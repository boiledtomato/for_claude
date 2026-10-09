# Microsoft Learn — Microsoft Entra / ユーザー・デバイス・ロール・マネージド ID (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 41

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-mac-sso-extension-plugin"} -->
## Apple デバイス上の Microsoft Enterprise SSO 拡張機能プラグインのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-mac-sso-extension-plugin
- Service: entra-id / devices
- Article date: 2026-02-23
- Summary: この記事は、Apple デバイスで Microsoft Enterprise SSO プラグインを展開する際のトラブルシューティングに役立ちます

この記事では、 [Enterprise SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)のデプロイと使用に関する問題を解決するために管理者が使用するトラブルシューティング ガイダンスについて説明します。 Apple SSO 拡張機能は、iOS/iPadOS と macOS に展開できます。

組織は、エンド ユーザーにより良いエクスペリエンスを提供するために、会社のデバイスに SSO を展開することもできます。 Apple プラットフォームでは、このプロセスには [、プライマリ更新トークン](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)を使用したシングル サインオン (SSO) の実装が含まれます。 SSO を使用すると、過剰な認証プロンプトによるエンド ユーザーの負担を軽減できます。

Microsoft は、Apple の SSO フレームワークに基づいて構築されたプラグインを実装しました。これは、Microsoft Entra IDと統合されたアプリケーションにブローカー認証を提供します。 詳細については、 [Apple デバイス用の Microsoft Enterprise SSO プラグインに関する記事を](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)参照してください。

### 拡張機能の種類

Apple では、フレームワークの一部である 2 種類の SSO 拡張機能 (リダイレクトと**資格情報**) **が**サポートされています。 Microsoft Enterprise SSO プラグインはリダイレクトの種類として実装されており、Microsoft Entra IDへの認証の仲介に最適です。 次の表では、この 2 つの型の拡張機能を比較しています。

| 拡張機能の種類 | 最も適しているデータ | しくみ | 主要な相違点 |
| --- | --- | --- | --- |
| リダイレクト | OpenID Connect、OAUTH2、SAML などの最新の認証方法 (Microsoft Entra ID) | オペレーティング システムは、アプリケーションからの認証要求を、拡張機能 MDM 構成プロファイルで定義されている ID プロバイダー URL にインターセプトします。 リダイレクト拡張機能は、URL、ヘッダー、本文を受け取ります。 | データを要求する前に資格情報を要求します。 MDM 構成プロファイルで URL を使用します。 |
| 資格情報 | チャレンジ応答型認証タイプ (**Kerberos**) (オンプレミスのActive Directory Domain Services) | アプリケーションから認証サーバー (AD ドメイン コントローラー) に要求が送信されます。 資格情報拡張機能が MDM 構成プロファイルでホストを使用して構成されます。 認証サーバーがプロファイルに列挙されているホストと一致するチャレンジを返す場合、オペレーティング システムはチャレンジを拡張機能にルーティングします。 拡張機能は、チャレンジを処理するか、拒否するかを選択します。 処理した場合、拡張機能は要求を完了するために Authorization ヘッダーを返し、認証サーバーは呼び出し元に応答を返します。 | その後、要求データは認証のためにチャレンジされます。 MDM 構成プロファイルでホストを使用します。 |

Microsoft では、次のクライアント オペレーティング システム用のブローカー認証の実装を用意しています:

| オペレーティングシステム (OS) | 認証ブローカー |
| --- | --- |
| ウィンドウズ | Web アカウント マネージャー (WAM) |
| iOS/iPadOS | Microsoft Authenticator |
| Android | Microsoft AuthenticatorまたはMicrosoft Intune Company Portal |
| macOS | Microsoft Intune Company Portal (SSO 拡張機能経由) |

すべての Microsoft ブローカー アプリケーションは、プライマリ更新トークン (PRT) と呼ばれる主要な成果物を使用します。これは、Microsoft Entra IDで保護されたアプリケーションと Web リソースのaccess トークンを取得するために使用される JSON Web トークン (JWT) です。 MDM を使用して展開すると、macOS または iOS 用の Enterprise SSO 拡張機能は、Web アカウント マネージャー (WAM) によって Windows デバイスで使用される PRT に似た PRT を取得します。 詳細については、「 [プライマリ更新トークンとは」](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)を参照してください。

### トラブルシューティング モデル

次のフローチャートは、SSO 拡張機能のトラブルシューティングに取り組むための論理フローの概要を示しています。 この記事の残りの部分では、このフローチャートに示されている手順について詳しく説明します。 トラブルシューティングは、 デプロイ と アプリケーション認証フローという 2 つの領域に分けることができます。

## [iOS](#tab/flowchart-ios)
[Image: iOS デバイスでの Apple SSO 拡張機能のトラブルシューティング プロセス フローを示すフローチャートのスクリーンショット。]

## [macOS](#tab/flowchart-macos)
[Image: macOS デバイスでの Apple SSO 拡張機能のトラブルシューティング プロセス フローを示すフローチャートのスクリーンショット。]

---

### macOS でプラットフォーム SSO をオプトアウトする手順

誤って有効にされた PSSO をオプトアウトする場合、管理者は、PSSO が有効になっている SSO 拡張機能プロファイルをデバイスから削除し、PSSO フラグが無効になっているか削除された新しい SSO 拡張機能プロファイルをデプロイする必要があります。

1. PSSO が有効になっている SSO プロファイルのターゲット設定を削除する
2. デバイス同期を開始して、デバイスから削除された、PSSO が有効になっている SSO プロファイルを取得する
3. PSSO が無効になっている新しい SSO プロファイルを使用してデバイスをターゲットにする
4. デバイス同期を開始して、デバイスにインストールされた新しいプロファイルを取得する

重要

**注: デバイス上の既存の SSO プロファイルを更新しても、PSSO 登録が完了した後に PSSO は無効にはなりません。** デバイスから SSO プロファイルを完全に削除した場合にのみ、デバイスから PSSO 状態が削除されます。

コンテキスト:

macOS 13 以降のデバイスでは、次の 2 つのシナリオにおいて、ユーザーへの PSSO 登録通知の表示が始まります。

1. デバイスに既に PSSO をサポートする Intune Company Portal バージョンがあり、管理者が PSSO を有効にして新しい SSO 拡張機能ポリシーを展開している場合
2. ユーザーが既に PSSO を有効にした SSO 拡張機能ポリシーを対象としている場合は、デバイスに PSSO をサポートする Intune Company Portal バージョンがインストールされます。

注意事項

**管理者は、PSSO を有効にした SSO 拡張機能ポリシーを持つユーザーをターゲットにしないでください。ただし、テストされ、展開する準備が整っていない限り、既存のユーザーとそのコンプライアンス条件が損なわれる可能性があるため**です。

重要

**注: PSSO 登録を完了したユーザーの場合、レガシの WPJ 登録はキーチェーンから削除されます。** PSSO 登録が誤って行われた場合に、管理者が PSSO を含む SSO プロファイルを削除し、PSSO なしで新しいプロファイルをインストールしたら、デバイスのコンプライアンスが機能するように、レガシの WPJ 登録をもう一度行う必要があります。

### デプロイのトラブルシューティング

お客様が発生するほとんどの問題は、SSO 拡張機能プロファイルの不適切な Mobile Device Management (MDM) 構成、または Apple デバイスが MDM から構成プロファイルを受信できないことに起因します。 このセクションでは、MDM プロファイルが Mac に展開されていて正しい構成になっていることを確認するための手順について説明します。

#### デプロイ要件

- macOS オペレーティング システム: **バージョン 10.15 (Catalina)** 以降。
- iOS オペレーティング システム: **バージョン 13** 以上。
- [Apple macOS または iOS](https://support.apple.com/guide/deployment/dep1d7afa557/web) (MDM 登録) をサポートする MDM ベンダーによって管理されるデバイス。
- インストールされている認証ブローカー ソフトウェア: [**Microsoft Intune Company Portal**](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos) または iOS 用[**Microsoft Authenticator**](https://support.microsoft.com/account-billing/download-and-install-the-microsoft-authenticator-app-351498fc-850a-45da-b7b6-27e523b8702a)。

##### macOS X オペレーティング システムのバージョンを確認する

macOS デバイスのオペレーティング システム (OS) のバージョンを確認するには、次の手順に従います。 Apple SSO 拡張機能プロファイルは、 **macOS 10.15 (Catalina) 以降を** 実行しているデバイスにのみデプロイされます。 macOS のバージョンは 、ユーザー インターフェイス または ターミナルから確認できます。

###### ユーザー インターフェイス

1. macOS デバイスから、左上隅にある Apple アイコンを選択し、[ **この Mac について**] を選択します。
2. **macOS** の横にオペレーティング システムのバージョンが表示されます。

###### ターミナル

1. macOS デバイスから、[ **アプリケーション** ] フォルダーをダブルクリックし、[ **Utilities** ] フォルダーをダブルクリックします。
2. **ターミナル** アプリケーションをダブルクリックします。
3. ターミナルが開いたら、プロンプトで **sw\_vers** を入力し、次のような結果を探してください。

    ```zsh
    % sw_vers
    ProductName: macOS
    ProductVersion: 13.0.1
    BuildVersion: 22A400
    ```

##### iOS オペレーティング システムのバージョンを確認する

iOS デバイスのオペレーティング システム (OS) のバージョンを確認するには、次の手順に従います。 Apple SSO 拡張機能プロファイルは、 **iOS 13** 以降を実行しているデバイスにのみデプロイされます。 **設定アプリ**から iOS のバージョンを確認できます。 **設定アプリ**を開きます。

[Image: iOS 設定アプリアイコンを示すスクリーンショット。]

**全般**、**情報**の順に移動します。 この画面には、iOS バージョン番号など、デバイスに関する情報が一覧表示されます。

[Image: 設定アプリの iOS バージョンを示すスクリーンショット。]

##### SSO 拡張機能の構成プロファイルの MDM 展開

MDM 管理者 (または Device Management チーム) と協力して、拡張機能の構成プロファイルが Apple デバイスに展開されていることを確認します。 拡張機能プロファイルは、macOS または iOS デバイスをサポートするあらゆる MDM から展開できます。

重要

Apple では、SSO 拡張機能を展開するには、デバイスが MDM に登録されている必要があります。

次の表は、拡張機能を展開する OS に応じて、MDM インストールに関する具体的なガイダンスを提供しています:

- [**iOS/iPadOS**: Microsoft Enterprise SSO プラグインをデプロイする](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-with-intune)
- [**macOS**: Microsoft Enterprise SSO プラグインをデプロイする](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-macos-with-intune)

重要

SSO 拡張機能の展開には MDM がサポートされていますが、多くの組織では、MDM コンプライアンス ポリシーを評価することで、[**device ベースの条件付きAccess ポリシー**](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant#require-device-to-be-marked-as-compliant)を実装しています。 サード パーティの MDM が使用されている場合は、デバイス ベースの条件付きAccess ポリシーを使用する場合は、MDM ベンダーが [**Intune Partner Compliance**](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-partners) をサポートしていることを確認します。 Intune または Intune パートナー コンプライアンスをサポートする MDM プロバイダーを介して SSO 拡張機能を展開すると、デバイス認証を完了できるように、拡張機能はデバイス証明書をMicrosoft Entra IDに渡すことができます。

##### macOS デバイスでネットワーク構成を検証する

Apple の SSO 拡張機能フレームワークと、それに基づいて構築された Microsoft Enterprise SSO 拡張機能では、特定のドメインが TLS インターセプト/検査 (ブレーク検査プロキシとも呼ばれます) から除外されている必要があります。 次のドメインは、TLS 検査の対象 **にすることはできません** 。

- app-site-association.cdn-apple.com
- app-site-association.networking.apple

###### TLS 検査が原因で SSO 構成がブレークされているかどうかを確認する

TLS 検査が SSO 構成に影響を与えているかどうかは、影響を受けたデバイスでターミナル アプリケーションから sysdiagnose を実行することで検証できます。

```zsh
sudo sysdiagnose -f ~/Desktop/
```

sysdiagnose は、.tar.gz アーカイブとしてデスクトップに保存されます。 アーカイブを抽出し、 **system\_logs.logarchive** ファイルを開きます。 このアーカイブはコンソール アプリケーションで開きます。 **com.apple.appsso** を検索し、フィルターを **SUBSYSTEM** に変更します。

[Image: sysdiagnose を示すスクリーンショット。]

関連ドメイン (特に、Microsoft ドメインに関連する login.microsoftonline.com などのドメイン) の障害があることを示すイベントを探します。 これらのイベントは、TLS 検査の問題を示している可能性があり、そのために SSO 拡張機能が正常に動作しないことがあります。 Apple ドメインは、サポートされていない TLS 検査構成の影響を受けている場合でも、sysdiagnose のログには表示されません。

###### TLS 検査構成を検証する

Apple では、構成に関するいくつかの一般的な問題を確認するためのツールとして、Mac Evaluation Utility と呼ばれる macOS ツールを提供しています。 このツールは [、AppleSeed for IT](https://beta.apple.com/for-it/) からダウンロードできます。 AppleSeed for IT にaccessしている場合は、[リソース] 領域から Mac 評価ユーティリティをダウンロードします。 アプリケーションをインストールした後、評価を実行します。 評価が完了したら、 **HTTPS インターセプト** --&gt;**追加コンテンツ** --&gt; に移動し、次の 2 つの項目を確認します。

[Image: Mac 評価ユーティリティを示すスクリーンショット。]

これらのチェックで警告やエラーが示された場合は、デバイスで TLS 検査が生じている可能性があります。 ネットワーク チームと協力して、\***.cdn-apple.com** と \***.networking.apple を** TLS 検査から除外します。

###### 詳細な swcd ログを出力する

Apple では、関連ドメインの検証の進行状況を監視できる `swcutil` というコマンド ライン ユーティリティを提供しています。 次のコマンドを使用して、関連ドメインのエラーを監視できます。

```zsh
sudo swcutil watch --verbose
```

次のエントリをログで探し、承認済みとしてマークされているかどうか、またはエラーがないかを確認します。

```

    ```
    Entry s = authsrv, a = UBF8T346G9.com.microsoft.CompanyPortalMac, d = login.microsoftonline.com
    ```

```

###### macOS TLS 検査キャッシュをクリアする

関連ドメインに関する問題があり、デバイス上の TLS 検査ツールで許可リストに登録されているドメインがある場合、Apple の関連ドメイン検証キャッシュが無効になるまでに時間がかかることがあります。 残念ながら、すべてのマシンで関連ドメインの再検証を再びトリガーする確定的な手順はありませんが、いくつかの方法を試みることができます。

次のコマンドを実行して、デバイスのキャッシュをリセットできます。

```zsh
pkill -9 swcd
sudo swcutil reset
pkill -9 AppSSOAgent
```

キャッシュをリセットした後、SSO 拡張機能の構成を再テストします。

場合によっては、このコマンドだけでは不十分でキャッシュが完全にリセットされないことがあります。 そのような場合は、次の手順を試してみてください。

- Intune Company Portal アプリをごみ箱に削除または移動してから、デバイスを再起動します。 再起動が完了したら、Company Portal アプリの再インストールを試すことができます。
- デバイスを再登録します。

上記のどの方法でも問題が解決しない場合は、環境内に存在する他の要素が関連ドメインの検証を妨げている可能性があります。 このような場合は、Apple サポートに連絡してトラブルシューティングを進めてください。

##### システム整合性保護 (SIP) が有効になっていることを確認する

Enterprise SSO フレームワークでは、コード署名の検証が成功している必要があります。 コンピューターがシステム整合性保護 (SIP) 明示的にオプトアウトされている場合、コード署名が正常に機能しない可能性があります。 この場合、次のような sysdiagnose エラーがコンピューターで発生します。

```
Error Domain=com.apple.AppSSO.AuthorizationError Code=-1000 "invalid team identifier of the extension=com.microsoft.CompanyPortalMac.ssoextension" UserInfo={NSLocalizedDescription=invalid team identifier of the extension=com.microsoft.CompanyPortalMac.ssoextension}
```

この問題を解決するには、次のいずれかの手順を実行します。

1. 影響を受けるコンピューターでシステム整合性保護を再度有効にします。
2. システム整合性保護を再度有効にできない場合は、`sudo nvram boot-args` の `amfi_get_out_of_my_way` 値が `1`に設定されていないことを確認します。 その場合は、その値を削除するか、`0` に設定して問題を解決します。

##### macOS デバイスで SSO 構成プロファイルを検証する

MDM 管理者が前のセクション「 SSO 拡張機能プロファイルの MDM 展開」の手順に従っていると仮定すると、次の手順は、プロファイルがデバイスに正常に展開されているかどうかを確認することです。

###### SSO 拡張機能の MDM 構成プロファイルを見つける

1. macOS デバイスから、[ **システム設定]** を選択します。
2. **[システム設定]** が表示されたら、「**プロファイル」**と入力して return キーを押**します**。
3. この操作により、[ **プロファイル]** パネルが表示されます。

    [Image: 構成プロファイルを示すスクリーンショット。]

    | スクリーンショットの吹き出し | 説明 |
    | --- | --- |
    | **1** | デバイスが **MDM** 管理下にあることを示します。 |
    | **2** | 複数のプロファイルから選択できる可能性があります。 この例では、Microsoft Enterprise SSO 拡張機能プロファイルを **Extensible Single Sign On Profile-32f37be3-302e-4549-a3e3-854d300e117a** と呼びます。 |

    Note

    使用されている MDM の種類によっては、いくつかのプロファイルが表示される可能性があり、MDM の構成に応じて名前付けスキームは任意です。 それぞれを選択し、[ **設定]** 行が **シングル サインオン拡張機能**であることを確認します。
4. **[設定]** の [**シングル サインオン拡張機能**] の値に一致する構成プロファイルをダブルクリックします。

    [Image: SSO 拡張機能の構成プロファイルを示すスクリーンショット。]

    | スクリーンショットの吹き出し | 構成プロファイルの設定 | 説明 |
    | --- | --- | --- |
    | **1** | **署名** | MDM プロバイダーの署名機関。 |
    | **2** | **インストール** | 拡張機能がいつインストール (または更新) されたかを示す Date/Timestamp。 |
    | **3** | **設定: シングル サインオン拡張機能** | この構成プロファイルが **Apple SSO 拡張機能** の種類であることを示します。 |
    | **4** | **拡張** | **Microsoft Enterprise 拡張機能プラグイン**を実行しているアプリケーションの**バンドル ID** にマップされる識別子。 プロファイルが macOS デバイスにインストールされている場合は、 **識別子を常** に **`com.microsoft.CompanyPortalMac.ssoextension`** に設定し、チーム識別子を **(UBF8T346G9)** として表示する必要があります。 注: 値が異なる場合、MDM で拡張機能が正しく呼び出されません。 |
    | **5** | **型** | **Microsoft Enterprise SSO 拡張機能**は**、常に** **リダイレクト**拡張機能の種類に設定する必要があります。 詳細については、「 リダイレクトと資格情報の拡張機能の種類」を参照してください。 |
    | **6** | **URL** | ID プロバイダー **(Microsoft Entra ID)**に属するログイン URL。 [サポートされている URL](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#manual-configuration-for-other-mdm-services) の一覧を参照してください。 |

    すべての Apple SSO リダイレクト拡張機能には、構成プロファイルに次の MDM ペイロード コンポーネントが必要です:

    | MDM ペイロード コンポーネント | 説明 |
    | --- | --- |
    | **拡張機能識別子** | 拡張機能を実行している macOS デバイス上のアプリケーションのバンドル識別子とチーム識別子の両方が含まれます。 注: Microsoft Enterprise SSO 拡張機能は常に com.microsoft.CompanyPortalMac.ssoextension (UBF8T346G9) に設定して、拡張機能クライアント コードが Intune Company Portal アプリケーション。 |
    | **型** | 「リダイレクト拡張機能」タイプを示すには、**リダイレクト** に設定する必要があります。 |
    | **URL** | オペレーティング システムが認証要求を拡張機能にルーティングする ID プロバイダー (Microsoft Entra ID) のエンドポイント URL。 |
    | **オプションの拡張機能固有の構成** | 構成パラメーターとして機能することのできるディクショナリ値。 Microsoft Enterprise SSO 拡張機能のコンテキストでは、これらの構成パラメーターは機能フラグと呼ばれます。 [機能フラグの定義を](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#more-configuration-options)参照してください。 |

    Note

    Apple の SSO 拡張機能プロファイルの MDM 定義は、Microsoft がこのスキーマに基づいて拡張機能を実装した [Apple デバイス用の Extensible Single Sign-on MDM ペイロード設定](https://support.apple.com/guide/deployment/depfd9cdf845/web) に関する記事で参照できます。 [Apple デバイス用の Microsoft Enterprise SSO プラグインを](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#manual-configuration-for-other-mdm-services)参照してください
5. Microsoft Enterprise SSO 拡張機能の正しいプロファイルがインストールされていることを確認するには、[ **拡張機能** ] フィールドが **com.microsoft.CompanyPortalMac.ssoextension (UBF8T346G9)** と一致している必要があります。
6. 構成プロファイルの **[インストール済み** ] フィールドは、構成に変更が加えられたときに役立つトラブルシューティング インジケーターになる可能性があるため、メモしておきます。

正しい構成プロファイルが検証されている場合は、「 アプリケーション認証フローのトラブルシューティング 」セクションに進みます。

###### MDM 構成プロファイルがない場合

**前のセクション**の後に SSO 拡張機能の構成プロファイルがプロファイルの一覧に表示されない場合は、MDM 構成でユーザー/デバイスのターゲット設定が有効になっている可能性があります。これは、ユーザーまたはデバイスが構成プロファイルを受信するのを効果的に**除外**している可能性があります。 MDM 管理者に確認し、**次のセクション**で見つかったコンソール ログを収集します。

###### MDM 固有のコンソール ログを収集する

1. macOS デバイスから、[ **アプリケーション** ] フォルダーをダブルクリックし、[ **Utilities** ] フォルダーをダブルクリックします。
2. **コンソール** アプリケーションをダブルクリックします。
3. [ **スタート** ] ボタンをクリックして、コンソールトレースログを有効にします。

    [Image: コンソール アプリとクリックされているスタート ボタンを示すスクリーンショット。]
4. MDM 管理者に、この macOS デバイス/ユーザーに構成プロファイルを再展開し、強制的に同期サイクルを実行してもらいます。
5. **検索バー**に **subsystem:com.apple.ManagedClient** と入力し、**return** キーを押します。

    [Image: サブシステム フィルターを含むコンソール アプリを示すスクリーンショット。]
6. **検索バー**の中でカーソルが点滅している場所に**、message:Extensible** と入力します。

    [Image: メッセージ フィールドでさらにフィルター処理されているコンソールを示すスクリーンショット。]
7. **これで、EXTENSIBLE SSO** 構成プロファイル アクティビティでフィルター処理された MDM コンソール ログが表示されます。 次のスクリーンショットは、ログ エントリ **のインストール済み構成プロファイル**を示し、構成プロファイルがインストールされたことを示しています。

### アプリケーション認証フローのトラブルシューティング

このセクションのガイダンスは、macOS デバイスに構成プロファイルが正しく展開されていることを前提としています。 手順については、 macOS デバイスでの SSO 構成プロファイルの検証 に関するページを参照してください。

**Microsoft Enterprise SSO Extension for Apple デバイス**をデプロイすると、アプリケーションの種類ごとに 2 種類のアプリケーション認証フローがサポートされます。 トラブルシューティングを行うときは、使用しているアプリケーションの種類を理解することが重要です。

#### アプリケーションの種類

| アプリケーションの種類 | 対話型認証 | サイレント認証 | 説明 | 例 |
| --- | --- | --- | --- | --- |
| [**ネイティブ MSAL アプリ**](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#applications-that-use-msal) | X | X | MSAL (Microsoft Authentication Library) は、Microsoft ID プラットフォーム (Microsoft Entra ID) を使用してアプリケーションを構築するために調整されたアプリケーション開発者フレームワークです。**MSAL バージョン 1.1 以降**で構築されたアプリは、Microsoft Enterprise SSO 拡張機能と統合できます。*アプリケーションが SSO 拡張機能 (ブローカー) に対応している場合は、追加の構成なしで拡張機能を利用します* 詳細については、[MSAL 開発者向けサンプル ドキュメント](https://github.com/AzureAD/microsoft-authentication-library-for-objc)を参照してください。 | Microsoft To Do |
| [**MSAL 以外のネイティブ/ブラウザー SSO**](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#applications-that-dont-use-msal) |  | X | Apple ネットワーク テクノロジまたは Web ビューを使用するアプリケーションは、SSO 拡張機能から共有資格情報を取得するように構成できます各アプリのバンドル ID に共有資格情報 (PRT) の取得を許可するには、機能フラグを構成する必要があります。 | Microsoft WordSafariMicrosoft EdgeVisual Studio |

重要

すべての Microsoft ファースト パーティ ネイティブ アプリケーションが MSAL フレームワークを使用しているわけではありません。 この記事の公開時点では、Microsoft Office macOS アプリケーションのほとんどは引き続き古い ADAL ライブラリ フレームワークに依存しているため、Browser SSO フローに依存しています。

##### macOS でアプリケーションのバンドル ID を見つける方法

1. macOS デバイスから、[ **アプリケーション** ] フォルダーをダブルクリックし、[ **Utilities** ] フォルダーをダブルクリックします。
2. **ターミナル** アプリケーションをダブルクリックします。
3. ターミナルが開いたら、プロンプトで「**`osascript -e 'id of app "<appname>"'`**」と入力します。 いくつかの例を次に示します:

    ```zsh
    % osascript -e 'id of app "Safari"'
    com.apple.Safari
    
    % osascript -e 'id of app "OneDrive"'
    com.microsoft.OneDrive
    
    % osascript -e 'id of app "Microsoft Edge"'
    com.microsoft.edgemac
    ```
4. バンドル ID が収集されたので、 [ガイダンスに従って機能フラグを構成し](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#enable-sso-for-all-apps-with-a-specific-bundle-id-prefix) 、 **非 MSAL Native/Browser SSO アプリ** で SSO 拡張機能を確実に利用できるようにします。 **注: すべてのバンドル ID は、フィーチャー フラグの構成で大文字と小文字が区別されます**。

注意事項

Apple Networking テクノロジ (**WKWebview や NSURLSession など**) を使用しないアプリケーションは、SSO 拡張機能から共有資格情報 (PRT) を使用できません。 **Google Chrome** と **Mozilla Firefox の**両方がこのカテゴリに分類されます。 MDM 構成プロファイルで構成された場合でも、ブラウザーで通常の認証プロンプトが表示されます。

#### ブートストラップ

既定では、MSAL アプリのみが SSO 拡張機能を呼び出し、拡張機能はMicrosoft Entra IDから共有資格情報 (PRT) を取得します。 ただし、 **Safari** ブラウザー アプリケーションまたはその他 **の MSAL 以外** のアプリケーションは、PRT を取得するように構成できます。 [「MSAL と Safari ブラウザーを使用しないアプリケーションからのユーザーのサインインを許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#allow-users-to-sign-in-from-applications-that-dont-use-msal-and-the-safari-browser)する」を参照してください。 SSO 拡張機能が PRT を取得すると、ユーザーのログイン キーチェーンにその資格情報が保存されます。 その後、PRT がユーザーのキーチェーンに存在することを確認します:

##### PRT のキーチェーンアクセスを確認

1. macOS デバイスから、[ **アプリケーション** ] フォルダーをダブルクリックし、[ **Utilities** ] フォルダーをダブルクリックします。
2. **Keychain Access** アプリケーションをダブルクリックします。
3. **[既定のキーチェーン**] で、[**ローカル項目 (または iCloud)]** を選択します。

    - **[すべてのアイテム]** が選択されていることを確認します。
    - 右側の検索バーに、「`primaryrefresh`」と入力します (これにより、フィルター処理します)。

    [Image: キーチェーンアクセスアプリでPRTを見つける方法を示すスクリーンショット]

    | スクリーンショットの吹き出し | キーチェーン資格情報のコンポーネント | 説明 |
    | --- | --- | --- |
    | **1** | **すべてのアイテム** | キーチェーン Access全体のすべての種類の資格情報を表示します |
    | **2** | **キーチェーン検索バー** | 資格情報によってフィルター処理できます。 Microsoft Entra PRT を探すために種類をフィルター処理するには、「**`primaryrefresh`**」と入力します |
    | **3** | **親切** | 資格情報の種類を指します。 Microsoft Entra PRT 資格情報は **アプリケーション パスワード** 資格情報の種類です |
    | **4** | **アカウント** | PRT を所有する Microsoft Entra ユーザー アカウントを次の形式で表示します: **`UserObjectId.TenantId-login.windows.net`** |
    | **5** | **どこ** | 資格情報のフル ネームを表示します。 Microsoft Entra PRT 資格情報は、次の形式で始まります。 **`primaryrefreshtoken-29d9ed98-a469-4536-ade2-f981bc1d605`** **29d9ed98-a469-4536-ade2-f981bc1d605** は、PRT 取得要求を処理する **Microsoft Authentication Broker** サービスのアプリケーション ID です。 |
    | **6** | **変更** | 資格情報が最後に更新された日時を示します。 Microsoft Entra の PRT 資格情報の場合、資格情報がブートストラップされるか、対話型サインオン イベントによって更新されるたびに、日付やタイムスタンプが更新されます |
    | **7** | **キーチェーン** | 選択した資格情報が存在するキーチェーンを示します。 Microsoft Entra PRT 資格情報は、 **ローカル項目** または **iCloud** キーチェーンに存在します。 macOS デバイスで iCloud が有効になっている場合、 **ローカル項目** キーチェーンが **iCloud** キーチェーンになります |
4. キーチェーン Accessで PRT が見つからない場合は、アプリケーションの種類に基づいて次の操作を行います。

    - **ネイティブ MSAL**: アプリケーション開発者 (アプリが **MSAL バージョン 1.1 以降**でビルドされている場合) によって、アプリケーションがブローカーに対応していることを確認します。 また、 **展開の問題を除外するには、展開のトラブルシューティングの手順** を確認してください。
    - **MSAL 以外 (Safari):** MDM 構成プロファイルで、機能フラグ **`browser_sso_interaction_enabled`** が 0 ではなく 1 に設定されていることを確認します

##### PRT のブートストラップ後の認証フロー

PRT (共有資格情報) が検証されたので、より詳細なトラブルシューティングを行う前に、アプリケーションの種類ごとの大まかな手順と、それが Microsoft Enterprise SSO 拡張機能プラグイン (ブローカー アプリ) とどのように対話するかを理解しておくことをお勧めします。 次のアニメーションと説明は、macOS 管理者がログ データを確認する前にシナリオを理解するのに役立ちます。

###### ネイティブ MSAL アプリケーション

シナリオ: Apple デバイスで実行されている MSAL (例: **Microsoft To Do** クライアント) を使用するように開発されたアプリケーションは、Microsoft Entra で保護されたサービス (例: **Microsoft To Do Service**) をaccessするために、ユーザーを Microsoft Entra アカウントでサインインさせる必要があります。

[Image: PRT を使用した MSAL アプリの認証フローを示す GIF アニメーション。]

1. MSAL で開発されたアプリケーションは SSO 拡張機能を直接呼び出し、PRT と、アプリケーションによるトークン要求 (Microsoft Entra で保護されたリソースのトークンの要求) を Microsoft Entra トークン エンドポイントに送信します
2. Microsoft Entra IDは PRT 資格情報を検証し、アプリケーション固有のトークンを SSO 拡張ブローカーに返します
3. その後、SSO 拡張機能ブローカーはトークンを MSAL クライアント アプリケーションに渡し、MSAL クライアント アプリケーションはそれを Microsoft Entra で保護されたリソースに送信します
4. ユーザーがアプリにサインインし、認証プロセスが完了しました

###### 非 MSAL/ブラウザー SSO

シナリオ: Apple デバイスのユーザーが、Safari Web ブラウザー (または Apple ネットワーク スタックをサポートする非 MSAL のネイティブ アプリ) を開いて、Microsoft Entra で保護されたリソース (たとえば `https://office.com` など) にサインインします。

[Image: SSO 拡張機能を使用した非 MSAL アプリの高レベルの認証フローを示すアニメーション。]

1. MSAL 以外のアプリケーション (例: **Safari**) を使用して、ユーザーは Microsoft Entra 統合アプリケーション (例: office.com) にサインインしようと試み、Microsoft Entra IDからトークンを取得するためにリダイレクトされます。
2. 非 MSAL アプリケーションが MDM ペイロード構成で許可リストに登録されている限り、Apple ネットワーク スタックは認証要求をインターセプトし、SSO 拡張機能ブローカーに要求をリダイレクトします
3. SSO 拡張機能がインターセプトされた要求を受信すると、PRT が Microsoft Entra トークン エンドポイントに送信されます
4. Microsoft Entra ID PRT を検証し、アプリケーション固有のトークンを SSO 拡張機能に返します
5. アプリケーション固有のトークンは MSAL 以外のクライアント アプリケーションに渡され、クライアント アプリケーションは Microsoft Entra で保護されたサービスaccessにトークンを送信します
6. ユーザーがサインインを完了し、認証プロセスが完了しました

#### SSO 拡張機能ログの取得

SSO 拡張機能に関するさまざまな問題をトラブルシューティングするための最も便利なツールの 1 つは、Apple デバイスからのクライアント ログです。

##### Company Portal アプリから SSO 拡張機能のログを保存する

1. macOS デバイスから、[ **アプリケーション** ] フォルダーをダブルクリックします。
2. **Company Portal** アプリケーションをダブルクリックします。
3. **Company Portal**が読み込まれたら、上部のメニューバーで**Help** - &gt;**診断レポートを保存**に移動します。 アプリにサインインする必要はありません。

    [Image: [ヘルプ] トップ メニューを移動して診断レポートを保存する方法を示すスクリーンショット。]
4. Company Portalログ アーカイブを任意の場所に保存します (例: Desktop)。
5. **CompanyPortal.zip** アーカイブを開き、任意のテキスト エディターで**SSOExtension.log** ファイルを開きます。

ヒント

ログを表示する便利な方法は、[**Visual Studio Code**](https://code.visualstudio.com/download) を使用し、[**Log Viewer**](https://marketplace.visualstudio.com/items?itemName=berublan.vscode-log-viewer) 拡張機能をインストールすることです。

##### macOS でターミナルを使用して SSO 拡張機能ログを表示する

トラブルシューティングの実行中に、SSO 拡張機能ログをリアルタイムで表示しながら問題を再現すると便利な場合があります:

1. macOS デバイスから、[ **アプリケーション** ] フォルダーをダブルクリックし、[ **Utilities** ] フォルダーをダブルクリックします。
2. **ターミナル** アプリケーションをダブルクリックします。
3. ターミナルが開いたら、次のように入力します:

    ```zsh
    tail -F ~/Library/Containers/com.microsoft.CompanyPortalMac.ssoextension/Data/Library/Caches/Logs/Microsoft/SSOExtension/*
    ```

    Note

    末尾の /\* は、複数のログが存在する場合は複数のログが表示されることを示します

    ```output
    % tail -F ~/Library/Containers/com.microsoft.CompanyPortalMac.ssoextension/Data/Library/Caches/Logs/Microsoft/SSOExtension/*
    ==> /Users/<username>/Library/Containers/com.microsoft.CompanyPortalMac.ssoextension/Data/Library/Caches/Logs/Microsoft/SSOExtension/SSOExtension 2022-12-25--13-11-52-855.log <==
    2022-12-29 14:49:59:281 | I | TID=783491 MSAL 1.2.4 Mac 13.0.1 [2022-12-29 19:49:59] Handling SSO request, requested operation: 
    2022-12-29 14:49:59:281 | I | TID=783491 MSAL 1.2.4 Mac 13.0.1 [2022-12-29 19:49:59] Ignoring this SSO request...
    2022-12-29 14:49:59:282 | I | TID=783491 MSAL 1.2.4 Mac 13.0.1 [2022-12-29 19:49:59] Finished SSO request.
    2022-12-29 14:49:59:599 | I | Beginning authorization request
    2022-12-29 14:49:59:599 | I | TID=783491 MSAL 1.2.4 Mac 13.0.1 [2022-12-29 19:49:59] Checking for feature flag browser_sso_interaction_enabled, value in config 1, value type __NSCFNumber
    2022-12-29 14:49:59:599 | I | TID=783491 MSAL 1.2.4 Mac 13.0.1 [2022-12-29 19:49:59] Feature flag browser_sso_interaction_enabled is enabled
    2022-12-29 14:49:59:599 | I | TID=783491 MSAL 1.2.4 Mac 13.0.1 [2022-12-29 19:49:59] Checking for feature flag browser_sso_disable_mfa, value in config (null), value type (null)
    2022-12-29 14:49:59:599 | I | TID=783491 MSAL 1.2.4 Mac 13.0.1 [2022-12-29 19:49:59] Checking for feature flag disable_browser_sso_intercept_all, value in config (null), value type (null)
    2022-12-29 14:49:59:600 | I | Request does not need UI
    2022-12-29 14:49:59:600 | I | TID=783491 MSAL 1.2.4 Mac 13.0.1 [2022-12-29 19:49:59] Checking for feature flag admin_debug_mode_enabled, value in config (null), value type (null)
    ```
4. 問題を再現するときは、 **ターミナル** ウィンドウを開いたままにして、末尾の **SSOExtension** ログからの出力を確認します。

##### iOS での SSO 拡張機能ログのエクスポート

iOS では、macOS のように SSO 拡張機能ログをリアルタイムで表示することはできません。 iOS SSO 拡張機能ログは、Microsoft Authenticator アプリからエクスポートしてから、別のデバイスから確認できます。

1. Microsoft Authenticator アプリを開きます。

    [Image: iOS の Microsoft Authenticator アプリのアイコンを示すスクリーンショット]
2. 左上のメニュー ボタンを押します。

    Microsoft Authenticator アプリでメニューボタンの場所を示すスクリーンショット
3. "フィードバックの送信" オプションを選択します。

    Microsoft Authenticator アプリのフィードバック送信オプションの場所を示すスクリーンショット
4. "問題が発生した場合" オプションを選択します。

    [Image: Microsoft Authenticator アプリにおける「問題がある場合」オプションの場所を示すスクリーンショット]
5. 診断データの表示オプションを押します。

    [Image: Microsoft Authenticator アプリの [診断データの表示] ボタンを示すスクリーンショット]

    ヒント

    Microsoft Supportを使用している場合は、この段階で **Send** ボタンを押して、サポートするログを送信できます。 これによりインシデント ID が提供され、Microsoft Support連絡先に提供できます。
6. "すべてコピー" ボタンを押して、ログを iOS デバイスのクリップボードにコピーします。 その後、ログ ファイルを他の場所に保存して確認したり、メールやその他のファイル共有方法を介して送信したりすることができます。

    [Image: Microsoft Authenticator アプリ内の［すべてのログのコピー］オプションを表示するスクリーンショットです。]

#### SSO 拡張機能ログについて

SSO 拡張機能ログの分析は、認証要求をMicrosoft Entra IDに送信するアプリケーションからの認証フローをトラブルシューティングする優れた方法です。 SSO 拡張機能ブローカーが呼び出されるたびに、一連のログ アクティビティの結果が返され、これらのアクティビティは **承認要求**と呼ばれます。 ログには、トラブルシューティングに役立つ次の情報が含まれています:

- 機能フラグの構成
- 承認要求の種類
    - ネイティブ MSAL
    - 非 MSAL/ブラウザー SSO
- 資格情報の取得/storage操作のための macOS キーチェーンとの対話
- Microsoft Entra サインイン イベントの関連付け ID
    - PRT の取得
    - デバイス登録

注意事項

SSO 拡張機能ログは、特にキーチェーンの資格情報操作を確認する場合、非常に詳細です。 このため、トラブルシューティングでは、ログを確認する前にシナリオを理解するのが常に最善です。

##### ログの構造

SSO 拡張機能ログは列に分割されます。 次のスクリーンショットは、ログの列の内訳を示しています:

[Image: SSO 拡張機能ログの列構造を示すスクリーンショット。]

| 列 | 列名 | 説明 |
| --- | --- | --- |
| **1** | **ローカル日付/時刻** | **表示されるローカル**日付と時刻 |
| **2** | ** I-情報W-警告E-エラー** | 情報、警告、エラーを表示します |
| **3** | **スレッド ID (TID)** | SSO 拡張機能ブローカー アプリの実行のスレッド ID を表示します |
| **4** | **MSAL バージョン番号** | Microsoft Enterprise SSO 拡張機能ブローカー プラグインは、MSAL アプリとしてビルドされています。 この列は、ブローカー アプリが実行されている MSAL のバージョンを示します |
| **5** | **macOS バージョン** | macOS オペレーティング システムのバージョンを表示します |
| **6** | **UTC 日付/時刻** | **表示される UTC** 日付と時刻 |
| **7** | **関連付け ID** | Microsoft Entra ID またはキーチェーン操作に関連するログ内の行は、UTC 日付/時刻の列に関連付け ID を追加して拡張されます。 |
| **8** | **メッセージ** | ログの詳細なメッセージを表示します。 トラブルシューティング情報のほとんどは、この列を調べることで確認できます |

##### 機能フラグの構成

Microsoft Enterprise SSO 拡張機能の MDM 構成中に、SSO 拡張機能の動作を変更するための指示として、オプションの拡張機能固有のデータを送信できます。 これらの構成固有の手順は **、機能フラグ**と呼ばれます。 機能フラグの構成は非 MSAL/ブラウザーによる SSO 承認要求の種類では特に重要です。これは、拡張機能が呼び出されているかどうかをバンドル ID で判断できるためです。 [機能フラグのドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#more-configuration-options)。 すべての承認要求は、機能フラグ構成レポートで始まります。 次のスクリーンショットで、機能フラグの構成の例を見てみましょう:

[Image: Microsoft SSO 拡張機能の機能フラグ構成の例を示すスクリーンショット。]

| コールアウト | 機能フラグ | 説明 |
| --- | --- | --- |
| **1** | **[ブラウザーSSOインタラクションが有効](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#allow-users-to-sign-in-from-applications-that-dont-use-msal-and-the-safari-browser)** | 非 MSAL または Safari ブラウザーでは、PRT をブートストラップできます |
| **2** | **ブラウザ\_SSO\_無効化\_MFA** | (現在は非推奨) PRT 資格情報のブートストラップ中に、既定では MFA が必要です。 この構成が **null** に設定されていることに注意してください。つまり、既定の構成が適用されます |
| **3** | **[アプリの明示的なプロンプトを無効にする](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#disable-oauth-2-application-prompts)** | プロンプトを減らすために、アプリケーションからの **prompt=login** 認証要求を置き換えます |
| **4** | **[アプリプレフィックス許可リスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#enable-sso-for-all-apps-with-a-specific-bundle-id-prefix)** | **`com.microsoft.`** で始まるバンドル ID を持つ非 MSAL アプリケーションは、SSO 拡張ブローカーによってインターセプトおよび処理できます |

重要

機能フラグが **null** に設定されている場合は、 **既定** の構成が設定されていることを意味します。 **[機能フラグのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#more-configuration-options)**で詳細を確認する

##### MSAL ネイティブ アプリケーションのサインイン フロー

次のセクションでは、ネイティブ MSAL アプリケーションの認証フローの SSO 拡張機能ログを調べる方法について説明します。 この例では、クライアント アプリケーションとして [MSAL macOS/iOS サンプル アプリケーション](https://github.com/AzureAD/microsoft-authentication-library-for-objc)を使用しており、アプリケーションはサインイン ユーザーの情報を表示するために Microsoft Graph API を呼び出しています。

###### MSAL ネイティブ: 対話型フローのチュートリアル

対話型サインオンを成功させるには、次のアクションを実行する必要があります:

1. ユーザーは、MSAL macOS サンプル アプリにサインインします。
2. Microsoft SSO 拡張機能ブローカーが呼び出され、要求が処理されます。
3. Microsoft SSO 拡張機能ブローカーは、サインインしているユーザーの PRT を取得するためのブートストラップ プロセスを実行します。
4. PRT をキーチェーンに保存します。
5. Microsoft Entra ID (WPJ) にデバイス登録オブジェクトが存在するかどうかを確認します。
6. クライアント アプリケーションにアクセス トークンを発行し、User.Read のスコープで Microsoft Graph にアクセスできるようにします。

重要

次のサンプル ログ スニペットにはコメント ヘッダー // による注釈が付けられています。これらはログに表示されません。 これらは、特定のアクションが実行されていることを示すのに使用されています。 コピーと貼り付けの操作に役立つように、このようにログ スニペットを文書化しています。 さらに、ログの例は、トラブルシューティングにおいて重要な行のみを示すようにトリミングされています。

ユーザーが **Call Microsoft Graph API** ボタンをクリックしてサインイン プロセスを呼び出します。

[Image: Microsoft Graph API を呼び出すボタンで起動された macOS 用の MSAL サンプルアプリを示すスクリーンショット]

```SSOExtensionLogs
//////////////////////////
//get_accounts_operation//
//////////////////////////
Handling SSO request, requested operation: get_accounts_operation
(Default accessor) Get accounts.
(MSIDAccountCredentialCache) retrieving cached credentials using credential query
(Default accessor) Looking for token with aliases (null), tenant (null), clientId 00001111-aaaa-2222-bbbb-3333cccc4444, scopes (null)
(Default accessor) No accounts found in default accessor.
(Default accessor) No accounts found in other accessors.
Completed get accounts SSO request with a personal device mode.
Request complete
Request needs UI
ADB 3.1.40 -[ADBrokerAccountManager allBrokerAccounts:]
ADB 3.1.40 -[ADBrokerAccountManager allMSIDBrokerAccounts:]
(Default accessor) Get accounts.
No existing accounts found, showing webview

/////////
//login//
/////////
Handling SSO request, requested operation: login
Handling interactive SSO request...
Starting SSO broker request with payload: {
    authority = "https://login.microsoftonline.com/common";
    "client_app_name" = MSALMacOS;
    "client_app_version" = "1.0";
    "client_id" = "00001111-aaaa-2222-bbbb-3333cccc4444";
    "client_version" = "1.1.7";
    "correlation_id" = "aaaa0000-bb11-2222-33cc-444444dddddd";
    "extra_oidc_scopes" = "openid profile offline_access";
    "instance_aware" = 0;
    "msg_protocol_ver" = 4;
    prompt = "select_account";
    "provider_type" = "provider_aad_v2";
    "redirect_uri" = "msauth.com.microsoft.idnaace.MSALMacOS://auth";
    scope = "user.read";
}

////////////////////////////////////////////////////////////
//Request PRT from Microsoft Authentication Broker Service//
////////////////////////////////////////////////////////////
Using request handler <ADInteractiveDevicelessPRTBrokerRequestHandler: 0x117ea50b0>
(Default accessor) Looking for token with aliases (null), tenant (null), clientId 11112222-bbbb-3333-cccc-4444dddd5555, scopes (null)
Attempting to get Deviceless Primary Refresh Token interactively.
Caching AAD Environements
networkHost: login.microsoftonline.com, cacheHost: login.windows.net, aliases: login.microsoftonline.com, login.windows.net, login.microsoft.com, sts.windows.net
networkHost: login.partner.microsoftonline.cn, cacheHost: login.partner.microsoftonline.cn, aliases: login.partner.microsoftonline.cn, login.chinacloudapi.cn
networkHost: login.microsoftonline.de, cacheHost: login.microsoftonline.de, aliases: login.microsoftonline.de
networkHost: login.microsoftonline.us, cacheHost: login.microsoftonline.us, aliases: login.microsoftonline.us, login.usgovcloudapi.net
networkHost: login-us.microsoftonline.com, cacheHost: login-us.microsoftonline.com, aliases: login-us.microsoftonline.com
Resolved authority, validated: YES, error: 0
[MSAL] Resolving authority: Masked(not-null), upn: Masked(null)
[MSAL] Resolved authority, validated: YES, error: 0
[MSAL] Start webview authorization session with webview controller class MSIDAADOAuthEmbeddedWebviewController: 
[MSAL] Presenting web view controller. 
```

ログ サンプルは、次の 3 つのセグメントに分類できます:

| セグメント | 説明 |
| --- | --- |
| **`get_accounts_operation`** | キャッシュに既存のアカウントがあるかどうかを確認します - **ClientID**: この MSAL アプリのMicrosoft Entra IDに登録されているアプリケーション ID**ADB 3.1.40** は、Microsoft Enterprise SSO Extension Broker プラグインのバージョンを示します |
| **`login`** | ブローカーは、Microsoft Entra IDの要求を処理します。 - **対話型 SSO 要求の処理...**: 対話型要求を示します - **correlation\_id**: Microsoft Entra サーバー側サインイン ログとの相互参照に役立ちます  - **scope**: **User.Read** Microsoft Graphから要求される API アクセス許可スコープ - **client\_version**: アプリケーションが実行されている MSAL のバージョン - **redirect\_uri**: MSAL アプリは形式を使用します **`msauth.com.<Bundle ID>://auth`** |
| **PRT 要求** | PRT を対話形式で取得するためのブートストラップ プロセスが開始され、Web ビュー SSO セッションがレンダリングされます**Microsoft 認証ブローカー サービス** - **clientId: 29d9ed98-a469-4536-ade2-f981bc1d605e** - すべての PRT 要求が Microsoft 認証ブローカー サービスに対して行われます |

SSO Web ビュー コントローラーが表示され、ユーザーは Microsoft Entra ログイン (UPN/Email) を入力するよう求められます

[Image: ユーザー情報が入力され、詳細情報の吹き出しが表示されている Apple SSO プロンプトを示すスクリーンショット。]

Note

Webview コントローラーの左下隅にある ***i*** をクリックすると、SSO 拡張機能に関する詳細情報と、それを呼び出したアプリに関する詳細が表示されます。

[Image: プロンプト SSO 画面の SSO 拡張機能に関する詳細情報を示すスクリーンショット。] ユーザーが Microsoft Entra 資格情報を正常に入力すると、次のログ エントリが SSO 拡張機能ログに書き込まれます

```
SSOExtensionLogs
///////////////
//Acquire PRT//
///////////////
[MSAL] -completeWebAuthWithURL: msauth://microsoft.aad.brokerplugin/?code=(not-null)&client_info=(not-null)&state=(not-null)&session_state=(not-null)
[MSAL] Dismissed web view controller.
[MSAL] Result from authorization session callbackURL host: microsoft.aad.brokerplugin , has error: NO
[MSAL] (Default accessor) Looking for token with aliases (
    "login.windows.net",
    "login.microsoftonline.com",
    "login.windows.net",
    "login.microsoft.com",
    "sts.windows.net"
), tenant (null), clientId 29d9ed98-a469-4536-ade2-f981bc1d605e, scopes (null)
Saving PRT response in cache since no other PRT was found
[MSAL] Saving keychain item, item info Masked(not-null)
[MSAL] Keychain find status: 0
Acquired PRT.

///////////////////////////////////////////////////////////////////////
//Discover if there is an Azure AD Device Registration (WPJ) present //
//and if so re-acquire a PRT and associate with Device ID            //
///////////////////////////////////////////////////////////////////////
WPJ Discovery: do discovery in environment 0
Attempt WPJ discovery using tenantId.
WPJ discovery succeeded.
Using cloud authority from WPJ discovery: https://login.microsoftonline.com/common
ADBrokerDiscoveryAction completed. Continuing Broker Flow.
PRT needs upgrade as device registration state has changed. Device is joined 1, prt is joined 0
Beginning ADBrokerAcquirePRTInteractivelyAction
Attempting to get Primary Refresh Token interactively.
Acquiring broker tokens for broker client id.
Resolving authority: Masked(not-null), upn: auth.placeholder-61945244__domainname.com
Resolved authority, validated: YES, error: 0
Enrollment id read from intune cache : (null).
Handle silent PRT response Masked(not-null), error Masked(null)
Acquired broker tokens.
Acquiring PRT.
Acquiring PRT using broker refresh token.
Requesting PRT from authority https://login.microsoftonline.com/<TenantID>/oauth2/v2.0/token
[MSAL] (Default accessor) Looking for token with aliases (
    "login.windows.net",
    "login.microsoftonline.com",
    "login.windows.net",
    "login.microsoft.com",
    "sts.windows.net"
), tenant (null), clientId (null), scopes (null)
[MSAL] Acquired PRT successfully!
Acquired PRT.
ADBrokerAcquirePRTInteractivelyAction completed. Continuing Broker Flow.
Beginning ADBrokerAcquireTokenWithPRTAction
Resolving authority: Masked(not-null), upn: auth.placeholder-61945244__domainname.com
Resolved authority, validated: YES, error: 0
Handle silent PRT response Masked(not-null), error Masked(null)

//////////////////////////////////////////////////////////////////////////
//Provide Access Token received from Azure AD back to Client Application// 
//and complete authorization request                                    //
//////////////////////////////////////////////////////////////////////////
[MSAL] (Default cache) Removing credentials with type AccessToken, environment login.windows.net, realm TenantID, clientID 00001111-aaaa-2222-bbbb-3333cccc4444, unique user ID dbb22b2f, target User.Read profile openid email
ADBrokerAcquireTokenWithPRTAction succeeded.
Composing broker response.
Sending broker response.
Returning to app (msauth.com.microsoft.idnaace.MSALMacOS://auth) - protocol version: 3
hash: AA11BB22CC33DD44EE55FF66AA77BB88CC99DD00
payload: Masked(not-null)
Completed interactive SSO request.
Completed interactive SSO request.
Request complete
Completing SSO request...
Finished SSO request.
```

認証/承認フローのこの時点で、PRT はブートストラップされており、macOS キーチェーンアクセスに表示されます。 PRT の Keychain Access を確認するを参照してください。 **MSAL macOS サンプル** アプリケーションは、Microsoft SSO Extension Broker から受信したaccess トークンを使用して、ユーザーの情報を表示します。

次に、クライアント側 SSO 拡張機能ログから収集された関連付け ID に基づいて、サーバー側 [の Microsoft Entra サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details) ログを調べます。 詳細については、「Microsoft Entra ID の Sign-in ログ」を参照してください。

###### 関連付け ID フィルターを使用して Microsoft Entra サインイン ログを表示する

1. アプリケーションが登録されているテナントの Microsoft Entra サインインを開きます。
2. [ **ユーザー サインイン (対話型)]**を選択します。
3. [ **フィルターの追加]** を選択し、[ **関連付け ID** ] ラジオ ボタンを選択します。
4. SSO 拡張機能ログから取得した関連付け ID をコピーして貼り付け、[ **適用**] を選択します。

MSAL 対話型ログイン フローでは、リソース **Microsoft Authentication Broker** サービスの対話型サインインが表示されます。 このイベントは、ユーザーが PRT をブートストラップするためにパスワードを入力した場所です。

 Microsoft Entra ID の対話型ユーザー サインインを示すスクリーンショットで、 Microsoft Authentication Broker Service への対話型サインインを表示しています。

クライアント アプリケーションの要求のaccess トークンを取得するために PRT が使用されるため、非対話型サインイン イベントもあります。 関連付け ID フィルターによる Microsoft Entra サインイン ログの表示に従いますが、手順 2 で **User サインイン (非対話型)** を選択します。

[Image: Microsoft Graph のアクセス トークンを取得するために PRT を使用するSSO 拡張機能を示すスクリーンショット。]

| サインイン ログ属性 | 説明 |
| --- | --- |
| **アプリケーション** | クライアント アプリケーションが認証する Microsoft Entra テナントにおけるアプリケーションの登録の表示名。 |
| **アプリケーション ID** | Microsoft Entra テナントにおけるアプリケーションの登録の ClientID とも呼ばれます。 |
| **資源** | クライアント アプリケーションがaccessを取得しようとしている API リソース。 この例では、リソースは **Microsoft Graph API** です。 |
| **受信トークンの種類** | **Primary Refresh Token (PRT)** の受信トークンの種類は、リソースのaccess トークンを取得するために使用されている入力トークンを示します。 |
| **ユーザー エージェント** | この例のユーザー エージェント文字列は、 **Microsoft SSO 拡張機能** がこの要求を処理しているアプリケーションであることを示しています。 SSO 拡張機能が使用されており、ブローカー認証要求が行われていることを示す便利な指標です。 |
| **Microsoft Entra アプリ認証ライブラリ** | MSAL アプリケーションが使用されている場合、ライブラリとプラットフォームの詳細がここに書き込まれます。 |
| **Oauth スコープ情報** | アクセストークン用に要求されたOauth2スコープ情報。 (**User.Read**、**profile**、**openid**、**email**)。 |

###### MSAL ネイティブ: サイレント フローのチュートリアル

一定期間が経過すると、access トークンは無効になります。 そのため、ユーザーが **Call Microsoft Graph API** ボタンを再度クリックするとします。 SSO 拡張機能は、既に取得した PRT を使用してaccess トークンの更新を試みます。

```
SSOExtensionLogs
/////////////////////////////////////////////////////////////////////////
//refresh operation: Assemble Request based on User information in PRT  /  
/////////////////////////////////////////////////////////////////////////
Beginning authorization request
Request does not need UI
Handling SSO request, requested operation: refresh
Handling silent SSO request...
Looking account up by home account ID dbb22b2f, displayable ID auth.placeholder-61945244__domainname.com
Account identifier used for request: Masked(not-null), auth.placeholder-61945244__domainname.com
Starting SSO broker request with payload: {
    authority = "https://login.microsoftonline.com/<TenantID>";
    "client_app_name" = MSALMacOS;
    "client_app_version" = "1.0";
    "client_id" = "00001111-aaaa-2222-bbbb-3333cccc4444";
    "client_version" = "1.1.7";
    "correlation_id" = "aaaa0000-bb11-2222-33cc-444444dddddd";
    "extra_oidc_scopes" = "openid profile offline_access";
    "home_account_id" = "<UserObjectId>.<TenantID>";
    "instance_aware" = 0;
    "msg_protocol_ver" = 4;
    "provider_type" = "provider_aad_v2";
    "redirect_uri" = "msauth.com.microsoft.idnaace.MSALMacOS://auth";
    scope = "user.read";
    username = "auth.placeholder-61945244__domainname.com";
}
//////////////////////////////////////////
//Acquire Access Token with PRT silently//
//////////////////////////////////////////
Using request handler <ADSSOSilentBrokerRequestHandler: 0x127226a10>
Executing new request
Beginning ADBrokerAcquireTokenSilentAction
Beginning silent flow.
[MSAL] Resolving authority: Masked(not-null), upn: auth.placeholder-61945244__domainname.com
[MSAL] (Default cache) Removing credentials with type AccessToken, environment login.windows.net, realm <TenantID>, clientID 00001111-aaaa-2222-bbbb-3333cccc4444, unique user ID dbb22b2f, target User.Read profile openid email
[MSAL] (MSIDAccountCredentialCache) retrieving cached credentials using credential query
[MSAL] Silent controller with PRT finished with error Masked(null)
ADBrokerAcquireTokenWithPRTAction succeeded.
Composing broker response.
Sending broker response.
Returning to app (msauth.com.microsoft.idnaace.MSALMacOS://auth) - protocol version: 3
hash: AA11BB22CC33DD44EE55FF66AA77BB88CC99DD00
payload: Masked(not-null)
Completed silent SSO request.
Request complete
Completing SSO request...
Finished SSO request.
```

このログ サンプルは、次の 2 つのセグメントに分割できます:

| セグメント | 説明 |
| --- | --- |
| **`refresh`** | ブローカーは、Microsoft Entra IDの要求を処理します。 - **サイレント SSO 要求の処理...**: サイレント要求を示します - **correlation\_id**: Microsoft Entra サーバー側サインイン ログとの相互参照に役立ちます  - **scope**: **User.Read** Microsoft Graphから要求される API アクセス許可スコープ - **client\_version**: アプリケーションが実行されている MSAL のバージョン - **redirect\_uri**: MSAL アプリは形式を使用します **`msauth.com.<Bundle ID>://auth`** **更新** には、要求ペイロードに大きく異なる点があります。 - **authority**: Microsoft Entra テナントの URL エンドポイントを含み、**共通**エンドポイントとは異なります - **home\_account\_id**: ユーザー アカウントを **&lt;UserObjectId&gt;.&lt; 形式で表示するTenantID&gt;** - **username**: ハッシュされた UPN 形式 **auth.placeholder-XXXXXXXX\_\_domainname.com** |
| **PRTを更新してアクセス トークンを取得する** | この操作により、PRT が再検証され、必要に応じて更新されてから、access トークンが呼び出し元のクライアント アプリケーションに返されます。 |

クライアント側**の SSO 拡張機能**ログから取得した**関連付け ID を**再度取得し、サーバー側の Microsoft Entra サインイン ログとの相互参照を行うことができます。

[Image: Enterprise SSO Broker プラグインを使用した Microsoft Entra サイレント サインイン要求を示すスクリーンショット。]

Microsoft Entra サインインの情報は、前のインタラクティブ ログイン セクションにおける**ログイン**操作とMicrosoft Graph リソースの情報と一致しています。

##### 非 MSAL/ブラウザー SSO アプリケーション ログイン フロー

次のセクションでは、非 MSAL/ブラウザー アプリケーション認証フローの SSO 拡張機能ログを調べる方法について説明します。 この例では、クライアント アプリケーションとして Apple Safari ブラウザーを使用しており、アプリケーションは Office.com (OfficeHome) Web アプリケーションを呼び出しています。

###### 非 MSAL/ブラウザー SSO フローのチュートリアル

正常にサインオンするには、次のアクションを実行する必要があります:

1. ブートストラップ プロセスを既に行ったユーザーに既存の PRT があるとします。
2. **Microsoft SSO Extension Broker** がデプロイされているデバイスでは、構成された**機能フラグ**がチェックされ、SSO 拡張機能によってアプリケーションを処理できることを確認します。
3. Safari ブラウザーは **Apple Networking Stack** に準拠しているため、SSO 拡張機能は Microsoft Entra 認証要求をインターセプトしようとします。
4. この PRT は、要求されるリソースのトークンを取得するために使用されます。
5. デバイスが Microsoft Entra に登録済みの場合、要求と共にデバイス ID が渡されます。
6. SSO 拡張機能によって、リソースにサインインするためのブラウザー要求のヘッダーが入力されます。

次のクライアント側 **SSO 拡張機能** ログは、要求を満たすために SSO 拡張ブローカーによって透過的に処理されている要求を示しています。

```
SSOExtensionLogs
Created Browser SSO request for bundle identifier com.apple.Safari, cookie SSO include-list (
), use cookie sso for this app 0, initiating origin https://www.office.com
Init MSIDKeychainTokenCache with keychainGroup: Masked(not-null)
[Browser SSO] Starting Browser SSO request for authority https://login.microsoftonline.com/common
[MSAL] (Default accessor) Found 1 tokens
[Browser SSO] Checking PRTs for deviceId 73796663
[MSAL] [Browser SSO] Executing without UI for authority https://login.microsoftonline.com/common, number of PRTs 1, device registered 1
[MSAL] [Browser SSO] Processing request with PRTs and correlation ID in headers (null), query aaaa0000-bb11-2222-33cc-444444dddddd
[MSAL] Resolving authority: Masked(not-null), upn: Masked(null)
[MSAL] No cached preferred_network for authority
[MSAL] Caching AAD Environements
[MSAL] networkHost: login.microsoftonline.com, cacheHost: login.windows.net, aliases: login.microsoftonline.com, login.windows.net, login.microsoft.com, sts.windows.net
[MSAL] networkHost: login.partner.microsoftonline.cn, cacheHost: login.partner.microsoftonline.cn, aliases: login.partner.microsoftonline.cn, login.chinacloudapi.cn
[MSAL] networkHost: login.microsoftonline.de, cacheHost: login.microsoftonline.de, aliases: login.microsoftonline.de
[MSAL] networkHost: login.microsoftonline.us, cacheHost: login.microsoftonline.us, aliases: login.microsoftonline.us, login.usgovcloudapi.net
[MSAL] networkHost: login-us.microsoftonline.com, cacheHost: login-us.microsoftonline.com, aliases: login-us.microsoftonline.com
[MSAL] Resolved authority, validated: YES, error: 0
[MSAL] Found registration registered in login.microsoftonline.com, isSameAsRequestEnvironment: Yes
[MSAL] Passing device header in browser SSO for device id 43cfaf69-0f94-4d2e-a815-c103226c4c04
[MSAL] Adding SSO-cookie header with PRT Masked(not-null)
SSO extension cleared cookies before handling request 1
[Browser SSO] SSO response is successful 0
[MSAL] Keychain find status: 0
[MSAL] (Default accessor) Found 1 tokens
Request does not need UI
[MSAL] [Browser SSO] Checking PRTs for deviceId 73796663
Request complete
```

| SSO 拡張機能ログのコンポーネント | 説明 |
| --- | --- |
| **作成されたブラウザー SSO 要求** | すべての非 MSAL/ブラウザー SSO 要求は、次の行で始まります: - **バンドル識別子**: バンドル ID: `com.apple.Safari` - **発信元の開始**: Microsoft Entra IDのログインURLのいずれかにアクセスする前にブラウザーがアクセスするWeb URL (https://office.com) |
| **認証機関に対するブラウザーのSSOリクエストを開始** | PRT の数と、デバイスが登録されているかどうかを解決します: https://login.microsoftonline.com/common、**PRT の数 1、登録済みのデバイス 1** |
| **関連付け ID** | [ブラウザー SSO] ヘッダー (null)、クエリ **&lt;CorrelationID&gt;** で PRT と関連付け ID の要求を処理しています。 この ID は、Microsoft Entra サーバー側サインイン ログとの相互参照で重要です |
| **デバイスの登録** | 必要に応じて、デバイスが Microsoft Entra に登録済みの場合、SSO 拡張機能はブラウザー SSO 要求でデバイス ヘッダーを渡すことができます: - Found registration registered in (次に登録されている登録が見つかりました) - **login.microsoftonline.com、isSameAsRequestEnvironment: Yes**Passing device header in browser SSO for device id (ブラウザー SSO で **デバイス ID **`43cfaf69-0f94-4d2e-a815-c103226c4c04` のデバイス ヘッダーを渡しています) |

次に、ブラウザー SSO 拡張機能ログから取得した関連付け ID を使用して、Microsoft Entra サインイン ログを相互参照します。

&&& Microsoft Entra サインイン ログにおける Browser SSO Extension の相互参照を示すスクリーンショット&&

| サインイン ログ属性 | 説明 |
| --- | --- |
| **アプリケーション** | クライアント アプリケーションが認証する Microsoft Entra テナントにおけるアプリケーションの登録の表示名。 この例では、表示名は **OfficeHome です**。 |
| **アプリケーション ID** | Microsoft Entra テナントにおけるアプリケーションの登録の ClientID とも呼ばれます。 |
| **資源** | クライアント アプリケーションがaccessを取得しようとしている API リソース。 この例では、リソースは **OfficeHome** Web アプリケーションです。 |
| **受信トークンの種類** | **Primary Refresh Token (PRT)** の受信トークンの種類は、リソースのaccess トークンを取得するために使用されている入力トークンを示します。 |
| **認証方法が検出されました** | [ **認証の詳細** ] タブの **Microsoft Entra SSO プラグイン** の値は、ブラウザーの SSO 要求を容易にするために SSO 拡張機能が使用されていることを示す便利なインジケーターです。 |
| **Microsoft Entra SSO 拡張機能のバージョン** | [ **追加の詳細** ] タブの下に、この値は Microsoft Enterprise SSO 拡張機能ブローカー アプリのバージョンを示します。 |
| **デバイス ID** | デバイスが登録されている場合、SSO 拡張機能はデバイス ID を渡してデバイス認証要求を処理できます。 |
| **オペレーティング システム** | オペレーティング システムの種類を示します。 |
| **準拠** | SSO 拡張機能を使用すると、デバイス ヘッダーを渡すことでコンプライアンス ポリシーを支援できます。 要件は次のとおりです。 - **Microsoft Entra デバイスの登録** - **MDM 管理** - **Intune または Intune パートナー コンプライアンス** |
| **管理** | デバイスが管理下にあることを示します。 |
| **参加の種類** | macOS と iOS (登録されている場合) の種類は、 **Microsoft Entra 登録済み**のみです。 |

ヒント

Jamf Connect を使用する場合は、Jamf Connect と Microsoft Entra ID の統合に関する latest Jamf ガイダンスに従うことをお勧めします。 推奨される統合パターンにより、Jamf Connect が条件付きAccess ポリシーとMicrosoft Entra ID Protectionで適切に動作します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension"} -->
## macOS プラットフォームのシングル サインオンの既知の問題とトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension
- Service: entra-id / devices
- Article date: 2024-12-19
- Summary: macOS プラットフォーム シングル サインオン (PSSO) に関する既知の問題を特定して解決します。

この記事では、macOS プラットフォーム シングル サインオン (PSSO) に関する現在の既知の問題とよく寄せられる質問の概要をまとめています。 問題の解決策と、カバーされていない問題を報告する方法に関する情報を提供します。 この記事にはトラブルシューティングのガイダンスも含まれています。

### 検証するシナリオ

PSSO がデバイスにデプロイされると、デプロイが成功したことを確認するために実行できる検証シナリオがいくつかあります。 問題がある場合は、問題の報告を参照して詳細な手順を確認してください。

#### パスワード変更イベント

セルフサービス パスワード リセット (SSPR) を通じて行われた Microsoft Entra ID パスワードの変更がローカル マシンに正常に同期されていることを確認します。 ユーザーの Microsoft Entra ID パスワードが Mac に同期された後に変更された場合、ユーザーは 4 時間以内に新しいパスワードを入力するよう求められます。

#### デバイスから PSSO 登録を修復または削除する

このセクションでは、macOS のバージョンに応じて、Mac デバイスから PSSO 登録を修復または削除する方法について説明します。

## [macOS 14](#tab/macOS14)
macOS 14 Sonoma では、デバイスの登録に問題がある場合は、既存の PSSO 登録を修復できます。

1. **[設定]** アプリを開き、**[ユーザーとグループ]**&gt;**[ネットワーク アカウント サーバー]** に移動します。
2. **[編集]** を選択してから、**[修復]** を選択します。 初期登録時と同じデバイス登録フローが実行されます。

次の手順を実行して、デバイスの登録を完全に解除することもできます。

1. **[ポータル サイト]** アプリを開き、**[ユーザー設定]** に移動します。
2. デバイスの登録を解除するには、**[登録解除]** を選択します。

## [macOS 13](#tab/macOS13)
macOS 13 Ventura で、デバイスの PSSO 登録に問題がある場合、またはデバイスの登録を解除する必要がある場合は、ポータル サイトを使用して組織からデバイスを削除します。

1. **[ポータル サイト]** アプリを開き、**[ユーザー設定]** に移動します。
2. デバイスの登録を解除するには、**[登録解除]** を選択します。

エラーの結果としてデバイスの登録を解除した場合は、「[既定のエクスペリエンスの間に Microsoft Entra ID を使用して Mac デバイスを参加させる](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-macos-platform-single-sign-on)」または「[ポータル サイトを使用して Microsoft Entra ID で Mac デバイスを参加させる](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-microsoft-entra-company-portal)」を参照してデバイスを再登録してください。

---

#### システムの更新後に Enterprise Single Sign-on (SSO) プラグインがアクティブ化されない

システム更新がデバイスに適用された後、Enterprise SSO プラグインがアクティブ化されない場合は、ソフトウェア更新デーモンを再起動する必要があります。

1. **[ターミナル]** アプリを開き、次のコマンドを入力して `swcd` プロセスを強制終了します。

    ```console
    sudo killall swcd
    ```
2. 次のコマンドを入力してプロセスをリセットします。

    ```console
    sudo swcutil reset
    ```

#### プラットフォーム SSO で除外される TLS 検査 URL

##### 1. PSSO 登録フローで許可する必要がある URL

**[ここに](https://learn.microsoft.com/ja-jp/entra/identity/devices/plan-device-deployment#network-requirements-for-device-registration-with-microsoft-entra)**記載されている URL へのトラフィックが既定で許可され、TLS インターセプトまたは検査から明示的に除外されていることを確認します。 これは、TLS チャレンジに依存する登録およびデバイス認証フローが正常に完了するために重要です。

##### 2. PSSO トークン取得フローとトークン更新フローで除外する必要がある URL

プラットフォーム SSO トークンの取得と更新をプラットフォーム SSO 対象デバイスで正常に実行できるように、以下の URL が TLS 傍受/検査から除外されていることを確認します。

- app-site-association.cdn-apple.com
- app-site-association.networking.apple
- login.microsoftonline.com
- login.microsoft.com
- sts.windows.net
- login.partner.microsoftonline.cn(\*)
- login.chinacloudapi.cn(\*)
- login.microsoftonline.us(\*)
- login-us.microsoftonline.com(\*)
- config.edge.skype.com(\*\*)

SSO 拡張機能が機能するためには、Apple のアプリ サイトアソシエーション ドメインが不可欠です。 ソブリンクラウドドメインは、環境でそれらに依存している場合にのみ許可する必要があります。 (\*\*)実験構成サービス (ECS) との通信を維持することで、Microsoft が重大なバグにタイムリーに対応できるようになります。

Note

企業プロキシが Apple システムルート証明書の外部で証明書信頼チェーンを使用している場合、テナント制限 v2 は、企業プロキシ経由のクライアント シグナリングを使用する macOS Platform SSO 機能では機能しません。 これは、Appleの制限であり、プラットフォームSSOがテナントの制限と互換性がなく、信頼されていない証明書を使用して中間ネットワークソリューションがヘッダーを挿入するときに発生します。 Apple は、Apple システムの信頼されたルート証明書ストアに独自の PKI 証明書を追加する顧客をサポートしていません。 システム提供のルート証明機関 (CA) に TLS 証明書の発行者が含まれている場合、テナント制限の TLS 検査は PSSO と互換性があります。 代替オプションが [TRv2 の既知の制限事項](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#known-limitations)に記載されている

Important

**注: 登録フローで使用される TLS エンドポイントに対する最新の更新が行われています。 中断を回避するために、環境の許可リストに最新の URL 要件が反映されていることを確認してください。**

#### パスワードリセット中に発行された一時パスワードを Platform SSO と同期できない

パスワードのリセット中に発行された一時パスワードをローカル デバイスに同期することはできません。 ユーザーは、SSO 拡張機能を使用して一時パスワードでパスワード リセット プロセスを完了することをお勧めします。

#### デバイスの移行

以前に登録したデバイス (キーチェーン アクセスに Workplace Join キーがある) が、PSSO デバイスの登録に成功した後にキーを削除することを確認します。

### よく寄せられる質問

#### ハイブリッド参加の展開で macOS PSSO を使用できますか?

いいえ、macOS PSSO は、Microsoft Entra 参加のデプロイでのみサポートされます。 Mac ユーザーには完全なクラウド ベースへの移行を推奨しているため、ハイブリッド参加のデプロイをサポートする予定はありません。

#### プラットフォーム SSO を使用しているときにパスワードを変更するにはどうすればよいですか?

ユーザーは、デバイス上のセルフサービス パスワード リセット (SSPR) を使用してパスワードを変更できます。

SSPR が別のコンピューターで実行された場合、ユーザーは古いパスワードまたは新しいパスワードを使用して Mac デバイスにサインインできます。 古いパスワードを使用すると、デバイスのロックが解除され、データの同期を続行するための新しいパスワードの入力をユーザーに求められます。 新しいパスワードを使用すると、デバイスのロックが解除され、データがすぐに同期されます。

組織にパスワード管理のオプションをさらに提供できるため、IT 管理者は可能な限り[マネージド Apple ID](https://support.apple.com/guide/deployment/depcaa668a58/web) を使用することをお勧めします。

#### パスワードを忘れた場合はどうすればいいですか?

##### パスワード同期

ユーザーはログイン画面またはロック画面でパスワードをリセットできます。 ユーザーが IT 管理者から一時的なパスワードを受け取った場合は、別のデバイスを使用してサインインし、新しいパスワードを設定し、自分のデバイスへのログイン プロンプトでその新しいパスワードを使用する必要があります。 詳細については、[忘れたパスワード](https://support.apple.com/102633)に関する Apple のドキュメントを参照してください。

Important

PSSO には、復旧中に登録の削除が発生する既知の問題があり、復旧後にユーザーに再登録を求める可能性があります。 これは正しい動作です。

IT 管理者は、パスワードを忘れた場合にデータを確実に回復できるように、Keyvault の回復も有効にする必要があります。 詳細については、「[Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos#password)での macOS デバイスのプラットフォーム SSO の構成」を参照してください。

Note

デバイスが起動し、FileVault 暗号化がある場合、新しいMicrosoft Entraパスワードは macOS15 でのみ機能します。

##### セキュリティで保護されたエンクレーブ

ユーザーは、Apple ID または管理者回復キーを使用してローカル パスワードをリセットできます。

### 既知の問題

#### macOS Sequoia での予期しない/頻繁な再登録プロンプト

Note

以下で説明する macOS 15.x での PSSO 再登録の問題に関する最新の更新プログラム: Apple は、修正プログラムが macOS 15.3 に展開されていることを確認しました。 macOS 15.3 以降でユーザーに再登録の問題が引き続き発生する場合は、Apple と連携し、Apple サポート経由でログを共有してください。

macOS 15 以降 (Sequoia) には、PSSO デバイス構成が破損する可能性がある既知のコンカレンシーの問題があります。 デバイス構成は、システムの AppSSOAgent プロセスと AppSSODaemon プロセスからの同時更新によって破損する可能性があります。 構成が破損すると、オペレーティング システムによって再登録修復フローがトリガーされ、ユーザーに対して予期しない登録プロンプトが表示されます。

この問題は現在、Apple によって調査されています。

影響を受けるユーザーからの Sysdiagnose ログに次のエラーが含まれています。

```
Error Domain=com.apple.PlatformSSO Code=-1001 "Error deserializing device config." UserInfo={NSLocalizedDescription=Error deserializing device config., NSUnderlyingError=0x9480343f0 {Error Domain=NSCocoaErrorDomain Code=3840 "Garbage at end around line 27, column 1." UserInfo={NSDebugDescription=Garbage at end around line 27, column 1., NSJSONSerializationErrorIndex=3052}}}
```

このエラーが発生したユーザーと管理者は、Apple Care の問題を報告し、Apple と連携して問題を解決することをお勧めします。

#### パスコード ポリシーの複雑さの不一致

適用された MDM 構成で、マシンへのサインインに使用される Microsoft Entra アカウントよりも複雑度の高いローカル パスワード ポリシーが指定されるという既知の問題があります。 この場合、Microsoft Entra ID とローカル マシン間のパスワード同期操作は失敗します。

MDM 構成中に、パスワードの複雑さの要件がローカル マシンと Microsoft Entra ID 間で同一であることを確認します。

#### 長時間の操作

## [macOS 14](#tab/macOS14)
設定アプリケーションからのデバイスの登録に失敗した場合は、約 10 分後にデバイス登録ポップアップが再度表示されるので、もう一度試してください。

## [macOS 13](#tab/macOS13)
デバイスの登録が完了するまでに数分かかる場合があります。 デバイスの登録に失敗した場合は、数分待ち、プロンプトが表示されたらもう一度試してください。

---

#### 登録中に SSO 認証プロンプト ダイアログが閉じられました

SSO 認証プロンプト ダイアログを閉じて登録プロセスをキャンセルする場合は、Mac デバイスからサインアウトして再度サインインする必要があります。 サインインに成功すると、登録通知が再び表示されて正常に機能します。

#### ユーザーごとの MFA によってパスワード同期エラーが発生する

PSSO が設定されているアカウントでユーザーごとの MFA が有効になっている場合、次の手順で Microsoft Entra ID 資格情報を入力できず、エラーが発生します。 このエラーを回避するには、管理者は [Microsoft Entra ID の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-turn-off-per-user-mfa)に従って条件付きアクセス MFA が有効になっていることを確認する必要があります。 これにより、登録中に MFA が抑制されて、パスワード同期が正常に完了します。

#### FileVault 回復または MDM 駆動型回復からパスワード リセットを開始した後は、PSSO の再登録が必要です

セキュリティで保護されたエンクレーブ キーはローカル アカウントのパスワードによって保護されているため、このパスワード (FileVault や MDM ベースの回復など) を指定せずにパスワードをリセットすると、セキュリティで保護されたエンクレーブがリセットされます。 Secure Enclave をリセットすると、このアカウントに以前保存されたキーにアクセスできなくなります。 セキュリティで保護されたエンクレーブ キーが失われたデバイスは、Platform SSO を使用するために再登録する必要があります。

### 問題の報告

PSSO で問題が発生している場合は、ポータル サイト で報告できます。

1. **[ポータル サイト]** アプリを開き、**[ヘルプ]**&gt;**[診断レポートの送信]** に移動します。
2. **[診断レポートの送信]** ウィンドウが表示されます。 ログを送信するには、**[ログのメール送信]** を選択します。
3. ウィンドウを閉じる前にインシデント ID をメモしてください。

**ターミナル** アプリを開くと、コンピューターの現在の PSSO 状態をいつでも確認できます。 次のコマンドを実行します。

```console
app-sso platform -s
```

#### お問い合わせください

皆様からのフィードバックをぜひお聞かせください。 次の情報を含める必要があります。

- Sysdiagnose ログと診断ログ
- 問題を再現する手順
- 該当する場合は、関連するスクリーンショットや録画を含めてください。

#### Sysdiagnose ログと診断ログのキャプチャ

1. ターミナルで次のコマンドを実行して、デバッグ ログの永続化を有効にします。

    ```console
    sudo log config --mode "level:debug,persist:debug" --subsystem "com.apple.AppSSO"
    ```
2. 問題を再現し、影響を受けるシナリオの新しいログが生成されます。 問題レポートに関連するタイムスタンプを指定して、ログ調査に役立ててください。
3. ターミナルで次のコマンドを実行して診断データをキャプチャします。

    ```console
    sudo sysdiagnose
    ```
4. ターミナルで次のコマンドを実行して、デバッグ ログを既定の設定にリセットします。

    ```console
    sudo log config --reset --subsystem "com.apple.AppSSO"
    ```

### トラブルシューティング ガイド

#### アクセス許可が不十分

ユーザーのアクセス許可が Microsoft Entra ID の参加と登録を完了するには不十分な場合、エラー メッセージは表示されません。 デバイスの参加と登録が正常に完了するには、登録フローを開始するユーザーが許可リストに登録されている必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)で、**Entra ID**&gt;**Devices**&gt;**Overview**&gt;**Device Settings** に移動します。
2. **[Microsoft Entra ID の参加と登録の設定]** で、**[ユーザーはデバイスを Microsoft Entra ID に参加させる可能性があります]** の切り替えメニューで **[すべて]** オプションが選択されていることを確認します。
3. **保存**を選択して、変更を適用します。

#### パスキーの問題のトラブルシューティング

[パスキーとしてのプラットフォーム資格情報] オプションは、Secure Enclave がプラットフォーム SSO の認証方法として構成されている場合にのみ使用できます。 次をチェックする必要があります。

1. 管理者が認証方法としてセキュアエンクレーブを使用してデバイスを設定し、組織 [のために](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-passkey-fido2#enable-passkey-authentication-method)パスキー (FIDO2) を有効にしていることを確認してください。
2. ユーザーは、デバイス設定で、[ポータル サイト] をパスキー プロバイダーとして有効にしていることを確認します。 **[設定]** アプリ、**[パスワード]**、**[パスワード オプション]** に移動し、**[ポータル サイト]** が有効になっていることを確認します。

#### Microsoft Edge SSO に関する問題のトラブルシューティング

プラットフォーム SSO の登録後Microsoft Edgeユーザーが SSO の問題に直面している場合は、ユーザーが Edge プロファイルにサインインしているかどうかを確認します。 ユーザーが Edge on Platform SSO 登録済みデバイスを使用するには、ブラウザー SSO のために Edge プロファイルにサインインする必要があります。

#### Google Chrome の SSO に関する問題のトラブルシューティング

Google Chrome の [Microsoft シングル サインオン](https://chromewebstore.google.com/detail/microsoft-single-sign-on/ppnbnpeolgkicgegkbkbjmhlideopiji?pli=1) 拡張機能がインストールされているユーザーの場合、Chrome ブラウザーは、SSO ユーザー エクスペリエンスとデバイスベースの条件付きアクセス ポリシーの両方について Microsoft SSO ブローカーと通信できる必要があります。 ユーザーが Google Chrome でデバイスベースの条件付きアクセス ポリシーを渡すことができない場合は、ポータル サイト アプリケーションのインストール方法に問題がある可能性があります。これにより、Chrome が SSO ブローカーと通信できなくなる可能性があります。 この問題を修復するには、次の手順を実行する必要があります。

1. Mac で **[アプリケーション]** フォルダーを開きます
2. **[ポータル サイト]** アプリケーションを右クリックし、**[ごみ箱に移動]** を選択します
3. https://go.microsoft.com/fwlink/?linkid=853070 からポータル サイト インストーラーの最新バージョンをダウンロードします
4. ダウンロードした **CompanyPortal-Installer.pkg** を使用してポータル サイトを新たにインストールします

このファイル **の**の存在を確認して、問題が解決されたことを検証します: `~/Library/Application\ Support/Google/Chrome/NativeMessagingHosts/com.microsoft.browsercore.json`

```console
ls ~/Library/Application\ Support/Google/Chrome/NativeMessagingHosts/com.microsoft.browsercore.json
```

または、MDM もしくはその他の自動化ツールを使用して次のスクリプトをデプロイし、JSON ファイルを正しい場所にコピーすることもできます。 このスクリプトは、Chrome SSO の問題が発生する各ユーザーに対して、ユーザーのコンテキストで実行する必要があります。

```zsh
#!/usr/bin/env zsh
# Copy over Browser Core json file to the right location
# If the folder doesn't exist, create it

# For Google Chrome (user-specific, default path)

if [ ! -d ~/Library/Application\ Support/Google/Chrome/NativeMessagingHosts ]; then
  mkdir ~/Library/Application\ Support/Google/Chrome/NativeMessagingHosts
fi

cp /Applications/Company\ Portal.app/Contents/Resources/com.microsoft.browsercore.json ~/Library/Application\ Support/Google/Chrome/NativeMessagingHosts/

# For Edge (user-specific, default path, not channel specific)
# See: https://learn.microsoft.com/microsoft-edge/extensions-chromium/developer-guide/native-messaging?tabs=v3%2Cmacos

if [ ! -d ~/Library/Application\ Support/Microsoft\ Edge/NativeMessagingHosts ]; then
  mkdir ~/Library/Application\ Support/Microsoft\ Edge/NativeMessagingHosts
fi

cp /Applications/Company\ Portal.app/Contents/Resources/com.microsoft.browsercore.json ~/Library/Application\ Support/Microsoft\ Edge/NativeMessagingHosts/
```

Important

**注: この問題が発生するのは、特定の状況下でのポータル サイトのインストール方法または更新方法にバグがあることが原因です。 この問題は、ポータル サイトの今後の更新で解決される予定です。**

#### サインインできない - シングル サインオン アプリケーションが見つかりません

[Image: シングル サインオン用のアカウントの登録中に拡張機能に問題があるためサインインできないというダイアログのスクリーンショット。]

**問題の概要: Setup Assistant 中は ポータル サイト/SSO 拡張機能を利用できない**

構成プロファイルと (SSO 拡張機能を提供する) ポータル サイト アプリが遅れた場合、PSSO プロファイルが既に配信されている場合でも、Setup Assistant に SSO アプリの不足メッセージが表示されることがあります。

- 登録アクションは単一のトランザクションとしてではなく別の手順で実行されるため、ポータル サイトのダウンロードまたはインストール中は、管理プロファイル/構成が存在する可能性があります。
- ポータル サイト が利用可能になったら、[再試行] ボタンを使って再試行すれば成功するはずです。
- 問題が解消されず、セットアップ アシスタントの実行中にプロファイルが更新された場合、変更はデバイスをワイプして初期状態からやり直すまで反映されません。

#### macOS 上の EnableRegistrationDuringSetup に対するプラットフォーム SSO を使用してデバイスを再登録する

構成が間違っている場合は、macOS デバイスを再登録する必要があります。

セットアップ中に PSSO を無効にするには、

1. Intune で、`EnableRegistrationDuringSetup` が有効になっている SSO 拡張機能プロファイルからデバイスの割り当てを解除します。
2. `EnableRegistrationDuringSetup`を無効 (false) に設定する新しい SSO 拡張機能プロファイルにデバイスを割り当てます。
3. デバイスをワイプして、登録プロセスをもう一度再起動します。

Note

登録プロセスを再起動し、更新された登録プロファイルを適用するには、デバイスをワイプする必要があります。

#### macOS セットアップ アシスタントで Sysdiagnose ログと CP ログをキャプチャするには

1. macOS セットアップ アシスタントで、Ctrl + Option + Command + Shift + Period (.) キーを押して sysdiagnose キャプチャを開始します。
    - キャプチャがトリガーされている間、画面が短時間フリーズしているように見える場合があります。
    - ログはバックグラウンドで収集され、.tar.gz ファイルにパッケージ化されます。
2. システムにアクセスしたら、
    - /private/var/tmp/（場合によっては /var/tmp/）から sys診断ファイルを取得します。 ファイル名の形式: sysdiagnose\_YYYY。MM.DD\_\*.tar.gz
    - ポータル サイト アプリを開き、[ヘルプ] -&gt; 診断レポートを送信し、インシデント ID を共有する
3. ユーザーがセットアップ アシスタントを完了できない場合 (PSSO 構成エラーなど)、sysdiagnose 出力と CP ログを収集して共有できます。
    - セットアップ アシスタントで sysdiagnose をトリガーすると、sysdiagnose .tar.gz ファイルを示す Finder のようなウィンドウが開きます。 AirDrop を使用してファイルを共有します。
    - 同じ Finder ウィンドウで、[アプリケーション] タブに移動し、ポータル サイト アプリを開きます。 [ヘルプ] -&gt;[診断レポートの送信] に移動し、インシデント ID を共有します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-primary-refresh-token"} -->
## Windows デバイスでのプライマリ更新トークンの問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-primary-refresh-token
- Service: entra-id / devices
- Article date: 2026-02-23
- Summary: Microsoft Entra に参加している Windows デバイスで Microsoft Entra 資格情報を使用して認証中に発生するプライマリ更新トークンの問題をトラブルシューティングします。

この記事では、Microsoft Entra 資格情報を使用して Microsoft Entra 参加済み Windows デバイスで認証するときの[プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) に関連する問題をトラブルシューティングする方法について説明します。

Microsoft Entra ID またはハイブリッド Microsoft Entra ID に参加しているデバイスでは、認証の主なコンポーネントは PRT です。 このトークンは、Microsoft Entra 参加済みデバイスで Microsoft Entra 資格情報を使用して Windows 10 に初めてサインインすることによって取得します。 PRT はそのデバイスにキャッシュされます。 以降のサインインでは、デスクトップを使用できるようにするためにキャッシュされたトークンが使用されます。

デバイスのロックとロック解除、または Windows への再サインインのプロセスの一環として、PRT を更新するために 4 時間ごとに 1 回、バックグラウンド ネットワーク認証が試行されます。 トークンの更新を妨げる問題が発生した場合、PRT は最終的に期限切れになります。 有効期限は、Microsoft Entra リソースへのシングル サインオン (SSO) に影響します。 また、サインイン プロンプトが表示されるようになります。

PRT の問題があると思われる場合は、まず Microsoft Entra ログを収集し、トラブルシューティング チェックリストに記載されている手順に従うことをお勧めします。 これを、できれば再現セッション内で、まず Microsoft Entra クライアントの問題に対して行います。 サポート要求を提出する前に、このプロセスを完了してください。

### トラブルシューティングのチェックリスト

#### 手順 1: プライマリ更新トークンの状態を取得する

1. PRT の問題が発生しているユーザー アカウントで Windows にサインインします。
2. **[スタート]** を選択し、**[コマンド プロンプト]** を検索して選択します。
3. デバイス登録コマンド ([dsregcmd](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-dsregcmd)) を実行するには、`dsregcmd /status` を入力します。
4. デバイス登録コマンドの出力の [SSO state](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-dsregcmd#sso-state) セクションを見つけます。 次のテキストは、このセクションの例を示しています。

    ```output
    +----------------------------------------------------------------------+
    | SSO State                                                            |
    +----------------------------------------------------------------------+
    
                    AzureAdPrt : YES
          AzureAdPrtUpdateTime : 2020-07-12 22:57:53.000 UTC
          AzureAdPrtExpiryTime : 2020-07-26 22:58:35.000 UTC
           AzureAdPrtAuthority : https://login.microsoftonline.com/00001111-aaaa-2222-bbbb-3333cccc4444
                 EnterprisePrt : YES
       EnterprisePrtUpdateTime : 2020-07-12 22:57:54.000 UTC
       EnterprisePrtExpiryTime : 2020-07-26 22:57:54.000 UTC
        EnterprisePrtAuthority : https://msft.sts.microsoft.com:443/adfs
    
    +----------------------------------------------------------------------+
    ```
5. `AzureAdPrt` フィールドの値を確認します。 `NO` に設定されている場合は、Microsoft Entra ID から PRT 状態を取得しようとしたときにエラーが発生しました。
6. `AzureAdPrtUpdateTime` フィールドの値を確認します。 `AzureAdPrtUpdateTime` フィールドの値が 4 時間を超えている場合は、PRT の更新を妨げる問題が発生したと考えられます。 デバイスをロックしてからロックを解除して PRT の更新を強制的に実行し、時間が更新されるかどうかを確認します。

#### 手順 2: エラーコードを取得する

次の手順では、PRT エラーの原因となるエラー コードを取得します。 PRT エラー コードを取得する最も簡単な方法は、デバイス登録コマンドの出力を調べることです。 ただし、この方法には、Windows 10 の 2021 年 5 月の更新プログラム (バージョン 21H1) 以降のバージョンが必要です。 もう 1 つの方法は、Microsoft Entra 分析ログと操作ログでエラー コードを見つけることです。

##### 方法 1: デバイス登録コマンドの出力を調べる

Note

この方法は、Windows 10 の 2021 年 5 月の更新プログラム (バージョン 21H1) 以降のバージョンの Windows を使用している場合にのみ使用できます。

PRT エラー コードを取得するには、`dsregcmd` コマンドを実行し、`SSO State` セクションを見つけます。 `AzureAdPrt` フィールドで、`Attempt Status` フィールドにエラー コードが含まれています。 次の例では、エラー コードは `0xc000006d` です。

```output
                AzureAdPrt : NO
       AzureAdPrtAuthority : https://login.microsoftonline.com/aaaa0000-bb11-2222-33cc-444444dddddd
     AcquirePrtDiagnostics : PRESENT
      Previous Prt Attempt : 2020-09-18 20:20:09.760 UTC
            Attempt Status : 0xc000006d
             User Identity : user@contoso.com
           Credential Type : Password
            Correlation ID : aaaa0000-bb11-2222-33cc-444444dddddd
              Endpoint URI : https://login.microsoftonline.com/aaaa0000-bb11-2222-33cc-444444dddddd/oauth2/token
               HTTP Method : POST
                HTTP Error : 0x0
               HTTP status : 400
         Server Error Code : invalid_grant
  Server Error Description : AADSTS50126: Error validating credentials due to invalid username or password.
```

##### 方法 2: イベント ビューアーを使用して AAD の分析ログと運用ログを調べる

1. **[スタート]** を選択し、**[イベント ビューアー]** を検索して選択します。
2. コンソール ツリーが **[イベント ビューアー]** ウィンドウに表示されない場合は、**[コンソール ツリーの表示/非表示]** アイコンを選択してコンソール ツリーを表示します。
3. コンソール ツリーで、**[イベント ビューアー (ローカル)]** を選択します。 子ノードがこの項目の下に表示されない場合は、選択内容をダブルクリックして表示します。
4. **[表示]** メニュー項目を選択します。 **[分析ログとデバッグ ログの表示]** の横にチェックマークが表示されていない場合は、そのメニュー項目を選択してその機能を有効にします。
5. コンソール ツリーで、**[アプリケーションとサービス ログ]**&gt;**[Microsoft]**&gt;**[Windows]**&gt;**[AAD]** の順に展開します。 **[操作]** と **[分析]** の子ノードが表示されます。

    Note

    Microsoft Entra クラウド認証プロバイダー (CloudAP) プラグインでは、**エラー** イベントは **操作**イベント ログに書き込まれ、情報イベントは**分析**イベント ログに書き込まれます。 PRT の問題をトラブルシューティングするには、**操作**イベント ログと**分析**イベント ログの両方を調べる必要があります。
6. コンソール ツリーで、**[分析]** ノードを選択して、AAD 関連の分析イベントを表示します。
7. 分析イベントの一覧で、イベント ID 1006 と 1007 を検索します。 イベント ID 1006 は PRT 取得フローの開始を表し、イベント ID 1007 は PRT 取得フローの終了を表します。 イベント ID 1006 とイベント ID 1007 の間で発生した **AAD** ログ (**分析**と**操作**の両方) 内のすべてのイベントは、PRT 取得フローの一部としてログに記録されます。 次の表にイベント一覧の例を示します。

    | レベル | 日時 | Source | イベント ID | タスク カテゴリ |
    | --- | --- | --- | --- | --- |
    | **情報** | **2020 年 6 月 24 日午前 3:35:35** | **AAD** | **1006** | **AadCloudAPPlugin 操作** |
    | 説明 | 2020 年 6 月 24 日午前 3:35:35 | AAD | 1018 | AadCloudAPPlugin 操作 |
    | 説明 | 2020 年 6 月 24 日午前 3:35:35 | AAD | 1144 | AadCloudAPPlugin 操作 |
    | 説明 | 2020 年 6 月 24 日午前 3:35:35 | AAD | 1022 | AadCloudAPPlugin 操作 |
    | Error | 2020 年 6 月 24 日午前 3:35:35 | AAD | 1084 | AadCloudAPPlugin 操作 |
    | Error | 2020 年 6 月 24 日午前 3:35:35 | AAD | 1086 | AadCloudAPPlugin 操作 |
    | Error | 2020 年 6 月 24 日午前 3:35:35 | AAD | 1160 | AadCloudAPPlugin 操作 |
    | **情報** | **2020 年 6 月 24 日午前 3:35:35** | **AAD** | **1007** | **AadCloudAPPlugin 操作** |
    | 説明 | 2020 年 6 月 24 日午前 3:35:35 | AAD | 1157 | AadCloudAPPlugin 操作 |
    | 説明 | 2020 年 6 月 24 日午前 3:35:35 | AAD | 1158 | AadCloudAPPlugin 操作 |
8. イベント ID 1007 を含む行をダブルクリックします。 このイベントの **[イベントのプロパティ]** ダイアログ ボックスが表示されます。
9. **[全般]** タブの説明ボックスで、エラー コードをコピーします。 エラー コードは、`0x` で始まり、8 桁の 16 進数が続く 10 桁の文字列です。

#### 手順 3: 特定のエラー コードのトラブルシューティング手順を取得する

##### 状態コード ("STATUS\_" プレフィックス、"0xc000" で始まるコード)

 STATUS\_LOGON\_FAILURE (-1073741715 / 0xc000006d)、 STATUS\_WRONG\_PASSWORD (-1073741718 / 0xc000006a)

###### 原因

- デバイスが Microsoft Entra 認証サービスに接続できません。
- デバイスが次のいずれかのソースから `400 Bad Request` HTTP エラー応答を受信しました。

    - Microsoft Entra 認証サービス
    - [WS-Trust プロトコル](http://docs.oasis-open.org/ws-sx/ws-trust/v1.4/ws-trust.html)のエンドポイント (フェデレーション認証に必要)

###### ソリューション

- オンプレミス環境で送信プロキシが必要な場合は、デバイスのコンピューター アカウントが送信プロキシを検出し、自動的に認証できることを確認してください。
- サーバー エラー コードとエラーの説明を取得し、「共通サーバー エラー コード ("AADSTS" プレフィックス)」セクションに移動して、そのサーバー エラー コードの原因とソリューションの詳細を確認します。

    Microsoft Entra 操作ログで、イベント ID 1081 には、Microsoft Entra 認証サービスでエラーが発生した場合のサーバー エラー コードとエラーの説明が含まれています。 WS-Trust エンドポイントでエラーが発生した場合、サーバー エラー コードとエラーの説明はイベント ID 1088 にあります。 Microsoft Entra 分析ログで、(操作イベント ID 1081 および 1088 よりも前にある) イベント ID 1022 の最初のインスタンスに、アクセスしている URL が含まれています。

    Microsoft Entra の操作ログと分析ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。

STATUS\_REQUEST\_NOT\_ACCEPTED (-1073741616 / 0xc00000d0)

###### 原因

デバイスが次のいずれかのソースから `400 Bad Request` HTTP エラー応答を受信しました。

- Microsoft Entra 認証サービス
- [WS-Trust プロトコル](http://docs.oasis-open.org/ws-sx/ws-trust/v1.4/ws-trust.html)のエンドポイント (フェデレーション認証に必要)

###### ソリューション

サーバー エラー コードとエラーの説明を取得し、「共通サーバー エラー コード ("AADSTS" プレフィックス)」セクションに移動して、そのサーバー エラー コードの原因とソリューションの詳細を確認します。

Microsoft Entra 操作ログで、イベント ID 1081 には、Microsoft Entra 認証サービスでエラーが発生した場合のサーバー エラー コードとエラーの説明が含まれています。 WS-Trust エンドポイントでエラーが発生した場合、サーバー エラー コードとエラーの説明はイベント ID 1088 にあります。 Microsoft Entra 分析ログで、(操作イベント ID 1081 および 1088 よりも前にある) イベント ID 1022 の最初のインスタンスに、アクセスしている URL が含まれています。

Microsoft Entra の操作ログと分析ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。

 STATUS\_NETWORK\_UNREACHABLE (-1073741252 / 0xc000023c)、 STATUS\_BAD\_NETWORK\_PATH (-1073741634 / 0xc00000be)、 STATUS\_UNEXPECTED\_NETWORK\_ERROR (-1073741628 / 0xc00000c4)

###### 原因

- デバイスが次のいずれかのソースから `4xx` HTTP エラー応答を受信しました。

    - Microsoft Entra 認証サービス
    - [WS-Trust プロトコル](http://docs.oasis-open.org/ws-sx/ws-trust/v1.4/ws-trust.html)のエンドポイント (フェデレーション認証に必要)
- 必要なエンドポイントへのネットワーク接続の問題が存在します。

###### ソリューション

- サーバー エラー コードとエラーの説明を取得し、「共通サーバー エラー コード ("AADSTS" プレフィックス)」セクションに移動して、そのサーバー エラー コードの原因とソリューションの詳細を確認します。

    Microsoft Entra 操作ログで、イベント ID 1081 には、Microsoft Entra 認証サービスでエラーが発生した場合のサーバー エラー コードとエラーの説明が含まれています。 WS-Trust エンドポイントでエラーが発生した場合、サーバー エラー コードとエラーの説明はイベント ID 1088 にあります。
- ネットワーク接続の問題の場合は、アクセスしている URL とネットワーク スタックからのサブエラー コードを取得します。 Microsoft Entra 分析ログのイベント ID 1022 には、アクセスしている URL が含まれています。 Microsoft Entra 操作ログのイベント ID 1084 には、ネットワーク スタックからのサブエラー コードが含まれています。

Microsoft Entra の操作ログと分析ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。

STATUS\_NO\_SUCH\_LOGON\_SESSION (-1073741729 / 0xc000005f)

###### 原因

Microsoft Entra 認証サービスでユーザーのドメインが見つからなかったため、ユーザー領域の検出に失敗しました。

###### ソリューション

- ユーザーのユーザー プリンシパル名 (UPN) のドメインを Microsoft Entra ID でカスタム ドメインとして追加します。 提供されている UPN を見つけるには、Microsoft Entra ID 分析ログでイベント ID 1144 を探します。

    Microsoft Entra の操作ログと分析ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。
- オンプレミスのドメイン名をルーティングできない場合 (たとえば、UPN が `jdoe@contoso.local` などの場合) は、[代替ログイン ID](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id) (AltID) を構成します。 (前提条件を見るには、「[Microsoft Entra ハイブリッド参加の実装を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)」を参照してください。)

##### 共通 CloudAP プラグイン エラー コード ("AAD\_CLOUDAP\_E\_" プレフィックス、"0xc004" で始まるコード)

AAD\_CLOUDAP\_E\_OAUTH\_USERNAME\_IS\_MALFORMED (-1073445812 / 0xc004844c)

###### 原因

ユーザーの UPN の形式が、予期された形式ではありません。 UPN 値は、次の表に示すように、デバイスの種類によって異なります。

| デバイスの参加の種類 | UPN 値 |
| --- | --- |
| Microsoft Entra 参加済みデバイスのみ | ユーザーがサインインするときに入力されるテキスト |
| Microsoft Entra ハイブリッド参加済みデバイス | サインイン プロセス中にドメイン コントローラーから返される UPN |

###### ソリューション

- インターネット標準 RFC 822 に基づいて、ユーザーの UPN をインターネット スタイルのサインイン名に設定します。 現在の UPN を見つけるには、Microsoft Entra ID 分析ログでイベント ID 1144 を探します。

    Microsoft Entra の操作ログと分析ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。
- Microsoft Entra ハイブリッド参加済みデバイスの場合は、UPN を正しい形式で返すようにドメイン コントローラーが構成されていることを確認します。 構成された UPN をドメイン コントローラーに表示するには、次の [whoami](https://learn.microsoft.com/ja-jp/windows-server/administration/windows-commands/whoami) コマンドを実行します。

    ```cmd
    whoami /upn
    ```

    Active Directory が正しい UPN で構成されている場合は、ローカル セキュリティ機関サブシステム サービス (LSASS または lsass.exe) の*タイム トラベル トレースを収集*します。
- オンプレミスのドメイン名をルーティングできない場合 (たとえば、UPN が `jdoe@contoso.local` などの場合) は、[代替ログイン ID](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id) (AltID) を構成します。 (前提条件を見るには、「[Microsoft Entra ハイブリッド参加の実装を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)」を参照してください。)

AAD\_CLOUDAP\_E\_OAUTH\_USER\_SID\_IS\_EMPTY (-1073445822 / 0xc0048442)

###### 原因

Microsoft Entra 認証サービスから返された ID トークンにユーザーセキュリティ ID (SID) がありません。

###### ソリューション

ネットワーク プロキシがサーバーの応答を妨害したり変更したりしていないことを確認します。

AAD\_CLOUDAP\_E\_WSTRUST\_SAML\_TOKENS\_ARE\_EMPTY (-1073445695 / 0xc00484c1 / 0x800484c1)

###### 原因

[WS-Trust プロトコル](http://docs.oasis-open.org/ws-sx/ws-trust/v1.4/ws-trust.html) エンドポイント (フェデレーション認証に必要) からエラーを受信しました。

###### ソリューション

- ネットワーク プロキシがサーバーの応答を妨害したり変更したりしていないことを確認します。
- Microsoft Entra 操作ログのイベント ID 1088 からサーバー エラー コードとエラーの説明を取得します。 次に、「共通サーバー エラー コード ("AADSTS" プレフィックス)」セクションに移動して、そのサーバー エラー コードの原因とソリューションの詳細を確認します。

    Microsoft Entra の操作ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。

AAD\_CLOUDAP\_E\_HTTP\_PASSWORD\_URI\_IS\_EMPTY (-1073445749 / 0xc004848b)

###### 原因

Metadata Exchange (MEX) エンドポイントが正しく構成されていません。 MEX 応答に、パスワードの URL が含まれていません。

###### ソリューション

- ネットワーク プロキシがサーバーの応答を妨害したり変更したりしていないことを確認します。
- 有効な URL が応答で返されるように、MEX の構成を修正してください。

AAD\_CLOUDAP\_E\_HTTP\_CERTIFICATE\_URI\_IS\_EMPTY (-1073445748 / 0xc004848c)

###### 原因

Metadata Exchange (MEX) エンドポイントが正しく構成されていません。 MEX 応答には、証明書エンドポイント URL が含まれていません。

###### ソリューション

- ネットワーク プロキシがサーバーの応答を妨害したり変更したりしていないことを確認します。
- ID プロバイダーの MEX 構成を修正して、有効な証明書 URL を応答として返します。

##### 共通 XML エラー コード ("0xc00c" で始まるコード)

WC\_E\_DTDPROHIBITED (-1072894385 / 0xc00cee4f)

###### 原因

[WS-Trust プロトコル](http://docs.oasis-open.org/ws-sx/ws-trust/v1.4/ws-trust.html) エンドポイント (フェデレーション認証に必要) からの XML 応答に、ドキュメント型定義 (DTD) が含まれていました。 XML 応答では DTD が想定されておらず、DTD が含まれている場合、応答の解析は失敗します。

###### ソリューション

- XML 応答で DTD が送信されないように ID プロバイダーの構成を修正します。
- Microsoft Entra 分析ログのイベント ID 1022 からアクセスしている URL を取得します。

    Microsoft Entra の操作ログと分析ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。

##### 共通サーバー エラー コード ("AADSTS" プレフィックス)

サーバー エラー コードの完全なリストと説明については、「[Microsoft Entra 認証と承認のエラー コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)」を参照してください。

AADSTS50155: デバイス認証に失敗しました

###### 原因

- Microsoft Entra で、PRT を発行するデバイスを認証できません。
- デバイスが削除された、または無効になっている可能性があります。 詳細について、「[Windows 10/11 デバイスに "Your organization has deleted the device (組織がデバイスを削除しました)" または "Your organization has disabled the device (組織がデバイスを無効にしました)" というエラー メッセージが表示されるのはなぜですか?](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq#why-do-my-users-see-an-error-message-saying--your-organization-has-deleted-the-device--or--your-organization-has-disabled-the-device--on-their-windows-10-11-devices)」を参照してください。

###### ソリューション

デバイスの参加の種類に基づいてデバイスを再登録します。 手順については、「[デバイスを無効化または削除しましたが、デバイスのローカル状態にはまだ登録済みと表示されます。どうすればよいですか。](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq#i-disabled-or-deleted-my-device--but-the-local-state-on-the-device-says-it-s-registered--what-should-i-do)」を参照してください。

AADSTS50034: ユーザー アカウント &lt;Account&gt; が &lt;tenant-id&gt; ディレクトリに存在しません

###### 原因

Microsoft Entra ID で、テナント内のユーザー アカウントを検出できません。

###### ソリューション

- ユーザーが正しい UPN を入力していることを確認します。
- オンプレミスのユーザー アカウントが Microsoft Entra と同期されていることを確認します。
- Microsoft Entra 分析ログでイベント ID 1144 を探して、提供された UPN を取得します。

    Microsoft Entra の操作ログと分析ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。

AADSTS50126: 無効なユーザー名またはパスワードにより、資格情報の検証でエラーが発生しました

###### 原因

- ユーザーがサインイン UI で正しくないユーザー名またはパスワードを入力しました。
- 次のシナリオのため、パスワードが Microsoft Entra に同期されていません。

    - テナントで[パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)が有効になっています。
    - このデバイスは、Microsoft Entra ハイブリッド参加済みデバイスです。
    - ユーザーが最近パスワードを変更しました。

###### ソリューション

新しい証明書を含む新しい PRT を取得するには、Microsoft Entra の同期が完了するまで待ちます。

##### 共通ネットワーク エラー コード ("ERROR\_WINHTTP\_" プレフィックス)

ネットワーク エラー コードの完全な一覧と説明については、「[エラー メッセージ (Winhttp.h)](https://learn.microsoft.com/ja-jp/windows/win32/winhttp/error-messages)」を参照してください。

 ERROR\_WINHTTP\_TIMEOUT (12002)、 ERROR\_WINHTTP\_NAME\_NOT\_RESOLVED (12007)、 ERROR\_WINHTTP\_CANNOT\_CONNECT (12029)、 ERROR\_WINHTTP\_CONNECTION\_ERROR (12030)

###### 原因

ネットワーク全般に関連した一般的な問題です。

###### ソリューション

- アクセスしている URL を取得します。 URL は、Microsoft Entra 操作ログのイベント ID 1084 または Microsoft Entra 分析ログのイベント ID 1022 にあります。

    Microsoft Entra の操作ログと分析ログでイベント ID を表示するには、「方法 2: イベント ビューアーを使用して Microsoft Entra の分析ログと操作ログを調べる」セクションを参照してください。
- オンプレミス環境で送信プロキシが必要な場合は、デバイスのコンピューター アカウントが送信プロキシを検出し、自動的に認証できることを確認してください。
- 次の手順に従ってネットワーク トレースを収集します。

    重要

    この手順では Fiddler を使用しないでください。

    1. 次の [netsh trace start](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/jj129382%28v=ws.11%29#start) コマンドを実行:

        ```cmd
        netsh trace start scenario=InternetClient_dbg capture=yes persistent=yes
        ```
    2. デバイスをロックします。
    3. デバイスが Microsoft Entra ハイブリッド参加済みデバイスの場合は、PRT 取得タスクが完了するまで少なくとも 60 秒待ちます。
    4. デバイスのロックを解除します。
    5. 次の [netsh trace stop](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/jj129382%28v=ws.11%29#stop) コマンドを実行:

        ```cmd
        netsh trace stop
        ```

#### 手順 4: ログとトレースを収集する

##### 通常のログ

1. [Auth スクリプト アーカイブ](https://aka.ms/authscripts)をダウンロードし、スクリプトをローカル ディレクトリに抽出します。
2. 管理 PowerShell セッションを開き、現在のディレクトリを Auth スクリプトを保存したディレクトリに変更します。
3. エラー トレース セッションを開始するには、次のコマンドを入力します。

    ```powershell
    .\Start-auth.ps1 -vAuth -acceptEULA
    ```
4. Windows ユーザー アカウントを切り替えて、問題のあるユーザーのセッションに移動します。
5. デバイスをロックします。
6. デバイスが Microsoft Entra ハイブリッド参加済みデバイスの場合は、PRT 取得タスクが完了するまで少なくとも 60 秒待ちます。
7. デバイスのロックを解除します。
8. Windows ユーザー アカウントを切り替えてトレース セッションを実行している管理セッションに戻ります。
9. 問題を再現したら、次のコマンドを実行してトレース セッションを終了します。

    ```powershell
    .\stop-auth.ps1
    ```
10. すべてのトレースが完全に停止するまで待ちます。

##### タイム トラベル トレース

次の手順では、[タイム トラベル デバッグ](https://learn.microsoft.com/ja-jp/windows-hardware/drivers/debugger/time-travel-debugging-overview) (TTD) 機能を使用してトレースをキャプチャする方法について説明します。

警告

タイム トラベル トレースには個人データが含まれます。 さらに、ローカル セキュリティ機関サブシステム サービス (LSASS または *lsass.exe*) トレースには、非常に機密性の高い情報が含まれています。 これらのトレースを処理するときは、この種類の情報のストレージと共有に関するベスト プラクティスを使用していることを確認します。

1. **[スタート]** を選択し、「*cmd*」と入力し、検索結果で **[コマンド プロンプト]** を見つけて右クリックし、**[管理者として実行]** を選択します。
2. コマンド プロンプトで、一時ディレクトリを作成します。

    ```cmd
    mkdir c:\temp
    ```
3. 次の [tasklist](https://learn.microsoft.com/ja-jp/windows-server/administration/windows-commands/tasklist) コマンドを実行します。

    ```cmd
    tasklist /m lsasrv.dll
    ```
4. `tasklist` コマンドの出力で、`PID` のプロセス識別子 () を見つけます。
5. *lsass.exe* プロセスのトレース セッションを開始するには、次のタイム トラベル デバッグ コマンド (*[TTD.exe](https://learn.microsoft.com/ja-jp/windows-hardware/drivers/debugger/time-travel-debugging-ttd-exe-command-line-util)*) を実行します。

    ```cmd
    TTD.exe -attach <lsass-pid> -out c:\temp
    ```
6. ドメイン アカウントでサインインしているデバイスをロックします。
7. デバイスのロックを解除します。
8. タイム トラベル トレース セッションを終了するには、次の TTD コマンドを実行します。

    ```cmd
    TTD.exe -stop all
    ```
9. 最新の *lsass##.run* ファイルを取得します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/whats-new-linux"} -->
## Linux 用 Microsoft シングル サインオンの新機能 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/whats-new-linux
- Service: entra-id / devices
- Article date: 2026-04-02
- Summary: Linux 用 Microsoft シングル サインオンの新機能リリースについて説明します

Microsoft は、セキュリティ、使いやすさ、標準のコンプライアンスを向上させるために、Microsoft ID プラットフォームの機能を定期的に追加および変更します。

特に明記されていない限り、ここで説明する変更は、変更後に記載された有効日より後に登録されたアプリケーションにのみ適用されます。

この記事を定期的にチェックして、以下について学んでください。

- 既知の問題と修正
- プロトコルの変更
- 非推奨の機能

この記事では、Linux 用 Microsoft シングル サインオンの最新の更新プログラムについて説明します。

### Microsoft Identity Broker バージョンのライフサイクルとサポート マトリックス

Microsoft では、次のパッケージ リポジトリを使用して、Microsoft Identity Broker と Microsoft Identity Diagnostics for Linux を配布します。 パッケージは `.deb` または `.rpm` 形式で利用できますが、サポートされるのは Ubuntu 長期サポート (LTS) と Red Hat Enterprise Linux (RHEL) のみです。

| チャネル | 主な目的 | 最新バージョン | サポートされています | 情報源 |
| --- | --- | --- | --- | --- |
| 安定 | 運用ワークロード | 3.0.x | はい | [Ubuntu 24.04 - Noble](https://packages.microsoft.com/ubuntu/24.04/prod/dists/noble/)[Ubuntu 22.04 - Jammy](https://packages.microsoft.com/ubuntu/22.04/prod/dists/jammy/)[RHEL8](https://packages.microsoft.com/rhel/8.0/prod/)[RHEL9](https://packages.microsoft.com/rhel/9.0/prod/) |
| insiders-fast | プレリリース パッケージのテスト | 3.0.x | いいえ | [Ubuntu 24.04 - Noble](https://packages.microsoft.com/ubuntu/24.04/prod/dists/insiders-fast/)[Ubuntu 22.04 - Jammy](https://packages.microsoft.com/ubuntu/22.04/prod/dists/insiders-fast/)[RHEL8](https://packages.microsoft.com/rhel/8.0/insiders-fast/)[RHEL9](https://packages.microsoft.com/rhel/9.0/insiders-fast/)[RHEL10](https://packages.microsoft.com/rhel/10/insiders-fast/) |

`insiders-fast`の`packages.microsoft.com` チャネルを使用すると、プレリリース パッケージをテストできます。 運用環境のワークロードには使用しないでください。 壊滅的変更や不完全な機能が含まれている可能性があります。

#### バージョン 2.0.2 以降の重要な注意事項

Important

バージョン 2.0.2 以降では、Microsoft Linux のシングル サインオンでは、デバイスの信頼に`Microsoft Entra registration`ではなく、`Microsoft Entra join`が使用されます。

- 以前のバージョンからアップグレードされた既存のデバイスは、再参加して再登録する必要があります。
- バージョン 2.0.2 以降を展開する前に、管理者は、Microsoft Entra 管理センター &gt; Devices &gt; Device 設定の`Users may join devices to Microsoft Entra`設定でユーザーが許可されていることを確認する必要があります。 `Users may register their devices with Microsoft Entra`設定では十分ではなくなりました。
- 詳細については、「Microsoft Entra IDでの[デバイス ID の管理」を](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities)参照してください。

### パッケージ リポジトリを追加する手順

Linux ディストリビューションに適切なパッケージ リポジトリを追加するには、次の手順に従います。

## [Ubuntu Production](#tab/debian-install-prod)
1. `curl`と`gpg`をインストールします。

    ```bash
    sudo apt install curl gpg
    ```
2. Microsoft パッケージ署名キーをインストールします。

    ```bash
    curl https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft.gpg
    sudo install -o root -g root -m 644 microsoft.gpg /usr/share/keyrings
    rm microsoft.gpg
    ```
3. Microsoft パッケージ リポジトリを追加し、パッケージ メタデータを更新します。

    ```bash
    sudo sh -c 'echo "deb [arch=amd64 signed-by=/usr/share/keyrings/microsoft.gpg] https://packages.microsoft.com/ubuntu/$(lsb_release -rs)/prod $(lsb_release -cs) main" >> /etc/apt/sources.list.d/microsoft-ubuntu-$(lsb_release -cs)-prod.list'
    sudo apt update
    ```

## [Ubuntu insiders-fast](#tab/debian-install-insiders-fast)
1. `curl`と`gpg`をインストールします。

    ```bash
    sudo apt install curl gpg
    ```
2. insiders-fast リポジトリ署名キーをインストールします。

    ```bash
    curl -s https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft-insiders-fast.gpg
    sudo install -o root -g root -m 644 microsoft-insiders-fast.gpg /usr/share/keyrings
    rm microsoft-insiders-fast.gpg
    ```
3. Microsoft パッケージ リポジトリを追加し、パッケージ メタデータを更新します。

    ```bash
    sudo sh -c 'echo "deb [arch=amd64 signed-by=/usr/share/keyrings/microsoft-insiders-fast.gpg] https://packages.microsoft.com/ubuntu/$(lsb_release -rs)/prod insiders-fast main" >> /etc/apt/sources.list.d/microsoft-ubuntu-$(lsb_release -cs)-insiders-fast.list'
    sudo apt update
    ```

---

## [RHEL 8/9 Prod](#tab/redhat89-install-prod)
1. Microsoft パッケージ署名キーをインストールします。

    ```bash
    # Legacy key (needed for RHEL 8 and RHEL 9 packages and Microsoft Edge)
    sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
    ```
2. Microsoft パッケージ リポジトリを追加します。

    ```bash
    sudo dnf install -y dnf-plugins-core
    sudo dnf config-manager --add-repo https://packages.microsoft.com/yumrepos/microsoft-rhel$(rpm -E %rhel).0-prod
    ```

## [RHEL 10 Prod](#tab/redhat10-install-prod)
1. Microsoft パッケージ署名キーをインストールします。 RHEL 10 パッケージは、RHEL 8 および RHEL 9 で使用される `microsoft.asc` キーとは異なる、新しい Microsoft GPG キー (RSA-4096) で署名されます。

    ```bash
    # Legacy key (needed for Microsoft Edge)
    sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
    
    # New key for RHEL 10 packages
    sudo rpm --import https://packages.microsoft.com/rhel/10/prod/repodata/repomd.xml.key
    ```
2. 次の内容を含む新しいリポジトリ ファイルを `/etc/yum.repos.d/` の下に作成して、リポジトリを追加します。

    ```bash
    sudo tee /etc/yum.repos.d/microsoft-prod.repo > /dev/null <<EOF
    [microsoft-prod]
    name=Microsoft prod - RHEL 10
    baseurl=https://packages.microsoft.com/rhel/10/prod
    enabled=1
    gpgcheck=1
    gpgkey=https://packages.microsoft.com/rhel/10/prod/repodata/repomd.xml.key
    EOF
    ```

## [RHEL 8/9 インサイダーズ・ファスト](#tab/redhat-install-insiders-fast)
1. Microsoft パッケージ署名キーをインストールします。

    ```bash
    # Legacy key (needed for Microsoft Edge)
    sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
    
    # Repository key for insiders-fast packages
    sudo rpm --import https://packages.microsoft.com/rhel/10/insiders-fast/repodata/repomd.xml.key
    ```
2. Microsoft パッケージ リポジトリを追加します。

    ```bash
    sudo dnf install -y dnf-plugins-core
    
    # for rhel 8 and 9
    sudo dnf config-manager --add-repo https://packages.microsoft.com/yumrepos/microsoft-rhel$(rpm -E %rhel).0-insiders-fast-prod
    
    # for rhel10:
    sudo dnf config-manager --add-repo https://packages.microsoft.com/yumrepos/microsoft-rhel10-insiders-fast-prod
    ```

## [RHEL 10 インサイダーズ・ファスト](#tab/redhat10-install-insiders-fast)
1. Microsoft パッケージ署名キーをインストールします。 RHEL 10 パッケージは、RHEL 8 および RHEL 9 で使用される `microsoft.asc` キーとは異なる、新しい Microsoft GPG キー (RSA-4096) で署名されます。

    ```bash
    # Legacy key (needed for Edge)
    sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
    
    # Key for RHEL 10 packages
    sudo rpm --import https://packages.microsoft.com/rhel/10/insiders-fast/repodata/repomd.xml.key
    ```
2. 次の内容を含む新しいリポジトリ ファイルを `/etc/yum.repos.d/` の下に作成して、リポジトリを追加します。

    ```bash
    sudo tee /etc/yum.repos.d/microsoft-insiders-fast.repo > /dev/null <<EOF
    [microsoft-insiders-fast]
    name=Microsoft insiders-fast - RHEL 10
    baseurl=https://packages.microsoft.com/rhel/10/insiders-fast
    enabled=1
    gpgcheck=1
    gpgkey=https://packages.microsoft.com/rhel/10/insiders-fast/repodata/repomd.xml.key
    EOF
    ```

---

### Changes

#### 3.0.2 - 2026 年 4 月 27 日

Ubuntu 26.04 LTS のサポートおよび Ubuntu 22.04 LTS Microsoft Intuneで Ubuntu 26.04 LTS がサポートされるようになりました。 Ubuntu 22.04 LTS のサポートは 2026 年 8 月に終了します。 Ubuntu 22.04 に既に登録されているデバイスは登録されたままですが、サポートされている Ubuntu バージョンにアップグレードするようにユーザーに通知する必要があります。 Intune 管理センターで Ubuntu 22.04 を実行しているデバイスを識別するには、デバイス &gt; すべてのデバイスに移動し、Linux でフィルター処理し、OS バージョン列を追加します。 詳細については、「[Microsoft Intuneにデスクトップ デバイスLinux登録](https://learn.microsoft.com/ja-jp/intune/device-enrollment/guide-linux)する」を参照してください。

**修正プログラム/機能強化**

- すべてのブラウザー呼び出しが同じスレッドで実行されていることを確認する
- ブローカーの動作と問題に関するより良い分析情報を提供するためにログ記録を更新しました
- RHEL 10 でパッケージ ファイルの時間不足の問題を修正する
- PKCE のサポート

##### 資産

- Ubuntu-26.04 - [microsoft-identity-broker_3.0.2-resolute_amd64.deb](https://packages.microsoft.com/ubuntu/26.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_3.0.2-resolute_amd64.deb)
- Ubuntu-24.04 - [microsoft-identity-broker_3.0.2-noble_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_3.0.2-noble_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_3.0.2-jammy_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_3.0.2-jammy_amd64.deb)
- Red Hat Enterprise Linux 10 - [microsoft-identity-broker-3.0.2-1.el10.x86_64.rpm](https://packages.microsoft.com/rhel/10/insiders-fast/Packages/m/microsoft-identity-broker-3.0.2-1.el10.x86_64.rpm)
- Red Hat Enterprise Linux 9.0 - [microsoft-identity-broker-3.0.2-1.el9.x86_64.rpm](https://packages.microsoft.com/rhel/9.0/insiders-fast/Packages/m/microsoft-identity-broker-3.0.2-1.el9.x86_64.rpm)
- Red Hat Enterprise Linux 8.0 - [microsoft-identity-broker-3.0.2-1.el8.x86_64.rpm](https://packages.microsoft.com/rhel/8.0/insiders-fast/Packages/m/microsoft-identity-broker-3.0.2-1.el8.x86_64.rpm)

#### 3.0.1 - 2026 年 3 月 31 日 - (GA メジャー リリース)

Microsoft Identity Broker for Linux の GA リリース。以前の Java ベースのブローカーではなく、新しく書き換えられた C++ ブローカーを使用するようになりました。

- SmartCard、証明書ベースの認証 (CBA)、または FIDO2 キーと個人 ID 検証 (PIV) プロファイルを使用した Linux デバイスでのフィッシング耐性 MFA (PRMFA) のサポートについて説明します。
- ID ブローカーのバージョンを区別するために、トークン要求にヘッダーを追加しました。
- ユーザーが新しい Linux デバイスでシングル サインオンを構成すると、デバイスは Microsoft Entra 登録ではなく Microsoft Entra 参加を実行します。 結合すると、デバイス全体との信頼関係が作成され、登録によってユーザー プロファイル内でのみ信頼が作成されます。 参加の信頼は、将来 platformSSO を有効にするための前提条件の手順です。
- デバイス ブローカー サービスの名前を `microsoft-identity-devicebroker` に変更しました。
- `microsoft-identity-broker`という名前のユーザー ブローカー サービスはなくなりました。 これで、ユーザー ブローカーは D-Bus 経由で呼び出される実行可能ファイルになります。
- デバイス証明書はキーチェーンから `/etc/ssl/private`に移動されます。 このディレクトリでは、ブローカーによってテナントごとのデバイス証明書、テナントごとのセッション トランスポート キー、およびデバイスレス キーが作成されます。 アクセス トークンや更新トークンなど、他のすべてのユーザー データはキーチェーンに格納され、Microsoft Authentication Library (MSAL) 経由でアクセスされます。
- `microsoft-identity-broker-diagnostics` パッケージのサポートを追加しました。
- 一貫性を保つため、サービス コンポーネントの名前を `linux_broker` から `microsoft-identity-broker` に変更しました。
- 一貫性を保つため、サービス コンポーネントの名前を `linux_devicebroker` から `microsoft-identity-device-broker` に変更しました。
- ディストリビューション名を使用するように `x-client-os` を更新しました。
- ターゲット OS を含むようにパッケージ ファイル名を変更しました。
- Linux ブローカー パッケージに LICENSE ファイルとブローカー固有の CHANGELOG.md が含まれています。
- 埋め込み認証ウィンドウの既定値 (タイトル/サイズ) が更新され、センター動作が改善されました。
- RHEL 10 のサポートを追加しました。
- デバイス登録の管理と診断のための `dsreg` コマンドライン ツールを追加しました。
- Linux デバイス ブローカーで使用される証明書とキーの場所を更新しました。
- ブローカーによって生成されたテレメトリにブローカーのバージョンが含まれています。
- DUNA クロスプラットフォーム サポートと DUNA iOS CBA が追加されました。
- GTK4 のスマート カード ダイアログ レイアウトを修正しました。
- ブラウザーが再利用されるときに発生するコールバックの問題を修正しました。
- C++ ブローカーで TLS 1.3 での GetDeviceState のサポートを追加しました。
- `sem_timedwait`および`Msai::SecureStorageLock`の信号による`Msoa::SystemMutex`障害を処理しました。

##### 資産

- Ubuntu-24.04 - [microsoft-identity-broker_3.0.1-noble_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_3.0.1-noble_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_3.0.1-jammy_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_3.0.1-jammy_amd64.deb)
- Red Hat Enterprise Linux 10 - [microsoft-identity-broker-3.0.1-1.el10.x86_64.rpm](https://packages.microsoft.com/rhel/10/insiders-fast/Packages/m/microsoft-identity-broker-3.0.1-1.el10.x86_64.rpm)
- Red Hat Enterprise Linux 9.0 - [microsoft-identity-broker-3.0.1-1.el9.x86_64.rpm](https://packages.microsoft.com/rhel/9.0/insiders-fast/Packages/m/microsoft-identity-broker-3.0.1-1.el9.x86_64.rpm)
- Red Hat Enterprise Linux 8.0 - [microsoft-identity-broker-3.0.1-1.el8.x86_64.rpm](https://packages.microsoft.com/rhel/8.0/insiders-fast/Packages/m/microsoft-identity-broker-3.0.1-1.el8.x86_64.rpm)

#### 2.5.2 - 2026 年 2 月 11 日 - (高速 Insider チャネルでのプレビュー リリース)

- (Linux)GTK4 のスマートカード ダイアログ レイアウトを修正する
- (Linux)ブラウザーが再利用される場合の間違ったコールバックの問題を修正します。

##### 資産

- Ubuntu-24.04 - [microsoft-identity-broker_2.5.2-noble_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.5.2-noble_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_2.5.2-jammy_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.5.2-jammy_amd64.deb)
- Red Hat Enterprise Linux 10 - [microsoft-identity-broker-2.5.2-1.el10.x86_64.rpm](https://packages.microsoft.com/rhel/10/insiders-fast/Packages/m/microsoft-identity-broker-2.5.2-1.el10.x86_64.rpm)
- Red Hat Enterprise Linux 9.0 - [microsoft-identity-broker-2.5.2-1.el9.x86_64.rpm](https://packages.microsoft.com/rhel/9.0/insiders-fast/Packages/m/microsoft-identity-broker-2.5.2-1.el9.x86_64.rpm)
- Red Hat Enterprise Linux 8.0 - [microsoft-identity-broker-2.5.2-1.el8.x86_64.rpm](https://packages.microsoft.com/rhel/8.0/insiders-fast/Packages/m/microsoft-identity-broker-2.5.2-1.el8.x86_64.rpm)

#### 2.5.1 - 2026 年 1 月 29 日 - (高速 Insider チャネルでのプレビュー リリース)

- (Linux)GTK4 のスマートカード ダイアログ レイアウトを修正する
- (Linux)ブラウザーが再利用される場合の間違ったコールバックの問題を修正します。
- (Linux)CPP ブローカーで TLS 1.3 で GetDeviceState のサポートを追加する
- (Linux)Msai::SecureStorageLock と Msoa::SystemMutex でシグナルを受信するプロセスによるsem\_timedwaitエラーを処理する

##### 資産

- Ubuntu-24.04 - [microsoft-identity-broker_2.5.1-noble_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.5.1-noble_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_2.5.1-jammy_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.5.1-jammy_amd64.deb)
- Red Hat Enterprise Linux 10 - [microsoft-identity-broker-2.5.1-1.el10.x86_64.rpm](https://packages.microsoft.com/rhel/10/insiders-fast/Packages/m/microsoft-identity-broker-2.5.1-1.el10.x86_64.rpm)
- Red Hat Enterprise Linux 9.0 - [microsoft-identity-broker-2.5.1-1.el9.x86_64.rpm](https://packages.microsoft.com/rhel/9.0/insiders-fast/Packages/m/microsoft-identity-broker-2.5.1-1.el9.x86_64.rpm)
- Red Hat Enterprise Linux 8.0 - [microsoft-identity-broker-2.5.1-1.el8.x86_64.rpm](https://packages.microsoft.com/rhel/8.0/insiders-fast/Packages/m/microsoft-identity-broker-2.5.1-1.el8.x86_64.rpm)

#### 2.5.0 - 2026 年 1 月 13 日 - (高速 Insider チャネルでのプレビュー リリース)

- (Linux)ターゲット OS を含むようにパッケージ ファイル名を変更する
- (Linux)その他のバグ修正
- (Linux)Linux ブローカー パッケージに LICENSE ファイルとブローカー固有の CHANGELOG.md を含めます。
- (Linux)埋め込み認証ウィンドウの既定値 (タイトル/サイズ) を更新し、中央揃え動作を改善します。
- (Linux)RHEL 10 のサポートを追加する
- (Linux)デバイス登録の管理と診断のための dsreg コマンド ライン ツールを追加する
- (Linux)Linux デバイス ブローカーで使用される証明書/キーの場所を更新する
- (Linux)ブローカーで生成されたテレメトリにブローカーのバージョンを含める
- (xplat)DUNA xplat と DUNA iOS CBA を追加する

##### 資産

- Ubuntu-24.04 - [microsoft-identity-broker_2.5.0-noble_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.5.0-noble_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_2.5.0-jammy_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.5.0-jammy_amd64.deb)

#### 2.0.3 - 2025 年 10 月 21 日 - (高速 Insider チャネルでのプレビュー リリース)

- microsoft-identity-broker-diagnostics パッケージのサポートを追加しました。
- 一貫性を保つため、サービス コンポーネントの名前を `linux_broker` から `microsoft-identity-broker` に変更しました。
- 一貫性を保つため、サービス コンポーネントの名前を `linux_devicebroker` から `microsoft-identity-device-broker` に変更しました。
- ディストリビューション名を使用するように x-client-os を更新する

##### 資産

- Ubuntu-24.04 - [microsoft-identity-broker_2.0.3_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.3_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_2.0.3_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.3_amd64.deb)

#### 2.0.2 - 2025 年 9 月 19 日 - (高速 Insider チャネルでのプレビュー リリース)

以前の Java ベースのブローカーではなく、新しく書き換えられた C++ ブローカーを使用するためのプレビュー更新。

- SmartCard、証明書ベースの認証 (CBA)、または FIDO2 キーと個人 ID 検証 (PIV) プロファイルを使用した Linux デバイスでのフィッシング耐性 MFA (PRMFA) のサポートについて説明します。
- トークン要求のヘッダーを追加し、ID ブローカーのバージョンを区別できるようにします。
- ユーザーが新しい Linux デバイスでシングル サインオンを構成すると、デバイスは Microsoft Entra 登録ではなく Microsoft Entra 参加を実行します。 結合すると、デバイス全体との信頼関係が作成され、登録によってユーザー プロファイル内でのみ信頼が作成されます。 参加の信頼は、将来 platformSSO を有効にするための前提条件の手順です。
- デバイス ブローカー サービスの名前を `microsoft-identity-devicebroker` に変更しました。
- `microsoft-identity-broker`という名前のユーザー ブローカー サービスはなくなりました。 ユーザー ブローカーは、dbus 接続を介して呼び出される実行可能ファイルになりました
- デバイス証明書はキーチェーンから `/etc/ssl/private`に移動されます。 `private` ディレクトリでは、ブローカーによって、テナントごとのデバイス証明書、テナントごとのセッション トランスポート キー、およびそのディレクトリに格納されているデバイスレス キーが作成されます。 AT/RT などの他のすべてのユーザー データは、KeyChain に格納され、Microsoft Authentication Library (MSAL) 経由でアクセスされます。

##### 資産

- Ubuntu-24.04 - [microsoft-identity-broker_2.0.2_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.2_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_2.0.2_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.2_amd64.deb)

#### Linux での MSAL Python と MSAL .NET のブローカー サポート - 2025 年 6 月 13 日

- 2.0.1 の時点で、 `microsoft.identity.broker` では、Linux 上の [認証ブローカーで MSAL Python を使用し、Linux 上](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/linux-broker-py) の [ブローカーで MSAL.NET を使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/linux-dotnet-sdk) してブローカー経由でトークン要求を行う機能がサポートされるようになりました。

#### 2.0.1 - 2024 年 11 月 18 日

- Ubuntu 24.04 のパッケージサポートを追加しました。

##### 資産

- Ubuntu-24.04 - [microsoft-identity-broker_2.0.1_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.1_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_2.0.1_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.1_amd64.deb)
- Ubuntu-20.04 - [microsoft-identity-broker_2.0.1_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.1_amd64.deb)

#### 2.0.0 - 2024 年 3 月 21 日

- バグの修正

##### 資産

- Ubuntu-22.04 - [microsoft-identity-broker_2.0.0_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.0_amd64.deb)
- Ubuntu-20.04 - [microsoft-identity-broker_2.0.0_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_2.0.0_amd64.deb)

#### 1.7.0 - 2024 年 1 月 31 日

- 登録エラー時の 1001 への対処
- Red Hat Enterprise Linux Broker のインストール スクリプトの更新
- Linux Broker パッケージへのライセンスの追加

#### 1.6.1 - 2023 年 8 月 17 日

- [PATCH]Linux Broker で X509 証明書の安全な逆シリアル化を実行する (#2483)

##### 資産

- Ubuntu-20.04 - [microsoft-identity-broker_1.6.1_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.6.1_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_1.6.1_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.6.1_amd64.deb)

#### 1.6.0 - 2023 年 6 月 29 日

- Red Hat Enterprise Linux 8 および 9 のサポートが追加されました。

##### 資産

- Ubuntu-20.04 - [microsoft-identity-broker_1.6.0_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.6.0_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_1.6.0_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.6.0_amd64.deb)
- Red Hat Enterprise Linux 9.0 - [microsoft-identity-broker-1.6.0-1.x86_64.rpm](https://packages.microsoft.com/rhel/9/prod/Packages/m/microsoft-identity-broker-1.6.0-1.x86_64.rpm)
- Red Hat Enterprise Linux 8.0 - [microsoft-identity-broker-1.6.0-1.x86_64.rpm](https://packages.microsoft.com/rhel/8/prod/Packages/m/microsoft-identity-broker-1.6.0-1.x86_64.rpm)

#### 1.5.1 - 2023 年 5 月 9 日

- シリアル化ライブラリの更新
- メモリ使用量の変更を除外しました
- シークレット サービスバージョンのアップグレード - kubuntu

##### 資産

- Ubuntu-20.04 - [microsoft-identity-broker_1.5.1_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.5.1_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_1.5.1_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.5.1_amd64.deb)

#### 1.4.1 - 2022 年 10 月 22 日

- リソース所有者パスワード資格情報 (ROPC) テスト フック。
- キーリング "1001" エラーのログが追加されました。

##### 資産

- Ubuntu-20.04 - [microsoft-identity-broker_1.4.1_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.4.1_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_1.4.1_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.4.1_amd64.deb)

#### 1.4.0 - 2022 年 10 月 26 日

- Java 17 のサポート
- Ubuntu 22 のサポート

##### 資産

- Ubuntu-20.04 - [microsoft-identity-broker_1.4.0_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.4.0_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-broker_1.4.0_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.4.0_amd64.deb)

#### 1.3.0 - 2022 年 10 月 26 日

##### 資産

- Ubuntu-20.04 - [microsoft-identity-broker_1.3.0_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.3.0_amd64.deb)

#### 1.2.0 - 2022 年 10 月 26 日

##### 資産

- Ubuntu-20.04 - [microsoft-identity-broker_1.2.0_amd64.deb](https://packages.microsoft.com/ubuntu/20.04/prod/pool/main/m/microsoft-identity-broker/microsoft-identity-broker_1.2.0_amd64.deb)

### Microsoft-Identity-Diagnostics

#### 2.0.3 - 2025 年 10 月 21 日 - (プレビュー リリース)

- microsoft-identity-broker-diagnostics パッケージのサポートを追加しました。
- `linux_broker` の名前を `microsoft-identity-broker` に変更しました。

##### 資産

- Ubuntu-24.04 - [microsoft-identity-diagnostics_2.0.3_amd64.deb](https://packages.microsoft.com/ubuntu/24.04/prod/pool/main/m/microsoft-identity-diagnostics/microsoft-identity-diagnostics_2.0.3_amd64.deb)
- Ubuntu-22.04 - [microsoft-identity-diagnostics_2.0.3_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-diagnostics/microsoft-identity-diagnostics_2.0.3_amd64.deb)

##### 1.1.0 - 2022 年 11 月 29 日

##### 資産

- Ubuntu 22.04 - [microsoft-identity-diagnostics_1.1.0_amd64.deb](https://packages.microsoft.com/ubuntu/22.04/prod/pool/main/m/microsoft-identity-diagnostics/microsoft-identity-diagnostics_1.1.0_amd64.deb)

##### 1.0.1 - 2022 年 8 月 7 日

##### 資産

- Red Hat Enterprise Linux 8.0 - [microsoft-identity-diagnostics-1.0.1-1.x86_64.rpm](https://packages.microsoft.com/rhel/8/prod/Packages/m/microsoft-identity-diagnostics-1.0.1-1.x86_64.rpm)

### バージョンに関する問題のトラブルシューティング

#### バージョンの互換性

**アップグレードする前に:**

- 現在のバージョンを確認します: `dpkg -l microsoft-identity-broker`。
- ターゲット バージョンの破壊的変更を確認します。
- 潜在的なデバイスの再登録を計画します。

#### 移行に関する一般的な問題

**Java から C++ へのブローカーの移行 (2.0.1 → 2.0.2 以降):**

- 現象: アップグレード後の認証エラー
- 解決策:完全なアンインストールとクリーン再インストールが必要
- 手順: すべてのブローカー状態の削除、新しいバージョンの再インストール、デバイスの再参加

**パッケージのインストールに関する問題:**

- リポジトリの構成が Ubuntu/RHEL のバージョンと一致するかどうかを確認する
- packages.microsoft.com へのネットワーク接続を確認する
- インストールに十分なディスク領域を確保する

#### ヘルプの取得

バージョン固有の問題の場合:

- 既知の問題については、リリースノートを確認してください。
- システム要件が満たされていることを確認する
- 次を使用してログをレビューします。`journalctl --user -u microsoft-identity-broker.service`
- 詳細なトラブルシューティングのために microsoft-identity-diagnostics パッケージの使用を検討する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources"} -->
## Azure リソースの Microsoft Entra マネージド ID に関するドキュメント - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources
- Service: entra-id / managed-identities
- Article date: 2025-01-15
- Summary: Microsoft Entra ID で Azure リソースのマネージド ID を使用する方法について説明します。

Microsoft Entra ID でマネージド ID を使用する方法について説明します。

### マネージド ID について

#### 概要

- [Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)

### Azure 仮想マシンでマネージド ID を構成する

#### 攻略ガイド

- [ポータル](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vmss)
- [コマンドラインインターフェース（CLI）](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-cli-windows-vmss)
- [PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-powershell-windows-vmss)
- [Azure Resource Manager テンプレート](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-template-windows-vmss)
- [休む](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-rest-vmss)

### VM でマネージド ID を使用する

#### 攻略ガイド

- [アクセス トークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-rest-vmss)
- [PowerShell と CLI にサインインする](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-use-vm-sign-in)
- [Azure SDK での使用](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-use-vm-sdk)

### アプリケーションの構成

#### 攻略ガイド

- [マネージド ID を信頼するようにアプリケーションを構成する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity)

### マネージド ID アクセスを別の Azure リソースに割り当てる

#### 攻略ガイド

- [ポータル](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities-scale-sets?pivots=identity-mi-methods-azp)
- [コマンドラインインターフェース（CLI）](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-assign-access-azure-resource?pivots=identity-mi-access-cli)
- [PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-assign-access-azure-resource?pivots=identity-mi-access-powershell)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/assign-app-role-managed-identity-azure-cli"} -->
## Azure CLI を使用してマネージド ID をアプリケーション ロールに割り当てる - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/assign-app-role-managed-identity-azure-cli
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: Azure CLI を使用してマネージド ID アクセスを別のアプリケーションのロールに割り当てる手順。

Azure リソースのマネージド ID により、Microsoft Entra ID の ID が Azure サービスに提供されます。 それらが動作するためにコード内の資格情報は必要ありません。 この ID は、Microsoft Entra 認証をサポートするサービスへの認証を行うために、Azure サービスによって使用されます。 アプリケーション ロールによってロールベースのアクセス制御の形式が提供され、サービスで承認規則を実装できます。

注

アプリケーションが受け取るトークンは、基になるインフラストラクチャによってキャッシュされます。 これは、マネージド ID のロールに対して変更を加える際、処理にかなりの時間がかかる可能性があることを意味します。 詳細については、「 [承認にマネージド ID を使用する制限事項」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identity-best-practice-recommendations#limitation-of-using-managed-identities-for-authorization)参照してください。

この記事では、 [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) または [Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/what-is-azure-cli) を使用して、別のアプリケーションによって公開されるアプリケーション ロールにマネージド ID を割り当てる方法について説明します。

### [前提条件]

- Azure リソースのマネージド ID に慣れていない場合は、Azure リソースの [マネージド ID の概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するページを参照してください。
- [システム割り当てマネージド ID とユーザー割り当てマネージド ID の違いを確認します](https://learn.microsoft.com/ja-jp/azure/logic-apps/authenticate-with-managed-identity)。
- Azure アカウントをまだお持ちでない場合は、続行する前に [無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### CLI を使用してマネージド ID アクセスを別のアプリケーションのアプリ ロールに割り当てる

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「[Azure Cloud Shell の概要](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI 参照コマンドをローカルで実行する場合は、Azure CLI を [インストール](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) します。 Windows または macOS で実行している場合は、Docker コンテナーで Azure CLI を実行することを検討してください。 詳細については、「[Docker コンテナーで Azure CLI を実行する方法](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)」を参照してください。

    - ローカル インストールを使用する場合は、[az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用して Azure CLI にサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「 [Azure CLI を使用した Azure への認証](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - 初回使用時にインストールを求められたら、Azure CLI 拡張機能をインストールします。 拡張機能の詳細については、「[Azure CLI で拡張機能を使用および管理する](https://learn.microsoft.com/ja-jp/cli/azure/azure-cli-extensions-overview)」を参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行し、インストールされているバージョンおよび依存ライブラリを検索します。 最新バージョンにアップグレードするには、[az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

1. Azure [仮想マシンなどの](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities) Azure リソースでマネージド ID を有効にします。
2. マネージド ID のサービス プリンシパルのオブジェクト ID を調べます。

    - **システム割り当てマネージド ID の場合**、Azure portal のリソースの **[ID]** ページでオブジェクト ID を見つけることができます。 次のスクリプトを使用してオブジェクト ID を調べることもできます。 前の手順で作成したリソースのリソース ID が必要です。これは、リソースの **[プロパティ** ] ページの Azure portal で使用できます。

        ```azurecli
        resourceIdWithManagedIdentity="/subscriptions/{my subscription ID}/resourceGroups/{my resource group name}/providers/Microsoft.Compute/virtualMachines/{my virtual machine name}"
        
        oidForMI=$(az resource show --ids $resourceIdWithManagedIdentity --query "identity.principalId" -o tsv | tr -d '[:space:]')
        echo "object id for managed identity is: $oidForMI"
        ```
    - **ユーザー割り当てマネージド ID の場合**、Azure portal のリソースの **[概要** ] ページでマネージド ID のオブジェクト ID を見つけることができます。 次のスクリプトを使用してオブジェクト ID を調べることもできます。 ユーザー割り当てのマネージド ID のリソース ID が必要になります。

        ```azurecli
        userManagedIdentityResourceId="/subscriptions/{my subscription ID}/resourceGroups/{my resource group name}/providers/Microsoft.ManagedIdentity/userAssignedIdentities/{my managed identity name}"
        
        oidForMI=$(az resource show --id $userManagedIdentityResourceId --query "properties.principalId" -o tsv | tr -d '[:space:]')
        echo "object id for managed identity is: $oidForMI"
        ```
3. マネージド ID が要求を送信するサービスを表す[新しいアプリケーション登録を作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)します。

    - マネージド ID に対してアプリ ロールの付与を公開している API またはサービスのサービス プリンシパルが Microsoft Entra テナントに既にある場合は、このステップをスキップします。
4. サービス アプリケーションのサービス プリンシパルのオブジェクト ID を調べます。 これは、 [Microsoft Entra 管理センター](https://entra.microsoft.com/)を使用して確認できます。

    1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
    2. 左側のナビゲーション ブレードで、 **Entra ID**&gt;**Enterprise アプリ**を選択します。 次に、アプリケーションを見つけて **、オブジェクト ID を探します**。
    3. 次のスクリプトを使用して、サービス プリンシパルのオブジェクト ID を表示名で検索することもできます。

        ```azurecli
        appName="{name for your application}"
        serverSPOID=$(az ad sp list --filter "displayName eq '$appName'" --query '[0].id' -o tsv | tr -d '[:space:]')
        echo "object id for server service principal is: $serverSPOID"
        ```

        注

        アプリケーションの表示名は一意ではないため、取得したサービス プリンシパルが適切なアプリケーションのものであることを確認する必要があります。
    4. または、アプリケーション登録用の一意のアプリケーション ID でオブジェクト ID を見つけることができます。

        ```azurecli
        appID="{application id for your application}"
        serverSPOID=$(az ad sp list --filter "appId eq '$appID'" --query '[0].id' -o tsv | tr -d '[:space:]')
        echo "object id for server service principal is: $serverSPOID"
        ```
5. 前の手順で作成したアプリケーションにアプリ [ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) を追加します。 ロールは、Azure portal または Microsoft Graph を使用して作成できます。 たとえば、次のようなアプリ ロールを追加できます。

    ```json
    {
        "allowedMemberTypes": [
            "Application"
        ],
        "displayName": "Read data from MyApi",
        "id": "00001111-aaaa-2222-bbbb-3333cccc4444",
        "isEnabled": true,
        "description": "Allow the application to read data as itself.",
        "value": "MyApi.Read.All"
    }
    ```
6. アプリ ロールをマネージド ID に割り当てます。 アプリ ロールを割り当てるには、次の情報が必要です。

    - `managedIdentityObjectId`: マネージド ID のサービス プリンシパルのオブジェクト ID。ステップ 2 で確認しました。
    - `serverServicePrincipalObjectId`: サーバー アプリケーションのサービス プリンシパルのオブジェクト ID。ステップ 4 で確認しました。
    - `appRoleId`: サーバー アプリによって公開されるアプリ ロールの ID。ステップ 5 で生成しました。この例では、アプリ ロール ID は `00000000-0000-0000-0000-000000000000` です。
7. 次のスクリプトを実行してロールの割り当てを追加します。 この機能は Azure CLI では直接公開されておらず、ここでは代わりに、REST コマンドが使用されています。

    ```azurecli
    roleguid="00000000-0000-0000-0000-000000000000"
    az rest -m POST -u https://graph.microsoft.com/v1.0/servicePrincipals/$oidForMI/appRoleAssignments -b "{\"principalId\": \"$oidForMI\", \"resourceId\": \"$serverSPOID\",\"appRoleId\": \"$roleguid\"}"
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/assign-app-role-managed-identity-powershell"} -->
## PowerShell を使用してマネージド ID をアプリケーション ロールに割り当てる - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/assign-app-role-managed-identity-powershell
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: PowerShell を使用してマネージド ID アクセスを別のアプリケーションのロールに割り当てる手順。

Azure リソースのマネージド ID により、Microsoft Entra ID の ID が Azure サービスに提供されます。 それらが動作するためにコード内の資格情報は必要ありません。 この ID は、Microsoft Entra 認証をサポートするサービスへの認証を行うために、Azure サービスによって使用されます。 アプリケーション ロールによってロールベースのアクセス制御の形式が提供され、サービスで承認規則を実装できます。

注

アプリケーションが受け取るトークンは、基になるインフラストラクチャによってキャッシュされます。 これは、マネージド ID のロールに対して変更を加える際、処理にかなりの時間がかかる可能性があることを意味します。 詳細については、「 [承認にマネージド ID を使用する制限事項」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identity-best-practice-recommendations#limitation-of-using-managed-identities-for-authorization)参照してください。

この記事では、 [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) または [Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/what-is-azure-cli) を使用して、別のアプリケーションによって公開されるアプリケーション ロールにマネージド ID を割り当てる方法について説明します。

### [前提条件]

- Azure リソースのマネージド ID に慣れていない場合は、Azure リソースの [マネージド ID の概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するページを参照してください。
- [システム割り当てマネージド ID とユーザー割り当てマネージド ID の違いを確認します](https://learn.microsoft.com/ja-jp/azure/logic-apps/authenticate-with-managed-identity)。
- Azure アカウントをまだお持ちでない場合は、続行する前に [無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### PowerShell を使用してマネージド ID アクセスを別のアプリケーションのアプリ ロールに割り当てる

サンプル スクリプトを実行するには、次の 2 つのオプションがあります。

- コード ブロックの右上隅にある [[試](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview)してみる] ボタンを使用して開くことができる **Azure Cloud Shell** を使用します。
- 最新バージョンの [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started) をインストールして、スクリプトをローカルで実行します。

1. Azure [VM などの](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities) Azure リソースでマネージド ID を有効にします。
2. マネージド ID のサービス プリンシパルのオブジェクト ID を調べます。

    **システム割り当てマネージド ID の場合**、Azure portal のリソースの **[ID]** ページでオブジェクト ID を見つけることができます。 次の PowerShell スクリプトを使用して、オブジェクト ID を調べることもできます。 手順 1 で作成したリソースのリソース ID が必要です。リソースの **[プロパティ** ] ページの Azure portal で使用できます。

    ```powershell
    $resourceIdWithManagedIdentity = '/subscriptions/{my subscription ID}/resourceGroups/{my resource group name}/providers/Microsoft.Compute/virtualMachines/{my virtual machine name}'
    (Get-AzResource -ResourceId $resourceIdWithManagedIdentity).Identity.PrincipalId
    ```

    **ユーザー割り当てマネージド ID の場合**、Azure portal のリソースの **[概要** ] ページでマネージド ID のオブジェクト ID を見つけることができます。 次の PowerShell スクリプトを使用して、オブジェクト ID を調べることもできます。 ユーザー割り当てのマネージド ID のリソース ID が必要になります。

    ```powershell
    $userManagedIdentityResourceId = '/subscriptions/{my subscription ID}/resourceGroups/{my resource group name}/providers/Microsoft.ManagedIdentity/userAssignedIdentities/{my managed identity name}'
    (Get-AzResource -ResourceId $userManagedIdentityResourceId).Properties.PrincipalId
    ```
3. マネージド ID から要求を送信するサービスを表す[新しいアプリケーション登録を作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)します。

    - マネージド ID に対してアプリ ロールの付与を公開している API またはサービスのサービス プリンシパルが Microsoft Entra テナントに既にある場合は、このステップをスキップします。 たとえば、マネージド ID アクセスを Microsoft Graph API に付与する場合です。
4. サービス アプリケーションのサービス プリンシパルのオブジェクト ID を調べます。 これは、 [Microsoft Entra 管理センター](https://entra.microsoft.com/)を使用して確認できます。

    1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
    2. 左側のナビゲーション ブレードで、 **Entra ID**&gt;**Enterprise アプリ**を選択します。 次に、アプリケーションを見つけて **、オブジェクト ID を探します**。
    3. 次の PowerShell スクリプトを使用して、サービス プリンシパルのオブジェクト ID を表示名で検索することもできます。

    ```powershell
    $serverServicePrincipalObjectId = (Get-MgServicePrincipal -Filter "DisplayName eq '$applicationName'").Id
    ```

    注

    アプリケーションの表示名は一意ではありません。そのため、正しいアプリケーションのサービス プリンシパルを取得していることを確認する必要があります。
5. 前の手順で作成したアプリケーションにアプリ [ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) を追加します。 ロールは、Azure portal または Microsoft Graph を使用して作成できます。 たとえば、 [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)で次のクエリを実行して、アプリ ロールを追加できます。

    ```http
    PATCH /applications/{id}/
    
    {
        "appRoles": [
            {
                "allowedMemberTypes": [
                    "User",
                    "Application"
                ],
                "description": "Read reports",
                "id": "00001111-aaaa-2222-bbbb-3333cccc4444",
                "displayName": "Report reader",
                "isEnabled": true,
                "value": "report.read"
            }
        ]
    }
    ```
6. アプリ ロールをマネージド ID に割り当てます。 アプリ ロールを割り当てるには、次の情報が必要です。

    - `managedIdentityObjectId`: マネージド ID のサービス プリンシパルのオブジェクト ID。前のステップで確認しました。
    - `serverServicePrincipalObjectId`: サーバー アプリケーションのサービス プリンシパルのオブジェクト ID。ステップ 4 で確認しました。
    - `appRoleId`: サーバー アプリによって公開されるアプリ ロールの ID。ステップ 5 で生成しました。この例では、アプリ ロール ID は `00000000-0000-0000-0000-000000000000` です。
    - 次の PowerShell コマンドを実行して、ロールの割り当てを追加します。

        ```powershell
        New-MgServicePrincipalAppRoleAssignment `
            -ServicePrincipalId $serverServicePrincipalObjectId `
            -PrincipalId $managedIdentityObjectId `
            -ResourceId $serverServicePrincipalObjectId `
            -AppRoleId $appRoleId
        ```

### 完全なサンプル スクリプト

このスクリプトの例は、Azure Web アプリのマネージド ID をアプリ ロールに割り当てる方法を示したものです。

```powershell
# Install the module.
# Install-Module Microsoft.Graph -Scope CurrentUser

# Your tenant ID (in the Azure portal, under Microsoft Entra ID > Overview).
$tenantID = '<tenant-id>'

# The name of your web app, which has a managed identity that should be assigned to the server app's app role.
$webAppName = '<web-app-name>'
$resourceGroupName = '<resource-group-name-containing-web-app>'

# The name of the server app that exposes the app role.
$serverApplicationName = '<server-application-name>' # For example, MyApi

# The name of the app role that the managed identity should be assigned to.
$appRoleName = '<app-role-name>' # For example, MyApi.Read.All

# Look up the web app's managed identity's object ID.
$managedIdentityObjectId = (Get-AzWebApp -ResourceGroupName $resourceGroupName -Name $webAppName).identity.principalid

Connect-MgGraph -TenantId $tenantId -Scopes 'Application.Read.All','Application.ReadWrite.All','AppRoleAssignment.ReadWrite.All','Directory.AccessAsUser.All','Directory.Read.All','Directory.ReadWrite.All'

# Look up the details about the server app's service principal and app role.
$serverServicePrincipal = (Get-MgServicePrincipal -Filter "DisplayName eq '$serverApplicationName'")
$serverServicePrincipalObjectId = $serverServicePrincipal.Id
$appRoleId = ($serverServicePrincipal.AppRoles | Where-Object {$_.Value -eq $appRoleName }).Id

# Assign the managed identity access to the app role.
New-MgServicePrincipalAppRoleAssignment `
    -ServicePrincipalId $serverServicePrincipalObjectId `
    -PrincipalId $managedIdentityObjectId `
    -ResourceId $serverServicePrincipalObjectId `
    -AppRoleId $appRoleId
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/azure-resources-extension-managed-identities"} -->
## マネージド ID に対して VS Code で Azure リソース拡張機能を使用する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/azure-resources-extension-managed-identities
- Service: entra-id / managed-identities
- Article date: 2024-06-12
- Summary: VS Code の Azure リソース拡張機能を使用して、開発環境から直接 Azure マネージド ID を管理および構成する方法について説明します。

Visual Studio Code 用の Azure リソース拡張機能は、開発環境から直接 Azure リソースを管理するための強力なインターフェイスを提供します。 この拡張機能は、開発者がマネージド ID 構成を検査および検証するための重要な機能を提供します。 この記事では、マネージド ID が適切に構成され、セキュリティで保護されるように、VS Code 内から実行できる 3 つのタスクについて説明します。

### [前提条件]

開始する前に、次のことを確認してください。

- Visual Studio Code がインストールされていること
- Azure リソースを表示するための適切なアクセス許可
- マネージド ID を含む Azure サブスクリプションへのアクセス

### Azure リソース拡張機能のインストール

Visual Studio Code でマネージド ID を操作するには、Azure リソース拡張機能が必要です。 詳細については、「[Visual Studio Code の Azure リソース」を](https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-azureresourcegroups)参照してください。

### マネージド ID を作成する

Azure サブスクリプションでマネージド ID を作成する必要があります。 詳細については、「 [マネージド ID の作成」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities)参照してください。

### マネージド ID プロパティ (クライアント ID、オブジェクト ID など) を検出する

マネージド ID を操作する最初の手順は、主なプロパティと VS Code からアクセスする方法を理解することです。 すべてのマネージド ID には、アプリケーション コードに必要な重要なプロパティがいくつかあります。 例えば次が挙げられます。

- クライアント ID: アプリケーションがトークンを要求するために使用する一意の識別子
- オブジェクト ID (Principal ID): ロールの割り当てに使用される Microsoft Entra の一意の識別子
- リソース ID: マネージド ID の完全な Azure リソース パス
- テナント ID: ID が存在する Microsoft Entra テナント

これらのプロパティは、Azure リソース拡張機能では直接表示できません。 `/@azure` コマンドで Copilot を使用して取得します。 その方法は、次の通りです。

1. VS Code サイドバーで Azure リソース拡張機能を開く
2. サブスクリプションを選択します。
3. [マネージド ID] セクションを見つけます。 ここは。 あなたがアクセス権を持つすべてのマネージド ID が表示されます。
4. 目的のマネージド ID を右クリックし、[Ask @Azure\*\*] を選択します。 これにより、Copilot とのチャットが開きます。 まだサインインしていない場合は、Azure アカウントにサインインする必要があります。 これは Azure リソースにアクセスするために必要であるため、エージェント モードで Copilot を使用していることを確認します。
5. `Client ID`、`Object ID`、`Resource ID`など、探している任意のプロパティを照会します。 たとえば、以下を入力できます。

    ```
     /@azure get the Client ID of the managed identity named "myManagedIdentity"
    ```

### マネージド ID を使用してソース リソースを確認する

マネージド ID は、仮想マシン、アプリ サービスなど、さまざまな Azure リソースの ID として使用できます。 特定のマネージド ID を使用しているリソースを確認するには、その ID に関連付けられているターゲット サービスを確認します。

1. VS Code サイドバーで Azure リソース拡張機能を開く
2. サブスクリプションを選択します。
3. [マネージド ID] セクションを見つけます。 ここは。 あなたはアクセス権を持つすべての管理された ID を見ることができます。
4. マネージド ID を選択し、[ **ソース リソース**] を選択します。
5. マネージド ID を使用するすべてのリソースを次に示します。

### ターゲット リソースへのマネージド ID の割り当てを確認する

マネージド ID は、さまざまな Azure リソースに割り当てることができます。 マネージド ID が割り当てられているリソースは、Visual Studio Code の Azure リソース拡張機能から直接確認できます。 これは、アクセスする必要があるリソースに対してマネージド ID が正しく構成されていることを確認するのに役立ちます。

1. VS Code サイドバーで Azure リソース拡張機能を開く
2. サブスクリプションを選択します。
3. [マネージド ID] セクションを見つけます。 ここは。 アクセス権を持つすべてのマネージド アイデンティティを確認できます。
4. マネージド ID を選択し、[ **ターゲット サービス**] を選択します。
5. マネージド ID が割り当てられているすべてのリソースがここに一覧表示されます。

### ターゲット リソースのマネージド ID アクセス許可を確認する

アクセス許可は Azure Role-Based アクセス制御 (RBAC) を通じて管理されます。これにより、特定のリソースのマネージド ID にロールを割り当てることができます。

マネージド ID のアクセス許可を確認するには、次の手順に従います。

1. VS Code サイドバーで Azure リソース拡張機能を開く
2. サブスクリプションを選択します。
3. [マネージド ID] セクションを見つけます。 ここでは、アクセス権を持つすべてのマネージド ID が表示されます。
4. マネージド ID を選択し、[ **ターゲット サービス**] を選択します。 マネージド ID が割り当てられているすべてのリソースがここに表示されます。
5. ターゲット リソースを選択してアクセス許可を表示する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/configure-managed-identities-assignment-restriction"} -->
## ユーザー割り当てマネージド ID の割り当て制限を構成する (プレビュー) - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/configure-managed-identities-assignment-restriction
- Service: entra-id / managed-identities
- Article date: 2026-07-10
- Summary: Azure ポータルでユーザー割り当てマネージド ID の割り当て制限を構成して、特定のリソース プロバイダーにスコープを設定する方法について説明します。

この記事では、Azure ポータルを使用して、ユーザー割り当てマネージド ID の割り当て制限 (リソース制限とも呼ばれます) を構成する方法について説明します。 これはプレビュー段階の機能です。

割り当て制限により、マネージド ID を割り当てることができるリソース プロバイダーまたはリソースの種類を明示的に定義できます。 割り当て制限を適用すると、マネージド ID が意図したスコープ内に保持され、セキュリティと運用上の境界が強化されます。 マネージド ID を割り当てることができる場所を制限することで、ID の再利用を制限し、ブラスト半径を減らします。

### 前提条件

作業を開始する前に、次の準備ができていることを確認します。

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/free)。

### Azure ポータルで割り当て制限を作成する

割り当て制限は、ユーザー割り当てマネージド ID を作成するときに構成します。 割り当て制限付きのユーザー割り当てマネージド ID を作成するには、次の手順に従います。

1. 少なくとも[マネージド ID 共同作成者](https://portal.azure.com)ロールを使用して[、Azure ポータル](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)にサインインします。
2. **[マネージド ID]** に移動します。 検索ボックスに、「*マネージド ID*」と入力します。 **[サービス]** の下で、 **[マネージド ID]** を選択します。
3. **[+ 作成**] を選択して、新しいユーザー割り当てマネージド ID を追加し、基本設定を構成します。

    - **[サブスクリプション]**:サブスクリプションを選択します。
    - **リソース グループ**: 既存のリソース グループを選択するか、新しいリソース グループを作成します。
    - **[名前**]: マネージド ID の名前を入力します。
    - **リージョン**: デプロイするリージョンを選択します。
    - **分離スコープ: 分離**スコープを選択します。 リージョンを分離するために、この値を **リージョン** に設定することをお勧めします。 詳細については、「 [ユーザー割り当てマネージド ID の分離スコープ」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-isolation-scope)参照してください。
    - **リソース**: [ **リソースの追加]** を選択します。 表示された [ **リソースの種類の選択]** パネルで、ID が制限されているリソース プロバイダーまたはリソースの種類を検索して選択します。

    Note

    **Resource** が未構成のままの場合、値は空の配列を表す **None** と表示されます。
4. **[確認および作成]** を選択して構成を検証します。
5. [ **作成]** を選択してマネージド ID をデプロイします。

### Azure ポータルでの割り当ての制限の更新

既存のユーザー割り当てマネージド ID の分離スコープと割り当て制限はいつでも更新できます。 割り当ての制限を更新するには、次の手順に従います。

1. 少なくとも[マネージド ID 共同作成者](https://portal.azure.com)ロールを使用して[、Azure ポータル](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)にサインインします。
2. **[マネージド ID]** に移動し、更新するユーザー割り当てマネージド ID を選択します。
3. **[設定]** で **[プロパティ]** を選択します。
4. 割り当て制限を更新します。

    - **分離スコープ**: **[リージョン** ] または **[なし]** を選択します。
    - **リソース**: 編集 (鉛筆) アイコンを選択します。 表示された [ **リソースの種類の選択** ] パネルで、ID が制限されているリソース プロバイダーまたはリソースの種類を追加または削除します。
5. **[保存]** を選択して変更を保存します。

Note

更新プログラムが割り当て制限リストからリソース プロバイダーを削除する場合は、リソース プロバイダーを一覧から削除する前に、 *まず*ソース リソースからユーザー割り当てマネージド ID の割り当てを解除します。

### Azure ポータルでサポートされているリソース プロバイダーとリソースの種類

Note

リソースの割り当て制限に **[なし]** を選択すると、ID は制限されず、マネージド ID をサポートするすべてのリソース プロバイダーのリソースに割り当てることができます。

ID 割り当てを特定のリソース プロバイダーに制限する場合にのみ、リソース割り当ての制限を構成します。 Azure ポータルの [**リソースの種類の選択**] ボックスの一覧には、サポートされているリソース プロバイダーとリソースの種類がすべて含まれていない場合があることに注意してください。

Azure ポータルの [**リソースの種類の選択**] ウィンドウには、マネージド ID をサポートするすべてのリソース プロバイダーとリソースの種類が表示されるわけではありません。 構成するリソースが一覧にない場合は、Azure CLIを使用して ID 割り当てを作成または更新します。 [**リソースの種類の選択**] ボックスの一覧で現在使用できないリソースについては、以下のAzure CLIの例を参照してください。

Warning

Azure CLI コマンドとコードとしてのインフラストラクチャ (IaC) テンプレートで、リソース プロバイダーの名前空間を独自に指定します (たとえば、`Microsoft.Storage`)。 `Microsoft.Storage/*`は使用しないでください。

Azure ポータルの [**リソースの種類の選択**] ウィンドウには、プロバイダー全体の選択が`Microsoft.Storage/*`として表示されます。 `/*` サフィックスはポータルの表示規則であり、API が受け入れる値の一部ではありません。 ポータルに`Microsoft.Storage/*`が表示されている場合でも、Azure CLIコマンドと IaC テンプレートで`Microsoft.Storage`を使用します。 プロバイダー全体ではなく 1 つのリソースの種類に ID を制限するには、完全なリソースの種類 ( `Microsoft.Storage/storageAccounts`など) を指定します。

#### リソース割り当て制限を使用して ID を作成する

```bash
az identity create \
  --name MyIdentity \
  --resource-group MyResourceGroup \
  --resource-restriction '{"providers": ["Microsoft.Compute", "Microsoft.Storage"]}'
```

#### 特定のリソースへの割り当てを制限するように ID を更新する

```bash
az identity update \
  --name MyIdentity \
  --resource-group MyResourceGroup \
  --resource-restriction '{"providers": ["Microsoft.Compute", "Microsoft.Storage"]}'
```

#### ID に関連付けられているリソースを一覧表示する

```bash
az identity list-resources \
  --name MyIdentity \
  --resource-group MyResourceGroup
```

#### 無制限の ID を作成する

```bash
az identity create \
--name MyIdentity \
--resource-group MyResourceGroup \
--resource-restriction '{"providers": []}'
```

#### ID からすべてのリソース プロバイダーの制限を削除する

```bash
az identity update \
--name MyIdentity \
--resource-group MyResourceGroup \
--resource-restriction '{"providers": []}'
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/configure-managed-identities-isolation-scope"} -->
## ユーザー割り当てマネージド ID の分離スコープを構成する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/configure-managed-identities-isolation-scope
- Service: entra-id / managed-identities
- Article date: 2025-07-17
- Summary: セキュリティと回復性を向上させるために、ユーザー割り当てマネージド ID の分離スコープを構成する方法について説明します。

この記事では、Azure portal でユーザー割り当てマネージド ID の分離スコープを構成する方法について説明します。 リージョン分離スコープを有効にするか、分離スコープを none に設定できます。 リージョン分離は、マネージド ID を使用できる場所を制限し、同じリージョン内のソース リソースにのみ割り当てられるようにすることで、セキュリティと回復性を向上させます。

### [前提条件]

作業を開始する前に、以下を必ず取得してください。

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 利点と影響を理解するには、 [ユーザー割り当てマネージド ID の分離スコープ](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-isolation-scope) の概念に関する記事を参照してください。

### Azure portal で分離スコープを構成する

次の手順を使用して、ユーザー割り当てマネージド ID の分離スコープを構成します。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. **[マネージド ID]** に移動します。 検索ボックスに「 *マネージド ID」と入力します*。 **[サービス]** の下で、 **[マネージド ID]** を選択します。
3. 新しいマネージド ID を作成します。 **[+ 作成**] を選択して、新しいユーザー割り当てマネージド ID を追加します。 基本設定を構成します。

    - **サブスクリプション: サブスクリプション**を選択する
    - **リソース グループ**: 既存のリソース グループを選択するか、新しいリソース グループを作成します。
    - **リージョン**: デプロイする特定のリージョンを選択します
    - **分離スコープ**: 分離スコープをリージョンに設定する場合は [ *リージョン* ] を選択し、なしに設定する場合は [ *なし* ] を選択します。
    - **名前**: マネージド ID の名前を入力します
4. 確認して作成します。 **[確認および作成]** を選択して構成を検証します。
5. [ **作成]** を選択してマネージド ID をデプロイします。

分離スコープの値を設定した後は、Azure Resource Manager デプロイ テンプレートまたは REST API を使用してのみ更新できます。 Azure portal では、作成後の分離スコープの変更はまだサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/grant-managed-identity-resource-access-azure-cli"} -->
## Azure CLI を使用してマネージド ID にリソースへのアクセスを許可する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/grant-managed-identity-resource-access-azure-cli
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: Azure CLI を使用して、マネージド ID アクセスを Azure リソースまたは別のリソースに割り当てる手順について説明します。

この記事では、Azure CLI を使用して、マネージド ID に Azure リソースへのアクセス権を付与する方法について説明します。 この記事では、Azure ストレージ アカウントにアクセスする Azure 仮想マシン (Azure VM) マネージド ID の例を使用します。 マネージド ID で Azure リソースを構成した後、他のセキュリティ プリンシパルと同じように、別のリソースにマネージド ID アクセスを付与できます。

### [前提条件]

- Azure [仮想マシン](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities)などの Azure リソースでマネージド ID が有効になっていることを確認します。
- Azure アカウントをまだお持ちでない場合は、続行する前に [無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### 環境を準備する

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「[Azure Cloud Shell の概要](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI 参照コマンドをローカルで実行する場合は、Azure CLI を [インストール](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) します。 Windows または macOS で実行している場合は、Docker コンテナーで Azure CLI を実行することを検討してください。 詳細については、「[Docker コンテナーで Azure CLI を実行する方法](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)」を参照してください。

    - ローカル インストールを使用する場合は、[az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用して Azure CLI にサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「 [Azure CLI を使用した Azure への認証](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - 初回使用時にインストールを求められたら、Azure CLI 拡張機能をインストールします。 拡張機能の詳細については、「[Azure CLI で拡張機能を使用および管理する](https://learn.microsoft.com/ja-jp/cli/azure/azure-cli-extensions-overview)」を参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行し、インストールされているバージョンおよび依存ライブラリを検索します。 最新バージョンにアップグレードするには、[az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

### Azure RBAC を使用して別のリソースにマネージド ID アクセスを割り当てる

1. この例では、Azure 仮想マシン (VM) マネージド アクセスをストレージ アカウントに付与します。 まず、[az resource list](https://learn.microsoft.com/ja-jp/cli/azure/resource#az-resource-list) を使用して、myVM という名前の VM のサービス プリンシパルを取得します。

    ```azurecli
    spID=$(az resource list -n myVM --query [*].identity.principalId --out tsv)
    ```

    Azure 仮想マシン (VM) スケール セットについてもコマンドはほぼ同じですが、ここでは、"DevTestVMSS" という名前の VM セットのサービス プリンシパルを取得します。

    ```azurecli
    spID=$(az resource list -n DevTestVMSS --query [*].identity.principalId --out tsv)
    ```
2. サービス プリンシパル ID を作成したら、[az role assignment create](https://learn.microsoft.com/ja-jp/cli/azure/role/assignment#az-role-assignment-create) を使用して、"myStorageAcct" というストレージ アカウントに、仮想マシンまたは仮想マシン スケール セットの**閲覧者**アクセスを付与します。

    ```azurecli
    az role assignment create --assignee $spID --role 'Reader' --scope /subscriptions/<mySubscriptionID>/resourceGroups/<myResourceGroup>/providers/Microsoft.Storage/storageAccounts/myStorageAcct
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/grant-managed-identity-resource-access-azure-portal"} -->
## Azure portal を使用してマネージド ID にリソースへのアクセス権を付与する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/grant-managed-identity-resource-access-azure-portal
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: Azure portal を使用して、マネージド ID アクセスを Azure リソースまたは別のリソースに割り当てる手順について説明します。

この記事では、Azure portal を使用して、マネージド ID に Azure リソースへのアクセス権を付与する方法について説明します。 この記事では、Azure ストレージ アカウントにアクセスする Azure 仮想マシン (Azure VM) マネージド ID の例を使用します。 マネージド ID で Azure リソースを構成した後、他のセキュリティ プリンシパルと同じように、別のリソースにマネージド ID アクセスを付与できます。

### [前提条件]

- Azure [仮想マシン](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities)などの Azure リソースでマネージド ID が有効になっていることを確認します。
- Azure アカウントをまだお持ちでない場合は、続行する前に [無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### Azure portal を使用して他のリソースにマネージド ID アクセスを割り当てるための Azure RBAC の使用方法

次に示す手順は、Azure RBAC を使用してサービスへのアクセスを許可する方法を示しています。 アクセス権の付与方法に関する特定のサービス ドキュメント ([Azure Data Explorer](https://learn.microsoft.com/ja-jp/azure/data-explorer/data-explorer-overview) の手順など) を確認してください。 一部の Azure サービスは、データ プレーンで Azure RBAC を採用している最中です。

1. マネージド ID を構成した Azure サブスクリプションに関連付けられているアカウントを使用して、[Azure portal](https://portal.azure.com) にサインインします。
2. アクセス制御を変更する目的のリソースに移動します。 この例では、Azure 仮想マシン (VM) アクセスをストレージ アカウントに付与し、ストレージ アカウントに移動できるようにします。
3. **[アクセス制御 (IAM)]** を選択します。
4. **[追加]**&gt;**[ロールの割り当ての追加]** を選択して、**[ロールの割り当ての追加]** ページを開きます。
5. ロールとマネージド ID を選択します。 詳細な手順については、「[Azure portal を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)」を参照してください。

    [Image: ロールの割り当てを追加するためのページを示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/grant-managed-identity-resource-access-powershell"} -->
## PowerShell を使用してマネージド ID にリソースへのアクセスを許可する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/grant-managed-identity-resource-access-powershell
- Service: entra-id / managed-identities
- Article date: 2024-06-03
- Summary: PowerShell を使用して Azure リソースまたは別のリソースにマネージド ID アクセスを割り当てる手順について説明します。

この記事では、PowerShell を使用して、マネージド ID に Azure リソースへのアクセス権を付与する方法について説明します。 この記事では、Azure ストレージ アカウントにアクセスする Azure 仮想マシン (Azure VM) マネージド ID の例を使用します。 マネージド ID で Azure リソースを構成した後、他のセキュリティ プリンシパルと同じように、別のリソースにマネージド ID アクセスを付与できます。

### [前提条件]

- Azure [仮想マシン](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities)などの Azure リソースでマネージド ID が有効になっていることを確認します。
- Azure アカウントをまだお持ちでない場合は、続行する前に [無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### PowerShell を使用して、Azure RBAC を使用して他のリソースにマネージド ID アクセスを割り当てる

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

この例のスクリプトを実行するには、次の 2 つのオプションがあります。

- コード ブロックの右上隅にある [[試](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview)してみる] ボタンを使用して開くことができる **Azure Cloud Shell** を使用します。
- 最新バージョンの [Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) をインストールしてスクリプトをローカルで実行した後、`Connect-AzAccount` を使用して Azure にサインインします。

1. Azure [VM などの](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities) Azure リソースでマネージド ID を有効にします。
2. Azure 仮想マシン (VM) アクセスをストレージ アカウントに付与します。

    1. [Get-AzVM](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/get-azvm) を使用して `myVM` という VM のサービス プリンシパルを取得します。これは、マネージド ID が有効になっているときに作成されたものです。
    2. [New-AzRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azroleassignment) を使用して、VM の**閲覧者**アクセスを `myStorageAcct` というストレージ アカウントに付与します。

    ```azurepowershell
    $spID = (Get-AzVM -ResourceGroupName myRG -Name myVM).identity.principalid
    New-AzRoleAssignment -ObjectId $spID -RoleDefinitionName "Reader" -Scope "/subscriptions/<mySubscriptionID>/resourceGroups/<myResourceGroup>/providers/Microsoft.Storage/storageAccounts/<myStorageAcct>"
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-managed-identities-work-vm"} -->
## Azure リソースのマネージド ID と Azure 仮想マシンの連携 - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-managed-identities-work-vm
- Service: entra-id / managed-identities
- Article date: 2025-03-14
- Summary: Azure リソースのマネージド ID と Azure 仮想マシンの連携について説明します。

Azure リソースのマネージド ID は、Microsoft Entra ID で自動的に管理される ID を Azure サービスに提供します。 この ID を使用すると、コード内に資格情報を記述することなく、Microsoft Entra の認証をサポートする任意のサービスに対して認証を行うことができます。

この記事では、マネージド ID が Azure 仮想マシン (VM) とどのように連携するかについて説明します。

### しくみ

内部的には、マネージド ID は特別な種類のサービス プリンシパルであり、Azure リソースとだけ使用できます。 マネージド ID が削除されると、対応するサービス プリンシパルが自動的に削除されます。 同様に、ユーザー割り当て ID またはシステム割り当て ID が作成されると、その ID に対し、マネージド ID リソースプロバイダー (MSRP) によって内部的に証明書が発行されます。

コードは、Microsoft Entra 認証をサポートするサービスのアクセス トークンを要求するために、マネージド ID を使用できます。 Azure は、サービス インスタンスによって使用される資格情報のローリングを実行します。

次の図は、マネージド サービス ID と Azure 仮想マシン (VM) が連携するようすを示したものです。

[Image: マネージド サービス ID が Azure 仮想マシンにどのように関連付けられているか、アクセス トークンを取得し、保護された Microsoft Entra リソースを呼び出す方法を示す図。]

次の表は、システム割り当てとユーザー割り当てのマネージド ID の違いを示しています。

| プロパティ | システム割り当て管理 ID | ユーザーによって割り当てられたマネージド ID |
| --- | --- | --- |
| 作成 | Azure リソース (たとえば、Azure 仮想マシンまたは Azure App Service) の一部として作成されます。 | スタンドアロンの Azure リソースとして作成されます。 |
| ライフ サイクル | マネージド ID の作成に使用された Azure リソースとの共有ライフ サイクル。  親リソースが削除されると、マネージド ID も削除されます。 | 独立したライフ サイクル。  明示的に削除する必要があります。 |
| Azure リソース間で共有されます | 共有できません。  1 つの Azure リソースにのみ関連付けることができます。 | 共有できます。  同じユーザー割り当てマネージド ID を、複数の Azure リソースに関連付けることができます。 |
| 一般的なユース ケース | 1 つの Azure リソース内に含まれるワークロード。  独立した ID が必要なワークロード。  たとえば、1 つの仮想マシンで実行されるアプリケーション | 複数のリソースで実行され、1 つの ID を共有できるワークロード。  プロビジョニング フローの一部として、セキュリティで保護されたリソースへの事前承認が必要なワークロード。  リソースが頻繁にリサイクルされるものの、アクセス許可は一貫性を保つ必要があるワークロード。  たとえば、複数の仮想マシンが同じリソースにアクセスする必要があるワークロード |

### システム割り当て管理 ID

1. Azure Resource Manager は、VM 上でシステム割り当てマネージド ID を有効にするための要求を受け取ります。
2. Azure Resource Manager は、VM の ID を表すサービス プリンシパルを Microsoft Entra ID に作成します。 サービス プリンシパルは、サブスクリプションの信頼された Microsoft Entra テナントに作成されます。
3. Azure Resource Manager では、Azure Instance Metadata Service の ID エンドポイント ([Windows](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/instance-metadata-service) および [Linux](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/instance-metadata-service) の場合) を使用して VM ID を更新します。これにより、エンドポイントにサービス プリンシパルのクライアント ID と証明書が提供されます。
4. VM に ID が設定された後、Azure リソースにアクセスする権利を VM に与えるには、そのサービス プリンシパル情報を使用します。 Azure Resource Manager を呼び出すには、Azure のロールベースのアクセス制御 (Azure RBAC) を使用して、VM のサービス プリンシパルに適切なロールを割り当てます。 Key Vault を呼び出すには、Key Vault 内の特定のシークレットまたは特定のキーにアクセスする権利をコードに与えます。
5. VM 上で実行されているコードは、VM 内からのみアクセスできる Azure Instance Metadata サービス エンドポイントにトークン (`http://169.254.169.254/metadata/identity/oauth2/token`) を要求できます。

    - リソース パラメーターは、トークンの送信先のサービスを指定します。 Azure Resource Manager に対して認証を行うには、`resource=https://management.azure.com/` を使用します。
    - API バージョン パラメーターは、IMDS バージョンを指定します。 api-version=2018-02-01 以降を使用します。

    次の例では、CURL を使用してローカル マネージド ID エンドポイントに要求を行い、Azure Instance Metadata サービス用のアクセス トークンを取得する方法を示しています。

    ```bash
    curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fstorage.azure.com%2F' -H Metadata:true
    ```
6. ステップ 3. で構成したクライアント ID と証明書を使用して、ステップ 5. で指定したアクセス トークンを要求する呼び出しが Microsoft Entra ID に対して行われます。 Microsoft Entra ID は、JSON Web トークン (JWT) アクセス トークンを返します。
7. Microsoft Entra 認証をサポートするサービスを呼び出すときにアクセス トークンをコードから送信します。

### ユーザーによって割り当てられたマネージド ID

1. Azure Resource Manager が、ユーザー割り当てマネージド ID を作成するための要求を受け取ります。
2. Azure Resource Manager が、ユーザー割り当てマネージド ID を表すサービス プリンシパルを Microsoft Entra ID に作成します。 サービス プリンシパルは、サブスクリプションの信頼された Microsoft Entra テナントに作成されます。
3. ユーザーが割り当てたマネージド ID を VM 上に構成する要求が Azure Resource Manager によって受信され、Azure Instance Metadata Service の ID エンドポイントがサービス プリンシパルのクライアント ID と証明書を使用して更新されます。
4. ユーザー割り当てマネージド ID が作成された後、Azure リソースにアクセスする権利をその ID に与えるには、そのサービス プリンシパル情報を使用します。 Azure Resource Manager を呼び出すには、Azure RBAC を使用して、ユーザー割り当て ID のサービス プリンシパルに適切なロールを割り当てます。 Key Vault を呼び出すには、Key Vault 内の特定のシークレットまたは特定のキーにアクセスする権利をコードに与えます。

    注

    この手順は、手順 3. の前に行ってもかまいません。
5. VM 上で実行されているコードは、VM 内からのみアクセスできる Azure Instance Metadata Service ID エンドポイントにトークン (`http://169.254.169.254/metadata/identity/oauth2/token`) を要求できます。

    - リソース パラメーターは、トークンの送信先のサービスを指定します。 Azure Resource Manager に対して認証を行うには、`resource=https://management.azure.com/` を使用します。
    - `client_id` パラメーターは、トークンの要求先の ID を指定します。 この値は、1 つの VM 上に複数のユーザー割り当て ID がある場合に、あいまいさを解消するために必要です。 **クライアント ID** は、マネージド ID の**概要**で確認できます。

        [Image: マネージド ID クライアント ID をコピーする方法を示すスクリーンショット。]
    - Azure Instance Metadata Service のバージョンは、API バージョン パラメーターで指定します。 `api-version=2018-02-01` 以降を使用してください。

        次の例では、CURL を使用してローカル マネージド ID エンドポイントに要求を行い、Azure Instance Metadata サービス用のアクセス トークンを取得する方法を示しています。

        ```bash
        curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fstorage.azure.com%2F&client_id=00001111-aaaa-2222-bbbb-3333cccc4444' -H Metadata:true
        ```
6. ステップ 3. で構成したクライアント ID と証明書を使用して、ステップ 5. で指定したアクセス トークンを要求する呼び出しが Microsoft Entra ID に対して行われます。 Microsoft Entra ID は、JSON Web トークン (JWT) アクセス トークンを返します。
7. Microsoft Entra 認証をサポートするサービスを呼び出すときにアクセス トークンをコードから送信します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-assign-managed-identity-via-azure-policy"} -->
## Azure Policy を使用してマネージド ID を割り当てる (プレビュー) - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-assign-managed-identity-via-azure-policy
- Service: entra-id / managed-identities
- Article date: 2026-05-05
- Summary: マネージド ID を Azure リソースに割り当てるために使用できる Azure Policy のドキュメント。

### Overview

[Azure Policy](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview) は、組織の標準を適用し、大規模なコンプライアンスを評価するのに役立ちます。Azure policyは、コンプライアンス ダッシュボードを通じて、管理者が環境の全体的な状態を評価するのに役立つ集計ビューを提供します。 リソースごと、ポリシーごとの細分性にドリルダウンできます。 既存のリソースの一括修復と新しいリソースの自動修復を使用して、お客様のリソースでコンプライアンスを実現するのにも役立ちます。 Azure Policy の一般的なユース ケースには、以下のようなガバナンスの実装が含まれます。

- リソースの整合性
- 規制に対するコンプライアンス
- セキュリティ
- コスト
- 管理

これらの一般的なユース ケース用のポリシー定義は、使用を開始できるように Azure 環境に既に用意されています。

Azure 監視エージェントには、監視対象の Azure 仮想マシン (VM) で[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) が必要です。 このドキュメントでは、これらのシナリオに必要なマネージド ID を VM に大規模に割り当てるのに役立つ、Microsoft によって提供される組み込み Azure Policy の動作について説明します。

システム割り当てマネージド ID を使用することは可能ですが、大規模に使用すると (たとえば、サブスクリプション内のすべての VM に対して)、Microsoft Entra IDで多数の ID が作成 (および削除) されます。 この ID のチャーンを回避するには、ユーザー割り当てマネージド ID を使用します。 これらは 1 回作成し、複数の VM 間で共有できます。

### ポリシーの定義と詳細

- [Virtual Machines のポリシー](https://portal.azure.com/#blade/Microsoft_Azure_Policy/PolicyDetailBlade/definitionId/%2Fproviders%2FMicrosoft.Authorization%2FpolicyDefinitions%2Fd367bd60-64ca-4364-98ea-276775bddd94)
- [Virtual Machine Scale Sets のポリシー](https://portal.azure.com/#blade/Microsoft_Azure_Policy/PolicyDetailBlade/definitionId/%2Fproviders%2FMicrosoft.Authorization%2FpolicyDefinitions%2F516187d4-ef64-4a1b-ad6b-a7348502976c)

実行されると、ポリシーは次のアクションを実行します。

1. サブスクリプションに新しい組み込みユーザー割り当てのマネージド ID を作成します（存在しない場合）。 ID は、ポリシーのスコープ内にある VM に基づいて、各Azure リージョンに作成されます。
2. 誤って削除されないように、ユーザー割り当てマネージド ID をロックします。
3. ポリシーのスコープ内にある VM に基づいて、サブスクリプションとリージョンの仮想マシンに組み込みのユーザー割り当てマネージド ID を割り当てます。

Note

仮想マシンに既にユーザー割り当てマネージド ID が 1 つだけ割り当てられている場合、その VM への組み込み ID の割り当てはスキップされます。 この動作により、ポリシーの割り当てによって、[Azure インスタンス メタデータ サービス (IMDS)](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-faq#what-identity-will-imds-default-to-if-i-dont-specify-the-identity-in-the-request) でのトークン エンドポイントの既定の動作に依存するアプリケーションが中断されないようにします。

このポリシーを使用するシナリオは次の 2 つです。

- ポリシーで組み込みのユーザー割り当てマネージド ID を作成して使用できるようにします。
- 独自のユーザー割り当てマネージド ID を使用する。

ポリシーは、次の入力パラメーターを受け取ります。

- **Bring-Your-Own-UAMI?**- 新しいユーザー割り当てマネージド ID が存在しない場合、ポリシーで新しいユーザー割り当てマネージド ID を作成する必要がありますか?
    - true に設定する場合は、次のように指定する必要があります。
        - マネージド ID の名前。
        - マネージド ID を含むリソース グループ。
    - false に設定した場合、これ以上入力は必要ありません。
        - このポリシーは、`built-in-identity` というリソース グループに、`built-in-identity-rg`という必要なユーザー割り当てマネージド ID を作成します。
- **Restrict-Bring-Your-Own-UAMI-To-Subscription?** - **Bring-Your-Own-UAMI**パラメーターが true に設定されている場合、ポリシーで一元化されたユーザー割り当てマネージド ID を使用するか、サブスクリプションごとに ID を使用する必要がありますか?
    - true に設定すると、これ以上入力は必要ありません。
        - このポリシーでは、サブスクリプションごとにユーザー割り当てマネージド ID が使用されます。
    - false に設定すると、ポリシーは、ポリシー割り当ての対象となるすべてのサブスクリプションに適用される 1 つの一元化されたユーザー割り当てマネージド ID を使用します。 ユーザーは次のものを指定する必要があります。
        - ユーザー割り当てマネージド ID のリソース ID

### ポリシーの使用

#### ポリシー割り当ての作成

ポリシー定義は、管理グループ、サブスクリプション、または特定のリソース グループで、Azureのさまざまなスコープに割り当てることができます。 ポリシーは継続的に適用する必要があるため、割り当て操作ではポリシー割り当てオブジェクトに関連付けられたマネージド ID が使用されます。 ポリシー割り当てオブジェクトは、システム割り当てマネージド ID とユーザー割り当てマネージド ID の両方をサポートします。 たとえば、PolicyAssignmentMI というユーザー割り当てマネージド ID を作成できます。 組み込みポリシーは、各サブスクリプションと、ポリシー割り当てのスコープ内にあるリソースを含む各リージョンに、ユーザー割り当てマネージド ID を作成します。 ポリシーによって作成されたユーザー割り当てマネージド ID のリソース ID 形式は次のとおりです。

>
> /subscriptions/your-subscription-id/resourceGroups/built-in-identity-rg/providers/Microsoft.ManagedIdentity/userAssignedIdentities/built-in-identity-{location}

例えば次が挙げられます。

>
> /subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/resourceGroups/built-in-identity-rg/providers/Microsoft.ManagedIdentity/userAssignedIdentities/built-in-identity-eastus

#### 必要な承認

PolicyAssignmentMI マネージド ID が、指定されたスコープ全体に組み込みポリシーを割り当てることができるようにするには、Azure RBAC (Azure ロールベースのアクセス制御) ロールの割り当てとして表される次のアクセス許可が必要です。

| プリンシパル | ロール/アクション | Scope | 目的 |
| --- | --- | --- | --- |
| PolicyAssignmentMI | アイデンティティ管理オペレーター | /subscription/subscription-id/resourceGroups/built-in-identity  OR ユーザー割り当てマネージドIDを持ち込む | 組み込み ID を VM に割り当てるために必要です。 |
| PolicyAssignmentMI | Contributor | /subscription/subscription-id&gt; | サブスクリプションに組み込みのマネージド ID を保持するリソース グループを作成するために必要です。 |
| PolicyAssignmentMI | マネージド ID 共同作成者 | /subscription/subscription-id/resourceGroups/built-in-identity | ユーザー割り当てマネージド ID を新規作成するために必要です。 |
| PolicyAssignmentMI | ユーザーアクセス管理者 | /subscription/subscription-id/resourceGroups/built-in-identity  OR ユーザー割り当て管理 ID を持参する | ポリシーによって作成されたユーザー割り当てマネージド ID にロックを設定するために必要です。 |

ポリシー割り当てオブジェクトには事前にこのアクセス許可が必要であるため、このシナリオでは PolicyAssignmentMI をシステム割り当てマネージド ID にすることはできません。 ポリシー割り当てタスクを実行するユーザーは、前の表に示したロールの割り当てを使用して PolicyAssignmentMI を事前認証する必要があります。

結果として必要な最小特権ロールは、サブスクリプション スコープで`Contributor`です。

### 既知の問題

VM に割り当てられた ID を変更する別のデプロイで競合状態が発生すると、予期しない結果が発生する可能性があります。

特定の競合状態では、2 つ以上の並列デプロイで同じ仮想マシンが更新され、すべての仮想マシンの ID 構成が変更されると、予期されるすべての ID がマシンに割り当てられない可能性があります。

たとえば、このドキュメントのポリシーでは VM のマネージド ID を更新し、別のプロセスでマネージド ID セクションが同時に変更される場合があります。 その場合、VM では、予想されるすべての ID が適切に割り当てられるとは限りません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities"} -->
## Azure 仮想マシン (VM) 上でマネージド ID を構成する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities
- Service: entra-id / managed-identities
- Article date: 2025-01-16
- Summary: Azure VM 上で、システムおよびユーザー割り当てマネージド ID を構成するための詳細な手順。

Azure リソースのマネージド ID は、Microsoft Entra ID で自動的に管理される ID を Azure サービスに提供します。 この ID を使用すると、コード内に資格情報を記述することなく、Microsoft Entra の認証をサポートする任意のサービスに対して認証を行うことができます。

Azure Policy の定義と詳細については、「[Azure Policy を使用してマネージド ID を割り当てる (プレビュー)](https://portal.azure.com/#blade/Microsoft_Azure_Policy/PolicyDetailBlade/definitionId/%2Fproviders%2FMicrosoft.Authorization%2FpolicyDefinitions%2Fd367bd60-64ca-4364-98ea-276775bddd94)」を参照してください。

::: zone pivot="qs-configure-portal-windows-vm"

この記事では、Azure portal を使用して、システムとユーザーによって Azure 仮想マシン (VM) に割り当てられたマネージド ID を有効および無効にする方法について説明します。

### 前提条件

- Azure リソースのマネージド ID に関する情報が必要な場合は、[概要セクション](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)をご覧ください。
- まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

### システム割り当て管理 ID

このセクションでは、Azure portal を使用して VM のシステム割り当てマネージド ID を有効および無効にする方法について説明します。

#### VM の作成中にシステム割り当てマネージド ID を有効にする

VM の作成中に VM でシステム割り当てマネージド ID を有効にするには、アカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

[Windows 仮想マシン](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/quick-create-portal#create-virtual-machine)または [Linux 仮想マシン](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-portal#create-virtual-machine)を作成する場合は、[**管理**] タブを選択します。

[ **ID** ] セクションで、 [ **システム割り当てマネージド ID を有効にする** ] チェック ボックスをオンにします。

[Image: VM の作成中にシステム割り当て ID を有効にする方法を示すスクリーンショット。]

#### 既存の VM でシステム割り当てマネージド ID を有効にする

もともとシステム割り当てマネージド ID をプロビジョニングされていなかった VM でシステム割り当てマネージド ID を有効にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. VM が含まれる Azure サブスクリプションに関連付けられているアカウントを使用して、[Azure Portal](https://portal.azure.com) にサインインします。
2. 目的の仮想マシンに移動し、[ **セキュリティ** ] セクションで **[ID]** を選択します。
3. **[システム割り当て済み]** にある **[状態]** で **[オン]** を選択して、 **[保存]** をクリックします。

    [Image: [システム割り当て済み] ステータスが [オン] に設定された [ID] ページを示すスクリーンショット。]

#### VM からシステム割り当てマネージド ID を削除する

VM からシステム割り当てマネージド ID を削除するには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

仮想マシンでシステム割り当てマネージド ID が不要になった場合:

1. VM が含まれる Azure サブスクリプションに関連付けられているアカウントを使用して、[Azure Portal](https://portal.azure.com) にサインインします。
2. 目的の仮想マシンに移動し、[ **セキュリティ** ] セクションで **[ID]** を選択します。
3. **[システム割り当て済み]** にある **[状態]** で **[オフ]** を選択して、 **[保存]** をクリックします。

    [Image: 構成ページのスクリーンショット。]

### ユーザーによって割り当てられたマネージド ID

このセクションでは、Azure portal を使用して、VM との間でユーザー割り当てマネージド ID を追加および削除する方法について説明します。

#### VM の作成中にユーザー割り当て ID を割り当てる

ユーザー割り当て ID を VM に割り当てるには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールと[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

現在 Azure portal では、VM 作成中のユーザー割り当てマネージド ID の割り当てはサポートされていません。 最初に [Windows 仮想マシン](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/quick-create-portal#create-virtual-machine) または [Linux 仮想マシン](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-portal#create-virtual-machine)を作成してから、ユーザー割り当てマネージド ID を VM に割り当てます。

#### ユーザー割り当てマネージド ID を既存の VM に割り当てる

ユーザー割り当て ID を VM に割り当てるには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールと[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. VM が含まれる Azure サブスクリプションに関連付けられているアカウントを使用して、[Azure Portal](https://portal.azure.com) にサインインします。
2. 目的の VM に移動し、[**Security**&gt;**Identity]、[** **User assigned**]、[**+Add**] の順にクリックします。 VM に追加したいユーザー割り当て ID をクリックして、 **[追加]** をクリックします。
3. 以前に作成した [ユーザー割り当てマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities#create-a-user-assigned-managed-identity) を一覧から選択します。

    [Image: [ユーザー割り当て] が選択され、[追加] ボタンが強調表示されている [ID] ページのスクリーンショット。]

#### VM からユーザー割り当てマネージド ID を削除する

VM からユーザー割り当て ID を削除するには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

VM が含まれる Azure サブスクリプションに関連付けられているアカウントを使用して、[Azure Portal](https://portal.azure.com) にサインインします。

目的の VM に移動し、 **[Security** **&gt;Identity**]、[**ユーザー割り当て**] の順に選択し、削除するユーザー割り当てマネージド ID の名前を選択し、 **[削除**] をクリックします (確認ウィンドウで **[はい**] をクリックします)。

[Image: VM からユーザー割り当てマネージド ID を削除する方法を示すスクリーンショット。]

::: zone-end

::: zone pivot="qs-configure-cli-windows-vm"

この記事では、Azure CLI を使用して、Azure VM 上に次の Azure リソースのマネージド ID 操作を実行する方法について説明します。

- Azure VM 上でシステム割り当てマネージド ID を有効および無効にする
- Azure VM 上でユーザー割り当てマネージド ID を追加および削除する

まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

### 前提条件

- Azure リソースのマネージド ID について不明な場合は、「[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください。 システム割り当てとユーザー割り当ての両方の種類のマネージド ID の詳細については、「[マネージド ID の種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)」をご覧ください。

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「[Azure Cloud Shell の概要](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI リファレンス コマンドをローカルで実行する場合、Azure CLI を[インストール](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)します。 Windows または macOS で実行している場合は、Docker コンテナーで Azure CLI を実行することを検討してください。 詳細については、「[Docker コンテナーで Azure CLI を実行する方法](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)」を参照してください。

    - ローカル インストールを使用する場合は、[az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用して Azure CLI にサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「 [Azure CLI を使用した Azure への認証](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - 初回使用時にインストールを求められたら、Azure CLI 拡張機能をインストールします。 拡張機能の詳細については、「[Azure CLI で拡張機能を使用および管理する](https://learn.microsoft.com/ja-jp/cli/azure/azure-cli-extensions-overview)」を参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行し、インストールされているバージョンおよび依存ライブラリを検索します。 最新バージョンにアップグレードするには、[az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

### システム割り当て管理 ID

このセクションでは、Azure CLI を使用して、Azure VM 上でシステム割り当てマネージド ID を有効および無効にする方法について説明します。

#### Azure VM の作成中にシステム割り当てマネージド ID を有効にする

システム割り当てマネージド ID を有効にして Azure VM を作成するには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. [az group create](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview#terminology) を使用して、VM とその関連リソースの管理およびデプロイ用に[リソース グループ](https://learn.microsoft.com/ja-jp/cli/azure/group/#az-group-create)を作成します。 代わりに使用するリソース グループが既にある場合は、この手順をスキップできます。

    ```azurecli
    az group create --name myResourceGroup --location westus
    ```
2. [az vm create](https://learn.microsoft.com/ja-jp/cli/azure/vm/#az-vm-create) を使用して VM を作成します。 次の例では、 パラメーターの要求どおりに、`--assign-identity` と `--role` を指定して、システム割り当てマネージド ID を持つ `--scope` という名前の VM を作成します。 `--admin-username` および `--admin-password` パラメーターは、仮想マシンのサインイン用の管理ユーザー名とパスワードを指定します。 これらの値は、お使いの環境に合わせて更新してください。

    ```azurecli
    az vm create --resource-group myResourceGroup --name myVM --image win2016datacenter --generate-ssh-keys --assign-identity --role contributor --scope /Subscriptions/mySubscriptionId/resourceGroups/myResourceGroup --admin-username azureuser --admin-password myPassword12
    ```

#### 既存の Azure VM 上でシステム割り当てマネージド ID を有効にする

VM でシステム割り当てマネージド ID を有効にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. ローカルのコンソールで Azure CLI を使用している場合は、最初に [az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) を使用して Azure にサインインします。 目的の VM が含まれる Azure サブスクリプションに関連付けられたアカウントを使用します。

    ```azurecli
    az login
    ```
2. [az vm identity assign](https://learn.microsoft.com/ja-jp/cli/azure/vm/identity) と `identity assign` コマンドを使用して、既存の VM に対するシステム割り当て ID を有効にします。

    ```azurecli
    az vm identity assign -g myResourceGroup -n myVm
    ```

#### Azure VM でシステム割り当て ID を無効にする

VM でシステム割り当てマネージド ID を無効にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

システム割り当て ID は不要になったが、ユーザー割り当て ID はまだ必要な仮想マシンがある場合は、次のコマンドを使用します。

```azurecli
az vm update -n myVM -g myResourceGroup --set identity.type='UserAssigned' 
```

システム割り当て ID は不要になった、ユーザー割り当て ID がない仮想マシンがある場合は、次のコマンドを使用します。

注意

`none` の値は、大文字と小文字が区別されます。 小文字にする必要があります。

```azurecli
az vm update -n myVM -g myResourceGroup --set identity.type="none"
```

### ユーザーによって割り当てられたマネージド ID

このセクションでは、Azure CLI を使用して、Azure VM に対してユーザー割り当てマネージド ID を追加および削除する方法について説明します。 VM とは異なるリソース グループにユーザー割り当てマネージド ID を作成する場合、 マネージド ID の URL を使用して、それを VM に割り当てる必要があります。 次に例を示します。

`--identities "/subscriptions/<SUBID>/resourcegroups/<RESROURCEGROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER_ASSIGNED_ID_NAME>"`

#### Azure VM の作成中にユーザー割り当てマネージド ID を割り当てる

ユーザー割り当て ID を作成中の VM に割り当てるには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールと[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. 使用するリソース グループが既にある場合は、この手順をスキップできます。 [az group create](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview#terminology) を使用して、ユーザー割り当てマネージド ID の格納と配置を行う[リソース グループ](https://learn.microsoft.com/ja-jp/cli/azure/group#az-group-create)を作成します。 `<RESOURCE GROUP>` と `<LOCATION>` のパラメーターの値は、必ず実際の値に置き換えてください。 :

    ```azurecli
    az group create --name <RESOURCE GROUP> --location <LOCATION>
    ```
2. [az identity create](https://learn.microsoft.com/ja-jp/cli/azure/identity#az-identity-create) を使用して、ユーザー割り当てマネージド ID を作成します。 `-g` パラメーターにはユーザー割り当てマネージド ID を作成するリソース グループを指定し、`-n` パラメーターにはその名前を指定します。

    重要

    ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

    ```azurecli
    az identity create -g myResourceGroup -n myUserAssignedIdentity
    ```

    応答には、次のように、作成されたユーザー割り当てマネージド ID の詳細が含まれています。 ユーザー割り当てマネージド ID に割り当てられたリソース ID 値は、次の手順で使用されます。

    ```json
    {
        "clientId": "00001111-aaaa-2222-bbbb-3333cccc4444",
        "clientSecretUrl": "https://control-westcentralus.identity.azure.net/subscriptions/<SUBSCRIPTON ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<myUserAssignedIdentity>/credentials?tid=5678&oid=9012&aid=aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
        "id": "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>",
        "location": "westcentralus",
        "name": "<USER ASSIGNED IDENTITY NAME>",
        "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
        "resourceGroup": "<RESOURCE GROUP>",
        "tags": {},
        "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
        "type": "Microsoft.ManagedIdentity/userAssignedIdentities"    
    }
    ```
3. [az vm create](https://learn.microsoft.com/ja-jp/cli/azure/vm#az-vm-create) を使用して VM を作成します。 次の例では、`--assign-identity` パラメーターで指定されたとおり、`--role` と `--scope` を指定して、新しいユーザー割り当て ID に関連付けられている VM を作成します。 `<RESOURCE GROUP>`、`<VM NAME>`、`<USER NAME>`、`<PASSWORD>`、`<USER ASSIGNED IDENTITY NAME>`、`<ROLE>`、`<SUBSCRIPTION>` の各パラメーターの値は、必ず実際の値に置き換えてください。

    ```azurecli
    az vm create --resource-group <RESOURCE GROUP> --name <VM NAME> --image <SKU linux image>  --admin-username <USER NAME> --admin-password <PASSWORD> --assign-identity <USER ASSIGNED IDENTITY NAME> --role <ROLE> --scope <SUBSCRIPTION> 
    ```

#### ユーザー割り当てマネージド ID を既存の Azure VM に割り当てる

ユーザー割り当て ID を VM に割り当てるには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールと[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. [az identity create](https://learn.microsoft.com/ja-jp/cli/azure/identity#az-identity-create) を使用してユーザー割り当て ID を作成します。 `-g` パラメーターにはユーザー割り当て ID を作成するリソース グループを指定し、`-n` パラメーターにはその名前を指定します。 `<RESOURCE GROUP>` と `<USER ASSIGNED IDENTITY NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。

    重要

    名前に特殊文字 (アンダースコアなど) が含まれるユーザー割り当てマネージド ID の作成は現在サポートされていません。 英数字を使用してください。 アップデートは後ほどご確認ください。 詳しくは、「[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)」をご覧ください。

    ```azurecli
    az identity create -g <RESOURCE GROUP> -n <USER ASSIGNED IDENTITY NAME>
    ```

    応答には、次のように、作成されたユーザー割り当てマネージド ID の詳細が含まれています。

    ```json
    {
      "clientId": "00001111-aaaa-2222-bbbb-3333cccc4444",
      "clientSecretUrl": "https://control-westcentralus.identity.azure.net/subscriptions/<SUBSCRIPTON ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>/credentials?tid=5678&oid=9012&aid=aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
      "id": "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>",
      "location": "westcentralus",
      "name": "<USER ASSIGNED IDENTITY NAME>",
      "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
      "resourceGroup": "<RESOURCE GROUP>",
      "tags": {},
      "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
      "type": "Microsoft.ManagedIdentity/userAssignedIdentities"    
    }
    ```
2. [az vm identity assign](https://learn.microsoft.com/ja-jp/cli/azure/vm) を使用して、ユーザー割り当て ID を VM に割り当てます。 `<RESOURCE GROUP>` と `<VM NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。 `<USER ASSIGNED IDENTITY NAME>` は、前の手順で作成されたユーザー割り当てマネージド ID のリソース `name` プロパティです。 VMとは異なるRGにユーザー割り当てマネージドIDを作成した場合、 マネージド ID の URL を使用する必要があります。

    ```azurecli
    az vm identity assign -g <RESOURCE GROUP> -n <VM NAME> --identities <USER ASSIGNED IDENTITY>
    ```

#### Azure VM からユーザー割り当てのマネージド ID を削除する

VM からユーザー割り当ての ID を削除にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。

仮想マシンに割り当てられている唯一のユーザー割り当てマネージド ID の場合は、ID の種類の値から `UserAssigned` が削除されます。 `<RESOURCE GROUP>` と `<VM NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。 `<USER ASSIGNED IDENTITY>` はユーザー割り当て ID の `name` プロパティになります。これは、`az vm identity show` を使用して、仮想マシンの ID セクションで見つけることができます。

```azurecli
az vm identity remove -g <RESOURCE GROUP> -n <VM NAME> --identities <USER ASSIGNED IDENTITY>
```

VM にシステム割り当てマネージド ID がないときに、ユーザー割り当て ID をすべて削除する場合は、次のコマンドを使用します。

注意

`none` の値は、大文字と小文字が区別されます。 小文字にする必要があります。

```azurecli
az vm update -n myVM -g myResourceGroup --set identity.type="none" identity.userAssignedIdentities=null
```

VM にシステム割り当て ID とユーザー割り当て ID の両方がある場合は、システム割り当て ID のみを使用するように切り替えることによって、すべてのユーザー割り当て ID を削除できます。 次のコマンドを使用します。

```azurecli
az vm update -n myVM -g myResourceGroup --set identity.type='SystemAssigned' identity.userAssignedIdentities=null 
```

::: zone-end

::: zone pivot="qs-configure-powershell-windows-vm"

この記事では、PowerShell を使用して、Azure VM 上に次の Azure リソースのマネージド ID 操作を実行する方法について説明します。

注意

Azure を操作するには、Azure Az PowerShell モジュールを使用することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「[AzureRM から Az への Azure PowerShell の移行](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)」を参照してください。

### 前提条件

- Azure リソースのマネージド ID に関する情報が必要な場合は、[概要セクション](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)をご覧ください。 **[システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を必ず確認してください**。
- まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。
- サンプル スクリプトを実行するには、次の 2 つのオプションがあります。
    - [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) を使用します。これは、コード ブロックの右上隅にある **[試してみる]** ボタンを使用して開くことができます。
    - 最新バージョンの [Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) をインストールしてスクリプトをローカルで実行した後、`Connect-AzAccount` を使用して Azure にサインインします。

### システム割り当て管理 ID

このセクションでは、Azure PowerShell を使用してシステム割り当てマネージド ID を有効および無効にする方法について説明します。

#### Azure VM の作成中にシステム割り当てマネージド ID を有効にする

システム割り当てマネージド ID を有効にして Azure VM を作成するには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. 次のいずれかの Azure VM クイック スタートを参照して、必要なセクション (「Azure へのサインイン」、「リソース グループの作成」、「ネットワーク グループの作成」、「VM の作成」) のみを実行してください。

    「VM の作成」セクションに到達したときに、[New-AzVMConfig](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/new-azvm) コマンドレットの構文にわずかな変更を加えます。 必ず `-IdentityType SystemAssigned` パラメーターを追加し、システム割り当て ID を有効にして VM のプロビジョニングを行います。次に例を示します。

    ```azurepowershell
    $vmConfig = New-AzVMConfig -VMName myVM -IdentityType SystemAssigned ...
    ```

    - [PowerShell で Windows 仮想マシンを作成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/quick-create-powershell)
    - [PowerShell で Linux 仮想マシンを作成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-powershell)

#### 既存の Azure VM 上でシステム割り当てマネージド ID を有効にする

もともとシステム割り当てマネージド ID をプロビジョニングされていなかった VM でシステム割り当てマネージド ID を有効にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. `Get-AzVM` コマンドレットを使用して VM プロパティを取得します。 システム割り当てマネージド ID を有効にするには、[Update-AzVM](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/update-azvm) コマンドレットで `-IdentityType` スイッチを使用します。

    ```azurepowershell
    $vm = Get-AzVM -ResourceGroupName myResourceGroup -Name myVM
    Update-AzVM -ResourceGroupName myResourceGroup -VM $vm -IdentityType SystemAssigned
    ```

#### VM のシステム割り当て ID をグループに追加する

VM でシステム割り当て ID を有効にした後は、その ID をグループに追加できます。 以降の手順では、VM のシステム割り当て ID をグループに追加します。

1. VM のサービス プリンシパルの `ObjectID` (返された値の `Id` フィールドで指定されています) を取得し、メモします。

    ```azurepowershell
    Get-AzADServicePrincipal -displayname "myVM"
    ```
2. グループの `ObjectID` (返された値の `Id` フィールドで指定されています) を取得し、メモします。

    ```azurepowershell
    Get-AzADGroup -searchstring "myGroup"
    ```
3. VM のサービス プリンシパルをグループに追加します。

    ```azurepowershell
    New-MgGroupMember -GroupId "<Id of group>" -DirectoryObjectId "<Id of VM service principal>" 
    ```

### Azure VM でシステム割り当てマネージド ID を無効にする

VM でシステム割り当てマネージド ID を無効にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

システム割り当てマネージド ID は不要になったが、ユーザー割り当てマネージド ID はまだ必要な仮想マシンがある場合は、次のコマンドレットを使用します。

1. `Get-AzVM` コマンドレットを使用して VM プロパティを取得し、`-IdentityType` パラメーターを `UserAssigned` に設定します。

    ```azurepowershell
    $vm = Get-AzVM -ResourceGroupName myResourceGroup -Name myVM
    Update-AzVm -ResourceGroupName myResourceGroup -VM $vm -IdentityType "UserAssigned" -IdentityID "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>..."
    ```

システム割り当てマネージド ID が不要になり、ユーザー割り当てマネージド ID がない仮想マシンがある場合は、次のコマンドを使用します。

```azurepowershell
$vm = Get-AzVM -ResourceGroupName myResourceGroup -Name myVM
Update-AzVm -ResourceGroupName myResourceGroup -VM $vm -IdentityType None
```

### ユーザーによって割り当てられたマネージド ID

このセクションでは、Azure PowerShell を使用して、VM との間でユーザー割り当てマネージド ID を追加および削除する方法について説明します。

#### 作成中にユーザー割り当てマネージド ID を VM に割り当てる

ユーザー割り当て ID を VM に割り当てるには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールと[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. 次のいずれかの Azure VM クイック スタートを参照して、必要なセクション (「Azure へのサインイン」、「リソース グループの作成」、「ネットワーク グループの作成」、「VM の作成」) のみを実行してください。

    「VM の作成」セクションに到達したときに、[`New-AzVMConfig`](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/new-azvm) コマンドレットの構文にわずかな変更を加えます。 `-IdentityType UserAssigned` および `-IdentityID` パラメーターを追加し、ユーザー割り当て ID を使用して VM のプロビジョニングを行います。 `<VM NAME>`、`<SUBSCRIPTION ID>`、`<RESOURCE GROUP>`、および `<USER ASSIGNED IDENTITY NAME>` を独自の値に置き換えます。 次に例を示します。

    ```azurepowershell
    $vmConfig = New-AzVMConfig -VMName <VM NAME> -IdentityType UserAssigned -IdentityID "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>..."
    ```

    - [PowerShell で Windows 仮想マシンを作成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/quick-create-powershell)
    - [PowerShell で Linux 仮想マシンを作成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-powershell)

#### ユーザー割り当てマネージド ID を既存の Azure VM に割り当てる

ユーザー割り当て ID を VM に割り当てるには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールと[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. [New-AzUserAssignedIdentity](https://learn.microsoft.com/ja-jp/powershell/module/az.managedserviceidentity/new-azuserassignedidentity) コマンドレットを使用して、ユーザー割り当てマネージド ID を作成します。 出力の `Id` は、次の手順で必要になるため書き留めてください。

    重要

    ユーザー割り当てのマネージド ID の作成では、英数字、下線、およびハイフン (0-9、a-z、A-Z、\_、-) 文字のみがサポートされます。 また、VM/VMSS への割り当てを正常に機能させるには、名前の長さを 3 ～ 128 文字に制限する必要があります。 詳しくは、「[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)」をご覧ください。

    ```azurepowershell
    New-AzUserAssignedIdentity -ResourceGroupName <RESOURCEGROUP> -Name <USER ASSIGNED IDENTITY NAME>
    ```
2. `Get-AzVM` コマンドレットを使用して VM プロパティを取得します。 次に、[Update-AzVM](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/update-azvm) コマンドレットで `-IdentityType` および `-IdentityID` スイッチを使用して、ユーザー割り当てマネージド ID を Azure VM に割り当てます。 `-IdentityId` パラメーターの値は、前の手順で書き留めた `Id` です。 `<VM NAME>`、`<SUBSCRIPTION ID>`、`<RESOURCE GROUP>`、および `<USER ASSIGNED IDENTITY NAME>` を独自の値に置き換えます。

    警告

    以前のユーザー割り当てマネージド ID を VM に割り当てておくには、VM オブジェクト (たとえば `Identity`) の `$vm.Identity` プロパティのクエリを実行します。 ユーザー割り当てマネージド ID が返された場合、VM に割り当てる新しいユーザー割り当てマネージド ID とともにこれらを次のコマンドに含めます。

    ```azurepowershell
    $vm = Get-AzVM -ResourceGroupName <RESOURCE GROUP> -Name <VM NAME>
    
    # Get the list of existing identity IDs and then append to it
    $identityIds = $vm.Identity.UserAssignedIdentities.Keys
    $uid = "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>"
    $identityIds = $identityIds + $uid 
    
    # Update the VM with added identity IDs
    Update-AzVM -ResourceGroupName <RESOURCE GROUP> -VM $vm -IdentityType UserAssigned -IdentityID $uid 
    ```

#### Azure VM からユーザー割り当てのマネージド ID を削除する

VM からユーザー割り当ての ID を削除にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。

VM に複数のユーザー割り当てマネージド ID がある場合は、次のコマンドを使用して、最後の ID 以外の ID をすべて削除できます。 `<RESOURCE GROUP>` と `<VM NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。 `<USER ASSIGNED IDENTITY NAME>` はユーザー割り当てマネージド ID の名前プロパティであり、VM 上に残す必要があります。 この情報は、クエリを使用して VM オブジェクトの `Identity` プロパティを検索して検出できます。 例: `$vm.Identity`

```azurepowershell
$vm = Get-AzVm -ResourceGroupName myResourceGroup -Name myVm
Update-AzVm -ResourceGroupName myResourceGroup -VirtualMachine $vm -IdentityType UserAssigned -IdentityID <USER ASSIGNED IDENTITY NAME>
```

VM にシステム割り当てマネージド ID がないときに、その VM からユーザー割り当てマネージド ID をすべて削除する場合は、次のコマンドを使用します。

```azurepowershell
$vm = Get-AzVm -ResourceGroupName myResourceGroup -Name myVm
Update-AzVm -ResourceGroupName myResourceGroup -VM $vm -IdentityType None
```

VM にシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方がある場合は、システム割り当てマネージド ID のみを使用するように切り替えることによって、すべてのユーザー割り当てマネージド ID を削除できます。

```azurepowershell
$vm = Get-AzVm -ResourceGroupName myResourceGroup -Name myVm
Update-AzVm -ResourceGroupName myResourceGroup -VirtualMachine $vm -IdentityType "SystemAssigned"
```

::: zone-end

::: zone pivot="qs-configure-template-windows-vm"

この記事では、Azure Resource Manager デプロイ テンプレートを使用して、Azure VM で Azure リソースのマネージド ID の次の操作を実行する方法を説明します。

### 前提条件

- Azure Resource Manager デプロイ テンプレートを使い慣れていない場合は、[概要セクション](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)をご覧ください。 **[システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を必ず確認してください**。
- まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

### Azure Resource Manager のテンプレート

Azure portal とスクリプトを使う場合と同じように、[Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) テンプレートを使うと、Azure リソース グループによって定義された新しいリソースまたは変更されたリソースをデプロイすることができます。 ローカルとポータル ベースの両方を含むテンプレートの編集やデプロイでは、次のような複数のオプションが使用できます。

- [Azure Marketplace のカスタム テンプレート](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/deploy-portal#deploy-resources-from-custom-template)を使用します。これにより、最初からテンプレートを作成したり、既存の共通テンプレートまたは[クイック スタート テンプレート](https://azure.microsoft.com/resources/templates/)に基づいてテンプレートを作成したりできます。
- [元のデプロイ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/export-template-portal)または[デプロイの現在の状態](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/export-template-portal)からテンプレートをエクスポートすることによって、既存のリソース グループから派生させます。
- ローカルの [JSON エディター (VS Code など)](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/quickstart-create-templates-use-the-portal) を使用してから、PowerShell または CLI を使用してアップロードおよびデプロイします。
- Visual Studio の [Azure リソース グループ プロジェクト](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/create-visual-studio-deployment-project)を使用して、テンプレートを作成およびデプロイします。

選択するオプションにかかわらず、初めてのデプロイ時も再デプロイ時もテンプレートの構文は同じです。 新規または既存の VM での、システムまたはユーザー割り当てマネージド ID の有効化も同様に行われます。 また、既定で Azure Resource Manager はデプロイに対して[増分更新](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/deployment-modes)を行います。

### システム割り当て管理 ID

このセクションでは、Azure Resource Manager テンプレートを使用して、システム割り当てマネージド ID を有効および無効にします。

#### Azure VM の作成時に、または既存の VM でシステム割り当てマネージド ID を有効にする

VM でシステム割り当てマネージド ID を有効にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. Azure にローカルでサインインする場合も、Azure Portal を使用してサインインする場合も、VM が含まれる Azure サブスクリプションに関連付けられているアカウントを使用します。
2. システム割り当てマネージド ID を有効にするには、テンプレートをエディターに読み込み、`Microsoft.Compute/virtualMachines` セクション内で対象の `resources` リソースを探し、`"identity"` プロパティと同じレベルに `"type": "Microsoft.Compute/virtualMachines"` プロパティを追加します。 次の構文を使用します。

    ```json
    "identity": {
        "type": "SystemAssigned"
    },
    ```
3. 完了すると、テンプレートの `resource` セクションに次のセクションが追加され、テンプレートは次のようになります。

    ```json
     "resources": [
         {
             //other resource provider properties...
             "apiVersion": "2018-06-01",
             "type": "Microsoft.Compute/virtualMachines",
             "name": "[variables('vmName')]",
             "location": "[resourceGroup().location]",
             "identity": {
                 "type": "SystemAssigned",
                 }                        
         }
     ]
    ```

#### VM のシステム割り当てマネージド ID にロールを割り当てる

VM でシステム割り当てマネージド ID を有効にしたら、作成先となったリソース グループへの**閲覧者**アクセス権などのロールを付与することができます。 この手順の詳細については、「[Azure Resource Manager テンプレートを使用して Azure でのロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-template)」という記事を参照してください。

#### Azure VM でシステム割り当てマネージド ID を無効にする

VM からシステム割り当てマネージド ID を削除するには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. Azure にローカルでサインインする場合も、Azure Portal を使用してサインインする場合も、VM が含まれる Azure サブスクリプションに関連付けられているアカウントを使用します。
2. テンプレートをエディターに読み込み、`Microsoft.Compute/virtualMachines` セクション内で関心のある `resources` リソースを探します。 システム割り当てマネージド ID のみが割り当てられた VM がある場合は、ID の種類を `None` に変更することで無効にすることができます。

    **Microsoft.Compute/virtualMachines API バージョン 2018-06-01**

    VM にシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方が割り当てられている場合は、ID の種類から `SystemAssigned` を削除し、`UserAssigned` ディクショナリ値と共に `userAssignedIdentities` を保持します。

    **Microsoft.Compute/virtualMachines API バージョン 2018-06-01**

    `apiVersion` が `2017-12-01` であり、VM にシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方が割り当てられている場合は、ID の種類から `SystemAssigned` を削除し、ユーザー割り当てマネージド ID の `UserAssigned` 配列と共に `identityIds` を保持します。

次の例は、ユーザー割り当てマネージド ID が割り当てられていない VM からシステム割り当てマネージド ID を削除する方法を示しています。

```json
{
    "apiVersion": "2018-06-01",
    "type": "Microsoft.Compute/virtualMachines",
    "name": "[parameters('vmName')]",
    "location": "[resourceGroup().location]",
    "identity": {
        "type": "None"
    }
}
```

### ユーザーによって割り当てられたマネージド ID

このセクションでは、Azure Resource Manager テンプレートを使用して、Azure VM にユーザー割り当てマネージド ID を割り当てます。

注意

Azure Resource Manager テンプレートを使用してユーザー割り当てマネージド ID を作成するには、「[Create a user-assigned managed identity (ユーザー割り当てマネージド ID を作成する)](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-arm#create-a-user-assigned-managed-identity)」をご覧ください。

#### Azure VM にユーザー割り当てマネージド ID を割り当てる

ユーザー割り当て ID を VM に割り当てるには、お使いのアカウントに[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. ユーザー割り当てマネージド ID を VM に割り当てるには、`resources` 要素に次のエントリを追加します。 `<USERASSIGNEDIDENTITY>` は、作成したユーザー割り当てマネージド ID の名前に置き換えてください。

    **Microsoft.Compute/virtualMachines API バージョン 2018-06-01**

    `apiVersion` が `2018-06-01` の場合、ユーザー割り当てマネージド ID は `userAssignedIdentities` ディクショナリ形式で格納されます。`<USERASSIGNEDIDENTITYNAME>` 値は、テンプレートの `variables` セクションに定義された変数に格納する必要があります。

    ```json
     {
         "apiVersion": "2018-06-01",
         "type": "Microsoft.Compute/virtualMachines",
         "name": "[variables('vmName')]",
         "location": "[resourceGroup().location]",
         "identity": {
             "type": "userAssigned",
             "userAssignedIdentities": {
                 "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<USERASSIGNEDIDENTITYNAME>'))]": {}
             }
         }
     }
    ```

    **Microsoft.Compute/virtualMachines API バージョン 2017-12-01**

    `apiVersion` が `2017-12-01` の場合、ユーザー割り当てマネージド ID は `identityIds` 配列に格納されます。`<USERASSIGNEDIDENTITYNAME>` 値は、テンプレートの `variables` セクションに定義された変数に格納する必要があります。

    ```json
    {
        "apiVersion": "2017-12-01",
        "type": "Microsoft.Compute/virtualMachines",
        "name": "[variables('vmName')]",
        "location": "[resourceGroup().location]",
        "identity": {
            "type": "userAssigned",
            "identityIds": [
                "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<USERASSIGNEDIDENTITYNAME>'))]"
            ]
        }
    }
    ```
2. 完了すると、テンプレートの `resource` セクションに次のセクションが追加され、テンプレートは次のようになります。

    **Microsoft.Compute/virtualMachines API バージョン 2018-06-01**

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
                    "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<USERASSIGNEDIDENTITYNAME>'))]": {}
                 }
             }
         }
     ] 
    ```

    **Microsoft.Compute/virtualMachines API バージョン 2017-12-01**

    ```json
    "resources": [
         {
             //other resource provider properties...
             "apiVersion": "2017-12-01",
             "type": "Microsoft.Compute/virtualMachines",
             "name": "[variables('vmName')]",
             "location": "[resourceGroup().location]",
             "identity": {
                 "type": "userAssigned",
                 "identityIds": [
                    "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<USERASSIGNEDIDENTITYNAME>'))]"
                 ]
             }
         }
    ]
    ```

#### Azure VM からユーザー割り当てのマネージド ID を削除する

VM からユーザー割り当て ID を削除するには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. Azure にローカルでサインインする場合も、Azure Portal を使用してサインインする場合も、VM が含まれる Azure サブスクリプションに関連付けられているアカウントを使用します。
2. テンプレートをエディターに読み込み、`Microsoft.Compute/virtualMachines` セクション内で関心のある `resources` リソースを探します。 ユーザー割り当てマネージド ID しか存在しない VM がある場合は、ID の種類を `None` に変更することによってそれを無効にすることができます。

    次の例は、システム割り当てマネージド ID が割り当てられていない VM からユーザー割り当てマネージド ID をすべて削除する方法を示しています。

    ```json
     {
       "apiVersion": "2018-06-01",
       "type": "Microsoft.Compute/virtualMachines",
       "name": "[parameters('vmName')]",
       "location": "[resourceGroup().location]",
       "identity": {
           "type": "None"
           },
     }
    ```

    **Microsoft.Compute/virtualMachines API バージョン 2018-06-01**

    VM から 1 つのユーザー割り当てマネージド ID を削除するには、`userAssignedIdentities` ディクショナリからそれを削除します。

    システム割り当てマネージド ID がある場合は、`identity` の下位にある `type` の値としてそれを保持します。

    **Microsoft.Compute/virtualMachines API バージョン 2017-12-01**

    VM から 1 つのユーザー割り当てマネージド ID を削除するには、`identityIds` 配列からそれを削除します。

    システム割り当てマネージド ID がある場合は、`type` 値の `identity` 値でそれを保持します。

::: zone-end

::: zone pivot="qs-configure-rest-vm"

この記事では、Azure Resource Manager REST エンドポイントを呼び出す CURL を使用して、Azure VM で次の Azure リソースのマネージド ID 操作を実行する方法について説明します。

- Azure VM 上でシステム割り当てマネージド ID を有効および無効にする
- Azure VM 上でユーザー割り当てマネージド ID を追加および削除する

まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

### 前提条件

- Azure リソースのマネージド ID について不明な場合は、「[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください。 システム割り当てとユーザー割り当ての両方の種類のマネージド ID の詳細については、「[マネージド ID の種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」をご覧ください。

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「[Azure Cloud Shell の概要](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI リファレンス コマンドをローカルで実行する場合、Azure CLI を[インストール](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)します。 Windows または macOS で実行している場合は、Docker コンテナーで Azure CLI を実行することを検討してください。 詳細については、「[Docker コンテナーで Azure CLI を実行する方法](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)」を参照してください。

    - ローカル インストールを使用する場合は、[az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用して Azure CLI にサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「 [Azure CLI を使用した Azure への認証](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - 初回使用時にインストールを求められたら、Azure CLI 拡張機能をインストールします。 拡張機能の詳細については、「[Azure CLI で拡張機能を使用および管理する](https://learn.microsoft.com/ja-jp/cli/azure/azure-cli-extensions-overview)」を参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行し、インストールされているバージョンおよび依存ライブラリを検索します。 最新バージョンにアップグレードするには、[az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

### システム割り当て管理 ID

このセクションでは、CURL を使用して Azure Resource Manager REST エンドポイントを呼び出し、Azure VM でシステム割り当てマネージド ID を有効および無効にする方法について説明します。

#### Azure VM の作成中にシステム割り当てマネージド ID を有効にする

システム割り当てマネージド ID を有効にして Azure VM を作成するには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. [az group create](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview#terminology) を使用して、VM とその関連リソースの管理およびデプロイ用に[リソース グループ](https://learn.microsoft.com/ja-jp/cli/azure/group/#az-group-create)を作成します。 代わりに使用するリソース グループが既にある場合は、この手順をスキップできます。

    ```azurecli
    az group create --name myResourceGroup --location westus
    ```
2. ご利用の VM の[ネットワーク インターフェイス](https://learn.microsoft.com/ja-jp/cli/azure/network/nic#az-network-nic-create)を作成します。

    ```azurecli
     az network nic create -g myResourceGroup --vnet-name myVnet --subnet mySubnet -n myNic
    ```
3. Bearer アクセス トークンを取得します。このトークンは、Authorization ヘッダーでシステム割り当てマネージド ID を使用して VM を作成する次の手順で使用します。

    ```azurecli
    az account get-access-token
    ```
4. Azure Cloud Shell を使用して、Azure Resource Manager REST エンドポイントを呼び出す CURL を使って VM を作成します。 次の例では、要求本文で値 `"identity":{"type":"SystemAssigned"}` によって識別される、システム割り当てマネージド ID を持つ VM *myVM* を作成します。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PUT -d '{"location":"westus","name":"myVM","identity":{"type":"SystemAssigned"},"properties":{"hardwareProfile":{"vmSize":"Standard_D2_v2"},"storageProfile":{"imageReference":{"sku":"2016-Datacenter","publisher":"MicrosoftWindowsServer","version":"latest","offer":"WindowsServer"},"osDisk":{"caching":"ReadWrite","managedDisk":{"storageAccountType":"StandardSSD_LRS"},"name":"myVM3osdisk","createOption":"FromImage"},"dataDisks":[{"diskSizeGB":1023,"createOption":"Empty","lun":0},{"diskSizeGB":1023,"createOption":"Empty","lun":1}]},"osProfile":{"adminUsername":"azureuser","computerName":"myVM","adminPassword":"<SECURE PASSWORD STRING>"},"networkProfile":{"networkInterfaces":[{"id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/networkInterfaces/myNic","properties":{"primary":true}}]}}}' -H "Content-Type: application/json" -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PUT https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
      {
        "location":"westus",
        "name":"myVM",
        "identity":{
           "type":"SystemAssigned"
        },
        "properties":{
           "hardwareProfile":{
              "vmSize":"Standard_D2_v2"
           },
           "storageProfile":{
              "imageReference":{
                 "sku":"2016-Datacenter",
                 "publisher":"MicrosoftWindowsServer",
                 "version":"latest",
                 "offer":"WindowsServer"
              },
              "osDisk":{
                 "caching":"ReadWrite",
                 "managedDisk":{
                    "storageAccountType":"StandardSSD_LRS"
                 },
                 "name":"myVM3osdisk",
                 "createOption":"FromImage"
              },
              "dataDisks":[
                 {
                    "diskSizeGB":1023,
                    "createOption":"Empty",
                    "lun":0
                 },
                 {
                    "diskSizeGB":1023,
                    "createOption":"Empty",
                    "lun":1
                 }
              ]
           },
           "osProfile":{
              "adminUsername":"azureuser",
              "computerName":"myVM",
              "adminPassword":"myPassword12"
           },
           "networkProfile":{
              "networkInterfaces":[
                 {
                    "id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/networkInterfaces/myNic",
                    "properties":{
                       "primary":true
                    }
                 }
              ]
           }
        }
     }  
    ```

#### 既存の Azure VM においてシステム割り当て ID を有効にする

もともとシステム割り当てマネージド ID をプロビジョニングされていなかった VM でシステム割り当てマネージド ID を有効にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. Bearer アクセス トークンを取得します。このトークンは、Authorization ヘッダーでシステム割り当てマネージド ID を使用して VM を作成する次の手順で使用します。

    ```azurecli
    az account get-access-token
    ```
2. お使いの VM 名*myVM*に対して、要求本文で`{"identity":{"type":"SystemAssigned"}`として識別されるシステム割り当てマネージドIDを有効にするには、以下の CURL コマンドを使用して Azure Resource Manager REST エンドポイントを呼び出します。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    重要

    VM に割り当てられている既存のユーザー割り当てマネージド ID を削除しないようにするには、次の CURL コマンドを使用してユーザー割り当てマネージド ID を一覧表示する必要があります。`curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachines/<VM NAME>?api-version=2018-06-01' -H "Authorization: Bearer <ACCESS TOKEN>"` 応答の `identity` 値で識別される、ユーザー割り当てマネージド ID が VM に割り当てられている場合は、VM でシステム割り当てマネージド ID を有効にしている間、ユーザー割り当てマネージド ID を保持する方法を示す手順 3 に進みます。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned"}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {  
        "identity":{  
           "type":"SystemAssigned"
        }
     }
    ```
3. 既存のユーザー割り当てマネージド ID を持つ VM でシステム割り当てマネージド ID を有効にするには、`SystemAssigned` を `type` 値に追加する必要があります。

    たとえば、VM にユーザー割り当てマネージド ID `ID1` と `ID2` が割り当てられている状態で、VM にシステム割り当てマネージド ID を追加する場合は、次の CURL 呼び出しを使用します。 `<ACCESS TOKEN>` と `<SUBSCRIPTION ID>` は、ご利用の環境に適した値に置き換えます。

    API バージョン `2018-06-01` では、ユーザー割り当てマネージド ID が `userAssignedIdentities` 値に配列形式で保存されていましたが、API バージョン `identityIds` では `2017-12-01` 値にディクショナリ形式で保存されます。

    **API バージョン 2018-06-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned, UserAssigned", "userAssignedIdentities":{"/subscriptions/<<SUBSCRIPTION ID>>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{},"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":{}}}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {  
        "identity":{  
           "type":"SystemAssigned, UserAssigned",
           "userAssignedIdentities":{  
              "/subscriptions/<<SUBSCRIPTION ID>>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{  
    
              },
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":{  
    
              }
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01' -X PATCH -d '{"identity":{"type":"SystemAssigned, UserAssigned", "identityIds":["/subscriptions/<<SUBSCRIPTION ID>>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1","/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2"]}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | リクエストヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {  
        "identity":{  
           "type":"SystemAssigned, UserAssigned",
           "identityIds":[  
              "/subscriptions/<<SUBSCRIPTION ID>>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1",
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2"
           ]
        }
     }
    ```

#### Azure VM でシステム割り当てマネージド ID を無効にする

VM でシステム割り当てマネージド ID を無効にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. Bearer アクセス トークンを取得します。このトークンは、Authorization ヘッダーでシステム割り当てマネージド ID を使用して VM を作成する次の手順で使用します。

    ```azurecli
    az account get-access-token
    ```
2. CURL を使用して Azure Resource Manager REST エンドポイントを呼び出し、システム割り当てマネージド ID を無効にして VM を更新します。 次の例では、VM 名 *myVM* の VM から、要求本文の値 `{"identity":{"type":"None"}}` によって識別されるシステム割り当てのマネージド ID を無効にします。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    重要

    VM に割り当てられている既存のユーザー割り当てマネージド ID を削除しないようにするには、次の CURL コマンドを使用してユーザー割り当てマネージド ID を一覧表示する必要があります。`curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachines/<VM NAME>?api-version=2018-06-01' -H "Authorization: Bearer <ACCESS TOKEN>"` 応答の `identity` 値で識別される、ユーザー割り当てマネージド ID が VM に割り当てられている場合は、VM でシステム割り当てマネージド ID を無効にしている間、ユーザー割り当てマネージド ID を保持する方法を示す手順 3 に進みます。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"None"}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 요구 헤더 | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {  
        "identity":{  
           "type":"None"
        }
     }
    ```

    仮想マシンにユーザー割り当てのマネージド ID がある場合で、システム割り当てのマネージド ID を削除したいときは、API バージョン 2018-06-01 を使用するときに、`SystemAssigned` を `{"identity":{"type:" "}}` 値から削除し、`UserAssigned` 値と `userAssignedIdentities` ディクショナリ値をそのままにしておきます。 **API バージョン 2017-12-01** またはそれ以前を使用している場合は、`identityIds` 配列を維持します。

### ユーザーによって割り当てられたマネージド ID

このセクションでは、CURL を使用して Azure Resource Manager REST エンドポイントを呼び出し、Azure VM でユーザー割り当てマネージド ID を追加および削除する方法について説明します。

#### Azure VM の作成中にユーザー割り当てマネージド ID を割り当てる

ユーザー割り当て ID を VM に割り当てるには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールと[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. Bearer アクセス トークンを取得します。このトークンは、Authorization ヘッダーでシステム割り当てマネージド ID を使用して VM を作成する次の手順で使用します。

    ```azurecli
    az account get-access-token
    ```
2. ご利用の VM の[ネットワーク インターフェイス](https://learn.microsoft.com/ja-jp/cli/azure/network/nic#az-network-nic-create)を作成します。

    ```azurecli
     az network nic create -g myResourceGroup --vnet-name myVnet --subnet mySubnet -n myNic
    ```
3. Bearer アクセス トークンを取得します。このトークンは、Authorization ヘッダーでシステム割り当てマネージド ID を使用して VM を作成する次の手順で使用します。

    ```azurecli
    az account get-access-token
    ```
4. 次のセクション「[ユーザー割り当てマネージド ID を作成する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-rest#create-a-user-assigned-managed-identity)」の手順を使用して、ユーザー割り当てマネージド ID を作成します。
5. CURL を使用して Azure Resource Manager REST エンドポイントを呼び出し、VM を作成します。 次の例では、リソース グループ *myResourceGroup* にユーザー割り当てマネージド ID `ID1` を使用し、`"identity":{"type":"UserAssigned"}` で指定された値によって要求本文で識別される *myVM* という VM を作成します。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    **API バージョン 2018-06-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PUT -d '{"location":"westus","name":"myVM","identity":{"type":"UserAssigned","identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"]},"properties":{"hardwareProfile":{"vmSize":"Standard_D2_v2"},"storageProfile":{"imageReference":{"sku":"2016-Datacenter","publisher":"MicrosoftWindowsServer","version":"latest","offer":"WindowsServer"},"osDisk":{"caching":"ReadWrite","managedDisk":{"storageAccountType":"StandardSSD_LRS"},"name":"myVM3osdisk","createOption":"FromImage"},"dataDisks":[{"diskSizeGB":1023,"createOption":"Empty","lun":0},{"diskSizeGB":1023,"createOption":"Empty","lun":1}]},"osProfile":{"adminUsername":"azureuser","computerName":"myVM","adminPassword":"myPassword12"},"networkProfile":{"networkInterfaces":[{"id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/networkInterfaces/myNic","properties":{"primary":true}}]}}}' -H "Content-Type: application/json" -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PUT https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {  
        "location":"westus",
        "name":"myVM",
        "identity":{  
           "type":"UserAssigned",
           "identityIds":[  
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"
           ]
        },
        "properties":{  
           "hardwareProfile":{  
              "vmSize":"Standard_D2_v2"
           },
           "storageProfile":{  
              "imageReference":{  
                 "sku":"2016-Datacenter",
                 "publisher":"MicrosoftWindowsServer",
                 "version":"latest",
                 "offer":"WindowsServer"
              },
              "osDisk":{  
                 "caching":"ReadWrite",
                 "managedDisk":{  
                    "storageAccountType":"StandardSSD_LRS"
                 },
                 "name":"myVM3osdisk",
                 "createOption":"FromImage"
              },
              "dataDisks":[  
                 {  
                    "diskSizeGB":1023,
                    "createOption":"Empty",
                    "lun":0
                 },
                 {  
                    "diskSizeGB":1023,
                    "createOption":"Empty",
                    "lun":1
                 }
              ]
           },
           "osProfile":{  
              "adminUsername":"azureuser",
              "computerName":"myVM",
              "adminPassword":"myPassword12"
           },
           "networkProfile":{  
              "networkInterfaces":[  
                 {  
                    "id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/networkInterfaces/myNic",
                    "properties":{  
                       "primary":true
                    }
                 }
              ]
           }
        }
     }
    
    ```

    **API バージョン 2017-12-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01' -X PUT -d '{"location":"westus","name":"myVM","identity":{"type":"UserAssigned","identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"]},"properties":{"hardwareProfile":{"vmSize":"Standard_D2_v2"},"storageProfile":{"imageReference":{"sku":"2016-Datacenter","publisher":"MicrosoftWindowsServer","version":"latest","offer":"WindowsServer"},"osDisk":{"caching":"ReadWrite","managedDisk":{"storageAccountType":"StandardSSD_LRS"},"name":"myVM3osdisk","createOption":"FromImage"},"dataDisks":[{"diskSizeGB":1023,"createOption":"Empty","lun":0},{"diskSizeGB":1023,"createOption":"Empty","lun":1}]},"osProfile":{"adminUsername":"azureuser","computerName":"myVM","adminPassword":"myPassword12"},"networkProfile":{"networkInterfaces":[{"id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/networkInterfaces/myNic","properties":{"primary":true}}]}}}' -H "Content-Type: application/json" -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PUT https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {
        "location":"westus",
        "name":"myVM",
        "identity":{
           "type":"UserAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"
           ]
        },
        "properties":{
           "hardwareProfile":{
              "vmSize":"Standard_D2_v2"
           },
           "storageProfile":{
              "imageReference":{
                 "sku":"2016-Datacenter",
                 "publisher":"MicrosoftWindowsServer",
                 "version":"latest",
                 "offer":"WindowsServer"
              },
              "osDisk":{
                 "caching":"ReadWrite",
                 "managedDisk":{
                    "storageAccountType":"StandardSSD_LRS"
                 },
                 "name":"myVM3osdisk",
                 "createOption":"FromImage"
              },
              "dataDisks":[
                 {
                    "diskSizeGB":1023,
                    "createOption":"Empty",
                    "lun":0
                 },
                 {
                    "diskSizeGB":1023,
                    "createOption":"Empty",
                    "lun":1
                 }
              ]
           },
           "osProfile":{
              "adminUsername":"azureuser",
              "computerName":"myVM",
              "adminPassword":"myPassword12"
           },
           "networkProfile":{
              "networkInterfaces":[
                 {
                    "id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/networkInterfaces/myNic",
                    "properties":{
                       "primary":true
                    }
                 }
              ]
           }
        }
     }
    ```

#### ユーザー割り当てマネージド ID を既存の Azure VM に割り当てる

ユーザー割り当て ID を VM に割り当てるには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールと[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロールの割り当てが必要です。 その他の Microsoft Entra ディレクトリ ロールを割り当てる必要はありません。

1. Bearer アクセス トークンを取得します。このトークンは、Authorization ヘッダーでシステム割り当てマネージド ID を使用して VM を作成する次の手順で使用します。

    ```azurecli
    az account get-access-token
    ```
2. 「[Create a user assigned managed identity](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-rest#create-a-user-assigned-managed-identity)」(ユーザー割り当てマネージド ID を作成する) に示されている手順を使用して、ユーザー割り当てマネージド ID を作成します。
3. VM に割り当てられている既存のユーザーまたはシステム割り当てマネージド ID を削除しないようにするには、次の CURL コマンドを使用して、VM に割り当てられている ID の種類を一覧表示する必要があります。 仮想マシン スケール セットにマネージド ID が割り当てられている場合、`identity` 値に一覧表示されます。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachines/<VM NAME>?api-version=2018-06-01' -H "Authorization: Bearer <ACCESS TOKEN>" 
    ```

    ```HTTP
    GET https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachines/<VM NAME>?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    応答の `identity` 値で識別される、ユーザーまたはシステム割り当てマネージド ID が VM に割り当てられている場合は、VM でユーザー割り当てマネージド ID を追加しながら、システム割り当てマネージド ID を保持する方法を示す手順 5 に進みます。
4. VM にユーザー割り当てマネージド ID が割り当てられていない場合は、次の CURL コマンドを使用して Azure Resource Manager REST エンドポイントを呼び出し、VM に最初のユーザー割り当てマネージド ID を割り当てます。

    次の例では、リソース グループの `ID1` 内の *myVM* という VM に、ユーザー割り当てマネージド ID  を割り当てます。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    **API バージョン 2018-06-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"UserAssigned", "userAssignedIdentities":{"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{}}}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {
        "identity":{
           "type":"UserAssigned",
           "userAssignedIdentities":{
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{
    
              }
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01' -X PATCH -d '{"identity":{"type":"userAssigned", "identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"]}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {
        "identity":{
           "type":"userAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"
           ]
        }
     }
    ```
5. VM に既存のユーザー割り当てマネージド ID またはシステム割り当てマネージド ID が割り当てられている場合:

    **API バージョン 2018-06-01**

    ユーザー割り当てマネージド ID を `userAssignedIdentities` ディクショナリ値に追加します。

    たとえば、現在、VM にシステム割り当てマネージド ID とユーザー割り当てマネージド ID `ID1` が割り当てられており、これにユーザー割り当てマネージド ID `ID2` を追加する場合は、次のようにします。

    ```bash
    curl  'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned, UserAssigned", "userAssignedIdentities":{"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{},"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":{}}}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned, UserAssigned",
           "userAssignedIdentities":{
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{
    
              },
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":{
    
              }
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    新しいユーザー割り当てマネージド ID を追加する一方で、保持したいユーザー割り当てマネージド ID は `identityIds` 配列値に入れておいてください。

    たとえば、現在、VM にシステム割り当てマネージド ID とユーザー割り当てマネージド ID `ID1` が割り当てられており、これにユーザー割り当てマネージド ID `ID2` を追加する場合は、次のようにします。

    ```bash
    curl  'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01' -X PATCH -d '{"identity":{"type":"SystemAssigned,UserAssigned", "identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1","/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2"]}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01 HTTP/1.1
    ```

    **リクエストヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned,UserAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1",
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2"
           ]
        }
     }
    ```

#### Azure VM からユーザー割り当てのマネージド ID を削除する

VM からユーザー割り当ての ID を削除にするには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。

1. Bearer アクセス トークンを取得します。このトークンは、Authorization ヘッダーでシステム割り当てマネージド ID を使用して VM を作成する次の手順で使用します。

    ```azurecli
    az account get-access-token
    ```
2. VM に割り当てたままにする既存のユーザー割り当てマネージド ID を削除しないようにするか、システム割り当てマネージド ID を削除するには、次の CURL コマンドを使用して、マネージド ID を一覧表示する必要があります。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachines/<VM NAME>?api-version=2018-06-01' -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    GET https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachines/<VM NAME>?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    VM にマネージド ID が割り当てられている場合、応答の `identity` 値に一覧表示されます。

    たとえば、ユーザー割り当てマネージド ID `ID1` と `ID2` が VM 割り当てられており、`ID1` を割り当てられたままにしてシステム割り当て ID を保持する場合:

    **API バージョン 2018-06-01**

    次のように、削除するユーザー割り当てマネージド ID に `null` を追加します。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned, UserAssigned", "userAssignedIdentities":{"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":null}}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned, UserAssigned",
           "userAssignedIdentities":{
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":null
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    次のように、`identityIds` 配列に維持するユーザー割り当てマネージド ID のみを保持します。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01' -X PATCH -d '{"identity":{"type":"SystemAssigned, UserAssigned", "identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"]}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
    | *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエストボディ**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned, UserAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"
           ]
        }
     }
    ```

VM にシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方がある場合は、次のコマンドを使用してシステム割り当てマネージド ID のみを使用するように切り替えることによって、すべてのユーザー割り当てマネージド ID を削除できます。

```bash
curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned"}}' -H "Content-Type: application/json" -H "Authorization:Bearer <ACCESS TOKEN>"
```

```HTTP
PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
```

**要求ヘッダー**

| 要求ヘッダー | 説明 |
| --- | --- |
| *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
| *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

**リクエストボディ**

```JSON
{
   "identity":{
      "type":"SystemAssigned"
   }
}
```

VM にユーザー割り当てマネージド ID のみがあり、そのすべてを削除する場合は、次のコマンドを使用します。

```bash
curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"None"}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
```

```HTTP
PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachines/myVM?api-version=2018-06-01 HTTP/1.1
```

**要求ヘッダー**

| 要求ヘッダー | 説明 |
| --- | --- |
| *Content-Type*（コンテンツの種類） | 必須。 `application/json` を設定します。 |
| *承認* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

**リクエストボディ**

```JSON
{
   "identity":{
      "type":"None"
   }
}
```

::: zone-end

::: zone pivot="qs-configure-sdk-windows-vm"

この記事では、Azure SDK を使用して Azure VM のマネージド ID を有効にする方法と削除する方法について説明します。

### 前提条件

- Azure リソースのマネージド ID 機能に慣れていない場合は、こちらの[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を参照してください。 Azure アカウントをお持ちでない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

### Azure リソースのマネージド ID をサポートする Azure SDK

Azure は、一連の [Azure SDK](https://azure.microsoft.com/downloads) によって、複数のプログラミング プラットフォームをサポートしています。 その一部は、Azure リソースのマネージド ID をサポートするために更新されています。また、使用方法を示す対応するサンプルが用意されています。 次の一覧は、他のサポートが追加されると更新されます。

| SDK | サンプル |
| --- | --- |
| .NET | [Azure リソースのマネージド ID が有効な VM からリソースを管理する](https://github.com/Azure-Samples/aad-dotnet-manage-resources-from-vm-with-msi) |
| Java | [Azure リソースのマネージド ID が有効な VM からストレージを管理する](https://github.com/Azure-Samples/compute-java-manage-resources-from-vm-with-msi-in-aad-group) |
| Node.js | [システム割り当てマネージド ID が有効な VM を作成する](https://github.com/Azure-Samples/compute-node-msi-vm) |
| Python | [システム割り当てマネージド ID が有効な VM を作成する](https://github.com/Azure-Samples/compute-python-msi-vm) |
| Ruby | [システム割り当て ID が有効な Azure VM を作成する](https://github.com/Azure-Samples/compute-ruby-msi-vm/) |

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities-scale-sets"} -->
## 仮想マシン スケール セットでAzure リソースのマネージド ID を構成する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities-scale-sets
- Service: entra-id / managed-identities
- Article date: 2025-01-16
- Summary: Azure ポータルを使用して、仮想マシン スケール セット上のAzure リソースのマネージド ID を構成する手順について説明します。

Azure リソースのマネージド ID は、Azure サービスにMicrosoft Entra IDで自動的にマネージド ID を提供します。 この ID を使用すると、コードに資格情報を持たずに、Microsoft Entra認証をサポートする任意のサービスに対して認証を行うことができます。

Azure Policyの定義と詳細については、「[マネージド ID (プレビュー)](https://portal.azure.com/#blade/Microsoft_Azure_Policy/PolicyDetailBlade/definitionId/%2Fproviders%2FMicrosoft.Authorization%2FpolicyDefinitions%2F516187d4-ef64-4a1b-ad6b-a7348502976c)を割り当てるには、Azure Policyを使用する」を参照してください。

::: zone pivot="identity-mi-methods-azp"

この記事では、Azure ポータルを使用して、仮想マシン スケール セットでAzure リソース操作に対して次のマネージド ID を実行する方法について説明します。

- Azure リソースのマネージド ID に慣れていない場合は、「[overview」セクション](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を確認してください。
- Azureアカウントをまだお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップしてから続行してください。
- この記事の管理操作を実行するには、アカウントに次のAzureロールの割り当てが必要です。

    注

    追加のMicrosoft Entraディレクトリ ロールの割り当ては必要ありません。

    - 仮想マシン スケール セットからシステム割り当てマネージド ID を有効化および削除するための[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)。

### システム割り当てマネージド ID

このセクションでは、Azure ポータルを使用して、システム割り当てマネージド ID を有効または無効にする方法について説明します。

#### 仮想マシン スケール セットの作成中にシステム割り当てマネージド ID を有効にする

現在、Azure ポータルでは、仮想マシン スケール セットの作成時にシステム割り当てマネージド ID を有効にすることはできません。 まず、[仮想マシン スケール セット](https://learn.microsoft.com/ja-jp/azure/virtual-machine-scale-sets/quick-create-portal)を作成してから、スケール セットでシステム割り当てマネージド ID を有効にします。

- [Azure ポータルで仮想マシン スケール セットを作成します](https://learn.microsoft.com/ja-jp/azure/virtual-machine-scale-sets/quick-create-portal)

#### 既存の仮想マシン スケール セットでシステム割り当てマネージド ID を有効にする

もともとシステム割り当てマネージド ID がプロビジョニングされていなかった仮想マシン スケール セットでそれを有効にするには:

1. 仮想マシン スケール セットを含むAzure サブスクリプションに関連付けられているアカウントを使用して>Azure ポータル
2. 目的の仮想マシン スケール セットに移動します。
3. **[システム割り当て済み]** にある **[状態]** で **[オン]** を選択して、 **[保存]** をクリックします。

    [Image: [ID (プレビュー)] ページのスクリーンショット。[システム割り当て済み] が選択され、[状態] が [オン] になっており、[保存] ボタンが強調表示されている。]

#### 仮想マシン スケール セットからシステム割り当てマネージド ID を削除する

システム割り当てマネージド ID が不要になった仮想マシン スケール セットがある場合:

1. 仮想マシン スケール セットを含むAzure サブスクリプションに関連付けられているアカウントを使用して>Azure ポータル
2. 目的の仮想マシン スケール セットに移動します。
3. **[システム割り当て済み]** にある **[状態]** で **[オフ]** を選択して、 **[保存]** をクリックします。

    [Image: 構成ページを示すスクリーンショット。]

### ユーザー指定マネージド ID

このセクションでは、Azure ポータルを使用して、仮想マシン スケール セットからユーザー割り当てマネージド ID を追加および削除する方法について説明します。

#### 仮想マシン スケール セットの作成中にユーザー割り当てマネージド ID を割り当てる

現在、Azure ポータルでは、仮想マシン スケール セットの作成時にユーザー割り当てマネージド ID を割り当てることはサポートされていません。 まず、[仮想マシン スケール セット](https://learn.microsoft.com/ja-jp/azure/virtual-machine-scale-sets/quick-create-portal)を作成してから、ユーザー割り当てマネージド ID をスケール セットに割り当てます。

#### 既存の仮想マシン スケール セットにユーザー割り当てマネージド ID を割り当てる

1. 仮想マシン スケール セットを含むAzure サブスクリプションに関連付けられているアカウントを使用して>Azure ポータル
2. 目的の仮想マシン スケール セットに移動し、**[セキュリティ]**&gt;**[ID]**、**[ユーザー割り当て済み]**、**[+ 追加]** の順にクリックします。
3. 以前に作成した [ユーザー割り当てマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities#create-a-user-assigned-managed-identity) を一覧から選択します。

    [Image: [ユーザー割り当て] が選択され、[追加] ボタンが強調表示されている [ID] ページのスクリーンショット。]

#### 仮想マシン スケール セットからユーザー割り当てマネージド ID を削除する

VM を含むAzure サブスクリプションに関連付けられているアカウントを使用して、[Azure ポータル](https://portal.azure.com)にサインインします。

目的の仮想マシン スケール セットに移動します。次に、 **[ID]** 、 **[ユーザー割り当て済み]** 、削除したいユーザー割り当てマネージド ID の名前を順にクリックしてから、 **[削除]** をクリックします (確認ウィンドウで **[はい]** をクリックします)。

[Image: 仮想マシン スケール セットからユーザー割り当て ID を削除する方法を示すスクリーンショット。]

::: zone-end

::: zone pivot="identity-mi-methods-azcli"

この記事では、Azure CLIを使用して、Azure仮想マシン スケール セットでAzure リソース操作に対して次のマネージド ID を実行する方法について説明します。

- Azure仮想マシン スケール セットでシステム割り当てマネージド ID を有効または無効にする
- Azure仮想マシン スケール セットでユーザー割り当てマネージド ID を追加および削除する

Azureアカウントをまだお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップしてから続行してください。

### 前提条件

- Azure リソースのマネージド ID に慣れていない場合は、「Azure リソースのマネージド ID とは。 システム割り当てとユーザー割り当ての両方の種類のマネージド ID の詳細については、「[マネージド ID の種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)」をご覧ください。
- この記事の管理操作を実行するには、アカウントに次のAzureロールベースのアクセス制御の割り当てが必要です。

    - [仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor): 仮想マシン スケール セットを作成し、そのセットにシステム割り当ての管理対象 ID またはユーザー割り当ての管理対象 ID を有効化または削除することができるロールです。
    - [マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールを使用して、ユーザー割り当てマネージド ID を作成します。
    - [マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator)ロール。ユーザー割り当てマネージド ID の仮想マシン スケール セットへの割り当ておよび仮想マシン スケール セットからの削除を実行します。

    注

    追加のMicrosoft Entraディレクトリ ロールの割り当ては必要ありません。

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「[Get started with Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI 参照コマンドをローカルで実行する場合は、Azure CLI[install](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)します。 Windowsまたは macOS で実行している場合は、Docker コンテナーでAzure CLIを実行することを検討してください。 詳細については、「[Docker コンテナーでAzure CLIを実行する方法](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)を参照してください。

    - ローカル インストールを使用している場合は、[az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用してAzure CLIにサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「[Azure CLI を使用して Azure に認証する](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - メッセージが表示されたら、最初に使用するときにAzure CLI拡張機能をインストールします。 拡張機能の詳細については、「Azure CLIを参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行し、インストールされているバージョンおよび依存ライブラリを検索します。 最新バージョンにアップグレードするには、[az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

### システム割り当てマネージド ID

このセクションでは、Azure CLIを使用して、Azure仮想マシン スケール セットのシステム割り当てマネージド ID を有効または無効にする方法について説明します。

#### Azure仮想マシン スケール セットの作成時にシステム割り当てマネージド ID を有効にする

システム割り当てマネージド ID を有効にして仮想マシン スケール セットを作成する場合:

1. [az group create](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview#terminology) を使用して、仮想マシン スケール セットとその関連リソースの管理およびデプロイ用に[リソース グループ](https://learn.microsoft.com/ja-jp/cli/azure/group/#az-group-create)を作成します。 代わりに使用するリソース グループが既にある場合は、この手順をスキップできます。

    ```azurecli
    az group create --name myResourceGroup --location westus
    ```
2. 仮想マシン スケール セットを[作成します](https://learn.microsoft.com/ja-jp/cli/azure/vmss/#az-vmss-create)。 次の例では、`--assign-identity` および `--role` を指定し、システム割り当てマネージド ID を持つ仮想マシン スケール セット *myVMSS* を、`--scope` パラメーターに従って作成します。 `--admin-username` および `--admin-password` パラメーターは、仮想マシンのサインイン用の管理ユーザー名とパスワードを指定します。 これらの値は、お使いの環境に合わせて更新してください。

    ```azurecli
    az vmss create --resource-group myResourceGroup --name myVMSS --image win2016datacenter --upgrade-policy-mode automatic --custom-data cloud-init.txt --admin-username azureuser --admin-password myPassword12 --assign-identity --generate-ssh-keys --role contributor --scope mySubscription
    ```

#### 既存のAzure仮想マシン スケール セットでシステム割り当てマネージド ID を有効にする

既存のAzure仮想マシン スケール セットでシステムによって割り当てられたマネージド ID を有効にする必要がある場合は:

```azurecli
az vmss identity assign -g myResourceGroup -n myVMSS
```

#### Azure仮想マシン スケール セットからシステム割り当てマネージド ID を無効にする

システム割り当てマネージド ID は不要になったが、ユーザー割り当てマネージド ID はまだ必要な仮想マシン スケール セットがある場合は、次のコマンドを使用します。

```azurecli
az vmss update -n myVM -g myResourceGroup --set identity.type='UserAssigned' 
```

システム割り当てマネージド ID が不要になり、ユーザー割り当てマネージド ID がない仮想マシンがある場合は、次のコマンドを使用します。

注

`none` の値は、大文字と小文字が区別されます。 小文字にする必要があります。

```azurecli
az vmss update -n myVM -g myResourceGroup --set identity.type="none"
```

### ユーザー指定マネージド ID

このセクションでは、Azure CLIを使用してユーザー割り当てマネージド ID を有効にして削除する方法について説明します。

#### 仮想マシン スケール セットの作成中にユーザー割り当てマネージド ID を割り当てる

このセクションでは、仮想マシン スケール セットを作成する方法と、ユーザー割り当てマネージド ID を仮想マシン スケール セットに割り当てる方法について説明します。 使用する仮想マシン スケール セットが既にある場合は、このセクションをスキップして次のセクションに進んでください。

1. 使用するリソース グループが既にある場合は、この手順をスキップできます。 [az group create](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview#terminology) を使用して、ユーザー割り当てマネージド ID の格納と配置を行う[リソース グループ](https://learn.microsoft.com/ja-jp/cli/azure/group/#az-group-create)を作成します。 `<RESOURCE GROUP>` と `<LOCATION>` のパラメーターの値は、必ず実際の値に置き換えてください。 :

    ```azurecli
    az group create --name <RESOURCE GROUP> --location <LOCATION>
    ```
2. [az identity create](https://learn.microsoft.com/ja-jp/cli/azure/identity#az-identity-create) を使用して、ユーザー割り当てマネージド ID を作成します。 `-g` パラメーターにはユーザー割り当てマネージド ID を作成するリソース グループを指定し、`-n` パラメーターにはその名前を指定します。 `<RESOURCE GROUP>` と `<USER ASSIGNED IDENTITY NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。

    重要

    ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

    ```azurecli
    az identity create -g <RESOURCE GROUP> -n <USER ASSIGNED IDENTITY NAME>
    ```

    応答には、次のように、作成されたユーザー割り当てマネージド ID の詳細が含まれています。 ユーザー割り当てマネージド ID に割り当てられたリソース `id` 値は、次の手順で使用されます。

    ```json
    {
         "clientId": "00001111-aaaa-2222-bbbb-3333cccc4444",
         "clientSecretUrl": "https://control-westcentralus.identity.azure.net/subscriptions/<SUBSCRIPTON ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>/credentials?tid=5678&oid=9012&aid=aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
         "id": "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>",
         "location": "westcentralus",
         "name": "<USER ASSIGNED IDENTITY NAME>",
         "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
         "resourceGroup": "<RESOURCE GROUP>",
         "tags": {},
         "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
         "type": "Microsoft.ManagedIdentity/userAssignedIdentities"    
    }
    ```
3. 仮想マシン スケール セットを[作成します](https://learn.microsoft.com/ja-jp/cli/azure/vmss#az-vmss-create)。 次の例では、`--assign-identity` パラメーターで指定されたとおり、`--role` と `--scope` を指定して新しいユーザー割り当てマネージド ID に関連付けられている仮想マシン スケール セットを作成します。 `<RESOURCE GROUP>`、`<VMSS NAME>`、`<USER NAME>`、`<PASSWORD>`、`<USER ASSIGNED IDENTITY>`、`<ROLE>`、`<SUBSCRIPTION>` の各パラメーターの値は、必ず実際の値に置き換えてください。

    ```azurecli
    az vmss create --resource-group <RESOURCE GROUP> --name <VMSS NAME> --image <SKU Linux Image> --admin-username <USER NAME> --admin-password <PASSWORD> --assign-identity <USER ASSIGNED IDENTITY> --role <ROLE> --scope <SUBSCRIPTION>
    ```

#### 既存の仮想マシン スケール セットにユーザー割り当てマネージド ID を割り当てる

1. [az identity create](https://learn.microsoft.com/ja-jp/cli/azure/identity#az-identity-create) を使用して、ユーザー割り当てマネージド ID を作成します。 `-g` パラメーターにはユーザー割り当てマネージド ID を作成するリソース グループを指定し、`-n` パラメーターにはその名前を指定します。 `<RESOURCE GROUP>` と `<USER ASSIGNED IDENTITY NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。

    ```azurecli
    az identity create -g <RESOURCE GROUP> -n <USER ASSIGNED IDENTITY NAME>
    ```

    応答には、次のように、作成されたユーザー割り当てマネージド ID の詳細が含まれています。

    ```json
    {
         "clientId": "00001111-aaaa-2222-bbbb-3333cccc4444",
         "clientSecretUrl": "https://control-westcentralus.identity.azure.net/subscriptions/<SUBSCRIPTON ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY >/credentials?tid=5678&oid=9012&aid=aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
         "id": "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY>",
         "location": "westcentralus",
         "name": "<USER ASSIGNED IDENTITY>",
         "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
         "resourceGroup": "<RESOURCE GROUP>",
         "tags": {},
         "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
         "type": "Microsoft.ManagedIdentity/userAssignedIdentities"    
    }
    ```
2. 仮想マシン スケール セットにユーザー割り当てマネージド ID を[割り当てます](https://learn.microsoft.com/ja-jp/cli/azure/vmss/identity)。 `<RESOURCE GROUP>` と `<VIRTUAL MACHINE SCALE SET NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。 `<USER ASSIGNED IDENTITY>` は、前の手順で作成されたユーザー割り当て ID のリソース `name` プロパティです。

    ```azurecli
    az vmss identity assign -g <RESOURCE GROUP> -n <VIRTUAL MACHINE SCALE SET NAME> --identities <USER ASSIGNED IDENTITY>
    ```

#### Azure仮想マシン スケール セットからユーザー割り当てマネージド ID を削除する

仮想マシン スケール セットからユーザー割り当てマネージド ID を[削除する](https://learn.microsoft.com/ja-jp/cli/azure/vmss/identity#az-vmss-identity-remove)には、`az vmss identity remove` を使用します。 仮想マシン スケール セットに割り当てられている唯一のユーザー割り当てマネージド ID の場合は、ID 型の値から `UserAssigned` が削除されます。 `<RESOURCE GROUP>` と `<VIRTUAL MACHINE SCALE SET NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。 `<USER ASSIGNED IDENTITY>` はユーザー割り当てマネージド ID の `name` プロパティになります。これは、`az vmss identity show` を使って、仮想マシン スケール セットの ID セクションで見つけることができます。

```azurecli
az vmss identity remove -g <RESOURCE GROUP> -n <VIRTUAL MACHINE SCALE SET NAME> --identities <USER ASSIGNED IDENTITY>
```

仮想マシン スケール セットにシステム割り当てマネージド ID がないときに、ユーザー割り当てマネージド ID をすべて削除する場合は、次のコマンドを使用します。

注

`none` の値は、大文字と小文字が区別されます。 小文字にする必要があります。

```azurecli
az vmss update -n myVMSS -g myResourceGroup --set identity.type="none" identity.userAssignedIdentities=null
```

仮想マシン スケール セットにシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方がある場合は、システム割り当てマネージド ID のみを使用するように切り替えることによって、すべてのユーザー割り当てマネージド ID を削除できます。 次のコマンドを使用します。

```azurecli
az vmss update -n myVMSS -g myResourceGroup --set identity.type='SystemAssigned' identity.userAssignedIdentities=null 
```

::: zone-end

::: zone pivot="identity-mi-methods-powershell"

この記事では、PowerShell を使用して、仮想マシン スケール セットでAzure リソース操作のマネージド ID を実行する方法について説明します。

- 仮想マシン スケール セット上でシステム割り当てマネージド ID を有効および無効にする
- 仮想マシン スケール セット上でユーザー割り当てマネージド ID の追加および削除を行う

注

Azure Az PowerShell モジュールを使用してAzureを操作することをお勧めします。 開始するには、[Install Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) を参照してください。 Az PowerShell モジュールに移行する方法については、「AzRM から Azを参照してください。

### 前提条件

- Azure リソースのマネージド ID に慣れていない場合は、「[overview」セクション](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を確認してください。 **[システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を確認してください**。
- Azureアカウントをまだお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップしてから続行してください。
- この記事の管理操作を実行するには、アカウントに次のAzureロールベースのアクセス制御の割り当てが必要です。

    注

    追加のMicrosoft Entraディレクトリ ロールの割り当ては必要ありません。

    - [仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)は、仮想マシン スケール セットを作成するための権限を持ちます。また、仮想マシン スケール セットに対して、システム割り当てマネージド IDおよびユーザー割り当てマネージド IDを有効化および削除する操作が可能です。
    - [マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールを使用して、ユーザー割り当てマネージド ID を作成します。
    - [マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator)ロール。ユーザー割り当てマネージド ID の仮想マシン スケール セットへの割り当ておよび仮想マシン スケール セットからの削除を実行します。
- サンプル スクリプトを実行するには、次の 2 つのオプションがあります。

    - [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview)を使用します。コード ブロックの右上隅にある **Try It** ボタンを使用して開くことができます。
    - 最新バージョンの [Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) をインストールしてローカルでスクリプトを実行し、`Connect-AzAccount` を使用してAzureにサインインします。

### システム割り当てマネージド ID

このセクションでは、Azure PowerShellを使用してシステム割り当てマネージド ID を有効にして削除する方法について説明します。

#### Azure仮想マシン スケール セットの作成時にシステム割り当てマネージド ID を有効にする

システム割り当てマネージド ID を有効にして仮想マシン スケール セットを作成する場合:

1. システム割り当てマネージド ID を持つ仮想マシン スケール セットの作成については、*New-AzVmssConfig* コマンドレット リファレンス記事の "[例 1](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/new-azvmssconfig)" を参照してください。 パラメーター `-IdentityType SystemAssigned` を `New-AzVmssConfig` コマンドレットに追加します。

    ```azurepowershell
    $VMSS = New-AzVmssConfig -Location $Loc -SkuCapacity 2 -SkuName "Standard_A0" -UpgradePolicyMode "Automatic" -NetworkInterfaceConfiguration $NetCfg -IdentityType SystemAssigned`
    ```

#### 既存のAzure仮想マシン スケール セットでシステム割り当てマネージド ID を有効にする

既存のAzure仮想マシン スケール セットでシステム割り当てマネージド ID を有効にする必要がある場合:

1. 使用しているAzure アカウントが、"仮想マシン共同作成者" などの仮想マシン スケール セットに対する書き込みアクセス許可を付与するロールに属していることを確認します。
2. [`Get-AzVmss`](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/get-azvmss) コマンドレットを使用して、仮想マシン スケール セットのプロパティを取得します。 システム割り当てのマネージド ID を有効にするには、[Update-AzVmss](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/update-azvmss) コマンドレットで `-IdentityType` スイッチを使用します。

    ```azurepowershell
    Update-AzVmss -ResourceGroupName myResourceGroup -Name -myVmss -IdentityType "SystemAssigned"
    ```

#### Azure仮想マシン スケール セットからシステム割り当てマネージド ID を無効にする

システム割り当てマネージド ID は不要になったが、ユーザー割り当てマネージド ID はまだ必要な仮想マシン スケール セットがある場合は、次のコマンドを使用します。

1. お使いのアカウントが、"仮想マシン共同作成者" など、仮想マシン スケール セット上の書き込みアクセス許可が提供されるロールに属していることを確認します。
2. 次のコマンドレットを実行します。

    ```azurepowershell
    Update-AzVmss -ResourceGroupName myResourceGroup -Name myVmss -IdentityType "UserAssigned"
    ```
3. システム割り当てマネージド ID が不要になった、ユーザー割り当てマネージド ID を持たない仮想マシン スケール セットがある場合は、次のコマンドを使用します。

    ```azurepowershell
    Update-AzVmss -ResourceGroupName myResourceGroup -Name myVmss -IdentityType None
    ```

### ユーザー指定マネージド ID

このセクションでは、Azure PowerShellを使用して、仮想マシン スケール セットからユーザー割り当てマネージド ID を追加および削除する方法について説明します。

#### Azure仮想マシン スケール セットの作成時にユーザー割り当てマネージド ID を割り当てる

ユーザー割り当てマネージド ID を持つ新しい仮想マシン スケール セットの作成は、PowerShell では現在サポートされていません。 次のセクションで、既存の仮想マシン スケール セットにユーザー割り当てマネージド ID を追加する方法を確認してください。 アップデートは後ほどご確認ください。

#### 既存のAzure仮想マシン スケール セットにユーザー割り当てマネージド ID を割り当てる

既存のAzure仮想マシン スケール セットにユーザー割り当てマネージド ID を割り当てるには:

1. お使いのアカウントが、"仮想マシン共同作成者" など、仮想マシン スケール セット上の書き込みアクセス許可が提供されるロールに属していることを確認します。
2. `Get-AzVM` コマンドレットを使用して、仮想マシン スケール セットのプロパティを取得します。 次に、仮想マシン スケール セットにユーザー割り当てマネージド ID を割り当てるには、[Update-AzVmss](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/update-azvmss) コマンドレットで `-IdentityType` スイッチと `-IdentityID` スイッチを使用します。 `<VM NAME>`、`<SUBSCRIPTION ID>`、`<RESOURCE GROUP>`、`<USER ASSIGNED ID1>`、`USER ASSIGNED ID2` を、実際の値に置き換えます。

    重要

    ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

    ```azurepowershell
    Update-AzVmss -ResourceGroupName <RESOURCE GROUP> -Name <VMSS NAME> -IdentityType UserAssigned -IdentityID "<USER ASSIGNED ID1>","<USER ASSIGNED ID2>"
    ```

#### Azure仮想マシン スケール セットからユーザー割り当てマネージド ID を削除する

仮想マシン スケール セットに複数のユーザー割り当てマネージド ID がある場合は、次のコマンドを使用して、最後の ID 以外の ID をすべて削除できます。 `<RESOURCE GROUP>` と `<VIRTUAL MACHINE SCALE SET NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。 `<USER ASSIGNED IDENTITY NAME>` はユーザー割り当てマネージド ID の名前プロパティであり、仮想マシン スケール セット上に残す必要があります。 この情報は、`az vmss show` を使用して、仮想マシン スケール セットの ID セクションで見つけることができます。

```azurepowershell
Update-AzVmss -ResourceGroupName myResourceGroup -Name myVmss -IdentityType UserAssigned -IdentityID "<USER ASSIGNED IDENTITY NAME>"
```

仮想マシン スケール セットにシステム割り当てマネージド ID がないときに、ユーザー割り当てマネージド ID をすべて削除する場合は、次のコマンドを使用します。

```azurepowershell
Update-AzVmss -ResourceGroupName myResourceGroup -Name myVmss -IdentityType None
```

仮想マシン スケール セットにシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方がある場合は、システム割り当てマネージド ID のみを使用するように切り替えることによって、すべてのユーザー割り当てマネージド ID を削除できます。

```azurepowershell
Update-AzVmss -ResourceGroupName myResourceGroup -Name myVmss -IdentityType "SystemAssigned"
```

::: zone-end

::: zone pivot="identity-mi-methods-arm"

この記事では、Azure Resource Managerデプロイ テンプレートを使用して、Azure仮想マシン スケール セットに対するAzure リソース操作に対して次のマネージド ID を実行する方法について説明します。

- Azure仮想マシン スケール セットでシステム割り当てマネージド ID を有効または無効にする
- Azure仮想マシン スケール セットでユーザー割り当てマネージド ID を追加および削除する

### 前提条件

- Azure リソースのマネージド ID に慣れていない場合は、「[overview」セクション](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を確認してください。 **[システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を必ず確認してください**。
- Azureアカウントをまだお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップしてから続行してください。
- この記事の管理操作を実行するには、アカウントに次のAzureロールベースのアクセス制御の割り当てが必要です。

    注

    追加のMicrosoft Entraディレクトリ ロールの割り当ては必要ありません。

    - [仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor): 仮想マシン スケール セットを作成し、そのセットにシステム割り当ての管理対象 ID またはユーザー割り当ての管理対象 ID を有効化または削除することができるロールです。
    - [マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールを使用して、ユーザー割り当てマネージド ID を作成します。
    - [マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator)ロール。ユーザー割り当てマネージド ID の仮想マシン スケール セットへの割り当ておよび仮想マシン スケール セットからの削除を実行します。

### Azure Resource Manager テンプレート

Azure ポータルとスクリプトと同様に、[Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) テンプレートでは、Azure リソース グループによって定義された新しいリソースまたは変更されたリソースをデプロイできます。 ローカルとポータル ベースの両方を含むテンプレートの編集やデプロイでは、次のような複数のオプションが使用できます。

- Azure Marketplace から custom テンプレートを使用すると、最初からテンプレートを作成したり、既存の共通テンプレートまたは quickstart テンプレート に基づいてテンプレートを作成したりできます。
- [元のデプロイ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/export-template-portal)または[デプロイの現在の状態](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/export-template-portal)からテンプレートをエクスポートすることによって、既存のリソース グループから派生させます。
- ローカルの [JSON エディター (VS Code など)](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/quickstart-create-templates-use-the-portal) を使用してから、PowerShell または CLI を使用してアップロードおよびデプロイします。
- Visual Studio [Azure リソース グループ プロジェクト](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/create-visual-studio-deployment-project)を使用して、テンプレートを作成およびデプロイします。

選択するオプションにかかわらず、初めてのデプロイ時も再デプロイ時もテンプレートの構文は同じです。 新規または既存の VM でAzure リソースのマネージド ID を有効にする方法も同じです。 また、既定では、Azure Resource Managerはデプロイに対して [incremental update](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/deployment-modes) を実行します。

### システム割り当てマネージド ID

このセクションでは、Azure Resource Manager テンプレートを使用して、システム割り当てマネージド ID を有効または無効にします。

#### 仮想マシン スケール セットの作成時に、または既存の仮想マシン スケール セットでシステム割り当てマネージド ID を有効にする

1. ローカルまたはAzure ポータルを使用してAzureにサインインする場合でも、仮想マシン スケール セットを含むAzure サブスクリプションに関連付けられているアカウントを使用します。
2. システム割り当てマネージド ID を有効にするには、テンプレートをエディターに読み込み、resources セクション内で対象の `Microsoft.Compute/virtualMachinesScaleSets` リソースを探し、`identity` プロパティと同じレベルに `"type": "Microsoft.Compute/virtualMachinesScaleSets"` プロパティを追加します。 次の構文を使用します。

    ```JSON
    "identity": {
        "type": "SystemAssigned"
    }
    ```
3. 完了すると、次のセクションがテンプレートのリソース セクションに追加され、以下に示す例のようになります。

    ```json
     "resources": [
         {
             //other resource provider properties...
             "apiVersion": "2018-06-01",
             "type": "Microsoft.Compute/virtualMachineScaleSets",
             "name": "[variables('vmssName')]",
             "location": "[resourceGroup().location]",
             "identity": {
                 "type": "SystemAssigned",
             },
            "properties": {
                 //other resource provider properties...
                 "virtualMachineProfile": {
                     //other virtual machine profile properties...
    
                 }
             }
         }
     ]
    ```

#### Azure仮想マシン スケール セットからシステム割り当てマネージド ID を無効にする

システム割り当てマネージド ID が不要になった仮想マシン スケール セットがある場合:

1. ローカルまたはAzure ポータルを使用してAzureにサインインする場合でも、仮想マシン スケール セットを含むAzure サブスクリプションに関連付けられているアカウントを使用します。
2. テンプレートをエディターに読み込み、`Microsoft.Compute/virtualMachineScaleSets` セクション内で関心のある `resources` リソースを探します。 システム割り当てマネージド ID のみが割り当てられた VM がある場合は、ID の種類を `None` に変更することで無効にすることができます。

    **Microsoft.Compute/virtualMachineScaleSets API バージョン 2018-06-01**

    お使いの apiVersion が `2018-06-01` であり、VM にシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方が割り当てられている場合は、ID の種類から `SystemAssigned` を削除し、userAssignedIdentities ディクショナリ値と共に `UserAssigned` を保持します。

    **Microsoft.Compute/virtualMachineScaleSets API バージョン 2018-06-01**

    お使いの apiVersion が `2017-12-01` であり、仮想マシン スケール セットにシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方が割り当てられている場合は、ID の種類から `SystemAssigned` を削除し、ユーザー割り当てマネージド ID の `UserAssigned` 配列と共に `identityIds` を保持します。

    次の例は、ユーザー割り当てマネージド ID が割り当てられていない仮想マシン スケール セットからシステム割り当てマネージド ID を削除する方法を示しています。

    ```json
    {
        "name": "[variables('vmssName')]",
        "apiVersion": "2018-06-01",
        "location": "[parameters(Location')]",
        "identity": {
            "type": "None"
         }
    
    }
    ```

### ユーザー指定マネージド ID

このセクションでは、Azure Resource Manager テンプレートを使用して、ユーザー割り当てマネージド ID を仮想マシン スケール セットに割り当てます。

注

Azure Resource Manager テンプレートを使用してユーザー割り当てマネージド ID を作成するには、「[ユーザー割り当てマネージド ID の作成](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-arm#create-a-user-assigned-managed-identity)を参照してください。

#### 仮想マシン スケール セットにユーザーが割り当てたマネージド ID を追加する

1. ユーザー割り当てマネージド ID を仮想マシン スケール セットに割り当てるには、`resources` 要素に次のエントリを追加します。 `<USERASSIGNEDIDENTITY>` は、作成したユーザー割り当てマネージド ID の名前に置き換えてください。

    **Microsoft.Compute/virtualMachineScaleSets API バージョン 2018-06-01**

    お使いの apiVersion が `2018-06-01` の場合、ユーザー割り当てマネージド ID は `userAssignedIdentities` ディクショナリ形式で格納されます。`<USERASSIGNEDIDENTITYNAME>` 値は、テンプレートの `variables` セクションに定義された変数に格納する必要があります。

    ```json
    {
        "name": "[variables('vmssName')]",
        "apiVersion": "2018-06-01",
        "location": "[parameters(Location')]",
        "identity": {
            "type": "userAssigned",
            "userAssignedIdentities": {
                "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<USERASSIGNEDIDENTITYNAME>'))]": {}
            }
        }
    
    }
    ```

    **Microsoft.Compute/virtualMachineScaleSets API バージョン 2017-12-01**

    お使いの `apiVersion` が `2017-12-01` 以前の場合、ユーザー割り当てマネージド ID は `identityIds` 配列に格納されます。`<USERASSIGNEDIDENTITYNAME>` 値は、テンプレートの variables セクションに定義された変数に格納する必要があります。

    ```json
    {
        "name": "[variables('vmssName')]",
        "apiVersion": "2017-03-30",
        "location": "[parameters(Location')]",
        "identity": {
            "type": "userAssigned",
            "identityIds": [
                "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<USERASSIGNEDIDENTITY>'))]"
            ]
        }
    }
    ```
2. 完了すると、テンプレートは以下の例のようになります。

    **Microsoft.Compute/virtualMachineScaleSets API バージョン 2018-06-01**

    ```json
    "resources": [
         {
             //other resource provider properties...
             "apiVersion": "2018-06-01",
             "type": "Microsoft.Compute/virtualMachineScaleSets",
             "name": "[variables('vmssName')]",
             "location": "[resourceGroup().location]",
             "identity": {
                 "type": "UserAssigned",
                 "userAssignedIdentities": {
                     "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<USERASSIGNEDIDENTITYNAME>'))]": {}
                 }
             },
            "properties": {
                 //other virtual machine properties...
                 "virtualMachineProfile": {
                     //other virtual machine profile properties...
                 }
             }
         }
     ]
    ```

    **Microsoft.Compute/virtualMachines API バージョン 2017-12-01**

    ```json
    "resources": [
         {
             //other resource provider properties...
             "apiVersion": "2017-12-01",
             "type": "Microsoft.Compute/virtualMachineScaleSets",
             "name": "[variables('vmssName')]",
             "location": "[resourceGroup().location]",
             "identity": {
                 "type": "UserAssigned",
                 "identityIds": [
                     "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<USERASSIGNEDIDENTITYNAME>'))]"
                 ]
             },
            "properties": {
                 //other virtual machine properties...
                 "virtualMachineProfile": {
                     //other virtual machine profile properties...
                 }
             }
         }
     ]
    ```

#### Azure仮想マシン スケール セットからユーザー割り当てマネージド ID を削除する

ユーザー割り当てマネージド ID が不要になった仮想マシン スケール セットがある場合は、次の手順に従います。

1. ローカルまたはAzure ポータルを使用してAzureにサインインする場合でも、仮想マシン スケール セットを含むAzure サブスクリプションに関連付けられているアカウントを使用します。
2. テンプレートをエディターに読み込み、`Microsoft.Compute/virtualMachineScaleSets` セクション内で関心のある `resources` リソースを探します。 ユーザー割り当てマネージド ID しか存在しない仮想マシン スケール セットがある場合は、ID の種類を `None` に変更することによってそれを無効にすることができます。

    次の例は、システム割り当てマネージド ID が割り当てられていない VM からユーザー割り当てマネージド ID をすべて削除する方法を示しています。

    ```json
    {
        "name": "[variables('vmssName')]",
        "apiVersion": "2018-06-01",
        "location": "[parameters(Location')]",
        "identity": {
            "type": "None"
         }
    }
    ```

    **Microsoft.Compute/virtualMachineScaleSets API バージョン 2018-06-01**

    仮想マシン スケール セットから 1 つのユーザー割り当てマネージド ID を削除するには、`userAssignedIdentities` ディクショナリからそれを削除します。

    システムから割り当てられた ID がある場合は、それを `identity` 値の下の `type` 値に保持してください。

    **Microsoft.Compute/virtualMachineScaleSets API バージョン 2017-12-01**

    仮想マシン スケール セットから 1 つのユーザー割り当てマネージド ID を削除するには、`identityIds` 配列からそれを削除します。

    システム割り当てマネージド ID がある場合は、`type` の下位にある `identity` の値としてそれを保持します。

::: zone-end

::: zone pivot="identity-mi-methods-rest"

この記事では、CURL を使用して Azure Resource Manager REST エンドポイントを呼び出し、仮想マシン スケール セットでAzure リソース操作に対して次のマネージド ID を実行する方法について説明します。

- Azure仮想マシン スケール セットでシステム割り当てマネージド ID を有効または無効にする
- Azure仮想マシン スケール セットでユーザー割り当てマネージド ID を追加および削除する

Azureアカウントをまだお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップしてから続行してください。

### 前提条件

- Azure リソースのマネージド ID に慣れていない場合は、「Azure リソースのマネージド ID とは。 システム割り当てとユーザー割り当ての両方の種類のマネージド ID の詳細については、「[マネージド ID の種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)」をご覧ください。
- この記事の管理操作を実行するには、アカウントに次のAzureロールの割り当てが必要です。

    - [仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor): 仮想マシン スケール セットを作成し、そのセットにシステム割り当ての管理対象 ID またはユーザー割り当ての管理対象 ID を有効化または削除することができるロールです。
    - [マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールを使用して、ユーザー割り当てマネージド ID を作成します。
    - 仮想マシン スケール セットにユーザーが割り当てた ID を割り当てたり削除したりするための[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) ロール。

    注

    追加のMicrosoft Entraディレクトリ ロールの割り当ては必要ありません。

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「[Get started with Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI 参照コマンドをローカルで実行する場合は、Azure CLI[install](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)します。 Windowsまたは macOS で実行している場合は、Docker コンテナーでAzure CLIを実行することを検討してください。 詳細については、「[Docker コンテナーでAzure CLIを実行する方法](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)を参照してください。

    - ローカル インストールを使用している場合は、[az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用してAzure CLIにサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「[Azure CLI を使用して Azure に認証する](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - メッセージが表示されたら、最初に使用するときにAzure CLI拡張機能をインストールします。 拡張機能の詳細については、「Azure CLIを参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行し、インストールされているバージョンおよび依存ライブラリを検索します。 最新バージョンにアップグレードするには、[az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

### システム割り当てマネージド ID

このセクションでは、CURL を使用して仮想マシン スケール セットでシステム割り当てマネージド ID を有効または無効にして、Azure Resource Manager REST エンドポイントを呼び出す方法について説明します。

#### 仮想マシン スケール セットの作成中にシステム割り当てマネージド ID を有効にする

システム割り当てマネージド ID が有効になっている仮想マシン スケール セットを作成するには、仮想マシン スケール セットを作成し、CURL を使用してシステム割り当てマネージド ID の種類の値を使用してResource Manager エンドポイントを呼び出すアクセス トークンを取得する必要があります。

1. [az group create](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview#terminology) を使用して、仮想マシン スケール セットとその関連リソースの管理およびデプロイ用に[リソース グループ](https://learn.microsoft.com/ja-jp/cli/azure/group/#az-group-create)を作成します。 代わりに使用するリソース グループが既にある場合は、この手順をスキップできます。

    ```azurecli
    az group create --name myResourceGroup --location westus
    ```
2. 仮想マシン スケール セット用の[ネットワーク インターフェイス](https://learn.microsoft.com/ja-jp/cli/azure/network/nic#az-network-nic-create)を作成します。

    ```azurecli
     az network nic create -g myResourceGroup --vnet-name myVnet --subnet mySubnet -n myNic
    ```
3. Bearer アクセス トークンを取得します。このトークンは、次の手順で、Authorization ヘッダーでシステム割り当てマネージド ID を使用して仮想マシン スケール セットを作成するときに使用します。

    ```azurecli
    az account get-access-token
    ```
4. Azure Cloud Shellを使用して、CURL を使用して仮想マシン スケール セットを作成し、Azure Resource Manager REST エンドポイントを呼び出します。 次の例では、要求本文に指定された値 `"identity":{"type":"SystemAssigned"}` に基づくシステム割り当てマネージド ID を使用して、*myResourceGroup* 内に *myVMSS* という仮想マシン スケール セットを作成します。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PUT -d '{"sku":{"tier":"Standard","capacity":3,"name":"Standard_D1_v2"},"location":"eastus","identity":{"type":"SystemAssigned"},"properties":{"overprovision":true,"virtualMachineProfile":{"storageProfile":{"imageReference":{"sku":"2016-Datacenter","publisher":"MicrosoftWindowsServer","version":"latest","offer":"WindowsServer"},"osDisk":{"caching":"ReadWrite","managedDisk":{"storageAccountType":"StandardSSD_LRS"},"createOption":"FromImage"}},"osProfile":{"computerNamePrefix":"myVMSS","adminUsername":"azureuser","adminPassword":"myPassword12"},"networkProfile":{"networkInterfaceConfigurations":[{"name":"myVMSS","properties":{"primary":true,"enableIPForwarding":true,"ipConfigurations":[{"name":"myVMSS","properties":{"subnet":{"id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/virtualNetworks/myVnet/subnets/mySubnet"}}}]}}]}},"upgradePolicy":{"mode":"Manual"}}}' -H "Content-Type: application/json" -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PUT https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "sku":{
           "tier":"Standard",
           "capacity":3,
           "name":"Standard_D1_v2"
        },
        "location":"eastus",
        "identity":{
           "type":"SystemAssigned"
        },
        "properties":{
           "overprovision":true,
           "virtualMachineProfile":{
              "storageProfile":{
                 "imageReference":{
                    "sku":"2016-Datacenter",
                    "publisher":"MicrosoftWindowsServer",
                    "version":"latest",
                    "offer":"WindowsServer"
                 },
                 "osDisk":{
                    "caching":"ReadWrite",
                    "managedDisk":{
                       "storageAccountType":"StandardSSD_LRS"
                    },
                    "createOption":"FromImage"
                 }
              },
              "osProfile":{
                 "computerNamePrefix":"myVMSS",
                 "adminUsername":"azureuser",
                 "adminPassword":"myPassword12"
              },
              "networkProfile":{
                 "networkInterfaceConfigurations":[
                    {
                       "name":"myVMSS",
                       "properties":{
                          "primary":true,
                          "enableIPForwarding":true,
                          "ipConfigurations":[
                             {
                                "name":"myVMSS",
                                "properties":{
                                   "subnet":{
                                      "id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/virtualNetworks/myVnet/subnets/mySubnet"
                                   }
                                }
                             }
                          ]
                       }
                    }
                 ]
              }
           },
           "upgradePolicy":{
              "mode":"Manual"
           }
        }
     }  
    ```

#### 既存の仮想マシン スケール セットでシステム割り当てマネージド ID を有効にする

既存の仮想マシン スケール セットでシステム割り当てマネージド ID を有効にするには、アクセス トークンを取得し、CURL を使用して Resource Manager REST エンドポイントを呼び出して ID の種類を更新する必要があります。

1. Bearer アクセス トークンを取得します。このトークンは、次の手順で、Authorization ヘッダーでシステム割り当てマネージド ID を使用して仮想マシン スケール セットを作成するときに使用します。

    ```azurecli
    az account get-access-token
    ```
2. 次の CURL コマンドを使用して、Azure Resource Manager REST エンドポイントを呼び出して、`{"identity":{"type":"SystemAssigned"}` という名前の仮想マシン スケール セットの値  によって要求本文で識別される、仮想マシン スケール セットのシステム割り当てマネージド ID を有効にします。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    重要

    仮想マシン スケール セットに割り当てられている既存のユーザー割り当てマネージド ID を削除しないようにするには、次の CURL コマンドを使用してユーザー割り当てマネージド ID を一覧表示する必要があります。`curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachineScaleSets/<VMSS NAME>?api-version=2018-06-01' -H "Authorization: Bearer <ACCESS TOKEN>"` 応答の `identity` 値で指定されたユーザー割り当てマネージド ID が仮想マシン スケール セットに割り当てられている場合は、仮想マシン スケール セットでシステム割り当てマネージド ID を有効にしている間、ユーザー割り当てマネージド ID を保持する方法を示す手順 3 にスキップします。

    ```bash
     curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned"}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned"
        }
     }
    ```
3. 既存のユーザー割り当てマネージド ID を持つ仮想マシン スケール セットでシステム割り当てマネージド ID を有効にするには、`SystemAssigned` を `type` 値に追加する必要があります。

    たとえば、仮想マシン スケール セットにユーザー割り当てマネージド ID `ID1` と `ID2` が割り当てられている状態で、仮想マシン スケール セットにシステム割り当てマネージド ID を追加する場合は、次の CURL 呼び出しを使用します。 `<ACCESS TOKEN>` と `<SUBSCRIPTION ID>` は、ご利用の環境に適した値に置き換えます。

    API バージョン `2018-06-01` では、ユーザー割り当てマネージド ID が `userAssignedIdentities` 値に配列形式で保存されていましたが、API バージョン `identityIds` では `2017-12-01` 値にディクショナリ形式で保存されます。

    **API バージョン 2018-06-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned,UserAssigned", "userAssignedIdentities":{"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{},"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":{}}}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned,UserAssigned",
           "userAssignedIdentities":{
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{
              },
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":{
    
              }
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01' -X PATCH -d '{"identity":{"type":"SystemAssigned,UserAssigned", "identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1","/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2"]}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned,UserAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1",
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2"
           ]
        }
     }
    ```

#### 仮想マシン スケール セットでシステム割り当てマネージド ID を無効にする

既存の仮想マシン スケール セットでシステム割り当て ID を無効にするには、アクセス トークンを取得し、CURL を使用して Resource Manager REST エンドポイントを呼び出し、ID の種類を `None` に更新する必要があります。

1. Bearer アクセス トークンを取得します。このトークンは、次の手順で、Authorization ヘッダーでシステム割り当てマネージド ID を使用して仮想マシン スケール セットを作成するときに使用します。

    ```azurecli
    az account get-access-token
    ```
2. CURL を使用して仮想マシン スケール セットを更新し、Azure Resource Manager REST エンドポイントを呼び出してシステム割り当てマネージド ID を無効にします。 この例は、*myVMSS* という名前の仮想マシン スケール セットから、リクエスト本文で `{"identity":{"type":"None"}}` の値として指定されたシステム割り当てマネージド ID を無効にします。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    重要

    仮想マシン スケール セットに割り当てられている既存のユーザー割り当てマネージド ID を削除しないようにするには、次の CURL コマンドを使用してユーザー割り当てマネージド ID を一覧表示する必要があります。`curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachineScaleSets/<VMSS NAME>?api-version=2018-06-01' -H "Authorization: Bearer <ACCESS TOKEN>"` ユーザー割り当てマネージド ID が仮想マシン スケール セットに割り当てられている場合は、仮想マシン スケール セットでシステム割り当てマネージド ID を削除する一方でユーザー割り当てマネージド ID を保持する方法を示す手順 3 にスキップします。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"None"}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"None"
        }
     }
    ```

    仮想マシン スケール セットからシステム割り当てマネージド ID を削除するには、ユーザー割り当てマネージド ID を持つ場合、API バージョン 2018-06-01 を使用して、`{"identity":{"type:" "}}` 値から `SystemAssigned` を削除し、`UserAssigned` 値と `userAssignedIdentities` ディクショナリ値を維持します。 **API バージョン 2017-12-01** またはそれ以前を使用している場合は、`identityIds` 配列を維持します。

### ユーザー指定マネージド ID

このセクションでは、CURL を使用して仮想マシン スケール セットでユーザー割り当てマネージド ID を追加および削除し、Azure Resource Manager REST エンドポイントを呼び出す方法について説明します。

#### 仮想マシン スケール セットの作成中にユーザー割り当てマネージド ID を割り当てる

1. Bearer アクセス トークンを取得します。このトークンは、次の手順で、Authorization ヘッダーでシステム割り当てマネージド ID を使用して仮想マシン スケール セットを作成するときに使用します。

    ```azurecli
    az account get-access-token
    ```
2. 仮想マシン スケール セット用の[ネットワーク インターフェイス](https://learn.microsoft.com/ja-jp/cli/azure/network/nic#az-network-nic-create)を作成します。

    ```azurecli
     az network nic create -g myResourceGroup --vnet-name myVnet --subnet mySubnet -n myNic
    ```
3. Bearer アクセス トークンを取得します。このトークンは、次の手順で、Authorization ヘッダーでシステム割り当てマネージド ID を使用して仮想マシン スケール セットを作成するときに使用します。

    ```azurecli
    az account get-access-token
    ```
4. 次のセクション「[ユーザー割り当てマネージド ID を作成する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-rest#create-a-user-assigned-managed-identity)」の手順を使用して、ユーザー割り当てマネージド ID を作成します。
5. CURL を使用して仮想マシン スケール セットを作成し、Azure Resource Manager REST エンドポイントを呼び出します。 次の例では、リソース グループ *myResourceGroup* 内に、リクエスト本文で値 `"identity":{"type":"UserAssigned"}` によって識別されたユーザー割り当てマネージド ID `ID1` とともに、仮想マシン スケール セット *myVMSS* を作成します。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    **API バージョン 2018-06-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PUT -d '{"sku":{"tier":"Standard","capacity":3,"name":"Standard_D1_v2"},"location":"eastus","identity":{"type":"UserAssigned","userAssignedIdentities":{"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{}}},"properties":{"overprovision":true,"virtualMachineProfile":{"storageProfile":{"imageReference":{"sku":"2016-Datacenter","publisher":"MicrosoftWindowsServer","version":"latest","offer":"WindowsServer"},"osDisk":{"caching":"ReadWrite","managedDisk":{"storageAccountType":"StandardSSD_LRS"},"createOption":"FromImage"}},"osProfile":{"computerNamePrefix":"myVMSS","adminUsername":"azureuser","adminPassword":"myPassword12"},"networkProfile":{"networkInterfaceConfigurations":[{"name":"myVMSS","properties":{"primary":true,"enableIPForwarding":true,"ipConfigurations":[{"name":"myVMSS","properties":{"subnet":{"id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/virtualNetworks/myVnet/subnets/mySubnet"}}}]}}]}},"upgradePolicy":{"mode":"Manual"}}}' -H "Content-Type: application/json" -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PUT https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "sku":{
           "tier":"Standard",
           "capacity":3,
           "name":"Standard_D1_v2"
        },
        "location":"eastus",
        "identity":{
           "type":"UserAssigned",
           "userAssignedIdentities":{
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{
    
              }
           }
        },
        "properties":{
           "overprovision":true,
           "virtualMachineProfile":{
              "storageProfile":{
                 "imageReference":{
                    "sku":"2016-Datacenter",
                    "publisher":"MicrosoftWindowsServer",
                    "version":"latest",
                    "offer":"WindowsServer"
                 },
                 "osDisk":{
                    "caching":"ReadWrite",
                    "managedDisk":{
                       "storageAccountType":"StandardSSD_LRS"
                    },
                    "createOption":"FromImage"
                 }
              },
              "osProfile":{
                 "computerNamePrefix":"myVMSS",
                 "adminUsername":"azureuser",
                 "adminPassword":"myPassword12"
              },
              "networkProfile":{
                 "networkInterfaceConfigurations":[
                    {
                       "name":"myVMSS",
                       "properties":{
                          "primary":true,
                          "enableIPForwarding":true,
                          "ipConfigurations":[
                             {
                                "name":"myVMSS",
                                "properties":{
                                   "subnet":{
                                      "id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/virtualNetworks/myVnet/subnets/mySubnet"
                                   }
                                }
                             }
                          ]
                       }
                    }
                 ]
              }
           },
           "upgradePolicy":{
              "mode":"Manual"
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01' -X PUT -d '{"sku":{"tier":"Standard","capacity":3,"name":"Standard_D1_v2"},"location":"eastus","identity":{"type":"UserAssigned","identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"]},"properties":{"overprovision":true,"virtualMachineProfile":{"storageProfile":{"imageReference":{"sku":"2016-Datacenter","publisher":"MicrosoftWindowsServer","version":"latest","offer":"WindowsServer"},"osDisk":{"caching":"ReadWrite","managedDisk":{"storageAccountType":"StandardSSD_LRS"},"createOption":"FromImage"}},"osProfile":{"computerNamePrefix":"myVMSS","adminUsername":"azureuser","adminPassword":"myPassword12"},"networkProfile":{"networkInterfaceConfigurations":[{"name":"myVMSS","properties":{"primary":true,"enableIPForwarding":true,"ipConfigurations":[{"name":"myVMSS","properties":{"subnet":{"id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/virtualNetworks/myVnet/subnets/mySubnet"}}}]}}]}},"upgradePolicy":{"mode":"Manual"}}}' -H "Content-Type: application/json" -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PUT https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "sku":{
           "tier":"Standard",
           "capacity":3,
           "name":"Standard_D1_v2"
        },
        "location":"eastus",
        "identity":{
           "type":"UserAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"
           ]
        },
        "properties":{
           "overprovision":true,
           "virtualMachineProfile":{
              "storageProfile":{
                 "imageReference":{
                    "sku":"2016-Datacenter",
                    "publisher":"MicrosoftWindowsServer",
                    "version":"latest",
                    "offer":"WindowsServer"
                 },
                 "osDisk":{
                    "caching":"ReadWrite",
                    "managedDisk":{
                       "storageAccountType":"StandardSSD_LRS"
                    },
                    "createOption":"FromImage"
                 }
              },
              "osProfile":{
                 "computerNamePrefix":"myVMSS",
                 "adminUsername":"azureuser",
                 "adminPassword":"myPassword12"
              },
              "networkProfile":{
                 "networkInterfaceConfigurations":[
                    {
                       "name":"myVMSS",
                       "properties":{
                          "primary":true,
                          "enableIPForwarding":true,
                          "ipConfigurations":[
                             {
                                "name":"myVMSS",
                                "properties":{
                                   "subnet":{
                                      "id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Network/virtualNetworks/myVnet/subnets/mySubnet"
                                   }
                                }
                             }
                          ]
                       }
                    }
                 ]
              }
           },
           "upgradePolicy":{
              "mode":"Manual"
           }
        }
     }
    ```

#### 既存のAzure仮想マシン スケール セットにユーザー割り当てマネージド ID を割り当てる

1. Bearer アクセス トークンを取得します。このトークンは、次の手順で、Authorization ヘッダーでシステム割り当てマネージド ID を使用して仮想マシン スケール セットを作成するときに使用します。

    ```azurecli
    az account get-access-token
    ```
2. 「[Create a user assigned managed identity](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-rest#create-a-user-assigned-managed-identity)」(ユーザー割り当てマネージド ID を作成する) に示されている手順を使用して、ユーザー割り当てマネージド ID を作成します。
3. 仮想マシン スケール セットに割り当てられている既存のユーザー割り当てマネージド ID やシステム割り当てマネージド ID を削除しないようにするには、次の CURL コマンドを使用して、仮想マシン スケール セットに割り当てられている ID の種類を一覧表示する必要があります。 仮想マシン スケール セットにマネージド ID が割り当てられている場合、`identity` 値に一覧表示されます。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachineScaleSets/<VMSS NAME>?api-version=2018-06-01' -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    GET https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachineScaleSets/<VMSS NAME>?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |
4. 仮想マシン スケール セットに割り当てられているユーザーまたはシステム割り当てマネージド ID がない場合は、次の CURL コマンドを使用して、Azure Resource Manager REST エンドポイントを呼び出して、最初のユーザー割り当てマネージド ID を仮想マシン スケール セットに割り当てます。 ユーザー割り当てマネージド ID またはシステム割り当てマネージド ID が仮想マシン スケール セットに割り当てられている場合は、システム割り当てマネージド ID を保持しながら、仮想マシン スケール セットに複数のユーザー割り当てマネージド ID を追加する方法を示す手順 5 にスキップします。

    次の例では、`ID1` リソース グループ内の *myVMSS* という仮想マシン スケール セットに、ユーザー割り当てマネージド ID  を割り当てます。 `<ACCESS TOKEN>` は前の手順で Bearer アクセス トークンを要求したときに受け取った値に置き換え、`<SUBSCRIPTION ID>` 値はご利用の環境に合わせて置き換えます。

    **API バージョン 2018-06-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-12-01' -X PATCH -d '{"identity":{"type":"userAssigned", "userAssignedIdentities":{"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{}}}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"userAssigned",
           "userAssignedIdentities":{
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{
    
              }
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01' -X PATCH -d '{"identity":{"type":"userAssigned", "identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"]}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"userAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"
           ]
        }
     }
    ```
5. 仮想マシン スケール セットに既存のユーザー割り当てマネージド ID またはシステム割り当てマネージド ID が割り当てられている場合:

    **API バージョン 2018-06-01**

    ユーザー割り当てマネージド ID を `userAssignedIdentities` ディクショナリ値に追加します。

    たとえば、現在、仮想マシン スケールにシステム割り当てマネージド ID とユーザー割り当てマネージド ID `ID1` が割り当てられており、これにユーザー割り当てマネージド ID `ID2` を追加する場合は、次のようにします。

    ```bash
    curl  'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned, UserAssigned", "userAssignedIdentities":{"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{},"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":{}}}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned, UserAssigned",
           "userAssignedIdentities":{
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1":{
    
              },
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":{
    
              }
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    新しいユーザー割り当てマネージド ID を追加する一方で、保持したいユーザー割り当てマネージド ID は `identityIds` 配列値に入れておいてください。

    たとえば、現在、仮想マシン スケール セットにシステム割り当て ID とユーザー割り当てマネージド ID `ID1` が割り当てられており、これにユーザー割り当てマネージド ID `ID2` を追加する場合は、次のようにします。

    ```bash
    curl  'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01' -X PATCH -d '{"identity":{"type":"SystemAssigned, UserAssigned", "identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1","/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2"]}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned, UserAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1",
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2"
           ]
        }
     }
    ```

#### 仮想マシン スケール セットからユーザー割り当てマネージド ID を削除する

1. Bearer アクセス トークンを取得します。このトークンは、次の手順で、Authorization ヘッダーでシステム割り当てマネージド ID を使用して仮想マシン スケール セットを作成するときに使用します。

    ```azurecli
    az account get-access-token
    ```
2. 仮想マシン スケール セットに割り当てたままにする既存のユーザー割り当てマネージド ID を削除しない、またはシステム割り当てマネージド ID を削除しないようにするには、次の CURL コマンドを使用して、マネージド ID を一覧表示する必要があります。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachineScaleSets/<VMSS NAME>?api-version=2018-06-01' -H "Authorization: Bearer <ACCESS TOKEN>" 
    ```

    ```HTTP
    GET https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Compute/virtualMachineScaleSets/<VMSS NAME>?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    VM にマネージド ID が割り当てられている場合、応答の `identity` 値に一覧表示されます。

    たとえば、ユーザー割り当てマネージド ID `ID1` と `ID2` が仮想マシン スケール セットに割り当てられており、`ID1` を割り当てられたままにしてシステム割り当てマネージド ID を保持する場合:

    **API バージョン 2018-06-01**

    次のように、削除するユーザー割り当てマネージド ID に `null` を追加します。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned, UserAssigned", "userAssignedIdentities":{"/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":null}}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned, UserAssigned",
           "userAssignedIdentities":{
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID2":null
           }
        }
     }
    ```

    **API バージョン 2017-12-01**

    次のように、`identityIds` 配列に維持するユーザー割り当てマネージド ID のみを保持します。

    ```bash
    curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01' -X PATCH -d '{"identity":{"type":"SystemAssigned,UserAssigned", "identityIds":["/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"]}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
    ```

    ```HTTP
    PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2017-12-01 HTTP/1.1
    ```

    **要求ヘッダー**

    | 要求ヘッダー | 説明 |
    | --- | --- |
    | *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
    | *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

    **リクエスト本文**

    ```JSON
     {
        "identity":{
           "type":"SystemAssigned,UserAssigned",
           "identityIds":[
              "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/myResourceGroup/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"
           ]
        }
     }
    ```

仮想マシン スケール セットにシステム割り当てマネージド ID とユーザー割り当てマネージド ID の両方がある場合は、次のコマンドを使用してシステム割り当てマネージド ID のみを使用するように切り替えることによって、すべてのユーザー割り当てマネージド ID を削除できます。

```bash
curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"SystemAssigned"}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
```

```HTTP
PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
```

**要求ヘッダー**

| 要求ヘッダー | 説明 |
| --- | --- |
| *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
| *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

**リクエスト本文**

```JSON
{
   "identity":{
      "type":"SystemAssigned"
   }
}
```

仮想マシン スケール セットにユーザー割り当てマネージド ID のみがあり、そのすべてを削除する場合は、次のコマンドを使用します。

```bash
curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01' -X PATCH -d '{"identity":{"type":"None"}}' -H "Content-Type: application/json" -H Authorization:"Bearer <ACCESS TOKEN>"
```

```HTTP
PATCH https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/myResourceGroup/providers/Microsoft.Compute/virtualMachineScaleSets/myVMSS?api-version=2018-06-01 HTTP/1.1
```

**要求ヘッダー**

| 要求ヘッダー | 説明 |
| --- | --- |
| *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
| *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

**リクエスト本文**

```JSON
{
   "identity":{
      "type":"None"
   }
}
```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-managed-identity-regional-move"} -->
## マネージド ID を別のリージョンに移動する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-managed-identity-regional-move
- Service: entra-id / managed-identities
- Article date: 2023-05-25
- Summary: 別のリージョンでマネージド ID を再作成する手順

既存のユーザー割り当てマネージド ID をリージョン間で移動することが必要になる場合があります。 たとえば、ユーザー割り当てマネージド ID を使用するソリューションを別のリージョンに移動する必要がある場合などです。 また、ディザスター リカバリー計画の一部として、既存の ID を別のリージョンに移動する必要がある場合もあります。

Azure リージョン間でのユーザー割り当てマネージド ID の移動はサポートされていません。 ただし、ターゲット リージョンでユーザー割り当てマネージド ID を再作成することはできます。

### [前提条件]

- 既存のユーザー割り当てマネージド ID に付与されたアクセス許可を一覧表示するアクセス許可。
- 新しいユーザー割り当てマネージド ID に必要なアクセス許可を付与するアクセス許可。
- Azure リソースに新しいユーザー割り当て ID を割り当てるアクセス許可。
- ユーザー割り当てマネージド ID が 1 つ以上のグループのメンバーである場合に、グループ メンバーシップを編集するためのアクセス許可。

### 準備と移動

1. ユーザー割り当てマネージド ID に割り当てられたアクセス許可をコピーします。 [Azure でのロールの割り当て](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-list-powershell)を一覧表示することはできますが、ユーザー割り当てマネージド ID に対するアクセス許可の付与方法によっては、この方法では不十分な場合があります。 ソリューションがサービス固有のオプションを使用して付与されたアクセス許可に依存していないことを確認する必要があります。
2. ターゲット リージョンに[新しいユーザー割り当てマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-powershell#create-a-user-assigned-managed-identity-2) を作成します。
3. マネージド ID に、置き換える元の ID と同じアクセス許可 (グループ メンバーシップを含む) を付与します。 [Azure ロールをマネージド ID に割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal-managed-identity)方法に関するページと、[グループ メンバーシップ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)に関するページを参照してください。
4. 新しく作成されたユーザー割り当てマネージド ID を使用するリソース インスタンスのプロパティで、新しい ID を指定します。

### 確認する

ターゲット リージョンで新しいマネージド ID を使用するようにサービスを再構成した後、すべての操作が復元されたことを確認する必要があります。

### クリーンアップ

サービスがオンラインに戻っていることを確認したら、使用しなくなったソース リージョン内のリソースを削除できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-use-vm-sdk"} -->
## Azure SDK で Azure VM でマネージド ID を使用する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-use-vm-sdk
- Service: entra-id / managed-identities
- Article date: 2023-05-23
- Summary: Azure リソースのマネージド ID を持つ Azure VM で Azure SDK を使用するためのコード サンプル。

この記事では、Azure リソースのマネージド ID に対するそれぞれの Azure SDK のサポートの使用方法を示す SDK サンプルの一覧を示します。

### [前提条件]

- Azure リソースのマネージド ID 機能に慣れていない場合は、こちらの[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を参照してください。 Azure アカウントをお持ちでない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

重要

- この記事のすべてのサンプル コード/スクリプトでは、Azure リソースのマネージド ID が有効になっている VM でクライアントが実行されていることを前提としています。 Azure portal で VM の "接続" 機能を使用して、VM にリモート接続します。 VM で Azure リソースのマネージド ID を有効にする方法の詳細については、「[Azure Portal を使用して VM 上に Azure リソースのマネージド ID を構成する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm)」、または関連する記事 (PowerShell、CLI、テンプレート、または Azure SDK の使用) のいずれかを参照してください。

### SDK コード サンプル

| SDK | コード サンプル |
| --- | --- |
| .NET | [Azure リソースのマネージド ID を使用して Windows VM から Azure Resource Manager テンプレートをデプロイする](https://github.com/Azure-Samples/windowsvm-msi-arm-dotnet) |
| .NET コア | [Azure リソースのマネージド ID を使用して Linux VM から Azure サービスを呼び出す](https://github.com/Azure-Samples/linuxvm-msi-keyvault-arm-dotnet/) |
| Go | [Go 用 Azure ID クライアント モジュール](https://pkg.go.dev/github.com/Azure/azure-sdk-for-go/sdk/azidentity#ManagedIdentityCredential) |
| Node.js | [Azure リソースのマネージド ID を使用してリソースを管理する](https://github.com/Azure-Samples/resources-node-manage-resources-with-msi) |
| Python | [Azure リソースのマネージド ID を使用して VM 内から認証する](https://github.com/Azure/azure-sdk-for-python/tree/azure-identity_1.15.0/sdk/identity/azure-identity/) |
| Ruby | [Azure リソースのマネージド ID が有効になっている VM からリソースを管理する](https://github.com/Azure-Samples/resources-ruby-manage-resources-with-msi/) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-use-vm-sign-in"} -->
## サインイン V にAzure VM でマネージド ID を使用する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-use-vm-sign-in
- Service: entra-id / managed-identities
- Article date: 2022-01-11
- Summary: スクリプト クライアントのサインインとリソース アクセスにAzureリソース サービス プリンシパルにAzure VM マネージド ID を使用する手順と例。

この記事では、Azure リソース サービス プリンシパルのマネージド ID を使用してサインインするための PowerShell および CLI スクリプトの例と、エラー処理などの重要なトピックに関するガイダンスを提供します。

注

Azure Az PowerShell モジュールを使用してAzureを操作することをお勧めします。 開始するには、[Install Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) を参照してください。 Az PowerShell モジュールに移行する方法については、「AzRM から Azを参照してください。

### [前提条件]

- Azure リソースのマネージド ID 機能に慣れていない場合は、この[overview](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を参照してください。 Azureアカウントをお持ちでない場合は、続行する前に[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップしてください。

この記事のAzure PowerShellまたはAzure CLIの例を使用する場合は、必ず最新バージョンの [Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) または [Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) をインストールしてください。

Von Bedeutung

- この記事のすべてのサンプル スクリプトでは、Azure リソースのマネージド ID が有効になっている VM でコマンド ライン クライアントが実行されていることを前提としています。 Azure ポータルで VM の "接続" 機能を使用して、VM にリモート接続します。 VM でAzure リソースのマネージド ID を有効にする方法の詳細については、「Azure ポータルを使用して VM 上のAzure リソースのマネージド ID を構成するまたはバリアント記事の 1 つ (PowerShell、CLI、テンプレート、またはAzure SDKを使用) を参照してください。
- リソース アクセス中にエラーが発生しないようにするには、VM でAzure Resource Manager操作を許可するために、適切なスコープ (VM 以上) で VM のマネージド ID に少なくとも "閲覧者" アクセス権を付与する必要があります。 詳細については、「[Azure ポータルを使用して Azure リソースにアクセスするためのマネージド ID の割り当て](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/grant-managed-identity-resource-access-azure-portal)」を参照してください。

### 概要

Azure リソースのマネージド ID は、サービス プリンシパル オブジェクトを提供します。これは、VM でAzure リソースのマネージド ID を有効にすると作成されます。 サービス プリンシパルには、Azure リソースへのアクセス権を付与し、サインインおよびリソース アクセス用のスクリプト/コマンド ライン クライアントによって ID として使用できます。 従来、セキュリティで保護されたリソースに独自の ID でアクセスするには、スクリプト クライアントで次の操作を行う必要があります。

- Microsoft Entra IDに機密/Web クライアント アプリケーションとして登録および同意する
- アプリの資格情報 (スクリプトに埋め込まれている可能性が高い) を使用して、サービス プリンシパルとしてサインインします。

Azure リソースのマネージド ID では、スクリプト クライアントは、Azure リソース サービス プリンシパルのマネージド ID でサインインできるため、どちらも行う必要がなくなります。

### Azure CLI

次のスクリプトは、次の方法を示しています。

1. Azure リソース用サービス プリンシパルの VM におけるマネージド IDとして Microsoft Entra ID にサインインします。
2. Azure Resource Managerを呼び出し、VM のサービス プリンシパル ID を取得します。 CLI は、トークンの取得/使用を自動的に管理します。 仮想マシン名は必ず `<VM-NAME>`に置き換える必要があります。

    ```azurecli
    az login --identity
    
    $spID=$(az resource list -n <VM-NAME> --query [*].identity.principalId --out tsv)
    echo The managed identity for Azure resources service principal ID is $spID
    ```

### Azure PowerShell

次のスクリプトは、次の方法を示しています。

1. VM のマネージド ID を使用して、Azure リソース サービス プリンシパルの Microsoft Entra ID にサインインします
2. Azure Resource Manager コマンドレットを呼び出して、VM に関する情報を取得します。 PowerShell では、トークンの使用を自動的に管理します。

    ```azurepowershell
    Add-AzAccount -identity
    
    # Call Azure Resource Manager to get the service principal ID for the VM's managed identity for Azure resources. 
    $vmInfoPs = Get-AzVM -ResourceGroupName <RESOURCE-GROUP> -Name <VM-NAME>
    $spID = $vmInfoPs.Identity.PrincipalId
    echo "The managed identity for Azure resources service principal ID is $spID"
    ```

### Azure サービスのリソース ID

Azure リソースのマネージド ID でテストされた Microsoft Entra ID をサポートするリソースの一覧およびそれぞれのリソース ID については、[Microsoft Entra 認証をサポートする Azure サービス](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status) を参照してください。

### エラー処理に関するガイダンス

次のような応答は、Azure リソースの VM のマネージド ID が正しく構成されていないことを示している可能性があります。

- PowerShell: *Invoke-WebRequest: リモート サーバーに接続できません*
- CLI: *MSI: 'HTTPConnectionPool(host='localhost', port=50342) というエラーで `http://localhost:50342/oauth2/token` からトークンを取得できませんでした*

これらのエラーのいずれかが発生した場合は、[Azure ポータル](https://portal.azure.com)でAzure VM に戻り、**Identity** ページに移動し、**System assigned** が "Yes" に設定されていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-use-vm-token"} -->
## 仮想マシン上でマネージド ID を使用してアクセス トークンを取得する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-use-vm-token
- Service: entra-id / managed-identities
- Article date: 2025-11-11
- Summary: 仮想マシン上で Azure リソースのマネージド ID を使用して OAuth アクセス トークンを取得する手順と例について説明します。

Azure リソースのマネージド ID には、Azure サービス向けの、Microsoft Entra ID で自動的に管理される ID が用意されています。 この ID を使用すると、コード内に資格情報を記述することなく、Microsoft Entra の認証をサポートする任意のサービスに対して認証を行うことができます。

この記事では、トークンの取得に使用する各種コードとスクリプトの例を提供します。 また、トークンの有効期限や HTTP エラーの処理についてのガイダンスも提供します。

### [前提条件]

- Azure リソースのマネージド ID 機能に慣れていない場合は、こちらの[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を参照してください。 Azure アカウントをお持ちでない場合は、[無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

この記事の Azure PowerShell の例を使用する場合は、[Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) の最新バージョンをインストールする必要があります。

Von Bedeutung

- この記事のすべてのサンプル コード/スクリプトは、Azure リソースのマネージド ID を使用する仮想マシン上でクライアントが実行されていることを前提としています。 お使いの VM にリモート接続するには、Azure portal で仮想マシンへの "接続" 機能を使用します。 VM で Azure リソースのマネージド ID を有効にする方法の詳細については、「[Azure Portal を使用して VM 上に Azure リソースのマネージド ID を構成する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm)」、または関連する記事 (PowerShell、CLI、テンプレート、または Azure SDK の使用) のいずれかを参照してください。

Von Bedeutung

- Azure リソースのマネージド ID のセキュリティ境界は、ID が使用されているリソースです。 仮想マシン上で実行されるすべてのコード/スクリプトは、そこで使用できる任意のマネージド ID のトークンを要求して取得できます。

### 概要

クライアント アプリケーションは、特定のリソースにアクセスするために、マネージド ID の[アプリ専用アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#access-token)を要求できます。 トークンは、[Azure リソース サービス プリンシパルのマネージド ID に基づいています](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)。 そのため、クライアントが、独自のサービス プリンシパルでアクセス トークンを取得する必要はありません。 トークンは、[クライアント資格情報を必要とするサービス間の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)のベアラー トークンとしての使用に適しています。

| リンク | 説明 |
| --- | --- |
| HTTP を使用してトークンを取得する | Azure リソース トークン エンドポイントのマネージド ID に関するプロトコルの詳細 |
| Azure.Identity を使用してトークンを取得する | Azure.Identity を使用した C# クライアントからの Azure リソース REST エンドポイントのマネージド ID の使用例 |
| C# を使用してトークンを取得する | HttpClient を使用した C# クライアントからの Azure リソース REST エンドポイントのマネージド ID の使用例 |
| Java を使用してトークンを取得する | Java クライアントから Azure リソース REST エンドポイントのマネージド ID を使用する例 |
| Go を使用してトークンを取得する | Go クライアントから Azure リソース REST エンドポイントのマネージド ID を使用する例 |
| PowerShell を使用してトークンを取得する | PowerShell クライアントから Azure リソース REST エンドポイントのマネージド ID を使用する例 |
| CURL を使用してトークンを取得する | Bash/CURL クライアントから Azure リソース REST エンドポイントのマネージド ID を使用する例 |
| トークンのキャッシュの処理 | 有効期限が切れたアクセス トークンの処理に関するガイダンス |
| エラー処理 | Azure リソース トークン エンドポイントのマネージド ID から返される HTTP エラーを処理するためのガイダンス |
| Azure サービスのリソース ID | サポートされている Azure サービスのリソース ID を取得する場所 |

### HTTP を使用してトークンを取得する

アクセス トークンの取得に使用する基本的なインターフェイスは REST に基づいているため、HTTP REST の呼び出しを行える VM 上で実行されている、すべてのクライアント アプリケーションにアクセスできます。 この方法は、クライアントが仮想マシン上のエンドポイントを使用する点以外は、Microsoft Entra のプログラミング モデルと同じです (Microsoft Entra のプログラミング モデルでは、Microsoft Entra エンドポイントを使用)。

Azure Instance Metadata Service (IMDS) エンドポイントを使用するサンプル要求 *(推奨)* :

```
GET 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https://management.azure.com/' HTTP/1.1 Metadata: true
```

| 要素 | 説明 |
| --- | --- |
| `GET` | HTTP 動詞。エンドポイントからデータを取得する必要があることを示します。 この例では、OAuth アクセス トークンです。 |
| `http://169.254.169.254/metadata/identity/oauth2/token` | Instance Metadata Service 用の Azure リソース エンドポイントのマネージド ID。 |
| `api-version` | クエリ文字列パラメーター。IMDS エンドポイントの API バージョンです。 API バージョン `2018-02-01` 以上を使用してください。 |
| `resource` | クエリ文字列パラメーター。ターゲット リソースのアプリ ID URI です。 発行されたトークンの `aud` (audience) 要求にも表示されます。 この例では、アプリ ID URI が `https://management.azure.com/` の Azure Resource Manager にアクセスするためのトークンを要求しています。 |
| `Metadata` | マネージド ID に必要な HTTP 要求ヘッダー フィールド。 この情報は、サーバー側のリクエスト フォージェリ (SSRF) 攻撃に対する軽減策として使用されます。 この値は、"true" に設定し、すべて小文字にする必要があります。 |
| `object_id` | (省略可能) クエリの文字列パラメーター。トークン用の管理対象 ID の object\_id を示します。 VM に複数のユーザーが割り当てたマネージド ID がある場合は必須です。 |
| `client_id` | (省略可能) クエリの文字列パラメーター。トークン用の管理対象 ID の client\_id を示します。 VM に複数のユーザーが割り当てたマネージド ID がある場合は必須です。 |
| `msi_res_id` | (省略可能) クエリの文字列パラメーター。トークン用の管理対象 ID の msi\_res\_id (Azure リソース ID) を示します。 VM に複数のユーザーが割り当てたマネージド ID がある場合は必須です。 |

応答の例:

```json
HTTP/1.1 200 OK
Content-Type: application/json
{
  "access_token": "eyJ0eXAi...",
  "refresh_token": "",
  "expires_in": "3599",
  "expires_on": "1506484173",
  "not_before": "1506480273",
  "resource": "https://management.azure.com/",
  "token_type": "Bearer"
}
```

| 要素 | 説明 |
| --- | --- |
| `access_token` | 要求されたアクセス トークン。 セキュリティで保護された REST API を呼び出すとき、トークンは `Authorization` 要求ヘッダー フィールドに "ベアラー" トークンとして埋め込まれ、API が呼び出し元を認証できるようにします。 |
| `refresh_token` | Azure リソースのマネージド ID には使用されません。 |
| `expires_in` | 発行時から有効期限が切れる前に、アクセス トークンが引き続き有効な秒数。 発行の時刻は、トークンの `iat` 要求で確認できます。 |
| `expires_on` | アクセス トークンの有効期限が切れる期間。 日付は、"1970-01-01T0:0:0Z UTC" からの秒数として表されます (トークンの `exp` 要求に対応します)。 |
| `not_before` | アクセス トークンが有効になり、承認されるまでの期間。 日付は、"1970-01-01T0:0:0Z UTC" からの秒数として表されます (トークンの `nbf` 要求に対応します)。 |
| `resource` | アクセス トークンの要求対象リソース。要求の `resource` クエリ文字列パラメーターと一致します。 |
| `token_type` | トークンの種類。"ベアラー" アクセス トークンです。これは、リソースがこのトークンのベアラーへのアクセス権を付与できることを意味します。 |

### Azure Identity クライアント ライブラリを使用してトークンを取得する

マネージド ID を使用するには、Azure ID クライアント ライブラリを使用することをお勧めします。 次の手順を実行します。

1. [Azure.Identity](https://www.nuget.org/packages/Azure.Identity) パッケージと、[Azure.Security.KeyVault.Secrets](https://aka.ms/azsdk) などの他の必要な [Azure SDK ライブラリ パッケージ](https://www.nuget.org/packages/Azure.Security.KeyVault.Secrets/)をインストールします。
2. 下のサンプル コードを使用します。 トークンの取得について心配する必要はありません。 Azure SDK クライアントを直接使用できます。 このコードは、必要に応じてトークンを取得する方法を示すものです。

    ```csharp
    using Azure.Core;
    using Azure.Identity;
    using Azure.Security.KeyVault.Secrets;
    
    ManagedIdentityCredential credential = new(
        ManagedIdentityId.FromUserAssignedClientId("<managed_identity_client_ID>"));
    
    // Option 1: Explicit token acquisition. Manually fetch the token and convert to a string, if necessary.
    AccessToken accessToken = await credential.GetTokenAsync(
        new TokenRequestContext(["https://vault.azure.net"]));
    string accessTokenString = accessToken.Token;
    
    // Option 2: Implicit token acquisition. Pass the credential object to the Azure service client constructor.
    // Token acquisition is triggered on the GetSecretAsync method call.
    SecretClient client = new(new Uri("https://myvault.vault.azure.net/"), credential);
    KeyVaultSecret secret = await client.GetSecretAsync("MySecret");
    ```

詳細については、「 [ユーザー割り当てマネージド ID の使用](https://learn.microsoft.com/ja-jp/dotnet/azure/sdk/authentication/user-assigned-managed-identity) 」および [「システム割り当てマネージド ID の使用」を](https://learn.microsoft.com/ja-jp/dotnet/azure/sdk/authentication/system-assigned-managed-identity)参照してください。

### C# を使用してトークンを取得する

```csharp
using System;
using System.Net.Http;
using Newtonsoft.Json.Linq;

// Construct HttpClient
var httpClient = new HttpClient
{
    DefaultRequestHeaders =
    {
        { "Metadata", Boolean.TrueString }
    }
};

// Construct URI to call
var resource = "https://management.azure.com/";
var uri = $"http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource={resource}";

// Make call
var response = await httpClient.GetAsync(uri);
try
{
    response.EnsureSuccessStatusCode();
}
catch (HttpRequestException)
{
    var error = await response.Content.ReadAsStringAsync();
    Console.WriteLine(error);
    throw;
}

// Parse response using Newtonsoft.Json
var content = await response.Content.ReadAsStringAsync();
var obj = JObject.Parse(content);
var accessToken = obj["access_token"];

Console.WriteLine(accessToken);
```

### Java を使用してトークンを取得する

Java を使用してトークンを取得するには、この [JSON ライブラリ](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.9.4)を使用します。

```Java
import java.io.*;
import java.net.*;
import com.fasterxml.jackson.core.*;
 
class GetMSIToken {
    public static void main(String[] args) throws Exception {
 
        URL msiEndpoint = new URL("http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https://management.azure.com/");
        HttpURLConnection con = (HttpURLConnection) msiEndpoint.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Metadata", "true");
 
        if (con.getResponseCode()!=200) {
            throw new Exception("Error calling managed identity token endpoint.");
        }
 
        InputStream responseStream = con.getInputStream();
 
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(responseStream);
 
        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();
 
            if(JsonToken.FIELD_NAME.equals(jsonToken)){
                String fieldName = parser.getCurrentName();
                jsonToken = parser.nextToken();
 
                if("access_token".equals(fieldName)){
                    String accesstoken = parser.getValueAsString();
                    System.out.println("Access Token: " + accesstoken.substring(0,5)+ "..." + accesstoken.substring(accesstoken.length()-5));
                    return;
                }
            }
        }
    }
}
```

### Go を使用してトークンを取得する

```go
package main

import (
  "fmt"
  "io/ioutil"
  "net/http"
  "net/url"
  "encoding/json"
)

type responseJson struct {
  AccessToken string `json:"access_token"`
  RefreshToken string `json:"refresh_token"`
  ExpiresIn string `json:"expires_in"`
  ExpiresOn string `json:"expires_on"`
  NotBefore string `json:"not_before"`
  Resource string `json:"resource"`
  TokenType string `json:"token_type"`
}

func main() {
    
    // Create HTTP request for a managed services for Azure resources token to access Azure Resource Manager
    var msi_endpoint *url.URL
    msi_endpoint, err := url.Parse("http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01")
    if err != nil {
      fmt.Println("Error creating URL: ", err)
      return 
    }
    msi_parameters := msi_endpoint.Query()
    msi_parameters.Add("resource", "https://management.azure.com/")
    msi_endpoint.RawQuery = msi_parameters.Encode()
    req, err := http.NewRequest("GET", msi_endpoint.String(), nil)
    if err != nil {
      fmt.Println("Error creating HTTP request: ", err)
      return 
    }
    req.Header.Add("Metadata", "true")

    // Call managed services for Azure resources token endpoint
    client := &http.Client{}
    resp, err := client.Do(req) 
    if err != nil{
      fmt.Println("Error calling token endpoint: ", err)
      return
    }

    // Pull out response body
    responseBytes,err := ioutil.ReadAll(resp.Body)
    defer resp.Body.Close()
    if err != nil {
      fmt.Println("Error reading response body : ", err)
      return
    }

    // Unmarshall response body into struct
    var r responseJson
    err = json.Unmarshal(responseBytes, &r)
    if err != nil {
      fmt.Println("Error unmarshalling the response:", err)
      return
    }

    // Print HTTP response and marshalled response body elements to console
    fmt.Println("Response status:", resp.Status)
    fmt.Println("access_token: ", r.AccessToken)
    fmt.Println("refresh_token: ", r.RefreshToken)
    fmt.Println("expires_in: ", r.ExpiresIn)
    fmt.Println("expires_on: ", r.ExpiresOn)
    fmt.Println("not_before: ", r.NotBefore)
    fmt.Println("resource: ", r.Resource)
    fmt.Println("token_type: ", r.TokenType)
}
```

### PowerShell を使用してトークンを取得する

次の例では、PowerShell クライアントから Azure リソース REST エンドポイントのマネージド ID を使用して、以下を実行する方法を示します。

1. アクセス トークンを取得します。
2. アクセス トークンを使用して Azure Resource Manager の REST API を呼び出し、VM に関する情報を取得します。 `<SUBSCRIPTION-ID>`、`<RESOURCE-GROUP>`、および `<VM-NAME>` の代わりに、ご自分のサブスクリプション ID、リソース グループ名、および仮想マシン名をそれぞれ使用する必要があります。

```azurepowershell
Invoke-RestMethod -Method GET -Uri 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fmanagement.azure.com%2F' -Headers @{Metadata="true"}
```

応答からアクセス トークンを解析する方法の例を次に示します。

```azurepowershell
# Get an access token for managed identities for Azure resources
$resource = 'https://management.azure.com'
$response = Invoke-RestMethod -Method GET `
                            -Uri "http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=$resource" `
                            -Headers @{ Metadata="true" }
$accessToken = $response.access_token
Write-Host "Access token using a User-Assigned Managed Identity is $accessToken"

# Use the access token to get resource information for the VM
$secureToken = $accessToken | ConvertTo-SecureString -AsPlainText
$vmInfoRest = Invoke-RestMethod -Method GET `
                              -Uri 'https://management.azure.com/subscriptions/<SUBSCRIPTION-ID>/resourceGroups/<RESOURCE-GROUP>/providers/Microsoft.Compute/virtualMachines/<VM-NAME>?api-version=2017-12-01' `
                              -ContentType 'application/json' `
                              -Authentication Bearer `
                              -Token $secureToken
Write-Host "JSON returned from call to get VM info: $($vmInfoRest.content)"

```

### CURL を使用してトークンを取得する

```bash
curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fmanagement.azure.com%2F' -H Metadata:true -s
```

応答からアクセス トークンを解析する方法の例を次に示します。

```bash
response=$(curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fmanagement.azure.com%2F' -H Metadata:true -s)
access_token=$(echo $response | python -c 'import sys, json; print (json.load(sys.stdin)["access_token"])')
echo Access token using a User-Assigned Managed Identity is $access_token
```

### トークンのキャッシュ

マネージド ID サブシステムによってトークンがキャッシュされますが、コードにトークン キャッシュを実装することをお勧めします。 リソースによってトークンの有効期限切れが示されるシナリオに備える必要があります。

ネットワークを介した Microsoft Entra ID の呼び出しは、次の場合にのみ行われます。

- Azure リソース サブシステム キャッシュのマネージド ID にトークンがないためキャッシュ ミスが発生する。
- キャッシュのトークンの有効期限が切れている。

### エラー処理

マネージド ID エンドポイントは、HTTP 応答メッセージのヘッダーに含まれる状態コード フィールドを通じて、4xx エラーまたは 5xx エラーのいずれかとして、エラーを通知します。

| 状態コード | エラーの理由 | 処理方法 |
| --- | --- | --- |
| 404 見つかりません。 | IMDS エンドポイントが更新されています。 | 指数バックオフを使用して再試行してください。 以下のガイダンスを参照してください。 |
| 410 | IMDS は更新プログラムを実行しています | IMDS は 70 秒以内に使用可能になります |
| 429 要求が多すぎます。 | IMDS スロットルの上限に達しました。 | 指数バックオフを使用して再試行してください。 以下のガイダンスを参照してください。 |
| 要求の 4xx エラーです。 | 1 つ以上の要求パラメーターが正しくありませんでした。 | 再試行しないでください。 詳しくは、エラーの詳細を確認します。 4xx エラーは、デザイン時のエラーです。 |
| サービスからの 5xx の一時的なエラーです。 | Azure リソース サブシステムまたは Microsoft Entra ID のマネージド ID から一時的なエラーが返されました。 | 少なくとも 1 秒間待機した後に、安全に再試行できます。 再試行が早すぎる場合や再試行の回数が多すぎる場合は、IMDS および Microsoft Entra ID からレート制限エラー (429) が返されることがあります。 |
| タイムアウトになる | IMDS エンドポイントが更新されています。 | 指数バックオフを使用して再試行してください。 後でガイダンスを参照してください。 |

エラーが発生すると、対応する HTTP 応答本文に、JSON とエラーの詳細が含まれます。

| 要素 | 説明 |
| --- | --- |
| エラー | エラー識別子。 |
| エラーの説明 | エラーの詳細な説明。 **エラーの説明は、予告なく変更になる場合があります。 エラーの説明に含まれる値に基づいて分岐するコードを作成しないでください。** |

#### HTTP 応答リファレンス

このセクションでは、想定されるエラー応答について説明します。 "200 OK" の状態は成功応答であり、access\_token 要素内の応答本文の JSON にアクセス トークンが含まれています。

| 状態コード | エラー | エラーの説明 | 解決策 |
| --- | --- | --- | --- |
| 400 無効な要求 | 無効なリソース | AADSTS50001: *&lt;URI&gt;* という名前のアプリケーションが *&lt;TENANT-ID&gt;* という名前のテナントに見つかりませんでした。 このメッセージは、テナント管理者がアプリケーションをインストールしていない場合、またはテナント ユーザーが同意していない場合に表示されます。 間違ったテナントに認証要求を送信した可能性があります。\ | (Linux のみ) |
| 400 無効な要求 | bad\_request\_102 | 必要なメタデータ ヘッダーが指定されていません | 要求で `Metadata` 要求ヘッダー フィールドが見つからないか、形式が正しくありません。 値は `true` として指定し、すべて小文字にする必要があります。 例については、上記の「REST」セクションの「要求のサンプル」を参照してください。 |
| 401 権限がありません | unknown\_source | 不明なソース *&lt;URI&gt;* | HTTP GET 要求の URI の形式が正しいことを確認します。 `scheme:host/resource-path` 部分は、`http://localhost:50342/oauth2/token` として指定する必要があります。 例については、上記の「REST」セクションの「要求のサンプル」を参照してください。 |
|  | 無効なリクエスト | 要求に必要なパラメーターが含まれていないか、要求に無効なパラメーター値が含まれているか、要求に複数回パラメーターが含まれているか、要求の形式が正しくないかのいずれかです。 |  |
|  | 認証されていないクライアント | クライアントには、このメソッドを使用してアクセス トークンを要求する権限がありません。 | Azure リソースのマネージド ID が正しく構成されていない VM での要求が原因です。 VM の構成についてサポートが必要な場合は、「[Azure portal を使用して VM 上に Azure リソースのマネージド ID を構成する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm)」を参照してください。 |
|  | アクセスが拒否されました | リソース所有者または承認サーバーによって、要求が拒否されました。 |  |
|  | サポートされていないレスポンスタイプ | このメソッドを使用したアクセス トークンの取得は、承認サーバーによってサポートされていません。 |  |
|  | invalid\_scope | 要求されたスコープが無効、不明、または形式が正しくありません。 |  |
| 500 内部サーバー エラー | 不明 | Active Directory からのトークンの取得に失敗しました。 詳細については、*&lt;file path&gt; のログを参照してください*" | VM で、Azure リソースのマネージド ID が有効になっていることを確認します。 VM の構成についてサポートが必要な場合は、「[Azure portal を使用して VM 上に Azure リソースのマネージド ID を構成する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm)」を参照してください。また、HTTP GET 要求の URI、特にクエリ文字列で指定されたリソース URI の形式が正しいかどうかを確認します。 例については、上記の「REST」セクションの「要求のサンプル」を参照してください。または、「[Microsoft Entra 認証をサポートしている Azure サービス](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)」で、サービスの一覧と、そのリソース ID を参照してください。 |

Von Bedeutung

- IMDS をプロキシの背後で使用することは想定されておらず、サポートされていません。 プロキシをバイパスする方法の例については、「[Azure Instance Metadata Samples (Azure インスタンス メタデータ サンプル)](https://github.com/microsoft/azureimds)」をご覧ください。

### 再試行のガイダンス

404、429、または 5xx エラー コードが表示される場合、再試行してください (「エラー処理」を参照)。 410 エラーが表示された場合は、IMDS が更新中であり、最大 70 秒で使用可能になることを示しています。

スロットル制限は、IMDS エンドポイントに対して行われる呼び出し回数に適用されます。 スロットルのしきい値を超えた場合、IMDS エンドポイントは、スロットルが有効な状態にあっても、それ以降の要求を制限します。 この期間中、IMDS エンドポイントは HTTP 状態コード 429 ("要求が多すぎます") を返し、要求は失敗します。

再試行については、次の方法をお勧めします。

| **再試行戦略** | **[設定]** | **値** | **機能** |
| --- | --- | --- | --- |
| ExponentialBackoff | 再試行回数最小バックオフ最大バックオフ差分バックオフ最初の高速再試行 | 50 秒60 秒2 秒偽り | 試行 1 - 0 秒の遅延試行 2 - 最大 2 秒の遅延試行 3 - 最大 6 秒の遅延試行 4 - 最大 14 秒の遅延試行 5 - 最大 30 秒の遅延 |

### Azure サービスのリソース ID

Azure リソースのマネージド ID をサポートするリソースの一覧については、[マネージド ID がサポートされている Azure サービス](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-view-associated-resources-for-an-identity"} -->
## ユーザー割り当てマネージド ID の関連リソースを表示する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-view-associated-resources-for-an-identity
- Service: entra-id / managed-identities
- Article date: 2025-03-15
- Summary: ユーザー割り当てマネージド ID に関連付けられている Azure リソースを表示するための詳細な手順

この記事では、ユーザー割り当てマネージド ID に関連付けられている Azure リソースを表示する方法について説明します。 この機能はパブリック プレビューで利用できます。

### [前提条件]

- Azure リソースのマネージド ID の基本点な事柄については、[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するセクションを参照してください。
- まだ Azure アカウントを持っていない場合は、[無料アカウントを新規登録](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### ユーザー割り当てマネージド ID のリソースを表示する

ユーザー割り当てマネージド ID に関連付けられている Azure リソースをすばやく確認できるため、環境の可視性が向上します。 安全に削除できる未使用の ID をすばやく識別し、マネージド ID のアクセス許可またはグループ メンバーシップを変更することで、影響を受けるリソースを把握できます。

#### Portal

- **Azure portal** から**マネージド ID を検索します**。
- マネージド ID を選択する
- 左側のメニューで、[ **関連付けられているリソース** ] リンクを選択します。
- マネージド ID に関連付けられている Azure リソースの一覧が表示されます

[Image: ユーザー割り当てマネージド ID に関連付けられているリソースの一覧を示すスクリーンショット。]

概要ページに移動するリソース名を選択します。

##### リソースの種類によるフィルター処理と並べ替え

概要ページの上部にあるフィルター ボックスに入力して、リソースをフィルター処理します。 名前、種類、リソース グループ、サブスクリプション ID でフィルター処理できます。

列のタイトルを選択して、アルファベット順、昇順、または降順に並べ替えます。

#### REST API

関連付けられているリソースの一覧には、REST API を使用してアクセスすることもできます。 このエンドポイントは、ユーザー割り当てマネージド ID の一覧を取得するために使用される API エンドポイントとは別です。 次の情報が必要です。

- サブスクリプション ID
- リソースを表示するユーザー割り当てマネージド ID のリソース名
- ユーザー割り当てマネージド ID のリソース グループ

*要求の形式*

```
https://management.azure.com/subscriptions/{resourceID of user-assigned identity}/listAssociatedResources?$filter={filter}&$orderby={orderby}&$skip={skip}&$top={top}&$skiptoken={skiptoken}&api-version=2021-09-30-preview 
```

*パラメーター*

| パラメーター | 例 | 説明 |
| --- | --- | --- |
| $フィルター | `type eq 'microsoft.cognitiveservices/account' and contains(name, 'test')` | 使用可能なフィールド (名前、型、resourceGroup、subscriptionId、subscriptionDisplayName) をフィルター処理できる OData 式次の操作がサポートされています: `and`、 `or`、 `eq` 、および `contains` |
| $orderby | `name asc` | 使用可能な任意のフィールドで並べ替え可能な OData 式 |
| $skip | 50 | 結果のページング中にスキップする項目の数。 |
| $top | 10 | 返されるリソースの数。 0 は、リソースの数のみを返します。 |

REST API に対するサンプル要求を確認できます。

```http
POST https://management.azure.com/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/resourceGroups/devrg/providers/Microsoft.ManagedIdentity/userAssignedIdentities/devIdentity/listAssociatedResources?$filter={filter}&$orderby={orderby}&$skip={skip}&$top={top}&skiptoken={skiptoken}&api-version=2021-09-30-preview 
```

REST API からのサンプル応答に注目してください。

```json
{
  "totalCount": 2,
  "value": [
    {
      "id": "/subscriptions/{subId}/resourceGroups/testrg/providers/Microsoft.CognitiveServices/accounts/test1",
      "name": "test1",
      "type": "microsoft.cognitiveservices/accounts",
      "resourceGroup": "testrg",
      "subscriptionId": "{subId}",
      "subscriptionDisplayName": "TestSubscription"
    },
    {
      "id": "/subscriptions/{subId}/resourceGroups/testrg/providers/Microsoft.CognitiveServices/accounts/test2",
      "name": "test2",
      "type": "microsoft.cognitiveservices/accounts",
      "resourceGroup": "testrg",
      "subscriptionId": "{subId}",
      "subscriptionDisplayName": "TestSubscription"
    }
  ],
  "nextLink": "https://management.azure.com/subscriptions/{subId}/resourceGroups/testrg/providers/Microsoft.ManagedIdentity/userAssignedIdentities/testid?skiptoken=ew0KICAiJGlkIjogIjEiLA0KICAiTWF4Um93cyI6IDIsDQogICJSb3dzVG9Ta2lwIjogMiwNCiAgIkt1c3RvQ2x1c3RlclVybCI6ICJodHRwczovL2FybXRvcG9sb2d5Lmt1c3RvLndpbmRvd3MubmV0Ig0KfQ%253d%253d&api-version=2021"
}

```

#### コマンド ライン インターフェイス

ユーザー割り当てマネージド ID の関連リソースを表示するには、次のコマンドを実行します。

```azurecli
az identity list-resources --resource-group <ResourceGroupName> --name <ManagedIdentityName>
```

応答は次のようになります。

```json
[
  {
    "id": "/subscriptions/XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130/resourceGroups/ProductionServices/providers/Microsoft.Compute/virtualMachines/linux-prod-1-US",
    "name": "linux-prod-1-US",
    "resourceGroup": "productionservices",
    "subscriptionDisplayName": "Visual Studio Enterprise Subscription",
    "subscriptionId": "XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130",
    "type": "microsoft.compute/virtualmachines"
  },
  {
    "id": "/subscriptions/XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130/resourceGroups/ProductionServices/providers/Microsoft.Web/sites/prodStatusCheck-US",
    "name": "prodStatusCheck-US",
    "resourceGroup": "productionservices",
    "subscriptionDisplayName": "Visual Studio Enterprise Subscription",
    "subscriptionId": "XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130",
    "type": "microsoft.web/sites"
  },
  {
    "id": "/subscriptions/XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130/resourceGroups/ProductionServices/providers/Microsoft.Web/sites/salesApp-US-1",
    "name": "salesApp-US-1",
    "resourceGroup": "productionservices",
    "subscriptionDisplayName": "Visual Studio Enterprise Subscription",
    "subscriptionId": "XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130",
    "type": "microsoft.web/sites"
  },
  {
    "id": "/subscriptions/XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130/resourceGroups/ProductionServices/providers/Microsoft.Web/sites/salesPortal-us-2",
    "name": "salesPortal-us-2",
    "resourceGroup": "productionservices",
    "subscriptionDisplayName": "Visual Studio Enterprise Subscription",
    "subscriptionId": "XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130",
    "type": "microsoft.web/sites"
  },
  {
    "id": "/subscriptions/XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130/resourceGroups/vmss/providers/Microsoft.Compute/virtualMachineScaleSets/vmsstest",
    "name": "vmsstest",
    "resourceGroup": "vmss",
    "subscriptionDisplayName": "Visual Studio Enterprise Subscription",
    "subscriptionId": "XXXX-XXXX-XXXX-XXXX-XXXfc47ab8130",
    "type": "microsoft.compute/virtualmachinescalesets"
  }
]
```

#### PowerShell を使用した REST API

マネージド ID の関連付けられているリソースを返す特定の PowerShell コマンドはありませんが、次のコマンドを使用して PowerShell で REST API を使用できます。

```PowerShell
Invoke-AzRestMethod -Path "/subscriptions/XXX-XXX-XXX-XXX/resourceGroups/test-rg/providers/Microsoft.ManagedIdentity/userAssignedIdentities/test-identity-name/listAssociatedResources?api-version=2021-09-30-PREVIEW&%24orderby=name%20asc&%24skip=0&%24top=100" -Method Post
```

注

ユーザーのアクセス許可に関係なく、ID に関連付けられているすべてのリソースが返されます。 ユーザーは、マネージド ID を読み取るためにのみアクセスできる必要があります。 つまり、ユーザーがポータル内の他の場所で表示できるリソースよりも多くのリソースが表示される可能性があります。 これは、ID の使用状況を完全に可視化するためです。 ユーザーが関連付けられているリソースにアクセスできない場合は、一覧からアクセスしようとするとエラーが表示されます。

### ユーザー割り当てマネージド ID を削除する

ユーザー割り当てマネージド ID の削除ボタンを選択すると、その ID に対して最大 10 個の関連リソースの一覧が表示されます。 完全な数がウィンドウの上部に表示されます。 この一覧では、ID の削除によって影響を受けるリソースを確認できます。 決定を確認するように求められます。

[Image: ユーザー割り当てマネージド ID の削除確認画面を示すスクリーンショット。]

この確認プロセスは、ポータルでのみ使用できます。 REST API を使用して削除する前に ID のリソースを表示するには、事前にリソースの一覧を手動で取得します。

### 制限事項

- この機能は、すべてのパブリック リージョンと USGov と中国で利用できます。
- 関連付けられたリソースに対する API 要求は、テナントごとに 1 秒あたり 1 つに制限されます。 この制限を超えると、 `HTTP 429` エラーが発生する可能性があります。 この制限は、ユーザー割り当てマネージド ID の一覧の取得には適用されません。
- プレビュー段階にある Azure リソースの種類、またはマネージド ID のサポートがプレビュー段階にある場合、完全に一般公開されるまで、関連付けられているリソースの一覧に表示されない場合があります。 この一覧には、Service Fabric クラスター、ブループリント、Machine Learning サービスが含まれます。
- この機能は、サブスクリプションが 5,000 未満のテナントに限定されます。 テナントに 5,000 を超えるサブスクリプションがある場合は、エラーが表示されます。
- 関連付けられているリソースの一覧には、表示名ではなく、リソースの種類が表示されます。
- Azure Policy の割り当てが一覧に表示されますが、名前が正しく表示されません。
- この機能は、PowerShell ではまだ使用できません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-view-managed-identity-activity"} -->
## マネージド ID の更新アクティビティとサインイン アクティビティを表示する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-view-managed-identity-activity
- Service: entra-id / managed-identities
- Article date: 2024-06-05
- Summary: マネージド ID に対して行われたアクティビティと、マネージド ID によって実行される認証を表示する詳細な手順

この記事では、マネージド ID に対して実行された更新と、マネージド ID によって行われたサインイン試行を表示する方法について説明します。

### 前提条件

- Azure リソースのマネージド ID の基本点な事柄については、[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するセクションを参照してください。
- まだ Azure アカウントを持っていない場合は、[無料アカウントを新規登録](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### ユーザー割り当てマネージド ID に対して行われた更新を表示する

この手順では、ユーザー割り当てマネージド ID に対して実行された更新を表示する方法を示します。

1. Azure portal で **[アクティビティ ログ]** に移動します。

    [Image: Azure portal でアクティビティ ログを参照する方法を示すスクリーンショット]
2. **[フィルターの追加]** 検索ピルを選択し、 **[操作]** をリストから選択します。

    [Image: 検索フィルターの構築を開始する方法を示すスクリーンショット]
3. **[操作]** ドロップダウン リストに、"ユーザー割り当て ID を削除する" と "UserAssignedIdentities を書き込む" という操作名を入力します。

    [Image: 検索フィルターに操作を追加する方法を示すスクリーンショット]
4. 一致する操作が表示された場合は、1 つを選択して概要を表示します。

    [Image: 操作の概要を示すスクリーンショット]
5. **[JSON]** タブを選択して操作に関する詳細情報を表示し、**プロパティ** ノードまでスクロールして、変更された ID に関する情報を表示します。

    [Image: 操作の詳細を示すスクリーンショット]

### マネージド ID に対して追加および削除されたロールの割り当てを表示する

注

ロールの割り当ての変更を表示するマネージド ID のオブジェクト (プリンシパル) ID で検索する必要があります。

1. ロールの割り当ての変更を表示するマネージド ID を見つけます。 システム割り当てマネージド ID を探している場合は、リソースの下の **[ID]** 画面にオブジェクト ID が表示されます。 ユーザー割り当て ID を探している場合は、マネージド ID の **[概要]** ページにオブジェクト ID が表示されます。

ユーザー割り当て ID:

[Image: ユーザー割り当て ID のオブジェクト ID を取得する方法を示すスクリーンショット]

システム割り当て ID:

[Image: システム割り当て ID のオブジェクト ID を取得する方法を示すスクリーンショット]

1. [オブジェクト ID] をコピー します。
2. **[アクティビティ ログ]** を参照します。

    [Image: Azure portal でアクティビティ ログを参照する方法を示すスクリーンショット]
3. **[フィルターの追加]** 検索ピルを選択し、 **[操作]** をリストから選択します。

    [Image: 検索フィルターの構築を開始する方法を示すスクリーンショット]
4. **[操作]** ドロップダウン リストに、「**ロールの割り当ての作成**」と「**ロールの割り当ての削除**」という操作名を入力します。

    [Image: ロールの割り当て操作を検索フィルターに追加する方法を示すスクリーンショット]
5. 検索ボックスにオブジェクト ID を貼り付けます。結果は自動的にフィルター処理されます。

    [Image: オブジェクト ID による検索方法を示すスクリーンショット]
6. 一致する操作が表示された場合は、1 つを選択して概要を表示します。

    [Image: マネージド ID のロールの割り当ての概要を示すスクリーンショット]

### マネージド ID で認証の試行を表示する

1. **[Microsoft Entra ID]** に移動します。

    [Image: Active Directory を参照する方法を示すスクリーンショット]
2. **[監視]** セクションから **[サインイン ログ]** を選択します。

    [Image: サインイン ログの選択を示すスクリーンショット]
3. **[マネージド ID のサインイン]** タブを選択します。

    [Image: すべての列を表示したマネージド ID アクティビティ セクションのスクリーンショット]
4. Microsoft Entra ID で ID のエンタープライズ アプリケーションを表示するには、[マネージド ID] 列を選びます。
5. Azure リソースまたはユーザー割り当てマネージド ID を表示するには、Azure portal の検索バーで名前を使用して検索します。

    [Image: マネージド ID のサインイン イベントを示すスクリーンショット]

注

マネージド ID 認証要求は Azure インフラストラクチャ内で送信されるため、IP アドレスの値はここでは除外されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/how-to-view-managed-identity-service-principal"} -->
## マネージド ID のサービス プリンシパルを表示する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-view-managed-identity-service-principal
- Service: entra-id / managed-identities
- Article date: 2025-03-14
- Summary: マネージド ID のサービス プリンシパルを表示するための詳細な手順。

Azure リソースのマネージド ID は、Microsoft Entra ID で自動的に管理される ID を Azure サービスに提供します。 この ID を使用して、コードに資格情報が含まれていなくても、Microsoft Entra 認証をサポートする任意のサービスに認証することができます。

この記事では、マネージド ID のサービス プリンシパルを表示する方法について説明します。

注

サービス プリンシパルは、エンタープライズ アプリケーションです。

### 前提条件

- Azure リソースのマネージド ID に慣れていない場合は、「Azure リソースの [マネージド ID とは」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)参照してください。
- Azure アカウントをまだお持ちでない場合は、続行する前に [無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。
- 仮想マシンまたは[アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm#system-assigned-managed-identity)[でシステム割り当て ID を](https://learn.microsoft.com/ja-jp/azure/app-service/overview-managed-identity#add-a-system-assigned-identity)有効にします。

::: zone pivot="identity-mi-service-principal-portal"

### Azure portal を使用してマネージド ID のサービス プリンシパルを表示する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
2. **Entra ID**&gt;の**Enterprise アプリ**に移動します。
3. [ **管理** ] セクションで、[ **すべてのアプリケーション**] を選択します。
4. "アプリケーションの種類 == マネージド ID" のフィルターを設定し、[ **適用**] を選択します。
5. (省略可能)検索フィルター ボックスに、システム マネージド ID が有効になっている Azure リソースの名前またはユーザー割り当てマネージド ID の名前を入力します。

    [Image: マネージド ID サービス プリンシパルのビューを表示するためのスクリーンショット。]

::: zone-end

::: zone pivot="identity-mi-service-principal-cli"

### Azure CLI を使用してマネージド ID のサービス プリンシパルを表示する

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「 [Azure Cloud Shell の概要](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI 参照コマンドをローカルで実行する場合は、Azure CLI を [インストール](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) します。 Windows または macOS で実行している場合は、Docker コンテナーで Azure CLI を実行することを検討してください。 詳細については、「 [Docker コンテナーで Azure CLI を実行する方法」を参照してください](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)。

    - ローカル インストールを使用している場合は、 [az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用して Azure CLI にサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「 [Azure CLI を使用した Azure への認証](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - 初回使用時にインストールを求められたら、Azure CLI 拡張機能をインストールします。 拡張機能の詳細については、「 [Azure CLI での拡張機能の使用と管理」を](https://learn.microsoft.com/ja-jp/cli/azure/azure-cli-extensions-overview)参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行して、インストールされているバージョンと依存ライブラリを見つけます。 最新バージョンにアップグレードするには、 [az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

次のコマンドは、マネージド ID が有効になっている仮想マシン (VM) またはアプリケーションのサービス プリンシパルを表示する方法を示しています。 `<Azure resource name>` を独自の値に置き換えます。

```azurecli
az ad sp list --display-name <Azure resource name>
```

::: zone-end

::: zone pivot="identity-mi-service-principal-powershell"

### PowerShell を使用してマネージド ID のサービス プリンシパルを表示する

この例のスクリプトを実行するには、次の 2 つのオプションがあります。

- コード ブロックの右上隅にある [[試](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview)してみる] ボタンを使用して開くことができる **Azure Cloud Shell** を使用します。
- 最新バージョンの [Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) をインストールしてローカルでスクリプトを実行し、 `Connect-AzAccount`を使用して Azure にサインインします。

次のコマンドは、"システム割り当ての ID" が有効になっている VM またはアプリケーションのサービス プリンシパルを表示する方法を示しています。`<Azure resource name>` を独自の値に置き換えます。

```powershell
Get-AzADServicePrincipal -DisplayName <Azure resource name>
```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/known-issues"} -->
## マネージド ID に関する既知の問題 - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues
- Service: entra-id / managed-identities
- Article date: 2022-01-11
- Summary: Azure リソースのマネージド ID に関する既知の問題。

この記事では、マネージドID に関するいくつかの問題とその対処方法について説明します。 マネージド ID に関してよく寄せられる質問については、[よく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-faq)に関する記事をご覧ください。

### 移動した後に VM を開始できない

実行状態の VM をリソース グループまたはサブスクリプションから移動すると、移動中も引き続き実行されます。 ただし、移動後に、VM を停止および再起動すると、VM を開始できなくなります。 この問題は、VM がマネージド ID 参照を更新せず、古い URI を使用し続けるために発生します。

**回避策**

Azure リソースのマネージド ID の正しい値を取得できるように、VM 上で更新をトリガーします。 VM プロパティの変更を行って、Azure リソース ID のマネージド ID への参照を更新できます。 たとえば、次のコマンドを使用して、VM で新しいタグの値を設定できます。

```azurecli
az vm update -n <VM Name> -g <Resource Group> --set tags.fixVM=1
```

このコマンドは、新しいタグ "fixVM" を値 1 で VM に設定します。

このプロパティの設定によって、VM は Azure リソース URI の正しいマネージド ID で更新され、VM を開始できるようになります。

VM が開始されると、次のコマンドを使用してタグを削除できます。

```azurecli
az vm update -n <VM Name> -g <Resource Group> --remove tags.fixVM
```

### Microsoft Entra ディレクトリ間でのサブスクリプションの転送

マネージド ID は、サブスクリプションが別のディレクトリに移動/転送されたときには更新されません。 その結果、既存のシステム割り当てマネージド ID やユーザー割り当てマネージド ID は破損します。

別のディレクトリに移動されたサブスクリプションのマネージド ID の回避策:

- システム割り当てマネージドID の場合、無効にしてから最有効化します。
- ユーザー割り当てマネージド ID の場合、削除、再作成の後、必要なリソース (例： 仮想マシン) へ再度アタッチします

詳細については、「[別の Microsoft Entra ディレクトリに Azure サブスクリプションを転送する」](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/transfer-subscription)を参照してください。

### マネージド ID 割り当て操作中のエラー

まれに、Azure リソースを使用したマネージド ID の割り当てに関連するエラーを示すエラー メッセージが表示されることがあります。 エラー メッセージの例を次に示します。

- Azure リソース 'azure-resource-id' には、ID 'managed-identity-id' へのアクセス権がありません。
- リソース 'azure-resource-id' にはマネージド サービス ID が関連付けられていません

**回避策** このようなまれなケースで、最も適切な次の手順は以下のとおりです。

1. リソースに割り当てる必要がなくなった ID は、リソースから削除します。
2. ユーザー割り当てマネージド ID の場合、ID を Azure リソースにもう一度割り当てます。
3. システム割り当てマネージド ID の場合、ID を無効にしてもう一度有効にします。

注

マネージド ID の割り当てまたは割り当て解除を行うには、次のリンクを参照してください。

- [VM のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm)
- [VMSS のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vmss)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-azure-cli"} -->
## Azure CLI を使用してユーザー割り当てマネージド ID を管理する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-azure-cli
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: Azure CLI を使用して、ユーザー割り当てマネージド ID を管理します。

Azure リソースのマネージド ID を使用すると、コードで資格情報を管理する必要がなくなります。 これらを使用して、アプリケーションの Microsoft Entra トークンを取得できます。 アプリケーションは、Microsoft Entra 認証をサポートするリソースにアクセスするときにトークンを使用できます。 ID は Azure で管理されるためユーザーが行う必要はありません。

マネージド ID には、システム割り当てとユーザー割り当ての 2 種類があります。 システム割り当てマネージド ID のライフサイクルは、それらを作成したリソースに関連付けられています。 この ID は 1 つのリソースのみに制限され、Azure ロールベースのアクセス制御 (RBAC) を使用してマネージド ID にアクセス許可を付与できます。 ユーザー割り当てマネージド ID は、複数のリソースで使用できます。

このアーティクルでは、Azure CLI を使用してユーザー割り当てマネージド ID を作成、一覧表示、削除したり、それにロールを割り当てる方法について説明します。

### [前提条件]

- Azure リソースのマネージド ID の基本点な事柄については、[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するセクションを参照してください。 [システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を必ず確認してください。
- まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

### 環境を準備する

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「[Azure Cloud Shell の概要](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI 参照コマンドをローカルで実行する場合は、Azure CLI を [インストール](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) します。 Windows または macOS で実行している場合は、Docker コンテナーで Azure CLI を実行することを検討してください。 詳細については、「[Docker コンテナーで Azure CLI を実行する方法](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)」を参照してください。

    - ローカル インストールを使用する場合は、[az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用して Azure CLI にサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「 [Azure CLI を使用した Azure への認証](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - 初回使用時にインストールを求められたら、Azure CLI 拡張機能をインストールします。 拡張機能の詳細については、「[Azure CLI で拡張機能を使用および管理する](https://learn.microsoft.com/ja-jp/cli/azure/azure-cli-extensions-overview)」を参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行し、インストールされているバージョンおよび依存ライブラリを検索します。 最新バージョンにアップグレードするには、[az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

CLI を使用してアプリのサービス プリンシパルを使用しているときにユーザーのアクセス許可を変更するには、CLI の一部がグラフ API に対して GET 要求を実行するため、サービス プリンシパルに Azure Active Directory Graph API でより多くの権限を与える必要があります。 そうしないと、「この操作を完了するのに十分な権限がありません」というメッセージが表示される可能性があります。

この手順を実行するには、

1. Microsoft Entra ID で **アプリの登録** に移動し、アプリを選択し、 **API のアクセス許可**を選択し、下にスクロールして **Azure Active Directory Graph** を選択します。
2. [ **アプリケーションのアクセス許可**] を選択し、適切なアクセス許可を追加します。

### ユーザー割り当てマネージド ID を作成する

ユーザー割り当てマネージド ID を作成するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

1. `az identity create` コマンドを使用して、ユーザー割り当てマネージド ID を作成します。 `-g` パラメーターには、ユーザー割り当てマネージド ID を作成するリソース グループを指定します。 `-n` パラメーターでは、名前を指定します。
2. `<RESOURCE GROUP>` および `<USER ASSIGNED IDENTITY NAME>` パラメーターの値は、実際の値に置き換えます。

    Important

    ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

    ```azurecli
    az identity create -g <RESOURCE GROUP> -n <USER ASSIGNED IDENTITY NAME>
    ```

### ユーザー割り当てマネージド ID を一覧表示する

ユーザー割り当てマネージド ID を一覧表示または読み取るには、アカウントへの [Managed Identity Operator](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) または [Managed Identity Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor) ロールの割り当てが必要です。

ユーザー割り当てマネージド ID を一覧表示するには、 `az identity list` コマンドを使用します。 `<RESOURCE GROUP>` は実際の値に置き換えます。

```azurecli
az identity list -g <RESOURCE GROUP>
```

JSON 応答内のユーザー割り当てマネージド ID には、キー `"Microsoft.ManagedIdentity/userAssignedIdentities"` に対して返された `type` の値が含まれます。

`"type": "Microsoft.ManagedIdentity/userAssignedIdentities"`

### ユーザー割り当てマネージド ID を削除する

ユーザー割り当てマネージド ID を削除するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

ユーザー割り当てマネージド ID を削除するには、

1. `az identity delete` コマンドを使用します。 `-n` パラメーターでは、名前を指定します。 `-g` パラメーターでは、ユーザー割り当てマネージド ID を作成したリソース グループを指定します。
2. `<USER ASSIGNED IDENTITY NAME>` および `<RESOURCE GROUP>` パラメーターの値は、実際の値に置き換えます。

    ```azurecli
    az identity delete -n <USER ASSIGNED IDENTITY NAME> -g <RESOURCE GROUP>
    ```

    ユーザー割り当てマネージド ID を削除しても、それが割り当てられていたリソースから参照が削除されることはありません。 それらをリソース自体から削除します。 たとえば、VM または仮想マシン スケール セットの場合は、 `az vm/vmss identity remove` コマンドを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-azure-portal"} -->
## Azure portal を使用してユーザー割り当てマネージド ID を管理する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-azure-portal
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: Azure portal を使用して、ユーザー割り当てマネージド ID を管理します。

Azure リソースのマネージド ID を使用すると、コードで資格情報を管理する必要がなくなります。 これらを使用して、アプリケーションの Microsoft Entra トークンを取得できます。 アプリケーションは、Microsoft Entra 認証をサポートするリソースにアクセスするときにトークンを使用できます。 ID は Azure で管理されるためユーザーが行う必要はありません。

マネージド ID には、システム割り当てとユーザー割り当ての 2 種類があります。 システム割り当てマネージド ID のライフサイクルは、それらを作成したリソースに関連付けられています。 この ID は 1 つのリソースのみに制限され、Azure ロールベースのアクセス制御 (RBAC) を使用してマネージド ID にアクセス許可を付与できます。 ユーザー割り当てマネージド ID は、複数のリソースで使用できます。

このアーティクルでは、Azure portal を使用してユーザー割り当てマネージド ID を作成、一覧表示、削除したり、それにロールを割り当てる方法について説明します。

### [前提条件]

- Azure リソースのマネージド ID の基本点な事柄については、[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するセクションを参照してください。 [システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を必ず確認してください。
- まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

### ユーザー割り当てマネージド ID を作成する

ユーザー割り当てマネージド ID を作成するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. 検索ボックスに、「**マネージド ID**」と入力します。 **[サービス]** の下で、 **[マネージド ID]** を選択します。
3. **[追加]** をクリックして、 **[ユーザー割り当てマネージド ID を作成する]** ウィンドウの次のボックスに値を入力します。

    - **サブスクリプション**:ユーザー割り当てマネージド ID を作成するサブスクリプションを選択します。
    - **リソース グループ**: ユーザー割り当てマネージド ID を作成するリソース グループを選択するか、 **[新規作成]** をクリックして新しいリソース グループを作成します。
    - **リージョン**: ユーザー割り当てマネージド ID をデプロイするリージョンを選択します。たとえば、**米国西部**などです。
    - **名前**: ユーザー割り当てマネージド ID の名前です。たとえば、UAI1 とします。

    Important

    ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

    [Image: ユーザー割り当て済みマネージド ID の作成ウィンドウを示すスクリーンショット。]
4. **[確認と作成]** を選択して変更を確認します。
5. **を選択して**を作成します。

### ユーザー割り当てマネージド ID を一覧表示する

ユーザー割り当てマネージド ID を一覧表示または読み取るには、アカウントに[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator)または[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. 検索ボックスに、「**マネージド ID**」と入力します。 **[サービス]** の下で、 **[マネージド ID]** を選択します。
3. ご使用のサブスクリプションのユーザー割り当てマネージド ID の一覧が表示されます。 ユーザー割り当てマネージド ID の詳細を確認するには、名前をクリックします。
4. 図に示すように、マネージド ID に関する詳細を表示できます。

    [Image: ユーザー割り当てマネージド ID の一覧を示すスクリーンショット。]

### ユーザー割り当てマネージド ID を削除する

ユーザー割り当てマネージド ID を削除するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。 ユーザー割り当て ID を削除しても、割り当てられていたリソースから削除されることはありません。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. ユーザー割り当てマネージド ID を選択して、 **[削除する]** をクリックします。
3. 確認ボックスで **[はい]** を選択します。

    [Image: ユーザー割り当てマネージド ID の削除を示すスクリーンショット。]

### ユーザー割り当てマネージド ID へのアクセスを管理する

環境によっては、管理者はユーザー割り当てマネージド ID を管理できるユーザーを制限することを選択する場合があります。 管理者は、[組み込みの](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#identity) RBAC ロールを使用してこの制限を実装できます。 これらのロールを使用して、ユーザー割り当てマネージド ID に対する組織内ユーザー、またはグループの権限を付与できます。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. 検索ボックスに、「**マネージド ID**」と入力します。 **[サービス]** の下で、 **[マネージド ID]** を選択します。
3. ご使用のサブスクリプションのユーザー割り当てマネージド ID の一覧が表示されます。 管理したいユーザー割り当てマネージド ID を選択します。
4. **[アクセス制御 (IAM)]** を選択します。
5. **[ロールの割り当ての追加]**を選びます

    [Image: ユーザー割り当てマネージド ID のアクセスの制御画面を示すスクリーンショット。]
6. **[ロールの割り当ての追加]** ウィンドウで、割り当てるロールを選択し、**[次へ]**を選択します。
7. ロールを割り当てるユーザーを選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-azure-resource-manager"} -->
## Azure Resource Manager を使用してユーザー割り当てマネージド ID を管理する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-azure-resource-manager
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: Azure Resource Manager を使用して、ユーザー割り当てマネージド ID を管理します。

Azure リソースのマネージド ID を使用すると、コードで資格情報を管理する必要がなくなります。 これらを使用して、アプリケーションの Microsoft Entra トークンを取得できます。 アプリケーションは、Microsoft Entra 認証をサポートするリソースにアクセスするときにトークンを使用できます。 ID は Azure で管理されるためユーザーが行う必要はありません。

マネージド ID には、システム割り当てとユーザー割り当ての 2 種類があります。 システム割り当てマネージド ID のライフサイクルは、それらを作成したリソースに関連付けられています。 この ID は 1 つのリソースのみに制限され、Azure ロールベースのアクセス制御 (RBAC) を使用してマネージド ID にアクセス許可を付与できます。 ユーザー割り当てマネージド ID は、複数のリソースで使用できます。

このアーティクルでは、Azure Resource Manager を使用してユーザー割り当てマネージド ID を作成します。 Resource Manager テンプレートを使用して、ユーザー割り当てマネージド ID を一覧表示したり削除したりすることはできません。 他の方法を使用して、ユーザー割り当てマネージド ID を一覧表示または削除します。

### [前提条件]

- Azure リソースのマネージド ID の基本点な事柄については、[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するセクションを参照してください。 *[システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を必ず確認してください*。
- まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。

### テンプレートの作成と編集

Resource Manager テンプレートを使用すると、Azure リソース グループによって定義された新しいリソースまたは変更されたリソースをデプロイできます。 ローカルとポータル ベースの両方を含むテンプレートの編集やデプロイでは、次のような複数のオプションが使用できます。 次のようにすることができます。

- [Microsoft Azure Marketplace からのカスタム テンプレート](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/deploy-portal#deploy-resources-from-custom-template)を使用し、最初からテンプレートを作成したり、既存の共通テンプレートまたは[クイックスタート テンプレート](https://azure.microsoft.com/resources/templates/)に基づいてテンプレートを作成したりします。
- テンプレートをエクスポートして、既存のリソース グループから派生させます。 [元のデプロイ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/manage-resource-groups-portal#export-resource-groups-to-templates)または[デプロイの現在の状態](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/manage-resource-groups-portal#export-resource-groups-to-templates)からエクスポートできます。
- ローカルの [JSON エディター (VS Code など)](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/quickstart-create-templates-use-the-portal) を使用してから、PowerShell または Azure CLI を使用してアップロードおよびデプロイします。
- Visual Studio の [Azure リソース グループ プロジェクト](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/create-visual-studio-deployment-project)を使用して、テンプレートを作成およびデプロイします。

### ユーザー割り当てマネージド ID を作成する

ユーザー割り当てマネージド ID を作成するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

ユーザー割り当てマネージド ID を作成するには、次のテンプレートを使用します。 `<USER ASSIGNED IDENTITY NAME>` は実際の値に置き換えます。

Important

ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

```json
{
  "$schema": "https://schema.management.azure.com/schemas/2015-01-01/deploymentTemplate.json#",
  "contentVersion": "1.0.0.0",
  "parameters": {
    "resourceName": {
          "type": "string",
          "metadata": {
            "description": "<USER ASSIGNED IDENTITY NAME>"
          }
        }
  },
  "resources": [
    {
      "type": "Microsoft.ManagedIdentity/userAssignedIdentities",
      "name": "[parameters('resourceName')]",
      "apiVersion": "2018-11-30",
      "location": "[resourceGroup().location]"
    }
  ],
  "outputs": {
      "identityName": {
          "type": "string",
          "value": "[parameters('resourceName')]"
      }
  }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-powershell"} -->
## PowerShell を使用してユーザー割り当てマネージド ID を管理する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-powershell
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: PowerShell を使用して、ユーザー割り当てマネージド ID を管理します。

Azure リソースのマネージド ID を使用すると、コードで資格情報を管理する必要がなくなります。 これらを使用して、アプリケーションの Microsoft Entra トークンを取得できます。 アプリケーションは、Microsoft Entra 認証をサポートするリソースにアクセスするときにトークンを使用できます。 ID は Azure で管理されるためユーザーが行う必要はありません。

マネージド ID には、システム割り当てとユーザー割り当ての 2 種類があります。 システム割り当てマネージド ID のライフサイクルは、それらを作成したリソースに関連付けられています。 この ID は 1 つのリソースのみに制限され、Azure ロールベースのアクセス制御 (RBAC) を使用してマネージド ID にアクセス許可を付与できます。 ユーザー割り当てマネージド ID は、複数のリソースで使用できます。

この記事では、PowerShell を使用して、ユーザー割り当てマネージド ID にロールを作成、一覧表示、削除、または割り当てる方法について説明します。 ユーザー割り当てマネージド ID を割り当てることができるリソースの例として、Azure Virtual Machine (AzureVM) を使用します。

### [前提条件]

- Azure リソースのマネージド ID の基本点な事柄については、[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するセクションを参照してください。 *[システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を必ず確認してください*。
- まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。
- サンプル スクリプトを実行するには、次の 2 つのオプションがあります。
    - [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) を使用します。これは、コード ブロックの右上隅にある **[試してみる]** ボタンから開くことができます。
    - Azure PowerShell を使用して、スクリプトをローカルで実行します。次のセクションの説明を参照してください。

#### ローカルで Azure PowerShell を構成する

このアーティクルのために、Cloud Shell を使わずにMicrosoft Azure PowerShell をローカルで使用するには、次の手順に従います。

1. [最新バージョンの Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) をインストールします (まだインストールしていない場合)。
2. Azure にサインインします。

    ```azurepowershell
    Connect-AzAccount
    ```
3. [PowerShellGet の最新バージョン](https://learn.microsoft.com/ja-jp/powershell/gallery/powershellget/install-powershellget)をインストールします。

    ```azurepowershell
    Install-Module -Name PowerShellGet -AllowPrerelease
    ```

    次の手順のために、このコマンドを実行した後、現在の PowerShell セッションを `Exit` 終了する必要があるかもしれません。
4. このアーティクルのユーザー割り当てマネージド ID 操作を実行するために、`Az.ManagedServiceIdentity` モジュールのプレリリース バージョンをインストールします。

    ```azurepowershell
    Install-Module -Name Az.ManagedServiceIdentity -AllowPrerelease
    ```

### ユーザー割り当てマネージド ID を作成する

ユーザー割り当てマネージド ID を作成するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

1. ユーザー割り当てマネージド ID を作成するには、`New-AzUserAssignedIdentity` コマンドを使用します。 `ResourceGroupName` パラメーターには、ユーザー割り当てマネージド ID を作成するリソース グループを指定します。 `-Name` パラメーターでは、名前を指定します。
2. `<RESOURCE GROUP>` および `<USER ASSIGNED IDENTITY NAME>` パラメーターの値は、実際の値に置き換えます。

    Important

    ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

    ```azurepowershell
    New-AzUserAssignedIdentity -ResourceGroupName <RESOURCEGROUP> -Name <USER ASSIGNED IDENTITY NAME>
    ```

### ユーザー割り当てマネージド ID を一覧表示する

ユーザー割り当てマネージド ID を一覧表示または読み取るには、アカウントへの [Managed Identity Operator](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) または [Managed Identity Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor) ロールの割り当てが必要です。

1. ユーザー割り当てマネージド ID を一覧表示するには、 `Get-AzUserAssignedIdentity` コマンドを使用します。 `-ResourceGroupName` パラメーターでは、ユーザー割り当てマネージド ID を作成したリソース グループを指定します。
2. `<RESOURCE GROUP>` は実際の値に置き換えます。

    ```azurepowershell
    Get-AzUserAssignedIdentity -ResourceGroupName <RESOURCE GROUP>
    ```

    応答内のユーザー割り当てマネージド ID には、キー `"Microsoft.ManagedIdentity/userAssignedIdentities"` に対して返された `Type` の値が含まれます。

    `Type :Microsoft.ManagedIdentity/userAssignedIdentities`

### ユーザー割り当てマネージド ID を削除する

ユーザー割り当てマネージド ID を削除するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

1. ユーザー割り当てマネージド ID を削除するには、`Remove-AzUserAssignedIdentity` コマンドを使用します。 `-ResourceGroupName` パラメーターには、ユーザー割り当て ID を作成したリソース グループを指定します。 `-Name` パラメーターでは、名前を指定します。
2. `<RESOURCE GROUP>` および `<USER ASSIGNED IDENTITY NAME>` パラメーターの値は、実際の値に置き換えます。

    ```azurepowershell
    Remove-AzUserAssignedIdentity -ResourceGroupName <RESOURCE GROUP> -Name <USER ASSIGNED IDENTITY NAME>
    ```

    ユーザー割り当てマネージド ID を削除しても、それが割り当てられていたリソースから参照が削除されることはありません。 ID の割り当ては、別に削除する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-rest"} -->
## REST を使用してユーザー割り当てマネージド ID を管理する - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/manage-user-assigned-managed-identities-rest
- Service: entra-id / managed-identities
- Article date: 2025-09-09
- Summary: REST を使用してユーザー割り当てマネージド ID を管理します。

Azure リソースのマネージド ID を使用すると、コードで資格情報を管理する必要がなくなります。 これらを使用して、アプリケーションの Microsoft Entra トークンを取得できます。 アプリケーションは、Microsoft Entra 認証をサポートするリソースにアクセスするときにトークンを使用できます。 ID は Azure で管理されるためユーザーが行う必要はありません。

マネージド ID には、システム割り当てとユーザー割り当ての 2 種類があります。 システム割り当てマネージド ID のライフサイクルは、それらを作成したリソースに関連付けられています。 この ID は 1 つのリソースのみに制限され、Azure ロールベースのアクセス制御 (RBAC) を使用してマネージド ID にアクセス許可を付与できます。 ユーザー割り当てマネージド ID は、複数のリソースで使用できます。

この記事では、REST を使用して、ユーザー割り当てマネージド ID を作成、一覧表示、および削除する方法について説明します。

### [前提条件]

- Azure リソースのマネージド ID の基本点な事柄については、[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するセクションを参照してください。 *[システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)を必ず確認してください*。
- まだ Azure アカウントを持っていない場合は、[無料のアカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してから先に進んでください。
- この記事で取り上げるすべてのコマンドは、クラウドでもローカルでも実行できます。
    - クラウドで実行するには、[Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) を使用します。
    - ローカルで実行するには、[curl](https://curl.se/download.html) と [Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) をインストールします。

### ベアラー アクセス トークンを取得する

1. ローカルで実行している場合は、Azure CLI を使用して Azure にサインインします。

    ```azurecli
    az login
    ```
2. [az account get-access-token](https://learn.microsoft.com/ja-jp/cli/azure/account#az-account-get-access-token) を使用してアクセス トークンを取得します。

    ```azurecli
    az account get-access-token
    ```

### ユーザー割り当てマネージド ID を作成する

ユーザー割り当てマネージド ID を作成するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

Important

ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

```bash
curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroup
s/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>?api-version=2015-08-31-preview' -X PUT -d '{"location": "<LOCATION>"}' -H "Content-Type: application/json" -H "Authorization: Bearer <ACCESS TOKEN>"
```

```HTTP
PUT https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroup
s/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>?api-version=2015-08-31-preview HTTP/1.1
```

**要求ヘッダー**

| リクエストヘッダー | Description |
| --- | --- |
| *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
| *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

**リクエスト本文**

| 名前 | Description |
| --- | --- |
| ロケーション | 必須。 リソースの場所。 |

### ユーザー割り当てマネージド ID を一覧表示する

ユーザー割り当てマネージド ID を一覧表示または読み取るには、アカウントへの [Managed Identity Operator](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator) または [Managed Identity Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor) ロールの割り当てが必要です。

```bash
curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities?api-version=2015-08-31-preview' -H "Authorization: Bearer <ACCESS TOKEN>"
```

```HTTP
GET https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities?api-version=2015-08-31-preview HTTP/1.1
```

| リクエストヘッダー | Description |
| --- | --- |
| *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
| *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |

### ユーザー割り当てマネージド ID を削除する

ユーザー割り当てマネージド ID を削除するには、お使いのアカウントに[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。

ユーザー割り当てマネージド ID を削除しても、それが割り当てられていたリソースから参照が削除されることはありません。

```bash
curl 'https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroup
s/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>?api-version=2015-08-31-preview' -X DELETE -H "Authorization: Bearer <ACCESS TOKEN>"
```

```HTTP
DELETE https://management.azure.com/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/resourceGroups/TestRG/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<USER ASSIGNED IDENTITY NAME>?api-version=2015-08-31-preview HTTP/1.1
```

| リクエストヘッダー | Description |
| --- | --- |
| *Content-Type*（コンテンツの種類） | 必須。 `application/json` に設定します。 |
| *認可* | 必須。 有効な `Bearer` アクセス トークンを設定します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/managed-identities-assignment-restriction"} -->
## マネージド ID の割り当て制限 (プレビュー) - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-assignment-restriction
- Service: entra-id / managed-identities
- Article date: 2026-07-10
- Summary: 割り当て制限で、セキュリティと回復性を向上させるために、ユーザー割り当てマネージド ID のスコープを 1 つ以上のリソース プロバイダーに適用する方法について説明します。

割り当て制限 (リソース制限とも呼ばれます) は、ユーザー割り当てマネージド ID のセキュリティ機能であり、ID を割り当てることができるリソース プロバイダーを制限します。 割り当ての制限は現在プレビュー段階です。 関連付けられていないサービス間で再利用できないように、マネージド ID を 1 つ以上のリソース プロバイダーに分離できます。

割り当て制限を構成する場合、マネージド ID の使用は厳密なスコープのままです。 このスコープにより、侵害された ID または正しく構成されていない ID の爆発半径が減少し、セキュリティと回復性の両方が向上します。

### リソース制限について

ユーザー割り当てマネージド ID を使用する場合、次の 2 つのリソース ロールが関係します。

- **ソース リソース**: マネージド ID が割り当てられているリソース。
- **ターゲット リソース**: ソース リソースがマネージド ID を使用してアクセスするリソース。

たとえば、App Service がマネージド ID を使用してストレージ アカウントにアクセスする場合、App Service はソース リソースであり、ストレージ アカウントはターゲット リソースです。

割り当て制限は、マネージド ID と *ソース リソース*の間の関係に適用されます。 ターゲット リソースには適用されません。

マネージド ID を複数のリソース プロバイダー (たとえば、 `Microsoft.Web` と `Microsoft.ContainerRegistry`の両方) にわたってソース リソースに制限する場合は、許可される各リソース プロバイダーを含む [リソース制限を設定](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/configure-managed-identities-assignment-restriction) します。

### 割り当て制限スコープ

ユーザー割り当てマネージド ID に割り当て制限が構成されている場合:

- ID は、許可されているリソース プロバイダーと一致するソース リソースにのみ割り当てることができます。
- ソース リソースは、適切なロールの割り当てが存在する限り、そのスコープ外のターゲット リソースに引き続きアクセスできます。

この動作により、サービスは、ID の割り当てを厳密に制御しながら、ダウンストリームの依存関係を維持できます。

### 割り当て制限の利点

割り当て制限には、次のセキュリティと運用上の利点があります。

#### セキュリティリスクの低減

マネージド ID を割り当てることができる場所を制限すると、関連付けられていないリソース プロバイダー間で ID が再利用されなくなります。 この制限により、資格情報が誤用または侵害された場合のブラスト半径が制限されます。

#### 設計上の最小限の特権

割り当て制限により、誤った ID または不要な ID の再利用を防ぐことができます。 サービスは、目的のスコープを超えてアクセス許可を保持しません。

#### エラーの封じ込め

マネージド ID が正しく構成されていない、無効になっている、または侵害された場合、その影響は、複数のリソース プロバイダーまたはサービスに連鎖するのではなく、限られたリソース セットに含まれます。

#### サービスの回復性の向上

スコープ ID の割り当てにより、ID またはロールの割り当ての問題によって引き起こされる停止の範囲が制限され、サービス全体の障害を防ぐことができます。

#### 運用上の明確さ

割り当てスコープを制限すると、ID の使用をより予測可能かつ追跡可能にすることで、監査、トラブルシューティング、インシデント対応が簡素化されます。

### 割り当て制限を設定しないリスク

Note

既定では、割り当て制限値は空白で、空の配列を表します。

割り当て制限値が設定されていないか、空の配列として構成されている場合:

- マネージド ID は、複数のリソース プロバイダーに割り当てることができます。
- ID の使用を監査することが困難になります。
- 1 つの ID 構成の誤りにより、マルチサービスまたはサービス全体の停止が発生する可能性があります。
- 侵害された ID により、元の設計意図を超えて、意図しない広範なアクセスが可能になる可能性があります。

### ベスト プラクティス

ユーザー割り当てマネージド ID の割り当て制限を計画する場合は、次のベスト プラクティスを検討してください。

- リソース プロバイダーごとに個別のマネージド ID を作成します。
- マネージド ID スコープを目的のソース リソースのみに一致させます。
- 便宜上、関連のないワークロード間でマネージド ID を再利用することは避けてください。

### Azure ポータルでサポートされているリソース プロバイダーとリソースの種類

Note

リソースの割り当て制限に **[なし]** を選択すると、ID は制限されず、マネージド ID をサポートするすべてのリソース プロバイダーのリソースに割り当てることができます。

ID 割り当てを特定のリソース プロバイダーに制限する場合にのみ、リソース割り当ての制限を構成します。 Azure ポータルの [**リソースの種類の選択**] ボックスの一覧には、サポートされているすべてのリソース プロバイダーとリソースの種類が含まれていない場合があります。

構成するリソース プロバイダーまたはリソースの種類が [**リソースの種類の選択**] ウィンドウに表示されない場合は、Azure CLIを使用します。 構成手順とコマンドの例については、「 [ユーザー割り当てマネージド ID の割り当て制限を構成する」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/configure-managed-identities-assignment-restriction)参照してください。

Warning

Azure CLI コマンドとコードとしてのインフラストラクチャ (IaC) テンプレートで、リソース プロバイダーの名前空間を独自に指定します (たとえば、`Microsoft.Storage`)。 `Microsoft.Storage/*`は使用しないでください。

Azure ポータルの [**リソースの種類の選択**] ウィンドウには、プロバイダー全体の選択が`Microsoft.Storage/*`として表示されます。 `/*` サフィックスはポータルの表示規則であり、API が受け入れる値の一部ではありません。 ポータルに`Microsoft.Storage/*`が表示されている場合でも、Azure CLIコマンドと IaC テンプレートで`Microsoft.Storage`を使用します。 プロバイダー全体ではなく 1 つのリソースの種類に ID を制限するには、完全なリソースの種類 ( `Microsoft.Storage/storageAccounts`など) を指定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/managed-identities-faq"} -->
## Azure リソースのマネージド ID に関してよく寄せられる質問 - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-faq
- Service: entra-id / managed-identities
- Article date: 2025-02-27
- Summary: マネージド ID に関してよく寄せられる質問

### 管理

#### マネージド ID を持つリソースを見つけるにはどうすればいいですか?

次の Azure CLI コマンドを使用して、システム割り当てマネージド ID を持つリソースの一覧を検索できます。

```azurecli
az resource list --query "[?identity.type=='SystemAssigned'].{Name:name, principalId:identity.principalId}" --output table
```

#### リソースでマネージド ID を使用するために必要な Azure ロールベースのアクセス制御 (RBAC) アクセス許可はどれですか?

- システム割り当てマネージド ID: リソースに対する書き込みアクセス許可が必要です。 たとえば、仮想マシンの場合は `Microsoft.Compute/virtualMachines/write` が必要です。 このアクションは、[Virtual Machine Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor) などのリソース固有の組み込みロールに含まれています。
- リソースへのユーザー割り当てマネージド ID の割り当て: そのリソースに対する書き込みアクセス許可が必要です。 たとえば、仮想マシンの場合は `Microsoft.Compute/virtualMachines/write` が必要です。 ユーザー割り当てアイデンティティに対する`Microsoft.ManagedIdentity/userAssignedIdentities/*/assign/action`のアクションが必要です。 このアクションは、[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator)組み込みロールに含まれています。
- ユーザー割り当て ID の管理: ユーザー割り当てマネージド ID を作成または削除するには、[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)ロールの割り当てが必要です。
- マネージド ID のロールの割り当ての管理: アクセスを付与しようとしているリソースに対する[所有者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#all)または[ユーザー アクセス管理者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#all)ロールの割り当てが必要です。 システム割り当て ID を持つリソース、またはロール割り当ての対象となるユーザー割り当て ID に対する [Reader](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#all) ロールの割り当てが必要です。 読み取りアクセスがない場合は、ロールの割り当てを追加する際にマネージド ID で検索するのではなく、「ユーザー、グループ、サービス プリンシパル」 で検索して、その ID のバッキング サービス プリンシパルを見つけることができます。 [Azure ロールの割り当ての詳細を参照してください](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)。

#### ユーザー割り当てマネージド ID を作成できないようにするにはどうすればよいですか。

[Azure Policy](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview) を使用して、ユーザー割り当てマネージド ID をユーザーが作成できないようにすることができます

1. [Azure portal](https://portal.azure.com) にサインインし、**[ポリシー]** に移動します。
2. **[定義]** を選択します。
3. **[+ ポリシー定義]** を選択し、必要な情報を入力します。
4. ポリシー規則セクションに、次を貼り付けます。

    ```json
    {
      "mode": "All",
      "policyRule": {
        "if": {
          "field": "type",
          "equals": "Microsoft.ManagedIdentity/userAssignedIdentities"
        },
        "then": {
          "effect": "deny"
        }
      },
      "parameters": {}
    }
    
    ```

ポリシーを作成したら、使用するリソース グループに割り当てます。

1. リソース グループに移動します。
2. テストに使用しているリソース グループを見つけます。
3. 左側のメニューから **[ポリシー]** を選択します。
4. **[ポリシーの割り当て]** を選択します。
5. **[基本]**セクションで、次を指定します。
    1. **スコープ** テストに使用しているリソース グループ
    2. **[ポリシー定義]** :前に作成したポリシー。
6. 他のすべての設定は既定値のままにして、 **[確認と作成]** を選択します

このようにすると、リソース グループ内にユーザー割り当てマネージド ID を作成しようとする試みはすべて失敗します。

[Image: ポリシー違反を示すスクリーンショット。]

### 概念

#### マネージド ID にバッキング アプリ オブジェクトはありますか?

いいえ。マネージド ID と Microsoft Entra アプリ登録は、ディレクトリ内では同じものではありません。

アプリの登録には、アプリケーション オブジェクトとサービス プリンシパル オブジェクトという 2 つのコンポーネントがあります。 マネージド ID には、サービス プリンシパル オブジェクトのみが含まれます。

マネージド ID にはディレクトリ内にアプリケーション オブジェクトがありません。これは、Microsoft Graph のアプリアクセス許可を付与するために一般的に使用されます。 代わりに、マネージド ID に対する Microsoft Graph のアクセス許可をサービス プリンシパルに直接付与する必要があります。

#### マネージド ID に関連付けられる資格情報は何ですか? その有効期間とローテーションの頻度は?

注

マネージド ID の認証方法は、内部的な実装の詳細であり、予告なく変更されます。

マネージド ID には、証明書ベースの認証が使用されます。 各マネージド ID の資格情報は有効期限が 90 日で、45 日後にローテーションされます。

#### 要求で ID を指定しない場合、IMDS ではどの ID が規定値になりますか?

- システム割り当てマネージド ID が有効で、要求内に ID が指定されていない場合、Azure Instance Metadata Service (IMDS) ではシステム割り当てマネージド ID が既定で使用されます。
- システム割り当てマネージド ID が有効ではなく、ユーザー割り当てマネージド ID が1つのみの場合、その単一のユーザー割り当てマネージド ID が既定値となります。

>
> 何らかの理由で別のユーザー割り当てマネージド ID がリソースに割り当てられている場合、IMDS への要求はエラー `Multiple user assigned identities exist, please specify the clientId / resourceId of the identity in the token request` で失敗し始めます。 リソースに対して現在ユーザー割り当てマネージド ID が 1 つのみ存在する場合でも、要求で ID を明示的に指定することを強くお勧めします。
- システム割り当てマネージド ID が有効ではなく、複数のユーザー割り当てマネージド ID が存在する場合は、要求でマネージド ID を指定する必要があります。

### 制限事項

#### 同じマネージド ID を複数のリージョンで使用できますか?

簡単に言えば、はい、ユーザー割り当て済みマネージド ID は複数の Azure リージョンで使用できます。 詳しく回答すると、ユーザー割り当てマネージド ID はリージョン リソースとして作成されますが、Microsoft Entra ID で作成される、関連付けられた[サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals#service-principal-object) (SP) はグローバルに使用できます。 サービス プリンシパルはどの Azure リージョンからでも使用でき、その可用性は Microsoft Entra ID の可用性に依存します。 たとえば、中南部リージョンでユーザー割り当て済みマネージド ID を作成し、そのリージョンが使用できなくなった場合、この問題はマネージド ID 自体の[コントロール プレーン アクティビティ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/control-plane-and-data-plane)のみに影響します。 マネージド ID を使用するように構成されているリソースが実行するアクティビティは影響を受けません。

#### Azure リソースのマネージド ID は Azure Cloud Services (クラシック) で動作しますか?

現時点では、Azure リソースのマネージド ID で [Azure Cloud Services (クラシック)](https://learn.microsoft.com/ja-jp/azure/cloud-services/cloud-services-choose-me) はサポートされていません。

#### Azure リソースのマネージド ID のセキュリティ境界は何ですか?

ID のセキュリティ境界は、ID の添付先リソースです。 たとえば、Azure リソースのマネージド ID が有効な仮想マシンのセキュリティ境界は、仮想マシンになります。 VM 上で実行されているすべてのコードは、Azure リソース エンドポイントと要求トークンのマネージド ID を呼び出すことができます。 このエクスペリエンスは、マネージドIDをサポートする他のリソースで作業する際にも似ています。

#### サブスクリプションを別のディレクトリに移動する場合、マネージド ID は自動的に再作成されますか?

いいえ。サブスクリプションを別のディレクトリに移動する場合は、お客様が手動でそれらを作成し直し、Azure ロールの割り当てをもう一度許可する必要があります。

- システム割り当てマネージド ID の場合は、無効にしてから再度有効にします。
- ユーザー割り当てマネージド ID の場合、削除、再作成の後、必要なリソース (例： 仮想マシン) へ再度アタッチします

#### マネージド ID を使って違うディレクトリやテナント内のリソースへアクセスできますか?

いいえ。現在、マネージド ID ではクロスディレクトリのシナリオはサポートされていません。

#### マネージド ID に適用されるレート制限はありますか?

マネージド ID の制限には、Azure サービスの制限、Azure Instance Metadata Service (IMDS) の制限、および Microsoft Entra サービスの制限に対する依存関係があります。

- **Azure サービスの制限**では、テナントとサブスクリプションのレベルで実行できる作成操作の数が定義されます。 ユーザー割り当てマネージド ID には、名前付けの方法に関する[制限](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-subscription-service-limits#managed-identity-limits)もあります。
- **IMDS**: 一般に、IMDS への要求は、1 秒あたり 5 つの要求に制限されます。 このしきい値を超える要求は、429 応答で拒否されます。 マネージド ID カテゴリに対する要求は、1 秒あたり 20 個の要求、同時要求数は 5 個に制限されます。 詳細については、「[Azure Instance Metadata Service (Windows)](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/instance-metadata-service?tabs=windows#managed-identity)」の記事を参照してください。
- **Microsoft Entra サービス**: 「[Microsoft Entra サービスの制限と制約](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)」で説明されているように、各マネージド ID は、Microsoft Entra テナントにおけるオブジェクトのクォータ制限に加算されます。

#### ユーザー割り当てマネージド ID を異なるリソース グループまたはサブスクリプションに移動できますか?

ユーザー割り当てマネージド ID が異なるリソース グループへの移動はサポートされていません。 別のリソース グループまたはサブスクリプションでマネージド ID を使用する必要がある場合は、新しいユーザー割り当てマネージド ID を作成し、それに必要なアクセス許可を割り当てる必要があります。

#### マネージド ID トークンはキャッシュされていますか?

マネージド ID のトークンは、パフォーマンスと回復性を確保するために、基盤の Azure インフラストラクチャによってキャッシュされます。マネージド ID のバックエンド サービスでは、リソース URI ごとのキャッシュを約 24 時間保持します。 これは、たとえば、マネージド ID のアクセス許可の変更が有効になるまでに数時間かかる場合があることを意味しています。 現時点では、有効期限が切れる前にマネージド ID のトークンを強制的に更新することはできません。 詳細については、「[認可のためのマネージド ID の使用の制限](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identity-best-practice-recommendations#limitation-of-using-managed-identities-for-authorization)」を参照してください。

#### マネージド ID はソフト削除されますか?

はい。マネージド ID は 30 日間論理的に削除されます。 論理的に削除されたマネージド ID サービス プリンシパルを表示することはできますが、復元したり、完全に削除したりすることはできません。

#### マネージド ID が削除された後のトークンはどうなりますか。

マネージド ID が削除されると、その ID に以前に関連付けられていた Azure リソースは、その ID の新しいトークンを要求できなくなります。 ID が削除される前に発行されたトークンは、元の有効期限まで有効です。 一部のターゲット エンドポイントの認可システムでは、ディレクトリで ID に関する他のチェックが実行される場合があります。その場合、オブジェクトが見つからないため、要求は失敗します。 ただし、Azure RBAC などの一部のシステムでは、有効期限が切れるまで、そのトークンからの要求を引き続き受け入れます。

### マネージド ID のディレクトリ オブジェクト クォータ

各マネージド ID には、Microsoft Entra IDにサービス プリンシパルがあります。 このサービス プリンシパルは、ユーザー、グループ、アプリケーション、デバイス、サービス プリンシパルなどの他のディレクトリ オブジェクトと共に、テナントのディレクトリ オブジェクト クォータにカウントされます。

他の重要なオブジェクトのディレクトリ容量を保持するために、新しいサービス プリンシパルによってディレクトリの使用量がテナントの合計ディレクトリ オブジェクト クォータの **98%を ** 超える場合、マネージド ID の作成はブロックされます。

98% しきい値は、ディレクトリ オブジェクトの合計使用量に適用されます。 マネージド ID のみをカウントする個別のクォータではありません。

Important

この検証は、新しいマネージド ID サービス プリンシパルの作成に影響します。 既存のマネージド ID は引き続き機能し、トークンを取得する機能には影響しません。

#### クォータの影響を受ける操作

ディレクトリ クォータの検証は、次の操作に影響する可能性があります。

- ユーザー割り当てマネージド ID の作成。
- Azure リソースでシステム割り当てマネージド ID を有効にする。
- 操作に新しいサービス プリンシパルが必要な場合は、システム割り当てマネージド ID を再度有効にします。

既存のユーザー割り当てマネージド ID を別のAzure リソースに割り当てると、別のサービス プリンシパルは作成されません。 したがって、割り当ては別のディレクトリ オブジェクトを使用せず、この検証によってブロックされません。

#### 98% しきい値のしくみ

Microsoft Entra は、マネージド ID 用のサービス プリンシパルを作成する前に、テナントの現在のディレクトリ オブジェクトの使用状況を評価します。

サービス プリンシパルを追加すると、ディレクトリの使用量がテナントの合計クォータの 98% を超える場合、作成はブロックされます。

たとえば、テナントに 300,000 個のディレクトリ オブジェクトのクォータがある場合、マネージド ID 作成のしきい値は 294,000 オブジェクトです。 使用量を 294,000 オブジェクトに増やす要求はブロックされます。

残りの容量は、他のディレクトリ操作と重要なオブジェクトで使用できます。 テナントのディレクトリ オブジェクトクォータの合計は増加しません。

#### エラー メッセージ

マネージド ID の作成がブロックされると、次のメッセージのようなエラーが表示されます。

>
> テナントのディレクトリ オブジェクト クォータの制限に達しました。 新しいマネージド ID の作成がブロックされます。 ディレクトリ クォータの制限を増やすか、オブジェクトを削除して使用されるクォータを減らすように管理者に依頼してください。

Important

ソフト削除されたオブジェクトは、全体のクォータ使用量に含まれます。

#### ブロックされたマネージド ID 操作を解決する

ディレクトリの使用率が 98% のしきい値を下回った後、または Microsoft サポートがテナントのクォータを増やした後に、マネージド ID の作成または有効化を再試行してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/managed-identities-glossary"} -->
## マネージド ID 用語集 - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-glossary
- Service: entra-id / managed-identities
- Article date: 2025-08-19
- Summary: Azure リソースのマネージド ID に関連する用語の包括的な用語集。

この用語集では、Azure リソースのマネージド ID とその広範なエコシステムに関連する主要な用語と概念を定義します。

### A

**アプリケーション オブジェクト** : Microsoft Entra ID でのアプリケーションのグローバルに一意の構成。 マネージド ID にはアプリケーション オブジェクトがなく、サービス プリンシパル オブジェクトのみが含まれます。

**Azure Instance Metadata Service (IMDS)** : Azure Resource Manager を使用して作成されたすべての VM で使用できる REST エンドポイント。 IMDS では、資格情報を必要とせずにマネージド ID トークンにアクセスできます。

**Azure Resource Manager** : リソースを作成、更新、削除するための管理レイヤーを提供する Azure のデプロイと管理サービス。

### C

**ワークロード ID の条件付きアクセス** : 場所やリスクなどの条件に基づいてアクセスを制御するために、組織が所有するサービス プリンシパルに適用できるセキュリティ ポリシー。

**継続的アクセス評価 (CAE)** : ワークロード ID の条件付きアクセス ポリシーとリスク シグナルをリアルタイムで適用し、即時失効機能を提供する機能です。

**コントロール プレーン** : リソースの作成、更新、削除など、Azure リソースに対して実行される管理操作。 データ プレーン操作とは異なります。

**資格情報のローテーション** : 認証資格情報を定期的に変更するプロセス。 マネージド ID は、90 日間の証明書の有効期限と 45 日間のローテーション サイクルで、これを自動的に処理します。

### D

**データ プレーン** : ストレージ アカウントからの読み取りやデータベースのクエリなど、リソースによって提供されるデータまたは機能と対話する操作。

**デバイス ID** : デスクトップ コンピューター、モバイル デバイス、IoT センサーなどの物理デバイスまたは仮想デバイスを表すコンピューター ID の種類。

### F

**フェデレーション ID 資格情報 (FIC):** マネージド ID を Microsoft Entra アプリケーションの資格情報として使用し、ワークロード ID フェデレーションを有効にする構成。

### H

**人間 ID** : 従業員、外部ユーザー、顧客、コンサルタント、ベンダー、パートナーなど、人を表す ID。

### I

**ID** : リソースへのアクセスを認証および承認できるディレクトリ オブジェクト。 マネージド ID コンテキストでは、人間 ID とワークロード ID の両方を参照します。

**分離スコープ** : ユーザー割り当てマネージド ID のプロパティ。ID をリージョン間で使用できるか (なし)、1 つのリージョン (リージョン) 内でのみ使用できるかを決定します。

### L

**最小特権** : ユーザーとサービスに、その機能を実行するために必要な最小限のアクセス許可のみを付与するセキュリティ原則。

**ライフサイクル管理** : アクセス許可とリソースの適切なクリーンアップを含む、作成から更新、削除までの ID を管理するプロセス。

**有効期間が長いトークン (LLT):** 継続的なセキュリティ チェックの対象となる継続的アクセス評価で使用される、延長期間のアクセス トークン (最大 24 時間)。

### M

**マシン ID** : デバイス ID とワークロード ID の両方を含む非人間 ID。 人間の ID と区別するために使用されます。

**マネージド ID** : Microsoft Entra 認証をサポートする他のリソースにアクセスするときに認証する ID を Azure リソースに提供する、Microsoft Entra ID の自動マネージド ID。

**マネージド ID 共同作成者ロール** : ユーザー割り当てマネージド ID の作成、読み取り、更新、削除を可能にする組み込みの Azure ロール。

**マネージド ID オペレーター ロール** : ユーザー割り当てマネージド ID の読み取りとリソースへの割り当てを可能にする組み込みの Azure ロール。

**Microsoft 認証ライブラリ (MSAL)** : アプリケーションが保護された Web API にアクセスするためのトークンを Microsoft Entra ID から取得できるようにするライブラリ。

**Microsoft Entra ID Protection** : ユーザー ID とワークロード ID の両方に対する ID ベースのリスクを検出、調査、修復するサービス。

### P

**プリンシパル ID** : Microsoft Entra ID でのマネージド ID のサービス プリンシパルの一意識別子。

### R

**リージョンの分離** : ユーザー割り当てマネージド ID が同じ Azure リージョン内のリソースでのみ使用されるように制限するセキュリティ機能。

**リソース ID** : `/subscriptions/{subscription-id}/resourceGroups/{resource-group}/providers/{resource-provider}/{resource-type}/{resource-name}`形式に従う Azure リソースの一意識別子。

**Role-Based アクセス制御 (RBAC)** : ロールの割り当てに基づいて、Azure リソースのきめ細かいアクセス管理を提供する Azure の承認システム。

### S

**サービス プリンシパル** : 特定の Microsoft Entra テナント内のアプリケーション オブジェクトのローカル表現。 すべてのマネージド ID にはサービス プリンシパルがありますが、すべてのサービス プリンシパルがマネージド ID であるわけではありません。

**ソース リソース** : マネージド ID コンテキストで、マネージド ID が割り当てられている Azure リソース (仮想マシンやアプリ サービスなど)。

**System-Assigned マネージド ID** : Azure リソースの一部として作成され、同じライフサイクルを共有するマネージド ID。 リソースが削除されると、ID は自動的に削除されます。

### 火

**ターゲット リソース** : マネージド ID コンテキストでは、ソース リソースがマネージド ID (ストレージ アカウントやキー コンテナーなど) を使用してアクセスするリソース。

**トークン エンドポイント** : マネージド ID が他の Azure サービスへの認証用のアクセス トークンを要求するために使用する IMDS エンドポイント。

### U

**User-Assigned マネージド ID** : 複数の Azure リソースに割り当てることができ、独立したライフサイクルを持つスタンドアロン Azure リソースとして作成されたマネージド ID。

### W

**ワークロード ID** : アプリケーション、サービス プリンシパル、マネージド ID を含む非人間 ID のカテゴリ。 これらの ID は、人間のユーザーではなくソフトウェア ワークロードを表します。

**ワークロード ID フェデレーション** : 外部 ID プロバイダーがシークレットまたは証明書を管理することなく、Microsoft Entra ID で保護されたリソースにアクセスできるようにする機能。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/managed-identities-isolation-scope"} -->
## ユーザー割り当てマネージド ID の分離スコープ - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-isolation-scope
- Service: entra-id / managed-identities
- Article date: 2025-07-16
- Summary: ユーザー割り当てマネージド ID の分離スコープと、それがセキュリティと回復性を向上させる方法について説明します。

マネージド ID の分離スコープの値は、 `None` または `Regional`に設定できます。

- *なし* (既定値): ID はすべてのリージョンで使用できます
- *リージョン*: ID は、マネージド ID と同じリージョン内のソース リソースでのみ使用できます

分離スコープを `Regional` に設定すると、マネージド ID の使用が厳密にスコープ設定され、セキュリティと運用上の境界に合わせて調整されます。 ユーザー割り当てマネージド ID のリージョン分離は、マネージド ID を使用できる場所を制限することで、セキュリティと回復性を向上するのに役立ちます。

### リージョン分離を理解する

マネージド ID を使用する場合、次の 2 種類のリソースがあります。

- **ソース リソース**: マネージド ID が割り当てられているリソース
- **ターゲット リソース**: ソース リソースがマネージド ID を使用してアクセスするリソース

たとえば、App Service がマネージド ID を使用してストレージ アカウントにアクセスする必要がある場合、App Service はソース リソースであり、ストレージ アカウントはターゲット リソースです。

リージョン分離は、マネージド ID とソース リソースの間の関係に適用されます。 リージョン分離スコープを有効にする場合:

- マネージド ID は、同じリージョン内のソース リソースにのみ割り当てることができます。
- ソース リソースは、(適切なロールのアクセス許可を持つ) 他のリージョンのターゲット リソースに引き続きアクセスできます。 たとえば、米国西部のソース リソースに割り当てられたマネージド ID を使用して、スペイン中部またはアラブ首長国連邦北部のターゲット リソースにアクセスできます。

### リージョン分離の利点

リージョン分離には、いくつかの主な利点があります。

#### セキュリティの露出を最小限に抑える

マネージド ID を 1 つのリージョンにスコープすることで、複数のリージョンで使用されるのを防ぎ、ID が侵害された場合の爆発半径を減らすことができます。 分離しないと、あるリージョンで発行されたトークンを使用して別のリージョンのリソースにアクセスできるため、資格情報の盗難や誤用による潜在的な影響が大きくなります。

#### 最小限の特権を設計で適用する

リージョンの分離により、ID に自身のリージョン内のリソースへのアクセスのみが許可され、ID の割り当てを解除することでサービスが不要な特権を保持することを防ぐことができます。 これにより、チームは、他のリージョンのサービスやデータへのアクセスを意図せずに許可することを回避できます。

#### 1 つのリージョンに対するエラーが含まれています

マネージド ID が設定ミスを犯している、または侵害された場合、地域的な範囲設定により、インシデントや障害が確実に封じ込まれます。 分離がないと、1 つの ID によって複数のリージョン間でサービスが中断され、障害の分離戦略が損なわれます。

#### サービスの回復性を向上させる

リージョンスコープでは、ID の構成ミスによって発生する停止の範囲が制限されます。 たとえば、ロールの割り当てまたはトークンの発行が 1 つのリージョンで失敗した場合、グローバル フットプリント全体にカスケードされません。

#### 堅牢なディザスター リカバリーをサポート

リージョン固有の ID を使用すると、リージョンごとに独立した復旧戦略を設計できます。 これにより、リージョンのフェールオーバーまたは復旧中にグローバル ID がボトルネックまたは単一障害点になるシナリオを回避できます。

### 分離スコープを none に設定するリスク

ユーザー割り当てマネージド ID の分離スコープ プロパティが `None` (既定値) に設定されていない場合は、すべてのリージョンで ID を使用できます。 これにより、いくつかのリスクが発生します。

- リージョン間トークンの使用: あるリージョンで発行されたトークンを使用して、別のリージョンのリソースにアクセスし、データ所在地またはコンプライアンスの境界に違反する可能性があります。
- 意図しないアクセス: 知らないうちに複数のリージョンのリソースに ID を割り当てて、意図したよりも広範なアクセスが行われる可能性があります。
- 監査とトラブルシューティングが困難: 分離しないと、ID を使用しているリソースと場所を追跡することが困難になり、インシデント対応が複雑になります。
- 爆発半径の増加: 侵害された ID を使用して、1 つのリージョンだけでなく、デプロイ全体のリソースにアクセスできます。

これらのリスクを回避するには、ユーザー割り当てマネージド ID の作成時に分離スコープを `Regional` に設定します。

### ベスト プラクティス

リージョン分離の利点を最大化するには:

- リージョンごとに 1 つのマネージド ID を使用する: サービスがデプロイされている Azure リージョンごとに個別のマネージド ID を作成する
- マネージド ID リージョンとコンピューティング リソースの照合: マネージド ID がソース リソースと同じリージョンに存在することを確認する
- 依存関係の計画: マネージド ID を共有するすべてのコンピューティング リソースが同じ依存関係にアクセスできることを確認します。 これらの依存関係は、コンピューティング リソース (VM、Function App、App Service など) がマネージド ID を使用してアクセスする必要があるダウンストリーム サービス、リソース、またはシステムです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/managed-identities-status"} -->
## マネージド ID を持つ Azure サービスとリソース - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status
- Service: entra-id / managed-identities
- Article date: 2025-05-09
- Summary: セキュリティで保護された資格情報のない認証のためにマネージド ID をサポートする Azure サービスとリソースの種類について説明します。

Azure リソースのマネージド アイデンティティは、Microsoft Entra ID で自動的に管理される ID を提供し、Azure サービスへの安全で資格情報を不要とする認証を可能にします。 この記事では、マネージド ID をサポートする Azure サービスとリソースの種類の一覧を示します。

このページには、マネージド ID を使用して他の Azure リソースにアクセスできるサービスのコンテンツへのリンクと、マネージド ID をサポートする Azure リソース プロバイダーとリソースの種類の一覧が表示されます。

その他のリソース プロバイダーの名前空間情報は、 [Azure サービスのリソース プロバイダー](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-services-resource-providers)で入手できます。

重要

新しい技術コンテンツが毎日追加されます。 このリストには、マネージド ID に関する記事すべてが含まれているわけではありません。 マネージド ID のサポートの詳細については、各サービスのコンテンツ セットを参照してください。

### マネージド ID をサポートするサービス

次の Azure サービスが、Azure リソースのマネージド ID をサポートしています。

| サービス名 | ドキュメント |
| --- | --- |
| API Management | [Azure API Management でマネージド ID を使用する](https://learn.microsoft.com/ja-jp/azure/api-management/api-management-howto-use-managed-service-identity) |
| Application Gateway | [Key Vault 証明書を使用した TLS 終端](https://learn.microsoft.com/ja-jp/azure/application-gateway/key-vault-certs) |
| Azure App Configuration | [Azure App Configuration でマネージド ID を使用する方法](https://learn.microsoft.com/ja-jp/azure/azure-app-configuration/overview-managed-identity) |
| Azure アプリ Service | [App Service と Azure Functions でマネージド ID を使用する方法](https://learn.microsoft.com/ja-jp/azure/app-service/overview-managed-identity) |
| Azure Arc 対応 Kubernetes | [クイックスタート: 既存の Kubernetes クラスターを Azure Arc に接続する](https://learn.microsoft.com/ja-jp/azure/azure-arc/kubernetes/quickstart-connect-cluster) |
| Azure Arc 対応サーバー | [Azure Arc 対応サーバーでの Azure リソースに対して認証を行う](https://learn.microsoft.com/ja-jp/azure/azure-arc/servers/managed-identity-authentication) |
| Azure Automanage | [Automanage アカウントの修復](https://learn.microsoft.com/ja-jp/azure/automanage/repair-automanage-account) |
| Azure Automation | [Azure Automation アカウントの認証の概要](https://learn.microsoft.com/ja-jp/azure/automation/automation-security-overview#managed-identities) |
| Azure Batch | [Azure Key Vault とマネージド ID を使用して Azure Batch アカウントのカスタマー マネージド キーを構成する](https://learn.microsoft.com/ja-jp/azure/batch/batch-customer-managed-key)[Batch プールでマネージド ID を構成する](https://learn.microsoft.com/ja-jp/azure/batch/managed-identity-pools) |
| Azure Blueprints | [ブループリント デプロイのステージ](https://learn.microsoft.com/ja-jp/azure/governance/blueprints/concepts/deployment-stages) |
| Azure Cache for Redis | [Azure Cache for Redis におけるストレージ アカウントのマネージド ID](https://learn.microsoft.com/ja-jp/azure/azure-cache-for-redis/cache-managed-identity) |
| Azure Chaos Studio | [Chaos Studio ワークスペースでのアクセス許可と ID](https://learn.microsoft.com/ja-jp/azure/chaos-studio/chaos-studio-workspace-permissions)[Azure Chaos Studio (クラシック) でのアクセス許可とセキュリティ](https://learn.microsoft.com/ja-jp/azure/chaos-studio/chaos-studio-permissions-security#user-assigned-managed-identity) |
| Azure Communications Gateway | [Azure Communications Gateway をデプロイする](https://learn.microsoft.com/ja-jp/azure/communications-gateway/deploy) |
| Azure Communication Services | [Azure Communication Services でマネージド ID を使用する方法](https://learn.microsoft.com/ja-jp/azure/communication-services/how-tos/managed-identity) |
| Azure Container Apps | [Azure Container Apps のマネージド ID](https://learn.microsoft.com/ja-jp/azure/container-apps/managed-identity) |
| Azure コンテナー インスタンス | [Azure Container Instances でマネージド ID を使用する方法](https://learn.microsoft.com/ja-jp/azure/container-instances/container-instances-managed-identity) |
| Azure Container Registry | [ACR タスクで Azure マネージド ID を使用する](https://learn.microsoft.com/ja-jp/azure/container-registry/container-registry-tasks-authentication-managed-identity) |
| Azure CycleCloud | [マネージド ID の使用](https://learn.microsoft.com/ja-jp/azure/cyclecloud/how-to/managed-identities?view=cyclecloud-8&preserve-view=true) |
| Azure AI サービス | [Azure AI サービス用に Azure Key Vault でカスタマー マネージド キーを構成する](https://learn.microsoft.com/ja-jp/azure/ai-services/encryption/cognitive-services-encryption-keys-portal) |
| Azure Data Box | [Azure Key Vault のカスタマー マネージド キーを Azure Data Box に使用する](https://learn.microsoft.com/ja-jp/azure/databox/data-box-customer-managed-encryption-key-portal) |
| Azure Data Explorer | [Azure Data Explorer クラスターのマネージド ID を構成する](https://learn.microsoft.com/ja-jp/azure/data-explorer/configure-managed-identities-cluster?tabs=portal) |
| Azure Data Factory | [Data Factory のマネージド ID](https://learn.microsoft.com/ja-jp/azure/data-factory/data-factory-service-identity) |
| Azure Data Lake Storage Gen1 | [Azure Storage の暗号化のためのカスタマー マネージド キー](https://learn.microsoft.com/ja-jp/azure/storage/common/customer-managed-keys-overview) |
| Azure Data Share | [Azure Data Share のロールと要件](https://learn.microsoft.com/ja-jp/azure/data-share/concepts-roles-permissions) |
| Azure DevTest Labs | [Azure DevTest Labs のラボ仮想マシン上でユーザー割り当てのマネージド ID を有効にする](https://learn.microsoft.com/ja-jp/azure/devtest-labs/enable-managed-identities-lab-vms) |
| Azure Digital Twins | [マネージド ID を有効にして、Azure Digital Twins イベントをルーティングできるようにします](https://learn.microsoft.com/ja-jp/azure/digital-twins/how-to-enable-managed-identities-portal) |
| Azure Event Grid | [マネージド ID を使用したイベント配信](https://learn.microsoft.com/ja-jp/azure/event-grid/managed-service-identity) |
| Azure Event Hubs | [Microsoft Entra ID を使って Event Hubs リソースにアクセスするためのマネージド ID を認証する](https://learn.microsoft.com/ja-jp/azure/event-hubs/authenticate-managed-identity) |
| Azure File Sync | [Azure File Sync でマネージド ID を使用する方法](https://learn.microsoft.com/ja-jp/azure/storage/file-sync/file-sync-managed-identities) |
| Azure Files | MICROSOFT ENTRA ID |
| Azure Health Data Services ワークスペース サービス | [Azure Health Data Services の認証と認可](https://learn.microsoft.com/ja-jp/azure/healthcare-apis/authentication-authorization) |
| Azure Health Data Services の匿名化サービス | [匿名化サービスを使用したマネージド ID を使用する](https://learn.microsoft.com/ja-jp/azure/healthcare-apis/deidentification/managed-identities) |
| Azure イメージ ビルダー | [Azure Image Builder の概要](https://learn.microsoft.com/ja-jp/azure/virtual-machines/image-builder-overview#permissions) |
| Azure インポート/エクスポート | [Azure Key Vault でユーザーが管理するキーを Import/Export サービスのために使用する](https://learn.microsoft.com/ja-jp/azure/import-export/storage-import-export-encryption-key-portal) |
| Azure IoT Hub | [Private Link とマネージド ID を使用した仮想ネットワークの IoT Hub サポート](https://learn.microsoft.com/ja-jp/azure/iot-hub/virtual-network-support) |
| Azure Kubernetes Service (AKS) | [Azure Kubernetes Service でマネージド ID を使用する](https://learn.microsoft.com/ja-jp/azure/aks/use-managed-identity) |
| Azure Load Testing | [Azure Load Testing にマネージド ID を使用する](https://learn.microsoft.com/ja-jp/azure/load-testing/how-to-use-a-managed-identity) |
| Azure Logic Apps | [Azure Logic Apps でマネージド ID を使用して Azure リソースへのアクセスを認証する](https://learn.microsoft.com/ja-jp/azure/logic-apps/create-managed-service-identity) |
| Azure Log Analytics ワークスペース | [Log Analytics ワークスペースのマネージド ID を有効にする](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/private-storage?tabs=azure-portal##link-storage-accounts-to-your-log-analytics-workspace) |
| Azure Log Analytics クラスター | [Azure Monitor のカスタマー マネージド キー](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/customer-managed-keys) |
| Azure Machine Learning サービス | [Azure Machine Learning でマネージド ID を使用する](https://learn.microsoft.com/ja-jp/azure/machine-learning/how-to-identity-based-service-authentication?tabs=python) |
| Azure マネージド ディスク | [Azure portal を使用して、マネージド ディスクでカスタマー マネージド キーを使用し、サーバー側の暗号化を有効にする](https://learn.microsoft.com/ja-jp/azure/virtual-machines/disks-enable-customer-managed-keys-portal) |
| Azure Media Services | [マネージド ID](https://learn.microsoft.com/ja-jp/azure/media-services/latest/concept-managed-identities) |
| Azure Monitor | [Azure Monitor のカスタマー マネージド キー](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/customer-managed-keys?tabs=portal) |
| Azure Policy | [Azure Policy を使って準拠していないリソースを修復する](https://learn.microsoft.com/ja-jp/azure/governance/policy/how-to/remediate-resources) |
| Microsoft Purview | [Microsoft Purview でのソース認証用の資格情報](https://learn.microsoft.com/ja-jp/purview/manage-credentials) |
| Azure Quantum | [マネージド ID を使用して認証する](https://learn.microsoft.com/ja-jp/azure/quantum/optimization-authenticate-managed-identity) |
| Azure Resource Mover | [(リソース グループ) のリソースをリージョン間で移動する](https://learn.microsoft.com/ja-jp/azure/resource-mover/move-region-within-resource-group) |
| Azure Site Recovery | [プライベート エンドポイントを使用してマシンをレプリケートする](https://learn.microsoft.com/ja-jp/azure/site-recovery/azure-to-azure-how-to-enable-replication-private-endpoints#enable-the-managed-identity-for-the-vault) |
| Azure Search | [マネージド ID を使用してデータ ソースへのインデクサー接続を設定する](https://learn.microsoft.com/ja-jp/azure/search/search-howto-managed-identities-data-sources) |
| Azure Service Bus | [Microsoft Entra ID を使って Azure Service Bus リソースにアクセスするためのマネージド ID を認証する](https://learn.microsoft.com/ja-jp/azure/service-bus-messaging/service-bus-managed-service-identity) |
| Azure Service Fabric | [Service Fabric での Azure のマネージド ID の使用](https://learn.microsoft.com/ja-jp/azure/service-fabric/concepts-managed-identity) |
| Azure SignalR Service | [Azure SignalR Service のマネージド ID](https://learn.microsoft.com/ja-jp/azure/azure-signalr/howto-use-managed-identity) |
| Azure Spring Apps | [Azure Spring Apps のアプリケーションのシステム割り当てマネージド ID を有効にする](https://learn.microsoft.com/ja-jp/azure/spring-apps/how-to-enable-system-assigned-managed-identity) |
| Azure SQL | [Azure SQL 用の Microsoft Entra でのマネージド ID](https://learn.microsoft.com/ja-jp/azure/azure-sql/database/authentication-azure-ad-user-assigned-managed-identity) |
| Azure SQL Managed Instance | [Azure SQL 用の Microsoft Entra でのマネージド ID](https://learn.microsoft.com/ja-jp/azure/azure-sql/database/authentication-azure-ad-user-assigned-managed-identity) |
| Azure Stack Edge | [Azure Key Vault を使用して Azure Stack Edge シークレットを管理する](https://learn.microsoft.com/ja-jp/azure/databox-online/azure-stack-edge-gpu-activation-key-vault#recover-managed-identity-access) |
| Azure Static Web Apps | [Azure Key Vault 内の認証シークレットの保護](https://learn.microsoft.com/ja-jp/azure/static-web-apps/key-vault-secrets) |
| Azure Stream Analytics | [マネージド ID を使用して Azure Data Lake Storage Gen1 に対して Stream Analytics を認証する](https://learn.microsoft.com/ja-jp/azure/stream-analytics/stream-analytics-managed-identities-adls) |
| Azure Synapse | [Azure Synapse ワークスペース マネージド ID](https://learn.microsoft.com/ja-jp/azure/data-factory/data-factory-service-identity) |
| Azure 仮想マシン イメージビルダー | [Azure CLI を使用して Azure Image Builder サービスのアクセス許可を構成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/image-builder-permissions-cli#using-managed-identity-for-azure-storage-access) |
| Azure 仮想マシン スケール セット | [仮想マシン スケール セットでマネージド ID を構成する - Azure CLI](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-cli-windows-vmss) |
| Azure 仮想マシン | [Azure で仮想マシンをセキュリティで保護し、ポリシーを使用する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/security-policy#managed-identities-for-azure-resources) |
| Azure Web PubSub サービス | [Azure Web PubSub サービスでのマネージド ID](https://learn.microsoft.com/ja-jp/azure/azure-web-pubsub/howto-use-managed-identity) |

### マネージド ID をサポートするリソース プロバイダーとリソースの種類

次のリソース プロバイダーとリソースの種類では、マネージド ID がサポートされています。

| Namespace | リソースタイプ | アイデンティティの種類 |
| --- | --- | --- |
| Microsoft.AVS | privateClouds | システム割り当てユーザー割り当て |
| Microsoft.ApiManagement | サービス | システム割り当てユーザー割り当て |
| Microsoft.App | ビルダー | システム割り当てユーザー割り当て |
| Microsoft.App | コンテナーアプリケーション | システム割り当てユーザー割り当て |
| Microsoft.App | ジョブ | システム割り当てユーザー割り当て |
| Microsoft.App | 管理された環境 | システム割り当てユーザー割り当て |
| Microsoft.App | sessionPools | システム割り当てユーザー割り当て |
| Microsoft.AppConfiguration | configurationStores | システム割り当てユーザー割り当て |
| Microsoft.AppPlatform | 春 | システム割り当て |
| Microsoft.AppPlatform | Spring/アプリ | システム割り当てユーザー割り当て |
| Microsoft.Automation | automationAccounts | システム割り当てユーザー割り当て |
| Microsoft.AzureStackHCI | クラスター | システム割り当て |
| Microsoft.AzureStackHCI | devicePools | システム割り当て |
| Microsoft.AzureStackHCI | エッジマシン | システム割り当て |
| Microsoft.AzureStackHCI | virtualMachines | システム割り当て |
| Microsoft.Batch | batchAccounts | システム割り当てユーザー割り当て |
| Microsoft.Batch | バッチアカウント/プール | ユーザー割り当て |
| Microsoft.Blueprint | blueprintAssignments | システム割り当てユーザー割り当て |
| Microsoft.Cache | Redis | システム割り当てユーザー割り当て |
| Microsoft.Cache | redisEnterprise | システム割り当てユーザー割り当て |
| Microsoft.Cdn | プロフィール | システム割り当てユーザー割り当て |
| Microsoft.ChangeAnalysis | プロファイル | システム割り当て |
| Microsoft.CognitiveServices | accounts | システム割り当てユーザー割り当て |
| Microsoft.CognitiveServices | accounts/encryptionScopes |  |
| Microsoft.Communication | 通信サービス | システム割り当てユーザー割り当て |
| Microsoft.Compute | ディスク暗号化セット | システム割り当てユーザー割り当て |
| Microsoft.Compute | ギャラリー | システム割り当てユーザー割り当て |
| Microsoft.Compute | virtualMachineScaleSets | システム割り当てユーザー割り当て |
| Microsoft.Compute | virtualMachines | システム割り当てユーザー割り当て |
| Microsoft.ContainerInstance | containerGroups | システム割り当てユーザー割り当て |
| Microsoft.ContainerInstance | containerScaleSets | システム割り当てユーザー割り当て |
| Microsoft.ContainerInstance | nGroups | システム割り当てユーザー割り当て |
| Microsoft.ContainerRegistry (マイクロソフトのコンテナレジストリ) | レジストリ | システム割り当てユーザー割り当て |
| Microsoft.ContainerRegistry (マイクロソフトのコンテナレジストリ) | レジストリ/資格セット | システム割り当て |
| Microsoft.ContainerRegistry (マイクロソフトのコンテナレジストリ) | レジストリ/エクスポートパイプライン | システム割り当てユーザー割り当て |
| Microsoft.ContainerRegistry (マイクロソフトのコンテナレジストリ) | registries/importPipelines | システム割り当てユーザー割り当て |
| Microsoft.ContainerRegistry (マイクロソフトのコンテナレジストリ) | registries/taskRuns | ユーザー割り当て |
| Microsoft.ContainerRegistry (マイクロソフトのコンテナレジストリ) | レジストリ/タスク | システム割り当てユーザー割り当て |
| マイクロソフト・コンテナーサービス | 艦隊 | システム割り当てユーザー割り当て |
| マイクロソフト・コンテナーサービス | マネージドクラスター | システム割り当てユーザー割り当て |
| マイクロソフト・コンテナーサービス | managedclustersnapshots | システム割り当てユーザー割り当て |
| マイクロソフト・コンテナーサービス | スナップショット | システム割り当てユーザー割り当て |
| Microsoft.CustomProviders | リソースプロバイダー | システム割り当て |
| Microsoft.DBforMariaDB | サーバー | システム割り当て |
| Microsoft.DBforMySQL | flexibleServers | ユーザー割り当て |
| Microsoft.DBforMySQL | サーバー | システム割り当て |
| Microsoft.DBforPostgreSQL | flexibleServers | システム割り当てユーザー割り当て |
| Microsoft.DBforPostgreSQL | serverGroupsv2 | ユーザー割り当て |
| Microsoft.DBforPostgreSQL | サーバー | システム割り当て |
| Microsoft.DataBox | ジョブ | システム割り当てユーザー割り当て |
| Microsoft.DataBoxEdge | DataBox Edge デバイス | システム割り当て |
| Microsoft.DataFactory | 工場 | システム割り当てユーザー割り当て |
| Microsoft.DataLakeStore | accounts | システム割り当て |
| Microsoft.DataMigration | SQLマイグレーションサービス | システム割り当て |
| Microsoft.DataMigration | migrationServices | システム割り当て |
| Microsoft.DataProtection | BackupVaults | システム割り当てユーザー割り当て |
| Microsoft.DataShare | accounts | システム割り当て |
| Microsoft.Databricks | accessConnectors | システム割り当てユーザー割り当て |
| Microsoft.DesktopVirtualization | ホストプール | システム割り当てユーザー割り当て |
| Microsoft.DevCenter | devcenters | システム割り当てユーザー割り当て |
| Microsoft.DevCenter | devcenters/encryptionsets | システム割り当てユーザー割り当て |
| Microsoft.DevCenter | projects | システム割り当てユーザー割り当て |
| Microsoft.DevCenter | プロジェクト/環境タイプ | システム割り当てユーザー割り当て |
| Microsoft.DevOpsInfrastructure | プール | ユーザー割り当て |
| Microsoft.DevTestLab | ラボ | システム割り当てユーザー割り当て |
| Microsoft.DevTestLab | labs/serviceRunners | システム割り当てユーザー割り当て |
| Microsoft.DeviceUpdate | accounts | システム割り当てユーザー割り当て |
| Microsoft.DeviceUpdate | updateAccounts | システム割り当てユーザー割り当て |
| Microsoft.Devices | IotHubs | システム割り当てユーザー割り当て |
| Microsoft.Devices | プロビジョニングサービス | システム割り当てユーザー割り当て |
| Microsoft.DigitalTwins | デジタルツインインスタンス | システム割り当てユーザー割り当て |
| Microsoft.DocumentDB | カサンドラクラスター | システム割り当て |
| Microsoft.DocumentDB | データベースアカウント | システム割り当てユーザー割り当て |
| Microsoft.DocumentDB | データベースアカウント/暗号化スコープ | ユーザー割り当て |
| Microsoft.DocumentDB | ガーネットクラスター | システム割り当て |
| Microsoft.DocumentDB | managedResources | システム割り当て |
| Microsoft.DocumentDB | スループットプールズ | システム割り当て |
| Microsoft.DocumentDB | throughputPools/throughputPoolAccounts | システム割り当て |
| Microsoft.ElasticSan | elasticSans/ボリュームグループ | システム割り当てユーザー割り当て |
| Microsoft.EventGrid | ドメイン | システム割り当てユーザー割り当て |
| Microsoft.EventGrid | 名前空間 | システム割り当てユーザー割り当て |
| Microsoft.EventGrid | パートナートピックス | システム割り当てユーザー割り当て |
| Microsoft.EventGrid | システムトピック | システム割り当てユーザー割り当て |
| Microsoft.EventGrid | トピック | システム割り当てユーザー割り当て |
| Microsoft.EventHub | 名前空間 | システム割り当てユーザー割り当て |
| Microsoft.HDInsight | クラスター | システム割り当てユーザー割り当て |
| Microsoft.HybridCompute | 機械 | システム割り当て |
| Microsoft.HybridNetwork | ネットワーク機能 | システム割り当てユーザー割り当て |
| Microsoft.HybridNetwork | 出版社たち | システム割り当て |
| Microsoft.HybridNetwork | serviceManagementContainers | システム割り当てユーザー割り当て |
| Microsoft.HybridNetwork | siteNetworkServices | システム割り当てユーザー割り当て |
| Microsoft.IoTCentral | IoTApps | システム割り当て |
| Microsoft.KeyVault | managedHSMs | ユーザー割り当て |
| Microsoft.Kubernetes | 接続されたクラスター | システム割り当て |
| Microsoft.KubernetesConfiguration | 拡張機能 | システム割り当て |
| Microsoft.Kusto | クラスター | システム割り当てユーザー割り当て |
| Microsoft.LoadTestService | 負荷テスト | システム割り当てユーザー割り当て |
| Microsoft.Logic | integrationAccounts | システム割り当てユーザー割り当て |
| Microsoft.Logic | integrationServiceEnvironments | システム割り当てユーザー割り当て |
| Microsoft.Logic | ワークフロー | システム割り当てユーザー割り当て |
| Microsoft.MachineLearningServices（マイクロソフトの機械学習サービス） | レジストリ | システム割り当てユーザー割り当て |
| Microsoft.MachineLearningServices（マイクロソフトの機械学習サービス） | 作業スペース | システム割り当てユーザー割り当て |
| Microsoft.MachineLearningServices（マイクロソフトの機械学習サービス） | ワークスペース/バッチエンドポイント | システム割り当て |
| Microsoft.MachineLearningServices（マイクロソフトの機械学習サービス） | workspaces/computes | システム割り当てユーザー割り当て |
| Microsoft.MachineLearningServices（マイクロソフトの機械学習サービス） | ワークスペース/推論プール/グループ | システム割り当てユーザー割り当て |
| Microsoft.MachineLearningServices（マイクロソフトの機械学習サービス） | ワークスペース/リンクサービス | システム割り当て |
| Microsoft.MachineLearningServices（マイクロソフトの機械学習サービス） | ワークスペース/オンラインエンドポイント | システム割り当てユーザー割り当て |
| Microsoft.Maps | accounts | システム割り当てユーザー割り当て |
| Microsoft.Media | メディアサービス | システム割り当てユーザー割り当て |
| Microsoft.Migrate | プロジェクトを移行する | システム割り当て |
| Microsoft.Migrate | プロジェクトの近代化 | システム割り当て |
| Microsoft.Migrate | コレクションを移動 | システム割り当て |
| Microsoft.MobileNetwork | mobileNetworks | ユーザー割り当て |
| Microsoft.MobileNetwork | packetCoreControlPlanes | ユーザー割り当て |
| Microsoft.MobileNetwork | simGroups | システム割り当てユーザー割り当て |
| Microsoft.NetApp | NetAppアカウント | システム割り当てユーザー割り当て |
| Microsoft.Network | networkWatchers/flowLogs | ユーザー割り当て |
| Microsoft Operational Insights（マイクロソフト オペレーショナル インサイツ） | クラスター | システム割り当てユーザー割り当て |
| Microsoft Operational Insights（マイクロソフト オペレーショナル インサイツ） | 作業スペース | システム割り当てユーザー割り当て |
| Microsoft.PowerPlatform | 企業方針 | システム割り当てユーザー割り当て |
| Microsoft.Purview | accounts | システム割り当てユーザー割り当て |
| Microsoft.Quantum | ワークスペース | システム割り当て |
| Microsoft.RecoveryServices | vaults | システム割り当てユーザー割り当て |
| Microsoft.RedHatOpenShift | オープンシフトクラスター (OpenShiftClusters) | システム割り当て |
| マイクロソフト・サーチ | searchServices | システム割り当てユーザー割り当て |
| Microsoft.Security | データスキャナー | システム割り当て |
| Microsoft.Security | pricings/securityOperators | システム割り当て |
| Microsoft.ServiceBus | 名前空間 | システム割り当てユーザー割り当て |
| Microsoft.ServiceFabric | クラスター | システム割り当てユーザー割り当て |
| Microsoft.ServiceFabric | クラスター/アプリケーション | システム割り当てユーザー割り当て |
| Microsoft.ServiceFabric | 管理されたクラスター | システム割り当てユーザー割り当て |
| Microsoft.ServiceFabric | マネージドクラスター/アプリケーション | システム割り当てユーザー割り当て |
| Microsoft.SignalRService | SignalR | システム割り当てユーザー割り当て |
| Microsoft.SignalRService | ウェブパブサブ | システム割り当てユーザー割り当て |
| Microsoft.Solutions | applications | システム割り当てユーザー割り当て |
| Microsoft.Sql | マネージドインスタンス | システム割り当てユーザー割り当て |
| Microsoft.Sql | サーバー | システム割り当てユーザー割り当て |
| Microsoft.Sql | サーバー/データベース | ユーザー割り当て |
| Microsoft.Sql | サーバー/ジョブエージェント | ユーザー割り当て |
| Microsoft.Storage | ストレージアカウント | システム割り当てユーザー割り当て |
| Microsoft.Storage | storageTasks | システム割り当て |
| Microsoft.StorageCache | amlFilesystems | ユーザー割り当て |
| Microsoft.StorageCache | caches | システム割り当てユーザー割り当て |
| Microsoft.StorageSync | storageSyncServices | システム割り当てユーザー割り当て |
| Microsoft.StreamAnalytics | ストリーミングジョブ | システム割り当てユーザー割り当て |
| Microsoft.Synapse | 作業スペース | システム割り当てユーザー割り当て |
| Microsoft.VirtualMachineImages | 画像テンプレート | ユーザー割り当て |
| Microsoft.Web | ホスティング環境 | システム割り当てユーザー割り当て |
| Microsoft.Web | sites | システム割り当てユーザー割り当て |
| Microsoft.Web | sites/slots | システム割り当てユーザー割り当て |
| Microsoft.Web | staticSites | システム割り当てユーザー割り当て |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/managed-identity-best-practice-recommendations"} -->
## マネージド システム ID に関するベスト プラクティスの推奨事項 - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identity-best-practice-recommendations
- Service: entra-id / managed-identities
- Article date: 2025-03-14
- Summary: ユーザー割り当てとシステム割り当てのマネージド ID の使い分けに関する推奨事項

Azure のマネージド ID は、Azure リソースで実行されているアプリケーションの資格情報を安全かつ便利に管理する方法を提供します。 この記事では、ユーザー割り当てマネージド ID とシステム割り当てマネージド ID の選択に関するベスト プラクティスの推奨事項について説明します。これにより、ID 管理を最適化し、管理オーバーヘッドを削減できます。

### システムまたはユーザー割り当てのマネージド ID を選択する

ユーザー割り当てマネージド ID のほうが、システム割り当てマネージド ID より広範なシナリオにおいて効率的です。 一部のシナリオと、ユーザー割り当てまたはシステム割り当てに関する推奨事項については、次の表を参照してください。

ユーザー割り当て ID は複数のリソースで使用できます。そのライフ サイクルは、関連しているリソースのライフ サイクルと切り離されています。 [どのリソースがマネージド ID をサポートしているかについてお読みください](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)。

このライフ サイクルによって、リソースの作成と ID 管理の責任を分離することができます。 ユーザー割り当て ID とそのロールの割り当てを、それらを必要とするリソースより先に構成できます。 リソースを作成するユーザーが必要とするのは、ユーザー割り当て ID を割り当てるアクセス権だけです。新しい ID やロールの割り当てを作成する必要はありません。

システム割り当て ID はリソースと共に作成および削除されるので、ロールの割り当てを前もって作成することはできません。 このシーケンスによって、リソースを作成しているユーザーがロールの割り当てを作成するアクセス権も持っていない場合、インフラストラクチャのデプロイ中にエラーが発生する可能性があります。

インフラストラクチャで複数のリソースが同じリソースへのアクセスを必要とする場合は、1 つのユーザー割り当て ID をそれらに割り当てることができます。 管理する個別の ID とロールの割り当てが少なくなるため、管理オーバーヘッドが削減されます。

各リソースがそれぞれの ID を持つことが必要な場合、あるいは一意のアクセス許可セットを必要とするリソースがあり、リソースが削除されたときに ID も削除したい場合は、システム割り当て ID を使用する必要があります。

| シナリオ | 推奨 | 注記 |
| --- | --- | --- |
| マネージド ID を使用したリソースの迅速な作成 (エフェメラル コンピューティングなど) | ユーザー割り当て ID | 複数のマネージド ID を短時間で作成しようとした場合 (たとえば、独自のシステム割り当て ID を持つ複数の仮想マシンをそれぞれデプロイする場合) は、Microsoft Entra オブジェクトの作成のレート制限を超える可能性があり、要求は HTTP 429 エラーで失敗します。 リソースを迅速に作成または削除している場合、システム割り当て ID を使用していると Microsoft Entra ID 内のリソース数の上限を超える可能性もあります。 削除されたシステム割り当て ID はリソースからアクセスできなくなりますが、30 日後に完全に消去されるまで制限にカウントされます。1 つのユーザー割り当て ID に関連付けられているリソースをデプロイするには、Microsoft Entra ID でサービス プリンシパルを 1 つだけ作成する必要があります。この場合、レート制限は回避されます。 事前に作成された 1 つの ID を使用すると、複数のリソースがそれぞれ独自の ID で作成された場合に発生する可能性のあるレプリケーション遅延のリスクが軽減されます。詳細については、[Azure サブスクリプション サービスの制限](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-subscription-service-limits#managed-identity-limits)に関するページを参照してください。 |
| レプリケートされたリソースおよびアプリケーション | ユーザー割り当て ID | 同じタスクを実行するリソース (重複した Web サーバーや、アプリ サービスと仮想マシン上のアプリケーションで実行されている同一の機能など) には、通常、同じアクセス許可が必要です。 同じユーザー割り当て ID を使用することで、必要なロールの割り当てが少なくなり、管理オーバーヘッドが減ります。 リソースは、種類が同じである必要はありません。 |
| コンプライアンス | ユーザー割り当て ID | 組織で、すべての ID の作成が承認プロセスを通過する必要がある場合、複数のリソースで 1 つのユーザー割り当て ID を使用すると、新しいリソースが作成されると作成されるシステム割り当て ID よりも承認が少なくなります。 |
| リソースがデプロイされる前に必要なアクセス権 | ユーザー割り当て ID | リソースによっては、デプロイの一環として特定の Azure リソースへのアクセスが必要になる場合があります。この場合、システム割り当て ID は時間内に作成されない可能性があるため、既存のユーザー割り当て ID を使用する必要があります。 |
| 監査ログ | システム割り当て ID | どのリソース (ID でない) がアクションを実行したのかをログ記録する必要がある場合は、システム割り当て ID を使用します。 |
| アクセス許可のライフサイクル管理 | システム割り当て ID | リソースのアクセス許可がリソースと共に削除されるようにしたい場合は、システム割り当て ID を使用します。 |

#### ユーザー割り当て ID を使用した管理の削減

図は、システム割り当て ID とユーザー割り当て ID を、複数の仮想マシンが 2 つのストレージ アカウントにアクセスするために使用したときの違いを示しています。

この図は、システム割り当て ID を持つ 4 つの仮想マシンを示しています。 それぞれの仮想マシンには、2 つのストレージ アカウントへのアクセスを許可する同じロールの割り当てがあります。

[Image: システム割り当て ID を使用してストレージ アカウントとキー コンテナーにアクセスする 4 つの仮想マシン。]

ユーザー割り当て ID が 4 つの仮想マシンに関連付けられている場合、必要となるロールの割り当ては 2 つだけなのに対して、システム割り当て ID では 8 つが必要です。 仮想マシンの ID により多くのロールの割り当てが必要な場合は、この ID に関連付けられているすべてのリソースに付与されます。

[Image: ユーザー割り当て ID を使用してストレージ アカウントとキー コンテナーにアクセスする 4 つの仮想マシン。]

セキュリティ グループを使用して、必要となるロールの割り当ての数を減らすることもできます。 この図は、システム割り当て ID を持つ 4 つの仮想マシンを示しています。この仮想マシンはセキュリティ グループに追加され、ロールの割り当てはシステム割り当て ID ではなくグループに追加されています。 結果は似ていますが、この構成では、ユーザー割り当て ID と同じ Resource Manager テンプレート機能が提供されません。

[Image: 4 つの仮想マシンのシステム割り当て ID が、ロール割り当てを持つセキュリティ グループに追加されている。]

#### 複数のマネージド ID

マネージド ID をサポートするリソースは、システム割り当て ID と、1 つ以上のユーザー割り当て ID の両方を使用できます。

このモデルには、共有のユーザー割り当て ID を使用し、しかもきめ細かいアクセス許可を必要に応じて適用できる柔軟性があります。

次の例では、"Virtual Machine 3" と "Virtual Machine 4" は、認証時に使用するユーザー割り当て ID に応じて、ストレージ アカウントとキー コンテナーの両方にアクセスできます。

[Image: 4 つの仮想マシン。2 つは複数のユーザー割り当て ID を持つ。]

次の例では、"Virtual Machine 4" にはユーザー割り当て ID の両方があり、認証時に使用される ID に応じて、ストレージ アカウントとキー コンテナーの両方にアクセスできます。 システム割り当て ID のロールの割り当ては、その仮想マシンに固有です。

[Image: 4 つの仮想マシン。1 つにはシステム割り当て ID とユーザー割り当て ID の両方がある。]

### 制限

[マネージド ID](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-subscription-service-limits#managed-identity-limits) の制限と、[カスタム ロールとロールの割り当て](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-subscription-service-limits#azure-rbac-limits)の制限をご覧ください。

### アクセス権を付与する場合は最小特権の原則に従う

マネージド ID を含む任意の ID にサービスへのアクセス許可を付与する際は、常に目的のアクションを実行するために必要な最小限の権限を付与するようにします。 たとえば、マネージド ID を使用してストレージ アカウントからデータを読み取る場合、その ID アクセス許可がストレージ アカウントにデータを書き込むのを許可する必要はありません。 追加のアクセス許可を付与する (たとえば、不必要であるに、も関わらず、マネージド ID を Azure サブスクリプションの共同作成者にするなど) と、その ID に関連するセキュリティの影響範囲が大きくなります。 その ID が侵害された場合の被害が最小限になるように、セキュリティの影響範囲は常に最小にする必要があります。

#### Azure リソースにマネージド ID を割り当てる、またはユーザーに割り当てアクセス許可を付与することによる影響を考慮する

Azure ロジック アプリや仮想マシンなどの Azure リソースにマネージド ID が割り当てられると、マネージド ID に付与されたすべてのアクセス許可が Azure リソースで使用できるようになることに注意してください。 もし、あるユーザーがこのリソースにコードをインストールまたは実行するアクセス権を持っている場合、そのユーザーはその Azure リソースに割り当てられている、または関連付けられているすべての ID へのアクセス許可を持っていることになるため、これは重要なことです。 マネージド ID の目的は、開発者がアクセスを得るために資格情報の処理を行ったり、これをコードに直接挿入したりすることなく、Azure リソース上で実行されるコードに他のリソースへのアクセス許可を与えることです。

たとえば、マネージド ID (ClientId = 1234) に ***StorageAccount7755*** への読み取り/書き込みアクセス権が付与され、 ***LogicApp3388*** に割り当てられている場合、ストレージ アカウントへの直接アクセス権を持たないが、 ***LogicApp3388*** 内でコードを実行するアクセス許可を持つ Alice は、マネージド ID を使用するコードを実行することで ***、StorageAccount7755*** との間でデータの読み取り/書き込みを行うこともできます。

同様に、Alice が自分でマネージド ID を割り当てるアクセス許可を持っている場合は、それを別の Azure リソースに割り当て、マネージド ID で使用できるすべてのアクセス許可にアクセスできます。

[Image: セキュリティのシナリオ]

一般的に、コードを実行でき (Logic App など)、マネージド ID を持つリソースに対する管理者権限をユーザーに付与する際には、そのユーザーに割り当てられるロールを使ってリソース上でコードをインストールまたは実行できるかどうかを考慮し、そうである場合は必要な場合にのみ、そのロールを割り当てるようにします。

### メンテナンス

システム割り当て ID は、リソースが削除されると自動的に削除されます。一方、ユーザー割り当て ID のライフサイクルは、それが関連付けられているリソースとは無関係です。

リソースが関連付けられていない場合でも、ユーザー割り当て ID が不要になったら、手動で削除する必要があります。

ロールの割り当ては、システム割り当てまたはユーザー割り当てのマネージド ID のいずれかが削除されたときに、自動的には削除されません。 これらのロール割り当ては手動で削除して、サブスクリプションごとのロール割り当ての制限を超えないようにする必要があります。

削除されたマネージド ID に関連付けられているロールの割り当ては、ポータルで表示されると "ID が見つかりません" と表示されます。 詳細については、[こちら](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/troubleshooting#symptom---role-assignments-with-identity-not-found)を参照してください。

[Image: ロールの割り当てでの「ID が見つかりません」。]

ユーザーまたはサービス プリンシパルに関連付けられていないロールの割り当ては、`ObjectType` の値が `Unknown` として表示されます。 それらを削除するために、パイプを使用していくつかの Azure PowerShell コマンドを結合できます。最初に、すべてのロールの割り当てを取得し、`ObjectType` という `Unknown` 値が付いたものにのみフィルターを適用した後で、Azure からそれらのロールの割り当てを削除します。

```azurepowershell
Get-AzRoleAssignment | Where-Object {$_.ObjectType -eq "Unknown"} | Remove-AzRoleAssignment 
```

### 認可にマネージド ID を使用する場合の制限

サービスへのアクセスを許可するために Microsoft Entra ID **グループ**を使用するのは、認可プロセスを簡素化するための優れた方法です。 発想はシンプルです。アクセス許可をグループに付与し、ID をグループに ID を追加して、それらの ID が同じアクセス許可を継承するようにします。 これは、さまざまなオンプレミス システムで採用されている十分に確立されたパターンであり、ID がユーザーを表す場合は適切に機能します。 Microsoft Entra ID で認可を制御するためのもう 1 つのオプションは、[アプリ ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)を使用することです。これにより、(ディレクトリ内のグローバル概念であるグループではなく) アプリに固有の**ロール**を宣言できます。 その後、(ユーザーやグループだけではなく) [マネージド ID にアプリ ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-assign-app-role-managed-identity)ことができます。

どちらの場合も、Microsoft Entra アプリケーションやマネージド ID などの人間以外の ID の場合、この認証情報をアプリケーションに提示する方法の正確なメカニズムは、現在理想的には適していません。 Microsoft Entra ID や Azure ロールベースのアクセス制御 (Azure RBAC) を使用する現在の実装では、Microsoft Entra ID によって発行されるアクセス トークンを各 ID の認証に使用します。 グループまたはロールに追加された ID は、Microsoft Entra ID によって発行されたアクセス トークン内のクレームとして表されます。 Azure RBAC ではこのクレームを使用して、アクセスを許可または拒否するための認可ルールをさらに評価します。

ID のグループとロールがアクセス トークン内の要求であるため、承認の変更はトークンが更新されるまで有効になりません。 通常は問題とならない人間のユーザーの場合、ログアウトして再度ログインすれば (または、トークンの有効期間が切れるのを待つ (既定では 1 時間) と)、新しいアクセス トークンを取得できるためです。 一方、マネージド ID のトークンは、パフォーマンスと回復性を確保するために、基盤の Azure インフラストラクチャによってキャッシュされます。マネージド ID のバックエンド サービスでは、リソース URI ごとのキャッシュを約 24 時間保持します。 これは、マネージド ID のグループまたはロール メンバーシップの変更が有効になるまでに数時間かかる可能性があることを意味します。 現時点では、有効期限が切れる前にマネージド ID のトークンを強制的に更新することはできません。 そのため、マネージド ID のグループまたはロールのメンバーシップを変更してアクセス許可を追加または削除する場合には、ID を使用する Azure リソースに適切なアクセス権が付与されるまでに、数時間の待ち時間が発生することがあります。

この遅延が要件に対して許容できない場合は、トークンでグループまたはロールを使用する代わりの方法を検討してください。 アクセス許可を持つマネージド ID の追加または削除を Microsoft Entra ID グループに対して行う代わりに、マネージド ID のアクセス許可の変更が速やかに有効になるよう、アクセス許可が ID に直接適用される[ユーザー割り当てマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-azcli) を使用して Azure リソースをグループ化することをお勧めします。 ユーザー割り当てマネージド ID は、1 つ以上の Azure リソースに割り当てて使用できるため、グループのように使用できます。 割り当て操作は、[マネージド ID 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-contributor)および[マネージド ID オペレーター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#managed-identity-operator)のロールを使用して制御できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/overview"} -->
## Azure リソースのマネージド ID - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview
- Service: entra-id / managed-identities
- Article date: 2025-08-19
- Summary: Azure リソースのマネージド ID の概要。

サービス間の通信をセキュリティで保護するために使用されるシークレット、資格情報、証明書、キーの管理は、開発者にとって共通の課題です。 シークレットと証明書の手動処理は、セキュリティの問題と停止の既知の原因です。 マネージド ID により、開発者はこれらの資格情報を管理する必要がなくなります。 アプリケーションは、マネージド ID を使用して、資格情報を管理することなく Microsoft Entra トークンを取得できます。

### マネージド ID とは

大まかに言えば、人間 ID とマシン ID と非人間 ID の 2 種類があります。 マシン/人間以外の ID は、デバイス ID とワークロード ID で構成されます。 Microsoft Entra では、ワークロード ID はアプリケーション、サービス プリンシパル、マネージド ID です。

マネージド ID は、Azure コンピューティング リソース (Azure 仮想マシン、Azure 仮想マシン スケール セット、Service Fabric クラスター、Azure Kubernetes クラスター) または Azure でサポートされている任意のアプリ ホスティング プラットフォームに割り当てることができる ID です。 マネージド ID がコンピューティング リソースに割り当てられると、ストレージ アカウント、SQL データベース、Cosmos DB などのダウンストリーム依存関係リソースに直接または間接的にアクセスできるようになります。 マネージド ID は、アクセス キーやパスワードなどのシークレットを置き換えます。 さらに、マネージド ID は、サービス間の依存関係の証明書またはその他の形式の認証を置き換えることができます。

次のビデオでは、マネージド ID を使用する方法を示します。

以下に、マネージド ID を使用するベネフィットをいくつか紹介します。

- 資格情報を自ら管理する必要がない。 資格情報には、自分もアクセスできません。
- マネージド ID を使用すると、独自のアプリケーションを含む、[Microsoft Entra 認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)をサポートするあらゆるリソースに対して認証を行うことができます。
- マネージド ID は追加コストなしで利用できます。

### マネージド ID の種類

マネージド ID には、次の 2 種類があります。

- **システム割り当て**。 仮想マシンなどの一部の Azure リソースでは、リソースでマネージド ID を直接有効にすることができます。 システム割り当てマネージド ID を有効にした場合:

    - 特別な種類のサービス プリンシパルが、ID 用に Microsoft Entra ID に作成されます。 このサービス プリンシパルは、その Azure リソースのライフサイクルに関連付けられます。 Azure リソースが削除されると、Azure により自動的にサービス プリンシパルが削除されます。
    - その ID を使用して Microsoft Entra ID にトークンを要求できるのは、必然的に、その Azure リソースのみとなります。
    - マネージド ID が 1 つ以上のサービスにアクセスできるように承認します。
    - システム割り当てサービス プリンシパルの名前は、それが作成された Azure リソースの名前と常に同じです。 デプロイ スロットの場合、システム割り当てマネージド ID の名前は `<app-name>/slots/<slot-name>`。
- **ユーザー割り当て**。 スタンドアロンの Azure リソースとしてマネージド ID を自分で作成することもできます。 ユーザー割り当てマネージド ID を作成し、1 つ以上の Azure リソースに割り当てることができます。 ユーザー割り当てマネージド ID を有効にする際は、次の点に注意します。

    - 特別な種類のサービス プリンシパルが、ID 用に Microsoft Entra ID に作成されます。 サービス プリンシパルは、それを使うリソースとは別に管理されます。
    - ユーザー割り当てマネージド ID は、複数のリソースで使用できます。
    - マネージド ID が 1 つ以上のサービスにアクセスできるように承認します。

    ユーザー割り当てマネージド ID は、コンピューティングとは独立してプロビジョニングされ、複数のコンピューティング リソースに割り当てることができる、Microsoft サービスに推奨されるマネージド ID の種類です。

システム割り当てマネージド ID をサポートするリソースでは、次のことが可能です。

- リソース レベルでマネージド ID を有効または無効にする。
- ロールベースのアクセス制御 (RBAC) を使用してアクセス許可を付与します。
- Azure アクティビティ ログの作成、読み取り、更新、削除 (CRUD) 操作を表示します。
- Microsoft Entra ID サインイン ログでサインイン アクティビティを表示します。

代わりにユーザー割り当てマネージド ID を選択した場合:

- ID の作成、読み取り、更新、削除を行うことができます。
- RBAC ロールの割り当てを使用してアクセス許可を付与できます。
- ユーザー割り当てマネージド ID は、複数のリソースで使用できます。
- CRUD 操作は、Azure アクティビティ ログで確認できます。
- Microsoft Entra ID サインイン ログでサインイン アクティビティを表示します。

マネージド ID に対する操作は、Azure Resource Manager テンプレート、Azure portal、Azure CLI、PowerShell、REST API を使用して実行できます。

### システム割り当てマネージド ID とユーザー割り当てマネージド ID の違い

次の表は、システム割り当てマネージド ID とユーザー割り当てマネージド ID の違いをまとめたものです。

| プロパティ | システム割り当てマネージド ID | ユーザー割り当てマネージド ID |
| --- | --- | --- |
| 作成 | Azure リソース (たとえば、Azure Virtual Machines または Azure App Service) の一部として作成されます。 | スタンドアロンの Azure リソースとして作成されます。 |
| ライフ サイクル | マネージド ID の作成に使用された Azure リソースとの共有ライフ サイクル。  親リソースが削除されると、マネージド ID も削除されます。 | 独立したライフ サイクル。  明示的に削除する必要があります。 |
| Azure リソース間で共有されます | 共有できません。  1 つの Azure リソースにのみ関連付けることができます。 | 共有できます。  同じユーザー割り当てマネージド ID を、複数の Azure リソースに関連付けることができます。 |
| 一般的なユース ケース | 1 つの Azure リソース内に含まれるワークロード。  独立した ID を必要としているワークロード。  たとえば、1 つの仮想マシンで実行されるアプリケーション。 | 複数のリソースで実行され、1 つの ID を共有できるワークロード。  プロビジョニング フローの一部として、セキュリティで保護されたリソースへの事前認可が必要なワークロード。  リソースが頻繁にリサイクルされるものの、アクセス許可は一貫性を保つ必要があるワークロード。  たとえば、複数の仮想マシンが同じリソースにアクセスする必要があるワークロード。 |

### Azure リソースにマネージド ID を使用する

#### マネージド ID を直接使用する

Azure コンピューティング リソースで実行されているサービス コードは、Microsoft Authentication Library (MSAL) または Azure.Identity SDK を使用して、マネージド ID によってサポートされる Entra ID からマネージド ID トークンを取得します。 このトークンの取得にはシークレットは必要なく、コードが実行される環境に基づいて自動的に認証されます。 マネージド ID が承認されている限り、サービス コードは Entra ID 認証をサポートするダウンストリームの依存関係にアクセスできます。

たとえば、Azure 仮想マシン (VM) を Azure コンピューティングとして使用できます。 その後、ユーザー割り当てマネージド ID を作成し、VM に割り当てることができます。 VM で実行されているワークロードは、ストレージ アカウントにアクセスするための Azure.Identity (または MSAL) と Azure Storage クライアント SDK の両方とインターフェイスします。 ユーザー割り当てマネージド ID には、ストレージ アカウントへのアクセスが承認されています。

通常は、次の手順でマネージド ID を使用します。

1. Azure でマネージド ID を作成します。 システム割り当てマネージド ID またはユーザー割り当てマネージド ID を選択できます。
    1. ユーザー割り当てマネージド ID を使う場合、マネージド ID を、仮想マシン、Azure ロジック アプリや、Azure Web アプリなどの "ソース" Azure リソースに割り当てます。
2. "ターゲット" サービスにアクセスできるマネージド ID を承認します。
3. マネージド ID を使用してリソースにアクセスします。 この手順では、Azure SDK と Azure.Identity ライブラリまたは Microsoft Authentication Library (MSAL) を使用できます。 一部の "ソース" リソースでは、マネージド ID を使用した接続の方法を認識しているコネクタが提供されます。 その場合は、ID をその "ソース" リソースの機能として使用します。

#### Entra ID アプリでマネージド ID をフェデレーション ID 資格情報 (FIC) として使用する

ワークロード ID フェデレーションでは、Entra ID アプリケーションで、証明書やパスワードと同様に、資格情報としてマネージド ID を使用できます。 Entra ID アプリが必要な場合は、常に資格情報を使わない方法をお勧めします。 Entra ID アプリでマネージド ID を FIC として使用する場合、20 FIC の制限があります。

Entra ID アプリケーションの容量で動作するワークロードは、マネージド ID を持つ任意の Azure コンピューティングでホストできます。 ワークロードは、マネージド ID を使用して、Entra ID アプリケーション トークンと交換されるトークンを、ワークロード ID フェデレーションを介して取得します。 この機能は、マネージドアイデンティティ、または FIC (フェデレーション ID クレデンシャル) とも呼ばれます。 詳細については、「 [マネージド ID を信頼するようにアプリケーションを構成する」を](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity)参照してください。

### マネージド ID をサポートする Azure サービス

Azure リソースのマネージド ID は、Microsoft Entra 認証をサポートするサービスの認証に使用することができます。 サポートされる Azure サービスの一覧については、[Azure リソースのマネージド ID をサポートするサービス](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/overview-for-developers"} -->
## 開発者向けの概要とガイドライン - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview-for-developers
- Service: entra-id / managed-identities
- Article date: 2024-09-26
- Summary: 開発者が Azure リソース用マネージド ID を使用する方法の概要。

マネージド ID がサポートされている Azure リソースには、Microsoft Entra 認証をサポートしている Azure リソースに接続するためにマネージド ID を指定するオプションが**常に**用意されています。 マネージド ID のサポートにより、開発者はコードで資格情報を管理しなくて済みます。 マネージド ID は、それをサポートしている Azure リソースを操作する場合に推奨される認証オプションです。 [マネージド ID の概要をご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)。

このページでは、Azure Key Vault、Azure Storage、Microsoft SQL Server に接続できるように App Service を構成する方法について説明します。 マネージド ID をサポートしていて、Microsoft Entra 認証をサポートしているリソースに接続する任意の Azure リソースに対して同じ原則を使用できます。

コード サンプルでは、Azure Identity クライアント ライブラリを使用します。これは、接続で使用されるアクセス トークンの取得など、多くの手順が自動的に処理されるため、推奨される方法です。

### マネージド ID ではどのようなリソースに接続できますか?

マネージド ID は、Microsoft Entra 認証をサポートする任意のリソースに接続できます。 一般に、マネージド ID で接続できるようにするためにリソースで必要な特別なサポートはありません。

一部のリソースでは、Microsoft Entra 認証がサポートされておらず、またクライアント ライブラリでトークンによる認証もサポートされていない場合があります。 マネージド ID を使用して、コードやアプリケーション構成に保存することなく資格情報に安全にアクセスする方法のガイダンスを続けてお読みください。

### マネージド ID を作成する

マネージド ID には、システム割り当てとユーザー割り当ての [2 種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)があります。 システム割り当て ID は、1 つの Azure リソースに直接リンクされます。 Azure リソースが削除されると、ID も削除されます。 ユーザー割り当てマネージド ID は複数の Azure リソースに関連付けることができ、そのライフサイクルはそれらのリソースから独立しています。

[ほとんどのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identity-best-practice-recommendations)では、ユーザー割り当てマネージド ID を使用することをお勧めします。 使用しているソース リソースがユーザー割り当てマネージド ID をサポートしていない場合は、そのリソース プロバイダーのドキュメントを参照して、システム割り当てマネージド ID を持つように構成する方法を確認する必要があります。

重要

マネージド ID の作成に使用されるアカウントには、新しいユーザー割り当てマネージド ID を作成するための "マネージド ID 共同作成者" などのロールが必要です。

任意のオプションを使用して、ユーザー割り当てマネージド ID を作成します。

- [Azure Portal](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-azp)
- [Azure CLI](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-azcli)
- [Azure PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-powershell)
- [Resource Manager](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-arm)
- [REST](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-rest)

ユーザー割り当てマネージド ID を作成したら、マネージド ID の作成時に返される `clientId` と `principalId` の値を書き留めます。 `principalId` はアクセス許可の追加時に使用し、`clientId` はアプリケーションのコードで使用します。

### App Service にユーザー割り当てマネージド ID を構成する

コードでマネージド ID を使用する前に、それを使用する App Service に割り当てる必要があります。 ユーザー割り当てマネージド ID を使用するように App Service を構成するプロセスでは、[アプリ構成でマネージド ID のリソース識別子を指定](https://learn.microsoft.com/ja-jp/azure/app-service/overview-managed-identity?tabs=portal%2Chttp#add-a-user-assigned-identity)する必要があります。

#### ID にアクセス許可を追加する

ユーザー割り当てマネージド ID を使用するように App Service を構成したら、その ID に必要なアクセス許可を付与します。 このシナリオでは、この ID を使用して Azure Storage を操作するため、 [Azure ロール ベースのアクセス制御 (RBAC) システムを使用して](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview) ユーザー割り当てマネージド ID にリソースへのアクセス許可を付与する必要があります。

重要

ロールの割り当てを追加するには、ターゲット リソースの "ユーザー アクセス管理者" や "所有者" などのロールが必要です。 必ず、アプリケーションの実行に必要な最小限の特権を付与してください。

アクセスするリソースには、ID アクセス許可を付与する必要があります。 たとえば、キー コンテナーにアクセスするためにトークンを要求する場合、アプリや関数のマネージド ID を含むアクセス ポリシーも追加する必要があります。 それ以外の場合は、有効なトークンを使用する場合でも、Key Vault への呼び出しは拒否されます。 同じことが Azure SQL Database にも当てはまります。 どのリソースで Microsoft Entra トークンがサポートされるかについて詳しくは、「Microsoft Entra 認証をサポートする Azure サービス」を参照siteください。

### コードでマネージド ID を使用する

上記の手順を完了すると、App Service には Azure リソースへのアクセス許可を持つマネージド ID があります。 マネージド ID を使用すると、コードに資格情報を格納する代わりに、コードが Azure リソースと対話するために使用できるアクセス トークンを取得できます。

お好みのプログラミング言語用の Azure ID ライブラリを使用することをお勧めします。 このライブラリはアクセス トークンを取得するため、ターゲット リソースへの接続が簡単になります。

Azure ID ライブラリの詳細については、以下をお読みください。

- [.NET 用 Azure ID ライブラリ](https://learn.microsoft.com/ja-jp/dotnet/api/overview/azure/identity-readme)
- [Java 用 Azure ID ライブラリ](https://learn.microsoft.com/ja-jp/java/api/overview/azure/identity-readme?view=azure-java-stable&preserve-view=true)
- [JavaScript 用 Azure ID ライブラリ](https://learn.microsoft.com/ja-jp/javascript/api/overview/azure/identity-readme?view=azure-node-latest&preserve-view=true)
- [Python 用 Azure ID ライブラリ](https://learn.microsoft.com/ja-jp/python/api/overview/azure/identity-readme?view=azure-python&preserve-view=true)
- [Go 用 Azure ID モジュール](https://learn.microsoft.com/ja-jp/azure/developer/go/azure-sdk-authentication)
- [C++ 用 Azure ID ライブラリ](https://github.com/Azure/azure-sdk-for-cpp/blob/main/sdk/identity/azure-identity/README.md)

#### 開発環境で Azure ID ライブラリを使用する

Azure ID ライブラリはそれぞれ、 `DefaultAzureCredential` の種類を提供します。 `DefaultAzureCredential` では、環境変数や対話型のサインインなどの複数のメカニズムを介した認証が自動的に試行されます。 この資格情報の種類は、独自の資格情報を使用する開発環境で使用できます。 また、マネージド ID を使用する Azure 運用環境でも使用できます。 アプリケーションをデプロイするときにコードを変更する必要はありません。

ユーザー割り当てマネージド ID を使用している場合は、ID のクライアント ID をパラメーターとして渡すことによって、認証するユーザー割り当てマネージド ID を明示的に指定する必要もあります。 Azure portal で ID を参照することで、クライアント ID を取得できます。

#### Azure Storage で BLOB にアクセスする

## [.NET](#tab/dotnet)
```csharp
using Azure.Identity;
using Azure.Storage.Blobs;

// code omitted for brevity

// Specify the Client ID if using user-assigned managed identities
var clientID = Environment.GetEnvironmentVariable("Managed_Identity_Client_ID");
var credentialOptions = new DefaultAzureCredentialOptions
{
    ManagedIdentityClientId = clientID
};
var credential = new DefaultAzureCredential(credentialOptions);                        

var blobServiceClient1 = new BlobServiceClient(new Uri("<URI of Storage account>"), credential);
BlobContainerClient containerClient1 = blobServiceClient1.GetBlobContainerClient("<name of blob>");
BlobClient blobClient1 = containerClient1.GetBlobClient("<name of file>");

if (blobClient1.Exists())
{
    var downloadedBlob = blobClient1.Download();
    string blobContents = downloadedBlob.Value.Content.ToString();                
}
```

## [Java](#tab/java)
```java
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

// read the Client ID from your environment variables
String clientID = System.getProperty("Client_ID");
DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
        .managedIdentityClientId(clientID)
        .build();

BlobServiceClient blobStorageClient = new BlobServiceClientBuilder()
        .endpoint("<URI of Storage account>")
        .credential(credential)
        .buildClient();

BlobContainerClient blobContainerClient = blobStorageClient.getBlobContainerClient("<name of blob container>");
BlobClient blobClient = blobContainerClient.getBlobClient("<name of blob/file>");
if (blobClient.exists()) {
    String blobContent = blobClient.downloadContent().toString();
}
```

## [Node.js](#tab/nodejs)
```nodejs
import { DefaultAzureCredential } from "@azure/identity";
import { BlobServiceClient } from "@azure/storage-blob";

// Specify the Client ID if using user-assigned managed identities
const clientID = process.env.Managed_Identity_Client_ID;
const credential = new DefaultAzureCredential({
  managedIdentityClientId: clientID
});

const blobServiceClient = new BlobServiceClient("<URI of Storage account>", credential);
const containerClient = blobServiceClient.getContainerClient("<name of blob>");
const blobClient = containerClient.getBlobClient("<name of file>");

async function downloadBlob() {
  if (await blobClient.exists()) {
    const downloadBlockBlobResponse = await blobClient.download();
    const downloadedBlob = await streamToString(downloadBlockBlobResponse.readableStreamBody);
    console.log("Downloaded blob content:", downloadedBlob);
  }
}

async function streamToString(readableStream) {
  return new Promise((resolve, reject) => {
    const chunks = [];
    readableStream.on("data", (data) => {
      chunks.push(data.toString());
    });
    readableStream.on("end", () => {
      resolve(chunks.join(""));
    });
    readableStream.on("error", reject);
  });
}

downloadBlob().catch(console.error);
```

## [Python](#tab/python)
```python
from azure.identity import DefaultAzureCredential
from azure.storage.blob import BlobServiceClient
import os

# Specify the Client ID if using user-assigned managed identities
client_id = os.getenv("Managed_Identity_Client_ID")
credential = DefaultAzureCredential(managed_identity_client_id=client_id)

blob_service_client = BlobServiceClient(account_url="<URI of Storage account>", credential=credential)
container_client = blob_service_client.get_container_client("<name of blob>")
blob_client = container_client.get_blob_client("<name of file>")

def download_blob():
    if blob_client.exists():
        download_stream = blob_client.download_blob()
        blob_contents = download_stream.readall().decode('utf-8')
        print("Downloaded blob content:", blob_contents)

download_blob()
```

## [Go](#tab/Go)
```go
package main

import (
    "context"
    "fmt"
    "io"
    "os"
    "strings"

    "github.com/Azure/azure-sdk-for-go/sdk/azidentity"
    "github.com/Azure/azure-sdk-for-go/sdk/storage/azblob"
)

func main() {
    // The client ID for the user-assigned managed identity is read from the AZURE_CLIENT_ID env var
    cred, err := azidentity.NewDefaultAzureCredential(nil)
    if err != nil {
        fmt.Printf("failed to obtain a credential: %v\n", err)
        return
    }

    accountURL := "<URI of Storage account>"
    containerName := "<name of blob>"
    blobName := "<name of file>"

    serviceClient, err := azblob.NewServiceClient(accountURL, cred, nil)
    if err != nil {
        fmt.Printf("failed to create service client: %v\n", err)
        return
    }

    containerClient := serviceClient.NewContainerClient(containerName)
    blobClient := containerClient.NewBlobClient(blobName)

    // Check if the blob exists
    _, err = blobClient.GetProperties(context.Background(), nil)
    if err != nil {
        fmt.Printf("failed to get blob properties: %v\n", err)
        return
    }

    // Download the blob
    downloadResponse, err := blobClient.Download(context.Background(), nil)
    if err != nil {
        fmt.Printf("failed to download blob: %v\n", err)
        return
    }

    // Read the blob content
    blobData := downloadResponse.Body(nil)
    defer blobData.Close()

    blobContents := new(strings.Builder)
    _, err = io.Copy(blobContents, blobData)
    if err != nil {
        fmt.Printf("failed to read blob data: %v\n", err)
        return
    }

    fmt.Println("Downloaded blob content:", blobContents.String())
}
```

---

#### Azure Key Vault に格納されているシークレットにアクセスする

## [.NET](#tab/dotnet)
```csharp
using Azure.Identity;
using Azure.Security.KeyVault.Secrets;
using Azure.Core;

// code omitted for brevity

// Specify the Client ID if using user-assigned managed identities
var clientID = Environment.GetEnvironmentVariable("Managed_Identity_Client_ID");
var credentialOptions = new DefaultAzureCredentialOptions
{
    ManagedIdentityClientId = clientID
};
var credential = new DefaultAzureCredential(credentialOptions);        

var client = new SecretClient(
    new Uri("https://<your-unique-key-vault-name>.vault.azure.net/"),
    credential);
    
KeyVaultSecret secret = client.GetSecret("<my secret>");
string secretValue = secret.Value;
```

## [Java](#tab/java)
```java
import com.azure.core.util.polling.SyncPoller;
import com.azure.identity.DefaultAzureCredentialBuilder;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.DeletedSecret;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

String keyVaultName = "mykeyvault";
String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";
String secretName = "mysecret";

// read the user-assigned managed identity Client ID from your environment variables
String clientID = System.getProperty("Managed_Identity_Client_ID");
DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
        .managedIdentityClientId(clientID)
        .build();

SecretClient secretClient = new SecretClientBuilder()
    .vaultUrl(keyVaultUri)
    .credential(credential)
    .buildClient();
    
KeyVaultSecret retrievedSecret = secretClient.getSecret(secretName);
```

## [Node.js](#tab/nodejs)
```javascript
import { DefaultAzureCredential } from "@azure/identity";
import { SecretClient } from "@azure/keyvault-secrets";

// Specify the Client ID if using user-assigned managed identities
const clientID = process.env.Managed_Identity_Client_ID;
const credential = new DefaultAzureCredential({
    managedIdentityClientId: clientID
});

const client = new SecretClient("https://<your-key-vault-name>.vault.azure.net/", credential);

async function getSecret() {
    const secret = await client.getSecret("<your-secret-name>");
    const secretValue = secret.value;
    console.log(secretValue);
}

getSecret().catch(err => console.error("Error retrieving secret:", err));
```

## [Python](#tab/python)
```Python
from azure.identity import DefaultAzureCredential
from azure.keyvault.secrets import SecretClient
import os

# Specify the Client ID if using user-assigned managed identities
client_id = os.getenv("Managed_Identity_Client_ID")
credential = DefaultAzureCredential(managed_identity_client_id=client_id)

client = SecretClient(vault_url="https://<your-key-vault-name>.vault.azure.net/", credential=credential)

def get_secret():
    secret = client.get_secret("<your-secret-name>")
    secret_value = secret.value
    print(secret_value)

if __name__ == "__main__":
    try:
        get_secret()
    except Exception as e:
        print(f"Error retrieving secret: {e}")
```

## [Go](#tab/Go)
```go
package main

import (
    "context"
    "fmt"
    "os"

    "github.com/Azure/azure-sdk-for-go/sdk/azidentity"
    "github.com/Azure/azure-sdk-for-go/sdk/keyvault/azsecrets"
)

func main() {
    // The client ID for the user-assigned managed identity is read from the AZURE_CLIENT_ID env var
    cred, err := azidentity.NewDefaultAzureCredential(nil)
    if err != nil {
        fmt.Printf("failed to obtain a credential: %v\n", err)
        return
    }

    client, err := azsecrets.NewClient("https://<your-key-vault-name>.vault.azure.net/", credential, nil)
    if err != nil {
        fmt.Printf("Failed to create secret client: %v\n", err)
        return
    }

    secretResp, err := client.GetSecret(context.TODO(), "<your-secret-name>", nil)
    if err != nil {
        fmt.Printf("Failed to get secret: %v\n", err)
        return
    }

    secretValue := *secretResp.Value
    fmt.Println(secretValue)
}
```

---

#### Azure SQL データベースにアクセスする

## [.NET](#tab/dotnet)
```csharp
using Azure.Identity;
using Microsoft.Data.SqlClient;

// code omitted for brevity

// Specify the Client ID if using user-assigned managed identities
var clientID = Environment.GetEnvironmentVariable("Managed_Identity_Client_ID");
var credentialOptions = new DefaultAzureCredentialOptions
{
    ManagedIdentityClientId = clientID
};

AccessToken accessToken = await new DefaultAzureCredential(credentialOptions).GetTokenAsync(
    new TokenRequestContext(new string[] { "https://database.windows.net//.default" }));                        

using var connection = new SqlConnection("Server=<DB Server>; Database=<DB Name>;")
{
    AccessToken = accessToken.Token
};
var cmd = new SqlCommand("select top 1 ColumnName from TableName", connection);
await connection.OpenAsync();
SqlDataReader dr = cmd.ExecuteReader();
while(dr.Read())
{
    Console.WriteLine(dr.GetValue(0).ToString());
}
dr.Close();	
```

## [Java](#tab/java)
[Azure Spring Apps](https://learn.microsoft.com/ja-jp/azure/spring-apps/) を使用すると、コードに変更を加えることなく、マネージド ID を使用して Azure SQL データベースに接続できます。

`src/main/resources/application.properties` ファイルを開き、次の行の末尾に `Authentication=ActiveDirectoryMSI;` を追加します。 `$AZ_DATABASE_NAME` 変数には必ず正しい値を使用してください。

```properties
spring.datasource.url=jdbc:sqlserver://$AZ_DATABASE_NAME.database.windows.net:1433;database=demo;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;Authentication=ActiveDirectoryMSI;
```

[マネージド ID を使用して Azure SQL Database を Azure Spring Apps アプリに接続する](https://learn.microsoft.com/ja-jp/azure/spring-apps/connect-managed-identity-to-azure-sql)方法の詳細をご覧ください。

## [Node.js](#tab/nodejs)
```javascript

import { DefaultAzureCredential } from "@azure/identity";
import { Connection, Request } from "tedious";

// Specify the Client ID if using a user-assigned managed identity
const clientID = process.env.Managed_Identity_Client_ID;
const credential = new DefaultAzureCredential({
    managedIdentityClientId: clientID
});

async function getAccessToken() {
    const tokenResponse = await credential.getToken("https://database.windows.net//.default");
    return tokenResponse.token;
}

async function queryDatabase() {
    const accessToken = await getAccessToken();

    const config = {
        server: "<your-server-name>",
        authentication: {
            type: "azure-active-directory-access-token",
            options: {
                token: accessToken
            }
        },
        options: {
            database: "<your-database-name>",
            encrypt: true
        }
    };

    const connection = new Connection(config);

    connection.on("connect", err => {
        if (err) {
            console.error("Connection failed:", err);
            return;
        }

        const request = new Request("SELECT TOP 1 ColumnName FROM TableName", (err, rowCount, rows) => {
            if (err) {
                console.error("Query failed:", err);
                return;
            }

            rows.forEach(row => {
                console.log(row.value);
            });

            connection.close();
        });

        connection.execSql(request);
    });

    connection.connect();
}

queryDatabase().catch(err => console.error("Error:", err));
```

## [Python](#tab/python)
```python
import os
from azure.identity import DefaultAzureCredential
from azure.core.credentials import AccessToken
import pyodbc

# Specify the Client ID if using a user-assigned managed identity
client_id = os.getenv("Managed_Identity_Client_ID")
credential = DefaultAzureCredential(managed_identity_client_id=client_id)

# Get the access token
token = credential.get_token("https://database.windows.net//.default")
access_token = token.token

# Set up the connection string
connection_string = "Driver={ODBC Driver 18 for SQL Server};Server=<your-server-name>;Database=<your-database-name>;"

# Connect to the database
connection = pyodbc.connect(connection_string, attrs_before={"AccessToken": access_token})

# Execute the query
cursor = connection.cursor()
cursor.execute("SELECT TOP 1 ColumnName FROM TableName")

# Fetch and print the result
row = cursor.fetchone()
while row:
    print(row)
    row = cursor.fetchone()

# Close the connection
cursor.close()
connection.close()
```

## [Go](#tab/Go)
```go
package main

import (
    "context"
    "database/sql"
    "fmt"
    "os"

    "github.com/Azure/azure-sdk-for-go/sdk/azidentity"
    "github.com/denisenkom/go-mssqldb"
)

func main() {
    // The client ID for the user-assigned managed identity is read from the AZURE_CLIENT_ID env var
    cred, err := azidentity.NewDefaultAzureCredential(nil)
    if err != nil {
        fmt.Printf("failed to obtain a credential: %v\n", err)
        return
    }

    // Get the access token
    token, err := credential.GetToken(context.TODO(), azidentity.TokenRequestOptions{
        Scopes: []string{"https://database.windows.net//.default"},
    })
    if err != nil {
        fmt.Printf("Failed to get token: %v\n", err)
        return
    }

    // Set up the connection string
    connString := fmt.Sprintf("sqlserver://<your-server-name>?database=<your-database-name>&access_token=%s", token.Token)

    // Connect to the database
    db, err := sql.Open("sqlserver", connString)
    if err != nil {
        fmt.Printf("Failed to connect to the database: %v\n", err)
        return
    }
    defer db.Close()

    // Execute the query
    rows, err := db.QueryContext(context.TODO(), "SELECT TOP 1 ColumnName FROM TableName")
    if err != nil {
        fmt.Printf("Failed to execute query: %v\n", err)
        return
    }
    defer rows.Close()

    // Fetch and print the result
    for rows.Next() {
        var columnValue string
        if err := rows.Scan(&columnValue); err != nil {
            fmt.Printf("Failed to scan row: %v\n", err)
            return
        }
        fmt.Println(columnValue)
    }
}
```

---

### Microsoft Entra ID またはライブラリのトークン ベースの認証をサポートしていないリソースに接続する

一部の Azure リソースでは、まだ Microsoft Entra 認証がサポートされていない場合や、そのクライアント ライブラリでトークンによる認証をサポートしていない場合があります。 通常、こうしたリソースは、ユーザー名とパスワードまたは接続文字列内のアクセス キーを必要とするオープンソース テクノロジです。

コードやアプリケーション構成に資格情報を格納しないようにするには、資格情報をシークレットとして Azure Key Vault に格納します。 上記の例を使用すると、マネージド ID を使用して Azure KeyVault からシークレットを取得し、接続文字列に資格情報を渡すことができます。 この方法は、コードや環境で資格情報を直接処理する必要がないことを意味します。

### トークンを直接処理する場合のガイドライン

一部のシナリオでは、組み込みのメソッドを使用してターゲット リソースに接続するのではなく、マネージド ID のトークンを手動で取得する必要がある場合があります。 このようなシナリオとしては、使用しているプログラミング言語や接続先のターゲット リソース用のクライアント ライブラリが存在しない場合や、Azure で実行されていないリソースに接続する場合などが挙げられます。 トークンを手動で取得する場合は、以下のガイドラインに従ってください。

#### 取得したトークンをキャッシュする

パフォーマンスと信頼性を確保するために、アプリケーションでトークンをローカル メモリにキャッシュするか、ディスクに保存する場合は暗号化することをお勧めします。 マネージド ID トークンは 24 時間有効であり、新しいトークンを定期的に要求しても、キャッシュされたものがトークン発行エンドポイントから返されるため、メリットはありません。 要求の制限を超えると、レート制限が適用され、HTTP 429 エラーが出されます。

トークンを取得するときに、トークンの生成時に返される `expires_on` (または同等のプロパティ) が示す期限の 5 分前にトークン キャッシュの有効期限が切れるように設定できます。

#### トークン検査

アプリケーションはトークンのコンテンツに依存しないようにする必要があります。 トークンのコンテンツは、トークンを要求しているクライアントではなく、アクセス対象 (ターゲット リソース) のみが使用するためのものです。 トークンのコンテンツは、将来変更されたり、暗号化されたりする可能性があります。

#### トークンを公開または移動しない

トークンは資格情報と同様に扱う必要があります。 ユーザーやその他のサービス (ログ/監視ソリューションなど) に公開しないでください。 ターゲット リソースに対して認証するとき以外は、それが使用されているソース リソースから移動しないでください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/reference-managed-identity-libraries"} -->
## マネージド ID 認証用のクライアント ライブラリ - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/reference-managed-identity-libraries
- Service: entra-id / managed-identities
- Article date: 2024-11-11
- Summary: Azure リソースのマネージド ID を使用してアプリを認証するために使用できるクライアント ライブラリについて説明します。

このドキュメントでは、Azure リソースのマネージド ID を使用してアプリケーションを認証するために使用できるクライアント ライブラリの概要について説明します。 これらのライブラリには、Azure ID ライブラリと Microsoft Authentication Libraries (MSAL) が含まれます。

一部の Azure サービスでは、これらのライブラリの上にクライアント ライブラリが構築されています。 たとえば、`Microsoft.Data.SqlClient` パッケージを使用して、マネージド ID を使用して Azure SQL データベースに対する認証を行うことができます。 バックグラウンドで、.NET 用の Azure ID ライブラリが使用されています。

### 適切なライブラリの選択

MSAL ライブラリは、Azure Identity などのライブラリよりも低レベルの抽象化を提供します。 MSAL ライブラリと Azure ID ライブラリの両方で、マネージド ID を使用してトークンを取得できます。 内部的には、Azure ID ライブラリは MSAL を使用し、アプリケーションの開発とデプロイ時に ID の種類間の手動切り替えを実装する必要がない `DefaultAzureCredential` などの上位レベルの API を提供します。

- アプリケーションでいずれかのライブラリが既に使用されている場合は、引き続き同じライブラリを使用します。
- 新しいアプリケーションを開発し、他の Azure リソースを呼び出す予定の場合は、Azure ID ライブラリを使用します。 このライブラリでは、マネージド ID が使用できないローカル開発者マシンでアプリを認証できるようにすることで、開発者エクスペリエンスが向上します。
- Microsoft Graph や独自の Web API などの他のダウンストリーム Web API を呼び出す必要がある場合は、MSAL を使用します。 .NET アプリケーションの場合は、MSAL 上に構築 *Microsoft.Identity.Web* ライブラリを使用します。

Azure サービスがこれらのライブラリの上にクライアント ライブラリを構築した場合は、サービス固有のクライアント ライブラリの使用を検討してください。 たとえば、Azure SQL の場合は、[`Microsoft.Data.SqlClient`](https://learn.microsoft.com/ja-jp/sql/connect/ado-net/sql/azure-active-directory-authentication#using-managed-identity-authentication) パッケージを使用します。

### 言語固有の API リファレンス

| 言語 | Azure ID | MSAL |
| --- | --- | --- |
| 。網 | .NET 用の Azure ID クライアント ライブラリを する | MSAL .NET の |
| C++ | C++ 用の Azure ID クライアント ライブラリを する |  |
| ジャワ | Java 用 Azure ID クライアント ライブラリの | MSAL Java の |
| JavaScript | JavaScript 用の Azure ID クライアント ライブラリを する | MSAL JavaScript の |
| ニシキヘビ | Python 用 Azure IDENTITY クライアント ライブラリを する | MSAL Python の |
| 行く | Go 用の Azure ID クライアント ライブラリを する | [MSAL Go](https://pkg.go.dev/github.com/AzureAD/microsoft-authentication-library-for-go) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/secretless-authentication"} -->
## Azure でのシークレットレス認証 - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/secretless-authentication
- Service: entra-id / managed-identities
- Article date: 2025-05-09
- Summary: 資格情報のリスクを軽減し、セキュリティを強化し、ゼロ トラスト原則を使用してユーザー エクスペリエンスを合理化するための Azure でのシークレットレス認証について説明します。

パスワードとセキュリティ キーは何十年もの間、デジタル セキュリティの基盤でしたが、最新の脅威に対応することはできなくなりました。 ゼロ トラスト モデルに合わせたシークレットレス認証は、アクセス制御とユーザー検証を資格情報なしのアプローチに移行します。 シークレットレス認証では、パスワード、証明書、シークレット、セキュリティ キーなどの従来の共有資格情報に依存することなく、クラウドでセキュリティで保護されたアプリケーションを設計する必要があります。 シークレットレス認証には、次の利点があります。

- 資格情報のリスクの軽減: パスワードを排除することで、シークレットレス認証によって、資格情報の盗難、フィッシング攻撃、ブルート フォース攻撃に関連するリスクが軽減されます。 このアプローチでは、生体認証、デジタル証明書、ハードウェア トークンなどの検証可能な ID 要素を使用します。
- アクセス制御の合理化: 共有シークレットではなく、真の ID を検証するメカニズムに依存することで、セキュリティが強化され、ユーザー エクスペリエンスが合理化されます。 これは、真の ID に基づいてすべてのアクセス要求を検証することで、ゼロ トラストの原則と一致します。
- エンド ユーザー エクスペリエンスの向上: ユーザーは、よりシームレスで安全な認証プロセスを利用できます。これにより、パスワードリセットの必要性が軽減され、全体的なユーザー満足度が向上します。

この記事では、Azure でのシークレットレス認証とその利点、およびクライアント アプリケーション、Azure サービス間通信、外部ワークロードなど、さまざまなシナリオにわたってそれを実装する方法について説明します。

#### パスワードとシークレットのセキュリティの課題

パスワードやその他のシークレットは慎重に使用する必要があり、開発者は安全でない場所にパスワードを配置しないでください。 多くのアプリは、ユーザー名、パスワード、アクセス キーを使用して、バックエンド データベース、キャッシュ、メッセージング、イベント サービスに接続します。 公開された場合、これらの資格情報を使用して、今後のキャンペーン用に作成した販売カタログや、非公開にする必要がある顧客データなどの機密情報への不正アクセスを取得できます。

アプリケーション自体にパスワードを埋め込むと、コード リポジトリを介した検出など、さまざまな理由で大きなセキュリティ リスクが発生します。 多くの開発者は、アプリケーションが異なる環境からパスワードを読み込むことができるように、環境変数を使用してこのようなパスワードを外部化します。 ただし、これにより、リスクがコード自体から実行環境に移行されるだけです。 環境にアクセスできるユーザーは誰でもパスワードを盗むことができるため、データ流出のリスクが高まります。

#### キーのセキュリティの課題

Azure アプリケーション開発で暗号化キーを使用すると、セキュリティと運用効率の両方を複雑にする可能性のあるいくつかの課題が発生します。 主な問題の 1 つは、キー管理の複雑さです。これには、さまざまなサービスや環境にキーをローテーション、配布、安全に格納する面倒なタスクが含まれます。 この継続的なプロセスには専用のリソースが必要であり、定期的なメンテナンスと監視が必要なため、運用コストが大幅に増加する可能性があります。 また、キーの漏洩による不正アクセスは機密データを侵害する可能性があるため、キーの漏洩や悪用に関連する大きなセキュリティ リスクがあります。 さらに、キーベースの認証方法では、多くの場合、動的環境に必要なスケーラビリティと柔軟性が欠け、変化する要件に適応し、運用を効果的にスケーリングすることが困難になります。 これらの課題は、リスクを軽減し、運用を合理化するために、マネージド ID などのより安全で効率的な認証方法を採用することの重要性を強調しています。

### クライアント アプリケーション (ユーザー向け) が Microsoft Entra で保護されたリソースにアクセスする

多要素認証 (MFA) などの機能は、organizationをセキュリティで保護するための優れた方法ですが、多くの場合、ユーザーはパスワードを覚えておく必要がある上に、追加のセキュリティ 層に不満を感じます。 パスワードレス認証方法は、パスワードが削除され、自分が持っているものや知っているものに置き換えられるため、より便利です。

認証に関しては、組織ごとに異なるニーズがあります。 Microsoft Entra ID と Azure Government には、次のパスワードレス認証オプションが統合されています。

- Windows Hello for Business
- macOS のプラットフォーム資格情報
- スマート カード認証を使用した macOS 用プラットフォーム シングル サインオン (PSSO)
- Microsoft Authenticator
- パスキー (FIDO2)
- 証明書ベースの認証

詳細については、 [Microsoft Entra パスワードレス サインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passwordless)に関するページを参照してください。

### Azure サービスから Azure へのサービス (Azure 内)

Azure リソース間の認証 (サービス間認証) の場合、Azure [リソースのマネージド ID を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)使用することをお勧めします。 ただし、テナント間でリソースを認証してアクセスする場合は、アプリケーションでフェデレーション ID 資格情報としてマネージド ID を設定する必要があります。

#### Microsoft Entra で保護されたリソースにアクセスする (同じテナント)

Azure リソースとリソースの間でサービス間認証が必要なシナリオでは、マネージド ID と Azure ID クライアント ライブラリの `DefaultAzureCredential` クラスが推奨されるオプションです。

マネージド ID は、Azure コンピューティング リソース (Azure Virtual Machines、Azure Functions、Azure Kubernetes など) または [マネージド ID をサポートする任意の Azure サービスに](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)割り当てることができる ID です。 マネージド ID がコンピューティング リソースに割り当てられると、ストレージ アカウント、SQL データベース、Cosmos DB などのダウンストリーム依存関係リソースへのアクセスを承認できます。 マネージド ID は、アクセス キー、パスワード、証明書などのシークレット、またはサービス間の依存関係に対する他の形式の認証を置き換えます。

詳細については、「 [Azure リソースのマネージド ID とは」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)参照してください。

アプリケーションに `DefaultAzureCredential` とマネージド ID を実装し、Microsoft Entra ID とロール ベースのアクセス制御 (RBAC) を使用して Azure サービスへのパスワードなしの接続を作成します。 `DefaultAzureCredential` は複数の認証方法をサポートしており、どの方法が使用されるかは実行時に決定されます。 このアプローチを採用すると、環境固有のコードを実装することなく、異なる環境 (ローカル開発環境と運用環境) で異なる認証方法をアプリに使用できます。

アプリケーションで `DefaultAzureCredential` とマネージド ID を使用する方法の詳細については、「 [Azure アプリとサービス間のシークレットレス接続の構成](https://learn.microsoft.com/ja-jp/azure/storage/common/multiple-identity-scenarios?tabs=csharp)」を参照してください。

#### Microsoft Entra で保護されたリソースにアクセスする (テナント間)

Azure リソース間でサービス間認証が必要なシナリオでは、マネージド ID をお勧めします。 ただし、テナント (マルチテナント アプリ) 間でリソースにアクセスする場合は、マネージド ID はサポートされません。 以前は、クライアント シークレットまたは証明書を資格情報として使用してマルチテナント アプリケーションを作成し、複数のテナントのリソースを認証してアクセスしました。 これにより、シークレットの公開に関する重大なリスクが残り、証明書のライフサイクルを格納、ローテーション、および維持する負担が増えます。

Azure ワークロードでは、マネージド ID をフェデレーション ID 資格情報として使用して、シークレットや証明書に依存することなく、テナント間で Microsoft Entra で保護されたリソースに安全にアクセスできるようになりました。

詳細については、「 [マネージド ID を信頼するようにアプリケーションを構成する」を参照してください](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity)。

### Azure サービスが外部または Microsoft Entra 以外の保護されたリソースにアクセスする

Microsoft Entra またはアクセスにパスワード、シークレット、キー、または証明書を必要とする Azure サービスによって保護されていないリソースを認証してアクセスする Azure ワークロードの場合、マネージド ID を直接使用することはできません。 この場合は、Azure Key Vault を使用して、ターゲット リソースの資格情報を格納します。 ワークロードのマネージド ID を使用して、キー コンテナーから資格情報を取得し、資格情報を使用してターゲット リソースにアクセスします。

詳細については、「 [コードで Key Vault に対する認証」](https://learn.microsoft.com/ja-jp/azure/key-vault/general/developers-guide#authenticate-to-key-vault-in-code)を参照してください。

### 外部ワークロード (Azure の外部) が Microsoft Entra で保護されたリソースにアクセスする

ソフトウェア ワークロードが Azure の外部 (たとえば、オンプレミスのデータセンター、開発者のマシン、または別のクラウド) で実行されていて、Azure リソースにアクセスする必要がある場合、Azure マネージド ID を直接使用することはできません。 以前は、Microsoft ID プラットフォームにアプリケーションを登録し、外部アプリのクライアント シークレットまたは証明書を使用してサインインしていました。 これらの資格情報はセキュリティ上のリスクをもたらすため、安全に保管し、定期的にローテーションする必要があります。 また、資格情報の有効期限が切れると、サービスのダウンタイムのリスクも発生します。

ワークロード ID フェデレーションを使用して、GitHub や Google などの外部 ID プロバイダー (IdP) からのトークンを信頼するように、Microsoft Entra ID でユーザー割り当てマネージド ID またはアプリ登録を構成します。 その信頼関係が作成されると、外部のソフトウェア ワークロードは、外部 IdP からの信頼されたトークンを Microsoft ID プラットフォームからのアクセス トークンと交換します。 ソフトウェア ワークロードは、そのアクセス トークンを使用して、ワークロードにアクセス権が付与されている Microsoft Entra で保護されたリソースにアクセスします。 資格情報を手動で管理するメンテナンスの負担がなくなり、シークレットが漏洩するリスクや、証明書の有効期限が切れるリスクが排除されます。

詳細については、 [ワークロード ID のフェデレーションに関するページを参照](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/tutorial-linux-managed-identities-vm-access"} -->
## チュートリアル - Linux VM/VMSS を使用して Azure リソースにアクセスする - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/tutorial-linux-managed-identities-vm-access
- Service: entra-id / managed-identities
- Article date: 2024-06-10
- Summary: Linux VM/VMSS を使用して Azure リソースにアクセスする方法を示すチュートリアル。

### 前提条件

- マネージド ID の知識。 Azure リソースのマネージド ID 機能に慣れていない場合は、こちらの[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を参照してください。
- Azure アカウント。[無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- 必要なリソース作成とロール管理の手順を実行するための、適切なスコープ (サブスクリプションまたはリソース グループ) の*所有者*アクセス許可。 ロールの割り当てに関するサポートが必要な場合は、[Azure ロールの割り当てによる Azure サブスクリプション リソースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)に関するページをご覧ください。
- システム割り当てマネージド ID が有効になっている Linux 仮想マシン (VM)。
    - このチュートリアル用に VM を作成する必要がある場合は、[システム割り当て ID が有効な仮想マシンの作成](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities)に関する記事をご覧ください。

::: zone pivot="identity-linux-mi-vm-access-data-lake"

### Linux VM のシステム割り当てマネージド ID を使用して Azure Data Lake Store にアクセスする

このチュートリアルでは、Linux 仮想マシン (VM) のシステム割り当てマネージド ID を使用して Azure Data Lake Store にアクセスする方法について説明します。

学習内容は次のとおりです。

- VM に Azure Data Lake Store へのアクセスを許可する
- VM のシステム割り当てマネージド ID を使用して Azure Data Lake Store にアクセスするためのアクセス トークンを取得する

### アクセス権の付与

このセクションでは、Azure Data Lake Store 内のファイルとフォルダーへのアクセス権を VM に付与する方法を示します。 この手順では、既存の Data Lake Store インスタンスを使用することも、新しいものを作成することもできます。 Azure Portal を使用して Data Lake Store インスタンスを作成するには、[Azure Data Lake Store のクイック スタート](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lake-store-get-started-portal)の手順を実行します。 [Azure Data Lake Store のドキュメント](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lake-store-overview)に、Azure CLI と Azure PowerShell を使用するクイック スタートも用意されています。

Data Lake Store で新しいフォルダーを作成し、Linux VM のシステム割り当てマネージド ID にそのフォルダー内のファイルに対して読み取り、書き込み、実行を行うためのアクセス許可を付与します。

1. Azure Portal の左側のウィンドウで **[Data Lake Store]** を選択します。
2. 使用する Data Lake Store インスタンスを選択します。
3. コマンド バーの **[データ エクスプローラー]** を選択します。
4. Data Lake Store インスタンスのルート フォルダーが選択されます。 コマンド バーの **[アクセス]** を選択します。
5. **[追加]** を選択します。 **[選択]** ボックスにお使いの VM の名前 (例: **DevTestVM**) を入力します。 検索結果からお使いの VM を選び、**[選択]** を選びます。
6. **[アクセス許可の選択]** を選びます。 **[読み取り]** と **[実行]** を選択して **[このフォルダー]** に追加し、**[アクセス許可のみ]** として追加してから、**[OK]** を選択します。 アクセス許可が正常に追加されます。
7. **[アクセス]** ウィンドウを閉じます。
8. 新しいフォルダーを作成し、コマンド バーで **[新しいフォルダー]** を選択し、新しいフォルダーに名前 (例: **TestFolder**) を付けてから、**[OK]** を選択します。
9. 作成したフォルダーを選択し、コマンド バーの **[アクセス]** を選択します。
10. **[追加]** を選択し、**[選択]** ボックスに VM の名前を入力します。
11. 検索結果からお使いの VM を選び、**[選択]** を選びます。
12. **[アクセス許可の選択]** を選択し、**[読み取り]**、**[書き込み]**、**[実行]** の順に選択します。
13. **[このフォルダー]** に追加することを選択してから、**[アクセス許可エントリと既定のアクセス許可エントリ]** として追加し、**[OK]** を選択します。 アクセス許可が正常に追加されます。

この時点で Azure リソースのマネージド ID は、作成したフォルダーのファイルに対してすべての操作を実行できます。 Data Lake Store のアクセス管理の詳細については、[Data Lake Store のアクセスの制御](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lake-store-access-control)に関するページをご覧ください。

### アクセス トークンを取得する

このセクションでは、アクセス トークンを取得し、Data Lake Store ファイル システムを呼び出す方法を示します。 Azure Data Lake Store は Microsoft Entra 認証をネイティブにサポートするため、Azure リソース用マネージド ID を使って取得されたアクセス トークンを直接受け入れることができます。

Data Lake Store のファイルシステムに対する認証を行うために、お使いの Data Lake Store ファイルシステムのエンドポイントに Microsoft Entra ID によって発行されたアクセス トークンを送信します。 アクセス トークンは、Authorization ヘッダーに `Bearer \<ACCESS_TOKEN_VALUE\>` という形式で指定します。 Microsoft Entra 認証に対する Data Lake Store のサポートの詳細については、[Microsoft Entra ID を使った Data Lake Store での認証](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lakes-store-authentication-using-azure-active-directory)に関するページを参照してください。

次に、cURL を使用して REST 要求を実行して、Data Lake Store ファイル システムの REST API に対する認証を行います。

注

Data Lake Store ファイル システムのクライアント SDK では、Azure リソースのマネージド ID はまだサポートされていません。

これらの手順を完了するには、SSH クライアントが必要です。 Windows を使用している場合は、[Windows Subsystem for Linux](https://learn.microsoft.com/ja-jp/windows/wsl/about) で SSH クライアントを使用することができます。 SSH クライアント キーの構成について支援が必要な場合は、「[Azure 上の Windows で SSH キーを使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)」または「[Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)」をご覧ください。

1. ポータルで Linux VM を参照し、**[概要]** セクションで **[接続]** を選択します。
2. 任意の SSH クライアントを使用して、VM に接続します。
3. ターミナル ウィンドウで、cURL を使用して、Azure リソース エンドポイントのローカル マネージド ID に、Data Lake Store ファイル システムのアクセス トークンを取得するよう要求します。 Data Lake Store のリソース識別子は `https://datalake.azure.net/` です。 リソース識別子の末尾にスラッシュが含まれることが重要です。

    ```bash
    curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fdatalake.azure.net%2F' -H Metadata:true   
    ```

    成功応答では、次のように Data Lake Store への認証に使用するアクセス トークンが返されます。

    ```bash
    {"access_token":"eyJ0eXAiOiJ...",
     "refresh_token":"",
     "expires_in":"3599",
     "expires_on":"1508119757",
     "not_before":"1508115857",
     "resource":"https://datalake.azure.net/",
     "token_type":"Bearer"}
    ```
4. cURL を使用してルート フォルダー内のフォルダーを一覧表示するには、お使いの Data Lake Store ファイルシステムの REST エンドポイントに要求を行います。 これは、すべてが正しく構成されていることを確認する最適な方法です。 前の手順で入手したアクセス トークンの値をコピーします。 Authorization ヘッダーの文字列 `Bearer` に、大文字の "B" があることが重要です。Azure Portal の [Data Lake Store] ウィンドウの **[概要]** セクションで、ご利用の **Data Lake Store** インスタンスの名前を確認できます。

    ```bash
    curl https://<YOUR_ADLS_NAME>.azuredatalakestore.net/webhdfs/v1/?op=LISTSTATUS -H "Authorization: Bearer <ACCESS_TOKEN>"
    ```

    成功した応答は次のようになります:

    ```bash
    {"FileStatuses":{"FileStatus":[{"length":0,"pathSuffix":"TestFolder","type":"DIRECTORY","blockSize":0,"accessTime":1507934941392,"modificationTime":1508105430590,"replication":0,"permission":"770","owner":"bd0e76d8-ad45-4fe1-8941-04a7bf27f071","group":"bd0e76d8-ad45-4fe1-8941-04a7bf27f071"}]}}
    ```
5. 次に、Data Lake Store インスタンスにファイルをアップロードします。 まず、アップロードするファイルを作成します。

    ```bash
    echo "Test file." > Test1.txt
    ```
6. cURL を使用して先ほど作成したフォルダーにファイルをアップロードするには、Data Lake Store ファイルシステムの REST エンドポイントに要求を行います。 アップロードではリダイレクトが必要ですが、cURL が自動的にリダイレクトします。

    ```bash
    curl -i -X PUT -L -T Test1.txt -H "Authorization: Bearer <ACCESS_TOKEN>" 'https://<YOUR_ADLS_NAME>.azuredatalakestore.net/webhdfs/v1/<FOLDER_NAME>/Test1.txt?op=CREATE' 
    ```

    成功した応答は次のようになります:

    ```bash
    HTTP/1.1 100 Continue
    HTTP/1.1 307 Temporary Redirect
    Cache-Control: no-cache, no-cache, no-store, max-age=0
    Pragma: no-cache
    Expires: -1
    Location: https://mytestadls.azuredatalakestore.net/webhdfs/v1/TestFolder/Test1.txt?op=CREATE&write=true
    x-ms-request-id: 756f6b24-0cca-47ef-aa12-52c3b45b954c
    ContentLength: 0
    x-ms-webhdfs-version: 17.04.22.00
    Status: 0x0
    X-Content-Type-Options: nosniff
    Strict-Transport-Security: max-age=15724800; includeSubDomains
    Date: Sun, 15 Oct 2017 22:10:30 GMT
    Content-Length: 0
    
    HTTP/1.1 100 Continue
    
    HTTP/1.1 201 Created
    Cache-Control: no-cache, no-cache, no-store, max-age=0
    Pragma: no-cache
    Expires: -1
    Location: https://mytestadls.azuredatalakestore.net/webhdfs/v1/TestFolder/Test1.txt?op=CREATE&write=true
    x-ms-request-id: af5baa07-3c79-43af-a01a-71d63d53e6c4
    ContentLength: 0
    x-ms-webhdfs-version: 17.04.22.00
    Status: 0x0
    X-Content-Type-Options: nosniff
    Strict-Transport-Security: max-age=15724800; includeSubDomains
    Date: Sun, 15 Oct 2017 22:10:30 GMT
    Content-Length: 0
    ```

ついに、Data Lake Store ファイル システムに他の API を使用して、ファイルへの追加、ファイルのダウンロードなどを実行できるようになりました。

::: zone-end

::: zone pivot="identity-linux-mi-vm-access-storage"

### Linux VM のシステム割り当てマネージド ID を使用して Azure Storage にアクセスする

このチュートリアルでは、Linux 仮想マシン (VM) のシステム割り当てマネージド ID を使用して Azure Storage にアクセスする方法について説明します。

学習内容は次のとおりです。

- ストレージ アカウントの作成
- ストレージ アカウントに BLOB コンテナーを作成する
- Linux VM のマネージド ID に Azure Storage コンテナーへのアクセス権を付与します
- アクセス トークン取得し、それを使用して Azure Storage を呼び出す

### ストレージ アカウントの作成

この例の CLI スクリプトを実行するには、次の 2 つのオプションがあります。

- Azure portal から、または各コード ブロックの右上隅にある [\[試してみる\]](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) ボタンを使用して、**Azure Cloud Shell** を使用します。
- ローカル CLI コンソールを使用する場合は、[CLI 2.0 の最新バージョン (2.0.23 以降) をインストール](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)します。

まず、ストレージ アカウントを作成します。

1. Azure portal の左上隅にある **[リソースの作成]** ボタンを選択します。
2. **[ストレージ]**、**[ストレージ アカウント - Blob、File、Table、Queue]** の順に選択します。
3. **[名前]** で、ストレージ アカウントの名前を入力します。
4. **[デプロイ モデル]** と **[アカウントの種類]** がそれぞれ **[Resource manager]** と **[ストレージ (汎用 v1)]** に設定されている必要があります。
5. **[サブスクリプション]** と **[リソース グループ]** が、前の手順で VM を作成したときに指定したものと一致していることを確認します。
6. **［作成］** を選択します

    [Image: 新しいストレージ アカウントの作成画面を示したスクリーンショット。]

### BLOB コンテナーを作成し、ファイルをストレージ アカウントにアップロードする

ファイルには Blob Storage が必要であるため、ファイルを格納する BLOB コンテナーを作成する必要があります。 次に、新しいストレージ アカウントで、BLOB コンテナーにファイルをアップロードします。

1. 新しく作成したストレージ アカウントに移動します。
2. **[Blob Service]**、**[コンテナー]** の順に選択します。
3. ページの上部にある **[+ コンテナー]** を選択します。
4. **[新しいコンテナー]** を選択し、コンテナーの名前を入力します。
5. **[パブリック アクセス レベル]** が既定値であることを確認します。

    [Image: ストレージ コンテナー作成画面を示したスクリーンショット。]
6. 任意のエディターを使用して、ローカル コンピューターに *hello world.txt* という名前のファイルを作成します。 ファイルを開き、「*Hello world!*」というテキストを追加して保存します。
7. コンテナー名を選択し、**[アップロード]** を選択します。 これにより、新しく作成されたコンテナーにファイルがアップロードされます。
8. **[BLOB のアップロード]** ペインの **[ファイル]** セクションで、フォルダー アイコンを選択し、ローカル コンピューター上の **hello\_world.txt** ファイルを参照します。
9. ファイルを選択し、**[アップロード]** を選択します。

    [Image: テキスト ファイルのアップロード セクションを示すスクリーンショット。]

### VM に Azure Storage コンテナーへのアクセスを許可する

VM のマネージド ID を使用して、Azure Storage Blob のデータを取得できます。 Azure リソースのマネージド ID は、Microsoft Entra 認証をサポートするリソースの認証に使用することができます。 お使いのストレージ アカウントを含むリソース グループのスコープで、マネージド ID に [storage-blob-data-reader](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#storage-blob-data-reader) ロールを割り当てることによって、アクセス権を付与します。

詳細な手順については、「[Azure portal を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)」を参照してください。

注

ストレージの確認にアクセス許可を付与するために使用できるさまざまなロールの詳細については、「[Microsoft Entra ID を使用した BLOB とキューへのアクセスの承認](https://learn.microsoft.com/ja-jp/azure/storage/blobs/authorize-access-azure-active-directory#assign-azure-roles-for-access-rights)」を参照してください

### アクセス トークン取得し、それを使用して Azure Storage を呼び出す

Azure Storage は Microsoft Entra 認証をネイティブにサポートするため、マネージド ID を使用して取得したアクセス トークンを直接受け入れることができます。 これは Azure Storage の Microsoft Entra ID との統合の一部であり、接続文字列に資格情報を提供することとは異なります。

次の手順を完了するには、前に作成した VM から行う必要があり、それに接続するには SSH クライアントが必要です。

Windows を使用している場合は、[Windows Subsystem for Linux](https://learn.microsoft.com/ja-jp/windows/wsl/about) で SSH クライアントを使用することができます。 SSH クライアント キーの構成について支援が必要な場合は、「[Azure 上の Windows で SSH キーを使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)」または「[Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)」をご覧ください。

1. Azure portal で **[Virtual Machines]** に移動し、Linux 仮想マシンに移動して、**[概要]** ページにある **[接続]** を選びます。 VM に接続する文字列をコピーします。
2. 任意の SSH クライアントを使用して、VM に**接続**します。
3. ターミナル ウィンドウで、CURL を使用して、ローカルのマネージド ID エンドポイントに対して Azure Storage のアクセス トークンを取得するよう要求します。

    ```bash
    curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fstorage.azure.com%2F' -H Metadata:true
    ```
4. アクセス トークンを使用して Azure Storage にアクセスします。 たとえば、以前にコンテナーにアップロードしたサンプル ファイルの内容を読み取る場合は、`<STORAGE ACCOUNT>`、`<CONTAINER NAME>`、`<FILE NAME>` の値を前に指定した値に、`<ACCESS TOKEN>` を前の手順で返されたトークンに置き換えます。

    ```bash
    curl https://<STORAGE ACCOUNT>.blob.core.windows.net/<CONTAINER NAME>/<FILE NAME> -H "x-ms-version: 2017-11-09" -H "Authorization: Bearer <ACCESS TOKEN>"
    ```

    応答には、次のようなファイルの内容が含まれています。

    ```bash
    Hello world! :)
    ```

最後に、次に示すように、トークンを変数に保存して、2 番目のコマンドに渡すこともできます。

```bash
# Run the first curl command and capture its output in a variable
access_token=$(curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fstorage.azure.com%2F' -H Metadata:true | jq -r '.access_token')

# Run the second curl command with the access token
curl "https://<STORAGE ACCOUNT>.blob.core.windows.net/<CONTAINER NAME>/<FILE NAME>" \
  -H "x-ms-version: 2017-11-09" \
  -H "Authorization: Bearer $access_token"

```

::: zone-end

::: zone pivot="identity-linux-mi-vm-access-sas-key"

### Linux VM のシステム割り当てマネージド ID を使用して SAS 資格情報で Azure Storage にアクセスする

このチュートリアルでは、Linux 仮想マシン (VM) のシステム割り当てマネージド ID を使用して、ストレージの Shared Access Signature (SAS) 資格情報 (具体的には [サービス SAS 資格情報](https://learn.microsoft.com/ja-jp/azure/storage/common/storage-sas-overview#types-of-shared-access-signatures)) を取得する方法について説明します。

注

このチュートリアルで生成された SAS キーは、この VM に制限またはバインドされません。

サービス SAS は、アカウント アクセス キーを公開することなく、ストレージ アカウント内のオブジェクトへの制限付きアクセスを許可します。 期間を限定し、特定のサービスについて、アクセス権を付与することができます。 ストレージ SDK の使用時など、ストレージ操作を実行するときに、SAS 資格情報を通常どおりに使用できます。 このチュートリアルでは、Azure Storage CLI を使用して BLOB をアップロードしてダウンロードします。

学習内容は次のとおりです。

- ストレージ アカウントの作成
- ストレージ アカウントに BLOB コンテナーを作成する
- Resource Manager で VM にストレージ アカウント SAS へのアクセス権を付与する
- VM の ID を使用してアクセス トークンを取得し、それを使用して Resource Manager から SAS を取得する

### ストレージ アカウントの作成

まだお持ちでない場合は、ストレージ アカウントを作成する必要があります。 この手順をスキップし、既存のストレージ アカウントのキーへのアクセスを、VM のシステム割り当てマネージド ID に付与することができます。

1. Azure portal の左上隅にある **[+/新しいサービスの作成]** ボタンを選択します。
2. **[ストレージ]**、**[ストレージ アカウント]** の順に選択すると、**[ストレージ アカウントの作成]** パネルが表示されます。
3. ストレージ アカウントの **[名前]** を入力します。 後で必要になるので、この名前を覚えておいてください。
4. **[デプロイ モデル]** が *[Resource Manager]* に設定され、**[アカウントの種類]** が *[汎用]* に設定されていることを確認します。
5. **[サブスクリプション]** と **[リソース グループ]** が、VM を作成したときに指定したものと一致していることを確認します。
6. **[作成]** を選択してストレージ アカウントの作成を完了します。

    [Image: 新しいストレージ アカウントの作成画面を示したスクリーンショット。]

### ストレージ アカウントに BLOB コンテナーを作成する

チュートリアルの後半で、新しいストレージ アカウントにファイルをアップロードしてダウンロードします。 ファイルには Blob Storage が必要であるため、ファイルを格納する Blob コンテナーを作成する必要があります。

1. 新しく作成したストレージ アカウントに移動します。
2. 左側のパネルで、**[Blob service]** の下の **[コンテナー]** リンクを選択します。
3. ページの上部にある **[+ コンテナー]** を選択すると、**[新しいコンテナー]** パネルが表示されます。
4. コンテナーに名前を付け、アクセス レベルを選択して、**[OK]** を選択します。 このチュートリアルの後半で指定した名前が必要になります。

    [Image: ストレージ コンテナー作成画面を示したスクリーンショット。]

### VM のシステム割り当てマネージド ID にストレージ SAS を使用するためのアクセス権を付与する

Azure Storage では Microsoft Entra 認証がネイティブでサポートされます。そのため、VM のシステム割り当てマネージド ID を使用して Resource Manager からストレージ SAS を取得できます。 その後、その SAS を使ってストレージにアクセスできます。

このセクションでは、ストレージ アカウントの SAS へのアクセスを VM のシステム割り当てマネージド ID に付与します。 お使いのストレージ アカウントを含むリソース グループのスコープで、マネージド ID に [\[ストレージ アカウント共同作成者\]](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#storage-account-contributor) ロールを割り当てます。

詳細な手順については、「[Azure portal を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)」を参照してください。

注

ストレージの確認にアクセス許可を付与するために使用できるさまざまなロールの詳細については、[Microsoft Entra ID を使用した BLOB とキューへのアクセスの承認](https://learn.microsoft.com/ja-jp/azure/storage/blobs/authorize-access-azure-active-directory#assign-azure-roles-for-access-rights)に関するページを参照してください。

### VM ID を使用してアクセス トークンを取得し、そのアクセス トークンを使用して Azure Resource Manager を呼び出す

このチュートリアルの残りの部分では、以前に作成した VM から作業を行います。

これらの手順を完了するには、SSH クライアントが必要です。 Windows を使用している場合は、[Linux 用 Windows サブシステム](https://learn.microsoft.com/ja-jp/windows/wsl/install-win10)で SSH クライアントを使用することができます。 SSH クライアントのキーの構成についてサポートが必要な場合は、以下を参照してください。

- [Azure 上の Windows における SSH の使用方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)
- [Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)。

SSH クライアントを取得したら、次の手順に従います。

1. Azure portal で **[Virtual Machines]** に移動し、Linux 仮想マシンに移動します。
2. **[概要]** ページで、画面の上部にある **[接続]** を選択します。
3. VM に接続する文字列をコピーします。
4. SSH クライアントを使用して VM に接続します。
5. **Linux VM** の作成時に追加した **[パスワード]** を入力します。 パスワードを入力すると、正常にサインインできます。
6. CURL を使用して Azure Resource Manager のアクセス トークンを取得します。

アクセス トークンの CURL 要求と応答を次に示します。

```bash
curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fmanagement.azure.com%2F' -H Metadata:true    
```

注

前述の要求では、`resource` パラメーターの値は、Microsoft Entra ID で予期されるものと完全に一致している必要があります。 Azure Resource Manager のリソース ID を使用する場合は、URI の末尾にスラッシュを含める必要があります。

次の応答では、簡潔にするため access\_token 要素が短縮されています。

```json
{
  "access_token":"eyJ0eXAiOiJ...",
  "refresh_token":"",
  "expires_in":"3599",
  "expires_on":"1504130527",
  "not_before":"1504126627",
  "resource":"https://management.azure.com",
  "token_type":"Bearer"
}
```

### ストレージ呼び出しを行うために Azure Resource Manager から SAS 資格情報を取得する

次に、CURL を使用して、前のセクションで取得したアクセス トークンを使用して Resource Manager を呼び出します。 これを使用して、ストレージ SAS 資格情報を作成します。 SAS 資格情報を取得したら、ストレージのアップロード/ダウンロード操作を呼び出すことができます。

この要求のために、次の HTTP 要求のパラメーターを使用して SAS 資格情報を作成します。

```JSON
{
    "canonicalizedResource":"/blob/<STORAGE ACCOUNT NAME>/<CONTAINER NAME>",
    "signedResource":"c",              // The kind of resource accessible with the SAS, in this case a container (c).
    "signedPermission":"rcw",          // Permissions for this SAS, in this case (r)ead, (c)reate, and (w)rite.  Order is important.
    "signedProtocol":"https",          // Require the SAS be used on https protocol.
    "signedExpiry":"<EXPIRATION TIME>" // UTC expiration time for SAS in ISO 8601 format, for example 2017-09-22T00:06:00Z.
}
```

これらのパラメーターを SAS 資格情報の POST 要求の本文に含めます。 SAS 資格情報を作成するためのパラメーターの詳細については、[List Service SAS REST リファレンス](https://learn.microsoft.com/ja-jp/rest/api/storagerp/storage-accounts/list-service-sas)に関する記事を参照してください。

次の CURL 要求を使用して、SAS 資格情報を取得できます。 `<SUBSCRIPTION ID>`、`<RESOURCE GROUP>`、`<STORAGE ACCOUNT NAME>`、`<CONTAINER NAME>`、および `<EXPIRATION TIME>` の各パラメーターの値は、必ず実際の値に置き換えてください。 `<ACCESS TOKEN>` の値は、以前に取得したアクセス トークンに置き換えます。

```bash
curl https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Storage/storageAccounts/<STORAGE ACCOUNT NAME>/listServiceSas/?api-version=2017-06-01 -X POST -d "{\"canonicalizedResource\":\"/blob/<STORAGE ACCOUNT NAME>/<CONTAINER NAME>\",\"signedResource\":\"c\",\"signedPermission\":\"rcw\",\"signedProtocol\":\"https\",\"signedExpiry\":\"<EXPIRATION TIME>\"}" -H "Authorization: Bearer <ACCESS TOKEN>"
```

注

前述の URL のテキストでは大文字小文字が区別されるので、リソース グループに使用されている大文字小文字が正しく反映されていることを確認してください。 また、これは POST 要求であり、GET 要求ではないことを知っておくことが重要です。

CURL 応答は、SAS 資格情報を返します。

```bash
{"serviceSasToken":"sv=2015-04-05&sr=c&spr=https&st=2017-09-22T00%3A10%3A00Z&se=2017-09-22T02%3A00%3A00Z&sp=rcw&sig=QcVwljccgWcNMbe9roAJbD8J5oEkYoq%2F0cUPlgriBn0%3D"} 
```

Linux VM で、次のコマンドを使用して、BLOB ストレージ コンテナーにアップロードするサンプル BLOB ファイルを作成します。

```bash
echo "This is a test file." > test.txt
```

次に、SAS 資格情報を使用して CLI `az storage` コマンドで認証を行い、ファイルを BLOB コンテナーにアップロードします。 この手順では、VM に[最新の Azure CLI をインストールする](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)必要があります (まだインストールされていない場合)。

```azurecli
 az storage blob upload --container-name 
                        --file 
                        --name
                        --account-name 
                        --sas-token
```

応答:

```JSON
Finished[#############################################################]  100.0000%
{
  "etag": "\"0x8D4F9929765C139\"",
  "lastModified": "2017-09-21T03:58:56+00:00"
}
```

また、Azure CLI を使用してファイルをダウンロードし、SAS 資格情報を使用して認証することもできます。

要求:

```azurecli
az storage blob download --container-name
                         --file 
                         --name 
                         --account-name
                         --sas-token
```

応答:

```JSON
{
  "content": null,
  "metadata": {},
  "name": "testblob",
  "properties": {
    "appendBlobCommittedBlockCount": null,
    "blobType": "BlockBlob",
    "contentLength": 16,
    "contentRange": "bytes 0-15/16",
    "contentSettings": {
      "cacheControl": null,
      "contentDisposition": null,
      "contentEncoding": null,
      "contentLanguage": null,
      "contentMd5": "Aryr///Rb+D8JQ8IytleDA==",
      "contentType": "text/plain"
    },
    "copy": {
      "completionTime": null,
      "id": null,
      "progress": null,
      "source": null,
      "status": null,
      "statusDescription": null
    },
    "etag": "\"0x8D4F9929765C139\"",
    "lastModified": "2017-09-21T03:58:56+00:00",
    "lease": {
      "duration": null,
      "state": "available",
      "status": "unlocked"
    },
    "pageBlobSequenceNumber": null,
    "serverEncrypted": false
  },
  "snapshot": null
}
```

::: zone-end

::: zone pivot="identity-linux-mi-vm-access-key"

### Linux VM のシステム割り当てマネージド ID を使用してアクセス キーで Azure Storage にアクセスする

このチュートリアルでは、Linux 仮想マシン (VM) のシステム割り当てマネージド ID を使用してストレージ アカウント アクセス キーを取得する方法について説明します。 ストレージ SDK の使用時など、ストレージ操作を実行するときに、ストレージ アクセス キーを通常どおりに使用できます。 このチュートリアルでは、Azure CLI を使用して BLOB をアップロードおよびダウンロードします。

学習内容は次のとおりです。

- Resource Manager で VM にストレージ アカウント アクセス キーへのアクセス権を付与する
- VM の ID を使用してアクセス トークンを取得し、それを使用して Resource Manager からストレージ アクセス キーを取得する

### ストレージ アカウントの作成

このチュートリアルを開始する前に既存のストレージ アカウントがない場合は、作成する必要があります。 既存のストレージ アカウントがある場合は、次の手順に従って、VM システム割り当てマネージド ID に既存のストレージ アカウントのキーへのアクセスを付与します。

1. Azure portal の左上隅にある **[+/新しいサービスの作成]** ボタンを選択します。
2. **[ストレージ]**、**[ストレージ アカウント]** の順に選択すると、**[ストレージ アカウントの作成]** パネルが表示されます。
3. ストレージ アカウントの **[名前]** を入力します。 後で必要になるので、この名前を覚えておいてください。
4. **[デプロイ モデル]** が *[Resource Manager]* に設定され、**[アカウントの種類]** が *[汎用]* に設定されていることを確認します。
5. **[サブスクリプション]** と **[リソース グループ]** が、VM を作成したときに指定したものと一致していることを確認します。
6. **[作成]** を選択してストレージ アカウントの作成を完了します。

    [Image: 新しいストレージ アカウントの作成を示すスクリーンショット。]

### ストレージ アカウントに BLOB コンテナーを作成する

チュートリアルの後半で、新しいストレージ アカウントにファイルをアップロードしてダウンロードします。 ファイルには Blob Storage が必要であるため、ファイルを格納する Blob コンテナーを作成する必要があります。

1. 新しく作成したストレージ アカウントに移動します。
2. 左側のパネルで、**[Blob service]** の下の **[コンテナー]** リンクを選択します。
3. ページの上部にある **[+ コンテナー]** を選択すると、**[新しいコンテナー]** パネルが表示されます。
4. コンテナーに名前を付け、アクセス レベルを選択して、**[OK]** を選択します。 このチュートリアルの後半で指定した名前が必要になります。

    [Image: ストレージ コンテナーの作成を示すスクリーンショット。]

### VM のシステム割り当てマネージド ID にストレージ アカウント アクセス キーを使用するためのアクセス権を付与する

Azure Storage では、ネイティブで Microsoft Entra 認証がサポートされていません。 ただし、VM のシステム割り当てマネージド ID を使用して Resource Manager からストレージ SAS を取得し、その SAS を使用してストレージにアクセスできます。 この手順では、ストレージ アカウントの SAS へのアクセス権を VM のシステム割り当てマネージド ID に付与します。 お使いのストレージ アカウントを含むリソース グループのスコープで、マネージド ID に [\[ストレージ アカウント共同作成者\]](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#storage-account-contributor) ロールを割り当てることによってアクセス権を付与します。

詳細な手順については、「[Azure portal を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)」を参照してください。

注

ストレージの確認にアクセス許可を付与するために使用できるさまざまなロールの詳細については、[Microsoft Entra ID を使用した BLOB とキューへのアクセスの承認](https://learn.microsoft.com/ja-jp/azure/storage/blobs/authorize-access-azure-active-directory#assign-azure-roles-for-access-rights)に関するページを参照してください。

### VM ID を使用してアクセス トークンを取得し、そのアクセス トークンを使用して Azure Resource Manager を呼び出す

チュートリアルの残りの部分では、先ほど作成した VM から作業します。

これらの手順を完了するには、SSH クライアントが必要です。 Windows を使用している場合は、[Windows Subsystem for Linux](https://learn.microsoft.com/ja-jp/windows/wsl/install) で SSH クライアントを使用することができます。 SSH クライアント キーの構成について支援が必要な場合は、「[Azure 上の Windows で SSH キーを使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)」または「[Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)」をご覧ください。

1. Azure portal で **[Virtual Machines]** に移動し、Linux 仮想マシンを選び、**[概要]** ページの上部にある **[接続]** を選びます。 VM に接続する文字列をコピーします。
2. SSH クライアントを使用して VM に接続します。
3. 次に、**Linux VM** の作成時に追加した **[パスワード]** を入力する必要があります。
4. CURL を使用して Azure Resource Manager のアクセス トークンを取得します。

    アクセス トークンの CURL 要求と応答を次に示します。

    ```bash
    curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fmanagement.azure.com%2F' -H Metadata:true
    ```

    注

    前述の要求では、"resource" パラメーターの値は、Microsoft Entra ID で予期されるものと完全に一致している必要があります。 Azure Resource Manager のリソース ID を使用する場合は、URI の末尾にスラッシュを含める必要があります。 次の応答では、簡潔にするため access\_token 要素が短縮されています。

    ```json
    {
      "access_token": "eyJ0eXAiOiJ...",
      "refresh_token": "",
      "expires_in": "3599",
      "expires_on": "1504130527",
      "not_before": "1504126627",
      "resource": "https://management.azure.com",
      "token_type": "Bearer"
    }
    ```

### ストレージ呼び出しを行うために Azure Resource Manager からストレージ アカウント アクセス キーを取得する

ここで、CURL を使用して、前のセクションで取得したアクセス トークンで Resource Manager を呼び出し、ストレージ アクセス キーを取得します。 ストレージ アクセス キーを取得したら、ストレージのアップロード/ダウンロード操作を呼び出すことができます。 `<SUBSCRIPTION ID>`、`<RESOURCE GROUP>`、および `<STORAGE ACCOUNT NAME>` の各パラメーターの値は、必ず実際の値に置き換えてください。 `<ACCESS TOKEN>` の値は、以前に取得したアクセス トークンに置き換えます。

```bash
curl https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Storage/storageAccounts/<STORAGE ACCOUNT NAME>/listKeys?api-version=2016-12-01 --request POST -d "" -H "Authorization: Bearer <ACCESS TOKEN>" 
```

注

前述の URL のテキストでは大文字小文字が区別されるので、リソース グループに使用されている大文字小文字が正しく反映されていることを確認してください。 また、これは GET 要求ではなく POST 要求であることを認識し、-d (NULL を指定可能) を指定して長さの制限を取得するための値を渡すことが重要です。

CURL 応答では、キーのリストが返されます。

```bash
{"keys":[{"keyName":"key1","permissions":"Full","value":"iqDPNt..."},{"keyName":"key2","permissions":"Full","value":"U+uI0B..."}]} 
```

BLOB ストレージ コンテナーにアップロードするサンプル BLOB ファイルを作成します。 Linux VM でこれを行うには、次のコマンドを使用します。

```bash
echo "This is a test file." > test.txt
```

次に、ストレージ アクセス キーを使用して CLI `az storage` コマンドで認証を行い、ファイルを BLOB コンテナーにアップロードします。 この手順では、VM に[最新の Azure CLI をインストールする](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)必要があります (まだインストールされていない場合)。

```azurecli
az storage blob upload -c <CONTAINER NAME> -n test.txt -f test.txt --account-name <STORAGE ACCOUNT NAME> --account-key <STORAGE ACCOUNT KEY>
```

応答:

```JSON
Finished[#############################################################]  100.0000%
{
  "etag": "\"0x8D4F9929765C139\"",
  "lastModified": "2017-09-12T03:58:56+00:00"
}
```

さらに、Azure CLI を使用してファイルをダウンロードし、ストレージ アクセス キーを使用して認証することもできます。

要求:

```azurecli
az storage blob download -c <CONTAINER NAME> -n test.txt -f test-download.txt --account-name <STORAGE ACCOUNT NAME> --account-key <STORAGE ACCOUNT KEY>
```

応答:

```JSON
{
  "content": null,
  "metadata": {},
  "name": "test.txt",
  "properties": {
    "appendBlobCommittedBlockCount": null,
    "blobType": "BlockBlob",
    "contentLength": 21,
    "contentRange": "bytes 0-20/21",
    "contentSettings": {
      "cacheControl": null,
      "contentDisposition": null,
      "contentEncoding": null,
      "contentLanguage": null,
      "contentMd5": "LSghAvpnElYyfUdn7CO8aw==",
      "contentType": "text/plain"
    },
    "copy": {
      "completionTime": null,
      "id": null,
      "progress": null,
      "source": null,
      "status": null,
      "statusDescription": null
    },
    "etag": "\"0x8D5067F30D0C283\"",
    "lastModified": "2017-09-28T14:42:49+00:00",
    "lease": {
      "duration": null,
      "state": "available",
      "status": "unlocked"
    },
    "pageBlobSequenceNumber": null,
    "serverEncrypted": false
  },
  "snapshot": null
}
```

::: zone-end

::: zone pivot="identity-linux-mi-vm-access-key-vault"

### Linux VM のシステム割り当てマネージド ID を使用して Azure Key Vault にアクセスする

このチュートリアルでは、Linux 仮想マシン (VM) でシステム割り当てマネージド ID を使用して [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/general/overview) にアクセスする方法について説明します。 Key Vault により、クライアント アプリケーションは、Microsoft Entra ID で保護されていないリソースにシークレットを使ってアクセスできます。 マネージド サービス ID は Azure によって自動的に管理され、認証情報をコードに含めなくても、Microsoft Entra 認証をサポートするサービスに対して認証を行うことができます。

学習内容は次のとおりです。

- Key Vault に格納されているシークレットへ VM のアクセスを許可する
- VM の ID を使用してアクセス トークンを取得して、Key Vault からシークレットを取得する

### Key Vault の作成

システム割り当てマネージド ID が有効になっている Linux 仮想マシンも必要です。

- このチュートリアル用に仮想マシンを作成する必要がある場合は、[Azure portal での Linux 仮想マシンの作成](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-portal#create-virtual-machine)に関する記事に従ってください。

このセクションでは、Key Vault に格納されているシークレットへのアクセスを VM に許可する方法を説明します。 Azure リソースのマネージド ID を使用すると、Microsoft Entra 認証をサポートするリソースに対して認証するためのアクセス トークンをコードで取得できます。

ただし、すべての Azure サービスで Microsoft Entra 認証がサポートされているわけではありません。 Azure リソースのマネージド ID をこれらのサービスと共に使用するには、Azure Key Vault にサービス資格情報を保存し、VM のマネージド ID を使用して Key Vault にアクセスして、資格情報を取得します。

まず、Key Vault を作成し、VM のシステム割り当てマネージド ID に Key Vault へのアクセスを付与する必要があります。

1. [Azure portal](https://portal.azure.com/) にサインインします。
2. 左側のナビゲーション バーの上部で、**[リソースの作成]** を選びます。
3. **[Marketplace を検索]** ボックスに「**Key Vault**」と入力し、**Enter** キーを押します。
4. 結果から **[Key Vault]** を選択します。
5. **［作成］** を選択します
6. 新しい Key Vault の **[名前]** を入力します。

    [Image: Azure Key Vault の作成画面を示すスクリーンショット。]
7. このチュートリアルで使用する仮想マシンを作成したサブスクリプションとリソース グループを必ず選択して、必要なすべての情報を入力します。
8. **[確認および作成]** を選択し、**[作成]** を選択します。

### シークレットを作成します

次に、Key Vault にシークレットを追加し、VM で実行されているコードを使用して後で取得できるようにする必要があります。 このセクションでは、PowerShell を使用します。 ただし、この仮想マシンで実行されるコードに同じ概念が適用されます。

1. 新しく作成した Key Vault に移動します。
2. **[シークレット]** を選択してから、**[追加]** を選択します。
3. **[Generate/Import](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/生成/インポート)** を選択します。
4. **[シークレットを作成します]** セクションで、**[アップロード オプション]** に移動し、**[手動]** が選択されていることを確認します。
5. シークレットの名前と値を指定します。 値は任意のものを指定できます。
6. アクティブ化の日付と有効期限はオフのままにし、**[有効]** が **[はい]** に設定されていることを確認します。
7. **[作成]** を選択して、シークレットを作成します。

    [Image: シークレットの作成を示すスクリーンショット。]

### アクセス権の付与

仮想マシンで使われるマネージド ID には、Key Vault に格納されているシークレットを読み取るアクセス権が必要です。

1. 新しく作成した Key Vault に移動します。
2. 左側のナビゲーションから **[アクセス ポリシー]** を選択します。
3. **[アクセス ポリシーの追加]** を選択します。

    [Image: キー コンテナーのアクセス ポリシーの作成画面のスクリーンショット。]
4. **[アクセス ポリシーの追加]** セクションで、**[テンプレートからの構成 (省略可能)]** のドロップダウン メニューから **[シークレットの管理]** を選択します。
5. **[プリンシパルの選択]** を選択し、以前に作成した VM の名前を検索フィールドに入力します。 結果一覧で VM を選択し、**[選択]** を選択します。
6. **[追加]** を選択します。
7. **[保存]** を選択します。

### データにアクセスする

これらの手順を完了するには、SSH クライアントが必要です。 Windows を使用している場合は、[Windows Subsystem for Linux](https://learn.microsoft.com/ja-jp/windows/wsl/about) で SSH クライアントを使用することができます。 SSH クライアント キーの構成について支援が必要な場合は、「[Azure 上の Windows で SSH キーを使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)」または「[Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)」をご覧ください。

重要

目標のサービスにアクセスするための Microsoft Entra トークンを容易に取得できる Azure.Identity ライブラリは、すべての Azure SDK でサポートされます。 [Azure SDK](https://azure.microsoft.com/downloads/) の詳細を確認し、Azure.Identity ライブラリにアクセスしてください。

- [。網](https://learn.microsoft.com/ja-jp/dotnet/api/overview/azure/identity-readme)
- [ジャワ](https://learn.microsoft.com/ja-jp/java/api/overview/azure/identity-readme)
- [JavaScript](https://learn.microsoft.com/ja-jp/javascript/api/overview/azure/identity-readme)
- [ニシキヘビ](https://learn.microsoft.com/ja-jp/python/api/overview/azure/identity-readme)

1. ポータルで Linux VM に移動し、**[概要]** の **[接続]** を選択します。
2. 任意の SSH クライアントを使用して、VM に**接続**します。
3. ターミナル ウィンドウで、cURL を使用して、Azure リソース エンドポイントのローカル マネージド ID に、Azure Key Vault のアクセス トークンを取得するよう要求します。 アクセス トークンの CURL 要求を次に示します。

```bash
curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fvault.azure.net' -H Metadata:true
  ```
The response includes the access token you need to access Resource Manager.

Response:

```bash
{"access_token":"eyJ0eXAi...",
"refresh_token":"",
"expires_in":"3599",
"expires_on":"1504130527",
"not_before":"1504126627",
"resource":"https://vault.azure.net",
"token_type":"Bearer"} 
```

このアクセス トークンを使用して Azure Key Vault に認証することができます。 次の CURL 要求は、CURL と Key Vault REST API を使用して Key Vault からシークレットを読み取る方法を示しています。 Key Vault の URL が必要です。これは、Key Vault の **[概要]** ページの **[Essentials]** セクションにあります。 前の呼び出しで取得したアクセス トークンも必要になります。

```bash
curl 'https://<YOUR-KEY-VAULT-URL>/secrets/<secret-name>?api-version=2016-10-01' -H "Authorization: Bearer <ACCESS TOKEN>" 
```

応答は次のようになります。

```bash
{"value":"p@ssw0rd!","id":"https://mytestkeyvault.vault.azure.net/secrets/MyTestSecret/7c2204c6093c4d859bc5b9eff8f29050","attributes":{"enabled":true,"created":1505088747,"updated":1505088747,"recoveryLevel":"Purgeable"}} 
```

Key Vault からシークレットを取得した後は、名前とパスワードを必要とするサービスへの認証にそのシークレットを使用できます。

### リソースをクリーンアップする

リソースをクリーンアップする準備ができたら、[Azure portal](https://portal.azure.com) にサインインし、**[リソース グループ]** を選択し、このチュートリアルのプロセスで作成されたリソース グループ (`mi-test` など) を見つけて選択します。 **[リソース グループの削除]** コマンドを使用するか、[PowerShell または CLI](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/delete-resource-group) を使用できます。

::: zone-end

::: zone pivot="identity-linux-mi-vm-access-arm"

### Linux VM のシステム割り当てマネージド ID を使用して、リソース マネージャーのリソース グループにアクセスする

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

::: zone pivot="identity-linux-mi-vm-user-arm"

### Linux VM のユーザー割り当てマネージド ID を使用して Resource Manager のリソース グループにアクセスする

このチュートリアルでは、ユーザー割り当て ID を作成して Linux 仮想マシン (VM) に割り当て、その ID を使用して [Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) API にアクセスする方法について説明します。 管理対象サービス ID は Azure によって自動的に管理されます。 管理対象サービス ID を使用すると、コード内に資格情報を埋め込む必要なく、Microsoft Entra の認証をサポートするサービスに認証することができます。

学習内容は次のとおりです。

- VM に Azure Resource Manager へのアクセスを許可します。
- VM のシステム割り当てマネージド ID を使って、Resource Manager にアクセスするためのアクセス トークンを取得します。

[az identity create](https://learn.microsoft.com/ja-jp/cli/azure/identity#az-identity-create) を使用して、ユーザー割り当てマネージド ID を作成します。 `-g` パラメーターにはユーザー割り当てマネージド ID を作成するリソース グループを指定し、`-n` パラメーターにはその名前を指定します。 `<RESOURCE GROUP>` と `<UAMI NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。

重要

ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

```azurecli
az identity create -g <RESOURCE GROUP> -n <UAMI NAME>
```

応答には、次の例のように、作成されたユーザー割り当てマネージド ID の詳細が含まれています。 次の手順でユーザー割り当てマネージド ID に `id` の値を使用するため、この値をメモしておきます。

```json
{
"clientId": "00001111-aaaa-2222-bbbb-3333cccc4444",
"clientSecretUrl": "https://control-westcentralus.identity.azure.net/subscriptions/<SUBSCRIPTON ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<UAMI NAME>/credentials?tid=5678&oid=9012&aid=aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
"id": "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<UAMI NAME>",
"location": "westcentralus",
"name": "<UAMI NAME>",
"principalId": "9012",
"resourceGroup": "<RESOURCE GROUP>",
"tags": {},
"tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
"type": "Microsoft.ManagedIdentity/userAssignedIdentities"
}
```

### Linux VM に ID を割り当てる

ユーザー割り当てマネージド ID は、複数の Azure リソース上のクライアントで使用できます。 単一の VM にユーザー割り当てマネージド ID を割り当てるには、次のコマンドを使用します。 `Id` パラメーターには、前の手順で返された `-IdentityID` プロパティを使用します。

[az vm assign-identity](https://learn.microsoft.com/ja-jp/cli/azure/vm) を使用して、ユーザー割り当てマネージド ID を Linux VM に割り当てます。 `<RESOURCE GROUP>` と `<VM NAME>` のパラメーターの値は、必ず実際の値に置き換えてください。 `id` パラメーターの値には、前の手順で返された `--identities` プロパティを使用します。

```azurecli
az vm identity assign -g <RESOURCE GROUP> -n <VM NAME> --identities "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<UAMI NAME>"
```

### Azure Resource Manager でリソース グループへのアクセスを許可する

マネージド ID は、Microsoft Entra 認証をサポートするリソース API を認証するアクセス トークンを要求するためにコードで使用できる ID です。 このチュートリアルでは、コードは Azure Resource Manager API にアクセスします。

コードで API にアクセスできるようにするには、事前に ID に Azure Resource Manager のリソースへのアクセスを許可する必要があります。 このケースでは、VM が含まれているリソース グループです。 使用する環境に合わせて、`<SUBSCRIPTION ID>` および `<RESOURCE GROUP>` の値を更新します。 さらに、`<UAMI PRINCIPALID>` を、「`principalId`」の `az identity create` コマンドによって返された  プロパティで置き換えます。

```azurecli
az role assignment create --assignee <UAMI PRINCIPALID> --role 'Reader' --scope "/subscriptions/<SUBSCRIPTION ID>/resourcegroups/<RESOURCE GROUP> "
```

応答には、次の例のように、作成されたロールの割り当ての詳細が含まれています。

```json
{
  "id": "/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>/providers/Microsoft.Authorization/roleAssignments/00000000-0000-0000-0000-000000000000",
  "name": "00000000-0000-0000-0000-000000000000",
  "properties": {
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "roleDefinitionId": "/subscriptions/<SUBSCRIPTION ID>/providers/Microsoft.Authorization/roleDefinitions/00000000-0000-0000-0000-000000000000",
    "scope": "/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>"
  },
  "resourceGroup": "<RESOURCE GROUP>",
  "type": "Microsoft.Authorization/roleAssignments"
}

```

### VM の ID を使用してアクセス トークンを取得し、このトークンを使用して Resource Manager を呼び出す

チュートリアルの残りの部分では、以前に作成した VM から作業を行います。

これらの手順を完了するには、SSH クライアントが必要です。 Windows を使用している場合は、[Windows Subsystem for Linux](https://learn.microsoft.com/ja-jp/windows/wsl/about) で SSH クライアントを使用することができます。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. Portal で **[仮想マシン]** に移動し、Linux 仮想マシンに移動して、**[概要]** の **[接続]** をクリックします。 VM に接続する文字列をコピーします。
3. 任意の SSH クライアントを使用して、VM に接続します。 Windows を使用している場合は、[Windows Subsystem for Linux](https://learn.microsoft.com/ja-jp/windows/wsl/about) で SSH クライアントを使用することができます。 SSH クライアント キーの構成について支援が必要な場合は、「[Azure 上の Windows で SSH キーを使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)」または「[Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)」をご覧ください。
4. ターミナル ウィンドウで、CURL を使用して、Azure Instance Metadata Service (IMDS) の ID エンドポイントに対して Azure Resource Manager のアクセス トークンを取得するよう要求します。

    アクセス トークンを取得するための CURL 要求を次の例に示します。 `<CLIENT ID>` を、「`clientId`」の `az identity create` コマンドによって返された  プロパティで置き換えてください。

    ```bash
    curl -H Metadata:true "http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fmanagement.azure.com/&client_id=<UAMI CLIENT ID>"
    ```

    注

    `resource` パラメーターの値は、Microsoft Entra ID で想定されているものと完全に一致している必要があります。 Resource Manager のリソース ID を使用する場合は、URI の末尾にスラッシュを含める必要があります。

    応答には、Azure Resource Manager へのアクセスに必要なアクセス トークンが含まれています。

    応答の例:

    ```bash
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
5. このアクセス トークンを使用して Azure Resource Manager にアクセスし、以前にユーザー割り当てマネージド ID にアクセスを許可したリソース グループのプロパティを読み取ります。 `<SUBSCRIPTION ID>` と `<RESOURCE GROUP>` は、以前に指定した値で必ず置き換えてください。`<ACCESS TOKEN>` は、前の手順で返されたトークンで置き換えてください。

    注

    URL では大文字小文字が区別されるため、リソース グループの命名時に以前使用したものと同じ大文字と小文字の使い分けが使用されていること、および `resourceGroups` の "G" が大文字であることを確認してください。

    ```bash
    curl https://management.azure.com/subscriptions/<SUBSCRIPTION ID>/resourceGroups/<RESOURCE GROUP>?api-version=2016-09-01 -H "Authorization: Bearer <ACCESS TOKEN>" 
    ```

    応答には、次の例のように、特定のリソース グループの情報が含まれています。

    ```bash
    {
    "id":"/subscriptions/<SUBSCRIPTION ID>/resourceGroups/DevTest",
    "name":"DevTest",
    "location":"westus",
    "properties":{"provisioningState":"Succeeded"}
    } 
    ```

::: zone-end

### 詳細情報

- [Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)
- [クイックスタート: VM でユーザー割り当てマネージド ID を使用して Azure Resource Manager にアクセスする](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/tutorial-linux-managed-identities-vm-access)
- [Azure PowerShell を使用してユーザー割り当てマネージド ID を作成、一覧表示、削除する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-powershell)
<!-- /MSL-PAGE -->
