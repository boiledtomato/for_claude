# Microsoft Learn — Microsoft Entra / エンタープライズアプリ・プロビジョニング・アプリプロキシ (part 6)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 23

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/tenant-restrictions"} -->
## テナント制限を使用して SaaS アプリへのアクセスを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tenant-restrictions
- Service: entra-id / enterprise-apps
- Article date: 2024-11-29
- Summary: 自社の Microsoft Entra テナントに基づいて、テナント制限を使用しアプリにアクセスできるユーザーを管理する方法。

セキュリティを重視する大規模な組織は、Microsoft 365 などのクラウド サービスへの移行を望んでいますが、ユーザーが承認済みリソースにしかアクセスできないことを認識しておく必要があります。 従来より、企業ではアクセスを管理するときにドメイン名や IP アドレスを制限しています。 このアプローチは、パブリック クラウドでホストされ、`outlook.office.com` や `login.microsoftonline.com` などのドメイン名で実行される、サービスとしてのソフトウェア (SaaS) アプリ の場合はうまくいきません。 これらのアドレスをブロックすると、ユーザーを承認済みの ID やリソースに単に制限するのではなく、ユーザーは Web 上の Outlook にまったくアクセスできなくなります。

この課題を解決する Microsoft Entra ソリューションが、テナント制限と呼ばれる機能です。 テナントの制限により、組織は、アプリケーションが [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)に使用する Microsoft Entra テナントに基づいて、SaaS クラウド アプリケーションへのアクセスを制御できます。 たとえば、自分の組織の Microsoft 365 アプリケーションへのアクセスは許可し、これらの同じアプリケーションの他の組織のインスタンスにはアクセスできないようにすることが可能です。

テナント制限では、組織はネットワーク上のユーザーがアクセスを許可されているテナントの一覧を指定できます。 その後、Microsoft Entra ID は、これらの許可されたテナントへのアクセスのみを許可します。他のテナントは、組織のユーザーがゲストである可能性があるものも含めて、すべてブロックされます。

この記事では Microsoft 365 のテナント制限に重点を置いて取り上げますが、この機能は、シングル サインオンのためにユーザーを Microsoft Entra ID に送信するすべてのアプリを保護するものです。 Microsoft 365 で使用されるテナントとは異なる Microsoft Entra テナントで SaaS アプリを使用する場合は、必要なすべてのテナントのアクセスが許可されていることをご確認ください。 （たとえば、B2B コラボレーションのシナリオの場合）。 SaaS クラウド アプリの詳細については、 [Active Directory Marketplace](https://azuremarketplace.microsoft.com/marketplace/apps) を参照してください。

テナント制限機能では、OneDrive、Hotmail、Xbox.com など、 すべての Microsoft コンシューマー アプリケーション (MSA アプリ) の使用をブロックすることもできます。 この機能は、`login.live.com` エンドポイントに対して別のヘッダーを使用します。これについては、当アーティクルの最後で詳細に説明します。

### しくみ

全体的なソリューションは、次のコンポーネントで構成されます。

1. **Microsoft Entra ID**: `Restrict-Access-To-Tenants: <permitted tenant list>` ヘッダーが存在する場合、Microsoft Entra 専用は許可されたテナントのセキュリティ トークンを発行します。
2. **オンプレミスのプロキシ サーバー インフラストラクチャ**: このインフラストラクチャは、トランスポート層セキュリティ (TLS) 検査が可能なプロキシ デバイスです。 このプロキシは、許可されているテナントのリストを含むヘッダーを Microsoft Entra ID 宛てのトラフィックに挿入するように構成する必要があります。
3. **クライアント ソフトウェア**: テナントの制限をサポートするには、プロキシ インフラストラクチャがトラフィックをインターセプトできるように、クライアント ソフトウェアが Microsoft Entra ID から直接トークンを要求する必要があります。 先進認証 (OAuth 2.0 など) を使用する Office クライアントと同様に、ブラウザー ベースの Microsoft 365 アプリケーションは現在、テナント制限をサポートしています。
4. **先進認証**: クラウド サービスでは、テナントの制限を使用し、許可されていないすべてのテナントへのアクセスをブロックするために、先進認証を使用する必要があります。 既定で先進認証プロトコルを使用するように Microsoft 365 クラウド サービスを構成する必要があります。 先進認証の Microsoft 365 サポートに関する最新情報については、「 [更新された Office 365 先進認証](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/modern-auth-for-office-2013-and-2016)」を参照してください。

次の図は、おおまかなトラフィック フローを示しています。 テナント制限では、TLS インスペクションは Microsoft 365 クラウド サービスへのトラフィックではなく、Microsoft Entra ID へのトラフィックでのみ必要です。 Microsoft Entra ID への認証のためのトラフィック量は一般に、Exchange Online や SharePoint Online などの SaaS アプリケーションへのトラフィック量よりはるかに少ないため、この特質は重要です。

[Image: テナント制限のトラフィック フローの図。]

### テナント制限を設定する

テナント制限の使用を開始するための手順は 2 つあります。 最初に、クライアントが適切なアドレスに接続できることを確認します。 2 つ目に、プロキシ インフラストラクチャを構成します。

#### URL と IP アドレス

テナント制限を使用するには、認証のために、クライアントは次の Microsoft Entra URL に接続できる必要があります。

- login.microsoftonline.com
- login.microsoft.com
- login.windows.net

さらに、Office 365 にアクセスするには、クライアントは Office 365 の URL と IP アドレス範囲で定義されている完全修飾ドメイン名 (FQDN)、URL、 [および IP アドレス](https://support.office.com/article/Office-365-URLs-and-IP-address-ranges-8548a211-3fe7-47cb-abb1-355ea5aa88a2)にも接続できる必要があります。

#### プロキシの構成と要件

プロキシ インフラストラクチャを使用したテナント制限を有効にするには、次の構成が必要です。 このガイダンスは一般的なものであるため、具体的な実装手順については、プロキシ ベンダーのドキュメントを参照してください。

##### 前提条件

- プロキシは、TLS インターセプト、HTTP ヘッダーの挿入、FQDN/URL を使用した送信先のフィルター処理を実行できる必要があります。
- クライアントは、TLS 通信でプロキシによって提示される証明書チェーンを信頼する必要があります。 たとえば、内部公開キー インフラストラクチャ (PKI) からの証明書が使用されている場合は、内部発行のルート証明機関証明書を信頼する必要があります。
- テナント制限の使用には、Microsoft Entra ID の P1 または P2 の ライセンスが必要です。

##### 構成

`login.microsoftonline.com`、`login.microsoft.com`、`login.windows.net`への各送信要求に対して、2 つの HTTP ヘッダー (*Restrict-Access-To-Tenants* と *Restrict-Access-Context*) を挿入します。

注

`*.login.microsoftonline.com` の下のサブドメインをプロキシの構成に含めないでください。 すると、`device.login.microsoftonline.com` が含まれ、デバイス登録とデバイスベースの条件付きアクセスで使用されるクライアント証明書の認証が妨げられます。 `device.login.microsoftonline.com` と `enterpriseregistration.windows.net` を TLS の中断と検査、およびヘッダーの挿入から除外するようにプロキシ サーバーを構成します。

これらのヘッダーには、次の要素を含める必要があります。

- *Restrict-Access-To-Tenants* の場合は、&lt;許可されたテナント リスト&gt;の値を使用します。これは、ユーザーがアクセスできるようにするテナントのコンマ区切りの一覧です。 テナントに登録されているドメインを使用して、このリストのテナントとディレクトリ ID 自体を識別できます。 コンマ区切りリストには空白を含めないでください。 テナントを記述する 3 つのすべての方法の例として、Contoso、Fabrikam、および Microsoft を許可する名前と値のペアは、`Restrict-Access-To-Tenants: contoso.com,fabrikam.onmicrosoft.com,aaaabbbb-0000-cccc-1111-dddd2222eeee` のようになります。
- *Restrict-Access-Context の*場合は、1 つのディレクトリ ID の値を使用して、どのテナントがテナント制限を設定するかを宣言します。 たとえば、テナント制限ポリシーを設定するテナントとして Contoso を宣言するには、名前と値のペアは `Restrict-Access-Context: bbbbcccc-1111-dddd-2222-eeee3333ffff` のようになります。 これらの認証のログを取得するには、ここで独自のディレクトリ ID を使用する *必要があります* 。 独自のディレクトリ ID 以外の ID を使用する場合、サインイン ログは他のユーザーのテナントに表示 "され"、すべての個人情報が削除されます。 詳細については、「 管理者エクスペリエンス」を参照してください。

ディレクトリ ID を見つけるには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グローバル 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)としてサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。
3. **テナント ID** の値をコピーします。

ディレクトリ ID またはドメイン名が同じテナントを参照していることを検証するには、URL &lt; で &gt;tenant`https://login.microsoftonline.com/<tenant>/v2.0/.well-known/openid-configuration` の代わりにその ID またはドメインを使用します。 ドメインと ID の結果が同じであれば、同じテナントを参照しています。

ユーザーが承認されていないテナントで独自の HTTP ヘッダーを挿入できないようにするには、受信要求に既に存在する場合は、プロキシで *Restrict-Access-To-Tenants* ヘッダーを置き換える必要があります。

`login.microsoftonline.com`、`login.microsoft.com`、`login.windows.net` へのすべての要求にプロキシを使用するよう、クライアントに強制する必要があります。 たとえば、クライアントにプロキシの使用を指示するために PAC ファイルが使用されている場合は、エンド ユーザーがその PAC ファイルを編集したり無効にしたりできないようにする必要があります。

### ユーザー エクスペリエンス

このセクションでは、エンド ユーザーと管理者の両方のエクスペリエンスについて説明します。

#### エンド ユーザー エクスペリエンス

たとえば、Contoso ネットワーク上のユーザーが、Outlook Online などの共有 SaaS アプリケーションの Fabrikam インスタンスにアクセスしようとしているとします。 Fabrikam が Contoso インスタンスに対して許可されていないテナントである場合、ユーザーにはアクセス拒否メッセージが表示されます。 拒否メッセージには、IT 部門によって承認されていない組織に属するリソースにアクセスしようとしていることが表示されています。

[Image: 2021 年 4 月からのテナント制限エラー メッセージのスクリーンショット。]

#### 管理者エクスペリエンス

テナント制限の構成は企業プロキシ インフラストラクチャで行われますが、管理者は、Microsoft Entra 管理センターで直接テナント制限レポートにアクセスできます。 レポートを表示するには、次の操作を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グローバル 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)としてサインインします。
2. **Entra ID**&gt;**概要**&gt;**テナントの制限**を確認します。

Restricted-Access-Context テナントとして指定されたテナントの管理者は、このレポートを使用して、テナント制限ポリシーのためにブロックされたサインイン (使用された ID やターゲット ディレクトリ ID を含む) を確認できます。 制限を設定するテナントがサインインのユーザー テナントまたはリソース テナントのいずれかである場合は、サインインが含まれます。

Restricted-Access-Context テナント以外のテナントに属するユーザーがサインインする場合、ターゲット ディレクトリ ID などの限られた情報がレポートに含まれる可能性があります。 この場合、名前やユーザー プリンシパル名などのユーザー識別情報はマスクされ、他のテナントのユーザー データは保護されます (例: 必要に応じて、ユーザー名とオブジェクト ID の代わりに `"{PII Removed}@domain.com" or 00000000-0000-0000-0000-000000000000`)。

Microsoft Entra 管理センターの他のレポートと同様に、フィルターを使用してレポートの範囲を指定できます。 特定の時間間隔、ユーザー、アプリケーション、クライアント、または状態についてフィルター処理できます。 [ **列** ] ボタンを選択すると、次のフィールドの任意の組み合わせでデータを表示できます。

- **ユーザー** - このフィールドでは、個人データを削除できます。このフィールドの値は `00000000-0000-0000-0000-000000000000` に設定されます。
- **アプリケーション**
- **地位**
- **日付**
- **日付 (UTC)** - UTC は世界協定時刻です
- **IPアドレス**
- **クライアント**
- **ユーザー名** - このフィールドでは、個人データを削除できます。このフィールドの値は `{PII Removed}@domain.com`
- **場所**
- **ターゲット テナント ID**

### Microsoft 365 サポート

テナント制限を完全にサポートするには、Microsoft 365 アプリケーションは次の 2 つの条件を満たす必要があります。

1. 使用されるクライアントが先進認証をサポートしている。
2. クラウド サービスの既定の認証プロトコルとして最新の認証が有効になっている。

