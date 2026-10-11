# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 11)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 63

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/class-components"} -->
## クラス コンポーネントでの MSAL React の使用 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/class-components
- Service: msal / msal-react
- Article date: 2025-05-21
- Summary: 初期化、コンポーネントの保護、MSAL React コンテキストへのアクセス、ログインをカバーするクラス コンポーネントで MSAL React を使用する方法について説明します。

MSAL React では、関数コンポーネントとクラス コンポーネントの両方がサポートされています。 この記事では、クラス コンポーネントで MSAL React を使用する方法について説明します。これにより、クラス コンポーネント内の MSAL React コンテキストを初期化、保護、アクセスできます。

クラス コンポーネント内で `@azure/msal-react` フックを使用できないことに注意することが重要です。 クラス コンポーネント内の認証状態にアクセスする必要がある場合は、 `@azure/msal-browser` を直接使用して同様の機能を取得する必要があります。

### Prerequisites

### 初期化

MSAL React を使用したクラス コンポーネントを使用した初期化は、関数コンポーネントとよく似ています。 関数コンポーネントを使用する場合と同様に、認証状態へのアクセスを必要とするコンポーネント ツリーの最上位レベルに [`MsalProvider`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react/#@azure-msal-react-msalprovider) コンポーネントが必要です。

次のスニペットでは、 `MsalProvider` コンポーネントを使用してアプリケーション全体をラップし、すべての子コンポーネントで MSAL インスタンスを使用できるようにします。 これにより、子コンポーネントは、ユーザー認証、トークンの取得、保護された API の呼び出しに MSAL を使用できます。

```javascript
import React from "react";
import { MsalProvider } from "@azure/msal-react";
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);

class App extends React.Component {
    render() {
        return (
            <MsalProvider instance={pca}>
                <YourAppComponents>
            </ MsalProvider>
        );
    }
}
```

### コンポーネントの保護

MSAL React では、コンポーネントを保護し、ユーザーの認証状態に基づいて条件付きでレンダリングできます。 これは、関数コンポーネントの使用と同様に機能します。 主な例は次のとおりです。

- [`AuthenticatedTemplate`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react/#@azure-msal-react-authenticatedtemplate) - このコンポーネントは、ユーザーが認証された場合にのみ子をレンダリングします。 ユーザーが認証されていない場合、何もレンダリングされません。
- [`UnauthenticatedTemplate`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react/#@azure-msal-react-unauthenticatedtemplate) - このコンポーネントは、ユーザーが認証されていない場合にのみ子をレンダリングします。 ユーザーが認証されている場合、何もレンダリングされません。
- [`MsalAuthenticationTemplate`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react/#@azure-msal-react-msalauthenticationtemplate) - このコンポーネントは、子をレンダリングする前にユーザーの認証を試みます。 相互作用の種類 (リダイレクトまたはポップアップ) を prop として指定できます。ユーザーが認証されていない場合は、認証プロセスが開始されます。

次のスニペットは、 `AuthenticatedTemplate`、 `UnauthenticatedTemplate`、 `MsalAuthenticationTemplate` を使用して React コンポーネントを保護する方法を示しています。 `MSAL Provider` が子コンポーネントを囲んでいることに注目してください。

```javascript
import React from "react";
import { MsalProvider, AuthenticatedTemplate, UnauthenticatedTemplate, MsalAuthenticationTemplate } from "@azure/msal-react";
import { PublicClientApplication, InteractionType } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);

class App extends React.Component {
    render() {
        return (
            <MsalProvider instance={pca}>
                <AuthenticatedTemplate>
                    <span>This will only render for authenticated users</span>
                </ AuthenticatedTemplate>
                <UnauthenticatedTemplate>
                    <span>This will only render for unauthenticated users</span>
                </ UnauthenticatedTemplate>
                <MsalAuthenticationTemplate interactionType={InteractionType.Popup}>
                    <span>This will only render for authenticated users.</span>
                </ MsalAuthenticationTemplate>
            </ MsalProvider>
        );
    }
}
```

### クラス コンポーネント内の MSAL React コンテキストへのアクセス

`useMsal` フックを使用して、クラス コンポーネント内の MSAL React コンテキストにアクセスすることはできません。 これは、フックは、インスタンスなしで機能を使用できる関数コンポーネントでのみ使用できるためです。 クラス コンポーネントにはインスタンスがあるため、他に 2 つのオプションがあります。 生のコンテキストを直接使用するか、 `withMsal` 上位のコンポーネントを使用してコンポーネントのプロパティにコンテキストを挿入できます。

#### 生コンテキストへのアクセス

次のスニペットは、 `MsalContext` を使用して、クラス コンポーネント内の生のコンテキストにアクセスする方法を示しています。

```javascript
import React from "react";
import { MsalProvider, MsalContext } from "@azure/msal-react";
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);

class App extends React.Component {
    render() {
        return (
            <MsalProvider instance={pca}>
                <YourClassComponent/>
            </ MsalProvider>
        );
    }
}

class YourClassComponent extends React.Component {
    static contextType = MsalContext;

    render() {
        const isAuthenticated = this.context.accounts.length > 0;
        if (isAuthenticated) {
            return <span>There are currently {this.context.accounts.length} users signed in!</span>
        }
    }
}
```

実際の例については、*react-router-sample* の [ProfileRawContext.jsx](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/react-router-sample/src/pages/ProfileRawContext.jsx) を参照してください。

#### withMsal HOC を介したアクセス

別の方法として、 `withMsal` 上位コンポーネントを使用して、コンポーネントのプロパティにコンテキストを挿入します。 次のスニペットは、 `withMsal` HOC を使用してクラス コンポーネント内の MSAL React コンテキストにアクセスする方法を示しています。

```javascript
import React from "react";
import { MsalProvider, withMsal } from "@azure/msal-react";
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);

class YourClassComponent extends React.Component {
    render() {
        const isAuthenticated = this.props.msalContext.accounts.length > 0;
        if (isAuthenticated) {
            return <span>There are currently {this.props.msalContext.accounts.length} users signed in!</span>
        }
    }
}

const YourWrappedComponent = withMsal(YourClassComponent);

class App extends React.Component {
    render() {
        return (
            <MsalProvider instance={pca}>
                <YourWrappedComponent />
            </ MsalProvider>
        );
    }
}
```

実際の例については、*react-router-sample* の [ProfileWithMsal.jsx](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/react-router-sample/src/pages/ProfileWithMsal.jsx) を参照してください。

### クラス コンポーネントを使用したログイン

`MSAL React` コンテキストを取得するために実行する方法に関係なく、使用は同じになります。 コンテキスト オブジェクトを取得したら、で`PublicClientApplication`呼び出したり、サインインしているアカウントを調べたり、認証が現在進行中かどうかを判断したりできます。

次の例では、 `withMsal` HOC アプローチを使用してログインする方法を示しますが、必要に応じて他のアプローチにすばやく適応できます。

Note

`MsalProvider` コンポーネントは、コンテキストを使用するコンポーネントの上の任意のレベルでレンダリングする必要があります。 次の例では、プロバイダーがあることを前提としており、これを示しません。

#### ボタンをクリックした結果としてログインする

次のスニペットでは、`LoginButton`上位コンポーネントを使用する React クラス コンポーネント `withMsal`を定義します。 ユーザーが認証されていない場合はログイン ポップアップをトリガーするか、認証された場合はユーザーをログアウトするボタンがレンダリングされます。

```javascript
import React from "react";
import { withMsal } from "@azure/msal-react";

class LoginButton extends React.Component {
    render() {
        const isAuthenticated = this.props.msalContext.accounts.length > 0;
        const msalInstance = this.props.msalContext.instance;
        if (isAuthenticated) {
            return <button onClick={() => msalInstance.logout()}>Logout</button>    
        } else {
            return <button onClick={() => msalInstance.loginPopup()}>Login</button>
        }
    }
}

export default YourWrappedComponent = withMsal(LoginButton);
```

#### ページ読み込み時のログイン

次のスニペットでは、`ProtectedComponent`上位コンポーネントを使用する React クラス コンポーネント `withMsal`を定義します。 マウントと更新時にユーザーの認証が試行され、ユーザーが認証されているかどうか、または読み込みページで認証が進行中かどうかを表示します。

```javascript
import React from "react";
import { withMsal } from "@azure/msal-react";
import { InteractionStatus } from "@azure/msal-browser";

class ProtectedComponent extends React.Component {
    callLogin() {
        const isAuthenticated = this.props.msalContext.accounts.length > 0;
        const msalInstance = this.props.msalContext.instance;

        // If a user is not logged in and authentication is not already in progress, invoke login
        if (!isAuthenticated && this.props.msalContext.inProgress === InteractionStatus.None) {
            msalInstance.loginPopup();
        }
    }
    componentDidMount() {
        this.callLogin();
    }

    componentDidUpdate() {
        this.callLogin();
    }
    
    render() {
        const isAuthenticated = this.props.msalContext.accounts.length > 0;
        if (isAuthenticated) {
            return <span>User is authenticated</span>
        } else {
            return <span>Authentication in progress</span>;
        }
    }
}

export default YourWrappedComponent = withMsal(ProtectedComponent);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/errors"} -->
## MSAL React でエラーと例外を処理する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/errors
- Service: msal / msal-react
- Article date: 2026-03-15
- Summary: 一般的な認証エラーやトラブルシューティング手順など、MSAL React でエラーと例外を処理する方法について説明します

この記事では、MSAL React によってスローされる可能性のあるエラーと例外の一部を処理する方法について説明します。 BrowserConfigurationAuthErrors と BrowserAuthErrors について説明し、解決に役立つトラブルシューティング手順をいくつか提供します。

### BrowserConfigurationAuthErrors

BrowserConfigurationAuthErrors は、MSAL 認証パラメーターの構成が正しくないために発生するエラーを表すために使用され、 `PublicClientApplication` または `ConfidentialClientApplication` インスタンスを初期化するときに発生する可能性があります。 コンストラクターに渡された構成オブジェクトがライブラリの要件を満たしていない場合は、有効なクライアント ID または機関を指定しないか、無効なリダイレクト URI を指定すると、この種類のエラーが発生する可能性があります

#### `stubbed_public_client_application_called`

このエラーは、コンポーネント ツリーで上位の`PublicClientApplication`を使用せずに msal コンポーネントまたはフックを使用しようとすると、スタブアウトまたは誤って初期化された`MsalProvider` インスタンスでメソッドが呼び出されたことを意味します。 すべてのフックとコンポーネントは [、React Context API](https://reactjs.org/docs/context.html) を使用し、プロバイダーを必要とします。 `Stub instance of PublicClientApplication was called. If using @azure/msal-react, please ensure context is not used without a provider`のようなメッセージが表示される場合があります。

次のコード スニペットでは、`useMsal` フックが `MsalProvider` のコンテキスト外で使用されているため、このエラーが発生します。

```javascript
import { useMsal, MsalProvider } from "@azure/msal-react";
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);

function App() {
    const { accounts } = useMsal();

    return (
        <MsalProvider instance={pca}>
            <YourAppComponent>
        </ MsalProvider>
    )
}
```

このエラーを解決するには、上記のスニペットをリファクタリングして、 `useMsal` フックが `MsalProvider`の下のコンポーネントで呼び出されるようにする必要があります。 正しい実装を次のスニペットに示します。

```javascript
import { useMsal, MsalProvider } from "@azure/msal-react";
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);

function ExampleComponent () {
    const { accounts } = useMsal();

    return <YourAppComponent />;
};

function App() {
    return (
        <MsalProvider instance={pca}>
            <ExampleComponent />
        </ MsalProvider>
    )
}
```

### BrowserAuthErrors

BrowserAuthErrors は、ブラウザー環境での認証プロセス中に発生するエラーを表すために使用されます。 このような状況は、以前の要求がまだ処理されている間にユーザーが新しいログイン要求を開始しようとしたときに発生する可能性があります。 これらのエラーを解決するには、新しい操作を開始する前に、各操作が完了していることを確認する必要があります。

#### `Interaction_in_progress`

このエラーは、ある対話型 API (`loginPopup`、`loginRedirect`、`acquireTokenPopup`、`acquireTokenRedirect`) が呼び出されたときに、別の対話型 API がまだ進行中だった場合に発生します。 login API と acquireToken API は非同期であるため、別の約束を呼び出す前に、結果として得られる約束が解決されていることを確認する必要があります。 次のようなエラー メッセージが表示される場合があります `Interaction is currently in progress. Please ensure that this interaction has been completed before calling an interactive API.`

`@azure/msal-react`では、次の 2 つのシナリオが発生する可能性があります。

1. アプリケーションは、 `inProgress` 状態にアクセスできないコンテキストの外部でいずれかの API を呼び出しています。 コンテキストの詳細については、[MSAL React](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/faq) に関する FAQ を参照してください
2. アプリケーションは、他の場所で既に相互作用が進行中かどうかを最初に確認することなく、いずれかの API を呼び出しています。

次のスニペットは、実行中の対話型 API を別のコンポーネントが既に呼び出している場合にエラーをスローします。

```javascript
import { useMsal, useIsAuthenticated } from "@azure/msal-react";
import { useEffect } from "react";

export function exampleComponent() {
    const { instance } = useMsal();
    const isAuthenticated = useIsAuthenticated();

    useEffect(() => {
        if (!isAuthenticated) {
            // If another component has already invoked an interactive API this will throw
            await instance.loginPopup();
        }
    }, [isAuthenticated, instance]);
}
```

前のスニペットを修正するには、 `loginPopup`を呼び出す前に、他の操作が進行中でないことを確認します。

```javascript
import { useMsal, useIsAuthenticated } from "@azure/msal-react";
import { InteractionStatus } from "@azure/msal-browser";
import { useEffect } from "react";

export function exampleComponent() {
    const { instance, inProgress } = useMsal();
    const isAuthenticated = useIsAuthenticated();

    useEffect(() => {
        if (!isAuthenticated && inProgress === InteractionStatus.None) {
            await instance.loginPopup();
        }
    }, [isAuthenticated, inProgress, instance]);
}
```

##### トラブルシューティングの手順

- [詳細ログを有効に](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration#using-the-config-object) し、イベントの順序をトレースします。 別の API が解決される前に対話型 API が呼び出されていないことを確認します。 リダイレクト フローを使用する場合は、 `handleRedirectPromise` が解決されていることを確認します ( `MsalProvider`で実行)。

このエラーが発生する原因がわからない場合は、[issue を作成し](https://github.com/AzureAD/microsoft-authentication-library-for-js/issues/new/choose)、次の情報を共有する準備をしてください。

- 詳細ログ
- 問題の再現に使用できるサンプル アプリやコード スニペット
- ページを更新します。 エラーは消えませんか?
- 新しいタブでアプリケーションを開きます。エラーは消えませんか?
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/events"} -->
## MSAL React のイベント コールバック - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/events
- Service: msal / msal-react
- Article date: 2025-05-21
- Summary: MSAL React イベント コールバックを使用してログイン応答を管理し、特定のエラーを処理する方法について説明します。

ほとんどの場合、 `@azure/msal-react` はログイン呼び出しと応答の処理を抽象化します。 アプリケーション開発者は、保護する必要があるコンポーネントと、ユーザーのサインインに使用する方法を決定する必要がありますが、応答の詳細にあまり関心がない可能性があります。 ただし、アプリケーションがログイン呼び出しの応答に直接アクセスする必要がある場合や、特定のエラーを処理する必要がある場合があります。 `@azure/msal-browser` は、この目的で使用できる [イベント API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/events) を公開します。 この記事では、React アプリでこれを利用する方法について説明します。

### イベント コールバックの登録と登録解除

イベント API を使用すると、イベントが生成されたときに何かを行うイベント コールバックを登録できます。 React コンポーネントにイベント コールバックを登録する場合は、必ず 2 つのことを行う必要があります。

1. コールバックは 1 回だけ登録されます。
2. コールバックは、コンポーネントがマウント解除される前に登録解除されます。

#### 関数コンポーネント

関数コンポーネントでは、空の依存関係配列を持つ `useEffect` フックを使用してこれを実現できます。 例を次のスニペットに示します。

```javascript
import { useEffect } from "react";
import { useMsal } from "@azure/msal-react";
import { EventType } from "@azure/msal-browser";

function EventExample() {
    const { instance } = useMsal();

    useEffect(() => {
        // This will be run on component mount
        const callbackId = instance.addEventCallback((message) => {
            // This will be run every time an event is emitted after registering this callback
            if (message.eventType === EventType.LOGIN_SUCCESS) {
                const result = message.payload;    
                // Do something with the result
            }
        });

        return () => {
            // This will be run on component unmount
            if (callbackId) {
                instance.removeEventCallback(callbackId);
            }
        }
        
    }, []);
}
```

#### クラス コンポーネント

クラス コンポーネントでは、 `componentDidMount` と `componentWillUnmount` を使用してこれを実現できます。

```javascript
class EventExample extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            callbackId: null;
        }
    }

    componentDidMount() {
        // This will be run on component mount
        const callbackId = this.props.msalContext.instance.addEventCallback((message) => {
            // This will be run every time an event is emitted after registering this callback
            if (message.eventType === EventType.LOGIN_SUCCESS) {
                const result = message.payload;    
                // Do something with the result
            }
        });

        this.setState({callbackId: callbackId});
    }

    componentWillUnmount() {
        // This will be run on component unmount
        if (this.state.callbackId) {
            this.props.msalContext.instance.removeEventCallback(this.state.callbackId);
        }
    }
}
```

### タブとウィンドウ間でログに記録された状態を同期する

ユーザーが別のタブまたはウィンドウでアプリにログインまたはログアウトするときに UI を更新する場合は、 `ACCOUNT_ADDED` と `ACCOUNT_REMOVED` イベントをサブスクライブできます。 ペイロードは、追加または削除された `AccountInfo` オブジェクトになります。

これらのイベントは、既定では生成されません。 これらのイベントを有効にするには、イベント コールバックを登録する前に、 [`enableAccountStorageEvents`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/eventhandler#@azure-msal-browser-eventhandler-enableaccountstorageevents) API を呼び出す必要があります。

```javascript
import { useEffect } from "react";
import { useMsal } from "@azure/msal-react";
import { EventType } from "@azure/msal-browser";

function EventExample() {
    const { instance } = useMsal();

    useEffect(() => {
        // This will be run on component mount
        instance.enableAccountStorageEvents();
        const callbackId = instance.addEventCallback((message) => {
            // This will be run every time an event is emitted after registering this callback
            if (message.eventType === EventType.ACCOUNT_ADDED) {
                const account = message.payload;    
                // Update UI
            } else if (message.eventType === EventType.ACCOUNT_REMOVED) {
                const account = message.payload;
                // Update UI
            }
        });

        return () => {
            // This will be run on component unmount
            instance.disableAccountStorageEvents();
            if (callbackId) {
                instance.removeEventCallback(callbackId);
            }
        }
        
    }, []);
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/faq"} -->
## MSAL React に関してよく寄せられる質問 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/faq
- Service: msal / msal-react
- Article date: 2026-03-15
- Summary: 互換性、SSR サポート、クラス コンポーネントなど、MSAL React に関してよく寄せられる質問に対する回答を見つける

### Compatibility

#### どのブラウザーがサポートされていますか?

サポートされているブラウザーについては [、こちらを](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/FAQ.md#what-browsers-are-supported-by-msaljs) 参照してください。

#### サポートされている React のバージョンは何ですか?

最新バージョンの `@azure/msal-react` (v3.x) には React 18 以降が必要です。 新しいプロジェクトには React 19 をお勧めします。

Note

以前のバージョンの `@azure/msal-react` (v2.x) では、React 16.8 以降、17、18 がサポートされています。 v2.x から v3.x にアップグレードする場合は、アプリで React 18 以降が実行されていることを確認します。

#### `@azure/msal-react`はサーバー側レンダリング (SSR) または静的サイト生成をサポートしていますか?

Yes! ただし、認証はサーバー側では実行できないため、MSAL API サーバー側の呼び出しは避ける必要があります。 `@azure/msal-react` では、このロジックの一部が抽象化されますが、カスタム認証ロジックを構築する場合は、ブラウザーでレンダリングされるときにすべての API が呼び出されるようにしてください。 例については、 [Next.js サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/nextjs-sample) と [Gatsby サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/gatsby-sample) を参照してください。

#### `@azure/msal-react`はクラス コンポーネントをサポートしていますか?

はい、 `@azure/msal-react` は関数コンポーネントとクラス コンポーネントの両方をサポートします。 ただし、フックはクラス コンポーネントで使用できないため、MSAL コンテキストを使用し、 `@azure/msal-browser` によって提供される API を使用して同等のロジックを構築する必要があります。 クラス コンポーネントでの `@azure/msal-react` の使用の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/class-components)。

### Microsoft Graph JavaScript SDK で`@azure/msal-react`使用できますか?

はい。`@azure/msal-react`は、[Microsoft Graph JavaScript SDK](https://github.com/microsoftgraph/msgraph-sdk-javascript) のカスタム認証プロバイダーとして使用できます。 実装については、サンプルの [React SPA 呼び出しGraph API](https://github.com/Azure-Samples/ms-identity-javascript-react-tutorial/tree/main/2-Authorization-I/1-call-graph)を参照してください。

### Authentication

#### React アプリでリダイレクト フローを処理するにはどうすればよいですか?

過去に `@azure/msal-browser` または `msal` v1 を使用したことがある場合は、リダイレクトの応答を処理するために、ページ読み込み時に `handleRedirectCallback` または `handleRedirectPromise` を呼び出すために使用される場合があります。 `@azure/msal-react`では、これは内部で行われ、あなた自身で`handleRedirectPromise`呼び出す必要はありません。 `loginRedirect`または`acquireTokenRedirect`を呼び出すと、Microsoft Entraサインイン ページにリダイレクトされ、資格情報を入力すると、ユーザーがサインインしていることを示すアプリにリダイレクトされます。 これはアプリケーションの新しいクリーンなインスタンスであるため、実際の応答オブジェクトを元の `loginRedirect` API 呼び出しに返すことはできません。 代わりに、必要なものを取得する方法がいくつかあります。

ID またはアクセス トークンが必要な場合は、トークンが必要になる直前に `acquireTokenSilent` API を呼び出することをお勧めします。 トークンを取得する前に、ユーザーがサインインしていて、操作が進行中ではないことを確認してください。 前のリダイレクト操作が成功した場合、 `acquireTokenSilent` はキャッシュからトークンを返します。

```javascript
const GetDataFromAPI = () => {
    const { instance, accounts, inProgress } = useMsal();
    const isAuthenticated = useIsAuthenticated();
    const [graphData, setGraphData] = useState(null);

    useEffect(() => {
        if (isAuthenticated && inProgress === InteractionStatus.None) {
            instance.acquireTokenSilent({
                account: accounts[0],
                scopes: ["User.Read"]
            }).then(response => {
                callAPI(response.accessToken);
            })
        }
    }, [inProgress, isAuthenticated, accounts, instance]);
};
```

リダイレクト操作によって返される応答オブジェクトまたはエラーに直接アクセスする必要がある場合は、次の 3 つの方法でこれを行うことができます。

1. [イベント API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/events) を使用して、アプリに戻ったときに応答で呼び出されるコールバックを登録します。 これは、リダイレクト**後**に実行されるコードパスで登録されていることを確認してください。リダイレクト**前**に登録されたコールバックは失われます。
2. ログインには [useMsalAuthentication フック](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/hooks#usemsalauthentication-hook) を使用します。 このフックは、アプリに戻ったときに結果またはエラーを返します。
3. 結果で解決するか、リダイレクトの結果としてページが読み込まれた場合にエラーで拒否する `handleRedirectPromise` を呼び出します。

#### `@azure/msal-react`コンテキストの外部でできること

簡単な答えは、MSAL API へのアクセスを必要とするアプリ内のすべてのコンポーネントを、コンポーネントが MSAL コンテキストにアクセスできるように、 `MsalProvider` コンポーネントの下にレンダリングする必要があるということです。 ただし、コンポーネント ツリーの外部にユーティリティ関数を使用してアクセス トークンの取得を処理したり、ユーザーがサインインしているかどうかを判断したりする方が理にかなっている場合があります。 ユーザーのログイン状態を **変更** したり、対話をトリガーしたりする可能性のある API を呼び出さない限り、これは問題ありません。

つまり、次の API は、 `MsalProvider`のコンテキスト外では制限外です。

- `loginPopup`
- `loginRedirect`
- `handleRedirectPromise`
- `ssoSilent`
- `logout`
- `acquireTokenPopup`
- `acquireTokenRedirect`

**メモ：**react コンテキストの外部で他の`@azure/msal-browser` API を使用する場合でも、`PublicClientApplication`に渡すのと同じ`MsalProvider` インスタンスを使用する必要があります。

### セルフサービス サインアップを実装するにはどうすればよいですか?

MSAL React では、認証コード フローでのセルフサービス サインアップがサポートされます。 要求でサポートされているプロンプト値とその予想される結果、およびAzure テナントに対して行う必要がある[セルフサービス サインアップ](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#popuprequest)と構成の変更の概要については、[こちらの](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)ドキュメントを参照してください。 B2C およびテスト環境では、セルフサービス サインアップは使用できません。

### B2C

#### React アプリでパスワードを忘れた場合のフローを処理するにはどうすればよいですか?

[新しいパスワード リセット エクスペリエンス](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/add-password-reset-policy?pivots=b2c-user-flow#self-service-password-reset-recommended)が、サインアップまたはサインイン ポリシーの一部になりました。 ユーザーが **[パスワードを忘れた場合]** リンクを選択すると、すぐにパスワードを忘れた場合のエクスペリエンスが表示されます。 パスワードのリセットに別のポリシーは不要です。

新しいパスワード リセット エクスペリエンスに移行することをお勧めします。これにより、アプリの状態が簡略化され、ユーザー側でのエラー処理が減ります。 何らかの理由で従来のパスワードを忘れた場合のフローを使用する必要がある場合は、ユーザーが [`AADB2C90118`れた場合] リンクを選択したときに B2C サービスから返されたエラー応答を処理し、パスワードを忘れた場合のポリシーを使用してログインをもう一度呼び出す必要があります。

`useMsalAuthentication` フックを使用してログインする場合は、返されたエラーを調べ、新しい要求オブジェクトを使用してログイン コールバックを呼び出すことができます。

```javascript
function Example() {
    const { result, error, login } = useMsalAuthentication(InteractionType.Popup);

    useEffect(() => {
        if (error && error.errorMessage.indexOf("AADB2C90118") > -1) {
            const request = {
                authority: "your-b2c-authority/your-password-reset-policy"
            }
            login(InteractionType.Popup, request)
        }
    }, [error]);
}
```

`MsalAuthenticationTemplate`を使用している場合、またはログイン API のいずれかを直接呼び出す場合は、[イベント API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/events) を使用してエラーをキャッチして処理する必要があります。

```javascript
function Example() {
    const { instance } = useMsal();

    useEffect(() => {
        const callbackId = instance.addEventCallback((event) => {
            if (event.eventType === EventType.LOGIN_FAILURE) {
                if (event.error && event.error.errorMessage.indexOf("AADB2C90118") > -1) {
                    if (event.interactionType === InteractionType.Redirect) {
                        instance.loginRedirect(forgotPasswordRequest);
                    } else if (event.interactionType === InteractionType.Popup) {
                        instance.loginPopup(forgotPasswordRequest).catch(e => {
                            return;
                        });
                    }
                }
            }
        });

        return () => {
            if (callbackId) {
                instance.removeEventCallback(callbackId);
            }
        };
    }, []);
}
```

### Errors

受け取る特定のエラーに関する質問がある場合は、一般的なエラーの詳細を示す次のドキュメントを参照してください。

- [`@azure/msal-browser` エラー ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/errors)
- [`@azure/msal-react` エラー ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/errors)

### 質問に回答していない場合はどうすればよいですか?

まず、 `@azure/msal-browser`[FAQ](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/FAQ.md) を調べて、質問に回答しているかどうかを確認します。 `@azure/msal-react` は `@azure/msal-browser` のラッパーなので、疑問の多くについてはそちらで回答されています。

ロードマップに関する質問がある場合は、ここで計画された機能とリリースの概要を [確認](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/roadmap.md)できます。

このドキュメントまたは `@azure/msal-browser` FAQ で質問に回答できない場合は、 [問題を開](https://github.com/AzureAD/microsoft-authentication-library-for-js/issues/new/choose) き、できるだけ早く回答します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/getting-started"} -->
## MSAL React を使ってみる - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/getting-started
- Service: msal / msal-react
- Article date: 2026-03-15
- Summary: MSAL React の使用を開始します。 MSAL React を初期化する方法、ユーザーが認証されているかどうかを判断する方法、コンポーネントを保護する方法、ユーザーをサインインさせる方法、アクセス トークンを取得する方法について説明します。

この記事では、 `@azure/msal-react`の使用を開始する方法について説明します。 初期化、ユーザーの認証の判断、コンポーネントの保護、ユーザーのサインイン、アクセス トークンの取得について説明します。

### Prerequisites

- [Node.js](https://nodejs.org/en/download/)

### 初期化

`@azure/msal-react` は [React コンテキスト API](https://reactjs.org/docs/context.html) 上に構築されており、認証を必要とするアプリのすべての部分を [`MsalProvider`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react/#@azure-msal-react-msalprovider) コンポーネントにラップする必要があります。 まず、のインスタンスを[`PublicClientApplication`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/publicclientapplication)してから、これを prop として`MsalProvider`に渡す必要があります。

```javascript
import React from "react";
import { createRoot } from "react-dom/client";

import { MsalProvider } from "@azure/msal-react";
import { Configuration,  PublicClientApplication } from "@azure/msal-browser";

import App from "./app.jsx";

// MSAL configuration
const configuration: Configuration = {
    auth: {
        clientId: "client-id"
    }
};

const pca = new PublicClientApplication(configuration);

// Component
const AppProvider = () => (
    <MsalProvider instance={pca}>
        <App />
    </MsalProvider>
);

const root = createRoot(document.getElementById("root"));
root.render(<AppProvider />);
```

`MsalProvider`の下にあるすべてのコンポーネントは、コンテキストだけでなく、`PublicClientApplication`によって提供されるすべてのフックとコンポーネントを介して`@azure/msal-react` インスタンスにアクセスできます。

### ユーザーが認証されているかどうかを判断する

ほとんどのアプリケーションでは、ユーザーがサインインしているかどうかに基づいて、特定のコンポーネントを条件付きでレンダリングする必要があります。 `@azure/msal-react` は、これを行う 2 つの簡単な方法を提供します。

#### `AuthenticatedTemplate` および `UnauthenticatedTemplate`

`AuthenticatedTemplate` および `UnauthenticatedTemplate` コンポーネントは、ユーザーがそれぞれ認証または認証されていない場合にのみ子をレンダリングします。

```javascript
import React from 'react';
import { AuthenticatedTemplate, UnauthenticatedTemplate } from "@azure/msal-react";

export function App() {
    return (
        <React.Fragment>
            <p>Anyone can see this paragraph.</p>
            <AuthenticatedTemplate>
                <p>At least one account is signed in!</p>
            </AuthenticatedTemplate>
            <UnauthenticatedTemplate>
                <p>No users are signed in!</p>
            </UnauthenticatedTemplate>
        </React.Fragment>
    );
}
```

#### `useIsAuthenticated` フック

アプリの上のラッパー コンポーネントの代わりに、 `useIsAuthenticated` フックを使用できます。 詳細については、 [MSAL React の Hooks を](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/hooks#useisauthenticated-hook)参照してください。

```javascript
import React from 'react';
import { useIsAuthenticated } from "@azure/msal-react";

export function App() {
    const isAuthenticated = useIsAuthenticated();

    return (
        <React.Fragment>
            <p>Anyone can see this paragraph.</p>
            {isAuthenticated && (
                <p>At least one account is signed in!</p>
            )}
            {!isAuthenticated && (
                <p>No users are signed in!</p>
            )}
        </React.Fragment>
    );
}
```

### コンポーネントの保護

認証されたユーザーにのみ表示するコンポーネントがある場合は、上記のいずれかの方法を使用できます。 しかし、ユーザーがまだ認証されていない場合にログインを自動的に呼び出す場合はどうでしょうか。 `@azure/msal-react` は、 `MsalAuthenticationTemplate` または `useMsalAuthentication` フックでこれを行う 2 つの方法を提供します。

#### `MsalAuthenticationTemplate` コンポーネント

`MsalAuthenticationTemplate` コンポーネントは、ユーザーが認証されている場合はその子要素をレンダリングし、そうでない場合はユーザーをサインインさせようとします。 使用する対話の種類 (リダイレクトまたはポップアップ) と、必要に応じて、ログイン API に渡す [要求オブジェクト](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/request-response-object.md) 、認証の進行中に表示するコンポーネント、またはエラーが発生した場合に表示するコンポーネントを指定するだけです。

この実際の例は、 ページのいずれかの`/profile`で確認できます。

```javascript
import React from "react";
import { MsalAuthenticationTemplate } from "@azure/msal-react";
import { InteractionType } from "@azure/msal-browser";

function ErrorComponent({error}) {
    return <p>An Error Occurred: {error}</p>;
}

function LoadingComponent() {
    return <p>Authentication in progress...</p>;
}

export function Example() {
    const authRequest = {
        scopes: ["openid", "profile"]
    };

    return (
        // authenticationRequest, errorComponent and loadingComponent props are optional
        <MsalAuthenticationTemplate 
            interactionType={InteractionType.Popup} 
            authenticationRequest={authRequest} 
            errorComponent={ErrorComponent} 
            loadingComponent={LoadingComponent}
        >
            <p>At least one account is signed in!</p>
        </MsalAuthenticationTemplate>
      )
};
```

#### `useMsalAuthentication` フック

`useMsalAuthentication` フックは、まずユーザーがサインインしているかどうかを確認してから、サインインしたユーザーがいない場合にユーザーのサインインを試みます。 使用する対話の種類 (リダイレクトまたはポップアップ) を指定する必要があります。 ログイン操作の結果、発生したエラー、再試行する必要がある場合に使用できるログイン関数が返されます。

このフックの詳細については、 [hooks ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/hooks#usemsalauthentication-hook)を参照してください。

```javascript
import React from 'react';
import { useMsalAuthentication } from "@azure/msal-react";
import { InteractionType } from '@azure/msal-browser';

export function App() {
    const {login, result, error} = useMsalAuthentication(InteractionType.Popup);

    return (
        <React.Fragment>
            <p>Anyone can see this paragraph.</p>
            <AuthenticatedTemplate>
                <p>At least one account is signed in!</p>
            </AuthenticatedTemplate>
            <UnauthenticatedTemplate>
                <p>No users are signed in!</p>
            </UnauthenticatedTemplate>
        </React.Fragment>
    );
}
```

### によって提供されるログイン API を使用してユーザーにサインインする `@azure/msal-browser`

サインインを呼び出すもう 1 つの方法は、コンテキスト内の`@azure/msal-browser` インスタンスから直接`PublicClientApplication` API を使用することです。 コンテキストからインスタンスにアクセスする方法は 3 つあります。

#### `useMsal` フック

`PublicClientApplication` インスタンスを返すフック、現在サインインしているすべてのアカウントの配列、および現在の msal の動作を示す`inProgress`値。

このフックの詳細については、 [hooks ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/hooks#usemsal-hook)を参照してください。

```javascript
import React from 'react';
import { useMsal } from "@azure/msal-react";

export function App() {
    const { instance, accounts, inProgress } = useMsal();

    if (accounts.length > 0) {
        return <span>There are currently {accounts.length} users signed in!</span>
    } else if (inProgress === "login") {
        return <span>Login is currently in progress!</span>
    } else {
        return (
            <>
                <span>There are currently no users signed in!</span>
                <button onClick={() => instance.loginPopup()}>Login</button>
            </>
        );
    }
}
```

#### 生のコンテキストの利用

クラス コンポーネントを使用していて、フックを使用できない場合は、 `MsalContext`を介して生の msal コンテキストを使用できます。 クラス コンポーネントでの `@azure/msal-react` の使用の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/class-components)。

```javascript
import React from "react";
import { MsalContext } from "@azure/msal-react";

class App extends React.Component {
    static contextType = MsalContext;

    render() {
        if (this.context.accounts.length > 0) {
            return <span>There are currently {this.context.accounts.length} users signed in!</span>
        } else if (this.context.inProgress === "login") {
            return <span>Login is currently in progress!</span>
        } else {
            return (
                <>
                    <span>There are currently no users signed in!</span>
                    <button onClick={() => this.context.instance.loginPopup()}>Login</button>
                </>
            );
        }
    }
}
```

#### コンポーネントを `withMsal` 上位コンポーネントでラップする

クラス コンポーネントと関数コンポーネントの両方で MSAL コンテキストを使用するもう 1 つの方法は、コンポーネントを `withMsal` HOC でラップし、コンポーネントのプロパティにコンテキストを挿入することです。

```javascript
import React from "react";
import { withMsal } from "@azure/msal-react";

class LoginButton extends React.Component {
    render() {
        const isAuthenticated = this.props.msalContext.accounts.length > 0;
        const msalInstance = this.props.msalContext.instance;
        if (isAuthenticated) {
            return <button onClick={() => msalInstance.logout()}>Logout</button>    
        } else {
            return <button onClick={() => msalInstance.loginPopup()}>Login</button>
        }
    }
}

export default YourWrappedComponent = withMsal(LoginButton);
```

### アクセス トークンの取得

API にアクセスするためにアクセス トークンが必要になるたびに、`acquireTokenSilent` オブジェクトで`PublicClientApplication` API を呼び出することをお勧めします。 これは、前のセクションで説明した方法と同様に実行できます。

```javascript
import React, { useState, useEffect } from "react"
import { useMsal, useAccount } from "@azure/msal-react";

export function App() {
    const { instance, accounts, inProgress } = useMsal();
    const account = useAccount(accounts[0] || {});
    const [apiData, setApiData] = useState(null);

    useEffect(() => {
        if (account) {
            instance.acquireTokenSilent({
                scopes: ["User.Read"],
                account: account
            }).then((response) => {
                if(response) {
                    callMsGraph(response.accessToken).then((result) => setApiData(result));
                }
            });
        }
    }, [account, instance]);

    if (accounts.length > 0) {
        return (
            <>
                <span>There are currently {accounts.length} users signed in!</span>
                {apiData && (<span>Data retreived from API: {JSON.stringify(apiData)}</span>)}
            </>
        );
    } else if (inProgress === "login") {
        return <span>Login is currently in progress!</span>
    } else {
        return <span>There are currently no users signed in!</span>
    }
}
```

#### React コンポーネントの外部でアクセス トークンを取得する

React コンポーネントの外部でアクセス トークンが必要な場合は、`acquireTokenSilent`で`PublicClientApplication`関数を直接呼び出すことができます。 コンテキスト内のコンポーネントが正しく更新されない可能性があるため、 `MsalProvider` によって提供される react コンテキストの外部でユーザーの認証状態 (ログイン、ログアウト) を変更する関数を呼び出すことはお勧めしません。

トークンを取得する前に、ユーザーがサインインする必要があることに注意してください。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

// This should be the same instance you pass to MsalProvider
const msalInstance = new PublicClientApplication(config);

const acquireAccessToken = async (msalInstance) => {
    const activeAccount = msalInstance.getActiveAccount(); // This will only return a non-null value if you have logic somewhere else that calls the setActiveAccount API
    const accounts = msalInstance.getAllAccounts();

    if (!activeAccount && accounts.length === 0) {
        /*
        * User is not signed in. Throw error or wait for user to login.
        * Do not attempt to log a user in outside of the context of MsalProvider
        */   
    }
    const request = {
        scopes: ["User.Read"],
        account: activeAccount || accounts[0]
    };

    const authResult = await msalInstance.acquireTokenSilent(request);

    return authResult.accessToken
};
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/hooks"} -->
## MSAL React のフック - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/hooks
- Service: msal / msal-react
- Article date: 2025-05-21
- Summary: MSAL React フックを使用して認証状態を管理し、認証と承認フローを実行する方法について説明します。

MSAL React のフックは、機能コンポーネント内で MSAL 機能と React 状態およびライフサイクル メソッドを使用できる関数です。 主なフックは、 `useAccount`、 `useIsAuthenticated`、 `useMsal`、および `useMsalAuthentication`です。 この記事では、これらの各フックの使用方法について説明します。

### `useAccount` フック

`useAccount` フックは、`accountIdentifier` パラメーターを受け取り、サインインしている場合はそのアカウントの`AccountInfo` オブジェクトを返し、サインインしていない場合は`null`します。 アカウント識別子が指定されていない場合、現在 [のアクティブなアカウント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/accounts#active-account-apis) が返されます。 `AccountInfo` の`@azure/msal-browser` ドキュメントで返される オブジェクトの詳細を確認できます。

```javascript
const accountIdentifier = {
    localAccountId: "example-local-account-identifier",
    homeAccountId: "example-home-account-identifier"
    username: "example-username" // We do not recommend relying only on username
}

const accountInfo = useAccount(accountIdentifier);
```

### `useIsAuthenticated` フック

`useIsAuthenticated` フックは、アカウントがサインインしているかどうかを示すブール値を返します。 必要に応じて、特定のアカウントがサインインしているかどうかを知る必要がある場合に指定できる `accountIdentifier` オブジェクトを受け入れます。

#### アカウントが現在サインインしているかどうかを確認する

次のスニペットでは、`useIsAuthenticated` パッケージの`@azure/msal-react` フックを使用します。 その後、コンポーネントは、ユーザーがサインインしているかどうかに基づいて、条件付きでメッセージをレンダリングします。

```javascript
import React from 'react';
import { useIsAuthenticated } from "@azure/msal-react";

export function App() {
    const isAuthenticated = useIsAuthenticated();

    return (
        <React.Fragment>
            <p>Anyone can see this paragraph.</p>
            {isAuthenticated && (
                <p>At least one account is signed in!</p>
            )}
            {!isAuthenticated && (
                <p>No users are signed in!</p>
            )}
        </React.Fragment>
    );
}
```

#### 特定のユーザーがサインインしているかどうかを判断する

次のスニペットでは、`useIsAuthenticated` パッケージの`@azure/msal-react` フックを使用して、特定のユーザーがサインインしているかどうかを判断します。

```javascript
import React from 'react';
import { useIsAuthenticated } from "@azure/msal-react";

export function App() {
    const accountIdentifiers = {
        localAccountId: "example-local-account-identifier",
        homeAccountId: "example-home-account-identifier",
        username: "example-username"
    }

    const isAuthenticated = useIsAuthenticated(accountIdentifiers);

    return (
        <React.Fragment>
            <p>Anyone can see this paragraph.</p>
            {isAuthenticated && (
                <p>User with specified localAccountId is signed in!</p>
            )}
            {!isAuthenticated && (
                <p>User with specified localAccountId is not signed in!</p>
            )}
        </React.Fragment>
    );
}
```

### `useMsal` フック

`useMsal` フックはコンテキストを返します。 これは、 `PublicClientApplication` インスタンスにアクセスする必要がある場合、現在サインインしているアカウントの一覧、またはログインまたはその他の操作が現在進行中かどうかを知る必要がある場合に使用できます。

注: `accounts`によって返される`useMsal`値は、アカウントが追加または削除された場合にのみ更新され、要求が更新された場合は更新されません。 現在のユーザーの更新された要求にアクセスする必要がある場合は、代わりに `useAccount` フックまたは呼び出し `acquireTokenSilent` を使用します。

```javascript
import { useState, useEffect } from "react";
import { useMsal } from "@azure/msal-react";
import { InteractionStatus } from "@azure/msal-browser";

const { instance, accounts, inProgress } = useMsal();
const [loading, setLoading] = useState(false);
const [apiData, setApiData] = useState(null);

useEffect(() => {
    if (!loading && inProgress === InteractionStatus.None && accounts.length > 0) {
        if (apiData) {
            // Skip data refresh if already set - adjust logic for your specific use case
            return;
        }

        const tokenRequest = {
            account: accounts[0], // This is an example - Select account based on your app's requirements
            scopes: ["User.Read"]
        }

        // Acquire an access token
        instance.acquireTokenSilent(tokenRequest).then((response) => {
            // Call your API with the access token and return the data you need to save in state
            callApi(response.accessToken).then((data) => {
                setApiData(data);
                setLoading(false);
            });
        }).catch(async (e) => {
            // Catch interaction_required errors and call interactive method to resolve
            if (e instanceof InteractionRequiredAuthError) {
                await instance.acquireTokenRedirect(tokenRequest);
            }

            throw e;
        });
    }
}, [inProgress, accounts, instance, loading, apiData]);

if (loading || inProgress === InteractionStatus.Login) {
    // Render loading component
} else if (apiData) {
    // Render content that depends on data from your API
}
```

### `useMsalAuthentication` フック

`useMsalAuthentication` フックは、ユーザーがまだサインインしていない場合はログインを開始し、それ以外の場合はトークンの取得を試みます。

#### 入力パラメーター

`useMsalAuthentication` フックには、いくつかの異なる入力パラメーターを指定できます。

- [interactionType](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/interactiontype) - (None、Popup、Redirect、または Silent) は、対話が必要な場合にトークンまたはログインを取得する方法を指定します (サイレント オプションには、以下で説明する追加の考慮事項があることに注意してください)。
- [request オブジェクト](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/request-response-object) - (省略可能) ログインまたはトークン取得呼び出しで使用される追加のパラメーターを指定します
- [accountIdentifiers](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react/accountidentifiers) - オブジェクトは、ログインまたはトークンを取得する必要があるユーザーをフックに通知するために使用されます。

#### リターン プロパティ

- [result](https://azuread.github.io/microsoft-authentication-library-for-js/ref/types/_azure_msal_browser.AuthenticationResult.html) - 最後に成功したログインまたはトークンの取得の結果。 このフックは、1 回だけ自動的にログインまたはトークンを取得しようとします。 必要に応じ、 `login` または `acquireToken` 関数を呼び出してこの値を更新するのは、アプリケーションの責任です。
- [error](https://azuread.github.io/microsoft-authentication-library-for-js/ref/classes/_azure_msal_browser.AuthError.html) - ログインまたはトークンの取得中にエラーが発生した場合、このプロパティにはエラーに関する情報が含まれます。 このフックによって返される `login` または `acquireToken` 関数を使用して再試行できます。 `error` プロパティは、次回成功したログインまたはトークンの取得時にクリアされます。
- `login` - 失敗したログインを再試行するために使用できる関数。 `result`プロパティと`error`プロパティが更新されます。
- `acquireToken` - 保護された API を呼び出す前に新しいアクセス トークンを取得するために使用できる関数。 `result`プロパティと`error`プロパティが更新されます。

"サイレント" 対話の種類を渡すと、非表示の iframe を開き、Microsoft Entra IDで既存のセッションを再利用しようとする`ssoSilent`が呼び出されます。 これは、Safari などのサード パーティの Cookie をブロックするブラウザーでは機能しません。 さらに、"Silent" 型を使用する場合は、要求オブジェクトが必要です。 ユーザーのサインイン情報が既にある場合は、 `loginHint` または省略可能なパラメーター `sid` 渡して、特定のアカウントにサインインできます。 注: ユーザーのセッションに関する情報を提供せずにを使用する場合は、`ssoSilent`があります。

#### `ssoSilent` 例

サイレントを使用する場合は、エラーをキャッチし、フォールバックとして対話型ログインを試みる必要があります。

```javascript
import React, { useEffect } from 'react';

import { AuthenticatedTemplate, UnauthenticatedTemplate, useMsal, useMsalAuthentication } from "@azure/msal-react";
import { InteractionType, InteractionRequiredAuthError } from '@azure/msal-browser';

function App() {
    const request = {
        loginHint: "name@example.com",
        scopes: ["User.Read"]
    }
    const { login, result, error } = useMsalAuthentication(InteractionType.Silent, request);

    useEffect(() => {
        if (error instanceof InteractionRequiredAuthError) {
            login(InteractionType.Popup, request);
        }
    }, [error]);

    const { accounts } = useMsal();

    return (
        <React.Fragment>
            <p>Anyone can see this paragraph.</p>
            <AuthenticatedTemplate>
                <p>Signed in as: {accounts[0]?.username}</p>
            </AuthenticatedTemplate>
            <UnauthenticatedTemplate>
                <p>No users are signed in!</p>
            </UnauthenticatedTemplate>
        </React.Fragment>
    );
}

export default App;
```

#### 特定のユーザーの例

特定のユーザーがサインインしていることを確認する場合は、 `accountIdentifiers` オブジェクトを指定します。

```javascript
import React from 'react';
import { useMsalAuthentication } from "@azure/msal-react";
import { InteractionType } from '@azure/msal-browser';

export function App() {
    const accountIdentifiers = {
        username: "example-username"
    }
    const request = {
        loginHint: "example-username",
        scopes: ["User.Read"]
    }
    const { login, result, error } = useMsalAuthentication(InteractionType.Popup, request, accountIdentifiers);

    return (
        <React.Fragment>
            <p>Anyone can see this paragraph.</p>
            <AuthenticatedTemplate username="example-username">
                <p>Example user is signed in!</p>
            </AuthenticatedTemplate>
            <UnauthenticatedTemplate username="example-username">
                <p>Example user is not signed in!</p>
            </UnauthenticatedTemplate>
        </React.Fragment>
    );
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/migration-guide"} -->
## MSAL v1 から MSAL React および MSAL Browser への移行ガイド - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/migration-guide
- Service: msal / msal-react
- Article date: 2025-05-21
- Summary: msal.js v1 から MSAL React および MSAL Browser に移行する方法について説明します。 このガイドでは、アプリの登録、インストール、初期化、コンポーネントの保護、アクセス トークンの取得、ID トークンの取得、redux ストア統合の更新、イベントへの対応について説明します。

この記事では、MSAL v1 から `@azure/msal-react` および `@azure/msal-browser` への移行の概要について説明します。 PKCE と条件付きアクセスを使用した承認コード フローを使用して、パフォーマンスを向上させ、セキュリティを向上させるために移行することをお勧めします。 さらに、シングル ページ アプリケーションのサポートが向上しています。

### Prerequisites

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成する](https://azure.microsoft.com/free/)
- Microsoft Entra テナントに登録されている既存のアプリケーション。

### アプリの登録の更新

`@azure/msal-react` ライブラリは、PKCE を使用して`@azure/msal-browser`を実装するのラッパーです。 これは、 [暗黙的フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)を実装する MSAL v1 ライブラリからの重要な更新です。

新しいアプリの登録を作成するか、既存のアプリを更新して、新しい `redirectUri` の種類 "SPA" を使用する必要があります。 詳細については、「 [シングルページ アプリケーション: アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-registration#redirect-uri-msaljs-20-with-auth-code-flow) 」を参照してください。

### `@azure/msal-react`のインストールと`@azure/msal-browser`

`@azure/msal-react`とそのピア依存関係`@azure/msal-browser`の両方を npm からインストールできます。 古い MSAL パッケージをアンインストールすることが重要です。 ターミナルを開き、次のコマンドを実行します。

```console
npm uninstall msal
npm install @azure/msal-react @azure/msal-browser
```

### `react-aad-msal` のアップグレード

アプリで現在認証[に React Microsoft Entra MSAL](https://www.npmjs.com/package/react-aad-msal) を使用していて、このセクション`@azure/msal-react`に移行しようとしている場合は、2 つのライブラリの違いと、行う必要がある変更の一部について説明します。 React Microsoft Entra MSAL はサード パーティ製のライブラリであり、MSAL React は一から構築されており、MSAL React でカバーされていない、またはサポートされていないエッジ ケースがある場合があります。

`react-aad-msal`ではサポートされていない`@azure/msal-react`でサポートされる機能を次に示します。

- 保護されたコンポーネントをレンダリングする前に IdToken の有効期限を確認する & 期限切れの IdToken の自動更新
- Redux ストアを標準でサポート（代替案は以下）

`react-aad-msal`では可能でも、`@azure/msal-react`ではできなくなったその他のケースについては、[microsoft-authentication-library-for-js](https://github.com/AzureAD/microsoft-authentication-library-for-js/issues/new/choose) の GitHub リポジトリで Issue を作成してください。

#### 初期化

`react-aad-msal`では、後で`MsalAuthProvider` コンポーネントに渡される`AzureAD` オブジェクトを作成して、MSAL インスタンスを初期化します。

```javascript
import { MsalAuthProvider } from "react-aad-msal";

const authProvider = new MsalAuthProvider(config, authenticationParameters, options);
```

`@azure/msal-react`では、`PublicClientApplication`からエクスポートされた`@azure/msal-browser`を使用して MSAL インスタンスを初期化し、`MsalProvider`からエクスポートされた`@azure/msal-react` コンポーネントに渡します。 構成オプションは、 `msal` と `@azure/msal-browser`の間でほぼ似ていますが、最新の構成オプションについては [構成の種類](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/configuration) を参照できます。

`authenticationParameters`で使用される`options`パラメーターと`react-aad-msal` パラメーターは`@azure/msal-react`では使用されませんが、個々のコンポーネントでも同様の機能を実現できます。 これについては、このドキュメントの後半で説明します。

`@azure/msal-react` では [React Context API](https://reactjs.org/docs/context.html) を使用して、コンポーネントツリー全体で `PublicClientApplication` と認証状態を利用できるようにします。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";
import { MsalProvider } from "@azure/msal-react";

const pca = new PublicClientApplication(config);

function App() {
    return (
        <MsalProvider instance={pca}>
            <YourAppComponents />
        </MsalProvider>
    );
}
```

`MsalProvider` コンポーネントに関する一般的な注意事項:

- `@azure/msal-react`によって公開される認証状態またはフック/コンポーネントへのアクセスを必要とするすべてのコンポーネントは、コンポーネント ツリーの上位に`MsalProvider`する必要があるため、`MsalProvider`可能な限りルートの近くにレンダリングすることをお勧めします。
- アプリでは、特定のページに 1 つ以上の `MsalProvider` コンポーネントをレンダリングしないでください。
- 再レンダリングの可能性があるため、コンポーネント内で `PublicClientApplication` を初期化することはお勧めしません

#### コンポーネントの保護

`react-aad-msal` コンポーネントは、`AzureAD` コンポーネントまたは `withAuthentication` HOC を使用して保護されます。これは内部で `AzureAD` を使用してコンポーネントをラップします。 `AzureAD` コンポーネントは、ユーザーが認証された場合にのみ子コンポーネントをレンダリングし、ユーザーが認証されていない場合は必要に応じてログインを開始します。 ログインに使用するオプション (スコープ、ポップアップやリダイレクトのどちらを使用するかなど) は、 `authProvider` prop を作成するときに先に指定します。

```javascript
import { MsalAuthProvider } from "react-aad-msal";

const authProvider = new MsalAuthProvider(config, authenticationParameters, options);

function App() {
    return (
        <AzureAD provider={authProvider} forceLogin={true}>
            <span>Only authenticated users can see me.</span>
        </AzureAD>
    );
}
```

`@azure/msal-react`一方、開発者は、誰に表示するかをより詳細に制御できます。

- `AuthenticatedTemplate` コンポーネントは、ユーザーが認証された場合に子をレンダリングします
- `UnauthenticatedTemplate` コンポーネントは、ユーザーが認証されていない場合に子をレンダリングします
- `MsalAuthenticationTemplate` コンポーネントは、ユーザーが認証されていない場合は自動的にログインを開始し、ユーザーが認証されると子をレンダリングします。

```javascript
import { PublicClientApplication, InteractionType } from "@azure/msal-browser";
import { MsalProvider, AuthenticatedTemplate, UnauthenticatedTemplate, MsalAuthenticationTemplate } from "@azure/msal-react";

const pca = new PublicClientApplication(config);

function App() {
    return (
        <MsalProvider instance={pca}>
            <AuthenticatedTemplate>
                <span>Only authenticated users can see me.</span>
            </AuthenticatedTemplate>
            <UnauthenticatedTemplate>
                <span>Only unauthenticated users can see me.</span>
            </UnauthenticatedTemplate>
            <MsalAuthenticationTemplate interactionType={InteractionType.Popup} authenticationRequest={request}>
                <span>Only authenticated users can see me. Unauthenticated users will get a popup asking them to login first.</span>
            </MsalAuthenticationTemplate>
        </MsalProvider>
    );
}
```

さらに、フックベースのアプローチを採用したい場合は、`@azure/msal-react` には同様の結果を実現するために使用できるフックがいくつか用意されています。 これらは基本的な例にすぎません。詳細については、 [MSAL React フック](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/hooks) を参照してください。

```javascript
import { PublicClientApplication, InteractionType } from "@azure/msal-browser";
import { MsalProvider, useIsAuthenticated, useMsalAuthentication } from "@azure/msal-react";

const pca = new PublicClientApplication(config);

function App() {
    return (
        <MsalProvider instance={pca}>
            <ExampleComponent />
        </MsalProvider>
    );
}

function ExampleComponent() {
    const isAuthenticated = useIsAuthenticated();
    const { error } = useMsalAuthentication(InteractionType.Popup, request); // Will initiate a popup login if user is unauthenticated

    if (isAuthenticated) {
        return <span>Only authenticated users can see me.</span>
    } else if (error) {
        return <span>An error occurred during login!</span>
    } else {
        return <span>Only unauthenticated users can see me.</span>
    }
}
```

#### アクセス トークンの取得

`react-aad-msal` は、API を呼び出す前にアクセス トークンを取得するために使用できる `getAccessToken` メソッドを公開します。

```javascript
import { MsalAuthProvider } from "react-aad-msal";

const authProvider = new MsalAuthProvider(config, authenticationParameters, options);
const accessToken = authProvider.getAccessToken();
```

`@azure/msal-react`と`@azure/msal-browser`を使用する場合は、`acquireTokenSilent` インスタンスで`PublicClientApplication`を呼び出します。

`MsalProvider`の下に存在するコンポーネントまたはフック内のアクセス トークンを取得する必要がある場合は、`useMsal` フックを使用して、必要なオブジェクトを取得できます。

```javascript
import { useState } from "react";
import { useMsal } from "@azure/msal-react";
import { InteractionRequiredAuthError } from "@azure/msal-browser";

function useAccessToken() {
    const { instance, accounts } = useMsal();
    const [accessToken, setAccessToken] = useState(null);

    if (accounts.length > 0) {
        const request = {
            scopes: ["User.Read"],
            account: accounts[0]
        };
        instance.acquireTokenSilent(request).then(response => {
            setAccessToken(response.accessToken);
        }).catch(error => {
            // acquireTokenSilent can fail for a number of reasons, fallback to interaction
            if (error instanceof InteractionRequiredAuthError) {
                instance.acquireTokenPopup(request).then(response => {
                    setAccessToken(response.accessToken);
                });
            }
        });
    }

    return accessToken;
}
```

`MsalProvider`のコンテキスト外でアクセス トークンを取得する必要がある場合は、`PublicClientApplication` インスタンスを直接使用し、`getAllAccounts()`を呼び出してアカウント オブジェクトを取得できます。

Important

`MsalProvider`のコンテキスト外でのみサイレント トークンの取得を試みます。 `MsalProvider`のコンテキスト外で対話型メソッド (リダイレクトまたはポップアップ) を呼び出さないでください。

次の例は、デモンストレーション目的での `PublicClientApplication` の初期化を示しています。 `PublicClientApplication` は、ページ読み込みごとに 1 回だけ初期化する必要があり、ここで `MsalProvider`に指定したのと同じインスタンスを使用する必要があります。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);
const accounts = pca.getAllAccounts();

async function getAccessToken() {
    if (accounts.length > 0) {
        const request = {
            scopes: ["User.Read"],
            account: accounts[0]
        }
        const accessToken = await pca.acquireTokenSilent(request).then((response) => {
            return response.accessToken;
        }).catch(error => {
            // Do not fallback to interaction when running outside the context of MsalProvider. Interaction should always be done inside context.
            console.log(error);
            return null;
        });

        return accessToken;
    }

    return null;
}
```

#### ID トークンの取得

`react-aad-msal` idToken を取得または更新するために `getIdToken` 関数を公開しました。

```javascript
import { MsalAuthProvider } from "react-aad-msal";

const authProvider = new MsalAuthProvider(config, authenticationParameters, options);
const token = await authProvider.getIdToken();
const idToken = token.idToken.rawIdToken;
```

idToken を取得するために、 `clientId` を唯一のスコープとして要求するパターンにも精通している場合があります。 これは、 `@azure/msal-browser`でサポートされているパターンではなくなりました。

`@azure/msal-react`および`@azure/msal-browser`では、すべてのトークン呼び出しでアクセス トークンと ID トークンの両方が返され、すべてのアクセス トークンの更新でも ID トークンが更新されます。

`MsalProvider`の下に存在するコンポーネントまたはフック内で ID トークンを取得する必要がある場合は、`useMsal` フックを使用して、必要なオブジェクトを取得できます。

```javascript
import { useState } from "react";
import { useMsal } from "@azure/msal-react";

function useIdToken() {
    const { instance, accounts } = useMsal();
    const [idToken, setIdToken] = useState(null);

    if (accounts.length > 0) {
        const request = {
            scopes: ["openid"],
            account: accounts[0]
        };
        instance.acquireTokenSilent(request).then(response => {
            setIdToken(response.idToken);
        }).catch(error => {
            // acquireTokenSilent can fail for a number of reasons, fallback to interaction
            if (error instanceof InteractionRequiredAuthError) {
                instance.acquireTokenPopup(request).then(response => {
                    setIdToken(response.idToken);
                });
            }
        });
    }

    return idToken;
}
```

`MsalProvider`のコンテキスト外で ID トークンを取得する必要がある場合は、`PublicClientApplication` インスタンスを直接使用し、`getAllAccounts()`を呼び出してアカウント オブジェクトを取得できます。

Important

`MsalProvider`のコンテキスト外でのみサイレント トークンの取得を試みます。 `MsalProvider`のコンテキスト外で対話型メソッド (リダイレクトまたはポップアップ) を呼び出さないでください。

次の例は、デモンストレーション目的での `PublicClientApplication` の初期化を示しています。 `PublicClientApplication` は、ページ読み込みごとに 1 回だけ初期化する必要があり、ここで `MsalProvider`に指定したのと同じインスタンスを使用する必要があります。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);
const accounts = pca.getAllAccounts();

async function getIdToken() {
    if (accounts.length > 0) {
        const request = {
            scopes: ["openid"],
            account: accounts[0]
        }
        const idToken = await pca.acquireTokenSilent(request).then((response) => {
            return response.idToken;
        }).catch (error => {
            // Do not fallback to interaction when running outside the context of MsalProvider. Interaction should always be done inside context.
            console.log(error);
            return null;
        });

        return idToken
    }

    return null;
}
```

#### redux ストア統合の更新/イベントへの対応

`react-aad-msal` ログインやログアウトなどのイベントが発生したときにアクションをディスパッチすることで、redux ストアとのすぐに統合できます。 `@azure/msal-react`ではこの機能は提供されませんが、同様の機能は、によって公開される`@azure/msal-browser` を使用して実現できます。

イベントがブロードキャストされるたびに呼び出されるイベント コールバックを登録できます (例: `LOGIN_SUCCESS`)。 コールバック関数は、イベントを検査し、ペイロードを使用して何かを行うことができます。 既存の redux ストアを引き続き使用する場合は、ストアにアクションをディスパッチするイベント コールバックを登録できます。

```javascript
import { PublicClientApplication, EventType } from "@azure/msal-browser";
import { store } from "your-redux-store-implementation";

const msalInstance = new PublicClientApplication(config);

const callbackId = msalInstance.addEventCallback((message: EventMessage) => {
    if (message.eventType === EventType.LOGIN_SUCCESS) {
        store.dispatchAction({type: "AAD_LOGIN_SUCCESS", payload: message.payload});
    }
});
```

ペイロードは、 `msal` v1 と `@azure/msal-browser` で異なる場合があるため、アプリケーションが特定のフィールドまたはオブジェクトの形状に依存している場合は、いくつかの調整が必要になる場合があります。 Typedoc には、 [イベントの種類](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/eventtype) と [ペイロードの種類](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/eventpayload) の最新の一覧が含まれており、 [イベント ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/events)で 2 つの間のマッピングを見つけることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/migration-guide-v4-v5"} -->
## MSAL React v3 から v5 への移行 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/migration-guide-v4-v5
- Service: msal / msal-react
- Article date: 2026-03-15
- Summary: React アプリケーションを MSAL React v3 から v5 に移行する方法 (React 19 の要件、CRA から Vite への移行、InteractionStatus の変更など) について説明します。

Note

MSAL React v4 リリースはありません。 パッケージバージョンは、他の MSAL.js ライブラリとバージョン管理 `msal-react` 合わせるために、v3 から v5 に直接インクリメントされました。 個別の v4 機能セットは存在しません。

ブラウザーのサポートとその他の重要な変更については、 [MSAL Browser v4-v5 移行ガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration) を参照してください。

### 移行パス

- **v3 -&gt; v5**: このガイドに従い、 [MSAL Browser v4-v5 移行ガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration) (特にリダイレクト ブリッジのセットアップ) を適用します。
- **v1/v2 -&gt; v5**: v1 -&gt; v2 および v2 -&gt; v3 の更新プログラムは、ほとんどのアプリに対してのみピア依存関係バージョンの更新でした。 最初に v3 に移動し、次に、このドキュメントの v3 -&gt; v5 ガイダンスとリダイレクト ブリッジのセットアップに従います。

### リダイレクトブリッジの設定（必須）

MSAL Browser v5 には、認証フロー用の専用のリダイレクト ページ/ブリッジが必要です。

[MSAL Browser v4-v5 移行ガイドの COOP セクション](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration#cross-origin-opener-policy-coop-support)を参照してください。

### 以前の React バージョンのサポートが削除されました

MSAL React v5 では、React 19.2.1 以降がサポートされています。 React 16、17、または 18 はサポートされなくなりました。

### React 18 の互換性に関するメモ

Warning

React 18 は寿命を迎えました。これが、MSAL React v5 がサポートを停止した理由です。

アプリがまだ React 18 上にある場合、ピア依存関係の制約により、 `@azure/msal-react@^5` のインストールが失敗する可能性があります。

- 一時的なインストールの回避策: `npm install --legacy-peer-deps`
- これにより、インストールと基本的なフローが一部のアプリで引き続き動作する場合がありますが、React 18 は v5 ではサポートまたは検証されていません
- レンダリング/ライフサイクルのタイミング、StrictMode の相互作用、または将来のパッチ更新プログラムに関する未テストの動作が表示される場合があります

運用ワークロードの場合は、 `@azure/msal-react@^5`に移行する前に、React を 19.2.1 以上にアップグレードします。

### React アプリの作成 (react-scripts) からの移行

React アプリの作成は非推奨となり、 `react-scripts` は React 19 をサポートしていません。 アプリで`react-scripts`を使用している場合は、にアップグレードする前に、別のビルド ツールに移行する`@azure/msal-react@^5`。

**推奨: [Vite](https://vite.dev/) に移行する**

1. `react-scripts`を削除し、Vite + React プラグインをインストールします。

    ```bash
    npm uninstall react-scripts
    npm install --save-dev vite @vitejs/plugin-react
    ```
2. `"type": "module"`に`package.json`を追加します。
3. `public/index.html`を`index.html`としてプロジェクト ルートに移動し、`%PUBLIC_URL%/`参照を`/`に置き換え、エントリ ポイント スクリプト タグを追加します。

    ```html
    <!-- Before (CRA): no script tag needed, CRA injects it -->
    <!-- After (Vite): add before </body> -->
    <script type="module" src="/src/index.jsx"></script>
    ```
4. プロジェクト ルートに `vite.config.js` を作成します。

    ```js
    import { defineConfig } from "vite";
    import react from "@vitejs/plugin-react";
    import { resolve } from "path";
    
    export default defineConfig({
        plugins: [react()],
        server: {
            port: 3000,
        },
        build: {
            rollupOptions: {
                input: {
                    main: resolve(__dirname, "index.html"),
                    redirect: resolve(__dirname, "public/redirect.html"),
                },
            },
        },
    });
    ```
5. `package.json`スクリプトを更新します。

    ```json
    "scripts": {
        "start": "vite",
        "build": "vite build",
        "preview": "vite preview"
    }
    ```
6. `process.env.REACT_APP_*`参照を`import.meta.env.VITE_*`に置き換え、それに応じて環境変数の名前を変更します (例: `REACT_APP_CLIENT_ID` → `VITE_CLIENT_ID`)。
7. `SKIP_PREFLIGHT_CHECK` ファイルから CRA 固有の環境変数 (`DISABLE_ESLINT_PLUGIN`、`.env`) を削除します。
8. React をアップグレードし、MSAL をインストールします。

    ```bash
    npm install react@^19.2.1 react-dom@^19.2.1
    npm install @azure/msal-browser@^5 @azure/msal-react@^5
    ```
9. Vite アプリの [リダイレクト ブリッジ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/redirect-bridge#vite) を設定します。

>
> **サンプル:** 完全な Vite ベース [の例については、react-router-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/react-router-sample)、 [typescript-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/typescript-sample)、 [および b2c-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/b2c-sample) を参照してください。

### ログアウトのバグを修正する

MSAL React v5 は、 `useMsalAuthentication` フックと `MsalAuthenticationTemplate`に影響するバグを修正しました。 ログアウトすると、ユーザーに関連付けられているすべての状態がクリアされるようになりました。

### `InteractionStatus` 変更

`InteractionStatus`: `Login`、`SsoSilent`、および`AcquireToken`が`AcquireToken`に統合されるようになりました。

#### 移行の例

アプリが以前に複数の進行中の状態を確認した場合は、統合された `AcquireToken` の状態を簡略化します。

```ts
import { InteractionStatus } from "@azure/msal-browser";

// Before (v3-style checks)
const tokenInteractionInProgress =inProgress === InteractionStatus.Login ||inProgress === InteractionStatus.SsoSilent ||inProgress === InteractionStatus.AcquireToken;

// After (v5)
const tokenInteractionInProgress =inProgress === InteractionStatus.AcquireToken;

if (!tokenInteractionInProgress) {// safe to initiate a new auth/token request
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/react/performance"} -->
## MSAL React のパフォーマンス - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/performance
- Service: msal / msal-react
- Article date: 2025-05-21
- Summary: クライアント側ナビゲーションにルーターのナビゲーション機能を使用するように @azure/msal-react を構成する方法について説明します

### クライアント側ナビゲーションにルーターのナビゲート機能を使用するように `@azure/msal-react` を構成する方法

既定では、MSAL.js アプリケーション内の 1 つのページから別のページに移動する必要がある場合は、 `window.location`再割り当てされ、フレーム全体がもう一方のページにリダイレクトされ、アプリケーションが再レンダリングされます。 ルーターを使用している場合は、多くのルーターが "クライアント側" ナビゲーションを実行し、再レンダリングする必要があるページの部分のみを再レンダリングするために使用できるメソッドを提供しているため、これは望ましくない可能性があります。

現在、MSAL.js がアプリケーション内のあるページから別のページに移動するシナリオがあります。 アプリケーションで次 **のすべてを** 実行している場合は、引き続きお読みください。

- アプリケーションがポップアップ フローではなくリダイレクト フローを使用してログインしている
- `PublicClientApplication` が `auth.navigateToLoginRequestUrl: true` で構成されている (既定)
- アプリケーションには、共有の`redirectUri`を使用して`loginRedirect`/`acquireTokenRedirect`を呼び出す可能性があるページがあります。つまり、`http://localhost` を redirectUri として使用して、`http://localhost/protected` から `loginRedirect` を呼び出します。

アプリケーションで上記のすべての処理を行っている場合は、`INavigationClient`の独自の実装を作成し、`setNavigationClient`をレンダリングする前に`PublicClientApplication`インスタンスで`MsalProvider`を呼び出すことによって、Msal が使用するメソッドをオーバーライドできます。

**注:**`MsalProvider` コンポーネントの上に`Route`レンダリングし、`navigateInternal`関数が`false`を返すようにすることをお勧めします。 `navigateInternal`が false を返すと、トークンはナビゲーションの直後に処理されます。 ナビゲーションの結果として `MsalProvider` が再レンダリングされた場合、 `navigateInternal` 関数は `true` を返して、トークンが再レンダリングの一部として処理されるようにする必要があります。

### 実装例

各ルーターには、クライアント側のナビゲーションを実行するための独自の方法があり、メソッドを公開する方法によっては、この機能をサポートするためにアプリケーションをリファクタリングする必要がある場合があります。 次の例では、 `react-router-dom`にこれを実装する方法を示します。 [react-router-dom](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/react-router-sample)、 [Next.js](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/nextjs-sample)、[Gatsby](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/gatsby-sample) 用にこれを実装する完全なサンプル アプリを見つけることができます。

上記のサンプルでは、 `react-router-dom` v6 を使用します。 代わりに `react-router-dom` v5 を使用する場合は、 [react-router-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-react-samples/react-router-sample/src/App.js) を参照してください。

#### INavigationClient の実装

```javascript
import { NavigationClient } from "@azure/msal-browser";

/**
 * Extending the default NavigationClient allows you to overwrite just navigateInternal while continuing to use the default navigateExternal function
 * If you would like to overwrite both you can implement INavigationClient directly instead
 */
class CustomNavigationClient extends NavigationClient{
    constructor(navigate) {
        super();
        this.navigate = navigate // Passed in from useNavigate hook provided by react-router-dom;
    }
    
    // This function will be called anytime msal needs to navigate from one page in your application to another
    async navigateInternal(url, options) {
        // url will be absolute, you will need to parse out the relative path to provide to the history API
        const relativePath = url.replace(window.location.origin, '');
        if (options.noHistory) {
            this.navigate(relativePath, {replace: true});
        } else {
            this.navigate(relativePath);
        }

        return false;
    }
}
```

#### カスタム NavigationClient を `@azure/msal-browser` に提供する

```javascript
import { MsalProvider } from "@azure/msal-react";
import { BrowserRouter as Router , Routes, Route, useNavigate } from "react-router-dom";

function App({ msalInstance }) {
    // It's important that the Router component is above the Example component because you'll need to use the useHistory hook before rendering MsalProvider
    return (
        <Router>
            <Example msalInstance={msalInstance}/>
        </Router>
    );
};

function Example({ msalInstance }) {
    const navigate = useNavigate();
    const navigationClient = new CustomNavigationClient(navigate);
    msalInstance.setNavigationClient(navigationClient);

    return (
        <MsalProvider instance={msalInstance}>
            <Routes>
                <Route path="/protected" element={<ProtectedRoute />} />
                <Route path="/" element={<Home />} />
            </Routes>
        </MsalProvider>
    )
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/msal-acquire-cache-tokens"} -->
## Microsoft Authentication Library (MSAL) を使用してトークンを取得しキャッシュする

- Source: https://learn.microsoft.com/ja-jp/entra/msal/msal-acquire-cache-tokens
- Service: msal
- Article date: 2024-02-27
- Summary: MSAL を使用したトークンの取得とキャッシュについて説明します。

[アクセス トークンを](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/access-tokens) 使用すると、クライアントは Azure によって保護された Web API を安全に呼び出すことができます。 Microsoft Authentication Library (MSAL) を使用してトークンを取得するには、いくつかの方法があります。 Web ブラウザーを使用したユーザー操作が必要なものもあれば、ユーザーの操作を必要としないものもあります。 一般に、トークンを取得するために使用される方法は、アプリケーションがパブリック クライアント アプリケーション (デスクトップ アプリまたはモバイル アプリ) か、機密クライアント アプリケーション (Web アプリ、Web API、またはデーモン アプリケーション) かによって異なります。

MSAL では、トークンは取得された後でキャッシュされます。 アプリケーション コードでは、他の手段でトークンの取得を試行する前に、まず、キャッシュからのトークンの自動的な取得を試みる必要があります。

また、トークン キャッシュをクリアすることもできます。これは、キャッシュからアカウントを削除することによって行います。 ただし、これによってブラウザーにあるセッション Cookie が削除されることはありません。

### トークンを取得するときのスコープ

[スコープは、](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-permissions-and-consent) クライアント アプリケーションがアクセスを要求できる Web API によって公開されるアクセス許可です。 クライアント アプリケーションでは、Web API にアクセスするためのトークンを取得するために認証要求を行うとき、これらのスコープに対するユーザーの同意を要求します。 MSAL を使用すると、Microsoft ID プラットフォーム API にアクセスするためのトークンを取得できます。 v2.0 プロトコルでは、要求内のリソースではなくスコープが使用されます。 受け付けるトークンのバージョンに関する Web API の構成に基づいて、v2.0 エンドポイントから MSAL にアクセス トークンが返されます。

いくつかの MSAL のトークン取得方法には、`scopes` パラメーターが必要です。 `scopes` パラメーターは、要求された必要とするアクセス許可とリソースを宣言する文字列のリストです。 よく知られたスコープとしては、[Microsoft Graph アクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-reference)があります。

MSAL で v1.0 のリソースにアクセスすることもできます。 詳細については、[v1.0 アプリケーションのスコープ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-v1-app-scopes)に関する記事を参照してください。

#### Web API のスコープを要求する

アプリケーションでリソース API に対する特定のアクセス許可を備えたアクセス トークンを要求する必要がある場合、API のアプリ ID URI を含むスコープを、`<app ID URI>/<scope>` 形式で渡します。

さまざまなリソースのスコープ値の例を次に示します。

- Microsoft Graph API:`https://graph.microsoft.com/User.Read`
- カスタム Web API: `api://11111111-1111-1111-1111-111111111111/api.read`

スコープ値の形式は、アクセス トークンを受け取るリソース (API) と、それが受け入れる `aud` 要求の値によって異なります。

Microsoft Graph の場合のみ、`user.read` スコープは `https://graph.microsoft.com/User.Read` にマップされ、両方のスコープ形式を同じ意味で使用できます。

Azure Resource Manager API (`https://management.core.windows.net/`) などの一部の Web API は、アクセス トークンの audience クレーム (`aud`) に末尾のフォワード スラッシュ (`/`) が含まれていることを想定しています。 この場合は、二重スラッシュ (`//`) を含めて、スコープを `https://management.core.windows.net//user_impersonation` として渡します。

その他の API では、スコープ値に*スキームやホストが含まれない*ことが必要になる場合があり、アプリ ID (GUID) とスコープ名のみを想定する場合もあります。次に例を示します。

`11111111-1111-1111-1111-111111111111/api.read`

Tip

ダウンストリーム リソースが制御下にない場合、アクセス トークンをリソースに渡すときに `401` またはその他のエラーが発生した場合は、異なるスコープ値の形式 (たとえば、スキームとホストを含めたり省略したりする) を試すことが必要な場合もあります。

#### 段階的な同意のために動的スコープをリクエストする

アプリケーションによって提供される機能やその要件が変更されると、スコープ パラメーターを使用して、必要に応じて追加のアクセス許可を要求できます。 このような *動的スコープを* 使用すると、ユーザーはスコープに増分同意を提供できます。

たとえば、ユーザーをサインインさせますが、最初はすべてのリソースへのアクセスを拒否します。 その後、トークン取得メソッドで予定表のスコープを要求して、そうすることへのユーザーの同意を取得することにより、ユーザーの予定表を表示する機能を提供できます。 たとえば、`https://graph.microsoft.com/User.Read` と `https://graph.microsoft.com/Calendar.Read` のスコープを要求して行います。

### キャッシュからトークンをサイレントに取得する

MSAL は、1 つのトークン キャッシュ (または、機密クライアント アプリケーションの場合は 2 つのキャッシュ) を保持しており、取得した後のトークンをキャッシュします。 多くの場合、トークンを自動的に取得しようとすると、キャッシュ内のトークンに基づいて、より多くのスコープを備える別のトークンが取得されます。 また、期限切れが近いトークンを更新することもできます (トークン キャッシュには更新トークンも含まれるため)。

#### パブリック クライアント アプリケーションの推奨される呼び出しパターン

アプリケーションのソース コードでは、まず、キャッシュからトークンを自動的に取得することを試みる必要があります。 メソッドの呼び出しで "UI が必要" エラーまたは例外が返される場合、他の手段でトークンの取得を試みます。

ただし、トークンをサイレントに取得しようと**すべきではない**フローが 2 つあります。

- ユーザー トークン キャッシュを使用せず、アプリケーション トークン キャッシュを使用するクライアント資格情報フロー。 この方法では、セキュリティ トークン サービス (STS) に要求を送信する前に、このアプリケーション トークン キャッシュの確認が行われます。
- アプリケーションがユーザーにサインインしてより多くのスコープに同意させることで取得したコードを引き換えるために、Web アプリで承認コードフローを利用します。 (アカウントでなく) コードがパラメーターとして渡されるため、メソッドはコードを引き換える前にキャッシュを参照することができません。そのため、サービスの呼び出しを起動します。

#### 承認コード フローが使用される Web アプリで推奨される呼び出しパターン

OpenID Connect 承認コード フローが使用される Web アプリケーションの場合、コントローラーで推奨されるパターンは次のとおりです。

- カスタマイズされたシリアル化を使用してトークン キャッシュで機密クライアント アプリケーションをインスタンス化します。
- 承認コード フローを使用してトークンを取得します

### トークンの取得

一般に、トークンを取得する方法は、アプリケーションがパブリック クライアントか機密クライアントかによって決まります。

#### パブリック クライアント アプリケーション

デスクトップやモバイルのアプリのようなパブリック クライアント アプリケーションでは、次のようにできます。

- UI またはポップアップ ウィンドウを使用してユーザーをサインインさせ、対話形式でトークンを取得します。
- ドメインまたは Azure に参加している Windows コンピューターでデスクトップ アプリケーションが実行されている場合は、統合 Windows 認証 (IWA/Kerberos) を使用して、サインインしているユーザーのトークンをサイレントで取得します。
- .NET Framework デスクトップ クライアント アプリケーションで、[ユーザー名とパスワード](https://learn.microsoft.com/ja-jp/entra/msal/msal-authentication-flows#usernamepassword-ropc)を使用してトークンを取得します (推奨されません)。 機密クライアント アプリケーションでは、ユーザー名とパスワードを使わないでください。
- Web ブラウザーを持たないデバイスで実行されているアプリケーションのデバイス コード フローを介してトークンを取得します。 ユーザーは URL とコードを提供された後、別のデバイスの Web ブラウザーに移動し、コードを入力してサインインします。 Microsoft Entra ID、トークンをブラウザーレスデバイスに送り返します。

#### 機密クライアント アプリケーション

機密クライアント アプリケーション (Web アプリ、Web API、または Windows サービスなどのデーモン アプリケーション) の場合は、次のようにします。

- クライアント資格情報フローを使用して、ユーザーではなく **アプリケーション自体** のトークンを取得します。 この手法は、同期ツールに対して、または特定のユーザーではなくユーザー一般を処理するツールに対して、使用できます。
- Web API の代理 (OBO) フローを使用して、ユーザーに代わって API を呼び出します。 ユーザー アサーションに基づいてトークンを取得するため、アプリケーションはクライアントの資格情報で識別されます (たとえば、SAML または JWT トークン)。 このフローは、サービス間呼び出しで特定のユーザーのリソースにアクセスする必要があるアプリケーションによって使用されます。
- ユーザーが承認要求 URL を使用してサインインした後、Web アプリの承認コード フローを使用してトークンを取得します。 OpenID Connect アプリケーションでは通常、このメカニズムを使用します。これにより、ユーザーは Open ID Connect を使用してサインインし、ユーザーの代わりに Web API にアクセスできます。

### 認証の結果

クライアントがアクセス トークンを要求すると、Microsoft Entra IDはアクセス トークンに関するメタデータを含む認証結果も返します。 この情報には、アクセス トークンの有効期限や、それが有効なスコープが含まれます。 このデータを使用すると、アプリはアクセス トークン自体を解析しなくても、そのアクセス トークンのインテリジェントなキャッシュを実行できます。 認証結果では以下が公開されます。

- リソースにアクセスするための Web API のアクセス トークン。 この文字列は、通常は Base64 でエンコードされた JWT ですが、クライアントがアクセス トークン内を参照することはありません。 この形式が変わらないことは保証されておらず、リソース用に暗号化できます。 クライアント上のアクセス トークンのコンテンツに応じてコードを記述している人は、エラーとクライアント ロジックの中断を起こす最も一般的な原因の 1 つです。
- ユーザーの ID トークン (JWT)。
- トークンの有効期限。トークンの有効期限が切れる日付と時刻を示します。
- テナント ID には、ユーザーが見つかったテナントが含まれています。 ゲスト ユーザー (Microsoft Entra B2B シナリオ) の場合、テナント ID はゲスト テナントであり、一意のテナントではありません。 トークンがユーザーの名前で提供されると、認証結果にはこのユーザーに関する情報も含まれます。 (アプリケーションの) ユーザーなしでトークンが要求される機密クライアント フローの場合、このユーザー情報は null です。
- トークンが発行された対象のスコープ。
- ユーザーの一意の ID。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/msal-authentication-flows"} -->
## Microsoft Authentication Library (MSAL) での認証フローのサポート

- Source: https://learn.microsoft.com/ja-jp/entra/msal/msal-authentication-flows
- Service: msal
- Article date: 2024-01-12
- Summary: MSAL でサポートされる承認許可と認証フローについて説明します。

Microsoft Authentication Library (MSAL) では、さまざまなアプリケーションの種類とシナリオで使用するために、いくつかの承認付与と関連するトークン フローがサポートされています。

| 認証フロー | Enables | サポートされているアプリケーションの種類 |
| --- | --- | --- |
| 承認コード | ユーザーがサインインし、ユーザーの代わりに Web API にアクセスします。 | \* [デスクトップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-overview) \* [モバイル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-overview) \* [シングルページ アプリ (SPA) (](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-overview) PKCE が必要)  \* [ウェブ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-overview) |
| クライアントの資格情報 | アプリケーション自体の ID を使用した Web API へのアクセス。 通常、サーバー間の通信や、ユーザーの操作を必要としない自動スクリプトに使用されます。 | [Daemon](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-overview) |
| デバイス コード | ユーザーは、スマート テレビやモノのインターネット (IoT) デバイスなど、入力に制約のあるデバイスでユーザーに代わってサインインし、Web API にアクセスします。 コマンド ライン インターフェイス (CLI) アプリケーションでも使用されます。 | [デスクトップ、モバイル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-device-code-flow) |
| 暗黙的な許可 | ユーザーのサインインと、ユーザーの代理による Web API へのアクセス。 *暗黙的な許可フローは推奨されなくなりました。代わりに、認証コードと Proof Key for Code Exchange (PKCE) を使用します。* | \* [シングルページ アプリ (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-overview) \* [ウェブ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-overview) |
| On-behalf-of (OBO) | ユーザーの代理による "アップストリーム" Web API から "ダウンストリーム" Web API へのアクセス。 ユーザーの ID と委任されたアクセス許可は、アップストリーム API からダウンストリーム API に渡されます。 | [ウェブAPI](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-overview) |
| ユーザー名/パスワード (ROPC) | アプリケーションはパスワードを直接処理することによって、ユーザーをサインインさせることができます。 ROPC フローは推奨されません。 | [デスクトップ、モバイル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-username-password) |
| 統合 Windows 認証 (IWA) | ドメインまたは Microsoft Entra ID 参加済みコンピューター上のアプリケーションが、(ユーザーからの UI 操作なしで) トークンをサイレントで取得できるようにします。 | [デスクトップ、モバイル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-integrated-windows-authentication) |

### Tokens

アプリケーションでは、1 つ以上の認証フローを使用できます。 各フローでは、認証、承認、トークンの更新に特定のトークンの種類が使用され、一部では承認コードも使用されます。

| 認証フローまたはアクション | Requires | ID トークン | アクセス トークン | 更新トークン | Authorization code (承認コード) |
| --- | --- | --- | --- | --- | --- |
| [承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) |  | ✅ | ✅ | ✅ | ✅ |
| [クライアントの資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) |  |  | ✅ (アプリのみ) |  |  |
| [デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code) |  | ✅ | ✅ | ✅ |  |
| [暗黙的なフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-overview) |  | ✅ | ✅ |  |  |
| [On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow) | アクセス トークン | ✅ | ✅ | ✅ |  |
| [ユーザー名/パスワード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) (ROPC) | ユーザー名、パスワード | ✅ | ✅ | ✅ |  |
| [ハイブリッド OIDC フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc#protocol-diagram-access-token-acquisition) |  | ✅ |  |  | ✅ |
| [リフレッシュトークンの交換](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token) | 更新トークン | ✅ | ✅ | ✅ |  |

#### 対話型と非対話型の認証

これらのフローのいくつかでは、対話型と非対話型の両方のトークンの取得がサポートされています。

- **対話型** - ユーザーに対して、承認サーバーから入力を求めるプロンプトが出される場合があります。 たとえば、サインインしたり、多要素認証 (MFA) を実行したり、より多くのリソース アクセス許可に同意を付与したりします。
- **非対話型** - ユーザーに入力を求 *められない* 場合があります。 *サイレント トークン取得*とも呼ばれるアプリケーションは、承認サーバーがユーザーに入力を求*められない可能性がある*メソッドを使用してトークンを取得しようとします。

MSAL ベースのアプリケーションでは、最初にトークンをサイレントで取得しようとします。その後、非対話型での試行が失敗した場合にのみ対話型の方法を試みます。 このパターンの詳細については、「[Microsoft Authentication Library (MSAL) を使用してトークンを取得し、キャッシュする](https://learn.microsoft.com/ja-jp/entra/msal/msal-acquire-cache-tokens)」を参照してください。

### Authorization code (承認コード)

[OAuth 2.0 認証コード付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)は、Web アプリ、シングルページ アプリ (SPA)、ネイティブ (モバイルおよびデスクトップ) アプリケーションで使用して、Web API などの保護されたリソースにアクセスできます。

ユーザーが Web アプリケーションにサインインすると、アプリケーションは、Web API を呼び出すアクセス トークンに引き換えることができる承認コードを受け取ります。

[Image: 承認コード フローの図]

上の図では、アプリケーションは次のようになります。

1. アクセス トークンに引き換えた承認コードを要求します。
2. アクセス トークンを使用して、Microsoft Graph などの Web API を呼び出します。

#### 承認コードの制約

- シングルページ アプリケーションでは、承認コード付与フローを使用するときに、[Proof Key for Code Exchange (PKCE)](https://oauth.net/2/pkce/) が必要です。 PKCE は MSAL でサポートされています。
- OAuth 2.0 仕様では、承認コードを使用してアクセス トークンを *1 回*だけ引き換える必要があります。

    同じ承認コードでアクセス トークンを複数回取得しようとすると、Microsoft ID プラットフォームから次のようなエラーが返されます。 一部のライブラリとフレームワークは自動的に承認コードを要求し、そのような場合にコードを手動で要求すると、このエラーが発生することに注意してください。

    `AADSTS70002: Error validating credentials. AADSTS54005: OAuth2 Authorization code was already redeemed, please retry with a new valid code or use an existing refresh token.`

### クライアントの資格情報

[OAuth 2 クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)を使用すると、アプリケーションの ID を使用して Web ホスト型リソースにアクセスできます。 この種類の許可は、通常、ユーザーからの即時の操作なしでバックグラウンドで実行する必要があるサーバー間 (S2S) の対話に使用されます。 これらの種類のアプリケーションは、多くの場合、デーモンまたはサービスと呼ばれます。

クライアント資格情報許可フローでは、Web サービス (Confidential クライアント) が別の Web サービスを呼び出すときに、ユーザーを偽装する代わりに、独自の資格情報を使用して認証することができます。 このシナリオでは、クライアントは通常、中間層の Web サービス、デーモン サービス、または Web サイトです。 高いレベルの保証では、Microsoft ID プラットフォームにより、呼び出し元サービスが、資格情報として (共有シークレットではなく) 証明書を使用することもできます。

#### アプリケーション シークレット

[Image: パスワードを使用した機密クライアントの図]

上の図では、アプリケーションは次のようになります。

1. アプリケーション シークレットまたはパスワード資格情報を使用してトークンを取得します。
2. トークンを使用してリソース要求を行います。

#### Certificates

[Image: 証明書を含む機密クライアントの図]

上の図では、アプリケーションは次のようになります。

1. 証明書の資格情報を使用してトークンを取得します。
2. トークンを使用してリソース要求を行います。

この種類のクライアント資格情報は、次のようにする必要があります。

- Azure AD に登録されています。
- コードで機密クライアント アプリケーション オブジェクトを構築するときに渡されます。

#### クライアント資格情報の制約

機密クライアント フローは、Android、iOS、ユニバーサル Windows プラットフォーム (UWP) などのモバイル プラットフォームでは **サポートされていません** 。 モバイル アプリケーションは、認証シークレットの機密性を保証できないパブリック クライアント アプリケーションと見なされます。

### デバイス コード

[OAuth 2 デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)を使用すると、ユーザーはスマート テレビ、モノのインターネット (IoT) デバイス、プリンターなどの入力に制約のあるデバイスにサインインできます。 Microsoft Entra ID による対話型認証には Web ブラウザーが必要です。 デバイスまたはオペレーティング システムが Web ブラウザーを提供しない場合、デバイス コード フローを使用すると、コンピューターや携帯電話などの別のデバイスを使用して対話形式でサインインできます。

デバイス コード フローを使用すると、アプリケーションでは、これらのデバイスおよびオペレーティング システム用に設計された 2 ステップ プロセスを通じてトークンを取得します。

[Image: デバイス コード フローの図]

前の図で:

1. ユーザー認証が必要になるたびに、アプリがコードを提供し、ユーザーに別のデバイス (インターネットに接続されたスマートフォンなど) を使用して特定の URL (たとえば、`https://microsoft.com/devicelogin`) に移動するよう求めます。 その後、ユーザーはコードの入力を求め、必要に応じて、同意プロンプトや多要素認証などの通常の認証エクスペリエンスを続行します。
2. 認証が成功すると、要求元のアプリケーションは Microsoft ID プラットフォームから必要なトークンを受け取り、それらを使用して必要な Web API 呼び出しを実行します。

#### デバイス コードの制約

- デバイス コード フローは、パブリック クライアント アプリケーションでのみ使用できます。
- MSAL でパブリック クライアント アプリケーションを初期化する場合は、次のいずれかの形式の機関を使用します。
    - テナントベース: `https://login.microsoftonline.com/{tenant}/,``{tenant}`は、テナント ID を表す GUID か、テナントに関連付けられているドメイン名です。
    - 職場または学校のアカウント: `https://login.microsoftonline.com/organizations/`

### Implicit grant (暗黙的な付与)

暗黙的な許可は、クライアント側のシングルページアプリケーション (SPA) において、推奨されるより安全なトークン付与フローとして、[PKCE を使用した承認コードフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-overview)に置き換えられました。 SPA を構築する場合は、代わりに PKCE を使用した承認コード フローを使用してください。

JavaScript で記述されたシングルページ Web アプリ (Angular、Vue.js、React.js などのフレームワークを含む) がサーバーからダウンロードされ、そのコードがブラウザーで直接実行されます。 クライアント側のコードが Web サーバーではなくブラウザーで実行されるため、従来のサーバー側の Web アプリケーションとは異なるセキュリティ特性を持ちます。 承認コード フローに対して Proof Key for Code Exchange (PKCE) が使用可能になる前は、アクセストークンを取得する際の応答性と効率を向上させるために、暗黙的な許可フローが SPA によって使用されていました。

[OAuth 2 の暗黙的な許可フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)を使用すると、アプリはバックエンド サーバーの資格情報交換を実行せずに、Microsoft ID プラットフォームからアクセス トークンを取得できます。 暗黙的な許可フローを使用すると、アプリで、ユーザーのサインイン、セッションの維持、ユーザー エージェント (通常は Web ブラウザー) によってダウンロードおよび実行される JavaScript コード内からの他の Web API のトークンの取得が可能になります。

[Image: 暗黙的な許可のフローの図]

#### 暗黙的な許可の制約

暗黙的な許可フローには、Electron や React Native などのクロスプラットフォーム JavaScript フレームワークを使用するアプリケーション シナリオは含まれていません。 このようなクロスプラットフォーム フレームワークでは、実行されているネイティブ デスクトップ プラットフォームやモバイル プラットフォームとの対話に追加の機能が必要です。

暗黙的フロー モードを介して発行されたトークンは、URL (が`response_mode`または`query`) でブラウザーに返されるため、`fragment`があります。 一部のブラウザーでは、ブラウザーのバー内の URL の長さが制限され、長すぎると失敗します。 そのため、これらの暗黙的なフロー トークンには `groups` または `wids` 要求が含まれていません。

### オンビハーフ・オブ (OBO)

[OAuth 2 の代理認証フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)フローは、アプリケーションがサービスまたは Web API を呼び出すときに使用されます。このフローは、委任されたユーザー ID と要求チェーンを通じて伝達する必要があるアクセス許可を使用して、別のサービスまたは Web API を呼び出す必要があります。 中間層サービスがダウンストリーム サービスに対して認証済み要求を行うには、要求元のユーザーに *代わって* Microsoft ID プラットフォームからのアクセス トークンをセキュリティで保護する必要があります。

[Image: 代行フローの図]

前の図で:

1. アプリケーションは Web API 用のアクセス トークンを取得します。
2. クライアント (Web、デスクトップ、モバイル、またはシングルページ アプリケーション) が保護された Web API を呼び出し、HTTP 要求の認証ヘッダーのベアラー トークンとしてアクセス トークンを追加します。 Web API がユーザーを認証します。
3. クライアントが Web API を呼び出すと、Web API はユーザーに代わって別のトークンを要求します。
4. 保護された Web API は、このトークンを使用して、ユーザーの代わりにダウンストリーム Web API を呼び出します。 Web API は、他のダウンストリーム API のトークンを (ただし、ここでも同じユーザーの代わりとして) 後で要求することもできます。

### ユーザー名/パスワード (ROPC)

Warning

リソース所有者パスワード資格情報 (ROPC) フローは **推奨されなくなりました**。 ROPC には、高度な信頼と資格情報の公開が必要です。 より安全なフローを使用できない場合にのみ ROPC を使用します。 詳細については、「 [パスワードの増大する問題の解決策とは」](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)を参照してください。

[OAuth 2 リソース所有者パスワード資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) (ROPC) の付与により、アプリケーションは自分のパスワードを直接処理してユーザーにサインインできます。 デスクトップ アプリケーションでは、ユーザー名/パスワードのフローを使ってサイレントにトークンを取得できます。 アプリケーションを使用するときに UI は必要ありません。

DevOps などの一部のアプリケーション シナリオでは ROPC が役に立つ場合がありますが、ユーザー サインイン用の対話型 UI を提供するアプリケーションでは避ける必要があります。

[Image: ユーザー名とパスワードのフローの図]

上の図では、アプリケーションは次のようになります。

1. ID プロバイダーにユーザー名とパスワードを送信してトークンを取得します。
2. トークンを使用して Web API を呼び出します。

Windows ドメインに参加しているマシンでトークンをサイレントモードで取得するには、ROPC ではなく [Web アカウント マネージャー (WAM) を使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) することをお勧めします。 その他のシナリオでは、デバイス コード フローを使用してください。

#### ROPC の制約

ROPC フローを使用するアプリケーションには、次の制約が適用されます。

- シングル サインオンは**サポートされません**。
- 多要素認証 (MFA) は **サポートされていません**。
    - このフローを使用する前に、テナント管理者に確認してください。MFA は一般的に使用されている機能です。
- 条件付きアクセスは**サポートされません**。
- ROPC は職場および学校のアカウントに*のみ*有効です。
- ROPC では、個人の Microsoft アカウント (MSA) は**サポートされません**。
- ROPC は、.NET デスクトップおよび .NET アプリケーションで **サポートされています** 。
- ROPC は、ユニバーサル Windows プラットフォーム (UWP) アプリケーションでは**サポートされません**。
- Microsoft Entra 外部 ID の ROPC は、ローカル アカウント *でのみ*サポートされます。
    - MSAL.NET および Microsoft Entra 外部 ID の ROPC の詳細については、「 [リソース所有者パスワード資格情報 (ROPC) with B2C](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/social-identities#resource-owner-password-credentials-ropc-with-b2c)」を参照してください。

### 統合 Windows 認証 (IWA)

Note

統合 Windows 認証は、トークンをサイレントモードで取得するためのより信頼性の高い方法 ( [WAM](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam)) に置き換えられました。 WAM は、現在の Windows ユーザーにサイレント ログインできます。 このワークフローは複雑なセットアップを必要とせず、個人 (Microsoft) アカウントでも機能します。 内部的には、Windows ブローカー (WAM) は、IWA や PRT の利用など、現在の Windows ユーザーのトークンを取得するためのいくつかの戦略を試みます。 これにより、IWA の制限事項の大部分が排除されます。

MSAL では、ドメイン参加済みまたは Microsoft Entra ID に参加している Windows コンピューターで実行されるデスクトップ およびモバイル アプリケーション用の統合 Windows 認証 (IWA) がサポートされています。 IWA を使用すると、これらのアプリケーションは、ユーザーによる UI 操作なしでトークンをサイレントに取得します。

[Image: 統合 Windows 認証の図]

上の図では、アプリケーションは次のようになります。

1. 統合 Windows 認証を使用してトークンを取得します。
2. トークンを使用してリソース要求を行います。

#### IWA の制約

- **互換性**。 統合 Windows 認証 (IWA) は、.NET デスクトップ、.NET、ユニバーサル Windows プラットフォーム (UWP) アプリで有効になっています。 IWA では、ADFS フェデレーション ユーザー *のみが* サポートされます。Active Directory で作成され、Microsoft Entra ID によってサポートされるユーザーです。 Microsoft Entra ID で直接作成され、Active Directory のサポートのないユーザー (マネージド ユーザー) はこの認証フローを使用できません。
- **多要素認証 (MFA)**。 MICROSOFT Entra ID テナントで MFA が有効になっていて、Microsoft Entra ID によって MFA チャレンジが発行されると、IWA 非対話型 (サイレント) 認証が失敗する可能性があります。 IWA が失敗した場合は、前述のように、対話型の認証方式に切り替える必要があります。 Microsoft Entra ID では、AI を使用して、2 要素認証が必要な状況を判別します。 通常、2 要素認証は、ユーザーが別の国/地域からサインインするとき、VPN を使用せずに企業ネットワークに接続する場合、および VPN 経由で接続する場合に必要 *になります* 。 MFA の構成とチャレンジの頻度は開発者が制御できない可能性があるため、アプリケーションでは IWA サイレント トークン取得の失敗を適切に処理する必要があります。
- **機関 URI の制限**。 パブリック クライアント アプリケーションを構築するときに渡される権限は、次のいずれかである必要があります。
    - `https://login.microsoftonline.com/{tenant}/` - この権限は、サインイン対象ユーザーが指定された Microsoft Entra ID テナント内のユーザーに制限されているシングルテナント アプリケーションを示します。 `{tenant}` の値には、GUID 形式のテナント ID、またはテナントに関連付けられているドメイン名を指定できます。
    - `https://login.microsoftonline.com/organizations/` - この機関は、サインイン対象ユーザーが Microsoft Entra ID テナントのユーザーであるマルチテナント アプリケーションを示します。
- **個人用アカウント**。 個人の Microsoft アカウント (MSA) は IWA でサポートされないため、権限の値に `/common` または `/consumers` を含めては**いけません**。
- **同意の要件**。 IWA はサイレント フローであるため、アプリケーションのユーザーはアプリケーションの使用に以前に同意している必要があります。または、テナント管理者は、アプリケーションを使用するためにテナント内のすべてのユーザーに以前に同意している必要があります。 いずれかの要件を満たすには、次のいずれかの操作が完了している必要があります。
    - アプリケーション開発者であるあなたは、Azure portal で **許可** をご自身に選択しました。
    - テナント管理者が Azure portal のアプリ登録の **[API のアクセス許可]** タブにある **[{テナント ドメイン} の管理者の同意を付与/取り消す]** を選択しておきます (「[Web API にアクセスするためのアクセス許可を追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-access-web-apis#add-permissions-to-access-your-web-api)」を参照)。
    - ユーザーがアプリケーションに同意する方法を提供しました。 [Microsoft ID プラットフォームでのアクセス許可と同意の概要を](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。
    - テナント管理者がアプリケーションに同意する方法を提供しました。 [Microsoft ID プラットフォームでのアクセス許可と同意の概要を](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/msal-client-applications"} -->
## パブリックおよび機密のクライアント アプリ (MSAL)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/msal-client-applications
- Service: msal
- Article date: 2024-01-12
- Summary: Microsoft Authentication Library (MSAL) でのパブリック クライアント アプリケーションと機密クライアント アプリケーションについて説明します。

Microsoft 認証ライブラリ (MSAL) は、 **パブリック クライアント** と機密クライアントの 2 種類の **クライアント**を定義します。 その 2 種類のクライアントは、認証サーバーを使用してセキュリティを確保した認証を行い、クライアント資格情報の機密性を維持する能力によって区別されます。

- **機密クライアント アプリケーション**とは、Web アプリ、Web API アプリ、サービス アプリやデーモン アプリなど、サーバー上で実行されるアプリです。 内部はアクセスが困難と見なされるため、アプリケーション シークレットをセキュリティで保護し、ユーザーの目に見えないようにすることができます。 機密クライアントは、構成時のシークレットを保持することができます。 クライアントの各インスタンスに個別の構成 (クライアント ID とクライアント シークレットを含む) があります。 Web アプリは最も一般的な機密クライアントです。 クライアント ID は Web ブラウザーを介して公開されますが、シークレットはバックエンド経由でのみ渡され、直接公開されることはありません。
- **パブリック クライアント アプリケーションは、** コンシューマー デバイス、デスクトップ コンピューター、または Web ブラウザーで実行されるアプリです。 クライアント アプリケーションはユーザーがリバース エンジニアリングまたは検査できるため、アプリケーション シークレットを安全に保持することは信頼されていないため、ユーザーの代わりに Web API にのみアクセスします。 また、これらではパブリック クライアント フローのみがサポートされます。

### クライアントの種類の比較

パブリック クライアント アプリと機密クライアント アプリの共通点および相違点をいくつか以下に示します。

- どちらの種類のアプリもユーザー トークン キャッシュを維持し、トークンをサイレントモードで取得できます (トークンが既にトークン キャッシュに入っている場合)。 機密クライアント アプリには、アプリ自体のためのトークン用のアプリ トークン キャッシュもあります。 さまざまなトークン キャッシュの種類の詳細については、 [トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization#token-cache-types) ガイドを参照してください。
- どちらの種類のアプリもユーザー アカウントを管理し、ユーザー トークン キャッシュからアカウントを取得したり、識別子に基づいてアカウントを取得したり、アカウントを削除したりできます。

MSAL では、アプリケーションの初期化中に、 *アプリケーション ID* または *アプリ ID* とも呼ばれるクライアント ID が使用されます。 アプリがトークンを取得する際にこれが再度渡される必要はありません。 これは、パブリックと機密の両方のクライアント アプリに当てはまります。 機密クライアント アプリのコンストラクターには、クライアント資格情報 (ID プロバイダーと共有するシークレット) も渡されます。シークレット キー (文字列として表される) または証明書を指定できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc"} -->
## iOS および macOS 向け Microsoft Authentication Library

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc
- Service: msal / msal-ios-mac
- Article date: 2024-03-26
- Summary: iOS 用 Microsoft Authentication Libraryと macOS について学習する

iOS および macOS 用の Microsoft Authentication Library (MSAL) は、業界標準の OAuth2 と OpenID Connect を使用してアプリに認証をシームレスに統合するために使用できる認証 SDK です。 これにより、Microsoft ID を使用してユーザーまたはアプリにサインインできます。 これらの ID には、Microsoft Entra IDの職場および学校アカウント、個人用Microsoft アカウント、ソーシャル アカウント、顧客アカウントが含まれます。

iOS および macOS 用の MSAL を使用すると、Microsoft ID プラットフォームからセキュリティ トークンを取得して、ユーザーを認証し、アプリケーションのセキュリティで保護された Web API にアクセスできます。 このライブラリでは、シングル サインオン (SSO)、条件付きアクセス、ブローカー認証など、複数の認証シナリオがサポートされています。

##### MSAL でのネイティブ認証のサポート

MSAL iOS は、アプリケーションがモバイル アプリケーションでエンド ツー エンドのカスタマイズ可能なフローを使用してネイティブ エクスペリエンスを実装できるようにするネイティブ認証 API を提供します。 ネイティブ認証を使用すると、ユーザーは、アプリを離れることなく、豊富でネイティブなモバイルファーストのサインアップとサインインの過程を案内されます。 ネイティブ認証機能は、 [顧客の外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) 上のモバイル アプリでのみ使用できます。 macOS はサポートされていません。 常に最も up-to-date バージョンの SDK を使用することをお勧めします。

### iOS および macOS 用 MSAL の概念ドキュメントの概要

MSAL ドキュメントでは、一般的なパターン、エラー処理とデバッグのベスト プラクティス、追加のライブラリ機能 (ログ記録、テレメトリなど)、アクティブなバグや既知の軽減策に関する一般的な問題について説明します。 Microsoft Entra ID、Microsoft アカウントの使用に関するその他のヘルプが必要な場合は、[Microsoft ID プラットフォームドキュメント](https://aka.ms/aaddev)を参照してください。Microsoft Graph API の詳細については、[Microsoft Graphドキュメント](https://graph.microsoft.io)を参照してください。MSAL iOS と macOS を効果的に使用してアプリケーションを保護するには、次の概念を理解することが重要です。

1. iOS 用 MSAL と macOS の違いについて説明します
2. [アプリを](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)Microsoft Entraに登録する
3. [iOS および macOS 用の MSAL をインストールし、ライブラリを](https://learn.microsoft.com/ja-jp/entra/msal/objc/install-and-configure-msal) 使用するようにプロジェクトを構成する
4. 保護された API にアクセスするための[トークンを取得](https://learn.microsoft.com/ja-jp/entra/msal/objc/acquire-tokens)する

アプリケーションで MSAL iOS と macOS を使用するには、Microsoft Entra管理センターにアプリケーションを登録し、プロジェクトを構成する必要があります。 SDK では、ブラウザーによる委任された認証エクスペリエンスとネイティブ認証エクスペリエンスの両方がサポートされているため、シナリオに基づいて、次のいずれかのクイックスタートの手順に従います。

- ブラウザーで委任された認証シナリオについては、「クイック スタート」の「[ユーザーをサインインさせ、iOS または macOS アプリからMicrosoft Graphを呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-ios-sign-in)」を参照してください。
- iOS アプリでのネイティブ認証シナリオについては、Microsoft Entra 外部 IDサンプル ガイドの[「iOS サンプル アプリの実行」](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-ios-app)を参照してください。

### サポートされているバージョン

**iOS** - MSAL は iOS 14 以降をサポートしています。

**macOS** - MSAL では、macOS (OSX) 10.13 以降がサポートされています。

### iOS 用 Microsoft Authentication Libraryと macOS の主な違い

このセクションでは、iOS と macOS のMicrosoft Authentication Library (MSAL) の機能の違いについて説明します。

Note

Mac では、MSAL は macOS アプリのみをサポートします。

### 全般的な相違点

macOS 用 MSAL は、iOS で使用できる機能のサブセットです。

macOS 用の MSAL では、次の機能はサポートされていません。

- `ASWebAuthenticationSession`、`SFAuthenticationSession`、`SFSafariViewController`など、さまざまなブラウザーの種類。
- Microsoft Authenticator アプリを使用したブローカー認証は、macOS ではサポートされていません。

同じ発行元のアプリ間でのキーチェーン共有は、macOS 10.14 以前では制限されています。 [アクセス制御リスト](https://developer.apple.com/documentation/security/keychain_services/access_control_lists?language=objc)を使用して、キーチェーンを共有するアプリへのパスを指定します。 ユーザーに追加のキーチェーン プロンプトが表示される場合があります。

macOS 10.15 以降では、MSAL の動作は iOS と macOS で同じです。 MSAL では [、キーチェーン共有にキーチェーン アクセス グループ](https://developer.apple.com/documentation/security/keychain_services/keychain_items/sharing_access_to_keychain_items_among_a_collection_of_apps?language=objc) が使用されます。

#### 条件付きアクセス認証

条件付きアクセスのシナリオでは、iOS 用の MSAL を使用する場合のユーザー プロンプトが少なくなります。 これは、iOS がブローカー アプリ (Microsoft Authenticator) を使用しているためです。これにより、場合によってはユーザーにプロンプトを表示する必要がなくなります。

#### プロジェクトの設定

**macOS**

- macOS でプロジェクトを設定するときは、アプリケーションが有効な開発証明書または運用証明書で署名されていることを確認します。 MSAL は未署名モードでも動作しますが、キャッシュの永続化に関しては動作が異なります。 アプリは、デバッグ目的でのみ署名なしで実行する必要があります。 署名されていないアプリを配布すると、次のようになります。

1. 10.14 以前では、MSAL はアプリを再起動するたびにキーチェーン パスワードの入力をユーザーに求めます。
2. 10.15 以降では、MSAL はユーザーにトークン取得ごとに資格情報の入力を求めます。

- macOS アプリでは、AppDelegate 呼び出しを実装する必要はありません。

**iOS**

- 認証ブローカー フローをサポートするようにプロジェクトを設定する追加の手順があります。 手順については、このチュートリアルで説明します。
- iOS プロジェクトでは、info.plist にカスタム スキームを登録する必要があります。 これは macOS では必要ありません。

### Azure Active Directory認証ライブラリ (ADAL) からの移行

Objective-C 用の Azure Active Directory 認証ライブラリ (ADAL) は**、2023 年 6 月 30 日**から非推奨となりました。 ユーザーまたは組織が Azure Active Directory 認証ライブラリ (ADAL) を使用している場合は、アプリのセキュリティを危険にさらさないように [MSAL に移行](https://learn.microsoft.com/ja-jp/entra/msal/objc/migrate-objc-adal-msal)する必要があります。

### Samples

[包括的なサンプル リストを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code?tabs=apptype#mobile)。

### SDK に関するヘルプを表示する

MSAL SDK に関する質問がある場合、ドキュメントでエラーが見つかった場合、または推奨事項が必要な場合は、詳細を含む [Github の問題](https://github.com/AzureAD/microsoft-authentication-library-for-objc/issues) を作成してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/acquire-tokens"} -->
## iOS/macOS 用 MSAL でトークンを取得する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/acquire-tokens
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: MSAL を使用してトークンを取得し、iOS/macOS アプリケーションでユーザーを認証する方法について説明します。

### アプリケーション オブジェクトの作成

MSALPublicClientApplication オブジェクトを初期化するときに、アプリ一覧のクライアント ID を使用します。

#### Swift

```swift
let config = MSALPublicClientApplicationConfig(clientId: "<your-client-id-here>")
let application = try? MSALPublicClientApplication(configuration: config) 
```

#### Objective-C

```obj
NSError *msalError = nil;
    
MSALPublicClientApplicationConfig *config = [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"<your-client-id-here>"];
MSALPublicClientApplication *application = [[MSALPublicClientApplication alloc] initWithConfiguration:config error:&msalError];
    
```

### 対話形式でトークンを取得する

##### Swift

```swift
#if os(iOS)let viewController = ... // Pass a reference to the view controller that should be used when getting a token interactivelylet webviewParameters = MSALWebviewParameters(authPresentationViewController: viewController)
#elselet webviewParameters = MSALWebviewParameters()
#endif
let interactiveParameters = MSALInteractiveTokenParameters(scopes: scopes, webviewParameters: webviewParameters)
application.acquireToken(with: interactiveParameters, completionBlock: { (result, error) in
                guard let authResult = result, error == nil else {	print(error!.localizedDescription)	return}
                // Get access token from resultlet accessToken = authResult.accessToken
                // You'll want to get the account identifier to retrieve and reuse the account for later acquireToken callslet accountIdentifier = authResult.account.identifier
})
```

#### Objective-C

```obj
#if TARGET_OS_IPHONE
    UIViewController *viewController = ...; // Pass a reference to the view controller that should be used when getting a token interactively
    MSALWebviewParameters *webParameters = [[MSALWebviewParameters alloc] initWithAuthPresentationViewController:viewController];
#else
    MSALWebviewParameters *webParameters = [MSALWebviewParameters new];
#endif 

MSALInteractiveTokenParameters *interactiveParams = [[MSALInteractiveTokenParameters alloc] initWithScopes:scopes webviewParameters:webParameters];
[application acquireTokenWithParameters:interactiveParams completionBlock:^(MSALResult *result, NSError *error) {if (!error)	{	// You'll want to get the account identifier to retrieve and reuse the account	// for later acquireToken calls	NSString *accountIdentifier = result.account.identifier;
            	NSString *accessToken = result.accessToken;}
  	else{	// Check the error}
}];
```

Note

このライブラリでは、既定で iOS 12 での認証に ASWebAuthenticationSession が使用されます。 [既定値の詳細と、その他の iOS バージョンのサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/customize-webviews)を参照してください。

### トークンをサイレントで取得する

#### Swift

```swift
guard let account = try? application.account(forIdentifier: accountIdentifier) else { return }
let silentParameters = MSALSilentTokenParameters(scopes: scopes, account: account)
application.acquireTokenSilent(with: silentParameters) { (result, error) in
            guard let authResult = result, error == nil else {
                let nsError = error! as NSError
                	if (nsError.domain == MSALErrorDomain &&		nsError.code == MSALError.interactionRequired.rawValue) {
                    		// Interactive auth will be required		return	}	return}
            // Get access token from resultlet accessToken = authResult.accessToken
}
```

#### Objective-C

```objective
NSError *error = nil;
MSALAccount *account = [application accountForIdentifier:accountIdentifier error:&error];
if (!account)
{
    // handle error
    return;
}
    
MSALSilentTokenParameters *silentParams = [[MSALSilentTokenParameters alloc] initWithScopes:scopes account:account];
[application acquireTokenSilentWithParameters:silentParams completionBlock:^(MSALResult *result, NSError *error) {
    if (!error)
    {
        NSString *accessToken = result.accessToken;
    }
    else
    {
        // Check the error
        if ([error.domain isEqual:MSALErrorDomain] && error.code == MSALErrorInteractionRequired)
        {
            // Interactive auth will be required
        }
            
        // Other errors may require trying again later, or reporting authentication problems to the user
    }
}];
```

### 操作が必要なエラーへの対応

新しいアクセス トークンを取得するためにユーザーの操作が必要になる場合があります。この場合、新しいトークンを自動的に取得しようとすると、 `MSALErrorInteractionRequired` エラーが発生します。 このような場合は、失敗した`acquireToken:`呼び出しと同じアカウントとスコープで`acquireTokenSilent:`を呼び出します。 対話型の `acquireToken:` 呼び出しを呼び出す前に、目立たない方法でステータス メッセージをユーザーに表示することをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/configure-authority"} -->
## ID プロバイダーの構成 (MSAL iOS/macOS)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/configure-authority
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: iOS および macOS 用の MSAL を使用して、B2C、ソブリン クラウド、ゲスト ユーザーなどのさまざまな機関を使用する方法について説明します。

この記事では、Microsoft Entra ID、企業間 (B2C)、ソブリン クラウド、ゲスト ユーザーなど、さまざまな機関に対して iOS および macOS 用の Microsoft Authentication Library (MSA) アプリを構成する方法について説明します。 この記事では、通常、機関を ID プロバイダーと考えることができます。

### 既定の機関構成

`MSALPublicClientApplication`は、`https://login.microsoftonline.com/common`の既定の機関 URL を使用して構成されます。これは、ほとんどのMicrosoft Entraシナリオに適しています。 各国のクラウドなどの高度なシナリオを実装している場合や、B2C を使用している場合を除き、変更する必要はありません。

Note

ID プロバイダー (ADFS) としてのActive Directory フェデレーション サービス (AD FS)による先進認証はサポートされていません (詳細については、[開発者向け ADFS](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/overview/ad-fs-openid-connect-oauth-flows-scenarios) を参照してください)。 ADFS はフェデレーションを通じてサポートされます。

### 既定の機関を変更する

一部のシナリオ (企業間 (B2C) など) では、既定の機関の変更が必要になる場合があります。

#### B2C

B2C を操作するには、MSAL に別の機関構成が必要です。 MSAL は、1 つの機関 URL 形式を B2C として単独で認識します。 認識された B2C 権限形式は、`https://<host>/tfp/<tenant>/<policy>`など、`https://login.microsoftonline.com/tfp/contoso.onmicrosoft.com/B2C_1_SignInPolicy`。 ただし、権限を B2C 機関として明示的に宣言することで、サポートされている他の B2C 機関 URL を使用することもできます。

B2C の任意の URL 形式をサポートするには、次のように任意の URL で `MSALB2CAuthority` を設定できます。

##### Objective-C

```obj
NSURL *authorityURL = [NSURL URLWithString:@"arbitrary URL"];
MSALB2CAuthority *b2cAuthority = [[MSALB2CAuthority alloc] initWithURL:authorityURL
                                                                     error:&b2cAuthorityError];
```

##### Swift

```swift
guard let authorityURL = URL(string: "arbitrary URL") else {
    // Handle error
    return
}
let b2cAuthority = try MSALB2CAuthority(url: authorityURL)
```

既定の B2C 機関形式を使用しないすべての B2C 機関は、既知の機関として宣言する必要があります。

機関がポリシーによってのみ異なる場合でも、それぞれ異なる B2C 権限を既知の機関リストに追加します。

##### Objective-C

```obj
MSALPublicClientApplicationConfig *b2cApplicationConfig = [[MSALPublicClientApplicationConfig alloc]
                                                               initWithClientId:@"your-client-id"
                                                               redirectUri:@"your-redirect-uri"
                                                               authority:b2cAuthority];
b2cApplicationConfig.knownAuthorities = @[b2cAuthority];
```

##### Swift

```swift
let b2cApplicationConfig = MSALPublicClientApplicationConfig(clientId: "your-client-id", redirectUri: "your-redirect-uri", authority: b2cAuthority)
b2cApplicationConfig.knownAuthorities = [b2cAuthority]
```

アプリが新しいポリシーを要求するときは、機関 URL がポリシーごとに異なるため、機関 URL を変更する必要があります。

B2C アプリケーションを構成するには、次のように、`@property MSALAuthority *authority`を作成する前に、`MSALB2CAuthority`の`MSALPublicClientApplicationConfig`のインスタンスで`MSALPublicClientApplication`を設定します。

##### Objective-C

```obj
    // Create B2C authority URL
    NSURL *authorityURL = [NSURL URLWithString:@"https://login.microsoftonline.com/tfp/contoso.onmicrosoft.com/B2C_1_SignInPolicy"];
    
    MSALB2CAuthority *b2cAuthority = [[MSALB2CAuthority alloc] initWithURL:authorityURL
                                                                     error:&b2cAuthorityError];
    if (!b2cAuthority)
    {
        // Handle error
        return;
    }
    
    // Create MSALPublicClientApplication configuration
    MSALPublicClientApplicationConfig *b2cApplicationConfig = [[MSALPublicClientApplicationConfig alloc]
                                                                   initWithClientId:@"your-client-id"
                                                                   redirectUri:@"your-redirect-uri"
                                                                   authority:b2cAuthority];

    // Initialize MSALPublicClientApplication
    MSALPublicClientApplication *b2cApplication =
    [[MSALPublicClientApplication alloc] initWithConfiguration:b2cApplicationConfig error:&error];
    
    if (!b2cApplication)
    {
        // Handle error
        return;
    }
```

##### Swift

```swift
do{
    // Create B2C authority URL
    guard let authorityURL = URL(string: "https://login.microsoftonline.com/tfp/contoso.onmicrosoft.com/B2C_1_SignInPolicy") else {
        // Handle error
        return
    }
    let b2cAuthority = try MSALB2CAuthority(url: authorityURL)

    // Create MSALPublicClientApplication configuration
    let b2cApplicationConfig = MSALPublicClientApplicationConfig(clientId: "your-client-id", redirectUri: "your-redirect-uri", authority: b2cAuthority)

    // Initialize MSALPublicClientApplication
    let b2cApplication = try MSALPublicClientApplication(configuration: b2cApplicationConfig)
} catch {
    // Handle error
}
```

#### ソブリン クラウド

アプリがソブリン クラウドで実行されている場合は、 `MSALPublicClientApplication`の機関 URL の変更が必要になる場合があります。 次の例では、ドイツのMicrosoft Entra クラウドで動作するように機関 URL を設定します。

##### Objective-C

```obj
    NSURL *authorityURL = [NSURL URLWithString:@"https://login.microsoftonline.de/common"];
    MSALAuthority *sovereignAuthority = [MSALAuthority authorityWithURL:authorityURL error:&authorityError];
    
    if (!sovereignAuthority)
    {
        // Handle error
        return;
    }
    
    MSALPublicClientApplicationConfig *applicationConfig = [[MSALPublicClientApplicationConfig alloc]
                                                               initWithClientId:@"your-client-id"
                                                               redirectUri:@"your-redirect-uri"
                                                               authority:sovereignAuthority];

    MSALPublicClientApplication *sovereignApplication = [[MSALPublicClientApplication alloc] initWithConfiguration:applicationConfig error:&error];

    if (!sovereignApplication)
    {
        // Handle error
        return;
    }
```

##### Swift

```swift
do{
    guard let authorityURL = URL(string: "https://login.microsoftonline.de/common") else {
        //Handle error
        return
    }
    let sovereignAuthority = try MSALAuthority(url: authorityURL)
            
    let applicationConfig = MSALPublicClientApplicationConfig(clientId: "your-client-id", redirectUri: "your-redirect-uri", authority: sovereignAuthority)
            
    let sovereignApplication = try MSALPublicClientApplication(configuration: applicationConfig)
} catch {
    // Handle error
}
```

場合によっては、各ソブリン クラウドに異なるスコープを渡す必要があります。 送信するスコープは、使用しているリソースによって異なります。 たとえば、世界中のクラウドで `"https://graph.microsoft.com/user.read"` を使用し、ドイツのクラウドで `"https://graph.microsoft.de/user.read"` できます。

#### ユーザーを特定のテナントにサインインさせる

機関 URL が `"login.microsoftonline.com/common"` に設定されている場合、ユーザーはホーム テナントにサインインします。 ただし、一部のアプリではユーザーを別のテナントにサインインさせる必要があり、一部のアプリは 1 つのテナントでのみ動作します。

特定のテナントにユーザーをサインインさせるには、特定の機関で `MSALPublicClientApplication` を構成します。 例えば次が挙げられます。

`https://login.microsoftonline.com/dddd5555-eeee-6666-ffff-00001111aaaa`

Contoso テナントにサインインする場合は、以下を使用します。

`https://login.microsoftonline.com/contoso.onmicrosoft.com`

Contoso テナントにユーザーをサインインさせる方法を次に示します。

##### Objective-C

```obj
    NSURL *authorityURL = [NSURL URLWithString:@"https://login.microsoftonline.com/contoso.onmicrosoft.com"];
    MSALAADAuthority *tenantedAuthority = [[MSALAADAuthority alloc] initWithURL:authorityURL error:&authorityError];
    
    if (!tenantedAuthority)
    {
        // Handle error
        return;
    }
    
    MSALPublicClientApplicationConfig *applicationConfig = [[MSALPublicClientApplicationConfig alloc]
                                                               initWithClientId:@"your-client-id"
                                                               redirectUri:@"your-redirect-uri"
                                                               authority:tenantedAuthority];
    
    MSALPublicClientApplication *application =
    [[MSALPublicClientApplication alloc] initWithConfiguration:applicationConfig error:&error];
    
    if (!application)
    {
        // Handle error
        return;
    }
```

##### Swift

```swift
do{
    guard let authorityURL = URL(string: "https://login.microsoftonline.com/contoso.onmicrosoft.com") else {
        //Handle error
        return
    }    
    let tenantedAuthority = try MSALAADAuthority(url: authorityURL)
            
    let applicationConfig = MSALPublicClientApplicationConfig(clientId: "your-client-id", redirectUri: "your-redirect-uri", authority: tenantedAuthority)
            
    let application = try MSALPublicClientApplication(configuration: applicationConfig)
} catch {
    // Handle error
}
```

### サポートされている機関

#### MSALAuthority

`MSALAuthority` クラスは、MSAL 機関クラスの基本抽象クラスです。 `alloc`または`new`を使用してインスタンスを作成しないでください。 代わりに、サブクラスの 1 つを直接作成するか (`MSALAADAuthority`、 `MSALB2CAuthority`)、またはファクトリ メソッド `authorityWithURL:error:` を使用して、機関 URL を使用してサブクラスを作成します。

正規化された機関の URL を取得するには、 `url` プロパティを使用します。 権限の一部ではない追加のパラメーターとパス コンポーネントまたはフラグメントは、返された正規化された機関の URL には含まれません。

使用する権限に応じてインスタンス化できる `MSALAuthority` のサブクラスを次に示します。

#### MSALAADAuthority

`MSALAADAuthority`は、Microsoft Entra機関を表します。 機関 URL は次の形式にする必要があります。ここで、 `<port>` は省略可能です。 `https://<host>:<port>/<tenant>`

#### MSALB2CAuthority

`MSALB2CAuthority` は B2C 機関を表します。 既定では、B2C 機関の URL は次の形式にする必要があります。ここで、 `<port>` は省略可能です: `https://<host>:<port>/tfp/<tenant>/<policy>`。 ただし、MSAL では、他の任意の B2C 機関形式もサポートされています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/customize-webviews"} -->
## ブラウザーをカスタマイズする & WebViews (MSAL iOS/macOS)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/customize-webviews
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: MSAL iOS/macOS ブラウザー エクスペリエンスをカスタマイズしてユーザーをサインインさせる方法について説明します。

対話型の認証には Web ブラウザーが必要です。 iOS および macOS 10.15 以降では、Microsoft Authentication Library (MSAL) は既定でシステム Web ブラウザー (アプリの上に表示される場合があります) を使用して、対話型認証を実行してユーザーをサインインします。 システム ブラウザーを使用すると、シングル サインオン (SSO) 状態を他のアプリケーションや Web アプリケーションと共有できるという利点があります。

次のような Web コンテンツを表示するための他のオプションに構成をカスタマイズすることで、エクスペリエンスを変更できます。

iOS の場合のみ:

- [SFAuthenticationSession](https://developer.apple.com/documentation/safariservices/sfauthenticationsession?language=objc)
- [SFSafariViewController](https://developer.apple.com/documentation/safariservices/sfsafariviewcontroller?language=objc)

iOS および macOS の場合:

- [ASWebAuthenticationSession](https://developer.apple.com/documentation/authenticationservices/aswebauthenticationsession?language=objc)
- [WKWebView](https://developer.apple.com/documentation/webkit/wkwebview?language=objc)。

macOS 用 MSAL では、古い OS バージョンでの `WKWebView` のみがサポートされます。 `ASWebAuthenticationSession` は macOS 10.15 以降でのみサポートされています。

### システム ブラウザー

iOS の場合、 `ASWebAuthenticationSession`、 `SFAuthenticationSession`、 `SFSafariViewController` はシステム ブラウザーと見なされます。 macOS の場合、 `ASWebAuthenticationSession` のみを使用できます。 一般に、システム ブラウザーは、Cookie やその他の Web サイト データを Safari ブラウザー アプリケーションと共有します。

既定では、MSAL は iOS バージョンを動的に検出し、そのバージョンで使用可能な推奨システム ブラウザーを選択します。 iOS 12 以降では、 `ASWebAuthenticationSession`されます。

#### iOS の既定の構成

| バージョン | Web ブラウザ |
| --- | --- |
| iOS 12 以降 | ASWebAuthenticationSession |
| iOS 11 | SFAuthenticationSession |
| iOS 10 | SFSafariViewController |

#### macOS の既定の構成

| バージョン | Web ブラウザ |
| --- | --- |
| macOS 10.15 以降 | ASWebAuthenticationSession |
| その他のバージョン | WKWebView |

開発者は、MSAL アプリ用に別のシステム ブラウザーを選択することもできます。

- `SFAuthenticationSession` は iOS 11 バージョンの `ASWebAuthenticationSession`です。
- `SFSafariViewController` は、より一般的な目的であり、Web を参照するためのインターフェイスを提供し、ログイン目的でも使用できます。 iOS 9 および 10 では、Cookie やその他の Web サイト データは Safari と共有されますが、iOS 11 以降では共有されません。

### アプリ内ブラウザー

[WKWebView](https://developer.apple.com/documentation/webkit/wkwebview) は、Web コンテンツを表示するアプリ内ブラウザーです。 Cookie や Web サイトのデータは、他の **WKWebView** インスタンスや Safari ブラウザーと共有されません。 WKWebView は、iOS と macOS の両方で使用できるクロスプラットフォーム ブラウザーです。

### Cookie の共有と SSO への影響

使用するブラウザーは、Cookie の共有方法により SSO エクスペリエンスに影響します。 次の表は、ブラウザーごとの SSO エクスペリエンスの概要を示しています。

| テクノロジ | ブラウザーの種類 | iOS の可用性 | macOS の可用性 | Cookie とその他のデータを共有する | MSAL の可用性 | SSO |
| --- | --- | --- | --- | --- | --- | --- |
| [ASWebAuthenticationSession](https://developer.apple.com/documentation/authenticationservices/aswebauthenticationsession) | システム | iOS12 以降 | macOS 10.15 以降 | はい | iOS および macOS 10.15 以降 | Safari インスタンスを含む |
| [SFAuthenticationSession](https://developer.apple.com/documentation/safariservices/sfauthenticationsession) | システム | iOS 11以降 | N/a | はい | iOS のみ | Safari インスタンスを含む |
| [SFSafariViewController](https://developer.apple.com/documentation/safariservices/sfsafariviewcontroller) | システム | iOS 11以降 | N/a | No | iOS のみ | いいえ\*\* |
| **SFSafariViewController** | システム | iOS10 | N/a | はい | iOS のみ | Safari インスタンスを含む |
| **WKWebView** | アプリ内 | iOS 8以降 | macOS 10.10 以降 | No | iOS と macOS | いいえ\*\* |

\*\* SSO を機能させるには、トークンをアプリ間で共有する必要があります。 これには、iOS 用のMicrosoft Authenticatorなどのトークン キャッシュまたはブローカー アプリケーションが必要です。

### 要求の既定のブラウザーを変更する

`MSALWebviewParameters`で次のプロパティを変更することで、UX 要件に応じてアプリ内ブラウザーまたは特定のシステム ブラウザーを使用できます。

```objc
@property (nonatomic) MSALWebviewType webviewType;
```

### 対話型要求ごとの変更

各要求は、`MSALInteractiveTokenParameters.webviewParameters.webviewType` API に渡す前に `acquireTokenWithParameters:completionBlock:` プロパティを変更することで、既定のブラウザーをオーバーライドするように構成できます。

さらに、MSAL では、`WKWebView` プロパティを設定してカスタム `MSALInteractiveTokenParameters.webviewParameters.customWebView`を渡すことがサポートされています。

例えば次が挙げられます。

Objective-C

```objc
UIViewController *myParentController = ...;
WKWebView *myCustomWebView = ...;
MSALWebviewParameters *webViewParameters = [[MSALWebviewParameters alloc] initWithAuthPresentationViewController:myParentController];
webViewParameters.webviewType = MSALWebviewTypeWKWebView;
webViewParameters.customWebview = myCustomWebView;
MSALInteractiveTokenParameters *interactiveParameters = [[MSALInteractiveTokenParameters alloc] initWithScopes:@[@"myscope"] webviewParameters:webViewParameters];

[app acquireTokenWithParameters:interactiveParameters completionBlock:completionBlock];
```

Swift

```swift
let myParentController: UIViewController = ...
let myCustomWebView: WKWebView = ...
let webViewParameters = MSALWebviewParameters(authPresentationViewController: myParentController)
webViewParameters.webviewType = MSALWebviewType.wkWebView
webViewParameters.customWebview = myCustomWebView
let interactiveParameters = MSALInteractiveTokenParameters(scopes: ["myscope"], webviewParameters: webViewParameters)

app.acquireToken(with: interactiveParameters, completionBlock: completionBlock)
```

カスタム Web ビューを使用する場合、通知は、表示されている Web コンテンツの状態を示すために使用されます。次に例を示します。

```objc
/*! Fired at the start of a resource load in the webview. The URL of the load, if available, will be in the @"url" key in the userInfo dictionary */
extern NSString *MSALWebAuthDidStartLoadNotification;

/*! Fired when a resource finishes loading in the webview. */
extern NSString *MSALWebAuthDidFinishLoadNotification;

/*! Fired when web authentication fails due to reasons originating from the network. Look at the @"error" key in the userInfo dictionary for more details.*/
extern NSString *MSALWebAuthDidFailNotification;

/*! Fired when authentication finishes */
extern NSString *MSALWebAuthDidCompleteNotification;

/*! Fired before ADAL invokes the broker app */
extern NSString *MSALWebAuthWillSwitchToBrokerApp;
```

#### オプション

MSAL でサポートされているすべての Web ブラウザーの種類は、[MSALWebviewType 列挙型](https://github.com/AzureAD/microsoft-authentication-library-for-objc/blob/master/MSAL/src/public/MSALDefinitions.h#L47)で宣言されています

```objc
typedef NS_ENUM(NSInteger, MSALWebviewType)
{
    /**
     For iOS 11 and up, uses AuthenticationSession (ASWebAuthenticationSession or SFAuthenticationSession).
     For older versions, with AuthenticationSession not being available, uses SafariViewController.
     For macOS 10.15 and above uses ASWebAuthenticationSession
     For older macOS versions uses WKWebView
     */
    MSALWebviewTypeDefault,

    /** Use ASWebAuthenticationSession where available.
     On older iOS versions uses SFAuthenticationSession
     Doesn't allow any other webview type, so if either of these are not present, fails the request*/
    MSALWebviewTypeAuthenticationSession,

#if TARGET_OS_IPHONE

    /** Use SFSafariViewController for all versions. */
    MSALWebviewTypeSafariViewController,

#endif
    /** Use WKWebView */
    MSALWebviewTypeWKWebView,
};
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/error-handling-ios"} -->
## iOS/macOS の MSAL でエラーと例外を処理する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/error-handling-ios
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: iOS/macOS アプリケーションの MSAL でエラーと例外、条件付きアクセス要求チャレンジ、再試行を処理する方法について説明します。

この記事では、さまざまな種類のエラーの概要と、一般的なサインイン エラーを処理するための推奨事項について説明します。

### MSAL エラー処理の基本

Microsoft Authentication Library (MSAL) の例外は、エンド ユーザーに表示されるのではなく、アプリ開発者がトラブルシューティングを行うために使用されます。 例外メッセージはローカライズされません。

例外とエラーを処理する場合は、例外の種類自体とエラー コードを使用して例外を区別できます。 エラー コードの一覧については、[認証と承認のエラー コードMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)参照してください。

サインイン エクスペリエンス中に、同意、条件付きアクセス (MFA、デバイス管理、場所ベースの制限)、トークンの発行と利用、およびユーザー プロパティに関するエラーが発生する場合があります。

次のセクションでは、アプリのエラー処理の詳細について説明します。

### iOS/macOS 用 MSAL でのエラー処理

iOS および macOS の MSAL エラーの完全な一覧は、 [MSALError 列挙型](https://github.com/AzureAD/microsoft-authentication-library-for-objc/blob/master/MSAL/src/public/MSALError.h#L128)に記載されています。

MSAL で生成されたすべてのエラーは、 `MSALErrorDomain` ドメインで返されます。

システム エラーの場合、MSAL はシステム API から元の `NSError` を返します。 たとえば、ネットワーク接続がないためにトークンの取得が失敗した場合、MSAL は `NSURLErrorDomain` ドメインと `NSURLErrorNotConnectedToInternet` コードでエラーを返します。

クライアント側では、少なくとも次の 2 つの MSAL エラーを処理することをお勧めします。

- `MSALErrorInteractionRequired`: ユーザーは対話型の要求を行う必要があります。 認証セッションの期限切れや追加の認証要件の必要性など、このエラーの原因となる可能性のある条件は多数あります。 MSAL 対話型トークン取得 API を呼び出して復旧します。
- `MSALErrorServerDeclinedScopes`:一部またはすべてのスコープが拒否されました。 許可されたスコープのみを続行するか、サインイン プロセスを停止するかを決定します。

Note

`MSALInternalError`列挙型は、参照とデバッグにのみ使用する必要があります。 実行時にこれらのエラーを自動的に処理しないでください。 アプリで `MSALInternalError`に該当するエラーのいずれかが発生した場合は、発生した内容を説明する一般的なユーザー向けのメッセージを表示できます。

たとえば、 `MSALInternalErrorBrokerResponseNotReceived` は、ユーザーが認証を完了せず、アプリに手動で戻したことを意味します。 この場合、アプリには、認証が完了しなかったことを説明する一般的なエラー メッセージが表示され、再認証を試みるように提案する必要があります。

次の Objective-C サンプル コードは、一般的なエラー状態を処理するためのベスト プラクティスを示しています。

```objc
    MSALInteractiveTokenParameters *interactiveParameters = ...;
    MSALSilentTokenParameters *silentParameters = ...;
    
    MSALCompletionBlock completionBlock;
    __block __weak MSALCompletionBlock weakCompletionBlock;
    
    weakCompletionBlock = completionBlock = ^(MSALResult *result, NSError *error)
    {
        if (!error)
        {
            // Use result.accessToken
            NSString *accessToken = result.accessToken;
            return;
        }
        
        if ([error.domain isEqualToString:MSALErrorDomain])
        {
            switch (error.code)
            {
                case MSALErrorInteractionRequired:
                {
                    // Interactive auth will be required
                    [application acquireTokenWithParameters:interactiveParameters
                                            completionBlock:weakCompletionBlock];
                    
                    break;
                }
                    
                case MSALErrorServerDeclinedScopes:
                {
                    // These are list of granted and declined scopes.
                    NSArray *grantedScopes = error.userInfo[MSALGrantedScopesKey];
                    NSArray *declinedScopes = error.userInfo[MSALDeclinedScopesKey];
                    
                    // To continue acquiring token for granted scopes only, do the following
                    silentParameters.scopes = grantedScopes;
                    [application acquireTokenSilentWithParameters:silentParameters
                                                  completionBlock:weakCompletionBlock];
                    
                    // Otherwise, instead, handle error fittingly to the application context
                    break;
                }
                    
                case MSALErrorServerProtectionPoliciesRequired:
                {
                    // Integrate the Intune SDK and call the
                    // remediateComplianceForIdentity:silent: API.
                    // Handle this error only if you integrated Intune SDK.
                    // See more info here: https://aka.ms/intuneMAMSDK
                    
                    break;
                }
                    
                case MSALErrorUserCanceled:
                {
                    // The user cancelled the web auth session.
                    // You may want to ask the user to try again.
                    // Handling of this error is optional.
                    
                    break;
                }
                    
                case MSALErrorInternal:
                {
                    // Log the error, then inspect the MSALInternalErrorCodeKey
                    // in the userInfo dictionary.
                    // Display generic error message to the end user
                    // More detailed information about the specific error
                    // under MSALInternalErrorCodeKey can be found in MSALInternalError enum.
                    NSLog(@"Failed with error %@", error);
                    
                    break;
                }
                    
                default:
                    NSLog(@"Failed with unknown MSAL error %@", error);
                    
                    break;
            }
            
            return;
        }
        
        // Handle no internet connection.
        if ([error.domain isEqualToString:NSURLErrorDomain] && error.code == NSURLErrorNotConnectedToInternet)
        {
            NSLog(@"No internet connection.");
            return;
        }
        
        // Other errors may require trying again later,
        // or reporting authentication problems to the user.
        NSLog(@"Failed with error %@", error);
    };
    
    // Acquire token silently
    [application acquireTokenSilentWithParameters:silentParameters
                                  completionBlock:completionBlock];

     // or acquire it interactively.
     [application acquireTokenWithParameters:interactiveParameters
                             completionBlock:completionBlock];
```

```swift
    let interactiveParameters: MSALInteractiveTokenParameters = ...
    let silentParameters: MSALSilentTokenParameters = ...
            
    var completionBlock: MSALCompletionBlock!
    completionBlock = { (result: MSALResult?, error: Error?) in
                
        if let result = result
        {
            // Use result.accessToken
            let accessToken = result.accessToken
            return
        }

        guard let error = error as NSError? else { return }

        if error.domain == MSALErrorDomain, let errorCode = MSALError(rawValue: error.code)
        {
            switch errorCode
            {
                case .interactionRequired:
                    // Interactive auth will be required
                    application.acquireToken(with: interactiveParameters, completionBlock: completionBlock)

                case .serverDeclinedScopes:
                    let grantedScopes = error.userInfo[MSALGrantedScopesKey]
                    let declinedScopes = error.userInfo[MSALDeclinedScopesKey]

                    if let scopes = grantedScopes as? [String] {
                        silentParameters.scopes = scopes
                        application.acquireTokenSilent(with: silentParameters, completionBlock: completionBlock)
                    }
                        
                    case .serverProtectionPoliciesRequired:
                        // Integrate the Intune SDK and call the
                        // remediateComplianceForIdentity:silent: API.
                        // Handle this error only if you integrated Intune SDK.
                        // See more info here: https://aka.ms/intuneMAMSDK
                        break
                        
                    case .userCanceled:
                       // The user cancelled the web auth session.
                       // You may want to ask the user to try again.
                       // Handling of this error is optional.
                       break
                        
                    case .internal:
                        // Log the error, then inspect the MSALInternalErrorCodeKey
                        // in the userInfo dictionary.
                        // Display generic error message to the end user
                        // More detailed information about the specific error
                        // under MSALInternalErrorCodeKey can be found in MSALInternalError enum.
                        print("Failed with error \(error)");
                        
                    default:
                        print("Failed with unknown MSAL error \(error)")
            }
        }
                
        // Handle no internet connection.
        if error.domain == NSURLErrorDomain && error.code == NSURLErrorNotConnectedToInternet
        {
            print("No internet connection.")
            return
        }
                
        // Other errors may require trying again later,
        // or reporting authentication problems to the user.
        print("Failed with error \(error)");    
    }
   
    // Acquire token silently
    application.acquireToken(with: interactiveParameters, completionBlock: completionBlock)
 
    // or acquire it interactively.
    application.acquireTokenSilent(with: silentParameters, completionBlock: completionBlock)
```

### 条件付きアクセスと要求の課題

トークンをサイレントで取得すると、アクセスしようとしている API で MFA ポリシーなどの [条件付きアクセス要求チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide) が必要な場合、アプリケーションでエラーが発生する可能性があります。

このエラーを処理するパターンは、MSAL を使用して対話形式でトークンを取得することです。 これにより、ユーザーにプロンプトが表示され、必要な条件付きアクセス ポリシーを満たす機会が提供されます。

場合によっては、条件付きアクセスが必要な API を呼び出す際に、API から返されるエラー内でクレーム チャレンジを受け取ることがあります。 たとえば、条件付きアクセス ポリシーでマネージド デバイス (Intune) を使用する場合、エラーは [AADSTS53000 のようになります。このリソースや同様のリソースにアクセスするには、デバイスを管理する必要があります](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes) 。 この場合、取得トークン呼び出しで要求を渡して、ユーザーが適切なポリシーを満たすように求めることができます。

iOS および macOS 用の MSAL を使用すると、対話型トークン取得シナリオとサイレント トークン取得シナリオの両方で特定の要求を要求できます。

カスタム要求を要求するには、`claimsRequest`または`MSALSilentTokenParameters`で`MSALInteractiveTokenParameters`を指定します。

詳細については、「 [iOS および macOS 用の MSAL を使用してカスタム要求を要求](https://learn.microsoft.com/ja-jp/entra/msal/objc/request-custom-claims) する」を参照してください。

### エラーと例外の後の再試行

MSAL を呼び出すときに、独自の再試行ポリシーを実装する必要があります。 MSAL では、Microsoft Entra サービスへの HTTP 呼び出しが行われ、エラーが発生することがあります。 たとえば、ネットワークがダウンしたり、サーバーが過負荷になったりする可能性があります。

#### HTTP 429

サービス トークン サーバー (STS) が多すぎる要求でオーバーロードされると、HTTP エラー 429 が返され、 `Retry-After` 応答フィールドで再試行できるまでの時間に関するヒントが返されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/howto-v2-keychain-objc"} -->
## キーチェーンの構成

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/howto-v2-keychain-objc
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: アプリがキーチェーンにトークンをキャッシュできるようにキーチェーンを構成する方法について説明します。

iOS および macOS 用のMicrosoft Authentication Library (MSAL) がユーザーをサインインさせるか、トークンを更新すると、キーチェーンにトークンをキャッシュしようとします。 キーチェーン内のトークンをキャッシュすると、MSAL は、同じ Apple 開発者によって配布される複数のアプリ間でサイレント シングル サインオン (SSO) を提供できます。 SSO は、キーチェーン アクセス グループ機能を使用して実現されます。 詳細については、Apple の [キーチェーン アイテムに関するドキュメントを参照してください](https://developer.apple.com/documentation/security/keychain_services/keychain_items/sharing_access_to_keychain_items_among_a_collection_of_apps?language=objc)。

この記事では、MSAL がキャッシュされたトークンを iOS および macOS キーチェーンに書き込むことができるようにアプリの権利を構成する方法について説明します。

### 既定のキーチェーン アクセス グループ

#### iOS

iOS 上の MSAL では、既定で `com.microsoft.adalcache` アクセス グループが使用されます。 これにより、同じ発行元の複数のアプリ間で最高の SSO エクスペリエンスが保証されます。

iOS では、Xcode の **プロジェクト設定**&gt;**Capabilities**&gt;**Keychain Sharing** で、`com.microsoft.adalcache`キーチェーングループをアプリのエンタイトルメントに追加します。

#### macOS

macOS 上の MSAL では、既定でアクセス グループ `com.microsoft.identity.universalstorage` 使用されます。

macOS では、iOS と同様に、**Project settings**&gt;**Capabilities**&gt;**Keychain Sharing** の Xcode 上で、アプリのエンタイトルメントに `com.microsoft.identity.universalstorage` キーチェーングループを追加します。

### カスタム キーチェーン アクセス グループ

別のキーチェーン アクセス グループを使用する場合は、次のように、`MSALPublicClientApplicationConfig`を作成する前に、`MSALPublicClientApplication`を作成するときにカスタム グループを渡すことができます。

## [Objective-C](#tab/objc)
```objc
MSALPublicClientApplicationConfig *config = [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"your-client-id"
                                                                                            redirectUri:@"your-redirect-uri"
                                                                                              authority:nil];
    
config.cacheConfig.keychainSharingGroup = @"custom-group";
    
MSALPublicClientApplication *application = [[MSALPublicClientApplication alloc] initWithConfiguration:config error:nil];
    
// Now call `acquiretoken`. 
// Tokens will be saved into the "custom-group" access group
// and only shared with other applications declaring the same access group
```

## [速い](#tab/swift)
```swift
let config = MSALPublicClientApplicationConfig(clientId: "your-client-id",
                                            redirectUri: "your-redirect-uri",
                                              authority: nil)
config.cacheConfig.keychainSharingGroup = "custom-group"
        
do {
  let application = try MSALPublicClientApplication(configuration: config)
  // continue on with application          
} catch let error as NSError {
  // handle error here
}       
```

---

### キーチェーン共有を無効にする

複数のアプリ間で SSO 状態を共有しない場合、またはキーチェーン アクセス グループを使用しない場合は、キーチェーン グループとしてアプリケーション バンドル ID を渡してキーチェーン共有を無効にします。

## [Objective-C](#tab/objc)
```objc
config.cacheConfig.keychainSharingGroup = [[NSBundle mainBundle] bundleIdentifier];
```

## [速い](#tab/swift)
```swift
if let bundleIdentifier = Bundle.main.bundleIdentifier {
    config.cacheConfig.keychainSharingGroup = bundleIdentifier
}
```

---

### -34018 エラーを処理する (項目をキーチェーンに設定できませんでした)

エラー -34018 は、通常、キーチェーンが正しく構成されていないことを意味します。 MSAL で構成されているキーチェーン アクセス グループが、エンタイトルメントで構成されているキーチェーン アクセス グループと一致していることを確認します。

### アプリケーションが正しく署名されていることを確認する

macOS では、開発者が署名しなくてもアプリケーションを実行できます。 MSAL のほとんどの機能は引き続き機能しますが、キーチェーン アクセスによる SSO ではアプリケーションに署名する必要があります。 複数のキーチェーン プロンプトが表示される場合は、アプリケーションの署名が有効であることを確認してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/install-and-configure-msal"} -->
## iOS/macOS 用の MSAL をインストールして構成する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/install-and-configure-msal
- Service: msal / msal-ios-mac
- Article date: 2024-03-11
- Summary: MSAL をインストールし、ライブラリを使用するようにプロジェクトを構成する方法について説明します

### CocoaPods を使用してインストール

##### CocoaPods のインストール

[CocoaPods](https://cocoapods.org) は、Swift および Objective-C Cocoa プロジェクトの依存関係マネージャーです。 次のコマンドを使用して、Ruby gem としてインストールできます。

```
$ sudo gem install cocoapods
```

>
> Ruby のセットアップによっては、 `sudo` が必要な場合と必要ない場合があります。

##### Podfile に MSAL を含める

**ブラウザー委任認証の場合:**

[CocoaPods](http://cocoapods.org/)を使って、target: の下の`Podfile`に`MSAL`を追加することでインストールできます。

```
use_frameworks!
 
target 'your-target-here' dopod 'MSAL'
end
```

**ネイティブ認証の場合:**

iOS アプリケーションで MSAL によって提供されるネイティブ認証機能を使用するには、次のように`native-auth`依存関係のサブスペックとして`MSAL`を指定する必要があります。

```
use_frameworks!
 
target 'your-target-here' dopod 'MSAL/native-auth'
end
```

`native-auth`サブスペックを使用している場合は、`use_frameworks!`に`Podfile`設定を含める必要があります。

注: MSAL の特定のブランチまたはタグをチェックアウトする場合は、CocoaPods が MSAL 依存関係を検出するように、:submodules =&gt; true フラグをポッドファイルに追加する必要があります

```
pod 'MSAL', :git => 'https://github.com/AzureAD/microsoft-authentication-library-for-objc', :branch => 'dev', :submodules => true
```

その後、 `pod install` (新しい PodFile の場合) または `pod update` (既存の PodFile の場合) を実行して、最新バージョンの MSAL を取得できます。

以降の `pod update` の呼び出しも、MSAL の最新リリース バージョンに更新されます。

PodFile の設定の詳細については、 [CocoaPods](https://guides.cocoapods.org/using/the-podfile.html) を参照してください

### Carthage を使用したインストール

[Carthage](https://github.com/Carthage/Carthage) は、一般的な依存関係マネージャー MSAL でサポートされているもう 1 つの方法です。 Carthage を使用したサンプルを次に示します。

###### iOS、tvOS、または watchOS 用にビルドする場合

1. Web サイトからダウンロードするか、Homebrew `brew install carthage`を使用している場合は、Mac に Carthage をインストールします。
2. Github でこのプロジェクトの MSAL ライブラリを一覧表示する `Cartfile` を作成する必要があります。

```
github "AzureAD/microsoft-authentication-library-for-objc" "dev"
```

注: これにより、Carthage が開発ブランチを指すので、常に最新の公式リリースを入手できます。 特定のバージョンを使用する場合は、次の操作を行うことができます。

```
github "AzureAD/microsoft-authentication-library-for-objc" == <latest_released_version>
```

1. `carthage update` を実行します。 これにより、依存関係が `Carthage/Checkouts` フォルダーにフェッチされ、MSAL ライブラリがビルドされます。
2. アプリケーション ターゲットの [全般] 設定タブの [リンクされたフレームワークとライブラリ] セクションで、ディスク上の `MSAL.framework` フォルダーから`Carthage/Build`をドラッグ アンド ドロップします。
3. アプリケーション ターゲットの [ビルド フェーズ] 設定タブで、[+] アイコンをクリックし、[新しい実行スクリプト フェーズ] を選択します。 シェルを指定する実行スクリプト (例: `/bin/sh`) を作成し、シェルの下のスクリプト領域に次の内容を追加します。

```sh
/usr/local/bin/carthage copy-frameworks
```

「Input Files」の下に、使用するフレームワークのパスを追加します。例:

```
$(SRCROOT)/Carthage/Build/iOS/MSAL.framework
```

このスクリプトは、ユニバーサル バイナリによってトリガーされる [App Store 申請のバグ](http://www.openradar.me/) を回避し、アーカイブ時に必要なビットコード関連のファイルと dSYM が確実にコピーされるようにします。

ビルドされた製品ディレクトリにコピーされたデバッグ情報を使用すると、ブレークポイントで停止するたびに Xcode でスタック トレースをシンボル化できます。 これにより、デバッガーでサードパーティのコードをステップ 実行することもできます。

アプリケーションをアーカイブして App Store または TestFlight に送信する場合、Xcode はこれらのファイルをアプリケーションの `.xcarchive` バンドルの dSYMs サブディレクトリにもコピーします。

### Swift パッケージを使用した MSAL のインストール

`MSAL`を[迅速なパッケージ依存関係](https://developer.apple.com/documentation/swift_packages/distributing_binary_frameworks_as_swift_packages)として追加できます。 MSAL バージョン 1.1.14 以降では、SWIFT パッケージとしての MSAL バイナリ フレームワークの配布を利用できます。

1. Xcode でプロジェクトの場合は、[ファイル] → [Swift パッケージ] → [パッケージ依存関係の追加]をクリックします。
2. 依存関係を追加するプロジェクトを選択する
3. 入力: パッケージ リポジトリの URL としてhttps://github.com/AzureAD/microsoft-authentication-library-for-objc
4. パッケージ オプションを選択:
    1. ルール → ブランチ : メイン (最新の MSAL リリースの場合)
    2. ルール → バージョン → 完全一致: [リリースバージョン &gt;= 1.1.14] (特定のリリースバージョンの場合)

問題が発生した場合は、未処理の SPM/Xcode バグがあるかどうかを確認してください。 発生したいくつかのバグの回避策:

- プロジェクトにプラグインがある場合は、 [CFBundleIdentifier の競合が発生する可能性があります。各バンドルには、一意のバンドル識別子エラーが必要です](https://github.com/AzureAD/microsoft-authentication-library-for-objc/issues/737#issuecomment-767311138) 。 [Workaround](https://github.com/AzureAD/microsoft-authentication-library-for-objc/issues/737#issuecomment-767990771)
- アーカイブ中、エラー: "IPA 処理に失敗しました" UserInfo={NSLocalizedDescription=IPA processing failed}。 [Workaround](https://github.com/AzureAD/microsoft-authentication-library-for-objc/issues/737#issuecomment-767990771)
- macOS アプリの場合、"Command CodeSign が 0 以外の終了コードで失敗しました" というエラーが発生しました。 [Workaround](https://github.com/AzureAD/microsoft-authentication-library-for-objc/issues/737#issuecomment-770056675)

### Git サブモジュールを使用して MSAL をインストールする

プロジェクトが Git リポジトリで管理されている場合は、MSAL を git サブモジュールとして含めることができます。 まず、GitHub リリース ページで最新のリリース タグを確認します。 &lt;latest\_release\_tag&gt;をそのバージョンに置き換えます。

- `git submodule add https://github.com/AzureAD/microsoft-authentication-library-for-objc msal`
- `cd msal`
- `git checkout tags/<latest_release_tag>`
- `git submodule update --init --recursive`
- `cd ..`
- `git add msal`
- `git commit -m "Use MSAL git submodule at <latest_release_tag>"`
- `git push`

### MSAL を使用するようにプロジェクトを構成する

#### プロジェクトへの MSAL の追加

1. [Microsoft Entra管理センター](https://entra.microsoft.com/signin/index/)でアプリを登録します。
2. アプリケーションのリダイレクト URI を登録してください。 次の形式にする必要があります。

`msauth.$(PRODUCT_BUNDLE_IDENTIFIER)://auth`

1. 新しいキーチェーン グループをプロジェクトの機能に追加します。 キーチェーン グループは、iOS で `com.microsoft.adalcache` し、macOS で `com.microsoft.identity.universalstorage` する必要があります。

[キーチェーン グループ](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-v2-keychain-objc)と [MSAL のサイレント SSO](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-macos-ios) の詳細を参照してください。

##### iOS のみの手順:

1. アプリケーションのリダイレクト URI スキームを `Info.plist` ファイルに追加する

```xml
<key>CFBundleURLTypes</key>
<array>
    <dict>
        <key>CFBundleURLSchemes</key>
        <array>
            <string>msauth.$(PRODUCT_BUNDLE_IDENTIFIER)</string>
        </array>
    </dict>
</array>
```

1. `LSApplicationQueriesSchemes`を追加して、インストールされている場合はMicrosoft Authenticatorを呼び出せるようにします。

Xcode 11 以降でアプリをコンパイルする場合は、"msauthv3" スキームが必要であることに注意してください。

```xml
<key>LSApplicationQueriesSchemes</key>
<array><string>msauthv2</string><string>msauthv3</string>
</array>
```

[MSAL のリダイレクト URI の構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url)について詳しくは、以下をご覧ください。

1. コールバックを処理するには、次を `appDelegate`に追加します。

##### Swift

```swift
func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        return MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: options[UIApplication.OpenURLOptionsKey.sourceApplication] as? String)
}
```

##### Objective-C

```obj
- (BOOL)application:(UIApplication *)app
            openURL:(NSURL *)url
            options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options
{
    return [MSALPublicClientApplication handleMSALResponse:url 
                                         sourceApplication:options[UIApplicationOpenURLOptionsSourceApplicationKey]];
}
```

**iOS 13 以降で UISceneDelegate を採用した場合**は、APPDelegate ではなく UISceneDelegate の適切なデリゲート メソッドに MSAL コールバックを配置する必要があることに注意してください。 MSAL `handleMSALResponse:sourceApplication:` の呼び出しは URL ごとに 1 回のみにする必要があります。 以前の iOS との互換性を保持するために UISceneDelegate と UIApplicationDelegate の両方をサポートしている場合は、MSAL コールバックを両方のファイルに配置する必要があります。

##### Swift

```swift
func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        
        guard let urlContext = URLContexts.first else {
            return
        }
        
        let url = urlContext.url
        let sourceApp = urlContext.options.sourceApplication
        
        MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: sourceApp)
    }
```

##### Objective-C

```objective
- (void)scene:(UIScene *)scene openURLContexts:(NSSet<UIOpenURLContext *> *)URLContexts
{
    UIOpenURLContext *context = URLContexts.anyObject;
    NSURL *url = context.URL;
    NSString *sourceApplication = context.options.sourceApplication;
    
    [MSALPublicClientApplication handleMSALResponse:url sourceApplication:sourceApplication];
}
```

##### macOS のみの手順:

1. アプリケーションが有効な開発証明書で署名されていることを確認します。 MSAL は引き続き符号なしモードで動作しますが、キャッシュの永続化に関して動作が異なります。

#### Apple デバイス用の Microsoft Enterprise SSO プラグイン

Microsoftは最近、[Enterprise Single Sign-On](https://developer.apple.com/documentation/authenticationservices) と呼ばれる新しく発表された Apple 機能を使用する新しいプラグインをリリースしました。 Apple デバイス用の Microsoft Enterprise SSO プラグインには、次の利点があります。

- Microsoft Authenticatorアプリで自動的に配信され、任意の MDM で有効にすることができます。
- Apple の Enterprise Single Sign-On 機能をサポートするすべてのアプリケーションで、Active Directory参加済みアカウントにシームレスな SSO を提供します。
- 近日公開予定: デバイス上の Safari ブラウザーとアプリケーション間でシームレスな SSO を提供します。

MSAL 1.1.0 以降では、デバイス上で Microsoft Enterprise SSO プラグインが有効になっている場合、Microsoft Authenticator アプリの代わりに自動的にそれが使用されます。 テナントで Microsoft Enterprise SSO プラグインを使用するには、MDM プロファイルで有効にする必要があります。

デバイスの Microsoft Enterprise SSO プラグインの構成の[詳細については](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)、[こちらを参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)してください

#### 単一アカウント モード

アプリで一度に 1 人のサインイン ユーザーのみをサポートする必要がある場合、MSAL はサインインしたアカウントを簡単に読み取る方法を提供します。 この API は、共有デバイスとして構成されているデバイスで実行するアプリケーションを構築する場合にも使用する必要があります。つまり、1 つの企業デバイスが複数の従業員間で共有されます。 従業員は自分のデバイスにサインインし、顧客情報にすばやくアクセスできます。 シフトまたはタスクが完了すると、共有デバイス上のすべてのアプリからサインアウトできるようになります。

現在のアカウントを取得する方法を示すコード スニペットを次に示します。 アプリがフォアグラウンドになるたびに、または機密性の高い操作を実行する前に API を呼び出して、サインインしているアカウントの変更を検出する必要があります。

##### Swift

```swift
let msalParameters = MSALParameters()
msalParameters.completionBlockQueue = DispatchQueue.main
                
application.getCurrentAccount(with: msalParameters, completionBlock: { (currentAccount, previousAccount, error) in
            // currentAccount is the currently signed in account// previousAccount is the previously signed in account if any
})
```

##### Objective-C

```objective
MSALParameters *parameters = [MSALParameters new];
parameters.completionBlockQueue = dispatch_get_main_queue();
        
[application getCurrentAccountWithParameters:parameters
                             completionBlock:^(MSALAccount * _Nullable account, MSALAccount * _Nullable previousAccount, NSError * _Nullable error)
{// currentAccount is the currently signed in account// previousAccount is the previously signed in account if any
}];
```

#### 複数アカウント モード

MSAL には、MSAL キャッシュに存在することを許可された複数のアカウントに対してクエリを実行するパブリック API も用意されています。

1. アンブレラ ヘッダー MSAL-umbrella.h がインポートされていることを確認します (Swift 用の MSAL のみ)
2. 構成を作成し、それを使用してアプリケーション オブジェクトを初期化する
3. また、アカウント識別子を使用して 'MSALAccountEnumerationParameters' オブジェクトを初期化します。 各 MSALAccount オブジェクトには、"identifier" という名前のパラメーターがあります。これは、指定された MSALAccount オブジェクトに関連付けられている一意のアカウント識別子を表します。 主要な検索条件として使用することをお勧めします。
4. 次に、列挙パラメーターを使用して、アプリケーション オブジェクトから API "accountsFromDeviceForParameters" を呼び出します。 MSAL キャッシュに複数のアカウントがある場合は、前の手順で指定したアカウント識別子を持つ MSALAccounts を含む配列が返されます。
5. MSAL アカウントが取得されたら、トークンの取得サイレント操作を呼び出します

##### Swift

```swift
#import MSAL //Make sure to import MSAL  

let config = MSALPublicClientApplicationConfig(clientId:clientId
                                           	redirectUri:redirectUri
                                            	authority:authority)
guard let application = MSALPublicClientApplication(configuration: config) else { return }

let accountIdentifier = "9f4880d8-80ba-4c40-97bc-f7a23c703084.f645ad92-e38d-4d1a-b510-d1b09a74a8ca"
let parameters = MSALAccountEnumerationParameters(identifier:accountIdentifier)

var scopeArr = ["https://graph.microsoft.com/.default"]

if #available(macOS 10.15, *)
{ application.accountsFromDeviceForParameters(with: parameters, completionBlock:{(accounts, error) in
         if let error = error 
         {
            //Handle error
         }
         
         guard let accountObjs = accounts else {return}
         
         let tokenParameters = MSALSilentTokenParameters(scopes:scopeArr, account: accountObjs[0]);
                                                                                                   
         application.acquireTokenSilentWithParameters(with: tokenParameters, completionBlock:{(result, error) in 
                     if let error = error
                     {
                         //handle error
                     }
                                       
                     guard let resp = result else {return} //process result
                                                                                             
         })                                                               
                                                                                                                                                             
   })
  
}
```

##### Objective-C

```objective
//import other key libraries  
#import "MSAL-umbrella.h" //Make sure to import umbrella file 

    MSALPublicClientApplicationConfig *config = [[MSALPublicClientApplicationConfig alloc] initWithClientId:clientId
     redirectUri:redirectUri
       authority:authority];

    MSALPublicClientApplication *application = [[MSALPublicClientApplication alloc] initWithConfiguration:config error:&error];
    MSALAccountEnumerationParameters *parameters = [[MSALAccountEnumerationParameters alloc] initWithIdentifier:@"9f4880d8-80ba-4c40-97bc-f7a23c703084.f645ad92-e38d-4d1a-b510-d1b09a74a8ca"]; //init with account identifier

    NSArray<NSString *> *scopeArr = [[NSArray alloc] initWithObjects: @"https://graph.microsoft.com/.default",nil]; //define scope

    if (@available(macOS 10.15, *)) //Currently, this public API requires macOs version 10.15 or greater.
    {
        [application accountsFromDeviceForParameters:parameters
                                     completionBlock:^(NSArray<MSALAccount *> * _Nullable accounts, __unused NSError * _Nullable error)
        {
            if (error)
            {
              //Log error & return 
            }
          
            if (accounts)
            {
                NSLog(@"hi there");
                MSALSilentTokenParameters *tokenParameters = [[MSALSilentTokenParameters alloc] initWithScopes:scopeArr account:accounts[0]];

                [application acquireTokenSilentWithParameters:tokenParameters
                                completionBlock:^(MSALResult * _Nullable result, NSError * _Nullable error)
                 {
                    if (error)
                    {
                        //Log Error & return 
                    }
                    if (result)
                    {
                        //process result
                    }
                }
                 ];
            }
     
        }];
    }
```

#### 共有デバイス モードを検出する

次のコードを使用して、デバイスが共有として構成されているかどうかなど、現在のデバイス構成を読み取ります。

##### Swift

```swift
application.getDeviceInformation(with: nil, completionBlock: { (deviceInformation, error) in
                guard let deviceInfo = deviceInformation else {	return}
                let isSharedDevice = deviceInfo.deviceMode == .shared// Change your app UX if needed
})
```

##### Objective-C

```objective
[application getDeviceInformationWithParameters:nil
                                completionBlock:^(MSALDeviceInformation * _Nullable deviceInformation, NSError * _Nullable error)
{if (!deviceInformation){	return;}
            BOOL isSharedDevice = deviceInformation.deviceMode == MSALDeviceModeShared;// Change your app UX if needed
}];
```

#### サインアウトを実装する

アプリからアカウントをサインアウトするには、MSAL のサインアウト API を呼び出します。 必要に応じて、ブラウザーからサインアウトすることもできます。 MSAL が共有デバイスで実行されている場合、サインアウト API はユーザーのデバイス上のすべてのアプリからグローバルにサインアウトします。

##### Swift

```swift
let account = .... /* account retrieved above */

let signoutParameters = MSALSignoutParameters(webviewParameters: self.webViewParameters!)
signoutParameters.signoutFromBrowser = false
            
application.signout(with: account, signoutParameters: signoutParameters, completionBlock: {(success, error) in
                if let error = error {	// Signout failed	return}
                // Sign out completed successfully
})
```

##### Objective-C

```objective
MSALAccount *account = ... /* account retrieved above */;
        
MSALSignoutParameters *signoutParameters = [[MSALSignoutParameters alloc] initWithWebviewParameters:webViewParameters];
signoutParameters.signoutFromBrowser = NO;
        
[application signoutWithAccount:account signoutParameters:signoutParameters completionBlock:^(BOOL success, NSError * _Nullable error)
{if (!success){	// Signout failed	return;}
            // Sign out completed successfully
}];
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/ios-13-and-macos-10.15-support-in-msal"} -->
## MSAL での iOS 13 のサポート

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/ios-13-and-macos-10.15-support-in-msal
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: MSAL での iOS 13 のサポートについて説明します

アプリで条件付きアクセスまたは証明書認証のサポートが必要な場合は、Azure Authenticator アプリと通信できるように MSAL を設定する必要があります。

MSAL は、アプリケーションと Azure Authenticator アプリの間の要求と応答を処理する役割を担います。

ただし、iOS 13 では、Apple は破壊的な API の変更を行い、カスタム URL スキームを使用して外部アプリケーションから応答を受信するときにソース アプリケーションを読み取るアプリケーションの機能を削除しました。 Apple のノート [はこちらをご覧ください](https://developer.apple.com/documentation/uikit/uiapplicationopenurloptionssourceapplicationkey?language=objc)。

要求がチームに属する別のアプリから送信された場合、UIKit は、このキーの値をそのアプリの ID に設定します。 元のアプリのチーム識別子が現在のアプリのチーム識別子と異なる場合、キーの値は nil です。

MSAL と Azure Authenticator アプリの間の通信を検証するために`UIApplicationOpenURLOptionsSourceApplicationKey`に依存していたため、これは MSAL にとって重大な変更です。

さらに、iOS 13 の開発者は、ASWebAuthenticationSession を使用するときにプレゼンテーション コントローラーを提供する必要があります。

これらの変更を軽減するために、iOS 13 をサポートする新しい MSAL バージョンをリリースしました。

- [MSAL 0.7.0](https://github.com/AzureAD/microsoft-authentication-library-for-objc/releases/tag/0.7.0)

#### アプリは、次の場合に影響を受けます。

1. お使いのアプリは iOS ブローカーを利用しており、かつ Xcode 11 でビルドしている、または
2. ASWebAuthenticationSession を使用しており、Xcode 11 を使用してビルドしています。

このような場合は、認証を正常に完了できるように、最新の MSAL リリースを使用する必要があります。

#### 次の場合、アプリは影響を受けません。

1. アプリで iOS ブローカーを使用していない、または
2. アプリは Xcode 11、OR を使用してビルドされています
3. お客様のアプリは Microsoft によって配布される（Microsoft の開発者配布プロファイルで署名されている）、または
4. ASWebAuthenticationSession を使用していません。

#### その他の考慮事項:

1. 最新の MSAL SDK を使用する場合は、最新の Authenticator アプリがインストールされていることを確認する必要があります。 バージョン 6.3.19 以降の認証アプリがサポートされています。
2. このリリースに更新するときは、Info.plist で LSApplicationQueriesSchemes を更新してください。 新しい値は次のようになります。

```
<key>LSApplicationQueriesSchemes</key>
<array>
     <string>msauthv2</string>
     <string>msauthv3</string>
</array>
```

これは、iOS 13 をサポートするデバイス上に最新の Authenticator アプリが存在することを検出するために必要です。

追加の質問がある場合や問題が発生した場合は、 [GitHub](https://github.com/AzureAD/microsoft-authentication-library-for-objc/issues) の問題を開いてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/logging-ios"} -->
## iOS/macOS 用 MSAL でのエラーと例外のログ記録

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/logging-ios
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: iOS/macOS 用 MSAL でエラーと例外をログに記録する方法について説明します

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

## [Objective-C](#tab/objc)
### iOS および macOS 用の MSAL でのログ記録 - ObjC

MSAL ログをキャプチャし、独自のアプリケーションのログ記録に組み込むコールバックを設定します。 コールバックのシグネチャは次のようになります。

```objc
/*!
    The LogCallback block for the MSAL logger

    @param  level           The level of the log message
    @param  message         The message being logged
    @param  containsPII     If the message might contain Personally Identifiable Information (PII)
                            this will be true. Log messages possibly containing PII will not be
                            sent to the callback unless PIllLoggingEnabled is set to YES on the
                            logger.

 */
typedef void (^MSALLogCallback)(MSALLogLevel level, NSString *message, BOOL containsPII);
```

例えば次が挙げられます。

```objc
[MSALGlobalConfig.loggerConfig setLogCallback:^(MSALLogLevel level, NSString *message, BOOL containsPII)
    {
        if (!containsPII)
        {
#if DEBUG
            // IMPORTANT: MSAL logs may contain sensitive information. Never output MSAL logs with NSLog, or print, directly unless you're running your application in debug mode. If you're writing MSAL logs to file, you must store the file securely.
            NSLog(@"MSAL log: %@", message);
#endif
        }
    }];
```

#### 個人データ

既定では、MSAL は個人データをキャプチャまたはログに記録しません。 このライブラリを使用すると、アプリ開発者は MSALLogger クラスのプロパティを使用してこれを有効にすることができます。 `pii.Enabled`を有効にすると、アプリは機密性の高いデータを安全に処理し、規制要件に従う責任を負います。

```objc
// By default, the `MSALLogger` doesn't capture any PII

// PII will be logged
MSALGlobalConfig.loggerConfig.piiEnabled = YES;

// PII will NOT be logged
MSALGlobalConfig.loggerConfig.piiEnabled = NO;
```

#### ログ記録のレベル

iOS および macOS 用の MSAL を使用してログ記録を行うときにログ 記録レベルを設定するには、次のいずれかの値を使用します。

| Level | 説明 |
| --- | --- |
| `MSALLogLevelNothing` | すべてのログ記録を無効にする |
| `MSALLogLevelError` | 既定のレベル。エラーが発生した場合にのみ情報が出力されます |
| `MSALLogLevelWarning` | Warnings |
| `MSALLogLevelInfo` | パラメーターとさまざまなキーチェーン操作を含むライブラリ エントリ ポイント |
| `MSALLogLevelVerbose` | API トレース |

例えば次が挙げられます。

```objc
MSALGlobalConfig.loggerConfig.logLevel = MSALLogLevelVerbose;
```

#### ログ メッセージの形式

MSAL ログ メッセージのメッセージ部分は、次の形式になります。 `TID = <thread_id> MSAL <sdk_ver> <OS> <OS_ver> [timestamp - correlation_id] message`

例えば次が挙げられます。

`TID = 551563 MSAL 0.2.0 iOS Sim 12.0 [2018-09-24 00:36:38 - 36764181-EF53-4E4E-B3E5-16FE362CFC44] acquireToken returning with error: (MSALErrorDomain, -42400) User cancelled the authorization session.`

関連付け ID とタイムスタンプを指定すると、問題を追跡するのに役立ちます。 タイムスタンプと関連付け ID の情報は、ログ メッセージで確認できます。 それらを取得する唯一の信頼性の高い場所は、MSAL ログ メッセージからです。

## [速い](#tab/swift)
### iOS および macOS 向け MSAL のログ記録 - Swift

MSAL ログをキャプチャし、独自のアプリケーションのログ記録に組み込むコールバックを設定します。 コールバックのシグネチャ (Objective-Cで表されます) は次のようになります。

```objc
/*!
    The LogCallback block for the MSAL logger

    @param  level           The level of the log message
    @param  message         The message being logged
    @param  containsPII     If the message might contain Personally Identifiable Information (PII)
                            this will be true. Log messages possibly containing PII will not be
                            sent to the callback unless PIllLoggingEnabled is set to YES on the
                            logger.

 */
typedef void (^MSALLogCallback)(MSALLogLevel level, NSString *message, BOOL containsPII);
```

例えば次が挙げられます。

```swift
MSALGlobalConfig.loggerConfig.setLogCallback { (level, message, containsPII) in
    if let message = message, !containsPII
    {
#if DEBUG
    // IMPORTANT: MSAL logs may contain sensitive information. Never output MSAL logs with NSLog, or print, directly unless you're running your application in debug mode. If you're writing MSAL logs to file, you must store the file securely.
    print("MSAL log: \(message)")
#endif
    }
}
```

#### 個人データ

既定では、MSAL は個人データをキャプチャまたはログに記録しません。 このライブラリを使用すると、アプリ開発者は MSALLogger クラスのプロパティを使用してこれを有効にすることができます。 `pii.Enabled`を有効にすると、アプリは機密性の高いデータを安全に処理し、規制要件に従う責任を負います。

```swift
// By default, the `MSALLogger` doesn't capture any PII

// PII will be logged
MSALGlobalConfig.loggerConfig.piiEnabled = true

// PII will NOT be logged
MSALGlobalConfig.loggerConfig.piiEnabled = false
```

#### ログ記録のレベル

iOS および macOS 用の MSAL を使用してログ記録を行うときにログ 記録レベルを設定するには、次のいずれかの値を使用します。

| Level | 説明 |
| --- | --- |
| `MSALLogLevelNothing` | すべてのログ記録を無効にする |
| `MSALLogLevelError` | 既定のレベル。エラーが発生した場合にのみ情報が出力されます |
| `MSALLogLevelWarning` | Warnings |
| `MSALLogLevelInfo` | パラメーターとさまざまなキーチェーン操作を含むライブラリ エントリ ポイント |
| `MSALLogLevelVerbose` | API のトレース |

例えば次が挙げられます。

```swift
MSALGlobalConfig.loggerConfig.logLevel = .verbose
```

#### ログ メッセージの形式

MSAL ログ メッセージのメッセージ部分は、次の形式になります。 `TID = <thread_id> MSAL <sdk_ver> <OS> <OS_ver> [timestamp - correlation_id] message`

例えば次が挙げられます。

`TID = 551563 MSAL 0.2.0 iOS Sim 12.0 [2018-09-24 00:36:38 - 36764181-EF53-4E4E-B3E5-16FE362CFC44] acquireToken returning with error: (MSALErrorDomain, -42400) User cancelled the authorization session.`

関連付け ID とタイムスタンプを指定すると、問題を追跡するのに役立ちます。 タイムスタンプと関連付け ID の情報は、ログ メッセージで確認できます。 それらを取得する唯一の信頼性の高い場所は、MSAL ログ メッセージからです。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/migrate-objc-adal-msal"} -->
## iOS および macOS 用の ADAL から MSAL への移行ガイド

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/migrate-objc-adal-msal
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: iOS/macOS 用 MSAL と Objective-C 用 Azure AD 認証ライブラリ (ADAL) の違いについて説明します。ObjC) と iOS/macOS 用 MSAL に移行する方法。

Azure Active Directory認証ライブラリ ([ADAL Objective-C](https://github.com/AzureAD/azure-activedirectory-library-for-objc)) は、v1.0 エンドポイント経由でMicrosoft Entra アカウントを操作するために作成されました。

iOS および macOS 向け Microsoft Authentication Library（MSAL）は、Microsoft ID プラットフォーム（旧称 Azure AD v2.0 エンドポイント）を介して、Microsoft Entra アカウント、個人用 Microsoft アカウント、Azure AD B2C アカウントなど、Microsoft のあらゆる ID で動作するように設計されています。

Microsoft ID プラットフォームには、Azure AD v1.0 との主な違いがいくつかあります。 この記事では、これらの違いについて説明し、アプリを ADAL から MSAL に移行するためのガイダンスを提供します。

### ADAL と MSAL アプリの機能の違い

#### サインインできるユーザー

- ADAL では、職場と学校のアカウント (Microsoft Entra アカウントとも呼ばれます) のみがサポートされます。
- MSAL では、Hotmail.com、Outlook.com、Live.com などの個人用Microsoft アカウント (MSA アカウント) がサポートされます。
- MSAL では、職場と学校のアカウント、および AD B2C アカウントAzureがサポートされています。

#### 標準へのコンプライアンス

- Microsoft ID プラットフォームは、OAuth 2.0 と OpenId Connect の標準に従います。

#### 段階的同意と動的同意

- Microsoft ID プラットフォームを使用すると、アクセス許可を動的に要求できます。 アプリは必要に応じてのみアクセス許可を要求し、アプリで必要に応じてさらに要求できます。 詳細については、「 [アクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#consent)」を参照してください。

### ADAL ライブラリと MSAL ライブラリの違い

MSAL パブリック API には、Azure AD v1.0 とMicrosoft ID プラットフォームの主な違いがいくつか反映されています。

#### ADAuthenticationContext の代わりに MSALPublicClientApplication

`ADAuthenticationContext` は、ADAL アプリが作成する最初のオブジェクトです。 これは、ADAL のインスタンス化を表します。 アプリは、Microsoft Entraクラウドとテナント (機関) の組み合わせごとに、`ADAuthenticationContext`の新しいインスタンスを作成します。 同じ `ADAuthenticationContext` を使用して、複数のパブリック クライアント アプリケーションのトークンを取得できます。

MSAL では、主な相互作用は、`MSALPublicClientApplication`の後にモデル化された オブジェクトを介して行われます。 `MSALPublicClientApplication`の 1 つのインスタンスを使用して、複数のMicrosoft Entra クラウドとテナントを操作できます。各機関に新しいインスタンスを作成する必要はありません。 ほとんどのアプリでは、1 つの `MSALPublicClientApplication` インスタンスで十分です。

#### リソースではなくスコープ

ADAL では、アプリは、Azure AD v1.0 エンドポイントからトークンを取得するために、などの`https://graph.microsoft.com`識別子を提供する必要がありました。 リソースでは、アプリ マニフェストで認識されるスコープ (oAuth2Permissions) を多数定義できます。 これにより、クライアント アプリは、アプリの登録時に事前に定義された特定のスコープ セットに対して、そのリソースからトークンを要求することができました。

MSAL では、単一のリソース識別子ではなく、アプリは要求ごとに一連のスコープを提供します。 スコープは、resource/permission 形式で、リソース識別子の後にアクセス許可名が続くものです。 たとえば、`https://graph.microsoft.com/user.read` のように指定します。

MSAL でスコープを提供するには、次の 2 つの方法があります。

- アプリに必要なすべてのアクセス許可の一覧を指定します。 例えば次が挙げられます。

    `@[@"https://graph.microsoft.com/directory.read", @"https://graph.microsoft.com/directory.write"]`

    この場合、アプリは `directory.read` と `directory.write` のアクセス許可を要求します。 ユーザーは、このアプリでそれらの権限に以前同意していない場合、それらの権限への同意を求められます。 アプリケーションは、ユーザーがアプリケーションに対して既に同意している追加のアクセス許可を受け取る場合もあります。 ユーザーは、新しいアクセス許可または付与されていないアクセス許可に対してのみ同意を求められます。
- その `/.default` スコープ。

これは、すべてのアプリケーションの組み込みスコープです。 これは、アプリケーションの登録時に構成されたアクセス許可の静的リストを参照します。 その動作は、 `resource`の動作と似ています。 これは、同様のスコープとユーザー エクスペリエンスのセットが維持されるように移行する場合に役立ちます。

`/.default` スコープを使用するには、リソース識別子に`/.default`を追加します。 たとえば、 `https://graph.microsoft.com/.default`と指定します。 リソースがスラッシュ (`/`) で終わる場合でも、先頭のフォワード スラッシュを含む `/.default` を追加する必要があります。その結果、スコープ内に二重フォワード スラッシュ (`//`) が含まれることになります。

[アクセス許可と](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc)スコープで "/.default" スコープを使用する方法の詳細を確認できます。

#### さまざまな WebView の種類とブラウザーのサポート

ADAL では、iOS の場合は UIWebView/WKWebView、macOS の場合は WebView のみがサポートされます。 iOS 用 MSAL では、承認コードを要求するときに Web コンテンツを表示するためのより多くのオプションがサポートされ、ユーザー エクスペリエンスとセキュリティを向上させる `UIWebView`はサポートされなくなりました。

既定では、iOS 上の MSAL では [ASWebAuthenticationSession](https://developer.apple.com/documentation/authenticationservices/aswebauthenticationsession?language=objc) が使用されます。これは、Apple が iOS 12 以降のデバイスでの認証に推奨する Web コンポーネントです。 アプリと Safari ブラウザー間で Cookie を共有することで、シングル サインオン (SSO) の利点が得られます。

アプリの要件と必要なエンドユーザー エクスペリエンスに応じて、異なる Web コンポーネントを使用することを選択できます。 その他のオプションについては、 [サポートされている Web ビューの種類](https://learn.microsoft.com/ja-jp/entra/msal/objc/customize-webviews) を参照してください。

ADAL から MSAL に移行する場合、 `WKWebView` は iOS および macOS 上の ADAL と最も似たユーザー エクスペリエンスを提供します。 可能であれば、iOS の `ASWebAuthenticationSession` に移行することをお勧めします。 macOS の場合は、 `WKWebView`を使用することをお勧めします。

#### アカウント管理 API の相違点

ADAL メソッド`acquireToken()`または`acquireTokenSilent()`を呼び出すと、認証されるアカウントを表す`ADUserInformation`からの要求の一覧を含む`id_token` オブジェクトを受け取ります。 さらに、`ADUserInformation`は、`userId`要求に基づいて`upn`を返します。 最初の対話型トークンの取得後、ADAL は開発者がすべてのサイレント呼び出しで `userId` を提供することを想定しています。

ADAL には、既知のユーザー ID を取得するための API は用意されていません。 これらのアカウントの保存と管理は、アプリに依存します。

MSAL には、トークンを取得しなくても MSAL に知られているすべてのアカウントを一覧表示する一連の API が用意されています。

ADAL と同様に、MSAL は、 `id_token`からの要求の一覧を保持するアカウント情報を返します。 これは、`MSALAccount` オブジェクト内の`MSALResult` オブジェクトの一部です。

MSAL には、アカウントを削除するための一連の API が用意されており、削除されたアカウントはアプリからアクセスできなくなります。 アカウントが削除されると、後でトークン取得の呼び出しが行われると、対話型のトークン取得を実行するようにユーザーに求められます。 アカウントの削除は、アカウントを起動したクライアント アプリケーションにのみ適用され、デバイスまたはシステム ブラウザーで実行されている他のアプリからアカウントが削除されることはありません。 これにより、個々のアプリからサインアウトした後でも、ユーザーがデバイスで SSO エクスペリエンスを維持し続けられます。

さらに、MSAL は、後でトークンを暗黙的に要求するために使用できるアカウント識別子も返します。 ただし、アカウント識別子 (`identifier` オブジェクトの `MSALAccount` プロパティを介してアクセス可能) は表示できず、どの形式にあるか想定することも、解釈や解析を試みる必要もありません。

#### アカウント キャッシュの移行

ADAL から移行する場合、アプリは通常、MSAL で必要な`userId`を持たない ADAL の`identifier`を格納します。 1 回限りの移行手順として、アプリは次の API で ADAL の userId を使用して MSAL アカウントに対してクエリを実行できます。

`- (nullable MSALAccount *)accountForUsername:(nonnull NSString *)username error:(NSError * _Nullable __autoreleasing * _Nullable)error;`

この API は、MSAL と ADAL の両方のキャッシュを読み取り、ADAL userId (UPN) によってアカウントを検索します。

アカウントが見つかった場合、開発者はアカウントを使用してサイレント トークンの取得を行う必要があります。 最初のサイレント トークンの取得では、アカウントが効果的にアップグレードされ、開発者は MSAL の結果 (`identifier`) で MSAL と互換性のあるアカウント識別子を取得します。 その後、次の API を使用して、アカウント参照に `identifier` のみを使用する必要があります。

`- (nullable MSALAccount *)accountForIdentifier:(nonnull NSString *)identifier error:(NSError * _Nullable __autoreleasing * _Nullable)error;`

MSAL のすべての操作で ADAL の `userId` を引き続き使用することは可能ですが、`userId` は UPN に基づいているため、ユーザー エクスペリエンスの低下につながる複数の制限を受けます。 たとえば、UPN が変更された場合、ユーザーはもう一度サインインする必要があります。 すべてのアプリで、すべての操作に表示できないアカウント `identifier` を使用することをお勧めします。

[キャッシュ状態の移行](https://learn.microsoft.com/ja-jp/entra/msal/objc/sso-between-adal-msal-apps)について詳しくは、こちらをご覧ください。

#### トークン取得の変更

MSAL では、いくつかのトークン取得呼び出しの変更が導入されています。

- ADAL と同様に、 `acquireTokenSilent` は常にサイレント要求になります。
- ADAL とは異なり、`acquireToken`は常に、Web ビューまたはMicrosoft Authenticator アプリを介してユーザーが操作可能な UI になります。 webview/Microsoft Authenticator 内の SSO の状態によっては、ユーザーに資格情報の入力を求められる場合があります。
- ADAL では、`acquireToken`を使用した`AD_PROMPT_AUTO`は最初にサイレント トークンの取得を試み、サイレント要求が失敗した場合にのみ UI を表示します。 MSAL では、このロジックを実現するには、最初に `acquireTokenSilent` を呼び出し、サイレント取得が失敗した場合にのみ `acquireToken` を呼び出します。 これにより、開発者は対話型トークンの取得を開始する前にユーザー エクスペリエンスをカスタマイズできます。

#### エラー処理の違い

MSAL を使用すると、アプリで処理できるエラーと、ユーザーによる介入が必要なエラーの間でより明確になります。 開発者が処理する必要があるエラーの数は限られています。

- `MSALErrorInteractionRequired`: ユーザーは対話型の要求を行う必要があります。 これは、認証セッションの期限切れ、条件付きアクセス ポリシーの変更、更新トークンの有効期限が切れた、取り消された、キャッシュに有効なトークンがないなどのさまざまな理由で発生する可能性があります。
- `MSALErrorServerDeclinedScopes`: 要求が完全に完了せず、一部のスコープにアクセス権が付与されませんでした。 これは、ユーザーが 1 つ以上のスコープへの同意を拒否した場合に発生する可能性があります。

[`MSALError` リスト](https://github.com/AzureAD/microsoft-authentication-library-for-objc/blob/master/MSAL/src/public/MSALError.h#L128)内の他のすべてのエラーの処理は省略可能です。 これらのエラーの情報を使用して、ユーザー エクスペリエンスを向上させることができます。

[MSAL エラー処理の詳細については、MSAL を使用した例外と](https://learn.microsoft.com/ja-jp/entra/msal/objc/error-handling-ios)エラーの処理を参照してください。

#### ブローカーのサポート

バージョン 0.3.0 以降の MSAL では、Microsoft Authenticator アプリを使用したブローカー認証のサポートが提供されます。 Microsoft Authenticatorでは、条件付きアクセス シナリオのサポートも有効になります。 条件付きアクセスシナリオの例としては、ユーザーが Intune を介してデバイスを登録するか、トークンを取得するためにMicrosoft Entra IDに登録する必要があるデバイス コンプライアンス ポリシーが含まれます。 モバイル アプリケーション管理 (MAM) 条件付きアクセス ポリシー。アプリでトークンを取得するには、コンプライアンスの証明が必要です。

アプリケーションのブローカーを有効にするには:

1. アプリケーションのブローカー互換リダイレクト URI 形式を登録します。 ブローカー互換のリダイレクト URI 形式が `msauth.<app.bundle.id>://auth`。 `<app.bundle.id>`をアプリケーションのバンドル ID に置き換えます。 ADAL から移行していて、アプリケーションが既にブローカー対応であった場合は、追加の操作は必要ありません。 前のリダイレクト URI は MSAL と完全に互換性があるため、手順 3 に進むことができます。
2. アプリケーションのリダイレクト URI スキームを info.plist ファイルに追加します。 既定の MSAL リダイレクト URI の場合、形式は `msauth.<app.bundle.id>`。 例えば次が挙げられます。

    ```xml
    <key>CFBundleURLSchemes</key>
    <array>
        <string>msauth.<app.bundle.id></string>
    </array>
    ```
3. 次のスキームをアプリの Info.plist の LSApplicationQueriesSchemes の下に追加します。

    ```xml
    <key>LSApplicationQueriesSchemes</key>
    <array>
         <string>msauthv2</string>
         <string>msauthv3</string>
    </array>
    ```
4. コールバックを処理するために AppDelegate.m ファイルに次のコードを追加します:Objective-C:

    ```objc
    - (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<NSString *,id> *)options`
    {
        return [MSALPublicClientApplication handleMSALResponse:url sourceApplication:options[UIApplicationOpenURLOptionsSourceApplicationKey]];
    }
    ```

    Swift:

    ```swift
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        return MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: options[UIApplication.OpenURLOptionsKey.sourceApplication] as? String)
    }
    ```

#### 企業間 (B2B)

ADAL では、アプリがトークンを要求するテナントごとに、 `ADAuthenticationContext` の個別のインスタンスを作成します。 これは MSAL での要件ではなくなりました。 MSAL では、acquireToken 呼び出しと acquireTokenSilent 呼び出しに対して別の権限を指定することで、`MSALPublicClientApplication`の単一のインスタンスを作成し、任意のMicrosoft Entraクラウドおよび組織に使用できます。

### 他の SDK とのパートナーシップでの SSO

iOS 用 MSAL では、ADAL Objective-C 2.7.x 以降で統合キャッシュを介して SSO を実現できます。

SSO は iOS キーチェーン共有を介して実現され、同じ Apple Developer アカウントから発行されたアプリ間でのみ使用できます。

iOS キーチェーン共有を介した SSO は、唯一のサイレント SSO の種類です。

macOS では、MSAL は iOS および macOS ベースのアプリケーションと ADAL Objective-C ベースのアプリケーション用の他の MSAL との SSO を実現できます。

iOS 上の MSAL では、他の 2 種類の SSO もサポートされています。

- Web ブラウザーを使用した SSO。 MSAL for iOS では、 `ASWebAuthenticationSession`がサポートされています。これにより、デバイス上の他のアプリと特に Safari ブラウザー間で共有される Cookie を介して SSO が提供されます。
- 認証ブローカーを介した SSO。 iOS デバイスでは、Microsoft Authenticatorは認証ブローカーとして機能します。 準拠デバイスの要求などの条件付きアクセス ポリシーに従い、登録済みデバイスに SSO を提供できます。 バージョン 0.3.0 以降の MSAL SDK では、既定でブローカーがサポートされています。

### Intune MAM SDK

[Intune MAM SDK では](https://learn.microsoft.com/ja-jp/mem/intune-service/developer/app-sdk-android-phase3)、バージョン [11.1.2](https://github.com/msintuneappsdk/ms-intune-app-sdk-ios/releases/tag/11.1.2) 以降の iOS 用 MSAL がサポートされます

### 同じアプリ内の MSAL と ADAL

ADAL バージョン 2.7.0 以降は、同じアプリケーションで MSAL と共存できません。 主な理由は、共有サブモジュールの共通コードが原因です。 Objective-C は名前空間をサポートしていないため、ADAL フレームワークと MSAL フレームワークの両方をアプリケーションに追加すると、同じクラスのインスタンスが 2 つ存在します。 実行時に選択される保証はありません。 両方の SDK が競合するクラスの同じバージョンを使用している場合でも、アプリが動作する可能性があります。 ただし、別のバージョンの場合は、診断が困難な予期しないクラッシュがアプリで発生する可能性があります。

同じ運用アプリケーションでの ADAL と MSAL の実行はサポートされていません。 ただし、ユーザーをテストして ADAL Objective-C から MSAL for iOS および macOS に移行するだけの場合は、引き続き [ADAL Objective-C 2.6.10](https://github.com/AzureAD/azure-activedirectory-library-for-objc/releases/tag/2.6.10) を使用できます。 同じアプリケーションで MSAL で動作する唯一のバージョンです。 この ADAL バージョンの新機能の更新プログラムは存在しないため、移行とテストの目的でのみ使用する必要があります。 アプリが ADAL と MSAL の共存に長期的に依存しないようにする必要があります。

同じアプリケーションでの ADAL と MSAL の共存はサポートされていません。 複数のアプリケーション間の ADAL と MSAL の共存は完全にサポートされています。

### 実際の移行手順

#### アプリ登録の移行

MSAL に切り替えてMicrosoft Entra アカウントを有効にするために、既存のMicrosoft Entra アプリケーションを変更する必要はありません。 ただし、ADAL ベースのアプリケーションがブローカー認証をサポートしていない場合は、MSAL に切り替える前に、アプリケーションの新しいリダイレクト URI を登録する必要があります。

リダイレクト URI は、次の形式にする必要があります: `msauth.<app.bundle.id>://auth`。 `<app.bundle.id>`をアプリケーションのバンドル ID に置き換えます。 Microsoft Entra 管理センターでリダイレクト URI を指定[します](https://entra.microsoft.com/?feature.broker=true#view/Microsoft_AAD_RegisteredApps/ApplicationsListBlade)。

iOS の場合のみ、証明書ベースの認証をサポートするには、追加のリダイレクト URI をアプリケーションに登録し、Microsoft Entra 管理センターを次の形式で登録する必要があります: `msauth://code/<broker-redirect-uri-in-url-encoded-form>`。 たとえば、`msauth://code/msauth.com.microsoft.mybundleId%3A%2F%2Fauth` のように指定します。

すべてのアプリで両方のリダイレクト URI を登録することをお勧めします。

増分同意のサポートを追加する場合は、[API アクセス許可] タブのアプリ登録で、アプリがアクセスを要求するように構成されている API と **アクセス許可** を選択します。

ADAL から移行していて、Microsoft Entra ID アカウントと MSA アカウントの両方をサポートする場合は、両方をサポートするように既存のアプリケーション登録を更新する必要があります。 Microsoft Entra IDと MSA の両方をすぐにサポートするように既存の運用アプリを更新することはお勧めしません。 代わりに、テスト用にMicrosoft Entra IDと MSA の両方をサポートする別のクライアント ID を作成し、すべてのシナリオが動作することを確認したら、既存のアプリを更新します。

#### アプリに MSAL を追加する

任意のパッケージ管理ツールを使用して、MSAL SDK をアプリに追加できます。 [詳細な手順については、こちらをご覧ください](https://github.com/AzureAD/microsoft-authentication-library-for-objc/wiki/Installation)。

#### アプリの Info.plist ファイルを更新する

iOS の場合のみ、アプリケーションのリダイレクト URI スキームを info.plist ファイルに追加します。 ADAL ブローカーと互換性のあるアプリの場合は、既に存在している必要があります。 既定の MSAL リダイレクト URI スキームは、 `msauth.<app.bundle.id>`形式になります。

```xml
<key>CFBundleURLSchemes</key>
<array>
    <string>msauth.<app.bundle.id></string>
</array>
```

アプリの Info.plist の `LSApplicationQueriesSchemes`の下に、次のスキームを追加します。

```xml
<key>LSApplicationQueriesSchemes</key>
<array>
     <string>msauthv2</string>
     <string>msauthv3</string>
</array>
```

#### AppDelegate コードを更新する

iOS の場合のみ、AppDelegate.m ファイルに次のコードを追加します。

Objective-C:

```objc
- (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<NSString *,id> *)options`
{
    return [MSALPublicClientApplication handleMSALResponse:url sourceApplication:options[UIApplicationOpenURLOptionsSourceApplicationKey]];
}
```

Swift:

```swift
func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
    return MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: options[UIApplication.OpenURLOptionsKey.sourceApplication] as? String)
}
```

**Xcode 11 を使用している場合は**、代わりに MSAL コールバックを `SceneDelegate` ファイルに配置する必要があります。 以前の iOS との互換性を保持するために UISceneDelegate と UIApplicationDelegate の両方をサポートしている場合は、MSAL コールバックを両方のファイルに配置する必要があります。

Objective-C:

```objc
 - (void)scene:(UIScene *)scene openURLContexts:(NSSet<UIOpenURLContext *> *)URLContexts
 {
     UIOpenURLContext *context = URLContexts.anyObject;
     NSURL *url = context.URL;
     NSString *sourceApplication = context.options.sourceApplication;
     
     [MSALPublicClientApplication handleMSALResponse:url sourceApplication:sourceApplication];
 }
```

Swift:

```swift
func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        
        guard let urlContext = URLContexts.first else {
            return
        }
        
        let url = urlContext.url
        let sourceApp = urlContext.options.sourceApplication
        
        MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: sourceApp)
    }
```

これにより、MSAL はブローカーと Web コンポーネントからの応答を処理できます。 ADAL では、アプリ デリゲート メソッドが自動的に "スウィズル" されるため、これは必要ありませんでした。 手動で追加するとエラーが発生しにくく、アプリケーションの制御が増えます。

#### トークンのキャッシュの有効化

既定では、MSAL はアプリのトークンを iOS または macOS キーチェーンにキャッシュします。

トークン キャッシュを有効にするには:

1. アプリケーションが正しく署名されていることを確認する
2. Xcode Project設定 &gt;**Capabilities タブ**に移動します&gt;**キーチェーン共有を有効にする**
3. [ **+** ] をクリックし、次の **キーチェーン グループ** エントリを入力します。3.a iOS の場合は、「 `com.microsoft.adalcache` 3.b For macOS」と入力します。 `com.microsoft.identity.universalstorage`

#### MSALPublicClientApplication を作成し、その acquireToken 呼び出しと acquireTokeSilent 呼び出しに切り替えます

次のコードを使用して `MSALPublicClientApplication` を作成できます。

Objective-C:

```objc
NSError *error = nil;
MSALPublicClientApplicationConfig *configuration = [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"<your-client-id-here>"];
    
MSALPublicClientApplication *application =
[[MSALPublicClientApplication alloc] initWithConfiguration:configuration
                                                     error:&error];
```

Swift:

```swift
let config = MSALPublicClientApplicationConfig(clientId: "<your-client-id-here>")
do {
  let application = try MSALPublicClientApplication(configuration: config)
  // continue on with application
            
} catch let error as NSError {
  // handle error here
}
```

次に、アカウント管理 API を呼び出して、キャッシュにアカウントがあるかどうかを確認します。

Objective-C:

```objc
NSString *accountIdentifier = nil /*previously saved MSAL account identifier */;
NSError *error = nil;
MSALAccount *account = [application accountForIdentifier:accountIdentifier error:&error];
```

Swift:

```swift
// definitions that need to be initialized
let application: MSALPublicClientApplication!
let accountIdentifier: String! /*previously saved MSAL account identifier */

do {
  let account = try application.account(forIdentifier: accountIdentifier)
  // continue with account usage
} catch let error as NSError {
  // handle error here
}
```

または、すべてのアカウント情報を読み取る:

Objective-C:

```objc
NSError *error = nil;
NSArray<MSALAccount *> *accounts = [application allAccounts:&error];
```

Swift:

```swift
let application: MSALPublicClientApplication!
do {
  let accounts = try application.allAccounts()
  // continue with account usage
} catch let error as NSError {
  // handle error here
}
```

アカウントが見つかった場合は、MSAL `acquireTokenSilent` API を呼び出します。

Objective-C:

```objc
MSALSilentTokenParameters *silentParameters = [[MSALSilentTokenParameters alloc] initWithScopes:@[@"<your-resource-here>/.default"] account:account];
    
[application acquireTokenSilentWithParameters:silentParameters
                              completionBlock:^(MSALResult *result, NSError *error)
{
    if (result)
    {
        NSString *accessToken = result.accessToken;
        // Use your token
    }
    else
    {
        // Check the error
        if ([error.domain isEqual:MSALErrorDomain] && error.code == MSALErrorInteractionRequired)
        {
            // Interactive auth will be required
        }
            
        // Other errors may require trying again later, or reporting authentication problems to the user
    }
}];
```

Swift:

```swift
let application: MSALPublicClientApplication!
let account: MSALAccount!
        
let silentParameters = MSALSilentTokenParameters(scopes: ["<your-resource-here>/.default"], 
                                                 account: account)
application.acquireTokenSilent(with: silentParameters) {
  (result: MSALResult?, error: Error?) in
  if let accessToken = result?.accessToken {
     // use accessToken
  }
  else {
    // Check the error
    guard let error = error else {
      assert(true, "callback should contain a valid result or error")
      return
    }
    
    let nsError = error as NSError
    if (nsError.domain == MSALErrorDomain
        && nsError.code == MSALError.interactionRequired.rawValue) {
      // Interactive auth will be required
    }
                
    // Other errors may require trying again later, or reporting authentication problems to the user
  }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/redirect-uris-ios"} -->
## MSAL でリダイレクト URI を使用する (iOS/macOS)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/redirect-uris-ios
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: Objective-C 用 Microsoft Authentication Library（iOS および macOS 用 MSAL）と Azure AD Authentication Library for Objective-C（ADAL.ObjC）の違いと、両者間で移行する方法について説明します。

ユーザーが認証を行うと、Microsoft Entra IDは、Microsoft Entra アプリケーションに登録されたリダイレクト URI を使用して、トークンをアプリに送信します。

MSAL では、リダイレクト URI を特定の形式でMicrosoft Entra アプリに登録する必要があります。 指定しない場合、MSAL は既定のリダイレクト URI を使用します。 形式は `msauth.[Your_Bundle_Id]://auth`。

既定のリダイレクト URI 形式は、ブローカー認証やシステム Web ビューなど、ほとんどのアプリとシナリオで機能します。 可能な限り、既定の形式を使用します。

ただし、次のセクションで説明するように、高度なシナリオではリダイレクト URI の変更が必要になる場合があります。

### 別のリダイレクト URI を必要とするシナリオ

#### アプリ間シングル サインオン (SSO)

Microsoft ID プラットフォームがアプリ間でトークンを共有するには、各アプリが同じクライアント ID またはアプリケーション ID を持っている必要があります。 クライアント ID は、Azure ポータルでアプリを登録したときに指定される一意の識別子です (Apple にアプリごとに登録するアプリケーション バンドル ID ではありません)。

リダイレクト URI は、iOS アプリごとに異なる必要があります。 これにより、Microsoft ID サービスは、アプリケーション ID を共有するさまざまなアプリを一意に識別できます。 各アプリケーションは、Azure ポータルに複数のリダイレクト URI を登録できます。 スイート内の各アプリには、異なるリダイレクト URI があります。 例えば次が挙げられます。

Azure ポータルで次のアプリケーション登録が行われます。

- クライアント ID: `ABCDE-12345`
- RedirectUris: `msauth.com.contoso.app1://auth`、 `msauth.com.contoso.app2://auth`、 `msauth.com.contoso.app3://auth`

App1 はリダイレクト `msauth.com.contoso.app1://auth`を使用します。 App2 は `msauth.com.contoso.app2://auth`を使用します。 App3 は `msauth.com.contoso.app3://auth`を使用します。

#### ADAL から MSAL への移行

Azure Active Directory認証ライブラリ (ADAL) を使用したコードを MSAL に移行する場合、アプリ用にリダイレクト URI が既に構成されている可能性があります。 ブローカー シナリオをサポートするように ADAL アプリが構成され、リダイレクト URI が MSAL リダイレクト URI 形式の要件を満たしている限り、同じリダイレクト URI を引き続き使用できます。

### MSAL リダイレクト URI 形式の要件

- MSAL リダイレクト URI は、次の形式である必要があります。 `<scheme>://host`

    ここで `<scheme>` は、アプリを識別する一意の文字列です。 これは主に、一意性を保証するためにアプリケーションのバンドル識別子に基づいています。 たとえば、アプリのバンドル ID が `com.contoso.myapp`されている場合、リダイレクト URI は `msauth.com.contoso.myapp://auth` の形式になります。

    ADAL から移行する場合、リダイレクト URI の形式は `<scheme>://[Your_Bundle_Id]` になり、 `scheme` は一意の文字列になります。 MSAL を使用すると、この形式は引き続き機能します。
- `<scheme>` は、 `CFBundleURLTypes > CFBundleURLSchemes`のアプリの Info.plist に登録されている必要があります。 この例では、Info.plist がソース コードとして開かれています。

    ```xml
    <key>CFBundleURLTypes</key>
    <array>
        <dict>
            <key>CFBundleURLSchemes</key>
            <array>
                <string>msauth.[BUNDLE_ID]</string>
            </array>
        </dict>
    </array>
    ```

MSAL はリダイレクト URI が正しく登録されているかどうかを確認し、正しくない場合はエラーを返します。

- ユニバーサル リンクをリダイレクト URI として使用する場合、 `<scheme>` は `https` する必要があり、 `CFBundleURLSchemes`で宣言する必要はありません。 代わりに、[開発者向けユニバーサル リンク](https://developer.apple.com/ios/universal-links/)で Apple の指示に従ってアプリとドメインを構成し、ユニバーサル リンクを使用してアプリケーションを開いたときに`handleMSALResponse:sourceApplication:`の`MSALPublicClientApplication`メソッドを呼び出します。

### カスタム リダイレクト URI を使用する

カスタム リダイレクト URI を使用するには、 `redirectUri` パラメーターを渡して `MSALPublicClientApplicationConfig` し、オブジェクトを初期化するときにそのオブジェクトを `MSALPublicClientApplication` に渡します。 リダイレクト URI が無効な場合、初期化子は `nil` を返し、追加情報を含む `redirectURIError`を設定します。 例えば次が挙げられます。

Objective-C:

```objc
MSALPublicClientApplicationConfig *config =
        [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"your-client-id"
                                                        redirectUri:@"your-redirect-uri"
                                                        authority:authority];
NSError *redirectURIError;
MSALPublicClientApplication *application =
        [[MSALPublicClientApplication alloc] initWithConfiguration:config error:&redirectURIError];
```

Swift:

```swift
let config = MSALPublicClientApplicationConfig(clientId: "your-client-id",
                                            redirectUri: "your-redirect-uri",
                                              authority: authority)
do {
  let application = try MSALPublicClientApplication(configuration: config)
  // continue on with application
} catch let error as NSError {
  // handle error here
}
```

### URL で開かれたイベントを処理する

アプリケーションは、URL スキームまたはユニバーサル リンクを介して応答を受信したときに MSAL を呼び出す必要があります。 アプリケーションを開いたときに、`handleMSALResponse:sourceApplication:`の`MSALPublicClientApplication` メソッドを呼び出します。 カスタム スキームの例を次に示します。

Objective-C:

```objc
- (BOOL)application:(UIApplication *)app
            openURL:(NSURL *)url
            options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options
{
    return [MSALPublicClientApplication handleMSALResponse:url
                                         sourceApplication:options[UIApplicationOpenURLOptionsSourceApplicationKey]];
}
```

Swift:

```swift
func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
    return MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: options[UIApplication.OpenURLOptionsKey.sourceApplication] as? String)
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/request-custom-claims"} -->
## カスタム要求を要求する (MSAL iOS/macOS)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/request-custom-claims
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: カスタム要求を要求する方法について説明します。

OpenID Connect を使用すると、必要に応じて、UserInfo エンドポイントまたは ID トークンから個々の要求の返しを要求できます。 要求要求は、要求された要求の一覧を含む JSON オブジェクトとして表されます。 詳細については、 [OpenID Connect Core 1.0](https://openid.net/specs/openid-connect-core-1_0-final.html#ClaimsParameter) を参照してください。

iOS および macOS 用の Microsoft Authentication Library (MSAL) を使用すると、対話型トークン取得シナリオとサイレント トークン取得シナリオの両方で特定の要求を要求できます。 これは、 `claimsRequest` パラメーターを使用して行います。

これが必要なシナリオは複数あります。 例えば次が挙げられます。

- アプリケーションの標準セットに含まれないクレームを要求しています。
- アプリケーションのスコープを使用して指定できない標準要求の特定の組み合わせを要求する。 たとえば、要求がないためにアクセス トークンが拒否された場合、アプリケーションは MSAL を使用して不足している要求を要求できます。

Note

MSAL は、要求要求が指定されるたびにアクセス トークン キャッシュをバイパスします。 追加の要求が必要な場合にのみ `claimsRequest` パラメーターを指定することが重要です (各 MSAL API 呼び出しで常に同じ `claimsRequest` パラメーターを提供するのとは対照的です)。

`claimsRequest` は、 `MSALSilentTokenParameters` および `MSALInteractiveTokenParameters`で指定できます。

```objc
/*!
 MSALTokenParameters is the base abstract class for all types of token parameters (silent and interactive).
 */
@interface MSALTokenParameters : NSObject

/*!
 The claims parameter that needs to be sent to authorization or token endpoint.
 If claims parameter is passed in silent flow, access token will be skipped and refresh token will be tried.
 */
@property (nonatomic, nullable) MSALClaimsRequest *claimsRequest;

@end
```

`MSALClaimsRequest` は、JSON 要求の NSString 表現から構築できます。

Objective-C:

```objc
NSError *claimsError = nil;
MSALClaimsRequest *request = [[MSALClaimsRequest alloc] initWithJsonString:@"{\"id_token\":{\"auth_time\":{\"essential\":true},\"acr\":{\"values\":[\"urn:mace:incommon:iap:silver\"]}}}" error:&claimsError];
```

Swift:

```swift
var requestError: NSError? = nil
let request = MSALClaimsRequest(jsonString: "{\"id_token\":{\"auth_time\":{\"essential\":true},\"acr\":{\"values\":[\"urn:mace:incommon:iap:silver\"]}}}",
                                        error: &requestError)
```

また、追加の特定の要求を要求することで変更することもできます。

Objective-C:

```objc
MSALIndividualClaimRequest *individualClaimRequest = [[MSALIndividualClaimRequest alloc] initWithName:@"custom_claim"];
individualClaimRequest.additionalInfo = [MSALIndividualClaimRequestAdditionalInfo new];
individualClaimRequest.additionalInfo.essential = @1;
individualClaimRequest.additionalInfo.value = @"myvalue";
[request requestClaim:individualClaimRequest forTarget:MSALClaimsRequestTargetIdToken error:&claimsError];
```

Swift:

```swift
let individualClaimRequest = MSALIndividualClaimRequest(name: "custom-claim")
let additionalInfo = MSALIndividualClaimRequestAdditionalInfo()
additionalInfo.essential = 1
additionalInfo.value = "myvalue"
individualClaimRequest.additionalInfo = additionalInfo
do {
  try request.requestClaim(individualClaimRequest, for: .idToken)
} catch let error as NSError {
  // handle error here  
}
        
```

`MSALClaimsRequest` 次に、トークン パラメーターを設定し、MSAL トークン取得 API のいずれかに提供する必要があります。

Objective-C:

```objc
MSALPublicClientApplication *application = ...;
MSALWebviewParameters *webParameters = ...;

MSALInteractiveTokenParameters *parameters = [[MSALInteractiveTokenParameters alloc] initWithScopes:@[@"user.read"]
                                                                                  webviewParameters:webParameters];
parameters.claimsRequest = request;
    
[application acquireTokenWithParameters:parameters completionBlock:completionBlock];
```

Swift:

```swift
let application: MSALPublicClientApplication!
let webParameters: MSALWebviewParameters!
        
let parameters = MSALInteractiveTokenParameters(scopes: ["user.read"], webviewParameters: webParameters)
parameters.claimsRequest = request
        
application.acquireToken(with: parameters) { (result: MSALResult?, error: Error?) in            ...

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/set-up-ios-device-shared-mode"} -->
## 共有デバイス モードで iOS または iPadOS デバイスを設定する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/set-up-ios-device-shared-mode
- Service: msal / msal-ios-mac
- Article date: 2024-08-19
- Summary: 共有デバイス モードで iOS または iPadOS デバイスを設定する方法について説明します

[共有デバイス モード](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-shared-devices) を使用すると、従業員がより簡単かつ安全に共有されるように iOS 14 以降または iPadOS デバイスを構成できます。 従業員は 1 回サインインして、この機能をサポートするすべてのアプリにシングル サインオン (SSO) を行い、情報にすばやくアクセスできます。 シフトまたはタスクが完了したら、サポートされている任意のアプリを通じてデバイスからサインアウトし、共有デバイス モードをサポートするすべてのアプリからサインアウトすることもできます。 その後、デバイスは、前のユーザーのデータへのアクセスを許可せずに、次の従業員が使用できる状態になります。

### Prerequisites

- アクティブなサブスクリプションを持つ Azure アカウント。 お持ちでない場合は、[無料のアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- Microsoft Entra IDに登録されていない iOS 14 以降の iOS デバイス。 登録されている場合は、出荷時の設定にリセットします。
- デバイスにインストールMicrosoft Authenticator[アプリ](https://play.google.com/store/apps/details/Microsoft_Authenticator?id=com.azure.authenticator&amp;hl=en_NZ)の最新バージョン。
- 手動セットアップの場合、デバイスは、Microsoft Intuneなどのモバイル デバイス管理 (MDM) ツールで管理する必要があります。
- MDM を使用してセットアップする場合、デバイスは共有デバイス モードをサポートする MDM で管理する必要があります。

### Intune を使用したゼロタッチ セットアップ

Microsoft Intuneでは、Microsoft Entra共有デバイス モードのデバイスに対するゼロタッチ プロビジョニングがサポートされています。つまり、現場担当者からの最小限の操作でデバイスをセットアップして Intune に登録できます。

Microsoft Intuneを MDM として使用するときに共有デバイス モードでデバイスを設定するには、まず、共有デバイスを Intune に登録し、共有デバイス モードを有効にして Authenticator アプリをインストールします。 詳細については、[共有デバイス モードでデバイスの登録を設定する方法Microsoft Entra](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/automated-device-enrollment-shared-device-mode)参照してください。

登録が完了したら、Authenticator アプリを起動し、登録プロセスを開始して完了します。 または、共有デバイス モードが有効なアプリケーションのいずれかを開いて、Authenticator アプリを起動し、登録を完了することもできます。

### 手動セットアップ

#### シングル サインオン プラグインを有効にする

MDM を使用して SSO プラグインを有効にするには、次の情報を使用します。

##### Microsoft Intune構成

MDM サービスとして Microsoft Intune を使用する場合は、組み込みの構成プロファイル設定を使用して、Microsoft Enterprise SSO プラグインを有効にすることができます。

1. 構成プロファイルの [SSO アプリ プラグイン](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-with-intune)の設定を構成します。
2. プロファイルがまだ割り当てられていない場合、[プロファイルをユーザーまたはデバイス グループに割り当てます](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-profile-assign)。

SSO プラグインを有効にするプロファイル設定は、各デバイスが次回 Intune でチェックインするときに、グループのデバイスに自動的に適用されます。

##### 他の MDM サービスの手動構成

MDM に Intune を使用しない場合は、Apple デバイス用に拡張可能なシングル サインオン プロファイル ペイロードを構成できます。 Microsoft Enterprise SSO プラグインとその構成オプションを構成するには、次のパラメーターを使用します。

iOS の設定:

- **拡張機能 ID**: `com.microsoft.azureauthenticator.ssoextension`
- **チーム ID**: iOS ではこのフィールドは不要です。

#### Microsoft Authenticator アプリを構成する

Authenticator アプリの設定を構成し、デバイスをEntra IDに登録するには、テナントのクラウド デバイス管理者が次の手順に従う必要があります。

1. Authenticator アプリを起動します。
2. ユーザーの操作を必要とせずに、(SSO プロファイルが既にデバイス上にある限り) アプリは自動的にデバイスの登録を開始する必要があります。 そうでない場合は、アプリを閉じて、SSO ペイロードがデバイス上にあることを検証してみてください。 次に、Authenticator アプリをもう一度開きます。

    [Image: アプリのデバイス登録画面を示すスクリーンショット]
3. 次に示すように、Authenticator アプリから資格情報の入力を求められた場合は、アプリを閉じて、SSO プロファイルがインストールされていることを確認します。 メール アドレスを入力しないでください。

    [Image: Authenticator アプリのサインイン ページのスクリーンショット]
4. 登録が成功した場合、デバイスは共有デバイス モードで正常に設定されます。

    [Image: Authenticator アプリでの共有デバイス モードのセットアップが成功したスクリーンショット]

### 共有デバイス モードのセットアップをテストする

共有デバイス モードの動作を確認するために、[GitHubの MSAL iOS Swift Microsoft Graph API](https://github.com/Azure-Samples/ms-identity-mobile-apple-swift-objc) コード サンプルには、共有デバイス モードで iOS デバイスで現場担当者アプリを実行する例が含まれています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/shared-devices-ios"} -->
## iOS デバイスの共有デバイス モード

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/shared-devices-ios
- Service: msal / msal-ios-mac
- Article date: 2025-04-29
- Summary: 共有デバイス モードを有効にして、現場担当者が iOS デバイスを共有できるようにする方法について説明します

このチュートリアルでは、共有デバイス モード (SDM) をサポートするように iOS または iPadOS アプリケーションを変更する方法について説明します。 SDM は、組織が複数の従業員間で簡単に共有できるように iOS、iPadOS、または Android デバイスを構成できるようにするMicrosoft Entra ID機能です。これは、現場担当者設定の一般的な方法です。

このチュートリアルでは、次の操作を行います。

- 単一アカウント モードのサポートを追加しました。
- SDM を使用するようにアプリを構成します。
- 共有デバイス モードを検出します。
- サインインしているユーザーが変更されたかどうかを確認します。

### Prerequisites

- [チュートリアル: iOS または macOS アプリからユーザーのサインインを行い、Microsoft Graph を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios)

### 共有デバイス モードを検出する

共有デバイス モードの検出は、アプリケーションにとって重要です。 多くのアプリケーションでは、共有デバイスでアプリケーションを使用するときに、ユーザー エクスペリエンス (UX) を変更する必要があります。 たとえば、アプリケーションに "サインアップ" 機能があるとします。これは、既にアカウントを持っている可能性があるため、現場担当者には適していません。 共有デバイス モードの場合は、アプリケーションのデータ処理にセキュリティを強化することもできます。

`getDeviceInformationWithParameters:completionBlock:`の`MSALPublicClientApplication` API を使用して、アプリが共有デバイス モードでデバイスで実行されているかどうかを判断します。

次のコード スニペットは、 `getDeviceInformationWithParameters:completionBlock:` API の使用例を示しています。

#### Swift

```swift
application.getDeviceInformation(with: nil, completionBlock: { (deviceInformation, error) in

    guard let deviceInfo = deviceInformation else {
        return
    }

    let isSharedDevice = deviceInfo.deviceMode == .shared
    // Change your app UX if needed
})
```

#### Objective-C

```objective
[application getDeviceInformationWithParameters:nil
                                completionBlock:^(MSALDeviceInformation * _Nullable deviceInformation, NSError * _Nullable error)
{
    if (!deviceInformation)
    {
        return;
    }

    BOOL isSharedDevice = deviceInformation.deviceMode == MSALDeviceModeShared;
    // Change your app UX if needed
}];
```

### サインインしているユーザーを取得し、ユーザーがデバイスで変更されたかどうかを判断する

共有デバイス モードをサポートするもう 1 つの重要な部分は、デバイス上のユーザーの状態を判断し、ユーザーが変更された場合、またはデバイスにユーザーがまったくいない場合にアプリケーション データをクリアすることです。 データが別のユーザーに漏洩しないようにする責任があります。

`getCurrentAccountWithParameters:completionBlock:` API を使用して、デバイスで現在サインインしているアカウントに対してクエリを実行できます。

##### Swift

```swift
let msalParameters = MSALParameters()
msalParameters.completionBlockQueue = DispatchQueue.main

application.getCurrentAccount(with: msalParameters, completionBlock: { (currentAccount, previousAccount, error) in

    // currentAccount is the currently signed in account
    // previousAccount is the previously signed in account if any
})
```

##### Objective-C

```objective
MSALParameters *parameters = [MSALParameters new];
parameters.completionBlockQueue = dispatch_get_main_queue();

[application getCurrentAccountWithParameters:parameters
                             completionBlock:^(MSALAccount * _Nullable account, MSALAccount * _Nullable previousAccount, NSError * _Nullable error)
{
    // currentAccount is the currently signed in account
    // previousAccount is the previously signed in account if any
}];
```

#### ユーザーをグローバルにサインインさせる

デバイスが共有デバイスとして構成されている場合、アプリケーションは `acquireTokenWithParameters:completionBlock:` API を呼び出してアカウントにサインインできます。 アカウントは、最初のアプリがアカウントにサインインした後、デバイス上のすべての対象アプリでグローバルに利用できるようになります。

##### Objective-C

```objective
MSALInteractiveTokenParameters *parameters = [[MSALInteractiveTokenParameters alloc] initWithScopes:@[@"api://myapi/scope"] webviewParameters:[self msalTestWebViewParameters]];

parameters.loginHint = self.loginHintTextField.text;

[application acquireTokenWithParameters:parameters completionBlock:completionBlock];
```

#### ユーザーをグローバルにサインアウトさせる

次のコードは、サインインしているアカウントを削除し、キャッシュされたトークンをアプリだけでなく、共有デバイス モードのデバイスからもクリアします。 ただし、アプリケーションから *データ* をクリアすることはありません。 アプリケーションからデータをクリアし、アプリケーションがユーザーに表示している可能性があるキャッシュされたデータをクリアする必要があります。

##### Swift

```swift
let account = .... /* account retrieved above */

let signoutParameters = MSALSignoutParameters(webviewParameters: self.webViewParamaters!)
signoutParameters.signoutFromBrowser = true // To trigger a browser signout in Safari.

application.signout(with: account, signoutParameters: signoutParameters, completionBlock: {(success, error) in
    if let error = error {

        // Signout failed

        return

    }

    // Sign out completed successfully

})
```

##### Objective-C

```objective
MSALAccount *account = ... /* account retrieved above */;

MSALSignoutParameters *signoutParameters = [[MSALSignoutParameters alloc] initWithWebviewParameters:webViewParameters];

signoutParameters.signoutFromBrowser = YES; // To trigger a browser signout in Safari.

[application signoutWithAccount:account signoutParameters:signoutParameters completionBlock:^(BOOL success, NSError * _Nullable error)

{

    if (!success)

    {

        // Signout failed

        return;

    }

    // Sign out completed successfully

}];
```

[Apple デバイス用の Microsoft Enterprise SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)は、アプリケーションの状態のみをクリアします。 Safari ブラウザーでは状態がクリアされません。 コード スニペットに示されている省略可能な `signoutFromBrowser` プロパティを使用して、Safari でブラウザーのサインアウトをトリガーできます。 これにより、ブラウザーがデバイス上で短時間起動します。

#### ブロードキャストを受信して、他のアプリケーションから開始されたグローバル サインアウトを検出する

アカウント変更ブロードキャストを受信するには、ブロードキャスト レシーバーを登録する必要があります。 アカウント変更ブロードキャストを受信したら、すぐに サインインしているユーザーを取得し、デバイスでユーザーが変更されたかどうかを判断します。 変更が検出された場合は、前にサインインしていたアカウントのデータのクリーンアップを開始します。 すべての操作を適切に停止し、データのクリーンアップを行うことをお勧めします。

次のコード スニペットは、ブロードキャスト レシーバーを登録する方法を示しています。

```objectivec
NSString *const MSAL_SHARED_MODE_CURRENT_ACCOUNT_CHANGED_NOTIFICATION_KEY = @"SHARED_MODE_CURRENT_ACCOUNT_CHANGED";

- (void) registerDarwinNotificationListener 

{ 

   CFNotificationCenterRef center =

   CFNotificationCenterGetDarwinNotifyCenter(); 

   CFNotificationCenterAddObserver(center, nil,

   sharedModeAccountChangedCallback,

   (CFStringRef)MSAL_SHARED_MODE_CURRENT_ACCOUNT_CHANGED_NOTIFICATION_KEY, 

   nil, CFNotificationSuspensionBehaviorDeliverImmediately); 

} 

// CFNotificationCallbacks used specifically for Darwin notifications leave userInfo unused 

void sharedModeAccountChangedCallback(CFNotificationCenterRef center, void * observer, CFStringRef name, void const * object, __unused CFDictionaryRef userInfo) 

{ 

    // Invoke account cleanup logic here 

} 
```

`CFNotificationAddObserver`または Swift で対応するメソッド シグネチャを表示するために使用できるオプションの詳細については、次を参照してください。

- [CFNotificationAddObserver](https://developer.apple.com/documentation/corefoundation/1543316-cfnotificationcenteraddobserver?language=objc)
- [CFNotificationCallback](https://developer.apple.com/documentation/corefoundation/cfnotificationcallback?language=objc)

iOS の場合、アプリにはバックグラウンドでアクティブな状態を維持し、Darwin の通知をリッスンするためのバックグラウンド アクセス許可が必要です。 バックグラウンド機能は、別のバックグラウンド操作をサポートするために追加する必要があります。このアプリには、Darwin の通知をリッスンするだけのバックグラウンド機能がある場合、Apple App Store から拒否される可能性があります。 アプリが既にバックグラウンド操作を完了するように構成されている場合は、その操作の一部としてリスナーを追加できます。 iOS のバックグラウンド機能の詳細については、「[バックグラウンド実行モードの構成」](https://developer.apple.com/documentation/xcode/configuring-background-execution-modes)を参照してください。

### 共有デバイス モードをサポートする Microsoft アプリケーション

次のMicrosoft アプリケーションでは、共有デバイス モードMicrosoft Entraサポートされています。

- [Microsoft Teams](https://learn.microsoft.com/ja-jp/microsoftteams/platform/)
- [Microsoft Viva Engage](https://learn.microsoft.com/ja-jp/viva/engage/overview) (以前[の Yammer](https://learn.microsoft.com/ja-jp/viva/engage/overview))
- [Microsoft Outlook](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-configuration-policies-outlook)
- [Microsoft Power Apps](https://learn.microsoft.com/ja-jp/power-apps/)
- [Microsoft Power BI Mobile](https://learn.microsoft.com/ja-jp/power-bi/consumer/mobile/mobile-app-shared-device-mode)
- [Microsoft Edge](https://learn.microsoft.com/ja-jp/microsoft-edge/)
- [Microsoft Word](https://learn.microsoft.com/ja-jp/microsoft-365-apps/mac/configure-shared-device-mode)
- [Microsoft Excel](https://learn.microsoft.com/ja-jp/microsoft-365-apps/mac/configure-shared-device-mode)
- [Microsoft PowerPoint](https://learn.microsoft.com/ja-jp/microsoft-365-apps/mac/configure-shared-device-mode)

Important

パブリック プレビューはサービス レベル アグリーメントなしで提供され、運用環境のワークロードには推奨されません。 一部の機能はサポートされていないか、機能が制限されている可能性があります。 詳細については、「 [オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)」を参照してください。

### 共有デバイス モードをサポートするサード パーティの MMM

次のサード パーティ製モバイル デバイス管理 (MDM) プロバイダーは、共有デバイス モードMicrosoft Entraサポートしています。

- [Jamf](https://learn.jamf.com/en-US/bundle/jamf-pro-release-notes-current/page/New_Features_and_Enhancements.html#ariaid-title5)
- [SOTI](https://www.soti.net/mc/help/v2025.1/en/console/configurations/profiles/configurations/categories/security/microsoft_authenticator_sso_for_ios_devices.html)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/single-sign-on-macos-ios"} -->
## macOS と iOS で SSO を構成する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/single-sign-on-macos-ios
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: macOS と iOS でシングル サインオン (SSO) を構成する方法について説明します。

macOS および iOS 用の Microsoft Authentication Library (MSAL) では、macOS/iOS アプリとブラウザー間のシングル サインオン (SSO) がサポートされています。 この記事では、次の SSO シナリオについて説明します。

- 複数のアプリ間のサイレント SSO

この種類の SSO は、同じ Apple Developer によって配布される複数のアプリ間で機能します。 キーチェーンから他のアプリによって書き込まれた更新トークンを読み取り、それらをアクセス トークンとサイレントに交換することで、サイレント SSO (つまり、ユーザーは資格情報の入力を求められません) を提供します。

- 認証ブローカーを介した SSO

Microsoftは、モバイル デバイスが Microsoft Entra ID に登録されている限り、さまざまなベンダーのアプリケーション間で SSO を有効にするブローカーと呼ばれるアプリを提供します。 この種類の SSO では、ブローカー アプリケーションをユーザーのデバイスにインストールする必要があります。

- **MSAL と Safari の間の SSO**

SSO は、 [ASWebAuthenticationSession クラスを](https://developer.apple.com/documentation/authenticationservices/aswebauthenticationsession?language=objc) 通じて実現されます。 他のアプリと Safari ブラウザーからの既存のサインイン状態が使用されます。 同じ Apple Developer によって配布されるアプリに限定されるわけではありませんが、ユーザーの操作が必要です。

アプリで既定の Web ビューを使用してユーザーをサインインすると、MSAL ベースのアプリケーションと Safari の間で自動 SSO が取得されます。 MSAL でサポートされる Web ビューの詳細については、「 [ブラウザーと WebView のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/msal/objc/customize-webviews)」を参照してください。

現在、この種類の SSO は macOS では使用できません。 macOS 上の MSAL では、Safari で SSO がサポートされていない WKWebView のみがサポートされます。

Note

iOS は、ログインを実行するために一時的なブラウザーを使用するため、ログイン直後にセッション Cookie をクリアします。 このブラウザーはセッション Cookie を共有しません。 iOS で SSO を機能させるには、永続的な Cookie を利用するために KMSI を有効にする必要があります。

- **ADAL と MSAL macOS/iOS アプリ間のサイレント SSO**

MSAL Objective-C、ADAL Objective-C ベースのアプリに対する移行と SSO をサポートします。 アプリは、同じ Apple Developer によって配布される必要があります。

[ADAL ベースのアプリと MSAL ベースのアプリ間のアプリ間 SSO の手順については、macOS と iOS](https://learn.microsoft.com/ja-jp/entra/msal/objc/sso-between-adal-msal-apps) 上の ADAL アプリと MSAL アプリ間の SSO に関するページを参照してください。

### アプリ間のサイレント SSO

MSAL では、iOS キーチェーン アクセス グループを介した SSO 共有がサポートされています。

アプリケーション全体で SSO を有効にするには、次の手順を実行する必要があります。詳細については、以下で説明します。

1. すべてのアプリケーションで同じクライアント ID またはアプリケーション ID が使用されていることを確認します。
2. キーチェーンを共有できるように、すべてのアプリケーションが Apple の同じ署名証明書を共有していることを確認します。
3. アプリケーションごとに同じキーチェーンエンタイトルメントを要求します。
4. 既定のキーチェーンと異なる場合に使用する共有キーチェーンについて MSAL SDK に伝えます。

#### 同じクライアント ID とアプリケーション ID を使用する

Microsoft ID プラットフォームでトークンを共有できるアプリケーションを把握するには、それらのアプリケーションで同じクライアント ID またはアプリケーション ID を共有する必要があります。 これは、ポータルで最初のアプリケーションを登録したときに提供された一意の識別子です。

Microsoft ID プラットフォーム が同じ Application ID を使用するアプリを見分ける方法は、**リダイレクト URI** によるものです。 各アプリケーションは、オンボード ポータルに複数のリダイレクト URI を登録できます。 スイート内の各アプリには、異なるリダイレクト URI があります。 例えば次が挙げられます。

App1 リダイレクト URI: `msauth.com.contoso.mytestapp1://auth` App2 リダイレクト URI: `msauth.com.contoso.mytestapp2://auth` App3 リダイレクト URI: `msauth.com.contoso.mytestapp3://auth`

リダイレクト URI の形式は、MSAL リダイレクト URI 形式の要件に記載されている MSAL がサポートする [形式](https://learn.microsoft.com/ja-jp/entra/msal/objc/redirect-uris-ios#msal-redirect-uri-format-requirements)と互換性がある必要があります。

#### アプリケーション間のキーチェーン共有を設定する

キーチェーン共有を有効にするには、Apple の [機能の追加](https://developer.apple.com/documentation/xcode/configuring-keychain-sharing) に関する記事を参照してください。 重要なのは、キーチェーンを呼び出す内容を決定し、SSO に関係するすべてのアプリケーションにその機能を追加することです。

エンタイトルメントを正しく設定すると、次の例のような `entitlements.plist` ファイルがプロジェクト ディレクトリに表示されます。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>keychain-access-groups</key>
    <array>
        <string>$(AppIdentifierPrefix)com.myapp.mytestapp</string>
        <string>$(AppIdentifierPrefix)com.myapp.mycache</string>
    </array>
</dict>
</plist>
```

##### 新しいキーチェーン グループを追加する

新しいキーチェーン グループをプロジェクトの **[機能]** に追加します。 キーチェーン グループは次のようになります。

- `com.microsoft.adalcache` iOS の場合
- `com.microsoft.identity.universalstorage` macOS の場合。

[Image: キーチェーンの例]

詳細については、 [キーチェーン グループ](https://learn.microsoft.com/ja-jp/entra/msal/objc/howto-v2-keychain-objc)を参照してください。

### アプリケーション オブジェクトを構成する

各アプリケーションでキーチェーンの権利を有効にし、SSO を使用する準備ができたら、次の例のようにキーチェーン アクセス グループで `MSALPublicClientApplication` を構成します。

Objective-C:

```objc
NSError *error = nil;
MSALPublicClientApplicationConfig *configuration = [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"<my-client-id>"];
configuration.cacheConfig.keychainSharingGroup = @"my.keychain.group";

MSALPublicClientApplication *application = [[MSALPublicClientApplication alloc] initWithConfiguration:configuration error:&error];
```

Swift:

```swift
let config = MSALPublicClientApplicationConfig(clientId: "<my-client-id>")
config.cacheConfig.keychainSharingGroup = "my.keychain.group"

do {
   let application = try MSALPublicClientApplication(configuration: config)
  // continue on with application
} catch let error as NSError {
  // handle error here
}
```

Warning

アプリケーション間でキーチェーンを共有すると、アプリケーション全体でユーザーまたはすべてのトークンを削除できます。 バックグラウンド処理を行うためにトークンに依存するアプリケーションがある場合、これは特に影響を受けます。 キーチェーンを共有する場合、アプリで Microsoft identity SDK の削除操作を使用する際は、細心の注意を払う必要があります。

それです！ Microsoft ID SDK は、すべてのアプリケーションで資格情報を共有するようになりました。 アカウントの一覧は、アプリケーション インスタンス間でも共有されます。

### iOS 上の認証ブローカーを介した SSO

MSAL は、Microsoft Authenticatorを使用したブローカー認証のサポートを提供します。 Microsoft Authenticatorは、登録されたデバイスMicrosoft Entra SSO を提供し、アプリケーションが条件付きアクセス ポリシーに従うのにも役立ちます。

アプリの認証ブローカーを使用して SSO を有効にする手順を次に示します。

1. アプリケーションのブローカー互換リダイレクト URI 形式をアプリの Info.plist に登録します。 ブローカー互換のリダイレクト URI 形式が `msauth.<app.bundle.id>://auth`。 '&lt;app.bundle.id&gt;' をアプリケーションのバンドル ID に置き換えます。 例えば次が挙げられます。

    ```xml
    <key>CFBundleURLSchemes</key>
    <array>
        <string>msauth.<app.bundle.id></string>
    </array>
    ```
2. アプリの Info.plist の `LSApplicationQueriesSchemes`の下に、次のスキームを追加します。

    ```xml
    <key>LSApplicationQueriesSchemes</key>
    <array>
         <string>msauthv2</string>
         <string>msauthv3</string>
    </array>
    ```
3. コールバックを処理するために、 `AppDelegate.m` ファイルに次のコードを追加します。

    Objective-C:

    ```objc
    - (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<NSString *,id> *)options
    {
        return [MSALPublicClientApplication handleMSALResponse:url sourceApplication:options[UIApplicationOpenURLOptionsSourceApplicationKey]];
    }
    ```

    Swift:

    ```swift
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        return MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: options[UIApplication.OpenURLOptionsKey.sourceApplication] as? String)
    }
    ```

**Xcode 11 を使用している場合は**、代わりに MSAL コールバックを `SceneDelegate` ファイルに配置する必要があります。 以前の iOS との互換性を保持するために UISceneDelegate と UIApplicationDelegate の両方をサポートしている場合は、MSAL コールバックを両方のファイルに配置する必要があります。

Objective-C:

```objc
 - (void)scene:(UIScene *)scene openURLContexts:(NSSet<UIOpenURLContext *> *)URLContexts
 {
     UIOpenURLContext *context = URLContexts.anyObject;
     NSURL *url = context.URL;
     NSString *sourceApplication = context.options.sourceApplication;

     [MSALPublicClientApplication handleMSALResponse:url sourceApplication:sourceApplication];
 }
```

Swift:

```swift
func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {

        guard let urlContext = URLContexts.first else {
            return
        }

        let url = urlContext.url
        let sourceApp = urlContext.options.sourceApplication

        MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: sourceApp)
    }
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/ssl-issues"} -->
## TLS/SSL の問題のトラブルシューティング (MSAL iOS/macOS)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/ssl-issues
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: MSAL での TLS/SSL 証明書の使用に関するさまざまな問題の対処方法について説明します。Objective-C ライブラリ。

この記事では、iOS および macOS 用の Microsoft Authentication Library (MSAL) の使用中に発生する可能性がある問題のトラブルシューティングに役立つ情報を提供します。

### ネットワークの問題。

**エラー -1200**: "SSL エラーが発生し、サーバーへのセキュリティで保護された接続を確立できません。"

このエラーは、接続がセキュリティで保護されていないことを意味します。 証明書が無効な場合に発生します。 TLS チェックに失敗しているサーバーなど、詳細については、エラー オブジェクトの `NSURLErrorFailingURLErrorKey` ディクショナリの`userInfo`を参照してください。

このエラーは、Apple のネットワーク ライブラリからのエラーです。 NSURL エラー コードの完全な一覧は、macOS SDK と iOS SDK の NSURLError.h にあります。 このエラーの詳細については、「 [URL 読み込みシステム エラー コード」を](https://developer.apple.com/documentation/foundation/1508628-url_loading_system_error_codes?language=objc)参照してください。

### 証明書の問題

無効な証明書を提供する URL が認証フローの一部として使用するサーバーに接続する場合、問題の診断を開始するには、 [SSL サーバー テスト](https://www.ssllabs.com/ssltest/analyze.html)などの SSL 検証サービスを使用して URL をテストすることをお勧めします。 さまざまなシナリオとブラウザーに対してサーバーをテストし、多くの既知の脆弱性をチェックします。

既定では、Apple の新しい [App Transport Security (ATS)](https://developer.apple.com/library/archive/documentation/General/Reference/InfoPlistKeyReference/Articles/CocoaKeys.html#//apple_ref/doc/uid/TP40009251-SW35) 機能は、TLS/SSL 証明書を使用するアプリに、より厳格なセキュリティ ポリシーを適用します。 一部のオペレーティング システムと Web ブラウザーでは、既定でこれらのポリシーの適用が開始されています。 セキュリティ上の理由から、ATS を無効にしないことをお勧めします。

SHA-1 ハッシュを使用する証明書には既知の脆弱性があります。 ほとんどの最新の Web ブラウザーでは、SHA-1 ハッシュを使用した証明書は許可されていません。

### キャプティブポータル

キャプティブ ポータルは、ユーザーが最初に Wi-Fi ネットワークにアクセスし、そのネットワークへのアクセスをまだ許可されていないときに、ユーザーに Web ページを表示します。 ユーザーがポータルの要件を満たすまで、インターネット トラフィックをインターセプトします。 ユーザーがポータルを介して接続するまで、ユーザーがネットワーク リソースに接続できないためのネットワーク エラーが予想されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/objc/sso-between-adal-msal-apps"} -->
## ADAL アプリと MSAL アプリ間の SSO (iOS/macOS)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/objc/sso-between-adal-msal-apps
- Service: msal / msal-ios-mac
- Article date: 2024-02-19
- Summary: ADAL アプリと MSAL アプリ間で SSO を共有する方法について説明します

iOS 用Microsoft Authentication Library (MSAL) は、アプリケーション間で [ADAL Objective-C](https://github.com/AzureAD/azure-activedirectory-library-for-objc) と SSO 状態を共有できます。 アプリを自分のペースで MSAL に移行できるため、ADAL ベースと MSAL ベースのアプリが混在していても、ユーザーはクロスアプリ SSO の恩恵を受けることができます。

MSAL SDK を使用してアプリ間の SSO を設定する方法のガイダンスについては、複数の [アプリ間のサイレント SSO](https://learn.microsoft.com/ja-jp/entra/msal/objc/single-sign-on-macos-ios#silent-sso-between-apps) に関するページを参照してください。 この記事では、ADAL と MSAL の間の SSO について説明します。

SSO を実装する詳細は、使用している ADAL のバージョンによって異なります。

### ADAL 2.7.x

このセクションでは、MSAL と ADAL 2.7.x の SSO の違いについて説明します

#### キャッシュ形式

ADAL 2.7.x は MSAL キャッシュ形式を読み取ることができます。 バージョン ADAL 2.7.x でのアプリ間 SSO に特別な操作を行う必要はありません。 ただし、これら 2 つのライブラリでサポートされているアカウント識別子の違いに注意してください。

#### アカウント識別子の違い

MSAL と ADAL では、異なるアカウント識別子が使用されます。 ADAL では、プライマリ アカウント識別子として UPN が使用されます。 MSAL では、AAD アカウントのオブジェクト ID とテナント ID に基づく表示不可能なアカウント識別子と、他の種類のアカウントに対する `sub` 要求が使用されます。

MSAL 結果で `MSALAccount` オブジェクトを受け取ると、 `identifier` プロパティにアカウント識別子が含まれます。 アプリケーションは、後続のサイレント要求にこの識別子を使用する必要があります。

`identifier`に加えて、`MSALAccount` オブジェクトには、`username`と呼ばれる表示可能な識別子が含まれています。 これは、ADAL の `userId` に変換されます。 `username` は一意の識別子とは見なされず、いつでも変更できるため、ADAL との下位互換性シナリオでのみ使用する必要があります。 MSAL では、 `username` または `identifier`を使用したキャッシュ クエリがサポートされています。 `identifier` によるクエリが推奨されます。

次の表は、ADAL と MSAL のアカウント識別子の違いをまとめたものです。

| アカウント識別子 | MSAL | ADAL 2.7.x | 古い ADAL (ADAL 2.7.x より前) |
| --- | --- | --- | --- |
| 表示可能な識別子 | `username` | `userId` | `userId` |
| 一意の表示不可能な識別子 | `identifier` | `homeAccountId` | N/a |
| アカウント ID が不明です | で `allAccounts:` API を使用してすべてのアカウントに対してクエリを実行する `MSALPublicClientApplication` | N/a | N/a |

これは、これらの識別子を提供する `MSALAccount` インターフェイスです。

```objc
@protocol MSALAccount <NSObject>

/*!
 Displayable user identifier. Can be used for UI and backward compatibility with ADAL.
 */
@property (readonly, nullable) NSString *username;

/*!
 Unique identifier for the account.
 Save this for account lookups from cache at a later point.
 */
@property (readonly, nullable) NSString *identifier;

/*!
 Host part of the authority string used for authentication based on the issuer identifier.
 */
@property (readonly, nonnull) NSString *environment;

/*!
 ID token claims for the account.
 Can be used to read additional information about the account, e.g. name
 Will only be returned if there has been an id token issued for the client Id for the account's source tenant.
 */
@property (readonly, nullable) NSDictionary<NSString *, NSString *> *accountClaims;

@end
```

#### MSAL から ADAL への SSO

MSAL アプリと ADAL アプリがあり、ユーザーが最初に MSAL ベースのアプリにサインインする場合は、`username` オブジェクトから`MSALAccount`を保存し、`userId`として ADAL ベースのアプリに渡すことで、ADAL アプリで SSO を取得できます。 その後、ADAL は、 `acquireTokenSilentWithResource:clientId:redirectUri:userId:completionBlock:` API を使用してアカウント情報をサイレント モードで検索できます。

#### ADAL から MSAL への SSO

MSAL アプリと ADAL アプリがあり、ユーザーが最初に ADAL ベースのアプリにサインインする場合は、MSAL のアカウント参照に ADAL ユーザー識別子を使用できます。 これは、ADAL から MSAL に移行する場合にも適用されます。

##### ADAL の homeAccountId

ADAL 2.7.x は、次のプロパティを使用して、結果の`homeAccountId` オブジェクト内の`ADUserInformation`を返します。

```objc
/*! Unique AAD account identifier across tenants based on user's home OID/home tenantId. */
@property (readonly) NSString *homeAccountId;
```

`homeAccountId` ADAL の場合は、MSAL の `identifier` に相当します。 この識別子は、 `accountForIdentifier:error:` API を使用してアカウント参照に MSAL で使用するために保存できます。

##### ADAL の `userId`

`homeAccountId`が使用できない場合、または表示可能な識別子しかない場合は、ADAL の`userId`を使用して MSAL 内のアカウントを参照できます。

MSAL では、まず、 `username` または `identifier`でアカウントを検索します。 クエリには常に `identifier` を使用し、フォールバックとして `username` のみを使用します。 アカウントが見つかった場合は、 `acquireTokenSilent` 呼び出しでアカウントを使用します。

Objective-C:

```objc
NSString *msalIdentifier = @"previously.saved.msal.account.id";
MSALAccount *account = nil;
    
if (msalIdentifier)
{
    // If you have MSAL account id returned either from MSAL as identifier or ADAL as homeAccountId, use it
    account = [application accountForIdentifier:@"my.account.id.here" error:nil];
}
else
{
    // Fallback to ADAL userId for migration
    account = [application accountForUsername:@"adal.user.id" error:nil];
}
    
if (!account)
{
  // Account not found.
  return;
}

MSALSilentTokenParameters *silentParameters = [[MSALSilentTokenParameters alloc] initWithScopes:@[@"user.read"] account:account];
[application acquireTokenSilentWithParameters:silentParameters completionBlock:completionBlock];
```

Swift:

```swift
        
let msalIdentifier: String?
var account: MSALAccount
        
do {
  if let msalIdentifier = msalIdentifier {
    account = try application.account(forIdentifier: msalIdentifier)
  }
  else {
    account = try application.account(forUsername: "adal.user.id") 
  }
             
  let silentParameters = MSALSilentTokenParameters(scopes: ["user.read"], account: account)          
  application.acquireTokenSilent(with: silentParameters) {
    (result: MSALResult?, error: Error?) in
    // handle result
  }  
} catch let error as NSError {
  // handle error or account not found
}
```

MSAL でサポートされているアカウント参照 API:

```objc
/*!
 Returns account for the given account identifier (received from an account object returned in a previous acquireToken call)
 
 @param  error      The error that occurred trying to get the accounts, if any, if you're
                    not interested in the specific error pass in nil.
 */
- (nullable MSALAccount *)accountForIdentifier:(nonnull NSString *)identifier
                                         error:(NSError * _Nullable __autoreleasing * _Nullable)error;
    
/*!
Returns account for for the given username (received from an account object returned in a previous acquireToken call or ADAL)
    
@param  username    The displayable value in UserPrincipleName(UPN) format
@param  error       The error that occurred trying to get the accounts, if any, if you're
                    not interested in the specific error pass in nil.
*/
- (MSALAccount *)accountForUsername:(NSString *)username
                              error:(NSError * __autoreleasing *)error;
```

### ADAL 2.x-2.6.6

このセクションでは、MSAL と ADAL 2.x-2.6.6 の SSO の違いについて説明します。

古い ADAL バージョンでは、MSAL キャッシュ形式がネイティブにサポートされていません。 ただし、ADAL から MSAL へのスムーズな移行を確実に行うために、MSAL はユーザーの資格情報を再度要求することなく、古い ADAL キャッシュ形式を読み取ることができます。

`homeAccountId`は古い ADAL バージョンでは使用できないため、`username`を使用してアカウントを検索する必要があります。

```objc
/*!
 Returns account for for the given username (received from an account object returned in a previous acquireToken call or ADAL)

 @param  username    The displayable value in UserPrincipleName(UPN) format
 @param  error       The error that occurred trying to get the accounts, if any.  If you're not interested in the specific error pass in nil.
 */
- (MSALAccount *)accountForUsername:(NSString *)username
                              error:(NSError * __autoreleasing *)error;
```

例えば次が挙げられます。

Objective-C:

```objc
MSALAccount *account = [application accountForUsername:@"adal.user.id" error:nil];;
MSALSilentTokenParameters *silentParameters = [[MSALSilentTokenParameters alloc] initWithScopes:@[@"user.read"] account:account];
[application acquireTokenSilentWithParameters:silentParameters completionBlock:completionBlock];
```

Swift:

```swift
do {
  let account = try application.account(forUsername: "adal.user.id")          
  let silentParameters = MSALSilentTokenParameters(scopes: ["user.read"], account: account)
  application.acquireTokenSilent(with: silentParameters) { 
    (result: MSALResult?, error: Error?) in
    // handle result
  }   
} catch let error as NSError { 
  // handle error or account not found
}
```

または、ADAL からアカウント情報を読み取るすべてのアカウントを読み取ることもできます。

Objective-C:

```objc
NSArray *accounts = [application allAccounts:nil];
    
if ([accounts count] == 0)
{
  // No account found.
  return; 
}
if ([accounts count] > 1)
{
  // You might want to display an account picker to user in actual application
  // For this sample we assume there's only ever one account in cache
  return;
}
    ``
MSALSilentTokenParameters *silentParameters = [[MSALSilentTokenParameters alloc] initWithScopes:@[@"user.read"] account:accounts[0]];
[application acquireTokenSilentWithParameters:silentParameters completionBlock:completionBlock];
```

Swift:

```swift
      
do {
  let accounts = try application.allAccounts()
  if accounts.count == 0 {
    // No account found.
    return
  }
  if accounts.count > 1 {
    // You might want to display an account picker to user in actual application
    // For this sample we assume there's only ever one account in cache
    return
  }
  
  let silentParameters = MSALSilentTokenParameters(scopes: ["user.read"], account: accounts[0])
  application.acquireTokenSilent(with: silentParameters) {
    (result: MSALResult?, error: Error?) in
    // handle result or error  
  }  
} catch let error as NSError { 
  // handle error
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/overview"} -->
## MSAL について学習する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/overview
- Service: msal
- Article date: 2024-01-12
- Summary: Microsoft Authentication Library (MSAL) を使用すると、アプリケーション開発者はセキュリティで保護された Web API を呼び出すためにトークンを取得できます。 これらの Web API には、Microsoft Graph、その他の Microsoft API、サード パーティの Web API、または、独自の Web API が含まれます。 MSAL は、複数のアプリケーション アーキテクチャとプラットフォームをサポートします。

Microsoft Authentication Library (MSAL) を使用すると、ユーザーを認証し、セキュリティで保護された Web API にアクセスするため、開発者は Microsoft ID プラットフォームからセキュリティ トークンを取得できます。 これを使用して、Microsoft Graph、Microsoft API、サードパーティの Web API、または独自の Web API に安全にアクセスできます。 MSAL では、.NET、JavaScript、Java、Python、Android、iOS など、さまざまなアプリケーション アーキテクチャとプラットフォームがサポートされています。

MSAL は、サポートされているプラットフォーム用の一貫性のある API を使用して、トークンを取得するいくつかの方法を提供するトークン取得ライブラリです。 MSAL を使用すると、次のような利点があります。

- OAuth プロトコルに対してアプリケーションを直接記述する必要はありません。 配管はライブラリによって処理されます。
- ユーザーやアプリケーション (プラットフォームに適用できるとき) の代わりにトークンを取得できます。
- ライブラリはトークン キャッシュを保持し、有効期限が切れるときにトークンを更新します。 自分自身でトークンの有効期限を処理する必要はありません。
- どの対象ユーザーがアプリケーションにサインインするかを指定するのに役立ちます。 サインイン対象ユーザーには、個人の Microsoft アカウント、Microsoft Entra 外部 ID 組織のソーシャル ID、職場、学校、ソブリン クラウドや国内クラウドのユーザーを含めることができます。
- 構成ファイルからアプリケーションをセットアップするのに役立ちます。
- アクション可能な例外、ログ記録、およびテレメトリを公開することで、アプリのトラブルシューティングに役立ちます。

### アプリケーションの種類とシナリオ

MSAL を使用すると、Web アプリケーション、Web API、シングルページ アプリ (JavaScript)、モバイル アプリケーション、ネイティブ アプリケーション、デーモン、サーバー側アプリケーションなど、さまざまな種類のアプリケーションに対してトークンを取得できます。

MSAL は、次のようないくつかのアプリケーション シナリオで使用できます。

- シングル ページ アプリケーション (JavaScript)
- ユーザーの Web アプリケーションのサインイン
- Web アプリケーションがユーザーにサインインし、ユーザーに代わって Web API を呼び出す
- Web API 認証。認証されたユーザーのみがアクセスできるようにする
- サインインしているユーザーの代わりに別のダウンストリーム Web API を呼び出す Web API
- サインインしているユーザーの代わりに Web API を呼び出すデスクトップ アプリケーション
- 対話形式でサインインしているユーザーに代わって Web API を呼び出すモバイル アプリケーション
- それ自体に代わって Web API を呼び出すデスクトップ/サービス デーモン アプリケーション

### 言語とフレームワーク

| Library | サポートされるプラットフォームとフレームワーク |
| --- | --- |
| [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | .NET Framework、.NET、ユニバーサル Windows プラットフォーム |
| [MSAL Java](https://github.com/AzureAD/microsoft-authentication-library-for-java) | Windows、macOS、Linux |
| [MSAL Python](https://github.com/AzureAD/microsoft-authentication-library-for-python) | Windows、macOS、Linux |
| [MSAL.js](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser) | Vue.js、Ember.js、Durandal.js などの JavaScript/TypeScript フレームワーク |
| [MSAL Node](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) | Express を使用した Web アプリ、Electron を使用したデスクトップ アプリ、クロスプラットフォームのコンソール アプリ |
| [MSAL React](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-react) | React および React ベースのライブラリ (Next.js、Gatsby.js) を備えたシングルページ アプリ |
| [MSAL Angular](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-angular) | Angular および Angular.js フレームワークを備えたシングルページ アプリ |
| [MSAL for ANDROID](https://github.com/AzureAD/microsoft-authentication-library-for-android) | Android |
| [iOS および macOS 用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc) | iOS と macOS |
| [MSAL Go](https://github.com/AzureAD/microsoft-authentication-library-for-go) | Windows、macOS、Linux |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python"} -->
## PythonのMicrosoft Authentication Library (MSAL) の概要 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python
- Service: msal / msal-python
- Article date: 2025-04-24
- Summary: Python 用 Microsoft Authentication Libraryを使用して、Microsoft ID を使用してユーザーまたはアプリにサインインします。"

Python 用 Microsoft Authentication Library (MSAL) を使用すると、Microsoft ID（[Microsoft Entra ID](https://azure.microsoft.com/services/active-directory/)、[Microsoft アカウント](https://account.microsoft.com)、および [Microsoft Entra ID](https://www.microsoft.com/security/business/identity-access/microsoft-entra-id) アカウント）を使用して、ユーザーまたはアプリをサインインさせることができます。 MSAL Pythonを使用すると、Microsoft Entra IDからトークンを取得して、Microsoft Graph、他[のMicrosoft](https://graph.microsoft.io/) API、独自の API などの保護された Web API を呼び出すことができます。

### Prerequisites

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料アカウントを作成します](https://signup.azure.com/)。
- [Python 3.6+](https://www.python.org/downloads/)。

### パッケージをインストールする

Python パッケージの MSAL をインストールします。 [PyPI](https://pypi.org/project/msal/) で MSAL Pythonを見つけることができます。

```Bash
pip install msal
```

### ID の概念

MSAL Pythonは、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview) エコシステムの一部です。 MSAL Pythonを効果的に使用してアプリケーションと API を保護するには、次の概念を理解してください。

- [ID とアクセス管理](https://learn.microsoft.com/ja-jp/entra/fundamentals/identity-fundamental-concepts)
- [認証と権限承認](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-vs-authorization)
- [Microsoft ID プラットフォーム における OAuth 2.0 と OpenID Connect (OIDC)](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)
- [Microsoft ID プラットフォームの機密およびパブリック クライアント アカウント](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications)
- [セキュリティ トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens)

### 使用シナリオ

MSAL Pythonを使用するには、アプリケーションをMicrosoft ID プラットフォームに登録します。 アクティブなサブスクリプションを持つAzure アカウントが必要です。 [無料アカウント](https://signup.azure.com/) がない場合は作成します。 [顧客テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)または[従業員テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-sign-user-app-registration?tabs=python)にアプリを登録できます。

アプリケーションでは、MSAL Pythonを使用して、保護された API にアクセスするためのトークンを取得できます。 アプリの種類が異なると、異なる認証フローを使用してトークンが取得されます。 サポートされているアプリの種類には、デスクトップ アプリケーション、Web アプリケーション、Web API、ブラウザーのないデバイス (IoT デバイスなど) で実行されているアプリケーションが含まれます。

MSAL Pythonでは、アプリケーションは次のように分類されます。

- [パブリック クライアント アプリケーション](https://datatracker.ietf.org/doc/html/rfc6749#section-2.1) (デスクトップとモバイル)。 これらの種類のアプリでは、アプリ シークレットを安全に格納できません。
- [機密クライアント アプリケーション](https://datatracker.ietf.org/doc/html/rfc6749#section-2.1) (Web アプリ、Web API、デーモン アプリケーション)。 これらの種類のアプリは、Microsoft Entra IDに登録されたシークレットを安全に格納します。

詳細については、[パブリック クライアント アプリと機密クライアント アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications)に関するドキュメントと、[Microsoft ID プラットフォームのさまざまなアプリの種類とその認証フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)を参照してください。

アプリケーションがパブリック クライアント アプリケーションか機密クライアント アプリケーションかを判断したら、MSAL Pythonを使用して、さまざまなシナリオのトークンを取得できます。

### 基本的な使用方法

MSAL Pythonを使用してトークンを取得するには、3 段階のパターンに従います。 フローごとにいくつかのバリエーションがあります。 実際の動作を確認したい場合は、 [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-python/tree/dev/sample)をダウンロードしてください。

1. MSAL は、パブリック クライアント アプリケーションと機密クライアント アプリケーションの間のクリーンな分離に依存します。 そのため、 [`PublicClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication) または [`ConfidentialClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication) インスタンスを作成し、アプリケーションのライフサイクル中に再利用します。 たとえば、パブリック クライアント アプリケーションの場合、初期化コードは次のようになります。

    ```python
    from msal import PublicClientApplication
    
    app = PublicClientApplication(
        "your_client_id",
        authority="https://login.microsoftonline.com/common")
    ```

    機関の値は、サインインするアカウントの種類と、アプリが登録されているテナントの種類によって異なります。 たとえば、従業員テナント (Microsoft Entra ID) でプロビジョニングされた職場アカウントと個人用Microsoft アカウントの両方にサインインするには、`https://login.microsoftonline.com/common`を使用します。 顧客テナント内でプロビジョニングされた顧客アカウントでは、権限は `https://<subdomain>.ciamlogin.com` のような形式になります。 詳細については、 [トークン発行者のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens#validate-the-issuer)。
2. 最初にキャッシュからトークンを取得してみてください。 MSAL の API モデルでは、トークン キャッシュを利用する方法を明示的に制御できます。 キャッシュ部分は技術的には省略可能ですが、アプリケーションで使用することを強くお勧めします。 キャッシュを使用すると、追加の API 呼び出しを行わず、トークンの更新を自動的に処理することができます。

    ```python
    # initialize result variable to hole the token response
    result = None 
    
    # We now check the cache to see
    # whether we already have some accounts that the end user already used to sign in before.
    accounts = app.get_accounts()
    if accounts:
        # If so, you could then somehow display these accounts and let end user choose
        print("Pick the account you want to use to proceed:")
        for a in accounts:
            print(a["username"])
        # Assuming the end user chose this one
        chosen = accounts[0]
        # Now let's try to find a token in cache for this account
        result = app.acquire_token_silent(["User.Read"], account=chosen)
    ```
3. キャッシュに適切なトークンがない場合、または前の手順をスキップすることを選択した場合は、トークンを取得するためにMicrosoft Entra IDに要求を送信します。 クライアントの種類とシナリオに応じてさまざまな方法がありますが、この例では、 [`acquire_token_interactive`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-acquire-token-interactive)を使用する方法を示しています。この方法では、ユーザーに資格情報の入力を求めます。

    ```python
    if not result:
        # So no suitable token exists in cache. Let's get a new one from Azure AD.
        result = app.acquire_token_interactive(scopes=["User.Read"])
    if "access_token" in result:
        print(result["access_token"])  # Yay!
    else:
        print(result.get("error"))
        print(result.get("error_description"))
        print(result.get("correlation_id"))  # You may need this when reporting a bug
    ```
4. コードを *msaltest.py などのPython* ファイルにローカルに保存します。
5. `python .\msalpytest.py`を実行してコードを実行します。 次のビジュアルは、この例のサインイン エクスペリエンスを示しています。

    [Image: ユーザーが自分のアカウントでサインインするように求めるアプリの例]
6. 認証が完了し、ブラウザーを閉じると、ターミナルに出力されたアクセス トークンを確認できるようになります。

### 堅牢なエンタープライズ対応アプリケーションのベスト プラクティス

MSAL Pythonを使用して、保護された Web API のトークンを取得できます。 また、更新トークンを自分で処理する必要はありません。 ただし、堅牢でエンタープライズ対応のアプリケーションを構築するには、もう少し行う必要があります。 たとえば、次の手順を実行します。

- トークンを取得する場合と、保護された Web API を呼び出すときの両方で[、例外を処理](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-handling-exceptions?tabs=python)します。 特に、テナント管理者が Multi Factor Authentication (MFA) を適用するように[条件付きアクセス](https://github.com/AzureAD/microsoft-authentication-library-for-python/wiki/Conditional-Access-and-Claims-Challenges) ポリシーを設定しているMicrosoft Entra テナントでアプリケーションを実行する場合は、要求チャレンジを処理する必要があります。
- ログ [記録](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-logging?tabs=python) を有効にして、ユーザーのプライバシーを尊重し、GDPR に準拠しながら、アプリケーションのトラブルシューティングを行い、ユーザーを支援することができます。

### Samples

MSAL Pythonの使用を開始するために使用できるサンプルがいくつかあります。

- [ライブラリ リポジトリ](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.22.0/sample)のサンプル。 これらのサンプルは、MSAL Pythonを使用して実装するさまざまな構成と認証フローを示しています。
- [ドキュメントで使用されているサンプル](https://github.com/Azure-Samples/ms-identity-docs-code-python)を含む 1 つのリポジトリ。 これらのサンプルには、最初からビルドしてレプリケートするのに役立つサポート ドキュメントがあります。

### References

- GitHub[上の](https://github.com/AzureAD/microsoft-authentication-library-for-python) MSAL Python ライブラリ リポジトリ
- MSAL Python の [GitHub でのリリース](https://github.com/AzureAD/microsoft-authentication-library-for-python/releases)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/aad-b2c"} -->
## MSAL Pythonを使用して Azure AD B2C を操作する - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/aad-b2c
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: MSAL Pythonを使用すると、Azure AD B2C を使用して、ソーシャル ID を使用したユーザーのサインイン、トークンの取得、サインイン エクスペリエンスのカスタマイズを行うことができます。

MSAL Pythonを使用すると、Azure [AD B2C](https://aka.ms/aadb2c) を使用して、ソーシャル ID でユーザーをサインインしたり、トークンを取得したり、サインイン エクスペリエンスをカスタマイズしたりできます。

Important

2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、[AZURE AD B2C を引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

Azure AD B2C は[、ユーザー フロー](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/active-directory-b2c-reference-policies) (旧称ポリシー) の概念に基づいて構築されています。 MSAL Pythonでは、ユーザー フローを指定すると、権限の提供に変換されます。

- クライアント アプリケーションをインスタンス化するときは、権限でユーザー フローを `https://{tenant_name}.b2clogin.com/{tenant_name}.onmicrosoft.com/{user_flow}`として指定する必要があります。 その後、通常の `acquire_token_by_xyz(...)` はすべて、そのユーザー フローで動作します。 B2C シナリオと B2C 以外のシナリオの間に API サーフェスの違いはありません。 別の構成セットを入れ替えるだけで、同じ [Web アプリサンプル](https://github.com/Azure-Samples/ms-identity-python-webapp) が B2C 以外のシナリオと B2C シナリオの両方で機能します。
- プロファイル ユーザー フローの編集やパスワードのリセット ユーザー フローなど、一部のユーザー フローは、基本的にカスタマイズされたユーザー エクスペリエンスです。 これらのページの URL を取得するには、その`PublicClientApplication`を含む権限を持つ`{user_flow}`を作成し、`get_authorization_request_url(...)`を呼び出します。 ( [Web アプリのサンプル](https://github.com/Azure-Samples/ms-identity-python-webapp)では、より高いレベルのヘルパーを提供しています)。

これらの概要の詳細については、以下で説明します。

### B2C テナントとユーザー フローの権限

使用する権限は `https://{tenant_name}.b2clogin.com/{tenant_name}.onmicrosoft.com/{user_flow}` です。ここで:

- `tenant_name`は、"contoso" など、Azure AD B2C テナントの名前です。
- `user_flow` は、適用するユーザー フローの名前です (たとえば、サインイン/サインアップに "b2c\_1\_susi" がある場合があります)。

アプリケーションをビルドするときは、上記のように構築された機関を通常どおり指定する必要があります

```python
app = msal.PublicClientApplication(  # Or ConfidentialClientApplication(...)
    "your_client_id",
    authority="https://contoso.b2clogin.com/contoso.onmicrosoft.com/b2c_1_susi",
    ...)
```

Note

また、独自のカスタマイズされたドメイン名を使用して、機関が "https:// **contoso.com**/{tenant\_name}.onmicrosoft.com/{user\_flow}" のように見えるようにすることもできます。 内部では、MSAL Pythonは、すべての B2C ドメインのvalidate\_authorityチェック動作を自動的に調整します。 そのため、余分な操作を行う必要はありません。

### トークンの取得

機関の一部として既に提供されているユーザー フローに基づいて、Azure AD B2C で保護された API のトークンを取得することは、B2C 以外のシナリオで行うのとまったく同じです。 そのため、B2C 以外の[Python Web アプリサンプル](https://github.com/Azure-Samples/ms-identity-python-webapp)は、B2C Web アプリのサンプルとして機能します。 その主要なファイル `app.py` は、B2C 以外のシナリオと B2C シナリオの両方で変更なしで機能します。

```python
app.acquire_token_by_xyz(...)  # Same as in non-B2C scenarios
```

"異なるユーザー フロー用に異なる MSAL アプリを作成する" というパターンに従っている限り、ユーザー フローによってアカウントをフィルター処理する必要はありません (B2C ユーザー フローは分離された機関のように動作するように設計されているため)。 実際には、通常は同じ MSAL アプリとそのトークン キャッシュを SignIn ユーザー フロー用に再利用し、EditProfile または ResetPassword ユーザー フローを呼び出すときにのみ新しい 1 回限りの MSAL アプリを作成します。この場合、返されたトークン (存在する場合) は役に立ちません。

### EditProfile および ResetPassword ユーザー フローの例

EditProfile と ResetPassword のユーザー フローは次のとおりです。

- トークンの取得についてはあまり扱いません。 それは明白ではないかもしれませんが、現在、ある B2C ユーザー フローによって発行されたトークンは、別の B2C ユーザー フローによって発行されたトークンと必ずしも互換性がないためです。 ただし、より安全なパターンは、これらのトークンを共有しないように Web アプリを整理することです。
- カスタマイズされたユーザー エクスペリエンスについて詳しく説明します。 古い学校の Web アプリ (MSAL または B2C のコンテキスト外であっても) で、カスタマイズされたプロファイル編集エクスペリエンスを提供する方法を想像してみてください。 Web アプリのナビゲーション バーに、そのカスタマイズされたページを指すリンクを含めます。

[B2C Web アプリのサンプル](https://github.com/Azure-Samples/ms-identity-python-webapp/commit/6b7cb85b79571f0164b3cb13ab998e3976293739#diff-fb5aa1cd1261d08d02db6f7dc314d9ab) は、基になるすべての決定を非表示にするために、正確に編成されています。 HTML テンプレートを更新して、[プロファイルの編集] ページなどの新しいリンクを含めるだけで済みます。

```html
<a href='{{_build_auth_url(authority=config["B2C_PROFILE_AUTHORITY"])}}'>Edit Profile</a>
```

### B2C でのリソース所有者パスワード認証情報 (ROPC)

Warning

セキュリティ リスクのため、パブリック クライアント アプリケーションではリソース所有者パスワード資格情報 (ROPC) フローが非推奨になりました。 Microsoft では、より安全な認証フローを使用することをお勧めします。 [ROPC から移行](https://aka.ms/msal-ropc-migration)する方法に関する公式ガイダンスに従ってください。

B2C シナリオと B2C 以外のシナリオの間に API の違いはありません。 次のコンテンツは、ミニチュートリアルとして機能します。

- Azure AD B2C テナントで、新しいユーザー フローを作成し、[**ROPC を使用してサインイン**] を選択します。 これにより、テナントの ROPC ユーザー フローが有効になります。 詳細については、「 [リソース所有者のパスワード資格情報フローの構成](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/configure-ropc) 」を参照してください。
- ROPC ユーザー フローを含む機関で MSAL インスタンスを作成すると、 [`acquire_token_by_username_password(...)`](https://msal-python.readthedocs.io/en/latest/#msal.PublicClientApplication.acquire_token_by_username_password) は通常どおりに動作します。
- 制限事項: これは **、ローカル アカウント** (電子メールまたはユーザー名を使用して B2C に登録する) でのみ機能します。 このフローは、B2C (Facebook、Google など) でサポートされている IdP のいずれかにフェデレーションする場合は機能しません。

Microsoft[、リソース所有者のパスワード資格情報の付与を使用しないことをお勧めします](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)。 ほとんどのシナリオでは、より安全な代替手段が利用でき、推奨されます。 このフローでは、アプリケーションに非常に高い信頼が必要であり、他のフローに存在しないリスクが伴います。 このフローは、より安全なフローが実行可能ではない場合にのみ使用してください。 詳細については、 [ユーザー名とパスワード](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/username-password-authentication) のガイダンスを参照してください。

### MSAL Python での B2C のキャッシュ

#### 既知の問題

MSAL Python トークン キャッシュの使用パターンは[、フィルターとして`get_accounts(...)` パラメーターをサポートする、`username`](https://msal-python.readthedocs.io/en/latest/#msal.ClientApplication.get_accounts)によって既存のすべてのアカウントに対してクエリを実行することから始まります。 そのユーザー名データは、ID トークン内の `preferred_username` 要求によって設定されます。

既定では、Azure AD B2C シナリオの多くでその要求が欠落しています。

顧客への影響は、アカウントを表示しようとすると、ユーザー名フィールドが空になることです。 Web アプリで認証コード フローを使用していて、ユーザーごとに 1 つのアカウントのみを処理している場合は、これは面倒ではありません。 ただし、ROPC フローを使用していて、エンド ユーザーのユーザー名が既にわかっている場合は、`accounts = app.get_accounts(username="john.doe@contoso.com")`が`"john.doe@contoso.com"`と一致しないため、`""`は空の結果を返します。

回避策は、B2C ユーザー フローをカスタマイズして `preferred_username` 要求を設定して返すか、特定のユーザー名パラメーターを指定せずに `app.get_accounts()` を呼び出すだけです。

### Samples

| Sample | Platform | 説明 |
| --- | --- | --- |
| [Microsoft ID Python Web アプリ](https://github.com/Azure-Samples/ms-identity-python-webapp) | Pythonをサポートするすべてのプラットフォーム | MSAL Pythonを使用して Azure Active Directory B2C 経由でユーザーを認証し、結果のトークンを使用して Web API にアクセスする方法を示す Web アプリ。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/client-capabilities"} -->
## クライアントの機能 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/client-capabilities
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: Microsoft Entra サービスには、条件付きアクセス ポリシーなど、特定のシナリオに適用できる機能とポリシーが用意されています。

Microsoft Entra サービスには、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/conditional-access) ポリシーなど、特定のシナリオに適用できる機能とポリシーが用意されています。 Microsoft Entra サービスは、エンド ツー エンドのシナリオが機能するために、クライアント アプリケーションが機能に参加しているか、ポリシーを処理できるかを判断する必要があります。

- アプリケーション オブジェクトの**クライアント機能**パラメーターを使用すると、クライアント アプリケーションは、ポリシーまたは機能を処理するシナリオと準備状況に準拠していることを示すことができるため、Microsoft Entra IDはクライアントに適用できます。

    たとえば、条件付きアクセス シナリオのコンテキストでは、クライアント機能 `CP1` を追加すると、クライアントはリソース プロバイダー (MS Graph など) からの `claims challenge` を処理できることを意味します。 したがって、STS (セキュリティ トークン サービス) Microsoft Entraは、リソース プロバイダー (MS Graph など) からの要求チャレンジにつながる可能性がある要求をアクセス トークンに出力します。
- これらの機能は、クライアント機能文字列の一覧である `client_capabilities` パラメーターを使用してアプリケーション オブジェクトを作成するときに設定されます。

    ```python
    app = msal.PublicClientApplication(client_id="client_id", authority="your_authority", client_capabilities = ["CP1"])
    ```
- これらはアプリケーション レベルで設定されると、すべての要求でMicrosoft Entra承認エンドポイントとトークン エンドポイントに送信されます。
- 機能を設定する場合、クライアント アプリケーションは、ポリシーまたは機能がアプリケーションで処理されていることを確認する必要があります。

    - 上記の例では、アプリケーション オブジェクトにこのような機能 (`CP1`) が設定されている場合、クライアント アプリケーションは、リソース プロバイダー (MS Graph など) からの要求チャレンジがアプリケーションで処理されるようにする必要があります。 それについての詳細は、[クレーム チャレンジの処理](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/conditional-access#handling-claim-challenge-in-msal-python)で確認できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/client-credentials"} -->
## クライアントの資格情報 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/client-credentials
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: MSAL Pythonには、アプリケーション シークレットと証明書という 2 種類のクライアント資格情報があります。

MSAL Pythonには、次の 2 種類のクライアント資格情報があります。

- アプリケーション シークレット
- 証明書

### アプリケーション シークレットを含むクライアント資格情報

Microsoft Entra IDを使用して機密クライアント アプリケーションを登録すると、クライアント シークレット (アプリケーション パスワードの一種) が生成されます。

#### アプリケーション登録ポータルを使用したクライアント シークレットの登録

クライアント資格情報の管理は、アプリケーションの **[証明書とシークレット** ] ページで行われます。

[Image: 画像]

- アプリケーション シークレット (名前付きクライアント シークレット) は、**新しいクライアント シークレット**を選択すると、機密クライアント アプリケーションの登録中にMicrosoft Entra IDによって生成されます。 その時点で、[ **保存**] を選択する前に、アプリで使用するシークレット文字列をクリップボードにコピーする必要があります。 この文字列は、今後再び表示されることはありません。

#### クライアント シークレットの使用

MSAL Pythonクライアント資格情報は、ADAL Python内の資格情報と似ていますが、クライアント資格情報はアプリケーションの構築時にパラメーターとして渡される点が異なります。 この場合、クライアント シークレットはパラメーターとして渡されます。 次に、機密クライアント アプリケーションが構築されると、スコープをパラメーターとして `acquire_token_for_client` が呼び出されます。

### 証明書を使用したクライアント資格情報

アプリケーションがMicrosoft Entra IDに登録されると、証明書の公開キーがアップロードされます。 アプリケーションの構築時に、 `thumbprint` と `private_key_file` がクライアント資格情報として渡されます。 トークンを取得する場合、クライアント アプリケーションはスコープをパラメーターとして渡して `acquire_token_for_client` メソッドを呼び出す必要があります。

クライアント資格情報フローを実装するときに使用する証明書と秘密キーを生成する手順は次のとおりです。

1. キーを生成します。

    `openssl genrsa -out server.pem 2048`
2. 証明書要求を作成します。

    `openssl req -new -key server.pem -out server.csr`
3. 証明書を生成します。

    `openssl x509 -req -days 365 -in server.csr -signkey server.pem -out server.crt`
4. この証明書 (`server.crt`) は、アプリケーション設定のAzure portalにアップロードする必要があります。 この証明書を保存すると、取得トークン呼び出しに必要なこの証明書の拇印がポータルに表示されます。 キーは、最初の手順で生成した `server.pem` キーになります。
5. これで、次のように MSAL Pythonの証明書を使用して、クライアント資格情報フローの資格情報を作成できます。

```python
client_credential = {
    "thumbprint": <thumbprint of cert file>,
    "private_key": <private key from the private_key_file>
 }
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/conditional-access"} -->
## 条件付きアクセスと要求チャレンジ - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/conditional-access
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: トークンをサイレントで取得すると、アクセスしようとしている API で条件付きアクセス要求チャレンジ (MFA ポリックなど) が必要な場合に、アプリケーションでエラーが発生する可能性があります。

### 経歴

トークンをサイレントに取得すると、アクセスしようとしている API で [条件付きアクセス要求チャレンジ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/conditional-access-dev-guide) (MFA ポリシーなど) が必要な場合、アプリケーションでエラーが発生する可能性があります。

このエラーを処理するパターンは、MSAL を使用して対話形式でトークンを取得することです。 トークンを対話形式で取得すると、ユーザーにプロンプトが表示され、必要な条件付きアクセス ポリシーを満たす機会が与えられます。

Note

ポリシーに準拠するためにユーザーの操作を必要としない特定の条件付きアクセス ポリシーがあります。 このような場合は、acquire\_token\_silent メソッドを使用して返される要求チャレンジを処理し、ユーザーの操作を求めないようにすることをお勧めします。

場合によっては、条件付きアクセスが必要な API を呼び出す際に、API から返されるエラー内でクレーム チャレンジを受け取ることがあります。 たとえば、条件付きアクセス ポリシーでマネージド デバイス (Intune) を使用する場合、エラーは [AADSTS53000 のようになります。このリソースや同様のリソースにアクセスするには、デバイスを管理する必要があります](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-aadsts-error-codes) 。 この場合、取得トークン呼び出しで要求を渡して、ユーザーが適切なポリシーを満たすように求めることができます。

### MSAL Pythonでの要求チャレンジの処理

条件付きアクセスを必要とする API を呼び出す場合、アプリケーションは要求チャレンジ エラーを処理する必要があります。 これは、Claims プロパティが空にならない [MSAL Python エラー応答](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-handling-exceptions?tabs=python)として表示されます。

要求チャレンジを処理するには、`claims_challenge` メソッドの `get_authorization_request_url()` パラメーターを使用する必要があります。 対話を必要としない場合は、`claims_challenge` メソッドで `acquire_token_silent()` パラメーターを使用する必要があります。メソッドは更新トークンを使用して新しいトークンを取得します。

**要求チャレンジ** は、アクセス トークンが承認されておらず、新しいアクセス トークンが必要な場合に、リソース プロバイダー (MS Graph など) が応答する *www-authenticate* ヘッダーのディレクティブです。 リソース プロバイダーからの要求チャレンジの例 (例: MS Graph) は次のようになります。 これは、401 である必要がある HTTP 状態コードと、上記のフィールドを含む *www-authenticate* ヘッダーで構成されます。

```http
HTTP 401; Unauthorized 
www-authenticate=Bearer realm="", 
authorization_uri="https://login.microsoftonline.com/common/oauth2/authorize", 
error="insufficient_claims", 
claims="returned_claims"
```

クライアントは要求を抽出し、acquire\_token\_by\_\* メソッドに渡す必要があります。 リソースから返される要求値が base64 でエンコードされている場合は、MSAL を呼び出す前にデコードする必要があります。 MSAL acquire\_token メソッドは、要求を json 文字列として受け入れます。 これは、リソース プロバイダーから返された要求を承認コード フローを使用して処理する方法の例です。

```python
claims_challenge = "claims returned from resource provider"
# if claims parameter is encoded, decode it and use the decoded claims
# decoded_claims_challenge = str(base64.b64decode(claims), "utf-8")

result = None

# Since you were using your previous AT and then met a claims challenge,
# you should've already be in a context with the current signed-in user.
# Here we assume you somehow already have that account,
# or you could possibly get it like this:
accounts = app.get_accounts()
assert accounts
account = accounts[0]

result = app.acquire_token_silent(scope, account=account, claims_challenge=claims_challenge)
if not result:
    # Auth endpoint call for interactive login (you can use auth url helper)
    auth_url = app.get_authorization_request_url(..., claims_challenge=claims_challenge)
    # Make a request to this auth_url to get back authorization_code
    result = app.acquire_token_by_authorization_code(..., claims_challenge=claims_challenge)
```

- この新しいアクセス トークンは、リソースへの要求で使用できるようになりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/instance-metadata-caching"} -->
## インスタンス メタデータのキャッシュ - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/instance-metadata-caching
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: すべての開発者は、プログラムをより速く実行することを望んでいます。 この記事では、ワンライナーを追加して MSAL Python搭載アプリを作成し、トークンを約 1.5 倍から 2 倍速く取得する方法について説明します。

すべての開発者は、プログラムをより速く実行することを望んでいます。 この記事では、ワンライナーを追加して MSAL Python搭載アプリを作成し、トークンを約 1.5 倍から 2 倍速く取得する方法について説明します。

### 準備

1. まず、この実験を示す既存のサンプル スクリプトを選択します。 ユーザーの操作は必要ないため、 [ユーザー名パスワードのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.0.0/sample/username_password_sample.py)を選択します。そのため、時間の測定は人間の低速な操作の影響を受けなくなります。
2. 命令ごとにサンプルの config.json を構成 [します](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.0.0/sample/username_password_sample.py#L2-L15)。
3. 実行時間を計測するには、サンプルの冒頭に以下のオプションのスニペットを追加するとよいでしょう。

    ```python
    import time; t = time.time()
    ```

    次に、サンプルの最後に次の行を追加します。

    ```python
    print("It took", time.time() - t, "second(s)")
    ```

    トークン取得の一部ではないため、[実際の Microsoft Graph API呼び出しの行](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.0.0/sample/username_password_sample.py#L62-L65)をコメント アウトするのが理想的です。 最後に、必要に応じて、 [これらの 2 つの行](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.0.0/sample/username_password_sample.py#L31-L32) のコメントを解除して、ログ記録を有効にして、内部で何が起こるかを確認することもできます。

### の前に

ここで、複数回実行します。 次のような出力が表示されます。

```bash
$ python username_password_sample.py config.json
It took 0.9160797595977783 second(s)

$ python username_password_sample.py config.json
It took 0.7800290584564209 second(s)

$ python username_password_sample.py config.json
It took 0.8385567665100098 second(s)
```

そのため、トークンを取得するのに約 0.8 秒以上かかりました。

### 実際のマジック

それでは、楽しくお楽しみください。

これを 1 回実行してサード パーティ製モジュール "requests-cache" をインストールします。 `pip install requests-cache`し、サンプルの最初に次の行を追加します。

```python
import requests_cache; requests_cache.install_cache()
```

### 後の

ここで、複数回実行します。 次のような出力が表示されます。

```bash
$ python username_password_sample.py config.json
It took 0.41900205612182617 second(s)

$ python username_password_sample.py config.json
It took 0.480008602142334 second(s)

$ python username_password_sample.py config.json
It took 0.44204020500183105 second(s)
```

そのため、トークンを取得するのに約 0.4 秒以上かかりました。

### どうなっているのですか。

トークンを取得する前に、MSAL は、いくつかのメタデータを検索するために複数の検出エンドポイントにヒットする必要があります。 これらのメタデータは頻繁に変更されないため、キャッシュして再利用できます。 これらはすべて、HTTP レイヤーで行われる標準的なプラクティスです。そのため、MSAL Pythonは単独でそのような動作を実装する必要はありません。汎用 HTTP キャッシュ ライブラリによってこのジョブが実行されます。

この場合、MSAL Pythonは既に `requests` という名前の一般的な HTTP ライブラリを使用しています。その後、`requests-cache`にパッチを適用し、HTTP キャッシュの動作を自動的に提供するように設計された別の汎用ライブラリ`requests`があります。 さらに良いことに、既定ではそれをディスクに保存するため、コマンドラインのサンプルでも、毎回新しいプロセスとして実行されても、そのような HTTP キャッシュの恩恵を引き続き受けられます。

実際、このトリックは、MSAL のPythonだけでなく、アプリの他のすべてのキャッシュ可能な HTTP トラフィックを改善します。

### まとめ

これで、1 行のコードだけで 1.5 倍から 2 倍のパフォーマンス向上が実現しました。 また、HTTP を利用するすべてのアプリに汎用で適用できます。

ハッピーハッキング!
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/linux-broker-py"} -->
## Linux 上の認証ブローカーでの MSAL Pythonの使用 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/linux-broker-py
- Service: msal / msal-python
- Article date: 2025-06-03
- Summary: MSAL は、Microsoftシングル サインオン for Linux を呼び出すことができます。これは、Intune Portal の依存関係として付属するコンポーネントです。 このコンポーネントは認証ブローカーとして機能し、アプリのユーザーはブローカーに知られているアカウントとの統合を利用できます。

Microsoft Authentication Library (MSAL) はソフトウェア開発キット (SDK) です。これにより、アプリは Linux ディストリビューションとは独立して配布される Linux コンポーネントである Microsoft シングル サインオンを Linux ブローカーに呼び出しますが、`sudo apt install microsoft-identity-broker`または`sudo dnf install microsoft-identity-broker`を使用してパッケージ マネージャーを使用してインストールされます。

このコンポーネントは認証ブローカーとして機能し、アプリのユーザーは、Linux に知られているアカウント (ブローカーから使用するアプリの Linux セッションにサインインしたアカウントなど) との統合の恩恵を受けることができます。

ブローカーは、Microsoft ([ポータル サイト](https://learn.microsoft.com/ja-jp/mem/intune-service/user-help/enroll-device-linux) など) によって開発されたアプリケーションの依存関係としてもバンドルされています。 インストールされているブローカーのインストールの例として、Linux コンピューターが[、Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)などのエンドポイント管理ソリューションを介して会社のデバイスフリートに登録されている場合があります。

### ブローカーとは

認証ブローカーは、接続されているアカウントの認証ハンドシェイクとトークンメンテナンスを管理するユーザーのマシン上で実行されるアプリケーションです。 Linux オペレーティング システムでは、認証ブローカーとして Linux 用の Microsoft シングル サインオンが使用されます。 開発者と顧客にとって、次のような多くの利点があります。

- **シングル サインオンを有効にする**: アプリを使用すると、ユーザーがMicrosoft Entra IDで認証する方法を簡略化し、Microsoft Entra ID更新トークンを流出や誤用から保護できます
- **セキュリティの強化。** 多くのセキュリティ強化は、アプリケーション ロジックを更新する必要なく、ブローカーと共に提供されます。
- **機能のサポート。** ブローカー開発者の助けを借りて、豊富な OS とサービス機能にアクセスできます。
- **システム統合。** 組み込みのアカウント ピッカーでブローカー のプラグ アンド プレイを使用するアプリケーションにより、ユーザーは同じ資格情報を何度も再入力する代わりに、既存のアカウントをすばやく選択できます。
- **トークン保護。** Linux 向け Microsoft シングル サインオンでは、リフレッシュ トークンがデバイスにバインドされるようにします。

### ブローカーの使用をオプトインする方法

1. MSAL Python ライブラリでは、WSL とスタンドアロン Linux の両方でブローカーを有効にする`enable_broker_on_linux`フラグを導入しました。
    - Azure CLIの WSL でのみブローカー サポートを有効にすることが目的の場合は、WSL でのみ `enable_broker_on_wsl` フラグをアクティブ化するように Azure CLI アプリ コードを変更することを検討できます。
    - クロスプラットフォーム アプリケーションを作成する場合は、「`enable_broker_on_windows`」の記事で説明されているように、も使用する必要があります。
    - 次のオプトイン パラメーターの任意の組み合わせを true に設定できます。

| オプトイン フラグ | アプリが～上で実行される場合 | アプリでこれをデスクトップ プラットフォームリダイレクト URI として登録Azure portal |
| --- | --- | --- |
| Windows でブローカーを有効にする | Windows 10+ | ms-appx-web://Microsoft.AAD.BrokerPlugin/your\_client\_id |
| enable\_broker\_on\_wsl | WSL | ms-appx-web://Microsoft.AAD.BrokerPlugin/your\_client\_id |
| enable\_broker\_on\_mac | ポータル サイトがインストールされている Mac | msauth.com.msauth.unsignedapp://auth |
| enable\_broker\_on\_linux | Intune がインストールされている Linux | `https://login.microsoftonline.com/common/oauth2/nativeclient` (有効にする必要があります) |

1. アプリケーションでは、ブローカー固有のリダイレクト URI をサポートする必要があります。 `Linux`具体的には、リダイレクト URI の URL は次のようにする必要があります。

    ```text
    https://login.microsoftonline.com/common/oauth2/nativeclient
    ```
2. ブローカーを使用するには、PyPI のコア MSAL に加えて、ブローカー関連のパッケージをインストールする必要があります。

    ```python
    pip install "msal[broker]>=1.33.0b1,<2"
    ```
3. 構成したら、 `acquire_token_interactive` を呼び出してトークンを取得できます。

    ```python
    result = app.acquire_token_interactive(["User.ReadBasic.All"],
                        parent_window_handle=app.CONSOLE_WINDOW_HANDLE)
    ```

### ブローカー サポートのパラメーター

MSAL Pythonでブローカー サポートを構成するには、次のパラメーターを使用できます。 これらのパラメーターは、 `PublicClientApplication` コンストラクターまたは `acquire_token_interactive` メソッドに渡すことができます。

| Parameters: | タイプ | 説明 |
| --- | --- | --- |
| Windows でブローカーを有効にする | `boolean` | この設定は、アプリが Windows 10 以降で実行されている場合にのみ有効です。 このパラメーターの既定値は None です。つまり、MSAL はブローカーを使用しません。 `New in MSAL Python 1.25.0.` |
| enable\_broker\_on\_wsl | `boolean` | この設定は、アプリが WSL で実行されている場合にのみ有効です。 このパラメーターの既定値は None です。つまり、MSAL はブローカーを使用しません。 `New in MSAL Python 1.25.0`. |
| enable\_broker\_on\_mac | `boolean` | この設定は、ポータル サイトがインストールされている Mac でアプリが実行されている場合にのみ有効です。 このパラメーターの既定値は None です。つまり、MSAL はブローカーを使用しません。 `New in MSAL Python 1.31.0`. |
| enable\_broker\_on\_linux | `boolean` | この設定は、アプリが Intune がインストールされた Linux 上で実行されている場合にのみ有効です。 このパラメーターの既定値は None です。つまり、MSAL はブローカーを使用しません。 `New in MSAL Python 1.33.0`. |
| parent\_window\_handle | `int` | *オプション* |

#### parent\_window\_handleに関する注意事項

linux では使用されていない場合でも、 `parent_window_handle` パラメーターが必要です。 GUI アプリケーションの場合、ログイン プロンプトの場所はアドホックで決定され、現在は特定のウィンドウにバインドできません。 今後の更新では、このパラメーターを使用して *実際* の親ウィンドウが決定されます。

| 状態 | 説明 |
| --- | --- |
| アプリはブローカーを利用したくない | parent\_window\_handleを指定する必要はありません |
| アプリがブローカーを使用することを選択する | parent\_window\_handleが必要です |
| アプリは、Windowsまたは Mac システムで実行されている GUI アプリです | サインイン ウィンドウがアプリケーションのウィンドウの前面に表示されるように、そのウィンドウ ハンドルを指定する必要があります |
| アプリは、Windowsまたは Mac システムで実行されているコンソール アプリです | プレースホルダーを使用できます `PublicClientApplication.CONSOLE_WINDOW_HANDLE` |
| アプリはクロスプラットフォーム アプリケーションを意図しています | アプリは、`enable_broker_on_windows`に関する記事で説明されているように、を使用する必要があります。 |

### MSAL Pythonのブローカー サポートのフォールバック動作

MSAL はエラーアウトするか、非ブローカー フローにサイレント フォールバックします。

1. MSAL はenable\_broker\_を無視します... ブローカーでサポートされていないことがわかっている認証フローでブローカーをバイパスします。 これには、ADFS、B2C などが含まれます。 その他の「could-use-broker」シナリオについては、以下を参照してください。
2. アプリ開発者がブローカーを使用することをオプトインしたが、直接依存関係 "中間層" パッケージがインストールされていない場合、MSAL エラーが発生します。 エラー メッセージは、アプリ開発者が正しい依存関係 msal[broker] を宣言するように指示します。 このエラーはアプリ開発者が対処できるものなので、ここではエラーを返します。
3. MSAL は、オプトインされており、依存関係がインストールされているものの初期化に失敗した場合、ブローカーを自動的に無効化し、非ブローカー方式にフォールバックします。 これは、OS が古すぎるか、基になるブローカー コンポーネントが何らかの方法で使用できないデバイスで発生する可能性があります。 アプリ開発者やエンド ユーザーがここでできることはあまりありません。 最終的に、条件付きアクセス ポリシーによって、ユーザーは別のデバイスに切り替える必要があります。
4. ブローカーがオプトイン、インストール、初期化されたが、後続のトークン要求が失敗した場合、MSAL エラーが発生します。

Important

ブローカー関連のパッケージがインストールされておらず、認証ブローカーを使用しようとすると、 `ImportError: You need to install dependency by: pip install "msal[broker]>=1.xx,<2"`というエラーが表示されます。

Note

linux では使用されていない場合でも、 `parent_window_handle` パラメーターが必要です。 GUI アプリケーションの場合、ログイン プロンプトの場所はアドホックで決定され、現在は特定のウィンドウにバインドできません。 今後の更新では、このパラメーターを使用して *実際* の親ウィンドウが決定されます。

### トークンのキャッシュ

認証ブローカーは、更新とアクセス トークンのキャッシュを処理します。 カスタム キャッシュを設定する必要はありません。

### サンプル アプリのビルド

MSAL [Python GitHub リポジトリ](https://github.com/AzureAD/microsoft-authentication-library-for-python/)内の Linux 上の認証ブローカーで MSAL Pythonを使用する方法を示すサンプル アプリを見つけることができます。 サンプル アプリは `samples/console_app` ディレクトリにあり、認証にブローカーを使用する方法の例が含まれています。

#### **アプリの登録**

Azure ポータルでアプリの登録を更新し、Linux 用のブローカー固有のリダイレクト URI を含めます。

```text
https://login.microsoftonline.com/common/oauth2/nativeclient
```

#### **Linux の依存関係**

まず、Linux ディストリビューションに python3 がインストールされているかどうかを確認します。

```bash
python3 --version
```

そうでない場合は、ディストリビューションのパッケージ マネージャーを使用してインストールします。

## [Ubuntu](#tab/ubuntudep)
debian/Ubuntu ベースの Linux ディストリビューションにインストールするには:

```bash
sudo add-apt-repository -y universe
sudo apt update
sudo apt install python3 python3-pip libwebkit2gtk-4.1-dev -y
```

## [Red Hat Enterprise Linux](#tab/rheldep)
Red Hat/Fedora ベースの Linux ディストリビューションにインストールするには:

```bash
sudo dnf install python3 python3-pip libubsan webkitgtk4-devel -y
```

RHEL 8 の場合は、EPEL リポジトリから OpenSSL3 パッケージをインストールする必要もあります。

```bash
sudo dnf install -y "https://dl.fedoraproject.org/pub/epel/epel-release-latest-8.noarch.rpm"
sudo dnf update
sudo dnf install -y openssl3-devel openssl3-libs
```

---

#### **Python依存関係**

ブローカーを使用するには、PyPI のコア MSAL に加えて、ブローカー関連のパッケージをインストールする必要があります。

```python
pip install "msal[broker]>=1.33.0b1,<2"
```

#### プロジェクトを作成

構成したら、 `acquire_token_interactive` を呼び出してトークンを取得できます。

```python
import sys  # For simplicity, we'll read config file from 1st CLI param sys.argv[1]
import json
import logging
import requests
import msal

# Optional logging
# logging.basicConfig(level=logging.DEBUG)

var_authority = "https://login.microsoftonline.com/common"
var_client_id = "your-client-id-here"  # Replace with your app's client ID
var_username = "your-username-here"  # Replace with your username, e.g., "
var_scope = ["User.ReadBasic.All"]
# Removed unused variable to avoid confusion

# Create a preferably long-lived app instance which maintains a token cache (Default cache is in memory only).
app = msal.PublicClientApplication(
    var_client_id, 
    authority=var_authority,
    enable_broker_on_windows=True,
    enable_broker_on_wsl=True
    )

# The pattern to acquire a token looks like this.
result = None

# Firstly, check the cache to see if this end user has signed in before
accounts = app.get_accounts(username=var_username)
if accounts:
    logging.info("Account(s) exists in cache, probably with token too. Let's try.")
    result = app.acquire_token_silent(var_scope, account=accounts[0])

if not result:
    logging.info("No suitable token exists in cache. Let's get a new one from AAD.")
    
    result = app.acquire_token_interactive(var_scope,parent_window_handle=app.CONSOLE_WINDOW_HANDLE)
    
if "access_token" in result:
    print("Access token is: %s" % result['access_token'])

else:
    print(result.get("error"))
    print(result.get("error_description"))
    print(result.get("correlation_id"))  # You may need this when reporting a bug
    if 65001 in result.get("error_codes", []):  # Not mean to be coded programatically, but...
        # AAD requires user consent for U/P flow
        print("Visit this to consent:", app.get_authorization_request_url(config["scope"]))
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/linux-broker-py-wsl"} -->
## Linux 用 Windows サブシステムでの MSAL Pythonの使用 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/linux-broker-py-wsl
- Service: msal / msal-python
- Article date: 2025-05-08
- Summary: MSAL Pythonと Linux ブローカーの Microsoft シングル サインオンを使用して、WSL アプリでMicrosoft Entra ID認証を統合する方法について説明します。

MSAL は、Linux ディストリビューションとは無関係に配布される Linux コンポーネントである Microsoft シングル サインオンを Linux に呼び出すことが可能ですが、`sudo apt install microsoft-identity-broker`または`sudo dnf install microsoft-identity-broker`を使用してパッケージ マネージャーを使用してインストールされます。

このコンポーネントは認証ブローカーとして機能し、アプリのユーザーは、Linux に知られているアカウント (ブローカーから使用するアプリの Linux セッションにサインインしたアカウントなど) との統合の恩恵を受けることができます。 また、[ポータル サイト](https://learn.microsoft.com/ja-jp/mem/intune-service/user-help/enroll-device-linux)など、Microsoftによって開発されたアプリケーションの依存関係としてバンドルされています。 これらのアプリケーションは、Linux コンピューターが、[Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)などのエンドポイント管理ソリューションを介して会社のデバイス フリートに登録されるときにインストールされます。

Linux で認証ブローカーを使用すると、ユーザーがアプリケーションからMicrosoft Entra IDで認証する方法を簡略化し、Microsoft Entra ID更新トークンを流出や誤用から保護する将来の機能を利用できます。

MSAL Pythonを使用して WSL アプリで SSO を有効にするには、キーチェーンが設定され、ロック解除されていることを確認する必要があります。MSAL はキーリング デーモンと通信するために`libsecret`を使用するためです。

### WSL 認証フローの例

Microsoft Entra IDで認証する必要がある WSL アプリがある場合、対話型要求の認証フローは次のようになります。

[Image: WSL 内からの認証フロー]

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

### Linux パッケージの依存関係

Linux プラットフォームに次の依存関係をインストールします。

- `libsecret-tools` は、Linux キーチェーンとのインターフェイスに必要です

## [Ubuntu](#tab/ubuntudep)
debian/Ubuntu ベースの Linux ディストリビューションにインストールするには:

```bash
sudo add-apt-repository -y universe
sudo apt update
sudo apt install libwebkit2gtk-4.1-dev libsecret-1-0  -y

#from Powershell, run
wsl.exe --shutdown
```

## [Red Hat Enterprise Linux](#tab/rheldep)
Red Hat/Fedora ベースの Linux ディストリビューションにインストールするには:

```bash
sudo dnf install libsecret-1-0 webkitgtk4-devel libubsan -y
```

RHEL 8 の場合は、EPEL リポジトリから OpenSSL3 パッケージをインストールする必要もあります。

```bash
sudo dnf install -y "https://dl.fedoraproject.org/pub/epel/epel-release-latest-8.noarch.rpm"
sudo dnf update
sudo dnf install -y openssl3-devel openssl3-libs

#from Powershell, run
wsl.exe --shutdown
```

---

Important

キーチェーンが意図したとおりに機能するようにするには、次のことを確認してください。1. 依存関係をインストールします。2. WSL を再起動します。3. キーチェーンを構成します。 正しい順序で手順を実行しないと、キーチェーンに "パスワード キーチェーン" オプションが表示されなくなります。

### WSL でキーリングを設定する

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

プロジェクトを構成する方法については、「[MSAL を使用してネイティブ Linux アプリで SSO を有効にする」Python](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/linux-broker-py)を参照してください。

#### **Python依存関係**

ブローカーを使用するには、PyPI のコア MSAL に加えて、ブローカー関連のパッケージをインストールする必要があります。

```python
pip install "msal[broker]>=1.33.0b1,<2"
```

#### サンプル アプリを実行する

構成したら、 `acquire_token_interactive` を呼び出してトークンを取得できます。 次を `wsl_broker.py`として保存します。

```python
import sys  # For simplicity, we'll read config file from 1st CLI param sys.argv[1]
import json
import logging
import requests
import msal

# Optional logging
# logging.basicConfig(level=logging.DEBUG)

var_authority = "https://login.microsoftonline.com/common"
var_client_id = " your-client-id-here"  # Replace with your app's client ID
var_username = "your-username-here"  # Replace with your username, e.g., "
var_scope = ["User.ReadBasic.All"]

# Create a preferably long-lived app instance which maintains a token cache (Default cache is in memory only).
app = msal.PublicClientApplication(
    var_client_id, 
    authority=var_authority,
    enable_broker_on_windows=True,
    enable_broker_on_wsl=True
    )

# The pattern to acquire a token looks like this.
result = None

# Firstly, check the cache to see if this end user has signed in before
accounts = app.get_accounts(username=var_username)
if accounts:
    logging.info("Account(s) exists in cache, probably with token too. Let's try.")
    result = app.acquire_token_silent(var_scope, account=accounts[0])

if not result:
    logging.info("No suitable token exists in cache. Let's get a new one from AAD.")
    
    result = app.acquire_token_interactive(var_scope,parent_window_handle=app.CONSOLE_WINDOW_HANDLE)
    
if "access_token" in result:
    print("Access token is: %s" % result['access_token'])

else:
    print(result.get("error"))
    print(result.get("error_description"))
    print(result.get("correlation_id"))  # You may need this when reporting a bug
    if 65001 in result.get("error_codes", []):  # Not mean to be coded programatically, but...
        # AAD requires user consent for U/P flow
        print("Visit this to consent:", app.get_authorization_request_url(config["scope"]))
```

#### サンプルを実行する

次のコマンドを使用してサンプル アプリを実行します。

```bash
python wsl_broker.py
```

次のようなプロンプトが表示されるはずです。

- ユーザー名/資格情報を入力する
- キーリング パスワードを入力する
- その後、アプリはトークンを取得し、コンソールに出力します
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/logging"} -->
## Logging - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/logging
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: MSAL Pythonのログ記録は、標準的なPythonログ記録メカニズムを使用するように設計されているため、Pythonログ記録に関する以前のすべての知識が MSAL Pythonに適用されます。

MSAL Pythonのログ記録は、標準的なPythonログ記録メカニズムを使用するように設計されているため、Pythonログ記録に関する以前のすべての知識が MSAL Pythonに適用されます。

- 既定では、Python スクリプトのログ記録はオフになっています。 Python スクリプト全体ですべてのモジュールのデバッグ ログを有効にする場合は、`logging.basicConfig(level=logging.DEBUG)`を使用します。
- MSAL Python ログのほとんどは既にデバッグ レベルであり、既定ではオフになっています。 ただし、Python スクリプト内の他のモジュールをデバッグするためにデバッグ ログを有効にし、そのため MSAL の出力は抑制したい場合は、MSAL Python が使用するロガーを単にオフにするだけです: `logging.getLogger("msal").setLevel(logging.WARN)`。
- MSAL Pythonでは、個人を特定できる情報 (PII) は記録されません。 つまり、MSAL for Python には PII ログを有効にするトグルすらありません。 アプリ開発者は引き続き標準のPythonログを使用して、コンテンツをログに記録できます。 これにより、アプリは機密性の高いデータを安全に処理し、規制要件に従う責任を負います。

Pythonログに関するトピックの詳細については、[Python標準的なログ記録のチュートリアル](https://docs.python.org/3/howto/logging.html#logging-basic-tutorial)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/macos-broker"} -->
## macOS での認証ブローカーでの MSAL Pythonの使用 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/macos-broker
- Service: msal / msal-python
- Article date: 2024-09-06
- Summary: macOS で認証ブローカーを使用すると、ユーザーがアプリケーションからMicrosoft Entra IDを使用して認証する方法を簡略化できるほか、トークン バインディング、流出や誤用から発行されたトークンを保護するなどの高度な機能を利用できます。

Note

macOS 認証ブローカーのサポートは、 `msal` バージョン 1.31.0 で導入されています。

macOS で認証ブローカーを使用すると、ユーザーがアプリケーションからMicrosoft Entra IDを使用して認証する方法を簡略化したり、Microsoft Entra ID更新トークンを流出や誤用から保護する将来の機能を利用したりできます。

認証ブローカーは macOS にはプレインストール**されません**が、Microsoftによって開発されたアプリケーション ([ポータル サイト](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos) など) です。 これらのアプリケーションは、通常、macOS コンピューターが[、Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)などのエンドポイント管理ソリューションを介して会社のデバイスフリートに登録されている場合にインストールされます。 Microsoft Identity Platform を使用して設定された Apple デバイスの詳細については、[Apple デバイス用の Microsoft Enterprise SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)を参照してください。

### 使用方法

ブローカーを使用するには、PyPI のコア MSAL に加えて、ブローカー関連のパッケージをインストールする必要があります。

```bash
pip install msal[broker]>=1.31,<2
```

Important

ブローカー関連のパッケージがインストールされておらず、認証ブローカーを使用しようとすると、 `ImportError: You need to install dependency by: pip install "msal[broker]>=1.31,<2"`というエラーが表示されます。

通常、macOS では、[パブリック クライアント](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications) Python アプリケーションはシステム ブラウザーを介して[トークンを取得](https://learn.microsoft.com/ja-jp/entra/msal/python/getting-started/acquiring-tokens)します。 代わりに macOS システムにインストールされている認証ブローカーを使用するには、 `PublicClientApplication` コンストラクターに追加の引数を渡す必要があります。 `enable_broker_on_mac`。

```python
from msal import PublicClientApplication
 
app = PublicClientApplication(
    "CLIENT_ID",
    authority="https://login.microsoftonline.com/common",
    enable_broker_on_mac =True)
```

Important

クロスプラットフォーム アプリケーションを作成する場合は、「`enable_broker_on_windows`」の記事で説明されているように、も使用する必要があります。

コンストラクターの変更に加えて、アプリケーションではブローカー固有のリダイレクト URI をサポートする必要があります。 *署名されていない*アプリケーションの場合、URI は次のようになります。

```text
msauth.com.msauth.unsignedapp://auth 
```

署名されたアプリケーションの場合、リダイレクト URI は次のようになります。

```text
msauth.BUNDLE_ID://auth
```

Entra ポータル内のアプリ構成でリダイレクト URI が正しく設定されていない場合は、次のようなエラーが表示されます。

```text
Error detected... 
tag=508170375
context=AADSTS50011 Description: (pii), Domain: MSAIMSIDOAuthErrorDomain.Error was thrown in location: Broker 
errorCode=-51411 
status=Response_Status.Status_Unexpected 
```

構成したら、 `acquire_token_interactive` を呼び出してトークンを取得できます。

```python
result = app.acquire_token_interactive(["User.ReadBasic.All"],
                    parent_window_handle=app.CONSOLE_WINDOW_HANDLE)
```

Note

macOS では使用されていない場合でも、 `parent_window_handle` パラメーターが必要です。 GUI アプリケーションの場合、ログイン プロンプトの場所はアドホックで決定され、現在は特定のウィンドウにバインドできません。 今後の更新では、このパラメーターを使用して *実際* の親ウィンドウが決定されます。

### トークンのキャッシュ

認証ブローカーは、更新とアクセス トークンのキャッシュを処理します。 カスタム キャッシュを設定する必要はありません。

### サポートされている macOS のバージョンとアーキテクチャ

macOS ブローカーでは、次の構成がサポートされています。

| コンポーネント | サポートされているバージョン |
| --- | --- |
| **Architecture** | ARM64 (Apple Silicon) と x64 (Intel) |
| **macOS バージョン** | macOS 10.15 (Catalina) 以降 |

Tip

最新のセキュリティ機能とブローカー機能との互換性を確保するために、最新の macOS バージョンに更新することをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/managed-identity"} -->
## マネージド ID の使用 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/managed-identity
- Service: msal / msal-python
- Article date: 2024-06-25
- Summary: Pythonにマネージド ID と Microsoft Authentication Library (MSAL) を使用する方法について説明します。

[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用すると、開発者は、Microsoft Azure クラウドで実行されているアプリケーションの資格情報を格納、ローテーション、その他の方法で処理する必要を排除できます。 PythonのMicrosoft Authentication Library (MSAL) では、サポートされているAzure ワークロードでのマネージド ID での認証の使用がサポートされます。

Note

MSAL Python のマネージド ID は、`msal`のバージョン [1.29.0](https://pypi.org/project/msal/) 以降でサポートされています。

MSAL Pythonでは、次のような、Azure インフラストラクチャ内で実行されているアプリケーションで使用する場合、マネージド ID サービスを介したトークンの取得がサポートされます。

- [Azure App Service](https://azure.microsoft.com/products/app-service/) (API バージョン `2019-08-01`)
- [Azure VM](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)
- [Azure Arc](https://learn.microsoft.com/ja-jp/azure/azure-arc/overview)
- [Azure クラウド シェル](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview)
- [Azure Service Fabric](https://learn.microsoft.com/ja-jp/azure/service-fabric/service-fabric-overview)
- [Azure ML](https://learn.microsoft.com/ja-jp/azure/machine-learning/how-to-identity-based-service-authentication)

完全な一覧については、[マネージド ID を使用して他のサービスにアクセスできる](https://learn.microsoft.com/ja-jp/azure/active-directory/managed-identities-azure-resources/managed-identities-status)サービスAzureを参照してください。

### どの SDK を使用するか - Azure SDK または MSAL?

MSAL ライブラリは、OAuth2 プロトコルと OIDC プロトコルに近い下位レベルの API を提供します。

MSAL PythonとAzure SDKの両方で、マネージド ID を介してトークンを取得できます。 内部的には、Azure SDKは MSAL Pythonを使用し、[`DefaultAzureCredential`](https://learn.microsoft.com/ja-jp/python/api/azure-identity/azure.identity.defaultazurecredential)と[`ManagedIdentityCredential`](https://learn.microsoft.com/ja-jp/python/api/azure-identity/azure.identity.ManagedIdentityCredential)抽象化を介して上位レベルの API を提供します。

アプリケーションでいずれかの SDK が既に使用されている場合は、引き続き同じ SDK を使用します。

- 新しいアプリケーションを作成し、他のAzure リソースを呼び出す予定の場合は、Azure SDKを使用します。この SDK を使用すると、マネージド ID が存在しないプライベート開発者マシンでアプリを実行できるため、開発者エクスペリエンスが向上します。
- Microsoft Graphや独自の Web API などの他のダウンストリーム Web API を呼び出す必要がある場合は、MSAL を使用します。

### マネージド ID の使用方法

開発者が使用できるマネージド ID には、**システム割り当てとユーザー割り当ての** 2 種類があります。 違いの詳細については、 [マネージド ID の種類](https://learn.microsoft.com/ja-jp/azure/active-directory/managed-identities-azure-resources/overview#managed-identity-types) に関する記事を参照してください。 MSAL Pythonでは、両方を使用したトークンの取得がサポートされています。 MSAL Python ログを使用すると、要求と関連メタデータを追跡できます。

MSAL Pythonからマネージド ID を使用する前に、開発者は、Azure CLIまたはAzure portalで使用するリソースに対して有効にする必要があります。

### 例示

システム割り当て ID とユーザー割り当て ID の両方で、開発者はマネージド ID にアクセスするために [ManagedIdentityClient](https://learn.microsoft.com/ja-jp/python/api/msal/msal.managed_identity.managedidentityclient) を使用する必要があります。

#### システム割り当てのマネージド ID

システム割り当てマネージド ID は、 [SystemAssignedManagedIdentity](https://learn.microsoft.com/ja-jp/python/api/msal/msal.managed_identity.systemassignedmanagedidentity) をインスタンス化し、 [ManagedIdentityClient](https://learn.microsoft.com/ja-jp/python/api/msal/msal.managed_identity.managedidentityclient)に渡すことによって使用できます。

Note

`requests.Session()` に設定できる `http_client` 参照を含める必要があります。 これにより、MSAL は IMDS エンドポイントへの接続のプールを維持できます。

[`acquire_token_for_client`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.managed_identity.managedidentityclient#msal-managed-identity-managedidentityclient-acquire-token-for-client)を呼び出すときに、ターゲット リソース スコープを指定できます。

```python
import msal
import requests

managed_identity = msal.SystemAssignedManagedIdentity()

global_app = msal.ManagedIdentityClient(managed_identity, http_client=requests.Session())

result = global_app.acquire_token_for_client(resource='https://vault.azure.net')

if "access_token" in result:
    print("Token obtained!")
```

Important

Python コードが実行されるリソースに対してシステム割り当て ID を有効にする必要があります。それ以外の場合、トークンは返されません。

#### ユーザー割り当て済みマネージド ID

ユーザー割り当てマネージド ID は、 [UserAssignedManagedIdentity](https://learn.microsoft.com/ja-jp/python/api/msal/msal.managed_identity.userassignedmanagedidentity) をインスタンス化し、 [ManagedIdentityClient](https://learn.microsoft.com/ja-jp/python/api/msal/msal.managed_identity.managedidentityclient)に渡すことによって使用できます。 **次のいずれかを指定する**必要があります。

- クライアント ID (`client_id`)
- リソース ID (`resource_id`)
- オブジェクト ID (`object_id`)

Note

`requests.Session()` に設定できる `http_client` 参照を含める必要があります。 これにより、MSAL は IMDS エンドポイントへの接続のプールを維持できます。

[`acquire_token_for_client`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.managed_identity.managedidentityclient#msal-managed-identity-managedidentityclient-acquire-token-for-client)を呼び出すときに、ターゲット リソース スコープを指定できます。

```python
import msal
import requests

managed_identity = msal.UserAssignedManagedIdentity(client_id='YOUR_CLIENT_ID')

global_app = msal.ManagedIdentityClient(managed_identity, http_client=requests.Session())

result = global_app.acquire_token_for_client(resource='https://vault.azure.net')

if "access_token" in result:
    print("Token obtained!")
```

Note

MSAL Pythonの[組み込みのマネージド ID サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.29.0/sample/managed_identity_sample.py#L38-L42)では、ユーザー割り当てマネージド ID を環境変数から推論する方法を示します。 これは、コード内のクライアント ID の明示的な定義の代わりに使用できる高度な使用パターンです。

Important

Python コードが実行されるリソースのユーザー割り当て ID をアタッチする必要があります。それ以外の場合、トークンは返されません。 ユーザー割り当てマネージド ID に正しくない識別子が使用されている場合、トークンも返されません。

### Caching

既定では、MSAL Pythonではメモリ内キャッシュがサポートされます。

Important

MSAL Pythonでは、マネージド ID のキャッシュ拡張もサポートされているため、トークン キャッシュをディスクに保持できます。 これは、コマンド ライン スクリプトやその他のいくつかの制限付きシナリオを記述する場合に役立ちます。 複数のマシン間でマネージド ID トークン キャッシュを共有することは **お勧めしません** 。これにより、キャッシュのユーザーに対して予期しないアクセス動作が発生する可能性があります。 ノード/マシンに対して取得されたトークン (分散キャッシュにキャッシュされている場合) は、意図されていない別のマシンに使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/migrate"} -->
## 既存の更新トークンを MSAL Pythonに移行する - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: MSAL は低レベルの OAuth2 ライブラリではありません。 MSAL は、リフレッシュ トークン (RT) の概念を隠蔽します。

MSAL は低レベルの OAuth2 ライブラリではありません。 MSAL は、リフレッシュ トークン (RT) の概念を内部で処理し、ユーザーが意識しなくて済むようにします。 そのため、MSAL Pythonを使用してプロジェクトを開始し、[その 3 つの手順の使用パターン](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/dev/README.md#usage-and-samples) (具体的には手順 2) に従っている場合は、RT を格納する場所、検索方法、更新するタイミングを把握し、注意する必要もありません。 MSAL Pythonはトークン キャッシュに関するすべてのハード作業を自動的に行うだけで、エンド ユーザーには最小限のサインイン プロンプトが表示されます。

ただし、既存のプロジェクトで他の OAuth2 ライブラリ (ADAL Pythonを含むがこれらに限定されない) を使用していて、そのプロジェクトを MSAL Pythonに移行する場合は、既存のエンド ユーザーが再度サインインする必要がないように、それらの RP を MSAL Pythonに移行することもできます。

MSAL Python 0.6.0 以降では、その方法の1つは、MSAL Python を使用して以前の RT を使って新しいアクセス トークンを取得し、新しい RT が返されると、MSAL がそれを通常どおり保存するというものです。 このメソッドは一般的ではないシナリオを対象としているため、公式の API サーフェスでは簡単にアクセスできません。 内部ヘルパー `app.client`を介して呼び出す必要があります。また、その名前付け規則も他の [公式 API](https://learn.microsoft.com/ja-jp/entra/msal/python/getting-started/acquiring-tokens) とは若干異なります。

```python
from msal import PublicClientApplication

def get_preexisting_rt_and_their_scopes_from_elsewhere(...):
    raise NotImplementedError("You will need to implement this by yourself")

app = PublicClientApplication(..., token_cache=...)

for old_rt, old_scope in get_preexisting_rt_and_their_scopes_from_elsewhere(...):
    # Assuming the old scope could be a space-delimited string,
    # in MSAL we expect a list, like ["scope1", "scope2"].
    scopes = old_scope.split()
        # If your old RT came from ADAL Python which uses resource rather than scope,
        # you need to somehow convert your v1 resource into v2 scopes
        # See /azure/active-directory/develop/azure-ad-endpoint-comparison#scopes-not-resources
        # You can probably just append "/.default" to your v1 resource to form a scope
        # See /azure/active-directory/develop/v2-permissions-and-consent#the-default-scope

    result = app.client.obtain_token_by_refresh_token(old_rt, scope=scopes)
    # Providing that the above function call would succeed, the new token(s) would be returned,
    # a new RT would be issued by Microsoft Identity platform and be stored in new msal cache.
```

このメソッドは、更新トークンを使用できるさまざまな統合シナリオにも使用できます。

### ADAL Pythonから MSAL Pythonへの移行

#### 相違点

開発者向け Azure AD (v1.0) エンドポイント (および ADAL Python) について既に理解している場合は、[Microsoft ID プラットフォーム (v2.0) エンドポイントの違いを](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/azure-ad-endpoint-comparison)読んでください。

#### スコープであり、リソースではない

ADAL Pythonはリソースのトークンを取得しますが、MSAL Pythonはスコープのトークンを取得します。 MSAL Pythonの API サーフェスには、リソース パラメーターがなくなりました。 必要なアクセス許可と要求されるリソースを宣言する文字列の一覧としてスコープを指定する必要があります。 既知のスコープは[、Microsoft Graphのスコープです](https://learn.microsoft.com/ja-jp/graph/permissions-reference)。

`/.default` スコープ サフィックスを使用すると、アプリを v1.0 エンドポイント (ADAL) から Microsoft ID プラットフォーム エンドポイント (MSAL) に移行するのに役立ちます。 たとえば、 `https://graph.microsoft.com/.default` のスコープ値は、v1.0 エンドポイント `resource=https://graph.microsoft.com`と機能的に同じです。 リソースが URL フォームに含まれていなくても、フォームのリソース ID が `resource_id = "XXXXXXXX-XXXX-XXXX-XXXXXXXXXXXX"`場合でも、 `scope = [ resource_id + "/.default" ]`を使用できます。

リファレンス:

- [/.default のスコープ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-permissions-and-consent#the-default-scope)
- [v1.0 トークンを受け入れる Web API のスコープ。](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-v1-app-scopes)

#### API マッピング

| ADAL Pythonの API | MSAL Python の API に大まかにマップされます |
| --- | --- |
| [AuthenticationContext](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext) | [PublicClientApplication または ConfidentialClientApplication](https://msal-python.readthedocs.io/en/latest/#msal.ClientApplication.__init__) |
| N/a | [get_authorization_request_url()](https://msal-python.readthedocs.io/en/latest/#msal.ClientApplication.get_authorization_request_url) |
| [acquire_token_with_authorization_code()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_authorization_code) | [acquire_token_by_authorization_code()](https://msal-python.readthedocs.io/en/latest/#msal.ClientApplication.acquire_token_by_authorization_code) |
| [acquire_token()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token) | [acquire_token_silent()](https://msal-python.readthedocs.io/en/latest/#msal.ClientApplication.acquire_token_silent) |
| [acquire_token_with_refresh_token()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_refresh_token) | N/A ( このセクションの詳細を参照) |
| [acquire_user_code()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_user_code) | [initiate_device_flow()](https://msal-python.readthedocs.io/en/latest/#msal.PublicClientApplication.initiate_device_flow) |
| [acquire_token_with_device_code()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_device_code) と [cancel_request_to_get_token_with_device_code()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.cancel_request_to_get_token_with_device_code) | [acquire_token_by_device_flow()](https://msal-python.readthedocs.io/en/latest/#msal.PublicClientApplication.acquire_token_by_device_flow) |
| [acquire_token_with_username_password()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_username_password) | [acquire_token_by_username_password()](https://msal-python.readthedocs.io/en/latest/#msal.PublicClientApplication.acquire_token_by_username_password) |
| [acquire_token_with_client_credentials()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_client_credentials) と [acquire_token_with_client_certificate()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_client_certificate) | [acquire_token_for_client()](https://msal-python.readthedocs.io/en/latest/#msal.ConfidentialClientApplication.acquire_token_for_client) |
| N/a | [acquire_token_on_behalf_of()](https://msal-python.readthedocs.io/en/latest/#msal.ConfidentialClientApplication.acquire_token_on_behalf_of) |
| [TokenCache()](https://adal-python.readthedocs.io/en/latest/#adal.TokenCache) | [SerializableTokenCache()](https://msal-python.readthedocs.io/en/latest/#msal.SerializableTokenCache) |
| N/a | [MSAL Extensions](https://github.com/marstr/original-microsoft-authentication-extensions-for-python) で利用可能な、永続化対応のキャッシュ |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/migrate-python-adal-msal"} -->
## ADAL から MSAL への移行ガイドをPythonする - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate-python-adal-msal
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: Azure Active Directory認証ライブラリ (ADAL) Python アプリをPythonのMicrosoft Authentication Library (MSAL) に移行する方法について説明します。

この記事では、Azure Active Directory認証ライブラリ (ADAL) を使用してMicrosoft Authentication Library (MSAL) を使用するアプリを移行するために必要な変更について説明します。

MSAL の詳細については、[Python 用 Microsoft Authentication Libraryの概要を](https://learn.microsoft.com/ja-jp/entra/msal/python/)参照してください。

### 相違点の強調表示

ADAL は、Azure Active Directory (Azure AD) v1.0 エンドポイントで動作します。 Microsoft Authentication Library (MSAL) は、以前は Azure Active Directory v2.0 エンドポイントと呼ばれるMicrosoft ID プラットフォームで動作します。 Microsoft ID プラットフォームは、Azure AD v1.0 とは異なります。

サポート：

- 職場および学校アカウント (Microsoft Entra IDプロビジョニングされたアカウント)
- 個人アカウント (Outlook.com や Hotmail.com など)
- Azure AD B2C オファリングを通じて自分の電子メールまたはソーシャル ID (LinkedIn、Facebook、Google など) を持ち込む顧客
- 標準は次と互換性があります。

    - OAuth v2.0
    - OpenID Connect (OIDC)

MSAL の詳細については、 [MSAL の概要を](https://learn.microsoft.com/ja-jp/entra/msal/python/)参照してください。

#### スコープであり、リソースではない

ADAL Pythonはリソースのトークンを取得しますが、MSAL Pythonはスコープのトークンを取得します。 MSAL Pythonの API サーフェスには、リソース パラメーターがなくなりました。 必要なアクセス許可と要求されるリソースを宣言する文字列の一覧としてスコープを指定する必要があります。 スコープの例については、[Microsoft Graphのスコープ](https://learn.microsoft.com/ja-jp/graph/permissions-reference)を参照してください。

`/.default` スコープ サフィックスをリソースに追加すると、アプリを v1.0 エンドポイント (ADAL) から Microsoft ID プラットフォーム (MSAL) に移行するのに役立ちます。 たとえば、 `https://graph.microsoft.com`のリソース値の場合、同等のスコープ値は `https://graph.microsoft.com/.default`。 リソースが URL 形式ではなく、フォームのリソース ID `XXXXXXXX-XXXX-XXXX-XXXXXXXXXXXX`場合でも、スコープ値を `XXXXXXXX-XXXX-XXXX-XXXXXXXXXXXX/.default`として使用できます。

さまざまな種類のスコープの詳細については、[Microsoft ID プラットフォームのアクセス許可と同意](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/permissions-consent-overview)、および [v1.0 トークンを受け入れる Web API のスコープ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-v1-app-scopes)に関する記事を参照してください。

#### エラー処理

ADAL for Pythonでは、例外`AdalError`を使用して問題が発生したことを示します。 Pythonの MSAL では、通常、代わりにエラー コードが使用されます。 詳細については、[エラー処理の MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-error-handling-python)参照してください。

#### API の変更

次の表に、ADAL for Python の API と、MSAL for Pythonの代わりに使用する API を示します。

| Python API の ADAL | Python API 向け MSAL |
| --- | --- |
| [AuthenticationContext](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext) | [PublicClientApplication](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication) または [ConfidentialClientApplication](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication) |
| N/a | [acquire_token_interactive](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-acquire-token-interactive) |
| N/a | [get_authorization_request_url](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-get-authorization-request-url) |
| N/a | [initiate_auth_code_flow](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-initiate-auth-code-flow) |
| [acquire_token_with_authorization_code()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_authorization_code) | [acquire_token_by_auth_code_flow](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-by-auth-code-flow) |
| [acquire_token()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token) | [acquire_token_silent](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-silent) |
| [acquire_token_with_refresh_token()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_refresh_token) | 次の 2 つのヘルパーは、 移行 時にのみ使用することを目的としています。 [acquire_token_by_refresh_token](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-by-refresh-token) |
| [acquire_user_code()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_user_code) | [initiate_device_flow](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-initiate-device-flow) |
| [acquire_token_with_device_code()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_device_code) と [cancel_request_to_get_token_with_device_code()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.cancel_request_to_get_token_with_device_code) | [acquire_token_by_device_flow](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-acquire-token-by-device-flow) |
| [acquire_token_with_username_password()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_username_password) | [acquire_token_by_username_password](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-by-username-password) |
| [acquire_token_with_client_credentials()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_client_credentials) と [acquire_token_with_client_certificate()](https://adal-python.readthedocs.io/en/latest/#adal.AuthenticationContext.acquire_token_with_client_certificate) | [acquire_token_for_client](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication#msal-application-confidentialclientapplication-acquire-token-for-client) |
| N/a | [acquire_token_on_behalf_of](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication#msal-application-confidentialclientapplication-acquire-token-on-behalf-of) |
| [TokenCache()](https://adal-python.readthedocs.io/en/latest/#adal.TokenCache) | [SerializableTokenCache](https://learn.microsoft.com/ja-jp/python/api/msal/msal.token_cache.serializabletokencache) |
| N/a | [MSAL Extensions](https://github.com/marstr/original-microsoft-authentication-extensions-for-python) で利用可能な、永続化対応のキャッシュ |

### MSAL Pythonの既存の更新トークンを移行する

MSAL は、更新トークンの概念を抽象化します。 MSAL Pythonでは、既定でメモリ内トークン キャッシュが提供されるため、更新トークンを格納、参照、更新する必要はありません。 通常、更新トークンはユーザーの介入なしに更新できるため、ユーザーに表示されるサインイン プロンプトも少なくなります。 トークン キャッシュの詳細については、[MSAL でのPythonのカスタム トークン キャッシュのシリアル化に関するページを参照してください](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-python-token-cache-serialization)。

次のコードは、別の OAuth2 ライブラリで管理されている更新トークン (ADAL Pythonを含むがこれらに限定されません) を、MSAL でPython用に管理するために移行するのに役立ちます。 これらの更新トークンを移行する理由の 1 つは、Python用にアプリを MSAL に移行するときに、既存のユーザーが再度サインインする必要がないようにするためです。

更新トークンを移行する方法は、前の更新トークンを使用して新しいアクセス トークンを取得するPythonに MSAL を使用することです。 新しい更新トークンが返されると、Pythonの MSAL によってキャッシュに格納されます。 MSAL Python 1.3.0 であるため、この目的のために MSAL 内に API を提供します。 [MSAL Pythonを使用した更新トークンの移行の完全なサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.3.0/sample/migrate_rt.py#L28-L67)から引用された、次のコード スニペットを参照してください

```python
import msal
def get_preexisting_rt_and_their_scopes_from_elsewhere():
    # Maybe you have an ADAL-powered app like this
    #   https://github.com/AzureAD/azure-activedirectory-library-for-python/blob/1.2.3/sample/device_code_sample.py#L72
    # which uses a resource rather than a scope,
    # you need to convert your v1 resource into v2 scopes
    # See https://learn.microsoft.com/azure/active-directory/develop/migrate-python-adal-msal#scopes-not-resources
    # You may be able to append "/.default" to your v1 resource to form a scope
    # See https://learn.microsoft.com/azure/active-directory/develop/v2-permissions-and-consent#the-default-scope

    # Or maybe you have an app already talking to the Microsoft identity platform,
    # powered by some 3rd-party auth library, and persist its tokens somehow.

    # Either way, you need to extract RTs from there, and return them like this.
    return [
        ("old_rt_1", ["scope1", "scope2"]),
        ("old_rt_2", ["scope3", "scope4"]),
        ]

# We will migrate all the old RTs into a new app powered by MSAL
app = msal.PublicClientApplication(
    "client_id", authority="...",
    # token_cache=...  # Default cache is in memory only.
                       # You can learn how to use SerializableTokenCache from
                       # https://msal-python.readthedocs.io/en/latest/#msal.SerializableTokenCache
    )

# We choose a migration strategy of migrating all RTs in one loop
for old_rt, scopes in get_preexisting_rt_and_their_scopes_from_elsewhere():
    result = app.acquire_token_by_refresh_token(old_rt, scopes)
    if "error" in result:
        print("Discarding unsuccessful RT. Error: ", json.dumps(result, indent=2))

print("Migration completed")
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/msal-error-handling-python"} -->
## Pythonの MSAL でエラーと例外を処理する - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-error-handling-python
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: PYTHON アプリケーションの MSAL でエラーと例外、条件付きアクセス要求チャレンジ、再試行を処理する方法について説明します。

Pythonの MSAL では、ほとんどのエラーは API 呼び出しからの戻り値として伝達されます。 エラーは、Microsoft ID プラットフォームからの JSON 応答を含むディクショナリとして表されます。

- 成功した応答には、 `"access_token"` キーが含まれています。 応答の形式は、OAuth2 プロトコルによって定義されます。 詳細については、「[5.1 正常な応答](https://tools.ietf.org/html/rfc6749#section-5.1)」を参照してください。
- エラー応答には `"error"` が含まれており、通常は `"error_description"`。 応答の形式は、OAuth2 プロトコルによって定義されます。 詳細については、「[5.2 エラー応答](https://tools.ietf.org/html/rfc6749#section-5.2)」を参照してください。

エラーが返されると、 `"error"` キーにマシンが読み取り可能なコードが含まれます。 `"error"`が`"interaction_required"`などの場合は、認証プロセスを完了するための追加情報をユーザーに提供するように求めることができます。 `"error"`が`"invalid_grant"`されている場合は、ユーザーに資格情報の再入力を求めるメッセージを表示できます。 次のスニペットは、Pythonの MSAL でのエラー処理の例です。

```python

from msal import ConfidentialClientApplication

authority_url = "https://login.microsoftonline.com/your_tenant_id"
client_id = "your_client_id"
client_secret = "your_client_secret"
scopes = ["https://graph.microsoft.com/.default"]

app = ConfidentialClientApplication(client_id, authority=authority_url, client_credential=client_secret)

result = app.acquire_token_silent(scopes=scopes, account=None)

if not result:
    result = app.acquire_token_silent(scopes=scopes)

if "access_token" in result:
    print("Access token: %s" % result["access_token"])
else:
    print("Error: %s" % result.get("error"))

```

エラーが返されると、`"error_description"` キーには人間が判読できるメッセージも含まれ、通常は、コンピューターが読み取り可能なMicrosoft ID プラットフォームエラー コードを含む`"error_code"` キーもあります。 さまざまなMicrosoft ID プラットフォームエラー コードの詳細については、「[認証と承認のエラー コード](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-error-codes)」を参照してください。

Pythonの MSAL では、ほとんどのエラーがエラー値を返すことによって処理されるため、例外はまれです。 `ValueError` 例外は、API パラメーターの形式に誤りがある場合など、ライブラリの使用方法に問題がある場合にのみスローされます。

### 条件付きアクセスと要求の課題

トークンをサイレントで取得すると、アクセスしようとしている API で MFA ポリシーなどの [条件付きアクセス要求チャレンジ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-conditional-access-dev-guide) が必要な場合、アプリケーションでエラーが発生する可能性があります。

このエラーを処理するパターンは、MSAL を使用して対話形式でトークンを取得することです。 これにより、ユーザーにプロンプトが表示され、必要な条件付きアクセス ポリシーを満たす機会が提供されます。

場合によっては、条件付きアクセスが必要な API を呼び出す際に、API から返されるエラー内でクレーム チャレンジを受け取ることがあります。 たとえば、条件付きアクセス ポリシーでマネージド デバイス (Intune) を使用する場合、エラーは [AADSTS53000 のようになります。このリソースや同様のリソースにアクセスするには、デバイスを管理する必要があります](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-error-codes) 。 この場合、取得トークン呼び出しで要求を渡して、ユーザーが適切なポリシーを満たすように求めることができます。

### エラーと例外の後の再試行

MSAL では、Microsoft Entra サービスへの HTTP 呼び出しが行われ、エラーが発生することがあります。 たとえば、ネットワークがダウンしたり、サーバーが過負荷になったりする可能性があります。

MSAL Python 1.11 以降では、1 回の再試行が自動的に実行されます。 この動作は、[`http_client`カスタマイズの手順に](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication)従ってカスタマイズできます。

#### HTTP 429

サービス トークン サーバー (STS) が多すぎる要求でオーバーロードされると、HTTP エラー 429 が返され、 `Retry-After` 応答フィールドで再試行できるまでの時間に関するヒントが返されます。

アプリは後続のリクエストを抑制し、指定された期間が経過した後にのみ再試行することが想定されていました。

MSAL Python 1.16 以降では、認証要求をオンデマンドで簡単に再試行できます (たとえば、エンド ユーザーがサインイン ボタンをもう一度クリックするたびに)。MSAL Python 1.16 以降では、HTTP キャッシュから同じエラー応答を返し、指定した期間後に呼び出しが試行された場合にのみ実際の HTTP 呼び出しを送信することで、それらの再試行が自動的に調整されます。

既定では、このスロットル メカニズムは、スロットル情報を組み込みのメモリ内 HTTP キャッシュに保存することによって機能します。 独自の `dict`のようなオブジェクトを HTTP キャッシュとして提供できます。このオブジェクトは、そのコンテンツを保持する方法を制御できます。 詳細については[、MSAL Python API のドキュメント](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/msal-logging-python"} -->
## Pythonの MSAL でのエラーと例外のログ記録 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-logging-python
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: PYTHONの MSAL でエラーと例外をログに記録する方法について説明します

Microsoft Authentication Library (MSAL) アプリは、問題の診断に役立つログ メッセージを生成します。 アプリでは、数行のコードでログ記録を構成し、詳細レベルと個人データと組織データをログに記録するかどうかをカスタム制御できます。 MSAL ログの実装を作成し、ユーザーが認証の問題がある場合にログを送信する方法を提供することをお勧めします。

MSAL Pythonのログ記録は、標準的なPythonログ記録メカニズムを使用するように設計されているため、Pythonログ記録に関する以前のすべての知識が MSAL Pythonに適用されます。

- 既定では、Python スクリプトのログ記録はオフになっています。 Python スクリプト全体ですべてのモジュールのデバッグ ログを有効にする場合は、`logging.basicConfig(level=logging.DEBUG)`を使用します。
- MSAL Python ログのほとんどは既にデバッグ レベルであり、既定ではオフになっています。 ただし、Python スクリプト内の他のモジュールをデバッグするためにデバッグ ログを有効にし、そのため MSAL の出力は抑制したい場合は、MSAL Python が使用するロガーを単にオフにするだけです: `logging.getLogger("msal").setLevel(logging.WARN)`。
- MSAL Pythonでは、個人を特定できる情報 (PII) は記録されません。 つまり、MSAL for Python には PII ログを有効にするトグルすらありません。 アプリ開発者は引き続き標準のPythonログを使用して、コンテンツをログに記録できます。 これにより、アプリは機密性の高いデータを安全に処理し、規制要件に従う責任を負います。

### ログ記録のレベル

MSAL には、いくつかのレベルのログの詳細が用意されています。

- `LogAlways`: このログ レベルでは、レベル のフィルター処理は行われません。 すべてのレベルのログ メッセージがログに記録されます。
- `Critical`: 回復不能なアプリケーションまたはシステムのクラッシュ、または直ちに注意が必要な致命的な障害を示すログ。
- `Error`: 問題が発生し、エラーが生成されたことを示します。 問題のデバッグと特定に使用されます。
- `Warning`: エラーや失敗は必ずしも発生していませんが、診断と問題の特定を目的としています。
- `Informational`: MSAL は、必ずしもデバッグを目的としていない情報目的のイベントをログに記録します。
- `Verbose` (既定値): MSAL は、ライブラリの動作の詳細をログに記録します。

Note

すべての MSAL ライブラリですべてのログ レベルを使用できるわけではありません。

### 個人データと組織データ

既定では、MSAL ロガーは機密性の高い個人データや組織データをキャプチャしません。 ライブラリには、個人データと組織データのログ記録を有効にするオプションが用意されています (これを行う場合)。

次のセクションでは、アプリケーションの MSAL エラー ログの詳細について説明します。

### Python ログ記録用の MSAL

Python用の MSAL のログ記録では、[Python標準ライブラリのログ モジュールが](https://docs.python.org/3/library/logging.html)利用されます。 MSAL ログは次のように構成できます ( [また、username_password_sample](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.0.0/sample/username_password_sample.py#L31L32)で動作していることがわかります)。

#### すべてのモジュールのデバッグ ログを有効にする

既定では、Python スクリプトのログ記録はオフになっています。 スクリプト内の**すべての**Python モジュールで詳細ログを有効にしたい場合は、レベルを`logging.DEBUG`に設定して `logging.basicConfig` を使用します:

```python
import logging

logging.basicConfig(level=logging.DEBUG)
```

これにより、ログ モジュールに指定されたすべてのログ メッセージが標準出力に出力されます。

#### MSAL ログ レベルを構成する

Python ログ プロバイダーの MSAL のログ レベルを構成するには、ロガー名を`logging.getLogger()``"msal"`メソッドを使用します。

```python
import logging

logging.getLogger("msal").setLevel(logging.WARN)
```

#### Azure アプリ Insights を使用して MSAL ログを構成する

Pythonログはログ ハンドラーに与えられ、既定では`StreamHandler`です。 インストルメンテーション キーを使用して MSAL ログを Application Insights に送信するには、`AzureLogHandler` ライブラリによって提供される`opencensus-ext-azure`を使用します。

インストールするには、pyPI から `opencensus-ext-azure` パッケージを依存関係または pip インストールに追加`opencensus-ext-azure`。

```console
pip install opencensus-ext-azure
```

次に、`"msal"` ログ プロバイダーの既定のハンドラーを、`AzureLogHandler`環境変数にインストルメンテーション キーが設定された`APP_INSIGHTS_KEY`のインスタンスに変更します。

```python
import logging
import os

from opencensus.ext.azure.log_exporter import AzureLogHandler

APP_INSIGHTS_KEY = os.getenv('APP_INSIGHTS_KEY')

logging.getLogger("msal").addHandler(AzureLogHandler(connection_string='InstrumentationKey={0}'.format(APP_INSIGHTS_KEY)))
```

#### Python内の個人データと組織データ

Pythonの MSAL では、個人データや組織データはログに記録されません。 個人または組織のデータ ログ記録を有効または無効にするプロパティはありません。

標準のPythonログ記録を使用して必要なものをログに記録できますが、機密データを安全に処理し、規制要件に従う必要があります。

Pythonのログインの詳細については、Pythonの[ログ記録の方法に関するページを](https://docs.python.org/3/howto/logging.html#logging-basic-tutorial)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/msal-python-adfs-support"} -->
## Azure AD FS のサポート (MSAL Python) - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-python-adfs-support
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: Python 用 Microsoft Authentication Libraryでの Active Directory フェデレーション サービス (AD FS) (AD FS) のサポートについて説明します

Windows Server の Active Directory フェデレーション サービス (AD FS) (AD FS) を使用すると、Pythonの Microsoft Authentication Library (MSAL) を使用して、OpenID Connect と OAuth 2.0 ベースの認証と承認をアプリに追加できます。 Python ライブラリ用の MSAL を使用すると、アプリは AD FS に対してユーザーを直接認証できます。 シナリオの詳細については、「 [開発者向け AD FS シナリオ」を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/ad-fs-development)参照してください。

通常、AD FS に対して認証する方法は 2 つあります。

- MSAL Pythonは、それ自体が他の ID プロバイダーとフェデレーションされているMicrosoft Entra IDと通信します。 フェデレーションは AD FS を介して行われます。 MSAL PythonはMicrosoft Entra IDに接続します。これにより、Microsoft Entra IDで管理されているユーザー (マネージド ユーザー) または AD FS (フェデレーション ユーザー) などの別の ID プロバイダーによって管理されているユーザーがサインインされます。 MSAL Pythonは、ユーザーがフェデレーションされていることを認識しません。 それは単にMicrosoft Entra IDと話すだけです。 この場合に使用する [権限](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-application-configuration#authority) は、通常の機関 (機関ホスト名 + テナント、共通、または組織) です。
- MSAL Pythonは、AD FS 機関と直接やり取りします。 これは、AD FS 2019 以降でのみサポートされます。

### AD FS とフェデレーションされた Active Directory に接続する

#### フェデレーション ユーザーのトークンを対話形式で取得する

Active Directory フェデレーション サービス (AD FS) (AD FS) に直接接続するか、Active Directory経由で接続するかに関係なく、次のことが適用されます。

`acquire_token_by_authorization_code`または`acquire_token_by_device_flow`を呼び出すと、通常、ユーザー エクスペリエンスは次のようになります。

1. ユーザーが自分のアカウント ID を入力します。
2. Microsoft Entra ID、"組織のページに移動する" というメッセージが簡単に表示され、ユーザーは ID プロバイダーのサインイン ページにリダイレクトされます。 サインイン ページは通常、組織のロゴでカスタマイズされます。

このフェデレーション シナリオでサポートされている AD FS のバージョンは次のとおりです。

- Active Directory フェデレーション サービス (AD FS) FS v2
- Active Directory フェデレーション サービス (AD FS) v3 (Windows Server 2012 R2)
- Active Directory フェデレーション サービス (AD FS) v4 (AD FS 2016)

#### ユーザー名とパスワードを使用してトークンを取得する

Warning

セキュリティ リスクのため、パブリック クライアント アプリケーションではリソース所有者パスワード資格情報 (ROPC) フローが非推奨になりました。 Microsoft では、より安全な認証フローを使用することをお勧めします。 [ROPC から移行](https://aka.ms/msal-ropc-migration)する方法に関する公式ガイダンスに従ってください。

Active Directory フェデレーション サービス (AD FS) (AD FS) に直接接続するか、Active Directory経由で接続するかに関係なく、次のことが適用されます。

`acquire_token_by_username_password`を使用してトークンを取得すると、MSAL Pythonは、ユーザー名に基づいて連絡する ID プロバイダーを取得します。 MSAL Pythonは、ID プロバイダーから [SAML 1.1 トークン](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-saml-tokens)を取得し、JSON Web トークン (JWT) を返すMicrosoft Entraに提供します。 他のフローには存在しないセキュリティ リスクがあるため、ユーザー名とパスワードのフローはお勧めしません。 この許可の使用を避けたい理由の詳細については、[パスワードを過去のものにするためにMicrosoftが取り組んでいる理由を](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)参照してください。

### AD FS への直接接続

ディレクトリを AD FS に接続すると、アプリケーションのビルドに使用する機関は次のようになります。 `https://somesite.contoso.com/adfs/`

MSAL Pythonは ADFS 2019 をサポートしますが、ADFS 2016 または ADFS v2 への直接接続はサポートしていません。 オンプレミス システムを ADFS 2019 にアップグレードしたら、MSAL Pythonを使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/msal-python-token-cache-serialization"} -->
## カスタム トークン キャッシュのシリアル化 (MSAL Python) - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-python-token-cache-serialization
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: MSAL for Pythonを使用してトークン キャッシュをシリアル化する方法について説明します

PythonのMicrosoft Authentication Library (MSAL) では、[ConfidentialClientApplication](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication)のインスタンスを作成するときに、アプリ セッションの期間中保持されるメモリ内トークン キャッシュが既定で提供されます。

トークン キャッシュをシリアル化して、アプリのさまざまなセッションからアクセスできるように、"すぐに使える" ようにすることはできません。Pythonの MSAL は、ファイル システムにアクセスできないアプリの種類 (Web アプリなど) で使用できます。 PYTHONに MSAL を使用するアプリで永続的なトークン キャッシュを作成するには、カスタム トークン キャッシュのシリアル化を指定する必要があります。

トークン キャッシュをシリアル化する方法は、パブリック クライアント アプリケーション (デスクトップ) と機密クライアント アプリケーション (Web アプリ、Web API、デーモン アプリ) のどちらを作成しているかによって異なります。

### パブリック クライアント アプリケーションのトークン キャッシュ

パブリック クライアント アプリケーションは、ユーザーのデバイスで実行され、1 人のユーザーのトークンを管理します。 この場合、キャッシュ全体をファイルにシリアル化できます。 アプリまたは別のアプリが同時にキャッシュにアクセスできる場合は、必ずファイル ロックを指定してください。 ロックせずにトークン キャッシュをファイルにシリアル化する方法の簡単な例については、 [SerializableTokenCache](https://learn.microsoft.com/ja-jp/python/api/msal/msal.token_cache.serializabletokencache) クラスのリファレンス ドキュメントの例を参照してください。

### Web アプリのトークン キャッシュ (機密クライアント アプリケーション)

Web アプリまたは Web API の場合は、セッション、Redis キャッシュ、またはデータベースを使用してトークン キャッシュを格納できます。 ユーザーごとに (アカウントごとに) 1 つのトークン キャッシュが存在する必要があるため、アカウントごとにトークン キャッシュをシリアル化してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/username-password-authentication"} -->
## ユーザー名とパスワード認証 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/username-password-authentication
- Service: msal / msal-python
- Article date: 2024-02-07
- Summary: 設計とポリシーにより、ユーザー名/パスワード認証は職場および学校アカウントでのみ機能しますが、Microsoft アカウント (MSA) では機能しません。

Warning

セキュリティ リスクのため、パブリック クライアント アプリケーションではリソース所有者パスワード資格情報 (ROPC) フローが非推奨になりました。 Microsoft では、より安全な認証フローを使用することをお勧めします。 [ROPC から移行](https://aka.ms/msal-ropc-migration)する方法に関する公式ガイダンスに従ってください。

以下の内容は、MSAL Pythonだけでなく、[すべての MSAL ライブラリ](https://learn.microsoft.com/ja-jp/entra/msal)に適用されます。

### ユーザー名とパスワードのフローは推奨されません

Microsoftでは、ユーザー名とパスワードのフローを使用しないことをお勧めします。 ほとんどのシナリオでは、より安全な代替手段が利用でき、推奨されます。 このフローでは、アプリケーションに非常に高い信頼が必要であり、他のフローに存在しないリスクが伴います。 このフローは、より安全なフローが実行可能ではない場合にのみ使用してください。 この許可の使用を避けたい理由の詳細については、[パスワードを過去のものにするためにMicrosoftが取り組んでいる理由を](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)参照してください。

### 制約

- 設計とポリシーにより、ユーザー名/パスワード認証は職場および学校アカウントでのみ機能しますが、Microsoft アカウント (MSA) では機能しません。 [この 2 種類のアカウントの定義については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)。
- ユーザー名/パスワード認証は、条件付きアクセスと多要素認証と互換性がありません。これは対話型フローではないため、Microsoft ID プラットフォームはエンド ユーザーが対話するための Web ベースのダイアログを表示する機会がありません。 その結果、テナント管理者が多要素認証を必要とするMicrosoft Entra テナントでアプリが実行されている場合 (多くの組織で行われます)、このフローは機能しません。
- ユーザー名パスワード認証は非対話型フローであるため、
    - アプリケーションのユーザーは、アプリケーションの使用に以前に同意している必要があります
    - または、テナント管理者が、アプリケーションを使用するためにテナント内のすべてのユーザーに以前に同意している必要があります。
    - これは、次のことを意味します。
        - 開発者が自分用に Azure portal 上の **[許可]** ボタンをクリックしておきます。
        - または、テナント管理者が、アプリケーションの登録の **API アクセス許可**タブにある **{tenant domain} に対する管理者の同意の付与/取り消**しボタンを押しました ([Web API にアクセスするためのアクセス許可の追加を](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/quickstart-configure-app-access-web-apis#add-permissions-to-access-web-apis)参照)
        - または、ユーザーがアプリケーションに同意する方法を提供している場合 ( [「個々のユーザーの同意を要求する」を](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-permissions-and-consent#requesting-individual-user-consent)参照)
        - または、テナント管理者がアプリケーションに同意する方法を提供している場合 (管理者の [同意](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-permissions-and-consent#requesting-consent-for-an-entire-tenant)を参照)

### 推奨事項

ユーザー名パスワード認証を使用する場合でも、エンド ユーザーのパスワードを保持しないでください。 最初のユーザー名パスワード認証が成功すると、MSAL のトークン キャッシュが開始され、更新トークン (RT) が自動的にキャッシュされます。 今後、アプリは MSAL の [`acquire_token_silent()`](https://msal-python.readthedocs.io/en/latest/#msal.ClientApplication.acquire_token_silent) を呼び出して、ユーザー名とパスワードなしで新しいアクセス トークンを取得できます。

### セットアップ

Microsoft ID プラットフォームでは、パブリック クライアント アプリケーションと Confidential クライアント アプリケーションでのユーザー名パスワード フローがサポートされます。 アプリをパブリック クライアント アプリケーションとして構成する必要がある場合は、次のスクリーンショットのスイッチをオンにして許可します。

[Image: パブリック アプリのセットアップ]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/advanced/wam"} -->
## Web アカウント マネージャーでの MSAL Pythonの使用 - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/wam
- Service: msal / msal-python
- Article date: 2025-04-24
- Summary: Windows アプリケーションを構築する場合は、認証ブローカー (Web アカウント マネージャー) を使用してユーザーの認証方法を簡略化することを検討してください。

Windows アプリケーションを構築する場合は、*認証ブローカー*を使用してユーザーの認証方法を簡略化することを検討してください。 [Web アカウント マネージャー](https://learn.microsoft.com/ja-jp/windows/uwp/security/web-account-manager) (WAM) は、MSAL Pythonで動作する認証ブローカーです。 WAM は、Windows 10 以降、およびWindows Server 2019以上でのみ使用できます。

認証ブローカーを使用する利点の詳細については、MSAL.NET ドキュメントの[ブローカー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam#what-is-a-broker)とはを参照してください。

### 使用方法

ブローカーを使用するには、PyPI のコア MSAL に加えて、ブローカー関連のパッケージをインストールする必要があります。

```bash
pip install msal[broker]>=1.20,<2
```

ブローカー関連のパッケージがインストールされておらず、認証ブローカーを使用しようとすると、ImportError というエラーが表示されます。 *依存関係をインストールするには、pip install "msal[broker]&gt;=1.20,&lt;2" をインストールする必要*があります。

次に、新しい [`PublicClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication) をインスタンス化し、 `enable_broker_on_windows` を `True` に設定します。 これにより、MSAL は新しいブラウザー ウィンドウをポップアップするのではなく、WAM と通信しようとします。 クロスプラットフォーム アプリケーションを作成する場合は、`enable_broker_on_mac`に関する記事で説明されているように、も使用する必要があります。

```python
from msal import PublicClientApplication

app = PublicClientApplication(
    "CLIENT_ID",
    authority="https://login.microsoftonline.com/common",
    enable_broker_on_windows=True)
```

[`acquire_token_interactive`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-acquire-token-interactive)を呼び出し、*parent\_window\_handleを使用*して親ウィンドウ ハンドルを指定することで、トークンを取得できるようになりました。

```python
result = app.acquire_token_interactive(["User.ReadBasic.All"],
         parent_window_handle=app.CONSOLE_WINDOW_HANDLE)
```

要求ウィンドウの上にダイアログが正しく表示されるようにするには、WAM によって親ウィンドウ ハンドルが必要です。 MSAL では、WAM がどのウィンドウにバインドする必要があるかに影響を与える可能性がある変数が多数存在し、アプリケーションを構築する開発者は、どのウィンドウを作成する必要があるかを決定するのに最適であるため、これを直接推測しません。

コンソール アプリケーションの場合、MSAL を使用すると、すぐに使用できるソリューションを提供して、ターミナルのウィンドウ ハンドル ( [`CONSOLE_WINDOW_HANDLE`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-console-window-handle)) を取得できます。 デスクトップ アプリケーションの場合、[ウィンドウ ハンドルを取得](https://learn.microsoft.com/ja-jp/windows/apps/develop/ui-input/retrieve-hwnd)するために、Windows API を使用する作業が増える必要がある場合があります。 [pywin32](https://pypi.org/project/pywin32/) などのヘルパー パッケージは、API 呼び出しに役立ちます。

アプリケーションを実行する前に、デスクトップ アプリのリダイレクト URL を構成していることを確認します。

Windows ブローカーを使用するには、アプリケーションで、Azure portalで正しいリダイレクト URL を次の形式で構成する必要があります。

```bash
ms-appx-web://microsoft.aad.brokerplugin/YOUR_CLIENT_ID
```

リダイレクト URL が構成されていない場合は、*(pii) のようなbroker\_errorが表示されます。状態: Response\_Status.Status\_ApiContractViolation、エラー コード: 3399614473、タグ: 557973642*。

構成とインスタンス化が正しい場合は、アプリケーションを実行すると、認証ブローカーが開始され、ユーザーが認証するアカウントを選択できるようになります。

[Image: Pythonから呼び出される WAM の例]

ブローカーベースの認証を使用するように切り替えた場合、ユーザーが以前にログインしていて、サインイン状態がまだ有効な場合、 [`acquire_token_interactive`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-acquire-token-interactive) を呼び出してもトークンの取得がサイレント試行され、必要な場合にのみプロンプトが表示されることに注目してください。 常にプロンプトを表示する場合は、この省略可能なパラメーター `prompt="select_account"`を使用できます。

### ブローカー エクスペリエンスの違い

[`PublicClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication)のインスタンス化時に指定された権限に応じて、ブローカーのユーザー インターフェイスが異なる場合があります。

#### /消費者

個人のMicrosoft アカウント**でのみ**認証するために使用されます。

[Image: コンシューマー向けの WAM UI]

#### /共通

個人のMicrosoft アカウントおよび職場および学校アカウントでの認証に使用されます。

[Image: 個人用アカウントと仕事用アカウント向けの WAM UI]

#### /organizations

職場および学校アカウント **でのみ** 認証するために使用されます。

[Image: 職場アカウント専用の WAM UI]

*login\_hint*が指定されていても、アカウントがまだ WAM に登録されていない場合、ヒントは自動的に *[電子メール] または [電話*] フィールドに入力されます。

#### /TENANT\_ID

指定したテナント内の職場および学校アカウント **でのみ** 認証するために使用されます。

[Image: テナント固有アカウントの WAM UI]

*login\_hint*が指定されていても、アカウントがまだ WAM に登録されていない場合、ヒントは自動的に *[電子メール] または [電話*] フィールドに入力されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/getting-started/acquiring-tokens"} -->
## アプリのトークンを取得する - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/getting-started/acquiring-tokens
- Service: msal / msal-python
- Article date: 2025-04-24
- Summary: Python アプリケーションのトークンを取得する方法について説明します。 トークンは、Web ブラウザーを介してサイレントモードまたは対話形式で取得できます。

MSAL Pythonを使用してトークンを取得する方法は多数あります。 ユーザーの操作が必要な場合もあれば、必要でないものもあります。 トークンの取得に使用されるアプローチは、開発者がパブリック クライアント (デスクトップまたはモバイル) または機密クライアント アプリケーション (Web アプリ、Web API、Windows サービスなどのデーモン) を構築しているかどうかによって異なります。

### Prerequisites

MSAL Pythonでトークンを取得する前に、[クライアント アプリケーションの種類](https://learn.microsoft.com/ja-jp/entra/msal/python/getting-started/client-applications)について説明します。

### ユーザー アカウントを取得する

アプリは、それ自体として、またはユーザーの代わりにトークンを取得できます。 ユーザーに代わってトークンを取得するには、アプリがユーザーのアカウントを認識している必要があります。 MSAL Pythonは、ユーザーのアカウントを取得する[`get_accounts`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-get-accounts)メソッドを提供します。 このメソッドは、 [`PublicClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication) クラスと [`ConfidentialClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication) クラスの両方で使用できます。 このメソッドは、ユーザーが以前にサインインしたアカウント、つまりキャッシュに存在するアカウントの一覧を返します。

```python
accounts = app.get_accounts(username=user.get("preferred_username"))
```

ユーザーがサインインのために選択したアカウントは、後で `acquire_token_silent()` でトークンを検索するために使用できます。

### トークン付与フロー

MSAL Pythonでトークンを取得するために使用できる認証フローがいくつかあります。 これらのフローの詳細については、[Microsoft ID プラットフォームドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)。

Warning

MSAL を使用してセキュリティ トークンを取得し、アプリで保護された Web API を呼び出します。 独自のトークン取得ロジックを実装することはお勧めしません。 これらのフローは、物事のしくみをより深く理解するのに役立ちます。 Web アプリケーションをセキュリティで保護する場合は、 [ID](https://pypi.org/project/identity/) ライブラリを使用することをお勧めします。 このライブラリは、Microsoftによって正式に管理されていませんが、Web アプリでトークンを取得するために必要なロジックのほとんどを実装しています。

### 対話型とサイレント

MSAL Pythonでは、対話型トークンとサイレント トークンの取得の両方がサポートされています。 対話型トークンの取得にはユーザーの操作が必要ですが、サイレント トークンの取得は必要ありません。 一般に、パブリック クライアントではユーザーの操作が必要ですが、機密クライアントは証明書やシークレットなどの事前プロビジョニングされた資格情報に依存します。

トークンをサイレント モードで取得するには、 [`acquire_token_silent_with_error`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-silent-with-error) メソッドを使用します。 このメソッドは、キャッシュから有効なアクセス トークン、またはキャッシュから有効な更新トークンを検索し、それを自動的に使用して新しいアクセス トークンを使用します。 どちらも true でない場合は、対話型メソッドを使用してトークンを取得する必要があります。

アプリがトークン キャッシュの検索中に正確なトークン更新エラーを気にしない場合は、 [`acquire_token_silent`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-silent) メソッドをお勧めします。

このメソッドの使用例は、次のコード スニペットに示されています。

```python
if accounts:
    # If so, you could then somehow display these accounts and let end user choose
    chosen = accounts[0]
    result = app.acquire_token_silent(scopes=["your_scope"], account=chosen)
    
    # At this point, you can save you can update your cache if you are using token caching
    # check result variable, if its None then you should interactively acquire a token
    if not result:
        # So no suitable token exists in cache. Let's get a new one from Microsoft Entra.
        result = app.acquire_token_by_one_of_the_actual_method(..., scopes=["User.Read"])
    
    if "access_token" in result:
        access_token = result["access_token"]
    else:
        print(result.get("error"))  
        print(result.get("error_description"))
        print(result.get("correlation_id"))  # You may need this when reporting a bug
```

対話型トークンの取得には、いくつかの方法を使用できます。 使用する方法は、ビルドするアプリの種類と、シナリオに適用できるトークン付与フローによって異なります。

### パブリック クライアントによる対話形式でのトークン取得

パブリック クライアント アプリケーションは、シークレットを安全に格納することはできません。また、製品と対話しているユーザーのみを認証できます。 MSAL Pythonは、[`PublicClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication)を介してパブリック アプリケーションのトークン取得ロジックを公開します。 パブリック クライアント アプリケーションがトークンを取得するために使用できるさまざまな方法を次に示します。

#### デバイス コード フロー

[デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code) は、Web ブラウザーにアクセスできないデバイスで実行されるアプリケーションでトークンを取得するために使用されます。 これらは、ヘッドレス アプリケーションと呼ばれるアプリケーションです。 このフローにより、ユーザーに URL とコードが提供されます。 ユーザーは別のデバイス上の Web ブラウザーに移動し、コードを入力してサインインします。 認証が成功すると、Microsoft Entraはブラウザーのないデバイスにトークンを返します。

まず、 [`initiate_device_flow`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-initiate-device-flow) メソッドを呼び出します。

```python
flow = app.initiate_device_flow(scopes=config["scope"])
if "user_code" not in flow:
    raise ValueError(
        "Fail to create device flow. Err: %s" % json.dumps(flow, indent=4))

print(flow["message"])
sys.stdout.flush()  # Some terminal needs this to ensure the message is shown

# Ideally you should wait here, in order to save some unnecessary polling
# input("Press Enter after signing in from another device to proceed, CTRL+C to abort.")
```

次に、フロー ディクショナリ オブジェクトを [`acquire_token_by_device_flow`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-acquire-token-by-device-flow) メソッドに渡してトークンを取得します。 既定では、このメソッドは現在のスレッドをブロックします。 [次の手順](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication#msal-application-publicclientapplication-acquire-token-by-device-flow)に従ってブロック時間を短縮したり、ブロック動作をオフにして、独自のカスタマイズされたループで`acquire_token_by_device_flow`を呼び出し続けたりすることもできます。

```python
result = app.acquire_token_by_device_flow(flow)

if "access_token" in result:
    access_token = result["access_token"]
else:
    print(result.get("error"))  
```

`access_token` キーを含む辞書の成功した応答。

#### トークンを対話型で取得する

MSAL Pythonには、パブリック クライアント アプリ (デスクトップとモバイル) がユーザーとしてトークンを取得する機能も用意されています。 ユーザーは、Web ブラウザー経由で承認要求 URL を使用してサインインします。 アプリの登録のMicrosoft Entra 管理センターで`http://localhost`するようにアプリのリダイレクト URI を設定します。 の作成時に`PublicClientApplication`することを選択した場合、アプリでは`ms-appx-web://Microsoft.AAD.BrokerPlugin/YOUR_CLIENT_ID`をリダイレクト URI として登録する必要もあります。

```python
result = app.acquire_token_interactive(  # It automatically provides PKCE protection
    scopes=config["scope"])

if "access_token" in result:
    access_token = result["access_token"]
else:
    print(result.get("error"))  
```

#### ユーザー名とパスワード

Warning

この API は、セキュリティ 上のリスクがあるため、パブリック クライアント フローでは非推奨となりました。より安全なフローを使用してください。 移行 [ガイダンスについては、このガイド](https://aka.ms/msal-ropc-migration) に従ってください。

この方法を使用することはお勧めしません。 [ユーザー名とパスワード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc)を使用してトークンを取得することもできます。 MSAL Pythonは、このユース ケースの[`acquire_token_by_username_password`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-by-username-password)メソッドを提供します。 アプリケーションがユーザーにパスワードを直接要求するため、これは安全ではないパターンであるため、お勧めしません。

より安全なフローを使用できます。 詳細については、 [ユーザー名とパスワードの認証フロー](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/username-password-authentication) のガイダンスを参照してください。

```python
result = app.acquire_token_by_username_password(
    username=config["username"], password=config["password"], scopes=config["scope"])

if "access_token" in result:
    access_token = result["access_token"]
else:
    print(result.get("error"))  
```

### 機密クライアントの対話型トークンの取得

機密クライアント アプリケーションは、シークレットを安全に格納でき、アプリケーションに代わって認証することも、特定のユーザーの代わりに認証することもできます。 MSAL Pythonでは、[`ConfidentialClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication)を開発するときにトークンを取得するさまざまな方法を開発者に提供します。

#### クライアントのトークンを取得する

ユーザーではなく、 [クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)を使用して、アプリケーション自体としてトークンを取得します。 たとえば、同期ツールなど、特定のユーザーではなく、バッチでユーザーを処理するアプリケーションでこれを使用できます。 MSAL Pythonには、これを行う[`acquire_token_for_client`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication#msal-application-confidentialclientapplication-acquire-token-for-client)メソッドが用意されています。 MSAL Python 1.23 であるため、このメソッドはキャッシュからトークンを自動的に検索し、キャッシュミス時にのみ ID プロバイダーに要求を送信します。

```python
result = app.acquire_token_for_client(scopes=config["scope"])

if "access_token" in result:
    access_token = result["access_token"]
else:
    print(result.get("error"))    
```

#### 代理でトークンを取得する

Web アプリまたは Web API がユーザーの名前で別のダウンストリーム Web API を呼び出す場合は、 [On Behalf Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow) を使用して、ユーザー アサーションに基づいてトークンを取得します。 たとえば、SAML と JWT です。 現在のアプリは、エンド ユーザーを表すトークンを使用して呼び出された中間層サービスです。 現在のアプリでは、このようなトークン (ユーザー アサーションとも呼ばれます) を使用して、そのユーザーに代わってダウンストリーム Web API にアクセスするための別のトークンを要求できます。 中間層アプリには、同意を得るためのユーザー操作がありません。 中間層アプリの事前の同意を得る方法については、ドキュメントを参照 [してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow#gaining-consent-for-the-middle-tier-application)。

[`acquire_token_on_behalf_of`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication#msal-application-confidentialclientapplication-acquire-token-on-behalf-of) メソッドを使用してアクセス トークンを取得するコードの例を次に示します。

```python
def get(self, request): # a web service endpoint receiving a request
    
    scopes = ["your-scopes"]
    downstream_api = "https://your-downstreamapi.com/resource" #your downstream API resource endpoint
    current_access_token = request.headers.get("Authorization", None)
    
    # initialize the app
    app = msal.ConfidentialClientApplication(...) # refer to initialization of the app documentation

    #acquire token on behalf of the user that called this API
    downstream_api_access_token = app.acquire_token_on_behalf_of(
        user_assertion=current_app_access_token.split(' ')[1],
        scopes=_scopes
    )

    if "access_token" in result:
        access_token = result["access_token"]
        # use access_token to call dowstream API e.g
        requests.get(downstream_api, headers={'Authorization': f'Bearer {downstream_api_access_token}'})
    else:
        print(result.get("error")) 
```

#### 承認コード フローによるトークンの取得

ユーザーの名前で認証する Web アプリの場合は、承認要求 URL を使用してユーザーがサインインできるようにした後、承認 [コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) を使用してトークンを取得します。 これは通常、ユーザーがこの特定のユーザーの Web API にサインインしてアクセスできるようにするアプリケーションで使用されるメカニズムです。

最初に、 [`initiate_auth_code_flow`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-initiate-auth-code-flow)を使用して認証コード フローを開始する必要があります。 このメソッドは、リダイレクト URI と状態文字列を他のパラメーターの中から取り込みます。 状態パラメーターの値もトークン応答に含まれます。 この値が存在しない場合、MSAL Python は内部的に値を自動生成します。 指定されたリダイレクト URI は、Microsoft Entra 管理センターに登録されているリダイレクト URI と一致する必要があります。 このメソッドは、 `auth_uri` と `state`を含むディクショナリである認証コード フローを返します。 `auth_uri`は、ユーザーがサインインするためにアクセスする必要がある URL です。

```python
flow = app.initiate_auth_code_flow(
    scopes=config["scope"], redirect_uri=config["redirect_uri"], state="your-state-value")

if "error" in flow:
    print(flow.get("error"))

# Save the response somewhere e.g in session
session["auth_flow"] = flow

# At this point, the app should guide the user to visit the auth ur (session["auth_flow"]["auth_uri"])
```

認証 URI エンドポイントへのアクセスからの応答は、 [`acquire_token_by_auth_code_flow`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-by-auth-code-flow) メソッドで使用されます。 状態は、承認サーバーからの応答を確認するために使用できる一意の識別子です。 ユーザーはサインイン時に要求されたスコープに同意する必要があります。

```python
# The uth_response value from visiting the auth_uri endpoint is passed as a query string
# You can change this by passing a value to the response_mode in the initiate_auth_code_flow method
try:
    result = app.acquire_token_by_auth_code_flow(session.get("flow", {}), auth_response)
    
    if "access_token" in result:
        access_token = result["access_token"]
    else:
        print(result.get("error"))
except ValueError:  # Usually caused by CSRF
    pass  # Simply ignore them
```

### MSAL Python トークン キャッシュ

パブリック クライアント アプリケーションと機密クライアント アプリケーションの両方で、MSAL Pythonによって直接処理されるトークン キャッシュがサポートされます。 アプリケーションは、他の手段に依存する前に、まずキャッシュからトークンを取得しようとする必要があります。 詳細については、 [推奨されるトークン取得パターン](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-desktop-acquire-token?tabs=python)を参照してください。

キャッシュを保持できるようにするには、開発者は [トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-python-token-cache-serialization) ロジックを構成する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/python/getting-started/client-applications"} -->
## クライアント アプリケーション - Microsoft Authentication Library for Python

- Source: https://learn.microsoft.com/ja-jp/entra/msal/python/getting-started/client-applications
- Service: msal / msal-python
- Article date: 2025-04-24
- Summary: MSAL Pythonでクライアント アプリケーションをインスタンス化する方法。

Microsoft Authentication Library (MSAL) Pythonでは、パブリック クライアント アプリケーションと機密クライアント アプリケーションの 2 種類のクライアント アプリケーションがサポートされています。 クライアントの種類は、承認サーバーで安全に認証し、機密性の高い ID 証明情報を保持して、アクセスのスコープ内でユーザーにアクセスまたは認識できないようにする機能によって区別されます。 この記事では、MSAL Pythonを使用してこれらのアプリケーションを初期化する方法について説明します。

### Prerequisites

[パブリック クライアント アプリケーションと機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications)の基本的な概念について説明します。

### アプリケーションをインスタンス化する

アプリを開始するには、アプリの登録が必要です。 Microsoft Entra 管理センターに[アプリを登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)します。 登録ページでは、次のものが必要です。

- アプリケーション クライアントの識別子。 これは GUID の形式の文字列です。
- アプリケーションの ID プロバイダー URL (インスタンス) とサインイン対象ユーザー。 これら 2 つのパラメーターは、総称して機関と呼 *ばれます*。
- 必要に応じて、組織 (シングルテナント アプリケーションとも呼ばれます) のみを対象とした基幹業務アプリケーションを作成する場合のテナント識別子 (GUID) です。
- 機密クライアント アプリを構築する場合は、文字列または証明書の形式でアプリケーション シークレットを作成します。
- Web アプリケーションの場合は、コードを返すために使用Microsoft Entra IDリダイレクト URL を設定します。 デスクトップ アプリケーションの場合は、認証ブローカーに依存していない場合は、 `http://localhost` を追加します。

### パブリック クライアント アプリケーションをインスタンス化する

パブリック クライアント アプリケーションは、 [`PublicClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication) クラスを使用します。

```python
import msal

app = msal.PublicClientApplication(
    "client_id",
    authority="authority",
    )
```

### 機密クライアント アプリケーションをインスタンス化する

機密クライアント アプリケーションは、 [`ConfidentialClientApplication`](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication) クラスを使用します。

```python
import msal

app = msal.ConfidentialClientApplication(
    "client_id",
    authority="authority",
    client_credential="client_secret",
    )
```

### Caching

クライアント アプリケーションをインスタンス化するときに、キャッシュ設定を定義するために使用できるパラメーターが 2 つあります。 これらのパラメーターは *、token\_cache* と *http\_cache*です。

- *token\_cache* は、クライアント アプリケーション インスタンスによって使用されるトークン キャッシュを設定します。 既定では、メモリ内キャッシュが作成され、使用されます。 詳細については、「[MSAL Pythonでのトークン キャッシュ」を参照してください](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-python-token-cache-serialization)。
- *http\_cache*は、MSAL Python バージョン 1.16 以降で使用できます。 これにより、一部の有限数の非トークン http 応答が自動的にキャッシュされるため、一部の状況では有効期間の長い *PublicClientApplication* インスタンスと *ConfidentialClientApplication* インスタンスのパフォーマンスと応答性が向上します。 *http\_cache* パラメーターが指定されていない場合、MSAL はメモリ内の dict を使用します。 アプリがコマンド ライン アプリ (CLI) の場合は、異なる CLI の実行間 *でhttp\_cache* を保持する必要があります。 詳細については、 [リファレンス ガイドを参照](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication)してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb"} -->
## Microsoft Identity Web ドキュメント

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: ASP.NET Core Web アプリ、Web API、デーモン アプリケーション、およびポリグロット マイクロサービスに認証と承認を追加するための Microsoft Identity Web ドキュメントについて説明します。

Microsoft ID プラットフォームを使用して、.NET アプリケーションとポリグロット マイクロサービスに認証と承認を追加します。

### 概要

#### 概要

- [Microsoft.Identity.Webとは何ですか?](https://learn.microsoft.com/ja-jp/entra/msidweb/overview)
- [NuGet パッケージ](https://learn.microsoft.com/ja-jp/entra/msidweb/getting-started/packages)

#### クイックスタート

- [Web アプリでユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/msidweb/getting-started/quickstart-webapp)
- [Web API を保護する](https://learn.microsoft.com/ja-jp/entra/msidweb/getting-started/quickstart-webapi)
- [デーモン アプリから API を呼び出す](https://learn.microsoft.com/ja-jp/entra/msidweb/getting-started/daemon-app)

### 認証と資格情報

#### 概念

- [資格証明の概要](https://learn.microsoft.com/ja-jp/entra/msidweb/authentication/credentials-overview)
- [証明書なしの認証](https://learn.microsoft.com/ja-jp/entra/msidweb/authentication/certificateless)
- [証明書](https://learn.microsoft.com/ja-jp/entra/msidweb/authentication/certificates)
- [クライアント シークレット](https://learn.microsoft.com/ja-jp/entra/msidweb/authentication/client-secrets)
- [トークンの暗号化解除](https://learn.microsoft.com/ja-jp/entra/msidweb/authentication/token-decryption)

#### 攻略ガイド

- [トークン キャッシュの構成](https://learn.microsoft.com/ja-jp/entra/msidweb/authentication/token-cache-overview)
- [トークン キャッシュのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/msidweb/authentication/token-cache-troubleshooting)
- [承認を設定する](https://learn.microsoft.com/ja-jp/entra/msidweb/authentication/authorization)

### Microsoft Entra ID 認証 SDK (サイドカー)

#### 概要

- [Entra ID 認証 SDK (サイドカー) とは?](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/overview)
- [Microsoftとの比較。Identity.Web](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/comparison)

#### 作業の開始

- [インストールと展開](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/installation)
- [構成参照](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/configuration)
- [セキュリティのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/security)

#### チュートリアル

- [承認ヘッダーを検証する](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/scenarios/validate-authorization-header)
- [ダウンストリーム API を呼び出す](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/scenarios/call-downstream-api)
- [マネージド ID の使用](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/scenarios/managed-identity)
- [TypeScript から統合する](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/scenarios/using-from-typescript)
- [Pythonからの統合](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/scenarios/using-from-python)

### フレームワーク サポート

#### 攻略ガイド

- [.NET Aspire](https://learn.microsoft.com/ja-jp/entra/msidweb/frameworks/aspire)
- [ASP.NET Framework と .NET Standard](https://learn.microsoft.com/ja-jp/entra/msidweb/frameworks/aspnet-framework)
- [Microsoft.Identity.Web と共に MSAL.NET を使用します。](https://learn.microsoft.com/ja-jp/entra/msidweb/frameworks/msal-dotnet-framework)
- [OWIN の統合](https://learn.microsoft.com/ja-jp/entra/msidweb/frameworks/owin)

#### リファレンス

- [IDownstreamApi への移行](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/migrate-to-downstreamapi)
- [Graph サービス クライアント](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/graph-service-client)

### ダウンストリーム API を呼び出す

#### 概念

- [API 呼び出し方法を選択する](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/overview)
- [トークン バインディング (mTLS)](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/token-binding)
- [エージェントのアイデンティティ](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/agent-identities)

#### 攻略ガイド

- [Web アプリから API を呼び出す](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/from-web-apps)
- [Web API から API を呼び出す](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/from-web-apis)
- [Microsoft Graph を呼び出す](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/microsoft-graph)
- [Azure SDK を呼び出す](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/azure-sdks)
- [カスタム API を呼び出す](https://learn.microsoft.com/ja-jp/entra/msidweb/call-downstream-apis/custom-apis)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/advanced/api-gateways"} -->
## ゲートウェイの背後に保護された API をデプロイする

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/advanced/api-gateways
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: Microsoft.Identity.Webで保護されたASP.NET Core Web APIをAzure API Management、Application Gateway、その他のリバースプロキシの背後にデプロイする方法を学びましょう。

Microsoft.Identity.Web で保護された ASP.NET Core Web API を、Azure API Management (APIM)、Azure Front Door、Azure Application Gateway を含む、Azure API ゲートウェイおよびリバースプロキシの背後にデプロイします。

### ゲートウェイの要件を理解する

ゲートウェイの背後に保護された API をデプロイする場合は、いくつかの問題に対処する必要があります。

- **転送されたヘッダー** - 元の要求コンテキスト (スキーム、ホスト、IP) を保持する
- **トークンの検証** - 対象ユーザーの要求がゲートウェイ URL と一致していることを確認する
- **CORS 構成** - クロスオリジン要求を正しく処理する
- **ヘルスエンドポイント** - 認証不要の正常性チェックを提供します
- **パスベースのルーティング** - ゲートウェイ レベルのパス プレフィックスをサポートする
- **SSL/TLS 終了** - ゲートウェイが SSL を終了するときに HTTPS を適切に処理する

### 一般的なゲートウェイ シナリオを確認する

要件に基づいてゲートウェイを選択します。 次のセクションでは、保護された API の最も一般的なAzure ゲートウェイ サービスについて説明します。

#### Azure API Management (APIM)

**ユース ケース:** ポリシー、レート制限、変換を使用したエンタープライズ API ゲートウェイ

**アーキテクチャ**:

```
Client → Microsoft Entra ID → Token
Client → APIM (apim.azure-api.net) → Backend API (app.azurewebsites.net)
```

**重要な考慮事項:**

- APIM ポリシーは、バックエンドに転送する前に JWT トークンを検証できます
- バックエンド API は引き続きトークンを検証します
- 対象ユーザー要求は、APIM URL またはバックエンド URL と一致する必要があります (適宜構成する)

#### Azure Front Door

**ユース ケース:** グローバル負荷分散、CDN、DDoS 保護

**アーキテクチャ**:

```
Client → Microsoft Entra ID → Token
Client → Front Door (azurefd.net) → Backend API (regional endpoints)
```

**重要な考慮事項:**

- Front Door は、 `X-Forwarded-*` ヘッダーを使用して要求を転送します
- Front Door での SSL/TLS 終端
- トークン対象ユーザーの検証には構成が必要

#### Azure Application Gateway

**ユース ケース:** リージョンの負荷分散、WAF、パスベースのルーティング

**アーキテクチャ**:

```
Client → Microsoft Entra ID → Token
Client → Application Gateway → Backend API (multiple instances)
```

**重要な考慮事項:**

- Web Application Firewall (WAF) 統合
- パスベースのルーティング規則
- バックエンド正常性プローブには、認証されていないエンドポイントが必要です

### 一般的なパターンを構成する

これらの構成パターンを適用して、保護された API がゲートウェイの背後で正しく動作することを確認します。

#### ヘッダーの転送ミドルウェア

ゲートウェイの背後にある場合は、常にフォワード ヘッダー ミドルウェアを設定します。 次のコードは、ミドルウェアを登録し、認証前に実行するように設定します。

```csharp
using Microsoft.AspNetCore.HttpOverrides;

var builder = WebApplication.CreateBuilder(args);

// Configure forwarded headers BEFORE authentication
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;

    // Clear known networks/proxies to accept forwarded headers from any source
    // (Azure infrastructure will be the proxy)
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();

    // Limit to specific headers if needed
    options.ForwardedForHeaderName = "X-Forwarded-For";
    options.ForwardedProtoHeaderName = "X-Forwarded-Proto";
    options.ForwardedHostHeaderName = "X-Forwarded-Host";
});

// Add authentication
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"));

var app = builder.Build();

// USE forwarded headers BEFORE authentication middleware
app.UseForwardedHeaders();
app.UseAuthentication();
app.UseAuthorization();

app.Run();
```

転送されたヘッダー ミドルウェアは、次の理由で重要です。

- ログ記録のために元のクライアント IP アドレスを保持します
- `HttpContext.Request.Scheme`が元の HTTPS スキームを反映していることを確認します
- リダイレクト URL とトークン検証用の正しい `Host` ヘッダーを提供します

#### 2. トークン対象ユーザーの構成

##### オプション A: ゲートウェイ URL とバックエンド URL の両方を受け入れる

`appsettings.json`構成に複数の有効な対象ユーザーを追加します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "your-tenant-id",
    "ClientId": "your-client-id",
    "Audience": "api://your-client-id",
    "TokenValidationParameters": {
      "ValidAudiences": [
        "api://your-client-id",
        "https://your-backend.azurewebsites.net",
        "https://your-apim.azure-api.net"
      ]
    }
  }
}
```

または、 `Program.cs`でプログラムによって複数の対象ユーザーを構成します。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Identity.Web;

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"))
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddInMemoryTokenCaches();

// Customize token validation to accept multiple audiences
builder.Services.Configure<JwtBearerOptions>(JwtBearerDefaults.AuthenticationScheme, options =>
{
    var existingValidation = options.TokenValidationParameters.AudienceValidator;

    options.TokenValidationParameters.AudienceValidator = (audiences, token, parameters) =>
    {
        var validAudiences = new[]
        {
            "api://your-client-id",
            "https://your-backend.azurewebsites.net",
            "https://your-apim.azure-api.net",
            builder.Configuration["AzureAd:ClientId"] // Also accept ClientId
        };

        return audiences.Any(a => validAudiences.Contains(a, StringComparer.OrdinalIgnoreCase));
    };
});
```

##### オプション B: APIM ポリシーで対象ユーザーを書き換える

バックエンドに転送する前に対象ユーザー要求を検証するように APIM を構成します。

```xml
<policies>
    <inbound>
        <validate-jwt header-name="Authorization" failed-validation-httpcode="401">
            <openid-config url="https://login.microsoftonline.com/{tenant-id}/v2.0/.well-known/openid-configuration" />
            <audiences>
                <audience>api://your-client-id</audience>
            </audiences>
        </validate-jwt>

        <!-- Optionally modify token claims for backend -->
        <set-header name="X-Gateway-Validated" exists-action="override">
            <value>true</value>
        </set-header>
    </inbound>
</policies>
```

#### 3. ヘルスエンドポイントの構成

ゲートウェイでは、プローブに認証不要の正常性エンドポイントが必要です。 認証ミドルウェアの前に正常性エンドポイントをマップして、トークンの検証をバイパスします。

```csharp
var app = builder.Build();

// Health endpoint BEFORE authentication middleware
app.MapGet("/health", () => Results.Ok(new { status = "healthy" }))
    .AllowAnonymous();

app.UseForwardedHeaders();
app.UseAuthentication();
app.UseAuthorization();

// Protected endpoints require authentication
app.MapControllers();

app.Run();
```

または、組み込みの ASP.NET Core Health Checks フレームワークを使用して、より充実した正常性レポートを作成することもできます。

```csharp
using Microsoft.Extensions.Diagnostics.HealthChecks;

builder.Services.AddHealthChecks()
    .AddCheck("api", () => HealthCheckResult.Healthy());

var app = builder.Build();

app.MapHealthChecks("/health").AllowAnonymous();
app.MapHealthChecks("/ready").AllowAnonymous();

app.UseForwardedHeaders();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();

app.Run();
```

#### 4. ゲートウェイの背後での CORS 構成

フロントエンド アプリケーションで Azure Front Door または APIM を使用する場合は、ゲートウェイの配信元からの要求を許可するように CORS を構成します。

```csharp
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowGateway", policy =>
    {
        policy.WithOrigins(
            "https://your-apim.azure-api.net",
            "https://your-frontend.azurefd.net",
            "https://your-app.azurewebsites.net"
        )
        .AllowAnyMethod()
        .AllowAnyHeader()
        .AllowCredentials(); // If using cookies
    });
});

var app = builder.Build();

app.UseForwardedHeaders();
app.UseCors("AllowGateway");
app.UseAuthentication();
app.UseAuthorization();

app.Run();
```

重要

CORS は、転送されたヘッダー **の後** と認証 **の前に** 構成する必要があります。

### Azure API Managementとの統合

このセクションでは、保護された API をAzure API Managementの背後にデプロイするための完全な構成について説明します。

#### バックエンド API を構成する

`Program.cs` で転送されたヘッダーとMicrosoft Entra ID認証を設定します。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.HttpOverrides;
using Microsoft.Identity.Web;

var builder = WebApplication.CreateBuilder(args);

// Forwarded headers for APIM
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.All;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});

// Authentication
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"));

builder.Services.AddControllers();

var app = builder.Build();

// Middleware order matters
app.UseForwardedHeaders();
app.UseHttpsRedirection();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();

app.Run();
```

Microsoft Entra構成を `appsettings.json` に追加します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "your-tenant-id",
    "ClientId": "your-backend-api-client-id",
    "Audience": "api://your-backend-api-client-id"
  }
}
```

#### JWT 検証用の APIM 受信ポリシーを追加する

JWT トークンを検証し、レート制限を適用し、バックエンドに要求を転送する受信ポリシーを定義します。

```xml
<policies>
    <inbound>
        <base />

        <!-- Validate JWT token -->
        <validate-jwt header-name="Authorization" failed-validation-httpcode="401" failed-validation-error-message="Unauthorized">
            <openid-config url="https://login.microsoftonline.com/{your-tenant-id}/v2.0/.well-known/openid-configuration" />
            <audiences>
                <audience>api://your-backend-api-client-id</audience>
            </audiences>
            <issuers>
                <issuer>https://login.microsoftonline.com/{your-tenant-id}/v2.0</issuer>
            </issuers>
            <required-claims>
                <claim name="scp" match="any">
                    <value>access_as_user</value>
                </claim>
            </required-claims>
        </validate-jwt>

        <!-- Rate limiting -->
        <rate-limit calls="100" renewal-period="60" />

        <!-- Forward original host header -->
        <set-header name="X-Forwarded-Host" exists-action="override">
            <value>@(context.Request.OriginalUrl.Host)</value>
        </set-header>

        <!-- Forward to backend -->
        <set-backend-service base-url="https://your-backend.azurewebsites.net" />
    </inbound>

    <backend>
        <base />
    </backend>

    <outbound>
        <base />
    </outbound>

    <on-error>
        <base />
    </on-error>
</policies>
```

#### APIM API の設定を構成する

APIM 構成を完了するには、次の名前付き値と API 設定を使用します。

**名前付き値 (再利用性のため):**

- `tenant-id`: Microsoft Entra テナント ID
- `backend-api-client-id`: バックエンド API のクライアント ID
- `backend-base-url`: `https://your-backend.azurewebsites.net`

**API 設定:**

- **API URL サフィックス**: `/api` (省略可能なパス プレフィックス)
- **Web サービス URL**: 名前付き値を使用してポリシーを使用して設定する
- **サブスクリプションが必要**: はい (別のセキュリティ層を追加)

#### クライアント アプリケーションを構成する

クライアント アプリは、APIM ではなくバックエンド **API** のトークンを要求します。 次のコードは、トークンを取得し、APIM エンドポイントを介して API を呼び出します。

```csharp
// Client app requests token
var result = await app.AcquireTokenSilent(
    scopes: new[] { "api://your-backend-api-client-id/access_as_user" },
    account)
    .ExecuteAsync();

// Call APIM URL with token
var client = new HttpClient();
client.DefaultRequestHeaders.Authorization =
    new AuthenticationHeaderValue("Bearer", result.AccessToken);

// Add APIM subscription key
client.DefaultRequestHeaders.Add("Ocp-Apim-Subscription-Key", "your-subscription-key");

var response = await client.GetAsync("https://your-apim.azure-api.net/api/weatherforecast");
```

### Azure Front Door と統合する

Azure Front Doorの背後にあるグローバル分散用に保護された API を構成します。

#### バックエンド API を構成する

Azure Front Doorの転送されたヘッダーを`Program.cs`で設定します。

```csharp
using Microsoft.AspNetCore.HttpOverrides;

var builder = WebApplication.CreateBuilder(args);

// Configure for Azure Front Door
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;

    // Accept headers from any source (Azure Front Door)
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();

    // Front Door specific headers
    options.ForwardedForHeaderName = "X-Forwarded-For";
    options.ForwardedProtoHeaderName = "X-Forwarded-Proto";
});

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"));

var app = builder.Build();

app.UseForwardedHeaders();
app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
```

#### Front Door のオリジンを設定する

Azure ポータルで次の手順を実行して、Front Door の配信元を設定します。

1. Front Door プロファイルの作成
2. バックエンド API インスタンスで配信元グループを追加する
3. `/health` エンドポイントに正常性プローブを構成する
4. HTTPS のみの転送を設定する
5. WAF ポリシーを有効にする (省略可能)

**ヘルスプローブの設定:**

- **パス**: `/health`
- **プロトコル**: HTTPS
- **メソッド**: GET
- **間隔**: 30 秒

#### 複数のリージョンを処理する

Front Door の背後にある複数のリージョンにデプロイする場合は、ログ記録と診断に関するリージョン認識を追加します。

```csharp
// Add region awareness for logging/diagnostics
builder.Services.AddSingleton<IHttpContextAccessor, HttpContextAccessor>();

app.Use(async (context, next) =>
{
    // Log the actual client IP and region
    var clientIp = context.Connection.RemoteIpAddress?.ToString();
    var forwardedFor = context.Request.Headers["X-Forwarded-For"].ToString();
    var frontDoorId = context.Request.Headers["X-Azure-FDID"].ToString();

    // Add to logger scope or response headers
    context.Response.Headers.Add("X-Served-By-Region",
        builder.Configuration["Region"] ?? "unknown");

    await next();
});
```

#### Front Door を使用してトークンを検証する

クライアントが Front Door URL のスコープを持つトークンを要求する場合は、有効な対象ユーザーリストに追加します。

```csharp
builder.Services.Configure<JwtBearerOptions>(JwtBearerDefaults.AuthenticationScheme, options =>
{
    options.TokenValidationParameters.ValidAudiences = new[]
    {
        "api://your-backend-api-client-id",
        "https://your-frontend.azurefd.net", // Front Door URL
        builder.Configuration["AzureAd:ClientId"]
    };
});
```

### Azure Application Gateway との統合

Azure Application Gateway の背後にある保護された API を Web Application Firewall (WAF) サポートで構成します。

#### バックエンド API を構成する

Application Gateway の転送用ヘッダーを `Program.cs` で設定します。

```csharp
using Microsoft.AspNetCore.HttpOverrides;

var builder = WebApplication.CreateBuilder(args);

// Application Gateway uses standard forwarded headers
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"));

builder.Services.AddHealthChecks();

var app = builder.Build();

// Health endpoint for Application Gateway probes
app.MapHealthChecks("/health").AllowAnonymous();

app.UseForwardedHeaders();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();

app.Run();
```

#### Application Gateway の設定を構成する

Azure ポータルで、次のバックエンド、正常性プローブ、および WAF 設定を設定します。

**バックエンド設定:**

- **プロトコル**: HTTPS (推奨) または HTTP
- **ポート**: 443 または 80
- **オーバーライド バックエンド パス**: いいえ (必要な場合を除く)
- **カスタム プローブ**: はい、`/health`を指しています

**健康プローブ:**

- **プロトコル**: HTTPS または HTTP
- **ホスト**: 既定値のままにするか、指定します
- **パス**: `/health`
- **間隔**: 30 秒
- **異常しきい値**: 3

**WAF ポリシー:**

- OWASP 3.2 ルールセットで WAF を有効にする
- **重要**: `Authorization` ヘッダー内の JWT トークンがブロックされていないことを確認する
- "Authorization" を含む `RequestHeaderNames` の WAF 除外を作成することが必要になる場合があります

#### パスベースのルーティングを設定する

パスベースのルーティング規則を使用する場合は、パス プレフィックスを処理するようにバックエンド API を構成します。

```csharp
// Backend API should work regardless of path prefix
var app = builder.Build();

// Option 1: Use path base (if gateway adds prefix)
app.UsePathBase("/api/v1");

// Option 2: Configure routing explicitly
app.UseForwardedHeaders();
app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
```

**Application Gateway ルール:**

- **パス**: `/api/v1/*`
- **バックエンド ターゲット**: バックエンド プール
- **バックエンド設定**: 構成済みの設定を使用する

### 一般的な問題のトラブルシューティング

これらのソリューションを使用して、ゲートウェイの背後に保護された API をデプロイするときの最も一般的な問題を解決します。

#### 問題: 401 ゲートウェイの背後にデプロイした後に承認されていません

**症状：**

- API はローカルで動作しますが、ゲートウェイの背後で 401 を返します
- トークンは、jwt.ms でデコードされると有効なようです

**考えられる原因**:

1. **対象ユーザー要求の不一致**

    ```bash
    # Check token audience
    # Decode token and verify 'aud' claim matches one of:
    # - api://your-client-id
    # - https://your-backend.azurewebsites.net
    # - https://your-gateway-url
    ```
2. **転送されたヘッダー ミドルウェアが見つかりません**

    ```csharp
    // Ensure this is BEFORE authentication
    app.UseForwardedHeaders();
    app.UseAuthentication();
    ```
3. **HTTPS リダイレクトの問題**

    ```csharp
    // If gateway terminates SSL, may need to disable or configure carefully
    if (!app.Environment.IsDevelopment())
    {
        app.UseHttpsRedirection();
    }
    ```

**Solution:**

- デバッグ ログを有効にしてトークン検証の詳細を表示する
- トークン検証に複数の有効な対象ユーザーを追加する
- `X-Forwarded-*` ヘッダーがゲートウェイによって転送されることを確認する

#### 問題: ヘルスプローブが失敗する

**症状：**

- ゲートウェイによってバックエンドが異常としてマークされる
- ヘルスエンドポイントから 401 が返される

**Solution:**

認証ミドルウェアの前にヘルスチェックエンドポイントが実行されるようにします。

```csharp
// Ensure health endpoint is BEFORE authentication
app.MapHealthChecks("/health").AllowAnonymous();

// Alternative: Use custom middleware
app.Map("/health", healthApp =>
{
    healthApp.Run(async context =>
    {
        context.Response.StatusCode = 200;
        await context.Response.WriteAsync("healthy");
    });
});

app.UseAuthentication(); // Health endpoint bypasses this
```

#### 問題: Front Door の背後にある CORS エラー

**症状：**

- プレフライト OPTIONS 要求が失敗する
- ブラウザー コンソールに CORS エラーが表示される

**Solution:**

CORS ポリシーに Front Door とフロントエンドのオリジンを追加します。

```csharp
builder.Services.AddCors(options =>
{
    options.AddDefaultPolicy(policy =>
    {
        policy.WithOrigins(
            "https://your-frontend.azurefd.net",
            "https://your-app.com"
        )
        .AllowAnyMethod()
        .AllowAnyHeader()
        .AllowCredentials();
    });
});

var app = builder.Build();

app.UseForwardedHeaders();
app.UseCors(); // Before authentication
app.UseAuthentication();
app.UseAuthorization();
```

#### 問題: ログにおける「転送されたヘッダー」の警告

**症状：**

```
Microsoft.AspNetCore.HttpOverrides.ForwardedHeadersMiddleware: Unknown proxy
```

**Solution:**

既知のネットワークとプロキシをクリアして、Azureインフラストラクチャから転送されたヘッダーを受け入れます。

```csharp
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    // Clear known networks to accept from any proxy
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();

    // Or explicitly add Azure IP ranges (more secure but complex)
    // options.KnownProxies.Add(IPAddress.Parse("20.x.x.x"));
});
```

#### 問題: APIM は 401 を返しますが、バックエンドは 200 を返します

**症状：**

- トークンはバックエンドに対して有効です
- APIM `validate-jwt` ポリシーが失敗する

**Solution:**

APIM ポリシーの対象ユーザーがトークンの対象ユーザーと一致するかどうかを確認します。

```xml
<validate-jwt header-name="Authorization">
    <openid-config url="https://login.microsoftonline.com/{tenant}/v2.0/.well-known/openid-configuration" />
    <audiences>
        <!-- Must match the 'aud' claim in your token -->
        <audience>api://your-backend-api-client-id</audience>
    </audiences>
</validate-jwt>
```

#### 問題: 複数の認証スキームが競合する

**症状：**

- JWT ベアラーとその他のスキームの両方を使用する
- 誤ったスキームが選択されている

**Solution:**

コントローラーで認証スキームを明示的に指定します。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Identity.Web;

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"))
    .AddScheme<MyCustomOptions, MyCustomHandler>("CustomScheme", options => {});

// In controller, specify scheme explicitly
[Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
public class WeatherForecastController : ControllerBase
{
    // ...
}
```

### ベスト プラクティスに従う

これらのプラクティスを適用して、ゲートウェイの背後に安全で回復性の高い API デプロイを構築します。

#### 1. 多層防御

ゲートウェイ**がトークンを検証した場合でも、バックエンド API でトークンを常**に検証します。

```csharp
// Gateway validates token (APIM policy)
// Backend ALSO validates token (Microsoft.Identity.Web)
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"));
```

ゲートウェイの構成は変更でき、トークンを再生できます。 多層防御は、セキュリティにとって非常に重要です。

#### 2. ゲートウェイ間通信にマネージド ID を使用する

ゲートウェイが独自の ID でバックエンドを呼び出す場合は、ユーザー トークンとマネージド ID トークンの両方を受け入れるようにバックエンドを構成します。

```csharp
// Backend accepts both user tokens and gateway's managed identity
builder.Services.Configure<JwtBearerOptions>(JwtBearerDefaults.AuthenticationScheme, options =>
{
    options.TokenValidationParameters.ValidAudiences = new[]
    {
        "api://backend-api-client-id", // User tokens
        "https://management.azure.com" // Managed identity tokens (if applicable)
    };
});
```

#### 3.ゲートウェイ メトリックを監視する

ゲートウェイのデプロイの可視性を維持するために、次の主要なメトリックを追跡します。

- 401/403 エラー率
- トークン検証エラー
- 正常性プローブの障害
- 転送されたヘッダー (デバッグ用)

#### 4. Application Insights を使用する

Application Insights テレメトリを追加して、ゲートウェイ固有の要求プロパティをログに記録します。

```csharp
builder.Services.AddApplicationInsightsTelemetry();

// Log custom properties
app.Use(async (context, next) =>
{
    var telemetry = context.RequestServices.GetRequiredService<TelemetryClient>();
    telemetry.TrackEvent("ApiRequest", new Dictionary<string, string>
    {
        ["ForwardedFor"] = context.Request.Headers["X-Forwarded-For"],
        ["OriginalHost"] = context.Request.Headers["X-Forwarded-Host"],
        ["Gateway"] = "APIM" // or "FrontDoor", "AppGateway"
    });

    await next();
});
```

#### 5. 正常性を準備完了から分離する

ライブネス (サービスは実行中ですか?) と準備 (サービスがトラフィックを受け入れるか) のチェックには、個別のエンドポイントを使用します。

```csharp
// Health: Is the service running?
app.MapGet("/health", () => Results.Ok()).AllowAnonymous();

// Ready: Can the service accept traffic?
app.MapHealthChecks("/ready", new HealthCheckOptions
{
    Predicate = check => check.Tags.Contains("ready")
}).AllowAnonymous();

builder.Services.AddHealthChecks()
    .AddCheck("database", () => /* check DB */ , tags: new[] { "ready" })
    .AddCheck("cache", () => /* check cache */ , tags: new[] { "ready" });
```

#### 6. ゲートウェイの構成を文書化する

次のドキュメントを含む README または Wiki ページを作成します。

- 使用中のゲートウェイはどれですか
- トークンの対象ユーザーの期待
- CORS 構成
- ヘルスプローブエンドポイント
- 転送されたヘッダーの構成
- 緊急ロールバック手順

### Azure API Managementを使用して完全な例を作成する

このセクションでは、Microsoft Entra ID認証を使用したAzure API Managementの背後にある ASP.NET Core API の完全な実稼働対応の例を示します。

#### バックエンド API (ASP.NET Core)

次の `Program.cs` では、転送ヘッダー、Microsoft Entra 認証、正常性チェック、および Application Insights を構成します。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.HttpOverrides;
using Microsoft.Identity.Web;

var builder = WebApplication.CreateBuilder(args);

// Forwarded headers for APIM
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.All;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});

// Authentication
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"))
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddMicrosoftGraph()
    .AddInMemoryTokenCaches();

// Application Insights
builder.Services.AddApplicationInsightsTelemetry();

// Health checks
builder.Services.AddHealthChecks();

builder.Services.AddControllers();

var app = builder.Build();

// Health endpoint (unauthenticated)
app.MapHealthChecks("/health").AllowAnonymous();

// Middleware order is critical
app.UseForwardedHeaders();
app.UseHttpsRedirection();
app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
```

次のMicrosoft Entraと Application Insights の構成を `appsettings.json` に追加します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "your-tenant-id",
    "ClientId": "backend-api-client-id",
    "Audience": "api://backend-api-client-id"
  },
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.AspNetCore": "Warning",
      "Microsoft.Identity.Web": "Debug"
    }
  },
  "ApplicationInsights": {
    "ConnectionString": "your-connection-string"
  }
}
```

次のコントローラーでは、デバッグ用の認証ヘッダーとログ転送ヘッダーが必要です。

```csharp
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Identity.Web.Resource;

[Authorize]
[ApiController]
[Route("[controller]")]
[RequiredScope("access_as_user")]
public class WeatherForecastController : ControllerBase
{
    private readonly ILogger<WeatherForecastController> _logger;

    public WeatherForecastController(ILogger<WeatherForecastController> logger)
    {
        _logger = logger;
    }

    [HttpGet]
    public IActionResult Get()
    {
        // Log forwarded headers for debugging
        var forwardedFor = HttpContext.Request.Headers["X-Forwarded-For"];
        var forwardedHost = HttpContext.Request.Headers["X-Forwarded-Host"];

        _logger.LogInformation(
            "Request from {ForwardedFor} via {ForwardedHost}",
            forwardedFor,
            forwardedHost);

        return Ok(new[] { "Weather", "Forecast", "Data" });
    }
}
```

#### APIM 構成

次の受信ポリシーは、JWT トークンの検証、レート制限の適用、ヘッダーの転送、CORS の構成を行います。

```xml
<policies>
    <inbound>
        <base />

        <!-- Rate limiting per subscription -->
        <rate-limit-by-key calls="100" renewal-period="60"
                           counter-key="@(context.Subscription.Id)" />

        <!-- Validate JWT -->
        <validate-jwt header-name="Authorization"
                      failed-validation-httpcode="401"
                      failed-validation-error-message="Unauthorized">
            <openid-config url="https://login.microsoftonline.com/{tenant-id}/v2.0/.well-known/openid-configuration" />
            <audiences>
                <audience>api://backend-api-client-id</audience>
            </audiences>
            <issuers>
                <issuer>https://login.microsoftonline.com/{tenant-id}/v2.0</issuer>
            </issuers>
            <required-claims>
                <claim name="scp" match="any">
                    <value>access_as_user</value>
                </claim>
            </required-claims>
        </validate-jwt>

        <!-- Forward headers -->
        <set-header name="X-Forwarded-Host" exists-action="override">
            <value>@(context.Request.OriginalUrl.Host)</value>
        </set-header>
        <set-header name="X-Forwarded-Proto" exists-action="override">
            <value>@(context.Request.OriginalUrl.Scheme)</value>
        </set-header>

        <!-- Backend URL -->
        <set-backend-service base-url="https://your-backend.azurewebsites.net" />
    </inbound>

    <backend>
        <base />
    </backend>

    <outbound>
        <base />

        <!-- Add CORS headers if needed -->
        <cors>
            <allowed-origins>
                <origin>https://your-frontend.com</origin>
            </allowed-origins>
            <allowed-methods>
                <method>GET</method>
                <method>POST</method>
            </allowed-methods>
            <allowed-headers>
                <header>*</header>
            </allowed-headers>
        </cors>
    </outbound>

    <on-error>
        <base />
    </on-error>
</policies>
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/advanced/customization"} -->
## Microsoft.Identity.Web を使用して認証をカスタマイズします。

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/advanced/customization
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: Microsoft.Identity.Web を使用して ASP.NET Core アプリの認証動作をカスタマイズし、セキュリティを維持しつつ、イベント、クレーム、サインインオプションを設定します。

Microsoft.Identity.Web は、Microsoft Entra ID と統合された ASP.NET Core アプリケーションにおいて、認証および承認のためのセキュリティ保護されたデフォルトを提供します。 ライブラリの組み込みのセキュリティ機能を維持しながら、認証動作のさまざまな側面をカスタマイズできます。

### カスタマイズ可能な領域を特定する

| 面積 | カスタマイズ オプション |
| --- | --- |
| **Configuration** | すべての `MicrosoftIdentityOptions`、 `OpenIdConnectOptions`、 `JwtBearerOptions` プロパティ |
| **イベント** | OpenID Connect イベント (`OnTokenValidated`、 `OnRedirectToIdentityProvider`など) |
| **トークンの取得** | 関連付け ID、追加のクエリ パラメーター |
| **請求** | カスタム要求を追加する `ClaimsPrincipal` |
| **UI** | サインアウト ページ、リダイレクト動作 |
| **サインイン** | ログイン ヒント、ドメイン ヒント |

### カスタマイズ方法を選択する

次の表は、カスタマイズできる領域と、各領域でサポートされる内容をまとめたものです。

オプションをカスタマイズするには、次の 2 つの方法のいずれかを使用します。

1. **`Configure<TOptions>`** - 使用する前にオプションを構成する
2. **`PostConfigure<TOptions>`** - すべての `Configure` 呼び出しの後にオプションを構成します

**実行順序:**

```
Configure → Configure → ... → PostConfigure → PostConfigure → ... → Options used
```

### 認証オプションを構成する

このセクションでは、Microsoft.Identity.Webで使用されるさまざまな認証オプションクラスを設定する方法について説明します。

#### 構成マッピングについて

`"AzureAd"`の `appsettings.json` セクションは、複数のクラスにマップされます。

- [`MicrosoftIdentityOptions`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.web.microsoftidentityoptions)
- [`ConfidentialClientApplicationOptions`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplicationoptions)

これらのクラスの任意のプロパティを構成で使用できます。

#### パターン 1: MicrosoftIdentityOptions を構成する

次のコードでは、PII ログの有効化、クライアント機能の設定、トークン検証パラメーターの調整を行うために、 `MicrosoftIdentityOptions` をカスタマイズします。

```csharp
using Microsoft.Identity.Web;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"));

// Customize Microsoft Identity options
builder.Services.Configure<MicrosoftIdentityOptions>(options =>
{
    // Enable PII logging (development only!)
    options.EnablePiiLogging = true;

    // Custom client capabilities
    options.ClientCapabilities = new[] { "CP1", "CP2" };

    // Override token validation parameters
    options.TokenValidationParameters.ValidateLifetime = true;
    options.TokenValidationParameters.ClockSkew = TimeSpan.FromMinutes(5);
});

var app = builder.Build();
```

#### パターン 2: OpenIdConnectOptions (Web アプリ) を構成する

次のコードでは、Web アプリの `OpenIdConnectOptions` をカスタマイズして、応答の種類を設定し、スコープを追加し、Cookie とトークンの検証設定を構成します。

```csharp
builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"));

// Customize OpenIdConnect options
builder.Services.Configure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options =>
{
    // Override response type
    options.ResponseType = "code id_token";

    // Add extra scopes
    options.Scope.Add("offline_access");
    options.Scope.Add("profile");

    // Customize token validation
    options.TokenValidationParameters.NameClaimType = "preferred_username";
    options.TokenValidationParameters.RoleClaimType = "roles";

    // Set redirect URI
    options.CallbackPath = "/signin-oidc";

    // Configure cookie options
    options.Cookie.HttpOnly = true;
    options.Cookie.SecurePolicy = CookieSecurePolicy.Always;
    options.Cookie.SameSite = SameSiteMode.Lax;
});
```

#### パターン 3: JwtBearerOptions (Web API) を構成する

次のコードでは、Web API の `JwtBearerOptions` をカスタマイズして、有効な対象ユーザー、要求マッピング、トークンの有効期間の検証を設定します。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"));

// Customize JWT Bearer options
builder.Services.Configure<JwtBearerOptions>(
    JwtBearerDefaults.AuthenticationScheme,
    options =>
{
    // Customize audience validation
    options.TokenValidationParameters.ValidAudiences = new[]
    {
        "api://your-api-client-id",
        "https://your-api.com"
    };

    // Set custom claim mappings
    options.TokenValidationParameters.NameClaimType = "name";
    options.TokenValidationParameters.RoleClaimType = "roles";

    // Customize token validation
    options.TokenValidationParameters.ValidateLifetime = true;
    options.TokenValidationParameters.ClockSkew = TimeSpan.Zero; // No tolerance
});
```

#### パターン 4: Cookie オプションを構成する

次のコードでは、セキュリティ設定や有効期限の動作など、アプリの Cookie ポリシーと Cookie 認証オプションを構成します。

```csharp
using Microsoft.AspNetCore.Authentication.Cookies;

// Configure cookie policy
builder.Services.Configure<CookiePolicyOptions>(options =>
{
    options.MinimumSameSitePolicy = SameSiteMode.Lax;
    options.Secure = CookieSecurePolicy.Always;
    options.HttpOnly = Microsoft.AspNetCore.CookiePolicy.HttpOnlyPolicy.Always;
});

// Configure cookie authentication options
builder.Services.Configure<CookieAuthenticationOptions>(
    CookieAuthenticationDefaults.AuthenticationScheme,
    options =>
{
    options.Cookie.Name = "MyApp.Auth";
    options.Cookie.HttpOnly = true;
    options.Cookie.SecurePolicy = CookieSecurePolicy.Always;
    options.Cookie.SameSite = SameSiteMode.Lax;
    options.ExpireTimeSpan = TimeSpan.FromHours(1);
    options.SlidingExpiration = true;
});
```

### イベント ハンドラーをカスタマイズする

OpenID Connect と JWT Bearer 認証では、フックできるイベントが公開されます。 Microsoft。Identity.Web は独自のイベント ハンドラーを設定するため、組み込みの機能を維持するには、カスタム ハンドラーを既存のハンドラーと連結する必要があります。

#### 既存のハンドラーを保持する

カスタム イベント ハンドラーを追加するときは、常に既存のハンドラーを保存して呼び出します。 次の例は、間違った正しい方法を示しています。

次のコード**誤って**Microsoft.Identity.Web ハンドラーを上書きします。

```csharp
services.Configure<JwtBearerOptions>(JwtBearerDefaults.AuthenticationScheme, options =>
{
    options.Events.OnTokenValidated = async context =>
    {
        // Your code - but you LOST the built-in validation!
        await Task.CompletedTask;
    };
});
```

次のコードは、既存のハンドラーと **正しく** チェーンします。

```csharp
services.Configure<JwtBearerOptions>(JwtBearerDefaults.AuthenticationScheme, options =>
{
    var existingOnTokenValidatedHandler = options.Events.OnTokenValidated;

    options.Events.OnTokenValidated = async context =>
    {
        // Call Microsoft.Identity.Web's handler FIRST
        await existingOnTokenValidatedHandler(context);

        // Then your custom code
        // (executes AFTER built-in security checks)
        var identity = context.Principal.Identity as ClaimsIdentity;
        identity?.AddClaim(new Claim("custom_claim", "custom_value"));
    };
});
```

#### 一般的なイベント シナリオを適用する

##### トークン検証後にカスタム要求を追加する

次のコードでは、Web API でのトークン検証後に、 `ClaimsPrincipal` にカスタム要求を追加します。 データベースからユーザーの部署を検索し、電子メール ドメインに基づいてアプリケーション固有のロールを割り当てます。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using System.Security.Claims;

builder.Services.Configure<JwtBearerOptions>(
    JwtBearerDefaults.AuthenticationScheme,
    options =>
{
    var existingHandler = options.Events.OnTokenValidated;

    options.Events.OnTokenValidated = async context =>
    {
        // Preserve built-in validation
        await existingHandler(context);

        // Add custom claims
        var identity = context.Principal.Identity as ClaimsIdentity;

        // Example: Add department claim from database
        var userObjectId = context.Principal.FindFirst("oid")?.Value;
        if (!string.IsNullOrEmpty(userObjectId))
        {
            var department = await GetUserDepartment(userObjectId);
            identity?.AddClaim(new Claim("department", department));
        }

        // Example: Add application-specific role
        var email = context.Principal.FindFirst("email")?.Value;
        if (email?.EndsWith("@admin.com") == true)
        {
            identity?.AddClaim(new Claim(ClaimTypes.Role, "SuperAdmin"));
        }
    };
});
```

次のコードでは、トークンの検証後に追加のユーザー プロファイル データを取得するMicrosoft Graphを呼び出して、Web アプリにカスタム要求を追加します。

```csharp
using Microsoft.AspNetCore.Authentication.OpenIdConnect;

builder.Services.Configure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options =>
{
    var existingHandler = options.Events.OnTokenValidated;

    options.Events.OnTokenValidated = async context =>
    {
        // Preserve built-in processing
        await existingHandler(context);

        // Call Microsoft Graph to get additional user data
        var graphClient = context.HttpContext.RequestServices
            .GetRequiredService<GraphServiceClient>();

        var user = await graphClient.Me.GetAsync();

        var identity = context.Principal.Identity as ClaimsIdentity;
        identity?.AddClaim(new Claim("jobTitle", user?.JobTitle ?? ""));
        identity?.AddClaim(new Claim("department", user?.Department ?? ""));
    };
});
```

##### 承認要求にクエリ パラメーターを追加する

次のコードは、Microsoft Entra ID プロバイダーに送信された承認要求にカスタム クエリ パラメーターを追加します。

```csharp
builder.Services.Configure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options =>
{
    var existingHandler = options.Events.OnRedirectToIdentityProvider;

    options.Events.OnRedirectToIdentityProvider = async context =>
    {
        // Preserve existing behavior
        if (existingHandler != null)
        {
            await existingHandler(context);
        }

        // Add custom query parameters
        context.ProtocolMessage.Parameters.Add("slice", "testslice");
        context.ProtocolMessage.Parameters.Add("custom_param", "custom_value");

        // Conditional parameters based on request
        if (context.HttpContext.Request.Query.ContainsKey("prompt"))
        {
            context.ProtocolMessage.Prompt = context.HttpContext.Request.Query["prompt"];
        }
    };
});
```

##### 認証エラーの処理をカスタマイズする

次のコードは、エラーをログに記録し、カスタム JSON エラー応答を返すことによって、認証エラーを処理します。

```csharp
builder.Services.Configure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options =>
{
    options.Events.OnAuthenticationFailed = async context =>
    {
        // Log the error
        var logger = context.HttpContext.RequestServices
            .GetRequiredService<ILogger<Program>>();
        logger.LogError(context.Exception, "Authentication failed");

        // Customize error response
        context.Response.StatusCode = 401;
        context.Response.ContentType = "application/json";
        await context.Response.WriteAsync($$"""
            {
                "error": "authentication_failed",
                "error_description": "{{context.Exception.Message}}"
            }
            """);

        context.HandleResponse(); // Suppress default error handling
    };
});
```

##### アクセス拒否の処理

次のコードは、ユーザーが同意を拒否したときにカスタム ページにリダイレクトします。

```csharp
builder.Services.Configure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options =>
{
    options.Events.OnAccessDenied = async context =>
    {
        // User denied consent
        context.Response.Redirect("/Home/AccessDenied");
        context.HandleResponse();
        await Task.CompletedTask;
    };
});
```

### トークンの取得をカスタマイズする

`IDownstreamApi`にオプションを渡すことで、ダウンストリーム API を呼び出すときにトークンを取得する方法をカスタマイズできます。

#### カスタム オプションで IDownstreamApi を使用する

次のコードは、 `IDownstreamApi`を介してトークンを取得するときに、関連付け ID と追加のクエリ パラメーターを渡します。

```csharp
using Microsoft.Identity.Abstractions;

public class TodoListController : ControllerBase
{
    private readonly IDownstreamApi _downstreamApi;

    public TodoListController(IDownstreamApi downstreamApi)
    {
        _downstreamApi = downstreamApi;
    }

    [HttpGet("{id}")]
    public async Task<ActionResult> GetTodo(int id, Guid correlationId)
    {
        var result = await _downstreamApi.GetForUserAsync<Todo>(
            "TodoListService",
            options =>
            {
                options.RelativePath = $"api/todolist/{id}";

                // Customize token acquisition
                options.TokenAcquisitionOptions = new TokenAcquisitionOptions
                {
                    CorrelationId = correlationId,
                    ExtraQueryParameters = new Dictionary<string, string>
                    {
                        { "slice", "test_slice" }
                    }
                };
            });

        return Ok(result);
    }
}
```

### UI をカスタマイズする

ユーザーがサインインおよびサインアウトした後の場所を制御し、サインアウトエクスペリエンスをカスタマイズできます。

#### サインイン後に特定のページにリダイレクトする

サインイン後にユーザーを特定のページに送信するには、 `redirectUri` パラメーターを使用します。

```html
<!-- Razor view -->
<a href="/MicrosoftIdentity/Account/SignIn?redirectUri=/Dashboard">Sign In</a>

<!-- Or in controller -->
[HttpGet]
public IActionResult SignInToDashboard()
{
    return RedirectToAction("SignIn", "Account", new
    {
        area = "MicrosoftIdentity",
        redirectUri = "/Dashboard"
    });
}
```

#### サインアウトページをカスタマイズする

**オプション 1: Razor ページをオーバーライドする**

カスタム コンテンツを使用して `Areas/MicrosoftIdentity/Pages/Account/SignedOut.cshtml` にファイルを作成します。

```cshtml
@page
@model Microsoft.Identity.Web.UI.Areas.MicrosoftIdentity.Pages.Account.SignedOutModel
@{
    ViewData["Title"] = "Signed out";
}

<div class="container text-center mt-5">
    <h1>You have been signed out</h1>
    <p>Thank you for using our application.</p>
    <a asp-area="" asp-controller="Home" asp-action="Index" class="btn btn-primary">
        Return to Home
    </a>
</div>
```

**オプション 2: カスタム ページにリダイレクトする**

次のコードは、ユーザーを既定ではなくカスタムのサインアウト ページにリダイレクトします。

```csharp
builder.Services.Configure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options =>
{
    options.Events.OnSignedOutCallbackRedirect = context =>
    {
        context.Response.Redirect("/Home/SignedOut");
        context.HandleResponse();
        return Task.CompletedTask;
    };
});
```

### サインイン エクスペリエンスをカスタマイズする

#### ログイン ヒントとドメイン ヒントを使用する

ユーザー名を事前に設定し、特定のMicrosoft Entra テナントにユーザーを誘導することで、サインイン エクスペリエンスを効率化します。

##### ヒントを理解する

| Hint | Purpose | 例 |
| --- | --- | --- |
| **loginHint** | ユーザー名/電子メール フィールドを事前設定する | `"user@contoso.com"` |
| **domainHint** | 特定のテナント ログイン ページに直接移動する | `"contoso.com"` |

##### ヒント パターンを適用する

**パターン 1: コントローラー ベース**

次のコードは、標準サインイン、ログイン ヒント、ドメイン ヒント、またはその両方を使用したサインインに対するコントローラー アクションを示しています。

```csharp
using Microsoft.AspNetCore.Mvc;

public class AuthController : Controller
{
    [HttpGet]
    public IActionResult SignIn()
    {
        // Standard sign-in
        return RedirectToAction("SignIn", "Account", new
        {
            area = "MicrosoftIdentity",
            redirectUri = "/Dashboard"
        });
    }

    [HttpGet]
    public IActionResult SignInWithLoginHint()
    {
        // Pre-populate username
        return RedirectToAction("SignIn", "Account", new
        {
            area = "MicrosoftIdentity",
            redirectUri = "/Dashboard",
            loginHint = "user@contoso.com"
        });
    }

    [HttpGet]
    public IActionResult SignInWithDomainHint()
    {
        // Direct to Contoso tenant
        return RedirectToAction("SignIn", "Account", new
        {
            area = "MicrosoftIdentity",
            redirectUri = "/Dashboard",
            domainHint = "contoso.com"
        });
    }

    [HttpGet]
    public IActionResult SignInWithBothHints()
    {
        // Pre-populate AND direct to tenant
        return RedirectToAction("SignIn", "Account", new
        {
            area = "MicrosoftIdentity",
            redirectUri = "/Dashboard",
            loginHint = "user@contoso.com",
            domainHint = "contoso.com"
        });
    }
}
```

**パターン 2: ビュー ベース**

次の HTML は、さまざまなヒント構成を持つサインイン リンクを示しています。

```html
<div class="sign-in-options">
    <h2>Sign In Options</h2>

    <!-- Standard sign-in -->
    <a href="/MicrosoftIdentity/Account/SignIn?redirectUri=/Dashboard"
       class="btn btn-primary">
        Sign In
    </a>

    <!-- With login hint -->
    <a href="/MicrosoftIdentity/Account/SignIn?redirectUri=/Dashboard&loginHint=user@contoso.com"
       class="btn btn-secondary">
        Sign In as user@contoso.com
    </a>

    <!-- With domain hint -->
    <a href="/MicrosoftIdentity/Account/SignIn?redirectUri=/Dashboard&domainHint=contoso.com"
       class="btn btn-secondary">
        Sign In (Contoso)
    </a>
</div>
```

**パターン 3: OnRedirectToIdentityProvider を使用したプログラム**

次のコードは、ID プロバイダーへのリダイレクト中にクエリ パラメーターと Cookie に基づいてヒントを動的に設定します。

```csharp
builder.Services.Configure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options =>
{
    var existingHandler = options.Events.OnRedirectToIdentityProvider;

    options.Events.OnRedirectToIdentityProvider = async context =>
    {
        if (existingHandler != null)
        {
            await existingHandler(context);
        }

        // Add hints based on application logic
        if (context.HttpContext.Request.Query.TryGetValue("tenant", out var tenant))
        {
            context.ProtocolMessage.DomainHint = tenant;
        }

        // Get suggested user from cookie or session
        var suggestedUser = context.HttpContext.Request.Cookies["LastSignedInUser"];
        if (!string.IsNullOrEmpty(suggestedUser))
        {
            context.ProtocolMessage.LoginHint = suggestedUser;
        }
    };
});
```

##### 利用事例

**Eコマース プラットフォーム:**

```csharp
// Pre-fill returning customer email
loginHint = customerEmail
```

**B2B アプリケーション:**

```csharp
// Direct to customer's tenant
domainHint = customerDomain
```

**マルチテナント型SaaS:**

```csharp
// Route based on subdomain
domainHint = GetTenantFromSubdomain(Request.Host)
```

### ベスト プラクティスに従う

#### やるべきこと

**1. 常に既存のイベント ハンドラーを保持します。** カスタム ロジックを実行する前に、既存のハンドラーを保存して呼び出します。

```csharp
var existingHandler = options.Events.OnTokenValidated;
options.Events.OnTokenValidated = async context =>
{
    await existingHandler(context); // Call Microsoft.Identity.Web's handler
    // Your custom code
};
```

**2. トレースに関連付け ID を使用します。** 診断用のトークン取得要求に関連付け ID をアタッチします。

```csharp
var tokenOptions = new TokenAcquisitionOptions
{
    CorrelationId = Activity.Current?.Id ?? Guid.NewGuid()
};
```

**3. カスタム要求を検証します。** アクセスを許可する前に、カスタム要求に予期される値が含まれていることを確認します。

```csharp
var department = context.Principal.FindFirst("department")?.Value;
if (!IsValidDepartment(department))
{
    throw new UnauthorizedAccessException("Invalid department");
}
```

**4. カスタマイズ エラーをログに記録します。** カスタムロジックをtry-catchブロックで囲み、エラーをログに記録します。

```csharp
try
{
    // Custom logic
}
catch (Exception ex)
{
    logger.LogError(ex, "Custom authentication logic failed");
    throw;
}
```

**5. 成功パスと失敗パスの両方をテストします。** テストのすべての認証シナリオについて説明します。

```csharp
// Test with valid tokens
// Test with missing claims
// Test with expired tokens
// Test with wrong audience
```

#### してはいけないこと

**1. Microsoft.Identity.Webのイベントハンドラーをスキップしないでください:**

```csharp
//  Wrong - loses built-in security checks
options.Events.OnTokenValidated = async context => { /* your code */ };

//  Correct - preserves security
var existing = options.Events.OnTokenValidated;
options.Events.OnTokenValidated = async context =>
{
    await existing(context);
    /* your code */
};
```

**2. 運用環境で PII ログを有効にしないでください。**

```csharp
//  Wrong
options.EnablePiiLogging = true; // In production!

//  Correct
if (builder.Environment.IsDevelopment())
{
    options.EnablePiiLogging = true;
}
```

**3. トークン検証をバイパスしない:**

```csharp
//  Wrong - insecure!
options.TokenValidationParameters.ValidateLifetime = false;
options.TokenValidationParameters.ValidateAudience = false;

//  Correct - maintain security
options.TokenValidationParameters.ValidateLifetime = true;
options.TokenValidationParameters.ClockSkew = TimeSpan.FromMinutes(5);
```

**4. 機密値をハードコーディングしないでください。**

```csharp
//  Wrong
options.ClientSecret = "mysecret123";

//  Correct
options.ClientSecret = builder.Configuration["AzureAd:ClientSecret"];
```

**5. ミドルウェアの認証を変更しないでください。**

```csharp
//  Wrong - configure in Startup, not middleware
app.Use(async (context, next) =>
{
    // Modifying auth options here is too late!
});
```

### 一般的な問題のトラブルシューティング

#### カスタマイズが有効にならない問題を解決する

**実行順序を確認します。**

1. `AddMicrosoftIdentityWebApp` / `AddMicrosoftIdentityWebApi` 既定値を設定する
2. `Configure`呼び出しが実行される
3. `PostConfigure` 呼び出しの実行 (ある場合)
4. オプションが使用されます

**ソリューション：**すべての`PostConfigure`呼び出しの後に`Configure`が実行されるため、`PostConfigure`呼び出しが有効でない場合は、`Configure`を使用します。

```csharp
services.PostConfigure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options => { /* your changes */ }
);
```

#### カスタム要求が見つからない問題を修正する

カスタム要求が表示されない場合は、次のことを確認します。

1. `OnTokenValidated` ハンドラーは、既存のハンドラーと正しく連結されます。
2. コードが要求を追加する前に認証が成功します。
3. クレームが正しい `ClaimsIdentity`に追加されます。

次のコードは、デバッグ用のすべての要求をログに記録します。

```csharp
var claims = context.Principal.Claims.ToList();
logger.LogInformation($"Claims count: {claims.Count}");
foreach (var claim in claims)
{
    logger.LogInformation($"{claim.Type}: {claim.Value}");
}
```

#### 発生しないイベントを修正する

イベントが発生しない場合は、認証と承認ミドルウェアが正しい順序で登録されていることを確認します。

```csharp
app.UseAuthentication(); // Must be first
app.UseAuthorization();  // Must be second
app.MapControllers();    // Then endpoints
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/advanced/logging"} -->
## Microsoft.Identity.Web のログ記録と診断

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/advanced/logging
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: Microsoftでログ記録を構成します。Identity.Web を使用して、ASP.NET Core アプリでの認証フロー、トークン取得、ダウンストリーム API 呼び出しの問題を診断します。

Microsoft。Identity.Web は、ASP.NET Coreのログ 記録インフラストラクチャと統合されます。 これを使用して、次の問題を診断します。

- **認証フロー** - サインイン、サインアウト、トークンの検証
- **トークンの取得** - トークン キャッシュヒット/ミス、MSAL 操作
- **ダウンストリーム API 呼び出し** - HTTP 要求、API のトークン取得
- **エラー条件** - 例外、検証エラー

### ログ記録されたコンポーネントを理解する

| コンポーネント | ログ ソース | Purpose |
| --- | --- | --- |
| **Microsoft。Identity.Web** | コア認証ロジック | 構成、トークンの取得、API 呼び出し |
| **MSAL.NET** | `Microsoft.Identity.Client` | トークン キャッシュ操作、機関の検証 |
| **IdentityModel** | トークンの検証 | JWT 解析、署名の検証、要求の抽出 |
| **ASP.NET Core 認証** | `Microsoft.AspNetCore.Authentication` | クッキー操作、検証/禁止するアクション |

### ログ記録を始める

#### 最小構成

次のログ レベルのエントリを `appsettings.json` に追加して、ID ログを有効にします。

```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.Identity": "Information"
    }
  }
}
```

これにより、Microsoft.Identity.Web とその依存関係 (MSAL.NET、IdentityModel) の **Information レベル** のログ記録が有効になります。

#### 開発構成

開発中に詳細な診断を行う場合:

```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft": "Warning",
      "Microsoft.Identity": "Debug",
      "Microsoft.AspNetCore.Authentication": "Information"
    }
  },
  "AzureAd": {
    "EnablePiiLogging": true  // Development only!
  }
}
```

#### 運用構成

運用環境では、エラーをキャプチャしながらログ ボリュームを最小限に抑えます。

```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Warning",
      "Microsoft": "Warning",
      "Microsoft.Identity": "Warning"
    }
  },
  "AzureAd": {
    "EnablePiiLogging": false  // Never true in production
  }
}
```

### ログ のフィルター処理を構成する

#### 名前空間ベースのフィルター処理

名前空間別にログの詳細度を制御します。 次の構成では、ID 関連の名前空間ごとに詳細なレベルを設定します。

```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information",

      // General Microsoft namespaces
      "Microsoft": "Warning",
      "Microsoft.AspNetCore": "Warning",

      // Identity-specific namespaces
      "Microsoft.Identity": "Information",
      "Microsoft.Identity.Web": "Information",
      "Microsoft.Identity.Client": "Information",

      // ASP.NET Core authentication
      "Microsoft.AspNetCore.Authentication": "Information",
      "Microsoft.AspNetCore.Authentication.JwtBearer": "Information",
      "Microsoft.AspNetCore.Authentication.OpenIdConnect": "Debug",

      // Token validation
      "Microsoft.IdentityModel": "Warning"
    }
  }
}
```

#### 特定のログ記録を無効にする

ノイズの多いコンポーネントを他のコンポーネントに影響を与えずに無音にするには、ログ レベルを `None` または `Warning` に設定します。

```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.Identity.Web": "None",  // Completely disable
      "Microsoft.Identity.Client": "Warning"  // Only errors/warnings
    }
  }
}
```

#### 環境固有の構成

環境設定ごとに `appsettings.{Environment}.json` を使用します。

**appsettings.Development.json:**

```json
{
  "Logging": {
    "LogLevel": {
      "Microsoft.Identity": "Debug"
    }
  },
  "AzureAd": {
    "EnablePiiLogging": true
  }
}
```

**appsettings.Production.json:**

```json
{
  "Logging": {
    "LogLevel": {
      "Microsoft.Identity": "Warning"
    }
  },
  "AzureAd": {
    "EnablePiiLogging": false
  }
}
```

### ログ レベルを理解する

ASP.NET Coreでは、次のログ レベルを定義します。 環境のログ ボリュームに対して診断の詳細のバランスを取るレベルを選択します。

#### ASP.NET Core のログレベル

| レベル | 使用方法 | ボリューム | 生産ですか？ |
| --- | --- | --- | --- |
| **トレース** | 最も詳細な、すべての操作 | 非常に高 | いいえ |
| **デバッグ** | 詳細なフロー。開発に役立ちます | 高 | いいえ |
| **情報** | 一般的なフロー、キー イベント | 適度 | 選択的 |
| **警告** | 予期しないが処理された条件 | 低 | はい |
| **エラー** | エラーと例外 | 非常に低い | はい |
| **重大** | 回復不可能なエラー | 非常に低い | はい |
| **なし** | ログ記録を無効にする | なし | 選択的 |

#### MSAL.NET を ASP.NET Core レベルにマップする

| MSAL.NET レベル | ASP.NET Coreに相当する | 説明 |
| --- | --- | --- |
| `Verbose` | `Debug` または `Trace` | 最も詳細なメッセージ |
| `Info` | `Information` | キー認証イベント |
| `Warning` | `Warning` | 異常だが正常に処理された状態 |
| `Error` | `Error` または `Critical` | エラーと例外 |

#### 環境別に推奨設定を適用する

環境ごとに次の構成を使用します。

**開発：**

```json
{
  "Logging": {
    "LogLevel": {
      "Microsoft.Identity": "Debug",
      "Microsoft.Identity.Client": "Information"
    }
  }
}
```

**ステージング：**

```json
{
  "Logging": {
    "LogLevel": {
      "Microsoft.Identity": "Information",
      "Microsoft.Identity.Client": "Warning"
    }
  }
}
```

**生産：**

```json
{
  "Logging": {
    "LogLevel": {
      "Microsoft.Identity": "Warning",
      "Microsoft.Identity.Client": "Error"
    }
  }
}
```

### PII ログの設定

既定では、Microsoft。Identity.Web は、個人を特定できる情報 (PII) をログから編集します。 開発環境でのみ PII ログを有効にして、完全なユーザーの詳細を表示します。

#### PII とは

**個人を特定できる情報 (PII)** には、次のものが含まれます。

- ユーザー名、メール アドレス
- 表示名
- オブジェクト ID、テナント ID
- IP アドレス
- トークン値、要求

#### セキュリティの警告

>
> **警告**: お客様とアプリケーションは、 [GDPR](https://www.microsoft.com/trust-center/privacy/gdpr-overview) で規定されているものを含め、適用されるすべての規制要件に準拠する責任を負います。 PII ログを有効にする前に、この機密性の高いデータを安全に処理できることを確認してください。

#### PII ログを有効にする (開発のみ)

`EnablePiiLogging`を開発構成ファイルの`true`に設定します。

**appsettings.Development.json:**

```json
{
  "AzureAd": {
    "EnablePiiLogging": true  //  Development/Testing ONLY
  },
  "Logging": {
    "LogLevel": {
      "Microsoft.Identity": "Debug"
    }
  }
}
```

#### プログラムによる PII ログ記録の制御

ホスティング環境に基づいて PII ログを切り替えます。

```csharp
var builder = WebApplication.CreateBuilder(args);

builder.Services.Configure<MicrosoftIdentityOptions>(options =>
{
    // Only enable PII in Development
    options.EnablePiiLogging = builder.Environment.IsDevelopment();
});
```

#### PII を有効にした場合の変更点

**PII ログなし:**

```
[Information] Token validation succeeded for user '{hidden}'
[Information] Acquired token from cache for scopes '{hidden}'
```

**PII が有効になっている場合:**

```
[Information] Token validation succeeded for user 'john.doe@contoso.com'
[Information] Acquired token from cache for scopes 'user.read api://my-api/.default'
```

#### ログ内のPIIの抹消

PII ログが無効になっている場合、機密データは次のように置き換えられます。

- `{hidden}` - ユーザー識別子を非表示にします
- `{hash:XXXX}` - 実際の値の代わりにハッシュを表示します
- `***` - トークンを隠す

### 関連付け ID を使用する

関連付け ID は、サービス間で認証要求をトレースします。 問題の解決を高速化するために、ログやサポートチケットにそれらを含めてください。

#### 関連付け ID とは

関連付け ID は、次の間で認証またはトークン取得要求を一意に識別する **GUID** です。

- ご利用のアプリケーション
- Microsoft ID プラットフォーム
- MSAL.NET ライブラリ
- バックエンド サービスのMicrosoft

#### 関連付け ID を取得する

**方法 1: AuthenticationResult から取得**

トークンの取得が成功した後、 `AuthenticationResult` から関連付け ID を抽出します。

```csharp
using Microsoft.Identity.Web;

public class TodoController : ControllerBase
{
    private readonly ITokenAcquisition _tokenAcquisition;
    private readonly ILogger<TodoController> _logger;

    public TodoController(
        ITokenAcquisition tokenAcquisition,
        ILogger<TodoController> logger)
    {
        _tokenAcquisition = tokenAcquisition;
        _logger = logger;
    }

    [HttpGet]
    public async Task<IActionResult> GetTodos()
    {
        var result = await _tokenAcquisition.GetAuthenticationResultForUserAsync(
            new[] { "user.read" });

        _logger.LogInformation(
            "Token acquired. CorrelationId: {CorrelationId}, Source: {TokenSource}",
            result.CorrelationId,
            result.AuthenticationResultMetadata.TokenSource);

        return Ok(result.CorrelationId);
    }
}
```

**方法 2: MsalServiceException を使用して**

トークンの取得が失敗したときに、 `MsalServiceException` から関連付け ID をキャプチャします。

```csharp
using Microsoft.Identity.Client;

try
{
    var token = await _tokenAcquisition.GetAccessTokenForUserAsync(
        new[] { "user.read" });
}
catch (MsalServiceException ex)
{
    _logger.LogError(ex,
        "Token acquisition failed. CorrelationId: {CorrelationId}, ErrorCode: {ErrorCode}",
        ex.CorrelationId,
        ex.ErrorCode);

    // Return correlation ID to user for support
    return StatusCode(500, new {
        error = "authentication_failed",
        correlationId = ex.CorrelationId
    });
}
```

**方法 3: カスタム関連付け ID を設定する**

カスタム関連付け ID を割り当てて、アプリケーション トレースとMicrosoft Entra ID要求をリンクします。

```csharp
[HttpGet("{id}")]
public async Task<IActionResult> GetTodo(int id)
{
    // Use request trace ID as correlation ID
    var correlationId = Activity.Current?.Id ?? HttpContext.TraceIdentifier;

    var todo = await _downstreamApi.GetForUserAsync<Todo>(
        "TodoListService",
        options =>
        {
            options.RelativePath = $"api/todolist/{id}";
            options.TokenAcquisitionOptions = new TokenAcquisitionOptions
            {
                CorrelationId = Guid.Parse(correlationId)
            };
        });

    _logger.LogInformation(
        "Called downstream API. TraceId: {TraceId}, CorrelationId: {CorrelationId}",
        HttpContext.TraceIdentifier,
        correlationId);

    return Ok(todo);
}
```

#### サポート用の関連付け ID を提供する

Microsoftサポートに問い合わせる場合は、次の詳細を入力します。

1. **関連付け ID** - ログまたは例外から
2. **タイムスタンプ** - エラーが発生したとき (UTC)
3. **テナント ID** - あなたの Microsoft Entra ID テナント
4. **エラー コード** - 該当する場合 (例: `AADSTS50058`)

**サポート要求の例:**

```
Subject: Token acquisition failing for user.read scope

Correlation ID: 12345678-1234-1234-1234-123456789012
Timestamp: 2025-01-15 14:32:45 UTC
Tenant ID: contoso.onmicrosoft.com
Error Code: AADSTS50058
```

### トークン キャッシュのログ記録を有効にする

トークン キャッシュ のログ記録は、キャッシュのヒット/ミスの動作を理解し、分散キャッシュのパフォーマンスの問題を診断するのに役立ちます。

#### トークン キャッシュ診断を有効にする

分散トークン キャッシュを使用する .NET Framework または .NET Core アプリの場合は、詳細なログ記録を構成します。

```csharp
using Microsoft.Extensions.Logging;
using Microsoft.Identity.Web.TokenCacheProviders;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddDistributedTokenCaches();

// Enable detailed token cache logging
builder.Services.AddLogging(configure =>
{
    configure.AddConsole();
    configure.AddDebug();
})
.Configure<LoggerFilterOptions>(options =>
{
    options.MinLevel = LogLevel.Debug;  // Detailed cache operations
});
```

#### トークン キャッシュ ログの例

**キャッシュ ヒット:**

```
[Debug] Token cache: Token found in cache for scopes 'user.read'
[Information] Token source: Cache
```

**キャッシュ ミス:**

```
[Debug] Token cache: No token found in cache for scopes 'user.read'
[Information] Token source: IdentityProvider
[Debug] Token cache: Token stored in cache
```

#### 分散キャッシュのトラブルシューティング

プロバイダー固有のログ記録を有効にして、キャッシュの接続とパフォーマンスの問題を診断します。

**Redis Cache:**

```csharp
builder.Services.AddStackExchangeRedisCache(options =>
{
    options.Configuration = builder.Configuration["Redis:ConnectionString"];
});

// Enable Redis logging
builder.Services.AddLogging(configure =>
{
    configure.AddFilter("Microsoft.Extensions.Caching", LogLevel.Debug);
});
```

**SQL Server cache:**

ログを使用して分散キャッシュSQL Server構成します。

```csharp
builder.Services.AddDistributedSqlServerCache(options =>
{
    options.ConnectionString = builder.Configuration["SqlCache:ConnectionString"];
    options.SchemaName = "dbo";
    options.TableName = "TokenCache";
});

// Enable SQL cache logging
builder.Services.AddLogging(configure =>
{
    configure.AddFilter("Microsoft.Extensions.Caching.SqlServer", LogLevel.Information);
});
```

### 一般的な問題のトラブルシューティング

次のシナリオを使用して、頻繁に発生する認証と承認の問題を診断します。

#### 一般的なログ記録シナリオ

##### シナリオ 1: トークン検証エラー

**現象:** 401 未承認の応答

**詳細なログを有効にします。**

```json
{
  "Logging": {
    "LogLevel": {
      "Microsoft.AspNetCore.Authentication.JwtBearer": "Debug",
      "Microsoft.IdentityModel": "Information"
    }
  }
}
```

**以下のものを探します。**

```
[Information] Microsoft.AspNetCore.Authentication.JwtBearer.JwtBearerHandler:
  Failed to validate the token.
[Debug] Microsoft.IdentityModel.Tokens: IDX10230: Lifetime validation failed.
  The token is expired.
```

##### シナリオ 2: トークン取得エラー

**現象:**`MsalServiceException` または `MsalUiRequiredException`

**詳細なログを有効にします。**

```json
{
  "Logging": {
    "LogLevel": {
      "Microsoft.Identity.Web": "Debug",
      "Microsoft.Identity.Client": "Information"
    }
  }
}
```

**以下のものを探します。**

```
[Error] Microsoft.Identity.Web: Token acquisition failed.
  ErrorCode: invalid_grant, CorrelationId: {guid}
[Information] Microsoft.Identity.Client: MSAL returned exception:
  AADSTS50058: Silent sign-in failed.
```

##### シナリオ 3: ダウンストリーム API 呼び出しエラー

**症状：** ダウンストリーム API を呼び出す HTTP 502 またはタイムアウト エラー

**詳細なログを有効にします。**

```json
{
  "Logging": {
    "LogLevel": {
      "Microsoft.Identity.Abstractions": "Debug",
      "System.Net.Http": "Information"
    }
  }
}
```

コントローラーにカスタム ログを追加して、ダウンストリーム API エラーをキャプチャします。

```csharp
[HttpGet]
public async Task<IActionResult> GetUserProfile()
{
    try
    {
        _logger.LogInformation("Acquiring token for Microsoft Graph");

        var user = await _downstreamApi.GetForUserAsync<User>(
            "MicrosoftGraph",
            options => options.RelativePath = "me");

        _logger.LogInformation(
            "Successfully retrieved user profile for {UserPrincipalName}",
            user.UserPrincipalName);

        return Ok(user);
    }
    catch (MsalUiRequiredException ex)
    {
        _logger.LogWarning(ex,
            "User interaction required. CorrelationId: {CorrelationId}",
            ex.CorrelationId);
        return Challenge();
    }
    catch (HttpRequestException ex)
    {
        _logger.LogError(ex, "Failed to call Microsoft Graph API");
        return StatusCode(502, "Downstream API error");
    }
}
```

#### ログ パターンを解釈する

次の例は、一般的な認証イベントの一般的なログ出力を示しています。

**成功した認証フロー:**

```
[Info] Authentication scheme OpenIdConnect: Authorization response received
[Debug] Correlation id: {guid}
[Info] Authorization code received
[Info] Token validated successfully
[Info] Authentication succeeded for user: {user}
```

**同意が必要:**

```
[Warning] Microsoft.Identity.Web: Incremental consent required
[Info] AADSTS65001: User consent is required for scopes: {scopes}
[Info] Redirecting to consent page
```

**トークンの更新:**

```
[Debug] Token expired, attempting silent token refresh
[Info] Token source: IdentityProvider
[Info] Token refreshed successfully
```

#### 外部プロバイダーを使用してログを集計する

監視とアラートのために、一元化されたログ プラットフォームに ID ログを転送します。

**Application Insights の統合:**

関連付け ID エンリッチメントを使用して Application Insights に ID テレメトリを送信します。

```csharp
using Microsoft.ApplicationInsights.Extensibility;

builder.Services.AddApplicationInsightsTelemetry();

// Enrich telemetry with correlation IDs
builder.Services.AddSingleton<ITelemetryInitializer, CorrelationIdTelemetryInitializer>();
```

**Serilog の統合:**

コンソールとローリング ファイルの出力に ID ログをキャプチャするように Serilog を構成します。

```csharp
using Serilog;

Log.Logger = new LoggerConfiguration()
    .MinimumLevel.Information()
    .MinimumLevel.Override("Microsoft.Identity", Serilog.Events.LogEventLevel.Debug)
    .Enrich.FromLogContext()
    .WriteTo.Console()
    .WriteTo.File("logs/identity-.txt", rollingInterval: RollingInterval.Day)
    .CreateLogger();

builder.Host.UseSerilog();
```

### ログのベスト プラクティスに従う

これらのプラクティスを適用して、ID ログをセキュリティで保護し、役立ち、パフォーマンスを維持します。

#### やるべきこと

**1. 構造化ログを使用する:**

ログ アグリゲーターがインデックスを作成してクエリできるように、値を名前付きパラメーターとして渡します。

```csharp
_logger.LogInformation(
    "Token acquired for user {UserId} with scopes {Scopes}",
    userId, string.Join(" ", scopes));
```

**2. ログ相関 ID:**

サポートの調査を簡略化するために、常にエラー ログに関連付け ID を含めます。

```csharp
_logger.LogError(ex,
    "Operation failed. CorrelationId: {CorrelationId}",
    ex.CorrelationId);
```

**3. 適切なログ レベルを使用します。**

ログ レベルを重大度と対象ユーザーと一致させます。

```csharp
_logger.LogDebug("Detailed diagnostic info");      // Development
_logger.LogInformation("Key application events");  // Selective production
_logger.LogWarning("Unexpected but handled");      // Production
_logger.LogError(ex, "Operation failed");          // Production
```

**4. 運用環境でログをサニタイズする:**

機密性の高い値を運用ログに書き込む前にマスクします。

```csharp
var sanitizedEmail = environment.IsProduction()
    ? MaskEmail(email)
    : email;
_logger.LogInformation("Processing request for {Email}", sanitizedEmail);
```

#### してはいけないこと

**1. 運用環境で PII を有効にしないでください。**

```csharp
//  Wrong
"EnablePiiLogging": true  // In production config!

//  Correct
"EnablePiiLogging": false
```

**2. シークレットをログに記録しない:**

```csharp
//  Wrong
_logger.LogInformation("Token: {Token}", accessToken);

//  Correct
_logger.LogInformation("Token acquired, expires: {ExpiresOn}", expiresOn);
```

**3. 運用環境では詳細ログを使用しないでください。**

```csharp
//  Wrong - production appsettings.json
"Microsoft.Identity": "Debug"

//  Correct
"Microsoft.Identity": "Warning"
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/advanced/multiple-auth-schemes"} -->
## Microsoftの複数の認証スキーム。Identity.Web

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/advanced/multiple-auth-schemes
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: Microsoft.Identity.Web を使用して、ASP.NET Core で複数の認証スキームを構成し、多数の ID プロバイダーを利用するシナリオに対応します。

Microsoft。Identity.Web では、1 つの ASP.NET Core アプリケーションで複数の認証スキームがサポートされています。 この方法は、アプリケーションがさまざまな種類の認証を同時に処理する場合に使用します。

既定では、ASP.NET Coreは 1 つの既定の認証スキームを使用します。 ただし、多くの実際のアプリケーションには複数のスキームが必要です。

| シナリオ | 関連するスキーム |
| --- | --- |
| API も公開する Web アプリ | OpenID Connect + JWT Bearer |
| 複数の ID プロバイダーからのトークンを受け入れる API | 複数の JWT ベアラー スキーム |
| ユーザー認証とサービス間認証の両方を使用する API | JWT ベアラー + API キー/証明書 |
| 移行のためのハイブリッド認証 | レガシ スキーム + 最新の OAuth |

### 認証スキームのフローについて

次の図は、ASP.NET Coreが要求を処理するときに認証スキームを解決する方法を示しています。

```mermaid
flowchart LR
    Request[Incoming Request] --> Middleware[Authentication Middleware]
    Middleware --> Default{Default Scheme?}
    Default -->|Yes| DefaultHandler[Default Handler]
    Default -->|No| Explicit{Explicit Scheme<br/>Specified?}
    Explicit -->|Yes| SpecificHandler[Specific Handler]
    Explicit -->|No| Error[401 Unauthorized]
```

### 一般的なシナリオを調べる

次のシナリオは、一般的なマルチスキーム構成を示しています。

#### シナリオ 1: 同じプロジェクト内の Web アプリと Web API

アプリケーションは、(Cookie/OpenID Connect を使用して) Web ページと API エンドポイント (JWT ベアラー トークンを使用) の両方を提供します。 次のコードは、両方のスキームを登録します。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authentication.OpenIdConnect;
using Microsoft.Identity.Web;

var builder = WebApplication.CreateBuilder(args);

// Add OpenID Connect for web app (browser sign-in)
builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"))
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddInMemoryTokenCaches();

// Add JWT Bearer for API endpoints
builder.Services.AddAuthentication()
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"), 
        JwtBearerDefaults.AuthenticationScheme);

builder.Services.AddControllersWithViews();

var app = builder.Build();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();
app.Run();
```

#### シナリオ 2: 複数の ID プロバイダー

Microsoft Entra IDと Azure AD B2C の両方からトークンを受け入れます。 次のコードは、プライマリスキームとセカンダリスキームを登録します。

```csharp
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    // Primary scheme: Microsoft Entra ID
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"), 
        JwtBearerDefaults.AuthenticationScheme)
    // Secondary scheme: Azure AD B2C
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAdB2C"), 
        "AzureAdB2C");
```

**appsettings.json:**

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "your-tenant-id",
    "ClientId": "your-api-client-id"
  },
  "AzureAdB2C": {
    "Instance": "https://your-tenant.b2clogin.com/",
    "Domain": "your-tenant.onmicrosoft.com",
    "TenantId": "your-b2c-tenant-id",
    "ClientId": "your-b2c-api-client-id",
    "SignUpSignInPolicyId": "B2C_1_SignUpSignIn"
  }
}
```

### 認証スキームを構成する

アプリケーションの起動時に認証スキームを登録して構成します。

#### 複数のスキームを登録する

次のコードは、既定のスキームを設定し、JWT Bearer と OpenID Connect の両方を登録します。

```csharp
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authentication.OpenIdConnect;
using Microsoft.Identity.Web;

var builder = WebApplication.CreateBuilder(args);

// Set the default authentication scheme (can also do with AddAuthentication(scheme))
builder.Services.AddAuthentication(options =>
{
    options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
})
// Add JWT Bearer (primary)
.AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"), 
    JwtBearerDefaults.AuthenticationScheme)
// Add OpenID Connect (secondary)
.AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"), 
    OpenIdConnectDefaults.AuthenticationScheme);
```

#### 名前付きスキームを定義する

スキームの名前付き定数を定義して、読みやすさを向上させ、入力ミスを防ぎます。

```csharp
public static class AuthSchemes
{
    public const string AzureAd = "AzureAd";
    public const string AzureAdB2C = "AzureAdB2C";
}

builder.Services.AddAuthentication(AuthSchemes.AzureAd)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"), AuthSchemes.AzureAd)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAdB2C"), AuthSchemes.AzureAdB2C);
```

### コントローラーでスキームを指定する

`[Authorize]`属性をコントローラーとアクションに適用して、各要求を処理する認証スキームを制御します。

#### [Authorize] 属性を使用する

コントローラーまたはアクションに使用する認証スキームを指定します。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

// Use default scheme
[Authorize]
[ApiController]
[Route("api/[controller]")]
public class DefaultController : ControllerBase
{
    [HttpGet]
    public IActionResult Get() => Ok("Using default scheme");
}

// Use specific scheme
[Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
[ApiController]
[Route("api/[controller]")]
public class ApiController : ControllerBase
{
    [HttpGet]
    public IActionResult Get() => Ok("Using JWT Bearer scheme");
}

// Accept multiple schemes (any one succeeds)
[Authorize(AuthenticationSchemes = "AzureAd,AzureAdB2C")]
[ApiController]
[Route("api/[controller]")]
public class MultiSchemeController : ControllerBase
{
    [HttpGet]
    public IActionResult Get() => Ok($"Authenticated via: {User.Identity?.AuthenticationType}");
}
```

#### アクションごとのスキームの選択

同じコントローラー内の個々のアクションに異なるスキームを適用します。

```csharp
[ApiController]
[Route("api/[controller]")]
public class MixedController : ControllerBase
{
    // This action uses JWT Bearer
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    [HttpGet("api-data")]
    public IActionResult GetApiData() => Ok("API data");

    // This action uses OpenID Connect (for browser-based calls)
    [Authorize(AuthenticationSchemes = OpenIdConnectDefaults.AuthenticationScheme)]
    [HttpGet("web-data")]
    public IActionResult GetWebData() => Ok("Web data");

    // This action accepts either scheme
    [Authorize(AuthenticationSchemes = $"{JwtBearerDefaults.AuthenticationScheme},{OpenIdConnectDefaults.AuthenticationScheme}")]
    [HttpGet("any-data")]
    public IActionResult GetAnyData() => Ok("Data for any authenticated user");
}
```

### API を呼び出すときにスキームを指定する

アプリケーションで複数の認証スキームを使用し、ダウンストリーム API を呼び出す場合は、トークンの取得に使用するスキームを指定します。

#### Microsoft Graph を呼び出す

次のコードは、Microsoft Graphを呼び出すときの認証スキームを指定します。

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Graph;

[Authorize]
public class GraphController : ControllerBase
{
    private readonly GraphServiceClient _graphClient;

    public GraphController(GraphServiceClient graphClient)
    {
        _graphClient = graphClient;
    }

    [HttpGet("profile")]
    public async Task<ActionResult> GetProfile()
    {
        // Specify which authentication scheme to use for token acquisition
        var user = await _graphClient.Me
            .GetAsync(r => r.Options
                .WithAuthenticationScheme(JwtBearerDefaults.AuthenticationScheme));

        return Ok(user);
    }

    [HttpGet("mail")]
    public async Task<ActionResult> GetMail()
    {
        // More detailed options including scopes and scheme
        var messages = await _graphClient.Me.Messages
            .GetAsync(r =>
            {
                r.Options.WithAuthenticationOptions(options =>
                {
                    // Specify authentication scheme
                    options.AcquireTokenOptions.AuthenticationOptionsName = 
                        JwtBearerDefaults.AuthenticationScheme;
                    
                    // Specify additional scopes if needed
                    options.Scopes = new[] { "Mail.Read" };
                });
            });

        return Ok(messages);
    }
}
```

#### IDownstreamApi を使用してダウンストリーム API を呼び出す

次のコードは、ダウンストリーム API を呼び出すときの認証スキームを指定します。

```csharp
using Microsoft.Identity.Abstractions;

[Authorize]
public class DownstreamController : ControllerBase
{
    private readonly IDownstreamApi _downstreamApi;

    public DownstreamController(IDownstreamApi downstreamApi)
    {
        _downstreamApi = downstreamApi;
    }

    [HttpGet("data")]
    public async Task<ActionResult> GetData()
    {
        var result = await _downstreamApi.CallApiForUserAsync<MyData>(
            "MyApi",
            options =>
            {
                options.AcquireTokenOptions.AuthenticationOptionsName = 
                    JwtBearerDefaults.AuthenticationScheme;
            });

        return Ok(result);
    }
}
```

### 一般的な問題のトラブルシューティング

#### 問題: 選択されているスキームが正しくありません

**症状：**

- 401 未承認のエラー
- 間違ったアクセス許可で取得されたトークン
- ユーザーの主張が見当たらないか、間違っている

**ソリューション：** コントローラー属性とトークン取得呼び出しの両方で認証スキームを明示的に指定します。

```csharp
// In controller
[Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
public class MyApiController : ControllerBase { }

// When calling APIs
var user = await _graphClient.Me
    .GetAsync(r => r.Options.WithAuthenticationScheme(JwtBearerDefaults.AuthenticationScheme));
```

#### 問題: 複数のスキームが競合する

**症状：**

- 認証は 1 つのスキームに対して機能しますが、別のスキームでは機能しません
- 予期しないリダイレクトまたはチャレンジ

**ソリューション：** 認証構成で既定のスキームを明示的に設定します。

```csharp
builder.Services.AddAuthentication(options =>
{
    // Default for API calls
    options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
    
    // Default for unauthenticated challenges (redirects)
    options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
});
```

#### 問題: トークン キャッシュの競合

**症状：**

- 間違ったスキームのためにキャッシュされたトークン
- ユーザー コンテキストが正しくありません

**ソリューション：** スキーム対応のトークン取得を使用して、正しいキャッシュが使用されていることを確認します。

```csharp
// Specify the authentication scheme when acquiring tokens
var accessToken = await _tokenAcquisition.GetAccessTokenForUserAsync(
    scopes,
    authenticationScheme: "AzureAdB2C");
```

#### 問題: 承認ポリシーが機能しない

**症状：**

- ポリシー要件が適用されない
- クレームが見つかりません

**ソリューション：** ポリシーが正しいスキームを参照していることを確認します。

```csharp
builder.Services.AddAuthorization(options =>
{
    options.AddPolicy("ApiPolicy", policy =>
    {
        policy.AuthenticationSchemes.Add(JwtBearerDefaults.AuthenticationScheme);
        policy.RequireAuthenticatedUser();
        policy.RequireClaim("scope", "access_as_user");
    });
});
```

### ベスト プラクティスに従う

これらのプラクティスを適用して、マルチスキーム構成を保守可能な状態に保ちます。

#### 1. スキーム名に定数を使用する

スキーマ名を定数として定義して、入力ミスを回避し、リファクタリングを改善します。 使用可能な場合 `SchemeDefaults.AuthenticationScheme` 使用するか、静的クラスを定義します。

```csharp
public static class AuthSchemes
{
    public const string Primary = JwtBearerDefaults.AuthenticationScheme;
    public const string B2C = "AzureAdB2C";
    public const string Internal = "InternalApi";
}

// Usage
[Authorize(AuthenticationSchemes = AuthSchemes.Primary)]
public class MyController : ControllerBase { }
```

#### 2. スキーム構成を文書化する

他の開発者が構成されているスキームとその目的を理解できるように、認証セットアップに XML ドキュメントを追加します。

```csharp
/// <summary>
/// Configures authentication for the application.
/// 
/// Schemes configured:
/// - JwtBearer (default): For API clients using Microsoft Entra tokens
/// - AzureAdB2C: For consumer-facing API clients using B2C tokens
/// - OpenIdConnect: For browser-based authentication (web app)
/// </summary>
public static IServiceCollection AddApplicationAuthentication(
    this IServiceCollection services, 
    IConfiguration configuration)
{
    // Implementation...
}
```

#### 3. 各スキームを個別にテストする

各スキームが正しく動作することを確認する統合テストを作成します。 次のテストでは、JWT ベアラーと B2C トークン認証を個別に検証します。

```csharp
[Fact]
public async Task Api_WithJwtBearerToken_ReturnsSuccess()
{
    var token = await GetJwtBearerTokenAsync();
    _client.DefaultRequestHeaders.Authorization = 
        new AuthenticationHeaderValue("Bearer", token);
    
    var response = await _client.GetAsync("/api/data");
    
    Assert.Equal(HttpStatusCode.OK, response.StatusCode);
}

[Fact]
public async Task Api_WithB2CToken_ReturnsSuccess()
{
    var token = await GetB2CTokenAsync();
    _client.DefaultRequestHeaders.Authorization = 
        new AuthenticationHeaderValue("Bearer", token);
    
    var response = await _client.GetAsync("/api/data");
    
    Assert.Equal(HttpStatusCode.OK, response.StatusCode);
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/advanced/web-apps-behind-proxies"} -->
## プロキシとゲートウェイの背後に Web アプリをデプロイする

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/advanced/web-apps-behind-proxies
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: Microsoft.Identity.Web を使用して、ASP.NET Core Web アプリをリバース プロキシ、ロード バランサー、Azure ゲートウェイの背後にデプロイし、正しいリダイレクト URI 処理を行います。

ASP.NET Core WebアプリをMicrosoft.Identity.Webを使用してリバースプロキシ、ロードバランサー、またはAzureゲートウェイの背後にデプロイするとき、認証コールバックを成功させるには、**redirect URI**を適切に処理する必要があります。

リダイレクト URI は、次の理由でプロキシ シナリオで複雑になります。

- **Microsoft Entra IDは、サインイン後にユーザー**を構成済みのリダイレクト URI にリダイレクトします
- **プロキシは要求コンテキストを変更します** -スキーム (HTTP/HTTPS)、ホスト、ポート、パス
- **Redirect URI は、Microsoft Entra IDに登録されているもの**正確に一致する必要があります
- **CallbackPath** はプロキシ経由で動作する必要があります

### 一般的なプロキシ シナリオを特定する

次のシナリオは、さまざまなプロキシ アーキテクチャがリダイレクト URI の構築にどのように影響するかを示しています。

#### Azure Application Gateway

**ユース ケース:** リージョン負荷分散、WAF、SSL 終了

**リダイレクト URI への影響:**

- ゲートウェイ URL: `https://gateway.contoso.com/myapp`
- バックエンド URL: `http://backend.internal/`
- Microsoft Entra ID リダイレクト: `https://gateway.contoso.com/myapp/signin-oidc`

#### Azure Front Door

**ユース ケース:** グローバル分散、CDN、複数のリージョン

**リダイレクト URI への影響:**

- フロントドア URL: `https://myapp.azurefd.net`
- バックエンド URL: `https://app-eastus.azurewebsites.net`、 `https://app-westus.azurewebsites.net`
- Microsoft Entra ID リダイレクト: `https://myapp.azurefd.net/signin-oidc`

#### オンプレミスのリバース プロキシ

**ユース ケース:** 企業ネットワーク、既存のインフラストラクチャ

**リダイレクト URI への影響:**

- プロキシ URL: `https://apps.corp.com/myapp`
- バックエンド URL: `http://appserver:5000/`
- Microsoft Entra ID リダイレクト: `https://apps.corp.com/myapp/signin-oidc`

#### Kubernetes イングレス

**ユース ケース:** コンテナー オーケストレーション、マイクロサービス

**リダイレクト URI への影響:**

- イングレス URL: `https://apps.k8s.com/webapp`
- サービス URL: `http://webapp-service.default.svc.cluster.local`
- Microsoft Entra ID リダイレクト: `https://apps.k8s.com/webapp/signin-oidc`

### 転送されたヘッダーを構成する

転送されたヘッダーは、プロキシのデプロイに不可欠です。 これを使用しないと、バックエンド アプリによって正しくないリダイレクト URI が作成されます。

#### 転送されたヘッダーが重要な理由

Web アプリ **には、次の適切な要求コンテキストが必要** です。

1. Microsoft Entra IDの絶対リダイレクト URI を構築する
2. 受信認証応答を検証する
3. 正しいサインアウト URI を生成する
4. HTTPS 要件の適用を処理する

**転送ヘッダー用のミドルウェアが使用されていない場合:**

```
User visits: https://gateway.contoso.com/myapp
Backend sees: http://localhost:5000/
Redirect URI built: http://localhost:5000/signin-oidc  Wrong!
Microsoft Entra ID redirects to: https://gateway.contoso.com/myapp/signin-oidc
Backend doesn't recognize it: Error!
```

**転送されたヘッダー ミドルウェアの場合:**

```
User visits: https://gateway.contoso.com/myapp
Backend sees forwarded headers: X-Forwarded-Proto: https, X-Forwarded-Host: gateway.contoso.com
Redirect URI built: https://gateway.contoso.com/myapp/signin-oidc  Correct!
Microsoft Entra ID redirects to: https://gateway.contoso.com/myapp/signin-oidc
Backend recognizes it: Success!
```

#### 基本的な転送ヘッダーを設定する

認証の前に、 `Program.cs` で転送されたヘッダー ミドルウェアを構成します。

```csharp
using Microsoft.AspNetCore.Authentication.OpenIdConnect;
using Microsoft.AspNetCore.HttpOverrides;
using Microsoft.Identity.Web;

var builder = WebApplication.CreateBuilder(args);

// CRITICAL: Configure forwarded headers BEFORE authentication
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;

    // Accept headers from any source (proxy/gateway)
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();

    // Standard header names
    options.ForwardedForHeaderName = "X-Forwarded-For";
    options.ForwardedProtoHeaderName = "X-Forwarded-Proto";
    options.ForwardedHostHeaderName = "X-Forwarded-Host";
});

// Authentication
builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"));

builder.Services.AddRazorPages();

var app = builder.Build();

// CRITICAL: Use forwarded headers BEFORE authentication
app.UseForwardedHeaders();

// Only enforce HTTPS if you're sure the proxy forwards the scheme correctly
if (!app.Environment.IsDevelopment())
{
    app.UseHttpsRedirection();
}

app.UseStaticFiles();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapRazorPages();

app.Run();
```

`appsettings.json`に認証設定を追加します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "your-tenant-id",
    "ClientId": "your-client-id",
    "ClientSecret": "your-client-secret",
    "CallbackPath": "/signin-oidc"
  }
}
```

Microsoft Entra アプリの登録で、ゲートウェイ URL をリダイレクト URI として登録します。

```
https://gateway.contoso.com/myapp/signin-oidc
```

### パスベースのルーティングを処理する

プロキシが要求にパス プレフィックスを追加する場合は、パスベースのルーティングを使用します。

#### 問題: プロキシがパス プレフィックスを追加する

**シナリオ:**

- プロキシ URL: `https://apps.contoso.com/webapp1`
- バックエンド URL: `http://backend:5000/`
- バックエンドは `/` についてのみ認識しており、`/webapp1` については認識していません。

#### 解決策 1: PathBase を使用する (推奨)

`UsePathBase`を設定して、そのパス プレフィックスについてアプリに通知します。

```csharp
var app = builder.Build();

// Tell the app it's hosted at a path prefix
app.UsePathBase("/webapp1");

app.UseForwardedHeaders();
app.UseStaticFiles();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapRazorPages();

app.Run();
```

**しくみ**:

- `HttpContext.Request.Path` は、ルーティングの `/webapp1` プレフィックスを削除します
- `HttpContext.Request.PathBase` は`/webapp1`を含む
- リンクの生成にパス ベースが自動的に含まれる
- リダイレクト URI にパス ベースが自動的に含まれる

Microsoft Entra アプリ登録時に完全なパスを登録します。

```
https://apps.contoso.com/webapp1/signin-oidc
```

#### 解決策 2: プロキシがパスを書き換える

一部のプロキシでは、転送前にパス プレフィックスが削除されます。 パスを書き換えて元のヘッダーを転送するようにプロキシを構成します。

**NGINX の構成:**

```nginx
location /webapp1/ {
    proxy_pass http://backend:5000/;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_set_header X-Forwarded-Host $host;
    proxy_set_header X-Original-URL $request_uri;
}
```

プロキシがプレフィックスを削除する場合、アプリケーションで `PathBase` は必要ありません。

```csharp
// No PathBase needed if proxy strips the prefix
app.UseForwardedHeaders();
```

Microsoft Entra アプリの登録にプロキシに接続する URL を登録します。

```
https://apps.contoso.com/webapp1/signin-oidc
```

#### ソリューション 3: 動的 PathBase のカスタム ミドルウェア

パス ベースが環境によって異なる場合は、構成から読み取るか、要求ヘッダーから検出します。

```csharp
// Read path base from configuration or headers
var pathBase = builder.Configuration["PathBase"];
if (!string.IsNullOrEmpty(pathBase))
{
    app.UsePathBase(pathBase);
}

// Or detect from X-Forwarded-Prefix header
app.Use((context, next) =>
{
    var forwardedPrefix = context.Request.Headers["X-Forwarded-Prefix"].ToString();
    if (!string.IsNullOrEmpty(forwardedPrefix))
    {
        context.Request.PathBase = forwardedPrefix;
    }
    return next();
});

app.UseForwardedHeaders();
```

### SSL/TLS 終了の処理

プロキシが SSL/TLS を終了すると、ヘッダー転送を構成しない限り、バックエンドは HTTP 要求を受信し、正しくないリダイレクト URI を作成します。

#### 問題: プロキシが HTTPS を終了する

**シナリオ:**

- ユーザーが HTTPS 経由でプロキシに接続する
- プロキシが HTTP 経由でバックエンドに接続する
- バックエンドが HTTP リダイレクト URI をビルドする (間違っています!)

#### 解決策: X-Forwarded-Proto ヘッダー

バックエンドが元のスキームを認識できるように、 `X-Forwarded-Proto` ヘッダーを送信するようにプロキシを構成します。

**プロキシ構成 (NGINX):**

```nginx
location / {
    proxy_pass http://backend:5000;
    proxy_set_header X-Forwarded-Proto $scheme;  # Critical!
    proxy_set_header X-Forwarded-Host $host;
}
```

転送された proto ヘッダーを読み取り、要求スキームを設定するようにアプリを構成します。

```csharp
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});

var app = builder.Build();

app.UseForwardedHeaders(); // Reads X-Forwarded-Proto and sets Request.Scheme = "https"

// HTTPS redirection becomes safe
app.UseHttpsRedirection(); // Won't create infinite redirect loop

app.UseAuthentication();
```

#### 一般的な間違い: HTTPS リダイレクト ループ

**問題:**

```csharp
// Without UseForwardedHeaders()
app.UseHttpsRedirection(); // Sees Request.Scheme = "http", redirects to HTTPS
// User gets infinite redirect loop!
```

**Solution:**

```csharp
// WITH UseForwardedHeaders()
app.UseForwardedHeaders(); // Sets Request.Scheme = "https" from X-Forwarded-Proto
app.UseHttpsRedirection(); // Sees HTTPS, no redirect needed 
```

### カスタム ドメインを構成する

Azure Front Door付きのカスタム ドメインを使用して、バックエンド サービスへのトラフィックのルーティング中にブランド化された URL をユーザーに提示します。

#### シナリオ: Azure Front Doorを介したカスタム ドメイン

**アーキテクチャ**:

- カスタム ドメイン: `https://myapp.contoso.com`
- Front Door: `https://myapp.azurefd.net` (バックエンドのオリジン)
- Azure Web アプリ: `https://myapp-backend.azurewebsites.net`

**Front Door の構成:**

1. Front Door にカスタム ドメイン `myapp.contoso.com` を追加する
2. SSL 証明書の構成 (Front Door マネージドまたはカスタム)
3. バックエンド プールを `myapp-backend.azurewebsites.net`
4. HTTPS のみを有効にする

Front Door から転送されたヘッダーを受け入れるようにアプリを構成します。

```csharp
// No special configuration needed if headers are forwarded correctly
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});
```

Microsoft Entra アプリの登録に、サインインとサインアウトの両方のコールバック URI を登録します。

```
https://myapp.contoso.com/signin-oidc
https://myapp.contoso.com/signout-callback-oidc
```

次の診断エンドポイントを使用して、アプリによって生成されるリダイレクト URI を確認します。

```csharp
// In a controller or page
public IActionResult TestRedirectUri()
{
    var request = HttpContext.Request;
    var scheme = request.Scheme; // Should be "https"
    var host = request.Host.Value; // Should be "myapp.contoso.com"
    var pathBase = request.PathBase.Value; // Should be "" or your path base
    var path = "/signin-oidc";

    var redirectUri = $"{scheme}://{host}{pathBase}{path}";
    // Expected: https://myapp.contoso.com/signin-oidc

    return Content($"Redirect URI would be: {redirectUri}");
}
```

### 複数のリダイレクト URI を登録する

アプリが複数の環境で異なるゲートウェイの背後で実行されている場合は、それぞれにリダイレクト URI を登録します。

#### 問題: 同じアプリ、複数のゲートウェイ

**シナリオ:**

- 生産: `https://app.contoso.com` (フロントドア)
- ステージング: `https://app-staging.azurewebsites.net` (ダイレクト)
- 開発: `https://localhost:5001` (ローカル)

#### 解決策: すべてのリダイレクト URI を登録する

すべての環境固有のリダイレクト URI を、Microsoft Entra アプリの登録に追加します。

```
https://app.contoso.com/signin-oidc
https://app-staging.azurewebsites.net/signin-oidc
https://localhost:5001/signin-oidc
```

アプリケーション コードでは、環境固有の変更は必要ありません。

```csharp
var app = builder.Build();

app.UseForwardedHeaders(); // Handles proxy scenarios
app.UseAuthentication(); // Builds correct redirect URI based on request context
```

**しくみ**:

- アプリケーションが受信要求に基づいてリダイレクト URI を動的に構築する
- `HttpContext.Request.Scheme`、`Host`、および URI を決定`PathBase`
- Microsoft Entra IDに登録されている限り、認証は成功します

### Azure Application Gateway を構成する

このセクションでは、パスベースのルーティングを使用してAzure Application Gatewayの背後に Web アプリをデプロイするための完全な構成例を示します。

#### パスベースのルーティングを使用した完全な例

**Application Gateway の設定:**

**バックエンド プール:**

- ターゲット: `backend.azurewebsites.net` または IP アドレス

**HTTP 設定:**

- プロトコル: HTTPS (推奨) または HTTP
- ポート: 443 または 80
- バックエンド パスをオーバーライドする: いいえ
- カスタム プローブ: はい

**ヘルスプローブ:**

- プロトコル: HTTPS または HTTP
- ホスト: 空白のままにします (バックエンド プールのホスト名を使用)
- パス: `/health` (匿名エンドポイントである必要があります)
- 間隔: 30 秒

**ルーティング規則:**

- 名前: `webapp-rule`
- リスナー: ポート 443 の HTTPS リスナー
- バックエンド プール: あなたのバックエンド プール
- HTTP 設定: あなたのHTTP設定

転送されたヘッダー、認証、正常性プローブを処理するようにアプリケーションを構成します。

```csharp
using Microsoft.AspNetCore.HttpOverrides;

var builder = WebApplication.CreateBuilder(args);

// Forwarded headers for Application Gateway
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});

// Authentication
builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"));

// Health checks (for Application Gateway probe)
builder.Services.AddHealthChecks();

builder.Services.AddRazorPages();

var app = builder.Build();

// Health endpoint BEFORE authentication (critical for gateway probes)
app.MapHealthChecks("/health").AllowAnonymous();

// Middleware order
app.UseForwardedHeaders();
app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapRazorPages();

app.Run();
```

`appsettings.json`に認証設定を追加します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "your-tenant-id",
    "ClientId": "your-client-id",
    "ClientSecret": "your-client-secret",
    "CallbackPath": "/signin-oidc"
  }
}
```

Microsoft Entra アプリの登録にゲートウェイに接続するリダイレクト URI を登録します。

```
https://gateway.contoso.com/signin-oidc
https://gateway.contoso.com/signout-callback-oidc
```

### Azure Front Doorの構成

このセクションでは、一貫した認証を使用して、Azure Front Doorの背後に複数リージョンの Web アプリをデプロイする方法について説明します。

#### 複数リージョンの Web アプリのデプロイ

**シナリオ:**

- Front Door: `https://app.azurefd.net` (グローバル エンドポイント)
- 米国東部: `https://app-eastus.azurewebsites.net`
- 米国西部: `https://app-westus.azurewebsites.net`
- 最も近いリージョンにルーティングされたユーザー

**Front Door の構成:**

**配信元グループ:**

- 名前: `webapp-origins`
- ヘルスプローブ: `/health`
- 負荷分散: レイテンシーベース

**起源：**

1. `app-eastus.azurewebsites.net` (優先順位 1)
2. `app-westus.azurewebsites.net` (優先順位 1)

**ルート：**

- パス: `/*`
- 転送プロトコル: HTTPS のみ
- 配信元グループ: `webapp-origins`

同じアプリケーション コードを両方のリージョンにデプロイします。

```csharp
using Microsoft.AspNetCore.HttpOverrides;

var builder = WebApplication.CreateBuilder(args);

// Forwarded headers for Front Door
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});

builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"));

builder.Services.AddHealthChecks();
builder.Services.AddRazorPages();

var app = builder.Build();

// Health check for Front Door probe
app.MapHealthChecks("/health").AllowAnonymous();

app.UseForwardedHeaders();
app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapRazorPages();

app.Run();
```

どちらのリージョンも、**同じ Microsoft Entra アプリ登録**を**同じリダイレクト URI**と共有します。

```
https://app.azurefd.net/signin-oidc
https://app.azurefd.net/signout-callback-oidc
```

**動作する理由:**

- Front Door URL はリージョン間で一貫しています
- 転送されたヘッダーにより、バックエンドが正しいリダイレクト URI をビルドすることを確認する
- トークンの取得はリージョン バックエンドで行われます
- 分散トークン キャッシュ (Redis) は、リージョン間でトークンを共有します

### 一般的な問題のトラブルシューティング

このセクションでは、プロキシデプロイでの最も一般的な認証エラーとその解決方法について説明します。

#### 問題: "リダイレクト URI の不一致" エラー

**症状：**

```
AADSTS50011: The redirect URI 'http://localhost:5000/signin-oidc'
specified in the request does not match the redirect URIs configured
for the application 'your-app-id'.
```

**考えられる原因**:

1. **転送されたヘッダー ミドルウェアが見つかりません**

    ```csharp
    // Fix: Add BEFORE authentication
    app.UseForwardedHeaders();
    app.UseAuthentication();
    ```
2. **Microsoft Entra ID に誤って登録されたリダイレクト URI**

    - Azure ポータルで登録済みのURIを確認する。
    - 運用環境で HTTPS (HTTP ではなく) を確認する
    - ホストが一致していることを確認する (標準以外の場合はポートを含む)
    - パスに PathBase が含まれていることを確認します (該当する場合)
3. **プロキシがヘッダーを転送しない**

    - プロキシの構成を確認する
    - `X-Forwarded-Proto`、`X-Forwarded-Host`が設定されていることを確認する
    - curl を使用してテストする: `curl -H "X-Forwarded-Proto: https" -H "X-Forwarded-Host: gateway.com" http://backend:5000/`
4. **PathBase が構成されていません**

    ```csharp
    // If proxy adds /myapp prefix, add this:
    app.UsePathBase("/myapp");
    ```

次の診断ミドルウェアを追加して、アプリがビルドするリダイレクト URI をログに記録します。

```csharp
// Add this middleware to log the redirect URI being built
app.Use(async (context, next) =>
{
    var logger = context.RequestServices.GetRequiredService<ILogger<Program>>();
    logger.LogInformation(
        "Request: Scheme={Scheme}, Host={Host}, PathBase={PathBase}, Path={Path}",
        context.Request.Scheme,
        context.Request.Host,
        context.Request.PathBase,
        context.Request.Path);

    await next();
});
```

#### 問題: 認証はローカルで機能しますが、プロキシの背後には機能しません

**症状：**

- サインインは`localhost:5001`で動作します。
- サインインが失敗する `gateway.contoso.com`
- エラー: リダイレクト URI の不一致または関連付けの失敗

**ソリューションのチェックリスト:**

1. **転送されたヘッダーを最初に構成して使用する**

```csharp
app.UseForwardedHeaders(); // Must be first!
```

1. **プロキシで必要なヘッダーが転送される**

- `X-Forwarded-Proto: https`
- `X-Forwarded-Host: gateway.contoso.com`
- 省略可能: パス ベースの`X-Forwarded-Prefix`

1. **Microsoft Entra ID に登録されたリダイレクトURI**

- `https://gateway.contoso.com/signin-oidc`

1. **必要に応じて PathBase を構成する**

```csharp
app.UsePathBase("/myapp"); // If proxy adds prefix
```

1. **HTTPS が正しく適用される**

```csharp
app.UseForwardedHeaders(); // Reads X-Forwarded-Proto first
app.UseHttpsRedirection(); // Then enforces HTTPS
```

#### 問題: Sign-Out が失敗するか、間違った URL にリダイレクトされる

**症状：**

- サインインが正常に動作します
- サインアウトにより、間違った URL (localhost、http://、間違ったホスト) にリダイレクトされる

転送された要求コンテキストを使用して `PostLogoutRedirectUri` を設定します。

```csharp
// Ensure PostLogoutRedirectUri uses correct base URL
builder.Services.Configure<OpenIdConnectOptions>(
    OpenIdConnectDefaults.AuthenticationScheme,
    options =>
    {
        options.Events.OnRedirectToIdentityProviderForSignOut = context =>
        {
            // Build correct post-logout redirect URI
            var request = context.HttpContext.Request;
            var postLogoutUri = $"{request.Scheme}://{request.Host}{request.PathBase}/signout-callback-oidc";

            context.ProtocolMessage.PostLogoutRedirectUri = postLogoutUri;
            return Task.CompletedTask;
        };
    });
```

Microsoft Entra アプリの登録にサインアウト コールバック URI を登録します。

```
https://gateway.contoso.com/signout-callback-oidc
```

#### 問題: 無限リダイレクト ループ

**症状：**

- ブラウザーがアプリとMicrosoft Entra IDの間でリダイレクトを続ける
- ログインが完了しない

**考えられる原因**:

1. **転送されたヘッダーの前の HTTPS リダイレクト**

    ```csharp
    // WRONG ORDER:
    app.UseHttpsRedirection(); // Sees HTTP, redirects to HTTPS
    app.UseForwardedHeaders(); // Too late!
    
    // CORRECT ORDER:
    app.UseForwardedHeaders(); // Sets scheme to HTTPS
    app.UseHttpsRedirection(); // Sees HTTPS, no redirect
    ```
2. **プロキシと互換性のない Cookie 設定**

    ```csharp
    builder.Services.Configure<CookiePolicyOptions>(options =>
    {
        options.MinimumSameSitePolicy = SameSiteMode.None; // For cross-site scenarios
        options.Secure = CookieSecurePolicy.Always; // Requires HTTPS
    });
    ```
3. **Cookie ドメインの不一致**

    ```csharp
    // If subdomain issues, may need to set cookie domain
    builder.Services.ConfigureApplicationCookie(options =>
    {
        options.Cookie.Domain = ".contoso.com"; // Allows cookies across subdomains
    });
    ```

### ベスト プラクティスに従う

一般的な落とし穴を避けるために、これらのプラクティスをすべてのプロキシデプロイに適用します。

#### 1. 常に転送ヘッダー ミドルウェアを使用する

```csharp
// For ANY deployment behind proxy/gateway/load balancer
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});

var app = builder.Build();
app.UseForwardedHeaders(); // FIRST middleware!
```

#### 2. すべてのリダイレクト URI を登録する

Microsoft Entra アプリの登録ですべての環境のリダイレクト URI を登録します。

```text
Production:  https://app.contoso.com/signin-oidc
Staging:     https://app-staging.azurewebsites.net/signin-oidc
Development: https://localhost:5001/signin-oidc
```

#### 3. リダイレクト URI の生成をテストする

開発専用診断エンドポイントを追加して、リダイレクト URI を確認します。

```csharp
// Add diagnostics endpoint (development only!)
if (app.Environment.IsDevelopment())
{
    app.MapGet("/debug/redirect-uri", (HttpContext context) =>
    {
        var redirectUri = $"{context.Request.Scheme}://{context.Request.Host}{context.Request.PathBase}/signin-oidc";
        return Results.Ok(new { redirectUri });
    }).AllowAnonymous();
}
```

#### 4. ゲートウェイ プローブの正常性エンドポイント

ゲートウェイ プローブがサインインを必要としないように、認証ミドルウェアの前に正常性エンドポイントをマップします。

```csharp
// Must be BEFORE authentication middleware
app.MapHealthChecks("/health").AllowAnonymous();

app.UseAuthentication(); // Health endpoint bypasses this
```

#### 5. マルチリージョンの分散トークン キャッシュ

Redis などの分散キャッシュを使用して、リージョン間でトークンを共有します。

```csharp
// Use Redis for token cache across regions
builder.Services.AddStackExchangeRedisCache(options =>
{
    options.Configuration = builder.Configuration["Redis:ConnectionString"];
    options.InstanceName = "TokenCache_";
});

builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"))
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddDistributedTokenCaches();
```

#### 6. トラブルシューティング用のログ記録を構成する

Microsoft.Identity.Webのデバッグ ログおよび転送されたヘッダーを有効にして問題を診断します。

```csharp
builder.Logging.AddConfiguration(builder.Configuration.GetSection("Logging"));

// In appsettings.json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.AspNetCore": "Warning",
      "Microsoft.Identity.Web": "Debug",
      "Microsoft.AspNetCore.HttpOverrides": "Debug"
    }
  }
}
```

### 完全な例を確認する

このエンド ツー エンドの例では、Microsoft Graph統合を使用して、Azure Application Gatewayの背後に Web アプリをデプロイします。

#### アプリケーション コード

次の `Program.cs` では、転送されたヘッダー、Microsoft Graphによる認証、正常性チェックが構成されます。

```csharp
using Microsoft.AspNetCore.Authentication.OpenIdConnect;
using Microsoft.AspNetCore.HttpOverrides;
using Microsoft.Identity.Web;
using Microsoft.Identity.Web.UI;

var builder = WebApplication.CreateBuilder(args);

// Forwarded headers for Application Gateway
builder.Services.Configure<ForwardedHeadersOptions>(options =>
{
    options.ForwardedHeaders = ForwardedHeaders.XForwardedFor |
                                ForwardedHeaders.XForwardedProto |
                                ForwardedHeaders.XForwardedHost;
    options.KnownNetworks.Clear();
    options.KnownProxies.Clear();
});

// Authentication
builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"))
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddMicrosoftGraph()
    .AddInMemoryTokenCaches();

// Health checks
builder.Services.AddHealthChecks();

// Add Microsoft Identity UI for sign-in/sign-out
builder.Services.AddRazorPages()
    .AddMicrosoftIdentityUI();

var app = builder.Build();

// Health endpoint (before authentication)
app.MapHealthChecks("/health").AllowAnonymous();

// Middleware order is critical
app.UseForwardedHeaders();

if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapRazorPages();
app.MapControllers();

app.Run();
```

認証とログ記録の構成を `appsettings.json`に追加します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "your-tenant-id",
    "ClientId": "your-client-id",
    "ClientSecret": "your-client-secret",
    "CallbackPath": "/signin-oidc",
    "SignedOutCallbackPath": "/signout-callback-oidc"
  },
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.AspNetCore": "Warning",
      "Microsoft.Identity.Web": "Information",
      "Microsoft.AspNetCore.HttpOverrides": "Debug"
    }
  }
}
```

Microsoft Entra アプリの登録に次の URI を登録します。

**リダイレクト URI:**

```
https://gateway.contoso.com/signin-oidc
https://gateway.contoso.com/signout-callback-oidc
```

**フロント チャネル ログアウト URL:**

```
https://gateway.contoso.com/signout-oidc
```

#### Application Gateway の構成

次の Application Gateway リソースを構成します。

**バックエンド プール:**

- 名前: `webapp-backend`
- ターゲット: `webapp.azurewebsites.net` または IP アドレス

**HTTP 設定:**

- 名前: `webapp-https-settings`
- プロトコル:HTTPS
- ポート: 443
- バックエンド パスをオーバーライドする: いいえ
- バックエンド ターゲットからホスト名を選択する: はい
- カスタム プローブ: はい → `webapp-health-probe`

**ヘルスプローブ:**

- 名前: `webapp-health-probe`
- プロトコル:HTTPS
- バックエンド HTTP 設定からホスト名を選択する: はい
- パス: `/health`
- 間隔: 30 秒
- 異常しきい値: 3

**リスナー:**

- 名前: `webapp-listener`
- フロントエンド IP: パブリック
- プロトコル:HTTPS
- ポート: 443
- SSL 証明書: あなたの証明書

**ルーティング規則:**

- 名前: `webapp-rule`
- ルールの種類: 基本
- リスナー： `webapp-listener`
- バックエンド ターゲット: `webapp-backend`
- HTTP 設定: `webapp-https-settings`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/agent-id-sdk/agent-identities"} -->
## エージェント ID: 自律パターンと対話型エージェント

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/agent-identities
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: 自律的および対話型の認証シナリオで Microsoft Entra ID Auth SDK (サイドカー) でエージェント ID を使用する方法について説明します。

エージェント ID を使用すると、エージェント アプリケーションが自律的に、またはユーザーに代わって動作する高度な認証シナリオが可能になります。 Microsoft Entra ID Auth SDK (サイドカー) でエージェント ID を使用すると、独自のコンテキストで動作する自律エージェントと、ユーザーに代わって動作する対話型エージェントの両方を作成できます。 これらのシナリオを容易にするために、SDK では、エージェント ID とユーザー コンテキストを指定するための特定のクエリ パラメーターがサポートされています。

エージェント ID の詳細なガイダンスについては、[Microsoft エージェント ID プラットフォームのドキュメント](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform)を参照してください。

### 概要

エージェント ID では、次の 2 つの主要なパターンがサポートされます。

- **自律エージェント**: エージェントは、独自のアプリケーション コンテキストで動作します。
- **対話型エージェント**: 対話型エージェントは、ユーザーに代わって動作します。

SDK は、次の 3 つの省略可能なクエリ パラメーターを受け入れます。

- `AgentIdentity` - エージェント ID の GUID。
- `AgentUsername` - 特定のユーザーのユーザープリンシパル名 (UPN)。
- `AgentUserId` - UPN の代わりに、特定のユーザーのユーザー オブジェクト ID (OID)。

### 優先順位ルール

`AgentUsername` と `AgentUserId` は相互に排他的です。 規則 2: 相互排他性で説明されているように、両方のパラメーターを含む要求は検証に失敗します。 要求ごとにこれらのパラメーターを 1 つだけ指定します。

### Microsoft Entra ID構成

アプリケーションでエージェント ID を構成する前に、Microsoft Entra IDで必要なコンポーネントを設定します。 新しいアプリケーションをMicrosoft Entra IDテナントに登録するには、[アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/msidweb/getting-started/quickstart-webapp)に関するページを参照してください。

#### エージェント ID の前提条件

1. **エージェント アプリケーションの登録**:

    - Microsoft Entra IDに親エージェント アプリケーションを登録します。
    - ダウンストリーム API の API アクセス許可を構成します。
    - クライアント資格情報 (FIC+MSI、証明書、またはシークレット) を設定します。
2. **エージェント ID の構成**:

    - エージェント ブループリントを使用してエージェント ID を作成します。
    - エージェント ID に必要なアクセス許可を割り当てます。
3. **アプリケーションのアクセス許可**:

    - 自律的なシナリオに対してアプリケーションのアクセス許可を付与します。
    - ユーザー委任シナリオに対して権限を委任します。
    - 必要に応じて、管理者の同意が提供されていることを確認します。

Microsoft Entra IDでエージェント ID を構成する詳細な手順については、[Microsoft エージェント ID プラットフォームのドキュメント](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform)を参照してください。

### セマンティック ルール

正常に認証するには、エージェント ID パラメーターを正しく使用する必要があります。 次の規則は、 `AgentIdentity`、 `AgentUsername`、および `AgentUserId` パラメーターの使用を制御します。 SDK から返される検証エラーを回避するには、次の規則に従います。

#### 規則 1: AgentIdentity の要件

**`AgentUsername`** または **`AgentUserId`** は **`AgentIdentity`**とペアにする必要があります。

`AgentUsername`なしで`AgentUserId`または`AgentIdentity`を指定した場合、要求は検証エラーで失敗します。

```bash
# INVALID - AgentUsername without AgentIdentity
GET /AuthorizationHeader/Graph?AgentUsername=user@contoso.com

# VALID - AgentUsername with AgentIdentity
GET /AuthorizationHeader/Graph?AgentIdentity=agent-client-id&AgentUsername=user@contoso.com
```

#### 規則 2: 相互排他性

**`AgentUsername`** と **`AgentUserId`** は相互に排他的なパラメーターです。

同じ要求で `AgentUsername` と `AgentUserId` の両方を指定することはできません。 両方のパラメーターを指定すると、要求は検証エラーで失敗します。

```bash
# INVALID - Both AgentUsername and AgentUserId specified
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id&AgentUsername=user@contoso.com&AgentUserId=user-oid

# VALID - Only AgentUsername
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id&AgentUsername=user@contoso.com

# VALID - Only AgentUserId
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id&AgentUserId=user-object-id
```

#### ルール 3: 自律型と対話型

パラメーターの組み合わせによって、認証パターンが決まります。

| パラメーター | パターン | Description |
| --- | --- | --- |
| `AgentIdentity` のみ | **自律エージェント** | エージェント ID のアプリケーション トークンを取得します |
| `AgentIdentity` + `AgentUsername` | **対話型エージェント** | 指定されたユーザーのユーザー トークンを取得します (UPN 別) |
| `AgentIdentity` + `AgentUserId` | **対話型エージェント** | 指定したユーザーのユーザー トークンを取得します (オブジェクト ID による)。 |

**例**:

```bash
# Autonomous agent - application context
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id

# Interactive agent - user context by username
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id&AgentUsername=user@contoso.com

# Interactive agent - user context by user ID
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id&AgentUserId=user-object-id
```

### 使用パターン

使用パターンごとに、パラメーターの組み合わせによって、認証フローと取得されたトークンの種類が決まります。

#### パターン 1: 自律エージェント

エージェント アプリケーションは、独自のアプリケーション コンテキストで独立して実行され、アプリケーション トークンを取得します。

**シナリオ**: ファイルを単独で処理するバッチ処理サービス。

```bash
GET /AuthorizationHeader/Graph?AgentIdentity=12345678-1234-1234-1234-123456789012
```

**トークンの特性**:

- トークンの種類: アプリケーション トークン
- 件名 (`sub`): エージェント アプリケーションのオブジェクト ID
- エージェントの ID 用に作成されたトークン
- **アクセス許可**: エージェント ID に割り当てられたアプリケーションのアクセス許可

**ユース ケース**:

- 自動バッチ処理
- バックグラウンド タスク
- システム間操作
- ユーザー コンテキストのないスケジュールされたジョブ

#### パターン 2: 自律ユーザー エージェント (ユーザー名別)

エージェントは、UPN によって識別された特定のユーザーに代わって実行されます。

**シナリオ**: チャット アプリケーションでユーザーに代わって機能する AI アシスタント。

```bash
GET /AuthorizationHeader/Graph?AgentIdentity=12345678-1234-1234-1234-123456789012&AgentUsername=alice@contoso.com
```

**トークンの特性**:

- トークンの種類: ユーザー トークン
- 件名 (`sub`): ユーザーのオブジェクト ID
- トークン クレームに含まれるエージェントIDの側面
- **インタラクティブ権限**: ユーザーにスコープが設定されたインタラクティブな権限

**ユース ケース**:

- 対話型エージェント アプリケーション
- ユーザー委任を使用した AI アシスタント
- ユーザースコープにおける自動化
- カスタマイズされたワークフロー

#### パターン 3: 自律ユーザー エージェント (オブジェクト ID 別)

エージェントは、オブジェクト ID で識別された特定のユーザーに代わって動作します。

**シナリオ**: 格納されているユーザー ID を使用してユーザー固有のタスクを処理するワークフロー エンジン。

```bash
GET /AuthorizationHeader/Graph?AgentIdentity=12345678-1234-1234-1234-123456789012&AgentUserId=87654321-4321-4321-4321-210987654321
```

**トークンの特性**:

- トークンの種類: ユーザー トークン
- 件名 (`sub`): ユーザーのオブジェクト ID
- トークン クレームに含まれるエージェントIDの側面
- **インタラクティブ権限**: ユーザーにスコープが設定されたインタラクティブな権限

**ユース ケース**:

- 保存されたユーザー識別子を持つ実行時間の長いワークフロー
- 複数のユーザーに代わってバッチ操作を実行する
- ユーザー参照にオブジェクト ID を使用するシステム

#### パターン 4: 対話型エージェント (呼び出し元のユーザーに代わって動作)

エージェント Web API は、ユーザー トークンを受け取り、検証し、そのユーザーに代わって委任された呼び出しを行います。

**シナリオ**: 着信ユーザー トークンを検証し、ダウンストリーム サービスへの委任された呼び出しを行う対話型エージェントとして機能する Web API。

**フロー**:

1. エージェント Web API は、呼び出し元のアプリケーションからユーザー トークンを受け取ります。
2. `/Validate` エンドポイントを介してトークンを検証します。
3. `/AuthorizationHeader`と受信 Authorization ヘッダーのみを使用して`AgentIdentity`を呼び出すことによって、ダウンストリーム API のトークンを取得します。

```bash
# Step 1: Validate incoming user token
GET /Validate
Authorization: Bearer <user-token>

# Step 2: Get authorization header on behalf of the user
GET /AuthorizationHeader/Graph?AgentIdentity=<agent-client-id>
Authorization: Bearer <user-token>
```

**トークンの特性**:

- トークンの種類: ユーザー トークン (OBO フロー)
- 件名 (`sub`): 元のユーザーのオブジェクト ID
- エージェントがユーザーの仲介役として機能する
- **アクセス許可**: ユーザーにスコープが設定された対話型のアクセス許可

**ユース ケース**:

- エージェントとして機能する Web API
- 対話型エージェント サービス
- ダウンストリーム API に委任するエージェントベースのミドルウェア
- ユーザー コンテキストを検証して転送するサービス

#### パターン 5: 通常の要求 (エージェントなし)

エージェント パラメーターを指定しない場合、SDK は受信トークンの ID を使用します。

**シナリオ**: エージェント ID を使用しない標準の代理 (OBO) フロー。

```bash
GET /AuthorizationHeader/Graph
Authorization: Bearer <user-token>
```

**トークンの特性**:

- トークンの種類: 受信トークンと構成に依存
- 標準の OBO またはクライアント資格情報フローを使用する
- エージェント ID ファセットなし

### コード例

次のコード スニペットは、さまざまなプログラミング言語を使用してさまざまなエージェント ID パターンを実装する方法と、SDK エンドポイントと対話する方法を示しています。 このコードでは、SDK へのプロセス外呼び出しを処理して、ダウンストリーム API 呼び出しの承認ヘッダーを取得する方法を示します。

#### TypeScript: 自律エージェント

```typescript
const sidecarUrl = "http://localhost:5000";
const Agent ID = "12345678-1234-1234-1234-123456789012";

async function runBatchJob() {
  const response = await fetch(
    `${sidecarUrl}/AuthorizationHeader/Graph?AgentIdentity=${agentId}`,
    {
      headers: {
        'Authorization': 'Bearer system-token'
      }
    }
  );
  
  const { authorizationHeader } = await response.json();
  // Use authorizationHeader for downstream API calls
}
```

#### Python: ユーザー ID を持つエージェント

```python
import requests

sidecar_url = "http://localhost:5000"
agent_id = "12345678-1234-1234-1234-123456789012"
user_email = "alice@contoso.com"

response = requests.get(
    f"{sidecar_url}/AuthorizationHeader/Graph",
    params={
        "AgentIdentity": agent_id,
        "AgentUsername": user_email
    },
    headers={"Authorization": f"Bearer {system_token}"}
)

token = response.json()["authorizationHeader"]
```

#### TypeScript: 対話型エージェント

```typescript
async function delegateCall(userToken: string) {
  // Validate incoming token
  const validation = await fetch(
    `${sidecarUrl}/Validate`,
    {
      headers: { 'Authorization': `Bearer ${userToken}` }
    }
  );
  
  const claims = await validation.json();
  
  // Call downstream API on behalf of user
  const response = await fetch(
    `${sidecarUrl}/DownstreamApi/Graph`,
    {
      headers: { 'Authorization': `Bearer ${userToken}` }
    }
  );
  
  return await response.json();
}
```

#### HttpClient を使用した C#

```csharp
using System.Net.Http;

var httpClient = new HttpClient();

// Autonomous agent
var autonomousUrl = $"http://localhost:5000/AuthorizationHeader/Graph" +
    $"?AgentIdentity={agentClientId}";
var response = await httpClient.GetAsync(autonomousUrl);

// Delegated agent with username
var delegatedUrl = $"http://localhost:5000/AuthorizationHeader/Graph" +
    $"?AgentIdentity={agentClientId}" +
    $"&AgentUsername={Uri.EscapeDataString(userPrincipalName)}";
response = await httpClient.GetAsync(delegatedUrl);

// Delegated agent with user ID
var delegatedByIdUrl = $"http://localhost:5000/AuthorizationHeader/Graph" +
    $"?AgentIdentity={agentClientId}" +
    $"&AgentUserId={userObjectId}";
response = await httpClient.GetAsync(delegatedByIdUrl);
```

### エラー シナリオ

エージェント ID パラメーターを正しく構成しないか、正しく使用しないと、SDK は検証エラーを返します。 次のセクションでは、一般的なエラー シナリオとそれに対応する応答について説明します。 エラー処理の詳細については、 [トラブルシューティング ガイドを](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/troubleshooting)参照してください。

#### AgentUsername で AgentIdentity が見つからない

**要求**:

```bash
GET /AuthorizationHeader/Graph?AgentUsername=user@contoso.com
```

**応答:**

```json
{
  "type": "https://tools.ietf.org/html/rfc7231#section-6.5.1",
  "title": "Bad Request",
  "status": 400,
  "detail": "AgentUsername requires AgentIdentity to be specified"
}
```

#### AgentUsername と AgentUserId の両方を指定

**要求**:

```bash
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id&AgentUsername=user@contoso.com&AgentUserId=user-oid
```

**応答:**

```json
{
  "type": "https://tools.ietf.org/html/rfc7231#section-6.5.1",
  "title": "Bad Request",
  "status": 400,
  "detail": "AgentUsername and AgentUserId are mutually exclusive"
}
```

#### AgentUserId 形式が無効です

**要求**:

```bash
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id&AgentUserId=invalid-guid
```

**応答:**

```json
{
  "type": "https://tools.ietf.org/html/rfc7231#section-6.5.1",
  "title": "Bad Request",
  "status": 400,
  "detail": "AgentUserId must be a valid GUID"
}
```

### ベスト プラクティス

1. **入力を検証**する: 要求を行う前に、常にエージェント ID パラメーターを検証します。
2. **使用可能な場合はオブジェクト ID を使用**します。オブジェクト ID の方が安定しています。
3. **適切なエラー処理を実装**する: エージェント ID 検証エラーを適切に処理します。
4. **セキュリティで保護されたエージェント資格情報**: エージェント ID クライアント ID と資格情報を保護します。
5. **監査エージェントの操作**: セキュリティとコンプライアンスのためにエージェント ID の使用状況をログに記録して監視します。
6. **両方のパターンをテストする**: テストで自律的なシナリオと委任されたシナリオの両方を検証します。
7. **ドキュメントの意図**: 各ユース ケースに適したエージェント パターンを明確に文書化します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/agent-id-sdk/comparison"} -->
## 比較: Microsoft Entra ID Auth SDK (サイドカー) と In-Process Microsoft.Identity.Web

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/comparison
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: Microsoft Entra ID 認証 SDK (サイドカー) とインプロセスの Microsoft.Identity.Web ライブラリを比較して、アプリケーションに適したアプローチを選択します。

このガイドは、アプリケーションで認証を処理する際の、Microsoft Entra ID Auth SDK（サイドカー）とインプロセスの Microsoft.Identity.Web ライブラリの違いを把握するのに役立ちます。 Microsoft。Identity.Web ライブラリは、パフォーマンスを最大化するために、.NET アプリケーションに直接統合されます。 Microsoft Entra ID Auth SDK (サイドカー) は別のコンテナーとして実行され、HTTP API を介して任意のプログラミング言語をサポートします。 適切なアプローチの選択は、アプリケーションのアーキテクチャ、言語、デプロイ環境によって異なります。

### アーキテクチャの違い

基本的な違いは、 **認証ロジックが実行される場所**にあります。 Microsoft。Identity.Web は、アプリケーション プロセス内で実行されます。 Microsoft Entra ID認証 SDK (サイドカー) は、アプリケーションと共に独立したサービスとして動作します。 このアーキテクチャの選択は、開発ワークフローや運用の複雑さなどの要因に影響します。

| 特徴 | Microsoft.Identity.Web (In-Process) | Microsoft Entra ID Auth SDK (サイドカー) (プロセス外) |
| --- | --- | --- |
| **プロセス境界** | アプリケーションと同じプロセス、メモリ、ライフサイクルを共有し、ダイレクト メソッド呼び出しと共有構成を有効にします | 完全な分離を維持し、HTTP API 経由でのみ通信し、独自のリソースを個別に管理します |
| **言語結合** | 認証戦略を.NETに密に結合し、認証が必要なすべての場所で C# のエクスペリエンスと.NETランタイムを必要とします | アプリケーションのテクノロジ スタックから認証を分離し、Python、Node.js、Go、または任意の HTTP 対応言語と同等に機能する言語に依存しない HTTP インターフェイスを公開します |
| **デプロイメント モデル** | アプリケーション バイナリに埋め込まれた NuGet パッケージとしてデプロイし、モノリシック デプロイ ユニットを作成する | 個別のコンテナー イメージとしてデプロイし、アプリケーション コードに影響を与えることなく、認証ロジックの独立したバージョン管理、スケーリング、および更新を有効にします。 |

#### Microsoft.Identity.Web (インプロセス)

このコードスニペットは、Microsoft.Identity.Web が ASP.NET Core アプリケーションにどのように直接統合されるかを示しています。

```csharp
// Startup configuration
services.AddMicrosoftIdentityWebApiAuthentication(Configuration)
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddDownstreamApi("Graph", Configuration.GetSection("DownstreamApis:Graph"))
    .AddInMemoryTokenCaches();

// Usage in controller
public class MyController : ControllerBase
{
    private readonly IDownstreamApi _downstreamApi;
    
    public MyController(IDownstreamApi downstreamApi)
    {
        _downstreamApi = downstreamApi;
    }
    
    public async Task<ActionResult> GetUserData()
    {
        var user = await _downstreamApi.GetForUserAsync<User>("Graph", 
            options => options.RelativePath = "me");
        return Ok(user);
    }
}
```

#### Microsoft Entra ID Auth SDK (サイドカー) (プロセス外)

このコード スニペットは、HTTP を使用して Node.js アプリケーションから Microsoft Entra ID 認証 SDK (サイドカー) を呼び出す方法を示しています。 SDK の `/DownstreamApi` エンドポイントへの呼び出しは、 `Authorization` ヘッダーで OBO フローの受信トークンを渡すなど、トークンの取得とダウンストリーム API 呼び出しを処理します。

```typescript
// Configuration
const SidecarUrl = process.env.SIDECAR_URL || "http://localhost:5000";

// Usage in application
async function getUserData(incomingToken: string) {
  const response = await fetch(
    `${SidecarUrl}/DownstreamApi/Graph?optionsOverride.RelativePath=me`,
    {
      headers: {
        'Authorization': `Bearer ${incomingToken}`
      }
    }
  );
  
  const result = await response.json();
  return JSON.parse(result.content);
}
```

### 機能の比較

| 特徴 | Microsoft。Identity.Web | Microsoft Entra ID 認証 SDK (サイドカー) |
| --- | --- | --- |
| **言語サポート** | C# / .NETのみ | 任意の言語 (HTTP) |
| **Deployment** | プロセス内ライブラリ | 個別のコンテナー |
| **トークンの取得** | MSAL.NET を直接に | HTTP API 経由 |
| **トークン キャッシュ** | インメモリ型、分散型 | インメモリ型、分散型 |
| **OBO フロー** | ネイティブ サポート | HTTP エンドポイント経由 |
| **クライアント資格情報** | ネイティブ サポート | HTTP エンドポイント経由 |
| **マネージド ID** | 直接サポート | 直接サポート |
| **エージェント ID** | 拡張機能によって | クエリ パラメーター |
| **トークンの検証** | ミドルウェア | /Validate エンドポイント |
| **ダウンストリーム API** | IDownstreamApi | /DownstreamApi エンドポイント |
| **Microsoft Graph** | Graph SDK の統合 | 「DownstreamApi」経由 |
| **パフォーマンス** | インプロセス (最速) | HTTP オーバーヘッド |
| **Configuration** | `appsettings.json` とコード | `appsettings.json` と環境変数 |
| **デバッグ** | 標準.NETデバッグ | コンテナーのデバッグ |
| **ホット リロード** | .NET ホット リロード（ホットリロード技術） | コンテナーの再起動 |
| **パッケージの更新** | NuGet パッケージ | コンテナー イメージ |
| **ライセンス** | MIT | MIT |

### 各アプローチを使用するタイミング

Microsoft.Identity.Web と Microsoft Entra ID Auth SDK（サイドカー）のどちらを選ぶかは、アプリケーションの要件、アーキテクチャ、およびデプロイ戦略によって決まります。 ニーズに応じて、一方のアプローチが他のアプローチよりも適している場合があります。 次のガイドラインは、情報に基づいた意思決定を行う際に役立ちます。

| Scenario | Microsoft.Identity.Web (In-Process) | Microsoft Entra ID Auth SDK (サイドカー) (アウトオブプロセス) |
| --- | --- | --- |
| **アプリケーション スタック** | .NETアプリケーションを排他的に使用するため• ASP.NET Core Web API• ASP.NET Core Web Apps• .NET Worker サービス• Blazor アプリケーション• デーモン アプリ | 複数言語マイクロサービス• Node.js、Python、Go、Javaサービス• ポリグロット アーキテクチャ• .NET以外のサービス• レガシ システムの統合 |
| **パフォーマンス要件** | パフォーマンスが重要• 高スループットのシナリオ• 待機時間の影響を受けやすい操作• ミリ秒ごとのカウント | HTTP オーバーヘッドを許容できる• 最大 1 ~ 5 ミリ秒の追加待機時間が許容可能• 認証によってボトルネックにならないスループット |
| **統合のニーズ** | 詳細な統合が必要• カスタム MSAL.NET 構成• MSAL 機能への直接アクセス• 高度なトークン キャッシュ戦略 | 標準化された統合• HTTP API で十分• サービス間で一貫した認証パターン |
| **開発エクスペリエンス** | 迅速な開発• クイックプロトタイピング• 開発用ホットリロード• 標準.NETデバッグ | コンテナーベースの開発• 変更のためのコンテナーの再起動• コンテナーのデバッグが必要 |
| **チームとアーキテクチャ** | 単一言語スタック• C#/.NET でのチームの専門知識• 多言語要件なし | テクノロジの多様性• フレームワークと言語の組み合わせ• Polyglot チームの構造 |
| **デプロイメント モデル** | モノリシック展開• 単一アプリケーションのデプロイ• 従来のホスティング モデル | コンテナ化によるデプロイメント• Kubernetes 環境• Docker Compose のセットアップサービス メッシュ アーキテクチャ |
| **Operations** | 結合認証の更新• 認証の変更にはアプリのリビルドが必要• アプリケーションとの共有ライフサイクル | 運用上の利点認証ロジックの独立したスケーリング• 認証更新プログラムをアプリ コードから分離する認証の一元的な監視 |

### 移行ガイダンス

#### Microsoft.Identity.Web から Microsoft Entra ID Auth SDK（サイドカー）への移行

特定のシナリオでは、認証に Microsoft Entra ID Auth SDK（サイドカー）を活用するために、Microsoft.Identity.Web を使用している既存の .NET アプリケーションを移行したい場合があります。 移行の理由としては、複数言語アーキテクチャの採用、サービス間での認証の標準化、コンテナー化されたデプロイ モデルへの移行などがあります。

この変更を行う前に、慎重な検討と計画が必要です。 このセクションでは、アプリケーションの移行に役立つコード例を含む高度な移行パスを示します。

注意事項

Microsoft は、Microsoft.Identity.Web から Microsoft Entra ID Auth SDK (sidecar) へ移行することを推奨していません。 この変更を行う場合、次の例は、他の言語やフレームワークでも同様の概念を示しています。

##### 手順 1: SDK コンテナーをデプロイする

まず、SDK コンテナーをポッドに追加します。

```yaml
# Before: Single ASP.NET Core container
containers:
- name: app
  image: myregistry/myapp:latest

# After: App + Microsoft Entra ID Auth SDK (sidecar)
containers:
- name: app
  image: myregistry/myapp:latest
  env:
  - name: SIDECAR_URL
    value: "http://localhost:5000"

- name: sidecar
  image: mcr.microsoft.com/entra-sdk/auth-sidecar:1.0.0
  env:
  - name: AzureAd__TenantId
    value: "your-tenant-id"
  - name: AzureAd__ClientId
    value: "your-client-id"
```

##### 手順 2: 構成を移行する

次に、 `appsettings.json` から環境変数に構成を転送します。

**変更前 (appsettings.json)**

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "your-tenant-id",
    "ClientId": "your-client-id"
  },
  "DownstreamApis": {
    "Graph": {
      "BaseUrl": "https://graph.microsoft.com/v1.0",
      "Scopes": "User.Read Mail.Read", 
      "RelativePath": "/me"
    }
  }
}
```

**After (Kubernetes ConfigMap / 環境変数)**

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: sidecar-config
data:
  AzureAd__Instance: "https://login.microsoftonline.com/"
  AzureAd__TenantId: "your-tenant-id"
  AzureAd__ClientId: "your-client-id"
  DownstreamApis__Graph__BaseUrl: "https://graph.microsoft.com/v1.0"
  DownstreamApis__Graph__Scopes: "User.Read Mail.Read"
  DownstreamApis__Graph__RelativePath: "/me"
```

##### 手順 3: アプリケーション コードを更新する

Microsoft.Identity.Web へのインプロセス呼び出しをすべて特定し、Microsoft Entra ID Auth SDK（サイドカー）のエンドポイントへの HTTP 呼び出しに置き換えます。

**（IDownstreamApiを使用するC#の）前の状態**：

```csharp
public class UserController : ControllerBase
{
    private readonly IDownstreamApi _downstreamApi;
    
    public UserController(IDownstreamApi downstreamApi)
    {
        _downstreamApi = downstreamApi;
    }
    
    [HttpGet]
    public async Task<ActionResult<User>> GetMe()
    {
        var user = await _downstreamApi.GetForUserAsync<User>(
            "Graph",
            options => options.RelativePath = "me"
        );
        return Ok(user);
    }
}
```

**After (HTTP クライアントを使用する任意の言語)**:

次のスニペットでは、`/DownstreamApi` エンドポイントを使用して Microsoft Entra ID Auth SDK (サイドカー) を呼び出してユーザー データを取得します。 例は、C# と TypeScript で提供されています。

```csharp
public class UserController : ControllerBase
{
    private readonly HttpClient _httpClient;
    private readonly string _SidecarUrl;
    
    public UserController(IHttpClientFactory httpClientFactory, IConfiguration config)
    {
        _httpClient = httpClientFactory.CreateClient();
        _SidecarUrl = config["SIDECAR_URL"];
    }
    
    [HttpGet]
    public async Task<ActionResult<User>> GetMe()
    {
        var inboundAuthorizationHeader = Request.Headers["Authorization"].ToString();
        // this validates the inbound authorization header and calls the downstream API.
        // If you don't call a downstream API, Do validate the inbound authorization header 
        // (calling the /Validate endpoint)
        var request = new HttpRequestMessage(
            HttpMethod.Get,
            $"{_SidecarUrl}/DownstreamApi/Graph?optionsOverride.RelativePath=me"
        );
        request.Headers.Add("Authorization", inboundAuthorizationHeader);
        
        var response = await _httpClient.SendAsync(request);
        var result = await response.Content.ReadFromJsonAsync<SidecarResponse>();
        var user = JsonSerializer.Deserialize<User>(result.Content);
        return Ok(user);
    }
}
```

#### TypeScript

TypeScript には、次のように同じロジックを実装できます。

```typescript
export async function getMe(incomingToken: string): Promise<User> {
  const SidecarUrl = process.env.SIDECAR_URL!;
  
  const response = await fetch(
    `${SidecarUrl}/DownstreamApi/Graph?optionsOverride.RelativePath=me`,
    {
      headers: {
        'Authorization': incomingToken
      }
    }
  );
  
  const result = await response.json();
  return JSON.parse(result.content) as User;
}
```

##### 手順 4: Microsoft.Identity.Web の依存関係を削除する

以前の手順を完了したら、プロジェクトから Microsoft.Identity.Web の NuGet パッケージを削除して、アプリケーションを整理します。

```xml
<!-- Remove these from .csproj -->
<PackageReference Include="Microsoft.Identity.Web" Version="..." />
<PackageReference Include="Microsoft.Identity.Web.MicrosoftGraph" Version="..." />
<PackageReference Include="Microsoft.Identity.Web.DownstreamApi" Version="..." />
```

それでもアプリでトークンを検証する場合は、元の認証構成を削除する必要はありません。 代わりに、検証を完全に Microsoft Entra ID Auth SDK (サイドカー) に委任できます。

```csharp
// Remove from Program.cs or Startup.cs
services.AddMicrosoftIdentityWebApiAuthentication(Configuration)
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddDownstreamApi("Graph", Configuration.GetSection("DownstreamApis:Graph"))
    .AddInMemoryTokenCaches();
```

##### 手順 5: テストと検証

1. **単体テスト**: SDK への HTTP 呼び出しをモックするようにテストを更新します。
2. **統合テスト**: ステージングで SDK 通信をテストします。
3. **パフォーマンス テスト**: HTTP オーバーヘッドへの影響を測定します。
4. **セキュリティ テスト**: トークンの処理とネットワーク ポリシーを検証します。

### パフォーマンスに関する考慮事項

#### SDK のオーバーヘッド

Microsoft Entra ID認証 SDK (サイドカー) では、HTTP 通信のオーバーヘッドが発生します。

| パフォーマンス係数 | インパクト | 軽減戦略 |
| --- | --- | --- |
| **Latency** | localhost 通信の要求あたり約 1 ~ 5 ミリ秒 | HTTP/2 を使用して、接続のオーバーヘッドを削減します。 |
| **Throughput** | HTTP 接続プールによって制限される | HTTP 接続を再利用するための接続プールを実装します。 |
| **メモリ** | 追加のコンテナー メモリのオーバーヘッド | 適切な SDK リソースの割り当てを確認します。 |
| **要求の効率** | 複雑な操作を行うための多重ラウンドトリップ | 可能な限り複数の操作を組み合わせるバッチ要求。 |
| **トークンのパフォーマンス** | トークン取得のオーバーヘッドの繰り返し | 最適なパフォーマンスを得るための SDK のトークン キャッシュを活用します。 |

#### 処理中のパフォーマンス

Microsoft.Identity.Web を使用すると、アプリケーションと同じプロセスで実行されるため、オーバーヘッドは最小限です。 これは、HTTP の制限なしに、マイクロ秒の待機時間と共有プロセス メモリを備えたネイティブ メソッド呼び出しを提供します。 パフォーマンスが重要な場合は、インプロセス統合が最適な選択肢です。 ただし、Microsoft Entra ID認証 SDK (サイドカー) の柔軟性と言語に依存しない設計は、多くのシナリオでのパフォーマンスのトレードオフを上回る可能性があります。

次の表は、インプロセスの使用状況と Microsoft Entra ID 認証 SDK (サイドカー) (アウトプロセス) の使用に関するいくつかのパフォーマンスとコストの比較を示しています。

### コストに関する考慮事項

| コストファクター | Microsoft.Identity.Web (In-Process) | Microsoft Entra ID 認証 SDK (サイドカー) (プロセス外) |
| --- | --- | --- |
| **計算する** | アプリケーション プロセスでの追加の CPU とメモリを最小限に抑えます | ポッドあたりの追加のコンテナー リソース。 |
| **Network** | 追加のオーバーヘッドなし | 最小限の localhost 通信。 |
| **ストレージ** | NuGet パッケージ サイズ (最大 10 MB) | コンテナー イメージ ストレージ。 |
| **管理** | 追加のオーバーヘッドなし | コンテナー オーケストレーションのオーバーヘッド。 |

#### コストの例

128 MiB/100m SDK 構成の 10 個のレプリカの場合:

| Resource | 処理中 | Microsoft Entra ID 認証 SDK (サイドカー) |
| --- | --- | --- |
| **メモリ** | 約 0 MB の追加 | 10 × 128 MiB = 1.28 GB |
| **CPU** | 約0%追加 | 10 × 100m = 1 コア |
| **ストレージ** | デプロイあたりおおよそ 10 MB | ノードあたりのコンテナー イメージ のサイズ |

### サポートとメンテナンス

| 特徴 | Microsoft。Identity.Web | Microsoft Entra ID 認証 SDK (サイドカー) |
| --- | --- | --- |
| **Updates** | NuGet パッケージの更新 | コンテナー イメージの更新 |
| **重大な変更** | パッケージのバージョニング経由で | コンテナー タグを使用する |
| **バグ修正** | コンパイル時の統合 | ランタイム コンテナーの更新 |
| **セキュリティ パッチ** | アプリケーションのリビルド | コンテナーを再デプロイする |
| **ドキュメンテーション** | 広範な.NETドキュメント | このドキュメント |
| **Community** | 大規模な.NET コミュニティ | 成長するコミュニティ |

### ハイブリッド アプローチ

同じアーキテクチャ内で両方のアプローチを組み合わせることができます。 最大限のパフォーマンスが求められる .NET サービスには Microsoft.Identity.Web を使用し、.NET 以外のサービスや、言語に依存しない認証パターンが必要な場合には Microsoft Entra ID Auth SDK（サイドカー）を使用します。 このハイブリッド戦略は、サービス エコシステム全体の一貫性と柔軟性を維持しながら、重要なパフォーマンスを最適化するのに役立ちます。

アーキテクチャの例を次に示します。

```mermaid
graph TB
    subgraph cluster["Kubernetes Cluster"]
        subgraph netpod["<b>.NET API Pod</b>"]
            netapi["<b>.NET API</b><br/>(Microsoft.Identity.Web)"]
            style netapi fill:#0078d4,stroke:#005a9e,stroke-width:2px,color:#fff
        end
        subgraph nodepod["<b>Node.js API Pod</b>"]
            nodeapi["<b>Node.js API</b>"]
            sidecar["<b>Microsoft Entra ID Auth SDK (sidecar)</b>"]
            style nodeapi fill:#68a063,stroke:#4a7c45,stroke-width:2px,color:#fff
            style sidecar fill:#f2711c,stroke:#d85e10,stroke-width:2px,color:#fff
        end
    end
    style cluster fill:#f0f0f0,stroke:#333,stroke-width:3px
    style netpod fill:#e8f4f8,stroke:#0078d4,stroke-width:2px
    style nodepod fill:#e8f4e8,stroke:#68a063,stroke-width:2px
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/agent-id-sdk/configuration"} -->
## 構成リファレンス: Microsoft Entra ID Auth SDK (サイドカー) 設定

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/configuration
- Service: msal / microsoft-identity-web
- Article date: 2026-09-15
- Summary: Microsoft Entra ID認証 SDK (サイドカー) 環境変数、資格情報、ダウンストリーム API を構成するための完全なリファレンス。

このガイドでは、コンテナー化された環境でのアプリケーションのトークンの取得と管理を処理するコンテナー化認証サービスである Microsoft Entra ID Auth SDK (サイドカー) の構成オプションを提供します。 SDK は、認証ライブラリを直接埋め込むアプリケーションを必要とせずに、Microsoft Entra ID認証、代理 (OBO) トークン フロー、ダウンストリーム API 呼び出しを管理することで、ID 統合を簡素化します。

このガイドでは Kubernetes デプロイ パターンに焦点を当てていますが、SDK は、Docker、Azure Container Instances、その他のコンテナー オーケストレーション プラットフォームを含む任意のコンテナー化された環境にデプロイできます。

Azure Kubernetes Service (AKS)にデプロイする場合、開発環境を設定する場合、または運用ワークロードを構成する場合、このリファレンスでは、Microsoft Entra IDを使用してアプリケーションをセキュリティで保護するために必要な構成パターン、資格情報の種類、環境変数について説明します。

### 構成の概要

Microsoft Entra ID認証 SDK (サイドカー) は、ASP.NET Core規則に従って構成ソースを使用して構成されます。 構成値は、次のような複数の方法で指定できます。

- 環境変数 (Kubernetes に推奨)
- Entra ID構成 - `appsettings.json` ファイルがコンテナーにアタッチされているか、yaml ファイルに埋め込まれています。
- コマンドライン引数
- Azure App ConfigurationまたはKey Vault (高度なシナリオの場合)

### コア Entra ID設定

Microsoft Entra ID認証 SDK (サイドカー) デプロイでは、受信トークンを認証し、ダウンストリーム API のトークンを取得するためのコア Entra ID設定が必要です。 セキュリティで保護された認証を確保するには、次の YAML 形式の適切なクライアント資格情報 (通常は環境変数) を使用します。

#### 必要な構成

まず、SDK のコア Entra ID設定を構成して、受信トークンを認証し、ダウンストリーム API のトークンを取得します。

```yaml
env:
- name: AzureAd__Instance
  value: "https://login.microsoftonline.com/"
- name: AzureAd__TenantId
  value: "<your-tenant-id>"
- name: AzureAd__ClientId
  value: "<your-client-id>"
```

| Key | Description | 必須 | 既定値 |
| --- | --- | --- | --- |
| `AzureAd__Instance` | Microsoft Entra機関の URL | いいえ | `https://login.microsoftonline.com/` |
| `AzureAd__TenantId` | Microsoft Entra テナント ID | イエス | - |
| `AzureAd__ClientId` | アプリケーション (クライアント) ID | イエス | - |
| `AzureAd__Audience` | 受信トークンで予想される対象ユーザー | いいえ | `api://{ClientId}` |
| `AzureAd__Scopes` | 受信トークンに必要なスコープ (スペース区切り) | いいえ | - |

注

予想される対象ユーザーの値は、アプリの登録の [**要求されたAccessTokenVersion**](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest#requestedaccesstokenversion-attribute) によって異なります。

- **バージョン 2**: `{ClientId}` 値を直接使用する
- **バージョン 1** または **null**: アプリ ID URI を使用する (通常はカスタマイズしていない限り `api://{ClientId}` )

### クライアント資格情報の構成

Microsoft Entra ID認証 SDK (サイドカー) では、ダウンストリーム API のトークンを取得するときに、Microsoft Entra IDで認証するための複数のクライアント資格情報の種類がサポートされています。 デプロイ環境とセキュリティ要件に最適な資格情報の種類を選択し、選択した構成がシナリオに適していることを確認します。

資格情報の種類ごとに、さまざまなシナリオが提供されます。

- **クライアント シークレット**: 開発とテスト用の簡単なセットアップ (運用環境では推奨されません)
- **Key Vault 証明書**: 証明書管理を一元化した運用環境
- **ファイル証明書**: 証明書がファイルとしてマウントされている場合 (たとえば、Kubernetes シークレットを使用)
- **Certificate Store**: 証明書ストアを含むWindows環境
- **Workload Identity for Containers**: ファイル ベースのトークン プロジェクションでMicrosoft Entra ワークロード ID を使用する AKS に推奨
- ** VM/App Services の管理 ID**: システムまたはユーザー割り当てマネージド ID を使用したAzure 仮想マシンと App Services (コンテナー用ではない)

次の YAML 形式で 1 つ以上の資格情報ソースを構成します。

#### 環境別に資格情報を選択する

サイドカーの実行場所に基づいて資格情報を選択します。 `SignedAssertionFilePath`と`SignedAssertionFromManagedIdentity`はどちらもフェデレーション ID 資格情報 (FIC) です。 サイドカーが署名付きアサーションを取得する方法は異なります。

| Environment | `SourceType` | 注記 |
| --- | --- | --- |
| Azure Kubernetes Service (AKS) | `SignedAssertionFilePath` | Azure ワークロード ID Webhook は、トークンをプロジェクトしてローテーションします。 |
| 非Azureまたはオンプレミスの Kubernetes | `SignedAssertionFilePath` | 投影されたトークン パスを設定します。 これは、ワークロード ID フェデレーションによってサポートされます。 |
| マネージド ID を使用して VM、App Service、または Container Apps をAzureする | `SignedAssertionFromManagedIdentity` | IMDS を介してマネージド ID Azure使用します。 Azureのみ。 |
| Docker または OIDC 発行者のない任意のホスト | `KeyVault`、 `Path`、または `StoreWithThumbprint` | プラットフォームが OIDC トークンを投影できない場合は、証明書を使用します。 |
| 開発またはテスト | `ClientSecret` | 運用環境での使用は推奨されません。 |

**重要**: `SignedAssertionFromManagedIdentity` は汎用 Kubernetes 資格情報ではなく、 `SignedAssertionFilePath`のフォールバックではありません。 Azureマネージド ID とプローブを使用して、Service Fabric、App Service、IMDS などのホスティング環境Azureします。 Azure以外のホストでは、これらのいずれも検出されず、最終的に IMDS に対して要求がタイムアウトします。 サイドカーが予期せず IMDS に到達した場合は、このソースの種類を選択しました。 `SignedAssertionFilePath` を代わりに使用します。

#### クライアント シークレット

この構成では、サービス間認証にクライアント シークレットを使用してEntra ID認証を設定します。

```yaml
- name: AzureAd__ClientCredentials__0__SourceType
  value: "ClientSecret"
- name: AzureAd__ClientCredentials__0__ClientSecret
  value: "<your-client-secret>"
```

#### Key Vaultからの証明書

この構成では、Azure Key Vaultに格納されている証明書を使用して、Entra ID認証を設定します。

```yaml
- name: AzureAd__ClientCredentials__0__SourceType
  value: "KeyVault"
- name: AzureAd__ClientCredentials__0__KeyVaultUrl
  value: "https://<your-keyvault>.vault.azure.net"
- name: AzureAd__ClientCredentials__0__KeyVaultCertificateName
  value: "<certificate-name>"
```

#### ファイルからの証明書

この構成では、ファイルとして格納されている証明書を使用してEntra ID認証を設定します。

```yaml
- name: AzureAd__ClientCredentials__0__SourceType
  value: "Path"
- name: AzureAd__ClientCredentials__0__CertificateDiskPath
  value: "/path/to/certificate.pfx"
- name: AzureAd__ClientCredentials__0__CertificatePassword
  value: "<certificate-password>"
```

#### ストアからの証明書

この構成では、ローカル証明書ストアの証明書を使用してEntra ID認証を設定します。

```yaml
- name: AzureAd__ClientCredentials__0__SourceType
  value: "StoreWithThumbprint"
- name: AzureAd__ClientCredentials__0__CertificateStorePath
  value: "CurrentUser/My"
- name: AzureAd__ClientCredentials__0__CertificateThumbprint
  value: "<thumbprint>"
```

#### AKS のワークロード ID (AKS に推奨)

この構成では、AKS でMicrosoft Entra ワークロード ID を使用してEntra ID認証を設定します。 これは、Azure Workload Identity Webhook がプロジェクトを行い、トークンをローテーションするため、AKS で推奨されるアプローチです。

```yaml
- name: AzureAd__ClientCredentials__0__SourceType
  value: "SignedAssertionFilePath"
```

**注**: AKS では、サービス アカウントの注釈とポッド ラベルを使用してポッドが適切に構成されている場合、トークン ファイル パス `/var/run/secrets/azure/tokens/azure-identity-token`または環境変数は、Azure Workload Identity webhook によって自動的に投影されます。 完全なセットアップ手順については [、マネージド ID の使用](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/scenarios/managed-identity) に関する記事を参照してください。

#### 非Azureまたはオンプレミスの Kubernetes のワークロード ID

エージェント ID フローは、AKS に限定されません。 オンプレミスやその他のクラウドを含むすべての Kubernetes プラットフォームでは、ワークロード ID フェデレーションで `SignedAssertionFilePath` を使用できます。 AKS の外部にはワークロード ID webhook Azureがないため、プラットフォームがマウントする投影されたサービス アカウント トークンにサイドカーをポイントします。

```yaml
- name: AzureAd__ClientCredentials__0__SourceType
  value: "SignedAssertionFilePath"
- name: AzureAd__ClientCredentials__0__SignedAssertionFileDiskPath
  value: "/var/run/secrets/tokens/sa-token"
```

サイドカーは各トークン要求でファイルを再読み込みするため、投影されたアサーションのプラットフォーム駆動型ローテーションが自動的にサポートされます。

Azure以外の Kubernetes でこの資格情報を使用するには、環境が次の前提条件を満たしている必要があります。

- Kubernetes プラットフォームは、たとえば、投影された `serviceAccountToken` ボリュームを通じて、サービス アカウント トークンをサイドカー ポッドに投影します。
- クラスターは、パブリックに到達可能な OIDC 発行者と JWKS エンドポイントを公開して、Microsoft Entraがアサーションを検証できるようにします。
- ブループリント アプリケーションでフェデレーション ID 資格情報 (FIC) が構成され、発行者とサブジェクトが投影されたトークンと一致します。

投影された OIDC トークンまたはパブリック発行者をプラットフォームで提供できない場合は、代わりに、証明書資格情報 (Key Vaultからの証明書やファイルからの証明書など) を使用します。

#### VM と App Services のマネージド ID

Virtual Machines または App Services (コンテナーではない) での従来のAzureマネージド ID のシナリオでは、`SignedAssertionFromManagedIdentity`を使用します。

```yaml
- name: AzureAd__ClientCredentials__0__SourceType
  value: "SignedAssertionFromManagedIdentity"
- name: AzureAd__ClientCredentials__0__ManagedIdentityClientId
  value: "<managed-identity-client-id>"
```

**重要**: Azure以外の環境またはオンプレミス環境では`SignedAssertionFromManagedIdentity`を使用しないでください。 IMDS を介してマネージド ID Azure使用し、マネージド ID を提供するAzureコンピューティングでのみ機能します。 Azure以外のホストでは、ホスト エンドポイントAzureプローブされ、IMDS に対してタイムアウトします。これは、SDK が IMDS にハードコーディングされているような場合があります。 AKS を含む任意の場所の Kubernetes では、 `SignedAssertionFilePath`を使用します。 詳細については、 https://aka.ms/idweb/client-credentials

#### その他のリソース

すべての資格情報構成オプションとその使用方法の詳細については、microsoft-identity-abstractions-for-dotnet リポジトリの [CredentialDescription 仕様](https://github.com/AzureAD/microsoft-identity-abstractions-for-dotnet/blob/main/docs/credentialdescription.md) を参照してください。

### 資格情報の優先順位

優先順位ベースの選択を使用して複数の資格情報を構成します。

```yaml
# First priority - Key Vault certificate
- name: AzureAd__ClientCredentials__0__SourceType
  value: "KeyVault"
- name: AzureAd__ClientCredentials__0__KeyVaultUrl
  value: "https://prod-keyvault.vault.azure.net"
- name: AzureAd__ClientCredentials__0__KeyVaultCertificateName
  value: "prod-cert"

# Second priority - Client secret (fallback)
- name: AzureAd__ClientCredentials__1__SourceType
  value: "ClientSecret"
- name: AzureAd__ClientCredentials__1__ClientSecret
  valueFrom:
    secretKeyRef:
      name: app-secrets
      key: client-secret
```

Microsoft Entra ID認証 SDK (サイドカー) は、資格情報を数値順 (0、1、2 など) で評価し、正常に認証された最初の資格情報を使用します。

### ダウンストリーム API の構成

アプリケーションが代理 (OBO) トークン フローを使用して呼び出す必要があるダウンストリーム API を構成します。 Microsoft Entra ID認証 SDK (サイドカー) は、トークンの取得を管理し、これらの API 呼び出しの認証ヘッダーを提供します。 各ダウンストリーム API には、トークンの取得と HTTP 要求の処理に一意の構成名と特定のパラメーターが必要です。

各ダウンストリーム API は、そのベース URL、必要なスコープ、および省略可能なパラメーターを使用して定義します。 SDK は、受信ユーザー トークンを使用してトークンの取得を自動的に処理し、アプリケーションの API 呼び出しに適切な承認ヘッダーを提供します。

```yaml
- name: DownstreamApis__Graph__BaseUrl
  value: "https://graph.microsoft.com/v1.0"
- name: DownstreamApis__Graph__Scopes
  value: "User.Read Mail.Read"
- name: DownstreamApis__Graph__RelativePath
  value: "/me"

- name: DownstreamApis__MyApi__BaseUrl
  value: "https://api.contoso.com"
- name: DownstreamApis__MyApi__Scopes
  value: "api://myapi/.default"
```

| キー パターン | Description | 必須 |
| --- | --- | --- |
| `DownstreamApis__<Name>__BaseUrl` | API のベース URL | イエス |
| `DownstreamApis__<Name>__Scopes` | 要求するスペース区切りのスコープ | イエス |
| `DownstreamApis__<Name>__HttpMethod` | 既定の HTTP メソッド | いいえ (GET) |
| `DownstreamApis__<Name>__RelativePath` | 既定の相対パス | いいえ |
| `DownstreamApis__<Name>__RequestAppToken` | OBO の代わりにアプリ トークンを使用する | いいえ (false) |

### トークン取得オプション

トークン取得の動作を微調整します。

```yaml
- name: DownstreamApis__Graph__AcquireTokenOptions__Tenant
  value: "<specific-tenant-id>"

- name: DownstreamApis__Graph__AcquireTokenOptions__AuthenticationScheme
  value: "Bearer"

- name: DownstreamApis__Graph__AcquireTokenOptions__CorrelationId
  value: "<correlation-id>"
```

### 送信トークン取得用の署名付き HTTP 要求 (SHR) 構成

セキュリティ強化のために署名済み HTTP 要求を有効にします。

```yaml
- name: DownstreamApis__SecureApi__AcquireTokenOptions__PopPublicKey
  value: "<base64-encoded-public-key>"

- name: DownstreamApis__SecureApi__AcquireTokenOptions__PopClaims
  value: '{"custom_claim": "value"}'
```

### ログ記録の構成

ログ 記録レベルを構成します。

```yaml
- name: Logging__LogLevel__Default
  value: "Information"
- name: Logging__LogLevel__Microsoft.Identity.Web
  value: "Debug"
- name: Logging__LogLevel__Microsoft.AspNetCore
  value: "Warning"
```

### ASP.NET Core設定

```yaml
- name: ASPNETCORE_ENVIRONMENT
  value: "Production"
- name: ASPNETCORE_URLS
  value: "http://+:5000"
```

### Per-Request 構成のオーバーライド

すべてのトークン取得エンドポイントは、構成をオーバーライドするクエリ パラメーターを受け入れます。

```bash
# Override scopes
GET /AuthorizationHeader/Graph?optionsOverride.Scopes=User.Read&optionsOverride.Scopes=Mail.Read

# Request app token instead of OBO
GET /AuthorizationHeader/Graph?optionsOverride.RequestAppToken=true

GET /AuthorizationHeaderUnauthenticated/Graph?optionsOverride.RequestAppToken=true

# Override tenant
GET /AuthorizationHeader/Graph?optionsOverride.AcquireTokenOptions.Tenant=<tenant-id>

# Override relative path
GET /DownstreamApi/Graph?optionsOverride.RelativePath=me/messages

# Enable SHR for this request
GET /AuthorizationHeader/Graph?optionsOverride.AcquireTokenOptions.PopPublicKey=<base64-key>
```

### エージェント ID のオーバーライド

要求時にエージェント ID を指定します。

```bash
# Autonomous agent
GET /AuthorizationHeader/Graph?AgentIdentity=<agent-client-id>

# Autonomous agent with specific agent user identity (by username)
GET /AuthorizationHeader/Graph?AgentIdentity=<agent-client-id>&AgentUsername=user@contoso.com

# Autonomous agent with specific agent user identity (by object ID)
GET /AuthorizationHeader/Graph?AgentIdentity=<agent-client-id>&AgentUserId=<user-object-id>
```

**重要な規則:**

- `AgentUsername` と `AgentUserId` が必要 `AgentIdentity`
- `AgentUsername` と `AgentUserId` は相互に排他的です

詳細なセマンティクスについては [、「エージェント ID」を](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/agent-identities) 参照してください。

### 完全な構成例

次に、構成とシークレットを適切に分離して SDK をデプロイする方法を示す実稼働対応の例を示します。 この例では、機密性の低い設定に Kubernetes ConfigMaps を使用して複数のダウンストリーム API を構成し、資格情報をシークレットに安全に格納し、環境固有の構成を適用してセキュリティで保護されたデプロイを行う方法を示します。

このパターンは、構成データを機密性の高い資格情報から分離し、セキュリティを維持しながらさまざまな環境を効果的に管理できるようにすることで、Kubernetes のベスト プラクティスに従います。

#### Kubernetes ConfigMap

ConfigMap には、Entra ID設定、ダウンストリーム API、ログ レベルなど、SDK の機密性の高い構成設定が格納されます。

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: sidecar-config
data:
  ASPNETCORE_ENVIRONMENT: "Production"
  ASPNETCORE_URLS: "http://+:5000"
  
  AzureAd__Instance: "https://login.microsoftonline.com/"
  AzureAd__TenantId: "common"
  AzureAd__ClientId: "your-app-client-id"
  AzureAd__Scopes: "access_as_user"
  
  DownstreamApis__Graph__BaseUrl: "https://graph.microsoft.com/v1.0"
  DownstreamApis__Graph__Scopes: "User.Read Mail.Read"
  
  DownstreamApis__MyApi__BaseUrl: "https://api.contoso.com"
  DownstreamApis__MyApi__Scopes: "api://myapi/.default"
  
  Logging__LogLevel__Default: "Information"
  Logging__LogLevel__Microsoft.Identity.Web: "Debug"
```

#### Kubernetes シークレット

シークレットには、クライアント シークレットなどの機密性の高い資格情報が ConfigMap とは別に格納されます。

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: sidecar-secrets
type: Opaque
stringData:
  AzureAd__ClientCredentials__0__ClientSecret: "your-client-secret"
```

#### ConfigMap とシークレットを使用したデプロイ

Deployment によって ConfigMap とシークレットの両方が SDK コンテナーにマウントされ、構成と資格情報が適切に分離されます。

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp
spec:
  template:
    spec:
      containers:
      - name: sidecar
        image: mcr.microsoft.com/entra-sdk/auth-sidecar:1.0.0
        envFrom:
        - configMapRef:
            name: sidecar-config
        - secretRef:
            name: sidecar-secrets
```

### 環境固有の構成

環境固有の設定を構成して、デプロイ環境のセキュリティ、ログ記録、テナントの分離を調整します。 開発効率、ステージング検証、運用のセキュリティ要件のバランスを取るために、環境ごとに異なる構成アプローチが必要です。

#### 発達

```yaml
- name: ASPNETCORE_ENVIRONMENT
  value: "Development"
- name: Logging__LogLevel__Default
  value: "Debug"
- name: AzureAd__TenantId
  value: "<dev-tenant-id>"
```

#### Staging

```yaml
- name: ASPNETCORE_ENVIRONMENT
  value: "Staging"
- name: Logging__LogLevel__Default
  value: "Information"
- name: AzureAd__TenantId
  value: "<staging-tenant-id>"
```

#### 生産

```yaml
- name: ASPNETCORE_ENVIRONMENT
  value: "Production"
- name: Logging__LogLevel__Default
  value: "Warning"
- name: Logging__LogLevel__Microsoft.Identity.Web
  value: "Information"
- name: AzureAd__TenantId
  value: "<prod-tenant-id>"
- name: ApplicationInsights__ConnectionString
  value: "<app-insights-connection>"
```

### Validation

Microsoft Entra ID認証 SDK (サイドカー) は起動時に構成を検証し、次のエラーをログに記録します。

- 必要な設定がない (`TenantId`、 `ClientId`)
- 無効な資格情報の構成
- 形式が正しくないダウンストリーム API 定義
- 無効な URL またはスコープ形式

検証メッセージのコンテナー ログを確認します。

```bash
kubectl logs <pod-name> -c sidecar
```

### 資格情報のトラブルシューティング

#### 要求が予期せず IMDS またはタイムアウトに達する

**現象**: サイドカーがハングしたり、ダウンストリーム トークンを取得したりタイムアウトしたりすると、Azure以外のホストでも IMDS エンドポイント (`169.254.169.254`) への要求がログに表示されます。

**原因**: 資格情報は `SignedAssertionFromManagedIdentity`として構成されています。 そのソースの種類は、ホスト環境と IMDS Azure意図的にプローブします。これは、Azureの外部には存在しません。 これは構成の選択であり、 `SignedAssertionFilePath`からのフォールバックではありません。

**解決方法**:

1. 有効な`SourceType`が`SignedAssertionFromManagedIdentity`ではなく`SignedAssertionFilePath`されていることを確認します。
2. 環境変数または ConfigMap が再導入 `SignedAssertionFromManagedIdentity`オーバーライドされていないことを確認します。
3. `SignedAssertionFileDiskPath`が、サイドカー コンテナーにマウントされている投影されたトークンを指していることを確認します。
4. Azure以外の Kubernetes では、クラスターの OIDC 発行者と JWKS にパブリックに到達可能であり、一致する FIC がブループリント アプリケーションに存在することを確認します。

永続的な問題を報告する場合は、1 つの失敗した要求からサイドカーのバージョン、有効なランタイム構成、およびコンテナー ログをキャプチャします。

### ベスト プラクティス

1. **資格情報のシークレットの使用**: クライアント シークレットと証明書を Kubernetes シークレットまたはAzure Key Vaultに格納します。 関連項目 https://aka.ms/msidweb/client-credentials
2. **環境ごとに個別の構成**: ConfigMaps を使用して環境固有の設定を管理する
3. **適切なログ記録を有効にする**: 開発中はデバッグ ログを使用し、運用環境では情報/警告を使用する
4. **正常性チェックの構成**: 正常性チェック エンドポイントが正しく構成されていることを確認する
5. **コンテナーのワークロード ID を使用する**: コンテナー化されたデプロイ (AKS) の場合は、セキュリティ強化のためにクライアント シークレットよりも `SignedAssertionFilePath` を使用したMicrosoft Entra ワークロード ID を優先します
6. ** VM/App Services のマネージド ID の使用**: Azure VM と App Services の場合は、システム割り当てマネージド ID またはユーザー割り当てマネージド ID を使用します
7. **デプロイ時に検証**する: 運用環境のデプロイ前にステージングで構成をテストする
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/agent-id-sdk/endpoints"} -->
## エンドポイント リファレンス: Microsoft Entra ID Auth SDK (サイドカー) HTTP API

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/endpoints
- Service: msal / microsoft-identity-web
- Article date: 2026-09-15
- Summary: 要求/応答形式やエラー処理など、すべての Microsoft Entra ID Auth SDK (サイドカー) HTTP エンドポイントの完全なリファレンス。

このドキュメントでは、Microsoft Entra ID認証 SDK (サイドカー) によって公開される HTTP エンドポイントの完全なリファレンスを提供します。

### API 仕様

**OpenAPI 仕様**: `/openapi/v1.json` (開発環境) およびリポジトリで使用できます: https://github.com/AzureAD/microsoft-identity-web/blob/master/src/Microsoft.Identity.Web.Sidecar/OpenAPI/Microsoft.Identity.Web.Sidecar.json

これは次の目的で使用されます。

- クライアント コードを生成する
- 要求を検証する
- 使用可能なエンドポイントを検出する

### エンドポイントの概要

| エンドポイント | メソッド | 目的 | 認証が必要 |
| --- | --- | --- | --- |
| `/Validate` | GET | 受信ベアラー トークンまたはアプリ専用 PoP 署名済み HTTP 要求を検証し、要求を返す | イエス |
| `/AuthorizationHeader/{serviceName}` | GET | 受信トークン (存在する場合) を検証し、ダウンストリーム API の承認ヘッダーを取得する | イエス |
| `/AuthorizationHeaderUnauthenticated/{serviceName}` | GET | 受信ユーザー トークンなしで承認ヘッダー (アプリまたはエージェント ID) を取得する | イエス |
| `/DownstreamApi/{serviceName}` | GET、POST、PUT、PATCH、DELETE | 受信トークン (存在する場合) を検証し、自動トークン取得を使用してダウンストリーム API を呼び出す | イエス |
| `/DownstreamApiUnauthenticated/{serviceName}` | GET、POST、PUT、PATCH、DELETE | ダウンストリーム API を呼び出す (アプリまたはエージェント ID のみ) | イエス |
| `/healthz` | GET | 正常性プローブ (liveness/readiness) | いいえ |
| `/openapi/v1.json` | GET | OpenAPI 3.0 ドキュメント | いいえ (開発のみ) |

### Authentication

ベアラーは、認証されたエンドポイントの既定の認証スキームのままです。 `/Validate` エンドポイントは、アプリ専用の署名付き HTTP 要求 (SHR) 資格情報の`PoP`スキームも受け入れます。 認証されていないと明示的にマークされている場合を除き、他の認証済みエンドポイントには Bearer が必要です。

```http
GET /AuthorizationHeader/Graph
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
```

トークンは、テナント、対象ユーザー、発行者、スコープを含む、構成されたMicrosoft Entra ID設定に対して検証されます (有効になっている場合)。 PoP 要求の場合、サイドカーは、同じテナント、対象ユーザー、発行者、署名キーの構成に対して SHR 署名とその埋め込みアクセス トークンを検証します。

### `/Validate`

受信ベアラー トークンまたはアプリ専用の SHR PoP 資格情報を検証し、検証済みのトークンとその要求を返します。

#### リクエスト

ベアラー要求:

```http
GET /Validate HTTP/1.1
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
```

PoP 要求:

```http
GET /Validate HTTP/1.1
Authorization: PoP <signed-http-request>
original-method: GET
original-uri: https://api.contoso.com/data
```

PoP の場合は、 `original-method` と `original-uri` が必要です。 SHR 資格情報がサインオンされた元の要求のメソッドと絶対 URI を含める必要があります。 サイドカーはこれらの値を使用して要求バインディングを検証します。 `/Validate` 要求自体が署名されたリソース要求ではありません。

#### 成功した応答 (200)

```json
{
  "protocol": "Bearer",
  "token": "eyJ0eXAiOiJKV1QiLCJhbGc...",
  "claims": {
    "aud": "api://your-api-id",
    "iss": "https://sts.windows.net/tenant-id/",
    "iat": 1234567890,
    "nbf": 1234567890,
    "exp": 1234571490,
    "acr": "1",
    "appid": "client-id",
    "appidacr": "1",
    "idp": "https://sts.windows.net/tenant-id/",
    "oid": "user-object-id",
    "tid": "tenant-id",
    "scp": "access_as_user",
    "sub": "subject",
    "ver": "1.0"
  }
}
```

`protocol`値はベアラー要求に対して`Bearer`され、PoP 要求の`PoP`されます。 PoP の場合、 `token` には、署名された HTTP 要求に埋め込まれた検証済みアクセス トークンが含まれます。 返される要求は、アクセス トークンのバージョンとテナントの構成によって異なります。

Important

受信 PoP 検証では、アプリ専用のクライアント資格情報アクセス トークンがサポートされます。 委任されたトークンまたはユーザー トークン、OBO またはアクター トークン フロー、mTLS PoP、または PFT/CDT over-PoP はサポートされていません。 `AzureAd__Scopes` これらのトークンには `scp` 要求が含まれていないため、アプリ専用の PoP 要求には適用されません。 アプリケーションは、そのロール、アプリケーションのアクセス許可、またはその他の信頼された要求を使用して、返されたアプリ ID を承認する必要があります。

#### エラーの例

不足している承認資格情報は、エンドポイントを実行する前に拒否されます。

```http
HTTP/1.1 401 Unauthorized
WWW-Authenticate: Bearer
```

無効なベアラー トークンは、ベアラー チャレンジを返します。

```http
HTTP/1.1 401 Unauthorized
WWW-Authenticate: Bearer error="invalid_token"
```

失敗した PoP 要求には、次のチャレンジが含まれます。 `/Validate`は両方の認証スキームを受け入れるため、応答にはベアラー チャレンジを含めることもできます。

```http
HTTP/1.1 401 Unauthorized
WWW-Authenticate: PoP error="invalid_token"
```

### `/AuthorizationHeader/{serviceName}`

構成されたダウンストリーム API のアクセス トークンを取得し、承認ヘッダー値として返します。 ユーザー ベアラー トークンが受信で提供されている場合は、OBO (委任) が使用されます。それ以外の場合は、アプリ コンテキスト パターンが適用されます (有効な場合)。

#### Path パラメーター

- `serviceName` – 構成内のダウンストリーム API の名前

#### クエリ パラメーター

##### 標準のオーバーライド

| パラメーター | タイプ | Description | Example |
| --- | --- | --- | --- |
| `optionsOverride.Scopes` | string[] | 構成されたスコープをオーバーライドする (繰り返し可能) | `?optionsOverride.Scopes=User.Read&optionsOverride.Scopes=Mail.Read` |
| `optionsOverride.RequestAppToken` | ブーリアン | アプリ専用トークンを強制する (OBO をスキップする) | `?optionsOverride.RequestAppToken=true` |
| `optionsOverride.AcquireTokenOptions.Tenant` | 文字列 | テナント ID をオーバーライドする | `?optionsOverride.AcquireTokenOptions.Tenant=tenant-guid` |
| `optionsOverride.AcquireTokenOptions.PopPublicKey` | 文字列 | PoP/SHR (base64 公開キー) を有効にする | `?optionsOverride.AcquireTokenOptions.PopPublicKey=base64key` |
| `optionsOverride.AcquireTokenOptions.PopClaims` | 文字列 | 追加の PoP 要求 (JSON) | `?optionsOverride.AcquireTokenOptions.PopClaims={"nonce":"abc"}` |

##### エージェント ID

| パラメーター | タイプ | Description | Example |
| --- | --- | --- | --- |
| `AgentIdentity` | 文字列 | エージェント アプリ (クライアント) ID | `?AgentIdentity=11111111-2222-3333-4444-555555555555` |
| `AgentUsername` | 文字列 | ユーザー プリンシパル名 (委任されたエージェント) | `?AgentIdentity=<id>&AgentUsername=user@contoso.com` |
| `AgentUserId` | 文字列 | ユーザー オブジェクト ID (委任されたエージェント) | `?AgentIdentity=<id>&AgentUserId=aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee` |

準則：

- `AgentUsername`または`AgentUserId``AgentIdentity` (ユーザー エージェント) が必要です。
- `AgentUsername` と `AgentUserId` は相互に排他的です。
- `AgentIdentity` alone = 自律エージェント。
- `AgentIdentity` + ユーザー受信トークン = 委任されたエージェント。

#### 例示

基本的な要求:

```http
GET /AuthorizationHeader/Graph HTTP/1.1
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
```

```http
GET /AuthorizationHeader/Graph?optionsOverride.RequestAppToken=true HTTP/1.1
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
```

```http
GET /AuthorizationHeader/Graph?AgentIdentity=agent-id HTTP/1.1
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
```

#### [応答]

```json
{
  "authorizationHeader": "Bearer eyJ0eXAiOiJKV1QiLCJhbGc..."
}
```

PoP/SHR 応答:

```json
{
  "authorizationHeader": "PoP eyJ0eXAiOiJhdCtqd3QiLCJhbGc..."
}
```

### `/AuthorizationHeaderUnauthenticated/{serviceName}`

`/AuthorizationHeader/{serviceName}`と同じ動作とパラメーターですが、受信ユーザー トークンは必要ありません。 ユーザー コンテキストなしでアプリ専用または自律/エージェント ID を取得するために使用されます。 ユーザー トークンを検証するオーバーヘッドを回避します。

#### リクエスト

```http
GET /AuthorizationHeaderUnauthenticated/Graph HTTP/1.1
```

#### [応答]

```json
{
  "authorizationHeader": "Bearer eyJ0eXAiOiJKV1QiLCJhbGc..."
}
```

### `/DownstreamApi/{serviceName}`

アクセス トークンを取得し、ダウンストリーム API への HTTP 要求を実行します。 ダウンストリーム応答から状態コード、ヘッダー、および本文を返します。 ユーザー OBO、アプリ専用、またはエージェント ID パターンをサポートします。

#### Path パラメーター

- `serviceName` – ダウンストリーム API 名を構成しました。

#### 追加のクエリ パラメーター ( `/AuthorizationHeader` パラメーターに加えて)

| パラメーター | タイプ | Description | Example |
| --- | --- | --- | --- |
| `optionsOverride.HttpMethod` | 文字列 | HTTP メソッドをオーバーライドする | `?optionsOverride.HttpMethod=POST` |
| `optionsOverride.RelativePath` | 文字列 | 構成された BaseUrl への相対パスの追加 | `?optionsOverride.RelativePath=me/messages` |
| `optionsOverride.CustomHeader.<Name>` | 文字列 | カスタム ヘッダーを追加する | `?optionsOverride.CustomHeader.X-Custom=value` |

#### 要求本文の転送

本文は変更されずに渡されます。

```http
POST /DownstreamApi/Graph?optionsOverride.RelativePath=me/messages HTTP/1.1
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
Content-Type: application/json
```

```json
{ 
  "subject": "Hello", 
   "body": { "contentType": "Text", "content": "Hello world" } 
}
```

#### [応答]

```json
{
  "statusCode": 200,
  "headers": {
    "content-type": "application/json"
  },
  "content": "{\"@odata.context\":\"...\",\"displayName\":\"...\"}"
}
```

エラーは、 `/AuthorizationHeader` とダウンストリーム API エラー状態コードを反映します。

### `/DownstreamApiUnauthenticated/{serviceName}`

`/DownstreamApi/{serviceName}`と同じですが、受信ユーザー トークンは検証されません。 アプリ専用または自律的なエージェント操作に使用します。

### /healthz

基本的な正常性プローブ エンドポイント。

#### [応答]

**正常 (200):**

```http
HTTP/1.1 200 OK
```

**異常 (503):**

```http
HTTP/1.1 503 Service Unavailable
```

### `/openapi/v1.json`

OpenAPI 3.0 仕様を返します (開発環境のみ)。 次の用途に使用します。

- クライアント コードを生成する
- 要求を検証する
- エンドポイントの検出

### 一般的なエラー パターン

#### 不適切な要求 (400)

サービス名がありません:

```json
// 400 Bad Request - Missing service name
{ "type": "https://tools.ietf.org/html/rfc7231#section-6.5.1", "title": "Bad Request", "status": 400, "detail": "Service name is required" }

// 400 Bad Request - Invalid agent combination
{ "type": "https://tools.ietf.org/html/rfc7231#section-6.5.1", "title": "Bad Request", "status": 400, "detail": "AgentUsername and AgentUserId are mutually exclusive" }

// 401 Unauthorized - Invalid token
{ "type": "https://tools.ietf.org/html/rfc7231#section-6.5.1", "title": "Unauthorized", "status": 401 }

// 403 Forbidden - Missing scope
{ "type": "https://tools.ietf.org/html/rfc7231#section-6.5.3", "title": "Forbidden", "status": 403, "detail": "The scope 'access_as_user' is required" }

// 404 Not Found - Service not configured
{ "type": "https://tools.ietf.org/html/rfc7231#section-6.5.4", "title": "Not Found", "status": 404, "detail": "Downstream API 'UnknownService' not configured" }

// 500 Internal Server Error - Token acquisition failure
{ "type": "https://tools.ietf.org/html/rfc7231#section-6.6.1", "title": "Internal Server Error", "status": 500, "detail": "Failed to acquire token for downstream API" }
```

#### MSAL エラーの例

```json
{ "type": "https://tools.ietf.org/html/rfc7231#section-6.6.1", "title": "Internal Server Error", "status": 500, "detail": "MSAL.NetCore.invalid_grant: AADSTS50076: Due to a configuration change ...", "extensions": { "errorCode": "invalid_grant", "correlationId": "..." } }
```

### 完全なオーバーライド リファレンス

```text
optionsOverride.Scopes=<scope>     # Repeatable
optionsOverride.RequestAppToken=<true|false>
optionsOverride.BaseUrl=<url>
optionsOverride.RelativePath=<path>
optionsOverride.HttpMethod=<method>
optionsOverride.AcquireTokenOptions.Tenant=<tenant-id>
optionsOverride.AcquireTokenOptions.AuthenticationScheme=<scheme>
optionsOverride.AcquireTokenOptions.CorrelationId=<guid>
optionsOverride.AcquireTokenOptions.PopPublicKey=<base64-key>
optionsOverride.AcquireTokenOptions.PopClaims=<json>
optionsOverride.CustomHeader.<Name>=<value>

AgentIdentity=<agent-client-id>
AgentUsername=<user-upn>            # Requires AgentIdentity
AgentUserId=<user-object-id>        # Requires AgentIdentity
```

#### オーバーライドの例

**スコープをオーバーライドします**。

```http
GET /AuthorizationHeader/Graph?optionsOverride.Scopes=User.Read&optionsOverride.Scopes=Mail.Read HTTP/1.1
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
```

### レート制限

Microsoft Entra ID認証 SDK (サイドカー) 自体では、レート制限は課されません。 有効な制限は次のとおりです。

1. Microsoft Entra ID トークン サービスの調整 (SDK がトークンをキャッシュする場合は発生しません)
2. ダウンストリーム API の制限
3. トークン キャッシュの効率 (取得量を削減)

### ベスト プラクティス

1. アドホック オーバーライドよりも構成を優先します。
2. サービス名は静的および宣言型のままにします。
3. 一時的な障害 (HTTP 500/503) の再試行ポリシーを実装します。
4. 呼び出す前にエージェント パラメーターを検証します。
5. サービス間でトレースするためのログ関連付け ID。
6. トークン取得の待機時間とエラー率を監視します。
7. オーケストレーション プラットフォームで正常性プローブを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/agent-id-sdk/faq"} -->
## Microsoft Entra ID認証 SDK (サイドカー) についてよく寄せられる質問

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/faq
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: Microsoft Entra ID認証 SDK (サイドカー) に関する一般的な質問への回答。

### 一般的な質問

#### Microsoft Entra ID認証 SDK (サイドカー) とは

Microsoft Entra ID認証 SDK (サイドカー) は、トークンの取得、検証、および安全なダウンストリーム API 呼び出しを処理するコンテナー化された Web サービスです。 アプリケーションと共にコンパニオン コンテナーとして実行されるため、ID ロジックを専用サービスにオフロードできます。 SDK で ID 操作を一元化することで、各サービスに複雑なトークン管理ロジックを埋め込む必要がなくなり、コードの重複と潜在的なセキュリティの脆弱性が軽減されます。

#### Microsoftの代わりにMicrosoft Entra ID認証 SDK (サイドカー) を使用する理由。Identity.Web?

| 特徴 | Microsoft。Identity.Web | Microsoft Entra ID Auth SDK (サイドカー) |
| --- | --- | --- |
| **言語サポート** | C# / .NETのみ | 任意の言語 (HTTP) |
| **Deployment** | インプロセス ライブラリ | 個別のコンテナー |
| **トークンの取得** | 直接 MSAL.NET | HTTP API 経由 |
| **トークン キャッシュ** | メモリ内、分散 | メモリ内、分散 |
| **OBO フロー** | ネイティブ サポート | HTTP エンドポイント経由 |
| **クライアント資格情報** | ネイティブ サポート | HTTP エンドポイント経由 |
| **マネージド ID** | 直接サポート | 直接サポート |
| **エージェント ID** | 拡張機能を使用する | クエリ パラメーター |
| **トークンの検証** | ミドルウェア | /Validate endpoint |
| **ダウンストリーム API** | IDownstreamApi | /DownstreamApi エンドポイント |
| **Microsoft Graph** | Graph SDK の統合 | DownstreamApi 経由 |
| **パフォーマンス** | インプロセス (最速) | HTTP オーバーヘッド |
| **Configuration** | `appsettings.json` とコード | `appsettings.json` 環境変数と環境変数 |
| **デバッグ** | 標準.NETデバッグ | コンテナーのデバッグ |
| **ホット リロード** | .NET ホット リロード (ホットリロード機能) | コンテナーの再起動 |
| **パッケージの更新** | NuGet パッケージ | コンテナー イメージ |
| **ライセンス** | MIT | MIT |

詳細なガイダンスについては、 [比較ガイド](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/comparison) を参照してください。

#### Microsoft Entra ID認証 SDK (サイドカー) は運用環境に対応していますか?

はい。SDK は運用環境の準備ができています。 最新のリリースの状態と運用環境の準備のガイドラインについては、[GitHub リリース](https://github.com/AzureAD/microsoft-identity-web/releases)を参照してください。

#### コンテナー イメージは使用できますか?

はい - 使用可能なイメージとバージョン タグについては [、インストール ガイド](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/installation#container-image) を参照してください。

#### Kubernetes の外部で SDK を実行できますか?

はい - Docker Compose またはその他のコンテナー環境 (Docker Compose、Azure Container Instances、AWS ECS/Fargate、スタンドアロン Docker) で SDK を実行する手順については、[>インストール ガイド](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/installation#docker-compose) を参照してください。

#### SDK はどのネットワーク ポートを使用しますか?

既定のポート: `5000` (構成可能)

SDK にはアプリケーション コンテナーからのみアクセスでき、外部に公開されることはありません。

### デプロイメント

デプロイ オプション、リソース要件、Docker Compose や Kubernetes などのコンテナー プラットフォームとの統合について説明します。

#### リソースの要件は何ですか?

詳細なリソース要件については、 [インストール ガイド](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/installation#resource-requirements) を参照してください。

#### Docker compose で SDK を使用できますか?

はい - Docker Compose の例の [インストール ガイド](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/installation#docker-compose) を参照してください。

#### マネージド ID を使用して AKS にデプロイする方法

はい - マネージド ID を使用した Azure Kubernetes Service (AKS) の のインストール ガイド セクションに従います。

### コンフィギュレーション

デプロイ要件に合わせて、資格情報、ダウンストリーム API、要求オーバーライドなどの SDK 設定を構成します。

#### 構成参照は利用できますか?

はい - 詳細な構成オプションについては、 [構成リファレンスを参照](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/configuration#required-configuration) してください。

#### クライアント シークレットまたは証明書を使用する必要がありますか?

クライアント シークレットよりも**証明書を優先**する:

- セキュリティの強化
- 回転が簡単
- Microsoftのおすすめ

**Best**: Azureでマネージド ID を使用します (資格情報は必要ありません)

ガイダンスについては、「 [セキュリティのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/security) 」を参照してください。

#### 複数のダウンストリーム API を構成できますか?

Yes. それぞれに独自のセクションを構成します。

```yaml
DownstreamApis__Graph__BaseUrl: "https://graph.microsoft.com/v1.0"
DownstreamApis__Graph__Scopes: "User.Read"

DownstreamApis__MyApi__BaseUrl: "https://api.contoso.com"
DownstreamApis__MyApi__Scopes: "api://myapi/.default"
```

#### 要求ごとに構成をオーバーライドする方法

エンドポイントでクエリ パラメーターを使用する:

```bash
# Override scopes
GET /AuthorizationHeader/Graph?optionsOverride.Scopes=User.Read

# Request app token instead of OBO
GET /AuthorizationHeader/Graph?optionsOverride.RequestAppToken=true

# Override relative path
GET /DownstreamApi/Graph?optionsOverride.RelativePath=me/messages
```

すべてのオプションについては、「 [構成リファレンス」を参照](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/configuration) してください。

### エージェントのアイデンティティ

エージェント ID を使用すると、エージェント アプリケーションが適切なコンテキスト分離とスコープを使用して、自律的に、またはユーザーの代わりに動作できるシナリオが可能になります。

#### エージェント ID とは

エージェント ID を使用すると、エージェント アプリケーションが次のいずれかの動作をするシナリオが有効になります。

- **自律的 -** 独自のアプリケーション コンテキストで
- **対話型** - 呼び出したユーザーに代わって

包括的なドキュメントについては [、エージェント ID](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/agent-identities) を参照してください。

#### 自律エージェント モードを使用する必要があるタイミング

自律エージェント モードは、次の場合に使用します。

- ユーザー コンテキストを使用しないバッチ処理
- バックグラウンド タスク
- システム間操作
- スケジュールされたジョブ

例:

```bash
GET /AuthorizationHeader/Graph?AgentIdentity=<agent-client-id>
```

#### 対話型エージェント モードを使用する必要があるタイミング

次の場合は、委任されたエージェント モードを使用します。

- 対話型エージェント アプリケーション
- ユーザーに代わって動作する AI アシスタント
- ユーザー スコープの自動化
- カスタマイズされたワークフロー

例:

```bash
GET /AuthorizationHeader/Graph?AgentIdentity=<agent-client-id>&AgentUsername=user@contoso.com
```

#### AgentIdentity なしで AgentUsername を使用できないのはなぜですか?

`AgentUsername` は、エージェントが代理で操作するユーザーを指定する修飾子です。 使用するエージェント コンテキストを指定するには、 `AgentIdentity` が必要です。 `AgentIdentity`しないと、パラメーターには意味がありません。

#### AgentUsername と AgentUserId が相互に排他的なのはなぜですか?

同じユーザーを識別するには、次の 2 つの方法があります。

- `AgentUsername` - ユーザー プリンシパル名 (UPN)
- `AgentUserId` - オブジェクト ID (OID)

両方を許可すると、あいまいさが生じします。 シナリオに合ったものを選択します。

- ユーザーの UPN がある場合に `AgentUsername` を使用する
- ユーザーのオブジェクト ID がある場合に `AgentUserId` を使用する

### API の使用

SDK では、認証されたフローと認証されていないフローの両方をサポートする、トークンの取得、検証、およびダウンストリーム API 呼び出し用の HTTP エンドポイントがいくつか公開されています。

#### SDK で公開されているエンドポイントは何ですか?

- `/Validate` - トークンを検証し、要求を返す
- `/AuthorizationHeader/{serviceName}` - トークンを使用して承認ヘッダーを取得する
- `/AuthorizationHeaderUnauthenticated/{serviceName}` - 受信ユーザー トークンなしでトークンを取得する
- `/DownstreamApi/{serviceName}` - ダウンストリーム API を直接呼び出す
- `/DownstreamApiUnauthenticated/{serviceName}` - 受信ユーザー トークンを使用せずにダウンストリーム API を呼び出す
- `/healthz` - 正常性プローブ

詳細については、「 [エンドポイント リファレンス」](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/endpoints) を参照してください。

#### 認証されたエンドポイントと認証されていないエンドポイントの違いは何ですか?

**認証済み**: `Authorization` ヘッダーにベアラー トークンを要求する (OBO フローの場合) **認証されていない**: 受信トークンを検証しない (アプリ専用またはエージェントのシナリオの場合)

#### ユーザー トークンを検証する方法

```bash
GET /Validate
Authorization: Bearer <user-token>
```

応答には、トークンからのすべての要求が含まれます。

#### ダウンストリーム API のアクセス トークンを取得するにはどうすればよいですか?

```bash
GET /AuthorizationHeader/Graph
Authorization: Bearer <user-token>
```

応答には、ダウンストリーム API で使用できる承認ヘッダーが含まれます。

#### 要求ごとに HTTP メソッドまたはパスをオーバーライドできますか?

はい。クエリ パラメーターを使用します。

```bash
# Override method
GET /DownstreamApi/Graph?optionsOverride.HttpMethod=POST

# Override path
GET /DownstreamApi/Graph?optionsOverride.RelativePath=me/messages
```

### トークンのキャッシュ

SDK は、パフォーマンスを最適化し、冗長なトークン取得要求を減らすために、トークンをメモリに自動的にキャッシュします。

#### SDK はトークンをキャッシュしますか?

はい - SDK は既定でメモリにトークンをキャッシュします。

#### トークンはどのくらいの期間キャッシュされますか?

トークンは有効期限が近くまでキャッシュされ、自動的に更新されます。 正確な期間は、トークンの有効期間 (通常、Entra ID トークンの場合は 1 時間) によって異なります。

#### キャッシュを無効にすることはできますか?

トークン キャッシュは自動で最適化されています。 現在、無効にするオプションはありません。

#### トークン キャッシュは SDK インスタンス間で共有されますか?

いいえ - 各 SDK インスタンスは、独自のメモリ内キャッシュを維持します。 高可用性デプロイでは、各ポッドには独立したキャッシュがあります。

### セキュリティ

セキュリティで保護された SDK デプロイは、マネージド ID の使用、ネットワークの分離、適切な資格情報の処理など、Microsoft Entra ID とデータ保護のベスト プラクティスに従います。

#### SDK を実行しても安全ですか?

はい - [セキュリティのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/security#is-it-safe-to-run-the-sdk) については、セキュリティのベスト プラクティスを参照してください。

#### SDK を外部で公開する必要がありますか?

**なし** - SDK には、アプリケーション コンテナーからのみアクセスできます。 セキュリティのベスト プラクティスの詳細については、「 [セキュリティのベスト プラクティス」](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/security)を参照してください。

#### SDK をセキュリティで保護する方法

包括的なガイダンスについては、「 [セキュリティのベスト プラクティス」](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/security#best-practices-checklist) を参照してください。

#### どのような資格情報を使用する必要がありますか?

基本設定の順序:

1. **管理 ID** (Azure) - 最も安全、資格情報なし
2. **証明書** - セキュリティで保護され、ローテーション可能
3. **クライアント シークレット** - あまり優先されません。セキュリティで保護されたコンテナーに保持する

#### SDK のコンプライアンスが認定されていますか?

[GitHub リポジトリ](https://github.com/AzureAD/microsoft-identity-web)で現在のコンプライアンス情報を確認します。

### Performance

SDK のパフォーマンスは、トークン キャッシュの有効性とネットワークラウンドトリップ待機時間に依存し、キャッシュされたトークンの一般的な応答時間は 10 から 50 ミリ秒です。

#### SDK を使用した場合のパフォーマンスへの影響は何ですか?

一般的な HTTP ラウンド トリップ: 10 ~ 50 ミリ秒

トークン キャッシュにより、取得の繰り返しが最小限に抑えられます。 最初の要求は低速 (トークンの取得) であり、後続の要求ではキャッシュされたトークンが使用されます。

#### SDK のパフォーマンスはインプロセス ライブラリとどのように比較されますか?

インプロセス ライブラリの方が高速ですが (ネットワークラウンドトリップはありません)、SDK では次の機能が提供されます。

- 言語に依存しないアクセス
- 一元化された構成
- サービス間の共有トークン キャッシュ
- 簡素化スケーリング

詳細については、 [比較ガイド](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/comparison) を参照してください。

#### SDK を水平方向にスケーリングできますか?

Yes. Kubernetes Deployment を使用して複数の SDK レプリカをデプロイします。 各ポッドは、独立したトークン キャッシュを維持します。

### Migration

Microsoftからの移動。IDENTITy.Web to the SDK には、複数言語のサポート、一元化された構成、サービス間の簡素化されたスケーリングの利点があります。

#### Microsoftから移行することはできますか。Microsoft Entra ID Auth SDK (サイドカー) への Identity.Web?

はい - 移行の詳細な手順については、 [比較ガイド](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/comparison#migration-guidance) を参照してください

### Support

公式チャネルを使用して、問題に関するヘルプを表示したり、追加のドキュメントを見つけたり、コミュニティ リソースにアクセスしたりできます。

#### バグを報告する場所

Entra ID テンプレートを使用して、[GitHub リポジトリ](https://github.com/AzureAD/microsoft-identity-web/issues)に関する問題を報告します。

### トラブルシューティング

SDK で問題が発生した場合は、トラブルシューティング ガイドの診断手順、一般的な問題、および解決策に関する包括的な [トラブルシューティング ガイドを](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/troubleshooting)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msidweb/agent-id-sdk/installation"} -->
## インストール ガイド: Microsoft Entra ID Auth SDK (サイドカー) をデプロイする

- Source: https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/installation
- Service: msal / microsoft-identity-web
- Article date: 2026-04-19
- Summary: Kubernetes、Docker、および Azure 環境で Microsoft Entra ID Auth SDK (サイドカー) コンテナーを取得してデプロイする手順。

Microsoft Entra ID認証 SDK (サイドカー) は、アプリケーションのセキュリティで保護されたトークンの取得を効率化する、すぐにデプロイできるコンテナー化された認証サービスです。 このインストール ガイドでは、Kubernetes、Docker、および Azure 環境に SDK コンテナーをデプロイする手順について説明します。これにより、アプリケーション コードに機密性の高い資格情報を直接埋め込む必要がなくなります。

### [前提条件]

- [Microsoft Artifact Registry](https://mcr.microsoft.com/)へのアクセス
- コンテナー ランタイム (Docker、Kubernetes、またはコンテナー サービス)
- [Microsoft Entra 管理センター](https://entra.microsoft.com)で、この組織ディレクトリ内のアカウントのみに構成された新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/msidweb/getting-started/quickstart-webapp) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- アプリケーションの資格情報:
    - セキュリティで保護されたクライアント シークレットまたは証明書 (例: Azure Key Vault)
- Azureデプロイの場合: [Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)または[Azure ポータルへのアクセス](https://portal.azure.com/)

### コンテナー イメージ

Microsoft Entra ID認証 SDK (サイドカー) は、成果物レジストリからコンテナー イメージとして配布されます

```text
mcr.microsoft.com/entra-sdk/auth-sidecar
```

Note

受信 SHR PoP 検証には、サイドカー バージョン `1.1.2-preview` 以降が必要です。

### デプロイ パターン

Microsoft Entra ID認証 SDK (サイドカー) は、アプリケーションと共にコンパニオン コンテナーとして実行するように設計されています。 これにより、アプリケーションは HTTP 呼び出しを介してトークンの取得と管理を SDK にオフロードし、アプリケーション コードから機密性の高い資格情報を保持できます。 一般的なデプロイ パターンを次に示します。特定の環境に合わせて調整する必要があります。

#### Kubernetes パターン

セキュリティで保護されたポッドローカル通信のために、アプリケーション コンテナーと同じポッドに Microsoft Entra ID Auth SDK (サイドカー) をデプロイします。 このパターンにより、認証サービスがアプリと共に実行され、アプリケーション コードから資格情報を分離したまま、HTTP ベースのトークンの迅速な取得が可能になります。

```yaml
apiVersion: v1
kind: Pod
metadata:
  # Your application container
  name: myapp
spec:
  containers:
  - name: app
    image: myregistry/myapp:latest
    ports:
    - containerPort: 8080
    env:
    - name: SIDECAR_URL
      value: "http://localhost:5000"
  # Microsoft Entra ID Auth SDK (sidecar) container
  - name: sidecar
    image: mcr.microsoft.com/entra-sdk/auth-sidecar:1.0.0
    ports:
    - containerPort: 5000
    env:
    - name: AzureAd__TenantId
      value: "your-tenant-id"
    - name: AzureAd__ClientId
      value: "your-client-id"
    - name: AzureAd__ClientCredentials__0__SourceType
      value: "KeyVault"
    - name: AzureAd__ClientCredentials__0__KeyVaultUrl
      value: "https://your-keyvault.vault.azure.net"
    - name: AzureAd__ClientCredentials__0__KeyVaultCertificateName
      value: "your-cert-name"
```

#### Kubernetes のデプロイ

Azure Kubernetes Services をターゲットにする場合は、[Azure の Kubernetes チュートリアル - Azure Kubernetes Service (AKS) 用にアプリケーションを準備する](https://learn.microsoft.com/ja-jp/azure/aks/tutorial-kubernetes-prepare-app?tabs=azure-cli) を参照してください。 このパターンでは、デプロイ リソースを使用してアプリケーションと Auth SDK (サイドカー) コンテナー Microsoft Entra ID管理し、スケーリングと更新を可能にします。 デプロイでは、正常性チェックとリソースの割り当ても処理され、運用環境での安全な操作が保証されます。

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      serviceAccountName: myapp-sa
      containers:
      - name: app
        image: myregistry/myapp:latest
        ports:
        - containerPort: 8080
        env:
        - name: SIDECAR_URL
          value: "http://localhost:5000"
        resources:
          requests:
            memory: "256Mi"
            cpu: "250m"
          limits:
            memory: "512Mi"
            cpu: "500m"
      
      - name: sidecar
        image: mcr.microsoft.com/entra-sdk/auth-sidecar:1.0.0
        ports:
        - containerPort: 5000
        env:
        - name: AzureAd__TenantId
          valueFrom:
            configMapKeyRef:
              name: app-config
              key: tenant-id
        - name: AzureAd__ClientId
          valueFrom:
            configMapKeyRef:
              name: app-config
              key: client-id
        - name: AzureAd__Instance
          value: "https://login.microsoftonline.com/"
        resources:
          requests:
            memory: "128Mi"
            cpu: "100m"
          limits:
            memory: "256Mi"
            cpu: "250m"
        livenessProbe:
          httpGet:
            path: /healthz
            port: 5000
          initialDelaySeconds: 10
          periodSeconds: 10
        readinessProbe:
          httpGet:
            path: /healthz
            port: 5000
          initialDelaySeconds: 5
          periodSeconds: 5
```

#### Docker Compose

Docker 環境で作業する場合は、Docker Compose を使用してマルチコンテナー アプリケーションを定義して実行できます。 次の例では、ローカル開発環境でアプリケーション コンテナーと共に Microsoft Entra ID Auth SDK (サイドカー) を設定する方法を示します。

```yaml
version: '3.8'

services:
  app:
    image: myregistry/myapp:latest
    ports:
      - "8080:8080"
    environment:
      - AzureAd__TenantId=${TENANT_ID}
      - AzureAd__ClientId=${CLIENT_ID}
      - AzureAd__ClientCredentials__0__SourceType=ClientSecret
      - AzureAd__ClientCredentials__0__ClientSecret=${CLIENT_SECRET}
    networks:
      - app-network

networks:
  app-network:
    driver: bridge
```

### Azure Kubernetes サービス (AKS) におけるマネージド ID

AKS にデプロイするときは、[Azureマネージド ID を](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/overview)使用して、構成に資格情報を格納せずに Microsoft Entra ID Auth SDK (サイドカー) を認証できます。 まず、AKS クラスターでMicrosoft Entra ワークロード ID を有効にし、マネージド ID のフェデレーション ID 資格情報を作成する必要があります。 次に、認証にマネージド ID を使用するように SDK を構成します。

#### 手順 1: マネージド ID を作成する

マネージド ID を作成し、適切なアクセス許可を割り当てる

```bash
# Create managed identity
az identity create \
  --resource-group myResourceGroup \
  --name myapp-identity

# Get the identity details
IDENTITY_CLIENT_ID=$(az identity show \
  --resource-group myResourceGroup \
  --name myapp-identity \
  --query clientId -o tsv)

IDENTITY_OBJECT_ID=$(az identity show \
  --resource-group myResourceGroup \
  --name myapp-identity \
  --query principalId -o tsv)
```

#### 手順 2: アクセス許可を割り当てる

ダウンストリーム API にアクセスするためのアクセス許可をマネージド ID に付与します。

```bash
# Example: Grant permission to call Microsoft Graph
az ad app permission add \
  --id $IDENTITY_CLIENT_ID \
  --api 00000003-0000-0000-c000-000000000000 \
  --api-permissions e1fe6dd8-ba31-4d61-89e7-88639da4683d=Scope
```

#### 手順 3: ワークロード ID を構成する

ワークロード ID フェデレーションを使用してサービス アカウントを作成します。

```bash
export AKS_OIDC_ISSUER=$(az aks show \
  --resource-group myResourceGroup \
  --name myAKSCluster \
  --query "oidcIssuerProfile.issuerUrl" -o tsv)

az identity federated-credential create \
  --name myapp-federated-identity \
  --identity-name myapp-identity \
  --resource-group myResourceGroup \
  --issuer $AKS_OIDC_ISSUER \
  --subject system:serviceaccount:default:myapp-sa
```

#### 手順 4: ワークロード ID を使用してデプロイする

次のデプロイ例では、Microsoft Entra ID Auth SDK (サイドカー) は、ファイル ベースのトークン プロジェクションを使用した認証にMicrosoft Entra ワークロード ID を使用するように構成されています。 `SignedAssertionFilePath`資格情報タイプは、ワークロード アイデンティティ Webhook によって投影されたファイルからトークンを読み取ります。

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: myapp-sa
  namespace: default
  annotations:
    azure.workload.identity/client-id: "<MANAGED_IDENTITY_CLIENT_ID>"

---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp-deployment
spec:
  template:
    metadata:
      labels:
        azure.workload.identity/use: "true"
    spec:
      serviceAccountName: myapp-sa
      containers:
      - name: app
        image: myregistry/myapp:latest
        env:
        - name: SIDECAR_URL
          value: "http://localhost:5000"
      
      - name: sidecar
        image: mcr.microsoft.com/entra-sdk/auth-sidecar:1.0.0
        ports:
        - containerPort: 5000
        env:
        - name: AzureAd__TenantId
          value: "your-tenant-id"
        - name: AzureAd__ClientId
          value: "<MANAGED_IDENTITY_CLIENT_ID>"
        
        # Workload Identity credentials - uses file-based token projection
        - name: AzureAd__ClientCredentials__0__SourceType
          value: "SignedAssertionFilePath"
```

**注**: ワークロード ID webhook は、ポッドに必要なラベルとサービス アカウントの注釈がある場合に、フェデレーション トークンを `/var/run/secrets/azure/tokens/azure-identity-token` または環境変数に自動的に投影します。

### ネットワーク構成

承認されていないアクセスを制限しながら、Microsoft Entra ID認証 SDK (サイドカー) と外部サービス間の安全な通信を確保するには、正しいネットワーク構成が不可欠です。 適切な構成により、セキュリティの脆弱性が防止され、Microsoft Entra IDエンドポイントへの信頼性の高い接続が保証されます。 デプロイ環境に応じて、SDK のネットワーク アクセスを構成するには、次のガイドラインを使用します。

#### 内部通信のみ

内部ポッドローカル通信専用に Microsoft Entra ID Auth SDK (サイドカー) を構成するには、環境に応じて、アプリケーションのエンドポイント URL を `localhost` または`127.0.0.1`を指すように設定します。

```yaml
containers:
- name: sidecar
  env:
  - name: Kestrel__Endpoints__Http__Url
    value: "http://127.0.0.1:5000" # Same pod, localhost communication
```

注意事項

LoadBalancer またはイングレスを使用して、Microsoft Entra ID認証 SDK (サイドカー) を外部に公開しないでください。 アプリケーション コンテナーからのみアクセスできる必要があります。

#### ネットワーク ポリシー

ネットワーク アクセスをさらに制限するには、SDK コンテナーとの間のトラフィックを制限する Kubernetes ネットワーク ポリシーの実装を検討してください。

```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: sidecar-network-policy
spec:
  podSelector:
    matchLabels:
      app: myapp
  policyTypes:
  - Ingress
  - Egress
  ingress:
  # No external ingress rules - only pod-local communication
  egress:
  - to:
    - namespaceSelector:
        matchLabels:
          name: kube-system
    ports:
    - protocol: TCP
      port: 53  # DNS
  - to:
    - podSelector: {}
  - to:
    # Allow outbound to Microsoft Entra ID
    ports:
    - protocol: TCP
      port: 443
```

### 健康診断

Microsoft Entra ID認証 SDK (サイドカー) は、ライブネスプローブと準備プローブ用の`/healthz`エンドポイントを公開し、コンテナーが安全に実行されていることを確認します。 次のプローブを含むようにデプロイを構成します。

```yaml
livenessProbe:
  httpGet:
    path: /healthz
    port: 5000
  initialDelaySeconds: 10
  periodSeconds: 10

readinessProbe:
  httpGet:
    path: /healthz
    port: 5000
  initialDelaySeconds: 5
  periodSeconds: 5
```

### リソース要件

推奨されるリソースの割り当ては次のとおりですが、トークンの取得頻度、構成済みのダウンストリーム API の数、キャッシュ サイズの要件に基づいて調整してください。

| リソース プロファイル | 記憶 | CPU |
| --- | --- | --- |
| **最低限** | 128Mi | 100m |
| **推奨** | 256Mi | 250m |
| **高トラフィック** | 512Mi | 500m |

### スケーリングに関する考慮事項

Microsoft Entra ID Auth SDK (サイドカー) は、アプリケーションに合わせてスケールするように設計されています。

1. **ステートレス設計**: 各 SDK インスタンスは独自のトークン キャッシュを保持します
2. **水平スケーリング**: アプリケーション ポッドを追加してスケーリングする (それぞれに独自の SDK インスタンスがある)
3. **キャッシュ ウォーミング**: トラフィックの多いシナリオに対するキャッシュ ウォーミング戦略の実装を検討する

### デプロイのトラブルシューティング

一般的な問題は、無効な構成値、Microsoft Entra IDへのネットワーク接続、または資格情報または証明書の不足が原因である可能性があります。 マネージド ID またはサービス プリンシパルに、適切なアプリケーションアクセス許可、管理者の同意 (必要な場合)、および正しいロールの割り当てが付与されていることを確認します。

デプロイの問題の解決に役立つ一般的なトラブルシューティング手順を次に示します。

#### コンテナーが起動しない

コンテナー ログを確認します。

```bash
kubectl logs <pod-name> -c sidecar
```

#### ヘルスチェックの障害

Microsoft Entra ID認証 SDK (サイドカー) が応答することを確認します。

```bash
kubectl exec <pod-name> -c sidecar -- curl http://localhost:5000/healthz
```

トラブルシューティングの詳細な手順については、トラブルシューティング [ガイド](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/troubleshooting)を参照してください。
<!-- /MSL-PAGE -->
