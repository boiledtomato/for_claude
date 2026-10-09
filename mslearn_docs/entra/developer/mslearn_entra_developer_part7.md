# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 7)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 41

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-prepare-android-app"} -->
## ネイティブ認証用に Android モバイル アプリを準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-android-app
- Service: identity-platform / external
- Article date: 2024-05-30
- Summary: Microsoft Authentication Library (MSAL) ネイティブ認証 SDK フレームワークを Android アプリに追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Microsoft Authentication Library (MSAL) ネイティブ認証 SDK を Android モバイル アプリに追加する方法について説明します。

このチュートリアルでは、次の操作を行います。

- MSAL 依存関係を追加します。
- 構成ファイルを作成します。
- MSAL SDK インスタンスを作成します。

### 前提条件

- まだ行っていない場合は、「 [ネイティブ認証を使用してサンプル Android (Kotlin) モバイル アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-android-app)させ、外部テナントにアプリを登録する」の手順に従います。 次の手順を完了していることを確認します。
    - アプリケーションを登録します。
    - パブリック クライアントとネイティブ認証フローを有効にします。
    - API アクセス許可を付与します。
    - ユーザー フローを作成します。
    - アプリをユーザー フローに関連付けます。
- Android プロジェクト。 Android プロジェクトがない場合は、作成します。

### MSAL 依存関係を追加する

1. Android Studio でプロジェクトを開くか、新しいプロジェクトを作成します。
2. アプリケーションの `build.gradle` を開き、次の依存関係を追加します。

    ```gradle
    allprojects {
        repositories {
            //Needed for com.microsoft.device.display:display-mask library
            maven {
                url 'https://pkgs.dev.azure.com/MicrosoftDeviceSDK/DuoSDK-Public/_packaging/Duo-SDK-Feed/maven/v1'
                name 'Duo-SDK-Feed'
            }
            mavenCentral()
            google()
        }
    }
    //...
    
    dependencies { 
        implementation 'com.microsoft.identity.client:msal:6.+'
        //...
    }
    ```
3. Android Studio で、[ **File**&gt;**Sync Project with Gradle Files**] を選択します。

### 構成ファイルを作成する

JSON 構成設定を使用して、アプリケーション (クライアント) ID などの必要なテナント識別子を MSAL SDK に渡します。

構成ファイルを作成するには、次の手順に従います。

1. Android Studio のプロジェクト ウィンドウで、*app\src\main\res* に移動します。
2. **res** を右クリックし、**新規**&gt;Directory を選択**します**。 新しいディレクトリの名前に「`raw`」と入力し、**[OK]** を選択します。
3. *app\src\main\r\raw* で、`auth_config_native_auth.json`という名前の新しい JSON ファイルを作成します。
4. `auth_config_native_auth.json` ファイルに、次の MSAL 構成を追加します。

    ```json
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
     //...
    ```
5. 次のプレースホルダーを、Microsoft Entra 管理センターから取得したテナント値に置き換えます。

    - `Enter_the_Application_Id_Here` プレースホルダーを、先ほど登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here`をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)。

    チャレンジの種類は値の一覧であり、アプリはサポートされている認証方法について Microsoft Entra に通知するために使用します。

    - 電子メールワンタイム パスコードを使用したサインアップとサインイン フローの場合は、 `["oob"]`を使用します。
    - 電子メールとパスワードを使用したサインアップフローとサインイン フローでは、 `["oob","password"]`を使用します。
    - セルフサービス パスワード リセット (SSPR) の場合は、 `["oob"]`を使用します。

    その他の [チャレンジの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)について説明します。

#### 省略可能: ログ記録の構成

SDK がログを出力できるように、ログ記録コールバックを作成して、アプリの作成時にログ記録を有効にします。

```kotlin
import com.microsoft.identity.client.Logger

fun initialize(context: Context) {
        Logger.getInstance().setExternalLogger { tag, logLevel, message, containsPII ->
            Logs.append("$tag $logLevel $message")
        }
    }
```

ロガーを構成するには、構成ファイルにセクションを追加する必要があります。 `auth_config_native_auth.json`。

```json
    //...
   { 
     "logging": { 
       "pii_enabled": false, 
       "log_level": "INFO", 
       "logcat_enabled": true 
     } 
   } 
    //...
```

1. **logcat\_enabled**: ライブラリのログ機能を有効にします。
2. **pii\_enabled**: 個人データまたは組織データを含むメッセージをログに記録するかどうかを指定します。 false に設定すると、ログには個人データは含まれません。 true に設定すると、ログに個人データが含まれる場合があります。
3. **log\_level**: 有効にするログ記録のレベルを決定するために使用します。 Android では、次のログ レベルがサポートされています。
    1. エラー
    2. 警告:
    3. 情報
    4. 冗長

MSAL ログの詳細については、「 [Android 用 MSAL でのログ記録](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-logging-android)」を参照してください。

### ネイティブ認証 MSAL SDK インスタンスを作成する

`onCreate()`メソッドで MSAL インスタンスを作成し、アプリがネイティブ認証を介してテナントに対して認証を実行できるようにします。 `createNativeAuthPublicClientApplication()` メソッドは、`authClient`というインスタンスを返します。 前に作成した JSON 構成ファイルをパラメーターとして渡します。

```kotlin
    //...
    authClient = PublicClientApplication.createNativeAuthPublicClientApplication( 
        this, 
        R.raw.auth_config_native_auth 
    )
    //...
```

コードは次のスニペットのようになります。

```kotlin
    class MainActivity : AppCompatActivity() { 
        private lateinit var authClient: INativeAuthPublicClientApplication 
 
        override fun onCreate(savedInstanceState: Bundle?) { 
            super.onCreate(savedInstanceState) 
            setContentView(R.layout.activity_main) 
 
            authClient = PublicClientApplication.createNativeAuthPublicClientApplication( 
                this, 
                R.raw.auth_config_native_auth 
            ) 
            getAccountState() 
        } 
 
        private fun getAccountState() {
            CoroutineScope(Dispatchers.Main).launch {
                val accountResult = authClient.getCurrentAccount()
                when (accountResult) {
                    is GetAccountResult.AccountFound -> {
                        displaySignedInState(accountResult.resultValue)
                    }
                    is GetAccountResult.NoAccountFound -> {
                        displaySignedOutState()
                    }
                }
            }
        } 
 
        private fun displaySignedInState(accountResult: AccountState) { 
            val accountName = accountResult.getAccount().username 
            val textView: TextView = findViewById(R.id.accountText) 
            textView.text = "Cached account found: $accountName" 
        } 
 
        private fun displaySignedOutState() { 
            val textView: TextView = findViewById(R.id.accountText) 
            textView.text = "No cached account found" 
        } 
    } 
```

- `getCurrentAccount()` を使用し、オブジェクトを返す `accountResult` によってキャッシュされたアカウントを取得します。
- アカウントが永続化状態にある場合は、 `GetAccountResult.AccountFound` を使用してサインイン状態を表示します。
- それ以外の場合は、 `GetAccountResult.NoAccountFound` を使用してサインアウト状態を表示します。

必ず import ステートメントを含めます。 Android Studio では、自動的に import ステートメントが含められます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app"} -->
## ネイティブ認証用に iOS/macOS アプリを準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app
- Service: identity-platform / external
- Article date: 2024-09-30
- Summary: Microsoft Authentication Library (MSAL) ネイティブ認証 SDK フレームワークを iOS/macOS アプリケーションに追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Microsoft Authentication Library (MSAL) ネイティブ認証 SDK フレームワークを iOS/macOS Swift アプリに追加する方法について説明します。

このチュートリアルでは、次の操作を行います。

- iOS/macOS アプリに MSAL フレームワークを追加します。
- SDK インスタンスを作成します。

### 前提条件

- [Xcodeの](https://developer.apple.com/xcode/resources/)
- まだ行っていない場合は、「 [ネイティブ認証を使用してサンプル iOS (Swift) モバイル アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-ios-app)させ、外部テナントにアプリを登録する」の手順に従います。 次の手順を完了していることを確認します。
    - アプリケーションを登録します。
    - パブリック クライアントとネイティブ認証フローを有効にします。
    - API アクセス許可を付与します。
    - ユーザー フローを作成します。
    - アプリをユーザー フローに関連付けます。
- iOS/macOS プロジェクト

### iOS/macOS アプリに MSAL フレームワークを追加する

1. Xcode で iOS/macOS プロジェクトを開きます。
2. [**ファイル**] メニューから [**パッケージの依存関係の追加...**] を選択します。
3. パッケージ URL として「 `https://github.com/AzureAD/microsoft-authentication-library-for-objc` 」と入力し、[ **パッケージの追加]** を選択します。
4. 新しいキーチェーン グループをプロジェクトの **[機能]** に追加します。 iOS では `com.microsoft.adalcache` を使用し、macOS では `com.microsoft.identity.universalstorage` を使用します。

プロジェクトに MSAL を追加するための詳細およびその他のメカニズムについては、 [プロジェクトの Readme ファイル](https://github.com/AzureAD/microsoft-authentication-library-for-objc?tab=readme-ov-file#installation)を参照してください。

### SDK インスタンスを作成する

1. `import MSAL` クラスの上部に`ViewController`を追加して、MSAL ライブラリをビュー コントローラーにインポートします。
2. `nativeAuth`関数の直前に次のコードを追加して、`ViewController`メンバー変数を `viewDidLoad()` クラスに追加します。

    ```swift
    var nativeAuth: MSALNativeAuthPublicClientApplication!
    ```
3. 次に、 `viewDidLoad()` 関数に次のコードを追加します。

    ```swift
     do {
        nativeAuth = try MSALNativeAuthPublicClientApplication(
            clientId: "Enter_the_Application_Id_Here",
            tenantSubdomain: "Enter_the_Tenant_Subdomain_Here",
            challengeTypes: [.OOB]
        )
    
        print("Initialized Native Auth successfully.")
     } catch {
        print("Unable to initialize MSAL \(error)")
     }
    ```
4. 次の値を Microsoft Entra 管理センターの値に置き換えます。

    1. `Enter_the_Application_Id_Here`値を見つけて、先ほど登録したアプリの**アプリケーション (クライアント) ID** に置き換えます。
    2. `Enter_the_Tenant_Subdomain_Here`を見つけて、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 ディレクトリ (テナント) サブドメインがない場合は、 [テナントの詳細を読み取る方法について](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)説明します。

        チャレンジの種類は値の一覧であり、アプリはサポートされている認証方法について Microsoft Entra に通知するために使用します。

        - 電子メールワンタイム パスコードを使用したサインアップとサインイン フローの場合は、 `[.OOB]`を使用します。
        - 電子メールとパスワードを使用したサインアップフローとサインイン フローでは、 `[.OOB, .password]`を使用します。
        - セルフサービス パスワード リセット (SSPR) の場合は、 `[.OOB]`を使用します。

        その他の [チャレンジの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)について説明します。
5. ビルドするには、プロジェクトのツール バーで **Product**&gt;**Build** を選択します。

#### 省略可能: ログ記録の構成

MSAL には、ログ記録を有効にして構成するために使用できるログ記録 API が用意されています。 MSAL からのすべてのデバッグ出力を表示するには、 `viewDidLoad()` 関数の先頭に次のコードを追加します。

```swift
MSALGlobalConfig.loggerConfig.logLevel = .verbose
MSALGlobalConfig.loggerConfig.setLogCallback { logLevel, message, containsPII in
   if !containsPII {
      print("MSAL: \(message ?? "")")
   }
}
```

これにより、MSAL からすべてのデバッグ ログが出力されます。これは、問題の診断やネイティブ認証フローのしくみの学習に役立ちます。 ログ レベルとベスト プラクティスの構成の詳細については、 [iOS/macOS 用の MSAL でのログ記録](https://learn.microsoft.com/ja-jp/entra/msal/objc/logging-ios?tabs=swift)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-angular-enable-mfa"} -->
## ネイティブ認証 JavaScript SDK を使用して Angular SPA で MFA を有効にする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-enable-mfa
- Service: identity-platform / external
- Article date: 2026-01-18
- Summary: ネイティブ認証 JavaScript SDK を使用する Angular シングルページ アプリケーションで多要素認証を有効にする方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証の JavaScript SDK を使用して、Angular シングルページ アプリケーション (SPA) に多要素認証 (MFA) を追加する方法について説明します。

[強力な認証方法の登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-register-strong-method)と同様に、MFA フローは次の 3 つのシナリオで発生します。

- **サインイン中**: ユーザーはサインインし、強力な認証方法が登録されています。
- **サインアップ後**: ユーザーがサインアップを完了したら、サインインに進みます。 新しいユーザーは、MFA チャレンジ [の前に強力な認証方法を登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-register-strong-method) する必要があります。 強力な認証方法も登録中に検証されるため、追加の MFA チャレンジを求められない場合があります。
- **セルフサービス パスワード リセット (SSPR) 後**: ユーザーはパスワードを正常にリセットし、自動的にサインインに進みます。 ユーザーに強力な認証方法が登録されている場合は、MFA チャレンジを完了するように求められます。

MFA が必要な場合、ユーザーは登録済みの方法の一覧から MFA チャレンジ方法を選択します。 使用できるオプションは、ユーザーが以前に登録した内容に応じて、 **電子メール** ワンタイム パスコード、 **SMS** ワンタイム パスコード、またはその両方です。

次のフロー図は、次の 3 つのシナリオを示しています。

[Image: 多要素認証チャレンジを完了します。]

### [前提条件]

- [サインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up)、[サインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-in)、[パスワードリセット](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-reset-password)、[および強力な認証方法の登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-register-strong-method)に関するチュートリアルの手順を完了します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコード エディター。
- [Node.js](https://nodejs.org/en/download/).
- [アプリの多要素認証 (MFA) を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)。

### アプリで多要素認証を処理できるようにする

Angular アプリで MFA を有効にするには、必要な機能を追加してアプリの構成を更新します。

1. *src/app/config/auth-config.ts* ファイルを見つけます。
2. `customAuth` オブジェクトで、次のコード スニペットに示すように、`capabilities`プロパティを追加または更新して、配列に`mfa_required`値を含めます。

    ```typescript
    const customAuthConfig: CustomAuthConfiguration = {
        customAuth: {
            ...
            capabilities: ["mfa_required"],
            ...
        },
        ...
    };
    ```

機能の値 `mfa_required` は、アプリが MFA フローを処理できることをMicrosoft Entraに通知します。 [ネイティブ認証チャレンジの種類と機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)の詳細について説明します。

### UI コンポーネントを作成する

MFA チャレンジ方法の選択や MFA チャレンジの送信など、MFA フローをサポートするには、アプリのフォーム コンポーネントが必要です。

#### 多要素認証方法の選択フォームを作成する

1. コンソールで *src/app/components/shared* フォルダーに移動し、Angular CLI を使用して、次のコマンドを使用して *mfa-auth-method-selection-form* などのコンポーネントを作成します。

    ```console
    ng generate component mfa-auth-method-selection-form
    ```

    この*コマンドは、 * *src/app/components/shared/mfa-auth-method-selection-form/* フォルダーに *mfa-auth-method-selection-form.component.htmlファイルと*mfa-auth-method-selection-form.component.ts ファイルを生成します。
2. *mfa-auth-method-selection-form.component.ts* ファイルを開き、その内容を [mfa-auth-method-selection-form.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/shared/mfa-auth-method-selection-form/mfa-auth-method-selection-form.component.ts) 内のコンテンツに置き換えます。
3. *mfa-auth-method-selection-form.component.html* ファイルを開き、[mfa-auth-method-selection-form.component.html](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/shared/mfa-auth-method-selection-form/mfa-auth-method-selection-form.component.html)に内容を追加します。

#### 多要素認証チャレンジ フォームを作成する

1. コンソールで *src/app/components/shared* フォルダーに移動し、Angular CLI を使用して、次のコマンドを使用して *mfa-challenge-form* などのコンポーネントを作成します。

    ```console
    ng generate component mfa-challenge-form
    ```

    この*コマンドは、* *src/app/components/shared/mfa-challenge-form/*フォルダーに*mfa-challenge-form.component.tsファイルと *mfa-challenge-form.component.htmlファイルを生成します。
2. *mfa-challenge-form.component.ts* ファイルを開き、その内容を [mfa-challenge-form.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/shared/mfa-challenge-form/mfa-challenge-form.component.ts) 内のコンテンツに置き換えます。
3. *mfa-challenge-form.component.html* ファイルを開き、[mfa-challenge-form.component.html](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/shared/mfa-challenge-form/mfa-challenge-form.component.html)に内容を追加します。

### サインイン時に多要素認証を処理する

サインイン中にアプリが MFA フローを処理できるように *、src/app/components/sign-in/sign-in.component.ts* ファイルを更新します。 [sign-in.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-in/sign-in.component.ts)の完全なコードを参照してください。

1. 次のコード スニペットに示すように、必要な型とコンポーネントをインポートします。

    ```typescript
    import {
        AuthenticationMethod,
        MfaAwaitingState,
        MfaVerificationRequiredState,
    } from "@azure/msal-browser/custom-auth";
    import { MfaAuthMethodSelectionFormComponent } from "../shared/mfa-auth-method-selection-form/mfa-auth-method-selection-form.component";
    import { MfaChallengeFormComponent } from "../shared/mfa-challenge-form/mfa-challenge-form.component";
    
    @Component({
        selector: "app-sign-in",
        templateUrl: "./sign-in.component.html",
        standalone: true,
        imports: [
            ...
            MfaAuthMethodSelectionFormComponent,
            MfaChallengeFormComponent,
            ...
        ],
    })
    ```
2. MFA の新しい状態変数を追加します。

    ```typescript
    export default function SignIn() {
        ...
        // MFA states
    const [mfaAuthMethods, setMfaAuthMethods] = useState<AuthenticationMethod[]>([]);
    const [selectedMfaAuthMethod, setSelectedMfaAuthMethod] = useState<AuthenticationMethod | undefined>(undefined);
    const [mfaChallenge, setMfaChallenge] = useState("");
    
        // ... initialization code
    }
    ```
3. `startSignIn`、`handlePasswordSubmit`、および`handleCodeSubmit`関数を更新して、MFA が必要かどうかを確認します。

    ```typescript
    async startSignIn() {
        const client = await this.auth.getClient();
        const result: SignInResult = await client.signIn({ username: this.username });
    
        ...
    
        if (result.isMfaRequired()) {
            this.showMfaAuthMethods = true;
            this.showPassword = false;
            this.showCode = false;
            this.showAuthMethodsForRegistration = false;
            this.showChallengeForRegistration = false;
            this.showMfaChallenge = false;
            this.mfaAuthMethods = result.state.getAuthMethods();
            // Set default selection to the first MFA auth method
            this.selectedMfaAuthMethod = this.mfaAuthMethods.length > 0 ? this.mfaAuthMethods[0] : undefined;
            this.signInState = result.state;
        }
    
        ...
    }
    
    async submitPassword() {
        if (this.signInState instanceof SignInPasswordRequiredState) {
            const result = await this.signInState.submitPassword(this.password);
    
            ...
    
            if (result.isMfaRequired()) {
                this.showMfaAuthMethods = true;
                this.showPassword = false;
                this.showCode = false;
                this.showAuthMethodsForRegistration = false;
                this.showChallengeForRegistration = false;
                this.showMfaChallenge = false;
                this.mfaAuthMethods = result.state.getAuthMethods();
                // Set default selection to the first MFA auth method
                this.selectedMfaAuthMethod = this.mfaAuthMethods.length > 0 ? this.mfaAuthMethods[0] : undefined;
                this.signInState = result.state;
            }
    
            ...
        }
    }
    
    async submitCode() {
        if (this.signInState instanceof SignInCodeRequiredState) {
            const result = await this.signInState.submitCode(this.code);
    
            ...
    
            if (result.isMfaRequired()) {
                this.showMfaAuthMethods = true;
                this.showPassword = false;
                this.showCode = false;
                this.showAuthMethodsForRegistration = false;
                this.showChallengeForRegistration = false;
                this.showMfaChallenge = false;
                this.mfaAuthMethods = result.state.getAuthMethods();
                // Set default selection to the first MFA auth method
                this.selectedMfaAuthMethod = this.mfaAuthMethods.length > 0 ? this.mfaAuthMethods[0] : undefined;
                this.signInState = result.state;
            }
        }
    }
    ```

    各関数では、次のコード スニペットを使用して MFA が必要かどうかを確認します。

    ```typescript
    if (result.isMfaRequired()) {...}
    ```
4. MFA チャレンジ選択のハンドラーを追加します。

    ```typescript
    async submitMfaAuthMethod() {
        this.error = "";
        this.loading = true;
    
        if (!this.selectedMfaAuthMethod) {
            this.error = "Please select an authentication method.";
            this.loading = false;
            return;
        }
    
        if (this.signInState instanceof MfaAwaitingState) {
            const result = await this.signInState.requestChallenge(this.selectedMfaAuthMethod.id);
    
            if (result.isFailed()) {
                if (result.error?.isInvalidInput()) {
                    this.error = "Incorrect verification contact.";
                } else {
                    this.error =
                        result.error?.errorData?.errorDescription ||
                        "An error occurred while verifying the authentication method.";
                }
            }
    
            if (result.isVerificationRequired()) {
                this.showMfaAuthMethods = false;
                this.showMfaChallenge = true;
                this.signInState = result.state;
            }
        }
        this.loading = false;
    }
    ```
5. MFA チャレンジ検証のハンドラーを追加します。

    ```typescript
    async submitMfaChallenge() {
        this.error = "";
        this.loading = true;
    
        if (!this.mfaChallenge) {
            this.error = "Please enter a code.";
            this.loading = false;
            return;
        }
    
        if (this.signInState instanceof MfaVerificationRequiredState) {
            const result = await this.signInState.submitChallenge(this.mfaChallenge);
    
            if (result.isFailed()) {
                if (result.error?.isIncorrectChallenge()) {
                    this.error = "Incorrect code.";
                } else {
                    this.error =
                        result.error?.errorData?.errorDescription ||
                        "An error occurred while verifying the challenge response.";
                }
            }
    
            if (result.isCompleted()) {
                this.isSignedIn = true;
                this.userData = result.data;
                this.showMfaChallenge = false;
                this.signInState = result.state;
            }
        }
        this.loading = false;
    }
    ```
6. */src/app/components/sign-in/sign-in.component.html* ファイルを更新して、正しい MFA チャレンジ フォーム (MFA チャレンジ方式の選択または MFA チャレンジ方法の検証) を表示します。

    ```typescript
    <!-- Use shared MFA auth method selection form -->
    <app-mfa-auth-method-selection-form *ngIf="showMfaAuthMethods" [authMethods]="mfaAuthMethods"
        [selectedAuthMethod]="selectedMfaAuthMethod" [loading]="loading"
        (selectedAuthMethodChange)="selectedMfaAuthMethod = $event" (submitForm)="submitMfaAuthMethod()">
    </app-mfa-auth-method-selection-form>
    
    <!-- Use shared MFA challenge form -->
    <app-mfa-challenge-form *ngIf="showMfaChallenge" [challenge]="mfaChallenge" [loading]="loading"
        (challengeChange)="mfaChallenge = $event" (submitForm)="submitMfaChallenge()">
    </app-mfa-challenge-form>
    ```

### サインアップまたはパスワードのリセット後に多要素認証を処理する

サインアップとパスワードのリセット後の MFA フローは、サインイン フローの MFA と同様に機能します。 サインアップまたはパスワードのリセットが成功すると、SDK は自動的にサインイン フローを続行できます。 ユーザーに強力な認証方法が登録されている場合、フローは MFA チャレンジ検証に移行します。

#### サインアップ後の多要素認証の処理

サインアップ後の MFA フローの場合は、 */src/app/components/sign-up/sign-up.component.ts* ファイルを更新する必要があります。 [sign-up.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.ts)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートしてください。
2. サインイン フローで発生するのと同様の方法で MFA 要件の状態を処理します。サインアップが正常に完了したら、次のコード スニペットに示すように、結果を使用して自動的にサインイン フローをトリガーします。

    ```typescript
    // In your sign-up completion handler
    if (this.signUpState instanceof SignUpCompletedState) {
        const result = await this.signUpState.signIn();
    
        ...
    
        if (result.isMfaRequired()) {
            this.showMfaAuthMethods = true;
            this.showPassword = false;
            this.showCode = false;
            this.showAuthMethodsForRegistration = false;
            this.showChallengeForRegistration = false;
            this.showMfaChallenge = false;
            this.mfaAuthMethods = result.state.getAuthMethods();
            // Set default selection to the first MFA auth method
            this.selectedMfaAuthMethod = this.mfaAuthMethods.length > 0 ? this.mfaAuthMethods[0] : undefined;
            this.signUpState = result.state;
        }
    
        ...
    }
    ```
3. */src/app/components/sign-up/sign-up.component.html* ファイルを更新して、MFA フォーム (MFA メソッド選択フォームと MFA チャレンジ検証フォーム) を追加します。 [sign-up.component.html](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.html)の完全な例を参照してください。

#### パスワードリセット後の多要素認証の処理

SSPR 後の MFA フローの場合は、 */src/app/components/reset-password/reset-password.component.ts* ファイルを更新する必要があります。 [reset-password.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/reset-password/reset-password.component.ts)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートしてください。
2. サインイン フローで発生するのと同様の方法で MFA 要件の状態を処理します。サインアップが正常に完了したら、次のコード スニペットに示すように、結果を使用して自動的にサインイン フローをトリガーします。

    ```typescript
    if (this.resetState instanceof ResetPasswordCompletedState) {
        const result = await this.resetState.signIn();
    
        ...
    
        if (result.isMfaRequired()) {
            this.showMfaAuthMethods = true;
            this.showCode = false;
            this.showNewPassword = false;
            this.showAuthMethodsForRegistration = false;
            this.showChallengeForRegistration = false;
            this.showMfaChallenge = false;
            this.isReset = false;
            this.mfaAuthMethods = result.state.getAuthMethods();
            // Set default selection to the first MFA auth method
            this.selectedMfaAuthMethod = this.mfaAuthMethods.length > 0 ? this.mfaAuthMethods[0] : undefined;
            this.resetState = result.state;
        }
    
        ...
    }
    ```
3. */src/app/components/reset-password/reset-password.component.html* ファイルを更新して、MFA フォーム (MFA メソッドの選択フォームと MFA チャレンジ検証フォーム) を追加します。 [reset-password.component.html](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/reset-password/reset-password.component.html)の完全な例を参照してください。

### アプリを実行してテストする

アプリをテストする前に、強力な認証方法が登録されているユーザー アカウントがあることを確認します。 [アプリの実行とテスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-register-strong-method#run-and-test-your-app)の手順を参照してアプリを実行しますが、今回は MFA フローのテストに重点を置きます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-angular-register-strong-method"} -->
## ネイティブ認証 JavaScript SDK を使用して Angular SPA に強力な認証方法を登録する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-register-strong-method
- Service: identity-platform / external
- Article date: 2026-01-18
- Summary: ネイティブ認証 JavaScript SDK を使用する Angular シングルページ アプリケーションに強力な認証方法を登録する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証の JavaScript SDK を使用して、Angular シングルページ アプリケーション (SPA) にユーザーの強力な認証方法を登録します。

多要素認証 (MFA) を有効にしても、ユーザーに強力な認証方法が登録されていない場合は、トークンを発行する前にユーザーに登録する必要があります。

強力な認証方法の登録フローは、次の 3 つのシナリオで発生します。

- **サインイン中**: ユーザーはサインインしますが、強力な認証方法が登録されていません。
- **サインアップ後**: ユーザーは正常にサインアップし、自動的にサインインに進みます。
- **セルフサービス パスワード リセット (SSPR) 後**: ユーザーはパスワードを正常にリセットし、自動的にサインインに進みます。

強力な認証方法の登録が必要な場合、ユーザーは、サポートされている方法の一覧から選択する方法を選択します。 使用できる方法は、 **電子メール** と **SMS** ワンタイム パスコードです。

次のフロー図は、次の 3 つのシナリオを示しています。

[Image: 強力な認証方法を登録します。]

### [前提条件]

- [サインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up)、サインイン、[パスワードリセット](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-in)のチュートリアル[の](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-reset-password)手順を完了します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコード エディター。
- [Node.jsバージョン20.x以降](https://nodejs.org/en/download/)
- [Angular CLI](https://angular.dev/tools/cli/setup-local) がグローバルにインストールされている。 この例では、バージョン 19.2.1 を使用します。
- [アプリの多要素認証 (MFA) を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)。

### アプリで強力な認証方法の登録を処理できるようにする

React アプリで強力な認証方法の登録を有効にするには、必要な機能を追加してアプリ構成を更新します。

1. *src/app/config/auth-config.ts* ファイルを見つけます。
2. `customAuth` オブジェクトで、次のコード スニペットに示すように、`capabilities` プロパティを追加または更新して、配列に`registration_required`値を含めます。

    ```typescript
    const customAuthConfig: CustomAuthConfiguration = {
        customAuth: {
            ...
            capabilities: ["registration_required"],
            ...
        },
        ...
    };
    ```

機能の値 `registration_required` は、アプリが強力な認証方法の登録フローを処理できることをMicrosoft Entraに通知します。 [ネイティブ認証チャレンジの種類と機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)の詳細について説明します。

### UI コンポーネントを作成する

強力な認証方法の登録を処理するには、フォーム コンポーネントが必要です。 次のセクションでは、フォーム コンポーネントを追加する方法について説明します。

#### 強力な認証方法の選択フォームを作成する

1. コンソールで *src/app/components/shared* フォルダーに移動し、Angular CLI を使用して、次のコマンドを使用して *auth-method-selection-form* などのコンポーネントを作成します。

    ```console
    ng generate component auth-method-selection-form
    ```

    このコマンド*は、 * *src/app/components/shared/auth-method-selection-form/*フォルダー内*のauth-method-selection-form.component.htmlファイル*とauth-method-selection-form.component.ts ファイルを生成します。
2. *auth-method-selection-form.component.ts* ファイルを開き、その内容を [auth-method-selection-form.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/shared/auth-method-selection-form/auth-method-selection-form.component.ts) 内のコンテンツに置き換えます。
3. auth-method-selection-form.component.html ファイルを開き、auth-method-selection-form.component.html

#### 強力な認証方法チャレンジ フォームを作成する

1. コンソールで *src/app/components/shared* フォルダーに移動し、Angular CLI を使用して、次のコマンドを使用して *auth-method-challenge-form* などのコンポーネントを作成します。

    ```console
    ng generate component auth-method-challenge-form
    ```

    このコマンド*は、* *src/app/components/shared/auth-method-challenge-form/*フォルダーに*auth-method-challenge-form.component.tsファイルと *auth-method-challenge-form.component.htmlファイルを生成します。
2. *auth-method-challenge-form.component.ts* ファイルを開き、その内容を [auth-method-challenge-form.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/shared/auth-method-challenge-form/auth-method-challenge-form.component.ts) 内のコンテンツに置き換えます。
3. auth-method-challenge-form.component.html ファイルを開き、その中に内容を追加します。

### サインイン時に強力な認証方法を登録する

サインイン中にアプリが強力な認証方法の登録フローを処理できるように、 *src/app/components/sign-in/sign-in.component.ts* ファイルを更新します。 [sign-in.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-in/sign-in.component.ts)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートします。

    ```typescript
    import {
        AuthMethodRegistrationRequiredState,
        AuthenticationMethod,
        AuthMethodVerificationRequiredState,
    } from "@azure/msal-browser/custom-auth";
    import { AuthMethodSelectionFormComponent } from "../shared/auth-method-selection-form/auth-method-selection-form.component";
    import { AuthMethodChallengeFormComponent } from "../shared/auth-method-challenge-form/auth-method-challenge-form.component";
    
    @Component({
        selector: "app-sign-in",
        templateUrl: "./sign-in.component.html",
        standalone: true,
        imports: [
            ...
            AuthMethodSelectionFormComponent,
            AuthMethodChallengeFormComponent,
            ...
        ],
    })
    ```
2. 強力な認証方法の登録に新しい変数を追加します。

    ```typescript
    showAuthMethodsForRegistration = false;
    showChallengeForRegistration = false;
    authMethodsForRegistration: AuthenticationMethod[] = [];
    selectedAuthMethodForRegistration: AuthenticationMethod | undefined = undefined;
    verificationContactForRegistration: string | undefined = undefined;
    challengeForRegistration: string | undefined = undefined;
    ```
3. `startSignIn`、`handlePasswordSubmit`、および`handleCodeSubmit`関数を更新して、強力な認証方法の登録が必要かどうかを確認します。

    ```typescript
    async startSignIn() {
        const client = await this.auth.getClient();
        const result: SignInResult = await client.signIn({ username: this.username });
    
        ...
    
        if (result.isAuthMethodRegistrationRequired()) {
            this.showAuthMethodsForRegistration = true;
            this.showPassword = false;
            this.showCode = false;
            this.showChallengeForRegistration = false;
            this.showMfaAuthMethods = false;
            this.showMfaChallenge = false;
            this.authMethodsForRegistration = result.state.getAuthMethods();
            // Set default selection to the first auth method
            this.selectedAuthMethodForRegistration =
                this.authMethodsForRegistration.length > 0 ? this.authMethodsForRegistration[0] : undefined;
            this.signInState = result.state;
        }
    
        ...
    }
    
    async submitPassword() {
        if (this.signInState instanceof SignInPasswordRequiredState) {
            const result = await this.signInState.submitPassword(this.password);
    
            ...
    
            if (result.isAuthMethodRegistrationRequired()) {
                this.showAuthMethodsForRegistration = true;
                this.showPassword = false;
                this.showCode = false;
                this.showChallengeForRegistration = false;
                this.showMfaAuthMethods = false;
                this.showMfaChallenge = false;
                this.authMethodsForRegistration = result.state.getAuthMethods();
                // Set default selection to the first auth method
                this.selectedAuthMethodForRegistration =
                    this.authMethodsForRegistration.length > 0 ? this.authMethodsForRegistration[0] : undefined;
                this.signInState = result.state;
            }
    
            ...
        }
    }
    
    async submitCode() {
        if (this.signInState instanceof SignInCodeRequiredState) {
            const result = await this.signInState.submitCode(this.code);
    
            ...
    
            if (result.isAuthMethodRegistrationRequired()) {
                this.showAuthMethodsForRegistration = true;
                this.showPassword = false;
                this.showCode = false;
                this.showChallengeForRegistration = false;
                this.showMfaAuthMethods = false;
                this.showMfaChallenge = false;
                this.authMethodsForRegistration = result.state.getAuthMethods();
                // Set default selection to the first auth method
                this.selectedAuthMethodForRegistration =
                    this.authMethodsForRegistration.length > 0 ? this.authMethodsForRegistration[0] : undefined;
                this.signInState = result.state;
            }
        }
    }
    ```

    各関数では、次のコード スニペットを使用して、強力な認証方法の登録が必要かどうかを確認します。

    ```typescript
    if (result.isAuthMethodRegistrationRequired()) {...}
    ```
4. 強力な認証方法を選択するためのハンドラーを追加します。

    ```typescript
    async submitAuthMethodForRegistration() {
        this.error = "";
        this.loading = true;
    
        if (!this.selectedAuthMethodForRegistration || !this.verificationContactForRegistration) {
            this.error = "Please select an authentication method and enter a verification contact.";
            this.loading = false;
            return;
        }
    
        if (this.signInState instanceof AuthMethodRegistrationRequiredState) {
            const result = await this.signInState.challengeAuthMethod({
                authMethodType: this.selectedAuthMethodForRegistration,
                verificationContact: this.verificationContactForRegistration,
            });
    
            if (result.isFailed()) {
                if (result.error?.isInvalidInput()) {
                    this.error = "Incorrect verification contact.";
                } else if (result.error?.isVerificationContactBlocked()) {
                    this.error =
                        "The verification contact is blocked. Consider using a different contact or a different authentication method.";
                } else {
                    this.error =
                        result.error?.errorData?.errorDescription ||
                        "An error occurred while verifying the authentication method.";
                }
            }
    
            if (result.isCompleted()) {
                this.isSignedIn = true;
                this.userData = result.data;
                this.showAuthMethodsForRegistration = false;
                this.signInState = result.state;
            }
    
            if (result.isVerificationRequired()) {
                this.showAuthMethodsForRegistration = false;
                this.showChallengeForRegistration = true;
                this.signInState = result.state;
            }
        }
        this.loading = false;
    }
    ```
5. 強力な認証方法の検証用のハンドラーを追加します。

    ```typescript
    async submitChallengeForRegistration() {
        this.error = "";
        this.loading = true;
    
        if (!this.challengeForRegistration) {
            this.error = "Please enter a code.";
            this.loading = false;
            return;
        }
    
        if (this.signInState instanceof AuthMethodVerificationRequiredState) {
            const result = await this.signInState.submitChallenge(this.challengeForRegistration);
    
            if (result.isFailed()) {
                if (result.error?.isIncorrectChallenge()) {
                    this.error = "Incorrect code.";
                } else {
                    this.error =
                        result.error?.errorData?.errorDescription ||
                        "An error occurred while verifying the challenge response.";
                }
            }
    
            if (result.isCompleted()) {
                this.isSignedIn = true;
                this.userData = result.data;
                this.showChallengeForRegistration = false;
                this.signInState = result.state;
            }
        }
        this.loading = false;
    }
    ```
6. 認証方法の登録フォームを含むように *sign-in.component.html* ファイルを更新します。 [sign-in.component.html](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-in/sign-in.component.html)の完全な例を参照してください。

    ```typescript
    <!-- Use shared auth method selection form -->
    <app-auth-method-selection-form *ngIf="showAuthMethodsForRegistration" [authMethods]="authMethodsForRegistration"
        [selectedAuthMethod]="selectedAuthMethodForRegistration"
        [verificationContact]="verificationContactForRegistration" [loading]="loading"
        [getPlaceholderText]="getPlaceholderTextForVerificationContact.bind(this)"
        (selectedAuthMethodChange)="selectedAuthMethodForRegistration = $event"
        (verificationContactChange)="verificationContactForRegistration = $event"
        (submitForm)="submitAuthMethodForRegistration()">
    </app-auth-method-selection-form>
    
    <!-- Use shared challenge form -->
    <app-auth-method-challenge-form *ngIf="showChallengeForRegistration" [challenge]="challengeForRegistration"
        [loading]="loading" (challengeChange)="challengeForRegistration = $event"
        (submitForm)="submitChallengeForRegistration()">
    </app-auth-method-challenge-form>
    ```

### サインアップまたはパスワードのリセット後に強力な認証方法を登録する

サインアップとパスワードリセット後の強力な認証方法の登録フローは、サインインフロー中の方法の登録と同様に機能します。 サインアップまたはパスワードのリセットが成功すると、SDK は自動的にサインインを続行できます。 ユーザーに強力な認証方法が登録されていない場合、フローは認証方法の登録状態に遷移します。

#### サインアップ後に強力な認証方法を登録する

サインアップ フロー後に強力な認証方法を登録するには、 *src/app/components/sign-up/sign-up.component.ts* ファイルを更新する必要があります。 [sign-up.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.ts)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートしてください。
2. サインイン フローで発生するのと同様の方法で、強力な認証方法の登録状態を処理します。 サインアップが正常に完了したら、次のコード スニペットに示すように、結果を使用して自動的にサインインをトリガーできます。

    ```typescript
    // In your sign-up completion handler
    if (this.signUpState instanceof SignUpCompletedState) {
        const result = await this.signUpState.signIn();
    
        ...
    
        if (result.isAuthMethodRegistrationRequired()) {
            this.showAuthMethodsForRegistration = true;
            this.showPassword = false;
            this.showCode = false;
            this.showChallengeForRegistration = false;
            this.showMfaAuthMethods = false;
            this.showMfaChallenge = false;
            this.authMethodsForRegistration = result.state.getAuthMethods();
            // Set default selection to the first auth method
            this.selectedAuthMethodForRegistration =
                this.authMethodsForRegistration.length > 0 ? this.authMethodsForRegistration[0] : undefined;
            this.signUpState = result.state;
        } 
    
        ...
    }
    ```
3. サインイン フローに示すように、 * 認証 * 方法の登録フォーム (*auth-method-selection-form* と *auth-method-challenge-form*) を追加するように、sign-up.component.htmlファイルを更新します。 [sign-up.component.html](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.html)の完全な例を参照してください。

#### パスワードのリセット後に強力な認証方法を登録する

SSPR の後に強力な認証方法を登録するには、 */src/app/components/reset-password/reset-password.component.ts* ファイルを更新する必要があります。 [reset-password.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/reset-password/reset-password.component.ts)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートしてください。
2. サインイン フローと同様の方法で、強力な認証方法の登録状態を処理します。 SSPRS が正常に完了したら、次のコード スニペットに示すように、結果を使用して自動的にサインインをトリガーできます。

    ```typescript
    if (this.resetState instanceof ResetPasswordCompletedState) {
        const result = await this.resetState.signIn();
    
        ...
    
        if (result.isAuthMethodRegistrationRequired()) {
            this.showAuthMethodsForRegistration = true;
            this.showCode = false;
            this.showNewPassword = false;
            this.showChallengeForRegistration = false;
            this.showMfaAuthMethods = false;
            this.showMfaChallenge = false;
            this.isReset = false;
            this.authMethodsForRegistration = result.state.getAuthMethods();
            // Set default selection to the first auth method
            this.selectedAuthMethodForRegistration =
                this.authMethodsForRegistration.length > 0 ? this.authMethodsForRegistration[0] : undefined;
            this.resetState = result.state;
        }
    
        ...
    }    
    ```
3. サインイン フローに示すように、 * 認証 * 方法の登録フォーム (*auth-method-selection-form* と *auth-method-challenge-form*) を追加するように、reset-password.component.htmlファイルを更新します。 [reset-password.component.html](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/reset-password/reset-password.component.html)の完全な例を参照してください。

### アプリを実行してテストする

「 [アプリを実行してテストしてアプリを](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up#test-the-sign-up-flow) 実行する」の手順を使用しますが、今回は強力な認証方法の登録フローをテストします。

#### サインアップ後の認証方法の登録をテストする

1. [http://localhost:4200/sign-up](http://localhost:3000/sign-up)に移動してサインアップ フォームを表示します。
2. 必要な詳細を入力し、次のプロンプトでサインアップします。 正常にサインアップすると、強力な認証方法の登録フォームが表示され、アプリは自動的にサインイン フローを続行します。
3. 選択した強力な認証方法 ( **Emails OTP** など) を選択し、メール アドレスを入力します。
4. [ **続行]** を選択してフォームの詳細を送信します。 確認コードがメール アドレスに届きます。
5. チャレンジ フォームのテキスト ボックスに確認コードを入力し、[ **続行** ] ボタンを選択します。 コードが検証されると、強力な認証方法が登録され、サインインします。

#### サインイン中に強力な認証方法の登録をテストする

サインイン時に強力な認証方法の登録をテストするには、強力な認証方法が登録されていないユーザー アカウントがあることを確認します。

1. [http://localhost:4200/sign-in](http://localhost:3000/sign-in)に移動してサインイン フォームを表示します。
2. 詳細を入力し、[ **続行** ] ボタンを選択し、プロンプトに従います。 アプリが強力な認証方法の登録フローに入ります。
3. アプリのプロンプトに従って、強力な認証方法の登録を完了します。

#### パスワード リセット後の認証方法の登録をテストする

SSPR の後に強力な認証方法の登録をテストするには、強力な認証方法が登録されていないユーザー アカウントがあることを確認します。

1. [http://localhost:4200/reset-password](http://localhost:3000/reset-password)に移動して、パスワード リセット フォームを表示します。
2. 詳細を入力し、[ **続行** ] ボタンを選択し、アプリのプロンプトに従ってパスワード リセット フローを完了します。 パスワードを正常にリセットすると、強力な認証方法の登録フォームが表示され、アプリのサインイン フローが続行されます。
3. アプリのプロンプトに従って、強力な認証方法の登録を完了します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-angular-reset-password"} -->
## ネイティブ認証 JavaScript SDK を使用して Angular SPA アプリのパスワードをリセットする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-reset-password
- Service: identity-platform / external
- Article date: 2025-11-18
- Summary: ネイティブ認証 JavaScript SDK を使用してユーザーがパスワードをリセットできるようにする Angular シングルページ アプリを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証 JavaScript SDK を使用して Angular シングルページ アプリでパスワードリセットを有効にする方法について説明します。 パスワードのリセットは、パスワード認証フローで電子メールを使用するユーザー アカウントで使用できます。

このチュートリアルでは、次の操作を行います。

- Angular アプリを更新して、ユーザーのパスワードをリセットします。
- パスワード リセット フローをテストする

### [前提条件]

- [「チュートリアル: ネイティブ認証 JavaScript SDK を使用して Angular シングルページ アプリにユーザーをサインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up)する」の手順を完了します。
- 外部テナントの顧客ユーザーに対して[セルフサービス パスワード リセット (SSPR) を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)。 SSPR は、パスワード認証フローで電子メールを使用するアプリの顧客ユーザーが利用できます。

### パスワード リセット コンポーネントを作成する

1. Angular CLI を使用して、次のコマンドを実行して、components フォルダー内にパスワード リセット用の新しいコンポーネントを生成します。

    ```console
    cd components
    ng generate component reset-password
    ```
2. *reset-password/reset-password.component.ts ファイルを*開き、その内容を[reset-password.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/reset-password/reset-password.component.ts)の内容に置き換えます
3. *reset-password/reset-password.component.htmlファイルを*開き、reset-password.component.htmlから内容を追加[します](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/reset-password/reset-password.component.html)。

    - *reset-password.component.ts*の次のロジックは、パスワードの初期リセット操作後の次の手順を決定します。 結果に応じて、パスワード リセット プロセスを続行するために * 、reset-password.component.html* にコード入力フォームが表示されます。

        ```typescript
        const result = await client.resetPassword({ username: this.email });
        if (result.isCodeRequired()) {
                    this.showCode = true;
                    this.isReset = false;
                    this.showNewPassword = false;
                }
        ```

        - SDK のインスタンス メソッド resetPassword() は、パスワード リセット フローを開始します。
        - reset-password.component.htmlファイルで次の * 手順を実行 * します。

        ```html
        <form *ngIf="showCode" (ngSubmit)="submitCode()">
            <input type="text" [(ngModel)]="code" name="code" placeholder="OTP Code" required />
            <button type="button" (click)="submitCode()" [disabled]="loading">{{ loading ? 'Verifying...' : 'Verify Code' }}</button>
            </form>
        ```
    - `submitCode()`が正常に呼び出されると、結果によって次の手順が決定されます。パスワードが必要な場合は、ユーザーがパスワード リセット プロセスを続行するための新しいパスワード フォームが表示されます。

        ```typescript
        if (result.isPasswordRequired()) {
            this.showCode = false;
            this.showNewPassword = true;
            this.isReset = false;
            this.resetState = result.state;
        }
        ```

        reset-password.component.htmlファイルで次の * 手順を実行 * します。

    ```html
      <form *ngIf="showNewPassword" (ngSubmit)="submitNewPassword()">
        <input type="password" [(ngModel)]="newPassword" name="newPassword" placeholder="New Password" required />
        <button type="button" (click)="submitNewPassword()" [disabled]="loading">{{ loading ? 'Submitting...' : 'Submit New Password' }}</button>
      </form>
    ```

    - パスワードのリセットが完了した直後にユーザーがサインイン フローを開始する場合は、次のスニペットを使用します。

        ```html
        <div *ngIf="isReset">
            <p>The password has been reset, please click <a href="/sign-in">here</a> to sign in.</p>
        </div>
        ```

#### パスワードのリセット後に自動的にサインインする (省略可能)

新しいサインイン フローを開始せずに、パスワードのリセットが成功した後にユーザーを自動的にサインインさせることができます。 これを行うには、次のコード スニペットを使用します。 [reset-password.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/reset-password/reset-password.component.ts)の完全な例を参照してください。

```typescript
if (this.resetState instanceof ResetPasswordCompletedState) {
    const result = await this.resetState.signIn();
    
    if (result.isFailed()) {
        this.error = result.error?.errorData?.errorDescription || "An error occurred during auto sign-in";
    }
    
    if (result.isCompleted()) {
        this.userData = result.data;
        this.resetState = result.state;
        this.isReset = true;
        this.showCode = false;
        this.showNewPassword = false;
    }
}
```

ユーザーで自動署名する場合は、 [reset-password.component.html](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/reset-password/reset-password.component.html)で次のスニペットを使用します。

```html
<div *ngIf="userData && !isSignedIn">
    <p>Password reset completed, and signed in as {{ userData?.getAccount()?.username }}</p>
</div>
<div *ngIf="isReset && !userData">
    <p>Password reset completed! Signing you in automatically...</p>
</div>
```

### ルーティング モジュールを更新する

*src/app/app.routes.ts* ファイルを開き、パスワード リセット コンポーネントのルートを追加します。

```typescript
import { ResetPasswordComponent } from './reset-password/reset-password.component';

const routes: Routes = [
    { path: 'reset-password', component: ResetPasswordComponent },
    ...
];
```

### パスワード リセット機能をテストする

1. CORS プロキシ サーバーを起動するには、ターミナルで次のコマンドを実行します。

    ```console
    npm run cors
    ```
2. アプリケーションを起動するには、ターミナルで次のコマンドを実行します。

    ```console
    npm start
    ```
3. Web ブラウザーを開き、`http://localhost:4200/reset-password`に移動します。 サインアップ フォームが表示されます。
4. 既存のユーザー アカウントのパスワードをリセットするには、詳細を入力し、[ **続行** ] ボタンを選択し、プロンプトに従います。

### next.config.js で `poweredByHeader: false` を設定する

- Next.jsでは、アプリケーションが Next.jsで動作していることを示す x-powered-by ヘッダーが既定で HTTP 応答に含まれます。 ただし、セキュリティ上またはカスタマイズ上の理由から、このヘッダーを削除または変更することが必要な場合があります。

    ```typescript
    const nextConfig: NextConfig = {
      poweredByHeader: false,
      /* other config options here */
    };
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-in"} -->
## ネイティブ認証 JavaScript SDK を使用して Angular SPA にユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-in
- Service: identity-platform / external
- Article date: 2025-11-18
- Summary: ネイティブ認証 JavaScript SDK を使用して外部テナントにユーザーをサインインさせる Angular シングルページ アプリを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証 JavaScript SDK を使用して、Angular シングルページ アプリ (SPA) にユーザーをサインインさせる方法について説明します。

このチュートリアルでは、次の操作を行います。

- Angular アプリを更新してユーザーをサインインさせる。
- サインイン フローをテストします。

### [前提条件]

- [「ネイティブ認証 JavaScript SDK を使用して Angular シングルページ アプリにユーザーをサインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up)する」の手順を完了します。

### サインイン コンポーネントを作成する

1. Angular CLI を使用して、次のコマンドを実行して *、components* フォルダー内のサインイン ページ用の新しいコンポーネントを生成します。

    ```console
    cd components
    ng generate component sign-in
    ```
2. *サインイン/sign-in.component.ts ファイルを*開き、その内容を[sign-in.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-in/sign-in.component.ts)のコンテンツに置き換えます
3. *サインイン/sign-in.component.htmlファイルを*開き、sign-in.component.htmlから内容[を](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-in/sign-in.component.html)追加します。

    - *sign-in.component.ts*の次のロジックは、最初のサインイン試行後の次の手順を決定します。 結果に応じて、サインイン プロセスの適切な部分をユーザーに案内するために * 、パスワード * または 1 回限りのコード フォームがsign-in.component.htmlに表示されます。

        ```typescript
            const result: SignInResult = await client.signIn({ username: this.username });
        
            if (result.isPasswordRequired()) {
                this.showPassword = true;
                this.showCode = false;
            } else if (result.isCodeRequired()) {
                this.showPassword = false;
                this.showCode = true;
            } else if (result.isCompleted()) {
                this.isSignedIn = true;
                this.userData = result.data;
            }
        ```

        - SDK のインスタンス メソッド `signIn()` 、サインイン フローを開始します。

        Note

        `username` パラメーターは、テナントのユーザー フローで Username 組み込みユーザー属性が有効になっている場合、ユーザーの電子メール アドレスまたはユーザー**名** (エイリアス) のいずれかを受け入れます。 ユーザーは、いずれかの値を入力してサインインできます。 この属性を有効にするには、「 [サインイン識別子ポリシーでユーザー名を有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)」を参照してください。

        - sign-in.component.htmlファイルで次の * 手順を実行 * します。

        ```html
        <form *ngIf="showPassword" (ngSubmit)="submitPassword()">
            <input type="password" [(ngModel)]="password" name="password" placeholder="Password" required />
            <button type="submit" [disabled]="loading">{{ loading ? 'Verifying...' : 'Submit Password' }}</button>
        </form>
        <form *ngIf="showCode" (ngSubmit)="submitCode()">
            <input type="text" [(ngModel)]="code" name="code" placeholder="OTP Code" required />
            <button type="submit" [disabled]="loading">{{ loading ? 'Verifying...' : 'Submit Code' }}</button>
        </form>
        ```

### ルーティング モジュールを更新する

*src/app/app.routes.ts* ファイルを開き、サインイン コンポーネントのルートを追加します。

```typescript
import { SignInComponent } from './components/sign-in/sign-in.component';

export const routes: Routes = [
    ...
    { path: 'sign-in', component: SignInComponent },
];
```

### サインイン機能をテストする

1. CORS プロキシ サーバーを起動するには、ターミナルで次のコマンドを実行します。

    ```console
    npm run cors
    ```
2. Angular アプリを起動するには、別のターミナル ウィンドウを開き、次のコマンドを実行します。

    ```console
    npm start
    ```
3. Web ブラウザーを開き、`http://localhost:4200/sign-in`に移動します。 サインイン フォームが表示されます。
4. 既存のアカウントにサインインするには、詳細を入力し、[サインイン] ボタンを選択し、プロンプトに従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up"} -->
## ネイティブ認証 SDK を使用して Angular SPA にユーザーをサインアップする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up
- Service: identity-platform / external
- Article date: 2025-11-18
- Summary: ネイティブ認証 JavaScript SDK を使用してユーザーをサインアップする Angular シングルページ アプリケーションを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証の JavaScript SDK を使用してユーザーをサインアップする Angular シングルページ アプリを構築する方法について説明します。

このチュートリアルでは、次の操作を行います。

- Angular Next.js プロジェクトを作成します。
- MSAL JS SDK を追加します。
- アプリの UI コンポーネントを追加します。
- ユーザーをサインアップするようにプロジェクトをセットアップします。

### [前提条件]

- [「クイック スタート: ネイティブ認証 JavaScript SDK を使用して Angular シングルページ アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-sdk-sign-in?tabs=angular)する」の手順を完了します。 このクイックスタートでは、サンプルの Angular コード サンプルを実行する方法について説明します。
- [「ネイティブ認証用の CORS ヘッダーを管理するための CORS プロキシ サーバーの設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-single-page-app-javascript-sdk-set-up-local-cors)」の手順を完了します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコード エディター。
- [Node.js](https://nodejs.org/en/download/)。
- [Angular CLI](https://angular.dev/tools/cli)。
- ユーザーがユーザー名 (エイリアス) でサインアップできるようにする場合は、サインアップ ユーザー フローで **Username** 組み込みユーザー属性を有効にします。 手順については、「 [サインイン識別子ポリシーでユーザー名を有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)」を参照してください。

### React プロジェクトを作成して依存関係をインストールする

コンピューター内の任意の場所で、次のコマンドを実行して *reactspa* という名前の新しい Angular プロジェクトを作成し、プロジェクト フォルダーに移動して、パッケージをインストールします。

```console
ng new angularspa
cd angularspa
```

コマンドを正常に実行すると、次の構造のアプリが作成されます。

```console
angularspa/
└──node_modules/
   └──...
└──public/
   └──...
└──src/
   └──app/
      └──app.component.html
      └──app.component.scss
      └──app.component.ts
      └──app.modules.ts
      └──app.config.ts
      └──app.routes.ts
   └──index.html
   └──main.ts
   └──style.scss
└──angular.json
└──package-lock.json
└──package.json
└──README.md
└──tsconfig.app.json
└──tsconfig.json
└──tsconfig.spec.json
```

### JavaScript SDK をプロジェクトに追加する

アプリでネイティブ認証 JavaScript SDK を使用するには、ターミナルを使用して次のコマンドを使用してインストールします。

```console
npm install @azure/msal-browser
```

ネイティブ認証機能は、 `azure-msal-browser` ライブラリの一部です。 ネイティブ認証機能を使用するには、 `@azure/msal-browser/custom-auth`からインポートします。 例えば次が挙げられます。

```typescript
  import CustomAuthPublicClientApplication from "@azure/msal-browser/custom-auth";
```

### クライアント構成の追加

このセクションでは、ネイティブ認証パブリック クライアント アプリケーションが SDK のインターフェイスと対話できるように構成を定義します。 そのためには、次の操作を実行します。

1. *src/app/config/auth-config.ts* という名前のファイルを作成し、次のコードを追加します。

    ```typescript
    export const customAuthConfig: CustomAuthConfiguration = {
        customAuth: {
            challengeTypes: ["password", "oob", "redirect"],
            authApiProxyUrl: "http://localhost:3001/api",
        },
        auth: {
            clientId: "Enter_the_Application_Id_Here",
            authority: "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com",
            redirectUri: "/",
            postLogoutRedirectUri: "/",
            navigateToLoginRequestUrl: false,
        },
        cache: {
            cacheLocation: "sessionStorage",
        },
        system: {
            loggerOptions: {
                loggerCallback: (level: LogLevel, message: string, containsPii: boolean) => {
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
            },
        },
    };
    ```

    コード内で、プレースホルダーを見つけてください:

    - `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` 次に、Microsoft Entra 管理センターのテナント サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
2. *src/app/services/auth.service.ts* という名前のファイルを作成し、次のコードを追加します。

    ```typescript
    import { Injectable } from '@angular/core';
    import { CustomAuthPublicClientApplication, ICustomAuthPublicClientApplication } from '@azure/msal-browser/custom-auth';
    import { customAuthConfig } from '../config/auth-config';
    
    @Injectable({ providedIn: 'root' })
    export class AuthService {
      private authClientPromise: Promise<ICustomAuthPublicClientApplication>;
      private authClient: ICustomAuthPublicClientApplication | null = null;
    
      constructor() {
        this.authClientPromise = this.init();
      }
    
      private async init(): Promise<ICustomAuthPublicClientApplication> {
        this.authClient = await CustomAuthPublicClientApplication.create(customAuthConfig);
        return this.authClient;
      }
    
      getClient(): Promise<ICustomAuthPublicClientApplication> {
        return this.authClientPromise;
      }
    }
    ```

### サインアップ コンポーネントを作成する

1. */app/components* という名前のディレクトリを作成します。
2. Angular CLI を使用して、次のコマンドを実行して、components フォルダー内のサインアップ ページの新しい *コンポーネント* を生成します。

    ```console
    cd components
    ng generate component sign-up
    ```
3. *サインアップ/sign-up.component.ts ファイルを*開き、その内容を [sign-up.component](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.ts) の内容に置き換えます
4. *サインアップ/sign-up.component.htmlファイルを*開き、[html ファイル](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.html)にコードを追加します。

    - *sign-up.component.ts* ファイル内の次のロジックは、サインアップ プロセスを開始した後にユーザーが次に実行する必要がある操作を決定します。 結果に応じて、ユーザーがサインアップ フローを続行できるように、パスワード フォームまたは確認コード フォームが *sign-up.component.html* に表示されます。

        ```typescript
           const attributes: UserAccountAttributes = {
                       givenName: this.firstName,
                       surname: this.lastName,
                       jobTitle: this.jobTitle,
                       city: this.city,
                       country: this.country,
                   };
           const result = await client.signUp({
                       username: this.email,
                       attributes,
                   });
        
           if (result.isPasswordRequired()) {
               this.showPassword = true;
               this.showCode = false;
           } else if (result.isCodeRequired()) {
               this.showPassword = false;
               this.showCode = true;
           }
        ```

        SDK のインスタンス メソッド `signUp()` 、サインアップ フローを開始します。
    - サインアップが完了した直後にユーザーがサインイン フローを開始する場合は、次のスニペットを使用します。

        ```html
        <div *ngIf="isSignedUp">
            <p>The user has been signed up, please click <a href="/sign-in">here</a> to sign in.</p>
        </div>
        ```
5. *src/app/app.component.scss ファイルを*開き、次の[スタイル ファイル](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/app.component.scss)を追加します。

### サインアップ時にユーザー名 (エイリアス) を収集する

ユーザーがメールに加えてユーザー名 (エイリアス) でサインアップできるようにすることができます。 ユーザー名 (エイリアス) は、顧客 ID、アカウント番号、または選択した別の値などの代替サインイン識別子です。

サインアップ時には、プライマリ識別子としてユーザー名 (電子メール) が常に必要であり、ユーザー名 (エイリアス) によって置き換えられることはありません。 既定では、ユーザー名 (エイリアス) は省略可能ですが、管理者は必要に応じて構成できます。 アプリは常にユーザー名 (電子メール) を収集し、エイリアスを電子メールと共に属性として収集します。 サインイン時に、ユーザーはユーザー名 (電子メール) またはユーザー名 (エイリアス) を使用してサインインできます。 **Username** 属性をオプションまたは必須として構成する方法については、「[ユーザー入力の種類とページ レイアウトを構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#configure-the-user-input-types-and-page-layout)」を参照してください。

サインアップ時にユーザー名 (エイリアス) を収集するには:

1. サインアップ ユーザー フローで **Username** 組み込みユーザー属性が有効になっていることを確認します。 手順については、「 [サインイン識別子ポリシーでユーザー名を有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)」を参照してください。
2. サインアップ コンポーネントに`flatUsername` フィールドを追加し、`flatusername`に渡す`UserAccountAttributes`に`signUp()`属性を含めます。

    ```typescript
    flatUsername = "";
    
    const attributes: UserAccountAttributes = {
        givenName: this.firstName,
         //...
        flatusername: this.flatUsername,
    };
    ```
3. 既存のフィールドと共に *sign-up.component.html* にエイリアス入力を追加します。

    ```html
    <input type="text" [(ngModel)]="flatUsername" name="flatUsername" placeholder="Username (alias)" />
    ```
4. ユーザー名 (エイリアス) に関連するエラーを処理します。

    - `result.error?.isUserAlreadyExists()` には、重複する電子メール *または* 重複するユーザー名 (エイリアス) が含まれます。 それに応じてメッセージを更新します。たとえば、 *このメールまたはユーザー名を持つアカウントが既に存在します*。
    - 無効なユーザー名 (エイリアス) は、`result.error?.isAttributesValidationFailed()`ではなく`result.error?.isInvalidUsername()`によって表示されます。 ユーザー名固有のメッセージを表示するには、このメソッドで分岐します。

### サインアップ後に自動的にサインインする (省略可能)

新しいサインイン フローを開始せずに、正常にサインアップした後にユーザーを自動的にサインインさせることができます。 これを行うには、次のコード スニペットを使用します。 [サインアップ/sign-up.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.ts)の完全な例を参照してください。

```typescript
    if (this.signUpState instanceof SignUpCompletedState) {
        const result = await this.signUpState.signIn();
    
        if (result.isFailed()) {
            this.error = result.error?.errorData?.errorDescription || "An error occurred during auto sign-in";
        }
    
        if (result.isCompleted()) {
            this.userData = result.data;
            this.signUpState = result.state;
            this.isSignedUp = true;
            this.showCode = false;
            this.showPassword = false;
        }
    }
```

ユーザーを自動署名する場合は、 [サインアップ/](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.html)sign-up.component.htmlhtml ファイルで次のスニペットを使用します。

```html
    <div *ngIf="userData && !isSignedIn">
        <p>Signed up complete, and signed in as {{ userData?.getAccount()?.username }}</p>
    </div>
    <div *ngIf="isSignedUp && !userData">
        <p>Sign up completed! Signing you in automatically...</p>
    </div>
```

### アプリのルーティングを更新する

1. *src/app/app.route.ts* ファイルを開き、サインアップ コンポーネントのルートを追加します。

    ```typescript
    import { NgModule } from '@angular/core';
    import { RouterModule, Routes } from '@angular/router';
    import { SignUpComponent } from './components/sign-up/sign-up.component';
    import { AuthService } from './services/auth.service';
    import { AppComponent } from './app.component';
    
    export const routes: Routes = [
        { path: 'sign-up', component: SignUpComponent },
    ];
    
    @NgModule({
        imports: [
            RouterModule.forRoot(routes),
            SignUpComponent,
        ],
        providers: [AuthService],
        bootstrap: [AppComponent]
    })
    export class AppRoutingModule { }
    ```

### サインアップ フローをテストする

1. CORS プロキシ サーバーを起動するには、ターミナルで次のコマンドを実行します。

    ```console
    npm run cors
    ```
2. アプリケーションを起動するには、ターミナルで次のコマンドを実行します。

    ```console
    npm start
    ```
3. Web ブラウザーを開き、`http://localhost:4200/sign-up`に移動します。 サインアップ フォームが表示されます。
4. アカウントにサインアップするには、詳細を入力し、[ **続行** ] ボタンを選択し、プロンプトに従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-angular-social-sign-in"} -->
## ネイティブ認証 JS SDK を使用した Angular SPA でのソーシャル サインインのサポート - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-social-sign-in
- Service: identity-platform / external
- Article date: 2026-04-10
- Summary: ネイティブ認証 JavaScript SDK を使用して、Apple、Facebook、Google、およびカスタム OIDC ID プロバイダーを使用して Angular SPA にソーシャル サインインを追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証の JavaScript SDK を外部テナントに使用して、ユーザーが既存のソーシャル アカウント (Apple、Facebook、Google、カスタム OIDC ソーシャル ID プロバイダーなど) を Angular シングルページ アプリケーション (SPA) でサインアップしてサインインできるようにする方法について説明します。

このチュートリアルでは、次の操作を行います。

- リダイレクト URI を設定するようにアプリ構成を更新します。
- フェデレーション ID プロバイダーのボタンを追加して、サインイン フォームとサインアップ フォームに追加します。
- フェデレーション ID プロバイダーとのサインインとサインアップを処理します。
- ソーシャル サインイン フローをテストします。

### 前提条件

- [サインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up)、[サインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-in)、[パスワードリセット](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-reset-password)、[強力な認証方法の登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-register-strong-method)、[MFA の有効化](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-enable-mfa)に関するチュートリアルの手順を完了します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコード エディター。
- [Node.jsバージョン20.x以降](https://nodejs.org/en/download/)
- 有効にするフェデレーション ID プロバイダーを構成します。 選択したプロバイダーの[IDプロバイダー 外部テナント用の](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)手順に従います。
    - [りんご](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)
    - [フェイスブック](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)
    - [グーグル](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)
    - [カスタム OIDC ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers)

### 構成を更新してリダイレクト URI を設定する

リダイレクト URI が  インターフェイスで構成されていること、およびその値が、Microsoft Entra 管理センターのアプリ登録で構成いずれかのリダイレクト URI と一致していることを確認します。

1. *src/app/config/auth-config.ts* ファイルを見つけます。
2. `auth` オブジェクトで、`redirectUri` プロパティを追加または更新し、その値が、Microsoft Entra 管理センターのアプリ登録で構成されているリダイレクト URI のいずれかと一致していることを確認します。

    ```typescript
    const customAuthConfig: CustomAuthConfiguration = {
        auth: {
            ...
            redirectUri: "/",
            ...
        },
        ...
    };
    ```

### UI コンポーネントを作成する

このセクションでは、フェデレーション ID プロバイダー ボタンをサインインフォームとサインアップ フォームに追加し、ユーザーがソーシャル ID プロバイダー (Apple、Facebook、Google) またはカスタム OIDC ID プロバイダー (LinkedIn など) で認証できるようにします。

#### サインイン フォームを更新する

フェデレーション ID プロバイダーボタンを含むように `sign-in.component.html` を更新します。 完全な例は、sign-in.component.html。

- `src/app/components/sign-in/sign-in.component.html`開き、ソーシャル プロバイダーボタンを最初のフォームに追加します。

    ```html
    <div class="auth-container">
        ...
        <form
            *ngIf="!showPassword && !showCode && !isSignedIn && !showAuthMethodsForRegistration && !showChallengeForRegistration && !showMfaAuthMethods && !showMfaChallenge"
            (ngSubmit)="startSignIn()">
            ...
    
            <div class="separator">
                <div class="separator-line"></div>
                <span class="separator-text">OR</span>
                <div class="separator-line"></div>
            </div>
    
            <button *ngFor="let provider of socialProviders" type="button" class="social-button"
                (click)="startSignInWithSocial(provider.domainHint)">
                <img [src]="provider.logo" [alt]="provider.name + ' logo'" class="provider-logo" />
                <span>Sign In with {{ provider.name }}</span>
            </button>
        </form>
        ...
    </div>
    ```

#### サインアップ フォームを更新する

同様に、 `sign-up.component.html` コンポーネントを更新します。 完全な例は、sign-up.component.html。

- `src/app/components/sign-up/sign-up.component.html`開き、通常のサインアップ ボタンの後にソーシャル プロバイダー ボタンを追加します。 ソーシャル プロバイダー ボタンを含めるには、サインイン フォームと同じ HTML ブロックを使用します。

### フォームの操作を処理する

このセクションでは、フェデレーション ID プロバイダーとのサインインとサインアップを処理するロジックを実装します。 実装では、MSAL の`loginPopup` メソッドと、`PopupRequest` プロパティを含む`domainHint`を使用します。 このプロパティは、使用するフェデレーション ID プロバイダーを指定します。 `domainHint`構成と発行者アクセラレーションの詳細については、「[外部 ID の ID プロバイダー」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)参照してください。

#### フェデレーション ID プロバイダーをサポートするようにサインアップ コンポーネントを更新する

フェデレーション ID プロバイダーによる認証を処理するように `sign-up.component.ts` を更新します。 完全な例については、[sign-up.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-up/sign-up.component.ts)を参照してください。

1. `sign-up.component.ts`で必要な型をインポートします。

    ```typescript
    import { customAuthConfig } from "../../config/auth-config";
    import { PopupRequest } from "@azure/msal-browser";
    ```
2. `sign-up.component.ts`に ID プロバイダーの一覧を追加します。

    ```typescript
    socialProviders = [
        { name: "Google", domainHint: "Google", logo: "/logos/google.svg" },
        { name: "Facebook", domainHint: "Facebook", logo: "/logos/facebook.svg" },
        { name: "Apple", domainHint: "Apple", logo: "/logos/apple.svg" },
        { name: "LinkedIn", domainHint: "www.linkedin.com", logo: "/logos/linkedin.svg" },
    ];
    ```
3. フェデレーション ID プロバイダーのサインアップのハンドラー関数を `sign-up.component.ts`に追加します。

    ```typescript
    async startSignUpWithSocial(domainHint: string) {
        this.error = "";
        this.loading = false;
    
        const popUpRequest: PopupRequest = {
            authority: customAuthConfig.auth.authority,
            scopes: [],
            redirectUri: customAuthConfig.auth.redirectUri || "",
            prompt: "login",
            domainHint: domainHint,
        };
    
        try {
            const client = await this.auth.getClient();
    
            await client.loginPopup(popUpRequest);
    
            const accountResult = client.getCurrentAccount();
    
            if (accountResult.isFailed()) {
                this.error =
                    accountResult.error?.errorData?.errorDescription ??
                    "An error occurred while getting the account from cache";
            }
    
            if (accountResult.isCompleted()) {
                this.userData = accountResult.data;
                this.isSignedIn = true;
            }
        } catch (error) {
            if (error instanceof Error) {
                this.error = error.message;
            } else {
                this.error = "An unexpected error occurred while logging in with popup";
            }
        }
    }
    ```

#### フェデレーション ID プロバイダーをサポートするようにサインイン コンポーネントを更新する

フェデレーション ID プロバイダーによる認証を処理するように `sign-in.component.ts` を更新します。 完全な例については、[sign-in.component.ts](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/angular-sample/src/app/components/sign-in/sign-in.component.ts)を参照してください。

1. `sign-in.component.ts`に ID プロバイダーの一覧を追加します。

    ```typescript
    socialProviders = [
        { name: "Google", domainHint: "Google", logo: "/logos/google.svg" },
        { name: "Facebook", domainHint: "Facebook", logo: "/logos/facebook.svg" },
        { name: "Apple", domainHint: "Apple", logo: "/logos/apple.svg" },
        { name: "LinkedIn", domainHint: "www.linkedin.com", logo: "/logos/linkedin.svg" },
    ];
    ```
2. `sign-in.component.ts` にフェデレーション ID プロバイダーサインイン用のハンドラー関数を追加します。

    ```typescript
    async startSignInWithSocial(domainHint: string) {
        this.error = "";
        this.loading = false;
    
        const popUpRequest: PopupRequest = {
            authority: customAuthConfig.auth.authority,
            scopes: [],
            redirectUri: customAuthConfig.auth.redirectUri || "",
            prompt: "login",
            domainHint: domainHint,
        };
    
        try {
            const client = await this.auth.getClient();
    
            await client.loginPopup(popUpRequest);
    
            const accountResult = client.getCurrentAccount();
    
            if (accountResult.isFailed()) {
                this.error =
                    accountResult.error?.errorData?.errorDescription ??
                    "An error occurred while getting the account from cache";
            }
    
            if (accountResult.isCompleted()) {
                this.userData = accountResult.data;
                this.isSignedIn = true;
            }
        } catch (error) {
            if (error instanceof Error) {
                this.error = error.message;
            } else {
                this.error = "An unexpected error occurred while logging in with popup";
            }
        }
    }
    ```

注

Microsoft Entra アカウントとMicrosoft アカウント (MSA) ID プロバイダーは現在サポートされていません。

#### PopupRequest 構成の詳細

フェデレーション ID プロバイダー認証の `PopupRequest` を構成する場合:

- **機関**: 構成済みの外部テナント機関を使用します。
- **redirectUri**: アプリ登録で構成したリダイレクト URI。
- **prompt**: `"login"` に設定して、ユーザーに資格情報の入力を強制します。
- **domainHint**: 使用するフェデレーション ID プロバイダーを決定するキー パラメーター。

`loginPopup`メソッドは、選択したフェデレーション ID プロバイダーを使用してユーザーが認証フローを完了するポップアップ ウィンドウを開きます。 認証が成功すると、ポップアップが自動的に閉じられ、アカウント情報がアプリで使用できるようになります。

### アプリを実行してテストする

アプリをテストする前に、CORS プロキシとアプリが実行されていることを確認します。

1. CORS プロキシが実行されていることを確認します。

    ```console
    npm run cors
    ```
2. アプリケーションを起動します。

    ```console
    npm run start
    ```

#### フェデレーション ID プロバイダーでのサインアップをテストする

1. `http://localhost:4200/sign-up`に移動して、サインアップ フォームを表示します。
2. 認証するフェデレーション ID プロバイダーのボタン ( **Google へのサインアップ**など) を選択します。 ポップアップ ウィンドウが開き、Google 認証ページにリダイレクトされます。
3. Google アカウントの資格情報でサインインします (または、必要に応じて新しい Google アカウントを作成します)。
4. プロンプトが表示されたら、必要なアクセス許可を付与します。

認証が成功した後、サインアップ時に追加のユーザー属性を収集するようにテナントが構成されている場合は、属性の収集を完了することが必要になる場合があります。 詳細については、「 [サインアップ時にユーザー属性を収集する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes)」を参照してください。

ポップアップ ウィンドウが自動的に閉じます。 サインインすると、アカウント情報がアプリに表示されます。 Google プロファイルの情報を使用して、外部テナントに新しいユーザー アカウントが作成されます。

#### フェデレーション ID プロバイダーを使用したサインインのテスト

1. `http://localhost:4200/sign-in`に移動して、サインイン フォームを表示します。
2. 認証に使用するフェデレーション ID プロバイダーのボタン ( **Google でのサインイン**など) を選択します。 ポップアップ ウィンドウが開き、Google 認証ページにリダイレクトされます。
3. Google アカウントの資格情報でサインインします。 この ID プロバイダーで初めてサインインする場合は、アプリケーションとの情報の共有に同意するように求められる場合があります。

認証が成功した後、サインアップ時に追加のユーザー属性を収集するようにテナントが構成されている場合は、属性の収集を完了することが必要になる場合があります。 詳細については、「 [サインアップ時にユーザー属性を収集する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes)」を参照してください。

ポップアップ ウィンドウが自動的に閉じます。 これでサインインし、アプリにアカウント情報が表示されます。

#### 多要素認証

SMS または電子メール ワンタイム パスコード (OTP) MFA が有効になっている場合、ソーシャル ID プロバイダーの認証が完了した後、ブラウザーで委任されたユーザー エクスペリエンスに MFA チャレンジが提示されます。

MFA の有効化の詳細については、「 [外部テナントでの多要素認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers) 」および「 [アプリへの多要素認証の追加」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)参照してください。

### 一般的なエラーのトラブルシューティング

このセクションを使用して、フェデレーション ID プロバイダーを統合するときに発生する可能性がある一般的な問題を解決します。

#### ブラウザーによってポップアップ ウィンドウがブロックされる

`loginPopup`メソッドにはブラウザー ポップアップが必要です。 ポップアップがブロックされている場合、認証フローは通知されずに失敗するか、エラーが発生します。

**解決策**: ブラウザーのポップアップ ブロック設定を確認し、アプリケーションのドメインからのポップアップを許可します。 ユーザーに同じ操作を行うよう指示します。 ほとんどのブラウザーでは、ポップアップがブロックされると、アドレス バーに通知が表示されます。

#### ドメイン ヒントが認識されない

フェデレーション ID プロバイダーの認証ページが表示されないか、 `domainHint` 値が無効であることを示すエラーが表示されます。

**解決策**: `domainHint`の`PopupRequest`値が、外部テナントで構成したものと正確に一致することを確認します。 次の値を使用します。

| プロバイダー | 予期される`domainHint`値 |
| --- | --- |
| 林檎 | `"Apple"` |
| フェイスブック | `"Facebook"` |
| Google | `"Google"` |
| カスタム OIDC (例: LinkedIn) | 構成した発行者 URI (例: `"www.linkedin.com"` |

#### ポップアップが開いた後に認証が失敗する

ポップアップが開き、ID プロバイダーにリダイレクトされますが、認証は完了しません。 ブラウザー コンソールでエラー メッセージを確認します。

**解決策**: 次の構成を確認します。

1. `redirectUri` の`PopupRequest`は、Microsoft Entra 管理センターのアプリ登録に登録されているリダイレクト URI のいずれかに一致します。
2. アプリ構成のクライアント ID が正しい。
3. フェデレーション ID プロバイダーは、外部テナントで適切に構成され、関連するユーザー フローに追加されます。

#### サインアップ後にユーザー アカウントが作成されない

ユーザーはフェデレーション ID プロバイダー認証を完了しますが、テナントに新しいアカウントは作成されません。

**解決策**: 外部テナントでのサインアップとサインインの両方のシナリオで、フェデレーション ID プロバイダーがユーザー フローで構成され、有効になっていることを確認します。 ユーザーが初めてフェデレーション ID プロバイダーでサインインすると、新しいアカウントが自動的に作成され、そのプロバイダーにリンクされます。 同じプロバイダーを使用する後続のサインインでは、既存のアカウントが使用されます。

#### CORS 関連のエラーがコンソールに表示される

ブラウザー コンソールに `Access-Control-Allow-Origin` または同様の CORS エラーが表示されます。

**解決策**: CORS プロキシが正しく実行されていることを確認します。 `npm run cors`でプロキシを再起動し、再試行する前にアクセス可能かどうかを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-javascript-custom-headers"} -->
## JavaScript SPA のネイティブ認証要求にカスタム ヘッダーを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-javascript-custom-headers
- Service: identity-platform / external
- Article date: 2026-05-20
- Summary: React または Angular SPA のネイティブ認証要求にカスタム x-* ヘッダーをアタッチして、不正検出 SDK をMicrosoft Entra 外部 IDと統合する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、React または Angular シングルページ アプリ (SPA) でネイティブ認証ネットワーク要求にカスタム `x-*` ヘッダーを追加する方法について説明します。 ネイティブ認証 JavaScript SDK の `CustomAuthRequestInterceptor` を使用して、サードパーティの不正行為およびボット検出プロバイダーと統合します。

このチュートリアルでは、次の操作を行います。

- SDK で適用されるヘッダーの名前付け規則について説明します。
- インターセプターをアプリ構成に登録します。
- ヘッダーが目的のエンドポイントに到達することを確認します。

### 前提条件

ネイティブ認証 JavaScript SDK を使用する React または Angular SPA。 お持ちでない場合は、最初に次のいずれかのチュートリアルを完了してください。

- [ネイティブ認証 JavaScript SDK を使用して React シングルページ アプリにユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-in)
- [ネイティブ認証を使用して Angular シングルページ アプリでユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-in)

### ヘッダーの名前付けルールについて

Microsoft Authentication Library (MSAL) は、指定したヘッダーを評価するときに次の規則を適用します。 インターセプターを実装する前に、ベンダーが必要とするヘッダー名を確認するには、次の規則を使用します。

- ヘッダーはで始まる`x-` (大文字と小文字は区別されません)。 MSAL は、 `x-`で始まらないヘッダーを無視します。
- MSAL は、次のいずれかの予約済みプレフィックスで始まるヘッダーを無視します。これは、SDK によって所有されているためです。
    - `x-client-`
    - `x-ms-`
    - `x-broker-`
    - `x-app-`

MSAL は、両方の規則をネットワーク要求に渡すヘッダーを追加します。 指定したヘッダー名が SDK によって設定されるヘッダー名と競合する場合は、値が優先されます。

### 要求インターセプターを実装する

`CustomAuthRequestInterceptor` インターフェイスは、SDK が各ネイティブ認証ネットワーク要求を送信する前に呼び出す 1 つのメソッド (`addAdditionalHeaderFields`) を宣言します。 実装は要求 URL を受け取り、追加するヘッダーのディクショナリを返します。その要求にヘッダーが必要ない場合は `null` します。

1. 次のようなアプリ構成ファイルを開きます。

    - `src/app/config/auth-config.ts` Angular の場合。
    - `src/config/auth-config.ts` React の場合。
2. 既存の `customAuthConfig` 定義の上に次のインターセプターを追加します。

```typescript
import { CustomAuthConfiguration, LogLevel } from "@azure/msal-browser/custom-auth";
import type { CustomAuthRequestInterceptor } from "@azure/msal-browser/custom-auth";

const requestInterceptor: CustomAuthRequestInterceptor = {
    addAdditionalHeaderFields(requestUrl: URL) {
        // Scope headers to specific endpoints only.
        if (requestUrl.pathname.endsWith("/oauth2/v2.0/initiate")) {
            return {
                value_1: "customer_header_1",            // Ignored: doesn't start with "x-".
                "x-client-header": "customer_header_2",  // Ignored: starts with reserved prefix "x-client-".
                "X-my-custom-header": "my data",         // Added to the network request.
            };
        }

        // Return null for all other requests to avoid over-sending signals.
        return null;
    },
};
```

`addAdditionalHeaderFields` メソッドは、`requestUrl`で送信要求の完全な URL を受け取ります。 URL を使用して、ヘッダーのスコープを、サインインやサインアップの開始エンドポイントなど、不正行為やボット検出ベンダーが必要とする特定のエンドポイントに限定します。 関連のないエンドポイントにヘッダーを送信すると、信号品質が低下し、誤検知が増加する可能性があります。

Note

MSAL は、インターセプターが戻った *後* に名前付け規則を評価します。 `addAdditionalHeaderFields`内からのログ記録には、MSAL が送信する最終的なセットではなく、指定したヘッダーのみが表示されます。 実際のネットワーク要求を調べて、最終的なヘッダーを確認します。

### インターセプターを登録する

インターセプターを実装したら、`CustomAuthConfiguration` の `customAuth` ブロックにある `requestInterceptor` プロパティにそれを割り当てます。

```typescript
export const customAuthConfig: CustomAuthConfiguration = {
    customAuth: {
        challengeTypes: ["password", "oob", "redirect"],
        authApiProxyUrl: "http://localhost:3001/api",
        requestInterceptor: requestInterceptor,
    },
    auth: {
        clientId: "Enter_the_Application_Id_Here",
        // ...
    },
};
```

構成は React と Angular の両方で同じです。

### ヘッダーが適用されていることを確認する

ヘッダーが目的のエンドポイントに到達することを確認するには、ブラウザーの開発者ツール ([ネットワーク] タブ) または Fiddler などのネットワーク プロキシ ツールを使用して、送信ネットワーク トラフィックを検査します。 次の内容を確認する:

- `x-`で始まり、予約済みのプレフィックスを使用しないヘッダーは、要求に表示されます。
- 予約済みプレフィックス (`x-client-`、 `x-ms-`、 `x-broker-`、 `x-app-`) を使用するヘッダーは要求に表示されません。
- ヘッダーは、インターセプターでターゲットにしたエンドポイントにのみ表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-javascript-sdk-web-fallback"} -->
## ネイティブ認証 JavaScript SDK での Web フォールバックのサポート - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-javascript-sdk-web-fallback
- Service: identity-platform / external
- Article date: 2025-06-30
- Summary: ネイティブ認証 JavaScript SDK で Web フォールバックを処理する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、 *Web フォールバック*と呼ばれるメカニズムを使用して、ネイティブ認証では認証フローを完了するのに十分ではないブラウザー ベースの認証を使用してセキュリティ トークンを取得する方法について説明します。

Web フォールバックを使用すると、ネイティブ認証を使用するクライアント アプリで、回復性を向上させるためのフォールバック メカニズムとしてブラウザー委任認証を使用できます。 このシナリオは、認証フローを完了するのにネイティブ認証だけでは十分ではない場合に発生します。 たとえば、承認サーバーにクライアントが提供できない機能が必要な場合です。 [Web フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback)の詳細を確認します。

このチュートリアルでは、次の操作を行います。

- エラー `isRedirectRequired` 確認します。
- `isRedirectRequired`エラーを処理します。

### [前提条件]

## [React](#tab/react)
- [「チュートリアル: ネイティブ認証 JavaScript SDK を使用して React シングルページ アプリにユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-in)する」の手順を完了します。

## [角度](#tab/angular)
- [「チュートリアル: ネイティブ認証 JavaScript SDK を使用して Angular シングルページ アプリにユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-in)する」の手順を完了します。

---

### Web フォールバックの確認と処理

JavaScript SDK の `signIn()` または `SignUp()` メソッドを使用するときに発生する可能性があるエラーの 1 つが `result.error?.isRedirectRequired()`。 ユーティリティメソッド `isRedirectRequired()` は、ブラウザーによって委任された認証にフォールバックする必要性をチェックします。 Web フォールバックをサポートするには、次のコード スニペットを使用します。

```typescript
const result = await authClient.signIn({
         username,
     });

if (result.isFailed()) {
   if (result.error?.isRedirectRequired()) {
      // Fallback to the delegated authentication flow.
      const popUpRequest: PopupRequest = {
         authority: customAuthConfig.auth.authority,
         scopes: [],
         redirectUri: customAuthConfig.auth.redirectUri || "",
         prompt: "login", // Forces the user to enter their credentials on that request, negating single-sign on.
      };

      try {
         await authClient.loginPopup(popUpRequest);

         const accountResult = authClient.getCurrentAccount();

         if (accountResult.isFailed()) {
            setError(
                  accountResult.error?.errorData?.errorDescription ??
                     "An error occurred while getting the account from cache"
            );
         }

         if (accountResult.isCompleted()) {
            result.state = new SignInCompletedState();
            result.data = accountResult.data;
         }
      } catch (error) {
         if (error instanceof Error) {
            setError(error.message);
         } else {
            setError("An unexpected error occurred while logging in with popup");
         }
      }
   } else {
         setError(`An error occurred: ${result.error?.errorData?.errorDescription}`);
   }
}
```

アプリがフォールバック メカニズムを使用する場合、アプリは `loginPopup()` メソッドを使用してセキュリティ トークンを取得します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-enable-mfa"} -->
## ネイティブ認証 JavaScript SDK を使用して React SPA で MFA を有効にする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-enable-mfa
- Service: identity-platform / external
- Article date: 2025-11-18
- Summary: ネイティブ認証 JavaScript SDK を使用する React シングルページ アプリケーションで多要素認証を有効にする方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証の JavaScript SDK を使用して、React シングルページ アプリケーション (SPA) に多要素認証 (MFA) を追加する方法について説明します。

[強力な認証方法の登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-register-strong-method)と同様に、MFA フローは次の 3 つのシナリオで発生します。

- **サインイン中**: ユーザーはサインインし、強力な認証方法が登録されています。
- **サインアップ後**: ユーザーがサインアップを完了したら、サインインに進みます。 新しいユーザーは、MFA チャレンジ [の前に強力な認証方法を登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-register-strong-method) する必要があります。 強力な認証方法も登録中に検証されるため、追加の MFA チャレンジを求められない場合があります。
- **セルフサービス パスワード リセット (SSPR) 後**: ユーザーはパスワードを正常にリセットし、自動的にサインインに進みます。 ユーザーに強力な認証方法が登録されている場合は、MFA チャレンジを完了するように求められます。

MFA が必要な場合、ユーザーは登録済みの方法の一覧から MFA チャレンジ方法を選択します。 使用できるオプションは、ユーザーが以前に登録した内容に応じて、 **電子メール** ワンタイム パスコード、 **SMS** ワンタイム パスコード、またはその両方です。

次のフロー図は、次の 3 つのシナリオを示しています。

[Image: 多要素認証チャレンジを完了します。]

### [前提条件]

- [サインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up)、[サインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-in)、[パスワードリセット](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-reset-password)、[および強力な認証方法の登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-register-strong-method)に関するチュートリアルの手順を完了します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコード エディター。
- [Node.js](https://nodejs.org/en/download/).
- [アプリの多要素認証 (MFA) を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)。

### アプリで多要素認証を処理できるようにする

React アプリで MFA を有効にするには、必要な機能を追加してアプリの構成を更新します。

1. *src/config/auth-config.ts* ファイルを見つけます。
2. `customAuth` オブジェクトで、次のコード スニペットに示すように、`capabilities`プロパティを追加または更新して、配列に`mfa_required`値を含めます。

    ```typescript
    const customAuthConfig: CustomAuthConfiguration = {
        customAuth: {
            ...
            capabilities: ["mfa_required"],
            ...
        },
        ...
    };
    ```

機能の値 `mfa_required` は、アプリが MFA フローを処理できることをMicrosoft Entraに通知します。 [ネイティブ認証チャレンジの種類と機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)の詳細について説明します。

### UI コンポーネントを作成する

MFA フローをサポートするには、アプリのフォーム コンポーネントが必要です。 これらのコンポーネントをアプリに追加するには、次の手順に従います。

1. 再利用可能なコンポーネント用の新しいフォルダー *src/app/shared/components* を作成します (まだ存在しない場合)。
2. 新しいフォルダーで、 *MfaAuthMethodSelectionForm.tsx* という名前のファイルを作成して、ユーザーが登録済みの強力な認証方法を選択できるフォームを表示します。 [MfaAuthMethodSelectionForm](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/MfaAuthMethodSelectionForm.tsx) 内のコードをファイルに追加します。
3. 新しいフォルダーで、 *MfaChallengeForm.tsx* という名前の別のファイルを作成し、ユーザーが受け取るワンタイム パスコードを使用して強力な認証方法を確認するためのフォームを表示します。 [MfaChallengeForm](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/MfaChallengeForm.tsx) のコードをファイルに追加します。

必要に応じて、サインインに再利用可能なコンポーネントをインポートして使用したり、サインアップ後にサインインしたり、SSPR フロー後にサインインしたりすることができます。

### サインイン時に多要素認証を処理する

サインイン中にアプリが MFA フローを処理できるように *、src/app/sign-in/page.tsx* ファイルを更新します。 [page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-in/page.tsx)の完全なコードを参照してください。

1. 次のコード スニペットに示すように、必要な型とコンポーネントをインポートします。

    ```typescript
    import {
        CustomAuthPublicClientApplication,
        ICustomAuthPublicClientApplication,
        SignInCodeRequiredState,
        SignInCompletedState,
        AuthFlowStateBase,
        MfaAwaitingState,
        MfaVerificationRequiredState,
    } from "@azure/msal-browser/custom-auth";
    import { MfaAuthMethodSelectionForm } from "../components/shared/MfaAuthMethodSelectionForm";
    import { MfaChallengeForm } from "../components/shared/MfaChallengeForm";
    ```
2. MFA の新しい状態変数を追加します。

    ```typescript
    export default function SignIn() {
        ...
        // MFA states
    const [mfaAuthMethods, setMfaAuthMethods] = useState<AuthenticationMethod[]>([]);
    const [selectedMfaAuthMethod, setSelectedMfaAuthMethod] = useState<AuthenticationMethod | undefined>(undefined);
    const [mfaChallenge, setMfaChallenge] = useState("");
    
        // ... initialization code
    }
    ```
3. `startSignIn`、`handlePasswordSubmit`、および`handleCodeSubmit`関数を更新して、MFA が必要かどうかを確認します。

    ```typescript
    const startSignIn = async (e: React.FormEvent) => {
        // Start the sign-in flow
        const result = await authClient.signIn({
            username,
        });
    
        ...
    
        if (result.isMfaRequired()) {
            const methods = result.state.getAuthMethods();
            setMfaAuthMethods(methods);
            setSelectedMfaAuthMethod(methods.length > 0 ? methods[0] : undefined);
        }
    
        ...
    };
    
    const handlePasswordSubmit = async (e: React.FormEvent) => {
        if (signInState instanceof SignInPasswordRequiredState) {
            const result = await signInState.submitPassword(password);
    
            ...
    
            // Check for MFA requirement
            if (result.isMfaRequired()) {
                const methods = result.state.getAuthMethods();
                setMfaAuthMethods(methods);
                setSelectedMfaAuthMethod(methods.length > 0 ? methods[0] : undefined);
                setSignInState(result.state);
            }
    
            ...
        }
    };
    
    const handleCodeSubmit = async (e: React.FormEvent) => {
        if (signInState instanceof SignInCodeRequiredState) {
            const result = await signInState.submitCode(code);
    
            ...
    
            // Check for MFA requirement
            if (result.isMfaRequired()) {
                const methods = result.state.getAuthMethods();
                setMfaAuthMethods(methods);
                setSelectedMfaAuthMethod(methods.length > 0 ? methods[0] : undefined);
                setSignInState(result.state);
            }
    
            ...
        }
    };
    ```

    各関数で、次のコード スニペットを使用して MFA が必要かどうかを確認します。

    ```typescript
    if (result.isMfaRequired()) {...}
    ```
4. MFA チャレンジ選択のハンドラーを追加します。

    ```typescript
    const handleMfaAuthMethodSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        if (!selectedMfaAuthMethod) {
            setError("Please select an authentication method.");
            setLoading(false);
            return;
        }
    
        if (signInState instanceof MfaAwaitingState) {
            const result = await signInState.requestChallenge(selectedMfaAuthMethod.id);
    
            if (result.isFailed()) {
                if (result.error?.isInvalidInput()) {
                    setError("Incorrect verification contact.");
                } else {
                    setError(
                        result.error?.errorData?.errorDescription ||
                            "An error occurred while verifying the authentication method"
                    );
                }
            }
    
            if (result.isVerificationRequired()) {
                setSignInState(result.state);
            }
        }
    
        setLoading(false);
    };
    ```
5. MFA チャレンジ検証のハンドラーを追加します。

    ```typescript
    const handleMfaChallengeSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        if (!mfaChallenge) {
            setError("Please enter a code.");
            setLoading(false);
            return;
        }
    
        if (signInState instanceof MfaVerificationRequiredState) {
            const result = await signInState.submitChallenge(mfaChallenge);
    
            if (result.isFailed()) {
                if (result.error?.isIncorrectChallenge()) {
                    setError("Incorrect code.");
                } else {
                    setError(
                        result.error?.errorData?.errorDescription ||
                            "An error occurred while verifying the challenge response"
                    );
                }
            }
    
            if (result.isCompleted()) {
                setData(result.data);
                setCurrentSignInStatus(true);
                setSignInState(result.state);
            }
        }
    
        setLoading(false);
    };
    ```
6. `renderForm()`関数を更新して、正しい MFA チャレンジ フォーム (MFA チャレンジ方法の選択または MFA チャレンジ方法の検証) を表示します。

    ```typescript
    const renderForm = () => {
        if (loadingAccountStatus) {
            return;
        }
    
        ...
    
        // Display MfaAuthMethodSelectionForm if the current state is MFA awaiting state
        if (signInState instanceof MfaAwaitingState) {
            return (
                <MfaAuthMethodSelectionForm
                    onSubmit={handleMfaAuthMethodSubmit}
                    authMethods={mfaAuthMethods}
                    selectedAuthMethod={selectedMfaAuthMethod}
                    setSelectedAuthMethod={setSelectedMfaAuthMethod}
                    loading={loading}
                    styles={styles}
                />
            );
        }
    
        // Display MfaChallengeForm if the current state is MFA verification required state
        if (signInState instanceof MfaVerificationRequiredState) {
            return (
                <MfaChallengeForm
                    onSubmit={handleMfaChallengeSubmit}
                    challenge={mfaChallenge}
                    setChallenge={setMfaChallenge}
                    loading={loading}
                    styles={styles}
                />
            );
        }
    
        ...
    };
    ```

### サインアップまたはパスワードのリセット後に多要素認証を処理する

サインアップとパスワードのリセット後の MFA フローは、サインイン フローの MFA と同様に機能します。 サインアップまたはパスワードのリセットが成功すると、SDK は自動的にサインイン フローを続行できます。 ユーザーに強力な認証方法が登録されている場合、フローは MFA チャレンジ検証に移行します。

#### サインアップ後の多要素認証の処理

サインアップ後の MFA フローの場合は、 */src/app/sign-up/page.tsx ファイルを更新する* 必要があります。 [page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートしてください。
2. サインイン フローで発生するのと同様の方法で MFA 要件の状態を処理します。サインアップが正常に完了したら、次のコード スニペットに示すように、結果を使用して自動的にサインイン フローをトリガーします。

    ```typescript
    // In your sign-up completion handler
    if (signUpState instanceof SignUpCompletedState) {
        // Continue with sign-in using the continuation token
        const result = await signUpState.signIn();
    
        ...
    
        if (result.isMfaRequired()) {
            const methods = result.state.getAuthMethods();
            setMfaAuthMethods(methods);
            setSelectedMfaAuthMethod(methods.length > 0 ? methods[0] : undefined);
            setSignUpState(state);
        }
    
        ...
    }
    
    // Then use the same renderForm logic to display MfaAuthMethodSelectionForm
    // and MfaChallengeForm components
    ```

#### パスワードリセット後の多要素認証の処理

SSPR 後の MFA フローの場合は、 */src/app/reset-password/page.tsx ファイルを更新する* 必要があります。 [page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/page.tsx)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートしてください。
2. サインイン フローと同様の方法で MFA 要件の状態を処理します。 SSPRS が正常に完了したら、次のコード スニペットに示すように、結果を使用して自動的にサインインをトリガーできます。

    ```typescript
    // In your password reset completion handler
    if (resetPasswordState instanceof ResetPasswordCompletedState) {
        // Continue with sign-in using the continuation token
        const result = await signUpState.signIn();
    
        ...
    
        if (result.isMfaRequired()) {
            const methods = result.state.getAuthMethods();
            setMfaAuthMethods(methods);
            setSelectedMfaAuthMethod(methods.length > 0 ? methods[0] : undefined);
            setResetState(state);
        }
    
        ...
    }
    
    // Then use the same renderForm logic to display MfaAuthMethodSelectionForm
    // and MfaChallengeForm components
    ```

### アプリを実行してテストする

アプリをテストする前に、強力な認証方法が登録されているユーザー アカウントがあることを確認します。 [アプリの実行とテスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-register-strong-method#run-and-test-your-app)の手順を参照してアプリを実行しますが、今回は MFA フローのテストに重点を置きます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-register-strong-method"} -->
## ネイティブ認証 JavaScript SDK を使用して React SPA に強力な認証方法を登録する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-register-strong-method
- Service: identity-platform / external
- Article date: 2026-01-18
- Summary: ネイティブ認証 JavaScript SDK を使用する React シングルページ アプリケーションに強力な認証方法を登録する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証の JavaScript SDK を使用して、React シングルページ アプリケーション (SPA) にユーザーの強力な認証方法を登録します。

多要素認証 (MFA) を有効にしても、ユーザーに強力な認証方法が登録されていない場合は、トークンを発行する前にユーザーに登録する必要があります。

強力な認証方法の登録フローは、次の 3 つのシナリオで発生します。

- **サインイン中**: ユーザーはサインインしますが、強力な認証方法が登録されていません。
- **サインアップ後**: ユーザーは正常にサインアップし、自動的にサインインに進みます。
- **セルフサービス パスワード リセット (SSPR) 後**: ユーザーはパスワードを正常にリセットし、自動的にサインインに進みます。

強力な認証方法の登録が必要な場合、ユーザーは、サポートされている方法の一覧から選択する方法を選択します。 使用できる方法は、 **電子メール** と **SMS** ワンタイム パスコードです。

次のフロー図は、次の 3 つのシナリオを示しています。

[Image: 強力な認証方法を登録します。]

### [前提条件]

- [サインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up)、サインイン、[パスワードリセット](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-in)のチュートリアル[の](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-reset-password)手順を完了します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコード エディター。
- [Node.jsバージョン20.x以降](https://nodejs.org/en/download/)
- [アプリの多要素認証 (MFA) を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)。

### アプリで強力な認証方法の登録を処理できるようにする

React アプリで強力な認証方法の登録を有効にするには、必要な機能を追加してアプリ構成を更新します。

1. *src/config/auth-config.ts* ファイルを見つけます。
2. `customAuth` オブジェクトで、次のコード スニペットに示すように、`capabilities` プロパティを追加または更新して、配列に`registration_required`値を含めます。

    ```typescript
    const customAuthConfig: CustomAuthConfiguration = {
        customAuth: {
            ...
            capabilities: ["registration_required"],
            ...
        },
        ...
    };
    ```

機能の値 `registration_required` は、アプリが強力な認証方法の登録フローを処理できることをMicrosoft Entraに通知します。 [ネイティブ認証チャレンジの種類と機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)の詳細について説明します。

### UI コンポーネントを作成する

強力な認証方法の登録を処理するには、フォーム コンポーネントが必要です。 これらのコンポーネントをアプリに追加するには、次の手順に従います。

1. 再利用可能なコンポーネントを格納するための *src/app/shared/components* という名前の新しいフォルダーを作成します。
2. 新しいフォルダーで、 `AuthMethodRegistrationForm.tsx` という名前のファイルを作成して、ユーザーが強力な認証方法を選択して登録できるフォームを表示します。 [AuthMethodRegistrationForm](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/AuthMethodRegistrationForm.tsx) のコードをファイルに追加します。
3. 新しいフォルダーで、 `AuthMethodRegistrationChallengeForm.tsx` という名前の別のファイルを作成し、ユーザーが受け取るワンタイム パスコードを使用して強力な認証方法を確認するためのフォームを表示します。 [AuthMethodRegistrationChallengeForm](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/AuthMethodRegistrationChallengeForm.tsx) のコードをファイルに追加します。

必要に応じて、サインインに再利用可能なコンポーネントをインポートして使用したり、サインアップ後にサインインしたり、SSPR フロー後にサインインしたりすることができます。

### サインイン時に強力な認証方法を登録する

*src/app/sign-in/page.tsx* ファイルを更新して、サインイン時にアプリが強力な認証方法の登録フローを処理できるようにします。 [page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-in/page.tsx)の完全なコードを参照してください。

1. 次のコード スニペットに示すように、必要な型とコンポーネントをインポートします。

    ```typescript
    import {
        CustomAuthPublicClientApplication,
        ICustomAuthPublicClientApplication,
        SignInCodeRequiredState,
        SignInCompletedState,
        AuthFlowStateBase,
        SignInAuthMethodRegistrationRequiredState,
        SignInAuthMethodRegistrationChallengeRequiredState,
    } from "@azure/msal-browser/custom-auth";
    import { AuthMethodRegistrationForm } from "../components/shared/AuthMethodRegistrationForm";
    import { AuthMethodRegistrationChallengeForm } from "../components/shared/AuthMethodRegistrationChallengeForm";
    ...
    ```
2. 強力な認証方法の登録用に新しい状態変数を追加します。

    ```typescript
    export default function SignIn() {
        ...
        // Authentication method registration states
        const [authMethodsForRegistration, setAuthMethodsForRegistration] = useState<AuthenticationMethod[]>([]);
        const [selectedAuthMethodForRegistration, setSelectedAuthMethodForRegistration] = useState<AuthenticationMethod | undefined>(undefined);
        const [verificationContactForRegistration, setVerificationContactForRegistration] = useState("");
        const [challengeForRegistration, setChallengeForRegistration] = useState("");
    
        // ... initialization code
    }
    ```
3. `startSignIn`、`handlePasswordSubmit`、および`handleCodeSubmit`関数を更新して、強力な認証方法の登録が必要かどうかを確認します。

    ```typescript
    const startSignIn = async (e: React.FormEvent) => {
        // Start the sign-in flow
        const result = await authClient.signIn({
            username,
        });
    
        ...
    
        // Check for auth method registration requirement
        if (result.isAuthMethodRegistrationRequired()) {
            setAuthMethodsForRegistration(result.state.getAuthMethods());
            // Set default selection to the first auth method
            const methods = result.state.getAuthMethods();
            setSelectedAuthMethodForRegistration(methods.length > 0 ? methods[0] : undefined);
        }
    
        ...
    };
    
    const handlePasswordSubmit = async (e: React.FormEvent) => {
        if (signInState instanceof SignInPasswordRequiredState) {
            const result = await signInState.submitPassword(password);
    
            ...
    
            // Check for auth method registration requirement
            if (result.isAuthMethodRegistrationRequired()) {
                const methods = result.state.getAuthMethods();
                setAuthMethodsForRegistration(methods);
                setSelectedAuthMethodForRegistration(methods.length > 0 ? methods[0] : undefined);
                setSignInState(result.state);
            }
    
            ...
        }
    };
    
    const handleCodeSubmit = async (e: React.FormEvent) => {
        if (signInState instanceof SignInCodeRequiredState) {
            const result = await signInState.submitCode(code);
    
            ...
    
            // Check for auth method registration requirement
            if (result.isAuthMethodRegistrationRequired()) {
                const methods = result.state.getAuthMethods();
                setAuthMethodsForRegistration(methods);
                setSelectedAuthMethodForRegistration(methods.length > 0 ? methods[0] : undefined);
                setSignInState(result.state);
            }
    
            ...
        }
    };
    ```

    各関数では、次のコード スニペットを使用して、強力な認証方法の登録が必要かどうかを確認します。

    ```typescript
    if (result.isAuthMethodRegistrationRequired()) {...}
    ```
4. 強力な認証方法を選択するためのハンドラーを追加します。

    ```typescript
    const handleAuthMethodRegistrationSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        if (!selectedAuthMethodForRegistration || !verificationContactForRegistration) {
            setError("Please select an authentication method and enter a verification contact.");
            setLoading(false);
            return;
        }
    
        if (signInState instanceof AuthMethodRegistrationRequiredState) {
            const result = await signInState.challengeAuthMethod({
                authMethodType: selectedAuthMethodForRegistration,
                verificationContact: verificationContactForRegistration,
            });
    
            if (result.isFailed()) {
                if (result.error?.isInvalidInput()) {
                    setError("Incorrect verification contact.");
                } else if (result.error?.isVerificationContactBlocked()) {
                    setError(
                        "The verification contact is blocked. Consider using a different contact or a different authentication method"
                    );
                } else {
                    setError(
                        result.error?.errorData?.errorDescription ||
                            "An error occurred while verifying the authentication method"
                    );
                }
            }
    
            if (result.isCompleted()) {
                setData(result.data);
                setCurrentSignInStatus(true);
                setSignInState(result.state);
            }
    
            if (result.isVerificationRequired()) {
                setSignInState(result.state);
            }
        }
    
        setLoading(false);
    };
    ```
5. 強力な認証方法の検証用のハンドラーを追加します。

    ```typescript
    const handleAuthMethodRegistrationChallengeSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        if (!challengeForRegistration) {
            setError("Please enter a code.");
            setLoading(false);
            return;
        }
    
        if (signInState instanceof AuthMethodVerificationRequiredState) {
            const result = await signInState.submitChallenge(challengeForRegistration);
    
            if (result.isFailed()) {
                if (result.error?.isIncorrectChallenge()) {
                    setError("Incorrect code.");
                } else {
                    setError(
                        result.error?.errorData?.errorDescription ||
                            "An error occurred while verifying the challenge response"
                    );
                }
            }
    
            if (result.isCompleted()) {
                setData(result.data);
                setCurrentSignInStatus(true);
                setSignInState(result.state);
            }
        }
    
        setLoading(false);
    };
    ```
6. `renderForm()`関数を更新して、正しい強力な認証方法の登録フォーム (メソッドの選択または検証) を表示します。

    ```typescript
    const renderForm = () => {
        if (loadingAccountStatus) {
            return;
        }
    
        ...
    
        // Display AuthMethodRegistrationForm if the current state is authentication method registration required state
        if (signInState instanceof AuthMethodRegistrationRequiredState) {
            return (
                <AuthMethodRegistrationForm
                    onSubmit={handleAuthMethodRegistrationSubmit}
                    authMethods={authMethodsForRegistration}
                    selectedAuthMethod={selectedAuthMethodForRegistration}
                    setSelectedAuthMethod={setSelectedAuthMethodForRegistration}
                    verificationContact={verificationContactForRegistration}
                    setVerificationContact={setVerificationContactForRegistration}
                    loading={loading}
                    styles={styles}
                />
            );
        }
    
        // Display AuthMethodRegistrationChallengeForm if the current state is authentication method verification required state
        if (signInState instanceof AuthMethodVerificationRequiredState) {
            return (
                <AuthMethodRegistrationChallengeForm
                    onSubmit={handleAuthMethodRegistrationChallengeSubmit}
                    challenge={challengeForRegistration}
                    setChallenge={setChallengeForRegistration}
                    loading={loading}
                    styles={styles}
                />
            );
        }
    
        ...
    };
    ```

### サインアップまたはパスワードのリセット後に強力な認証方法を登録する

サインアップとパスワードリセット後の強力な認証方法の登録フローは、サインインフロー中の方法の登録と同様に機能します。 サインアップまたはパスワードのリセットが成功すると、SDK は自動的にサインインを続行できます。 ユーザーに強力な認証方法が登録されていない場合、フローは認証方法の登録状態に遷移します。

#### サインアップ後に強力な認証方法を登録する

サインアップ フロー後に強力な認証方法を登録するには、 */src/app/sign-up/page.tsx* ファイルを更新する必要があります。 [page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートしてください。
2. サインイン フローで発生するのと同様の方法で、強力な認証方法の登録状態を処理します。 サインアップが正常に完了したら、次のコード スニペットに示すように、結果を使用して自動的にサインインをトリガーできます。

    ```typescript
    // In your sign-up completion handler
    if (signUpState instanceof SignUpCompletedState) {
        // Continue with sign-in using the continuation token
        const result = await signUpState.signIn();
    
        ...
    
        // Check for auth method registration requirement
        if (result.isAuthMethodRegistrationRequired()) {
            setAuthMethodsForRegistration(result.state.getAuthMethods());
            const methods = result.state.getAuthMethods();
            setSelectedAuthMethodForRegistration(methods.length > 0 ? methods[0] : undefined);
            setSignUpState(result.state);
        }
    
        ...
    }
    
    // Then use the same renderForm logic to display AuthMethodRegistrationForm
    // and AuthMethodRegistrationChallengeForm components
    ```

#### パスワードのリセット後に強力な認証方法を登録する

SSPR の後に強力な認証方法を登録するには、 */src/app/reset-password/page.tsx ファイルを更新する* 必要があります。 [page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/page.tsx)の完全なコードを参照してください。

1. 必要な型とコンポーネントをインポートしてください。
2. サインイン フローと同様の方法で、強力な認証方法の登録状態を処理します。 SSPRS が正常に完了したら、次のコード スニペットに示すように、結果を使用して自動的にサインインをトリガーできます。

    ```typescript
    // In your password reset completion handler
    if (resetPasswordState instanceof ResetPasswordCompletedState) {
        // Continue with sign-in using the continuation token
        const result = await signUpState.signIn();
    
        ...
    
        // Check for auth method registration requirement
        if (result.isAuthMethodRegistrationRequired()) {
            setAuthMethodsForRegistration(result.state.getAuthMethods());
            const methods = result.state.getAuthMethods();
            setSelectedAuthMethodForRegistration(methods.length > 0 ? methods[0] : undefined);
            setSignUpState(result.state);
        }
    
        ...
    }
    
    // Then use the same renderForm logic to display AuthMethodRegistrationForm
    // and AuthMethodRegistrationChallengeForm components
    ```

### アプリを実行してテストする

「アプリの実行とテスト」の手順を使用しますが、今回は強力な認証方法の登録フローをテストします。

#### サインアップ後の認証方法の登録をテストする

1. http://localhost:3000/sign-upに移動してサインアップ フォームを表示します。
2. 必要な詳細を入力し、次のプロンプトでサインアップします。 正常にサインアップすると、強力な認証方法の登録フォームが表示され、アプリは自動的にサインイン フローに進みます。
3. 選択した強力な認証方法 ( **Emails OTP** など) を選択し、メール アドレスを入力します。
4. [ **続行]** を選択してフォームの詳細を送信します。 確認コードがメール アドレスに届きます。
5. チャレンジ フォームのテキスト ボックスに確認コードを入力し、[ **続行** ] ボタンを選択します。 コードが検証されると、強力な認証方法が登録され、サインインします。

#### サインイン中に強力な認証方法の登録をテストする

サインイン時に強力な認証方法の登録をテストするには、強力な認証方法が登録されていないユーザー アカウントがあることを確認します。

1. http://localhost:3000/sign-inに移動してサインイン フォームを表示します。
2. 詳細を入力し、[ **続行** ] ボタンを選択し、プロンプトに従います。 アプリが強力な認証方法の登録フローに入ります。
3. アプリのプロンプトに従って、強力な認証方法の登録を完了します。

#### パスワード リセット後の認証方法の登録をテストする

SSPR の後に強力な認証方法の登録をテストするには、強力な認証方法が登録されていないユーザー アカウントがあることを確認します。

1. http://localhost:3000/reset-passwordに移動して、パスワード リセット フォームを表示します。
2. 詳細を入力し、[ **続行** ] ボタンを選択し、アプリのプロンプトに従ってパスワード リセット フローを完了します。 パスワードを正常にリセットすると、強力な認証方法の登録フォームが表示され、アプリのサインイン フローが続行されます。
3. アプリのプロンプトに従って、強力な認証方法の登録を完了します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-reset-password"} -->
## ネイティブ認証を使用して React SPA のパスワードをリセットする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-reset-password
- Service: identity-platform / external
- Article date: 2025-02-07
- Summary: ネイティブ認証を使用して外部テナントのユーザーのパスワードをリセットする React シングルページ アプリを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証を使用して React シングルページ アプリ (SPA) でパスワードをリセットする方法について説明します。

このチュートリアルでは、次の操作を行います。

- React アプリを更新して、ユーザーのパスワードをリセットします。
- パスワード リセット フローをテストする

### [前提条件]

- 「[チュートリアル: ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-set-up-local-cors)の CORS ヘッダーを管理するように CORS プロキシ サーバーを設定する」の手順を完了します。

### アプリがネイティブ認証 API に対して行う呼び出しの種類を定義する

パスワード リセット フロー中、アプリは、パスワード リセット要求の開始やパスワード リセット フォームの送信など、ネイティブ認証 API を複数回呼び出します。

これらの呼び出しを定義するには、*scr/client/RequestTypes.ts* ファイルを開き、次のコード スニペットを追加します。

```typescript
    export interface ResetPasswordStartRequest {
        username: string;
        challenge_type: string;
        client_id: string;
    }

    export interface ResetPasswordSubmitRequest {
        client_id: string;
        continuation_token: string;
        new_password: string;
    }

    export interface ResetPasswordSubmitForm {
        continuation_token: string;
        new_password: string;
    }
```

### ネイティブ認証 API からアプリが受け取る応答の種類を定義する

パスワード リセット操作のネイティブ認証 API からアプリが受信できる応答の種類を定義するには、*src/client/ResponseTypes.ts* ファイルを開き、次のコード スニペットを追加します。

```typescript
    export interface ChallengeResetResponse {
        continuation_token: string;
        expires_in: number;
    }

    export interface ResetPasswordSubmitResponse {
        continuation_token: string;
        poll_interval: number;
    }
```

### パスワード リセット要求を処理する

このセクションでは、パスワード リセット フロー要求を処理するコードを追加します。 これらの要求の例として、パスワード リセット要求を開始し、パスワード リセット フォームを送信します。

これを行うには、src/client/ResetPasswordService.ts という名前のファイルを作成し、次のコード スニペットを追加します。

```typescript
    import { CLIENT_ID, ENV } from "../config";
    import { postRequest } from "./RequestClient";
    import { ChallengeForm, ChallengeRequest, ResetPasswordStartRequest, ResetPasswordSubmitForm, ResetPasswordSubmitRequest } from "./RequestTypes";
    import { ChallengeResetResponse, ChallengeResponse, ResetPasswordSubmitResponse } from "./ResponseTypes";

    export const resetStart = async ({ username }: { username: string }) => {
        const payloadExt: ResetPasswordStartRequest = {
            username,
            client_id: CLIENT_ID,
            challenge_type: "password oob redirect",
        };

        return await postRequest(ENV.urlResetPwdStart, payloadExt);
    };

    export const resetChallenge = async ({ continuation_token }: { continuation_token: string }): Promise<ChallengeResponse> => {
        const payloadExt: ChallengeRequest = {
            continuation_token,
            client_id: CLIENT_ID,
            challenge_type: "oob redirect",
        };

        return await postRequest(ENV.urlResetPwdChallenge, payloadExt);
    };

    export const resetSubmitOTP = async (payload: ChallengeForm): Promise<ChallengeResetResponse> => {
        const payloadExt = {
            client_id: CLIENT_ID,
            continuation_token: payload.continuation_token,
            oob: payload.oob,
            grant_type: "oob",
        };

        return await postRequest(ENV.urlResetPwdContinue, payloadExt);
    };

    export const resetSubmitNewPassword = async (payload: ResetPasswordSubmitForm): Promise<ResetPasswordSubmitResponse> => {
        const payloadExt: ResetPasswordSubmitRequest = {
            client_id: CLIENT_ID,
            continuation_token: payload.continuation_token,
            new_password: payload.new_password,
        };

        return await postRequest(ENV.urlResetPwdSubmit, payloadExt);
    };

    export const resetPoll = async (continuation_token: string): Promise<ChallengeResetResponse> => {
        const payloadExt = {
            client_id: CLIENT_ID,
            continuation_token,
        };
        return await postRequest(ENV.urlResetPwdPollComp, payloadExt);
    };
```

`challenge_type` プロパティは、クライアント アプリがサポートする認証方法を示します。 [のチャレンジの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)について詳しく読む。

### UI コンポーネントを作成する

パスワード リセット フロー中に、このアプリはさまざまな画面で、ユーザーのユーザー名 (電子メール)、ワンタイム パスコード、および新しいユーザー パスワードを収集します。

1. *src* フォルダー内に、*/pages/resetpassword* という名前のフォルダーを作成します。
2. パスワード リセット フォームを作成、表示、送信するには、src/pages/resetpassword/ResetPassword.tsx ファイルを作成し、次のコードを追加します。

    ```typescript
    // ResetPassword.tsx
    import React, { useState } from "react";
    import { resetChallenge, resetStart, resetSubmitNewPassword, resetSubmitOTP } from "../../client/ResetPasswordService";
    import { ChallengeResetResponse, ChallengeResponse, ErrorResponseType } from "../../client/ResponseTypes";
    
    export const ResetPassword: React.FC = () => {
      const [username, setUsername] = useState<string>("");
      const [otp, setOTP] = useState<string>("");
      const [newPassword, setNewPassword] = useState<string>("");
      const [error, setError] = useState<string>("");
      const [step, setStep] = useState<number>(1);
      const [isLoading, setIsloading] = useState<boolean>(false);
      const [tokenRes, setTokenRes] = useState<ChallengeResponse>({
        binding_method: "",
        challenge_channel: "",
        challenge_target_label: "",
        challenge_type: "",
        code_length: 0,
        continuation_token: "",
        interval: 0,
      });
      const [otpRes, setOTPRes] = useState<ChallengeResetResponse>({
        expires_in: 0,
        continuation_token: "",
      });
    
      const handleResetPassword = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!username) {
          setError("Username is required");
          return;
        }
        setError("");
        try {
          setIsloading(true);
          const res1 = await resetStart({ username });
          const tokenRes = await resetChallenge({ continuation_token: res1.continuation_token });
          setTokenRes(tokenRes);
          setStep(2);
        } catch (err) {
          setError("An error occurred during password reset " + (err as ErrorResponseType).error_description);
        } finally {
          setIsloading(false);
        }
      };
    
      const handleSubmitCode = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!otp) {
          setError("All fields are required");
          return;
        }
        setError("");
        try {
          setIsloading(true);
          const res = await resetSubmitOTP({
            continuation_token: tokenRes.continuation_token,
            oob: otp,
          });
          setOTPRes(res);
          setStep(3);
        } catch (err) {
          setError("An error occurred while submitting the otp code " + (err as ErrorResponseType).error_description);
        } finally {
          setIsloading(false);
        }
      };
    
      const handleSubmitNewPassword = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!newPassword) {
          setError('All fields are required');
          return;
        }
        setError('');
        try {
          setIsloading(true);
          await resetSubmitNewPassword({
            continuation_token: otpRes.continuation_token,
            new_password: newPassword,
          });
          setStep(4);
        } catch (err) {
          setError("An error occurred while submitting the new password " + (err as ErrorResponseType).error_description);
        } finally {
          setIsloading(false);
        }
      };
    
      return (
        <div className="reset-password-form">
        //collect username to initiate password reset flow
          {step === 1 && (
            <form onSubmit={handleResetPassword}>
              <h2>Reset Password</h2>
              <div className="form-group">
                <label>Username:</label>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} required />
              </div>
              {error && <div className="error">{error}</div>}
              {isLoading && <div className="warning">Sending request...</div>}
              <button type="submit">Reset Password</button>
            </form>
          )}
            //collect OTP
          {step === 2 && (
            <form onSubmit={handleSubmitCode}>
              <h2>Submit one time code received via email at {tokenRes.challenge_target_label}</h2>
              <div className="form-group">
                <label>One time code:</label>
                <input type="text" maxLength={tokenRes.code_length} value={otp} onChange={(e) => setOTP(e.target.value)} required />
              </div>
              {error && <div className="error">{error}</div>}
              {isLoading && <div className="warning">Sending request...</div>}
              <button type="submit">Submit code</button>
            </form>
          )}
            //Collect new password
          {step === 3 && (
            <form onSubmit={handleSubmitNewPassword}>
              <h2>Submit New Password</h2>
              <div className="form-group">
                <label>New Password:</label>
                <input type="password" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} required />
              </div>
              {error && <div className="error">{error}</div>}
              {isLoading && <div className="warning">Sending request...</div>}
              <button type="submit">Submit New Password</button>
            </form>
          )}
            //report success after password reset is successful
          {step === 4 && (
            <div className="reset-password-success">
              <h2>Password Reset Successful</h2>
              <p>Your password has been reset successfully. You can now log in with your new password.</p>
            </div>
          )}
        </div>
      );
    };
    ```

### アプリ ルートを追加する

*src/AppRoutes.tsx* ファイルを開き、次のコード行のコメントを解除します。

```typescript
    //uncomment
    import { ResetPassword } from "./pages/ResetAccount/ResetPassword";
    //...
    
    export const AppRoutes = () => {
      return (
        <Routes>
            //uncomment
            <Route path="/reset" element={<ResetPassword />} />
        </Routes>
      );
    };
```

### アプリを実行してテストする

「[実行」の手順に従って、アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-set-up-local-cors#run-and-test-you-app) をテストしてアプリを実行します。 ただし、パスワード リセット フローは、前にサインアップしたユーザー アカウントのみを使用してテストします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-reset-password"} -->
## ネイティブ認証 JavaScript SDK を使用して React SPA アプリのパスワードをリセットする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-reset-password
- Service: identity-platform / external
- Article date: 2025-11-18
- Summary: ネイティブ認証 JavaScript SDK を使用して、ユーザーが React シングルページ アプリのパスワードを外部テナントにリセットできるようにする React シングルページ アプリを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証 JavaScript SDK を使用して React シングルページ アプリでパスワードリセットを有効にする方法について説明します。

このチュートリアルでは、次の操作を行います。

- React アプリを更新して、ユーザーのパスワードをリセットします。
- パスワード リセット フローをテストする

### [前提条件]

- [「チュートリアル: ネイティブ認証 JavaScript SDK を使用して React シングルページ アプリにユーザーをサインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up)する」の手順を完了します。
- 外部テナントの顧客ユーザーに対して[セルフサービス パスワード リセット (SSPR) を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)。 SSPR は、パスワード認証フローで電子メールを使用するアプリの顧客ユーザーが利用できます。

### UI コンポーネントを作成する

パスワード リセット フロー中に、このアプリはユーザーのユーザー名 (電子メール)、ワンタイム パスコード、新しいユーザー パスワードを異なる画面で収集します。 このセクションでは、アプリがパスワードをリセットするために必要な情報を収集するフォームを作成します。

1. *src/app/reset-password* という名前のフォルダーを作成します。
2. *reset-password/components/InitialForm.tsx ファイルを*作成し、[reset-password/components/InitialForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/components/InitialForm.tsx) のコードを貼り付けます。 このコンポーネントには、ユーザーのユーザー名 (電子メール) を収集するフォームが表示されます。
3. *reset-password/components/CodeForm.tsx ファイルを*作成し、[reset-password/components/CodeForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/CodeForm.tsx) からコードを貼り付けます。 このコンポーネントは、ユーザーが受信したワンタイム パスコードを電子メールの受信トレイに収集するフォームを表示します。
4. *reset-password/components/NewPasswordForm.tsx* ファイルを作成し、[reset-password/components/NewPasswordForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/components/NewPasswordForm.tsx) のコードを貼り付けます。 このコンポーネントには、ユーザーの新しいパスワードを収集するフォームが表示されます。

#### フォームの操作を処理する

サインイン フローのロジックを処理する *reset-password/page.tsx* ファイルを作成します。 このファイルでは、次の操作を行います。

- 必要なコンポーネントをインポートし、状態に基づいて適切なフォームを表示します。 [reset-password/page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/page.tsx) の完全な例を参照してください。

    ```typescript
    import {
      CustomAuthPublicClientApplication,
      ICustomAuthPublicClientApplication,
      ResetPasswordCodeRequiredState,
      ResetPasswordPasswordRequiredState,
      ResetPasswordCompletedState,
      AuthFlowStateBase,
    } from "@azure/msal-browser/custom-auth";
    
    export default function ResetPassword() {
      const [app, setApp] = useState<ICustomAuthPublicClientApplication | null>(
        null
      );
      const [loadingAccountStatus, setLoadingAccountStatus] = useState(true);
      const [isSignedIn, setSignInState] = useState(false);
      const [email, setEmail] = useState("");
      const [code, setCode] = useState("");
      const [newPassword, setNewPassword] = useState("");
      const [error, setError] = useState("");
      const [loading, setLoading] = useState(false);
      const [resetState, setResetState] = useState<AuthFlowStateBase | null>(
        null
      );
    
      useEffect(() => {
        const initializeApp = async () => {
          const appInstance = await CustomAuthPublicClientApplication.create(
            customAuthConfig
          );
          setApp(appInstance);
        };
    
        initializeApp();
      }, []);
    
      useEffect(() => {
        const checkAccount = async () => {
          if (!app) return;
    
          const accountResult = app.getCurrentAccount();
    
          if (accountResult.isCompleted()) {
            setSignInState(true);
          }
    
          setLoadingAccountStatus(false);
        };
    
        checkAccount();
      }, [app]);
    
      const renderForm = () => {
        if (loadingAccountStatus) {
          return;
        }
    
        if (isSignedIn) {
          return (
            <div style={styles.signed_in_msg}>
              Please sign out before processing the password reset.
            </div>
          );
        }
    
        if (resetState instanceof ResetPasswordPasswordRequiredState) {
          return (
            <NewPasswordForm
              onSubmit={handleNewPasswordSubmit}
              newPassword={newPassword}
              setNewPassword={setNewPassword}
              loading={loading}
            />
          );
        }
    
        if (resetState instanceof ResetPasswordCodeRequiredState) {
          return (
            <CodeForm
              onSubmit={handleCodeSubmit}
              code={code}
              setCode={setCode}
              loading={loading}
            />
          );
        }
    
        if (resetState instanceof ResetPasswordCompletedState) {
          return <ResetPasswordResultPage />;
        }
    
        return (
          <InitialForm
            onSubmit={handleInitialSubmit}
            email={email}
            setEmail={setEmail}
            loading={loading}
          />
        );
      };
    
      return (
        <div style={styles.container}>
          <h2 style={styles.h2}>Reset Password</h2>
          {renderForm()}
          {error && <div style={styles.error}>{error}</div>}
        </div>
      );
    }
    ```
- パスワード リセット フローを開始するには、次のコード スニペットを使用します。 コード スニペットを配置する場所については、 [reset-password/page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/page.tsx) の完全な例を参照してください。

    ```typescript
    const handleInitialSubmit = async (e: React.FormEvent) => {
        if (!app) return;
        e.preventDefault();
        setError("");
        setLoading(true);
        const result = await app.resetPassword({
            username: email,
        });
        const state = result.state;
        if (result.isFailed()) {
            if (result.error?.isInvalidUsername()) {
                setError("Invalid email address");
            } else if (result.error?.isUserNotFound()) {
                setError("User not found");
            } else {
                setError(
                    result.error?.errorData.errorDescription || "An error occurred while initiating password reset"
                );
            }
        } else {
            setResetState(state);
        }
        setLoading(false);
    };
    ```

    SDK のインスタンス メソッド ( `resetPassword()`) は、パスワード リセット フローを開始します。
- ワンタイム パスコードを送信するには、次のコード スニペットを使用します。 コード スニペットを配置する場所については、 [reset-password/page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/page.tsx) の完全な例を参照してください。

    ```typescript
    const handleCodeSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
        if (resetState instanceof ResetPasswordCodeRequiredState) {
            const result = await resetState.submitCode(code);
            const state = result.state;
            if (result.isFailed()) {
                if (result.error?.isInvalidCode()) {
                    setError("Invalid verification code");
                } else {
                    setError(result.error?.errorData.errorDescription || "An error occurred while verifying the code");
                }
            } else {
                setResetState(state);
            }
        }
        setLoading(false);
    };
    ```

    パスワード リセット状態の `submitCode()` は、ワンタイム パスコードを送信します。
- ユーザーの新しいパスワードを送信するには、次のコード スニペットを使用します。 コード スニペットを配置する場所については、 [reset-password/page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/page.tsx) の完全な例を参照してください。

    ```typescript
    const handleNewPasswordSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
        if (resetState instanceof ResetPasswordPasswordRequiredState) {
            const result = await resetState.submitNewPassword(newPassword);
            const state = result.state;
            if (result.isFailed()) {
                if (result.error?.isInvalidPassword()) {
                    setError("Invalid password");
                } else {
                    setError(result.error?.errorData.errorDescription || "An error occurred while setting new password");
                }
            } else {
                setResetState(state);
            }
        }
        setLoading(false);
    };
    ```

    パスワード リセット状態の `submitNewPassword()` は、ユーザーの新しいパスワードを送信します。
- パスワードリセットの結果を使用するには、次のコード スニペットを使用します。 コード スニペットを配置する場所については、 [reset-password/page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/page.tsx) の完全な例を参照してください。

    ```typescript
    if (resetState instanceof ResetPasswordCompletedState) {
        return <ResetPasswordResultPage/>;
    }
    
    ```

### 省略可能: パスワードのリセット後にユーザーを自動的にサインインする

ユーザーが自分のパスワードを正常にリセットしたら、新しいサインイン フローを開始せずにアプリに直接サインインできます。 これを行うには、次のコード スニペットを使用します。 [reset-password/page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/reset-password/page.tsx) の完全な例を参照してください。

```typescript
if (resetState instanceof ResetPasswordCompletedState) {
    const result = await resetState.signIn();
    const state = result.state;
    if (result.isFailed()) {
        setError(result.error?.errorData?.errorDescription || "An error occurred during auto sign-in");
    }
    if (result.isCompleted()) {
        setData(result.data);
        setResetState(state);
    }
}
```

### アプリを実行してテストする

「 [アプリの実行とテスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up#run-and-test-your-app) 」の手順に従ってアプリを実行し、サインイン フローをテストします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-in"} -->
## ネイティブ認証 JavaScript SDK を使用して React SPA でユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-in
- Service: identity-platform / external
- Article date: 2025-11-18
- Summary: ネイティブ認証 JavaScript SDK を使用して、React シングルページ アプリのユーザーを外部テナントにサインインさせる React シングルページ アプリを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証 JavaScript SDK を使用して React シングルページ アプリ (SPA) にユーザーをサインインさせる方法について説明します。

このチュートリアルでは、次の操作を行います。

- React アプリを更新してユーザーをサインインさせる。
- サインイン フローをテストします。

### [前提条件]

- [「ネイティブ認証 JavaScript SDK を使用して React シングルページ アプリにユーザーをサインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up)する」の手順を完了します。

### UI コンポーネントを作成する

このセクションでは、ユーザーのサインイン情報を収集するフォームを作成します。

1. *src/app/sign-in* という名前のフォルダーを作成します。
2. *サインイン/コンポーネント/InitialForm.tsx ファイルを*作成し、[サインイン/コンポーネント/InitialForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-in/components/InitialForm.tsx) からコードを貼り付けます。 このコンポーネントには、ユーザーのユーザー名 (電子メール) を収集するフォームが表示されます。
3. 認証方法が電子メールとワンタイム パスコードの場合は、 *サインイン/コンポーネント/CodeForm.tsx* ファイルを作成し、 [サインイン/コンポーネント/CodeForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/CodeForm.tsx) からコードを貼り付けます。 管理者が Microsoft Entra 管理センターのサインイン フローとしてメール ワンタイム パスコードを設定した場合、このコンポーネントはユーザーからワンタイム パスコードを収集するフォームを表示します。
4. 認証方法が電子メールとパスワードの場合は、 *サインイン/コンポーネント/PasswordForm.tsx* ファイルを作成し、 [サインイン/コンポーネント/PasswordForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/PasswordForm.tsx) からコードを貼り付けます。 このコンポーネントには、ユーザーのパスワードを収集するフォームが表示されます。
5. *サインイン/コンポーネント/UserInfo.tsx ファイルを*作成し、[サインイン/コンポーネント/UserInfo.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-in/components/UserInfo.tsx) からコードを貼り付けます。 このコンポーネントには、サインインしているユーザーのユーザー名とサインイン状態が表示されます。

### フォームのインタラクションを処理する

このセクションでは、サインイン フローの開始、ユーザー パスワードの送信、ワンタイム パスコードなど、サインイン フォームの操作を処理するコードを追加します。

サインイン フローのロジックを処理する *サインイン/ページ.tsx* ファイルを作成します。 このファイルでは、次の操作を行います。

- 必要なコンポーネントをインポートし、状態に基づいて適切なフォームを表示します。 [サインイン/ページ.tsx の](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-in/page.tsx)完全な例を参照してください。

    ```typescript
    import {
      CustomAuthPublicClientApplication,
      ICustomAuthPublicClientApplication,
      SignInCodeRequiredState,
      // Uncommon if using a Email + Password flow
      // SignInPasswordRequiredState,
      SignInCompletedState,
      AuthFlowStateBase,
    } from "@azure/msal-browser/custom-auth";
    
    export default function SignIn() {
        const [authClient, setAuthClient] = useState<ICustomAuthPublicClientApplication | null>(null);
        const [username, setUsername] = useState("");
        //If you are sign in using a Email + Password flow, uncomment the following line
        //const [password, setPassword] = useState("");
        const [code, setCode] = useState("");
        const [error, setError] = useState("");
        const [loading, setLoading] = useState(false);
        const [signInState, setSignInState] = useState<AuthFlowStateBase | null>(null);
        const [data, setData] = useState<CustomAuthAccountData | undefined>(undefined);
        const [loadingAccountStatus, setLoadingAccountStatus] = useState(true);
        const [isSignedIn, setCurrentSignInStatus] = useState(false);
    
        useEffect(() => {
            const initializeApp = async () => {
                const appInstance = await CustomAuthPublicClientApplication.create(customAuthConfig);
                setAuthClient(appInstance);
            };
    
            initializeApp();
        }, []);
    
        useEffect(() => {
            const checkAccount = async () => {
                if (!authClient) return;
    
                const accountResult = authClient.getCurrentAccount();
    
                if (accountResult.isCompleted()) {
                    setCurrentSignInStatus(true);
                }
    
                setData(accountResult.data);
    
                setLoadingAccountStatus(false);
            };
    
            checkAccount();
        }, [authClient]);
    
        const renderForm = () => {
            if (loadingAccountStatus) {
                return;
            }
    
            if (isSignedIn || signInState instanceof SignInCompletedState) {
                return <UserInfo userData={data} />;
            }
            //If you are signing up using Email + Password flow, uncomment the following block of code
            /*
            if (signInState instanceof SignInPasswordRequiredState) {
                return (
                    <PasswordForm
                        onSubmit={handlePasswordSubmit}
                        password={password}
                        setPassword={setPassword}
                        loading={loading}
                    />
                );
            }
            */
            if (signInState instanceof SignInCodeRequiredState) {
                return <CodeForm onSubmit={handleCodeSubmit} code={code} setCode={setCode} loading={loading} />;
            }
    
            return <InitialForm onSubmit={startSignIn} username={username} setUsername={setUsername} loading={loading} />;
        };
    
        return (
            <div style={styles.container}>
                <h2 style={styles.h2}>Sign In</h2>
                <>
                    {renderForm()}
                    {error && <div style={styles.error}>{error}</div>}
                </>
            </div>
        );
    }
    ```
- サインイン フローを開始するには、次のコード スニペットを使用します。 コード スニペットを配置する場所については、 [サインイン/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-in/page.tsx) の完全な例を参照してください。

    ```typescript
    const startSignIn = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        if (!authClient) return;
    
        // Start the sign-in flow
        const result = await authClient.signIn({
            username,
        });
    
        // Thge result may have the different states,
        // such as Password required state, OTP code rquired state, Failed state and Completed state.
    
        if (result.isFailed()) {
            if (result.error?.isUserNotFound()) {
                setError("User not found");
            } else if (result.error?.isInvalidUsername()) {
                setError("Username is invalid");
            } else if (result.error?.isPasswordIncorrect()) {
                setError("Password is invalid");
    
            } else {
                setError(`An error occurred: ${result.error?.errorData?.errorDescription}`);
            }
        }
    
        if (result.isCompleted()) {
            setData(result.data);
        }
    
        setSignInState(result.state);
    
        setLoading(false);
    };
    ```

    SDK のインスタンス メソッド `signIn()`、サインイン フローが開始されます。

    Note

    `username` パラメーターは、テナントのユーザー フローで Username 組み込みユーザー属性が有効になっている場合、ユーザーの電子メール アドレスまたはユーザー**名** (エイリアス) のいずれかを受け入れます。 ユーザーは、いずれかの値を入力してサインインできます。 この属性を有効にするには、「 [サインイン識別子ポリシーでユーザー名を有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)」を参照してください。
- 選択した認証フローが電子メールとワンタイム パスコードの場合は、次のコード スニペットを使用してワンタイム パスコードを送信します。 コード スニペットを配置する場所については、 [サインイン/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) の完全な例を参照してください。

    ```typescript
    const handleCodeSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        if (signInState instanceof SignInCodeRequiredState) {
            const result = await signInState.submitCode(code);
    
            // the result object may have the different states, such as Failed state and Completed state.
    
            if (result.isFailed()) {
                if (result.error?.isInvalidCode()) {
                    setError("Invalid code");
                } else {
                    setError(result.error?.errorData?.errorDescription || "An error occurred while verifying the code");
                }
            }
    
            if (result.isCompleted()) {
                setData(result.data);
                setSignInState(result.state);
            }
        }
    
        setLoading(false);
    };
    ```

    サインイン状態の `submitCode()` はワンタイム パスコードを送信します。
- 選択した認証フローが電子メールとパスワードの場合は、次のコード スニペットを使用してユーザーのパスワードを送信します。 コード スニペットを配置する場所については、 [サインイン/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) の完全な例を参照してください。

    ```typescript
    const handlePasswordSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        if (signInState instanceof SignInPasswordRequiredState) {
            const result = await signInState.submitPassword(password);
    
            if (result.isFailed()) {
                if (result.error?.isInvalidPassword()) {
                    setError("Incorrect password");
                } else {
                    setError(
                        result.error?.errorData?.errorDescription || "An error occurred while verifying the password"
                    );
                }
            }
    
            if (result.isCompleted()) {
                setData(result.data);
    
                setSignInState(result.state);
            }
        }
    
        setLoading(false);
    };
    ```

    サインイン状態の `submitPassword()` は、ユーザーのパスワードを送信します。

### サインアップ エラーを処理する

サインイン中、すべてのアクションが成功するわけではありません。 たとえば、ユーザーが存在しないユーザー名でサインインしようとしたり、無効な電子メール ワンタイム パスコードや、最小要件を満たしていないパスワードを送信しようとしたりすることがあります。 エラーを適切に処理するときは、次のようにしてください。

- `signIn()` メソッドでサインイン フローを開始します。
- `submitCode()` メソッドでワンタイム パスコードを送信します。
- `submitPassword()`メソッドでパスワードを送信します。 このエラーは、選択したサインアップ フローが電子メールとパスワードで行われる場合に処理します。

`signIn()` メソッドの結果として発生する可能性があるエラーの 1 つが`result.error?.isRedirectRequired()`。 このシナリオは、認証フローを完了するのにネイティブ認証だけでは十分ではない場合に発生します。 たとえば、承認サーバーにクライアントが提供できない機能が必要な場合です。 [ネイティブ認証 Web フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback)の詳細と、React アプリで [Web フォールバックをサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-javascript-sdk-web-fallback)する方法について説明します。

### アプリを実行してテストする

「 [アプリの実行とテスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up#run-and-test-your-app) 」の手順に従ってアプリを実行し、サインイン フローをテストします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up"} -->
## ネイティブ認証 SDK を使用して React SPA にユーザーをサインアップする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up
- Service: identity-platform / external
- Article date: 2025-11-18
- Summary: ネイティブ認証 JavaScript SDK を使用してユーザーをサインアップする React シングルページ アプリケーションを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証の JavaScript SDK を使用してユーザーをサインアップする React シングルページ アプリを構築する方法について説明します。

このチュートリアルでは、次の操作を行います。

- React Next.js プロジェクトを作成します。
- MSAL JS SDK を追加します。
- アプリの UI コンポーネントを追加します。
- ユーザーをサインアップするようにプロジェクトをセットアップします。

### [前提条件]

- [「クイック スタート: ネイティブ認証 JavaScript SDK を使用して React シングルページ アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-sdk-sign-in?tabs=react)する」の手順を完了します。 このクイック スタートでは、サンプルの React コード サンプルを実行する方法について説明します。
- [「ネイティブ認証用の CORS ヘッダーを管理するための CORS プロキシ サーバーの設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-single-page-app-javascript-sdk-set-up-local-cors)」の手順を完了します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコード エディター。
- [Node.js](https://nodejs.org/en/download/)。
- ユーザーがユーザー名 (エイリアス) でサインアップできるようにする場合は、サインアップ ユーザー フローで **Username** 組み込みユーザー属性を有効にします。 手順については、「 [サインイン識別子ポリシーでユーザー名を有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)」を参照してください。

### React プロジェクトを作成して依存関係をインストールする

コンピューター内の任意の場所で、次のコマンドを実行して、reactspa 名前の新しい React プロジェクトを作成し、プロジェクト フォルダーに移動してからパッケージをインストールします。

```console
npx create-next-app@latest
cd reactspa
npm install
```

コマンドを正常に実行すると、次の構造のアプリが作成されます。

```console
spasample/
└──node_modules/
   └──...
└──public/
   └──...
└──src/
   └──app/
      └──favicon.ico
      └──globals.css
      └──page.tsx
      └──layout.tsx
└──postcss.config.mjs
└──package-lock.json
└──package.json
└──tsconfig.json
└──README.md
└──next-env.d.ts
└──next.config.ts
```

### JavaScript SDK をプロジェクトに追加する

アプリでネイティブ認証 JavaScript SDK を使用するには、ターミナルを使用して次のコマンドを使用してインストールします。

```console
npm install @azure/msal-browser
```

ネイティブ認証機能は、 `azure-msal-browser` ライブラリの一部です。 ネイティブ認証機能を使用するには、 `@azure/msal-browser/custom-auth`からインポートします。 例えば次が挙げられます。

```typescript
  import CustomAuthPublicClientApplication from "@azure/msal-browser/custom-auth";
```

### クライアント構成の追加

このセクションでは、ネイティブ認証パブリック クライアント アプリケーションが SDK のインターフェイスと対話できるように構成を定義します。 これを行うには、 *src/config/auth-config.ts* という名前のファイルを作成し、次のコードを追加します。

```typescript
export const customAuthConfig: CustomAuthConfiguration = {
  customAuth: {
    challengeTypes: ["password", "oob", "redirect"],
    authApiProxyUrl: "http://localhost:3001/api",
  },
  auth: {
    clientId: "Enter_the_Application_Id_Here",
    authority: "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com",
    redirectUri: "/",
    postLogoutRedirectUri: "/",
    navigateToLoginRequestUrl: false,
  },
  cache: {
    cacheLocation: "sessionStorage",
  },
  system: {
    loggerOptions: {
      loggerCallback: (
        level: LogLevel,
        message: string,
        containsPii: boolean
      ) => {
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
    },
  },
};
```

コード内で、次のプレースホルダーを見つけてください。

- `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリのアプリケーション (クライアント) ID に置き換えます。
- `Enter_the_Tenant_Subdomain_Here` 次に、Microsoft Entra 管理センターのテナント サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。

### UI コンポーネントを作成する

このアプリは、指定された名前、ユーザー名 (電子メール)、パスワード、ワンタイム パスコードなどのユーザーの詳細をユーザーから収集します。 そのため、アプリには、この情報を収集するフォームが必要です。

1. *src/app/sign-up* という名前のフォルダーを *src* フォルダーに作成します。
2. *サインアップ/コンポーネント/InitialForm.tsx ファイルを*作成し、[サインアップ/コンポーネント/InitialForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/components/InitialForm.tsx) からコードを貼り付けます。 このコンポーネントには、ユーザーサインアップ属性を収集するフォームが表示されます。
3. *サインアップ/コンポーネント/CodeForm.tsx ファイルを*作成し、[サインアップ/components/CodeForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/CodeForm.tsx) からコードを貼り付けます。 このコンポーネントは、ユーザーに送信されたワンタイム パスコードを収集するフォームを表示します。 このフォームは、パスワードを含むメール、またはワンタイム パスコード認証方法を使用した電子メールに必要です。
4. 認証方法が *パスワード付きの電子メール*の場合は、 *サインアップ/コンポーネント/PasswordForm.tsx* ファイルを作成し、 [サインアップ/コンポーネント/PasswordForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/shared/components/PasswordForm.tsx) からコードを貼り付けます。 このコンポーネントは、パスワード入力フォームを表示します。

### フォームの操作を処理する

このセクションでは、ユーザーのサインアップの詳細やワンタイム パスコードやパスワードの送信など、サインアップ フォームの操作を処理するコードを追加します。

サインアップ フローのロジックを処理する *sign-up/page.tsx* を作成します。 このファイルでは、次の操作を行います。

- 必要なコンポーネントをインポートし、状態に基づいて適切なフォームを表示します。 [サインアップ/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) の完全な例を参照してください。

    ```typescript
        import { useEffect, useState } from "react";
        import { customAuthConfig } from "../../config/auth-config";
        import { styles } from "./styles/styles";
        import { InitialFormWithPassword } from "./components/InitialFormWithPassword";
    
        import {
        CustomAuthPublicClientApplication,
        ICustomAuthPublicClientApplication,
        SignUpCodeRequiredState,
        // Uncomment if your choice of authentication method is email with password
        // SignUpPasswordRequiredState,
        SignUpCompletedState,
        AuthFlowStateBase,
      } from "@azure/msal-browser/custom-auth";
    
        import { SignUpResultPage } from "./components/SignUpResult";
        import { CodeForm } from "./components/CodeForm";
        import { PasswordForm } from "./components/PasswordForm";    
    export default function SignUpPassword() {
        const [authClient, setAuthClient] = useState<ICustomAuthPublicClientApplication | null>(null);
        const [firstName, setFirstName] = useState("");
        const [lastName, setLastName] = useState("");
        const [jobTitle, setJobTitle] = useState("");
        const [city, setCity] = useState("");
        const [country, setCountry] = useState("");
        const [email, setEmail] = useState("");
        //Uncomment if your choice of authentication method is email with password
        //const [password, setPassword] = useState("");
        const [code, setCode] = useState("");
        const [error, setError] = useState("");
        const [loading, setLoading] = useState(false);
        const [signUpState, setSignUpState] = useState<AuthFlowStateBase | null>(null);
        const [loadingAccountStatus, setLoadingAccountStatus] = useState(true);
        const [isSignedIn, setSignInState] = useState(false);
    
        useEffect(() => {
            const initializeApp = async () => {
                const appInstance = await CustomAuthPublicClientApplication.create(customAuthConfig);
                setAuthClient(appInstance);
            };
            initializeApp();
        }, []);
    
        useEffect(() => {
            const checkAccount = async () => {
                if (!authClient) return;
                const accountResult = authClient.getCurrentAccount();
                if (accountResult.isCompleted()) {
                    setSignInState(true);
                }
                setLoadingAccountStatus(false);
            };
            checkAccount();
        }, [authClient]);
    
        const renderForm = () => {
            if (loadingAccountStatus) {
                return;
            }
            if (isSignedIn) {
                return (
                    <div style={styles.signed_in_msg}>Please sign out before processing the sign up.</div>
                );
            }
            if (signUpState instanceof SignUpCodeRequiredState) {
                return (
                    <CodeForm
                        onSubmit={handleCodeSubmit}
                        code={code}
                        setCode={setCode}
                        loading={loading}
                    />
                );
            } 
            //Uncomment the following block of code if your choice of authentication method is email with password 
            /*
            else if(signUpState instanceof SignUpPasswordRequiredState) {
                return <PasswordForm
                    onSubmit={handlePasswordSubmit}
                    password={password}
                    setPassword={setPassword}
                    loading={loading}
                />;
            }
            */
            else if (signUpState instanceof SignUpCompletedState) {
                return <SignUpResultPage />;
            } else {
                return (
                    <InitialForm
                        onSubmit={handleInitialSubmit}
                        firstName={firstName}
                        setFirstName={setFirstName}
                        lastName={lastName}
                        setLastName={setLastName}
                        jobTitle={jobTitle}
                        setJobTitle={setJobTitle}
                        city={city}
                        setCity={setCity}
                        country={country}
                        setCountry={setCountry}
                        email={email}
                        setEmail={setEmail}
                        loading={loading}
                    />
                );
            }
        }
        return (
            <div style={styles.container}>
                <h2 style={styles.h2}>Sign Up</h2>
                {renderForm()}
                {error && <div style={styles.error}>{error}</div>}
            </div>
        );
    }
    ```

    このコードでは、 クライアント構成を使用してネイティブ認証パブリック クライアント アプリのインスタンスも作成します。

    ```typescript
    const appInstance = await CustomAuthPublicClientApplication.create(customAuthConfig);
    setAuthClient(appInstance);
    ```
- 最初のフォーム送信を処理するには、次のコード スニペットを使用します。 コード スニペットを配置する場所については、 [サインアップ/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) の完全な例を参照してください。

    ```typescript
    const handleInitialSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        if (!authClient) return;
    
        const attributes: UserAccountAttributes = {
            displayName: `${firstName} ${lastName}`,
            givenName: firstName,
            surname: lastName,
            jobTitle: jobTitle,
            city: city,
            country: country,
        };
    
        const result = await authClient.signUp({
            username: email,
            attributes
        });
        const state = result.state;
    
        if (result.isFailed()) {
            if (result.error?.isUserAlreadyExists()) {
                setError("An account with this email already exists");
            } else if (result.error?.isInvalidUsername()) {
                setError("Invalid uername");
            } else if (result.error?.isInvalidPassword()) {
                setError("Invalid password");
            } else if (result.error?.isAttributesValidationFailed()) {
                setError("Invalid attributes");
            } else if (result.error?.isMissingRequiredAttributes()) {
                setError("Missing required attributes");
            } else {
                setError(result.error?.errorData.errorDescription || "An error occurred while signing up");
            }
        } else {
            setSignUpState(state);
        }
        setLoading(false);
    };
    ```

    SDK のインスタンス メソッド `signUp()` 、サインアップ フローを開始します。
- ワンタイム パスコードの送信を処理するには、次のコード スニペットを使用します。 コード スニペットを配置する場所については、 [サインアップ/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) の完全な例を参照してください。

    ```typescript
    const handleCodeSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        setLoading(true);
    
        try {
            if (signUpState instanceof SignUpCodeRequiredState) {
                const result = await signUpState.submitCode(code);
                if (result.error) {
                    if (result.error.isInvalidCode()) {
                        setError("Invalid verification code");
                    } else {
                        setError("An error occurred while verifying the code");
                    }
                    return;
                }
                if (result.state instanceof SignUpCompletedState) {
                    setSignUpState(result.state);
                }
            }
        } catch (err) {
            setError("An unexpected error occurred");
            console.error(err);
        } finally {
            setLoading(false);
        }
    };
    ```
- パスワードの送信を処理するには、次のコード スニペットを使用します。 認証方法の選択がパスワード *付きの電子メール*である場合は、パスワードの送信を処理します。 コード スニペットを配置する場所については、 [サインアップ/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) の完全な例を参照してください。

    ```typescript
        const handlePasswordSubmit = async (e: React.FormEvent) => {
            e.preventDefault();
            setError("");
            setLoading(true);
    
            if (signUpState instanceof SignUpPasswordRequiredState) {
                const result = await signUpState.submitPassword(password);
                const state = result.state;
    
                if (result.isFailed()) {
                    if (result.error?.isInvalidPassword()) {
                        setError("Invalid password");
                    } else {
                        setError(result.error?.errorData.errorDescription || "An error occurred while submitting the password");
                    }
                } else {
                    setSignUpState(state);
                }
            }
    
            setLoading(false);
        };
    ```
- `signUpState instanceof SignUpCompletedState`を使用して、ユーザーがサインアップされ、フローが完了したことを示します。 [サインアップ/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) の完全な例を参照してください。

    ```typescript
    if (signUpState instanceof SignUpCompletedState) {
        return <SignUpResultPage/>;
    }
    ```

### サインアップ時にユーザー名 (エイリアス) を収集する

ユーザーがメールに加えてユーザー名 (エイリアス) でサインアップできるようにすることができます。 ユーザー名 (エイリアス) は、顧客 ID、アカウント番号、または選択した別の値などの代替サインイン識別子です。

サインアップ時には、プライマリ識別子としてユーザー名 (電子メール) が常に必要であり、ユーザー名 (エイリアス) によって置き換えられることはありません。 既定では、ユーザー名 (エイリアス) は省略可能ですが、管理者は必要に応じて構成できます。 アプリは常にユーザー名 (電子メール) を収集し、エイリアスを電子メールと共に属性として収集します。 サインイン時に、ユーザーはユーザー名 (電子メール) またはユーザー名 (エイリアス) を使用してサインインできます。 **Username** 属性をオプションまたは必須として構成する方法については、「[ユーザー入力の種類とページ レイアウトを構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#configure-the-user-input-types-and-page-layout)」を参照してください。

サインアップ時にユーザー名 (エイリアス) を収集するには:

1. サインアップ ユーザー フローで **Username** 組み込みユーザー属性が有効になっていることを確認します。 手順については、「 [サインイン識別子ポリシーでユーザー名を有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)」を参照してください。
2. サインアップページに`flatUsername`状態を追加し、`signUp()` に渡す `UserAccountAttributes` に `flatusername` 属性を含めます。

    ```typescript
    const [flatUsername, setFlatUsername] = useState("");
    
    const attributes: UserAccountAttributes = {
        displayName: `${firstName} ${lastName}`,
        //...
        flatusername: flatUsername,
    };
    ```
3. *InitialForm.tsx* にエイリアス入力を追加して、ユーザー名 (エイリアス) の値を収集します。

    ```tsx
    <input
        type="text"
        placeholder="Username (alias)"
        value={flatUsername}
        onChange={(e) => setFlatUsername(e.target.value)}
        style={styles.input}
    />
    ```
4. ユーザー名 (エイリアス) に関連するエラーを処理します。

    - `result.error?.isUserAlreadyExists()` には、重複する電子メール *または* 重複するユーザー名 (エイリアス) が含まれます。 それに応じてメッセージを更新します。たとえば、 *このメールまたはユーザー名を持つアカウントが既に存在します*。
    - 無効なユーザー名 (エイリアス) は、`result.error?.isAttributesValidationFailed()`ではなく`result.error?.isInvalidUsername()`によって表示されます。 ユーザー名固有のメッセージを表示するには、このメソッドで分岐します。

### サインアップ エラーを処理する

サインアップ中、すべてのアクションが成功するわけではありません。 たとえば、ユーザーが既に使用されているメール アドレスでサインアップしようとしたり、無効なメールのワンタイム パスコードを送信したりすることがあります。 次の操作を行う際は、エラーを適切に処理してください。

- `signUp()` メソッドでサインアップ フローを開始します。
- `submitCode()` メソッドでワンタイム パスコードを送信します。
- `submitPassword()`メソッドでパスワードを送信します。 このエラーは、選択したサインアップ フローが電子メールとパスワードで行われる場合に処理します。

`signUp()` メソッドの結果として発生する可能性があるエラーの 1 つが`result.error?.isRedirectRequired()`。 このシナリオは、認証フローを完了するのにネイティブ認証だけでは十分ではない場合に発生します。 たとえば、承認サーバーにクライアントが提供できない機能が必要な場合です。 [ネイティブ認証 Web フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback)の詳細と、React アプリで [Web フォールバックをサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-javascript-sdk-web-fallback)する方法について説明します。

### 任意: サインアップ後に自動的にサインインする

ユーザーが正常にサインアップしたら、新しいサインイン フローを開始せずに、アプリに直接サインインできます。 これを行うには、次のコード スニペットを使用します。 [サインアップ/ページ.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) の完全な例を参照してください。

```typescript
if (signUpState instanceof SignUpCompletedState) {
    const result = await signUpState.signIn();
    const state = result.state;
    if (result.isFailed()) {
        setError(result.error?.errorData?.errorDescription || "An error occurred during auto sign-in");
    }
    
    if (result.isCompleted()) {
        setData(result.data);
        setSignUpState(state);
    }
}
```

### アプリを実行してテストする

1. ターミナル ウィンドウを開き、アプリのルート フォルダーに移動します。

    ```console
    cd reactspa
    ```
2. CORS プロキシ サーバーを起動するには、ターミナルで次のコマンドを実行します。

    ```console
    npm run cors
    ```
3. React アプリを起動するには、別のターミナル ウィンドウを開き、次のコマンドを実行します。

    ```console
    cd reactspa
    npm start
    ```
4. Web ブラウザーを開き、`http://localhost:3000/sign-up`に移動します。 サインアップ フォームが表示されます。
5. アカウントにサインアップするには、詳細を入力し、[ **続行** ] ボタンを選択し、プロンプトに従います。

次に、React アプリを更新してユーザーをサインインさせたり、ユーザーのパスワードをリセットしたりできます。

### next.config.js で poweredByHeader を false に設定する

既定では、 `x-powered-by` ヘッダーは HTTP 応答に含まれており、アプリケーションが Next.jsを使用していることを示します。 ただし、セキュリティまたはカスタマイズの理由から、このヘッダーを削除または変更することが必要になる場合があります。

```typescript
const nextConfig: NextConfig = {
  poweredByHeader: false,
  /* other config options here */
};
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-set-up-local-cors"} -->
## ネイティブ認証を使用して SPA のヘッダーを管理するように CORS プロキシ サーバーを設定する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-set-up-local-cors
- Service: identity-platform / external
- Article date: 2025-02-07
- Summary: ネイティブ認証 API を使用するシングルページ アプリケーション用に CORS プロキシ サーバーを設定する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、React シングルページ アプリ (SPA) からネイティブ認証 API と対話しながら CORS ヘッダーを管理するように CORS プロキシ サーバーを設定する方法について説明します。 CORS プロキシ サーバーは、ネイティブ認証 API がクロスオリジン リソース共有 (CORS) サポートできないことを解決するソリューションです。

このチュートリアルでは、次の操作を行います。

- CORS プロキシ サーバーを作成します。
- ネイティブ認証 API を呼び出す CORS プロキシ サーバーを設定します。
- React アプリを実行してテストします。

### [前提条件]

- 「[チュートリアル: ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sign-up)を使用してユーザーを外部テナントにサインインさせる React シングルページ アプリを作成する」の手順を完了します。

#### CORS プロキシ サーバーを作成する

1. React アプリのルート フォルダーに、*cors.js*という名前のファイルを作成し、次のコードを追加します。

    ```javascript
    const http = require("http");
    const https = require("https");
    const url = require("url");
    const proxyConfig = require("./proxy.config.js");
    
    http
    .createServer((req, res) => {
        const reqUrl = url.parse(req.url);
        const domain = url.parse(proxyConfig.proxy).hostname;
        if (reqUrl.pathname.startsWith(proxyConfig.localApiPath)) {
    
            const targetUrl = proxyConfig.proxy + reqUrl.pathname?.replace(proxyConfig.localApiPath, "") + (reqUrl.search || "");
    
            console.log("Incoming request -> " + req.url + " ===> " + reqUrl.pathname);
    
            const proxyReq = https.request(
                targetUrl,
                {
                    method: req.method,
                    headers: {
                        ...req.headers,
                        host: domain,
                    },
                },
                (proxyRes) => {
                    res.writeHead(proxyRes.statusCode, {
                        ...proxyRes.headers,
                        "Access-Control-Allow-Origin": "*",
                        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS",
                        "Access-Control-Allow-Headers": "Content-Type, Authorization",
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
    })
    .listen(proxyConfig.port, () => {
        console.log("CORS proxy running on http://localhost:3001");
        console.log("Proxying from " + proxyConfig.localApiPath + " ===> " + proxyConfig.proxy);
    });
    ```
2. React アプリのルート フォルダーに、*proxy.config.js*という名前のファイルを作成し、次のコードを追加します。

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
3. *package.json* ファイルを開いて、次のコマンドを *スクリプト* オブジェクトに追加します。

    ```json
    "cors": "node cors.js",
    ```

この時点で、React アプリと CORS プロキシ サーバーを実行する準備ができました。

### アプリを実行してテストする

1. ターミナル ウィンドウを開き、アプリのルート フォルダーに移動します。

    ```console
    cd reactspa
    ```
2. CORS プロキシ サーバーを起動するには、ターミナルで次のコマンドを実行します。

    ```console
    npm run cors
    ```
3. React アプリを起動するには、別のターミナル ウィンドウを開き、次のコマンドを実行します。

    ```console
    cd reactspa
    npm start
    ```
4. Web ブラウザーを開き、`http://localhost:3000/`に移動します。 サインアップ フォームが表示されます。
5. アカウントにサインアップするには、詳細を入力し、**サインアップ** ボタンを選択し、プロンプトに従います。

この時点で、ネイティブ認証 API を使用してユーザーをサインアップできる React アプリが正常に作成されました。 次に、React アプリを更新してユーザーをサインインさせたり、ユーザーのパスワードをリセットしたりできます。

### CORS プロキシ サーバーに関する追加情報

このチュートリアルでは、ローカル CORS サーバーを設定します。 ただし、[テスト環境で説明されているように、Azure 関数アプリを使用することで CORS ヘッダーを管理するようにリバース プロキシ サーバーを設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-cors-solution-test-environment)できます。

運用環境では、「[Azure Function App](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-cors-solution-production-environment) を使用して CORS プロキシ サーバーを設定することで、ネイティブ認証 API を使用するシングルページ アプリのリバース プロキシを設定する」の手順を使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-sign-in"} -->
## ネイティブ認証を使用して React SPA でユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sign-in
- Service: identity-platform / external
- Article date: 2025-11-17
- Summary: ネイティブ認証を使用して React シングルページ アプリのユーザーを外部テナントにサインインさせる React シングルページ アプリを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証を使用して React シングルページ アプリ (SPA) にユーザーをサインインさせる方法について説明します。

このチュートリアルでは、次の操作を行います。

- ユーザー名 (電子メール) とパスワードを使用してユーザーをサインインするように React アプリを更新します。
- サインイン フローをテストします。

### 前提条件

- 「[チュートリアル: ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-set-up-local-cors)の CORS ヘッダーを管理するための CORS プロキシ サーバーのセットアップ」の手順を完了します。

### アプリがネイティブ認証 API に対して行う呼び出しの種類を定義する

サインイン フロー中、アプリは、サインイン要求の開始、認証方法の選択、セキュリティ トークンの要求など、ネイティブ認証 API を複数回呼び出します。

これらの呼び出しを定義するには、*scr/client/RequestTypes.ts* ファイルを開き、次のコード スニペットを追加します。

```typescript
   export interface TokenRequestType {
        continuation_token: string;
        client_id: string;
        grant_type: string;
        scope: string;
        password?: string;
        oob?: string;
        challenge_type?: string;
    }

    // Sign in
    export interface TokenSignInType {
        continuation_token: string;
        grant_type: string;
        password?: string;
        oob?: string;
    }

    export interface ChallengeRequest {
        client_id: string;
        challenge_type: string;
        continuation_token: string;
    }

    export interface SignInStartRequest {
        client_id: string;
        challenge_type: string;
        username: string;
    }

    export interface SignInTokenRequest {
        client_id: string;
        grant_type: string;
        continuation_token: string;
        scope: string;
        challenge_type?: string;
        password?: string;
        oob?: string;
    }
```

### ネイティブ認証 API からアプリが受け取る応答の種類を定義する

サインイン操作のためにアプリがネイティブ認証 API から受信できる応答の種類を定義するには、*src/client/ResponseTypes.ts* ファイルを開き、次のコード スニペットを追加します。

```typescript
    export interface TokenResponseType {
        token_type: string;
        scope: string;
        expires_in: number;
        access_token: string;
        refresh_token: string;
        id_token: string;
    }
```

### サインイン要求を処理する

このセクションでは、サインイン フロー要求を処理するコードを追加します。 これらの要求の例としては、サインイン フローの開始、認証方法の選択、セキュリティ トークンの要求があります。

これを行うには、src/client/SignInService.ts という名前のファイルを作成し、次のコード スニペットを追加します。

```typescript
    import { CLIENT_ID, ENV } from "../config";
    import { postRequest } from "./RequestClient";
    import { ChallengeRequest, SignInStartRequest, TokenRequestType, TokenSignInType } from "./RequestTypes";
    import { TokenResponseType } from "./ResponseTypes";

    export const signInStart = async ({ username }: { username: string }) => {
        const payloadExt: SignInStartRequest = {
            username,
            client_id: CLIENT_ID,
            challenge_type: "password oob redirect",
        };

        return await postRequest(ENV.urlOauthInit, payloadExt);
    };

    export const signInChallenge = async ({ continuation_token }: { continuation_token: string }) => {
        const payloadExt: ChallengeRequest = {
            continuation_token,
            client_id: CLIENT_ID,
            challenge_type: "password oob redirect",
        };

        return await postRequest(ENV.urlOauthChallenge, payloadExt);
    };

    export const signInTokenRequest = async (request: TokenSignInType): Promise<TokenResponseType> => {
        const payloadExt: TokenRequestType = {
            ...request,
            client_id: CLIENT_ID,
            challenge_type: "password oob redirect",
            scope: "openid offline_access",
        };

        if (request.grant_type === "password") {
            payloadExt.password = request.password;
        }

        if (request.grant_type === "oob") {
            payloadExt.oob = request.oob;
        }

        return await postRequest(ENV.urlOauthToken, payloadExt);
    };
```

`challenge_type` プロパティは、クライアント アプリがサポートする認証方法を示します。 このアプリは、パスワードで電子メールを使用してサインインするため、チャレンジの種類の値は *パスワード oob リダイレクト*です。 [のチャレンジの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)について詳しく読む。

### UI コンポーネントを作成する

サインイン フロー中に、このアプリはユーザーの資格情報、ユーザー名 (電子メール)、パスワードを収集してユーザーをサインインさせます。 ユーザーが正常にサインインすると、アプリにユーザーの詳細が表示されます。

1. */pages/signin* という名前のフォルダーを*src* フォルダー内に作成します。
2. サインイン フォームを作成、表示、送信するには、src/pages/signin/SignIn.tsx ファイルを作成し、次のコードを追加します。

    ```typescript
        import React, { useState } from "react";
        import { Link as LinkTo, useNavigate } from "react-router-dom";
        import { signInStart, signInChallenge, signInTokenRequest } from "../../client/SignInService";
        import { ErrorResponseType } from "../../client/ResponseTypes";
    
        export const SignIn: React.FC = () => {
          const [email, setEmail] = useState<string>("");
          const [password, setPassword] = useState<string>("");
          const [error, setError] = useState<string>("");
          const [isLoading, setIsloading] = useState<boolean>(false);
    
          const navigate = useNavigate();
          const validateEmail = (email: string): boolean => {
            const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return re.test(String(email).toLowerCase());
          };
    
          const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
            e.preventDefault();
            if (!validateEmail(email)) {
              setError("Invalid email format");
              return;
            }
            setError("");
            setIsloading(true);
            try {
              const res1 = await signInStart({
                username: email,
              });
              const res2 = await signInChallenge({ continuation_token: res1.continuation_token });
              const res3 = await signInTokenRequest({
                continuation_token: res2.continuation_token,
                grant_type: "password",
                password: password,
              });
              navigate("/user", { state: res3 });
            } catch (err) {
              setError("An error has occured " + (err as ErrorResponseType).error_description);
            } finally {
              setIsloading(false);
            }
          };
    
          return (
            <div className="login-form">
              <form onSubmit={handleSubmit}>
                <h2>Login</h2>
                <div className="form-group">
                  <label>Email:</label>
                  <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                </div>
                <div className="form-group">
                  <label>Password:</label>
                  <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                </div>
                {error && <div className="error">{error}</div>}
                {isLoading && <div className="warning">Sending request...</div>}
                <button type="submit" disabled={isLoading}>Login</button>
              </form>
            </div>
          );
        };
    ```
3. サインインが成功した後にユーザーの詳細を表示するには:

    1. *client/Utils.ts* というファイルを作成し、次のコード スニペットを追加します。

        ```typescript
            export function parseJwt(token: string) {
                var base64Url = token.split(".")[1];
                var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
                var jsonPayload = decodeURIComponent(
                    window
                    .atob(base64)
                    .split("")
                    .map(function (c) {
                        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
                    })
                    .join("")
                );
                return JSON.parse(jsonPayload);
            }
        ```
    2. *src/pages* フォルダーで *user* というフォルダーを作成します。
    3. src/pages/user/UserInfo.tsx という名前のファイルを作成し、次のコード スニペットを追加します。

        ```typescript
        // User.tsx
        import React from "react";
        import { useLocation } from "react-router-dom";
        import { parseJwt } from "../../client/Utils";
        
        export const UserInfo: React.FC = () => {
          const { state } = useLocation();
          const decodedToken = parseJwt(state.access_token);
          const { given_name, scp, family_name, unique_name: email } = decodedToken;
        
          console.log(decodedToken);
          const familyName = family_name;
          const givenName = given_name;
          const tokenExpireTime = state.expires_in;
          const scopes = state.scope;
        
          return (
            <div className="user-info">
              <h2>User Information</h2>
              <div className="info-group">
                <label>Given Name:</label>
                <span>{givenName}</span>
              </div>
              <div className="info-group">
                <label>Family Name:</label>
                <span>{familyName}</span>
              </div>
              <div className="info-group">
                <label>Email:</label>
                <span>{email}</span>
              </div>
              <div className="info-group">
                <label>Token Expire Time:</label>
                <span>{tokenExpireTime}</span>
              </div>
              <div className="info-group">
                <label>Scopes:</label>
                <span>{scopes}</span>
              </div>
              <div className="info-group">
                <label>Token payload:</label>
                <span><pre>{JSON.stringify(decodedToken, null, 2)}</pre></span>
              </div>
            </div>
          );
        };
        ```

### アプリ ルートを追加する

*src/AppRoutes.tsx* ファイルを開き、その内容を次のコードに置き換えます。

```typescript
import { Route, Routes } from "react-router-dom";
import { SignIn } from "./pages/SignIn/SignIn";
import { UserInfo } from "./pages/User/UserInfo";
import { SignUp } from "./pages/SignUp/SignUp";
import { SignUpChallenge } from "./pages/SignUp/SignUpChallenge";
import { SignUpCompleted } from "./pages/SignUp/SignUpCompleted";
//For password reset
//import { ResetPassword } from "./pages/ResetAccount/ResetPassword";

export const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<SignUp />} />
      <Route path="/signin" element={<SignIn />} />
      <Route path="/user" element={<UserInfo />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/signup/challenge" element={<SignUpChallenge />} />
      <Route path="/signup/completed" element={<SignUpCompleted />} />
      //For password reset
      //<Route path="/reset" element={<ResetPassword />} />
    </Routes>
  );
};
```

### アプリを実行してテストする

[実行とテスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-set-up-local-cors#run-and-test-you-app) の手順に従ってアプリを実行しますが、今回は、先ほどサインアップしたユーザー アカウントを使用してサインイン フローをテストします。

### エイリアスまたはユーザー名を使用してサインインを有効にする

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、またはユーザー名として使用する別の識別子を指定できます。

Microsoft Entra 管理センターを使用してユーザー アカウントにユーザー名を手動で割り当てたり、Microsoft Graph API を使用してアプリで自動化したりできます。

[「エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を使用してサインインする」の記事の手順を使用して、ユーザーがアプリケーションでユーザー名を使用してサインインできるようにします。

1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、 [Microsoft Graph API を使用して、アプリでのユーザーの作成と更新を自動化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-sign-up"} -->
## ネイティブ認証を使用して React SPA でユーザーをサインアップする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sign-up
- Service: identity-platform / external
- Article date: 2025-02-07
- Summary: ネイティブ認証 API を使用してユーザーをサインアップする React シングルページ アプリケーションを構築する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証を使用してユーザーをサインアップする React シングルページ アプリを構築する方法について説明します。

このチュートリアルでは、次の操作を行います。

- React プロジェクトを作成します。
- アプリの UI コンポーネントを追加します。
- ユーザー名 (電子メール) とパスワードを使用してユーザーをサインアップするようにプロジェクトをセットアップします。

### [前提条件]

- [「クイック スタート: ネイティブ認証 API を使用してサンプルの React シングルページ アプリケーションでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-react-sign-in)する」の手順を完了します。 このクイックスタートでは、外部テナントを準備し、サンプルの React コード サンプルを実行する方法について説明します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコードエディター。
- [Node.js](https://nodejs.org/en/download/)。

### React プロジェクトを作成して依存関係をインストールする

コンピューター内の任意の場所で、次のコマンドを実行して、reactspa 名前の新しい React プロジェクトを作成し、プロジェクト フォルダーに移動してからパッケージをインストールします。

```console
npm config set legacy-peer-deps true
npx create-react-app reactspa --template typescript
cd reactspa
npm install ajv
npm install react-router-dom
npm install
```

### アプリの構成ファイルを追加する

src/config.js*という名前*ファイルを作成し、次のコードを追加します。

```typescript
// App Id obatained from the Microsoft Entra portal 
export const CLIENT_ID = "Enter_the_Application_Id_Here";

// URL of the CORS proxy server
const BASE_API_URL = `http://localhost:3001/api`;

// Endpoints URLs for Native Auth APIs
export const ENV = {
    urlSignupStart: `${BASE_API_URL}/signup/v1.0/start`,
    urlSignupChallenge: `${BASE_API_URL}/signup/v1.0/challenge`,
    urlSignupContinue: `${BASE_API_URL}/signup/v1.0/continue`,
}
```

- `Enter_the_Application_Id_Here` 値を見つけて、Microsoft Entra 管理センターに登録したアプリの **アプリケーション ID (clientId)** に置き換えます。
- `BASE_API_URL` は、このチュートリアル シリーズの後半で設定した、[クロスオリジン リソース共有 (CORS)](https://developer.mozilla.org/docs/Web/HTTP/CORS) プロキシ サーバーを指しています。 ネイティブ認証 API は CORS をサポートしていないため、REACT SPA とネイティブ認証 API の間に CORS プロキシ サーバーを設定して CORS ヘッダーを管理します。

### ネイティブ認証 API を呼び出して応答を処理するように React アプリを設定する

ネイティブ認証 API を使用してサインアップ フローなどの認証フローを完了するために、アプリは calla dn で応答を処理します。 たとえば、アプリはサインアップ フローを開始し、応答を待ってからユーザー属性を送信し、ユーザーが正常にサインアップされるまでもう一度待機します。

#### ネイティブ認証 API へのクライアント呼び出しを設定する

このセクションでは、ネイティブ認証を呼び出し、応答を処理する方法を定義します。

1. *src*に *クライアント* というフォルダーを作成します。
2. scr/client/RequestClient.ts *という名前*ファイルを作成し、次のコード スニペットを追加します。

    ```typescript
    import { ErrorResponseType } from "./ResponseTypes";
    
    export const postRequest = async (url: string, payloadExt: any) => {
    const body = new URLSearchParams(payloadExt as any);
    
    const response = await fetch(url, {
        method: "POST",
        headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        },
        body,
    });
    
    if (!response.ok) {
        try {
        const errorData: ErrorResponseType = await response.json();
        throw errorData;
        } catch (jsonError) {
        const errorData = {
            error: response.status,
            description: response.statusText,
            codes: [],
            timestamp: "",
            trace_id: "",
            correlation_id: "",
        };
        throw errorData;
        }
    }
    
    return await response.json();
    };
    ```

    このコードでは、アプリがネイティブ認証 API を呼び出し、応答を処理する方法を定義します。 アプリが認証フローを開始する必要がある場合は常に、URL とペイロード データを指定して `postRequest` 関数を使用します。

#### アプリがネイティブ認証 API に対して行う呼び出しの種類を定義する

サインアップ フロー中に、アプリはネイティブ認証 API を複数回呼び出します。

これらの呼び出しを定義するには、scr/client/RequestTypes.ts という名前のファイルを作成し、次のコード スニペットを追加します。

```typescript
    //SignUp 
    export interface SignUpStartRequest {
        client_id: string;
        username: string;
        challenge_type: string;
        password?: string;
        attributes?: Object;
    }
    
    export interface SignUpChallengeRequest {
        client_id: string;
        continuation_token: string;
        challenge_type?: string;
    }
    
    export interface SignUpFormPassword {
        name: string;
        surname: string;
        username: string;
        password: string;
    }
    
    //OTP
    export interface ChallengeForm {
        continuation_token: string;
        oob?: string;
        password?: string;
    }
```

#### ネイティブ認証 API からアプリが受け取る応答の種類を定義する

サインアップ操作のネイティブ認証 API からアプリが受信できる応答の種類を定義するには、src/client/ResponseTypes.ts *という名前のファイル*作成し、次のコード スニペットを追加します。

```typescript
    export interface SuccessResponseType {
    continuation_token?: string;
    challenge_type?: string;
    }
    
    export interface ErrorResponseType {
        error: string;
        error_description: string;
        error_codes: number[];
        timestamp: string;
        trace_id: string;
        correlation_id: string;
    }
        
    export interface ChallengeResponse {
        binding_method: string;
        challenge_channel: string;
        challenge_target_label: string;
        challenge_type: string;
        code_length: number;
        continuation_token: string;
        interval: number;
    }
```

#### サインアップ要求を処理する

このセクションでは、サインアップ フロー要求を処理するコードを追加します。 これらの要求の例としては、サインアップ フローの開始、認証方法の選択、ワンタイム パスコードの送信があります。

これを行うには、src/client/SignUpService.ts という名前のファイルを作成し、次のコード スニペットを追加します。

```typescript
import { CLIENT_ID, ENV } from "../config";
import { postRequest } from "./RequestClient";
import { ChallengeForm, SignUpChallengeRequest, SignUpFormPassword, SignUpStartRequest } from "./RequestTypes";
import { ChallengeResponse } from "./ResponseTypes";

//handle start a sign-up flow
export const signupStart = async (payload: SignUpFormPassword) => {
const payloadExt: SignUpStartRequest = {
    attributes: JSON.stringify({
    given_name: payload.name,
    surname: payload.surname,
    }),
    username: payload.username,
    password: payload.password,
    client_id: CLIENT_ID,
    challenge_type: "password oob redirect",
};

return await postRequest(ENV.urlSignupStart, payloadExt);
};

//handle selecting an authentication method
export const signupChallenge = async (payload: ChallengeForm):Promise<ChallengeResponse> => {
    const payloadExt: SignUpChallengeRequest = {
        client_id: CLIENT_ID,
        challenge_type: "password oob redirect",
        continuation_token: payload.continuation_token,
    };

    return await postRequest(ENV.urlSignupChallenge, payloadExt);
};

//handle submit one-time passcode
export const signUpSubmitOTP = async (payload: ChallengeForm) => {
    const payloadExt = {
        client_id: CLIENT_ID,
        continuation_token: payload.continuation_token,
        oob: payload.oob,
        grant_type: "oob",
    };

    return await postRequest(ENV.urlSignupContinue, payloadExt);
};
```

`challenge_type` プロパティは、クライアント アプリがサポートする認証方法を示します。 このアプリ サインでは電子メールとパスワードが使用されるため、チャレンジの種類の値は *password oob redirect* です。 [のチャレンジの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)について詳しく読む。

#### UI コンポーネントを作成する

このアプリは、指定された名前、ユーザー名 (電子メール)、パスワード、ワンタイム パスコードなどのユーザーの詳細をユーザーから収集します。 そのため、アプリにはサインアップとワンタイム パスコード収集フォームが必要です。

1. *src* フォルダーの中に、*/pages/SignUp* という名前のフォルダーを作成します。
2. サインアップ フォームを作成、表示、送信するには、src/pages/SignUp/SignUp.tsx ファイルを作成し、次のコードを追加します。

    ```typescript
        import React, { useState } from 'react';
        import { signupChallenge, signupStart } from '../../client/SignUpService';
        import { useNavigate } from 'react-router-dom';
        import { ErrorResponseType } from "../../client/ResponseTypes";
    
        export const SignUp: React.FC = () => {
            const [name, setName] = useState<string>('');
            const [surname, setSurname] = useState<string>('');
            const [email, setEmail] = useState<string>('');
            const [error, setError] = useState<string>('');
            const [isLoading, setIsloading] = useState<boolean>(false);
            const navigate = useNavigate();
            const validateEmail = (email: string): boolean => {
              const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
              return re.test(String(email).toLowerCase());
            };
    
            const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
              e.preventDefault();
              if (!name || !surname || !email) {
                setError('All fields are required');
                return;
              }
              if (!validateEmail(email)) {
                setError('Invalid email format');
                return;
              }
              setError('');
              try {
                setIsloading(true);
                const res1 = await signupStart({ name, surname, username: email, password });
                const res2 = await signupChallenge({ continuation_token: res1.continuation_token });
                navigate('/signup/challenge', { state: { ...res2} });
              } catch (err) {
                setError("An error occurred during sign up " + (err as ErrorResponseType).error_description);
              } finally {
                setIsloading(false);
              }
            };
    
            return (
              <div className="sign-up-form">
                <form onSubmit={handleSubmit}>
                  <h2>Sign Up</h2>
                  <div className="form-group">
                    <label>Name:</label>
                    <input
                      type="text"
                      value={name}
                      onChange={(e) => setName(e.target.value)}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>Last Name:</label>
                    <input
                      type="text"
                      value={surname}
                      onChange={(e) => setSurname(e.target.value)}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>Email:</label>
                    <input
                      type="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </div>
                  {error && <div className="error">{error}</div>}
                  {isLoading && <div className="warning">Sending request...</div>}
                  <button type="submit">Sign Up</button>
                </form>
              </div>
            );
          };
    ```
3. ワンタイム パスコード フォームを作成、表示、送信するには、src/pages/signup/SignUpChallenge.tsx ファイルを作成し、次のコードを追加します。

    ```typescript
    import React, { useState } from "react";
    import { useNavigate, useLocation } from "react-router-dom";
    import { signUpSubmitOTP } from "../../client/SignUpService";
    import { ErrorResponseType } from "../../client/ResponseTypes";
    
    export const SignUpChallenge: React.FC = () => {
      const { state } = useLocation();
      const navigate = useNavigate();
      const { challenge_target_label, challenge_type, continuation_token, code_length } = state;
    
      const [code, setCode] = useState<string>("");
      const [error, setError] = useState<string>("");
      const [isLoading, setIsloading] = useState<boolean>(false);
    
      const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!code) {
          setError("All fields are required");
          return;
        }
    
        setError("");
        try {
          setIsloading(true);
          const res = await signUpSubmitOTP({ continuation_token, oob: code });
          navigate("/signup/completed");
        } catch (err) {
          setError("An error occurred during sign up " + (err as ErrorResponseType).error_description);
        } finally {
          setIsloading(false);
        }
      };
    
      return (
        <div className="sign-up-form">
          <form onSubmit={handleSubmit}>
            <h2>Insert your one time code received at {challenge_target_label}</h2>
            <div className="form-group">
              <label>Code:</label>
              <input maxLength={code_length} type="text" value={code} onChange={(e) => setCode(e.target.value)} required />
            </div>
            {error && <div className="error">{error}</div>}
            {isLoading && <div className="warning">Sending request...</div>}
            <button type="submit">Sign Up</button>
          </form>
        </div>
      );
    };
    ```
4. src/pages/signup/SignUpCompleted.tsx ファイルを作成し、次のコードを追加します。

    ```typescript
    import React from 'react';
    import { Link } from 'react-router-dom';
    
    export const SignUpCompleted: React.FC = () => {
      return (
        <div className="sign-up-completed">
          <h2>Sign Up Completed</h2>
          <p>Your sign-up process is complete. You can now log in.</p>
          <Link to="/signin" className="login-link">Go to Login</Link>
        </div>
      );
    };
    ```

    このページには、成功メッセージと、ユーザーが正常にサインアップした後にサインイン ページに移動するためのボタンが表示されます。
5. *src/App.tsx* ファイルを開き、その内容を次のコードに置き換えます。

    ```typescript
    import React from "react";
    import { BrowserRouter, Link } from "react-router-dom";
    import "./App.css";
    import { AppRoutes } from "./AppRoutes";
    
    function App() {
      return (
        <div className="App">
          <BrowserRouter>
            <header>
              <nav>
                <ul>
                  <li>
                    <Link to="/signup">Sign Up</Link>
                  </li>
                  <li>
                    <Link to="/signin">Sign In</Link>
                  </li>
                  <li>
                    <Link to="/reset">Reset Password</Link>
                  </li>
                </ul>
              </nav>
            </header>
            <AppRoutes />
          </BrowserRouter>
        </div>
      );
    }
    
    export default App;
    ```
6. React アプリを正しく表示するには:

    1. *src/App.css* ファイルを開き、`App-header` クラスに次のプロパティを追加します。

        ```css
        min-height: 100vh;
        ```
    2. *src/Index.css* ファイルを開いて、中身を [src/index.css](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/API/React/ReactAuthSimple/src/index.css) のコードに置き換えます

#### アプリ ルートを追加する

src/AppRoutes.tsx という名前のファイルを作成し、次のコードを追加します。

```typescript
import { Route, Routes } from "react-router-dom";
import { SignUp } from "./pages/SignUp/SignUp";
import { SignUpChallenge } from "./pages/SignUp/SignUpChallenge";
import { SignUpCompleted } from "./pages/SignUp/SignUpCompleted";

export const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<SignUp />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/signup/challenge" element={<SignUpChallenge />} />
      <Route path="/signup/completed" element={<SignUpCompleted />} />
   
    </Routes>
  );
};
```

この時点で、React アプリはネイティブ認証 API にサインアップ要求を送信できますが、CORS ヘッダーを管理するように CORS プロキシ サーバーを設定する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-single-page-app-react-social-sign-in"} -->
## ネイティブ認証 JS SDK を使用した React SPA でのソーシャル サインインのサポート - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-social-sign-in
- Service: identity-platform / external
- Article date: 2026-04-10
- Summary: ネイティブ認証 JavaScript SDK を使用して、Apple、Facebook、Google、およびカスタム OIDC ID プロバイダーによるソーシャル サインインを React SPA に追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、外部テナント用のネイティブ認証 JavaScript SDK を使用して、ユーザーが、Apple、Facebook、Google、カスタム OIDC ソーシャル ID プロバイダーなどの既存のソーシャル アカウントでサインアップとサインインできるようにする方法について説明します。

このチュートリアルでは、次の操作を行います。

- リダイレクト URI を設定するようにアプリ構成を更新します。
- フェデレーション ID プロバイダーのボタンを追加して、サインイン フォームとサインアップ フォームに追加します。
- フェデレーション ID プロバイダーとのサインインとサインアップを処理します。
- ソーシャル サインイン フローをテストします。

### 前提条件

- [サインアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sign-up)、[サインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sign-in)、[パスワードリセット](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-reset-password)、[強力な認証方法の登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-register-strong-method)、MFA チュートリアルの[有効化](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-enable-mfa)の手順を完了します。
- [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) または別のコード エディター。
- [Node.jsバージョン20.x以降](https://nodejs.org/en/download/)
- 有効にするフェデレーション ID プロバイダーを構成します。 選択したプロバイダーの[IDプロバイダー 外部テナント用の](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)手順に従います。
    - [りんご](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)
    - [フェイスブック](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)
    - [グーグル](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)
    - [カスタム OIDC ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers)

### 構成を更新してリダイレクト URI を設定する

リダイレクト URI が  インターフェイスで構成されていること、およびその値が、Microsoft Entra 管理センターのアプリ登録で構成いずれかのリダイレクト URI と一致していることを確認します。

1. *src/config/auth-config.ts* ファイルを見つけます。
2. `auth` オブジェクトで、`redirectUri` プロパティを追加または更新し、その値が、Microsoft Entra 管理センターのアプリ登録で構成されているリダイレクト URI のいずれかと一致していることを確認します。

    ```typescript
    const customAuthConfig: CustomAuthConfiguration = {
        auth: {
            ...
            redirectUri: "/",
            ...
        },
        ...
    };
    ```

### UI コンポーネントを作成する

このセクションでは、フェデレーション ID プロバイダー ボタンをサインインフォームとサインアップ フォームに追加し、ユーザーがソーシャル ID プロバイダー (Apple、Facebook、Google) またはカスタム OIDC ID プロバイダー (LinkedIn など) で認証できるようにします。

#### サインイン初期フォームを更新する

フェデレーション ID プロバイダーのボタンを含むように、サインイン `InitialForm.tsx` コンポーネントを更新します。 完全な例は、[InitialForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-in/components/InitialForm.tsx) にあります。

1. `src/app/sign-in/components/InitialForm.tsx`を開き、ソーシャル プロバイダーボタンを含むようにコンポーネントを更新します。

    ```typescript
    import type { SignInInitialFormProps } from "../types/formProperties";
    
    const socialProviders = [
        {
            name: "Google",
            domainHint: "Google",
        },
        {
            name: "Facebook",
            domainHint: "Facebook",
        },
        {
            name: "Apple",
            domainHint: "Apple",
        },
        {
            name: "LinkedIn",
            domainHint: "www.linkedin.com",
        },
    ];
    
    export const InitialForm = ({
        onSubmit,
        username,
        setUsername,
        loading,
        onSignInWithSocial,
    }: SignInInitialFormProps) => (
        <form onSubmit={onSubmit} style={styles.form}>
            ...
    
            <div style={styles.separator}>
                <div style={styles.separatorLine}></div>
                <span style={styles.separatorText}>OR</span>
                <div style={styles.separatorLine}></div>
            </div>
    
            {socialProviders.map((provider) => (
                <button
                    key={provider.domainHint}
                    type="button"
                    style={styles.socialButton}
                    onClick={() => onSignInWithSocial(provider.domainHint)}
                >
                    <span>Sign In with {provider.name}</span>
                </button>
            ))}
        </form>
    );
    ```
2. `SignInInitialFormProps`で`src/app/sign-in/types/formProperties.ts` インターフェイスを更新します。

    ```typescript
    import { FormProps } from "@/app/shared/types/formProperties";
    
    export interface SignInInitialFormProps extends FormProps {
        ...
        onSignInWithSocial: (domainHint: string) => Promise<void>;
    }
    ```

#### サインアップ初期フォームを更新する

同様に、サインアップ `InitialForm.tsx` コンポーネントを更新します。 完全な例は、[InitialForm.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/components/InitialForm.tsx) にあります。

1. `src/app/sign-up/components/InitialForm.tsx`を開き、ソーシャル プロバイダーの配列とボタンを追加します。 サインイン `InitialForm.tsx`と同じ構造を使用しますが、クリック ハンドラーを更新して `onSignUpWithSocial` を呼び出し、ボタンのテキストを "サインアップ" と表示するように更新します。
2. `SignUpInitialFormProps`で`src/app/sign-up/types/formProperties.ts` インターフェイスを更新します。

    ```typescript
    import { FormProps } from "@/app/shared/types/formProperties";
    
    export interface SignUpInitialFormProps extends FormProps {
        ...
        onSignUpWithSocial: (domainHint: string) => Promise<void>;
    }
    ```

### フォームの操作を処理する

このセクションでは、フェデレーション ID プロバイダーとのサインインとサインアップを処理するロジックを実装します。 実装では、MSAL の`loginPopup` メソッドと、`PopupRequest` プロパティを含む`domainHint`を使用します。 このプロパティは、使用するフェデレーション ID プロバイダーを指定します。 `domainHint`構成と発行者アクセラレーションの詳細については、「[外部 ID の ID プロバイダー」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)参照してください。

#### フェデレーション ID プロバイダーをサポートするようにサインアップ ページを更新する

フェデレーション ID プロバイダーによる認証を処理するようにサインアップ `page.tsx` を更新します。 完全な例については、[page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-up/page.tsx) を参照してください。

1. 必要な型をインポートします。

    ```typescript
    import { PopupRequest } from "@azure/msal-browser";
    ```
2. サインアップ `page.tsx`にフェデレーション ID プロバイダーサインアップのハンドラー関数を追加します。

    ```typescript
    const startSignUpWithSocial = async (domainHint: string) => {
        setError("");
        setLoading(false);
    
        if (!authClient) return;
    
        const popUpRequest: PopupRequest = {
            authority: customAuthConfig.auth.authority,
            scopes: [],
            redirectUri: customAuthConfig.auth.redirectUri || "",
            prompt: "login",
            domainHint: domainHint,
        };
    
        try {
            await authClient.loginPopup(popUpRequest);
    
            const accountResult = authClient.getCurrentAccount();
    
            if (accountResult.isFailed()) {
                setError(
                    accountResult.error?.errorData?.errorDescription ??
                        "An error occurred while getting the account from cache"
                );
            }
    
            if (accountResult.isCompleted()) {
                setData(accountResult.data);
                setSignInState(true);
            }
        } catch (error) {
            if (error instanceof Error) {
                setError(error.message);
            } else {
                setError("An unexpected error occurred while logging in with popup");
            }
        }
    };
    ```
3. `renderForm()`関数を更新して、ハンドラーを`InitialForm` コンポーネントに渡します。

    ```typescript
    const renderForm = () => {
    
        ... other state checks ...
    
        if (!signUpState) {
            return (
                <InitialForm
                    ...
                    onSignUpWithSocial={startSignUpWithSocial}
                />
            );
        }
    };
    ```

#### フェデレーション ID プロバイダーをサポートするようにサインイン ページを更新する

フェデレーション ID プロバイダーによる認証を処理するようにサインイン `page.tsx` を更新します。 完全な例については、[page.tsx](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/blob/main/typescript/native-auth/react-nextjs-sample/src/app/sign-in/page.tsx) を参照してください。

1. 必要な型をインポートします。

    ```typescript
    import { PopupRequest } from "@azure/msal-browser";
    ```
2. サインイン `page.tsx`でフェデレーション ID プロバイダーサインインのハンドラー関数を追加します。

    ```typescript
    const startSignInWithSocial = async (domainHint: string) => {
        setError("");
        setLoading(false);
    
        if (!authClient) return;
    
        const popUpRequest: PopupRequest = {
            authority: customAuthConfig.auth.authority,
            scopes: [],
            redirectUri: customAuthConfig.auth.redirectUri || "",
            prompt: "login",
            domainHint: domainHint,
        };
    
        try {
            await authClient.loginPopup(popUpRequest);
    
            const accountResult = authClient.getCurrentAccount();
    
            if (accountResult.isFailed()) {
                setError(
                    accountResult.error?.errorData?.errorDescription ??
                        "An error occurred while getting the account from cache"
                );
            }
    
            if (accountResult.isCompleted()) {
                setData(accountResult.data);
                setCurrentSignInStatus(true);
            }
        } catch (error) {
            if (error instanceof Error) {
                setError(error.message);
            } else {
                setError("An unexpected error occurred while logging in with popup");
            }
        }
    };
    ```
3. `renderForm()`関数を更新して、ハンドラーを`InitialForm` コンポーネントに渡します。

    ```typescript
    const renderForm = () => {
    
        ... other state checks ...
    
        return (
            <InitialForm
                ...
                onSignInWithSocial={startSignInWithSocial}
            />
        );
    };
    ```

注

Microsoft Entra アカウントとMicrosoft アカウント (MSA) ID プロバイダーは現在サポートされていません。

#### PopupRequest 構成の詳細

フェデレーション ID プロバイダー認証の `PopupRequest` を構成する場合:

- **機関**: 構成済みの外部テナント機関を使用します。
- **redirectUri**: アプリ登録で構成したリダイレクト URI。
- **prompt**: `"login"` に設定して、ユーザーに資格情報の入力を強制します。
- **domainHint**: 使用するフェデレーション ID プロバイダーを決定するキー パラメーター。

`loginPopup`メソッドは、選択したフェデレーション ID プロバイダーを使用してユーザーが認証フローを完了するポップアップ ウィンドウを開きます。 認証が成功すると、ポップアップが自動的に閉じられ、アカウント情報がアプリで使用できるようになります。

### アプリを実行してテストする

アプリをテストする前に、CORS プロキシとアプリが実行されていることを確認します。

1. CORS プロキシが実行されていることを確認します。

    ```console
    npm run cors
    ```
2. アプリケーションを起動します。

    ```console
    npm run dev
    ```

#### フェデレーション ID プロバイダーでのサインアップをテストする

1. `http://localhost:3000/sign-up`に移動して、サインアップ フォームを表示します。
2. 認証するフェデレーション ID プロバイダーのボタン ( **Google へのサインアップ**など) を選択します。 ポップアップ ウィンドウが開き、Google 認証ページにリダイレクトされます。
3. Google アカウントの資格情報でサインインします (または、必要に応じて新しい Google アカウントを作成します)。
4. プロンプトが表示されたら、必要なアクセス許可を付与します。

認証が成功した後、サインアップ時に追加のユーザー属性を収集するようにテナントが構成されている場合は、属性の収集を完了することが必要になる場合があります。 詳細については、「 [サインアップ時にユーザー属性を収集する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes)」を参照してください。

ポップアップ ウィンドウが自動的に閉じます。 サインインすると、アカウント情報がアプリに表示されます。 Google プロファイルの情報を使用して、外部テナントに新しいユーザー アカウントが作成されます。

#### フェデレーション ID プロバイダーを使用したサインインのテスト

1. `http://localhost:3000/sign-in`に移動して、サインイン フォームを表示します。
2. 認証に使用するフェデレーション ID プロバイダーのボタン ( **Google でのサインイン**など) を選択します。 ポップアップ ウィンドウが開き、Google 認証ページにリダイレクトされます。
3. Google アカウントの資格情報でサインインします。 この ID プロバイダーで初めてサインインする場合は、アプリケーションとの情報の共有に同意するように求められる場合があります。

認証が成功した後、サインアップ時に追加のユーザー属性を収集するようにテナントが構成されている場合は、属性の収集を完了することが必要になる場合があります。 詳細については、「 [サインアップ時にユーザー属性を収集する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes)」を参照してください。

ポップアップ ウィンドウが自動的に閉じます。 これでサインインし、アプリにアカウント情報が表示されます。

#### 多要素認証

SMS または電子メール ワンタイム パスコード (OTP) MFA が有効になっている場合、ソーシャル ID プロバイダーの認証が完了した後、ブラウザーで委任されたユーザー エクスペリエンスに MFA チャレンジが提示されます。

MFA の有効化の詳細については、「 [外部テナントでの多要素認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers) 」および「 [アプリへの多要素認証の追加」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)参照してください。

### 一般的なエラーのトラブルシューティング

このセクションを使用して、フェデレーション ID プロバイダーを統合するときに発生する可能性がある一般的な問題を解決します。

#### ブラウザーによってポップアップ ウィンドウがブロックされる

`loginPopup`メソッドにはブラウザー ポップアップが必要です。 ポップアップがブロックされている場合、認証フローは通知されずに失敗するか、エラーが発生します。

**解決策**: ブラウザーのポップアップ ブロック設定を確認し、アプリケーションのドメインからのポップアップを許可します (たとえば、 `localhost:3000`)。 ユーザーに同じ操作を行うよう指示します。 ほとんどのブラウザーでは、ポップアップがブロックされると、アドレス バーに通知が表示されます。

#### ドメイン ヒントが認識されない

フェデレーション ID プロバイダーの認証ページが表示されないか、 `domainHint` 値が無効であることを示すエラーが表示されます。

**解決策**: `domainHint`の`PopupRequest`値が、外部テナントで構成したものと正確に一致することを確認します。 次の値を使用します。

| プロバイダー | 予期される`domainHint`値 |
| --- | --- |
| 林檎 | `"Apple"` |
| フェイスブック | `"Facebook"` |
| Google | `"Google"` |
| カスタム OIDC (例: LinkedIn) | 構成した発行者 URI (例: `"www.linkedin.com"` |

#### ポップアップが開いた後に認証が失敗する

ポップアップが開き、ID プロバイダーにリダイレクトされますが、認証は完了しません。 ブラウザー コンソールでエラー メッセージを確認します。

**解決策**: 次の構成を確認します。

1. `redirectUri` の`PopupRequest`は、Microsoft Entra 管理センターのアプリ登録に登録されているリダイレクト URI のいずれかに一致します。
2. アプリ構成のクライアント ID が正しい。
3. フェデレーション ID プロバイダーは、外部テナントで適切に構成され、関連するユーザー フローに追加されます。

#### サインアップ後にユーザー アカウントが作成されない

ユーザーはフェデレーション ID プロバイダー認証を完了しますが、テナントに新しいアカウントは作成されません。

**解決策**: 外部テナントでのサインアップとサインインの両方のシナリオで、フェデレーション ID プロバイダーがユーザー フローで構成され、有効になっていることを確認します。 ユーザーが初めてフェデレーション ID プロバイダーでサインインすると、新しいアカウントが自動的に作成され、そのプロバイダーにリンクされます。 同じプロバイダーを使用する後続のサインインでは、既存のアカウントが使用されます。

#### CORS 関連のエラーがコンソールに表示される

ブラウザー コンソールに `Access-Control-Allow-Origin` または同様の CORS エラーが表示されます。

**解決策**: CORS プロキシが正しく実行されていることを確認します。 `npm run cors`でプロキシを再起動し、再試行する前にアクセス可能かどうかを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-app-javascript-configure-authentication"} -->
## チュートリアル: JavaScript シングルページ アプリ (SPA) にサインインフローとサインアウト フローを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-configure-authentication
- Service: identity-platform
- Article date: 2024-02-11
- Summary: Microsoft ID プラットフォームを使用して JavaScript シングルページ アプリ (SPA) に認証を追加する方法について説明します。

**適用対象**: [Image: 白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: 白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細はこちら](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、認証用に JavaScript シングルページ アプリケーション (SPA) を構成します。 このシリーズ [のパート 1](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-prepare-app)では、JavaScript SPA を作成し、認証用に準備しました。 このチュートリアルでは、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) コンポーネントをアプリに追加し、アプリの応答性の高いユーザー インターフェイス (UI) を構築することで、認証フローを追加する方法について説明します。

このチュートリアルでは、

- 認証フローを処理するコードを *auth.js* に追加する
- アプリケーションのユーザー インターフェイスを構築する

### 前提条件

- [チュートリアル: 認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-prepare-app)用に JavaScript シングルページ アプリケーションを準備します。

### リダイレクト ファイルにコードを追加する

認証フローは、アプリケーションがユーザーを認証するために実行する一連の手順です。 *Auth.js* には、認証フローの処理に使用される関数が含まれています。これには、サインインや、リダイレクトまたはポップアップメソッドを使用したサインアウトが含まれます。

1. パブリック/auth.js 開き、次のコードを追加します。

    ```javascript
    // Browser check variables: If you support IE, our recommendation is to sign-in using Redirect APIs. If you are testing using Edge InPrivate mode, please add "isEdge" to the if check.
    const ua = window.navigator.userAgent;
    const msie = ua.indexOf("MSIE ");
    const msie11 = ua.indexOf("Trident/");
    const msedge = ua.indexOf("Edge/");
    const isIE = msie > 0 || msie11 > 0;
    const isEdge = msedge > 0;
    
    let signInType;
    let accountId = "";
    
    // myMSALObj instance - configuration parameters are located at authConfig.js
    const myMSALObj = new msal.PublicClientApplication(msalConfig);
    
    myMSALObj.initialize().then(() => {
        // Redirect: once login is successful and redirects with tokens, call Graph API
        myMSALObj.handleRedirectPromise().then(handleResponse).catch(err => {
            console.error(err);
        });
    })
    
    function selectAccount() {
    
        /**
        * See here for more info on account retrieval: 
        * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-common/docs/Accounts.md
        */
    
        const currentAccounts = myMSALObj.getAllAccounts();
    
        if (!currentAccounts) {
            return;
        } else if (currentAccounts.length > 1) {
            // Add your account choosing logic here
            console.warn("Multiple accounts detected.");
        } else if (currentAccounts.length === 1) {
            username = currentAccounts[0].username
            showWelcomeMessage(currentAccounts[0].username);
            updateTable(currentAccounts[0]);
        }
    }
    
    function handleResponse(resp) {
        if (resp !== null) {
            accountId = resp.account.homeAccountId;
            myMSALObj.setActiveAccount(resp.account);
            showWelcomeMessage(resp.account);
        } else {
                selectAccount();
            } 
        }
    
    async function signIn(method) {
        signInType = isIE ? "redirect" : method;
        if (signInType === "popup") {
            return myMSALObj.loginPopup({
                ...loginRequest,
                redirectUri: "/redirect"
            }).then(handleResponse).catch(function (error) {
                console.log(error);
            });
        } else if (signInType === "redirect") {
            return myMSALObj.loginRedirect(loginRequest)
        }
    }
    
    function signOut(interactionType) {
        const logoutRequest = {
            account: myMSALObj.getAccountByHomeId(accountId)
        };
    
        if (interactionType === "popup") {
            myMSALObj.logoutPopup(logoutRequest).then(() => {
                window.location.reload();
            });
        } else {
            myMSALObj.logoutRedirect(logoutRequest);
        }
    }
    
    // This function can be removed if you do not need to support IE
    async function getTokenRedirect(request, account) {
        return await myMSALObj.acquireTokenSilent(request).catch(async (error) => {
            console.log("silent token acquisition fails.");
            if (error instanceof msal.InteractionRequiredAuthError) {
                // fallback to interaction when silent call fails
                console.log("acquiring token using redirect");
                myMSALObj.acquireTokenRedirect(request);
            } else {
                console.error(error);
            }
        });
    }
    ```
2. ファイルを保存します。

### アプリケーションのユーザー インターフェイスを構築する

承認が構成されている場合は、プロジェクトの実行時にアプリケーションと対話するための UI を作成できます。 [Bootstrap](https://getbootstrap.com) を使用して、**サインイン** と [サインアウト **] ボタン** 含む応答性の高い UI を作成します。 UI には、トークンからの要求を表示するテーブルも含まれています。これは、チュートリアルの後半で追加されます。

#### *index.html* ファイルにコードを追加する

SPA のメイン ページである *index.html* は、アプリの起動時に読み込まれる最初のページです。 また、ユーザーが **[サインアウト]** ボタンを選択したときに読み込まれるページでもあります。 このページには、ナビゲーション バー、ユーザーの電子メールを含むウェルカム メッセージ、トークンからの要求を表示するテーブルが含まれています。

1. *public/index.html* を開き、次のコード スニペットを追加します。

    ```html
     <!DOCTYPE html>
     <html lang="en">
    
     <head>
         <meta charset="UTF-8">
         <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
         <title>Microsoft identity platform</title>
         <link rel="SHORTCUT ICON" href="./favicon.svg" type="image/x-icon">
         <link rel="stylesheet" href="./styles.css">
    
         <!-- adding Bootstrap 5 for UI components  -->
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
             integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
         <!-- msal.min.js can be used in the place of msal-browser.js -->
         <script src="/msal-browser.min.js"></script>
     </head>
    
     <!DOCTYPE html>
     <html lang="en">
     <head>
     <meta charset="UTF-8">
     <title>Bootstrap 5 Navbar</title>
     <!-- Bootstrap 5 CSS -->
     <link 
         href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" 
         rel="stylesheet" 
         integrity="sha384-Zenh87qX5JnK2Rl3l94faQlfdS3b8SpxyDkgOn+Y5Qu3og6JpNZnN9LfX9k8wAI5" 
         crossorigin="anonymous">
     </head>
    
     <body>
     <!-- Navbar -->
     <nav class="navbar navbar-expand-sm navbar-dark bg-primary navbarStyle">
         <a class="navbar-brand" href="/">Microsoft identity platform</a>
    
         <div class="ms-auto d-flex align-items-center">
         <!-- Dropdown group (Bootstrap 5 uses dropstart instead of dropleft) -->
         <div class="btn-group dropstart">
             <!-- Toggle button for dropdown -->
             <button
             id="signIn"
             type="button"
             class="btn btn-primary dropdown-toggle"
             data-bs-toggle="dropdown"
             aria-expanded="false"
             >
             Sign In
             </button>
    
             <!-- Dropdown menu -->
             <div class="dropdown-menu">
             <button class="dropdown-item" id="popup" onclick="signIn(this.id)">
                 Sign in using Popup
             </button>
             <button class="dropdown-item" id="redirect" onclick="signIn(this.id)">
                 Sign in using Redirect
             </button>
             </div>
         </div>
    
         <!-- Sign Out button -->
         <button class="btn btn-secondary ms-2" id="signOut" onclick="signOut()">
             Sign Out
         </button>
         </div>
     </nav>

     <br />
     <div class="container">
         <div class="row">
         <h5 id="title-div" class="card-header text-center">
             JavaScript single-page application secured with MSAL.js
         </h5>
         <br />
         <h5 id="welcome-div" class="card-header text-center d-none"></h5>
    
         <table class="table table-striped table-bordered d-none" id="table-div">
             <thead>
             <tr>
                 <th>Claim Type</th>
                 <th>Value</th>
                 <th>Description</th>
             </tr>
             </thead>
             <tbody id="table-body-div"></tbody>
         </table>
         </div>
     </div>
    
     <script 
         src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
         integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" 
         crossorigin="anonymous">
     </script>
    
     <!-- Bootstrap 5 JS bundle (includes Popper) -->
     <script 
         src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" 
         integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" 
         crossorigin="anonymous">
     </script>
    
     <!-- Your custom scripts -->
     <script type="text/javascript" src="./authConfig.js"></script>
     <script type="text/javascript" src="./ui.js"></script>
     <script type="text/javascript" src="./claimUtils.js"></script>
     <script type="text/javascript" src="./auth.js"></script>
     </body>
     </html>
    ```
2. ファイルを保存します。

#### *ui.js* ファイルにコードを追加する

アプリケーションを対話形式にするために、*ui.js* ファイルを使用してアプリケーションの UI 要素を処理します。 このファイルには、サインイン時にユーザーの名前を更新し、トークンからの要求でテーブルを更新するために使用される関数が含まれています。

1. *public/ui.js* を開き、次のコード スニペットを追加します。

    ```javascript
    // Select DOM elements to work with
    const signInButton = document.getElementById('signIn');
    const signOutButton = document.getElementById('signOut');
    const titleDiv = document.getElementById('title-div');
    const welcomeDiv = document.getElementById('welcome-div');
    const tableDiv = document.getElementById('table-div');
    const tableBody = document.getElementById('table-body-div');
    
    function showWelcomeMessage(account) {
        signInButton.classList.add('d-none');
        signOutButton.classList.remove('d-none');
        titleDiv.classList.add('d-none');
        welcomeDiv.classList.remove('d-none');
        welcomeDiv.innerHTML = `Welcome ${account.username}!`;
        updateTable(account);
    };
    
    function updateTable(account) {
        tableDiv.classList.remove('d-none');
    
        const tokenClaims = createClaimsTable(account.idTokenClaims);
    
        Object.keys(tokenClaims).forEach((key) => {
            let row = tableBody.insertRow(0);
            let cell1 = row.insertCell(0);
            let cell2 = row.insertCell(1);
            let cell3 = row.insertCell(2);
            cell1.innerHTML = tokenClaims[key][0];
            cell2.innerHTML = tokenClaims[key][1];
            cell3.innerHTML = tokenClaims[key][2];
        });
    };
    ```
2. ファイルを保存します。

### *signout.html* ファイルにコードを追加する

*signout.html* ファイルは、ユーザーがアプリケーションからサインアウトするときにメッセージを表示するために使用されます。

1. *public/signout.html* を開き、次のコード スニペットを追加します。

    ```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Microsoft Entra ID | JavaScript SPA</title>
        <link rel="SHORTCUT ICON" href="./favicon.svg" type="image/x-icon">
    
        <!-- adding Bootstrap 4 for UI components  -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <div class="jumbotron" style="margin: 10%">
            <h1>Goodbye!</h1>
            <p>You have signed out and your cache has been cleared.</p>
            <a class="btn btn-primary" href="/" role="button">Take me back</a>
        </div>
    </body>
    </html>
    ```
2. ファイルを保存します。

#### アプリにスタイルを追加する

最後に、アプリケーションにいくつかのスタイルを追加して、より魅力的に見えるようにします。 スタイルは *styles.css* ファイルに追加され、ニーズに合わせてカスタマイズできます。

1. *public/styles.css* を開き、次のコード スニペットを追加します。

    ```css
    .navbarStyle {
        padding: .5rem 1rem !important;
    }
    
    .table-responsive-ms {
        max-height: 39rem !important;
        padding-left: 10%;
        padding-right: 10%;
    }
    ```
2. ファイルを保存します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-app-javascript-prepare-app"} -->
## チュートリアル: 認証用に JavaScript シングルページ アプリ (SPA) を準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-prepare-app
- Service: identity-platform
- Article date: 2025-05-12
- Summary: Microsoft ID プラットフォームを使用して認証用に JavaScript シングルページ アプリ (SPA) を準備する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、JavaScript シングルページ アプリケーション (SPA) を構築し、Microsoft ID プラットフォームを使用して認証用に準備します。 このチュートリアルでは、 `npm`を使用して JavaScript SPA を作成し、認証と承認に必要なファイルを作成し、テナントの詳細をソース コードに追加する方法について説明します。 このアプリケーションは、従業員テナントの従業員または外部テナントを使用している顧客に使用できます。

このチュートリアルでは、次のことを行いました。

- 新しい JavaScript プロジェクトを作成する
- 認証に必要なパッケージをインストールする
- ファイル構造を作成し、サーバー ファイルにコードを追加する
- 認証構成ファイルにテナントの詳細を追加する

### 前提条件

## [従業員テナント](#tab/workforce-tenant)
- 従業員テナント。 [既定のディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)を使用するか、新しいテナントを設定できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`。

## [外部テナント](#tab/external-tenant)
- 外部テナント。 作成するには、次の方法から選択します。
    - (推奨)[Microsoft Entra External ID 拡張機能](https://aka.ms/ciamvscode/tutorials/marketplace) を使用して、Visual Studio Code で外部テナントを直接設定します。
    - [Microsoft Entra 管理センターで新しい外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details) を作成します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`。
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。

---

- [Node.js](https://nodejs.org/en/download/)。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。

### JavaScript プロジェクトを作成して依存関係をインストールする

1. Microsoft Entra 管理センターにグローバル管理者としてサインインします。
2. Visual Studio Code を開き、**[ファイル]**&gt;**[フォルダーを開く...]** の順に選択します。プロジェクトを作成する場所に移動して選択します。
3. **[ターミナル]**&gt;**[新しいターミナル]** を選択して、新しいターミナルを開きます。
4. 次のコマンドを実行して、新しい JavaScript プロジェクトを作成します。

    ```powershell
    npm init -y
    ```
5. 次のプロジェクト構造を実現するために、追加のフォルダーとファイルを作成します。

    ```javascript
    └── public
        └── authConfig.js
        └── auth.js
        └── claimUtils.js
        └── index.html
        └── signout.html
        └── styles.css
        └── ui.js    
    └── server.js
    └── package.json
    ```
6. **ターミナル**で次のコマンドを実行し、プロジェクトに必要な依存関係をインストールします。

    ```powershell
    npm install express morgan @azure/msal-browser
    ```

### サーバー ファイルにコードを追加する

**Express** は、**Node.js** 用の Web アプリケーション フレームワークです。 アプリケーションをホストするサーバーを作成するために使用されます。 **Morgan** は、HTTP 要求をコンソールに記録するミドルウェアです。 サーバー ファイルはこれらの依存関係をホストするために使用され、ファイルにはアプリケーションのルートが含まれています。 認証と承認は、[JavaScript 用 Microsoft Authentication Library (MSAL.js)](https://learn.microsoft.com/ja-jp/javascript/api/overview) で処理されます。

1. *server.js* ファイルに次のコードを追加します。

    ```javascript
    const express = require('express');
    const morgan = require('morgan');
    const path = require('path');
    
    const DEFAULT_PORT = process.env.PORT || 3000;
    
    // initialize express.
    const app = express();
    
    // Configure morgan module to log all requests.
    app.use(morgan('dev'));
    
    // serve public assets.
    app.use(express.static('public'));
    
    // serve msal-browser module
    app.use(express.static(path.join(__dirname, "node_modules/@azure/msal-browser/lib")));
    
    // set up a route for signout.html
    app.get('/signout', (req, res) => {
        res.sendFile(path.join(__dirname + '/public/signout.html'));
    });
    
    // set up a route for redirect.html
    app.get('/redirect', (req, res) => {
        res.sendFile(path.join(__dirname + '/public/redirect.html'));
    });
    
    // set up a route for index.html
    app.get('/', (req, res) => {
        res.sendFile(path.join(__dirname + '/index.html'));
    });
    
    app.listen(DEFAULT_PORT, () => {
        console.log(`Sample app listening on port ${DEFAULT_PORT}!`);
    });
    
    module.exports = app;
    ```

このコードでは、**app** 変数は **Express** モジュールを使用して初期化され、パブリック資産を提供します。 [MSAL-browser](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser) は静的アセットとして機能し、認証フローを開始するために使用されます。

### テナントの詳細を MSAL 構成に追加する

**authConfig.js** ファイルには認証フローの構成設定が含まれており、認証に必要な設定**でMSAL.js** を構成するために使用されます。

## [従業員テナント](#tab/workforce-tenant)
1. *パブリック/authConfig.js* を開き、次のコードを追加します。

    ```javascript
    /**
    * Configuration object to be passed to MSAL instance on creation. 
    * For a full list of MSAL.js configuration parameters, visit:
    * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md 
    */
    const msalConfig = {
        auth: {
            clientId: "Enter_the_Application_Id_Here",
            // WORKFORCE TENANT
            authority: 'https://login.microsoftonline.com/Enter_the_Tenant_Info_Here', //  Replace the placeholder with your tenant info
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
    * https://learn.microsoft.com/entra/identity-platform/permissions-consent-overview#openid-connect-scopes
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
2. 次の値を Microsoft Entra 管理センターの値に置き換えます。

    - `Enter_the_Application_Id_Here` 値を見つけて、Microsoft Entra 管理センターに登録したアプリの **アプリケーション ID (clientId)** に置き換えます。
    - `Enter_the_Tenant_Info_Here` 値を検索し、それを、Microsoft Entra 管理センターで作成した従業員テナントの **Tenant ID** に置き換えます。
3. ファイルを保存します。

## [外部テナント](#tab/external-tenant)
1. *public/authConfig.js* を開き、次のコード スニペットを追加します。

    ```javascript
    /**
    * Configuration object to be passed to MSAL instance on creation. 
    * For a full list of MSAL.js configuration parameters, visit:
    * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md 
    */
    const msalConfig = {
        auth: {
            clientId: "Enter_the_Application_Id_Here",
            // EXTERNAL TENANT
            authority: "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/", // Replace the placeholder with your tenant subdomain
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
    * https://learn.microsoft.com/entra/identity-platform/permissions-consent-overview#openid-connect-scopes
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
2. 次の値を Microsoft Entra 管理センターの値に置き換えます。

    - `Enter_the_Application_Id_Here` Microsoft Entra 管理センターのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
3. ファイルを保存します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-app-javascript-sign-in-sign-out"} -->
## チュートリアル: JavaScript シングルページ アプリ (SPA) からサインインしてサインアウトする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-sign-in-sign-out
- Service: identity-platform
- Article date: 2024-02-11
- Summary: Microsoft ID プラットフォームを使用して JavaScript シングルページ アプリ (SPA) でサインインとサインアウトをテストする方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、JavaScript シングルページ アプリケーション (SPA) の構築と、Microsoft ID プラットフォームを使用した認証の準備を行うシリーズの最後の部分です。 [このシリーズのパート 2](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-configure-authentication) では、JavaScript SPA に認証フローを追加し、応答性の高い UI を構築しました。 この最後の手順では、アプリでサインインとサインアウトの機能をテストする方法について説明します。

このチュートリアルでは、次の操作を行います。

- *claimUtils.js* ファイルにコードを追加して要求テーブルを作成する
- アプリのサインインとサインアウト
- ID トークンから返された要求を表示する

### 前提条件

- [チュートリアル: 認証用に JavaScript シングルページ アプリケーションを準備する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-prepare-app)。

### *claimUtils.js* ファイルにコードを追加する (省略可能)

ID トークンから返された要求を表示できるテーブルの機能を追加するには * 、claimUtils.jsファイル * にコードを追加します。 このコード スニペットは、要求テーブルに適切な説明と対応する値を設定します。

1. *パブリック/claimUtils.js* を開き、次のコード スニペットを追加します。

    ```javascript
     /**
     * Populate claims table with appropriate description
     * @param {Object} claims ID token claims
     * @returns claimsObject
     */
    const createClaimsTable = (claims) => {
        let claimsObj = {};
        let index = 0;
    
        Object.keys(claims).forEach((key) => {
            if (typeof claims[key] !== 'string' && typeof claims[key] !== 'number') return;
            switch (key) {
                case 'aud':
                    populateClaim(
                        key,
                        claims[key],
                        "Identifies the intended recipient of the token. In ID tokens, the audience is your app's Application ID, assigned to your app in the Entra admin center.",
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'iss':
                    populateClaim(
                        key,
                        claims[key],
                        'Identifies the issuer, or authorization server that constructs and returns the token. It also identifies the Microsoft Entra tenant for which the user was authenticated. If the token was issued by the v2.0 endpoint, the URI will end in /v2.0. The GUID that indicates that the user is a consumer user from a Microsoft account is 9188040d-6c67-4c5b-b112-36a304b66dad.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'iat':
                    populateClaim(
                        key,
                        changeDateFormat(claims[key]),
                        'Issued At indicates when the authentication for this token occurred.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'nbf':
                    populateClaim(
                        key,
                        changeDateFormat(claims[key]),
                        'The nbf (not before) claim identifies the time (as UNIX timestamp) before which the JWT must not be accepted for processing.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'exp':
                    populateClaim(
                        key,
                        changeDateFormat(claims[key]),
                        "The exp (expiration time) claim identifies the expiration time (as UNIX timestamp) on or after which the JWT must not be accepted for processing. It's important to note that in certain circumstances, a resource may reject the token before this time. For example, if a change in authentication is required or a token revocation has been detected.",
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'name':
                    populateClaim(
                        key,
                        claims[key],
                        "The principal about which the token asserts information, such as the user of an application. This value is immutable and can't be reassigned or reused. It can be used to perform authorization checks safely, such as when the token is used to access a resource. By default, the subject claim is populated with the object ID of the user in the directory",
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'preferred_username':
                    populateClaim(
                        key,
                        claims[key],
                        'The primary username that represents the user. It could be an email address, phone number, or a generic username without a specified format. Its value is mutable and might change over time. Since it is mutable, this value must not be used to make authorization decisions. It can be used for username hints, however, and in human-readable UI as a username. The profile scope is required in order to receive this claim.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'nonce':
                    populateClaim(
                        key,
                        claims[key],
                        'The nonce matches the parameter included in the original /authorize request to the IDP. If it does not match, your application should reject the token.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'oid':
                    populateClaim(
                        key,
                        claims[key],
                        'The oid (user’s object id) is the only claim that should be used to uniquely identify a user in an Azure AD tenant. The token might have one or more of the following claim, that might seem like a unique identifier, but is not and should not be used as such.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'tid':
                    populateClaim(
                        key,
                        claims[key],
                        'The tenant ID. You will use this claim to ensure that only users from the current Azure AD tenant can access this app.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'upn':
                    populateClaim(
                        key,
                        claims[key],
                        '(user principal name) – might be unique amongst the active set of users in a tenant but tend to get reassigned to new employees as employees leave the organization and others take their place or might change to reflect a personal change like marriage.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'email':
                    populateClaim(
                        key,
                        claims[key],
                        'Email might be unique amongst the active set of users in a tenant but tend to get reassigned to new employees as employees leave the organization and others take their place.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'acct':
                    populateClaim(
                        key,
                        claims[key],
                        'Available as an optional claim, it lets you know what the type of user (homed, guest) is. For example, for an individual’s access to their data you might not care for this claim, but you would use this along with tenant id (tid) to control access to say a company-wide dashboard to just employees (homed users) and not contractors (guest users).',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'sid':
                    populateClaim(key, claims[key], 'Session ID, used for per-session user sign-out.', index, claimsObj);
                    index++;
                    break;
                case 'sub':
                    populateClaim(
                        key,
                        claims[key],
                        'The sub claim is a pairwise identifier - it is unique to a particular application ID. If a single user signs into two different apps using two different client IDs, those apps will receive two different values for the subject claim.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'ver':
                    populateClaim(
                        key,
                        claims[key],
                        'Version of the token issued by the Microsoft identity platform',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'auth_time':
                    populateClaim(
                        key,
                        claims[key],
                        'The time at which a user last entered credentials, represented in epoch time. There is no discrimination between that authentication being a fresh sign-in, a single sign-on (SSO) session, or another sign-in type.',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'at_hash':
                    populateClaim(
                        key,
                        claims[key],
                        'An access token hash included in an ID token only when the token is issued together with an OAuth 2.0 access token. An access token hash can be used to validate the authenticity of an access token',
                        index,
                        claimsObj
                    );
                    index++;
                    break;
                case 'uti':
                case 'rh':
                    index++;
                    break;
                default:
                    populateClaim(key, claims[key], '', index, claimsObj);
                    index++;
            }
        });
    
        return claimsObj;
    };
    
        /**
         * Populates claim, description, and value into an claimsObject
         * @param {string} claim
         * @param {string} value
         * @param {string} description
         * @param {number} index
         * @param {Object} claimsObject
         */
        const populateClaim = (claim, value, description, index, claimsObject) => {
            let claimsArray = [];
            claimsArray[0] = claim;
            claimsArray[1] = value;
            claimsArray[2] = description;
            claimsObject[index] = claimsArray;
        };
    
        /**
         * Transforms Unix timestamp to date and returns a string value of that date
         * @param {string} date Unix timestamp
         * @returns
         */
        const changeDateFormat = (date) => {
            let dateObj = new Date(date * 1000);
            return `${date} - [${dateObj.toString()}]`;
    };
    ```
2. ファイルを保存します。

### プロジェクトを実行してサインインする

必要なコード スニペットがすべて追加されたため、アプリケーションを Web ブラウザーで呼び出してテストできます。

1. 新しいターミナルを開き、次のコマンドを実行して Express Web サーバーを起動します。

    ```console
    npm start
    ```
2. ターミナルに表示される `http` URL (`http://localhost:3000`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. テナントに登録されているアカウントでサインインします。
4. 次のスクリーンショットのようなインターフェイスが表示され、アプリケーションにサインインしたことを示します。 要求テーブルを追加した場合は、ID トークンから返された要求を表示できます。

    [Image: API 呼び出しの結果を示す JavaScript アプリのスクリーンショット。]

### アプリケーションからサインアウトする

1. ページの **[サインアウト** ] ボタンを見つけて選択します。
2. サインアウト元のアカウントを選択するように求められます。 サインインに使用したアカウントを選択します。

サインアウトしたことを示すメッセージが表示されます。ブラウザー ウィンドウを閉じることができるようになりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-app-react-configure-authentication"} -->
## チュートリアル: React シングルページ アプリ (SPA) にサインインコンポーネントとサインアウト コンポーネントを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-configure-authentication
- Service: identity-platform
- Article date: 2025-02-25
- Summary: Microsoft ID プラットフォームを使用して React シングルページ アプリ (SPA) に認証を追加する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、認証用に React シングルページ アプリケーション (SPA) を構成します。 このシリーズ パート 1 では、React SPA を作成し、認証用に準備しました。 このチュートリアルでは、microsoft Authentication Library (MSAL) [機能コンポーネント](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) アプリに追加して認証フローを追加し、アプリの応答性の高いユーザー インターフェイス (UI) を構築する方法について説明します。

このチュートリアルでは、次の操作を行います。

- 機能コンポーネントをアプリケーションに追加する
- ユーザーのプロファイル情報を表示する方法を作成する
- サインインとサインアウトエクスペリエンスを表示するレイアウトを作成する
- サインインとサインアウトエクスペリエンスを追加する

### 前提 条件

- 「[チュートリアル: 認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app)用にアプリケーションを準備する」の前提条件と手順の完了。

### 機能コンポーネントをアプリケーションに追加する

機能コンポーネントは React アプリの構成要素であり、アプリケーションでサインインとサインアウトのエクスペリエンスを構築するために使用されます。

#### NavigationBar コンポーネントを追加する

ナビゲーション バーは、アプリのサインインとサインアウトのエクスペリエンスを提供します。 *index.js* ファイルで以前に設定したインスタンス変数は、サインインメソッドとサインアウト メソッドを呼び出すために使用されます。このメソッドは、ユーザーをサインイン ページにリダイレクトします。

1. src/components/NavigationBar.jsx  開き、次のコード スニペットを追加します。

    ```jsx
    import { AuthenticatedTemplate, UnauthenticatedTemplate, useMsal } from '@azure/msal-react';
    import { Navbar, Button } from 'react-bootstrap';
    import { loginRequest } from '../authConfig';
    
    export const NavigationBar = () => {
        const { instance } = useMsal();
    
        const handleLoginRedirect = () => {
            instance.loginRedirect(loginRequest).catch((error) => console.log(error));
        };
    
        const handleLogoutRedirect = () => {
            instance.logoutRedirect().catch((error) => console.log(error));
        };
    
        /**
         * Most applications will need to conditionally render certain components based on whether a user is signed in or not.
         * msal-react provides 2 easy ways to do this. AuthenticatedTemplate and UnauthenticatedTemplate components will
         * only render their children if a user is authenticated or unauthenticated, respectively.
         */
        return (
            <>
                <Navbar bg="primary" variant="dark" className="navbarStyle">
                    <a className="navbar-brand" href="/">
                        Microsoft identity platform
                    </a>
                    <AuthenticatedTemplate>
                        <div className="collapse navbar-collapse justify-content-end">
                            <Button variant="warning" onClick={handleLogoutRedirect}>
                                Sign out
                            </Button>
                        </div>
                    </AuthenticatedTemplate>
                    <UnauthenticatedTemplate>
                        <div className="collapse navbar-collapse justify-content-end">
                            <Button onClick={handleLoginRedirect}>Sign in</Button>
                        </div>
                    </UnauthenticatedTemplate>
                </Navbar>
            </>
        );
    };
    ```
2. ファイルを保存します。

#### PageLayout コンポーネントを追加する

PageLayout コンポーネントは、アプリのメイン コンテンツを表示するために使用され、アプリのすべてのページに表示する追加コンテンツを含むようにカスタマイズできます。 ユーザーのプロファイル情報は、props を介して情報を渡すことによって表示されます。

1. src/components/PageLayout.jsx  開き、次のコード スニペットを追加します。

    ```jsx
    import { AuthenticatedTemplate } from '@azure/msal-react';
    
    import { NavigationBar } from './NavigationBar.jsx';
    
    export const PageLayout = (props) => {
        /**
         * Most applications will need to conditionally render certain components based on whether a user is signed in or not.
         * msal-react provides 2 easy ways to do this. AuthenticatedTemplate and UnauthenticatedTemplate components will
         * only render their children if a user is authenticated or unauthenticated, respectively.
         */
        return (
            <>
                <NavigationBar />
                <br />
                <h5>
                    <center>Welcome to the Microsoft Authentication Library For React Tutorial</center>
                </h5>
                <br />
                {props.children}
                <br />
                <AuthenticatedTemplate>
                    <footer>
                        <center>
                            How did we do?
                            <a
                                href="https://forms.office.com/Pages/ResponsePage.aspx?id=v4j5cvGGr0GRqy180BHbR_ivMYEeUKlEq8CxnMPgdNZUNDlUTTk2NVNYQkZSSjdaTk5KT1o4V1VVNS4u"
                                rel="noopener noreferrer"
                                target="_blank"
                            >
                                {' '}
                                Share your experience!
                            </a>
                        </center>
                    </footer>
                </AuthenticatedTemplate>
            </>
        );
    }
    ```
2. ファイルを保存します。

#### DataDisplay コンポーネントを追加する

`DataDisplay` コンポーネントは、ユーザーのプロファイル情報とクレームのテーブルを表示するために使用されます。これは、チュートリアルの次のセクションで作成されます。 `IdTokenData` コンポーネントは、ID トークンに要求を表示するために使用されます。

1. src/components/DataDisplay.jsx  開き、次のコード スニペットを追加します。

    ```jsx
    import { Table } from 'react-bootstrap';
    import { createClaimsTable } from '../utils/claimUtils';
    
    import '../styles/App.css';
    
    export const IdTokenData = (props) => {
        const tokenClaims = createClaimsTable(props.idTokenClaims);
    
        const tableRow = Object.keys(tokenClaims).map((key, index) => {
            return (
                <tr key={key}>
                    {tokenClaims[key].map((claimItem) => (
                        <td key={claimItem}>{claimItem}</td>
                    ))}
                </tr>
            );
        });
        return (
            <>
                <div className="data-area-div">
                    <p>
                        See below the claims in your <strong> ID token </strong>. For more information, visit:{' '}
                        <span>
                            <a href="https://docs.microsoft.com/en-us/azure/active-directory/develop/id-tokens#claims-in-an-id-token">
                                docs.microsoft.com
                            </a>
                        </span>
                    </p>
                    <div className="data-area-div">
                        <Table responsive striped bordered hover>
                            <thead>
                                <tr>
                                    <th>Claim</th>
                                    <th>Value</th>
                                    <th>Description</th>
                                </tr>
                            </thead>
                            <tbody>{tableRow}</tbody>
                        </Table>
                    </div>
                </div>
            </>
        );
    };
    ```
2. ファイルを保存します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-app-react-prepare-app"} -->
## チュートリアル: 認証用に React シングルページ アプリケーションを準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app
- Service: identity-platform
- Article date: 2025-05-25
- Summary: Microsoft ID プラットフォームを使用して認証用に React シングルページ アプリ (SPA) を準備する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、React シングルページ アプリケーション (SPA) を構築し、Microsoft ID プラットフォームを使用して認証用に準備します。 このチュートリアルでは、 `npm`を使用して React SPA を作成し、認証と承認に必要なファイルを作成し、テナントの詳細をソース コードに追加する方法について説明します。 このアプリケーションは、従業員テナントの従業員または外部テナントを使用している顧客に使用できます。

このチュートリアルでは、次の操作を行います。

- 新しい React プロジェクトを作成する
- 認証に必要なパッケージをインストールする
- ファイル構造を作成し、サーバー ファイルにコードを追加する
- 認証構成ファイルにテナントの詳細を追加する

### 前提条件

## [従業員テナント](#tab/workforce-tenant)
- 従業員テナント。 [既定のディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)を使用するか、新しいテナントを設定できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`。

## [外部テナント](#tab/external-tenant)
- 外部テナント。 作成するには、次の方法から選択します。
    - (推奨)[Microsoft Entra External ID 拡張機能](https://aka.ms/ciamvscode/tutorials/marketplace) を使用して、Visual Studio Code で外部テナントを直接設定します。
    - [Microsoft Entra 管理センターで新しい外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details) を作成します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`。
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。

---

- [Node.js](https://nodejs.org/en/download/)。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。

### 新しい React プロジェクトを作成する

1. Visual Studio Code を開き、**[ファイル]**&gt;**[フォルダーを開く...]** の順に選択します。プロジェクトを作成する場所に移動して選択します。
2. **[ターミナル]**&gt;**[新しいターミナル]** を選択して、新しいターミナルを開きます。
3. 次のコマンドを実行して、新しい React プロジェクトを *reactspalocal* という名前で作成し、新しいディレクトリに移動して React プロジェクトを開始します。 既定では、アドレス `http://localhost:3000/` で Web ブラウザーが開きます。 ブラウザーは開いたままで、変更が保存されるたびに再レンダリングされます。

    ```console
    npx create-react-app reactspalocal
    cd reactspalocal
    npm start
    ```
4. 追加のフォルダーとファイルを作成して、次のフォルダー構造を完成させます。

    ```javascript
    ├─── public
    │   └─── index.html
    └───src
        └─── styles
        │   └─── App.css
        │   └─── index.css
        ├─── utils
        │   └─── claimUtils.js
        ├─── components
        │   └─── DataDisplay.jsx
        │   └─── NavigationBar.jsx
        │   └─── PageLayout.jsx
        └── App.jsx
        └── authConfig.js
        └── index.js
    ```

### ID およびブートストラップのパッケージをインストールする

ユーザー認証を有効にするには、ID 関連の **npm** パッケージがプロジェクトにインストールされている必要があります。 プロジェクトのスタイル設定では、 **ブートストラップ** が使用されます。

1. **[ターミナル]** バーで、**+** アイコンを選択して新しいターミナルを作成します。 別のターミナル ウィンドウが開き、前のノード ターミナルがバックグラウンドで実行され続けます。
2. 正しいディレクトリが選択されていることを確認し (*reactspalocal*)、ターミナルに次のように入力して、関連する `msal` と `bootstrap` パッケージをインストールします。

    ```console
    npm install @azure/msal-browser @azure/msal-react
    npm install react-bootstrap bootstrap
    ```

### テナントの詳細を MSAL 構成に追加する

*authConfig.js* ファイルには認証フローの構成設定が含まれており、認証に必要な設定**でMSAL.js** を構成するために使用されます。

## [従業員テナント](#tab/workforce-tenant)
1. *src* フォルダーで *authConfig.js* を開き、次のコード スニペットを追加します。

    ```javascript
    
     import { LogLevel } from '@azure/msal-browser';
    
     /**
     * Configuration object to be passed to MSAL instance on creation. 
     * For a full list of MSAL.js configuration parameters, visit:
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md 
     */
    
     export const msalConfig = {
         auth: {
             clientId: 'Enter_the_Application_Id_Here', // This is the ONLY mandatory field that you need to supply.
             authority: 'https://login.microsoftonline.com/Enter_the_Tenant_Info_Here', // Replace the placeholder with your tenant info
             redirectUri: 'http://localhost:3000', // Points to window.location.origin. You must register this URI on Microsoft Entra admin center/App Registration.
             postLogoutRedirectUri: '/', // Indicates the page to navigate after logout.
             navigateToLoginRequestUrl: false, // If "true", will navigate back to the original request location before processing the auth code response.
         },
         cache: {
             cacheLocation: 'sessionStorage', // Configures cache location. "sessionStorage" is more secure, but "localStorage" gives you SSO between tabs.
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
                 },
             },
         },
     };
    
     /**
     * Scopes you add here will be prompted for user consent during sign-in.
     * By default, MSAL.js will add OIDC scopes (openid, profile, email) to any login request.
     * For more information about OIDC scopes, visit: 
     * https://docs.microsoft.com/en-us/azure/active-directory/develop/v2-permissions-and-consent#openid-connect-scopes
     */
     export const loginRequest = {
         scopes: [],
     };
    
     /**
     * An optional silentRequest object can be used to achieve silent SSO
     * between applications by providing a "login_hint" property.
     */
     // export const silentRequest = {
     //     scopes: ["openid", "profile"],
     //     loginHint: "example@domain.net"
     // };
    ```
2. 次の値を Microsoft Entra 管理センターから取得した値に置き換えます。

    - `clientId` - アプリケーションの識別子 。クライアントとも呼ばれます。 `Enter_the_Application_Id_Here` を、登録したアプリケーションの概要ページから先ほど記録した**アプリケーション (クライアント) ID** の値に置き換えます。
    - `authority`- これは 2 つの部分で構成されます。
        - "インスタンス" はクラウド プロバイダーのエンドポイントです。[各国のクラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#azure-ad-authentication-endpoints)の利用可能なさまざまなエンドポイントで確認します。
        - "テナント ID" は、アプリケーションが登録されているテナントの識別子です。*Enter\_the\_Tenant\_Info\_Here* を、登録したアプリケーションの概要ページから先ほど記録した**ディレクトリ (テナント) ID** の値に置き換えます。
3. ファイルを保存します。

## [外部テナント](#tab/external-tenant)
1. *src* フォルダーで *authConfig.js* を開き、次のコード スニペットを追加します。

    ```javascript
    import { LogLevel } from '@azure/msal-browser';
    
    /**
     * Configuration object to be passed to MSAL instance on creation.
     * For a full list of MSAL.js configuration parameters, visit:
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md 
     */
    
    export const msalConfig = {
        auth: {
            clientId: 'Enter_the_Application_Id_Here', // This is the ONLY mandatory field that you need to supply.
            authority: 'https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/', // Replace the placeholder with your tenant subdomain 
            redirectUri: '/', // Points to window.location.origin. You must register this URI on Azure Portal/App Registration.
            postLogoutRedirectUri: '/', // Indicates the page to navigate after logout.
            navigateToLoginRequestUrl: false, // If "true", will navigate back to the original request location before processing the auth code response.
        },
        cache: {
            cacheLocation: 'sessionStorage', // Configures cache location. "sessionStorage" is more secure, but "localStorage" gives you SSO between tabs.
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
                },
            },
        },
    };
    
    /**
     * Scopes you add here will be prompted for user consent during sign-in.
     * By default, MSAL.js will add OIDC scopes (openid, profile, email) to any login request.
     * For more information about OIDC scopes, visit:
     * https://docs.microsoft.com/azure/active-directory/develop/v2-permissions-and-consent#openid-connect-scopes
     */
    export const loginRequest = {
        scopes: [],
    };
    
    /**
     * An optional silentRequest object can be used to achieve silent SSO
     * between applications by providing a "login_hint" property.
     */
    // export const silentRequest = {
    //     scopes: ["openid", "profile"],
    //     loginHint: "example@domain.net"
    // };
    ```
2. 次の値を Entra 管理センターの値に置き換えます。

    - `Enter_the_Application_Id_Here` Microsoft Entra 管理センターのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
3. ファイルを保存します。

#### カスタム URL ドメインを使用する (省略可能)

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

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

### 認証プロバイダーを追加する

`msal` パッケージは、アプリケーションで認証を提供するために使用されます。 `msal-browser` パッケージは認証フローを処理するために使用され、`msal-react` パッケージは `msal-browser` を React と統合するために使用されます。 `addEventCallback` は、ユーザーが正常にログインしたときなど、認証プロセス中に発生するイベントをリッスンするために使用されます。 `setActiveAccount`メソッドは、アプリケーションのアクティブなアカウントを設定するために使用されます。これは、表示するユーザーの情報を決定するために使用されます。

1. "src" フォルダーで、"index.js" を開き、 パッケージとブートストラップのスタイルを使用するために、ファイルの内容を次のコード スニペットに置き換えます。`msal`

    ```javascript
    import React from 'react';
    import { createRoot } from 'react-dom/client';
    import App from './App';
    import { PublicClientApplication, EventType } from '@azure/msal-browser';
    import { msalConfig } from './authConfig';
    
    import 'bootstrap/dist/css/bootstrap.min.css';
    import './styles/index.css';
    
    /**
    * MSAL should be instantiated outside of the component tree to prevent it from being re-instantiated on re-renders.
    * For more, visit: https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-react/docs/getting-started.md
    */
    const msalInstance = new PublicClientApplication(msalConfig);
    
    // Default to using the first account if no account is active on page load
    if (!msalInstance.getActiveAccount() && msalInstance.getAllAccounts().length > 0) {
        // Account selection logic is app dependent. Adjust as needed for different use cases.
        msalInstance.setActiveAccount(msalInstance.getAllAccounts()[0]);
    }
    
    // Listen for sign-in event and set active account
    msalInstance.addEventCallback((event) => {
        if (event.eventType === EventType.LOGIN_SUCCESS && event.payload.account) {
            const account = event.payload.account;
            msalInstance.setActiveAccount(account);
        }
    });
    
    const root = createRoot(document.getElementById('root'));
    root.render(
        <App instance={msalInstance}/>
    );
    ```
2. ファイルを保存します。

これらのパッケージの詳細については、 [`msal-browser`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser) と [`msal-react`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react)のドキュメントを参照してください。

### メイン アプリケーション コンポーネントを追加する

認証を必要とするアプリのすべての部分を [`MsalProvider`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react/#@azure-msal-react-msalprovider) コンポーネントにラップする必要があります。 `instance` フックを呼び出して`useMsal` インスタンスを取得する[`PublicClientApplication`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/publicclientapplication)変数を設定し、それを`MsalProvider`に渡します。 `MsalProvider` コンポーネントを使用すると、React の Context API を使用して、アプリ全体で`PublicClientApplication` インスタンスを使用できるようになります。 `MsalProvider`の下にあるすべてのコンポーネントは、コンテキストだけでなく、`PublicClientApplication`によって提供されるすべてのフックとコンポーネントを介して`msal-react` インスタンスにアクセスできます。

1. *src* フォルダーで *App.jsx* を開き、ファイルの内容を次のコード スニペットに置き換えます。

    ```javascript
    import { MsalProvider, AuthenticatedTemplate, useMsal, UnauthenticatedTemplate } from '@azure/msal-react';
    import { Container, Button } from 'react-bootstrap';
    import { PageLayout } from './components/PageLayout';
    import { IdTokenData } from './components/DataDisplay';
    import { loginRequest } from './authConfig';
    
    import './styles/App.css';
    
    /**
    * Most applications will need to conditionally render certain components based on whether a user is signed in or not. 
    * msal-react provides 2 easy ways to do this. AuthenticatedTemplate and UnauthenticatedTemplate components will 
    * only render their children if a user is authenticated or unauthenticated, respectively. For more, visit:
    * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-react/docs/getting-started.md
    */
    const MainContent = () => {
        /**
        * useMsal is hook that returns the PublicClientApplication instance,
        * that tells you what msal is currently doing. For more, visit:
        * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-react/docs/hooks.md
        */
        const { instance } = useMsal();
        const activeAccount = instance.getActiveAccount();
    
        const handleRedirect = () => {
            instance
                .loginRedirect({
                    ...loginRequest,
                    prompt: 'create',
                })
                .catch((error) => console.log(error));
        };
        return (
            <div className="App">
                <AuthenticatedTemplate>
                    {activeAccount ? (
                        <Container>
                            <IdTokenData idTokenClaims={activeAccount.idTokenClaims} />
                        </Container>
                    ) : null}
                </AuthenticatedTemplate>
                <UnauthenticatedTemplate>
                    <Button className="signInButton" onClick={handleRedirect} variant="primary">
                        Sign up
                    </Button>
                </UnauthenticatedTemplate>
            </div>
        );
    };

    /**
    * msal-react is built on the React context API and all parts of your app that require authentication must be 
    * wrapped in the MsalProvider component. You will first need to initialize an instance of PublicClientApplication 
    * then pass this to MsalProvider as a prop. All components underneath MsalProvider will have access to the 
    * PublicClientApplication instance via context as well as all hooks and components provided by msal-react. For more, visit:
    * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-react/docs/getting-started.md
    */
    const App = ({ instance }) => {
        return (
            <MsalProvider instance={instance}>
                <PageLayout>
                    <MainContent />
                </PageLayout>
            </MsalProvider>
        );
    };
    
    export default App;
    ```
2. ファイルを保存します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-app-react-sign-in-sign-out"} -->
## チュートリアル: React シングルページ アプリ (SPA) からサインインしてサインアウトする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-sign-in-sign-out
- Service: identity-platform
- Article date: 2023-09-25
- Summary: Microsoft ID プラットフォームを使用して React シングルページ アプリ (SPA) でサインインとサインアウトをテストする方法について説明します。

**適用対象**: [Image: 白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: 白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細はこちら](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、React シングルページ アプリケーション (SPA) の構築と、Microsoft ID プラットフォームを使用した認証の準備を行うシリーズの最後の部分です。 このシリーズ [のパート 2](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-configure-authentication)では、React SPA に機能コンポーネントを追加し、応答性の高い UI を構築しました。 この最後の手順では、アプリでサインインとサインアウトの機能をテストする方法について説明します。

このチュートリアルでは、次の操作を行います。

- *claimUtils.js* ファイルにコードを追加して要求テーブルを作成する
- アプリのサインインとサインアウト
- ID トークンから返された要求を表示する

### 前提条件

- 「[チュートリアル: React シングルページ アプリでサインインおよびサインアウトするためのコンポーネントを作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-configure-authentication)」の前提条件と手順を完了。

### *claimUtils.js* ファイルにコードを追加する (省略可能)

ID トークンから返された要求を表示できるテーブルの機能を追加するには、*claimUtils.js* ファイルにコードを追加します。 このコード スニペットは、要求テーブルに適切な説明と対応する値を設定します。

1. utils/claimUtils.js 開き、次のコード スニペットを追加します。

```javascript
export const createClaimsTable = (claims) => {
    let claimsObj = {};
    let index = 0;

    Object.keys(claims).forEach((key) => {
        if (typeof claims[key] !== 'string' && typeof claims[key] !== 'number') return;
        switch (key) {
            case 'aud':
                populateClaim(
                    key,
                    claims[key],
                    "Identifies the intended recipient of the token. In ID tokens, the audience is your app's Application ID, assigned to your app in the Microsoft Entra admin center.",
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'iss':
                populateClaim(
                    key,
                    claims[key],
                    'Identifies the issuer, or authorization server that constructs and returns the token. It also identifies the external tenant for which the user was authenticated. If the token was issued by the v2.0 endpoint, the URI will end in /v2.0. The GUID that indicates that the user is a consumer user from a Microsoft account is 9188040d-6c67-4c5b-b112-36a304b66dad.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'iat':
                populateClaim(
                    key,
                    changeDateFormat(claims[key]),
                    'Issued At indicates when the authentication for this token occurred.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'nbf':
                populateClaim(
                    key,
                    changeDateFormat(claims[key]),
                    'The nbf (not before) claim identifies the time (as UNIX timestamp) before which the JWT must not be accepted for processing.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'exp':
                populateClaim(
                    key,
                    changeDateFormat(claims[key]),
                    "The exp (expiration time) claim identifies the expiration time (as UNIX timestamp) on or after which the JWT must not be accepted for processing. It's important to note that in certain circumstances, a resource may reject the token before this time. For example, if a change in authentication is required or a token revocation has been detected.",
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'name':
                populateClaim(
                    key,
                    claims[key],
                    "The name claim provides a human-readable value that identifies the subject of the token. The value isn't guaranteed to be unique, it can be changed, and it's designed to be used only for display purposes. The profile scope is required to receive this claim.",
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'preferred_username':
                populateClaim(
                    key,
                    claims[key],
                    'The primary username that represents the user. It could be an email address, phone number, or a generic username without a specified format. Its value is mutable and might change over time. Since it is mutable, this value must not be used to make authorization decisions. It can be used for username hints, however, and in human-readable UI as a username. The profile scope is required in order to receive this claim.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'nonce':
                populateClaim(
                    key,
                    claims[key],
                    'The nonce matches the parameter included in the original /authorize request to the IDP. If it does not match, your application should reject the token.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'oid':
                populateClaim(
                    key,
                    claims[key],
                    'The oid (user’s object id) is the only claim that should be used to uniquely identify a user in an external tenant. The token might have one or more of the following claim, that might seem like a unique identifier, but is not and should not be used as such.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'tid':
                populateClaim(
                    key,
                    claims[key],
                    'The tenant ID. You will use this claim to ensure that only users from the current external tenant can access this app.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'upn':
                populateClaim(
                    key,
                    claims[key],
                    '(user principal name) – might be unique amongst the active set of users in a tenant but tend to get reassigned to new employees as employees leave the organization and others take their place or might change to reflect a personal change like marriage.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'email':
                populateClaim(
                    key,
                    claims[key],
                    'Email might be unique amongst the active set of users in a tenant but tend to get reassigned to new employees as employees leave the organization and others take their place.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'acct':
                populateClaim(
                    key,
                    claims[key],
                    'Available as an optional claim, it lets you know what the type of user (homed, guest) is. For example, for an individual’s access to their data you might not care for this claim, but you would use this along with tenant id (tid) to control access to say a company-wide dashboard to just employees (homed users) and not contractors (guest users).',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'sid':
                populateClaim(key, claims[key], 'Session ID, used for per-session user sign-out.', index, claimsObj);
                index++;
                break;
            case 'sub':
                populateClaim(
                    key,
                    claims[key],
                    'The sub claim is a pairwise identifier - it is unique to a particular application ID. If a single user signs into two different apps using two different client IDs, those apps will receive two different values for the subject claim.',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'ver':
                populateClaim(
                    key,
                    claims[key],
                    'Version of the token issued by the Microsoft identity platform',
                    index,
                    claimsObj
                );
                index++;
                break;
            case 'uti':
            case 'rh':
                index++;
                break;
            case '_claim_names':
            case '_claim_sources':
            default:
                populateClaim(key, claims[key], '', index, claimsObj);
                index++;
        }
    });

    return claimsObj;
};

/**
 * Populates claim, description, and value into an claimsObject
 * @param {String} claim
 * @param {String} value
 * @param {String} description
 * @param {Number} index
 * @param {Object} claimsObject
 */
const populateClaim = (claim, value, description, index, claimsObject) => {
    let claimsArray = [];
    claimsArray[0] = claim;
    claimsArray[1] = value;
    claimsArray[2] = description;
    claimsObject[index] = claimsArray;
};

/**
 * Transforms Unix timestamp to date and returns a string value of that date
 * @param {String} date Unix timestamp
 * @returns
 */
const changeDateFormat = (date) => {
    let dateObj = new Date(date * 1000);
    return `${date} - [${dateObj.toString()}]`;
};
```

### プロジェクトを実行してサインインする

必要なコード スニペットがすべて追加されたため、アプリケーションを Web ブラウザーで呼び出してテストできます。

1. 新しいターミナルを開き、次のコマンドを実行して Express Web サーバーを起動します。

    ```console
    npm start
    ```
2. ターミナルに表示される `http` URL (`http://localhost:3000`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. テナントに登録されているアカウントでサインインします。
4. 次のスクリーンショットのようなインターフェイスが表示され、アプリケーションにサインインしたことを示します。 要求テーブルを追加した場合は、ID トークンから返された要求を表示できます。

    [Image: API 呼び出しの結果を示す JavaScript アプリのスクリーンショット。]

### アプリケーションからサインアウトする

1. ページ上の **サインアウト** ボタンを見つけて選択します。
2. サインアウト元のアカウントを選択するように求められます。 サインインに使用したアカウントを選択します。

サインアウトしたことを示すメッセージが表示されます。ブラウザー ウィンドウを閉じることができるようになりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-apps-angular-extract-user-data"} -->
## チュートリアル: Angular シングルページ アプリ (SPA) を使用してユーザー データを抽出する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-extract-user-data
- Service: identity-platform
- Article date: 2025-02-20
- Summary: Angular シングルページ アプリ (SPA) を使用してユーザー データを抽出する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、Angular シングルページ アプリケーション (SPA) の構築と、Microsoft ID プラットフォームを使用した認証の追加を示すシリーズの最後の部分です。 [このシリーズのパート 2](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-sign-in-users-app) では、Angular SPA を作成し、従業員テナントでの認証用に準備しました。

このチュートリアルでは、次のことを行いました。

- Angular アプリケーションにデータ処理を追加します。
- アプリケーションをテストし、ユーザー データを抽出します。

### 前提条件

- [チュートリアル: Angular シングルページ アプリケーションでサインインとサインアウトを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-sign-in-users-app)

### アプリケーション UI で表示するデータを抽出する

## [従業員テナント](#tab/workforce-tenant)
Microsoft Graph API と対話するように Angular アプリケーションを構成するには、次の手順を実行します。

1. `src/app/profile/profile.component.ts` ファイルを開き、内容を次のコード スニペットに置き換えます。

    ```typescript
    // Required for Angular
    import { Component, OnInit } from '@angular/core';
    
    // Required for the HTTP GET request to Graph
    import { HttpClient } from '@angular/common/http';
    
    type ProfileType = {
      businessPhones?: string,
      displayName?: string,
      givenName?: string,
      jobTitle?: string,
      mail?: string,
      mobilePhone?: string,
      officeLocation?: string,
      preferredLanguage?: string,
      surname?: string,
      userPrincipalName?: string,
      id?: string
    }
    
    @Component({
      selector: 'app-profile',
      templateUrl: './profile.component.html'
    })
    export class ProfileComponent implements OnInit {
      profile!: ProfileType;
      tokenExpiration!: string;
    
      constructor(
        private http: HttpClient
      ) { }
    
      // When the page loads, perform an HTTP GET request from the Graph /me endpoint
      ngOnInit() {
        this.http.get('https://graph.microsoft.com/v1.0/me')
          .subscribe(profile => {
            this.profile = profile;
          });
    
        this.tokenExpiration = localStorage.getItem('tokenExpiration')!;
      }
    }
    ```

    Angular の `ProfileComponent` は、Microsoft Graph の `/me` エンドポイントからユーザー プロファイル データをフェッチします。 `ProfileType`や`displayName`などのプロパティの構造に`mail`を定義します。 `ngOnInit`では、`HttpClient`を使用して GET 要求を送信し、応答を`profile`に割り当てます。 また、 `localStorage` からトークンの有効期限を取得して `tokenExpiration`に格納します。
2. `src/app/profile/profile.component.html` ファイルを開き、内容を次のコード スニペットに置き換えます。

    ```html
    <div class="profile">
        <p><strong>Business Phones:</strong> {{profile?.businessPhones}}</p>
        <p><strong>Display Name:</strong> {{profile?.displayName}}</p>
        <p><strong>Given Name:</strong> {{profile?.givenName}}</p>
        <p><strong>Job Title:</strong> {{profile?.jobTitle}}</p>
        <p><strong>Mail:</strong> {{profile?.mail}}</p>
        <p><strong>Mobile Phone:</strong> {{profile?.mobilePhone}}</p>
        <p><strong>Office Location:</strong> {{profile?.officeLocation}}</p>
        <p><strong>Preferred Language:</strong> {{profile?.preferredLanguage}}</p>
        <p><strong>Surname:</strong> {{profile?.surname}}</p>
        <p><strong>User Principal Name:</strong> {{profile?.userPrincipalName}}</p>
        <p><strong>Profile Id:</strong> {{profile?.id}}</p>
        <br><br>
        <p><strong>Token Expiration:</strong> {{tokenExpiration}}</p>
        <br><br>
        <p>Refreshing this page will continue to use the cached access token until it nears expiration, at which point a new access token will be requested.</p>
    </div>
    ```

    このコードでは、Angular の補間構文を使用して、 `profile` オブジェクト (たとえば、 `businessPhones`、 `displayName`、 `jobTitle`) からプロパティをバインドして、ユーザー プロファイル情報を表示する HTML テンプレートを定義します。 また、 `tokenExpiration` 値も表示され、ページを更新すると、有効期限が近くまでキャッシュされたアクセス トークンが使用され、その後新しいトークンが要求されることを示すメモが含まれています。

## [外部テナント](#tab/external-tenant)
サインイン時に ID トークンに要求を表示するように Angular アプリを構成します。

1. *src/app/* フォルダーに *claim-utils.ts* という名前のファイルを作成し、次のコード スニペットを貼り付けます。

    ```javascript
    /**
     * Populate claims table with appropriate description
     * @param {Record} claims ID token claims
     * @returns claimsTable
     */
    export const createClaimsTable = (claims: Record<string, string>): any[] => {
      const claimsTable: any[] = [];
    
      Object.keys(claims).map((key) => {
        switch (key) {
          case 'aud':
            populateClaim(
              key,
              claims[key],
              "Identifies the intended recipient of the token. In ID tokens, the audience is your app's Application ID, assigned to your app in the Azure portal.",
              claimsTable
            );
            break;
          case 'iss':
            populateClaim(
              key,
              claims[key],
              'Identifies the issuer, or authorization server that constructs and returns the token. It also identifies the Azure AD tenant for which the user was authenticated. If the token was issued by the v2.0 endpoint, the URI will end in /v2.0.',
              claimsTable
            );
            break;
          case 'iat':
            populateClaim(
              key,
              changeDateFormat(+claims[key]),
              '"Issued At" indicates the timestamp (UNIX timestamp) when the authentication for this user occurred.',
              claimsTable
            );
            break;
          case 'nbf':
            populateClaim(
              key,
              changeDateFormat(+claims[key]),
              'The nbf (not before) claim dictates the time (as UNIX timestamp) before which the JWT must not be accepted for processing.',
              claimsTable
            );
            break;
          case 'exp':
            populateClaim(
              key,
              changeDateFormat(+claims[key]),
              "The exp (expiration time) claim dictates the expiration time (as UNIX timestamp) on or after which the JWT must not be accepted for processing. It's important to note that in certain circumstances, a resource may reject the token before this time. For example, if a change in authentication is required or a token revocation has been detected.",
              claimsTable
            );
            break;
          case 'name':
            populateClaim(
              key,
              claims[key],
              "The name claim provides a human-readable value that identifies the subject of the token. The value isn't guaranteed to be unique, it can be changed, and it's designed to be used only for display purposes. The 'profile' scope is required to receive this claim.",
              claimsTable
            );
            break;
          case 'preferred_username':
            populateClaim(
              key,
              claims[key],
              'The primary username that represents the user. It could be an email address, phone number, or a generic username without a specified format. Its value is mutable and might change over time. Since it is mutable, this value must not be used to make authorization decisions. It can be used for username hints, however, and in human-readable UI as a username. The profile scope is required in order to receive this claim.',
              claimsTable
            );
            break;
          case 'nonce':
            populateClaim(
              key,
              claims[key],
              'The nonce matches the parameter included in the original /authorize request to the IDP.',
              claimsTable
            );
            break;
          case 'oid':
            populateClaim(
              key,
              claims[key],
              'The oid (user object id) is the only claim that should be used to uniquely identify a user in an Azure AD tenant.',
              claimsTable
            );
            break;
          case 'tid':
            populateClaim(
              key,
              claims[key],
              'The id of the tenant where this application resides. You can use this claim to ensure that only users from the current Azure AD tenant can access this app.',
              claimsTable
            );
            break;
          case 'upn':
            populateClaim(
              key,
              claims[key],
              'upn (user principal name) might be unique amongst the active set of users in a tenant but tend to get reassigned to new employees as employees leave the organization and others take their place or might change to reflect a personal change like marriage.',
              claimsTable
            );
            break;
          case 'email':
            populateClaim(
              key,
              claims[key],
              'Email might be unique amongst the active set of users in a tenant but tend to get reassigned to new employees as employees leave the organization and others take their place.',
              claimsTable
            );
            break;
          case 'acct':
            populateClaim(
              key,
              claims[key],
              'Available as an optional claim, it lets you know what the type of user (homed, guest) is. For example, for an individuals access to their data you might not care for this claim, but you would use this along with tenant id (tid) to control access to say a company-wide dashboard to just employees (homed users) and not contractors (guest users).',
              claimsTable
            );
            break;
          case 'sid':
            populateClaim(
              key,
              claims[key],
              'Session ID, used for per-session user sign-out.',
              claimsTable
            );
            break;
          case 'sub':
            populateClaim(
              key,
              claims[key],
              'The sub claim is a pairwise identifier - it is unique to a particular application ID. If a single user signs into two different apps using two different client IDs, those apps will receive two different values for the subject claim.',
              claimsTable
            );
            break;
          case 'ver':
            populateClaim(
              key,
              claims[key],
              'Version of the token issued by the Microsoft identity platform',
              claimsTable
            );
            break;
          case 'login_hint':
            populateClaim(
              key,
              claims[key],
              'An opaque, reliable login hint claim. This claim is the best value to use for the login_hint OAuth parameter in all flows to get SSO.',
              claimsTable
            );
            break;
          case 'idtyp':
            populateClaim(
              key,
              claims[key],
              'Value is app when the token is an app-only token. This is the most accurate way for an API to determine if a token is an app token or an app+user token',
              claimsTable
            );
            break;
          case 'uti':
          case 'rh':
            break;
          default:
            populateClaim(key, claims[key], '', claimsTable);
        }
      });
    
      return claimsTable;
    };
    
    /**
     * Populates claim, description, and value into an claimsObject
     * @param {String} claim
     * @param {String} value
     * @param {String} description
     * @param {Array} claimsObject
     */
    const populateClaim = (
      claim: string,
      value: string,
      description: string,
      claimsTable: any[]
    ): void => {
      claimsTable.push({
        claim: claim,
        value: value,
        description: description,
      });
    };
    
    /**
     * Transforms Unix timestamp to date and returns a string value of that date
     * @param {number} date Unix timestamp
     * @returns
     */
    const changeDateFormat = (date: number) => {
      let dateObj = new Date(date * 1000);
      return `${date} - [${dateObj.toString()}]`;
    };
    ```
2. *src/index.html* を開き、コードを次のスニペットに置き換えます。

    ```html
    <!doctype html>
    <html lang="en">
    <head>
      <meta charset="utf-8">
      <title>Microsoft identity platform</title>
      <base href="/">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="icon" type="image/x-icon" href="favicon.svg">
      <link rel="preconnect" href="https://fonts.gstatic.com">
      <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body class="mat-typography">
      <app-root></app-root>
      <app-redirect></app-redirect>
    </body>
    </html>
    ```
3. *src/app/guarded/guarded.component.ts* を開き、既存のコードを次のコード スニペットに置き換えます。

    ```javascript
    import { Component, OnInit } from '@angular/core';
    
    @Component({
      selector: 'app-guarded',
      templateUrl: './guarded.component.html',
      styleUrls: ['./guarded.component.css']
    })
    export class GuardedComponent implements OnInit {
    
      constructor() { }
    
      ngOnInit(): void {
      }
    
    }
    ```

---

### アプリケーションをテストする

アプリケーションを機能させるには、Angular アプリケーションを実行し、サインインして Microsoft Entra テナントで認証し、ユーザー データを抽出する必要があります。

## [従業員テナント](#tab/workforce-tenant)
アプリケーションをテストするには、次の手順を実行します。

1. ターミナルで次のコマンドを実行して、Angular アプリケーションを実行します。

    ```bash
    ng serve --open
    ```
2. [ **サインイン** ] ボタンを選択して、Microsoft Entra テナントで認証します。
3. サインインしたら、[ **プロファイルの表示** ] リンクを選択して [ **プロファイル** ] ページに移動します。 ユーザーの名前、電子メール、役職、その他の詳細など、ユーザー プロファイル情報が表示されていることを確認します。

    [Image: API 呼び出しの結果を示す JavaScript アプリのスクリーンショット。]
4. [ **サインアウト** ] ボタンを選択して、アプリケーションからサインアウトします。

## [外部テナント](#tab/external-tenant)
1. アプリケーション フォルダーからコマンド ライン プロンプトで次のコマンドを実行して、Web サーバーを起動します。

    ```bash
    npm install
    npm start
    ```
2. ブラウザーで、「 `http://localhost:4200` 」と入力してアプリケーションを開きます。

    [Image: サインイン ダイアログが表示されている Web ブラウザーのスクリーンショット]
3. 画面の右上隅にある **[ログイン** ] ボタンを選択します。
4. サインインすると、プロファイル情報がページに表示されます。

    [Image: サインインしているアプリを表示している Web ブラウザー]
5. 画面の右上隅にある **[ログアウト** ] ボタンを選択してサインアウトします。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-apps-angular-prepare-app"} -->
## チュートリアル: 認証用に Angular シングルページ アプリ (SPA) を準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-prepare-app
- Service: identity-platform
- Article date: 2025-02-20
- Summary: 認証を管理し、ユーザー アクセスをセキュリティで保護するために、Microsoft Entra テナントで Angular シングルページ アプリ (SPA) を準備します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、Angular シングルページ アプリケーション (SPA) の構築、認証の追加、および Microsoft ID プラットフォームを使用したユーザー データの抽出を示すシリーズの最初の部分です。

このチュートリアルでは、次の操作を行います。

- 新しい Angular プロジェクトを作成する
- アプリケーションの設定を構成する
- アプリケーションに認証コードを追加する

### 前提条件

## [従業員テナント](#tab/workforce-tenant)
- 従業員テナント。 [既定のディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)を使用するか、新しいテナントを設定できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:4200/`。

## [外部テナント](#tab/external-tenant)
- 外部テナント。 作成するには、次の方法から選択します。
    - (推奨)[Microsoft Entra External ID 拡張機能](https://aka.ms/ciamvscode/tutorials/marketplace) を使用して、Visual Studio Code で外部テナントを直接設定します。
    - [Microsoft Entra 管理センターで新しい外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details) を作成します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:4200/`。
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。

---

- [Angular CLIの](https://v17.angular.io/cli#installing-angular-cli)
- [Node.js 18.19 以降](https://nodejs.org/en/download/)。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。

### 新しい Angular プロジェクトを作成する

このセクションでは、Visual Studio Code で Angular CLI を使用して新しい Angular プロジェクトを作成します。 テナントの種類に基づいて適切なタブを選択します。

## [従業員テナント](#tab/workforce-tenant)
Angularプロジェクトを最初から作成するには、次の手順を実行します。

1. ターミナル ウィンドウを開き、次のコマンドを実行して、新しい Angular プロジェクトを作成します。

    ```console
    ng new msal-angular-tutorial --routing=true --style=css --strict=false
    ```

    このコマンドにより、ルーティングが有効で、スタイル設定が CSS で、厳密モードが無効になっている `msal-angular-tutorial` という名前の Angular プロジェクトが新しく作成されます。
2. プロジェクト ディレクトリに移動します。

    ```console
    cd msal-angular-tutorial
    ```
3. アプリの依存関係をインストールします。

    ```console
    npm install @azure/msal-browser @azure/msal-angular bootstrap
    ```

    コマンド `npm install @azure/msal-browser @azure/msal-angular bootstrap` により、Azure MSAL ブラウザー、Azure MSAL Angular、ブートストラップ パッケージがインストールされます。
4. `angular.json` を開き、ブートストラップの CSS パスを `styles` 配列に追加します。

    ```json
    "styles": [
        "src/styles.css",
        "node_modules/bootstrap/dist/css/bootstrap.min.css"
    ],
    ```

    このコードにより、ブートストラップ CSS が `angular.json` ファイル内のスタイル配列に追加されます。
5. ホーム コンポーネントとプロファイル コンポーネントを生成します。

    ```console
    ng generate component home
    ng generate component profile
    ```

    このコマンドにより、Angular プロジェクトでホーム コンポーネントとプロファイル コンポーネントが生成されます。
6. 不要なファイルとコードをプロジェクトから削除します。

    ```console
    rm src/app/app.component.css
    rm src/app/app.component.spec.ts
    rm src/app/home/home.component.css
    rm src/app/home/home.component.spec.ts
    rm src/app/profile/profile.component.css
    rm src/app/profile/profile.component.spec.ts
    ```

    このコマンドを実行すると、不要なファイルとコードがプロジェクトから削除されます。
7. Visual Studio Code を使用して `app.routes.ts` の名前を `app-routing.module.ts` に変更し、アプリケーション全体で `app.routes.ts` のすべての参照を更新します。
8. Visual Studio Code を使用して `app.config.ts` の名前を `app.module.ts` に変更し、アプリケーション全体で `app.config.ts` へのすべての参照を更新します。

これらの手順を完了すると、プロジェクト構造は次のようになります。

```console
.
├── README.md
├── angular.json
├── package-lock.json
├── package.json
├── src
│   ├── app
│   │   ├── app-routing.module.ts
│   │   ├── app.component.html
│   │   ├── app.component.ts
│   │   ├── app.module.ts
│   │   ├── home
│   │   │   ├── home.component.html
│   │   │   └── home.component.ts
│   │   └── profile
│   │       ├── profile.component.html
│   │       └── profile.component.ts
│   ├── index.html
│   ├── main.ts
│   ├── polyfills.ts
│   └── styles.css
├── tsconfig.app.json
└── tsconfig.json
```

## [外部テナント](#tab/external-tenant)
1. Visual Studio Code を開き、[フォルダーを&gt;] を選択します。プロジェクトを作成する場所に移動して選択します。
2. **[ターミナル]**&gt;**[新しいターミナル]** を選択して、新しいターミナルを開きます。
3. 次のコマンドを実行して、 `angularspalocal`という名前の新しい Angular プロジェクトを作成し、Angular Material コンポーネント ライブラリ、MSAL Browser、MSAL Angular をインストールし、ホーム コンポーネントと保護されたコンポーネントを生成します。

    ```powershell
    npm install -g @angular/cli@14.2.0
    ng new angularspalocal --routing=true --style=css --strict=false
    cd angularspalocal
    npm install @angular/material@13.0.0 @angular/cdk@13.0.0
    npm install @azure/msal-browser@2.37.0 @azure/msal-angular@2.5.7
    ng generate component home
    ng generate component guarded
    ```

---

### アプリケーション設定の構成

このセクションでは、認証用のアプリケーション設定を構成します。 アプリの登録時に記録された値を使用して、認証用にアプリケーションを構成します。 テナントの種類に基づいて適切なタブを選択します。

## [従業員テナント](#tab/workforce-tenant)
アプリの登録時に記録された値を使用して、認証用にアプリケーションを構成します。 次のステップを実行します。

1. `src/app/app.module.ts` ファイルを開き、その内容を次のコードに置き換えます。

    ```typescript
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

    このコードにより、ユーザー認証と API 保護のために MSAL を設定されます。 これにより API 要求を保護する `MsalInterceptor` と、ルートを保護する `MsalGuard` でアプリが構成され、さらに認証のためのコンポーネントとサービスが定義されます。 次の値を Microsoft Entra 管理センターから取得した値に置き換えます。

    - `Enter_the_Application_Id_Here` を、アプリ登録の `Application (client) ID` に置き換えます。
    - `Enter_the_Tenant_Info_Here` を、アプリ登録の `Directory (tenant) ID` に置き換えます。
2. ファイルを保存します。

## [外部テナント](#tab/external-tenant)
1. *src/app/* フォルダーに *auth-config.ts* という名前の新しいファイルを作成し、次のコード スニペットを追加します。 このファイルには認証パラメーターが含まれています。 これらのパラメーターは、Angular と MSAL Angular の構成を初期化するために使用されます。

    ```javascript
    
    /**
     * This file contains authentication parameters. Contents of this file
     * is roughly the same across other MSAL.js libraries. These parameters
     * are used to initialize Angular and MSAL Angular configurations in
     * in app.module.ts file.
     */
    
    import {
      LogLevel,
      Configuration,
      BrowserCacheLocation,
    } from '@azure/msal-browser';
    
    /**
     * Configuration object to be passed to MSAL instance on creation.
     * For a full list of MSAL.js configuration parameters, visit:
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md
     */
    export const msalConfig: Configuration = {
      auth: {
        clientId: 'Enter_the_Application_Id_Here', // This is the ONLY mandatory field that you need to supply.
        authority: 'https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/', // Replace the placeholder with your tenant subdomain
        redirectUri: '/', // Points to window.location.origin by default. You must register this URI on Azure portal/App Registration.
        postLogoutRedirectUri: '/', // Points to window.location.origin by default.
      },
      cache: {
        cacheLocation: BrowserCacheLocation.LocalStorage, // Configures cache location. "sessionStorage" is more secure, but "localStorage" gives you SSO between tabs.
      },
      system: {
        loggerOptions: {
          loggerCallback(logLevel: LogLevel, message: string) {
            console.log(message);
          },
          logLevel: LogLevel.Verbose,
          piiLoggingEnabled: false,
        },
      },
    };
    
    /**
     * Scopes you add here will be prompted for user consent during sign-in.
     * By default, MSAL.js will add OIDC scopes (openid, profile, email) to any login request.
     * For more information about OIDC scopes, visit:
     * https://learn.microsoft.com/entra/identity-platform/permissions-consent-overview#openid-connect-scopes
     */
    export const loginRequest = {
      scopes: [],
    };
    ```
2. 次の値を Microsoft Entra 管理センターの値に置き換えます。

    - `Enter_the_Application_Id_Here` 値を見つけて、Microsoft Entra 管理センターに登録したアプリの **アプリケーション ID (clientId)** に置き換えます。
    - **機関**で、`Enter_the_Tenant_Subdomain_Here`を見つけて、テナントのサブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
3. ファイルを保存します。
4. *src/app/app.module.ts* を開き、次のコード スニペットを追加します。

    ```javascript
    import { NgModule } from '@angular/core';
    import { BrowserModule } from '@angular/platform-browser';
    import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
    
    import { MatToolbarModule } from "@angular/material/toolbar";
    import { MatButtonModule } from '@angular/material/button';
    import { MatCardModule } from '@angular/material/card';
    import { MatTableModule } from '@angular/material/table';
    import { MatMenuModule } from '@angular/material/menu';
    import { MatDialogModule } from '@angular/material/dialog';
    import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
    import { MatIconModule } from '@angular/material/icon';
    
    import { AppRoutingModule } from './app-routing.module';
    import { AppComponent } from './app.component';
    import { HomeComponent } from './home/home.component';
    import { GuardedComponent } from './guarded/guarded.component';
    
    import {
        IPublicClientApplication,
        PublicClientApplication,
        InteractionType,
    } from '@azure/msal-browser';
    
    import {
        MSAL_INSTANCE,
        MsalGuardConfiguration,
        MSAL_GUARD_CONFIG,
        MsalService,
        MsalBroadcastService,
        MsalGuard,
        MsalRedirectComponent,
        MsalInterceptor,
        MsalModule,
    } from '@azure/msal-angular';
    
    import { msalConfig, loginRequest } from './auth-config';
    
    /**
     * Here we pass the configuration parameters to create an MSAL instance.
        * For more info, visit: https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/docs/v2-docs/configuration.md
        */
    export function MSALInstanceFactory(): IPublicClientApplication {
        return new PublicClientApplication(msalConfig);
    }
    
    /**
     * Set your default interaction type for MSALGuard here. If you have any
        * additional scopes you want the user to consent upon login, add them here as well.
        */
    export function MsalGuardConfigurationFactory(): MsalGuardConfiguration {
        return {
        interactionType: InteractionType.Redirect,
        authRequest: loginRequest
        };
    }
    
    @NgModule({
        declarations: [
        AppComponent,
        HomeComponent,
        GuardedComponent,
        ],
        imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatTableModule,
        MatMenuModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatDialogModule,
        MatIconModule,
        MsalModule,
        ],
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
            useFactory: MsalGuardConfigurationFactory,
        },
        MsalService,
        MsalBroadcastService,
        MsalGuard,
        ],
        bootstrap: [AppComponent, MsalRedirectComponent],
    })
    export class AppModule { }
    ```

    このコードは、MSAL インスタンスを初期化し、MSALGuard の既定の対話の種類を設定します。 また、認証に必要なサービスとコンポーネントも提供します。

---

### アプリケーションに認証コードを追加する

このセクションでは、ユーザー認証とセッション管理を処理する認証コードをアプリケーションに追加します。 テナントの種類に基づいて適切なタブを選択します。

## [従業員テナント](#tab/workforce-tenant)
1. `src/app/app.component.ts` ファイルを開き、内容を次のコードに置き換えます。

    ```typescript
    // Required for Angular
    import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
    
    // Required for MSAL
    import { MsalService, MsalBroadcastService, MSAL_GUARD_CONFIG, MsalGuardConfiguration } from '@azure/msal-angular';
    import { EventMessage, EventType, InteractionStatus, RedirectRequest } from '@azure/msal-browser';
    
    // Required for RJXS
    import { Subject } from 'rxjs';
    import { filter, takeUntil } from 'rxjs/operators';
    
    @Component({
      selector: 'app-root',
      templateUrl: './app.component.html'
    })
    export class AppComponent implements OnInit, OnDestroy {
      title = 'Angular - MSAL Example';
      loginDisplay = false;
      tokenExpiration: string = '';
      private readonly _destroying$ = new Subject<void>();
    
      constructor(
        @Inject(MSAL_GUARD_CONFIG) private msalGuardConfig: MsalGuardConfiguration,
        private authService: MsalService,
        private msalBroadcastService: MsalBroadcastService
      ) { }
    
      // On initialization of the page, display the page elements based on the user state
      ngOnInit(): void {
        this.msalBroadcastService.inProgress$
            .pipe(
            filter((status: InteractionStatus) => status === InteractionStatus.None),
            takeUntil(this._destroying$)
          )
          .subscribe(() => {
            this.setLoginDisplay();
          });
    
          // Used for storing and displaying token expiration
          this.msalBroadcastService.msalSubject$.pipe(filter((msg: EventMessage) => msg.eventType === EventType.ACQUIRE_TOKEN_SUCCESS)).subscribe(msg => {
          this.tokenExpiration=  (msg.payload as any).expiresOn;
          localStorage.setItem('tokenExpiration', this.tokenExpiration);
        });
      }
    
      // If the user is logged in, present the user with a "logged in" experience
      setLoginDisplay() {
        this.loginDisplay = this.authService.instance.getAllAccounts().length > 0;
      }
    
      // Log the user in and redirect them if MSAL provides a redirect URI otherwise go to the default URI
      login() {
        if (this.msalGuardConfig.authRequest) {
          this.authService.loginRedirect({ ...this.msalGuardConfig.authRequest } as RedirectRequest);
        } else {
          this.authService.loginRedirect();
        }
      }
    
      // Log the user out
      logout() {
        this.authService.logoutRedirect();
      }
    
      ngOnDestroy(): void {
        this._destroying$.next(undefined);
        this._destroying$.complete();
      }
    }
    ```

    このコードは、MSAL と Angular を統合してユーザー認証を管理します。 これはサインイン状態の変更をリッスンして、サインイン状態を表示し、トークン取得イベントを処理して、Microsoft Entra の構成に基づいてユーザーをログインまたはログアウトさせる手段を提供します。
2. ファイルを保存します。

## [外部テナント](#tab/external-tenant)
1. *src/app/app.component.ts* を開き、コードを次のように置き換えて、ポイント オブ プレゼンス (POP) を使用してユーザーをサインインさせます。 このコードでは、MSAL Angular ライブラリを使用してユーザーをサインインします。

    ```javascript
      import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
      import {
        MsalService,
        MsalBroadcastService,
        MSAL_GUARD_CONFIG,
        MsalGuardConfiguration,
      } from '@azure/msal-angular';
      import {
        AuthenticationResult,
        InteractionStatus,
        InteractionType,
        PopupRequest,
        RedirectRequest,
        EventMessage,
        EventType
      } from '@azure/msal-browser';
      import { Subject } from 'rxjs';
      import { filter, takeUntil } from 'rxjs/operators';
    
      @Component({
        selector: 'app-root',
        templateUrl: './app.component.html',
        styleUrls: ['./app.component.css'],
      })
      export class AppComponent implements OnInit, OnDestroy {
        title = 'Microsoft identity platform';
        loginDisplay = false;
        isIframe = false;
    
        private readonly _destroying$ = new Subject<void>();
    
        constructor(
          @Inject(MSAL_GUARD_CONFIG) private msalGuardConfig: MsalGuardConfiguration,
          private authService: MsalService,
          private msalBroadcastService: MsalBroadcastService,
        ) { }
    
        ngOnInit(): void {
          this.isIframe = window !== window.parent && !window.opener;
          this.setLoginDisplay();
          this.authService.instance.enableAccountStorageEvents(); // Optional - This will enable ACCOUNT_ADDED and ACCOUNT_REMOVED events emitted when a user logs in or out of another tab or window
    
          /**
           * You can subscribe to MSAL events as shown below. For more info,
           * visit: https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/docs/v2-docs/events.md
           */
          this.msalBroadcastService.inProgress$
            .pipe(
              filter(
                (status: InteractionStatus) => status === InteractionStatus.None
              ),
              takeUntil(this._destroying$)
            )
            .subscribe(() => {
              this.setLoginDisplay();
              this.checkAndSetActiveAccount();
            });
    
          this.msalBroadcastService.msalSubject$
            .pipe(
              filter(
                (msg: EventMessage) => msg.eventType === EventType.LOGOUT_SUCCESS
              ),
              takeUntil(this._destroying$)
            )
            .subscribe((result: EventMessage) => {
              this.setLoginDisplay();
              this.checkAndSetActiveAccount();
            });
    
          this.msalBroadcastService.msalSubject$
            .pipe(
              filter(
                (msg: EventMessage) => msg.eventType === EventType.LOGIN_SUCCESS
              ),
              takeUntil(this._destroying$)
            )
            .subscribe((result: EventMessage) => {
              const payload = result.payload as AuthenticationResult;
              this.authService.instance.setActiveAccount(payload.account);
            });
        }
    
        setLoginDisplay() {
          this.loginDisplay = this.authService.instance.getAllAccounts().length > 0;
        }
    
        checkAndSetActiveAccount() {
          /**
           * If no active account set but there are accounts signed in, sets first account to active account
           * To use active account set here, subscribe to inProgress$ first in your component
           * Note: Basic usage demonstrated. Your app may require more complicated account selection logic
           */
          let activeAccount = this.authService.instance.getActiveAccount();
    
          if (!activeAccount && this.authService.instance.getAllAccounts().length > 0) {
            let accounts = this.authService.instance.getAllAccounts();
            // add your code for handling multiple accounts here
            this.authService.instance.setActiveAccount(accounts[0]);
          }
        }
    
        login() {
          if (this.msalGuardConfig.interactionType === InteractionType.Popup) {
            if (this.msalGuardConfig.authRequest) {
              this.authService.loginPopup({
                ...this.msalGuardConfig.authRequest,
              } as PopupRequest)
                .subscribe((response: AuthenticationResult) => {
                  this.authService.instance.setActiveAccount(response.account);
                });
            } else {
              this.authService.loginPopup()
                .subscribe((response: AuthenticationResult) => {
                  this.authService.instance.setActiveAccount(response.account);
                });
            }
          } else {
            if (this.msalGuardConfig.authRequest) {
              this.authService.loginRedirect({
                ...this.msalGuardConfig.authRequest,
              } as RedirectRequest);
            } else {
              this.authService.loginRedirect();
            }
          }
        }
    
        logout() {
    
          if (this.msalGuardConfig.interactionType === InteractionType.Popup) {
            this.authService.logoutPopup({
              account: this.authService.instance.getActiveAccount(),
            });
          } else {
            this.authService.logoutRedirect({
              account: this.authService.instance.getActiveAccount(),
            });
          }
        }
    
        // unsubscribe to events when component is destroyed
        ngOnDestroy(): void {
          this._destroying$.next(undefined);
          this._destroying$.complete();
        }
      }
    ```

    このコードは、サインイン状態の変更をリッスンし、サインイン状態を表示し、Microsoft Entra の構成に基づいてユーザーをログインまたはログアウトする方法を提供します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-single-page-apps-angular-sign-in-users-app"} -->
## チュートリアル: Angular シングルページ アプリ (SPA) でユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-sign-in-users-app
- Service: identity-platform
- Article date: 2025-02-20
- Summary: Microsoft Entra テナントの Angular シングルページ アプリ (SPA) でユーザーをサインインさせ、認証を管理し、ユーザー アクセスをセキュリティで保護します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、Angular シングルページ アプリケーション (SPA) の構築と Microsoft ID プラットフォームを使用した認証の追加を示すシリーズのパート 2 です。 [このシリーズのパート 1](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-prepare-app) では、Angular SPA を作成し、初期構成を追加しました。

このチュートリアルでは、次の操作を行います。

- サインインとサインアウトを追加する

### 前提条件

- チュートリアルの前提条件と手順の完了 [: Angular シングルページ アプリケーションを作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-prepare-app)

### サインインとサインアウトの機能をアプリに追加する

このセクションでは、Angular アプリケーションでのサインインとサインアウトの機能をサポートするコンポーネントを追加します。 これらのコンポーネントを使用すると、ユーザーはセッションの認証と管理を行うことができます。 認証状態に基づいてユーザーを適切なコンポーネントに誘導するルーティングをアプリケーションに追加します。

## [従業員テナント](#tab/workforce-tenant)
Angular アプリケーションでサインインとサインアウトの機能を有効にするには、次の手順に従います。

1. `src/app/app.component.html` ファイルを開き、その内容を次のコードに置き換えます。

    ```html
    <a class="navbar navbar-dark bg-primary" variant="dark" href="/">
        <a class="navbar-brand"> Microsoft Identity Platform </a>
        <a>
            <button *ngIf="!loginDisplay" class="btn btn-secondary" (click)="login()">Sign In</button>
            <button *ngIf="loginDisplay" class="btn btn-secondary" (click)="logout()">Sign Out</button>
        </a>
    </a>
    <a class="profileButton">
        <a [routerLink]="['profile']" class="btn btn-secondary" *ngIf="loginDisplay">View Profile</a> 
    </a>
    <div class="container">
        <router-outlet></router-outlet>
    </div>
    ```

    このコードは、Angular アプリにナビゲーション バーを実装します。 これにより、ユーザー認証の状態に基づいて **[サインイン]** と **[サインアウト]** ボタンが動的に表示され、ログインしているユーザーの **[プロファイルの表示]** ボタンが含まれているため、アプリケーションのユーザー インターフェイスが強化されます。 `login()` の `logout()` および `src/app/app.component.ts` メソッドは、ボタンが選択されたときに呼び出されます。
2. `src/app/app-routing.module.ts` ファイルを開き、その内容を次のコードに置き換えます。

    ```typescript
    // Required for Angular
    import { NgModule } from '@angular/core';
    
    // Required for the Angular routing service
    import { Routes, RouterModule } from '@angular/router';
    
    // Required for the "Profile" page
    import { ProfileComponent } from './profile/profile.component';
    
    // Required for the "Home" page
    import { HomeComponent } from './home/home.component';
    
    // MsalGuard is required to protect routes and require authentication before accessing protected routes
    import { MsalGuard } from '@azure/msal-angular';
    
    // Define the possible routes
    // Specify MsalGuard on routes to be protected
    // '**' denotes a wild card
    const routes: Routes = [
      {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [
          MsalGuard
        ]
      },
      {
        path: '**',
        component: HomeComponent
      }
    ];
    
    // Create an NgModule that contains all the directives for the routes specified above
    @NgModule({
      imports: [RouterModule.forRoot(routes, {
        useHash: true
      })],
      exports: [RouterModule]
    })
    export class AppRoutingModule { }
    ```

    このコード スニペットでは、**プロファイル** と**ホーム** コンポーネントのパスを確立して、Angular アプリケーションのルーティングを構成します。 `MsalGuard` を使用して、**プロファイル** ルートに認証を適用しますが、一致しないパスはすべて **ホーム** コンポーネントにリダイレクトされます。
3. `src/app/home/home.component.ts` ファイルを開き、その内容を次のコードに置き換えます。

    ```typescript
    // Required for Angular
    import { Component, OnInit } from '@angular/core';
    
    // Required for MSAL
    import { MsalBroadcastService, MsalService } from '@azure/msal-angular';
    
    // Required for Angular multi-browser support
    import { EventMessage, EventType, AuthenticationResult } from '@azure/msal-browser';
    
    // Required for RJXS observables
    import { filter } from 'rxjs/operators';
    
    @Component({
      selector: 'app-home',
      templateUrl: './home.component.html'
    })
    export class HomeComponent implements OnInit {
      constructor(
        private authService: MsalService,
        private msalBroadcastService: MsalBroadcastService
      ) { }
    
      // Subscribe to the msalSubject$ observable on the msalBroadcastService
      // This allows the app to consume emitted events from MSAL
      ngOnInit(): void {
        this.msalBroadcastService.msalSubject$
          .pipe(
            filter((msg: EventMessage) => msg.eventType === EventType.LOGIN_SUCCESS),
          )
          .subscribe((result: EventMessage) => {
            const payload = result.payload as AuthenticationResult;
            this.authService.instance.setActiveAccount(payload.account);
          });
      }
    }
    ```

    このコードでは、Microsoft Authentication Library (MSAL) と統合する `HomeComponent` と呼ばれる Angular コンポーネントが設定されます。 `ngOnInit` ライフサイクル フックでは、コンポーネントは `msalSubject$` から監視可能な `MsalBroadcastService` をサブスクライブし、ログイン成功イベントをフィルター処理します。 ログイン イベントが発生すると、認証結果が取得され、`MsalService` にアクティブなアカウントが設定され、アプリケーションでユーザー セッションを管理できるようになります。
4. `src/app/home/home.component.html` ファイルを開き、その内容を次のコードに置き換えます。

    ```html
    <div class="title">
        <h5>
            Welcome to the Microsoft Authentication Library For JavaScript - Angular SPA
        </h5>
        <p >View your data from Microsoft Graph by clicking the "View Profile" link above.</p>
    </div>
    ```

    このコードは、アプリにユーザーを迎え入れ、ユーザーに **[プロファイルの表示]** リンクをクリックして Microsoft Graph データを表示するようにダイアログを表示します。
5. `src/main.ts` ファイルを開き、その内容を次のコードに置き換えます。

    ```typescript
    import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
    
    import { AppModule } from './app/app.module';
    
    platformBrowserDynamic().bootstrapModule(AppModule)
      .catch(err => console.error(err));
    ```

    コード スニペットは、Angular のプラットフォーム ブラウザーの動的モジュールから `platformBrowserDynamic`を、アプリケーションのモジュール ファイルから `AppModule` をインポートします。 その後、`platformBrowserDynamic()` を使用して `AppModule` をブートストラップし、Angular アプリケーションを初期化します。 ブートストラップ プロセス中に発生したエラーはすべてキャッチされ、コンソールに記録されます。
6. `src/index.html` ファイルを開き、その内容を次のコードに置き換えます。

    ```html
    <!doctype html>
    <html lang="en">
      <head>
        <meta charset="utf-8">
        <title>MSAL For JavaScript - Angular SPA</title>
      </head>
      <body>
        <app-root></app-root>
        <app-redirect></app-redirect>
      </body>
    </html>
    ```

    このコード スニペットでは、言語として英語と UTF-8 文字エンコードを使用する HTML5 ドキュメントを定義します。 タイトルを "MSAL For JavaScript - Angular SPA" に設定します。本文には、メイン エントリ ポイントとして `<app-root>` コンポーネントと、リダイレクト機能の `<app-redirect>` コンポーネントが含まれています。
7. `src/styles.css` ファイルを開き、その内容を次のコードに置き換えます。

    ```css
    body {
      margin: 0;
      font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
        'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
        sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }
    
    code {
      font-family: source-code-pro, Menlo, Monaco, Consolas, 'Courier New',
        monospace;
    }
    
    .app {
      text-align: center;
      padding: 8px;
    }
    
    .title{
      text-align: center;
      padding: 18px;
    }

    .profile{
      text-align: center;
      padding: 18px;
    }
    
    .profileButton{
      display: flex;
      justify-content: center;
      padding: 18px;
    }
    ```

    CSS コードは、本文のフォントを最新の sans-serif スタックに設定し、既定の余白を削除し、読みやすさを向上させるためにフォント のスムージングを適用して、Web ページのスタイルを設定します。 テキストを中央に配置し、`.app`、`.title`、`.profile` クラスにパディングを追加します。一方、 `.profileButton` クラスは flexbox を使用してその要素を中央揃えします。

## [外部テナント](#tab/external-tenant)
1. *src/app/app.component.html* を開き、既存のコードを次のコード スニペットに置き換えます。

    ```html
      <mat-toolbar color="primary">
          <a class="title" href="/">{{ title }}</a>
          <div class="toolbar-spacer"></div>
          <a mat-button [routerLink]="['guarded']">Guarded Component</a>
          <button mat-raised-button *ngIf="!loginDisplay" (click)="login()">Login</button>
          <button mat-raised-button color="accent" *ngIf="loginDisplay" (click)="logout()">Logout</button>
        </mat-toolbar>
        <div class="container">
          <!--This is to avoid reload during acquireTokenSilent() because of hidden iframe -->
          <router-outlet *ngIf="!isIframe"></router-outlet>
        </div>
        <footer *ngIf="loginDisplay">
          <mat-toolbar>
            <div class="footer-text"> How did we do? <a
                href="https://forms.office.com/Pages/ResponsePage.aspx?id=v4j5cvGGr0GRqy180BHbR_ivMYEeUKlEq8CxnMPgdNZUNDlUTTk2NVNYQkZSSjdaTk5KT1o4V1VVNS4u"
                target="_blank"> Share your experience with us!</a>
            </div>
          </mat-toolbar>
        </footer>
    ```

    このコード スニペットは、Angular アプリケーションにナビゲーション バーを追加します。 ナビゲーション バーには、ユーザーがアプリケーションにサインインおよびサインアウトできるようにするタイトルボタンと **ログイン** ボタンと **ログアウト** ボタンが含まれています。
2. *src/app/app.component.css* を開き、コードを次のスニペットに置き換えます。

    ```css
    .toolbar-spacer {
      flex: 1 1 auto;
    }
    
    a.title {
      color: white;
    }
    
    footer {
      position: fixed;
      left: 0;
      bottom: 0;
      width: 100%;
      color: white;
      text-align: center;
    }
    
    .footer-text {
      font-size: small;
      text-align: center;
      flex: 1 1 auto;
    }
    ```

    このコード スニペットは、Angular アプリケーションのナビゲーション バーとフッターのスタイルを設定します。 タイトルの色を白に設定し、フッター テキストを中央に揃え、フッター テキストのサイズを調整します。
3. *src/app/app-routing.module.ts* を開き、ファイルの内容全体を次のスニペットに置き換えます。 これにより、 `home` および `guarded` コンポーネントにルートが追加されます。

    ```javascript
    import { NgModule } from '@angular/core';
    import { RouterModule, Routes } from '@angular/router';
    import { BrowserUtils } from '@azure/msal-browser';
    import { MsalGuard } from '@azure/msal-angular';
    
    import { HomeComponent } from './home/home.component';
    import { GuardedComponent } from './guarded/guarded.component';
    
    /**
     * MSAL Angular can protect routes in your application
        * using MsalGuard. For more info, visit:
        * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/docs/v2-docs/initialization.md#secure-the-routes-in-your-application
        */
    const routes: Routes = [
        {
        path: 'guarded',
        component: GuardedComponent,
        canActivate: [
            MsalGuard
        ]
        },
        {
        path: '',
        component: HomeComponent,
        },
    ];
    
    @NgModule({
        imports: [
        RouterModule.forRoot(routes, {
            // Don't perform initial navigation in iframes or popups
            initialNavigation:
            !BrowserUtils.isInIframe() && !BrowserUtils.isInPopup()
                ? 'enabledNonBlocking'
                : 'disabled', // Set to enabledBlocking to use Angular Universal
        }),
        ],
        exports: [RouterModule],
    })
    export class AppRoutingModule { }
    ```
4. *src/styles.css* を開き、既存のコードを次のコード スニペットに置き換えます。

    ```css
    @import '~@angular/material/prebuilt-themes/deeppurple-amber.css';
    html, body { height: 100%; }
    body { margin: 0; font-family: Roboto, "Helvetica Neue", sans-serif; }
    ```

    このコード スニペットは、Angular Material の事前構築済みテーマをインポートし、HTML 要素と本文要素の高さを設定します。 既定の余白を削除し、フォント ファミリを設定します。
5. *src/app/home/home.component.ts* を開き、既存のコードを次のコード スニペットに置き換えます。

    ```javascript
    import { Component, Inject, OnInit } from '@angular/core';
    import { Subject } from 'rxjs';
    import { filter } from 'rxjs/operators';
    
    import { MsalBroadcastService, MsalGuardConfiguration, MsalService, MSAL_GUARD_CONFIG } from '@azure/msal-angular';
    import { AuthenticationResult, InteractionStatus, InteractionType } from '@azure/msal-browser';
    
    import { createClaimsTable } from '../claim-utils';
    
    @Component({
      selector: 'app-home',
      templateUrl: './home.component.html',
      styleUrls: ['./home.component.css'],
    })
    export class HomeComponent implements OnInit {
      loginDisplay = false;
      dataSource: any = [];
      displayedColumns: string[] = ['claim', 'value', 'description'];
    
      private readonly _destroying$ = new Subject<void>();
    
      constructor(
        @Inject(MSAL_GUARD_CONFIG)
        private msalGuardConfig: MsalGuardConfiguration,
        private authService: MsalService,
        private msalBroadcastService: MsalBroadcastService
      ) { }
    
      ngOnInit(): void {
    
        this.msalBroadcastService.inProgress$
          .pipe(
            filter((status: InteractionStatus) => status === InteractionStatus.None)
          )
          .subscribe(() => {
            this.setLoginDisplay();
            this.getClaims(
              this.authService.instance.getActiveAccount()?.idTokenClaims
            );
          });
      }
    
      setLoginDisplay() {
        this.loginDisplay = this.authService.instance.getAllAccounts().length > 0;
      }
    
      getClaims(claims: any) {
        if (claims) {
          const claimsTable = createClaimsTable(claims);
          this.dataSource = [...claimsTable];
        }
      }
    
      signUp() {
        if (this.msalGuardConfig.interactionType === InteractionType.Popup) {
          this.authService.loginPopup({
            scopes: [],
            prompt: 'create',
          })
            .subscribe((response: AuthenticationResult) => {
              this.authService.instance.setActiveAccount(response.account);
            });
        } else {
          this.authService.loginRedirect({
            scopes: [],
            prompt: 'create',
          });
        }
    
      }
    
      // unsubscribe to events when component is destroyed
      ngOnDestroy(): void {
        this._destroying$.next(undefined);
        this._destroying$.complete();
      }
    }
    ```

    このコード スニペットは、Angular アプリケーションのホーム ページを管理する `HomeComponent` クラスを定義します。 コンポーネントは、アプリケーションの認証状態を監視するために、`inProgress$`から監視できる`MsalBroadcastService`をサブスクライブします。 認証状態が変更されると、コンポーネントはログイン表示を更新し、アクティブなアカウントの ID トークンから要求を取得します。 `signUp()`メソッドは、ユーザーが **[サインアップ**] ボタンをクリックすると呼び出され、認証プロセスが開始されます。
6. *src/app/home/home.component.html* を開き、既存のコードを次のコード スニペットに置き換えます。 このコードは、アプリケーションのホーム ページの HTML 要素を定義します。

    ```html
    <mat-card class="card-section" *ngIf="!loginDisplay">
      <mat-card-title>Angular single-page application built with MSAL Angular</mat-card-title>
      <mat-card-subtitle>Sign in with Microsoft Entra External ID</mat-card-subtitle>
      <mat-card-content>This sample demonstrates how to configure MSAL Angular to sign up, sign in and sign out with Microsoft Entra External ID</mat-card-content>
      <button mat-raised-button color="primary" (click)="signUp()">Sign up</button>
    </mat-card>
    <br>
    <p class="text-center" *ngIf="loginDisplay"> See below the claims in your <strong> ID token </strong>. For more
      information, visit: <span>
        <a href="https://docs.microsoft.com/en-us/azure/active-directory/develop/id-tokens#claims-in-an-id-token">
          docs.microsoft.com </a>
      </span>
    </p>
    <div id="table-container">
      <table mat-table [dataSource]="dataSource" class="mat-elevation-z8" *ngIf="loginDisplay">
        <!-- Claim Column -->
        <ng-container matColumnDef="claim">
          <th mat-header-cell *matHeaderCellDef> Claim </th>
          <td mat-cell *matCellDef="let element"> {{element.claim}} </td>
        </ng-container>
        <!-- Value Column -->
        <ng-container matColumnDef="value">
          <th mat-header-cell *matHeaderCellDef> Value </th>
          <td mat-cell *matCellDef="let element"> {{element.value}} </td>
        </ng-container>
        <!-- Value Column -->
        <ng-container matColumnDef="description">
          <th mat-header-cell *matHeaderCellDef> Description </th>
          <td mat-cell *matCellDef="let element"> {{element.description}} </td>
        </ng-container>
        <tr mat-header-row *matHeaderRowDef="displayedColumns sticky: true"></tr>
        <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>
      </table>
    </div>
    ```

    このコード スニペットは、Angular アプリケーションのホーム ページの HTML 要素を定義します。 これには、ユーザーが Microsoft Entra External ID で認証するための **[サインアップ** ] ボタンを含むカード セクションが含まれています。
7. *src/app/home/home.component.css* を開きます。 既存のコードを次のコード スニペットに置き換えます。

    ```css
    #table-container {
      height: '100vh';
      overflow: auto;
    }
    
    table {
      margin: 3% auto 1% auto;
      width: 70%;
    }
    
    .mat-row {
      height: auto;
    }
    
    .mat-cell {
      padding: 8px 8px 8px 0;
    }
    
    p {
      text-align: center;
    }
    
    .card-section {
      margin: 10%;
      padding: 5%;
    }
    ```

    このコード スニペットは、Angular アプリケーションのホーム ページの HTML 要素にスタイルを設定します。 テーブル コンテナーの高さとオーバーフローのプロパティを設定し、テーブルの余白と幅を調整し、段落要素のテキストを配置します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-v2-nodejs-console"} -->
## チュートリアル: Node.js コンソール デーモン アプリで Microsoft Graph を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-console
- Service: identity-platform / workforce
- Article date: 2024-04-09
- Summary: このチュートリアルでは、Microsoft Graph を呼び出すためのコンソール デーモン アプリを作成します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、独自の ID を使用して Microsoft Graph API を呼び出すコンソール デーモン アプリを作成します。 作成するデーモン アプリでは、[Node.js 用の Microsoft Authentication Library (MSAL)](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) を使用します。

このチュートリアルでは、次の手順に従います。

- Azure portal でアプリケーションを登録する
- Node.js コンソール デーモン アプリ プロジェクトを作成する
- アプリに認証ロジックを追加する
- アプリの登録の詳細を追加する
- Web API を呼び出すメソッドを追加する
- アプリケーションをテストする

### 前提条件

- [Node.js](https://nodejs.org/en/download/)
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター

### アプリケーションを登録する

まず、[Microsoft ID プラットフォームへのアプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)に関するページの手順に従って、アプリを登録します。

アプリの登録には、次の設定を使用します。

- 名前: `NodeDaemonApp` (推奨)
- サポートされているアカウントの種類: **この組織のディレクトリ内のアカウントのみ**
- API のアクセス許可: **[Microsoft API]**&gt;**[Microsoft Graph]**&gt;**[アプリケーションのアクセス許可]**&gt;`User.Read.All`
- クライアント シークレット: `*********` (後の手順で使用するためにこの値を記録します。これは 1 回しか表示されません)

### プロジェクトを作成する

1. まず、この Node.js チュートリアル プロジェクトのディレクトリを作成します。 例: *NodeDaemonApp*。
2. ターミナルで、作成したディレクトリ (プロジェクト ルート) に移動し、次のコマンドを実行します。

    ```console
    npm init -y
    npm install --save dotenv yargs axios @azure/msal-node
    ```
3. 次に、プロジェクト ルートの *package.json* ファイルを編集し、次のように `main` の値の前に `bin/` を付けます。

    ```json
    "main": "bin/index.js",
    ```
4. 次に、*bin* ディレクトリを作成し、*bin* 内に、*index.js* という名前の新しいファイルに次のコードを追加します。

    ```JavaScript
    #!/usr/bin/env node
    
    // read in env settings
    require('dotenv').config();
    
    const yargs = require('yargs');
    
    const fetch = require('./fetch');
    const auth = require('./auth');
    
    const options = yargs
        .usage('Usage: --op <operation_name>')
        .option('op', { alias: 'operation', describe: 'operation name', type: 'string', demandOption: true })
        .argv;
    
    async function main() {
        console.log(`You have selected: ${options.op}`);
    
        switch (yargs.argv['op']) {
            case 'getUsers':
    
                try {
                    // here we get an access token
                    const authResponse = await auth.getToken(auth.tokenRequest);
    
                    // call the web API with the access token
                    const users = await fetch.callApi(auth.apiConfig.uri, authResponse.accessToken);
    
                    // display result
                    console.log(users);
                } catch (error) {
                    console.log(error);
                }
    
                break;
            default:
                console.log('Select a Graph operation first');
                break;
        }
    };
    
    main();
    ```

作成した *index.js* ファイルは、次に作成する他の 2 つのノード モジュールを参照します。

- *auth.js* - MSAL Node を使用して、Microsoft ID プラットフォームからアクセス トークンを取得します。
- *fetch.js* - API への HTTP 要求にアクセス トークン (*auth.js* で取得) を含めて、Microsoft Graph API からデータを要求します。

チュートリアルの最後に、プロジェクトのファイルとディレクトリの構造が次のように表示されます。

```
NodeDaemonApp/
├── bin
│   ├── auth.js
│   ├── fetch.js
│   ├── index.js
├── package.json
└── .env
```

### 認証ロジックを追加する

*bin* ディレクトリ内で、*auth.js* という名前の新しいファイルに次のコードを追加します。 *auth.js* のコードは、Microsoft Graph API の要求に含めるためのアクセス トークンを Microsoft ID プラットフォームから取得します。

```JavaScript
const msal = require('@azure/msal-node');

/**
 * Configuration object to be passed to MSAL instance on creation.
 * For a full list of MSAL Node configuration parameters, visit:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md
 */
const msalConfig = {
    auth: {
        clientId: process.env.CLIENT_ID,
        authority: process.env.AAD_ENDPOINT + '/' + process.env.TENANT_ID,
        clientSecret: process.env.CLIENT_SECRET,
    }
};

/**
 * With client credentials flows permissions need to be granted in the portal by a tenant administrator.
 * The scope is always in the format '<resource>/.default'. For more, visit:
 * https://learn.microsoft.com/azure/active-directory/develop/v2-oauth2-client-creds-grant-flow
 */
const tokenRequest = {
    scopes: [process.env.GRAPH_ENDPOINT + '/.default'],
};

const apiConfig = {
    uri: process.env.GRAPH_ENDPOINT + '/v1.0/users',
};

/**
 * Initialize a confidential client application. For more info, visit:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/initialize-confidential-client-application.md
 */
const cca = new msal.ConfidentialClientApplication(msalConfig);

/**
 * Acquires token with client credentials.
 * @param {object} tokenRequest
 */
async function getToken(tokenRequest) {
    return await cca.acquireTokenByClientCredential(tokenRequest);
}

module.exports = {
    apiConfig: apiConfig,
    tokenRequest: tokenRequest,
    getToken: getToken
};
```

上記のコード スニペットでは、まず構成オブジェクト (*msalConfig*) を作成し、これを渡すことによって MSAL [ConfidentialClientApplication](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/initialize-confidential-client-application.md) を初期化します。 次に、**クライアントの資格情報**を使用してトークンを取得するためのメソッドを作成し、最後に、*main.js* からアクセスできるようにこのモジュールを公開します。 このモジュールの構成パラメーターは、次の手順で作成する環境ファイルから取得されます。

### アプリの登録の詳細を追加する

トークンを取得するときに使用されるアプリ登録の詳細を格納するための環境ファイルを作成します。 これを行うには、サンプルのルート フォルダー (*NodeDaemonApp*) 内に *.env* という名前のファイルを作成し、次のコードを追加します。

```
# Credentials
TENANT_ID=Enter_the_Tenant_Id_Here
CLIENT_ID=Enter_the_Application_Id_Here
CLIENT_SECRET=Enter_the_Client_Secret_Here

# Endpoints
AAD_ENDPOINT=Enter_the_Cloud_Instance_Id_Here/
GRAPH_ENDPOINT=Enter_the_Graph_Endpoint_Here/
```

これらの詳細には、Azure アプリ登録ポータルから取得した値を入力します。

- `Enter_the_Tenant_Id_here`は、次のいずれかにする必要があります。
    - ご自分のアプリケーションで "*この組織のディレクトリ内のアカウント*" がサポートされる場合は、この値を**テナント ID** または**テナント名**に置き換えます。 たとえば、「 `contoso.microsoft.com` 」のように入力します。
    - アプリケーションで "*任意の組織のディレクトリ内のアカウント*" がサポートされる場合は、この値を `organizations` に置き換えます。
    - アプリケーションで "*任意の組織のディレクトリ内のアカウントと個人用の Microsoft アカウント*" がサポートされる場合は、この値を `common` に置き換えます。
    - "*個人用の Microsoft アカウントのみ*" にサポートを制限するには、この値を `consumers` に置き換えます。
- `Enter_the_Application_Id_Here`:登録したアプリケーションの**アプリケーション (クライアント) ID**。
- `Enter_the_Cloud_Instance_Id_Here`:アプリケーションが登録されている Azure クラウド インスタンス。
    - メイン ("*グローバル*") Azure クラウドの場合は、「`https://login.microsoftonline.com`」と入力します。
    - **各国**のクラウド (中国など) の場合は、「[各国のクラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud)」に適切な値が記載されています。
- `Enter_the_Graph_Endpoint_Here`は、アプリケーションが通信する必要がある、Microsoft Graph API のインスタンスです。
    - **グローバル** Microsoft Graph API エンドポイントの場合は、この文字列の両方のインスタンスを `https://graph.microsoft.com` に置き換えます。
    - **各国**のクラウドのデプロイにおけるエンドポイントの場合は、Microsoft Graph のドキュメントで「[各国のクラウドでのデプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)」を参照してください。

### Web API を呼び出すメソッドを追加する

*bin* フォルダーに *fetch.js* という名前の別のファイルを作成し、Microsoft Graph API への REST 呼び出しを行うための次のコードを追加します。

```javascript
const axios = require('axios');

/**
 * Calls the endpoint with authorization bearer token.
 * @param {string} endpoint
 * @param {string} accessToken
 */
async function callApi(endpoint, accessToken) {

    const options = {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    };

    console.log('request made to web API at: ' + new Date().toString());

    try {
        const response = await axios.get(endpoint, options);
        return response.data;
    } catch (error) {
        console.log(error)
        return error;
    }
};

module.exports = {
    callApi: callApi
};
```

ここでは、`callApi` メソッドを使用して、アクセス トークンを必要とする保護されたリソースに対して HTTP `GET` 要求を実行します。 その後、この要求からその内容が呼び出し元に返されます。 このメソッドは、取得したトークンを *HTTP Authorization ヘッダー*に追加します。 ここで保護されているリソースは、このアプリが登録されているテナント内のユーザーを表示する Microsoft Graph API [ユーザー エンドポイント](https://learn.microsoft.com/ja-jp/graph/api/user-list)です。

### アプリケーションをテストする

これでアプリケーションの作成が完了し、アプリの機能をテストする準備ができました。

プロジェクト フォルダーのルート内から次のコマンドを実行して、Node.js コンソール デーモン アプリを起動します。

```console
node . --op getUsers
```

これにより Microsoft Graph API からの JSON 応答が生成され、コンソールにユーザー オブジェクトの配列が表示されます。

```console
You have selected: getUsers
request made to web API at: Fri Jan 22 2021 09:31:52 GMT-0800 (Pacific Standard Time)
{
    '@odata.context': 'https://graph.microsoft.com/v1.0/$metadata#users',
    value: [
        {
            displayName: 'Adele Vance'
            givenName: 'Adele',
            jobTitle: 'Retail Manager',
            mail: 'AdeleV@msaltestingjs.onmicrosoft.com',
            mobilePhone: null,
            officeLocation: '18/2111',
            preferredLanguage: 'en-US',
            surname: 'Vance',
            userPrincipalName: 'AdeleV@msaltestingjs.onmicrosoft.com',
            id: '00aa00aa-bb11-cc22-dd33-44ee44ee44ee'
        }
    ]
}
```

[Image: Graph の応答が表示されたコマンド ライン インターフェイス]

### アプリケーションの動作

このアプリケーションでは、[OAuth 2.0 クライアント資格情報の認可](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)を使用します。 この種類の許可は、バックグラウンドでの実行が必要なサーバー間の相互作用に使用され、ユーザーとの即時の相互動作は必要ありません。 資格情報許可フローでは、Web サービス (機密クライアント) が別の Web サービスを呼び出すときに、ユーザーを偽装する代わりに、独自の資格情報を使用して認証を行うことができます。 この認証モデルでサポートされるアプリケーションの種類は、通常、**デーモン**または**サービス アカウント**です。

クライアントの資格情報フローのために要求するスコープは、後に `/.default` が続くリソースの名前です。 この表記は、アプリケーションの登録時に静的に宣言された "アプリケーション レベルの権限" を使用するように Microsoft Entra ID に指示します。 また、これらの API アクセス許可は、**テナント管理者**が付与する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-v2-nodejs-desktop"} -->
## チュートリアル:Electron デスクトップ アプリでユーザーのサインインと Microsoft Graph API の呼び出しを行う - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-desktop
- Service: identity-platform / workforce
- Article date: 2021-02-17
- Summary: このチュートリアルでは、ユーザーのサインインを処理すると共に、認証コード フローを使用して Microsoft ID プラットフォームからアクセス トークンを取得し、Microsoft Graph API を呼び出すことができる Electron デスクトップ アプリを構築します。

このチュートリアルでは、ユーザーのサインインを行い、PKCE による認可コード フローを使用して Microsoft Graph を呼び出す Electron デスクトップ アプリケーションを構築します。 構築するデスクトップ アプリでは、[Node.js 用の Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/) を使用します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、次の操作を行います。

- Azure portal でアプリケーションを登録する
- Electron デスクトップ アプリ プロジェクトを作成する
- アプリに認証ロジックを追加する
- Web API を呼び出すメソッドを追加する
- アプリの登録の詳細を追加する
- アプリケーションをテストする

### 前提条件

- 従業員テナント。 [既定のディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)を使用するか、新しいテナントを設定できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost`
- [Node.js](https://nodejs.org/en/download/)
- [電子](https://www.electronjs.org/)
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター

### プロジェクトを作成する

注

このチュートリアルで提供される Electron サンプルは、MSAL ノードで動作するように特別に設計されています。 MSAL-browser は、Electron アプリケーションではサポートされていません。 プロジェクトを正しく設定するには、次の手順を実行してください。

アプリケーションをホストするフォルダーを作成します (例: *ElectronDesktopApp*)。

1. 最初に、ターミナル内のプロジェクト ディレクトリに移動し、次の `npm` コマンドを実行します。

    ```console
    npm init -y
    npm install --save @azure/msal-node @microsoft/microsoft-graph-client isomorphic-fetch bootstrap jquery popper.js
    npm install --save-dev electron@20.0.0
    ```
2. 次に、*App* という名前のフォルダーを作成します。 このフォルダー内に、UI として機能する *index.html* という名前のファイルを作成します。 そこに、次のコードを追加します。

    ```html
    <!DOCTYPE html>
    <html lang="en">
    
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta http-equiv="Content-Security-Policy" content="script-src 'self'" />
        <title>MSAL Node Electron Sample App</title>
    
        <!-- adding Bootstrap 4 for UI components  -->
        <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.min.css">
    </head>
    
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand">Microsoft identity platform</a>
            <div class="btn-group ml-auto dropleft">
                <button type="button" id="signIn" class="btn btn-secondary" aria-expanded="false">
                    Sign in
                </button>
                <button type="button" id="signOut" class="btn btn-success" hidden aria-expanded="false">
                    Sign out
                </button>
            </div>
        </nav>
        <br>
        <h5 class="card-header text-center">Electron sample app calling MS Graph API using MSAL Node</h5>
        <br>
        <div class="row" style="margin:auto">
            <div id="cardDiv" class="col-md-6" style="display:none; margin:auto">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title" id="WelcomeMessage">Please sign-in to see your profile and read your mails
                        </h5>
                        <div id="profileDiv"></div>
                        <br>
                        <br>
                        <button class="btn btn-primary" id="seeProfile">See Profile</button>
                    </div>
                </div>
            </div>
        </div>
    
        <!-- importing bootstrap.js and supporting js libraries -->
        <script src="../node_modules/jquery/dist/jquery.js"></script>
        <script src="../node_modules/popper.js/dist/umd/popper.js"></script>
        <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
    
        <!-- importing app scripts | load order is important -->
        <script src="./renderer.js"></script>
    
    </body>
    
    </html>
    ```
3. 次に、*main.js* という名前のファイルを作成し、次のコードを追加します。

    ```js
    /*
     * Copyright (c) Microsoft Corporation. All rights reserved.
     * Licensed under the MIT License.
     */
    
    const path = require("path");
    const { app, ipcMain, BrowserWindow } = require("electron");
    
    const AuthProvider = require("./AuthProvider");
    const { IPC_MESSAGES } = require("./constants");
    const { protectedResources, msalConfig } = require("./authConfig");
    const getGraphClient = require("./graph");
    
    let authProvider;
    let mainWindow;
    
    function createWindow() {
        mainWindow = new BrowserWindow({
            width: 800,
            height: 600,
            webPreferences: { preload: path.join(__dirname, "preload.js") },
        });
    
        authProvider = new AuthProvider(msalConfig);
    }
    
    app.on("ready", () => {
        createWindow();
        mainWindow.loadFile(path.join(__dirname, "./index.html"));
    });
    
    app.on("window-all-closed", () => {
        app.quit();
    });
    
    app.on('activate', () => {
        // On OS X it's common to re-create a window in the app when the
        // dock icon is clicked and there are no other windows open.
        if (BrowserWindow.getAllWindows().length === 0) {
            createWindow();
        }
    });

    // Event handlers
    ipcMain.on(IPC_MESSAGES.LOGIN, async () => {
        const account = await authProvider.login();
    
        await mainWindow.loadFile(path.join(__dirname, "./index.html"));
        
        mainWindow.webContents.send(IPC_MESSAGES.SHOW_WELCOME_MESSAGE, account);
    });
    
    ipcMain.on(IPC_MESSAGES.LOGOUT, async () => {
        await authProvider.logout();
    
        await mainWindow.loadFile(path.join(__dirname, "./index.html"));
    });
    
    ipcMain.on(IPC_MESSAGES.GET_PROFILE, async () => {
        const tokenRequest = {
            scopes: protectedResources.graphMe.scopes
        };
    
        const tokenResponse = await authProvider.getToken(tokenRequest);
        const account = authProvider.account;
    
        await mainWindow.loadFile(path.join(__dirname, "./index.html"));
    
        const graphResponse = await getGraphClient(tokenResponse.accessToken)
            .api(protectedResources.graphMe.endpoint).get();
    
        mainWindow.webContents.send(IPC_MESSAGES.SHOW_WELCOME_MESSAGE, account);
        mainWindow.webContents.send(IPC_MESSAGES.SET_PROFILE, graphResponse);
    });
    ```

上記のコード スニペットでは、Electron メイン ウィンドウ オブジェクトを初期化し、Electron ウィンドウとやり取りするためのイベント ハンドラーをいくつか作成します。 また、構成パラメーターをインポートし、サインイン、サインアウト、トークンの取得を処理するための *authProvider* クラスを初期化して、Microsoft Graph API を呼び出します。

1. 同じフォルダー (*App*) 内に、*renderer.js* という名前の別のファイルを作成し、次のコードを追加します。

    ```js
    // Copyright (c) Microsoft Corporation. All rights reserved.
    // Licensed under the MIT License
    
    /**
     * The renderer API is exposed by the preload script found in the preload.ts
     * file in order to give the renderer access to the Node API in a secure and 
     * controlled way
     */
    const welcomeDiv = document.getElementById('WelcomeMessage');
    const signInButton = document.getElementById('signIn');
    const signOutButton = document.getElementById('signOut');
    const seeProfileButton = document.getElementById('seeProfile');
    const cardDiv = document.getElementById('cardDiv');
    const profileDiv = document.getElementById('profileDiv');
    
    window.renderer.showWelcomeMessage((event, account) => {
        if (!account) return;
    
        cardDiv.style.display = 'initial';
        welcomeDiv.innerHTML = `Welcome ${account.name}`;
        signInButton.hidden = true;
        signOutButton.hidden = false;
    });
    
    window.renderer.handleProfileData((event, graphResponse) => {
        if (!graphResponse) return;
    
        console.log(`Graph API responded at: ${new Date().toString()}`);
        setProfile(graphResponse);
    });
    
    // UI event handlers
    signInButton.addEventListener('click', () => {
        window.renderer.sendLoginMessage();
    });
    
    signOutButton.addEventListener('click', () => {
        window.renderer.sendSignoutMessage();
    });
    
    seeProfileButton.addEventListener('click', () => {
        window.renderer.sendSeeProfileMessage();
    });
    
    const setProfile = (data) => {
        if (!data) return;
        
        profileDiv.innerHTML = '';
    
        const title = document.createElement('p');
        const email = document.createElement('p');
        const phone = document.createElement('p');
        const address = document.createElement('p');
    
        title.innerHTML = '<strong>Title: </strong>' + data.jobTitle;
        email.innerHTML = '<strong>Mail: </strong>' + data.mail;
        phone.innerHTML = '<strong>Phone: </strong>' + data.businessPhones[0];
        address.innerHTML = '<strong>Location: </strong>' + data.officeLocation;
    
        profileDiv.appendChild(title);
        profileDiv.appendChild(email);
        profileDiv.appendChild(phone);
        profileDiv.appendChild(address);
    }
    ```

レンダラー メソッドは、*preload.js* ファイル内にある事前読み込みスクリプトによって公開され、レンダラーが安全かつ制御された方法で `Node API` にアクセスできるようにします。

1. 次に、新規の *preload.js* ファイルを作成し、次のコードを追加します。

    ```js
    // Copyright (c) Microsoft Corporation. All rights reserved.
    // Licensed under the MIT License
    
    const { contextBridge, ipcRenderer } = require('electron');
    
    /**
     * This preload script exposes a "renderer" API to give
     * the Renderer process controlled access to some Node APIs
     * by leveraging IPC channels that have been configured for
     * communication between the Main and Renderer processes.
     */
    contextBridge.exposeInMainWorld('renderer', {
        sendLoginMessage: () => {
            ipcRenderer.send('LOGIN');
        },
        sendSignoutMessage: () => {
            ipcRenderer.send('LOGOUT');
        },
        sendSeeProfileMessage: () => {
            ipcRenderer.send('GET_PROFILE');
        },
        handleProfileData: (func) => {
            ipcRenderer.on('SET_PROFILE', (event, ...args) => func(event, ...args));
        },
        showWelcomeMessage: (func) => {
            ipcRenderer.on('SHOW_WELCOME_MESSAGE', (event, ...args) => func(event, ...args));
        },
    });
    ```

この事前読み込みスクリプトは、メイン プロセスとレンダラー プロセス間の通信用に構成された IPC チャネルを適用して、レンダラー プロセスに一部の `Node APIs` への制御されたアクセスを提供するレンダラー API を公開します。

1. 最後に、アプリケーションの*イベント*を記述するための文字列定数を格納する **constants.js** という名前のファイルを作成します。

    ```js
    /*
     * Copyright (c) Microsoft Corporation. All rights reserved.
     * Licensed under the MIT License.
     */
    
    const IPC_MESSAGES = {
        SHOW_WELCOME_MESSAGE: 'SHOW_WELCOME_MESSAGE',
        LOGIN: 'LOGIN',
        LOGOUT: 'LOGOUT',
        GET_PROFILE: 'GET_PROFILE',
        SET_PROFILE: 'SET_PROFILE',
    }
    
    module.exports = {
        IPC_MESSAGES: IPC_MESSAGES,
    }
    ```

これで、Electron アプリのシンプルな GUI と対話が作成できました。 チュートリアルの残りの部分を完了すると、このプロジェクトのファイルとフォルダーの構造は次のようになります。

```
ElectronDesktopApp/
├── App
│   ├── AuthProvider.js
│   ├── constants.js
│   ├── graph.js
│   ├── index.html
|   ├── main.js
|   ├── preload.js
|   ├── renderer.js
│   ├── authConfig.js
├── package.json
```

### アプリに認証ロジックを追加する

*App* フォルダーに、*AuthProvider.js* という名前のファイルを作成します。 *AuthProvider.js* ファイルに、MSAL Node を使用して、ログイン、ログアウト、トークンの取得、アカウントの選択、および関連する認証タスクを処理する認証プロバイダー クラスを含めます。 そこに、次のコードを追加します。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

const { PublicClientApplication, InteractionRequiredAuthError } = require('@azure/msal-node');
const { shell } = require('electron');

class AuthProvider {
    msalConfig
    clientApplication;
    account;
    cache;

    constructor(msalConfig) {
        /**
         * Initialize a public client application. For more information, visit:
         * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/initialize-public-client-application.md
         */
        this.msalConfig = msalConfig;
        this.clientApplication = new PublicClientApplication(this.msalConfig);
        this.cache = this.clientApplication.getTokenCache();
        this.account = null;
    }

    async login() {
        const authResponse = await this.getToken({
            // If there are scopes that you would like users to consent up front, add them below
            // by default, MSAL will add the OIDC scopes to every token request, so we omit those here
            scopes: [],
        });

        return this.handleResponse(authResponse);
    }

    async logout() {
        if (!this.account) return;

        try {
            /**
             * If you would like to end the session with AAD, use the logout endpoint. You'll need to enable
             * the optional token claim 'login_hint' for this to work as expected. For more information, visit:
             * https://learn.microsoft.com/azure/active-directory/develop/v2-protocols-oidc#send-a-sign-out-request
             */
            if (this.account.idTokenClaims.hasOwnProperty('login_hint')) {
                await shell.openExternal(`${this.msalConfig.auth.authority}/oauth2/v2.0/logout?logout_hint=${encodeURIComponent(this.account.idTokenClaims.login_hint)}`);
            }

            await this.cache.removeAccount(this.account);
            this.account = null;
        } catch (error) {
            console.log(error);
        }
    }

    async getToken(tokenRequest) {
        let authResponse;
        const account = this.account || (await this.getAccount());

        if (account) {
            tokenRequest.account = account;
            authResponse = await this.getTokenSilent(tokenRequest);
        } else {
            authResponse = await this.getTokenInteractive(tokenRequest);
        }

        return authResponse || null;
    }

    async getTokenSilent(tokenRequest) {
        try {
            return await this.clientApplication.acquireTokenSilent(tokenRequest);
        } catch (error) {
            if (error instanceof InteractionRequiredAuthError) {
                console.log('Silent token acquisition failed, acquiring token interactive');
                return await this.getTokenInteractive(tokenRequest);
            }

            console.log(error);
        }
    }

    async getTokenInteractive(tokenRequest) {
        try {
            const openBrowser = async (url) => {
                await shell.openExternal(url);
            };

            const authResponse = await this.clientApplication.acquireTokenInteractive({
                ...tokenRequest,
                openBrowser,
                successTemplate: '<h1>Successfully signed in!</h1> <p>You can close this window now.</p>',
                errorTemplate: '<h1>Oops! Something went wrong</h1> <p>Check the console for more information.</p>',
            });

            return authResponse;
        } catch (error) {
            throw error;
        }
    }

    /**
     * Handles the response from a popup or redirect. If response is null, will check if we have any accounts and attempt to sign in.
     * @param response
     */
    async handleResponse(response) {
        if (response !== null) {
            this.account = response.account;
        } else {
            this.account = await this.getAccount();
        }

        return this.account;
    }

    /**
     * Calls getAllAccounts and determines the correct account to sign into, currently defaults to first account found in cache.
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-common/docs/Accounts.md
     */
    async getAccount() {
        const currentAccounts = await this.cache.getAllAccounts();

        if (!currentAccounts) {
            console.log('No accounts detected');
            return null;
        }

        if (currentAccounts.length > 1) {
            // Add choose account code here
            console.log('Multiple accounts detected, need to add choose account code.');
            return currentAccounts[0];
        } else if (currentAccounts.length === 1) {
            return currentAccounts[0];
        } else {
            return null;
        }
    }
}

module.exports = AuthProvider;
```

上記のコード スニペットでは、まず、構成オブジェクト (`PublicClientApplication`) を渡すことによって MSAL Node `msalConfig` を初期化しました。 次に、メイン モジュール (`login`) によって呼び出される `logout`、`getToken`、 の各メソッドを公開しました。 `login` と `getToken` で、MSAL Node `acquireTokenInteractive` パブリック API を使用して ID とアクセス トークンを取得します。

### Microsoft Graph SDK を追加する

*graph.js* という名前のファイルを作成します。 *graph.js* ファイルには、MSAL Node によって取得されたアクセス トークンを使用して、Microsoft Graph API 上のデータへのアクセスを容易にするために、Microsoft Graph SDK クライアントのインスタンスが含まれます。

```js
const { Client } = require('@microsoft/microsoft-graph-client');
require('isomorphic-fetch');

/**
 * Creating a Graph client instance via options method. For more information, visit:
 * https://github.com/microsoftgraph/msgraph-sdk-javascript/blob/dev/docs/CreatingClientInstance.md#2-create-with-options
 * @param {String} accessToken
 * @returns
 */
const getGraphClient = (accessToken) => {
    // Initialize Graph client
    const graphClient = Client.init({
        // Use the provided access token to authenticate requests
        authProvider: (done) => {
            done(null, accessToken);
        },
    });

    return graphClient;
};

module.exports = getGraphClient;
```

### アプリの登録の詳細を追加する

トークンを取得するときに使用されるアプリ登録の詳細を格納するための環境ファイルを作成します。 これを行うには、サンプル (*ElectronDesktopApp*) のルート フォルダー内に *authConfig.js* という名前のファイルを作成し、次のコードを追加します。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

const { LogLevel } = require("@azure/msal-node");

/**
 * Configuration object to be passed to MSAL instance on creation.
 * For a full list of MSAL.js configuration parameters, visit:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md
 */
const AAD_ENDPOINT_HOST = "Enter_the_Cloud_Instance_Id_Here"; // include the trailing slash

const msalConfig = {
    auth: {
        clientId: "Enter_the_Application_Id_Here",
        authority: `${AAD_ENDPOINT_HOST}Enter_the_Tenant_Info_Here`,
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                console.log(message);
            },
            piiLoggingEnabled: false,
            logLevel: LogLevel.Verbose,
        },
    },
};

/**
 * Add here the endpoints and scopes when obtaining an access token for protected web APIs. For more information, see:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/resources-and-scopes.md
 */
const GRAPH_ENDPOINT_HOST = "Enter_the_Graph_Endpoint_Here"; // include the trailing slash

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

これらの詳細には、Azure アプリ登録ポータルから取得した値を入力します。

- `Enter_the_Tenant_Id_here`は、次のいずれかにする必要があります。
    - ご自分のアプリケーションで "*この組織のディレクトリ内のアカウント*" がサポートされる場合は、この値を**テナント ID** または**テナント名**に置き換えます。 たとえば、「 `contoso.microsoft.com` 」のように入力します。
    - アプリケーションで "*任意の組織のディレクトリ内のアカウント*" がサポートされる場合は、この値を `organizations` に置き換えます。
    - アプリケーションで "*任意の組織のディレクトリ内のアカウントと個人用の Microsoft アカウント*" がサポートされる場合は、この値を `common` に置き換えます。
    - "*個人用の Microsoft アカウントのみ*" にサポートを制限するには、この値を `consumers` に置き換えます。
- `Enter_the_Application_Id_Here`:登録したアプリケーションの**アプリケーション (クライアント) ID**。
- `Enter_the_Cloud_Instance_Id_Here`:アプリケーションが登録されている Azure クラウド インスタンス。
    - メイン ("*グローバル*") Azure クラウドの場合は、「`https://login.microsoftonline.com/`」と入力します。
    - **各国**のクラウド (中国など) の場合は、「[各国のクラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud)」に適切な値が記載されています。
- `Enter_the_Graph_Endpoint_Here`は、アプリケーションが通信する必要がある、Microsoft Graph API のインスタンスです。
    - **グローバル** Microsoft Graph API エンドポイントの場合は、この文字列の両方のインスタンスを `https://graph.microsoft.com/` に置き換えます。
    - **各国**のクラウドのデプロイにおけるエンドポイントの場合は、Microsoft Graph のドキュメントで「[各国のクラウドでのデプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)」を参照してください。

### アプリケーションをテストする

これでアプリケーションの作成が完了し、Electron デスクトップ アプリを起動して、アプリの機能をテストする準備ができました。

1. プロジェクト フォルダーのルート内から次のコマンドを実行して、アプリを起動します。

```console
electron App/main.js
```

1. アプリケーションのメイン ウィンドウには、*index.html* ファイルの内容と **[サインイン]** ボタンが表示されるはずです。

### サインインとサインアウトをテストする

*index.html* ファイルが読み込まれたら、 **[サインイン]** を選択します。 Microsoft ID プラットフォームにサインインするように求められます。

[Image: サインイン プロンプト]

要求されたアクセス許可に同意すると、Web アプリケーションにはユーザー名が表示されます。これは、ログインが成功したことを示しています。

[Image: サインインに成功]

### Web API 呼び出しをテストする

サインインした後、**[See Profile]** を選択して、Microsoft Graph API への呼び出しからの応答で返されるユーザー プロファイル情報を表示します。 同意すると、応答で返されたプロファイル情報が表示されます。

[Image: Microsoft Graph からのプロファイル情報]

### アプリケーションの動作

ユーザーが初めて **[サインイン]** ボタンを選択すると、MSAL Node の `acquireTokenInteractive` メソッドが呼び出されます。 このメソッドは、Microsoft ID プラットフォーム エンドポイントを使用してユーザーをサインインにリダイレクトし、ユーザーの資格情報を検証して、**認証コード**を取得します。次に、そのコードを ID トークン、アクセス トークン、更新トークンに交換します。 MSAL Node では、将来使用するためにこれらのトークンもキャッシュに入れます。

ID トークンには、表示名など、ユーザーについての基本的な情報が含まれています。 アクセス トークンの有効期間は限られており、24 時間後に有効期限が切れます。 保護されたリソースにアクセスするためにこれらのトークンを使用する予定がある場合は、アプリケーションの有効なユーザーに対してトークンが発行されたことを保証するために、バックエンド サーバーでトークンを検証する "*必要があります*"。

このチュートリアルで作成したデスクトップ アプリでは、アクセス トークンを要求ヘッダーのベアラー トークン ([RFC 6750](https://tools.ietf.org/html/rfc6750)) として使用して、Microsoft Graph API への REST 呼び出しを行います。

Microsoft Graph API には、ユーザーのプロファイルを読み取るための *user.read* スコープが必要です。 既定では、このスコープは、Azure portal に登録されているすべてのアプリケーションに自動的に追加されます。 Microsoft Graph の他の API や、バックエンド サーバーのカスタム API には、追加のスコープが必要な場合があります。 たとえば、Microsoft Graph API では、ユーザーのメールを一覧表示するために *Mail.Read* スコープが必要です。

スコープを追加すると、追加したスコープに対して追加の同意を求めるメッセージがユーザーに表示される場合があります。

### ヘルプとサポート

サポートが必要な場合、問題をレポートする場合、またはサポート オプションについて知りたい場合は、[開発者向けのヘルプとサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-support-help-options)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-v2-nodejs-webapp-msal"} -->
## Node.js/Express Web アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-webapp-msal
- Service: identity-platform
- Article date: 2022-11-09
- Summary: このチュートリアルでは、Web アプリでのユーザーのサインインのサポートを追加します。

このチュートリアルでは、ユーザーをサインインさせ、Microsoft Graph を呼び出すためのアクセス トークンを取得する Web アプリを構築します。 作成する Web アプリでは、[Node 用の Microsoft Authentication Library (MSAL)](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) を使用します。

このチュートリアルでは、次の手順に従います。

- Azure portal でアプリケーションを登録する
- Express Web アプリ プロジェクトを作成する
- 認証ライブラリ パッケージをインストールする
- アプリの登録の詳細を追加する
- ユーザー ログインのコードを追加する
- アプリをテストする 詳細については、MSAL Node を使用してサインインし、サインアウトし、Microsoft Graph などの保護されたリソースのアクセス トークンを取得する方法を示す [サンプル コード](https://github.com/Azure-Samples/ms-identity-node) を参照してください。

### 前提条件

- [Node.js](https://nodejs.org/en/download/)
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター

### アプリケーションを登録する

まず、[Microsoft ID プラットフォームへのアプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)に関するページの手順に従って、アプリを登録します。

アプリの登録には、次の設定を使用します。

- 名前: `ExpressWebApp` (推奨)
- サポートされているアカウントの種類: **この組織のディレクトリ内のアカウントのみ**
- プラットフォームの種類:**Web**
- リダイレクト URI: `http://localhost:3000/auth/redirect`
- クライアント シークレット: `*********` (後の手順で使用するためにこの値を記録します。これは 1 回しか表示されません)

### プロジェクトを作成する

[Express アプリケーション ジェネレーター ツール](https://expressjs.com/en/starter/generator.html)を使用して、アプリケーションのスケルトンを作成します。

1. まず、[express-generator](https://www.npmjs.com/package/express-generator) パッケージをインストールします。

```console
    npm install -g express-generator
```

1. 次に、次のようにアプリケーションのスケルトンを作成します。

```console
    express --view=hbs /ExpressWebApp && cd /ExpressWebApp
    npm install
```

これで、簡単な Express Web アプリができました。 このプロジェクトのファイルとフォルダーの構造は、次のフォルダー構造のようになります。

```
ExpressWebApp/
├── bin/
|    └── wwww
├── public/
|    ├── images/
|    ├── javascript/
|    └── stylesheets/
|        └── style.css
├── routes/
|    ├── index.js
|    └── users.js
├── views/
|    ├── error.hbs
|    ├── index.hbs
|    └── layout.hbs
├── app.js
└── package.json
```

### 認証ライブラリをインストールする

ターミナルでプロジェクト ディレクトリのルートを探し、npm を使用して MSAL Node パッケージをインストールします。

```console
    npm install --save @azure/msal-node
```

### その他の依存関係をインストールする

このチュートリアルの Web アプリ サンプルでは、セッション管理用の [express-session](https://www.npmjs.com/package/express-session) パッケージ、開発中の環境パラメーターを読み取るための [dotenv](https://www.npmjs.com/package/dotenv) パッケージ、Microsoft Graph API に対してネットワーク呼び出しを行う [axios](https://www.npmjs.com/package/axios) を使用します。 npm を使用してこれらをインストールします。

```console
    npm install --save express-session dotenv axios
```

### アプリの登録の詳細を追加する

1. プロジェクト フォルダのルートに *.env.dev* ファイルを作成します。 次のコードを追加します。

```text
CLOUD_INSTANCE="Enter_the_Cloud_Instance_Id_Here" # cloud instance string should end with a trailing slash
TENANT_ID="Enter_the_Tenant_Info_Here"
CLIENT_ID="Enter_the_Application_Id_Here"
CLIENT_SECRET="Enter_the_Client_Secret_Here"

REDIRECT_URI="http://localhost:3000/auth/redirect"
POST_LOGOUT_REDIRECT_URI="http://localhost:3000"

GRAPH_API_ENDPOINT="Enter_the_Graph_Endpoint_Here" # graph api endpoint string should end with a trailing slash

EXPRESS_SESSION_SECRET="Enter_the_Express_Session_Secret_Here"
```

これらの詳細には、Azure アプリ登録ポータルから取得した値を入力します。

- `Enter_the_Cloud_Instance_Id_Here`:アプリケーションが登録されている Azure クラウド インスタンス。
    - メイン (または "グローバル") の Azure クラウドの場合、「 (末尾のスラッシュを含む)」と入力します。`https://login.microsoftonline.com/`
    - **各国**のクラウド (中国など) の場合は、「[各国のクラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud)」に適切な値が記載されています。
- `Enter_the_Tenant_Info_here`は、次のいずれかのパラメーターにする必要があります。
    - ご自分のアプリケーションで "*この組織のディレクトリ内のアカウント*" がサポートされる場合は、この値を**テナント ID** または**テナント名**に置き換えます。 たとえば、`contoso.microsoft.com` のようにします。
    - アプリケーションで "*任意の組織のディレクトリ内のアカウント*" がサポートされる場合は、この値を `organizations` に置き換えます。
    - アプリケーションで "*任意の組織のディレクトリ内のアカウントと個人用の Microsoft アカウント*" がサポートされる場合は、この値を `common` に置き換えます。
    - "*個人用の Microsoft アカウントのみ*" にサポートを制限するには、この値を `consumers` に置き換えます。
- `Enter_the_Application_Id_Here`:登録したアプリケーションの**アプリケーション (クライアント) ID**。
- `Enter_the_Client_secret`:この値を、前に作成したクライアント シークレットで置き換えます。 新しいキーを生成するには、Azure portal のアプリ登録設定で **証明書とシークレット** を使用します。

警告

ソース コードでシークレットがプレーンテキストになっていると、セキュリティ リスクが増大します。 この記事でプレーンテキストのクライアント シークレットを使用しているのは、あくまで簡潔にするためです。 機密性の高いクライアント アプリケーション、特に運用環境へのデプロイを予定しているアプリでは、クライアント シークレットではなく、[証明書の資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)を使用してください。

- `Enter_the_Graph_Endpoint_Here`: アプリが呼び出す Microsoft Graph API クラウド インスタンス。 メイン (グローバル) Microsoft Graph API サービスの場合は、「`https://graph.microsoft.com/`」 (末尾のスラッシュを含める) と入力します。
- `Enter_the_Express_Session_Secret_Here` Express セッション Cookie の署名に使用されるシークレット。 クライアント シークレットなど、この文字列を置き換える文字のランダムな文字列を選択します。

1. 次に、プロジェクトのルートに、これらのパラメーターを読み取るための *authConfig.js* という名前のファイルを作成します。 作成したら、そこに次のコードを追加します。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

require('dotenv').config({ path: '.env.dev' });

/**
 * Configuration object to be passed to MSAL instance on creation.
 * For a full list of MSAL Node configuration parameters, visit:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md
 */
const msalConfig = {
    auth: {
        clientId: process.env.CLIENT_ID, // 'Application (client) ID' of app registration in Azure portal - this value is a GUID
        authority: process.env.CLOUD_INSTANCE + process.env.TENANT_ID, // Full directory URL, in the form of https://login.microsoftonline.com/<tenant>
        clientSecret: process.env.CLIENT_SECRET // Client secret generated from the app registration in Azure portal
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                console.log(message);
            },
            piiLoggingEnabled: false,
            logLevel: 3,
        }
    }
}

const REDIRECT_URI = process.env.REDIRECT_URI;
const POST_LOGOUT_REDIRECT_URI = process.env.POST_LOGOUT_REDIRECT_URI;
const GRAPH_ME_ENDPOINT = process.env.GRAPH_API_ENDPOINT + "v1.0/me";

module.exports = {
    msalConfig,
    REDIRECT_URI,
    POST_LOGOUT_REDIRECT_URI,
    GRAPH_ME_ENDPOINT
};
```

### ユーザー サインインとトークン取得のコードを追加する

1. *auth* という名前の新しいフォルダを作成し、その下に *AuthProvider.js* という名前の新しいファイルを追加します。 これには、MSAL ノードを使用して必要な認証ロジックをカプセル化する **AuthProvider** クラスが含まれます。 そこに、次のコードを追加します。

```js
const msal = require('@azure/msal-node');
const axios = require('axios');

const { msalConfig } = require('../authConfig');

class AuthProvider {
    msalConfig;
    cryptoProvider;

    constructor(msalConfig) {
        this.msalConfig = msalConfig
        this.cryptoProvider = new msal.CryptoProvider();
    };

    login(options = {}) {
        return async (req, res, next) => {

            /**
             * MSAL Node library allows you to pass your custom state as state parameter in the Request object.
             * The state parameter can also be used to encode information of the app's state before redirect.
             * You can pass the user's state in the app, such as the page or view they were on, as input to this parameter.
             */
            const state = this.cryptoProvider.base64Encode(
                JSON.stringify({
                    successRedirect: options.successRedirect || '/',
                })
            );

            const authCodeUrlRequestParams = {
                state: state,

                /**
                 * By default, MSAL Node will add OIDC scopes to the auth code url request. For more information, visit:
                 * https://docs.microsoft.com/azure/active-directory/develop/v2-permissions-and-consent#openid-connect-scopes
                 */
                scopes: options.scopes || [],
                redirectUri: options.redirectUri,
            };

            const authCodeRequestParams = {
                state: state,

                /**
                 * By default, MSAL Node will add OIDC scopes to the auth code request. For more information, visit:
                 * https://docs.microsoft.com/azure/active-directory/develop/v2-permissions-and-consent#openid-connect-scopes
                 */
                scopes: options.scopes || [],
                redirectUri: options.redirectUri,
            };

            /**
             * If the current msal configuration does not have cloudDiscoveryMetadata or authorityMetadata, we will 
             * make a request to the relevant endpoints to retrieve the metadata. This allows MSAL to avoid making 
             * metadata discovery calls, thereby improving performance of token acquisition process. For more, see:
             * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/performance.md
             */
            if (!this.msalConfig.auth.cloudDiscoveryMetadata || !this.msalConfig.auth.authorityMetadata) {

                const [cloudDiscoveryMetadata, authorityMetadata] = await Promise.all([
                    this.getCloudDiscoveryMetadata(this.msalConfig.auth.authority),
                    this.getAuthorityMetadata(this.msalConfig.auth.authority)
                ]);

                this.msalConfig.auth.cloudDiscoveryMetadata = JSON.stringify(cloudDiscoveryMetadata);
                this.msalConfig.auth.authorityMetadata = JSON.stringify(authorityMetadata);
            }

            const msalInstance = this.getMsalInstance(this.msalConfig);

            // trigger the first leg of auth code flow
            return this.redirectToAuthCodeUrl(
                authCodeUrlRequestParams,
                authCodeRequestParams,
                msalInstance
            )(req, res, next);
        };
    }

    acquireToken(options = {}) {
        return async (req, res, next) => {
            try {
                const msalInstance = this.getMsalInstance(this.msalConfig);

                /**
                 * If a token cache exists in the session, deserialize it and set it as the 
                 * cache for the new MSAL CCA instance. For more, see: 
                 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/caching.md
                 */
                if (req.session.tokenCache) {
                    msalInstance.getTokenCache().deserialize(req.session.tokenCache);
                }

                const tokenResponse = await msalInstance.acquireTokenSilent({
                    account: req.session.account,
                    scopes: options.scopes || [],
                });

                /**
                 * On successful token acquisition, write the updated token 
                 * cache back to the session. For more, see: 
                 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/caching.md
                 */
                req.session.tokenCache = msalInstance.getTokenCache().serialize();
                req.session.accessToken = tokenResponse.accessToken;
                req.session.idToken = tokenResponse.idToken;
                req.session.account = tokenResponse.account;

                res.redirect(options.successRedirect);
            } catch (error) {
                if (error instanceof msal.InteractionRequiredAuthError) {
                    return this.login({
                        scopes: options.scopes || [],
                        redirectUri: options.redirectUri,
                        successRedirect: options.successRedirect || '/',
                    })(req, res, next);
                }

                next(error);
            }
        };
    }

    handleRedirect(options = {}) {
        return async (req, res, next) => {
            if (!req.body || !req.body.state) {
                return next(new Error('Error: response not found'));
            }

            const authCodeRequest = {
                ...req.session.authCodeRequest,
                code: req.body.code,
                codeVerifier: req.session.pkceCodes.verifier,
            };

            try {
                const msalInstance = this.getMsalInstance(this.msalConfig);

                if (req.session.tokenCache) {
                    msalInstance.getTokenCache().deserialize(req.session.tokenCache);
                }

                const tokenResponse = await msalInstance.acquireTokenByCode(authCodeRequest, req.body);

                req.session.tokenCache = msalInstance.getTokenCache().serialize();
                req.session.idToken = tokenResponse.idToken;
                req.session.account = tokenResponse.account;
                req.session.isAuthenticated = true;

                const state = JSON.parse(this.cryptoProvider.base64Decode(req.body.state));
                res.redirect(state.successRedirect);
            } catch (error) {
                next(error);
            }
        }
    }

    logout(options = {}) {
        return (req, res, next) => {

            /**
             * Construct a logout URI and redirect the user to end the
             * session with Azure AD. For more information, visit:
             * https://docs.microsoft.com/azure/active-directory/develop/v2-protocols-oidc#send-a-sign-out-request
             */
            let logoutUri = `${this.msalConfig.auth.authority}/oauth2/v2.0/`;

            if (options.postLogoutRedirectUri) {
                logoutUri += `logout?post_logout_redirect_uri=${options.postLogoutRedirectUri}`;
            }

            req.session.destroy(() => {
                res.redirect(logoutUri);
            });
        }
    }

    /**
     * Instantiates a new MSAL ConfidentialClientApplication object
     * @param msalConfig: MSAL Node Configuration object 
     * @returns 
     */
    getMsalInstance(msalConfig) {
        return new msal.ConfidentialClientApplication(msalConfig);
    }

    /**
     * Prepares the auth code request parameters and initiates the first leg of auth code flow
     * @param req: Express request object
     * @param res: Express response object
     * @param next: Express next function
     * @param authCodeUrlRequestParams: parameters for requesting an auth code url
     * @param authCodeRequestParams: parameters for requesting tokens using auth code
     */
    redirectToAuthCodeUrl(authCodeUrlRequestParams, authCodeRequestParams, msalInstance) {
        return async (req, res, next) => {
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
                responseMode: msal.ResponseMode.FORM_POST, // recommended for confidential clients
                codeChallenge: req.session.pkceCodes.challenge,
                codeChallengeMethod: req.session.pkceCodes.challengeMethod,
            };

            req.session.authCodeRequest = {
                ...authCodeRequestParams,
                code: '',
            };

            try {
                const authCodeUrlResponse = await msalInstance.getAuthCodeUrl(req.session.authCodeUrlRequest);
                res.redirect(authCodeUrlResponse);
            } catch (error) {
                next(error);
            }
        };
    }

    /**
     * Retrieves cloud discovery metadata from the /discovery/instance endpoint
     * @returns 
     */
    async getCloudDiscoveryMetadata(authority) {
        const endpoint = 'https://login.microsoftonline.com/common/discovery/instance';

        try {
            const response = await axios.get(endpoint, {
                params: {
                    'api-version': '1.1',
                    'authorization_endpoint': `${authority}/oauth2/v2.0/authorize`
                }
            });

            return await response.data;
        } catch (error) {
            throw error;
        }
    }

    /**
     * Retrieves oidc metadata from the openid endpoint
     * @returns
     */
    async getAuthorityMetadata(authority) {
        const endpoint = `${authority}/v2.0/.well-known/openid-configuration`;

        try {
            const response = await axios.get(endpoint);
            return await response.data;
        } catch (error) {
            console.log(error);
        }
    }
}

const authProvider = new AuthProvider(msalConfig);

module.exports = authProvider;
```

1. 次に、*routes* フォルダーの下に *auth.js* という名前の新しいファイルを作成し、そこに次のコードを追加します。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

var express = require('express');

const authProvider = require('../auth/AuthProvider');
const { REDIRECT_URI, POST_LOGOUT_REDIRECT_URI } = require('../authConfig');

const router = express.Router();

router.get('/signin', authProvider.login({
    scopes: [],
    redirectUri: REDIRECT_URI,
    successRedirect: '/'
}));

router.get('/acquireToken', authProvider.acquireToken({
    scopes: ['User.Read'],
    redirectUri: REDIRECT_URI,
    successRedirect: '/users/profile'
}));

router.post('/redirect', authProvider.handleRedirect());

router.get('/signout', authProvider.logout({
    postLogoutRedirectUri: POST_LOGOUT_REDIRECT_URI
}));

module.exports = router;
```

1. 既存のコードを次のコード スニペットに置き換えて、*index.js* ルートを更新します。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
    res.render('index', {
        title: 'MSAL Node & Express Web App',
        isAuthenticated: req.session.isAuthenticated,
        username: req.session.account?.username,
    });
});

module.exports = router;
```

1. 最後に、既存のコードを次のコード スニペットに置き換えて、*users.js* ルートを更新します。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

var express = require('express');
var router = express.Router();

var fetch = require('../fetch');

var { GRAPH_ME_ENDPOINT } = require('../authConfig');

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

router.get('/profile',
    isAuthenticated, // check if user is authenticated
    async function (req, res, next) {
        try {
            const graphResponse = await fetch(GRAPH_ME_ENDPOINT, req.session.accessToken);
            res.render('profile', { profile: graphResponse });
        } catch (error) {
            next(error);
        }
    }
);

module.exports = router;
```

### Microsoft Graph API を呼び出すコードを追加する

プロジェクトのルートに *fetch.js* という名前のファイルを作成し、次のコードを追加します。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

var axios = require('axios');

/**
 * Attaches a given access token to a MS Graph API call
 * @param endpoint: REST API endpoint to call
 * @param accessToken: raw access token string
 */
async function fetch(endpoint, accessToken) {
    const options = {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    };

    console.log(`request made to ${endpoint} at: ` + new Date().toString());

    try {
        const response = await axios.get(endpoint, options);
        return await response.data;
    } catch (error) {
        throw new Error(error);
    }
}

module.exports = fetch;
```

### データを表示するためのビューを追加する

1. *views* フォルダーで、既存のコードを次のように置き換えて、*index.hbs* ファイルを更新します。

```hbs
<h1>{{title}}</h1>
{{#if isAuthenticated }}
<p>Hi {{username}}!</p>
<a href="/users/id">View ID token claims</a>
<br>
<a href="/auth/acquireToken">Acquire a token to call the Microsoft Graph API</a>
<br>
<a href="/auth/signout">Sign out</a>
{{else}}
<p>Welcome to {{title}}</p>
<a href="/auth/signin">Sign in</a>
{{/if}}
```

1. 引き続き同じフォルダーに、ユーザーの ID トークンのコンテンツを表示するための *id.hbs* という名前の別のファイルを作成します。

```hbs
<h1>Azure AD</h1>
<h3>ID Token</h3>
<table>
    <tbody>
        {{#each idTokenClaims}}
        <tr>
            <td>{{@key}}</td>
            <td>{{this}}</td>
        </tr>
        {{/each}}
    </tbody>
</table>
<br>
<a href="https://aka.ms/id-tokens" target="_blank">Learn about claims in this ID token</a>
<br>
<a href="/">Go back</a>
```

1. 最後に、Microsoft Graph への呼び出しの結果を表示するための *profile.hbs* という名前の別のファイルを作成します。

```hbs
<h1>Microsoft Graph API</h1>
<h3>/me endpoint response</h3>
<table>
    <tbody>
        {{#each profile}}
        <tr>
            <td>{{@key}}</td>
            <td>{{this}}</td>
        </tr>
        {{/each}}
    </tbody>
</table>
<br>
<a href="/">Go back</a>
```

### ルーターを登録して状態管理を追加する

プロジェクト フォルダーのルートにある *app.js* ファイルで、先ほど作成したルートを登録し、**express-session** パッケージを使用して認証状態を追跡するためのセッション サポートを追加します。 既存のコードを次のコード スニペットに置き換えます。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

require('dotenv').config();

var path = require('path');
var express = require('express');
var session = require('express-session');
var createError = require('http-errors');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var authRouter = require('./routes/auth');

// initialize express
var app = express();

/**
 * Using express-session middleware for persistent user session. Be sure to
 * familiarize yourself with available options. Visit: https://www.npmjs.com/package/express-session
 */
 app.use(session({
    secret: process.env.EXPRESS_SESSION_SECRET,
    resave: false,
    saveUninitialized: false,
    cookie: {
        httpOnly: true,
        secure: false, // set this to true on production
    }
}));

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

app.use(logger('dev'));
app.use(express.json());
app.use(cookieParser());
app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/auth', authRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
    next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error');
});

module.exports = app;
```

### サインインのテストと Microsoft Graph の呼び出し

これでアプリケーションの作成が完了し、アプリの機能をテストする準備ができました。

1. プロジェクト フォルダーのルート内から次のコマンドを実行して、Node.js コンソール アプリを起動します。

```console
   npm start
```

1. ブラウザー ウィンドウを開き、`http://localhost:3000` に移動します。 ウェルカム ページが表示されるはずです。

[Image: 表示される Web アプリのウェルカム ページ]

1. **[サインイン]** リンクを選択します。 次のような Microsoft Entra サインイン画面が表示されるはずです。

[Image: Microsoft Entra サインイン画面の表示]

1. 資格情報を入力すると、アプリのアクセス許可を承認するよう求める同意画面が表示されます。

[Image: Microsoft Entra 同意画面の表示]

1. 同意すると、アプリケーションのホーム ページにリダイレクトされます。

[Image: 表示されるサインイン後の Web アプリのウェルカム ページ]

1. サインインしているユーザーの ID トークンのコンテンツを表示するには、**[View ID Token](ID トークンの表示)** リンクを選択します。

[Image: 表示される ユーザー ID トークン画面]

1. ホーム ページに戻り、**[Acquire an access token and call the Microsoft Graph API](アクセス トークンを取得して Microsoft Graph API を呼び出す)** リンクを選択します。 サインインしたユーザーの Microsoft Graph /me エンドポイントからの応答が表示されます。

[Image: 表示される Graph 呼び出し画面]

1. ホーム ページに戻り、**[サインアウト]** リンクを選択します。 次のような Microsoft Entra サインアウト画面が表示されるはずです。

[Image: Microsoft Entra サインアウト画面の表示]

### アプリケーションの動作

このチュートリアルでは、Azure portal で Microsoft Entra アプリの登録から取得したパラメーターを含む構成オブジェクト ([msalConfig](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/initialize-confidential-client-application.md)) を渡すことによって、MSAL Node *ConfidentialClientApplication* オブジェクトをインスタンス化しました。 作成した Web アプリでは、[OpenID Connect プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)を使用してユーザーをサインインさせ、[OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を使用してアクセス トークンが取得されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-v2-shared-device-mode"} -->
## チュートリアル: Android アプリケーションに Shared Device Mode のサポートを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-shared-device-mode
- Service: identity-platform
- Article date: 2024-08-19
- Summary: このチュートリアルでは、Android アプリケーションを Shared Device Mode で実行できるように設定する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Android 用 Microsoft Authentication Library (MSAL) を使用して Android アプリケーションに Shared Device Mode のサポートを追加する方法について、Android 開発者向けに説明します。

このチュートリアルでは、次の操作を行います。

- 既存の Android アプリケーション プロジェクトを作成または変更する。
- 共有デバイス モードを有効にして検出する
- アカウント モードが単一か複数かを検出する
- ユーザー スイッチを検出する
- グローバル サインインとサインアウトを有効にする

#### Android アプリケーションを作成する、または既存の Android アプリケーションを変更する。

このチュートリアルの残りの部分を完了するには、新しい Android アプリケーションを作成するか、既存の Android アプリケーションを変更する必要があります。 まだ行っていない場合は、MSAL と Android アプリを統合する方法、ユーザーをサインインさせる方法、Microsoft Graph を呼び出す方法、ユーザーをサインアウトする方法に関するガイダンスについては、 [MSAL Android チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-android) を参照してください。 学習とテストに完成したコード サンプルを使用する場合は、GitHub から [サンプル アプリケーション](https://github.com/Azure-Samples/ms-identity-android-java/) を複製します。 このサンプルには、 [単一または複数のアカウント モード](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-multi-account)で動作する機能があります。

#### ローカルの Maven リポジトリに MSAL SDK を追加する

サンプル アプリを使用していない場合は、次のように MSAL ライブラリを依存関係として自分の build.gradle ファイルに追加します:

```gradle
dependencies{
  implementation 'com.microsoft.identity.client.msal:4.9.+'
}
```

#### 単一アカウント モードのサポートを追加する

Microsoft Authentication Library (MSAL) SDK を使用して記述されたアプリケーションは、単一のアカウントまたは複数のアカウントを管理できます。 詳細については、 [単一アカウント モードまたは複数アカウント モードを](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-multi-account)参照してください。

お使いのアプリで使用できる Microsoft ID プラットフォーム機能は、アプリケーションが単一アカウント モードと複数アカウント モードのどちらで実行されているかによって異なります。

**共有デバイス モード アプリは、単一アカウント モードでのみ動作**します。

重要

複数アカウント モードのみをサポートするアプリケーションは、共有デバイス上で実行できません。 従業員が、単一アカウント モードをサポートしていないアプリを読み込んだ場合、そのアプリは共有デバイス上で実行されません。

MSAL SDK がリリースされる前に記述されたアプリは複数アカウント モードで実行されるため、共有モード デバイス上で実行するには、単一アカウント モードをサポートするように更新しておく必要があります。 **単一アカウントと複数アカウントの両方のサポート**

アプリは、個人デバイスと共有デバイスの両方での実行をサポートするように構築できます。 現在、アプリが複数のアカウントをサポートしており、共有デバイス モードをサポートするようにしたい場合は、単一アカウント モードのサポートを追加してください。

アプリで実行されているデバイスの種類に応じて、アプリが動作を変更するようにもできます。 いつ単一アカウント モードで実行するかを決定するには、`ISingleAccountPublicClientApplication.isSharedDevice()` を使用します。

アプリケーションが実行しているデバイスの種類を表す 2 つの異なるインターフェイスが存在します。 MSAL のアプリケーション ファクトリにアプリケーション インスタンスを要求すると、適切なアプリケーション オブジェクトが自動的に提供されます。

次のオブジェクト モデルは、受信する可能性のあるオブジェクトの型と、それが共有デバイスのコンテキストで何を示すかを示しています。

[Image: パブリック クライアント アプリケーションの継承モデルの図。]

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

| - | 共有モード デバイス | 個人デバイス |
| --- | --- | --- |
| **アカウント** | 単一のアカウント | 複数のアカウント |
| **サインイン** | グローバル | グローバル |
| **サインアウト** | グローバル | 各アプリケーションは、サインアウトがアプリに対してローカルかどうかを制御できます。 |
| **サポートされているアカウントの種類** | 職場アカウントのみ | サポートされている個人アカウントと業務用アカウント |

#### 共有デバイス モードを使用するようアプリを構成する

構成ファイルの設定の詳細については、 [構成ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-configuration) を参照してください。

ご自分の MSAL 構成ファイルで `"shared_device_mode_supported"` を `true` に設定します。

複数アカウント モードのサポートを計画していない場合もあります。 共有デバイスを使用しておらず、ユーザーが同時に複数のアカウントを使用してアプリにサインインできる場合がこれに該当します。 その場合は、`"account_mode"` を `"SINGLE"` に設定します。 これにより、お客様のアプリで常に `ISingleAccountPublicClientApplication` が取得されるようになり、MSAL の統合が大幅に簡素化されます。 `"account_mode"` の既定値は `"MULTIPLE"` であるため、`"single account"` モードを使用する場合は、構成ファイルでこの値を変更することが重要です。

サンプル アプリの &gt;&gt;&gt; ディレクトリに含まれる auth\_config.json ファイルの例を次に示します。

```json
{
  "client_id": "Client ID after app registration at https://aka.ms/MobileAppReg",
  "authorization_user_agent": "DEFAULT",
  "redirect_uri": "Redirect URI after app registration at https://aka.ms/MobileAppReg",
  "account_mode": "SINGLE",
  "broker_redirect_uri_registered": true,
  "shared_device_mode_supported": true,
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

#### 共有デバイス モードを検出する

共有デバイス モードを使用すると、複数の従業員で共有できるように Android デバイスを構成しながら、Microsoft ID に基づくデバイス管理を実現できます。 従業員は自分のデバイスにサインインし、顧客情報にすばやくアクセスできます。 シフトやタスクが終了した従業員は共有デバイスのすべてのアプリからワンクリックでサインアウトでき、そのデバイスは次の従業員がすぐに使用できるようになります。

`isSharedDevice()` を使用すると、アプリが共有デバイス モードのデバイスで実行されているかどうかを特定できます。 お客様のアプリでは、このフラグを使用して、適宜 UX を変更する必要があるかどうかを特定できます。

次のコード スニペットは `isSharedDevice()` の使用方法を示しています。 これは、サンプル アプリの `SingleAccountModeFragment` クラスに含まれています。

```Java
deviceModeTextView.setText(mSingleAccountApp.isSharedDevice() ? "Shared" : "Non-Shared");
```

#### PublicClientApplication オブジェクトを初期化する

MSAL 構成ファイルで `"account_mode":"SINGLE"` を設定した場合、返されるアプリケーション オブジェクトを `ISingleAccountPublicCLientApplication` として安全にキャストできます。

```java
private ISingleAccountPublicClientApplication mSingleAccountApp;

/*Configure your sample app and save state for this activity*/
PublicClientApplication.create(this.getApplicationCOntext(),
  R.raw.auth_config,
  new PublicClientApplication.ApplicationCreatedListener(){
  @Override
  public void onCreated(IPublicClientApplication application){
  mSingleAccountApp = (ISingleAccountPublicClientApplication)application;
  loadAccount();
  }
  @Override
  public void onError(MsalException exception){
  /*Fail to initialize PublicClientApplication */
  }
});
```

#### アカウント モードが単一か複数かを検出する

共有デバイス上でフロントライン ワーカーのみが使用するアプリを作成している場合は、単一アカウント モードのみをサポートするようにアプリを作成することをお勧めします。 これには、診療記録アプリ、請求書アプリ、大部分の基幹業務アプリなどの、タスクに重点を置いたほとんどのアプリケーションが含まれます。 これにより、SDK の多くの機能に対応する必要がなくなるため、開発が簡単になります。

アプリで複数アカウントと共有デバイス モードがサポートされている場合は、次に示すように、種類のチェックを実行して適切なインターフェイスにキャストする必要があります。

```java
private IPublicClientApplication mApplication;

        if (mApplication instanceOf IMultipleAccountPublicClientApplication) {
          IMultipleAccountPublicClientApplication multipleAccountApplication = (IMultipleAccountPublicClientApplication) mApplication;
          ...
        } else if (mApplication instanceOf    ISingleAccountPublicClientApplication) {
           ISingleAccountPublicClientApplication singleAccountApplication = (ISingleAccountPublicClientApplication) mApplication;
            ...
        }
```

#### サインインしているユーザーを取得し、デバイスでユーザーが変更されたかどうかを特定する

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
        mSingleAccountApp.acquireTokenSilentAsync(SCOPES,"http://login.microsoftonline.com/common",getAuthSilentCallback());
      }
    }
    @Override
    public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable Iaccount currentAccount)
    {
      if (currentAccount == null)
      {
        //Perform a cleanup task as the signed-in account changed.
        updateSingedOutUI();
      }
    }
    @Override
    public void onError(@NonNull Exception exception)
    {
    }
  }
}
```

#### ユーザーをグローバルにサインインさせる

Authenticator アプリを使用し、デバイス全体でユーザーを MSAL が使用される別のアプリにサインインさせるには、次のようにします。

```java
private void onSignInClicked()
{
  mSingleAccountApp.signIn(getActivity(), SCOPES, null, getAuthInteractiveCallback());
}
```

#### ユーザーをグローバルにサインアウトさせる

サインインしているアカウントを削除し、キャッシュされているトークンをアプリだけでなく共有デバイス モードのデバイスからもクリアするには、次のようにします。

```java
private void onSignOutClicked()
{
  mSingleAccountApp.signOut(new ISingleAccountPublicClientApplication.SignOutCallback()
  {
    @Override
    public void onSignOut()
    {
      updateSignedOutUI();
    }
    @Override
    public void onError(@NonNull MsalException exception)
    {
      /*failed to remove account with an exception*/
    }
  });
}
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

#### アプリケーションを登録してテスト用にテナントを設定する

アプリケーションを設定してデバイスを共有デバイス モードにする前に、組織のテナント内にアプリケーションを登録する必要があります。 次に、アプリケーションが正しく実行されるように * 、auth\_config.json* でこれらの値を指定します。

これを行う方法については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-android)」を参照してください。

注意

アプリを登録するときは、左側のクイック スタート ガイドを使用し、[ **Android**] を選択してください。 これにより、アプリの **パッケージ名** と **署名ハッシュ** を指定するように求められるページが表示されます。 アプリ構成を確実に機能させるためには、これらが非常に重要です。 次に、自分のアプリに使用できる構成オブジェクトを受け取ります。これは切り取って、自分の auth\_config.json ファイルに貼り付けます。

[Image: Android アプリ ページを構成する]

[ **この変更を行う** ] を選択し、クイック スタートで求める値を指定する必要があります。 完了すると、Microsoft Entra ID によって必要なすべての構成ファイルが生成されます。

テストを目的として、自分のテナントで次のロール、つまり、少なくとも 2 人の従業員と 1 人のクラウド デバイス管理者を設定します。 クラウド デバイス管理者を設定するには、組織のロールを変更する必要があります。 Microsoft Entra 管理センターで、**Entra ID**、&gt;、**すべてのロール**の順に選択して組織の役割に移動し、&gt;を選択します。 デバイスを共有モードにすることができるユーザーを追加します。

### サンプル アプリの実行

このサンプル アプリケーションは、お客様の組織の Graph API を呼び出す単純なアプリです。 最初の実行では、お客様の従業員アカウントでこのアプリケーションを使用するのが初めてであるため、同意を求めるメッセージが表示されます。

[Image: アプリケーション構成情報画面]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-v2-windows-desktop"} -->
## チュートリアル: 認証に Microsoft ID プラットフォームを使用する Windows Presentation Foundation (WPF) アプリを作成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-windows-desktop
- Service: identity-platform
- Article date: 2024-06-27
- Summary: このチュートリアルでは、ユーザーのサインインに Microsoft ID プラットフォームを使用し、そのユーザーに代わって Microsoft Graph API を呼び出すためのアクセス トークンを取得する WPF アプリケーションをビルドします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ユーザーのサインインを処理して Microsoft Graph API を呼び出すためのアクセス トークンを取得するネイティブ Windows デスクトップ .NET (XAML) アプリを作成します。

このガイドを完了すると、アプリケーションで個人アカウント (outlook.com、live.com など) を使用する保護された API を呼び出すことができるようになります。 このアプリケーションでは、Microsoft Entra ID を使用する会社または組織の職場および学校アカウントも使用します。

このチュートリアルでは、次の操作を行います。

- Visual Studio で *Windows Presentation Foundation (WPF)* プロジェクトを作成する
- .NET 用 Microsoft 認証ライブラリ (MSAL) をインストールする
- アプリケーションを登録する
- ユーザーのサインインとサインアウトをサポートするコードを追加する
- Microsoft Graph API を呼び出すコードを追加する
- アプリケーションをテストする

### 前提条件

- 従業員テナント。 [既定のディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)を使用するか、新しいテナントを設定できます。
- [組織のディレクトリと個人用](https://entra.microsoft.com)の Microsoft アカウントのアカウント用に構成された Microsoft *Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `https://login.microsoftonline.com/common/oauth2/nativeclient`
- [.NET Framework 4.8](https://dotnet.microsoft.com/download/dotnet-framework/net48)
- [Visual Studio 2019 の](https://visualstudio.microsoft.com/vs/)

### このガイドで生成されたサンプル アプリの動作

[Image: このチュートリアルで生成されたサンプル アプリの動作のスクリーンショット。]

このガイドで作成するサンプル アプリケーションにより、Windows デスクトップ アプリケーションにおいて、Microsoft ID プラットフォーム エンドポイントからのトークンを受け付ける Microsoft Graph API または Web API に対してクエリを実行できるようになります。 このシナリオでは、Authorization ヘッダーを使用して HTTP 要求にトークンを追加します。 トークンの取得と更新は、Microsoft Authentication Library (MSAL) で処理されます。

### 保護された Web API にアクセスするためのトークン取得処理

ユーザーが認証されると、サンプル アプリケーションは、Microsoft Graph API または Microsoft ID プラットフォームによって保護されている Web API でのクエリに使用できるトークンを受け取ります。

Microsoft Graph などの API では、特定のリソースへのアクセスを許可するためにトークンが必要になります。 たとえば、トークンは、ユーザーのプロファイルの読み取り、ユーザーの予定表へのアクセス、メールの送信などに必要です。 アプリケーションでは、MSAL を使用してアクセス トークンを要求し、API スコープを指定することによってこれらのリソースにアクセスできます。 このアクセス トークンは、保護されたリソースに対するすべての呼び出しで HTTP Authorization ヘッダーに追加されます。

アクセス トークンのキャッシュと更新は MSAL が管理するため、アプリケーションが管理する必要はありません。

### NuGet パッケージ

このガイドでは、次の NuGet パッケージを使用します。

| ライブラリ | 説明 |
| --- | --- |
| [Microsoft.Identity.クライアント](https://www.nuget.org/packages/Microsoft.Identity.Client) | Microsoft 認証ライブラリ (MSAL.NET) |

### プロジェクトの設定

このセクションでは、Windows デスクトップ .NET アプリケーション (XAML) を "Microsoft でサインイン" と統合して、アプリケーションがトークンを必要とする Web API のクエリを実行できるようにするための方法をデモする新しいプロジェクトを作成します。

作成するアプリケーションには、Microsoft Graph API を呼び出すボタン、結果を表示するための領域、サインアウト ボタンが表示されます。

注記

代わりにこのサンプルの Visual Studio プロジェクトをダウンロードすることもできます。 [プロジェクトをダウンロード](https://github.com/Azure-Samples/active-directory-dotnet-desktop-msgraph-v2/archive/msal3x.zip)し、実行する前にアプリケーションを登録してコード サンプルを構成します。

次の手順に従ってアプリケーションを作成します。

1. Visual Studio を開く
2. [スタート ウィンドウ] で、 **[新しいプロジェクトの作成]** を選択します。
3. **[すべての言語]** ドロップダウンで、**[C#]** を選択します。
4. **WPF アプリ (.NET Framework)** テンプレートを検索して選択し、[次へ] を選択します。
5. **[プロジェクト名]** ボックスに、*Win-App-calling-MsGraph* などの名前を入力します。
6. プロジェクトの **[場所]** を選択するか、既定のオプションをそのまま使用します。
7. **[フレームワーク]** で、**[.NET Framework 4.8]** を選択します。
8. **［作成］** を選択します

### プロジェクトに MSAL を追加する

1. Visual Studio で、 **[ツール]**&gt;**[NuGet パッケージ マネージャー]**&gt;**[パッケージ マネージャー コンソール]** の順に選択します。
2. [パッケージ マネージャー コンソール] ウィンドウで、次の Azure PowerShell コマンドを貼り付けます。

    ```powershell
    Install-Package Microsoft.Identity.Client -Pre
    ```

### MSAL を初期化するコードの追加

この手順では、トークンの処理など MSAL ライブラリとのやり取りを処理するクラスを作成します。

1. *App.xaml.cs* ファイルを開き、クラスに MSAL の参照を追加します。

    ```csharp
    using Microsoft.Identity.Client;
    ```
2. App クラスを以下のように更新します。

    ```csharp
    public partial class App : Application
    {
        static App()
        {
            _clientApp = PublicClientApplicationBuilder.Create(ClientId)
                .WithAuthority(AzureCloudInstance.AzurePublic, Tenant)
                .WithDefaultRedirectUri()
                .Build();
        }
    
        // Below are the clientId (Application Id) of your app registration and the tenant information.
        // You have to replace:
        // - the content of ClientID with the Application Id for your app registration
        // - the content of Tenant by the information about the accounts allowed to sign-in in your application:
        //   - For Work or School account in your org, use your tenant ID, or domain
        //   - for any Work or School accounts, use `organizations`
        //   - for any Work or School accounts, or Microsoft personal account, use `common`
        //   - for Microsoft Personal account, use consumers
        private static string ClientId = "Enter_the_Application_Id_here";
    
        private static string Tenant = "common";
    
        private static IPublicClientApplication _clientApp ;
    
        public static IPublicClientApplication PublicClientApp { get { return _clientApp; } }
    }
    ```

### アプリケーション UI を作成する

このセクションでは、アプリケーションで、Microsoft Graph のような保護されたバックエンド サーバーに対してクエリを実行する方法を示します。

*MainWindow.xaml* ファイルは、プロジェクト テンプレートの一部として自動的に作成されます。 このファイルを開き、アプリケーションの *&lt;Grid&gt;* ノードを次のコードに置き換えます。

```xml
<Grid>
    <StackPanel Background="Azure">
        <StackPanel Orientation="Horizontal" HorizontalAlignment="Right">
            <Button x:Name="CallGraphButton" Content="Call Microsoft Graph API" HorizontalAlignment="Right" Padding="5" Click="CallGraphButton_Click" Margin="5" FontFamily="Segoe Ui"/>
            <Button x:Name="SignOutButton" Content="Sign-Out" HorizontalAlignment="Right" Padding="5" Click="SignOutButton_Click" Margin="5" Visibility="Collapsed" FontFamily="Segoe Ui"/>
        </StackPanel>
        <Label Content="API Call Results" Margin="0,0,0,-5" FontFamily="Segoe Ui" />
        <TextBox x:Name="ResultText" TextWrapping="Wrap" MinHeight="120" Margin="5" FontFamily="Segoe Ui"/>
        <Label Content="Token Info" Margin="0,0,0,-5" FontFamily="Segoe Ui" />
        <TextBox x:Name="TokenInfoText" TextWrapping="Wrap" MinHeight="70" Margin="5" FontFamily="Segoe Ui"/>
    </StackPanel>
</Grid>
```

### MSAL を使用して Microsoft Graph API のトークンを取得する

このセクションでは、MSAL を使用して Microsoft Graph API のトークンを取得します。

1. *MainWindow.xaml.cs* ファイルで、クラスに MSAL の参照を追加します。

    ```csharp
    using Microsoft.Identity.Client;
    ```
2. `MainWindow` クラス コードを次のコードに置き換えます。

    ```csharp
    public partial class MainWindow : Window
    {
        //Set the API Endpoint to Graph 'me' endpoint
        string graphAPIEndpoint = "https://graph.microsoft.com/v1.0/me";
    
        //Set the scope for API call to user.read
        string[] scopes = new string[] { "user.read" };

        public MainWindow()
        {
            InitializeComponent();
        }
    
      /// <summary>
        /// Call AcquireToken - to acquire a token requiring user to sign-in
        /// </summary>
        private async void CallGraphButton_Click(object sender, RoutedEventArgs e)
        {
            AuthenticationResult authResult = null;
            var app = App.PublicClientApp;
            ResultText.Text = string.Empty;
            TokenInfoText.Text = string.Empty;
    
            var accounts = await app.GetAccountsAsync();
            var firstAccount = accounts.FirstOrDefault();
    
            try
            {
                authResult = await app.AcquireTokenSilent(scopes, firstAccount)
                    .ExecuteAsync();
            }
            catch (MsalUiRequiredException ex)
            {
                // A MsalUiRequiredException happened on AcquireTokenSilent.
                // This indicates you need to call AcquireTokenInteractive to acquire a token
                System.Diagnostics.Debug.WriteLine($"MsalUiRequiredException: {ex.Message}");
    
                try
                {
                    authResult = await app.AcquireTokenInteractive(scopes)
                        .WithAccount(accounts.FirstOrDefault())
                        .WithPrompt(Prompt.SelectAccount)
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
    
            if (authResult != null)
            {
                ResultText.Text = await GetHttpContentWithToken(graphAPIEndpoint, authResult.AccessToken);
                DisplayBasicTokenInfo(authResult);
                this.SignOutButton.Visibility = Visibility.Visible;
            }
        }
        }
    ```

#### 詳細情報

##### ユーザー トークンを対話形式で取得する

`AcquireTokenInteractive` メソッドを呼び出すと、ユーザーにサインインを求めるウィンドウが表示されます。 通常、アプリケーションは、ユーザーが保護されたリソースに初めてアクセスするときに、対話形式でユーザーにサインインを求めます。 また、自動でのトークンの取得に失敗した場合 (ユーザーのパスワードが期限切れになっている場合など) にも、ユーザーはサインインする必要があります。

##### ユーザー トークンをバックグラウンドで取得する

`AcquireTokenSilent` メソッドは、ユーザーの操作なしでトークンの取得と更新を処理します。 `AcquireTokenInteractive` が初めて実行された後、以降の呼び出しでは、保護されたリソースへのアクセスに使用するトークンを取得する際に、通常は `AcquireTokenSilent` メソッドを使用します。トークンを要求または更新する呼び出しが自動で行われるからです。

最終的に、`AcquireTokenSilent` メソッドは失敗します。 この失敗は、ユーザーがサインアウトしたか、別のデバイスでパスワードを変更したことが原因と考えられます。 ユーザーの操作によって解決できる問題が MSAL によって検出された場合、MSAL は `MsalUiRequiredException` 例外を発行します。 アプリケーションでは、この例外を 2 つの方法で処理できます。

- `AcquireTokenInteractive` にすぐに呼び出しを行うことができます。 この呼び出しにより、ユーザーにサインインを求めます。 ユーザーに対して使用できるオフライン コンテンツがないオンライン アプリケーションでは、このパターンを使用します。 このセットアップで生成されるサンプルでは、このパターンに従います。これは、サンプルの初回実行時に実際の動作を確認できます。
- アプリケーションはユーザーによって使用されたことがないため、`PublicClientApp.Users.FirstOrDefault()` には null 値が含まれ、`MsalUiRequiredException` 例外がスローされます。
- サンプルのコードでは、`AcquireTokenInteractive` を呼び出してユーザーにサインインを求めることにより、この例外を処理します。
- 対話形式でのサインインが必要であることをユーザーに視覚的に示すことで、ユーザーが適切なタイミングでサインインできるようにします。 または、アプリケーションが後で `AcquireTokenSilent` を再試行します。 多くの場合、このパターンは、ユーザーが中断することなく他のアプリケーション機能を使用できる場合に使用されます。 たとえば、アプリケーションでオフラインのコンテンツを使用可能な場合です。 この場合、保護されたリソースにアクセスしたり、古くなった情報を更新したりするために、サインインするタイミングをユーザーが決定できます。 また、一時的に使用できなくなっていたネットワークが回復したときに、アプリケーションが `AcquireTokenSilent` の再試行を決定することもできます。

### 取得したトークンを使用して Microsoft Graph API を呼び出す

次の新しいメソッドを `MainWindow.xaml.cs` に追加します。 このメソッドは、Authorize ヘッダーを使用した Graph API に対する `GET` 要求の実行に使用されます。

```csharp
/// <summary>
/// Perform an HTTP GET request to a URL using an HTTP Authorization header
/// </summary>
/// <param name="url">The URL</param>
/// <param name="token">The token</param>
/// <returns>String containing the results of the GET operation</returns>
public async Task<string> GetHttpContentWithToken(string url, string token)
{
    var httpClient = new System.Net.Http.HttpClient();
    System.Net.Http.HttpResponseMessage response;
    try
    {
        var request = new System.Net.Http.HttpRequestMessage(System.Net.Http.HttpMethod.Get, url);
        //Add the token in Authorization header
        request.Headers.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("Bearer", token);
        response = await httpClient.SendAsync(request);
        var content = await response.Content.ReadAsStringAsync();
        return content;
    }
    catch (Exception ex)
    {
        return ex.ToString();
    }
}
```

#### 保護された API に対する REST 呼び出しの実行についての詳細

このサンプル アプリケーションでは、`GetHttpContentWithToken` メソッドを使用して、トークンが必要な保護されたリソースに対して HTTP `GET` 要求を実行し、呼び出し元にその内容を返します。 このメソッドは、取得したトークンを HTTP Authorization ヘッダーに追加します。 このサンプルで使用するリソースは、ユーザーのプロファイル情報を表示する Microsoft Graph API *me* エンドポイントです。

### ユーザーをサインアウトさせるメソッドを追加する

ユーザーをサインアウトさせるには、次のメソッドを `MainWindow.xaml.cs` ファイルに追加します。

```csharp
/// <summary>
/// Sign out the current user
/// </summary>
private async void SignOutButton_Click(object sender, RoutedEventArgs e)
{
    var accounts = await App.PublicClientApp.GetAccountsAsync();

    if (accounts.Any())
    {
        try
        {
            await App.PublicClientApp.RemoveAsync(accounts.FirstOrDefault());
            this.ResultText.Text = "User has signed-out";
            this.CallGraphButton.Visibility = Visibility.Visible;
            this.SignOutButton.Visibility = Visibility.Collapsed;
        }
        catch (MsalException ex)
        {
            ResultText.Text = $"Error signing-out user: {ex.Message}";
        }
    }
}
```

#### ユーザーのサインアウトに関する詳細情報

`SignOutButton_Click` メソッドは、MSAL ユーザー キャッシュからユーザーを削除します。これは実質的に MSAL に現在のユーザーを忘れさせることになり、以降の要求が対話形式で行われる場合のみトークンの取得が成功します。

このサンプルのアプリケーションは単一ユーザーに対応していますが、MSAL は複数のアカウントで同時にサインインするシナリオをサポートしています。 例として、電子メール アプリケーションで 1 人のユーザーが複数のアカウントを持っている場合が挙げられます。

### 基本的なトークン情報を表示する

トークンについての基本的な情報を表示するには、次のメソッドを *MainWindow.xaml.cs* ファイルに追加します。

```csharp
/// <summary>
/// Display basic information contained in the token
/// </summary>
private void DisplayBasicTokenInfo(AuthenticationResult authResult)
{
    TokenInfoText.Text = "";
    if (authResult != null)
    {
        TokenInfoText.Text += $"Username: {authResult.Account.Username}" + Environment.NewLine;
        TokenInfoText.Text += $"Token Expires: {authResult.ExpiresOn.ToLocalTime()}" + Environment.NewLine;
    }
}
```

#### 詳細情報

Microsoft Graph API の呼び出しに使用するアクセス トークンに加えて、MSAL はユーザーのサインイン後に ID トークンも取得します。 このトークンには、ユーザー関連情報の少量のサブセットが含まれています。 `DisplayBasicTokenInfo` メソッドは、このトークンに含まれている基本的な情報を表示します。 たとえば、ユーザーの表示名や ID に加えて、トークンの有効期限やアクセス トークンを表す文字列などを表示します。 *[Call Microsoft Graph API](Microsoft Graph API の呼び出し)* ボタンを複数回押すと、後の要求で同じトークンが再利用されてことが確認できます。 また、MSAL がトークンの更新時期だと判断したときに、有効期限が延長されることも確認できます。

### コードのテスト

Visual Studio で、お使いのプロジェクトを実行するには、**F5** キーを押します。 アプリケーション **MainWindow** が表示されます。

初めてアプリケーションを実行して **[Call Microsoft Graph API](Microsoft Graph API の呼び出し)** ボタンを選択すると、サインインを求められます。 テストを行うには、Microsoft Entra アカウント (職場または学校アカウント) または Microsoft アカウント (live.com、outlook.com) を使用します。

[Image: アプリケーションにサインインする。]

#### アプリケーションによるアクセスに同意する

アプリケーションに初めてサインインするときに、次に示すように、アプリケーションがプロファイルにアクセスし、サインインすることを許可することへの同意を求められます。

[Image: アプリケーションによるアクセスに同意する。]

#### アプリケーションの結果を表示する

サインインしたら、Microsoft Graph API の呼び出しによって返されたユーザー プロファイル情報が表示されます。 結果は、 **[API Call Results](API コールの結果)** ボックスに表示されます。 `AcquireTokenInteractive` または `AcquireTokenSilent` の呼び出しを介して取得されたトークンに関する基本情報は、 **[Token Info](https://learn.microsoft.com/ja-jp/entra/identity-platform/トークン情報)** ボックスに表示されます。 結果には、以下のプロパティが含まれます。

| プロパティ | フォーマット | 説明 |
| --- | --- | --- |
| **ユーザー名** | user@domain.com | ユーザーの識別に使用されているユーザー名。 |
| **トークンの有効期限** | 日付と時刻 | トークンの有効期限が切れる時刻。 MSAL は、必要に応じてトークンを更新することで、有効期限日を延長します。 |

#### スコープと委任されたアクセス許可の詳細

Microsoft Graph API には、ユーザーのプロファイルを読み取るための *user.read* スコープが必要です。 このスコープは、アプリケーション登録ポータルで登録されたすべてのアプリケーションで、既定で自動的に追加されます。 Microsoft Graph の他の API や、バックエンド サーバーのカスタム API には、追加のスコープが必要な場合があります。 Microsoft Graph API には、ユーザーの予定表を表示するための *Calendars.Read* スコープが必要です。

アプリケーションのコンテキストでユーザーの予定表にアクセスするには、*Calendars.Read* の委任されたアクセス許可をアプリケーション登録情報に追加します。 次に、*Calendars.Read* スコープを `acquireTokenSilent` 呼び出しに追加します。

注記

スコープの数を増やすと、ユーザーは追加の同意を求められることがあります。

### ヘルプとサポート

サポートが必要な場合、問題をレポートする場合、またはサポート オプションについて知りたい場合は、[開発者向けのヘルプとサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-support-help-options)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-api-dotnet-core-build-app"} -->
## チュートリアル: Microsoft ID プラットフォームを使用して ASP.NET Core Web API を構築してセキュリティで保護する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-build-app
- Service: identity-platform
- Article date: 2025-03-18
- Summary: API のエンドポイントを保護し、それを実行して、HTTP 要求をリッスンしていることを確認します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアル シリーズでは、Microsoft ID プラットフォームを使用して ASP.NET Core Web API を保護し、承認されたユーザーとクライアント アプリにのみアクセスできるようにする方法について説明します。 作成する Web API では、委任されたアクセス許可 (スコープ) とアプリケーションのアクセス許可 (アプリ ロール) の両方が使用されます。

このチュートリアルでは、次の操作を行います。

- ASP.NET Core Web API を構築する
- Microsoft Entra アプリの登録の詳細を使用するように Web API を構成する
- Web API エンドポイントを保護する
- Web API を実行して HTTP 要求を受け付けていることを確認する

### 前提条件

- まだ行っていない場合は、[クイックスタート: Microsoft Identity プラットフォームによって保護されている Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-dotnet-protect-app?tabs=aspnet-core)の手順を完了してください。 コード サンプルを複製して実行する必要はありませんが、次のことを確認してください。
    - クライアント ID とテナント ID など、Microsoft Entra 管理センターからの Web API のアプリ登録の詳細。
    - *Web API によって公開される委任されたアクセス許可 (スコープ)* としての *ToDoList.Read* および [ToDoList.ReadWrite](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-dotnet-protect-app?tabs=aspnet-core#add-delegated-permissions-scopes)
    - *Web API によって公開されるアプリケーションのアクセス許可 (アプリ ロール)* としての *ToDoList.Read.All* と [ToDoList.ReadWrite.All](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-dotnet-protect-app?tabs=aspnet-core#add-application-permissions-app-roles)
- [.NET 8.0 SDK](https://dotnet.microsoft.com/download/dotnet) 以降。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。

### 新しい ASP.NET Core Web API プロジェクトを作成する

最小限の ASP.NET Core Web API プロジェクトを作成するには、次の手順に従います。

1. Visual Studio Code またはその他のコード エディターでターミナルを開き、プロジェクトを作成するディレクトリに移動します。
2. .NET CLI またはその他のコマンド ライン ツールで次のコマンドを実行します。

    ```dotnetcli
    dotnet new web -o TodoListApi
    cd TodoListApi
    ```
3. ダイアログ ボックスで作成者を信頼するかどうかを確認するメッセージが表示されたら、[ **はい** ] を選択します。
4. 必要なアセットをプロジェクトに追加するかどうかを確認するダイアログ ボックスが表示されたら、[ **はい** ] を選択します。

### 必要なパッケージをインストールする

ASP.NET Core Web API をビルド、保護、テストするには、次のパッケージをインストールする必要があります。

- `Microsoft.EntityFrameworkCore.InMemory`- Entity Framework Core とメモリ内データベースを使用できるパッケージ。 これはテスト目的で役立ちますが、運用環境用には設計されていません。
- `Microsoft.Identity.Web` - Microsoft ID プラットフォームと統合する Web アプリと Web API への認証と承認のサポートの追加を簡略化する一連の ASP.NET Core ライブラリ。

パッケージをインストールするには、次のコマンドを使用します。

```dotnetcli
dotnet add package Microsoft.EntityFrameworkCore.InMemory
dotnet add package Microsoft.Identity.Web
```

### アプリ登録の詳細を構成する

アプリ フォルダーで *appsettings.json* ファイルを開き、Web API の登録後に記録したアプリ登録の詳細を追加します。

```json
{
    "AzureAd": {
        "Instance": "Enter_the_Authority_URL_Here",
        "TenantId": "Enter_the_Tenant_Id_Here",
        "ClientId": "Enter_the_Application_Id_Here"
    },
    "Logging": {...},
  "AllowedHosts": "*"
}
```

次のプレースホルダーを以下のように置き換えてください。

- `Enter_the_Application_Id_Here` をアプリケーション (クライアント) ID に置き換えます。
- `Enter_the_Tenant_Id_Here` をディレクトリ (テナント) ID に置き換えます。
- 次のセクションで説明するように、 `Enter_the_Authority_URL_Here` を機関の URL に置き換えます。

#### アプリの権限 URL

機関 URL は、Microsoft Authentication Library (MSAL) がトークンを要求できるディレクトリを指定します。 次に示すように、従業員と外部テナントの両方で異なる方法で構築します。

## [従業員テナント](#tab/workforce-tenant)
```json
//Instance for workforce tenant
Instance: "https://login.microsoftonline.com/"
```

## [外部テナント](#tab/external-tenant)
```json
//Authority URL for external tenant
Instance: "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/"
```

---

#### カスタム URL ドメインを使用する (省略可能)

## [従業員テナント](#tab/workforce-tenant)
カスタム URL ドメインは、従業員テナントではサポートされていません。

## [外部テナント](#tab/external-tenant)
#### カスタム URL ドメインを使用する (省略可能)

カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム ドメインを使用するには、次の手順に従います。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *appsettings.json* ファイルを開きます。

    1. `Instance` プロパティの値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here* に更新します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
    2. `knownAuthorities` という値を持つ  プロパティを追加します。

カスタム URL ドメインが *login.contoso.com*、テナント ID が *aaaabbbb-0000-cccc-1111-dddd2222eeee* の場合、*appsettings.json* ファイルに変更を加えた後には、ファイルは次のスニペットのようになるはずです。

```json
{
    "AzureAd": {
        "Instance": "https://login.contoso.com/aaaabbbb-0000-cccc-1111-dddd2222eeee",
        "TenantId": "Enter_the_Tenant_Id_Here",
        "ClientId": "Enter_the_Application_Id_Here",
        "KnownAuthorities": ["login.contoso.com"]
    },
    "Logging": {...},
  "AllowedHosts": "*"
}
```

---

### アクセス許可を追加する

クライアント アプリがユーザーのアクセス トークンを正常に取得するために、すべての API は少なくとも 1 つのスコープ (委任されたアクセス許可とも呼ばれます) を発行する必要があります。 また、API は、クライアント アプリがアクセス トークンを自分で取得するために、つまりユーザーがサインインしていない場合に、少なくとも 1 つのアプリ ロール (アプリケーションのアクセス許可とも呼ばれます) を発行する必要があります。

これらのアクセス許可は、*appsettings.json* ファイルで指定します。 このチュートリアルでは、次の委任されたアクセス許可とアプリケーションのアクセス許可を登録しました。

- **委任されたアクセス許可:***ToDoList.Read* と *ToDoList.ReadWrite*。
- **アプリケーションのアクセス許可:***ToDoList.Read.All* と *ToDoList.ReadWrite.All*。

ユーザーまたはクライアント アプリケーションが Web API を呼び出すと、これらのスコープまたはアクセス許可を持つクライアントのみが、保護されたエンドポイントへのアクセスを承認されます。

```json
{
  "AzureAd": {
    "Instance": "Enter_the_Authority_URL_Here",
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

### API に認証と承認を実装する

認証と承認を構成するには、 `program.cs` ファイルを開き、その内容を次のコード スニペットに置き換えます。

#### 認証スキームを追加する

この API では、既定の認証メカニズムとして JSON Web トークン (JWT) ベアラー スキームを使用します。 JWT ベアラー スキームを登録するには、 `AddAuthentication` メソッドを使用します。

```cs
// Add required packages to your imports
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Identity.Web;

var builder = WebApplication.CreateBuilder(args);

// Add an authentication scheme
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration);

```

#### アプリのモデルを作成する

プロジェクトのルート フォルダーに、 *Models* という名前のフォルダーを作成します。 *Models* フォルダーに移動し、`ToDo.cs`という名前のファイルを作成し、次のコードを追加します。

```cs
using System;

namespace ToDoListAPI.Models;

public class ToDo
{
    public int Id { get; set; }
    public Guid Owner { get; set; }
    public string Description { get; set; } = string.Empty;
}
```

上記のコードでは、 *ToDo* という名前のモデルが作成されます。 このモデルは、アプリが管理するデータを表します。

#### データベース コンテキストの追加

次に、データ モデルの [Entity Framework](https://learn.microsoft.com/ja-jp/ef/core/) 機能を調整するデータベース コンテキスト クラスを定義します。 このクラスは、アプリケーションとデータベース間の相互作用を管理する [Microsoft.EntityFrameworkCore.DbContext](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.entityframeworkcore.dbcontext) クラスを継承します。 データベース コンテキストを追加するには、次の手順に従います。

1. プロジェクトのルート フォルダーに *DbContext* という名前のフォルダーを作成します。
2. *DbContext* フォルダーに移動し、`ToDoContext.cs`という名前のファイルを作成し、次のコードを追加します。

    ```cs
    using Microsoft.EntityFrameworkCore;
    using ToDoListAPI.Models;
    
    namespace ToDoListAPI.Context;
    
    public class ToDoContext : DbContext
    {
        public ToDoContext(DbContextOptions<ToDoContext> options) : base(options)
        {
        }
    
        public DbSet<ToDo> ToDos { get; set; }
    }
    ```
3. プロジェクトのルート フォルダー内の *Program.cs* ファイルを開き、次のコードで更新します。

    ```cs
    // Add the following to your imports
    using ToDoListAPI.Context;
    using Microsoft.EntityFrameworkCore;
    
    //Register ToDoContext as a service in the application
    builder.Services.AddDbContext<ToDoContext>(opt =>
        opt.UseInMemoryDatabase("ToDos"));
    ```

前のコード スニペットでは、DB コンテキストをスコープ付きサービスとして ASP.NET Core アプリケーション サービス プロバイダー (依存関係挿入コンテナーとも呼ばれます) に登録します。 また、ToDo List API のメモリ内データベースを使用するように `ToDoContext` クラスを構成します。

#### コントローラーを設定する

コントローラーは通常、リソースを管理するための作成、読み取り、更新、および削除 (CRUD) アクションを実装します。 このチュートリアルでは、API エンドポイントの保護に重点を置いているため、コントローラーには 2 つのアクション項目のみを実装します。 すべての To-Do 項目を取得するための Read all アクションと、新しい To-Do 項目を追加する作成アクション。 プロジェクトにコントローラーを追加するには、次の手順に従います。

1. プロジェクトのルート フォルダーに移動し、Controllers という名前のフォルダーを作成 *します*。
2. `ToDoListController.cs` フォルダー内に  という名前のファイルを作成し、次のボイラー プレート コードを追加します。

```cs
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Identity.Web;
using Microsoft.Identity.Web.Resource;
using ToDoListAPI.Models;
using ToDoListAPI.Context;

namespace ToDoListAPI.Controllers;

[Authorize]
[Route("api/[controller]")]
[ApiController]
public class ToDoListController : ControllerBase
{
    private readonly ToDoContext _toDoContext;

    public ToDoListController(ToDoContext toDoContext)
    {
        _toDoContext = toDoContext;
    }

    [HttpGet()]
    [RequiredScopeOrAppPermission()]
    public async Task<IActionResult> GetAsync(){...}

    [HttpPost]
    [RequiredScopeOrAppPermission()]
    public async Task<IActionResult> PostAsync([FromBody] ToDo toDo){...}

    private bool RequestCanAccessToDo(Guid userId){...}

    private Guid GetUserId(){...}

    private bool IsAppMakingRequest(){...}
}
```

#### コントローラーにコードを追加する

このセクションでは、前のセクションでスキャフォールディングされたコントローラーにコードを追加する方法について説明します。 ここでは、API を構築するのではなく、保護することに重点を置きます。

1. **必要なパッケージをインポートします。**`Microsoft.Identity.Web` パッケージは、トークン検証の処理などの認証ロジックを簡単に処理するのに役立つ、MSAL.NET のラッパーです。 エンドポイントで承認が必要になるように、組み込みの `Microsoft.AspNetCore.Authorization` パッケージを使用します。
2. この API を呼び出すアクセス許可は、ユーザーの代わりに委任されたアクセス許可か、ユーザーの代わりではなくクライアントからそれ自体として呼び出すアプリケーションのアクセス許可を使用して付与したので、呼び出しがアプリによってアプリのために行われているかどうかを把握することが重要です。 これを行う最も簡単な方法は、アクセス トークンにオプションの要求 `idtyp` 含まれているかどうかを調べる方法です。 この `idtyp` 要求が、API がトークンがアプリ トークンかアプリ + ユーザー トークンかを判断するための最も簡単な方法です。 `idtyp` オプション要求を有効にすることをお勧めします。

    `idtyp` 要求が有効になっていない場合は、`roles` および `scp` 要求を使用して、アクセス トークンがアプリ トークンかアプリ + ユーザー トークンかを判断できます。 Microsoft Entra ID によって発行されたアクセス トークンには、2 つの要求のうち少なくとも 1 つがあります。 ユーザーに発行されたアクセス トークンは、`scp` 要求を含みます。 アプリケーションに発行されたアクセス トークンは、`roles` 要求を含みます。 両方の要求を含むアクセス トークンはユーザーに対してのみ発行され、`scp` 要求は委任されたアクセス許可を指定し、`roles` 要求はユーザーのロールを指定します。 どちらも持たないアクセス トークンは受け入れられません。

    ```csharp
    private bool IsAppMakingRequest()
    {
        if (HttpContext.User.Claims.Any(c => c.Type == "idtyp"))
        {
            return HttpContext.User.Claims.Any(c => c.Type == "idtyp" && c.Value == "app");
        }
        else
        {
            return HttpContext.User.Claims.Any(c => c.Type == "roles") && !HttpContext.User.Claims.Any(c => c.Type == "scp");
        }
    }
    ```
3. 行われている要求に、目的のアクションを実行するのに十分なアクセス許可が含まれているかどうかを決定するヘルパー関数を追加します。 アプリが自身のための要求を行っているのか、またはアプリが特定のリソースを所有するユーザーに代わって呼び出しを行っているのかをユーザー ID を検証することで確認します。

    ```csharp
    private bool RequestCanAccessToDo(Guid userId)
        {
            return IsAppMakingRequest() || (userId == GetUserId());
        }
    
    private Guid GetUserId()
        {
            Guid userId;
            if (!Guid.TryParse(HttpContext.User.GetObjectId(), out userId))
            {
                throw new Exception("User ID is not valid.");
            }
            return userId;
        }
    ```
4. アクセス許可の定義を組み込んでルートを保護します。 `[Authorize]` 属性をコントローラー クラスに追加することで、API を保護します。 これによって、認可されている ID で API が呼び出された場合にのみコントローラー アクションを呼び出すことができるようになります。 アクセス許可の定義では、これらのアクションを実行するために必要なアクセス許可の種類を定義します。

    ```csharp
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class ToDoListController: ControllerBase{...}
    ```

    GET エンドポイントと POST エンドポイントにアクセス許可を追加します。 *Microsoft.Identity.Web.Resource* 名前空間の一部である *RequiredScopeOrAppPermission* メソッドを使用してこれを行います。 次に、*RequiredScopesConfigurationKey* 属性および *RequiredAppPermissionsConfigurationKey* 属性を使用して、このメソッドにスコープとアクセス許可を渡します。

    ```csharp
    [HttpGet]
    [RequiredScopeOrAppPermission(
        RequiredScopesConfigurationKey = "AzureAD:Scopes:Read",
        RequiredAppPermissionsConfigurationKey = "AzureAD:AppPermissions:Read"
    )]
    public async Task<IActionResult> GetAsync()
    {
        var toDos = await _toDoContext.ToDos!
            .Where(td => RequestCanAccessToDo(td.Owner))
            .ToListAsync();
    
        return Ok(toDos);
    }
    
    [HttpPost]
    [RequiredScopeOrAppPermission(
        RequiredScopesConfigurationKey = "AzureAD:Scopes:Write",
        RequiredAppPermissionsConfigurationKey = "AzureAD:AppPermissions:Write"
    )]
    public async Task<IActionResult> PostAsync([FromBody] ToDo toDo)
    {
        // Only let applications with global to-do access set the user ID or to-do's
        var ownerIdOfTodo = IsAppMakingRequest() ? toDo.Owner : GetUserId();
    
        var newToDo = new ToDo()
        {
            Owner = ownerIdOfTodo,
            Description = toDo.Description
        };
    
        await _toDoContext.ToDos!.AddAsync(newToDo);
        await _toDoContext.SaveChangesAsync();
    
        return Created($"/todo/{newToDo!.Id}", newToDo);
    }
    ```

#### コントローラーを使用するように API ミドルウェアを構成する

次に、HTTP 要求を処理するためにコントローラーを認識して使用するようにアプリケーションを構成します。 `program.cs` ファイルを開き、次のコードを追加して、コントローラー サービスを依存関係挿入コンテナーに登録します。

```csharp

builder.Services.AddControllers();

var app = builder.Build();
app.MapControllers();

app.Run();
```

前のコード スニペットでは、 `AddControllers()` メソッドは、必要なサービスを登録してコントローラーを使用するようにアプリケーションを準備しますが、 `MapControllers()` は、着信 HTTP 要求を処理するためにコントローラー ルートをマップします。

### API を実行する

コマンド `dotnet run`を使用して、API を実行してエラーが発生しないようにします。 テスト中でも HTTPS プロトコルを使用する場合は、信頼する必要があります [。NET の開発証明書](https://learn.microsoft.com/ja-jp/aspnet/core/tutorials/first-web-api#test-the-project)。

1. ターミナルで次のように入力して、アプリケーションを起動します。

    ```powershell
    dotnet run
    ```
2. ターミナルには、次のような出力が表示されます。これは、アプリケーションが `http://localhost:{port}` で実行され、要求をリッスンしていることを確認します。

    ```powershell
    Building...
    info: Microsoft.Hosting.Lifetime[0]
        Now listening on: http://localhost:{port}
    info: Microsoft.Hosting.Lifetime[0]
        Application started. Press Ctrl+C to shut down.
    ...
    ```

Web ページ `http://localhost:{host}` には、次の図のような出力が表示されます。 これは、API が認証なしで呼び出されているためです。 承認された呼び出しを行うには、次の 手順 を参照して、保護された Web API にアクセスする方法に関するガイダンスを参照してください。

[Image: Web ページが起動したときの 401 エラーを示すスクリーンショット。]

この API コードの完全な例については、[サンプル ファイル](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/tree/main/2-Authorization/3-call-own-api-dotnet-core-daemon)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-api-dotnet-core-call-protected-api"} -->
## チュートリアル: 保護された ASP.NET Core Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-call-protected-api
- Service: identity-platform
- Article date: 2025-03-18
- Summary: Microsoft ID プラットフォームを使用してエンドポイントが保護されている Web API を呼び出す方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、Microsoft Entra テナントに登録されている保護された Web API の構築とテストを示すシリーズの最後の部分です。 [このシリーズのパート 1](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-build-app) では、ASP.NET Core Web API を作成し、そのエンドポイントを保護しました。 次に、軽量デーモン アプリを作成し、テナントに登録し、デーモン アプリを使用して構築した Web API をテストします。

このチュートリアルでは、次の操作を行います。

- デーモン アプリを登録する
- デーモン アプリにアプリ ロールを割り当てる
- デーモン アプリをビルドする
- デーモン アプリを実行して保護された Web API を呼び出す

### 前提条件

- まだ行っていない場合は、「[チュートリアル: Microsoft ID プラットフォームを使用して ASP.NET Core Web API を構築して保護](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-build-app)する」を完了します

### デーモン アプリを登録する

次の手順は、Microsoft Entra 管理センターでデーモン アプリを登録する方法を示しています。

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. **[+ 新規登録]** を選択します。
5. 表示される **[アプリケーションの登録] ページ**で、アプリケーションの登録情報を入力します。

    1. **[名前]** セクションに、アプリのユーザーに表示されるわかりやすいアプリケーション名 ("ciam-client-app" など) を入力します。
    2. **[サポートされているアカウントの種類]** で、**[この組織のディレクトリ内のアカウントのみ]** を選択します。
6. **登録** を選択します。
7. 登録が完了すると、アプリケーションの **[概要] ペイン**が表示されます。 ディレクトリ (テナント) ID と、アプリケーションのソース コードで使用するアプリケーション (クライアント) ID を記録します。

登録したアプリケーションに対してクライアント シークレットを作成します。 アプリケーションは、トークンの要求時にクライアント シークレットを使用して ID を証明します。

1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *Web アプリ クライアント シークレット*など) を選択して **[概要** ] ページを開きます。
2. **[管理]** で、**[証明書とシークレット]**&gt;**[クライアント シークレット]**&gt;**[新しいクライアント シークレット]** の順に選択します。
3. [ **説明** ] ボックスに、クライアント シークレットの説明 ( *Web アプリ クライアント シークレット*など) を入力します。
4. **[有効期限]** で、シークレットが (組織のセキュリティ規則に基づいて) 有効な期間を選択してから、**[追加]** を選択します。
5. シークレットの**値**を記録します。 この値は、後の手順で構成に使用します。 シークレットの値は再表示されず、**[証明書とシークレット]** から移動した後はどのような手段でも取得できません。 必ず記録しておくようにしてください。

### デーモン アプリにアプリ ロールを割り当てる

ユーザーなしで自分で認証するアプリケーションには、アプリのアクセス許可 (ロールとも呼ばれます) が必要です。 これらのアクセス許可により、アプリ自体はリソースに直接アクセスできます。 一方、サインインしているユーザーで API をテストする場合は、委任されたアクセス許可 (スコープ) を割り当てます。 委任されたアクセス許可により、アプリはユーザーのアクセス権に限定され、ユーザーに代わって動作できるようになります。 デーモン アプリにアプリケーションのアクセス許可を割り当てるには、次の手順に従います。

1. **[アプリの登録]** ページから、作成したアプリケーション (*ciam-client-app* など) を選択します。
2. [**管理**] で **API 許可**を選択します。
3. **[構成されたアクセス許可]** の下で **[アクセス許可の追加]** を選択します。
4. **[所属する組織で使用している API]** タブを選択します。
5. API の一覧で、API (*ciam-ToDoList-api* など) を選択します。
6. **[アプリケーションのアクセス許可]** オプションを選択します。 このアプリは、ユーザーの代理としてではなくそれ自体がサインインするものであるため、このオプションを選びます。
7. アクセス許可の一覧から、**TodoList.Read.All と ToDoList.ReadWrite.All** を選択します (必要に応じて検索ボックスを使用してください)。
8. **[アクセス許可の追加]** ボタンを選択します。
9. この時点で、アクセス許可が正しく割り当てられました。 ただし、デーモン アプリはユーザーが対話することを許可しないため、ユーザー自身がこれらのアクセス許可に同意することはできません。 この問題に対処するには、管理者が次のように、テナント内のすべてのユーザーに代わってこれらのアクセス許可に同意する必要があります。

    1. **[&lt;テナント名&gt; に管理者の同意を与えます]** を選択してから、**[はい]** を選択します。
    2. **[最新の情報に更新]** を選択してから、両方のアクセス許可の &lt; に **[&gt;テナント名 に付与されました]** と表示されていることを確認します。

### デーモン アプリをビルドする

1. .NET コンソール アプリを初期化し、そのルート フォルダーに移動します。

    ```dotnetcli
    dotnet new console -o MyTestApp
    cd MyTestApp
    ```
2. 次のコマンドを実行して、認証の処理に役立つ MSAL.NET をインストールします。

    ```dotnetcli
    dotnet add package Microsoft.Identity.Client
    ```
3. API プロジェクトを実行し、それが実行されているポートをメモします。
4. "Program.cs" ファイルを開き、"Hello world" コードを次のコードに置き換えます。

    ```csharp
    using System;
    using System.Net.Http;
    using System.Net.Http.Headers;
    
    HttpClient client = new HttpClient();
    
    var response = await client.GetAsync("http://localhost:<your-api-port>/api/todolist");
    Console.WriteLine("Your response is: " + response.StatusCode);
    ```

    デーモン アプリのルート ディレクトリに移動し、コマンド `dotnet run` を使用してアプリを実行します。 このコードは、アクセス トークンなしで要求を送信します。 文字列 "Your response is: Unauthorized (応答: 未承認)" がコンソールに出力されます。
5. 手順 4 のコードを削除し、次に置き換えて、有効なアクセス トークンで要求を送信して API をテストします。 このデーモン アプリは、ユーザーの操作なしで認証を行う際に、クライアント資格情報フローを使用してアクセス トークンを取得します。

    ```csharp
    using Microsoft.Identity.Client;
    using System;
    using System.Net.Http;
    using System.Net.Http.Headers;
    
    HttpClient client = new HttpClient();
    
    var clientId = "<your-daemon-app-client-id>";
    var clientSecret = "<your-daemon-app-secret>";
    var scopes = new[] {"api://<your-web-api-application-id>/.default"};
    var tenantId = "<your-tenant-id>";     //Use in workforce tenant configuration
    var tenantName = "<your-tenant-name>"; //Use in external tenant configuration
    var authority = $"https://login.microsoftonline.com/{tenantId}"; // Use "https://{tenantName}.ciamlogin.com" for external tenant configuration 
    
    var app = ConfidentialClientApplicationBuilder
        .Create(clientId)
        .WithAuthority(authority)
        .WithClientSecret(clientSecret)
        .Build();
    
    var result = await app.AcquireTokenForClient(new string[] { scopes }).ExecuteAsync();
    Console.WriteLine($"Access Token: {result.AccessToken}");
    
    client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", result.AccessToken);
    var response = await client.GetAsync("http://localhost:/<your-api-port>/api/todolist");
    var content = await response.Content.ReadAsStringAsync();
    
    Console.WriteLine("Your response is: " + response.StatusCode);
    Console.WriteLine(content);
    ```
6. コード内のプレースホルダーを、デーモン アプリのクライアント ID、シークレット、Web API アプリケーション ID、テナント名に置き換えます。

    - 外部テナントの場合は、オーソリティを次の形式で使用します: `"https://{tenantName}.ciamlogin.com/"`
    - 従業員テナントの場合は、オーソリティを次の形式で使用します: `"https://login.microsoftonline.com/{tenantId}"`
7. デーモン アプリのルート ディレクトリに移動し、コマンド `dotnet run` を使用してアプリを実行します。 このコードは、アクセス トークン付きで要求を送信します。 文字列 "Your response is: OK (応答: OK)" がコンソールに出力されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-app-dotnet-call-api"} -->
## チュートリアル: ユーザーをサインインさせる ASP.NET Core Web アプリをテストする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-call-api
- Service: identity-platform
- Article date: 2024-01-18
- Summary: Microsoft Graph Web API を呼び出し、サインインし、ログインユーザーのプロファイル情報を表示する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ASP.NET Core Web アプリのサインインとサインアウトのエクスペリエンスをテストし、ID トークンで要求を表示します。 前の [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-sign-in-users)では、認証要素、サインイン、サインアウト エクスペリエンスをアプリケーションに追加して、アプリが Web API を呼び出せるようにしました。 このチュートリアルでは、Microsoft Graph API を呼び出して、ログインしているユーザーのプロファイル情報を表示します。

このチュートリアルでは、次の操作を行います。

- アプリケーションをテストし、ID トークン要求を表示する
- アプリケーションからサインアウトする
- リソースをクリーンアップする

### [前提条件]

- [「チュートリアル: アプリケーションにサインインを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-sign-in-users)」の前提条件と手順の完了。

### アプリケーションをテストする

このセクションでは、サインインして Microsoft Graph API を呼び出して、ログインしているユーザーのプロファイル情報を表示することで、アプリケーションをテストする方法について説明します。

## [従業員テナント](#tab/workforce-tenant)
1. ターミナルで次のように入力してアプリケーションを起動します。これにより、`https`の プロファイルが起動します。

    ```bash
    dotnet run --launch-profile https
    ```
2. 新しいプライベート ブラウザーを開き、ブラウザーにアプリケーション URI を入力します (この場合は `https://localhost:5001`)。
3. サインイン ウィンドウが表示されたら、サインインに使用するアカウントを選択します。 アカウントがアプリ登録の条件と一致していることを確認します。
4. サインイン フローを完了するように指示された 1 回限りのパスコードを電子メールに入力します。 サインインしたままにするかどうかは、**[サインインしたままにする]** ウィンドウで選択できます。
5. アプリケーションは、アクセス権を付与したデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します。 **[Accept](https://learn.microsoft.com/ja-jp/entra/identity-platform/承認)** を選択します。
6. アプリケーションにサインインしたことを示す次のスクリーンショットが表示されます。 ID トークン要求が自動的に表示されます。

    [Image: API 呼び出しの結果を示すスクリーンショット。]

## [外部テナント](#tab/external-tenant)
1. ターミナルで次のように入力してアプリケーションを起動します。これにより、`https`の プロファイルが起動します。

    ```bash
    dotnet run --launch-profile https
    ```
2. 新しいプライベート ブラウザーを開き、ブラウザーにアプリケーション URI を入力します (この場合は `https://localhost:5001`)。
3. 前に構成したサインアップ ユーザー フローをテストするには、**[アカウントがありませんか? 作成します]** を選択します。
4. [ **アカウントの作成** ] ウィンドウで、外部テナントに登録されている電子メール アドレスを入力します。これによって、アプリケーションのユーザーとしてサインアップ フローが開始されます。
5. サインアップ フローを完了するための指示に従って、電子メール、1 回限りのパスコード、新しいパスワードを入力します。 サインインしたままにするかどうかは、**[サインインしたままにする]** ウィンドウで選択できます。
6. アプリケーションは、アクセス権を付与したデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します。 **[Accept](https://learn.microsoft.com/ja-jp/entra/identity-platform/承認)** を選択します。
7. アプリケーションにサインインしたことを示す次のスクリーンショットが表示されます。 ID トークン要求が自動的に表示されます。

    [Image: API 呼び出しの結果を示すスクリーンショット。]

---

### アプリケーションからサインアウトする

アプリケーションがテストされ、Microsoft Graph API と呼ばれるので、アプリケーションからサインアウトする必要があります。

1. ページの右上隅にある **サインアウト** リンクを見つけて選択します。
2. サインアウトするアカウントを選択するように求められます。 サインインに使用したアカウントを選択します。
3. サインアウトしたことを示すメッセージが表示されます。ブラウザー ウィンドウを閉じることができるようになりました。

### リソースをクリーンアップする

今後使用する予定がない場合は、アプリケーションの登録を削除する必要があります。 ローカル アプリケーションと自己署名証明書を削除することもできます。

1. Microsoft Entra 管理センターでアプリケーションの **[概要** ] ページに移動し、ページの上部にある [ **削除** ] を選択します。 サイド パネルのチェック ボックスをオンにし、[削除] を選択 **します**。
2. ローカル アプリケーションを見つけて、IDE またはターミナルを使用して削除します。
3. 証明書が別のテスト アプリケーションで使用されていないことを確認し、自己署名証明書でプロセスを繰り返します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-app-dotnet-prepare-app"} -->
## チュートリアル: 認証用の Web アプリケーションを準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app
- Service: identity-platform
- Article date: 2025-04-15
- Summary: Microsoft ID プラットフォームでの認証用に ASP.NET Core アプリケーションを作成して準備し、自己署名証明書を使用してセキュリティで保護する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ASP.NET Core Web アプリを作成し、認証用に構成します。 これは、ASP.NET Core Web アプリケーションを構築し、Microsoft Entra 管理センターを使用して認証用に準備する方法を示すシリーズのパート 1 です。 このアプリケーションは、従業員テナントの従業員または外部テナントを使用している顧客に使用できます

このチュートリアルでは、次の操作を行います。

- ASP.NET Core Web アプリを作成する
- 自己署名証明書を作成する
- アプリケーションの設定を構成する
- プラットフォームの設定と URL を定義する

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。 このアカウントには、アプリケーションを管理するためのアクセス許可が必要です。 アプリケーションを登録するために必要な次のロールのいずれかを使用します。
    - アプリケーション管理者
    - アプリケーション開発者
- ASP.NET Core アプリケーションをサポートする統合開発環境 (IDE) は使用できますが、このチュートリアルでは **Visual Studio Code を**使用します。 はこちら ダウンロードできます。
- [.NET 8.0 SDK](https://dotnet.microsoft.com/download/dotnet) の最小要件。
- ASP.NET Core 開発者証明書。 [dotnet dev-certs](https://learn.microsoft.com/ja-jp/dotnet/core/additional-tools/self-signed-certificates-guide#with-dotnet-dev-certs) を使用してインストールする

## [従業員テナント](#tab/workforce-tenant)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `https://localhost:5001/signin-oidc`
    - **フロント チャネルログアウト URL**: `https://localhost:5001/signout-oidc`
- 開発目的で、 [自己署名証明書を作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-self-signed-certificate)します。 証明書を [アップロード](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials) し、証明書の **拇印**を記録するには、資格情報の追加を参照してください。 運用アプリ**には自己署名証明書を使用しないでください**。 信頼された証明機関を使用します。

## [外部テナント](#tab/external-tenant)
- 外部テナント。 お持ちでない場合は、Microsoft Entra 管理センターで [新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `https://localhost:5001/signin-oidc`
    - **フロント チャネルログアウト URL**: `https://localhost:5001/signout-oidc`
- 開発目的で、 [自己署名証明書を作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-self-signed-certificate)します。 証明書を [アップロード](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials) し、証明書の **拇印**を記録するには、資格情報の追加を参照してください。 運用アプリ**には自己署名証明書を使用しないでください**。 信頼された証明機関を使用します。
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。

---

### ASP.NET Core プロジェクトを作成する

このセクションでは、Visual Studio Code で ASP.NET Core プロジェクトを作成します。

1. Visual Studio Code を開き、[ **ファイル] &gt; [フォルダーを開く...]** を選択します。プロジェクトを作成する場所に移動して選択します。
2. **[ターミナル] &gt; [新しいターミナル]** を選択して、新しいターミナルを開きます。
3. 次のコマンドを入力して、モデル ビュー コントローラー (MVC) ASP.NET Core プロジェクトを作成します。

    ```console
    dotnet new mvc -n identity-client-web-app
    ```

### ID パッケージをインストールする

このアプリケーションは [Microsoft.Identity.Web を](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/microsoft-identity-web/) 使用し、関連する NuGet パッケージをインストールする必要があります。

次のスニペットを使用して、新しい *identity-client-web-app* フォルダーに変更し、関連する NuGet パッケージをインストールします。

```console
cd identity-client-web-app
dotnet add package Microsoft.Identity.Web.UI
```

### 認証用にアプリケーションを構成する

Microsoft ID プラットフォームを使用してユーザーをサインインする Web アプリケーション * は、appsettings.json*構成ファイルを使用して構成されます。 ASP.NET Core では、次の値を指定する必要があります。

## [従業員テナント](#tab/workforce-tenant)
| Setting | Description |
| --- | --- |
| `Instance` | 国内クラウドでアプリを実行するための認証エンドポイント。 次のいずれかを使用します。  - `https://login.microsoftonline.com/` (Azure パブリック クラウド)  - `https://login.microsoftonline.us/` (Azure 米国政府機関)  - `https://login.microsoftonline.de/` (Microsoft Entra Germany)  - `https://login.partner.microsoftonline.cn/` (21Vianet が運営する Microsoft Entra China) |
| `TenantId` | アプリが登録されているテナントの識別子。 **推奨:** アプリ登録のテナント ID を使用します。 **選択肢：** - `organizations` (職場または学校アカウント)  - `common` (職場/学校または Microsoft 個人アカウント)  - `consumers` (Microsoft 個人アカウントのみ)。 |
| `ClientId` | アプリケーション登録から取得したアプリケーション (クライアント) の識別子。 |
| `CertificateThumbprint` | Microsoft Entra 管理センターにアップロードされた証明書の拇印 ( [資格情報の追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials)を参照)。 |
| `CallbackPath` | 応答のリダイレクトに使用されるパス。このチュートリアルの `/signin-oidc` に設定します。 |
| `DownstreamApi` | Microsoft Graph にアクセスするためのエンドポイントを定義する識別子。 アプリケーション URI を必要なスコープ (たとえば、 `user.read`) と組み合わせます。 |

## [外部テナント](#tab/external-tenant)
| Setting | Description |
| --- | --- |
| `Authority` | アプリケーションが登録されている外部テナントの URL。 形式: `https://<tenant_subdomain>.ciamlogin.com/`. テナント サブドメインの詳細を取得するには、「 [外部テナントの作成」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)参照してください。 |
| `ClientId` | アプリケーション登録から取得したアプリケーション (クライアント) の識別子。 |
| `CertificateThumbprint` | Microsoft Entra 管理センターで [資格情報を追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials) して取得した証明書の拇印。 |

---

#### 構成ファイルを更新する

IDE で *appsettings.json* を開き、ファイルの内容を次のスニペットに置き換えます。 引用符で囲まれたテキストを、前に記録した値に置き換えます。

## [従業員テナント](#tab/workforce-tenant)
```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "Enter_the_Tenant_Id_Here",
    "ClientId": "Enter_the_Application_Id_Here",
    "ClientCertificates": [
      {
        "SourceType": "StoreWithThumbprint",
        "CertificateStorePath": "CurrentUser/My",
        "CertificateThumbprint": "Enter the certificate thumbprint obtained the Microsoft Entra admin center"
      }   
    ],
    "CallbackPath": "/signin-oidc"
  },
    "DownstreamApi": {
      "BaseUrl": "https://graph.microsoft.com/v1.0/",
      "RelativePath": "me",
      "Scopes": [ 
        "user.read" 
      ]
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

## [外部テナント](#tab/external-tenant)
```json
{
  "AzureAd": {
    "Authority": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/",
    "ClientId": "Enter_the_Application_Id_Here",
    "ClientCertificates": [
      {
        "SourceType": "StoreWithThumbprint",
        "CertificateStorePath": "CurrentUser/My",
        "CertificateThumbprint": "Enter the certificate thumbprint obtained the Microsoft Entra admin center"
      }   
    ],
    "CallbackPath": "/signin-oidc",
    "SignedOutCallbackPath": "/signout-callback-oidc"
  },
  "DownstreamApi": {
    "BaseUrl": "https://graph.microsoft.com/v1.0/",
    "RelativePath": "me",
    "Scopes": [ 
      "user.read" 
    ]
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

---

#### リダイレクト URI を更新する

前提条件から、リダイレクト URI は `https://localhost:5001/signin-oidc` に設定されます。 これは、アプリケーションの起動設定で更新する必要があります。 ローカル アプリケーションのセットアップ中に作成されたリダイレクト URI、またはアプリケーション登録のリダイレクト URI と一致する場合は、その他の使用可能なポート番号を使用できます。

1. **[プロパティ]** フォルダーで *launchSettings.json* ファイルを開きます。
2. `https` オブジェクトを見つけ、`applicationURI`の値を正しいポート番号で更新します (この場合は`5001`)。 この行は次のスニペットのようになります。

    ```json
    "applicationUrl": "https://localhost:5001;http://localhost:{port}",
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-app-dotnet-sign-in-users"} -->
## 承認と認証のために ASP.NET Core Web アプリを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-sign-in-users
- Service: identity-platform
- Article date: 2024-01-18
- Summary: ID パッケージとサインイン コンポーネントを ASP.NET Core アプリケーションにインストールし、ユーザー認証を有効にする方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、認証要素と承認要素を ASP.NET Core Web アプリに追加します。 前の [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app)では、ASP.NET Core プロジェクトを作成し、認証用に構成しました。

このチュートリアルでは、次の操作を行います。

- 認可要素と認証要素をコードに追加する
- ID トークンでの要求の表示を有効にする
- サインインとサインアウトエクスペリエンスを追加する

### [前提条件]

- [「チュートリアル: ユーザーを認証する ASP.NET Core Web アプリを設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app)」の前提条件と手順の完了。

### 認証要素と承認要素を追加する

*ASP.NET* Core Web アプリに*認証要素と*承認要素を追加するには、HomeController.csファイルとProgram.cs ファイルを変更する必要があります。 これには、ホーム ページの管理、正しい名前空間の追加、サインインの構成が含まれます。

#### *HomeController.cs* に認可を追加する

アプリケーションのホーム ページには、ユーザーを承認する機能が必要です。 `Microsoft.AspNetCore.Authorization`名前空間は、Web アプリへの承認を実装するためのクラスとインターフェイスを提供します。 `[Authorize]`属性は、認証されたユーザーのみが Web アプリを使用できるように指定するために使用されます。

1. Web アプリで *Controllers/HomeController.cs* を開き、ファイルの先頭に次のスニペットを追加します。

    ```csharp
    using System.Diagnostics;
    using Microsoft.AspNetCore.Authorization;
    using Microsoft.AspNetCore.Mvc;
    using dotnetcore_webapp.Models;
    ```
2. 次のスニペットに示すように、`[Authorize]` クラス定義の上に`HomeController`属性を追加します。

    ```csharp
    [Authorize]
    public class HomeController : Controller
    {
    ...
    ```

#### 認証要素と承認要素を*Program.cs*に追加する

*Program.cs* ファイルはアプリケーションのエントリ ポイントであり、Web アプリに認証と承認を追加するように変更する必要があります。 認証に *appsettings.jsonで定義 * されている設定をアプリが使用できるようにするには、サービスを追加する必要があります。

1. ファイルの先頭に次の名前空間を追加します。

    ```csharp
    using Microsoft.AspNetCore.Authentication.OpenIdConnect;
    using Microsoft.AspNetCore.Authorization;
    using Microsoft.AspNetCore.Mvc.Authorization;
    using Microsoft.Identity.Web;
    using Microsoft.Identity.Web.UI;
    using System.IdentityModel.Tokens.Jwt;
    ```
2. 次に、認証に Microsoft ID を使用するようにアプリを構成する Microsoft Identity Web アプリ認証サービスを追加します。

    ```csharp
    // Add services to the container.
    builder.Services.AddControllersWithViews();
    
    // This is required to be instantiated before the OpenIdConnectOptions starts getting configured.
    // By default, the claims mapping will map claim names in the old format to accommodate older SAML applications.
    // This flag ensures that the ClaimsIdentity claims collection will be built from the claims in the token
    JwtSecurityTokenHandler.DefaultMapInboundClaims = false;
    
    // Sign-in users with the Microsoft identity platform
    builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
        .AddMicrosoftIdentityWebApp(builder.Configuration)
        .EnableTokenAcquisitionToCallDownstreamApi()
        .AddInMemoryTokenCaches();
    
    builder.Services.AddControllersWithViews(options =>
    {
        var policy = new AuthorizationPolicyBuilder()
            .RequireAuthenticatedUser()
            .Build();
        options.Filters.Add(new AuthorizeFilter(policy));
    }).AddMicrosoftIdentityUI();
    
    ```
3. 次に、認証機能を有効にするようにミドルウェアを構成する必要があります。 残りのコードを次のスニペットに置き換えます。

    ```csharp
    var app = builder.Build();
    
    // Configure the HTTP request pipeline.
    if (!app.Environment.IsDevelopment())
    {
        app.UseExceptionHandler("/Home/Error");
        // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
        app.UseHsts();
    }
    
    app.UseHttpsRedirection();
    app.UseStaticFiles();
    
    app.UseRouting();
    app.UseAuthorization();
    
    app.MapControllerRoute(
        name: "default",
        pattern: "{controller=Home}/{action=Index}/{id?}");
    
    app.Run();
    ```

### サインインとサインアウトエクスペリエンスを追加する

UI は、サインインとサインアウトのためのよりわかりやすいエクスペリエンスを提供するための更新プログラムである必要があります。このセクションでは、ユーザーの認証状態に基づいてナビゲーション項目を表示する新しいファイルを作成する方法を示します。 このコードでは ID トークン要求を読み取り、ユーザーが認証済みで、`User.Claims` を使用して ID トークン要求を抽出するかどうか確認します。

1. *Views/Shared* で新しいファイルを作成し、*\_LoginPartial.cshtml* という名前を付けます。
2. ファイルを開き、サインインとサインアウトエクスペリエンスを追加するための次のコードを追加します。

    ```csharp
    @using System.Security.Principal
    
    <ul class="navbar-nav">
    @if (User.Identity is not null && User.Identity.IsAuthenticated)
    {
            <li class="nav-item">
                <span class="nav-link text-dark">Hello @User.Claims.First(c => c.Type == "preferred_username").Value!</span>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark" asp-area="MicrosoftIdentity" asp-controller="Account" asp-action="SignOut">Sign out</a>
            </li>
    }
    else
    {
            <li class="nav-item">
                <a class="nav-link text-dark" asp-area="MicrosoftIdentity" asp-controller="Account" asp-action="SignIn">Sign in</a>
            </li>
    }
    </ul>
    ```
3. *Views/Shared/\_Layout.cshtml* を開き、前の手順で作成した`_LoginPartial`への参照を追加します。 次のスニペットに示すように、 `navbar-nav` クラスの末尾の近くに配置します。

    ```csharp
    <div class="navbar-collapse collapse d-sm-inline-flex justify-content-between">
        <ul class="navbar-nav flex-grow-1">
            <li class="nav-item">
                <a class="nav-link text-dark" asp-area="" asp-page="/Index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark" asp-area="" asp-page="/Privacy">Privacy</a>
            </li>
        </ul>
        <partial name="_LoginPartial" />
    </div>
    ```

### カスタム URL ドメインを使用する (省略可能)

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザー視点では、認証プロセス中に*ユーザーはドメインに留まる*ため、ciamlogin.com ドメイン名にリダイレクトされません。

カスタム ドメインを使用するには、次の手順に従います。

1. [「外部テナントのアプリのカスタム URL ドメインを有効にする」の](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)手順に従って、外部テナントのカスタム URL ドメインを有効にします。
2. *appsettings.json* ファイルを開きます。

    1. `Instance`パラメーターと`TenantId` パラメーターを `Authority` プロパティに更新します。
    2. `Authority` 値に文字列 `https://Enter_the_Custom_Domain_Here/Enter_the_Tenant_ID_Here` を追加します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
    3. 値 `knownAuthorities` を持つ プロパティを追加します。

*appsettings.json* ファイルに変更を加えた後、カスタム URL ドメインが *login.contoso.com* で、テナント ID が *aaaabbbb-0000-cccc-1111-dddd222eeee* の場合、ファイルは次のスニペットのようになります。

```json
{
  "AzureAd": {
    "Authority": "https://login.contoso.com/aaaabbbb-0000-cccc-1111-dddd2222eeee",
    "ClientId": "Enter_the_Application_Id_Here",
    "ClientCertificates": [
      {
        "SourceType": "StoreWithThumbprint",
        "CertificateStorePath": "CurrentUser/My",
        "CertificateThumbprint": "AA11BB22CC33DD44EE55FF66AA77BB88CC99DD00"
      }   
    ],
    "CallbackPath": "/signin-oidc",
    "SignedOutCallbackPath": "/signout-callback-oidc",
    "KnownAuthorities": ["login.contoso.com"]
    ...
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-app-node-call-microsoft-graph-api"} -->
## Express.js Web アプリから Microsoft Graph API を Tutorial-Call する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-call-microsoft-graph-api
- Service: identity-platform
- Article date: 2025-01-03
- Summary: Node/Express.js Web でアクセス トークンを取得して、Microsoft Graph API からユーザーのプロファイルの詳細を読み取る方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Node/Express.js Web アプリから Microsoft Graph API を呼び出します。 ユーザーがサインインすると、アプリは Microsoft Graph API を呼び出すアクセス トークンを取得します。

このチュートリアルは、3 部構成のチュートリアル シリーズのパート 3 です。

このチュートリアルでは、次の操作を行います。

- Node/Express.js Web アプリを更新してアクセス トークンを取得する
- アクセス トークンを使用して Microsoft Graph API を呼び出します。

### [前提条件]

- [「チュートリアル: Microsoft ID プラットフォームを使用して、Node/Express.js Web アプリにサインインを追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-sign-out)する」の手順を完了します。

### UI コンポーネントを追加する

1. コード エディターで *views/index.hbs* ファイルを開き、次のコード スニペットを使用して **ユーザー プロファイルの表示** リンクを追加します。

    ```html
    <a href="/users/profile">View user profile</a>
    ```

    更新後、 *views/index.hbs* ファイルは次のファイルのようになります。

    ```html
       <h1>{{title}}</h1>
        {{#if isAuthenticated }}
        <p>Hi {{username}}!</p>
        <a href="/users/id">View ID token claims</a>
        <br>
        <a href="/users/profile">View user profile</a>
        <br>
        <br>
        <a href="/auth/signout">Sign out</a>
        {{else}}
        <p>Welcome to {{title}}</p>
        <a href="/auth/signin">Sign in</a>
        {{/if}}
    ```
2. *views/profile.hbs ファイルを*作成し、次のコードを追加します。

    ```html
    <h1>Microsoft Graph API</h1>
    <h3>/me endpoint response</h3>
    <table>
        <tbody>
            {{#each profile}}
            <tr>
                <td>{{@key}}</td>
                <td>{{this}}</td>
            </tr>
            {{/each}}
        </tbody>
    </table>
    <br>
    <a href="/">Go back</a>
    ```

    - このページには、Microsoft Graph API から返されるユーザーのプロファイルの詳細が表示されます。

### アクセス トークンの取得

コードエディタで*auth/AuthProvider.js*ファイルを開き、次に`AuthProvider`クラスに`getToken`メソッドを追加します。

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

`getToken`メソッドは、指定したスコープを使用してアクセス トークンを取得します

### 呼び出し API ルートを追加する

コード エディターで *routes/users.js* ファイルを開き、次のルートを追加します。

```javascript
router.get(
    "/profile",
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
        .send("Failed to fetch profile details"); 
    }
    res.render("profile", {
        profile: graphResponse,
    });
    },
);
```

- 顧客ユーザーが [ユーザー `/profile`] リンクを選択すると、 ルートがトリガーされます。 アプリ:

    - *User.Read* アクセス許可を持つアクセス トークンを取得します。
    - Microsoft Graph API を呼び出して、サインインしているユーザーのプロファイルを読み取ります。
    - *profile.hbs* UI にユーザーの詳細を表示します。

### Microsoft Graph API を呼び出す

ファイル *fetch.js* 作成し、次のコードを追加します。

```javascript
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

    try{
        const response = await axios.get(endpoint, options);
        return await response.data;

    }catch(error){
        throw new Error(error);
    }

};

module.exports = { fetch };
```

実際の API 呼び出しは * 、fetch.js* ファイルで行われます。

### Node/Express.js Web アプリを実行してテストする

1. 「[Node/Express.js Web アプリの実行とテスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-sign-out#run-and-test-the-nodeexpressjs-web-app) の手順に従って、Web アプリを実行してください。」
2. サインインしたら、[ **ユーザー プロファイル** リンクの表示] を選択します。 アプリが正常に動作する場合は、サインインしているユーザーのプロファイルが Microsoft Graph API から読み取られたものとして表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app"} -->
## Microsoft ID プラットフォームを使用して Node.js/Express Web アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app
- Service: identity-platform
- Article date: 2025-02-25
- Summary: 外部テナントまたは従業員テナントの従業員によって顧客向けアプリにユーザーをサインインさせるノード Web アプリ プロジェクトを設定する

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、外部テナントの顧客向けアプリまたは従業員テナントの従業員にユーザーをサインインさせる Node/Express.js Web アプリを構築する方法について説明します。 このチュートリアルでは、Microsoft Graph API を呼び出すためのアクセス トークンを取得する方法についても説明します。

このチュートリアルは、3 部構成のシリーズのパート 1 です。

このチュートリアルでは、次の手順を行います。

- Node.js プロジェクトを設定する
- 依存関係のインストール
- アプリ ビューと UI コンポーネントを追加する

### [前提条件]

## [人材テナント](#tab/workforce-tenant)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/auth/redirect`
    - **フロント チャネル ログアウト URL**: `https://localhost:5001/signout-callback-oidc`
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。

## [外部テナント](#tab/external-tenant)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/auth/redirect`
    - **フロント チャネル ログアウト URL**: `https://localhost:5001/signout-callback-oidc`
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。

---

- [Node.js](https://nodejs.org).
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。

### Node.js プロジェクトを作成する

1. コンピューター内の任意の場所に、 *ciam-sign-in-node-express-web-app* などのノード アプリケーションをホストするフォルダーを作成します。
2. ターミナルで、 `cd ciam-sign-in-node-express-web-app`などの Node Web アプリ フォルダーにディレクトリを変更し、次のコマンドを実行して新しい Node.js プロジェクトを作成します。

    ```powershell
    npm init -y
    ```

    `init -y` コマンドは、Node.js プロジェクトの既定*の *package.jsonファイルを作成します。
3. 次のプロジェクト構造を実現するために、追加のフォルダーとファイルを作成します。

    ```text
        ciam-sign-in-node-express-web-app/
        ├── server.js
        └── app.js
        └── authConfig.js
        └── package.json
        └── .env
        └── auth/
            └── AuthProvider.js
        └── controller/
            └── authController.js
        └── routes/
            └── auth.js
            └── index.js
            └── users.js
        └── views/
            └── layouts.hbs
            └── error.hbs
            └── id.hbs
            └── index.hbs   
        └── public/stylesheets/
            └── style.css
    ```

### アプリの依存関係をインストールする

必要な ID と関連する npm パッケージ Node.js インストールするには、ターミナルで次のコマンドを実行します。

```powershell
npm install express dotenv hbs express-session axios cookie-parser http-errors morgan @azure/msal-node   
```

### アプリ UI コンポーネントをビルドする

1. コード エディターで *views/index.hbs ファイルを* 開き、次のコードを追加します。

    ```html
        <h1>{{title}}</h1>
        {{#if isAuthenticated }}
        <p>Hi {{username}}!</p>
        <a href="/users/id">View ID token claims</a>
        <br>
        <a href="/auth/signout">Sign out</a>
        {{else}}
        <p>Welcome to {{title}}</p>
        <a href="/auth/signin">Sign in</a>
        {{/if}}
    ```

    このビューでは、ユーザーが認証されると、ユーザー名とリンクが表示され、 `/auth/signout` および `/users/id` エンドポイントにアクセスできます。それ以外の場合は、ユーザーがサインインするために `/auth/signin` エンドポイントにアクセスする必要があります。 これらのエンドポイントの高速ルートは、この記事の後半で定義します。
2. コード エディターで *views/id.hbs ファイルを* 開き、次のコードを追加します。

    ```html
        <h1>Azure AD for customers</h1>
        <h3>ID Token</h3>
        <table>
            <tbody>
                {{#each idTokenClaims}}
                <tr>
                    <td>{{@key}}</td>
                    <td>{{this}}</td>
                </tr>
                {{/each}}
            </tbody>
        </table>
        <a href="/">Go back</a>
    ```

    このビューを使用して、ユーザーが正常にサインインした後に Microsoft Entra 外部 ID がこのアプリに返す ID トークン要求を表示します。
3. コード エディターで *views/error.hbs ファイルを* 開き、次のコードを追加します。

    ```html
        <h1>{{message}}</h1>
        <h2>{{error.status}}</h2>
        <pre>{{error.stack}}</pre>
    ```

    このビューを使用して、アプリの実行時に発生するエラーを表示します。
4. コード エディターで *views/layout.hbs ファイルを* 開き、次のコードを追加します。

    ```html
        <!DOCTYPE html>
        <html>        
            <head>
                <title>{{title}}</title>
                <link rel='stylesheet' href='/stylesheets/style.css' />
            </head>            
            <body>
                {{{body}}}
            </body>        
        </html>
    ```

    `layout.hbs` ファイルはレイアウト ファイルにあります。 これには、アプリケーション ビュー全体で必要な HTML コードが含まれています。
5. コード エディターで、 *public/stylesheets/style.css* ファイルを開き、次のコードを追加します。

    ```css
        body {
          padding: 50px;
          font: 14px "Lucida Grande", Helvetica, Arial, sans-serif;
        }
    
        a {
          color: #00B7FF;
        }
    ```
<!-- /MSL-PAGE -->
