# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 10)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 78

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/events"} -->
## MSAL Angular のイベント - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/events
- Service: msal / msal-angular
- Article date: 2026-03-15
- Summary: MsalBroadcastService を使用して MSAL Angular の認証イベントをサブスクライブして処理する方法について説明します

ここで開始する前に、 [アプリケーション オブジェクトを初期化](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/initialization)する方法を理解していることを確認してください。

`@azure/msal-angular` は、認証と MSAL に関連するイベントを出力する `@azure/msal-browser`によって公開されるイベント システムを使用します。また、UI の更新やエラー メッセージの表示などにも使用できます。

### アプリでのイベントの消費

`@azure/msal-angular`のイベントは、`MsalBroadcastService`によって管理され、`msalSubject$`で監視可能な`MsalBroadcastService`をサブスクライブすることによって使用できます。

アプリケーションで生成されたイベントを使用する方法の例を次に示します。

```javascript
import { MsalBroadcastService } from '@azure/msal-angular';
import { EventMessage, EventType } from '@azure/msal-browser';

export class AppComponent implements OnInit, OnDestroy {
  private readonly _destroying$ = new Subject<void>();

  constructor(
    //...
    private msalBroadcastService: MsalBroadcastService
  ) {}

  ngOnInit(): void {
    this.msalBroadcastService.msalSubject$
      .pipe(
        // Optional filtering of events.
        filter((msg: EventMessage) => msg.eventType === EventType.LOGIN_SUCCESS), 
        takeUntil(this._destroying$)
      )
      .subscribe((result: EventMessage) => {
        // Do something with the result
      });
  }

  ngOnDestroy(): void {
    this._destroying$.next(null);
    this._destroying$.complete();
  }
}
```

コンパイル エラーを防ぐために、 `result.payload` を特定の型としてキャストする必要がある場合があることに注意してください。 ペイロードの種類はイベントによって異なります。 [こちらの](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/events.md)ドキュメントで確認できます。

```javascript
ngOnInit(): void {
  this.msalBroadcastService.msalSubject$
    .pipe(
      filter((msg: EventMessage) => msg.eventType === EventType.LOGIN_SUCCESS),
    )
    .subscribe((result: EventMessage) => {
      // Casting payload as AuthenticationResult to access account
      const payload = result.payload as AuthenticationResult;
      this.authService.instance.setActiveAccount(payload.account);
    });
}
```

イベントを使用する完全な例については、 [こちらの](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-modules-sample/src/app/home/home.component.ts)サンプルを参照してください。

### イベントの表

`EventMessage`によって現在出力されているイベントの完全なテーブル (説明や関連するペイロードを含む) など、`@azure/msal-browser` オブジェクトの詳細については、[こちらの](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/events.md)ドキュメントを参照してください。

### イベントでのエラーの処理

`EventError`の`EventMessage`は`AuthError | Error | null`として定義されているため、エラーは、その上の特定のプロパティにアクセスする前に、正しい型として検証する必要があります。

TypeScript エラーを回避するためにエラーを `AuthError` にキャストする方法の次の例を参照してください。

```javascript
import { MsalBroadcastService } from '@azure/msal-angular';
import { EventMessage, EventType } from '@azure/msal-browser';

export class AppComponent implements OnInit, OnDestroy {
  private readonly _destroying$ = new Subject<void>();

  constructor(
    //...
    private msalBroadcastService: MsalBroadcastService
  ) {}

  ngOnInit(): void {
    this.msalBroadcastService.msalSubject$
      .pipe(
        // Optional filtering of events
        filter((msg: EventMessage) => msg.eventType === EventType.LOGIN_FAILURE), 
        takeUntil(this._destroying$)
      )
      .subscribe((result: EventMessage) => {
        if (result.error instanceof AuthError) {
          // Do something with the error
        }
      });
  }

  ngOnDestroy(): void {
    this._destroying$.next(null);
    this._destroying$.complete();
  }
}
```

エラー処理の例は、 [MSAL Angular B2C サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-b2c-sample/src/app/app.component.ts#L129)でも確認できます。

### タブとウィンドウ間でログに記録された状態を同期する

ユーザーが別のタブまたはウィンドウでアプリにログインまたはログアウトするときに UI を更新する場合は、 `ACCOUNT_ADDED` と `ACCOUNT_REMOVED` イベントをサブスクライブできます。 ペイロードは、追加または削除された `AccountInfo` オブジェクトになります。

```javascript
import { MsalService, MsalBroadcastService } from '@azure/msal-angular';
import { EventMessage, EventType } from '@azure/msal-browser';

export class AppComponent implements OnInit, OnDestroy {
  private readonly _destroying$ = new Subject<void>();

  constructor(
    //...
    private authService: MsalService,
    private msalBroadcastService: MsalBroadcastService
  ) {}

  ngOnInit(): void {
    this.authService.instance.enableAccountStorageEvents(); // Register the storage listener that will be emitting the events
    this.msalBroadcastService.msalSubject$
      .pipe(
        // Optional filtering of events
        filter((msg: EventMessage) => msg.eventType === EventType.ACCOUNT_ADDED || msg.eventType === EventType.ACCOUNT_REMOVED), 
        takeUntil(this._destroying$)
      )
      .subscribe((result: EventMessage) => {
        if (this.authService.msalInstance.getAllAccounts().length === 0) {
          // Account logged out in a different tab, redirect to homepage
          window.location.pathname = "/";
        } else {
          // Update UI to show user is signed in. result.payload contains the account that was logged in
        }
      });
  }

  ngOnDestroy(): void {
    this._destroying$.next(null);
    this._destroying$.complete();
  }
}
```

完全な例は、 [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-modules-sample/src/app/app.component.ts)でも確認できます。

### inProgress$ Observable

監視可能な `inProgress$` は `MsalBroadcastService`によっても処理され、特に相互作用が完了したことを確認するために、アプリケーションが対話の状態を知る必要があるときにサブスクライブする必要があります。 ユーザー アカウントを含む関数の前に、対話の状態が `InteractionStatus.None` されていることを確認することをお勧めします。

最後の、つまり最新の `InteractionStatus` も、`inProgress$` Observable をサブスクライブした際に利用できることに注意してください。

その使用については、次の例を参照してください。 完全な例は、 [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-modules-sample/src/app/home/home.component.ts#L29)でも確認できます。 対話状態の完全な一覧 [については、こちらをご覧ください](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/src/utils/BrowserConstants.ts#L87)。

```js
import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import { MsalBroadcastService} from '@azure/msal-angular';
import { InteractionStatus } from '@azure/msal-browser';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  private readonly _destroying$ = new Subject<void>();

  constructor(
    private msalBroadcastService: MsalBroadcastService
  ) {}

  ngOnInit(): void {
    this.msalBroadcastService.inProgress$
      .pipe(
        // Filtering for all interactions to be completed
        filter((status: InteractionStatus) => status === InteractionStatus.None),
        takeUntil(this._destroying$)
      )
      .subscribe(() => {
        // Do something related to user accounts or UI here
      })
  }

  ngOnDestroy(): void {
    this._destroying$.next(null);
    this._destroying$.complete();
  }
}

```

### オプションの `MsalBroadcastService` 構成

`MsalBroadcastService`は、必要に応じて、サブスクライブ時に過去のイベントを再生するように構成できます。 既定では、 `MsalBroadcastService` のサブスクライブ後に生成されるイベントを使用できます。 サブスクリプションより前のイベントが必要な場合があります。 `MsalBroadcastService`の構成を指定し、`eventsToReplay` パラメーターを数値に設定することで、サブスクリプションでその数の過去のイベントを使用できるようになります。

イベントの再生の詳細については、ReplaySubjects の RxJS ドキュメント [を参照してください](https://rxjs.dev/api/index/class/ReplaySubject)。

`MsalBroadcastService`は、app.module.ts ファイルで次のように構成できます。

```typescript
// app.module.ts
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { MsalModule, MsalService, MsalGuard, MsalInterceptor, MsalBroadcastService, MsalRedirectComponent, MSAL_BROADCAST_CONFIG } from "@azure/msal-angular"; // Import MsalBroadcastService and MSAL_BROADCAST_CONFIG here
import { PublicClientApplication, InteractionType, BrowserCacheLocation } from "@azure/msal-browser";

@NgModule({
    imports: [
        MsalModule.forRoot( new PublicClientApplication({ // MSAL Configuration
            auth: {
                clientId: "clientid",
                authority: "https://login.microsoftonline.com/common/",
                redirectUri: "http://localhost:4200/",
                postLogoutRedirectUri: "http://localhost:4200/",
                navigateToLoginRequestUrl: true
            },
            cache: {
                cacheLocation : BrowserCacheLocation.LocalStorage,
            },
            system: {
                loggerOptions: {
                    loggerCallback: () => {},
                    piiLoggingEnabled: false
                }
            }
        }), {
            interactionType: InteractionType.Popup, // MSAL Guard Configuration
            authRequest: {
              scopes: ['user.read']
            },
            loginFailedRoute: "/login-failed" 
        }, {
            interactionType: InteractionType.Redirect, // MSAL Interceptor Configuration
            protectedResourceMap
        })
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: MsalInterceptor,
            multi: true
        },
        {
          provide: MSAL_BROADCAST_CONFIG, // Add configuration to providers here
          useValue: {
            eventsToReplay: 2 // Set how many events you want to replay when subscribing
          }
        },
        MsalGuard,
        MsalBroadcastService // Ensure the MsalBroadcastService is provided
    ],
    bootstrap: [AppComponent, MsalRedirectComponent]
})
export class AppModule {}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/initialization"} -->
## MSAL Angular の初期化 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/initialization
- Service: msal / msal-angular
- Article date: 2026-03-15
- Summary: MsalModule をインポートし、アプリ モジュールで PublicClientApplication を構成して、MSAL Angular を初期化する方法について説明します

`@azure/msal-angular`を使用する前に、[アプリケーションをMicrosoft Entra IDに登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)して`clientId`を取得します。

### アプリ モジュールに MSAL モジュールを含め、初期化する

app.module.tsに `MsalModule` をインポートします。 MSAL モジュールを初期化するには、アプリケーションの clientId を渡します。アプリケーションの登録から取得します。

```js
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { MsalModule, MsalService, MsalGuard, MsalInterceptor, MsalBroadcastService, MsalRedirectComponent } from "@azure/msal-angular";
import { PublicClientApplication, InteractionType, BrowserCacheLocation } from "@azure/msal-browser";

@NgModule({
    imports: [
        MsalModule.forRoot( new PublicClientApplication({ // MSAL Configuration
            auth: {
                clientId: "Your client ID",
                authority: "Your authority",
                redirectUri: "Your redirect Uri",
            },
            cache: {
                cacheLocation : BrowserCacheLocation.LocalStorage,
            },
            system: {
                loggerOptions: {
                    loggerCallback: () => {},
                    piiLoggingEnabled: false
                }
            }
        }), {
            interactionType: InteractionType.Redirect, // MSAL Guard Configuration
        }, {
            interactionType: InteractionType.Redirect, // MSAL Interceptor Configuration
        })
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: MsalInterceptor,
            multi: true
        },
        MsalService,
        MsalGuard,
        MsalBroadcastService
    ],
    bootstrap: [AppComponent, MsalRedirectComponent]
})
export class AppModule {}
```

### アプリケーション内のルートをセキュリティで保護する

ルート定義に `canActivate: [MsalGuard]` を追加して、アプリケーション内の特定のルートをセキュリティで保護するための認証を追加します。 親ルートまたは子ルートに追加します。 ユーザーがこれらのルートにアクセスすると、ライブラリはユーザーに認証を求めます。

追加のインターフェイスの使用など、構成と考慮事項の詳細については、 [`MsalGuard` ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-guard) を参照してください。

`MsalGuard`で定義されたルートの例を次に示します。

```js
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { MsalGuard } from '@azure/msal-angular';

const routes: Routes = [
    {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [MsalGuard]
    },
    {
        path: '',
        component: HomeComponent
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
```

### Web API 呼び出しのトークンを取得する

`@azure/msal-angular` では、次のように http インターセプター (`MsalInterceptor`) を `app.module.ts` に追加できます。 `MsalInterceptor`はトークンを取得し、`protectedResourceMap`に基づいて API 呼び出し内のすべての Http 要求に追加します。 構成と使用の詳細については、 [MsalInterceptor のドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-interceptor) を参照してください。

```js
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { MsalModule, MsalService, MsalGuard, MsalInterceptor, MsalBroadcastService, MsalRedirectComponent } from "@azure/msal-angular";
import { PublicClientApplication, InteractionType, BrowserCacheLocation } from "@azure/msal-browser";

@NgModule({
    imports: [
        MsalModule.forRoot( new PublicClientApplication({ // MSAL Configuration
            auth: {
                clientId: "Your client ID",
                authority: "Your authority",
                redirectUri: "Your redirect Uri",
            },
            cache: {
                cacheLocation : BrowserCacheLocation.LocalStorage,
            },
            system: {
                loggerOptions: {
                    loggerCallback: () => {},
                    piiLoggingEnabled: false
                }
            }
        }), {
            interactionType: InteractionType.Redirect, // MSAL Guard Configuration
        }, {
            interactionType: InteractionType.Redirect, // MSAL Interceptor Configuration
            protectedResourceMap: new Map([
                ['https://graph.microsoft.com/v1.0/me', ['user.read']],
                ['https://api.myapplication.com/users/*', ['customscope.read']],
                ['http://localhost:4200/about/', null] 
            ])
        })
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: MsalInterceptor,
            multi: true
        },
        MsalService,
        MsalGuard,
        MsalBroadcastService
    ],
    bootstrap: [AppComponent, MsalRedirectComponent]
})
export class AppModule {}
```

`MsalInterceptor`の使用は省略可能です。 代わりに acquireToken API を使用してトークンを明示的に取得することもできます。

`MsalInterceptor`は便宜上提供されており、すべてのユース ケースに適合しない場合があることに注意してください。 `MsalInterceptor`で対処されていない特定のニーズがある場合は、独自のインターセプターを記述します。

### イベントを購読する

MSAL は、認証と MSAL に関連するイベントを出力するイベント システムを提供します。 イベントを使用するには、コンポーネントまたはサービスのコンストラクターに `MsalBroadcastService` を追加します。

#### 1. イベントを購読する方法

```js
import { EventMessage, EventType } from '@azure/msal-browser';
import { filter } from 'rxjs/operators';

this.msalBroadcastService.msalSubject$
    .pipe(
        filter((msg: EventMessage) => msg.eventType === EventType.LOGIN_SUCCESS)
    )
    .subscribe((result) => {
        // do something here
    });
```

#### 2. 利用可能なイベント

MSAL で使用できるイベントの一覧については、 [`@azure/msal-browser` イベントのドキュメントを参照してください。](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/events.md)

#### 3. サブスクライブを解除する

サブスクリプションの解除は重要です。 コンポーネントに `ngOnDestroy()` を実装してサブスクリプションを解除します。

```js
import { EventMessage, EventType } from '@azure/msal-browser';
import { filter, Subject, takeUntil } from 'rxjs';

private readonly _destroying$ = new Subject<void>();

this.msalBroadcastService.msalSubject$
    .pipe(
        filter((msg: EventMessage) => msg.eventType === EventType.LOGIN_SUCCESS),
        takeUntil(this._destroying$)
    )
    .subscribe((result) => {
        this.checkAccount();
    });

ngOnDestroy(): void {
    this._destroying$.next(null);
    this._destroying$.complete();
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/logging"} -->
## MSAL Angular でのログ記録 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/logging
- Service: msal / msal-angular
- Article date: 2025-05-21
- Summary: MSAL Angular でのログ記録について説明します

ロガー定義には、次のプロパティがあります。

1. correlationId
2. logLevel
    - logLevel には、 `Error`、 `Warning`、 `Info`、 `Trace`、および `Verbose`
3. piiLoggingEnabled

次に示すように、アプリでログ記録を有効にすることができます。

```js
import { LogLevel, PublicClientApplication } from '@azure/msal-browser';

export function loggerCallback(logLevel, message) {
    console.log(message);
}

@NgModule({
    imports: [ 
        MsalModule.forRoot(new PublicClientApplication({
            auth: {
                clientId: 'Your client ID',
            },
            system: {
                loggerOptions: {
                    loggerCallback,
                    piiLoggingEnabled: true,
                    logLevel: LogLevel.Info
                }
            }
        }))
    ]
})
```

`logger`は、`MsalService.setLogger()`を使用して動的に設定することもできます。

```js
this.authService.setLogger(new Logger({
    loggerCallback: (logLevel, message, piiEnabled) => {
        console.log('MSAL Logging: ', message);
    },
    piiLoggingEnabled: false,
    logLevel: LogLevel.Info
}));
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/msal-guard"} -->
## MSAL Guard を使用してルートを保護する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-guard
- Service: msal / msal-angular
- Article date: 2026-03-15
- Summary: MsalGuard を使用してルートを保護し、MSAL Angular アプリケーションで認証を要求する方法について説明します

MSAL Angular には、 `MsalGuard`、ルートを保護するために使用できるクラスが用意されており、保護されたルートにアクセスする前に認証が必要です。 このドキュメントでは、 `MsalGuard`を使用する場合の構成と考慮事項について詳しく説明します。

`MsalGuard` は、ユーザー エクスペリエンスを向上させるために使用できる便利なクラスですが、セキュリティに依存すべきではありません。 攻撃者はクライアント側のガードを回避する可能性があり、ユーザーがアクセスしてはならないデータがサーバーから返されないようにする必要があります。

また、特定のニーズに対応するルート ガードが必要な場合もあります。 `MsalGuard`がこれらのすべてのニーズを満たしていない場合は、独自のガードを記述することをお勧めします。

### Configurations

#### *app.module.ts* と *app-routing.module.ts* での `MsalGuard` の設定

`MsalGuard` は、設定とともに、*app.module.ts* 内でアプリケーションのプロバイダーとして追加できます。 インポートでは、MSAL のインスタンスと、2 つの Angular 固有の構成オブジェクトが取り込まれます。 2 番目の引数は、[`MsalGuardConfiguration`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.guard.config.ts)の値、省略可能な`interactionType`、および省略可能な`authRequest`を含む`loginFailedRoute` オブジェクトです。

`MsalGuard`は、app-routing.module.ts内のルートを保護するために使用*されます*。 次のコード サンプルでは、`MsalGuard` ルートに`Profile`を追加する方法を示します。 `Profile` ルートを保護することは、ユーザーが [`Login`] ボタンを使用してサインインしない場合でも、`Profile` ルートにアクセスしようとしたり、[`Profile`] ボタンをクリックしようとした場合でも、`MsalGuard`は、`Profile` ページを表示する前に、ポップアップまたはリダイレクトを使用してユーザーに認証を求めるメッセージを表示することを意味します。

構成は次のようになります。 アプリ用に MSAL Angular を構成する他の方法については [、構成ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/configuration) を参照してください。ルーティングの `MsalConfiguration` オブジェクトとインターフェイスの詳細については、以下のセクションを参照してください。

```javascript
// app.module.ts
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { MsalModule, MsalRedirectComponent, MsalGuard } from '@azure/msal-angular'; // Import MsalInterceptor
import { InteractionType, PublicClientApplication } from '@azure/msal-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        MsalModule.forRoot( new PublicClientApplication({
            // MSAL Configuration
        }), {
            // MSAL Guard Configuration
            interactionType: InteractionType.Redirect,
            authRequest: {
                scopes: ['user.read']
            },
            loginFailedRoute: '/login-failed'
        }, {
            // MSAL Interceptor Configurations
        }),
        AppRoutingModule
    ],
    providers: [
        // ...
        MsalGuard
    ],
    bootstrap: [AppComponent, MsalRedirectComponent]
})
export class AppModule { }
```

```javascript
// app-routing.module.ts
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { MsalGuard } from '@azure/msal-angular';

const routes: Routes = [
    {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [MsalGuard]
    },
    {
        path: '',
        component: HomeComponent
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
```

#### 相互作用の種類

対話の種類を設定すると、 `MsalGuard` が対話形式でログインを求める方法が決まります。 `InteractionType`は`@azure/msal-browser`からインポートし、`Popup`または`Redirect`に設定できます。

#### オプションの authRequest

省略可能な `authRequest` は、必須ではない高度な機能です。 ただし、スコープに対して事前に同意を得ることができるように、`authRequest`を使用して`MsalGuardConfiguration`に`scopes`を設定することをお勧めします。 `scopes`の同意が事前に同意されていない場合は、スコープを段階的に取得できます。 これにより、同意ダイアログがアプリ ユーザーに複数回表示される可能性があります。

必要なスコープに事前に同意することは、上記のコードサンプルと当社の[サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples)で示されています。

要求オブジェクトに使用できるパラメーターはすべて、 [`PopupRequest`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/popuprequest) と [`RedirectRequest`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/redirectrequest)で確認できます。

#### ログインに失敗したルート

`loginFailedRoute`文字列は、`MsalGuardConfiguration`に設定できます。 ログインが必要で失敗した場合、 `MsalGuard` はこのルートにリダイレクトされます。

[構成](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-modules-sample/src/app/app.module.ts#L66)と[アプリ ルーティング モジュール](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-modules-sample/src/app/app-routing.module.ts#L20)で実装する例については、Angular のサンプルを参照してください。

基本型の違いにより、 `CanLoad` インターフェイスを使用する Angular 9 アプリケーションでは、障害時のリダイレクトは使用できません。

#### Interfaces

`canActivate`に加えて、`MsalGuard`では`canActivateChild`と`canLoad`も実装され、これらは*app-routing.module.ts*のルート定義に追加できます。 これらは、 [以前の MSAL Angular v2 Angular 11 サンプル アプリケーション](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-v2-samples/angular11-sample-app/src/app/app-routing.module.ts)と以下で使用されています。 インターフェイスの詳細については、 [Angular のドキュメント](https://angular.io/api/router)を参照してください。

```js
const routes: Routes = [
    {
        path: 'profile',
        canActivateChild: [MsalGuard],
        children: [
        {
            path: '',
            component: ProfileComponent
        },
        {
            path: 'detail',
            component: DetailComponent
        }
        ]
    },
    { 
        path: 'lazyLoad', 
        loadChildren: () => import('./lazy/lazy.module').then(m => m.LazyModule),
        canLoad: [MsalGuard]
    },
];
```

### MSAL Guard を使用する場合の考慮事項

#### ホーム ページでの MSAL Guard の使用

最初のページで `MsalGuard` を設定することをお勧めします。ユーザーがアプリケーションにアクセスしたときにログインするように求めるメッセージが表示される場合です。 `login`の`ngOnInit`で`app.component.ts`を呼び出すことはお勧めしません。これにより、リダイレクトのループが発生する可能性があるためです。

その他の推奨事項は、ルーティング戦略によって異なります。以下のセクションで確認できます。

#### パス ルーティングでの MSAL Guard の使用

Angular アプリで `PathLocationStrategy` とリダイレクトを使用する場合は、リダイレクト専用のルートを使用することをお勧めします。これにより、ループを防ぐことができます。 このルートは `redirectUri`でもあり、 `MsalGuard`によって保護されないようにする必要があります。

```javascript
const routes: Routes = [
    {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [MsalGuard]
    },
    {
        // Dedicated route for redirects
        path: 'auth', 
        component: MsalRedirectComponent
    },
    {
        path: '',
        component: HomeComponent
    }
];
```

アプリにアクセスしたときにユーザーをログインするには、 `PathLocationStrategy`を使用するときは、次のことをお勧めします。

- 初期ページでの `MsalGuard` の設定
- `redirectUri`を`'http://localhost:4200/auth'`に設定します
- `'auth'` パスをルートに追加し、`MsalRedirectComponent`をコンポーネントとして設定します (このルートは`MsalGuard`で保護しないでください)。
- `MsalRedirectComponent`がブートストラップされていることを確認する
- オプション: すべてのルートを保護する場合は、すべてのルートに `MsalGuard` を追加する

[Angular Modules サンプルでは、](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-modules-sample)`PathLocationStrategy`を使用し、`MsalGuard`でルートを保護する方法を示します。

#### ハッシュ ルーティングでの MSAL Guard の使用

Angular アプリで`HashLocationStrategy`を使用する場合は、app-routing.module.tsでプレースホルダー ルート (`/code` など) を*設定することを強*くお勧めします。これにより、Microsoft Entra IDがハッシュで認証コードの応答を返すときに Angular ルーターがトリガーされないようにします。そうせずに認証を完了する際に問題が発生する可能性があるためです。 これらのプレースホルダー ルートは、 `MsalGuard`によって保護されるべきではありません。また、ページ読み込み時に対話をトリガーしたり、保護された API 呼び出しを行ったりするコンポーネントを指すべきではありません。

```javascript
const routes: Routes = [
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [MsalGuard]
  },
  {
    // Needed for hash routing
    path: 'code',
    component: HomeComponent
  },
  {
    path: '',
    component: HomeComponent
  }
];
```

MSAL 構成の `redirectUri` もホーム ページに設定する必要があります。

アプリにアクセスしたときにユーザーをログインするには、 `HashLocationStrategy`を使用するときは、次のことをお勧めします。

- 初期ページでの `MsalGuard` の設定
- プレースホルダー ルートに `MsalGuard` を設定しない (例: `/code`、 `/error`)
- `MsalRedirectComponent`がブートストラップされていることを確認する
- 必要に応じて、すべてのルートを保護する場合は、残りのすべてのルートに `MsalGuard` を追加します

を使用し、`HashLocationStrategy`でルートを保護する方法を示す、`MsalGuard`を参照してください。

### msal-angular v1 から v2 への変更

- **構成**: `MsalAngularConfiguration` は非推奨となり、機能しなくなりました。 `MsalGuard`の構成は、`MsalGuardConfiguration`を使用して行われるようになりました。
- **インターフェイス**: `MsalGuard`は、`CanActivateChild`に加えて`CanLoad`と`CanActivate`を実装するようになりました。 詳細については、 `Interfaces` に関する上記のセクションを参照してください。
- **失敗時のリダイレクト**: `MsalGuard` 構成に、構成できる `loginFailedRoute` が含まれるようになりました。 詳細については、 `loginFailedRoute` 上のセクションを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/msal-interceptor"} -->
## MSAL インターセプターの使用 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-interceptor
- Service: msal / msal-angular
- Article date: 2026-03-15
- Summary: MsalInterceptor を使用して、Angular の HTTP 要求にトークンを自動的に取得してアタッチする方法について説明します

MSAL Angular には、既知の保護されたリソースに対して Angular `Interceptor` クライアントを使用する送信要求のトークンを自動的に取得する`http` クラスが用意されています。 このドキュメントでは、 `MsalInterceptor`の構成と使用について詳しく説明します。

`MsalInterceptor` API の代わりに`acquireTokenSilent`を直接使用することをお勧めしますが、`MsalInterceptor`の使用は省略可能であることに注意してください。 代わりに acquireToken API を使用してトークンを明示的に取得することもできます。

`MsalInterceptor`は便宜上提供されており、すべてのユース ケースに適合しない場合があることに注意してください。 `MsalInterceptor`で対処されていない特定のニーズがある場合は、独自のインターセプターを記述することをお勧めします。

### コンフィギュレーション

#### *app.module.ts* での `MsalInterceptor` の設定

`MsalInterceptor` は、設定とともに、*app.module.ts* 内でアプリケーションのプロバイダーとして追加できます。 インポートでは、MSAL のインスタンスと、2 つの Angular 固有の構成オブジェクトが取り込まれます。 3 番目の引数は [`MsalInterceptorConfiguration`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.interceptor.config.ts) オブジェクトであり、 `interactionType`、 `protectedResourceMap`、および省略可能な `authRequest`の値が含まれます。

構成は次のようになります。 アプリ用に MSAL Angular を構成する他の方法については、 [構成ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/configuration) を参照してください。

```javascript
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { AppComponent } from './app.component';
import { MsalModule, MsalRedirectComponent, MsalGuard, MsalInterceptor } from '@azure/msal-angular'; // Import MsalInterceptor
import { InteractionType, PublicClientApplication } from '@azure/msal-browser';

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        MsalModule.forRoot( new PublicClientApplication({
            // MSAL Configuration
        }), {
            // MSAL Guard Configuration
        }, {
            // MSAL Interceptor Configurations
            interactionType: InteractionType.Redirect,
            protectedResourceMap: new Map([ 
                ['Enter_the_Graph_Endpoint_Here/v1.0/me', ['user.read']]
            ])
        })
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS, // Provides as HTTP Interceptor
            useClass: MsalInterceptor,
            multi: true
        },
        MsalGuard
    ],
    bootstrap: [AppComponent, MsalRedirectComponent]
})
export class AppModule { }
```

#### 相互作用の種類

`MsalInterceptor`はトークンをサイレントで取得するように設計されていますが、サイレント要求が失敗した場合は、対話形式でトークンの取得にフォールバックします。 `InteractionType`は`@azure/msal-browser`からインポートし、`Popup`または`Redirect`に設定できます。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map([ 
        ['Enter_the_Graph_Endpoint_Here/v1.0/me', ['user.read']]
    ])
}
```

#### 保護されたリソース マップ

保護されたリソースと対応するスコープは、`protectedResourceMap`構成の`MsalInterceptor`として提供されます。

`protectedResourceMap` コレクションに指定する URL は、大文字と小文字が区別されます。 リソースごとに、アクセス トークンで返されるように要求されているスコープを追加します。

例えば次が挙げられます。

- `["user.read"]` 向け Microsoft Graph
- `["<Application ID URL>/scope"]` カスタム Web API の場合 (つまり、 `api://<Application ID>/access_as_user`)

リソースのスコープは、次の方法で指定できます。

1. スコープの配列。HTTP メソッドに関係なく、そのリソースに対するすべての HTTP 要求に追加されます。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map<string, Array<string> | null>([
        ["https://graph.microsoft.com/v1.0/me", ["user.read", "profile"]],
        ["https://myapplication.com/user/*", ["customscope.read"]]
    ]),
}
```

1. 特定の HTTP メソッドに対してのみスコープをアタッチする `ProtectedResourceScopes`の配列。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map<string, Array<string|ProtectedResourceScopes> | null>([
        ["https://graph.microsoft.com/v1.0/me", ["user.read"]],
        ["http://myapplication.com", [
            {
                httpMethod: "POST",
                scopes: ["write.scope"]
            }
        ]]
    ])
}
```

リソースのスコープには、文字列と `ProtectedResourceScopes`の組み合わせを含めることができます。 次の例では、 `GET` 要求にはスコープ `"all.scope"` と `"read.scope"`が含まれますが、 `PUT` 要求には `"all.scope"`があります。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map<string, Array<string|ProtectedResourceScopes> | null>([
        ["http://myapplication.com", [
            "all.scope",
            {
                httpMethod: "GET",
                scopes: ["read.scope"]
            },
            {
                httpMethod: "POST",
                scopes: ["info.scope"]
            }
        ]]
    ])
}
```

1. リソースが保護されず、トークンを取得しないことを示す、 `null`のスコープ値。 `protectedResourceMap`に含まれていないリソースは、既定では保護されません。 保護されていない特定のリソースを指定すると、リソース上の一部のルートを保護する場合や保護されないルートがある場合に便利です。 `protectedResourceMap`の順序が重要であるため、同様のベース URL またはワイルドカードの前に null リソースを配置する必要があることに注意してください。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map<string, Array<string> | null>([
        ["https://graph.microsoft.com/v1.0/me", ["user.read", "profile"]],
        ["https://myapplication.com/unprotected", null],
        ["https://myapplication.com/unprotected/post", [{ httpMethod: 'POST', scopes: null }]],
        ["https://myapplication.com", ["custom.scope"]]
    ]),
}
```

`protectedResourceMap`に関するその他の注意事項:

- **ワイルドカード**: `protectedResourceMap` では、ワイルドカードに `*` を使用できます。 ワイルドカードを使用する場合、 `protectedResourceMap`で複数の一致するエントリが見つかった場合は、見つかった最初の一致が ( `protectedResourceMap`の順序に基づいて) 使用されます。
- **相対パス**: アプリケーションに相対リソース パスがある場合は、 `protectedResourceMap`に相対パスを指定する必要があります。 これは、ngx-translate で発生する可能性のある問題にも当てはまります。 `protectedResourceMap`の相対パスは、アプリによっては先頭のスラッシュが必要な場合と必要ない場合があり、両方を試す必要がある場合があることに注意してください。

#### 厳密な照合 (`strictMatching`)

msal-angular v5 では、 `protectedResourceMap` エントリの URL コンポーネント パターン マッチングでは、既定で厳密な一致セマンティクスが使用されます。 この動作は、`strictMatching`の `MsalInterceptorConfiguration` フィールドによって制御されます。

Important

アプリケーション `protectedResourceMap` キーを動的に設定し (たとえば、環境ファイル、 `APP_INITIALIZER`、JSON 構成から)、それらのキーがサブパスやワイルドカードのないベース URL である場合、厳密な照合により、 `Authorization` ヘッダーが自動的にアタッチされない可能性があります。 これにより、 **ビルド時エラーがなく、要求ごとの警告も発生しない 401 エラーが発生します。 `strictMatching` が明示的に構成されていない場合は、1 回限りの初期化警告になります**。 詳細については、 厳密な照合のトラブルシューティング を参照してください。

##### 厳密な照合の変更点

| Behavior | レガシ (`strictMatching: false`) | Strict (v5 の既定値) |
| --- | --- | --- |
| メタ文字エスケープ | `.` およびその他の正規表現メタ文字はエスケープ **されません** 。正規表現演算子として機能する | すべてのメタ文字 (`.`を含む) は**リテラル**として扱われます |
| アンカー設定 | パターンは文字列内の任意の場所と一致する可能性があります | パターンは **完全な文字列** と一致する必要があります (`^…$`) |
| ホストワイルドカード (`*`) | `*` を含む任意の文字シーケンスに一致します。 `.` | `*`は、を含`.`文字シーケンスと一致します (ワイルドカードは 1 つの DNS ラベル内に留まる) |
| パス/検索/ハッシュ ワイルドカード (`*`) | `*` 任意の文字シーケンスに一致します | `*` 任意の文字シーケンスと一致します (変更なし) |
| `?` 文字 | 基になる正規表現に渡される | **リテラル**として扱われます`?` (ワイルドカードではなく、URL クエリ文字列区切り記号) |

厳密に一致する場合 (v5 の既定値):

- `*.contoso.com` のようなパターンは `app.contoso.com` には一致しますが、`a.b.contoso.com` には**一致しません**（ワイルドカードはドット区切りをまたぐことはできません）。
- `https://graph.microsoft.com/v1.0/me`のようなパターンは、その正確な URL にのみ一致します。

##### 一般的なエラー パターン

次の `protectedResourceMap` キー パターンは従来の一致では機能しますが、厳密な一致では警告なしに失敗します。

| キーパターン | 送信リクエスト URL | 厳密な照合の結果 | 修正 |
| --- | --- | --- | --- |
| `https://api.example.com` | `https://api.example.com/v1/users` | 一致しません — キーはパス `/` に解決されますが、リクエストのパスは `/v1/users` です | `https://api.example.com/*` |
| `https://api.example.com/` | `https://api.example.com/v1/users` | 一致なし — 末尾のスラッシュはパターンを正確に固定します `/` | `https://api.example.com/*` |
| `environment.apiConfig.uri` (例: `https://api.example.com`) | `https://api.example.com/v1/users` | 一致なし - 上記と同じ | ``${environment.apiConfig.uri}/*`` |

##### v5 での既定の動作 (構成は必要ありません)

厳密な照合は既定で有効になっています。 追加の構成は必要ありません。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map([
        ["https://*.contoso.com/api", ["contoso.scope"]],
        ["https://graph.microsoft.com/v1.0/me", ["user.read"]]
    ])
    // strictMatching defaults to true in v5
}
```

##### 従来の照合を無効にする

パターンが v4 の緩い一致に依存している場合は、レガシ動作を一時的に保持するように `strictMatching: false` を設定できます。

Note

従来の一致 (`strictMatching: false`) は下位互換性のために提供されており、今後のメジャー バージョンでは削除される可能性があります。 厳密な一致を使用するように、 `protectedResourceMap` パターンを更新することをお勧めします。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map([
        ["https://*.contoso.com/api", ["contoso.scope"]],
        ["https://graph.microsoft.com/v1.0/me", ["user.read"]]
    ]),
    strictMatching: false  // Use legacy matching for backwards compatibility
}
```

##### 環境駆動型構成のガイダンス

`protectedResourceMap` キーが Angular `environment` 値 (`environment.apiConfig.uri` など) を参照している場合は、それらの値が**正確なパス** (`https://graph.microsoft.com/v1.0/me` など) か**ベア ベース URL** (`https://api.example.com` など) であるかを確認します。 厳密なパスは厳密な一致で正しく機能し、特別な処理は必要ありません。

```javascript
export function MSALInterceptorConfigFactory(): MsalInterceptorConfiguration {
  const protectedResourceMap = new Map<string, Array<string>>();
  // environment.apiConfig.uri is an exact path (e.g. "https://graph.microsoft.com/v1.0/me")
  // — strict matching works correctly
  protectedResourceMap.set(environment.apiConfig.uri, environment.apiConfig.scopes);

  return {
    interactionType: InteractionType.Redirect,
    protectedResourceMap,
  };
}
```

環境の値がベア ベース URL で、サブパスと一致する必要がある場合は、 `/*` ワイルドカードを追加します。

```javascript
  // environment.apiConfig.uri is a base URL (e.g. "https://api.example.com")
  // Append /* to match all sub-paths
  protectedResourceMap.set(`${environment.apiConfig.uri}/*`, environment.apiConfig.scopes);
```

キーシェイプがビルド時に認識されない真に **動的な** 構成 (たとえば、 `APP_INITIALIZER`、 `fetch`経由で読み込まれた JSON、 `platformBrowserDynamic`) の場合は、 `strictMatching: false` を一時的な安全な既定値として設定します。 コード例については、「 修正オプション-オプション B 」を参照してください。

##### 厳密な照合のトラブルシューティング

###### Symptoms

- API 要求は、 v5 にアップグレードした後 (または 5.0.x → 5.1.x などの v5 マイナー バージョン間) に `@azure/msal-angular` を返します。
- `Authorization: Bearer <token>` ヘッダーが送信 HTTP 要求に**含まれていない**。
- ビルド時またはランタイム エラーは報告されません。エラーは **サイレントです**。
- この問題は、API ベース URL が開発と異なる特定の環境 (ステージング/運用など) でのみ発生する可能性があります。

###### 修正オプション

**オプション A: 厳密な照合を使用するようにキーを更新する (推奨)**

厳密な一致規則に一致する正確なパスまたはワイルドカードを使用するように、 `protectedResourceMap` キーを更新します。 厳密な照合の方が安全で予測しやすいため、この方法をお勧めします。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map([
        // Exact path — matches only this URL
        ["https://graph.microsoft.com/v1.0/me", ["user.read"]],
        // Wildcard — matches all sub-paths of the API
        ["https://api.example.com/v1/*", ["api.scope"]]
    ])
    // strictMatching defaults to true — no need to set it
}
```

**オプション B: `strictMatching: false` を設定する (動的構成のフォールバック)**

`protectedResourceMap` キーが実行時に動的に読み込まれ (`APP_INITIALIZER`、JSON 構成、`platformBrowserDynamic`など)、正確なパスやワイルドカードが含まれていることを保証できない場合は、`strictMatching: false`を一時的な安全な既定値として設定します。

```javascript
{
    interactionType: InteractionType.Redirect,
    protectedResourceMap: new Map([
        [config.apiUri, config.apiScopes]
    ]),
    // Dynamic keys may be base URLs without wildcards.
    // Remove once keys are migrated to exact paths or wildcard patterns.
    strictMatching: false
}
```

Note

従来の一致 (`strictMatching: false`) は下位互換性のために提供されており、今後のメジャー バージョンでは削除される可能性があります。

###### 実行時警告

`MsalInterceptor`が明示的に構成されていない場合、初期化中に MSAL ロガーを介して `strictMatching`が出力されます。 この警告が表示された場合は、上記の修正オプションに従ってください。

#### オプションの authRequest

`authRequest`で設定できるオプションの`MsalInterceptorConfiguration`の詳細については、[こちらのマルチテナント ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/multi-tenant#dynamic-auth-request)を参照してください。

### msal-angular v1 から v2 への変更

Note

MSAL Angular v1 の`unprotectedResourceMap`の`MsalAngularConfiguration`は非推奨となり、機能しなくなりました。

- `protectedResourceMap` は `MsalInterceptorConfiguration` オブジェクトに移動され、 `Map<string, Array<string|ProtectedResourceScopes>>`として渡すことができます。 `MsalAngularConfiguration` は非推奨となり、機能しなくなりました。
- すべてのルートを保護するためにルート ドメインを `protectedResourceMap` に配置することはサポートされなくなりました。 代わりにワイルドカード マッチングを使用してください。

スコープを構成する方法の詳細については、 [FAQ](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/FAQ.md) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/multi-tenant"} -->
## マルチテナント アプリケーションのサポート - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/multi-tenant
- Service: msal / msal-angular
- Article date: 2025-05-21
- Summary: MSAL Angular でのマルチテナント アプリケーションのサポートの詳細

既定では、MSAL は、構成で指定されていない場合に、権限内のテナントを "共通" に設定するため、アプリケーションのマルチテナント サポートがあります。これにより、すべてのMicrosoft アカウントがアプリケーションに対して認証できるようになります。 マルチテナントの動作に関心がない場合は、次に示すように、`authority`で MSAL をインスタンス化するときに、`app.module.ts`構成プロパティを設定する必要があります。

```js
@NgModule({
  imports: [
    MsalModule.forRoot({ // MSAL Configuration
      auth: {
        clientId: 'CLIENT_ID_HERE',
        authority: 'https://login.microsoftonline.com/TENANT_ID_HERE',
        redirectUri: 'http://localhost:4200',
        postLogoutRedirectUri: 'http://localhost:4200'
      },
      // Additional configuration here
    });
  ]
})
export class AppModule {}
```

マルチテナント認証を許可し、すべてのMicrosoft アカウントユーザーにアプリケーションの使用を許可しない場合は、ログインが許可されているテナントのみにトークン発行者をフィルター処理する独自の方法を指定する必要があります。

### テナントの変更

次に示すように、関連するコンポーネントで MSAL の新しいインスタンスをインスタンス化することで、テナントを動的に設定することもできます。

```js
import { PublicClientApplication } from '@azure/msal-browser';
import { MsalService } from '@azure/msal-angular';

@Component({})
export class AppComponent implements OnInit {
  constructor(
    private authService: MsalService
  ) {}

  ngOnInit(): void {
    this.authService.instance = new PublicClientApplication({
      auth: {
        clientId: 'CLIENT_ID_HERE',
        authority: 'https://login.microsoftonline.com/TENANT_ID_HERE',
        redirectUri: 'http://localhost:4200',
        postLogoutRedirectUri: 'http://localhost:4200'
      }
    });
  }
```

### 動的認証リクエスト

既定では、MsalGuard と MsalInterceptor は構成で設定された静的プロパティを使用します。どちらも、認証に使用されるパラメーターを動的に変更できるように、 `authRequest`のメソッドを使用して構成することもできます。

#### MsalInterceptor - 動的認証要求 (マルチテナント トークン)

`organizations`または`common`がテナントとして使用されている場合は、すべてのトークンがユーザーのホーム テナントに対して要求されます。 ただし、これは望ましい結果ではない可能性があります。 ユーザーがゲストとして招待された場合、トークンは間違った機関から取得されている可能性があります。

`authRequest` のをメソッドに設定すると、認証要求を動的に変更できます。 たとえば、ゲスト ユーザーを使用するときに、アカウントのホーム テナントに基づいて権限を設定できます。 `authRequest`のプロパティは変更できますが、常に次のように`originalAuthRequest`を拡張する必要があります。

```js
export function MSALInterceptorConfigFactory(): MsalInterceptorConfiguration {
  const protectedResourceMap = new Map<string, Array<string>>();
  protectedResourceMap.set("https://graph.microsoft.com/v1.0/me", ["user.read"]);
  
  return {
    interactionType: InteractionType.Popup,
    protectedResourceMap,
    authRequest: (msalService, httpReq, originalAuthRequest) => {
      return {
        ...originalAuthRequest,
        authority: `https://login.microsoftonline.com/${originalAuthRequest.account?.tenantId ?? 'organizations'}`
      };
    }
  };
}
...

@NgModule({
  declarations: [...],
  imports: [...],
  providers: [
    ...
    {
      provide: MSAL_INTERCEPTOR_CONFIG,
      useFactory: MSALInterceptorConfigFactory
    }
  ]
});

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/performance"} -->
## クライアント側ナビゲーションにルーターを使用するように MSAL を構成する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/performance
- Service: msal / msal-angular
- Article date: 2025-05-21
- Summary: クライアント側ナビゲーションにルーターのナビゲーション機能を使用するように MSAL Angular を構成する方法

既定では、MSAL.js アプリケーション内の 1 つのページから別のページに移動する必要がある場合は、 `window.location`再割り当てされ、フレーム全体がもう一方のページにリダイレクトされ、アプリケーションが再レンダリングされます。 Angular Router を使用している場合は、ルーターが "クライアント側" ナビゲーションを有効にし、必要に応じてページの部分のみを表示または非表示にするため、これは望ましくない可能性があります。

現在、MSAL.js がアプリケーション内のあるページから別のページに移動するシナリオがあります。 アプリケーションで次 **のすべてを** 実行している場合は、引き続きお読みください。

- アプリケーションがポップアップ フローではなくリダイレクト フローを使用してログインしている
- `PublicClientApplication` が `auth.navigateToLoginRequestUrl: true` で構成されている (既定)
- アプリケーションには、共有の`redirectUri`を使用して`loginRedirect`/`acquireTokenRedirect`を呼び出す可能性があるページがあります。つまり、`http://localhost` を redirectUri として使用して、`http://localhost/protected` から `loginRedirect` を呼び出します。

アプリケーションで上記のすべての処理を行っている場合は、MSAL が使用するメソッドをオーバーライドして、 `MsalCustomNavigationClient` をインポートし、 `setNavigationClient`を呼び出します。

**注**: セキュリティ修正のため、`MsalCustomNavigationClient`が true に設定され、リダイレクトを処理している場合、`Router`は Angular `navigateToLoginRequestUrl`を使用してクライアント側を移動しません。 これは、今後のリリースで対処される既知の問題です。

### 実装例

次の例では、Angular `Router`を使用するときにこれを実装する方法を示します。 Angular Router の詳細については、 [こちらを](https://angular.io/guide/router)参照してください。Angular 用にこれを実装する完全なサンプル アプリ [については、こちらを参照してください](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/samples/msal-angular-v2-samples/angular10-sample-app)。

```javascript
import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { MsalService, MsalBroadcastService, MSAL_GUARD_CONFIG, MsalGuardConfiguration, MsalCustomNavigationClient } from '@azure/msal-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  constructor(
    @Inject(MSAL_GUARD_CONFIG) private msalGuardConfig: MsalGuardConfiguration,
    private authService: MsalService,
    private msalBroadcastService: MsalBroadcastService,
    private router: Router,
    private location: Location
  ) {
    const customNavigationClient = new MsalCustomNavigationClient(this.authService, this.router, this.location);
    this.authService.instance.setNavigationClient(customNavigationClient);
  }

  ngOnInit(): void {
    // Additional code
  }
}

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/public-apis"} -->
## MSAL Angular で一般的に使用されるパブリック API - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/public-apis
- Service: msal / msal-angular
- Article date: 2025-05-21
- Summary: MSAL Angular で一般的に使用されるパブリック API

ここで開始する前に、 [アプリケーション オブジェクトを初期化](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/initialization)する方法を理解していることを確認してください。

MSAL のログイン API は、サインインしているユーザーの [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)と交換できる`authorization code`を取得します。一方、追加のリソースのスコープと、アプリが API を安全に呼び出せるように、ユーザーが同意したスコープを含む[アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)を取得します。 [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)の詳細を確認します。

### パブリック API

`@azure/msal-angular` では、構成と共に次のものが公開されます。 プロパティとメソッドについては、ライブラリ参照を [参照](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_angular.html) してください。

1. [`MsalService`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.service.ts/)
2. [`MsalGuard`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.guard.ts/)
    - [`MsalGuardConfiguration`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.guard.config.ts/)
3. [`MsalInterceptor`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.interceptor.ts/)
    - [`MsalInterceptorConfiguration`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.interceptor.config.ts/)
4. [`MsalBroadcastService`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.broadcast.service.ts/)
5. [`MsalModule`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.module.ts/)

Angular オブザーバブルを使用したログイン関数と取得トークン関数は、 [IMsalService](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/IMsalService.ts/) にあります。

`@azure/msal-angular` また、次の情報も公開します。

1. [`MsalRedirectComponent`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.redirect.component.ts): リダイレクトの処理に使用されます。 詳細については、 [リダイレクトドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/redirects) を参照してください。
2. [`MsalCustomNavigationClient`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.navigation.client.ts): クライアント側のナビゲーションに使用されます。 詳細については、 [パフォーマンスに関するドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/performance) を参照してください。

`@azure/msal-browser`のその他の関数については、[`IPublicClientApplication`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/src/app/IPublicClientApplication.ts)に対応するドキュメント[を参照してください](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/login-user.md)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/redirects"} -->
## MSAL Angular でのリダイレクトの使用 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/redirects
- Service: msal / msal-angular
- Article date: 2026-03-06
- Summary: MSAL Angular でリダイレクトを使用する方法について説明します

MSAL でリダイレクトを使用する場合は、または`MsalRedirectComponent`でリダイレクトを処理する`handleRedirectObservable`。

以下の Angular スタンドアロン コンポーネントで MSAL Angular を使用するための具体的なガイダンスが追加されていることに注意してください。

1. `MsalRedirectComponent`
2. `handleRedirectObservable`を手動で購読
3. スタンドアロン コンポーネントを使用したリダイレクト

### 1. `MsalRedirectComponent`: 専用の `handleRedirectObservable` コンポーネント

Note

この方法は、Angular スタンドアロン コンポーネントと互換性がありません。 詳細なガイダンスについては、 以下のスタンドアロン コンポーネントでのリダイレクト に関するセクションを参照してください。

これは、リダイレクトを処理するための推奨されるアプローチです。

- `@azure/msal-angular` には、アプリケーションにインポートできる専用のリダイレクト コンポーネントが用意されています。 `MsalRedirectComponent`をインポートし、これをアプリケーションの`AppComponent`と共に`app.module.ts`にブートストラップすることをお勧めします。これは、コンポーネントが手動で`handleRedirectObservable()`サブスクライブしなくてもすべてのリダイレクトを処理するためです。
- リダイレクト後に機能 (ユーザー アカウント機能、UI の変更など) を実行したいページは、`InteractionStatus.None` でフィルターした `inProgress$` Observable をサブスクライブする必要があります。 これにより、関数の実行時に進行中の相互作用が発生しないようにします。 最後の、つまり最新の `InteractionStatus` も、`inProgress$` Observable をサブスクライブした際に利用できることに注意してください。 相互作用のチェックの詳細については、 [イベント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/events#the-inprogress-observable) に関するドキュメントを参照してください。
- `MsalRedirectComponent`を使用しない場合は、以下の方法で説明するとおり、`handleRedirectObservable()`を使ってリダイレクトを**必ず**自分で処理する必要があります。
- このアプローチの例については [、Angular モジュールのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-modules-sample/src/app/app.module.ts#L110) を参照してください。

msal.redirect.component.ts

```js
// This component is part of @azure/msal-angular and can be imported and bootstrapped
import { Component, OnInit } from "@angular/core";
import { MsalService } from "./msal.service.ts";

@Component({
  selector: 'app-redirect', // Selector to be added to index.html
  template: ''
})
export class MsalRedirectComponent implements OnInit {
  
  constructor(private authService: MsalService) { }
  
  ngOnInit(): void {    
      this.authService.handleRedirectObservable().subscribe();
  }
  
}

```

index.html

```js
<body>
  <app-root></app-root>
  <app-redirect></app-redirect> <!-- Selector for additional bootstrapped component -->
</body>
```

app.module.ts

```js
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { IPublicClientApplication, PublicClientApplication, InteractionType, BrowserCacheLocation, LogLevel } from '@azure/msal-browser';
import { MsalGuard, MsalInterceptor, MsalBroadcastService, MsalInterceptorConfiguration, MsalModule, MsalService, MSAL_GUARD_CONFIG, MSAL_INSTANCE, MSAL_INTERCEPTOR_CONFIG, MsalGuardConfiguration, MsalRedirectComponent } from '@azure/msal-angular'; // Redirect component imported from msal-angular

export function loggerCallback(logLevel: LogLevel, message: string) {
  console.log(message);
}

export function MSALInstanceFactory(): IPublicClientApplication {
  return new PublicClientApplication({
    auth: {
      clientId: '00001111-aaaa-2222-bbbb-3333cccc4444',
      redirectUri: 'http://localhost:4200',
      postLogoutRedirectUri: 'http://localhost:4200'
    },
    cache: {
      cacheLocation: BrowserCacheLocation.LocalStorage,
    },
    system: {
      loggerOptions: {
        loggerCallback,
        logLevel: LogLevel.Info,
        piiLoggingEnabled: false
      }
    }
  });
}

export function MSALInterceptorConfigFactory(): MsalInterceptorConfiguration {
  const protectedResourceMap = new Map<string, Array<string>>();
  protectedResourceMap.set('https://graph.microsoft.com/v1.0/me', ['user.read']);

  return {
    interactionType: InteractionType.Redirect,
    protectedResourceMap
  };
}

export function MSALGuardConfigFactory(): MsalGuardConfiguration {
  return { interactionType: InteractionType.Redirect };
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatButtonModule,
    MatToolbarModule,
    MatListModule,
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
  bootstrap: [AppComponent, MsalRedirectComponent] // Redirect component bootstrapped here
})
export class AppModule { }

```

app.component.ts

```js
import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import { MsalBroadcastService, InteractionStatus } from '@azure/msal-angular';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit, OnDestroy {
  private readonly _destroying$ = new Subject<void>();

  constructor(
    private msalBroadcastService: MsalBroadcastService
  ) {}

  ngOnInit(): void {
    this.msalBroadcastService.inProgress$
      .pipe(
        filter((status: InteractionStatus) => status === InteractionStatus.None),
        takeUntil(this._destroying$)
      )
      .subscribe(() => {
        // Do user account/UI functions here
      })
  }
```

### 2. `handleRedirectObservable` を手動で購読する

これは推奨される方法ではありませんが、`MsalRedirectComponent`をブートストラップできない場合は、次のようにを使用してリダイレクトを処理`handleRedirectObservable`。

- `handleRedirectObservable()` は、リダイレクトが発生する可能性のある **すべての** ページでサブスクライブする必要があります。 MSAL Guard によって保護されたページでは、リダイレクトは MSAL Guard で処理されるため、`handleRedirectObservable()` を購読する必要はありません。
- ユーザー アカウントに関連するアクションへのアクセスまたは実行は、 `handleRedirectObservable()` が完了するまで実行しないでください。それまでは完全に設定されない可能性があるためです。 さらに、 `handleRedirectObservables()` の進行中に対話型 API が呼び出されると、 `interaction_in_progress` エラーが発生します。 相互作用のチェックの詳細については[イベント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/events#the-inprogress-observable)に関するドキュメント、 エラーの詳細については`interaction_in_progress`に関するドキュメントを参照してください。
- このアプローチの例については [、MSAL Angular モジュールのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-modules-sample/src/app/app.component.ts) を参照してください。

home.component.ts ファイルの例:

```js
import { Component, OnInit } from '@angular/core';
import { MsalBroadcastService, MsalService } from '@azure/msal-angular';
import { AuthenticationResult } from '@azure/msal-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authService: MsalService) { }

  ngOnInit(): void {
    this.authService.handleRedirectObservable().subscribe({
      next: (result: AuthenticationResult) => {
        // Perform actions related to user accounts here
      },
      error: (error) => console.log(error)
    });
  }

}
```

#### `handleRedirectObservable` のオプション

`handleRedirectObservable` は、次のプロパティを持つ省略可能な `HandleRedirectPromiseOptions` オブジェクトを受け取ります。

| 財産 | タイプ | 説明 |
| --- | --- | --- |
| `hash` | `string` | 現在の URL ハッシュの代わりに処理する省略可能なハッシュ。 |
| `navigateToLoginRequestUrl` | `boolean` | リダイレクトの処理後に元の要求 URL に移動するかどうかを指定します。 既定値は `true` です。 自分でナビゲーションを処理する場合は、 `false` に設定します。 |

使用例:

```js
// Basic usage - processes redirect and navigates to original URL
this.authService.handleRedirectObservable().subscribe();

// Disable automatic navigation after redirect
this.authService.handleRedirectObservable({ navigateToLoginRequestUrl: false }).subscribe({
  next: (result: AuthenticationResult) => {
    if (result) {
      // Handle navigation yourself
      this.router.navigate(['/home']);
    }
  }
});

// Process a specific hash
this.authService.handleRedirectObservable({ hash: '#code=...' }).subscribe();
```

Note

ハッシュ文字列を `handleRedirectObservable(hash)` に直接渡すことは非推奨です。 代わりに options オブジェクトを使用します: `handleRedirectObservable({ hash: "#..." })`。

### 3. スタンドアロン コンポーネントを使用したリダイレクト

スタンドアロン コンポーネントを使用する Angular アプリケーションの多くは `MsalRedirectComponent`をブートストラップできないため、 `handleRedirectObservable` 直接サブスクライブする必要があります。 `app.component.ts` ファイルでサブスクライブすることをお勧めします。

- アプリケーション アーキテクチャによっては、他の領域でも `handleRedirectObservable()` をサブスクライブする必要があります。
- 進行中の相互作用の確認は引き続き適用されます。相互作用のチェックの詳細については、 [イベント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/events#the-inprogress-observable) に関するドキュメントを参照してください。
- このアプローチの例については、 [Angular スタンドアロン](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-angular-samples/angular-standalone-sample) サンプルを参照してください。

`app.component.ts` ファイルの例:

```js
import { Component, OnInit, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MsalService, MsalBroadcastService, MSAL_GUARD_CONFIG, MsalGuardConfiguration } from '@azure/msal-angular';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    standalone: true,
    imports: [CommonModule, RouterModule]
})
export class AppComponent implements OnInit {

  constructor(
    @Inject(MSAL_GUARD_CONFIG) private msalGuardConfig: MsalGuardConfiguration,
    private authService: MsalService,
    private msalBroadcastService: MsalBroadcastService
  ) {}

  ngOnInit(): void {
    this.authService.handleRedirectObservable().subscribe();
  }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/ssosilent"} -->
## ssoSilent() を使用したサイレント ログイン - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/ssosilent
- Service: msal / msal-angular
- Article date: 2025-05-21
- Summary: 'ssoSilent()' API を使用してトークンをサイレントで取得する方法について説明します

認証サーバーとのセッションが既に存在する場合は、 `ssoSilent()` API を使用して、対話なしでトークンの要求を行うことができます。

### ユーザーヒント付き

ユーザーのサインイン情報が既にある場合は、これを API に渡してパフォーマンスを向上させ、承認サーバーが正しいアカウント セッションを探すようにすることができます。 トークンをサイレントモードで正常に取得するために、次のいずれかを要求オブジェクトに渡すことができます。

- `account` (アカウント API を使用して取得できます)
- `sid`(`idTokenClaims` オブジェクトの`account`から取得できます)
- `login_hint` (アカウント オブジェクト `username` プロパティまたは ID トークンの `upn` 要求から取得できます)

アカウントを渡すと、トークン要求で sid が検索され、loginHint (指定されている場合) またはアカウント ユーザー名にフォールバックします。

```js
const silentRequest: SsoSilentRequest = {
    scopes: ["User.Read", "Mail.Read"],
    loginHint: "user@contoso.com"
};

this.authService.ssoSilent(silentRequest)
    .subscribe({
        next: (result) => console.log("Success!"), // Handle result
        error: (error) => console.log(error) // Handle error
    });
```

### ユーザー ヒントなし

ユーザーに関する十分な情報がない場合は、`ssoSilent`、、または`account`を渡`sid`、`login_hint` API の使用を試みることができます。

```javascript
const silentRequest = {
    scopes: ["User.Read", "Mail.Read"]
};
```

ただし、アプリケーションが 1 つのブラウザー セッションで複数のユーザーのコード パスを持っている場合、またはユーザーがその 1 つのブラウザー セッションに対して複数のアカウントを持っている場合は、サイレント サインイン エラーが発生する可能性が高くなります。 承認サーバーによって複数のアカウント セッションが見つかった場合、次のエラーが表示されることがあります。

```txt
InteractionRequiredAuthError: interaction_required: AADSTS16000: Either multiple user identities are available for the current request or selected account is not supported for the scenario.
```

これは、サーバーがサインインするアカウントを決定できなかったことを示し、アカウントを選択するには、上記のパラメーター (`account`、 `login_hint`、 `sid`) または対話型サインインのいずれかが必要になります。

### エラーの処理

ssoSilent() が失敗した場合は、対話形式でログインしてこのエラーを処理することをお勧めします。 アプリケーションの `app.component.ts`で使用されている ssoSilent() の例を次に示します。

```js
import { Component, OnInit } from '@angular/core';
import { MsalService } from '@azure/msal-angular';
import { SilentRequest, SsoSilentRequest } from '@azure/msal-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(
    private authService: MsalService,
  ) {}

  ngOnInit(): void {
    const silentRequest: SsoSilentRequest = {
      scopes: ["User.Read"],
      loginHint: "user@contoso.com"
    }

    this.authService.ssoSilent(silentRequest)
      .subscribe({
        next: (result: AuthenticationResult) => {
          console.log("SsoSilent succeeded!"); // Handle result
        }, 
        error: (error) => {
          this.authService.loginRedirect(); // Handle error by logging in interactively
        }
      });
  }
}

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/universal-ssr"} -->
## MSAL Angular を使用した Angular Universal SSR - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/universal-ssr
- Service: msal / msal-angular
- Article date: 2026-03-15
- Summary: SSR アプリケーション用 Angular Universal および MSAL Angular を使用してサーバー側レンダリングを構成する方法について説明します

Angular Universal は、 `@azure/msal-angular`で最小限サポートされます。 `@azure/msal-angular`は`@azure/msal-browser`用のラッパー ライブラリであり、`window` オブジェクトや`location` オブジェクトなどのブラウザー専用グローバル オブジェクトを使用します。Angular Universal を使用する場合、`@azure/msal-angular`のすべての機能を使用できるわけではありません。 ログインとトークンの取得はサーバー側ではサポートされていませんが、Angular Universal はアプリを中断することなく `@azure/msal-angular` で使用できます。

既存のアプリケーションで Angular Universal をインストールする方法、および[ブラウザー専用グローバル オブジェクト](https://angular.io/guide/universal)の詳細については、[Angular ドキュメント](https://angular.io/guide/universal#working-around-the-browser-apis)の手順を参照してください。

Note

MSAL Angular では、サーバー側およびプリレンダリング機能は正式にはサポートされていません。 MSAL Angular で SSR を使用すると、アプリが壊れる可能性があります。

Angular Universal で `@azure/msal-angular` を使用するには、次の調整を行います。

1. ブラウザー専用オブジェクトへの参照を削除します。 [Angular Modules サンプルには、](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-modules-sample)サーバー側をレンダリングするために削除する必要がある関連する行の横にコメントがあります。 Angular Universal を使用している場合、これらの行を削除してもサンプル アプリには影響しません。

    ```ts
    this.isIframe = window !== window.parent && !window.opener; // Remove this line to use Angular Universal
    ```
2. または、ブラウザー専用グローバル オブジェクトを使用する行の前にチェックを追加することもできます。

    ```ts
    if (typeof window !== "undefined") {
        this.isIframe = window !== window.parent && !window.opener;
    }
    ```
3. `MsalInterceptor`は現在ブラウザー専用オブジェクトを使用するため、アプリによって行われた HTTP 呼び出しにも同じチェックを追加する必要があります。 これは、今後の修正で対処される予定です。 *古い MSAL Angular v2 Angular 11 サンプル アプリ*の[profile.component.ts](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/samples/msal-angular-v2-samples/angular11-sample-app)の次の例を参照してください。

    ```ts
    export class ProfileComponent implements OnInit {
    profile!: ProfileType;
    
        constructor(
            private http: HttpClient
        ) { }
    
        ngOnInit() {
            // This check is added to ensure HTTP calls are made client-side
            if (typeof window !== "undefined") {
                this.getProfile();
            }
        }
    
        getProfile() {
            this.http.get(GRAPH_ENDPOINT)
                .subscribe(profile => {
                    this.profile = profile;
                });
        }
    }
    ```
4. アプリでハッシュ ルーティングが使用されていないことを確認します。 [古い MSAL Angular v2 Angular 11 サンプル アプリ](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/samples/msal-angular-v2-samples/angular11-sample-app)の既定のルーティング戦略はハッシュ ルーティングであるため、`useHash`が`false`でに設定されていることを確認します。

    ```ts
    @NgModule({
        imports: [RouterModule.forRoot(routes, {
            useHash: false,
            initialNavigation: 'enabled'
        })],
        exports: [RouterModule]
    })
    export class AppRoutingModule { }
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/v0-v1-upgrade-guide"} -->
## MSAL Angular v0 から v1 へのアップグレード - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v0-v1-upgrade-guide
- Service: msal / msal-angular
- Article date: 2025-05-21
- Summary: MSAL Angular v0 を使用してアプリケーションを V1 にアップグレードする方法について説明します

MSAL Angular v1 では、Angular ラッパーが最新バージョンの MSAL コアに対応し、さらに Angular（6 以降）および rxjs（6）を標準でサポートします。

このガイドでは、既存のアプリケーションを `@azure/msal-angular@0.x` から `@azure/msal-angular@1.0.0` に移行するために必要な変更について説明します。

変更の詳細な一覧については、 [CHANGELOG](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/CHANGELOG.md) を参照してください。

MSAL Angular v1 のドキュメント [については、こちらをご覧ください](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/lib/msal-angular/docs/v1-docs/)。

### Installation

MSAL Angular に対する最初の基本的な変更は、コア `msal` パッケージが通常の依存関係ではなく、 [代わりにピアの依存関係](https://nodejs.org/en/blog/npm/peer-dependencies/)であるということです。 つまり、アプリケーションには、MSAL Angular に依存するのではなく、通常の依存関係として `msal` も含める必要があります。 これにより、アプリケーションで最新バージョンの `msal` を使用するか (推奨)、最新バージョンの MSAL Angular 自体を引き続き使用しながら、カスタム バージョン/範囲を選択できます。 ただし、ピアの依存関係に対して提供される semver 範囲を満たすバージョンを提供する必要があります。そうしないと、MSAL Angular が意図したとおりに機能しない可能性があります。

ステップ:

1. `msal`と`@azure/msal-angular`: `npm install msal@beta @azure/msal-angular@beta`をインストールします。

### MSAL.js v1 の破壊的変更

`msal@1` には、 `msal@0.2.x`からの破壊的変更が多数含まれています。 これらの多くはアプリケーションから抽象化する必要がありますが、コードの変更が必要になるものがいくつかあります。

#### MsalModule.forRoot が 2 つの引数を受け取るようになりました。

以前は、MSAL Angular は、 `MsalModule.forRoot()`を介して 1 つの構成オブジェクトを受け入れていました。 `msal@1`に合わせ、MSAL Angular の柔軟性を高めるために、これはコア ライブラリ用とラッパー用の 2 つのオブジェクトに分割されています。

ステップ:

1. 最初の引数は、`Configuration`に渡す`msal`です。
2. 2 番目の引数は、[`MsalAngularConfiguration object`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-angular-v1/lib/msal-angular/src/msal-angular.configuration.ts)、`consentScopes`、`popUp`、および`extraQueryParameters`の値を含む`protectedResourceMap`です。 `unprotectedResources` は非推奨となりました。

これらの構成オブジェクトを渡す方法の例については、 [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-samples/angular6-sample-app/src/app/app.module.ts) を参照してください。

#### AOT モード エラーの軽減策

新しい `msal` 構成オブジェクトは、 `system.logger` と `framework.protectedResourceMap`の関数を受け取ります。これは、 `aot` モードで実行すると正しく機能しません。 次の 2 つの回避策を使用できるようになりました。

1. `protectedResourceMap` は `MsalAngularConfiguration` オブジェクトに移動され、 `[string, string[]][]` または `Map`として渡すことができます。 `framework.protectedResourceMap` は引き続き機能しますが、非推奨となりました。 使用方法については、 [更新されたサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-samples/angular6-sample-app/src/app/app.module.ts) を参照してください。
2. `logger``MsalService.setLogger()`を使用して動的に設定できるようになりました。 使用方法については、 [更新されたサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-samples/angular6-sample-app/src/app/app.component.ts) を参照してください。

#### その他の重大な変更

- `acquireToken`メソッドと`login` メソッドは、パラメーターとして 1 つの`AuthenticationParameters` オブジェクトを受け取るようになりました。
- `getUser()` は `getAccount()` になりました。
- ブロードキャスト イベントで、文字列だけでなくオブジェクトが出力されるようになりました。
- `Redirect` メソッドを使用するアプリケーションでは、`handleRedirectCallback` メソッドを実装する必要があります (また、ページの読み込みごとに実行する必要があります)。これによって、リダイレクト操作の結果がキャプチャされます。 実装方法の例については、 [Angular](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-samples/angular6-sample-app/src/app/app.component.ts) サンプルを参照してください。

### Angular 6+ と rxjs@6

MSAL Angular では、アプリケーションが `@angular/core@>=6`、 `@angular/common@>=6`、 `rxjs@6`でビルドされていることが想定されるようになりました。 また、 `rxjs-compat` は不要になりました。

ステップ:

1. Angular と rxjs の新しいバージョンをインストールします。 `npm install @angular/core @angular/common rxjs`
2. `rxjs-compat`をアンインストールします (他のライブラリでは必要ない場合)。`npm uninstall rxjs-compat`

### Samples

Angular 6、7、8、9 の基本的なサンプル アプリケーションをまとめています。 これらのサンプルは、基本的な構成と使用方法を示しており、段階的に改善および追加されます。 また、より多くのシナリオやユース ケース用のサンプルを追加する予定です。

- [Angular 6](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/samples/msal-angular-samples/angular6-sample-app)
- [Angular 7](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/samples/msal-angular-samples/angular7-sample-app)
- [Angular 8](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/samples/msal-angular-samples/angular8-sample-app)
- [Angular 9](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/samples/msal-angular-samples/angular9-sample-app)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/v1-v2-upgrade-guide"} -->
## MSAL Angular v1 から v2 へのアップグレード - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v1-v2-upgrade-guide
- Service: msal / msal-angular
- Article date: 2025-05-21
- Summary: MSAL Angular v1 を使用してアプリケーションを V2 にアップグレードする方法について説明します

MSAL Angular v2 では、Angular 用ラッパーが最新バージョンの MSAL Common に対応し、さらに Angular（9～12）および rxjs（6）の最新バージョンを追加設定なしでサポートします。

このガイドでは、既存のアプリケーションを `@azure/msal-angular` v1 から v2 に移行するために必要な変更について説明します。

MSAL Angular v2 のドキュメントについては、 [こちらをご覧ください](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/lib/msal-angular/docs/v2-docs/)。

### Installation

MSAL Angular v2 の最初の基本的な変更は、コア `msal` パッケージを使用しなくなったが、 `@azure/msal-browser` パッケージを [ピア依存関係](https://nodejs.org/en/blog/npm/peer-dependencies/)としてラップすることです。

まず、現在使用されている MSAL の以前のバージョンをすべてアンインストールします。

`@azure/msal-browser`と`@azure/msal-angular`をインストールするには:

```
npm install @azure/msal-browser @azure/msal-angular@latest
```

### `@azure/msal-browser@2` での破壊的変更

`@azure/msal-browser@2` には、 `msal@1.x`からの破壊的変更が多数含まれています。 これらの多くはアプリケーションから抽象化する必要がありますが、コードの変更が必要になるものがいくつかあります。

#### MsalModule.forRoot が 3 つの引数を受け取るようになった

以前 `@azure/msal-angular` 、 `MsalModule.forRoot()`を介して 2 つの構成オブジェクトを受け入れ、1 つはコア ライブラリ用、1 つは `@azure/msal-angular`用です。 これは、MSAL のインスタンスと、2 つの Angular 固有の構成オブジェクトを取り込むよう変更されました。

1. 最初の引数は MSAL インスタンスです。 これは、MSAL をインスタンス化するファクトリとして、または構成で MSAL のインスタンスを渡すことによって提供できます。
2. 2 番目の引数は [`MsalGuardConfiguration`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/lib/msal-angular/src/msal.guard.config.ts) オブジェクトであり、 `interactionType` と省略可能な `authRequest` と省略可能な `loginFailedRoute`を指定します。
3. 3 番目の引数は [`MsalInterceptorConfiguration`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/lib/msal-angular/src/msal.interceptor.config.ts) オブジェクトであり、 `interactionType`、 `protectedResourceMap`、および省略可能な `authRequest`の値が含まれます。 `unprotectedResourceMap` は非推奨となりました。

詳細については、[構成ドキュメント](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/lib/msal-angular/docs/v2-docs/configuration.md)および[MsalInterceptor](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/lib/msal-angular/docs/v2-docs/msal-interceptor.md)と[MsalGuard](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/lib/msal-angular/docs/v2-docs/msal-guard.md)に関する個別のドキュメントを参照してください。 これらの構成オブジェクトを渡す方法の例については、 [更新されたサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-v2-samples/angular10-sample-app/src/app/app.module.ts) も参照してください。

#### Logger

- `logger` は、`logger` のインスタンスではなく、`system.loggerOptions` 配下の、`loggerCallback`、`piiLoggingEnabled`、`logLevel` を含む MSAL インスタンスの構成で設定されるようになりました。 `logger`は、`MsalService.setLogger()`を使用して動的に設定することもできます。 使用の詳細と[`logger documentation`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/lib/msal-angular/docs/v2-docs/logging.md)については、を参照してください。

#### API の変更

- `acquireToken`メソッドと`login` メソッドは、異なる要求オブジェクトをパラメーターとして受け取るようになりました。 詳細については、 [msal.service.ts](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/lib/msal-angular/src/msal.service.ts) を参照してください。
- ブロードキャスト イベントは、文字列だけでなく、 `EventMessage` オブジェクトを出力するようになりました。 実装方法の例については、 [Angular](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-v2-samples/angular10-sample-app/src/app/app.component.ts) サンプルを参照してください。
- `Redirect`メソッドを使用するアプリケーションでは、`MsalRedirectComponent`とブートストラップを、すべてのリダイレクトを処理するapp.component.ts内の`AppComponent`と共にインポートする必要があります。 アプリケーションでこれを行うことができない場合は、 `handleRedirectObservable` メソッドを実装する必要があります (また、すべてのページ読み込み時に実行する必要があります)。これにより、リダイレクト操作の結果がキャプチャされます。 詳細については、 [リダイレクトのドキュメント](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/lib/msal-angular/docs/v2-docs/redirects.md) を参照してください。

#### MSAL インターセプター

- 現在のの構成と v1 と v2 の違いの詳細については、`MsalInterceptor`を参照してください。

#### MSAL Guard

- 現在のの構成と v1 と v2 の違いの詳細については、`MsalGuard`を参照してください。

#### Accounts

- アカウント情報を取得する前に、監視可能な `inProgress$` をサブスクライブし、 `InteractionStatus.None` をフィルター処理することをお勧めします。 これにより、アカウント情報を取得する前にすべての操作が完了します。 この使用例については、 [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-v2-samples/angular10-sample-app/src/app/app.component.ts#L27) を参照してください。
- アカウントを取得するときは、MSAL インスタンスで使用できる `getAccountByHomeId()` と `getAccountByLocalId()`を使用することをお勧めします。 `getAccount()` は現在 `getAccountByUsername()`されていますが、信頼性が低く、便宜上のみであるため、セカンダリの選択肢にする必要があります。
- `getAllAccounts()` は、MSAL インスタンスでも使用できます。 アカウントの方法の詳細については、に関する`@azure/msal-browser`を参照してください。
- さらに、 `getActiveAccount()` と `setActiveAccount()`を使用してアクティブなアカウントを取得および設定できるようになりました。 詳細については、 [FAQ](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/lib/msal-angular/docs/v2-docs/FAQ.md#how-do-i-get-and-set-active-accounts) を参照してください。

### Angular 9+ と rxjs@6

MSAL Angular では、アプリケーションが `@angular/core@>=9`、 `@angular/common@>=9`、 `rxjs@6`でビルドされていることが想定されるようになりました。 MSAL Angular v1 と同様に、 `rxjs-compat` は必要ありません。

ステップ:

1. Angular と rxjs の新しいバージョンをインストールします。 `npm install @angular/core @angular/common rxjs`
2. `rxjs-compat`をアンインストールします (他のライブラリでは必要ない場合)。`npm uninstall rxjs-compat`

### Samples

Angular 9、10、11、12 の基本的なサンプル アプリケーションをまとめています。 これらのサンプルは、基本的な構成と使用方法を示しており、段階的に改善および追加されます。

MSAL Angular v2 サンプルの一覧と、示されている機能については、 [こちらを](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/msal-lts/samples/msal-angular-v2-samples/README.md) 参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/v2-v3-upgrade-guide"} -->
## MSAL Angular v2 から v3 へのアップグレード - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v2-v3-upgrade-guide
- Service: msal / msal-angular
- Article date: 2026-03-15
- Summary: 重大な変更や Angular 15 以上の要件など、アプリケーションを MSAL Angular v2 から v3 に移行する方法について説明します

MSAL Angular v3 では、Angular ラッパーが最新バージョンの MSAL Browser に対応し、さらに Angular 15、16、17、18 および rxjs 7 を標準でサポートします。

このガイドでは、既存のアプリケーションを `@azure/msal-angular` v2 から v3 に移行するために必要な変更について説明します。

`@azure/msal-angular` v1 から移行する場合は、まず [v1-v2 移行ガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v1-v2-upgrade-guide)を参照して MSAL v2 に移行してください。

ブラウザーのサポートやその他の重要な変更については、 [MSAL Browser Migration Doc](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser/docs/v2-migration.md) も参照してください。

### `@azure/msal-angular@3` での破壊的変更

#### 初期化

##### リダイレクトを使用するアプリケーション

MSAL v3.x では、アプリケーション オブジェクトの初期化が必要になりました。 初期化は `MsalRedirectComponent` および `handleRedirectObservable` API に組み込まれており、リダイレクト戦略を実装したアプリケーションは変更を加える必要はありません。 アプリケーションでスタンドアロン コンポーネントを使用している場合は、追加の変更が必要になる場合があります。

アプリケーションでの [リダイレクトの](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/redirects) 処理の詳細については、リダイレクトのガイドを参照してください。

##### ポップアップを使用するアプリケーション

初期化は `MsalRedirectComponent` と `handleRedirectObservable`に組み込まれているため、ポップアップのみを使用するアプリケーションでは、 `MsalRedirectComponent` をブートストラップするか、 `handleRedirectObservable` を手動で呼び出してアプリケーション オブジェクトを初期化する必要もあります。

設定の詳細については [、リダイレクトのガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/redirects) を参照してください。

#### `allowNativeBroker` フラグ

構成では、 `allowNativeBroker` フラグが既定でオンになりました。 B2C 機関を使用している場合は、次のように無効にすることができます。

```js
export function MSALInstanceFactory(): IPublicClientApplication {
    return new PublicClientApplication({
        auth: {
            ...
        },
        cache: {
            ...
        },
        system: {
            allowNativeBroker: false, // Disables native brokering support
        }
    });
}
```

### Angular 15、16、17、18、rxjs@7

MSAL Angular は、次の機能を使用してアプリケーションがビルドされることを想定するようになりました。

- `@angular/core@15` または `@angular/core@16`、あるいは `@angular/core@17` または `@angular/core@18`
- `@angular/common@15` または `@angular/common@16`、あるいは `@angular/common@17` または `@angular/common@18`
- `rxjs@7`

この変更により、MSAL Angular v3 は以前のバージョンの Angular および RxJS と下位互換性がないため、アプリケーションの更新が必要になる場合があります。 [Angular Update Guide](https://update.angular.io/) に従って、アプリケーションを Angular 15、16、17、または 18 に更新してください。

MSAL Angular v2 と同様に、 `rxjs-compat` は必要ありません。

### Samples

次の開発者サンプルが利用可能になりました。

- [Angular 15 サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/v3-lts/samples/msal-angular-v3-samples/angular15-sample-app)
- [Angular 16 サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/v3-lts/samples/msal-angular-v3-samples/angular16-sample-app)
- [B2C を使用した Angular 16 サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/v3-lts/samples/msal-angular-v3-samples/angular-b2c-sample-app)
- [Angular スタンドアロン コンポーネントを使用した Angular 16 サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/v3-lts/samples/msal-angular-v3-samples/angular-standalone-sample)
- [Angular 17 スタンドアロン サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/v3-lts/samples/msal-angular-v3-samples/angular17-standalone-sample)
- [Angular 18 スタンドアロン サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/v3-lts/samples/msal-angular-v3-samples/angular18-standalone-sample)

サンプルは基本的な構成と使用方法を示しており、改善され、増分的に追加される場合があります。

MSAL Angular v3 サンプルの一覧と、示されている機能については、 [こちらを](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/v3-lts/samples/msal-angular-v3-samples/README.md) 参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/v3-v4-upgrade-guide"} -->
## MSAL Angular v3 から v4 へのアップグレード - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v3-v4-upgrade-guide
- Service: msal / msal-angular
- Article date: 2026-03-06
- Summary: セキュリティ更新プログラムや Angular 19 のサポートなど、Angular アプリケーションを MSAL Angular v3 から v4 にアップグレードする方法について説明します。

MSAL Angular v4 には、MSAL Browser のセキュリティ更新プログラムが含まれており、Angular 19 のサポートが既存の Angular 15-18 サポートに追加されています。

ブラウザーのサポートとその他の重要な変更については、 [MSAL Browser v3 移行ガイド](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/v3-migration.md) を参照してください。

### `@azure/msal-angular@4` の変更点

#### ローカル ストレージの使用

ローカル ストレージの暗号化に関連する MSAL Browser の変更により、初期化が完了していること、および対話状態が `None` されていることを確認してから、アカウント API を呼び出してください。

```js
this.msalBroadcastService.inProgress$
    .pipe(
        filter(
            (status: InteractionStatus) => status === InteractionStatus.None
        ),
        takeUntil(this._destroying$)
    )
    .subscribe(() => {
        this.loginDisplay = this.authService.instance.getAllAccounts().length > 0;
    });
```

### Samples

次の開発者サンプルが利用可能になりました。

- [Angular B2C サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-b2c-sample)
- [Angular スタンドアロン サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-standalone-sample)

サンプルは基本的な構成と使用方法を示しており、改善され、増分的に追加される場合があります。

現在の MSAL Angular サンプルと示されている機能の一覧については、 [こちらを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples) 参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/v4-v5-upgrade-guide"} -->
## MSAL Angular v4 から v5 へのアップグレード - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v4-v5-upgrade-guide
- Service: msal / msal-angular
- Article date: 2026-03-15
- Summary: 破壊的変更や移行ガイダンスなど、Angular アプリケーションを MSAL Angular v4 から v5 にアップグレードする方法について説明します。

MSAL Angular v5 には Angular 19 の最小バージョンが必要であり、Angular 15、16、17、18 のサポートが削除されます。

基になる ライブラリでのブラウザーのサポートとその他の重要な変更については、`@azure/msal-browser`を参照してください。

### `@azure/msal-angular@5` での破壊的変更

#### `protectedResourceMap` の厳密な照合

msal-angular v5 では、 `protectedResourceMap` エントリの URL パターン マッチングでは、既定で厳密な照合セマンティクスが使用されます。 厳密一致では、パターンのメタ文字はリテラルとして扱われ、一致対象は URL コンポーネント全体に限定され、ドット区切りをまたがないホストのワイルドカード ルールが適用されます。 v4 構成が緩やかな一致動作に依存している場合は、厳密な一致に合わせて `protectedResourceMap` パターンを更新するか、レガシ動作を一時的に保持するように `strictMatching` を `false` に設定します。 詳細については、 [MSAL Interceptor のドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-interceptor#strict-matching-strictmatching) を参照してください。

Warning

この変更は、v5 のマイナー アップグレードにも影響する可能性があります。 当初採用した v5 のマイナーバージョン（例: 5.0.x）では strict matching がまだデフォルトでなかった場合、strict matching がデフォルトになっている後続の v5 のマイナーバージョン（例: 5.1.x）にアップグレードすると、気付かないうちにトークンのアタッチが機能しなくなる可能性があります。 主な症状はまったく同じです: **401 エラー** — `strictMatching` が明示的に設定されていない場合に実行時警告が出力されるようになりましたが、一致失敗自体は依然として通知されず、`Authorization` ヘッダーも付与されなくなりました。

##### クイック チェックリスト

1. **`protectedResourceMap` キーを確認します。** ワイルドカードやサブパスのないベア ベース URL ( `https://api.example.com` など) のキーは、要求をその URL のサブパスと照合しなくなります。 [一般的な障害パターンを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-interceptor#common-failure-patterns)参照してください。
2. **正確なパスまたはワイルドカードを使用するようにキーを更新します。** 各キーは、アプリケーションが要求した正確な URL と一致するか、 `/*` ワイルドカード サフィックスを使用してサブパスに一致させる必要があります。 [修正オプション](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-interceptor#fix-options)を参照してください。
3. **キーが実行時に動的に読み込まれる場合は**、 `strictMatching: false` を一時的な安全な既定値として設定します。 [環境駆動型構成のガイダンスを参照してください](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-interceptor#guidance-for-environment-driven-configurations)。

##### 環境駆動型 `protectedResourceMap`

`protectedResourceMap` キーが Angular `environment` ファイル、`APP_INITIALIZER`、JSON 構成、または`platformBrowserDynamic`から取得される場合は、移行中に`strictMatching: false`を安全な既定値として設定します。

```javascript
export function MSALInterceptorConfigFactory(): MsalInterceptorConfiguration {
  const protectedResourceMap = new Map<string, Array<string>>();
  protectedResourceMap.set(environment.apiConfig.uri, environment.apiConfig.scopes);

  return {
    interactionType: InteractionType.Redirect,
    protectedResourceMap,
    // TODO: Remove once protectedResourceMap keys are updated to use
    // exact paths or wildcard patterns (e.g. "https://api.example.com/*").
    strictMatching: false,
  };
}
```

すべてのキーを正確なパスまたはワイルドカードに移行したら、 `strictMatching: false` を削除 (または `true`に設定) して、より厳密で安全な照合動作の恩恵を受けます。 詳細については [、環境駆動型構成のガイダンス](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-interceptor#guidance-for-environment-driven-configurations) を参照してください。

#### `logout()` の削除

`logout()` は削除されました。 代わりに、`logoutRedirect()` タグまたは `logoutPopup()` タグを使用してください。

```typescript
// BEFORE (v4)
this.authService.logout();

// AFTER (v5)
this.authService.logoutRedirect();
// or
this.authService.logoutPopup();
```

### `@azure/msal-angular@5` のその他の変更点

#### `inject(TOKEN)` の構文

`MSAL_INSTANCE`、`MSAL_GUARD_CONFIG`、`MSAL_INTERCEPTOR_CONFIG`、および `MSAL_BROADCAST_CONFIG` は、`inject(TOKEN)` 構文をサポートするために、文字列ではなく型として解決されるようになりました。 この変更により、明示的な入力なしでアプリケーションで TypeScript エラーが発生する可能性があります。

#### `handleRedirectObservable()` のオプション

`handleRedirectObservable()`では、オプションの`HandleRedirectPromiseOptions` オブジェクトが受け入れられました。これには、`navigateToLoginRequestUrl`の構成から移動された`@azure/msal-browser@5` オプションが含まれます。 詳細については、 [リダイレクトのドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/redirects#handleredirectobservable-options) を参照してください。

```typescript
// BEFORE (msal-browser v4 configuration)
const msalConfig = {
  auth: {
    clientId: 'your-client-id',
    navigateToLoginRequestUrl: false // This option has moved
  }
};

// AFTER (msal-angular v5)
this.authService.handleRedirectObservable({
  navigateToLoginRequestUrl: false
}).subscribe();
```

Note

ハッシュ文字列を `handleRedirectObservable(hash)` に直接渡すことは非推奨です。 代わりに options オブジェクトを使用します: `handleRedirectObservable({ hash: "#..." })`。

### Samples

次の開発者サンプルが利用可能になりました。

- [Angular B2C サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-b2c-sample)
- [Angular モジュールのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-modules-sample)
- [Angular スタンドアロン サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-standalone-sample)

現在の MSAL Angular サンプルと示されている機能の一覧については、 [こちらを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples) 参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/about-msal-browser"} -->
## MSAL ブラウザーについて - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/about-msal-browser
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: JavaScript アプリケーションで MSAL Browser を使用する方法について説明します

JavaScript 用の MSAL ライブラリを使用すると、クライアント側の JavaScript アプリケーションは、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)の職場および学校アカウント、Microsoft個人アカウント (MSA) とソーシャル ID プロバイダー (Facebook、Google、LinkedIn、Microsoft アカウントなど) を使用して、[Azure AD を使用してユーザーを認証できます。B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview#identity-providers) サービス。 また、アプリがトークンを取得して、[Microsoft Graph](https://www.microsoft.com/enterprise)などの[Microsoft Cloud](https://graph.microsoft.com) サービスにアクセスすることもできます。

`@azure/msal-browser` パッケージでは、PKCE を使用した OAuth 2.0 承認コード フローを使用した JavaScript シングルページ アプリケーションでの認証が有効になります。 暗黙的なフローはサポートされていません。 現在のバージョンは v5.x MSAL.js です。 古いバージョンを使用している場合は、アップグレードの [移行ガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration) を参照してください。

### Prerequisites

- `@azure/msal-browser` は、 [Single-Page アプリケーション のシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-overview)で使用することを意図しています。
- `@azure/msal-browser`を使用する前に、構成の有効なを取得し、アプリがリダイレクト トラフィックを受け入れるルートを登録するには、Microsoft Entra IDに`clientId`を登録する必要があります。

### 主要な機能

MSAL Browser には、シングルページ アプリケーションに対して次の機能が用意されています。

- [ポップアップ フローまたはリダイレクト フローを使用してユーザーをサインインさせる](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user)
- [キャッシュから、または更新を介してトークンをサイレントモードで取得する](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/acquire-token)
- [Cross-Origin-Opener-Policy（COOP）ポップアップフローのサポート](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user)
- [モデル コンテキスト プロトコル (MCP) 認証](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/mcp)
- [プラットフォーム ブローカー (WAM) を介したデバイス バインド トークン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/device-bound-tokens)
- [localStorage 内の AES-GCM で暗号化されたトークンキャッシュ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/caching)
- [所有証明 (PoP) トークン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/access-token-proof-of-possession)
- [タブとアプリケーション間でのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/single-sign-on)
- Microsoft 365 アプリ向けの入れ子式アプリ認証 (NAA)

### Installation

#### npm経由

```javascript
npm install @azure/msal-browser
```

### Samples

[`msal-browser-samples` フォルダー](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples)には、ライブラリのサンプル アプリケーションが含まれています。

サンプルを実行する手順の詳細については、VanillaJSTestApp2.0 フォルダーの [`README.md` ファイル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/Readme.md) を参照してください。

チュートリアルに基づくより高度なサンプルについては、GitHubの[Azureサンプル](https://github.com/Azure-Samples)領域を参照してください。

- [Express.js Web API を呼び出す JavaScript SPA](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/3-Authorization-II/1-call-api)
- [On-Behalf-Of フローを使用して Express.js Web API 経由で Microsoft Graph を呼び出す JavaScript SPA](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/4-AdvancedGrants/1-call-api-graph)
- [Azure App ServiceとAzure Storageのデプロイ に関するチュートリアル](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/5-Deployment)

### フレームワークラッパー

Angular や React などのフレームワークを使用している場合は、ラッパー ライブラリの 1 つを使用することに興味があるかもしれません。

- Angular: [`@azure/msal-angular` (現在: v5.1.1)](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/initialization)
- React: [`@azure/msal-react` (現在: v5.0.6)](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/getting-started)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/access-token-proof-of-possession"} -->
## 所有証明で保護されたアクセス トークンの取得 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/access-token-proof-of-possession
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: アクセス トークン所有証明 (AT PoP) を使用して、MSAL.js ブラウザー アプリケーションでトークンを暗号化的にバインドする方法について説明します

ブラウザーに格納されている OAuth 2.0 アクセス トークンの "トークンリプレイ" に対する保護を強化するために、MSAL は `Access Token Proof-of-Posession` 認証スキームを提供します。 `Access Token Proof-of-Possession`( `AT PoP`) は、アクセス トークンを要求元のブラウザーおよびクライアント アプリケーションに暗号でバインドする認証スキームです。つまり、別のアプリケーションまたはデバイスから使用することはできません。

`AT PoP`がエンド ツー エンドで動作し、目的のセキュリティ アップグレードを提供するためには、アクセス トークンを発行する**承認サービス**と、アクセスを提供する**リソース サーバー**の両方が`AT PoP`をサポートする必要があることを理解しておくことが重要です。

### ベアラー アクセス トークンとバインド (PoP) アクセス トークン

#### ベアラー アクセス トークン

MSAL v2 API によって返される標準的な [認証結果](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#authenticationresult) には、 `accessToken` プロパティが含まれています。 既定の `Bearer` 認証スキームで使用する場合、 `accessToken` プロパティの値は、承認サーバーによって提供されるアクセス トークン シークレットです。 この成果物は MSAL によってキャッシュされ、リソース要求に、要求の `Authorization` ヘッダーのベアラー トークンとして追加する必要があります。

ベアラー アクセス トークンの使用例:

```typescript
// Using the Bearer scheme (default), acquireTokenRedirect returns an AuthenticationResult object containing the Bearer access token secret
const { accessToken } = await myMSALObj.acquireTokenRedirect(popTokenRequest);

// The bearer token secret is appended to the Authorization header
const headers = new Headers();
const authHeader = `Bearer ${accessToken}`; // The Bearer label is used in this header
headers.append("Authorization", authHeader);
```

#### バインドされたアクセス トークン

A.K.A `PoP Token` または `Signed HTTP Request`。 MSAL トークン要求で `POP` 認可スキームが有効になっている場合でも、認可サーバーは引き続き、`Bearer` アクセス トークンのように見える JSON Web Token のアクセス トークン シークレットを提供し、それも MSAL によってキャッシュされます。 主な違いは、 `POP` スキームを使用する場合、そのアクセス トークン シークレットは非対称暗号化キーペアを介してユーザーのブラウザーにバインドされることです。

アクセス トークン シークレットは新しい JSON Web トークンにラップされます。このトークンは、 `HMAC` (ハッシュベースのメッセージ認証コード) ハッシュ アルゴリズムと、MSAL が生成、格納、管理するキーペアの秘密キーを使用して署名されます。 署名された JWT は、`AuthorizationResult` プロパティの下の`accessToken` オブジェクトに追加され、呼び出された MSAL v2 API から返されます。

クライアント アプリケーションは、返された認証結果を受け取ると、認証結果から`accessToken`値を抽出し、`Authorization` ラベルの代わりに `PoP` ラベルを使用して、PoP で保護されたリソース要求の`Bearer` ヘッダーに追加できます。

**注: 署名された JWT (署名済み HTTP 要求または SHR と呼ばれます) は、MSAL によってキャッシュされることはありません。 MSAL v2 API が呼び出されるたびに、MSAL はキャッシュから有効な生アクセス トークン シークレットを取得するか、承認サーバーから新しいアクセス トークンを要求します。 その後、MSAL は、そのアクセス トークンに署名し、認証結果で返します。**

バインド (PoP) アクセス トークンの使用例:

```typescript
// Using the POP scheme (default), acquireTokenRedirect returns an AuthenticationResult object containing the Signed HTTP Request (PoP Token)
const { accessToken } = await myMSALObj.acquireTokenRedirect(popTokenRequest);

// The SHR is appended to the Authorization header
const headers = new Headers();
const authHeader = `PoP ${accessToken}`; // The PoP label is used in this header
headers.append("Authorization", authHeader);
```

### PoP トークン要求の作成

承認サービスとリソース サーバーがアクセス トークン バインドをサポートすることを確認したら、アクセス トークン PoP 固有の属性を含むトークン要求オブジェクトを構築することで、バインドされたアクセス トークンを取得するように MSAL 認証および承認要求オブジェクトを構成できます。 要求オブジェクトでは次の属性はすべて省略可能ですが、 **所有証明を有効にするには authorizationScheme を手動で "pop" に設定する必要があります**。

#### AT PoP リクエスト パラメーター

| 名前 | 説明 | 必須 |
| --- | --- | --- |
| `authenticationScheme` | MSAL が `Bearer` または `PoP` トークンを取得する必要があるかどうかを示します。 既定値は `Bearer` です。 | **必須** |
| `resourceRequestMethod` | 署名されたトークンを使用する要求の HTTP メソッドのすべて大文字の名前 (`GET`、 `POST`、 `PUT`など) | **必須** |
| `resourceRequestUri` | アクセス トークンが発行されている保護されたリソースの URL | **必須** |
| `shrClaims` | SignedHTTPRequest に追加するカスタム クライアント要求を含む文字列化された JSON オブジェクト。 詳細については、 [カスタム SHR 要求](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/shr-client-claims.md) のドキュメントを参照してください。 | *オプション* |
| `shrNonce` | Base64URL が文字列としてエンコードされた、サーバーによって生成された署名付きタイムスタンプ。 この nonce は、PoP トークンの事前生成を可能にするために、クロック スキュー攻撃とタイムトラベル攻撃を軽減するために使用されます。 詳細については、 [SHR サーバー Nonce](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/shr-server-nonce.md) のドキュメントを参照してください。 | *オプション* |

*注: このドキュメントでは、`shrNonce`に`SignedHttpRequest`を追加する方法を示しますが、サーバー nonce 取得パターンは範囲外です。 サーバーで生成されたノンスの取得方法の詳細については、[SHR Server Nonce dcoumentation](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/shr-server-nonce.md#acquiring-a-server-nonce)を参照してください。*

#### トークン リダイレクト要求の取得の例

```typescript
const popTokenRequest = {
    scopes: ["User.Read"],
    authenticationScheme: msal.AuthenticationScheme.POP,
    resourceRequestMethod: "POST",
    resourceRequestUri: "YOUR_RESOURCE_ENDPOINT",
    shrClaims: "{\"shrClaim1\": \"claimValue\"}",
    shrNonce: "NONCE_ACQUIRED_FROM_RESOURCE_SERVER"
}

```

要求が構成され、 `POP` が `authenticationScheme`として設定されたら、 `acquireTokenRedirect` MSAL v2 API に送信できます。

```typescript
const response = await myMSALObj.acquireTokenRedirect(popTokenRequest);

// Once a Pop Token has been acquired, it can be added on the authorization header of a resource request
const headers = new Headers();
const authHeader = `${response.tokenType} ${response.accessToken}`;

headers.append("Authorization", authHeader);

const options = {
    method: popTokenRequest.resourceRequestMethod,
    headers: headers
};

// After the request has been built and the POP access token has bee appended, the request can be executed using an API like "fetch"
fetch(endpoint, options)
    .then(response => response.json())
    .then(response => callback(response, endpoint))
    .catch(error => console.log(error));
});
```

#### トークンをサイレントに取得するリクエストの例

PoP アクセス トークンをサイレントモードで取得するには、対話型の `acquireToken` API と同じ変更がトークン要求構成に必要です。

```typescript
const silentPopTokenRequest = {
    scopes: ["User.Read"],
    authenticationScheme: msal.AuthenticationScheme.POP, // Default is "BEARER"
    resourceRequestMethod: "POST",
    resourceRequestUri: "YOUR_RESOURCE_ENDPOINT",
    shrClaims: "{\"shrClaim1\": \"claimValue\"}",
    shrNonce: "NONCE_ACQUIRED_FROM_RESOURCE_SERVER"
}

// Try to acquire token silently
const { accessToken } = await myMSALObj.acquireTokenSilent(silentPopTokenRequest).catch(async (error) => {
        console.log("Silent token acquisition failed.");
        if (error instanceof msal.InteractionRequiredAuthError) {
            // Fallback to interaction if silent call fails
            console.log("Acquiring token using redirect");
            myMSALObj.acquireTokenRedirect(silentPopTokenRequest);
        } else {
            console.error(error);
        }
    });

// Once a Pop Token has been acquired, it can be added on the authorization header of a resource request
const headers = new Headers();
const authHeader = `PoP ${accessToken}`;

headers.append("Authorization", authHeader);

const options = {
    method: popTokenRequest.resourceRequestMethod,
    headers: headers
};

// After the request has been built and the POP access token has bee appended, the request can be executed using an API like "fetch"
fetch(endpoint, options)
    .then(response => response.json())
    .then(response => callback(response, endpoint))
    .catch(error => console.log(error));
});
```

### PoP キー管理

所有証明認証スキームは、非対称暗号化キーペアに依存して、アクセス トークンをユーザーのブラウザーにバインドします。 MSAL Browser は、最初に承認サービスからアクセス トークンを要求するときにこのキーペアを生成し、 [IndexedDB](https://developer.mozilla.org/en-US/docs/Web/API/IndexedDB_API) を使用して格納します。 この暗号化キーペアは、バインドされたアクセス トークンがサイレントで要求されるたびに、 `SHR` に署名するために使用されます。

バインドされたアクセス トークンを更新した場合、MSAL は期限切れのバインドされたアクセス トークンを要求したときに生成された暗号化キーペアを削除し、新しいアクセス トークンの新しい暗号化キーペアを生成し、キーストアに新しいキーペアを格納します。

### 高度な機能: アプリケーションで管理される暗号化キーペア

Warning

[所有証明プロトコル](https://oauth.net/2/dpop/)に精通しており、独自の暗号化キーペアを生成するための特定の要件がある場合を除き、この機能を使用することはお勧めしません。 ほとんどの場合、このドキュメントの残りの部分で説明されているように PoP の使用をお勧めします。

独自の暗号化キーペアを生成することを選択した場合、この機能により、アプリケーションは `popKid` を要求パラメーターとして指定できます。 MSAL JS は、トークン発行者がトークンに `cnf` を埋め込むが、 *発行されたトークンを符号なし*で返すようにします。 対象のリソースに転送される前にアクセストークンに署名するのは、アプリケーションの責任です。

また、この動作を利用する場合は、を除く残りの `authenticationScheme`が設定されていないことを確認してください。

#### アクセス トークンが非同期的に保存される理由

たとえば、ほとんどの MSAL 資格情報とキャッシュ 項目 ( `ID Tokens` など) は、同期的に格納および削除できます。 これは、これらのキャッシュ項目は `localStorage` または `sessionStorage` (同期的に操作できます) に格納され、非同期アクセス制限を持つ他の格納された項目には依存関係がないためです。

他のキャッシュ項目とは異なり、 `Access Tokens` は非同期的にキャッシュに保存されます。 その理由は、アクセス トークンが暗号化キーペアにバインドされ、 `IndexedDB`に格納されている場合、アクセス トークンを置き換える場合にも、暗号化キーペアを置き換える必要があるということです。 `IndexedDB`へのキーの削除と書き込みが非同期操作であることを考えると、アクセス トークンを保存するプロセスは、必然的に拡張機能によって非同期になります。

### コードサンプル

- [JavaScript SPA による PoP トークンの取得](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/app/pop)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/accounts"} -->
## MSAL ブラウザーのアカウント - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/accounts
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: MSAL Browser でアカウント API を使用してキャッシュされたアカウント オブジェクトを管理およびフィルター処理する方法について説明します

これは、キャッシュされたアカウントにアクセスするための次の API を提供する、 `@azure/msal-browser` ライブラリのプラットフォーム固有のアカウントに関するドキュメントです。

- `getAllAccounts()`: 現在キャッシュ内にあるすべてのアカウントを返します。 特定のアカウント セットを返すオプションのフィルターをサポートします。 アプリケーションは、トークンをサイレントモードで取得するアカウントを選択する必要があります。
- `getAccount()`: 渡されたフィルターに一致する最初のキャッシュされたアカウントを返します。 アカウントがキャッシュから読み取られる順序は任意であり、フィルター処理された一覧の最初のアカウントが、 `getAccount`の 2 つの呼び出しで同じになることは保証されません。 以下で説明するように、フィルター属性の数を増やすと、より正確な一致が提供されます。

### Account Filter オブジェクト

[AccountFilter](https://azuread.github.io/microsoft-authentication-library-for-js/ref/types/_azure_msal_common.AccountFilter.html) 型のドキュメントには、アカウントをフィルター処理するために使用および結合できるプロパティが一覧表示されています。

Note

通常、単一アカウント フィルター属性は、キャッシュされたアカウント オブジェクトを一意に識別することは保証されません。 `homeAccountId` + `localAccountId`など、繰り返されない属性の組み合わせを追加すると、検索を絞り込むことができます。

Note

`realm` がキャッシュに `tenantId` 。

次の `getAccountBy` API は非推奨となりました。 代わりに、適切なフィルター オブジェクトで `getAccount()` を使用してください。

- `getAccountByHomeId()`: 代わりに `getAccount({ homeAccountId })` を使用します。
- `getAccountByLocalId()`: 代わりに `getAccount({ localAccountId })` を使用します。
- `getAccountByUsername()`: 代わりに `getAccount({ username })` を使用します。

これらの API の使用例を次に示します。

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
            // Add account selection code here
            homeAccountId = ...
        } else if (currentAccounts.length === 1) {
            homeAccountId = currentAccounts[0].homeAccountId; // Single account scenario
        }
    }
}
```

次に、 `homeAccountId`、 `localAccountId`、 `username` などのアカウント プロパティを使用して、トークンをサイレントで取得する前に、キャッシュされたアカウントを検索できます。

```javascript
// This method attempts silent token acquisition and falls back on acquireTokenPopup
async function getTokenPopup(request, homeAccountId) {
    // In this case, accounts are filtered by homeAccountId, but more attributes can be added to refine the search and increase the precision of the account filter
    const accountFilter = {
        homeAccountId: homeAccountId,
    };
    request.account = myMSALObj.getAccount(accountFilter);
    return await myMSALObj.acquireTokenSilent(request).catch(async (error) => {
        // Handle error
        return await myMSALObj.acquireTokenPopup(request);
    });
}
```

#### ログイン ヒントによるフィルター処理

`@azure/msal-browser@3.2.0`時点では、すべてのログイン ヒント値を使用してアカウントを検索およびフィルター処理できます。 ログイン ヒントでフィルター処理するために、MSAL は、`AccountFilter` オブジェクトの`loginHint`値を次のアカウント属性 (優先順位順) と比較して一致を検索します。

- `login_hint` ID トークン要求
- `username` account プロパティ
- `upn` ID トークン要求

Note

上記のすべての属性は、 `loginHint` プロパティとしてアカウント フィルターに渡すことができます。 アカウント フィルターでは、 `username` 属性も `username`として受け入れ、よりパフォーマンスの高い検索が生成されます。

##### `login_hint`要求の使用

```javascript
const accountFilter = {
    loginHint: previouslyObtainedIdTokenClaims.login_hint;
};
request.account = myMSALObj.getAccount(accountFilter);
return await myMSALObj.acquireTokenSilent(request).catch(async (error) => {
    // Handle error
    return await myMSALObj.acquireTokenPopup(request);
});
```

##### 'username' の使用

Note

`username`値は、`username`または`loginHint`として`AccountFilter` オブジェクトに含めることができます。 これは、 `username` 要求が、トークン サービスがログイン ヒントとして受け入れる 3 つの値 ( `login_hint` および `upn` ID トークン要求と共) のいずれかであるためです。 アプリケーションで問題の値が `username`であることが確実な場合は、 `AccountFilter.username` プロパティとして設定すると、検索パフォーマンスが向上します。 `username`値を `loginHint` として設定できることは、アプリケーションがログイン ヒントを利用し、その値が`username`、`login_hint`、または`upn`要求から取得されたかどうかに関するコンテキストを保持しない場合に便利です。

`username`を次のように渡す`loginHint`

```javascript
const accountUsername = userProfile.username;
const accountFilter = {
    loginHint: accountUsername;
};
request.account = myMSALObj.getAccount(accountFilter);
return await myMSALObj.acquireTokenSilent(request).catch(async (error) => {
    // Handle error
    return await myMSALObj.acquireTokenPopup(request);
});
```

`username`を次のように渡す`username`

```javascript
const accountUsername = userProfile.username;
const accountFilter = {
    username: accountUsername;
};
request.account = myMSALObj.getAccount(accountFilter);
return await myMSALObj.acquireTokenSilent(request).catch(async (error) => {
    // Handle error
    return await myMSALObj.acquireTokenPopup(request);
});
```

##### `upn`要求の使用

```javascript
const accountFilter = {
    loginHint: previouslyObtainedIdTokenClaims.upn;
};
request.account = myMSALObj.getAccount(accountFilter);
return await myMSALObj.acquireTokenSilent(request).catch(async (error) => {
    // Handle error
    return await myMSALObj.acquireTokenPopup(request);
});
```

### アクティブ なアカウント API

`@azure/msal-browser` ライブラリには、現在 "アクティブ" であり、トークン要求に使用する必要があるアカウントを追跡するのに役立つ便利な API が 2 つ用意されています。

- `getActiveAccount()`: 現在アクティブなアカウントを返します。
- `setActiveAccount()`: アカウント オブジェクトを受け取り、アクティブなアカウントとして設定します。

トークンの取得に使用するアカウントはアプリによって異なりますが、使用するアカウントを決定したら、選択したアカウント オブジェクトで `setActiveAccount()` API を呼び出すだけです。 個々の要求で別のアカウントが指定されていない場合、 `acquireToken`、 `login` 、または `ssoSilent` の呼び出しでは、既定でアクティブ なアカウントが使用されるようになりました。 現在アクティブなアカウントをクリアするには、 `setActiveAccount(null)`を呼び出すことができます。

```javascript
function login() {
    return myMsalObj.loginPopup().then((response) => {
        // After a successful login set the active account to be the user that just logged in
        myMsalObj.setActiveAccount(response.account);
    });
}

function getAccessToken() {
    // Providing an account in the token request is not required if there is an active account set
    return myMsalObj.acquireTokenSilent({ scopes: ["User.Read"] });
}
```

注: バージョン 2.16.0 以降、アクティブなアカウントは、 `PublicClientApplication` インスタンスで構成されたキャッシュの場所に格納されます。 以前のバージョンを使用している場合、アクティブなアカウントはメモリ内に格納されるため、ページの読み込みごとにリセットする必要があります。

#### 入れ子になったアプリ認証

NAA アプリケーションの場合、 `setActiveAccount()` と `getActiveAccount()` は NO-OP API です。 ユーザーはアクティブなアカウントを設定して取得できますが、NAA アプリケーションには常に *1 つの* アカウントが必要であり、アカウントはホスト アプリケーションによって `accountContext`で提供されるため、アクティブに無視されます。 今後、ハブ全体で複数のアカウントがサポートされる場合、この動作は変わると予想されます。

### メモ

- 現在の msal-browser の既定の [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/) には、動作する単一アカウントのシナリオがあります。
- 複数のアカウントのシナリオがある場合は、 [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/app/default/auth.js) ( `handleResponse()`) を変更して、キャッシュされたすべてのアカウントを一覧表示し、特定のアカウントを選択してください。
- アプリケーションが`username`に基づいてアカウントを取得する場合は、`getAccount()` API で`username` フィルターを使用する前に、(特定のユーザーの`login` API の応答から) `username`を保存する必要があります。
- `getAllAccounts()` は、複数の対話型トークン要求を行い、ユーザーが 2 つ以上の対話で異なるアカウントを選択した場合、複数のアカウントを返します。 最初の操作の後にアカウントの選択画面をMicrosoft Entra ID表示するには、対話型の acquireToken またはログイン API に`prompt: "select_account"`または`prompt: "login"`を渡す必要がある場合があります。
- アカウント API はローカル アカウントの状態を返し、必ずしもサーバーの状態を反映しているわけではありません。 以前に MSAL.js を使用してこのアプリにサインインしたアカウントが返され、サーバー セッションがまだアクティブな場合とそうでない場合があります。
- 異なるドメインでホストされている 2 つのアプリは、ブラウザー ストレージがドメインごとにセグメント化されているため、アカウントの状態を共有しません。
- `getAllAccounts()` は順序付けされておらず、複数の呼び出しで同じ順序になることは保証されていません
- acquireToken またはログイン API の呼び出しが成功するたびに、1 つのアカウントが返されます
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/acquire-token"} -->
## アクセス トークンの取得と使用 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/acquire-token
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: アクセス トークンを取得して使用する方法について説明します

アクセス トークンを取得する前に、 [アプリケーション オブジェクトを初期化](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization)する方法を理解しておく必要があります。 また、 [アクセス トークンとリソース](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/resources-and-scopes)の関係を理解することも重要です。

MSAL では、ライブラリによって提供される `acquireToken*` メソッドを使用して、アプリが呼び出す必要がある API のアクセス トークンを取得できます。 `acquireToken*`メソッドは、[OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を使用したトークンの取得に関連する 2 つの手順を抽象化します。

1. `authorization code` を取得するために Microsoft Entra ID に要求します。
2. ユーザーが同意したスコープを含む [アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) のコードを交換する

### アクセス トークンの取得

#### 相互作用の種類を選択する

と`acquireTokenRedirect`の違いがわからない場合は、`acquireTokenPopup`参照してください。

#### 要求オブジェクトを準備する

`acquireToken*` API に要求オブジェクトを渡す必要があります。 このオブジェクトを使用すると、要求で異なるパラメーターを使用できます。 要求オブジェクトのパラメーターの詳細については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/request-response-object) 参照してください。 スコープは、すべての `acquireToken*` 呼び出しに必要です。

#### キャッシュを確認する

MSAL は [キャッシュ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/caching) を使用して、スコープ、リソース、機関などの特定のパラメーターに基づいてトークンを格納し、必要に応じてキャッシュからトークンを取得します。 また、有効期限が切れたときに、それらのトークンのサイレント更新を実行することもできます。 MSAL は、 `acquireTokenSilent` メソッドを使用してこの機能を公開します。

`ssoSilent` API または `login*` API のいずれかを使用してログインすると、キャッシュには一連の ID、アクセス トークン、および更新トークンが含まれます。 アクセス トークンが必要になるたびに、 `acquireTokenSilent` を呼び出す必要があります。失敗した場合は、代わりに対話型 API を呼び出します。 `acquireTokenSilent` はキャッシュ内で有効なトークンを検索し、有効期限が近づいているか、存在しない場合は、キャッシュされた更新トークンを使用して自動的に更新を試みます。 詳細については、`acquireTokenSilent`こちらを参照[してください](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/token-lifetimes#token-renewal)。

##### Popup

```javascript
var request = {
    scopes: ["User.Read"],
};

msalInstance.acquireTokenSilent(request).then(tokenResponse => {
    // Do something with the tokenResponse
}).catch(async (error) => {
    if (error instanceof InteractionRequiredAuthError) {
        // fallback to interaction when silent call fails
        return msalInstance.acquireTokenPopup(request);
    }

    // handle other errors
})
```

##### リダイレクト

```javascript
var request = {
    scopes: ["User.Read"],
};

msalInstance.acquireTokenSilent(request).then(tokenResponse => {
    // Do something with the tokenResponse
}).catch(error => {
    if (error instanceof InteractionRequiredAuthError) {
        // fallback to interaction when silent call fails
        return msalInstance.acquireTokenRedirect(request)
    }

    // handle other errors
});
```

### アクセス トークンの使用

アクセス トークンを取得したら、次に示すように、トークンを取得したリソースへの要求の`Authorization`として、 ヘッダーに含める必要があります。

```JavaScript
var headers = new Headers();
var bearer = "Bearer " + tokenResponse.accessToken;
headers.append("Authorization", bearer);
var options = {
        method: "GET",
        headers: headers
};
var graphEndpoint = "https://graph.microsoft.com/v1.0/me";

fetch(graphEndpoint, options)
    .then(resp => {
        //do something with response
    });
```

### MSAL トークン取得のベスト プラクティス

エラー、パフォーマンス ヒット、および使いやすさの問題を回避するために、MSAL でトークンを取得するためのベスト プラクティスを次に示します。 特定のシナリオでは、これらの例外が提供される場合があります。

#### 1 つの PublicClientApplication インスタンスを使用する

アプリケーションごとに 1 つの `PublicClientApplication` をインスタンス化し、アプリ全体で同じインスタンスを使用します。 これにより、MSAL が常に実行している内容 ( [MSAL イベント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/events)を参照) に関する信頼できるソースが 1 つ存在することが保証され、個別のアプリ オブジェクトが並列の対話型要求やキャッシュ競合を発生させる可能性がなくなり、アプリが中断されたり、パフォーマンスが低下したり、ユーザー エクスペリエンスが妨げられたりする可能性があります。

#### 常に約束が解決されるのを待つ

すべての MSAL `acquireToken*` と `login*` API は非同期操作を実行し、promise を返します。 ユーザー情報のレンダリング、保護された API の呼び出し、他の MSAL API の呼び出しなど、認証状態またはトークンに依存する他のタスクを実行する前に、これらの約束が解決されるまで常に待つ必要があります。

#### まずサイレント リクエストを試し、次に対話型リクエストを試します

トークンを要求するときは、常に最初に `acquireTokenSilent` を使用し、必要に応じて対話型トークンの取得にフォールバックします (たとえば、 `InteractionRequiredAuthError` がスローされたとき)。

複数のサイレント リクエストを同時に実行できます。 2 つ以上のサイレント要求が同時に行われると、(必要に応じて) 1 つの要求のみがネットワークに送信されますが、それらの要求が同じ要求パラメーター (スコープなど) に対するものである限り、すべてが応答を受け取ります。

同時対話型要求は許可 **されません** 。 2 つ以上の対話型要求が同時に行われると、最初の要求のみが対話を開始し、残りは [エラー interaction_in_progress](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/errors.md#interaction_in_progress) 失敗します。 このエラーと考えられる解決方法を理解して、アプリケーションでエラーが発生しないようにすることをお勧めします。

#### リソースごとに 1 つのトークン要求を行う

一度に 1 つのリソースに対してのみアクセス トークンを要求できます ( [リソースとスコープを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/resources-and-scopes)参照)。 必要に応じて、要求オブジェクトの `extraScopesToConsent` パラメーターを使用して、複数のリソースに必要なスコープ (アクセス許可) にユーザーの同意を求めることができます。 以前に同意したスコープのアクセス トークンは、サイレントで取得できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/caching"} -->
## MSAL.js でのキャッシュ - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/caching
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: MSAL.js でのトークン キャッシュ メカニズム、キャッシュ ストレージ オプション、トークン ライフサイクル管理について説明します

MSAL は、トークンを取得すると、将来の使用のためにトークンをキャッシュします。 MSAL は、トークンの有効期間の管理と自動更新を行います。 `acquireTokenSilent()` API は、特定のアカウントのアクセス トークンをキャッシュから取得し、必要に応じて更新します。

### キャッシュ ストレージ

キャッシュ ストレージの場所は、MSAL のインスタンス化に使用される構成オブジェクトを使用して構成できます。

```typescript
import { PublicClientApplication, BrowserCacheLocation } from "@azure/msal-browser";

const pca = new PublicClientApplication({
    auth: {
        clientId: "Enter_the_Application_Id_Here", // e.g. "00001111-aaaa-2222-bbbb-3333cccc4444" (guid)
        authority: "https://login.microsoftonline.com/Enter_the_Tenant_Info_Here", // e.g. "common" or your tenantId (guid),
        redirectUri: "/"
    },
    cache: {
       cacheLocation: BrowserCacheLocation.SessionStorage // "sessionStorage"
    }
});
```

既定では、MSAL は、すべての最新のブラウザーでサポートされている [Web Storage API](https://developer.mozilla.org/docs/Web/API/Web_Storage_API) を使用して、IdP から取得したさまざまな認証成果物をブラウザー ストレージに格納します。 したがって、MSAL には、 `sessionStorage` (既定) と `localStorage`の 2 つの永続的ストレージ方法が用意されています。 さらに、MSAL には `memoryStorage` オプションが用意されており、ブラウザー ストレージへのキャッシュの保存をオプトアウトできます。

| キャッシュの場所 | クリアされた日時 | ウィンドウ/タブ間で共有 | サポートされているリダイレクト フロー |
| --- | --- | --- | --- |
| `sessionStorage` | ウィンドウ/タブを閉じる | No | はい |
| `localStorage` | ブラウザーを閉じる (ユーザーが選択した場合を除き、サインインしたままにする) | はい | はい |
| `memoryStorage` | ページの更新/ナビゲーション | No | No |

Note

ウィンドウ/タブの閉じるか、ページの更新/ナビゲーションが原因でセッションとメモリ ストレージの認証状態が失われる可能性があります。ただし、セッション Cookie の有効期限が切れていない限り、ユーザーは IdP とのアクティブなセッションを保持し、プロンプトなしで再認証できる可能性があります。

異なるストレージの場所を選択すると、ユーザー エクスペリエンスの向上とセキュリティの強化のトレードオフが反映されます。 上の表に示すように、ローカル ストレージでは可能な限り最適なユーザー エクスペリエンスが得られますが、メモリ ストレージはブラウザー ストレージに機密情報が格納されないので最高のセキュリティを提供します。 詳細については、 以下のセキュリティ と キャッシュされたアーティファクトに関する セクションを参照してください。

#### LocalStorage のメモ

v4 以降では、 `localStorage` キャッシュの場所を使用している場合、ユーザーがサインイン中に [サインインしたままにする] を選択しない限り、認証アーティファクトは暗号化されます。 使用される暗号化アルゴリズムは、[HKDF](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/encrypt#aes-gcm) を使用してキーを派生させる [AES-GCM](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/deriveKey#hkdf) です。 基本キーは、 `msal.cache.encryption`というタイトルのセッション Cookie に格納されます。

この Cookie は、ブラウザー インスタンス (タブではない) が閉じられたときに自動的に削除されるため、セッションが終了した後に認証アーティファクトを復号化できなくなります。 これらの有効期限が切れた認証アーティファクトは、次回 MSAL が初期化されるときに削除され、ユーザーは再認証が必要になる場合があります。 `localStorage`の場所では、引き続きすべてのユーザーにクロスタブ キャッシュ永続化が提供されますが、"サインインしたままにする" (KMSI) を選択したユーザーのブラウザー セッション間でのみ保持されます。

Important

この暗号化の目的は、追加のセキュリティを提供 **せず** 、認証アーティファクトの永続化を減らすことです。 悪意のあるアクターがブラウザー ストレージにアクセスできる場合は、キーにアクセスすることも、キャッシュをまったく必要とせずに、ユーザーに代わってトークンを要求することもできます。 アプリケーションが XSS 攻撃に対して脆弱でないことを確認するのは、お客様の責任です。 詳細については、 セキュリティ セクションを参照してください。

#### Cookie の保存領域

Note

MSAL.js v4 では、一時的な認証成果物の Cookie ストレージは非推奨です。 このセクションは、MSAL.js v3 以前を引き続き使用しているアプリケーションに対して保持されます。

MSAL Browser は、一時的な認証成果物を格納するために Cookie を使用するように構成できます。 このオプションを使用すると、リダイレクトベースのログイン フロー中にローカル/セッション ストレージをクリアする可能性があるブラウザー (Internet Explorer、プライベート モードの Firefox など) をサポートできます。 このオプションを選択すると、トークン自体は引き続きブラウザーまたはメモリ ストレージに格納されることに注意してください。 詳細については、 [構成](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration#cache-config-options) を参照してください。

#### セキュリティ

アプリケーションにクロスサイト スクリプティング (XSS) と関連する脆弱性がない限り、セッション/ローカル ストレージは安全であると考えられます。 XSS に対するアプリケーションのセキュリティ保護については、 [OWASP XSS 防止チート シート](https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html) を参照してください。 それでも問題が解決しない場合は、代わりに `memoryStorage` オプションを使用することをお勧めします。

### キャッシュ済みアーティファクト

優れた UX を維持しながら効率的なトークンの取得を簡単にするために、MSAL は API 呼び出しに起因するさまざまな成果物をキャッシュします。 MSAL キャッシュ内のエンティティの概要を次に示します。

- **永続的なアーティファクト**（要求の後も存続する - 関連項目: [トークンの有効期間](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/token-lifetimes)）
    - アクセス トークン
    - id トークン
    - 更新トークン
    - accounts
- **一時的なアーティファクト**（リクエストの存続期間に限定）
    - リクエスト メタデータ（例: state、nonce、authority）
    - エラー
    - 相互作用の状態
- **テレメトリ**
    - 以前の失敗した要求
    - パフォーマンス データ

Note

一時キャッシュ エントリは、常にセッション ストレージまたはメモリに格納されます。 セッション ストレージが使用できない場合、MSAL はメモリ ストレージにフォールバックします。

Note

承認コードはメモリにのみ格納され、トークンに引き換えた後に破棄されます。

### temporaryCacheLocation のオーバーライド

Note

`temporaryCacheLocation`構成オプションは、MSAL.js v4 では非推奨です。 このセクションは、MSAL.js v3 以前を引き続き使用しているアプリケーションに対して保持されます。

Warning

`temporaryCacheLocation`のオーバーライドは、特に`localStorage`を選択する場合は注意して行う必要があります。 複数のタブ/ウィンドウでの操作はサポートされておらず、予期せず `interaction_in_progress` エラーが発生する可能性があります。 これはエスケープ ハッチであり、完全にサポートされている機能ではありません。

新しいウィンドウまたはタブで認証が成功した後にユーザーがリダイレクトされるシナリオで既定の構成で MSAL.js を使用すると、PKCE フローを使用した OAuth 2.0 承認コードが中断されます。 この場合、認証状態 (コード検証ツールとチャレンジ) が格納されている元のウィンドウまたはタブは失われ、認証フローは失敗します。

このシナリオを処理するには、`localStorage`構成プロパティをオーバーライドすることで、`temporaryCacheLocation`をキャッシュの場所として使用するように MSAL を構成します。 これにより、コード検証ツールとチャレンジをブラウザーの `localStorage`に格納できます。これは、複数のタブとウィンドウに保持されます。

### MSAL.js のアップグレードとロールバック中のキャッシュの永続化

新しい要件、機能、バグ修正をサポートするために、MSAL.js キャッシュされた成果物の形状を変更する必要がある場合があります。 多くの場合、これらの変更は、アプリケーションが新しいバージョンにアップグレードされたときや古いバージョンにロールバックしたときに、ユーザーのブラウザーに存在するキャッシュを引き続き使用できるように、下位互換性のある方法で行われます。 ただし、これは常に可能であるとは限らないので、キャッシュの複数のコピーが同時に存在する状態になる可能性があります。1 つは現在のバージョンの MSAL.js 実行で使用され、もう 1 つはアップグレード前に使用されたバージョンによって書き込まれたものになります。 これは、必要に応じてアプリケーションが正常にロールバックできるようにするために行われます。 アップグレードの大部分では、MSAL.js は既存のキャッシュを新しい形式に移行し、シームレスなアップグレード エクスペリエンスを実現します。 v3 から v4 へのアップグレードなど、まれなケースでは、セキュリティまたはプライバシーの要件が原因でこれが不可能になる可能性があり、これにより常にメジャー バージョンのバンプが発生します。

重大なキャッシュ変更が行われると、古いキャッシュは、必要に応じてロールバックできるように、既定で 5 日間保持されます。 古いキャッシュが保持される時間の長さは、`cacheRetentionDays`の`PublicClientApplication` キャッシュ構成を使用して構成できます。 キャッシュがその時間内にアクティブに使用されていない場合は、次回 MSAL.js が初期化されるときにクリアされます。 さらに、ロールバックする必要がない場合は、この値を `0` に設定して、新しいバージョンの MSAL.jsにアップグレードすると、古いキャッシュを常にすぐに削除する必要があることを示します。 逆に、アップグレードのロールアウト期間が長い場合は、これを長い値に設定することもできます。

Note

アクセス トークンと更新トークンは、構成された `cacheRetentionDays` にまだ到達していない場合でも、有効期限が切れると削除されます。 ブラウザー ストレージがストレージ クォータに達した場合、有効なアクセス トークンはいつでも削除される可能性があります。 ストレージ クォータに達すると、アクセス トークンは最初に削除されます。最初に、以前のバージョンの MSAL.js によって書き込まれたエントリから始まり、現在のバージョンの MSAL.jsによって書き込まれたエントリに移動します。

```javascript
const config = {
    auth: {
        clientId: "<your-client-id>"
    },
    cache: {
        cacheLocation: "localStorage",
        cacheRetentionDays: 0 // Set this to the number of days you want old cache to be preserved in the event a rollback is needed (Default 5 days)
    }
}

const pca = new PublicClientApplication(config);
await pca.initialize();
```

### 注釈

- キャッシュ内のエンティティの直接使用に依存するビジネス ロジックを持つアプリはお勧めしません。 代わりに、トークンを取得したりアカウントを取得したりする必要がある場合は、適切な MSAL API を使用してください。
- 所有証明 (PoP) トークンの暗号化に使用されるキーは、 [IndexedDB API](https://developer.mozilla.org/docs/Web/API/IndexedDB_API) とメモリ ストレージの組み合わせを使用して格納されます。 詳細については、 [アクセス トークンの所有証明](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/access-token-proof-of-possession#pop-key-management)に関するページを参照してください。

### 詳細情報

- [Microsoft Authentication Libraryを使用してトークンを取得してキャッシュする](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-acquire-cache-tokens)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/cdn-usage"} -->
## MSAL ブラウザーの CDN 使用法 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/cdn-usage
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: @azure/msal-browser の CDN の使用状況について説明します

npm に加えて、`msal`はMicrosoftホストされる CDN から使用できます。

***メモ：*** MSAL.js v3 以降では、 `msal-browser` は CDN でホストされなくなります。 CDN から `msal-browser` を使用する場合は、npm から `msal-browser` をダウンロードし、アップグレードする前に次のいずれかの方法を選択する必要があります。

1. ESM ビルドを利用する（推奨）
2. ダウンロードしたパッケージから `lib/msal-browser.js` または `lib/msal-browser.min.js` を抽出し、アプリで静的資産として提供するか、独自の CDN でホストします

### ベスト プラクティス

- MSAL.js v2 の最新バージョンを使用します。
- 本番環境では圧縮版ビルドを使用し、開発時には非圧縮版ビルドを使用します。
- サード パーティの CDN ではなく、Microsoft CDN を使用します。
- ユーザーに最も近い CDN リージョンを使用します。
- ページのレンダリングをブロックしないようにするには、 `async` 属性または `defer` 属性を使用します。
- `integrity`属性を使用して、CDN ビルドの整合性を確保します。
- IE11 のサポートには Promise ポリフィルが必要です (IE11 のサポートに関する[詳細情報](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/app/ie11-sample) )。

### 基本的な使用方法

```html
<script type="text/javascript" src="https://alcdn.msauth.net/browser/2.35.0/js/msal-browser.min.js"></script>
```

### 非最小化ビルド

圧縮版ビルドに加えて、各バージョンの非圧縮版ビルドも利用できます（`msal-browser.min.js` の代わりに `msal-browser.js` として）

```html
<!-- Replace <version> with desired version, e.g. 2.3.0 -->
<script type="text/javascript" src="https://alcdn.msauth.net/browser/<version>/js/msal-browser.js"></script>
```

### 代替リージョン

Microsoftでは、米国西部とヨーロッパ北部の 2 つの異なるリージョンでホストされている CDN ビルドが提供されます。

| CDN ドメイン | リージョン | 例 |
| --- | --- | --- |
| `alcdn.msauth.net` | 米国西部 | `https://alcdn.msauth.net/browser/<version>/js/msal-browser.min.js` |
| `alcdn.msftauth.net` | 北ヨーロッパ | `https://alcdn.msftauth.net/browser/<version>/js/msal-browser.min.js` |

#### CDN フォールバック

万が一、CDN ビルドが破損しているか、CDN 自体にアクセスできない場合、アプリケーションは他の CDN リージョンをフォールバックとして使用できます。

```html
<script type="text/javascript" src="https://alcdn.msauth.net/browser/2.3.0/js/msal-browser.min.js"></script>
<script type="text/javascript">
    if(typeof msal === 'undefined')document.write(unescape("%3Cscript src='https://alcdn.msftauth.net/browser/2.3.0/js/msal-browser.min.js' type='text/javascript' %3E%3C/script%3E"));
</script>
```

**注:**`document.write`を使用するこの方法は、特定の状況で特定のブラウザーでブロックされる可能性があります。 詳細については、 [こちらをご覧ください](https://www.chromestatus.com/feature/5718547946799104)。

### サブリソースの整合性

[MDN から](https://developer.mozilla.org/docs/Web/Security/Subresource_Integrity):

>
> サブリソース整合性 (SRI) はセキュリティ機能であり、ブラウザーは、フェッチするリソース (CDN からなど) が予期しない操作なしで配信されることを確認できます。 これは、フェッチされたリソースが一致する必要がある暗号化ハッシュを提供できるようにすることで機能します。

>
> コンテンツ配信ネットワーク (CDN) を使用して、複数のサイト間で共有されるスクリプトやスタイルシートなどのファイルをホストすることで、サイトのパフォーマンスを向上させ、帯域幅を節約できます。 ただし、CDN を使用すると、攻撃者が CDN の制御を取得した場合、攻撃者は任意の悪意のあるコンテンツを CDN 上のファイルに挿入 (またはファイルを完全に置き換える) 可能性があるため、その CDN からファイルをフェッチするすべてのサイトを攻撃する可能性もあるというリスクもあります。

>
> サブリソースの整合性を使用すると、このような攻撃のリスクを軽減できます。たとえば、Web アプリケーションまたは Web ドキュメントのフェッチ (CDN または任意の場所から) のファイルが、サード パーティがそれらのファイルに追加コンテンツを挿入することなく、それらのファイルに対して他の種類の変更を加えることなく確実に配信されるようにすることができます。

アプリケーションと、ブラウザーに格納される認証アーティファクト (アクセス トークンなど) をセキュリティで保護するために、MSAL.js の CDN ビルドで SRI ハッシュ MSAL.js を使用することを強くお勧めします。

#### MSAL.js SRI ハッシュの例

```html
<script
    type="text/javascript"
    src="https://alcdn.msauth.net/browser/2.3.0/js/msal-browser.min.js"
    integrity="sha384-o+Sncs5XJ3NEAeriM/FV8YGZrh7mZk4GfNutRTbYjsDNJxb7caCLeqiDabistgwW"
    crossorigin="anonymous"></script>
```

#### SRI ハッシュ に関する注意事項

- 各ハッシュは、MSAL.js v2 のバージョンに固有であり、変更されません。
- SRI ハッシュの使用は、MSAL.js CDN ビルドでは省略可能です。
- `integrity`属性を v2 CDN ビルド MSAL.js 使用する場合は、`crossorigin`属性を `"anonymous"` に設定する必要があります。
- CDN ビルドが侵害されたと思われる場合は、すぐに [お知らせください](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/SECURITY.md#reporting-a-vulnerability) 。

#### SRI ハッシュ履歴

| バージョン | ビルド | SRI ハッシュ |
| --- | --- | --- |
| 2.35.0 | msal-browser.js | `sha384-+8A1rifuHuJxJgQn6UCxGgmo6Q2SvIeEAqtPOsXCpi0DpqZLtuDhxxnqjJZw/1ve` |
| 2.35.0 | msal-browser.min.js | `sha384-PARf28kmic36Ve+O3DnUerRXFtOQ7ZDqRDGpLcbljly5/N39T2OV3kt3QsWOKeAX` |
| 2.34.0 | msal-browser.js | `sha384-8TkGQjPkLg+rgblWRah3ZvrUIkKL7iDl8aYUAd/8xqSzwSpmOAtEPkE7eLQvs+Ru` |
| 2.34.0 | msal-browser.min.js | `sha384-oSX3mlcV2SGDE+ZhNgvgqV1xrCHF5lBDuFj1qGUIuCYg/OZzhuJmMSvXOuh1waW2` |
| 2.33.0 | msal-browser.js | `sha384-xfWQjvZ0VzT5Zvno0SgkPatOmGBEbU8nBL9PyHta/UR299kguq6f5AdiT7i4njhQ` |
| 2.33.0 | msal-browser.min.js | `sha384-sIyEdUUBfHyE7nRpvFHA2odaB/z+HNe9frVsvOKkncXSRPnyek6HeakhZhNdJLrj` |
| 2.32.2 | msal-browser.js | `sha384-FiPubKXaL8pr5mOhDJ06p9BG501EIJFxOxYWUfqLiUeSGIF2SWo8cWI3QnQEZ1FN` |
| 2.32.2 | msal-browser.min.js | `sha384-y1IcCD+XmMR+nTJWafnzQJt1pwQU6CT7CvoixQpAYX2LlSu0NG7KmE5KXnarCJLy` |
| 2.32.1 | msal-browser.js | `sha384-8M9Qh0U/bSYzHACsgrp8pVFDoPDH5C1Cm39aGMGXpQ2XzxnyMZbbQnnu8nEnj6vp` |
| 2.32.1 | msal-browser.min.js | `sha384-UrQz8fjd/68UVLcPZl2ZTrABZEfujnPqnuQt7CV6903eSw62F3KEsOwGudSw2A9Q` |
| 2.32.0 | msal-browser.js | `sha384-zT0cfnNK2YXRh/ickIdCtkvP0wUCA0jH2jgHuXuErJIDc2UXBt3iwS5eJaW0ZXVG` |
| 2.32.0 | msal-browser.min.js | `sha384-MkuKIg4TRd71anKVt5q1+USEhZ2N2tVOTgDwlOuysviT1CanXfuWB2P4B2X4jSXj` |
| 2.31.0 | msal-browser.js | `sha384-BO4qQ2RTxj2akCJc7t6IdU9aRg6do4LGIkVVa01Hm33jxM+v2G+4q+vZjmOCywYq` |
| 2.31.0 | msal-browser.min.js | `sha384-1vMI1nswvByekU8FrM4tFu+iue2XqjisDCQmalPH2vWX53szbpaLAQA/rI1p9mjz` |
| 2.30.0 | msal-browser.js | `sha384-o4ufwq3oKqc7IoCcR08YtZXmgOljhTggRwxP2CLbSqeXGtitAxwYaUln/05nJjit` |
| 2.30.0 | msal-browser.min.js | `sha384-HC34/sGr6mESU7p33Bo1s3lWvYOdfDnu05vmaJFpSvHZbTUdKWIOxIn5SuZnqafp` |
| 2.29.0 | msal-browser.js | `sha384-L8LyrNcolaRZ4U+N06atid1fo+kBo8hdlduw0yx+gXuACcdZjjquuGZTA5uMmUdS` |
| 2.29.0 | msal-browser.min.js | `sha384-CirgUajm2J/qIZ/u+TqkKMfxFdAhX0Q5UDQ0lJR0cMZam8SP6WASMDgG6ZuH9YU2` |
| 2.28.3 | msal-browser.js | `sha384-OnQxm8gBWqXtcrgb4TGKgQsKvNFDAVRb/LYrQ4SkYg6nYK+vg9oC/OF6+KRmqwGn` |
| 2.28.3 | msal-browser.min.js | `sha384-LTsAmgDln/CC82A+RiT/7SX65gRIMoqFU6jclq+TzHTa3ydfY6ex6J3LO1pyLC1/` |
| 2.28.2 | msal-browser.js | `sha384-bTszrDBNEw/vuvCJ58o9obswP5dg379zO8MJx53LyZCsKsSnrErje1LM+6Bk8Lkl` |
| 2.28.2 | msal-browser.min.js | `sha384-203jB5A+1LERtg89ajpErgNu5XzbM4Hye182KOJTVuHD19rezlVuwnwQ3WVbhZVF` |
| 2.28.1 | msal-browser.js | `sha384-M6geA+l92SitR/WGDtbiK0tt/MAv3qimyNK2vaOatn2c+OrHVbwYaG85IIlSq7eY` |
| 2.28.1 | msal-browser.min.js | `sha384-ei8xVSyFPTuRnbO1sdYy5qJT6Kd9neBfVG8AjZySEwdMG1GhCThbceSqxJnx0Ci3` |
| 2.28.0 | msal-browser.js | `sha384-q8S4bw8Wfzedv3LPXdOP0+IKu+LqXg4l9xZaOwTp3h40FYMw6YeO/6FX+aG6vgXx` |
| 2.28.0 | msal-browser.min.js | `sha384-dKtQ/y8SrxV+8eZsQnb3vQpwWP57fRau9cbe4FbFK6B+VSC5SaWTM9w6lwQdNhKG` |
| 2.27.0 | msal-browser.js | `sha384-CsXI9QUbEXvbc1SIiLQ1/sUNkZZfkQSamJ2YU4g/yDKQNDyn8D2HTgR1ww7QV5+U` |
| 2.27.0 | msal-browser.min.js | `sha384-IlUQkOwOI6mWk8GNIWu8hpPE1sasxSg3gGjZo0dncq6IhHsTlH51mp5mhFYS5po1` |
| 2.26.0 | msal-browser.js | `sha384-fitpJWrpyl840mvd9nBFLGulqR4BJzvim0fzrXQKdsVh2AQzE4rTTJ0o5o+x+dRK` |
| 2.26.0 | msal-browser.min.js | `sha384-VdtLJ4gW9+dszXDbJEzdUFYI+xq4hXfOGntgGlDve3qz/5WEzWjLeN1voiro74af` |
| 2.25.0 | msal-browser.js | `sha384-zezf4cRFK/02dUQFQjo+qA3OjwpHtgizVgd4wMyxG2bWNy2TxzKe1CqIyBYWRJxF` |
| 2.25.0 | msal-browser.min.js | `sha384-dDqLsp/gmQFrDNIjpyKi23AtweUuA2Hn3wnwxOr+IwWGC8Zock5gcEwvLAkvvXh/` |
| 2.24.0 | msal-browser.js | `sha384-NcVVwcZIMSdYk9wbu0m7mElxxmQMIMRVSXXkF53lT+d41bXftjpIs8neU7xry4ch` |
| 2.24.0 | msal-browser.min.js | `sha384-U+uxTX8q39oAXjbZ8Bp9eTNwAmwyS8Sq7afx4SiEUFTt4LRIEbZDcMmVE0K85Vze` |
| 2.23.0 | msal-browser.js | `sha384-dvhiAjRHm++5woziYGV/JQSredPVb/p0VCATrsE/Upv4VmhLrKo6MWW218QofKIG` |
| 2.23.0 | msal-browser.min.js | `sha384-QxXqndv+Kkjr1gNW8SQUIE2zG/UT8lHYvf6HYdAAUj56xs9utgJNWyknd0O1CrUl` |
| 2.22.1 | msal-browser.js | `sha384-cKDVz4ain64nzHeJR0vejySPl0i8A6c7YfJI5ehEDQDoA5SSlb/zoLAFvXTQvTQS` |
| 2.22.1 | msal-browser.min.js | `sha384-nCTmWvEOevLDR1A0WzHvi1PbktdL8pPPACO2UYs9NPp+TCEz0hE0c8JmMxRlNSjh` |
| 2.22.0 | msal-browser.js | `sha384-B46pTTVe+0LfqOtym4Ys6NnTk47DzGHgn83hf4JBDIUfgiZlYFaywZqzEYQYCg4b` |
| 2.22.0 | msal-browser.min.js | `sha384-JmIjzXWxZ0+8Zd5wAsqkE9EKmxRx1ikmsnABsK9yFAbMmjOv9kSK4j560FLjkCxn` |
| 2.21.0 | msal-browser.js | `sha384-928QQ3wSVfsx4ZV1MR0896l8lK21YX1xWK3gSl8AW/lMEAZ2GeYqvFkm/VPzgn4y` |
| 2.21.0 | msal-browser.min.js | `sha384-s/NxjjAgw1QgpDhOlVjTceLl4axrp5nqpUbCPOEQy1PqbFit9On6uw2XmEF1eq0s` |
| 2.20.0 | msal-browser.js | `sha384-Ky1dhcw3VPMuZV6VlVBcQBtWHs5Ry8rkG2LGdxdEOoaApfNShXhD3OQTgN3klUN5` |
| 2.20.0 | msal-browser.min.js | `sha384-WV/465aYIrPn7bcKutSAFz+3JedxtCaRXxYu5EEjgGKPaOz7gjN06C8tBoGEmlZm` |
| 2.19.0 | msal-browser.js | `sha384-qMZg1SlV+Gm/R5MbEArouisSUHYzmBo05auTya2W3UOOr2Mlr2UvzzP1+nw5zc01` |
| 2.19.0 | msal-browser.min.js | `sha384-VK+6hHt27itNFksZdEeXofJXdAhmlizHbC/a1TUUJm/Yq6gOuAjXKkiiCaGbFsnd` |
| 2.18.0 | msal-browser.js | `sha384-PERHHiF9DdKG6zSfxaBeyaXmEbHrKvJjvab6BjfKeufVnfveKzZLHGB6m213V4tT` |
| 2.18.0 | msal-browser.min.js | `sha384-h9/gGcqbtmiQLu/34PC5wXvlxf+ugeTXcotRfHcjAIwyIB7UzyxcfNBcz3ONQiTz` |
| 2.17.0 | msal-browser.js | `sha384-LgNKCUpBSQJW+dWsseosd8ZZ7GNGiO+9SbgDBYyfqFkGJGg29VAzMQDYjK76r7o5` |
| 2.17.0 | msal-browser.min.js | `sha384-nPvMTGGQIdPr+oK09URmAR99LAk9PEVLJE+RJfjIH/QADFbVgGlF9tFdVbgSYC+c` |
| 2.16.1 | msal-browser.js | `sha384-JNwcxoC2tyQMQnFA7pCGC8h4BSIlWY9ytKZv0tVvvFzlqsCj77gw9sa+0FlM5c0F` |
| 2.16.1 | msal-browser.min.js | `sha384-bPBovDNeUf0pJstTMwF5tqVhjDS5DZPtI1qFzQI9ooDIAnK8ZCYox9HowDsKvz4i` |
| 2.16.0 | msal-browser.js | `sha384-W29UmqhBlCkSR5sC6sGVNkUpnNBI2hYRwB0/3wiCixcjzpXRLyByZbNOrx+xPiR/` |
| 2.16.0 | msal-browser.min.js | `sha384-h/D+9sV4N/CFwWR6G+dv+dkByf17RfGMJZl5f9noj9QamUJdw6BW3xZPAVSWyG4A` |
| 2.15.0 | msal-browser.js | `sha384-dFzMiVGB5HpWZ+5w5VSif6jhWfNeplSw9ACYmQKZcY2azuT9kCxVWVI9HyfGdkHV` |
| 2.15.0 | msal-browser.min.js | `sha384-/weuqUPkC0P9JxnstihEV1GHdWrheU9Qo3MbdTuxxKJM8l/cSTE5zGP5VBIM4TZN` |
| 2.14.2 | msal-browser.js | `sha384-SeRoSpLefUsASd6PTJsFeKDwITzOJ6gxSmsl+Z9Fl/hY2PAn/rKcw3WzoaBGc4my` |
| 2.14.2 | msal-browser.min.js | `sha384-ggh+EF1aSqm+Y4yvv2n17KpurNcZTeYtUZUvhPziElsstmIEubyEB6AIVpKLuZgr` |
| 2.14.1 | msal-browser.js | `sha384-RRV2T1wXStSmpELGRUont/dBMwIOD45UU7pk2qP7msfNC5dBalYUq+7cO02NPSj1` |
| 2.14.1 | msal-browser.min.js | `sha384-U3GjPGP2DZIb7AJBXk4B2quSe+7i4Dos1SqrcBwXPUkgnEZtnUBobrvAGXxpH6Cj` |
| 2.14.0 | msal-browser.js | `sha384-BiEwq81GpEOSES/Zj2TjBInBOQjHki/s0Si4VLT6JK2XoFX0cJK6HjII3W3eJ7DS` |
| 2.14.0 | msal-browser.min.js | `sha384-WBY8oVVrEdSaZOKwHzdAhKjmFK8vz2bpQt90XIIBsBFJI8JtGteFQn6ngXmA3n9h` |
| 2.13.1 | msal-browser.js | `sha384-7hwr87O1w6buPsX92CwuRaz/wQzachgOEq+iLHv0ESavynv6rbYwKImSl7wUW3wV` |
| 2.13.1 | msal-browser.min.js | `sha384-2Vr9MyareT7qv+wLp1zBt78ZWB4aljfCTMUrml3/cxm0W81ahmDOC6uyNmmn0Vrc` |
| 2.13.0 | msal-browser.js | `sha384-8WE3wV17rmapJTrZubql1Rziv4aZmDrlb7F3iZbiQEuOT7nmK8UH39MN2cEIOWQy` |
| 2.13.0 | msal-browser.min.js | `sha384-vDONqKCGNcmVCJ/YQ6YHzbrhwKdihAE08TkDEHBZjgjqRYz5itQb7Rdst5Oy5GB+` |
| 2.12.1 | msal-browser.js | `sha384-QMpSjAFJzgh/J1zBL23Xx95KfVI1n9Je9GmB3byJbZ0/hj8o9CJTmxOK/BPZivil` |
| 2.12.1 | msal-browser.min.js | `sha384-cZM20w+KzsO/N+6IGI+U1e2zsBS1ciCc6VdEC5SZ1/pHGyvcpE6D2uk0XcFwgb1q` |
| 2.12.0 | msal-browser.js | `sha384-WqJE0XrCKXbaCrjTk1+pq6qArRvxmGqf6YUBgoqwCz4WDXJFCW2hZZN0HMLE7/XB` |
| 2.12.0 | msal-browser.min.js | `sha384-eD9W7ukFmFKtgjDgCWa6WpkuqKjQ5Q/EP686z9/t2DK93dQtdIx8LAhMc9Mjy3hA` |
| 2.11.2 | msal-browser.js | `sha384-Zr4eBs1XVPlVMD5df2RNBeFhTy62Z8nN//v8dOR8pAgf6iI9rBTuvRZJSGjGjOCQ` |
| 2.11.2 | msal-browser.min.js | `sha384-MkT8/EXqCzh7OVmmpVdg5H2Fhpbt9uNrQM7UMbTg+v8fJVcfQ0BWf16siodTYgF6` |
| 2.11.1 | msal-browser.js | `sha384-LIpmPPrsEE7hCBPf0k4nn8zf9h8Z1i69YnG8TmRLTnaMDI6B2nGzLIh/c51BHgpN` |
| 2.11.1 | msal-browser.min.js | `sha384-wgFLXq8mfWaFslz/C51m2NvUX7aBENCLEqRi9BNL+23HBfWkeFCGfvqaPGmAbrzC` |
| 2.11.0 | msal-browser.js | `sha384-4PutheeyrGgwghN7WV8QaHBshN673W9fDW3PDP0Zw1Wm9CVZIuU2RFSWfcxEWAR0` |
| 2.11.0 | msal-browser.min.js | `sha384-mxc9xXB8zELCYWdhT4JCez24AMsgk+uN7e991ek2TrQy9rBPVlUiuppobVCuja8S` |
| 2.10.0 | msal-browser.js | `sha384-h4/puysjUElY8ygLCfA1sWMW/y1DsRmX02pAa6Om71bq6qS/w9PzLjby6YTkgH6W` |
| 2.10.0 | msal-browser.min.js | `sha384-UiyYbBRwVt3gTqaQfkEn8ceYV1cB9KAofImJ8nOc/vdqHATCuzgGZhxWgkhPBjNe` |
| 2.9.0 | msal-browser.js | `sha384-+akJUidBAUlm36Zv/ib9eOD+CZDJ67/yVEPaDV9aNw7164awXZbuEjLnsxDXuQO1` |
| 2.9.0 | msal-browser.min.js | `sha384-MUSnn9XLYFUDadNpWJyizBT8WbNR2FGs9zcvZG1GbEwFSK59dFTUESMnAtN6Edgg` |
| 2.8.0 | msal-browser.js | `sha384-Mjsbb/z4VVY/1KEUcSY4zG2SObmLbGdEQp1a6qJ8x4Qkd8HqhBmkigPO1LO+ZKC8` |
| 2.8.0 | msal-browser.min.js | `sha384-b/qP1MqDbgDE3TTcBfXi4/r9pcSjPbT1lTVl5Q71LhQMpn95C4bDE8+83ImeSE+l` |
| 2.7.0 | msal-browser.js | `sha384-5Fqyq1ncNYhL2mXCdWAFXkf2wWtKeA0mXYp++ryAX1lowD0ctAHFdity37L/ULXh` |
| 2.7.0 | msal-browser.min.js | `sha384-isB7RsMD9bXfK4BK9pJHfTyTfQMM/KQ/1a58J/PVsDFbto29TgNxOP3ZyrhRyiTV` |
| 2.6.1 | msal-browser.js | `sha384-kHVR+hnKKUXpL5UEI3dgmdIKZgopBagC1RdQytFqglEGROvOSAGJRkaFWfu8VsSx` |
| 2.6.1 | msal-browser.min.js | `sha384-ry0iBug2qnSSs0YiS8IfxgvYZvgsCCXiplbiwrf9tQWkpCFMcezBMuLDbVtYrKIl` |
| 2.6.0 | msal-browser.js | `sha384-MOtTwBzAcbzhOnPuklGgFJINWfT6ekHPIhI0GkJdUihkt/AtO/ttlrT93yen631k` |
| 2.6.0 | msal-browser.min.js | `sha384-sGG/3pGinkV/9X/+VrbuRSSJmOaYKq9Bdyet6ICHajSN8wSG9DpJHda6vls5BkUd` |
| 2.5.2 | msal-browser.js | `sha384-ZOWQBoErNmfc9sfHh6PXYc9NZ+02cf5d+wdsnvfKHSEyQ2x+YSWaf12KInVhfurI` |
| 2.5.2 | msal-browser.min.js | `sha384-A9ludGsBPhx3Ec8zLyd3vZEqJrRbvD6fJWpasbzAFyaaa/AMR6rtCUtbUmP07rsj` |
| 2.5.1 | msal-browser.js | `sha384-MFZe/UOLz61FRpO06noy2uBkJEZUaxccyYYwrSrwBOEY59Fi0GxyRzPiiZKLvvkC` |
| 2.5.1 | msal-browser.min.js | `sha384-/cOXpDxWc4bzFZUDf49Sp31Im+bSjki6UxTPadEDitHw0277qGX5teCOdieziPZh` |
| 2.5.0 | msal-browser.js | `sha384-JtZbGQvK0HbNDG42cgeg3XxEllLbMW8aAiSCXoLdW7iJhkdC7v4Kzqvl4LWOSiFF` |
| 2.5.0 | msal-browser.min.js | `sha384-+sjqS/ee1BeZqojCMFh8gGTbZ0ATgrA/rEIANI0l0Y6QdA+MDwmXLhj3JGvHueL7` |
| 2.4.1 | msal-browser.js | `sha384-4Equw/X3Wp2XPnMSCbe2OQQRE/8MzlwepR53zKGbAz/6eO//yRXOcn3LKf1MnBWS` |
| 2.4.1 | msal-browser.min.js | `sha384-vazVaX5+cCJf+t0Dzdb8CxX9jLLvWuSZqEI2lBSMeLUBPQovS4IlwFQI6epI2tJD` |
| 2.4.0 | msal-browser.js | `sha384-Bz0kggjHC0kxcxxtRzWgjaF0JGsmHuO1atz26xKETeu5WgdarvGmr9Pr/f/pKtrq` |
| 2.4.0 | msal-browser.min.js | `sha384-tBRIK0qPn8yxGmyhpgVsVIFaJNa0EDL62hp+zvDu1vtT1bIqWU6HiYexMhtk52bP` |
| 2.3.1 | msal-browser.js | `sha384-khe4Bq8VcpAsK8zAycaYefEMHsLny9P/kgPF9Jy1afhFNZ4EODmrdq//+LFp1mWV` |
| 2.3.1 | msal-browser.min.js | `sha384-d6fJLwOshjtqjJPGMQ4XgIKOvx46EBeyiPxTBaNJlj0GWqXKCh09qA6SgpAPnqD8` |
| 2.3.0 | msal-browser.js | `sha384-ILJg8BOvXQwFGYEbkLVLYTYoNpTT7tP905UubLu2AqwksVdddAu5z9k3e6gMhqc5` |
| 2.3.0 | msal-browser.min.js | `sha384-o+Sncs5XJ3NEAeriM/FV8YGZrh7mZk4GfNutRTbYjsDNJxb7caCLeqiDabistgwW` |
| 2.2.1 | msal-browser.js | `sha384-M6zl9i1upj9LPj3zSUn/IejJwPyUCewu0+RD444XuWQiRomvb2ZUwanqc0c2XfCy` |
| 2.2.1 | msal-browser.min.js | `sha384-8LDT8A5GReznR7uR2KGWc1Ep/kTc0ErU3yVBKJMOmAMoSf+hMonk3y3BceQ1rvF6` |
| 2.2.0 | msal-browser.js | `sha384-DDogsvdm1j1csBm8TKIenLTaFJA+x0KjwdW0CAx6ZW8+5EOqSIasS3OKZ0aiq3RV` |
| 2.2.0 | msal-browser.min.js | `sha384-ywaKEa0KdH8yiwoKS+2hRMenFDilyhT/K0r3WTXBzUQj+RNlYGnLeecytOEdgHpR` |
| 2.1.0 | msal-browser.js | `sha384-M9bRB06LdiYadS+F9rPQnntFCYR3UJvtb2Vr4Tmhw9WBwWUfxH8VDRAFKNn3VTc/` |
| 2.1.0 | msal-browser.min.js | `sha384-EmYPwkfj+VVmL1brMS1h6jUztl4QMS8Qq8xlZNgIT/luzg7MAzDVrRa2JxbNmk/e` |
| 2.0.2 | msal-browser.js | `sha384-rQvomuvjVybeTxLQIpbtb6lqFsDuJparCjjUJZjRZjVDNzGRloXbPj9qbgf9YM/d` |
| 2.0.2 | msal-browser.min.js | `sha384-zHGbJmHXAWMXaREIK7qFkrJCcU2ktJd8G9DAp49Q+y/+H6ArVhvFUW5IbyTzbNnn` |
| 2.0.1 | msal-browser.js | `sha384-knPh00kvaT+k3+4TCD5S2ORDNVc2I3RVbqI/ksbTlpdSBh8ZnyAPxW2kkTSG0+mT` |
| 2.0.1 | msal-browser.min.js | `sha384-fbyYRj8H9iJU/JyncEbzW6WgVOaR5C+PU1dHsRBg2Ag2Q14F4IB8+T8BdknwjRQ8` |
| 2.0.0 | msal-browser.js | `sha384-BqIcDtzVkr3wRGsSrk+iJJNm9GSdUsP0I2MplbnhPPc+I1l1d+dkKbcnqgNddGWX` |
| 2.0.0 | msal-browser.min.js | `sha384-n3aacu1eFuIAfS3ZY4WGIZiQG/skqpT+cbeqIwLddpmMWcxWZwYdt+F0PgKyw+m9` |
| 2.0.0-beta.4 | msal-browser.js | `sha384-7sxY2tN3GMVE5jXH2RL9AdbO6s46vUh9lUid4yNCHJMUzDoj+0N4ve6rLOmR88yN` |
| 2.0.0-beta.4 | msal-browser.min.js | `sha384-j9+OYwF1QFM1A8/DNvWKqvTw+bc5alOXQ7IA2WvGAcLLLpN/tK9XRTbJtlTiSFJI` |
| 2.0.0-beta.3 | msal-browser.js | `sha384-iKgpFzdbMAsg695JG+EmHleQe5gRjoAAixuMf0jfM7pCOVuGqhyBuXO1Ai71fixx` |
| 2.0.0-beta.3 | msal-browser.min.js | `sha384-X2nv+6ViZGj+UCfGAbimHAXpBEAi0RA6GWuqCckbMLU5jVr8uDjf6pGUvTkq7wME` |
| 2.0.0-beta.2 | msal-browser.js | `sha384-CEQpk7EG1PVKCHHdoQzDdR5uU7nJ1PLlcdx1s7vi8Ta/Pndhr04imhqCUkZGimOj` |
| 2.0.0-beta.2 | msal-browser.min.js | `sha384-O3n9nwTefR6cSLikBQsCDYke2pWL5YWluwvp0RgGe+VK2eU0+RJC1cmMow5jD1OE` |
| 2.0.0-beta.0 | msal-browser.js | `sha384-r7Qxfs6PYHyfoBR6zG62DGzptfLBxnREThAlcJyEfzJ4dq5rqExc1Xj3TPFE/9TH` |
| 2.0.0-beta.0 | msal-browser.min.js | `sha384-OV4a42kPPZv7IxRWcyqoLn9Ohs0g1WXejuNceZxAE9usAfLVFBcdre9yqo4I03VN` |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/configuration"} -->
## 認証構成オプション - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: 認証フローの動作をカスタマイズするために使用できる構成オプションについて説明します

ここで開始する前に、 [アプリ オブジェクトを初期化](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization)する方法を理解していることを確認してください。

MSAL ライブラリには、認証フローの動作をカスタマイズするために使用できる一連の構成オプションがあります。 これらのオプションは、 `PublicClientApplication` オブジェクトのコンストラクターで、または [要求 API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/request-response-object) の一部として設定できます。 ここでは、 `PublicClientApplication` コンストラクターに渡すことができる構成オブジェクトについて説明します。

### config オブジェクトの使用

構成オブジェクトは次の構造を持ち、 `PublicClientApplication` コンストラクターに渡すことができます。 必要な構成パラメーターは、アプリケーションのクライアント ID のみです。 それ以外はすべて省略可能ですが、テナントとアプリケーション モデルによっては必要になる場合があります。

```javascript
const msalConfig = {
    auth: {
        clientId: "enter_client_id_here",
        authority: "https://login.microsoftonline.com/common",
        knownAuthorities: [],
        cloudDiscoveryMetadata: "",
        redirectUri: "enter_redirect_uri_here",
        postLogoutRedirectUri: "enter_postlogout_uri_here",
        navigateToLoginRequestUrl: true,
        clientCapabilities: ["CP1"],
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
        protocolMode: "AAD"
    },
    telemetry: {
        application: {
            appName: "My Application",
            appVersion: "1.0.0",
        },
    },
};

const msalInstance = new PublicClientApplication(msalConfig);
```

### 構成オプション

#### 認証構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `clientId` | アプリケーションのアプリ ID。 Azure portal アプリの登録ウィンドウで確認できます | UUID/GUID | ありません。 MSAL でアクションを実行するには、このパラメーターが必要です。 |
| `authority` | 認証と承認に使用するテナントの URI。 通常、次の形式をとります。 `https://{uri}/{tenantid}` | テナントを含む URI 形式の文字列 - `https://{uri}/{tenantid}` | `https://login.microsoftonline.com/common` |
| `knownAuthorities` | 有効であることがわかっている URI の配列。 B2C シナリオで使用されます。 | URI 形式の文字列の配列 | 空の配列 `[]` |
| `cloudDiscoveryMetadata` | クラウド検出応答を含む文字列。 Microsoft Entraシナリオで使用されます。 | 文字列 | 空の文字列 `""` |
| `authorityMetadata` | .well-known/openid-configuration エンドポイント応答を含む文字列。 | 文字列 | 空の文字列 `""` |
| `redirectUri` | 承認コードの応答の送信先となる URI。 ここで指定する場所には、応答を処理するために MSAL ライブラリが必要です。 | 絶対 URI 形式または相対 URI 形式の文字列 | ログイン要求ページ (認証要求を行ったページの`window.location.href` ) |
| `postLogoutRedirectUri` | logout() 呼び出しが行われた後にリダイレクトされる URI。 | 絶対 URI 形式または相対 URI 形式の文字列。 ログアウト後のリダイレクトを無効にするには、 `null` を渡します。 | ログイン要求ページ (認証要求を行ったページの`window.location.href` ) |
| `navigateToLoginRequestUrl` | `true`場合は、承認コードの応答を処理する前に、元の要求の場所に戻ります。 `redirectUri`が元の要求の場所と同じ場合は、このフラグを false に設定する必要があります。 | boolean | `true` |
| `clientCapabilities` | `xms_cc`要求の一部としてすべてのネットワーク要求に追加する機能の配列 | 文字列の配列 | [] |
| `azureCloudOptions` | 開発者が特定のクラウド機関に既定で設定するための定義済みの Azure クラウド オプションのセット。 | [AzureCloudOptions](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#azurecloudoptions) | `AzureCloudInstance.None` |
| `skipAuthorityMetadataCache` | 権限の初期化中にローカル メタデータ キャッシュを使用するかどうかを選択するフラグ。 メタデータ キャッシュは、権限メタデータが指定されていない場合、およびメタデータのネットワーク呼び出しが行われる前に使用されます。 | boolean | `false` |
| `onRedirectNavigate` | URL MSAL が渡されたコールバックは、リダイレクト フロー内に移動します。 コールバックで `false` を返すると、ナビゲーションが停止します。 | 関数- `(url: string) => boolean \| void` | `undefined` |
| `instanceAware` | STS がトークンを取得する場所を指定するために追加のパラメーターを返す必要があるかどうかを示すフラグ。 | boolean | `false` |
| `isMcp` | `true`場合は、すべてのトークン要求で`resource` パラメーターが必要です。 モデル コンテキスト プロトコル (MCP) フローに使用されます。 | boolean | `false` |

#### キャッシュ構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `cacheLocation` | ブラウザーでのトークン キャッシュの場所。 | 次のいずれかである必要がある文字列値: `"sessionStorage"`、 `"localStorage"`、 `"memoryStorage"` | `sessionStorage` |
| `temporaryCacheLocation` | (**非推奨**)ブラウザーでの一時キャッシュの場所。 このオプションは、特定のエッジ ケースに対してのみ変更する必要があります。 詳細については、 [キャッシュを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/caching#cached-artifacts)参照してください。 | 次のいずれかである必要がある文字列値: `"sessionStorage"`、 `"localStorage"`、 `"memoryStorage"` | `sessionStorage` |
| `storeAuthStateInCookie` | (**非推奨**)true の場合は、ブラウザーのキャッシュと同様に、Cookie にキャッシュ項目を格納します。 以前は、Internet Explorer互換性のために使用されていました。 | boolean | `false` |
| `secureCookies` | (**非推奨**)true で `storeAuthStateInCookie` も有効になっている場合、MSAL はブラウザー Cookie に `Secure` フラグを追加して、HTTPS 経由でのみ送信できるようにします。 | boolean | `false` |
| `cacheMigrationEnabled` | true の場合、古いバージョンの MSAL のキャッシュ エントリは、起動時に最新のキャッシュ スキーマに準拠するように更新されます。 アプリケーションが新しいバージョンの MSAL.jsに最近更新されていない場合は、これを安全にオフにすることができます。 古いキャッシュ エントリが移行されない場合、アカウントまたはトークンを取得しようとしたときにキャッシュ ミスが発生する可能性があり、影響を受けるユーザーは最新の状態に戻るために再認証が必要になる場合があります。 | boolean | `true``localStorage`を使用する場合は`false`それ以外の場合 |
| `claimsBasedCachingEnabled` | `true`場合、要求された要求文字列のハッシュを含むキーの下にアクセス トークンがキャッシュされるため、異なる要求または不足している要求で同じトークン要求が行われると、キャッシュ ミスと新しいネットワーク トークン要求が発生します。 `false`に設定すると、トークンは要求なしでキャッシュされますが、要求を含むすべての要求はネットワークに移動し、以前にキャッシュされたトークンを同じスコープで上書きします。 | boolean | `false` |

Note

`temporaryCacheLocation` オプションは、MSAL Browser の最新バージョンでは非推奨となり、今後のメジャー リリースで削除される可能性があります。 新しい実装では、このオプションに依存しないでください。

Note

MSAL Browser の最新バージョンでは、 `storeAuthStateInCookie` オプションと `secureCookies` オプションは非推奨になりました。 これらのオプションは、主にInternet Explorer互換性のために使用されていましたが、これはサポートされなくなりました。 今後のメジャー リリースで削除される可能性があります。

詳細については、 [MSAL でのキャッシュ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/caching) を参照してください。

#### システム構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `loggerOptions` | ロガーの構成オブジェクト。 | 以下を参照 してください。 | 以下を参照 してください。 |
| `windowHashTimeout` | ポップアップ操作が解決されるまで待機するタイムアウト (ミリ秒)。 | integer (ミリ秒) | `60000` |
| `iframeHashTimeout` | iframe 操作の解決を待機するタイムアウト (ミリ秒単位)。 | integer (ミリ秒) | `6000` |
| `loadFrameTimeout` | iframe/popup 操作の解決を待機するタイムアウト (ミリ秒単位)。 指定した場合は、 `windowHashTimeout` と `iframeHashTimeout`の既定値を設定します。 | integer (ミリ秒) | `undefined` |
| `navigateFrameWait ` | iframe がウィンドウに読み込まれるのを待機するまでのミリ秒単位の遅延。 | integer (ミリ秒) | IE または Edge: `500`、その他すべてのブラウザーで次の手順を実行します。 `0` |
| `asyncPopups` | (**非推奨** — 代わりに `navigatePopups` を使用してください。)ポップアップを非同期的に開くかどうかを設定します。 `false`に設定すると、何も発生する前に空白のポップアップが開きます。 `true`に設定すると、ネットワーク要求を行うときにポップアップが開きます。 | boolean | `false` |
| `navigatePopups` | ポップアップを開いて後で移動するかどうかを設定します。 `true`に設定すると、空白のポップアップが開き、ログイン ドメインに移動します。 `false`に設定すると、ポップアップがログイン ドメインに直接開かれます。 これは、デスクトップ アプリやプログレッシブ Web アプリなど、`about:blank`がサポートされていないシナリオの`false`に設定できます。 | boolean | `true` |
| `allowRedirectInIframe` | 既定では、MSAL では、アプリケーションが iframe 内にあるときにリダイレクト操作を開始することはできません。 このチェックを削除するには、このフラグを `true` に設定します。 | boolean | `false` |
| `cryptoOptions` | ブラウザーでの暗号化操作の構成オブジェクト。 | 暗号化構成オプションを参照してください | 暗号化構成オプションを参照してください |
| `pollIntervalMilliseconds` | 認証中のポップアップ URL ハッシュのポーリングの間隔 (ミリ秒単位)。 | integer (ミリ秒) | `30` |
| `protocolMode` | 使用するプロトコル モードを表す列挙型。 `"AAD"`場合、MSAL は OIDC 準拠の AAD v2 エンドポイントで機能します。`"OIDC"`場合は、他の OIDC 準拠エンドポイントで機能します。 | 文字列 | `"AAD"` |

##### Logger 構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `loggerCallback` | MSAL ステートメントのログ記録を処理するコールバック関数。 | 関数- `loggerCallback: (level: LogLevel, message: string, containsPii: boolean): void` | 上記を参照してください。 |
| `piiLoggingEnabled` | true の場合、個人を特定できる情報 (PII) がログに含まれます。 | boolean | `false` |

##### 暗号化構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `useMsrCrypto` | ブラウザーで使用可能な場合に [MSR Crypto](https://github.com/microsoft/MSR-JavaScript-Crypto) を使用するかどうか (およびその他の暗号化インターフェイスは使用できません)。 | boolean | `false` |
| `entropy` | MSR Crypto のシード処理に使用される暗号的に強力なランダム値 (ノードから `crypto.randomBytes(48)` など)。 エントロピの 48 ビットをお勧めします。 `useMsrCrypto`が有効な場合は必須。 | `Uint8Array` | `undefined` |

#### テレメトリ構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `application` | MSAL.js を使用するアプリケーションのテレメトリ オプション | 以下を参照 してください | 以下を参照 してください |
| `client` | テレメトリ パフォーマンス クライアント インスタンス | [IPerformanceClient](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-common/src/telemetry/performance/IPerformanceClient.ts) | [StubPerformanceClient](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-common/src/telemetry/performance/StubPerformanceClient.ts) |

##### アプリケーション テレメトリ

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `appName` | アプリケーションの一意の文字列名 | 文字列 | 空の文字列 "" |
| `appVersion` | MSAL を使用したアプリケーションのバージョン | 文字列 | 空の文字列 "" |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/device-bound-tokens"} -->
## Windowsで WAM を使用してデバイス バインド トークンを取得する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/device-bound-tokens
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: Windowsで Web アカウント マネージャー (WAM) を使用してデバイス バインド トークンを取得する方法について説明します

MSAL.js では、Windows上の Web アカウント マネージャー (WAM) からトークンを取得できます。 これらのトークンは、取得されたデバイスにバインドされ、ブラウザーの localStorage または sessionStorage にキャッシュされません。

### サポートされている環境

この機能は現在、次の環境でのみサポートされています。

- この機能をサポートするWindows ビルドを実行しているマシン (詳細については、この記事を参照してください)
- Chrome または Edge ブラウザー、または Teams
- Chrome または Edge を使用している場合は[、Windows アカウント拡張機能](https://chrome.google.com/webstore/detail/windows-accounts/ppnbnpeolgkicgegkbkbjmhlideopiji) (バージョン 1.0.5 以降) がインストールされます
- アプリは `https` 上でホストされている必要があります

さらに、この機能は現在、職場および学校アカウントでのみサポートされています

### MSAL.js で機能を有効にする

MSAL.js でこの機能を有効にするには、次のように構成オブジェクトで `allowPlatformBroker` フラグを true に設定します。

```javascript
const msalConfig = {
    auth: {
        clientId: "insert-clientId"
    },
    system: {
        allowPlatformBroker: true
    }
};
```

さらに、他の MSAL.js API を呼び出す前に、新しい `initialize` API を呼び出して待機する必要があります。

```javascript
const pca = new PublicClientApplication(msalConfig);

// Initialize will establish a connection with the browser extension, if present
await pca.initialize();

// Call handleRedirectPromise, after initialization is complete
await pca.handleRedirectPromise();

// After initialize and handleRedirectPromise have completed, you may call any of the other APIs as you would without this feature
pca.acquireTokenSilent();
```

この新機能をサポートするために、他の変更は必要ありません。 サポートされている環境からアプリにアクセスするすべてのユーザーは、デバイスバインドトークンを取得できるようになります。 サポートされていない環境のユーザーは、従来の Web ベースのフローを通じて引き続きトークンを取得します。

作業サンプル[については、こちらを参照してください](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/app/wamBroker)。

### WAM を使用してトークンを取得するときの違い

WAM を使用してトークンを取得する場合、動作が少し異なる場合があります。

- すべてのキャッシュ関連の構成は、MSAL のローカル キャッシュにのみ適用されます。 ネイティブ ブローカーは、ブラウザー ストレージの代わりに使用される独自の、より安全なキャッシュを制御し、そのキャッシュ動作の構成をサポートしていません。 つまり、 `forceRefresh`、 `cacheLookupPolicy` 、 `storeInCache`などの要求パラメーターの値に関係なく、キャッシュされたトークンを受け取る可能性があります。 さらに、ネイティブ ブローカーから受信したトークンは、PublicClientApplication で構成した内容に関係なく、ローカルまたはセッション ストレージに格納 *されません* 。
- WAM がユーザーに対話を求める必要がある場合は、システム プロンプトが開きます。 このプロンプトは、使い慣れたブラウザー ポップアップ ウィンドウとは少し異なります。
- WAM プロンプトでアカウントを切り替えることはサポートされておらず、この場合、MSAL.js はエラー (エラー コード: user\_switch) をスローします。 このエラーをキャッチし、シナリオに適した方法で処理するのはアプリの責任です (たとえば、エラー ページの表示、新しいアカウントでの再試行、元のアカウントでの再試行など)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/errors"} -->
## MSAL JS の一般的なエラー - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/errors
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: MSAL JS の一般的なエラーについて説明します

### BrowserConfigurationAuthErrors

#### スタブ化されたパブリック クライアント アプリケーションが呼び出されました

**エラー メッセージ**: パブリック クライアント アプリケーションのスタブ インスタンスが呼び出されました。 msal-react を使用する場合は、プロバイダーなしでコンテキストが使用されていないことを確認してください。

[msal-react エラーを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-react/docs/errors.md)確認する

### BrowserAuthErrors

#### インタラクション進行中

**エラー メッセージ**: 相互作用は現在進行中です。 対話型 API を呼び出す前に、この操作が完了していることを確認してください。

このエラーは、ある対話型 API (`loginPopup`、`loginRedirect`、`acquireTokenPopup`、`acquireTokenRedirect`) が呼び出されたときに、別の対話型 API がまだ進行中だった場合に発生します。 login API と acquireToken API は非同期であるため、別の約束を呼び出す前に、結果として得られる約束が解決されていることを確認する必要があります。

##### `loginPopup`または`acquireTokenPopup`

これらの API から返された Promise が、別の API を呼び出す前に解決されていることを確認します。

 ❌次の例では、`acquireTokenPopup` が呼び出された時点では `loginPopup` がまだ進行中であるため、このエラーが発生します。

```javascript
const request = { scopes: ["openid", "profile"] };
loginPopup();
acquireTokenPopup(request);
```

✔️ これを解決するには、別の API を呼び出す前にすべての対話型 API が解決されていることを確認する必要があります。

```javascript
const request = { scopes: ["openid", "profile"] };
await msalInstance.loginPopup();
await msalInstance.acquireTokenPopup(request);
```

##### `loginRedirect`または`acquireTokenRedirect`

リダイレクト API を使用する場合は、リダイレクトから戻るときに `handleRedirectPromise` を呼び出す必要があります。 これにより、サーバーからのトークン応答が適切に処理され、一時キャッシュ エントリがクリーンアップされます。 このエラーは、アプリケーションが`loginRedirect`または`acquireTokenRedirect`を呼び出す前に、`handleRedirectPromise`が完了する機会がない場合にスローされます。

 ❌次の例では、`loginRedirect`が 2 回目に呼び出された時点で、`handleRedirectPromise`は以前の`loginRedirect`呼び出しからの応答をまだ処理しているため、このエラーがスローされます。

```javascript
msalInstance.handleRedirectPromise();

const accounts = msalInstance.getAllAccounts();
if (accounts.length === 0) {
    // No user signed in
    msalInstance.loginRedirect();
}
```

✔️ 解決するには、対話型 API を呼び出す前に、 `handleRedirectPromise` が解決されるのを待つ必要があります。

```javascript
await msalInstance.handleRedirectPromise();

const accounts = msalInstance.getAllAccounts();
if (accounts.length === 0) {
    // No user signed in
    msalInstance.loginRedirect();
}
```

または、次の方法を使用します。

```javascript
msalInstance
    .handleRedirectPromise()
    .then((tokenResponse) => {
        if (!tokenResponse) {
            const accounts = msalInstance.getAllAccounts();
            if (accounts.length === 0) {
                // No user signed in
                msalInstance.loginRedirect();
            }
        } else {
            // Do something with the tokenResponse
        }
    })
    .catch((err) => {
        // Handle error
        console.error(err);
    });
```

**メモ：**`loginRedirect`ではないページから`acquireTokenRedirect`または`redirectUri`を呼び出す場合は、`handleRedirectPromise` ページとリダイレクトを開始したページの両方で`redirectUri`が呼び出され、待機されていることを確認する必要があります。 これは、 `redirectUri` ページが最初に `loginRedirect` 呼び出したページへのリダイレクトを開始し、そのページがトークン応答を処理するためです。

##### ラッパーライブラリ

ラッパー ライブラリ (React または Angular) のいずれかを使用している場合は、このエラーが発生する可能性がある追加の理由から、これらの特定のライブラリのエラー ドキュメントを参照してください。

- [msal-react エラー](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-react/docs/errors.md#interaction_in_progress)
- [msal-angular エラー](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/docs/v2-docs/errors.md#interaction_in_progress)

ラッパー ライブラリを使用していないが、アプリケーションが同時対話型要求をトリガーする可能性があることを懸念している場合は、トークン取得メソッドで対話を呼び出す前に、他の対話が進行中かどうかを確認する必要があります。 これを実現するには、 [MSAL Events API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/events) を介して現在の MSAL 相互作用状態を出力するグローバル アプリケーション状態またはブロードキャスト サービスなどを実装します。

 ❌次の例では、`acquireTokenPopup` ブロック内のが、現時点で別の操作が行われているかどうかを確認しないため、このエラーがスローされます。

```javascript
async function myAcquireToken(request) {
    const msalInstance = getMsalInstance(); // get the msal application instance

    const tokenRequest = {
        account: msalInstance.getActiveAccount() || null;
        ...request
    };

    let tokenResponse;

    try {
        // attempt silent acquisition first
        tokenResponse = await msalInstance.acquireTokenSilent(tokenRequest);
    } catch (error) {
        if (error instanceof InteractionRequiredAuthError) {
            try {
                tokenResponse = await msalInstance.acquireTokenPopup(tokenRequest);
            } catch (err) {
                console.log(err);
                // handle other errors
            }
        }

        console.log(error);
        // handle other errors
    }

    return tokenResponse;
};

const request = {
    scopes: ["User.Read"]
};

myAcquireToken(request);
myAcquireToken(request);
```

✔️ 解決するには、他の対話型 API を呼び出す前に、対話状態が `None` されるのを待つ必要があります。

```javascript
async function myAcquireToken(request) {
    const msalInstance = getMsalInstance(); // get the msal application instance

    const tokenRequest = {
        account: msalInstance.getActiveAccount() || null;
        ...request
    };

    let tokenResponse;

    try {
        // attempt silent acquisition first
        tokenResponse = await msalInstance.acquireTokenSilent(tokenRequest);
    } catch (error) {
        if (error instanceof InteractionRequiredAuthError) {
            // check for any interactions
            if (myGlobalState.getInteractionStatus() !== InteractionStatus.None) {
                // throw a new error to be handled in the caller below
                throw new Error("interaction_in_progress");
            } else {
                // no interaction, invoke popup flow
                tokenResponse = await msalInstance.acquireTokenPopup(tokenRequest);
            }
        }

        console.log(error);
        // handle other errors
    }

    return tokenResponse;
};

async function myInteractionInProgressHandler() {
    /**
     * "myWaitFor" method polls the interaction status via getInteractionStatus() from
     * the application state and resolves when it's equal to "None".
     */
    await myWaitFor(() => myGlobalState.getInteractionStatus() === InteractionStatus.None);

    // wait is over, call myAcquireToken again to re-try acquireTokenSilent
    return (await myAcquireToken(tokenRequest));
};

const request = {
    scopes: ["User.Read"]
};

myAcquireToken(request).catch((e) => myInteractionInProgressHandler());
myAcquireToken(request).catch((e) => myInteractionInProgressHandler());
```

##### トラブルシューティングの手順

- [詳細ログを有効に](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md#using-the-config-object) し、イベントの順序をトレースします。 `handleRedirectPromise`が呼び出され、`login`または `acquireToken` API が呼び出される前に返されることを確認します。

このエラーが発生する原因がわからない場合は、[issue を作成し](https://github.com/AzureAD/microsoft-authentication-library-for-js/issues/new/choose)、次の情報を共有する準備をしてください。

- 詳細ログ
- 問題の再現に使用できるサンプル アプリやコード スニペット
- ページを更新します。 エラーは消えませんか?
- 新しいタブでアプリケーションを開きます。エラーは消えませんか?

#### block\_iframe\_reload

**エラー メッセージ**: MSAL が認証応答を検出したため、iframe 内で要求がブロックされました。

このエラーは、`ssoSilent` または `acquireTokenSilent` を呼び出したときに、`redirectUri` として使用しているページが login または acquireToken 関数を呼び出そうとすると発生します。 これに対して推奨される軽減策は、サイレント API を呼び出すときに MSAL を実装しない空白のページに `redirectUri` を設定するためです。 また、非表示の iframe ではページをレンダリングする必要がないため、パフォーマンスを向上させるという利点もあります。

✔️ これは要求ごとに行うことができます。次に例を示します。

```javascript
msalInstance.acquireTokenSilent({
    scopes: ["User.Read"],
    redirectUri: "http://localhost:3000/blank.html",
});
```

この新しい `redirectUri` をアプリ登録に登録する必要があります。

この目的で専用の `redirectUri` を使用しない場合は、代わりに、サイレント API によって使用される非表示の iframe 内でレンダリングされるときに、 `redirectUri` が MSAL API を呼び出そうとしないようにする必要があります。

#### monitor\_window\_timeout

**エラー メッセージ**:

- タイムアウトのため、iframe でのトークンの取得に失敗しました。

このエラーは、`ssoSilent`、`acquireTokenSilent`、`acquireTokenPopup`、または `loginPopup` を呼び出したときに発生することがあり、その理由はいくつかあります。 最も一般的なものは次のとおりです。

1. `redirectUri`として使用するページは、ハッシュを削除または操作しています
2. `redirectUri`として使用するページは、自動的に別のページに移動します。
3. ID プロバイダーにより制限されています
4. ID プロバイダーが`redirectUri`にリダイレクトしませんでした。

**重要**: アプリケーションでルーター ライブラリ (React Router、Angular Router など) を使用している場合は、MSAL トークンの取得の進行中にハッシュまたは自動リダイレクトが削除されないことを確認してください。 可能であれば、 `redirectUri` ページがルーターをまったく呼び出さない場合に最適です。

##### redirectUri ページによって発生する問題

サイレント呼び出しを行うと、場合によっては iframe が開き、ID プロバイダーの承認ページに移動します。 ID プロバイダーがユーザーを承認すると、ハッシュ フラグメント内の承認コードまたはエラー情報を使用して iframe が `redirectUri` にリダイレクトされます。 最初に要求を行ったフレームまたはウィンドウで実行されている MSAL インスタンスは、この応答ハッシュを抽出して処理します。 `redirectUri` がこのハッシュを削除または操作している場合、または MSAL がこのハッシュを抽出する前に別のページに移動した場合、このタイムアウト エラーが発生します。

✔️ この問題を解決するには、 `redirectUri` として使用するページが、少なくともポップアップまたは iframe に読み込まれるときに、これらの処理を実行しないようにする必要があります。 これらの処理が発生しないように、サイレント フローとポップアップ フローの `redirectUri` として空白のページを使用することをお勧めします。

これは要求ごとに行うことができます。次に例を示します。

```javascript
msalInstance.acquireTokenSilent({
    scopes: ["User.Read"],
    redirectUri: "http://localhost:3000/blank.html",
});
```

この新しい `redirectUri` をアプリ登録に登録する必要があります。

**Angular と React に関する注意事項:**

- `@azure/msal-angular`を使用している場合は、`redirectUri` ページを`MsalGuard`で保護しないでください。
- `@azure/msal-react` を使用している場合、`redirectUri` ページでは `MsalAuthenticationComponent` をレンダリングせず、`useMsalAuthentication` フックも使用しないでください。

##### ID プロバイダーによって発生する問題

##### Throttling

このエラーがスローされる最も一般的な理由の 1 つは、アプリケーションがループでスタックしたり、短時間でトークン要求が多すぎたりしたことです。 この場合、ID プロバイダーは短時間、後続のリクエストを制限することがあり、その結果、`redirectUri` にリダイレクトされなくなり、最終的にこのエラーが発生します。

✔️ スロットリングに起因する問題を解決するには、次の 2 つの方法があります。

1. 再試行する前に、しばらくの間要求を停止してください。
2. `acquireTokenPopup`や`acquireTokenRedirect`などの対話型 API を呼び出します。

###### X-Frame-Options 拒否

ID プロバイダーがアプリケーションへのリダイレクトに失敗した場合も、このエラーが発生する可能性があります。 サイレント シナリオでは、このエラーに X-Frame-Options: Deny エラーが伴うことがあります。これは、ID プロバイダーがエラー メッセージを表示しようとしているか、またはユーザーの操作を必要としていることを示しています。

✔️ X-Frame-Options エラーには通常、URL が含まれており、この URL を新しいタブで開くと、何が起こっているかを識別するのに役立つ場合があります。 対話が必要な場合は、代わりに対話型 API を使用することを検討してください。 エラーが表示されている場合は、エラーに対処します。

一部の B2C フローでは、ユーザーの操作を必要とするため、このエラーが発生することが想定されます。 これらのフローは次のとおりです。

- パスワードのリセット
- プロファイルの編集
- サインアップ
- 構成方法に応じた一部のカスタム ポリシー

###### ネットワーク待機時間

ID プロバイダーが時間内にアプリケーションにリダイレクトしないもう 1 つの考えられる理由は、ネットワーク待機時間が増える可能性があります。

✔️ 既定のタイムアウトは約 10 秒であり、ほとんどの場合は十分ですが、ID プロバイダーがリダイレクトに時間がかかっている場合は、 `iframeHashTimeout`、 `windowHashTimeout` 、または `loadFrameTimeout` 構成パラメーターを使用して MSAL 構成でこのタイムアウトを増やすことができます。

```javascript
const msalConfig = {
    auth: {
        clientId: "your-client-id",
    },
    system: {
        windowHashTimeout: 9000, // Applies just to popup calls - In milliseconds
        iframeHashTimeout: 9000, // Applies just to silent calls - In milliseconds
        loadFrameTimeout: 9000, // Applies to both silent and popup calls - In milliseconds
    },
};
```

#### hash\_empty\_error

**エラー メッセージ**:

>
> ハッシュ値は空であるため、処理できません。 redirectUri でハッシュがクリアされていないことを確認してください。

このエラーは、redirectUri として使用するページがハッシュを削除している場合、または別のページに自動リダイレクトしているときに発生します。 これは最も一般的に、アプリケーションが別のルートに移動し、ハッシュをドロップするルーターを実装する場合に発生します。

このエラーを解決するには、ルーターの対象ではない専用の redirectUri ページを使用することをお勧めします。 サイレント呼び出しとポップアップ呼び出しの場合は、空白のページを使用することをお勧めします。 これが不可能な場合は、MSAL トークンの取得の進行中にルーターが移動しないことを確認してください。 これを行うには、アプリケーションがサイレント呼び出しでは iframe 内に、ポップアップ呼び出しではポップアップ内に読み込まれているかどうかを検出するか、リダイレクト呼び出しでは `handleRedirectPromise` を待つことで実行できます。

#### ハッシュに既知のプロパティが含まれていません

**エラー メッセージ**:

>
> ハッシュに既知の固有値が含まれていません。 redirectUri でハッシュが変更されていないことを確認してください。

上記の hash_empty_error については、説明を参照してください。 このエラーの根本原因は似ていますが、ハッシュが削除されるのではなく変更されています。

#### ネイティブ プラットフォームからトークンを取得できません

**エラー メッセージ**:

- ネイティブ プラットフォームからトークンを取得できません。

このエラーは、`acquireTokenByCode`ではなく`nativeAccountId`を使用して `code` API を呼び出し、ネイティブ ブローカーからトークンを取得しない環境でアプリが実行されている場合にスローされます。 前提条件の一覧については、 [デバイス バインド トークン](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/device-bound-tokens.md)に関するドキュメントを参照してください。

#### ネイティブ接続が確立されていません

**エラー メッセージ**:

- ネイティブ プラットフォームへの接続が確立されていません。 互換性のあるブラウザー拡張機能をインストールし、initialize() を実行してください。

このエラーは、ユーザーがネイティブブローカーでサインインしているものの、ネイティブブローカーへの接続が現在確立されていない場合に発生します。 これが発生する理由としては次のようなことが考えられます。

- Windows アカウント拡張機能がアンインストールまたは無効になりました
- `initialize`API が呼び出されていないか、別の MSAL API を呼び出す前に待機されていません

#### 未初期化のパブリック クライアント アプリケーション

**エラー メッセージ**:

- 他の MSAL API を呼び出す前に、initialize 関数を呼び出して待機する必要があります。

このエラーは、`login` API が呼び出される前に、`acquireToken`、`handleRedirectPromise`、または`initialize` API が呼び出されたときにスローされます。 トークンの取得を試みる前に、 `initialize` API を呼び出して待機する必要があります。

 ❌次の例では、初期化が完了する前に `handleRedirectPromise` が呼び出されるため、このエラーがスローされます。

```javascript
const msalInstance = new PublicClientApplication({
    auth: {
        clientId: "your-client-id",
    },
    system: {
        allowNativeBroker: true,
    },
});

await msalInstance.handleRedirectPromise(); // This will throw
msalInstance.acquireTokenSilent(); // This will also throw
```

✔️ 解決するには、他の MSAL API を呼び出す前に、 `initialize` が解決されるまで待つ必要があります。

```javascript
const msalInstance = new PublicClientApplication({
    auth: {
        clientId: "your-client-id",
    },
    system: {
        allowNativeBroker: true,
    },
});

await msalInstance.initialize();
await msalInstance.handleRedirectPromise(); // This will no longer throw this error since initialize completed before this was invoked
msalInstance.acquireTokenSilent(); // This will also no longer throw this error
```

### Other

msal でスローされないエラー (サーバー エラーなど)

#### [url] へのアクセスは、CORS ポリシーによってブロックされました

このエラーは、MSAL.js v2.x で発生し、**Azure portal**での**アプリの登録**中に不適切な構成が原因で発生します。 特に、App Registration の **Authentication** ブレードで、`redirectUri` が種類: `Single-page application` として登録されていることを確認してください。 正常に完了すると、次の緑色のチェックマークが表示されます。

>
> リダイレクト URI は、PKCE を使用した承認コード フローの対象となります。

[Image: 画像]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/events"} -->
## MSAL ブラウザーのイベント - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/events
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: MSAL Browser イベント API を使用して認証イベントを処理し、アプリケーション UI を更新する方法について説明します

バージョン 2.4 以降の Msal-Browser (`@azure/msal-browser`) では、コア ライブラリとラッパー ライブラリのユーザーが使用できるイベント API が提供されるようになりました。 これらのイベントは認証と MSAL の処理に関連しており、アプリケーションで UI の更新やエラー メッセージの表示などを行うために使用できます。

### イベントの外観

```javascript
export type EventMessage = {
    eventType: EventType;
    interactionType: InteractionType | null;
    payload: EventPayload;
    error: EventError;
    timestamp: number;
};
```

`EventMessage`のペイロードとエラーは次のように定義されます。

```javascript
export type EventPayload = PopupRequest | RedirectRequest | SilentRequest | SsoSilentRequest | EndSessionRequest | AuthenticationResult | PopupEvent | null;

export type EventError = AuthError | Error | null;
```

### msal-browser でのイベントの生成方法

Msal-browser には、保護された関数 `emitEvent`があり、主要な API でイベントを出力します。 現在出力されているイベントの一覧については、次の表を参照してください。

msal-browser がペイロードまたはエラーを含むイベントを生成する方法の例を次に示します。

```javascript
this.emitEvent(EventType.LOGIN_SUCCESS, InteractionType.Redirect, result);

this.emitEvent(EventType.LOGIN_FAILURE, InteractionType.Redirect, null, e);
```

### イベント API の使用方法

Msal-browser は、コールバック関数を受け取る `addEventCallback` 関数をエクスポートし、出力されたイベントを処理するために使用できます。

アプリケーションで生成されたイベントを使用する方法の例を次に示します。

```javascript
const callbackId = msalInstance.addEventCallback((message: EventMessage) => {
    // Update UI or interact with EventMessage here
    if (message.eventType === EventType.LOGIN_SUCCESS) {
        console.log(message.payload);
     }
});
```

イベント コールバックを追加すると、ID が返されます。この ID は、msal-browser によってエクスポートされた `removeEventCallback` 関数を使用して、必要に応じてコールバックを削除するために使用できます。

```javascript
msalInstance.removeEventCallback(callbackId);
```

#### エラーの処理

`EventError`の定義方法により、イベントで生成されたエラーを処理するには、出力されたエラーの特定のプロパティにアクセスする前に、エラーが正しい型であることを検証する必要があります。 エラーは、 `AuthError` にキャストすることも、 `AuthError`のインスタンスであることを確認することもできます。

出力されたイベントを使用してエラーをキャストする例を次に示します。

```javascript
const callbackId = msalInstance.addEventCallback((message: EventMessage) => {
    // Update UI or interact with EventMessage here
    if (message.eventType === EventType.LOGIN_FAILURE) {
        if (message.error instanceof AuthError) {
            // Do something with the error
        }
     }
});
```

#### イベントからの対話状態の取得

[getInteractionStatusFromEvent](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/eventmessageutils) API を使用して、イベントから現在の対話状態を取得できます。

進行中の対話がない場合にメッセージを表示する例を次に示します。

```javascript
const callbackId = msalInstance.addEventCallback((message: EventMessage) => {
    const status = EventMessageUtils.getInteractionStatusFromEvent(message);

    // Update UI or interact with EventMessage here
    if (status === InteractionStatus.None) {
        console.log(message.payload);
    }
});
```

### タブとウィンドウ間でログに記録された状態を同期する

ユーザーがアプリにログインまたはログアウトするとき、または別のタブまたはウィンドウでアクティブなアカウントを変更するときに UI を更新する場合は、 `LOGIN_SUCCESS`、 `LOGOUT_SUCCESS`、および `ACTIVE_ACCOUNT_CHANGED` イベントをサブスクライブできます。

- アカウントの追加と削除の場合、ペイロードは追加または削除された `AccountInfo` オブジェクトになります。
- アクティブなアカウントの更新の場合、ペイロードはありません

```javascript
msalInstance.addEventCallback((message: EventMessage) => {
    if (message.eventType === EventType.LOGIN_SUCCESS) {
        // Update UI with new account
    } else if (message.eventType === EventType.LOGOUT_SUCCESS) {
        // Update UI with account logged out
    } else if (message.eventType === EventType.ACTIVE_ACCOUNT_CHANGED) {
        const accountInfo = msalInstance.getActiveAccount();
        // Update UI with new active account info
    }
});
```

### イベントの表

これらは、msal-browser によって現在生成されているイベントです。

| イベントの種類 | 説明 | 相互作用の種類 | ペイロード | エラー |
| --- | --- | --- | --- | --- |
| `LOGIN_START` | LoginPopup または loginRedirect が呼び出される | `Popup` または `Redirect` | [PopupRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#popuprequest) または [RedirectRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#redirectrequest) |  |
| `LOGIN_SUCCESS` | 正常にログインしました | `Popup` または `Redirect` | [AccountInfo](https://azuread.github.io/microsoft-authentication-library-for-js/ref/types/_azure_msal_common.AccountInfo.html) |  |
| `LOGIN_FAILURE` | ログイン時のエラー | `Popup` または `Redirect` |  | [AuthError](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/autherror) またはエラー |
| `ACQUIRE_TOKEN_START` | AcquireTokenPopup または acquireTokenRedirect または acquireTokenSilent が呼び出される | `Popup` または `Redirect` または `Silent` | [PopupRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#popuprequest) または [RedirectRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#redirectrequest) または [SilentRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#silentrequest) |  |
| `ACQUIRE_TOKEN_SUCCESS` | キャッシュまたはネットワークからトークンを正常に取得しました | `Popup` または `Redirect` または `Silent` | [AuthenticationResult](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#authenticationresult) |  |
| `ACQUIRE_TOKEN_FAILURE` | トークンを取得するときのエラー | `Popup` または `Redirect` または `Silent` |  | [AuthError](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/autherror) またはエラー |
| `ACQUIRE_TOKEN_NETWORK_START` | ネットワークからのトークンの取得の開始 | `Silent` |  |  |
| `SSO_SILENT_START` | SsoSilent API の呼び出し | `Silent` | [SsoSilentRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#ssosilentrequest) |  |
| `SSO_SILENT_SUCCESS` | SsoSilent succeeded | `Silent` | [AuthenticationResult](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#authenticationresult) |  |
| `SSO_SILENT_FAILURE` | SsoSilent failed | `Silent` |  | [AuthError](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/autherror) またはエラー |
| `HANDLE_REDIRECT_START` | HandleRedirectPromise の呼び出し | `Redirect` |  |  |
| `HANDLE_REDIRECT_END` | HandleRedirectPromise が完了しました | `Redirect` |  |  |
| `LOGOUT_START` | 呼び出されたログアウト | `Redirect` または `Popup` | [EndSessionRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionrequest) または [EndSessionPopupRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionpopuprequest) |  |
| `LOGOUT_END` | ログアウトが完了しました | `Redirect` または `Popup` |  |  |
| `LOGOUT_SUCCESS` | ログアウトの成功 | `Redirect` または `Popup` | [EndSessionRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionrequest) または [EndSessionPopupRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionpopuprequest) |  |
| `LOGOUT_FAILURE` | ログアウトに失敗しました | `Redirect` または `Popup` |  | [AuthError](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/autherror) またはエラー |
| `ACTIVE_ACCOUNT_CHANGED` | 別のタブまたはウィンドウで変更されたアクティブなアカウント フィルター | N/a | N/a | N/a |
| `INITIALIZE_START` | 呼び出された初期化関数 | N/a | N/a | N/a |
| `INITIALIZE_END` | 関数の初期化が完了しました | N/a | N/a | N/a |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/handle-errors-and-exceptions"} -->
## MSAL.js におけるエラーと例外の処理 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/handle-errors-and-exceptions
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: MSAL.js アプリケーションでエラーと例外、条件付きアクセス要求チャレンジ、再試行を処理する方法について説明します。

この記事では、さまざまな種類のエラーの概要と、一般的なサインイン エラーを処理するための推奨事項について説明します。

### MSAL エラー処理の基本

Microsoft Authentication Library (MSAL) の例外は、エンド ユーザーに表示されるのではなく、アプリ開発者がトラブルシューティングを行うために使用されます。 例外メッセージはローカライズされません。

例外とエラーを処理する場合は、例外の種類自体とエラー コードを使用して例外を区別できます。 エラー コードの一覧については、[認証と承認のエラー コードMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)参照してください。

サインイン エクスペリエンス中に、同意、条件付きアクセス (MFA、デバイス管理、場所ベースの制限)、トークンの発行と利用、およびユーザー プロパティに関するエラーが発生する場合があります。

次のセクションでは、アプリのエラー処理の詳細について説明します。

### MSAL.js でのエラー処理

MSAL.js は、さまざまな種類の一般的なエラーを抽象化して分類するエラー オブジェクトを提供します。 また、エラー メッセージなど、エラーの特定の詳細にアクセスして適切に処理するためのインターフェイスも提供します。

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

エラー クラスを拡張すると、次のプロパティにアクセスできます。

- `AuthError.message`: `errorMessage`と同じです。
- `AuthError.stack`: スローされたエラーのスタック トレースです。

#### エラーの種類

次のエラーの種類を使用できます。

- `AuthError`: MSAL.js ライブラリの基本エラー クラス。予期しないエラーにも使用されます。
- `ClientAuthError`: クライアント認証に関する問題を示すエラー クラス。 ライブラリから発生するほとんどのエラーは ClientAuthErrors です。 これらのエラーは、ログインが既に進行中の場合のログイン メソッドの呼び出し、ユーザーによるログインの取り消しなどが原因です。
- `ClientConfigurationError`:特定のユーザー構成パラメーターが正しくないか見つからないときに、要求が行われる前にスローされる、`ClientAuthError` を拡張するエラー クラス。
- `ServerError`: Error クラスは、認証サーバーによって送信されたエラー文字列を表します。 これらのエラーは、無効な要求形式またはパラメーター、またはサーバーがユーザーの認証または承認を妨げるその他のエラーである可能性があります。
- `InteractionRequiredAuthError`: Error クラスは、対話型呼び出しを必要とするサーバー エラーを表すために `ServerError` を拡張します。 このエラーは、ユーザーが認証/承認のために資格情報または同意を提供するためにサーバーと対話する必要がある場合に、 `acquireTokenSilent` によってスローされます。 エラー コードには、 `"interaction_required"`、 `"login_required"`、および `"consent_required"`が含まれます。

リダイレクト方法 (`loginRedirect`、 `acquireTokenRedirect`) を使用した認証フローでのエラー処理の場合は、次のように、 `handleRedirectPromise()` メソッドを使用してリダイレクト後に成功または失敗して呼び出されるリダイレクトの約束を処理する必要があります。

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

ポップアップ エクスペリエンス (`loginPopup`、 `acquireTokenPopup`) のメソッドは promise を返すので、promise パターン (`.then` と `.catch`) を使用して、次のように処理できます。

```javascript
myMSALObj.acquireTokenPopup(request).then(
    function (response) {
        // success response
    }).catch(function (error) {
        console.log(error);
    });
```

#### 対話を必要とするエラー

`acquireTokenSilent`などのトークンを取得する非対話型メソッドを使用しようとするとエラーが返されますが、MSAL では警告なしでは実行できませんでした。

次のような原因が考えられます。

- サインインする必要がある
- 同意する必要がある
- 多要素認証エクスペリエンスを使用する必要があります。

修復では、 `acquireTokenPopup` や `acquireTokenRedirect`などの対話型メソッドを呼び出します。

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

### 条件付きアクセスと要求の課題

トークンをサイレントで取得すると、アクセスしようとしている API で MFA ポリシーなどの [条件付きアクセス要求チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide) が必要な場合、アプリケーションでエラーが発生する可能性があります。

このエラーを処理するパターンは、MSAL を使用して対話形式でトークンを取得することです。 これにより、ユーザーにプロンプトが表示され、必要な条件付きアクセス ポリシーを満たす機会が提供されます。

場合によっては、条件付きアクセスが必要な API を呼び出す際に、API から返されるエラー内でクレーム チャレンジを受け取ることがあります。 たとえば、条件付きアクセス ポリシーでマネージド デバイス (Intune) を使用する場合、エラーは [AADSTS53000 のようになります。このリソースや同様のリソースにアクセスするには、デバイスを管理する必要があります](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes) 。 この場合、取得トークン呼び出しで要求を渡して、ユーザーが適切なポリシーを満たすように求めることができます。

MSAL.jsを使用して ( `acquireTokenSilent` を使用して) トークンをサイレントに取得すると、アクセスしようとしている API で MFA ポリシーなどの [条件付きアクセス要求チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide) が必要な場合、アプリケーションでエラーが発生する可能性があります。

このエラーを処理するパターンは、次の例のように、 `acquireTokenPopup` や `acquireTokenRedirect` などの MSAL.js でトークンを取得するための対話型呼び出しを行います。

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

トークンを対話形式で取得すると、ユーザーにプロンプトが表示され、必要な条件付きアクセス ポリシーを満たす機会が与えられます。

条件付きアクセスを必要とする API を呼び出すと、API からエラーの要求チャレンジを受け取ることができます。 この場合、エラーで返された要求を`claims`の  パラメーターに渡して、適切なポリシーを満たすことができます。

詳細については、 [アプリケーションで継続的アクセス評価が有効な API を使用する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-resilience-continuous-access-evaluation) を参照してください。

#### 他のフレームワークの使用

ID プラットフォームで登録されたシングル ページ アプリケーション (SPA) に Tauri などのツールキットを使用することは、実稼働アプリでは認識されません。 SPA では、運用アプリの `https` で始まる URL と、ローカル開発用の `http://localhost` のみがサポートされます。 `tauri://localhost`などのプレフィックスは、ブラウザー アプリには使用できません。 この形式は、ブラウザー アプリとは異なり機密コンポーネントがあるため、モバイル アプリまたは Web アプリでのみサポートできます。

### エラーと例外の後の再試行

MSAL を呼び出すときに、独自の再試行ポリシーを実装する必要があります。 MSAL では、Microsoft Entra サービスへの HTTP 呼び出しが行われ、エラーが発生することがあります。 たとえば、ネットワークがダウンしたり、サーバーが過負荷になったりする可能性があります。

#### HTTP 429

サービス トークン サーバー (STS) が多すぎる要求でオーバーロードされると、HTTP エラー 429 が返され、Retry-After 応答フィールドで再試行できるまでの時間に関するヒントが返されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/iframe-usage"} -->
## iframed アプリでの MSAL の使用 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/iframe-usage
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: iframed アプリで MSAL を使用する方法について説明します

既定では、MSAL は、アプリが iframe 内にレンダリングされるときに**、Microsoft Entra ID**認証エンドポイントへのフルフレーム リダイレクトを防止します。つまり、IdP とのユーザー操作に[リダイレクト API を](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization#redirect-apis)使用することはできません。

- **この**制限は、Microsoft Entra IDは**、クリックジャッキング攻撃**を防ぐための手段である**エラー**をスローすることによって、ユーザーの操作 (`X-FRAME OPTIONS SET TO DENY`、[同意](https://html.spec.whatwg.org/multipage/browsing-the-web.html#the-x-frame-options-header)、[ログアウト](https://owasp.org/www-community/attacks/Clickjacking)など) を必要とするプロンプトを iframe に表示することを拒否するためです。
- 代わりに、ユーザー操作が必要な場合は MSAL の [ポップアップ API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization#popup-apis) に依存し、ユーザー操作を回避できる場合はサイレント API (`ssoSilent()`、 `acquireTokenSilent()`) に依存する必要があります。
- 同様に、[サインアウトには logoutPopup()](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/logout#logoutpopup) API を使用する必要があります (:warning: アプリで v2.13 より前のバージョンの`msal-browser`を使用している場合は、`logout()` API をアップグレードして置き換えてください。Microsoft Entra IDへのフル フレーム リダイレクトが試行されるためです)。
- [ポップアップ API を](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization#popup-apis)使用する場合は、親アプリによって課される[サンドボックス制限を](https://html.spec.whatwg.org/multipage/origin.html#sandboxing)考慮する必要があります。 特に、親アプリは、iframe がサンドボックス化されるときに `allow-popups` フラグを設定する必要があります。

**Azure AD B2C** には、iframe でカスタム ログイン UI をレンダリングできる[埋め込みサインイン エクスペリエンス](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/embedded-login)が用意されています。 MSAL では既定で iframe でのリダイレクトが禁止されるため、この機能を利用するには [allowRedirectInIframe](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration#system-config-options) 構成オプションを **true** に設定する必要があります。 上記の制限により、**Microsoft Entra ID**上のアプリでこのオプションを有効にすることはお勧めしません。

### ブラウザーの制限

iframe 内のMicrosoft Entraセッション Cookie は[サード パーティの Cookie](https://developer.mozilla.org/docs/Web/HTTP/Cookies#third-party_cookies) と見なされるため、特定のブラウザー (**シークレット モードの** **Safari** や *Chrome* など) は、既定でこれらの Cookie をブロックまたはクリアします。 IdP のセッション Cookie にアクセスできないため、iframed アプリの **シングル サインオン** エクスペリエンスに影響します (「 シングル サインオン」を参照)。

さらに、 **Chrome** でサード パーティの Cookie が無効になっている場合、iframed MSAL アプリはローカルまたはセッション ストレージにアクセスできません。 この場合、MSAL はメモリ内ストレージにフォールバックします。

### 単一サインイン

**親アプリから iframe に埋め込まれたアプリに [アカウント ヒント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user#silent-login-with-ssosilent) を渡すと、**[同一オリジン](https://developer.mozilla.org/docs/Web/Security/Same-origin_policy)**および**[クロスオリジン](https://developer.mozilla.org/docs/Web/Security/Same-origin_policy#cross-origin_script_api_access)の iframe に埋め込まれたアプリと親アプリの間で、[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-sso)を実現できます。

#### 同一オリジンのアプリ

同じ配信元の Iframed アプリと親アプリは、同じ MSAL.js キャッシュ インスタンスにアクセスでき、両方のアプリがキャッシュに [ローカル ストレージ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/caching#cache-storage) を使用するように MSAL を構成している場合、プロンプトなしでサインインできます。 詳細については、「[MSAL.jsでのシングル サインオン」](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-sso)を参照してください。

#### クロスオリジンを使用するアプリ

クロスオリジンの Iframed アプリと親アプリでは、 [ssoSilent()](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user#silent-login-with-ssosilent) API を使用してシングル サインオンを実現できます。 これを行うには、親アプリが **アカウント**、 **loginHint** (ユーザー名) または **セッション ID** (sid) を iframed アプリに渡す必要があります。

アプリは、上記のパラメーターなしで `ssoSilent` の使用を試みることができます。 ただし、ユーザーのセッションに関する情報を提供せずにを使用する場合は、`ssoSilent`があることに注意してください。

iframed アプリと親アプリの間のクロスオリジン通信には、いくつかの選択肢を検討できます。

- 親アプリで iframe のソースにクエリ文字列を追加し、後で子で取得できます。

```javascript
// Create the main myMSALObj instance
// configuration parameters are located at authConfig.js
const myMSALObj = new msal.PublicClientApplication({
    auth: {
        clientId: "ENTER_CLIENT_ID",
        authority: "https://login.microsoftonline.com/ENTER_TENANT_ID",
        redirectUri: "/redirect", // set to a blank page for handling auth code response via popups
    },
    cache: {
        cacheLocation: "localStorage", // set your cache location to local storage
    },
});

window.onload = () => {
    
    const urlParams = new URLSearchParams(window.location.search);
    const sid = urlParams.get("sid");

    // attempt SSO
    myMSALObj.ssoSilent({
        sid: sid
    }).then((response) => {
        // do something with response
    }).catch(error => {
        // handle errors
    });
}
```

- 親アプリで [postMessage()](https://html.spec.whatwg.org/multipage/web-messaging.html#dom-window-postmessage-options-dev) API を使用し、子アプリのメッセージ イベントをリッスンできます。

```javascript
// Create the main myMSALObj instance
// configuration parameters are located at authConfig.js
const myMSALObj = new msal.PublicClientApplication({
    auth: {
        clientId: "ENTER_CLIENT_ID",
        authority: "https://login.microsoftonline.com/ENTER_TENANT_ID",
        redirectUri: "/redirect", // set to a blank page for handling auth code response via popups
    },
    cache: {
        cacheLocation: "localStorage", // set your cache location to local storage
    },
});

const parentDomain = "http://localhost:3001";

window.addEventListener("message", (event) => {
    // check the origin of the data
    if (event.origin === parentDomain) {
        const sid = event.data;

        // attempt SSO
        myMSALObj.ssoSilent({
            sid: sid
        }).then((response) => {
            // do something with response
        }).catch(error => {
            // handle errors
        });
    }
});
```

### エラー処理

`ssoSilent()`が失敗した場合は、エラーをキャッチして処理する必要があります。 具体的には次のとおりです。

- [InteractionRequiredError](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/interactionrequiredautherror): 同意が必要な場合、ユーザーが MFA などを実行する必要がある場合にスローされます。このエラーは、多くの場合、対話型 API を開始するだけで処理できます。
- [BrowserAuthError](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser/browserautherror): *アカウント ヒント* が指定されていない場合や無効な場合、ポップアップがブロックされている場合などにスローされます。`errorCode` を確認し、これらに適切に対処する必要があります。

```javascript
    myMSALObj.ssoSilent({
        sid: sid
    }).then((response) => {
            // do something with response
        }).catch(error => {
            if (error instanceof msal.InteractionRequiredAuthError) {
                myMSALObj.loginPopup()
                    .then((response) => {
                        // do something with response
                    });
            } else if (error instanceof msal.BrowserAuthError) {
                if (error.errorCode === "silent_sso_error") {
                    // e.g. username is null
                }
                if (error.errorCode === "popup_window_error") {
                    // e.g. popups are blocked
                }
            } else {
                console.log(error);
            }
        });
```

### ユーザー操作

ユーザーの操作を必要とする IdP との通信を最小限に抑える場合、または何らかの理由でポップアップに問題がある場合は、いくつかのオプションを検討できます。

- [管理者の同意を付与します](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-admin-consent)。 これにより、ユーザーが初めてサインインするときに、アプリで必要なアクセス許可に対する同意プロンプトが表示されなくなります。
- [クライアント アプリを事前に承認](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest#preauthorizedapplications-attribute)します。 これにより、クライアント アプリによって呼び出されたときに、Web API に必要なアクセス許可に対する同意プロンプトが表示されなくなります。

### シングル サインアウト

[MSAL.js をフロント チャネル ログアウト URI](https://openid.net/specs/openid-connect-backchannel-1_0.html) と共に使用して、iframed アプリと親アプリの間*でシングル サインアウト*効果を実現できます。 たとえば、ユーザーが親アプリからログアウトするときに、ユーザーが iframed アプリから自動的にログアウトするようにする場合は、iframed アプリのフロント チャネル ログアウトを有効にする必要があります。 これを行うには、「 [フロント チャネル ログアウト URI を構成する方法](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/logout#front-channel-logout)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/initialization"} -->
## MSAL ブラウザーの初期化 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: PublicClientApplication と構成オプションを使用して JavaScript アプリケーションで MSAL.js を初期化する方法について説明します

MSAL Browser を初期化する前に、まず[アプリケーションをMicrosoft Entra 管理センターに登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)して、アプリケーション (クライアント) ID を取得します。

### CreatePCA パターン

MSAL.js は、アプリの`CreatePCA`の種類を選択できる`PublicClientApplication` パターンを提供します。 現在のオプションには、 `Standard` 構成と `Nestable` 構成が含まれます。 今後、より多くの構成が導入される予定です。

#### 標準構成

シングルページ アプリケーションで MSAL.js を使用している場合は、msal-browser をインポートして、`IPublicClientApplication`を含む`createStandardPublicClientApplication` インスタンスを作成します。 この関数は、標準構成で `PublicClientApplication` インスタンスを作成します。

```javascript
import * as msal from "@azure/msal-browser";

const pca = msal.createStandardPublicClientApplication({
    auth: {
        clientId: "ENTER_CLIENT_ID",
        authority: "https://login.microsoftonline.com/ENTER_TENANT_ID",
    },
});
```

#### ネストされたアプリ構成

アプリがハブ SDK (MetaOS フレームワークで実行されている SPA またはデスクトップ アプリケーション) にその認証を委任する iframed 入れ子になったアプリの場合は、msal-browser をインポートして、`IPublicClientApplication`を使用して`createNestablePublicClientApplication` インスタンスを作成します。 この関数は、NAA 構成を使用して `PublicClientApplication` インスタンスを作成します。

```javascript
import * as msal from "@azure/msal-browser";

const nestablePca = msal.createNestablePublicClientApplication({
    auth: {
        clientId: "ENTER_CLIENT_ID",
        authority: "https://login.microsoftonline.com/ENTER_TENANT_ID",
    },
});
```

Important

入れ子になったアプリ認証をオプトインする前に、次のガイダンスを確認してください。

- `createNestablePublicClientApplication` 入れ子になったアプリ ブリッジが使用できない場合、または入れ子になったアプリ認証をサポートするようにハブが構成されていない場合は、 `createStandardPublicClientApplication` にフォールバックします。
- アプリケーションを入れ子になったアプリにする必要がない場合は、代わりに `createStandardPublicClientApplication` を使用する必要があります。
- NAA アプリでは、特定のアカウント参照 API はサポートされていません。 詳細については、「 [アクティブなアカウント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/accounts#active-account-apis)」を参照してください。

### PublicClientApplication オブジェクトの初期化

MSAL.jsを使用するには、 `PublicClientApplication` オブジェクトをインスタンス化する必要があります。 アプリケーションの `client id` (`appId`) を指定する必要があります。

#### オプション 1

`PublicClientApplication` オブジェクトをインスタンス化し、後で初期化します。 `initialize`関数は非同期であり、他の MSAL.js API を呼び出す前に解決する必要があります。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

const msalConfig = {
    auth: {
        clientId: 'your_client_id'
    }
};

const msalInstance = new PublicClientApplication(msalConfig);
await msalInstance.initialize();
```

#### 方法 2

初期化された`createPublicClientApplication` オブジェクトを返す`PublicClientApplication`静的メソッドを呼び出します。 この関数は非同期であることに注意してください。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

const msalConfig = {
    auth: {
        clientId: 'your_client_id'
    }
};

const msalInstance = await PublicClientApplication.createPublicClientApplication(msalConfig);
```

### (省略可能)権限の構成

既定では、MSAL は `common` テナントで構成されます。これは、(B2C ではなく) 個人アカウントを許可するマルチテナント アプリケーションおよびアプリケーションに使用されます。

```javascript
const msalConfig = {
    auth: {
        clientId: 'your_client_id',
        authority: 'https://login.microsoftonline.com/common/'
    }
};
```

アプリケーションの対象が単一テナントである場合は、以下のようにテナント ID を含む authority を指定する必要があります。

```javascript
const msalConfig = {
    auth: {
        clientId: 'your_client_id',
        authority: 'https://login.microsoftonline.com/{your_tenant_id}'
    }
};
```

アプリケーションで `"https://login.live.com"` や IdentityServer などの OIDC 準拠の別の機関を使用している場合は、 `knownAuthorities` フィールドに指定し、 `protocolMode` を `"OIDC"` に設定する必要があります。

```javascript
const msalConfig = {
    auth: {
        clientId: 'your_client_id',
        authority: 'https://login.live.com',
        knownAuthorities: ["login.live.com"],
    },
    system: {
        protocolMode: "OIDC",
    }
};
```

Note

`protocolMode`構成オプションは、Microsoft Entra ID固有の変更を有効にするかどうかを MSAL に指示し、次の動作を変更します。

- 認証局メタデータ（`v2.4.0`以降）:
    - `OIDC`に設定すると、権限メタデータをフェッチするときに、ライブラリは権限パスに`/v2.0/`を含めません。
    - `AAD` (既定値) に設定すると、権限メタデータをフェッチするときに、ライブラリは権限パスに`/v2.0/`を含めます。

### （任意）リダイレクト URI を設定する

既定では、MSAL は、リダイレクト URI を実行中の現在のページに設定するように構成されています。 MSAL を実行しているページとは異なるページで承認コードを受け取る場合は、構成でこれを設定できます。

```javascript
const msalConfig = {
    auth: {
        clientId: 'your_client_id',
        authority: 'https://login.microsoftonline.com/{your_tenant_id}',
        redirectUri: 'https://contoso.com'
    }
};
```

使用するリダイレクト URI は、ポータルの登録で構成する必要があります。 [ログイン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user) API [と要求 API を](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/acquire-token)使用して、要求ごとのリダイレクト URI を設定することもできます。

### (省略可能)追加の構成

MSAL には、ここで確認できる追加の構成オプション [があります](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration)。

### 0 個以上の利用可能なアカウントでのアプリ起動の処理

次のフロー図は、1 つのアカウント (または複数のアカウント) が SSO に使用できる場合に、不要な認証プロンプトを回避するのに役立ちます。

[Image: ブート フローのMSAL.js 図]

### 相互作用の種類の選択

ブラウザーでは、アプリケーションからユーザーにログイン画面を表示する方法が 2 つあります。

- 現在のページからポップアップ ウィンドウを表示する
- ブラウザー ウィンドウをログイン サーバーにリダイレクトする

#### ポップアップ API群

- `loginPopup`
- `acquireTokenPopup`

ポップアップ API では、ポップアップ内の認証フローが終了して指定されたリダイレクト URI に戻ったときに解決する ES6 Promise を使用するか、コードに問題がある場合やポップアップがブロックされた場合は拒否します。

##### RedirectUri に関する考慮事項

ポップアップ API を使用する場合、 `redirectUri` は MSAL リダイレクト ブリッジを実装する専用ページを指す必要があります。 このページでは、認証応答を処理し、メイン アプリケーションに通信します。

リダイレクト ページの設定に関する詳細なガイダンスについては、「 [RedirectUri の考慮事項](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user#redirecturi-considerations)」を参照してください。

```javascript
msalInstance.loginPopup({
    redirectUri: "http://localhost:3000/redirect",
});
```

#### リダイレクト API

- `loginRedirect`
- `acquireTokenRedirect`

注: `msal-angular` または `msal-react`を使用している場合、リダイレクトは異なる方法で処理されます。詳細については、 [`msal-angular` リダイレクトドキュメント](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/docs/redirects.md) と [`msal-react` FAQ](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-react/FAQ.md#how-do-i-handle-the-redirect-flow-in-a-react-app) が表示されます。

リダイレクト API は、基本的な情報をキャッシュした後にブラウザー ウィンドウをリダイレクトする非同期 (つまり、promise を返す) `void` 関数です。 リダイレクト API を使用する場合は、API を **正しく処理するために `handleRedirectPromise()` を呼び出す必要があることに**注意してください。 次の関数を使用して、このトークン交換が完了したときにアクションを実行できます。

```javascript
msalInstance.handleRedirectPromise().then((tokenResponse) => {
    // Check if the tokenResponse is null
    // If the tokenResponse !== null, then you are coming back from a successful authentication redirect.
    // If the tokenResponse === null, you are not coming back from an auth redirect.
}).catch((error) => {
    // handle error, either in the library or coming back from the server
});
```

これにより、ページの再読み込み時にトークンを取得することもできます。 使用方法の詳細については、 [onPageLoad サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/app/onPageLoad/) を参照してください。

1 つのアプリケーションで両方の対話の種類を使用することはお勧めしません。

Note

`handleRedirectPromise` 必要に応じて、処理するハッシュ値を受け入れます。既定値は `window.location.hash` の現在の値です。 このパラメーターは、 `window.location.hash` の現在の値に、処理する必要があるリダイレクト応答が含まれていないシナリオでのみ指定する必要があります。 **ほぼすべてのシナリオで、アプリケーションでこのパラメーターを明示的に指定する必要はありません。**
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/known-issues-ie-edge-browsers"} -->
## Internet Explorerに関する問題Microsoft Edge (MSAL.js) - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/known-issues-ie-edge-browsers
- Service: msal / msal-js
- Article date: 2020-05-18
- Summary: Internet ExplorerブラウザーとMicrosoft EdgeブラウザーでJavaScript 用 Microsoft Authentication Library (MSAL.js) を使用する場合の問題を把握する方法について説明します。

### セキュリティ ゾーンによる問題

IE とMicrosoft Edgeでの認証に関する複数の問題の報告がありました (*Microsoft Edge ブラウザーバージョンが 40.15063.0.0 に*更新された以降)。 これらは追跡中であり、Microsoft Edge チームに通知しています。 Microsoft Edgeは解決策で動作しますが、頻繁に発生する問題と、実装可能な回避策について説明します。

#### 原因

これらの問題のほとんどの原因は次のとおりです。 セッション ストレージとローカル ストレージは、Microsoft Edge ブラウザーのセキュリティ ゾーンによってパーティション分割されます。 この特定のバージョンのMicrosoft Edgeでは、アプリケーションがゾーン間でリダイレクトされると、セッション ストレージとローカル ストレージがクリアされます。 具体的には、通常のブラウザー ナビゲーションでセッション ストレージがクリアされ、セッションとローカル ストレージの両方がブラウザーの InPrivate モードでクリアされます。 MSAL.js セッション ストレージに特定の状態を保存し、認証フロー中にこの状態を確認します。 セッション ストレージがクリアされると、この状態は失われ、エクスペリエンスが壊れます。

#### 課題

- **認証中の無限リダイレクト ループとページ の再読み込み**。 ユーザーがMicrosoft Edgeでアプリケーションにサインインすると、ユーザーはMicrosoft Entraログイン ページからリダイレクトされ、無限のリダイレクト ループでスタックし、ページの再読み込みが繰り返されます。 通常、これはセッション ストレージ内の `invalid_state` エラーを伴います。
- **無限取得トークン ループとAADSTS50058 エラー**。 Microsoft Edgeで実行されるアプリケーションがリソースのトークンを取得しようとすると、取得トークン呼び出しの無限ループでアプリケーションがスタックする可能性があります。 ネットワーク トレースのMicrosoft Entra IDから次のエラーが返されます。

    `Error :login_required; Error description:AADSTS50058: A silent sign-in request was sent but no user is signed in. The cookies used to represent the user's session were not sent in the request to Azure AD. This can happen if the user is using Internet Explorer or Edge, and the web app sending the silent sign-in request is in different IE security zone than the Azure AD endpoint (login.microsoftonline.com)`
- **ポップアップ ウィンドウを使用して認証を行うと、ポップアップ ウィンドウが閉じなかったり、スタックしたりすることはありません**。 Microsoft Edgeまたは IE (InPrivate) のポップアップ ウィンドウを介して認証する場合、資格情報を入力してサインインした後、セキュリティ ゾーン間で複数のドメインがナビゲーションに関係している場合、ポップアップ ウィンドウは閉じられません。ポップアップ ウィンドウ`MSAL.js`ハンドルが失われるためです。
- **tauri のプレフィックスが付いたリダイレクト URL を使用してログインすることはできません**。 リダイレクト URI に対してサポートされているスキームは、実稼働アプリ用に `https:` され、ローカル開発用に `http://localhost` のみです。 モバイルまたはデスクトップ アプリケーションに対して、 `tauri://localhost`などの別のスキームを使用しようとすると、次のエラー メッセージが表示されます。 このエラーは、SPA のバックエンドの設計方法の結果として発生します。

    `AADSTS90023: Cross-origin token redemption is permitted only for the 'Single-Page Application' client-type or 'Native' client-type with origin registered in AllowedOriginForNativeAppCorsRequestInOAuthToken allow list.`

#### 更新: MSAL.js 0.2.3 で利用可能な修正プログラム

認証リダイレクト ループの問題の修正プログラムは [、MSAL.js 0.2.3](https://github.com/AzureAD/microsoft-authentication-library-for-js/releases) でリリースされました。 この修正プログラムを利用するには、MSAL.js 構成でフラグ `storeAuthStateInCookie` を有効にします。 既定では、このフラグは false に設定されています。

`storeAuthStateInCookie` フラグが有効になっている場合、MSAL.js はブラウザーの Cookie を使用して、認証フローの検証に必要な要求の状態を格納します。

Note

この修正プログラムは、 `msal-angular` ラッパーと `msal-angularjs` ラッパーではまだ使用できません。 この修正プログラムは、ポップアップ ウィンドウの問題には対処しません。

##### その他の回避策

これらの回避策を採用する前に、問題が特定のバージョンの Microsoft Edge ブラウザーでのみ発生していることをテストし、他のブラウザーで動作することを確認してください。

1. これらの問題を回避するための最初の手順として、認証フローのリダイレクトに関連するアプリケーション ドメインおよびその他のサイトが、ブラウザーのセキュリティ設定に信頼済みサイトとして追加されていることを確認します。 これにより、リダイレクトが同じセキュリティ ゾーンに属することが保証されます。 そのためには、次の手順に従います。

    - **Internet Explorer**開き、右上隅にある**設定** (歯車アイコン) をクリックします
    - **インターネット オプションの**選択
    - [ **セキュリティ** ] タブを選択する
    - [ **信頼済みサイト** ] オプションで、[ **サイト** ] ボタンをクリックし、表示されるダイアログ ボックスに URL を追加します。
2. 前述のように、通常のナビゲーション中にセッション ストレージのみがクリアされるため、代わりにローカル ストレージを使用するように MSAL.js を構成できます。 これは、MSAL の初期化中に `cacheLocation` 構成パラメーターとして設定できます。

これらの回避策では、セッションとローカル ストレージの両方がクリアされるため、InPrivate の参照に関する問題は解決されません。

### ポップアップ ブロックによる問題

多[要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)中に 2 つ目のポップアップが発生した場合など、IE またはMicrosoft Edgeでポップアップがブロックされる場合があります。 ポップアップ ウィンドウを 1 回または常に許可するアラートがブラウザーに表示されます。 許可を選ぶと、ブラウザーがポップアップ ウィンドウを自動的に開き、そのウィンドウの `null` ハンドルを返します。 その結果、ライブラリにはウィンドウのハンドルがないため、ポップアップ ウィンドウを閉じる方法はありません。 ポップアップ ウィンドウが自動的に開かないため、ポップアップ ウィンドウを許可するように求められた場合、Chrome でも同じ問題は発生しません。

**回避策**として、開発者は、この問題を回避するために、アプリの使用を開始する前に IE とMicrosoft Edgeでポップアップを許可する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/logging"} -->
## アプリケーションでログ記録を有効にする - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/logging
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: ログ レベルとコールバックを使用して MSAL.js ログを有効にして構成し、診断情報を収集する方法について説明します

アプリケーションの MSAL JS ログを有効にするために必要な手順を次に示します。

1. MSAL [構成](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration) オブジェクトでは、ログ記録を有効にして msal js ログを収集できます。 さまざまなレベルのログ記録を有効にし、必要に応じて適切なレベルを選択できます。
2. ロガー オプションは次のように設定できます。この例では、 `LogLevel.Trace`

```javascript

import { PublicClientApplication, LogLevel } from "@azure/msal-browser";

const msalConfig = {
    auth: {
        ...
    },
    cache: {
        ...
    },
    system: {
        loggerOptions: {
            logLevel: LogLevel.Trace,
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
                        console.log(message);
                        return;
                }    
            }
        }
    },

    ...
}

const msalInstance = new PublicClientApplication(msalConfig);      

```

サンプルの使用例は [、ここで](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/app/default/authConfig.js#:%7E:text=logLevel%3A%20msal.LogLevel.Trace%2C)アクセスできます。

1. これらのログを表示するには、ブラウザーコンソールで適切なログ レベルが有効になっていることを確認します。たとえば、ブラウザーがこれらを読み込むには、"verbose" を有効にする必要がある場合があります。

    [Image: ブラウザー コンソール]

### ログ レベルと PII 設定をオーバーライドする

以下は、MSAL ログ レベルと PII 設定をオーバーライドして、開発環境以外のエラーのトラブルシューティングを行う手順です。

#### セッション ストレージに移動する

1. ブラウザー開発者ツールを開く
    - Edge、Chrome、Firefox のブラウザー: F12 キーを押します
    - Safari: Safari の環境設定 (`Safari Menu`&gt;`Preferences`) に移動し、 `Advanced Tab` を選択して `Show features for web developers`を有効にします。 そのメニューが有効になると、開発者コンソールが表示されます。 `Develop`&gt;`Show Javascript Console`
2. `Session Storage`に移動します。
    - [Edge](https://learn.microsoft.com/ja-jp/microsoft-edge/devtools-guide-chromium/storage/sessionstorage)
    - [クロム](https://developer.chrome.com/docs/devtools/storage/sessionstorage)
    - [Firefox](https://firefox-source-docs.mozilla.org/devtools-user/storage_inspector/local_storage_session_storage)
    - Safariで`Storage`タブに移動し、`Session Storage`を展開します
3. ターゲット ドメインの選択

#### ログ レベルをオーバーライドする

`msal.browser.log.level``Session Storage`キーを追加し、その値を目的のログ レベル (`Verbose` など) に設定し、ページを更新してサインイン操作を再試行します。

#### PII ログ設定をオーバーライドする

`msal.browser.log.pii`キーを`Session Storage`に追加し、その値を`true`または`false`に設定し、ページを更新してサインイン操作を再試行します。

### キャプチャされたログを取得する

1. コンソール タブに移動します。
    - [Edge](https://learn.microsoft.com/ja-jp/microsoft-edge/devtools-guide-chromium/console)
    - [クロム](https://developer.chrome.com/docs/devtools/console)
    - [Firefox](https://firefox-source-docs.mozilla.org/devtools-user/browser_console)
    - Safari: JavaScript コンソールを開き、`Console` に移動します
2. 共有する前に、ログを確認して機密データが含まれていないことを確認してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/login-user"} -->
## ユーザーのサインイン - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: ポップアップメソッドとリダイレクトメソッドを使用して MSAL.js を使用してユーザーをサインインさせ、承認コードとトークンを取得する方法について説明します

ここで開始する前に、 [アプリケーション オブジェクトを初期化](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization)する方法を理解していることを確認してください。

MSAL のログイン API は、サインインしているユーザーについて、[ID token](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens) と交換できる `authorization code` を取得します。また、追加のリソースに対するスコープへの同意とともに、ユーザーが同意したスコープを含む [access token](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) を取得し、これによりアプリは API を安全に呼び出すことができます。

### 相互作用の種類の選択

と`loginRedirect`の違いがわからない場合は、`loginPopup`参照してください。

### ユーザーにログインする

ログイン API に要求オブジェクトを渡す必要があります。 このオブジェクトを使用すると、要求で異なるパラメーターを使用できます。 要求オブジェクトのパラメーターの詳細については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/request-response-object) 参照してください。

ログイン要求の場合、すべてのパラメーターは省略可能であるため、空のオブジェクトを送信できます。

- Popup

```javascript
try {
    const loginResponse = await msalInstance.loginPopup({});
} catch (err) {
    // handle error
}
```

- リダイレクト

```javascript
try {
    msalInstance.loginRedirect({});
} catch (err) {
    // handle error
}
```

または、一連の [スコープ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/resources-and-scopes) を送信して、次の内容に事前に同意することもできます。

- Popup

```javascript
var loginRequest = {
    scopes: ["user.read", "mail.send"] // optional Array<string>
};

try {
    const loginResponse = await msalInstance.loginPopup(loginRequest);
} catch (err) {
    // handle error
}
```

- リダイレクト

```javascript
var loginRequest = {
    scopes: ["user.read", "mail.send"] // optional Array<string>
};

try {
    msalInstance.loginRedirect(loginRequest);
} catch (err) {
    // handle error
}
```

### アカウント API

ログイン呼び出しが成功したら、 `getAllAccounts()` 関数を使用して、現在サインインしているユーザーに関する情報を取得できます。

```javascript
const myAccounts: AccountInfo[] = msalInstance.getAllAccounts();
```

アカウント情報がわかっている場合は、 `getAccount()` API を使用してアカウント情報を取得することもできます。

```javascript
const username = "test@contoso.com";
const myAccount: AccountInfo = msalInstance.getAccount({ username });

const homeAccountId = "userid.hometenantid"; // Best to retrieve the homeAccountId from an account object previously obtained through msal
const myAccount: AccountInfo = msalInstance.getAccount({ homeAccountId });
```

Note

`username`によるフィルター処理は便宜上提供されており、`homeAccountId`に基づく検索よりも信頼性が低いと見なす必要があります。 可能な場合は、`homeAccountId` を使用します。

B2C シナリオでは、`emails` API で `idTokens` フィルターを使用するために、`username`で`getAccount()`要求を返すように B2C テナントを構成する必要があります。

これらの API は、次のシグネチャを持つアカウント オブジェクトまたはアカウント オブジェクトの配列を返します。

```javascript
{
    // home account identifier for this account object
    homeAccountId: string;
    // Entity who issued the token represented as a full host of it (e.g. login.microsoftonline.com)
    environment: string;
    // Full tenant or organizational id that this account belongs to
    tenantId: string;
    // preferred_username claim of the id_token that represents this account.
    username: string;
};
```

### ssoSilent() を使用したサイレント ログイン

認証サーバーとのセッションが既に存在する場合は、ssoSilent() API を使用して、対話なしでトークンの要求を行うことができます。

#### ユーザーヒント付き

ユーザーのサインイン情報が既にある場合は、これを API に渡してパフォーマンスを向上させ、承認サーバーが正しいアカウント セッションを探すようにすることができます。 トークンをサイレントモードで正常に取得するために、次のいずれかを要求オブジェクトに渡すことができます。

[`login_hint`オプションの ID トークン要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims#v10-and-v20-optional-claims-set) (`ssoSilent`として`loginHint`に提供) を利用することをお勧めします。これは、サイレント (および対話型) 要求の最も信頼性の高いアカウント ヒントであるためです。

- `account` (アカウント [API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/accounts) のいずれかを使用して取得できます)
- `sid`(`idTokenClaims` オブジェクトの`account`から取得できます)
- `login_hint`(次の方法で取得できます)
    - アカウント オブジェクトの `loginHint` プロパティとして (推奨)
    - アカウント オブジェクトの `login_hint` ID トークン要求として (推奨)
    - アカウント オブジェクトの `username` プロパティとして (推奨されません)
    - アカウント オブジェクトの `upn` ID トークン要求として (推奨されません)

Note

`username`プロパティと`upn`プロパティは、実際の`login_hint`要求の代わりに部分的にサポートされていますが、推奨されません。 使用可能な場合は、 `loginHint` または `idTokenClaims.login_hint` アカウントのプロパティを使用します。

アカウントを渡すと、まず `login_hint` のオプションの ID トークン クレーム (推奨) を探し、次に `sid` のオプションの id トークン クレームを探し、最後に `loginHint` (指定されている場合) またはアカウントのユーザー名にフォールバックします。

```javascript
const account = msalInstance.getAllAccounts()[0];

const silentRequest = {
    scopes: ["User.Read", "Mail.Read"],
    loginHint: account.loginHint, // alternatively, account.idTokenClaims.login_hint
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

#### ユーザー ヒントなし

ユーザーに関する十分な情報がない場合は、`ssoSilent`、、または`account`を渡`sid`、`login_hint` API の使用を試みることができます。

```javascript
const silentRequest = {
    scopes: ["User.Read", "Mail.Read"]
};
```

ただし、アプリケーションが 1 つのブラウザー セッションで複数のユーザーのコード パスを持っている場合、またはユーザーがその 1 つのブラウザー セッションに対して複数のアカウントを持っている場合は、サイレント サインイン エラーが発生する可能性が高くなります。 承認サーバーによって複数のアカウント セッションが見つかった場合、次のエラーが表示されることがあります。

```txt
InteractionRequiredAuthError: interaction_required: AADSTS16000: Either multiple user identities are available for the current request or selected account is not supported for the scenario.
```

これは、サーバーがサインインするアカウントを判断できなかったことを示し、アカウントを選択するには、上記のパラメーター (`account`、 `login_hint`、 `sid`) または対話型サインインのいずれかが必要になります。

Warning

`ssoSilent`を使用すると、サービスは非表示の埋め込み iframe にリダイレクト URI ページを読み込もうとします。 アプリのリダイレクト URI ページ応答に存在するコンテンツ セキュリティ ポリシーと HTTP ヘッダー値 ( `X-FRAME-OPTIONS: DENY` や `X-FRAME-OPTIONS: SAMEORIGIN`など) は、アプリが iframe に読み込まれるのを防ぎ、サイレント SSO を効果的にブロックする可能性があります。 `ssoSilent`を使用する場合は、リダイレクト URI がそのようなポリシーを実装していないページを指していることを確認します。

### RedirectUri に関する考慮事項

**すべての認証フローに** 、MSAL リダイレクト ブリッジを実装する専用のリダイレクト ページが必要になりました。 これは、COOP (クロスオリジン -Opener-Policy) ヘッダーをサポートし、ポップアップ/iframe ウィンドウとメイン アプリケーション間の安全な通信を可能にするために必要です。

#### リダイレクト ページの設定

`redirectUri`は、リダイレクト ブリッジ スクリプトを読み込む専用ページを指す必要があります。 このページは次のとおりである必要があります:

1. **リダイレクト ブリッジ スクリプトを読み込む** - このスクリプトはメイン ウィンドウとの通信を処理します
2. **ブリッジ スクリプトを除く JavaScript を含まない** - リダイレクト ページでは、ブリッジ スクリプトのみを実行する必要があります
3. **ルーティング ロジックを含まない** - ハッシュ処理を妨げる可能性のあるルーター ライブラリを回避する
4. **アプリ登録に登録する** - URI は、Azure ポータルに登録されているものと正確に一致する必要があります

**リダイレクト ページの例 (Vite や Webpack などのバンドルを使用する場合):**

```html
<!DOCTYPE html>
<html>
<head>
    <title>Redirect</title>
</head>
<body>
    <p>Processing authentication...</p>
    <script type="module">
        import { broadcastResponseToMainFrame } from "@azure/msal-browser/redirect-bridge";

        broadcastResponseToMainFrame();
    </script>
</body>
</html>
```

Note

`@azure/msal-browser/redirect-bridge`指定子は、バンドル (Vite、Webpack など) によって解決する必要があります。ブラウザーが直接フェッチできる URL ではありません。 フレームワーク固有の手順については、 [リダイレクト ブリッジのセットアップ ガイド](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/redirect-bridge.md)を参照してください。

#### コンフィギュレーション

`redirectUri` は、MSAL の構成でグローバルに設定することも、リクエストごとに設定することもできます。

**グローバル構成:**

```javascript
const msalConfig = {
    auth: {
        clientId: "your-client-id",
        authority: "https://login.microsoftonline.com/common",
        redirectUri: "http://localhost:3000/redirect"
    }
};

const msalInstance = new PublicClientApplication(msalConfig);
```

**要求ごとの構成:**

```javascript
msalInstance.loginPopup({
    scopes: ["user.read"],
    redirectUri: "http://localhost:3000/redirect"
});
```

詳細と完全なサンプル実装については、次を参照してください。

- [React ルーターのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/react-router-sample)
- [高速サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/ExpressSample)

### ポップアップ `interaction_in_progress` エラーの処理

ポップアップ フローの場合は、 `overrideInteractionInProgress` フラグを使用して保留中の対話を取り消し、新しい操作を開始できます。 これは、ユーザーがポップアップを取り消したか、操作が失敗した回復シナリオに役立ちます。

Note

この機能は **ポップアップ フローでのみ使用でき** 、 **リダイレクト フローではサポートされていません**。 COOP (Cross-Origin-Opener-Policy) ヘッダーを使用すると、従来の `window.opener` 接続が切断され、ポップアップ ウィンドウが BroadcastChannel 経由でのみメイン フレームと通信できるようになります。

Important

これを `true` に設定すると、保留中のポップアップ認証要求は強制的に取り消されますが、開いているポップアップは閉じ **られません** 。

**`true`に設定する場合:**

- 別のポップアップ操作が現在進行中の場合、強制的に取り消されますが、開いているポップアップは閉じられません
- 保留中の対話が `interaction_in_progress_cancelled` エラーで拒否される
- 新しいポップアップ フローはすぐに続行されます

**有効なユース ケース:**

- ユーザーがポップアップをキャンセルしたエラーからの復旧 (認証を完了せずにポップアップが閉じられました)
- カスタム エラー復旧フローの実装
- ポップアップ操作の失敗後に "再試行" メカニズムを提供する

**既定：**`false`

#### 重要: ボタン クリック時にのみ使用する

 エラーをキャッチするときに`interaction_in_progress`。 オーバーライドは、明示的なユーザー アクション ([再試行] ボタンのクリックなど) **によってのみ** トリガーする必要があります。 インタラクションを自動的にオーバーライドすると、次の問題が発生する可能性があります。

- 複数の認証フロー間の競合状態
- 正当な認証試行の予期しないキャンセル
- 認証フローの開始と停止が予期せず行われ、ユーザー エクスペリエンスが低下する
- 正常な認証応答に至らない、開いたままのポップアップが多数ある

#### 例: ユーザーによってトリガーされた再試行による適切なエラー処理

視覚的なフィードバックを使用した完全な実装については、次を参照してください。

- [Express Sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/ExpressSample) — カスタム CSS を使用した JavaScript の実装を示します
- [React Router サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/react-router-sample) — Material-UI コンポーネントを使用した React の実装を示します

どちらのサンプルも次の例を示しています。

- ポップアップ認証中に表示される警告メッセージ
- エラーが発生したときに明確な説明を含むモーダル/ダイアログ `interaction_in_progress` 再試行する
- ユーザーによってトリガーされる再試行の適切な状態管理
- 運用対応 UI コンポーネント

```typescript
// State to track if user wants to retry
let userWantsRetry = false;

// Button click handler
async function handleLoginClick() {
    try {
        const loginRequest = {
            scopes: ["user.read"]
        };

        // If user explicitly clicked retry, override the existing interaction
        if (userWantsRetry) {
            loginRequest.overrideInteractionInProgress = true;
            userWantsRetry = false; // Reset flag
        }

        const response = await msalInstance.loginPopup(loginRequest);
        // Handle successful login
    } catch (error) {
        if (error.errorCode === 'interaction_in_progress') {
            // Show retry button to user - DO NOT automatically retry
            showRetryButton();
        } else {
            // Handle other errors
            console.error(error);
        }
    }
}

// Retry button click handler
function handleRetryClick() {
    userWantsRetry = true; // Set flag for next login attempt
    handleLoginClick(); // User explicitly requested retry
}
```

#### 例: ユーザーによってトリガーされた再試行を含む React コンポーネント

```jsx
function LoginButton() {
    const { instance } = useMsal();
    const [showRetry, setShowRetry] = useState(false);
    const [retryRequested, setRetryRequested] = useState(false);

    const handleLogin = async () => {
        try {
            const loginRequest = {
                scopes: ["user.read"],
                // Only override if user clicked the retry button
                overrideInteractionInProgress: retryRequested
            };

            setRetryRequested(false); // Reset retry flag

            const response = await instance.loginPopup(loginRequest);
            setShowRetry(false);
        } catch (error) {
            if (error.errorCode === 'interaction_in_progress') {
                // Show retry button - let user decide whether to retry
                setShowRetry(true);
            } else {
                console.error(error);
            }
        }
    };

    const handleRetry = () => {
        setRetryRequested(true); // User explicitly requested retry
        handleLogin();
    };

    return (
        <div>
            <button onClick={handleLogin}>Login</button>
            {showRetry && (
                <button onClick={handleRetry}>
                    Retry Login (Cancel Pending)
                </button>
            )}
        </div>
    );
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/logout"} -->
## ユーザーのサインアウト - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/logout
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: リダイレクトまたはポップアップを使用してローカル キャッシュと ID サーバー セッションをクリアして、MSAL.js を使用してユーザーをサインアウトする方法について説明します

ここから始める前に、[ログイン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user)、[トークンの取得、トークンの](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/acquire-token)[有効期間の管理](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/token-lifetimes)方法を理解しておく必要があります。

### ログアウト

MSAL のログアウト プロセスには 2 つの手順があります。

1. MSAL キャッシュをクリアします。
2. ID サーバー上のセッションをクリアします。

`PublicClientApplication` オブジェクトは、これらのアクションを実行する 2 つの API を公開します。

```javascript
msalInstance.logoutRedirect();
msalInstance.logoutPopup();
```

これらの API は、ユーザーおよびセッション データのトークン キャッシュをクリアし、ブラウザー ウィンドウまたはポップアップ ウィンドウをサーバーのログアウト ページに移動します。 次の条件が満たされている限り、サーバーはサインアウトするアカウントを選択し、 `postLogoutRedirectUri` にリダイレクトするようにユーザーに求めます。

1. URI は、アプリ登録の応答 URL として登録されます
2. URI は、`PublicClientApplication` 設定またはログアウト リクエストの `postLogoutRedirectUri` として指定されます
3. ユーザーが ID プロバイダーとのアクティブなセッションを持っている
4. (MSA シナリオ)アプリの登録時にフロント チャネルログアウト URL が構成されている

上記のいずれかの条件が満たされていない場合、ページ (またはポップアップ ウィンドウ) は ID プロバイダーのログアウト ページに残ります。

**大事な：** このログアウト ナビゲーションが何らかの方法で中断された場合、MSAL キャッシュはクリアされる可能性がありますが、セッションは引き続きサーバー上に保持される可能性があります。 アプリケーションに戻る前に、ナビゲーションが完全に完了していることを確認します。

```javascript
const msalConfig = {
    auth: {
        clientId: 'your_client_id',
        authority: 'https://login.microsoftonline.con/{your_tenant_id}',
        redirectUri: 'https://contoso.com',
        postLogoutRedirectUri: 'https://contoso.com/homepage'
    }
};
```

### オブジェクトを要求する

各ログアウト API に構成オプションを指定して、動作をカスタマイズできます。

- [logoutRedirect リクエスト](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionrequest)
- [logoutPopup リクエスト](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionpopuprequest)

### logoutRedirect

`logoutRedirect`を使用すると、ユーザー トークンのローカル キャッシュがクリアされ、ウィンドウがサーバーのサインアウト ページにリダイレクトされます。 `logoutRedirect`によって返される約束は解決されるとは思われませんが、リダイレクトが開始される前に他のコードの実行をブロックする必要がある場合は、それを待つことができます。

動作をカスタマイズするための[構成オプション](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionrequest)を指定できます。

```javascript
const currentAccount = msalInstance.getAccount({ homeAccountId });
await msalInstance.logoutRedirect({
    account: currentAccount,
    postLogoutRedirectUri: "https://contoso.com/loggedOut"
});
```

#### サーバーのサインアウトのスキップ

Warning

サーバーのサインアウトをスキップすると、ユーザーのセッションはサーバー上でアクティブなままになり、資格情報を再度指定せずにアプリケーションにサインインし直すことができます。

アプリケーションでローカル ログアウトのみを実行する場合は、要求の `onRedirectNavigate` パラメーターにコールバックを指定し、コールバックから false を返すことができます。

```javascript
msalInstance.logoutRedirect({
    onRedirectNavigate: (url) => {
        // Return false if you would like to stop navigation after local logout
        return false;
    }
});
```

### logoutPopup

`logoutPopup` API によって、サーバーのサインアウト ページがポップアップで開き、アプリケーションが現在の状態を維持できるようになります。 このため、ポップアップを使用してログアウトする場合は、 `logoutRedirect` に関するいくつかの追加の考慮事項があります。

- `logoutPopup` が返す Promise は、ポップアップが閉じた後に解決されることが想定されています
- `postLogoutRedirectUri` は、サインアウトが完了したときに MSAL がポップアップを閉じることができるようにするために **必要** です
- `postLogoutRedirectUri` は、メイン フレームではなくポップアップ ウィンドウで開きます。 ログアウト後に最上位レベルのアプリをリダイレクトする必要がある場合は、ログアウト要求で `mainWindowRedirectUri` パラメーターを使用できます。

動作をカスタマイズするための[構成オプション](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionpopuprequest)を指定できます。

```javascript
const currentAccount = msalInstance.getAccount({ homeAccountId });
await msalInstance.logoutPopup({
    account: currentAccount,
    postLogoutRedirectUri: "https://contoso.com/loggedOut",
    mainWindowRedirectUri: "https://contoso.com/homePage",
    popupWindowAttributes: {
        popupSize: {
            height: 100,
            width: 100
        },
        popupPosition: {
            top: 100,
            left: 100
        }
    }
});
```

### 確認なしでログアウト

クライアント アプリケーションで ID トークンに対して [login_hintオプションの要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims#v10-and-v20-optional-claims-set) が有効になっている場合は、ID トークンの `login_hint` 要求を利用して、 `logoutRedirect` または `logoutPopup`を使用しているときに"サイレント" またはプロンプトなしのログアウトを実行できます。 プロンプトなしのログアウトを実現するには、次の 2 つの方法があります。

#### オプション 1: MSAL がアカウントの ID トークン要求からlogin\_hintを自動的に解析できるようにする

最初の最も簡単なオプションは、ログアウト API へのセッションを終了するアカウント オブジェクトを指定することです。 MSAL は、アカウントの ID トークンで `login_hint` 要求が使用可能かどうかを確認し、アカウント ピッカー プロンプトをスキップする `logout_hint` として、その要求をエンド セッション要求に自動的に追加します。

```javascript
const currentAccount = msalInstance.getAccount({ homeAccountId });
// The account's ID Token must contain the login_hint optional claim to avoid the account picker
await msalInstance.logoutRedirect({ account: currentAccount});
```

#### オプション 2: ログアウト要求で logoutHint オプションを手動で設定する

または、 `logoutHint`を手動で設定する場合は、アプリで `login_hint` 要求を抽出し、ログアウト要求の `logoutHint` として設定できます。

```javascript
const currentAccount = msalInstance.getAccount({ homeAccountId });

// Extract login hint to use as logout hint
const logoutHint = currentAccount.idTokenClaims.login_hint;
await msalInstance.logoutPopup({ logoutHint: logoutHint });
```

***注: 選択した API (リダイレクト/ポップアップ) に応じて、アプリは引き続きリダイレクトまたはポップアップを開いてサーバー セッションを終了します。違いは、ユーザーがサーバーのアカウント ピッカー プロンプトを表示したり、操作したりする必要がないということです。***

### フロントチャネル ログアウト

Microsoft Entra IDおよび Azure AD B2C では[、OAuth フロント チャネル ログアウト機能](https://openid.net/specs/openid-connect-frontchannel-1_0.html)がサポートされています。これにより、ユーザーがログアウトを開始したときに、すべてのアプリケーションでシングル サインアウトが可能になります。 MSAL.jsでこの機能を利用するには、次の手順に従います。

1. アプリケーションで、専用のログアウト ページを作成します。 このページ **では** 、ページの読み込み時にトークンを取得するなど、他の機能を実行しないでください (詳細については、以下を参照してください)。 このページは非表示の iframe に読み込まれ、Microsoft Entra IDおよび MSA ユーザーの場合は、`iss`および`sid`クエリ パラメーターが含まれます。
2. Microsoft Entra 管理センター で、アプリケーションの [**認証**] ページに移動し、手順 1 のページを [**Front-channel logout URL**] に登録します。 このページは、 `https`経由で読み込む必要があることに注意してください。

#### フロント チャネル ログアウト ページの要件

フロント チャネル ログアウトに使用されるページは、次のように作成する必要があります。

1. ページの読み込み時に、MSAL `logoutRedirect` API を自動的に呼び出します。
2. `PublicClientApplication`構成で、`system.allowRedirectInIframe`を `true` に設定します。
3. `logout`を呼び出すときは、iframe のログアウト ページへのリダイレクトを禁止することをお勧めします (上記を参照)。

例：

```typescript
const msal = new PublicClientApplication({
    auth: {
        clientId: "my-client-id"
    },
    system: {
        allowRedirectInIframe: true
    }
})

// Automatically on page load
msal.logoutRedirect({
    onRedirectNavigate: () => {
        // Return false to stop navigation after local logout
        return false;
    }
});
```

ユーザーが別のアプリケーションからログアウトすると、アプリケーションのフロント チャネル ログアウト URL が非表示の iframe に読み込まれ、MSAL.js はキャッシュをクリアしてシングル サインアウトを完了します。

Note

フロント チャネル ログアウトは、ブラウザー間で常にサポートされるとは限りません。 Chromium 対応 [ストレージ パーティション分割](https://privacysandbox.google.com/cookies/storage-partitioning) と Firefox では、フロント チャネル ログアウトを実行するための [同様の標準](https://developer.mozilla.org/en-US/docs/Web/Privacy/Guides/State_Partitioning) 制限アプリケーションがサポートされています。 このトピックに関する Entra の公式ドキュメントについては、「 [サード パーティの Cookie を使用しないフロント チャネル ログアウトの制限事項](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas#limitations-on-front-channel-logout-without-third-party-cookies)」を参照してください。

#### フロントチャネル ログアウトのサンプル

次のサンプルは、MSAL.jsを使用してフロント チャネル ログアウトを実装する方法を示しています。

- MSAL Angular v2: [Angular 11 サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-v2-samples/angular11-sample-app)
- MSAL React: [React ルーターのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/react-router-sample)

### Events

アプリの異なる部分で、`logoutRedirect` または `logoutPopup` によって返される Promise に直接アクセスせずにログアウト状態の変化に反応する必要がある場合は、[イベント API](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/events.md) を使用できます。

ログアウトが成功または失敗したとき、および `logoutPopup`を使用しているときにポップアップが開かれると、イベントが生成されます。

### 重要な注意事項

- ログアウト API に渡されたアカウントがない場合、または EndSessionRequest オブジェクトがない場合は、すべてのアカウントからログアウトされます。
- アカウントがログアウト API に渡された場合、MSAL はそのアカウントに関連するトークンのみをクリアします。
- サーバーサインアウトは便利な機能であり、ベスト エフォートで行われます。 ログアウト API は、サーバーのサインアウトが成功したかどうかに関係なく、ローカル アプリケーション キャッシュが正常にクリアされている限り、正常に解決されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/mcp"} -->
## MSAL Browser で MCP フローを使用する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/mcp
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: MSAL Browser でモデル コンテキスト プロトコル (MCP) フローを有効にして、MCP アプリケーションと入れ子になったアプリ認証のリソース スコープ トークンを取得する方法について説明します。

[モデル コンテキスト プロトコル (MCP)](https://modelcontextprotocol.io/) は、AI アプリケーションが外部ツール、データ ソース、およびサービスと安全に接続できるようにするオープン標準です。 MSAL Browser では、すべてのトークン要求に `resource` パラメーターが含まれるように強制し、そのリソースによってキー指定されたアクセス トークンをキャッシュすることで、MCP フローがサポートされます。

Note

MCP フローは、 `PublicClientApplication` を使用する標準ブラウザー アプリケーションと、 `createNestablePublicClientApplication` を使用した入れ子になったアプリ認証 (NAA) アプリケーションの両方でサポートされています。

サーバー側の実装については、 [MSAL ノード MCP フローを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/mcp)参照してください。

### Prerequisites

- [Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- `@azure/msal-browser` プロジェクトに v5 以降がインストールされている

### MCP の有効化

`PublicClientApplication` の作成時に、`auth` 構成で `isMcp: true` を設定します:

```javascript
const msalConfig = {
    auth: {
        clientId: "your-client-id",
        authority: "https://login.microsoftonline.com/common",
        isMcp: true,
    },
};

const pca = new msal.PublicClientApplication(msalConfig);
```

NAA アプリケーションの場合は、 `createNestablePublicClientApplication`で同じ構成を使用します。

```javascript
const pca = await msal.createNestablePublicClientApplication(msalConfig);
```

### リソース パラメーター

`isMcp`が`true`である場合、すべてのトークン要求には**必ず**`resource` パラメーターを含める必要があります。 省略すると、`resource_parameter_required` エラーになります。

```javascript
const tokenRequest = {
    scopes: ["User.Read"],
    resource: "https://example.microsoft.com",
};
```

Important

`resource` パラメーターを要求オブジェクトに直接設定します。 `resource` プロパティと同時に、`extraQueryParameters` または `extraParameters` 経由で渡さないでください。`misplaced_resource_parameter` エラーがスローされます。

次の例は、 `resource` パラメーターを設定する正しい方法と正しくない方法を示しています。

```javascript
// Correct
const request = {
    scopes: ["User.Read"],
    resource: "https://example.microsoft.com",
};

// Wrong — resource in both locations
const request = {
    scopes: ["User.Read"],
    resource: "https://example.microsoft.com",
    extraQueryParameters: { resource: "https://example.microsoft.com" },
};
```

### リソース単位のキャッシュ

`isMcp`が有効になっている場合、アクセス トークンは関連付けられているリソースと共にキャッシュされます。 この動作は、サイレント トークンの取得に影響します。

- **キャッシュ ヒット**: 同じスコープ **と** リソースに対してキャッシュされたアクセス トークンが存在する場合は、キャッシュから返されます。
- **キャッシュ ミス**: 要求されたリソースがキャッシュされたトークンと一致しない場合、MSAL はネットワークにフォールバックして、要求されたリソースの新しいトークンを取得します。

```javascript
// First request — acquires token from network
const token1 = await pca.acquireTokenSilent({
    scopes: ["User.Read"],
    resource: "https://resource-a.microsoft.com",
    account: account,
});

// Same resource — returns cached token
const token2 = await pca.acquireTokenSilent({
    scopes: ["User.Read"],
    resource: "https://resource-a.microsoft.com",
    account: account,
});

// Different resource — falls back to network
const token3 = await pca.acquireTokenSilent({
    scopes: ["User.Read"],
    resource: "https://resource-b.microsoft.com",
    account: account,
});
```

### エラー処理

2 つのエラーは、MCP フローに固有です。

| エラー コード | 説明 |
| --- | --- |
| `resource_parameter_required` | `isMcp` は `true` ですが、要求に `resource` パラメーターは含まれません。 |
| `misplaced_resource_parameter` | `resource` プロパティと `resource` または `extraQueryParameters` の両方で`extraParameters`が見つかりました。 1 つだけを使用します。 |

両方のエラーは `ClientAuthError` としてスローされます。 詳細については、 [エラーのドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/errors)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/migrate-adal-js-to-msal-js"} -->
## JavaScript アプリケーションを ADAL.js から MSAL.js に移行する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/migrate-adal-js-to-msal-js
- Service: msal / msal-js
- Article date: 2024-05-20
- Summary: Active Directory認証ライブラリ (ADAL) ではなく、認証と承認に Microsoft Authentication Library (MSAL) を使用するように既存の JavaScript アプリケーションを更新する方法。

[JavaScript 用 Microsoft Authentication Library](https://github.com/AzureAD/microsoft-authentication-library-for-js) (MSAL.js, `msal-browser`) 2.x は、Microsoft ID プラットフォーム上の JavaScript アプリケーションで使用することをお勧めする認証ライブラリです。 この記事では、ADAL.js を使用しているアプリを MSAL.js 2.x に移行するために必要な変更について説明します。

Note

MSAL.js 1.x ではなく MSAL.js 2.x を強くお勧めします。 認証コードの付与フローがより安全になり、サードパーティーの Cookie をブロックするために Safari などのブラウザーに実装されているプライバシー対策に関係なく、シングルページ アプリケーションが良好なユーザー エクスペリエンスを維持できるなどの利点があります。

### Prerequisites

- アプリ登録ポータルで **Platform** / **Reply URL Type** を **シングルページ アプリケーション** に設定する必要があります ( **Web** など、アプリ登録に他のプラットフォームが追加されている場合は、リダイレクト URI が重複しないようにする必要があります。参照: [リダイレクト URI の制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url))
- あなたのアプリをInternet Explorerで実行するには、MSAL.js が依存する ES6 機能（例えば、Promise）に対するポリフィルを提供する必要があります。

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

### MSAL を初期化する

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

Note

v2.0 で `https://login.microsoftonline.com/common` 機関を使用する場合、ユーザーは任意のMicrosoft Entra組織または個人の Microsoft アカウント (MSA) でサインインできるようになります。 MSAL.jsでは、ログインを任意のMicrosoft Entra アカウント (ADAL.jsと同じ動作) に制限する場合は、代わりに `https://login.microsoftonline.com/organizations` を使用します。

### MSAL を構成する

[AuthenticationContext](https://github.com/AzureAD/azure-activedirectory-library-for-js/wiki/Config-authentication-context) の初期化時に使用される [ADAL.jsの構成オプション](https://github.com/AzureAD/azure-activedirectory-library-for-js/wiki/Config-authentication-context#authenticationcontext)の一部は、MSAL.jsでは非推奨ですが、いくつかの新しいオプションが導入されています。 [使用可能なオプションの完全な一覧を](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md)参照してください。 重要なのは、 `clientId`を除くこれらのオプションの多くは、トークンの取得中にオーバーライドできるため、 *要求ごとに* 設定できます。 たとえば、トークンの取得時に初期化時に設定した機関 URI とは異なる **機関 URI** または **リダイレクト URI を** 使用できます。

さらに、構成オプションでログイン エクスペリエンス (ポップアップ ウィンドウを使用するか、ページをリダイレクトするかなど) を指定する必要もなくなりました。 代わりに、`MSAL.js`は、`loginPopup` インスタンスを介して`loginRedirect`メソッドと`PublicClientApplication` メソッドを公開します。

### ログ記録を有効にする

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
| `login` | N/a | Deprecated. `loginPopup`または`loginRedirect`を使用する |
| `logOut` | N/a | Deprecated. `logoutPopup`または`logoutRedirect`を使用する |
| N/a | `loginPopup` |  |
| N/a | `loginRedirect` |  |
| N/a | `logoutPopup` |  |
| N/a | `logoutRedirect` |  |
| N/a | `getAccountByHomeId` | ホーム ID (oid + テナント ID) を使用してアカウントをフィルター処理します |
| N/a | `getAccountLocalId` | ローカル ID を使用してアカウントをフィルター処理します (ADFS に役立ちます) |
| N/a | `getAccountUsername` | ユーザー名を使用してアカウントをフィルター処理します (存在する場合) |

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

ADAL.jsと同様に、MSAL.js は [、Web Storage API](https://developer.mozilla.org/docs/Web/API/Web_Storage_API) を使用して、トークンやその他の認証成果物をブラウザー ストレージにキャッシュします。 `sessionStorage` オプション (構成を参照) を使用することをお勧めします。ユーザーが取得したトークンを格納する方が安全ですが、`localStorage`ではタブとユーザー セッション間で[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/single-sign-on)が提供されるためです。

重要な点は、キャッシュに直接アクセスすることは想定されていないということです。 その代わり、適切な MSAL.js の API を使用して、アクセス トークンやユーザー アカウントなどの認証成果物を取得する必要があります。

### 更新トークンを使用してトークンを更新する

ADAL.js は、セキュリティ上の理由から更新トークンを返さない [OAuth 2.0 暗黙的フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform//v2-oauth2-implicit-grant-flow)を使用します (更新トークンの有効期間はアクセス トークンよりも長いため、悪意のあるアクターの手では危険です)。 そのため、ADAL.js の場合、ユーザーが何度も認証を求められないように、トークンの更新に非表示のフレームが使用されています。 ADAL.js または MSAL.js v1.0 を使用している場合は、暗黙的な許可フローよりも安全な PKCE を使用して承認コード フローを利用するために、MSAL.js v2.0 以降への移行を検討してください。

PKCE をサポートする認証コード フローの場合、MSAL.js 2.x を使用するアプリは、ID トークンとアクセス トークンと共に更新トークンを受け取ります。更新にこれを使用することができます。 更新トークンの使用方法は抽象化されており、開発者がそれに関するロジックを構築することは想定されていません。 その代わり、更新トークンを使用したトークンの更新は、MSAL によって自動的に管理されます。 ADAL.js を使用した以前のトークン キャッシュは MSAL.js に転送できません。これは、トークン キャッシュのスキーマが変更され、ADAL.js で使用されているスキーマとは互換性がないためです。

### エラーと例外を処理する

MSAL.jsを使用する場合、発生する可能性がある最も一般的なエラーの種類は、 `interaction_in_progress` エラーです。 このエラーは、ある対話型 API (`loginPopup`、`loginRedirect`、`acquireTokenPopup`、`acquireTokenRedirect`) が呼び出されたときに、別の対話型 API がまだ進行中だった場合に発生します。 `login*` API と `acquireToken*` API は*非同期*であるため、別の約束を呼び出す前に、結果として得られる約束が解決されていることを確認する必要があります。

もう 1 つの一般的なエラーは `interaction_required`です。 多くの場合、このエラーは対話型トークン取得のプロンプトを開始するだけで解決されます。 たとえば、アクセスしようとしている Web API に [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーが設定されている場合、ユーザーは [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) (MFA) を実行する必要があります。 その場合、`interaction_required`または`acquireTokenPopup`をトリガーして`acquireTokenRedirect`エラーを処理すると、ユーザーに MFA の入力を求められます。これにより、ユーザーはそのエラーをフルフィルできるようになります。

さらに、発生する可能性があるもう 1 つの一般的なエラーは `consent_required`です。これは、保護されたリソースのアクセス トークンを取得するために必要なアクセス許可がユーザーによって同意されていない場合に発生します。 `interaction_required`と同様に、`consent_required` エラーの解決策は、多くの場合、`acquireTokenPopup`または`acquireTokenRedirect`を使用して、対話型のトークン取得プロンプトを開始します。

詳細については、以下を参照してください。 [一般的な MSAL.js エラーとその処理方法](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/errors.md)

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

次のスニペットは、Microsoft ID プラットフォームを使用してユーザーを認証するシングルページ アプリケーションに必要な最小限のコードを示しています。このスニペットでは、最初に ADAL.js を使用して Microsoft Graph のアクセストークンを取得し、その後に MSAL.js を使用します。

| ADAL.js の使用 | MSAL.js の使用 |
| --- | --- |
| ```html<br><br><head><br>    <meta charset="UTF-8"><br>    <meta http-equiv="X-UA-Compatible" content="IE=edge"><br>    <meta name="viewport" content="width=device-width, initial-scale=1.0"><br>    <script type="text/javascript" src="https://alcdn.msauth.net/lib/1.0.18/js/adal.min.js"></script><br></head><br><br><body><br>    <div><br>        <p id="welcomeMessage" style="visibility: hidden;"></p><br>        <button id="loginButton">Login</button><br>        <button id="logoutButton" style="visibility: hidden;">Logout</button><br>        <button id="tokenButton" style="visibility: hidden;">Get Token</button><br>    </div><br>    <script><br>        // DOM elements to work with<br>        var welcomeMessage = document.getElementById("welcomeMessage");<br>        var loginButton = document.getElementById("loginButton");<br>        var logoutButton = document.getElementById("logoutButton");<br>        var tokenButton = document.getElementById("tokenButton");<br><br>        // if user is logged in, update the UI<br>        function updateUI(user) {<br>            if (!user) {<br>                return;<br>            }<br><br>            welcomeMessage.innerHTML = 'Hello ' + user.profile.upn + '!';<br>            welcomeMessage.style.visibility = "visible";<br>            logoutButton.style.visibility = "visible";<br>            tokenButton.style.visibility = "visible";<br>            loginButton.style.visibility = "hidden";<br>        };<br><br>        // attach logger configuration to window<br>        window.Logging = {<br>            piiLoggingEnabled: false,<br>            level: 3,<br>            log: function (message) {<br>                console.log(message);<br>            }<br>        };<br><br>        // ADAL configuration<br>        var adalConfig = {<br>            instance: 'https://login.microsoftonline.com/',<br>            clientId: "ENTER_CLIENT_ID_HERE",<br>            tenant: "ENTER_TENANT_ID_HERE",<br>            redirectUri: "ENTER_REDIRECT_URI_HERE",<br>            cacheLocation: "sessionStorage",<br>            popUp: true,<br>            callback: function (errorDesc, token, error, tokenType) {<br>                if (error) {<br>                    console.log(error, errorDesc);<br>                } else {<br>                    updateUI(authContext.getCachedUser());<br>                }<br>            }<br>        };<br><br>        // instantiate ADAL client object<br>        var authContext = new AuthenticationContext(adalConfig);<br><br>        // handle redirect response or check for cached user<br>        if (authContext.isCallback(window.location.hash)) {<br>            authContext.handleWindowCallback();<br>        } else {<br>            updateUI(authContext.getCachedUser());<br>        }<br><br>        // attach event handlers to button clicks<br>        loginButton.addEventListener('click', function () {<br>            authContext.login();<br>        });<br><br>        logoutButton.addEventListener('click', function () {<br>            authContext.logOut();<br>        });<br><br>        tokenButton.addEventListener('click', () => {<br>            authContext.acquireToken(<br>                "https://graph.microsoft.com",<br>                function (errorDesc, token, error) {<br>                    if (error) {<br>                        console.log(error, errorDesc);<br><br>                        authContext.acquireTokenPopup(<br>                            "https://graph.microsoft.com",<br>                            null, // extraQueryParameters<br>                            null, // claims<br>                            function (errorDesc, token, error) {<br>                                if (error) {<br>                                    console.log(error, errorDesc);<br>                                } else {<br>                                    console.log(token);<br>                                }<br>                            }<br>                        );<br>                    } else {<br>                        console.log(token);<br>                    }<br>                }<br>            );<br>        });<br>    </script><br></body><br><br></html><br><br>``` | ```html<br><br><head><br>    <meta charset="UTF-8"><br>    <meta http-equiv="X-UA-Compatible" content="IE=edge"><br>    <meta name="viewport" content="width=device-width, initial-scale=1.0"><br>    <script type="text/javascript" src="https://alcdn.msauth.net/browser/2.34.0/js/msal-browser.min.js"></script><br></head><br><br><body><br>    <div><br>        <p id="welcomeMessage" style="visibility: hidden;"></p><br>        <button id="loginButton">Login</button><br>        <button id="logoutButton" style="visibility: hidden;">Logout</button><br>        <button id="tokenButton" style="visibility: hidden;">Get Token</button><br>    </div><br>    <script><br>        // DOM elements to work with<br>        const welcomeMessage = document.getElementById("welcomeMessage");<br>        const loginButton = document.getElementById("loginButton");<br>        const logoutButton = document.getElementById("logoutButton");<br>        const tokenButton = document.getElementById("tokenButton");<br><br>        // if user is logged in, update the UI<br>        const updateUI = (account) => {<br>            if (!account) {<br>                return;<br>            }<br><br>            welcomeMessage.innerHTML = `Hello ${account.username}!`;<br>            welcomeMessage.style.visibility = "visible";<br>            logoutButton.style.visibility = "visible";<br>            tokenButton.style.visibility = "visible";<br>            loginButton.style.visibility = "hidden";<br>        };<br><br>        // MSAL configuration<br>        const msalConfig = {<br>            auth: {<br>                clientId: "ENTER_CLIENT_ID_HERE",<br>                authority: "https://login.microsoftonline.com/ENTER_TENANT_ID_HERE",<br>                redirectUri: "ENTER_REDIRECT_URI_HERE",<br>            },<br>            cache: {<br>                cacheLocation: "sessionStorage"<br>            },<br>            system: {<br>                loggerOptions: {<br>                    loggerCallback(loglevel, message, containsPii) {<br>                        console.log(message);<br>                    },<br>                    piiLoggingEnabled: false,<br>                    logLevel: msal.LogLevel.Verbose,<br>                }<br>            }<br>        };<br><br>        // instantiate MSAL client object<br>        const pca = new msal.PublicClientApplication(msalConfig);<br><br>        // handle redirect response or check for cached user<br>        pca.handleRedirectPromise().then((response) => {<br>            if (response) {<br>                pca.setActiveAccount(response.account);<br>                updateUI(response.account);<br>            } else {<br>                const account = pca.getAllAccounts()[0];<br>                updateUI(account);<br>            }<br>        }).catch((error) => {<br>            console.log(error);<br>        });<br><br>        // attach event handlers to button clicks<br>        loginButton.addEventListener('click', () => {<br>            pca.loginPopup().then((response) => {<br>                pca.setActiveAccount(response.account);<br>                updateUI(response.account);<br>            })<br>        });<br><br>        logoutButton.addEventListener('click', () => {<br>            pca.logoutPopup().then((response) => {<br>                window.location.reload();<br>            });<br>        });<br><br>        tokenButton.addEventListener('click', () => {<br>            const account = pca.getActiveAccount();<br><br>            pca.acquireTokenSilent({<br>                account: account,<br>                scopes: ["User.Read"]<br>            }).then((response) => {<br>                console.log(response);<br>            }).catch((error) => {<br>                if (error instanceof msal.InteractionRequiredAuthError) {<br>                    pca.acquireTokenPopup({<br>                        scopes: ["User.Read"]<br>                    }).then((response) => {<br>                        console.log(response);<br>                    });<br>                }<br><br>                console.log(error);<br>            });<br>        });<br>    </script><br></body><br><br></html><br><br>``` |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/migrate-spa-implicit-to-auth-code"} -->
## JavaScript シングルページ アプリを暗黙的な許可から承認コード フローに移行する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/migrate-spa-implicit-to-auth-code
- Service: msal / msal-js
- Article date: 2024-05-22
- Summary: MSAL.js 1.x を使用して JavaScript SPA を更新し、暗黙的な許可フローを MSAL.js 2.x に更新する方法と、PKCE と CORS をサポートする承認コード フロー。

JavaScript 用 Microsoft Authentication Library (MSAL.js) v2.0 では、PKCE および CORS を使用した承認コード フローが、Microsoft ID プラットフォーム上のシングルページ アプリケーションに対してサポートされます。 暗黙的な許可を使用して MSAL.js 1.x アプリケーションを MSAL.js 2.0 以降 (以降は *2.x*) に移行し、PKCE を使用した承認コード フローを移行するには、以下のセクションの手順に従います。

MSAL.js 2.x では、暗黙的な許可フローではなく、ブラウザーで承認コード フローをサポートすることで、MSAL.js 1.x が向上します。 MSAL.js 2.x では、暗黙的フローはサポート **されません** 。

### 移行の手順

アプリケーションを MSAL.js 2.x と認証コード フローに更新するには、次の 3 つの主要な手順があります。

1. アプリ登録リダイレクト URI を **Web** プラットフォームから**シングルページ アプリケーション** プラットフォームに切り替えます。
2. コードを MSAL.js 1.x から **2.x** に更新します。
3. 登録を共有するすべてのアプリケーションが PKCE を使用して MSAL.js 2.x および認証コード フローに更新されている場合は、アプリの登録で 暗黙的な許可 を無効にします。

以降のセクションでは、各手順についてさらに詳しく説明します。

### リダイレクト URI を SPA プラットフォームに切り替える

Tip

この記事の手順は、開始元のポータルによって若干異なる場合があります。

アプリケーションの既存のアプリ登録を引き続き使用する場合は、Microsoft Entra 管理センターを使用して、登録のリダイレクト URI を SPA プラットフォームに更新します。 これにより、登録を使用するアプリに対する PKCE と CORS のサポートによる承認コード フローが可能になります (それでも、v2.x を MSAL.js するようにアプリケーションのコードを更新する必要があります)。

**Web** プラットフォーム リダイレクト URI で現在構成されているアプリの登録については、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Identity**&gt;**Applications**&gt;アプリの登録 に移動**し、アプリケーション**を選択して、[**認証**] を選択します。
3. [**リダイレクト URI] の** [**Web** プラットフォーム] タイルで、URI を移行する必要があることを示す警告バナーを選択します。
4. アプリケーションで 2.x MSAL.js 使用するリダイレクト URI *のみを* 選択し、[ **構成**] を選択します。

これらのリダイレクト URI が **シングルページ アプリケーション** プラットフォーム タイルに表示され、承認コード フローでの CORS のサポートと、これらの URI に対する PKCE が有効であることが示されます。

既存の登録のリダイレクト URI を更新する代わりに、 [新しいアプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-registration) 登録を作成することもできます。

### コードを MSAL.js 2.x に更新する

MSAL 1.x では、次のように UserAgentApplication を初期化してアプリケーション インスタンスを作成しました。

```javascript
// MSAL 1.x
import * as msal from "msal";

const msalInstance = new msal.UserAgentApplication(config);
```

MSAL 2.x では、代わりに [PublicClientApplication][msal-js-publicclientapplication]: を初期化します。

```javascript
// MSAL 2.x
import * as msal from "@azure/msal-browser";

const msalInstance = new msal.PublicClientApplication(config);
```

MSAL 2.x をアプリケーションに追加する手順については、「[チュートリアル: ユーザーをサインインさせ、認証コード フローを使用して JavaScript シングルページ アプリ (SPA) から Microsoft Graph APIを呼び出す」](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-javascript-auth-code)を参照してください。

コードに加える必要があるその他の変更については、GitHubの[移行ガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v1-migration)を参照してください。

### 暗黙的な許可設定を無効にする

このアプリ登録とそのクライアント ID を使用するすべての運用アプリケーションを MSAL 2.x と承認コード フローに更新したら、アプリ登録の **[認証** ] メニューの下にある暗黙的な許可設定をオフにする必要があります。

アプリの登録で暗黙的な許可設定をオフにすると、登録とそのクライアント ID を使用するすべてのアプリケーションに対して暗黙的フローが無効になります。

すべてのアプリケーションを MSAL.js 2.x と [PublicClientApplication][msal-js-publicclientapplication] に更新する前に、暗黙的な許可フローを無効に**しないでください**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/mip-logging"} -->
## MSAL.js でのエラーと例外のログ記録 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/mip-logging
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: MSAL.js でエラーと例外をログに記録する方法について説明します

この記事では、さまざまな種類のエラーの概要と、一般的なサインイン エラーを処理するための推奨事項について説明します。

### MSAL エラー処理の基本

Microsoft Authentication Library (MSAL) の例外は、エンド ユーザーに表示されるのではなく、アプリ開発者がトラブルシューティングを行うために使用されます。 例外メッセージはローカライズされません。

例外とエラーを処理する場合は、例外の種類自体とエラー コードを使用して例外を区別できます。 エラー コードの一覧については、[認証と承認のエラー コードMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)参照してください。

サインイン エクスペリエンス中に、同意、条件付きアクセス (MFA、デバイス管理、場所ベースの制限)、トークンの発行と利用、およびユーザー プロパティに関するエラーが発生する場合があります。

次のセクションでは、アプリのエラー処理の詳細について説明します。

### MSAL.js でログ記録を構成する

`PublicClientApplication` インスタンスを作成するための構成中に loggerOptions オブジェクトを渡して、MSAL.js (JavaScript) のログ記録を有効にします。 必要な構成パラメーターは、アプリケーションのクライアント ID のみです。 それ以外はすべて省略可能ですが、テナントとアプリケーション モデルによっては必要になる場合があります。

loggerOptions オブジェクトには、次のプロパティがあります。

- `loggerCallback`: カスタムの方法で MSAL ステートメントのログ記録を処理するために開発者が提供できるコールバック関数。 ログのリダイレクト方法に応じて、 `loggerCallback` 関数を実装します。 loggerCallback 関数の形式は次のとおりです。 ` (level: LogLevel, message: string, containsPii: boolean): void`
    - サポートされているログ レベルは、 `Error`、 `Warning`、 `Info`、および `Verbose`です。 既定値は `Info` です。
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

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/mip-pass-custom-state"} -->
## 認証要求でカスタム状態を渡す (MSAL.js) - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/mip-pass-custom-state
- Service: msal / msal-js
- Article date: 2026-01-28
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用して、認証要求でカスタム状態パラメーター値を渡す方法について説明します。

OAuth 2.0 で定義されている *状態* パラメーターは認証要求に含まれており、クロスサイト要求フォージェリ攻撃を防ぐためにトークン応答にも返されます。 既定では、JavaScript 用 Microsoft Authentication Library (MSAL.js) は、ランダムに生成された一意の *状態* 認証要求のパラメーター値を渡します。

状態パラメーターを使用して、リダイレクト前にアプリの状態の情報をエンコードすることもできます。 このパラメーターへの入力として、アプリ内のユーザーの状態への参照 (ページまたはビューの識別子など) を渡すことができます。 MSAL.js ライブラリを使用すると、[Request](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#redirectrequest) オブジェクトの状態パラメーターとしてカスタム状態を渡すことができます。

Important

セキュリティとプライバシーのために、状態パラメーターに URL やその他の機密データを直接配置しないでください。 代わりに、localStorage や sessionStorage などのブラウザー ストレージに格納されているデータに対応するキーまたは識別子を使用します。 この方法により、アプリは認証後に必要なデータを安全に参照できます。 たとえば、実際の URL を sessionStorage に格納し、状態パラメーターにストレージ キーのみを渡すことができます。

例えば次が挙げられます。

```javascript
import {PublicClientApplication} from "@azure/msal-browser";

const myMsalObj = new PublicClientApplication({
    clientId: "ENTER_CLIENT_ID_HERE"
});

let loginRequest = {
    scopes: ["user.read"],
    state: "state_key"
}

myMSALObj.loginRedirect(loginRequest);
```

渡された状態は、要求の送信時に MSAL.js によって設定された一意の GUID に追加されます。 応答が返されると、MSAL.js 状態の一致を確認し、[Response](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#authenticationresult) オブジェクトで渡されたカスタムを `state`として返します。

詳細については、MSAL.jsを使用 [したシングルページ アプリケーション (SPA) の構築](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-overview) に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/navigation"} -->
## ウィンドウ ナビゲーションのインターセプトまたはオーバーライド - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/navigation
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: ウィンドウ ナビゲーションをインターセプトまたはオーバーライドする方法について説明します

既定では、 `msal-browser` は `window.location.assign` と `window.location.replace` を使用して、外部リダイレクトから戻った後にアプリケーションを外部 URL (サインインページやサインアウト ページ、内部 URL など) やアプリ内の他のページにリダイレクトします。 ただし、既定の動作を独自の動作でオーバーライドしたい場合もあります。 たとえば、React や Angular などのフレームワークでは、SPA 全体を再読み込みすることなく、クライアント側のナビゲーションを処理するさまざまな API が提供されます。 `msal-browser` では、独自のカスタム実装を提供するために、 `INavigationClient` インターフェイスと `NavigationClient` の既定の実装の両方が公開されます。

### 独自の実装を作成する

インターフェイスには、次の 2 つのメソッドが含まれています。

- `navigateInternal` - アプリ内のページ間をリダイレクトするときに呼び出されます 。たとえば、 `redirectUri` からログインを開始したページにリダイレクトする場合などです。
- `navigateExternal` - Microsoft Entra のサインイン プロンプトなど、アプリ外部の URL にリダイレクトするときに呼び出されます

`INavigationClient`を実装することで、両方のカスタム実装を提供することを選択できます。

```javascript
class CustomNavigationClient implements INavigationClient {
    async navigateInternal(url, options) {
        // Your custom logic
    }

    async navigateExternal(url, options) {
        // Your custom logic
    }
}
```

または、1 つのメソッドをオーバーライドするだけで済む場合は、既定の `NavigationClient`を拡張できます。

```javascript
class CustomNavigationClient extends NavigationClient {
    async navigateInternal(url, options) {
        // Your custom logic
    }

    // navigateExternal will use the default
}
```

**メモ：**`navigateInternal`の独自の実装を提供する場合は、認証フローが壊れる可能性がある別のドメインに移動しないでください。 これは、同じドメイン上のページへのナビゲーションにのみ使用することを目的としています。

#### 関数パラメーター

- `url`: MSAL.js が遷移先として指定する URL。 これは絶対 URL になります。 相対 URL が必要な場合は、これを解析する必要があります。
- `options`: 次のような便利なナビゲーションに関する追加情報が含まれます。ナビゲーションを呼び出そうとしている関数の ApiId、推奨されるタイムアウト値、およびこのナビゲーションをブラウザー履歴に追加する必要があるかどうか。 これらの値を使用する必要はありませんが、便宜上提供されます。

#### 戻り値

どちらの関数も非同期であり、boolean 値に解決される Promise `true`/`false` を返す必要があります。 ほとんどの場合、 `true`を返す必要があります。

次の場合に `true` を返します。

- この関数により、ページが別のページに完全にリダイレクトされます 。たとえば、Microsoft Entraサインイン ページに移動したり、ウィンドウ オブジェクトを再割り当てしたりします。
- 関数によって、直接または間接的に `PublicClientApplication` が再初期化されるか、 `handleRedirectPromise` が再実行されます

次の場合 `false` 返します。

- この関数では、URL を抽出して別のウィンドウに移動する場合など、ページがリダイレクトまたは再読み込みされることはありません
- この関数は、クライアント側ナビゲーションを呼び出して、 `PublicClientApplication` を再初期化しないページの一部を再レンダリングするか、 `handleRedirectPromise` を再度呼び出します。

### `PublicClientApplication` へのカスタム実装を提供する

カスタム クラスを記述したら、 `PublicClientApplication`に渡す構成でインスタンスを指定します。

```javascript
const navigationClient = new CustomNavigationClient();

const config: Configuration = {
    auth: {
        clientId: "your-client-id"
    },
    system: {
        navigationClient: navigationClient
    }
};

const msalInstance = new PublicClientApplication(config);
```

React や Angular のように、初期化後にカスタム クラスを指定する必要がある場合があります。 これを行うには、 `setNavigationClient` API を呼び出します。

```javascript
const config: Configuration = {
    auth: {
        clientId: "your-client-id"
    }
};

const msalInstance = new PublicClientApplication(config);
const navigationClient = new CustomNavigationClient();
msalInstance.setNavigationClient(navigationClient);
```

### 例示

カスタム `NavigationClient` を提供するエンド ツー エンドの例を確認したい場合は、 [React サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples)を確認してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/performance"} -->
## MSAL.js でのパフォーマンスの測定 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/performance
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: MSAL.js アプリケーションでのトークン取得の認証パフォーマンスとテレメトリを測定および監視する方法について説明します

MSAL.js の認証フローのパフォーマンスを測定するアプリケーションは、手動で行ったり、ライブラリ自体によって実行されるパフォーマンス測定を使用したりできます。 パフォーマンス測定を使用するには、 [テレメトリ構成オプション](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration#telemetry-config-options) でパフォーマンス クライアントを設定し、パフォーマンス コールバックを追加する必要があります。

#### テレメトリ パフォーマンス クライアントを設定する

```javascript
import { PublicClientApplication, BrowserPerformanceClient } from "@azure/msal-browser";

const msalConfig = {
    auth: {
        ...
    },
    cache: {
        ...
    },
    system: {
        ...
    }
}

const msalInstance = new PublicClientApplication({
    ...msalConfig,
    telemetry: {
        client: new BrowserPerformanceClient(msalConfig)
    }
});
msalInstance.initialize();
```

#### パフォーマンス コールバックを追加する

アプリケーションは、ライブラリによって取得されたパフォーマンス測定を受信するコールバックを登録できます。 これらの測定には、最上位レベルの API のエンド ツー エンドの測定値と、重要な内部 API の測定値が含まれます。

**MSFT ファースト パーティ アプリケーションの場合の注意**: このテレメトリをキャプチャするために既にインストルメント化されている `@azure/msal-browser` の内部ビルドを公開します。 詳細については、お問い合わせください。

##### 例

```typescript
const msalInstance = new PublicClientApplication(config);

msalInstance.addPerformanceCallback((events: PerformanceEvent[]) => {
    events.forEach(event => {
        console.log(event);
    });
});
```

イベントの例:

```typescript
const event: PerformanceEvent = {
    correlationId: "aaaa0000-bb11-2222-33cc-444444dddddd",durationMs: 1873,endPageVisibility: "hidden",fromCache: false,name: "acquireTokenSilent",startPageVisibility: "visible",startTimeMs: 1636414041888,success: true,
    silentCacheClientAcquireTokenDurationMs: 0,
    silentRefreshClientAcquireTokenDurationMs: 150,
    silentIframeClientAcquireTokenDurationMs: 0
    cryptoOptsGetPublicKeyThumbprintDurationMs: 200,
    cryptoOptsSignJwtDurationMs: 8,
    clientId: "00001111-aaaa-2222-bbbb-3333cccc4444",
    authority: "https://login.microsoftonline.com/common",
    libraryName: "@azure/msal-browser-1p",
    libraryVersion: "5.0.0",
    appName: "my-application",
    appVersion: "1.0.0"
}
```

`PerformanceEvents`オブジェクトの詳細については、[こちらをご覧ください](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-common/src/telemetry/performance/PerformanceEvent.ts)。 注目すべきプロパティの一覧を次に示します。

| **Property** | タイプ | 説明 |
| --- | --- | --- |
| `name` | `string` | 通常、操作の名前は、最上位レベルの API 名 ( `acquireTokenSilent`、 `acquireTokenByCode`、 `ssoSilent`など) と一致します。 |
| `durationMs` | `number` | 操作のエンド ツー エンドの期間 (ミリ秒単位)。 |
| `success` | `boolean` | 操作が成功したかどうか。 |
| `fromCache` | `boolean` | 操作がキャッシュから結果を取得したかどうか。 |
| `correlationId` | `string` | 操作に使用される関連付け ID (要求ごとに一意であることが望ましい)。 |
| `libraryVersion` | `string` | 操作に使用 MSAL.js のバージョン。 |
| `authority` | `string` | 操作に使用される権限。 |
| `<internalFunctionName>DurationMs` | `number` | 内部操作の時間 (ミリ秒単位)。 |

#### removePerformanceCallback

`addPerformanceCallback` API はコールバック ID を返します。コールバック ID は、アプリケーションが`PublicClientApplication.removePerformanceCallback`に渡して、パフォーマンス イベントの受信からそのコールバックの登録を解除できます。 コールバックが正常に削除されたかどうかを示すブール値が返されます。

##### 例

```typescript
const msalInstance = new PublicClientApplication(config);

const callbackId: string = msalInstance.addPerformanceCallback((events: PerformanceEvent[]) => {
    events.forEach(event => {
        console.log(event);
    });
});

const removed: boolean = msalInstance.removePerformanceCallback(callbackId);
```

#### ブラウザーのパフォーマンスの測定

ブラウザーのパフォーマンス測定は、パフォーマンスのオーバーヘッドが大きいため、既定では無効になっています。 ブラウザーのパフォーマンス タイムラインに報告されるパフォーマンス測定を有効にするアプリケーションでは、次のことが必要です。

1. ブラウザー開発者ツールを開く
    - Edge、Chrome、Firefox のブラウザー: F12 キーを押します
    - Safari: Safari の環境設定 (`Safari Menu`&gt;`Preferences`) に移動し、 `Advanced Tab` を選択して `Show features for web developers`を有効にします。 そのメニューが有効になると、開発者コンソールが表示されます。 `Develop`&gt;`Show Javascript Console`
2. `Session Storage`に移動します。
    - [Edge](https://learn.microsoft.com/ja-jp/microsoft-edge/devtools-guide-chromium/storage/sessionstorage)
    - [クロム](https://developer.chrome.com/docs/devtools/storage/sessionstorage)
    - [Firefox](https://firefox-source-docs.mozilla.org/devtools-user/storage_inspector/local_storage_session_storage)
    - Safariで`Storage`タブに移動し、`Session Storage`を展開します
3. ターゲット ドメインの選択
4. `msal.browser.performance.enabled`キーを`Session Storage`に追加し、その値を `1` に設定し、ページを更新して、ブラウザーのパフォーマンス タイムラインを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/prompt-behavior"} -->
## MSAL.js を使用したプロンプト動作 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/prompt-behavior
- Service: msal / msal-js
- Article date: 2019-04-24
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用してプロンプトの動作をカスタマイズする方法について説明します。

MSAL.js では、ログインまたはトークン要求メソッドの一部としてプロンプト値を渡すことができます。 アプリケーションのシナリオに基づいて、要求**オブジェクト**で prompt パラメーターを設定することで、要求の[Microsoft Entraプロンプト](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#commonauthorizationurlrequest)動作をカスタマイズできます。

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

次のプロンプト値は、Microsoft ID プラットフォームで認証するときに使用できます。

| パラメーター | Behavior |
| --- | --- |
| `login` | ユーザーがその要求に対して資格情報を入力するように強制し、シングル サインオンを否定します。 |
| `none` | ユーザーに対話型プロンプトが表示されないようにします。 シングル サインオンを使用してサイレント モードで要求を完了できない場合、Microsoft ID プラットフォームは*login\_required*または*interaction\_requiredエラーを*返します。 |
| `consent` | ユーザーがサインインした後に OAuth 同意ダイアログをトリガーし、ユーザーにアプリへのアクセス許可の付与を求めます。 |
| `select_account` | セッション内のすべてのアカウントを一覧表示するアカウント選択エクスペリエンスまたは別のアカウントを完全に選択するオプションを提供することで、シングル サインオンを中断します。 |
| `create` | 外部ユーザーがアカウントを作成できるようにするサインアップ ダイアログをトリガーします。 詳細については、「[セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)」を参照してください。 |

MSAL.js は、サポートされていないプロンプト値に対して `invalid_prompt` エラーをスローします。

```console
invalid_prompt_value: Supported prompt values are 'login', 'select_account', 'consent', 'create' and 'none'. Please see here for valid configuration options: https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#commonauthorizationurlrequest Given value: my_custom_prompt
```

### 既定のプロンプト値

次に、MSAL.js が使用する既定のプロンプト値を示します。

| MSAL.js メソッド | 既定のプロンプト | 許可されるプロンプト |
| --- | --- | --- |
| `loginPopup` | N/a | Any |
| `loginRedirect` | N/a | Any |
| `ssoSilent` | `none` | N/A (無視) |
| `acquireTokenPopup` | N/a | Any |
| `acquireTokenRedirect` | N/a | Any |
| `acquireTokenSilent` | `none` | N/A (無視) |

Note

**プロンプト**はプロトコル レベルのパラメーターであり、必要な認証動作を ID プロバイダーに通知します。 MSAL.js 動作には影響せず、MSAL.js はサービスが最終的に要求を処理する方法を制御できません。 ほとんどの状況では、Microsoft Entra IDは要求を尊重しようとします。 これが不可能な場合は、エラー応答を返すか、指定されたプロンプト値を完全に無視する可能性があります。

### prompt=none を使用する対話型リクエスト

通常、サイレント要求を行う必要がある場合は、サイレント MSAL.js メソッド (`ssoSilent`、`acquireTokenSilent`) を使用し、対話型メソッド (`loginPopup`、`acquireTokenRedirect`) を使用してlogin\_requiredまたはinteraction\_requiredエラーを処理します。

ただし、プロンプト値 `none` を対話型の MSAL.js メソッドと共に使用してサイレント認証を実現できる場合もあります。 たとえば、一部のブラウザーではサードパーティの Cookie 制限があるため、Microsoft Entra IDを使用したアクティブなユーザー セッションにもかかわらず、`ssoSilent`要求は失敗します。 解決策として、`none`などの対話型要求に`loginPopup`プロンプト値を渡すことができます。 MSAL.js はその後、Microsoft Entra ID へのポップアップ ウィンドウを開き、Microsoft Entra ID は既存のセッション Cookie を利用してプロンプト値に従います。 この場合、ユーザーには簡単なポップアップ ウィンドウが表示されますが、資格情報の入力を求めるメッセージは表示されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/redirect-bridge"} -->
## MSAL Browser でリダイレクト ブリッジ ページを設定する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/redirect-bridge
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: Angular、Vite、Webpack、Next.js、Express など、さまざまなフレームワークにわたって MSAL Browser v5 のリダイレクト ブリッジ ページを設定する方法について説明します。

このガイドでは、MSAL Browser v5 で導入されたリダイレクト ブリッジ ページを設定するためのフレームワーク固有の手順について説明します。 リダイレクト ブリッジが必要な理由の背景については、 [v4 から v5 への移行ガイドを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration#cross-origin-opener-policy-coop-support)参照してください。

Warning

リダイレクト ブリッジ ページは、ヘッダーと共に提供`Cross-Origin-Opener-Policy`。 ブリッジ ページは、IdP が OAuth フローを完了した後に認証応答を受信する中継局です。 ブリッジ ページに COOP ヘッダーが設定されている場合、ブラウザーは、通信チャネルをメイン アプリケーションに切り替える参照コンテキスト グループスワップを実行します。ブリッジが解決するように設計されている正確な問題を再導入します。

Important

新しいリダイレクト ブリッジ ページを指すように`redirectUri`を更新した後、**Entra ID アプリ**登録のリダイレクト URI も更新[する必要があります](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#add-a-redirect-uri)。 URI は、パス、プロトコル、ポートなど、 **正確に** 一致する必要があります。 アプリの登録を更新しないと、 `redirect_uri_mismatch` エラーが発生します。

### Angular

1. **リダイレクト ブリッジ コンポーネント (**`src/app/redirect/redirect.component.ts`) を作成します。

```typescript
import { Component, OnInit } from "@angular/core";
import { broadcastResponseToMainFrame } from "@azure/msal-browser/redirect-bridge";

@Component({
    selector: "app-redirect",
    standalone: true,
    template: "<p>Processing authentication...</p>",
})
export class RedirectComponent implements OnInit {
    ngOnInit(): void {
        broadcastResponseToMainFrame().catch((error: Error) => {
            console.error("Error broadcasting response to main frame:", error);
        });
    }
}
```

1. ルーティング構成**に`/redirect`ルートを追加**します。 リダイレクト ルートは`MsalGuard`にある必要があり、リダイレクト ページは、`MsalInterceptor`をトリガーする API 呼び出しを行わない (または MSAL API を呼び出す) ようにする必要があります。

```typescript
import { RedirectComponent } from "./redirect/redirect.component";

const routes: Routes = [
    { path: "redirect", component: RedirectComponent },
    // ... your other routes
];
```

1. **ビルドにコンポーネントが含まれていることを確認します。** Angular ルート コンポーネントを使用する場合、 `angular.json` アセットの変更は必要ありません。Angular CLI によってコンポーネントが自動的にバンドルされます。 ルーティング コンポーネントではなく静的 `redirect.html` を使用する場合は、それを assets 配列に追加します。

```jsonc
// angular.json
{
    "projects": {
        "your-app": {
            "architect": {
                "build": {
                    "options": {
                        "assets": [
                            { "glob": "**/*", "input": "public" },
                            "src/redirect.html" // ← Add redirect bridge page
                        ]
                    }
                }
            }
        }
    }
}
```

>
> **サンプル：**[angular-standalone-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-standalone-sample) と [angular-modules-sample を](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-modules-sample)参照してください。

### Vite

Vite では、ビルド出力に別のエントリ ポイントとして `redirect.html` が含まれるように、マルチページ構成が必要です。

1. **`redirect.html` を作成します**（`index.html` の隣のプロジェクト ルート内）:

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Redirect</title>
</head>
<body>
    <p>Processing authentication...</p>
    <script type="module">
        import { broadcastResponseToMainFrame } from "@azure/msal-browser/redirect-bridge";

        broadcastResponseToMainFrame().catch((error) => {
            console.error("Error broadcasting response:", error);
        });
    </script>
</body>
</html>
```

1. **`vite.config.ts`を更新**して、2番目のエントリとしてリダイレクトページを追加します:

```typescript
import { defineConfig } from "vite";
import { resolve } from "path";

export default defineConfig({
    build: {
        rollupOptions: {
            input: {
                main: resolve(__dirname, "index.html"),
                redirect: resolve(__dirname, "redirect.html"), // ← Redirect bridge entry
            },
        },
    },
});
```

開発中 (`vite dev`)、リダイレクト ページは自動的に `/redirect.html`で提供されます。 実稼働ビルドでは、Rollup によって出力ディレクトリに `index.html` と `redirect.html` の両方が出力されます。

>
> **サンプル：**[react-router-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/react-router-sample)、[typescript-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/typescript-sample)、および [b2c-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/b2c-sample) を参照してください。

### Webpack

Webpack には、専用のエントリ ポイントと、リダイレクト ページの `HtmlWebpackPlugin` インスタンスが必要です。

1. **作成`src/redirect.html`**:

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Redirect</title>
</head>
<body>
    <p>Processing authentication...</p>
    <!-- The redirect script bundle will be injected by HtmlWebpackPlugin (see redirect.js entry). -->
</body>
</html>
```

1. **作成 `src/redirect.js`**（Webpack のエントリポイント）:

```javascript
import { broadcastResponseToMainFrame } from "@azure/msal-browser/redirect-bridge";

broadcastResponseToMainFrame().catch((error) => {
    console.error("Error broadcasting response:", error);
});
```

1. **更新`webpack.config.js`**:

```javascript
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
    entry: {
        main: "./src/index.js",
        redirect: "./src/redirect.js", // ← Redirect bridge entry
    },
    plugins: [
        new HtmlWebpackPlugin({
            filename: "index.html",
            template: "./src/index.html",
            chunks: ["main"],
        }),
        new HtmlWebpackPlugin({
            filename: "redirect.html",
            template: "./src/redirect.html",
            chunks: ["redirect"], // ← Only include the redirect chunk
        }),
    ],
};
```

### Next.js

Next.js ページは自動的にルートになるため、リダイレクト ブリッジはページ コンポーネントです。 **セットアップは、ページ ルーター**と**アプリ ルーター**の間で異なります。

#### Pages Router (`pages/`)

1. **作成 `pages/redirect.js`**:

```jsx
import { useEffect } from "react";
import { broadcastResponseToMainFrame } from "@azure/msal-browser/redirect-bridge";

export default function Redirect() {
    useEffect(() => {
        broadcastResponseToMainFrame().catch((error) => {
            console.error("Error broadcasting response to main frame:", error);
        });
    }, []);

    return <p>Processing authentication...</p>;
}
```

1. **`MsalProvider`でリダイレクトページを除外する**`_app.js`:

```jsx
// pages/_app.js
import { useRouter } from "next/router";
import { MsalProvider } from "@azure/msal-react";

function MyApp({ Component, pageProps }) {
    const router = useRouter();

    // The redirect page must NOT be wrapped in MsalProvider
    if (router.pathname === "/redirect") {
        return <Component {...pageProps} />;
    }

    return (
        <MsalProvider instance={msalInstance}>
            <Component {...pageProps} />
        </MsalProvider>
    );
}
```

#### アプリ ルーター (`app/`)

1. **`app/redirect/page.js`の作成**— クライアント コンポーネント (`"use client"`) である必要があります。

```jsx
"use client";

import { useEffect } from "react";
import { broadcastResponseToMainFrame } from "@azure/msal-browser/redirect-bridge";

export default function Redirect() {
    useEffect(() => {
        broadcastResponseToMainFrame().catch((error) => {
            console.error("Error broadcasting response to main frame:", error);
        });
    }, []);

    return <p>Processing authentication...</p>;
}
```

1. **ルート レイアウトで `MsalProvider` からリダイレクト ルートを除外します。**`app/layout.js` が子要素を `MsalProvider` で囲む場合は、そのラップ処理をスキップするリダイレクトルート用に別のレイアウトを作成してください。

```jsx
// app/redirect/layout.js — no MsalProvider wrapper
export default function RedirectLayout({ children }) {
    return <>{children}</>;
}
```

これにより、実行前に MSAL が認証応答ハッシュを処理できなくなります。

いずれのルーターにも `next.config.js` 変更は必要ありません。Next.js はページを自動的に処理します。

>
> **サンプル：** ページ ルーターの例については、 [nextjs-sample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-react-samples/nextjs-sample) を参照してください。

### Express.js/Node.js バックエンド

Express.js (または静的ファイルを提供する Node.js バックエンド) を使用する場合は、COOP ヘッダー **なしで** リダイレクト ページを提供するようにサーバーを構成します。

```javascript
const express = require("express");
const path = require("path");
const app = express();

// Serve the redirect bridge page WITHOUT COOP headers
app.get("/redirect", (req, res) => {
    res.sendFile(path.join(__dirname, "public", "redirect.html"));
});

// Set COOP headers for all other routes
app.use((req, res, next) => {
    res.setHeader("Cross-Origin-Opener-Policy", "same-origin");
    next();
});

app.use(express.static(path.join(__dirname, "public")));
```

>
> **サンプル：**[HybridSample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/HybridSample) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/request-response-object"} -->
## 要求オブジェクトと応答オブジェクト - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/request-response-object
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: 認証フローのカスタマイズに使用できる構成オプションについて説明します

MSAL Browser ライブラリには、認証フローの動作をカスタマイズするために使用できる一連の構成オプションがあります。 これらのオプションの一部は [、 `PublicClientApplication` オブジェクトのコンストラクター](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration)で設定でき、そのほとんどは要求ごとに設定できます。 次の表では、login API と acquireToken API に渡すことができる構成オブジェクトと、応答を表す返されるオブジェクトについて詳しく説明します。

| API | Request オブジェクト | Response オブジェクト |
| --- | --- | --- |
| `acquireTokenPopup` | [PopupRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#popuprequest) | [AuthenticationResult](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#authenticationresult) |
| `acquireTokenRedirect` | [RedirectRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#redirectrequest) | [AuthenticationResult](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#authenticationresult) ( `handleRedirectPromise` 経由) |
| `acquireTokenSilent` | [SilentRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#silentrequest) | [AuthenticationResult](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#authenticationresult) |
| `loginPopup` | [PopupRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#popuprequest) | [AuthenticationResult](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#authenticationresult) |
| `loginRedirect` | [RedirectRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#redirectrequest) | [AuthenticationResult](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#authenticationresult) ( `handleRedirectPromise` 経由) |
| `logoutRedirect` | [EndSessionRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionrequest) | `void` |
| `logoutPopup` | [EndSessionPopupRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#endsessionpopuprequest) | `void` |
| `ssoSilent` | [SsoSilentRequest](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#ssosilentrequest) | [AuthenticationResult](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#authenticationresult) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/resources-and-scopes"} -->
## リソースとスコープ - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/resources-and-scopes
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: リソースへのアクセスとトークン要求に含まれるスコープについて説明します

Microsoft ID プラットフォームでは、*スコープ中心の*モデルを使用してリソースにアクセスします。 ここでは、*リソース*とは、**アクセス トークン**の受信者 ([MS Graph API](https://learn.microsoft.com/ja-jp/graph/overview)や独自の Web API など) を指し、*スコープ* ("アクセス許可" *とも呼ばれる*) は**、アクセス トークン**が権限を付与するリソースの任意の側面を指します。

**アクセストークン** の要求は、**MSAL.js** では *リソースごと、スコープごと* になるように設計されています。 これは、スコープ`scp1`でリソース**A**に対して要求された**アクセス トークン**が存在することを意味します。

- は、スコープ を使用してリソース `scp2` にアクセスするために使用できません。
- は、どのスコープのリソース **B** にもアクセスするために使用できません。

**アクセス トークン**の目的の受信者は、`aud`要求によって表されます。`aud`要求の値がリソース [APP ID URI](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-app-registration) と一致しない場合、トークンは無効と見なす必要があります。 同様に、 **アクセス トークン** が付与するアクセス許可は、 `scp` 要求によって表されます。 詳細については、「 [アクセス トークン要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens#payload-claims) 」を参照してください。

### 既定のスコープ

既定では、MSAL.js はすべての要求に `openid`、 `profile` 、および `offline_access` スコープを追加します。 これらのスコープは、更新トークンと、アカウント オブジェクトにユーザーの情報を設定するために使用される ID トークン要求を受け取るために必要です。

### 複数のリソースの操作

複数のリソースにアクセスする必要がある場合は、それぞれに対して個別のトークン要求を開始します。

```javascript
// "User.Read" stands as shorthand for "graph.microsoft.com/User.Read"
const graphToken = await msalInstance.acquireTokenSilent({
     scopes: [ "User.Read" ]
});
const customApiToken = await msalInstance.acquireTokenSilent({
     scopes: [ "api://<myCustomApiClientId>/My.Scope" ]
});
```

同じリソースに対して複数のスコープ (MS Graph APIの、`User.Read`、`User.Write``Calendar.Read`要求**できること**に注意してください。

```javascript
const graphToken = await msalInstance.acquireTokenSilent({
     scopes: [ "User.Read", "User.Write", "Calendar.Read" ] // all MS Graph API scopes
});
```

トークン要求で複数のリソース *を誤って* 渡した場合、受け取るトークンは最初のリソースに対してのみ発行されます。

```javascript
// you will only receive a token for MS GRAPH API's "User.Read" scope here
const myToken = await msalInstance.acquireTokenSilent({
     scopes: [ "User.Read", "api://<myCustomApiClientId>/My.Scope" ]
});
```

### 動的スコープと段階的な同意

**Microsoft Entra ID**では、アプリケーション登録に直接設定されたスコープ (*アクセス許可*) は**静的スコープ**と呼ばれます。 コード内でのみ定義されている他のスコープは、 **動的スコープ**と呼ばれます。 これは、**MSAL.jsのログイン** (*loginPopup*、*loginRedirect*) および **acquireToken** (つまり *acquireTokenPopup*、*acquireTokenRedirect*、*acquireTokenSilent*) メソッドに影響**します。 ** 以下を検討してください。

```javascript
 const loginRequest = {
      scopes: [ "openid", "profile", "User.Read" ]
 };
 const tokenRequest = {
      scopes: [ "Mail.Read" ]
 };
 // will return an ID Token and an Access Token with scopes: "openid", "profile" and "User.Read"
 msalInstance.loginPopup(loginRequest);
 // will fail and fallback to an interactive method prompting a consent screen
 // after consent, the received token will be issued for "openid", "profile" ,"User.Read" and "Mail.Read" combined
 msalInstance.acquireTokenSilent(tokenRequest);
```

上記のコード スニペットでは、ユーザーが認証し、スコープがされた **ID トークン**と`User.Read`を受け取ると、同意を求められます。 後で、の`User.Read`を要求した場合、再び同意を求められるわけではありません (つまり、*トークンをサイレントで*取得できます)。

一方、ユーザーは認証段階で`Mail.Read`に同意しなかったため、スコープの`Mail.Read`を要求するときに同意を求められます。 受信したトークンには、(その特定のリソースに対して) 以前に同意したすべてのスコープが含まれるため、 *増分同意*という用語が含まれます。

少し異なるケースを考えてみましょう。

```javascript
 const loginRequest = {
      scopes: [ "openid", "profile", "User.Read" ],
      extraScopesToConsent: [ "api://<myCustomApiClientId>/My.Scope"]
 };
 const tokenRequest = {
      scopes: [ "Mail.Read" ]
 };
 const anotherTokenRequest = {
      scopes: [ "api://<myCustomApiClientId>/My.Scope" ]
 }
 // will return an ID Token and an Access Token with scopes: "openid", "profile" and "User.Read"
 msalInstance.loginPopup(loginRequest);
 // will fail with InteractionRequiredError due to lack of consent for "Mail.Read" scope. You should fallback to an interactive method in this case.
 msalInstance.acquireTokenSilent(tokenRequest);
 // will succeed and return an Access Token with scope "api://<myCustomApiClientId>/My.Scope"
 msalInstance.acquireTokenSilent(anotherTokenRequest);
```

上記のコード スニペットでは、ユーザーが `User.Read` スコープと `api://<myCustomApiClientId>/My.Scope` スコープの両方に同意した場合でも、**リソースごとのスコープ**の原則に従って MS **Graph API**の*アクセス トークン*のみを受け取ります。 ただし、 `api://<myCustomApiClientId>/My.Scope`に既に同意しているため、後でそのリソース/スコープの **アクセス トークン** を *サイレント モード* で取得できます。

### 同意の有効期間

Microsoft Entra IDでは、同意はアプリケーションの有効期間を超えて存続します。 つまり、リソース **のアクセス トークン** を要求すると、その時点で要求されたスコープに関係なく、そのリソースに対して以前に同意したすべてのスコープが返されます。 つまり、今日`User.Read`と`Mail.Read`に同意し、明日アプリケーションの新しいインスタンスを実行して、**アクセストークン**を`User.Read`についてのみ要求した場合でも、**両方の**`User.Read`と`Mail.Read`に対して発行されたトークンを引き続き受け取ることになります。 詳細については、「 [アクセス許可と同意」](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-permissions-and-consent#scopes-and-permissions)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/shr-client-claims"} -->
## カスタム署名付き HTTP 要求要求 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/shr-client-claims
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: MSAL.js で所有証明トークンを使用して、署名済み HTTP 要求 (SHR) にカスタム クライアント要求を追加する方法について説明します

アクセス トークンの Proof-of-Possesion の機能強化として、MSAL Browser はカスタム クライアント要求を `Signed HTTP Request` ( `PoP Token`とも呼ばれます) に挿入する方法を提供します。 これらのカスタム SHR 要求は、 `POP` 認証スキームを使用する任意のトークン要求に追加できます。

MSAL Browser では、最初の`Signed HTTP Request` 要求と競合する可能性があるカスタム要求名を許可するのではなく、のペイロードに特定の形式があることを考えると、カスタム クライアント要求を文字列として渡すことができます。この要求は、`JWT`という要求の値として署名済み HTTP 要求`client_claims`に追加されます。 通常、この文字列はカスタム要求を含むシリアル化されたオブジェクトですが、MSAL は文字列を解析または検証しません。

MSAL が `Signed HTTP Requests` キャッシュしない (アクセス トークン シークレットがキャッシュされ、 `acquireTokenSilent` が呼び出されるたびに SHR ペイロードがビルドされ、Just-In-Time に署名される) 場合、カスタム クライアント要求もキャッシュ **されません** 。 つまり、結果の`acquireTokenSilent`に要求を追加するには、すべての`SignedHTTPRequest`呼び出しにも要求を渡す必要があります。

`Signed HTTP Request`が`PoP Token`としてリソース サーバーに送信されると、リソース サーバーは、署名されたペイロードの検証と、`client_claims`の抽出と解析を行います。

### 使用方法

SHR のカスタム クライアント要求は、 `shrClaims`として任意のトークン要求オブジェクトに渡すことができます。 カスタム SHR 要求はアクセス トークンの所有証明スキームの拡張機能であるため、トークン要求の `authenticationScheme` が `POP`に設定されている場合にのみ使用されることに注意してください。

#### トークン リダイレクト要求の取得の例

```javascript
const popTokenRequest = {
    scopes: ["User.Read"],
    authenticationScheme: msal.AuthenticationScheme.POP,
    resourceRequestMethod: "POST",
    resourceRequestUri: "YOUR_RESOURCE_ENDPOINT",
    shrClaims: "{\"nonce\": \"AQAA123456\",\"local_nonce\": \"AQAA7890\"}"
}
```

要求が構成され、 `POP` が `authenticationScheme`として設定されたら、 `acquireTokenRedirect` MSAL Browser API に送信できます。

```javascript
const response = await myMSALObj.acquireTokenRedirect(popTokenRequest);
```

応答には、`accessToken`を含む `Signed HTTP Request` というプロパティが含まれます。 公開キーを使用して検証すると、SHR の JWT ペイロードは次のようになります。

```javascript
{
    at: ...,
    ts: ...,
    m: "POST",
    u: "YOUR_RESOURCE_ENDPOINT",
    nonce: ...,
    p: ...,
    q: ...,
    client_claims: "{\"nonce\": \"AQAA123456\",\"local_nonce\": \"AQAA7890\"}"
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/shr-server-nonce"} -->
## SHR サーバー ナンス - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/shr-server-nonce
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: SHR Server Nonce について学習する

アクセス トークンの Proof-of-Possesion の機能強化として、MSAL Browser は、サーバー生成の署名付きタイムスタンプ ( **サーバー nonce**) を `Signed HTTP Request` ( `PoP Token`とも呼ばれます) に挿入する方法を提供します。 このサーバーで生成された nonce は、 `POP` 認証スキームを使用する任意のトークン要求に追加できます。

[MSAL がキャッシュしない](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/access-token-proof-of-possession.md#bound-access-token)場合`Signed HTTP Requests`サーバーの nonces もキャッシュ**されません**。 つまり、結果の`acquireTokenSilent` オブジェクトにサーバー nonce を追加するには、すべての`SignedHttpRequest`呼び出しにサーバー nonce を渡す必要があります。

`Signed HTTP Request`が`PoP Token`としてリソース サーバーに送信されると、リソース サーバーは、署名されたペイロードの検証と、`shrNonce`の抽出と検証を行います。

### 使用方法

取得すると、SPR のサーバー生成 nonces を任意のトークン要求オブジェクトに`shrNonce`として渡すことができます。 SHR サーバー nonce はアクセス トークンの所有証明スキームの拡張機能であるため、トークン要求の `authenticationScheme` が `POP`に設定されている場合にのみ使用されることに注意してください。

#### トークン取得リクエストの例

```javascript
const popTokenRequest = {
    scopes: ["User.Read"],
    authenticationScheme: msal.AuthenticationScheme.POP,
    resourceRequestMethod: "POST",
    resourceRequestUri: "YOUR_RESOURCE_ENDPOINT",
    shrNonce: "eyJhbGciOiJIUzI1NiIsImtpZCI6IktJRCIsInR5cCI6IkpXVCJ9.eyJ0cyI6IjE2MjU2NzI1MjkifQ.rA5ho63Lbdwo8eqZ_gUtQxY3HaseL0InIVwdgf7L_fc" // Sample Base64URL encoded server nonce value
}
```

要求が構成され、 `POP` が `authenticationScheme`として設定されたら、任意の loginXXX または acquireTokenXXX API に渡すことができます。

```javascript
const response = await myMSALObj.acquireTokenSilent(popTokenRequest);
```

応答には、`accessToken`を含む `Signed HTTP Request` というプロパティが含まれます。 公開キーを使用して検証すると、SHR の JWT ペイロードは次のようになります。

```javascript
{
    at: ...,
    ts: ...,
    m: "POST",
    u: "YOUR_RESOURCE_ENDPOINT",
    nonce: "eyJhbGciOiJIUzI1NiIsImtpZCI6IktJRCIsInR5cCI6IkpXVCJ9.eyJ0cyI6IjE2MjU2NzI1MjkifQ.rA5ho63Lbdwo8eqZ_gUtQxY3HaseL0InIVwdgf7L_fc",
    p: ...,
    q: ...,
    client_claims: "{\"nonce\": \"AQAA123456\",\"local_nonce\": \"AQAA7890\"}"
}
```

### SignedHttpRequest Nonce 属性

PoP トークン要求内で構成できる`shrNonce`値は、`nonce`で返される`SignedHttpRequest`の`AuthenticationResult`属性に割り当てられます。 ただし、SHR 内の `nonce` 属性は、 `shrNonce` 値を使用して手動で構成されていない場合は空になりません。 次の一覧では、`nonce`で`SignedHttpRequest`値が設定されるロジック (優先順位順) について説明します。

1. 認証要求の `shrNonce` が `null` または `undefined`されていない場合、MSAL はその値を SHR の `nonce` プロパティに割り当てます
2. `shrNonce`が`null`または`undefined`の場合、MSAL はランダムな GUID 文字列を生成し、SHR の `nonce` プロパティに割り当てます。

### サーバーナンスの取得

クライアント アプリケーションが最初にサーバー生成 nonce を取得して更新する方法は、クライアント アプリケーションが対話しているリソース サーバーによって異なる場合があります。 MSAL Browser が最適化されているサーバー nonce の取得と更新のフローの概要を次に示します。 nonce 取得フローが異なる場合でも、サーバー nonce は、以下の 「使用法 」セクションの説明に従って、クライアント アプリケーションによるトークン要求に常に手動で追加されます。

#### 初期ナンスの取得

1. サーバーによって生成された nonce を取得する最初の手順は、リソースに対して承認された要求を行います。 この承認された要求では、 `PoP Token` が `Authorization` ヘッダーに追加されますが、 `PoP Token` には有効な nonce は含まれません。

```javascript

let shrNonce = null; // Globally scoped variable

// 1. Configure PoP Token Request without a valid SHR Nonce
const popTokenRequest = {
    scopes: ["User.Read"],
    authenticationScheme: msal.AuthenticationScheme.POP,
    resourceRequestMethod: "POST",
    resourceRequestUri: "YOUR_RESOURCE_ENDPOINT"
    shrNonce: shrNonce // SHR Nonce is invalid as null string at this point
};

 // Get PoP token to make authenticated request
const shr = await publicClientApplication.acquireTokenSilent(popTokenRequest);

// Set up PoP Resource request
const reqHeaders = new Headers();
const authorizationHeader = `PoP ${shr}`;

headers.append("Authorization", authorizationHeader);

const options = {
    method: method,
    headers: headers
};
```

1. 要求が設定され、 `SHR` が Authorization ヘッダーに追加されると、要求がリソースに `POST`されます。 この時点で、 `PoP Token`/`SHR`に有効なサーバー nonce がない場合、リソースは `401 Unauthorized` HTTP エラーで応答する必要があります。このエラーには、最初の有効なサーバー nonce を含む `WWW-Authenticate` ヘッダーがチャレンジの 1 つとして含まれます。

```typescript
// Make call to resource with SHR
return fetch(resourceEndpointData.endpoint, options)
    .then(response => response.json())
    .then(response => {
        // At this point, the response will be a 401 Error, so ignore the success case for now
    })
    .catch(error => {
        // This error will be a `401 Unauthorized` error, containing a WWW-Authenticate header with an error message such as "nonce_missing" or "nonce_malformed"
        // The correct way to handle this scenario is shown in the following step.
});
```

1. MSAL は、上記の `WWW-Authenticate` ヘッダーからサーバー nonce を抽出するために、 `AuthenticationHeaderParser` クラスを公開します。これには、 `getShrNonce` API が含まれています。このクラスには、サーバー nonce が入っている認証ヘッダーからサーバー nonce を解析します。

```typescript
import { PublicClientApplication, AuthenticationHeaderParser } from "@azure/msal-browser";

...

// Make call to resource with SHR
return fetch(resourceEndpointData.endpoint, options)
    .then(response => response.json())
    .then(response => {
        if (response.status === 200 && response.headers.get("Authentication-Info")) {
            // At this point, the response will be a 401 Error, so ignore the success case for now
        }
        // Check if error is 401 unauthorized and WWW-Authenticate header is included
        else if (response.status === 401 && response.headers.get("WWW-Authenticate")) {
            lastResponseHeaders = response.headers;
            const authHeaderParser = new AuthenticationHeaderParser(response.headers);
            shrNonce = authHeaderParser.getShrNonce(); // Null is replaced with valid nonce from WWW-Authenticate header
        } else {
            // Deal with other errors as necessary
        }
    }); 
});
```

#### 有効なナンスの使用と更新

1. `shrNonce`が初めて取得されたので、有効な nonce を含め、`PoP Token`を再度要求でき、承認されたリソース要求を正常に完了できます。 `200 OK`成功した応答には、`nextnonce` チャレンジを持つ `Authentication-Info` ヘッダーが含まれるようになりました。これは、`WWW-Authenticate` ナンス と同じ方法で MSAL によって解析できます。

```typescript
import { PublicClientApplication, AuthenticationHeaderParser } from "@azure/msal-browser";

// 1. Configure PoP Token Request without a valid SHR Nonce
const popTokenRequest = {
    scopes: ["User.Read"],
    authenticationScheme: msal.AuthenticationScheme.POP,
    resourceRequestMethod: "POST",
    resourceRequestUri: "YOUR_RESOURCE_ENDPOINT"
    shrNonce: shrNonce // SHR Nonce is now a valid server-generated nonce
};

 // Get PoP token to make authenticated request
const shr = await publicClientApplication.acquireTokenSilent(popTokenRequest);

// Set up PoP Resource request
const reqHeaders = new Headers();
const authorizationHeader = `PoP ${shr}`;

headers.append("Authorization", authorizationHeader);

const options = {
    method: method,
    headers: headers
};

// Make call to resource with SHR
return fetch(resourceEndpointData.endpoint, options)
    .then(response => response.json())
    .then(response => {
         if (response.status === 200 && response.headers.get("Authentication-Info")) {
             /** NEW **/
            // 200 OK if nonce was valid
            lastResponseHeaders = response.headers;
            const authHeaderParser = new AuthenticationHeaderParser(response.headers);
            shrNonce = authHeaderParser.getShrNonce(); // Previous nonce (possibly expired) is replaced with the nextnonce generated by the server
        }
        // Check if error is 401 unauthorized and WWW-Authenticate header is included
        else if (response.status === 401 && response.headers.get("WWW-Authenticate")) {
           /** SAME AS BEFORE **/
            lastResponseHeaders = response.headers;
            const authHeaderParser = new AuthenticationHeaderParser(response.headers);
            shrNonce = authHeaderParser.getShrNonce(); // Null is replaced with valid nonce from WWW-Authenticate header
        } else {
            // Deal with other errors as necessary
        }
    });
});
```

#### 統合型ナンス取得サイクル

次のスクリプトは、サーバー nonce を継続的に取得および更新する必要がある `PoP Token` 要求を処理するための推奨される方法を提案しています。

```typescript
/**
 * Application script
 */

import { PublicClientApplication, AuthenticationHeaderParser } from "@azure/msal-browser";
const publicClientApplication = new PublicClientApplication(msalConfig);

// Initialize header map to keep track of the "last" response's headers.
let lastResponseHeaders: HttpHeaders = null;
// Call the PoP API endpoint 
const { responseBody, lastResponseHeaders } = await callPopResource(publicClientApplication, resourceEndpointData, lastResponseHeaders);

/**
 * End Application script
 */

/**
 * Source Code: 
 * This method is responsible for getting data from a PoP-protected API. It is called at the bottom of the 
 * demo code in the application script.
 */
const async callPopResource(
    publicClientApplication: PublicClientApplication,
    resourceEndpointData: ResourceEndpointData,
    lastResponseHeaders: HttpHeaders): ResponseBody {
    
    // Get headers from last response's headers
    const headerParser = new AuthenticationHeaderParser(lastResponseHeaders);
    let shrNonce: string | null;
    
    try {
        shrNonce = headerParser.getShrNonce(); // Will return 
    } catch (e) {
        // If the lastResponse headers are null, .getShrNonce will throw (by design)
        shrNonce = null;
    }

    // Build PoP request as usual, adding the server nonce
    const popTokenRequest = {
        account: CURRENT_ACCOUNT,
        scopes: resourceEndpointData.POP_RESOURCE_SCOPES,
        authenticationScheme: AuthenticationScheme.POP,
        resourceRequestUri: resourceEndpointData.RESOURCE_URI,
        resourceRequestMethod: resourceEndpointData.METHOD,
        shrClaims: resourceEndpointData.CUSTOM_CLAIMS_STRING,
        shrNonce: shrNonce || undefined // Will be undefined on the first call, shrNonce should be valid on subsequent calls
    }

    // Get pop token to make authenticated request
    const shr = await publicClientApplication.acquireTokenSilent(popTokenRequest);

    // PoP Resource request
    const reqHeaders = new Headers();
    const authorizationHeader = `PoP ${shr}`; //Create Authorization header

    headers.append("Authorization", authorizationHeader); // Add Authorization header to request headers

    const options = {
        method: method,
        headers: headers
    };

    // Make call to resource with SHR
    return fetch(resourceEndpointData.endpoint, options)
        .then(response => response.json())
        .then(response => {
            if (response.status === 200 && response.headers.get("Authentication-Info")) {
                lastResponseHeaders = response.headers;
                const authHeaderParser = new AuthenticationHeaderParser(response.headers);
                shrNonce = authHeaderParser.getShrNonce(); // Previous nonce (possibly expired) is replaced with the nextnonce generated by the server
            }
            // Check if error is 401 unauthorized and WWW-Authenticate header is included
            else if (response.status === 401 && response.headers.get("WWW-Authenticate")) {
            /** SAME AS BEFORE **/
                lastResponseHeaders = response.headers;
                const authHeaderParser = new AuthenticationHeaderParser(response.headers);
                shrNonce = authHeaderParser.getShrNonce(); // Null is replaced with valid nonce from WWW-Authenticate header
            } else {
                // Deal with other errors as necessary
            }
        });
    });
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/signed-http-request"} -->
## 署名付きHTTPリクエスト - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/signed-http-request
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: 署名された Http 要求を使用する方法について説明します

MSAL.js は、MSAL.jsから帯域外で取得されるペイロード (トークンなど`SignedHttpRequest`作成するのに役立つ便利な クラスを提供します。 これにより、アプリケーションは、独自のペイロードに対して [アクセス トークンの所有証明](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/access-token-proof-of-possession) に使用するのと同じ暗号化とキャッシュ MSAL.js を利用できます。

注: `SignedHttpRequest` クラスは、組み込みのアクセス トークン所有証明機能を使用できない高度なユース ケースを対象としています。 これらのユース ケースの例には、MSAL.jsから帯域外で取得されたMicrosoft Entra IDまたは MSA アクセス トークン、およびワークロード アクセス トークンが含まれています。

### Requirements

`SignedHttpRequest` API を使用するには、アプリケーションで次の手順を実行する必要があります。

1. ペイロードを発行するサーバーに返された公開キーの拇印を転送します。そのサーバーは、署名されたペイロード内に拇印を埋め込む必要があります。 これは通常、署名された JWT です。
2. ペイロードと元の公開キーの拇印を MSAL に返します。
3. SHR が意図されているリソース サーバーは、SHR と内部ペイロードをデコードして検証する方法を理解する必要があります。

#### Microsoft Entra パラメーター

Microsoft Entra IDまたは MSA によって発行されたトークンの場合、アプリケーションはトークン要求に追加のクエリ パラメーターを含める必要があります。

- `req_cnf` : 公開キーの拇印を含む Base64 でエンコードされた文字列。
- `token_type`: 所有証明フローであることを示す `pop` に設定します。

### Sample

#### アプリケーションの使用状況

##### api.ts

```typescript
import { SignedHttpRequest } from "@azure/msal-browser";

const resourceRequestUri = "https://api.contoso.com/my-api";
const resourceRequestMethod = "GET";

// Instantiate the token binding class. There may be configuration options possible in the future.
const signedHttpRequest = new SignedHttpRequest({
    resourceRequestUri, 
    resourceRequestMethod
});

// Use MSAL to generate and cache the keys, and return the public key thumbprint to the app.
const publicKeyThumbprint = await signedHttpRequest.generatePublicKeyThumbprint();

// Application acquires the payload to the be signed, providing the public key.
// This payload can be cached by the application, if desired.
const payload = await fetchAccessTokenWithoutMsal(publicKeyThumbprint);

// Application invokes custom business logic to acquire nonce (optional)
const nonce = await fetchServerNonce();

// Use MSAL to generate the pop token for the payload.
// This popToken should be used immediately and not be cached by the application.
const popToken = await signedHttpRequest.signRequest(payload, publicKeyThumbprint, { nonce });

// Initiate http request using pop token
const headers = new Headers();
headers.append("Authorization", `PoP ${popToken}`);

const options = {
    method: resourceRequestMethod,
    headers
};

const response = await fetch(resourceRequestUri, options);
const json = await response.json();

// Delete keys from cache
await signedHttpRequest.removeKeys(publicKeyThumbprint);

```

#### サーバー ナンス

`SignedHttpRequest` API は、サーバー ナンス経由などを含む、SHR 内のカスタム クレームを設定できるようになります。 サーバー ノンスを取得するには、次の手順を実行します。

1. 公開キーの拇印を生成し、バインドされたペイロードを取得します。
2. nonce 値を指定せずに `SignedHttpRequest.signRequest()` を呼び出します。 これにより、ライブラリによって生成された `nonce` 要求 (現在は guid) を含む SHR が生成されます。
3. リソース サーバーに対して SHR を使用してネットワーク要求を呼び出します。
4. リソース サーバーは、ヘッダーで使用するエラーと新しい `nonce` 値で応答します。
5. アプリケーションはこのヘッダーを解析し、 `SignedHttpRequest.signRequest()`を再び取り込む必要があります。今回は解析された `nonce` 値を渡します (将来的には、MSAL はヘッダーを解析するためのヘルパー関数を提供します)。
6. SHR を使用してネットワーク要求をリソース サーバーに再び取り込みます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/single-sign-on"} -->
## シングル サインオン (MSAL.js) - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/single-sign-on
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用したシングル サインオン エクスペリエンスの構築について説明します。

シングル サインオン (SSO) を使用すると、ユーザーが資格情報を要求される回数を減らすことで、よりシームレスなエクスペリエンスを実現できます。 ユーザーは資格情報を 1 回入力します。確立されたセッションは、同じデバイス上の他のアプリケーションが、それ以上のプロンプトを表示せずに再利用できます。

Microsoft Entra IDでは、ユーザーが初めて認証するときにセッション Cookie を設定することで SSO を有効にします。 MSAL.js では、アプリケーション ドメインごとにブラウザー ストレージにユーザーの ID トークンとアクセス トークンもキャッシュされます。 セッション Cookie と Microsoft Authentication Library (MSAL) キャッシュMicrosoft Entra 2 つのメカニズムは互いに独立していますが、連携して SSO 動作を提供します。

### 同じアプリのブラウザー タブ間の SSO

ユーザーがアプリケーションを複数のタブで開き、そのうちの 1 つにサインインすると、プロンプトが表示されることなく、他のタブで開いている同じアプリにサインインできます。 これを行うには、次の例に示すように、MSAL.js 構成オブジェクトの *cacheLocation* を `localStorage` に設定する必要があります。

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

この場合、異なるブラウザー タブ内のアプリケーション インスタンスは同じ MSAL キャッシュを使用するため、それらの間で認証状態が共有されます。 ユーザーが別のブラウザー タブまたはウィンドウからログインするときに、アプリケーション インスタンスを更新するために MSAL イベントを使用することもできます。 詳細については、「[ログに記録された状態をタブとウィンドウ間で同期する](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/events.md#syncing-logged-in-state-across-tabs-and-windows)」を参照してください。

### 異なるアプリ間の SSO

ユーザーが認証を行うと、ブラウザーの Microsoft Entra ドメインにセッション Cookie が設定されます。 MSAL.js は、このセッション Cookie に依存して、異なるアプリケーション間でユーザーに SSO を提供します。 特に、MSAL.js は、ユーザーをサインインさせ、対話なしでトークンを取得する `ssoSilent` メソッドを提供します。 ただし、ユーザーが Microsoft Entra ID とのセッションで複数のユーザー アカウントを持っている場合は、サインインするアカウントを選択するように求められます。 そのため、 `ssoSilent` 方法を使用して SSO を実現するには、2 つの方法があります。

#### ユーザーヒント付き

パフォーマンスを向上させ、承認サーバーが正しいアカウント セッションを探すようにするには、 `ssoSilent` メソッドの要求オブジェクトで次のいずれかのオプションを渡して、トークンをサイレントで取得できます。

- `login_hint`は、ID トークン内の `account` オブジェクトのユーザー名プロパティまたは `upn` 要求から取得できます。 アプリが B2C でユーザーを認証している場合は、「[ID トークンでユーザー名を出力するように B2C ユーザー フローを構成する](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/FAQ.md#why-is-getaccountbyusername-returning-null-even-though-im-signed-in)」を参照してください
- `account` オブジェクトの `idTokenClaims` から取得できるセッション ID、`sid`。
- `account`1 つの[アカウント メソッド](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/login-user.md#account-apis)を使用して取得できます。

サイレント要求と対話型要求のための最も信頼性の高いアカウント ヒントであるため、`login_hint`[オプションの ID トークン クレーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims-reference#v10-and-v20-optional-claims-set)を`ssoSilent`に提供される`loginHint`として使用することをお勧めします。

##### ログイン ヒントの使用

`login_hint`オプションのクレームは、サインインしようとしているユーザー アカウントに関するヒントを Microsoft Entra ID に提供します。 対話型認証要求時に通常表示されるアカウント選択プロンプトをバイパスするには、次のように `loginHint` を指定します。

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

この例では、 `loginHint` にはユーザーの電子メールまたは UPN が含まれています。これは、対話型トークン要求時にヒントとして使用されます。 アプリケーション間でヒントを渡して、アプリケーション A がユーザーのサインイン、`loginHint`の読み取り、アプリケーション B への要求と現在のテナント コンテキストの送信を容易にするサイレント SSO を容易にすることができます。Microsoft Entra IDは、サインイン フォームの事前入力またはアカウント選択プロンプトのバイパスを試み、指定されたユーザーの認証プロセスを直接続行します。

`login_hint`要求の情報が既存のユーザーと一致しない場合は、アカウントの選択を含む標準的なサインイン エクスペリエンスを実行するようにリダイレクトされます。

##### セッション ID の使用

セッション ID を使用するには、[`sid`を省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)としてアプリの ID トークンに追加します。 `sid`要求を使用すると、アプリケーションはアカウント名やユーザー名に関係なく、ユーザーのMicrosoft Entra セッションを識別できます。 `sid`などの省略可能な要求を追加する方法については、「[省略可能な要求をアプリに提供する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)。 MSAL.jsの `ssoSilent` で行うサイレント認証要求でセッション ID (SID) を使用します。

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

ユーザー アカウント情報がわかっている場合は、 `getAccountByUsername()` または `getAccountByHomeId()` メソッドを使用してユーザー アカウントを取得することもできます。

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

#### ユーザー ヒントなし

次のコードに示すように、`ssoSilent`、`account`、または`sid`を渡さずに、`login_hint` メソッドの使用を試みることができます。

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

ただし、アプリケーションが 1 つのブラウザー セッションに複数のユーザーを持っている場合、またはユーザーがその 1 つのブラウザー セッションに対して複数のアカウントを持っている場合は、サイレント サインイン エラーが発生する可能性があります。 複数のアカウントが使用可能な場合、次のエラーが表示されることがあります。

```txt
InteractionRequiredAuthError: interaction_required: AADSTS16000: Either multiple user identities are available for the current request or selected account is not supported for the scenario.
```

このエラーは、サインインするアカウントをサーバーが判断できなかったことを示し、前の例のパラメーター (`account`、 `login_hint`、 `sid`) のいずれか、またはアカウントを選択するための対話型サインインが必要であることを示します。

### 使用する場合の考慮事項 `ssoSilent`

#### リダイレクト URI (応答 URL)

パフォーマンスを向上させ、問題を回避するには、 `redirectUri` を、MSAL を使用しない空白のページまたはその他のページに設定します。

- アプリケーション ユーザーがポップアップ メソッドとサイレント メソッドのみを使用する場合は、`redirectUri`構成オブジェクトに`PublicClientApplication`を設定します。
- アプリケーションでリダイレクト メソッドも使用する場合は、要求ごとに `redirectUri` を設定します。

#### サード パーティの Cookie

`ssoSilent`は、非表示の iframe を開き、Microsoft Entra IDで既存のセッションを再利用しようとします。 これは、Safari などのサードパーティの Cookie をブロックするブラウザーでは機能せず、対話エラーが発生します。

```txt
InteractionRequiredAuthError: login_required: AADSTS50058: A silent sign-in request was sent but no user is signed in. The cookies used to represent the user's session were not sent in the request to Azure AD
```

エラーを解決するには、ユーザーが `loginPopup()` または `loginRedirect()`を使用して対話型認証要求を作成する必要があります。 場合によっては、プロンプト値 **none** を対話型の MSAL.js メソッドと共に使用して SSO を実現できます。 詳細については、 [prompt=none を使用した対話型要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-prompt-behavior#interactive-requests-with-promptnone) を参照してください。 ユーザーのサインイン情報が既にある場合は、 `loginHint` または省略可能なパラメーター `sid` 渡して、特定のアカウントにサインインできます。

### prompt=login での SSO の否定

認証サーバーとのアクティブなセッションにもかかわらず、ユーザーに資格情報の入力を求めるMicrosoft Entra ID場合は、MSAL.js要求で**ログイン** プロンプト パラメーターを使用できます。 詳細については、 [プロンプトの動作MSAL.js](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-prompt-behavior) 参照してください。

### ADAL.js と MSAL.js の間で認証状態を共有する

MSAL.js は、Microsoft Entra認証シナリオの機能と ADAL.js の同等性をもたらします。 ADAL.js から MSAL.js への移行を簡単にし、アプリ間で認証状態を共有するために、ライブラリは、ADAL.js キャッシュ内のユーザーのセッションを表す ID トークンを読み取ります。 ADAL.jsから移行するときにこれを利用するには、ライブラリがトークンのキャッシュに `localStorage` を使用していることを確認する必要があります。 初期化時に MSAL.js 構成と ADAL.js 構成の両方で`cacheLocation`するように`localStorage`を設定します。

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

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/ssh-certificates"} -->
## SSH 証明書 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/ssh-certificates
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: SSH 証明書を取得して使用する方法について説明します

MSAL Browser は、`Bearer`および`PoP`アクセス トークンに加えて、ユーザーが SSH プロトコルを介してリモートで Linux Virtual Machines Azureアクセスできるようにするエフェメラル SSH 証明書の取得をサポートしています。 このドキュメントでは、これらの SSH 証明書を取得するときに MSAL の `acquireToken` API の推奨される使用パターンについて説明します。

### SSH 証明書の取得

#### 前提条件

MSAL は、要求されている SSH 証明書の取得、キャッシュ、更新 (または "更新") を処理します。 次のアクションは、クライアント アプリケーションの責任です。

- SSH 証明書が署名するための RSA キーペアの生成
- キーペアを安全に保存する
- 前述のキーペアから公開鍵とその一意のキー ID を取得し、使用される MSAL `acquireToken` API に提供すること
- クライアント アプリケーションの AzureAD アプリ登録で必要なリソース、スコープ、アクセス許可を有効にする
- SSH 証明書を使用して、対象のリソースに対して承認された要求を行う

#### SSH 証明書要求

SSH 証明書を取得するには、次のパラメーターを認証要求に追加する必要があります。

| パラメーター | 価値 | 説明 |
| --- | --- | --- |
| `authenticationScheme` | `ssh-cert` (`AuthenticationScheme.SSH`) | 要求の認証スキームは、要求する資格情報の種類を MSAL に指示します。 オプションは `Bearer`、`pop`、`ssh-cert` です。 MSAL には、これらのオプションを一覧表示する `AuthenticationScheme` 列挙型が用意されています。 |
| `sshJwk` | JSON Web キー形式の文字列化された RSA 公開キー | SSH JWK は、アプリケーションが生成する RSA キーの公開キー コンポーネントのシリアル化 (文字列化) バージョンです。 |
| `sshKid` | `sshJwk` RSA キーを一意に識別する文字列。 | SSH キー ID は、MSAL がアプリケーションの代わりに取得する SSH 証明書を一意に識別して照合するために使用されます。 |

MSAL では、問題の RSA キーの秘密キー コンポーネネットは使用されません。 必要に応じて秘密キーを安全に格納および取得するのは、クライアント アプリケーションの責任です。

##### トークン リダイレクト要求の取得の例

```typescript
// Configure SSH Key Data
const sshKeyData = {
    key: PUBLIC_KEY_JWK, // Replace with your SSH Public Key in JSON Web Key object format
    keyId: "PUBLIC_KEY_ID" // Replace with the SSH Public Key's unique ID
};

// Configure SSH Certificate Request
const sshCertificateRequest = {
    scopes: ["SAMPLE_SSH_SCOPE"], // Replace with your resource's scope
    authenticationScheme: msal.AuthenticationScheme.SSH,
    sshJwk: JSON.stringify(sshKeyData.key),
    sshKid: sshKeyData.keyId
}
```

*注: `authenticationScheme` を `AuthenticationScheme.SSH` に設定すると、 `sshJwk` 属性と `sshKid` 属性が必須になり、いずれかを省略した場合、要求は `ClientConfigurationError` で失敗します。*

要求が構成され、 `SSH` が `authenticationScheme`として設定されたら、 `acquireToken` MSAL v2 API に送信できます。

```typescript
// The SSH Certificate will be the string value contained in the accessToken property of the AuthenticationResult object
const { accessToken } = await myMSALObj.acquireTokenRedirect(sshCertificateRequest);
```

##### トークンをサイレントに取得するリクエストの例

SSH 証明書をサイレント モードで取得するには、対話型の `acquireToken` API と同じトークン要求構成の変更が必要です。

```typescript
// Configure SSH Key Data
const sshKeyData = {
    key: PUBLIC_KEY_JWK, // Replace with your SSH Public Key in JSON Web Key object format
    keyId: "PUBLIC_KEY_ID" // Replace with the SSH Public Key's unique ID
};

// Configure SSH Certificate Request
const silentSshCertificateRequest = {
    scopes: ["SAMPLE_SSH_SCOPE"], // Replace with your resource's scope
    authenticationScheme: msal.AuthenticationScheme.SSH,
    sshJwk: JSON.stringify(sshKeyData.key),
    sshKid: sshKeyData.keyId
}

// Try to acquire certificate silently
const { accessToken } = await myMSALObj.acquireTokenSilent(silentSshTokenRequest).catch(async (error) => {
        console.log("Silent token acquisition failed.");
        if (error instanceof msal.InteractionRequiredAuthError) {
            // Fallback to interaction if silent call fails
            console.log("Acquiring SSH Certificate using redirect");
            myMSALObj.acquireTokenRedirect(silentSshCertificateRequest);
        } else {
            console.error(error);
        }
    });
```

### コードサンプル

- [JavaScript SPA による SSH 証明書の取得](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0/app/ssh)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/testing"} -->
## ブラウザー環境でのアプリケーションのテスト - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/testing
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: ユニット、統合、E2E テスト用の loadExternalTokens API を使用して、MSAL.js ブラウザー アプリケーションをテストする方法について説明します

### The loadExternalTokens() API

MSAL Browser バージョン 2.17.0 以降では、 `loadExternalTokens()` API が追加されました。これにより、ID、アクセス、および更新トークンを MSAL キャッシュに読み込むことができます。これにより、 `acquireTokenSilent()`を使用してフェッチできます。

**注: これは、ブラウザー環境でのテストのみを目的とした高度な機能です。 アプリケーションのキャッシュにトークンを読み込むと、アプリが中断する可能性があります。 さらに、 `loadExternalTokens()` API を単体テストと統合テストで使用することをお勧めします。 E2E テストについては、代わりに [TestingSample](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/TestingSample) を参照してください。**

`loadExternalTokens()` API は、MSAL キャッシュへのカスタム 読み込みトークンをアプリに容易にするパブリック API です。

```js
await loadExternalTokens(
    config,
    silentRequest,
    serverResponse,
    loadTokenOptions,
);
```

`loadExternalTokens()` は、型 `SilentRequest`の要求、 `ExternalTokenResponse`型の応答、および型 `LoadTokenOptions`のオプションを受け取ります。

`@azure/msal-browser`からインポートできる、それぞれの型定義を参照してください。

- [`SilentRequest`](https://azuread.github.io/microsoft-authentication-library-for-js/ref/types/_azure_msal_browser.SilentRequest.html)
- [`ExternalTokenResponse`](https://azuread.github.io/microsoft-authentication-library-for-js/ref/types/_azure_msal_browser.ExternalTokenResponse.html)
- [`LoadTokenOptions`](https://azuread.github.io/microsoft-authentication-library-for-js/ref/types/_azure_msal_browser.LoadTokenOptions.html)

### トークンの読み込み

キャッシュには ID、アクセス、および更新トークンの任意の組み合わせを指定できますが、少なくとも、 `loadExternalTokens` API では、トークンの関連付けとキャッシュを適切に識別するために、次のいずれかの入力パラメーターセットが必要です。

- `SilentRequest`を含む オブジェクト(OR)
- 権限を持つ`SilentRequest`オブジェクトと、`clientInfo`を持つ`LoadTokenOptions`オブジェクト、または
- 権限を持つ `SilentRequest` オブジェクトと、その権限を持つサーバー応答オブジェクト `client_info`
- 権限を持つ `SilentRequest` オブジェクトと、その権限を持つサーバー応答オブジェクト `id_token`

次の例は、トークンを個別に読み込む方法を示していますが、1 つの要求に 1 つ、2 つ、または 3 つすべてを指定できます。

#### ID トークンの読み込み

上記のパラメーターに加えて、ID トークンを読み込むには次の情報を提供します。

1. `id_token` フィールドを含むサーバー応答

また、上記の情報に基づいて、アカウントもキャッシュに設定されます。

以下のコード例を参照してください。

```ts
const config: Configuration = {
    auth: { clientId: "your-client-id" },
};

const silentRequest: SilentRequest = {
    account: {
        homeAccountId: "your-home-account-id",
        environment: "login.microsoftonline.com",
        tenantId: "your-tenant-id",
        username: "test@contoso.com",
        localAccountId: "your-local-account-id",
    },
};

const serverResponse: ExternalTokenResponse = {
    id_token: "id-token-here",
};

const loadTokenOptions: LoadTokenOptions = {};

const pca = new PublicClientApplication(config);
await loadExternalTokens(
    config,
    silentRequest,
    serverResponse,
    loadTokenOptions
);

// OR

const config: Configuration = {
    auth: { clientId: "your-client-id" },
};

const silentRequest: SilentRequest = {
    scopes: [],
    authority: "https://login.microsoftonline.com/your-tenant-id",
};

const serverResponse: ExternalTokenResponse = {
    id_token: "id-token-here",
};

const loadTokenOptions: LoadTokenOptions = {
    clientInfo: "client-info-here",
};

const pca = new PublicClientApplication(config);
await loadExternalTokens(
    config,
    silentRequest,
    serverResponse,
    loadTokenOptions
);

// OR

const config: Configuration = {
    auth: { clientId: "your-client-id" },
};

const silentRequest: SilentRequest = {
    scopes: [],
    authority: "https://login.microsoftonline.com/your-tenant-id",
};

const serverResponse: ExternalTokenResponse = {
    id_token: "id-token-here",
    client_info: "client-info-here",
};

const loadTokenOptions: LoadTokenOptions = {};

const pca = new PublicClientApplication(config);
await loadExternalTokens(
    config,
    silentRequest,
    serverResponse,
    loadTokenOptions
);
```

#### アクセス トークンの読み込み

上記のパラメーターに加えて、アクセス トークンを読み込むには次の情報を提供します。

1. `access_token`、`expires_in`、`token_type`、および`scope`を含むサーバー応答

以下のコード例を参照してください。

```ts
const config: Configuration = {
    auth: { clientId: "your-client-id" },
};

const silentRequest: SilentRequest = {
    scopes: ["User.Read", "email"],
    account: {
        homeAccountId: "your-home-account-id",
        environment: "login.microsoftonline.com",
        tenantId: "your-tenant-id",
        username: "test@contoso.com",
        localAccountId: "your-local-account-id",
    },
};

const serverResponse: ExternalTokenResponse = {
    token_type: AuthenticationScheme.BEARER, // "Bearer"
    scope: "User.Read email",
    expires_in: 3599,
    access_token: "access-token-here",
};

const loadTokenOptions: LoadTokenOptions = {
    extendedExpiresOn: 6599,
};

const pca = new PublicClientApplication(config);
await loadExternalTokens(
    config,
    silentRequest,
    serverResponse,
    loadTokenOptions
);
```

#### 更新トークンの読み込み

上記のパラメーターに加えて、更新トークンを読み込むには次の情報を提供します。

1. `refresh_token`を含むサーバー応答(必要に応じて)`refresh_token_expires_in`

以下のコード例を参照してください。

```ts
const config: Configuration = {
    auth: { clientId: "your-client-id" },
};

const silentRequest: SilentRequest = {
    scopes: [],
    account: {
        homeAccountId: "your-home-account-id",
        environment: "login.microsoftonline.com",
        tenantId: "your-tenant-id",
        username: "test@contoso.com",
        localAccountId: "your-local-account-id",
    },
};

const serverResponse: ExternalTokenResponse = {
    refresh_token: "refresh-token-here",
    refresh_token_expires_in: "86399",
};

const loadTokenOptions: LoadTokenOptions = {};

const pca = new PublicClientApplication(config);

await loadExternalTokens(
    config,
    silentRequest,
    serverResponse,
    loadTokenOptions
);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/token-lifetimes"} -->
## トークンの有効期間を管理する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/token-lifetimes
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: MSAL.js でトークンの有効期間と ID トークン、アクセス トークン、更新トークンの自動更新を管理する方法について説明します

ここから始める前に、 [ログイン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user) して [トークンを取得](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/acquire-token)する方法を理解していることを確認してください。

MSAL.jsを使用するときは、ユーザーのトークンを取得することの影響と、これらのトークンの有効期間を管理する方法を理解する必要があります。

### トークンの有効期間と有効期限

Microsoft ID プラットフォームによって発行されたアクセス、ID、またはセキュリティ アサーション マークアップ言語 (SAML) トークンのトークン[の有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)を構成できます。 情報の一部を以下にまとめます。

#### ID トークン

ID トークンはアカウントとクライアントの特定の組み合わせにバインドされ、通常はユーザーに関するプロファイル情報が含まれます。 通常、Web アプリケーションのユーザー セッションの有効期間は、ID トークン セッションの有効期間 (既定では 24 時間) の有効期間と一致します。 [トークンの有効期間の構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)について詳しくは、こちらをご覧ください。

#### アクセス トークン

ブラウザーのアクセス トークンの既定の推奨有効期限は 1 時間です。 この 1 時間後、期限切れのトークンを使用したベアラー呼び出しはすべて拒否されます。 このトークンは、このトークンで取得された更新トークンを使用して、サイレントモードで更新できます。 [トークンの有効期間の構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)について詳しくは、こちらをご覧ください。

#### 更新トークン

Single-Page アプリケーションに与えられる更新トークンは、限られた時間の更新トークンです (通常、取得時から 24 時間)。 これは、調整不可で、スライドしないウィンドウの有効期間です。 更新トークンを使用してアクセス トークンを更新するたびに、更新されたアクセス トークンを使用して新しい更新トークンがフェッチされます。 この新しい更新トークンの有効期間は、元の更新トークンの残りの有効期間と同じです。 更新トークンの有効期限が切れると、新しい承認コード フローを開始して承認コードを取得し、それを新しいトークン セットと交換する必要があります。

注: 新しい更新トークンが取得されると、msal.js はキャッシュされた更新トークンを新しい更新トークンに置き換えますが、古い更新トークンはサーバーによって無効にされず、有効期限が切れるまでアクセス トークンを取得するために引き続き使用できます。

### トークンの更新

`PublicClientApplication` オブジェクトは、期限切れでないトークンをサイレントモードで取得することを目的として、`acquireTokenSilent`という API を公開します。 これは、いくつかの手順で行われます。

1. 特定の `scopes`、 `client id`、 `authority`、または `homeAccountIdentifier`のトークン キャッシュにトークンが既に存在するかどうかを確認します。
2. 指定されたパラメーターにトークンが存在する場合は、1 つの一致を取得し、有効期限を確認します。
3. アクセス トークンの有効期限が切れていない場合、MSAL は関連するトークンを含む応答を返します。
4. アクセス トークンの有効期限が切れているが、更新トークンがまだ有効な場合、MSAL は指定された更新トークンを使用して新しいトークンのセットを取得し、応答を返します。
5. 更新トークンの有効期限が切れている場合、MSAL は非表示の iframe を使用してアクセス トークンをサイレント モードで取得しようとします。 これにより、アカウントの要求オブジェクトの sid またはユーザー名が使用され、ユーザーのセッションに関するヒントが取得されます。 この非表示の iframe 呼び出しが失敗した場合、MSAL はサーバーからエラーを `InteractionRequiredAuthError`として渡し、新しいトークン セットを取得するための承認コードを取得するように求めます。 これを行うには、 `PublicClientApplication` オブジェクトで login または acquireToken API 呼び出しを実行します。 セッションがまだアクティブな場合、サーバーはユーザー プロンプトなしでコードを送信します。 それ以外の場合、ユーザーは資格情報を入力する必要があります。

 メソッドに設定できる構成パラメーターの詳細については、`acquireTokenSilent`に関する記事を参照してください。

#### ユーザーのセッションの途中で対話型の中断を回避する

場合によっては、必要に応じて、ユーザーのセッションの開始時に対話を事前に呼び出して、ユーザーがトークンをサイレントモードで取得し、さらに中断することなくアプリケーションを使用できるようにすることができます。 もちろん、これは、アプリケーションが初めて読み込まれるたびに対話を呼び出すことによって実現できます。ただし、ユーザーエクスペリエンスが低下し、ユーザーが以前のセッションまたは別のウィンドウ/タブからトークンを既に持っている場合はパフォーマンスが低下します。代わりに、いくつかの要求パラメーターを使用して、 `acquireTokenSilent` を使用して、任意の長さの間、キャッシュがサイレントモードで返すために必要なトークンを持っていることを確認できます。

`acquireTokenSilent`が有効なトークンを最低 1 時間返すことができるようにするには、次のようにします。

- `acquireTokenSilent`要求パラメーターを `forceRefresh` に設定して、ページ読み込み時に`true`を呼び出します。 これにより、キャッシュがスキップされ、以降の呼び出しでキャッシュから提供できる新しいトークンが取得されます。
- 後続の呼び出しでは `forceRefresh` 設定を解除するか、明示的に `false` して、トークンをキャッシュから提供できるようにします

`acquireTokenSilent`が、最大 24 時間の任意の長さの有効なトークンを返すことができるようにするには、次のようにします。

- `acquireTokenSilent`要求パラメーターを `forceRefresh` に設定し、`true` パラメーターを対話なしの目的の時間 (秒単位) に設定して、ページ読み込み時に`refreshTokenExpirationOffsetSeconds`を呼び出します
- 後続の呼び出しでは、 `forceRefresh` を残し、 `refreshTokenExpirationOffsetSeconds` 設定を解除して、キャッシュからトークンを確実に処理できるようにします。

たとえば、次の 2 時間、ユーザーがトークンをサイレントモードで取得できるようにする場合は、次のようにします。

```javascript
var request = {
    scopes: ["Mail.Read"],
    account: currentAccount,
    forceRefresh: true,
    refreshTokenExpirationOffsetSeconds: 7200 // 2 hours * 60 minutes * 60 seconds = 7200 seconds
};

const tokenResponse = await msalInstance.acquireTokenSilent(request).catch(async (error) => {
    if (error instanceof InteractionRequiredAuthError) {
        // fallback to interaction when silent call fails
        await msalInstance.acquireTokenRedirect(request);
    }
});
```

注: 更新トークンの有効期限がまだ切れていない場合でも、トークンをサイレントで取得できる保証はありません。 上記のパターンは、不便な時に相互作用を最小限に抑えるためのベスト エフォートの試みですが、必要な時間内に必要な相互作用の可能性を排除することはできません。 さらに、すべての ID プロバイダーが更新トークンの有効期限を返すわけではありません。このような場合、 `refreshTokenExpirationOffsetSeconds` 要求パラメーターは評価されません。

#### キャッシュ参照ポリシー

キャッシュ参照ポリシーは、必要に応じて要求に提供できます。 キャッシュ参照ポリシーは次のとおりです。

- `CacheLookupPolicy.Default` - `acquireTokenSilent` は、キャッシュからアクセス トークンの取得を試みます。 アクセス トークンの有効期限が切れているか、見つからない場合は、更新トークンを使用して新しいトークンを取得します。 最後に、更新トークンの有効期限が切れている場合、 `acquireTokenSilent` は、新しいアクセス トークン、ID トークン、および更新トークンを自動的に取得しようとします。
- `CacheLookupPolicy.AccessToken` - `acquireTokenSilent` では、キャッシュ内のアクセス トークンのみが検索されます。 アクセス トークンまたは更新トークンの更新を試行しません。
- `CacheLookupPolicy.AccessTokenAndRefreshToken` - `acquireTokenSilent` は、キャッシュからアクセス トークンの取得を試みます。 アクセス トークンの有効期限が切れているか、見つからない場合は、更新トークンを使用して新しいトークンが取得されます。 更新トークンの有効期限が切れている場合、更新されず、 `acquireTokenSilent` は失敗します。
- `CacheLookupPolicy.RefreshToken` - `acquireTokenSilent` はキャッシュからアクセス トークンを取得しようとせず、キャッシュされた更新トークンを新しいアクセス トークンと交換しようとします。 更新トークンの有効期限が切れている場合、更新されず、 `acquireTokenSilent` は失敗します。
- `CacheLookupPolicy.RefreshTokenAndNetwork` - `acquireTokenSilent` は、アクセス トークンのキャッシュを検索しません。 キャッシュされた更新トークンを使用してネットワークに直接移動します。 更新トークンの有効期限が切れている場合は、更新が試行されます。 これは、 `forceRefresh: true`の設定と同じです。
- `CacheLookupPolicy.Skip` - `acquireTokenSilent` は、アクセス トークンと更新トークンの両方の更新を試みます。 キャッシュ内では検索されません。 サード パーティの Cookie がブラウザーによってブロックされている場合、これは常に失敗します。

#### コード スニペット

##### Popup

```javascript
var username = "test@contoso.com";
var currentAccount = msalInstance.getAccount({ username });
var silentRequest = {
    scopes: ["Mail.Read"],
    account: currentAccount,
    forceRefresh: false,
    cacheLookupPolicy: CacheLookupPolicy.Default // will default to CacheLookupPolicy.Default if omitted
};

var request = {
    scopes: ["Mail.Read"],
    loginHint: currentAccount.username // For v1 endpoints, use upn from idToken claims
};

const tokenResponse = await msalInstance.acquireTokenSilent(silentRequest).catch(async (error) => {
    if (error instanceof InteractionRequiredAuthError) {
        // fallback to interaction when silent call fails
        return await msalInstance.acquireTokenPopup(request).catch(error => {
            if (error instanceof InteractionRequiredAuthError) {
                // fallback to interaction when silent call fails
                return msalInstance.acquireTokenRedirect(request)
            }
        });
    }
});
```

##### リダイレクト

```javascript
var username = "test@contoso.com";
var currentAccount = msalInstance.getAccount({ username });
var silentRequest = {
    scopes: ["Mail.Read"],
    account: currentAccount,
    forceRefresh: false,
    cacheLookupPolicy: CacheLookupPolicy.Default // will default to CacheLookupPolicy.Default if omitted
};

var request = {
    scopes: ["Mail.Read"],
    loginHint: currentAccount.username // For v1 endpoints, use upn from idToken claims
};

const tokenResponse = await msalInstance.acquireTokenSilent(silentRequest).catch(error => {
    if (error instanceof InteractionRequiredAuthError) {
        // fallback to interaction when silent call fails
        return msalInstance.acquireTokenRedirect(request)
    }
});
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/use-ie-browser"} -->
## Internet Explorerに関する問題 (MSAL.js) - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/use-ie-browser
- Service: msal / msal-js
- Article date: 2021-12-01
- Summary: Internet Explorer ブラウザーで JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用します。

Internet Explorerとの互換性を高めるために、[JavaScript ES5](https://262.ecma-international.org/5.1/) のJavaScript 用 Microsoft Authentication Library (MSAL.js) を生成しますが、アプリケーションの開発時に考慮すべき点は他にもあります。

### Internet Explorerでアプリを実行する

Internet Explorerでは、MSAL.jsに必要な JavaScript Promise のネイティブ サポートがありません。

Internet Explorer アプリで JavaScript Promise をサポートするには、MSAL.jsを参照する前に Promise ポリフィルを参照してください。

```html
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/bluebird/3.3.4/bluebird.min.js"
  class="pre"
></script>
```

### Internet Explorerで実行されているアプリケーションのデバッグ

#### 本番環境での実行

エンド ユーザーがポップアップを受け入れた場合、アプリケーションを運用環境 (Azure Web アプリなど) にデプロイしても正常に動作します。 Internet Explorer 11 でテストしました。

#### ローカルでの実行

アプリケーションをローカルでデバッグするには、デバッグ セッション中にInternet Explorerの*保護モード*を一時的に無効にします。

1. Internet Explorerで、[**ツール**&gt;&gt;**[セキュリティ**] タブ&gt;**Internet** ゾーンを選択します。
2. **[保護モードを有効にする (Internet Explorerの再起動が必要)]** チェック ボックスをオフにします。
3. [**OK] を**選択してInternet Explorerを再起動します。

デバッグが完了したら、前の手順に従い、保護**モードを有効にする (Internet Explorerを再起動する必要があります)** チェック ボックスをオンにします (オフではなく)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/v1-migration"} -->
## MSAL v1.x から MSAL v2.x への移行 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v1-migration
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: MSAL v1.x から MSAL v2.x に移行する方法について説明します

MSAL を初めて使用する場合は、 [ここから](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization)始める必要があります。 MSAL v1.x から来ている場合は、このガイドに従って、MSAL v2.x を使用するようにコードを更新できます

### 1. アプリケーションの登録を更新する

テナントのMicrosoft Entra 管理センターに移動し、アプリの登録を確認します。 MSAL 2.x の [新しい登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-registration#create-the-app-registration) を作成することも、MSAL 1.x に使用している登録の [既存の登録を更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-registration#redirect-uri-msaljs-20-with-auth-code-flow) することもできます。

### 2. `msal-browser` パッケージをプロジェクトに追加する

npm を使用して、次のコマンドを使用します。

```javascript
npm install @azure/msal-browser
```

### 3. コードを更新する

MSAL 1.x では、次のようにアプリケーション インスタンスを作成しました。

```javascript
import * as msal from "msal";

const msalInstance = new msal.UserAgentApplication(config);
```

MSAL 2.x では、新しい `PublicClientApplication` オブジェクトを使用するようにこれを更新できます。

```javascript
import * as msal from "@azure/msal-browser";

const msalInstance = new msal.PublicClientApplication(config);
```

渡される構成オブジェクトには、いくつかの小さな違いがある場合があります。 `UserAgentApplication` オブジェクトにさらに高度な構成を渡す場合は、新しいアプリ オブジェクト構成オプションの詳細については、[こちらを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration)参照してください。

要求と応答のオブジェクトシグネチャが変更されました。 `acquireTokenSilent` 対話型 API とは別のオブジェクト署名が作成されました。 要求 API の構成の詳細については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/request-response-object) 参照してください。

MSAL 1.x のほとんどの API は、変更なしで MSAL 2.x に引き継がれたものです。 一部の関数は削除されました。

- `handleRedirectCallback`
- `urlContainsHash`
- `getCurrentConfiguration`
- `getLoginInProgress`
- `getAccount`
- `getAccountState`
- `isCallback`

MSAL 2.x では、MSAL が応答から承認コードを解析するとすぐにトークン交換が実行されるため、ハッシュからの応答の処理は非同期操作です。 このため、MSAL は、リダイレクト呼び出しを実行するときに、MSAL によって完全に処理されたときに解決される promise を返す `handleRedirectPromise` 関数を提供します。 リダイレクト メソッドを使用する場合、 `redirectUri` として使用されるページは、応答が処理され、リダイレクトから戻るときにトークンがキャッシュされるように、 `handleRedirectPromise` を実装する必要があります。

```javascript
const myMSALObj = new msal.PublicClientApplication(msalConfig); 

// Register Callbacks for Redirect flow
myMSALObj.handleRedirectPromise().then((tokenResponse) => {
    let accountObj = null;
    if (tokenResponse !== null) {
        accountObj = tokenResponse.account;
        const id_token = tokenResponse.idToken;
        const access_token = tokenResponse.accessToken;
    } else {
        const currentAccounts = myMSALObj.getAllAccounts();
        if (!currentAccounts || currentAccounts.length === 0) {
            // No user signed in
            return;
        } else if (currentAccounts.length > 1) {
            // More than one user signed in, find desired user with getAccountByUsername(username)
        } else {
            accountObj = currentAccounts[0];
        }
    }
    
    const username = accountObj.username;
   
}).catch((error) => {
    handleError(error);
});

function signIn() {
    myMSALObj.loginRedirect(loginRequest);
}

async function getTokenRedirect(request) {
    return await myMSALObj.acquireTokenSilent(request).catch(error => {
        this.logger.info("silent token acquisition fails. acquiring token using redirect");
        // fallback to interaction when silent call fails
        return myMSALObj.acquireTokenRedirect(request)
    });
}
```

`loginPopup`、`acquireTokenPopup`、または`acquireTokenSilent`の呼び出し中に、約束が解決されるのを待つことができます。

```javascript
const myMSALObj = new msal.PublicClientApplication(msalConfig); 

async function signIn(method) {
    try {
        const loginResponse = await myMSALObj.loginPopup(loginRequest);
    } catch (err) {
        handleError(error);
    }

    const currentAccounts = myMSALObj.getAllAccounts();
    if (!currentAccounts || currentAccounts.length === 0) {
        // No user signed in
        return;
    } else if (currentAccounts.length > 1) {
        // More than one user signed in, find desired user with getAccountByUsername(username)
    } else {
        accountObj = currentAccounts[0];
    }
}

async function getTokenPopup(request) {
    return await myMSALObj.acquireTokenSilent(request).catch(async (error) => {
        this.logger.info("silent token acquisition fails. acquiring token using popup");
        // fallback to interaction when silent call fails
        return await myMSALObj.acquireTokenPopup(request).catch(error => {
            handleError(error);
        });
    });
}
```

使用方法の詳細については、 [ログイン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user) と [トークンの取得](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/acquire-token) に関するドキュメントを参照してください。

リフレッシュ トークンがトークン応答の一部として返されるようになり、ライブラリでユーザーの操作や iframe を使用せずにアクセス トークンを更新するために使用されます。 トークンの更新の詳細については、 [トークンの有効期間](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/token-lifetimes) に関するドキュメントを参照してください。

他のすべての API は、以前と同様に機能する必要があります。 [MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0) 2.0 の動作例については、既定のサンプルを参照することをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/v2-migration"} -->
## MSAL v2.x から MSAL v3.x への移行 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v2-migration
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: MSAL v2.x から MSAL v3.x に移行する方法について説明します

MSAL を初めて使用する場合は、 [ここから](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization)始める必要があります。

MSAL v1.x から来ている場合は、最初に [このガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v1-migration) を確認して MSAL v2.x に移行してから、次の手順に従う必要があります。

MSAL v2.x から来ている場合は、このガイドに従って、MSAL v3.x を使用するようにコードを更新できます。

### 破壊的な変更

#### アプリケーションのインスタンス化

MSAL v2.x では、次のようにアプリケーション インスタンスを作成しました。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

const msalConfig = {
    auth: {
        clientId: 'your_client_id'
    }
};

const msalInstance = new PublicClientApplication(msalConfig);
```

MSAL v3.x では、アプリケーション オブジェクトも初期化する必要があります。 自由に使用できるオプションがいくつかあります。

##### オプション 1

`PublicClientApplication` オブジェクトをインスタンス化し、後で初期化します。 `initialize`関数は非同期であり、他の MSAL.js API を呼び出す前に解決する必要があります。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

const msalConfig = {
    auth: {
        clientId: 'your_client_id'
    }
};

const msalInstance = new PublicClientApplication(msalConfig);
await msalInstance.initialize();
```

##### 方法 2

初期化された`createPublicClientApplication` オブジェクトを返す`PublicClientApplication`静的メソッドを呼び出します。 この関数は非同期であることに注意してください。

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

const msalConfig = {
    auth: {
        clientId: 'your_client_id'
    }
};

const msalInstance = await PublicClientApplication.createPublicClientApplication(msalConfig);
```

#### 要求ベースのキャッシュ

MSAL v2.x では、要求に要求を追加すると、要求された要求文字列のハッシュが既定でトークン キャッシュ キーに追加されます。 これは、MSAL 2.x が既定で要求に基づいてトークンをキャッシュして照合することを意味します。 MSAL v3.x では、この動作は既定ではなくなりました。 MSAL v3.x の既定の動作は、トークンが以前にキャッシュされていて、まだ有効であるかどうかに関係なく、要求が要求されるたびにトークンを更新するためにネットワークに移動することです。 次に、ネットワークに移動した後、要求のないサイレント要求が後で実行された場合に備えて、受信したトークンによってキャッシュされたトークンが上書きされます。 MSAL v3.x でクレーム ベースのキャッシュを有効にして MSAL v2.x と同じ動作を維持するには、クライアント アプリケーション構成オブジェクトで true に設定された `cacheOptions.claimsBasedCachingEnabled` 構成フラグを使用する必要があります。

```typescript
const msalConfig = {
    auth: {
        ...
    },
    ...
    cache: {
        claimsBasedCachingEnabled: true
    }
}

const msalInstance = new msal.PublicClientApplication(msalConfig);
await msalInstance.initialize();
```

他のすべての API は、MSAL v2.x と下位互換性があります。 [MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/VanillaJSTestApp2.0) v3.0 の動作例については、既定のサンプルを参照することをお勧めします。

#### 暗号

MSAL v3.x は、ネイティブ ブラウザー暗号化 API `window.msCrypto`を優先して、IE11 ネイティブ暗号化`window.msrCrypto`と Microsoft Research JavaScript Cryptography Library (MSR crypto) `window.crypto`のサポートを削除します。 MSR 暗号化に使用された `config.system.cryptoOptions` 暗号化オプションもサポートされなくなりました。

### 主な変更

#### ブラウザーのサポート

MSAL.js は、次のブラウザーをサポートしなくなりました。

- IE 11
- Edge (レガシ)

#### パッケージの依存関係

TypeScript のバージョンが `3.8.3` から `4.9.5` に更新されました。

#### コンパイラ オプション

モジュール/ターゲット バージョンがそれぞれ `es6`/`es5` から `es2020`/`es2020` にバンプされました。

#### CDN

MSAL.js は CDN でホストされなくなりました。 詳細については [、このドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/cdn-usage) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/v3-migration"} -->
## MSAL Browser v3 から v4 への移行 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v3-migration
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: loadExternalTokens の非同期変更やプラットフォーム ブローカーの名前変更など、ブラウザー アプリケーションを MSAL Browser v3 から v4 に移行する方法について説明します。

Note

v4 から v5 に移行する場合は、「 [MSAL Browser v4 から v5 への移行](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration)」を参照してください。

MSAL を初めて使用する場合は、「 [アプリケーションの初期化](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization)」から始める必要があります。

MSAL v2 から移行する場合は、まず [MSAL Browser v2 から v3 への移行](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v2-migration) を確認して MSAL v3 に移行し、その後で次の手順に従ってください。

MSAL v3 から来ている場合は、このガイドに従って、MSAL v4 を使用するようにコードを更新できます。

### API の破壊的変更

#### loadExternalTokens API が非同期になりました

[`loadExternalTokens` API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/testing) は非同期になり、Promise を返します。 この API を使用している場合は、その結果を使用する前に Promise が解決されるのを待つようにコードを更新する必要があります。

```js
const msalTokenCache = myMSALObj.getTokenCache();

// v3
const authenticationResult = msalTokenCache.loadExternalTokens(
    silentRequest,
    serverResponse,
    loadTokenOptions
);

// v4 change this to:
const authenticationResult = await msalTokenCache.loadExternalTokens(
    silentRequest,
    serverResponse,
    loadTokenOptions
);
```

#### allowNativeBroker の名前が allowPlatformBroker に変更された

`allowNativeBroker`構成パラメーターの名前が `allowPlatformBroker` に変更されました。 デバイスバインド トークンを使用している場合は、この機能を引き続き使用するように構成を更新する必要があります。 動作や既定値に対するその他の変更はありません。 [デバイス バインド トークン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/device-bound-tokens)のプラットフォーム ブローカーの詳細を確認します。

```js
// v3
const msalConfig = {
    auth: {
        clientId: "insert-clientId"
    },
    system: {
        allowNativeBroker: true
    }
};

// v4 change this to:
const msalConfig = {
    auth: {
        clientId: "insert-clientId"
    },
    system: {
        allowPlatformBroker: true
    }
};
```

### 動作の破壊的変更

次の変更ではコードを変更する必要はありませんが、ライブラリの動作の変更に関する FYI としてここに記載されています。

#### LocalStorage 暗号化

v4 以降では、`localStorage` キャッシュの場所を使用している場合、認証成果物は [HKDF](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/encrypt#aes-gcm) を使用して [AES-GCM](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/deriveKey#hkdf) で暗号化され、キーが派生します。 基本キーは、 `msal.cache.encryption`というタイトルのセッション Cookie に格納されます。

この Cookie は、ブラウザー インスタンス (タブではない) が閉じられると自動的に削除されるため、セッションが終了した後に認証アーティファクトを復号化できなくなります。 これらの有効期限が切れた認証アーティファクトは、次回 MSAL が初期化されるときに削除され、ユーザーは再認証が必要になる場合があります。 `localStorage`の場所は引き続きクロスタブ キャッシュの永続化を提供しますが、ブラウザー セッション間では保持されません。

Important

この暗号化の目的は、追加のセキュリティを提供 **せず** 、認証アーティファクトの永続化を減らすことです。 悪意のあるアクターがブラウザー ストレージにアクセスできる場合は、キーにアクセスすることも、キャッシュをまったく必要とせずに、ユーザーに代わってトークンを要求することもできます。 アプリケーションが XSS 攻撃に対して脆弱でないことを確認するのは、お客様の責任です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/v4-migration"} -->
## MSAL Browser v4 から v5 への移行 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: COOP のサポート、localStorage 暗号化、API の変更など、ブラウザー アプリケーションを MSAL Browser v4 から v5 に移行する方法について説明します。

MSAL を初めて使用する場合は、 [ここから](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization)始める必要があります。

MSAL v2 から来ている場合は、最初に [このガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v2-migration) を確認して MSAL v3 に移行する必要があります。 MSAL v3 から来ている場合は、最初に [このガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration) を確認して MSAL v4 に移行してから、次の手順に従う必要があります。

MSAL v4 から来ている場合は、このガイドに従って、MSAL v5 を使用するようにコードを更新できます。

### API の破壊的変更

#### SignedHttpRequest.removeKeys 戻り値の型が変更されました

`removeKeys` クラスの`SignedHttpRequest`関数は、`Promise<void>`ではなく`Promise<boolean>`を返すようになりました。 Promise 正常な解決は、以前の `true` の戻り値に相当するようになりました。 エラーが発生した場合、`false` を返す代わりにエラーとしてスローされるようになりました。

```javascript
// BEFORE
const shr = new SignedHttpRequest(shrParameters, shrOptions);
const result = await shr.removeKeys(thumbprint);
if (result) {
    // do something on success
} else {
    // do something on failure
}

// AFTER
const shr = new SignedHttpRequest(shrParameters, shrOptions);
await shr
    .removeKeys(thumbprint)
    .then(() => {
        // do something on success
    })
    .catch((e) => {
        // do something on failure
        console.log(e);
    });
```

#### TokenCache と loadExternalTokens

[loadExternalTokens](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/testing#the-loadexternaltokens-api) の MSAL JS API が変更されました。 それらの変更を次に示します。

- `TokenCache` オブジェクトと `getTokenCache()` が削除されました
- `loadExternalTokens()` API は別のエクスポートになり、パラメーターとして`Configuration`が必要になりました

```js
// BEFORE

const pca = new PublicClientApplication(config);
await pca
    .getTokenCache()
    .loadExternalTokens(silentRequest, serverResponse, loadTokenOptions);

//AFTER

await loadExternalTokens(
    config,
    silentRequest,
    serverResponse,
    loadTokenOptions
);
```

#### `handleRedirectPromise` API シグネチャが変更されました

以前は、 `PublicClientApplication.handleRedirectPromise` 省略可能なハッシュ パラメーターを使用しました。 `HandleRedirectPromiseOptions`と呼ばれる新しいオプションの種類が導入されました。 MSAL Browser v5 の時点では、 `HandleRedirectPromiseOptions` 型の省略可能なオブジェクトが、 `handleRedirectPromise()` 受け入れる唯一のパラメーターです。

```javascript
// BEFORE
const hash = window.location.hash; // Arbitrary example value
pca.handleRedirectPromise(hash);

// AFTER
pca.handleRedirectPromise({
    hash: window.location.hash, // Option nested inside a `HandleRedirectPromiseOptions` object
    navigateToLoginRequestUrl: true, // Additional option
});
```

#### `PublicClientApplication` の一部の機能の削除

`PublicClientApplication`の次の関数は削除されました。

1. `enableAccountStorageEvents()` と `disableAccountStorageEvents()`: アカウント ストレージ イベントが常に有効になりました。 これらの関数呼び出しは必要なくなりました。
2. `getAccountByHomeId()`、 `getAccountByLocalId()`、 `getAccountByUsername()`: 代わりに `getAccount()` を使用します。

    ```typescript
    // BEFORE
    const account1 = accountManager.getAccountByHomeId(yourHomeAccountId);
    const account2 = accountManager.getAccountByLocalId(yourLocalAccountId);
    const account3 = accountManager.getAccountByUsername(yourUsername);
    
    // AFTER
    const account1 = accountManager.getAccount({
        homeAccountId: yourHomeAccountId,
    });
    const account2 = accountManager.getAccount({
        localAccountId: yourLocalAccountId,
    });
    const account3 = accountManager.getAccount({ username: yourUsername });
    ```
3. `logout()`: 代わりに `logoutRedirect()` または `logoutPopup()` を使用します。

#### `startPerformanceMeasurement()` の削除

`startPerformanceMeasurement()` は削除されました。 代わりに、`startMeasurement()` を使用してください。

#### `PublicClientNext` の削除

`PublicClientNext` クラスとその静的メソッド`createPublicClientApplication()`は、MSAL v5 で削除されました。 アプリケーションの要件に応じて、次のいずれかの代替手段を使用する必要があります。

- **`PublicClientApplication`**: これは、標準のシングルアプリ シナリオに使用します。 これが既定で最も一般的な使用方法です。
- **`createNestablePublicClientApplication`**: 入れ子になったアプリ (NAA) をサポートする必要がある場合に使用します。 入れ子になったアプリ ブリッジが使用できない場合、または入れ子になったアプリ認証をサポートするようにハブが構成されていない場合、この関数は自動的に標準の PublicClientApplication にフォールバックします。 詳細については、[入れ子になったアプリの構成](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization#nested-app-configuration)を参照してください。
- **`createStandardPublicClientApplication`**: これを使用して、標準 (NAA 以外) の PublicClientApplication インスタンスをインスタンス化して初期化します。

##### 移行の例

```typescript
// BEFORE (using PublicClientNext)
import { PublicClientNext } from "@azure/msal-browser";

const pca = PublicClientNext.createPublicClientApplication(config);
```

```typescript
// AFTER (standard usage)
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication(config);
await pca.initialize();
```

```typescript
// AFTER (nested app support)
import { createNestablePublicClientApplication } from "@azure/msal-browser";

const pca = await createNestablePublicClientApplication(config);
```

```typescript
// AFTER (standard)
import { createStandardPublicClientApplication } from "@azure/msal-browser";

const pca = await createStandardPublicClientApplication(config);
```

ほとんどのアプリケーションでは、 `PublicClientNext.createPublicClientApplication(config)` を `new PublicClientApplication(config)` に置き換える必要があります。 以前に `supportsNestedAppAuth` 構成オプションを使用していた場合は、代わりに `createNestablePublicClientApplication(config)` に移行します。

#### PublicClientApplication.createPublicClientApplication 静的関数の削除

`createPublicClientApplication`の`PublicClientApplication`静的関数は削除され、個別にエクスポートされた`createStandardPublicClientApplication`に置き換えられました。

##### 移行の例

```typescript
// BEFORE
import { PublicClientApplication } from "@azure/msal-browser";

const pca = await PublicClientApplication.createPublicClientApplication(config);
```

```typescript
// AFTER
import { createStandardPublicClientApplication } from "@azure/msal-browser";

const pca = await createStandardPublicClientApplication(config);
```

### 構成の変更

#### BrowserAuthOptions の変更

1. `skipAuthorityMetadataCache` パラメーターが Configuration の BrowserAuthOptions から削除されました。
2. `protocolMode` パラメーターは、Configuration の BrowserAuthOptions ではなく SystemOptions に移動されました。
3. `supportsNestedAppAuth` パラメーターは削除されました。 入れ子になったアプリの `createNestablePublicClientApplication` API を代わりに使用します。 入れ子になったアプリの詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/initialization#nested-app-configuration)。
4. `navigateTologinRequestUrl` パラメーターは Configuration の BrowserAuthOptions から削除されました。代わりに、`handleRedirectPromise`の呼び出しのパラメーターとして options オブジェクト内で指定できるようになりました。

    ```typescript
    pca.handleRedirectPromise({ navigateToLoginRequestUrl: false });
    ```
5. `encodeExtraQueryParams` パラメーターは削除されました。 追加のクエリ パラメーターはすべてエンコードされます。
6. `supportsNestedAppAuth` パラメーターは削除されました。 `createNestablePublicClientApplication()` を代わりに使用します。

    ```typescript
        // BEFORE
        const pca = new PublicClientApplication({
            auth: {
                clientId: "your-client-id",
                authority: "https://login.microsoftonline.com/common"
                supportsNestedAppAuth: true
            },
        });
    
        // AFTER
        const pca = await createNestablePublicClientApplication({
            auth: {
                clientId: "your-client-id",
                authority: "https://login.microsoftonline.com/common"
            }
        });
    ```
7. `OIDCOptions` パラメーターは、`ResponseMode`ではなく`ServerResponseType`を受け取るようになりました。 `ResponseMode.QUERY`ではなく、`ServerResponseType.QUERY`や`ResponseMode.FRAGMENT`の代わりに`ServerResponseType.FRAGMENT`を使用してください。

#### CacheOptions の変更

次のパラメーターは MSAL Browser v4 で非推奨となり、v5 の `CacheOptions` から削除されました。

1. `temporaryCacheLocation`
2. `claimsBasedCachingEnabled` - アクセス トークンは、要求された要求に基づいて格納されなくなりました。
3. `storeAuthStateInCookie`
4. `secureCookies` - すべての Cookie が HTTPS 経由でのみ安全に送信されるようになりました。
5. `cacheMigrationEnabled`

#### システム オプション

1. `protocolMode` パラメーターは、Configuration の`SystemOptions`から`BrowserAuthOptions`に移動されました。 オプションや機能に変更はありません。
2. `navigateFrameWait` パラメーターは削除されました。 これは以前、MSAL.jsでサポートされなくなった古いブラウザーで必要とされていました。
3. `iframeHashTimeout`パラメーターと `windowHashTimeout` パラメーターは、それぞれ `iframeBridgeTimeout` および `popupBridgeTimeout` に置き換えられました。 これらのタイムアウトは、BroadcastChannel API 経由でリダイレクト ブリッジからの応答を待機する時間を制御するようになりました。

##### `asyncPopups`

`asyncPopups` パラメーターの名前が `navigatePopups` で `SystemOptions` に変更され、オプションが逆になります。 ポップアップを開いて後で移動するかどうかを設定します。 true に設定すると、空白のポップアップが開き、ログイン ドメインに移動します。 false に設定すると、ポップアップがログイン ドメインに直接開かれます。 これは、デスクトップ アプリやプログレッシブ Web アプリなど、 `about:blank` がサポートされていないシナリオでは false に設定できます。

Important

既定では、 `navigatePopups` は `true` に設定されます。 以前 `asyncPopups` を使用していた場合は、 `navigatePopups` に変更し、構成を元に戻す必要があります。

詳細については、 [構成に関するドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/configuration#system-config-options) を参照してください。

### 要求時の変更

#### `onRedirectNavigate` パラメーターの削除

`onRedirectNavigate` パラメーターは、今後の オブジェクト`Configuration`、`RedirectRequest`および`EndSessionRequest` オブジェクトから削除されます。 使用する必要がある場合は、msal 構成で設定してください。

#### 追加の要求パラメーターの統合

次の要求パラメーターが削除されました。

- `authorizePostBodyParams`
- `tokenBodyParameters`
- `tokenQueryParameters`

追加の要求パラメーターを簡略化するには、新しい `extraParameters` 要求オプションで汎用の追加パラメーターを使用する必要があります。 リクエストで `extraParameters` が設定されている場合、それらは、リクエストで構成されている `httpMethod`（既定値は `GET`）に応じて、URL のクエリ文字列またはリクエスト本文内のすべてのトークンサービス呼び出しで送信されます。 **URL クエリ文字列に含まれている必要がある追加のパラメーターを送信するには、 `extraQueryParameters` 引き続き使用できます。**

Note

追加のパラメーターを `extraQueryStringParameters` にするか、 `extraParameters`に入れるのかわからない場合は、 `extraParameters`に入る可能性が最も高くなります。

##### v4（以前の）リクエストの例:

```javascript
// Example of a GET request with extra parameters
const authRequest = {
    scopes: ["SAMPLE_SCOPE"],
    extraQueryParamters: {
        "dc": "DC_VALUE" // This was sent on the query string on GET /authorize
    },
    tokenBodyParameters: {
        "extra_parameters_assertion": "ASSERTION_VALUE" // This was sent on the POST body to /token
    },
    tokenQueryParamters: {
        "slice": "SLICE_VALUE" // This was sent on the query string on POST /token
    }
}

// Example of a POST request with extra parameters
const authRequest = {
    scopes: ["SAMPLE_SCOPE"],
    httpMethod: "POST", // default is "GET" -> Determines method for "/authorize" call. Calls to "/token" are always POST
    extraQueryParamters: {
        "dc": "DC_VALUE" // This was sent on the query string on POST /authorize
    },
    authorizePostBodyParameters: {
        "extra_parameters_assertion": "ASSERTION_VALUE", // This was sent on the body on POST /authorize
    }
    tokenBodyParameters: {
        "extra_parameters_assertion": "ASSERTION_VALUE" // This was sent on the POST body to /token
    },
    tokenQueryParamters: {
        "slice": "SLICE_VALUE" // This was sent on the query string on POST /token
    }
}
```

##### v5 リクエストの例

```javascript
// Example of a GET request with extra parameters
const authRequest = {
    scopes: ["SAMPLE_SCOPE"],
    extraQueryParamters: {
        // Will be sent in query string to /authorize and /token
        "dc": "DC_VALUE",
        "slice": "SLICE_VALUE"
    },
    extraParameters: {
        "extra_parameters_assertion": "ASSERTION_VALUE", // Will be sent in query string to /authorize and in body to /token
    },
};

// Example of a POST request with extra parameters
const authRequest = {
    scopes: ["SAMPLE_SCOPE"],
    httpMethod: "POST", // default is "GET" -> Determines method for "/authorize" call. Calls to "/token" are always POST
    extraQueryParamters: {
        // Will be sent in query string to /authorize and /token
        "dc": "DC_VALUE",
        "slice": "SLICE_VALUE"
    },
    extraParameters: {
        extra_parameter_assertion: "assertion_value", // Will be sent in post body to /authorize and /token
    },
};
```

Note

MSAL によって、 `extraParameters` を URL 文字列にエンコードする必要があると判断された場合、 `extraParameters` は同じ名前のパラメーターを上書きする方法で `extraQueryParams` とマージされます。 このような場合、 `extraParameters` のパラメーターの値は、 `extraQueryParams`の値よりも優先されます。

#### Cross-Origin-Opener-Policy (COOP) 対応

MSAL Browser v5 には、クロスオリジンOpener-Policy (COOP) の組み込みサポートが導入されています。これにより、参照コンテキストを分離することでセキュリティが強化されます。 認証サービス (Microsoft Entra IDまたは Azure AD B2C) が COOP ヘッダーを返すと、従来のポップアップおよびサイレント iframe 認証フローが制限されます。 MSAL v5 には、COOP 対応環境での認証を処理するためのリダイレクト ブリッジ メカニズムが用意されています。

Note

Microsoft Entra ID (以前のAzure AD) では、COOP が既定で有効になっています。 Azure AD B2C の場合、COOP の可用性は、バックエンド構成と使用されている認証エンドポイントによって異なります。

##### 変更された内容

認証サービスの応答 ( `Cross-Origin-Opener-Policy: same-origin` など) に COOP ヘッダーが存在する場合、認証ウィンドウがメイン アプリケーション ウィンドウと通信できないため、従来のポップアップおよびサイレント iframe 認証フローは失敗します。 MSAL v5 は、リダイレクト ブリッジ パターンを導入することでこれを解決します。

**すべての認証フロー** (`acquireTokenSilent()`、 `ssoSilent()`、 `loginPopup()`、 `loginRedirect()`) でリダイレクト ブリッジが使用されるようになりました。 リダイレクト ブリッジは、フローに基づいて認証応答を異なる方法で処理します。

- **ポップアップ フローとサイレント フロー**: リダイレクト ブリッジは、BroadcastChannel API を使用してメイン アプリケーション ウィンドウに認証応答をブロードキャストします
- **リダイレクト フロー**: リダイレクト ブリッジは、URL の認証応答を使用してリダイレクトを開始したアプリケーションのページに戻ります

##### しくみ

1. **メイン アプリケーション**: アプリケーションは、`loginPopup()`、`ssoSilent()`、または `loginRedirect()` を使用して認証を開始します
2. **リダイレクト**: MSAL は、機関ページへのポップアップ/iframe/ウィンドウを開きます
3. **認証フロー**: 機関ページが OAuth フローを完了し、認証応答を受け取ります
4. **応答処理**: リダイレクト ページでは、次の新しい `broadcastResponseToMainFrame()`関数が使用されます。
    - **ポップアップ/サイレント フロー**の場合: BroadcastChannel API を使用してメイン ウィンドウに応答をブロードキャストします
    - **リダイレクト フロー**の場合: 認証応答で`acquireTokenRedirect`が開始されたページに移動します
5. **トークンの取得**: メイン アプリケーションが応答を受け取り、トークンの取得を完了します

##### 移行の手順

###### 1. リダイレクト ブリッジ ページを設定する

`broadcastResponseToMainFrame()`から`@azure/msal-browser/redirect-bridge`を呼び出すページを作成します。 このページは COOP ヘッダーと共に提供 **しないでください** 。

セットアップはビルド システムによって異なります。 **[リダイレクト ブリッジのセットアップ ガイド Framework-Specific](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/redirect-bridge)** 参照してください。

| フレームワーク | Approach |
| --- | --- |
| [角度](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/redirect-bridge#angular) | ルート コンポーネント + 省略可能な `angular.json` アセット |
| [Vite](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/redirect-bridge#vite) | 複数ページ `rollupOptions.input` |
| [Webpack](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/redirect-bridge#webpack) | 別個のエントリ + `HtmlWebpackPlugin` |
| [Next.js](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/redirect-bridge#nextjs) | `MsalProvider` から除外されるページコンポーネント |
| CRA (React アプリの作成) | 静的 `public/redirect.html` ページ |
| [Express.js](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/redirect-bridge#expressjs--nodejs-backend) | サーバー側 COOP ヘッダーの除外 |

「**リダイレクト URI に関する考慮事項** |  | [」も参照してください](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Cross-Origin-Opener-Policy)。

###### 2. MSAL 構成を更新する

`redirectUri`を新しいリダイレクト ブリッジ ページにポイントします。

```javascript
const msalConfig = {
    auth: {
        clientId: "{your-client-id}",
        authority: "https://login.microsoftonline.com/common",
        redirectUri: "https://{your-app-home-page}/redirect",
    },
};
```

Important

また、**Entra ID アプリの登録**でリダイレクト URI を更新[する必要があります](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#add-a-redirect-uri)。 URI は、パス、プロトコル、ポートなど、 **正確に** 一致する必要があります。 これを行わないと、 `redirect_uri_mismatch` エラーが発生します。

### 動作の破壊的変更

#### イベントの種類と InteractionStatus の変更

イベントの種類と InteractionStatus を統合し、発生した API ではなく、何が起こったかを反映しました。

1. `SSO_SILENT` および `ACQUIRE_TOKEN_BY_CODE` イベントは、 `ACQUIRE_TOKEN` イベント (`START`/`SUCCESS`/`FAILURE` バリアント) に置き換えられました
2. `ACCOUNT_ADDED``ACCOUNT_REMOVED`は、それぞれ`LOGIN_SUCCESS`と`LOGOUT_SUCCESS`に置き換えられました。
3. `LOGIN_START``LOGIN_FAILURE`は、それぞれ`ACQUIRE_TOKEN_START`と`ACQUIRE_TOKEN_FAILURE`に置き換えられました。
4. `LOGIN_SUCCESS`のペイロードが`AccountInfo` オブジェクトになりました。
5. ログインが成功すると、 `LOGIN_SUCCESS` イベントと `ACQUIRE_TOKEN_SUCCESS` イベントの両方が出力されるようになりました。

##### `LOGIN_SUCCESS` ペイロードの種類の移行

イベント コールバックで現在 `LOGIN_SUCCESS` ペイロードを `AuthenticationResult` にキャストしている場合は、`LOGIN_SUCCESS` には `AccountInfo` を使用するように更新し、`ACQUIRE_TOKEN_SUCCESS` には `AuthenticationResult` を使用してください。

```typescript
// BEFORE (v4-style assumption)
import {
    EventType,
    AuthenticationResult,
} from "@azure/msal-browser";

pca.addEventCallback((event) => {
    if (event.eventType === EventType.LOGIN_SUCCESS) {
        const result = event.payload as AuthenticationResult;
        setAccount(result.account); // Will silently fail in v5 where payload is AccountInfo, not AuthenticationResult
    }
});
```

```typescript
// AFTER (v5-safe handling)
import {
    EventType,
    AuthenticationResult,
    AccountInfo,
} from "@azure/msal-browser";

pca.addEventCallback((event) => {
    if (event.eventType === EventType.LOGIN_SUCCESS) {
        const account = event.payload as AccountInfo;
        setAccount(account);
    }

    if (event.eventType === EventType.ACQUIRE_TOKEN_SUCCESS) {
        const result = event.payload as AuthenticationResult;
        setAccessToken(result.accessToken);
    }
});
```

#### エラー メッセージ形式の変更

バンドル サイズを小さくするために、エラー メッセージがバンドルから移動されました。 エラーがスローされると、 `message` プロパティは、説明的なエラー メッセージではなく、エラー ドキュメントへの汎用リンクを返すようになりました。

```javascript
// BEFORE (v4)
error.message = "Token request cannot be made without authorization code or refresh token.";

// AFTER (v5)
error.message = "See https://aka.ms/msal.js.errors#request_cannot_be_made for details";
```

`errorCode` プロパティは変更されず、特定のエラーを識別するために引き続き使用できます。 エラーの詳細な説明については、 [エラーのドキュメントを参照してください](https://aka.ms/msal.js.errors)。

Important

アプリケーションが `error.message` プロパティの解析または表示に依存している場合は、代わりに `errorCode` を使用するようにエラー処理コードを更新するか、ユーザーをドキュメント リンクに誘導する必要があります。

##### エラー処理コードの更新

**ユーザーにエラーを表示する場合**は、`errorCode`を直接表示するのではなく、`error.message`をわかりやすいメッセージにマップします。

```javascript
// BEFORE (v4)
showError(error.message);

// AFTER (v5) — use errorCode for user-facing messages
const userMessages = {
    request_cannot_be_made: "Please sign in again to continue.",
    interaction_required: "Additional verification is needed.",
    consent_required: "Administrator approval is required for this action.",
    login_required: "Your session has expired. Please sign in again.",
    // Add mappings for error codes your application encounters
};
showError(userMessages[error.errorCode] || "An authentication error occurred.");
```

**条件付きロジックのエラーを解析する場合**は、 `message` の文字列一致から `errorCode` の比較に切り替えます (これは v4 で既に推奨されているアプローチでした)。

```javascript
// BEFORE (v4) — fragile, relied on message text
if (error.message.includes("interaction_required")) {
    await msalInstance.acquireTokenPopup(request);
}

// AFTER (v5) — use errorCode (stable across versions)
if (error.errorCode === "interaction_required") {
    await msalInstance.acquireTokenPopup(request);
}
```

**診断のエラーをログに記録する場合**は、 `errorCode` と `message` の両方を含めます (メッセージには関連するドキュメントへの直接リンクが含まれるようになりました)。

```javascript
// AFTER (v5) — log errorCode for programmatic use, message for the docs link
logger.error(`MSAL Error [${error.errorCode}]: ${error.message}`);
// Output: MSAL Error [request_cannot_be_made]: See https://aka.ms/msal.js.errors#request_cannot_be_made for details
```

Tip

`errorCode`の値は v4 と v5 の間で同じです。`message`形式のみが変更されています。 既存のコードが既に `errorCode`で分岐している場合、変更は必要ありません。

#### コンソールログの変更

バンドル サイズを小さくするために、コンソール ログ メッセージがハッシュされるようになりました。 ブラウザー コンソールに完全なログ メッセージが表示されるのではなく、ハッシュ値が表示されます。

```javascript
// BEFORE (v4)
[Wed, 15 Jan 2025 10:30:45 GMT] : abc-123 : @azure/msal-browser@4.27.0 : Info - Returning token from cache

// AFTER (v5)
[Wed, 15 Jan 2025 10:30:45 GMT] : abc-123 : @azure/msal-browser@5.0.0 : Info - 7f3a9b2c
```

ブラウザー コンソールでのデバッグには、ログをデコードするための追加の手順が必要です。 ハッシュされたログを読み取り可能なメッセージにデコードするには、 [デコード スクリプト](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser/scripts/decode-logs.cjs)を使用します。 デコード スクリプトの使用方法の詳細については、スクリプトの [ドキュメントを参照してください](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser/scripts/README.md)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/browser/working-with-b2c"} -->
## JavaScript 用 Microsoft Authentication Library を使用して Azure AD B2C を操作する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/working-with-b2c
- Service: msal / msal-js
- Article date: 2025-05-21
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用すると、アプリケーションは Azure AD B2C を操作し、セキュリティで保護された Web API を呼び出すトークンを取得できます。 これらの Web API には、Microsoft Graph、他の Microsoft API、他のユーザーの Web API、または独自の Web API を使用できます。

Important

2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、[AZURE AD B2C を引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

[JavaScript 用 Microsoft Authentication Library (MSAL.js)](https://github.com/AzureAD/microsoft-authentication-library-for-js) を使用すると、JavaScript 開発者は [Azure Active Directory B2C (Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview)) を使用して、ソーシャル ID とローカル ID でユーザーを認証できます。

Id 管理サービスとして Azure AD B2C を使用すると、顧客がアプリケーションを使用する際のプロファイルのサインアップ、サインイン、および管理方法をカスタマイズおよび制御できます。

Azure AD B2C を使用すると、認証プロセス中にアプリケーションに表示される UI をブランド化およびカスタマイズすることもできます。

### サポートされているアプリの種類とシナリオ

MSAL.js を使用すると、[シングルページ アプリケーション](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/application-types#single-page-applications)は、PKCE を用いた[認可コードフロー](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/authorization-code-flow)を利用して Azure AD B2C にユーザーがサインインできます。 MSAL.js と Azure AD B2C の場合:

- ユーザー **は、** ソーシャル ID とローカル ID で認証できます。
- ユーザーは、Azure AD B2C で保護されたリソースへのアクセスを承認 **できます** (ただし、Microsoft Entra で保護されたリソースにはアクセスできません)。
- ユーザー**は**[、委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#types-of-permissions)を使用して Microsoft API (MS Graph API など) のトークンを取得できません。
- 管理者特権を持つユーザーは、**委任されたアクセス許可**を使用して Microsoft API (MS Graph API など) のトークンを取得[できます](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#types-of-permissions)。

詳細については、「[Azure AD B2C の使用](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/working-with-b2c)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/accounts"} -->
## MSAL ノードのアカウント - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/accounts
- Service: msal / msal-node
- Article date: 2026-03-15
- Summary: MSAL ノードのさまざまな API を使用して、キャッシュされたアカウントにアクセスする方法について説明します。

この記事では、 `msal-node` ライブラリを使用して、Node.js アプリケーション内のキャッシュされたアカウントにアクセスする方法について説明します。 API、 `getAllAccounts()`、 `getAccountByHomeId()`、および `getAccountByLocalId()` について説明します

### Prerequisites

- [Node.js](https://nodejs.org/en/download/)

### 使用方法

`msal-node` ライブラリには、キャッシュされたアカウントにアクセスするための次の異なる API が用意されています。

- `getAllAccounts()`: 現在キャッシュ内にあるすべてのアカウントを返します。 アプリケーションは、トークンをサイレントモードで取得するアカウントを選択する必要があります。
- `getAccountByHomeId()`: `homeAccountId` 文字列を受け取り、キャッシュから一致するアカウントを返します。
- `getAccountByLocalId()`: `localAccountId` 文字列を受け取り、キャッシュから一致するアカウントを返します。

各 API の使用例を次に示します。

#### `getAllAccounts`

複数のアカウントのシナリオの場合:

```javascript
// Initiates Acquire Token Silent flow
function callAcquireTokenSilent() {
    // Find all accounts
    const msalTokenCache = myMSALObj.getTokenCache();
    const cachedAccounts = await msalTokenCache.getAllAccounts();

    // Account selection logic would go here

    const account = .... // Select Account code

    // Build silent request after account is selected
    const silentRequest = {
        account: account,
        scopes: scopes,
    };

    // Acquire Token Silently to be used in MS Graph call
    myMSALObj.acquireTokenSilent(silentRequest)
        .then((response) => {
            // Successful response handling
        })
        .catch((error) => {
            // Error handling
        });
}
```

#### `getAccountByHomeId` および `getAccountByLocalId`

1 つのアカウント シナリオでは、`homeAccountId` フローなどの非サイレント承認フローから受信した最初の`localAccountId` オブジェクトから`AuthResponse`または`Auth Code`を取得する必要があります。

```javascript

// Initialize global homeAccountId variable, ideally stored in application state
let homeAccountId = null; // Same for localAccountId

// Get MSAL Token Cache from MSAL Client Applicaiton object
const msalTokenCache = myMSALObj.getTokenCache();

// Initial token acquisition, second leg of Auth Code flow
function getTokenAuthCode() {
    const tokenRequest = {
        code: req.query.code,
        redirectUri: "http://localhost:3000/redirect",
        scopes: scopes,
    };

    myMSALObj.acquireTokenByCode(tokenRequest).then((response) => {
        // Home account ID or local account ID to be used to find the right account before acquireTokenSilent
        homeAccountId = response.account.homeAccountId; // Same for localAccountId
        .
        .
        .
        // Handle successful token response
    }).catch((error) => {
        // Handle token request error
    });
}
```

アカウントとトークンがキャッシュされ、アプリケーションの状態が`homeAccountId`または`localAccountId`文字列を保持すると、`getAccountByHomeId`呼び出しの前に`getAccountByLocalId`と`acquireTokenSilent`を使用できます。

```javascript
async function getResource() {
    // Find account using homeAccountId or localAccountId built after receiving auth code token response
    const account = await msalTokenCache.getAccountByHomeId(app.locals.homeAccountId); // alternativley: await msalTokenCache.getAccountByLocalId(localAccountId) if using localAccountId

    // Build silent request
    const silentRequest = {
        account: account,
        scopes: scopes,
    };
    // Acquire Token Silently to be used in Resource API call
    pca.acquireTokenSilent(silentRequest)
        .then((response) => {
            // Handle successful resource API response
        })
        .catch((error) => {
            // Handle resource API request error
        });
}
```

複数のアカウントのシナリオでは、 [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/samples/msal-node-samples/silent-flow/index.js) ( `/graphCall` ルート) を変更して、キャッシュされたすべてのアカウントを一覧表示し、特定のアカウントを選択する必要があります。 関連するビュー テンプレートと `handlebars` テンプレート パラメーターをカスタマイズする必要がある場合もあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/acquire-token-requests"} -->
## MSAL ノードでのトークンの取得 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/acquire-token-requests
- Service: msal / msal-node
- Article date: 2025-05-21
- Summary: さまざまな OAuth 2.0 フローを使用して MSAL ノードでトークンを取得する方法について説明します。

MSAL Node ではさまざまな承認コード許可がサポートされているため、許可ごとに異なるパブリック API とそれに対応する要求がサポートされます。 この記事では、フローごとに使用できるさまざまなパブリック API と、対応する要求の種類について説明します。 アプリケーションの承認コード フローを実装することを強くお勧めします。

### 認証コード フロー

#### パブリック API

- [getAuthCodeUrl()](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/publicclientapplication#@azure-msal-node-publicclientapplication-getauthcodeurl): この API は、MSAL ノードの `authorization code grant` の最初の区間です。 要求は [AuthorizationUrlRequest 型です](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/authorizationurlrequest)。 アプリケーションには、 `authorization code`の生成に使用できる URL が送信されます。 この URL は任意のブラウザーで開くことができ、そこでユーザーは認証情報を入力すると、[アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-app-registration)時に登録された `redirectUri` に `authorization code` 付きでリダイレクトされます。 `authorization code`は、次の手順で`token`に引き換えることができます。 パブリック クライアント アプリケーションに対して承認コード フローが実行されている場合は、 [PKCE](https://tools.ietf.org/html/rfc7636) をお勧めします。
- [acquireTokenByCode()](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/publicclientapplication#@azure-msal-node-publicclientapplication-acquiretokenbycode): この API は、MSAL ノードの `authorization code grant` の第 2 段階です。 ここで構築される要求は、 [AuthorizationCodeRequest](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/authorizationcoderequest) 型である必要があります。 アプリケーションは、上記の手順の一環として受信した `authorization code` を渡し、 `token`と交換しました。 パブリック クライアント アプリケーションに対して承認コード フローが実行されている場合は、[PKCE](https://tools.ietf.org/html/rfc7636) をお勧めします。

```javascript

    const authCodeUrlParameters = {
        scopes: ["sample_scope"],
        redirectUri: "your_redirect_uri",
    };

    // get url to sign user in and consent to scopes needed for application
    cca.getAuthCodeUrl(authCodeUrlParameters).then((response) => {
        console.log(response);
    }).catch((error) => console.log(JSON.stringify(error)));

    const tokenRequest = {
        code: "authorization_code",
        redirectUri: "your_redirect_uri",
        scopes: ["sample_scope"],
    };

    // acquire a token by exchanging the code
    cca.acquireTokenByCode(tokenRequest).then((response) => {
        console.log("\nResponse: \n:", response);
    }).catch((error) => {
        console.log(error);
    });
```

### デバイス コード フロー

#### パブリック API

- [acquireTokenByDeviceCode()](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/publicclientapplication#@azure-msal-node-publicclientapplication-acquiretokenbydevicecode): この API を使用すると、アプリケーションはデバイス コードの付与を使用してトークンを取得できます。 要求の種類は [DeviceCodeRequest です](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/devicecoderequest)。 この API は、OAuth 2.0 のデバイス コード フローを使用して認証局から `token` を取得します。 このフローは、ブラウザーにアクセスできない、または入力の制約があるデバイス向けに設計されています。 承認サーバーは、検証コード、エンドユーザー コード、およびエンド ユーザー検証 URI を使用して DeviceCode オブジェクトを発行します。 DeviceCode オブジェクトはコールバックを通じて提供され、エンドユーザーは別のデバイスを使用して検証 URI に移動して資格情報を入力するように指示する必要があります。 クライアントは受信要求を受信できないため、エンド ユーザーが資格情報の入力を完了するまで、承認サーバーを繰り返しポーリングします。

```javascript
const msalConfig = {
    auth: {
        clientId: "your_client_id_here",
        authority: "your_authority_here",
    }
};

const pca = new msal.PublicClientApplication(msalConfig);

const deviceCodeRequest = {
    deviceCodeCallback: (response) => (console.log(response.message)),
    scopes: ["user.read"],
};

pca.acquireTokenByDeviceCode(deviceCodeRequest).then((response) => {
    console.log(JSON.stringify(response));
}).catch((error) => {
    console.log(JSON.stringify(error));
});

```

### リフレッシュ トークン フロー

#### パブリック API

- [acquireTokenByRefreshToken](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/apiid): この API は、新しいトークン セットに提供された更新トークンを交換することによってトークンを取得します。 要求の種類は [RefreshTokenRequest です](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/refreshtokenrequest)。 `refresh token`は応答でユーザーに返されることはありませんが、ユーザー キャッシュからアクセスできます。 非対話型のシナリオでは、 `acquireTokenSilent()` を使用することをお勧めします。 [acquireTokenSilent()を](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/clientapplication)使用する場合、MSAL はトークンのキャッシュと更新を自動的に処理します。

```javascript
const config = {
    auth: {
        clientId: "your_client_id_here",
        authority: "your_authority_here",
    }
};

const pca = new msal.PublicClientApplication(config);

const refreshTokenRequest = {
    refreshToken: "",
    scopes: ["user.read"],
};

pca.acquireTokenByRefreshToken(refreshTokenRequest).then((response) => {
    console.log(JSON.stringify(response));
}).catch((error) => {
    console.log(JSON.stringify(error));
});
```

### サイレント フロー

#### パブリック API

- [acquireTokenSilent](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/clientapplication): この API は、ユーザーによってキャッシュが提供された場合、またはキャッシュが作成された場合に、他の対話型フロー (承認コード フローなど) でこの呼び出しの前にトークンを取得します。 要求の種類は [SilentFlowRequest です](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/silentflowrequest)。 `token`は、トークンが要求されるアカウントをユーザーが指定すると、自動的に取得されます。

```javascript
/**
 * Cache Plugin configuration
 */
const cachePath = "path_to_your_cache_file/msal_cache.json"; // Replace this string with the path to your valid cache file.

const readFromStorage = () => {
    return fs.readFile(cachePath, "utf-8");
};

const writeToStorage = (getMergedState) => {
    return readFromStorage().then(oldFile =>{
        const mergedState = getMergedState(oldFile);
        return fs.writeFile(cachePath, mergedState);
    })
};

const cachePlugin = {
    readFromStorage,
    writeToStorage
};

/**
 * Public Client Application Configuration
 */
const publicClientConfig = {
    auth: {
        clientId: "your_client_id_here",
        authority: "your_authority_here",
        redirectUri: "your_redirectUri_here",
    },
    cache: {
        cachePlugin
    },
};

/** Request Configuration */

const scopes = ["your_scopes"];

const authCodeUrlParameters = {
    scopes: scopes,
    redirectUri: "your_redirectUri_here",
};

const pca = new msal.PublicClientApplication(publicClientConfig);
const msalCacheManager = pca.getCacheManager();
let accounts;

pca.getAuthCodeUrl(authCodeUrlParameters)
    .then((response) => {
        console.log(response);
    }).catch((error) => console.log(JSON.stringify(error)));

const tokenRequest = {
    code: req.query.code,
    redirectUri: "http://localhost:3000/redirect",
    scopes: scopes,
};

pca.acquireTokenByCode(tokenRequest).then((response) => {
    console.log("\nResponse: \n:", response);
    return msalCacheManager.writeToPersistence();
}).catch((error) => {
    console.log(error);
});

// get Accounts
accounts = msalCacheManager.getAllAccounts();

// Build silent request
const silentRequest = {
    account: accounts[0], // You would filter accounts to get the account you want to get tokens for
    scopes: scopes,
};

// Acquire Token Silently to be used in MS Graph call
pca.acquireTokenSilent(silentRequest).then((response) => {
    console.log("\nSuccessful silent token acquisition:\nResponse: \n:", response);
    return msalCacheManager.writeToPersistence();
}).catch((error) => {
        console.log(error);
});
```

### クライアント資格情報フロー

#### パブリック API

- [acquireTokenByClientCredential](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/confidentialclientapplication#@azure-msal-node-confidentialclientapplication-acquiretokenbyclientcredential): この API は、別の Web サービスを呼び出すときに(ユーザーを偽装するのではなく) 認証するために、機密クライアント アプリケーションの資格情報を使用してトークンを取得します。 このシナリオでは、通常、クライアントは中間層 Web サービス、デーモン サービス、またはバックエンド Web アプリケーションです。 高いレベルの保証では、Microsoft ID プラットフォームにより、呼び出し元サービスが、資格情報として (共有シークレットではなく) 証明書を使用することもできます。 要求の種類は [ClientCredentialRequest です](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/clientcredentialrequest)。

#### シークレットを安全に使用する

シークレットはハードコーディングしないでください。 dotenv npm パッケージを使用すると、シークレットを .gitignore に含める必要がある .env ファイル (プロジェクトのルート ディレクトリにあります) にシークレットを格納し、シークレットが誤ってアップロードされるのを防ぐことができます。

```javascript
import "dotenv/config"; // process.env now has the values defined in a .env file

const config = {
    auth: {
        clientId: "your_client_id_here",
        authority: "your_authority_here",
        clientSecret: process.env.clientSecret
    }
};

// Create msal application object
const cca = new msal.ConfidentialClientApplication(config);

// With client credentials flows permissions need to be granted in the portal by a tenant administrator.
// The scope is always in the format "<resource>/.default"
const clientCredentialRequest = {
    scopes: ["https://graph.microsoft.com/.default"], // replace with your resource
};

cca.acquireTokenByClientCredential(clientCredentialRequest).then((response) => {
    console.log("Response: ", response);
}).catch((error) => {
    console.log(JSON.stringify(error));
});
```

### Flow に代わって

- [acquireTokenOnBehalfOf](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/confidentialclientapplication#@azure-msal-node-confidentialclientapplication-acquiretokenonbehalfof): この API は、On Behalf Of Flow を実装します。これは、アプリケーションがサービス/Web API を呼び出すときに使用されます。これは、他の認証フロー (デバイス コード、ユーザー名、パスワードなど) を使用する別のサービス/Web API を呼び出す必要があります。 アクセス トークンは最初に Web API によって取得されます (Web API フローのいずれかによって)。Web API は、OBO を介してこのトークンを別のトークンと交換できます。 要求は [OnBehalfOfRequest](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/onbehalfofrequest) 型です

使用方法については、On Behalf Of フローの [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/standalone-samples/on-behalf-of) を参照してください。

- [WebAPI](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/on-behalf-of/web-api/index.js) サンプル コード
- [WebApp](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/on-behalf-of/web-app/index.js) サンプル コード
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/brokering"} -->
## ネイティブ トークン ブローカーでの MSAL ノードの使用 (Windows) - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/brokering
- Service: msal / msal-node
- Article date: 2026-06-05
- Summary: MSAL Node でWindowsのネイティブ トークン ブローカーを使用して、デバイス バインド トークンを取得し、所有証明を構成し、ブローカー固有の動作を理解する方法について説明します。

Microsoft Authentication Library (MSAL) ノードでは、ネイティブ トークン ブローカーからのトークンの取得がサポートされています。 ネイティブ ブローカーを使用する場合、更新トークンは取得されたデバイスにバインドされ、 `msal-node` やアプリケーションからはアクセスできません。 これにより、 `msal-node` だけでは実現できない、より高いレベルのセキュリティが提供されます。

この記事では、Windows ブローカーを構成し、所有証明を設定し、ブローカー固有の動作を理解する方法について説明します。

ブローカーのサポートは、次のプラットフォームで利用できます。

| Platform | Broker | ドキュメンテーション |
| --- | --- | --- |
| **Windows** | Web アカウント マネージャー (WAM) | Windows ブローカー (この記事) |
| **macOS** | Microsoft Enterprise SSO プラグイン (ポータル サイト) | [macOS ブローカー](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/brokering-macos) |
| **Linux** | Microsoft Linux のシングル サインオン | [Linux ブローカー](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/brokering-linux) |

### ブローカーとは

認証ブローカーは、ユーザーのコンピューター上で実行され、接続されているアカウントの認証ハンドシェイクとトークンのライフサイクルを管理するコンポーネントです。 Windowsでは、このロールは Web アカウント マネージャー (WAM) によって実行されます。 主な利点は次のとおりです。

- **セキュリティの強化。** セキュリティの強化は、アプリ コードの変更を必要とせずに、OS またはブローカーの更新プログラムを介して提供されます。 更新トークンはデバイスにバインドされ、流出から保護されます。
- **機能のサポート。** 追加のスキャフォールディング コードなしで、Windows Hello、Microsoft Entra 条件付きアクセス ポリシー、Fast Identity Online (FIDO) セキュリティ キーなどの豊富な OS 機能にアクセスできます。
- **システム統合。** アプリケーションは組み込みのアカウント ピッカーに接続されるため、ユーザーは資格情報を再入力する代わりに既存のアカウントをすばやく選択できます。
- **トークン保護。** ブローカーは、更新トークンがデバイスにバインドされていることを確認し、 アプリが所有証明アクセス トークンを取得できるようにします。

### サポートされているアーキテクチャ

- Windows: x64、x86、ARM64

### Prerequisites

- Node.js 18 以降
- 依存関係として `@azure/msal-node-extensions` をインストールする
- ブローカーのリダイレクト URI をアプリの登録に登録します。 必要な値については、「 リダイレクト URI」を参照してください。

### リダイレクト URI

Azure ポータルの**モバイル およびデスクトップ アプリケーション** プラットフォームに、次のリダイレクト URI を登録します。

```text
ms-appx-web://Microsoft.AAD.BrokerPlugin/<your-client-id>
```

`<your-client-id>`をアプリケーションのクライアント ID に置き換えます。

### 機能の有効化

トークン ブローカーを有効にするには、構成パラメーターを 1 つだけ必要とします。 ブローカー構成で `NativeBrokerPlugin` インスタンスを渡します。

```javascript
import { PublicClientApplication, Configuration } from "@azure/msal-node";
import { NativeBrokerPlugin } from "@azure/msal-node-extensions";

const msalConfig: Configuration = {
    auth: {
        clientId: "your-client-id",
    },
    broker: {
        nativeBrokerPlugin: new NativeBrokerPlugin(),
    },
};

const pca = new PublicClientApplication(msalConfig);
```

Note

`msal-node` は、障害が発生した場合に非ブローカー フローにフォールバック *しません* 。 予期しないエラーを回避するために、ブローカー フローをサポートする環境でのみ有効にします。

実際のサンプルは、 [auth-code-cli-brokered-app サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/auth-code-cli-brokered-app)にあります。

### ウィンドウのペアレンティング

認証プロンプトが呼び出し元のアプリケーションに表示され、それ以上の対話をブロックするには、アプリケーションのウィンドウ ハンドルを `acquireTokenInteractive` API に提供します。

CLI アプリでは、ウィンドウ ハンドルを検索するためのベスト エフォート試行が内部で行われますが、信頼性が低い場合があります。

Electron を使用している場合は、 [`getNativeWindowHandle`](https://www.electronjs.org/docs/latest/api/browser-window#wingetnativewindowhandle) API を使用し、結果を `acquireTokenInteractive`に渡します。

```javascript
import { BrowserWindow } from "electron";

const win = new BrowserWindow();
const pca = new PublicClientApplication(msalConfig);

pca.acquireTokenInteractive({
    windowHandle: win.getNativeWindowHandle(),
});
```

### 所持証明

アクセス トークン所有証明 (PoP) は、ネイティブ ブローカーを介してトークンを取得するときにサポートされます。 PoP トークンを要求するには、 `acquireTokenInteractive` または `acquireTokenSilent`に指定された要求オブジェクトに次のプロパティを追加します。

#### AT PoP リクエスト パラメータ

| 名前 | 説明 | 必須 |
| --- | --- | --- |
| `authenticationScheme` | MSAL が `Bearer` または `PoP` トークンを取得する必要があるかどうかを示します。 既定値は `Bearer` です。 | **必須** |
| `resourceRequestMethod` | 署名されたトークンを使用する要求の HTTP メソッドのすべて大文字の名前 (`GET`、 `POST`、 `PUT`など) | **必須** |
| `resourceRequestUri` | アクセス トークンが発行されている保護されたリソースの URL | **必須** |
| `shrNonce` | Base64URL が文字列としてエンコードされた、サーバーによって生成された署名付きタイムスタンプ。 この nonce は、PoP トークンの事前生成を可能にするために、クロック スキュー攻撃とタイムトラベル攻撃を軽減するために使用されます。 | *オプション* |

#### 使用例

この例では、認証スキームと署名付き HTTP 要求プロパティを設定して、所有証明トークンを要求します。

```javascript
import { PublicClientApplication, Configuration, AuthenticationScheme } from "@azure/msal-node";
import { NativeBrokerPlugin } from "@azure/msal-node-extensions";

const msalConfig: Configuration = {
    auth: {
        clientId: "your-client-id",
    },
    broker: {
        nativeBrokerPlugin: new NativeBrokerPlugin(),
    },
};

const pca = new PublicClientApplication(msalConfig);

const popTokenRequest = {
    scopes: ["User.Read"],
    authenticationScheme: AuthenticationScheme.POP,
    resourceRequestMethod: "POST",
    resourceRequestUri: "YOUR_RESOURCE_ENDPOINT",
    shrNonce: "NONCE_ACQUIRED_FROM_RESOURCE_SERVER",
};

pca.acquireTokenInteractive(popTokenRequest);
pca.acquireTokenSilent(popTokenRequest);
```

Note

アクセス トークンの所有証明は、ネイティブ ブローカー フロー経由でのみサポートされ、非ブローカー フローでは使用できません。

### Windows ブローカーを使用する場合の違い

ネイティブ ブローカーを使用してトークンを取得するときに、動作が異なる場合がいくつかあります。

- `forceRefresh`呼び出しの`acquireTokenSilent` パラメーターはサポートされていません。 このフラグの設定に関係なく、ブローカーからキャッシュされたトークンを受け取ることがあります。
- ブローカーがユーザーに対話を求める必要がある場合は、システム プロンプトが開きます。 これにより、ブラウザー ウィンドウで認証が行われないため、ユーザー エクスペリエンス (UX) が変更されます。
- アクセス トークンの所有証明 *は* ブローカーによってサポートされますが、ブローカー以外のフローではサポート *されていません* 。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/brokering-linux"} -->
## Linux ブローカーでの MSAL ノードの使用 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/brokering-linux
- Service: msal / msal-node
- Article date: 2026-06-05
- Summary: MSAL ノードで Linux ブローカーを使用して、シングル サインオンを有効にし、依存関係をインストールし、セキュリティで保護されたブローカー トークンの取得を構成する方法について説明します。

Microsoft Authentication Library (MSAL) ノードは、linux ブローカー用の Microsoft シングル サインオンを使用して、サポートされている Linux ディストリビューションでシングル サインオン (SSO) とセキュリティで保護されたトークン取得を提供できます。 ブローカーは認証ハンドシェイクとトークンのライフサイクルを管理し、ユーザーがシステムに認識されているアカウントとの統合を利用できるようにします。

この記事では、Linux ブローカーとは何か、サポートされているディストリビューション、および Node.js アプリでブローカー トークンの取得を有効にする方法について説明します。

すべてのプラットフォームでのブローカー サポートの概要については、 [ネイティブ トークン ブローカーでの MSAL ノードの使用に関するページを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/brokering)参照してください。

### Linux ブローカーとは

Microsoft シングル サインオン for Linux は、Linux ディストリビューションとは別に出荷される認証ブローカーです。 パッケージ マネージャー (`sudo apt install microsoft-identity-broker` または `sudo dnf install microsoft-identity-broker`) でインストールします。 また、[ポータル サイトなどのMicrosoft](https://learn.microsoft.com/ja-jp/mem/intune-service/user-help/enroll-device-linux) アプリケーションの依存関係としてバンドルされています。

主な利点は次のとおりです。

- **シングルサインオン。** ユーザーがMicrosoft Entra IDで認証する方法を簡略化し、流出や誤用から更新トークンを保護します。
- **セキュリティの強化。** セキュリティの強化は、アプリ コードの変更を必要とせずに、ブローカーの更新プログラムを通じて提供されます。
- **トークン保護。** ブローカーは、更新トークンがデバイスにバインドされていることを確認します。
- **システム統合。** アプリケーションは組み込みのアカウント ピッカーに接続され、ユーザーは既存のアカウントをすばやく選択できます。

### サポートされているディストリビューション

- Ubuntu 22.04 / 24.04 (x64)
- Red Hat Enterprise Linux (RHEL) 8 / 9 / 10 (x64)

### Prerequisites

- Node.js 18 以降
- 依存関係として `@azure/msal-node-extensions` をインストールする
- `microsoft-identity-broker` デバイスにインストールする必要があります
- アプリの登録にブローカー リダイレクト URI を登録します (以下の リダイレクト URI を 参照してください)
- 必要なシステム依存関係をインストールします (以下の 「システムの依存関係」 を参照)

### リダイレクト URI

Linux ブローカー フローの場合は、Azure ポータルの**モバイル およびデスクトップ アプリケーション** プラットフォームに次のリダイレクト URI を登録します。

```text
https://login.microsoftonline.com/common/oauth2/nativeclient
```

### システムの依存関係

## [Ubuntu](#tab/ubuntu)
#### Ubuntu 22.04/24.04

```bash
sudo apt install libsecret-1-0 libdbus-1-3
```

- **libsecret** — システム キーリング (GNOME Keyring または KDE ウォレット) を介して認証トークンを安全に格納および取得します。
- **dbus** — 認証ブローカーとのプロセス間通信。 D-Bus セッション バスが実行されている必要があります (デスクトップ環境では標準。ヘッドレス サーバーには `dbus-run-session` またはそれと同等のものが必要な場合があります)。

```bash
sudo apt install libwebkit2gtk-4.1-0 libgtk-3-0
```

- **WebKitGTK** — 対話型サインイン UI 用の埋め込みブラウザーを提供します。
- **GTK** — WebKitGTK に必要な基になる UI ツールキット。

## [RHEL](#tab/rhel)
#### RHEL 8

```bash
sudo dnf install libsecret dbus-libs openssl3-libs
```

- **libsecret** — システム キーリング (GNOME Keyring または KDE ウォレット) を介して認証トークンを安全に格納および取得します。
- **dbus-libs** — 認証ブローカーとのプロセス間通信。 D-Bus セッション バスが実行されている必要があります (デスクトップ環境では標準。ヘッドレス サーバーには `dbus-run-session` またはそれと同等のものが必要な場合があります)。
- **openssl3-libs** — ネイティブ バイナリは OpenSSL 3 (`libcrypto.so.3`) に対してリンクされています。 RHEL 8 には既定で OpenSSL 1.1.1 が付属しているため、このパッケージを明示的にインストールする必要があります。 多くの RHEL 8 システムには、他のソフトウェアの依存関係として既に存在しています。

```bash
sudo dnf install webkit2gtk3 gtk3
```

#### RHEL 9

```bash
sudo dnf install libsecret dbus-libs
```

追加の OpenSSL パッケージは必要ありません。OpenSSL 3 がシステムの既定値です。

```bash
sudo dnf install webkit2gtk3 gtk3
```

#### RHEL 10

```bash
sudo dnf install libsecret dbus-libs
```

追加の OpenSSL パッケージは必要ありません。OpenSSL 3 がシステムの既定値です。

```bash
sudo dnf install webkitgtk6.0 gtk4
```

---

### 機能の有効化

Linux ブローカーを有効にすると、Windowsおよび macOS と同じ構成が使用されます。 ブローカー構成で `NativeBrokerPlugin` インスタンスを渡します。

```javascript
import { PublicClientApplication, Configuration } from "@azure/msal-node";
import { NativeBrokerPlugin } from "@azure/msal-node-extensions";

const msalConfig: Configuration = {
    auth: {
        clientId: "your-client-id",
    },
    broker: {
        nativeBrokerPlugin: new NativeBrokerPlugin(),
    },
};

const pca = new PublicClientApplication(msalConfig);
```

Note

`msal-node` は、ブローカーが使用できない場合、ブラウザー ベースのフローにフォールバックしません。 予期しないエラーを回避するために、サポートされている Linux デバイスでのみブローカー認証を有効にします。

### トークンの取得

#### 対話型トークンの取得

`acquireTokenInteractive`を使用して、Linux ブローカーを介してトークンを要求します。

```javascript
const tokenRequest = {
    scopes: ["User.Read"],
};

const result = await pca.acquireTokenInteractive(tokenRequest);
console.log("Access token:", result.accessToken);
```

#### サイレント トークンの取得

最初の対話型サインインの後、後続のトークン要求をサイレントモードで行うことができます。

```javascript
const accounts = await pca.getAllAccounts();

if (accounts.length > 0) {
    const silentRequest = {
        scopes: ["User.Read"],
        account: accounts[0],
    };

    const result = await pca.acquireTokenSilent(silentRequest);
    console.log("Access token (silent):", result.accessToken);
}
```

### トークンのキャッシュ

認証ブローカーは、更新とアクセス トークンのキャッシュを処理します。 ブローカーを介して取得されたトークンは、ブローカー自体によって管理され、デバイスバインドされます。 ブローカーを使用するときにカスタム キャッシュを設定する必要はありません。

### Linux ブローカーを使用する場合の違い

- ブローカーが対話を求める必要がある場合は、ネイティブ サインイン ダイアログ (WebKitGTK ベース) が表示されます。 これにより、ブラウザー ベースの認証と比較してユーザー エクスペリエンス (UX) が変更されます。
- `forceRefresh`の`acquireTokenSilent` パラメーターはサポートされていません。 このフラグに関係なく、ブローカーからキャッシュされたトークンを受け取ることがあります。
- ブローカー通信を機能させるには、D-Bus セッション バスが実行されている必要があります。

### Limitations

- Azure AD B2C および Active Directory フェデレーション サービス (AD FS) (AD FS) 機関は、Linux ブローカーを介してサポートされていません。
- サード パーティの ID プロバイダー (IDP) はサポートされていません。
- `microsoft-identity-broker` は、ブローカーが機能するためにデバイスにインストールされている必要があります。
- `msal-node` は、ブローカーが使用できない場合はブラウザーにフォールバックしません。 ブローカーをサポートする環境でのみブローカーを有効にします。
- アクセス トークン所有証明 (PoP) は現在、Linux ブローカーではサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/brokering-macos"} -->
## macOS ブローカーでの MSAL ノードの使用 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/brokering-macos
- Service: msal / msal-node
- Article date: 2026-06-05
- Summary: macOS ブローカーで MSAL Node を使用して、デバイス バインド トークンの取得、SSO の有効化、macOS アプリのブローカー認証の構成を行う方法について説明します。

Microsoft Authentication Library (MSAL) ノードは、macOS 認証ブローカーを使用して、オペレーティング システムに知られているアカウントを使用してシングル サインオン (SSO) とセキュリティで保護されたトークンの取得を提供できます。 この記事では、macOS ブローカーと、MSAL ノードでブローカー認証を有効にして使用する方法について説明します。

すべてのプラットフォームでのブローカー サポートの概要については、 [ネイティブ トークン ブローカーでの MSAL ノードの使用に関するページを](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/brokering)参照してください。

### macOS ブローカーとは

macOS では、認証ブローカーは、ポータル サイト [アプリに](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)付属する **Apple デバイス用の Microsoft Enterprise SSO プラグイン**によって提供されます。 ブローカーは、接続されたアカウントの認証ハンドシェイクとトークンのライフサイクルを管理します。 主な利点は次のとおりです。

- **セキュリティの強化。** セキュリティの強化は、アプリ コードの変更を必要とせずに、ブローカーの更新プログラムを通じて提供されます。 更新トークンはデバイスにバインドされ、流出から保護されます。
- **システム統合。** ユーザーは、ポータル サイトから既存のサインインアカウントを再利用できるため、資格情報の再入力が減ります。
- **トークン保護。** ブローカーは、更新トークンがデバイス コンテキストにバインドされていることを確認します。

### サポートされているプラットフォームとアーキテクチャ

| コンポーネント | サポートされている |
| --- | --- |
| **Architecture** | ARM64 (Apple Silicon) と x64 (Intel) |
| **macOS バージョン** | macOS 10.15 (Catalina) 以降 |

Tip

最新のセキュリティ機能とブローカー機能との互換性を確保するために、最新の macOS バージョンに更新することをお勧めします。

### Prerequisites

- Node.js 18 以降
- 依存関係として `@azure/msal-node-extensions` をインストールする
- デバイスは[ポータル サイト](https://learn.microsoft.com/ja-jp/intune/intune-service/user-help/enroll-your-device-in-intune-macos-cp)経由で登録する必要があります。 登録後、他のMicrosoft アプリが SSO 拡張機能を使用してサインインできることを確認します (たとえば、ポータル サイト経由でWordにサインインできます)。
- アプリの登録にブローカー リダイレクト URI を登録します。 サポートされている URI 値については、「 リダイレクト URI」を参照してください。

### リダイレクト URI

macOS ブローカー フローの場合は、Azure ポータルの**モバイル およびデスクトップ アプリケーション** プラットフォームの下にプラットフォーム固有のリダイレクト URI を登録する必要があります。

**署名されていないアプリケーション (スクリプト、CLI ツール) の場合:**

スクリプトや CLI ツールなどの署名されていないアプリケーションには、次のリダイレクト URI を使用します。

```text
msauth.com.msauth.unsignedapp://auth
```

**署名済み/バンドルされたアプリケーションの場合:**

署名済みまたはバンドルされたアプリケーションには、次のリダイレクト URI 形式を使用します。

```text
msauth.<your-bundle-id>://auth
```

`<your-bundle-id>`をアプリケーションの Apple バンドル識別子 (例: `msauth.com.example.myapp://auth`) に置き換えます。

Important

ブローカー リダイレクト URI は、ブローカー フロー **にのみ** 使用する必要があります。 アプリケーションでブラウザー ベースの認証フローも使用している場合は、個別のリダイレクト URI を使用します。 ブラウザー ベースのフローにブローカー リダイレクト URI を指定すると、エラーが発生します。

### 機能の有効化

macOS ブローカーを有効にするには、Windowsと同じ構成が必要です。 ブローカー構成で `NativeBrokerPlugin` インスタンスを渡します。

```javascript
import { PublicClientApplication, Configuration } from "@azure/msal-node";
import { NativeBrokerPlugin } from "@azure/msal-node-extensions";

const msalConfig: Configuration = {
    auth: {
        clientId: "your-client-id",
    },
    broker: {
        nativeBrokerPlugin: new NativeBrokerPlugin(),
    },
};

const pca = new PublicClientApplication(msalConfig);
```

Note

`msal-node` は、ブローカーが使用できない場合、ブラウザー ベースのフローにフォールバックしません。 予期しないエラーを回避するために、ブローカーをサポートする環境でのみブローカー認証を有効にします。

### トークンの取得

#### 対話型トークンの取得

`acquireTokenInteractive`を使用して、macOS ブローカーを介してトークンを要求します。

```javascript
const tokenRequest = {
    scopes: ["User.Read"],
};

const result = await pca.acquireTokenInteractive(tokenRequest);
console.log("Access token:", result.accessToken);
```

#### サイレント トークンの取得

最初の対話型サインインの後、後続のトークン要求をサイレントモードで行うことができます。

```javascript
const accounts = await pca.getAllAccounts();

if (accounts.length > 0) {
    const silentRequest = {
        scopes: ["User.Read"],
        account: accounts[0],
    };

    const result = await pca.acquireTokenSilent(silentRequest);
    console.log("Access token (silent):", result.accessToken);
}
```

### トークンのキャッシュ

認証ブローカーは、更新とアクセス トークンのキャッシュを処理します。 ブローカーを介して取得されたトークンは、ブローカー自体によって管理され、デバイスバインドされます。 ブローカーを使用するときにカスタム キャッシュを設定する必要はありません。

### macOS ブローカーを使用する場合の違い

- ブローカーが対話を求める必要がある場合は、ネイティブ macOS 認証ダイアログが表示されます。 これにより、ブラウザー ベースの認証と比較してユーザー エクスペリエンス (UX) が変更されます。
- `forceRefresh`の`acquireTokenSilent` パラメーターはサポートされていません。 このフラグに関係なく、ブローカーからキャッシュされたトークンを受け取ることがあります。
- アクセス トークンの所有証明 (PoP) は、ブローカーを通じてサポートされます。

### Limitations

- Azure AD B2C および Active Directory フェデレーション サービス (AD FS) (AD FS) 機関は、macOS ブローカーを介してサポートされていません。
- サード パーティの ID プロバイダー (IDP) はサポートされていません。
- ポータル サイトをインストールし、ブローカーが機能するためにはデバイスを登録する必要があります。
- `msal-node` は、ブローカーが使用できない場合はブラウザーにフォールバックしません。 ブローカーをサポートする環境でのみブローカーを有効にします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/caching"} -->
## MSAL ノードでのトークン キャッシュ - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/caching
- Service: msal / msal-node
- Article date: 2025-05-21
- Summary: MSAL ノードでトークンを効果的にキャッシュし、クライアント シークレットを安全に使用する方法について説明します。

MSAL ノードは、トークンを取得すると、後で使用するためにメモリにキャッシュします。 MSAL ノードは、トークンの有効期間と更新を管理します。 `acquireTokenSilent()`などの API は、特定のアカウントのキャッシュからアクセス トークンを取得します。

MSAL では、セキュリティ上の理由から更新トークンは公開されません。 更新トークンを取得する方法については、 [FAQ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/faq#how-do-i-get-the-refresh-token) を参照してください。

### クライアント シークレットを安全に使用する

クライアント シークレットをハードコーディングしないでください。 dotenv npm パッケージを使用すると、シークレットを *.gitignore* に含める必要がある *.env* ファイル (プロジェクトのルート ディレクトリにあります) にシークレットを格納し、シークレットが誤ってアップロードされるのを防ぐことができます。

```javascript
const msal = require('@azure/msal-node');
require('dotenv').config(); // process.env now has the values defined in a .env file

// Create msal application object
const cca = new msal.ConfidentialClientApplication({
    auth: {
        clientId: "Enter_the_Application_Id_Here", // e.g. "00001111-aaaa-2222-bbbb-3333cccc4444" (guid)
        authority: "https://login.microsoftonline.com/Enter_the_Tenant_Info_Here", // e.g. "common" or your tenantId (guid)
        clientSecret: process.env.clientSecret // obtained during app registration
    }
});

/**
* acquireToken* APIs return an account object containing the "homeAccountId"
* you should keep a record of this in your app and use it later on when calling acquireTokenSilent
*/
const someUserHomeAccountId = "Enter_User_Home_Account_Id";

const msalTokenCache = cca.getTokenCache();
const account = await msalTokenCache.getAccountByHomeId(someUserHomeAccountId);

const silentTokenRequest = {
    account: account,
    scopes: ["User.Read"],
};

cca.acquireTokenSilent(silentTokenRequest).then((response) => {
    // do something with response
}).catch((error) => {
    // catch and handle errors
});
```

運用環境では、ほとんどの場合、トークン キャッシュをシリアル化して保持する必要があります。 アプリケーションの種類に応じて、次のことができます。

- デスクトップ アプリ、コンソール アプリ (パブリック クライアント アプリ (PCA)):
    - [MSAL ノード拡張機能](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/extensions)を使用します。これは、Windows、Linux、Mac OS 上の保存時の永続化と暗号化ソリューションを提供します
- Web アプリ、Web API、デーモン アプリ (機密クライアント アプリ (CCA)):
    - MSAL のインメモリ トークン キャッシュは、運用環境ではスケーリングされません。 分散トークン キャッシュ パターンを使用して、選択したストレージ環境 (Redis、MongoDB、SQL データベースなど) にキャッシュを永続化 -keep、Redis のようなメモリ キャッシュを永続性の第 1 レイヤーとして、SQL データベースを 2 つ目の安定した永続化レイヤーとして同時に使用できることを念頭に置きます。

### メモリ内キャッシュ

MSAL はメモリ内キャッシュを維持します。 メモリ内キャッシュは、アプリケーション キャッシュの状態を代表します。 メモリ内キャッシュの有効期間は、MSAL アプリケーション オブジェクトと同じです。 MSAL を使用するプロセスが再起動すると、プロセスのライフサイクルが完了するとキャッシュは消去されます。 メモリ内キャッシュが空で、キャッシュを復元する永続的なキャッシュがない場合、ユーザーは再認証する必要があります。 この場合、ユーザーがまだ Microsoft Entra ID とのアクティブなセッションを持っている場合は、プロンプトなしで再認証される可能性があります。ただし、これによりユーザー エクスペリエンスが低下します。 サービス間のシナリオ (つまり、クライアント資格情報フロー、代理フロー) も、Microsoft Entra IDからトークンを取得するには HTTP 要求が必要であり、キャッシュからトークンを取得するよりもはるかに低速であるため、問題が発生します。

メモリ内キャッシュはサーバー側アプリケーションではスケーラブルではなく、キャッシュに数 100 個のトークンを保持した後にパフォーマンスが低下することに注意してください。 Web アプリと Web API のシナリオでは、これは約 100 人のユーザーにサービスを提供します。 他のアプリを呼び出すためにクライアント資格情報付与を使用するデーモン アプリのシナリオでは、これは数 100 のテナントを意味します。 詳細については、以下の パフォーマンス を参照してください。

>
>  ⚠️ セキュリティと望ましいキャッシュの寿命の両方のために、すべての運用アプリケーションの**暗号化**を使用してキャッシュを**保持**することをお勧めします。 キャッシュを保持しないことを選択した場合でも、 [TokenCache](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/tokencache) インターフェイスを使用してキャッシュされたエンティティにアクセスできます。

### 永続的キャッシュ

MSAL ノードは、メモリ内キャッシュにアクセスするとイベントを発生させ、アプリはキャッシュを保持するかどうかを選択できます ( [TokenCacheContext](https://azuread.github.io/microsoft-authentication-library-for-js/ref/classes/_azure_msal_node.TokenCacheContext.html) を参照) (ファイル、SQL データベースなど)。 これは、次の 2 つのアクションを構成します。

1. キャッシュにアクセスする前に、永続ストレージから MSAL のメモリ上にキャッシュを読み込む
2. メモリ内キャッシュが前回のアクセス以降に変更された場合は、キャッシュを永続化に戻します

キャッシュを永続化するために、MSAL は [構成](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/configuration)でカスタム キャッシュ プラグインを受け入れます。 このプラグインは [、ICachePlugin](https://azuread.github.io/microsoft-authentication-library-for-js/ref/interfaces/_azure_msal_node.ICachePlugin.html) インターフェイスを実装する必要があります。

```typescript
interface ICachePlugin {
    beforeCacheAccess: (tokenCacheContext: TokenCacheContext) => Promise<void>;
    afterCacheAccess: (tokenCacheContext: TokenCacheContext) => Promise<void>;
}
```

`ICachePlugin` インターフェイスの基本的な実装は次のようになります (サーバー側アプリを構築する場合は、パフォーマンスとセキュリティも参照してください)。

```typescript
class MyCachePlugin implements ICachePlugin {
    private client: ICacheClient;

    constructor(client: ICacheClient) {
        this.client = client; // client object to access the persistent cache
    }

    public async beforeCacheAccess(cacheContext: TokenCacheContext): Promise<void> {
        const cacheData = await this.client.get(); // get the cache from persistence
        cacheContext.tokenCache.deserialize(cacheData); // deserialize it to in-memory cache
    }

    public async afterCacheAccess(cacheContext: TokenCacheContext): Promise<void> {
        if (cacheContext.cacheHasChanged) {
            await this.client.set(cacheContext.tokenCache.serialize()); // deserialize in-memory cache to persistence
        }
    }
}
```

- パブリック クライアント アプリを開発している場合、 [MSAL Node Extensions](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/extensions) はこれを自動的に処理します。
- 機密クライアント アプリを開発している場合は、サーバーごとの単一のキャッシュ インスタンスは、多数の *サーバー* とアプリ インスタンスを持つクラウド環境には適していないため、別のサービスを介してキャッシュを保持する必要があります。

トークン キャッシュは、ディスクに保存するときに暗号化することを強くお勧めします。 パブリック クライアント アプリの場合、これは MSAL ノード拡張機能と共にすぐに使用できます。 ただし、機密クライアントの場合は、適切な暗号化ソリューションを考案する責任があります。

### パフォーマンスとセキュリティ

パブリック クライアント アプリでは、MSAL Node Extensions によってパフォーマンスとセキュリティが保証されます。

ユーザーを処理する機密クライアント アプリ (ユーザーをサインインして Web API を呼び出す Web アプリ、ダウンストリーム Web API を呼び出す Web API) では、特定のアプリケーションに対して多数のユーザーが同時にアクティブになる可能性があります。 ユーザーごとに 1 つのキャッシュ BLOB ( [CacheRecord](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-common/src/cache/entities/CacheRecord.ts) を参照) をシリアル化することをお勧めします。 これは、分散システム全体でキャッシュをスケーリングするのに役立ちます。 キャッシュをパーティション分割するためのキー (*つまり*、**パーティション キー**) を使用します。次に例を示します。

- Web アプリの場合: `<userObjectId>.<tenantId>` (つまり、 `homeAccountId`)
- クライアント資格情報付与を使用するマルチテナント デーモン アプリの場合: `<clientId>.<tenantId>`
- OBO を使用して他の Web API を呼び出す Web API の場合: 受信したアクセス トークンのハッシュ (つまり `oboAssertion`) - その後 OBO トークンと交換されることになるトークン

>
>  ⚠️ 使用状況を監視し [、パフォーマンス](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/performance) の低下を回避する方法の詳細については、パフォーマンスを確認してください。

#### Web アプリ

Web アプリはユーザー向けであり、多くの場合、各ユーザーを追跡するためにセッションに依存するため、キャッシュに適したパーティション キーは多くの場合、セッション データ内に格納され、キャッシュ参照を実行する前に取得する必要があります。 これを支援するために、MSAL Node は [、ICachePlugin](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/distributedcacheplugin) を実装する [DistributedCachePlugin クラスを](https://azuread.github.io/microsoft-authentication-library-for-js/ref/interfaces/_azure_msal_node.ICachePlugin.html)提供します。 `DistributedCachePlugin`のインスタンスには、次のものが必要です。

- 永続化サーバー (Redis、MySQL など) に操作と操作を実装する`get` (`set`)。
- 特定の**セッション ID** に関してキャッシュに対する読み取りと書き込みを行う[パーティション マネージャー](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/ipartitionmanager) (**IPartitionManager**)。

サンプル実装については、 [DistributedCachePlugin を使用した Web アプリ](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/auth-code-distributed-cache) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/caching-in-extensions"} -->
## ノードのMicrosoft認証拡張機能でのキャッシュ - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/caching-in-extensions
- Service: msal / msal-node
- Article date: 2023-10-04
- Summary: Node のMicrosoft認証拡張機能を使用すると、アプリケーション開発者はクロスプラットフォーム トークン キャッシュのシリアル化と永続化を実行できます。 ノード用 Microsoft Authentication Library (MSAL ノード) に対する追加のサポートが提供されます。

Node のMicrosoft認証拡張機能を使用すると、開発者はクロスプラットフォーム トークン キャッシュのシリアル化とディスクへの永続化を実行できます。 ノードのMicrosoft Authentication Library (MSAL) を追加でサポートします。

MSAL ノードでは、既定でメモリ内キャッシュがサポートされ、キャッシュのシリアル化を実行するための ICachePlugin インターフェイスが提供されますが、トークン キャッシュをディスクに格納する既定の方法は提供されません。 ノードのMicrosoft認証拡張機能は、異なるプラットフォーム間でキャッシュをディスクに保持するための既定の実装です。

Node のMicrosoft認証拡張機能では、次のプラットフォームがサポートされています。

- Windows - データ保護 API (DPAPI) が保護に使用されます。
- Mac - Mac キーチェーンが使用されます。
- Linux - LibSecret は、"Secret Service" への格納に使用されます。

### Installation

`msal-node-extensions` パッケージは、Node パッケージ マネージャー (NPM) で使用できます。

```bash
npm i @azure/msal-node-extensions --save
```

### トークン キャッシュを構成する

ノードの認証拡張機能Microsoft使用してトークン キャッシュを構成するコードの例を次に示します。

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

次の表に、永続化構成のすべての引数について説明します。

| フィールド名 | 説明 | 次の場合は必須 |
| --- | --- | --- |
| cachePath | ライブラリが読み取りと書き込みの同期に使用するロック ファイルへのパス | Windows、Mac、Linux |
| dataProtectionScope | 現在のユーザーまたはローカル コンピューター Windowsデータ保護のスコープを指定します。 | Windows |
| serviceName | Mac または Linux で使用するサービス名を指定します | Mac と Linux |
| accountName | Mac または Linux で使用するアカウント名を指定します | Mac と Linux |
| usePlaintextFileOnLinux | LibSecret が失敗した場合に linux で既定でプレーン テキストに設定されるフラグ。 既定値は `false` に設定されます | Linux |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/certificate-credentials"} -->
## MSAL ノードでの証明書資格情報の使用 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/certificate-credentials
- Service: msal / msal-node
- Article date: 2026-03-15
- Summary: MSAL ノードで証明書資格情報を使用する方法について説明します。 証明書を作成、登録、初期化し、安全に使用します。

MSAL Node (Web アプリ、デーモン アプリなど) を使用して、機密クライアント アプリケーションを構築できます。 機密 **クライアントには、クライアント資格情報** が必須です。

### Prerequisites

- [機密クライアント アプリケーションの初期化に関する](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/initialize-confidential-client-application)十分な理解。

MSAL Node (Web アプリ、デーモン アプリなど) を使用して、機密クライアント アプリケーションを構築できます。 機密 **クライアントには、クライアント資格情報** が必須です。 クライアント資格情報は次のようになります。

- `managed identity`: これは証明書なしのシナリオであり、Azure インフラストラクチャを介して信頼が確立されます。 シークレット/証明書の管理は必要ありません。 MSAL はまだこの機能を実装していませんが、代わりに Azure Identity SDK を使用できます。 [Azure リソースのマネージド ID に関するドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/)
- `clientSecret`: アプリの登録中に生成されたシークレット文字列、または既存のアプリケーションの登録後に更新されたシークレット文字列。 運用環境では、これはお勧めしません。
- `clientCertificate`: アプリの登録中に設定された証明書。 MSAL によって生成される [アサーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials) の署名に使用されるため、証明書には秘密キーが必要です。 `thumbprintSha256`は証明書の *X.509 SHA-256* 拇印であり、`privateKey`は PEM でエンコードされた秘密キーです。
- `clientAssertion`: MSAL に [アサーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials#using-a-client-assertion)を作成させる代わりに、アプリ開発者が制御します。 アサーションに追加の要求を追加する場合や、ローカル証明書ではなく署名に KeyVault を使用する場合に便利です。 アサーションの署名に使用する証明書は、アプリ登録時に引き続き設定する必要があります。

注: 1p アプリも `x5c`を送信する必要があります。 これは、*サブジェクト名/発行者認証シナリオ*で使用される [X.509](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/sni) 証明書チェーンです。

### シークレットと証明書を安全に使用する

シークレットはハードコーディングしないでください。 dotenv npm パッケージを使用すると、シークレットの誤アップロードを防ぐために、.gitignore に含める必要がある .env ファイル (プロジェクトのルート ディレクトリにある) にシークレットまたは証明書を格納できます。

証明書は、NodeJS の fs モジュールを介してファイルから読み取ることもできます。 ただし、プロジェクトのディレクトリに保存しないでください。 運用アプリでは、[Azure KeyVault](https://azure.microsoft.com/products/key-vault) またはその他のセキュリティで保護されたキー コンテナーから証明書をフェッチする必要があります。

詳細については、 [証明書とシークレットを](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-app-registration#certificates-and-secrets) 参照してください。

MSAL サンプルを参照してください。 [auth-code-with-certs](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/auth-code-with-certs)

#### 証明書の登録

証明書がない場合は、PowerShell または [keyVault を](https://learn.microsoft.com/ja-jp/powershell/module/pki/new-selfsignedcertificate)[使用して](https://azure.microsoft.com/products/key-vault#layout-container-uida0cf)自己署名証明書Azure作成できます。

証明書を**Microsoft Entra ID**にアップロードする必要があります。

1. [Azure ポータル](https://portal.azure.com)に移動し、Microsoft Entra アプリの登録を選択します。
2. 左側の **[証明書とシークレット** ] ブレードを選択します。
3. [証明書の **アップロード** ] をクリックし、アップロードする証明書ファイル (例: *example.crt*) を選択します。
4. **追加**をクリックします。 証明書がアップロードされると、 *拇印 (SHA-256)*、 *開始日*、 *および有効期限* の値が表示されます。

詳細については、「[証明書をMicrosoft ID プラットフォームに登録する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials#register-your-certificate-with-microsoft-identity-platform)参照してください。

#### 証明書を使用した MSAL ノードの初期化

```javascript
const msal = require('@azure/msal-node');
require('dotenv').config(); // process.env now has the values defined in a .env file

const config = {
    auth: {
        clientId: "YOUR_CLIENT_ID",
        authority: "https://login.microsoftonline.com/YOUR_TENANT_ID",
        clientCertificate: {
            thumbprintSha256: process.env.thumbprint,
            privateKey: process.env.privateKey,
        }
    }
};

// Create msal application object
const cca = new msal.ConfidentialClientApplication(config);
```

`thumbprintSha256`と`privateKey`の両方が文字列である必要があります。 `privateKey` は、さらに次の形式である必要があります (*PKCS#8*)。

```text
-----BEGIN ENCRYPTED PRIVATE KEY-----
MIIJQwIBADANBgkqhkiG9w0BAQEFAASCCS0wggkpAgEAAoICAQDkpKPrsfpIijS3
z2HCpDsa7dxOsKIrm7F1AtGBjyB0yVDjlh/FA7jT5sd2ypBh3FVsZGJudQsLRKfE
// ...
-----END ENCRYPTED PRIVATE KEY-----
```

Note

または、秘密キーは、 `-----BEGIN PRIVATE KEY-----` (暗号化されていない *PKCS#8*) または `-----BEGIN RSA PRIVATE KEY-----` (*PKCS#1*) で始まる場合があります。 これらの形式も許容されます。 互換性のあるキーを PKCS#8 キー型に変換するには、次のコマンドを使用できます。

```bash
openssl pkcs8 -topk8 -inform PEM -outform PEM -in example.key -out example.key
```

秘密キーをパススルーで暗号化した場合 (または秘密キーが既に暗号化されている場合*)、***MSAL ノード**に渡す前に暗号化を解除する必要があります。

**重要**: パスワードをソース コードにハードコーディングしないでください。 証明書の秘密キーと省略可能な descryption パスワードの両方を安全な場所 (例: Azure KeyVault) からフェッチし、Web API を使用して安全にデプロイする必要があります。

これは、Node の [暗号化モジュール](https://nodejs.org/docs/latest-v14.x/api/crypto.html)を使用して行うことができます。 キーを解析してエクスポートするには、 `createPrivateKey()` メソッドを使用します。

```javascript
const fs = require('fs');
const crypto = require('crypto');

const privateKeySource = fs.readFileSync('<path_to_key>/example.key')

const privateKeyObject = crypto.createPrivateKey({
    key: privateKeySource,
    passphrase: process.env.YOUR_PASSPHRASE,
    format: 'pem'
});

const privateKey = privateKeyObject.export({
    format: 'pem',
    type: 'pkcs8'
});
```

#### (省略可能)pfx から pem への変換

OpenSSL は、 *pfx* でエンコードされた証明書ファイルを *pem* に変換するために使用できます。

```bash
    openssl pkcs12 -in certificate.pfx -out certificate.pem
```

変換をプログラムで行う必要がある場合は、サード パーティのパッケージに依存する必要がある場合があります。Node.js にはネイティブメソッドが用意されていないためです。 たとえば、 [node-forge](https://www.npmjs.com/package/node-forge) のような一般的な TLS 実装を使用すると、次のことができます。

```javascript
const forge = require('node-forge');

/**
 * @param {string} pfx: certificate + private key combination in pfx format
 * @param {string} passphrase: passphrase used to encrypt pfx file
 * @returns {Object}
 */
function convertPFX(pfx, passphrase = null) {

    const asn = forge.asn1.fromDer(forge.util.decode64(pfx));
    const p12 = forge.pkcs12.pkcs12FromAsn1(asn, true, passphrase);

    // Retrieve key data
    const keyData = p12.getBags({ bagType: forge.pki.oids.pkcs8ShroudedKeyBag })[forge.pki.oids.pkcs8ShroudedKeyBag]
        .concat(p12.getBags({ bagType: forge.pki.oids.keyBag })[forge.pki.oids.keyBag]);

    // Retrieve certificate data
    const certBags = p12.getBags({ bagType: forge.pki.oids.certBag })[forge.pki.oids.certBag];
    const certificate = forge.pki.certificateToPem(certBags[0].cert)

    // Convert a Forge private key to an ASN.1 RSAPrivateKey
    const rsaPrivateKey = forge.pki.privateKeyToAsn1(keyData[0].key);

    // Wrap an RSAPrivateKey ASN.1 object in a PKCS#8 ASN.1 PrivateKeyInfo
    const privateKeyInfo = forge.pki.wrapRsaPrivateKey(rsaPrivateKey);

    // Convert a PKCS#8 ASN.1 PrivateKeyInfo to PEM
    const privateKey = forge.pki.privateKeyInfoToPem(privateKeyInfo);

    console.log("Converted certificate: \n", certificate);
    console.log("Converted key: \n", privateKey);

    return {
        certificate: certificate,
        key: privateKey
    };
}
```

#### (省略可能)HTTPS サーバーの作成

OAuth 2.0 プロトコルでは、可能な限り HTTPS 接続を使用することをお勧めします。 Azure App Serviceなどのほとんどのクラウド サービスでは、既定でプロキシ経由で HTTPS 接続が提供されます。 テスト目的で独自の HTTPS サーバーをセットアップする場合は、HTTPS サーバーの作成に関するガイダンス Node.js ドキュメントを参照してください。

ブラウザーのセキュリティ ポリシーをバイパスするには、OS の*資格情報マネージャー* / *キー チェーン***に自己署名**証明書を追加する必要もあります。 その後もブラウザーに警告が表示されることがあります (Chrome など)。

- Windowsユーザーの場合は、「[方法: MMC スナップインで証明書を表示する」](https://learn.microsoft.com/ja-jp/dotnet/framework/wcf/feature-details/how-to-view-certificates-with-the-mmc-snap-in)のガイドに従ってください。
- Linux および MacOS ユーザーの場合は、証明書のインストール方法に関するオペレーティング システムのドキュメントを参照してください。

Warning

上記のコマンドを実行するには、 **管理者** 特権が必要な場合があります。

#### 一般的な問題

場合によっては、`AADSTS700027: Client assertion contains an invalid signature` エラーなど、証明書を使用して認証しようとすると、Microsoft Entra IDからエラーが発生し、MSAL ノードの初期化に使用する証明書や秘密キーの形式が正しくないことを示す場合があります。 よくある理由は、MSAL Node に渡している証明書 / 秘密キーの文字列に、*キャリッジ リターン*（`\r`）や *改行*（`\n`）などの予期しない文字が含まれていることです:

```text
-----BEGIN CERTIFICATE-----\nMIIDDzCCAfegAwIBAgIJAMkyzQVK88NHMA0GCSqGSIb3DQEBBQUAMIGCMQswCQYDVQQGEwJTRTESMBAGA1UECBMJU3RvY2tob2xtMQ4wDAYDVQQHEwVLaXN0YTEQMA4G0fbkqbKulrchGbNgkankZtEVg4PGjobZq7B+njvcVa7SsWF/WLq5AUbw==\r\n-----END CERTIFICATE-----
```

または、証明書/キー ファイルに *バッグ属性が*含まれている場合があります。

```text
Bag Attributes
    localKeyID: 28 B5 8E 16 11 88 E9 00 58 D5 76 30 12 B9 59 B8 E4 CE 7C AA
subject=/C=UK/ST=Suffolk/L=Ipswich/O=Example plc/CN=alice
issuer=/C=UK/ST=Suffolk/L=Ipswich/O=Example plc/CN=Certificate Authority/emailAddress=ca@example.com\n
-----BEGIN CERTIFICATE-----
MIIDDzCCAfegAwIBAgIJAMkyzQVK88NHMA0GCSqGSIb3DQEBBQUAMIGCMQswCQYD
VQQGEwJTRTESMBAGA1UECBMJU3RvY2tob2xtMQ4wDAYDVQQHEwVLaXN0YTEQMA4G
0fbkqbKulrchGbNgkankZtEVg4PGjo+Y8MdMjtfSZB29hwYvfMX09jzJ68ZqmpYQ
njvcVtLbEZN5OGCkaslb/f2OxLbsUNgIbws538WnaaufDvKmQe2kUdWmpl9Wn9Bf
bZq7B+njvcVa7SsWF/WLq5AUbw==
-----END CERTIFICATE-----
```

このような場合は、MSAL ノード構成に渡す前に、文字列をクリーニングする必要があります。 次に例を示します。

```javascript
const msal = require('@azure/msal-node');
const fs = require('fs');

const privateKeySource = fs.readFileSync('<path_to_key>/certs/example.key');
const privateKey = Buffer.from(privateKeySource, 'base64').toString().replace(/\r/g, "").replace(/\n/g, "");

const config = {
    auth: {
        clientId: "YOUR_CLIENT_ID",
        authority: "https://login.microsoftonline.com/YOUR_TENANT_ID",
        clientCertificate: {
            thumbprintSha256: process.env.thumbprint,
            privateKey: privateKey,
        }
    }
};

// Create msal application object
const cca = new msal.ConfidentialClientApplication(config);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/configuration"} -->
## MSAL ノードの構成 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/configuration
- Service: msal / msal-node
- Article date: 2026-03-06
- Summary: MSAL ノードを構成する方法について説明します。

MSAL ライブラリには、認証フローの動作をカスタマイズするために使用できる一連の構成オプションがあります。 これらのオプションは、 [PublicClientApplication](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/publicclientapplication) オブジェクトのコンストラクターで、または [要求 API](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/acquire-token-requests) の一部として設定できます。 ここでは、 [PublicClientApplication](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/publicclientapplication) コンストラクターに渡すことができる構成オブジェクトについて説明します。

### Prerequisites

- [アプリ オブジェクトを初期化](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/initialize-public-client-application)する方法をよく理解してください。

### 使用方法

構成オブジェクトは、 `PublicClientApplication` コンストラクターに渡すことができます。 必要な構成パラメーターは、アプリケーションの `client_id` のみです。 それ以外はすべて省略可能ですが、認証フロー、テナント、アプリケーション モデルによっては必要になる場合があります。

サポートされているすべてのパラメーターを持つ [Configuration](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/configuration) オブジェクトを次に示します。

```javascript

// Call back APIs which automatically write and read into a .json file - example implementation
const beforeCacheAccess = async (cacheContext) => {
    cacheContext.tokenCache.deserialize(await fs.readFile(cachePath, "utf-8"));
};

const afterCacheAccess = async (cacheContext) => {
    if(cacheContext.cacheHasChanged){
        await fs.writeFile(cachePath, cacheContext.tokenCache.serialize());
    }
};

// Cache Plugin
const cachePlugin = {
    beforeCacheAccess,
    afterCacheAccess
};;

const msalConfig = {
    auth: {
        clientId: "enter_client_id_here",
        authority: "https://login.microsoftonline.com/common",
        knownAuthorities: [],
        cloudDiscoveryMetadata: "",
        azureCloudOptions: {
            azureCloudInstance: "enter_AzureCloudInstance_here" // AzureCloudInstance enum is exported as a "type",
            tenant: "enter_tenant_info" // defaults to "common"
        }
    },
    cache: {
        cachePlugin // your implementation of cache plugin
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                console.log(message);
            },
            piiLoggingEnabled: false,
            logLevel: msal.LogLevel.Verbose,
        },
    }
}

const msalInstance = new PublicClientApplication(msalConfig);
```

### オプション

#### 認証構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `clientId` | アプリケーションのアプリ ID。 アプリの登録のMicrosoft Entra 管理センターアプリの登録で確認できます。 | UUID/GUID | ありません。 MSAL でアクションを実行するには、このパラメーターが必要です。 |
| `authority` | 認証と承認に使用するテナントの URI。 通常、次の形式をとります。 `https://{uri}/{tenantid}` | テナントを含む URI 形式の文字列 - `https://{uri}/{tenantid}` | `https://login.microsoftonline.com/common` |
| `knownAuthorities` | 有効であることがわかっている URI の配列。 B2C シナリオで使用されます。 | URI 形式の文字列の配列 | 空の配列 `[]` |
| `cloudDiscoveryMetadata` | クラウド検出応答を含む文字列。 Microsoft Entraシナリオで使用されます。 詳細については、「 [パフォーマンス」](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/performance) を参照してください。 | 文字列 | 空の文字列 `""` |
| `authorityMetadata` | .well-known/openid-configuration エンドポイント応答を含む文字列。 詳細については、「 [パフォーマンス」](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/performance) を参照してください。 | 文字列 | 空の文字列 `""` |
| `clientCapabilities` | `xms_cc`要求の一部としてすべてのネットワーク要求に追加する機能の配列 | 文字列の配列 | [] |
| `azureCloudOptions` | 開発者が既定で特定のクラウド機関に対して定義されている Azure クラウド オプションのセット。サポートされている特定のクラウドについては、[AzureCloudInstance](https://azuread.github.io/microsoft-authentication-library-for-js/ref/types/_azure_msal_node.AzureCloudInstance.html) を参照してください | [AzureCloudOptions](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/#azurecloudoptions) | `AzureCloudInstance.None` |

#### キャッシュ構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `cachePlugin` | キャッシュ永続化の読み取りと書き込みのコールバックを含むキャッシュ プラグイン (キャッシュも[参照)](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/caching) | [ICachePlugin](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_node.html#icacheplugin) | null 値 |

#### Broker の構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `nativeBrokerPlugin` | ネイティブ トークン ブローカーを介してトークンを取得するためのブローカー プラグイン (「 [ブローカー](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/brokering)」も参照) | INativeBrokerPlugin | null 値 |

#### システム構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `loggerOptions` | ロガーの構成オブジェクト。 | 以下を参照 してください。 | 以下を参照 してください。 |
| `NetworkClient` | カスタム HTTP 実装 | INetworkModule | [HttpClient.ts](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/src/network/HttpClient.ts) |
| `protocolMode` | 使用するプロトコル モードを表す列挙型。 `"AAD"`場合は、Microsoft Entra v2 エンドポイントで機能します。`"OIDC"`場合は、OIDC 準拠のエンドポイントで機能します。 | 文字列 | `"AAD"` |
| `disableInternalRetries` | true の場合、MSAL は失敗したネットワーク要求を再試行しません | boolean | `false` |

Note

MSAL Node v5 では、 `proxyUrl` パラメーターと `customAgentOptions` パラメーターが削除されました。 アプリケーションでプロキシのサポートが必要な場合は、 [INetworkModule](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/inetworkmodule) を使用してカスタム HTTP クライアントを実装します。 例については、 [カスタム INetworkModule サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/custom-INetworkModule-and-network-tracing) を参照してください。 `protocolMode` パラメーターも認証構成からシステム構成に移動されました。

##### Logger 構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `loggerCallback` | MSAL ステートメントのログ記録を処理するコールバック関数。 | 関数- `loggerCallback: (level: LogLevel, message: string, containsPii: boolean): void` | 上記を参照してください。 |
| `piiLoggingEnabled` | true の場合、個人を特定できる情報 (PII) がログに含まれます。 | boolean | `false` |

#### テレメトリ構成オプション

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `application` | MSAL.js を使用するアプリケーションのテレメトリ オプション | 以下を参照 してください | 以下を参照 してください |

##### アプリケーション テレメトリ

| オプション | 説明 | Format | デフォルト値 |
| --- | --- | --- | --- |
| `appName` | アプリケーションの一意の文字列名 | 文字列 | 空の文字列 "" |
| `appVersion` | MSAL を使用したアプリケーションのバージョン | 文字列 | 空の文字列 "" |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/extensions"} -->
## Microsoft の Node.js 向け認証拡張機能 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/extensions
- Service: msal / msal-node
- Article date: 2025-05-21
- Summary: ノードのMicrosoft認証拡張機能を使用して、クロスプラットフォーム トークン キャッシュのシリアル化と永続化を実行する方法について説明します。

Node 用のMicrosoft認証拡張機能は、クライアント アプリケーションがクロスプラットフォーム トークン キャッシュのシリアル化と永続化を実行するためのセキュリティで保護されたメカニズムを提供します。

MSAL ノードでは、開発者はトークン キャッシュを保持するための独自のロジックを実装する必要があります。 MSAL Node 拡張機能は、パブリック クライアント アプリケーション (デスクトップ クライアント、CLI アプリケーションなど) に対して、Windows、Mac、Linux 全体で、堅牢でセキュリティで保護された構成可能なトークン キャッシュの永続性の実装を提供することを目的としています。 複数のプロセスが同時にトークン キャッシュにアクセスするだけでなく、暗号化するためのメカニズムも提供します。

サポートされているプラットフォームは、Windows、Mac、Linux です。

- Windows - DPAPI は暗号化に使用されます。
- MAC - MAC KeyChain は npm keytar を介して使用されます。
- Linux - LibSecret は、npm keytar を介して "Secret Service" に格納するために使用されます。

### Code

#### 永続化レイヤーの作成

永続化レイヤーを作成するための API は、対象とするプラットフォームによって異なります。

または、`createPersistence` の  API を汎用ラッパーとして使用し、プラットフォーム/OS に基づいて適切な永続化方法を選択することもできます。

```javascript
const { PublicClientApplication } = require("@azure/msal-node");
const {
  DataProtectionScope,
  PersistenceCreator,
  PersistenceCachePlugin,
} = require("@azure/msal-node-extensions");

const persistence = await PersistenceCreator.createPersistence({
                cachePath: "path/to/cache/file.json",
                dataProtectionScope: DataProtectionScope.CurrentUser,
                serviceName: "test-msal-electron-service",
                accountName: "test-msal-electron-account",
                usePlaintextFileOnLinux: false,
          });
// Use the persistence object to initialize an MSAL PublicClientApplication with cachePlugin
const pca = new PublicClientApplication({
                auth: {
                        clientId: "CLIENT_ID_HERE",
                    },
                cache: {
                        cachePlugin: new PersistenceCachePlugin(persistence);
                    },
                });
```

または、以下のプラットフォーム固有のオプションを使用できます。

## [Windows](#tab/windows)
```javascript

const { FilePersistenceWithDataProtection, DataProtectionScope } = require("@azure/msal-node-extensions");
const { PublicClientApplication } = require("@azure/msal-node");

const cachePath = "path/to/cache/file.json";
const dataProtectionScope = DataProtectionScope.CurrentUser;
const optionalEntropy = ""; //specifies password or other additional entropy used to encrypt the data.
const windowsPersistence = await FilePersistenceWithDataProtection.create(cachePath, dataProtectionScope, optionalEntropy);
// Use the persistence object to initialize an MSAL PublicClientApplication with cachePlugin
const pca = new PublicClientApplication({
                auth: {
                        clientId: "CLIENT_ID_HERE",
                    },
                cache: {
                        cachePlugin: new PersistenceCachePlugin(windowsPersistence);
                    },
                });

```

- `cachePath` は、暗号化されたキャッシュ ファイルが格納されるファイル システム内のパスです。
- `dataProtectionScope` は、現在のユーザーまたはローカル コンピューターのデータ保護のスコープを指定します。 データを保護または保護解除するためのキーは必要ありません。 スコープを CurrentUser に設定すると、資格情報で実行されているアプリケーションのみがデータの保護を解除できます。ただし、これは、資格情報で実行されているアプリケーションが保護されたデータにアクセスできることを意味します。 スコープを LocalMachine に設定すると、コンピューター上のすべての完全信頼アプリケーションで、データの保護解除、アクセス、変更を行うことができます。
- `optionalEntropy` は、データの暗号化に使用されるパスワードまたはその他の追加エントロピを指定します。

`FilePersistenceWithDataProtection`では、Win32 CryptProtectData API と CryptUnprotectData API が使用されます。 dataProtectionScope または optionalEntropy の詳細については、これらの API のドキュメントを参照してください。

## [Mac](#tab/mac)
```javascript
const { KeychainPersistence } = require("@azure/msal-node-extensions");

const cachePath = "path/to/cache/file.json";
const serviceName = "test-msal-electron-service";
const accountName = "test-msal-electron-account";
const macPersistence = await KeychainPersistence.create(cachePath, serviceName, accountName);
// Use the persistence object to initialize an MSAL PublicClientApplication with cachePlugin
const pca = new PublicClientApplication({
                auth: {
                        clientId: "CLIENT_ID_HERE",
                    },
                cache: {
                        cachePlugin: new PersistenceCachePlugin(macPersistence);
                    },
                });

```

`cachePath`はキャッシュが格納される場所ではありません。 代わりに、拡張機能はこのファイルをダミー データで更新してファイルの更新時刻を更新し、キーチェーンの内容を読み込むかどうかを確認します。 ロック ファイルの場所としても使用されます。 キャッシュがキーチェーンに格納されるサービス名。 キャッシュがキーチェーンに格納されるアカウント名。

## [Linux](#tab/linux)
```javascript
const { LibSecretPersistence } = require("@azure/msal-node-extensions");

const cachePath = "path/to/cache/file.json";
const serviceName = "test-msal-electron-service";
const accountName = "test-msal-electron-account";
const linuxPersistence = await LibSecretPersistence.create(cachePath, serviceName, accountName);
// Use the persistence object to initialize an MSAL PublicClientApplication with cachePlugin
const pca = new PublicClientApplication({
                auth: {
                        clientId: "CLIENT_ID_HERE",
                    },
                cache: {
                        cachePlugin: new PersistenceCachePlugin(linuxPersistence);
                    },
                });

```

cachePath は、キャッシュが格納される場所 **ではありません** 。 代わりに、拡張機能はこのファイルをダミー データで更新してファイルの更新時刻を更新し、シークレット サービス (Gnome Keyring など) の内容が読み込まれるかどうかを確認します。 ロック ファイルの場所としても使用されます。

- キャッシュがシークレットサービスに格納される際のサービス名。
- キャッシュがシークレット サービスに格納されるアカウント名。

---

### すべてのプラットフォーム

暗号化されていないファイル永続化は、すべてのプラットフォームで機能しますが、推奨されませんが、便宜上提供されます。

```javascript
const { FilePersistence } = require("@azure/msal-node-extensions");

const filePath = "path/to/cache/file.json";
const filePersistence = await FilePersistence.create(filePath, loggerOptions);
// Pass the persistence to msal config's cachePlugin
const pca = new PublicClientApplication({
    auth: {
            clientId: "CLIENT_ID_HERE",
        },
    cache: {
            cachePlugin: new PersistenceCachePlugin(filePersistence);
        },
  });

```

ファイルまたはディレクトリが作成されていない場合、 `FilePersistence.create()` はファイルとパス内の任意のディレクトリを再帰的に作成します。 これは、[FilePersistence.ts](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/master/extensions/msal-node-extensions/src/persistence/FilePersistence.ts#L18)の動作で確認できます。

#### コンカレンシーのためにキャッシュ プラグインにロック オプションを渡す

前の手順で作成した永続化オブジェクトを渡して、 `PersistenceCachePlugin`を作成します。

```js
const { PersistenceCachePlugin } = require("@azure/msal-node-extensions");

const persistenceCachePlugin = new PersistenceCachePlugin(windowsPersistence); // or any of the other ones.
```

複数のプロセスによる同時アクセスをサポートするために、拡張機能はファイル ベースのロックを使用します。 `CrossPlatformLockOptions`を使用して、ロック取得の再試行回数と再試行遅延を構成できます。

```js
const {
  PersistenceCreator,
  PersistenceCachePlugin,
} = require("@azure/msal-node-extensions");

const lockOptions = {
    retryNumber: 100,
    retryDelay: 50
}

const persistence = await PersistenceCreator.createPersistence(persistenceConfiguration);
const persistenceCachePlugin = new PersistenceCachePlugin(persistence, lockOptions); // or any of the other ones
const pca = new PublicClientApplication({
    auth: {
            clientId: "CLIENT_ID_HERE",
        },
    cache: {
            cachePlugin: persistenceCachePlugin
        },
    });

```

#### MSAL ノード `PublicClientApplication` 構成での PersistenceCachePlugin の設定 (例を含む)

要約すると、MSAL ノード `PersistenceCachePlugin`で設定できる`PublicClientApplication`が作成されたら、次に示すように[構成](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/configuration)オブジェクトの一部として設定します。

```javascript
import { PublicClientApplication } from "@azure/msal-node";

const publicClientConfig = {
    auth: {
        clientId: "",
        authority: "",
    },
    cache: {
        cachePlugin: persistenceCachePlugin
    },
};

const pca = new PublicClientApplication(publicClientConfig);
```

例 (Electron node-js デスクトップ アプリの場合):-

authConfig.js:-

```js
const AAD_ENDPOINT_HOST = "https://login.microsoftonline.com/"; // include the trailing slash
const REDIRECT_URI = "ENTER_REDIRECT_URI";

const cachePath = "path/to/cache/file.json";

/*define persistence config based on the appropriate persistence you are using(e.g- FilePersistenceWithDataProtection, generic PersistenceCreateor, etc)*/

//defining persistence config for PersistenceCreator
const persistenceConfiguration = {
    cachePath,
    dataProtectionScope: DataProtectionScope.CurrentUser,
    serviceName: "test-msal-electron-service",
    accountName: "test-msal-electron-account",
    usePlaintextFileOnLinux: false,
}

  const msalConfig = {
    auth: {
        clientId: "CLIENT_ID_HERE",
        authority: `${AAD_ENDPOINT_HOST}TENANT_ID_HERE`,
    },
    cache: {
        cachePlugin: null // set later in main.js as shown above 
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
...

module.exports = {
  msalConfig: msalConfig,
  protectedResources: protectedResources,
  REDIRECT_URI: REDIRECT_URI,
  persistenceConfiguration
};

```

#### Electron 開発者向けノート

電子サンプル: この [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/extensions/samples/electron-webpack) は、msal-node-extensions ライブラリを、webpack にバンドルされている電子アプリケーションに統合する方法を示しています。

Electron にこの拡張機能を使用している場合は、次のようなエラーが発生する可能性があります。

```
Uncaught Exception:
Error: The module
"<path-to-project>\node_modules\...\dpapi.node" was compiled against a different Node.js version using NODE_MODULE_VERSION 85. This version of Node.js requires NODE_MODULE_VERSION 80. Please try re-compiling or re-installing the module...."
```

このエラーは、Electron プロジェクトと拡張機能 Node.js バージョンの違いが原因である可能性があります。 これは、次の手順でパッケージをビルドし直すことで処理できます。

- まだインストールしていない場合は、コマンド `electron-rebuild`を使用して`npm i -D electron-rebuild`をインストールします。
- `packages-lock.json` が存在する場合は、プロジェクトから削除します
- `./node_modules/.bin/electron-rebuild` を実行します。

#### Samples

1. [永続化のための Electron-webpack サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/extensions/samples/electron-webpack)
2. [Msal-node 拡張機能のサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/extensions/samples/msal-node-extensions)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/faq"} -->
## MSAL ノードについてよく寄せられる質問 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/faq
- Service: msal / msal-node
- Article date: 2026-03-06
- Summary: MSAL Node についてよく寄せられる質問について説明します。

### General

#### MSAL ノードはいつ使用されますか?

MSAL Node では、パブリック/コンフィデンシャル アプリのサーバー ベースの認証がサポートされています。 これは、認証を必要とするサーバー ベースの認証シナリオ/Web API に適用できます。 サポートされているシナリオの完全な一覧[については、こちらを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/master/lib/msal-node#scenarios-supported)参照してください。サポートされているフローは[次のとおりです](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/master/lib/msal-node#oauth20-grant-types-supported)。

#### ADAL ノードの状態は何ですか? 移行ガイドは利用できますか?

ADAL ノードは現在メインタンタンスであり、すべてのユーザーに MSAL ノードへの移行をお勧めします。 MSAL ノードは、ADAL ノードを完全に置き換えるために設計されています。 ADAL から MSAL に移行する場合は、移行に役立つ [移行ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/migration) が提供されています。 これは古い機能の完全な見直しであるため、すべてのアプリがスムーズな移行を行っていない可能性があることに注意してください。

#### サポートされているサービスは何ですか?

MSAL ノードでは、Microsoft Entra ID、MSA、ADFS、B2C がサポートされます。 サンプルでは、 [ここで](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/standalone-samples)の使用方法を示します。 MSAL Node では、 [シングル テナント アプリとマルチテナント アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps)もサポートされます。

注: ADFS は現在サポートされています。スタンドアロン サンプルはまだ公開されていません。 近日中の更新については、こちらをご確認ください。

#### パブリック アプリまたは機密アプリとは アプリの登録時に知っておくべきこと

MSAL の[基本](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node#msal-basics)でこれを確認してください

#### "authority" と "azureCloudOptions" を指定した場合、機関文字列の既定値は何ですか?

開発者が `azureCloudOptions`を提供した場合、MSAL.js は `authority`で指定された値を上書きします。 MSAL.js は、`request`よりも`configuration`で指定されたパラメーターを優先します。 構成で`azureCloudOptions`が設定されている場合は、`authority`の`request`よりも優先されることに注意してください。 開発者はこれを上書きする必要がある場合は、`azureCloudOptions`で`request`を設定する必要があります。

#### トークンの有効期間は何ですか?

- Microsoft Entra: Microsoft Entraの最新のリファレンス[については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)。 特定のトークンの種類に対して構成可能な機能の一部が最近廃止されることに注意してください。
- B2C: B2C トークンの有効期間に関するガイダンス [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/tokens-overview#configuration)

#### 更新トークンを取得する方法

MSAL ノードは、セキュリティ上の理由から更新トークンを公開しません。 代わりに、キャッシュを介して更新トークンを管理し、必要に応じて更新して、開発者の対応する ID トークンとアクセス トークンをフェッチします。 適切な `acquireToken*` API を使用してアクセス トークンを取得すると、MSAL によって必要に応じて更新されます。 他の方法で取得した更新トークンがある場合は、 [acquireTokenByRefreshToken](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/confidentialclientapplication#@azure-msal-node-confidentialclientapplication-acquiretokenbyrefreshtoken) API を使用できます ( [「更新トークンのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/refresh-token)」も参照)。 Microsoft Entra トークンの詳細[については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens)。

#### Electron はサポートされていますか?

Yes. [MSAL ノードのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples)を参照してください。

#### 対話型フローはサポートされていますか?

Yes. MSAL ノードは、承認コード フローの両方の足を処理する`acquireTokenInteractive()`に対する`PublicClientApplication` API を提供します。 ユーザーがサインインするためのブラウザー ウィンドウが開き、 `AuthenticationResult`が返されます。 実装例については、 [トークン要求の取得](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/acquire-token-requests) に関するドキュメントと [auth-code-cli-app サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/auth-code-cli-app) を参照してください。

#### SPA は MSAL ノードでサポートされていますか?

SPA ベースのユース ケースについては、 [MSAL ブラウザー](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser) を参照してください。 MSAL ノードは、デスクトップ アプリ、Web アプリ、Web API、またはサーバー側の認証シナリオに対して選択する必要があります。

#### MSAL Node 拡張機能とは キャッシュ プラグインとは

MSAL Node 拡張機能は、クライアント アプリケーションがクロスプラットフォーム トークン キャッシュのシリアル化と永続化を実行するためのセキュリティで保護されたメカニズムを提供する MSAL Node のサポート ライブラリです。 使用方法、サンプルなどについては、 [こちらをご覧ください](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/extensions)

#### MSAL Node 拡張機能で提供されるキャッシュ プラグインは、Electron アプリケーションで使用できますか?

はい、できます。 ノード バージョンに関連する問題が発生した場合は、トラブルシューティングの手順を示すこの [メモ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/extensions#note-for-electron-developers) を参照してください。

#### サポートされている Node.js のバージョンは何ですか? アクティブな開発 Node.js バージョンを使用する場合、インストール エラーをバイパスするにはどうすればよいですか?

MSAL Node では、 [ここに](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node#node-version-support)記載されているように、番号付けされた安定した LTS リリースも正式にサポートされています。

この問題を回避する場合は、次の点に注意してください。

- **Yarn**: `--ignore-engines` フラグを `yarn` コマンドに渡します。
- **npm**: .npmrc ファイルに `engine-strict=false` を追加します。

Important

MSAL Node v5 には 20 以降 Node.js 必要です。 Node.js 16 と 18 はサポートされなくなりました。

#### MSAL Node でセルフサービス サインアップを実装するにはどうすればよいですか?

MSAL Node では、認証コード フローでのセルフサービス サインアップがサポートされます。 要求でサポートされているプロンプト値とその期待される結果については、[こちらの](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/authorizationurlrequest)ドキュメントを参照してください。また、Azure テナントに対して行う必要があるセルフサービス サインアップと構成の変更の概要については、[こちらを](https://aka.ms/s3u)参照してください。 B2C およびテスト環境では、セルフサービス サインアップは使用できません。

#### プロキシの背後でアプリが実行されているときにアプリが正しく機能しないのはなぜですか?

MSAL Node v5 の時点では、プロキシ構成はカスタム HTTP クライアントを介して処理されます。 プロキシのサポートを使用して [INetworkModule](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/inetworkmodule) をインスタンス化して独自のネットワーク クライアントを実装し、`networkClient`のとして提供します。 例については、 [カスタム INetworkModule サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/custom-INetworkModule-and-network-tracing) を参照してください。

#### MSAL ノードにカスタム http(s) エージェントを実装するにはどうすればよいですか?

MSAL Node v5 の時点で、 `customAgentOptions` パラメーターは削除されました。 カスタム HTTP(S) エージェントを使用するには、 [INetworkModule](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/inetworkmodule) をインスタンス化し、その中でエージェントを構成することで、独自のネットワーク クライアントを実装します。 `networkClient`のとして、カスタム ネットワーク クライアントを指定します。 例については、 [カスタム INetworkModule サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/custom-INetworkModule-and-network-tracing) を参照してください。

### B2C

#### パスワード リセット ユーザー フローを処理する方法

[新しいパスワード リセット エクスペリエンス](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/add-password-reset-policy?pivots=b2c-user-flow#self-service-password-reset-recommended)が、サインアップまたはサインイン ポリシーの一部になりました。 ユーザーが **[パスワードを忘れた場合]** リンクを選択すると、すぐにパスワードを忘れた場合のエクスペリエンスが表示されます。

新しいパスワード リセット エクスペリエンスに移行することをお勧めします。これにより、アプリの状態が簡略化され、ユーザー側でのエラー処理が減ります。 何らかの理由で従来のパスワード リセット ユーザー フローを使用する必要がある場合は、ユーザーが [`AADB2C90118`場合] リンクを選択したときに B2C サービスから返されるエラー コードを処理する必要があります。 これを行う方法については、サンプル「[MSAL Node B2C Web アプリのサンプル (認証コードを使用)](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/b2c-user-flows)」を参照してください。

### Compatibility

### Microsoft Graph JavaScript SDK で MSAL ノードを使用できますか?

はい。MSAL Node は、[Microsoft Graph JavaScript SDK](https://github.com/microsoftgraph/msgraph-sdk-javascript) のカスタム認証プロバイダーとして使用できます。 実装については、サンプル「[Express Web App の呼び出しGraph API](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/tree/main/2-Authorization/1-call-graph)」を参照してください。

### コマンド ラインを使用して MSAL Node アプリをプロビジョニングできますか?

はい。これを行うための新しい [Powershell Graph SDK](https://github.com/microsoftgraph/msgraph-sdk-powershell) をお勧めします。 たとえば、次のスクリプトでは、ユーザーが指定したテナント内のMicrosoft Graphのカスタム リダイレクト URI (**Mobile アプリとデスクトップ アプリ** ( *InstalledClient* とも呼ばれます) と **User.Read** アクセス許可を持つMicrosoft Entra アプリケーションを作成し、このアプリケーション オブジェクトに基づいて同じテナントにサービス プリンシパルをプロビジョニングします。

```Powershell
Import-Module Microsoft.Graph.Applications

Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

Connect-MgGraph -TenantId "ENTER_TENANT_ID_HERE" -Scopes "Application.ReadWrite.All"

# User.Read delegated permission for Microsoft Graph
$mgUserReadScope = @{
    "Id" = "e1fe6dd8-ba31-4d61-89e7-88639da4683d" # permission Id
    "Type" = "Scope"
}

# Add additional permissions to array below
$mgResourceAccess = @($mgUserReadScope)

[object[]]$requiredResourceAccess = @{
    "ResourceAppId" = "00000003-0000-0000-c000-000000000000" # MS Graph App Id
    "ResourceAccess" = $mgResourceAccess
}

# Create the application
$msalApplication = New-MgApplication -displayName myMsalDesktopApp `
    -SignInAudience AzureADMyOrg `
    -PublicClient @{RedirectUris = "msal://redirect"} `
    -RequiredResourceAccess $requiredResourceAccess

# Provision the service principal
New-MgServicePrincipal -AppId $msalApplication.AppId
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/initialize-confidential-client-application"} -->
## MSAL ノードで機密クライアント アプリケーションを初期化する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/initialize-confidential-client-application
- Service: msal / msal-node
- Article date: 2026-03-15
- Summary: シークレット、証明書、およびセキュリティで保護された構成を使用して MSAL ノードで ConfidentialClientApplication を初期化する方法について説明します

この記事では、MSAL ノードで `ConfidentialClientApplication` オブジェクトを初期化する方法について説明します。 シークレットと証明書を安全に使用する方法と、機関を構成する方法について説明します。

### Prerequisites

アプリケーションを初期化する前に、まずアプリケーションを[Microsoft Entra 管理センターに登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-registration)し、アプリケーションとMicrosoft ID プラットフォームの間に信頼関係を確立する必要があります。

アプリを登録した後、Microsoft Entra 管理センターで見つかる次の値の一部またはすべてが必要になります。

| 価値 | 必須 | 説明 |
| --- | --- | --- |
| アプリケーション (クライアント) ID | 必須 | Microsoft ID プラットフォーム内でアプリケーションを一意に識別する GUID。 |
| 権威 | Optional | アプリケーションの ID プロバイダー URL ( *インスタンス*) と *サインイン対象ユーザー* 。 インスタンスとサインイン対象ユーザーが連結されると、権限が構成 *されます*。 |
| ディレクトリ (テナント) ID | Optional | 組織専用の基幹業務アプリケーションを構築する場合は、ディレクトリ (テナント) ID を指定します(多くの場合、 *シングルテナント アプリケーション*と呼ばれます)。 |
| リダイレクト URI | Optional | Web アプリを構築する場合、`redirectUri`は、ID プロバイダー (Microsoft ID プラットフォーム) が発行したセキュリティ トークンを返す場所を指定します。 |

### `ConfidentialClientApplication` オブジェクトの初期化

MSAL ノードを使用するには、 [ConfidentialClient](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/confidentialclientapplication) オブジェクトをインスタンス化する必要があります。

#### シークレットと証明書を安全に使用する

シークレットはハードコーディングしないでください。 dotenv npm パッケージを使用すると、シークレットの誤アップロードを防ぐために *、.gitignore* に含める必要がある .env ファイル (プロジェクトのルート ディレクトリにある) にシークレットまたは証明書を格納できます。

証明書は、NodeJS の fs モジュールを介してファイルから読み取ることもできます。 ただし、プロジェクトのディレクトリに保存しないでください。 運用アプリでは、[Azure KeyVault](https://azure.microsoft.com/products/key-vault) またはその他のセキュリティで保護されたキー コンテナーから証明書をフェッチする必要があります。

詳細については、 [証明書とシークレットを](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-app-registration#certificates-and-secrets) 参照してください。

MSAL サンプルを参照してください。 [auth-code-with-certs](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/master/samples/msal-node-samples/auth-code-with-certs)

```javascript
import * as msal from "@azure/msal-node";
import "dotenv/config"; // process.env now has the values defined in a .env file

const clientAssertionCallback = async (config) => {
    // network request that uses config.clientId and (optionally) config.tokenEndpoint
    const result = await Promise.resolve(
        "network request which gets assertion"
    );
    return result;
};

const clientConfig = {
    auth: {
        clientId: "your_client_id",
        authority: "your_authority",
        clientSecret: process.env.clientSecret, // OR
        clientCertificate: {
            thumbprintSha256: process.env.thumbprint,
            privateKey: process.env.privateKey,
        }, // OR
        clientAssertion: clientAssertionCallback, // or a predetermined clientAssertion string
    },
};
const cca = new msal.ConfidentialClientApplication(clientConfig);
```

[証明書をインポートするときの一般的な問題を](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/certificate-credentials#common-issues)参照してください。

### 構成の基本

ノードの[構成](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/configuration)オプションには、認証フローごとに`common`パラメーターと`specific`パラメーターがあります。

- `clientId` は、パブリック クライアント アプリケーションを初期化するために必須です
- `authority`構成中にユーザーが設定しない場合、既定値は `https://login.microsoftonline.com/common/`
- 機密クライアントには、クライアント資格情報が必須です。 クライアント資格情報は次のいずれかです:
    - `clientSecret` は、アプリの登録時に生成されるシークレット文字列です。
    - `clientCertificate` は、アプリの登録に設定された証明書です。 `thumbprintSha256`は証明書の X.509 SHA-256 拇印であり、`privateKey`は PEM でエンコードされた秘密キーです。 `x5c` は、 [サブジェクト名/発行者認証シナリオ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/sni)で使用されるオプションの X.509 証明書チェーンです。
    - `clientAssertion` は、アサーション文字列または、アプリケーションがトークンを要求するときに使用するアサーション文字列と、アサーションの型 (urn:ietf:params:oauth:client-assertion-type:jwt-bearer) を返すコールバック関数を含む ClientAssertion オブジェクトです。 コールバックは、MSAL がトークン発行者からトークンを取得する必要があるたびに呼び出されます。 アサーションの有効期限が切れ、新しいアサーションを作成する必要があるため、通常、アプリ開発者はコールバックを使用する必要があります。 アプリ開発者は、アサーションの有効期間について責任を負います。 フェデレーション ID 資格情報を使用してダウンストリーム API のトークンを取得するには、 [このメカニズム](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-create-trust) を使用します。

[構成](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/configuration)に関するその他のオプションについては、「[MSAL ノードの構成」](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/configuration)を参照してください。

### 権限の構成

既定では、MSAL は `common` テナントで構成されます。これは、(B2C ではなく) 個人アカウントを許可するマルチテナント アプリケーションおよびアプリケーションに使用されます。

```javascript
    authority: 'https://login.microsoftonline.com/common/'
```

アプリケーションの対象が単一テナントである場合は、次のようにテナント ID を含む authority を指定する必要があります。

```javascript
    authority: 'https://login.microsoftonline.com/{your_tenant_id}'
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/initialize-public-client-application"} -->
## MSAL ノードでパブリック クライアント アプリケーション オブジェクトを初期化する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/initialize-public-client-application
- Service: msal / msal-node
- Article date: 2025-05-21
- Summary: MSAL ノードで PublicClientApplication オブジェクトを初期化する方法と、権限を構成する方法について説明します。

この記事では、MSAL ノードで `PublicClientApplication` オブジェクトを初期化する方法と、権限を構成する方法について説明します。

### Prerequisites

アプリケーションを初期化する前に、まずアプリケーションを[Microsoft Entra 管理センターに登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-registration)し、アプリケーションとMicrosoft ID プラットフォームの間に信頼関係を確立する必要があります。

アプリを登録した後、Microsoft Entra 管理センターで見つかる次の値の一部またはすべてが必要になります。

| 価値 | 必須 | 説明 |
| --- | --- | --- |
| アプリケーション (クライアント) ID | 必須 | Microsoft ID プラットフォーム内でアプリケーションを一意に識別する GUID。 |
| 権威 | Optional | アプリケーションの ID プロバイダー URL ( *インスタンス*) と *サインイン対象ユーザー* 。 インスタンスとサインイン対象ユーザーが連結されると、権限が構成 *されます*。 |
| ディレクトリ (テナント) ID | Optional | 組織専用の基幹業務アプリケーションを構築する場合は、ディレクトリ (テナント) ID を指定します(多くの場合、 *シングルテナント アプリケーション*と呼ばれます)。 |
| リダイレクト URI | Optional | Web アプリを構築する場合、`redirectUri`は、ID プロバイダー (Microsoft ID プラットフォーム) が発行したセキュリティ トークンを返す場所を指定します。 |

### PublicClientApplication オブジェクトの初期化

MSAL ノードを使用するには、 [PublicClientApplication](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/publicclientapplication) オブジェクトをインスタンス化する必要があります。 すべての PublicClientApplication に対して、[PKCE](https://tools.ietf.org/html/rfc7636#section-6.2)（コード交換用証明キー）の使用をサポートしており、強く推奨しています。 使用パターンは [PKCE サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/master/samples/msal-node-samples/auth-code-pkce)で示されています。

```javascript
import * as msal from "@azure/msal-node";

const clientConfig = {
    auth: {
        clientId: "your_client_id",
        authority: "your_authority",
    }
};
const pca = new msal.PublicClientApplication(clientConfig);
```

### 構成の基本

ノードの[構成](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/configuration)オプションには、認証フローごとに`common`パラメーターと`specific`パラメーターがあります。

- `client_id` は、パブリック クライアント アプリケーションを初期化するために必須です
- `authority`構成中にユーザーが設定しない場合、既定値は `https://login.microsoftonline.com/common/`

[構成](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/configuration)に関するその他のオプションについては、「[MSAL ノードの構成」](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/configuration)を参照してください。

### 権限の構成

既定では、MSAL は `common` テナントで構成されます。これは、(B2C ではなく) 個人アカウントを許可するマルチテナント アプリケーションおよびアプリケーションに使用されます。

```javascript
const msalConfig = {
    auth: {
        clientId: 'your_client_id',
        authority: 'https://login.microsoftonline.com/common/'
    }
};
```

アプリケーションの対象が単一テナントである場合は、次のように、テナント ID を含む authority を指定する必要があります。

```javascript
const msalConfig = {
    auth: {
        clientId: 'your_client_id',
        authority: 'https://login.microsoftonline.com/{your_tenant_id}'
    }
};
```

アプリケーションで `"https://login.live.com"` や IdentityServer などの OIDC 準拠の別の機関を使用している場合は、 `knownAuthorities` フィールドに指定し、 `protocolMode` を `"OIDC"` に設定する必要があります。

```javascript
const msalConfig = {
    auth: {
        clientId: 'your_client_id',
        authority: 'https://login.live.com',
        knownAuthorities: ["login.live.com"],
        protocolMode: "OIDC"
    }
};
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/key-vault-managed-identity"} -->
## Azure Key VaultとAzureマネージド ID を使用した MSAL Node アプリの資格情報のセキュリティ保護 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/key-vault-managed-identity
- Service: msal / msal-node
- Article date: 2025-05-21
- Summary: Azure Key VaultとAzureマネージド ID を使用して MSAL Node アプリの資格情報をセキュリティで保護する方法について説明します。

この記事では、Azure Key Vaultを使用して、Node.js アプリケーションで証明書などの機密情報を安全に管理およびアクセスする方法について説明します。 キー コンテナーを作成し、それに証明書をインポートし、Azure SDKを使用してこれらの資格情報にアクセスする方法について説明します。 OpenSSL を使用して証明書形式を変換する方法と、Node.js アプリケーションのキー コンテナーから証明書をフェッチする方法について説明します。

### Prerequisites

- [Node.js](https://nodejs.org/en/download/)
- [MSAL ノードでの証明書資格情報の使用に関する](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/certificate-credentials)理解。

### Azure Key Vault の使用

機密情報はソース コードに格納しないでください。 このセクションでは、Azure SDKを使用してキー コンテナーを作成し、そこから資格情報にアクセスする方法について説明します。 実装については、 [auth-code-key-vault](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/master/samples/msal-node-samples/auth-code-key-vault) のコード サンプルを参照してください。

#### キー コンテナーを作成して証明書をインポートする

まず、キー コンテナーを作成します。 これを行うには、「[クイック スタート: Azure ポータルを使用してキー コンテナーを作成](https://learn.microsoft.com/ja-jp/azure/key-vault/general/quick-create-portal#create-a-vault)する」ガイドに従います

Azure Key Vault**は、証明書**に加えて、シークレットやその他の機密情報 (データベース接続文字列など) の格納にも使用できます。

これで、証明書をKey Vaultにインポートできます。 **Azure Key Vault**では、次のいずれかの証明書が必要です。

- *.pem* ファイル形式には、1 つ以上の X509 証明書ファイルが含まれています。
- *.pfx* ファイル形式は、複数の暗号化オブジェクトを 1 つのファイルに格納するためのアーカイブ ファイル形式です。つまり、サーバー証明書 (ドメインに対して発行)、一致する秘密キー、および必要に応じて中間 CA を含めることができます。

手元に証明書がない場合は、Azure Key Vaultを使用して証明書を生成できます。 パートナー証明機関を割り当て、証明書のローテーションを自動化する利点があります。 詳細については、「[クイック スタート: Azure ポータルを使用してAzure Key Vaultを使用して証明書を生成する](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/quick-create-portal)」を参照してください。

公開キーと秘密キーを 1 つの *.pem* ファイルに結合し、このファイルをKey Vaultにアップロードします。 変換には、 **OpenSSL** を使用します。 ターミナルに次のように入力します。

```bash
cat example.crt example.key > example.pem
```

>
> PowerShell ユーザーは、以下と同等の **cat** を使用できます。
>
>
> ```powershell
>    Get-Content example.crt, exampleDecrypted.key | Set-Content example.pem
> ```

これにより、 `example.pem`が得されるはずです。 次に、これをKey Vaultに**アップロード**します。

1. [Azure ポータル](https://portal.azure.com)でキー コンテナーに移動します。
2. Key Vaultのプロパティ ページで、**Certificates** を選択します。
3. [ **生成/インポート**] をクリックします。
4. [ **証明書の作成**] 画面で、次の値を選択します。
    - **証明書の作成方法**: インポート。
    - **証明書名**: ExampleCertificate。
    - **証明書ファイルのアップロード**: ディスクから証明書ファイルを選択する
    - **パスワード** : パスワードで保護された (つまり *、パス フレーズ*) 証明書ファイルをアップロードする場合は、ここでそのパスワードを指定します。 それ以外の場合は空白のまま残します。 証明書ファイルが正常にインポートされると、キー コンテナーはそのパスワードを削除します。
5. **Create** をクリックしてください。

別のインポート方法については、「[チュートリアル: Azure Key Vaultで証明書をインポートする](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/tutorial-import-certificate)」を参照してください。

>
>  ℹ️ Azure Key Vault Certificates に証明書を生成またはインポートすると、対応する秘密鍵が Azure Key Vault Secrets に自動的に作成されます。 後で、[シークレット] ブレードから秘密キー **を** 取得できます。

#### Node.js でコンテナーから証明書を取得する

[Azure Key Vault JavaScript SDK](https://learn.microsoft.com/ja-jp/javascript/api/overview/azure/keyvault-certificates-readme) を使用して、前の手順でアップロードした証明書をフェッチできます。 開発中、Key Vault **JavaScript SDK Azure**は、VS Code のコンテキストを使用して[、@azure/ID](https://learn.microsoft.com/ja-jp/javascript/api/overview/azure/identity-readme) パッケージを使用してローカル環境から必要なアクセス トークンを取得します。 これを行うには、Azureにサインインする必要があります。

まず、[Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)をダウンロードしてインストールします。 これにより、システム パス**にAzure CLI**が追加されます。 VS Code を再起動し、**VS** Code [統合ターミナル](https://code.visualstudio.com/docs/editor/integrated-terminal)でAzure CLIを使用できるようにする必要があります。 次に、次のように入力してサインインします。

```console
az login --tenant YOUR_TENANT_ID
```

認証されると、[@azure/ID](https://learn.microsoft.com/ja-jp/javascript/api/overview/azure/identity-readme) パッケージは、次に示すようにAzure Key Vaultにアクセスできます。

```JavaScript

// Initialize Azure SDKs
const credential = new identity.DefaultAzureCredential();
const certClient = new keyvaultCert.CertificateClient(KVUri, credential);
const secretClient = new keyvaultSecret.SecretClient(KVUri, credential);

async function main() {

    // Grab the certificate thumbprint
    const certResponse = await certClient.getCertificate(CERTIFICATE_NAME);
    const thumbprint = certResponse.properties.x509Thumbprint.toString('hex').toUpperCase();

    // When you upload a certificate to Key Vault, a "secret" containing your private key is automatically created
    const secretResponse = await secretClient.getSecret(CERTIFICATE_NAME);

    // secretResponse contains both public and private key, but we only need the private key
    const privateKey = secretResponse.value.split('-----BEGIN CERTIFICATE-----\n')[0]

    // Initialize msal and start the server
    msalApp(thumbprint, privateKey);
}

main();
```

実装については、 [auth-code-key-vault](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/auth-code-key-vault) のコード サンプルを参照してください。

#### `PKCS12/PFX`を`PEM`に変換

ほとんどの状況では、コンテンツ `pem`が証明書の生成中にとして選択された場合、Azure Key Vaultは**証明書**と秘密キーを`pem`形式でエクスポートできます (「Key Vault[で証明書を作成](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/tutorial-rotate-certificates#create-a-certificate-in-key-vault)する」を参照)。 何らかの理由でそうではない場合は、OpenSSL を変換に使用できます。 [「証明書: pfx を pem に変換する](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/certificate-credentials#optional-converting-pfx-to-pem)」を参照してください。

### Azure マネージド ID の使用

ローカル環境での開発時には**Azure Key Vault JavaScript SDK**を使用できます。一方、本番環境やデプロイ時には、**Azure Managed Identity** サービスを使用してキー コンテナーにアクセスします。

>
> マネージド ID について理解するには、少し時間を取ります。[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#how-a-system-assigned-managed-identity-works-with-an-azure-vm)

#### App Service へのデプロイ

詳細については、「[App Service のマネージド ID を使用する方法」を](https://learn.microsoft.com/ja-jp/azure/app-service/overview-managed-identity?tabs=portal%2Chttp)参照してください。

##### 手順 1: ファイルをデプロイする

1. **VS Code** アクティビティ バーで、**Azure**ロゴを選択して**、Azure App Service** エクスプローラーを表示します。 **Azure にサインイン...** を選択し、指示に従います。 サインインすると、エクスプローラーに **Azure** サブスクリプションの名前が表示されます。
2. **App Service** エクスプローラーセクションには、上向きの矢印アイコンが表示されます。 サンプル フォルダー内のローカル ファイルをクリックして **Azure アプリ Services** に発行します (必要に応じて [参照] オプションを使用し、適切なフォルダーを見つけます)。
3. 展開するオペレーティング システムに基づいて作成オプションを選択します。 このサンプルでは、Linux を選択 **します**。
4. メッセージが表示されたら、Node.js バージョンを選択します。 **LTS** バージョンをお勧めします。
5. Web アプリのグローバルに一意の名前を入力し、Enter キーを押します。 名前は、すべての**Azure**で一意である必要があります。 (例: `msal-node-webapp1`)
6. すべてのプロンプトに応答すると、**VS Code** によって、アプリ用に作成されている**Azure** リソースが通知ポップアップに表示されます。
7. ターゲット Linux サーバーでを実行するように構成を更新するように求められたら、[`npm install`] を選択します。

#### 手順 2: アプリのリダイレクト URI を更新する

1. [Azure ポータル](https://portal.azure.com)に移動し、**Microsoft Entra ID** サービスを選択します。
2. 左側の [ **アプリの登録** ] ブレードを選択し、登録した Web アプリを見つけて選択します。
3. **[認証**] ブレードに移動します。 そこで、[ **リダイレクト URI]** セクションに、次のリダイレクト URI を入力します: `https://msal-node-webapp1.azurewebsites.net/redirect`。
4. **[保存]** を選択して変更を保存します。

#### マネージド ID の統合

##### システム割り当て ID を作成する

1. [ポータルAzure](https://portal.azure.com)移動し、**Azure App Service**を選択します。
2. 前に作成した App Service を見つけて選択します。
3. App Service ポータルで、[ **ID] を**選択します。
4. **[システム割り当て済み]** タブで、 **[状態]** を **[オン]** に切り替えます。 **保存** をクリックします。

詳細については、「[システム割り当て ID の追加」を](https://learn.microsoft.com/ja-jp/azure/app-service/overview-managed-identity?tabs=portal%2Cdotnet#add-a-system-assigned-identity)参照してください。

##### Key Vaultへのアクセスを許可する

App Service にデプロイされたアプリにマネージド ID が設定されたので、この手順では、キー コンテナーへのアクセス権を付与します。

1. [Azure ポータル](https://portal.azure.com)に移動し、Key Vaultを検索します。
2. 左側の [ **概要**&gt;**アクセス ポリシー** ] ブレードを選択します。
3. **アクセス ポリシーの追加**&gt;**証明書のアクセス許可**&gt;**取得** をクリックします
4. **アクセス ポリシーの追加**&gt;**シークレットのアクセス許可**&gt;**取得** をクリックします
5. [ **プリンシパルの選択**] をクリックし、アカウントと事前に作成 **されたシステム割り当て** ID を追加します。
6. [ **OK]** をクリックして新しいアクセス ポリシーを追加し、[ **保存** ] をクリックしてアクセス ポリシーを保存します。

詳細については、「[Azure マネージド ID での App Service からのKey Vaultの使用」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/tutorial-windows-vm-access-nonaad)参照してください。

##### 環境変数の追加

最後に、Web アプリをデプロイした App Service に環境変数を追加する必要があります。

1. [Azure ポータル](https://portal.azure.com)で、**App Service** を検索して選択し、アプリを選択します。
2. 左側の **[構成** ] ブレードを選択し、[ **新しいアプリケーション設定]** を選択します。
3. 次の変数 (name-value) を追加します。
    1. **REDIRECT\_URI**: Microsoft Entra IDに登録したリダイレクト URI(例:`https://msal-node-webapp1.azurewebsites.net/redirect`
    2. **KEY\_VAULT\_NAME**: 作成したキー コンテナーの名前 (例: `node-test-vault`
    3. **CERTIFICATE\_NAME**: キー コンテナーにインポートするときに指定した証明書の名前 (例: `ExampleCert`

**App Service** での変更が有効になるまで数分待ちます。 その後、公開した Web サイトにアクセスし、適宜サインインできるはずです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/managed-identity"} -->
## MSAL ノードでマネージド ID を使用する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/managed-identity
- Service: msal / msal-node
- Article date: 2026-03-06
- Summary: MSAL Node でAzureのマネージド ID を使用して、シークレットを手動で管理せずにトークンを取得する方法について説明します。

開発者にとって一般的な課題は、サービス間の通信をセキュリティで保護するために使用されるシークレット、資格情報、証明書、およびキーの管理です。 Azureの[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) により、開発者がこれらの資格情報を手動で処理する必要がなくなります。 MSAL Node では、次のようなAzureインフラストラクチャ内で実行されているアプリケーションで使用する場合、マネージド ID サービスを介したトークンの取得がサポートされます。

- [Azure App Service](https://azure.microsoft.com/products/app-service/) (API バージョン `2019-08-01` 以降)
- [Azure VM](https://azure.microsoft.com/free/virtual-machines/)
- [Azure Arc](https://learn.microsoft.com/ja-jp/azure/azure-arc/overview)
- [Azure クラウド シェル](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview)
- [Azure Service Fabric](https://learn.microsoft.com/ja-jp/azure/service-fabric/service-fabric-overview)
- [Azure Machine Learning](https://azure.microsoft.com/products/machine-learning)

完全な一覧については、[マネージド ID を使用して他のサービスにアクセスできる](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)サービスAzureを参照してください。

Note

ブラウザーベースの MSAL ライブラリでは、ブラウザーが Azure でホストされていないため、マネージド ID は提供されません。

### どの SDK を使用するか — Azure SDK または MSAL?

MSAL ノードと[Azure SDK](https://learn.microsoft.com/ja-jp/javascript/api/overview/azure/identity-readme)の両方で、マネージド ID を介してトークンを取得できます。 内部的には、Azure SDKは MSAL ノードを使用し、その`DefaultAzureCredential`と`ManagedIdentityCredential`抽象化を介して上位レベルの API を提供します。

アプリケーションでいずれかの SDK が既に使用されている場合は、引き続き同じ SDK を使用します。 新しいアプリケーションを作成し、他のAzure リソースを呼び出す予定の場合は、Azure SDKを使用します。この SDK を使用すると、マネージド ID が存在しないプライベート開発者マシンでアプリを実行できるため、開発者エクスペリエンスが向上します。 Microsoft Graphや独自の Web API などの他のダウンストリーム Web API を呼び出す必要がある場合は、MSAL の使用を検討してください。

Azure Managed Identity を実際に試して使い始めるには、[MSAL Node のマネージド ID サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/Managed-Identity)のいずれかを使用できます。

### マネージド ID の使用方法

開発者が使用できるマネージド ID には、**システム割り当てとユーザー割り当ての** 2 種類があります。 違いの詳細については、 [マネージド ID の種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types) に関する記事を参照してください。 MSAL Node では、両方を使用したトークンの取得がサポートされています。

MSAL ノードからマネージド ID を使用するには、Azure CLIまたはAzure ポータルで使用するリソースに対してマネージド ID を有効にする必要があります。

ユーザー割り当て ID とシステム割り当て ID の両方で、開発者は `ManagedIdentityApplication` クラスを使用できます。

#### システム割り当てのマネージド ID

システム割り当てマネージド ID の場合、開発者は、 `ManagedIdentityApplication`のインスタンスを作成するときに追加情報を渡す必要はありません。これは、割り当てられた ID に関する関連メタデータが自動的に推論されるためです。

[acquireToken](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/managedidentityapplication) は、 `https://management.azure.com`などのトークンを取得するためにリソースと共に呼び出されます。

```typescript
import {
    ManagedIdentityApplication,
    ManagedIdentityConfiguration,
    ManagedIdentityRequestParams,
    NodeSystemOptions,
    LoggerOptions,
    LogLevel,
    AuthenticationResult
} from "@azure/msal-node";

const config: ManagedIdentityConfiguration = {
    system: {
        loggerOptions: {
            logLevel: LogLevel.Verbose,
        } as LoggerOptions,
    } as NodeSystemOptions,
};

const systemAssignedManagedIdentityApplication: ManagedIdentityApplication =
    new ManagedIdentityApplication(config);

const managedIdentityRequestParams: ManagedIdentityRequestParams = {
    resource: "https://management.azure.com",
};

const response: AuthenticationResult =
    await systemAssignedManagedIdentityApplication.acquireToken(
        managedIdentityRequestParams
    );
console.log(response);
```

#### ユーザー割り当て済みマネージド ID

ユーザー割り当てマネージド ID の場合、開発者は、 `ManagedIdentityApplication`を作成するときに、クライアント ID、完全なリソース識別子、またはマネージド ID のオブジェクト ID を渡す必要があります。

システム割り当てマネージド ID の場合と同様に、トークンを取得するリソースで `acquireToken` が呼び出されます。

```typescript
import {
    ManagedIdentityApplication,
    ManagedIdentityConfiguration,
    ManagedIdentityIdParams,
    ManagedIdentityRequestParams,
    NodeSystemOptions,
    LoggerOptions,
    LogLevel,
    AuthenticationResult
} from "@azure/msal-node";

const managedIdentityIdParams: ManagedIdentityIdParams = {
    userAssignedClientId: "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
};

const config: ManagedIdentityConfiguration = {
    managedIdentityIdParams,
    system: {
        loggerOptions: {
            logLevel: LogLevel.Verbose,
        } as LoggerOptions,
    } as NodeSystemOptions,
};

const userAssignedManagedIdentityApplication: ManagedIdentityApplication =
    new ManagedIdentityApplication(config);

const managedIdentityRequestParams: ManagedIdentityRequestParams = {
    resource: "https://management.azure.com",
};

const response: AuthenticationResult =
    await userAssignedManagedIdentityApplication.acquireToken(
        managedIdentityRequestParams
    );
console.log(response);
```

### Caching

MSAL ノードは、マネージド ID のトークンをメモリにキャッシュします。 削除はありませんが、限られた数のマネージド ID を定義できるため、メモリは問題になりません。 このシナリオでは、トークンをマシン間で共有しないようにする必要があるため、キャッシュ拡張機能はサポートされていません。

### よくあるエラーのトラブルシューティング

失敗した要求の場合、エラー応答には、さらに診断とログ分析に使用できる関連付け ID が含まれています。 MSAL で生成された関連付け ID または MSAL に渡される関連付け ID は、MSAL がマネージド ID トークン取得エンドポイントに関連付け ID を渡すことができないため、サーバー エラー応答で返される関連付け ID とは異なることに注意してください。

##### `ManagedIdentityError` — エラー コード: `invalid_resource`

**エラー メッセージ**: 指定されたリソースが無効な URL です。

この例外は、トークンを取得しようとしているリソースがサポートされていないか、間違ったリソース ID 形式を使用して提供されていることを意味する可能性があります。 正しいリソース ID 形式の例としては、 `https://management.azure.com/.default`、 `https://management.azure.com`、 `https://graph.microsoft.com`などがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/mcp"} -->
## MSAL ノードで MCP フローを使用する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/mcp
- Service: msal / msal-node
- Article date: 2026-03-15
- Summary: MSAL ノードでモデル コンテキスト プロトコル (MCP) フローを有効にして、MCP アプリケーションのリソース スコープ トークンを取得する方法について説明します。

[モデル コンテキスト プロトコル (MCP)](https://modelcontextprotocol.io/) アプリケーションを構築するときに、リソース スコープのトークンの取得とキャッシュを適用するように MSAL ノードを構成できます。 MCP モードが有効になっている場合、MSAL では、すべてのトークン要求に `resource` パラメーターを含める必要があり、そのリソースによってキー指定されたアクセス トークンがキャッシュされます。

Note

MCP フローは、パブリック クライアント アプリケーションでのみ使用できます。

### Prerequisites

- [Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- パブリック クライアントとして構成されたアプリケーション (デスクトップアプリケーションや CLI アプリケーションなど)
- `@azure/msal-node` プロジェクトに v5 以降がインストールされている

### MCP モードを有効にする

`PublicClientApplication` の作成時に、`auth` 構成で `isMcp: true` を設定します:

```javascript
const config = {
    auth: {
        clientId: "your-client-id",
        authority: "https://login.microsoftonline.com/common",
        isMcp: true,
    },
};

const pca = new msal.PublicClientApplication(config);
```

### リソース パラメーターを含める

`isMcp`が`true`されると、すべてのトークン要求に  パラメーターが含まれている`resource`。 省略すると、`resource_parameter_required` エラーになります。

```javascript
const tokenRequest = {
    scopes: ["User.Read"],
    redirectUri: "http://localhost:3000/redirect",
    resource: "https://example.microsoft.com",
    code: authorizationCode,
};

const response = await pca.acquireTokenByCode(tokenRequest);
```

Important

`resource` パラメーターを要求オブジェクトに直接設定します。 `extraQueryParameters`経由で`resource`プロパティと同時に渡**さないで**ください。—そうすると`misplaced_resource_parameter`エラーが発生します。

次の例は、 `resource` パラメーターを設定する正しい方法と正しくない方法を示しています。

```javascript
// Correct — resource on the request object
const request = {
    scopes: ["User.Read"],
    resource: "https://example.microsoft.com",
};

// Wrong — resource in both locations
const request = {
    scopes: ["User.Read"],
    resource: "https://example.microsoft.com",
    extraQueryParameters: { resource: "https://example.microsoft.com" },
};
```

### リソース単位のキャッシュ

`isMcp`が有効になっている場合、アクセス トークンは関連付けられているリソースと共にキャッシュされます。 これは、サイレント トークンの取得に影響します。

- **キャッシュ ヒット**: 同じスコープ **と** リソースに対してキャッシュされたアクセス トークンが存在する場合は、キャッシュから返されます。
- **キャッシュ ミス**: 要求されたリソースがキャッシュされたトークンと一致しない場合、MSAL はネットワークにフォールバックして、要求されたリソースの新しいトークンを取得します。

```javascript
const msalTokenCache = pca.getTokenCache();
const accounts = await msalTokenCache.getAllAccounts();

// First request — acquires token from network
const token1 = await pca.acquireTokenSilent({
    scopes: ["User.Read"],
    resource: "https://resource-a.microsoft.com",
    account: accounts[0],
});

// Same resource — returns cached token
const token2 = await pca.acquireTokenSilent({
    scopes: ["User.Read"],
    resource: "https://resource-a.microsoft.com",
    account: accounts[0],
});

// Different resource — falls back to network
const token3 = await pca.acquireTokenSilent({
    scopes: ["User.Read"],
    resource: "https://resource-b.microsoft.com",
    account: accounts[0],
});
```

### エラー処理

2 つのエラーは、MCP フローに固有です。

| エラー コード | 説明 |
| --- | --- |
| `resource_parameter_required` | `isMcp` は `true` ですが、要求に `resource` パラメーターは含まれません。 |
| `misplaced_resource_parameter` | `resource` プロパティと `resource` の両方で`extraQueryParameters`が見つかりました。 1 つだけを使用します。 |

両方のエラーは `ClientAuthError` としてスローされます。 詳細については、 [MSAL ノード](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/faq)に関する FAQ を参照してください。

### Samples

- [MCP フローのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/mcp-flows) - 承認コードとサイレント フローを使用して MCP を示す Express アプリ。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/migration"} -->
## Node.js アプリケーションを ADAL から MSAL に移行する - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/migration
- Service: msal / msal-node
- Article date: 2025-05-21
- Summary: Active Directory認証ライブラリ (ADAL) ではなく、認証と承認に Microsoft Authentication Library (MSAL) を使用するように既存の Node.js アプリケーションを更新する方法。

[ノード用 Microsoft Authentication Library](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) (MSAL ノード) は、Microsoft ID プラットフォームに登録されているアプリケーションの認証と承認を有効にするために推奨される SDK です。 この記事では、アプリを Active Directory Authentication Library for Node (ADAL Node) から MSAL Node に移行するために必要な重要な手順について説明します。

### Prerequisites

- Node バージョン 10、12、14、16、または 18。 [バージョンのサポートに関する注意事項を](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node#node-version-support)参照してください

### アプリケーションを ADAL ノードから MSAL ノードに移行する

ADAL と MSAL の違いに関する一般的な注意事項、および重要な日付とよく寄せられる質問については、「[アプリケーションを Microsoft Authentication Library (MSAL) に移行する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration)を参照してください。

### アプリの登録設定を更新する

ADAL ノードを使用する場合、**Azure AD v1.0 エンドポイント**を使用していた可能性があります。 **Microsoft ID プラットフォーム エンドポイント**を使用するには、アプリを ADAL から MSAL に移行します。

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

### MSAL を初期化する

ADAL ノードでは、 `AuthenticationContext` オブジェクトを初期化し、さまざまな認証フローで使用できるメソッド (Web アプリの `acquireTokenWithAuthorizationCode` など) を公開します。 初期化時に必須のパラメーターは、 **機関 URI** のみです。

```javascript
var adal = require('adal-node');

var authorityURI = "https://login.microsoftonline.com/common";
var authenticationContex = new adal.AuthenticationContext(authorityURI);
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

Note

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

ADAL の`PublicClientApplication`とは異なり、`ConfidentialClientApplication`と`AuthenticationContext`の両方がクライアント ID にバインドされます。 これは、アプリケーションで使用したいさまざまなクライアント ID がある場合、それぞれに対して新しい MSAL インスタンスを作成する必要があることを意味します。 詳細については、[MSAL ノードの初期化を](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/initialize-confidential-client-application)参照してください。

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

重要な違いとして、MSAL には、機関の検証を無効にするフラグがなく、機関は必ず既定で検証されます。 要求した機関は、MSAL によって、Microsoft が認識している機関の一覧、または構成で指定した機関の一覧と比較されます。 詳細については、「[構成オプション」](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/configuration)を参照してください。

### MSAL API に切り替える

ADAL Node のパブリック メソッドのほとんどには、MSAL Node に同等のものがあります。

| ADAL | MSAL | メモ |
| --- | --- | --- |
| `acquireToken` | `acquireTokenSilent` | 名前が変更され、 [アカウント](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/clientapplication#@azure-msal-node-clientapplication-acquiretokensilent) オブジェクトが必要になりました |
| `acquireTokenWithAuthorizationCode` | `acquireTokenByCode` |  |
| `acquireTokenWithClientCredentials` | `acquireTokenByClientCredential` |  |
| `acquireTokenWithRefreshToken` | `acquireTokenByRefreshToken` | 有効な更新トークンの移行に役立ちます |
| `acquireTokenWithDeviceCode` | `acquireTokenByDeviceCode` | ユーザー コードの取得を抽象化するようになりました (下記参照) |
| `acquireTokenWithUsernamePassword` | `acquireTokenByUsernamePassword` |  |

ただし、ADAL Node の一部のメソッドは非推奨とされ、その一方、MSAL Node には新しいメソッドが用意されています。

| ADAL | MSAL | メモ |
| --- | --- | --- |
| `acquireUserCode` | N/a | `acquireTokeByDeviceCode`とマージされました (上記を参照してください) |
| N/a | `acquireTokenOnBehalfOf` | [OBO フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)を抽象化する新しいメソッド |
| `acquireTokenWithClientCertificate` | N/a | 初期化中に証明書が割り当てられるので不要になりました ( 構成オプションを参照) |
| N/a | `getAuthCodeUrl` | [承認エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints) URL の構築を抽象化する新しいメソッド |

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

### コールバックの代わりに promise を使用する

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

### ログ記録を有効にする

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

独自のキャッシュ **プラグイン**を提供することで、キャッシュをディスクに書き込むこともできます。 キャッシュ プラグインは、 [インターフェイス ICachePlugin](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-node/icacheplugin) を実装する必要があります。 ログと同様に、キャッシュは構成オプションの一部であり、MSAL Node インスタンスの初期化で作成されます。

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

Note

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

詳細については、 [ADAL ノードから MSAL ノードへの移行サンプルを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/refresh-token)参照してください。

Note

上記のように MSAL ノードの `acquireTokenByRefreshToken` メソッドを使用して、まだ有効な更新トークンを使用して新しいトークンセットを取得したら、古い ADAL ノード トークン キャッシュを破棄することをお勧めします。

### エラーと例外を処理する

MSAL ノードを使用する場合、発生する可能性がある最も一般的なエラーの種類は、 `interaction_required` エラーです。 多くの場合、このエラーは対話型トークン取得のプロンプトを開始するだけで解決されます。 たとえば、 `acquireTokenSilent`を使用する場合、キャッシュされた更新トークンがない場合、MSAL Node はアクセス トークンをサイレント モードで取得できません。 同様に、アクセスしようとしている Web API には [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーが設定されている可能性があり、ユーザーは [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) (MFA) を実行する必要があります。 このような場合は、`interaction_required`をトリガーして`acquireTokenByCode`エラーを処理することで、ユーザーにMFAの入力を求め、解決できるようになります。

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

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/performance"} -->
## MSAL Node におけるパフォーマンス - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/performance
- Service: msal / msal-node
- Article date: 2025-05-21
- Summary: MSAL ノードのパフォーマンスを測定する方法について説明します。

### Prerequisites

- MSAL を使用したトークン取得のパフォーマンスを向上させるためにアプリケーションで使用できる手法の概要については、「 [MSAL Browser](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/performance) のパフォーマンス」を参照してください。
- [Node.js](https://nodejs.org)

### パフォーマンスの測定

MSAL Node の認証フローのパフォーマンスを測定するアプリケーションは、Node の [パフォーマンス測定 API などを](https://nodejs.org/api/perf_hooks.html) 使用して手動で行うことができます。 収集できる注目すべきデータ ポイントの一覧を次に示します。

| データ | Meaning | 推奨事項 |
| --- | --- | --- |
| `authType` | `acquireToken*` トークン要求に使用される API | 使用状況を識別するために使用します。 |
| `correlationId` | トークン要求に使用される関連付け ID。 これは`correlationId` の  プロパティを使用して取得できます。 | 使用状況を識別するために使用します。 |
| `durationTotalInMs` | ネットワーク呼び出しとキャッシュ アクセスを含む、MSAL で費やされた合計時間 | 全体的な待機時間が長い場合のアラーム (&gt; 1 秒)。 値はトークン ソースによって異なります。 キャッシュから: 1 つのキャッシュ アクセス。 ネットワークから: 2 つのキャッシュ アクセスと 2 つの HTTP 呼び出し。 |
| `durationInCacheInMs` | トークン キャッシュの永続化 (Redis など) の読み込みまたは保存に費やされた時間。 | スパイク時のアラーム。 |
| `durationInHttpInMs` | IdP への HTTP 呼び出しの実行に費やされた時間 (例: Microsoft Entra ID)。 より詳細な監視には、カスタム ネットワーク クライアントを使用できます。 詳細については、 [構成](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/configuration#system-config-options) と [カスタム ネットワークのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/custom-INetworkModule-and-network-tracing) を参照してください | スパイク時のアラーム。 |
| `tokenSource` | トークンのソース (つまり、キャッシュとネットワーク)。 これは`fromCache` の  プロパティを使用して取得できます。 | トークンはキャッシュからはるかに高速に取得されます (たとえば、約 100 ミリ秒と約 700 ミリ秒)。 キャッシュヒット率の監視とアラートに使用できます。 `durationTotalInMs`と一緒に使用します。 |

例えば次が挙げられます。

```javascript
    const { PerformanceObserver, performance } = require('node:perf_hooks');

    const perfObserver = new PerformanceObserver((items) => {
        items.getEntries().forEach((entry) => {
            console.log(entry);
        })
    });

    perfObserver.observe({ entryTypes: ["measure"], buffered: true });

    // ...

    performance.mark("acquireTokenByClientCredential-start");

    const tokenResponse = await msalInstance.acquireTokenByClientCredential({
        scopes: ["User.Read.All"],
    });

    performance.mark("acquireTokenByClientCredential-end");

    performance.measure("acquireTokenByClientCredential", {
        start: "acquireTokenByClientCredential-start"
        end: "acquireTokenByClientCredential-end"
        detail: {
            tokenSource: tokenResponse.fromCache
            correlationId: tokenResponse.correlationId
        }
    });
```

### 機密クライアント アプリケーションのパフォーマンスに関する考慮事項

機密クライアント アプリケーションは主に、要求の同時処理を伴うサーバー側のシナリオで使用されるため、MSAL `ConfidenticalClientApplication` (CCA) インスタンスのスコープを各ユーザー、要求、またはセッションにすることをお勧めします。

各要求の新しい CCA インスタンスは、既定のメモリ内キャッシュに、トークンの取得方法に関するトークンやメタデータが最初に含まれていないことを意味します。 そのため、パフォーマンスが低下しないように、作成する CCA インスタンスはトークン要求の前に準備する必要があります。

```typescript
import {
    ConfidentialClientApplication,
    AuthenticationResult,
    CryptoProvider,
    OnBehalfOfRequest
} from "@azure/msal-node";

function getMsalInstance(partitionKey: string): ConfidentialClientApplication {
    return new ConfidentialClientApplication({
        auth: {
            clientId: "ENTER_CLIENT_ID",
            authority: "http://login.microsoftonline.com/ENTER_TENANT_ID"
            cloudDiscoveryMetadata: "PROVIDE_STRINGIFIED_DISCOVERY_METADATA"
            authorityMetadata: "PROVIDE_STRINGIFIED_AUTHORITY_METADATA"
        },
        cache: {
            cachePlugin: new CustomCachePlugin(
                this.cacheClientWrapper,
                partitionKey
            )
        }
    });
};

async function getToken(tokenRequest: OnBehalfOfRequest): Promise<AuthenticationResult | null> {
    const partitionKey = await this.cryptoProvider.hashString(tokenRequest.oboAssertion);

    const cca = getMsalInstance(partitionKey);

    let tokenResponse = null;

    try {
        await cca.getTokenCache().getAllAccounts(); // required for cache read
        tokenResponse = await cca.acquireTokenOnBehalfOf(tokenRequest);
    } catch (error) {
        throw error;
    }

    return tokenResponse;
};
```

詳細については、 [カスタム分散キャッシュ プラグインを使用した (CCA) Web API](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/auth-code-distributed-cache) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/regional-authorities"} -->
## 地域当局の有効化 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/regional-authorities
- Service: msal / msal-node
- Article date: 2026-03-15
- Summary: MSAL ノードの地域機関が特定のAzure地理的リージョン内でトークン要求をルーティングできるようにする方法について説明します

Important

地域機関は、内部Microsoft サービスとクライアント資格情報フローでのみ使用できる従来の機能です。 代わりに [マネージド ID を](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/managed-identity) 使用することをお勧めします。

Azureの信頼性、可用性、パフォーマンスを向上させるために、地域化は、すべてのトラフィックを地理的領域内に保持することを目的としています。 たとえば、アプリが WestUs2 のKey Vaultからデータをフェッチする必要がある場合は、MSAL で生成されたトラフィックを含め、これに伴うすべてのトラフィックが WestUs2 に留まる必要があります。

地域当局に関するいくつかの重要な注意事項:

- アクセス トークンは、由来のリージョンに関係なく同じです
- 1 つのリージョンに対して取得されたトークンは、リージョン以外のエンドポイントに対して有効です ("westus2.login.microsoft.com" のトークンは、"login.microsoftonline.com" のトークンと同じです)。 その逆も同様です。 これは同じトークンであり、rh と呼ばれる 1 つの要求を引いた値です

Note

このレガシ機能は、内部Microsoft サービスとクライアント資格情報フローでのみ使用できます。

### コンフィギュレーション

地域機関を使用するようにアプリケーションを構成するには、クライアント資格情報要求本文の `azureRegion` フィールドにリージョンを指定する必要があります。

#### シークレットを安全に使用する

シークレットはハードコーディングしないでください。 dotenv npm パッケージを使用すると、シークレットを .gitignore に含める必要がある .env ファイル (プロジェクトのルート ディレクトリにあります) にシークレットを格納し、シークレットが誤ってアップロードされるのを防ぐことができます。

```js
var msal = require('@azure/msal-node');
require('dotenv').config(); // process.env now has the values defined in a .env file

const config = {
    auth: {
        clientId: "<CLIENT_ID>",
        authority: "https://login.microsoftonline.com/<TENANT_ID>",
        clientSecret: process.env.clientSecret,
    }
};

// Create msal application object
const cca = new msal.ConfidentialClientApplication(config);

const clientCredentialRequest = {
    scopes: ["<SCOPE_1>, <SCOPE_2>"],
    azureRegion: "REGION_NAME" // Specify the region you will deploy your application to here. E.g. "westus2"
};

cca
    .acquireTokenByClientCredential(clientCredentialRequest)
    .then((response) => {
        // Handle a successful authentication 
    })
    .catch((error) => {
        // Handle a failed authentication 
    });
```

Note

`"TryAutoDetect"` フィールドに値`azureRegion`を指定すると、MSAL ライブラリは、アプリケーションがデプロイされているリージョンを検出し、そのリージョンを使用しようとします。 この自動検出は信頼性が低く、回避する必要があります。 代わりに、リージョンを明示的に指定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/sni"} -->
## MSAL ノードを使用したサブジェクト名/発行者 (SNI) 認証 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/sni
- Service: msal / msal-node
- Article date: 2026-03-15
- Summary: MSAL ノードでセキュリティで保護されたトークンの取得に証明書でサブジェクト名/発行者 (SNI) 認証を使用する方法について説明します

Warning

ここで開始する前に、 [MSAL ノードでの証明書資格情報の使用](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/certificate-credentials)について理解していることを確認してください。

SNI (サブジェクト名/発行者) 認証を使用すると、事前に定義された信頼された CA のパブリック証明書を使用してアプリを認証し、複雑な証明書のロールオーバー シナリオをサポートできます。 [X5C ヘッダー パラメーター](https://tools.ietf.org/html/rfc7515#section-4.1.6)を使用して、サーバーに証明書を提供します。

ファースト パーティのユーザーは[、内部Microsoft Entra Wiki](https://aadwiki.windows-int.net/index.php?title=Subject_Name_and_Issuer_Authentication) の指示に従って、SNI をサポートするようにMicrosoft Entra環境を設定する必要があります。

### `x5c` 要求

`pem`と`clientCertificate.x5c`の両方を指定するだけでなく、`clientCertificate.thumbprintSha256`でエンコードされた証明書の文字列を `clientCertificate.privateKey` フィールドの MSAL 構成オブジェクトに指定する必要があります。

`.pem` ファイルの `x5c` ストリングの例:

```text
-----BEGIN CERTIFICATE-----
<cert1>
-----END CERTIFICATE-----

-----BEGIN CERTIFICATE-----
<cert2>
-----END CERTIFICATE-----

// ...
```

「[証明書: pfx を pem に変換する](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/certificate-credentials#optional-converting-pfx-to-pem)」も参照してください

### アプリの構成

#### シークレットと証明書を安全に使用する

シークレットはハードコーディングしないでください。 dotenv npm パッケージを使用すると、シークレットの誤アップロードを防ぐために、.gitignore に含める必要がある .env ファイル (プロジェクトのルート ディレクトリにある) にシークレットまたは証明書を格納できます。

証明書は、NodeJS の fs モジュールを介してファイルから読み取ることもできます。 ただし、プロジェクトのディレクトリに保存しないでください。 運用アプリでは、[Azure KeyVault](https://azure.microsoft.com/products/key-vault) またはその他のセキュリティで保護されたキー コンテナーから証明書をフェッチする必要があります。

詳細については、 [証明書とシークレットを](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-app-registration#certificates-and-secrets) 参照してください。

MSAL サンプルを参照してください。 [auth-code-with-certs](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/auth-code-with-certs)

次のスニペットは、サブジェクト名/発行者 (SNI) 認証用に MSAL を初期化する方法を示しています。

```js
var msal = require('@azure/msal-node');
require('dotenv').config(); // process.env now has the values defined in a .env file

const config = {
    auth: {
        clientId: "ENTER_CLIENT_ID",
        authority: "https://login.microsoftonline.com/ENTER_TENANT_ID",
        clientCertificate: {
                thumbprintSha256: process.env.thumbprint, // a 64-digit hexadecimal string
                privateKey: process.env.privateKey,
                x5c: process.env.x5c 
            }
   }
}
};

// Create msal application object
const cca = new msal.ConfidentialClientApplication(config);
```

### 一般的な問題

[証明書をインポートするときの一般的な問題を](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/certificate-credentials#common-issues)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/node/v5-migration"} -->
## MSAL Node v3 から v5 への移行 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/v5-migration
- Service: msal / msal-node
- Article date: 2026-03-06
- Summary: 重大な変更や構成の更新など、アプリケーションを MSAL Node v3 から v5 に移行する方法について説明します。

Note

MSAL Node v4 リリースはありません。 パッケージバージョンは、他の MSAL.js ライブラリとバージョン管理 `msal-node` 合わせるために、v3 から v5 に直接インクリメントされました。 個別の v4 機能セットは存在しません。

### Node.js 16 および 18 のサポートが削除されました

MSAL Node v5 では、Node.js 16 または 18 はサポートされなくなりました。Node.js 20 以降を使用する必要があります。

### `proxyUrl` と `customAgentOptions` を削除

MSAL Node v5 では、HTTP クライアントのオプション構成が提供されなくなりました。 `proxyUrl`パラメーターと`customAgentOptions` パラメーターは、`NodeSystemOptions`から削除されました。

```ts
// BEFORE (v3)
NodeSystemOptions = {
    loggerOptions?: LoggerOptions;
    networkClient?: INetworkModule;
    proxyUrl?: string;
    customAgentOptions?: http.AgentOptions | https.AgentOptions;
    disableInternalRetries?: boolean;
    protocolMode?: ProtocolMode;
};

// AFTER (v5)
NodeSystemOptions = {
    loggerOptions?: LoggerOptions;
    networkClient?: INetworkModule;
    disableInternalRetries?: boolean;
    protocolMode?: ProtocolMode;
};
```

プロキシのサポートが必要な場合、開発者は独自のカスタム HTTP クライアントを作成する必要があります。 実装の詳細については、 [カスタム INetworkModule サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/custom-INetworkModule-and-network-tracing) を参照してください。

### 構成の変更

#### `protocolMode` システム構成に移動

`protocolMode` パラメーターは、認証構成オプションではなく、代わりにシステム構成オプションです。

```ts
// BEFORE (v3)
const msalConfig = {
    auth: {
        clientId: "your_client_id",
        authority: "https://login.live.com",
        protocolMode: "OIDC",
    },
};

// AFTER (v5)
const msalConfig = {
    auth: {
        clientId: "your_client_id",
        authority: "https://login.live.com",
    },
    system: {
        protocolMode: "OIDC",
    },
};
```

#### その他の削除されたパラメーター

- `skipAuthorityMetadataCache` パラメーターは削除されました。 アプリケーションは、権限の初期化中にローカル メタデータ キャッシュを使用しなくなりました。
- `encodeExtraQueryParams` パラメーターは削除されました。 追加のクエリ パラメーターはすべて自動的にエンコードされます。

### `fromNativeBroker` に名前が変更された `fromPlatformBroker`

`AuthenticationResult` オブジェクトでは、`fromNativeBroker` フィールドの名前が `fromPlatformBroker` に変更されました。
<!-- /MSL-PAGE -->
