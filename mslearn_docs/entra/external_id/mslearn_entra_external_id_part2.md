# Microsoft Learn — Microsoft Entra / External ID・Verified ID (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 65

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-migrate-users"} -->
## ユーザーと資格情報をMicrosoft Entra External IDに移行する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-users
- Service: entra-external-id / external
- Article date: 2026-03-16
- Summary: レガシ ID プロバイダーからMicrosoft Entra External IDにユーザーと資格情報を移行する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このガイドでは、ユーザーと資格情報を現在の ID プロバイダーからMicrosoft Entra External IDに移行する方法の基礎について説明します。 このガイドは、任意のレガシ ID プロバイダー (Azure AD B2C を含む) に適用され、ディレクトリの準備、一括ユーザー移行、資格情報の移行のセットアップ、および使用可能な資格情報の移行方法について説明します。

ヒント

Azure AD B2C から具体的に移行する場合は、「[AZURE AD B2C から外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id) への移行を計画する」を参照してください。B2C 固有の決定ガイダンス、パスワード保持オプション、実装手順について説明します。

### 前提条件

外部 ID へのユーザーの移行を開始する前に、次のものが必要です。

- 外部テナント。 作成するには、次の方法から選択します。
    - [Microsoft Entra External ID拡張機能](https://aka.ms/ciamvscode/samples/marketplace)を使用して、Visual Studio Codeで直接外部テナントを設定します。
    - [Microsoft Entra admin centerに新しい外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)を作成します。

### 準備: ディレクトリのクリーンアップ

ユーザー移行プロセスを開始する前に、レガシ ID プロバイダー ディレクトリのデータをクリーンアップする時間を取る必要があります。 これにより、必要なデータのみを移行し、移行プロセスをスムーズに行うことができます。

- 外部 ID に格納するユーザー属性のセットを特定し、必要なものだけを移行します。 必要に応じて、外部 ID 内 [にカスタム属性を作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes) して、ユーザーに関するより多くのデータを格納できます。
- 複数の認証ソースを持つ環境から移行する場合 (たとえば、各アプリケーションに独自のユーザー ディレクトリがある場合)、外部 ID の統合アカウントに移行します。 異なるソースから同じユーザーのアカウントをマージおよび調整するには、独自のビジネス ロジックを適用する必要がある場合があります。
- ユーザー名は、外部のアカウントごとに一意である必要があります。 複数のアプリケーションで異なるユーザー名を使用する場合は、独自のビジネス ロジックを適用して、アカウントを調整およびマージする必要があります。 パスワードについては、ユーザーがパスワードを選択し、ディレクトリに設定します。 選択したパスワードのみを外部 ID アカウントに格納する必要があります。
- 未使用のユーザー アカウントを削除するか、古いアカウントを移行しないでください。

### ステージ 1: ユーザー データを移行する

移行プロセスの最初の手順は、ユーザー データをレガシ ID プロバイダーから外部 ID に移行することです。 これには、ユーザー名とその他の関連する属性が含まれます。 これを行うには、次の操作を行う必要があります。

1. レガシ ID プロバイダーからユーザー アカウントを読み取ります。
2. 外部 ID ディレクトリに対応するユーザー アカウントを作成します。 プログラムによるユーザー アカウントの作成の詳細については、「[Manage Consumer ユーザー アカウントと Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/user-post-users?view=graph-rest-1.0&tabs=http#example-2-create-a-user-with-social-and-local-account-identities-in-azure-ad-b2c&preserve-view=true)」を参照してください。
3. ユーザーのプレーンテキスト パスワードにアクセスできる場合は、ユーザー データを移行するときに、新しいアカウントに直接設定できます。 プレーンテキスト パスワードにアクセスできない場合は、パスワード移行プロセスの一環として後で更新されるランダムなパスワードを設定する必要があります。

注

多数のオブジェクトを移行するときに、Microsoft Graphの調整制限が発生する可能性があります。 調整を処理または回避するためのベスト プラクティスについては、調整 [の制限](https://learn.microsoft.com/ja-jp/graph/throttling-limits) と調整に関する [ガイダンス](https://learn.microsoft.com/ja-jp/graph/throttling) を参照してください。

レガシ ID プロバイダーのすべての情報を外部 ID ディレクトリに移行する必要はありません。 次の推奨事項は、外部 ID に格納するユーザー属性の適切なセットを決定するのに役立ちます。

外部 ID に **DO** を格納する。

- ユーザー名、パスワード、メール アドレス、電話番号、メンバーシップ番号/識別子。
- プライバシー ポリシーとエンドユーザーライセンス契約の同意マーカー。

外部 ID には格納**しないでください**。

- クレジット カード番号、社会保障番号 (SSN)、医療記録、または政府または業界のコンプライアンス機関によって規制されているその他のデータなどの機密データ。
- マーケティングまたはコミュニケーションの好み、ユーザーの行動、分析情報。

### 資格情報の移行の代替手段

すべての移行で資格情報の移行が必要なわけではありません。 ユーザーがソーシャル ID プロバイダーまたはエンタープライズ フェデレーションを使用して認証する場合、パスワードはディレクトリに格納されず、移行する必要はありません。 また、パスワードレス認証に移行する場合や、ユーザーがセルフサービス パスワード リセット [(SSPR)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers) を使用してパスワードをリセットすることに慣れている場合は、資格情報の移行をスキップすることもできます。

資格情報の移行が必要ない場合は、ユーザーの移行は完了です。 移行ガイドに戻り、環境を検証し、アプリケーションのカットオーバーを計画します [。ステージ 4: カットオーバーの検証、監視、計画を行います](https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-from-b2c-to-external-id#stage-4-validate-monitor-and-plan-cutover)。

### ステージ 2: 資格情報の移行を準備する

既存のパスワードを保持する必要がある場合は、いずれかの移行方法を実装する前に、資格情報の移行用にユーザー アカウントを準備します。 このセットアップは、ステージ 3 で説明されている JIT と従来の IdP によって開始されるアプローチの両方によって共有されます。

#### 移行の状態を追跡するための拡張プロパティを定義する

各ユーザーの資格情報がレガシ ID プロバイダーから移行されたかどうかを追跡するディレクトリ拡張プロパティを定義します。 Microsoft Graphでは、[directory (Microsoft Entra ID) 拡張機能](https://learn.microsoft.com/ja-jp/graph/extensibility-overview#directory-microsoft-entra-id-extensions)を介したディレクトリ オブジェクトへのカスタム プロパティの追加がサポートされています。

## [Graph](#tab/graph)
Microsoft Graph APIを使用して拡張機能プロパティを作成します。

```http
POST https://graph.microsoft.com/v1.0/applications/00001111-aaaa-2222-bbbb-3333cccc4444/extensionProperties 

{ 
    "name": "toBeMigrated", 
    "dataType": "Boolean",
    "targetObjects":[ 
        "User" 
    ] 
} 
```

`00001111-aaaa-2222-bbbb-3333cccc4444`を、`b2c-extensions-app` アプリケーションのオブジェクト ID に置き換えます。 この拡張機能の値は、移行を必要とするすべてのユーザーに対して `true` に設定する必要があります。

## [管理センター](#tab/admin-center)
Microsoft Entra admin centerを使用して拡張プロパティを作成するには:

1. [Microsoft Entra admin center](https://entra.microsoft.com/)にサインインします。
2. **Identity**&gt;**外部 ID**&gt;**カスタム ユーザー属性**に移動します。
3. [**] を選択し、[**] を追加します。
4. 次の値を入力します。
    - **名前**: プロパティの名前を入力します (例: `toBeMigrated`)。
    - **データ型**: **ブール値**を選択します。
    - **説明**: わかりやすい説明を入力します (たとえば、ユーザーのパスワードがレガシ システムから移行されたかどうかを追跡します)。
5. **を選択して**を作成します。

---

##### 拡張プロパティ ID を取得する

拡張機能プロパティを作成した後、資格情報の移行の実装で使用する一意の識別子を取得する必要があります。 拡張プロパティ ID は、 `extension_{applicationId-without-hyphens}_{propertyName}`という名前付け規則に従います。

拡張プロパティ ID を構築するには:

1. **Entra ID**&gt;**App registrations** に移動[Microsoft Entra admin center](https://entra.microsoft.com/)。
2. アプリケーションの一覧の上から **[すべての** アプリケーション] を選択します。
3. `b2c-extensions-app`という名前のアプリケーションを見つけて、その**アプリケーション (クライアント) ID の値を**コピーします。
4. ハイフンをアプリケーション ID から削除し、属性名と組み合わせます。

たとえば、アプリケーション ID が `00001111-aaaa-2222-bbbb-3333cccc4444` され、属性名が `toBeMigrated`されている場合、拡張プロパティ ID は `extension_00001111aaaa2222bbbb3333cccc4444_toBeMigrated`されます。

#### ランダムな強力なパスワードを生成する

ユーザーを作成する前に、ユーザー アカウントごとに一意の強力な一時パスワードを生成します。 これらは、資格情報の移行が完了すると、レガシ ID プロバイダーのユーザーの実際のパスワードに置き換えられます。

Von Bedeutung

移行プロセス中にセキュリティを維持するために、一時パスワードが一意で強力であることを確認します。 組織のセキュリティ要件を満たすパスワード生成ライブラリまたはサービスの使用を検討してください。

#### 移行フラグを使用してユーザーを作成する

外部 ID テナントにユーザー アカウントを作成します。 ユーザーは、[Microsoft Entra admin center](https://entra.microsoft.com/) を使用するか、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/user-post-users) を使用してプログラムで作成できます。 ユーザー作成の詳細な手順については、「ユーザー [を作成、招待、削除する方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)」を参照してください。

次の例では、Microsoft Graph APIを使用して、移行拡張機能プロパティを `true` に設定してユーザーを作成する方法を示します。 `{extension-property-id}`を、前の手順で作成した実際の拡張プロパティ ID に置き換えます。

```http
POST https://graph.microsoft.com/v1.0/users

{
    "creationType": "LocalAccount",
    "accountEnabled": true,
    "passwordProfile": {
        "forceChangePasswordNextSignIn": false,
        "password": "<unique-generated-random-strong-password>"
    },
    "{extension-property-id}": true
}
```

ユーザーの移行をサポートするサンプル コードは [、B2C から MEEID への移行ツール](https://github.com/microsoft/b2c-to-meeid-migration-tool/)にあります。

### ステージ 3: 資格情報を移行する

移行フラグを使用してユーザー アカウントを準備したら、移行中にアプリケーションが認証される場所に基づいて資格情報の移行アプローチを選択します。

#### JIT パスワードの移行 (外部 ID によって開始)

JIT 移行では、アプリケーションは既に外部 ID エンドポイントに移動されています。 ユーザーがサインインすると、外部 ID は `OnPasswordSubmit` カスタム認証拡張機能を使用して、レガシ IdP に対してユーザーの資格情報を検証し、パスワードを外部 ID アカウントに書き込み、アカウントに移行済みとしてフラグを設定します。 後続のサインインは、外部 ID に対して直接認証されます。

完全な実装手順については、 [Just-In-Time パスワードの移行に](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-passwords-just-in-time)関する記事を参照してください。

#### 従来の IdP 主導の資格情報収集

この方法では、カスタム ポリシーまたはフローが REST API を呼び出して各ユーザーの資格情報を検証し、対応する外部 ID アカウントに書き込む間、アプリケーションはレガシ IdP エンドポイントに残ります。 十分な資格情報が移行されると、アプリケーションは外部IDの使用に切り替えられます。

この方法は、プレーンテキスト パスワードにアクセスできない場合に適用されます。 たとえば、次の場合です。

- パスワードは、レガシ ID プロバイダーによってハッシュ形式または暗号化形式で格納されます。
- パスワードはレガシ ID プロバイダーによって管理され、独自の認証サービスを通じてのみ検証できます。

資格情報の移行プロセスは、次の手順で構成されます。

1. 顧客がサインインしたら、入力したメール アドレスに対応する外部 ID ユーザー アカウントを読み取る。
2. アカウントに既に移行済みのフラグが設定されている場合は、通常のサインインを続行します。
3. アカウントに移行のフラグが設定されていない場合は、レガシ ID プロバイダーに対してパスワードを検証します。
    1. 従来の IdP がパスワードが正しくないと判断した場合は、わかりやすいエラーをユーザーに返します。
    2. レガシ IdP がパスワードが正しいと判断した場合は、外部 ID アカウントにパスワードを書き込み、移行フラグを更新します。

資格情報の移行は 2 つのフェーズで行われます。 まず、レガシ資格情報が収集され、外部 ID に格納されます。 その後、十分な数のユーザーに対して資格情報が更新されると、アプリケーションを移行して外部 ID で直接認証できます。 移行されていないユーザーは、初めてサインインするときにパスワードをリセットする必要があります。

資格情報の移行プロセスの概要設計を次の図に示します。

レガシ ID プロバイダーから資格情報を取得し、外部 ID で対応するアカウントを更新します。

[Image: 資格情報の移行の第 1 フェーズの概要設計を示す図。]

資格情報の収集を停止し、外部 ID で認証するアプリケーションを移行します。 レガシ ID プロバイダーの使用を停止します。

[Image: 資格情報の移行の第 2 フェーズの概要設計を示す図。]

注

このアプローチを使用している場合は、REST API をブルート フォース攻撃から保護することが重要です。 攻撃者は、最終的にユーザーの資格情報を推測することを期待して、複数のパスワードを送信できます。 このような攻撃を阻止するには、サインイン試行回数が特定のしきい値を超えたときに、REST API への要求の提供を停止します。

### 完全な検証と移行

ユーザーと資格情報の移行が完了したら、移行ガイドに戻り、エンドツーエンドの認証フローを検証し、アプリケーションのカットオーバーを計画します。 [ステージ 4: カットオーバーの検証、監視、計画を行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-from-b2c-to-external-id#stage-4-validate-monitor-and-plan-cutover)います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-multifactor-authentication-customers"} -->
## 多要素認証 (MFA) を顧客アプリに追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers
- Service: entra-external-id / external
- Article date: 2025-09-16
- Summary: 多要素認証 (MFA) を消費者および法人顧客向け (CIAM) アプリケーションに追加する方法について説明します。 たとえば、2 つ目の認証要素としてメール ワンタイム パスコードを CIAM サインアップおよびサインイン ユーザー フローに追加します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

多要素認証 (MFA) では、ユーザーがサインアップまたはサインイン中に ID を検証するための 2 つ目の方法を提供するように要求することで、アプリケーションにセキュリティのレイヤーが追加されます。 外部テナントでは、2 番目の要素として次の認証方法がサポートされています。

- **ワンタイム パスコードをメール**で送信する: ユーザーが自分のメールとパスワードでサインインすると、電子メールに送信されるパスコードの入力を求められます。 MFA の電子メール ワンタイム パスコードの使用を許可するには、ローカル アカウントの認証方法を *[Email with password]\(パスワード付き電子メール\*) に設定します。 *ワンタイム パスコードを含む電子メール*を選択した場合、プライマリ サインインにこの方法を使用しているお客様は、MFA のセカンダリ検証に使用できません。
- **SMS ベースの認証**: SMS は第 1 要素認証のオプションではありませんが、MFA の 2 番目の要素として使用できます。 メールとパスワード、電子メールとワンタイム パスコード、または Google、Facebook、Apple などのソーシャル ID を使用してサインインするユーザーは、SMS を使用して 2 回目の検証を求められます。 SMS による MFA には、自動詐欺行為チェックが含まれています。 詐欺の疑いがある場合は、確認のために SMS コードを送信する前に、CAPTCHA を完了して相手がロボットではないことを確認するようにユーザーに依頼します。 また、 [テレフォニー詐欺](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-region-code-opt-in)に対するセーフガードも提供します。 SMS はアドオン機能です。 テナントは、アクティブで有効なサブスクリプションに [リンク](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing#link-an-external-tenant-to-a-subscription) されている必要があります。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#sms-based-authentication)
- **パスキー (FIDO2):**パスキーは、1 つのジェスチャ (顔、指紋、PIN、またはセキュリティ キー) で MFA を満たす、フィッシングに対する耐性のあるパスワードレス認証を提供します。 ユーザーは、パスワードレスのプライマリ サインイン 方法としてパスキーを使用することもできます。 セットアップについては、 [パスキーを使用したサインインに関する説明を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey)参照してください。

この記事では、Microsoft Entra 条件付きアクセス ポリシーを作成し、サインアップとサインインのユーザー フローに MFA を追加することで、顧客に MFA を適用する方法について説明します。

### 前提条件

- Microsoft Entra 外部テナント。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。
- 外部テナントに登録され、サインアップおよびサインイン ユーザー フローに追加されたアプリ。
- 条件付きアクセス ポリシーと MFA を構成するための、少なくともセキュリティ管理者のロールを持つアカウント。
- SMS はアドオン機能であり、 [リンクされたサブスクリプション](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing#link-an-external-tenant-to-a-subscription)が必要です。 サブスクリプションの有効期限が切れた場合、またはサブスクリプションが取り消された場合、エンド ユーザーは SMS を使用して認証できなくなります。そのため、MFA ポリシーに応じてユーザーのサインインがブロックされる可能性があります。

### 条件付きアクセス ポリシーを作成する

ユーザーがアプリにサインアップまたはサインインするときに、ユーザーに MFA を求めるダイアログを表示する条件付きアクセス ポリシーを外部テナントに作成します。 (詳細については、「 [一般的な条件付きアクセス ポリシー: すべてのユーザーに MFA を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)」を参照してください)。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動し、[**新しいポリシー**] を選択します。

    [Image: 新しいポリシー ボタンのスクリーンショット。]
4. ポリシーに名前を付けます。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. [ **割り当て]** で、[ **ユーザー**] の下にあるリンクを選択します。

    a. [ **含める** ] タブで、[ **すべてのユーザー**] を選択します。

    b。 [ **除外** ] タブで、[ **ユーザーとグループ** ] を選択し、組織の緊急アクセスまたはブレークグラス アカウントを選びます。 次に、[選択] を **選択します**。

    [Image: 新しいポリシーにユーザーを割り当てるスクリーンショット。]
6. [ **ターゲット リソース**] の下にあるリンクを選択します。

    a. [ **含める** ] タブで、次のいずれかのオプションを選択します。

    - **すべてのリソース (以前の "すべてのクラウド アプリ") を**選択します。
    - [ **リソースの選択** ] を選択し、[選択] の下にあるリンクを **選択します**。 アプリを見つけて選択し、**[選択]** を選択します。

    b。 [ **除外** ] タブで、多要素認証を必要としないアプリケーションを選択します。

    [Image: 新しいポリシーにアプリを割り当てるスクリーンショット。]
7. **アクセス制御**で、**許可**の下にあるリンクを選択します。 [ **アクセス権の付与**] を選択し、[ **多要素認証を要求**する] を選択し、[選択] を **選択します**。

    [Image: MFA の要求のスクリーンショット。]
8. 設定を確認し、[ **ポリシーを有効にする]** を **[オン]** に設定します。
9. [ **作成]** を選択して作成し、ポリシーを有効にします。

### MFA メソッドとしてメールのワンタイム パスコードを有効にする

すべてのユーザーに対して、外部テナントでのメールのワンタイム パスコード認証方法を有効にします。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **[方法**] ボックスの一覧で [**電子メール OTP**] を選択します。

    [Image: 電子メールワンタイム パスコード オプションのスクリーンショット。]
4. **[有効化とターゲット]** で、**[有効]** トグルをオンにします。
5. [ **含める**] の [ **ターゲット**] の横 **にある [すべてのユーザー**] を選択します。

    [Image: 電子メールワンタイム パスコードを有効にするスクリーンショット。]
6. **[保存] を選択します**。

### MFA メソッドとして SMS を有効にする

すべてのユーザーに対して、外部テナントでの SMS 認証方法を有効にします。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. [ **方法** ] ボックスの一覧で [ **SMS**] を選択します。

    [Image: SMS オプションのスクリーンショット。]
4. **[有効化とターゲット]** で、**[有効]** トグルをオンにします。
5. [ **含める**] の [ **ターゲット**] の横 **にある [すべてのユーザー**] を選択します。

    [Image: SMS を有効にするスクリーンショット。]
6. **[保存] を選択します**。

#### オプトイン リージョンの通信をアクティブにする

2025 年 1 月以降、一部の国番号は、SMS による認証は既定で非アクティブ化されます。 非アクティブ化されたリージョンからのトラフィックを許可する場合は、Microsoft Graph `onPhoneMethodLoadStartevent` ポリシーを使用して、それらをアプリケーションに対してアクティブ化する必要があります。 [SMS 検証のオプトインが必要なリージョンを](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-region-code-opt-in)参照してください。

### サインオンをテストする

プライベート ブラウザーでアプリケーションを開き、[ **サインイン**] を選択します。 別の認証方法を求めるメッセージが表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-region-code-opt-in"} -->
## 外部テナントを使用した MFA テレフォニー検証のリージョン オプトイン (プレビュー) - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-region-code-opt-in
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: 顧客を保護するために、一部のリージョンでは、Microsoft Entra External ID の外部テナント向けに SMS テレフォニー検証を受けるため、国コードを有効にする必要があります。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

テレフォニー詐欺から保護するために、Microsoftは特定の電話番号の国番号からのトラフィックを禁止します。 これにより、不正アクセスを防ぎ、国際収益シェア詐欺 (IRSF) などの不正なアクティビティからお客様を保護できます。 IRSF を使用すると、犯罪者はネットワークへの不正アクセスを取得し、トラフィックをプレミアム レートの数値に転送します。 トラフィック ポンプと呼ばれる手法によって利益を生み出します。 この手法は多要素認証システムを対象とすることが多く、料金の増大、サービスの不安定性、システム エラーの原因となり、顧客がサービスにアクセスすることが困難になります。

国コードがブロックされると、お客様がアプリケーションの多要素認証 (MFA) の SMS 検証を設定しようとすると、"別の検証方法を試す" というメッセージが表示されることがあります。この問題を解決するには、アプリケーション内の特定の国番号に対してテレフォニー トラフィックを有効にします。

Microsoft Graph API `onPhoneMethodLoadStart` イベント ポリシーを使用して、外部テナント内のアプリのテレフォニー トラフィックを管理できます。 このイベント ポリシーを使用すると、特定の国と地域の国コードを有効または無効にすることができます。

重要

現在、この機能はプレビュー段階にあります。 ベータ版、プレビュー版>一般公開されていないAzureの機能とサービスに適用される法律条項については、[Universal License Terms for Online Services](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all) を参照してください。

### オプトインが必要な国コード

SMS 検証では、次の国コードが既定で非アクティブ化されます。 非アクティブ化されたリージョンからのトラフィックを許可する場合は、 `onPhoneMethodLoadStart` イベント ポリシーを使用してそれらを有効にする必要があります。

**表 1. SMS 検証でオプトインが必要な国コード**

| 国番号 | 地域の名前 |
| --- | --- |
| 93 | アフガニスタン |
| 213 | アルジェリア |
| 244 | アンゴラ |
| 374 | アルメニア |
| 994 | アゼルバイジャン |
| 880 | バングラデシュ |
| 375 | ベラルーシ |
| 501 | ベリーズ |
| 229 | ベナン |
| 975 | ブータン |
| 591 | ボリビア |
| 387 | ボスニア・ヘルツェゴビナ |
| 359 | ブルガリア |
| 226 | ブルキナファソ |
| 257 | ブルンジ |
| 238 | カーボベルデ |
| 855 | カンボジア |
| 2:37 | カメルーン |
| 235 | 中央アフリカ共和国 |
| 269 | コモロ |
| 2:43 | コンゴ (民主共和国) |
| 242 | コンゴ (共和国) |
| 225 | コートジボワール |
| 385 | クロアチア |
| 53 | キューバ |
| 253 | ジブチ |
| 593 | エクアドル |
| 20 | エジプト |
| 503 | エルサルバドル |
| 291 | エリトリア |
| 251 | エチオピア |
| 679 | フィジー |
| 689 | 仏領ポリネシア |
| 241 | ガボン |
| 220 | ガンビア |
| 995 | ジョージア |
| 233 | ガーナ |
| 590 | グアドループ |
| 502 | グアテマラ |
| 224 | ギニア |
| 245 | ギニアビサウ |
| 592 | ガイアナ |
| 509 | ハイチ |
| 62 | インドネシア |
| 98 | イラン |
| 964 | イラク |
| 1876 | ジャマイカ |
| 962 | ヨルダン |
| 254 | ケニア |
| 686 | キリバス |
| 383 | コソボ |
| 965 | クウェート |
| 996 | キルギス |
| 961 | レバノン |
| 266 | レソト |
| 231 | リベリア |
| 2:18 | リビア |
| 261 | マダガスカル |
| 265 | マラウイ |
| 2:23 | マリ |
| 2:22 | モーリタニア |
| 230 | モーリシャス |
| 262 | マヨット |
| 691 | ミクロネシア |
| 373 | モルドバ |
| 976 | モンゴル |
| 2:12 | モロッコ |
| 258 | モザンビーク |
| 95 | ミャンマー |
| 977 | ネパール |
| 687 | ニューカレドニア |
| 505 | ニカラグア |
| 227 | ニジェール |
| 234 | ナイジェリア |
| 968 | オマーン |
| 92 | パキスタン |
| 970 | パレスチナ自治政府 |
| 675 | パプアニューギニア |
| 63 | フィリピン |
| 974 | カタール |
| 7 | ロシア、カザフスタン |
| 250 | ルワンダ |
| 290 | セントヘレナ |
| 508 | サンピエール島・ミクロン島 |
| 1,784 | セントビンセント及びグレナディーン諸島 |
| 685 | サモア |
| 377 | サンマリノ |
| 966 | サウジアラビア |
| 2:21 | セネガル |
| 2:32 | シエラレオネ |
| 1721 | シント・マールテン |
| 386 | スロベニア |
| 2:52 | ソマリア |
| 211 | 南スーダン |
| 94 | スリランカ |
| 249 | スーダン |
| 963 | シリア |
| 992 | タジキスタン |
| 2:55 | タンザニア |
| 670 | ティモール・レステ |
| 672 | ティモール・レステ |
| 2:28 | トーゴ |
| 690 | トンガ |
| 216 | チュニジア |
| 993 | トルクメニスタン |
| 256 | ウガンダ |
| 380 | ウクライナ |
| 971 | アラブ首長国連邦 |
| 998 | ウズベキスタン |
| 678 | バヌアツ |
| 84 | ベトナム |
| 967 | イエメン |
| 260 | ザンビア |
| 263 | ジンバブエ |

### Microsoft Graphを使用してリージョンのテレフォニーを管理する

`OnPhoneMethodLoadStartExternalUsersAuthHandler` イベント ポリシーを使用して、国コードを有効または無効にします。

**表 2 OnPhoneMethodLoadStartExternalUsersAuthHandler のプロパティ**

| プロパティ | 説明 |
| --- | --- |
| デフォルト地域 | テレフォニー サービスが既定で有効になっている国コードの配列。 このプロパティは読み取り専用です。 |
| 追加の地域を含める | 既定の国コードに加えて、テレフォニー サービスを有効にする国コードの配列。 コードは、現在の国際サブスクライバー ダイヤル (ISD) の国番号 (最大長は 4) に対して検証されます。 IncludeAdditionalRegions と ExcludeRegions の両方で同じコードを指定することはできません。 |
| ExcludeRegions | テレフォニー サービスで無効にする国コードの配列。 コードは、現在の ISD 国コード (最大長は 4) に対して検証されます。 IncludeAdditionalRegions と ExcludeRegions の両方で同じコードを指定することはできません。 |

#### リージョンのテレフォニーを有効にする方法

現在非アクティブ化されている国コードからのテレフォニー トラフィックを有効にするには、Microsoft Graph API を使用して、1 つ以上のアプリケーションの `includeAdditionalRegions` イベント ポリシーで `onPhoneMethodLoadStart` プロパティを設定します。 有効にするリージョンの API 要求本文の `includeAdditionalRegions` プロパティに、関連する国コードを含めます。 たとえば、南アジアで SMS 要求を送信するには、その地域の特定の国の国番号を有効にします。

##### REST API の例

```http
POST https://graph.microsoft.com/v1.0/identity/authenticationEventListeners  
{  
    "@odata.type": "#microsoft.graph.onPhoneMethodLoadStartListener",  
    "conditions": {  
        "applications": {  
            "includeApplications": [  
                "3dfff01b-0afb-4a07-967f-d1ccbd81102a"  
            ]  
        }  
    },  
    "priority": 500,  
    "handler": {
        "@odata.type": "#microsoft.graph.onPhoneMethodLoadStartExternalUsersAuthHandler",
        "smsOptions": {
            "includeAdditionalRegions": [222, 998],
            "excludeRegions": []
        },
        "voiceOptions": {
          "includeAdditionalRegions": [],
          "excludeRegions": []
        }
    }
} 

HTTP/1.1 201 Created 
{ 
    "@odata.context": "https://microsoft.graph.microsoft.com/v1.0/$metadata#identity/authenticationEventListeners/$entity", 
    "@odata.type": "#microsoft.graph.onPhoneMethodLoadStartListener", 
    "id": "2be3336b-e3b4-44f3-9128-b6fd9ad39bb8", 
    "conditions": {  
        "applications": { 
            "includeApplications": [  
                "3dfff01b-0afb-4a07-967f-d1ccbd81102a"  
            ] 
        }   
    },   
    "handler": {
        "@odata.type": "#microsoft.graph.onPhoneMethodLoadStartExternalUsersAuthHandler",
        "smsOptions": {
            "includeAdditionalRegions": [222, 998],
            "excludeRegions": []
        },
        "voiceOptions": {
            "includeAdditionalRegions": [],
            "excludeRegions": []
        }
    }
} 
```

#### リージョンのテレフォニーを無効にする方法

リージョンからの不正な要求の可能性をブロックする場合は、`excludeRegions` ポリシーの `onPhoneMethodLoadStart` プロパティを使用して国コードを無効にすることができます。

たとえば、外部 ID アプリケーションが特定の国コードから大量の非検証 SMS メッセージを検出した場合、そのリージョンのテレフォニーを無効にすることができます。 これを行うには、その国コードを `excludeRegions` のリスト含めます。

##### REST API の例

```http
POST https://graph.microsoft.com/v1.0/identity/authenticationEventListeners  
{  
    "@odata.type": "#microsoft.graph.onPhoneMethodLoadStartListener",  
    "conditions": {  
        "applications": {  
            "includeApplications": [  
                "3dfff01b-0afb-4a07-967f-d1ccbd81102a"  
            ]  
        }  
    },  
    "priority": 500,
    "handler": {
        "@odata.type": "#microsoft.graph.onPhoneMethodLoadStartExternalUsersAuthHandler",
        "smsOptions": {
            "includeAdditionalRegions": [222, 998],
            "excludeRegions": [234, 971]
        },
        "voiceOptions": {
            "includeAdditionalRegions": [],
            "excludeRegions": []
        }
    }
} 

HTTP/1.1 201 Created 
{ 
    "@odata.context": "https://microsoft.graph.microsoft.com/v1.0/$metadata#identity/authenticationEventListeners/$entity", 
    "@odata.type": "#microsoft.graph.onPhoneMethodLoadStartListener", 
    "id": "2be3336b-e3b4-44f3-9128-b6fd9ad39bb8", 
    "conditions": {  
        "applications": { 
            "includeApplications": [  
                "3dfff01b-0afb-4a07-967f-d1ccbd81102a"  
            ] 
        }   
    },
    "handler": {
        "@odata.type": "#microsoft.graph.onPhoneMethodLoadStartExternalUsersAuthHandler",
        "smsOptions": {
            "includeAdditionalRegions": [222, 998],
            "excludeRegions": [234, 971]
        },
        "voiceOptions": {
            "includeAdditionalRegions": [],
            "excludeRegions": []
        }
    }
}  
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-register-saml-app"} -->
## SAML アプリを登録する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-register-saml-app
- Service: entra-external-id / external
- Article date: 2025-06-26
- Summary: 顧客 ID とアクセス管理 (CIAM) の外部 ID を使用して SAML アプリを作成して登録する方法について説明します。 アプリの種類を選び、詳細な手順を取得します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

外部テナントでは、認証とシングル サインオンに OpenID Connect (OIDC) または Security Assertion Markup Language (SAML) プロトコルを使用するアプリケーションを登録できます。 [アプリの登録](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-register-ciam-app) プロセスは、OIDC アプリ専用に設計されています。 ただし、エンタープライズ アプリケーション機能を使用して、SAML アプリを作成して登録できます。 このプロセスでは、一意のアプリケーション ID (クライアント ID) が生成され、アプリの登録にアプリが追加され、そこでそのプロパティを表示および管理できます。

この記事では、*Enterprise アプリケーション*でギャラリー以外の **アプリ** 作成して、独自の SAML アプリケーションを外部テナントに登録する方法について説明します。

### 前提条件

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- Microsoft Entra [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。

### SAML アプリを作成して登録する

1. Microsoft Entra 管理センターに、少なくともアプリケーション管理者としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの [**設定]** アイコン  を使用し、[**ディレクトリ]** メニューから外部テナントに切り替えます。
3. **Identity**&gt;**Applications**&gt;**Enterprise アプリケーション**に移動します。
4. [ **新しいアプリケーション**] を選択し、[ **独自のアプリケーションの作成**] を選択します。

    [Image: Microsoft Entra ギャラリーの [独自のアプリケーションの作成] オプションのスクリーンショット。]
5. **[独自のアプリケーション** の作成] ウィンドウで、アプリの名前を入力します。
6. **ギャラリーに見つからない他のアプリケーションを統合する (ギャラリー以外)** を選択します。
7. **を選択して**を作成します。
8. アプリ **の [概要]** ページが開きます。 左側のメニューの **[管理]** で、**[プロパティ]** を選択します。 ユーザーがセルフサービス サインアップを使用できるように、**[割り当てが必要ですか?]** トグルを **[いいえ]** に切り替えて、**[保存]** を選びます。

    [Image: [割り当てが必要ですか?] トグルのスクリーンショット。]
9. 左側のメニューの [ **管理**] で、[ **シングル サインオン**] を選択します。
10. [ **シングル サインオン方法の選択] で** 、[SAML] を選択 **します**。

    [Image: シングルサインオン方法タイルのスクリーンショット。]
11. **[SAML ベースのサインオン**] ページで、次のいずれかの操作を行います。

    - **[メタデータファイルをアップロード**] を選択し、メタデータを含むファイルを参照し、それから **[追加**] を選択します。 **保存**を選択します。
    - または、[**編集]** 鉛筆オプションを使用して各セクションを更新し、[**保存]**選択します。
12. **[SAML 証明書**] の 3 番目のセクションで、[**フェデレーション メタデータ XML**] の横に **[ダウンロード**] ボタンがないことに注意してください。 このボタンは、外部テナントではなく、従業員テナントにのみ表示されます。 外部テナントにメタデータ ファイルをダウンロードするには、リンクをコピーしてブラウザーに貼り付けます。

    [Image: フェデレーション メタデータの xml リンクのスクリーンショット。]
13. **[テスト**] を選択し、**[テスト サインイン**] ボタンを選択して、シングル サインオンが機能しているかどうかを確認します。 このテストでは、現在の管理者アカウントが `https://login.microsoftonline.com` エンドポイントを使用してサインインできることを確認します。

    [Image: テスト シングル サインオン オプションのスクリーンショット。]

    外部ユーザーのサインインは、次の手順でテストできます。

    - [まだ作成していない場合は、サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) を作成します。
    - [SAML アプリケーションをユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)に追加します。
    - アプリケーションを実行します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-sign-in-alias"} -->
## エイリアスを使用してサインインする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias
- Service: entra-external-id / external
- Article date: 2026-01-13
- Summary: 顧客 ID とアクセス管理 (CIAM) の外部 ID を使用して、エイリアス/ユーザー名でサインインおよびサインアップする方法について説明します。 ユーザー名をサインイン識別子として有効にし、メール アドレスとユーザー名の両方を持つユーザーを作成する詳細な手順を説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインアップまたはサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、または選択した別の識別子を指定できます。

[Image: ユーザー名サインイン オプションのスクリーンショット。]

### [前提条件]

- 独自の Microsoft Entra 外部テナントをまだ作成していない場合は、 [今すぐ作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)します。
- [アプリを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。
- [ユーザー フローを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。
- ユーザー フローに[アプリケーションを追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)します。

### サインイン識別子ポリシーでユーザー名を有効にする

ユーザー名をサインイン識別子として有効にするには、まず Microsoft Entra 管理センターでサインイン識別子ポリシーを有効にします。 UserPrincipalName (UPN) と電子メール アドレスが既定で選択されています。 ユーザー名も有効にすると、ユーザー名が割り当てられているユーザーは、自分のメール アドレスまたはユーザー名を使用してサインインできるようになります。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **[Entra ID] **&gt;、または **[Entra ID] **&gt; のいずれかから **[サインイン識別子]** を参照します。
4. [**サインイン識別子**] ページで、任意の文字列を受け入れる**既定の正規表現**を選択するか、より厳密な検証のために最大 2 つのカスタム正規表現パターンを指定して、**ユーザー名**をサインイン識別子として有効にします。 いずれかのパターンが一致する場合、ユーザー名は有効と見なされます。 電子メール アドレスの形式と一致しないことを確認する以外に、カスタム正規表現の検証は組み込まれていないことに注意してください。 指定された値が正規表現と一致しない場合、または正規表現自体が無効な場合、実行時に認証が失敗する可能性があります。

    [Image: Microsoft Entra 管理センターの [サインイン識別子] オプションのスクリーンショット。]
5. ページの最上部で **[保存]** を選択します。

### ユーザー名を使用してユーザーを作成および更新する

ユーザー名をサインイン識別子として有効にすると、電子メール アドレスとユーザー名の両方をサインイン識別子として持つ新しいユーザーを作成できます。 既存のユーザーを更新してユーザー名を追加することもできます。 これは、Microsoft Entra 管理センターまたは Microsoft Graph API を使用して行うことができます。

## [Microsoft Entra 管理センター](#tab/admin-center)
#### 管理センターでユーザー名を持つユーザーを作成する

Microsoft Entra 管理センターまたは Microsoft Graph API を使用して、電子メール アドレスとユーザー名の両方をサインイン識別子として持つ外部ユーザーを作成できます。 このセクションでは、Microsoft Entra 管理センターでのユーザーの作成について説明します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. 外部テナントで、**[Entra ID] **&gt;** [ユーザー]** を参照します。
4. [**+ 新しいユーザー**] を選択&gt;**外部ユーザーを作成します**。
5. **[ID] の**横に、**電子メール** と**ユーザー名**の両方のサインイン 方法の値を入力します。 選択順序は関係ありません。

    [Image: Microsoft Entra 管理センターの [外部ユーザーの作成] ウィンドウの [サインイン方法] ドロップダウンのスクリーンショット。]
6. [ **プロパティ** ] タブで、ユーザーの電子メール属性を指定します。

    [Image: Microsoft Entra 管理センターの [外部ユーザーの作成] ウィンドウの [電子メール属性] フィールドのスクリーンショット。]
7. [ **確認と作成** ] を選択してユーザーを作成します。

#### 既存のユーザーを更新して管理センターにユーザー名を追加する

Microsoft Entra 管理センターで既存の外部ユーザーにユーザー名を追加するには、次の手順に従います。 ユーザー名は、メール アカウントとパスワード アカウントを持つ外部ユーザーにのみ追加できます。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. 外部テナントで、**[Entra ID] **&gt;** [ユーザー]** を参照します。
4. 更新する外部ユーザーを選択します。
5. ユーザー ウィンドウで、[プロパティの **編集]** を選択します。
6. [ **ID** ] タブの [ **ID** ] セクションで、[ **+ ID の追加**] を選択します。
7. ドロップダウンから **[ユーザー名]** を選択し、ユーザーに割り当てるユーザー名を入力します。

    [Image: Microsoft Entra 管理センターで既存のユーザーにユーザー名を追加するスクリーンショット。]
8. **[保存]** をクリックして変更を適用します。

## [マイクロソフト グラフ API](#tab/graph-api)
#### Microsoft Graph API を使用してユーザー名を持つユーザーを作成する

[MS Graph エクスプローラー](https://developer.microsoft.com/en-us/graph/graph-explorer)にサインインした後、[Users API](https://learn.microsoft.com/ja-jp/graph/api/user-post-users) を使用して、電子メール アドレスとユーザー名の両方をサインイン識別子として持つユーザーを作成できます。 次の要求例は、電子メール アドレスとユーザー名の両方をサインイン識別子として持つユーザーを作成する方法を示しています。

```http
POST https://graph.microsoft.com/v1.0/users
Content-type: application/json
{
    "displayName": "Test User",
    "identities": [
        {
            "signInType": "emailAddress",
            "issuer": "contoso.onmicrosoft.com",
            "issuerAssignedId": "dylan@woodgrovebank.com"
        },
        {
            "signInType": "username",
            "issuer": "contoso.onmicrosoft.com",
            "issuerAssignedId": "dylan123"
        }
    ],
    "mail": "dylan@woodgrovebank.com",
    "passwordProfile": {
        "password": "passwordValue",
        "forceChangePasswordNextSignIn": false
    },
    "passwordPolicies": "DisablePasswordExpiration"
}
```

#### Microsoft Graph API を使用して既存のユーザーにユーザー名を追加する

既存の外部ユーザーにユーザー名を追加することもできます。

#### 手順 1: ユーザーの詳細を取得する

`$filter`を使用してユーザー オブジェクトを取得し、`$select` ID と`identities[]`プロパティを返します。 次の要求例は、電子メール アドレスをサインイン識別子として使用してユーザー アカウントを取得する方法を示しています。

```http
GET https://graph.microsoft.com/v1.0/users?$select=displayName,id,identities&$filter=identities/any(c:c/issuerAssignedId eq 'dylan@woodgrovebank.com' and c/issuer eq 'contoso.onmicrosoft.com')
```

次の応答例は、ユーザーの詳細を含む応答を示しています。

```http
HTTP/1.1 200 OK
Content-type: application/json
 
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#users(displayName,id,identities)",
    "value": [
        {
            "displayName": "ciam test 1",
            "id": "0daaf9dd-3583-4cd4-8934-a2ffd0490287",
            "identities": [
                {
                    "signInType": "userPrincipalName",
                    "issuer": "contoso.onmicrosoft.com",
                    "issuerAssignedId": "0daaf9dd-3583-4cd4-8934-a2ffd0490287@contoso.onmicrosoft.com"
                },
                {
                    "signInType": "emailAddress",
                    "issuer": "contoso.onmicrosoft.com",
                    "issuerAssignedId": "dylan@woodgrovebank.com"
                }
            ]
        }
    ]
}
```

#### 手順 2: ユーザーの詳細を更新する

上記のクエリからユーザーの詳細を取得したら、ユーザーの `identities[]` プロパティを更新できます。 ユーザーの `identities[]` プロパティ全体を置き換える必要があります。

次の要求例は、電子メール/パスワード ユーザーの `identities[]` プロパティを更新してユーザー名を追加する方法を示しています。

```http
POST https://graph.microsoft.com/v1.0/users/00aa00aa-bb11-cc22-dd33-44ee44ee44ee
Content-type: application/json 
{
            "identities": [
                {
                    "signInType": "userPrincipalName",
                    "issuer": "contoso.onmicrosoft.com",
                    "issuerAssignedId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee@contoso.onmicrosoft.com"
                },
                {
                    "signInType": "emailAddress",
                    "issuer": "contoso.onmicrosoft.com",
                    "issuerAssignedId": "dylan@woodgrovebank.com"
                },
                {
                    "signInType": "userName",
                    "issuer": "contoso.onmicrosoft.com",
                    "issuerAssignedId": "dylan1234"
                }
            ]
}
```

---

### エイリアスまたはユーザー名でサインアップする (プレビュー)

ユーザーが Microsoft Entra 外部 ID のメール アドレスに加えて、ユーザー名またはエイリアスでサインアップすることを許可できます。 サインアップ時に、ユーザーからユーザー名を収集します。 ユーザー名はテナント全体で一意である必要があります。 ユーザー名を収集するようにユーザー フローを構成するには、次の手順に従います。

#### 手順 1: ユーザー フローにユーザー名属性を追加する

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. 外部テナントで、 **Entra ID**&gt;**外部アイデンティティ**&gt;**ユーザー フロー**を参照します。
4. 前提条件で作成したユーザー フローを選択します。
5. [ **設定]** で、[ **ユーザー属性**] を選択します。
6. **Username** 属性を選択します。
7. [ **保存] を** 選択して、属性をユーザー フローに追加します。

    [Image: Microsoft Entra 管理センターの Username 属性のスクリーンショット。]

#### 手順 2: (省略可能) ユーザー名フィールドのラベルを変更する

サインアップ ページに表示されるユーザー名フィールドのラベルを変更できます。

1. 外部テナントで、 **Entra ID**&gt;**外部アイデンティティ**&gt;**ユーザー フロー**を参照します。
2. **ページ レイアウト**に移動します。
3. **Username** 属性を見つけて、ラベルを編集します。 たとえば、"Alias" または "User ID" に変更できます。
4. **保存**を選びます。

    [Image: Microsoft Entra 管理センターの [ラベルの変更] オプションのスクリーンショット。]

#### 手順 3: (省略可能) Microsoft Graph API を使用してユーザー名のカスタム検証正規表現を設定する

ユーザー名属性の `validationRegEx` を構成することで、入力検証用のカスタム正規表現を設定できます。 現在、この設定は管理センター UI では使用できませんが、Microsoft Graph を使用して構成できます。 この値を設定するには、 [authenticationAttributeCollectionInputConfiguration](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationattributecollectioninputconfiguration) リソースの種類を使用します。 参照については、 [セルフサービス サインアップ ユーザー フローのページ レイアウトを更新する例を](https://learn.microsoft.com/ja-jp/graph/api/authenticationeventsflow-update#example-2-update-the-page-layout-of-a-self-service-sign-up-user-flow)参照してください。

電子メール アドレスの形式と一致しないことを確認する以外に、カスタム正規表現の検証は組み込まれていないことに注意してください。 指定された値が正規表現と一致しない場合、または正規表現自体が無効な場合、実行時に検証が失敗する可能性があります。

また、サインアップ属性の検証 (この手順) とサインイン識別子ポリシーの両方に対してカスタム正規表現を構成する場合は、互換性が必要であるか、認証が失敗する可能性があります。 たとえば、サインアップ検証に合格したが、サインイン識別子ポリシーの正規表現と一致しないユーザー名は、実行時に認証が失敗します。

### ユーザー名を事前入力または割り当てる (プレビュー)

他の属性と同様に、ユーザー名を事前入力するか、他のユーザー情報を収集した後に割り当てることで、サインアップをカスタマイズできます。 値を事前入力するには、 [onAttributeCollectionStart](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-onattributecollectionstart-retrieve-return-data) イベントでカスタム拡張機能を使用し、ページ レイアウトまたは [Microsoft Graph を使用して](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#configure-attribute-visibility-and-editability-with-microsoft-graph)表示方法を構成します。 詳細を収集した後にユーザー名を割り当て、変更、または検証する必要がある場合は、 [onAttributeCollectionSubmit](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-onattributecollectionsubmit-retrieve-return-data) イベントを使用します。

注

ユーザー名フィールドがユーザーに表示されず、ユーザー名の値がプログラムによって割り当てられている場合は、ユーザー名の値が一意であるか、サインアップがブロックされていることを確認します。

### エイリアスまたはユーザー名を使用してサインインをテストする

ユーザー [フローの実行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-test-user-flows) 機能を使用して、作成したユーザーに割り当てたメール アドレスとユーザー名を使用して、サインアップとサインインをテストできます。 電子メール アドレスでサインインすると、要求に電子メール アドレス `preferred_username` 表示されます。 ユーザー名でサインインすると、要求にユーザー名 `preferred_username` 表示されます。

注

ユーザー オブジェクトの `identities[]` プロパティは、Microsoft Entra サインイン識別子ポリシーによって適用されません。 管理者はユーザーの `identities[]` プロパティに値を割り当てることができますが、認証は構成されたサインイン識別子ポリシーによって決まります。 つまり、ユーザーに対してサインインの種類が指定されているが、ポリシーで有効になっていない場合、認証の試行は実行時に失敗します。 たとえば、ユーザーにユーザー名 *User1234* が割り当てられている可能性がありますが、ユーザー名のサインイン方法がポリシーで有効になっていない場合、ユーザーはそのユーザー名を使用してサインインできなくなります。

### サインイン ページをカスタマイズする (省略可能)

サインイン ページをカスタマイズして、ユーザーのエクスペリエンスを向上させることができます。 サインイン ページで識別子フィールドのヒント テキストをカスタマイズし、ユーザー名に関連する他の文字列をローカライズできます。

#### サインイン ページの識別子フィールドのヒント テキストをカスタマイズする

カスタム [ブランド化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers#to-customize-the-sign-in-form)を使用して、すべてのアプリのサインイン ページで識別子フィールドのヒント テキストをカスタマイズできます。

ヒント

[ブランド化テーマ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding-themes-apps#apply-a-theme-to-applications)を使用して、特定のアプリケーションまたはアプリケーションのサブセットのヒント テキストをカスタマイズすることもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[組織のブランド管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから前に作成した外部テナントに切り替えます。
3. 検索バーを使用するか、**Entra ID**&gt; に移動して、**会社**のブランドを参照します。
4. [ **編集] を** 選択してブランドを変更します。
5. [ **サインイン フォーム** ] タブでは、サインイン ページの識別子フィールドのヒント テキストをカスタマイズできます。 たとえば、 *メール アドレスまたはメンバー ID* に変更できます。

    [Image: Microsoft Entra 管理センターでユーザー名ヒント テキストをカスタマイズするスクリーンショット。]
6. [ **確認と保存]** を選択して変更を保存します。

#### ユーザー名に関連する他の文字列をカスタマイズおよびローカライズする

言語ファイルをアップロードすることで、ユーザー名を使用してサインインするエンド ユーザーのエクスペリエンスに関連する他の文字列をカスタマイズおよびローカライズできます。 詳細については、「 [ユーザー フローに言語のカスタマイズを追加する」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-languages-customers#add-language-customization-to-a-user-flow)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-sign-in-with-passkey"} -->
## Microsoft Entra 外部 ID でパスキーを使用してサインインする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey
- Service: entra-external-id / external
- Article date: 2026-10-05
- Summary: Microsoft Entra 外部 IDを使用して、コンシューマーおよびビジネス顧客アプリでフィッシングに強く、パスワードなしのサインインに対してパスキー (FIDO2) を有効にする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

パスキーを使用すると、Microsoft Entra 外部 ID上に構築されたコンシューマー アプリケーションにサインインするためのフィッシングに強い方法が顧客に提供されます。 お客様は、パスワードを記憶したり、ワンタイム コードを入力したりする代わりに、顔、指紋、PIN、またはセキュリティ キーを使用できます。

外部テナントでは、次の 2 つの方法でパスキーを使用できます。

- **パスワードなしのサインイン。** ユーザーが自分のメールまたはユーザー名を入力します。 パスキーを使用できる場合は、顔、指紋、PIN、またはセキュリティ キーを使用してサインインするように求められます。 パスキーは第 1 要素認証を完了し、多要素認証 (MFA) 要件も満たします。
- **MFA 用のパスキー。** ユーザーは、メールまたはユーザー名とパスワードを使用してサインインします。 パスキーが登録されている場合、パスキーを使用して MFA を完了します。これは、従来の検証方法に代わるフィッシング対策の代替手段です。 MFA オプションの詳細については、 [外部テナントでの MFA に関するページを](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers)参照してください。

### 前提条件

- Microsoft Entra の外部テナント。 [無料試用版を作成](https://aka.ms/ciam-free-trial) するか、既存の外部テナントを使用します。
- パスキー ポリシーを構成するための少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロールを持つアカウント。
- テナント用に構成された [カスタム URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain) 。 パスキーは、カスタム URL を証明書利用者として登録されます。
- アプリケーションに関連付けられている [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 。
- 外部テナントに[登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)され、サインアップおよびサインインのユーザー フローに追加されたアプリ。

#### エンドユーザーの要件

- パスキーを登録できるのは、電子メール + パスワードとユーザー名とパスワードのローカル アカウントのみです。
- ユーザーは、パスキーを登録する前に MFA を完了する必要があります。 MFA を設定するには、「 [アプリに多要素認証 (MFA) を追加する」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)参照してください。
- お客様には、WebAuthn 対応のブラウザーとデバイスが必要です。 詳細については、「 サポートされているプラットフォームとブラウザー」を参照してください。

### 手順 1: パスキー (FIDO2) 認証方法を有効にする

テナントのパスキー (FIDO2) 認証方法を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Entra ID**&gt;**Security**&gt;**認証方法**&gt;**ポリシー**を参照してください。
3. **[メソッド**] ボックスの一覧で[**パスキー (FIDO2)]**を選択します。
4. **[有効化とターゲット]** で、**[有効]** トグルをオンにします。
5. [ **含める**] の [ **ターゲット]** の横 **にある [すべてのユーザー** ] を選択するか、特定のグループを選択します。
6. **保存**を選びます。

### 手順 2: パスキー プロファイルを構成する

パスキー プロファイルを使用すると、ユーザー グループごとに異なるポリシーを定義できます。 詳細については、[Microsoft Entra ID でパスキー (FIDO2) を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-passkey-fido2) を参照してください。

プロファイルを構成するには:

1. **パスキー (FIDO2)** ポリシー ページで、[**構成**] タブを選択します。
2. [ **セルフサービスの設定を許可する** ] を **[はい**] に設定します。
3. **[既定のパスキー プロファイル**] を選択するか、[**+ パスキー プロファイルの追加]** を選択して新しいパスキー プロファイルを作成します。
4. プロファイルを構成します。

    - **パスキーの種類** - 柔軟性を最大限 **に高めるために、[同期済み** ] と [ **デバイスバインド** ] の両方を選択します。
    - **構成証明を強制する** — コンシューマー向けのシナリオでは **いいえ** に設定します (同期済みのパスキーを許可します)。 規制対象環境の場合のみ **、[はい** ] に設定します。
    - **キーの制限** - AAGUID によって特定の認証子を許可またはブロックする必要がない限り、無効のままにします。
5. [ **ターゲット]** で、プロファイルを適切なユーザー グループに割り当てます。
6. **保存**を選びます。

### 手順 3: アプリケーションのパスキー管理エクスペリエンスを構築する

サインインしている顧客が独自のパスキーを登録して管理できるように、アプリケーションには資格情報管理エクスペリエンスが必要です。 [資格情報管理 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-credential-management-api) を使用して、低特権の委任アクセス許可でこのエクスペリエンスを構築します。

資格情報管理エクスペリエンスにより、お客様は次の操作を実行できるようになります。

- 同じデバイス (Windows Hello、プラットフォーム認証子、セキュリティ キー) にパスキーを登録します。
- クロスデバイス QR コード フロー (電話またはタブレット) を使用してパスキーを登録します。
- 登録されているパスキーを表示します。
- パスキーを削除します。

アプリでパスキー管理をサポートするには、[パスキー認証情報管理サンプル アプリ](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/tree/main/passkey-sample)を使用します。 このサンプルでは、委任されたアクセス許可を持つ資格情報管理 API を使用して、サインインしているユーザーが自分のパスキーを一覧表示して登録する方法を示します。 サンプルのREADMEの手順に従って、アプリを設定して実行してください。

Von Bedeutung

サンプルの削除フローでは、依然として、高い特権を持つアプリケーションのアクセス許可とブラウザー コード内のクライアント シークレットを使用する Microsoft Graph が使用されています。 テスト テナントでのみサンプルを実行します。 運用環境にデプロイしないでください。

### ユーザー エクスペリエンス

次のセクションでは、顧客がパスキーを登録、サインイン、または管理するときに表示される内容について説明します。

#### パスキーを登録する

ユーザーは、資格情報管理エクスペリエンスからパスキーを登録します。

1. ユーザーは、電子メールまたはユーザー名とパスワードを使用してアプリケーションにサインインします。
2. ユーザーは設定ページに移動して資格情報を管理し、パスキーを追加するオプションを選択します。
3. ユーザーが MFA を完了します。 直近 5 分以内に MFA を完了している必要があります。
4. ブラウザーには、ネイティブパスキー作成ダイアログが表示されます。 ユーザーは、次のいずれかのオプションを選択します。
    - **Same device** — Windows Hello、iCloud キーチェーン、Google パスワード マネージャー、または 1Password や Bitwarden などのサードパーティ プロバイダー。
    - **デバイス間** — QR コードをスキャンして、電話またはタブレットから登録します。
    - **セキュリティ キー** - FIDO2 ハードウェア キーを挿入またはタップします。
5. ユーザーは、顔、指紋、PIN、またはセキュリティ キーを使用して検証を完了します。
6. パスキーが登録され、サインインの準備が整いました。

#### パスキーを使ってサインインする

登録済みユーザーは、パスワードではなくパスキーを使用してサインインします。

1. ユーザーがアプリケーションに移動し、サインイン プロセスを開始します。
2. ユーザーが自分のメール アドレスまたはユーザー名を入力します。
3. パスキーが登録されている場合、サインイン エクスペリエンスは、ユーザーが以前にサインインしたかどうかによって異なります。
    - **初めての場合。** ユーザーは、[ **顔、指紋、PIN、またはセキュリティ キーを代わりに使用**する] を選択します。
    - **後続のサインイン。** ユーザーは、すぐにパスキーによる認証を求められます。 ブラウザーまたはプラットフォームでは、オートフィルまたはパスワード マネージャーを使用してパスキーが自動的に表示される場合もあります。
    - **クロスデバイス サインイン。** ユーザーは、別のデバイスからパスキーを使用するオプションを選択し、携帯電話またはタブレットで QR コードをスキャンします。
4. 認証が成功すると、ユーザーはアプリケーションにサインインします。

#### MFA にパスキーを使用する

条件付きアクセス ポリシーで MFA が必要であり、ユーザーに登録済みのパスキーがある場合、ユーザーはパスキーを使用して MFA を満たすことができます。

1. ユーザーがアプリケーションに移動し、サインイン プロセスを開始します。
2. ユーザーは、自分のメール アドレスまたはユーザー名とパスワードを入力します。
3. MFA プロンプトで、ユーザーはパスキー オプションを選択します。
4. ユーザーは、顔、指紋、PIN、またはセキュリティ キーを使用して検証を完了します。

検証が成功すると、ユーザーはアプリケーションにサインインします。

#### パスキーの管理

資格情報管理エクスペリエンスを通じて、顧客は次のことができます。

- 登録されているパスキー (名前、型、作成日) を**表示**します。
- 不要になったパスキーを**削除します**。
- バックアップ用のパスキーをさらに**登録**します。

### よく寄せられる質問

#### どのプラットフォームとブラウザーがサポートされていますか?

| Platform | 最小バージョン | サポートされているブラウザー |
| --- | --- | --- |
| Windows | Windows 10 バージョン 1903 以降 (Windows 11推奨) | Edge、Chrome、Firefox |
| macOS | macOS 13 (ベンチュラ) 以降 | Safari、Chrome、Edge |
| iOS | iOS 16 以降 | Safari、Chrome |
| Android | Android 9 以降 | Chrome、Edge |
| Linux | - | Chrome、Edge (外部セキュリティ キー付き) |

#### サポートされているパスキー プロバイダーと型は何ですか?

| タイプ | サポートされているプロバイダー |
| --- | --- |
| **デバイス固定** | Windows Hello、FIDO2 セキュリティ キー (YubiKey、Feitian など) |
| **同期済み** | Apple iCloud キーチェーン、Google パスワード マネージャー、1Password、Bitwarden |

デバイスに紐づけられたパスキーは、1台の物理デバイスのみに保存され、そのデバイスから外に出ることはありません。 同期されたパスキーは暗号化され、クラウド プロバイダーにリンクされているすべてのデバイスで使用できます。

#### Microsoft Authenticator はパスキーでサポートされていますか?

いいえ。 Microsoft Authenticator アプリを介したパスキーの登録は現在サポートされていません。 代わりに、Windows Hello、FIDO2 セキュリティ キー、iCloud キーチェーン、Google パスワード マネージャー、1Password、Bitwarden を使用します。

#### パスキーとは

パスキーは、公開キー暗号化を使用する FIDO2 ベースの資格情報です。 秘密キーは顧客のデバイスにとどまります。公開キーは、Microsoft Entra 外部 IDと共に格納されます。 サインインには、ローカルの生体認証または PIN が必要です。パスキーのフィッシング対策を行います。 共有シークレットは送信されません。

#### 同期型のパスキーとデバイスに紐づけられたパスキーの違いは何ですか?

同期されたパスキーは暗号化され、クラウド プロバイダー (iCloud キーチェーン、Google パスワード マネージャー、1Password、Bitwarden) にリンクされているすべてのデバイスで使用できます。 デバイス バインド パスキーは、単一の物理デバイス (Windows Hello、FIDO2 セキュリティ キー) 上に置き、そのままにしないでください。 デバイスに紐づいたパスキーはアテステーションをサポートしますが、同期されたパスキーはサポートしません。

#### パスキーは多要素認証（MFA）の要件を満たしますか?

はい。 パスキーは、デバイスの所有物 (所有しているもの) と生体認証または PIN (自分が知っているもの) を組み合わせて、1 つのジェスチャで MFA を満たします。

#### 顧客は、唯一のサインイン方法としてパスキーを使用できますか?

はい。一度登録します。 ただし、現在、パスキーを最初に登録するには、メール + パスワードまたはユーザー名 + パスワード アカウントが必要です。

#### 顧客がデバイスを紛失した場合はどうなりますか?

同期されたパスキーの場合、資格情報は顧客の他のリンクされたデバイスで使用できます。 顧客は、電子メール + パスワードまたはユーザー名 + パスワードとフォールバック MFA 方法を使用してサインインすることもできます。 管理者は、管理センターまたはGraph APIを使用して、紛失したパスキーを削除できます。

#### 顧客は、登録した場所とは異なるデバイスでパスキーを使用できますか?

はい — 同期されたパスキーは、リンクされているすべてのデバイスで自動的に使用できます。 パスキーの種類に関しては、QR コードをスキャンすることでクロスデバイス サインインが可能です (両方のデバイスでBluetoothが必要)。

#### 条件付きアクセスの認証強度を使用して、パスキーを必須にできますか?

いいえ。 外部 ID テナントは現在、条件付きアクセスの認証強度をサポートしていません。 条件付きアクセス ポリシーを使用してフィッシングに強い MFA を適用することはできません。 パスキーは、使用可能なサインイン方法として提供されますが、現時点ではポリシーを使用して必須にすることはできません。 ただし、条件付きアクセス ポリシーを使用して MFA を要求することはできます。 詳細については、「 [アプリに多要素認証 (MFA) を追加する」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)参照してください。

#### Email のワンタイム パスコードまたはソーシャル IdP のユーザーでもパスキーを使用できますか?

まだです。 現在、パスキーを使用できるのは、メールとパスワードのローカル アカウント ユーザーだけです。 メール ワンタイム パスコード、フェデレーション IdP ユーザー、ソーシャル IdP ユーザーのサポートがロードマップに記載されています。

#### パスキー認証にはコストはかかりますか?

いいえ。 パスキーは、リンクされたサブスクリプションを必要とする SMS ベースの MFA とは異なり、追加コストなしですべてのMicrosoft Entra 外部 ID価格レベルに含まれます。

#### パスキーは Azure AD B2C でサポートされていますか?

いいえ。 顧客向けアプリのパスキーは、Microsoft Entra 外部 ID (外部テナント) でのみ使用できます。 B2C を使用している場合は、 [外部 ID への移行を計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)することを検討してください。

#### 埋め込み Web ビューで passkey サインインはサポートされていますか?

いいえ。 埋め込み Web ビュー (WebView、WKWebView) では、WebAuthn のサポートが制限されているか、サポートされていません。 システム ブラウザーまたはデバイスの既定のブラウザーを使用します。

#### 管理者は顧客に代わってパスキーを登録できますか?

いいえ。 登録するには、お客様の物理的なプレゼンスと、ローカルの生体認証または PIN ジェスチャが必要です。 管理者は、パスキーを削除し、再登録を求めることができます。

#### 資格情報管理エクスペリエンスを構築するための特権が低い API はありますか。

はい。 [資格情報管理 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-credential-management-api) を使用して、サインインしている顧客が委任権限で自身のパスキーを一覧表示および登録できるようにします。

#### 複数のドメイン (関連するオリジン) で同じパスキーを使用できますか?

いいえ。 関連するオリジンのサポートは現在利用できません。 パスキーは 1 つのドメイン (証明書利用者) に対して登録され、複数のドメイン間で使用することはできません。 関連するオリジンのサポートはロードマップにあります。

#### パスキーはモバイル ネイティブ認証フローでサポートされていますか?

いいえ。 パスキーは現在、ネイティブ認証 API ではサポートされていません。 モバイル ネイティブ認証フローのサポートはロードマップに含まれています。

#### すぐに使えるパスキー登録エクスペリエンスはありますか?

いいえ。 Microsoftは現在、外部テナントに組み込みのパスキー登録エクスペリエンスを提供していません。 [資格情報管理 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-credential-management-api) を使用して、アプリケーションで資格情報管理エクスペリエンスを構築します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-test-user-flows"} -->
## ユーザー フローをテストする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-test-user-flows
- Service: entra-external-id / external
- Article date: 2025-01-22
- Summary: ユーザー フローの実行機能を使用して、顧客およびビジネス顧客向けアプリのサインアップとサインインのユーザー フローをテストする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

**ユーザー フローの実行**機能を使用すると、アプリケーションでユーザーのサインアップまたはサインイン エクスペリエンスをシミュレートすることで、ユーザー フローをテストできます。 この機能を使用して、ユーザー フローが期待どおりに動作していることを確認できます。 この機能を使用するには、アプリケーションに関連付けられているユーザー フローを選択し、ユーザー フローを実行して、要求されたサインアップまたはサインイン情報を入力します。

この機能は、アプリケーション登録から実行する必要があるほとんどの値を取得します。 テストするアプリケーションを選択し、ユーザー インターフェイスのブラウザー言語を指定できますが、通常、他のフィールドは既定値のままにしておくことができます。

注

この機能は SAML アプリをサポートしていません。 ただし、SAML アプリを実行することで、エンド ユーザー エクスペリエンスをテストすることはできます。

### 前提条件

- **Microsoft Entra 外部テナント**: [無料試用版](https://aka.ms/ciam-free-trial?wt.mc_id=ciamcustomertenantfreetrial_linkclick_content_cnl)を設定することも、Microsoft Entra ID で新しい外部テナントを作成することもできます。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。
- [Microsoft Entra に登録されている](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)アプリケーションにはリダイレクト URI が指定されており、[ユーザー フローに関連付けられています](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)。

### ユーザー フローをテストするには

**ユーザー フローの実行**機能を使用してユーザー フローをテストするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**ユーザーフロー**を参照します。
3. 一覧からユーザー フローを選択します。 リダイレクト URI を持つ少なくとも 1 つのアプリケーションがこのユーザー フローに関連付けられている必要があります ( 前提条件を参照)。

    注

    テストするアプリケーションがまだユーザー フローに追加されていない場合は、ここで追加できます。 アプリケーションを追加した後、 **ユーザー フローの実行** 機能を使用してテストできるようになるまで、少し時間がかかる場合があります。
4. [ **ユーザー フローの実行** ] ボタンを選択します。

    [Image: [ユーザー フローの実行] ボタンを示すスクリーンショット。]
5. [ **ユーザー フローの実行** ] ウィンドウでは、ほとんどのフィールドにアプリケーション登録の値が入力されるため、既定値のままにすることができます。 各フィールドの詳細については、次の表を参照してください。

    [Image: [ユーザー フローの実行] ウィンドウを示すスクリーンショット。]

    | フィールド | 説明 |
    | --- | --- |
    | **Open ID 構成 URL** | この値は、アプリケーション登録から取得されます。 これは、Microsoft Entra ID で登録したときにアプリケーションに割り当てられたパブリックにアクセス可能な URL です。 この URL は、認証 URL と公開署名キーを検索するためにクライアント アプリケーションによって使用される OpenID 構成ドキュメントを指します。 形式は `https://{tenant}.ciamlogin.com/{tenant}.onmicrosoft.com/v2.0/.well-known/openid-configuration?appid=00001111-aaaa-2222-bbbb-3333cccc4444` です。 |
    | **アプリケーション** | このメニューには、このユーザー フローに関連付けられているアプリケーションが一覧表示されます。 少なくとも 1 つのアプリケーションが必要です。 複数のアプリケーションがある場合は、テストするアプリケーションを選択します。 |
    | **応答 URL** / **リダイレクト URI** | この値はアプリケーション登録から取得され、ユーザー フローの実行機能が機能するために必要です。 アプリケーション用に構成された応答 URL またはリダイレクト URI (プロトコルに応じて) である現在の設定を維持します。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#add-a-redirect-uri) |
    | **資源** | この値は、保護された Web API のアプリケーション登録から取得され、アクセス トークンに適用されます。 **リソース**は、アプリの登録中に公開されたときに API に割り当てられたグローバルに一意の**アプリケーション ID URI** です ([詳細を参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis))。 アクセス トークンには、Web API への安全なアクセスを許可するために **、リソース** と **スコープの** 両方の値が含まれている必要があります。 |
    | **スコープ** | この値は、保護された Web API のアプリケーション登録から取得され、アクセス トークンに適用されます。 **スコープ**は、アプリケーションが API のデータと機能にアクセスするために必要なアクセス許可です。 これらの値は、アプリの登録中に API を公開するときに定義されます ([詳細を参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis))。 アクセス トークンには、Web API への安全なアクセスを許可するために **、リソース** と **スコープの** 両方の値が含まれている必要があります。 |
    | **応答の種類** | **応答の種類**は、承認エンドポイントによって発行されたトークンで返される情報の種類を指定します。 **応答の種類**に使用できる値は、アプリケーション登録で暗黙的な許可とハイブリッド フローの設定を構成する方法に基づいています。 ID トークンが指定されている場合 (暗黙的フローとハイブリッド フローの場合)、この一覧で **ID トークン** を使用できます。 アクセス トークンのみが指定されている場合 (またはトークンが指定されていない場合)、 **使用可能** な唯一のオプションはコードです。 |
    | **Code Exchange の証明キー** | シングルページ アプリケーション (SPA) には、キー交換の証明コード (PKCE) を使用した認可コード フローをおすすめします。 PKCE では、アプリケーションの指定された応答 URL にトークンの代わりに認可コードが配信されます。 PKCE フローをテストするには、[ **コード チャレンジの指定** ] チェック ボックスをオンにします。 その後、自動生成された **コード検証ツール**、 **Code Challenge メソッド**、および **コード チャレンジ** の値を使用して、ユーザー フロー エクスペリエンスをテストできます。 または、開発中にアプリケーションで予期される値を使用して、アプリケーションがトークンの認可コードを引き換えることができます。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) |
    | **ローカライゼーション** | 特定の言語をテストするには、[ **UI ロケールの指定** ] オプションを選択し **、[ターゲット言語の選択** ] メニューを使用して言語を選択します。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-languages-customers) |
    | **ユーザー フロー エンドポイントの実行** | この URL は、選択したオプションを使用してユーザー フローを実行します。 この URL を使用するか、[ **ユーザー フローの実行** ] ボタンを選択できます。 |
6. [ **ユーザー フローの実行** ] ボタンを選択するか、[ **ユーザー フロー エンドポイント URL の実行** ] を新しいブラウザー ウィンドウにコピーします。
7. サインイン ページが開き、ユーザー エクスペリエンスをテストできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-use-app-roles-customers"} -->
## アプリのロールベースのアクセス制御の使用 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: コンシューマー アプリケーションとビジネス顧客アプリケーションを定義し、それらのロールを外部テナントのユーザーとグループに割り当てる方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ロールベースのアクセス制御 (RBAC) は、アプリケーションにおいて承認を実施する一般的なメカニズムです。 組織で RBAC を使用する場合、アプリケーション開発者はアプリケーションのロールを定義します。 その後、管理者はさまざまなユーザーやグループにロールを割り当てて、誰がアプリケーションのコンテンツと機能にアクセスできるかを制御できます。

アプリケーションでは通常、セキュリティ トークン内の要求としてユーザー ロール情報を受け取ります。 開発者は、ロール要求をアプリケーションのアクセス許可として解釈する方法について、独自の実装を提供できます。 この解釈には、アプリケーション プラットフォームまたは関連ライブラリによって提供されるミドルウェアまたはその他のオプションを使用する必要があります。

### アプリ ロール

Microsoft Entra External IDを使用すると、アプリケーションのアプリケーション ロールを定義し、それらのロールをユーザーとグループに割り当てることができます。 ユーザーまたはグループに割り当てたロールによって、アプリケーション内のリソースと操作へのアクセスのレベルが定義されます。

Microsoft Entra External ID は認証されたユーザーに対してセキュリティ トークンを発行する際に、そのセキュリティ トークンのロール要求にユーザーまたはグループに割り当てられたロールの名前を含めます。 要求でそのセキュリティ トークンを受け取るアプリケーションは、ロール要求内の値に基づいて認可の決定を行うことができます。

### グループ

また開発者は、アプリケーションに RBAC を実装するためにセキュリティ グループも使用できます。この場合、特定グループ内でのユーザーのメンバーシップが、ロール メンバーシップとして解釈されます。 組織でセキュリティ グループを使用すると、グループ要求がトークンに組み込まれます。 グループ要求では、現在の外部テナント内でユーザーが割り当てられる先のすべてのグループの ID が指定されます。

### アプリ ロールとグループ

承認にはアプリ ロールまたはグループを使用できますが、両者の間の重要な違いは、実際のシナリオでどちらを使用するかの決定に影響する可能性があります。

| アプリの役割 | グループ |
| --- | --- |
| アプリケーションに固有のものであり、アプリの登録で定義されます。 | これらはアプリではなく、外部テナントに固有のものです。 |
| アプリケーション間で共有することはできません。 | 複数のアプリケーションで使用できます。 |
| アプリ ロールは、アプリの登録が削除されると削除されます。 | アプリが削除されても、グループはそのまま残ります。 |
| `roles` クレームで提供されています。 | `groups` 要求で提供されます。 |

### セキュリティ グループの作成

セキュリティ グループは、共有リソースに対するユーザーとコンピューターのアクセスを管理します。 セキュリティ グループを作成して、グループの全メンバーに同じセキュリティ アクセス許可セットが付与されるようにすることができます。

セキュリティ グループを作成するには、次の手順に従います:

1. [Microsoft Entra admin center](https://entra.microsoft.com)に少なくとも [Groups Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
4. [ **新しいグループ]** を選択します。
5. [ **グループの種類** ] ドロップダウンで、[セキュリティ] を選択 **します**。
6. Contoso\_App\_Administratorsなど、セキュリティ グループのグループ **名** を入力 *します*。
7. **Contoso app Security Administrator** など、セキュリティ グループのグループ*の説明*を入力します。
8. **作成** を選択します。

新しいセキュリティ グループが **[すべてのグループ** ] 一覧に表示されます。 すぐに表示されない場合は、ページを最新の情報に更新します。

Microsoft Entra External IDは、アプリケーション内で使用するトークンにユーザーのグループ メンバーシップ情報を含めることができます。 グループ要求をトークンに追加する方法については、「 ユーザーとグループをロールに割り当てる 」セクションを参照してください。

### アプリケーションのロールを宣言する

1. [Microsoft Entra admin center](https://entra.microsoft.com) に[特権ロール管理者として](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)少なくともサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**App registrations** に移動します。
4. アプリ ロールを定義するアプリケーションを選択します。
5. [ **アプリ ロール**] を選択し、[ **アプリ ロールの作成**] を選択します。
6. [ **アプリ ロールの作成** ] ウィンドウで、ロールの設定を入力します。 次の表に、各設定とそのパラメーターを示します。

    | フィールド | 説明 | 例 |
    | --- | --- | --- |
    | **表示名** | アプリ割り当ての際に表示されるアプリロールの名前です。 この値にはスペースを含めることができます。 | `Orders manager` |
    | **許可されるメンバー型** | このアプリのロールをユーザー、アプリケーション、またはその両方に割り当てることができるかどうかを指定します。 | `Users/Groups` |
    | **価値** | アプリケーション側でトークンに想定するロール要求の値を指定します。 この値は、アプリケーションのコードで参照される文字列と正確に一致する必要があります。 値にスペースを含めることはできません。 | `Orders.Manager` |
    | **説明** | 管理者のアプリの割り当て時に表示されるアプリのロールの詳細な説明です。 | `Manage online orders.` |
    | **このアプリ ロールを有効にしますか?** | アプリ ロールを有効にするかどうかを指定します。 アプリのロールを削除するには、このチェックボックスをオフにして、変更を適用してから削除操作を試行してください。 | *チェック* |
7. [ **適用]** を選択して、アプリケーション ロールを作成します。

#### ロールにユーザーとグループを割り当てる

アプリケーションにアプリ ロールを追加したら、管理者はそれらのロールにユーザーとグループを割り当てることができます。 ユーザーとグループのロールへの割り当ては、管理センターを通じて行うか、[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/user-post-approleassignments)を使用してプログラムで行うことができます。 さまざまなアプリのロールに割り当てられたユーザーがアプリケーションにサインインすると、`roles` 要求で割り当てられたロールがトークンに付与されます。

Azure ポータルを使用してアプリケーション ロールにユーザーとグループを割り当てるには:

1. [Microsoft Entra admin center](https://entra.microsoft.com) に[特権ロール管理者として](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)少なくともサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
4. **[すべてのアプリケーション**] を選択して、すべてのアプリケーションの一覧を表示します。 アプリケーションが一覧に表示されない場合は、[ **すべてのアプリケーション** ] リストの上部にあるフィルターを使用して一覧を制限するか、一覧を下にスクロールしてアプリケーションを見つけます。
5. ユーザーまたはグループをロールに割り当てるアプリケーションを選択します。
6. [ **管理**] で、[ **ユーザーとグループ**] を選択します。
7. [ **ユーザー/グループの追加]** を選択して **、[割り当ての追加]** ウィンドウを開きます。
8. [ **割り当ての追加** ] ウィンドウで、[ **ユーザーとグループ**] の下にあるリンクを選択します。 ユーザーとセキュリティ グループの一覧が表示されます。 一覧の複数のユーザーとグループを選択することができます。
9. ユーザーとグループを選択したら、[選択] を **選択します**。
10. [ **割り当ての追加** ] ウィンドウで、[ロールの選択] の下にあるリンク **を選択**します。 アプリケーションに対して定義したすべてのロールが表示されます。
11. ロールを選択し、**選択** をクリックします。
12. [ **割り当て]** を選択して、アプリへのユーザーとグループの割り当てを完了します。
13. 追加したユーザーとグループが [ **ユーザーとグループ** ] の一覧に表示されることを確認します。

アプリケーションをテストするには、サインアウトし、ロールを割り当てたユーザーでもう一度サインインします。 セキュリティ トークンを調べて、ユーザーのロールが含まれていることを確認します。

### セキュリティ トークンにグループ要求を追加する

セキュリティ トークンでグループ メンバーシップ要求を出力するには、次の手順に従います。

1. 少なくとも [Microsoft Entra admin center](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**App registrations** に移動します。
4. グループ要求を追加するアプリケーションを選択します。
5. [ **管理**] で、[ **トークンの構成**] を選択します。
6. [ **グループ要求の追加] を選択します**。
7. セキュリティ トークンに含めるグループの種類を選択します。
8. [ **種類別にトークンのプロパティをカスタマイズ**する] で、[ **グループ ID**] を選択します。
9. [ **追加]** を選択して、グループ要求を追加します。

#### グループへのメンバーの追加

アプリケーションにアプリ グループ要求を追加したので、セキュリティ グループにユーザーを追加します。 セキュリティ グループがない場合は、 [作成します](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups#create-a-basic-group-and-add-members)。

1. 少なくとも[Groups Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)の権限を持つ[Microsoft Entra admin center](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
4. 管理するグループを選択します。
5. **[メンバー] を選択します**。
6. [ **+ メンバーの追加] を選択します**。
7. リストをスクロールするか、検索ボックスに名前を入力します。 複数の名前を選択できます。 準備ができたら、[選択] を **選択します**。
8. **[グループの概要]** ページが更新され、グループに追加されたメンバーの数が表示されます。

アプリケーションをテストするには、サインアウトしてから、セキュリティ グループに追加したユーザーでもう一度サインインします。 セキュリティ トークンを調べて、ユーザーのグループ メンバーシップが含まれていることを確認します。

### グループとアプリケーション ロールのサポート

外部テナントは、Microsoft Entraユーザーとグループの管理モデルとアプリケーションの割り当てに従います。 コア Microsoft Entra機能の多くは、外部テナントに段階的に移行されています。

次の表には、現在使用できる機能が示されています。

| **特徴** | **現在利用できますか?** |
| --- | --- |
| リソースのアプリケーション ロールを作成する | はい (アプリケーション マニフェストを変更する) |
| アプリケーション ロールをユーザーに割り当てる | はい |
| アプリケーション ロールをグループに割り当てる | はい(Microsoft Graph経由のみ) |
| アプリケーションロールをアプリケーションに割り当てる | はい (アプリケーションのアクセス許可を使用) |
| ユーザーをアプリケーション ロールに割り当てる | はい |
| アプリケーションをアプリケーション ロールに割り当てる (アプリケーションのアクセス許可) | はい |
| アプリケーション/サービス プリンシパルにグループを追加する (グループ クレーム) | はい(Microsoft Graph経由のみ) |
| Microsoft Entra admin centerを使用して顧客 (ローカル ユーザー) を作成、更新、削除する | はい |
| Microsoft Entra admin centerを使用して顧客 (ローカル ユーザー) のパスワードをリセットする | はい |
| Microsoft Graphを使用して顧客 (ローカル ユーザー) を作成、更新、削除する | はい |
| Microsoft Graphを使用して顧客 (ローカル ユーザー) のパスワードをリセットする | はい (サービス プリンシパルがグローバル管理者ロールに追加されている場合のみ) |
| Microsoft Entra admin centerを使用してセキュリティ グループを作成、更新、削除する | はい |
| Microsoft Graph API を使用してセキュリティ グループを作成、更新、削除する | はい |
| Microsoft Entra admin centerを使用してセキュリティ グループのメンバーを変更する | はい |
| Microsoft Graph API を使用してセキュリティ グループのメンバーを変更する | はい |
| 50,000 人のユーザーと 50,000 グループにスケールアップ | 現在、利用できません |
| 少なくとも 2 つのグループに 50,000 人のユーザーを追加する | 現在、利用できません |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-user-flow-add-application"} -->
## アプリケーションをユーザー フローに追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application
- Service: entra-external-id / external
- Article date: 2025-04-14
- Summary: アプリケーションをユーザー フローに追加して、アプリケーションをサインアップとサインインのユーザー エクスペリエンスに関連付ける方法について説明します。 アプリケーションの登録とテナント情報を使用してアプリケーション構成を更新するためのガイダンスを取得します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ユーザー フローによって、顧客がアプリケーションにサインインするために使用できる認証方法と、サインアップ中に指定する必要のある情報が定義されます。 [ユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)したら、外部テナントに登録されている 1 つ以上のアプリケーションに関連付けることができます。

すべてのアプリに同じサインイン エクスペリエンスを与えることが推奨されるため、同じユーザー フローに複数のアプリを追加できます。 ただし、アプリケーションに必要なサインイン エクスペリエンスは 1 つだけであるため、各アプリケーションは 1 つのユーザー フローにしか追加できません。

### 前提条件

- **サインアップとサインインのユーザー フロー**: 開始する前に、アプリケーションに関連付ける [ユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) します。
- **アプリケーションの登録**: 外部テナントで、 [アプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。

### アプリケーションをユーザー フローに追加する

既に外部テナントにアプリケーションを登録している場合は、それを新しいユーザー フローに追加できます。 この手順により、アプリケーションにアクセスするユーザーのためのサインアップとサインインのエクスペリエンスがアクティブ化されます。 アプリケーションにはユーザー フローを 1 つしか割り当てることができませんが、ユーザー フローは複数のアプリケーションで使用できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**外部ID**&gt;**ユーザーフロー**を参照します。
3. 一覧から、ユーザー フローを選択します。
4. 左側のメニューの [ **使用**] で、[ **アプリケーション**] を選択します。
5. [ **アプリケーションの追加] を選択します**。

    [Image: ユーザー フローのアプリケーションの選択を示すスクリーンショット。]
6. 一覧からアプリケーションを選択します。 または、検索ボックスを使用してアプリケーションを検索し、それを選択します。
7. **選択**を選びます。

### 拡張機能アプリ

アプリケーションの一覧に **b2c-extensions-app** という名前のアプリが表示される場合があります。 このアプリは新しいディレクトリ内に自動的に作成され、外部テナントのすべての拡張属性を格納します。 組み込み属性以外の情報を収集する場合は、 [カスタム ユーザー属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes) を作成し、サインアップ ユーザー フローに追加できます。 カスタム属性は、顧客ディレクトリに格納されているユーザー プロファイル情報を拡張するため、ディレクトリ拡張属性とも呼ばれます。 外部テナントのすべての拡張機能属性は **、b2c-extensions-app** に格納されます。 このアプリは削除しないでください。 このアプリの詳細については [、こちらをご覧ください](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/extensions-app)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers"} -->
## ユーザー フローを作成する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers
- Service: entra-external-id / external
- Article date: 2025-09-16
- Summary: コンシューマーやビジネス顧客向けにサインアップとサインインのユーザー フローを追加します。 外部テナントのアプリ向けに、ブランド化されたカスタマイズ済みのユーザー エクスペリエンスを作成します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ヒント

ユーザー フローは、Microsoft Entra 管理センター両方の認証方法で同じ方法で作成されます。 この記事の手順では、アプリで**ブラウザー委任認証** (Microsoft ホスト型サインイン ページ) または**ネイティブ認証** (アプリに組み込まれているサインイン UI) のどちらを使用するかが適用されます。 実行時にアプリをユーザー フローと統合する方法は、アプローチによって異なります。 詳細については、「 [認証方法の選択」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)参照してください。

アプリケーションにユーザー フローを追加することで、顧客向けの簡単なサインアップとサインインのエクスペリエンスを作成できます。 ユーザー フローでは、ユーザーが従う一連のサインアップ手順と、使用できる一連のサインイン方法 (電子メールとパスワード、ワンタイム パスコード、Google、facebook、Apple) のソーシャル アカウント、またはカスタム OIDC フェデレーションを定義します。 また、一連のユーザー組み込み属性から選択するか、独自のカスタム属性を追加することで、サインアップ時に顧客から情報を収集することもできます。

この記事では、サインインとサインアップのユーザー フローを作成する方法について説明します。 ユーザー フローを作成したら、次の手順として [、アプリケーションをユーザー フローに追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)します。 顧客に提供する複数のアプリケーションがある場合は、複数のユーザー フローを作成できます。 または、多くのアプリケーションで同じユーザー フローを使用できます。 ただし、アプリケーションに指定できるユーザー フローは 1 つだけです。

### 前提条件

- **Microsoft Entra 外部テナント**: 開始する前に、Microsoft Entra 外部テナントを作成します。 [無料試用版](https://aka.ms/ciam-free-trial?wt.mc_id=ciamcustomertenantfreetrial_linkclick_content_cnl)を設定することも、Microsoft Entra ID で新しい外部テナントを作成することもできます。
- **電子メールワンタイム パスコードが有効 (省略可能)**: ユーザーがサインインするたびにメール アドレスとワンタイム パスコードを使用する場合は、テナント レベルで電子メール ワンタイム パスコードが有効になっていることを確認します ( [Microsoft Entra 管理センター](https://entra.microsoft.com/)で、 **外部 ID**&gt;**All Identity Providers**&gt;**Email One-time-passcode** に移動します)。
- **定義されたカスタム属性 (省略可能):** ユーザー属性は、セルフサービス サインアップ時にユーザーから収集された値です。 Microsoft Entra ID には組み込みの属性セットが付属していますが、 [サインアップ時に収集するカスタム属性を定義](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)できます。 カスタム属性を事前に定義して、ユーザー フローを設定するときに使用できるようにします。 または、後で作成して追加することもできます。
- **定義されている ID プロバイダー (省略可能):**[Google](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)、 [Facebook](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers) 、または [OIDC ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers) とのフェデレーションを事前に設定し、ユーザー フローを作成するときにサインイン オプションとして選択できます。

### ユーザーフローの作成とカスタマイズ

顧客がアプリケーションにサインインまたはサインアップするために使用できるユーザー フローを作成するには、次の手順に従います。 これらの手順では、新しいユーザー フローを追加し、収集する属性を選択し、サインアップ ページの属性の順序を変更する方法について説明します。

#### 新しいユーザー フローを追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**External Identities**&gt;**ユーザーフロー**に移動します。
4. [ **新しいユーザー フロー**] を選択します。

    [Image: 新しいユーザー フロー オプションのスクリーンショット。]
5. [ **作成** ] ページで、ユーザー フローの **名前** ("SignUpSignIn" など) を入力します。
6. [ **ID プロバイダー**] の [ **電子メール アカウント** ] チェック ボックスをオンにし、次のいずれかのオプションを選択します。

    - **パスワード付き電子メール**: 新しいユーザーがサインイン名として電子メール アドレスを使用してサインアップおよびサインインし、パスワードを第 1 要素認証方法として使用できるようにします。 また、サインイン ページでセルフサービス パスワード リセット リンクを表示、非表示、またはカスタマイズするためのオプションを構成することもできます ([詳細を参照](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers#to-customize-self-service-password-reset))。 多要素認証を必要とする場合、このオプションを使用すると、電子メールのワンタイム パスコード、SMS テキスト コード、またはその両方を第 2 要素方式として選択できます。
    - **電子メール ワンタイム パスコード**: 新しいユーザーがサインイン名としてメール アドレスを使用してサインアップおよびサインインし、最初の要素認証方法として電子メール ワンタイム パスコードを使用できるようにします。 多要素認証を必要とする場合、第 2 要素方式として SMS テキスト コードを有効にすることができます。

    注

    **Microsoft Entra ID サインアップ** オプションは使用できません。これは、お客様が別の Microsoft Entra 組織からのメールを使用してローカル アカウントにサインアップできますが、Microsoft Entra フェデレーションは認証に使用されないためです。 **[Google](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)** と **[Facebook](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)** は、フェデレーションを設定した後でのみ利用できるようになります。 [認証方法と ID プロバイダーの詳細について説明します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)。

    [Image: [ユーザー フローの作成] ページの ID プロバイダー オプションのスクリーンショット。]
7. [ **ユーザー属性**] で、サインアップ時にユーザーから収集する属性を選択します。

    [Image: [ユーザー フローの作成] ページのユーザー属性オプションのスクリーンショット。]
8. [ **詳細を表示** ] を選択すると、 **役職**、 **表示名**、 **郵便番号**などの属性の完全な一覧から選択できます。

    この一覧には、 [定義したカスタム属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)も含まれます。 サインアップ時にユーザーから収集する属性については、それぞれの横にあるチェック ボックスをオンにします。

    [Image: [詳細を表示] を選択した後のユーザー属性ペインのスクリーンショット。]
9. [ **OK] を選択します**。
10. [ **作成]** を選択してユーザー フローを作成します。

### 「サインインしたままにする」を管理する ダイアログを表示する

既定では、ユーザー フローを使用するアプリに顧客がサインインすると、ブラウザー セッション間 **でサインイン** したままにするかどうかを確認するプロンプトが表示されます。 ユーザーが **[はい**] を選択した場合、永続的な認証 Cookie が発行され、ブラウザー セッション間でサインインしたままになります。 [ **いいえ**] を選択すると、非永続的な Cookie が発行されます。

このプロンプトは、すべてのユーザー フローの既定の動作です。 ユーザー フローにカスタム ブランドを適用したり、多要素認証を要求したりしても、プロンプトが表示されるかどうかは変わりません。 条件付きアクセス ポリシーでオーバーライドしない限り、すべてのケースで表示されます。

プロンプトはユーザー フロー設定ではありません。 変更または抑制するには、顧客とアプリを対象とする条件付きアクセス ポリシーで **永続的なブラウザー セッション** セッション制御を構成します。

- **常に永続的**: ブラウザー セッションは常に永続化されます。 [ **サインインしたままにする]** プロンプトは表示されません。
- **永続的でない**: ブラウザーが閉じられると、ブラウザー セッションが終了します。 [ **サインインしたままにする]** プロンプトは表示されません。

セッション制御とその適用方法の詳細については、「 [条件付きアクセス: セッション - 永続的なブラウザー セッション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session#persistent-browser-session) 」および [「認証セッション管理の構成](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime#persistence-of-browsing-sessions)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-user-insights"} -->
## Microsoft Entra 外部 ID におけるユーザー アクティビティを分析する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights
- Service: entra-external-id / external
- Article date: 2026-05-19
- Summary: 外部テナントに登録されているアプリケーションのユーザー アクティビティとエンゲージメントを分析する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Important

**User Insights は、2026 年 8 月 31 日に廃止されます。** 提供終了日より前のAzure MonitorおよびLog Analyticsへの移行を計画します。 推奨される代替手段と次の手順については、「 User Insights からの移行 」を参照してください。

[使用状況と分析情報] のアプリケーション ユーザー アクティビティ機能は、テナントに登録されているアプリケーションのユーザー アクティビティとエンゲージメントに関するデータ分析を提供します。 この機能を使用すると、Microsoft Entra 管理センターでユーザー アクティビティ データを表示、クエリ、分析できます。 この機能により、戦略的な意思決定を支援し、ビジネスの成長を促進できる貴重な分析情報を発見することができます。

### サポートされるシナリオ

ユーザー分析情報機能は、次のシナリオで使用できます。

- **アクティブ ユーザーの追跡** - テナント内のアクティブ ユーザーの総数を把握して、アプリケーションとの全体的なユーザー エンゲージメントを評価する必要がある場合。
- **追加された新しいユーザーの監視** - 過去 1 か月間にテナントに追加されたユーザーの数を追跡して識別する必要がある場合。 このデータは、ユーザー ベースの増加を監視するために重要です。
- **日次および月次のアプリケーション サインインの分析** - 日次および月次でアプリケーションにサインインするユーザー数のデータを収集して、ユーザー エンゲージメントを評価し、傾向をつかむ必要がある場合。
- **MFA 使用の成功と失敗の評価** - アプリケーションの多要素認証 (MFA) の使用の成功率と失敗率を比較して、認証プロセスのセキュリティとユーザー エクスペリエンスに関する分析情報を提供する必要がある場合。 また、MFA SMS と不正行為の検出に新しい通信メトリックを使用することもできます。 これらのプレビュー メトリックは、脆弱性を特定し、潜在的な不正行為を検出するのに役立ちます。

### 前提条件

アプリケーション ユーザー アクティビティからデータにアクセスして表示するには、次の情報が必要です。

- Microsoft Entra 外部 ID の[外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)。
- 一部のサインイン データとサインアップ データを含む[登録済みアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。

### アプリケーション ユーザー アクティビティ ダッシュボードへのアクセス方法

アプリケーション ユーザー アクティビティ ダッシュボードでは、ユーザーのアプリの使用状況に関する分析情報が提供されます。 **[アプリケーション ユーザー アクティビティ]** メニューからアクセスできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上でサインインしてください。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用して、**[ディレクトリとサブスクリプション]** メニューから、前に作成した外部テナントに切り替えます。
3. **Entra ID**&gt;**監視＆ヘルス**&gt;**使用状況＆インサイト** に移動します
4. **[アプリケーション ユーザー アクティビティ]** を選択して、ダッシュボードを表示します。

    [Image: [使用状況と分析情報] メニューの [Application user activity] (アプリケーション ユーザー アクティビティ) ダッシュボードのスクリーンショット。]

### 使用可能なダッシュボードを参照する

ユーザー、要求、認証を中心としたデータを含む 3 つのダッシュボードがあります。 各ダッシュボードには、1 つ以上のサインインを試行したアプリケーション内のアクティビティの概要が提供されます。 ダッシュボードには、選択した時間範囲内のアクティビティ データが表示されます。

#### ユーザー ダッシュボード

**[ユーザー]** ダッシュボードには、日次および月間アクティブ ユーザー数と、テナントに追加された新しいユーザー数の概要が表示されます。 このデータセットでは、次の傾向を確認できます。

- 30 日間のアクティブ ユーザー数と非アクティブ ユーザー数の日次推移。
- 12 か月間の月間アクティブ ユーザー数の推移
- アプリケーション別の月間アクティブ ユーザー数の比較。
- 12 か月間に追加された新しいユーザー数。
- 新しいユーザーのオペレーティング システム別の内訳。

    [Image: [ユーザー] ダッシュボードのスクリーンショット。]

#### 認証ダッシュボード

**[認証]** ダッシュボードには、テナント内の日次および月次の認証の概要が表示されます。 このデータセットでは、次の傾向を確認できます。

- 30 日間の日次の認証。
- オペレーティング システム別の日次認証の内訳。
- 12 か月間の場所別にまとめた月次認証の内訳。

    [Image: [認証] ダッシュボードのスクリーンショット。]

#### MFA使用状況ダッシュボード

**[MFA 使用状況]** ダッシュボードには、すべてのアプリケーションの月間 MFA 認証パフォーマンスの概要が表示されます。 このデータセットでは、次の傾向を確認できます。

- MFA に登録されているユーザー
- 12 か月間の成功と失敗のカウントの概要を含む MFA の使用状況の種類
- 過去 30 日間の CAPTCHA トリガーとアクティビティ

    [Image: [MFA 使用状況] ダッシュボードのスクリーンショット。]

#### 通信メトリック (プレビュー)

MFA のパフォーマンスをより深く理解するために、[MFA 使用状況] ダッシュボードに新しいメトリックが追加されました。 これらのメトリックは、SMS ベースの MFA 使用状況に関する、アクションにつながる分析情報を提供します。

- **MFA を必要とする条件付きアクセス ポリシー**: このメトリックは、MFA を必要とする条件付きアクセス ポリシーを特定するのに役立ちます。これにより、セキュリティギャップを特定できます。
- **MFA に登録されているユーザーの数**: このメトリックは、MFA に登録されているユーザーの数と、ユーザーが使用する方法を追跡します。 この情報は、MFA 導入のレベルを評価するのに役立ちます。

通信不正行為の可能性を検出するのに役立つ新しいメトリックがいくつか追加されました。 Microsoft Entra 外部 ID では、SMS MFA に CAPTCHA を使用して、人間のユーザーとボットを区別して自動攻撃を防ぎます。 危険なユーザーが検出された場合、ユーザーがサインインするのをブロックするか、SMS による検証コードを送信する前に CAPTCHA を完了するようにユーザーに依頼します。 この方法の効果を視覚化するために、次のメトリックがダッシュボードに追加されました。

- **許可**: このメトリックは、サインインまたはサインアップ中に SMS を正常に受信したユーザーの数を示します。
- **ブロック**: このメトリックは、SMS を受信できなかったユーザーの数を示します。 通信 MFA がブロックされると、ユーザーに通知があり、代替の認証方法を試すことが推奨されます。
- **チャレンジ済み**: このメトリックは、SMS を送信する前に CAPTCHA チャレンジが表示されたタイミング示します。 これは通常、異常な動作が検出された場合に発生します。 このデータ ポイントについては、次のメトリックも表示されます。
    - **CAPTCHA を完了できないユーザーの数**: このメトリックは、CAPTCHA チャレンジを通過できなかったユーザーの数を追跡するのに役立ちます。 この分析情報は、CAPTCHA が正当なユーザーにとって難しすぎるかどうかを評価するのに役立ちます。これにより、セキュリティとアクセシビリティのバランスを調整できます。
    - **CAPTCHA を正常に完了したユーザーの数**: このメトリックは、CAPTCHA チャレンジの完了に成功したユーザーの数を確認するのに役立ちます。 このデータは、CAPTCHA が自動攻撃をどのように効果的に防止できるかについての分析情報を提供し、正当なユーザーが認証を行えることを確認します。

### ダッシュボードをカスタマイズ

[アプリケーション ユーザー アクティビティ] ダッシュボードには、ダイジェストが簡単なグラフやチャートがありますが、カスタマイズ オプションは限られています。 このダッシュボードは Microsoft Entra 管理センターで利用でき、現在ベータ版の Microsoft Graph API 経由でアクセスできます。

Microsoft Graph API を使用すると、特定のニーズや好みに合わせて調整された、強力でカスタマイズされたダッシュボードを構築し、いくつかの利点を提供できます。

- **柔軟性**: 他のデータ ソースと統合して、ビジネス目標に合わせてデータを提示できます。
- **視覚エフェクトの強化**: データの視覚表現をより鮮やかでインタラクティブにできます。
- **複雑なクエリ処理**: 高度なフィルター、集計、計算をユーザー分析情報データに適用し、より詳細で正確な結果を得ることができます。

独自のユーザー分析情報ダッシュボードを構築するには、Microsoft Graph の API アクセス許可を構成する必要があります。 すると、Microsoft Graph API を使用してデータにアクセスし、好みの分析ツールでカスタム レポートを作成できます。 Power BI を使用してデータを視覚化することをお勧めしますが、他の好きな分析ツールを選択することもできます。

#### API のアクセス許可の構成

独自のユーザー分析情報ダッシュボードを構築するには、[Microsoft Graph の API アクセス許可を 構成し](https://learn.microsoft.com/ja-jp/graph/auth-v2-service)、登録されているアプリに `Insights-UserMetric.Read.All` アクセス許可を追加する必要があります。

[Image: API のアクセス許可のスクリーンショット。]

また、[クライアント シークレットを生成し](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-service-principal-portal#option-3-create-a-new-client-secret)、[アクセス トークン](https://learn.microsoft.com/ja-jp/graph/auth-v2-service?tabs=http#4-request-an-access-token)を取得して、Microsoft Graph を操作する必要があります。

アクセス トークンが正常に作成されたら、Microsoft Graph API を使用してデータにアクセスし、カスタム レポートを作成できます。

#### カスタム Power BI レポートを作成する

ユーザー分析情報データをフェッチするために、カスタム コネクタを使用して Power BI レポートを作成できます。 以下に、これを実行する方法を示します。

1. 新しい空の Power BI レポートを作成する
2. [カスタム コネクタ](https://learn.microsoft.com/ja-jp/power-bi/connect-data/desktop-connect-to-data)を作成し、クエリを実行する Microsoft Graph API エンドポイントの URL を入力します。 たとえば、月次アクティブ ユーザー データでは `https://graph.microsoft.com/beta/reports/userinsights/monthly/activeUsers` です。
3. **[高度]** を選択して、アクセス トークンを追加します。
4. **[HTTP 要求ヘッダー パラメーター (省略可能)]** セクションで、ドロップダウン リストの [承認] を選択し、アクセス トークンを入力します。
5. **[OK]** を選択して Power BI を Microsoft Graph API に接続し、データを読み込みます。

[Image: トークン追加のスクリーンショット。]

Power BI には、データのクリーンアップと整形に役立つ Power Query エディターが付属しています。 不要な列を削除したり、欠損した値を処理したり、マージ、グループ化、フィルター処理などの変換を適用したりできます。 詳細については、「[クエリ エディターの概要](https://learn.microsoft.com/ja-jp/power-bi/transform-model/desktop-query-overview)」をご覧ください｡

### User Insights からの移行

User Insights は、 **2026 年 8 月 31** 日に廃止されます。 その日以降、アプリケーション ユーザー アクティビティ ダッシュボードと Microsoft Graph `reports/userInsights/*` (ベータ) エンドポイントはデータの返しを停止します。 ユーザー アクティビティ、サインイン、MFA の使用状況を把握するには、提供終了日の前に、このセクションの代替手段に移行します。 エンド ユーザーへの影響はなく、ダッシュボードの履歴データは自動的に移行されません。

#### 推奨される代替手段

- **Azure Monitor と Log Analytics (推奨)。** Microsoft Entra のサインイン ログと監査ログを Log Analytics ワークスペースにルーティングし、[Microsoft Entra ワークブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)または `SigninLogs` テーブルと `AuditLogs` テーブルに対する KQL クエリを使用して、User Insights ビューを再現します。 セットアップについては、「[外部テナントでAzure Monitorを設定する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor)と[Azure Monitor ログを含むMicrosoft Entraログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)を設定する方法に関するページを参照してください。
- **Microsoft Graph のサインインおよび監査ログ API** カスタム レポートとパイプラインの場合は、 `reports/userInsights/*` の呼び出しを [List signIns](https://learn.microsoft.com/ja-jp/graph/api/signin-list)、 [List directoryAudits](https://learn.microsoft.com/ja-jp/graph/api/directoryaudit-list)、および [レポート API](https://learn.microsoft.com/ja-jp/graph/api/resources/report) に置き換えます。

#### 次のステップ

2026 年 8 月 31 日より前は、User Insights または `reports/userInsights/*` エンドポイントに依存するダッシュボード、Power BI レポート、アプリ登録のインベントリを作成し、Azure MonitorまたはMicrosoft Graph アクティビティ ログ API を使用してビューを再構築します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/migrate-from-b2c-to-external-id"} -->
## Azure AD B2C から Microsoft Entra 外部 ID に移行する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-from-b2c-to-external-id
- Service: entra-external-id / external
- Article date: 2026-03-13
- Summary: 標準の移行アプローチを使用して、ユーザー、資格情報、アプリケーションAzure AD B2C からMicrosoft Entra 外部 IDに移行します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

標準の移行アプローチを使用して、ユーザー、資格情報、アプリケーションAzure AD B2C からMicrosoft Entra 外部 IDに移行します。 この記事では、AZURE AD B2C に ID が既に存在し、中断を最小限に抑えながら移行する必要がある移行シナリオについて説明します。

大規模なMicrosoft Entra 外部 IDを評価する新しい顧客は、[ソリューションの計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution)を参照する必要があります。

AZURE AD B2C のお客様で、移行に使用できるオプションをまだ確認していない場合は、「[Azure AD B2C から外部 ID への移行を計画する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)を参照してください。

この記事では、次の方法について説明します。

- 現在の Azure AD B2C 実装を評価する
- 宛先の外部 ID テナントとベースライン セキュリティを準備する
- ユーザーの移行とパスワードの保持 (必要な場合)
- アプリケーションのカットオーバーを検証、監視、および計画する

### 前提条件

この記事では、 **標準の移行アプローチ**を既に選択していることを前提としています。 方法 (標準モードと HSC モード) を決定する必要がある場合は、[Azure AD B2C から外部 ID への移行を計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)から開始してください。

### ステージ 1: 現在の Azure AD B2C 実装を評価する

アプリケーション、ユーザー フロー、ID プロバイダー、トークン/要求の要件、カスタム ビジネス ロジックなど、外部 ID で同等の動作を再作成できるように、現在の機能をインベントリします。

Azure AD B2C 実装を外部 ID 構成に変換する際の開始点として、次の機能マッピング テーブルを使用します。

#### 機能マッピング テーブル

| 特徴 | Azure AD B2C | 外部 ID と同等 |
| --- | --- | --- |
| カスタム ビジネス ロジック | カスタム ポリシー (XML/IEF) | カスタム認証拡張機能 |
| 認証 UI | HTML/CSS を使用したホストされたページ | ブランド化とネイティブ認証を使用したユーザー フロー |
| ユーザー管理 | Microsoft Graph API | Microsoft Graph API |
| アクセス方針 | カスタム ポリシー、ユーザー フロー条件 | Microsoft Entra 条件付きアクセス |
| モバイル認証 | ブラウザー ベースのフロー、Web リダイレクト | ネイティブ認証 SDK (ローカル アカウントのみ) |
| トークンのカスタマイズ | ポリシー内のカスタム要求 | カスタム認証拡張機能 |

完全な機能の概要については、「 [サポートされている機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers)」を参照してください。

### ステージ 2: 移行先の外部 ID テナントを準備する

運用ユーザーを移行したり、アプリケーションを切り取ったりする前に、移行先の外部 ID テナントとベースラインのセキュリティ、コンプライアンス、監視を設定します。 新しい外部 ID テナントの詳細なデプロイ ガイダンスについては、「ソリューションの計画」 [を参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution)。

通常、このステージには次のものが含まれます。

- [外部テナントの作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)
- [アプリケーションの登録とユーザー フローの構成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)
- [ソーシャル ID プロバイダーとのフェデレーションの設定](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers) (省略可能)
- [セキュリティ、コンプライアンス、監視の構成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-security-customers)

運用ユーザー データを移行する前に、次の手順を実行します。

### ステージ 3: ユーザーと資格情報を移行する

このステージでは、ユーザーと資格情報を AZURE AD B2C から外部 ID に移行する方法を決定します。 パスワードを保持するかどうかに関係なく、ユーザーの一括移行は常に必要です。 重要な決定は、既存のパスワードを保持する必要があるかどうか、その場合は使用する方法です。

#### パスワードを保持する必要がありますか?

既存のパスワードを保持する必要があるかどうかを決定します。 すべての移行でパスワードの保持が必要なわけではありません。 不要な場合、ユーザーは [セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers) を使用して移行後にパスワードをリセットするか、パスワードレスまたはソーシャル サインイン オプションに移動できます。

通常、次 **の場合はパスワードの** 保持は必要ありません。

- ユーザーは **ソーシャル ID プロバイダー** (Google や Facebook など) を使用して認証します。
- **パスワードレス認証** (電子メールワンタイム パスコードなど) を使用する予定です。
- 移行後にユーザーに **パスワードのリセットを** 要求することに慣れている。
- 規制要件では、ユーザーの同意を更新する必要があります。 この場合は、送信メール通信の後にユーザーが開始したパスワードのリセットによって同意を得ることができます。
- **plain-text パスワード**にアクセスでき、一括ユーザー移行中にMicrosoft Graphを介して直接設定できます。

パスワードの保持が必要ない場合は、「 [ユーザーと資格情報を外部 ID に移行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-users#stage-1-migrate-user-data) する」のステージ 1 を完了し、「 ステージ 4: カットオーバーの検証、監視、計画」に直接スキップします。

#### パスワードの保存方法を選択する

パスワードを保持する必要がある場合は、移行中にアプリケーションが認証される場所に基づいてアプローチを選択します。

次のデシジョン ツリーを使用して、残りのパスワードの保持とカットオーバーの手順を決定します。

[Image: パスワードの保持とアプリケーションのカットオーバー オプションを示す Azure AD B2C 移行デシジョン ツリーのダイアグラム。]

##### Just-In-Time (JIT) パスワードの移行 (外部 ID によって開始)

JIT パスワードの移行では、アプリケーションは既に外部 ID エンドポイントに移動されています。 ユーザーがサインインすると、外部 ID は `OnPasswordSubmit` カスタム認証拡張機能を使用して、レガシ IdP に対してユーザーの資格情報を検証し、パスワードを外部 ID アカウントに書き込み、アカウントに移行済みとしてフラグを設定します。

この図では、一括移行、JIT パスワード移行、アプリケーションのカットオーバーを組み合わせた、標準の移行パスの詳細なビューを示します。

- **1:** ユーザー アカウントは外部 ID で事前にプロビジョニングされますが、アプリケーションはレガシ IdP を介して認証を続けます。
- **2:** パスワードが JIT 経由で移行される間、アプリケーションは外部 ID に移行され、従来の IdP は移行用の資格情報を検証します。
- **3:** レガシ IdP は、すべてのユーザーと資格情報が完全に移動されるとシャットダウンされ、外部 ID は唯一の認証システムのままにされます。

[Image: 従来の IdP、一括移行、パスワード同期、外部 ID へのカットオーバーを示す 3 段階のユーザー移行ワークフローの図。]**JIT パスワードの移行を使用する場合の考慮事項**

JIT では、AZURE AD B2C と外部 ID の両方を監視およびサポートする必要がある共存期間が導入されています。 次の点に注意してください。

- **ユーザーの一括移行は引き続き必要です。** JIT サインインを機能させるには、外部 ID にユーザー アカウントが存在している必要があります。 早期に一括移行を完了します。
- **ID 状態の同期は、時間の経過と同時に困難になります。** プロファイルの更新、パスワードの変更、および新しいサインアップは、両方のシステムで調整されている必要があります。 通常、定期的な調整が必要です。
- **サインインしないユーザーは移行されません。** 残りのユーザーの強制パスワード リセットまたは最終的な一括移行を計画し、明確なカットオーバー タイムラインを設定します。

JIT は、共存がオープン エンドではなく、タイム ボックス化されている場合に最適に機能します。

##### Azure AD B2C によって開始された移行

B2C によって開始されるパターンでは、アプリケーションは AZURE AD B2C エンドポイントに残り、資格情報はバックグラウンドで収集されます。 B2C カスタム ポリシーは REST API を呼び出して、レガシ IdP に対して資格情報を検証し、対応する外部 ID アカウントに書き込みます。 十分な資格情報が移行されると、アプリケーションは外部IDの使用に切り替えられます。

- **1:** ユーザーは資格情報の検証とバックグラウンド移行のためにAzure Functionsを使用して段階的に外部 ID に移行されている間、レガシ IdP で認証を続けます。
- **2:** ほとんどのユーザーが移行されると、アプリケーションは外部 ID に切り継がれ、外部 ID はすべてのコア ユーザー フローのプライマリ認証サービスになります。

Azure AD B2C 移行ワークフローのダイアグラムは、ステージ、認証フロー、Azure Functions による移行を示しています。

詳細な実装手順については、「 [ユーザーと資格情報の移行: レガシ IdP によって開始される資格情報の収集](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-users#legacy-idp-initiated-credential-harvesting)」を参照してください。 B2C によって開始される移行では、このパターンは、サインイン時に REST API を呼び出して資格情報を検証および収集する、Azure AD B2C カスタム ポリシーを使用して実装されます。

#### 実装の手順

アプローチを決定したら、次の順序で完了します。

1. **ユーザー データを移行します。** 「 [ユーザーと資格情報を外部 ID に移行する」の](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-users#stage-1-migrate-user-data)ステージ 1 を完了します。

    ヒント

    多数のユーザー オブジェクトを移行すると、Microsoft Graphの調整制限が発生する可能性があります。 ベスト プラクティスについては、 [調整の制限](https://learn.microsoft.com/ja-jp/graph/throttling-limits) と [調整に関するガイダンス](https://learn.microsoft.com/ja-jp/graph/throttling) を参照してください。
2. **資格情報の移行を準備** します (パスワードを保持する場合)。 「 [ユーザーと資格情報を外部 ID に移行する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-users#stage-2-prepare-for-credential-migration) 」のステージ 2 を完了して、移行拡張機能プロパティとフラグ付きユーザー アカウントを設定します。
3. **選択したアプローチを実装します。**

    - **JIT。**[Just-in-Time パスワード移行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-passwords-just-in-time)
    - **B2C 開始型。**[ユーザーと認証情報の移行: レガシー IdP 開始型の認証情報収集](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-users#legacy-idp-initiated-credential-harvesting) (Azure AD B2C カスタム ポリシーを使用して実装)

### ステージ 4: 移行の計画、検証、監視

#### 移行の成功を検証する

AD B2C アプリAzure運用環境に移行して使用停止にする前に、エンド ツー エンドのユーザー体験と統合を検証します。

- すべての認証フロー (サインアップ、サインイン、パスワードリセット) をテストします。
- トークンの発行とカスタム要求を検証します。
- アプリケーションと API の統合をテストします。
- カスタム認証拡張機能を確認します。
- パスワード プロビジョニングとネイティブ認証をテストします。
- パフォーマンス テストとロード テストを実施します。
- セキュリティ ポリシーとユーザー データの整合性を確保します。

注

次のAzure AD B2C 機能はMicrosoft Entra 外部 IDでは使用できないため、移行前に対処する必要があります。

- **B2C カスタム ポリシーを使用して構成されたソーシャル ID プロバイダー。** ソーシャル フェデレーションは、外部 ID の組み込みのソーシャル ID プロバイダーサポートを使用して再構成する必要があります。 B2C カスタム ポリシーを使用して構成されたサード パーティの ID プロバイダーはサポートされていません。

ガイダンスについては、「 [テスト ユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-test-user-flows)、 [サンプル](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all)、 [カスタム拡張機能属性コレクション](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection) 」を参照してください。

#### 監視と最適化

運用環境に移行したら、監視と分析を実装してシステムの正常性を維持し、ユーザー エクスペリエンスを最適化します。

- Azure MonitorとMicrosoft Sentinelを使用してログ記録と監視を設定します。 詳細については、「[Azure Monitor 統合](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor)を参照してください。
- ユーザー アクティビティ、認証、エンゲージメントの分析情報には、組み込みのダッシュボードを使用します。 詳細については、「 [ユーザー分析情報](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights)」を参照してください。 User Insights は 2026 年 8 月 31 日に廃止されます。新しいデプロイの場合は、Log AnalyticsでAzure Monitorを使用します。 [「User Insights からの移行」](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights#migrate-from-user-insights)を参照してください。

継続的な監視により、予防的な問題の解決、データドリブンの最適化、CIAM ソリューションの継続的な改善が可能になります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/migrate-from-cognito-to-external-id"} -->
## Amazon Cognito から Microsoft Entra 外部 ID に移行する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-from-cognito-to-external-id
- Service: entra-external-id / external
- Article date: 2026-06-29
- Summary: 詳細なガイダンス、機能マッピング、検証戦略を使用して、Amazon Cognito からMicrosoft Entra 外部 IDに移行する方法について説明します。

現在、ソーシャル サインインで Amazon Cognito を使用しており、ワークロードをAzureに移行する予定の場合、このガイドは、機能マッピング、移行プロセス、ベスト プラクティスを理解するのに役立ちます。

このガイドは、コンシューマー向けアプリケーションを Amazon Cognito から Microsoft Entra 外部 ID に移行する開発者とアーキテクトを対象としています。 ソーシャル サインイン、トークン発行、グループベースの承認、カスタム要求、ラムダ トリガーのエンドツーエンドの移行について説明します。

### 達成する内容

学習内容は次のとおりです。

- Cognito ユーザー ベースを外部 ID テナント (ソーシャル ID プロバイダーにリンクされたユーザーを含む) に移行します。
- Facebook や Google などの既存のソーシャル ID プロバイダーを保持し、外部 ID で ID プロバイダーとして構成します。
- アプリケーションを AWS 増幅認証 (または Cognito SDK) から Microsoft Authentication Library (MSAL) に移動します。
- Cognito が発行したアクセス トークンを、API 呼び出しの外部 ID で発行されたトークンに置き換えます。 External ID は、コード交換のための証明キー（PKCE）を使用する OAuth 2.0 の認可コードフローを使用します。
- ディレクトリ ストレージ属性と出力されたトークン要求の区別を含め、Cognito 要求を外部 ID に対応付けることで、承認ロジックを保持します。
- ユーザーがソーシャル アカウントでサインインできること、および API がユーザーの代わりに機能することを検証します。

このガイドでは、次の 5 段階のアプローチを取ります。

- Plan
- 準備
- 実行
- Evaluate
- 廃止

### シナリオ例: ソーシャル サインインと API アクセスを使用するコンシューマー アプリ

次のシナリオは、このガイドの移行手順の基礎です。 シナリオが異なる場合、高レベルのアプローチは同じですが、一部の詳細が異なる場合があります。

コンシューマー向けの Web アプリケーション (コンテンツ プラットフォームやマーケットプレースなど) がある。 フロントエンドは、ユーザーをサインインのために Cognito でホストされる UI にリダイレクトするために、増幅認証を使用します。 ユーザーは Google または Facebook でサインインします。 Cognito は、最初のサインイン時にユーザー プールにユーザーを作成します。 トークンが発行される前に、Lambda トリガーが実行され、ユーザーのプロファイルまたは別のシステムでの参照に基づくカスタム要求が追加されます。 その後、Cognito はアクセス トークンと ID トークンを発行し、フロントエンドによってそれらを格納します。 Web アプリは、バックエンド API を呼び出すと、要求で Cognito アクセス トークンを送信します。 API はトークンを検証し、 `cognito:groups` 要求を確認して、ユーザーが実行できる操作を決定します。 このシナリオのターゲット状態では、同じアップストリームソーシャル ID プロバイダーで外部 ID で管理されるサインインが使用されます。

Note

このガイドでは、ソーシャル サインイン (Google、Facebook) を備えた 1 つの Cognito ユーザー プールと、バックエンド API を使用したコンシューマー向け Web アプリケーションについて説明します。 マルチプール アーキテクチャ、ID プール (AWS リソース アクセスのフェデレーション ID)、ブランド化を超えた Cognito でホストされる UI のカスタマイズ、およびサーバー間 (マシン間) フローは範囲外です。

目標は、ユーザーが新しいアカウントを作成したり、ソーシャル サインインをリセットしたりする必要なしに、アプリの ID スタック全体を外部 ID に移動することです。

#### アーキテクチャの概要

次の図は、移行前と移行後の認証フローを比較したものです。 重要な変更: 増幅認証は MSAL になり、Cognito は外部 ID マネージド サインインになり、API は `cognito:groups` の検証から `roles` の読み取りに切り替わります (または、グループベースの承認の `groups` )。

[!\[アーキテクチャの前後に、左側に Cognito フロー、右側に外部 ID フローが表示されています。\](media/migrate-from-cognito-to-external-id/cognito-entra-architecture-comparison.png)
 この図は、AWS から Azure への移行前と移行後の認証アーキテクチャを比較したものです。 移行前に、クライアント アプリは Amazon Cognito を使用してユーザーを認証し、外部 ID プロバイダーとフェデレーションし、Cognito アクセス トークンを使用してバックエンド API を呼び出します。 移行後、同じフローで Amazon Cognito ではなくMicrosoft Entra 外部 IDが使用されますが、クライアント アプリ、バックエンド API、Facebook または Google とのフェデレーションは保持されます。](media/migrate-from-cognito-to-external-id/cognito-entra-architecture-comparison.png#lightbox)

#### Prerequisites

- アクティブな外部 ID テナント。
- AWS コンソール、AWS CLI、または AWS SDK を使用してソース Cognito 環境にアクセスします。 IAM ユーザーまたはロールには、 `AmazonCognitoReadOnly` マネージド ポリシー (または、ユーザー プール、クライアント、ID プロバイダー、ユーザー、グループに対する同等の読み取りアクセス許可) が必要です。
- 移行する予定の各ソーシャル ID プロバイダー (Facebook と Google) の OAuth クライアント資格情報 (クライアント ID とシークレット)。
- 運用環境の前に移行を検証できる開発環境またはテスト環境。 運用テナントを介してテスト トラフィックを実行するのではなく、テストに別の外部 ID テナントを使用します。 この方法では、テスト ユーザーによる運用ディレクトリの汚染を回避し、リスクなくテスト テナントを削除して再作成できます。
- 外部 ID テナントに割り当てられた **アプリケーション管理者** ロールと **ユーザー管理者** ロールを持つアカウント。
- Azure サブスクリプション。
- 一括ユーザー プロビジョニングに対する Microsoft Graph APIアクセス許可。 移行アプリケーションには、少なくとも管理者の同意が必要な `User.ReadWrite.All` アプリケーションのアクセス許可が必要です。

### 手順 1: 計画する

この手順では、移行の信頼できるソース インベントリを作成します。 評価では、現在の Cognito 構成、Cognito トークンとクレームに対するアプリケーションの依存関係、および Cognito が削除された後も引き続き動作する必要がある運用システムをキャプチャする必要があります。

#### Amazon Cognito 環境を評価する

移行を開始する前に、Cognito で現在構成したすべての内容を確認して文書化します。 また、アプリケーションとバックエンド API で現在 Cognito がどのように使用されているかも理解する必要があります。 移行を計画するには、完全なインベントリが必要です。

Cognito ユーザー プール、アプリケーション コード、API 承認ロジックを確認し、次の情報を記録します。

- **ユーザー プールの設定:** ユーザー プール ID、リージョン、サインイン エイリアス (電子メール、電話、ユーザー名)、必要な属性、パスワード ポリシー、多要素認証 (MFA) の構成。
- **アプリ クライアント:** クライアント ID、クライアント シークレット (存在する場合)、許可された OAuth フロー、コールバック URL、サインアウト URL、許可された OAuth スコープ、クライアントに割り当てられた ID プロバイダー。
- **ID プロバイダー:** 構成されているソーシャル プロバイダーまたはエンタープライズ プロバイダー、それぞれに使用される OAuth クライアント ID とシークレット、属性マッピング。
- **カスタム属性:** ユーザー プールで定義されている `custom:*` 属性の一覧と、それらを使用するアプリ。
- **グループ:** すべての Cognito グループ、それらの優先順位の値、およびそれらに関連付けられている IAM ロール。
- **ラムダ トリガー:** 構成されるトリガー (事前サインアップ、事前認証、確認後、事前トークン生成、カスタム メッセージ、認証後、ユーザー移行、認証チャレンジの定義/作成/検証) と、それぞれの動作。 カスタム電子メール送信者とカスタム SMS 送信者トリガー。 (どちらも暗号化に AWS Key Management Service (KMS) が必要です)。 これらのトリガーを使用してサード パーティのプロバイダー (SendGrid、Twilio など) を介してメッセージを送信する場合は、プロバイダー、KMS キー、およびメッセージ テンプレートに注意してください。
- **ホストされる UI 設定:** ブランド化のカスタマイズ、CSS のオーバーライド、ロゴ。
- **ユーザー数と増加率:** アクティブなユーザー数、月間アクティブ ユーザー数、サインイン率のピーク。
- **ダウンストリーム イベント コンシューマー:** CloudWatch は、Cognito イベント、Cognito によってトリガーされる EventBridge ルール、Webhook データを受信するサードパーティ分析、およびユーザー プールに関連付けられている簡易通知サービス (SNS)/Simple Queue Service (SQS) 通知に関するアラームを通知します。 Cognito が使用停止になると、これらはすべて自動的に中断されます。

AWS CLI を使用して、この情報の大部分を抽出します。 例えば次が挙げられます。

```bash
aws cognito-idp describe-user-pool --user-pool-id <pool-id>
aws cognito-idp list-user-pool-clients --user-pool-id <pool-id>
aws cognito-idp list-identity-providers --user-pool-id <pool-id>
aws cognito-idp list-groups --user-pool-id <pool-id>
aws cognito-idp list-users --user-pool-id <pool-id>
```

これらのコマンドは、プールの構成、登録済みアプリ、フェデレーション プロバイダー、グループ定義、およびユーザー レコードを記述する JSON を返します。 この出力は、外部 ID テナントを計画するためのソース インベントリとして使用します。

#### ソーシャル サインインを超えて Cognito が所有しているものを理解する

すべてのユーザーが Facebook または Google 経由でサインインした場合でも、Cognito は多くの作業を処理します。

- Cognito は承認サーバーとして機能します。 アプリと API が使用するトークンを発行します。
- Cognito には、ユーザーのプロファイル、カスタム属性、およびグループ メンバーシップが格納されます。
- Cognito は、トークンの強化に使用するロジックを含むラムダ トリガーを実行します。
- Cognito は、トークンの有効期間、要求の内容、および `cognito:groups` 要求の形状を制御します。
- Cognito はトークンに署名します。 API は Cognito JWKS エンドポイントを信頼します。

外部 ID への移行では、ソーシャル ID プロバイダーをスワップアウトするだけでなく、Cognito が現在ユーザーに提供しているすべての機能を移動する必要があります。 サインイン、ユーザー、グループ、カスタム要求、ラムダ ロジックが含まれるように移行を計画します。

#### 直接的な機能マッピング

次の表は、Cognito の主要な概念を、Microsoft Entra IDの同等の概念にマップします。

| Cognito | 外部 ID |
| --- | --- |
| ユーザー プール | [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution) |
| アプリ クライアント | [アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) |
| ID プロバイダー (Facebook または Google) | [External ID のソーシャル ID プロバイダ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers) |
| SAML/OIDC フェデレーション | [SAML](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-overview) または [カスタム OIDC ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers) |
| Cognito グループ | [アプリ ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) (従業員以外のログインの場合) |
| カスタム属性 (`custom:*`) | [ディレクトリ拡張機能の属性](https://learn.microsoft.com/ja-jp/graph/extensibility-overview) |
| ホスト型 UI | [会社のブランドを使用したマネージド サインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)。完全なカスタム CSS またはピクセル レベルのパリティが必要な場合は、カスタム アプリでホストされる UI を使用する |
| ラムダ トリガー | [カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions) |
| PKCE を使用した承認コードの付与 | [PKCE を使用した OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) |
| Cognito アクセス トークン | [外部 ID アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) |
| `cognito:groups` 要求 | [`roles`](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)アプリ定義承認の場合、または直接グループベースの承認が必要な場合に[`groups`](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims) |
| `sub` (Cognito ユーザー ID) | [`oid`(Microsoft Entra オブジェクト ID)](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-token-claims-reference) |
| リソースサーバーとスコープ | [API と API のアクセス許可を公開する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis) |
| デバイスの追跡/デバイスの記憶 | [Conditional Access に準拠するデバイス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) または ["MFA を記憶する" セッション有効期間設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime) (Microsoft Entra ID P1 が必要) |

#### 機能の不一致と代替戦略

一部の Cognito 機能には、外部 ID に直接相当するものはありません。 次のセクションでは、各機能の機能、1 対 1 にマップされない理由、代わりに使用する内容について説明します。

##### アイデンティティプール

Cognito アイデンティティプールに直接対応する Azure の同等の機能はありません。 ID プールは、IAM ロールにバインドされている有効期間の短い AWS セキュリティ トークン サービス (STS) 資格情報のフェデレーション トークンを交換するために使用されます。 次のマッピングに対して設計上の決定を行う必要があります。

- **サービス間アクセス:** 一時的な資格情報の代わりに [マネージド ID を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) 使用します。
- **ユーザーを対象としたリソース アクセス:** Azure のロールベースのアクセス制御 (RBAC) の割り当て、またはユーザーの ID にスコープされた Shared Access Signature (SAS) トークンを使用します。
- **クライアント アプリからの直接リソース アクセス:** クライアントからリソースへの直接アクセスの場合は、ターゲット リソースのアクセス トークンをクライアントから直接取得します。 [On-Behalf-Of (OBO) フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)は、ダウンストリーム API を呼び出すためにユーザーのアクセス トークンを交換する中間層の機密 API でのみ使用します。
- アプリケーションで ID プールを使用する場合は、別のワークストリームとしてこの置換を計画します。 このガイドで説明する Cognito から外部 ID へのユーザー プールの移行以外のアーキテクチャの変更が必要です。

##### ソーシャル ID プロバイダー

Cognito では、ユーザー プール レベルでソーシャル ID プロバイダー (IdP) を 1 回登録し、アプリ クライアントごとに有効にします。 外部 ID では、テナント レベルでプロバイダーを 1 回登録し、それを受け入れる必要がある各ユーザー フローに追加します。 既存の Facebook または Google OAuth クライアントの資格情報を再利用できますが、Google および Facebook 開発者コンソールの許可リストに新しい外部 ID リダイレクト URI を追加する必要があります。

##### ホストされる UI とマネージド サインイン

Cognito では、ホストされる UI には、ロゴ、色、CSS カスタマイズなどのブランド化をユーザー プール レベルで含めることができますが、コールバック URL、OAuth フロー、スコープ、および有効な ID プロバイダーはアプリ クライアントごとに構成されます。 外部 ID では、このシナリオではマネージド サインインを使用して移行パターンを維持します。アプリはユーザーをMicrosoftホスト型サインイン エクスペリエンスにリダイレクトし、アプリの登録で Google や Facebook などのサインイン オプションを構成します。 マネージド サインインは会社のブランド化をサポートしますが、Cognito でホストされるすべての UI CSS カスタマイズの 1 対 1 の移行ターゲットではありません。 現在ホストされている UI がカスタム CSS またはピクセル レベルのコントロールに依存している場合は、次の 2 つのオプションがあります。

- マネージド サインイン のブランド化機能にエクスペリエンスを適応させます。
- 独自のアプリケーションでサインイン エントリ エクスペリエンスをホストし、MSAL を使用して外部 ID 承認フローを開始します。

##### グループと承認

Cognito グループは外部 ID グループのように見えますが、アプリ レベルの承認では、外部 ID では通常、よりクリーンに [アプリ ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)にマップされます。 アプリ ロールは、アプリの登録内で承認モデルを保持し、 `roles` 要求で出力され、テナント全体のグループの肥大化を回避し、グループの超過分の上限に達しないようにします (手順 3 で説明します)。 アプリに小さく安定したアクセス許可セット (管理者、エディター、ビューアー) がある場合は、プライマリ承認モデルとしてアプリ ロールを使用します。 管理を簡略化するために、エンタープライズ アプリケーションのアプリ ロールにセキュリティ グループを割り当てることができます。 直接グループ ベースの承認は、アプリがグループ メンバーシップを理由にする必要がある場合にのみ使用します。 既定では、外部 ID `groups` 要求にはグループ オブジェクト ID が含まれます。 `admin` や `viewer`などの移植可能なビジネス ラベルは含まれません。 詳細については、「 [アクセス トークン要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference)、 [省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)、 [およびグループ要求の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-group-claims)」を参照してください。

##### カスタム属性

Cognito の各 `custom:*` 属性は、外部 ID の顧客ユーザー属性にマップされます。この属性は、ユーザー オブジェクトのディレクトリ拡張属性として格納されます。 Microsoft Graphでは、これらの属性はストレージの名前付けパターン `extension_<appid>_<name>`を使用します。 トークン要求名は異なる場合があります。 たとえば、カスタム認証拡張機能では、ディレクトリから `extension_<appid>_tier` を読み取り、 `tier`という名前のビジネスに適したトークン要求を出力できます。 アプリ登録で拡張機能属性を構成し、ユーザー フローまたはカスタム認証拡張機能を使用して発行されたトークンに含めます。 カスタム認証拡張機能を使用すると、認証フロー内の特定のポイントで、独自のビジネス ロジックを使用して認証フローを拡張できます。 アクティブ化すると、ワークフロー アクションを定義する REST API エンドポイントへの HTTP 呼び出しが行われます。 TokenIssuanceStart 要求ペイロードに、エンリッチメントに必要なすべてのロール、グループ、またはカスタム属性が含まれているとは想定しないでください。 要求エンリッチメントがこれらの値に依存する場合は、外部参照を必要としない確定的なマッピング、または最小特権アプリケーションのアクセス許可を持つマネージド ID または機密クライアント資格情報を使用するMicrosoft Graph参照を設計します。 詳細については、 [外部 ID ユーザー属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes)、 [カスタム属性の定義](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)、および [トークンへのカスタム ユーザー属性の追加に関するページを](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-attributes-to-token)参照してください。

##### トークンの有効期間

Cognito を使用すると、アプリ クライアントごとにアクセス トークン、ID トークン、更新トークンの有効期間を設定できます。 外部 ID は、有効期間ポリシーを通じてアクセス トークンと ID トークンの構成可能な有効期間をサポートします。 更新トークンの有効期間は [、外部 ID では構成できません](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)。 Cognito アプリ クライアントが意図的なセキュリティ制御として更新トークンの有効期間が短い場合は、代わりに [条件付きアクセスのサインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime) を使用します。 アクセス トークンと ID トークンの場合は、カットオーバー時の動作の変化を最小限に抑えるために、アプリごとの Cognito クライアントの値を一致させます。

##### イベント マッピング: カスタム認証拡張機能へのラムダ トリガー

Cognito Lambda トリガーは、認証フロー中にAzure関数 (または任意の HTTPS エンドポイント) を呼び出す外部 ID カスタム認証拡張機能になります。 すべてのトリガーに 1 対 1 の同等の機能があるわけではありません。 このセクションのマッピングでは、サポートされている製品パターンについて説明します。

| Cognito ラムダ トリガー | 外部 ID と同等 | 注記 |
| --- | --- | --- |
| 事前サインアップ | [属性コレクションの開始](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview#attribute-collection-start) と [属性コレクションの送信](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview#attribute-collection-submit) | 入力の検証、サインアップのブロック、自動確認。 |
| 事前認証 | パスワード送信時のカスタム認証拡張機能 | Just-In-Time (JIT) パスワード移行に使用: 最初のサインイン時に Cognito の API に対してパスワードを検証し、成功した場合は外部 ID に書き込みます。 ローカル アカウント (電子メールとパスワード) があり、パスワードの強制リセットではなく JIT 移行を選択する場合にのみ関連します。 |
| 投稿確認 | ダイレクト イベント トリガーなし | Microsoft Graph変更通知またはユーザー作成に対応するロジック アプリを使用します。 |
| 事前トークンの生成 | [トークン発行の開始](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview#token-issuance-start-event-listener) | 追加の要求を使用してトークンを強化します。 |
| 認証後 | 直接同等の機能はありません | サインイン ログとAzure Monitorを使用してダウンストリーム アクションをトリガーします。 |
| カスタム メッセージ | ダイレクト イベント トリガーなし | 外部 ID は、構成可能な電子メール プロバイダーとブランドを使用します。 完全カスタム メッセージ ロジックは、イベントとしてサポートされていません。 ソーシャル サインイン ユーザーには関係ありません。サインイン メッセージはソーシャル プロバイダー (Google、Facebook) によって処理されるためです。 電子メール ワンタイム パスコード (OTP) またはパスワード リセット フローを使用するローカル アカウントにのみ適用されます。 |
| ユーザー移行 | [パスワード送信時のカスタム認証拡張機能 (JIT)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-passwords-just-in-time) | 最初のサインイン時に Cognito に対してパスワードを検証し、外部 ID に移行します。 ローカル アカウント (電子メールとパスワード) にのみ関連します。 移行するパスワードがないため、ソーシャル サインイン ユーザーには適用されません。 |
| 認証チャレンジの定義/作成/検証 | [カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions) | Cognito の完全なカスタム認証フロー (SMS ベースのパスワードレスやセキュリティの質問など) に使用されます。 一対一のマッピングではありません。 外部 ID カスタム認証拡張機能モデルを使用してフローを再構築する必要があります。 ソーシャル サインイン ユーザーは Google または Facebook 経由で認証を行い、カスタム チャレンジ フローをバイパスするため、関連しません。 |

Tip

確認後トリガー、認証後トリガー、およびカスタムメッセージトリガーには、External ID に直接対応するイベントベースの同等機能はありません。 現在の Cognito セットアップがこれらのトリガーのいずれかに依存している場合は、別の方法を計画します。 確認後のアクションでは、Microsoft Graph変更通知に対応することが一般的な回避策です。 カスタム メール コンテンツの場合、外部 ID のブランド化と言語のカスタマイズが最も近いオプションです。

製品の詳細については、 [カスタム認証拡張機能の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)、 [カスタム認証拡張機能のリソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/customauthenticationextension)、 [トークン発行の開始構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration)、 [属性コレクション拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection)、および [ワンタイム パスコードのカスタム 電子メール プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-email-otp-get-started)を参照してください。

##### 外部 ID 承認コード フロー エンドポイント

外部 ID は、次のパターンに従って、専用ドメイン上の OIDC および OAuth 2.0 エンドポイントを公開します。

- 承認： `https://<tenant-name>.ciamlogin.com/<tenant-id>/oauth2/v2.0/authorize`
- トークン： `https://<tenant-name>.ciamlogin.com/<tenant-id>/oauth2/v2.0/token`

Microsoft Entra 管理センターの正確な URL は、**アプリの登録**&gt;**Endpoints** の下、または `https://<tenant-name>.ciamlogin.com/<tenant-id>/v2.0/.well-known/openid-configuration` の OpenID Connect 検出ドキュメントから確認できます。 外部テナントの場合、メタデータによって返される発行者の値は、ホスト内のテナント ID とパス ( `https://<tenant-id>.ciamlogin.com/<tenant-id>/v2.0`) を使用します。 手動で構築された値ではなく、メタデータ ドキュメントの発行者値に対してトークンを検証します。

Cognito と外部 ID は同じフローをサポートします。

**変更内容:**

- **承認エンドポイント:**`https://<cognito-domain>/oauth2/authorize`からテナントの外部 ID 承認エンドポイントに移動します。
- **トークン エンドポイント:**`https://<cognito-domain>/oauth2/token`から外部 ID トークン エンドポイントに移動します。
- **スコープ：** Cognito スコープは `<resource-server-identifier>/<scope>`書式設定されますが、外部 ID スコープは `<application-id-uri>/<scope>`書式設定され、通常はアプリケーション ID URI が `api://<api-client-id>`されます。 外部 ID では、API **を公開**して API を定義し、そこでスコープを宣言し、 **API アクセス許可**としてクライアント アプリに付与します。 クライアントは、API を呼び出すときにこれらのスコープを要求します。
- **観客：** アクセス トークンに表示されるランタイム対象ユーザーの値を検証します。 Microsoft ID プラットフォーム v2.0 アクセス トークンでは、通常、`aud`は API アプリケーションのクライアント ID (GUID) です。 v1.0 トークンでは、アプリケーション ID URI を指定できます。 Cognito アクセス トークンは、 `client_id`を使用してアプリ クライアントを識別します。 `aud`要求は、アプリが API の Cognito リソース バインディングを要求した場合にのみ表示されます。 API の JSON Web トークン (JWT) 検証コントロールは、トークンのバージョンとプロバイダーの動作に基づいて、予想される `aud`、予想される `iss`、および JWKS URI を更新する必要があります。 詳細については、「 [アクセス トークン要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference)」、 [「要求の検証」](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation#validate-the-audience)、および [「Cognito アクセス トークン要求」](https://docs.aws.amazon.com/cognito/latest/developerguide/amazon-cognito-user-pools-using-the-access-token.html)を参照してください。

[承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)に関するMicrosoft ID プラットフォームドキュメントは、正確な要求と応答の形式のリファレンスです。

[!\[PKCE を使用した OAuth 2.0 承認コード フローを示す図。クライアント、外部 ID、バックエンド API が表示されます。\](media/migrate-from-cognito-to-external-id/authorization-code-flow-pkce-external-id.png)
 この図は、クライアント アプリが PKCE を使用して OAuth 2.0 承認コード フローを使用して、Microsoft Entra 外部 IDを介してユーザーをサインインすることを示しています。 アプリはユーザーをサインインにリダイレクトし、認証コードをトークンと交換し、アクセス トークンと ID トークンを受け取ります。 アプリはアクセス トークンを使用してバックエンド API を呼び出し、API は応答を返す前にトークンを検証します。](media/migrate-from-cognito-to-external-id/authorization-code-flow-pkce-external-id.png#lightbox)

#### カットオーバー アプローチを選択する

次の 2 つのカットオーバー アプローチから選択します。

- **段階的。** 一度に 1 つのユーザー セグメント。
- **ビッグバン。** カットオーバー期間を 1 回にまとめ、全員が一斉に実施する。

**可能であれば、段階的なカットオーバーを選択します。** ソーシャル サインインを使用する顧客向けアプリの場合、段階的なカットオーバーによって中断が最小限になり、すべてのユーザーに影響を与える前に問題を見つける時間が与えられます。 ビッグバン方式のカットオーバーは、次の場合にのみ適切です。

- 移行は、信頼性の高い非運用環境でエンド ツー エンドでステージングできます。
- 従来の Cognito ユーザー プールは、定義済みのフォールバック期間に対して読み取り専用のまま使用できます。
- メンテナンス期間は短く、明確に定義されています。 (ほとんどのコンシューマー向けアプリでは使用できません)。
- ユーザー ベースは十分に小さいので、ロールバックは安価です。

このシナリオでは、段階的なカットオーバーがどのように表示されるかを次に示します。

1. **ユーザーを外部 ID に事前に移行する。** Microsoft Graphを使用して、外部 ID でユーザー アカウントを一括作成します。 ソーシャルにリンクされたユーザーの場合は、各ユーザー オブジェクトに `identities` プロパティを設定して、どの Google または Facebook アカウントがどのユーザーにマップされるかを外部 ID が認識するようにします。 このステップは、いかなるカットオーバーよりも前に行われます。
2. **少数のサインインを外部 ID にルーティングします。** アプリは、機能フラグまたは構成設定に基づいて OAuth フローを開始する ID プロバイダーを選択します。 5% のトラフィックから始めます。 外部 ID を使用してサインインするユーザーは、新しいテナントを介して認証を行います。また、手順 1 でフェデレーション ID が事前にリンクされているため、再登録を求められずに既存のユーザー アカウントと照合されます。
3. **監視して拡張する。** 外部 ID グループのサインイン成功率、トークン検証、API 承認の結果を確認します。 すべてが適切に見える場合は、割合を増やします。 問題が発生した場合は、機能フラグをロールバックし、展開する前に修正します。 SDK が埋め込まれたモバイル アプリの場合、ロールバックには、構成の変更ではなく、ストア レビューによる新しいアプリ リリースが必要になるため、モバイル ウェーブを控えめに計画してください。
4. **非アクティブなユーザーをバックフィルします。** 移行期間中にサインインしなかったユーザー (非アクティブまたは破棄されたアカウント) には、次の 2 つのオプションがあります。

    - 手順 1 で事前に移行しておけば、今後戻ってくることがあっても対応できます。
    - それらを Cognito に残しておき、切り替え後にログインした場合は、必要に応じて移行します。 (アプリが Cognito 専用ユーザーを検出し、最初のサインイン時に外部 ID に移行する軽量 JIT パターンを使用します)。

    ほとんどの組織は、JIT 検出の複雑さを回避するために、すべてのユーザーを事前に移行します。

ビッグ バン カットオーバーは、短く明確に定義されたメンテナンス期間、小さなユーザー ベース、またはデュアル ID プロバイダーを並列で実行できない場合にのみ適しています。

#### その他の考慮事項

ターゲット テナントの構成を開始する前に、これらの計画に関する考慮事項を使用して、移行アプローチが実行可能かどうかを判断します。 これらは、運用上の影響を見積もり、準備基準を定義し、各移行ウェーブ中にロールバックを実用的に保つのに役立ちます。

##### Licensing

外部 ID には、一定の数の月間アクティブ ユーザー (MAU) が無料で含まれます。 この制限を超える場合、価格は認証ごとに行われます。 条件付きアクセス ポリシーには、少なくとも Microsoft Entra ID P1 ライセンスが必要です。 Microsoft Entra ID 保護 (リスクベースのサインイン、侵害された資格情報の検出) には、P2 ライセンスMicrosoft Entra ID必要があります。 [外部 ID の価格ページ](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)と[Microsoft Entra ID ライセンス ガイドを](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)確認して、ユーザー ベースのコストを見積もります。

##### 成功基準

明確な成功条件を設定します。

- 移行前ベースラインの *X*% 内のサインイン成功率
- Cognito ベースラインの *Y* ミリ秒内の待ち時間の中央値
- API認可結果にリグレッションはありません

手順 4 で、移行の成功を承認するかどうかを評価する際に、成功条件を使用します。

##### ロールバック計画

1. 各移行ウェーブの検証が完了するまで、Cognito を完全に動作させ続けます。
2. 移行中は、ソーシャル プロバイダー コンソールで Cognito リダイレクト URI と外部 ID リダイレクト URI の両方をアクティブのままにします。
3. ウェーブの検証に失敗した場合は、アプリの構成を Cognito に戻します。 (MSAL 機関を Cognito ドメインに更新するか、増幅認証バージョンを再デプロイします)。
4. 各ウェーブに、ステップ 4: 評価の検証チェックリストに基づいた実行可否のチェックポイントがあることを確認してください。
5. 最終ウェーブが検証を通過し、Cognito へのトラフィックが少なくとも 72 時間にわたってゼロであることを確認した後にのみ、Cognito のリダイレクト URI を削除し、ユーザープールを無効化してください。
6. ロールバック前にユーザーが外部 ID でプロファイルをサインアップまたは変更した場合、それらの変更は外部 ID ディレクトリにのみ存在します。 調整プロセスを文書化します。新しい外部 ID ユーザーまたはプロファイルの変更をエクスポートし、Cognito で再作成するか、次の移行の試行で繰り越すかを決定します。

#### 移行手順書

運用環境の変更を開始する前に、チームの移行 Runbook を作成します。 Runbook は、実行フェーズ (手順 3) の間にチームが従う詳細な運用ドキュメントです。 カットオーバーを開始する前に、チームがシーケンス、責任、通信チェックポイント、および決定基準に同意するように、計画中に定義します。 以下の番号付きの手順は、ランブックの概要を示しています。 チームのツールとコミュニケーション チャネルに合わせて調整します。

**移行ウェーブ**は、Cognito から外部 ID に一緒に移動する定義済みのユーザー コーホートです。 コホートは、トラフィックの割合、アプリのバージョン、地域、または顧客セグメントで定義できます。 各ウェーブに対して同じ順序付けされたプロセスを実行し、現在のウェーブが検証に合格した後にのみ次のウェーブに展開します。

Runbook では、移行ウェーブごとに次の手順を説明する必要があります。

1. 実施可否の条件を確認します。パイロット検証に合格し、監視が有効になっており、ロールバックの準備が整い、移行スクリプトのテストが完了し、サポート チームが対象ウェーブの範囲を把握していることを確認します。
2. 機能フラグまたは構成スイッチの背後にまだデプロイされていない場合は、最終的に準備されたアプリケーション コードをデプロイします。
3. 準備されたカスタム認証拡張機能の有効化やアプリ ロールの割り当ての確認など、パイロットで既に検証された最終的な外部 ID 構成の変更のみを適用します。
4. ウェーブ用に準備されたユーザー移行スクリプトを実行し、出力を調整します。
5. ウェーブの運用トラフィックをルーティングする前に、移行されたユーザーを検証します。
6. ウェーブのカットオーバー ウィンドウを開始し、選択したユーザーを外部 ID にルーティングします。
7. 両方のシステムを並列で実行し、サインイン、トークン発行、API 承認、カスタム拡張機能の正常性、サポート シグナルを監視します。
8. 障害のしきい値を超えた場合はウェーブをロールバックするか、検証に合格したら次のウェーブに展開します。

#### MFA メソッドの移行を計画する

Cognito MFA 設定は自動的に引き継がれない。 外部 ID の顧客テナントは、電子メール ワンタイム パスコードと SMS ベースの認証 ( [アドオン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers)として) を第 2 要素の方法としてサポートします。 パスキー (FIDO2) は、外部 ID プロバイダー ユーザーの 2 番目の要素として使用できません。 Cognito からの認証アプリの登録は、サポートされている移行ターゲットではありません。

Cognito Authenticator アプリ MFA を使用したユーザーの場合は、移行ウェーブの前に、サポートされている外部 ID メソッドへの移行を計画します。 ユーザーが使用を求める 2 番目の要素を理解できるように、カットオーバーの前に変更を伝えます。

MFA ユーザーの移行エクスペリエンスは、MFA 以外のユーザーとは異なります。 最初に MFA 以外のユーザーを移行してコア フローを検証し、次に MFA ユーザーを別のウェーブとして移行し、対象となる通信を提供することを検討してください。

#### パスワード移行戦略を選択する

ローカルの Cognito アカウント (メールとパスワード) を持つユーザーの場合、Cognito はパスワード ハッシュをエクスポートしません。 次の 3 つのオプションのいずれかを選択し、カットオーバーの前にユーザーに通知します。

- **パスワードの強制リセット (推奨):** パスワードなしでユーザー アカウントを移行し、最初のサインイン時にアカウントにパスワード リセットのマークを付け、ユーザーが外部 ID セルフサービス フローを使用して新しいパスワードを設定できるようにします。 このオプションは、最も単純で最も信頼性の高いパスです。 最初のサインインからの外部 ID パスワード ポリシーにユーザーを合わせます。 ユーザーが驚かないように、カットオーバーの数日前に電子メールでプロアクティブな通信を提供します。
- **Just-In-Time (JIT) パスワードの移行:** カスタム認証拡張機能は、最初のサインイン時に Cognito の `AdminInitiateAuth` API に対してパスワードを検証し、その後、パスワードを外部 ID に書き込みます。 ユーザーは移行に気付きません。 このオプションでは、より多くの実装と運用作業が必要です。 JIT ウィンドウ内で Cognito に到達可能な状態を維持する必要があり、独自の実装と検証のワークストリームとして追跡する必要があります。
- **パスワードレスへの移行:** ローカル アカウントを電子メールワンタイム パスコードに切り替え、パスワードを完全に削除します。

既定として、強制パスワード リセットを使用します。 JIT は、企業がパスワードのリセットを必要としない場合にのみ使用します。 完全なパスワードリセットを伴うフルマイグレーションは、レガシーシステムが稼働し続けることに依存するJITフローよりも見通しが立てやすく、障害が発生する箇所も少なくなります。

### 手順 2: 準備する

これで、Cognito 環境のインベントリ、カットオーバー アプローチ、移行 Runbook が文書化されました。 次に、ターゲット環境を構築し、パイロットでエンド ツー エンドで検証します。

この手順では、ユーザーを移動または運用トラフィックを切り替える前に、ターゲット環境と移行資産を準備します。 最初にパイロット環境でこれらのアクティビティを完了してから、運用環境で検証済みの構成を繰り返します。

#### パイロット環境を準備する

運用環境の外部 ID テナントで何かを構成する前に、テスト ユーザーの少数のグループで移行をエンド ツー エンドで検証できる別のパイロット テナントを設定します。 まず、アプリの登録、マネージド サインイン、ソーシャル ID プロバイダー、API のアクセス許可、アプリ ロール、監視、コード構成、移行スクリプトなど、パイロットを構成します。 パイロットが検証に合格したら、運用テナントで同じ構成を繰り返します。

受け入れ基準を前もって定義します。

- ユーザーは、Facebook や Google などの構成済みのソーシャル ID プロバイダーを使用してサインインできます。
- 新しいサインアップにより、ディレクトリにユーザーが作成されます。
- Cognitoから移行された既存ユーザーは、再度登録を求められることなくサインインできます。
- API は外部 ID アクセス トークンを受け取り、アプリ ロールに基づいてユーザーを承認します。または、グループ ベースの承認を明示的に構成した場合は、グループ要求を承認します。 アプリ ロールを使用する場合は、API アプリの登録/サービス プリンシパルでロールが定義され、割り当てられていることを確認して、API アクセス トークンに表示されるようにします。
- 監視では、パイロット検証中のサインイン エラー、API 承認エラー、およびカスタム拡張機能のエラーがキャプチャされます。
- 移行スクリプトでは、Cognito からパイロット ユーザー セットをエクスポートし、手動で変更することなく、外部 ID で対応するユーザーを作成または更新できます。

パイロットでこれらの条件のいずれかが失敗した場合は、運用環境に修正されたセットアップを適用する前に、パイロット環境で構成、コード、または移行スクリプトを修正します。

#### 準備のための特権アクセスを確認する

パイロットまたは運用テナントを構成する前に、このフェーズを実行するオペレーターが各タスクを完了するために必要な特権を持っていることを確認します。 アプリの登録の作成、API スコープの公開、マネージド サインインの構成、アプリ ロールの割り当て、Microsoft Graphによるユーザーの作成、監視エクスポートの構成には、異なる管理ロールまたはMicrosoft Graphアクセス許可が必要な場合があります。 少なくとも、外部 ID テナントの**アプリケーション管理者**ロールと**ユーザー管理者**ロールを計画し、パイロットを開始する前に、Log Analytics、Azure Monitor、Azure Functions、マネージド ID、または CI/CD シークレットに必要な追加の特権を確認します。

#### 外部テナントを作成して構成する

パイロット外部 ID テナントがまだない場合は、運用テナントを作成または変更する前に、Microsoft Entra 管理センターにテナントを作成します。 データ所在地の整合性を維持するには、Cognito ユーザー プールの場所と一致する地理的な場所を選択します。 外部 ID は、テナントの作成時に選択した地理的リージョンにユーザー プロファイル データ、資格情報、および認証メタデータを格納します。 この選択は永続的であり、作成後に変更することはできません。 厳格なデータ所在地要件があるワークロードについては、先に進む前に [Microsoft Entra ID が ID データを保存する場所](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency) を確認してから、[Go-Local アドオン](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency#go-local-add-on) を使用してください。

別のアプリの外部 ID テナントが既にある場合は、それを再利用できます。 各テナントは、複数のアプリ登録、ユーザー フロー、およびソーシャル IdP 構成をサポートします。

#### アプリケーションの登録とユーザー フローの構成

Cognito アプリ クライアントごとに、外部 ID で一致するアプリ登録を作成します。

1. クライアント アプリ (ユーザーがサインインする Web アプリ) を登録します。 リダイレクト URI を、移行後にアプリが使用するコールバックに設定します。 PKCE を使用して承認コード フローを有効にします。
2. バックエンド API を別のアプリ登録として登録します。 **API の公開**で、アプリケーション ID URI を設定し、必要なスコープ (Cognito リソース サーバー スコープと同等) を宣言します。
3. クライアント アプリの登録で、API **アクセス許可**の下に API スコープを追加します。
4. API がアプリ ロールによって承認される場合は、バックエンド API アプリの登録でロールを定義し、API エンタープライズ アプリケーション上のこれらのロールにユーザーまたはセキュリティ グループを割り当てます。 クライアント アプリでのみ定義されたロールは、API アクセス トークンには表示されません。
5. 外部 ID でユーザー フロー (サインアップとサインイン) を作成します。 ID プロバイダー、サインアップ時に収集する属性、および認証方法を構成します。
6. クライアント アプリをユーザー フローに関連付け、リダイレクト URI、サインアウト後のリダイレクト URI、スコープ、API のアクセス許可がパイロット アプリの構成と一致することを確認します。

#### ホストされている UI からマネージド サインインへの移行を計画する

Cognito のホスト型 UI でカスタムブランディングを使用している場合は、マネージドサインインを設定する前に、現在ユーザーに表示されている内容を記録してください。 ロゴ、色、テキスト、カスタム ドメインの使用状況、言語の動作、サインイン画面とサインアップ画面、パスワード リセット エントリ ポイント、およびカスタム CSS に依存する動作をキャプチャします。

外部 ID で、最初にパイロット テナントで最も近いマネージド サインイン エクスペリエンスを構成します。 会社のブランドがユーザー エクスペリエンスの要件を満たしているかどうかを検証します。 そうではない場合は、切り替え日まで判断を先送りしないでください。 MSAL が外部 ID を使用して標準ベースの承認フローを処理している間に、マネージド サインインの違いを受け入れるか、ユーザー通信を更新するか、ユーザー向けのエントリ エクスペリエンスをアプリケーションに移動するかを準備中に決定します。

#### ソーシャル ID プロバイダーを構成する

Facebook や Google など、現在使用しているのと同じアップストリームソーシャル ID プロバイダーを外部 ID テナントに追加します。 移行により、ブローカーとトークンの発行者が Cognito から外部 ID に変更されます。 アップストリームのソーシャル プロバイダー アカウントは置き換えられません。

ソーシャル プロバイダーの開発者コンソール構成を変更する前に、評価インベントリから現在の Cognito コールバックとサインアウト URL を確認します。 Cognito 用に既に構成した既存の Google および Facebook OAuth クライアント資格情報を再利用できます。 Google Cloud コンソールと Meta for Developers ポータルに移動し、Cognito リダイレクト URI の横にある承認された一覧に外部 ID リダイレクト URI を追加します。 手動で作成するのではなく、各プロバイダーのMicrosoft Entra 管理センターから正確なリダイレクト URI をコピーします。 (形式はプロバイダーによって異なります。たとえば、カスタム OIDC の `/federation/oauth2` 、Google と Facebook のプロバイダー固有のパスなど)。Cognito リダイレクト URI はまだ削除しないでください。 切り替え中は、両方のセットがアクティブである必要があります。

次に、外部 ID 管理センターで、ID プロバイダーとして [Google](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers) と [Facebook](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers) を追加し、OAuth クライアント ID とシークレットを貼り付けて、マネージド サインインのサインイン オプションとして追加します。

カスタム OIDC プロバイダーは、 [カスタム OIDC フェデレーション フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers)に従います。 プロバイダーの既知の検出エンドポイント、クライアント ID、クライアント シークレットが必要です。 カスタム以外のプロバイダーとは異なり、クレーム マッピング (`sub`、 `email`、 `name`など) は、Cognito の内容と一致するように明示的に構成する必要があります。

#### カスタム ドメインを移行する (該当する場合)

Cognito 環境でカスタム ドメイン ( `auth.example.com` など) を使用している場合は、DNS 移行を計画します。 Cognito と External ID の両方に同じドメインを同時に指定することはできません。 いくつかのオプションを次に示します。

- デュアル実行中は、外部 ID に別のサブドメインを使用します。 (たとえば、Cognito を指す`login.example.com`を維持しながら、外部 ID に`auth.example.com`を使用します)。カットオーバー後、必要に応じて元のドメインを外部 ID に切り替えることができます。
- External ID でカスタム ドメインを使用するには、External ID テナントの前段にリバース プロキシとして Azure Front Door を使用する必要があります。 「[カスタム URL ドメインを使用](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)する」の手順に従います。これには、Azure Front Door インスタンスの作成、カスタム ルーティング規則の構成、標準名 (CNAME) レコードを使用したカスタム ドメインの追加が含まれます。 切り替えが迅速に有効になるように、DNS の有効期間 (TTL) を低い値 (300 秒など) に設定します。

#### 運用構成の前にパイロット準備を確認する

運用環境で構成を繰り返す前に、この手順の最初のパイロット環境が受け入れ基準に合格したことを確認します。 条件が満たされない場合は、運用環境にセットアップを適用する前に、パイロットの構成、コード、監視、または移行スクリプトを修正します。

#### 監視を設定する

パイロット検証の前と運用カットオーバーの前に監視を設定します。 監視は移行準備の一部であり、フォローアップ タスクではありません。 手順 1 で説明した監視ベースライン (サインイン成功率、トークン発行待機時間、ラムダ トリガーの実行、失敗率に関する Cognito CloudWatch メトリック) を確認して、Cognito での可視性を一致または超過します。 Azureで同じメトリックをレプリケートします。

- **サインイン ログ:** 外部 ID サインイン ログには、すべての認証試行が表示されます。 構成の問題をキャッチするために、アプリケーションとエラーの理由でフィルター処理します。 これにより、Cognito のサインイン CloudWatch メトリックが置き換えられます。
- **Azure Monitor:** サインイン ログと監査ログを Log Analytics ワークスペースにエクスポートします。 障害率の急増とカスタム拡張機能のタイムアウトに関するアラートを設定します。 Cognito と同じアラートしきい値を構成して、回帰をすぐにキャッチできるようにします。
- **カスタム拡張機能メトリック:** カスタム認証拡張機能の [既定のタイムアウトは 1 秒](https://learn.microsoft.com/ja-jp/graph/api/resources/customextensionclientconfiguration)で、最大 2 秒まで構成できます。 Azure Functionsのコールド スタートは、多くの場合、この制限を超えています。 詳細な待機時間データはコンピューティング 層に配置され、外部 ID は拡張機能の呼び出しが失敗したログのみを記録します。 タイムアウトのしきい値に近づく P99 レイテンシに対して、拡張機能のコンピュートにアラートを設定します。 これをラムダ トリガー CloudWatch メトリックと比較します。 カスタム拡張機能がタイムアウトになったり、エラーが返されたりすると、外部 ID は拡張機能の要求なしで続行されます。 不足しているクレームがある場合に、サインインをブロックする (フェイル クローズ) か、機能を制限した上でサインインを許可する (フェイル オープン) かを事前に決定し、そのロジックを API ミドルウェアに実装します。
- **ユーザー アクティビティ ダッシュボード:** 外部 ID には、サインアップ、サインイン、MFA の使用のための組み込みのダッシュボードが含まれています。 これらのダッシュボードを使用して、ユーザーの動作が移行前のベースラインと一致することを検証します。

#### 移行スクリプトの作成とテスト

準備フェーズ中に移行スクリプトまたは Runbook を作成します。 スクリプトでは、Cognito からユーザーを読み取り、データを外部 ID ターゲット図形に変換し、Microsoft Graphを使用してユーザーを作成または更新する必要があります。 運用環境で実行する前に、小規模なユーザー セットを使用してパイロット テナントに対してスクリプトをテストします。

スクリプトのエクスポート フェーズでは、 `ListUsers` API と `AdminGetUser` API を使用し、改ページ処理を処理し、ターゲット外部 ID ユーザー オブジェクトに必要なフィールドをキャプチャする必要があります。

- ユーザー名または電子メール
- 電子メール検証済みフラグと電話検証済みフラグ
- リンクされた各ソーシャル サインイン アカウントのフェデレーション ID の詳細 (プロバイダー、外部サブジェクト ID)
- カスタム属性
- `AdminListGroupsForUser` のグループ メンバーシップ
- MFA 構成 (該当する場合)

Cognito はパスワード ハッシュを公開しません。 ソーシャルのみのユーザーの場合、移行する必要がないため、この設計は問題ありません。 ローカル アカウント ユーザーの場合は、強制パスワード リセットまたは [JIT パスワード移行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-passwords-just-in-time)を計画します。

インポート フェーズでは、Microsoft Graph `/users` エンドポイントを介してユーザーを作成する必要があります。 ソーシャルリンクされたユーザーの場合、スクリプトはユーザー オブジェクトに各フェデレーション ID を追加する必要があります。 ユーザーが次に Facebook または Google アカウントを使用してサインインすると、外部 ID は `issuer` と `issuerAssignedId` に一致し、新しいアカウントを作成するのではなく、事前に作成されたアカウントを再利用します。 最初のサインインの前に、設定された `identities` コレクションが存在している必要があります。

| Provider | `issuer`値 | `issuerAssignedId`値 |
| --- | --- | --- |
| Google | `google.com` | ユーザーの Google `sub` (Cognito の `identities` 属性から) |
| Facebook | `facebook.com` | ユーザーの Facebook ユーザー ID (Cognito の `identities` 属性から) |

Google にリンクされたユーザーの Microsoft Graph API 呼び出しの例を次に示します。

```json
POST /users
{
  "displayName": "Jane Doe",
  "accountEnabled": true,
  "userPrincipalName": "janedoe_google.com#EXT#@<tenant>.onmicrosoft.com",
  "identities": [
    {
      "signInType": "federated",
      "issuer": "google.com",
      "issuerAssignedId": "110169484474386276334"
    }
  ]
}
```

`issuer`または`issuerAssignedId`が間違っている場合、外部 ID は次のソーシャル サインインを新しいユーザーとして扱い、重複するアカウントを作成します。 Cognito ユーザー オブジェクト (`identities` によって返される) の`AdminGetUser` JSON 属性からプロバイダーのサブジェクト ID をプルします。

Cognito グループがアプリケーションのアクセス許可を表すときに、主にアプリ ロールにマップします。 直接グループベースの承認が必要な場合は、それらを外部 ID グループにマップし、 `groups` 値を出力したドキュメントは既定でグループ オブジェクト ID になります。 各ユーザーのメンバーシップまたはロールの割り当てに同じマッピングを適用します。

移行拡張機能のプロパティ パターンを使用して、移行されるユーザーと移行されていないユーザーにフラグを設定します。 このフラグは、ソーシャル プロバイダーとローカル アカウントのどちらでサインインするかに関係なく、移行の進行状況を追跡したり、段階的なカットオーバー中にユーザーを識別したりするのに役立ちます。

##### 大規模なユーザー ベース

多数のユーザーを移行する場合は、Microsoft Graph のスロットリングにより、この手順の完了にかなりの時間がかかります。 [Microsoft Graphバッチ処理を](https://learn.microsoft.com/ja-jp/graph/json-batching)使用して要求数を減らし、429/503 応答で返された`Retry-After` ヘッダーを考慮してバックオフ ロジックを実装し、[調整制限](https://learn.microsoft.com/ja-jp/graph/throttling-limits)に繰り返し達しないようにします。

フォローアップ`identities`呼び出しではなく、最初の`POST`呼び出しで`PATCH`属性とカスタム属性を記述します。

バッチ処理、再試行、調整のロジックを含む参照実装については、[GitHubの B2C 間External-ID 移行ツール](https://github.com/microsoft/b2c-to-meeid-migration-tool/)を参照してください。 Cognito ソースに合わせてパターンを調整します。

これらのスクリプトは、手順 3 で実行します。

#### カスタム ロジックの移行を準備する

移行ウィンドウの前に、各 Lambda トリガーの置換をリビルドしてテストします。 ソーシャル サインイン シナリオの最も一般的なパターンは、 **事前トークン生成 &gt;`OnTokenIssuanceStart`**です。 Lambda がユーザー属性またはバックエンド検索に基づいてカスタム要求を追加する場合は、そのロジックをAzure関数または予期される外部 ID 応答形式を返す別の HTTPS エンドポイントとして再構築します。 タイムアウト動作、ロールバック、署名キーの要件、監視アラートなど、パイロット テナントの拡張機能を検証します。

確認後や認証後など、直接の外部 ID イベントがないトリガーの場合は、カットオーバーの前に置換ワークフローを準備します。 たとえば、ビジネス要件に応じて、Microsoft Graph変更通知、Logic Apps、サインイン ログ、またはAzure Monitorを使用します。

`OnTokenIssuanceStart` を実装するエンドツーエンドの例については、「[カスタム認証拡張機能の使用を開始する](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-setup)」を参照してください。

#### トークンの検証、要求マッピング、および ID キーを準備する

移行日の前にトークンと要求のマッピングを完了します。 外部 ID 発行者、JWKS メタデータ エンドポイント、予想される対象ユーザー、受け入れ済みのトークン バージョンのバックエンド API 検証設定を更新します。 アプリ ロールには `roles` などのターゲット クレーム モデルを使用するように認可コードを更新してください。`groups` は、直接グループ クレームを意図的に使用する場合にのみ使用してください。

次の表を使用して、Cognito のクレームを External ID に相当するクレームに対応付けます。 次の表では、コードで処理する必要がある正確な要求名を指定することで、手順 1 の機能マッピングについて説明します。

| 概念 | Cognito クレーム | 外部 ID クレーム |
| --- | --- | --- |
| ユーザー識別子 | `sub` | `oid` - 外部 ID トークンに `sub` クレームが含まれるようにしてください。ただし、それはペアワイズです。 アプリケーション間で安定した識別子として `oid` を使用します。 |
| Issuer | `https://cognito-idp.<region>.amazonaws.com/<pool-id>` | `https://<tenant-id>.ciamlogin.com/<tenant-id>/v2.0` - External ID メタデータ ドキュメントで返される発行者と照合して検証します。 |
| 対象ユーザー (ID トークン) | `aud` = アプリ クライアント ID | `aud` = アプリケーション (クライアント) ID |
| 対象ユーザー (アクセス トークン) | `client_id` = アプリ クライアント ID。 `aud` は、アプリが API の Cognito リソース バインドを要求した場合にのみ存在します | `aud` = v2.0 アクセス トークンの API クライアント ID GUID。 v1.0 トークンでは、アプリケーション ID URI を使用できます。 |
| 認可メンバー資格 | `cognito:groups` | `roles` はアプリ ロール用、`groups` はダイレクト グループ クレーム用であるため、既定ではグループ オブジェクト ID として出力されます。 |
| ユーザー名 (ID トークン) | `cognito:username` | `preferred_username` |
| カスタム属性 | `custom:<name>` | ディレクトリ ストレージ: `extension_<appid>_<name>`; 出力されるトークン要求では、構成済みまたは拡張機能によって生成された名前 ( `tier`など) を使用できます。 |
| トークンの有効期限 | `exp` | `exp` |
| スコープ | `scope` | `scp` (アクセス トークン) |

データベースまたはダウンストリーム システムが Cognito `sub` をユーザー識別子として格納している場合は、カットオーバーの前にデータ移行またはルックアップ戦略を準備します。 移行後、安定したユーザー オブジェクト識別子として外部 ID `oid` を使用します。 承認チェック、所有権チェック、監査ログ、およびサポート ツールが、移行されたユーザーを最初の運用ウェーブの前に正しく解決できることを検証します。

グループベースの承認を使用する場合は、カットオーバーの前にグループ超過戦略を準備します。 Cognito は、ユーザーが直接属しているすべてのグループを `cognito:groups` 要求に埋め込みます (ユーザーあたり最大 100 グループ)。 Microsoft Entra IDは動作が異なります。 既定では、 `groups` にはグループ オブジェクト ID が含まれますが、フレンドリ グループ名は含まれません。 ユーザーが 200 を超えるグループのメンバーである場合、Microsoft Entra IDは JWT トークンで`groups`要求を出力しません。 代わりに、 [アクセス トークン要求のリファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference)で説明されているように、超過分インジケーターを出力します。

```json
"_claim_names": { "groups": "src1" },
"_claim_sources": {
  "src1": {
    "endpoint": "https://graph.microsoft.com/v1.0/users/{id}/getMemberObjects"
  }
}
```

API は、グループ エントリを含む`_claim_names`を確認すると、の POST 本文を使用して `{"securityEnabledOnly": false}` エンドポイントを呼び出して、実際のグループ リストを取得します。 SAML トークンの場合、超過分の制限は 150 です。 暗黙的フロー トークンの動作は異なります。外部 ID は`hasgroups: true``_claim_names`/ パターンではなく`_claim_sources` (ブール値) を出力し、アプリはトークンからエンドポイント ヒントなしでMicrosoft Graphを直接呼び出す必要があります。 暗黙的フロー トークンの超過分の制限は 5 です。

通常、ユーザーが少数のグループに属している場合、この区別は適用されません。 多数のグループにユーザーがいる場合は、次のいずれかの方法で処理します。

- アプリケーションに割り当てられているグループのみを含むようにグループ要求を構成します。これにより、要求のサイズが小さくなります。
- グループの代わりにアプリ ロールを使用します。 アプリ ロールは超過分の制限の対象ではなく、アプリ レベルの承認に推奨されるパターンです。
- `_claim_names` (または暗黙的なフロー トークンの場合は`hasgroups`) が表示されたときにMicrosoft Graphを呼び出して、API の超過分要求を処理します。

Cognito からのほとんどの移行では、アプリロールへの移行が最もクリーンなアプローチです。 `roles`要求で安定したアプリ定義値を維持しながら、グループベースの管理が必要な場合は、アプリ ロールに割り当てられたセキュリティ グループを使用します。

**再確認が必要なその他の申請。** 外部 ID トークン内の要求の既定のセットは、一部のチームが予想するよりも小さいです。 API が既定のセットにない要求 (たとえば、 `email`、 `family name`、 `given_name`) を読み取る場合は、トークンに含まれるように、アプリ登録の省略可能な要求として追加します。

#### アプリケーション コードの変更を準備する

移行を実行する前に、またはトラフィックを切り替える前に、アプリケーションの変更を準備します。 パイロット ブランチまたは環境で、外部 ID 機関、クライアント ID、リダイレクト URI、および API スコープで MSAL を使用するようにフロントエンド認証構成を更新します。 外部 ID 発行者、JWKS メタデータ、および予想される対象ユーザーのバックエンド API 検証設定を更新します。 アプリ ロールの `roles` やグループ要求を意図的に使用する場合は `groups` など、ターゲット要求を読み取るように承認コードを更新します。

段階的なカットオーバーを使用している場合は（カットオーバー方法を選択するを参照）、ユーザーコホートに対して Cognito と External ID のどちらを使用するかを選択する機能フラグまたは設定スイッチを準備します。 API が移行中に両方のトークン発行者を受け入れる必要がある場合は、最初のウェーブの前に発行者固有のトークン検証を準備してテストします。 手順 3 で、これらのコードの変更をデプロイの準備をしておいてください。

モバイル アプリの場合は、プラットフォーム固有の MSAL ライブラリを準備します。

**Ios：**[iOS 用の MSAL を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-ios-sign-in)使用します。 トークンは iOS キーチェーンにキャッシュされます。 `msauth.<bundle-id>://auth`形式でリダイレクト URI を登録します。 Microsoft Authenticatorをブローカーとして使用する場合は、ブローカーのリダイレクト URI を追加します。

**Android：**[Android 用の MSAL を使用します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-android-sign-in)。 トークンは `EncryptedSharedPreferences`にキャッシュされます。 アプリの署名ハッシュを使用してリダイレクト URI を登録します。 Authenticator アプリは、アプリ間でのシングル サインオン (SSO) のブローカーとして機能します。

#### デュアルラン トークン検証を準備する

移行中に、API が Cognito と External ID の両方からアクセス トークンを受け取る場合があります。 発行者ごとにトークン検証を構成します。 発行者ごとに、正しいメタデータまたは JWKS エンドポイントを使用して署名を検証し、予想される発行者と対象ユーザーを適用します。 発行者の許可リストのみを十分な検証として扱わない。

**ASP.NET:** 個別の JWT ベアラー スキームまたはポリシー スキームを使用して、各発行者が独自の権限、メタデータ、署名キー、検証設定を持ちます。 `Microsoft.Identity.Web`を使用する場合は、信頼するトークン ソースに対して各スキームを明示的に構成します。

**Node.js:** トークン発行者が署名検証に使用する JWKS エンドポイントを決定できるように、発行者対応のキーの選択で `express-jwt` または `jose` を使用します。 また、トークン ソースごとに予想される `aud` も検証します。

デュアル実行中に、API を呼び出す Web アプリケーションの配信元が変更された場合にのみ、API のクロスオリジン リソース共有 (CORS) ポリシーを更新します。 CORS は、Web アプリの配信元から API へのブラウザー要求に適用されます。 Cognito でホストされる UI ドメインと外部 ID サインイン ドメインは、通常、API を呼び出す配信元ではありません。

アプリケーションで Cognito 承認者と共に AWS API Gateway を使用する場合、同等のAzureはアーキテクチャによって異なります。

- Azure API Managementで、外部 ID トークンを検証するように [validate-jwt ポリシー](https://learn.microsoft.com/ja-jp/azure/api-management/validate-jwt-policy)を構成し、`openid-config` URL をテナントのメタデータ エンドポイントに設定します。
- API Management を使用しない場合は、[Microsoft.Identity.Web](https://learn.microsoft.com/ja-jp/entra/msidweb/overview) (.NET) を使用して、アプリケーション ミドルウェアでトークンを検証します。

#### セッションの切り替え動作を設定する

カットオーバーの前に Cognito 更新トークンの有効期間を短縮する予定がある場合は、移行期間中ではなく準備中に行います。 既存の更新トークンは、構成された有効期間の有効期限が切れるまで有効なままにすることができます。そのため、以前に発行されたトークンがカットオーバー ウェーブの前に期限切れになるまで、変更を十分早く行います。 現在の Cognito 更新トークンの有効期間を使用して、この設定を適用するタイミングを決定します。

トラフィックの少ない時間帯にカットオーバーをスケジュールします。 Cognito CloudWatch メトリックを調べて、最も静かな期間を見つけます。

また、再認証のためにユーザー通信を準備します。 ユーザーが Cognito から外部 ID に移動されると、アクティブな Cognito セッションが次のトークン更新で失敗し、ユーザーが再度サインインする必要がある場合があります。 ユーザー ベースが強制的な再認証 (金融アプリや医療アプリなど) に敏感な場合は、ウェーブの前に事前に通信します。

#### 実稼働前の負荷テストとレジリエンステストを実施する

運用移行の前に、パイロット環境またはステージング環境で実稼働前ロード テストを実行します。 トークンの発行、API 呼び出し、カスタム認証拡張機能の呼び出しなど、外部 ID とアプリケーションを通じて現実的なサインイン率を高めます。 結果を使用して、手順 3. の前に調整動作、カスタム拡張機能のタイムアウトマージン、監視アラート、ロールバック条件を検証します。

### 手順 3: 実行

パイロット環境が検証され、コードの準備が整い、移行スクリプトがテストされます。 次に、段階的なカットオーバーを実行します。 Runbook に密接に従い、プロセス全体を通して関係者と通信します。

ステップ 1 で作成した Runbook をウェーブごとに実行します。 次のセクションでは、各手順の操作の詳細について説明します。

#### ウェーブのユーザー移行スクリプトを実行する

手順 2 で作成してテストした移行スクリプトを、このウェーブのユーザー コーホートに対して実行します。 出力ログを調整します。作成されたユーザーを確認し、スキップまたは失敗した書き込みを確認し、重複する ID を解決してから続行します。

#### 資格情報とソーシャルにリンクされたアカウントを移行する

**ソーシャル ID プロバイダーのみ:** ソーシャル ID プロバイダーのみを使用してサインインするユーザーの場合、移行するパスワードはありません。 移行プロセスでは、外部 ID ユーザーが同じ Facebook または Google の件名にリンクされるため、次回のサインインは再登録なしで機能します。 フェデレーション ID を正しく設定した場合、これらのユーザーの移行は完了です。

**ローカル アカウント:** 手順 2 で選択したパスワード戦略 (強制リセット、JIT、またはパスワードレス) を実行します。

一部の Cognito ユーザーには、ソーシャル プロバイダー のリンクとローカル パスワードの両方があります。 これらのユーザーが発生したら、両方の ID を移行します。 Microsoft Graphの`identities` コレクションを使用してフェデレーション ID (Google、Facebook) をユーザー オブジェクトに追加し、ローカル資格情報の一時パスワードを設定します。 ユーザーがそのプロバイダーを選択すると、サインインのソーシャル ID が優先されます。 ユーザーが代わりに電子メールとパスワードでサインインする場合、パスワード パスは、選択した移行オプションによって異なります。JIT 拡張機能は Cognito に対してパスワードを検証するか、ユーザーがセルフサービスパスワードリセットを完了します。 この方法では、ユーザーが選択しなくても、両方のサインイン パスが保持されます。

JIT を選択した場合は、外部 ID ドキュメントの既存の Just-In-Time パスワード移行に関する記事のガイダンスに従ってください。 実装パターンは、Cognito の場合と、他のレガシ IdP の場合と同じです。 さらに、次の障害モードを計画します。

- **Cognito に到達できない:** ユーザーは、停止中にまったくサインインできません。 設定されたウィンドウ内で移行されていないユーザーのパスワードの強制的なリセットへのフォールバックを検討してください。
- **Cognito が検証した後、Microsoft Graph書き込みが失敗する:** パスワードは保存されません。 次回のサインインでは再び Cognito にアクセスし、廃止されるまでその動作が続きます。 これらのエラーをログに記録し、Microsoft Graph書き込みを非同期的に再試行します。
- **レート制限:** Cognito の `AdminInitiateAuth` API には調整制限があります。 数千人のユーザーに対して JIT を同時に使用している場合は、指数バックオフを実装します。

#### ウェーブの MFA 遷移を検証する

**SMS MFA:** ユーザーの電話番号が Cognito ユーザー エクスポートから外部 ID に移行された場合、SMS MFA は再登録なしで機能しますが、外部 ID テナントで MFA メソッドとして SMS を有効にし、必要なサブスクリプションをリンクする必要があります。 Cognito に登録されている電話番号が、External ID で必要とされる E.164 形式になっていることを確認してください。

運用ウェーブに MFA ユーザーを含める前に、メール ワンタイム パスコードや SMS など、使用する予定のサポートされている外部 ID MFA メソッドを、電話番号の書式設定、回復パス、移行されたユーザーの MFA チャレンジの正常な完了と共に検証します。

#### 準備されたカスタム ロジックを有効にする

手順 2 で準備して検証したカスタム認証拡張機能と置換ワークフローのみを有効にします。 手順 3 は、ラムダ トリガーの置換を設計または再構築する場合ではありません。

ソーシャル サインイン シナリオでは、準備されたランタイム動作を検証します。

- **事前トークン生成 &gt;`OnTokenIssuanceStart`:** 準備された拡張機能がデプロイされ、有効になり、予期される要求が返され、待機時間の予算が満たされていることを確認します。
- **確認後の置換ワークフロー:**Microsoft Graph変更通知、Logic Apps、サインイン ログ処理、またはその他の準備された代替手段が実行されていることを確認します。
- **サインアップ前の置換ワークフロー:** 準備された属性コレクション拡張機能が入力を検証するか、サインアップを期待どおりにブロックすることを確認します。

ウェーブに対してビジネス ロジックを有効にした場合は、同じ状態に保ちます。 エラーが発生した場合は、カットオーバー ウィンドウで新しいロジックをデバッグするのではなく、手順 2 で準備したロールバック パスを使用します。

#### トークンと要求の動作を確認する

手順 2 で準備したトークンの検証と要求のマッピングがこのウェーブに対して正しく機能することを確認します。 手順 2 の要求マッピング テーブルを参照として使用します。 次の点を確認します。

- API は、正しい発行者と対象ユーザーを持つ外部 ID トークンを受け入れます。
- `oid` は、ダウンストリームシステムで正しいユーザーとして認識されます。
- `roles` または `groups` 要求によって、予期される承認の決定が生成されます。
- API で必要な場合は、省略可能な要求 (`email`、 `given_name`など) が存在します。
- グループ超過処理は、ユーザーが 200 を超えるグループに属している場合に機能します。
- ダウンストリーム システム (データベース、監査ログ、サポート ツール) は、移行されたユーザー識別子を正しく解決します。

最終的なカットオーバーウェーブを完了し、Cognito トラフィックがゼロであることを確認したら、すぐに検証構成から Cognito 発行者を削除します。 移行後に両方の発行者をアクティブのままにすると、不要な攻撃対象領域が作成されます。

#### アプリケーションの変更をデプロイする

手順 2 で準備したアプリケーションの変更をデプロイします。 フロントエンドは、移行ウェーブの一部として、増幅認証、Cognito SDK、または OIDC クライアント ライブラリから MSAL に移行する必要があります。

パイロットで検証したデプロイ戦略を使用します。 クリーンな MSAL 実装は保守が安価で、推論が容易ですが、段階的なカットオーバーでは一時的な構成スイッチが必要になる場合があるため、選択したコーホートは外部 ID を使用し、他のコーホートは Cognito を続行します。

デプロイされたコードが、サインイン リダイレクト、アカウントの選択、トークン取得、トークン キャッシュ、ログアウト、API トークン要求に対して準備された MSAL 実装を使用していることを確認します。 ブラウザー ベースのアプリの場合は、 [ログイン](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/login-user)、 [トークンの取得](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/acquire-token)、 [アカウント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/accounts)、 [ログアウト](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/logout)、 [React フック](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/hooks)に MSAL Browser と MSAL React のドキュメントを使用します。 前のアプリで、増幅認証ではなく汎用 OIDC クライアントを使用していた場合は、外部 ID 機関、クライアント ID、リダイレクト URI、スコープ、トークン処理コードが、パイロットで検証された MSAL 構成と一致することを確認します。

**デプロイされた構成を確認します。**

次の点を確認します。

- MSAL 構成内の OAuth クライアント ID と authority URL は、External ID を指しています。
- リダイレクト URI は、外部 ID に登録されている URI と一致します。
- API 呼び出しは、新しい外部 ID スコープを要求します。
- ID トークンまたはアクセス トークンを解析するコードは、準備された要求マッピングを使用します。

#### カットオーバー手順

移行ウェーブごとに次のチェックリストを使用します。

1. ウェーブのカットオーバー前検証チェックを実行します (手順 4 の完全な検証のサブセット)。
    - 移行されたユーザーを使用して、構成された各ソーシャル ID プロバイダーでサインインします。
    - トークンが発行されていることを確認します。
    - ユーザーが再登録を求められていないことを確認します。
    - 新しいサインアップを完了します。
    - アクセス トークンに、期待されるロールまたはグループ要求が含まれていることを確認します。
    - 必要なカスタム要求が存在することを確認します。
    - カスタム認証拡張機能がタイムアウト内に完了したことを確認します。
    - アプリケーションがバックエンド API を呼び出すことができることを確認します。
2. カスタム認証拡張機能がデプロイされ、正常な応答が返されることを確認します。
3. カスタム クレーム プロバイダーまたはカスタム トークン発行ロジックが有効になっている場合は、運用環境でフローを有効にする前に、アプリケーション固有の署名キーが構成され、アクティブであり、有効期限が切れていないことを確認します。
4. ユーザーが拡張機能を有効にした直後に `AADSTS50146` または `invalid_request` が発生した場合は、ロールバック手順として拡張機能またはクレーム プロバイダーを無効にし、サインインを復元してから、再試行する前に署名キーの構成を修正します。
5. ソーシャル IdP リダイレクト URI が外部 ID 側で動作していることを確認します。 (パイロット環境で各プロバイダーとのサインインをテストします)。
6. Cognito 更新トークンの有効期間が、カットオーバー設計の一部である場合は、手順 2 で既に短縮されていることを確認します。
7. アクティブな Cognito セッションの有効期限が切れるのを待つか、 `AdminUserGlobalSignOut` API を使用して更新トークンを取り消して無効にします。
8. 外部 ID 機関を使用して、Amplify Auth/Cognito SDK から MSAL に切り替えるアプリケーション更新プログラムをデプロイします。
9. 最初の 15 分間、サインイン ログを監視します。 エラー、カスタム拡張機能のタイムアウト、予期しない要求値を監視します。
10. 移行後の検証チェックリストを実行します (手順 4)。
11. エラーがしきい値を超えた場合は、ロールバックします。Cognito を指す以前のアプリ バージョンを再デプロイします。 どちらのリダイレクト URI もアクティブなので、ロールバックは構成の変更です。

Note

カットオーバー前に Cognito によって発行された機内認証コードは、外部 ID トークン エンドポイントで交換できません。 スイッチ中にサインインの途中にいるユーザーには、1 回限りのエラーが表示され、サインインを再起動する必要があります。 これを最小限に抑えるには、カットオーバー ウィンドウを短くします。

### 手順 4: 評価

各ウェーブのカットオーバーが完了しました。 展開または廃止する前に、正常に機能することを確認してください。

完全な移行を検討する前に、エンドツーエンドのテストで移行を検証します。

#### 移行後の検証チェックリスト

各移行ウェーブの後、最後のカットオーバー後に次のチェックを完了して、移行をエンドツーエンドで検証します。 このチェックリストでは、手順 3 のカットオーバー前チェックのすべての項目について説明し、移行後のシナリオを追加します。

移行されたユーザーを使用して、アプリケーション用に構成された各ソーシャル ID プロバイダーでサインインします。 次の点を確認します。

- トークンが発行されます。
- ユーザーは再登録を求められません。

各ソーシャル ID プロバイダーで新しいサインアップを完了します。 次の点を確認します。

- ユーザーは、想定される属性を持つ外部 ID で作成されます。
- パスワードを設定した戻りユーザー (パスワードの強制リセット後など) は、新しいパスワードを使用してサインインできます。
- アクセス トークンには、想定されるグループまたはロールのクレームが含まれています。
- アクセス トークンには、API に必要なすべてのカスタム要求が含まれます。
- カスタム認証拡張機能が実行され、構成されたタイムアウト内で完了します。
- アプリケーションは、外部 ID アクセス トークンを使用してバックエンド API を正常に呼び出すことができます。
- API 承認の決定は、同じユーザーの Cognito で観察される動作と一致します。

#### 認証フローと API 承認を確認する

チェックリストの実行に加えて、運用環境のカットオーバーの前に次のシナリオを実行します。

- **Happy-path ソーシャル サインイン &gt; トークン発行 &gt; API 呼び出し:** 運用環境で使用されるのと同じコード パスを介してエンド ツー エンド。
- **アカウント のリンク:** 外部 ID に既に存在するユーザー (移行されたユーザー) は、同じ Google アカウントでサインインします。 新しいユーザーではなく、既存のユーザーと一致していることを確認します。
- **MFA チャレンジ:** MFA が有効になっている場合は、電子メールワンタイム パスコードや SMS など、構成したサポートされている外部 ID メソッドに対して別の MFA 検証パスを実行します。 チャレンジ配信、SMS の電話番号形式、回復動作、移行されたユーザーの正常なサインインを確認します。
- **パスワード リセット (ローカル アカウントの場合、強制リセットを使用した場合):** ユーザーはセルフサービス パスワード リセット (SSPR) フローを実行し、再度サインインします。
- **トークンの有効期限と更新:** アクセス トークンの有効期限を設定し、更新トークン フローが MSAL 経由で動作することを確認します。
- **API 承認の境界:** 今回は、外部 ID で発行されたアクセス トークンを使用して、Cognito で使用したのと同じ承認テストを再実行します。 読み取り専用のテスト ID が書き込み操作を拒否されていること、およびアプリで構成されているロールまたはグループに従って、管理者特権のテスト ID が読み取り操作と書き込み操作の両方を実行できることを確認します。

ウェーブで移行する場合は、各ウェーブの後のチェックリストを使用します。

#### 監視の確認

手順 2 で構成した監視 (準備) によってデータが生成されていることを確認します。 サインイン ログとカスタム拡張機能メトリック ダッシュボードを確認します。

手順 1 の計画で定義した成功基準に対して結果を測定します。

非機能検証の一環として、手順 2 の実稼働前ロード テストの結果を確認します。 各ウェーブ終了後、運用環境のテレメトリをパイロット負荷テストのベースラインと比較し、調整、カスタム拡張のタイムアウト、テール レイテンシを監視してください。

#### トラフィックのカットオーバーを確認する

使用を停止する前に、トラフィックが Cognito に達していないかどうかを確認します。

- サインインとトークン要求の Cognito CloudWatch メトリックを確認します。 使用停止中のユーザー プール クライアントの場合、監視期間中、 `SignInSuccesses`、 `FederationSuccesses`、 `TokenRefreshSuccesses` などのメトリックはゼロのままである必要があります。
- Cognito エンドポイントへの呼び出しがないか、アプリケーション ログを確認します。
- Cognito リダイレクト URI の残りの使用については、ソーシャル ID プロバイダーの開発者コンソールを確認してください。

何かを削除する前に、数日間メトリックを観察します。

モバイル アプリの場合は、アプリ ストア分析を確認して、新しいバージョン (MSAL を使用するもの) の導入を確認します。 古いアプリのバージョンが許容されるしきい値 (たとえば、アクティブなユーザーの 1% 未満) を下回るまで、Cognito を有効に保ちます (新しいサインアップは許可されません)。 モバイル アプリを強制的に更新することはできません。 古いバージョンがまだ使用されている間に Cognito を廃止すると、それらのユーザーはアクセスできなくなります。

### 手順 5: 廃止

すべてのウェーブが検証に合格し、手順 4 で Cognito トラフィックがゼロであることを確認しました。 レガシ インフラストラクチャを削除します。

#### データのアーカイブ、リソースの削除、関係者への通知

Cognito にトラフィックが送信されないという確信がある場合:

1. **コンプライアンスのために保持する必要がある内容をアーカイブします。** ユーザー データ、監査ログ、CloudTrail イベントをエクスポートします。 保持ポリシーに従って保存してください。
2. **監査ログの継続性を確認します。** カットオーバー *の前* に外部 ID サインインと監査ログをセキュリティ情報およびイベント管理 (SIEM) システムにエクスポートし始めると、Cognito CloudTrail イベントと外部 ID ログの間に重複が発生します。 これにより、コンプライアンスの継続的な監査範囲が提供されます。
3. **ダウンストリーム イベント コンシューマーを検証します。** Cognito イベント、EventBridge ルール、サードパーティの Webhook、SNS/SQS 通知、およびその他のユーザー プール イベント コンシューマーに対する CloudWatch アラームが移行または廃止されることを確認します。 Cognito を削除する前に置き換え元を検証して、ダウンストリーム ワークフローがサイレントモードで中断されないようにします。
4. **Google および Facebook 開発者コンソールから Cognito リダイレクト URI を削除します** 。この手順により、古い構成でトラフィックが送信されなくなります。
5. **Cognito アプリ クライアントを無効または削除します。** ロールバック ウィンドウが終了したら、Cognito アプリ クライアントを無効または削除し、それらに関連付けられているクライアント シークレットをすべて削除します。 ユーザー プールを削除する場合、アプリ クライアントはそのクリーンアップの一部として削除されます。
6. **サインインを無効にする:** 削除前に Cognito アプリ クライアントのサインインを無効にして、滑り落ちたトラフィックをキャッチできるようにします。
7. **ユーザー プールと ID プールを削除します** 。ユーザーまたは ID プールを使用していた場合は、誤って使用されないように削除し、関連するコストを停止します。
8. **クリーンアップ:** ユーザープールを参照した Lambda トリガー、IAM ロール、API ゲートウェイオーソライザーをクリーンアップします。
9. **関係者に通知する:** 運用チーム、サポート チーム、Cognito を可視化した内部所有者に通知します。 ユーザー エクスペリエンスが顕著な方法で変更された場合は、カットオーバーの前ではなく、メールまたはアプリ内メッセージを通じてエンド ユーザーに通知します。

#### まとめ

この時点で、ユーザーは外部 ID を使用してサインインし、バックエンド API は外部 ID トークンを検証し、Cognito は完全に使用停止になります。 カットオーバー後に問題が発生した場合は、まず外部 ID サインイン ログを確認してください。 移行後の多くの問題は、要求マッピングの不一致またはカスタム拡張機能のタイムアウトによって発生します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/migrate-to-external-id"} -->
## CIAM のMicrosoft Entra 外部 IDへの移行 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-to-external-id
- Service: entra-external-id / external
- Article date: 2025-07-30
- Summary: CIAM のMicrosoft Entra 外部 IDへの移行: 従来の顧客 ID ソリューションを移行して、セキュリティ、コンプライアンス、スケーラビリティを強化する方法について説明します。

アプリケーションを構築する開発者は、多くの場合、アプリケーションにアクセスする顧客の認証と承認を制御します。 顧客 ID アクセス管理 (CIAM) ソリューションを使用して、完全な ID およびアクセス管理 (IAM) ソリューションの構築と維持を回避します。 Microsoft Entra 外部 IDを使用すると、開発者はアプリケーションを、標準的な IAM ソリューションである Microsoft Entra ID の顧客に重点を置いたバージョンに接続できます。 このガイドでは、開発者と ID チーム向けの移行パスとリソースを提供します。

### Microsoft Entra 外部 IDとは

コンシューマーやビジネスのお客様がアプリを利用できるようにする組織や企業の場合、[Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam) では、セルフサービス登録、パーソナライズされたサインイン エクスペリエンス、顧客アカウント管理などの CIAM 機能を追加できます。 これらの CIAM 機能はMicrosoft Entra IDに組み込まれているため、強化されたセキュリティ、コンプライアンス、スケーラビリティなどのプラットフォーム機能の恩恵を受けることができます。

### 他の CIAM ソリューションから移行する理由

組織は、次のような戦略的目標に基づいて、別のツールからMicrosoft Entra 外部 IDに移行する場合があります。

- クラウド ID プロバイダーを統合する
- 既存のエンタープライズ ID ソリューションに合わせる
- セキュリティとコンプライアンスの強化
- [強力な開発者ガイダンスとアクティブなコミュニティ](https://developer.microsoft.com/identity/external-id)へのアクセス
- [機能の可用性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers#general-feature-comparison)
- [コストの削減](https://azure.microsoft.com/pricing/details/microsoft-entra-external-id)

### 移行を計画する

[Microsoft Entra 外部 ID展開ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-external-intro)は、CIAM ソリューションの概念を初めて使用する場合に、組織が展開を開始するのに役立ちます。 その後、この記事で概説されている手順と共に、既存の CIAM デプロイを持つ組織が Microsoft Entra 外部 ID への移行を完了するのに役立ちます。 組織はまず、[移行の準備状況を確認するために、Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers#general-feature-comparison) の重要な機能の可用性を評価します。

AWS から Azure にワークロードを移行する予定の場合は、その取り組みを体系的に進めることをお勧めします。 コンポーネントの選択とAzureの基本は、その大きなプロセスの重要な部分です。 Microsoftのガイダンスを使用して移行計画を微調整するには、[Amazon Web Services からのセキュリティ サービスの移行](https://learn.microsoft.com/ja-jp/azure/migration/migrate-security-from-aws)に関するページを参照してください。

#### 移行手順

このガイドは、従来の顧客 ID アクセス管理 (CIAM) ソリューションをMicrosoft Entra 外部 IDに移行するのに役立ちます。 この一連の記事に従って、移行プロセスの手順に移動します。

| 段階 | Steps |
| --- | --- |
| 事前移行計画 | • 従来の CIAM 機能を [Microsoft Entra 外部 ID 機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)にマップします。 • 既存の CIAM ソリューションのインベントリを完了します。 |
| Microsoft Entra 外部 ID のセットアップ | • [外部テナントを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。 • [管理者アカウントを追加および管理します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts)。 • [他の ID プロバイダーを有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)。 • [顧客向けアプリケーションをすべて登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 • [アプリケーションをユーザー フローに追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)します。 • [ユーザー フローをテストします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-test-user-flows)。 |
| ID フローとブランド化 | • [サインアップとサインインのユーザー フローを追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)する• [アプリケーションをユーザー フローに追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)する• [アクセスの管理](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers)• [ユーザー フローをテスト](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-test-user-flows)する• [ブランド化をカスタマイズ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)する |
| セキュリティと監視 | • [MFA を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)• [ダッシュボードを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights) (2026 年 8 月 31 日に廃止予定; [移行ガイダンス](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights#migrate-from-user-insights)を参照) • [Azure Monitor を設定する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor) |
| テストと展開 | • ロールアウト戦略を定義します。 • 必要に応じて、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/user-post-users) を使用して、ユーザーの最終バッチをインポートします。 • ライブ トラフィックを Microsoft Entra 外部 ID に切り替える。 • ライブ認証ログとエラー率を監視します。 • フィードバックを収集します。 • レガシ ソリューションの使用停止。 |

#### 在庫

次のような既存の構成とアーキテクチャのインベントリを取得します。

- ユーザー
- アクセス グループとセキュリティ グループ
- 接続されているアプリケーション
- サインアップとサインインのユーザー フロー
- 多要素認証メカニズム
- ソーシャル ID プロバイダー
- 特定のコンプライアンスまたは規制要件

このフェーズでは、ユーザー データ変換が必要かどうかを判断し、 [変換とマッピングを](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes)完了します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/overview-customers-ciam"} -->
## 外部テナントの概要 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam
- Service: entra-external-id / external
- Article date: 2026-01-30
- Summary: ゲスト ユーザー アクセス、顧客 ID とアプリのアクセス管理 (CIAM) など、外部 ID のシナリオを管理するためにMicrosoft Entra 外部 IDがどのように提供されるかについて説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra 外部 IDには、Microsoftの顧客 ID とアクセス管理 (CIAM) ソリューションが含まれます。 アプリをコンシューマーおよびビジネス顧客が利用できるようにする必要がある組織や企業の場合、外部 ID を使用すると、セルフサービス登録、パーソナライズされたサインイン エクスペリエンス、顧客アカウント管理などの CIAM 機能を簡単に追加できます。 これらの CIAM 機能はMicrosoft Entra IDに組み込まれているため、強化されたセキュリティ、コンプライアンス、スケーラビリティなどのプラットフォーム機能も利用できます。

[Image: 顧客 ID およびアクセス管理の概要を示す図。]

### 専用外部テナントを作成する

コンシューマーおよびビジネス顧客アプリの外部 ID の使用を開始するときは、まず、顧客アカウントのアプリ、リソース、ディレクトリのテナントを作成します。

Microsoft Entra IDを使用したことがある場合は、従業員ディレクトリ、内部アプリ、その他の組織リソースを含むMicrosoft Entra テナントの使用について既に理解しています。 外部 ID を使用すると、標準のMicrosoft Entra テナント モデルに従って個別のテナントを作成しますが、外部シナリオ用に構成されます。 この外部テナントには次のものが含まれます。

- **ディレクトリ**: ディレクトリには、顧客の資格情報とプロファイル データが格納されます。 コンシューマーまたはビジネス顧客がアプリにサインアップすると、外部テナントにローカル アカウントが作成されます。
- **アプリケーションの登録**: Microsoft Entra IDは、登録済みアプリケーションに対してのみ ID とアクセスの管理を実行します。 アプリを登録すると信頼関係が確立され、アプリをMicrosoft Entra IDと統合できます。 外部テナントでは、認証とシングル サインオン (SSO) に OpenID Connect (OIDC) または Security Assertion Markup Language (SAML) プロトコルを使用するアプリを登録できます。 [アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)プロセスは、OIDC ベースのアプリ用に最適化されています。 [SAML アプリを登録](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-register-saml-app)するには、代わりにエンタープライズ アプリケーション機能を使用します。
- **ユーザー フロー**: 外部テナントには、顧客に対して有効にしたい、セルフサービスのサインアップ、サインイン、パスワード リセットの各エクスペリエンスが含まれています。
- **拡張機能**: 外部システムからのユーザー属性とデータを追加する必要がある場合は、ユーザー フロー用のカスタム認証拡張機能を作成できます。
- **サインイン メソッド**: ユーザー名とパスワード、ワンタイム パスコード、Google、Facebook、Apple、Microsoft Entra ID、カスタム OIDC ID など、アプリにサインインするためのさまざまなオプションを有効にすることができます。
- **暗号化キー**: トークン、クライアント シークレット、証明書、パスワードの署名と検証のための暗号化キーを追加および管理します。

[password とワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers) サインインの詳細と、[Google](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)、 [Facebook](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)、[Apple](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers)、および [OIDC](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers) フェデレーション。

外部テナントで管理できるユーザー アカウントには、次の 2 種類があります。

- **顧客アカウント**: アプリケーションにアクセスする顧客を表すアカウント。
- **管理者アカウント**: 職場アカウントを持つユーザーは、テナントのリソースを管理でき、管理者ロールがあれば、テナントも管理できます。 職場アカウントを持つユーザーは、新しいコンシューマー アカウントの作成、パスワードのリセット、アカウントのブロック/ブロック解除、アクセス許可の設定、セキュリティ グループへのアカウントの割り当てを行うことができます。

外部テナントでの[顧客アカウント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts)と[管理者アカウント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts)の管理に関する詳細について確認してください。

### カスタマイズされたサインインを追加する

外部 ID は、ID とアクセスのために Microsoft Entra プラットフォームを使用して顧客がアプリケーションを使用できるようにする企業を対象としています。

- **アプリにサインアップ ページとサインイン ページを追加する。** 顧客アプリの直感的で使いやすいサインアップとサインインエクスペリエンスをすばやく追加します。 顧客は 1 つの ID を使用して、使用するすべてのアプリケーションに安全にアクセスできます。
- **ソーシャル ID とエンタープライズ ID を使用してシングル サインオン (SSO) を追加する。** 顧客は、ソーシャル ID、エンタープライズ ID、またはマネージド ID を選択して、ユーザー名とパスワード、電子メール、またはワンタイム パスコードでサインインできます。
- **サインアップページに会社のブランドを追加する。** 既定のエクスペリエンスと特定のブラウザー言語のエクスペリエンスの両方を含め、サインアップとサインインのエクスペリエンスの外観をカスタマイズします。
- **サインアップ フローを簡単にカスタマイズして拡張できます。** ID ユーザー フローをニーズに合わせて調整します。 サインアップ時に顧客から収集する属性を選択するか、独自のカスタム属性を追加します。 アプリで必要な情報が外部システムに含まれている場合は、収集するカスタム認証拡張機能を作成し、認証トークンにデータを追加します。
- **複数のアプリの言語とプラットフォームを統合する。** Microsoft Entraを使用すると、複数のアプリの種類、プラットフォーム、言語に対して、セキュリティで保護されたブランド化された認証フローをすばやく設定して提供できます。
- **アプリにネイティブ認証を使用します。** iOS および Android 用の Microsoft Authentication Library (MSAL) を使用して、モバイル アプリケーションとデスクトップ アプリケーションのシームレスな認証エクスペリエンスを作成します。
- **セルフサービス アカウント管理を提供します。** お客様は、自分でオンライン サービスに登録したり、プロファイルを管理したり、アカウントを削除したり、多要素認証 (MFA) メソッドに登録したり、管理者やヘルプ デスクのサポートなしでパスワードをリセットしたりできます。
- **利用規約とプライバシー ポリシーに同意します。** サインアップ中、使用条件に同意するようにユーザーに求めることができます。 顧客のユーザー属性を使用すると、サインアップ フォームにチェックボックスを追加し、利用規約とプライバシー ポリシーへのリンクを含めることができます。

[統合の計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution)、[認証方法の選択](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)、[サインインの外観のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-branding-customers)について詳しく説明します。

### セルフサービス サインアップ用のユーザー フローを設計する

アプリケーションにユーザー フローを追加することで、顧客向けの簡単なサインアップとサインインのエクスペリエンスを作成できます。 ユーザー フローでは、顧客が従う一連のサインアップ手順と、使用できるサインイン方法として、電子メールやパスワード、ワンタイム パスコード、[Google](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)、[Facebook](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)、[Apple](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers) のソーシャル アカウント、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers) フェデレーション、[カスタム OIDC](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers) ID プロバイダーなどが含まれます。 また、一連のユーザー組み込み属性から選択するか、独自のカスタム属性を追加することで、サインアップ時に顧客から情報を収集することもできます。

いくつかのユーザー フロー設定により、顧客がアプリケーションにサインアップする方法を制御できます。これには以下が含まれます。

- サインイン メソッドと外部 ID プロバイダー
- サインアップする顧客から収集する属性 (名、郵便番号、居住国/地域など)
- 会社のブランド化と言語のカスタマイズ

ユーザー フローの構成の詳細については、「[顧客向けのサインアップとサインインのユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)」を参照してください。

### 独自のビジネス ロジックの追加

外部 ID は、認証フロー内の特定のポイントにアクションを定義できるようにして柔軟性を実現できるように設計されています。 カスタム認証拡張機能を使用すると、トークンがアプリケーションに発行される直前に、外部システムからのクレームをトークンに追加できます。

カスタム認証拡張機能を使用した[独自のビジネス ロジックの追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions)の詳細を確認してください。

### Microsoft Entraのセキュリティと信頼性

外部 ID は、ビジネスと消費者間 (B2C) 機能の Microsoft Entra プラットフォームへの統合を表します。 セキュリティの強化、規制への準拠、ID およびアクセス管理プロセスをスケーリングする機能など、プラットフォーム機能の恩恵を受けることができます。

#### 条件付きアクセス

Microsoft Entra 条件付きアクセスシグナルをまとめ、意思決定を行い、セキュリティ ポリシーを適用します。 条件付きアクセス ポリシーは、簡単に言えば、(**if**) ユーザーがアプリケーションにアクセスする場合、(**then**) ユーザーはアクションを完了する必要があるという if-then ステートメントです。

条件付きアクセス ポリシーは、ユーザーが第 1 段階認証が完了した後で適用されます。 たとえば、ユーザーのサインイン リスク レベルが高い場合は、アクセスを取得するために MFA を実行する必要があります。 または、最も制限の厳しいアプローチは、アプリケーションへのアクセスをブロックすることです。

#### 多要素認証 (MFA)

Microsoft Entra MFA は、ユーザーのシンプルさを維持しながら、データとアプリケーションへのアクセスを保護するのに役立ちます。 Microsoft Entra 外部 ID Microsoft Entra MFA と直接統合されるため、2 番目の形式の認証を要求することで、サインアップおよびサインイン エクスペリエンスにセキュリティを追加できます。 MFA は、アプリに対して適用したいセキュリティの範囲に応じて微調整できます。 次のシナリオについて考えてみましょう。

- 顧客に 1 つのアプリを提供し、追加のセキュリティを実現するために MFA を有効にしたいものとします。 すべてのユーザーとアプリを対象とする条件付きアクセス ポリシーで MFA を有効にすることができます。
- 顧客に複数のアプリを提供するものの、すべてのアプリケーションで MFA を要求するわけではないものとします。 たとえば、顧客が自動車保険アプリケーションにサインインするにはソーシャルまたはローカル アカウントを使用できますが、同じディレクトリに登録されている住宅保険アプリケーションにアクセスするには事前に電話番号を確認する必要があるような場合です。 条件付きアクセス ポリシーでは、MFA を適用するアプリのみを除くすべてのユーザーを対象にすることができます。

[外部テナントでの MFA](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers) の詳細について学習するか、[多要素認証を有効にする方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)を確認してください。

#### マシン間認証 (M2M)

マシン間 (M2M) 認証では、[OAuth 2.0 クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)を使用して、アプリケーションがMicrosoft Entra IDで直接認証できるようにします。 このフローは、バックエンド サービスがアクセス トークンを安全に要求し、自分の代わりに API を呼び出す必要がある、ユーザー操作のないシナリオを対象としています。

Microsoft Entra 外部 ID アプリケーションの場合は、クライアント シークレットまたは証明書でクライアント資格情報フローを使用して M2M 認証を構成できます。 この方法では、API にアクセスするときにアプリケーションをそれ自体として認証できます。 M2M 認証を有効にするには、 [M2M Premium アドオン](https://www.microsoft.com/security/pricing/microsoft-entra-external-id/)を使用する必要があります。 組織のプレミアム アドオンの使用ポリシーを確認して、コストへの影響を理解し、内部ガバナンスとライセンスの要件に確実に準拠します。

#### Microsoft Entraの信頼性とスケーラビリティ

高度にカスタマイズされたサインイン エクスペリエンスを作成し、顧客アカウントを大規模に管理できます。 Microsoft Entraパフォーマンス、回復性、ビジネス継続性、低待機時間、高スループットを利用して、優れたカスタマー エクスペリエンスを確保します。

### ユーザー アクティビティとエンゲージメントを分析する

[使用状況と分析情報] のアプリケーション ユーザー アクティビティ機能は、テナントに登録されているアプリケーションのユーザー アクティビティとエンゲージメントに関するデータ分析を提供します。 この機能を使用すると、Microsoft Entra 管理センターのユーザー アクティビティ データを表示、クエリ、分析できます。 これにより、戦略的な意思決定を支援し、ビジネスの成長を促進できる貴重な分析情報を発見することができます。

顧客テナントで使用できる[アプリケーション ユーザー アクティビティ ダッシュボード](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights)の詳細について確認してください。

Important

アプリケーション ユーザー アクティビティ ダッシュボード (User Insights) と Microsoft Graph `reports/userInsights/*` (ベータ) エンドポイントは、**2026 年 8 月 31 日** で廃止されます。 Log Analytics (プライマリ) またはMicrosoft Graphサインインおよび監査ログ API を使用してAzure Monitorに移行することを計画します。 詳細については、「 [User Insights からの移行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights#migrate-from-user-insights)」を参照してください。

### AZURE AD B2C について

2025 年 5 月 1 日より、[Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/) は新規のお客様による購入ができなくなります (詳細については、[FAQ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/faq-customers#azure-ad-b2c-and-azure-ad-external-identities) を参照してください)。 Microsoft Entra 外部 IDは、Microsoftの次世代 CIAM ソリューションであり、すべての新機能がこのプラットフォーム上に構築されています。 既存の Azure AD B2C のお客様の場合は、[Azure AD B2C から外部 ID への移行を計画する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/plan-your-migration-from-b2c-to-external-id"} -->
## Azure AD B2C から外部 ID への移行を計画する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id
- Service: entra-external-id / external
- Article date: 2026-03-13
- Summary: AZURE AD B2C から Microsoft Entra 外部 ID に移行する場合は、標準の移行アプローチとハイ スケール互換性 (HSC) モードを選択します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

実装を開始する前に、Azure AD B2C テナントに適した移行アプローチを決定します。 この記事では、標準の移行モードと高スケール互換性 (HSC) モードの選択、重要な決定ポイントの理解、適切な次のステップの検索に役立ちます。 この記事は、移行を計画している Azure AD B2C の既存のお客様を対象としています。 Microsoft Entra 外部 IDを評価する新しい顧客は、[ソリューションの計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution)を参照する必要があります。

Important

アプローチを選択する前に、Microsoft Entra 外部 IDに適用される機能とサービスの制限事項と、HSC モードで適用される追加の制限事項を確認してください。 一部のAzure AD B2C 機能 (ソーシャル ID プロバイダー、パスキー、年齢制限、特定の条件付きアクセス シナリオなど) は、現在 HSC モードでは使用できません。 HSC モードの制限事項と、[スケール モードとデプロイ モードによる機能のサポート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits#capability-support-by-scale-and-deployment-mode)に関する説明を参照してください。

この記事では、次の方法について説明します。

- 使用可能な移行方法を比較する (標準と高スケールの互換性)
- 主要な決定ポイントと適格性基準を理解する
- HSC モードの制限事項と機能の可用性を確認する
- 共存のしくみを段階的に確認する
- 選択したアプローチの構成手順へのリンクを見つける

### 移行アプローチを選択する

最初の決定は、使用する移行アプローチです。 ほとんどのお客様は、標準のアプローチを使用する必要があります。 高スケール互換性 (HSC) モードは、テナントが特定のスケールのしきい値を超え、その機能制限を受け入れることができる場合にのみ関連します。

- **標準移行。** ほとんどのお客様にお勧めします。 ユーザーと認証情報を新しい外部 ID テナントに移行し、アプリケーションを切り替えます。
- **高スケール互換性 (HSC) モード。** 500 万を超えるディレクトリ オブジェクトを持つ非常に大規模なAzure AD B2C テナントの場合。 既存のユーザーと資格情報を維持し、段階的にアプリケーションを移行します。

#### ハイ スケール互換性 (HSC) モードの移行が適用されるかどうかを判断する

次のデシジョン ツリーを使用して、テナントが HSC モードの対象かどうかを確認します。

[Image: HSC と標準の移行方法の手順を示す、Azure AD B2C の移行デシジョン ツリーのダイアグラム。]

HSC モードの移行は、次 **の条件がすべて** 満たされている場合に適している可能性があります。

- 既存のAzure AD B2Cのお客様です。
- テナントには、約 500 万以上のディレクトリ オブジェクト (ユーザー、グループ、アプリケーション) が含まれています。
- HSC モードの 機能制限 を確認し、受け入れた。

#### 決定の要約

| 標準移行 | ハイ スケール互換 (HSC) モードの移行 |
| --- | --- |
| **最適な対象:** ほとんどの入居者**一般的なトリガー**: 大規模しきい値未満**ID アプローチ**: 外部 ID への移行の一環として、ユーザー (および必要に応じて資格情報) を移行することを計画する**共存**:非常に大規模な長時間のサイドバイサイド操作用に設計されていません**機能カバレッジ**: 互換性が最も広範です。 | 最適な用途: 非常に大規模な Azure AD B2C テナント**一般的なトリガー**: 最大 500 万以上のディレクトリ オブジェクトとスケールドリブン制約**ID アプローチ**: フェーズでアプリケーションを移行する際に、既存のユーザーと資格情報を維持する**共存**: AZURE AD B2C と外部 ID が同じテナント内で並行して実行され、スキーマの更新が必要になる場合があります**機能の対象範囲**: 現在の重要な機能ギャップ - ソーシャル ID プロバイダーなし、パスキーなし、年齢制限なし、管理ポータル エクスペリエンスなし、制限付き条件付きアクセス。 HSC モードの制限事項を参照してください |

テナントが HSC モードの適格性条件を満たしている場合は、決定する前に、以下の両方の方法を確認してください。 機能の要件によっては、標準的なアプローチが適している可能性があります。

テナントが HSC モードの適格性基準を満たしていない場合は、 **標準の移行アプローチ**を使用します。 HSC モードは、高スケールのしきい値を下回る場合には追加の利点を提供しません。

### 標準的な移行アプローチ

ほとんどのAzure AD B2C のお客様には、標準的な移行アプローチをお勧めします。 これは、大規模な共存動作を必要とせずに、ユーザー (および必要に応じて資格情報) を移行し、アプリケーションをMicrosoft Entra 外部 IDに移動できるテナントを対象としています。

#### 標準アプローチで移行する内容

標準的なアプローチでは、ID とアプリケーションを新しいMicrosoft Entra 外部 ID テナントに移行します。 これには通常、次のものが含まれます。

- 移行先テナントの作成とセキュリティ、コンプライアンス、監視の構成
- アプリケーションの登録とユーザー フローの構成
- 既存のテナントからのユーザー データの移行
- パスワードの保持 (必要な場合)
- アプリケーションを外部IDに移行する

#### 一般的な移行パターン

- **ユーザーの一括移行、アプリのカットオーバー**: ユーザーは事前に外部 ID に移行され、アプリケーションは外部 ID に対して認証されるように更新されます。
- **Just-In-Time (JIT) パスワード移行を使用したユーザーの一括移行**: ユーザーは最初に外部 ID に移行されます。その後、パスワードの検証/移行は、サインインまたはパスワードのリセット中に、時間ボックス化された共存期間にわたって行われます。
- **Azure AD B2C によって開始された移行**: アプリケーションは、最初はレガシ B2C テナント経由で認証を続行しますが、パスワードはバックグラウンドで段階的に移行され、アプリケーションは外部 ID に切り捨てられます。

#### 考慮事項

実装を開始する前に、大まかに次の領域を確認してください。

- **カスタム ビジネス ロジック**: 再作成する必要があるカスタム ポリシー ロジック、トークン/要求の整形、ダウンストリームの依存関係を特定します。
- **ユーザー エクスペリエンス**: 現在のサインイン UX のカスタマイズを確認し、使用する外部 ID エクスペリエンスを決定します。
- **ID プロバイダー**: ソーシャル ID プロバイダーとエンタープライズ ID プロバイダー、およびフェデレーション要件を一覧表示します。
- **アクセス制御**: 移行後に同等である必要がある条件付きアクセス ポリシーと条件に注意してください。
- **アプリケーション レベルの変更**: 移行には、テナント レベルだけでなく、アプリケーション レベルでの変更が必要です。 外部 ID エンドポイントを使用し、それに応じてトークンを検証するには、各アプリケーションを更新する必要があります。 テナントにサード パーティが所有するアプリケーション (顧客が自分のアプリを登録する ISV テナントなど) が含まれている場合は、早い段階でそれらのアプリ所有者と連携します。 すべてのアプリケーションが更新されるまで、移行を完了することはできません。
- **自動化およびオペレーション**: Microsoft Graph を利用したライフサイクルオペレーション、監視、およびRunbookの計画を立てます。

#### 既知の制限

一部のAzure AD B2C 機能は、Microsoft Entra 外部 IDで利用できない (または完全に利用できない) 場合があります。 移行計画にコミットする前に、これらを確認してください。

- **Age gating**: カスタム ポリシーを使用して年齢ベースの属性（マイナー分類やメジャー分類など）を派生または格納する Azure AD B2C テナントは、代替アプローチを計画する必要があります。 現在、Microsoft Entra 外部 IDでは年齢制限はサポートされていません。
- **カスタム ポリシー (IEF):** カスタム 認証拡張機能を使用してカスタム ポリシー ロジックを再作成する必要があります。 1 対 1 のパリティは保証されません。

サービスの制限と機能の違いの完全な一覧については、「 [サービスの制限と制限](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits)」を参照してください。

#### 標準移行を選択するタイミング

- AZURE AD B2C と外部 ID の実行時間の長いサイド バイ サイド操作は必要ありません。
- ID とアプリケーションを外部 ID に移動するときに、最も広範な機能の互換性が必要です。

#### 構成手順

標準的な移行方法を使用することに決めた場合は、[Azure AD B2C から Microsoft Entra 外部 ID に移行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-from-b2c-to-external-id)して、新しいテナントを構成し、Azure AD B2C から新しい環境にユーザーと資格情報を移行する手順についてのガイダンスを参照してください。

### ハイ スケール互換 (HSC) モードの移行アプローチ

ハイ スケール互換 (HSC) モードは、非常に大規模なAzure AD B2C テナントに特化したアプローチです。 これにより、既存のユーザーと資格情報を維持しながら、Microsoft Entra 外部 IDエンドポイントと機能を採用できるため、段階的にアプリケーションを移行できます。

#### HSC モードのしくみ

HSC モードでは、Azure AD B2C と Microsoft Entra 外部 ID が同じテナントで並列に実行されます。 既存のアプリは、新しいアプリまたは移行されたアプリを外部 ID エンドポイントに移動するときに、引き続き Azure AD B2C エンドポイントを使用できます。

#### HSC モードを選択する理由

HSC モードは、完全なユーザーと資格情報の移行がリスクが高いか、1 回の操作で完了するのが困難なテナントを対象としています。 次を実現するのに役立ちます。

- 中断することなく、既存の B2C ユーザーと資格情報を保持します。
- 外部 ID を使用して、新しいアプリまたは移行されたアプリと共にレガシ B2C アプリケーションを引き続きサポートします。
- 移行のペースと範囲を制御し、ビジネス ニーズに応じてアプリケーション間の段階的な移行を可能にします。

HSC モードを有効にする前に、適格性を確認し、制限事項を確認し、少数のアプリケーションで主要なサインインとトークン発行のシナリオを検証します。 次のセクションでは、各前提条件の概要を示します。

#### テナントの適格性を確認する

テナントが必要なオブジェクト クォータ (約 500 万個のディレクトリ オブジェクト) を超えた場合、テナントは HSC モードの対象となります。 現在の使用状況は、Graph API `directoryObject` リソースの種類で確認できます。 詳細については、「 [directoryObject リソースの種類」を参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryobject?view=graph-rest-1.0&preserve-view=true)。

テナントがこのオブジェクト クォータを超えない場合、HSC モードには追加の利点はなく、標準の移行アプローチをお勧めします。

#### 制限事項とロードマップの調整を確認する

Important

HSC モードは、大規模に適用される制限を受け入れることができる場合にのみ適しています。 HSC モードを有効にするか、追加のアプリケーションを移行する前に、以下の HSC モードの制限事項に関するセクションを確認してください。

一部の制限は大規模な運用に不可欠であり、現在Azure AD B2C に存在します。 HSC モードで外部 ID を実行する場合も、これらの同じ制約が適用されます。 包括的な一覧については、「 [スケールモードとデプロイ モードによる機能のサポート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits#capability-support-by-scale-and-deployment-mode)」を参照してください。

#### HSC モードのアプリケーション要件

HSC モードでアプリケーションを外部 ID に移行する場合は、次の要件が適用されます。

- **新しいアプリケーション登録を作成します。** 既存の Azure AD B2C アプリケーションの登録を再利用しないでください。 外部 ID では、アプリケーションのプロパティとネイティブ認証のサポートの違いにより、新しい登録が必要です。
- **シングルテナント構成を使用します。** 各アプリケーションをシングル テナントとして登録します (*この組織のディレクトリ内のアカウントのみ*)。 マルチテナント アプリケーションの登録は、外部 ID エンドポイントではサポートされていません。

#### 共存のしくみを理解する

HSC モードでは、Azure AD B2C と Microsoft Entra 外部 ID が同じテナント内で並列に実行されます。 既存のアプリケーションでは引き続き AZURE AD B2C エンドポイントが使用されますが、新規または移行されたアプリケーションでは外部 ID エンドポイントが使用されます。 ユーザーと資格情報は、両方のエクスペリエンスで共有されます。

**ステージ 1:** B2C サービスで現在実行されているすべてのアプリ。

**Stage 2:** 既存の Azure AD B2C テナントで HSC モードが有効になっています。 これは、アプリに影響を与えずに実行されます。 外部 ID サービスで実行するアプリを移行し、他のアプリは B2C に残すようになりました。

[Image: エンドポイントと成果物が一覧表示された B2C から外部 ID へのアプリの移行を示す HSC モード ワークフローの図。]

**Stage 3:** すべてのアプリが完全に外部IDに移動され、あなたのテナントはAzure AD B2Cの廃止に準備が整いました。

Important

アプリケーションの移行は常にユーザーが実行します。 HSC モードでは、アプリケーションは自動的に移動されません。

#### 構成手順

高スケール互換性 (HSC) モードを使用することにした場合は、引き続き [外部 ID の高スケール互換性 (HSC) モードを有効](https://learn.microsoft.com/ja-jp/entra/external-id/customers/enable-external-id-high-scale-compatibility-mode) にして、テナントの HSC モードを有効にする手順と、共存のために環境を構成する方法に関するガイダンスを参照してください。

HSC モードの制限を確認した後で、代わりに標準的な方法を使用することを希望する場合は、[Azure AD B2C から Microsoft Entra 外部 ID への移行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-from-b2c-to-external-id)を参照してください。

### HSC モードの制限事項

HSC モードを有効にする前に、これらの制限事項をよく確認してください。 これらは、一般的な [外部 ID サービスの制限](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits)に加えて適用されます。 一部の機能は部分的に利用できますが、HSC モードでは完全に検証されておらず、機能の可用性タイムラインは HSC モードと標準デプロイで異なる場合があります。 最新の状態については、公式のロードマップを参照してください。

**認証とアクセス制御**

- 認証コンテキスト、ステップアップ認証、セッション ベースの制御など、高度な条件付きアクセス シナリオ。
- グループを使用したアプリケーションの割り当て。
- 現在、パスキー (FIDO2) は HSC モードでは使用できません。 これらは、標準のMicrosoft Entra 外部 IDデプロイで使用できます。 セットアップについては、 [パスキーを使用したサインインに関する説明を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey)参照してください。

**フェデレーションとエコシステムの統合**

- ソーシャル ID プロバイダー (Google、Facebook、Apple、および Azure AD B2C で構成されているその他のソーシャル ID プロバイダー)。
- AZURE AD B2C カスタム ポリシーを使用して構成されたサード パーティの ID プロバイダー。
- AZURE AD B2C カスタム ポリシーで構成されているカスタム OIDC フェデレーション (エンタープライズ OIDC ID プロバイダーがサポートされています)。

**セキュリティと不正行為の防止**

- Web ホスト型 (ブラウザーベース) のサインインおよびサインアップ フローに対するサード パーティの不正アクセス防止の統合は、HSC モードではサポートされていません。 ネイティブ認証 API フローは、ネイティブ認証エンドポイントの前にある Web アプリケーション ファイアウォール (WAF) を使用して、サードパーティの不正アクセス保護と統合できます。 実装のガイダンスについては、 [サードパーティのボット保護をネイティブ認証と統合](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-third-party-bot-protection-native-api-sign-up) し、 [サードパーティのアカウント引き継ぎ保護をネイティブ認証と統合](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-third-party-account-take-over-protection-native-api)する方法に関する説明を参照してください。

**ユーザー エクスペリエンスとコンプライアンス**

- 年齢制限。 カスタムポリシーを使用して年齢ベースの属性（マイナー分類やメジャー分類など）を派生させたり、格納したりする Azure AD B2C テナントは、代替アプローチを計画する必要があります。

**管理ポータルのエクスペリエンス**

- 現在、管理の構成と管理は、Microsoft Graphと自動化を使用してプログラムによって実行されます。

権限のある機能の比較については、 [スケールとデプロイ モードによる機能のサポート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits#capability-support-by-scale-and-deployment-mode)に関する説明を参照してください。

### 移行パートナーからヘルプを受ける

Microsoft は、Azure AD B2C から Microsoft Entra 外部 ID への移行を専門とするサービスおよび統合パートナーと連携しています。 パートナーは、標準モードと HSC モードの両方のアプローチで、アドバイザリ、実装、エンジニアリング主導の配信を支援できます。 パートナーの一覧とその関与方法については、 [外部 ID のサービスと統合パートナーに関するページを](https://learn.microsoft.com/ja-jp/entra/external-id/customers/services-integration-partners)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/quickstart-get-started-guide"} -->
## クイック スタート - 作業の開始 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-get-started-guide
- Service: entra-external-id / external
- Article date: 2024-11-28
- Summary: Microsoft Entra 外部 ID の使用を開始する方法を説明します。 わずか数分でアプリの外観をカスタマイズし、ユーザーを設定してサインアップ フローをテストし、サンプル アプリを構成します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイックスタートでは、外部テナントでアプリの外観をカスタマイズする方法について説明します。 また、わずか数分で、ユーザーを設定してサインアップ フローをテストしたり、サンプル アプリを構成したりできるようになります。 これらの組み込みの外部構成機能を使用すると、Microsoft Entra 外部 ID は顧客の ID プロバイダーおよびアクセス管理サービスとして機能させることができます。

### 前提条件

- 外部テナント。 まだお持ちでない場合は、[無料試用版にサインアップ](https://aka.ms/ciam-free-trial?wt.mc_id=ciamcustomertenantfreetrial_linkclick_content_cnl) するか、[Microsoft Entra 管理センターで外部構成を使ってテナントを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)。

注

Visual Studio Code の [Microsoft Entra 外部 ID 拡張機能](https://aka.ms/ciamvscode/quickstarts/marketplace)を使用して、Visual Studio Code 内で直接外部テナントを作成し、サインイン エクスペリエンスをカスタマイズし、サンプル アプリを設定することもできます ([詳細はこちら](https://aka.ms/ciamvscode/quickstartguide))。

### サインイン エクスペリエンスをカスタマイズする

外部テナントの無料試用版をセットアップすると、新しい外部テナントの構成の一環としてガイドが自動的に始まります。 Azure サブスクリプションを使って外部テナントを作成した場合は、以下の手順に従って手動でガイドを開始できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Overview** に移動します。
4. **[作業の開始]** タブで、**[ガイドの開始]** を選びます。

    [Image: ガイドを使用して始める方法を示すスクリーンショット。]

外部テナントで、顧客のサインインとサインアップのエクスペリエンスをカスタマイズできます。 ガイドに従って、3 つの簡単な手順でテナントを設定してください。 最初に、顧客がサインインする方法を指定する必要があります。 この手順では、**[メールとパスワード]** および **[メールとワンタイム パスコード]** の 2 つのオプションから選択できます。 外部アカウントは後で構成できます。これにより、顧客は [Google](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)、 [Facebook](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)、 [Apple](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)、 [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers)、または [カスタム OIDC](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers) アカウントを使用してサインインできるようになります。 サインアップ時にユーザーから収集するように[カスタム属性を定義](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)することもできます。

必要に応じて、会社のロゴを追加したり、背景色を変更したり、サインイン レイアウトを調整したりできます。 これらのオプションの変更は、この外部構成付きテナント内のすべてのアプリの外観に適用されます。 テナントを作成したら、他のブランド化オプションを使用できます。 [既定のブランド化をカスタマイズ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)し、[言語を追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-languages-customers)できます。 カスタマイズが完了したら、**[続行]** を選択します。

[Image: ガイドでのサインイン エクスペリエンスのカスタマイズのスクリーンショット。]

### サインアップ エクスペリエンスを試して、最初のユーザーを作成する

1. このガイドでは、選択したオプションを使用してテナントを構成します。 構成が完了すると、ボタンのテキストが **[セットアップ中...]** から **[今すぐ実行]** に変更されます。
2. **[今すぐ実行]** ボタンを選択します。 新しいブラウザー タブで、ユーザーの作成とサインインに使用できるテナントのサインイン ページが開きます。
3. **[アカウントがありませんか? アカウントを作成します]** を選択して、テナントに新しいユーザーを作成します。
4. 新しいユーザーのメール アドレスを追加し、**[次へ]** を選択します。

注

試用版の作成に使用したメール アドレスとは異なるメール アドレスを使用します。 テナント管理者の電子メールを使用して [、セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview) または Microsoft Entra 管理センターで [新しい外部ユーザーを追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts#create-a-customer-account) して顧客アカウントを作成する場合、システムは同じメール アドレスを持つ 2 つ目のアカウントを作成します。 この新しいアカウントには顧客レベルの特権があり、競合が発生する可能性があります。

1. 画面のサインアップ手順を完了します。 通常、ユーザーがサインインすると、アプリにリダイレクトされます。 ただし、この手順ではアプリを設定していないため、代わりに JWT.ms にリダイレクトされます。ここで、サインイン プロセス中に発行されたトークンの内容を確認できます。
2. [ガイド] タブに戻ります。この段階では、ガイドを終了して、管理センターに移動し、テナントのすべての構成オプションを調べることができます。 または、**続行**してサンプル アプリを設定することもできます。 追加で行った構成変更をテストするために使用できるように、サンプル アプリを設定することをお勧めします

    [Image: サインアップ エクスペリエンスの作成に成功したことを示すスクリーンショット。]

### サンプル アプリを設定する

作業の開始ガイドでは、次のアプリの種類と言語についてサンプル アプリが自動的に構成されます。

- シングル ページ アプリケーション (SPA): JavaScript、React、Angular
- Web アプリ: Node.js (Express)、ASP.NET Core
- デスクトップ アプリ: .NET (MAUI)
- モバイル アプリ: .NET (MAUI)

サンプル アプリをダウンロードして実行するには、次の手順に従います。

1. アプリの種類を選択して、サンプル アプリの設定に進みます。
2. お使いの言語を選択し、コンピューターに**サンプル アプリをダウンロード**します。
3. 指示に従ってアプリをインストールし実行します。 サンプル アプリにサインインします。

    [Image: サンプル アプリの設定のスクリーンショット。]
4. 試用版テナントの作成、サインイン エクスペリエンスの構成、最初のユーザーの作成、サンプル アプリの設定のプロセスが完了しました。 **[続行]** を選択して概要ページに移動します。ここで、管理センターに移動することも、ガイドを再起動して別のオプションを選択することもできます。

注

次にテナントに戻ったときに、テナント管理者アカウントのセキュリティを強化するための追加の認証要素を設定するように求められる場合があります。

### Microsoft Entra 外部 ID を確認する

「[作業の開始ガイドの機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-guide-explained)」に関する詳細な記事で開始ガイドによって設定される機能について説明します。 いつでも[管理センター](https://entra.microsoft.com/)に戻ってテナントをカスタマイズし、テナントのすべての構成オプションを試すことができます。 最新の開発者向けコンテンツとリソースについては、[外部 ID デベロッパー センター](https://aka.ms/ciam/dev)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/quickstart-tenant-setup"} -->
## 外部テナントのクイック スタート - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup
- Service: entra-external-id / external
- Article date: 2025-04-08
- Summary: このクイック スタートでは、顧客の ID およびアクセス管理 (CIAM) 用の外部テナントを作成する方法について説明します。 サインイン エクスペリエンスをカスタマイズし、サンプル アプリで試してみてください。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra 外部 ID には、アプリとサービス用に安全でカスタマイズされたサインイン エクスペリエンスを作成できる顧客 ID アクセス管理 (CIAM) ソリューションが用意されています。 作業を開始するには、Microsoft Entra 管理センターで外部構成を持つテナントを作成する必要があります。 外部構成を持つテナントが作成されたら、Microsoft Entra 管理センターと Azure portal の両方でそれにアクセスできます。

このクイックスタートでは、Azure サブスクリプションが既にある場合に、外部構成を持つテナントを作成する方法について説明します。

### 前提条件

- Azure サブスクリプション。
- 少なくともサブスクリプションをスコープとする [テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator) ロールが割り当てられている Azure アカウント。

### 外部構成を持つ新しいテナントを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[概要]**&gt;**[テナントを管理する]** を参照します。
3. **［作成］** を選択します

    [Image: テナントの作成オプションのスクリーンショット。]
4. **[外部]** を選択し、**[続行]** を選択します。

    [Image: テナントの種類の選択画面のスクリーンショット。]
5. **[テナントの作成]** ページの **[基本]** タブで、次の情報を入力します。

    [Image: [基本] タブのスクリーンショット。]

    - 目的の **[テナント名]** (例: *Contoso Customers*) を入力します。
    - 目的の **[ドメイン名]** (例: *Contosocustomers*) を入力します。
    - 目的の **[場所]** を選択します。 この選択を後から変更することはできません。
6. **[次へ: サブスクリプションの追加]** を選択します。
7. **[サブスクリプションの追加]** タブで、次の情報を入力します。

    - **[サブスクリプション]** の横にあるメニューから自分のサブスクリプションを選択します。
    - **[リソース グループ]** の横にあるメニューからリソース グループを選択します。 使用可能なリソース グループがない場合は、**[新規作成]** を選択し、名前を追加して、**[OK]** を選択します。
    - **[リソース グループの場所]** が表示されたら、メニューからリソース グループの地理的な場所を選択します。

    [Image: サブスクリプションの設定を示すスクリーンショット。]
8. **次へ: 確認と作成** を選択します。 入力した情報が正しい場合は、**[作成]** を選択します。 テナント作成プロセスは、最長で 30 分かかる場合があります。 テナント作成プロセスの進行状況は、**[通知]** ウィンドウで監視できます。 テナントが作成されたら、Microsoft Entra 管理センターと Azure portal の両方でそれにアクセスできます。

    [Image: 新しいテナントへのリンクを示すスクリーンショット。]

### ガイドを使用してテナントをカスタマイズする

このガイドでは、ユーザーを設定し、わずか数分でサンプル アプリを構成するプロセスについて説明します。 つまり、さまざまなサインインとサインアップのオプションをすばやく簡単にテストし、サンプル アプリを設定して最適なものを確認できます。 このガイドは、任意の外部テナントで使用できます。

注

このガイドは、上記の手順で作成した外部テナントでは自動的には実行されません。 ガイドを実行する場合は、次の手順に従ってください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **[ホーム]**&gt;**[テナントの概要]** を参照します。
4. [作業の開始] タブで、**[ガイドの開始]** を選択します。

    [Image: ガイドを使用して始める方法を示すスクリーンショット。]

このリンクをクリックすると、3 つの簡単な手順でテナントをカスタマイズできる[ガイド](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-get-started-guide)が表示されます。

注

[Visual Studio Code 用 Microsoft Entra 外部 ID 拡張機能](https://aka.ms/ciamvscode/quickstarts/marketplace)を使用して、Visual Studio Code 内で直接外部テナントを設定およびカスタマイズすることもできます。 詳細については、[クイックスタート ガイド](https://aka.ms/ciamvscode/quickstartguide)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/reference-group-app-roles-support"} -->
## 外部テナントでのグループとアプリ ロールのサポート - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-group-app-roles-support
- Service: entra-external-id / external
- Article date: 2023-05-01
- Summary: ユーザーとグループの管理モデルとアプリケーションの割り当てに関連する Microsoft Entra のコア機能のうちどれが、外部テナントで使用できるか確認します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

外部テナントは、Microsoft Entra のユーザーおよびグループ管理モデルとアプリケーションの割り当てに従います。 Microsoft Entra のコア機能の多くは、外部テナントに段階的に移行されています。 次の表には、現在使用できる機能が示されています。

| **機能** | **現在利用可能?** |
| --- | --- |
| リソースのアプリケーション ロールを作成する | はい (アプリケーション マニフェストを変更する) |
| アプリケーション ロールをユーザーに割り当てる | はい |
| アプリケーション ロールをグループに割り当てる | はい (Microsoft Graph 経由のみ) |
| アプリケーションロールをアプリケーションに割り当てる | はい (アプリケーションのアクセス許可を使用) |
| ユーザーをアプリケーション ロールに割り当てる | はい |
| アプリケーションをアプリケーション ロールに割り当てる (アプリケーションのアクセス許可) | はい |
| アプリケーション/サービス プリンシパルにグループを追加する (グループ要求) | はい (Microsoft Graph 経由のみ) |
| Microsoft Entra 管理センターを使用して顧客 (ローカル ユーザー) を作成、更新、削除する | はい |
| Microsoft Entra 管理センターを使用して顧客 (ローカル ユーザー) のパスワードをリセットする | はい |
| Microsoft Graph を使用して顧客 (ローカル ユーザー) を作成、更新、削除する | はい |
| Microsoft Graph を使用して顧客 (ローカル ユーザー) のパスワードをリセットする | はい (サービス プリンシパルがグローバル管理者ロールに追加されている場合のみ) |
| Microsoft Entra 管理センターを使用してセキュリティ グループを作成、更新、削除する | はい |
| Microsoft Graph API を使用してセキュリティ グループを作成、更新、削除する | はい |
| Microsoft Entra 管理センターを使用してセキュリティ グループのメンバーを変更する | はい |
| Microsoft Graph API を使用してセキュリティ グループのメンバーを変更する | はい |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/reference-oidc-claims-mapping-customers"} -->
## OIDC の要求マッピングを設定する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-oidc-claims-mapping-customers
- Service: entra-external-id / external
- Article date: 2026-06-11
- Summary: ID プロバイダーが外部テナントで提供する要求を使用して、標準の OpenID Connect 要求を構成する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

OpenID Connect プロトコルでは、要求はエンド ユーザーに関する情報を通信します。 クレームは、ID プロバイダーがそのユーザーに対して発行する ID トークンに含まれるユーザー情報の一部です。 ID トークンには、エンド ユーザーに関する要求が含まれています。 サインアップ時に、これらの要求はユーザーを一意に識別し、追加のプロファイル情報を提供するのに役立ちます。 値は、ディレクトリ内の対応するユーザー属性に格納されます。

要求マッピングを設定するには、Microsoft Entra 外部 ID テナントに ID プロバイダー (IdP) を作成します。 IdP 構成には要求 **マッピング** セクションが含まれています。このセクションでは、ID プロバイダーが ID トークンで提供する要求に標準の OpenID Connect (OIDC) 要求をマップできます。

[Image: Microsoft Entra 管理センターの [OpenID Connect ID プロバイダーの構成] ページのスクリーンショット。[要求マッピング] セクションが強調表示されています。]

### 要求と属性のマッピング

次の表を使用して、標準の OpenID Connect 要求を、対応するユーザー フロー属性と IdP 要求にマップします。

| OIDC 標準要求 | ユーザー フロー属性 | Description |
| --- | --- | --- |
| sub | N/A | サブジェクト - 発行者のエンド ユーザーの識別子。 |
| 名前 | 表示される名前 | エンドユーザーのロケールと好みに従って順序付けられ、表示可能な形式で全名前部（タイトルやサフィックスを含む場合があります）を含むフルネーム。 |
| given\_name | 名前 | エンド ユーザーの指定された名前または指定された名。 |
| family\_name | 姓 | エンド ユーザーの姓またはファミリ名。 |
| 電子メール (既定で必要) | Email | 優先メール アドレス。 外部 IdP サインアップ シナリオでは [省略可能にすることができます](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers#make-email-optional-for-external-identity-provider-sign-up) 。 |
| メール確認済み | N/A | ID プロバイダーがエンド ユーザーの電子メール アドレスを検証したかどうかを示します。 `true` は、ID プロバイダーが検証の実行時に電子メール アドレスがエンド ユーザーによって制御されたことを確認するための肯定的な手順を実行していることを意味します。 電子メール要求が存在する場合は、アカウントの作成に `true` の値が必要です。 電子メール要求が存在せず、 [電子メールがオプションとして構成されている](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers#make-email-optional-for-external-identity-provider-sign-up)場合、アカウントの作成は電子メール アドレスなしで続行されます。 |
| 電話番号 | 電話番号 | この主張は、ユーザーの電話番号を提供します。 |
| 電話番号が確認済み | N/A | 受信した ID トークンでは、エンド ユーザーの電話番号が検証されている場合、この要求の値は true になります。それ以外の場合は false。 この要求値が true の場合は、ID プロバイダーが電話番号を確認するための肯定的な手順を実行したことを意味します。 |
| street\_address | 番地 | 宛名ラベルに表示または使用するために書式設定された完全な郵送先住所。 トークン応答では、このフィールドには改行で区切られた複数の行が含まれる場合があります (MAY)。 改行は、復帰/改行ペア ("\r\n") または単一の改行文字 ("\n") として表すことができます。 |
| ローカリティ | 市区町村 | 市区町村または地域。 |
| リージョン | 都道府県 | 州、県、都道府県、または地域。 |
| 郵便番号 | 郵便番号 | 郵便番号またはポスタルコード。 |
| country | 国または地域 | 国名。 |

注

ID プロバイダーからの要求をユーザー オブジェクトに格納するには、対応するユーザー フロー属性をユーザー フローに含める必要があります。 まず、外部 ID プロバイダーの要求を OIDC 標準要求にマップします。 次に、ID プロバイダーがアタッチされているユーザー フローで、対応するユーザー フロー属性を有効にします。 サインアップ時に属性をユーザーに表示したくない場合は、属性 [を非表示](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#configure-attribute-visibility-and-editability-with-microsoft-graph) にしたままユーザー フローに保持し、要求値が格納されるようにすることができます。

### ID プロバイダーを確認する

要求マッピングを追加した後、[確認] タブで OIDC の構成を **確認** します。[ **確認** ] タブには、IdP 要求にマップした要求一覧と対応するユーザー フロー属性が表示されます。

[Image: ID プロバイダー構成を保存する前にマップされた OIDC 要求と対応するユーザー フロー属性を示す [確認] タブのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/reference-service-limits"} -->
## サービスの制限と制約 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits
- Service: entra-external-id / external
- Article date: 2025-07-07
- Summary: 外部テナントでのサービスの制限と制約について学習します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、外部テナントのMicrosoft Entra 外部 IDのサービス制限と使用上の制約について説明します。これは、Microsoftの最新の顧客 ID およびアクセス管理 (CIAM) ソリューションです。 Microsoft Entra IDサービスの制限の完全なセットを探している場合は、[Microsoft Entra サービスの制限と制限](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)を参照してください。

### ユーザーまたは従量課金に関連する制限

外部テナントで認証できるユーザーの数は、要求の制限によって制限されます。 次の表は、テナントの要求制限を示しています。

| カテゴリ | なし |
| --- | --- |
| 外部テナントごとの IP あたりの最大要求数 | 1 秒あたり 20 |
| 外部テナントあたりの最大要求数 | 1 秒あたり 200 |
| 外部試用版テナントあたりの最大要求数 | 1 秒あたり 20 |

### エンドポイント要求の使用状況

Microsoft Entra 外部 IDは、[OAuth 2.0](https://datatracker.ietf.org/doc/html/rfc6749)、[OpenID Connect (OIDC)](https://openid.net/certification/) プロトコルに準拠しています。 次の表には、エンドポイントと、各エンドポイントで使用される要求の数が一覧表示されています。

| エンドポイント | エンドポイントの種類 | 使用される要求 |
| --- | --- | --- |
| /oauth2/v2.0/authorize | 動的 | 場合により異なる |
| /oauth2/v2.0/トークン | 静的 | 1 |
| /.well-known/openid-config | 静的 | 1 |
| /discovery/v2.0/keys | 静的 | 1 |
| /oauth2/v2.0/ログアウト | 静的 | 1 |

### トークン発行レート

ユーザー フローの種類ごとに一意のユーザー エクスペリエンスが提供され、異なる数の要求が使用されます。 ユーザー フローのトークン発行レートは、静的エンドポイントと動的エンドポイントの両方で使用される要求の数によって異なります。 次の表は、各ユーザー フローの動的エンドポイントで使用される要求の数を示しています。

| ユーザー フロー | 使用される要求 |
| --- | --- |
| サインアップ | 6 |
| サインイン | 4 |
| パスワードのリセット | 4 |

ユーザー フローに、多要素認証などの機能を追加すると、より多くの要求が使用されます。 次の表は、ユーザーが次の機能のいずれかを操作するときに使用される追加の要求の数を示しています。

| 機能 | 使用される追加の要求 |
| --- | --- |
| メールのワンタイム パスワード | 2 |

ユーザー フローの 1 秒あたりのトークン発行レートを取得する方法:

1. 上記の表を使用して、動的エンドポイントで使用される要求の総数を追加します。
2. アプリケーションの種類に基づいて、静的エンドポイントで必要な要求の数を追加します。
3. 次の数式を使用して、1 秒あたりのトークン発行レートを計算します。

```
Tokens/sec = 200/requests-consumed
```

### 外部構成テナントのMicrosoft Entra IDの構成制限

次の表に、Microsoft Entra 外部 ID サービスの管理構成の制限を示します。

| カテゴリ | なし |
| --- | --- |
| アプリケーションあたりのスコープの数 | 1000 |
| ユーザーあたりのカスタム属性の数 | 100 |
| アプリケーションあたりのリダイレクト URL の数 | 100 |
| アプリケーションあたりのサインアウト URL の数 | 1 |
| 属性あたりの文字列制限 | 250 文字 |
| サブスクリプションあたりの外部テナントの数 | 20 |
| 試用版テナントあたりのオブジェクト (ユーザー アカウントとアプリケーション) の合計数 (拡張できません) | 1万 |
| テナントあたりのオブジェクト (ユーザー アカウントとアプリケーション) の総数 この制限を引き上げる場合は、[Microsoft サポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-support-help-options#create-an-azure-support-request)にお問い合わせください。 | 300,000 |
| [カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)の数 | 100 |
| イベント リスナー ポリシーの数 | 249 |
| カスタム認証拡張機能の最大タイムアウト | 2,000 ミリ秒 |
| カスタム認証拡張機能の最大再試行回数 | 1 |
| 1 秒あたりの最大カスタム認証拡張機能要求数(テナント内のすべての拡張機能で結合) | 50 |

### テレフォニーの調整の制限

次の表に、停止や速度低下を防ぐために実装するサービスの制限を示します。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-telephony-fraud)

| なし | 15 分ごとにテキスト | 60 分ごとにテキスト | 24 時間ごとにテキスト | 7 日ごとにテキスト |
| --- | --- | --- | --- | --- |
| IP アドレスに基づく制限 | テキスト 100 個 | テキスト 300 個 | 500通のテキスト | 無制限 |
| 電話番号に基づく制限 | テキスト 15 個 | テキスト 20 個 | テキスト 30 個 | テキスト 50 個 |
| テナントに基づく制限 | 500通のテキスト | 1500のテキスト | 5,000通のテキスト | 無制限 |

### スケールとデプロイ モードによる機能のサポート

次の表は、テナントのディレクトリ スケールとデプロイ モードに基づいて使用できるMicrosoft Graph機能を示しています。

| 機能領域 | 標準モード\* | HSC モード |
| --- | --- | --- |
| 高度なディレクトリ クエリ (フィルター処理、並べ替え、カウント、検索、推移的メンバーシップ) | サポートされている | サポートしていません |
| 変更ベース (デルタ) クエリ | サポートされている | サポートしていません |
| SCIM 送信ユーザー プロビジョニング | サポートされている | サポートしていません |

\* この表に記載されている機能は、最大 1,500 万個のディレクトリ オブジェクトを持つテナントの標準モードでサポートされています。

注

これらの機能は、ディレクトリ サイズに関係なく HSC モードでは使用できません。 HSC モードでは、クエリ負荷の高いディレクトリ操作またはイベントドリブン ディレクトリ操作よりも、大規模な安定性とスループットが優先されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/reference-training-videos"} -->
## トレーニング、デモ、ビデオ - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-training-videos
- Service: entra-external-id / external
- Article date: 2025-09-16
- Summary: Microsoft Entra 外部 IDトレーニング、ライブ デモ、ビデオについて掘り下げる。 セキュリティで保護されたサインアップ エクスペリエンスを作成し、多要素認証を使用してアクセスを保護する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

### トレーニング モジュール

Microsoft Entra 外部 ID トレーニング モジュールは、Microsoft Entra 外部 IDを探索、理解、評価するための概念実証として機能します。 これは、Microsoft Entra 外部 IDを使用して顧客を登録し、サインインするオンライン食料品店で作業する架空のシナリオについて説明します。 モジュールが終了すると、実際のプロトタイプが独自の環境で実行されます。

トレーニング モジュールでは、次の手順を実行します:

- 最初のMicrosoft Entra 外部 ID テナントを作成します。
- Web アプリケーションのためのブランド化されたシームレスで安全なサインアップとサインイン エクスペリエンスを構築します。
- 消費者および企業顧客のニーズに照らして Microsoft Entra 外部 ID の ID プロバイダーの機能を評価する
- 条件付きアクセスと多要素認証を使用して、アプリケーションへのアクセスを保護します。

トレーニングを開始するには、[\[ガイド付き プロジェクト – Microsoft Entra 外部 ID を評価するためにサンプル アプリを構築する\]](https://aka.ms/eeid/training-module) に移動して、順番にユニットに従います。

### ビデオ ライブラリにアクセスする

Microsoft Entra 外部 IDビデオはドキュメントに組み込まれており、YouTube の 「[Microsoft Security チャンネル](https://www.youtube.com/microsoft-security)」と「[Microsoft Entra 外部 IDプレイリスト](https://www.youtube.com/playlist?list=PL3ZTgFEc7Lythpts59O9KOVuEDLWJLLmA)」でも見つけることができます。 これらのビデオは、概念的な説明や実用的な「操作方法」ガイドから、コースとして作られた広範なシリーズものまで幅広い種類があります。

ビデオ ライブラリは定期的に拡張されるため、最新の更新プログラムについては必ず Microsoft Security チャンネルに登録してください。 ここでは、Microsoft Entra 外部 IDの使用を開始するのに役立つビデオをいくつか紹介します。

#### Microsoft Entra 外部 ID の概要

Microsoft Entra External ID は、Microsoft の顧客 ID およびアクセス管理 (CIAM) プラットフォームです。 外部向けアプリケーションにアクセスできるユーザーを制御し、オンライン ID を継続的に確認しながら、個人情報とプライバシーを確実に保護することができます。 このビデオでは、Microsoft Entra External ID の概要、その機能、およびエンド ユーザーとして使用する方法について説明します。

#### Microsoft Entra 外部 ID の概要

Microsoft Entra External ID を使用すると、顧客向けのアプリに対してセキュリティで保護されたカスタマイズ可能なサインインが可能になります。 製品を購入したり、サービスをサブスクライブしたり、アカウント データにアクセスしたりするためのアプリを顧客に提供したい企業では、堅牢な顧客 ID とアクセス管理 (CIAM) が提供されます。 アプリを簡単に統合し、Microsoft Entra のすべてのセキュリティ、信頼性、スケーラビリティの利点を得ることができます。 このビデオでは、最も一般的に使用される Entra 外部 ID 機能の一部について説明します。

#### Woodgrove Groceries ライブ デモ

Woodgrove Groceries ライブ デモは、オンライン ショッピング アプリにMicrosoft Entra 外部 IDを使用する架空のグローバル食品小売業者です。 このビデオでは、ライブ デモの使用方法と、顧客向けアプリ用に構成できる認証機能を試す方法を説明します。

#### Microsoft Entra 外部 ID の使用を開始する

このチュートリアルでは、新しいMicrosoft Entra 外部 ID テナントを作成する手順について説明しますので、サンプル アプリの実行とユーザーのサインインを始めることができます。 また、関連するさまざまなコンポーネントについて掘り下げ、構成を強化する方法についても説明します。

#### ソーシャル アカウントでのサインインを有効にする

このビデオでは、Facebook、Google、Apple などのソーシャル ID プロバイダーをアプリケーションのサインアップとサインインフローに統合する方法について説明します。 こちらでは、登録エクスペリエンスを強化およびカスタマイズする方法に重点を置いています。 また、Microsoft Entra 外部 IDを使用して、堅牢なセキュリティを確保し、ユーザーを効率的に管理する方法についても説明します。

#### OpenID Connect ID プロバイダーでのサインインを有効にする

このビデオでは、Microsoft Entra External ID でカスタム OpenID Connect ID プロバイダーを構成する方法について説明します。 既知のエンドポイント、発行者 URI、要求マッピングなどの必要な詳細を入力するなど、新しいフェデレーションを設定する手順について説明します。

### アプリケーションへのアクセスを保護する

次のビデオでは、Microsoft Entra 外部 ID を使用してアプリケーションへのアクセスを保護する方法について説明します。 アプリケーション アクセスをセキュリティで保護し、Microsoft Entra 外部 ID を使用して強化された保護を実装して、承認されたユーザーとソフトウェア コンポーネントのみがアプリケーションにアクセスできるようにする手順について説明します。

#### アプリケーションへのアクセスを承認する

このビデオでは、Microsoft Entra ID と Microsoft Entra 外部 ID を使用したアプリケーションのロールベースの制御とクレームベースの認可の複雑さを詳しく探ります。

[第 2 部](https://youtu.be/Sc1y4WBHP2k?si=kDCv0Cts5UUHyM-b) では、Microsoft Entra 外部 ID を使用してアプリケーションのロールベースのアクセス制御する手順を説明し、アプリケーションと Web API に効果的に統合する方法を示します。

#### ステップアップ認証

ビデオでは、ステップアップ認証とテナント構成について説明します。 ステップアップ認証では、ユーザーはユーザー名とパスワード、ソーシャル ID などの最小限の認証手順でサインインします。 ただし、価値の高いトランザクションや機密データへのアクセスなど、リスクの高いアクションでは、アプリケーションはより多くの検証を必要とします。

#### カスタム認証拡張機能の概要

この入門ビデオでは、Microsoft Entra カスタム認証拡張機能の主な機能と利点について説明します。 独自のクレーム プロバイダーを統合し、サインアップ プロセス中に入力検証を実行し、確認メールをカスタマイズする方法について説明します。

#### カスタム認証拡張機能を始める

このビデオでは、Microsoft Entra カスタム認証拡張機能の構成について詳しく説明し、最適な実装のためのベスト プラクティスと貴重なヒントを提供します。

#### Microsoft Entra カスタム クレーム プロバイダーを構成する

Microsoft Entra カスタム認証拡張機能を使用すると、組織はカスタム ビジネス ロジックを認証ワークフローに統合できます。 このビデオでは、Microsoft Entra カスタム認証拡張機能 (カスタム クレーム プロバイダーとしての knonwd) を使用して、外部システムからの要求をセキュリティ トークンにマッピングする手順について説明します。

#### Microsoft Entra カスタム認証拡張機能を構築する

このビデオでは、コードを記述せずに、Azure Logic Apps で Microsoft Entra カスタム認証拡張機能を作成する方法について説明します。 Azure Logic App を使用すると、ユーザーはビジュアル デザイナーを使用してワークフローを構築できます。 このビデオでは、検証メールのカスタマイズについて説明し、カスタム要求プロバイダーを含むすべての種類のカスタム認証拡張機能に適用されます。

#### ユーザーのプロファイルを編集する

このビデオでは、Microsoft Entra External ID でユーザー プロファイルにアクセスして編集するために使用できるさまざまな方法について説明します。

#### Microsoft Graph と継続的インテグレーション

このビデオでは、タスクの自動化とバッチ操作の実行に Microsoft Graph API と Microsoft Graph PowerShell を使用する利点について説明します。 GitHub ワークフローを使用してデプロイを効率化し、統合とデプロイの問題を軽減し、リリース サイクルを高速化し、変更管理を改善し、さまざまな環境でバージョン コントロールを維持します。

#### Microsoft Entra 外部 ID の VS Code 拡張機能

Visual Studio Code の Microsoft Entra External ID 拡張機能を使用すると、外部テナントをすばやく作成し、外部ユーザーのサインイン エクスペリエンスを構成し、すべて Visual Studio Code 内で直接外部 ID サンプルを設定できます。 拡張機能をインストールして使用する方法については、このビデオをご覧ください。

#### 使い方ビデオ

これらの使い方ビデオでは、Microsoft Entra 外部 IDの使用方法について説明します:

- アプリケーションと Web API の [OAuth 2.0 On-Behalf-Of フローの使用方法](https://youtu.be/S2uYPgxBbMw?si=YdAn75BGyFwaUZ5q)。
- [ステップアップ認証](https://youtu.be/869Opl4TQT0?si=gGLBvm6RN0u8HB7r)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/reference-user-permissions"} -->
## 外部テナントでのユーザーの既定のアクセス許可 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions
- Service: entra-external-id / external
- Article date: 2025-03-10
- Summary: 外部テナントでのユーザーの既定のアクセス許可について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

"外部" 構成の Microsoft Entra テナントは、*Microsoft Entra 外部 ID* シナリオにのみ使用されます。 外部テナントを使うと、企業の従業員のディレクトリと顧客向けアプリのディレクトリを明確に分離できます。 既定では、外部テナントで作成されたユーザーは、外部テナント内の他のユーザー、グループ、またはデバイスに関する情報へのアクセスが制限されます。 管理者ロールを割り当てない限り、すべてのユーザーに既定のアクセス許可があります。

外部テナントのユーザーの一般的なユース ケースをよりよく理解するために、次のように分類できます。

- **外部ユーザー** は、外部テナントに登録されているアプリを使用するコンシューマーおよびビジネス ユーザーです。 通常、ユーザーの既定のアクセス許可は保持されます。つまり、管理者ロールは割り当てられません。 通常、これらのユーザーはセルフサービス サインアップを通じて作成されますが、Microsoft Entra 管理センターまたは Microsoft Graph の [\[Create new external user](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#create-a-new-external-user)]\(新しい外部ユーザーの作成\) オプションを使用して作成できます。
- **内部ユーザー** は、通常、Microsoft Entra ロール 割り当てる管理者です。 管理センターまたは Microsoft Graph の [新しいユーザー [の作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#create-a-new-user) オプションを使用して、内部ユーザーを作成し、ロールを割り当てることができます。
- **招待されたユーザー** は、通常、外部テナントに招待する管理者であり、Microsoft Entra ロール 割り当てる管理者です。 ロールが割り当てられない場合は、既定のユーザー アクセス許可を持ちます。 管理センターまたは Microsoft Graph の [外部ユーザーの招待] [オプション](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#invite-an-external-user) 使用して、ユーザーを招待し、ロールを割り当てることができます。

ユーザーが外部テナントに作成されると、それらはすべて既定のアクセス許可で始まります。 ただし、外部テナント内 [管理タスクを実行する必要があるユーザーには、](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) Microsoft Entra ロールを割り当てることができます。

### 既定のアクセス許可

次の表では、以下を含む外部テナントのユーザーに割り当てられた既定のアクセス許可について説明します。

- セルフサービス サインアップを使用するユーザー
- 管理者によって作成されたユーザー
- 招待されたユーザー

| **領域** | **既定のユーザーアクセス許可** |
| --- | --- |
| ユーザーと連絡先 | - アプリ プロファイル管理エクスペリエンスを使用して独自のプロファイルを読み取って更新する<br>- 自分のパスワードを変更する<br>- ローカル アカウントまたはソーシャル アカウントでサインインする |
| アプリケーション | - アプリケーションにアクセスする<br>- アプリケーションへの同意を取り消す |

### Microsoft Graph API とアクセス許可

次の表は、顧客がプロファイル情報を管理できるようにする API 操作を示しています。 ユーザー ID または userPrincipalName は、常にサインインしているユーザーの ID です。

| ユーザー操作 | API 操作 | 必要なアクセス許可 |
| --- | --- | --- |
| プロファイルの読み取り | [GET /me](https://learn.microsoft.com/ja-jp/graph/api/user-get) または [GET /users/{id または userPrincipalName}](https://learn.microsoft.com/ja-jp/graph/api/user-get) | User.Read |
| プロファイルの更新 | [PATCH /me](https://learn.microsoft.com/ja-jp/graph/api/user-update) または [PATCH /users/{id または userPrincipalName}](https://learn.microsoft.com/ja-jp/graph/api/user-update) 更新可能なプロパティ: city、country、displayName、givenName、jobTitle、postalCode、state、streetAddress、surname、preferredLanguage | User.ReadWrite さん |
| [パスワードの変更] | [POST /me/changePassword](https://learn.microsoft.com/ja-jp/graph/api/user-changepassword) | Directory.AccessAsUser.All |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/samples-ciam-all"} -->
## 外部 ID を持つアプリを統合するためのサンプルとガイド - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all
- Service: entra-external-id / external
- Article date: 2026-10-05
- Summary: サインアップ、サインイン、API を呼び出すアクセス トークンの取得などのシナリオで、外部テナントとアプリを構築して統合する方法について説明します。

Microsoft では、さまざまな種類のアプリケーションを Microsoft Entra 外部 ID に統合する方法を示すコード サンプルを保持しています。 一般的な認証と承認のシナリオ、開発言語、プラットフォームに基づいて、サンプルをダウンロードして使用したり、独自のアプリを構築したりする手順について説明します。 プロジェクトをビルドし (該当する場合)、サンプル アプリケーションを実行する手順が含まれています。 サンプル コード内ではコメントにより、これらのライブラリをアプリケーション内でどのように使用して、外部テナント内で認証と認可を行うかを理解することができます。

Tip

Microsoft Entra 外部 IDでは、2 つの認証方法がサポートされています。**ブラウザー委任認証**は、ユーザーをMicrosoftホスト型サインイン ページにリダイレクトし、**ネイティブ認証を**使用してアプリで直接サインイン UI を構築できます。 この記事のサンプルには、両方が含まれています。 使用する方法がわからない場合は、「 [認証方法の選択」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)参照してください。

### サンプルとガイド

タブを使って、アプリの種類または優先される言語やプラットフォームでサンプルを並べ替えます。

Von Bedeutung

パスキー資格情報管理サンプルでは、委任されたアクセス許可を使用して一覧表示と登録を行います。 その削除フローでは、高い特権のアプリケーション権限を持つMicrosoft Graphと、ブラウザー コード内のクライアント シークレットが引き続き使用されます。 運用環境ではなく、テスト テナントでのみこのサンプルを実行してください。

## [アプリの種類別](#tab/apptype)
#### シングルページ アプリケーション (SPA)

これらのサンプルと攻略ガイドでは、シングルページ アプリケーションを Microsoft Entra 外部 ID に統合する方法を示します。

| 言語/プラットフォーム | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| JavaScript | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in?pivots=external&tabs=javascript-external) • [パスキーの一覧表示と登録 (GitHub サンプル)](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/tree/main/passkey-sample) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-prepare-app?tabs=external-tenant) • [パスキーを使用してサインインする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey) |
| Angular（アンギュラー） | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in?pivots=external&tabs=angular-external) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-prepare-app?tabs=external-tenant) |
| 反応する | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in?pivots=external&tabs=react-external) • [パスキーの一覧表示と登録 (GitHub サンプル)](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/tree/main/passkey-sample) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app?tabs=external-tenant) • [パスキーを使用してサインインする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey) |

#### Web アプリ

これらのサンプルと攻略ガイドでは、Microsoft Entra 外部 ID に統合する Web アプリケーションを記述する方法を示します。

| 言語/プラットフォーム | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| JavaScript、Node.js (Express) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=node-external) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-call-api) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-web-app-node-sign-in-call-api-prepare-tenant) |
| ASP.NET Core | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=asp-dot-net-core-external) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app?tabs=external-tenant) |
| Pythonジャンゴ | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=python-django-external) | --- |
| パイソンフラスコ | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=python-flask-external) | --- |

#### Web API

これらのサンプルと攻略ガイドは、Microsoft ID プラットフォームで Web API を保護する方法と、その Web API からダウンストリーム API を呼び出す方法を示しています。

| 言語/プラットフォーム | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| ASP.NET Core | --- | • [ASP.NET Web API をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-build-app) |

#### デスクトップ

これらのサンプルと攻略ガイドでは、Microsoft Entra 外部 ID に統合するデスクトップ アプリケーションを記述する方法を示します。

| 言語/プラットフォーム | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| JavaScript、Electron | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-sign-in?pivots=external&tabs=node-js-external) | --- |
| ASP.NET (マウイ島) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-sign-in?pivots=external&tabs=wpfdotnet-maui-external) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-app-maui-sign-in-prepare-app) |
| .NET (MAUI) WPF | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-sign-in?pivots=external&tabs=wpfdotnet-wpf-external) | --- |

#### モバイル: ブラウザー委任認証

これらのサンプルと攻略ガイドでは、Microsoft Entra 外部 ID と統合する、ブラウザー委任認証を使用したパブリック クライアント モバイル アプリケーションを作成する方法を示します。

| 言語/プラットフォーム | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| ASP.NET Coreマウイ島 | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-sign-in?pivots=external&tabs=android-netmaui-external) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-mobile-app-maui-sign-in-prepare-tenant) |
| Android (Kotlin) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-sign-in?pivots=external&tabs=android-external) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-call-api?pivots=external&tabs=android-external) | • [ユーザーのサインイン、API の呼び出し](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-mobile-app-android-kotlin-prepare-tenant) |
| iOS (Swift) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-sign-in?pivots=external&tabs=ios-macos-external) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-mobile-app-ios-swift-sign-in-call-api) | • [ユーザーのサインイン、API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-tenant?pivots=external) |

#### デスクトップ: ネイティブ認証

これらのサンプルと攻略ガイドでは、Microsoft Entra 外部 ID に統合するデスクトップ アプリケーションを記述する方法を示します。

| 言語/プラットフォーム | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| macOS (Swift) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-macos-sign-in) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app) |

#### モバイル: ネイティブ認証

これらのサンプルと攻略ガイドでは、Microsoft Entra 外部 ID と統合する、ネイティブ認証を使用したパブリック クライアント モバイル アプリケーションを作成する方法を示します。

| 言語/プラットフォーム | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| Android (Kotlin) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-sign-in) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-call-api) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-android-app) |
| iOS (Swift) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-sign-in) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-call-api) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app) |

#### デーモン

これらのサンプルと攻略ガイドでは、Microsoft Entra 外部 ID に統合するデーモン アプリケーションを記述する方法を示します。

| 言語/プラットフォーム | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| Node.js | • [API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-call-api?pivots=external&tabs=node-external) | • [API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-daemon-node-call-api-build-app?pivots=external&tabs=asp-dot-net-core-external) |
| .NET | • [API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-call-api?pivots=external&tabs=asp-dot-net-core-external) | • [API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-dotnet-daemon-call-api) |

## [言語/プラットフォーム別](#tab/language)
#### .NET

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| デーモン | • [API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-call-api?pivots=external&tabs=asp-dot-net-core-external) | • [API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-dotnet-daemon-call-api) |

#### Android (Kotlin)

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| モバイル: ブラウザー委任認証 | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-mobile-app-android-kotlin-sign-in) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-mobile-app-android-kotlin-sign-in-call-api) | • [ユーザーのサインイン、API の呼び出し](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-mobile-app-android-kotlin-prepare-tenant) |
| モバイル: ネイティブ認証 | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-sign-in) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-call-api) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-android-app) |

#### ASP.NET Core

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| Web API | --- | • [ASP.NET Web API をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-build-app) |
| Web アプリ | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=asp-dot-net-core-external) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app?tabs=external-tenant) |

#### .NET (マウイ島)

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| デスクトップ | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-sign-in?pivots=external&tabs=wpfdotnet-maui-external) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-app-maui-sign-in-prepare-app) |
| モバイル: ブラウザー委任認証 | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-sign-in?pivots=external&tabs=android-netmaui-external) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-mobile-app-maui-sign-in-prepare-tenant) |

#### Python、Django

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| Web アプリ | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=python-django-external) | --- |

#### Python、Flask

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| Web アプリ | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=python-flask-external) | --- |

#### iOS/macOS (Swift)

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| モバイル: ブラウザー委任認証 | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-sign-in?pivots=external&tabs=ios-macos-external) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-mobile-app-ios-swift-sign-in-call-api) | • [ユーザーのサインイン、API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-tenant?pivots=external) |
| モバイル: ネイティブ認証 | • iOS (Swift) [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-sign-in) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-call-api) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app) |
| デスクトップ: ネイティブ認証 | • macOS (Swift) [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-macos-sign-in) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app) |

#### JavaScript

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| シングルページ アプリケーション | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in?pivots=external&tabs=javascript-external) • [パスキーの一覧表示と登録 (GitHub サンプル)](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/tree/main/passkey-sample) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-prepare-app?tabs=external-tenant) • [パスキーを使用してサインインする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey) |

#### JavaScript、Angular

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| シングルページ アプリケーション | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in?pivots=external&tabs=angular-external) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-apps-angular-prepare-app?tabs=external-tenant) |

#### JavaScript、React

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| シングルページ アプリケーション | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in?pivots=external&tabs=react-external) • [パスキーの一覧表示と登録 (GitHub サンプル)](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/tree/main/passkey-sample) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app?tabs=external-tenant) • [パスキーを使用してサインインする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey) |

#### JavaScript、Node

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| デーモン | • [API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-call-api?pivots=external&tabs=node-external) | • [API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-daemon-node-call-api-build-app?pivots=external&tabs=asp-dot-net-core-external) |

#### JavaScript、Node.js (Express)

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| Web アプリ | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=node-external) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-call-api) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app) • [ユーザーのサインインと API の呼び出し](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-web-app-node-sign-in-call-api-prepare-tenant) |

#### JavaScript、Electron

| アプリの種類 | コード サンプル ガイド | ビルドと統合ガイド |
| --- | --- | --- |
| デスクトップ | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-sign-in?pivots=external&tabs=node-js-external) | --- |

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/services-integration-partners"} -->
## Azure AD B2C からMicrosoft Entra External ID移行のパートナー - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/services-integration-partners
- Service: entra-external-id / external
- Article date: 2026-03-26
- Summary: Microsoft Entra External IDを使用した顧客 ID およびアクセス管理 (CIAM) シナリオのデプロイと統合に役立つパートナーについて説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

### Microsoft Entra External IDのサービスと統合パートナー

以下に示すパートナーは、Azure AD B2C から Microsoft Entra External ID への移行の計画と実行に役立ちます。 このページを使用して、アドバイザリ、実装、エンジニアリング サポートなど、移行のニーズに基づいてパートナーを特定して連絡します。 各パートナーは、そのアプローチと機能を概説する追加のコンテンツを公開しています。

**この一覧の使用方法**

- ニーズに合ったパートナーを特定する (例: アドバイザリ、実装、エンジニアリング主導の配信)
- パートナーの説明とリンクされたリソースを確認する
- 移行の計画と実行を開始するには、パートナーに直接問い合わせてください

| 名前 | 説明 | 連絡先 |
| --- | --- | --- |
| [アバナード](https://www.avanade.com/en/services/microsoft-tech/microsoft-security/advanced-identity) | 「Avanade は、クライアントを Entra B2C から Entra External ID に移行するためのMicrosoftの立ち上げパートナーになることに興奮しています。 Entra B2C と Entra External ID の両方に関する当社の深い専門知識により、Entra External ID への移行を高速化し、移行リスクを軽減し、エンド ユーザー エクスペリエンスを向上させる独自の資産を開発しました。 Avanade は、Entra External ID のグリーンフィールドデプロイと移行サービスの両方を提供し、お客様のニーズに合わせてサービスを調整し、セキュリティを損なうことなく、消費者のサインインに可能な限り最高のエクスペリエンスを提供するようにクライアントと協力しています。" | identity@avanade.com |
| [Edgile (Wipro 企業)](https://edgile.com/blog/edgile-a-wipro-company-announced-as-eeid-migration-partner-by-microsoft/) | Wipro の会社である Edgile は、Microsoft Entra External IDのMicrosoft移行パートナーになることに興奮しています。 長年にわたり、Azure AD B2C を含む顧客 ID 環境のデプロイと管理を支援してきました。 カスタマー ID 管理、Azure AD B2C、Microsoftの後継の Entra External ID に関する豊富な経験により、プログラムが成功を収めるために独自に位置付けられます。 当社のプロジェクト アクセラレータは、お客様のリスクを軽減し、より迅速に結果を提供します。」 | info@edgile.com |
| [EY](https://www.ey.com/en_us/alliances/microsoft) | 「EY 組織は、深い業界分析情報と洗練された人間中心のアプローチを適用して、企業がMicrosoft Entra External IDで外部 ID を再考するのを支援します。 EY-Microsoft Alliance を通じて、実証済みのアクセラレータと配信方法により、Azure AD B2C からの移行が合理化され、複雑な統合が簡素化され、セキュリティで保護されたスケーラブルな ID 基盤が実現され、顧客、パートナー、開発者のコミュニティ全体の信頼が強化されます。" | americasmicrosoftallianceteam@ey.com |
| [グリット](https://www.gritiam.com/migration.html) | 「Grit Software は、コンシューマー ID とアクセス管理に関する深い専門知識を持ち、Fortune 500 および中堅企業が複雑な変革プロジェクトを適切かつ時間的に実行するのを支援してきた強力な実績を持っています。 Azure AD B2C から Microsoft Entra External ID への移行において、Grit の AI を活用した移行サービスは、高度なコーディングエージェントを使用することで、数日で正確な移行を実現しながら、顧客データが AI モデルに送信されないことを保証します。 | info@gritsoftwaresystems.com |
| [モダン 42](https://www.modern42.com/blog/microsoft-recommends-modern-42-for-azure-ad-b2c-entra-external-id-migrations-australia) | 「Modern 42 は、オーストラリア全体でエンタープライズ レベルの ID アドバイザリとエンジニアリング サービスを提供する専門の ID コンサルタントです。 Modern 42 は政府および民間セクターのお客様向けに Microsoft Entra External ID の経験を持ち、Azure AD B2C から Microsoft Entra External ID への移行を、どのような複雑さのある組織でも支援できる技術的な専門知識と経験を備えています。 Modern 42 のアプローチでは、戦略的コンサルティングと実践的な技術実装が組み合わされ、CIAM ソリューションがビジネス目標、規制要件、カスタマー エクスペリエンスの期待、セキュリティのベスト プラクティスに合わせて調整されます。" | hello@modern42.com |
| [PlanB。](https://www.planb.net/en/post/microsoft-entra-external-id) | "PlanB. は、ID とセキュリティに関する深い専門知識と、測定可能なビジネス成果に強い焦点を当てています。 microsoft は、Microsoft Entra エコシステム内の経験豊富なパートナーとして、顧客、エージェント、パートナー、サプライヤーなど、大規模な外部 ID をデジタル プラットフォーム全体に安全に統合して管理できるように支援します。 ID 戦略とアーキテクチャから、Microsoft Entra External IDの実践的な実装まで、セキュリティ、ガバナンス、コンプライアンス、ユーザー エクスペリエンスがシームレスに連携することを保証します。 私たちの使命は、複雑な ID シナリオを簡素化し、リスクを軽減し、信頼できる ID 基盤に基づいて構築された持続可能なデジタル成長を可能にすることです。 | Felix Rohmeier (Id のソリューション エキスパート) Identity@plan-b-gmbh.com |
| [スラローム](https://www.slalom.com/us/en/who-we-are/newsroom/microsoft-slalom-accelerate-microsoft-entra-migrations) | 「Slalom は、組織が大規模に ID システムを最新化するのに役立つ信頼できるMicrosoft パートナーです。 Entra External ID 移行の統合パートナーとして、Slalom は ID の移行、組織の変更管理、およびセキュリティで保護された顧客アクセスに関する実用的な経験を提供します。 B2C から Entra 外部 ID への移行アプローチでは、戦略的な計画、アーキテクチャの専門知識、実証済みの配信ツールを組み合わせて、複雑な移行を簡素化し、リスクを軽減します。 これにより、組織は、セキュリティで保護されたスケーラブルな顧客 ID ソリューションを提供し、Microsoft Entra プラットフォームを完全に活用できるようになります。 | MoreTogether@Slalom.com |
| [WhoIAM](https://whoiam.ai/product/azureadb2c-to-entraexternalid-migrati/) | 「WhoIAM は、Microsoft顧客 ID の信頼できるスペシャリストであり、Azure AD B2C から Microsoft Entra External ID への移行を通じて組織を導く独自の立場にあります。 元Microsoftの ID エンジニアリング リーダーによって設立された WhoIAM は、Azure AD B2C、Entra、および大規模な CIAM プラットフォーム全体に深い実践的な専門知識をもたらします。 目的に応じた移行ツール、実証済みの Just-In-Time および一括移行パターン、顧客エンジニアリングチームやセキュリティ チームとの緊密なコラボレーションにより、WhoIAM は組織が中断を最小限に抑えて ID を最新化し、Microsoft Entraで将来対応できる外部 ID 基盤を構築しながら、今日の継続性を確保するのに役立ちます。" | Info@whoiam.ai |

#### 次のステップ

- 上記の一覧からパートナーに問い合わせて移行を開始する
- Microsoft account チームと連携してサポートを受ける
- Microsoft Entra External ID の移行ガイダンスを確認します。
    - [AZURE AD B2C から外部 ID への移行を計画します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)
    - [Azure AD B2C から Microsoft Entra External ID に移行します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-from-b2c-to-external-id)
    - [外部 ID ハイ スケール互換性 (HSC) モードを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/enable-external-id-high-scale-compatibility-mode)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/troubleshoot-high-scale-compatibility-mode"} -->
## 高スケール互換性 (HSC) モードのトラブルシューティング - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/troubleshoot-high-scale-compatibility-mode
- Service: entra-external-id / external
- Article date: 2026-04-08
- Summary: AZURE AD B2C から Microsoft Entra 外部 ID に移行するために高スケール互換性 (HSC) API を使用する場合の一般的なエラーを診断して解決します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事は、Microsoft Entra 外部 IDおよびAzure AD B2C テナントに対して [High Scale Compatibility (HSC) モード](https://learn.microsoft.com/ja-jp/entra/external-id/customers/enable-external-id-high-scale-compatibility-mode) API を使用する場合の一般的なエラーを診断して解決するのに役立ちます。

Important

HSC API へのアクセスは、正しい **アクセス許可** と **管理者の同意**の両方によって異なります。 ほとんどの HSC 操作には、全体管理者アクセスが必要です。 委任されたアクセス許可を使用している場合は、管理者が必要なスコープに対する同意を付与していることを確認します。

### クイック リファレンス

| エラー メッセージ | HTTP 状態 |
| --- | --- |
| 許可されていない | 403 許可されていません |
| 未承認 (Graph 権限がありません) | 403 許可されていません |
| '{tenantId}' は Azure AD B2C のディレクトリではありません | 400 BadRequest |
| このテナントでは、Entra 外部 ID ハイブリッド アップグレードは許可されていません | 400 BadRequest |
| '{tenantId}' は、外部 ID ハイブリッド モードが有効になっているAzure AD B2C ディレクトリではありません | 403 許可されていません |
| POST 後の古い GET 応答 (キャッシュ動作) | 200 OK（正常に処理されました） |
| B2C からハイブリッド テナントの外部 ID コンテキストにカスタム属性を同期できませんでした | 400 BadRequest |
| テナント「{tenantId}」が有効なサブスクリプションにリンクされていません | 400 BadRequest |

### 呼び出し元に必要なロールがない

**HTTP 状態:** 403 未承認**影響を受けるエンドポイント:** すべての HSC エンドポイント

呼び出し元 ID (ユーザーまたはサービス プリンシパル) には、必要なディレクトリ ロールがありません。

- グローバル管理者
- 外部IDユーザーフロー管理者
- `TenantAdmin`、 `CpimServiceAdminReaderWriter`、または `AppOnlyCaller` (アプリ専用フローの場合)

**修正方法:**

1. Azure ポータルで、**Microsoft Entra ID**&gt;**Roles and administrators** に移動します。
2. **グローバル管理者**または**外部 ID ユーザー フロー管理者**ロールを呼び出し元アカウントまたはサービス プリンシパルに割り当てます。
3. ロールの伝達を数分待ってから、要求を再試行します。

ヒント

自動化されたパイプラインの場合は、サービス プリンシパルを使用し、シナリオを満たす最小特権ロールを割り当てます。

### 呼び出し元にMicrosoft Graphアクセス許可がありません

**HTTP 状態:** 403 未承認**影響を受けるエンドポイント:** すべての HSC エンドポイント

アプリケーションの登録に必要なMicrosoft Graphアクセス許可`Policy.ReadWrite.AuthenticationFlows`がありません。

**修正方法:**

1. Azure ポータルで、**Microsoft Entra ID**&gt;**アプリ登録**&gt; あなたのアプリに移動します。
2. **API のアクセス許可**&gt;**アクセス許可の追加**&gt;**Microsoft Graph**&gt;**アプリケーションのアクセス許可**を選択します。
3. `Policy.ReadWrite.AuthenticationFlows`を検索して追加します。
4. [ **テナントに管理者の同意 *を付与する*** ] を選択し、確認します。
5. 要求をやり直してください。

### 誤ったテナントタイプのコンテキスト

**HTTP 状態:** 400 BadRequest エラー コード: `AccessDenied_NonB2CTenantNotAllowed`**エラー メッセージ:**`'{tenantId}' is not an Azure AD B2C directory. Access to this Api can only be made for an Azure AD B2C directory.`**影響を受けるエンドポイント:** すべての HSC エンドポイント

CIAM または Microsoft Entra ID (ワークフォース) テナント コンテキストに対して要求を発行しました。 HSC API は、B2C テナントに対して直接呼び出す必要があります。

**修正方法:**

1. 要求 URL とトークン対象ユーザーが B2C テナント ID を参照していることを確認します (たとえば、B2C テナントが Active Directory コンテキストとして設定された `https://graph.microsoft.com/v1.0/` )。
2. トークンを取得するときは、従業員テナントではなく、 `https://login.microsoftonline.com/<b2c-tenant-id>`に権限を設定します。
3. Azure ポータルでテナント ID を確認します:**Microsoft Entra ID**&gt;**Overview**&gt;**テナント ID**。

### 親テナントが有効になっていない

**HTTP 状態:** 400 BadRequest エラー コード: `HybridUpgradeNotAllowed`**エラーメッセージ：**`Entra External Id Hybrid Upgrade is not allowed for this tenant`**影響を受けるエンドポイント:** POST (ハイブリッド モードを有効にする)

親ワークフォース テナントには、 `EnableHybridUpgradeApi` 機能フラグが設定されていません。 このバックエンドの有効化を切り替えることができるのは、Microsoftだけです。

**修正方法:**

Microsoft サポートに連絡し、親ワークフォース テナントで `EnableHybridUpgradeApi` フラグを有効にすることを要求します。 サポート 要求に親テナント ID を含めます。

### 非ハイブリッド テナントでの DELETE

**HTTP 状態:** 403 禁止 エラー コード: `AccessDenied_NonHybridTenantNotAllowed`**エラー メッセージ:**`'{tenantId}' is not an Azure AD B2C directory with External Id Hybrid mode enabled. Access to this Api can only be made for an Azure AD B2C directory with Hybrid mode enabled.`**影響を受けるエンドポイント:** DELETE (ハイブリッド モードを無効にする)

ハイブリッド モードを無効にするために DELETE 要求を送信しましたが、テナントがまだ正常に有効になっていません (ハイブリッド モードを有効にする POST は完了していません)。

**修正方法:**

1. POST 要求を完了してハイブリッド モードを有効にし、 `201 Created` 応答を確認します。
2. テナントがハイブリッド状態になってから、DELETE 要求を再試行します。

### POST 後の古い GET 応答

**HTTP 状態:** 200 OK (ただし、データが古くなっている可能性があります)**エラー コード:** なし (予想されるキャッシュ動作)**影響を受けるエンドポイント:** GET (ハイブリッド状態の読み取り)

テナント メタデータは最大 1 時間キャッシュされます。 POST が成功した直後に GET 要求を発行した場合、応答には移行前の状態が反映されている可能性があります。

**修正方法:**

1. POST 自体からの `201 Created` 応答は、操作が成功したことを示す権限のある確認です。 後続の GET ではなく、その応答に依存します。
2. 状態をポーリングする必要がある場合は、最大 1 時間待ってから GET 要求を再試行してください。

### カスタム属性の同期が失敗する

**HTTP 状態:** 400 BadRequest エラー コード: `AADB2C99089`**エラーメッセージ：**`Failed to sync custom attributes from B2C to the hybrid tenant's external ID context. The following attributes couldn't be transferred: {failedAttributeList}. Please retry the operation or contact support.`**影響を受けるエンドポイント:** POST (ハイブリッド モードを有効にする — 属性同期フェーズ)

ハイブリッド モードを有効にする POST 中に、サービスはすべての B2C カスタム属性を外部 ID テナントに同期しようとします。 カスタム属性に null または空の説明 ( `AdminHelpText` フィールドから取得) がある場合、その属性は失敗したリストに追加され、エラー `HybridTenantMigrationFailedAttributes` が返されます。

多くの B2C カスタム属性は説明なしで作成されるため、この問題は多数の属性にサイレントで影響を与える可能性があります。

空でない `description` フィールドを必要とするカスタム属性には、 `AdminHelpText` が null または空の属性が含まれます。 一般的な例には、プログラムまたは以前の B2C ポータル エクスペリエンスを使用して作成された属性が含まれます。

**修正方法:**

1. すべてのカスタム属性を一覧表示します。

    ```http
    GET https://graph.microsoft.com/v1.0/identity/userFlowAttributes
    ```
2. `description` が null または空である属性ごとに、空でない説明でパッチを適用します。

    ```http
    PATCH https://graph.microsoft.com/v1.0/identity/userFlowAttributes/{id}
    Content-Type: application/json
    
    {
      "description": "<a meaningful, non-empty description>"
    }
    ```
3. すべての属性が更新されたら、POST を再試行してハイブリッド モードを有効にします。

ヒント

Microsoft Graph PowerShell または Graph Explorer を使用して、移行を開始する前に属性の一括パッチを効率的に適用します。

### サブスクリプションがリンクされていない

**HTTP 状態:** 400 BadRequest エラー コード: `NoResourceProviderDataFound`**エラーメッセージ：**`Your tenant '{tenantId}' is not linked to a valid subscription.`**影響を受けるエンドポイント:** POST (ハイブリッド モードを有効にする)

リソース プロバイダーのデータ参照で null が返されたか、 `SubscriptionTenantId`がありません。 B2C テナントは、Azureサブスクリプションにリンクされていません。

**修正方法:**

Azure ポータルを使用して、B2C テナントが有効なAzure サブスクリプションにリンクされていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/troubleshooting-known-issues"} -->
## 外部テナントの既知の問題 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/troubleshooting-known-issues
- Service: entra-external-id / external
- Article date: 2025-07-07
- Summary: 外部テナントの既知の問題について説明します。

この記事では、外部向けアプリに Microsoft Entra 外部 ID を使用するときに発生する可能性がある既知の問題について説明し、これらの問題を解決するためのヘルプを提供します。

### テナントの作成と管理

#### 管理者の電子メールを使用してローカルの顧客アカウントを作成すると、テナントを管理できなくなります

外部テナントを作成した管理者で、管理者アカウントと同じメール アドレスを使用して同じテナントにローカル 顧客アカウントを作成する場合、管理者特権でテナントに直接サインインすることはできません。

**原因**: テナント管理者の電子メールを使用して、セルフサービス サインアップを使用して顧客アカウントを作成すると、同じ電子メール アドレスを持つが、顧客レベルの特権を持つ 2 番目のユーザーが作成されます。 `https://entra.microsoft.com/<tenantID>` または `<tenantName>.onmicrosoft.com`を使用してテナントにサインインすると、最小特権アカウントが優先され、管理者ではなく顧客としてサインインされます。テナントを管理するための特権が不十分です。

**回避策の**: 次のいずれかのアクションを実行します。

- ローカルの顧客アカウントを作成するときは、テナントを作成した管理者が使用するメール アドレスとは異なるメール アドレスを使用します。
- 管理者と同じメール アドレスを持つ顧客アカウントを既に作成している場合は、管理センターからサインアウトしてから、`https://entra.microsoft.com` または `https://entra.microsoft.com/<tenantID>` の代わりに `<tenantName>.onmicrosoft.com` を使用して正しい管理者アカウントでサインインします。

### Web API のトークン のバージョン

#### Web API の実行時にエラーが発生する

(Web API サンプルのアプリ作成スクリプトを使用せずに) 外部テナントに独自の Web API を作成し、それを実行してアクセス トークンを送信すると、ログ記録が有効になり、次のエラーが表示されます。

`IDX20804: Unable to retrieve document from: https://<tenant>.ciamlogin.com/common/discovery/keys`

**原因**: このエラーは、受け入れられたアクセス トークンのバージョンを 2 に設定していない場合に発生します。

**回避策の**: 次の操作を行ってください。

1. アプリケーションのアプリ登録に移動します。
2. マニフェストの編集を選択します。
3. **accessTokenAcceptedVersion** プロパティを null から **2**に変更します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/tutorial-configure-external-id-web-app-firewall"} -->
## Azure Web アプリケーション ファイアウォールを使用して Microsoft Entra 外部 ID を構成する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-configure-external-id-web-app-firewall
- Service: entra-external-id / external
- Article date: 2025-01-09
- Summary: Azure Web アプリケーション ファイアウォールを使用して Microsoft Entra 外部 ID を構成する方法について説明します。

この記事では、Microsoft Entra 外部 ID テナントの [Azure Web Application Firewall](https://learn.microsoft.com/ja-jp/azure/web-application-firewall/ag/ag-overview) (WAF) サービスを有効にする方法について説明します。 Azure WAF は、クロスサイト スクリプティング、分散型サービス拒否 (DDoS) 攻撃、悪意のあるボット アクティビティなどの一般的な悪用や脆弱性から Web アプリケーションを保護します。

### 前提 条件

- **Azure サブスクリプション**。 お持ちでない場合 [、Azure アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を無料で入手できます。
- **Microsoft Entra 外部 ID テナント**。 テナント内のユーザー フロー (ID プロバイダー (IdP) とも呼ばれます) でユーザー資格情報を検証する承認サーバー。 [外部テナントを作成する方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)を参照。
- **Azure Front Door Premium**。 Azure Front Doorでは、セキュリティの最適化と WAF マネージド ルール セットへのアクセスを使用して、Microsoft Entra 外部 ID テナントのカスタム ドメインを有効にします。
- Azure Web Application Firewall （Premium SKU が必要）. [Azure WAF](https://azure.microsoft.com/services/web-application-firewall/) は、承認サーバーが受信するトラフィックを管理します。
- **カスタムドメイン**。 Azure Front Door のカスタム ドメイン機能と共に使用します。 外部テナントでアプリのカスタム URL ドメインを有効にする 方法について説明します。

重要

カスタム ドメインを構成したら、使用する前に[カスタム ドメインのテスト](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain#test-your-custom-url-domains)を行います。

### Azure Web アプリケーション ファイアウォールを有効にする

WAF で保護を有効にするには、WAF ポリシーを構成し、それを Azure Front Door Premium に関連付けます。 Microsoft では、セキュリティのために Azure Front Door Premium を最適化し、クロスサイト スクリプティングや JavaScript の悪用などの一般的な脆弱性から保護するために WAF によって提供されるルール セットを管理します。 さらに、Azure WAF には、悪意のあるボット アクティビティから保護し、アプリケーションにレイヤー 7 の DDoS 保護を提供するルール セットが用意されています。

#### Azure Web アプリケーション ファイアウォール ポリシーを作成する

WAF ポリシーを作成するには、次の手順に従います。

1. [Azure portal](https://portal.azure.com)にサインインします。
2. [**Azure サービス**] で、[**リソースの作成**] を選択します。
3. 検索バーに「Azure WAF入力し、Microsoftから Azure Service Web Application Firewall (WAF) 選択します。
4. **[作成]**を選択します。
5. **[WAF ポリシーの作成]** に移動します。
6. **[基本]**を選択します。
    - **[次に関するポリシー]** で、**[グローバル WAF (Front Door)]** を選択します。
    - **[Front Door SKU]** で、[Premium] SKU を選択します。
    - **[サブスクリプション]** で、Front Door のサブスクリプション名を選択します。
    - **リソース グループの**で、Front Door リソース グループ名を選択します。
    - **ポリシー名**には、WAF ポリシーの一意の名前を入力します。
    - **[ポリシーの状態]** で、**[有効]** を選択します。
    - **ポリシーモード**を選択し、**検出**を選びます。
7. **[WAF ポリシーの作成]**&gt;**[関連付け]** の順に移動します。
8. **[+ Front Door プロファイルの関連付け]** を選択します。
9. **Front Door**: Microsoft Entra 外部 ID カスタム ドメインに関連付けた Front Door 名を選択します。
10. **ドメイン**: WAF ポリシーを関連付ける Microsoft Entra 外部 ID カスタム ドメインを選択します。
11. [**を選択し**を追加] します。
12. **[確認と作成]** を選択します。
13. **[作成]**を選択します。

### 既定の規則セットを構成する

新しい WAF ポリシーを作成すると、Azure Front Door は、[Azure で管理される既定の規則セット](https://learn.microsoft.com/ja-jp/azure/web-application-firewall/afds/waf-front-door-drs) (DRS) の最新バージョンを使用して自動的にデプロイします。 このルール セットは、Web アプリケーションを一般的な脆弱性や悪用から保護します。 Azure で管理される規則セットは、一般的なセキュリティの脅威から保護します。 Azure は、新しい攻撃シグネチャから保護するために、必要に応じてこれらの規則セットを管理および更新します。 DRS には、対象範囲の拡大、特定の脆弱性の修正プログラム、偽陽性の削減を実現する [Microsoft Threat Intelligence Collection ルール](https://learn.microsoft.com/ja-jp/azure/web-application-firewall/afds/waf-front-door-drs#microsoft-threat-intelligence-collection-rules) が含まれています。

### ボット マネージャールールセットを構成する

既定では、Azure Front Door WAF は、最新バージョンの Azure マネージド [ボット マネージャー 規則セット](https://learn.microsoft.com/ja-jp/azure/web-application-firewall/afds/afds-overview#bot-protection-rule-set)デプロイされます。 このルールセットは、ボットトラフィックを良いボット、悪いボット、不明なボットに分類します。 WAF プラットフォームは、このルール セットの背後にあるボット署名を管理し、動的に更新します。

### レート制限の構成

Azure Front Door の レート制限を使用すると、ソケット IP アドレスからの異常に高いトラフィックを検出してブロックできます。 Azure Front Door で Azure WAF を使用して、一部の種類のサービス拒否攻撃を軽減します。 レート制限により、クライアントが誤って構成され、短時間で大量の要求が送信されるのを防げます。 WAFでレート制限 を手動で構成するには、カスタム規則を使用する必要があります。

### 検出モードと防止モードを構成する

WAF ポリシーを作成すると、Azure は **検出モード**でポリシーを開始します。 トラフィックの WAF を調整する際は、WAF ポリシーを **検出モード** のままにします。 **検出モード**では、WAF は要求をブロックしません。 代わりに、[ログを有効にした](https://learn.microsoft.com/ja-jp/azure/web-application-firewall/afds/waf-front-door-monitor#logs-and-diagnostics)後、WAF により、WAF ルールと一致する要求がログされます。

ログを有効にし、WAF が要求トラフィックを受信したら、ログを調べて、[WAF を調整](https://learn.microsoft.com/ja-jp/azure/web-application-firewall/afds/waf-front-door-tuning)します。

次のクエリは、WAF ポリシーの例が過去 24 時間以内にブロックした要求を示しています。 詳細には、ルール名、要求データ、ポリシーが実行したアクション、ポリシー モードが含まれます。

```kusto
AzureDiagnostics
| where TimeGenerated >= ago(24h)
| where Category == "FrontdoorWebApplicationFirewallLog"
| where action_s == "Block"
| project RuleID=ruleName_s, DetailMsg=details_msg_s, Action=action_s, Mode=policyMode_s, DetailData=details_data_s
```

| RuleID | 詳細メッセージ | アクション | モード | 詳細データ |
| --- | --- | --- | --- | --- |
| DefaultRuleSet-1.0-SQLI-942430 | 制限付き SQL 文字の異常検出 (引数): 特殊文字の数を超えました (12) | ブロック | 検出 | 一致したデータ: CfDJ8KQ8bY6D |

WAF ログを確認して、WAF の規則によって誤検知が発生したかどうかを判断します。 次に、除外を使用して、WAF の誤検知を軽減します。 Web アプリケーション ファイアウォールの除外リストを構成します。 Azure Front Door の除外リストを使用して Web アプリケーション ファイアウォールを構成します。

ログ記録を設定し、WAF がトラフィックを受信したら、ボット のトラフィックを処理する際のボット マネージャー ルールの有効性を評価できます。 次のクエリは、ボット マネージャー ルール セットの例で実行されたアクションをボットの種類別に分類して示しています。 **検出モードでは**、WAF はボットトラフィックアクションのみをログに記録します。 **防止モード**に切り替えると、WAF は不要なボット トラフィックをアクティブにブロックし始めます。

```kusto
AzureDiagnostics
| where Category == "FrontDoorWebApplicationFirewallLog"
| where action_s in ("Log", "Allow", "Block", "JSChallenge", "Redirect") and ruleName_s contains "BotManager"
| extend RuleGroup = extract("Microsoft_BotManagerRuleSet-[\\d\\.]+-(.*?)-Bot\\d+", 1, ruleName_s)
| extend RuleGroupAction = strcat(RuleGroup, " - ", action_s)
| summarize Hits = count() by RuleGroupAction, bin(TimeGenerated, 30m)
| project TimeGenerated, RuleGroupAction, Hits
| render columnchart kind=stacked
```

### 検出モードから防止モードに切り替える

要求されたトラフィックのアクティビティを監視するには、Azure portal の WAF ポリシーの [**の概要]** ページから **[防止モードに切り替える]** を選択します。 この選択により、モードが **検出モード** から **防止モード**に変更されます。 WAF は、WAF ポリシーの規則に一致する要求をブロックし、WAF ログに記録します。 WAF は、要求が 1 つ以上のルールと一致し、結果をログに記録するときに、所定のアクションを実行します。 既定では、DRS は異常スコアリング モードに設定されます。WAF は、異常スコアのしきい値を満たしていない限り、要求に対してアクションを実行しません。

**検出モード**に戻すには、[**概要]** ページから [**検出モードに切り替える]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/tutorial-third-party-account-take-over-protection-native-api"} -->
## サードパーティのアカウント引き継ぎ保護をネイティブ認証 API と統合する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-third-party-account-take-over-protection-native-api
- Service: entra-external-id / external
- Article date: 2026-02-24
- Summary: Web アプリケーション ファイアウォール (WAF) を使用して、サード パーティのアカウント引き継ぎ (ATO) 保護プロバイダーと Microsoft Entra 外部 ID のネイティブ API 認証を統合する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、サード パーティのアカウント引き継ぎ (ATO) 保護プロバイダーと Microsoft Entra 外部 ID のネイティブ API 認証を統合する方法について説明します。 Web アプリケーション ファイアウォール (WAF) を使用して認証要求をインターセプトすることで、サインイン時にリスクベースの MFA チャレンジを実装して、自動攻撃やアカウントの侵害から保護することができます。

Important

ネイティブ認証に対するサード パーティの ATO 保護は、ネイティブ認証 API エンドポイントの前に配置された WAF を通じてサポートされます。 これは、ネイティブ API フローでサポートされているアーキテクチャです。 Microsoft Entra 外部 IDネイティブ認証用の `RiskPreventionProvider` スタイルの構成は公開されません。リスク評価は、WAF を介してサードパーティ プロバイダーによって実行され、Microsoft Entra条件付きアクセス認証コンテキストを通じて結果の MFA 要件が適用されます。 ブラウザー委任 (Web ホスト型) サインイン フローについては、このチュートリアルでは説明しません。

注

このチュートリアルでは、認証フローを実行するために生の HTTP 要求を手動で行うものとします。 可能な場合は、Microsoft が構築し、サポートされている認証 SDK を使用します。 「 [チュートリアル: ネイティブ認証用に Android モバイル アプリを準備する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-prepare-android-app) 」と [「チュートリアル: ネイティブ認証用に iOS/macOS モバイル アプリを準備する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-prepare-ios-macos-app)」を参照してください。

### 前提条件

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。 外部テナントをまだ作成していない場合は、ここで作成してください。
- 次の構成で Microsoft Entra 管理センターに [登録されているアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。
    - 記録されたアプリケーション (クライアント) ID とディレクトリ (テナント) ID。
    - [管理者の同意が付与されました](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)。
    - [パブリック クライアントとネイティブ認証フローが有効になっています](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication#how-to-enable-native-authentication)。
- Microsoft Entra 管理センターで作成され、[アプリケーションに関連付けられている](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)[ユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)。
- サインイン フロー テストのために [テナントに登録されている](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts#create-a-customer-account) テスト 顧客ユーザー。
- 外部テナントの [認証機能拡張管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-extensibility-administrator) または [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールを少なくとも持つアカウント。
- 外部テナントに関連付けられている [カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain) 。
- サードパーティの ATO 保護プロバイダー アカウント (このチュートリアルでは、LexisNexis Risk Solutions を例として使用) で、次の構成値を使用します。
    - セッション クエリ API 資格情報。
    - API アクセスを更新します。
    - SDK 統合の詳細。
- ドメイン管理者特権を持つ WAF プラットフォーム アカウント (このチュートリアルでは Cloudflare を使用します)。

### ATO 保護のしくみ

ユーザーがネイティブ認証を使用してサインインしようとすると、認証要求は /token エンドポイントをインターセプトする Web アプリケーション ファイアウォール (WAF) を通過します。 WAF は、リスク評価 API を使用して、サードパーティの ATO プロバイダーとの要求を評価します。 デバイスのフィンガープリント、行動分析、またはその他のリスクシグナルに基づいて要求に疑わしいフラグが設定されている場合、WAF は MFA を必要とする認証コンテキストを使用して条件付きアクセス ポリシーをトリガーします。 認証を続行する前に、ユーザーが MFA チャレンジを完了する必要があります。

このアプローチでは、ブラウザーベースのリダイレクトを必要とせずに、ネイティブ サインイン フロー中にリスクベースの保護を適用し、アカウント引き継ぎ攻撃から保護しながらネイティブ アプリのユーザー エクスペリエンスを維持することができます。

### アーキテクチャ コンポーネント

この統合には、リスクベースの認証を提供するために連携するいくつかの重要なコンポーネントが含まれます。

- **外部テナント:** 外部 ID と顧客アクセスを管理するための専用の Microsoft Entra ID インスタンス。
- **ネイティブ アプリケーション:** Microsoft Entra 外部 ID (ネイティブ認証) を使用してユーザーをサインアップしてサインインするモバイルまたはデスクトップ アプリケーション。
- **ネイティブ API:** モバイル アプリとデスクトップ アプリがサインアップ、サインイン、セルフサービス パスワード リセット (SSPR) を実行できるようにするサービス エンドポイントは、ブラウザーリダイレクトなしでアプリ内でフローします。
- **Web アプリケーション ファイアウォール (WAF):** 受信および送信 HTTP トラフィックを検査し、認証要求をインターセプトし、サードパーティ プロバイダーと連携してリスク評価を行うファイアウォール。
- **サード パーティの ATO プロバイダー:** ボットの検出、デバイスのフィンガープリント、リスク評価サービスを提供するサード パーティのプロバイダー。
- **条件付きアクセス (CA) ポリシー:** スコープ内のユーザー、アプリ、条件、および認証コンテキストによってトリガーされるアクセス権を付与するために必要なコントロールを指定するポリシー。
- **認証コンテキスト:** アプリ レベルではなく、特定のアクションまたはシナリオに詳細なポリシーを適用できる条件付きアクセス機能。

[Image: ネイティブ アプリ、WAF、サード パーティ プロバイダー、MFA の手順を示すリスクベースの認証フローの図。]

### コンフィギュレーションの手順

1. 外部テナントのサインイン フローを作成します。
2. WAF 構成を作成します。
3. テナントの MFA を有効にします。
4. 条件付きアクセス認証コンテキストを設定します。
5. 認証コンテキストを使用して条件付きアクセス ポリシーを作成します。
6. サインイン フロー中に特定の API 要求をインターセプトするように WAF レイヤーを更新します。
7. ネイティブ アプリのサインイン API 呼び出しフローを更新して、MFA を導入します。

### 外部テナントの基本的なサインイン フローを構成する

1. 外部テナントをまだ作成していない場合は、[ここで作成してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
2. まだ登録していない場合は、 [Microsoft Entra 管理センターにアプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 次のことを確認します。

    - 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。
    - アプリケーション[に管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)します。
    - [パブリック クライアントとネイティブ認証フローを有効にします](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication#how-to-enable-native-authentication)。
3. まだ作成していない場合は、 [Microsoft Entra 管理センターでユーザー フローを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers#to-add-a-new-user-flow)。 ユーザー フローを作成するときは、必要に応じて構成したユーザー属性を書き留めます。 これらの属性は、Microsoft Entra がアプリの提出を期待する属性です。
4. [アプリの登録をユーザー フローに関連付けます](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)。
5. サインイン フローの場合は、テスト [に使用する顧客ユーザーを登録](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts#create-a-customer-account) します。 または、サインアップ フローを実行した後に、このテスト ユーザーを取得できます。

### WAF 構成を作成する

リスク評価のために認証要求をインターセプトするには、WAF 構成が必要です。 このチュートリアルでは、Cloudflare を例として使用しますが、要求のインターセプトとカスタム ロジックの実行をサポートする任意の WAF を使用できます。

Important

WAF を構成する前に、カスタム ドメインを外部テナントに関連付ける必要があります。 カスタム ドメインがないと、WAF は認証要求をインターセプトできません。

Cloudflare WAF のセットアップ手順の詳細については、「 [Microsoft Entra 外部 ID を使用して Cloudflare WAF を構成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-waf-integration)する」を参照してください。

### テナントの多要素認証 (MFA) を有効にする

疑わしいサインイン試行が検出されたときに MFA チャレンジを適用するには、まずテナントに対して MFA を有効にします。 このチュートリアルでは、危険なサインイン時にユーザーの第 2 要素認証方法として電子メール OTP を使用します。

詳細なセットアップ手順については、「 [Microsoft Entra 多要素認証を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)」を参照してください。

注

現時点では、ネイティブ認証のリスクベースの MFA は、"パスワード付き電子メール" サインイン フローにのみ適用できます。 ユーザーは、強力な認証方法として電子メールを構成する必要があります。

### 条件付きアクセス認証コンテキストを設定する

[条件付きアクセス認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-guide-conditional-access-authentication-context) (認証コンテキスト) を使用すると、アプリケーション レベルだけでなく、特定のアクションまたはデータの機密性に基づいて、詳細なレベルで条件付きアクセス ポリシーを適用できます。 このシナリオでは、認証コンテキストを使用して、すべてのサインイン試行に MFA を要求するのではなく、サインイン試行が危険であると WAF が判断した場合にのみ MFA をトリガーします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
2. [ **条件付きアクセス** ] セクションで、[ **認証コンテキスト**] を選択し、[ **新しい認証コンテキスト**] を選択します。

    [Image: [条件付きアクセス認証コンテキスト] ページを示すスクリーンショット。]

    [Image: 新しい認証コンテキスト作成フォームを示すスクリーンショット。]
3. **名前** (必須) と**説明** (省略可能) を追加します。
4. 認証コンテキストの **ID を** 選択します。 ID の範囲は c1 から c99 です。 この例では、 **c3** を選択します。
5. **を選択して**を作成します。

ヒント

認証コンテキスト ID は、ユーザーが特定の認証コンテキストを使用していることを示すために/token エンドポイントで使用されます。 WAF ワーカーの構成時に必要に応じて、選択した ID (c3 など) を書き留めておきます。

### 認証コンテキストを使用して条件付きアクセス ポリシーを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動し、[ **+ 新しいポリシー**] を選択します。

    [Image: 新しいポリシー オプションが表示された [条件付きアクセス ポリシー] ページを示すスクリーンショット。]
3. ポリシーの **名前** を入力し、ポリシーが影響を受ける特定の **ユーザーまたはユーザー グループ** (またはすべてのユーザー) を選択します。 ネイティブ認証 API で電子メール MFA を適用する場合は、強力な認証方法として電子メールを使用する必要があります。

    [Image: ポリシー名とユーザー割り当てのオプションを示すスクリーンショット。]

    [Image: 条件付きアクセス ポリシーのユーザー選択を示すスクリーンショット。]
4. **[ターゲット リソース]** で、ドロップダウンで **[認証コンテキスト**] を選択し、前に作成した認証コンテキストを選択します。
5. [ **許可]** で、ユーザーに適用するアクションを選択します (多要素認証が必要な場合など)。 [ **選択] を選択**し、[ **ポリシーの有効化]** を **[オン**] に設定し、[ **作成**] を選択します。

    [Image: 許可コントロールとポリシー有効化設定を示すスクリーンショット。]

この時点で、選択したユーザーがテナント アプリのいずれかにサインインするときに MFA を要求するように条件付きアクセス ポリシーが構成されています。

### リスク評価用にWAFワーカーを構成する

このセクションでは、/token 要求をインターセプトし、サードパーティの ATO プロバイダーでリスク評価を実行するように WAF を構成する方法について説明します。

#### サインイン フロー中に特定の API 要求をインターセプトするように WAF レイヤーを更新する

このチュートリアルでは、Cloudflare WAF を使用します。 Cloudflare WAF のセットアップ手順については、このチュートリアルの 「WAF 構成の作成 」セクションを参照してください。

1. 少なくともドメイン**管理者**特権を持つ外部テナントに関連付けられている外部ドメイン (WAF 構成手順で説明) の **Cloudflare アカウント**にサインインします。
2. **[Workers Routes**] に移動し、[**アプリケーションの作成**] を選択します。

    [Image: Cloudflare Workers Routes ページを示すスクリーンショット。]

    [Image: Cloudflare の [アプリケーションの作成] オプションを示すスクリーンショット。]
3. [ **Hello World から開始] を選択します**。

    [Image: GitHub、GitLab、Hello World、テンプレート、静的ファイルのアップロード ボタンを示す Cloudflare アプリケーション作成オプションのスクリーンショット。]
4. ワーカーに名前を付け、[デプロイ] を選択 **します**。 [Image: Hello World セットアップの worker 名、コード プレビュー、デプロイ ボタンを示す Cloudflare Worker のデプロイ画面のスクリーンショット。]
5. worker がデプロイされたら、[ **設定]** タブを選択します。[ **+ドメイン** と **ルート**に追加] を選択します。

    [Image: [ドメイン] と [ルート] セクションの [設定] タブと、ルートを追加するための [+追加] リンクが表示されているスクリーンショット。]
6. **ルーティング** を選択します。 [Image: Worker エンドポイントをマッピングするためのカスタム ドメインとルート オプションを含む [ドメイン] と [ルート] 設定のスクリーンショット。]
7. **[ゾーン**] からドメインを選択します。 **[ルート**] フィールドに次の値を追加します。

    `*<custom_domain>/<external_tenant_id(guid)>/oauth2/v2.0/token*`

    失敗モードの場合は、[ **Fail Closed]\(失敗終了\)** を選択します。

    [Image: ゾーンと障害モードの設定を含むルート構成を示すスクリーンショット。]
8. [ **ルートの追加] を選択します**。

WAF のセットアップが正しく構成されている場合、外部テナント /トークン エンドポイントに対するすべての要求がワーカーによってインターセプトされます。 MFA でチャレンジする要求を決定し、サードパーティのリスク プロバイダーでリスク評価を行う worker ロジックを構成します。

#### リスク評価用のワーカーロジックを構成する

ワーカー ロジックを次のように構成します。

1. 認証要求 (デバイスのフィンガープリント、IP アドレス、動作データ) から関連情報を抽出します。
2. このデータをサードパーティの ATO プロバイダーのリスク評価 API に送信します。
3. プロバイダーによって返されるリスク スコアを評価します。
4. リスク スコアがしきい値を超えた場合は、MFA をトリガーする認証コンテキストを含むように要求を変更します。
5. 要求を Microsoft Entra /token エンドポイントに転送します。

ヒント

WAF ベースの ATO 保護を使用した完全な Android SDK の実装例については、 [LexisNexis Risk Solutions ATO 保護のサンプル アプリ](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-android-sample/tree/feature/integrate-lnr-signin-ato-protection)を参照してください。

#### サード パーティプロバイダーの統合

このチュートリアルでは、サードパーティの ATO プロバイダーとして [LexisNexis Risk Solutions](https://risk.lexisnexis.com/) を使用します。 LexisNexis によって提供されるセッション クエリ API は、リスク評価に使用されます。 次の LexisNexis ドキュメントを参照してください。

- [セッション クエリ API](https://portal.threatmetrix.com/kb-en/Implementation_Guides/APIs/Session_Query_API.htm)
- [Update API](https://portal.threatmetrix.com/kb-en/Implementation_Guides/APIs/update_api.htm)
- SDK ドキュメント: [ThreatMetrix SDK の概要と FAQ](https://portal.threatmetrix.com/kb-en/Implementation_Guides/ThreatMetrix%20SDK/introduction_to_threatmetrix_sdk_and_faq.htm)

### MFA をサポートするようにネイティブ アプリサインイン API 呼び出しフローを更新する

ネイティブ API エンドポイントを使用した標準サインイン フローについては、 [ネイティブ認証 API リファレンス ドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api?tabs=emailOtp#api-reference-for-sign-in-flow)。 このフローでは、既定では MFA は呼び出されません。 このセクションでは、リスクベースの MFA をサポートするようにネイティブ アプリを更新する方法について説明します。

このチュートリアルでは、前の手順で構成した認証コンテキストを使用して、リスクベースの MFA を呼び出します。

注

現時点では、リスクベースの MFA は"パスワード付き電子メール" フローにのみ適用できます。

次のフローでは、レイヤーとして WAF を使用して、/token 呼び出しのリスクを評価します。

#### MFA を開始するための論理フロー

1. MFA フローに対して定義された認証コンテキストを使用して `/token` を呼び出します。
2. `/initiate` エンドポイントは、第 1 要素認証フローの状態オブジェクトとして`CredentialToken`を引き続き使用します。
3. `/challenge` エンドポイントは、第 1 要素認証フローの状態オブジェクトとして`CredentialToken`を引き続き使用します。
4. `/token`では、WAF レイヤーでリスクが評価されます。 WAF レイヤーがチャレンジを使用して要求を確認することを決定した場合は、MFA フロー用に構成された`/token` (この例では "c3") を使用して、新しい`AuthContext`呼び出しが行われます。
5. `/introspect` エンドポイントは、`CredentialVerificationInputState`からメソッドを読み取り、ユーザーに返します。
6. `/challenge` エンドポイントは、`CredentialVerificationInputState`から強力な認証メソッドを読み取り、要求のチャレンジタイプと比較することで、認証チャレンジに使用される強力な認証メソッドを選択します。 メソッドが選択され、チャレンジ操作のために EC UCV に渡されると、選択したメソッドの ID と型が `CredentialVerificationIntermediateState`に書き込まれます。
7. `/token` エンドポイントは、`CredentialVerificationIntermediateState`から強力な認証方法 ID と型を読み取り、要求からの oob 値と共に EC UCV 検証操作に渡します。 EC UCV が正常に戻ると、 `/token` ハンドラーはポップし、 `CredentialVerificationIntermediateState` を `CredentialToken`にマージします。 これにより、 `StsRequest` の FlowToken が MFA の詳細で更新されます。 `StsRequest` はパイプラインを実行して認証フローを完了します。

ヒント

トリガーされたときに MFA フローを処理するには、ネイティブ アプリを準備する必要があります。 アプリが `/introspect` エンドポイントを呼び出し、電子メール OTP の `/challenge` を処理し、最終的な `/token` 呼び出しで OTP 値を送信できることを確認します。

#### エンド ツー エンド フローをテストする

構成が完了したら、統合を検証します。

1. リスクの低いコンテキスト (既知のデバイスや IP など) からテスト顧客ユーザーとサインインします。 サインインは MFA チャレンジなしで完了する必要があります。
2. サードパーティの ATO プロバイダーが危険と分類するコンテキスト (プロバイダーのテスト ガイダンスに従って、新しいデバイス、匿名化された IP、シミュレートされたボット トラフィックなど) からサインインを繰り返します。 WAF は条件付きアクセス認証コンテキストをトリガーする必要があり、アプリはトークンを発行する前に電子メール OTP MFA を必要とするチャレンジを受け取る必要があります。
3. MFA 後の `/token` 応答に予想されるアクセス トークンと ID トークンが含まれていること、およびテナントとプロバイダー ダッシュボードのサインイン テレメトリにリスクの決定が反映されていることを確認します。

MFA が想定どおりにトリガーされない場合は、ユーザーが強力な認証方法として構成された電子メールを持っていることを確認し、条件付きアクセス ポリシーが適切な認証コンテキスト (たとえば、 `c3`) をターゲットにし、WAF ワーカーがプロバイダーのリスク API を呼び出し、 `/token` 呼び出しで認証コンテキストを転送していることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/tutorial-third-party-bot-protection-native-api-sign-up"} -->
## サードパーティのボット保護をネイティブ API サインアップと統合する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-third-party-bot-protection-native-api-sign-up
- Service: entra-external-id / external
- Article date: 2026-02-24
- Summary: Web アプリケーション ファイアウォールを使用して、サードパーティのボット保護プロバイダーと Microsoft Entra 外部 ID のネイティブ API サインアップ フローを統合する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Microsoft Entra 外部 ID でサードパーティのボット保護プロバイダーとネイティブ API サインアップ フローを統合する方法について説明します。 Web アプリケーション ファイアウォール (WAF) を使用してサインアップ要求をインターセプトすることで、ユーザー登録時にリスクベースのチャレンジ メカニズムを実装して、ボットの自動攻撃や偽のアカウント作成から保護することができます。

注

この統合は、ネイティブ認証 API フローにのみ適用されます。 WAF は、ネイティブ認証 `/start` エンドポイントをインターセプトして、ユーザー登録を続行する前に要求を評価します。 ブラウザー委任 (Web ホスト型) サインアップ フローについては、このチュートリアルでは説明せず、別の統合モデルを使用します。

注

このチュートリアルでは、サインアップ フローを実行するために生の HTTP 要求を手動で行うものとします。 可能な場合は、Microsoft が構築し、サポートされている認証 SDK を使用します。 「 [チュートリアル: ネイティブ認証用に Android モバイル アプリを準備する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-prepare-android-app) 」と [「チュートリアル: ネイティブ認証用に iOS/macOS モバイル アプリを準備する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-prepare-ios-macos-app)」を参照してください。

### 前提条件

- 外部テナント。 外部テナントをまだ作成していない場合は、[ここで作成してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- 次の構成で Microsoft Entra 管理センターに [登録されているアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。
    - 記録されたアプリケーション (クライアント) ID とディレクトリ (テナント) ID。
    - [管理者の同意が付与されました](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)。
    - [パブリック クライアントとネイティブ認証フローが有効になっています](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication#how-to-enable-native-authentication)。
- Microsoft Entra 管理センターで作成され、[アプリケーションに関連付けられている](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)[ユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)。
- 外部テナントに関連付けられている [カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain) 。
- 次の構成値を持つサードパーティのボット保護プロバイダー アカウント (このチュートリアルでは、例として HUMAN Security を使用します)。
    - Enforcer API 資格情報
    - SDK 統合の詳細
- ドメイン管理者特権を持つ WAF プラットフォーム アカウント (このチュートリアルでは Cloudflare を使用します)。

### ボット保護のしくみ

ユーザーがネイティブ認証を使用してサインアップしようとすると、サインアップ要求は/start エンドポイントをインターセプトする Web アプリケーション ファイアウォール (WAF) を通過します。 WAF は、検出 API を使用して、サードパーティのボット保護プロバイダーとの要求を評価します。 デバイスのフィンガープリント、行動分析、またはボット署名に基づいて要求に疑わしいフラグが設定されている場合、WAF は要求をブロックしたり、ユーザーが人間であることを確認するためのチャレンジを提示したりできます。

このアプローチを使用すると、ブラウザーベースのリダイレクトを必要とせずにネイティブ サインアップ フロー中にボット保護を適用でき、自動アカウント作成やボット攻撃から保護しながらネイティブ アプリのユーザー エクスペリエンスを維持できます。

### アーキテクチャ コンポーネント

この統合には、ボット保護を提供するために連携するいくつかの重要なコンポーネントが含まれます。

- **外部テナント:** 外部 ID と顧客アクセスを管理するための専用の Microsoft Entra ID インスタンス。
- **ネイティブ アプリケーション:** Microsoft Entra 外部 ID (ネイティブ認証) を使用してユーザーをサインアップしてサインインするモバイルまたはデスクトップ アプリケーション。
- **ネイティブ API:** モバイル アプリとデスクトップ アプリがサインアップ、サインイン、セルフサービス パスワード リセット (SSPR) を実行できるようにするサービス エンドポイントは、ブラウザーリダイレクトなしでアプリ内でフローします。
- **Web アプリケーション ファイアウォール (WAF):** 受信および送信 HTTP トラフィックを検査し、サインアップ要求をインターセプトし、ボット検出のためにサード パーティプロバイダーと調整するファイアウォール。
- **サードパーティのボット保護プロバイダー:** ボットの検出、デバイスのフィンガープリント、リスク評価サービスを提供し、自動攻撃を識別するサード パーティのプロバイダー。

[Image: ネイティブ アプリ、WAF、サードパーティ プロバイダー、OTP ベースの MFA 手順を示すリスクベースの認証フローの図。]

### コンフィギュレーションの手順

1. 外部テナントのサインアップ フローを作成します。
2. WAF 構成を作成します。
3. サインアップ フロー中に特定の API 要求をインターセプトするように WAF レイヤーを更新します。
4. ネイティブ アプリのサインアップ API 呼び出しフローを更新します。

### 外部テナントのサインアップ フローを作成する

ボット保護を統合する前に、動作中のサインアップ フローが構成されていることを確認します。 前提条件の一部としてこのセットアップを既に完了している場合は、次のセクションに進むことができます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
2. まだ登録していない場合は、 [Microsoft Entra 管理センターにアプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 次のことを確認してください。
    - 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。
    - アプリケーション[に管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)します。
    - [パブリック クライアントとネイティブ認証フローを有効にします](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication#how-to-enable-native-authentication)。
3. まだ作成していない場合は、 [Microsoft Entra 管理センターでユーザー フローを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers#to-add-a-new-user-flow)。 ユーザー フローを作成するときは、必要に応じて構成したユーザー属性を書き留めます。 これらの属性は、Microsoft Entra がアプリの提出を期待する属性です。
4. [アプリの登録をユーザー フローに関連付けます](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)。
5. [顧客ユーザーを登録](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts#create-a-customer-account)してサインアップ フローをテストします。 または、統合を完了した後でテストすることもできます。

### サインアップ要求をインターセプトするように WAF を構成する

ボット検出のサインアップ要求をインターセプトするように WAF を構成します。 このチュートリアルでは、Cloudflare を例として使用しますが、要求のインターセプトとカスタム ロジックの実行をサポートする任意の WAF を使用できます。

Important

WAF を構成する前に、カスタム ドメインを外部テナントに関連付ける必要があります。 カスタム ドメインがないと、WAF はサインアップ要求をインターセプトできません。

Cloudflare WAF のセットアップ手順の詳細については、「 [Microsoft Entra 外部 ID を使用して Cloudflare WAF を構成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-waf-integration)する」を参照してください。

### ボット検出用に WAF worker を構成する

このセクションでは、サインアップ/開始要求をインターセプトし、サード パーティのプロバイダーでボット検出を実行するように WAF を構成します。

#### サインアップ フロー中に特定の API 要求をインターセプトするように WAF レイヤーを更新する

前のセクションで作成した Cloudflare WAF を使用します。

1. 少なくともドメイン**管理者**特権を持つ外部テナントに関連付けられている外部ドメイン (WAF 構成手順で説明) の **Cloudflare アカウント**にサインインします。
2. **Workers Routes** に移動し、[**アプリケーションの作成**] を選択します。

    [Image: Cloudflare の [Workers Routes] ページを示すスクリーンショット。]

    [Image: [アクセス]、[速度]、[キャッシュ]、[ルール]、[エラー ページ] メニュー オプションが表示されている[Workers Routes](ワーカー ルート) が選択されている Cloudflare ダッシュボード サイドバーのスクリーンショット。]
3. [ **Hello World から開始] を選択します**。

    [Image: [Hello World で開始] テンプレート オプションを示すスクリーンショット。]
4. ワーカーに名前を付け、[デプロイ] を選択 **します**。

    [Image: Worker 名、コード プレビュー、デプロイ ボタンを示す Cloudflare Worker のデプロイ画面のスクリーンショット。]
5. worker がデプロイされたら、[ **設定]** タブを選択します。[ **+ドメイン** と **ルート**に追加] を選択します。

    [Image: ワーカー ルートを構成するための [ドメインとルート] セクションと [追加] ボタンを含む [設定] タブのスクリーンショット。]
6. **ルーティング** を選択します。

    [Image: [ルート] オプションを示すスクリーンショット。]
7. **[ゾーン**] からドメインを選択します。 **[ルート**] フィールドに次を追加します。

    `*<custom_domain>/<external_tenant_id(guid)>/*signup/v1.0/start*`

    失敗モードの場合は、[ **Fail Closed]\(失敗終了\)** を選択します。

    [Image: [ゾーン] フィールドと [ルート] フィールドを含むルート構成を示すスクリーンショット。]
8. [ **ルートの追加] を選択します**。

WAF のセットアップが正しく構成されている場合、外部テナント /開始エンドポイントに対するすべての要求がワーカーによってインターセプトされます。

#### ボット検出用の worker ロジックを構成する

ワーカー ロジックは、次のように構成する必要があります。

- サインアップ要求から関連情報 (デバイスのフィンガープリント、IP アドレス、ユーザー エージェント、行動データ) を抽出します。
- このデータをサードパーティのボット保護プロバイダーの検出 API に送信します。
- プロバイダーによって返されるボット検出スコアを評価します。
- 要求がしきい値に基づいてボットとして識別される場合は、要求をブロックするか、チャレンジを提示します。
- 要求が正当と思われる場合は、Microsoft Entra /start エンドポイントに転送します。

##### サード パーティプロバイダーの統合

このチュートリアルでは、サードパーティのボット保護プロバイダーとして [HUMAN Security](https://docs.humansecurity.com/home) を使用します。 HUMAN Security によって提供される Enforcer API は、ボットの検出に使用されます。 次の HUMAN セキュリティ ドキュメントを参照してください。

- [HTTP 要求を強制する |HUMAN ドキュメント](https://docs.humansecurity.com/applications/reference/request)
- SDK ドキュメント: [概要 |HUMAN ドキュメント](https://docs.humansecurity.com/applications/mobile-sdk-intro)

注

ワーカー コードの実装は、選択したボット保護プロバイダーの API と検出しきい値に固有です。 特定のプロバイダーのワーカー ロジックの実装に関するガイダンスについては、Microsoft サポートにお問い合わせください。

### ネイティブ アプリのサインアップ API 呼び出しフローを更新する

ネイティブ API エンドポイントを使用した標準のサインアップ フローについては、 [ネイティブ認証 API リファレンス ドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api?tabs=emailOtp#api-reference-for-sign-up)。 ボット保護を有効にすると、ネイティブ アプリのサインアップ フローが WAF レイヤーと透過的に対話します。

#### ボット保護を使用したサインアップ フロー

WAF は、 `/start` 要求をインターセプトし、ボットからの要求と判断した場合、要求を完全にブロックするか、チャレンジを提示することができます。 フローは次のように動作します。

1. **アプリがサインアップを開始します。** ネイティブ アプリは、 `/start` エンドポイントを呼び出してサインアップ フローを開始します。
2. **WAF は要求をインターセプトします。** WAF は要求を受信し、デバイスと動作のシグナルを抽出します。
3. **ボット検出の評価:** WAF は、分析のためにボット保護プロバイダーにシグナルを送信します。
4. **決定ポイント:**
    - 正当な場合: 要求は Microsoft Entra `/start` エンドポイントに転送されます。
    - 疑わしい場合: 構成に基づいて要求がブロックまたはチャレンジされます。
5. **サインアップは続行されます。** 許可されている場合、標準のサインアップ フローは、 `/challenge`、 `/continue`、およびその他のエンドポイントで続行されます。

ヒント

ボット検出の精度を高めるために、プロバイダーの SDK をネイティブ アプリに統合して、デバイスのフィンガープリントと行動シグナルを収集します。 カスタム ヘッダーまたは要求パラメーターを使用して、これらのシグナルを WAF に渡します。

ヒント

WAF ベースのボット保護を使用した完全な Android SDK の実装例については、 [HUMAN Security ボット保護のサンプル アプリ](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-android-sample/tree/feature/integrate-human-signup-bot-protection)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/verified-id-setup-issuance-presentation"} -->
## Microsoft Entra の検証済み ID を設定する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/verified-id-setup-issuance-presentation
- Service: entra-external-id / external
- Article date: 2026-09-08
- Summary: 外部テナントでMicrosoft Entra Verified IDを構成し、資格情報を発行し、アプリケーションで提示された資格情報を確認します。

外部 ID テナント管理者または開発者の場合は、この記事を使用して、外部テナントでMicrosoft Entra Verified IDを構成します。 開始する前に、クイック セットアップとサンプル アプリケーションの前提条件を確認してください。 完了したら、資格情報の種類を作成し、ユーザーに資格情報を発行し、資格情報を要求して確認するようにアプリケーションを構成できます。

クイック セットアップは、外部テナントでサポートされているセットアップ方法です。 署名キーを構成し、分散型識別子 (DID) を登録し、ドメインの所有権を検証し、既定の検証済み Workplace 資格情報を作成します。

Important

高度なセットアップと Face Check は、外部テナントでは使用できません。 クイック セットアップでは、Microsoftマネージド共有署名キーを使用し、テナントごとに 1 秒あたり 2 つの発行要求と検証要求をサポートし、資格情報の有効性を 6 か月に制限します。

### 前提条件

- [登録済みのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-manage)を持つMicrosoft Entra 外部 ID テナント。 登録済みのカスタム ドメインがない場合、外部テナントに対してサポートされている検証済み ID セットアップ パスはありません。
- 検証済み ID を構成するための [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロール。
- [アプリケーションを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)登録する必要がある場合のアプリケーション管理者ロール。
- 最新バージョンの Microsoft Authenticator を備えたモバイル デバイス。
- .NETサンプル アプリケーション、[Git](https://git-scm.com/downloads)、[Visual Studio Code](https://code.visualstudio.com/Download)、または同様のコード エディター[の場合は、.NET 8.0](https://dotnet.microsoft.com/download/dotnet/8.0)、[および ngrok](https://ngrok.com/) アカウントです。

### 確認済み ID を設定する

クイック セットアップを使用して、Azure Key Vaultの展開や署名キーの管理を行わずに検証済み ID を構成します。

1. 認証ポリシー管理者ロールを[使用してMicrosoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[確認済 ID]** を選択します。
3. 左側のメニューで、[セットアップ] を選択 **します**。
4. **[Get started](https://learn.microsoft.com/ja-jp/entra/external-id/customers/作業を開始する)** を選択します。
5. テナントに複数の登録済みドメインがある場合は、確認済み ID に使用するドメインを選択します。
6. セットアップが完了するまで待ち、既定の職場の資格情報が表示されることを確認します。

クイックセットアップでは、 の形式で DID が作成されます。 クイック セットアップと既定の資格情報の詳細については、「[クイック Microsoft Entra Verified IDセットアップ」](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-tenant-quick)を参照してください。

### アプリケーションを登録する

発行とプレゼンテーションのために検証済み ID 要求サービスを呼び出すアクセス トークンを取得できるように、アプリケーションを登録します。

1. Microsoft Entra 管理センターで、 **Microsoft Entra ID を**選択します。
2. **アプリケーション&gt;** **アプリの登録**&gt;**新規登録**を選択します。
3. アプリケーションの表示名を入力します。
4. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。
5. **登録**を選択します。
6. アプリケーション ページで、[**API のアクセス許可**] を選択します&gt;**アクセス許可を追加します**。
7. **[所属する組織で使用している API]** を選択します。
8. **検証可能な資格情報サービス要求を**検索して選択します。
9. **[アプリケーションのアクセス許可]** を選択し、[**VerifiableCredential.Create.All**] を展開して、[**アクセス許可の追加]** を選択します。
10. **[&lt;テナント名&gt; に管理者の同意を与えます]** を選択します。

これらの責任を分離する必要がある場合は、発行とプレゼンテーションのアクセス許可を個別のアプリケーションに付与できます。 完全な登録手順については、「[Microsoft Entra IDにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-tenant#register-an-application-in-microsoft-entra-id)」を参照してください。

### 資格情報の種類を作成する

アプリケーションが発行するクレームの表示定義とルール定義を含むカスタム資格情報を作成します。

1. **Verified ID** で、**資格情報** を選択します。
2. **[資格情報の追加]** を選択します。
3. [ **カスタム資格情報]** を選択し、[ **次へ**] を選択します。
4. 認証情報名を入力してください。
5. 資格情報の表示定義を追加します。
6. アプリケーションからの入力要求を資格情報の出力要求にマップするルール定義を追加します。
7. **を選択して**を作成します。
8. 作成した資格情報の**資格情報の発行**を選択します。
9. 権限 DID、マニフェスト URL、およびテナント ID を記録します。 これらの値を使用して、発行元アプリケーションを構成します。

表示定義、ルール定義、および資格情報のサンプルについては、「[アプリケーションからの資格情報Microsoft Entra Verified ID発行する」を](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-issuer)参照してください。

### 資格情報を発行する

ユーザーの資格情報を要求し、ユーザーの Microsoft Authenticator ウォレットに追加するように発行アプリケーションを構成します。

1. [.NET サンプル アプリケーション](https://github.com/Azure-Samples/active-directory-verifiable-credentials-dotnet)をダウンロードまたは複製します。
2. 記録したテナント ID、アプリケーション クライアント ID、アプリケーション資格情報、機関 DID、資格情報マニフェスト URL を使用してサンプル アプリケーションを構成します。
3. サンプル アプリケーションを実行し、そのコールバック エンドポイントを使用できるようにします。
4. サンプル アプリケーションで、[資格情報の **取得**] を選択します。
5. Microsoft Authenticatorで QR コードをスキャンします。
6. Microsoft Authenticatorの指示に従って資格情報を追加します。
7. サンプル アプリケーションに戻り、正常に発行されたことが表示されることを確認します。

サンプル構成と完全なテスト手順については、[アプリケーションからの資格情報Microsoft Entra Verified IDの発行に関するページを](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-issuer)参照してください。

### 資格情報のプレゼンテーションを構成する

証明書利用者アプリケーションを構成して資格情報を要求し、コールバックで返された検証済み要求を処理します。

1. Microsoft Entra 管理センターで、**検証済み ID**&gt;**Organization 設定**を選択します。
2. 確認組織のテナント識別子と DID を記録します。
3. 要求するテナント ID、アプリケーション クライアント ID、アプリケーション資格情報、DID 機関、および資格情報の種類を使用して検証ツール アプリケーションを構成します。
4. 資格情報の種類のプレゼンテーション要求を作成します。
5. 認証されたコールバックを受信し、検証済みの要求を使用してアクセスを決定するようにアプリケーションを構成します。

発行者と検証ツールは、同じテナントを使用することも、別の組織とテナントを使用することもできます。 個別の場合は、独自のテナント識別子と DID を使用して検証ツールを構成します。 完全なサンプル構成については、「[Microsoft Entra Verified ID検証ツールの構成」](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-verifier)を参照してください。

### 資格情報を提示して確認する

Microsoft Authenticatorの資格情報を使用してプレゼンテーション要求をテストします。

1. 検証ツール アプリケーションを実行し、そのコールバック エンドポイントを使用できるようにします。
2. 検証ツール アプリケーションで、[資格情報の **確認**] を選択します。
3. Microsoft Authenticatorで QR コードをスキャンします。
4. プレゼンテーション要求を確認し、[許可] を選択 **します**。
5. 検証ツール アプリケーションに戻り、プレゼンテーションを受け取ったかどうかを確認します。
6. アプリケーションが検証済みの要求を使用して、予想されるアクセスの決定を行っていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/visual-studio-code-extension"} -->
## 外部 ID 用 Visual Studio Code 拡張機能 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/visual-studio-code-extension
- Service: entra-external-id / external
- Article date: 2024-09-16
- Summary: Visual Studio Code 用 Microsoft Entra 外部 ID 拡張機能の使用方法について説明します。 提供されているアプリケーション サンプルを使って、開発環境を離れることなく、アプリケーションの外部ユーザー向けにカスタマイズされたブランドのサインイン エクスペリエンスを設定します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

コンシューマーおよびビジネス顧客向けアプリケーションに認証を統合することは、リソースと顧客データをセキュリティで保護するために不可欠です。 Visual Studio Code の Microsoft Entra 外部 ID 拡張機能を使用すると、外部テナントをすばやく作成し、外部ユーザーのサインイン エクスペリエンスを構成し、すべて Visual Studio Code 内で直接外部 ID サンプルを設定できます。 拡張機能のチュートリアルを使用して、アプリケーションの外部ユーザー向けにカスタマイズされブランド化されたサインイン エクスペリエンスを設定し、構成済みのサンプル アプリケーションを使用してプロジェクトをブートストラップする方法について説明します。

[Image: 拡張機能の概要を示すスクリーンショット。]

この拡張機能には、ユーザーのために自動的にアプリケーションのテナントを作成し、準備する基本的なセットアップ機能があります。 また、アプリケーション ID などの値を構成ファイルに自動的に入力してセットアップ プロセスをよりスムーズにすることで、ワークフローを効率化します。

### 拡張機能をインストールする

Microsoft Entra 外部 ID 拡張機能は、Visual Studio Code Marketplace で入手できます。

1. Visual Studio Code をまだインストールしていない場合は、[Visual Studio Code をダウンロード](https://code.visualstudio.com/Download)し、インストール手順を完了します。
2. https://aka.ms/vscodequickstart/marketplace から Visual Studio Code 用の Microsoft Entra 外部 ID 拡張機能をインストールします。

拡張機能がインストールされたら、アクティビティ バーのアイコンを使って拡張機能にアクセスできます。

[Image: チュートリアルの拡張機能を開くオプションを示すスクリーンショット。]

Visual Studio Code の **[ようこそ]** ページから拡張機能を開くこともできます。**[ヘルプ]**&gt;**[ようこそ]** を選び、**[チュートリアル]** の下にある **[Microsoft Entra 外部 ID の使用を開始する]** を選びます。 拡張機能の一覧を展開するには、必要に応じて **[その他]** を選びます。

### 外部 ID のセットアップの使用を開始する

Microsoft Entra 外部 ID 拡張機能を使うと、アプリと外部ユーザーのディレクトリを含む外部構成にテナントが作成されます。 この新しいテナントを既存の Azure サブスクリプションに追加できます。

- [Microsoft Entra 外部 ID の使用を開始する] ようこそページで、次のオプションを選びます。

    - まだ Azure アカウントをお持ちでない場合は、**[無料評価版の設定]** を選びます。
    - 既に Azure アカウントをお持ちの場合は、**[個人用サブスクリプションの使用]** を選びます。

    [Image: [概要] ページのスクリーンショット。]

#### 無料評価版を設定する (プレビュー)

1. **[無料評価版の設定]** を選びます。
2. サインインの確認メッセージで、**[許可]** を選びます。
3. 新しいブラウザー ウィンドウが開きます。 個人用アカウント、Microsoft アカウント (MSA)、または GitHub アカウントを使ってサインインします。 サインインしたら、ブラウザー ウィンドウを閉じます。
4. Visual Studio Code に戻ります。 **[テナントをどこに配置する必要がありますか?]** メニューで、テナント データの場所を選びます。 この選択を後から変更することはできません。
5. テナントの一意の名前を入力します。

    [Image: テナント名フィールドのスクリーンショット。]
6. 拡張機能により、試用版テナントが作成されます。 **[表示]**&gt;**[出力]** ウィンドウを開くと、進行状況を確認できます。 プロセスが完了すると、"**テナントが作成されました**" と表示されます。

#### 自分のサブスクリプションを使う

1. **[自分のサブスクリプションを使用する]** を選びます。
2. アカウントに複数のテナントが関連付けられている場合は、**[ディレクトリの選択]** メニューが表示されます。 使うサブスクリプションに関連付けられたディレクトリ (テナント) を選びます。

    [Image: ディレクトリ フィールドのスクリーンショット。]

    注

    "**使用できるサブスクリプションがありません**" というメッセージが表示された場合は、代わりに無料試用版を設定できます。
3. ブラウザー ページが開き、アカウントにサインインできます。 サインインしたら、Visual Studio Code に戻ります。
4. **[サブスクリプションの追加]** メニューで、お使いのサブスクリプションを選びます。
5. **[リソース グループの選択]** メニューで、リソース グループを選びます。
6. **[テナントをどこに配置する必要がありますか?]** メニューで、テナント データの場所を選びます。 この選択を後から変更することはできません。
7. テナントの名前を入力し、**Enter** キーを押してテナントを作成します。

    [Image: 試用版のテナント名フィールドのスクリーンショット。]

    注

    テナント作成プロセスは、最長で 30 分かかる場合があります。 テナントが作成されたら、Microsoft Entra 管理センターと Azure portal の両方でそれにアクセスできます。

### ユーザーのサインインを設定する

ユーザーが自分のメール アドレスとパスワードまたはワンタイム パスコードを使ってサインインできるようにアプリを構成できます。 会社ロゴの追加、背景色の変更、またはサインイン レイアウトの調整によって、ユーザー エクスペリエンスの外観をデザインすることもできます。 これらの変更は、この新しいテナント内のすべてのアプリの外観に適用されます。

1. **[ユーザーのサインインを設定する]** で、**[サインインとブランドを設定する]** を選びます。

    [Image: サインインとブランド化の設定手順を示すスクリーンショット。]
2. 新しいテナントにサインインするように求められます。 **[許可]** を選び、開いたブラウザー ウィンドウで現在使っているアカウントを選んでサインインします。 Visual Studio Code に戻ります。
3. 上部の **[ユーザーのサインイン方法]** メニューで、ユーザーが使用できるようにするサインイン方法 (**[メール アドレスとパスワード]** または **[メール アドレスとワンタイム パスコード]**) を選びます。

    [Image: サインイン方法を示すスクリーンショット。]
4. **[OK]** を選択します。
5. サインイン ページをブラウザー ウィンドウ内のどこに表示するかを選びます。**[中央揃え]** または **[右揃え]** のいずれかです。

    [Image: サインイン レイアウトの選択肢を示すスクリーンショット。]
6. サインアップ ページの背景色を選びます。

    [Image: 背景色を示すスクリーンショット。]
7. 次に、エクスプローラー ウィンドウが開いたら、会社のロゴを追加できます。 会社のロゴ ファイルを参照し、**[アップロード]** を選びます。

    注

    画像の要件は次のとおりです。

    - 画像サイズ: 245 x 36 ピクセル
    - ファイルの最大サイズ: 50 KB
    - ファイルの種類: 透過 PNG または JPEG
8. メッセージ "**サインイン フローの構成中**" が表示されます。 [出力] ウィンドウで進行状況を確認できます。 構成が完了すると、"**ユーザー フローのセットアップが完了しました**" というメッセージが表示されます。

### サインイン エクスペリエンスを試す

チュートリアルの**サインイン エクスペリエンスの詳細**の手順では、構成したサインイン エクスペリエンスをプレビューできます。

[Image: サインイン エクスペリエンスを試すオプションのスクリーンショット。]

1. **[今すぐ実行]** ボタンを選択します。 新しいブラウザー タブで、ユーザーの作成とサインインに使用できるテナントのサインアップ ページが開きます。
2. **[アカウントをお持ちではない場合、作成できます]** を選択して、テナントに新しいユーザーを作成します。
3. 新しいユーザーのメール アドレスを追加し、**[次へ]** を選択します。

注

試用版の作成に使用したメール アドレスとは異なるメール アドレスを使用します。 テナント管理者の電子メールを使用して [、セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview) または Microsoft Entra 管理センターで [新しい外部ユーザーを追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts#create-a-customer-account) して顧客アカウントを作成する場合、システムは同じメール アドレスを持つ 2 つ目のアカウントを作成します。 この新しいアカウントには顧客レベルの特権があり、競合が発生する可能性があります。

1. 画面のサインアップ手順を完了します。 通常、ユーザーがサインインすると、アプリにリダイレクトされます。 ただし、この手順ではアプリを設定していないため、代わりに JWT.ms にリダイレクトされます。ここで、サインイン プロセス中に発行されたトークンの内容を確認できます。

この手順で作成したユーザーを確認するには、[Microsoft Entra 管理センター](https://entra.microsoft.com/)に移動し、ユーザーの一覧でユーザーを探します。

### サンプル アプリを設定して実行する

この拡張機能には、さまざまなアプリケーションの種類や開発言語で認証がどのように実装されるかを示すコード サンプルがいくつか含まれています。 シングル ページ アプリ (JavaScript、React、Angular) と Web アプリ [Node.js (Express)、ASP.NET Core、Python Django、Python Flask、Java Servlet] のサンプルが含まれています。 拡張機能内からサンプルを選ぶと、拡張機能により、サインイン エクスペリエンスに合わせてアプリケーションが自動的に構成されます。

1. **[サンプル アプリを設定して実行する]** の下にある、**[サンプル アプリを設定する]** ボタンを選びます。

    [Image: サンプル アプリを設定して実行する手順のスクリーンショット。]
2. メニューでダウンロードするアプリの種類を選びます。 もう一度アカウントを選ぶように求められたら、これまで使っていたものと同じアカウントを選びます。

    [Image: アプリの選択のスクリーンショット。]
3. エクスプローラー ウィンドウが開くので、サンプル リポジトリの保存場所を選択できます。 フォルダーを選び、**[ここにリポジトリをダウンロードする]** を選びます。
4. ダウンロードが完了すると、新しい Visual Studio Code プロジェクト ワークスペースが開き、ダウンロードしたアプリ フォルダーがエクスプローラーに表示されます。
5. Visual Studio Code ウィンドウで新しいターミナルを開きます。
6. 上部のメニューで、**[実行]**&gt;**[デバッグなしで実行]** を選びます。 デバッグ コンソールには、起動スクリプトの進行状況が表示されます。 プロジェクトが設定され、ビルド スクリプトが実行されるまでに少し時間がかかります。

拡張機能によってアプリケーションがダウンロードされると、自動的に Microsoft Authentication Library (MSAL) 構成が更新され、新しいテナントに接続され、設定したエクスペリエンスが使われます。 これ以上の構成は必要ありません。プロジェクトがビルドされたらすぐにアプリケーションを実行できます。 たとえば、authConfig ファイルでは、**clientId** がアプリケーション ID に設定され、**authority** が新しいテナントのサブドメインに設定されます。

[Image: auth-config ファイルのスクリーンショット。]

### エクスペリエンスを実行する

セットアップが完了したら、ブラウザーにアプリケーションのローカル ホスト リダイレクト URI を入力して、サインイン エクスペリエンスを試してください。 リダイレクト URL は、アプリケーションの README.md ファイルに記載されています。

### エクスプローラー ビューを使う

エクスプローラー ビューには、**[リソースの管理]**、**[概要]**、**[ヘルプ とフィードバック]** のセクションが表示されます。 エクスプローラー] ビューを開くには、Visual Studio Code のアクティビティ バーに表示されている拡張機能アイコンを選択します。

### リソース管理

**[リソースの管理]** セクションでは、外部テナント、登録済みアプリケーション、ユーザー フロー、および会社のブランド化を表示および管理できます。 プロジェクト リソースを表示するには、左側のパネルの **[リソースの管理]** の下にあるノードを展開します。

[Image: エクスプローラー ビューのスクリーンショット。]

**[リソースの管理]** セクションでは、リソースを選び、Microsoft Entra 管理センターに直接移動して、リソースを管理または構成できます。 たとえば、アプリケーションを右クリックし、**[管理センターで開く]** を選びます。 サインインするように求められ、Microsoft Entra 管理センターが開き、そのアプリケーションのアプリ登録ページが直接表示されます。

[Image: [管理センターで開く] オプションのスクリーンショット。]

### 作業の開始アクション

[作業の開始] セクションでは、無料試用版のドキュメントにアクセスすることや、拡張機能のチュートリアルを開かずにサインイン エクスペリエンスの構成ページやサンプル アプリのダウンロード ページに直接移動することができます。

[Image: チュートリアルを開始するための左側メニュー オプションのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/visual-studio-connected-service"} -->
## Microsoft アイデンティティ プラットフォーム Visual Studio の接続サービスを始める - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/visual-studio-connected-service
- Service: entra-external-id / external
- Article date: 2024-12-23
- Summary: Visual Studio 接続済みサービスを使用して、開発環境から直接 Microsoft Entra ID をアプリケーションに統合する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

リソースと顧客データをセキュリティで保護するには、ID 管理ソリューションを組織および顧客向けのアプリケーションに統合することが不可欠です。 Visual Studio の接続済みサービスを使用すると、Microsoft ID プラットフォームを ASP.NET Web アプリにすばやく統合し、すべて Visual Studio 内でサインイン エクスペリエンスを構成できます。 この記事では、Microsoft Entra ID に対して Visual Studio の接続済みサービス機能を使用する方法について詳しく説明します。

### 前提 条件

- [**ASP.NET と Web 開発ワークロードがインストールされている Visual Studio 2022**](https://visualstudio.microsoft.com/downloads/).
- **Microsoft Entra テナント**(職場または外部)。 お持ちでない場合は、次の方法から選択します。
    - [Microsoft Entra 管理センターで新しいテナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) を作成します。
    - アクティブなサブスクリプションで Azure アカウントを使用します。 まだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- 使用するアカウントには、テナント内のアプリケーションを管理するためのアクセス許可が必要です。 次のいずれかの Microsoft Entra ロールには、必要なアクセス許可があります。
    - アプリケーション管理者
    - アプリケーション開発者
    - クラウド アプリケーション管理者

### プロジェクトを作成して Microsoft ID プラットフォームに接続する

1. Visual Studio で、ASP.NET Model-view-controller (MVC) プロジェクトまたは ASP.NET Web API プロジェクトを作成または開きます。 このクイック スタートでは、"ASP.NET Core Web App (Razor Pages) テンプレートを使用します。
2. プロジェクト名 (例: sample-asp-dotnet-webapp)、プロジェクトを作成する 場所 を入力し、[次へ]選択します。
3. **Framework** の選択で、.NET 8.0 (長期サポート) を選択します。
4. [**認証の種類]**で、[Microsoft ID プラットフォーム] を選択します。

Visual Studio で空のプロジェクト テンプレートからアプリを作成している場合、または既に既存の ASP.NET Web アプリがあり、Microsoft Entra ID 認証を追加する場合は、次の手順に従います。

1. ソリューション エクスプローラーを開き、接続済みサービス選択します。
2. Visual Studio で [接続済みサービス] ウィンドウが開いたら、[サービス依存関係の追加]選択するか、[+] アイコンを使用します。

    [Image: Visual Studio の [接続済みサービス] ウィンドウを示すスクリーンショット。]
3. ドロップダウン リストから、Microsoft ID プラットフォーム選択します。 必要に応じて、[検索] タブを使用できます。

    [Image: Visual Studio での Microsoft ID プラットフォームとその他のサービスの依存関係を示すスクリーンショット。]
4. 次に示すように、Microsoft ID プラットフォームは、[接続済みサービス] ウィンドウのサービス依存関係の下に表示されます。

    [Image: Visual Studio へのサービス依存関係として正常に接続された Microsoft ID プラットフォームを示すスクリーンショット。]

### 必要なコンポーネントをインストールする

プロジェクトで Microsoft ID プラットフォームを使用するには、**dotnet msidentity ツール**をインストールする必要があります。 このコマンド ライン ツールを使用すると、Microsoft Entra アプリの登録を作成できます。 また、ASP.NET Core アプリケーション (MVC、Razor Pages、Blazor WebAssembly (WASM)、Blazor WASM Hosted、Blazor Server) の構成ファイルを変更することで、Microsoft ID プラットフォームを使用するようにアプリを更新します。

デバイスに dotnet msidentity ツールがインストールされていない場合は、次のように Visual Studio からインストールするように求められます。

[Image: dotnet msidentity ツールをインストールするための Visual Studio プロンプトを示すスクリーンショット]

コマンド ラインから dotnet msidentity ツールをインストールするには、次のコマンドを実行します。

```sh
dotnet tool install --global Microsoft.dotnet-msidentity --version 2.0.8
```

dotnet msidentity ツールのインストールが完了したら、[次  を選択して構成に進みます。

### Microsoft ID プラットフォームを使用するようにアプリケーションを構成する

Microsoft ID プラットフォーム接続サービスを使用すると、従業員または外部テナントでアプリケーションを構成できます。 構成を完了するには、次の手順に従います。

1. 右上のセクションで、Microsoft アカウントにサインインします。 複数のアカウントがある場合は、アプリケーションを登録するテナントのアカウントを選択します。

    [Image: Microsoft ID プラットフォームを使用するようにアプリケーションを構成する Visual Studio ウィンドウを示すスクリーンショット。]
2. サインインすると、テナントに登録されているアプリケーションの一覧が表示されます。アプリケーションの表示名、クライアント ID、作成日を指定します。
3. Microsoft Entra 管理センターでアプリの登録をまだ作成していない場合は、[新しいの作成] 選択します。 アプリケーションを作成するテナントを選択し、sample-web-app や Select **Register**などの表示名を指定します。 アプリケーションの表示名は後で変更できます。

    [Image: 新しいアプリケーションを登録する Visual Studio ウィンドウを示すスクリーンショット。]
4. 作成したアプリケーションが一覧に表示されます。 それを選択し、[次へ] **選択します。**

    [Image: テナント内のアプリ登録の一覧を示すスクリーンショット。]
5. 次の画面では、Microsoft Graph またはその他の API にアクセスするようにアプリのアクセス許可を構成できます。 **「次へ」** を選択すると、必要な情報がまだそろっていない場合に、後で構成を完了できます。
6. プロジェクトに加えられた変更の概要を示す画面が表示されます。 **[完了]** を選択して、プロセスを完了します。

    [Image: プロジェクトに加えられた変更の一覧を示すスクリーンショット。]
7. 次に示すように、プロジェクト内の実際の変更を示す依存関係の構成の進行状況画面が表示されます。 成功したら、**[閉じる]** を選択します。

    [Image: 依存関係の構成の進行状況を示すスクリーンショット。]

### [省略可能]: Web API にアクセスするためのアクセス許可を構成する

Microsoft ID プラットフォーム接続サービスを使用すると、必要に応じて、Microsoft Graph やその他の Web API にアクセスするためのアクセス許可を追加できます。 Microsoft ID プラットフォームに登録されている独自の API またはサードパーティ API のサポートを追加できます。

Microsoft Graph などの API のサポートを追加するなど、変更する場合は、Microsoft ID プラットフォーム サービスの依存関係の 3 つの点を選択し、[依存関係編集] を選択します。 手順を繰り返し、アクセス権を付与する API を追加できます。

[Image: Microsoft Graph やその他の Web API にアクセスするためのアクセス許可を追加できるウィンドウを示すスクリーンショット。]

### アプリを実行してテストする

サンプル アプリケーションを実行するには、次の手順に従います。

1. Visual Studio の上部のナビゲーション バーに移動し、[デバッグ]  [デバッグなしで開始]選択して、次のようにアプリケーションのビルドを開始します。

    [Image: Visual Studio でのサンプル アプリケーションのビルドを示すスクリーンショット。]
2. ビルドが完了すると、https://localhost:7142に新しいブラウザー ウィンドウが開きます。
3. アプリケーションの動作に応じて、Microsoft Entra ID によって、必要なアクションを実行するようにリダイレクトされます。 サンプル アプリケーションの場合、次に示すように、サインアップとサインインのプロセスを完了するように求められます。

    [Image: Visual Studio で実行されている Microsoft ID プラットフォームと統合されたサンプル アプリケーションを示すスクリーンショット。]

#### 関連コンテンツ

- [ASP.NET Web アプリに Microsoft によるサインインを追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-aspnet-webapp)
- [Microsoft Entra 外部 ID 用の Visual Studio Code 拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/visual-studio-code-extension)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customize-invitation-api"} -->
## B2B コラボレーションの API とカスタマイズ - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customize-invitation-api
- Service: entra-external-id / external
- Article date: 2024-12-10
- Summary: Microsoft Entra B2B コラボレーションは、会社のアプリケーションにビジネス パートナーが選択的にアクセスできるようにすることで会社間のリレーションシップをサポートします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

[Microsoft Graph REST API を使うと](https://learn.microsoft.com/ja-jp/graph/api/resources/invitation)、組織に最適な方法で招待プロセスをカスタマイズできます。

### 招待 API の機能

API には次の機能が用意されています。

1. 次の JSON 表現は、 *任意* のメール アドレスを持つ外部ユーザーを招待する方法を示しています。

    ```
    "invitedUserDisplayName": "Taylor",
    "invitedUserEmailAddress": "taylor@fabrikam.com"
    ```
2. 招待に応じたユーザーの移動先をカスタマイズできます。

    ```
    "inviteRedirectUrl": "https://myapps.microsoft.com/"
    ```
3. 標準的な招待メールを Microsoft 経由で送信することを選択できます。

    ```
    "sendInvitationMessage": true
    ```

    カスタマイズできる受信者へのメッセージを含みます。

    ```
    "customizedMessageBody": "Hello Sam, let's collaborate!"
    ```
4. また、このコラボレーターの招待について知らせておきたい人を cc することができます。
5. または、Microsoft Entra ID を通じて通知を送信しないことを選択して、招待とオンボード ワークフローを完全にカスタマイズすることができます。

    ```
    "sendInvitationMessage": false
    ```

    この場合は、招待に応じるための URL を API から受け取り、それを電子メール テンプレート、IM、またはその他の任意の配布方法に埋め込むことができます。
6. 最後に、管理者である場合は、ユーザーをメンバーとして招待することを選択できます。

    ```
    "invitedUserType": "Member"
    ```

### ユーザーが既にディレクトリに招待されたかどうかを判断する

招待 API を使用して、ユーザーがリソース テナントに既に存在するかどうかを判断できます。 これは、招待 API を使用してユーザーを招待するアプリを開発している場合に便利です。 ユーザーが既にリソース ディレクトリに存在する場合、そのユーザーは招待を受信しないため、まずクエリを実行して、電子メールが UPN またはその他のサインイン プロパティとして既に存在するかどうかを判定できます。

1. ユーザーのメール ドメインが、リソース テナントの確認済みドメインに含まれていないことを確認します。
2. リソース テナントで、次の get ユーザー クエリを使用します。0 は招待する電子メール アドレスです。

    ```
    “userPrincipalName eq '0' or mail eq '0' or proxyAddresses/any(x:x eq 'SMTP:0') or signInNames/any(x:x eq '0') or otherMails/any(x:x eq '0')" 
    ```

### 承認モデル

API は、以下の承認モードで実行できます。

#### アプリ + ユーザー モード

このモードでは、API を使用するユーザーは、B2B 招待を作成できるアクセス許可を付与されている必要があります。

#### アプリのみモード

アプリのみのコンテキストで招待を成功させるには、アプリに User.Invite.All スコープが必要です。

詳細については、「https://developer.microsoft.com/graph/docs/authorization/permission_scopes」を参照してください。

### PowerShell

PowerShell を使用して、簡単に外部ユーザーを組織に追加および招待できます。 次のコマンドレットを使用して招待を作成します。

```powershell
New-MgInvitation
```

以下のオプションを使用できます。

- -InvitedUserDisplayName
- -InvitedUserEmailAddress
- -SendInvitationMessage
- -InvitedUserMessageInfo

#### 招待の状態

外部ユーザーに招待を送信した後、**Get-MgBetaUser** コマンドレットを使用して、招待が受け取られたかどうかを確認できます。 外部ユーザーに招待が送信されると、Get-MgBetaUser の次のプロパティが入力されます。

- **externalUserState** は、招待が **PendingAcceptance** であるか **Accepted** であるかを示します。
- **externalUserStateChangeDateTime** は、**externalUserState** プロパティに対する最新の変更のタイムスタンプを示します。

**Filter** オプションを使用して、**externalUserState** で結果をフィルター処理できます。 次の例では、保留中の招待を持っているユーザーのみを表示するように結果をフィルター処理する方法を示しています。 表示するプロパティを指定するための **Format-List** オプションも示しています。

```powershell
Get-MgBetaUser -Filter "externalUserState eq 'PendingAcceptance'" | Format-List -Property DisplayName,UserPrincipalName,externalUserState,externalUserStateChangeDateTime
```

Note

必ず最新バージョンの [Microsoft Graph PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)を使ってください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/default-account"} -->
## Microsoft Entra アカウントを使用する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/default-account
- Service: entra-external-id / external
- Article date: 2026-03-27
- Summary: 外部のビジネス パートナーとゲスト ユーザーが Microsoft Entra の職場または学校アカウントを使用して、B2B Collaboration のアプリにサインインできるようにします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

既定では、B2B コラボレーションの ID プロバイダーオプションとして Microsoft Entra ID を使用できます。 外部のゲスト ユーザーが職場または学校を通じて Microsoft Entra アカウントを持っている場合は、Microsoft Entra アカウントを使用して B2B コラボレーションの招待を利用したり、サインアップ ユーザーフローを完了したりできます。

### Microsoft Entra アカウントを使用したゲストのサインイン

ゲスト ユーザーが Microsoft Entra アカウントを使用してサインインできるようにする場合、招待フローまたはセルフサービス サインアップ ユーザー フローのいずれかを使用できます。 それ以上の構成は必要ありません。

#### 招待フローの Microsoft Entra アカウント

B2B コラボレーションに[ゲスト ユーザーを招待する](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)ときは、その Microsoft Entra アカウントを、サインインに使う**メール アドレス**として指定できます。

[Image: Microsoft Entra アカウントを使ってゲスト ユーザーを招待するスクリーンショット。]

#### セルフサービス サインアップ ユーザー フローの Microsoft Entra アカウント

Microsoft Entra アカウントは、セルフサービス サインアップ ユーザー フロー用の ID プロバイダー オプションです。 ユーザーは、自分の Microsoft Entra アカウントを使用してアプリケーションにサインアップできます。 まず、テナント [のセルフサービス サインアップを有効](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow) にしてから、アプリケーションのユーザー フローを設定します。

[Image: セルフサービス サインアップ ユーザー フローの Microsoft Entra アカウントのスクリーンショット。]

### アプリケーションのパブリッシャー ドメインを検証する

2020 年 11 月現在、新しいアプリケーション登録は、[アプリケーションの発行元ドメインが検証済み](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain) "**かつ**" 会社の ID が Microsoft Partner Network で検証され、アプリケーションに関連付けられている場合を除き、ユーザーの同意プロンプトで未検証として表示されます。 (この変更の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)を参照してください)。Microsoft Entra ユーザー フローの場合、発行元のドメインは、[Microsoft アカウント](https://learn.microsoft.com/ja-jp/entra/external-id/microsoft-account)またはその他の Microsoft Entra テナントを ID プロバイダーとして使用する場合にのみ表示されることに注意してください。 これらの新しい要件を満たすには、次の手順に従います。

1. [自分の Microsoft Partner Network (MPN) アカウントを使用して会社 ID を確認します](https://learn.microsoft.com/ja-jp/partner-center/verification-responses)。 このプロセスにより、会社と会社の主要連絡先に関する情報が検証されます。
2. 発行元の確認プロセスを完了し、次のいずれかのオプションを使用して、MPN アカウントをアプリ登録に関連付けます。
    - Microsoft アカウント ID プロバイダーのアプリの登録が Microsoft Entra テナント内にある場合は、[アプリ登録ポータルでアプリを検証します](https://learn.microsoft.com/ja-jp/entra/identity-platform/mark-app-as-publisher-verified)。
    - Microsoft アカウント ID プロバイダーのアプリの登録が Azure AD B2C テナント内にある場合、[Microsoft Graph API を使用して発行元がアプリを検証済みとしてマーク](https://learn.microsoft.com/ja-jp/entra/identity-platform/troubleshoot-publisher-verification#making-microsoft-graph-api-calls)します (たとえば、Graph Explorer を使用)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/direct-federation"} -->
## SAML/WS-Fed ID プロバイダーを追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation
- Service: entra-external-id / external
- Article date: 2026-04-08
- Summary: ユーザーが職場アカウントでサインインできるように、SAML 2.0 または WS-Fed ID プロバイダーとの直接フェデレーションを設定します。 フェデレーションの属性と要求について理解します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra テナントは、SAML または WS-Fed ID プロバイダー (IdP) を使用する外部組織と直接フェデレーションできます。 外部組織のユーザーは、独自の IdP マネージド アカウントを使用して、招待の利用中またはセルフサービス サインアップ時に、新しい Microsoft Entra 資格情報を作成しなくても、アプリまたはリソースにサインインできます。 ユーザーは、アプリにサインアップまたはサインインするときに IdP にリダイレクトされ、正常にサインインすると Microsoft Entra に戻ります。

### [前提条件]

- [SAML/WS-Fed ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-overview)の構成に関する考慮事項を確認します。
- 従業員テナントまたは [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。

注

IdP の発行者の値は、RFC 3986 形式 (たとえば、 `https://testdev.example.com` や `http://www.example.com/exk10l6w90DHM0yi`) に従う有効な URI である必要があります。 単一単語または URI 以外の値 ( `testdev`など) はサポートされておらず、ポータルによって拒否されます。 これは、識別子 URI の制限に関するページに記載されているように、Microsoft Entra ID のセキュリティで保護された [識別子パターンと](https://learn.microsoft.com/ja-jp/entra/identity-platform/identifier-uri-restrictions)一致します。 SAML IdP の場合、発行者は、SAML 2.0 標準および [Microsoft Entra 検証規則](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-saml-idp#required-attributes)に従って、プロバイダーを URI として一意に識別する必要があります。

### SAML/WS-Fed IdP フェデレーションを構成する方法

#### 手順 1: パートナーが DNS テキスト レコードを更新する必要があるかどうかを判断する

パートナーが DNS レコードを更新してフェデレーションを有効にする必要があるかどうかを判断するには、次の手順に従います。

1. パートナーの IdP パッシブ認証 URL を調べて、ドメインがターゲット ドメインまたはターゲット ドメイン内のホストと一致するかどうかを確認します。 すなわち、`fabrikam.com` のフェデレーションを設定する場合は次のようになります。

    - パッシブ認証エンドポイントが `https://fabrikam.com` または `https://sts.fabrikam.com/adfs` (同じドメイン内のホスト) の場合、DNS の変更は必要ありません。
    - パッシブ認証エンドポイントが `https://fabrikamconglomerate.com/adfs` または `https://fabrikam.co.uk/adfs` の場合、ドメインは fabrikam.com ドメインと一致していないため、パートナーは DNS 構成に認証 URL のテキスト レコードを追加する必要があります。
2. 前の手順に基づいて DNS の変更が必要な場合は、次の例のように、ドメインの DNS レコードに TXT レコードを追加するようにパートナーに依頼します。

    `fabrikam.com.  IN   TXT   DirectFedAuthUrl=https://fabrikamconglomerate.com/adfs`

#### 手順 2: パートナー組織の IdP を構成する

次に、パートナー組織において、必須の要求と証明書利用者の信頼を指定して IdP を構成する必要があります。 フェデレーションを適切に機能させるためには、Microsoft Entra 外部 ID が、外部 IdP において構成される特定の属性とクレームを送信するように外部 IdP に求めます。

注

フェデレーション用に SAML/WS-Fed IdP を構成する方法を示すために、例として Active Directory フェデレーション サービス (AD FS) を使用します。 [AD FSとSAML/WS-Fed IdPのフェデレーション構成](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-adfs)に関する記事を参照してください。この中には、AD FSをSAML 2.0またはWS-Fed IdPとしてフェデレーションの準備を整えるための設定例が示されています。

##### SAML 2.0 ID プロバイダーを構成するには

Microsoft Entra 外部 ID では、外部 IdP からの SAML 2.0 応答に特定の属性と要求が含まれている必要があります。 必要な属性と要求は、次のいずれかの方法で外部 IdP で構成できます。

- オンライン セキュリティ トークン サービス XML ファイルへのリンク、または
- 値を手動で入力する

必要な値については、次の表を参照してください。

注

値が、外部フェデレーションを設定しているクラウドと一致することを確認してください。

**表 1. IdP からの SAML 2.0 応答に必要な属性。**

| 属性 | 従業員テナントの値 | 外部テナント向けの価値 |
| --- | --- | --- |
| アサーチョンコンシューマサービス | `https://login.microsoftonline.com/login.srf` | `https://<tenantID>.ciamlogin.com/login.srf``https://<tenantID>.ciamlogin.com/login.srf`、そのプロバイダーで必要な場合は、外部 IdP のコールバック/ACS URL として追加します。 |
| 対象者 | `https://login.microsoftonline.com/<tenant ID>/` (推奨) `<tenant ID>` を、フェデレーションを設定する Microsoft Entra テナントのテナント ID に置き換えます。 外部フェデレーションに対して Microsoft Entra ID によって送信された SAML 要求の場合、発行者 URL はテナント エンドポイントです (たとえば `https://login.microsoftonline.com/<tenant ID>/`)。 新しいフェデレーションでは、すべてのパートナーは、SAML または WS-Fed ベースの IdP の対象ユーザーをテナントのエンドポイントに設定することをお勧めします。 グローバル エンドポイント (`urn:federation:MicrosoftOnline`など) で構成されている既存のフェデレーションは引き続き機能しますが、外部 IdP が Microsoft Entra ID によって送信された SAML 要求のグローバル発行者 URL を想定している場合、新しいフェデレーションは機能しなくなります。 | `https://login.microsoftonline.com/<tenant ID>/``<tenant ID>`を、フェデレーションを設定する Microsoft Entra テナントのテナント ID に置き換えます。 |
| 発行者 | パートナーの IdP の発行者 URI (たとえば `http://www.example.com/exk10l6w90DHM0yi...`) | パートナーの IdP の発行者 URI (たとえば `http://www.example.com/exk10l6w90DHM0yi...`) |

**表 2. IdP によって発行された SAML 2.0 トークンに必要な要求。**

| 属性名 | 値 |
| --- | --- |
| NameID の形式 | `urn:oasis:names:tc:SAML:2.0:nameid-format:persistent` |
| `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` | ユーザーの電子メール アドレス |

##### WS-Fed ID プロバイダーを構成するには

Microsoft Entra 外部 ID には、外部 IdP からの WS-Fed メッセージに特定の属性と要求が含まれている必要があります。 必要な属性と要求は、次のいずれかの方法で外部 IdP で構成できます。

- オンライン セキュリティ トークン サービス XML ファイルへのリンク、または
- 値を手動で入力する

注

現在、Microsoft Entra ID との互換性がテストされている 2 つの WS-Fed プロバイダーは、AD FS と Shibboleth です。

###### 必須の WS-Fed 属性および要求

サード パーティの WS-Fed IdP に構成する必要がある特定の属性と要求の要件を次の各表に示します。 フェデレーションを設定するには、IdP からの WS-Fed メッセージにおいて以下の属性が受け取られる必要があります。 これらの属性は、オンライン セキュリティ トークン サービスの XML ファイルにリンクするか手動で入力することによって、構成できます。

必要な値については、次の表を参照してください。

注

値が、外部フェデレーションを設定しているクラウドと一致することを確認してください。

**表 3. IdP からの WS-Fed メッセージに必要な属性。**

| 属性 | 従業員テナントの値 | 外部テナント向けの価値 |
| --- | --- | --- |
| PassiveRequestorEndpoint | `https://login.microsoftonline.com/login.srf` | `https://<tenantID>.ciamlogin.com/login.srf` |
| 対象者 | `https://login.microsoftonline.com/<tenant ID>/` (推奨) `<tenant ID>` を、フェデレーションを設定する Microsoft Entra テナントのテナント ID に置き換えます。 外部フェデレーションに対して Microsoft Entra ID によって送信された SAML 要求の場合、発行者 URL はテナント エンドポイントです (たとえば `https://login.microsoftonline.com/<tenant ID>/`)。 新しいフェデレーションでは、すべてのパートナーは、SAML または WS-Fed ベースの IdP の対象ユーザーをテナントのエンドポイントに設定することをお勧めします。 グローバル エンドポイント (`urn:federation:MicrosoftOnline`など) で構成されている既存のフェデレーションは引き続き機能しますが、外部 IdP が Microsoft Entra ID によって送信された SAML 要求のグローバル発行者 URL を想定している場合、新しいフェデレーションは機能しなくなります。 | `https://login.microsoftonline.com/<tenant ID>/``<tenant ID>`を、フェデレーションを設定する Microsoft Entra テナントのテナント ID に置き換えます。 |
| 発行者 | パートナーの IdP の発行者 URI (たとえば `http://www.example.com/exk10l6w90DHM0yi...`) | パートナーの IdP の発行者 URI (たとえば `http://www.example.com/exk10l6w90DHM0yi...`) |

**表 4. IdP が発行する WS-Fed トークンに必須の要求。**

| 属性 | 値 |
| --- | --- |
| イミュータブルID (不変ID) | `http://schemas.microsoft.com/LiveID/Federation/2008/05/ImmutableID` |
| メールアドレス | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` |

#### 手順 3: Microsoft Entra 外部 ID で SAML/WS-Fed IdP フェデレーションを構成する

次に、Microsoft Entra 外部 ID で手順 1 で構成した IdP とのフェデレーションを構成します。 Microsoft Entra 管理センターまたは [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/samlorwsfedexternaldomainfederation) を使用できます。 フェデレーション ポリシーが有効になるまで 5 分から 10 分かかる場合があります。 この間、セルフサービス サインアップを完了したり、フェデレーション ドメインの招待を利用したりしないでください。 次の属性は必須です。

- パートナーの IdP の発行者 URI
- パートナー IdP のパッシブ認証エンドポイント (サポートされているのは https のみ)
- 証明書

##### Microsoft Entra 管理センターでテナントに IdP を追加するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ** ] メニューからテナントに切り替えます。
3. **Microsoft Entra ID**&gt;**External Identities**&gt;**すべての ID プロバイダーを表示する**。
4. [ **カスタム** ] タブを選択し、[ **Add new**&gt;**SAML/WS-Fed**] を選択します。

    [Image: 新しい SAML または WS-Fed IdP を追加するためのボタンを示すスクリーンショット。]
5. [ **新しい SAML/WS-Fed IdP** ] ページで、次のように入力します。

    - **表示名** - パートナーの IdP を識別するのに役立つ名前を入力します。
    - **ID プロバイダー プロトコル** - **SAML** または **WS-Fed** を選択します。
    - **ドメインレス** - **ドメインレス** を選択すると、ユーザーのメール アドレスのドメイン チェックは適用されません。 詳細については、「 [ドメインレス SAML IdP フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation#domainless-saml-idp-federation)」を参照してください。
    - **フェデレーション IdP のドメイン名 - フェデレーション** のパートナーの IdP ターゲット ドメイン名を入力します。 この初期構成時に、ドメイン名を 1 つだけ入力します。 ドメインは後からさらに追加できます。

    [Image: 新しい SAML または WS-Fed IdP ページを示すスクリーンショット。]
6. メタデータを設定する方法を選択します。 メタデータを含むファイルがある場合は、[メタデータ ファイルの解析] を選択して **ファイル** を参照することで、フィールドを自動的に設定できます。 または、[ **入力メタデータ] を手動で** 選択し、次の情報を入力できます。

    - パートナーの SAML IdP の **発行者 URI** 、またはパートナーの WS-Fed IdP の **エンティティ ID** 。
    - パートナーの SAML IdP の **パッシブ認証エンドポイント** 、またはパートナーの WS-Fed IdP の **パッシブ リクエスタ エンドポイント** 。
    - **証明書** - 署名証明書 ID。
    - **メタデータ URL** - 署名証明書の自動更新のための IdP のメタデータの場所。

    [Image: メタデータ フィールドを示すスクリーンショット。]

    注

    メタデータ URL は省略可能です。 ただし、強くお勧めします。 メタデータ URL を指定すると、署名証明書が有効期限切れになったときに、Microsoft Entra ID によって自動的に更新することができます。 有効期限が切れる前に何らかの理由で証明書がローテーションされた場合、またはメタデータ URL を指定しない場合、Microsoft Entra ID はそれを更新できません。 この場合、署名証明書を手動で更新する必要があります。
7. **[保存] を選択します**。 ID プロバイダーが **SAML/WS-Fed ID プロバイダーの** 一覧に追加されます。

    [Image: SAML/WS-Fed ID プロバイダーの一覧と新しいエントリを示すスクリーンショット。]
8. (省略可能) このフェデレーション ID プロバイダーにドメイン名を追加するには、次の手順を行います。

    1. [ **ドメイン]** 列のリンクを選択します。

        [Image: SAML/WS-Fed ID プロバイダーにドメインを追加するためのリンクを示すスクリーンショット。]
    2. [ **フェデレーション IdP のドメイン名] の**横にドメイン名を入力し、[ **追加**] を選択します。 追加するドメインごとに繰り返します。 完了したら、[ **完了]** を選択します。

        [Image: ドメインの詳細ウィンドウの [追加] ボタンを示すスクリーンショット。]

##### Microsoft Graph API を使用してフェデレーションを構成する方法

Microsoft Graph API [samlOrWsFedExternalDomainFederation](https://learn.microsoft.com/ja-jp/graph/api/resources/samlorwsfedexternaldomainfederation?view=graph-rest-beta&preserve-view=true) リソースの種類を使用して、SAML または WS-Fed プロトコルをサポートする ID プロバイダーとのフェデレーションを設定できます。

#### 手順 4: 引き換え順序を構成する (従業員テナントでの B2B コラボレーション)

確認済みドメインとの B2B コラボレーションのためにワークフォース テナントでフェデレーションを構成する場合は、招待の利用時にフェデレーション IdP が最初に使用されていることを確認します。 インバウンド B2B コラボレーションのテナント間アクセス設定で[**引き換え順序**](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)の設定を構成します。 **SAML/WS-Fed ID プロバイダー**を**プライマリ ID** プロバイダーの一覧の一番上に移動して、フェデレーション IdP で引き換えの優先順位を付けます。

新しい B2B ゲスト ユーザーを招待することで、フェデレーションのセットアップをテストできます。 詳細については、 [Microsoft Entra 管理センターでの Microsoft Entra B2B コラボレーション ユーザーの追加に関するページを](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)参照してください。

注

招待の引き換え順序は、Microsoft Graph REST API (ベータ 版) を使用して構成できます。 例 2: Microsoft Graph リファレンス ドキュメントの [既定の招待引き換え構成を更新](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicyconfigurationdefault-update?view=graph-rest-beta&tabs=http#example-2-update-default-invitation-redemption-configuration&preserve-view=true) するを参照してください。

### ドメインレス SAML IdP フェデレーション

Microsoft Entra IDの従来のフェデレーションでは、カスタム ドメイン (`contoso.com` など) を確認し、認証要求を外部 SAML ID プロバイダー (IdP) にリダイレクトするようにそのドメインを構成する必要があります。 このセットアップでは、認証後に外部 IdP によって提供される電子メール要求のドメインが、Microsoft Entra IDで構成された SAML IdP に関連付けられているドメインに対して検証されます。

ユーザーの電子メール ドメインが SAML IdP で構成されているドメイン (yahoo.com や gmail.com など) と異なる場合、ユーザーはサインイン中に次のエラーが発生する可能性があります。

**AADSTS5000819**: SAML アサーションが無効です。 電子メール アドレス要求が見つからないか、外部領域のドメインと一致しません。

このエラーは通常次の場合に発生します。

- 外部 SAML IdP が電子メール要求を送信しない、または
- IdP によって提供される電子メール アドレス ドメインが、Microsoft Entra IDの外部 IdP で構成されているドメインと一致しません

電子メール要求が存在する場合でも、ドメインベースの一致要件により、電子メール ドメインが構成済みの IdP ドメインと一致しない場合、認証が失敗する可能性があります。

SAML ID プロバイダーとのドメインレス SAML フェデレーションを使用すると、外部ユーザーは、メール ドメインに関係なく、IdP で管理される資格情報を使用してアプリまたは従業員リソースに対して認証を行うことができます。 ドメインレス フェデレーションを使用すると、サインインまたは招待の利用中に、ユーザーの電子メールドメインと事前構成済みの IdP ドメインの間でドメインマッチングを行う必要がなくなります。

#### ドメインレス SAML IdP フェデレーションを構成する

ドメイン一致の制限に対処するには、SAML IdP をドメインレスとして構成します。 ドメインレス フェデレーションが有効になっている場合:

- Microsoft Entra IDは、発行者 URI の関連付けに基づいて、構成済みの SAML IdP に認証要求をルーティングします。
- ユーザーのメール アドレス ドメインが、IdP 用に構成されたドメインと一致しません。

ユーザーは、外部 SAML IdP を使用してサインインするときに、任意のドメインの電子メール アドレス (yahoo.com や gmail.com など) を使用して正常に認証できます。

新しい SAML IdP に対してドメインレス フェデレーションを有効にするには、次の手順に従います。

- [ **新しい SAML/WS-Fed IdP** ] ページで、[ **ドメインレス**] を選択します。 [ **ドメインレス]** を選択すると、ユーザーのメール アドレスのドメイン チェックは適用されません。

    [Image: ドメインレス構成を含む SAML/WS-Fed ID プロバイダーの一覧を示すスクリーンショット。]

重要

**[ドメインレス**] フィールドが選択されている場合、フェデレーションはドメインレスとして構成されます。 Microsoft Entra IDでは、ドメイン ベースのルーティングではなく、**Issuer URI** を使用して受信認証要求を照合します。 現時点では、テナントごとに構成できるワイルドカード IdP は **1 つだけ** です。

#### ドメインレス SAML IdP フェデレーションのユーザー フロー

ドメインレス フェデレーションを構成したら、次の手順に従って、パートナー組織からゲスト ユーザーを招待できます。

1. Microsoft Entra 管理センターで、**Identity**&gt;**Users**&gt;**All users** に移動します。
2. [ **+ 新しいユーザー** ] を選択し、[ **外部ユーザーの招待**] を選択します。
3. ゲスト ユーザーのメール アドレスを入力します。 メール ドメインは、テナント内の検証済みドメインと一致する必要はありません。
4. 招待リダイレクト URL に **、domain\_hint** パラメーターを含め、構成された発行者 URI に基づいてユーザーが適切な IdP にルーティングされるようにします。 **domain\_hint**値は、SAML IdP 構成で定義されている**発行者 URI** と一致する必要があります。
5. 招待を完了して送信します。
6. 招待されたユーザーが招待を利用すると、Microsoft Entra IDは、`domain_hint` パラメーターの発行者 URI を使用して、構成された SAML IdP に認証要求をルーティングします。
7. ユーザーは外部 SAML IdP を使用して認証します。 ユーザーのメール アドレス ドメインが IdP 用に構成されたドメインと一致せず、ユーザーはリソース テナントにアクセスできます。
8. それ以降のサインインでは、ユーザー オブジェクトが外部 IdP 関連付けで更新されるため、ユーザーは外部 SAML IdP で認証することで、リソース テナント アプリケーションに直接アクセスできます。

#### 既知の問題

次の既知の問題に積極的に対処中であり、このセクションは修正プログラムが利用可能になった後に更新されます。

- IdP 構成が保存されていない場合でも、無効なドメイン名が入力された場合、IdP 構成は削除されます。

### 証明書または構成の詳細を更新する方法

[すべての ID プロバイダー ] ページで、構成されている SAML/WS-Fed ID プロバイダーとその証明書の有効期限の一覧を表示できます。 この一覧から、証明書を更新したり、その他の構成の詳細を変更したりできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
2. **Microsoft Entra ID**&gt;**External Identities**&gt;**すべての ID プロバイダーを表示する**。
3. [カスタム] タブ **を** 選択します。
4. 一覧内の ID プロバイダーまでスクロールするか、検索ボックスを使用します。
5. 証明書を更新するか、構成の詳細を変更するには、次の手順を実行します。

    - ID プロバイダーの **[構成]** 列で、[ **編集]** リンクを選択します。
    - 構成ページで、次の詳細から任意のものを変更します。
        - **表示名** - パートナーの組織の表示名。
        - **ID プロバイダー プロトコル** - **SAML** または **WS-Fed** を選択します。
        - **パッシブ認証エンドポイント** - パートナー IdP のパッシブ リクエスタ エンドポイント。
        - **証明書** - 署名証明書の ID。 更新するには、新しい証明書 ID を入力します。
        - **メタデータ URL** - 署名証明書の自動更新に使用されるパートナーのメタデータを含む URL。
    - **[保存] を選択します**。

    [Image: IDP 構成の詳細のスクリーンショット。]
6. パートナーに関連付けられているドメインを編集するには、[ **ドメイン** ] 列のリンクを選択します。 ドメインの詳細ウィンドウで、次の操作を行います。

    - ドメインを追加するには、[ **フェデレーション IdP のドメイン名] の横にドメイン名を**入力し、[ **追加**] を選択します。 追加するドメインごとに繰り返します。
    - ドメインを削除するには、ドメインの横にある削除アイコンを選択します。
    - 完了したら、[ **完了]** を選択します。

    [Image: ドメイン構成ページのスクリーンショット。]
7. ドメインレス フェデレーションに切り替えるには、[ **ドメインレス** ] チェック ボックスをオンにし、[ **完了]** を選択します。

    [Image: ドメインレスのドメイン構成ページのスクリーンショット。]

    注

    パートナーとのフェデレーションを削除するには、最初に 1 つを除くすべてのドメインを削除してから、次のセクション の手順に従います。

### フェデレーションを削除する方法

フェデレーション構成は削除できます。 その場合、既に招待を利用したフェデレーション ゲスト ユーザーはサインインできなくなります。 ただし、 [引き換えの状態をリセット](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)することで、リソースへのアクセス権を再び付与することができます。 Microsoft Entra管理センターで IdP の構成を削除するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
2. **Microsoft Entra ID**&gt;**External Identities**&gt;**すべての ID プロバイダーを表示する**。
3. [ **カスタム** ] タブを選択し、一覧から ID プロバイダーまでスクロールするか、検索ボックスを使用します。
4. [ **ドメイン** ] 列のリンクを選択して、IdP のドメインの詳細を表示します。
5. [ **ドメイン名** ] 一覧の 1 つ以外のドメインをすべて削除します。
6. [ **構成の削除]** を選択し、[ **完了]** を選択します。

    [Image: 構成の削除のスクリーンショット。]
7. [ **OK] を** 選択して削除を確定します。

Microsoft Graph API [samlOrWsFedExternalDomainFederation](https://learn.microsoft.com/ja-jp/graph/api/resources/samlorwsfedexternaldomainfederation?view=graph-rest-beta&preserve-view=true) リソースの種類を使用してフェデレーションを削除することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/direct-federation-adfs"} -->
## AD FS フェデレーションを設定する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-adfs
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: Microsoft Entra External IDで B2B コラボレーション用に AD FS との SAML/WS-Fed IdP フェデレーションを設定する方法について説明します。 AD FS を SAML 2.0 または WS-Fed IdP として構成し、属性と要求を管理します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

注

Microsoft Entra External IDの *Direct federation* は、*SAML/WS-Fed ID プロバイダー (IdP) フェデレーション*と呼ばれるようになりました。

この記事では、SAML 2.0 または WS-Fed IdP として Active Directory Federation Services (AD FS) を使用して、[SAML/WS-Fed IdP フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)を設定する方法について説明します。 フェデレーションをサポートするには、IdP に特定の属性とクレームを構成する必要があります。 フェデレーション用に IdP を構成する方法を示すために、例として Active Directory Federation Services (AD FS) を使用します。 AD FS を SAML IdP として設定する方法と、WS-Fed IdP として設定する方法の両方を紹介します。

注

この記事では、わかりやすいように、AD FS を SAML 用に設定する方法と、WS-Fed 用に設定する方法の両方について説明します。 IdP が AD FS であるフェデレーションの統合には、WS-Fed をプロトコルとして使用することをお勧めします。

### AD FS を SAML 2.0 のフェデレーション用に構成する

Microsoft Entra B2B は、以下に示す特定の要件で SAML プロトコルを使用する IdP とフェデレーションするように構成できます。 SAML の構成手順を説明するために、このセクションでは SAML 2.0 用に AD FS を設定する方法を示します。

フェデレーションを設定するには、IdP からの SAML 2.0 応答において以下の属性が受け取られる必要があります。 これらの属性は、オンライン セキュリティ トークン サービスの XML ファイルにリンクするか手動で入力することによって、構成できます。 「[Create a test AD FS instance](https://medium.com/in-the-weeds/create-a-test-active-directory-federation-services-3-0-instance-on-an-azure-virtual-machine-9071d978e8ed)」(AD FS テスト インスタンスの作成) のステップ 12 では、AD FS エンドポイントを検索する方法や、メタデータ URL (たとえば `https://fs.iga.azure-test.net/federationmetadata/2007-06/federationmetadata.xml`) を生成する方法について説明しています。

| 属性 | 値 |
| --- | --- |
| アサーチョンコンシューマサービス | `https://login.microsoftonline.com/login.srf` |
| 対象ユーザー | `urn:federation:MicrosoftOnline` |
| 発行者 | パートナー IdP の発行者 URI (たとえば `http://www.example.com/exk10l6w90DHM0yi...`) |

IdP によって発行される SAML 2.0 トークン内に以下のクレームが構成されている必要があります。

| 属性 | 値 |
| --- | --- |
| NameID の形式 | `urn:oasis:names:tc:SAML:2.0:nameid-format:persistent` |
| `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` | ユーザーの電子メール アドレス |

次のセクションでは、SAML 2.0 IdP の例として、AD FS を使用して必須の属性とクレームを構成する方法について説明します。

#### 開始する前に

この手順を開始する前に、AD FS サーバーが既に設定されていて稼働している必要があります。

#### 要求記述を追加する

1. ご利用の AD FS サーバー上で、 **[ツール]**&gt;**[AD FS の管理]** を選択します。
2. ナビゲーション ウィンドウで、 **[サービス]**&gt;**[要求記述]** を選択します。
3. **[操作]** の **[要求記述の追加]** を選択します。
4. **[要求記述の追加]** ウィンドウで、次の値を指定します。

    - **[表示名]** : 永続的な識別子
    - **要求識別子**: `urn:oasis:names:tc:SAML:2.0:nameid-format:persistent`
    - **[このフェデレーション サービスが受け付けることができる要求の種類としてこの要求記述をフェデレーション メタデータで公開する]** のチェック ボックスをオンにします。
    - **[このフェデレーション サービスが送信できる要求の種類としてこの要求記述をフェデレーション メタデータで公開する]** のチェック ボックスをオンにします。
5. **[OK]** を選択します。

#### 証明書利用者信頼を追加する

1. AD FS サーバーで、**[ツール]**&gt;**[AD FS の管理]** に移動します。
2. ナビゲーション ウィンドウで、**[証明書利用者信頼]** を選択します。
3. **[操作]** の **[証明書利用者信頼の追加]** を選択します。
4. **証明書利用者信頼の追加**ウィザードで、**[要求に対応する]** を選択してから、**[開始]** を選択します。
5. **[データ ソースの選択]** セクションで、**[オンラインまたはローカル ネットワークで公開されている証明書利用者についてのデータをインポートする]** のチェック ボックスを選択します。 次のフェデレーション メタデータ URL を入力します: `https://nexus.microsoftonline-p.com/federationmetadata/saml20/federationmetadata.xml`。 **[次へ]** を選択します。
6. その他の設定は、既定のオプションのままにしておきます。 **[次へ]** を選択して続行し、最後に **[閉じる]** を選択してウィザードを閉じます。
7. **[AD FS の管理]** の **[証明書利用者信頼]** で、作成した証明書利用者信頼を右クリックして、**[プロパティ]** を選びます。
8. **[監視]** タブで、**[証明書利用者を監視する]** ボックスをオフにします。
9. **Identifiers** タブで、サービス パートナーのMicrosoft Entra テナントのテナント ID を使用して、`https://login.microsoftonline.com/<tenant ID>/` ボックスに「」と入力します。 **[追加]** を選択します。

    注

    テナント ID の後のスラッシュ (/) を必ず含めてください (例: `https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/`)。
10. **[OK]** を選択します。

#### 要求規則を作成する

1. 作成した証明書利用者信頼を右クリックしてから、**[要求発行ポリシーの編集]** を選択します。
2. **要求規則の編集**ウィザードで、**[規則の追加]** を選択します。
3. **[要求規則テンプレート]** で、**[LDAP 属性を要求として送信]** を選択します。
4. **[要求規則の構成]** で、次の値を指定します。

    - **[要求規則名]** : 電子メール要求規則
    - **Attribute store**: Active Directory
    - **[LDAP 属性]** : 電子メール アドレス
    - **[出力方向の要求の種類]**: メール アドレス
5. **[完了]** を選択します。
6. **[規則の追加]** を選択します。
7. **[要求規則テンプレート]** で、**[入力方向の要求を変換]** を選択してから、**[次へ]** を選択します。
8. **[要求規則の構成]** で、次の値を指定します。

    - **[要求規則名]** : 電子メール変換規則
    - **受信要求の種類**: 電子メールアドレス
    - **[出力方向の要求の種類]**: 名前 ID
    - **[出力方向の名前 ID 形式]**: 永続的な識別子
    - **[すべての要求値をパススルーする]** を選択します。
9. **[完了]** を選択します。
10. **[要求規則の編集]** ペインに新しい規則が表示されます。 **[適用]** を選びます。
11. **[OK]** を選択します。 これで、SAML 2.0 プロトコルを使用したフェデレーション用に AD FS サーバーが構成されました。

### AD FS を WS-Fed フェデレーション用に構成する

Microsoft Entra B2B は、以下に示す特定の要件で WS-Fed プロトコルを使用する IdP とフェデレーションするように構成できます。 現在、Microsoft Entra External IDとの互換性をテストした 2 つの WS-Fed プロバイダーは、AD FS と Shibboleth です。 ここでは、WS-Fed IdP の例として Active Directory Federation Services (AD FS) を使用します。 WS-Fed 準拠プロバイダーとMicrosoft Entra External IDの間で証明書利用者の信頼を確立する方法の詳細については、Microsoft Entra ID プロバイダーの互換性に関するドキュメントをダウンロードしてください。

フェデレーションを設定するには、IdP からの WS-Fed メッセージにおいて以下の属性が受け取られる必要があります。 これらの属性は、オンライン セキュリティ トークン サービスの XML ファイルにリンクするか手動で入力することによって、構成できます。 「[Create a test AD FS instance](https://medium.com/in-the-weeds/create-a-test-active-directory-federation-services-3-0-instance-on-an-azure-virtual-machine-9071d978e8ed)」(AD FS テスト インスタンスの作成) のステップ 12 では、AD FS エンドポイントを検索する方法や、メタデータ URL (たとえば `https://fs.iga.azure-test.net/federationmetadata/2007-06/federationmetadata.xml`) を生成する方法について説明しています。

| 属性 | 値 |
| --- | --- |
| PassiveRequestorEndpoint | `https://login.microsoftonline.com/login.srf` |
| 対象ユーザー | `urn:federation:MicrosoftOnline` |
| 発行者 | パートナー IdP の発行者 URI (たとえば `http://www.example.com/exk10l6w90DHM0yi...`) |

IdP によって発行される WS-Fed トークンに必須の要求:

| 属性 | 値 |
| --- | --- |
| イミュータブルID (不変ID) | `http://schemas.microsoft.com/LiveID/Federation/2008/05/ImmutableID` |
| メールアドレス | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` |

次のセクションでは、WS-Fed IdP の例として、AD FS を使用して必須の属性とクレームを構成する方法について説明します。

#### 開始する前に

この手順を開始する前に、AD FS サーバーが既に設定されていて稼働している必要があります。

#### 証明書利用者信頼を追加する

1. AD FS サーバー上で、 **[ツール]**&gt;**[AD FS の管理]** に移動します。
2. ナビゲーション ウィンドウで、 **[信頼関係]**&gt;**[証明書利用者信頼]** を選択します。
3. **[操作]** の **[証明書利用者信頼の追加]** を選択します。
4. 証明書利用者信頼の追加ウィザードで、**[要求に対応する]** を選んでから、[開始] を選びます。
5. [**データ ソースの選択**] セクションで、[**証明書利用者についてのデータを手動で入力する**] を選択してから、[**次へ**] をクリックします。
6. **[表示名の指定]** ページで、**[表示名]** に名前を入力します。 必要に応じて、この証明書利用者信頼の説明を **[メモ]** セクションに入力できます。 **[次へ]** を選択します。
7. **[証明書の構成]** ページでは、トークン暗号化証明書がある場合は、必要に応じて、**[参照]** をクリックして証明書ファイルを指定します。 **[次へ]** を選択します。
8. **[URL の構成]** ページでは、**[WS-Federation のパッシブ プロトコルのサポートを有効にする]** チェック ボックスをオンにします。 **[証明書利用者 WS-Federation パッシブ プロトコルの URL]** に、次の URL を入力します: `https://login.microsoftonline.com/login.srf`
9. **[次へ]** を選択します。
10. **[識別子の構成]** ページで、次の URL を入力して **[追加]** を選びます。 2 番目の URL に、サービス パートナーのMicrosoft Entra テナントのテナント ID を入力します。

    - `urn:federation:MicrosoftOnline`
    - `https://login.microsoftonline.com/<tenant ID>/`

    注

    テナント ID の後のスラッシュ (/) を必ず含めてください (例: `https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/`)。
11. **[次へ]** を選択します。
12. **[アクセス制御ポリシーの選択]** ページで、ポリシーを選んでから、**[次へ]** を選びます。
13. **[信頼の追加の準備完了]** ページで、設定を確認し、**[次へ]** を選んで証明書利用者信頼の情報を保存します。
14. **[完了]** ページで、**[閉じる]** を選びます。
15. リライアントパーティトラストを選択し、[**要求発行ポリシーの編集**] を選択します。

#### 要求規則を作成する

1. 作成した証明書利用者信頼を選んでから、**[要求発行ポリシーの編集]** を選びます。
2. **[規則の追加]** を選択します。
3. **[LDAP 属性を要求として送信]** を選んで、**[次へ]** を選びます。
4. **[要求規則の構成]** で、次の値を指定します。

    - **[要求規則名]** : 電子メール要求規則
    - **Attribute store**: Active Directory
    - **[LDAP 属性]** : 電子メール アドレス
    - **[出力方向の要求の種類]**: メール アドレス
5. **[完了]** を選択します。
6. 同じ**要求規則の編集**ウィザードで、**[規則の追加]** を選択します。
7. **[カスタム規則を使用して要求を送信]** を選んでから、**[次へ]** を選びます。
8. **[要求規則の構成]** で、次の値を指定します。

    - **[要求規則名]** : 不変 ID の発行
    - **Custom rule**: `c:[Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsaccountname"] => issue(store = "Active Directory", types = ("http://schemas.microsoft.com/LiveID/Federation/2008/05/ImmutableID"), query = "samAccountName={0};objectGUID;{1}", param = regexreplace(c.Value, "(?<domain>[^\\]+)\\(?<user>.+)", "${user}"), param = c.Value);`
9. **[完了]** を選択します。
10. **[OK]** を選択します。 これで、WS-Fed を使用したフェデレーション用に AD FS サーバーが構成されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/direct-federation-overview"} -->
## SAML/WS-Fed ID プロバイダーのフェデレーション - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-overview
- Service: entra-external-id / external
- Article date: 2026-04-21
- Summary: 外部ユーザーのセルフサービス サインアップと招待の利用のための外部組織の SAML/WS-Fed ID プロバイダー (IdP) とのフェデレーションについて説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra の従業員と外部テナントでは、SAML または WS-Fed ID プロバイダー (IdP) を使用する他の組織とのフェデレーションを設定できます。 外部組織のユーザーは、独自の IdP マネージド アカウントを使用して、招待の利用中またはセルフサービス サインアップ時に、新しい Microsoft Entra 資格情報を作成しなくても、アプリまたはリソースにサインインできます。 ユーザーは、アプリにサインアップまたはサインインするときに IdP にリダイレクトされ、正常にサインインすると Microsoft Entra に戻ります。

複数のドメインを 1 つのフェデレーション構成に関連付けることができます。 パートナーのドメインは、Microsoft Entra の検証済みまたは未確認のいずれかになります。

注

2 つの Microsoft Entra テナント間の直接 SAML/WS-Fed フェデレーションは、サポートまたは推奨される構成ではありません。 2 つの Entra テナント間で SAML 信頼が技術的に構成されている場合でも、Microsoft Entra は SAML ではなくネイティブの Entra-to-Entra B2B コラボレーション モデルを引き続き使用します。

SAML/WS-Fed IdP フェデレーションを設定するには、テナントと外部組織の IdP の両方で構成が必要です。 場合によっては、パートナーが DNS テキスト レコードを更新する必要があります。 また、必要な要求と証明書利用者の信頼を使用して IdP を更新する必要もあります。

### SAML/WS-Fed IdP フェデレーションを使用したユーザー認証

パートナーの SAML/WS-Fed IdP とのフェデレーションを設定したら、ユーザーは [サインアップ] または [サインインに使用する] オプションを選択して **サインアップ** または **サインイン** できます。 ID プロバイダーにリダイレクトされ、正常にサインインすると Microsoft Entra に返されます。

外部テナントの場合、ユーザーのサインイン電子メールは、SAML フェデレーション中に設定された定義済みのドメインと一致する必要はありません。 ユーザーが外部テナントにアカウントを持っていなくても、いずれかの外部 ID プロバイダーの定義済みドメインと一致する電子メール アドレスをサインイン ページに入力すると、その ID プロバイダーで認証するようにリダイレクトされます。

#### 検証済みドメインと未確認ドメイン

ユーザーのサインイン エクスペリエンスは、パートナーのドメインが Microsoft Entra で検証されているかどうかによって異なります。

- **未確認のドメイン** は、Microsoft Entra ID で DNS 検証されていないドメインです。 フェデレーション後、ユーザーは未確認のドメインの資格情報を使用してサインインできます。
- **アンマネージド (電子メール検証済みまたは "バイラル") テナント** は、ユーザーが招待を利用するか、現在存在しないドメインを使用して Microsoft Entra ID のセルフサービス サインアップを実行したときに作成されます。 フェデレーション後、ユーザーはアンマネージド テナントの資格情報を使用してサインインできます。
- **Microsoft Entra ID 検証済みドメイン** は、テナントが [管理者の引き継ぎ](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-admin-takeover)を受けたドメインを含め、Microsoft Entra で DNS 検証されたドメインです。 フェデレーション後:

    - セルフサービス サインアップの場合、ユーザーは独自のドメイン資格情報を使用できます。
    - 招待を利用する場合、Microsoft Entra ID はプライマリ IdP のままです。 従業員テナント内で、[引き換え順序を変更する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#configurable-redemption)方法で、招待の引き換えに対応するフェデレーション IdP に優先順位を付けることができます。

    注

    引き換え順序の変更は、現在、外部テナントまたはクラウド間ではサポートされていません。

#### フェデレーションが現在の外部ユーザーに与える影響

外部ユーザーが既に招待を利用している場合、またはセルフサービス サインアップを使用している場合、フェデレーションを設定しても認証方法は変更されません。 元の認証方法 (ワンタイム パスコードなど) を引き続き使用します。 未確認のドメインのユーザーがフェデレーションを使用し、その組織が後で Microsoft Entra に移行した場合でも、フェデレーションを引き続き使用します。

従業員テナントでの B2B コラボレーションでは、既存のユーザーが現在のサインイン方法を引き続き使用するため、新しい招待を既存のユーザーに送信する必要はありません。 ただし、[ユーザーの引き換えの状態をリセット](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)することは可能です。 ユーザーが次回アプリにアクセスすると、引き換え手順が繰り返され、フェデレーションに切り替えることができます。

#### 従業員テナント内のサインイン エンドポイント

フェデレーションが従業員テナントで設定されている場合、フェデレーション組織のユーザーは、[共通エンドポイント](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#redemption-process-and-sign-in-through-a-common-endpoint) (つまり、テナント コンテキストを含まない一般的なアプリ URL) を使用して、マルチテナントアプリまたは Microsoft ファーストパーティ アプリにサインインできます。 サインイン プロセス中に、ユーザーは **サインイン オプション**を選択し、次に **組織にサインイン**を選択します。 彼らは組織の名前を入力し、自分自身の資格情報を使用してサインインを続けます。

SAML/WS-Fed IdP フェデレーション ユーザーは、テナント情報を含むアプリケーション エンドポイントを使用することもできます。次に例を示します。

- `https://myapps.microsoft.com/?tenantid=<your tenant ID>`
- `https://myapps.microsoft.com/<your verified domain>.onmicrosoft.com`
- `https://portal.azure.com/<your tenant ID>`

また、テナント情報 (`https://myapps.microsoft.com/signin/X/<application ID?tenantId=<your tenant ID>`など) を含めることで、アプリケーションまたはリソースへの直接リンクをユーザーに付与することもできます。

### SAML/WS-Fed フェデレーションに関する主な考慮事項

#### パートナー IdP の要件

SAML/WS-Fed IdP フェデレーションを設定するには、テナントと外部組織の IdP の両方で構成が必要です。 パートナーの IdP によっては、パートナーが自身の DNS レコードを更新して、お客様とのフェデレーションを有効にすることが必要な場合があります。 「[手順 1: パートナーが DNS テキスト レコードを](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation#step-1-determine-if-the-partner-needs-to-update-their-dns-text-records)更新する必要があるかどうかを判断する」を参照してください。

パートナーは、必要な要求と証明書利用者の信頼を使用して、自らの IdP を更新する必要があります。 外部フェデレーションに対して Microsoft Entra ID によって送信された SAML 要求の発行者 URL はテナントエンドポイントになりましたが、以前はグローバル エンドポイントでした。 グローバル エンドポイントとの既存のフェデレーションは引き続き機能します。 ただし、新しいフェデレーションの場合は、外部 SAML または WS-Fed IdP の対象ユーザーをテナントエンドポイントに設定します。 必要な属性と要求については、[SAML 2.0 セクションの](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation#to-configure-a-saml-20-identity-provider) と [WS-Fed セクション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation#to-configure-a-ws-fed-identity-provider) を参照してください。

#### 署名証明書の有効期限

IdP の設定でメタデータ URL を指定した場合、署名証明書が有効期限切れになると、Microsoft Entra ID によって自動的に更新されます。 ただし、証明書が有効期限切れになる前に何らかの理由でローテーションされた場合、またはメタデータ URL を指定しなかった場合には、Microsoft Entra ID による更新はできません。 この場合、署名証明書を手動で更新する必要があります。

#### セッションの有効期限

Microsoft Entra セッションの有効期限が切れたり無効になったり、フェデレーション IdP で SSO が有効になっている場合、ユーザーは SSO を体験します。 フェデレーション ユーザーのセッションが有効な場合、ユーザーに再度サインインのダイアログが表示されることはありません。 それ以外の場合、ユーザーはサインインのために IdP にリダイレクトされます。

#### 部分的に同期されたテナント

フェデレーションでは、部分的に同期されたテナントによって発生するサインインの問題は解決されません。パートナーのオンプレミス ユーザー ID がクラウド内の Microsoft Entra と完全に同期されていません。 これらのユーザーは B2B 招待でサインインできないため、代わりに [メール ワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode) 機能を使用する必要があります。 SAML/WS-Fed IdP フェデレーション機能は、独自の IdP で管理される組織アカウントを持ち、Microsoft Entra が存在しないパートナー向けです。

#### B2B ゲスト アカウント

フェデレーションは、ディレクトリ内の B2B ゲスト アカウントの必要性を置き換えるわけではありません。 B2B コラボレーションでは、使用される認証またはフェデレーション方法に関係なく、従業員テナント ディレクトリ内のユーザーに対してゲスト アカウントが作成されます。 このユーザー オブジェクトを使用すると、アプリケーションにアクセス権を付与したり、ロールを割り当てたり、セキュリティ グループのメンバーシップを定義したりできます。

#### 署名された認証トークン

現在のところ、Microsoft Entra SAML/WS-Fed フェデレーション機能では、署名された認証トークンを SAML ID プロバイダーに送信することはサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/external-collaboration-settings-configure"} -->
## 外部コラボレーションを構成する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra 外部 ID で外部コラボレーション設定を構成する方法について学習します。 ゲスト ユーザー アクセスを制御し、ゲストを招待できるユーザーを指定し、B2B コラボレーションのドメイン制限を管理します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

外部コラボレーション設定を使用すると、組織内のどのロールが、B2B コラボレーションのために外部ユーザーを招待できるかを指定できます。 これらの設定には、[特定のドメインを許可またはブロック](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)するためのオプションや、外部のゲストユーザーが Microsoft Entra ディレクトリで表示できる内容を制限するオプションも含まれています。 次のオプションを利用できます。

- **[ゲスト ユーザーのアクセスを決定する]**: Microsoft Entra 外部 ID では、外部のゲスト ユーザーが表示できる Microsoft Entra ディレクトリの内容を制限できます。 たとえば、グループ メンバーシップのゲスト ユーザーによる表示を制限したり、ゲストには自分のプロファイル情報の表示だけを許可したりすることができます。
- **[ゲストを招待できるユーザーを指定する]**: 既定では、組織内のすべてのユーザー (B2B コラボレーションのゲスト ユーザーを含む) が、B2B コラボレーションに外部ユーザーを招待できます。 招待を送信する機能を制限する場合は、ユーザー全員に対して招待をオンまたはオフにすることや、特定のロールに対して招待を制限することができます。
- **[ユーザー フローによるゲストのセルフサービス サインアップを有効にする]**: 構築するアプリケーションのために、ユーザーがアプリにサインアップして新しいゲスト アカウントを作成できるようにするユーザー フローを作成できます。 外部のコラボレーション設定でこの機能を有効にしてから、[セルフサービス サインアップのユーザー フローをアプリに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow)ことができます。
- **[ドメインを許可またはブロックする]**: 指定したドメインへの招待を許可または拒否するために、コラボレーションの制限を使用できます。 詳細については、[ドメインの許可またはブロック](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)に関するページを参照してください。

他の Microsoft Entra 組織との B2B コラボレーションの場合、[クロステナント アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)を見直して、インバウンドとアウトバウンドの B2B コラボレーションを確認し、特定のユーザー、グループ、アプリケーションへのアクセスのスコープを設定する必要もあります。

メモ

Microsoft 2025 年 7 月に B2B コラボレーションのゲスト ユーザー サインイン エクスペリエンスの更新プログラムのロールアウトを開始し、ロールアウトは 2025 年末までに完了しました。 この更新プログラムでは、ゲスト ユーザーは自分の組織のサインイン ページにリダイレクトされ、資格情報が提供されます。 ゲスト ユーザーには、ホーム テナントのブランドと URL エンドポイントが表示されます。 自分の組織で認証が成功すると、サインインを完了するためにゲスト ユーザーが組織に返されます。 次の例では、Woodgrove Groceries のブランドが左側に表示されます。 右側の例では、ユーザーのホーム テナントのカスタム ブランドが表示されます。

[Image: ゲスト ユーザーのログイン フローを示すスクリーンショット。]

### ポータルで設定を構成する

Microsoft Entra 管理センターには、グローバル管理者や外部 ID プロバイダー管理者などの外部コラボレーション設定を更新できるロールが必要です。 Microsoft Graphを使用する場合、個々の設定で使用できる特権の低いロールが考えられます。 この記事の後で説明するMicrosoft Graph を使用した構成設定を参照してください。

#### ゲスト ユーザー アクセスを構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**外部コラボレーション設定**に移動します。
3. **[ゲスト ユーザーのアクセス]** で、ゲスト ユーザーに付与するアクセスのレベルを選択します。

    [Image: ゲスト ユーザー アクセスの設定を示すスクリーンショット。]

    - **ゲスト ユーザーには、メンバーと同じアクセス権があります (最も包括的)**: このオプションを選択すると、ゲストがメンバー ユーザーと同じように Microsoft Entra リソースとディレクトリ データにアクセスできるようになります。
    - **Guest users have limited access to properties and memberships of directory objects (ゲスト ユーザーに対してディレクトリ オブジェクトのプロパティとメンバーシップへのアクセスを制限する)** :(デフォルト) この設定を選択すると、ゲストは、特定のディレクトリ タスク (ユーザー、グループ、またはその他のディレクトリ リソースを列挙するなど) を実行できなくなります。 ゲストは、非表示でないすべてのグループのメンバーシップを表示できます。 [既定のゲスト アクセス許可の詳細について説明します](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#member-and-guest-users)。
    - **Guest user access is restricted to properties and memberships of their own directory objects (most restrictive) (ゲスト ユーザーのアクセスを、自分のディレクトリ オブジェクトのプロパティとメンバーシップに制限する (最も厳しい制限))** :この設定では、ゲストは自分のプロファイルのみにアクセスできます。 ゲストは、他のユーザーのプロファイル、グループ、またはグループ メンバーシップを参照することはできません。

#### ゲスト招待の設定を構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**外部コラボレーション設定**に移動します。
3. **ゲスト招待の設定** で、適切な設定を選択します。

    [Image: ゲスト招待の設定を示すスクリーンショット。]

    - **ゲストと非管理者を含む組織内のすべてのユーザーがゲスト ユーザーを招待できる (最も包括的)**: 組織内のゲストが、組織のメンバーではないユーザーも含めて他のゲストを招待できるようにするには、このオプション ボタンを選択します。
    - **メンバー アクセス許可を持つゲストを含むメンバー ユーザーと特定の管理者ロールに割り当てられたユーザーがゲスト ユーザーを招待できる**: メンバー ユーザーと特定の管理者の役割を持つユーザーがゲストを招待できるようにするには、このオプション ボタンを選択します。
    - **特定の管理者の役割に割り当てられているユーザーのみがゲスト ユーザーを招待できる**: [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)または[ゲスト招待元](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#guest-inviter)ロールを持つユーザーだけがゲストを招待できるようにするには、このオプション ボタンを選択します。
    - **管理者を含む組織内のすべてのユーザーがゲスト ユーザーを招待できない (最も制限的)** : 組織内の全員がゲストを招待できないようにするには、このラジオ ボタンを選択します。

#### ゲストのセルフサービス サインアップを構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**外部コラボレーション設定**に移動します。
3. ユーザーがアプリにサインアップできるようにユーザー フローを作成したい場合は、**ユーザー フローによるゲスト セルフサービス サインアップを有効にする** で **[はい]** を選択します。 この設定の詳細については、[アプリへのセルフサービス サインアップ ユーザー フローの追加](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow)に関するページを参照してください。

    [Image: ユーザー フローによるセルフサービス サインアップの設定を示すスクリーンショット。]

#### 外部ユーザーの脱退設定を構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**外部コラボレーション設定**に移動します。
3. **[外部ユーザーの脱退設定]** で、外部ユーザーが組織から自分を削除できるかどうかを制御できます。

    - **はい**: ユーザーは、管理者またはプライバシー連絡先からの承認なしに、組織を脱退することが可能です。
    - **いいえ**: ユーザーは自分で組織を離れることはできません。 管理者またはプライバシー連絡先に連絡して組織からの削除を依頼するようにガイドするメッセージが表示されます。

    重要

    **外部ユーザーの脱退設定**は、Microsoft Entra テナントに[プライバシー情報を追加した](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area)場合にのみ構成できます。 それ以外の場合、この設定は使用できなくなります。

    [Image: ポータルの外部ユーザーの脱退設定を示すスクリーンショット。]

#### コラボレーションの制限 (ドメインを許可またはブロックする) を構成するには

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**外部コラボレーション設定**に移動します。
3. **[コラボレーションの制限]** で、指定したドメインへの招待を許可するか拒否するかを選択し、テキスト ボックスに特定のドメイン名を入力できます。 複数ドメインの場合は、それぞれのドメインを新しい行に入力します。 詳細については、「[B2B ユーザーに対する特定組織からの招待を許可またはブロックする](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)」を参照してください。

    [Image: コラボレーションの制限の設定を示すスクリーンショット。]

### Microsoft Graph を使用して設定を構成する

外部コラボレーションの設定は、Microsoft Graph APIを使用して構成できます。

- **ゲスト ユーザーのアクセス制限**と**ゲスト招待の制限**には、[authorizationPolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/authorizationpolicy?view=graph-rest-1.0&preserve-view=true) リソースの種類を使用します。
- [ **ユーザー フローによるゲスト セルフサービス サインアップを有効にする]** 設定では、 [authenticationFlowsPolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationflowspolicy?view=graph-rest-1.0&preserve-view=true) リソースの種類を使用します。
- **外部ユーザーの休暇設定**では、[externalidentitiespolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/externalidentitiespolicy?view=graph-rest-1.0&preserve-view=true) リソースの種類を使用します。
- メールのワンタイム パスコード設定 (Microsoft Entra 管理センターの **[すべての ID プロバイダー]** ページに表示) には、[emailAuthenticationMethodConfiguration](https://learn.microsoft.com/ja-jp/graph/api/resources/emailAuthenticationMethodConfiguration?view=graph-rest-1.0&preserve-view=true) リソースの種類を使用します。

### ゲスト招待元ロールをユーザーに割り当てる

[ゲスト招待元](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#guest-inviter)ロールを使用すると、個々のユーザーにより高い特権管理者の役割を割り当てなくても、ゲストを招待する機能を付与できます。 ゲスト招待ロールがあるユーザーは、**[特定の管理者ロールに割り当てられているユーザーのみがゲスト ユーザーを招待できる]** (**[Guest invite settings] (ゲスト招待設定)** の下) が選択されていても、ゲストを招待できます。

Microsoft Graph PowerShell を使用してユーザーを `Guest Inviter` ロールに追加する方法を示す例を次に示します:

```powershell

Import-Module Microsoft.Graph.Identity.DirectoryManagement

$roleName = "Guest Inviter"
$role = Get-MgDirectoryRole | where {$_.DisplayName -eq $roleName}
$userId = <User Id/User Principal Name>

$DirObject = @{
  "@odata.id" = "https://graph.microsoft.com/v1.0/directoryObjects/$userId"
  }

New-MgDirectoryRoleMemberByRef -DirectoryRoleId $role.Id -BodyParameter $DirObject

```

### B2B ユーザーのサインイン ログ

B2B ユーザーが共同作業を行うリソース テナントにサインインすると、ホーム テナントとリソース テナントの両方にサインイン ログが生成されます。 これらのログには、使用されているアプリケーション、メール アドレス、テナント名、ホーム テナントとリソース テナントの両方のテナント ID などの情報が含まれます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/external-identities-overview"} -->
## Microsoft Entra 外部 ID の概要 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra External ID を使用して、B2B コラボレーションや Azure AD B2C など、組織外のユーザーと連携するためのソリューションを比較します。

Microsoft Entra External ID は、組織外のユーザーと連携するための強力なソリューションを組み合わせたものです。 外部 ID 機能を使用すると、外部 ID がアプリとリソースに安全にアクセスできるようにすることができます。

外部パートナー、コンシューマー、ビジネス顧客のいずれと連携している場合でも、ユーザーは自分の ID を持ち込むことができます。 これらの ID には、企業または政府が発行したアカウントや、Google や Facebook などのソーシャル ID プロバイダーを含めることができます。

[Image: 外部 ID の概要を示す図。]

これらのシナリオは、外部 ID のスコープ内に含まれます。

- コンシューマー アプリを作成する組織または開発者の場合は、外部 ID を使用して、認証と顧客 ID とアクセス管理 (CIAM) をすばやくアプリケーションに追加します。 アプリを登録し、カスタマイズされたサインイン エクスペリエンスを作成し、"外部" 構成の Microsoft Entra テナントでアプリ ユーザーを 管理します。 このテナントは、従業員や組織のリソースとは別です。
- 従業員がビジネス パートナーやゲストと共同作業できるようにする場合は、外部 ID で B2B コラボレーションを使用します。 招待またはセルフサービス サインアップを使用して、エンタープライズ アプリへの安全なアクセスを許可します。 従業員と組織のリソースを含む Microsoft Entra テナント ( *従業員* 構成のテナント) に対してゲストが持つアクセス レベルを決定します。

外部 ID は、次の両方に対応する柔軟なソリューションです。

- 認証と CIAM が必要なコンシューマー向けアプリ開発者
- セキュリティで保護された B2B コラボレーションを求める企業

### コンシューマーおよびビジネス顧客向けのアプリをセキュリティで保護する

組織や開発者は、コンシューマーやビジネス顧客にアプリを発行するときに、[外部テナントの外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam) を CIAM ソリューションとして使用できます。

外部構成で個別の Microsoft Entra テナントを作成して、従業員とは別にアプリとユーザー アカウントを管理できます。 このテナント内で、カスタム ブランドのサインアップ エクスペリエンスとユーザー管理機能を構成できます。

- 顧客が従うサインアップ手順と、ユーザーが使用できるサインイン方法を定義するセルフサービス登録フローを設定します。 これらの方法には、メールとパスワード、ワンタイム パスコード、Google または Facebook のソーシャル アカウントが含まれます。
- テナントのブランド設定を構成して、アプリにサインインするユーザー向けのカスタム エクスペリエンスを作成します。 これらの設定を使用すると、アプリ全体にサインインするための独自の背景画像、色、会社のロゴ、テキストを追加できます。
- 組み込みのユーザー属性から選択するか、独自のカスタム属性を追加して、サインアップ時に顧客から情報を収集します。
- ユーザー アクティビティとエンゲージメント データを分析して、戦略的な意思決定を支援し、ビジネスの成長を促進できる貴重な分析情報を明らかにします。

外部 ID を使用すると、顧客は既に持っている ID を使用してサインインできます。 顧客がアプリケーションを使用するときにサインアップおよびサインインする方法をカスタマイズおよび制御できます。 これらの CIAM 機能は 外部 ID に組み込まれているため、セキュリティ、コンプライアンス、スケーラビリティの強化などの Microsoft Entra プラットフォーム機能の利点も得られます。

詳細については、 [外部テナントの Microsoft Entra 外部 ID の概要を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)参照してください。

### ビジネス ゲストとコラボレーションする

[外部 ID B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を使用すると、従業員は外部のビジネス パートナーと共同作業を行うことができます。 自分の資格情報を使用して Microsoft Entra 組織にサインインするようにすべてのユーザーを招待して、共有するアプリやリソースにアクセスできるようにします。

ビジネス ゲストが Office 365 アプリ、サービスとしてのソフトウェア (SaaS) アプリ、および基幹業務アプリにアクセスできるようにする必要がある場合は、B2B コラボレーションを使用します。 ビジネス ゲストに関連付けられている資格情報はありません。 代わりに、ホーム組織または ID プロバイダーで認証を行い、組織はゲスト コラボレーションの資格を確認します。

コラボレーションのためにビジネス ゲストを組織に追加するには、さまざまな方法があります。

- Microsoft Entra アカウント、Microsoft アカウント、または有効にしたソーシャル ID (Google など) を使用して、共同作業を行うようユーザーを招待します。 管理者は、Microsoft Entra 管理センターまたは PowerShell を使用して、ユーザーを共同作業に招待できます。 ユーザーは、職場、学校、またはその他の電子メール アカウントで簡単な引き換えプロセスを使用して、共有リソースにサインインします。
- セルフサービス サインアップ ユーザー フローを使用して、ゲストがアプリケーション自体にサインアップできます。 このエクスペリエンスは、職場、学校、またはソーシャル ID (Google や Facebook など) によるサインアップを許可するようにカスタマイズできます。 サインアップ プロセス中にユーザーに関する情報を収集することもできます。
- [外部ユーザーの ID とアクセスを大規模](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)に管理するための機能である [Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#how-access-works-for-external-users)を使用します。 この機能を使用すると、アクセス要求ワークフロー、アクセス割り当て、レビュー、有効期限を自動化できます。

ユーザー オブジェクトは、従業員に使用するのと同じディレクトリに、ビジネス ゲスト用に作成されます。 このユーザー オブジェクトは、ディレクトリ内の他のユーザー オブジェクトと同様に管理できます。 たとえば、グループに追加できます。 ユーザーが既存の資格情報を認証に使用できるようにしながら、承認のためにユーザー オブジェクトにアクセス許可を割り当てることができます。

[テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)は、他の Microsoft Entra 組織とのコラボレーションおよび Microsoft Azure クラウド間のコラボレーションを管理するために使用できます。 Microsoft Entra 以外の外部ユーザーや組織とのコラボレーションには、 [外部コラボレーション設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)を使用します。

### 従業員と外部テナントとは

*テナント*は、Microsoft Entra ID 専用および信頼されたインスタンスです。 これには、登録済みのアプリやユーザーのディレクトリなど、組織のリソースが含まれています。 テナントを構成するには、組織がテナントを使用する方法と管理するリソースに応じて、次の 2 つの方法があります。

- *ワークフォース* テナントの構成は、従業員、内部ビジネス アプリ、およびその他の組織リソースを含む標準の Microsoft Entra テナントです。 従業員テナントでは、内部ユーザーは B2B コラボレーションを使用して外部のビジネス パートナーやゲストと共同作業できます。
- *外部*テナント構成は、コンシューマーまたはビジネスユーザーに発行するアプリ専用です。 この個別のテナントは、標準の Microsoft Entra テナント モデルに従いますが、コンシューマー シナリオ用に構成されています。 アプリの登録と、コンシューマーアカウントまたは顧客アカウントのディレクトリが含まれます。

詳細については、「[Microsoft Entra 外部 ID のワークフォースと外部テナントの構成](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations)」を参照してください。

### 外部 ID の機能セットの比較

次の表は、外部 ID で有効にできるシナリオを比較したものです。

| シナリオ | ワークフォース テナントの外部 ID | 外部テナントの外部 ID |
| --- | --- | --- |
| 主なシナリオ | 従業員がビジネス ゲストと共同作業できるようにします。 ゲストが優先 ID を使用して、Microsoft Entra 組織内のリソースにサインインできるようにします。 外部 ID を使用すると、SaaS アプリやカスタム開発アプリなど、Microsoft アプリケーションまたは独自のアプリケーションにアクセスできます。  "例": 外部ユーザーを Microsoft アプリにサインインするか、Teams 内でゲスト メンバーになるよう招待します。 | ID エクスペリエンスに外部 ID を使用して、外部コンシューマーとビジネス 顧客にアプリを発行します。 外部 ID は、最新の SaaS またはカスタム開発アプリ (Microsoft アプリではなく) の ID とアクセス管理を提供します。  "例": コンシューマー モバイル アプリのユーザー向けにカスタマイズされたサインイン エクスペリエンスを作成し、アプリの使用状況を監視します。 |
| 対象: | サプライヤー、パートナー、ベンダーなどの外部組織のビジネス パートナーとの共同作業。 これらのユーザーは、Microsoft Entra ID または管理された IT を持っている場合もあり、持っていない場合もあります。 | アプリのコンシューマーとビジネス ユーザー。 これらのユーザーは、外部アプリとユーザー用に構成された Microsoft Entra テナントで管理されます。 |
| ユーザー管理 | 従業員と同じ従業員テナントで B2B コラボレーション ユーザーを管理しますが、通常はゲスト ユーザーとして注釈を付けます。 ゲスト ユーザーは、従業員と同じ方法で管理し、同じグループに追加できます。 [クロステナント アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)を使用して、B2B コラボレーションにアクセスできるユーザーを決定できます。 | アプリケーションのコンシューマー用に作成する外部テナントでアプリ ユーザーを管理します。 外部テナントのユーザーには、従業員テナントのユーザーとは異なる[既定のアクセス許可](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)があります。 外部テナントは、組織の従業員ディレクトリとは別です。 |
| シングル サインオン (SSO) | Microsoft Entra に接続されているすべてのアプリへの SSO がサポートされています。 たとえば、Microsoft 365 またはオンプレミスのアプリケーションや、Salesforce、Workday などの SaaS アプリへのアクセスを提供できます。 | 外部テナントに登録されているアプリへの SSO がサポートされています。 Microsoft 365 やその他の Microsoft SaaS アプリへの SSO はサポートされていません。 |
| 会社のブランド化 | 認証エクスペリエンスの既定の状態は、Microsoft の設計です。 管理者は、会社のブランドを使用してゲスト サインイン エクスペリエンスをカスタマイズできます。 | 外部テナントの既定のブランド化はニュートラルであり、既存の Microsoft ブランドは含まれません。 管理者は、組織またはアプリケーションごとにブランドをカスタマイズできます。 [詳細情報。](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-branding-customers) |
| Microsoft クラウド設定 | [サポートされています](https://learn.microsoft.com/ja-jp/entra/external-id/cross-cloud-settings)。 | 該当なし。 |
| 権利管理 | [サポートされています](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)。 | 該当なし。 |

### 関連テクノロジ

いくつかの Microsoft Entra テクノロジは、外部ユーザーや組織とのコラボレーションに関連しています。 外部 ID コラボレーション モデルを設計する際には、これらのその他の機能を検討してください。

#### B2B 直接接続

B2B 直接接続を使用して、他の Microsoft Entra 組織との双方向の信頼関係を作成し、Teams Connect 共有チャネル機能を有効にすることができます。 この機能を使用すると、ユーザーはチャット、通話、ファイル共有、アプリ共有のために Teams 共有チャネルにシームレスにサインインできます。

2 つの組織が相互に B2B 直接接続を有効にした場合、ユーザーはホーム組織で認証を行い、アクセスのためにリソース組織からトークンを受け取ります。 B2B コラボレーションとは異なり、B2B 直接接続ユーザーはワークフォース ディレクトリにゲストとして追加されません。 [Microsoft Entra External ID での B2B 直接接続の詳細について説明します](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)。

外部組織との B2B 直接接続を設定すると、Teams 共有チャネルに対して次の機能が利用できるようになります。

- Teams 内では、共有チャネル所有者は、外部組織の許可されたユーザーを検索して、共有チャネルに追加できます。
- 外部ユーザーは、組織を切り替えたり、別のアカウントを使用してサインインしたりしなくても、Teams 共有チャネルにアクセスできます。 Teams 内から、外部ユーザー **は [ファイル** ] タブを使用してファイルやアプリにアクセスできます。共有チャネルのポリシーによって、ユーザーのアクセスが決まります。

[テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)を使用して、他の Microsoft Entra 組織との信頼関係を管理し、B2B 直接接続の受信ポリシーと送信ポリシーを定義します。

Teams 共有チャネル経由で B2B 直接接続のユーザーが利用できるリソース、ファイル、アプリケーションの詳細については、 [Microsoft Teamsのチャット、チーム、チャネル、アプリを](https://learn.microsoft.com/ja-jp/microsoftteams/deploy-chat-teams-channels-microsoft-teams-landing-page)参照してください。

ライセンスと課金は、月間アクティブ ユーザー (MAU) に基づいています。 [Microsoft Entra External ID の課金モデルの詳細について説明します](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)。

#### Azure Active Directory B2C (Azure AD B2C)

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

Azure AD B2C は、顧客 ID とアクセス管理のためのレガシ ソリューションです。 Azure AD B2C には、Azure AD B2C サービスを介して Azure portal で管理する別のコンシューマーベースのディレクトリが含まれています。 各 Azure AD B2C テナントは、他の Microsoft Entra ID テナントや Azure AD B2C テナントとは別個のもので、区別されます。

Azure AD B2C ポータルのエクスペリエンスは Microsoft Entra ID に似ていますが、主な違いがあります。 たとえば、Identity Experience Framework を使用してユーザー体験をカスタマイズできます。

Azure AD B2C テナントと Microsoft Entra テナントの違いについて詳しくは、[Azure AD B2C でサポートされる Microsoft Entra の機能](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/supported-azure-ad-features)に関するページを参照してください。 Azure AD B2C の構成と管理の詳細については、「[Azure AD B2C のドキュメント](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/)」を参照してください。

#### ビジネス ゲスト サインアップ用の Microsoft Entra エンタイトルメント管理

リソースへのアクセスが必要な個々の外部コラボレーターが事前にわからない場合があります。 パートナー企業のユーザーが、あなたが管理するポリシーのもとで自分自身でサインアップできる方法が必要です。

他の組織のユーザーがアクセスを要求できるようにするには、[Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用 して、[外部ユーザーのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#how-access-works-for-external-users)ポリシーを構成できます。 承認されると、これらのユーザーはゲスト アカウントでプロビジョニングされ、グループ、アプリ、および SharePoint Online サイトに割り当てられます。

#### 条件付きアクセス

組織では、Microsoft Entra 条件付きアクセス ポリシーを使用して、外部ユーザーに適切なアクセス制御を適用することでセキュリティを強化できます。 これらのコントロールには、多要素認証 (MFA) が含まれます。

##### 外部テナントの条件付きアクセスと MFA

外部テナントでは、組織は条件付きアクセス ポリシーを作成し、MFA を追加してサインアップとサインインのユーザー フローを作成することで、顧客に MFA を適用できます。 外部テナントでは、2 つ目の要素として認証のための 2 つの方法がサポートされています。

- **ワンタイム パスコードを電子メールで送信します**。 ユーザーは、メールとパスワードでサインインすると、メールに送信されるパスコードの入力を求められます。
- **SMS ベースの認証**。 SMS は、外部テナントのユーザーに対する MFA の第 2 要素認証方法として使用できます。 メールとパスワード、メールとワンタイム パスコード、Google や Facebook などのソーシャル ID を使用してサインインしたユーザーは、SMS を使用して 2 回目の確認を求められます。

[外部テナントの認証方法の詳細について説明します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers)。

##### B2B コラボレーションと B2B 直接接続の条件付きアクセス

従業員テナントでは、組織は、組織のフルタイムの従業員とメンバーに対して有効にしたのと同じ方法で、外部の B2B コラボレーションおよび B2B 直接接続ユーザーに条件付きアクセス ポリシーを適用できます。 Microsoft Entra テナント間シナリオでは、条件付きアクセス ポリシーで MFA またはデバイス コンプライアンスが要求される場合、外部ユーザーのホーム組織からの MFA 要求とデバイス コンプライアンス要求を信頼できるようになりました。

信頼設定が有効になっている場合、Microsoft Entra ID では、認証時に、MFA 要求のユーザーの資格情報またはデバイス ID を確認して、ポリシーが既に満たされているかどうかを判断します。 その場合、外部ユーザーには共有リソースへのシームレスなサインオンが許可されます。 そうでない場合、ユーザーのホーム テナントで MFA またはデバイスのチャレンジが開始されます。 [従業員テナントの外部ユーザーの認証フローと条件付きアクセスの詳細について説明します](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)。

#### マルチテナント アプリケーション

SaaS アプリケーションを多くの組織に提供する場合は、任意の Microsoft Entra テナントからのサインインを受け入れるようにアプリケーションを構成できます。 この構成はアプリケーションのマルチテナント化と呼ばれます。 Microsoft Entra テナントのユーザーは、アプリケーションで自分のアカウントを使用することに同意した後、アプリケーションにサインインできます。 [マルチテナント サインインを有効にする方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)。

#### マルチテナント組織

[マルチテナント組織](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview)は、Microsoft Entra ID の複数のインスタンスを持つ組織です。 複数のテナントを使用する理由はさまざまです。 たとえば、組織は複数のクラウドや地理的境界にまたがる場合があります。

マルチテナント組織の機能により、Microsoft 365 全体でシームレスなコラボレーションが可能になります。 また、Microsoft Teamsや Microsoft Viva Engage などのアプリケーションで、複数のテナントの組織全体で従業員のコラボレーション エクスペリエンスを向上させます。

[テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)機能は、ユーザーが各テナントで同意プロンプトを受け入れる招待メールを受け取ることなくリソースにアクセスできるようにする一方向の同期サービスです。

マルチテナント組織とクロステナント同期の詳細については、 [マルチテナント組織のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/) と [機能の比較](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview#compare-multitenant-capabilities)を参照してください。

### Microsoft Graph API

次のセクションに示す機能を除き、すべての外部 ID 機能は、Microsoft Graph API を使用した自動化でもサポートされています。 詳細については、「[Microsoft Graph を使用して Microsoft Entra ID とネットワーク アクセス機能を管理する](https://learn.microsoft.com/ja-jp/graph/api/resources/identity-network-access-overview)」 を参照してください。

#### Microsoft Graph でサポートされていない機能

| 外部 ID 機能 | サポート対象 : | 自動化の回避策 |
| --- | --- | --- |
| [所属する組織を特定する](https://learn.microsoft.com/ja-jp/entra/external-id/leave-the-organization#what-organizations-do-i-belong-to) | 労働者テナント | 「テナント - Azure Resource Manager API の [一覧表示](https://learn.microsoft.com/ja-jp/rest/api/resources/tenants/list) 」を参照してください。 Teams 共有チャネルと B2B 直接接続の場合は、 [Get `tenantReferences`](https://learn.microsoft.com/ja-jp/graph/api/outboundshareduserprofile-list-tenants) Microsoft Graph API を使用します。 |

#### B2B コラボレーション用の Microsoft Graph API

- [テナント間アクセス API](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicy-overview?view=graph-rest-beta&preserve-view=true)。 プログラムによって、Azure portal で構成できる同じ B2B コラボレーション ポリシーと B2B 直接接続ポリシーを作成します。

    これらの API を使用すると、受信コラボレーションと送信コラボレーションのポリシーを設定できます。 たとえば、すべてのユーザーの機能を既定で許可またはブロックし、特定の組織、グループ、ユーザー、アプリケーションへのアクセスを制限できます。

    これらの API を使用して、他の Microsoft Entra 組織から MFA とデバイス要求 (準拠クレームと Microsoft Entra ハイブリッド参加済みクレーム) を受け入れることもできます。
- [招待管理のリソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/invitation)。 ビジネス ゲスト向けに独自のオンボーディング エクスペリエンスを構築します。 たとえば、 [招待の作成 API](https://learn.microsoft.com/ja-jp/graph/api/invitation-post) を使用して、カスタマイズした招待メールを B2B ユーザーに直接自動的に送信できます。 または、作成応答で返された `inviteRedeemUrl` 値を使用して、招待されたユーザーへの独自の招待を作成できます (選択した通信メカニズムを通じて)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/external-identities-pricing"} -->
## 外部 ID 料金設定 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing
- Service: entra-external-id / external
- Article date: 2026-06-22
- Summary: 外部テナントを Azure サブスクリプションにリンクする手順と共に、Microsoft Entra 外部 IDの価格と課金の構造について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、Microsoft Entra 外部 IDの価格と課金の構造について説明します。 外部 ID は、高度なシナリオに対して、オプションの Premium アドオンを備えた基本的な月間アクティブ ユーザー (MAU) 課金モデルを使用します。 また、テナントを Azure サブスクリプションにリンクして、正しい課金と機能へのアクセスを確保する方法についても説明します。

最新の価格の詳細については、「 [外部 ID の価格」を](https://aka.ms/ExternalIDPricing)参照してください。

### 外部ID課金モデル

基本的な外部 ID 課金モデルは、月間アクティブ ユーザー (MAU) に基づいています。これは、カレンダー月内にテナントに対して認証を行う一意の外部ユーザーの数です。 MAU の合計数を確認するために、サブスクリプションにリンクされているすべての従業員と外部テナントの MA を結合します。

MAU 課金は、無料の階層と柔軟で予測可能な価格設定を提供することで、コスト削減に役立ちます。 無料で始めて、ビジネスの成長に合わせて使用した分だけ支払うことができます。

外部 ID の MAU 課金モデルは、すべてのゲスト ユーザーに適用されます。 ゲスト ユーザーには次のものが含まれます。

- Microsoft Entra [ワークフォース テナント](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants)での B2B コラボレーションの外部ゲスト。 これらのユーザーは *、外部* 資格情報を使用してサインインします。 `UserType`プロパティは `Guest` に設定されます。

    注

    複数のテナントを所有して運用している場合、メンバー ユーザーは MAU の合計にカウントされることなく、テナント全体で認証できます。 B2B コラボレーションの場合、MAU 課金モデルは、`UserType`の`Guest`値を持つ外部ユーザーにのみ適用されます。 組織内から発信され、`UserType`の`Member`値を持つユーザーには適用されません。
- Microsoft Entra の内部ゲスト。 これらのユーザーは、 `internal` 資格情報を使用してサインインします。 `UserType`プロパティは `Guest` に設定されます。
- Microsoft Entra [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)内の外部ユーザー:

    - コンシューマーとビジネス ゲスト (ディレクトリ ロールのないユーザー)
    - 管理者 (ディレクトリ ロールを持つユーザー)

    MAU 課金は、 `UserType` 設定に関係なく、外部テナント内のすべてのユーザーに適用されます。

内部ゲストと外部ゲストの違いの詳細については、「 [B2B ゲスト ユーザーのプロパティを理解して管理する](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)」を参照してください。

### Premium アドオン

外部 ID には、基本的な MAU 課金に加えて、高度なシナリオ向けに機能を拡張するプレミアム アドオンが用意されています。 各アドオンには、独自の課金モデルがあります。 次の表に、使用可能なアドオンの概要を示します。

| アドオン | テナント構成 | 課金モデル | 説明 |
| --- | --- | --- | --- |
| **M2M 認証** | External | トランザクションベース | OAuth 2.0 クライアント資格情報を使用した認証は、ユーザー操作なしでマシン間 (M2M) 認証シナリオにフローします。 料金は、認証トランザクションの数に基づいています。 |
| **SMS 電話認証** | 従業員、外部 | トランザクションベース | 各 SMS ベースの認証イベントに対する追加料金 (テキストのみ、音声はサポートされていません)。 詳細については、「[Microsoft Entra多要素認証の機能とライセンス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing)」を参照してください。 |
| **Go-Local** | External | MAU ベース | データ所在地の要件を満たすために、特定の地理的リージョンに外部 ID データを格納します。 現在、オーストラリアと日本でのみ利用できます。 |
| **ID ガバナンス** | 労働力 | MAU ベース | Microsoft Entra ID ガバナンスのプレミアム機能を使用してゲスト ユーザーを管理します。 詳細については、「[ゲスト ユーザーのMicrosoft Entra ID ガバナンスライセンス」を](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)参照してください。 |
| **GSA（ゲスト向け）** | 労働力 | MAU基準 | ワークフォース テナントのゲスト ユーザーに対する Global Secure Access (GSA) のサポート範囲。 |

注

Premium アドオン料金は、基本の MAU 課金に加算されます。 アドオンの価格の最新情報については、「 [外部 ID の価格」を](https://aka.ms/ExternalIDPricing)参照してください。

### 課金シナリオ

次の例は、基本的な MAU 課金アドオンと Premium アドオンがどのように連携するかを示しています。 各シナリオは、それが適用されるテナント構成 (従業員または外部) を示します。 詳細については、「 [テナントの構成](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations)」を参照してください。 これらのシナリオは概念であり、特定の価格は含まれません。 現在の価格については、「 [外部 ID の価格」を](https://aka.ms/ExternalIDPricing)参照してください。

#### シナリオ 1: 基本的なサインインを使用するコンシューマー アプリ (外部テナント)

外部テナントに登録されているコンシューマー向けアプリには、電子メールとパスワードまたはソーシャル ID プロバイダーを使用してサインインする 10,000 人のユーザーがいます。 Premium アドオンは有効になっていません。

- **テナント構成**: 外部
- **適用可能なアドオン SKU**: なし
- **測定の種類**: MAU
- **MAU カウント**: 10,000
- **結果**: MAU の使用が空き制限内にある場合、コストはかからなくなります。

#### シナリオ 2: M2M 認証 (外部テナント)

コンソール アプリなどのバックグラウンド サービスは継続的に実行され、クライアント資格情報を使用してMicrosoft Entra 外部 IDで認証されます。 アプリは、ユーザーの操作なしで API を単独で呼び出し、アクセス トークンを 1 時間ごとに更新します。

- **テナント構成**: 外部
- **適用可能なアドオン SKU**: M2M 認証
- **測定の種類**: トランザクション (M2M 認証)
- **MAU 数**: 0 (M2M 認証にはユーザー サインインは含まれないので、MAU 料金は適用されません)
- **説明**: クライアント資格情報認証要求の数に基づくトランザクション料金。たとえば、1 時間あたり 1 回のトークン更新では、1 か月あたり約 720 トランザクションが生成されます。
- **結果**: M2M 認証アドオンの料金のみが適用されます。 現在のトランザクションの価格については、「 [外部 ID の価格」を](https://aka.ms/ExternalIDPricing)参照してください。

#### シナリオ 3: 対話型ユーザーと M2M 呼び出しを使用するコンシューマー アプリ (外部テナント)

外部テナントのコンシューマー アプリには、対話形式でサインインするユーザーが 5,000 人います。 アプリでは、データの同期や通知の送信などのバックグラウンド処理タスクにも M2M 認証 (クライアント資格情報) が使用されます。

- **テナント構成**: 外部
- **適用可能なアドオン SKU**: M2M 認証
- **測定の種類**: MAU とトランザクション (M2M 認証)
- **MAU 数**: 5,000 (対話型ユーザーのみ;M2M 認証呼び出しは MAU にカウントされません)
- **説明**: クライアント資格情報認証要求の数に基づくトランザクション料金。
- **結果**: 対話型ユーザー向けの Microsoft Entra 外部 ID Basic MAU 料金に加えて、バックグラウンド処理用の M2M 認証アドオン料金が発生します。

#### シナリオ 4: ID ガバナンス (ワークフォース テナント) との B2B コラボレーション

組織は、従業員テナントで B2B コラボレーション ゲストとして 2,000 人の外部ビジネス パートナーを招待します。 組織では、ID ガバナンスを使用して、ゲスト ユーザーの機械学習支援アクセス レビューを管理します。

- **テナント構成**: Workforce
- **適用可能なアドオン SKU**: ID ガバナンス
- **測定の種類**: MAU
- **MAU カウント**: 2,000
- **説明**: 機械学習支援アクセス レビューなど、月の間にガバナンス アクションをトリガーするゲストの料金。詳細については、「[ゲスト ユーザーのMicrosoft Entra ID ガバナンスライセンス」を](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)参照してください。
- **結果**: Microsoft Entra 外部 ID Basic MAU の料金に、ID ガバナンスのアドオン料金を加えたもの。

#### シナリオ 5: データ所在地を持つコンシューマー アプリ (外部テナント)

外部テナントのコンシューマー アプリには、対話形式でサインインする 8,000 人のユーザーがいます。 組織は、Go-Local アドオンがデータ所在地の要件を満たすために、特定の地理的リージョンに外部 ID データを格納できるようにします。 Go-Local アドオンは現在、オーストラリアと日本でのみ使用できます。

- **テナント構成**: 外部
- **適用可能なアドオン SKU**: Go-Local
- **測定の種類**: MAU
- **MAU カウント**: 8,000
- **説明**: 基本的な MAU 料金に加えて、選択したリージョンに外部 ID データを格納するための MAU ベースの料金。
- **結果**: Microsoft Entra 外部 ID Basic の MAU 料金に、Go-Local アドオン料金を加えたもの。

### サブスクリプションの要件

外部 ID には、課金にAzure サブスクリプションが必要です。 次のセクションでは、従業員または外部テナントをサブスクリプションにリンクする方法について説明します。 価格の詳細については、「 [外部 ID の価格」を](https://aka.ms/ExternalIDPricing)参照してください。

注

Azure AD 外部 ID P1/P2 SKU で B2B コラボレーションをサブスクライブしたことがある場合は、現在の価格オプションと使用可能なアップグレード パスの詳細については、「[外部 ID の価格](https://aka.ms/ExternalIDPricing)」ページを参照してください。

### 従業員テナントをサブスクリプションにリンクする

適切な課金と機能へのアクセスを行うには、Microsoft Entra従業員テナントをAzure サブスクリプションにリンクする必要があります。 テナントをサブスクリプションにリンクするには:

1. [Microsoft Entra管理センター](https://entra.microsoft.com/)にサインインします。 サブスクリプション内に少なくとも共同作成者ロールを持つアカウント、またはサブスクリプション内のリソース グループを使用します。
2. リンクするディレクトリを選択します。

    1. ツール バーの **[設定]** アイコンを選択します。
    2. [ **ディレクトリ + サブスクリプション** ] ウィンドウで、 **ディレクトリ名** の一覧で従業員テナントを見つけます。 次に、[ **切り替え**] を選択します。
3. **Entra ID**&gt;**外部ID**&gt;**概要** に移動します。
4. [ **サブスクリプション] で**、[ **リンクされたサブスクリプション**] を選択します。
5. テナントの一覧で、テナントの横にあるチェック ボックスをオンにし、[ **サブスクリプションのリンク**] を選択します。

    [Image: サブスクリプションをリンクするためのアクションのスクリーンショット。]
6. [ **サブスクリプションのリンク** ] ウィンドウで、サブスクリプションとリソース グループを選択します。 続いて **適用** を選択します。 (サブスクリプションが一覧に表示されない場合は、この記事の後半の「 サブスクリプションが見つからない場合 の動作」を参照してください)。

    [Image: サブスクリプションとリソース グループを選択するためのボックスのスクリーンショット。]

これらの手順を完了すると、Azure 直接契約または Enterprise Agreement の詳細 (該当する場合) に基づいて Azure サブスクリプションが課金されます。

#### サブスクリプションが見つからない場合はどうすればよいですか?

[サブスクリプションのリンク] ウィンドウで使用できる **サブスクリプション** がない場合は、次のような理由が考えられます。

- 従業員テナントをサブスクリプションにリンクしようとしていますが、現在は外部テナントにサインインしています。 ワークフォース テナントに切り替える。

    1. Microsoft Entra 管理センターのツール バーで、[ **設定]** を選択します。
    2. [ **ディレクトリ + サブスクリプション** ] ウィンドウで、一覧からワークフォース テナントを見つけます。 次に、[ **切り替え**] を選択します。
- 適切なアクセス許可がありません。 少なくともサブスクリプション内の共同作成者ロールまたはサブスクリプション内のリソース グループを持つ Azure アカウントを使用してサインインしてください。
- サブスクリプションは存在しますが、ディレクトリに関連付けられていません。 [既存のサブスクリプションをテナントに関連付け、テナント](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)にリンクする手順を繰り返すことができます。
- サブスクリプションが存在しません。 [ **サブスクリプションのリンク** ] ウィンドウで、リンクを選択してサブスクリプションを作成できます( **まだサブスクリプションがない場合は、ここで作成できます**)。

    新しいサブスクリプションを作成した後、新しいサブスクリプション [にリソース グループを作成](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/manage-resource-groups-portal) する必要があります。 次に、テナントにリンクするための手順を繰り返します。

### 外部テナントをサブスクリプションにリンクする

外部テナントの作成方法によっては、既にいずれかのサブスクリプションにリンクされている場合があります。 確認するには、次の手順に従います。

1. [Microsoft Entra管理センター](https://entra.microsoft.com/)にサインインします。
2. 外部テナントが選択されていることを確認します。

    1. ツール バーの **[設定]** アイコンを選択します。
    2. [ **ディレクトリとサブスクリプション** ] ウィンドウで、ディレクトリ **名** の一覧で外部テナントを見つけます。 次に、[ **切り替え**] を選択します。
3. [ **ホーム** ] を選択し、[ **課金** ] セクションを見つけます。 次に、次のいずれかのアクションを実行します。

    - テナントが既にサブスクリプションにリンクされている場合は、このセクションにサブスクリプション ID が表示されます。 ID を選択すると、サブスクリプションの詳細が表示されます。

        [Image: サブスクリプションにリンクされている外部テナントの例を示すスクリーンショット。]
    - テナントがまだサブスクリプションにリンクされていない場合は、[ **課金** ] セクションで [ **ここをクリックしてアップグレード] リンクを** 選択します。 次に、[ **サブスクリプションの追加]** ボタンを選択します。

        [Image: サブスクリプションがない外部テナントの例を示すスクリーンショット。]

### 外部テナントがリンクされているサブスクリプションを変更する

使用するサブスクリプションが現在のサブスクリプションと同じ Microsoft Entra ワークフォース テナントにある限り、外部テナントを別のサブスクリプションに移動できます。 *別*の Microsoft Entra ワークフォース テナント内のサブスクリプションへの移行は、現在サポートされていません。

外部テナント リソースを新しいサブスクリプションに移動するには、「[Move Azure リソースを新しいリソース グループまたはサブスクリプションに移動する](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/move-resource-group-and-subscription)ɳ」の説明に従ってAzure Resource Managerを使用します。 移行を開始する前に必ずこの記事に目を通して、制限事項と要件を十分に理解してください。 この記事には他にも、移行前のチェックリストや移行の検証手順といった重要な情報が含まれています。

### サブスクリプションの所有権を変更できますか?

サブスクリプションの所有権を Microsoft Entra 外部テナントに変更することはできません。 外部テナントにはサブスクリプション管理機能がありません。 外部テナントは、Microsoft Entra 従業員テナントが所有するサブスクリプションにリンクされている必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/facebook-federation"} -->
## Facebook を ID プロバイダーとして追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/facebook-federation
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: Facebook とフェデレーションして、外部ユーザー (ゲスト) が自分の Facebook アカウントを使用してMicrosoft Entra アプリにサインインできるようにします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ヒント

この記事では、従業員テナントで B2B Collaboration の ID プロバイダーとして Facebook を追加する方法について説明します。 外部テナントの手順については、「 [Id プロバイダーとして Facebook を追加する」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)参照してください。

Facebook をセルフサービス サインアップのユーザー フローに追加して、ユーザーが自分の Facebook アカウントを使用してアプリケーションにサインインできるようにすることができます。 ユーザーが Facebook を使用してサインインできるようにするには、まずテナントの [セルフサービス サインアップを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow) 必要があります。 Facebook を ID プロバイダーとして追加した後、アプリケーションに対するユーザー フローを設定し、サインイン オプションの 1 つとして Facebook を選択します。

アプリケーションのサインイン オプションの 1 つとして Facebook を追加した後、[サインイン] ページ **で** 、ユーザーが Facebook に使用するメールを入力できます。 または、[ **サインイン] オプション** を選択し、[ **Facebook でサインイン**] を選択することもできます。 どちらの場合も、認証のために Facebook サインイン ページにリダイレクトされます。

[Image: サインイン オプションと Facebook でのサインイン オプションを示すMicrosoft Entra External ID サインイン ページのスクリーンショット。]

Note

ユーザーは、セルフサービス サインアップおよびユーザー フローを使用したアプリ経由のサインアップに限り、Facebook アカウントを使用できます。 ユーザーを招待したり、Facebook アカウントを使用して招待を利用したりすることはできません。

### Facebook 開発者コンソールでアプリを作成する

Facebook アカウントを [ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers)として使用するには、Facebook 開発者コンソールでアプリケーションを作成する必要があります。 まだ Facebook アカウントを持っていない場合は、[https://www.facebook.com/](https://www.facebook.com) でサインアップできます。

Note

このドキュメントは、作成した時点でのプロバイダーの開発者ページの状態を使って作成されたものであり、変更が発生する可能性があります。

1. Facebook 開発者アカウントの資格情報を使用して [、開発者向けに](https://developers.facebook.com/apps) Facebook にサインインします。
2. まだ登録していない場合は、Facebook 開発者として登録します。ページの右上隅にある [ **作業の開始** ] を選択し、Facebook のポリシーに同意して、登録手順を完了します。
3. [ **アプリの作成] を選択します**。 この手順では、Facebook プラットフォームのポリシーを受け入れてオンライン セキュリティ チェックを完了することが必要な場合があります。
4. [**認証] を選択し、Facebook ログインを使用してユーザーにデータを要求**します&gt;**次へ**。
5. [ **ゲームをビルドしていますか?** ] で [ **いいえ、 ゲームをビルドしていない** ]、[ **次へ**] の順に選択します。
6. アプリ名と有効なアプリの連絡先メール アドレスを追加します。 ビジネス アカウントがある場合は、それを追加することもできます。
7. [ **アプリの作成] を選択します**。
8. アプリが作成されたら、ダッシュボードに移動します。
9. [ **アプリの設定]**&gt;**[基本**]を選択します。
    1. **アプリ ID** の値をコピーします。 次に、[ **表示** ] を選択し、 **アプリ シークレット**の値をコピーします。 テナントで ID プロバイダーとして Facebook を構成するには、両方の値を使用します。 **アプリ シークレット** は重要なセキュリティ資格情報です。
    2. **プライバシー ポリシー URL の URL を**入力します (例: `https://www.contoso.com/privacy`)。 ポリシーの URL は、アプリケーションのプライバシーに関する情報を提供するために維持されるページです。
    3. **サービス利用規約 URL の URL を**入力します (例: `https://www.contoso.com/tos`)。 サービス利用規約 URL は、アプリケーションの使用条件を提供するために管理するページです。
    4. **ユーザー データ**削除の URL を入力します (例: `https://www.contoso.com/delete_my_data`)。 ユーザー データ削除 URL は、ユーザーがデータの削除を要求する方法を提供するために保持するページです。
    5. **カテゴリ** (**ビジネスやページなど)** を選択します。 Facebook ではこの値が必要ですが、Microsoft Entra IDでは使用されません。
10. ページの下部にある [ **プラットフォームの追加**] を選択し、[ **Web サイト**] を選択して、[ **次へ**] を選択します。
11. **[サイト URL]** に、Web サイトのアドレスを入力します (例: `https://contoso.com`)。
12. [ **変更の保存] を選択します**。
13. 左側の **[ユース ケース**] を選択し、[**認証とアカウントの作成**] の横にある **[カスタマイズ**] を選択します。
14. **[Facebook ログイン**] の [**設定に移動**] を選択します。
15. **Valid OAuth リダイレクト URI** で、次の URI を入力し、`<tenant-ID>`をMicrosoft Entraテナント ID、`<tenant-subdomain>` をテナント サブドメインに置き換え、`<tenant-name>`をMicrosoft Entraテナント名に置き換えます。

- `https://login.microsoftonline.com/te/<tenant-ID>/oauth2/authresp`
- `https://login.microsoftonline.com/te/<tenant-subdomain>.onmicrosoft.com/oauth2/authresp`
- `https://<tenant-name>.ciamlogin.com/<tenant-ID>/federation/oidc/www.facebook.com`
- `https://<tenant-name>.ciamlogin.com/<tenant-name>.onmicrosoft.com/federation/oidc/www.facebook.com`
- `https://<tenant-name>.ciamlogin.com/<tenant-ID>/federation/oauth2`
- `https://<tenant-name>.ciamlogin.com/<tenant-name>.onmicrosoft.com/federation/oauth2`

1. [ **変更の保存]** を選択し、ページの上部にある [ **アプリ** ] を選択し、先ほど作成したアプリを選択します。
2. ページの左側にある **[ユース ケース**] を選択し、[**認証とアカウントの作成**] の横にある **[カスタマイズ**] を選択します。
3. [アクセス許可] で [ **追加** ] を選択して、電子メールの **アクセス許可**を追加します。
4. ページの上部にある [ **戻る** ] を選択します。
5. この時点では、Facebook アプリケーションの所有者のみがサインインできます。 アプリを登録したため、Facebook アカウントを使用してサインインできます。 Facebook アプリケーションをユーザーが使用できるようにするには、メニューから [ **ライブに移動**] を選択します。 一覧表示されているすべての手順に従って、すべての要件を完了します。 ID をビジネス エンティティまたはビジネス組織として確認するため、データ処理の質問とビジネス検証を完了することが必要な場合があります。 詳細については、「 [メタ アプリ開発」を](https://developers.facebook.com/docs/development/release)参照してください。

### ID プロバイダーとして Facebook アカウントを構成する

次に、Facebook クライアント ID とクライアント シークレットを、Microsoft Entra admin centerに入力するか、PowerShell を使用して設定します。 セルフサービス サインアップが有効になっているアプリでユーザー フローを使用してサインアップすることで、Facebook の構成をテストすることができます。

#### Microsoft Entra admin centerで Facebook フェデレーションを構成するには

1. 少なくとも[External Identity Provider Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)として[Microsoft Entra admin center](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[外部 ID]**&gt;**[すべての ID プロバイダー]** を参照し、**[Facebook]** の行で、**[構成]** を選択します。
3. **クライアント ID** には、先ほど作成した Facebook アプリケーションの**アプリ ID を**入力します。
4. **クライアント シークレット**の場合は、記録した**アプリ シークレット**を入力します。

    [Image: クライアント ID フィールドとクライアント シークレット フィールドを含むMicrosoft Entra admin centerの Facebook ID プロバイダー構成ウィンドウのスクリーンショット。]
5. **[保存] を選択します**。

#### PowerShell を使用して Facebook フェデレーションを構成するには

1. 最新バージョンの [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をインストールします。
2. 次のコマンドを実行します。

    ```powershell
    Connect-MgGraph -Scopes "IdentityProvider.ReadWrite.All"
    ```
3. サインイン プロンプトで、少なくとも [外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
4. 次のコマンドを実行します。

    ```powershell
    $params = @{
       "@odata.type" = "microsoft.graph.socialIdentityProvider"
       displayName = "Facebook"
       identityProviderType = "Facebook"
       clientId = "[Client ID]"
       clientSecret = "[Client secret]"
    }
    
    New-MgIdentityProvider -BodyParameter $params
    ```

    [テナントのセルフサービス サインアップを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow#enable-self-service-sign-up-for-your-tenant)必要がある場合があります。

    Note

    Facebook 開発者コンソールで、前の手順で作成したアプリのクライアント ID とクライアント シークレットを使用します。 詳細については、 [New-MgIdentityProvider](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands) の記事を参照してください。

### Facebook フェデレーションを削除する方法

Facebook フェデレーション セットアップは削除できます。 それを行った場合、Facebook アカウントを使用してユーザー フローを通じてサインアップしたユーザーは、サインインできなくなります。

#### Microsoft Entra admin centerで Facebook フェデレーションを削除するには:

1. 少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**すべての ID プロバイダー** に移動します。
3. **Facebook** の行を選択します。 [**構成済み**] を選択し、[**削除します**] を選択します。
4. [ **はい** ] を選択して削除を確定します。

#### PowerShell を使用して Facebook フェデレーションを削除するには:

1. 最新バージョンの [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をインストールします。
2. 次のコマンドを実行します。

    ```powershell
    Connect-MgGraph -Scopes "IdentityProvider.ReadWrite.All"
    ```
3. サインイン プロンプトで、少なくとも [外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
4. 次のコマンドを入力します。

    ```powershell
    Remove-MgIdentityProvider -IdentityProviderBaseId "Facebook-OAUTH"
    ```

    Note

    詳細については、「 [Remove-MgIdentityProvider](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/remove-mgidentityprovider)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/faq"} -->
## B2B コラボレーションに関するよくある質問 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/faq
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: Microsoft Entra B2B コラボレーションに関してよく寄せられる質問に対する回答を取得します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra企業間 (B2B) コラボレーションに関するよく寄せられる質問 (FAQ) は、新しいトピックを含むように定期的に更新されます。

### B2B コラボレーションでのゲスト ユーザー サインイン エクスペリエンスの変更点

2025 年 7 月、Microsoftは B2B コラボレーションのゲスト ユーザー サインイン エクスペリエンスの更新プログラムのロールアウトを開始しました。 ロールアウトは 2025 年末まで継続しました。 この更新プログラムでは、ゲスト ユーザーは自分の組織のサインイン ページにリダイレクトされ、資格情報が提供されます。 ゲスト ユーザーには、ホーム テナントのブランドと URL エンドポイントが表示されます。 この手順では、使用するサインイン情報に関するより明確なガイダンスを提供します。 自分の組織で認証が成功すると、サインインを完了するためにゲスト ユーザーが組織に返されます。

### B2B コラボレーション ユーザーはSharePoint OnlineとOneDriveにアクセスできますか?

はい。 ただし、ユーザー 選択ウィンドウを使用して SharePoint Online で既存のゲスト ユーザーを検索する機能は、既定では **Off** です。 既存のゲスト ユーザーを検索するオプションを有効にするには、**ShowPeoplePickerSuggestionsForGuestUsers** を **On** に設定します。 この設定は、テナント レベルで、またはサイト コレクション レベルで有効にできます。 この設定を変更するには、Set-SPOTenant および Set-SPOSite コマンドレットを使用します。 これらのコマンドレットにより、メンバーは、ディレクトリ内のすべての既存のゲスト ユーザーを検索できます。 テナント スコープの変更は、既にプロビジョニングされているオンライン サイトSharePointには影響しません。

### B2B コラボレーション ユーザーはPower BIコンテンツにアクセスできますか?

はい。B2B コラボレーションを使用して、外部ゲスト ユーザーにPower BIコンテンツをdistribute できます。 Microsoft クラウド間でPower BIコンテンツを共有するには、[クラウド設定 Microsoft](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#microsoft-cloud-settings)を使用して、クラウドと外部クラウド間の相互 B2B コラボレーションを確立できます。

### CSV のアップロード機能は、まだサポートされていますか。

はい。 .csv ファイルのアップロード機能の使用の詳細については、[この PowerShell サンプル](https://learn.microsoft.com/ja-jp/entra/external-id/code-samples)をご覧ください。

### 招待の電子メールは、どのようにカスタマイズできますか。

[B2B 招待 API](https://learn.microsoft.com/ja-jp/entra/external-id/customize-invitation-api) を使用して、招待元プロセスのほぼすべてをカスタマイズすることができます。

### ゲスト ユーザーは、多要素認証方法をリセットできますか。

はい。 ゲスト ユーザーは、通常のユーザーと同じように、多要素認証方法をリセットできます。

### 多要素認証のライセンスに責任を持つのはどちらの組織ですか。

招待側組織が多要素認証を実行します。 招待側組織は、多要素認証を使用している B2B ユーザーに対する十分なライセンスがあることを確認する必要があります。

### パートナー組織で、既に多要素認証を設定している場合はどうなりますか。 それらの多要素認証を信頼できますか。

[クロス テナント アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)を使用すると、他のMicrosoft Entra組織からの多要素認証とデバイス要求 ([準拠要求とMicrosoft Entraハイブリッド参加済み要求](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)) を信頼できます。

### クロステナント アクセス設定に追加できる組織はいくつですか?

クロステナント アクセス設定は、他の組織との共同作業方法に関する設定を保存するディレクトリ内のポリシーです。 テナント間アクセス設定で追加できる組織の数に制限はありません。

### 遅れて送る招待状をどう使うことができますか？

組織で、B2B コラボレーション ユーザーを追加し、必要に応じてそれらのユーザーをアプリケーションにプロビジョニングして、招待を送信したいと考える場合があります。 B2B コラボレーションの招待 API を使用して、オンボード ワークフローをカスタマイズできます。

### ゲスト ユーザーを Exchange グローバル アドレス一覧に表示することはできますか?

はい。 既定では、ゲスト オブジェクトは組織のグローバル アドレス一覧には表示されませんが、それらを表示可能にできます。 詳細については、グループごとのゲスト アクセスのMicrosoft 365に関する記事の「[ゲストをグローバル アドレス一覧に追加する](https://learn.microsoft.com/ja-jp/microsoft-365/solutions/per-group-guest-access#add-guests-to-the-global-address-list)>」を参照してください。

### ゲスト ユーザーを制限付き管理者にできますか。

もちろん。 詳細については、[ゲスト ユーザーのロールへの追加](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)に関するページをご覧ください。

### Microsoft Entra B2B コラボレーションのユーザーは、Microsoft Entra 管理センターにアクセスできますか?

ユーザーに制限付き管理者のロールが割り当てられない限り、B2B コラボレーション ユーザーはMicrosoft Entra admin centerにアクセスする必要はありません。 ただし、制限付き管理者のロールを割り当てられている B2B コラボレーション ユーザーは portal にアクセスできます。 また、これらのいずれかの管理者ロールが割り当てられていないゲスト ユーザーがポータルにアクセスすると、ユーザーは、エクスペリエンスの特定の部分にアクセスできます。 ゲスト ユーザー ロールは、ディレクトリのアクセス許可の一部を持ちます。

### ゲスト ユーザーのMicrosoft Entra admin centerへのアクセスをブロックできますか?

はい。管理センターまたはポータルへのゲスト ユーザー アクセスをブロックする条件付きアクセス ポリシーを作成できます。 条件付きアクセス ポリシーを構成する際に、ポリシーを適用する外部ユーザーの種類をきめ細かく制御できます。 このポリシーを構成する場合は、誤ってメンバーと管理者のアクセスをブロックしないように注意してください。 「[外部ユーザーの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access#conditional-access-for-external-users)」の詳細を確認する。

### B2B コラボレーションMicrosoft Entraは多要素認証とコンシューマー電子メール アカウントをサポートしていますか?

はい。 多要素認証とコンシューマー電子メール アカウントの両方が、Microsoft Entra B2B コラボレーションでサポートされています。

### Microsoft Entra B2B コラボレーション ユーザーのパスワード リセットをサポートしていますか?

Microsoft Entra テナントがユーザーのホーム ディレクトリである場合は、Microsoft Entra admin centerからユーザーのパスワードを設定できます。 ただし、別のMicrosoft Entra ディレクトリまたは外部 ID プロバイダーによって管理されているアカウントでサインインするゲスト ユーザーのパスワードを直接リセットすることはできません。 パスワードをリセットできるのは、ゲスト ユーザーまたはユーザーのホーム ディレクトリの管理者だけです。 ゲスト ユーザーに対するパスワード リセットの動作の例をいくつか次に示します。

- "Guest" (UserType==Guest) とマークされているMicrosoft Entra テナント内のゲスト ユーザーは、https://aka.ms/ssprsetup を介して SSPR に登録できません。 このような種類のゲスト ユーザーは、https://aka.ms/sspr 経由でのみ SSPR を実行することができます。
- Microsoft account (guestuser@live.com など) を使用してサインインするゲスト ユーザーは、Microsoft accountセルフサービス パスワード リセット (SSPR) を使用して自分のパスワードをリセットできます。 「[Microsoft accountパスワードをリセットする方法](https://support.microsoft.com/help/4026971/microsoft-account-how-to-reset-your-password)」を参照>。
- Google アカウントまたはそれ以外の外部 ID プロバイダーでサインインしたゲスト ユーザーは、ID プロバイダーの SSPR 方法を使用して、自分のパスワードをリセットできます。 たとえば、Google アカウント guestuser@gmail.com のゲスト ユーザーは、「[パスワードを変更または再設定する](https://support.google.com/accounts/answer/41078)」の手順に従って自分のパスワードをリセットできます。
- ID テナントが Just-In-Time (JIT) または "バイラル" テナントである場合 (つまり、個別のアンマネージド Azure テナント)、ゲスト ユーザーのみがパスワードをリセットできます。 場合によっては、組織が、従業員が仕事用メール アドレスを使用してサービスにサインアップするときに作成される[バイラル テナントの管理を引き継ぎます](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-admin-takeover)。 組織がバイラル テナントを引き継いだ後は、その組織の管理者しか、ユーザーのパスワードをリセットしたり SSPR を有効にしたりできなくなります。 必要に応じて、招待側の組織としては、ディレクトリからゲスト ユーザー アカウントを削除し、招待を再送信することができます。
- ゲスト ユーザーのホーム ディレクトリが Microsoft Entra テナントの場合は、ユーザーのパスワードをリセットできます。 たとえば、ユーザーを作成したか、on-premises Active Directoryからユーザーを同期し、その UserType を Guest に設定したとします。 このユーザーはディレクトリに所属しているため、Microsoft Entra admin centerからパスワードをリセットできます。

### Microsoft Dynamics 365は、Microsoft Entra B2B コラボレーションのオンライン サポートを提供していますか?

はい。Dynamics 365 (オンライン) では、B2B コラボレーションMicrosoft Entraサポートされます。 詳細については、Dynamics 365の記事「[B2B コラボレーション](https://learn.microsoft.com/ja-jp/power-platform/admin/invite-users-azure-active-directory-b2b-collaboration)を Microsoft Entra使用するユーザーを招待する」を参照してください。

### 新しく作成した B2B コラボレーション ユーザー用の初期パスワードの有効期間はどうなっていますか。

Microsoft Entra IDには、すべてのMicrosoft Entraクラウド ユーザー アカウントに均等に適用される、一連の文字、パスワードの強度、アカウントロックアウトの要件が固定されています。 クラウド ユーザー アカウントは、次のような別の ID プロバイダーとフェデレーションされないアカウントです。

- マイクロソフトアカウント
- フェイスブック
- Active Directory フェデレーション サービス (Active Directory Federation Services)
- (B2B コラボレーション用の) 別のクラウド テナント

フェデレーション アカウントの場合、パスワード ポリシーは、オンプレミステナントとユーザーのMicrosoft account設定で適用されるポリシーによって異なります。

### 組織で、アプリケーションにテナント ユーザーとゲスト ユーザーに対して異なるエクスペリエンスを設定したいと考える場合があります。 これを行うための標準的なガイダンスはありますか。 ID プロバイダー要求の存在は、使用する正しいモデルですか。

ゲスト ユーザーは、任意の ID プロバイダーを使用して認証できます。 詳細については、[B2B コラボレーション ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)に関するページをご覧ください。 **UserType** プロパティを使用して、ユーザー エクスペリエンスを決定します。 **UserType 要求**は、現在トークンに含まれていません。 アプリケーションでは、Microsoft Graph APIを使用してユーザーのディレクトリを照会し、UserType を取得する必要があります。

### ソリューションを共有し、アイデアを提供するための B2B コラボレーション コミュニティにはどこからアクセスできますか。

B2B コラボレーションを向上させるためのフィードバックは絶えず受け付けています。 ユーザー シナリオ、ベスト プラクティス、B2B コラボレーションMicrosoft Entra気に入ったことを共有してください。 [Microsoft Tech Community](https://techcommunity.microsoft.com/t5/Azure-Active-Directory-B2B/bd-p/AzureAD_B2b) のディスカッションに参加してください。

また、[B2B Collaboration Ideas](https://techcommunity.microsoft.com/t5/Azure-Active-Directory-B2B-Ideas/idb-p/AzureAD_B2B_Ideas)でアイデアを送信し、今後の機能に投票することをお勧めします。

### ユーザーがすぐに "準備" できるように、自動的に引き換えられる招待を送信できますか。 それとも、ユーザーは常に引き換えの URL をクリックする必要があるのですか。

UI、PowerShell スクリプト、または API を使用して、パートナー組織の他のユーザーを招待できます。 その後、招待元はゲスト ユーザーに共有アプリへの直接リンクを送信できます。 ほとんどの場合、メール招待状を開いて、引き換えの URL をクリックする必要はなくなります。 [Microsoft Entra B2B コラボレーションへの招待の利用](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience)を参照してください。

### 招待されたパートナーがフェデレーションを使用して独自のオンプレミス認証を追加するとき、B2B コラボレーションはどのように機能しますか?

パートナーがオンプレミスの認証インフラストラクチャにフェデレーションされたMicrosoft Entra テナントを持っている場合、オンプレミスのシングル サインオン (SSO) が自動的に実現されます。 パートナーにMicrosoft Entra テナントがない場合は、新しいユーザー用にMicrosoft Entra アカウントが作成されます。

### Azure AD B2C ローカル アカウントを B2B コラボレーションのMicrosoft Entra テナントに招待できますか?

いいえ。 Azure AD B2C ローカル アカウントは、Azure AD B2C テナントへのサインインにのみ使用できます。 アカウントを使用してMicrosoft Entraテナントにサインインすることはできません。 Azure AD B2C ローカル アカウントを B2B コラボレーションのMicrosoft Entra テナントに招待することはサポートされていません。

重要

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、「[AZURE AD B2C は引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

### B2B ゲスト ユーザー Azureサポートするアプリケーションとサービスは何ですか?

すべてのMicrosoft Entra統合アプリケーションは、Azure B2B ゲスト ユーザーをサポートできますが、ゲスト ユーザーを認証するにはテナントとして設定されたエンドポイントを使用する必要があります。 また、ゲスト ユーザーがアプリに対して認証を行うと発行される SAML トークン内の[要求をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/claims-mapping)ことも必要になる場合があります。

### パートナーが多要素認証を使用していない場合に、B2B ゲスト ユーザーに多要素認証を強制できますか。

はい。 詳細については、「[B2B コラボレーション ユーザーの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)」を参照してください。

### SharePointでは、外部ユーザーの "許可" または "拒否" リストを定義できます。 Azureでこれを行うことができますか?

はい。 Microsoft Entra B2B コラボレーションでは、許可リストとブロックリストがサポートされます。

### B2B Microsoft Entraどのようなライセンスを使用する必要がありますか?

組織で Microsoft Entra B2B を使用するために必要なライセンスの詳細については、「[External ID の価格](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)」を参照してください。

### メールアドレスと UPN が一致しないユーザーを招待するとどうなりますか?

一概には言えません。 既定では、Microsoft Entra IDはログイン ID として UPN のみを許可します。 UPN と電子メールが同じ場合、B2B の招待とその後のサインインMicrosoft Entra想定どおりに機能します。 ただし、ユーザーの電子メールと UPN が一致せず、サインインに UPN の代わりに電子メールが使用される場合は、問題が発生する可能性があります。 UPN 以外のメールで招待されたユーザーは、[メール招待リンク](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#redemption-process-through-the-invitation-email)を使用すると招待を引き換えることができますが、[直接リンク](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#redemption-process-through-a-direct-link)経由での引き換えは失敗します。 ただし、ユーザーが招待を正常に利用した場合でも、別のログイン ID として電子メールを許可するように ID プロバイダー (Microsoft Entra IDまたはフェデレーション ID プロバイダー) が構成されていない限り、UPN 以外の電子メールを使用した後続のサインイン試行は失敗します。 この問題は以下の方法で軽減できます。

1. [招待された/自宅のMicrosoft Entraテナントに代替ログイン ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-use-email-signin) として電子メールを登録する
2. フェデレーション ID プロバイダーがログイン ID として電子メールをサポートできるようにする (Microsoft Entra IDが別の ID プロバイダーにフェデレーションされている場合) または
3. UPN を使用して引き換え/サインインするようにユーザーに指示する。

この問題を完全に回避するには、管理者はユーザーの UPN とメールアドレスが同じ値であることを確認する必要があります。

[Image: ゲスト ユーザーの電子メールと UPN が一致しない場合の招待の引き換え動作を示すフロー図。]

[Image: ゲスト ユーザーの電子メールと UPN が一致しない場合の後続のサインイン動作を示すフロー図。]

注

Microsoft クラウド間で送信される招待と引き換えは、UPN を使用する必要があります。 現時点では、電子メールはサポートされていません。 たとえば、米国政府テナントのユーザーが商用テナントに招待される場合、ユーザーは UPN を使用して招待される必要があります。

### インスタント オン: レプリケーションの待機時間が発生する原因は?

B2B コラボレーションのフローでは、ユーザーをディレクトリに追加し、招待の使用、アプリ割り当てなどの際に動的に更新します。 更新と書き込みは、通常、1 つのディレクトリ インスタンスで行い、すべてのインスタンス間でレプリケートする必要があります。 すべてのインスタンスが更新されると、レプリケーションが完了します。 いずれかのインスタンスでオブジェクトの書き込みまたは更新が行われ、そのオブジェクトを取得する呼び出しが別のインスタンスに対して行われた場合は、レプリケーションの遅延が発生する可能性があります。 その場合は、更新または再試行が役立つことがあります。 API を使用してアプリを作成する場合は、バックオフを伴う再試行がこの問題を軽減するための良い防御的な方法です。

### アプリは Google の WebView の非推奨とMicrosoftの OTP の既定値に対応していますか?

2021 年 1 月 4 日以降、Google は [WebView サインインのサポートを非推奨にしました](https://developers.googleblog.com/2020/08/guidance-for-our-effort-to-block-less-secure-browser-and-apps.html)。 Gmail で Google フェデレーションまたはセルフサービス サインアップを使用している場合は、[基幹業務ネイティブ アプリケーションの互換性をテストする](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation#deprecation-of-web-view-sign-in-support)必要があります。 すべての新しいテナントと、明示的に無効にしていない既存のテナントに対して、[電子メール ワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)機能が既定で有効になりました。 この機能をオフにすると、フォールバック認証方法は、招待者にMicrosoft accountの作成を求めるメッセージを表示することです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/google-federation"} -->
## Google ID プロバイダー - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/google-federation
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra 外部 ID で ID プロバイダーとして Google を追加する方法について学習します。 お客様が Google アカウントでサインインし、シームレスなアクセスのために Google フェデレーションを構成できるようにします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ヒント

この記事では、ワークフォーステナントにおけるB2BコラボレーションのためのIDプロバイダーとしてGoogleを追加する方法について説明します。 外部テナントの手順については、「[Google を ID プロバイダー として追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)」を参照してください。

Google とのフェデレーションを設定することで、招待されたユーザーが Microsoft アカウントを作成することなく、独自の Gmail アカウントを使用して共有アプリおよびリソースにサインインできるようにできます。 アプリケーションのサインイン オプションの 1 つとして Google を追加すると、ユーザーは **[サインイン]** ページで、Google へのサインインに使用する Gmail アドレスを入力できます。

[Image: Google ユーザーのサインイン オプション]

注意

Google フェデレーションは Gmail ユーザー専用に設計されています。 Google Workspace ドメインとのフェデレーションを行うには、[SAML/WS-Fed ID プロバイダー フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)を使用します。

重要

- 2021 年 7 月 12 日以降、Microsoft Entra B2B のお客様がセルフサービス サインアップまたはカスタムアプリケーションまたは基幹業務アプリケーション用に外部ユーザーを招待するために新しい Google 統合を設定した場合、Gmail ユーザーの認証がブロックされる可能性があります (エラー画面は >予想に表示されます)。 この問題は、2021 年 7 月 12 日以降にセルフサービス サインアップ ユーザー フローまたは招待用に Google 統合を作成し、カスタムまたは基幹業務アプリケーションの Gmail 認証がシステム Web ビューに移動されていない場合にのみ発生します。 システム Web ビューは既定で有効になっているため、ほとんどのアプリは影響を受けません。 この問題を回避するには、セルフサービス サインアップ用の新しい Google 統合を作成する前に、Gmail 認証をシステム ブラウザーに移動します。 詳細については、 埋め込み Web ビューに必要なアクションを参照してください。
- 2021 年 9 月 30 日以降、Google は [Web ビューサインインのサポートを非推奨にしました](https://developers.googleblog.com/2021/06/upcoming-security-changes-to-googles-oauth-2.0-authorization-endpoint.html)。 アプリが認証に埋め込みWebビューを使用し、[Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/identity-provider-google)やGoogleフェデレーションを使って外部ユーザー招待や[セルフサービスサインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers)をMicrosoft Entra B2Bで行う場合、Google Gmailユーザーは認証できません。 詳細については、 Web ビュー のサインイン サポートの廃止に関するページを参照してください。

### Google ユーザーのエクスペリエンスの内容

さまざまな方法で Google ユーザーを B2B コラボレーションに招待できます。 たとえば、[Microsoft Entra 管理センターを使用してディレクトリに追加](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-quickstart-add-guest-users-portal)できます。 招待を引き換えたときのエクスペリエンスは、Google に既にサインインしているかどうかによって異なります。

- Google にサインインしていないゲストユーザーには、そうするように求めるメッセージが表示されます。
- Google に既にサインインしているゲスト ユーザーには、使用するアカウントの選択を求めるメッセージが表示されます。 招待に使用されたアカウントを選択する必要があります。

"ヘッダーが長すぎます" というエラーが表示されたゲスト ユーザーは、Cookie をクリアするか、プライベートまたは匿名のウィンドウを開いて、もう一度サインインを試すことができます。

[Image: Google のサインイン ページを示すスクリーンショット。]

### サインインのエンドポイント

Google のゲスト ユーザーは、[共通のエンドポイント](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#redemption-process-and-sign-in-through-a-common-endpoint) (つまり、テナント コンテキストを含まない一般的なアプリ URL) を使って、マルチテナント アプリまたは Microsoft のファーストパーティ アプリにサインインできるようになりました。 サインイン プロセス中に、ゲスト ユーザーは **[サインイン オプション]** を選択してから、 **[Sign in to an organization](https://learn.microsoft.com/ja-jp/entra/external-id/組織にサインイン)** を選択します。 次に、ユーザーは組織の名前を入力し、Google 資格情報を使用してサインインを続行します。

Google ゲスト ユーザーは、テナント情報を含むアプリケーション エンドポイントを使用することもできます。次に例を示します。

- `https://myapps.microsoft.com/?tenantid=<your tenant ID>`
- `https://myapps.microsoft.com/<your verified domain>.onmicrosoft.com`
- `https://portal.azure.com/<your tenant ID>`

また、`https://myapps.microsoft.com/signin/X/<application ID?tenantId=<your tenant ID>` のようにテナント情報を含めることによって、アプリケーションまたはリソースへの直接リンクを Google ゲスト ユーザーに提供することもできます。

### Web ビュー サインイン サポートの廃止

2021 年の 9 月 30 日より、Google は[埋め込み Web ビューのサインイン サポートを廃止](https://developers.googleblog.com/2021/06/upcoming-security-changes-to-googles-oauth-2.0-authorization-endpoint.html)します。 アプリで埋め込み Web ビューを使用してユーザーを認証していて、Google フェデレーションを [Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/identity-provider-google)、Microsoft Entra B2B [(外部ユーザーの招待用)](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation)、または[セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers)で使用している場合、Google Gmail ユーザーが認証されなくなります。

Gmail ユーザーに影響を与える既知のシナリオを次に示します。

- Windows 上の Microsoft アプリ (Teams や Power Apps など)
- [WebView](https://learn.microsoft.com/ja-jp/windows/communitytoolkit/controls/wpf-winforms/webview) コントロール、[WebView2](https://learn.microsoft.com/ja-jp/microsoft-edge/webview2/)、または古い WebBrowser コントロールを認証で使用する Windows アプリ。 これらのアプリは、Web アカウント マネージャー (WAM) フローの使用へ移行する必要があります。
- WebView UI 要素を使用した Android アプリケーション
- UIWebView または WKWebview を使用した iOS アプリケーション
- [ADAL を使用したアプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-get-list-of-all-auth-library-apps)

この変更は、次のものには影響しません。

- Web アプリ
- web サイト経由でアクセスされる Microsoft 365 サービス (SharePoint オンライン、Office Web アプリ、Teams Web アプリなど)
- 認証でシステム Web ビューを使用したモバイルアプリ (iOS の [SFSafariViewController](https://developer.apple.com/documentation/safariservices/sfsafariviewcontroller)、Android の[カスタム タブ](https://developer.chrome.com/docs/android/custom-tabs/overview/))。
- Google Workspace ID (たとえば、Google Workspace との [SAML ベースのフェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)を使用している場合)
- Web アカウント マネージャー (WAM) または Web 認証ブローカー (WAB) を使用する Windows アプリ。

#### 埋め込みウェブビューに対する対応が必要です

サインインにシステム ブラウザーを使用するようにアプリを変更します。 詳細については、MSAL.NET ドキュメントの「[埋め込み Web ビューとシステム ブラウザー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers#embedded-web-view-vs-system-browser)」を参照してください。 すべての MSAL SDK が既定でシステム ブラウザーを使います。

#### 期待されること

Microsoft は、9 月 30 日より、埋め込み Web ビューを現在も使用しているアプリケーションの認証がブロックされないようにするための回避策となる、デバイスのサインイン フローをグローバルに展開します。

#### デバイスのサインイン フローでサインインする方法

デバイスのサインイン フローでは、Gmail アカウントを使用して埋め込み Web ビューからサインインを行ったユーザーに対して、サインインを完了する前に別のブラウザーでコードを入力するように求めるメッセージを表示します。 ユーザーが、ブラウザーにアクティブなセッションがない状態で初めて Gmail アカウントでサインインした場合、次のような一連の画面が表示されます。 既存の Gmail アカウントで既にサインインしている場合は、これらの手順の一部が省略される可能性があります。

1. **[サインイン]** 画面で、ユーザーが自身の Gmail のアドレスを入力し、 **[次へ]** を選択します。

    [Image: サインイン画面を示すスクリーンショット]
2. 次の画面が表示され、ユーザーに新しいウィンドウを開き、 https://microsoft.com/deviceloginに移動し、表示される 9 桁の英数字コードを入力するように求められます。

    [Image: 9 桁のコードを示すスクリーンショット]
3. ユーザーがコードを入力できる、デバイスのサインイン ページが開きます。

    [Image: デバイスのサインイン ページを示すスクリーンショット]
4. コードが一致する場合、セキュリティ上の理由から、ユーザーはアプリとサインインの場所を確認するためにメール アドレスの再入力を求められます。

    [Image: メールアドレスを再表示するための画面を示すスクリーンショット]
5. ユーザーは、自身のメールアドレスとパスワードを使用して Google にサインインします。

    [Image: Google のサインイン画面を示すスクリーンショット]
6. ここでも、サインインしようとしているアプリを確認するように求められます。

    [Image: アプリケーションの確認画面を示すスクリーンショット]
7. ユーザーが **[続行]** を選択します。 サインインが完了したことを確認するメッセージが表示されます。 ユーザーがタブまたはウィンドウを閉じると、アプリにサインインした最初の画面に戻ります。

    [Image: サインインの確認を示すスクリーンショット]

または、既存の Gmail ユーザーと新しい Gmail ユーザーに、電子メールのワンタイム パスコードを使用してサインインさせることもできます。 Gmail ユーザーが電子メールのワンタイム パスコードを使用するようにするには、次のようにします。

1. [メールのワンタイム パスコードを有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode#enable-or-disable-email-one-time-passcodes)。
2. [Google フェデレーションを削除します](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation#how-do-i-remove-google-federation)。
3. Gmail ユーザーの[引き換えステータスをリセット](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)して、今後、電子メールのワンタイムパスコードを使用できるようにします。

延長を要求する場合、影響を受ける OAuth クライアント ID をお持ちのお客様には、Google Developers から、2022 年 1 月 31 日までに完了しなければならない 1 回限りのポリシー施行延長に関する以下の情報が記載されたメールが届いているはずです。

- "If necessary, you may request a one-time **policy enforcement extension for embedded webviews** for each listed OAuth client ID until January 31, 2022. (必要に応じて、記載されている OAuth クライアント ID ごとに、2022 年 1 月 31 日までであれば、埋め込み Web ビューのポリシー施行延長を 1 回に限り、要求することができます。) For clarity, the policy for embedded webviews will be enforced on February 1, 2022 with no exceptions or extensions." (明確にするために、埋め込み Web ビューのポリシーは 2022 年 2 月 1 日に施行され、例外や延長はありません。)

許可された認証用 Web ビューに移行しているアプリケーションには影響はなく、ユーザーは通常どおり Google 経由で認証することができます。

許可された認証用 Web ビューにアプリケーションが移行されない場合、影響を受ける Gmail ユーザーには次の画面が表示されます。

[Image: アプリがシステム ブラウザーに移行されない場合の Google サインイン エラー]

#### CEF/Electron と埋め込み Web ビューを区別する

埋め込み Web ビューとフレームワーク サインイン サポートの廃止に加えて、Google は [Chromium Embedded Framework (CEF) ベースの Gmail 認証の廃止](https://developers.googleblog.com/2020/08/guidance-for-our-effort-to-block-less-secure-browser-and-apps.html)も行います。 Electron アプリなど、CEF で構築されたアプリケーションについて、Google は 2021 年 6 月 30 日に認証を無効にします。 影響を受けるアプリケーションには Google から直接通知が送られており、このドキュメントでは説明していません。 このドキュメントは、Google が別の日付 (2021 年 9 月 30 日) に制限する予定である、前に説明した埋め込み Web ビューに関連しています。

#### 埋め込みフレームワークに必要な対応

[Google のガイダンス](https://developers.googleblog.com/2016/08/modernizing-oauth-interactions-in-native-apps.html)に従って、ご利用のアプリが影響を受けるかどうかを判断します。

### 手順 1:Google 開発者プロジェクトを構成する

最初に、Google Developers Console で新しいプロジェクトを作成して、Microsoft Entra 外部 ID に後で追加するクライアント ID とクライアント シークレットを取得します。

1. https://console.developers.google.com で Google API に移動し、Google アカウントでサインインします。 共有のチーム Google アカウントを使用することをお勧めします。
2. サービスの使用条件への同意を求めるメッセージが表示されたらそのようにします。
3. 新しいプロジェクトを作成する: ページの上部にあるプロジェクト メニューを選択して、**[プロジェクトの選択]** ページを開きます。 **[新しいプロジェクト]** を選択します。
4. **[新しいプロジェクト]** ページで、プロジェクトに名前 (「`MyB2BApp`」など) を付け、**[作成]** を選択します。

    [Image: [新しいプロジェクト] ページを示すスクリーンショット。]
5. **[通知]** メッセージ ボックスでリンクを選択するか、ページの上部にあるプロジェクト メニューを使用して、新しいプロジェクトを開きます。
6. 左側のメニューで、**[API とサービス]** を選択し、次に **[OAuth 同意画面]** を選択します。
7. **[User Type] (ユーザーの種類)** で、**[外部]** を選択し、**[作成]** を選択します。
8. **[OAuth 同意画面]** の **[App information] (アプリ情報)** で、**[Application name] (アプリケーション名)** を入力します。
9. **[User support email] (ユーザー サポートのメール)** でメール アドレスを選択します。
10. **[Authorized domains] (承認済みドメイン)** の **[Add domain] (ドメインの追加)** を選択してから `microsoftonline.com` ドメインを追加します。
11. **[Developer contact information] (開発者の連絡先情報)** で、メール アドレスを入力します。
12. **[Save and continue]** (保存して続行) を選択します。
13. 左側のメニューで、**[Credentials] (認証情報)** を選択します。
14. **[Create credentials] (認証情報の作成)** を選択してから、**[OAuth client ID] (OAuth クライアント ID)** を選択します。
15. [アプリケーションの種類] メニューの **[Web アプリケーション]** を選択します。 アプリケーションの適切な名前を指定します (`Microsoft Entra B2B` など)。 **[Authorized redirect URIs](承認されたリダイレクト URI)** に、次の URI を追加します。

    - `https://login.microsoftonline.com`
    - `https://login.microsoftonline.com/te/<tenant ID>/oauth2/authresp`(`<tenant ID>` はご利用のテナントの ID です)
    - `https://login.microsoftonline.com/te/<tenant name>.onmicrosoft.com/oauth2/authresp`(`<tenant name>` はご利用のテナント名です)

    注意

    顧客テナント ID を見つけるには、[Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。 [ **Entra ID**] で [ **概要** ] を選択し、 **テナント ID を**コピーします。
16. **［作成］** を選択します ご自分のクライアント ID とクライアント シークレットをコピーします。 これらはMicrosoft Entra管理センターで ID プロバイダーを追加するときに使用します。

    [Image: OAuth クライアント ID とクライアント シークレットを示すスクリーンショット。]
17. **テスト**の発行ステータスでプロジェクトを終了し、OAuth 同意画面にテスト ユーザーを追加することができます。 または、OAuth の同意画面 で **[アプリの発行]** ボタンを選択して、Google アカウントを持つ任意のユーザーがアプリを使用できます。

    注意

    場合によっては、アプリで Google による確認が必要になる場合があります (たとえば、アプリケーションのロゴを更新した場合)。 詳細については、Google の[確認ステータスに関するヘルプ](https://support.google.com/cloud/answer/10311615#verification-status)を参照してください。

### 手順 2: Microsoft Entra 外部 IDで Google フェデレーションを構成する

次に、Google クライアント ID とクライアント シークレットを設定します。 これを行うには、Microsoft Entra 管理センターまたは PowerShell を使用できます。 自分自身を招待することで、Google フェデレーションの構成をテストしてください。 Gmail アドレスを使用し、招待状を受け取った Google アカウントで引き換えを試みてください。

**Microsoft Entra 管理センターで Facebook フェデレーションを構成するには**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
2. **[ID]**&gt;**[External Identities]**&gt;**[すべての ID プロバイダー]** に移動し、**[Google]** の行で **[構成]** を選択します。
3. 前に取得したクライアント ID とクライアント シークレットを入力します。 **[保存]** を選択します。

    [Image: Google ID プロバイダーの追加ページを示すスクリーンショット。]

**PowerShell を使用して Google フェデレーションを構成するには**

1. [Microsoft Graph PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)の最新バージョンをインストールします。
2. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドを使用してテナントに接続します。
3. サインイン プロンプトで、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
4. 次のコマンドを実行します。

    ```powershell
    $params = @{
       "@odata.type" = "microsoft.graph.socialIdentityProvider"
       displayName = "Login with Google"
       identityProviderType = "Google"
       clientId = "<client ID>"
       clientSecret = "<client secret>"
    }
    
    New-MgIdentityProvider -BodyParameter $params
    ```

    注意

    「手順 1: Google 開発者プロジェクトを構成する」で作成したアプリのクライアント ID とクライアント シークレットを使用します。詳細については、「[New-MgIdentityProvider](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/new-mgidentityprovider)」を参照してください。

### ユーザー フローに Google ID プロバイダーを追加する

この時点で、Google ID プロバイダーは Microsoft Entra テナントで設定されています。 招待を引き換えたユーザーは、Google を使ってサインインできます。 ただし、セルフサービス サインアップ ユーザー フローを作成した場合は、ユーザー フローのサインイン ページに Google を追加する必要もあります。 Google ID プロバイダーをユーザー フローに追加するには、次のようにします。

1. **Entra ID**&gt;**外部アイデンティティ**&gt;**ユーザーフロー**を参照します。
2. Google ID プロバイダーを追加するユーザー フローを選択します。
3. 設定で、**ID プロバイダー** を選択します。
4. ID プロバイダーの一覧で、**Google** を選びます。
5. **[保存]** を選択します。

### Google フェデレーションを削除する方法

Google フェデレーション セットアップは削除できます。 そのようにした場合、招待を既に引き換えた Google ゲスト ユーザーは、サインインできません。 ただし、[ユーザーの引き換え状態をリセットする](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)ことで、リソースへのアクセスを再度許可することができます。

**Microsoft Entra 管理センターで Google フェデレーションを削除するには**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
2. **Entra ID**&gt;**エクスターナルアイデンティティ**&gt;**すべてのIDプロバイダーを閲覧します**。
3. **[Google]** の行で、(**構成済み**) を選択し、**[削除]** を選択します。
4. **[はい]** を選択して、削除を確認します。

**PowerShell を使用して Google フェデレーションを削除するには**

1. [Microsoft Graph PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)の最新バージョンをインストールします。
2. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドを使用してテナントに接続します。
3. サインイン プロンプトで、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
4. 次のコマンドを入力します。

    ```powershell
    Remove-MgIdentityProvider -IdentityProviderBaseId Google-OAUTH
    ```

    注意

    詳細については、「[Remove-MgIdentityProvider](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/remove-mgidentityprovider)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/hybrid-cloud-to-on-premises"} -->
## B2B ユーザーにオンプレミスのアプリへのアクセス許可する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra B2B コラボレーションを使用して、クラウド B2B ユーザーにオンプレミス アプリへのアクセス権を付与する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

組織が Microsoft Entra B2B コラボレーション機能を使用して、パートナー組織のゲスト ユーザーを招待する場合、これらの B2B ユーザーにオンプレミスのアプリへのアクセスを提供できるようになりました。 これらのオンプレミス アプリは、SAML ベースの認証または統合 Windows 認証 (IWA) を Kerberos の制約付き委任 (KCD) と共に使用できます。

### SAML アプリケーションへのアクセス

オンプレミスのアプリで SAML による認証を使用する場合は、Microsoft Entra 管理センター で Microsoft Entra アプリケーション プロキシを使用して、Microsoft Entra B2B コラボレーション ユーザーがそれらのアプリを利用できるように、簡単に設定できます。

以下の操作を行う必要があります。

- アプリケーション プロキシを有効にしてコネクターをインストールする。 手順については、[Microsoft Entra アプリケーション プロキシを使用したアプリケーションの発行](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)に関する記事をご覧ください。
- 「[アプリケーション プロキシを使用したオンプレミスのアプリケーションに対する SAML シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-sso-apps)」の説明に従って、Microsoft Entra アプリケーション プロキシ経由で、オンプレミスの SAML ベースのアプリケーションを公開します。
- Microsoft Entra B2B ユーザーを SAML アプリケーションに割り当てます。

上の手順を完了したら、アプリが動作します。 Microsoft Entra B2B アクセスをテストするには、次のようにします。

1. ブラウザーを開き、アプリを発行したときに作成した外部 URL に移動します。
2. アプリに割り当てた Microsoft Entra B2B アカウントでサインインします。 アプリを開き、シングル サインオンでアクセスできます。

### IWA および KCD アプリへのアクセス

B2B ユーザーに、統合 Windows 認証と Kerberos の制約付き委任を使用してセキュリティで保護されたオンプレミス アプリケーションへのアクセスを提供するには、次のコンポーネントが必要です。

- **Microsoft Entra アプリケーション プロキシを使用した認証**。 B2B ユーザーは、オンプレミス アプリケーションに対して認証できる必要があります。 これを行うには、Microsoft Entra アプリケーション プロキシを介してオンプレミス アプリを公開する必要があります。 詳細については、[アプリケーション プロキシを使用したリモート アクセスを行うためのオンプレミス アプリケーションの追加に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)を参照してください。
- **オンプレミス ディレクトリの B2B ユーザー オブジェクトを介した承認**。 アプリケーションは、ユーザー アクセス チェックを実行し、正しいリソースへのアクセス権を付与できる必要があります。 IWA と KCD がこの承認を完了するには、オンプレミスの Windows Server Active Directory 内のユーザー オブジェクトが必要です。 「[KCD を使ったシングル サインオンのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd#how-single-sign-on-with-kcd-works)」で説明されているように、アプリケーション プロキシはこのユーザー オブジェクトを使用してユーザーを偽装し、アプリに対する Kerberos トークンを取得する必要があります。

    注

    Microsoft Entra アプリケーション プロキシを構成するときに、統合 Windows 認証 (IWA) のシングル サインオン構成で **[委任されたログオン ID]** が **[ユーザー プリンシパル名]** (既定値) に設定されていることを確認してください。

    B2B ユーザーのシナリオでは、オンプレミス ディレクトリでの承認に必要なゲスト ユーザー オブジェクトを作成できる方法が 2 つあります。

    - Microsoft Identity Manager (MIM) と Microsoft Graph 用 MIM 管理エージェント。
    - PowerShell スクリプトは、MIM を必要としない、より軽量なソリューションです。

次の図は、Microsoft Entra アプリケーション プロキシと、オンプレミス ディレクトリ内の B2B ユーザー オブジェクトの生成を連携して、B2B ユーザーにオンプレミス IWA および KCD アプリへのアクセスを許可する方法の概要を示しています。 番号が付いた手順については、図の下の詳細な説明を参照してください。

[Image: IWA および KCD アプリ アクセス用の Microsoft Entra アプリケーション プロキシ フローと、オンプレミスのゲスト ユーザー オブジェクトの MIM または PowerShell スクリプトの作成を示す図。]

1. パートナー組織のユーザー (Fabrikam テナント) が Contoso テナントに招待されます。
2. ゲスト ユーザー オブジェクトが Contoso テナントに作成されます (たとえば、UPN が guest\_fabrikam.com#EXT#@contoso.onmicrosoft.com のユーザー オブジェクト)。
3. MIMO または B2B PowerShell スクリプトを介して Fabrikam ゲストが Contoso からインポートされます。
4. Fabrikam ゲスト ユーザー オブジェクト (Guest#EXT#) の表現つまり "フットプリント" が、MIM または B2B PowerShell スクリプトを介してオンプレミス ディレクトリの Contoso.com に作成されます。
5. ゲスト ユーザーがオンプレミスのアプリケーションの app.contoso.com にアクセスします。
6. 認証要求が、Kerberos の制約付き委任を使用して Application Proxy を介して承認されます。
7. ゲスト ユーザー オブジェクトがローカルに存在するため、認証は成功します。

#### ライフサイクル管理ポリシー

ライフサイクル管理ポリシーを使用して、オンプレミス B2B ユーザー オブジェクトを管理できます。 次に例を示します。

- アプリケーション プロキシ認証中に多要素認証 (MFA) が使われるようにゲスト ユーザーの MFA ポリシーを設定することができます。 詳細については、「[B2B コラボレーション ユーザーの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)」を参照してください。
- クラウド B2B ユーザーに対して実行されるスポンサー プラン、アクセス レビュー、アカウント検証は、オンプレミス ユーザーに適用されます。 たとえば、ライフサイクル管理ポリシーを使用してクラウド ユーザーが削除された場合、MIM 同期または Microsoft Entra B2B スクリプトによってオンプレミス ユーザーも削除されます。 詳細については、「[Microsoft Entra のアクセス レビューによるゲスト アクセスの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-guest-access-with-access-reviews)」を参照してください。

#### Microsoft Entra B2B スクリプトを使用して B2B ゲスト ユーザー オブジェクトを作成する

[Microsoft Entra B2B サンプル スクリプト](https://github.com/Azure-Samples/B2B-to-AD-Sync)を使用して、Microsoft Entra B2B アカウントから同期されたシャドウ Microsoft Entra アカウントを作成できます。 その後、KCD を使用するオンプレミス アプリにシャドウ アカウントを使用できます。

#### MIM を介した B2B ゲスト ユーザー オブジェクトの作成

MIM および Microsoft Graph 用の MIM コネクタを使用して、オンプレミス ディレクトリにゲスト ユーザー オブジェクトを作成できます。 詳しくは、[Azure アプリケーション プロキシを使用した Microsoft Entra B2B (Business-to-Business) と Microsoft Identity Manager (MIM) 2016 SP1 のコラボレーション](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016-graph-b2b-scenario)に関するページを参照してください。

### ライセンスに関する考慮事項

オンプレミス アプリにアクセスする外部ゲスト ユーザー、またはオンプレミスで ID が管理されている外部ゲスト ユーザーに対して、正しいクライアント アクセス ライセンス (CAL) または外部コネクタがあることを確認してください。 詳細については、「[クライアント アクセス ライセンスと マネジメント ライセンス](https://www.microsoft.com/licensing/product-licensing/client-access-license.aspx)」の「エクスターナル コネクタ」セクションを参照してください。 具体的なライセンス要件については、マイクロソフトの担当者または地域の販売代理店にお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/hybrid-on-premises-to-cloud"} -->
## B2B ユーザーとしてローカル パートナー アカウントをクラウドに同期する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-on-premises-to-cloud
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra B2B コラボレーションと同じ資格情報を使用して、ローカルで管理されている外部パートナーにローカル リソースとクラウド リソースの両方へのアクセス権を付与します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra ID の前に、オンプレミスの ID システムを持つ組織は、オンプレミスディレクトリにマネージド パートナー アカウントを持っています。 このような組織では、アプリケーションを Microsoft Entra ID に移行するときに、パートナーが必要なリソースにアクセスできるようにする必要があります。 リソースがオンプレミスにあるかクラウド内にあるかは問題ではありません。 また、パートナー ユーザーがオンプレミスと Microsoft Entra の両方のリソースに同じサインイン資格情報を使用できるようにする必要があります。

オンプレミス ディレクトリに外部パートナーのアカウントを作成する場合 (たとえば、partners.contoso.com ドメインで Maria Sullivan という外部ユーザーのためにサインイン名が "msullivan" のアカウントを作成する場合)、これらのアカウントをクラウドと同期できます。 具体的には、 [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) を使用してパートナー アカウントをクラウドに同期し、UserType = Guest を使用してユーザー アカウントを作成できます。 この構成により、パートナー ユーザーはローカル アカウントと同じ資格情報を使用してクラウド リソースにアクセスできます。必要以上のアクセス権を付与する必要はありません。 ローカル ゲスト アカウントの変換の詳細については、「[ローカル ゲスト アカウントを Microsoft Entra B2B ゲスト アカウントに変換する](https://learn.microsoft.com/ja-jp/entra/architecture/10-secure-local-guest)」をご覧ください。

注

[B2B コラボレーションへの内部ユーザーの招待](https://learn.microsoft.com/ja-jp/entra/external-id/invite-internal-users)も参照してください。 この機能を使用すると、B2B コラボレーションを使用するように内部ゲスト ユーザーを招待することができます。そのアカウントをオンプレミス ディレクトリからクラウドに同期したかどうかは関係ありません。 ユーザーが招待を受け入れると、ユーザーは自分の ID と資格情報を使用して、アクセスするリソースにサインインできます。 パスワードを維持したり、アカウントのライフサイクルを管理したりする必要はありません。

### UserType の一意の属性を識別する

UserType 属性の同期を有効にする前に、まず、UserType 属性をオンプレミス Active Directory から派生させる方法を決める必要があります。 つまり、オンプレミス環境内で、外部コラボレーターに対して一意のパラメーターはどれでしょうか。 こうした外部コラボレーターと、組織のメンバーを区別するパラメーターを特定してください。

パラメーターを定義する 2 つの一般的な方法は次のとおりです。

- ソース属性として使用する未使用のオンプレミス Active Directory 属性 (extensionAttribute1 など) を指定する。
- または、UserType 属性の値を他のプロパティから派生させる。 たとえば、オンプレミス Active Directory UserPrincipalName 属性の末尾がドメイン *@partners.contoso.com* である場合、すべてのユーザーをゲストとして同期する必要があるとします。

詳細な属性要件については、[UserType の同期の有効化](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration#enable-synchronization-of-usertype)に関するページをご覧ください。

### Microsoft Entra Connect を構成してユーザーをクラウドに同期する

一意の属性を特定したら、これらのユーザーをクラウドに同期させるように Microsoft Entra Connect を構成できます。これにより、UserType = Guest でユーザー アカウントが作成されます。 承認の観点から、これらのユーザーと、Microsoft Entra B2B コラボレーション招待プロセスによって作成された B2B ユーザーを区別することはできません。

実装手順については、[UserType の同期の有効化](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration#enable-synchronization-of-usertype)に関するページをご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/hybrid-organizations"} -->
## ハイブリッド組織向けの B2B コラボレーション - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-organizations
- Service: entra-external-id / external
- Article date: 2024-10-21
- Summary: Microsoft Entra B2B コラボレーションによって、パートナーがオンプレミスのリソースとクラウド リソースの両方にアクセスできるようにします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra B2B コラボレーションにより、外部パートナーが組織内のアプリやリソースに簡単にアクセスできるようにすることができます。 これは、オンプレミスとクラウドベースの両方のリソースがあるハイブリッド構成であっても同様です。 現在、外部パートナーのアカウントをオンプレミスの ID システムでローカルに管理していても、または Microsoft Entra B2B ユーザーとしてクラウド内で管理していても構いません。 いずれの場所のリソースに対しても、両方の環境に同じサインイン資格情報を使用して、ユーザーにアクセス権を付与できるようになりました。

### Microsoft Entra ID の B2B ユーザーにオンプレミスのアプリへのアクセスを許可する

組織が Microsoft Entra B2B コラボレーション機能を使用して、パートナー組織のゲスト ユーザーを Microsoft Entra ID に招待する場合、これらの B2B ユーザーにオンプレミスのアプリへのアクセスを提供できるようになりました。

SAML ベースの認証を使用するアプリの場合、Azure portal を通じて、認証に Microsoft Entra アプリケーション プロキシを使用して B2B ユーザーがこれらのアプリを入手できるようにすることができます。

統合 Windows 認証 (IWA) を Kerberos の制約付き委任 (KCD) と共に使用するアプリの場合は、認証に Microsoft Entra ID プロキシを使用することもできます。 ただし、承認を機能させるには、オンプレミスの Windows Server Active Directory にユーザー オブジェクトが必要です。 B2B ゲスト ユーザーを表すローカル ユーザー オブジェクトの作成に使用できる方法は 2 つあります。

- Microsoft Identity Manager (MIM) 2016 SP1 と、Microsoft Graph 用 MIM 管理エージェントです。
- PowerShell スクリプトを使用できます。 (このソリューションでは、MIM は不要です。)

これらのソリューションを実装する方法の詳細については、[Microsoft Entra B2B ユーザーに対するオンプレミス アプリケーションへのアクセスの許可](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises)に関するページを参照してください。

### ローカルで管理されているパートナーのアカウントにクラウド リソースへのアクセスを付与する

Microsoft Entra ID 以前は、オンプレミスの ID システムを持つ組織は、パートナーのアカウントを従来、オンプレミスのディレクトリで管理してきました。 このような組織では、アプリとその他のリソースをクラウドに移動した場合に、パートナーが引き続きアクセスできることを確認する必要があります。 これらのユーザーが同じ資格情報セットを使用して、クラウドとオンプレミスの両方のリソースにアクセスできるようにするのが理想的です。

Microsoft Entra Connect を使用して "ゲスト ユーザー" としてこれらのローカル アカウントをクラウドと同期したり、アカウントを Microsoft Entra B2B ユーザーと同じように動作させたりすることができるようになりました。

会社のデータを保護するために、適切なリソースのみへのアクセスを制御し、これらのゲスト ユーザーを自社の従業員と区別して処理する承認ポリシーを構成できます。

実装の詳細については、[Microsoft Entra B2B コラボレーションを使用した、ローカルで管理されたパートナー アカウントへに対するクラウド リソースへのアクセスの許可](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-on-premises-to-cloud)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/identity-providers"} -->
## 職場テナントの ID プロバイダー - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra ID を外部ユーザーと共有するための既定の ID プロバイダーとして使用する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ヒント

この記事の対象は、従業員テナントでの B2B コラボレーションです。 外部テナントの詳細については、[外部テナントの認証方法と ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)に関する記事をご覧ください。

*ID プロバイダー* (IdP) は、アプリケーションに認証サービスを提供しながら、ID 情報を作成、維持、管理します。 アプリとリソースを外部ユーザーと共有する場合は、Microsoft Entra ID が共有のための既定の ID プロバイダーです。 Microsoft Entra アカウントまたは Microsoft アカウントを既に持っている外部ユーザーを招待すると、それ以上構成を行わなくても、そのユーザーは自動的にサインインできます。

外部 ID では、さまざまな ID プロバイダーを提供しています。

- **Microsoft Entra アカウント**: ゲスト ユーザーは、Microsoft Entra の職場または学校アカウントを使用して、B2B コラボレーションの招待を利用したり、サインアップ ユーザー フローを完了したりできます。 [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/external-id/default-account) は、既定で許可されている ID プロバイダーの 1 つです。 この ID プロバイダーをユーザー フローで使用できるようにするために必要なその他の構成はありません。
- **Microsoft アカウント**: ゲスト ユーザーは、自分自身の個人用 Microsoft アカウント (MSA) を使用して、B2B コラボレーションの招待を引き換えることができます。 [セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)のユーザー フローを設定するときには、許可される ID プロバイダーの 1 つとして [Microsoft アカウント](https://learn.microsoft.com/ja-jp/entra/external-id/microsoft-account)を追加できます。 この ID プロバイダーをユーザー フローで使用できるようにするために必要なその他の構成はありません。
- **ワンタイム パスコードをメールで送信する**: ゲストは、招待に応じるとき、または共有リソースにアクセスするときに、一時的なコードを要求できます。 このコードがメール アドレスに送信されます。 その後は、このコードを入力してサインインを続けます。 電子メール ワンタイム パスコード機能では、B2B ゲスト ユーザーが他の手段を使用して認証できないときにユーザーを認証します。 セルフサービス サインアップのユーザー フローを設定するときには、許可される ID プロバイダーの 1 つとして**電子メール ワンタイム パスコード**を追加できます。 いくつかの設定が必要になります。「[電子メール ワンタイム パスコード認証](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)」を参照してください。
- **Google**: Google フェデレーションでは、外部ユーザーが自分の Gmail アカウントでアプリにサインインすることにより、あなたからの招待を利用することができます。 Google フェデレーションは、セルフサービスのサインアップ ユーザー フローでも使用できます。 [ID プロバイダーとして Google を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation)方法に関するページを参照してください。

    重要

    - 2021 年 7 月 12 日以降、Microsoft Entra B2B のお客様がカスタムまたは基幹業務アプリケーションのセルフサービス サインアップ用に新しい Google 統合を設定した場合、認証がシステム Web ビューに移動されるまで、Google ID による認証は機能しません。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation#deprecation-of-web-view-sign-in-support) を参照してください。
    - 2021 年 9 月 30 日以降、Google は [埋め込み Web ビュー サインインのサポートを非推奨にしました](https://developers.googleblog.com/2016/08/modernizing-oauth-interactions-in-native-apps.html)。 アプリが埋め込み Web ビューを使用してユーザーを認証し、[Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/identity-provider-google) または Microsoft Entra B2B を [外部ユーザーの招待](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation) またはセルフサービス サインアップに使用している場合、その場合は Google の Gmail ユーザーは認証できません。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation#deprecation-of-web-view-sign-in-support) を参照してください。
- **Facebook**: アプリを構築するときに、セルフサービス サインアップを構成し、Facebook フェデレーションを有効にすることで、ユーザーが自分の Facebook アカウントを使用してアプリにサインアップできるようにすることが可能です。 Facebook は、セルフサービス サインアップ ユーザー フローにのみ使用でき、ユーザーがあなたからの招待を利用するときにサインイン オプションとして使用することはできません。 [ID プロバイダーとして Facebook を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/facebook-federation)方法に関するページを参照してください。
- **SAML/WS-Fed ID プロバイダー フェデレーション**: SAML または WS-Fed プロトコルをサポートする任意の外部 ID プロバイダーとのフェデレーションを設定することもできます。 SAML/WS-Fed IdP フェデレーションを使用すると、外部ユーザーは独自の IdP マネージド アカウントを使用してアプリまたはリソースにサインインできます。新しい Microsoft Entra 資格情報を作成する必要はありません。 詳細については、「 [SAML/WS-Fed ID プロバイダー」を](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-overview)参照してください。 詳細なセットアップ手順については、「 [SAML/WS-Fed ID プロバイダーとのフェデレーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)」を参照してください。

Google、Facebook、または SAML/WS-Fed ID プロバイダーとのフェデレーションを構成するには、少なくとも Microsoft Entra テナントの [外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator) である必要があります。

### ソーシャル ID プロバイダーの追加

Azure AD はセルフサービス サインアップが既定で有効になっているため、ユーザーは常に Microsoft Entra アカウントを使用してサインアップすることができます。 ただし、Google や Facebook のようなソーシャル ID プロバイダーを含め、その他の ID プロバイダーを有効にできます。 Microsoft Entra テナントでソーシャル ID プロバイダーを設定するには、その ID プロバイダーでアプリケーションを作成し、資格情報を構成します。 クライアントまたはアプリの ID と、クライアントまたはアプリのシークレットを取得して、Microsoft Entra テナントに追加できます。

Microsoft Entra テナントに ID プロバイダーを追加した後、次のようにします。

- 組織内のアプリまたはリソースに外部ユーザーを招待すると、外部ユーザーはその ID プロバイダーでの自分のアカウントを使用してサインインできるようになります。
- アプリに対して[セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)を有効にすると、外部ユーザーは、追加した ID プロバイダーでの自分のアカウントを使用して、アプリにサインアップできます。 サインアップ ページで使用できるようにしたソーシャル ID プロバイダーのオプションから選択できます。

    [Image: Google と Facebook のオプションが表示されたサインイン画面のスクリーンショット]

最適なサインイン エクスペリエンスにするには、可能な限り ID プロバイダーとフェデレーションします。これにより、招待されたゲストがアプリにアクセスしたときに、シームレスなサインイン エクスペリエンスを提供できるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/index-b2b"} -->
## ビジネス ゲストの外部 ID に関するドキュメント - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/index-b2b
- Service: entra-external-id / external
- Article date: 2025-04-15
- Summary: Microsoft Entra External ID を使用すると、ビジネス ゲストやパートナーとの安全で効率的な従業員コラボレーションが可能になります。

Microsoft Entra External ID は、アプリのゲスト ユーザー アクセス (B2B コラボレーション) や顧客 ID とアクセス管理 (CIAM) など、外部 ID のシナリオを管理するためのソリューションです。 B2B コラボレーション機能を使用して、ビジネス ゲストやパートナーとの従業員のコラボレーションをセキュリティで保護および管理する方法について説明します。

### 外部 ID について

#### 概要

- [外部 ID ソリューションの比較](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview)
- [Microsoft Entra B2B コラボレーションとは?](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)
- [Microsoft Entra B2B 直接接続とは?](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)

### 組織外のユーザーとの共同作業 (B2B)

#### 概念

- [テナント間アクセス設定の概要](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)
- [B2B コラボレーション ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)
- [B2B コラボレーション ユーザーが招待に応じる方法](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience)

#### 攻略ガイド

- [外部コラボレーションの設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)
- [B2B コラボレーションのためにテナント間アクセスを構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)
- [SAML/WS-Fed IdP フェデレーションを設定する](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)
- [ワンタイム パスコードの設定](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)
- [ゲスト ユーザーを追加して招待する](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)

### B2B ゲスト ユーザーのセルフサービス サインアップ フロー

#### 概要

- [セルフサービス サインアップとは](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)

#### sample

- [ユーザー フローの API コネクタ コード サンプル](https://learn.microsoft.com/ja-jp/entra/external-id/code-samples-self-service-sign-up)

#### 攻略ガイド

- [セルフサービス サインアップのユーザー フローを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow)
- [ユーザー フローのカスタム属性を定義する](https://learn.microsoft.com/ja-jp/entra/external-id/user-flow-add-custom-attributes)

### マルチテナント組織

#### 概要

- [マルチテナント組織の機能](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview)
- [マルチテナント組織とは?](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview)
- [テナント間同期とは?](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/invitation-email-elements"} -->
## B2B 招待について - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/invitation-email-elements
- Service: entra-external-id / external
- Article date: 2025-12-05
- Summary: アプリの認証とアクセスが必要なビジネス パートナーや外部ゲスト ユーザーに送信できる B2B Collaboration 招待メールについて説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

招待メールは、Microsoft Entra B2B コラボレーション ユーザーとしてパートナーを歓迎するための鍵となります。 [必須ではありませんが](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#redemption-process-through-a-direct-link)、これらのメールは、受信者が招待を受け入れるかどうかを決定するのに役立つ重要な情報を提供します。 後でリソースにすばやくアクセスするためのリンクが含まれています。

[Image: B2B 招待メールのスクリーンショット。]

### 電子メールの説明

メールのいくつかの要素を確認して、その機能の使用方法を理解しましょう。 これらの要素は、一部の電子メール クライアントでは若干異なる場合があります。

#### サブジェクト

電子メールの件名行は、次のパターンに従います。`username``primary domain`との共同作業を依頼しました。 たとえば、Megan Bowen が Contoso というドメインから招待した場合、件名は "Megan Bowen が Contoso との共同作業に招待しました" です。

#### 差出人アドレス

From アドレスは、次のパターンに従います。 `primary domain``<invites@<primary domain>.onmicrosoft.com>`に代わって Microsoft の招待。 たとえば、Megan Bowen が Contoso から招待した場合、From アドレスは "Contoso invites@Contoso.onmicrosoft.com に代わって Microsoft の招待" になります。

2025 年 12 月より前は、招待は Microsoft Invitations invites@microsoftから送信されます。

注意

[中国の 21Vianet](https://learn.microsoft.com/ja-jp/azure/china/) が運営する Azure サービスでは、送信者のアドレスは `<primary domain>.partner.onmschina.cn` になります。[政府機関向け Microsoft Entra ID](https://learn.microsoft.com/ja-jp/azure/azure-government/) の場合、送信者アドレスは `<primary domain>.onmicrosoft.us` です。

#### 返信

電子メールの返信先は招待元の電子メール アドレス (使用可能な場合) に設定されるので、電子メールに返信すると、招待元に送信されます。

#### フィッシングの警告

メールは、予想される招待のみを受け入れるようにユーザーに通知する、簡単なフィッシング詐欺の警告から始まります。 招待を期待するように、パートナーに事前に通知することをお勧めします。

[Image: メール内のフィッシングの警告のスクリーンショット。]

#### 招待元の情報と招待メッセージ

このメールには、招待を送信する組織に関連付けられている名前とプライマリ ドメインが含まれています。 この情報は、招待された人が、招待を受け入れるかどうかを情報に基づいて決定するのに役立ちます。 招待者は、[ディレクトリ、グループ、またはアプリ](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)への招待の一部として、または[招待 API を使用する](https://learn.microsoft.com/ja-jp/entra/external-id/customize-invitation-api)ときに、メッセージを含めることができます。 メッセージは、メールのメイン セクションで強調表示されています。 招待元の名前とプロファイルの画像 (使用可能な場合) が含まれます。 メッセージ自体はテキスト領域であるため、セキュリティ上の理由から、HTML タグは処理されません。

[Image: メール内の招待メッセージのスクリーンショット。]

#### 招待ボタンまたはリンクとリダイレクト URL に同意する

メールの次のセクションでは、招待を受け入れた後に招待者がリダイレクトされる場所と、続行するためのボタンまたはリンクが表示されます。 将来、招待された人はいつでもこのリンクを使用してリソースに直接戻ることができます。

[Image: 電子メールの [承諾] ボタンとリダイレクト URL のスクリーンショット。]

#### フッター セクション

フッターには、招待に関する追加の詳細が表示されます。 組織が[プライバシー ステートメントを構成している](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area)場合は、ステートメントへのリンクがここに表示されます。 それ以外の場合は、組織のプライバシー ステートメントが利用できないことを示すメモが表示されます。

[Image: メールのフッター セクションを示すスクリーンショット。]

### 言語が決定される方法

次の設定により、招待メールでゲスト ユーザーに表示される言語が決まります。 設定は優先順位の順に一覧表示されます。 設定を構成しない場合は、一覧の次の設定によって言語が決まります。

- **Create invitation API** の [invitedUserMessageInfo](https://learn.microsoft.com/ja-jp/graph/api/resources/invitedusermessageinfo) オブジェクトの [messageLanguage](https://learn.microsoft.com/ja-jp/graph/api/invitation-post) プロパティ
- ゲストの**ユーザー オブジェクト**で指定されている [preferredLanguage](https://learn.microsoft.com/ja-jp/graph/api/resources/user) プロパティ
- ゲスト ユーザーのホーム テナントのプロパティに設定された **通知言語** (Microsoft Entra テナントのみ)
- リソース テナントのプロパティで設定されている**通知言語**

これらの設定を構成しない場合、言語は既定で英語 (米国) になります。

### カスタム ドメインの電子メール要件

招待メールが (既定の MOERA ドメインではなく) 組織のカスタム ドメインから送信される場合、配信を成功させるには、次の要件を満たす必要があります。

#### メールが有効なテナント

テナントは、Exchange Online (EXO) ライセンスを使用してメールが有効になっている必要があります。 これを行わないと、カスタム ドメインから招待メールを送信できません。

#### MOERA (Microsoft オンライン 電子メール ルーティング アドレス) ドメインを回避する

MOERA ドメイン (`.onmicrosoft.com`) は、次の理由から招待メールを送信しないことを **強くお勧めします** 。

- MOERAドメインには[スロットリング制限](https://techcommunity.microsoft.com/blog/exchange/limiting-onmicrosoft-domain-usage-for-sending-emails/4292065)が適用されます。
- MOERA ドメインから送信されたメールは、スパムとしてフィルター処理される可能性が高くなります。

これらの問題を回避するには、 [検証済みのカスタム ドメインをテナントの既定のドメインとして設定します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/setup/add-domain)。

#### DNS 構成 (SPF、DKIM、DMARC)

組織が送信メールをルーティングする方法に基づいて、電子メール認証レコードを DNS で構成する必要があります。 Microsoft Entra IDだけでカスタム ドメインを所有して確認するだけでは不十分です。DNS レコードも配置する必要があります。

- **送信メールは Exchange Online を直接経由します** — Microsoft 365 の設定に基づいて SPF、DKIM、DMARC を構成します。

    - [DNS レコードを追加してドメインに接続する](https://learn.microsoft.com/ja-jp/microsoft-365/admin/get-started/add-domain)
    - [SPF、DKIM、DMARC を設定する](https://learn.microsoft.com/ja-jp/defender-office-365/email-authentication-about)
- **サード パーティのゲートウェイ** (Proofpoint や Mimecast など) 経由の送信メール ルート — Microsoft 365ではなく、サード パーティのプロバイダーの要件に基づいて SPF、DKIM、DMARC を構成します。 SPF レコードはプロバイダーの送信 IP を承認する必要があり、DKIM 署名はプロバイダーのインフラストラクチャによって処理されます。

Important

組織が Exchange Online から送信メールを直接送信しない場合は、Microsoft 365 の SPF/DKIM レコードを DNS に **追加しないで** ください。 代わりに、送信メールを処理するサード パーティのサービスに DNS 認証レコードを配置します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/invite-internal-users"} -->
## 内部ユーザーを B2B コラボレーションに招待する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/invite-internal-users
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: パートナー、ディストリビューター、サプライヤー、ベンダー、その他のゲストの内部ユーザー アカウントがある場合は、独自の外部資格情報を使用してサインインするように招待することで、B2B コラボレーションMicrosoft Entraに移行できます。 PowerShell または Microsoft Graph 招待 API を使用します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra B2B コラボレーションを利用できるようになる前に、組織はディストリビューター、サプライヤー、ベンダー、その他のゲスト ユーザーと、内部資格情報を設定することで共同作業を行うことができました。 このような内部ゲスト ユーザーがいる場合、代わりに B2B コラボレーションを使用するように招待することができます。 これらの B2B ゲスト ユーザーは、自分の ID と資格情報を使用してサインインできるため、パスワードのメンテナンスやアカウントのライフサイクル管理が不要になります。

既存の内部アカウントに招待を送信すると、そのユーザーのオブジェクト ID、ユーザー プリンシパル名 (UPN)、グループ メンバーシップ、アプリの割り当てを保持できます。 ユーザーを手動で削除して再招待したり、リソースを再割り当てしたりする必要はありません。 ユーザーを招待するには、招待 API を使用して、内部ユーザー オブジェクトとゲスト ユーザーのメール アドレスの両方を招待と共に渡します。 ユーザーが招待に同意すると、B2B サービスによって既存の内部ユーザー オブジェクトが B2B ユーザーに変更されます。 今後、ユーザーは B2B 資格情報を使用してクラウド リソース サービスにサインインする必要があります。

### 考慮事項

- **オンプレミス リソースへのアクセス**: ユーザーが B2B コラボレーションに招待された後も、内部資格情報を使用してオンプレミスのリソースにアクセスできます。 内部アカウントのパスワードをリセットまたは変更することで、これを防ぐことができます。 例外は、電子メール ワンタイム パスコード認証です。ユーザーの認証方法がワンタイム パスコードに変更された場合、そのユーザーは内部資格情報を使用できなくなります。
- **課金**: この機能ではユーザーの UserType は変更されないため、ユーザーの課金モデルが [外部 ID の月間アクティブ ユーザー (MAU) の価格](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)に自動的に切り替わることはありません。 ユーザーの MAU の価格をアクティブにするには、ユーザーの UserType を `guest` に変更します。 また、MAU 課金をアクティブ化するには、Microsoft Entra テナントを Azure サブスクリプションにリンクする必要があることにも注意してください。
- **Teams**: ユーザーが外部資格情報を使用して Teams にアクセスすると、最初は Teams のテナント選択ツールにテナントが表示されません。 ユーザーは、テナント コンテキストを含む URL (例: `https://teams.microsoft.com/?tenantId=<TenantId>`) を使用して Teams にアクセスできます。 その後は、Teams テナント ピッカーでテナントを使用できるようになります。
- **オンプレミスの同期されたユーザー**: オンプレミスとクラウドの間で同期されるユーザー アカウントの場合、B2B コラボレーションの使用を招待された後も、オンプレミスディレクトリは権限のソースのままです。 アカウントの無効化や削除を含む、オンプレミスのアカウントに対して行ったすべての変更は、クラウド アカウントに同期されます。 そのため、ユーザーがオンプレミス アカウントを削除することで、クラウド アカウントを保持したままオンプレミス アカウントにサインインできないようにすることはできません。 その代わりに、オンプレミスのアカウントのパスワードをランダムな GUID またはその他の不明な値に設定することができます。

注

Microsoft Entra Connect Sync には、onPremisesUserPrincipalName 属性をユーザー オブジェクトに書き込む既定の規則があります。 この属性が存在すると、ユーザーが外部資格情報を使用してサインインできなくなる可能性があるため、この属性を使用するユーザー オブジェクトの内部から外部への変換をブロックします。 Microsoft Entra Connect を使用していて、内部ユーザーを B2B コラボレーションに招待できるようにする場合は、onPremisesUserPrincipalName 属性がユーザー オブジェクトに書き込まれないように、既定のルール>

### B2B コラボレーションに内部ユーザーを招待する方法

Microsoft Entra admin center、PowerShell、または招待 API を使用して、B2B 招待を内部ユーザーに送信できます。 注意事項:

- ユーザーを招待する前に、内部ユーザー オブジェクトの `User.Mail` プロパティ (Microsoft Entra admin centerのユーザーの **Email** プロパティ) が、B2B コラボレーションに使用する外部メール アドレスに設定されていることを確認します。 内部ユーザーに既存のメールボックスがある場合、このプロパティを外部メール アドレスに変更することはできません。 [Exchange admin center](https://learn.microsoft.com/ja-jp/exchange/exchange-admin-center)で属性を更新する必要があります。
- ユーザーを招待すると、招待が電子メールでユーザーに送信されます。 PowerShell または招待 API を使用している場合は、`SendInvitationMessage` を`False` に設定することで、この電子メールを抑制できます。 その後、別の方法でユーザーに通知できます。 [招待 API の詳細を確認](https://learn.microsoft.com/ja-jp/entra/external-id/customize-invitation-api)します。
- ユーザーが招待を引き換える場合、使用しているアカウントは`User.Mail`プロパティのドメインと一致する必要があります。 そうしないと、Teams などの一部のサービスがユーザーを認証できなくなります。

### Microsoft Entra admin centerを使用して B2B 招待を送信する

1. 少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 一覧でユーザーを見つけるか、検索ボックスを使用します。 次に、ユーザーを選択します。
4. [ **概要** ] タブの [ **個人用フィード**] で、[ **外部ユーザーに変換**] を選択します。

    B2B コラボレーション カードに [外部ユーザーに変換] アクションが表示されたMicrosoft Entra admin centerの [ユーザー プロファイルの概要] タブのスクリーンショット <>

    注

    カードに 「この B2B ユーザーの招待を再送信するか、引き換えの状態をリセットする」と表示されている場合、ユーザーは既に B2B コラボレーションに外部資格情報を使用するように招待されています。
5. 外部メール アドレスを追加し、[ **送信**] を選択します。

    [Image: 招待を送信する前に外部メール アドレスが入力されている [外部ユーザーに変換] ウィンドウのスクリーンショット。]

    注

    このオプションが使用できない場合は、ユーザーの **電子メール** プロパティが、B2B コラボレーションに使用する必要がある外部メール アドレスに設定されていることを確認します。
6. 確認メッセージが表示され、招待が電子メールでユーザーに送信されます。 その後、ユーザーは外部の資格情報を使用して招待を利用することができます。

### PowerShell を使用して B2B の招待を送信する

[最新のMicrosoft Graph PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)が必要です。 次のコマンドを使って、最新のモジュールに更新し、B2B コラボレーションに内部ユーザーを招待します。

```powershell
Update-Module Microsoft.Graph
Connect-MgGraph -Scopes "User.Invite.All","User.ReadWrite.All"
$msGraphUser = Get-MgUser -UserId '00aa00aa-bb11-cc22-dd33-44ee44ee44ee' 
New-MgInvitation -InvitedUserEmailAddress John@contoso.com -SendInvitationMessage:$true -InviteRedirectUrl "https://myapps.microsoft.com" -InvitedUser $msGraphUser
```

### 招待 API を使用して B2B の招待を送信する

次のサンプルは、招待 API を呼び出して、内部ユーザーを B2B ユーザーとして招待する方法を示しています。

```json
POST https://graph.microsoft.com/v1.0/invitations
Authorization: Bearer eyJ0eX...
Content-Type: application/json
{
    "invitedUserEmailAddress": "<<external email>>",
    "sendInvitationMessage": true,
    "invitedUserMessageInfo": {
        "messageLanguage": "en-US",
        "ccRecipients": [
            {
                "emailAddress": {
                    "name": null,
                    "address": "<<optional additional notification email>>"
                }
            }
        ],
        "customizedMessageBody": "<<custom message>>"
    },
    "inviteRedirectUrl": "https://myapps.microsoft.com?tenantId=<tenant-id>",
    "invitedUser": {
        "id": "<<ID for the user you want to convert>>"
    }
}
```

API への応答は、新しいゲストユーザーをディレクトリに招待したときと同じ応答になります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/leave-the-organization"} -->
## 組織を脱退する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/leave-the-organization
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: B2B Collaboration ユーザーとして、アプリへのゲスト ユーザー アクセスが不要になった場合に組織を脱退する方法について説明します。 管理者の場合は、外部ユーザーの脱退を許可する方法をご覧ください。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra B2B Collaboration または B2B 直接接続ユーザーは、アプリへのアクセスが不要になったらいつでも組織から退出できます。 組織を離れると、関連付けも終了します。

### 開始する前に

通常、管理者に連絡することなく独自の判断で組織を脱退することができます。 ただし、場合によっては、このオプションを使用できないため、外部組織のアカウントを削除できるテナント管理者に問い合わせる必要があります。

この記事には、組織を離れるユーザーと、外部ユーザーの休暇設定を管理する管理者向けのガイダンスが含まれています。

組織を管理および脱退する方法に関する情報を探しているユーザーの場合は、 [マイ アカウント ポータルの「職場または学校アカウントの組織を管理](https://support.microsoft.com/account-billing/manage-organizations-for-a-work-or-school-account-in-the-my-account-portal-a9b65a70-fec5-4a1a-8e00-09f99ebdea17)する」を参照してください。

### 自分が所属している組織を識別する

1. 所属している組織を表示するには、まず **[マイ アカウント]** ページを開きます。 組織によって作成された職場または学校アカウント、もしくは Xbox、Hotmail、Outlook.com などの個人用アカウントのいずれかがあります。

    - 職場または学校アカウントを使用している場合は、 https://myaccount.microsoft.com にアクセスしてサインインします。
    - 個人用アカウントまたはメールのワンタイム パスコードを使用する場合は、テナント名またはテナント ID を含むマイ アカウントの URL を使用する必要があります。 次に例を示します。

        `https://myaccount.microsoft.com?tenantId=contoso.onmicrosoft.com`

        または

        `https://myaccount.microsoft.com?tenantId=aaaabbbb-0000-cccc-1111-dddd2222eeee`

        この URL はプライベート ブラウザー セッションで開くことが必要な場合があります。
2. 左側のナビゲーション ウィンドウから **[組織]** を選択するか、 **[組織]** ブロックから **[組織の管理]** リンクを選択します。
3. **[組織]** ページが表示され、所属する組織を表示および管理できます。

    [Image: 所属する組織の一覧を示すスクリーンショット。]

    - **ホーム組織**: ホーム組織が一覧の最初に表示されます。 この組織は、ご自身の職場または学校のアカウントを所有しています。 個人のアカウントは組織の管理者によって管理されているため、ホーム組織からの脱退は許可されていません。 **[脱退]** へのリンクがないことがわかります。 ホーム組織の割り当てがない場合は、ご自身に関連付けられている組織の一覧を持つ**組織**という見出しだけが表示されます。
    - **共同作業を行う他の組織 (Other organizations you collaborate with)**: 職場または学校のアカウントを使用すると、以前にサインインしたことのある他の組織も表示されます。 これらの組織からの脱退は、いつでも決定できます。

### 組織を脱退する方法

ユーザーが外部組織から自分を削除することが、所属する組織によって許可されている場合は、次の手順に従って組織を脱退できます。

1. **[組織]** ページを開きます。 (上記の「自分が所属している組織を識別する」の手順に従います)。
2. **[共同作業を行う他の組織]** (またはホーム組織がない場合は **[組織]**) の下で、脱退する組織を見つけ、**[脱退]** を選択します。

    [Image: ユーザー インターフェイスに組織を脱退するオプションが示されたスクリーンショット。]
3. 確認を求められたら、**[脱退]** を選択します。
4. 組織に対して **[脱退]** を選択しても次のメッセージが表示される場合は、組織の管理者またはプライバシー連絡先に連絡し、組織から自分を削除するように依頼する必要があります。

    [Image: 組織から脱退するためにアクセス許可が必要な場合のメッセージを示すスクリーンショット。]

### 組織から脱退できない理由

**[ホーム組織]** セクションには、組織を**脱退**するリンクがありません。 ホーム組織からアカウントを削除できるのは管理者だけです。

**[共同作業を行う他の組織]** の下に一覧表示されている外部組織では、次の場合など、自分で脱退できない場合があります。

- あなたが脱退したい組織は、自分で脱退することができません。
- アカウントが無効になっている

こうした場合、**[脱退]** を選択できますが、組織の管理者またはプライバシー連絡先に問い合わせて、ユーザーの削除を依頼する必要があるというメッセージが表示されます。

### 管理者向けの詳細情報

管理者は、**[外部ユーザーの脱退設定]** を使用して、外部ユーザーが組織から自分を削除できるかどうかを制御できます。 外部ユーザーが自分を組織から削除できないようにした場合、外部ユーザーは管理者またはプライバシー連絡先に連絡して削除してもらう必要があります。

重要

**外部ユーザーの脱退設定**は、Microsoft Entra テナントに[プライバシー情報を追加した](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area)場合にのみ構成できます。 それ以外の場合、この設定は使用できなくなります。 外部ユーザーがポリシーを確認し、必要に応じてプライバシー連絡先にメールを送信できるように、プライバシー情報を追加することをお勧めします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**外部コラボレーション設定**に移動します。
3. **[外部ユーザーの脱退]** で、外部ユーザーが組織を脱退することを許可するかどうかを選択します。

    - **はい**: ユーザーは、管理者またはプライバシー連絡先からの承認なしに、組織を脱退することが可能です。
    - **いいえ**: ユーザーは自分で組織を離れることはできません。 管理者またはプライバシー連絡先に連絡して組織からの削除を依頼するようにガイドするメッセージが表示されます。

    [Image: ポータルの外部ユーザーの脱退設定を示すスクリーンショット。]

#### アカウントの削除

B2B コラボレーション ユーザーが組織を脱退すると、そのユーザーのアカウントはディレクトリ内で "論理的に削除" されます。 既定では、ユーザー オブジェクトは Microsoft Entra ID の **[削除済みのユーザー]** 領域に移動しますが、30 日の間は完全には削除されません。 この論理的削除により、ユーザーが、アカウントが完全に削除される前にアカウントの復元を要求した場合に、管理者はそのアカウント (グループおよびアクセス許可を含む) を復元させることができます。

必要であれば、テナント管理者は以下の手順を使用して論理削除期間中にいつでもアカウントを完全に削除できます。 このアクションは取り消すことはできません。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動する
3. **[削除されたユーザー]** を選択します。
4. [削除されたユーザー] の横のチェック ボックスを選択して、**[完全に削除]** を選択します。

完全削除は管理者が開始することができます。または、論理的な削除期間の終わりに行われます。 完全削除は、データの削除に追加で最大 30 日かかる場合があります。

B2B 直接接続ユーザーの場合、ユーザーが確認メッセージで **[脱退]** を選ぶとすぐにデータの削除が開始され、完了するまでに最大 30 日かかることがあります。

### お困りですか?

コンテンツで取り上げられない追加のサポートが必要な場合、いくつかの選択肢があります。 Microsoft コミュニティで[ヘルプやサポートの受け方がわかります](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)。あるいは、Microsoft に直接、サポート リクエストを送信してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/microsoft-account"} -->
## Microsoft アカウントの使用 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/microsoft-account
- Service: entra-external-id / external
- Article date: 2026-03-27
- Summary: 外部のビジネス パートナーとゲスト ユーザーが Microsoft アカウント (MSA) を使用して、B2B Collaboration を行うためにアプリにサインインできるようにします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

B2B ゲスト ユーザーは、追加の構成を行わなくても、B2B コラボレーションに自分の個人用 Microsoft アカウントを使用できます。 ゲスト ユーザーは、自分の個人用 Microsoft アカウントを使用して、B2B コラボレーションの招待に応じたり、サインアップ ユーザー フローを実行したりすることができます。

コンシューマー向けの Microsoft 製品およびクラウド サービス (Outlook、OneDrive、Xbox LIVE、Microsoft 365 など) にアクセスするために、ユーザー自身が Microsoft アカウントを設定します。 このアカウントは、Microsoft が運営する Microsoft コンシューマー ID アカウント システムを使用して、作成および保存されます。

### Microsoft アカウントを使用したゲストのサインイン

Microsoft アカウントは、既定で、**[External Identities]**&gt;**[すべての ID プロバイダー]** の一覧で使用できます。 追加の構成を行わなくても、ゲスト ユーザーは招待フローまたはセルフサービス サインアップ ユーザー フローのどちらかを使用して、自分の Microsoft アカウントでサインインできます。

#### 招待フローの Microsoft アカウント

B2B コラボレーションに[ゲスト ユーザーを招待](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)する場合、その Microsoft アカウントを、サインインに使用する電子メール アドレスとして指定できます。

[Image: Microsoft アカウントを使用した招待のスクリーンショット。]

#### セルフサービス サインアップ ユーザー フローの Microsoft アカウント

Microsoft アカウントは、セルフサービス サインアップ ユーザー フロー用の ID プロバイダー オプションです。 ユーザーは、自分の Microsoft アカウントを使用してアプリケーションにサインアップできます。 最初に、テナントに対して[セルフサービス サインアップを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow)必要があります。 次に、アプリケーションでのユーザー フローを設定し、サインイン オプションの 1 つとして Microsoft アカウントを選択します。

[Image: セルフサービス サインアップ ユーザー フローの Microsoft アカウントのスクリーンショット。]

### アプリケーションのパブリッシャー ドメインを検証する

2020 年 11 月現在、新しいアプリケーション登録は、[アプリケーションの発行元ドメインが検証済み](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)で、"***かつ***" 会社の ID が Microsoft Partner Network で検証され、アプリケーションに関連付けられている場合を除き、ユーザーの同意プロンプトで未検証として表示されます。 Microsoft Entra 外部 ID ユーザー フローの場合、発行元のドメインは、Microsoft アカウントまたはその他の Microsoft Entra テナントを ID プロバイダーとして使用する場合にのみ表示されます。 これらの新しい要件を満たすには、次の手順に従います。

1. [自分の Microsoft Partner Network (MPN) アカウントを使用して会社 ID を確認します](https://learn.microsoft.com/ja-jp/partner-center/verification-responses)。 このプロセスにより、会社と会社の主要連絡先に関する情報が検証されます。
2. 発行元の確認プロセスを完了し、次のいずれかのオプションを使用して、MPN アカウントをアプリ登録に関連付けます。
    - Microsoft アカウント ID プロバイダーのアプリの登録が Microsoft Entra テナント内にある場合は、[アプリ登録ポータルでアプリを検証します](https://learn.microsoft.com/ja-jp/entra/identity-platform/mark-app-as-publisher-verified)。
    - Microsoft アカウント ID プロバイダーのアプリの登録が Azure AD B2C テナント内にある場合、[Microsoft Graph API を使用して発行元がアプリを検証済みとしてマーク](https://learn.microsoft.com/ja-jp/entra/identity-platform/troubleshoot-publisher-verification#making-microsoft-graph-api-calls)します (たとえば、Graph Explorer を使用)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/one-time-passcode"} -->
## 電子メール ワンタイム パスコード認証 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode
- Service: entra-external-id / external
- Article date: 2026-03-27
- Summary: Microsoft Entra 外部 ID で B2B ゲスト ユーザーに対して電子メール ワンタイム パスコード認証を有効にして使用する方法について学習します。 この機能により、サインインのためのシームレスなフォールバック認証方法が提供されます。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

電子メール ワンタイム パスコード機能は、Microsoft Entra ID、Microsoft アカウント (MSA)、またはソーシャル ID プロバイダーなどの他の方法で B2B コラボレーション ユーザーを認証できない場合に、そのユーザーを認証する方法です。 B2B ゲスト ユーザーは、招待を引き換えたり、共有リソースにサインインしたりしようとするときに、一時パスコードを要求できます。このパスコードは、ユーザーのメール アドレスに送信されます。 その後、このパスコードを入力してサインインを続けます。

[Image: 電子メール ワンタイム パスコードの概要を示す図。]

Important

- すべての新しいテナントと、明示的に無効にしていない既存のテナントに対して、電子メール ワンタイム パスコード機能が既定で有効になりました。 この機能により、ゲスト ユーザーに対するシームレスなフォールバック認証方法が提供されます。 この機能を使用しない場合は無効にできます。その場合、ユーザーは代わりに Microsoft アカウントを作成するように求められます。

Note

現在、条件付きアクセスを介して認証強度ポリシーを電子メールワンタイム パスコード アカウントに適用することはできません。 代わりに、条件付きアクセス許可コントロール [MFA を必須にする] を使います。 詳細については、「[外部 ID の認証と条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access#authentication-strength-policies-for-external-users)」ページの「[外部ユーザーの認証強度ポリシー](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)」セクションを参照してください。

### サインイン エンドポイント

メールのワンタイム パスコードのゲスト ユーザーは、[共通エンドポイント](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#redemption-process-and-sign-in-through-a-common-endpoint) (つまり、テナント コンテキストを含まない一般的なアプリ URL) を使って、マルチテナント アプリまたは Microsoft のファースト パーティ アプリにサインインできるようになりました。 サインイン プロセス中に、ゲスト ユーザーは **[サインイン オプション]** を選択してから、 **[組織にサインイン]** を選択します。 次に、ユーザーは組織の名前を入力し、ワンタイム パスコードを使用してサインインを続行します。

電子メール ワンタイム パスコードのゲスト ユーザーは、テナント情報を含むアプリケーション エンドポイントを使用することもできます。次に例を示します。

- `https://myapps.microsoft.com/?tenantid=<your tenant ID>`
- `https://myapps.microsoft.com/<your verified domain>.onmicrosoft.com`
- `https://portal.azure.com/<your tenant ID>`

また、`https://myapps.microsoft.com/signin/X/<application ID>?tenantId=<your tenant ID>` のようにテナント情報を含めることによって、アプリケーションまたはリソースへの直接リンクを電子メール ワンタイム パスコードのゲスト ユーザーに提供することもできます。

Note

メールのワンタイム パスコードのゲスト ユーザーは、**サインイン オプション**を選択せずに、共通エンドポイントから直接 Microsoft Teams にサインインできます。 Microsoft Teams へのサインイン プロセス中に、ゲスト ユーザーはリンクを選択してワンタイム パスコードを送信できます。

### ワンタイム パスコードのゲスト ユーザーに対するユーザー エクスペリエンス

電子メール ワンタイム パスコード機能が有効になっている場合、一定の条件を満たす新しく招待されたユーザーはワンタイム パスコード認証を使用します。 電子メール ワンタイム パスコードが有効になる前に招待に応じたゲスト ユーザーは、引き続き同じ認証方法を使用します。

ワンタイム パスコード認証では、ゲスト ユーザーは、直接リンクをクリックするか、招待メールを使用して、招待を引き換えることができます。 どちらの場合も、ブラウザーのメッセージで、ゲスト ユーザーのメール アドレスにコードが送信されることが示されます。 ゲスト ユーザーは、 **[コードの送信]** を選択します。

[Image: [コードの送信] ボタンを示すスクリーンショット。]

パスコードがユーザーのメール アドレスに送信されます。 ユーザーは、メールからパスコードを取得し、ブラウザー ウィンドウに入力します。

[Image: [コードの入力] ページを示すスクリーンショット。]

ゲスト ユーザーは認証されて、共有リソースを表示したり、サインインを続行したりできるようになります。

Note

ワンタイム パスコードの有効期間は 30 分です。 30 分が経過すると、その特定のワンタイム パスコードは無効になり、ユーザーは新しいパスコードを要求する必要があります。 ユーザー セッションは 24 時間後に期限が切れます。 それを過ぎると、ゲスト ユーザーはリソースにアクセスするときに新しいパスコードを受け取ります。 セッションの有効期限は、特にゲスト ユーザーが退職した場合やアクセスが不要になった場合に、セキュリティを強化します。

### ゲスト ユーザーがワンタイム パスコードを入手するとき

次の場合、ゲスト ユーザーは、招待に応じたとき、または共有されているリソースへのリンクを使用したときに、ワンタイム パスコードを受け取ります。

- Microsoft アカウントを持っていない。
- Microsoft アカウントを持っていない。
- 招待元のテナントで、ソーシャル プロバイダー ([Google](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation) など) や他の ID プロバイダーとのフェデレーションを設定していなかった。
- 他の認証方法もパスワードで認証されるアカウントも持っていない。
- 電子メール ワンタイム パスコードが有効になっている。

招待するとき、招待されるユーザーにワンタイム パスコード認証が使用されることは示されません。 ただし、ゲスト ユーザーがサインインするとき、他の認証方法を使用できない場合は、ワンタイム パスコード認証がフォールバック メソッドになります。

Note

ワンタイム パスコードを引き換えて、後で MSA、Microsoft Entra アカウント、または別のフェデレーション アカウントを取得した場合でも、認証にはワンタイム パスコードを使用します。 システムは、別のアカウントの種類を取得した後も、ワンタイム パスコードを使用してユーザーを認証し続けます。 ユーザーの認証方法を更新する場合は、ユーザーの[認証状況をリセットする](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)ことができます。

#### Example

ゲスト ユーザー nicole@firstupconsultants.com は、Google フェデレーションが設定されていない Fabrikam に招待されます。 Nicole は Microsoft アカウントを持っていません。 認証用のワンタイム パスコードを受け取ります。

### メールのワンタイム パスコードを有効または無効にする

すべての新しいテナントと、明示的に無効にしていない既存のテナントに対して、電子メール ワンタイム パスコード機能が既定で有効になりました。 この機能により、ゲスト ユーザーに対するシームレスなフォールバック認証方法が提供されます。 この機能を使用しない場合は無効にできます。その場合、ユーザーは Microsoft アカウントを作成するように求められます。

Note

- 電子メール ワンタイム パスコードの設定は、Microsoft Graph API で [emailAuthenticationMethodConfiguration](https://learn.microsoft.com/ja-jp/graph/api/resources/emailauthenticationmethodconfiguration) リソースの種類を使用して構成することもできます。
- テナントで電子メールワンタイム パスコード機能が有効になっていて、無効にすると、ワンタイム パスコードを使用したゲスト ユーザーはサインインできなくなります。 別の認証方法を使用して再度サインインできるように、[認証状態をリセットする](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)ことができます。

#### メールのワンタイム パスコードを有効または無効にするには

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**エクスターナルアイデンティティ**&gt;**すべてのIDプロバイダーを閲覧します**。
3. **[組み込み]** タブの [ワンタイム パスコードをメールで送信する] の横にある **[構成済み]** を選択します。
4. **[ゲストの電子メール ワンタイム パスコード]** で、次のいずれかを選択します。

    - **はい**: 機能が明示的にオフになっていない限り、トグルは既定では **[はい]** に設定されます。 この機能を有効にするには、**[はい]** が選択されていることを確認します。
    - **いいえ**: メールのワンタイム パスコード機能を無効にする場合は、**[いいえ]** を選択します。

    [Image: [電子メール ワンタイム パスコード] トグルを示すスクリーンショット。]
5. **保存** を選択します。

### よく寄せられる質問

**電子メールでワンタイムパスコードを有効にした場合、既存のゲストユーザーはどうなりますか?**

既存のゲストユーザーは、すでに引き換え時期を過ぎているため、メールのワンタイム パスコードを有効にしても影響を受けません。 電子メール ワンタイム パスコードの有効化は、新しいゲスト ユーザーがテナントを利用する際の将来の引き換え行為にのみ影響します。

**電子メール ワンタイム パスコードが無効になっているときのユーザー エクスペリエンスはどのようなものですか?**

電子メール ワンタイム パスコード機能を無効にした場合、ユーザーは Microsoft アカウントの作成を求められます。

電子メール ワンタイム パスコードが無効であると、ユーザーがダイレクト アプリケーション リンクを利用していて、事前にディレクトリに追加されていないときに、ユーザーにサインインエラーが表示される可能性があります。

さまざまな利用開始プロセスの詳細については、[「B2B コラボレーションの招待の利用」](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience)に関するページを参照してください。

**「アカウントがありませんか？」 作成してください！ セルフサービスのサインアップオプションがなくなるのですか?**

No. [外部 ID のコンテキストでのセルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)は、メール検証済みユーザーのセルフサービス サインアップと混同しやすいですが、これらは 2 つの異なる機能です。 非推奨となったアンマネージド (「バイラル」) 機能は、[メール検証済みユーザーのセルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-self-service-signup)であり、このため、ゲストはアンマネージド Microsoft Entra アカウントを作成します。 ただし、外部 ID のセルフサービス サインアップは引き続き利用可能であり、ゲストは[さまざまな ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers)を使用して組織にサインアップすることになります。

**Microsoft では、既存の Microsoft アカウント (MSA) をどのようにすることを勧めていますか?**

ID プロバイダーの設定で Microsoft アカウントを無効にできるようになったら (現時点では利用できません)、Microsoft アカウントを無効にして電子メール ワンタイム パスコードを有効にすることを強くお勧めします。 次に、Microsoft アカウントを使用している既存のゲストの[利用状態をリセット](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)します。これにより、ゲストはメールのワンタイム パスコード認証の使用に再度切り替え、今後はメールのワンタイム パスコードを使用してサインインできるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/redemption-experience"} -->
## Microsoft Entra B2B コラボレーション招待の引き換え - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience
- Service: entra-external-id / external
- Article date: 2026-04-21
- Summary: ゲスト サインイン、同意プロセス、プライバシー条件など、Microsoft Entra B2B の招待の利用のしくみについて説明します。 組織のリソースへの安全なアクセスを確保します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、ゲスト ユーザーがリソースにアクセスし、必要な同意手順を完了する方法など、ゲスト ユーザー向けの Microsoft Entra B2B 招待の利用プロセスについて説明します。 招待メールを送信する場合も、直接リンクを指定する場合でも、ゲストは安全なサインインと同意プロセスを通じて案内され、組織のプライバシー条件と [使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)に確実に準拠します。

ゲスト ユーザーをディレクトリに追加すると、ゲスト ユーザー アカウントの同意状態 (PowerShell で表示可能) が最初に **PendingAcceptance** に設定されます。 ゲストが招待を受け入れ、プライバシー ポリシーと利用規約に同意するまで、この設定は維持されます。 その後、同意の状態が**承認済み**に変わり、同意ページはゲストに表示されなくなります。

注

Onmicrosoft の既定のドメインから送信される B2B 招待メールには、Exchange Online の送信制限が適用されます。 詳細については、「 [電子メールを送信するための Onmicrosoft ドメインの使用の制限](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/exchange-online-service-description/exchange-online-limits#sending-limits) 」を参照してください。 より高い制限が必要な場合は、カスタム ドメインへの更新を検討してください。 詳細については、「 [カスタム ドメイン名をテナントに追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)。

### 共通のエンドポイントを使用した引き換えプロセスとサインイン

ゲスト ユーザーは、`https://myapps.microsoft.com` のような共通のエンドポイント (URL) を介して、マルチテナント アプリまたは Microsoft のファーストパーティ アプリにサインインできるようになりました。 これまで、ゲスト ユーザーは認証のために共通 URL によって、リソース テナントではなくホーム テナントにリダイレクトされていたため、テナント固有のリンク (例: `https://myapps.microsoft.com/?tenantid=<tenant id>`) が必要でした。 現在、ゲスト ユーザーはアプリケーションの共通 URL にアクセスして、 **[サインイン オプション]** を選択し、 **[Sign in to an organization](https://learn.microsoft.com/ja-jp/entra/external-id/組織にサインイン)** を選択できます。 次に、ユーザーは組織のドメイン名を入力します。

[Image: Microsoft Entra B2B 招待の引き換えフロー図のスクリーンショット。]

その後、ユーザーはテナント固有のエンドポイントにリダイレクトされます。ここで、ユーザーは自分のメール アドレスでサインインするか、構成した ID プロバイダーを選択できます。

### 直接リンクによる引き換えプロセス

招待メールまたはアプリケーションの共通 URL の代わりに、ゲストにアプリまたはポータルへの直接リンクを付与します。 まず、 [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-quickstart-add-guest-users-portal) または [PowerShell](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-quickstart-invite-powershell) を使用して、ゲスト ユーザーをディレクトリに追加します。 次に、 [カスタマイズ可能な方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences)のいずれかを使用して、直接サインオン リンクを含むアプリケーションをユーザーに展開します。 ゲストが招待メールの代わりに直接リンクを使用する場合、リンクは引き続き初回の同意エクスペリエンスを案内します。

注

直接リンクはテナント固有です。 つまり、共有アプリが配置されている、テナントでゲストを認証できるように、テナント ID または確認済みドメインが含まれています。 テナント コンテキストを含む直接リンクの例をいくつか以下に示します。

- アプリ アクセス パネル: `https://myapps.microsoft.com/?tenantid=<tenant id>`
- 確認済みドメインのアプリ アクセス パネル: `https://myapps.microsoft.com/<;verified domain>`
- Microsoft Entra 管理センター: `https://entra.microsoft.com/<tenant id>`
- 個々のアプリ: [直接サインオン リンク](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences#direct-sign-on-links)の使用方法を参照してください

直接リンクと招待メールの使用に関して注意すべき点を以下に示します。

- **電子メール エイリアス:** 招待されたメール アドレスのエイリアスを使用するゲストには、招待メールが必要です。 (エイリアスとは、メール アカウントに関連付けられた別のメール アドレスのことです。)このユーザーは、招待メール内の引き換え URL を選択する必要があります。
- **競合する連絡先オブジェクト:** 引き換えプロセスでは、ゲスト ユーザー オブジェクトがディレクトリ内の連絡先オブジェクトと競合する場合のサインインの問題を防ぐことができます。 既存の連絡先と一致するメールを持つゲストを追加または招待すると、ゲスト ユーザー オブジェクトの proxyAddresses プロパティは空になります。 以前は、外部 ID は proxyAddresses プロパティのみを検索していたため、一致するものが見つからない場合、直接リンクの引き換えは失敗しました。 現在、外部 ID は proxyAddresses と invited email プロパティの両方を検索するようになっています。

### 招待メールによる引き換えプロセス

[Microsoft Entra 管理センターを使用](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-quickstart-add-guest-users-portal)してディレクトリにゲスト ユーザーを追加すると、招待メールがゲストに送信されます。 [PowerShell を使用して](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-quickstart-invite-powershell)ゲスト ユーザーをディレクトリに追加するときに、招待メールを送信することもできます。 メールのリンクを引き換えたときのゲストのエクスペリエンスの説明を次に示します。

1. ゲストは、の代理として Microsoft Invitations からの`<primary domain> <invites@<primary domain>.onmicrosoft.com>`を受け取ります。
2. ゲストは、電子メールで **[招待の承諾]** を選択します。
3. ゲストは、独自の資格情報を使用してディレクトリにサインインします。 ゲストがディレクトリにフェデレーションできるアカウントを持っていなくても、 [電子メール ワンタイム パスコード (OTP)](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode) 機能が有効になっていない場合、ゲストは個人用 [Microsoft アカウント (MSA](https://support.microsoft.com/help/4026324/microsoft-account-how-to-create)) の作成を求められます。 詳細については、「招待の引き換えフロー」を参照してください。
4. ゲストは、「ゲストの同意エクスペリエンス」セクションで説明されている 同意エクスペリエンス に従って案内されます。

### 招待の引き換えプロセス

ユーザーが**招待メール**で [\[招待を受け入れる](https://learn.microsoft.com/ja-jp/entra/external-id/invitation-email-elements)] リンクを選択すると、Microsoft Entra ID は既定の引き換え順序に基づいて招待を自動的に引き換えます。

[Image: 引き換えフローを図解したスクリーンショット。]

1. Microsoft Entra ID は、ユーザーベースの検出を実行して、ユーザーがマネージド Microsoft Entra テナントに既に存在するかどうかを判断します。 (アンマネージド Microsoft Entra アカウントは、引き換えフローには使用できません)。ユーザーのユーザー プリンシパル名 ([UPN](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-userprincipalname#what-is-userprincipalname)) が既存の Microsoft Entra アカウントと個人用 MSA の両方と一致する場合、ユーザーは、使用するアカウントを選択するように求められます。
2. 管理者が [SAML/WS-Fed IdP フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)を有効にした場合、Microsoft Entra ID は、ユーザーのドメイン サフィックスが構成済みの SAML/WS-Fed ID プロバイダーのドメインと一致するかどうかを確認し、構成済みの ID プロバイダーにユーザーをリダイレクトします。

    注

    2 つの Microsoft Entra テナント間の直接 SAML/WS-Fed フェデレーションは、サポートまたは推奨される構成ではありません。 2 つの Entra テナント間で SAML 信頼が技術的に構成されている場合でも、Microsoft Entra は SAML ではなくネイティブの Entra-to-Entra [B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-overview) モデルを引き続き使用します。
3. 管理者が [Google フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation)を有効にした場合、Microsoft Entra ID は、ユーザーのドメイン サフィックスが gmail.com または googlemail.com されているかどうかを確認し、ユーザーを Google にリダイレクトします。
4. 引き換えプロセスでは、ユーザーに個人用の [MSA](https://learn.microsoft.com/ja-jp/entra/external-id/microsoft-account) が既に与えられているかどうかが確認されます。 ユーザーが既に既存の MSA を持っている場合は、既存の MSA でサインインします。
5. ユーザーの**ホーム ディレクトリ**が確認されると、ユーザーはサインインするため、それに対応する ID プロバイダーの元に送られます。
6. ホーム ディレクトリが見つからず、ゲストの電子メール ワンタイム パスコード機能が "有効になっている" 場合、招待メール経由でユーザーに*パスコードが送信されます*。 ユーザーはこのパスコードを取得し、Microsoft Entra サインイン ページで入力します。
7. ホーム ディレクトリが見つからず、ゲストの電子メール ワンタイム パスコードが "無効になっている" 場合、ユーザーは招待メールでコンシューマー MSA を作成するように求められます。 Microsoft Entra ID では、Microsoft Entra ID で検証されていないドメイン内の仕事用メールを含む MSA の作成がサポートされています。
8. 正しい ID プロバイダーに対して認証を行った後、同意エクスペリエンスを完了するため、ユーザーは Azure AD にリダイレクトされます。

### 構成可能な引き換え

[構成可能な引き換え](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)では、招待状を引き換えるときにゲストに提示される ID プロバイダーの順序をカスタマイズできます。 ゲストが **[招待を承諾]** リンクを選ぶと、Microsoft Entra ID は、既定の順序に基づいて招待を自動的に引き換えます。 [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)で ID プロバイダーの引き換え順序を変更して、この順序をオーバーライドします。

### ゲストの同意エクスペリエンス

ゲストがパートナー組織のリソースに初めてサインインすると、次の同意エクスペリエンスが表示されます。 ゲストはサインイン後にのみこれらの同意ページを表示し、ユーザーが既に同意した場合は表示されません。

1. ゲストは、招待元の組織の**プライバシーに**関する声明を説明する [\[アクセス許可の確認](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area)] ページを確認します。 続行するには、ユーザーは招待元の組織のプライバシー ポリシーに従って情報の使用を **承諾** する必要があります。

    この同意プロンプトに同意すると、アカウントの特定の要素が共有されていることを確認できます。 これらの要素には、名前、写真、電子メール アドレス、および他の組織がアカウントの管理を改善し、組織間のエクスペリエンスを向上させるために使用するディレクトリ識別子が含まれます。

    [Image: [アクセス許可の確認] ページのスクリーンショット。]

    注

    テナント管理者が組織のプライバシーに関する声明にリンクする方法については、「[方法: Microsoft Entra ID で組織のプライバシー情報を追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area)」を参照してください。
2. 使用条件が構成されている場合、ゲストは利用規約を開いて確認し、[ **同意**する] を選択します。

    [Image: 新しい利用規約を示すスクリーンショット。]

    [\[外部 ID\]](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)&gt; で**使用条件**を構成できます。
3. 特に指定されていない限り、ゲストはアプリ アクセス パネルにリダイレクトされます。そこには、ゲストがアクセスできるアプリケーションがリスト表示されています。

    [Image: アプリ アクセス パネルを示すスクリーンショット。]

ディレクトリでは、ゲストの **[招待が受け入れられました]** の値が **[はい]** に変わります。 MSA が作成された場合、ゲストの **[ソース]** には **Microsoft アカウント**が示されます。 ゲスト ユーザー アカウントのプロパティの詳細については、[Microsoft Entra B2B コラボレーション ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)を参照してください。 アプリケーションへのアクセス中に管理者の同意を要求するエラーが表示される場合は、[アプリに管理者の同意を付与する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-admin-consent)に関するページを参照してください。

#### 自動引き換えプロセスの設定

B2B コラボレーションのために別のテナントに招待を追加するときに、ユーザーが同意プロンプトを受け入れる必要がないように、招待を自動的に引き換える必要がある場合があります。 この設定を構成すると、B2B コラボレーション ユーザーは、アクションを必要としない通知メールを受信します。 ユーザーは通知メールを直接受信するため、メールを受信する前に最初にテナントにアクセスする必要はありません。

自動的に招待を利用する方法の詳細については、[テナント間アクセスの概要](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#automatic-redemption-setting)に関する記事と「[B2B コラボレーションのためにテナント間アクセス設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)」を参照してください。

### 追加情報

- **2021 年 7 月 12 日以降**、Microsoft Entra B2B のお客様がカスタムアプリケーションまたは基幹業務アプリケーションのセルフサービス サインアップで使用する新しい Google 統合を設定した場合、認証がシステム Web ビューに移動されるまで、Google ID による認証は機能しません。 詳細については、 [Google Web ビューのサインインの廃止に](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation#deprecation-of-web-view-sign-in-support)関するページを参照してください。
- **2021 年の 9 月 30 日より**、Google は[埋め込みの Web ビューのサインイン サポートを廃止](https://developers.googleblog.com/2016/08/modernizing-oauth-interactions-in-native-apps.html)します。 アプリで埋め込み Web ビューを使用してユーザーを認証していて、Google フェデレーションを [Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/identity-provider-google)、Microsoft Entra B2B [(外部ユーザーの招待用)](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation)、または[セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers)で使用している場合、Google Gmail ユーザーが認証されなくなります。 詳細については、 [Google Web ビューのサインインの廃止に](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation#deprecation-of-web-view-sign-in-support)関するページを参照してください。
- すべての新しいテナントと、明示的に無効にしていない既存のテナントに対して、 [電子メール ワンタイム パスコード機能](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode) が既定で有効になりました。 この機能をオフにすると、フォールバック認証方法は、Microsoft アカウントの作成を招待者に求める方法です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/reference-cross-tenant-custom-roles"} -->
## テナント間アクセス設定を管理するためのカスタム ロール - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/reference-cross-tenant-custom-roles
- Service: entra-external-id / external
- Article date: 2025-07-07
- Summary: 組織がカスタム ロールを定義してテナント間のアクセス設定を管理し、組み込みの管理ロールに依存せずに正確な制御を可能にする方法について説明します。

組織では、テナント間アクセス設定を管理するための[カスタム ロールを定義](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)できます。 これらのロールを使用すると、組み込みの管理ロールに依存することなく、正確に制御できるようになります。 この記事では、テナント間のアクセス設定を管理するための推奨カスタム ロールを作成する方法について説明します。

### テナント間アクセス管理者

このロールでは、既定の設定や組織ベースの設定など、テナント間アクセス設定のすべての設定を管理できます。 このロールは、テナント間アクセス設定のすべての設定を管理する必要があるユーザーに割り当てる必要があります。

このロールには、次のアクションをお勧めします。

| アクション |
| --- |
| microsoft.directory/tenantRelationships/standard/read |
| microsoft.directory/crossTenantAccessPolicy/standard/read |
| microsoft.directory/crossTenantAccessPolicy/allowedCloudEndpoints/update |
| microsoft.directory/crossTenantAccessPolicy/basic/update |
| microsoft.directory/crossTenantAccessPolicy/default/b2bCollaboration/update |
| microsoft.directory/crossTenantAccessPolicy/default/b2bDirectConnect/update |
| microsoft.directory/crossTenantAccessPolicy/default/crossCloudMeetings/update |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read |
| microsoft.directory/crossTenantAccessPolicy/default/tenantRestrictions/update |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bCollaboration/update |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bDirectConnect/update |
| microsoft.directory/crossTenantAccessPolicy/partners/create |
| microsoft.directory/crossTenantAccessPolicy/partners/crossCloudMeetings/update |
| microsoft.directory/crossTenantAccessPolicy/partners/delete |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/basic/update |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/create |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/standard/read |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read |
| microsoft.directory/crossTenantAccessPolicy/partners/tenantRestrictions/update |

### テナント間アクセス閲覧者

このロールでは、既定の設定や組織ベースの設定など、テナント間アクセス設定のすべての設定を読み取ることができます。 このロールは、テナント間アクセス設定で設定を確認する必要はあるが、管理する必要はないユーザーに割り当てる必要があります。

このロールには、次のアクションをお勧めします。

| アクション |
| --- |
| microsoft.directory/tenantRelationships/standard/read |
| microsoft.directory/crossTenantAccessPolicy/standard/read |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/standard/read |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read |

### テナント間アクセス パートナー管理者

このロールでは、パートナーに関連するすべての設定を管理し、既定の設定を読み取ることができます。 このロールは、組織ベースの設定を管理する必要はあるが、既定の設定を変更できないようにする必要があるユーザーに割り当てる必要があります。

このロールには、次のアクションをお勧めします。

| アクション |
| --- |
| microsoft.directory/tenantRelationships/standard/read |
| microsoft.directory/crossTenantAccessPolicy/standard/read |
| microsoft.directory/crossTenantAccessPolicy/basic/update |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bCollaboration/update |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bDirectConnect/update |
| microsoft.directory/crossTenantAccessPolicy/partners/create |
| microsoft.directory/crossTenantAccessPolicy/partners/crossCloudMeetings/update |
| microsoft.directory/crossTenantAccessPolicy/partners/delete |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/basic/update |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/create |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/standard/read |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read |
| microsoft.directory/crossTenantAccessPolicy/partners/tenantRestrictions/update |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/reset-redemption-status"} -->
## ゲストの引き換え状態をリセットする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: Microsoft Entra External IDでゲスト ユーザーの引き換え状態をリセットする方法について説明します。 このガイドでは、管理センター、PowerShell、および Microsoft Graph APIの使用について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、B2B コラボレーションへの招待を引き換えた後に [、ゲスト ユーザーの](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties) サインイン情報を更新する方法について説明します。 次のような場合に、サインイン情報の更新が必要になることがあります。

- ユーザーが別のメールおよび ID プロバイダーを使用してサインインすることを望んでいる
- ホーム テナント内のユーザーのアカウントが削除され、再作成された
- ユーザーは別の会社に転職したが、リソースへのアクセス権は引き続き同じものが必要である
- ユーザーの責任が別のユーザーに移った

以前は、これらのシナリオを管理するために、ディレクトリからゲスト ユーザーのアカウントを手動で削除し、ユーザーを再招待する必要がありました。 これで、Microsoft Entra admin center、PowerShell、またはMicrosoft Graph招待 API を使用して、ユーザーの引き換え状態をリセットし、ユーザーのオブジェクト ID、グループ メンバーシップ、アプリの割り当てを維持しながらユーザーを再招待できるようになりました。 ユーザーが新しい招待を利用しても、ユーザー プリンシパル名 (UPN) は変更されませんが、ユーザーのサインイン名は新しいメールに変更されます。 その後、ユーザーオブジェクトの `otherMails` プロパティに追加した新しい電子メールまたは電子メールを使用してサインインできます。

### 必要な Microsoft Entra のロール

ユーザーの引き換え状態をリセットするには、ディレクトリ スコープで次のいずれかのロールが割り当てられている必要があります。

- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) (最小特権)
- [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)

### Microsoft Entra admin centerを使用して引き換えの状態をリセットする

1. 少なくとも [Microsoft Entra admin center](https://entra.microsoft.com)[User Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 一覧でユーザーの名前を選択して、ユーザー プロファイルを開きます。
4. (省略可能) ユーザーが別のメールを使用してサインインすることを望んでいる場合:

    1. **[プロパティの編集]** アイコンを選択します。
    2. [ **すべて** ] タブまたは [ **連絡先情報** ] タブで、[ **メール** ] までスクロールし、新しいメールを入力します。
    3. [ **その他のメール] の**横にある [ **他のメールの追加または編集**] を選択します。 [ **追加] を**選択し、新しいメールを入力して、[ **保存]** を選択します。
    4. ページの下部にある **[保存]** ボタンを選択して、すべての変更を保存します。
5. [**概要**] タブの [**マイ フィード**] で、[**B2B コラボレーション**] タイルの [**引き換え状態のリセット**] リンクを選択します。

    [Image: Microsoft Entra admin centerのゲスト ユーザー プロファイルのスクリーンショット。[概要] タブの [B2B コラボレーション] タイルで [引き換え状態のリセット] リンクが強調表示されています]
6. **[引き換え状態のリセット]** で、**[リセット]** を選択します。

    [Image: ゲスト ユーザー オブジェクトを保持したまま招待を再送信するリセット アクションを示す [引き換え状態のリセット] ウィンドウのスクリーンショット。]

### PowerShell または Microsoft Graph API を使用して引き換えの状態をリセットする

#### サインインに使用するメール アドレスをリセットする

ユーザーが別のメールを使用してサインインすることを望んでいる場合:

1. 新しいメール アドレスが、user オブジェクトの `mail` プロパティまたは `otherMails` プロパティに追加されていることを確認します。
2. `InvitedUserEmailAddress` プロパティのメール アドレスを新しいメール アドレスに置き換えます。
3. 次のいずれかの方法を使用して、ユーザーの引き換え状態をリセットします。

Note

- ユーザーのメール アドレスを新しいアドレスにリセットする場合は、`mail` プロパティを設定することをお勧めします。 このようにすると、ユーザーは招待の引き換えリンクを使用するだけでなく、ディレクトリにサインインして招待を引き換えることができます。
- アプリのみ呼び出しの場合、ターゲット ユーザー アカウントにロールが割り当てられていると、引き換えの状態はリセットすることができません。

#### PowerShell を使用して引き換え状態をリセットする

```powershell
Install-Module Microsoft.Graph -Scope CurrentUser
Connect-MgGraph -Scopes "User.Invite.All","User.ReadWrite.All"

$user = Get-MgUser -Filter "startsWith(mail, 'john.doe@fabrikam.net')"
New-MgInvitation `
    -InvitedUserEmailAddress $user.Mail `
    -InviteRedirectUrl "https://myapps.microsoft.com" `
    -ResetRedemption `
    -SendInvitationMessage `
    -InvitedUser $user
```

#### Microsoft Graph API を使用して引き換えの状態をリセットする

[Microsoft Graph招待 API](https://learn.microsoft.com/ja-jp/graph/api/resources/invitation) を使用するには、`resetRedemption` プロパティを `true` に設定し、`invitedUserEmailAddress` プロパティに新しい電子メール アドレスを指定します。

```json
POST https://graph.microsoft.com/v1.0/invitations  
Authorization: Bearer eyJ0eX...  
Content-Type: application/json  
{  
   "invitedUserEmailAddress": "<<external email>>",  
   "sendInvitationMessage": true,  
   "invitedUserMessageInfo": {  
      "messageLanguage": "en-US",  
      "ccRecipients": [  
         {  
            "emailAddress": {  
               "name": null,  
               "address": "<<optional additional notification email>>"  
            }  
         } 
      ],  
      "customizedMessageBody": "<<custom message>>"  
},  
"inviteRedirectUrl": "https://myapps.microsoft.com?tenantId=<tenant-id>",  
"invitedUser": {  
   "id": "<<ID for the user you want to reset>>"  
}, 
"resetRedemption": true 
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/self-service-portal"} -->
## B2B コラボレーションのためのセルフサービス サインアップ ポータル - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/self-service-portal
- Service: entra-external-id / external
- Article date: 2025-04-15
- Summary: 組織のニーズに合わせて、Microsoft Entra B2B ユーザー向けのオンボード ワークフローをカスタマイズする方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

お客様は、[Azure portal](https://portal.azure.com) とエンド ユーザー向けの[アプリケーション アクセス パネル](https://myapps.microsoft.com)を通して公開される組み込み機能を使用して、さまざまな操作を実行できます。 ただし、ユーザーの組織のニーズに合わせて、B2B ユーザー向けのオンボード ワークフローをカスタマイズすることが必要な場合があります。

### B2B ゲスト ユーザーのサインアップのための Microsoft Entra のエンタイトルメント管理

招待する側の組織は、だれが社外のコラボレーターであり、だれ企業のリソースへのアクセスを必要としているのが事前にわからないことがあります。 招待側の組織には、組織で制御するポリシーに従ってパートナー企業のユーザーがサインアップできるようにする方法が必要です。 [Microsoft Entra のエンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用して、[外部ユーザーのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#how-access-works-for-external-users)ポリシーを構成できます。 その後、他の組織のユーザーはアクセスを要求でき、承認時にゲスト アカウントを使用してプロビジョニングし、グループ、アプリ、SharePoint Online サイトに割り当てることができます。

### Microsoft Entra B2B 招待 API

組織は、[Microsoft Graph 招待マネージャー API](https://learn.microsoft.com/ja-jp/graph/api/resources/invitation) を使用して、B2B ゲスト ユーザー向けの独自のオンボード エクスペリエンスを構築できます。 セルフサービス B2B ゲスト ユーザーのサインアップを提供する場合は、[Microsoft Entra のエンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用することをお勧めします。 ただし、独自のエクスペリエンスを構築する場合は、 [招待 API](https://learn.microsoft.com/ja-jp/graph/api/invitation-post?tabs=http) を使用して、たとえば、カスタマイズした招待メールを B2B ユーザーに直接自動的に送信できます。 また、アプリは、作成応答で返された inviteRedeemUrl を使用して、(選択した通信メカニズムを介して) 招待するユーザーに対して独自の招待状を作成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/self-service-sign-up-add-api-connector"} -->
## セルフサービスのサインアップ フローに API コネクタを追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-api-connector
- Service: entra-external-id / external
- Article date: 2025-04-15
- Summary: ユーザー フローで使用するように Web API を構成します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

[API コネクタ](https://learn.microsoft.com/ja-jp/entra/external-id/api-connectors-overview)を使用するには、まず API コネクタを作成してから、ユーザー フローで有効にします。

重要

- **2021 年 7 月 12 日の時点**で、Microsoft Entra B2B のお客様がカスタムまたは基幹業務アプリケーションのセルフサービス サインアップで使用する新しい Google 統合を設定した場合、認証がシステム Web ビューに移動されるまで、Google ID による認証は機能しません。 [詳細については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation#deprecation-of-web-view-sign-in-support)。
- **2021 年 9 月 30 日**、Google は [埋め込み Web ビュー サインインのサポートを非推奨にしました](https://developers.googleblog.com/2016/08/modernizing-oauth-interactions-in-native-apps.html)。 アプリで Web ビューが埋め込まれたユーザーを認証し、[外部ユーザーの招待](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/identity-provider-google)または[セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation)に [Azure AD B2C](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers) または Microsoft Entra B2B との Google フェデレーションを使用している場合、Google Gmail ユーザーは認証できません。 [詳細については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation#deprecation-of-web-view-sign-in-support)。

### API コネクタを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[外部 ID]**&gt;**[概要]** に移動します。
3. **[すべての API コネクタ**] を選択し、[**新しい API コネクタ**] を選択します。

    [Image: 外部 ID に新しい API コネクタを追加するスクリーンショット。]
4. 呼び出しの表示名を指定します。 たとえば、 **承認の状態を確認します**。
5. API 呼び出しの **エンドポイント URL を** 指定します。
6. 認証の **種類** を選択し、API を呼び出すための認証情報を構成します。 [API コネクタをセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-secure-api-connector)する方法について説明します。

    [Image: API コネクタの構成のスクリーンショット。]
7. **[保存] を選択します**。

### API に送信される要求

API コネクタは **HTTP POST** 要求として具体化され、JSON 本文でキーと値のペアとしてユーザー属性 ('claims') を送信します。 属性は、 [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) のユーザー プロパティと同様にシリアル化されます。

**要求の例**

```http
POST <API-endpoint>
Content-type: application/json

{
 "email": "johnsmith@fabrikam.onmicrosoft.com",
 "identities": [ // Sent for Google, Facebook, and Email One Time Passcode identity providers 
     {
     "signInType":"federated",
     "issuer":"facebook.com",
     "issuerAssignedId":"0123456789"
     }
 ],
 "displayName": "John Smith",
 "givenName":"John",
 "surname":"Smith",
 "jobTitle":"Supplier",
 "streetAddress":"1000 Microsoft Way",
 "city":"Seattle",
 "postalCode": "12345",
 "state":"Washington",
 "country":"United States",
 "extension_<extensions-app-id>_CustomAttribute1": "custom attribute value",
 "extension_<extensions-app-id>_CustomAttribute2": "custom attribute value",
 "ui_locales":"en-US"
}
```

**[Entra ID]**&gt;**[外部ID]**&gt;**[概要]**&gt;**[カスタムのユーザー属性]** のエクスペリエンスで一覧表示されるユーザー プロパティとカスタム属性だけが、要求で送信できます。

カスタム属性は、ディレクトリ内の **extension\_&lt;extensions-app-id&gt;\_AttributeName** 形式に存在します。 API では、これと同じシリアル化された形式で要求を受け取ることを想定しています。 カスタム属性の詳細については、 [セルフサービス サインアップ フローのカスタム属性の定義](https://learn.microsoft.com/ja-jp/entra/external-id/user-flow-add-custom-attributes)に関するページを参照してください。

さらに、通常、要求はすべての要求で送信されます。

- **UI ロケール ('ui\_locales')** - デバイスで構成されているエンドユーザーのロケール。API は、国際化された応答を返すために使用できます。

- **電子メール アドレス ('email')** または [**ID ('id')**](https://learn.microsoft.com/ja-jp/graph/api/resources/objectidentity) - これらの要求は、アプリケーションに対して認証されているエンド ユーザーを識別するために API によって使用できます。

重要

API エンドポイントが呼び出された時点でクレームに値がない場合、その要求は API に送信されません。 要求が要求に含まれていないケースを明示的にチェックして処理するように API を設計する必要があります。

### ユーザー フローで API コネクタを有効にする

セルフサービス サインアップ ユーザー フローに API コネクタを追加するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[外部 ID]**&gt;**[概要]** に移動します。
3. [ **ユーザー フロー**] を選択し、API コネクタを追加するユーザー フローを選択します。
4. **API コネクタを**選択し、ユーザー フローの次の手順で呼び出す API エンドポイントを選択します。

    - **サインアップ中に ID プロバイダーとフェデレーションした後**
    - **ユーザーを作成する前に**

    [Image: ユーザー フローのステップに使用する API コネクタを選択します (例:]
5. **[保存] を選択します**。

### サインアップ時に ID プロバイダーとのフェデレーションを行った後

サインアップ プロセスのこのステップでの API コネクタは、ID プロバイダー (Google、Facebook、Microsoft Entra ID など) でユーザーが認証された直後に呼び出されます。 この手順は、 ***ユーザー属性を収集***するためにユーザーに表示されるフォームである属性コレクション ページの前にあります。

#### この手順で API に送信される要求の例

```http
POST <API-endpoint>
Content-type: application/json

{
 "email": "johnsmith@fabrikam.onmicrosoft.com",
 "identities": [ // Sent for Google, Facebook, and Email One Time Passcode identity providers 
     {
     "signInType":"federated",
     "issuer":"facebook.com",
     "issuerAssignedId":"0123456789"
     }
 ],
 "displayName": "John Smith",
 "givenName":"John",
 "lastName":"Smith",
 "ui_locales":"en-US"
}
```

API に送信される正確な要求は、ID プロバイダーによって提供される情報によって異なります。 "email" は常に送信されます。

#### この手順での Web API からの予期される応答の種類

ユーザー フロー中に Web API で Microsoft Entra ID から HTTP 要求を受信すると、次の応答が返されることがあります。

- 継続応答
- ブロック応答

##### 継続応答

継続応答は、ユーザー フローが次の手順 (属性収集ページ) に進む必要があることを示します。

継続応答では、API は次の要求を返すことができます。

- 属性収集ページの入力フィールドに事前入力します。

継続応答の例を参照してください。

##### ブロック応答

ブロック応答によって、ユーザー フローは終了します。 API は意図的にブロック応答を発行し、ブロック ページをユーザーに表示することで、ユーザー フローの継続を停止できます。 ブロック ページには、API によって提供された `userMessage` が表示されます。

ブロック応答の例を参照してください。

### ユーザーを作成する前

サインアップ プロセスのこのステップでの API コネクタは、属性コレクション ページが含まれている場合、その後に呼び出されます。 このステップは、Microsoft Entra ID でユーザー アカウントが作成される前に必ず呼び出されます。

#### この手順で API に送信される要求の例

```http
POST <API-endpoint>
Content-type: application/json

{
 "email": "johnsmith@fabrikam.onmicrosoft.com",
 "identities": [ // Sent for Google, Facebook, and Email One Time Passcode identity providers 
     {
     "signInType":"federated",
     "issuer":"facebook.com",
     "issuerAssignedId":"0123456789"
     }
 ],
 "displayName": "John Smith",
 "givenName":"John",
 "surname":"Smith",
 "jobTitle":"Supplier",
 "streetAddress":"1000 Microsoft Way",
 "city":"Seattle",
 "postalCode": "12345",
 "state":"Washington",
 "country":"United States",
 "extension_<extensions-app-id>_CustomAttribute1": "custom attribute value",
 "extension_<extensions-app-id>_CustomAttribute2": "custom attribute value",
 "ui_locales":"en-US"
}
```

API に送信される正確な要求は、ユーザーから収集される情報または ID プロバイダーによって提供される情報によって異なります。

#### この手順での Web API からの予期される応答の種類

ユーザー フロー中に Web API で Microsoft Entra ID から HTTP 要求を受信すると、次の応答が返されることがあります。

- 継続応答
- ブロック応答
- 検証応答

##### 継続応答

継続応答は、ユーザー フローが次の手順 (ディレクトリでのユーザーの作成) に進む必要があることを示します。

継続応答では、API は次の要求を返すことができます。

- 属性コレクション ページから要求に既に割り当てられている値をオーバーライドします。

継続応答の例を参照してください。

##### ブロック応答

ブロック応答によって、ユーザー フローは終了します。 API は意図的にブロック応答を発行し、ブロック ページをユーザーに表示することで、ユーザー フローの継続を停止できます。 ブロック ページには、API によって提供された `userMessage` が表示されます。

ブロック応答の例を参照してください。

#### 検証エラー応答

API が検証エラー応答で応答すると、ユーザー フローは属性収集ページにとどまり、`userMessage` がユーザーに表示されます。 これで、ユーザーはフォームを編集して再送信することができます。 この種類の応答は、入力の検証に使用できます。

検証エラー応答の例を参照してください。

### 応答の例

#### 継続応答の例

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "version": "1.0.0",
    "action": "Continue",
    "postalCode": "12349", // return claim
    "extension_<extensions-app-id>_CustomAttribute": "value" // return claim
}
```

| パラメーター | タイプ | 必須 | 説明 |
| --- | --- | --- | --- |
| バージョン | 糸 | あり | API のバージョン。 |
| アクション | 糸 | あり | 値は `Continue` とする必要があります。 |
| &lt;組み込みユーザー属性&gt; | &lt;属性タイプ&gt; | いいえ | 値は、API コネクタ構成で **受信する要求** として選択され、ユーザー フローの **ユーザー属性** として選択されている場合は、ディレクトリに格納できます。 値は、 **アプリケーション要求**として選択されている場合、トークンで返すことができます。 |
| &lt;extension\_{extensions-app-id}\_CustomAttribute&gt; | &lt;属性タイプ&gt; | いいえ | `_<extensions-app-id>_` は "省略可能" であり、要求に含める必要はありません。 戻り値を使用すると、ユーザーから収集された値を上書きすることができます。 |

#### ブロック応答の例

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "version": "1.0.0",
    "action": "ShowBlockPage",
    "userMessage": "There was an error with your request. Please try again or contact support.",
}

```

| パラメーター | タイプ | 必須 | 説明 |
| --- | --- | --- | --- |
| バージョン | 糸 | あり | API のバージョン。 |
| アクション | 糸 | あり | 値は `ShowBlockPage` とする必要があります |
| ユーザーメッセージ | 糸 | あり | ユーザーに表示するメッセージ。 |

**ブロック応答を使用したエンド ユーザー エクスペリエンス**

[Image: API がブロック応答を返した後のエンド ユーザー エクスペリエンスの例。]

#### 検証エラー応答の例

```http
HTTP/1.1 400 Bad Request
Content-type: application/json

{
    "version": "1.0.0",
    "status": 400,
    "action": "ValidationError",
    "userMessage": "Please enter a valid Postal Code.",
}
```

| パラメーター | タイプ | 必須 | 説明 |
| --- | --- | --- | --- |
| バージョン | 糸 | あり | API のバージョン。 |
| アクション | 糸 | あり | 値は `ValidationError` とする必要があります。 |
| 状態 | Integer/String | あり | ValidationError 応答の値は、`400` または `"400"` である必要があります。 |
| ユーザーメッセージ | 糸 | あり | ユーザーに表示するメッセージ。 |

Note

HTTP 状態コードは、応答の本文で、"status" 値であることに加え、"400" である必要があります。

**検証エラー応答を使用したエンド ユーザー エクスペリエンス**

[Image: API が検証エラー応答を返した後のエンド ユーザー エクスペリエンスの例。]

### ベスト プラクティスとトラブルシューティングの方法

#### サーバーレス クラウド機能の使用

[Azure Functions の HTTP トリガー](https://learn.microsoft.com/ja-jp/azure/azure-functions/functions-bindings-http-webhook-trigger)などのサーバーレス関数は、API コネクタで使用する API エンドポイントを作成する方法を提供します。 たとえば [、サーバー](https://learn.microsoft.com/ja-jp/entra/external-id/code-samples-self-service-sign-up#api-connector-azure-function-quickstarts)レス クラウド関数を使用して検証ロジックを実行し、サインアップを特定の電子メール ドメインに制限することができます。 複雑なシナリオには、サーバーレス クラウド機能を使用して、他の Web API、データ ストア、および他のクラウド サービスを呼び出して起動することもできます。

#### ベスト プラクティス

次のことを確認します。

- API は、前に説明したように API 要求と応答のコントラクトに従います。
- API コネクタの **エンドポイント URL** は、正しい API エンドポイントを指します。
- API によって、それが依存する受信済み要求の null 値が明示的に確認されます。
- API には、セキュリティで [保護された API コネクタ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-secure-api-connector)に記載されている認証方法が実装されています。
- API が可能な限り迅速に応答することで、スムーズなユーザー エクスペリエンスが保証されます。
    - Microsoft Entra ID 側は、応答を受信するために最大 *20 秒間*待機します。 何も受信しない場合は、API の呼び出しが *もう一度実行 (再試行)* されます。
    - サーバーレス機能またはスケーラブルな Web サービスを使用している場合は、API を運用環境で "起動状態" または "ウォーム状態" に保つホスティング プランを使用します。 Azure Functions の場合は、少なくとも [Premium プラン](https://learn.microsoft.com/ja-jp/azure/azure-functions/functions-scale#overview-of-plans)を使用することをお勧めします。
- API の高可用性を保証します。
- ダウンストリームの API、データベース、または API のその他の依存関係のパフォーマンスを監視し、最適化します。
- エンドポイントは、Microsoft Entra TLS と暗号のセキュリティ要件に準拠している必要があります。 詳細については、「 [TLS と暗号スイートの要件](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/https-cipher-tls-requirements)」を参照してください。

#### ログの使用

一般に、 [Application Insights](https://learn.microsoft.com/ja-jp/azure/azure-functions/functions-monitoring) などの Web API サービスによって有効になっているログ ツールを使用して、API で予期しないエラー コード、例外、パフォーマンス低下を監視すると便利です。

- HTTP 200 または 400 以外の HTTP 状態コードを監視します。
- 401 または 403 HTTP 状態コードは、通常、認証に問題があることを示しています。 API コネクタで API の認証レイヤーとそれに対応する構成を再確認します。
- 開発では、必要に応じて、より積極的なログ レベル ("トレース" や "デバッグ" など) を使用します。
- 長い応答時間について API を監視します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/self-service-sign-up-add-approvals"} -->
## セルフサービス サインアップ フローにカスタム承認を追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-approvals
- Service: entra-external-id / external
- Article date: 2025-05-20
- Summary: 外部 ID セルフサービス サインアップにカスタム承認ワークフローの API コネクタを追加する

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

[API コネクタ](https://learn.microsoft.com/ja-jp/entra/external-id/api-connectors-overview)を使用すると、セルフサービス サインアップを使用して独自のカスタム承認ワークフローと統合できるため、テナントに作成されるゲスト ユーザー アカウントを管理できます。

この記事では、承認システムと統合する方法の例を示します。 この例では、セルフサービス サインアップ ユーザー フローがサインアップ プロセス中にユーザー データを収集し、データを承認システムに渡します。 承認システムでは、次のことが可能です。

- ユーザーを自動的に承認し、Microsoft Entra ID にユーザー アカウントの作成を許可します。
- 手動レビューをトリガーします。 要求が承認された場合、承認システムは Microsoft Graph を使用してユーザー アカウントをプロビジョニングします。 承認システムは、アカウントが作成されたことをユーザーに通知することもできます。

### 承認システム用のアプリケーションを登録する

承認システムは Microsoft Entra テナントのアプリケーションとして登録する必要があります。登録すると、そのシステムは Microsoft Entra ID で認証されて、ユーザーを作成するアクセス許可が付与されます。 [Microsoft Graph の認証と承認の基本について](https://learn.microsoft.com/ja-jp/graph/auth/auth-concepts)詳しく説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**App registrations** に移動し、[**新規登録**] を選択します。
3. アプリケーションの **名前** (サインアップ承認など) を入力 *します*。
4. [ **登録**] を選択します。 他のすべてのフィールドは既定値のままにすることができます。

[Image: [登録] ボタンが強調表示されているスクリーンショット。]

1. 左側のメニューの [ **管理** ] で 、[ **API のアクセス許可**] を選択し、[ **アクセス許可の追加]** を選択します。
2. [ **API のアクセス許可の要求** ] ページ **で、Microsoft Graph** を選択し、[ **アプリケーションのアクセス許可**] を選択します。
3. [ **アクセス許可の選択**] で [ **ユーザー**] を展開し、[ **User.ReadWrite.All** ] チェック ボックスをオンにします。 このアクセス許可は、承認システムが承認時にユーザーを作成することを許可します。 次に、[ **アクセス許可の追加]** を選択します。

[Image: API アクセス許可の要求のスクリーンショット。]

1. **[API のアクセス許可**] ページで、[**管理者の同意を付与する (テナント名)]** を選択し、[**はい**] を選択します。
2. 左側のメニューの [ **管理** ] で[ **証明書とシークレット**] を選択し、[ **新しいクライアント シークレット**] を選択します。
3. シークレットの **説明** ( *承認クライアント シークレット*など) を入力し、クライアント シークレットの有効期限を選択 **します**。 次に、[ **追加]** を選択します。
4. クライアント シークレットの値をコピーします。 クライアント シークレットの値を表示できるのは、作成直後のみです。 シークレットを作成したら、ページを離れる前に必ず保存してください。

[Image: クライアント シークレットのコピーのスクリーンショット。]

1. **アプリケーション ID をクライアント ID** として使用し、Microsoft Entra ID で認証するために生成した**クライアント シークレット**を使用するように承認システムを構成します。

### API コネクタを作成する

次に、セルフサービス サインアップ ユーザー フロー用 [の API コネクタを作成](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-api-connector#create-an-api-connector) します。 承認システム API には、次に示す例のように、2 つのコネクタとそれに対応するエンドポイントが必要です。 これらの API コネクタは次の処理を実行します。

- **承認の状態を確認します**。 ユーザーが既存の承認要求を持っているか、既に拒否されているかどうかを確認するために、ユーザーが ID プロバイダーでサインインした直後に承認システムに呼び出しを送信します。 承認システムが自動承認の決定のみを行う場合、この API コネクタは必要ないことがあります。 "承認状態の確認" API コネクタの例。

[Image: 承認状態の確認 API コネクタ構成のスクリーンショット。]

- **承認の要求** - ユーザーが属性コレクション ページを完了した後、ユーザー アカウントが作成される前に承認システムに呼び出しを送信して、承認を要求します。 承認要求は、自動的に許可したり手動で確認したりすることができます。 "要求の承認" API コネクタの例。

[Image: 要求承認 API コネクタの構成のスクリーンショット。]

これらのコネクタを作成するには、 [API コネクタを作成する手順に](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-api-connector#create-an-api-connector)従います。

### ユーザー フローで API コネクタを有効にする

ここで、次の手順を使用して、セルフサービス サインアップ ユーザー フローに API コネクタを追加します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**User フロー**を参照し、API コネクタを有効にするユーザー フローを選択します。
3. **API コネクタを**選択し、ユーザー フローの次の手順で呼び出す API エンドポイントを選択します。

    - **サインアップ中に ID プロバイダーとフェデレーションした後**: 承認状態 API コネクタを選択します (承認 *状態の確認*など)。
    - **ユーザーを作成する前に**:承認要求 API コネクタ (承認 *要求*など) を選択します。

[Image: ユーザー フローの API コネクタのスクリーンショット。]

1. **[保存] を選択します**。

### API 応答を使用してサインアップ フローを制御する

承認システムでは、呼び出し時にその応答を使用して、サインアップ フローを制御できます。

#### "承認状態の確認" API コネクタの要求と応答

API によって "承認状態の確認" API コネクタから受信した要求の例:

```http
POST <API-endpoint>
Content-type: application/json

{
 "email": "johnsmith@fabrikam.onmicrosoft.com",
 "identities": [ //Sent for Google, Facebook, and Email One Time Passcode identity providers 
     {
     "signInType":"federated",
     "issuer":"facebook.com",
     "issuerAssignedId":"0123456789"
     }
 ],
 "displayName": "John Smith",
 "givenName":"John",
 "lastName":"Smith",
 "ui_locales":"en-US"
}
```

API に送信される正確な要求は、ID プロバイダーによって提供される情報によって異なります。 "email" は常に送信されます。

##### "承認状態の確認" の継続応答

**承認状態の確認** API エンドポイントは、次の場合に継続応答を返す必要があります。

- ユーザーが以前承認を要求していない。

継続応答の例:

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "version": "1.0.0",
    "action": "Continue"
}
```

##### "承認状態の確認" のブロック応答

**承認状態の確認** API エンドポイントは、次の場合にブロック応答を返す必要があります。

- ユーザー承認が保留されている。
- ユーザーが拒否され、再度承認を要求することができない。

ブロック応答の例は次のとおりです。

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "version": "1.0.0",
    "action": "ShowBlockPage",
    "userMessage": "Your access request is already processing. You'll be notified when your request has been approved.",
}
```

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "version": "1.0.0",
    "action": "ShowBlockPage",
    "userMessage": "Your sign up request has been denied. Please contact an administrator if you believe this is an error",
}
```

#### "要求の承認" API コネクタの要求と応答

API によって受信された "要求の承認" API コネクタからの HTTP 要求の例:

```http
POST <API-endpoint>
Content-type: application/json

{
 "email": "johnsmith@fabrikam.onmicrosoft.com",
 "identities": [ // Sent for Google, Facebook, and Email One Time Passcode identity providers 
     {
     "signInType":"federated",
     "issuer":"facebook.com",
     "issuerAssignedId":"0123456789"
     }
 ],
 "displayName": "John Smith",
 "givenName":"John",
 "surname":"Smith",
 "jobTitle":"Supplier",
 "streetAddress":"1000 Microsoft Way",
 "city":"Seattle",
 "postalCode": "12345",
 "state":"Washington",
 "country":"United States",
 "extension_<extensions-app-id>_CustomAttribute1": "custom attribute value",
 "extension_<extensions-app-id>_CustomAttribute2": "custom attribute value",
 "ui_locales":"en-US"
}
```

API に送信される正確な要求は、ユーザーから収集される情報または ID プロバイダーによって提供される情報によって異なります。

##### "要求の承認" の継続応答

**要求承認** API エンドポイントは、次の場合に継続応答を返す必要があります。

- ユーザーは ***自動的に承認できます***。

継続応答の例:

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "version": "1.0.0",
    "action": "Continue"
}
```

重要

継続応答を受信すると、Microsoft Entra ID によってユーザー アカウントが作成され、そのユーザーがアプリケーションに送られます。

##### "要求の承認" のブロック応答

**要求承認** API エンドポイントは、次の場合にブロック応答を返す必要があります。

- ユーザー承認要求が作成されて現在保留になっている。
- ユーザー承認要求が自動的に拒否された。

ブロック応答の例は次のとおりです。

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "version": "1.0.0",
    "action": "ShowBlockPage",
    "userMessage": "Your account is now waiting for approval. You'll be notified when your request has been approved.",
}
```

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "version": "1.0.0",
    "action": "ShowBlockPage",
    "userMessage": "Your sign up request has been denied. Please contact an administrator if you believe this is an error",
}
```

応答内の `userMessage` がユーザーに表示されます。次に例を示します。

[Image: 承認待ちのページの例]

### 手動承認後のユーザー アカウントの作成

カスタム承認システムは、手動承認を取得した後、[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/azuread-users-concept-overview) を使用して[ユーザー](https://learn.microsoft.com/ja-jp/graph/use-the-api) アカウントを作成します。 承認システムがユーザー アカウントをプロビジョニングする方法は、ユーザーによって使用された ID プロバイダーによって異なります。

#### Google または Facebook のフェデレーション ユーザーおよび電子メール ワンタイム パスコードの場合

重要

この方法を使用するには、承認システムは、`identities`、`identities[0]`、および `identities[0].issuer` が存在し、`identities[0].issuer` が 'facebook'、'google'、または 'mail' に一致することを明示的に確認する必要があります。

ユーザーが Google または Facebook アカウントでサインインした場合、またはワンタイム パスコードを電子メールで送信する場合は、 [ユーザー作成 API](https://learn.microsoft.com/ja-jp/graph/api/user-post-users?tabs=http) を使用できます。

1. 承認システムはユーザー フローから HTTP 要求を受信します。

```http
POST <Approvals-API-endpoint>
Content-type: application/json

{
 "email": "johnsmith@outlook.com",
 "identities": [
     {
     "signInType":"federated",
     "issuer":"facebook.com",
     "issuerAssignedId":"0123456789"
     }
 ],
 "displayName": "John Smith",
 "city": "Redmond",
 "extension_<extensions-app-id>_CustomAttribute": "custom attribute value",
 "ui_locales":"en-US"
}
```

1. 承認システムは、Microsoft Graph を使用してユーザー アカウントを作成します。

```http
POST https://graph.microsoft.com/v1.0/users
Content-type: application/json

{
 "userPrincipalName": "johnsmith_outlook.com#EXT@contoso.onmicrosoft.com",
 "accountEnabled": true,
 "mail": "johnsmith@outlook.com",
 "userType": "Guest",
 "identities": [
     {
     "signInType":"federated",
     "issuer":"facebook.com",
     "issuerAssignedId":"0123456789"
     }
 ],
 "displayName": "John Smith",
 "city": "Redmond",
 "extension_<extensions-app-id>_CustomAttribute": "custom attribute value"
}
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| userPrincipalName | あり | API に送信された `email` 要求を取得し、`@` 文字を `_` に置き換え、これを `#EXT@<tenant-name>.onmicrosoft.com` の先頭に追加することによって生成できます。 |
| accountEnabled | あり | `true` に設定する必要があります。 |
| メール | あり | API に送信される `email` 要求と同じです。 |
| ユーザータイプ | あり | `Guest`である必要があります。 このユーザーをゲスト ユーザーとして指定します。 |
| ID | あり | フェデレーション ID 情報。 |
| &lt;その他の組み込み属性&gt; | いいえ | `displayName`、`city` などの他の組み込み属性。 パラメーター名は、API コネクタによって送信されるパラメーターと同じです。 |
| &lt;extension\_{extensions-app-id}\_CustomAttribute&gt; | いいえ | ユーザーに関するカスタム属性。 パラメーター名は、API コネクタによって送信されるパラメーターと同じです。 |

#### フェデレーションされた Microsoft Entra ユーザーまたは Microsoft アカウントユーザーの場合

ユーザーがフェデレーション Microsoft Entra アカウントまたは Microsoft アカウントでサインインする場合は、 [招待 API を](https://learn.microsoft.com/ja-jp/graph/api/invitation-post) 使用してユーザーを作成し、必要に応じて [ユーザー更新 API](https://learn.microsoft.com/ja-jp/graph/api/user-update) を使用してユーザーに属性を割り当てる必要があります。

1. 承認システムはユーザー フローから HTTP 要求を受信します。

```http
POST <Approvals-API-endpoint>
Content-type: application/json

{
 "email": "johnsmith@fabrikam.onmicrosoft.com",
 "displayName": "John Smith",
 "city": "Redmond",
 "extension_<extensions-app-id>_CustomAttribute": "custom attribute value",
 "ui_locales":"en-US"
}
```

1. 承認システムは、API コネクタによって提供される `email` を使用して招待を作成します。

```http
POST https://graph.microsoft.com/v1.0/invitations
Content-type: application/json

{
    "invitedUserEmailAddress": "johnsmith@fabrikam.onmicrosoft.com",
    "inviteRedirectUrl" : "https://myapp.com"
}
```

応答の例:

```http
HTTP/1.1 201 OK
Content-type: application/json

{
    ...
    "invitedUser": {
        "id": "<generated-user-guid>"
    }
}
```

1. 承認システムは、招待されたユーザーの ID を使用して、収集されたユーザー属性でユーザーのアカウントを更新します (省略可能)。

```http
PATCH https://graph.microsoft.com/v1.0/users/<generated-user-guid>
Content-type: application/json

{
    "displayName": "John Smith",
    "city": "Redmond",
    "extension_<extensions-app-id>_AttributeName": "custom attribute value"
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/self-service-sign-up-overview"} -->
## セルフサービス サインアップ - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview
- Service: entra-external-id / external
- Article date: 2024-10-21
- Summary: Microsoft Entra External ID のセルフサービス サインアップを有効にする方法について説明します。 外部ユーザーが自分でアプリケーションにサインアップし、サインアップ エクスペリエンスをカスタマイズし、ユーザー フローを管理できるようにします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

セルフサービス サインアップは、外部 ID の従業員と顧客シナリオに不可欠な機能です。 これは、パートナーやコンシューマー、その他の外部ユーザーが、ユーザーの介入なしにサインアップしてアプリにアクセスするための円滑な方法を提供します。

- B2B コラボレーション シナリオでは、共有するアプリケーションにアクセスする必要があるユーザーが必ずしも事前にわかっているとは限りません。 セルフサービス サインアップを有効にすることで、個人に招待を直接送信する代わりに、外部ユーザーが自分で特定のアプリケーションにサインアップするようにできます。 方法については、「[B2B コラボレーションのためのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow)」を参照してください。
- 顧客 ID とアクセス管理 (CIAM) のシナリオでは、コンシューマー向けに構築したアプリに、セルフサービス サインアップ エクスペリエンスを追加することが重要です。 これを行うには、セルフサービス サインアップ ユーザー フローを構成します。 「[カスタマー エクスペリエンスの計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution)」、または「[顧客向けのサインアップとサインインのユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)」を参照してください。

どちらのシナリオでも、外観をカスタマイズしてソーシャル ID プロバイダーでサインインを提供し、サインアップ プロセス中にユーザーに関する情報を収集することで、パーソナライズされたサインアップ エクスペリエンスを作成できます。

注

組織によって構築されたアプリにユーザー フローを関連付けることができます。 ユーザー フローは、SharePoint や Teams などの Microsoft アプリには使用できません。

### セルフサービス サインアップのユーザー フロー

セルフサービス サインアップ のユーザー フローでは、外部ユーザーに提供するアプリケーションのサインアップ エクスペリエンスを作成します。 ユーザー フローの設定を構成して、ユーザーがアプリケーションにサインアップする方法を制御できます。

- サインインに使用するアカウントの種類 (Facebook などのソーシャル アカウント、または Microsoft Entra アカウント)
- ユーザー サインアップから収集する属性 (名、郵便番号、居住国/地域など)

ユーザーは、Web、モバイル、デスクトップ、またはシングルページ アプリケーション (SPA) を使用して、アプリケーションにサインインできます。 アプリケーションは、ユーザー フローによって提供されるエンドポイントに対する認可要求を開始します。 ユーザー フローによって、ユーザーのエクスペリエンスが定義および制御されます。 ユーザーがサインアップ ユーザー フローが完了すると、Microsoft Entra ID によってトークンが生成され、ユーザーはアプリケーションにリダイレクトされます。 サインアップが完了すると、ディレクトリ内のユーザーに対してアカウントがプロビジョニングされます。 複数のアプリケーションで同じユーザー フローを使用できます。

### セルフサービス サインアップの例

次の B2B コラボレーションの例は、ゲスト ユーザー向けのセルフサービス サインアップ機能を示しています。 Woodgrove のパートナーが Woodgrove アプリを開きます。 ユーザーがサプライヤー アカウントにサインアップすることを決定して、サプライヤー アカウントの要求を選択すると、セルフサービス サインアップのフローが開始されます。

[Image: セルフサービス サインアップの開始ページの例]

ユーザーは、選択した電子メールを使用してサインアップを行います。

[Image: Facebook を選択してサインインする例]

Microsoft Entra ID により、パートナーの Facebook アカウントを使用して Woodgrove との関係が作成され、サインアップした後にユーザーの新しいゲスト アカウントが作成されます。

Woodgrove は、氏名、勤務先名、ビジネス登録コード、電話番号など、ユーザーの詳細を知りたいと考えています。

[Image: ユーザーのサインアップ属性を示す例]

ユーザーは情報を入力し、サインアップ フローを続行して、必要なリソースにアクセスできるようになります。

[Image: サインインしたユーザーを示す例]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/self-service-sign-up-secure-api-connector"} -->
## Microsoft Entra External ID セルフサービス サインアップのユーザー フローで API コネクタとして使用される API をセキュリティで保護する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-secure-api-connector
- Service: entra-external-id / external
- Article date: 2025-04-15
- Summary: セルフサービス サインアップのユーザー フローで API コネクタとして使用されるカスタム RESTful API をセキュリティで保護します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra External ID セルフサービス サインアップのユーザー フロー内で REST API を統合する場合は、認証を使用して REST API エンドポイントを保護する必要があります。 REST API 認証により、Microsoft Entra ID などの適切な資格情報を備えたサービスだけが REST API エンドポイントを呼び出すことができます。 この記事では、REST API をセキュリティで保護する方法について説明します。

### 必須コンポーネント

「 [チュートリアル: サインアップ ユーザー フロー ガイドに API コネクタを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-api-connector) 」の手順を完了します。

API エンドポイントを保護するには、HTTP 基本認証または HTTPS クライアント証明書認証を使用します。 どちらの場合も、API エンドポイントを呼び出すときに Microsoft Entra ID によって使用される資格情報を指定します。 次に、API エンドポイントは資格情報を確認し、承認の決定を行います。

### HTTP 基本認証

HTTP 基本認証は [RFC 2617](https://tools.ietf.org/html/rfc2617) で定義されています。 基本認証は、次のように動作します。Microsoft Entra ID によって、クライアントの資格情報 (`username` と `password`) が `Authorization` ヘッダーに含まれた HTTP 要求が送信されます。 資格情報は、Base64 でエンコードされた文字列 `username:password` として書式設定されます。 その後、お使いの API によってこれらの値がチェックされ、他の承認決定が実行されます。

HTTP 基本認証を使用して API コネクタを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**外部ID**&gt;**概要** に移動します。
3. **[すべての API コネクタ**] を選択し、構成する **API コネクタ**を選択します。
4. **[認証の種類] で** 、[**基本**] を選択します。
5. REST API エンドポイントの **ユーザー名**と **パスワード** を指定します。 [Image: API コネクタの基本的な認証構成のスクリーンショット。]
6. **[保存] を選択します**。

### HTTPS クライアント証明書認証

クライアント証明書認証は、相互証明書ベースの認証です。 クライアントである Microsoft Entra ID は、SSL ハンドシェイクの一部として ID を証明するクライアント証明書をサーバーに提供します。 お使いの API によって、証明書が有効なクライアント (Microsoft Entra ID など) に属していることが確認され、承認の決定が実行されます。 クライアント証明書は、X.509 デジタル証明書です。

重要

運用環境では、この証明書は証明機関によって署名されている必要があります。

#### 証明書を作成する

##### オプション 1: Azure Key Vault を使用する (推奨)

証明書を作成するには、 [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/create-certificate) を使用できます。このコンテナーには、自己署名証明書のオプションと、署名された証明書の証明書発行者プロバイダーとの統合が含まれています。 推奨設定には次が含まれます。

- **件名**: `CN=<yourapiname>.<tenantname>.onmicrosoft.com`
- **コンテンツ タイプ**: `PKCS #12`
- **有効期間の種類**: `Email all contacts at a given percentage lifetime` または `Email all contacts a given number of days before expiry`
- **キーの種類**: `RSA`
- **キー サイズ**: `2048`
- **エクスポート可能な秘密キー**: `Yes` ( `.pfx` ファイルをエクスポートできるようにするため)

その後、 [証明書をエクスポート](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/how-to-export-certificate)できます。

##### オプション 2: PowerShell を使用して自己署名証明書を準備する

証明書をまだ持っていない場合は、自己署名証明書を使用できます。 自己署名証明書は、証明機関 (CA) によって署名されていないセキュリティ証明書であり、CA によって署名された証明書のセキュリティ保証を提供するものではありません。

## [ウィンドウズ](#tab/windows)
Windows では、PowerShell の [New-SelfSignedCertificate](https://learn.microsoft.com/ja-jp/powershell/module/pki/new-selfsignedcertificate) コマンドレットを使用して証明書を生成します。

1. この PowerShell コマンドを実行して、自己署名証明書を生成します。 `-Subject` などのアプリケーションと Azure AD B2C のテナント名に合わせて `contosowebapp.contoso.onmicrosoft.com` 引数を変更します。 また、証明書に別の有効期限を指定するように `-NotAfter` 日付を調整することもできます。

    ```PowerShell
    New-SelfSignedCertificate `
        -KeyExportPolicy Exportable `
        -Subject "CN=yourappname.yourtenant.onmicrosoft.com" `
        -KeyAlgorithm RSA `
        -KeyLength 2048 `
        -KeyUsage DigitalSignature `
        -NotAfter (Get-Date).AddMonths(12) `
        -CertStoreLocation "Cert:\CurrentUser\My"
    ```
2. Windows コンピューターで、[**ユーザー証明書の管理**] を検索して選択します
3. [ **証明書 - 現在のユーザー**] で、[ **個人用**&gt;**Certificates**&gt;*yourappname.yourtenant.onmicrosoft.com* を選択します。
4. 証明書を選択し、 **アクション**&gt;**すべてのタスク**&gt;**Export** を選択します。
5. [**次へ**&gt;次へ] を選択し**、秘密キーをエクスポート**します&gt;**次へ**。
6. [ **ファイル形式のエクスポート**] の既定値をそのまま使用し、[ **次へ**] を選択します。
7. **[パスワード**] オプションを有効にし、証明書のパスワードを入力して、[**次へ**] を選択します。
8. 証明書を保存する場所を指定するには、[ **参照** ] を選択し、任意のディレクトリに移動します。
9. [ **名前を付けて保存** ] ウィンドウで、 **ファイル名**を入力し、[保存] を選択 **します**。
10. [ **次へ**&gt;**完了] を選択**します。

Azure AD B2C で .pfx ファイルのパスワードを受け入れるには、Windows 証明書ストアのエクスポート ユーティリティで、AES256-SHA256 ではなく、TripleDES-SHA1 オプションを使用してパスワードを暗号化する必要があります。

## [macOS](#tab/macos)
macOS では、キーチェーン アクセスの [証明書アシスタント](https://support.apple.com/guide/keychain-access/aside/glosa3ed0609/11.0/mac/11.0) を使用して証明書を生成します。

1. Mac 上の [キーチェーン アクセスで自己署名証明書を作成](https://support.apple.com/guide/keychain-access/kyca8916/mac)する方法の手順に従います。
2. Mac 上のキーチェーン アクセス アプリで、作成した証明書を選択します。
3. [**ファイル**] &gt;**[アイテムのエクスポート] を選択します**。
4. 証明書を保存するファイル名を選択します。 例: **自己署名証明書.p12**。
5. **[ファイル形式**] で、[**Personal Information Exchange (.p12)]** を選択します。
6. **[保存] を選択します**。
7. [パスワード] ボックスと [**確認**] ボックスに**パスワード**を入力します。
8. ファイル拡張子を .pfx に置き換えます。 例: **自己署名証明書.pfx**。

---

#### API コネクタを構成する

クライアント証明書認証を使用して API コネクタを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**外部ID**&gt;**概要** に移動します。
3. **[すべての API コネクタ**] を選択し、構成する **API コネクタ**を選択します。
4. **[認証の種類] で** [**証明書**] を選択します。
5. [ **証明書のアップロード** ] ボックスで、秘密キーを含む証明書の .pfx ファイルを選択します。
6. [ **パスワードの入力** ] ボックスに、証明書のパスワードを入力します。 [Image: API コネクタの証明書認証構成のスクリーンショット。]
7. **[保存] を選択します**。

#### 承認の決定を実行する

API エンドポイントを保護するために、API では、送信されたクライアント証明書に基づいて承認が実装される必要があります。 Azure App Service と Azure Functions については、[API コードから証明書](https://learn.microsoft.com/ja-jp/azure/app-service/app-service-web-configure-tls-mutual-auth)を有効にして検証する方法については、*TLS 相互認証の構成*に関するページを参照してください。 または、任意の API サービスの前のレイヤーとして Azure API Management を使用して、 [クライアント証明書のプロパティ](https://learn.microsoft.com/ja-jp/azure/api-management/api-management-howto-mutual-certificates-for-clients) を目的の値と照合することもできます。

#### 証明書を書き換える

証明書の有効期限が切れたときのアラーム アラートを設定することをお勧めします。 新しい証明書を生成し、使用されている証明書の有効期限が切れそうになったら、この記事の手順を繰り返す必要があります。 新しい証明書の使用を "ロール" するために、新しい証明書がデプロイされている間、一時的に、お使いの API サービスで引き続き古い証明書と新しい証明書を受け入れることができます。

既存の API コネクタに新しい証明書をアップロードするには、API **コネクタで API** コネクタを選択し、[ **新しい証明書のアップロード**] を選択します。 Microsoft Entra ID は、有効期限が切れていない、および開始日が経過した、最近アップロードされた証明書を自動的に使用します。

[Image: 新しい証明書が既に存在する場合のスクリーンショット。]

### API キー認証

一部のサービスは、"API キー" メカニズムを使用して、呼び出し元に HTTP ヘッダーまたは HTTP クエリ パラメーターとして一意のキーを含めることを要求することにより、開発中に HTTP エンドポイントへのアクセスを難読化します。 [Azure Functions](https://learn.microsoft.com/ja-jp/azure/azure-functions/functions-bindings-http-webhook-trigger#authorization-keys) の場合は、API コネクタの`code` にクエリ パラメーターとしてを含めます。 たとえば、`https://contoso.azurewebsites.net/api/endpoint`**`?code=0123456789`** です。

運用環境では、このメカニズムを単独で使用しないでください。 そのため、基本認証または証明書認証の構成は常に必要です。 開発上の目的により、いずれの認証方法も実装しない場合 (推奨されません)、API コネクタの構成で "基本" 認証を選択し、適切な承認を実装する間、API が無視できる一時的な値を `username` と `password` に使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/self-service-sign-up-user-flow"} -->
## B2B ゲスト サインインを追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow
- Service: entra-external-id / external
- Article date: 2026-03-27
- Summary: 作成するアプリのサインアップとサインインのユーザー フローを作成します。 外部 ID を持つユーザーは、サインアップ、ユーザー属性の送信、B2B Collaboration ゲスト アカウントの作成を行うことができます。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ヒント

この記事の対象は、従業員テナントでの B2B コラボレーションのユーザー フローです。 外部テナントの詳細については、「 [サインアップとサインインのユーザー フローの作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)」を参照してください。

構築するアプリケーションのために、ユーザーがアプリにサインアップして新しいゲスト アカウントを作成できるようにするユーザー フローを作成できます。 セルフサービス サインアップ ユーザー フローでは、サインアップ時にユーザーが従う一連の手順、使用を許可する [ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers) 、および収集するユーザー属性を定義します。 1 つのユーザー フローに、1 つ以上のアプリケーションを関連付けることができます。

注

組織によって構築されたアプリにユーザー フローを関連付けることができます。 ユーザー フローは、SharePoint や Teams などの Microsoft アプリには使用できません。

### 前提条件

開始する前に、ID プロバイダーを追加し、カスタム属性を定義することが必要になる場合があります。

#### ID プロバイダーを追加する (省略可能)

Microsoft Entra ID は、セルフサービス サインアップ用の既定の ID プロバイダーです。 つまり、ユーザーは既定で Microsoft Entra アカウントでサインアップできます。 セルフサービス サインアップのユーザー フローに、Google や Facebook などのソーシャル ID プロバイダー、Microsoft アカウント、メールのワンタイム パスコード機能を含めることもできます。 詳細と例については、次の記事をご覧ください。

- [ソーシャル ID プロバイダーの一覧に Google を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation)
- [ソーシャル ID プロバイダーの一覧に Facebook を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/facebook-federation)
- [ID プロバイダーとして Microsoft アカウントを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/microsoft-account)
- [電子メール ワンタイム パスコード認証](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)

#### カスタム属性を定義する (省略可能)

ユーザー属性は、セルフサービス サインアップ時にユーザーから収集される値です。 Microsoft Entra External ID には組み込みで一連の属性が付属していますが、ユーザー フローで使用するカスタム属性を作成することもできます。 また、Microsoft Graph API を使用してこれらの属性を読み書きすることもできます。 [ユーザー フローのカスタム属性の定義を](https://learn.microsoft.com/ja-jp/entra/external-id/user-flow-add-custom-attributes)参照してください。

### テナントのセルフサービス サインアップを有効にする

セルフサービス サインアップのユーザー フローをアプリケーションに追加する前に、テナントに対してこの機能を有効にする必要があります。 その後、コントロールを使用できるようになり、これによってユーザー フローをアプリケーションに関連付けることができます。

注

この設定は、Microsoft Graph API の [authenticationFlowsPolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationflowspolicy?view=graph-rest-1.0&preserve-view=true) リソースの種類を使用して構成することもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**外部コラボレーション設定**に移動します。
3. [ **ユーザー フローによるゲスト セルフサービス サインアップを有効にする]** トグルを **[はい**] に設定します。

    [Image: [ゲストセルフサービスサインアップを有効にする] トグルのスクリーンショット。]
4. **[保存] を選択します**。

### セルフサービス サインアップのユーザー フローを作成する

次に、セルフサービス サインアップのユーザー フローを作成し、アプリケーションに追加します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**ユーザーフロー**に移動し、[**新しいユーザーフロー**] を選択します。

    [Image: 新しいユーザー フロー ボタンのスクリーンショット。]
3. [ **作成** ] ページで、ユーザー フローの名前を入力 **します** 。 名前の先頭に B2X\_1\_ が自動的 **に付けられます**。
4. **ID プロバイダー**の一覧で、外部ユーザーがアプリケーションへのログインに使用できる 1 つ以上の ID プロバイダーを選択します。 (ID プロバイダーを追加する方法については、この記事の前半で *始める前* に参照してください)。
5. [ **ユーザー属性**] で、ユーザーから収集する属性を選択します。 その他の属性については、[ **さらに表示**] を選択します。 たとえば、[ **詳細を表示**] を選択し、[ **国/地域**]、[ **表示名]**、[郵便番号] の属性と要求を選択 **します**。 [ **OK] を選択します**。

    [Image: 新しいユーザー フロー作成ページのスクリーンショット。]

    注

    初回のみ、ユーザーの新規登録時に属性を収集できます。 ユーザーがサインアップした後、ユーザー フローを変更した場合でも、属性情報の収集を求めるメッセージは表示されなくなります。
6. **作成**を選択します。
7. 新しいユーザー フローがユーザー **フロー** の一覧に表示されます。 必要に応じて、ページを更新してください。

### 属性コレクション フォームのレイアウトを選択する

サインアップ ページに属性を表示する順序を選択できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**ユーザーフロー**を参照します。
3. 一覧から、セルフサービス サインアップのユーザー フローを選択します。
4. [ **カスタマイズ**] で、[ **ページ レイアウト**] を選択します。
5. 収集することを選択した属性が一覧表示されます。 表示順序を変更するには、属性を選択し、[ **上へ移動**]、[ **下へ移動**]、[ **上へ移動**]、または **[下へ移動**] を選択します。
6. **[保存] を選択します**。

### セルフサービス サインアップのユーザー フローにアプリケーションを追加する

これで、アプリケーションをユーザー フローに関連付けて、このようなアプリケーションにサインアップできるようになります。 関連付けられているアプリケーションにアクセスする新しいユーザーには、新しいセルフサービス サインアップ エクスペリエンスが表示されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**ユーザーフロー**に移動します
3. 一覧から、セルフサービス サインアップのユーザー フローを選択します。
4. 左側のメニューの [ **使用**] で、[ **アプリケーション**] を選択します。
5. [ **アプリケーションの追加] を選択します**。

    [Image: ユーザー フローにアプリケーションを追加するスクリーンショット。]
6. 一覧からアプリケーションを選択します。 または、検索ボックスを使用してアプリケーションを検索し、それを選択します。
7. **選択**を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/tenant-configurations"} -->
## テナント構成 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations
- Service: entra-external-id / external
- Article date: 2025-05-20
- Summary: 従業員と外部テナントの違いなど、Microsoft Entra External ID のテナント構成について説明します。

*テナント*は、Microsoft Entra ID 専用および信頼されたインスタンスです。 これには、登録済みのアプリやユーザーのディレクトリなど、組織のリソースが含まれています。 テナントを構成する方法は、組織がテナントを使用する方法と、管理するリソースに応じて 2 とおりあります。

- *従業員*テナント構成は、従業員、社内ビジネス アプリ、およびその他の組織リソース向けです。 外部のビジネス パートナーとゲストを従業員テナントに招待できます。
- *外部*テナント構成は、コンシューマーまたはビジネスユーザーにアプリを発行する Microsoft Entra 外部 ID シナリオ専用です。 [外部テナントの外部 ID についてさらに知る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)。

各テナント構成は、組織外のユーザーと連携するための異なるシナリオを表します。

[Image: 外部 ID テナントの構成を示す図。]

### 従業員テナント

従業員テナントは、1 つの組織を表します。 これを使用して、従業員、ビジネス アプリ、およびその他の内部リソースを管理します。 Microsoft Entra ID を使用したことがあれば、すでに職場テナントを知っていることでしょう。 これは、組織が Microsoft Azure、Microsoft Intune、Microsoft 365 などの Microsoft クラウド サービス サブスクリプションにサインアップしたときに自動的に作成される標準テナントです。

従業員テナントでは、外部 ID 機能である [B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を使用して、従業員は外部のビジネス パートナーやゲストと共同作業することができます。

Microsoft Entra 管理センターまたは Azure portal のいずれかで、追加の従業員テナントを作成できます。

### 外部テナント

外部 ID を使用してアプリに顧客の ID 管理とアクセス管理 (CIAM) を追加する場合は、*外部*構成で新しいテナントを作成します。 このテナントは従業員テナントとは別個のものであり、分離しています。 また、Microsoft Entra の標準的なテナント モデルに従っていますが、コンシューマーとビジネス顧客のシナリオに合わせて構成されています。

外部テナントは、アプリの登録、サインアップとサインインのユーザー フローの作成、アプリのユーザーの管理を行う場所です。 アプリにサインアップしたコンシューマーとビジネス顧客はテナント ディレクトリに追加されますが、[制限付きの既定のアクセス許可](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)が付与されます。

#### 外部テナントの作成が必要なタイミング

コンシューマーまたはビジネスのお客様向けのアプリに外部 ID を使用する予定の場合、最初に作成する必要があるリソースは、外部構成を持つ新しいテナントです。

外部テナントは、次の 2 つの方法で作成できます。

- Azure サブスクリプションが既にある場合は、Microsoft Entra 管理センターで[新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal)できます。 テナントを作成するときに、外部構成を選択します。 従業員テナントの作成のみをサポートする Azure portal を使用して外部テナントを作成することはできません。
- まだ Microsoft Entra テナントを持っていなくても、外部テナントで外部 ID 機能を試したい場合は、get-started エクスペリエンスを使用して無料試用版を開始することをお勧めします。

テナントを作成するときに、正しい地理的な場所とドメイン名を設定できます。 現在 Azure Active Directory B2C (Azure AD B2C) を使用している場合、新しい従業員と顧客のテナント モデルは、既存の Azure AD B2C テナントには影響しません。

Von Bedeutung

2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、FAQ で [Azure AD B2C を引き続き購入できますか](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) ? を参照してください。

### 従業員と外部テナントの比較

従業員テナントと外部テナントは基になる同じ Microsoft Entra プラットフォーム上に構築されていますが、いくつかの機能の違いがあります。 テナントの機能の詳細な比較については、「 [従業員と外部テナントでサポートされる機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/tenant-restrictions-migration"} -->
## テナント制限の計画 v1 テナント制限への移行 v2 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-migration
- Service: entra-external-id / external
- Article date: 2024-02-12
- Summary: ユーザー、グループ、アプリケーションのテナント レベルの制限と制御、およびクラウドベースのポータルでのポリシー管理について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

管理者は、[テナント制限 v1](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tenant-restrictions) を使用して、ネットワーク上の外部テナントへのユーザー アクセスを制御します。 ただし、テナント間アクセス設定 [を使用するテナント制限 v2](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2) では、テナント レベルの制限が追加されます。 テナント制限 v2 では、個々のユーザー、グループ、アプリケーションの制御など、細分性も高くなります。

テナント制限 v2 は、ポリシー管理をネットワーク プロキシからクラウドベースのポータルに移動します。 プロキシ ヘッダーのサイズ制限により、組織がターゲットテナントの最大数に達しなくなりました。

テナント制限 v1 からテナント制限 v2 への移行は、その他のライセンス要件のない 1 回限りのプロセスです。 移行を計画する際に、ネットワーク チームと ID チームの利害関係者を含めます。

### 前提条件

- テナント制限 v1 ヘッダーを挿入するプロキシへの管理者アクセス。 プロキシは、オンプレミスでもクラウドベースのサービスからでもかまいません。
- [Microsoft Entra ID P1 または P2](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium) ライセンス。
- 移行の実現可能性の検証。 [テナント制限 v2 については、サポートされていないシナリオを](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#unsupported-scenarios)参照してください。

### 必要なロール

このセクションでは、デプロイに必要な最小限の権限ロールを示します。 セキュリティ管理者ロールを使用するか、 `Microsoft.directory/crossTenantAccessPolicy/`で少なくとも次のアクセス許可を持つカスタム ロールを使用します。

- `Standard/read`
- `Partners/standard/read`
- `Default/standard/read`
- `Basic/update`
- `Default/tenantRestrictions/update`
- `Partners/tenantRestrictions/update`
- `Partners/create`
- `Partners/b2bCollaboration/update`
- `Default/b2bCollaboration/update`

### 新しいテナント制限ポリシーを作成して指定する

プロキシが挿入している現在のヘッダー文字列を取得します。 現在のポリシーを評価し、不要なテナント ID または許可されている宛先を削除します。 評価後、外部テナント ID または外部ドメイン、あるいはその両方の一覧を作成します。

移行用のテナント間アクセス設定とテナント制限 v2 ポリシーを構成します。 テナント間アクセスの送信設定は、内部 ID がアクセスするテナントを定義します。 テナント間アクセス設定では、テナント制限 v2 によって、他の外部 ID がマネージド ネットワーク上にある間にアクセスするテナントが定義されます。

### 技術的な考慮事項

テナント間アクセスの送信設定を構成すると、ポリシーは 1 時間以内に有効になり、テナント制限 v1 ポリシーに加えて評価されます。 テナント間アクセス設定とテナント制限 v1 が評価され、より制限の厳しいオプションが適用されます。

ユーザーに悪影響を与えないようにするには、新しいポリシーでテナント制限 v1 ポリシーを可能な限りミラー化します。 クロステナント アクセス設定で構成するテナント制限 v2 ポリシーは、プロキシを新しいヘッダーで更新した後に有効になります。

次のセクションでは、一般的なシナリオに合った移行と構成の管理を設定します。 このガイダンスを使用して、組織に必要なポリシーを作成します。

#### 特定の外部テナントへのアクセスを内部 ID だけに許可する

従業員などの内部 ID がマネージド ネットワーク上の特定の外部テナントにアクセスすることを許可します。 内部 ID の許可リストにないテナントへのアクセスをブロックします。 請負業者やベンダーなどの外部 ID による外部テナントへのアクセスをすべてブロックします。

1. **テナント間アクセス設定の** [**組織設定**] タブで、[各ドメインまたはテナントを組織として追加](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#add-an-organization)します。
2. すべてのユーザーとグループを許可し、すべてのアプリケーションを許可するには、追加された組織ごとに、 [企業間 (B2B) コラボレーションの送信アクセスを構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#modify-outbound-access-settings)します。

    [Image: [クロステナント アクセス設定] の下の組織設定のタブのスクリーンショット。]
3. すべてのユーザーとグループ、および B2B コラボレーションのすべてのアプリケーションをブロックするには、 [テナント間アクセスの既定の送信設定を構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#configure-default-settings)します。 このアクションは、手順 1 で追加しなかったテナントにのみ適用されます。

    [Image: [クロステナント アクセス設定] の既定の設定のタブのスクリーンショット。]
4. **テナント制限の**既定値で、ポリシー ID を作成します (まだ作成していない場合)。 次 [に、すべてのユーザー、グループ、および外部アプリケーションをブロックするようにポリシーを構成します](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#configure-a-server-side-cloud-policy-for-tenant-restrictions-v2)。 このアクションは、手順 1 で追加しなかったテナントにのみ適用されます。

    [Image: テナント制限の既定値のスクリーンショット。]

#### 特定の外部テナントへのアクセスを内部 ID と外部 ID に許可する

従業員などの内部 ID と、請負業者やベンダーなどの外部 ID が、マネージド ネットワーク上の特定の外部テナントにアクセスできるようにします。 すべての ID の許可リストにないテナントへのアクセスをブロックします。

1. **テナント間アクセス設定の** [**組織設定**] タブで、[各ドメインまたはテナント ID を組織として追加](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#add-an-organization)します。
2. 追加された組織ごとに、内部 ID を有効にするには、すべてのユーザー、グループ、およびアプリケーションを許可するように [B2B コラボレーションの送信アクセスを構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#modify-outbound-access-settings) します。
3. 追加された組織ごとに、外部 ID を有効にするには、すべてのユーザー、グループ、およびアプリケーションを許可するように [組織のテナント制限を構成](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#step-2-configure-tenant-restrictions-v2-for-specific-partners) します。

    [Image: [組織の設定] の下の送信アクセスとテナント制限の詳細のスクリーンショット。]
4. B2B コラボレーションのすべてのユーザー、グループ、アプリケーションをブロックするには、 [テナント間アクセスの既定の送信設定を構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#configure-default-settings)します。 このアクションは、手順 1 で追加しなかったテナントにのみ適用されます。

    [Image: 既定の設定の下の送信アクセス設定のスクリーンショット。]
5. **テナント制限の**既定値で、ポリシー ID を作成します (まだ作成していない場合)。 次 [に、すべてのユーザー、グループ、および外部アプリケーションをブロックするようにポリシーを構成します](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#configure-a-server-side-cloud-policy-for-tenant-restrictions-v2)。 このアクションは、手順 1 で追加しなかったテナントにのみ適用されます。

    [Image: 外部ユーザーとグループ、および外部アプリケーションがブロックされているテナント制限のスクリーンショット。]

注

- コンシューマー Microsoft アカウントを対象にするには、テナント ID: `9188040d-6c67-4c5b-b112-36a304b66dad`を持つ組織を追加します。
- テナント制限 v2 ポリシーは作成されますが、有効ではありません。

### テナント制限 v2 を有効にする

[テナント ID とポリシー ID の値を使用して](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#option-2-set-up-tenant-restrictions-v2-on-your-corporate-proxy)、新しいヘッダーを作成します。 ネットワーク プロキシを更新して、新しいヘッダーを挿入します。

新しい `sec-Restrict-Tenant-Access-Policy` ヘッダーを挿入するようにネットワーク プロキシを更新する場合は、2 つのテナント制限 v1 ヘッダー ( `Restrict-Access-To-Tenants` と `Restrict-Access-Context`) を削除します。

ヒント

- フェーズ ロールアウトでネットワーク プロキシを更新します。 現在のテナント制限 v1 のヘッダーと値を保存します。
- 潜在的な問題のナビゲートに役立つロールバック計画を作成します。

以下のいずれかのパターンを使用して、プロキシ構成を移行します。 プロキシが選択したパターンをサポートしていることを確認します。

- **テナント制限 v2 ヘッダーを使用して一度に 1 つのプロキシをアップグレード**します。このプロキシを経由するユーザーは更新されたヘッダーを受け取り、新しいポリシーが適用されます。 問題を監視します。 問題が発生しない場合は、次のプロキシを更新し、すべてのプロキシを更新するまで続行します。
- **ユーザーに基づいてヘッダーの挿入を更新**する: 一部のプロキシでは認証されたユーザーが必要であり、ユーザーとグループに基づいて挿入するヘッダーを選択する場合があります。 新しいテナント制限 v2 ヘッダーをテスト グループのユーザーにロールアウトします。 問題を監視します。 問題が発生しない場合は、トラフィックの 100% がスコープ内になるまで、段階的にユーザーを追加します。
- **新しいテナント制限 v2 ヘッダーを一度に適用するようにサービスを更新**します。このオプションはお勧めしません。

新しいヘッダーの更新をロールアウトする際には、ユーザーが経験しているのが期待される動作であることをテストして検証します。

### モニター

テナント間アクセスのテナント制限 v2 と送信設定を展開する場合は、サインイン ログを監視します。 また、 [クロステナント アクセス アクティビティ ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-cross-tenant-access-activity) を使用して、ユーザーが承認されていないテナントにアクセスしていないことを確認することもできます。 これらのツールは、誰がどの外部アプリケーションにアクセスしているかを特定するのに役立ちます。

テナント間アクセスとテナント制限の送信設定を構成して、グループ メンバーシップや特定のアプリケーションに基づいて送信アクセスを制限できます。
<!-- /MSL-PAGE -->