現在先進認証をサポートしている Office クライアントの最新情報については、「 [更新された Office 365 先進認証](https://www.microsoft.com/microsoft-365/blog/2015/03/23/office-2013-modern-authentication-public-preview-announced/)」を参照してください。 このページには、特定の Exchange Online テナントと Skype for Business Online テナントで最新の認証を有効にする手順へのリンクも含まれています。 SharePoint Online では、先進認証が既定で有効になっています。 Teams は先進認証のみをサポートしており、レガシ認証はサポートされないため、このバイパスの問題は Teams には当てはまりません。

Microsoft 365 ブラウザー ベースのアプリケーション (Office ポータル、Yammer、SharePoint サイト、Outlook on the Web など) は現在、テナント制限をサポートしています。 シック クライアント (Outlook、Skype for Business、Word、Excel、PowerPoint など) は、先進認証を使用している場合にのみテナント制限を適用できます。

先進認証をサポートする Outlook と Skype for Business のクライアントは、先進認証が有効ではないテナントに対してレガシ プロトコルを引き続き使用できる場合があるため、テナント制限を実質的に迂回します。 テナント制限では、レガシ プロトコルを使用するアプリケーションが認証中に `login.microsoftonline.com`、`login.microsoft.com`、`login.windows.net` へ接続すると、ブロックされる場合があります。

Windows 上の Outlook の場合、エンド ユーザーが未承認の電子メール アカウントをプロファイルに追加できないようにする制限を実装することもできます。 たとえば、「 [既定以外の Exchange アカウントの追加を禁止する](https://gpsearch.azurewebsites.net/default.aspx?ref=1) 」グループ ポリシー設定を参照してください。

#### Azure RMS と Office メッセージ暗号化の非互換性

[Azure Rights Management サービス (Azure RMS)](https://learn.microsoft.com/ja-jp/azure/information-protection/what-is-azure-rms) と [Office Message Encryption](https://learn.microsoft.com/ja-jp/purview/ome) の機能は、テナントの制限と互換性がありません。 これらの機能は、暗号化されたドキュメントの暗号化解除キーを取得するために、ユーザーを他のテナントにサインインする必要があります。 テナント制限は他のテナントへのアクセスをブロックするため、信頼されていないテナントからユーザーに送信された暗号化メールとドキュメントにはアクセスできません。

### テスト

テナント制限を組織全体で実装する前にテストする必要がある場合は、Fiddler などのツールを使用したホスト ベースのアプローチと、プロキシ設定の段階的なロールアウトの 2 つのオプションがあります。

#### Fiddler を使用したホスト ベースのアプローチ

Fiddler は無料の Web デバッグ プロキシで、HTTP/HTTPS トラフィックをキャプチャして変更できます (HTTP ヘッダーの挿入など)。 Fiddler を構成してテナント制限をテストするには、次の手順を実行します。

1. [Fiddler をダウンロードしてインストール](https://www.telerik.com/fiddler)します。
2. Fiddler のヘルプ ドキュメントに従って、HTTPS トラフィックを復号化するように [Fiddler](https://docs.telerik.com/fiddler/Configure-Fiddler/Tasks/DecryptHTTPS) を構成します。
3. カスタム規則を使用して *Restrict-Access-To-Tenants* ヘッダーと *Restrict-Access-Context* ヘッダーを挿入するように Fiddler を構成します。

    1. Fiddler Web デバッガー ツールで、[ **ルール** ] メニューを選択し、[ **ルールのカスタマイズ...** ] を選択して CustomRules ファイルを開きます。
    2. `OnBeforeRequest` 関数に次の行を追加します。 &lt;List of tenant identifiers&gt; を、テナントに登録されているドメイン (`contoso.onmicrosoft.com` など) に置き換えます。 &lt;directory ID&gt; を、テナントの Microsoft Entra GUID 識別子に置き換えます。 ログをテナントに表示するには、正しい GUID 識別子を含める **必要があります** 。

    ```JScript
     // Allows access to the listed tenants.
       if (
           oSession.HostnameIs("login.microsoftonline.com") ||
           oSession.HostnameIs("login.microsoft.com") ||
           oSession.HostnameIs("login.windows.net")
       )
       {
           oSession.oRequest["Restrict-Access-To-Tenants"] = "<List of tenant identifiers>";
           oSession.oRequest["Restrict-Access-Context"] = "<Your directory ID>";
       }
    
     // Blocks access to consumer apps
       if (
           oSession.HostnameIs("login.live.com")
       )
       {
           oSession.oRequest["sec-Restrict-Tenant-Access-Policy"] = "restrict-msa";
       }
    ```

    複数のテナントを許可する必要がある場合は、テナント名をコンマで区切ります。 次に例を示します。

    `oSession.oRequest["Restrict-Access-To-Tenants"] = "contoso.onmicrosoft.com,fabrikam.onmicrosoft.com";`
4. CustomRules ファイルを保存して閉じます。

Fiddler を構成したら、[ **ファイル** ] メニューに移動し、[トラフィックのキャプチャ] を選択して **トラフィックをキャプチャ**できます。

#### プロキシ設定の段階的なロールアウト

プロキシ インフラストラクチャの機能によっては、設定をユーザーに段階的にロールアウトできる場合があります。 考慮事項については、次の大まかなオプションをご覧ください:

1. PAC ファイルを使用して、テスト ユーザーがテスト用プロキシ インフラストラクチャを使用するようにし、通常のユーザーは運用環境のプロキシ インフラストラクチャを引き続き使用します。
2. 場合によっては、一部のプロキシ サーバーでグループを使用して別の構成をサポートします。

具体的な詳細については、ご使用のプロキシ サーバーのドキュメントを参照してください。

### 顧客のアプリケーションをブロックする

OneDrive などの、コンシューマー アカウントと組織アカウントの両方をサポートする Microsoft のアプリケーションは、同じ URL でホストされる場合があります。 これは、仕事目的でその URL にアクセスする必要があるユーザーが、個人使用でもその URL にアクセスできることを意味します。 このオプションは、運用ガイドラインでは許可されない場合があります。

一部の組織では、個人用アカウントの認証をブロックするために `login.live.com` をブロックして、この問題を解決しようとします。 この修正には、いくつかの欠点があります。

1. `login.live.com` をブロックすると、B2B ゲストシナリオでの個人アカウントの使用がブロックされ、訪問者やコラボレーションに侵入する場合があります。
2. [Autopilot をデプロイするためには、`login.live.com`を使用する必要があります。](https://learn.microsoft.com/ja-jp/autopilot/networking-requirements)`login.live.com` がブロックされると、Intune およびオートパイロットのシナリオは失敗するおそれがあります。
3. デバイス ID の login.live.com サービスに依存する組織のテレメトリと Windows 更新プログラムは [機能しなくなります](https://learn.microsoft.com/ja-jp/troubleshoot/windows-client/deployment/windows-update-issues-troubleshooting#feature-updates-are-not-being-offered-while-other-updates-are)。

#### コンシューマー アプリの構成

`Restrict-Access-To-Tenants` ヘッダーは許可リストとして機能しますが、Microsoft アカウント (MSA) ブロックは拒否シグナルとして機能し、コンシューマー アプリケーションにサインインすることをユーザーに許可しないように Microsoft アカウント プラットフォームに指示します。 この信号を送信するために、`sec-Restrict-Tenant-Access-Policy` ヘッダーは、この記事の「`login.live.com`」セクションで説明したように、同じ企業プロキシまたはファイアウォールを使用して、を訪問するトラフィックに挿入されます。 ヘッダーの値は、`restrict-msa` である必要があります。 ヘッダーが存在し、コンシューマ アプリがユーザーを直接サインインしようとすると、そのサインインはブロックされます。

現時点では、login.live.com は Microsoft Entra ID とは別にホストされているため、コンシューマー アプリケーションに対する認証は 管理者ログに表示されません。

#### ヘッダーでブロックされるものとされないもの

`restrict-msa` ポリシーにより、コンシューマー アプリケーションの使用はブロックされますが、他の一部の種類のトラフィックおよび認証を通じて許可されます。

1. デバイスのユーザーレス トラフィック。 このオプションには、オートパイロット、Windows Update、組織のテレメトリのトラフィックが含まれます。
2. コンシューマー アカウントの B2B 認証。 [テナントとの共同作業に招待された](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#invitation-redemption-flow)Microsoft アカウントを持つユーザーは、リソース テナントにアクセスするために login.live.com に対して認証されます。
    1. そのリソース テナントへのアクセスを許可または拒否するために、このアクセスは `Restrict-Access-To-Tenants` ヘッダーを使用して制御されます。
3. 多くの Azure アプリと Office.com で使用されている 「パススルー」認証では、アプリはコンシューマー コンテキストでのコンシューマー ユーザーのサインインに Microsoft Entra ID を使用します。
    1. 特別な "パススルー" テナントへのアクセスを許可または拒否するために、このアクセスも、`Restrict-Access-To-Tenants` ヘッダーを使用して制御されます (`f8cdef31-a31e-4b4a-93e4-5f571e91255a`)。 このテナントが許可されたドメインの `Restrict-Access-To-Tenants` リストに掲載されていない場合、Microsoft Entra ID は一般ユーザーのアカウントによるこれらのアプリへのサインインをブロックします。

### TLS の中断と検査をサポートしていないプラットフォーム

テナントの制限は、HTTPS ヘッダーで許可されているテナントの一覧を挿入するかどうかによって異なります。 この依存関係では、トラフィックを中断して検査するためにトランスポート層セキュリティ検査 (TLSI) が必要です。 クライアント側でトラフィックの中断と検査、ヘッダーの追加ができない環境では、テナント制限は機能しません。

Android 7.0 以降の例を見てみましょう。 Android では、セキュリティで保護されたアプリ トラフィックでより安全な既定値を提供するため、信頼された証明機関 (CA) の処理方法が変更されました。 詳細については、「 [Android Nougat の信頼された証明機関への変更](https://android-developers.googleblog.com/2016/07/changes-to-trusted-certificate.html)」を参照してください。

Microsoft クライアント アプリは、Google からの推奨事項に従って、既定でユーザー証明書を無視します。 このポリシーにより、ネットワーク プロキシによって使用される証明書がユーザー証明書ストアにインストールされ、クライアント アプリが信頼しないため、このようなアプリはテナント制限で動作できなくなります。

テナント制限パラメーターをヘッダーに追加するために、トラフィックを中断して検査することができないような環境では、Microsoft Entra ID は他の機能による保護を提供できます。 そのような Microsoft Entra 機能の詳細情報については、以下のリストを参照してください。

- [条件付きアクセス: マネージド/準拠デバイスの使用のみを許可する](https://learn.microsoft.com/ja-jp/mem/intune/protect/conditional-access-intune-common-ways-use#device-based-conditional-access)
- [条件付きアクセス: ゲスト/外部ユーザーのアクセスを管理する](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/identity-access-policies-guest-access)
- [B2B コラボレーション: パラメーター "Restrict-Access-To-Tenants" に記載されているのと同じテナントに対して、クロステナントアクセスによる送信ルールを制限する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)
- [B2B コラボレーション: "Restrict-Access-To-Tenants" パラメーターに記載されているのと同じドメインに B2B ユーザーへの招待を制限する](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)
- [アプリケーション管理: ユーザーがアプリケーションに同意する方法を制限する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)
- [Intune: Intune を介してアプリ ポリシーを適用して、管理対象アプリの使用を、デバイスを登録したアカウントの UPN のみに制限](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-configuration-policies-use-android) します。[アプリの小見出し **で構成された組織アカウントのみを許可する]** セクションを確認します。

ただし、一部の特定のシナリオでは、テナント制限を使用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/troubleshoot-app-publishing"} -->
## サインインがブロックされる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/troubleshoot-app-publishing
- Service: entra-id / enterprise-apps
- Article date: 2025-04-29
- Summary: Microsoft アプリケーション ネットワーク ポータルへのサインインがブロックされる問題のトラブルシューティングを行います。

この記事では、Microsoft アプリケーション ネットワーク ポータルへのサインインがブロックされる問題を解決するための情報を提供します。

### 現象

Microsoft アプリケーション ネットワーク ポータルにサインインしようとすると、ユーザーにこのメッセージが表示されます。

[Image: ポータルへのサインインがブロックされていることを示すスクリーンショット。]

### 原因

ゲスト ユーザーは、Microsoft Entra テナントでもあるホーム テナントにフェデレーションされます。 ゲスト ユーザーは高リスクです。 高リスクのユーザーは、リソースへのアクセスを許可されません。 高リスクのユーザー (従業員、ゲスト、ベンダー) はすべて、リソースにアクセスするためには自分のリスクを修正する必要があります。 ゲスト ユーザーの場合、リスクはホーム テナントに由来しており、ポリシーはリソース テナントから取得されます。

### 解決策

- MFA に登録されたゲスト ユーザーは、自分のユーザー リスクを修復します。 ゲスト ユーザーは、ホーム テナントで [セキュリティで保護されたパスワードをリセットまたは変更](https://aka.ms/sspr) します (これには、ホーム テナントで MFA とセルフサービス パスワード リセット (SSPR) が必要です)。 セキュリティで保護されたパスワードの変更やリセットは、オンプレミスではなく Microsoft Entra ID で開始する必要があります。
- ゲスト ユーザーは、自分の管理者にリスクを修正してもらいます。 この場合、管理者はパスワードをリセットします (一時的なパスワードの生成)。 ゲスト ユーザーの管理者は、https://aka.ms/RiskyUsers にアクセスして、**[パスワードのリセット]** を選択できます。
- ゲスト ユーザーは、自分の管理者にリスクを無視してもらいます。 管理者は https://aka.ms/RiskyUsers にアクセスして、**[ユーザー リスクを無視する]** を選択できます。 ただし管理者は、ユーザー リスクを無視する前にデュー デリジェンスを行い、リスク評価は偽陽性であったことを確認する必要があります。 そのようにしないと、調査なしでリスク評価を無視することになり、リソースがリスクにさらされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/troubleshoot-password-based-sso"} -->
## パスワードベースのシングル サインオンのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/troubleshoot-password-based-sso
- Service: entra-id / enterprise-apps
- Article date: 2025-09-15
- Summary: パスワード ベースのシングル サインオン用に構成された Microsoft Entra アプリに関する問題のトラブルシューティングを行います。

マイ アプリでパスワードベースのシングルサインオン (SSO) を使用するには、ブラウザー拡張機能をインストールする必要があります。 パスワード ベースの SSO 用に構成されたアプリを選択すると、拡張機能が自動的にダウンロードされます。 エンドユーザーの観点からマイ アプリを使用する方法については、 [マイ アプリ ポータルのヘルプ](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)を参照してください。

### マイ アプリのブラウザー拡張機能がインストールされていない

ブラウザー拡張機能がインストールされていることを確認します。 詳細については、「 [Microsoft Entra My Apps の展開を計画](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview)する」を参照してください。

### シングル サインオンが構成されていない

パスワードベースのシングル サインオンが構成されていることを確認します。 詳細については、「 [パスワード ベースのシングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications)」を参照してください。

### ユーザーが割り当てられていない

ユーザーがアプリに割り当てられていることを確認します。 詳細については、「 [アプリにユーザーまたはグループを割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)参照してください。

### 資格情報は入力されますが、拡張機能では送信されません

この問題は通常、アプリケーション ベンダーがサインイン ページを最近変更して、フィールドを追加したり、ユーザー名とパスワードのフィールドを検出するために使用されていた識別子を変更したり、アプリケーションのサインイン エクスペリエンスのしくみを変更したりした場合に発生します。 多くの場合は、Microsoft がアプリケーション ベンダーと協力して、これらの問題を迅速に解決することができます。

Microsoft には、統合の破綻を自動的に検出するテクノロジがありますが、このような問題をすぐに発見できなかったり、修正に時間がかかったりする場合があります。 これらの統合のいずれかが正しく機能しない場合は、サポート ケースを開いて、できるだけ早く修正できるようにします。

**このアプリケーションのベンダーと連絡を取っている場合は、**彼らを紹介して、Microsoft が Microsoft Entra ID とアプリケーションをネイティブに統合できるようにしてください。 連係を開始するために、「[Microsoft Entra アプリケーション ギャラリーでのアプリケーションの表示](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)」をベンダーに参照してもらいます。

### 資格情報を入力して送信したが、ページには資格情報が正しくないと表示される

この問題を解決するには、まず次のことを試してください。

- ユーザーに最初に、保存されている資格情報を使用 **してアプリケーション Web サイトに直接サインイン** するようにします。

    - サインインが機能する場合は、[**マイ アプリ**] の [**アプリ**] セクションの **[アプリケーション] タイル**の [[資格情報の更新](https://myapps.microsoft.com/)] ボタンを選択して、最新の既知の作業ユーザー名とパスワードに更新させます。
    - 自分または別の管理者がこのユーザーの資格情報を割り当てた場合は、アプリケーションの [ **ユーザーとグループ** ] タブに移動し、割り当てを選択して **[資格情報の更新** ] ボタンをクリックして、ユーザーまたはグループのアプリケーション割り当てを見つけます。
- ユーザーが自分の資格情報を割り当てた場合は、アプリケーション **でパスワードの有効期限が切れていないことを確認** し、期限切れの場合は、アプリケーションに直接サインインして **期限切れのパスワードを更新** します。

    - アプリケーションでパスワードが更新されたら、**マイ** アプリの [**アプリ**] セクションの **[アプリケーション] タイル**の [\[資格情報の更新](https://myapps.microsoft.com/)] ボタンを選択して、最新の既知の作業ユーザー名とパスワードに更新するようにユーザーに要求します。
    - 自分または別の管理者がこのユーザーの資格情報を割り当てた場合は、アプリケーションの [ **ユーザーとグループ** ] タブに移動し、割り当てを選択して **[資格情報の更新** ] ボタンをクリックして、ユーザーまたはグループのアプリケーション割り当てを見つけます。
- ユーザーのブラウザーで、マイ アプリのブラウザー拡張機能が実行され、有効になっていることを確認します。
- シークレット **モード、inPrivate モード、プライベート モード**の間に、ユーザーがマイ アプリからアプリケーションにサインインしようとしていないことを確認します。 マイ アプリの拡張機能は、これらのモードではサポートされていません。

上記の提案が機能しない場合は、アプリケーション側で変更が発生し、アプリケーションと Microsoft Entra ID の統合が一時的に壊れている可能性があります。 たとえば、これは、アプリケーション ベンダーがページにスクリプトを導入した場合に発生する可能性があります。手動入力と自動入力では動作が異なります。これにより、独自の統合と同様に、自動化された統合が中断されます。 多くの場合は、Microsoft がアプリケーション ベンダーと協力して、これらの問題を迅速に解決することができます。

Microsoft には、アプリケーションの統合の破綻を自動的に検出するテクノロジがありますが、このような問題をすぐに発見できなかったり、修正に時間がかかったりする場合があります。 統合が正しく機能しない場合は、サポート ケースを開いて、できるだけ早く修正することができます。

これに加えて、**このアプリケーションのベンダーと連絡を取っている場合は、**Microsoft Entra ID とアプリケーションをネイティブに統合するために協力できるように、Microsoft に紹介してください。 連係を開始するために、「[Microsoft Entra アプリケーション ギャラリーでのアプリケーションの表示](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)」をベンダーに参照してもらいます。

### アプリケーションのサインイン ページが最近変更されたか、追加のフィールドが必要かどうかを確認する

アプリケーションのサインイン ページが大幅に変更された場合、統合が中断することがあります。 たとえば、アプリケーション ベンダーがサインイン フィールド、キャプチャ、または多要素認証をエクスペリエンスに追加する場合です。 多くの場合は、Microsoft がアプリケーション ベンダーと協力して、これらの問題を迅速に解決することができます。

Microsoft には、アプリケーションの統合の破綻を自動的に検出するテクノロジがありますが、このような問題をすぐに発見できなかったり、修正に時間がかかったりする場合があります。 統合が正しく機能しない場合は、サポート ケースを開いて、できるだけ早く修正することができます。

これに加えて、**このアプリケーションのベンダーと連絡を取っている場合は、**Microsoft Entra ID とアプリケーションをネイティブに統合するために協力できるように、Microsoft に紹介してください。 連係を開始するために、「[Microsoft Entra アプリケーション ギャラリーでのアプリケーションの表示](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)」をベンダーに参照してもらいます。

### アプリのサインイン フィールドをキャプチャする

サインイン フィールドのキャプチャは、HTML 対応のサインイン ページでのみサポートされます。 Adobe Flash やその他の HTML 非対応テクノロジを使用するページなどの非標準サインイン ページではサポートされていません。 次のセクションでは、カスタム アプリのサインイン フィールドをキャプチャする方法を示します。

#### アプリのサインイン フィールドをキャプチャする

サインイン フィールドをキャプチャするには、My Apps ブラウザー拡張機能がインストールされている必要があります。 また、ブラウザーを *inPrivate*、 *incognito*、または *プライベート* モードで実行することはできません。

アプリのパスワードベースの SSO を構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**すべてのアプリ**を参照します。
3. SSO を構成するアプリを選択します。
4. アプリが読み込まれたら、左側のナビゲーション ウィンドウで [ **シングル サインオン** ] を選択します。
5. **[パスワード ベースのサインオン モード] を**選択します。
6. ユーザーがサインインするユーザー名とパスワードを入力するページである **サインオン URL を**入力します。 指定*した URL のページにサインイン フィールドが表示されていることを確認*します。
7. **「*&lt;appname&gt;* のパスワードシングルサインオン設定を構成する」を選択します**。
8. [ **サインイン フィールドのキャプチャ**] を選択します。
9. [ **OK] を選択します**。
10. **[保存] を選択します**。
11. 手順に従って、マイ アプリを使用します。

### 問題のトラブルシューティング

#### "シングル サインオンの構成を保存できません" というエラーが表示される

まれに SSO 構成の更新に失敗することがあります。 この問題を解決するには、構成をもう一度保存してみます。

引き続きエラーが表示される場合は、サポート ケースを開きます。 この記事の「ポータル通知の詳細を表示する」と「支援を受けるためにサポート エンジニアに通知の詳細を送信する」セクション内で説明されている情報を含めてください。

#### アプリのサインイン フィールドを検出できない

検出が機能しない場合、次の動作が観察されることがあります。

- キャプチャ プロセスは機能しているように見えましたが、キャプチャされたフィールドは正しくありません。
- キャプチャ プロセスを実行しているときに、正しいフィールドが強調表示されない。
- キャプチャ プロセスにより、期待どおりにアプリのサインイン ページに移動するが、何も起こらない。
- 検出は行われますが、ユーザーがマイ アプリからアプリに移動しても SSO は発生しません。

これらの問題のいずれかが発生した場合は、次のことを行います。

- My Apps ブラウザー拡張機能の最新バージョンが *インストールされ、有効*になっていることを確認します。
- キャプチャ プロセス中にブラウザーが *シークレット* モード、 *inPrivate* モード、または *プライベート* モードになっていないことを確認します。 マイ アプリの拡張機能は、これらのモードではサポートされていません。
- ユーザーがシークレット モード、*プライベート モード*、または*inPrivate モード*でマイ アプリからアプリにサインインしようとしていないことを確認してください。
- キャプチャ プロセスをもう一度試してください。 正しいフィールドに赤いマーカーが表示されていることを確認します。
- キャプチャ プロセスが応答を停止しているように見える場合、またはサインイン ページが応答しない場合は、キャプチャ プロセスをもう一度試してください。 ただし、今回はプロセスを完了した後で F12 キーを押して、ブラウザーの開発者コンソールを開きます。 **コンソール** タブを選択します。**window.location="*&lt;アプリの構成時に指定したサインイン URL&gt;*"** と入力し、Enter キーを押します。 これにより、キャプチャ プロセスを終了し、キャプチャされたフィールドを格納するページ リダイレクトが強制的に実行されます。

#### パスワードベースの SSO アプリに別のユーザーを追加できない

ユーザーが直接割り当てられているすべてのパスワード SSO アプリで、48 を超える資格情報を構成することはできません。

パスワード ベースの SSO を持つアプリをユーザーに追加する場合は、ユーザーが直接メンバーであるグループにアプリを割り当て、そのグループの資格情報を構成することを検討してください。 グループに対して構成された資格情報は、グループのすべてのメンバーで使用できます。

#### パスワードベースの SSO アプリに別のグループを追加できない

各パスワード ベースの SSO アプリには、割り当てられ、資格情報が構成されている 48 グループの制限があります。 追加のグループを追加する場合は、次のいずれかを実行できます。

- アプリのインスタンスを追加する
- アプリを使用しなくなったグループを削除する

### サポートの要求

SSO を設定してユーザーを割り当てるときにエラー メッセージが表示される場合は、サポート チケットを開きます。 次の情報をできるだけお知らせください。

- 関連エラー ID
- UPN (ユーザーの電子メール アドレス)
- TenantID
- ブラウザーの種類
- タイム ゾーンと、エラーが発生したときの時刻/時間帯
- Fiddler のトレース

#### ポータル通知の詳細を表示する

ポータルの通知の詳細を確認するには、これらの手順に従います。

1. Microsoft Entra 管理センターの右上隅にある **通知** アイコン (ベル) を選択します。
2. **エラー**状態を示す通知を選択します。 (赤い "!" が表示されているものです)
    注

    *[成功]* または [*進行中*] 状態の通知は選択できません。
3. [ **通知の詳細** ] ウィンドウが開きます。 情報を読んで問題を把握します。
4. 依然として支援が必要な場合は、この情報をサポート エンジニアまたは製品グループと共有します。 **エラー** ボックスの右側にあるコピーアイコンを選択し、通知の詳細をコピーして共有します。

#### 支援を受けるためにサポート エンジニアに通知の詳細を送信する

このセクションに記載 *されているすべての* 詳細をサポートと共有して、迅速に支援できるようにすることが重要です。 記録するには、スクリーンショットを撮るか、[ **エラーのコピー**] を選択します。

次の情報は、各通知項目の意味を説明し、例を示しています。

##### 重要な通知項目

- **タイトル**: 通知のわかりやすいタイトル。

    例: *アプリケーション プロキシの設定*
- **説明**: 操作の結果として発生した内容。

    例: *入力された内部 URL は、別のアプリケーションで既に使用されています。*
- **通知 ID: 通知**の一意の ID。

    例: *clientNotification-2adbfc06-2073-4678-a69f-7eb78d96b068*
- **クライアント要求 ID**: ブラウザーが行った特定の要求 ID。

    例: *0000aa-11bb-cccc-dd22-eeeeee333333*
- **タイム スタンプ UTC**: 通知が発生したときのタイムスタンプ (UTC)。

    例: *2017-03-23T19:50:43.7583681Z*
- **内部トランザクション ID**: システムでエラーを検索するために使用される内部 ID。

    例: **71a2f329-ca29-402f-aa72-bc00a7aca603**
- **UPN**: 操作を実行したユーザー。

    例: *tperkins@f128.info*
- **テナント ID**: 操作を実行したユーザーがメンバーであるテナントの一意の ID。

    例: *aaaabbbb-0000-cccc-1111-dddd2222eeee*
- **ユーザー オブジェクト ID**: 操作を実行したユーザーの一意の ID。

    例: *aaaaaa-0000-1111-2222-bbbbbbbbbb*

##### 詳細な通知項目

- **表示名**: エラーのより詳細な表示名 (空の場合があります)。

    例: *アプリケーション プロキシの設定*
- **状態**: 通知の特定の状態。

    例: *失敗*
- **オブジェクト ID**: 操作が実行された対象のオブジェクト ID (空の場合があります)。

    例: *aaaaaa-0000-1111-2222-bbbbbbbbbb*
- **詳細**: 操作の結果として発生した内容の詳細な説明。

    例: *内部 URL 'https://bing.com/' は既に使用されているため無効です。*
- **コピー エラー**: **コピー エラー** ボックスの右側にある**コピー アイコン**を選択できるようにして、サポート用に通知の詳細をコピーできます。

    例:

    `{"errorCode":"InternalUrl\_Duplicate","localizedErrorDetails":{"errorDetail":"Internal url 'https://google.com/' is invalid since it is already in use"},"operationResults":\[{"objectId":null,"displayName":null,"status":0,"details":"Internal url 'https://bing.com/' is invalid since it is already in use"}\],"timeStampUtc":"2017-03-23T19:50:26.465743Z","clientRequestId":"00aa00aa-bb11-cc22-dd33-44ee44ee44ee","internalTransactionId":"aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb","upn":"tperkins@f128.info","tenantId":"aaaabbbb-0000-cccc-1111-dddd2222eeee","userObjectId":"aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"}`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/troubleshoot-saml-based-sso"} -->
## Azure Active Directory のアプリケーションに対する SAML に基づいたシングル サインオンをデバッグする方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/troubleshoot-saml-based-sso
- Service: entra-id / enterprise-apps
- Article date: 2023-09-07
- Summary: SAML ベースのシングル サインオン用に構成された Microsoft Entra アプリの問題をトラブルシューティングします。

アプリケーションの構成中に問題が発生した場合は、アプリケーションのチュートリアルに記載されたすべての手順に従っていることを確認してください。 アプリケーションの構成内に、アプリケーションの構成方法についてのインライン ドキュメントがあります。 また、 [SaaS アプリと Microsoft Entra ID を統合する方法に関するチュートリアルの一覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) にアクセスして、詳細な手順を説明します。

### アプリケーションの別のインスタンスを追加することができません

アプリケーションの 2 つ目のインスタンスを追加するには、以下のことができる必要があります。

- 2 つ目のインスタンスのために一意の識別子を構成する。 1 つ目のインスタンスに使用されているのと同じ識別子を構成することはできません。
- 1 つ目のインスタンスに使用されている証明書とは別の証明書を構成する。

表示されているオプションのいずれもアプリケーションがサポートしていない場合、2 つ目のインスタンスを構成することはできません。

### 識別子または応答 URL を追加することができません

識別子または応答 URL を構成できない場合は、ID と応答 URL の値がアプリケーション用に構成済みのパターンに一致していることを確認します。

アプリケーション用に構成済みのパターンを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。 Microsoft Entra ID のアプリケーション構成ペインが既に表示されている場合、手順 4 に進みます。
2. **Entra ID**&gt;で、**Enterprise アプリ**&gt;の**すべてのアプリケーション**を参照します。
3. シングル サインオンを構成するアプリケーションを選択します。
4. アプリケーションが読み込まれたら、アプリケーションの左側のナビゲーション メニューから **[シングル サインオン** ] を選択します。
5. [**モード**] ドロップダウンから **[SAML ベースのサインオン**] を選択します。
6. [ドメインと URL] セクションの下にある [ **識別子** ] または [ **応答** **URL] ボックスに移動します。**
7. アプリケーションでサポートされているパターンを確認するには、次の 3 つの方法があります。
    - テキスト ボックスで、サポートされているパターンがプレースホルダーとして表示されます (例: `https://contoso.com`)。
    - パターンがサポートされていない場合は、テキスト ボックスに値を入力しようとすると、赤い感嘆符が表示されます。 赤い感嘆符にポインターを置くと、サポートされているパターンが表示されます。
    - アプリケーションのチュートリアルでも、サポートされているパターンに関する情報を取得することができます。 **Microsoft Entra シングル サインオンの構成**セクションにおいて。 [ **ドメインと URL]** セクションの値を構成する手順に移動します。

Microsoft Entra ID で事前構成されたパターンと値が一致しない場合、アプリケーション ベンダーと協力して、Microsoft Entra ID で事前構成されたパターンと一致する値を取得できます。 アプリケーションがマルチテナントの場合、プリンシパル オブジェクトがアプリのプライマリ インスタンスでなければ、単一の識別子が許可されます。

### EntityID (ユーザー識別子) の形式はどこで設定しますか

Microsoft Entra ID がユーザー認証後の応答でアプリケーションに送信する EntityID (ユーザー ID) の形式は、選択することができません。

Microsoft Entra ID は、選択した値に基づく NameID 属性 (ユーザー識別子) の形式、または SAML AuthRequest でアプリケーションによって要求された形式を選択します。 詳細については、「NameIDPolicy」セクションの「 [シングル サインオン SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol#authnrequest) 」の記事を参照してください。

### アプリケーションでの構成を完了するための Microsoft Entra メタデータが見つかりません

Microsoft Entra ID からアプリケーションのメタデータまたは証明書をダウンロードするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;で、**Enterprise アプリ**&gt;の**すべてのアプリケーション**を参照します。
3. シングル サインオンを構成したアプリケーションを選択します。
4. アプリケーションが読み込まれたら、アプリケーションの左側のナビゲーション メニューから [ **シングル サインオン** ] を選択します。
5. **SAML 署名証明書**セクションに移動し、**ダウンロード**列の値を選択します。 アプリケーションでシングル サインオンを構成するために何が必要かに応じて、メタデータ XML または証明書をダウンロードするオプションが表示されます。

Microsoft Entra には、メタデータを取得する URL は用意されていません。 メタデータは、XML ファイルとしてのみ取得できます。

### アプリケーションに送信される SAML 要求をカスタマイズする

アプリケーションに送信される SAML 属性要求をカスタマイズする方法については、 [Microsoft Entra ID での要求マッピング](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization) に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/troubleshoot-user-provisioning-validation"} -->
## Microsoft Entra アプリ ギャラリー (プレビュー) のユーザー プロビジョニング検証のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/troubleshoot-user-provisioning-validation
- Service: entra-id / enterprise-apps
- Article date: 2026-08-26
- Summary: ギャラリーの申請を送信する前に、Azure Logic Apps検証テンプレートで失敗したテストを診断し、SCIM エンドポイントの一般的な問題を修正します。

Azure Logic Apps検証テンプレートのテストが失敗すると、どのフェーズとアクションが中断されたかが結果に示され、基になる HTTP 応答によってその理由が示されます。 この記事では、障害の根本原因を追跡し、最も頻繁に発生するクロスドメイン ID 管理 (SCIM) エンドポイントの問題を修正する方法について説明します。

トラブルシューティングを行う前に、失敗によって実際にブロックされたことを確認してください。 一部の結果は想定内であり、一部の失敗はオンボーディングを妨げるものではない警告です。 完全な一覧については、「 [受け渡しの意味を理解する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery#understand-what-passing-means)」を参照してください。

### 失敗したテストをトレースする

結果 JSON から外側に向かって作業します。 `provisioningErrorDetails` フィールドには、通常、根本原因に独自の名前が付けられます。

1. `Orchestrator_Workflow`実行で`Final_TestResults`アクションを開き、[**未加工の出力を表示**] を選択します。
2. 失敗したテストを見つけます。 `testResult`値は、この例のように、フェーズと失敗したアクションの名前を指定します。

    ```json
    {
      "testName": "Delete_Group_Test",
      "testResult": "FAILED - [Delete Phase] Failed Action: Delete_Step5_Delete_Group_By_Id",
      "provisioningErrorDetails": {
        "provisioningLogs": {
          "statusCode": 403,
          "body": {
            "error": {
              "code": "Authorization_RequestDenied",
              "message": "Insufficient privileges to complete the operation."
            }
          }
        }
      }
    }
    ```

    この結果は、 `Delete_Step5_Delete_Group_By_Id`の削除フェーズ中に失敗し、403 応答にはロジック アプリのマネージド ID にアクセス許可が不足しています。
3. HTTP 状態コードとエラー メッセージの `provisioningErrorDetails` を読み取ります。
4. 詳細が必要な場合は、 `runLink` 値を選択して子ワークフロー実行を開きます。
5. ワークフロー デザイナーで、検索アイコンを使用して、失敗したアクションを名前で検索します。
6. アクションを選択し、 **入力** と **出力を比較します**。 応答本文には、Microsoft Graphまたは SCIM エンドポイントによって返された正確なエラーが保持されます。

SCIM オンボード エージェントを使用した場合は、これらの手順が自動的に実行され、根本原因が報告され、エラーが修正された後に自動的に再実行されます。

### 一般的なエラーを修正する

ほとんどのエラーは、次のいずれかの問題にさかのぼります。 基になる問題を修正し、テストを再実行します。

#### エンドポイント URL に機能フラグが含まれている場合、要求は失敗します

`aadOptscim062020`などの機能フラグは、ロジック アプリのパラメーターではなく、プロビジョニング構成に属します。 `scimEndpoint` パラメーターにフラグが表示されると、要求は失敗します。

ロジック アプリ構成からフラグを削除します。 **エンタープライズ アプリケーション**でのみ設定します&gt;アプリケーション&gt;**プロビジョニング**&gt;**テナント URL**。

#### 認証エラーが発生して実行が途中で失敗する

完全な検証の実行には 60 ~ 90 分かかります。これは、多数のアクセス トークンがライブであるよりも長くなります。 実行中にトークンの有効期限が切れると、残りのテストは認証に失敗します。

少なくとも 24 時間有効なベアラー トークンを使用します。

#### Get\_Templates アクションは未承認のエラーを返します

ロジック アプリのマネージド ID に、テンプレート リソースに対するアクセス許可がありません。

アクセス許可スクリプトを再実行し、割り当てが反映されるまで数分かかります。 エラーが解決しない場合は、ロジック アプリを再作成してから、マネージド ID とアクセス許可を再構成します。

#### フィルター処理された SCIM 要求が失敗する

Microsoft Entra ID は、照合プロパティとして構成されている任意の属性でフィルターされた SCIM `GET` 要求を送信します。 エンドポイントがこれらの属性のいずれかでフィルター処理をサポートしていない場合、要求は失敗し、プロビジョニング ログにエラーとして表示されます。

**Provisioning**&gt;**Attribute マッピング**で一致するプロパティとして構成されたすべての属性に対して、フィルター処理された`GET`要求をサポートします。 たとえば、 `emails[type eq "work"].value` が一致するプロパティである場合、エンドポイントはこの要求を処理する必要があります。

```http
GET /scim/v2/Users?filter=emails[type eq "work"].value eq "user@contoso.com"
```

#### SCIM 409 競合エラーでテストが失敗する

SCIM サービスが期待どおりに応答せず、再試行によって競合が発生する可能性がある場合、プロビジョニング サービスは更新を再試行します。

テストを再実行して、エラーが一時的であるかどうかを確認します。 繰り返し発生する場合は、サービスが更新要求に対して正しく応答し、応答を遅らせないことを確認します。

#### 存在しないユーザーのクエリが失敗する

SCIM 仕様では、存在しないユーザーに対する`404 Not Found`応答が許可されますが、Microsoft Entra プロビジョニング サービスではその動作はサポートされていません。

空の`200 OK`配列を持つ`totalResults: 0`のように、どのユーザーにも一致しないフィルターベースのクエリに対しては、結果 0 件で`Resources`を返します。 この動作は、オンボーディングに必要です。 完全な一覧については、 [SCIM API の要件](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-user-provisioning-requirements#scim-api-requirements)を参照してください。

#### スキーマ検出可能性テストが失敗する

`Schema_Discoverability_Test` は、プロビジョニング マッピングに SCIM スキーマがアドバタイズしない属性が含まれている場合に失敗します。 詳細には、マップされている属性の数と比較して、サポートされている属性の数が表示されます。

エンドポイントに合わせて、属性マッピングを整理してください。 アプリケーションで[ **プロビジョニング**&gt;**マッピング**&gt;**ユーザーのプロビジョニング**]、[ **詳細オプションの表示**]、[ **属性一覧の編集]** の順に選択し、サポートされていない属性を削除します。

#### スキーマ検証エラーでテストが失敗する

SCIM サーバーは属性を一連の値に制限し、テスト ユーザー プロファイルはそのセットの外部の値を使用しました。

許可されている値を `defaultUserProperties` パラメーターに追加します。 `/Schemas` エンドポイントが`canonicalValues`を発行すると、エージェントは自動的に制限を検出します。

#### グループ テストが失敗する

ギャラリーへのオンボーディングにはグループ プロビジョニングが必要です。 `/Schemas` エンドポイントがグループ スキーマを発行しない場合、すべてのグループ テストはスキップされずに失敗します。

SCIM 2.0 グループ エンドポイントを実装します。これには、1 つの `PATCH` 要求で複数のグループ メンバーシップを更新するサポートが含まれます。

#### HTTP 429 で要求が失敗する

エンドポイントは、プロビジョニング サービスのレート制限です。

テナントあたり 1 秒あたり少なくとも 25 個の要求をサポートします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/tutorial-enforce-secret-standards"} -->
## チュートリアル: アプリケーション管理ポリシーを使用してシークレットと証明書の標準を適用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-enforce-secret-standards
- Service: entra-id / enterprise-apps
- Article date: 2024-03-12
- Summary: Microsoft Entra ID でアプリケーション管理ポリシーを使用してシークレットと証明書の標準を適用する方法について説明します。

このチュートリアルでは、Microsoft Entra ID のアプリケーション管理ポリシーを使用してシークレットと証明書の標準を適用する方法について説明します。

組織内のアプリケーションがセキュリティで保護された認証を使用していることを確認することは、機密データを保護し、システムの整合性を維持するために重要です。 Microsoft Entra ID は、アプリケーション管理ポリシーを使用してシークレットと証明書の制限を適用する方法を提供します。 この機能は、使用できるシークレットとキーの種類を管理し、それらが定期的にローテーションされるようにするのに役立ちます。 アプリケーション管理ポリシーは、Microsoft Graph PowerShell または Microsoft Graph API を使用してのみ更新できます。 この機能の詳細については、 [Microsoft Entra アプリケーション管理ポリシー API の概要に関するページを](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy)参照してください。

ポリシーは、組織内のすべてのアプリケーションまたは特定のアプリケーションに適用できます。 このチュートリアルでは、次の情報を学習します。

- シークレットと証明書の推奨される制限について説明します。
- テナントの現在のアプリケーション管理ポリシーを読み取る。
- 制限を適用するようにアプリケーション ポリシーを更新します。
- ポリシーが適用されていることを確認します。

重要

アプリケーション管理ポリシーを変更すると、アプリケーションとその認証機能に大きな影響を与える可能性があります。 変更を行う前に、それらの変更の影響と、それらの変更がアプリケーションに与える影響を理解しておくことが重要です。 運用環境に適用する前に、非運用環境で変更をテストし、現在のポリシー設定のコピーを作成してから更新する必要があります。

### [前提条件]

- ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) または [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロール。
- [Graph Explorer](https://aka.ms/ge)**OR** などの API クライアント
- Microsoft Graph PowerShell モジュールがインストールされています。 [「Microsoft Graph PowerShell モジュールのインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true)」を参照してください。

### シークレットと証明書の推奨プラクティス

多くの場合、アプリケーションに対する攻撃では、パスワード、キー、証明書などのシークレットを標的にして、機密データへの不正アクセスを取得します。 制限を適用することで、これらのリスクを軽減し、アプリケーションのセキュリティを確保できます。 シークレットと証明書に推奨される制限事項を次に示します。

- **アプリケーション パスワード/クライアント シークレットを無効にする**: クライアント シークレットを使用するアプリケーションは、それらを構成ファイルに格納したり、スクリプトにハードコーディングしたり、他の方法で公開を危険にさらしたりする可能性があります。 シークレット管理の複雑さにより、クライアント シークレットはリークの影響を受けやすく、攻撃者にとって魅力的です。
- **アプリケーションで対称キーの使用を無効にする**: 対称キーは、アプリケーションとアクセスするリソースの間で共有されるという点で、クライアント シークレットに似ています。 これは、攻撃者が対称キーにアクセスした場合、アプリケーションを偽装してリソースにアクセスできることを意味します。 対称キーは、両方の当事者が同じキーを共有する必要があるため、非対称キーよりも管理が困難です。
- **非対称キー (証明書) の有効期間を 180 日に制限**する: 証明書は、クライアント シークレットよりもアプリケーションを認証するより安全な方法を提供します。 ただし、適切に管理されていない場合でも、侵害される可能性があります。 証明書の有効期間を制限することで、有効期間の長い証明書が攻撃者によって悪用されるリスクを軽減できます。 証明書が侵害されないように、定期的にローテーションする必要があります。 証明書の推奨される最大有効期間は 180 日です。 つまり、少なくとも 180 日ごとに証明書をローテーションする必要があります。 機密性の高いアプリケーションの有効期間を短くすると、侵害のリスクがさらに軽減されます。 また、Azure Key Vault を使用して証明書の自動ローテーションを構成することもお勧めします。 詳細については、「[1 セットの認証資格情報を使用するリソースのシークレットのローテーションを自動化する](https://learn.microsoft.com/ja-jp/azure/key-vault/secrets/tutorial-rotation)」を参照してください。

Microsoft Entra テナントに推奨されるセキュリティプラクティスの詳細については、「セキュリティを [強化するための Microsoft Entra の構成](https://aka.ms/EntraSecurityRecommendations)」を参照してください。

### テナントアプリケーション管理ポリシーを確認する

新しいアプリケーション管理ポリシーを作成する前に、既存のポリシーを読んで、ニーズを満たしているかどうかを確認できます。 次の例は、テナントの既定のアプリケーション管理ポリシーを読み取る方法を示しています。 この API 要求を再利用して、このチュートリアルの後半でポリシーが適用されたことを確認することもできます。

#### 例

次の例では、テナントの既定のアプリケーション管理ポリシーを読み取ります。 応答には、現在のポリシー設定が表示されます。

::: zone pivot="ms-powershell"

`Connect-MgGraph` コマンドレットと`Policy.Read.All`アクセス許可を使用して Microsoft Graph に接続します。 少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) ロールでサインインします。 次に、次のコマンドを実行して、テナントの既定のアプリケーション管理ポリシーを読み取います。

```powershell
Connect-MgGraph -Scopes 'Policy.Read.All'
# Get the default application management policy
Get-MgPolicyDefaultAppManagementPolicy | format-list
```

このコマンドレットの詳細については、「 [Get-MgPolicyDefaultAppManagementPolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgpolicydefaultappmanagementpolicy?view=graph-powershell-1.0&preserve-view=true)」を参照してください。

#### アウトプット

次の例は、既定のテナント アプリ管理ポリシーの出力を示しています。 ポリシーが例と異なる場合があります。 組織でポリシーが適用されていない場合、 `id` フィールドは `00000000-0000-0000-0000-000000000000` に設定され、 `isEnabled` フィールドは `false` に設定されます。

```output
ApplicationRestrictions      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphAppManagementApplicationConfiguration
DeletedDateTime              :
Description                  : Default tenant policy that enforces app management restrictions on applications and service principals. To apply policy to targeted resources, create a new policy under appManagementPolicies collection.
DisplayName                  : Default app management tenant policy
Id                           : 00000000-0000-0000-0000-000000000000
IsEnabled                    : false
ServicePrincipalRestrictions : Microsoft.Graph.PowerShell.Models.MicrosoftGraphAppManagementServicePrincipalConfiguration
AdditionalProperties         : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#policies/defaultAppManagementPolicy/$entity]}
```

::: zone-end

::: zone pivot="ms-graph"

少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) ロールを使用して Microsoft Graph エクスプローラーにサインインします。 次に、次の要求を実行して、テナントの既定のアプリケーション管理ポリシーを読み取います。 `Policy.Read.All` アクセス許可に同意していることを確認します。

```http
GET https://graph.microsoft.com/v1.0/policies/defaultAppManagementPolicy
```

この要求の詳細については、 [tenantAppManagementPolicy の取得](https://learn.microsoft.com/ja-jp/graph/api/tenantappmanagementpolicy-get?view=graph-rest-1.0&tabs=http&preserve-view=true)に関するページを参照してください。

#### [応答]

次の例は、既定のテナント アプリ管理ポリシーの応答を示しています。 ポリシーが例と異なる場合があります。 組織内でポリシーが既に適用されていない場合、 `id` フィールドは `00000000-0000-0000-0000-000000000000` に設定され、 `isEnabled` フィールドは `false` に設定されます。

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#policies/defaultAppManagementPolicy/$entity",
    "@odata.id": "https://graph.microsoft.com/v2/927c6607-8060-4f4a-a5f8-34964ac78d70/defaultAppManagementPolicy/00000000-0000-0000-0000-000000000000",
    "id": "00000000-0000-0000-0000-000000000000",
    "displayName": "Default app management tenant policy",
    "description": "Default tenant policy that enforces app management restrictions on applications and service principals. To apply policy to targeted resources, create a new policy under appManagementPolicies collection.",
    "isEnabled": false,
    "applicationRestrictions": {
        "passwordCredentials": [],
        "keyCredentials":[]
    },
    "servicePrincipalRestrictions": {
        "passwordCredentials": [],
        "keyCredentials":[]
    }
}
```

::: zone-end

重要

更新する前に、現在のポリシー設定のコピーを作成します。 これにより、必要に応じて元の設定に戻すことができるようになります。 これを行うには、現在のポリシー設定をファイルにコピーするか、設定のスクリーンショットを撮ります。 元の設定を保存していない場合は、更新後に元の設定を見つけることができません。

### アプリケーション管理ポリシーを更新する

シークレットと証明書の制限を実装するには、既定のアプリケーション管理ポリシーを更新する必要があります。 この例では、推奨される設定を提供しますが、ニーズに合わせて調整したり、適用しない場合は特定の要素を省略したりできます。 次の例は、既定のアプリケーション管理ポリシーを推奨設定で更新する方法を示しています。

- `passwordCredentials`: クライアント シークレットと対称キーの属性を制限するポリシーを設定できます。 これらの種類の資格情報を制限するポリシーを設定しない場合は、これを省略できます。

    - `restrictionType` パラメーターを使用すると、適用する制限の種類を設定できます。 この場合、 `passwordAddition`、 `customPasswordAddition`、および `symmetricKeyAddition`を制限します。 これらの設定により、クライアント シークレット、カスタム パスワード、対称キーの作成が制限されます。
    - `state` パラメーターを使用すると、制限を有効または無効にすることができます。 `enabled`に設定すると、制限が適用されます。 `disabled`に設定すると、制限は適用されません。
    - `maxLifetime` パラメーターを使用すると、シークレットの最大有効期間を設定できます。 `passwordCredentials`の場合は、値を `null` に設定します。 値を `null` に設定すると、最大有効期間は制限されません。 これは、クライアント シークレットと対称キーの作成を完全に無効にしているためです。 クライアント シークレットの最大有効期間を設定する場合は、この値を ISO 8601 形式の期間に設定できます。 この例は、次のセクションで説明します。 期間の書式設定の詳細については、 [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601#Durations) を参照してください。
    - `restrictForAppsCreatedAfterDateTime` パラメーターを使用すると、新しいアプリケーションに対してポリシーを有効にする日付を設定できます。 この日付より前に作成されたアプリケーションは、ポリシーの影響を受けません。 この場合、2025 年 2 月 20 日以降に作成されたアプリケーションに制限を適用します。 ニーズに合わせてこの日付を更新してください。 特定の日付の前または後に作成されたアプリケーションに対して異なる制限を設定する場合は、異なる `restrictForAppsCreatedAfterDateTime` 値を持つ複数のポリシーを設定できます。
- `keyCredentials`: 証明書のパラメーターを設定できます。 この場合、アプリケーション証明書の有効期間を 180 日に制限します。

    - `restrictionType` パラメーターを使用すると、適用する制限の種類を設定できます。 この場合は、 `asymmetricKeyLifetime`を制限します。 これにより、アプリケーション証明書の有効期間がユーザー定義値に制限されます。
    - `state` パラメーターを使用すると、制限を有効または無効にすることができます。 `enabled`に設定すると、制限が適用されます。 `disabled`に設定すると、制限は適用されません。
    - `maxLifetime` パラメーターを使用すると、証明書の最大有効期間を設定できます。 この場合、証明書の有効期間を 180 日に制限します。 これは、ISO 8601 期間形式を使用して行われます。 プレフィックス `P` は値が一定期間であることを示し、 `180D` は期間が 180 日であることを示します。 特定のニーズに合わせて、 `180` から別の値に数値を変更できます。 期間の書式設定の詳細については、 [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601#Durations) を参照してください。
    - `restrictForAppsCreatedAfterDateTime` パラメーターを使用すると、新しいアプリケーションに対してポリシーを有効にする日付を設定できます。 この日付より前に作成されたアプリケーションは、ポリシーの影響を受けません。 この場合、2025 年 2 月 20 日以降に作成されたアプリケーションに制限を適用します。 ニーズに合わせてこの日付を更新してください。 特定の日付の前または後に作成されたアプリケーションに対して異なる制限を設定する場合は、異なる `restrictForAppsCreatedAfterDateTime` 値を持つ複数のポリシーを設定できます。

#### 例

次の例では、前のセクションで説明した設定で既定のアプリケーション管理ポリシーを更新します。

::: zone pivot="ms-powershell"

```powershell
Connect-MgGraph -Scopes 'Policy.ReadWrite.All'
Import-Module Microsoft.Graph.Identity.SignIns
# Define the parameters for the application management policy
$params = @{
isEnabled = $true
applicationRestrictions = @{
    passwordCredentials = @(
        @{
            restrictionType = "passwordAddition"
            state = "enabled"
            maxLifetime = $null
            restrictForAppsCreatedAfterDateTime = [System.DateTime]::Parse("2025-02-20T10:37:00Z")
        }
        @{
            restrictionType = "customPasswordAddition"
            state = "enabled"
            maxLifetime = $null
            restrictForAppsCreatedAfterDateTime = [System.DateTime]::Parse("2025-05-20T10:37:00Z")
        }
        @{
            restrictionType = "symmetricKeyAddition"
            state = "enabled"
            maxLifetime = $null
            restrictForAppsCreatedAfterDateTime = [System.DateTime]::Parse("2025-02-20T10:37:00Z")
        }
    )
    keyCredentials = @(
        @{
            restrictionType = "asymmetricKeyLifetime"
            maxLifetime = "P180D"
            restrictForAppsCreatedAfterDateTime = [System.DateTime]::Parse("2025-02-20T10:37:00Z")
        }
    )
}
}
# Update the default application management policy
Update-MgPolicyDefaultAppManagementPolicy -BodyParameter $params
```

このコマンドレットの詳細については、「 [Update-MgPolicyDefaultAppManagementPolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgpolicydefaultappmanagementpolicy?view=graph-powershell-1.0&preserve-view=true)」を参照してください。

::: zone-end

::: zone pivot="ms-graph"

`Policy.ReadWrite.All` アクセス許可に同意していることを確認します。 次に、次の要求を実行して、テナントの既定のアプリケーション管理ポリシーを更新します。

```http
PATCH https://graph.microsoft.com/v1.0/policies/defaultAppManagementPolicy
Content-Type: application/json

{
    "isEnabled": true,
    "applicationRestrictions": {
        "passwordCredentials": [
            {
                "restrictionType": "passwordAddition",
                "state": "enabled",
                "maxLifetime": null,
                "restrictForAppsCreatedAfterDateTime": "2025-02-20T10:37:00Z"
            },
            {
                "restrictionType": "customPasswordAddition",
                "state": "enabled",
                "maxLifetime": null,
                "restrictForAppsCreatedAfterDateTime": "2025-05-20T10:37:00Z"
            },
            {
                "restrictionType": "symmetricKeyAddition",
                "state": "enabled",
                "maxLifetime": null,
                "restrictForAppsCreatedAfterDateTime": "2025-02-20T10:37:00Z"
            },
        ],
        "keyCredentials": [
            {
                "restrictionType": "asymmetricKeyLifetime",
                "state": "enabled",
                "maxLifetime": "P180D",
                "restrictForAppsCreatedAfterDateTime": "2025-02-20T10:37:00Z"
            }
        ]
    },
}
```

この要求の詳細については、「 [tenantAppManagementPolicy の更新](https://learn.microsoft.com/ja-jp/graph/api/tenantappmanagementpolicy-update?view=graph-rest-1.0&tabs=http&preserve-view=true)」を参照してください。

#### [応答]

要求が送信されると、ポリシーが正常に更新されたことを示す応答を受信する必要があります。 応答は、要求が成功し、返されるコンテンツがないことを示す `204 No Content` 状態コードである必要があります。

```http
    HTTP/1.1 204 No Content
```

::: zone-end

### ポリシーが適用されていることを確認する

アプリケーション管理ポリシーを更新したら、 前に示したように、既定のアプリケーション管理ポリシーをもう一度読み取ることで、アプリケーション管理ポリシーが適用されていることを確認できます。 応答には、適用した制限を含む更新されたポリシーが表示されます。

初めての場合は、アプリケーション管理ポリシーを適用します。 `id` フィールドは `00000000-0000-0000-0000-000000000000` から新しい GUID に変更されている必要があります。 この変更は、ポリシーが作成されたことを示します。

新しいアプリケーションを作成し、制限が適用されているかどうかを確認することで、ポリシーが適用されていることを確認することもできます。 たとえば、クライアント シークレットまたは対称キーを使用して新しいアプリケーションを作成しようとすると、次のスクリーンショットに示すように、操作が許可されていないことを示すエラーが表示されます。

[Image: クライアント シークレットがテナント全体のポリシーによってブロックされていることを示す警告を示す Microsoft Entra 管理センターのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/tutorial-govern-monitor"} -->
## チュートリアル*: アプリケーション*の管理とモニター* - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-govern-monitor
- Service: entra-id / enterprise-apps
- Article date: 2024-12-04
- Summary: アクセス レビューや Azure Monitor とのログの統合など、Microsoft Entra ID でアプリケーションを管理および監視する方法について説明します。

Fabrikam の IT 管理者は、 [Microsoft Entra アプリケーション ギャラリーからアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-application-gallery)を追加して構成しました。 また、「チュートリアル: アプリケーションのアクセスとセキュリティを管理する」の情報を使用して、アクセスを管理できること [と、アプリケーションがセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-access-security)されていることを確認しました。 次に、アプリケーション\*の管理とモニター\*に使用できるリソース\*を理解する必要があります。

アプリケーション\*の管理者\*は、このチュートリアル\*の情報を用いて、次の方法を学習します：

- アクセス レビューの作成
- 監査ログを表示する
- サインインを表示する
- ログを Azure Monitor に送信する

### 前提条件

- アクティブなサブスクリプションが含まれる Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 組織のメンバー ユーザーのための Microsoft Entra ID ガバナンス サブスクリプション（すべての従業員を含みます。アクセスをレビュー中の従業員や、アクセス権をレビューされている従業員も含まれます）。 この機能に含まれる一部の機能は、Microsoft Entra ID P2 サブスクリプションで動作する場合があります。
- 次のいずれかのロール: Identity Governance 管理者、特権ロール管理者、クラウド アプリケーション管理者、またはアプリケーション管理者。
- ご利用の Microsoft Entra テナントに構成されているエンタープライズ アプリケーション。

### アクセス レビューの作成

管理者\*は、ユーザー\*またはゲスト\*が適切なアクセス権を持っていることを確認する必要があります。 アプリケーション\*のユーザー\*は、アクセスレビュー\*と認定に参加するか、アクセスの必要性を証明することを求められます。 アクセス レビュー\*が完了した時点で、ユーザー\*のアクセス権に変更を加えたり、不要なアクセス権を削除\*することができます。 詳細については、「 [アクセス レビューを使用してユーザーとゲストのユーザー アクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-access-review)」を参照してください。

アクセス レビュー\*を作成する\*には：

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**Access のレビューを参照します**。
3. **[新しいアクセス レビュー**] を選択して、新しいアクセス レビューを作成します。
4. [ **確認する内容の選択**] で、[アプリケーション] を選択 **します**。
5. **[+ アプリケーションの選択]** を選び、アプリケーションを決めたら、[**選択**] をクリックします。
6. 次に、レビューのスコープを選択できます。 オプションは次のとおりです。
    - **ゲスト ユーザーのみ** - このオプションでは、ディレクトリ内の Microsoft Entra B2B ゲスト ユーザーのみにアクセス レビューが制限されます。
    - **すべてのユーザー** - このオプションは、リソースに関連付けられているすべてのユーザー オブジェクトにアクセス レビューのスコープを設定します。 **[すべてのユーザー]** を選択します。
7. [ **次へ: レビュー]** を選択します。
8. [ **校閲者の指定** ] セクションの [校閲者の選択] ボックス **で、[選択したユーザーまたはグループ**] を選択し、[ **+ 校閲者の選択**] を選択し、アプリケーションに割り当てられているユーザー アカウントを選択します。
9. [ **レビューの繰り返しの指定**] セクションで、次の選択項目を指定します。
    - **期間 (日数)** - 既定値の **3** をそのまま使用します。
    - **繰り返しを確認** する - **1 回**選択します。
    - **開始日** - 今日の日付を開始日として受け入れます。
10. [ **次へ: 設定] を選択します**。
11. [ **完了時の設定** ] セクションでは、レビューが完了した後の動作を指定できます。 [ **リソースに結果を自動適用する] を選択します**。
12. [ **次へ: 確認と作成**] を選択します。
13. アクセス レビューに名前を付けます。 必要に応じて、そのレビューに説明を加えます。 その名前と説明がレビュアーに示されます。
14. 情報を確認し、[ **作成**] を選択します。

#### アクセス レビューを開始する

アクセス レビューは数分で開始され、その状態を示すインジケーターと共に一覧に表示されます。

既定では、レビューの開始直後に Microsoft Entra ID からレビュー担当者宛てにメールが送信されます。 Microsoft Entra ID からメールを送信しないように選択した場合は、アクセス レビューが実行待ちになっていることを必ずレビュー担当者に伝えてください。 レビュー担当者には、グループまたはアプリケーションへのアクセスをレビューする手順を案内することができます。 レビュー\*対象がゲスト\*で、自分のアクセスをレビュー\*してもらう場合は、グループ\*またはアプリケーション\*への自身のアクセス権をレビュー\*するための手順を案内します。

ゲストがレビュー担当者として割り当てられていても、テナントへの招待を受け入れていない場合、そのゲストはアクセス レビューからの電子メールを受信しません。 ゲストがレビューを開始するには、まず招待を受け入れる必要があります。

#### アクセス レビューの状態を表示する

アクセス レビューが完了するたびに、その進行状況を追跡できます。

1. **ID ガバナンス**&gt;**アクセス レビュー**に移動します。
2. 一覧で、作成したアクセス レビューを選択します。
3. [ **概要** ] ページで、アクセス レビューの進行状況を確認します。

**[結果**] ページには、インスタンスでレビュー中の各ユーザーに関する情報が表示されます。これには、結果の停止、リセット、ダウンロードの機能が含まれます。 詳細については、 [Microsoft Entra アクセス レビューのグループとアプリケーションのアクセス レビューの完了に関する記事を](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review) 参照してください。

### 監査ログを表示する

Microsoft Entra 監査ログは、テナント内のさまざまなアクティビティをキャプチャします。 これらのログは、監視する必要があるアクティビティに関する貴重な分析情報を提供します。 詳細については、「 [Microsoft Entra ID の監査ログ」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)参照してください。

監査ログを表示するには、**Entra ID**&gt;**監視と健康**&gt;**監査ログ**に移動します。

監査ログは、次のカテゴリに分類されるアクティビティをキャプチャします。 このリストは全てを網羅しているわけではありません。 監査ログのカテゴリとアクティビティの完全な一覧については、「 [監査ログアクティビティ」](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)を参照してください。

- パスワード リセット アクティビティ
- パスワード リセット登録アクティビティ
- セルフ サービス グループ アクティビティ
- Office 365 グループ名の変更
- アカウント プロビジョニングのアクティビティ
- パスワード ロールオーバーの状態
- アカウント プロビジョニング エラー

### サインイン ログを表示する

Microsoft Entra サインイン ログは、対話型、非対話型、マネージド ID、サービス プリンシパルのサインインをキャプチャします。詳細については、「 [Microsoft Entra ID のサインイン ログ」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)参照してください。

サインイン ログを表示するには、 **Entra ID**&gt;**監視と健康**&gt;**サインイン ログに移動します**。

[エンタープライズ アプリケーション] 領域からアプリケーションのサインイン情報を表示することもできます。 サインイン ログは **、監視と正常性**&gt;**サインイン ログ**から同じログを開きますが、フィルターは既に選択したアプリケーションに設定されています。 **使用状況と分析情報レポートには、**アプリケーションのサインイン アクティビティもまとめられます。

### ログを Azure Monitor に送信する

Microsoft Entra アクティビティ ログには、Microsoft Entra ID Free の場合は 7 日間、Microsoft Entra ID P1/P2 の場合は 30 日間の情報のみが格納されます。 ニーズに応じて、アクティビティログ データをバックアップするために追加のストレージが必要になる場合があります。

Azure Monitor ログを使用すると、データを長期間保持し、視覚化やアラートなどの強力な分析ツールを有効にすることができます。 ログと Azure Monitor ログの統合の詳細については、 [Microsoft Entra ログと Azure Monitor の統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)に関するページを参照してください。

Azure Monitor にログを送信するには、Log Analytics ワークスペースが必要です。 それが作成されたら、Log Analytics と統合するように診断設定を構成します。 ログと Azure Monitor と Log Analytics の統合にはコストに関する考慮事項があるため、先に進む前に [、Azure Monitor の Microsoft Entra アクティビティ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations#cost-considerations) のこのセクションを確認してください。

Log Analytics ワークスペースが構成されている場合は、次を行います。

1. [ **診断設定]** を選択し、[ **診断設定の追加]** を選択します。 [監査ログ] または [サインイン] ページから [エクスポート設定] を選択して、診断設定の構成ページに移動することもできます。
2. ストリーミングするログを選択し、[ **Log Analytics ワークスペースに送信** ] オプションを選択して、フィールドに入力します。
3. **[保存] を選択します**。

約 15 分後に、イベントが Log Analytics ワークスペースにストリーミングされていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/tutorial-manage-access-security"} -->
## チュートリアル: アプリケーションのアクセスとセキュリティを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-access-security
- Service: entra-id / enterprise-apps
- Article date: 2022-07-18
- Summary: このチュートリアルでは、Microsoft Entra ID でアプリケーションへのアクセスを管理し、そのセキュリティを確保する方法について説明します。

Fabrikam の IT 管理者は、Microsoft Entra アプリケーション ギャラリーからアプリケーションを追加して構成しました。 この時点で彼らは、アプリケーションへのアクセスを管理、かつアプリケーションのセキュリティを確保するために使用できる機能について理解しておく必要があります。 管理者は、このチュートリアルの情報を用いて、次の方法を学習します：

- すべてのユーザーの代理でアプリケーションへの同意を許可する
- 多要素認証を有効にすることで、サインインのセキュリティを強化する
- アプリケーションのユーザーに使用条件を伝える
- マイ アプリ ポータルでコレクションを作成する

### 前提条件

- アクティブなサブスクリプションが含まれる Azure アカウント。 [アカウントは無料で作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール: 特権ロール管理者、クラウド アプリケーション管理者、またはアプリケーション管理者。
- ご利用の Microsoft Entra テナントに構成されているエンタープライズ アプリケーション。
- 少なくとも 1 つのユーザー アカウントが追加され、アプリケーションに割り当てられます。 詳細については、[「クイック スタート: ユーザー アカウントを作成して割り当てる」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)。

### テナント全体の管理者の同意を許可する

管理者がテナントに追加したアプリケーションにおいては、組織内のすべてのユーザーがテナントを使用できて、その使用に対する同意を個別に要求する必要が生じないよう、アプリケーションをセットする必要があります。 ユーザーの同意が必要になるのを回避するために、管理者は組織内のすべてのユーザーの代理でアプリケーションの同意を付与することができます。 詳細については、[「同意とアクセス許可の概要」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview)を参照してください。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. テナント全体の管理者の同意を付与するアプリケーションを選択します。
4. **[セキュリティ]**で**[アクセス許可]**を選択します。
5. アプリケーションに必要なアクセス許可を慎重に確認します。 アプリケーションで必要なアクセス許可に同意する場合は、**[管理者の同意の付与]** を選択します。

### 条件付きアクセス ポリシーを作成する

管理者は、アプリケーションに割り当てるユーザーだけが安全にサインインできるようにする必要があります。 これを行うために、特定のユーザー グループに対して多要素認証を強制する条件付きアクセス ポリシーを構成できます。 詳細については、[「条件付きアクセスとは」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)をご覧ください。

#### グループの作成

管理者にとっては、アプリケーションのすべてのユーザーをグループに割り当て、アプリケーションへのアクセスを管理する方が簡単です。 その後、管理者はグループ レベルでアクセスを管理できます。

1. テナントの概要の左側のメニューで、**[グループ]**&gt;**[すべてのグループ]** を選択します。
2. ペインの上部にある **[新しいグループ]** を選択します。
3. グループの名前として *「MFA-Test-Group」* と入力します。
4. [メンバーが選択されません] を選択し、アプリケーションに割り当てたユーザー アカウントを選択します。
5. **[作成]** を選択します。

#### グループの条件付きアクセス ポリシーを作成する

1. テナントの概要の左側のメニューで、 **Entra ID**&gt;**Conditional Access** を選択します。
2. [ **+ 新しいポリシー**] を選択し、[ **新しいポリシーの作成**] を選択します。
3. ポリシーの名前を入力します (例: *MFA Pilot*)。
4. **[割り当て]** で、**[ユーザーまたはワークロード ID]** を選択します。
5. **[含める]** タブで**[ユーザーとグループを選択]**を選択してから、**[ユーザーとグループ]**を選択します。
6. 以前に作成した *MFA-Test-Group* を参照して選んだら、**[選択]**を選択します。
7. **[作成する]** はまだ選択しないでください。次のセクションでポリシーに MFA を追加します。

#### 多要素認証を構成する

このチュートリアルで、管理者はアプリケーションを構成するための基本的なステップを確認できますが、開始する前に MFA のプラン作成を検討する必要があります。 詳細については、「[Microsoft Entra 多要素認証デプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)」を参照してください。

1. **[クラウド アプリまたはアクション]** で、**[No cloud apps, action, or authentication contexts selected](クラウド アプリ、アクション、または認証コンテキストが選択されません)** を選択します。 このチュートリアルに関しては、**[含める]** タブで **[リソースを選択]** を選択してください。
2. アプリケーションを検索して選択し、**[選択]**を選択します。
3. **[アクセスの制御]** と**[許可]**で、**[0個 のコントロールが選択されました]**を選択します。
4. **[多要素認証を要求する]** チェックボックスをオンにし、**[選択]**を選択します。
5. **[ポリシーを有効にする]** を **[オン]** に設定します。
6. 条件付きアクセス ポリシーを適用するには、**[作成]** を選択します。

#### 多要素認証のテスト

1. InPrivate またはシークレット モードで新しいブラウザー ウィンドウを開き、アプリケーションの URL を参照します。
2. サインインには、アプリケーションに割り当てたユーザー アカウントを使用します。 Microsoft Entra 多要素認証に登録して使用する必要があります。 プロンプトに従って手順を完了し、Microsoft Entra 管理者センターにサインインできることを確認します。
3. ブラウザー ウィンドウを閉じます。

### 使用条件ステートメントを作成する

Juan は、ユーザーがアプリケーションの使用を開始する前に、特定のご契約条件を認識していることを確認する必要があります。 詳細については、「[Microsoft Entra 利用規約](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)」を参照してください。

1. Microsoft Word で、新しいドキュメントを作成します。
2. 「マイ 使用条件」とタイプし、ドキュメントをコンピューターに *mytou.pdf* として保存します。
3. **[管理]**の **[条件付きアクセス]** メニューで、**[利用規約]**を選択します。
4. トップ メニューで、**[+ 新しい用語]**を選択します。
5. **[名前]** ボックスに「*My TOU*」と入力します。
6. **[表示名]** ボックスに、「*My TOU*」と入力します。
7. 使用条件 PDF ファイルをアップロードします。
8. **[言語]** は **[英語]** を選択します。
9. **[ユーザーに使用条件の展開を要求する]** では、**[オン]** を選択します。
10. **[条件付きアクセス ポリシー テンプレートを使用して適用します]** で、**[カスタム ポリシー]** を選択します。
11. **[作成]** を選択します。

#### 使用条件をポリシーに追加する

1. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
2. ポリシーの一覧から、*MFA Pilot* ポリシーを選択します。
3. **[アクセスの制御]** と**[許可]**で、[0個 のコントロールが選択されました]を選択します。
4. *[My TOU]* を選択します。
5. **[選択したコントロールすべてが必要]**を選択し、**[選択]**を選択します。
6. **保存**を選択します。

### マイ アプリ ポータルでコレクションを作成する

マイ アプリ ポータルでは、管理者とユーザーが組織で使用されるアプリケーションを管理できます。 詳細については、[「アプリケーションのエンド ユーザー エクスペリエンス」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences)を参照してください。

Note

アプリケーションは、ユーザーがアプリケーションに割り当てられてから、アプリケーションがユーザーに表示されるよう設定された後にのみ、ユーザーのマイ アプリ ポータルに表示されます。 アプリケーションをユーザーに表示する方法 については、[「アプリケーションのプロパティを構成する」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-configure)を参照してください。

既定では、すべてのアプリケーションが 1 つのページにまとめて表示されます。 しかし、コレクションを使用して関連するアプリケーションをグループ化し、別々のタブで表示すれば、アプリケーションが見つけやすくなります。 たとえば、コレクションを使用して、特定の担当業務、タスク、プロジェクトなどに関連したアプリケーションの論理グループを作成することができます。 このセクションでは、コレクションを作成してユーザーとグループに割り当てます。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. **[管理]** で **[App Launchers](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/アプリ起動ツール)**&gt;**[コレクション]** を選択します。
4. **[新しいコレクション]** を選択します。 [新しいコレクション] ページで、コレクションの**名前**を入力します (名前に「collection」を使用しないことをお勧めします)。 次に、**説明**を入力します。
5. **[アプリケーション]** タブを選択します。 **[+ Add application](+ アプリケーションの追加)** を選択して、[アプリケーションの追加] ページで、コレクションに追加するすべてのアプリケーションを選択するか、検索ボックスを使用してアプリケーションを検索します。
6. アプリケーションの追加が完了したら、**[追加]** を選択します。 選択したアプリケーションの一覧が表示されます。 矢印を使用して、一覧内のアプリケーションの順序を変更できます。
7. **[所有者]** タブをクリックします。 **[+ ユーザーとグループの追加]** を選択して、[ユーザーとグループの追加] ページで、所有権を割り当てるユーザーまたはグループを選択します。 ユーザーとグループの選択が終了したら、**[選択]** を選択します。
8. **[ユーザーとグループ]** タブを選択します。 **[+ ユーザーとグループの追加]** を選択して、**[ユーザーとグループの追加]** ページで、コレクションを割り当てるユーザーまたはグループを選択します。 または、検索ボックスを使用して、ユーザーまたはグループを見つけます。 ユーザーとグループの選択が終了したら、**[選択]** を選択します。
9. **[確認と作成]**、**[作成]** の順に選択します。 新しいコレクションのプロパティが表示されます。

#### マイ アプリ ポータルでコレクションを確認する

1. InPrivate またはシークレット モードで新しいブラウザー ウィンドウを開き、[マイ アプリ](https://myapps.microsoft.com/) に移動します。
2. サインインには、アプリケーションに割り当てたユーザー アカウントを使用します。
3. 作成したコレクションがマイ アプリ ポータルに表示されていることを確認します。
4. ブラウザー ウィンドウを閉じます。

### リソースをクリーンアップする

将来使用するためにリソースを保持するか、このチュートリアルで作成したリソースを引き続き使用しない場合は、次のステップで削除します。

#### アプリケーションを削除する

1. 左側のメニューで、**[エンタープライズ アプリケーション]** を選択します。 **[すべてのアプリケーション]** ペインが開き、Microsoft Entra テナントのアプリケーションの一覧が表示されます。 削除するアプリケーションを検索して選択します。
2. 左側のメニューの **[管理]** セクションで **[プロパティ]** を選択します。
3. **[プロパティ]** ウィンドウの上部で、**[削除]** を選択してから **[はい]** を選択し、Microsoft Entra テナントからアプリケーションを削除することを確定します。

#### 条件付きアクセス ポリシーを削除する

1. [**Entra ID**&gt;**条件付きアクセス**&gt;**ポリシー**]
2. **MFA パイロット**を検索して 選択します。
3. ペインの上部にある **[削除]** を選択します。

#### グループを削除します

1. **[Entra ID]**&gt;**[グループ]** を選択します。
2. **[すべてのグループ]** ページで、**[MFA-Test-Group]** グループを検索して選択します。
3. [概要] ページで **[削除]**を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on"} -->
## チュートリアル: フェデレーション証明書の管理 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on
- Service: entra-id / enterprise-apps
- Article date: 2025-04-30
- Summary: このチュートリアルでは、フェデレーション証明書の有効期限をカスタマイズする方法と、有効期限が近づいている証明書を更新する方法について説明します。

このチュートリアルでは、有効期限をカスタマイズし、シームレスな SAML シングル サインオン (SSO) の証明書を更新することで、Microsoft Entra IDでフェデレーション証明書を管理する方法について説明します。

サービスとしてのソフトウェア (SaaS) アプリケーションへのフェデレーション シングル サインオン (SSO) を確立するために作成Microsoft Entra ID証明書に関する一般的な質問と情報について説明します。 Microsoft Entra アプリケーション ギャラリーから、またはギャラリー以外のアプリケーション テンプレートを使用して、アプリケーションを追加します。 アプリケーションの構成には、フェデレーション SSO オプションを使用します。

このチュートリアルは、Security Assertion Markup Language (SAML) を介して Microsoft Entra SSO を使用するように構成されたアプリに関連します。

このチュートリアルでは、アプリケーションの管理者が次の方法を学習します。

- ギャラリーおよびギャラリー以外のアプリケーションの証明書の生成
- 証明書の有効期限をカスタマイズする
- 証明書の有効期限の電子メール通知アドレスの追加
- 証明書の更新
- 証明書のローテーションに関する ISV のガイダンスとベスト プラクティス

### 前提条件

- アクティブなサブスクリプションを持つAzure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール: 特権ロール管理者、クラウド アプリケーション管理者、またはアプリケーション管理者。
- Microsoft Entra テナントで構成されたエンタープライズ アプリケーション。

### ギャラリーおよびギャラリー以外のアプリケーション用に自動生成された証明書

ギャラリーから新しいアプリケーションを追加し、SAML ベースのサインオンを構成すると、Microsoft Entra IDは 3 年間有効なアプリケーションの自己署名証明書を生成します。 SAML サインオンの設定の詳細については、「[Single sign-on to applications in Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を参照してください。

アクティブな証明書をセキュリティ証明書 (**.cer**) ファイルとしてダウンロードするには、Microsoft Entra 管理センターの次のセクションに移動します。

**Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**すべてのアプリケーション**&gt;** [Your application]**&gt;**シングル サインオン**を選択し、**SAML 証明書**の見出しでダウンロードリンクを選択します。

未加工 (バイナリ) の証明書または Base64 (Base64 エンコード テキスト) 証明書を選択できます。 ギャラリー アプリケーションの場合、このセクションには、アプリケーションの要件に応じて、証明書をフェデレーション メタデータ XML ( **.xml** ファイル) としてダウンロードするためのリンクが表示される場合もあります。

[ **トークン署名証明書** ] 見出しの **[編集]** アイコン (鉛筆) を選択して、アクティブまたは非アクティブな証明書をダウンロードすることもできます。このアイコンには、[ **SAML 署名証明書** ] ページが表示されます。 ダウンロードする証明書の横にある省略記号 (**...**) を選択し、目的の証明書の形式を選択します。

プライバシー強化メール (PEM) 形式で証明書をダウンロードするその他のオプションがあります。 この形式は Base64 と同じですが、**.pem** ファイル名拡張子を持ちます。これは証明書形式としてWindows認識されません。 証明書に関してよく寄せられる質問については、「 [よく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-management-certs-faq)」を参照してください。

### フェデレーション証明書の有効期限のカスタマイズと、新しい証明書へのロールオーバー

既定では、Azureは、SAML シングル サインオンの構成中に証明書を自動的に作成するときに、3 年後に有効期限が切れる証明書を構成します。 保存した後は証明書の日付を変更できないので、以下を行う必要があります。

1. 目的の日付で新しい証明書を作成します。
2. 新しい証明書を作成します。
3. 適切な形式で新しい証明書をダウンロードします。
4. アプリケーションに新しい証明書をアップロードします。
5. Microsoft Entra 管理センターで新しい証明書をアクティブにします。

次の 2 つのセクションは、これらの手順の実行に役立ちます。

### 新しい証明書を作成する

最初に、別の有効期限の新しい証明書を作成し、保存します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**All applications** に移動します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. [ **管理** ] セクションで、[ **シングル サインオン**] を選択します。
5. **[シングル サインオン方法の選択**] ページが表示されたら、[SAML] を選択**します**。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **SAML 証明書** ] 見出しを見つけて、[ **編集** ] アイコン (鉛筆) を選択します。 **[SAML 署名証明書**] ページが表示され、各証明書の状態 (**アクティブ**または**非アクティブ**)、有効期限、および拇印 (ハッシュ文字列) が表示されます。
7. [ **新しい証明書**] を選択します。 新しい行が証明書一覧の下に表示され、既定で有効期限が現在の日付からちょうど 3 年後に設定されます。 (変更はまだ保存されていないので、有効期限は依然として変更できます)
8. 新しい証明書の行で、有効期限の列にカーソルを合わせ、[ **日付の選択** ] アイコン (カレンダー) を選択します。 新しい行の現在の有効期限の日付が表示されたカレンダー コントロールが表示されます。
9. カレンダー コントロールを使用して新しい日付を設定します。 現在の日付と現在の日付から 3 年後の間の任意の日付を設定できます。
10. **[保存] を選択します**。 新しい証明書の状態が **[非アクティブ]**、選択した有効期限、拇印で表示されるようになりました。
    Note

    既に有効期限が切れている既存の証明書があり、新しい証明書を生成すると、新しい証明書は署名トークンと見なされます。 まだアクティブでない場合でも考慮されます。 期限切れの証明書は、トークンの署名に使用されなくなりました。
11. **[X]** を選択して、**[SAML によるシングル サインオンのセットアップ]** ページに戻ります。

### 証明書のアップロードとアクティブ化

次に、新しい証明書を正しい形式でダウンロードし、アプリケーションにアップロードして、Microsoft Entra IDでアクティブにします。

1. アプリケーションの SAML サインオンの構成手順については、次のいずれかから詳細を確認できます。

    - **構成ガイド**のリンクを選択して、別のブラウザー ウィンドウまたはタブに表示します。
    - **設定**した見出しを参照し、[**詳細な手順を表示**] を選択してサイドバーに表示します。
2. 手順では、証明書のアップロードに必要なエンコード形式に注意してください。
3. 前の「 ギャラリーおよびギャラリー以外のアプリケーションの自動生成された証明書 」セクションの手順に従います。 この手順では、アプリケーションによるアップロードに必要なエンコード形式で証明書をダウンロードします。
4. 新しい証明書にロール オーバーする場合は、[ **SAML 署名証明書** ] ページに戻り、新しく保存した証明書の行で省略記号 (**...**) を選択し、[ **証明書をアクティブにする**] を選択します。 新しい証明書の状態は **アクティブ**に変わり、以前にアクティブだった証明書は **非アクティブ**の状態に変わります。
5. 適切なエンコード形式の SAML 署名証明書をアップロードできるように、前に表示したアプリケーションの SAML サインオン構成手順に引き続き従います。

アプリに証明書の有効期限の検証がなく、証明書がMicrosoft Entra IDとアプリの両方と一致する場合は、アクセス可能なままです。 この条件は、証明書の有効期限が切れている場合でも当てはまります。 アプリケーションで証明書の有効期限を検証できることを確認してください。

証明書の有効期限の検証を無効のままにする場合は、証明書のロールオーバーのために予定されているメンテナンス期間まで、新しい証明書を作成しないでください。 有効期限が切れている証明書と非アクティブな有効な証明書の両方がアプリケーションに存在する場合、Microsoft Entra IDは有効な証明書を自動的に利用します。 この場合、ユーザーはアプリケーションの停止を経験する可能性があります。

### 証明書の有効期限のメール通知アドレスの追加

Microsoft Entra IDは、SAML 証明書の有効期限が切れる 60 日、30 日前、7 日前に電子メール通知を送信します。 通知を受信するメール アドレスを複数追加できます。 通知の送信先となるメール アドレスを一つ以上指定します。

1. **[SAML 署名証明書**] ページで、**通知メール アドレス**の見出しに移動します。 既定では、この見出しにはアプリケーションを追加した管理者のメール アドレスのみが使われます。
2. 最後のメール アドレスの下に、証明書の有効期限通知を受信する必要のあるメール アドレスを入力し、Enter キーを押します。
3. 追加するメール アドレスごとに前の手順を繰り返します。
4. 削除するメール アドレスごとに、メール アドレスの横にある **[削除** ] アイコン (ごみ箱) を選択します。
5. **[保存] を選択します**。

通知一覧に最大 5 つのメールアドレスを追加できます (アプリケーションを追加した管理者のメール アドレスを含む)。 もっと多くのユーザーに通知する必要がある場合は、配布リストのメールアドレスを使用します。

 azure-noreply@microsoft.com から通知メールを受け取ります。 メールがスパムの場所に入れられるのを避けるため、このメール アドレスをアドレス帳に追加します。

Note

通知メール アドレスが Microsoft Graph または PowerShell を使用してプログラムで構成されている場合、管理者は**エンタープライズ アプリケーション**&gt;**[アプリケーション]**&gt;**Single サインオン**&gt;**SAML** に移動し、[**SAML 証明書**] セクションで**通知メール** アドレスが正しく構成されていることを確認する必要があります。 カスタム SAML 署名証明書を使用するアプリケーションの場合、Microsoft Entra 管理センターで SAML 証明書エクスペリエンスを開くと、証明書通知の登録がまだ存在しない場合は初期化する必要があります。 通知の登録と証明書の情報の更新には時間がかかる場合があります。 管理者は、証明書の有効期限が切れる前に、証明書通知の設定を十分に確認する必要があります。 管理センターで SAML 設定が検証されていない場合、証明書の有効期限通知メールが送信されない可能性があります。

### 有効期限が近づいている証明書を更新する

証明書の有効期限が近づいている場合は、ユーザーの大幅なダウンタイムが発生しない手順を使用して更新できます。 有効期限が近づいている証明書を更新するには:

1. 既存の証明書と重複する日付を使用して、前の「 新しい証明書の作成 」セクションの手順に従います。 その日付は、証明書の期限切れに起因するダウンタイム時間を制限します。
2. アプリケーションが証明書を自動的にロールオーバーできる場合は、以下の手順に従って新しい証明書をアクティブに設定します。

    1. **[SAML 署名証明書**] ページに戻ります。
    2. 新しく保存した証明書の行で、省略記号 (**...**) を選択し、[ **証明書をアクティブにする**] を選択します。
    3. 次の 2 つの手順をスキップします。
3. アプリケーションで一度に 1 つの証明書しか処理できない場合は、ダウンタイムの間隔を選択して次の手順を実行します。 (または、アプリケーションが新しい証明書を自動的に取得しなくても、複数の署名証明書を処理できる場合は、いつでも次の手順を実行できます)
4. 古い証明書の有効期限が切れる前に、前述の「 証明書のアップロードとアクティブ化 」セクションの手順に従ってください。 Microsoft Entra IDで新しい証明書が更新された後にアプリケーション証明書が更新されない場合、アプリケーションでの認証が失敗する可能性があります。
5. アプリケーションにサインインして、証明書が正しく動作することを確認します。

アプリに証明書の有効期限の検証がなく、証明書がMicrosoft Entra IDとアプリの両方と一致する場合は、アクセス可能なままです。 この条件は、証明書の有効期限が切れている場合でも当てはまります。 アプリケーションで証明書の有効期限を検証できることを確認してください。

### 証明書のローテーションに関する ISV のガイダンスとベスト プラクティス

このセクションでは、SAML 証明書の有効期限が近づいている場合や、アプリケーションが Microsoft Entra ID とフェデレーションされている場合に、独立系ソフトウェア ベンダー (ISV) が自動証明書ロールオーバーを有効にするために採用できるベスト プラクティスについて説明します。 Entra IDの SAML 証明書は、フェデレーション シングル サインオン (SSO) でアサーションに署名するために使用されます。 これらの証明書は有効期限が切れ (通常は 1 年から 3 年ごと) です。ローテーションでは、ダウンタイムなしで両方のシステムで相互証明書を更新するために、顧客と SaaS ISV の調整が必要です。 業界の傾向では、証明書の有効期間が短縮され、手動ロールオーバー プロセスによって運用上の負担が増え、サービスの中断がリスクにさらされます。特に、多くの SAML エンタープライズ アプリケーションを持つ大規模な組織では発生します。

大まかに言えば、推奨されるロールオーバー モデルは、Microsoft Entra IDで新しい署名証明書を生成 (またはアップロード) し、SAML アプリケーションがアプリケーションのフェデレーション メタデータ エンドポイントを介して自動的に検出することに依存します。 アプリケーションでは、メタデータを定期的にダウンロードし、新しいキーがまだアクティブでない間に新しく検出された証明書をセカンダリ署名証明書として追加し、Microsoft Entra IDでアクティブ化した後、シームレスにプライマリに昇格させる必要があります。 新しい証明書を使用すると、古い証明書をMicrosoft Entra IDとアプリケーションの両方から安全に削除でき、ダウンタイムなしでローテーションを完了できます。

この自動化を有効にするには、ISV で次の操作を行う必要があります。

1. テナントごと、アプリケーションごとのメタデータ URL を使用したMicrosoft Entra IDフェデレーション メタデータのインジェストをサポートする
2. API (リスト/読み取り、追加/削除、プライマリ/セカンダリ昇格) を使用して証明書ライフサイクル管理を公開します。

Microsoftでは、ダウンタイムを回避し、少なくとも 24 時間ごとにメタデータを監視し、証明書管理に最小特権アクセスを適用し、必要に応じて新しい証明書が検出されたときに管理者に通知するために、ISV で複数の署名証明書 (プライマリとセカンダリ) をサポートすることをお勧めします。 お客様側では、証明書の有効期限の監視、交換証明書の作成またはアップロード、Microsoft Entra IDでの新しい証明書のアクティブ化、API または UI を使用したアプリケーションのプライマリ証明書の更新の調整、および廃止された証明書の削除を行うために、自動化された操作が引き続き必要です。 また、お客様は、秘密キーマテリアル (PFX ファイルなど) と、自動化によって使用されるサードパーティの資格情報もセキュリティで保護する必要があります。

業界標準によって証明書の最長有効期間が短縮されるため、手動での証明書のロールオーバーは不可能になります。特に、多くの SAML エンタープライズ アプリケーションを使用する大規模な組織では実行できなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/understand-microsoft-sso-model"} -->
## Microsoftの SSO モデルを理解する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/understand-microsoft-sso-model
- Service: entra-id / enterprise-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra IDが SAML プロトコルと OpenID Connect プロトコルの両方の一元化された ID プラットフォームとしてシングル サインオン (SSO) を実装する方法について説明します。

Microsoft Entra IDは、中央の ID プラットフォームです。 さまざまな認証プロトコルを使用するアプリ間でシングル サインオン (SSO) が提供されます。 この記事では、Microsoft Entra IDがプラットフォーム レベルで SSO を実装する方法と、それがアプリの統合に何を意味するかを説明します。

### 中央 ID プラットフォームとしてのMicrosoft Entra ID

Microsoft Entra IDは中央 ID プロバイダーです。 ユーザーを認証し、アプリに ID トークンを発行します。 ユーザーに 1 つのサインイン エクスペリエンスを提供し、ID 情報をアプリに配信するためのさまざまなプロトコルをサポートします。

ユーザーは、Microsoft Entra ID経由で 1 回サインインします。 その後、プラットフォームは、再びサインインするように要求することなく、多くのアプリに ID をアサートできます。 認証ポリシー、セキュリティ制御、ユーザー資格情報は 1 か所で提供されます。 各アプリは、独自のプロトコル形式で ID 情報を受け取ります。

重要な考え方は、認証がプラットフォーム全体で統合されていることです。 Security Assertion Markup Language (SAML) や OpenID Connect (OIDC) などのプロトコルは、認証後にその ID 情報をパッケージ化して配信する方法でのみ異なります。

### SAML と OIDC Microsoft Entra認証を処理する方法

Microsoft Entra IDでは、アプリが [SAML](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol) と [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) のどちらを使用しているかに関係なく、同じ認証ステージが使用されます。

#### 共有認証ステージ

**ユーザー認証**: Microsoft Entra IDはユーザー資格情報を検証します。 ユーザーは、パスワード、多要素認証、またはパスワードなしの方法でサインインできます。 この手順は、どのプロトコルを使用する場合でも、すべてのアプリで同じです。

**ポリシーの適用**: プラットフォームは、条件付きアクセス ポリシー、デバイスコンプライアンス、およびその他のセキュリティ制御をチェックします。 これらのチェックは、サインイン後、Microsoft Entra IDがトークンまたはアサーションを発行する前に実行されます。

**ID 決定**: Microsoft Entra IDは、含める ID 情報を決定します。 これは、アプリの構成と、ユーザーのプロファイルとグループ メンバーシップに基づいて行われます。 詳細については、「 [アプリケーションとユーザーの認証」を](https://learn.microsoft.com/ja-jp/entra/architecture/authenticate-applications-and-users)参照してください。

#### プロトコルの相違

認証とポリシーチェックの後、2 つのプロトコルは ID 情報のパッケージ化と配信方法が異なります。

**SAML アプリケーションは、** XML 形式で SAML アサーションを受け取ります。 各アサーションは、ID 情報と属性を保持し、デジタル署名され、SAML プロトコル メッセージで送信されます。

**OpenID Connect アプリケーションは、** ID 要求を保持する JSON Web トークン (JWT) を受け取ります。 これらのトークンは、構造と配信の [OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) と OpenID Connect 仕様に従います。

認証と承認の決定は同じです。 アプリのプロトコルに基づいて、形式と配信のみが異なります。

### 共通の強制およびポリシーレイヤー

Microsoft Entra IDは、使用するプロトコルに関係なく、すべてのアプリに同じセキュリティ ポリシーとアクセス制御を適用します。 この強制は、Microsoft Entra IDがトークンまたはアサーションを発行する前に行われます。

**[条件付きアクセス ポリシーでは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)** 、ユーザーの場所、デバイスのコンプライアンス、サインイン リスク、アプリの機密性などの要因が考慮されます。 ポリシーでは、追加の認証を要求したり、アクセスをブロックしたり、設定された条件下でアクセスを許可したりする場合があります。 プラットフォームでは、SAML アプリと OpenID Connect アプリに対して、これらのポリシーが同様に適用されます。

**[多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)** は、サインイン時にプロトコル固有のトークンが発行される前に適用されます。 アプリで SAML または OIDC のどちらを使用する場合でも、割り当てられたポリシーに基づいて、ユーザーには同じ MFA プロンプトが表示されます。

**デバイス ベースのポリシーは** 、デバイスのコンプライアンス、登録状態、およびデバイスベースの条件付きアクセス規則を確認します。 これらのチェックはサインイン中に実行され、すべてのアプリ プロトコルに同じ方法が適用されます。

この共有ポリシー レイヤーは、使用する SAML と OpenID Connect の組み合わせに関係なく、すべてのアプリでセキュリティ制御とアクセスの決定の一貫性を維持します。

### 構成モデル: アプリケーション、テナント、プロトコルの設定

Microsoft Entra IDでは、構造化された構成モデルを使用して、アプリの統合とプロトコル固有の設定を管理します。 テナントは、組織の専用のMicrosoft Entra ID インスタンスです。

**[アプリケーション オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)** は、アプリのグローバル定義です。 アプリケーション オブジェクトは、アプリの認証要件、サポートされているプロトコル、および基本的な構成を保持します。 アプリで実行できる操作を定義します。

**サービス プリンシパルは、特定の** テナント内のアプリを表します。 だれかがテナントにアプリを追加すると、Microsoft Entra IDによってサービス プリンシパルが作成されます。 サービス プリンシパルは、テナント固有の構成、ユーザーの割り当て、およびプロトコル設定を保持します。 アプリ オブジェクトとサービス プリンシパルは別々であるため、アプリは異なる顧客テナントで異なる構成を使用できます。

**プロトコル構成** はサービス プリンシパル レベルで実行されるため、SAML と OpenID Connect の設定はテナント固有です。 これらの設定には、次のものが含まれます。

- SAML アサーションの属性と NameID の形式
- OpenID Connect のスコープとクレームのマッピング
- リダイレクト URI とプロトコル固有のエンドポイント
- トークン署名の証明書とキーの構成

この構成モデルにより、ISV アプリは [複数のテナントを](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps)サポートでき、それぞれに独自のプロトコル設定と構成があります。

### サポートされているエンドポイントとプロトコル

Microsoft Entra IDは、テナント固有のエンドポイントを提供します。 アプリは、認証とトークン要求にこれらのエンドポイントを使用します。

**[OpenID Connect エンドポイントは、標準の](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)** OAuth 2.0 および OpenID Connect パターンに従います。 テナント固有の URL には、テナント識別子が含まれます。 これらのエンドポイントは、承認要求とトークン要求を処理します。 また、検出メタデータも発行されるため、アプリは自動的に構成できます。

**[SAML 構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)** では、メタデータベースの検出が使用されます。 Microsoft Entra IDは、使用可能なエンドポイント、証明書情報、プロトコル機能を一覧表示するテナント固有の SAML メタデータを発行します。 アプリは、このメタデータを読み取って SAML 設定を自動的に構成します。

どちらのプロトコルもテナント スコープのエンドポイントを使用するため、各テナントには認証用の独自の URL セットがあります。 この分離により、各要求が適切な組織コンテキストに保持され、トークンが正しいテナント固有の情報を確実に保持します。

### サポートされている SSO パターンとフローの開始

Microsoft Entra IDでは、[SAML](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol) アプリと OpenID Connect アプリの両方について、サービス プロバイダー (SP) によって開始されるフローがサポートされます。 SP によって開始されるフローでは、ユーザーはアプリから開始します。 その後、アプリはサインインするMicrosoft Entra IDにリダイレクトします。

SAML アプリの場合、Microsoft Entra IDでは ID プロバイダー (IdP) によって開始されるフローもサポートされます。 ユーザーは、マイ アプリ ポータルなどの Microsoft Entra ID から操作を開始し、Microsoft Entra ID は、すでに認証済みのアサーションを伴って、そのアプリへユーザーをリダイレクトします。

OpenID Connect は [、OAuth 2.0 承認フローに](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)従います。 通常、Web アプリは承認コード フローを使用し、モバイル アプリとシングルページ アプリはそれに合ったフローを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/update-or-remove-app-gallery"} -->
## Microsoft Entra アプリ ギャラリーからアプリを更新または削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/update-or-remove-app-gallery
- Service: entra-id / enterprise-apps
- Article date: 2026-08-24
- Summary: アプリケーションの一覧を更新する要求を送信する方法、または Microsoft Entra アプリ ギャラリーからアプリケーションを削除する方法について説明します。

アプリケーション更新要求は[、Microsoft アプリケーション ネットワーク ポータル](https://microsoft.sharepoint.com/teams/apponboarding/Apps)で送信できます

[アクセスの要求] ページが表示された場合は、業務上の正当な理由を入力し、[アクセスの要求] を選択します。

アカウントが追加されたら、Microsoft Application Network ポータルにサインインし、ホーム ページで [要求の送信 (ISV)] タイルを選択して要求を送信し、ギャラリーで [アプリケーションの一覧を更新] を選択し、選択に従って次のいずれかのオプションを選択できます。

1. アプリケーションの SSO 機能を更新する場合は、[アプリケーションのフェデレーション SSO 機能を更新する] を選択します。
2. パスワード SSO 機能を更新する場合は、[アプリケーションのパスワード SSO 機能の更新] を選択します。
3. 一覧を Password SSO から Federated SSO にアップグレードする場合は、[Upgrade my application from Password SSO to Federated SSO]\(パスワード SSO からフェデレーション SSO へのアプリケーションのアップグレード\) を選択します。
4. MDM 登録情報を更新する場合は、[MDM アプリの更新] を選択します。
5. 既存のユーザー プロビジョニング統合を更新する場合は、[アプリケーションのユーザー プロビジョニング機能の向上] を選択します。
6. Microsoft Entra アプリケーション ギャラリーからアプリケーションを削除する場合は、[ギャラリーから自分のアプリケーション一覧を削除する] を選択します。

ログイン中にサインインがブロックされたというエラーが表示された場合は、「[Microsoft アプリケーション ネットワーク ポータルへのサインインのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/troubleshoot-app-publishing)」を参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/user-admin-consent-overview"} -->
## ユーザーと管理者の同意の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview
- Service: entra-id / enterprise-apps
- Article date: 2025-03-18
- Summary: Microsoft Entra ID におけるユーザーと管理者の同意の基本的な概念について説明します。

同意とは、アプリケーションが保護されたリソースにアクセスすることを、ユーザーが許可するプロセスです。 必要なアクセス レベルを示すために、アプリケーションは必要な API アクセス許可を要求します。 たとえば、アプリケーションは、サインインしているユーザーのプロファイルを表示し、ユーザーのメールボックスの内容を読み取るアクセス許可を要求できます。

同意はさまざまな方法で開始できます。 たとえば、ユーザーが初めてアプリケーションにサインインしようとするときに、同意を求めるメッセージを表示できます。 必要なアクセス許可によっては、一部のアプリケーションでは、管理者が同意を付与することが必要になる場合があります。

この記事では、Microsoft Entra ID におけるユーザーと管理者の同意に関する基本的な概念とシナリオについて説明します。

### ユーザーの同意

ユーザーは、そのユーザーとして機能しながら、保護されたリソースにある一部のデータにアクセスすることをアプリケーションに対して承認できます。 この種類のアクセスを許可するアクセス許可は、"委任されたアクセス許可" と呼ばれます。

ユーザーの同意は、ユーザーがアプリケーションにサインインするときに開始されます。 ユーザーがサインイン資格情報を指定すると、同意が既に付与されているかどうかが確認されます。 必要なアクセス許可に対するユーザーまたは管理者の同意の以前のレコードが存在しない場合、ユーザーは同意プロンプト ウィンドウに移動して、要求されたアクセス許可をアプリケーションに付与します。

管理者以外のユーザーによる同意は、アプリケーションに対してユーザーの同意が許可されている組織と、アプリケーションが必要とする一連のアクセス許可に対する場合にのみ可能です。 ユーザーの同意が無効になっている場合、または要求されたアクセス許可に対してユーザーの同意が許可されていない場合、ユーザーは同意を求められません。 ユーザーが同意を許可され、要求されたアクセス許可を受け入れる場合、同意は記録されます。 通常、ユーザーは同じアプリケーションへの将来のサインイン時にもう一度同意する必要はありません。

#### ユーザーの同意設定

ユーザーが自分のデータを制御しています。 特権管理者は、管理者以外のユーザーがアプリケーションにユーザーの同意を付与できるかどうかを構成できます。 この設定では、アプリケーションとアプリケーションの発行元の側面、要求されるアクセス許可を考慮できます。 ユーザーの同意を構成する手順については、「ユーザーの同意 [設定を構成する」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)を参照してください。

管理者は、ユーザーの同意を許可するかどうかを選択できます。 ユーザーの同意を許可する場合は、ユーザーがアプリケーションに同意する前に満たす必要がある条件を選択することもできます。

すべてのユーザーに適用するアプリケーションの同意ポリシーを選択することにより、ユーザーがアプリケーションに同意することを許可する場合に制限を設けることができます。 また、ユーザーが管理者の確認と承認を要求する必要がある場合も、同意ポリシーによって通知されます。 Microsoft Entra 管理センターには、次の組み込みオプションが用意されています。

- *ユーザーの同意を無効にします。* ユーザーはアプリケーションにアクセス許可を付与することはできません。 ユーザーは、以前に同意したアプリケーション、または管理者が自分に代わって同意を与えたアプリケーションに引き続きサインインします。 ただし、アプリケーションに対する新しいアクセス許可に自分で同意することは許可されません。 同意を付与する権限を含むディレクトリ ロールが付与されているユーザーのみが、新しいアプリケーションに同意できます。
- *ユーザーは、検証済みの発行元または組織のアプリケーションに同意できますが、ユーザーが選択したアクセス許可に対してのみ同意できます*。 すべてのユーザーは、[検証済みの発行元](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)によって発行されたアプリケーションと、テナントに登録されているアプリケーションにのみ同意できます。 ユーザーは、"低影響" として分類されたアクセス許可に対してのみ同意できます。 ユーザーが同意を許可されるアクセス許可を選択するには、[アクセス許可を分類](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-permission-classifications)する必要があります。
- *ユーザーは、すべてのアプリケーションに同意できます*。 すべてのユーザーが、すべてのアプリケーションに対し、管理者の同意を必要としないすべてのアクセス許可に同意することができます。

ほとんどの組織では、1 つの組み込みオプションが適切です。 一部の高度な顧客は、ユーザーが同意を許可される条件をより詳細に制御する必要がある場合があります。 これらの顧客は、[カスタム アプリの同意ポリシーを作成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-app-consent-policies#create-a-custom-app-consent-policy-using-powershell)し、それらのポリシーがユーザーの同意に適用されるように構成することができます。

### 管理者の同意

管理者の同意時に、特権管理者は、他のユーザーに代わって (通常は組織全体に代わって) アプリケーションへのアクセス権を付与できます。 管理者の同意時にも、アプリケーションまたはサービスは API への直接アクセスを提供します。これは、サインインしているユーザーがいない場合にアプリケーションによって使用されます。 管理者の同意を付与するために必要な具体的なロールは、要求されたアクセス許可によって異なります。これについては、[管理者の同意の付与](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent#prerequisites)に関する記事で概説されています。

組織が新しいアプリケーションのライセンスまたはサブスクリプションを購入する場合は、組織内のすべてのユーザーがアプリケーションを使用できるよう、事前にアプリケーションを設定することができます。 ユーザーの同意が必要になるのを回避するために、管理者は組織内のすべてのユーザーに代わってアプリケーションの同意を付与できます。

管理者が組織に代わって管理者の同意を与えた後、ユーザーは、そのアプリケーションに対する同意を求められません。 場合によっては、管理者から同意が付与された後でも、ユーザーに同意を求めるメッセージが表示される場合があります。 たとえば、アプリケーションが管理者にまだ許可していない別のアクセス許可を要求した場合などです。

組織に代わって管理者の同意を付与することは機密性の高い操作であり、組織のデータの大部分、または高度な特権付きの操作を行うアクセス許可にアプリケーションの発行者がアクセスできる可能性があります。 そのような操作の例は、ロール管理、すべてのメールボックスまたはすべてのサイトへのフルアクセス、完全なユーザー偽装などです。

テナント全体の管理者の同意を許可する前に、許可するアクセス レベルに対して、アプリケーションとアプリケーションの発行元を信頼していることを確認します。 アプリケーションを制御しているユーザーと、アプリケーションがアクセス許可を要求している理由について、確信を持てない場合は、同意を許可しないでください。

管理者の同意要求の評価に関するガイダンスについては、「 [テナント全体の管理者の同意の要求の評価](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-consent-requests#evaluate-a-request-for-tenant-wide-admin-consent)」を参照してください。

テナント全体の管理者の同意を Microsoft Entra 管理センターから許可する手順については、「[アプリケーションに対してテナント全体の管理者の同意を付与する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)」を参照してください。

#### 特定のユーザーに代わって同意を許可する

管理者は、組織全体に同意を許可するのではなく、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/use-the-api) を使用して、1 人のユーザーに代わって委任されたアクセス許可に同意を許可することもできます。 Microsoft Graph PowerShell を使用した詳細な例については、「[PowerShell を使用して 1 人のユーザーに代わって同意を許可する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-consent-single-user)」を参照してください。

#### アプリケーションへのユーザー アクセスの制限

テナント全体の管理者の同意が既に許可されている場合でも、ユーザーのアプリケーションへのアクセスは制限される可能性があります。 アプリケーションのプロパティを構成してユーザー割り当てによって、アプリケーションへのユーザーのアクセスが制限されるように要求します。 詳細については、[ユーザーとグループの割り当て方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関するページを参照してください。

その他の複雑なシナリオの処理方法など、より詳しい概要については、[Microsoft Entra ID を使用したアプリケーション アクセス管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management)に関する記事を参照してください。

### 管理者の同意ワークフロー

管理者の同意ワークフローでは、ユーザーが自分で同意できない場合に、アプリケーションに対して管理者の同意を要求する方法が提供されます。 管理者の同意ワークフローが有効になっている場合、ユーザーには、アプリケーションへのアクセスに対する管理者の承認を要求する [承認が必要] ウィンドウが表示されます。

ユーザーが管理者の同意要求を送信すると、レビュー担当者として指定された管理者は通知を受け取ります。 レビュー担当者が要求に応じて行動した後、ユーザーに通知されます。 Microsoft Entra 管理センターを使用して管理者の同意ワークフローを構成する手順については、「[管理者の同意ワークフローの構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)」を参照してください。

#### ユーザーが管理者の同意を要求する方法

管理者の同意ワークフローを有効にすると、ユーザーはユーザーの同意が許可されていないアプリケーションについて管理者の承認を要求できます。 プロセスの手順を次に示します。

- ユーザーがアプリケーションにサインインしようとします。
- "**承認が必要です**" というメッセージが表示されます。 ユーザーは、アプリケーションへのアクセスが必要である理由を入力し、[承認要求] を選択します。
- **[リクエストが送信されました]** というメッセージで、要求が管理者に送信されたことを確認します。ユーザーが複数の要求を送信した場合は、最初の要求のみが管理者に送信されます。
- 要求が承認、拒否、またはブロックされると、ユーザーにメール通知が届きます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/v2-howto-app-gallery-listing"} -->
## アプリを検証して発行するための前提条件 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing
- Service: entra-id / enterprise-apps
- Article date: 2026-09-02
- Summary: Microsoft Entra App Gallery でアプリケーションを検証して公開するための共通の前提条件を確認します。

Microsoft Entra アプリ ギャラリーは、何千ものアプリケーションのカタログです。 Microsoftギャラリーでアプリケーションを発行すると、顧客はそれを検出してテナントに追加できます。 詳細については、「[Microsoft Entra アプリ ギャラリーの概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-application-gallery)」を参照してください。

アプリケーションを検証する前に、共有の前提条件と、発行する予定の各機能の要件を確認します。

### 発行する機能を選択する

アプリケーションに適用される要件を確認します。

- Security Assertion Markup Language (SAML) または OpenID Connect (OIDC) の統合については、[Microsoft Entra アプリ ギャラリーの SSO 要件に関](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-sso-requirements)するページを参照してください。
- System for Cross-Domain Identity Management (SCIM) 統合については、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニング要件](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-user-provisioning-requirements)」を参照してください。

アプリケーションで SSO とユーザー プロビジョニングの両方がサポートされている場合は、両方の機能の要件と検証を完了します。

### 共有の前提条件

アプリケーションを送信する前に、次の前提条件を満たす必要があります。

- [Microsoft Entra アプリ ギャラリーの使用条件](https://azure.microsoft.com/support/legal/active-directory-app-gallery-terms/)を読んで同意します。
- お客様がアクセスできる運用対応アプリケーションを準備します。
- オンボーディングとオンボード後のサポートのためのエンジニアリングとサポートの連絡先を確立します。
- 公開する予定の各機能について、公開顧客のドキュメントを準備します。
- テスト テナントとテスト アカウントを作成します。 [Microsoft 365開発者プログラム](https://learn.microsoft.com/ja-jp/office/developer-program/microsoft-365-developer-program)に参加して、Microsoft Entra機能を備えた再生可能な開発サブスクリプションを取得できます。
- 組織を[Microsoft AI Cloud Partner Program](https://partner.microsoft.com/partnership)に関連付けます。
- 組織に関連付けられている**パートナー One ID (以前の Microsoft Partner Network (MPN) ID) を**指定します。 この識別子は、Microsoft Entra アプリ ギャラリーのオンボードおよび発行プロセス中に使用されます。

### パートナー ワンID

パートナー One ID は、Microsoft AI Cloud Partner Program内の組織を識別します。 Microsoft Entra App Gallery でアプリケーションの発行元として表示される組織に関連付けられている Partner One ID を指定してください。

Note

パートナー One ID がわからない場合は、組織のMicrosoft AI Cloud Partner Program管理者に問い合わせてください。 パートナー One ID とパートナー アカウントの詳細については、 [パートナー センターのドキュメントを参照してください](https://learn.microsoft.com/ja-jp/partner-center/)。

### 顧客のドキュメントを準備する

明確なドキュメントは、顧客が統合を構成してサポートするのに役立ちます。 次の情報を含めます:

- サポートされている機能、プロトコル、バージョン、SKU。
- ライセンス要件。
- 統合を構成するために必要なロール。
- 構成とテストの手順。
- エラー コードやメッセージなどのトラブルシューティング情報。
- サポート オプション。

SSO とユーザー プロビジョニングの要件に関する記事では、含める機能固有の情報について説明します。

### アプリケーションを公開する

アプリケーションが該当する要件を満たし、検証に合格したら、「[アプリ ギャラリーにアプリを発行する」Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/publish-app-gallery)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/validate-oidc-multitenant-app-gallery"} -->
## Microsoft Entra アプリ ギャラリーのオンボード用に OIDC マルチテナント アプリを検証する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-oidc-multitenant-app-gallery
- Service: entra-id / enterprise-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra App Validator ブラウザー拡張機能を使用して、OpenID Connect マルチテナント アプリを検証し、アプリ ギャラリーの発行用のテスト ID を生成します。

OpenID Connect (OIDC) マルチテナント アプリケーションを Microsoft Entra アプリ ギャラリーに発行する前に、サインイン実装がギャラリーの要件を満たしていることを検証する必要があります。 検証は、アプリが発行する準備ができていることを確認し、提出に必要なテスト ID を生成するセルフサービス ステップです。

Microsoft Entra アプリ検証ツールは、アプリケーションへのサインイン時に、OIDC マルチテナント サインイン フローを Microsoft Entra アプリ ギャラリーの要件に照らして評価します。

検証後は、次の内容が表示されます。

- 必要な修正プログラムと推奨される修正プログラムを強調表示する検証レポート
- Microsoft Entra アプリ ギャラリーにアプリを発行するために必要なテスト ID

### 前提条件

検証を開始する前に、次のことが既に当てはまることを確認します。

- アプリは、[Microsoft Entra IDのマルチテナント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps)として登録されます。
- [OIDC サインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) が実装され、正常に動作します。
- アプリがデプロイされ、パブリック URL 経由でアクセスできます。
- リダイレクト URI、スコープ、およびアクセス許可が構成されています。
- Microsoft アカウントを使用して正常にサインインできます。
- アプリケーション内の既存のセッションからサインアウトしている。
- ギャラリー申請 ID。最初にギャラリー申請を作成します (下記参照)。 これは、検証結果を送信する前に必要になります。

Important

これらの前提条件が満たされていない場合、検証は失敗します。 検証を試みる前に、OIDC 実装を完了してテストします。

### Microsoft ID プラットフォーム v1 エンドポイントはサポートされていません

Microsoft Entra App Gallery のセルフサービス オンボーディングは、Microsoft ID プラットフォーム v2 の OpenID Connect (OIDC) エンドポイントを使用するアプリケーションに対応しています。

Microsoft ID プラットフォーム v1 エンドポイントを使用するアプリケーションは、セルフサービス オンボード エクスペリエンスでは検証できません。 Microsoftでは、セルフサービス検証、発行、ライフサイクル管理機能を利用するために、v2 エンドポイントに移行することをお勧めします。

v2 エンドポイントは、 `openid`、 `profile`、 `email`などの標準 OIDC スコープを含むスコープ ベースの承認モデルを使用し、セルフサービス エクスペリエンスを通じて検証できる一貫した承認とトークン モデルを提供します。 また、増分同意などの機能もサポートされており、アプリケーションは必要に応じて委任されたアクセス許可を要求できます。

Microsoft ID プラットフォーム v1.0 と v2.0 の ID トークンは、公開される要求とトークンのセマンティクスが異なります。 詳細については、「 [ID トークン要求リファレンス」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-token-claims-reference)。

Note

Microsoft ID プラットフォーム v1 エンドポイントを使用するように構成されたアプリケーションは、セルフサービス オンボード エクスペリエンスでは検証できません。 検証を開始する前に、v2 エンドポイントに移行します。

### ギャラリーの申請 ID

検証を開始する前に、Microsoft Entra ギャラリーの申請を作成し、その申請 ID をコピーします。 検証結果を送信するには、この ID が必要です。

申請 ID を検索するには:

1. Microsoft Entra 管理センターで、Microsoft Entra ギャラリーにアプリケーションを発行するプロセスを開始します。
2. [ **アプリケーションの発行] を選択します**。 すでに下書きの申請がある場合は、代わりに**公開済みのアプリ**を選択してください。

    [Image: [アプリケーションの発行] オプションを示すMicrosoft Entra 管理センターのスクリーンショット。]
3. **統合の種類**の手順に移動します。

    [Image: 申請 ID を示す [統合の種類] ステップのスクリーンショット。]
4. **送信 ID**の下に表示されている値をコピーしてください。

提出 ID は GUID です。 検証コントロールの実行中は、この ID を使用できます。

検証コントロールが提出 ID を要求したとき:

1. 申請が現在サインインしているテナントに属している場合は、**あなたの申請のいずれかを選択** からそれを選択します。
2. 申請が別のテナントに属している場合は、代わりに提出 ID をフィールドに貼り付けます。

一覧に提出物が表示されない場合でも、ID を手動で貼り付けることができます。

Important

有効なギャラリー申請 ID がないと、検証結果を送信できません。

### Microsoft Entra App Validator ブラウザー拡張機能をインストールする

検証を実行するには、Microsoft Entra App Validator ブラウザー拡張機能が必要です。

1. **Microsoft Edge** を開きます。
2. [Get Entra App Validator](https://microsoftedge.microsoft.com/addons/detail/entra-app-validator/iglkgnbeekgcnlapikofffkhkoldbaoj) に移動します。
3. [ **取得]** を選択してインストールを開始します。
4. メッセージが表示されたら、[ **拡張機能の追加**] を選択します。
5. **Entra App Validator** が拡張機能の一覧に表示され、有効になっていることを確認します。

[Image: Microsoft Entra App Validator 拡張機能がインストールされ、ツール バーにピン留めされているMicrosoft Edgeのスクリーンショット。]

### 新しい検証セッションを開始する

拡張機能にサインインし、アプリケーションのサインイン ページを開始点として確認して、検証セッションを開始します。

1. 新しいブラウザー タブを開き、アプリケーションのサインイン ページに移動します。 これは、ユーザーが Microsoft Entra ID 経由でサインインするときに使用するページと同じである必要があります。
2. 既にサインインしている場合は、完全にサインアウトして、検証セッションがクリーンであることを確認します。
3. ブラウザーのツール バーから **Entra App Validator** 拡張機能アイコンを選択します。
4. Microsoft アカウントを使用して拡張機能にサインインします。
5. [ **テストの開始] を選択します**。
6. メッセージが表示されたら、開始ページを確認し、[ **はい]、[テストの開始]** の順に選択します。

拡張機能は検証セッションを初期化し、実行するチェックを一覧表示します。

[Image: [テストの開始] ボタンとテスト初期化画面を示す Microsoft Entra App Validator 拡張機能のスクリーンショット。]

### OIDC 認証フローを実行する

検証コントロールがアプリケーションの OIDC 認証フローをキャプチャして評価できるように、Microsoft アカウントでサインインします。

1. バリデーターは、アプリが**Microsoft でサインイン**のエントリ ポイントを提供していることを確認します。 これは、OIDC ベースのギャラリー アプリに必要です。
2. メッセージが表示されたら、[ **認証**] を選択します。
3. Microsoft アカウントを使用して、Microsoftサインイン プロセスを完了します。

認証中、検証コントロールは次を評価します。

- Microsoft サインイン エントリ ポイントの設定
- テナント エンドポイントの使用状況 (マルチテナント アプリの`/common` または `/organizations` )
- OIDC v2.0 エンドポイントコンプライアンス
- `openid` と `profile` を含む必要なスコープ
- 承認要求の構造とパラメーター

Tip

最も一般的な検証エラーは、シングルテナント エンドポイント、必要なスコープの不足、リダイレクト URI の不一致です。 発行する前に、これらの問題を修正する必要があります。

### 検証レポートを確認する

認証が完了した後:

1. Microsoft Entra アプリ 検証機能拡張機能で、[**プレビュー レポート**] を選択します。
2. 次のセクションを確認します。
    - **実行されたテスト** – 実行された検証チェック
    - **合格したテスト** – アプリが満たした要件
    - **特定された問題** - ブロックと非ブロッキングの問題
    - **キャプチャされた認証データ** – OIDC フローからの詳細
    - **推奨事項** – 問題を解決するためのガイダンス

[Image: 実行されたテスト、成功したテスト、特定された問題、および推奨事項のセクションを示す Microsoft Entra アプリ 検証コントロール レポートのスクリーンショット。]

レポートにブロッキングの問題が表示されている場合は、アプリでそれらを解決し、必要なすべてのチェックが成功するまで検証を再実行します。

Important

発行する前に、ブロックの問題を解決する必要があります。 非ブロッキングの問題は推奨事項であり、送信を妨げることはありません。

### アプリでユーザーを識別する方法を宣言する

送信する前に、検証コントロールは、サインインしているユーザーを独自のシステムのユーザー レコードと照合するためにアプリケーションが使用するトークンから、どの識別子を要求します。 オブジェクト ID (oid)、ユーザー プリンシパル名 (UPN)、電子メール アドレス、従業員 ID、オンプレミスの SAM アカウント名、名前付きカスタム要求など、アプリケーションが使用するすべての識別子の種類を選択します。

### これが求められる理由

メール アドレスなどの変更可能なセルフアサート可能な値でのみユーザーを照合すると、アカウント引き継ぎリスクが発生する可能性があります。 攻撃者が以前に別のユーザーに関連付けられた電子メール アドレスを取得し、アプリケーションがそのメール アドレスのみに依存してアカウントの照合を行った場合、攻撃者は既存のアプリケーション アカウントと照合される可能性があります。 このリスクを軽減するために、バリデーターは変更可能な識別子を単独で受け入れません。 アプリケーションで変更可能な識別子を使用する場合は、アプリケーションがユーザーを識別するためにも使用する不変識別子と共に選択します。

[カスタム要求] を選択した場合は、要求の名前を指定する必要があります。 名前付きカスタム要求は単独で十分であり、追加の識別子は必要ありません。

### 検証を送信してテスト ID を取得する

アプリケーションが必要なすべての検証チェックに合格したら、次の手順を実行します。

1. 検証レポートで、[ **送信]** を選択します。
2. 検証が成功したことを証明する一意の **テスト ID が** 生成されます。
3. テスト ID を保存します。 ギャラリーを公開する際に必要です。

テスト ID は期限付きであり、2 週間後に期限切れになります。

Important

有効なテスト ID がないと、Microsoft Entra アプリ ギャラリーへの公開を進めることはできません。

[Image: 生成されたテスト ID を表示する検証の送信確認のスクリーンショット。]

検証が完了し、テスト ID が生成されたら、アプリを Microsoft Entra アプリ ギャラリーに発行する準備ができました。 検証が失敗した場合は、続行する前に、レポートを確認し、ブロックの問題を解決し、検証を再実行します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/validate-saml-single-sign-on-app-gallery"} -->
## Microsoft Entra アプリ ギャラリーのオンボード用に SAML シングル サインオン アプリを検証する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-saml-single-sign-on-app-gallery
- Service: entra-id / enterprise-apps
- Article date: 2026-08-19
- Summary: Microsoft Entra App Validator ブラウザー拡張機能を使用して SAML 2.0 シングル サインオン統合を検証してから、Microsoft Entra App Gallery のオンボード用のアプリケーションを送信します。

Microsoft Entra App Validator ブラウザー拡張機能を使用して、アプリケーションの Security Assertion Markup Language (SAML) 2.0 シングル サインオン (SSO) 統合を検証してから、Microsoft Entra App Gallery のオンボード用にアプリケーションを送信します。 検証コントロールは、アプリケーションへのサインインを観察し、プロトコルの証拠をキャプチャし、シナリオの完了を追跡し、送信の結果をパッケージします。

Important

検証コントロールは、SAML 署名を暗号で検証したり、Microsoft Entraテナント構成を変更したり、アプリケーションのテスト スイートを置き換えたりすることはありません。 アプリケーションは、署名を検証し、その認証と承認の要件を適用する責任を負います。

このチュートリアルでは、以下の内容を学習します。

- SAML 検証用のMicrosoft Entra環境を準備します。
- SAML SSO 用にギャラリー以外のエンタープライズ アプリケーションを構成します。
- Entra アプリ バリデーターをインストールして開きます。
- ID プロバイダー (IdP) によって開始され、サービス プロバイダー (SP) によって開始される SAML フローを検証します。
- 証明書の動作と期限切れの証明書のシナリオを実行します。
- 必要に応じて、単一ログアウトを検証します。
- 検証結果を確認して送信します。

### 前提条件

開始する前に、次の内容があることを確認します。

- Microsoft Entra のテナント。
- **クラウド アプリケーション管理者**、**アプリケーション管理者**、またはサービス プリンシパルの所有権など、エンタープライズ アプリケーションを管理するアクセス許可。
- アプリケーションに割り当てることができるテスト ユーザー アカウント。
- Microsoft Edge。
- テスト ユーザーがアクセスできるアプリケーション サインイン エンドポイント。
- アプリケーションで想定される SAML 値:
    - **識別子 (エンティティ ID)**
    - **応答 URL (Assertion Consumer Service URL)**
    - アプリケーションで SP Initiated SSO がサポートされている場合は、**サインオン URL**
- ギャラリーの申請 ID。 有効な申請 ID がないと検証結果を送信できないため、検証を開始する前にギャラリー申請を作成します。

Important

検証には、非運用テナントと非運用アプリケーションを使用します。 有効期限が切れた証明書のシナリオでは、テスト対象のアプリケーションへのサインインが意図的に中断されます。

### ギャラリーの申請 ID を取得する

検証を開始する前に、Microsoft Entra ギャラリーの申請を作成し、その申請 ID をコピーします。

1. Microsoft Entra 管理センターで、Microsoft Entra ギャラリーにアプリケーションを発行するプロセスを開始します。
2. [ **アプリケーションの発行] を選択します**。 既に下書きの提出がある場合は、[ **発行済みのアプリケーション**] を選択します。

    [Image: [アプリ ギャラリーに発行] オプションが強調表示され、その下に [公開済みアプリケーション] が表示されている [Microsoft Entra アプリ ギャラリーの参照] ページのスクリーンショット。]
3. **統合の種類**の手順に移動します。

    [Image: [提出 ID] フィールドが強調表示されている [統合の種類] ステップの [ギャラリーへのアプリケーションの発行] ワークフローのスクリーンショット。]
4. **送信 ID**の下に表示されている値をコピーしてください。

申請 ID は、 `11680398-5b31-4e47-a61d-191e7e638e85`などの GUID です。 バリデーターを実行している間は、それを利用可能な状態のままにしておいてください。

検証コントロールが提出 ID を要求したとき:

- その提出物が、現在ログインしているテナントのものである場合は、**申請から 1 つ選択する**から選択してください。
- 申請が別のテナントに属している場合は、フィールドに申請 ID を貼り付けます。

一覧に申請が表示されない場合でも、申請 ID を手動で貼り付けることができます。

Important

有効なギャラリー申請 ID がないと、検証結果を送信できません。

### アプリケーションを構成する

検証コントロールは、ギャラリー以外のエンタープライズ アプリケーションとしてテナントに存在するアプリケーションをテストします。

#### アプリケーションを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[エンタープライズ アプリケーション]** に移動します。
3. [**新しいアプリケーション**] を選択&gt;**独自のアプリケーションを作成します**。
4. アプリケーションの名前を入力します。
5. **ギャラリーに見つからないその他のアプリケーションを統合する (ギャラリー以外)** を選択します。
6. **を選択して**を作成します。

#### SAML シングル サインオンを設定

1. 作成したエンタープライズ アプリケーションを開きます。
2. **[シングル サインオン]** を選択します。
3. **[SAML]** を選択します。
4. **基本的な SAML 構成**で次の値を構成します。

| Field | Description |
| --- | --- |
| **識別子 (エンティティ ID)** | アプリケーションが自身を識別してMicrosoft Entra IDするために使用する識別子。 |
| **応答 URL (ACS URL)** | Microsoft Entra IDが SAML 応答を投稿するエンドポイント。 |
| **サインオン URL** | アプリケーションのサインイン ページ。 アプリケーションで SP Initiated SSO がサポートされている場合は、この値を構成します。 |

Note

**サインオン URL** はテスト動作に影響します。 サインオン URL が構成されている場合、マイ アプリ タイルはアプリケーションのサインイン ページにリダイレクトし、SP によって開始されるサインインを生成できます。 マイ アプリから IdP によって開始される SSO をテストするには、そのテスト実行のサインオン URL をクリアします。 アプリケーションで SP Initiated SSO もサポートされている場合は、後で復元します。

#### 要求を構成する (必要な場合)

アプリケーションで追加の属性、カスタム要求、アプリケーション ロール、または特定の NameID 形式が必要な場合は、アプリケーションの**シングル サインオン** ページの **[属性] & [要求**] で構成します。

#### テスト ユーザーの割り当て

1. エンタープライズ アプリケーションを開きます。
2. ユーザーおよびグループの選択
3. [ **ユーザー/グループの追加]** を選択します。
4. テスト ユーザーをアプリケーションに割り当てます。
5. [マイ アプリ](https://myapps.microsoft.com)開き、アプリケーション タイルが表示されることを確認します。

マイ アプリ タイルは IdP によって開始される検証に必要です。これは、そのフローがMicrosoft Entra IDから開始されるためです。

### Entra アプリ バリデーターをインストールして開く

1. Microsoft Edge アドオンから [Entra アプリ 検証ツール](https://microsoftedge.microsoft.com/addons/detail/entra-app-validator/iglkgnbeekgcnlapikofffkhkoldbaoj)をインストールします。
2. 拡張機能をブラウザーのツール バーにピン留めします。
3. 拡張アイコンを選択して検証コントロールを開きます。
4. 検証コントロールで、[ **テスト** ] タブを開きます。
5. **SAML 2.0 を選択します**。
6. 新しいテストを作成するか、既存のテストを選択して続行します。

テストの進行状況は自動的に保存されるため、検証コントロールを閉じて、後でテストを再開できます。

### 検証する SAML フローを選択する

アプリケーションでサポートされているサインイン フローを選択します。

- IDP Initiated
- SP Initiated
- 両方とも

[Image: Microsoft Entraエンタープライズ アプリケーションの横にある Entra アプリ 検証コントロールを示すスクリーンショット。検証コントロールは、IdP サインインと SP サインインが選択された状態で、アプリケーションがサポートする SAML サインイン フローを確認します。]

検証シナリオとして提供されるのは、宣言したフローだけです。 実行が既にキャプチャされているフローを後で削除すると、検証コントロールは、宣言した機能と一致しなくなったため、それらの実行を削除します。

### SAML シナリオについて

各 SAML シナリオは、同じプロセスに従います。シナリオを選択し、アプリを開き、サインインをキャプチャしてから、実行を確認して保存します。

| Scenario | 検証内容 | 必須 |
| --- | --- | --- |
| 有効な証明書 | 有効な SAML 署名証明書を使用した通常のサインイン。 | イエス |
| 期限切れの証明書 | アサーションが期限切れの証明書で署名されている場合のアプリケーションの動作。 | イエス |
| 単一ログアウト | サインアウト後のログアウト要求キャプチャ。 | いいえ |

作業証明書と有効期限切れの証明書のシナリオのみが SAML の完了にカウントされます。 シングル ログアウトは省略可能であり、送信をブロックしません。

### サポートされている SAML の機能と動作について

検証結果には、アプリケーションでサポートされている機能と、テストを選択した機能が反映されます。 実装に応じて、検証済みの SAML 機能には次のものが含まれます。

- IdP 起点のシングル サインオン
- SP が起点となるシングル サインオン
- シングル ログアウト (SLO)
- アプリケーション固有の要求とユーザー識別子のサポート

>
> アプリケーションが実装する機能のみを選択します。 実装または検証されていない機能は、サポートされている機能として表すべきではありません。

### IdP によって開始された検証を実行する

IdP によって開始されるサインオンは、Microsoft Entra IDから開始されます。 ユーザーはマイ アプリでアプリケーション タイルを選択し、Microsoft Entra IDアプリケーションの ACS URL に要求されていない SAML 応答を投稿します。

[Image: SAML 2.0 の [Entra App Validator Test] タブのスクリーンショット。IdP サインイン マイ アプリ シナリオが拡張され、必要な作業証明書と期限切れの証明書のシナリオと、オプションのシングル ログアウト シナリオが表示されます。]

1. 検証コントロールで、 **IdP によって開始される作業証明書** のシナリオを選択します。
2. 検証コントロールによって表示されるマイ アプリ タイルからアプリケーションを選択します。
3. キャプチャを開始します。
4. マイ アプリ開いたら、アプリケーション タイルを選択します。
5. サインインを完了します。
6. バリデーターで、確認されたサインイン結果を確認してください。
7. 検証の実行を確認して保存します。

予想される観測値には、アプリケーション タイル、ACS URL に投稿された未承諾の SAML 応答、Microsoft以外のランディング ページ、結果の手動確認が含まれます。

Tip

キャプチャされた応答に `InResponseTo` 値が含まれている場合、サインインは IdP によって開始されるのではなく、SP によって開始されました。 `InResponseTo`値は、アプリケーションからの`AuthnRequest`への応答を結び付けます。

### SP によって開始される検証を実行する

SP によって開始される SSO は、アプリケーションから開始されます。 アプリケーションによって、ブラウザーが SAML 要求を使用してMicrosoft Entra IDにリダイレクトされます。 ユーザーの認証後、Microsoft Entra IDは ACS URL に SAML 応答を投稿します。

[Image: SAML 2.0 の [Entra App Validator Test] タブのスクリーンショット。アプリのログイン ページ の SP サインイン シナリオが拡張され、必要な作業証明書と期限切れの証明書のシナリオと、オプションのシングル ログアウト シナリオが表示されます。]

1. 検証コントロールで、 **SP によって開始される作業証明書** のシナリオを選択します。
2. アプリケーションを識別します。
3. アプリケーションのサインオン URL を入力します。
4. キャプチャを開始します。
5. 検証コントロールによって開かれたブラウザー タブでサインインを完了します。
6. 実際のサインイン結果を確認します。
7. 検証の実行を確認して保存します。

予想される観察としては、アプリケーションのサインオン URL の読み込み、Microsoft Entra IDに送信された SAML 要求、ACS URL で受信した SAML 応答、Microsoft以外のランディング ページ、結果の手動確認などがあります。

### 期限切れ証明書の検証を実行

「有効期限が切れた証明書」のシナリオでは、アサーションが有効期限が切れた証明書で署名されているサインインを、アプリケーションが拒否するという証拠が記録されます。 このシナリオは、作業証明書シナリオに使用したのと同じアプリケーションに対して実行します。

Caution

このシナリオでは、作業中の証明書を復元するまで、テスト対象のアプリケーションのサインインが中断されます。 この検証用に作成されたギャラリー以外のアプリケーションに対してのみこのテストを実行し、アプリケーションが他のユーザーまたはワークロードによって使用されていないことを確認します。

検証を完了するには、次の手順に従います。

1. Microsoft Entra 管理センターでエンタープライズ アプリケーションを開きます。
2. [ **シングル サインオン**&gt;**SAML 証明書**] を選択します。
3. 既に有効期限が切れている承認済みのテスト証明書をインポートします。
4. 期限切れの証明書をアクティブにします。
5. 構成の変更が適用されるまで待ちます。
6. 検証コントロールで、 **期限切れの証明書シナリオを** 実行します。
7. サインインを完了します。
8. アプリケーションでサインインが失敗することを確認します。
9. 動作している証明書を復元します。

>
> 予期される結果: SAML アサーションが期限切れの証明書で署名されている場合、アプリケーションはサインイン試行を拒否する必要があります。
>
> 期限切れの証明書で署名されたアサーションを使用してアプリケーションがユーザーを認証し続ける場合は、この動作がセキュリティまたは実装の問題を示す可能性があるため、アプリケーションの証明書検証ロジックを確認します。

注: テストの目的で、有効期限が過去の自己署名証明書を生成し、このシナリオで使用できます。 検証を実行する前に、証明書を SAML 証明書にインポートし、アクティブにします。 期限切れの自己署名証明書を生成する テスト用に期限切れの自己署名証明書を生成するには、PowerShell Windows開き、次のスクリプトを実行します。

パスワード: ExpiredCert-TestOnly-123!

```powershell
# Test-only PFX password
$password = ConvertTo-SecureString `
    "ExpiredCert-TestOnly-123!" `
    -AsPlainText `
    -Force

# Create a certificate that was valid from two years ago
# until one year ago, meaning it is already expired.
$cert = New-SelfSignedCertificate `
    -Type Custom `
    -Subject "CN=Expired-Entra-SAML-Test" `
    -FriendlyName "Expired Entra SAML Test" `
    -CertStoreLocation "Cert:\CurrentUser\My" `
    -KeyAlgorithm RSA `
    -KeyLength 2048 `
    -HashAlgorithm SHA256 `
    -KeyExportPolicy Exportable `
    -KeyUsage DigitalSignature `
    -NotBefore (Get-Date).AddYears(-2) `
    -NotAfter (Get-Date).AddYears(-1)

# Export certificate and private key for importing into Entra
Export-PfxCertificate `
    -Cert $cert `
    -FilePath "$PWD\expired-saml-signing.pfx" `
    -Password $password

# Optional: export only the public certificate
Export-Certificate `
    -Cert $cert `
    -FilePath "$PWD\expired-saml-signing.cer"

# Confirm its dates
$cert | Format-List `
    Subject,
    Thumbprint,
    NotBefore,
    NotAfter,
    HasPrivateKey
```

期限切れの証明書をアップロードしてアクティブ化してから 5 ~ 10 分待ってから、シナリオを実行する前に変更が反映されるようにします。 これにより、前の証明書ではなく、新しくアクティブ化された証明書が実行によって確実にキャプチャされます。 期限切れの証明書が 5 分から 10 分後に反映されない場合は、シナリオを実行する前に、期限切れでない証明書を削除します。

[Image: Entra アプリ検証コントロールの SAML 署名の詳細のスクリーンショット。有効な値の証明書は EXPIRED とマークされます。]

Note

専用のテスト証明書とテスト アプリケーションを使用します。 このシナリオでは、運用環境の署名証明書を使用しないでください。

### 必要に応じて、単一ログアウトを検証する

アプリケーションで SAML シングル ログアウトがサポートされている場合は、 **単一ログアウト** シナリオを実行します。

1. バリデーターを使用してサインインします。
2. アプリケーションからサインアウトします。
3. バリデーターがログアウト要求をキャプチャできるようにします。
4. 結果を保存します。

アプリケーションでシングル ログアウトがサポートされていない場合は、[ **マイ アプリでサポートされていません**] を選択します。 単一ログアウトのスキップは記録され、送信はブロックされません。

### 検証結果を確認して送信する

実行のたびに、提出前にキャプチャされた証拠を確認します。 SAML 実行の場合、検証コントロールはデコードされた要求と応答の詳細、アサーション情報、要求、証明書の詳細、署名情報を表示できます。

送信する前に、アプリケーションでユーザーを識別する方法を宣言します。 検証コントロールは、オブジェクト ID、ユーザー プリンシパル名 (UPN)、電子メール アドレス、従業員 ID、オンプレミスの SAM アカウント名、名前付きカスタム要求など、ユーザー レコードへのサインインを照合するためにアプリケーションが使用するトークン値を確認します。

Important

ユーザーと一致させるために、メール アドレスなどの変更可能な値だけに依存しないでください。 メール アドレスとユーザー名は変更または再割り当てできます。 変更可能な値でのみ照合すると、以前のユーザーの識別子を継承したユーザーがそのユーザーのアプリケーション アカウントにアクセスできるようになります。

アプリケーションで変更可能な識別子を使用する場合は、オブジェクト ID などの変更できない識別子とペアにします。 アプリケーションがユーザーを識別する方法であれば、名前付きカスタム要求で十分です。

SAML の場合、送信には次のものが必要です。

- 宣言された各フローに対する完了済みの**勤務証明書**シナリオ。
- 各宣言済みフローに対する、完了した**期限切れの証明書**シナリオ。
- 必要なシナリオごとに手動で結果を確認します。
- アイデンティティマッピング宣言。

シングル ログアウトは省略可能であり、送信をブロックしません。

### 一般的な問題のトラブルシューティング

#### アプリケーション タイルがマイ アプリに表示されない

テスト ユーザーをアプリケーションに割り当ててから、マイ アプリ再読み込みします。 割り当てられていないアプリケーションはマイ アプリに表示されません。IdP によって開始される検証は、マイ アプリ タイルから開始されます。

#### 「IdP主導」を選択しましたが、応答に `InResponseTo` が含まれています

`InResponseTo`を含む応答は、アプリケーションからの要求に関連付けられます。つまり、サインインは SP によって開始されます。 一般的な原因は、構成された**サインオン URL** です。これにより、マイ アプリ タイルがアプリケーションのサインイン ページにリダイレクトされる可能性があります。

#### 有効期限が切れた証明書の実行で、古い証明書がキャプチャされました

証明書の変更がまだ反映されていない可能性があります。 伝達時間とクリーンアップについては、承認済みの製品手順に従い、シナリオを再実行します。

#### サインインが失敗し、 `AADSTS` エラーが発生する

まず、バリデーターのインライン診断を確認してください。

| エラー コード | Meaning | 提案されているアクション |
| --- | --- | --- |
| `AADSTS750054` | Microsoft Entra ID へのリクエストに SAML リクエストが見つかりませんでした。 | アプリケーションが `SAMLRequest` クエリ パラメーターを使用して`AuthnRequest`を送信することを確認します。 |
| `AADSTS50011` | 応答 URL の不一致。 | アプリケーションによって要求された ACS URL がアプリケーションの応答 URL リストにあることを確認します。 |
| `AADSTS700016` | アプリケーションが見つかりません。 | 識別子 (エンティティ ID) がテナント内のアプリケーションと一致することを確認します。 |

その他のトラブルシューティングについては、Microsoft Entraサインイン ログと SAML トラブルシューティング ガイダンスを使用してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/validate-user-provisioning-app-gallery"} -->
## Microsoft Entra アプリ ギャラリーのユーザー プロビジョニングを検証する (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery
- Service: entra-id / enterprise-apps
- Article date: 2026-08-26
- Summary: SCIM プロビジョニングと Azure Logic Apps テンプレートの統合を検証し、Microsoft Entra App Gallery のオンボードの結果を送信します。

Microsoft Entra App Gallery でユーザー プロビジョニングをサポートするアプリケーションを発行するには、System for Cross-Domain Identity Management (SCIM) エンドポイントが Microsoft Entra プロビジョニング サービスと連携していることを示す必要があります。 自分のスケジュールに従って、エンドポイントに対して一連の自動テストを実行し、ギャラリーの提出で結果を送信します。

検証では、Microsoftが提供するAzure Logic Apps テンプレートが使用されます。 このテンプレートは、ユーザー プロビジョニング、グループ プロビジョニング、SCIM コンプライアンス全体で 25 個のテストを実行し、エンドポイントが正しく処理した操作を報告します。

完了すると、検証に合格したことを示す Logic App の実行 ID と、Microsoft がギャラリー提出と併せてレビューする提出済みの結果一式が得られます。

### 前提条件

- [Microsoft Entra アプリ ギャラリーのユーザー プロビジョニング要件を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-user-provisioning-requirements)満たす SCIM エンドポイント。 まだエンドポイントを構築している場合は、「 [SCIM エンドポイントのプロビジョニングの開発と計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)」を参照してください。
- Microsoft Entra のテナント。 作成するには、 [新しいテナントの作成に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)参照してください。
- 少なくともそのテナントの**アプリケーション管理者**ロール。
- アプリケーションがグループ プロビジョニングのみをサポートしている場合は、[Microsoft Entra ID P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)。 試用版ライセンスが動作し、Microsoft Entra ID P1 は Microsoft 365 E3 および E5 に含まれています。
- 少なくとも[ロジック アプリ共同作成者](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-securing-a-logic-app?tabs=azure-portal)ロールを持つ、同じテナント内のAzure サブスクリプション。 このテンプレートでは [Standard ホスティング モデル](https://learn.microsoft.com/ja-jp/azure/logic-apps/single-tenant-overview-compare)が使用されるため、従量課金制サブスクリプションでは、通常は 1 か月あたり 10 米ドル未満の小さなコストが予想されます。
- SCIM エンドポイントのベアラー トークン。少なくとも 24 時間有効なままです。 検証の実行には 60 ~ 90 分かかるため、有効期間の短いトークンでは途中でテストが失敗します。

Note

有効期間の長いベアラー トークンは、検証でのみ許容されます。 プロビジョニング統合をギャラリーに発行するには、アプリケーションで OAuth 2.0 クライアント資格情報の付与またはワークロード ID フェデレーションがサポートされている必要があります。 詳細については、 [SCIM 認証の要件](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-user-provisioning-requirements#scim-authentication-requirements)に関するページを参照してください。

### 検証方法を選択する

どちらのメソッドでも、Microsoftに送信したロジック アプリの実行と同じ結果が生成されます。 自動化するセットアップの量に基づいて選択します。

| - | エージェント | Azure portal |
| --- | --- | --- |
| **どのように機能するのか** | AI エージェントは、リソースを作成し、ロジック アプリをデプロイし、テストを実行し、会話を通じてエラーを診断します。 | 各リソースを作成し、Azure ポータルとMicrosoft Entra 管理センターでロジック アプリを自分で構成します。 |
| **Time** | 30 ~ 60 分 | 1 ~ 3 時間 |
| **必要なスキル** | AI チャット ツールに関する知識 | Azure portal、Microsoft Entra 管理センター、PowerShell、または Azure CLI |
| **最適な用途** | セットアップの高速化と、修正可能なエラー後の自動再試行 | すべてのステップを完全に制御 |

ロジック アプリを自分で設定するには、[Azure ポータルで検証ロジック アプリを設定する方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-logic-app)に関する説明を参照してください。

### SCIM オンボード エージェントを使用して検証する

SCIM オンボード エージェントは、任意の AI コーディング エージェントを実行できる命令ファイルです。 エージェントのホストとモデルを指定します。Microsoftはエージェントを提供します。

エージェントはエンドポイントの詳細を要求し、Microsoft Entra アプリケーションとAzure リソースを作成し、ロジック アプリをデプロイし、テストをトリガーして、結果を報告します。 また、エラーが修正された後も自動的に再試行されます。

#### エージェント ホストを準備する

1. [Azure CLI](https://aka.ms/installazurecli)をインストールしてサインインします。

    ```azurecli
    az login
    ```
2. ファイルの読み取りと書き込み、CLI コマンドの実行、会話の保持を行うことができる AI コーディング エージェントを選択します。 GitHub Copilot、Cursor、Windsurf、Cline、Claude Code を使用したVisual Studio Codeはすべて機能します。
3. 対応するモデルを選択します。 エージェントの理由は複数のステップにまたがっているので、より小さいモデルや古いモデルでは、必要な入力をスキップしたり、エラーを診断せずに再試行したりする可能性があります。

    | プロバイダー | 推奨される最小モデル |
    | --- | --- |
    | Anthropic | Claude Opus 4 以降 |
    | OpenAI | GPT-4.1 以降 |
    | Google | Gemini 2.5 Pro 以降 |
4. SCIMReferenceCode リポジトリから [scim-onboarding.agent.md](https://github.com/AzureAD/SCIMReferenceCode/blob/master/Microsoft.SCIM.LogicAppValidationTemplate/StandardLogicApp/scim-onboarding.agent.md) をダウンロードします。
5. プロジェクト フォルダーを作成し、ホストが検出する場所にエージェント ファイルを配置します。 GitHub Copilotの場合は、`.github/agents/`を使用します。

    ```
    C:\scim-validation\
    └── .github\
        └── agents\
            └── scim-onboarding.agent.md
    ```
6. エージェント ホスト内のフォルダーを開きます。 GitHub Copilotは、`.github/agents/`内のエージェントを自動的に検出するため、Copilot Chatで`@scim-onboarding`を使用してエージェントを呼び出すことができます。 他のホストの場合は、システム プロンプトまたはカスタム命令としてファイルを読み込みます。

#### 検証を実行する

1. エージェントに次のメッセージを送信します。

    ```
    Validate my SCIM integration for Entra app gallery onboarding
    ```
2. エージェントの質問に回答します。 各コマンドを実行する前に確認して承認します。

    | Question | 提供する内容 |
    | --- | --- |
    | SCIM エンドポイント URL | SCIM 2.0 エンドポイントのベース URL ( `https://api.myapp.com/scim/v2`など)。 `aadOptscim062020`機能フラグは含めないでください。 このフラグは、Microsoft Entra 管理センターの **[テナント URL**] フィールドでのみ構成します。 |
    | ベアラー トークン | 少なくとも 24 時間有効なトークン。 |
    | 認証方法 | 静的ベアラー トークン、または OAuth クライアント資格情報。 クライアント資格情報を選択した場合、エージェントはクライアント ID、クライアント シークレット、トークン エンドポイント、スコープも要求します。 |
    | Azure サブスクリプション | ロジック アプリ リソースが作成されるサブスクリプション。 エージェントが 1 つしかない場合は、自動的に選択されます。 |
    | 属性マッピング | Microsoft Entra で作成された既定の設定をそのまま使用するか、Microsoft Entra 管理センターでそれらをカスタマイズして、エージェントに戻ります。 テストは、最終的なスキーマを確認するまで開始されません。 |
    | 属性値の制限 | SCIM サーバーによって制限される値（`Engineer` が `Manager`、`Director`、または `jobTitle` に制限される場合など）。 エージェントは、最初に `/Schemas` エンドポイントを確認します。 報告されていない制限により、スキーマ検証エラーが発生します。 |
3. 実行が終了するまで待ちます。 エージェントはリソースを作成し、テストをトリガーし、合格したテストと失敗したテストを報告します。

エージェントが修正できる理由でテストが失敗した場合、エージェントは修正プログラムを適用し、確認せずに再実行します。 エージェントは、誤った`aadOptscim062020`機能フラグ、Microsoft Graph権限の不足、正規の値の不一致、および`defaultUserProperties`内のフィールドの欠落を処理します。 サポートされていない SCIM フィルターや空のクエリの 404 など、お客様側でエラーが発生した場合、エージェントはエンドポイントで何を変更するかについて説明します。

### 検証結果を確認する

ロジック アプリでは、7 つのユーザー テスト、7 つのグループ テスト、11 個の SCIM コンプライアンス テストの 25 個のテストが実行されます。 アプリケーションでサポートされている内容が検出され、適用されないテストはスキップされます。 各テストの説明については、 [SCIM 検証テストの概要](https://github.com/AzureAD/SCIMReferenceCode/blob/master/Microsoft.SCIM.LogicAppValidationTemplate/StandardLogicApp/SCIM-Validation-Test-Overview.md)を参照してください。

エージェントが代わりに結果を取得して表示します。 Azure ポータルでロジック アプリを設定した場合は、ロジック アプリを開き、**ワークフロー**&gt;**Orchestrator\_Workflow&gt;** **実行履歴**に移動し、実行を選択し、`Final_TestResults`アクションを選択して、[**生出力の表示**] を選択します。

各結果は次のようになります。

```json
{
  "testName": "Create_User_Test",
  "testResult": "success",
  "provisioningErrorDetails": "",
  "recommendationUrl": "",
  "runLink": "https://portal.azure.com/#view/...",
  "message": "Click the runLink and search for the action Compose_Final_Results for more info."
}
```

| フィールド | それがあなたに伝えるもの |
| --- | --- |
| `testName` | `Create_User_Test`や`Update_Group_Test`など、実行されたテスト。 |
| `testResult` | `success` テストに合格した場合 失敗した場合、`FAILED - [Delete Phase] Failed Action: Delete_Step5_Delete_Group_By_Id` などの、失敗したフェーズとアクション。 |
| `provisioningErrorDetails` | 成功した場合は空です。 失敗した場合、Microsoft Graphまたは SCIM 呼び出しからの HTTP 状態コード、応答本文、およびエラー メッセージ。 このフィールドは、デバッグに最も役立ちます。 |
| `recommendationUrl` | 問題の解決に役立つ可能性があるドキュメントへのリンク。 |
| `runLink` | 子ワークフローへの直接リンクは、Azure ポータルで実行されます。 |
| `message` | ワークフロー実行の詳細を確認する場所。 |

`skipped`の`testResult`値は、前提条件が満たされなかったことを意味します。 たとえば、マネージャー属性がターゲット スキーマにない場合、 `User_Update_Manager_Test` はスキップされ、OAuth が構成されていない場合は `Federated_Identity_Test` はスキップされます。

### 一般的な検証エラーのトラブルシューティング

検証テストが失敗した場合は、 `provisioningErrorDetails`、 `recommendationUrl`、ロジック アプリの実行の詳細を確認して、原因を理解します。

| 故障領域 | 考えられる原因 | 推奨されるアクション |
| --- | --- | --- |
| Authentication | 無効なベアラー トークン | 資格情報を確認し、検証を再実行する |
| ユーザーの作成 | 必要な属性がありません | SCIM スキーマとマッピングを確認する |
| グループ プロビジョニング | グループ エンドポイントが実装されていません | SCIM グループのサポートを確認する |
| SCIM コンプライアンス | SCIM 応答形式が無効です | SCIM 2.0 の要件を確認する |

### 受け渡しの意味を理解する

提出する前に、該当するすべてのテストに合格する必要があります。 次の例外はオンボーディングをブロックしません。

- `Validate_Credentials_Test` または `Federated_Identity_Test` が失敗するのは、静的なベアラー トークンを使用したためです。 2 つのうち少なくとも 1 つに合格する必要があり、運用環境には OAuth またはワークロード ID フェデレーションが必要です。
- `User_Update_Manager_Test` マネージャー属性がターゲット ディレクトリ スキーマにないため、 `SCIM_Update_Manager_Test` はスキップされます。
- `Delete_User_Test`、 `Delete_Group_Test`、 `Restore_Group_Test`、および `SCIM_Group_Pagination_Test` は警告を返します。 これらのテストは省略可能です。

これらの例外のため、統合を送信する準備ができた場合でも、 `overallResult` は `Failed` を読み取ることができます。 全体的な値に依存するのではなく、個々のテスト結果を確認します。

ここに記載されていない理由でテストが失敗した場合は、「 [ユーザー プロビジョニング検証のトラブルシューティング」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/troubleshoot-user-provisioning-validation)参照してください。

### ロジック アプリの実行 ID を検索する

該当するすべてのテストが成功した実行の実行 ID を送信します。

1. [Azure portal](https://portal.azure.com) にサインインする
2. **ロジック アプリ**を検索し、検証に使用するロジック アプリを選択します。
3. **「ワークフロー」**&gt;**Orchestrator\_Workflow**を選択します。

    [Image: Orchestrator_Workflowが選択されている [ロジック アプリ ワークフロー] ペインのスクリーンショット。]
4. [ **実行履歴]** を選択し、状態が **[成功]** の最新の実行を見つけて、[ **識別子** ] 列のコピー アイコンを選択します。

    [Image: 成功した実行識別子の横にあるコピー アイコンが強調表示されたOrchestrator_Workflow実行履歴のスクリーンショット。]
5. 実行が渡されたことを確認するには、識別子のリンクを選択し、デザイナー ビューで `Final_TestResults` ステージまでスクロールします。 エラーなしで完了したステージは、実行がすべての検証テストに合格したことを意味します。

    [Image: 最終的な TestResults ステージが正常に完了したことを示すOrchestrator_Workflow デザイナー ビューのスクリーンショット。]

### 検証結果を送信する

送信すると、結果がMicrosoftに使用できるようになり、ギャラリーの申請にリンクされます。 この手順は、エージェントを使用したか、Azure ポータルでロジック アプリを設定したかに関係なく必要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/?feature.enableSelfServiceOnboardingDeveloperPortal=true&amp;feature.enableSupportabilityAssistant=true&amp;feature.consoletelemetry=true&amp;feature.enableInitialContextHandoff=true&amp;Microsoft_AAD_DXP=stagepreview&amp;dxpEndpoint=TIP#view/Microsoft_AAD_IAM/EntraLanding.ReactView)にサインインします。
2. **エンタープライズ アプリケーション**&gt;**すべてのアプリケーション**を参照し、検証したアプリケーションを選択します。 エージェントによってこのアプリケーションが自動的に作成されるか、Microsoft Entra 管理センターで作成されます。
3. [**プロビジョニング**]&gt;**[検証結果の送信**]、[**検証結果の送信]** の順に選択します。

    [Image: [検証結果の送信] コマンドが強調表示されている [検証結果の送信] ウィンドウのスクリーンショット。]
4. [ **検証結果** ] タブで、提出 ID を入力します。 この ID は、検証結果をギャラリーの申請にマップします。 見つけるには、「[Microsoft Entra アプリ ギャラリーにアプリを発行する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/publish-app-gallery)」を参照してください。

    [Image: [送信 ID] フィールドと [ロジック アプリの実行] セクションを示す [検証結果] タブのスクリーンショット。]
5. [ **ロジック アプリの実行**] で [ **はい**] を選択し、使用したロジック アプリのサブスクリプション、リソース グループ、名前を入力します。
6. コピーした実行 ID を入力し、[ **検証**] を選択します。

    [Image: サブスクリプション、リソース グループ、ロジック アプリ、および実行 ID フィールドを示す [ロジック アプリの詳細の送信] フォームのスクリーンショット。]
7. [ **プレビュー** ] タブで、属性マッピングやジョブ設定など、結果から抽出されたアプリケーションの詳細を確認します。 送信後にフォームを編集することはできません。
8. **構成証明** タブで、すべての要件に **はい** と回答します。 これらの構成証明は、ロジック アプリがプログラムで確認できないセキュリティ要件を対象としているため、それらすべてを確認するまで続行することはできません。
9. [ **確認と送信** ] タブで、[ **送信**] を選択します。

送信後、Microsoftは検証結果を提出要求に一致させ、発行ワークフローを続行します。 公開エクスペリエンスで申請のステータスを確認できます。

### リソースをクリーンアップする

検証リソースが不要になった場合は、ロジック アプリを含むリソース グループを削除します。 リソース グループを削除すると、ロジック アプリとそのワークフローが削除され、それ以上のコストは停止されます。

Microsoftが提出の確認を完了するまで、リソースを保持します。 検証後に統合が大幅に変更された場合は、再送信する前にもう一度検証してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/validate-user-provisioning-logic-app"} -->
## Azure ポータルで検証ロジック アプリを設定する (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-logic-app
- Service: entra-id / enterprise-apps
- Article date: 2026-08-26
- Summary: ISV オンボード アプリを作成し、Azure Logic Apps検証テンプレートをデプロイして、Microsoft Entra アプリ ギャラリーの SCIM プロビジョニング統合をテストします。

Azure Logic Apps検証テンプレートは、Microsoft Entra プロビジョニング サービスに対するクロスドメイン ID 管理 (SCIM) プロビジョニング統合のシステムをテストします。 この記事では、リソースを作成し、テンプレートを自分でデプロイする方法について説明します。そのため、すべての手順を完全に制御できます。

独立系ソフトウェア ベンダー (ISV) は、まず、Microsoft Entra 管理センターで ISV オンボード アプリを作成し、SCIM エンドポイントに対してプロビジョニング ジョブを開始します。 次に、ロジック アプリをデプロイし、必要なアクセス許可を付与し、その実行パラメーターを設定して、テストを実行します。

このセットアップを自動化する場合、SCIM オンボード エージェントは、約半分の時間で会話を通じて同じリソースを作成します。 両方の方法の比較については、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニングを検証する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery)」を参照してください。

### 前提条件

- 「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニングの検証](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery#prerequisites)」の前提条件の完了。
- Microsoft Entra テナント内の検証済みドメイン。 テンプレートは、このドメインにテスト ユーザーを作成し、SCIM エンドポイントにプロビジョニングします。
- [ワークフロー](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)の展開とアクセス許可の割り当てに使用する[Azure PowerShellまたはAzure CLI](https://aka.ms/installazurecli)。

### ISV オンボーディング アプリを作成する

ロジック アプリはプロビジョニング ジョブを通じてテストを実行するため、テンプレートをデプロイする前に動作する ISV オンボード アプリが必要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **エンタープライズ アプリケーション**&gt;**新しいアプリケーション**を参照し、**Entra Gallery Provisioning Test App を**検索します。

    [Image: Entra Gallery Provisioning Test App を示す Microsoft Entra App Gallery の検索結果のスクリーンショット。]
3. アプリケーションの名前を入力し、[ **作成**] を選択します。
4. アプリケーションの **[概要** ] ページで、 **オブジェクト ID をコピーします**。 この値は、ロジック アプリの `servicePrincipalId` パラメーターです。

    [Image: [オブジェクト ID] が強調表示されているエンタープライズ アプリケーションの [概要] ページのスクリーンショット。]
5. [ **プロビジョニング]** を選択し、[ **プロビジョニング モード** ] を **[自動**] に設定し **、[新しい構成]** を選択し、OAuth クライアントの資格情報を入力して、[ **テスト接続**] を選択します。
6. **プロビジョニング**&gt;**Mappings**&gt;**プロビジョニング ユーザー**を参照してプロビジョニング ジョブを作成し、スキーマを設定します。

    [Image: [Provisioning Mappings](プロビジョニング マッピング) ペインのスクリーンショット。[Provision Users](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/ユーザーのプロビジョニング) マッピングが表示されています。]
7. SCIM エンドポイントがサポートする属性のみが含まれるようにスキーマを排除します。 [ **詳細オプションの表示**] を選択し、[ **属性リストの編集]** を選択し、エンドポイントに合わせて属性を更新または削除します。

    [Image: アプリケーションがサポートする SCIM 属性を示す属性リスト エディターのスクリーンショット。]

    エンドポイントでサポートされていない属性があると `Schema_Discoverability_Test` は失敗するため、リストは必要最小限に絞ってください。 スキーマをエクスポートするには、 **ここで [スキーマの確認**] を選択し、スキーマ エディターで **[ダウンロード** ] を選択します。 マッピングの詳細については、「属性マッピングの [カスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)」を参照してください。
8. [ **概要** ] ページで、[ **プロビジョニングの開始**] を選択します。 エラーなしでジョブが開始されると、ロジック アプリをデプロイする準備が整います。

    [Image: [プロビジョニングの開始] コマンドが強調表示されている [プロビジョニングの概要] ページのスクリーンショット。]

### ロジック アプリを作成する

ISV オンボード アプリと同じテナントにロジック アプリを作成します。

1. [Azure portal](https://portal.azure.com) にサインインする
2. **サブスクリプションを**検索し、使用するサブスクリプションを選択し、ロジック アプリのリソース グループを作成します。
3. **ロジック アプリ**を検索し、**追加**&gt;**Workflow サービス プラン (Standard)** を選択します。

    [Image: [ワークフロー サービス プラン Standard] オプションが選択されている [ロジック アプリの作成] ウィンドウのスクリーンショット。]
4. 名前を入力し、作成したリソース グループを選択します。
5. [ **ストレージ** ] タブで、 **BLOB サービスの診断設定** を今すぐ構成するように設定し、既定のワークスペースを選択します。
6. 残りの設定は既定値のままにして、[ **確認と作成**] を選択します。
7. デプロイが完了したら、ロジック アプリを開きます。

### 検証ワークフローをデプロイする

テンプレートは、SCIMReferenceCode リポジトリ内のワークフロー定義のセットとして付属しています。 ロジック アプリにアップロードし、指定されたスクリプトを使用してデプロイします。

1. [StandardLogicApp フォルダー](https://github.com/AzureAD/SCIMReferenceCode/tree/master/Microsoft.SCIM.LogicAppValidationTemplate/StandardLogicApp)からすべてのファイルをダウンロードし、それらを 1 つのフォルダーにまとめる。
2. ロジック アプリで、 **開発ツール**&gt;**Logic アプリコード ビュー**を選択し、 `logicAppTemplate.json`の内容を貼り付けて、[ **保存]** を選択します。

    [Image: ワークフロー定義 JSON を示す [ロジック アプリ のコード ビュー] ペインのスクリーンショット。]
3. Azure Cloud Shellまたはローカル ターミナルを開き、ダウンロードしたファイルをアップロードします。
4. [ロジック アプリの **概要** ] ページで、サブスクリプション ID、リソース グループ、およびロジック アプリ名をコピーします。

    [Image: サブスクリプション、リソース グループ、および名前を示す [ロジック アプリの概要] ページのスクリーンショット。]
5. デプロイ スクリプトを実行します。

    ```azurepowershell
    .\Deploy-LogicAppWorkflows.ps1 `
      -SubscriptionId $subscriptionId `
      -ResourceGroup $resourceGroupName `
      -LogicAppName $LogicAppName
    ```
6. 5 つのワークフローがすべてデプロイされていることを確認します。

    [Image: デプロイされた検証ワークフローが一覧表示されている [ロジック アプリ ワークフロー] ペインのスクリーンショット。]

テンプレートでは、入れ子になったワークフロー アーキテクチャが使用されます。 `Orchestrator_Workflow` はエントリ ポイントであり、子ワークフローとして他のユーザーを呼び出します。 `Initialization_Workflow` はテストの実行を準備し、テストを `UserTests_Workflow`、 `GroupTests_Workflow`、および `SCIMTests_Workflow` 保持します。 `Orchestrator_Workflow`の最後のセクションでは、結果を評価します。

### ロジック アプリにアクセス許可を付与する

ロジック アプリはMicrosoft Graphを呼び出して、テスト ユーザーとグループの作成、更新、削除、プロビジョニング ログの読み取りを行います。 マネージド ID を付与し、これらの呼び出しに必要なロールを割り当てます。

1. ロジック アプリで、[**設定]**&gt;**[Identity**] を選択します。
2. [ **システム割り当て済み** ] タブで、[ **状態]** を **[オン] に**設定し、確認ダイアログで **[はい** ] を選択し、[保存] を選択 **します**。

    [Image: システム割り当てマネージド ID の状態が [オン] に設定されている [ID] ペインのスクリーンショット。]
3. マネージド **ID のオブジェクト ID** をコピーします。 アクセス許可スクリプトには、この値が必要です。

    [Image: オブジェクト ID を示すシステム割り当てマネージド ID のスクリーンショット。]
4. **Azure ロールの割り当て**&gt;**ロールの割り当ての追加** を選択し、**所有者** ロールを割り当てます。

    [Image: 所有者ロールが選択されている [ロールの割り当ての追加] ウィンドウのスクリーンショット。]
5. [AssignRolesTOManagedIdentity-LogicApps 1.ps1ダウンロードし](https://github.com/AzureAD/SCIMReferenceCode/blob/master/Microsoft.SCIM.LogicAppValidationTemplate/AssignRolesTOManagedIdentity-LogicApps%201.ps1)、`$miObjId`変数をコピーしたオブジェクト ID に設定して、スクリプトを実行します。

    Azure Cloud Shellで、最初にファイルをアップロードしてから、シェルから実行します。

スクリプトが完了すると、マネージド ID はテストに必要なすべてのロールを保持します。

### 実行パラメーターを設定する

デザイナーで `Orchestrator_Workflow` を開き、[ **パラメーター]** を選択します。 値を更新した後、ロジック アプリを保存します。

[Image: [パラメーター] コマンドが強調表示されている Orchestrator ワークフロー デザイナーのスクリーンショット。]

| パラメーター | 価値 |
| --- | --- |
| `servicePrincipalId` | 作成した ISV オンボード アプリのオブジェクト ID。 |
| `scimEndpoint` | SCIM エンドポイントの URL。 ISV オンボード アプリで使用されている場合でも、 `aadOptscim062020`などの機能フラグは含めないでください。 プロビジョニング構成の **[テナント URL** ] フィールドでのみフラグを構成します。 |
| `scimBearerToken` | 少なくとも 24 時間有効なベアラー トークン。 |
| `testUserDomain` | テナント内の確認済みドメイン。 テストでは、このドメインにユーザーが作成されます。 |
| `defaultUserProperties` | ユーザー プロパティ値のセットが 1 つ以上。 テンプレートは、1 つのセットをランダムに選択してユーザーを作成し、別のセットを更新するため、少なくとも 2 つのセットを指定します。 |
| `EnabledTests` | 実行するテストを制御する 1 つの値。 `All`を使用してすべてを実行するか、`UserTests`、`GroupTests`、または`SCIMTests`を使用して 1 つのワークフローを実行します。 `Create_User_Test`など、個々のテストに名前を付けることもできます。 |
| `scimClientId` | OAuth クライアント ID。 |
| `scimClientSecret` | OAuth クライアント シークレット。 |
| `scimTokenEndpoint` | あなたのOAuthトークンエンドポイント。 |

すべてのテストを完了する実行によって、作成したテスト ユーザーがクリーンアップされます。 途中で実行が失敗した場合、または取り消した場合は、テスト ユーザー スタブがテナントに残る可能性があります。 テストをもう一度実行する前に、それらを削除します。

### テストの実行

1. **ワークフロー**&gt;**Orchestrator\_Workflow**に移動します。
2. デザイナーで、[実行] を選択 **します**。

    [Image: [実行] コマンドが強調表示されている Orchestrator ワークフロー デザイナーのスクリーンショット。]
3. **[実行履歴**] で進行状況を追跡します。 完全な実行には 60 ~ 90 分かかります。

    [Image: 実行の状態と期間を示す Orchestrator ワークフローの実行履歴のスクリーンショット。]
4. 実行を選択し、 `Final_TestResults` アクションを選択し、[ **未加工の出力を表示** ] を選択して、合格したテストを確認します。

出力を解釈し、オンボードをブロックするエラーを理解するには、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニングの検証](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery#review-your-validation-results)」を参照してください。 テストが失敗した場合は、「 [ユーザー プロビジョニング検証のトラブルシューティング」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/troubleshoot-user-provisioning-validation)参照してください。

ValidateLogicAppRun-Standard.ps1を使用してコマンド ラインから [実行を確認](https://github.com/AzureAD/SCIMReferenceCode/blob/master/Microsoft.SCIM.LogicAppValidationTemplate/StandardLogicApp/ValidateLogicAppRun-Standard.ps1)することもできます。

### リソースをクリーンアップする

検証リソースが不要になった場合は、ロジック アプリを含むリソース グループを削除します。 リソース グループを削除すると、ロジック アプリとそのワークフローが削除され、それ以上のコストは停止されます。

Microsoftが提出の確認を完了するまで、リソースを保持します。 また、不完全な実行によって残されたテスト ユーザーを削除し、テストが完了したら、ISV オンボード アプリでプロビジョニング ジョブを停止します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/view-applications-portal"} -->
## クイックスタート: エンタープライズ アプリケーションを表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal
- Service: entra-id / enterprise-apps
- Article date: 2025-03-31
- Summary: Microsoft Entra 管理センターにアクセスして、エンタープライズ アプリを簡単に表示およびフィルター処理できます。 テナント管理を合理化し、ただちに責任を持ちます。

このクイック スタートでは、Microsoft Entra 管理センターを使用して、Microsoft Entra テナントで構成されたエンタープライズ アプリケーションを検索して表示する方法について説明します。

このクイックスタートの手順をテストするには、非運用環境を使うことをお勧めします。

### 前提条件

Microsoft Entra テナントに登録されているアプリケーションを表示するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: クラウド アプリケーション管理者、サービス プリンシパルの所有者。
- [エンタープライズ アプリケーションを追加するクイックスタート](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)の手順を完了していること。

### アプリケーションの一覧を表示する

テナントに登録されているエンタープライズ アプリケーションを表示するには、次のようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。 [Image: Microsoft Entra テナントに登録されているアプリケーションを表示する。]
3. 他のアプリケーションを表示するには、一覧の一番下にある **[さらに読み込む]** を選択します。 テナント内に多数のアプリケーションがある場合は、一覧をスクロールするよりも特定のアプリケーションを検索する方が簡単な場合があります。

### アプリケーションを検索する

特定のアプリケーションを検索するには:

1. **[アプリケーションの種類]** フィルター オプションを選択します。 **[アプリケーションの種類]** ドロップダウン メニューで **[すべてのアプリケーション]** を選択し、**[適用]** を選択します。
2. 検索するアプリケーションの名前を入力します。 アプリケーションが既に Microsoft Entra テナントに追加されている場合は、それが検索結果に表示されます。 たとえば、前のクイック スタートで使用した **Microsoft Entra SAML Toolkit 1** アプリケーションを検索できます。
3. アプリケーション名の最初の数文字を入力してください。

### 表示オプションを選択する

探しているものに応じてオプションを選択します。

1. 既定のフィルターは、**アプリケーションの種類**と**Application ID の先頭文字列**です。
2. **[アプリケーションの種類]**で、次のいずれかのオプションを選択します。
    - **[エンタープライズ アプリケーション]** には、Microsoft 以外のアプリケーションが表示されます。
    - **[Microsoft アプリケーション]** には、Microsoft アプリケーションが表示されます。
    - **[マネージド ID]** には、Microsoft Entra 認証をサポートするサービスの認証に使用されるアプリケーションが表示されます。
    - **エージェント ID (プレビュー)** には、Microsoft Entra 認証をサポートするサービスに対する認証に AI エージェントによって使用される AI エージェント ID が表示されます。
    - **[すべてのアプリケーション]** には、Microsoft 以外のアプリケーションと Microsoft アプリケーションの両方が表示されます。
3. アプリケーション ID がわかっている場合は、**Application ID の先頭文字列**に、アプリケーション ID の最初の数桁を入力します。
4. 必要なオプションを選択したら、 **[適用]** を選択します。
5. **[フィルターの追加]**を選択して、検索結果をフィルター処理するオプションを追加します。 その他のオプションには、次のものが含まれます。
    - **アプリケーションの状態**
    - **アプリケーションの可視性**
    - **[作成日]**
    - **必須の割り当て**
    - **Is App Proxy (アプリ プロキシ)**
    - **所有者**
    - **識別子 URI (エンティティ ID)**
    - **ホームページ URL**
6. 既に追加されているフィルター オプションを削除するには、フィルター オプションの横にある **[X]** アイコンを選択します。

### リソースをクリーンアップする

クイック スタート全体で使用された **Microsoft Entra SAML Toolkit 1** という名前のテスト アプリケーションを作成した場合は、今すぐ削除してテナントをクリーンアップすることを検討できます。 詳細については、[アプリケーションの削除](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/ways-users-get-assigned-to-applications"} -->
## ユーザーをアプリに割り当てる方法を理解する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/ways-users-get-assigned-to-applications
- Service: entra-id / enterprise-apps
- Article date: 2021-01-07
- Summary: ID 管理のために Microsoft Entra ID を使用するアプリにユーザーを割り当てる方法について説明します。

この記事では、ユーザーがテナントでアプリケーションに割り当てられる方法について説明します。

### Microsoft Entra ID でユーザーをアプリケーションに割り当てる方法

アプリケーションをユーザーに割り当てるには、いくつかの方法があります。 割り当ては管理者またはビジネス デリゲートによって行われますが、場合によってはユーザー自身で実行できることもあります。 次に、ユーザーをアプリケーションに割り当てることができる方法について説明します。

- 管理者が、アプリケーションに直接[ユーザーを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。
- 管理者が、アプリケーションにユーザーがメンバーとなっている[グループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。次のグループが含まれます。

    - オンプレミスから同期されたグループ
    - クラウドで作成された静的なセキュリティ グループ
    - クラウドで作成された[動的なセキュリティ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)
    - クラウドで作成された Microsoft 365 グループ
    - [すべてのユーザー](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups) グループ
- 管理者が [\[アプリケーションのセルフ サービス アクセス\]](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access) を有効にして、[ビジネス承認なし](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)でユーザーが**マイ アプリ**の **[アプリの追加]** 機能を使用してアプリケーションを追加することを許可します
- 管理者が [\[アプリケーションのセルフ サービス アクセス\]](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access) を有効にして、ユーザーが[マイ アプリ](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)の **[アプリの追加]** 機能を使用してアプリケーションを追加することを許可しますが、**選択された一連のビジネス承認者からの事前の承認があった**場合に限ります
- 管理者が [\[セルフサービスによるグループ管理\]](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management) を有効にして、アプリケーションが**ビジネス承認なし**で割り当てられているグループにユーザーが参加することを許可します。
- 管理者が [\[セルフサービスによるグループ管理\]](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management) を有効にして、アプリケーションが割り当てられているグループにユーザーが参加することを許可しますが、**選択された一連のビジネス承認者からの事前の承認があった**場合に限ります。
- アプリケーションのロールの 1 つが[エンタイトルメント管理アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources)に含まれており、ユーザーがそのアクセス パッケージを要求するか、そのアクセス パッケージに割り当てられています
- 管理者は、[Microsoft 365](https://www.microsoft.com/microsoft-365) などの Microsoft サービスのライセンスを直接ユーザーに割り当てます
- 管理者は、Microsoft サービスのライセンスを、ユーザーがメンバーとなっているグループに割り当てます。
- ユーザーは、自身の代わりに[アプリケーションに同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview#user-consent)します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/what-is-access-management"} -->
## アプリへのアクセスを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management
- Service: entra-id / enterprise-apps
- Article date: 2024-08-25
- Summary: Microsoft Entra ID により、組織が各ユーザーがアクセスするアプリをどのように指定できるかついて説明します。

アプリを組織の ID システムに統合すると、アクセス管理、使用状況評価、レポートに課題が生じます。 通常、IT 管理者またはヘルプ デスクのスタッフがアプリへのアクセスを監視する必要があります。 アクセスの割り当ては、一般または部門の IT チームに任せることができますが、理想的には、IT がプロセスを完了する前に、ビジネス上の意思決定者が関与して承認を与える必要があります。

他の組織は、既存の自動 ID との統合に投資し、ロール ベースの Access Control (RBAC)、属性ベースの Access Control (ABAC) などの管理システムにアクセスします。 統合とルールの開発はいずれも専門知識や高いコストが求められる傾向にあります。 いずれの管理方法でも、監視またはレポートには別途コストがかかり、複雑な投資が必要になります。

### Microsoft Entra ID の活用方法

Microsoft Entra ID では、構成済みのアプリケーション用に広範なアクセスの管理がサポートされているため、組織は、属性に基づく自動的な割り当て (ABAC または RBAC シナリオ) から、委任、また管理者の管理までにわたり、適切なアクセス ポリシーを簡単に達成できます。 Microsoft Entra ID を使用すると、1 つのアプリケーションに対して複数の管理モデルを組み合わせて複雑なポリシーを簡単に達成できるだけでなく、同じ対象ユーザーに対して、アプリケーション全体で管理ルールを再利用することもできます。

Microsoft Entra ID では、使用量と割り当てのレポートが完全に統合されるため、管理者は割り当ての状態、割り当てに関するエラーのほか、使用量に関して簡単にレポート作成できます。

#### ユーザーとグループをアプリに割り当てる

Microsoft Entra のアプリケーション割り当ては、次の 2 つの主要な割り当てモードが中心となります。

- **個別の割り当て** ディレクトリのクラウド アプリケーション管理者のアクセス許可を持つ IT 管理者は個々のユーザー アカウントを選択してアプリケーションへのアクセス権を付与できます。
- **グループ ベースの割り当て (Microsoft Entra ID P1 または P2 が必要)** ディレクトリのクラウド アプリケーション管理者のアクセス許可を持つ IT 管理者はアプリケーションにグループを割り当てることができます。 特定のユーザーのアクセスは、アプリケーションにアクセスしようとしたときに、そのグループのメンバーであるかどうかによって決まります。 言い換えると、管理者は "割り当てられたグループの現在のメンバーは誰でもアプリケーションにアクセスできる" ことを示す割り当てルールを効率的に作成できます。この割り当てオプションを使用することで、管理者は属性ベースの[動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)、外部システム グループ (オンプレミスの Active Directory や Workday など)、管理者によって管理またはセルフサービスで管理されたグループなど、あらゆる Microsoft Entra グループ管理オプションからメリットが得られます。 1 つのグループを複数のアプリに簡単に割り当てて、割り当てアフィニティを持つアプリケーションで割り当てルールを共有し、全体的な管理の複雑さを軽減できます。

    注意

    現在、アプリケーションに対するグループベースの割り当てでは、[入れ子になったグループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups) のメンバーシップはサポートされてされていません。

これら 2 つの割り当てモードを使用して、管理者は自分にとって理想的な割り当て管理方法を実現できます。

#### アプリのユーザー割り当ての要求

特定の種類のアプリケーションでは、アプリケーションにユーザーを割り当てることを要求できます。 そうすることで、明示的に割り当てたユーザー以外の人がアプリケーションにサインインすることを禁止します。 次の種類のアプリケーションでこのオプションがサポートされています。

- SAML ベースの認証を使用したフェデレーション シングル サインオン (SSO) 用に構成されたアプリケーション
- Microsoft Entra 事前認証を使用する アプリケーション プロキシのアプリケーション
- ユーザーまたは管理者がそのアプリケーションに同意した後に OAuth 2.0/OpenID Connect 認証を使用する Microsoft Entra アプリケーション プラットフォームに構築されたアプリケーション。 エンタープライズ アプリケーションには、サインインを許可されるユーザーをより詳細に制御できるものがあります。

ユーザー割り当てが要求される場合は、アプリケーションに (直接のユーザー割り当てを使用して、またはグループ メンバーシップに基づいて) 割り当てたユーザーのみがサインインできます。 アプリには、各自の [マイ アプリ] ポータルで、または直接リンクを使用してアクセスできます。

ユーザー割り当てが要求されない場合、割り当てられていないユーザーのマイ アプリにはそのアプリが表示されませんが、その場合でもアプリケーション自体にサインインする (SP によって開始されるサインオンともいう) か、またはアプリケーションの **[プロパティ]** ページで **[ユーザー アクセス URL]** を使用する (IDP によって開始されるサイン オンともいう) ことができます。 ユーザー割り当て構成の要求の詳細については、「[アプリケーションの構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-configure)」を参照してください。

この設定は、アプリケーションが [マイ アプリ] に表示されるかどうかには影響しません。 アプリケーションにユーザーまたはグループを割り当てると、アプリケーションはユーザーの [マイ アプリ] ポータルに表示されます。

注意

アプリケーションが割り当てを要求する場合、そのアプリケーションに対するユーザーの同意は許可されません。 これは、そのアプリに対するユーザーの同意がそれ以外の場合に許可されている場合でも当てはまります。 割り当てを必要とするアプリに対して、[テナント全体の管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)してください。

一部のアプリケーションでは、アプリケーションのプロパティにユーザー割り当てを要求するオプションがありません。 そのような場合、PowerShell を利用し、サービス プリンシパルで appRoleAssignmentRequired プロパティを設定できます。

#### アプリにアクセスするときのユーザー エクスペリエンスを決定する

Microsoft Entra ID には、組織内のエンド ユーザーに[アプリケーションを展開するためのカスタマイズ可能な複数の方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences)が用意されています。

- Microsoft Entra のマイ アプリ
- Microsoft 365 アプリケーション起動プログラム
- フェデレーション アプリへの直接サインオン (service-pr)
- フェデレーション アプリ、パスワードベースのアプリ、または既存のアプリへのディープ リンク

企業アプリに割り当てられているユーザーがマイ アプリや Microsoft 365 アプリケーション起動プログラムでそれを表示できるかどうかを決定できます。

### 例: Microsoft Entra ID を使用した複雑なアプリケーションの割り当て

Salesforce のようなアプリケーションについて考えます。 多くの企業では、Salesforce は主にマーケティング チームや販売チームが使用します。 通常、マーケティング チームのメンバーは Salesforce に対する高い特権アクセスを持つ一方、販売チームのメンバーのアクセスは制限されます。 多くの場合、インフォメーション ワーカーの大多数が、このアプリケーションへのアクセスを制限されます。 これらのルールに対する例外が、問題を複雑にします。 通常、マーケティングまたは販売の指揮部隊には、これらの一般的なルールとは無関係に、ユーザーにアクセス権を付与したり、そのロールを変更したりする特権があります。

Microsoft Entra ID では、Salesforce のようなアプリケーションをシングル サインオン (SSO) やプロビジョニングの自動化向けに事前構成できます。 アプリケーションが構成されたら、管理者は 1 回限りの操作を実行して、適切なグループを作成、割り当てることができます。 この例では、管理者は次のような割り当てを実行できます。

- [動的なグループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups) を定義できます。

    - マーケティング グループのすべてのメンバーは、Salesforce で "marketing" ロールに割り当てられます。
    - 販売チームのすべてのメンバーは、Salesforce で "sales" ロールに割り当てられます。 さらに調整するために、さまざまな Salesforce ロールに割り当てられた地域の販売チームを表す複数のグループを使用することもできます。
- 例外のメカニズムを有効にするには、各ロールについてセルフ サービス グループを作成できます。 たとえば、"salesforce marketing 例外" グループを、セルフ サービス グループとして作成します。 このグループを Salesforce marketing ロールに割り当て、マーケティングの指揮部隊を所有者にすることができます。 マーケティングの指揮部隊のメンバーはユーザーを追加または削除、参加ポリシーを設定できるほか、各ユーザーの参加要求を承認または拒否することもできます。 このメカニズムは、インフォメーション ワーカーにとって適切なエクスペリエンスを通してサポートされ、所有者またはメンバーになるための特別なトレーニングを必要としません。

この場合、割り当てられたすべてのユーザーは、Salesforce に自動的にプロビジョニングされます。 彼らが別のグループに追加されると、そのロール割り当ては Salesforce で更新されます。 ユーザーは、マイ アプリ、Office Web クライアントを通じて、また組織の Salesforce サインイン ページに移動して、Salesforce を探してアクセスできます。 管理者は Microsoft Entra ID レポート機能を使用して、使用量や割り当ての状態を簡単に確認できます。

管理者は、[Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups) を使用して、特定のロールのアクセス ポリシーを設定できます。 これらのポリシーには、企業環境の外部でアクセスが許可されるかどうかや、場合によっては、さまざまな状況でアクセスを実現するための多要素認証やデバイス要件も含めることができます。

### Microsoft アプリケーションへのアクセス

Microsoft アプリケーション (Exchange、SharePoint、Yammer など) の割り当てと管理の方法は、シングル サインオンのために Microsoft Entra ID と統合する Microsoft 以外の SaaS アプリケーションやその他のアプリケーションとは少し異なります。

Microsoft が公開したアプリケーションにユーザーがアクセスする方法は、主に 3 つあります。

- Microsoft 365 またはその他の有料のスイートのアプリケーションでは、**ライセンスの割り当て**によってユーザーにアクセス権が付与されます。ライセンスの割り当ては、ユーザー アカウントに直接、またはグループ ベースのライセンス割り当て機能を使用してグループを通じて行われます。
- Microsoft または Microsoft 以外の組織が誰でも使用できるように無料で公開するアプリケーションでは、[ユーザーの同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)によってユーザーにアクセスを許可します。 ユーザーは、自分の Microsoft Entra 職場または学校アカウントでアプリケーションにサインインし、そのアカウントの限定されたデータ セットにアクセスすることが許可されます。
- Microsoft または Microsoft 以外の組織が誰でも使用できるように無料で公開するアプリケーションでは、[管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-consent-requests)によってユーザーにアクセスを許可することもできます。 これは組織の全員がそのアプリケーションを使用してもよいと管理者が判断していることを意味しており、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロールを使用してアプリケーションにサインインし、組織の全員にアクセスを許可します。

一部のアプリケーションでは、これらのメソッドを組み合わせています。 たとえば、一部の Microsoft アプリケーションは Microsoft 365 サブスクリプションに含まれていますが、同意する必要が依然としてあります。

ユーザーは Office 365 ポータルから Microsoft 365 アプリケーションにアクセスできます。 また、ディレクトリの [\[ユーザー設定\]](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/hide-application-from-user-portal) にある **Office 365 表示切り替え機能**を利用し、マイ アプリで Microsoft 365 アプリケーションの表示と非表示を切り替えることができます。

企業アプリケーションと同様に、Microsoft Entra 管理センターから特定の Microsoft アプリケーションに[ユーザーを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)ことができます。あるいは、PowerShell を利用してユーザーを割り当てることができます。

### ローカル アカウントを使用したアプリケーション アクセスの防止

Microsoft Entra ID を使用すると、組織はシングル サインオンを設定して、ユーザーが条件付きアクセスや多要素認証などを使用してアプリケーションに対して認証する方法を保護できます。従来、一部のアプリケーションには独自のローカル ユーザー ストアがあり、ユーザーはシングル サインオンを使用するのではなく、ローカル資格情報またはアプリケーション固有のバックアップ認証方法を使用してアプリケーションにサインインできます。 これらのアプリケーション機能は悪用される可能性があり、ユーザーが Microsoft Entra ID でアプリケーションに割り当てられなくなったり、Microsoft Entra ID にサインインできなくなったりした後でもアプリケーションへのアクセスを保持でき、攻撃者が Microsoft Entra ID ログに表示されずにアプリケーションを侵害しようとする可能性があります。 これらのアプリケーションへのサインインが Microsoft Entra ID によって保護されるようにするには、

- シングル サインオンのためにディレクトリに接続されているアプリケーションを特定すると、エンド ユーザーはローカル アプリケーション資格情報またはバックアップ認証方法でシングル サインオンをバイパスできます。 可能かどうか、および使用可能な設定を理解するには、アプリケーション プロバイダーによって提供されるドキュメントを確認する必要があります。 次に、これらのアプリケーションで、エンド ユーザーが SSO をバイパスできるようにする設定を無効にします。 InPrivate でブラウザーを開き、アプリケーションのサインイン ページに接続し、テナント内のユーザーの ID を指定して、エンド ユーザー エクスペリエンスがセキュリティで保護されていることをテストし、Microsoft Entra 経由以外にサインインするオプションがないことを確認します。
- アプリケーションでユーザー パスワードを管理するための API が提供されている場合は、ローカル パスワードを削除するか、API を使用してユーザーごとに一意のパスワードを設定します。 これにより、エンド ユーザーがローカル資格情報を使用してアプリケーションにサインインできなくなります。
- アプリケーションでユーザーを管理するための API が提供されている場合は、ユーザーがアプリケーションまたはテナントのスコープに入っていない場合にユーザー アカウントを無効または削除するように、そのアプリケーションへの Microsoft Entra ユーザー プロビジョニングを構成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/what-is-application-management"} -->
## アプリケーション管理とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management
- Service: entra-id / enterprise-apps
- Article date: 2024-11-29
- Summary: Microsoft Entra ID でのアプリケーションのライフサイクル管理の概要。

Microsoft Entra ID でのアプリケーション管理は、クラウドでアプリケーションの作成、構成、管理、監視を行うプロセスです。 [アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)が Microsoft Entra テナントに登録されている場合、既に割り当てられているユーザーは安全にアクセスできます。 Microsoft Entra ID には多くの種類のアプリケーションを登録できます。 詳細については、「 [Microsoft ID プラットフォームのアプリケーションの種類」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types)参照してください。

この記事では、アプリケーションのライフサイクルを管理する上で重要な次の側面について説明します。

- **開発、追加、接続** - 独自のアプリケーションを開発しているか、事前に統合されたアプリケーションを使用しているか、オンプレミス アプリケーションに接続しているかによって、さまざまなパスを使用します。
- **アクセスの管理** – アクセスを管理するには、シングル サインオン (SSO) を使用し、リソースを割り当て、アクセスの許可と同意の方法を定義し、自動プロビジョニングを使用します。
- **プロパティの構成** – アプリケーションにサインインするための要件と、ユーザー ポータルでのアプリケーションの表現方法を構成します。
- **アプリケーションのセキュリティ保護** - アクセス許可、多要素認証、条件付きアクセス、トークン、証明書の構成を管理します。
- **管理と監視** – エンタイトルメント管理とリソースのレポートおよび監視を使用して、対話を管理し、アクティビティを確認します。
- **クリーンアップ** – アプリケーションが不要になったら、アプリケーションへアクセス権を削除し、アプリケーションを削除して、テナントをクリーンアップします。

### 開発、追加、または接続

Microsoft Entra ID では複数の方法でアプリケーションを管理できます。 アプリケーションの管理を開始する最も簡単な方法は、Microsoft Entra ギャラリーから事前に設定されたアプリケーションを使用することです。 独自のアプリケーションを開発し、Microsoft Entra ID に登録することも、オンプレミス アプリケーションを引き続き使用することもできます。

次の図は、これらのアプリケーションがどのように Microsoft Entra ID とやり取りするかを示したものです。

[Image: 独自に開発したアプリ、事前に統合されたアプリ、オンプレミス アプリをエンタープライズ アプリとして使用する方法を示す図。]

#### 事前に設定されたアプリケーション

多くのアプリケーションは既に事前に統合されており (この記事の前の図では **クラウド アプリケーション** として示されています)、最小限の労力で設定できます。 Microsoft Entra ギャラリー内の各アプリケーションには、[アプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)ために必要な手順を示す記事が用意されています。 ギャラリーから Microsoft Entra テナントにアプリケーションを追加する方法の簡単な例については、「[クイックスタート: エンタープライズ アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)」を参照してください。

#### 独自のアプリケーション

独自のビジネス アプリケーションを開発する場合は、それを Microsoft Entra ID で登録して、テナントが提供するセキュリティ機能を利用できます。 アプリケーションを **[アプリの登録]** で登録するか、新しいアプリケーションを **[エンタープライズ アプリケーション]** で追加するときに **[独自のアプリケーションの作成]** リンクを使用して登録できます。 Microsoft Entra ID との統合のためにアプリケーションでどのように[認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)を実装するかを検討します。

ギャラリーを通じてアプリケーションを使用できるようにする場合は、アプリケーション [を使用可能にする要求を送信](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)できます。

#### オンプレミスのアプリケーション

オンプレミス アプリケーションを引き続き使用するが、Microsoft Entra ID が提供するものを利用する場合は、 [Microsoft Entra アプリケーション プロキシを使用して Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy) ID に接続します。 アプリケーション プロキシは、オンプレミスのアプリケーションを外部に発行する場合に実装できます。 これにより、内部アプリケーションにアクセスする必要があるリモート ユーザーが、安全にそれらにアクセスできるようになります。

### アクセスを管理する

アプリケーションの[アクセスを管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management)するには、次の質問に回答する必要があります。

- アプリケーションに対するアクセスをどのように許可および同意するか。
- アプリケーションで SSO をサポートするか。
- どのユーザー、グループ、所有者をアプリケーションに割り当てる必要があるか。
- アプリケーションをサポートする他の ID プロバイダーが存在するか。
- ユーザー ID とロールのプロビジョニングを自動化すると役立ちますか?

#### アクセスと同意

[ユーザーの同意設定を管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)して、ユーザーがアプリケーションまたはサービスにユーザー プロファイルと組織のデータへのアクセスを許可できるかどうかを選択できます。 アプリケーションにアクセス権が付与されていると、ユーザーは Microsoft Entra ID と統合されたアプリケーションにサインインでき、アプリケーションは組織のデータにアクセスして、高機能なデータ駆動型エクスペリエンスを提供できます。

アプリケーションが要求しているアクセス許可にユーザーが同意できない状況の場合は、管理者の同意ワークフローを構成することを検討してください。 ワークフローを使用すると、ユーザーが正当な理由を提供し、管理者によるアプリケーションのレビューと承認を要求することができるようになります。 Microsoft Entra テナントで管理者の同意ワークフローを構成する方法については、「[管理者の同意ワークフローの構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)」を参照してください。

管理者は、アプリに[テナント全体の管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)できます。 テナント全体の管理者の同意は、通常のユーザーが許可されていないアクセス許可をアプリケーションが求める場合に必要です。 テナント全体の管理者の同意を付与することで、組織は独自のレビュー プロセスを実装することもできます。 同意を付与する前に、常にアプリケーションで要求されているアクセス許可をよく確認してください。 アプリケーションにテナント全体の管理者の同意が付与されると、ユーザーの割り当てを要求するように構成しない限り、すべてのユーザーがアプリケーションにサインインできます。

#### 単一サインイン

アプリケーションでの SSO の実装を検討してください。 ほとんどのアプリケーションを SSO 用に手動で構成できます。 Microsoft Entra ID で最も一般的なオプションは、[SAML ベース SSO と OpenID Connect ベース SSO](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) です。 開始する前に、SSO の要件と[デプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment)方法を理解していることを確認してください。 Microsoft Entra テナントのエンタープライズ アプリケーションに対して SAML ベース SSO を構成する方法の詳細については、「[Microsoft Entra ID を使用したアプリケーションに対するシングル サインオンの有効化](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)」を参照してください。

#### ユーザー、グループ、所有者の割り当て

既定では、すべてのユーザーがエンタープライズ アプリケーションに割り当てることなくアクセスできます。 ただし、アプリケーションを一連のユーザーに割り当てる場合は、ユーザーの割り当てを要求するようにアプリケーションを構成し、選択ユーザーをアプリケーションに割り当てます。 ユーザー アカウントを作成してアプリケーションに割り当てる方法の簡単な例については、[ユーザー アカウントの作成と割り当てに関するクイックスタート](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)を参照してください。

サブスクリプションに含まれている場合は、 [グループをアプリケーションに割り当てて](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) 、グループ所有者に継続的なアクセス管理を委任できるようにします。

[所有者の割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners)は、アプリケーションの Microsoft Entra 構成のすべての側面を管理する能力を付与するための簡単な方法です。 所有者であるユーザーは、アプリケーションの組織固有の構成を管理できます。 ベスト プラクティスとして、テナント内のアプリケーションを事前に監視して、所有者のないアプリケーションの状況を回避するために、少なくとも 2 人の所有者があることを確認する必要があります。

#### プロビジョニングを自動化する

[アプリケーションのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)とは、ユーザーがアクセスする必要のあるアプリケーションに、ユーザーの ID とロールを自動的に作成することを意味します。 自動プロビジョニングには、ユーザー ID の作成に加えて、状態または役割が変化したときのユーザー ID のメンテナンスおよび削除が含まれます。

#### ID プロバイダー

Microsoft Entra ID にやり取りをさせたい ID プロバイダーが存在しますか? [ホーム領域検出](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy)は、ユーザーがサインインするときに、どの ID プロバイダーで認証する必要があるかを Microsoft Entra ID が決定できるようにする構成を提供します。

#### ユーザー ポータル

Microsoft Entra ID には、組織内のユーザーにアプリケーションをデプロイするためのカスタマイズ可能な方法が用意されています。 たとえば、[マイ アプリ ポータルや Microsoft 365 アプリケーション起動ツール](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences)などです。 マイ アプリを使用すると、ユーザーは 1 か所で作業を開始し、アクセスできるすべてのアプリケーションを見つけることができます。 アプリケーションの管理者は、 [組織内のユーザーがマイ アプリを使用する方法を計画](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview)する必要があります。

### プロパティの構成

アプリケーションを Microsoft Entra テナントに追加する際、ユーザーがアプリケーションとやり取りする方法に影響するプロパティを構成する機会があります。 サインインする機能を有効または無効にしたり、ユーザーの割り当てを要求するようにアプリケーションを設定したりすることができます。 また、アプリケーションの可視性、アプリケーションを表すロゴ、およびアプリケーションに関するメモを決定できます。 構成可能なプロパティの詳細については、「エンタープライズ アプリケーションの [プロパティ」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-properties)。

### アプリケーションをセキュリティで保護する

エンタープライズ アプリケーションのセキュリティを維持するために使用できる複数の方法があります。 たとえば、[テナントのアクセスを制限](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tenant-restrictions)したり、[可視性、データ、分析を管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/cloud-app-security)したり、場合によっては[ハイブリッド アクセス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access)を提供できます。 エンタープライズ アプリケーションをセキュリティで保護するには、アクセス許可、MFA、条件付きアクセス、トークン、証明書の構成を管理する必要もあります。

#### 権限

[アプリケーションまたはサービスに付与されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)を定期的に確認し、必要に応じて管理することが重要です。 疑わしいアクティビティが存在するかどうかを定期的に評価することで、アプリケーションへの適切なアクセスのみを許可してください。

[アクセス許可の分類](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-permission-classifications)を使用すると、組織のポリシーとリスク評価に応じて、さまざまなアクセス許可の影響を特定できます。 たとえば、同意ポリシーでアクセス許可の分類を使用して、ユーザーが同意を許可された一連のアクセス許可を識別できます。

#### 多要素認証と条件付きアクセス

Microsoft Entra 多要素認証を使用すると、2 つ目の形式の認証を使用して別のセキュリティ層が提供され、データやアプリケーションへのアクセスを保護できます。 2 つ目の要素の認証に使用できる方法は多数あります。 開始する前に、組織内の[アプリケーションへの MFA の展開を計画](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)します。

組織は、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を使用して MFA を有効にすることで、ソリューションを組織の特定のニーズに適合させることができます。 管理者は、条件付きアクセス ポリシーを使用して、特定の[アプリケーション、アクション、認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)にコントロールを割り当てることができます。

#### トークンと証明書

Microsoft Entra ID の認証フローでは、使用されるプロトコルに応じて、さまざまな種類のセキュリティ トークンが使用されます。 たとえば、SAML プロトコルでは [SAML トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-saml-tokens)が使用され、OpenID Connect プロトコルでは [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)と[アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)が使用されます。 トークンは、Microsoft Entra ID によって生成される一意の証明書と、特定の標準アルゴリズムによって署名されます。

[トークンを暗号化する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)ことで、セキュリティをさらに強化できます。 また、アプリケーションで[許可されているロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)を含め、トークン内の情報を管理することもできます。

Microsoft Entra ID は、既定では [SHA-256 アルゴリズム](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/certificate-signing-options)を使用して、SAML 応答に署名します。 アプリケーションで SHA-1 が必要でない限り、SHA-256 を使用してください。 [証明書の有効期間を管理する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)ためのプロセスを確立してください。 署名証明書の最長有効期間は 3 年です。 証明書の期限切れによる停止を防止または最小限にするには、ロールとメール配布リストを使用して、証明書関連の変更通知が厳重に監視されるようにします。

### 管理と監視

Microsoft Entra ID の[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios)を使用すると、アプリケーションと管理者、カタログ所有者、アクセス パッケージ マネージャー、承認者、要求元との間のやり取りを管理できます。

Microsoft Entra のレポートおよび監視ソリューションは、法令、セキュリティ、運用の要件と、既存の環境およびプロセスに依存します。 Microsoft Entra ID で管理されているログがいくつかあります。 そのため、アプリケーションに対して可能な限り最適なエクスペリエンスを維持するために、 [デプロイのレポートと監視を計画](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/plan-monitoring-and-reporting) する必要があります。

### クリーンアップ

アプリケーションへのアクセス権をクリーンアップできます。 たとえば、[ユーザーのアクセス権を削除](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/methods-for-removing-user-access)します。 また、[ユーザーがサインインする方法を無効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal)こともできます。 そして最後に、組織で不要になったアプリケーションを削除することができます。 Microsoft Entra テナントからエンタープライズ アプリケーションを削除する方法の詳細については、「[クイックスタート: エンタープライズ アプリケーションを削除する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal)」を参照してください。

### ガイド付き攻略

この記事の推奨事項の多くのガイド付きチュートリアルについては、 [Microsoft 365 でのシングル サインオン (SSO) によるクラウド アプリのセキュリティ保護に関するガイド付きチュートリアル](https://go.microsoft.com/fwlink/?linkid=2221502)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/what-is-single-sign-on"} -->
## Microsoft Entra ID でのシングル サインオンとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on
- Service: entra-id / enterprise-apps
- Article date: 2026-06-04
- Summary: SAML プロトコルや OpenID Connect プロトコルなど、Microsoft Entra IDでのエンタープライズ アプリケーションのシングル サインオンについて説明します。

シングル サインオン (SSO) を使用すると、ユーザーは 1 回サインインし、多くのアプリケーションにアクセスできます。 この記事では、SSO とは何か、ISV と組織に役立つ理由、Microsoft Entra IDの SSO オプション、サインイン プロセスのしくみについて説明します。

SSO を使用すると、ユーザーは 1 セットの資格情報でサインインします。 その後、割り当てられたすべてのアプリケーションを再度サインインせずに開くことができます。

SSO は 2 人の対象ユーザーにとって重要です。 **独立系ソフトウェア ベンダー (ISV) は** 、エンタープライズ顧客向けのアプリケーションを構築します。 **組織は** 、ユーザーのアプリケーション アクセスを管理します。 アプリをMicrosoft Entra IDと統合する開発者、または SSO ロールアウトを計画している管理者である可能性があります。 どちらのロールでも、SSO の基本は、セキュリティとユーザー エクスペリエンスの向上に役立ちます。

Microsoft Entra IDが ID プロバイダーの場合、ユーザーは職場の資格情報を使用して 1 回サインインします。 Microsoft Entra IDは、各ユーザーを検証し、アプリに対する ID を確認します。 アプリで個別のユーザー名とパスワードが管理されなくなりました。

### シングル サインオンを使用する理由

SSO には、ISV とアプリを使用および管理するユーザーという 2 つのグループに明確な利点があります。

#### ISV アプリケーション プロバイダーの場合

ISV の場合、SSO を使用すると、アプリケーションの販売とサポートが容易になります。

- **エンタープライズ対応**性: SSO により、アプリはエンタープライズ顧客に適しています。
- **オンボードの高速化**: お客様は、追加の資格情報を管理せずにアプリをデプロイします。
- **競争上の利点**: エンタープライズ 購入者は多くの場合、SSO を必要とします。
- **よりシンプルなユーザー管理**: アプリは、独自のユーザー データベースではなく、顧客の ID システムに依存します。

#### エンド ユーザーと組織向け

ユーザーと管理者の SSO により、毎日のアクセスとセキュリティが向上します。

- **ユーザー エクスペリエンスの向上**: ユーザーが保持する資格情報の数を減らし、サインインの頻度を減らします。
- **セキュリティの強化**: 一元的なサインインでは資格情報の公開が制限され、一貫性のあるポリシーが適用されます。
- **アクセス管理の容易**さ: 管理者は、1 つの ID プロバイダーからのアクセスを制御します。
- **サポートのオーバーヘッドが少ない**: パスワードのリセットとアカウント タスクがヘルプ デスクに届く数が少なくなります。

### シングル サインオンのオプション

適切な SSO 方法は、アプリの認証方法と実行場所によって異なります。 Microsoft Entra IDでは、いくつかの方法がサポートされています。

#### フェデレーション ベースの SSO

フェデレーション ベースの SSO により、最も高度な統合が実現します。 Microsoft Entra IDは、ユーザーを認証し、標準プロトコルを使用して ID 情報をアプリに送信します。

**セキュリティ アサーション マークアップ言語 (SAML) 2.0**: 企業で広く使用されている成熟した XML ベースの標準。 SAML は、従来の Web アプリや、詳細なユーザー属性が必要なケースに適しています。

**OpenID Connect (OIDC):**JSON ベースのトークンを使用する OAuth 2.0 上に構築された最新のプロトコル。 OIDC は、認証と承認の両方を必要とする最新の Web アプリ、モバイル アプリ、API に適しています。

**プロトコルに関する考慮事項**:

- **ISV 開発者の場合**:OIDC は通常、最新のフレームワークを使用して構築する方が簡単です。 SAML は、より広範なエンタープライズ互換性を提供します。
- **管理者の場合**: どちらのプロトコルも ID インフラストラクチャと連携しますが、SAML は確立されたエンタープライズ システムに適している可能性があります。

#### パスワードベースの SSO

パスワードベースの SSO は、ユーザー名とパスワードのサインインを使用するアプリで機能します。 Microsoft Entra ID資格情報を安全に格納し、アプリに再生します。 この方法は、フェデレーション プロトコルをサポートしていないアプリ、特にアプリケーション プロキシを使用するオンプレミス アプリに役立ちます。 アプリケーション プロキシは、セキュリティで保護されたリモート アクセスのためにオンプレミス アプリを発行します。

#### 連携済みSSO

リンクされた SSO は、アプリの移行中に一貫したエクスペリエンスを維持します。 ユーザー ポータルにアプリ リンクが追加されますが、真のシングル サインオンは提供されません。 完全な SSO が後で提供される段階的な移行に使用します。

#### 無効な SSO

SSO が無効になっている場合、ユーザーは各アプリに個別にサインインします。 テスト中、または統合サインインを必要としないアプリの場合は、この設定を使用します。

### MICROSOFT ENTRA IDでの SSO のしくみ

SSO プロセスには、ユーザー、アプリ、ID プロバイダーとしてのMicrosoft Entra IDの 3 つの部分があります。

1. **ユーザーがアクセスを要求する**: ユーザーがアプリを開きます。
2. **サインインへのリダイレクト**: アプリはユーザーをMicrosoft Entra IDに送信します。
3. **ID チェック**: Microsoft Entra IDは、ユーザーの作業資格情報を検証します。
4. **アクセス許可**: Microsoft Entra IDはユーザーの ID を確認し、アプリはアクセス権を付与します。

この 4 段階のプロセスは自動的に行われるため、アプリはユーザー資格情報を直接管理しません。

### SSO の導入を計画する

SSO ロールアウトの成功は、アプリのホスティング、ユーザーのニーズ、統合オプションによって異なります。 アプリは、オンプレミス、クラウドでサービスとしてのソフトウェア (SaaS)、またはハイブリッド環境で実行できます。 各ホスティング モデルは、SSO アプローチを形作っています。

- **クラウド アプリ** では、通常、SAML や OpenID Connect などのフェデレーション プロトコルが使用されます。
- **オンプレミス アプリでは**、アプリケーション プロキシを介してフェデレーション プロトコルまたはパスワードベースの SSO を使用できます。
- **ハイブリッド シナリオでは、** 各アプリのニーズに基づいてアプローチが組み合わせられます。

包括的な計画ガイダンスについては、組織の [シングル サインオン展開の計画](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment) と、アプリケーション開発者 [向けの ISV アプリケーションの SSO 統合の計画](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-integration-isv) に関するページを参照してください。

### ユーザー エクスペリエンス: マイ アプリ ポータル

エンド ユーザーは、マイ アプリ ポータルを使用して SSO 対応アプリケーションにアクセスします。これにより、割り当てられたすべてのアプリケーションに一元的な場所が提供されます。 ユーザーは、複数の資格情報を覚えずにアプリケーションを見つけて起動できます。 詳細については、「[マイ アプリ ポータルからアプリにサインインして開始する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/whats-new-docs"} -->
## Microsoft Entra アプリケーション管理の最新情報 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/whats-new-docs
- Service: entra-id / enterprise-apps
- Article date: 2026-07-02
- Summary: この記事では、Microsoft Entra アプリケーション管理の新規ドキュメントと更新されたドキュメントを示しています。

Microsoft Entra アプリケーション管理のドキュメントの最新情報にようこそ。 この記事では、過去 3 か月間の新しいドキュメントと重要な更新があった記事の一覧を示します。 アプリケーション管理サービスの最新情報を確認するには、「[Microsoft Entra ID の最新情報](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)」を参照してください。

### 2026 年 6 月

#### 新しい記事

- [Microsoft Entra ID (ISV) との SSO 統合を計画する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-integration-isv)
- [SAML と OpenID Connect: 適切な SSO プロトコルを選択する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/saml-vs-oidc-decision-guide)
- [Microsoftの SSO モデルを理解する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/understand-microsoft-sso-model)

#### 更新された記事

- [Microsoft Entra アプリケーション ギャラリーにアプリケーションを公開するための要求を送信する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing) - ISV アプリ開発者向けの SSO オンボーディング記事の追加
- [Microsoft Entra ID でのシングル サインオンとは](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) - ISV アプリ開発者向けの SSO オンボード記事を追加する

### 2026 年 5 月

今月の更新はありません。

### 2026 年 4 月

#### 更新された記事

- 次の記事でわかりやすく、読みやすくするために、コピー編集パスを実行しました。

    - [PowerShell を使用して 1 人のユーザーに代わって同意を付与する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-consent-single-user)
    - [アプリケーションのホーム領域検出](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy)
    - [管理者の同意ワークフローの概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/admin-consent-workflow-overview)
    - [Microsoft Entra IDでのエンタープライズ アプリケーションの所有権の概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-assign-app-owners)
    - [アプリケーションに同意すると、予期しないエラーが発生する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-unexpected-user-consent-error)
- [Microsoft Entra アプリケーション ギャラリーでアプリケーションを公開するための要求を送信する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing) - アプリ公開ドキュメントに SSO チェックリストを追加しました
<!-- /MSL-PAGE -->
