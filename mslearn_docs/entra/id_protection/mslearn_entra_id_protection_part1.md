# Microsoft Learn — Microsoft Entra / ID 保護 (Identity Protection) (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 28

---

<!-- MSL-PAGE {"url":"entra/id-protection"} -->
## Microsoft Entra ID Protection のドキュメント - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection
- Service: entra-id-protection
- Article date: 2025-03-07
- Summary: Microsoft Entra ID Protection を使用して組織内の ID のリスクを特定し、対処する方法について説明します。

Microsoft Entra ID Protection を使用して組織内の ID のリスクを特定し、対処する方法について説明します。

### Microsoft Entra ID Protection について

#### 概要

- [Microsoft Entra ID Protection とは](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)
- [Microsoft Entra ID Protection のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/id-protection/how-to-deploy-identity-protection)

### リスク検出の調査

#### 攻略ガイド

- [リスク ポリシーの構成](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)
- [リスクの調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)

#### リファレンス

- [イベントの種類](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)

### Microsoft Entra ID Protection のエクスペリエンス

#### 概念

- [Microsoft Entra ID Protection ダッシュボード](https://learn.microsoft.com/ja-jp/entra/id-protection/id-protection-dashboard)
- [サインイン エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience)

#### 攻略ガイド

- [リスク検出をシミュレートする](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-simulate-risk)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-identity-protection-b2b"} -->
## B2B ユーザー向けの Microsoft Entra ID Protection - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-b2b
- Service: entra-id-protection
- Article date: 2025-08-06
- Summary: B2B ユーザーに Microsoft Entra ID Protection を使用して組織をセキュリティで保護する方法について説明します。 アカウントのブロックを解除する利点と手順について説明します。

組織の資格情報を使用して、別の組織にゲストとしてサインインできます。 このプロセスは、[企業間または B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)と呼ばれます。 組織は、資格情報が [危険にさら](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)されていると見なされた場合にユーザーのサインインをブロックするように条件付きアクセス ポリシーを構成できます。 Microsoft Entra ID Protection は、B2B ユーザーを含む Microsoft Entra ユーザーの侵害された資格情報を検出します。 資格情報が侵害されたと検出された場合は、他のユーザーがあなたのパスワードを持っていて、不正に使用している可能性があることを意味します。 アカウントのリスクが高まるのを防ぐには、パスワードを安全にリセットし、悪意のある人物が侵害されたパスワードを使用できないようにすることが重要です。

アカウントが危険にさらされていて、ゲストとして別の組織へのサインインがブロックされている場合は、この記事の手順を使用してアカウントを自己修復できる可能性があります。

### アカウントのブロックを解除する

別の組織にゲストとしてサインインしようとして、リスクが原因でブロックされている場合は、次のブロック メッセージが表示されます。 お客様のアカウントについて、疑わしいアクティビティが検出されました。」というブロック メッセージが表示されます。

ゲスト アカウントがブロックされていることを示すエラー メッセージのスクリーンショット [Image: 、組織の管理者に問い合わせてください。]

組織で有効にしている場合は、セルフサービス パスワード リセットを使用してアカウントのブロックを解除し、資格情報を安全な状態に戻すことができます。

1. [\[パスワード リセット ポータル\]](https://passwordreset.microsoftonline.com/) に移動して、パスワード リセットを開始します。 セルフサービス パスワード リセットが自身のアカウントで有効になっておらず、続行できない場合は、以下の情報を使用して IT 管理者に連絡してください。
2. アカウントでセルフサービス パスワード リセットが有効になっている場合は、パスワードを変更する前に、セキュリティ方法を使用して ID を確認するように求められます。 詳細については、「[職場または教育機関のパスワードのリセット](https://support.microsoft.com/account-billing/reset-your-work-or-school-password-using-security-info-23dde81f-08bb-4776-ba72-e6b72b9dda9e)」に関する記事を参照してください。
3. パスワードが正常かつ安全にリセットされると、ユーザーのリスクが修復されます。 これで、もう一度ゲスト ユーザーとしてサインインを試すことができます。

パスワードをリセットした後も、リスクを理由にゲストとしてブロックされている場合は、組織の IT 管理者に連絡してください。

### 管理者としてユーザーのリスクを修復する方法

ID Protection は、Microsoft Entra テナントにとって危険なユーザーを自動的に検出します。 これまで ID Protection のレポートを確認していなかった場合、リスクのあるユーザーが多数存在する可能性があります。 リソース テナントがユーザー リスク ポリシーをゲスト ユーザーに適用できるため、ユーザーは、それまでリスクのある状態を認識していなくても、リスクを理由にブロックされる可能性があります。 ユーザーが、別のテナントでリスクを理由にゲスト ユーザーとしてブロックされていると報告してきた場合は、そのユーザーを修復してアカウントを保護し、コラボレーションができるようにすることが重要です。

#### ユーザーのパスワードをリセットする

Microsoft Entra ID Protection の [危険なユーザー レポート](https://portal.azure.com/#blade/Microsoft_AAD_IAM/SecurityMenuBlade/RiskyUsers) から、影響を受けたユーザーを "ユーザー" フィルターで検索します。 レポートで影響を受けたユーザーを選択し、上部のツール バーの [パスワード  リセット] を選択します。 ユーザーに割り当てられる一時パスワードは、次のサインイン時に変更しなければなりません。 このプロセスによって、ユーザーのリスクが修復されて、資格情報が安全な状態に戻ります。

#### ユーザーのリスクを手動で無視する

パスワードのリセットを選択できない場合は、ユーザーのリスクを手動で無視することができます。 ユーザー リスクを無視しても、ユーザーの既存のパスワードには影響しませんが、このプロセスにより、ユーザーの **リスク状態** が **[リスク時** ] から **[無視済み**] に変更されます。 重要なのは、ID を安全な状態に戻すために使用できるあらゆる方法を使用してユーザーのパスワードを変更することです。

ユーザーのリスクを無視するには、[Microsoft Entra セキュリティ] メニューの [\[危険なユーザー レポート\]](https://portal.azure.com/#blade/Microsoft_AAD_IAM/SecurityMenuBlade/RiskyUsers) に移動します。 "ユーザー" フィルターを使用して影響を受けたユーザーを検索し、そのユーザーを選択します。 ツール バーの [ユーザー リスク **を無視する**] オプションを選択します。 このアクションの完了後、レポートのユーザーのリスク状態が更新されるまでに数分かかる場合があります。

Microsoft Entra ID 保護の詳細については、「[ID 保護とは](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)」を参照してください。

### ID 保護は B2B ユーザーに対してどのように機能するか?

B2B コラボレーション ユーザーのユーザー リスクは、ホーム ディレクトリで評価されます。 これらのユーザーのリアルタイムなサインイン リスクは、ユーザーがリソースにアクセスしようとしたときに、リソース ディレクトリで評価されます。 Microsoft Entra B2B コラボレーションでは、ID Protection を使用して、B2B ユーザーに対するリスクベースのポリシーを適用できます。 これらのポリシーは、次の 2 つの方法で構成できます。

- 管理者は、サイン イン リスクを条件として使用し、ゲスト ユーザーを含めた条件付きアクセスポリシーを構成できます。
- 管理者は、組み込みの ID Protection によるリスクベースのポリシーを構成し、それをすべてのアプリに適用して、ゲスト ユーザーを含めることができます。

### B2B コラボレーション ユーザーの ID Protection に関する制限事項

リソース ディレクトリ内の B2B コラボレーション ユーザーに対する ID Protection の実装については、複数の制限事項があります。これは、それらのユーザーの ID がホーム ディレクトリに存在するためです。 主な制限事項は次のとおりです。

- ゲスト ユーザーが ID Protection のユーザー リスク ポリシーをトリガーしてパスワードのリセットを強制した場合、**そのユーザーはブロックされます**。 このブロックは、リソース ディレクトリ内ではパスワードをリセットできないことが原因です。
- **ゲスト ユーザーは、危険なユーザー レポートには表示されません**。 この制限事項は、リスク評価が B2B ユーザーのホーム ディレクトリで実行されることに起因します。
- 管理者は、リソース ディレクトリ内の**危険な B2B コラボレーション ユーザーを無視したり、修復したりすることはできません**。 この制限事項は、リソース ディレクトリ内の管理者が B2B ユーザーのホーム ディレクトリにアクセスできないことに起因します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-identity-protection-policies"} -->
## Microsoft Entra ID 保護 のリスクベースのアクセス ポリシー - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies
- Service: entra-id-protection
- Article date: 2026-05-15
- Summary: リスクに基づく条件付きアクセス ポリシーを識別する

リスクベースのアクセス制御ポリシーを適用して、サインインまたはユーザーが危険にさらされていることが検出されたときに組織を保護できます。

[Image: 概念的なリスクベースの条件付きアクセス ポリシーを示す図。]

Microsoft Entra 条件付きアクセスには、Microsoft Entra ID 保護 シグナルを利用した 2 つのユーザー固有のリスク条件 ( **[サインイン リスク](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#sign-in-risk)** と **[ユーザー リスク) が](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#user-risk)**用意されています。 組織は、これら 2 つのリスク条件を構成し、アクセス制御方法を選択することで、リスクベースの条件付きアクセス ポリシーを作成できます。 各サインイン時に、ID Protection は検出されたリスク レベルを条件付きアクセスに送信し、ポリシー条件が満たされた場合はリスクベースのポリシーが適用されます。

サインイン リスク レベルが中または高の場合は、多要素認証が必要になる場合があります。 ユーザーは、そのレベルでのみプロンプトが表示されます。

[Image: 自己修復を伴う概念的なリスクベースの条件付きアクセス ポリシーを示す図。]

前の例では、リスクベースのポリシーの主な利点である **自動リスク修復**も示しています。 セキュリティで保護されたパスワードの変更など、ユーザーが必要なアクセス制御を正常に完了すると、そのリスクが修復されます。 そのサインイン セッションとユーザー アカウントは危険にさらされなくなり、管理者からのアクションは必要ありません。

このプロセスを使用してユーザーの自己修復を許可すると、セキュリティ侵害から組織を保護しながら、管理者のリスク調査と修復の負担が大幅に軽減されます。 リスクの修復の詳細については、[「リスクの修復とユーザーのブロック解除」](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)に関する記事を参照してください。

注

[Microsoft Entra ID P2](https://www.microsoft.com/security/business/microsoft-entra-pricing) は、リスクベースのアクセス ポリシーを使用するために必要です。

### ユーザー リスクに基づく条件付きアクセス ポリシー

ID Protection は、ユーザー アカウントに関するシグナルを分析し、ユーザーがセキュリティ侵害された確率に基づいてリスク スコアを計算します。 ユーザーのサインイン動作が危険な場合、または資格情報が漏洩した場合、ID Protection はこれらのシグナルを使用してユーザー リスク レベルを計算します。 管理者は、次のような要件を含む、ユーザー リスクに基づいてアクセス制御を適用するように、リスクベースの条件付きアクセス ポリシーを構成できます。

1. リスクの修復が必要: ID Protection は、すべての認証方法に対して適切な修復フローを管理します。
2. パスワードの変更が必要: ID 保護は、ユーザーが安全なパスワードの変更を完了するまでアクセスをブロックします。
3. アクセスをブロックする: ID 保護は、リスクが対処されるまでユーザーをブロックします。

#1 または #2 を必要とするポリシーでは、エンド ユーザーはユーザーのリスクを修復し、ブロックを解除する必要があります。

### リスク修復制御を要求する

このコントロールでは、アダプティブ リスク修復を使用して、パスワードベースやパスワードレスなど、すべての認証方法に対応する条件付きアクセス リスク ポリシーを作成できます。 つまり、ポリシーの付与コントロールで [リスクの修復が必要] を選択すると、Microsoft Entra ID 保護 は、観察された脅威とユーザーの認証方法に基づいて適切な修復フローを管理します。 アダプティブ リスク修復を有効にする方法の詳細な手順については、「 [リスク ポリシーの構成」](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#microsoft-recommendations)を参照してください。

- **パスワード認証**: 危険なユーザーには、漏洩した資格情報、パスワード スプレー、侵害されたパスワードを含むセッション履歴など、アクティブなリスク検出があります。 ユーザーは、セキュリティで保護されたパスワードの変更を実行するように求められます。完了すると、以前のセッションは取り消されます。
- **パスワードレス認証**: 危険なユーザーはアクティブなリスク検出を行いますが、パスワードが侵害されることはありません。 考えられるリスク検出には、異常なトークン、あり得ない移動、または未知のサインイン プロパティが含まれます。 ユーザーのセッションは取り消され、もう一度サインインするように求められます。
- **攻撃者が追加したデバイス**: 危険なユーザーは、Microsoftの脅威インテリジェンスによって、攻撃者によってデバイスが追加されたとしてフラグが設定されます。 Entra デバイス オブジェクトが無効になっているので、新しいトークンの発行がブロックされます。 ユーザーのセッションは取り消され、再度サインインするように求められます。

##### 特別な考慮事項

- **リスク修復が要求される**のは、サインインのリスクではなく、ユーザーのリスクです。
- ユーザーが複数のポリシーに割り当てられている場合、優先順位が適用されます。 **[Require risk remediation** overrides **Require password change]\(リスク**修復のオーバーライドを要求する\) と **[ブロック]** は、他のすべてのポリシーをオーバーライドします。 競合を回避するには、各ユーザーを一度に 1 つのポリシーにのみ割り当てます。
- **認証の強度** と **サインインの頻度** が必要 - セッションの失効後に、エンド ユーザーが指定された認証強度で再認証をすぐに求められるように、ポリシーに自動的に適用されます。
- **Require risk remediation** は、外部ユーザーとゲスト ユーザーではサポートされていません。これは、Microsoft Entra IDがそれらのユーザーのセッション失効をサポートしていないためです。
- リスクの修復中、Microsoft Entra IDは専用の安全なフローを使用して、セッションの失効などのアクションを実行します。 修復がブロックされないようにするために、このフローは他の条件付きアクセス ポリシーの影響を受けずに続行できます。
    - `AppId`: パブリック クラウド = `93625bc8-bfe2-437a-97e0-3d0060024faa`、米国政府のAzure = `00001111-aaaa-2222-bbbb-3333cccc4444`
    - `ResourceId`: `00000003-0000-0000-c000-000000000000`

### サインイン時のリスクに基づく条件付きアクセス ポリシー

各サインイン時に、ID Protection は数百のシグナルをリアルタイムで分析し、特定の認証要求が承認されない確率を表すサインイン リスク レベルを計算します。 その後、このリスク レベルは条件付きアクセスに送信され、そこで組織の構成済みポリシーが評価されます。 管理者は、サインイン リスクベースの条件付きアクセス ポリシーを構成して、次のような要件を含むサインイン リスクに基づいてアクセス制御を適用できます。

- アクセスのブロック
- アクセスを許可
- 多要素認証を要求する
- 再認証が必要 (サインイン頻度)

サインインでリスクが検出された場合、ユーザーは多要素認証などの必要なアクセス制御を実行して自己修復し、危険なサインイン イベントを閉じて管理者の不要なノイズを防ぐことができます。

[Image: サインイン リスクベースの条件付きアクセス ポリシーのスクリーンショット。]

注

ユーザーは、サインイン リスク ポリシーをトリガーする前に、Microsoft Entra 多要素認証を満たすことができる認証方法を登録しておく必要があります。

### ID Protection リスク ポリシーを条件付きアクセスに移行する

ID Protection (旧称 Identity Protection) でレガシ **ユーザー リスク ポリシー** または **サインイン リスク ポリシー** が有効になっている場合は、 [条件付きアクセスに移行します](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#migrate-risk-policies-to-conditional-access)。

警告

Microsoft Entra ID 保護 で構成されているレガシ リスク ポリシーは **、2026 年 10 月 1 日**に廃止されます。

条件付きアクセスでリスク ポリシーを構成すると、次のような利点が得られます。

- アクセス ポリシーが 1 か所で管理されます。
- レポート専用モードと Graph API の使用。
- サインインの頻度を強制して、毎回再認証を要求する。
- リスクと場所などの他の条件を組み合わせたきめ細かいアクセス制御が提供される。
- さまざまなユーザー グループまたはリスク レベルを対象とする複数のリスクベースのポリシーを使用してセキュリティを強化する。
- サインインログで、どのリスクベースのポリシーが適用されたかを詳細に示す診断エクスペリエンスが向上する。
- バックアップ認証システムのサポート。

### Microsoft Entra 多要素認証の登録ポリシー

ID 保護は、組織がサインイン時に登録を必要とするポリシーを使用して Microsoft Entra 多要素認証をロールアウトするのに役立ちます。 このポリシーを有効にすると、組織内の新しいユーザーが最初の日に MFA に登録されます。 多要素認証は、ID Protection 内のリスク イベントに対する自己修復方法の 1 つです。 自己修復機能を使用すると、ユーザーは自分でアクションを実行でき、ヘルプデスクへの問い合わせを減らすことができます。

Microsoft Entra 多要素認証の詳細については、「 [しくみ: Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)」の記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-identity-protection-risks"} -->
## リスク検出とは - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks
- Service: entra-id-protection
- Article date: 2026-04-22
- Summary: 各リスク イベントの種類の説明と共に、リスク検出とそれに対応するリスク イベントの種類の完全な一覧を確認します。

Microsoft Entra ID Protection は、組織内の疑わしいアクティビティを識別するために使用できる、幅広いリスク検出を提供できます。 この記事に含まれる表は、ライセンス要件を含むサインインとユーザー リスク検出の一覧、または検出がリアルタイムまたはオフラインで行われるかどうかをまとめたものです。 各リスク検出の詳細については、次の表を参照してください。

- ほとんどのリスク検出の詳細については、Microsoft Entra ID P2 が必要です。
    - Microsoft Entra ID P2 ライセンスを持たないお客様は、リスク検出の詳細なしで **検出された追加のリスク** というタイトルの検出を受け取ります。
    - 詳細については、 [License requirements ライセンスの要件](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) を参照してください。
- ワークロード ID リスク検出の詳細については、「 [ワークロード ID のセキュリティ保護」を](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)参照してください。

Note

リアルタイムとオフラインの検出とリスク レベルの詳細については、「 [**リスク検出の種類とレベル**](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-detection-types)」を参照してください。

### riskEventType にマップされたサインイン リスク検出

一覧からリスク検出を選択して、リスク検出の説明、動作方法、およびライセンス要件を表示します。 この表では、 *Premium* は検出に少なくとも Microsoft Entra ID P2 ライセンスが必要であることを示しています。 *Nonpremium* は、検出が Microsoft Entra ID Free で使用可能であることを示します。 `riskEventType`列は、Microsoft Graph API クエリに表示される値を示します。

| サインイン リスク検出 | 検出の種類 | タイプ | riskEventType |
| --- | --- | --- | --- |
| 匿名 IP アドレスからのアクティビティ | Offline | Premium | riskyIPAddress |
| 疑わしい MFA 認証の承認 | Real-time | Premium | authenticatorPhishing |
| 検出された追加のリスク (サインイン) | リアルタイムまたはオフライン | Nonpremium | ジェネリック^ |
| ユーザーに対する管理者確認済みのセキュリティ侵害 | Offline | Nonpremium | adminConfirmedUserCompromised |
| 異常なトークン (サインイン) | リアルタイムまたはオフライン | Premium | anomalousToken |
| 匿名 IP アドレス | Real-time | Nonpremium | anonymizedIPAddress |
| 非定型旅行 | Offline | Premium | unlikelyTravel |
| 不可能な移動 | Offline | Premium | mcasImpossibleTravel |
| 悪意のある IP アドレス | Offline | Premium | maliciousIPAddress |
| 機密ファイルへの大量アクセス | Offline | Premium | mcasFinSuspiciousFileAccess |
| Microsoft Entra の脅威インテリジェンス (サインイン) | リアルタイムまたはオフライン | Nonpremium | investigationsThreatIntelligence |
| 新しい国 | Offline | Premium | newCountry |
| パスワード スプレー | リアルタイムまたはオフライン | Premium | passwordSpray |
| 疑わしいブラウザー | Offline | Premium | suspiciousBrowser |
| 受信トレイからの疑わしい転送 | Offline | Premium | suspiciousInboxForwarding |
| 受信トレイに対する疑わしい操作ルール | Offline | Premium | mcasSuspiciousInboxManipulationRules |
| トークン発行者の異常 | Offline | Premium | tokenIssuerAnomaly |
| 見慣れないサインイン プロパティ | Real-time | Premium | unfamiliarFeatures |
| 検証済み脅威アクター IP | Real-time | Premium | nationStateIP |

^ **追加のリスク検出** の riskEventType は、Microsoft Entra ID Free または Microsoft Entra ID P1 を持つテナントの *汎用* です。 危険なものが検出されましたが、Microsoft Entra ID P2 ライセンスがないと詳細は利用できません。

### riskEventType にマップされたユーザー リスク検出

一覧からリスク検出を選択して、リスク検出の説明、動作方法、およびライセンス要件を表示します。

| ユーザー リスク検出 | 検出の種類 | タイプ | riskEventType |
| --- | --- | --- | --- |
| 検出された追加のリスク (ユーザー) | リアルタイムまたはオフライン | Nonpremium | ジェネリック^ |
| 異常なトークン (ユーザー) | リアルタイムまたはオフライン | Premium | anomalousToken |
| 異常なユーザー アクティビティ | Offline | Premium | anomalousUserActivity |
| 中間攻撃者 | Offline | Premium | attackerinTheMiddle |
| 漏洩した資格情報 | Offline | Nonpremium | leakedCredentials |
| Microsoft Entra の脅威インテリジェンス (ユーザー) | リアルタイムまたはオフライン | Nonpremium | investigationsThreatIntelligence |
| プライマリ更新トークン (PRT) へのアクセス試行の可能性 | Offline | Premium | attemptedPrtAccess |
| 不審な API トラフィック | Offline | Premium | suspiciousAPITraffic |
| 疑わしい送信パターン | Offline | Premium | suspiciousSendingPatterns |
| ユーザーから報告された疑わしいアクティビティ | Offline | Premium | userReportedSuspiciousActivity |

^ **追加のリスク検出** の riskEventType は、Microsoft Entra ID Free または Microsoft Entra ID P1 を持つテナントの *汎用* です。 危険なものが検出されましたが、Microsoft Entra ID P2 ライセンスがないと詳細は利用できません。

### サインイン リスク検出

#### 匿名 IP アドレスからのアクティビティ

この検出は、 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/anomaly-detection-policy#activity-from-anonymous-ip-addresses)によって提供される情報を使用して検出されます。 この検出は、匿名プロキシ IP アドレスとして識別された IP アドレスからユーザーがアクティブであったかどうかを識別します。

- オフラインで計算される
- ライセンス要件:
    - Microsoft Entra ID P2 と Microsoft Defender for Cloud Apps のスタンドアロン ライセンス
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### 疑わしい MFA 認証の承認

この検出は、ASN、ブラウザー、デバイス、GPS の位置情報データなどの未知のプロパティがソーシャル エンジニアリングやフィッシング攻撃の可能性を示すサインインを識別します。 この検出は、 *パスワード + MFA* 認証方法 *と* Microsoft Authenticator アプリからのテレメトリを使用するセッション中に発生します。 これらのシグナルの組み合わせは、検出の精度を高め、サインイン試行に **高リスク**としてフラグを設定するのに役立ちます。 認証 *要求* デバイスと認証 *承認* デバイスの間の場所の近接性は、正当なサインインと攻撃者が開始したセッションを区別するために、Microsoft Entra ID によって分析されます。

- リアルタイムで計算
- ライセンス要件: Microsoft Entra ID P2

#### 検出された追加のリスク (サインイン)

この検出は、Premium 検出のいずれかがトリガーされたことを示します。 Premium 検出は Microsoft Entra ID P2 のお客様にのみ表示されるため、Microsoft Entra ID P2 ライセンスを持たないユーザーに対して **検出された追加のリスク** としてラベル付けされます。

- リアルタイムまたはオフラインで計算
- ライセンス要件: Microsoft Entra ID Free または Microsoft Entra ID P1

#### ユーザーに対する管理者確認済みのセキュリティ侵害

この検出は、管理者が危険なユーザー UI で、または riskyUsers API を使用して、 **ユーザーに対するセキュリティ侵害を確認する** を選択したことを示します。 このユーザーに対するセキュリティ侵害を確認した管理者を調べるには、ユーザーのリスク履歴を (UI または API 経由で) 確認します。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID Free または Microsoft Entra ID P1

#### 異常なトークン (サインイン)

これが検出されたことは、通常とは異なる有効期間や、見慣れない場所からのトークンなど、トークンに異常な特性があることを示しています。 この検出では、"セッション トークン" と "更新トークン" が対象となります。ユーザーの場所、アプリケーション、IP アドレス、ユーザー エージェント、またはその他の特性が予期しない場合、管理者はこのリスクを潜在的なトークンリプレイのインジケーターと見なす必要があります。

異常なトークンは、従来、他の検出よりも多くのノイズが発生するように調整されていました。 最近の検出の改善により、ノイズが減少しました。ただし、この検出によってフラグ付けされた一部のセッションが低および中リスク レベルで誤検知である可能性は、通常よりも高いままです。

- リアルタイムまたはオフラインで計算
- ライセンス要件: Microsoft Entra ID P2
- [異常なトークンの検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#anomalous-token-and-token-issuer-anomaly-detections)

#### 匿名 IP アドレス

このリスク検出の種類は、匿名の IP アドレス (Tor ブラウザーや Anonymizer VPN など) からのサインインを示します。 これらの IP アドレスは、一般に、悪意のある可能性がある意図のためにサインイン情報 (IP アドレス、場所、デバイスなど) を隠したいアクターによって使用されます。

- リアルタイムで計算
- ライセンス要件: Microsoft Entra ID Free または Microsoft Entra ID P1

#### 通常とは異なる移動

このリスク検出の種類では、地理的に離れた場所で行われた 2 つのサインインを識別します。少なくとも 1 つの場所は、ユーザーの過去の行動から考えて、ユーザーの通常とは異なる可能性のある場所でもあります。 このアルゴリズムでは、2 回のサインインの間隔や、最初の場所から 2 回目の場所にユーザーが移動するのに要する時間など、複数の要因が考慮に入れられます。 このリスクは、別のユーザーが同じ資格情報を使用していることを示している可能性があります。

このアルゴリズムは、組織内の他のユーザーによって普通に使用される VPN や場所など、不可能な移動状況の原因になる明らかな "誤検知" を無視します。 システムには、最短で 14 日間または 10 回のログインの初期学習期間があり、その間に新しいユーザーのサインイン行動が学習されます。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID P2
- [通常とは異なる移動の検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#atypical-travel-detections)

#### あり得ない移動

この検出は、 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/anomaly-detection-policy#impossible-travel)によって提供される情報を使用して検出されます。 この検出では、地理的に離れている場所で、最初の場所から 2 番目の場所に移動するのに要する時間より短い時間内に発生した (単一または複数のセッションでの) ユーザー アクティビティを特定します。 このリスクは、別のユーザーが同じ資格情報を使用していることを示している可能性があります。

- オフラインで計算される
- ライセンス要件:
    - Microsoft Entra ID P2 と Microsoft Defender for Cloud Apps のスタンドアロン ライセンス
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### 悪意のある IP アドレス

この検出は、悪意のある IP アドレスからのサインインを示します。 IP アドレスは、その IPアドレスまたはその他の IP 評価ソースから受信した無効な資格情報によるエラー率の高さに基づいて、悪意があるとみなされます。 場合によっては、この検出は以前の悪意のあるアクティビティでトリガーされます。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID P2
- [悪意のある IP アドレスの検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#malicious-ip-address-detections)

#### 機密ファイルへの大量アクセス

この検出は、 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/investigate-anomaly-alerts#unusual-file-access-by-user)によって提供される情報を使用して検出されます。 この検出は、ユーザーが Microsoft SharePoint オンラインまたは Microsoft OneDrive から複数のファイルにアクセスするときに、環境を調べて、アラートをトリガーします。 アラートがトリガーされるのは、アクセスされるファイルの数がこのユーザーにとって普通のことではなく、ファイルに機密情報が含まれている可能性がある場合のみです。

- オフラインで計算される
- ライセンス要件:
    - Microsoft Entra ID P2 と Microsoft Defender for Cloud Apps のスタンドアロン ライセンス
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### Microsoft Entra の脅威インテリジェンス (サインイン)

Microsoft Entra 脅威インテリジェンスは、ユーザーにとって通常とは異なる、または既知の攻撃パターンと一致するユーザー アクティビティを示します。 この検出は、Microsoft 脅威インテリジェンス センター (MSTIC) やその他の Microsoft セキュリティ チームからのデータを含む、Microsoft の内部および外部の脅威インテリジェンス調査に基づいています。 これらの検出は、ログと ID 保護レポートに "Microsoft Entra 脅威インテリジェンス" として表示されます。

- リアルタイムまたはオフラインで計算
- ライセンス要件: Microsoft Entra ID Free または Microsoft Entra ID P1
- [Microsoft Entra 脅威インテリジェンスの検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#microsoft-entra-threat-intelligence)

#### 初めての国

この検出は、 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/anomaly-detection-policy#activity-from-infrequent-country)によって提供される情報を使用して検出されます。 この検出では、新しい場所や頻度の低い場所を判断する際に、過去にアクティビティが発生した場所が考慮されます。 異常検出エンジンにより、組織内のユーザーが以前に使用したことのある場所に関する情報が格納されます。

- オフラインで計算される
- ライセンス要件:
    - Microsoft Entra ID P2 と Microsoft Defender for Cloud Apps のスタンドアロン ライセンス
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### パスワード スプレー

パスワード スプレー攻撃では、一般的なパスワードを使用して統一されたブルート フォース方式で複数の ID が攻撃されます。 Microsoft は、すべての Microsoft Entra テナントでこれらの攻撃を検出するために、IP アドレスやその他の識別子全体のパスワード スプレー パターンを監視します。 リスク検出は、攻撃者がユーザーのパスワードを正常に検証した場合にのみトリガーされます。 ユーザーに対するスプレー試行に失敗しても、検出は生成されません。 テナントで検出が発生すると、Microsoft がスプレー攻撃を観察し、攻撃者がテナント内のユーザーに対して資格情報の検証を成功させたことを確認したことを意味します。 この検出は、攻撃者がリソースにアクセスできたのではなく、ユーザーのパスワードが正しく識別されたことを示します。

- リアルタイムまたはオフラインで計算
- ライセンス要件: Microsoft Entra ID P2
- [パスワード スプレーの検出を調査するためのヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#password-spray-detections)

#### 疑わしいブラウザー

疑わしいブラウザー検出は、同じブラウザー内の異なる国/地域の複数のテナントにわたる疑わしいサインイン アクティビティに基づく異常な動作を示します。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID P2
- [疑わしいブラウザーの検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#suspicious-browser-detections)

#### 受信トレイからの疑わしい転送

この検出は、 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/anomaly-detection-policy#suspicious-inbox-forwarding)によって提供される情報を使用して検出されます。 この検出は、ユーザーがすべてのメールのコピーを外部のアドレスに転送する受信トレイ ルールを作成した場合などの疑わしいメール転送ルールを探します。

- オフラインで計算される
- ライセンス要件:
    - Microsoft Entra ID P2 と Microsoft Defender for Cloud Apps のスタンドアロン ライセンス
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### 受信トレイに対する疑わしい操作ルール

この検出は、 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/anomaly-detection-policy#suspicious-inbox-manipulation-rules)によって提供される情報を使用して検出されます。 ユーザーの受信トレイでメッセージまたはフォルダーを削除または移動する疑わしいルールが設定されている場合、この検出によって環境が調べられ、アラートがトリガーされます。 この検出は、ユーザー アカウントが侵害されていること、メッセージが意図的に非表示にされていること、組織内でスパムまたはマルウェアを配信するためにメールボックスが使用されていることを示している可能性があります。

- オフラインで計算される
- ライセンス要件:
    - Microsoft Entra ID P2 と Microsoft Defender for Cloud Apps のスタンドアロン ライセンス
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### トークン発行者の異常

このリスクの検出は、関連する SAML トークンの SAML トークン発行者が侵害された恐れがあることを意味しています。 トークンに含まれるクレームが、通常とは異なるか、既知の攻撃者パターンと一致しています。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID P2
- [トークン発行者の異常検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#anomalous-token-and-token-issuer-anomaly-detections)

#### 見慣れないサインイン プロパティ

このリスク検出の種類では、過去のサインイン履歴を考慮して異常なサインインを探します。システムによって、以前のサインインに関する情報が保存され、そのユーザーになじみのないプロパティでサインインが発生したときにリスク検出がトリガーされます。 これらのプロパティには、IP、ASN、場所、デバイス、ブラウザー、テナント IP サブネットが含まれます。 新しく作成されたユーザーは、"学習モード" 期間に置かれ、そのユーザーの行動がアルゴリズムによって学習される間、見慣れないサインイン プロパティのリスク検出は停止されます。 学習モードの期間は動的であり、ユーザーのサインイン パターンに関する十分な情報をアルゴリズムが収集するためにかかる時間によって決まります。 最小期間は 5 日です。 無活動状態が長期間続いた場合、ユーザーは学習モードに戻る可能性があります。

この検出は、基本認証 (または従来のプロトコル) に対しても実行されます。 これらのプロトコルには、クライアント ID などの最新のプロパティがないため、誤検知を減らすためのデータが限られています。 お客様には、最新の認証に移行することをお勧めしています。

見慣れないサインイン プロパティは、対話型サインインと非対話型サインインの両方で検出できます。この検出が非対話型サインインで行われた場合、トークン リプレイ攻撃のリスクがあるため精査を増やす必要があります。

見慣れないサインイン プロパティのリスクを選択すると、このリスクがトリガーされた理由の詳細を示す情報を表示できます。

- リアルタイムで計算
- ライセンス要件: Microsoft Entra ID P2

#### 検証済み脅威アクター IP

リアルタイムで計算されます。 この種類のリスク検出では、Microsoft Threat Intelligence Center (MSTIC) のデータに基づいて、国家的なアクターまたはサイバー犯罪グループに関連付けられている既知の IP アドレスと一致するサインイン アクティビティが示されます。

- リアルタイムで計算
- ライセンス要件: Microsoft Entra ID P2

### ユーザー リスク検出

#### 検出された追加のリスク (ユーザー)

この検出は、Premium 検出のいずれかが検出されたことを示します。 Premium 検出は Microsoft Entra ID Premium P2 の顧客にのみ表示されるため、Microsoft Entra ID P2 ライセンスを持っていない顧客には **検出された追加のリスク** というタイトルが付けられます。

- リアルタイムまたはオフラインで計算
- ライセンス要件: Microsoft Entra ID Free または Microsoft Entra ID P1

#### 異常なトークン (ユーザー)

これが検出されたことは、通常とは異なる有効期間や、見慣れない場所からのトークンなど、トークンに異常な特性があることを示しています。 この検出では、"セッション トークン" と "更新トークン" が対象となります。ユーザーの場所、アプリケーション、IP アドレス、ユーザー エージェント、またはその他の特性が予期しない場合、管理者はこのリスクを潜在的なトークンリプレイのインジケーターと見なす必要があります。

異常なトークンは、従来、他の検出よりも多くのノイズが発生するように調整されていました。 最近の検出の改善により、ノイズが減少しました。ただし、この検出によってフラグ付けされた一部のセッションが低および中リスク レベルで誤検知である可能性は、通常よりも高いままです。

- リアルタイムまたはオフラインで計算
- ライセンス要件: Microsoft Entra ID P2
- [異常なトークンの検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#anomalous-token-and-token-issuer-anomaly-detections)

#### 異常なユーザー アクティビティ

このリスク検出では、Microsoft Entra ID での通常の管理ユーザーの動作がベースライン化され、ディレクトリに対する疑わしい変更などの異常な動作パターンが検出されます。 検出は、変更を行った管理者または変更されたオブジェクトに対してトリガーされます。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID P2

#### 中間攻撃者

中間の敵対者とも呼ばれるこの高精度検出は、認証セッションが悪意のあるリバース プロキシにリンクされたときにトリガーされます。 この種の攻撃では、敵対者はユーザーに発行されたトークンを含むユーザーの資格情報をインターセプトできます。 Microsoft Security Research チームは、Microsoft Defender for Cloud Apps を使用して特定されたリスクをキャプチャし、ユーザーを **高** リスクに引き上げます。 管理者は、この検出がトリガーされたときにユーザーを手動で調査し、リスクを確実にクリアすることをお勧めします。 このリスクをクリアするには、セキュリティで保護されたパスワードのリセットや既存のセッションの取り消しが必要になる可能性があります。

- オフラインで計算される
- ライセンス要件:
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### 漏洩した資格情報

このリスク検出は、ユーザーの有効な資格情報が既知の資格情報侵害に出現したことを示します。 Microsoft は、Microsoft Threat Intelligence Center (MSTIC)、Microsoft Digital Crimes Unit (DCU)、および業界パートナーとのパートナーシップを通じて、ダーク Web フォーラム、侵害ダンプ リポジトリ、貼り付けサイト、法執行機関の差し押さえデータ、およびその他のソースを継続的に監視する大規模な資格情報スキャン パイプラインを運用しています。 検出された資格情報が見つかると、サービスは実際の資格情報マテリアルをテナントの現在の有効なパスワード ハッシュに照らして検証します。 検出は、確認された一致が見つかった場合にのみ生成されます。 この検出は、ヒューリスティック信号ではなく、検証された資格情報の公開を表しているため、常に **高リスク** です。 Microsoft Entra を使用したクラウドベースのパスワード リセットは、オンプレミスのパスワードに対してパスワード ハッシュ同期 (PHS) が有効になっている限り、クラウドとオンプレミスのパスワードに対するこの検出のユーザー リスクを修復します。 オンプレミスのパスワード保護の詳細については、「 [Microsoft Defender for Identity アカウントのセキュリティ体制の評価」を](https://learn.microsoft.com/ja-jp/defender-for-identity/security-posture-assessments/accounts#change-password-for-on-premises-account-with-potentially-leaked-credentials-preview)参照してください。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID Free または Microsoft Entra ID P1
- オンプレミス [のパスワードにパスワード ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization) が必要
- [漏洩した資格情報の検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#leaked-credentials-detections)

#### Microsoft Entra の脅威インテリジェンス (ユーザー)

このリスク検出の種類は、ユーザーにとって通常とは異なる、または既知の攻撃パターンと一致する、ユーザー アクティビティを示します。 この検出は、Microsoft 脅威インテリジェンス センター (MSTIC) やその他の Microsoft セキュリティ チームからのデータを含む、Microsoft の内部および外部の脅威インテリジェンス調査に基づいています。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID Free または Microsoft Entra ID P1
- [Microsoft Entra 脅威インテリジェンスの検出の調査に関するヒント。](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#microsoft-entra-threat-intelligence)

#### プライマリ更新トークン (PRT) へのアクセス試行の可能性

このリスク検出の種類は、Microsoft Defender for Endpoint (MDE) によって提供される情報を使用して検出されます。 プライマリ更新トークン (PRT) は、Windows 10、Windows Server 2016 以降のバージョン、iOS、および Android デバイスでの Microsoft Entra 認証のキー アーティファクトです。 PRT とは、これらのデバイスで使用されるアプリケーション間でシングル サインオン (SSO) を有効にするために、マイクロソフトのファースト パーティ トークン ブローカーに発行される JSON Web トークン (JWT) です。 攻撃者は、このリソースにアクセスして、組織への侵入や資格情報の盗難を試みる可能性があります。 これが検出されると、ユーザーは高リスクに移行されます。これは MDE をデプロイしている組織でのみ実行されます。 この検出は高リスクであるため、これらのユーザーを速やかに修復することをお勧めします。 ボリュームが小さいため、ほとんどの組織ではあまり現れません。

- オフラインで計算される
- ライセンス要件:
    - Microsoft Entra ID P2 と Microsoft Defender for Cloud Apps のスタンドアロン ライセンス
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### 不審な API トラフィック

このリスク検出は、異常な GraphAPI トラフィックまたはディレクトリの列挙が観察されたときに報告されます。 不審な API トラフィックは、ユーザーが侵害され、環境内で偵察を行っていることを示している可能性があります。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID P2

#### 疑わしい送信パターン

このリスク検出の種類は、 [Microsoft Defender for Office 365 (MDO)](https://learn.microsoft.com/ja-jp/defender-office-365/air-about)によって提供される情報を使用して検出されます。 このアラートは、組織内の誰かが疑わしい電子メールを送信し、電子メールの送信にリスクがあるか、または制限されている場合に生成されます。 この検出により、ユーザーは中程度のリスクに移行します。これは、MDO をデプロイした組織でのみ発生します。 この検出は少なく、ほとんどの組織ではあまり見られません。

- オフラインで計算される
- ライセンス要件:
    - Microsoft Entra ID P2 と Microsoft Defender for Cloud Apps のスタンドアロン ライセンス
    - Microsoft 365 E5 と Enterprise Mobility + Security E5

#### ユーザーからの疑わしいアクティビティの報告

このリスク検出は、ユーザーが多要素認証 (MFA) のプロンプトを拒否し、それを疑わしいアクティビティと報告した場合に報告されます。 ユーザーによって開始されていない MFA プロンプトは、資格情報が侵害されていることを意味する可能性があります。 この検出を機能させるには、 **疑わしいアクティビティのレポート** 機能を有効にする必要があります。 詳細については、「 [Microsoft Entra MFA 設定の構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#report-suspicious-activity)」を参照してください。

- オフラインで計算される
- ライセンス要件: Microsoft Entra ID P2
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-identity-protection-unified-risk"} -->
## Microsoft Entra ID 保護の統合されたリスクシグナル - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-unified-risk
- Service: entra-id-protection
- Article date: 2026-06-30
- Summary: 統合リスクシグナルがMicrosoft Entra ID 保護とMicrosoft Defender全体で ID リスクを関連付け、複合ユーザー リスクを計算する方法について説明します。

Microsoft Entra ID 保護は、Microsoft Entra ID 保護、Microsoft Defender、およびその他のMicrosoftセキュリティ製品からの相関リスク信号を集計することで、ユーザー リスク評価を強化します。 統一されたリスクシグナルは、個別にアラートを評価する代わりに、製品間で ID 関連のシグナルを関連付け、同じ時間枠内でまとめて評価します。 この複合アプローチは、個々のアラートが見逃す可能性がある調整された攻撃またはステルス攻撃を検出します。

その結果、分離されたシグナルに基づいて以前は中程度のリスクとして表示されていたユーザーは、複数の関連する検出を組み合わせると、高リスクに昇格する可能性があります。 これにより、高度または低シグナル攻撃の検出が向上し、特定されたリスクの高いユーザーの数が増え、リスクベースの条件付きアクセス ポリシーが自動的にトリガーされる可能性があります。

### 前提条件

統合リスク シグナルには、次のものが必要です。

- **Microsoft Entra ID P2** — ユーザー リスクとサインイン リスク検出、ID 保護ポリシー、リスクベースの条件付きアクセス、高度な ID 分析を有効にします。
- **Microsoft 365 E5**（推奨）— Microsoft Entra ID P2 に加え、Microsoft Defender for Endpoint、Microsoft Defender for Identity、Microsoft Defender for Office 365、および製品横断のシグナルを完全に関連付けるための Microsoft Purview が含まれます。
- **Microsoft Defender for Identity** — 構成する必要があります。 Microsoft Defender for Identityなしで統合されたリスクシグナルを有効にしても、ユーザー リスクが複合化されることはありません。

#### 必須のロール

| 役割 | Purpose |
| --- | --- |
| [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) | ID 保護とリスク ポリシーの管理 |
| [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | リスクに基づいて適用を構成する |
| [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator) または [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | アラートとインシデントを調査する |

### 複合リスクのしくみ

強化されたユーザー リスク評価では、個別のアラートではなく、ユーザーの行動全体が評価されます。 システムは、複数のソースからリスク信号を収集し、ユーザーと時間によってそれらを関連付け、単一の複合ユーザー リスク スコアを計算します。

#### 信号ソース

さまざまなMicrosoft製品によって、さまざまな種類の疑わしい動作シグナルが提供されます。

- **Microsoft Entra ID** — 奇妙なサインイン、多要素認証のバイパス、トークンの再利用または誤用。
- **Microsoft Defender for Identity** — オンプレミスのActive Directory攻撃、横移動、資格情報の盗難手法。
- **Microsoft Defender for Endpoint** — デバイス レベルの実行、マルウェア、NTDS.dit アクセス。
- **Microsoft Defender for Cloud Apps** — 疑わしい OAuth アプリ、セッションの異常、SaaS アプリケーション アクティビティ。
- **Microsoft Purview** — リスクの高いデータ アクセス、インサイダーまたはコンプライアンス関連の懸念。

#### シグナルの相関関係

システムは、ユーザーと時間ごとにシグナルをラインアップします。 同じユーザーが短時間で複数の異常な信号を表示した場合、それらの信号は接続済みとして扱われます。 各シグナルには重大度と信頼度レベルがあります。 信頼度の低いアラートは、単独の場合は低いままですが、他の信号と共に発生するとより深刻になります。

相関関係では、次の 4 つの重要な要因が使用されます。

- **キル チェーンの幅** — 複数の攻撃段階 (偵察、資格情報アクセス、横移動) にまたがるアラートは、意図を持つ攻撃者であり、事故ではないことを示します。
- **アラートの種類** — 異なる製品の複数の異なるアラートの種類によって、誤検知が発生する可能性は低いです。
- **時間的な集中** — 短い間隔で発生するアラートは、数週間にわたってばらばらに発生するアラートよりも、相互に関連している可能性が高くなります。
- **製品間の相関関係** — 各製品には、現実の部分的なビューが表示されます。 それらを関連付けると、ギャップが解消され、誤った仮定が減ります。

#### 以前の動作と比較した変更点

以前は、システムは時間の経過と同時にリスクを軽減できましたが、非高度なアラートの蓄積のみに基づいてユーザーのリスク レベルを上げることはできませんでした。 統合されたリスクシグナルを使用すると、Microsoft Entra ID 保護は、同時に発生する複数の中または低信頼信号に基づいて、ユーザーのリスクを**高**に昇格できるようになりました。

### アカウントセットとリンク済みアカウント

Microsoft Defender for Identityは、Microsoft Entra IDアカウント自体だけでなく、**アカウント セット**からのリスクシグナルを提供できます。 アカウント セットは、次のような複数のアカウント間で同じ人間の ID を表します。

- オンプレミス Active Directory アカウント
- サードパーティ ID プロバイダー アカウント (Okta など)
- 別のドメインの特権アカウントまたはレガシ アカウント

これらのリンクされたアカウントのいずれかがアクティブな攻撃を受けている場合、Microsoft Defender for Identityは引き続き疑わしいアクティビティを検出し、アカウント セットの新しい ID リスクシグナルを発生させます。 これらのシグナルはMicrosoft Entra ID 保護に送り返され、ユーザーの全体的なリスク スコアに再集計されます。

リンクされたMicrosoft Entra アカウントの場合、統合リスクでは、リンクされているすべてのアカウントで**最大リスク レベル**が使用されます。

Note

Microsoft Entra ID アカウント自体が変更されていない場合でも、リンクされたアカウントに対する基になる攻撃が続く限り、ユーザーのリスクは繰り返し再び発生する可能性があります。 Microsoft Defenderでのアカウントのリンク方法の詳細については、「[アカウントを ID にリンクまたはリンク解除する](https://learn.microsoft.com/ja-jp/defender-for-identity/manage-related-identities-accounts)」を参照してください。

### 複合リスクを表示する

#### Microsoft Entra ID 保護 では

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインします。
2. **[ID Protection (ID 保護)]**&gt;**[Risky users (危険なユーザー)]** に移動します。
3. **リスクの高い**ユーザーの詳細を開くには、高リスクとしてマークされたユーザーを選択します。
4. **タイムライン**で、進行状況を探します。
    - ユーザーは、最初は個々の検出によって **中** リスクとしてフラグが設定されました。
    - ユーザー リスクは**、統合されたリスク シグナル**によって**高**に昇格されました。
5. リスク **検出の詳細を**表示するには、リスクの高いイベントに関連付けられている [リスク検出] リンクを選択します。
    - **検出の種類**: 統合されたリスクシグナル
    - **リスク レベル**: 高 (複合)
    - **リスクの詳細**: Microsoft Entra ID 保護 によりユーザー リスクが引き上げられました

Microsoft Defenderでユーザーのアカウントへのリンクを表示するには、[**ユーザー検出**] セクションで **[列の追加と削除**] を選択し、[**追加情報**] を有効にします。

#### Microsoft Defender内

1. Microsoft Entraのリスク検出の詳細から、[**追加情報**] セクションの **[ここをクリックして詳細**] リンクを選択します。
2. これにより、Microsoft Defender ポータル (**Assets**&gt;) でユーザーのページの [**インシデントとアラート**] タブが開きます。
3. 貢献するシグナルとそのサービス ソースを確認します。
4. [**リスク スコア**] タブを選択すると、ID の全体的なリスク スコア (0 から 100)、ID にリンクされたアカウント セット、および各アカウントのMicrosoft Entra IDリスク レベルが表示されます。
5. [ **概要** ] タブを選択すると、すべてのアラートとインシデント、複合リスク、インサイダー リスクの重大度や爆発半径などのコンテキスト情報が表示されます。

詳細については、「[Microsoft Defenderでの ID の調査」を](https://learn.microsoft.com/ja-jp/defender-xdr/investigate-users)参照してください。

### アラートの同期とリスクの減衰

Important

リスクの減衰ロジックが原因で、一部のアラートの同期の問題が発生する可能性があります。 これにより、アラートがMicrosoft EntraまたはMicrosoft Defenderで表示されますが、他のアラートは表示されません。 これにより、24 時間以内に両側でアラートが減衰すると、自動的に解決されます。

リスク検出イベントは、既存のデータ フローに変更を加えずにMicrosoft DefenderおよびMicrosoft Sentinelにストリーミングされます。 統合リスク シグナルが展開されるにつれて、**条件付きアクセスによりブロック** イベントが増えていることに気付くかもしれません。 これは、アラートが個別に評価されたときに、以前は無害と見なされていた調整された攻撃アクティビティをシステムが正しく識別して停止していることを示します。

### 統合リスクシグナルのトラブルシューティング

#### 無視されたリスクが返され続ける

Microsoft Entraでユーザーのリスクを無視しても戻り続ける場合、その原因は通常、Microsoft Defenderの**リンクされたアカウント**に対する継続的な攻撃アクティビティです。 解雇後も、Microsoft Defender for Identityはリンクされたオンプレミスまたはサードパーティのアカウントで疑わしいアクティビティを検出し続け、新しいシグナルをMicrosoft Entra ID 保護に送り返し、ユーザーのリスクに再集計します。

それを解決するには、次のようにします。

1. Microsoft Defender ポータルで投稿シグナルを調査します。 **Assets**&gt;**Identities** に移動し、ユーザーを選択します。 [ **インシデントとアラート** ] タブを確認して、進行中のシグナルのソースを特定します。
2. リンクされたアカウントに対する基になる攻撃を修復します (たとえば、オンプレミスの AD パスワードのリセット、侵害されたアカウントの無効化、セッションの取り消しなど)。
3. 攻撃が解決されると、シグナルが停止して減衰すると、リスク スコアが自動的に低下します。

#### 複合リスク イベントを特定する

複合リスク イベントでは、次の値が使用されます。

| フィールド | 価値 |
| --- | --- |
| リスク イベントの種類 | `aiCompoundAccountRisk` |
| リスクの詳細 | `aiElevatedAccountRisk` |
| 検出の種類 | 統合されたリスクシグナル |

### 実際の攻撃シナリオ

次の表は、リスクの複合化によって検出精度が向上する方法の例を示しています。

| シナリオ | アラートが検出されました | 複合なし | 複合あり |
| --- | --- | --- | --- |
| セッション Cookie の盗難 | 異常なトークン (Microsoft Entra) + 疑わしい OAuth アプリ (Microsoft Defender for Cloud Apps) | 中程度のリスク (各アラートは個別に評価されます) | **高リスク**（製品間の相関 + キルチェーンの広さ） |
| マルチステージ攻撃 | 通常とは異なるサインイン (Microsoft Entra ID) + Kerberoasting (Microsoft Defender for Identity) + NTDS.dit のダンプ (Microsoft Defender for Endpoint) | 中程度のリスク (単一の信頼度の高いアラートなし) | **高リスク**（キルチェーンの3段階 + 時間的収束） |
| 中間者攻撃型 (AiTM) フィッシング | 匿名 IP (Microsoft Entra ID) + AiTM 経由で侵害されたユーザー (Microsoft Defender for Office 365) | 中程度のリスク (Microsoft Entraアラートだけでは信頼度が低い) | **高リスク** (Microsoft Defender アラートによって侵害が確認されます) |

### サポートの範囲

統合されたリスクシグナルは、Microsoft Entra ID 保護とMicrosoft Defenderにまたがる。 ケースは、顧客が開始する場所に基づいてトリアージされます。

- **Microsoft Entra** (ID 保護、条件付きアクセス) 以降: Identity Protection サービス チームが所有しています。
- **Microsoft Defender以降**: リスク計算にMicrosoft Entra ID 保護シグナルが含まれている場合でも、Defender サポート チームが所有しています。

調査によって、リンクされたオンプレミスアカウントアクティビティを含むMicrosoft Defenderシグナルによってユーザーリスクが駆動されていることが確認されると、Microsoft Defenderは調査と解決のための所有チームになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-identity-protection-user-experience"} -->
## Microsoft Entra ID 保護 を使用したユーザーの自己修復 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience
- Service: entra-id-protection
- Article date: 2025-10-30
- Summary: 管理者がリスクを許可する場合、ユーザーはどのようにしてリスクを自己修復できますか? そうでない場合のエクスペリエンスは何ですか?

Microsoft Entra ID 保護 と条件付きアクセスを使うと、次のことができます。

- ユーザーに対して Microsoft Entra 多要素認証への登録を必須にする
- 危険なサインインと侵害されたユーザーを自動的に修復する
- 特定のケースでユーザーをブロックする。

ユーザーとサインインのリスクを統合する条件付きアクセス ポリシーは、ユーザーのサインイン エクスペリエンスに影響します。 多要素認証とセキュリティで保護されたパスワードの変更やセルフサービス パスワード リセット (SSPR) Microsoft Entra などのツールをユーザーが使用できるようにすることで、影響を軽減できます。 これらのツールは、適切なポリシーの選択と共に、強力なセキュリティ制御を適用しながら、ユーザーが必要なときに自己修復オプションを提供します。

### 多要素認証登録

管理者が Microsoft Entra 多要素認証の登録を必要とする ID 保護ポリシーを有効にすると、ユーザーは Microsoft Entra 多要素認証を使用して自己修復できます。 このポリシーを構成すると、ユーザーは登録する 14 日間の期間が与え、その後、ユーザーは強制的に登録されます。

#### 登録中断

1. Microsoft Entra 統合アプリケーションにサインインするときに、ユーザーはアカウントを多要素認証に設定することを求める通知を受け取ります。 このポリシーは、新しいデバイスを使用する新しいユーザーに対する Windows Out of Box Experience でもトリガーされます。

    [Image: ブラウザー ウィンドウで必要な詳細情報のプロンプトを示すスクリーンショット。]
2. ガイド付き手順を完了して、Microsoft Entra 多要素認証に登録し、サインインします。

### リスクの自己修復

管理者がリスクベースの条件付きアクセス ポリシーを構成すると、影響を受けるユーザーは構成されたリスク レベルに達すると処理が中断されます。 管理者が多要素認証を使用した自己修復を許可すると、このプロセスは通常の多要素認証プロンプトとしてユーザーに表示されます。

ユーザーが多要素認証を完了すると、リスクが修復され、サインインできます。

[Image: サインイン時の多要素認証プロンプトを示すスクリーンショット。]

サインインだけでなく、ユーザーが危険にさらされている場合、管理者は条件付きアクセスでユーザー リスク ポリシーを構成して、多要素認証に加えてパスワードの変更を要求できます。 その場合、ユーザーには次の追加画面が表示されます。

[Image: ユーザーのリスクが検出された場合、パスワード変更を示すスクリーンショットがプロンプトで要求されます。]

#### アダプティブ リスク修復

アダプティブ リスク修復ポリシーは、パスワード ベースとパスワードレスを含むすべての認証方法に対応します。 このポリシーの許可コントロールには、自動的に**認証の強度を要求する**ことと**サインインの頻度 - 毎回**が含まれています。これにより、セッションが取り消されるたびにユーザーに再認証を求めるメッセージが表示されます。 詳細については、「concept-identity-protection-policies#require-risk-remediation-control-preview」を参照してください。

ユーザーがこのポリシーを有効にしてリスクを修復する必要がある場合、ユーザーはセッションが取り消された直後にサインインする必要があります。 ユーザーがサインインしたばかりで、危険にさらされている場合は、もう一度サインインするように求められます。 リスクは、ユーザーが 2 回目に正常にサインインした後に修復されます。

#### 管理者による危険なサインインの解除

管理者は、リスク レベルに応じて、サインイン時にユーザーをブロックする場合があります。 ブロックを解除するには、ユーザーは IT スタッフに連絡するか、使い慣れた場所またはデバイスからサインインを試す必要があります。 この場合、自己修復は選択できません。

[Image: アカウントがブロックされている画面を示すスクリーンショット。]

IT スタッフは、「 [ユーザーのブロックを解除する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock#unblock-users) 」の手順に従って、ユーザーがサインインし直せるようにすることができます。

### 危険度の高い技術者

ホーム テナントで自己修復ポリシーが有効になっていない場合は、技術者のホーム テナントの管理者がリスクを修復する必要があります。 次に例を示します。

1. 組織に、クラウド環境の構成を担当するマネージド サービス プロバイダー (MSP) またはクラウド ソリューション プロバイダー (CSP) があります。
2. MSP 技術者の資格情報の 1 つが漏洩し、高リスクがトリガーされます。 その技術者が、他のテナントへのサインインをブロックされます。
3. ホーム テナントで[危険度の高いユーザーのパスワード変更を必要とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user)該当のポリシーまたは[危険なユーザーの MFA](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-sign-in)が有効になっている場合、技術者は自己修復してサインインできます。
    - ホーム テナントで自己修復ポリシーが有効になっていない場合は、技術者のホーム テナントの管理者が[リスクを修復する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-risk-detection-types"} -->
## リスク検出の種類とレベル - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-detection-types
- Service: entra-id-protection
- Article date: 2026-06-10
- Summary: リアルタイム検出とオフライン検出の違いなど、リスク検出とリスク レベルについて説明します。

Microsoft Entra ID 保護 は、組織にテナントでの疑わしいアクティビティに関する情報を提供し、さらにリスクが発生するのを防ぐために迅速に対応できるようにします。 リスク検出は、ディレクトリ内のユーザー アカウントとサービス プリンシパルに関連する疑わしいアクティビティや異常なアクティビティを含めることができる強力なリソースです。 ID Protection のリスク検出は、個々のユーザーまたはサインイン イベントにリンクし、 [危険なユーザー レポート](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports)で見つかった全体的なユーザー リスク スコアに貢献できます。

ユーザー リスク検出では、潜在的な脅威アクターが資格情報を侵害してアカウントにアクセスした場合、または異常なユーザー アクティビティが検出された場合に、正当なユーザー アカウントにリスクとしてフラグを設定する可能性があります。 サインイン リスク検出では、付与された認証要求が、アカウントの承認された所有者でない確率を表します。 ユーザー レベルとサインイン レベルでリスクを特定する機能を備えることは、顧客がテナントの安全を確保する上で非常に重要です。

注

リスク検出の完全な一覧、リスク検出の計算方法、およびライセンス要件については、「 [**リスク検出とイベントの種類**](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。

### リスク レベル

ID 保護では、リスクを低、中、高の 3 つのレベルに分類します。 リスク レベルは機械学習アルゴリズムによって計算され、承認されていないエンティティによって 1 つ以上のユーザーの資格情報が認識されているという Microsoft の確信度を表します。

検出は、信頼レベルに応じて、複数のリスク レベルで発生する可能性があります。 たとえば、[見慣れないサインイン プロパティ](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#unfamiliar-sign-in-properties)は、サインイン特性への慣れレベルに基づいて、高、中、または低でトリガーされる可能性があります。 [漏洩した資格情報](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#leaked-credentials)や[検証済みの脅威アクター IP](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#verified-threat-actor-ip) などのその他の検出は、漏洩した資格情報または脅威アクターの証明が見つかったため、常に高リスクとして提供されます。

リスク レベルは、 [優先順位を付け、調査し、修復](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#investigation-and-risk-remediation-framework)する検出を決定する際に重要です。 リスク レベルは、調査と修復の作業に優先順位を付けるのに役立ちます。

リスク レベルは、 [リスクベースの条件付きアクセス ポリシーを構成](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#choosing-acceptable-risk-levels)する際にも重要な役割を果たします。各ポリシーは、低、中、高、またはリスク検出なしをトリガーするように設定できます。 組織のリスク許容度に基づいて、ID Protection がいずれかのユーザーに対して特定のリスク レベルを検出したときに MFA またはパスワードのリセットを必要とする条件付きアクセス ポリシーを作成できます。 これらのポリシーにより、ユーザーはリスクを解決するため自己修復できるようになります。

Von Bedeutung

**低** レベルのリスク検出とユーザーは 6 か月間製品に保持され、その後は自動的に期限切れになり、よりクリーンな調査エクスペリエンスが提供されます。 **中** リスクレベルと **高** リスク レベルは、修復または無視されるまで保持されます。

リスクレベル: が設定されているリスク検出

- **高** は、Microsoft がアカウントが侵害されていることを非常に確信していることを意味します。 脅威インテリジェンスや既知の攻撃パターンなどのシグナルは、リスク検出の信頼度レベルに影響します。
- **Medium** は、1 つ以上の中程度の重大度の異常が検出されたことを示しますが、アカウントが侵害されたという信頼度は低くなります。 サインイン パターン、動作、およびその他のシグナルは、リスク検出の信頼度レベルに影響します。
- **低** は、サインインまたはユーザーの資格情報に異常が存在することを示しますが、アカウントが侵害されていないという確信は低くなります。 サインインの前後のサインイン パターンは、パターンがあるかどうか、またはサインインが異常であるかどうかを判断するために使用されます。

### リアルタイム検出とオフライン検出

ID 保護は、認証後にリアルタイムまたはオフラインでいくつかのリスクを計算することで、ユーザーのリスク検出およびサインインのリスク検出の精度を高めるテクノロジを利用しています。 サインイン時にリアルタイムでリスクを検出すると、ユーザーがサインイン中に自己修復でき、管理者が潜在的な侵害をすばやく調査できるように、リスクを早期に特定できるという利点があります。 サインイン中にトリガーされる条件付きアクセス ポリシーは、アカウントにアクセスする前に無効なアクターを停止できます。

オフラインで計算された検出により、脅威アクターがアカウントにアクセスした方法と正当なユーザーへの影響に関するより多くの分析情報を得ることができます。 一部の検出は、オフラインとサインイン中の両方でトリガーできるため、侵害検出の信頼性が向上します。

リアルタイムでトリガーされた検出は、レポートに詳細が表示されるまでに 5 分から 10 分かかります。 オフライン検出は、潜在的なリスクの特性を評価するのに時間を要するため、レポートに反映されるまでに最大 48 時間かかります。 一部のリスク検出はサインイン後にオフラインで計算されるため、リスク レベルが変更される可能性があることに注意してください。

| 検出の種類 | サインイン リスク | ユーザーのリスク |
| --- | --- | --- |
| **リアルタイム** | 不審なサインインが検出され、MFA を要求するなど、条件付きアクセス ポリシーを使用してリアルタイムで修復できます。 | ユーザー リスクが検出され、セキュリティで保護されたパスワードの変更をリアルタイムで強制するなど、条件付きアクセス ポリシーを使用して修復できます。 |
| **オフライン** | サインイン リスクは、サインイン後に識別されます。 修復しないと、より多くのリスクが検出され、ユーザー リスクに集約された場合、このリスクはユーザー リスクにエスカレートする可能性があります。 | サインイン後、ユーザーは危険と見なされます。 条件付きアクセス ポリシーが構成されている場合、ユーザーは次回の認証時にセルフサービス パスワード リセットを実行するまでブロックされます。 |

注

システムは、ユーザー リスク スコアの原因となったリスク イベントが次のいずれかであると判断する場合があります。

- 誤検知、または他のエラー
- ユーザー リスクは [、ポリシーによって修復されました](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock) (多要素認証またはセキュリティで保護されたパスワードの変更を完了することによって)。

システムはリスクの状態を無視し、リスクの詳細を **AI によって確認されたサインイン セーフ**に設定するため、リスク状態はユーザーの全体的なリスクに影響しなくなります。

#### 時間検出

リスクの詳細データでは、 **時刻の検出** に、ユーザーのサインイン中にリスクが特定された正確な瞬間が記録されています。これにより、リアルタイムのリスク評価と、ユーザーと組織を保護するためのポリシーの即時適用が可能になります。 **検出の最終更新日時** には、新しい情報、リスク レベルの変更、または管理アクションに起因する可能性があるリスク検出の最新の更新内容が表示され、最新情報に基づくリスク管理が保証されます。

これらのフィールドは、リアルタイムの監視、脅威への対応、組織のリソースへの安全なアクセスの維持に不可欠です。

#### 場所

リスク検出の場所は、IP アドレス検索を使用して特定されます。 信頼できる [ネームド ロケーション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network#trusted-locations) からのサインインにより、Microsoft Entra ID 保護のリスク計算の精度が向上し、信頼済みとしてマークされた場所から認証するときにユーザーのサインイン リスクが軽減されます。

注

**riskEventType テーブルにマップされているリスク検出**をお探しですか? 新しい [**リスク検出とイベントの種類**](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks) に関する記事に移動しました。

### エージェントの検出

**リスク検出**レポートには、**Microsoft Entra エージェント ID** を使用した自律 AI エージェント専用のリスク検出を表示するエージェント[検出](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-professional/what-is-microsoft-entra-agent-id)専用のタブが含まれています。 これらの検出は、エージェントに関連付けられている疑わしいアクティビティを特定するのに役立ち、管理者は潜在的な脅威を効果的に監視して対応できます。 エージェントに関連付けられているリスク検出の一覧については、「エージェントの [ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents#activities-contributing-to-risk)」を参照してください。

[Image: リスク検出レポートの [エージェント検出] 列を示すスクリーンショット。]

エージェントがユーザーの委任されたアクセス許可を使用して動作するエージェントの代理フローでは、危険なアクティビティはエージェントではなく **ユーザー** に帰属します。 このアプローチは、他のユーザーのエージェントを中断することなく、侵害されたユーザー セッションでの修復を対象としています。 この表のリスク検出は、自律エージェント アクティビティに特に適用されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-risk-reports"} -->
## ID 保護リスク レポート - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports
- Service: entra-id-protection
- Article date: 2025-11-05
- Summary: Microsoft Entra ID Protection リスク レポートにアクセスし、フィルター処理し、使用して、ユーザーとサインインを危険または確認された侵害としてマークする方法について説明します。

Microsoft Entra ID Protection は、ID ベースのリスクを自動的に検出して対応することで、組織を保護するのに役立ちます。 自動修復は多くの脅威を処理しますが、状況によっては手動による調査とアクションが必要な場合があります。 ID Protection リスク レポートには、ユーザー、サインイン、ワークロード ID に影響を与える潜在的なセキュリティ上の脅威を特定、調査、対応するために必要な分析情報が表示されます。

### リスク レポートにアクセスする

[ID 保護ダッシュボード](https://learn.microsoft.com/ja-jp/entra/id-protection/id-protection-dashboard)には、潜在的なリスクを特定するためにいつでも使用できる重要な分析情報の概要が表示されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインします。
2. **ID Protection**&gt;**Dashboard** に移動します。
3. [ID 保護] ナビゲーション メニューからレポートを選択します。

[Image: Microsoft Entra ID Protection ダッシュボードを示すスクリーンショット。]

各レポートは、レポートの上部に表示されている期間のすべての検出の一覧と共に起動します。 ユーザー設定に基づいて列をフィルター処理したり、列を追加または削除したりできます。 データを.CSVまたは.JSON形式でダウンロードして、さらに処理します。 詳細な分析のためにレポートをセキュリティ情報およびイベント管理 (SIEM) ツールと統合するには、「 [診断設定の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)参照してください。

### 詳細を表示してアクションを実行する

レポート内のエントリを選択すると、表示しているレポートによって異なる詳細が表示されます。 詳細ウィンドウから、選択したユーザーまたはサインインに対してアクションを実行することもできます。 1 つまたは複数のエントリを選択し、リスクを確認するか、無視することができます。 ユーザーからパスワード リセット フローを開始することもできます。 これらの機能には異なるロール要件があるため、オプションがグレー表示されている場合は、より高い特権ロールが必要です。 詳細については、「 [ID 保護に必要なロール](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#required-roles)」を参照してください。

#### 危険なユーザー

選択した危険なユーザーの詳細は、修復された、無視された、または現在危険にさらされ、調査が必要なリスクに関する情報を提供します。 また、関連するリスク検出に関する詳細も提供されます。 詳細な概要については、 [危険なユーザー レポートを](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-user-report)参照してください。

次の場合、ユーザーは危険なユーザーになります。

- 彼らには一つ以上のリスクの高いサインインがあります。
- 漏洩した資格情報など、ユーザーのアカウントで検出された[リスク](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)が 1 つ以上ある場合。

##### Security Copilot

また、Security Copilot を使用している場合は、ユーザー リスク レベルが上昇した理由や、軽減と対応方法に関するガイダンスなどのシナリオで [、自然言語の概要](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization) にアクセスできます。

#### 危険なサインイン

危険なサインイン レポートには、危険にさらされている、侵害が確認された、安全な確認済み、無視された、または修復されたサインインが一覧表示されます。 詳細ウィンドウには、調査中に役立つ可能性のあるサインイン試行に関する詳細情報が表示されます。たとえば、サインイン試行に関連するリアルタイムおよび集計のリスク レベルや、トリガーされる検出の種類などです。

**危険なサインインの詳細は** 次のとおりです。

- ユーザーがアクセスしようとしたアプリケーション
- 適用された条件付きアクセス ポリシー
- MFA の詳細
- デバイス、アプリケーション、および場所の情報
- リスクの状態、リスク レベル、およびリスク検出のソース (ID 保護または Microsoft Defender for Endpoint)

**危険なサインイン** レポートには、過去 30 日間 (1 か月間) のフィルター可能なデータが含まれています。 ID 保護では、対話型か非対話型かにかかわらず、すべての認証フローについてリスクを評価します。 危険なサインイン レポートには、対話型サインインと非対話型サインインの両方が表示されます。このビューを変更するには、"サインインの種類" フィルターを使用します。

[Image: 危険なサインイン レポートのスクリーンショット。]

アクション ボタンが淡色表示されている場合は、より高い特権ロールが必要です。 管理者は危険なサインイン イベントに対してアクションを実行し、次の操作を選ぶことができます。

- **サインインを侵害ありと確認** - このアクションでは、サインインが真陽性であることを確認します。 修復手順が取られるまで、サインインは危険とみなされます。
- **サインインを安全と確認** - このアクションでは、サインインが擬陽性であることを確認します。 同じようなサインインは、将来的に危険とみなされるべきではありません。
- **サインイン リスクを無視する** - このアクションは無害な真陽性に使用されます。 検出されたこのサインイン リスクは実際のものですが、既知の侵入テストや承認済みアプリケーションで生成された既知のアクティビティによるリスクと同様に、悪意のあるものではありません。 同様のサインインについては、今後もリスクを評価する必要があります。

これらの各アクションを実行するタイミングの詳細については、「[Microsoft でのリスクに関するフィードバックの使用方法](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-risk-feedback#how-does-microsoft-use-my-risk-feedback)」をご覧ください。

#### 危険なエージェント

ID 保護は、組織内の危険なエージェントを特定するのに役立ちます。 このレポートには、 [Microsoft Entra Agent ID](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-professional/what-is-microsoft-entra-agent-id) プラットフォーム内のすべての ID が含まれます。 **危険なエージェント** レポートには、各エージェントのリスク検出の概要と、レポートから直接アクションを実行するのに役立つツールが用意されています。

**危険なエージェントの詳細** は次のとおりです。

- エージェントの表示名
- リスクの状態とリスク レベル
- エージェントの種類
- エージェント スポンサー

アカウントで 1 つ以上のリスク イベントが検出されると、エージェントは危険なエージェントになります。 詳細については、「 [エージェントの ID 保護」を](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)参照してください。

#### 危険なワークロード ID

[ワークロード ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) は、アプリケーションがリソースにアクセスできるようにする ID であり、ユーザーのコンテキストでアクセスできる場合があります。 リスクの高いワークロード ID の詳細ページから、サービス プリンシパルのサインインログと監査ログにアクセスして詳細な分析を行うことができます。

Important

ワークロード ID Premium のお客様は、完全なリスクの詳細とリスクベースのアクセス制御を利用できます。ただし、 **[ワークロード ID Premium](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-faqs)** ライセンスを持たないお客様は、レポートの詳細が制限された検出をすべて受け取ります。

**危険なワークロード ID の詳細は次** のとおりです。

- サービス主体 ID
- リスクの状態とリスク レベル
- リスクの履歴

#### リスクの検出

リスク検出レポートは、ユーザーとサインインに関連付けられているさまざまなリスク検出に関する分析情報を提供します。詳細には、検出されたリスクの種類、それに関連するユーザー、またはサインイン、およびリスクの現在の状態に関する情報が含まれます。 詳細ウィンドウから、関連付けられているユーザー リスク レポート、ユーザーのサインイン、およびリスク検出にアクセスすることもできます。

**リスク検出の詳細** は次のとおりです。

- 検出の種類
- リスクの状態、リスク レベル、およびリスクの詳細
- 攻撃の種類
- リスク検出のソース (ID 保護または Microsoft Defender for Endpoint)

リスク検出レポートには、最長で過去 90 日間 (3 か月) のフィルター可能なデータが含まれています。

[Image: リスク検出レポートのスクリーンショット。]

リスク検出レポートで提供される情報を使用して、管理者は以下を確認できます。

- 各リスク検出に関する情報
- MITRE ATT&CK フレームワークに基づく、攻撃の種類
- 同時にトリガーされたその他のリスク
- サインインが試行された場所
- Microsoft Defender for Cloud Apps の詳細へのリンク
- [エージェント検出](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents) (プレビュー)

管理者は、ユーザーのリスク レポートまたはサインイン レポートに戻り、収集された情報に基づいてアクションを実行できます。

注

システムによって次のことが検出される場合があります。

- リスクスコアに寄与したリスクイベントは偽陽性でした。又は
- MFA プロンプトの完了やパスワードのセキュリティで保護された変更など、ポリシーの適用によってリスクが修復されました。

そのため、システムは「AI がサインインの安全性を確認した」ことでリスク状態とリスクの詳細を解消し、ユーザーのリスクに影響を与えなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-risky-agents"} -->
## エージェントの ID 保護 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents
- Service: entra-id-protection
- Article date: 2026-06-17
- Summary: 危険なエージェントMicrosoft Entra ID 保護識別する方法について説明します。

組織が自律 AI エージェントを導入、構築、デプロイするにつれて、それらのエージェントを監視および保護する必要性が重要になります。 Microsoft Entra ID 保護は、[Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id)によって提供されるエージェント ID を持つエージェントに対する ID ベースのリスクを自動的に検出して対応することで、組織を保護するのに役立ちます。

### [前提条件]

#### 役割

Risky Agent レポートを使用するには、次のいずれかの管理者ロールが割り当てられている必要があります。

- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)
- [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)
- [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)

エージェント リスクを条件として使用するポリシーを構成するには、 [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) ロールが割り当てられている必要があります。

#### ライセンス

間もなく、エージェントの ID 保護では、Microsoft Entra エージェント IDを介してエージェントに保護を拡張するために、[Microsoft Agent 365 ライセンス](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)[が](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id#how-to-get-started)必要になります。

### 動作方法

エージェントはユーザーに代わって自律的に動作できるため、一意のサインイン動作を表示できます。 エージェントは、イニシアチブを取り、機密データと対話し、大規模に運用できます。 エージェントのMicrosoft Entra ID 保護は、これらの機能に関連するリスクを識別して軽減します。 **学習モード** では、十分なアクティビティ履歴がないエージェントの動作アラートが自動的に抑制され、オンボード中や非アクティブな期間後に誤検知が防止されます。 別の検出処理が並行して実行され、初期段階での真に悪意のある挙動も引き続き検出されるようにします。 エージェントが疑わしい動作を示すと、ID Protection によってアクティビティに危険なフラグが設定されます。

### リスクに貢献する活動

次の表に、リスクのフラグが設定されているエージェントに影響を与える可能性がある異常なアクティビティを示します。 現時点では、危険なエージェントのすべてのリスク検出はオフラインです。

Note

エージェントがユーザーの委任されたアクセス許可を使用して動作する On-Behalf-of (OBO) フローでは、危険なアクティビティはエージェントではなく **ユーザー** に帰属します。 このアプローチは、他のユーザーのエージェントを中断することなく、侵害されたユーザー セッションでの修復を対象としています。 特に明記されていない限り、この表のリスク検出は自律エージェント アクティビティにのみ適用されます。

| エージェントのリスク検出 | Description | riskEventType |
| --- | --- | --- |
| 侵害が確認されました | 管理者がエージェントのセキュリティ侵害を確認しました | adminConfirmedAgentCompromised |
| 初期の生活の悪意のあるアクティビティ | 新しく作成されたエージェントは、攻撃者のように動作する複数の疑わしい動作パターンをすぐに示しました。 | earlyLifeMaliciousActivity |
| Entra Directory の情報収集 | エージェントは、疑わしい偵察または危険度の高いディレクトリ操作を実行しました。 | entraDirectoryReconnaissance |
| 失敗したアクセス試行 | エージェントが承認されていないリソースにアクセスしようとしましたが、アクセスできませんでした。 この検出は、攻撃者が承認されていないリソースに対してエージェントのトークンを再生しようとしていることを示している可能性があります。 | アクセス試行失敗 |
| Microsoft Entra の脅威インテリジェンス | Microsoft内部および外部の脅威インテリジェンス ソースに基づいて、既知の攻撃パターンと一致するアクティビティを特定しました。 | 脅威インテリジェンスアカウント |
| サインインの急増 | エージェントは、通常のサインイン頻度と比較して、より多くのサインインを行いました。 このスパイクは、攻撃者がオートメーションまたはツールキットを使用していることを示している可能性があります。 | ログインスパイク |
| 疑わしい資格情報の使用 | この検出は、新しい資格情報がエージェント ブループリントに追加され、実際に使用されるときにフラグを設定します。 | 不審な資格情報の使用 |
| 未知のリソース アクセス | エージェントが通常アクセスしないリソースを対象とします。 この検出は、攻撃者がエージェントの意図した目的を超えて機密性の高いリソースにアクセスしようとしていることを意味する可能性があります。 | 見慣れないリソースアクセス |

### 危険なエージェント レポートを表示する

**危険なエージェント** レポートには、危険な動作のフラグが設定されたすべてのエージェントの一覧が表示されます。 危険なエージェントの概要が [ID 保護ダッシュボード](https://learn.microsoft.com/ja-jp/entra/id-protection/id-protection-dashboard)に表示されます。 このスナップショット ビューでは、リスク レベル別にリスクのフラグが設定されたエージェントの数の概要を示します。 [ **リスクの高いエージェントの表示** ] を選択して、完全なレポートを開きます。

ID 保護ナビゲーション メニューから **危険なエージェント** レポートに直接移動することもできます。 フィルター処理と並べ替えによって、特定のエージェント、リスク状態、またはリスク レベルを検索します。

[Image: 危険なエージェント レポートを示すスクリーンショット。]

エージェントに対するアクションは、次のようなレポートから直接実行できます。

- **侵害の確認**: 手動調査または自動検出によってアカウントが侵害されていることを確認した後に選択します。 この手順は、さらなる損害を防ぐためにインシデント対応の一環として役立ちます。 侵害を確認すると、リスク レベルが自動的に [高] に設定され、エージェントの **リスク検出**でイベントが作成されます。 このアクションにより、高いエージェント リスクに対するアクセスをブロックするように構成されたリスクベースの条件付きアクセス ポリシーがトリガーされます。
- **安全な状態を確認**する: 調査後にユーザーを安全としてマークし、リスク レベルを [なし] に設定して、そのユーザーのアクティブなリスク状態をクリアします。 このオプションは、誤検知をマークする場合や、システムが同様のアクティビティにフラグを設定しないようにする場合に使用します。
- **リスクを却下**: エージェントに対する検出されたリスクが調査後に重要でないと判断された場合、または無害な真の陽性であり、システムが類似の活動を引き続きフラグ付けする必要があることをシステムに通知します。
- **Disable**: Microsoft Entra IDおよび接続されているアプリ全体でそのエージェントのすべてのサインインを禁止します。

### 危険なエージェントの詳細を表示する

危険なエージェント レポートで、エントリを選択して、そのエージェントの対応するリスク検出を含む完全な詳細を表示します。 すべての ID Protection リスク レポートと同様に、レポートまたは詳細ビューからエージェントに対して直接アクションを実行できます。

**危険なエージェントの詳細** は次のとおりです。

- エージェントの表示名と ID
- リスクの状態とリスク レベル
- エージェントの種類とスポンサー (指定されている場合)

また、 **リスク検出** レポートに移動し、[ **エージェントの検出** ] タブを選択して、過去 90 日間までの検出リスク イベントの完全な一覧を表示することもできます。 リスク検出は、調査目的で最大 90 日間保持されます。

[Image: 危険なエージェントの詳細を示すスクリーンショット。]

### エージェントのリスクベースの条件付きアクセス

[エージェントの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)を使用して、危険なエージェントがリソースやその他のエージェントにアクセスするのをブロックするリスク ポリシーを設定します。 この [条件付きアクセス テンプレート](https://aka.ms/CreateAgentRiskPolicy) を使用して、組織内でのこのポリシーの展開を簡略化します。

### Microsoft Graph

また、Microsoft Graph APIを使用して、危険なエージェント クエリを実行することもできます。 [ID Protection API](https://learn.microsoft.com/ja-jp/graph/api/resources/identityprotection-overview) には 2 つのコレクションがあります。

- `riskyAgents`
- `agentRiskDetections`

### リスク データのエクスポート

データをエクスポートするには、[Microsoft Entra IDで診断設定](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-export-risk-data)を構成して、リスク データをLog Analytics ワークスペースに送信したり、ストレージ アカウントにアーカイブしたり、イベント ハブにストリーミングしたり、SIEM ソリューションに送信したりできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-risky-user-report"} -->
## 危険なユーザー レポート - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-user-report
- Service: entra-id-protection
- Article date: 2025-11-05
- Summary: Microsoft Entra ID 保護 の危険なユーザー レポートについて説明します

どのユーザーが危険にさらされているのか、 *なぜ* 危険にさらされているかを知ることは、セキュリティ管理者と ID 管理者の重要な責任です。 Microsoft Entra ID 保護 の危険なユーザー レポートには、リスク データの概要とアクティビティのタイムラインと共に、完全なレポートが用意されています。

また、リスクの高いユーザー レポートは、拡張されたエージェントの提案と分析情報を得るための Identity Risk Management Agent (プレビュー) と統合されています。 Identity Risk Management エージェントを有効にしている場合は、レポートの標準ビューとエージェント ビューを切り替えることができます。

この記事では、危険なユーザー レポートで使用できる情報とアクションの概要について説明します。

### [前提条件]

このレポートにアクセスするには、次のものが必要です。

- Microsoft Entra ID Free、Microsoft Entra ID P1 (ユーザーに関する制限付きデータ用)。
- 危険なユーザー データへのフル アクセスのための Microsoft Entra ID P2 ライセンス。
- [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) と [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator) は、レポートの *標準ビュー* を使用するために必要な最小限の特権ロールです。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#search-administrator) は、レポートの *エージェント ビュー* を使用し、Identity Risk Management エージェントの機能にアクセスする必要があります。
- パスワードをリセットするには[、ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)が必要です。

### 危険なユーザー レポート

危険なユーザー レポートの標準ビューには、3 つの主要なセクションが含まれています。各レベルの危険なユーザーの概要グラフ、1 日あたりの新しい危険なユーザー、危険なユーザーの完全な一覧です。 [Identity Risk Management エージェント](https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-risky-user-report)を有効にしている場合は、**エージェント ビュー**を使用してエージェントの提案と分析情報を表示できます。

**各リスク レベル グラフの危険なユーザーの割合**は、ユーザーとそのリスク レベルを視覚的に表したものです。 この視覚的な概要を使用すると、組織内の状態をすばやく確認できます。 グラフの各セグメントにカーソルを合わせると、各リスク レベルでのユーザーの割合が表示されます。

[Image: 各リスク レベル グラフの危険なユーザーの割合のスクリーンショット。]

[ **New risky users per day]\(リスクの高い 1 日あたりの新しいユーザー** 数\) グラフには、組織内で危険なユーザーが検出された時期のタイムラインが表示されます。 グラフには、ユーザーまたは管理者によってリスクが修復されたかどうかも示されます。 グラフ内の任意のポイントにカーソルを合わせると、危険なユーザーと修復アクティビティの内訳が表示されます。

[Image: 1 日あたりの新しいリスクの高いユーザーのグラフのスクリーンショット。]

レポートの下半分には、危険なユーザーの完全な一覧が含まれています。

- リスクの詳細を表示するには、危険なユーザーの名前を選択します。
- 侵害の確認やリスクの無視など、アクションを実行する 1 人以上のユーザーの横にあるチェック ボックスをオンにします。
- アクション オプションが淡色表示されている場合は、より高い特権ロールが必要です。 詳細については、「 [Microsoft Entra ID 保護 とは」を](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#required-roles)参照してください。

[Image: 危険なユーザーの一覧のスクリーンショット。]

### 危険なユーザーの詳細

危険なユーザーの詳細ページから、リスクを無視したり、ユーザーのパスワードをリセットしたりするなどのアクションを実行できます。

**危険なユーザー レポート**から、ユーザーを選択してリスク イベントの詳細を表示し、そのユーザーに対してアクションを実行することもできます。

詳細には、ユーザーに関する基本情報と、最近のリスク アクティビティのタイムラインが含まれます。 **[タイムライン]** セクションには、ユーザーに関連付けられているリスク イベントの時系列ビューが表示されます。 タイムラインには、リスクが検出されたタイミング、リスク レベル、検出されたリスクの種類が表示されます。

リスク サインイン イベントと危険なユーザー イベントを表示するには、[リスクの高い **サインインによるリスクシグナルの集計** ] チェック ボックスをオンにします。

#### 統合されたリスクシグナル

Microsoft Entra ID 保護は、Microsoft Defenderやその他のソースからのシグナルを関連付け、ユーザー リスク検出のための統合されたリスクシグナルを提供します。 この機能は、リンクされたアカウントやアカウント セットなど、ID ファブリック全体からの複数の ID シグナルに基づいて、包括的な ID リスク スコアを計算します。

リスクの高いユーザー レポートの標準ビューとエージェント ビューの両方で、統合されたリスクシグナルを表示できます。 一覧からユーザーを選択すると、リスクの高いユーザーに関連付けられているリンクされた各アカウントの詳細が表示され、ユーザーの ID 全体のリスクの全範囲を理解するのに役立ちます。 ID リスク スコアが発生すると、Microsoft Entra スコアも発生し、リスクベースの条件付きアクセス ポリシーを自動的にトリガーできます。

ID リスク スコアは、危険なユーザー レポートから選択したユーザーのコンテキスト内に表示されます。 スコア、リスクの概要、さらに調査するためのリンクが提供され、リスクを理解し、適切なアクションを実行するのに役立ちます。 **Microsoft Defender の [View full report in Microsoft Defender]\(Microsoft Defender で完全なレポートを表示**\) リンクを選択して、Microsoft Defender for Identity の相関シグナルを表示し、リスクの高いユーザーをさらに調査します。

統合リスクのしくみ、前提条件、機能を有効にする方法、トラブルシューティングの詳細については、[Microsoft Entra ID 保護の統合リスクシグナルを](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-unified-risk)参照してください。

### 危険なユーザーに対してアクションを実行する

ユーザー レベルでアクションを実行すると、そのユーザーに現在関連付けられているすべての検出に適用されます。 アクション ボタンが淡色表示されている場合は、より高い特権ロールが必要です。 管理者はユーザーに対してアクションを実行し、次の操作を選ぶことができます。

- **パスワードのリセット** - このアクションにより、ユーザーの現在のセッションが取り消されます。
- **ユーザーが侵害されたことを確認する** - このアクションは正の真陽性に対して実行されます。 ID Protection は、ユーザー リスクを高く設定し、新しい検出を追加します。管理者がユーザーの侵害を確認しました。 修復手順が取られるまで、そのユーザーはリスクがあるとみなされます。
- **ユーザーの安全を確認する** - このアクションは誤検知に対して実行されます。 その結果、このユーザーのリスクと検出が削除され、使用プロパティを再学習するための学習モードに配置されます。 このオプションを使用して、誤検知をマークすることができます。
- **ユーザー リスクを無視する** - このアクションは、無害な陽性のユーザー リスクに対して実行されます。 検出されたこのユーザー リスクは実際のものですが、既知の侵入テストによるリスクのような悪意のあるものではありません。 同様のユーザーについては、今後もリスクを評価する必要があります。
- **ユーザーのブロック** - このアクションは、攻撃者がパスワードへのアクセス権や MFA の実行能力を持っている場合に、ユーザーがサインインするのをブロックします。
- **Microsoft 365 Defender で調査する** - このアクションを実行すると、管理者は Microsoft Defender ポータルに移動して、さらに調査できるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/concept-workload-identity-risk"} -->
## Microsoft Entra ID 保護を使ったワークロード ID のセキュリティ保護 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk
- Service: entra-workload-id
- Article date: 2025-08-06
- Summary: Microsoft Entra ID 保護におけるワークロードのアイデンティティ リスク

Microsoft Entra ID 保護は、ワークロード ID を検出、調査、修復して、ユーザー ID に加えてアプリケーションとサービス プリンシパルを保護できます。

[ワークロード ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) は、アプリケーションがリソースにアクセスできるようにする ID であり、ユーザーのコンテキストでアクセスできる場合があります。 これらのワークロード ID は、従来のユーザー アカウントとは次の面で異なります:

- 多要素認証を実行できません。
- 通常、正式なライフサイクル プロセスがありません。
- 資格情報またはシークレットをどこかに保存する必要があります。

これらの違いによって、ワークロード ID の管理が難しくなり、侵害リスクが高くなります。

重要

ワークロード ID Premium のお客様は、完全なリスクの詳細とリスクベースのアクセス制御を利用できます。ただし、 [ワークロード ID Premium](https://entra.microsoft.com/#view/Microsoft_Azure_ManagedServiceIdentity/WorkloadIdentitiesBlade) ライセンスを持たないお客様は、レポートの詳細が制限された検出をすべて受け取ります。

注

ID Protection は、シングル テナント、Microsoft 以外の SaaS、マルチテナント アプリのリスクを検出します。 マネージド ID は現在スコープ内にありません。

### 前提条件

管理センターのリスク検出の **[危険なワークロード ID** ] や [ **ワークロード ID 検出** ] タブなど、ワークロード ID **リスク** レポートを利用するには、次のものが必要です。

- 次のいずれかの管理者ロールが割り当てられます

    - [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)
    - [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)
    - [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)
- [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)ロールが割り当てられているユーザーは、リスクを条件として使用するポリシーを作成できます。
- リスクの高いワークロード ID に対してアクションを実行するには、 [ワークロード ID Premium](https://www.microsoft.com/security/business/identity-access/microsoft-entra-workload-identities#office-StandaloneSKU-k3hubfz) ライセンスが必要なリスクベースの条件付きアクセス ポリシーを設定することをお勧めします。ワークロード [ID の](https://portal.azure.com/#view/Microsoft_Azure_ManagedServiceIdentity/WorkloadIdentitiesBlade)表示、試用の開始、およびライセンスの取得を行うことができます。

注

Microsoft Security Copilotを使用すると、自然言語プロンプトを使用して、危険なワークロード ID に関する分析情報を取得できます。 Microsoft Entraで Microsoft Security Copilot を使用してアプリケーションのリスクを 評価する方法について説明します。

### ワークロードアイデンティティのリスク検出

サインイン時の動作やオフラインでの侵害の兆候からのワークロード ID のリスクを検します。

| 検出名 | 検出の種類 | 説明 | リスクイベントタイプ |
| --- | --- | --- | --- |
| Microsoft Entra の脅威インテリジェンス | オフライン | このリスク検出は、Microsoft の内部および外部の脅威インテリジェンス調査に基づく既知の攻撃パターンと一致するアクティビティを示します。 | 調査脅威インテリジェンス |
| 不審なサインイン | オフライン | このリスク検出は、このサービス プリンシパルにとって通常とは異なるサインイン プロパティまたはパターンを示します。 この検出は、テナント内のワークロード ID のベースライン サインイン動作を学習します。 この検出には、2 日から 60 日かかり、その後のサインイン時に 1 つ以上の見慣れないプロパティIP アドレス/ASN、ターゲットリソース、ユーザーエージェント、ホスティング/非ホスティング IP の変更、IP カントリー、資格証明の種類が現れた場合に警告を発します。 プログラムの性質上、ワークロードID サインインについては、特定のサインイン イベントにフラグを設定するのではなく、疑わしいアクティビティの timestamp を提供します。 承認された構成の変更後に開始されるサインインによって、この検出がトリガーされる場合があります。 | 不審なサインイン |
| サービス プリンシパルがセキュリティ侵害されていることを管理者が確認しました | オフライン | この検出は、管理者が "リスクのあるワークロード ID" UI で、または riskyServicePrincipals API を使用して、[セキュリティ侵害の確認] を選択したことを示します。 このアカウントに対するセキュリティが侵害されたことを確認した管理者を調べるには、アカウントのリスク履歴を (UI または API 経由で) 確認します。 | 管理者確認済サービスプリンシパル侵害 |
| 漏洩した資格情報 | オフライン | このリスク検出は、アカウントの有効な資格情報が漏洩したことを示します。 この漏洩は、他のユーザーが GitHub のパブリック コード アーティファクトで資格情報にチェックインしたとき、またはデータ侵害によって資格情報が漏洩したときに発生する可能性があります。 Microsoft の漏洩した資格情報サービスで、GitHub、ダーク ウェブ、ペースト サイト、またはその他のソースから資格情報が取得された場合は、有効な一致を見つけるために Microsoft Entra ID の現在の有効な資格情報に対してチェックされます。 | 漏洩した認証情報 |
| 悪意のあるアプリケーション | オフライン | この検出では、ID Protection のアラートとMicrosoft Defender for Cloud Appsを組み合わせて、Microsoft がサービス使用条件に違反したアプリケーションを無効にしたタイミングを示します。 アプリケーション [の調査を行](https://go.microsoft.com/fwlink/?linkid=2208429) うことをお勧めします。 注: これらのアプリケーションは、Microsoft Graph の関連するアプリケーションおよびサービス プリンシパル リソースの種類のプロパティに表示されます。 将来的に組織で再度インスタンス化されないようにするために、これらのオブジェクトを削除することはできません。 | 悪意のあるアプリケーション |
| 疑わしいアプリケーション | オフライン | この検出は、ID Protection や Microsoft Defender for Cloud Apps のサービス使用条件に違反している可能性のあるアプリケーションを Microsoft が特定したが、無効にしていないことを示します。 アプリケーション [の調査を行](https://go.microsoft.com/fwlink/?linkid=2208429) うことをお勧めします。 | 疑わしいアプリケーション |
| サービス プリンシパルの異常なアクティビティ | オフライン | このリスク検出では、Microsoft Entra ID での通常の管理サービス プリンシパルの動作がベースライン化され、ディレクトリに対する疑わしい変更などの異常な動作パターンが検出されます。 検出は、変更を行った管理サービス プリンシパルまたは変更されたオブジェクトに対してトリガーされます。 | 異常なサービスプリンシパル活動 |
| 疑わしい API トラフィック | オフライン | このリスク検出は、異常な Graph API トラフィックまたはサービス プリンシパルのディレクトリ列挙が観察されたときに報告されます。 不審な API トラフィック検出は、サービス プリンシパルによる異常な偵察またはデータ流出を示している可能性があります。 | 不審なAPIトラフィック |

### リスクの高いワークロード ID を特定する

組織は、次の 2 つのいずれかの場所で、リスクのフラグが立てられたワークロード ID を見つけられます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインします。
2. **ID 保護**&gt;**リスクのあるワークロード ID** に移動します。

[Image: レポート内のワークロード ID に対して検出されたリスクを示すスクリーンショット。]

#### Microsoft Graph API

[Microsoft Graph API を使用して](https://learn.microsoft.com/ja-jp/graph/use-the-api)、危険なワークロード ID のクエリを実行することもできます。 [ID Protection API](https://learn.microsoft.com/ja-jp/graph/api/resources/identityprotection-overview) には 2 つの新しいコレクションがあります。

- `riskyServicePrincipals`
- `servicePrincipalRiskDetections`

#### リスク データのエクスポート

組織は [、Microsoft Entra ID で診断設定](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-export-risk-data) を構成して、リスク データを Log Analytics ワークスペースに送信したり、ストレージ アカウントにアーカイブしたり、イベント ハブにストリーミングしたり、SIEM ソリューションに送信したりすることで、データをエクスポートできます。

### リスクベースの条件付きアクセスを使用してアクセス制御を適用する

[ワークロード ID に条件付きアクセスを](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity)使用すると、ID 保護によって "危険な状態" としてマークされたときに選択した特定のアカウントのアクセスをブロックできます。ポリシーは、テナントに登録されているシングルテナント サービス プリンシパルに適用できます。 Microsoft 以外の SaaS、マルチテナント アプリ、マネージド ID は範囲外です。

ワークロード ID のセキュリティと回復性を向上させるうえで、ワークロード ID の継続的アクセス評価 (CAE) は強力なツールであり、条件付きアクセス ポリシーと検出されたリスク シグナルを即座に適用できます。 CAE 対応のファースト パーティ リソースにアクセスする CAE 対応の Microsoft 以外のワークロード ID には、継続的なセキュリティ チェックの対象となる 24 時間の Long Lived Token (LLT) が備わっています。 CAE のワークロード ID クライアントと現在の機能スコープの構成の詳細については、 [ワークロード ID の CAE に関するドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation-workload)。

### リスクの高いワークロード識別子の調査

ID Protection を使用すると、ワークロード IDのリスクを調査するために使用できる 2 種類のレポートが組織に提供されます。 これらのレポートは、リスクの高いワークロード ID と、ワークロード ID のリスク検出です。 すべてのレポートは、詳細な分析を行うために、イベントを .CSV 形式でダウンロードできます。

調査の際の重要な質問には以下のようなものがあります。

- アカウントに疑わしいサインインの動きはありますか?
- 資格情報に未承認の変更がありましたか?
- アカウントに対する疑わしい構成変更はありましたか?
- アカウントは不正なアプリケーション ロールを取得しましたか?

[アプリケーションの Microsoft Entra セキュリティ運用ガイドには、](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-applications)調査領域に関する詳細なガイダンスが記載されています。

ワークロード ID が侵害されたかどうかを判断したら、アカウントのリスクを無視するか、リスクの高いワークロード ID レポートで侵害されたアカウントを確認する必要があります。 以後そのアカウントでサインインされないようにする場合は、"サービス プリンシパルを無効にする" を選択することもできます。

[Image: ワークロード ID の侵害を確認するか、リスクを無視します。]

### リスクの高いワークロード ID の修復

1. サービス プリンシパルとアプリケーション オブジェクトのどちらに対しても、リスクの高いワークロード ID に割り当てられている資格情報をインベントリします。
2. 新しい資格情報を追加します。 Microsoft では、x509 証明書の使用をお勧めします。
3. 侵害された資格情報を削除します。 アカウントが危険にさらされている可能性がある場合は、既存のすべての資格情報を削除することをお勧めします。
4. サービス プリンシパルがアクセスしている Azure KeyVault シークレットをローテーションして修復します。

[Microsoft Entra Toolkit](https://github.com/microsoft/AzureADToolkit) は、これらのアクションの一部を実行するのに役立つ PowerShell モジュールです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/how-to-deploy-identity-protection"} -->
## Microsoft Entra ID 保護の展開の計画 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/how-to-deploy-identity-protection
- Service: entra-id-protection
- Article date: 2025-08-06
- Summary: Microsoft Entra ID 保護をデプロイするプランを作成します。

Microsoft Entra ID 保護は、ID ベースのリスクを検出して報告し、管理者がこれらのリスクを調査して修復して、組織の安全とセキュリティを維持できるようにします。 リスク データは、さらに条件付きアクセスなどのツールに送って、アクセスに関する決定を行ったり、セキュリティ情報イベント管理 (SIEM) ツールに送って、詳細な解析や調査を行ったりすることができます。

この展開計画は、[条件付きアクセスの展開計画](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)で導入された概念を拡張します。

### 前提条件

- Microsoft Entra ID P2 か試用版のライセンスが有効になっている稼働中の Microsoft Entra テナント。 必要に応じて、[無料で 1 つ作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
    - 一部のリスク検出には、Microsoft 365 E5 または Microsoft Enterprise Mobility + Security E5 ライセンスが必要です。 詳細については、[Microsoft Entra ID Protection の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#microsoft-defender)に関する記事を参照してください。
- ID 保護と対話する管理者は、実行しているタスクに応じて次の役割の割り当てを 1 つ以上持っている必要があります。 [最小特権のゼロ トラスト原則](https://learn.microsoft.com/ja-jp/security/zero-trust/)に従うには、[Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)を使用し、Just-In-Time で特権ロールの割り当てをアクティブ化することを検討してください。
    - 「ID 保護と条件付きアクセスのポリシー」と構成を参照してください
        - [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)
        - [グローバルリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)
    - ID 保護の管理
        - [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)
        - [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)
    - 条件付きアクセス ポリシーを作成または変更する
        - [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)
        - [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)
- 実際のユーザーにデプロイする前に、ポリシーが想定どおりに機能することを確認する、管理者以外のテスト ユーザー。 ユーザーを作成する必要がある場合、「[クイックスタート: Microsoft Entra ID に新しいユーザーの追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)」を参照してください。
- ユーザーが属するグループ。 グループを作成する必要がある場合、「[Microsoft Entra ID でグループを作成してメンバーの追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)」を参照してください。

#### 適切な関係者を関わらせる

テクノロジ プロジェクトが失敗した場合、通常、その原因は影響、結果、責任に対する想定の不一致です。 これらの落とし穴を回避するには、適切な利害関係者を関与させ、利害関係者およびそのプロジェクトでの入力と説明責任を文書化することで、プロジェクトでの利害関係者の役割をよく理解させます。

#### 変更の連絡

コミュニケーションは、新しい機能の成功に必要不可欠です。 ユーザー [エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience)がどのように変わるのか、いつ変わるのか、および問題が発生したときにサポートを受ける方法について、ユーザーに事前に連絡する必要があります。

### 手順 1: 既存のレポートの確認

リスクベースの条件付きアクセス ポリシーを展開する前に、[ID 保護レポート](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)を確認することが重要です。 このレビューは、既存の疑わしい動作を調査する機会をもたらします。 リスクでないと判断した場合は、そのリスクを無視するか、これらのユーザーが安全であることを確認することができます。

- [リスク検出の調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)
- [リスクを修復してユーザーをブロック解除する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)
- [Microsoft Graph PowerShell を使用して一括変更を行う](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-graph-api)

効率を高めるために、手順 3 で説明されているポリシーを使用してユーザーが自己修復できるようにすることをお勧めします。

### 手順 2: 条件付きアクセス リスク ポリシーの計画

ID 保護によりリスク シグナルが条件付きアクセスに送信され、決定が下されて組織のポリシーが適用されます。 このポリシーでは、ユーザーが多要素認証またはセキュリティで保護されたパスワード変更を実行する必要があったりします。 組織がポリシーを作成する前に計画する必要のある項目がいくつかあります。

#### ポリシーの除外

条件付きアクセス ポリシーは強力なツールです。 ポリシーから次のアカウントを除外することをお勧めします。

- ポリシー構成の誤りによるロックアウトを防ぐための**緊急アクセス**または**ブレークグラス**アカウント。 すべての管理者がロックアウトされる可能性が低いシナリオでは、緊急アクセス管理者アカウントを使用してサインインし、アクセスを回復できます。
    - 詳細は、「[Microsoft Entra ID で緊急アクセス用アカウントの管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)」の記事を参照してください。
- **サービス アカウント**と**サービス プリンシパル**(Microsoft Entra Connect 同期アカウントなど)。 サービス アカウントは、特定のユーザーに関連付けられていない非対話型アカウントです。 通常、アプリケーションへのプログラムによるアクセスを許可するためにバックエンド サービスによって使用されますが、管理目的でシステムにサインインするためにも使用されます。 サービス プリンシパルによって行われた呼び出しは、ユーザーを対象とする条件付きアクセス ポリシーによってブロックされません。 ワークロード ID の条件付きアクセスを使用して、サービス プリンシパルを対象とするポリシーを定義します。
    - 組織がスクリプトまたはコードでこれらのアカウントを使用している場合は、 [それらをマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) に置き換えます。

#### 多要素認証

ただし、ユーザーがリスクを自己修復するには、リスクが生じる前に Microsoft Entra 多要素認証に登録する必要があります。 詳細については、「[Microsoft Entra 多要素認証の展開を計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)」の記事をご覧ください。

#### 認識されたネットワークの場所

[条件付きアクセスにネームド ロケーション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network#how-are-these-locations-defined)を構成し、[Defender for Cloud アプリ](https://learn.microsoft.com/ja-jp/defender-cloud-apps/ip-tags#create-an-ip-address-range)に VPN 範囲を追加することが重要です。 信頼済みまたは既知としてマークされたネームド ロケーションからのサインインにより、Microsoft Entra ID 保護のリスク計算の精度が向上します。 信頼済みまたは既知としてマークされた場所から認証を受けると、これらのログインはユーザーのリスクを低減します。 これにより、環境内での検出の一部における誤検知が減少します。

#### レポート専用モード

[レポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)は、条件付きアクセス ポリシーの状態です。このモードを使うと、管理者が環境で条件付きアクセス ポリシーを適用する前に、その影響を評価することができます。

### 手順 3: ポリシーの構成

#### ID 保護 MFA 登録ポリシー

ID 保護の多要素認証登録ポリシーを使用すると、ユーザーが Microsoft Entra 多要素認証の使用が必要になる前にその認証に登録されるようにすることができます。 「[Microsoft Entra 多要素認証登録ポリシーを構成する方法](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy)」の記事の手順に従い、このポリシーを有効にします。

#### 条件付きアクセス ポリシー

**ログイン リスク** - ほとんどのユーザーは、追跡できる正常な行動をします。この規範から逸脱た場合、そのユーザーのログインを許可するだけでも危険である可能性があります。 そのユーザーをブロックしたり、多要素認証を実行してユーザーが本人であることを証明するように求めたりすることができます。 まず、これらのポリシーをユーザーのサブセットにスコープします。

**ユーザー リスク** - Microsoft は、研究者、警察、Microsoft のさまざまなセキュリティ チーム、その他の信頼されたソースと協力し、漏洩したユーザー名とパスワードのペアを調査しています。 これらの脆弱なユーザーが検出された場合は、多要素認証を実行してからパスワードをリセットすることをユーザーに要求することをお勧めします。

記事「[リスク ポリシーの構成と有効化](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)」では、これらのリスクに対処する条件付きアクセス ポリシーを作成するためのガイダンスを提供します。

### 手順 4: 監視と継続的な運用ニーズ

#### メール通知

リスクにさらされているというフラグがユーザーに設定されたときに対応できるように、[通知を有効にします](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-notifications)。 この通知により、すぐに調査を開始できます。 週刊ダイジェスト メールを設定して、その週のリスクの概要を確認することもできます。

#### 監視と調査

[リスクベースのアクセス ポリシーの影響分析ブック](https://learn.microsoft.com/ja-jp/entra/id-protection/workbook-risk-based-policy-impact)は、管理者がリスクベースの条件付きアクセス ポリシーを作成する前にユーザーへの影響を把握するのに役立ちます。

[ID 保護ワークブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-risk-analysis)は、テナント内のパターンを監視および探すのに役立ちます。 ブックで動向と条件付きアクセスの「レポート専用モード」の結果を監視し、変更が必要かどうかを確認します（たとえば、指定された場所の追加など）。

Microsoft Defender for Cloud Apps では、組織が出発点として使用できる調査フレームワークが提供されます。 詳細については、「[異常検出アラートを調査する方法](https://learn.microsoft.com/ja-jp/defender-cloud-apps/investigate-anomaly-alerts)」の記事を参照してください。

ID 保護 API を使用して他のツールに[リスク情報をエクスポート](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-export-risk-data)することにより、セキュリティ チームがリスク イベントを監視してアラートを出せるようします。

テスト中、[いくつかの脅威をシミュレーション](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-simulate-risk)して調査プロセスをテストすることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-export-risk-data"} -->
## Microsoft Entra ID 保護データのエクスポートと使用 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-export-risk-data
- Service: entra-id-protection
- Article date: 2025-09-30
- Summary: Microsoft Entra ID 保護からリスク データをエクスポートするためのさまざまな長期データ保存および監視オプションについて説明します。

Microsoft Entra ID は、定義された期間にわたるレポートとセキュリティ信号を格納します。 リスク情報に関しては、その期間が十分ではない場合があります。

| レポート/信号 | Microsoft Entra ID Free（無料） | Microsoft Entra ID P1 | Microsoft Entra ID P2 |
| --- | --- | --- | --- |
| 監査ログ | 7 日 | 30 日 | 30 日 |
| サインイン | 7 日 | 30 日 | 30 日 |
| Microsoft Entra 多要素認証の使用 | 30 日 | 30 日 | 30 日 |
| リスクの高いサインイン | 7 日 | 30 日 | 30 日 |

この記事では、長期保存と分析のために Microsoft Entra ID 保護からリスク データをエクスポートする方法について説明します。

### 前提条件

リスク データを保存および分析用にエクスポートするには、次のものが必要です。

- Log Analytics ワークスペース、Azure イベント ハブ、または Azure ストレージ アカウントを作成するための Azure サブスクリプション。 Azure サブスクリプションを持っていない場合は、[無料試用版にサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロールは、**Microsoft Entra テナントの診断設定を構成**するために必要な最小限の特権ロールです。

### 診断設定

組織は、Microsoft Entra IDの診断設定を構成することで、**RiskyUsers**、**UserRiskEvents**、**RiskyServicePrincipals**、**ServicePrincipalRiskEvents**、**RiskyAgents**、および**AgentRiskEvents**データを保存またはエクスポートすることを選択できます。 Log Analytics ワークスペースとデータの統合、ストレージ アカウントへのデータのアーカイブ、イベント ハブへのデータのストリーミング、またはパートナー ソリューションへのデータの送信を実行できます。

診断設定を構成するには、その前にログのエクスポートのために選択したエンドポイントを設定する必要があります。 ログの保存と分析に使用できるメソッドの概要については、「[Microsoft Entra ID でアクティビティ ログにアクセスする方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)」を参照してください。

1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**監視と健康**&gt;**診断設定**に移動します。
3. **[+ 診断設定の追加]** を選択します。
4. **[診断設定の名前]** を入力し、ストリーミングするログ カテゴリを選択し、以前に構成した保存先を選択して、**[保存]** を選択します。

[Image: Microsoft Entra ID の診断設定画面のスクリーンショット。]

選択した保存先にデータが表示されるまで、約15分ほど待つ必要がある場合があります。 詳細については、「[Microsoft Entra 診断設定を構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)」を参照してください。

### Log Analytics

リスク データを Log Analytics と統合することで、強力なデータ分析と可視化機能が提供されます。 Log Analytics を使用してリスク データを分析するための大まかなプロセスは次のとおりです。

1. [Log Analytics ワークスペースを作成します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace)。
2. [Microsoft Entra 診断設定を構成し、データをエクスポートします](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)。
3. [Log Analytics でデータのクエリを実行します](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/get-started-queries)。

データをエクスポートしてクエリを実行するには、その前に Log Analytics ワークスペースを構成する必要があります。 Log Analytics ワークスペースを構成し、診断設定を使用してデータをエクスポートしたら、 [Microsoft Entra 管理センター](https://entra.microsoft.com)&gt;**Entra ID**&gt;**Monitoring & health**&gt;**Log Analytics** に移動します。 その後、Log Analytics を使用して、組み込みまたはカスタムの Kusto クエリを使用してデータのクエリを実行できます。

Important

**診断設定**で選択した名前は、Kusto (KQL) クエリで使用する**テーブル名**と同じではありません。

- **診断設定カテゴリ** = エクスポートに対して有効にする項目
- **Log Analyticsテーブル名** = クエリ対象 (通常は `AAD` で始まる)

#### 診断設定カテゴリと Log Analytics テーブル名

エクスポートを有効にし、クエリを記述するときに、次のマッピングを使用します。

| レポート/シグナル | 診断設定カテゴリ (エクスポートを有効にする) | Log Analytics テーブル名 (クエリで使用) | テーブル参照 |
| --- | --- | --- | --- |
| 危険なユーザー | `RiskyUsers` | `AADRiskyUsers` | [AADRiskyUsers](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/aadriskyusers) |
| リスク検出 (ユーザー) | `UserRiskEvents` | `AADUserRiskEvents` | [AADUserRiskEvents](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/aaduserriskevents) |
| 危険なワークロード ID | `RiskyServicePrincipals` | `AADRiskyServicePrincipals` | [AADRiskyServicePrincipals](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/aadriskyserviceprincipals) |
| ワークロード ID の検出 | `ServicePrincipalRiskEvents` | `AADServicePrincipalRiskEvents` | [AADServicePrincipalRiskEvents](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/aadserviceprincipalriskevents) |
| 危険なエージェント | `RiskyAgents` | `AADRiskyAgents` | [AADRiskyAgents](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/aadriskyagents) |
| エージェント ID の検出 | `AgentRiskEvents` | `AADAgentRiskEvents` | [AADAgentRiskEvents](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/aadagentriskevents) |

メモ

Log Analytics では、ストリーミング中のデータのみが可視化されます。 Microsoft Entra ID からのイベントの送信を有効にする前のイベントは表示されません。

#### サンプル クエリ

[Image: 上位 5 つのイベントに対する AADUserRiskEvents クエリを示す Log Analytics ビューのスクリーンショット。]

前の画像では、トリガーされた最新の 5 つのリスク検出を示すため、次のクエリが実行されました。

```kusto
AADUserRiskEvents
| take 5
```

もう 1 つのオプションは、AADRiskyUsers テーブルに対してクエリを実行して、危険なユーザーをすべて表示する方法です。

```kusto
AADRiskyUsers
```

リスクの高いユーザーの数を日別に表示します。

```kusto
AADUserRiskEvents
| where TimeGenerated > ago(30d)
| where RiskLevel has "high"
| summarize count() by bin (TimeGenerated, 1d)
```

未修復または未却下の高リスク検出に対して、ユーザー エージェント文字列などの調査に役立つ詳細情報を確認できます。

```kusto
AADUserRiskEvents
| where RiskLevel has "high"
| where RiskState has "atRisk"
| mv-expand ParsedFields = parse_json(AdditionalInfo)
| where ParsedFields has "userAgent"
| extend UserAgent = ParsedFields.Value
| project TimeGenerated, UserDisplayName, Activity, RiskLevel, RiskState, RiskEventType, UserAgent,RequestId
```

AADUserRiskEvents ログと AADRisky Users ログに基づいて、[リスク ベースのアクセス ポリシーの影響分析のブック](https://learn.microsoft.com/ja-jp/entra/id-protection/workbook-risk-based-policy-impact)の、より多くのクエリと視覚的な分析情報にアクセスできます。

#### リスク分析

組織は、セキュリティ オペレーション センター (SOC) のワークロードを削減し、リスクベースの条件付きアクセス ポリシーを使用してオーバーヘッドをサポートできます。 詳細については、次のビデオ「 **Microsoft Entra ID 保護 を使用したリスク分析のマスタリング」を参照してください**。

### ストレージ アカウント

ログを Azure ストレージ アカウントにルーティングすることで、既定の保持期間よりも長くデータを保持できます。

1. [Azure Storage アカウント](https://learn.microsoft.com/ja-jp/azure/storage/common/storage-account-create)を作成します。
2. [Microsoft Entra ログをストレージ アカウントにアーカイブします](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-archive-logs-to-storage-account)。

### Azure Event Hubs

Azure Event Hubs は Microsoft Entra ID 保護のようなソースからの受信データを参照し、リアルタイムの分析と相関関係を提供できます。

1. [Azure イベント ハブを作成します](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-create)。
2. [Microsoft Entra ログをイベント ハブにストリーム配信します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)。

### Microsoft Sentinel

組織は、セキュリティ情報イベント管理 (SIEM) とセキュリティ オーケストレーション、自動化、対応 (SOAR) のために、[Microsoft Sentinel に Microsoft Entra データを接続](https://learn.microsoft.com/ja-jp/azure/sentinel/data-connectors/azure-active-directory-identity-protection)することもできます。

1. [Log Analytics ワークスペースを作成します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace)。
2. [Microsoft Entra 診断設定を構成し、データをエクスポートします](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)。
3. [Microsoft Sentinel にデータ ソースを接続します](https://learn.microsoft.com/ja-jp/azure/sentinel/configure-data-connector)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-identity-protection-configure-mfa-policy"} -->
## MFA 登録ポリシーを構成する - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy
- Service: entra-id-protection
- Article date: 2025-08-06
- Summary: Microsoft Entra ID Protection 多要素認証登録ポリシーを構成する方法について説明します。

Microsoft は、サインイン先の先進認証アプリに関係なく、MFA 登録を要求するように Microsoft Entra ID Protection ポリシーを構成することで、多要素認証 (MFA) の展開を管理するのに役立ちます。 多要素認証は、ユーザー名とパスワードに加えて、その他の要素を使用することでユーザーを確認する手段を提供します。 ユーザーのサインインに第 2 のセキュリティ層を提供します。ユーザーが MFA プロンプトに応答できるようにするには、まず、Microsoft Authenticator アプリなどの認証方法を登録する必要があります。

ユーザーのサインインに対して多要素認証を要求することをお勧めします。[Microsoft の調査](https://www.microsoft.com/security/security-insider/microsoft-digital-defense-report-2023)によると、MFA を使用した場合、アカウントが侵害されるリスクは 99% 以上低下します。 常に MFA を必要としない場合でも、このポリシーにより、MFA が必要なときにユーザーの準備が整います。

詳細については、「[一般的な条件付きアクセス ポリシー: すべてのユーザーに対して MFA を必須にする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)」の記事を参照してください。

### [前提条件]

- MFA 登録ポリシーの変更など、Microsoft Entra ID Protection 機能へのフル アクセスには、Microsoft Entra ID P2 または Microsoft Entra Suite ライセンスが必要です。
    - 各ライセンスレベルの機能の詳細な一覧については、「 [Microsoft Entra ID Protection とは」を](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)参照してください。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロールは、**リスクベースのポリシーを作成または編集**するために必要な最小限の特権ロールです。

### ポリシーの構成

1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **ID Protection**&gt;**Dashboard**&gt;**Multifactor 認証登録ポリシー**を参照します。
    1. **[割り当て]**&gt;**[ユーザー]**。
        1. **[含める]** の下で **[すべてのユーザー]** またはロールアウトを制限する場合は **[個人とグループの選択]** を選択します。
        2. **[除外]** で、 **[ユーザーとグループ]** を選択し、組織の緊急アクセス用または非常用アカウントを選択します。
3. **[ポリシーの適用]** を **[有効]** に設定します。
4. **[保存]** を選択します。

### ユーザー エクスペリエンス

Microsoft Entra ID Protection は、次回対話形式でサインインする際に登録するようユーザーに求め、登録を完了するまでに 14 日間かかります。 この 14 日間は、条件として MFA が必要ない場合は登録をバイパスできますが、期間の終了時に、サインイン プロセスを完了する前に登録する必要があります。

関連するユーザー エクスペリエンスの概要については、次を参照してください。

- [Microsoft Entra ID Protection のサインイン エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-identity-protection-configure-notifications"} -->
## Microsoft Entra ID 保護通知を構成する - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-notifications
- Service: entra-id-protection
- Article date: 2026-05-27
- Summary: 調査アクティビティをサポートするために、リスクの高いユーザーのアラートや毎週のダイジェスト メールなど、Microsoft Entra ID 保護通知を構成する方法について説明します。

Microsoft Entra ID 保護 では、ユーザー リスクとリスク検出の管理に役立つ以下の 2 種類の自動通知メールを送信します。

- 危険なユーザーが検出されました
- 週間ダイジェスト

### [前提条件]

- MFA 登録ポリシーの変更など、Microsoft Entra ID 保護 機能へのフル アクセスには、Microsoft Entra ID P2 または Microsoft Entra スイート ライセンスが必要です。
    - 各ライセンスレベルの機能の詳細な一覧については、「 [Microsoft Entra ID 保護 とは」を](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)参照してください。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロールは、**アラートを構成**するために必要な最小限の特権ロールです。

### 通知メールを受信するユーザー

既定では、グローバル管理者、セキュリティ管理者、またはセキュリティ閲覧者ロールがアクティブに割り当てられているユーザーは、通知受信者リストに自動的に追加されます。 これらのユーザーには、有効な **電子メール** または **連絡用メールが** 構成されている必要があります。

ユーザーがオンデマンドでこれらのロールの 1 つに昇格するために Privileged Identity Management (PIM) に登録されている場合、ユーザーは電子メールの送信時に昇格された場合にのみ電子メールを受信します。

注

グループ割り当てロールのユーザーへの電子メールの送信はサポートされていません。

### リスクのあるユーザーが検出されたメール

危険にさらされているアカウントが検出された場合、Microsoft Entra ID 保護は、**リスクが検出されたユーザー**を件名として含む電子メール アラートを生成します。 メールには、**[リスクのフラグが設定されたユーザー](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)** レポートへのリンクが含まれます。 ベスト プラクティスとして、影響を受けるアカウントをすぐに調査して保護します。

このアラートを構成すると、アラートを生成するユーザー リスク レベルを指定できます。 ユーザーのリスク レベルが、指定されたものに達するとメールが生成されます。

#### サンプル シナリオ

中程度のユーザー リスクに対してアラートを生成するようにポリシーを設定します。 ユーザーのリスク スコアは、リアルタイムのサインイン リスクのために中程度のリスクに移行し、リスクが検出されたユーザーのメールを受け取ります。

このユーザーに対して後続のリスク検出が行われ、それによってユーザー リスク レベルの計算が、指定されたリスク レベル (またはそれ以上) になった場合は、ユーザー リスク スコアが再計算されたときに、リスクのあるユーザーが検出されたという追加のメールを受け取ります。 たとえば、ユーザーが 1 月 1 日に中リスクに移行した場合、設定が中リスクに関するアラートに設定されている場合、電子メール通知を受け取ります。 同じユーザーに 1 月 5 日に別のリスク検出があり、ユーザー リスク スコアが再計算されても引き続き中レベルである場合は、別のメール通知を受け取ります。

#### 電子メール通知のタイミングに関する考慮事項

追加の電子メール通知は、ユーザー リスク レベルの変更が最後に送信されたメールよりも新しい場合にのみ送信されます。 たとえば、ユーザーは 1 月 1 日午前 5 時にサインインし、リアルタイムのリスクはありません (つまり、そのサインインのために電子メールが生成されません)。 10 分後の午前 5 時 10 分に、同じユーザーが再びサインインし、リアルタイム リスクが高くなり、ユーザー リスク レベルが高くなり、メールがトリガーされます。 その後、午前 5:15 に、午前 5 時に行われた元のサインインのオフライン リスク スコアが、オフライン リスク処理により高リスクに変わります。 最初のサインインの時刻は、既に電子メール通知をトリガーした 2 番目のサインインの前であったため、リスクのフラグが設定された別のユーザーは送信されません。

メールの過負荷を防ぐには、5 秒以内に受信するメールは 1 つだけです。 同じ 5 秒間に複数のユーザーが指定されたリスク レベルに移行した場合、データは集計され、すべてのユーザーに対して 1 つの電子メールで送信されます。

#### ユーザー のリスクと修復

「[Microsoft Entra ID 保護 のユーザー エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience)」の記事で説明されているように、組織で自己修復を有効にしている場合は、調査を行う前に、ユーザーが自分のリスクを修復できる可能性があります。 既に修復されている危険なユーザーや危険なサインインを確認するには、**危険なユーザー**または**危険なサインイン**のいずれかのレポートの **[リスクの状態]** フィルターに "**修復済み**" を追加します。

危険なサインインの通知を受け取っても、Microsoft Entra 管理センターに結果が表示されない場合は、サインインが自動的に修復されている可能性があります。 これらのイベントを表示するには、**ID Protection**&gt;**Risky サインイン**に移動し、[**修復済み**] を含むように **[リスク状態**] フィルターを設定します。 通知メールには、リスクが後で自動的に解決された場合でも、発生した時点での検出が含まれます。 その結果、明示的にフィルター処理されない限り、修復されたサインインがレポートに表示されない場合があります。

#### 検出された危険なユーザーに関するアラートを設定する

これらのアラートのユーザー リスク レベルと受信者を構成するには、次の手順に従います。

管理者として、次の内容を設定できます。

- **このメールの生成をトリガーするユーザー リスク レベル** - 既定では、リスク レベルは「高」リスクに設定されます。
- **このメールの受信者**
    - 必要に応じて、**[ここでカスタム メールを追加する]** ことができます。定義されているユーザーがリンク レポートを表示するには適切なアクセス許可が必要です。

[Microsoft Entra 管理センター](https://entra.microsoft.com)の**ID Protection**&gt;**Dashboard**&gt;**Users at risk detected alerts**のセクションで、危険にさらされているユーザーのメール設定を構成します。

### 週間ダイジェスト電子メール

週間ダイジェスト電子メールには、新しいリスク検出の概要が含まれます。 次の情報が含まれます。

- 新しい危険なユーザーが検出されました
- 新たに検出された危険なサインイン (リアルタイムで)
- ID 保護で関連するレポートへのリンク

[Image: 新しい危険なユーザーと危険なサインインの概要を示すMicrosoft Entra ID 保護からのサンプル週次ダイジェスト 電子メールのスクリーンショット.]

#### 週刊ダイジェストの電子メールを構成する

管理者は、週刊ダイジェストの電子メールの送信を有効にしたり無効にしたりできるほか、メールの受信者として割り当てるユーザーを選択することができます。

[Microsoft Entra 管理センター](https://entra.microsoft.com)で週単位のダイジェスト メール&gt;**ID Protection**&gt;**Dashboard**&gt;**Weekly ダイジェストを**構成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-identity-protection-configure-risk-policies"} -->
## リスク ポリシー - Microsoft Entra ID 保護 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies
- Service: entra-id-protection
- Article date: 2025-10-30
- Summary: Microsoft Entra ID 保護 でリスク ポリシーを有効にし、構成します。

Microsoft Entra 条件付きアクセスには、2 つの種類の設定可能な[リスク ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies)があります。 これらのポリシーを使用すると、リスクへの対応を自動化し、リスクが検出されたときにユーザーが自己修復を行えるようにできます。

- ユーザー リスクのポリシー
- サインイン リスク ポリシー

警告

サインイン リスクとユーザー リスク条件を同じ条件付きアクセス ポリシーに組み合わせないでください。 リスク条件ごとに個別のポリシーを作成します。

[Image: 条件としてリスクを示す条件付きアクセス ポリシーのスクリーンショット。]

### [前提条件]

- Microsoft Entra ID 保護 機能へのフル アクセスには、Microsoft Entra ID P2 または Microsoft Entra スイート ライセンスが必要です。
    - 各ライセンスレベルの機能の詳細な一覧については、「 [Microsoft Entra ID 保護 とは」を](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)参照してください。
- [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)ロールは、**条件付きアクセス ポリシーを作成または編集**するために必要な最小限の特権ロールです。

### 許容されるリスク レベルの選択

組織は、セキュリティ体制とユーザーの生産性のバランスを取りながら、アクセス制御を必要とするリスクのレベルを決定する必要があります。

リスク レベル**高**でアクセスの制御を適用することを選択すると、ポリシーがトリガーされる回数が減り、ユーザーにとっての手間が最小限になります。 しかし、これは**低**と**中**のリスクをポリシーから排除するため、攻撃者が侵害された ID を悪用するのを防げない可能性があります。 通常、[ **中** ] レベルまたは [ **低** リスク] レベルを選択すると、ユーザー割り込みが増えます。

構成済の信頼された[ネットワークの場所](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network#trusted-locations)は、Microsoft Entra ID 保護によって一部のリスク検出に使用され、偽陽性を低減します。

#### リスク軽減

組織は、リスクが検出された場合にアクセスをブロックすることを選択できます。 ブロックすると、正当なユーザーが必要な操作を実行できなくなる場合があります。 より適切な解決策は、ユーザーが自己修復できるようにする、ユーザーおよび[サインイン](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock#end-user-self-remediation)のリスクベースの条件付きアクセス ポリシーを構成することです。

警告

ユーザーは、修復が必要な状況に直面する前に Microsoft Entra 多要素認証に登録する必要があります。 オンプレミスから同期されるハイブリッド ユーザーの場合は、パスワード ライトバックが有効になっている必要があります。 登録されていないユーザーはブロックされ、管理者の介入が必要になります。

リスクの高いユーザー ポリシー修復フロー以外のパスワードの変更 (パスワードがわかっており、新しいものに変更する必要があります) は、セキュリティで保護されたパスワード変更の要件を満たしていません。

#### Microsoft の推奨事項

Microsoft は、以下のリスク ポリシー構成で組織を保護することを推奨しています。

#### ユーザー リスク ポリシー

組織は、ユーザーのリスク レベルが**高い**場合に **[リスクの修復を要求する**] を選択する必要があります。 パスワードレス ユーザーの場合、Microsoft Entra はユーザーのセッションを取り消して、再認証する必要があります。 パスワードを持つユーザーの場合、Microsoft Entra 多要素認証が成功した後、セキュリティで保護されたパスワードの変更を完了するように求められます。

[ **リスクの修復が必要]** を選択すると、次の 2 つの設定が自動的に適用されます。

- **認証強度を要求する**は、承認制御として自動的に選択されます。
- **サインインの頻度 - 毎回** 、セッション コントロールとして自動的に適用されます。

#### サインインのリスク ポリシー

サインイン リスク レベルが **中** または **高**の場合は、Microsoft Entra 多要素認証が必要です。 この構成により、ユーザーは、登録済みの認証方法のいずれかを使用して、自分自身であることを証明し、サインイン リスクを軽減できます。

また、危険なサインインの再認証を要求するために、 [サインイン頻度セッション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime#require-reauthentication-every-time) を含めることもできます。通常、多要素認証またはパスワードレス認証を使用して成功した "強力な認証" は、リスク レベルに関係なく、サインイン リスクを自己修復する唯一の方法です。

### ポリシーを有効にする

組織は、以下の手順を使用して条件付きアクセスにリスクベース ポリシーをデプロイすることを選択できます。または、[条件付きアクセス テンプレート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)を使用できます。

組織は、これらのポリシーを有効にする前に、すべてのアクティブなリスクを[調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)して[修復](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)する手順を踏む必要があります。

#### ポリシーの排除事項

条件付きアクセス ポリシーは強力なツールです。 ポリシーから次のアカウントを除外することをお勧めします。

- ポリシー構成の誤りによるロックアウトを防ぐための**緊急アクセス**または**ブレークグラス**アカウント。 すべての管理者がロックアウトされる可能性が低いシナリオでは、緊急アクセス管理者アカウントを使用してサインインし、アクセスを回復できます。
    - 詳細は、「[Microsoft Entra ID で緊急アクセス用アカウントの管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)」の記事を参照してください。
- **サービス アカウント**と**サービス プリンシパル**(Microsoft Entra Connect 同期アカウントなど)。 サービス アカウントは、特定のユーザーに関連付けられていない非対話型アカウントです。 通常、アプリケーションへのプログラムによるアクセスを許可するためにバックエンド サービスによって使用されますが、管理目的でシステムにサインインするためにも使用されます。 サービス プリンシパルによって行われた呼び出しは、ユーザーを対象とする条件付きアクセス ポリシーによってブロックされません。 ワークロード ID の条件付きアクセスを使用して、サービス プリンシパルを対象とするポリシーを定義します。
    - 組織がスクリプトまたはコードでこれらのアカウントを使用している場合は、 [それらをマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) に置き換えます。

#### 条件付きアクセスでのユーザー リスク ポリシー

1. [条件付きアクセス管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. **[新しいポリシー]** を選択します。
4. ポリシーに名前を付ける。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. **[割り当て]** で、 **[ユーザーまたはワークロード ID]**を選択します。
    1. **[Include](https://learn.microsoft.com/ja-jp/entra/id-protection/含める)** で、 **[すべてのユーザー]** を選択します。
    2. **[除外]** で、 **[ユーザーとグループ]** を選択し、組織の緊急アクセス用または非常用アカウントを選択します。
    3. **完了** を選択します。
6. [ **ターゲット リソース**&gt;**Include**] で、[ **すべてのリソース ] (以前の [すべてのクラウド アプリ])** を選択します。
7. **[条件]**&gt;**[ユーザー リスク]** で、**[構成]** を **[はい]**に設定します。
    1. **[ポリシーを適用するために必要なユーザー リスク レベルを構成する]** で、**[高]** を選びます。 [このガイダンスは Microsoft の推奨事項に基づいており、組織ごとに異なる場合があります](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#choosing-acceptable-risk-levels)
    2. **完了** を選択します。
8. **[アクセス制御]**&gt;**[許可]** で、 **[アクセス権の付与]**を選択します。
    1. **リスク修正を要求**を選択します。 **[Require authentication strength** grant control]\(認証強度付与の制御が必要\) が自動的に選択されます。 組織に適した強度を選択します。
    2. **[選択]** を選びます。
9. [ **セッション**] の [ **サインイン頻度] - 毎回** がセッション コントロールとして自動的に適用され、必須です。
10. 設定を確認し、 **[ポリシーの有効化]** を **[レポート専用]** に設定します。
11. [ **作成] を** 選択してポリシーを作成します。

[ポリシーの影響モードまたはレポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#reviewing-results)を使用して設定を確認したら、[**ポリシーの有効化]** トグルを **[レポートのみ]** から **[オン]** に移動します。

#### 条件付きアクセスのサインイン リスク ポリシー

1. [条件付きアクセス管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. **[新しいポリシー]** を選択します。
4. ポリシーに名前を付ける。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. **[割り当て]** で、 **[ユーザーまたはワークロード ID]**を選択します。
    1. **[Include](https://learn.microsoft.com/ja-jp/entra/id-protection/含める)** で、 **[すべてのユーザー]** を選択します。
    2. **[除外]** で、 **[ユーザーとグループ]** を選択し、組織の緊急アクセス用または非常用アカウントを選択します。
    3. **完了** を選択します。
6. **[Cloud アプリまたはアクション]**&gt;**[含める]** で、**[すべてのリソース] (旧称 "すべてのクラウド アプリ")** を選択します。
7. **[条件]**&gt;**[ログイン リスク]** で、**[構成]** を **[はい]**に設定します。
    1. **[このポリシーを適用するサインイン リスク レベルを選択します]** で、 **[高]** と **[中]** を選択します。 [このガイダンスは Microsoft の推奨事項に基づいており、組織ごとに異なる場合があります](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#choosing-acceptable-risk-levels)
    2. **完了** を選択します。
8. **[アクセス制御]**&gt;**[許可]** で、 **[アクセス権の付与]**を選択します。
    1. **[認証強度を要求する]** を選択し、一覧から組み込みの認証強度である **[多要素認証]** を選択します。
    2. **[選択]** を選びます。
9. **セッション**の下で
    1. **[サインインの頻度]** を選択します。
    2. **[毎回]** が選択されていることを確認します。
    3. **[選択]** を選びます。
10. 設定を確認し、 **[ポリシーの有効化]** を **[レポート専用]** に設定します。
11. **[作成]** を選択して、ポリシーを作成および有効化します。

[ポリシーの影響モードまたはレポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#reviewing-results)を使用して設定を確認したら、[**ポリシーの有効化]** トグルを **[レポートのみ]** から **[オン]** に移動します。

#### パスワードレスのシナリオ

[パスワードレス認証方式](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-deployment)を採用している組織の場合は、次の変更を行います。

##### パスワードレス サインイン リスク ポリシーを更新する

1. **[ユーザー]**で:
    1. **含める**、**ユーザーとグループ**を選択し、パスワードレスのユーザーを選択します。
    2. **[除外]** で、 **[ユーザーとグループ]** を選択し、組織の緊急アクセス用または非常用アカウントを選択します。
    3. **完了** を選択します。
2. **[クラウド アプリまたは操作]**&gt;**[対象]** で、**[すべてのリソース]** (旧称 [すべてのクラウド アプリ]) を選択します。
3. **[条件]**&gt;**[ログイン リスク]** で、**[構成]** を **[はい]**に設定します。
    1. **[このポリシーを適用するサインイン リスク レベルを選択します]** で、 **[高]** と **[中]** を選択します。 リスク レベルの詳細については、「[許容されるリスク レベル](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#choosing-acceptable-risk-levels)の選択」を参照してください。
    2. **完了** を選択します。
4. **[アクセス制御]**&gt;**[許可]** で、 **[アクセス権の付与]**を選択します。
    1. **[認証強度が必要]** を選択してから、対象のユーザーが持つ方法に基づいて、組み込みの **[パスワードレス MFA]** または **[フィッシング防止 MFA]** を選択します。
    2. **[選択]** を選びます。
5. **[セッション]**で:
    1. **[サインインの頻度]** を選択します。
    2. **[毎回]** が選択されていることを確認します。
    3. **[選択]** を選びます。

### リスク ポリシーを条件付きアクセスに移行

Microsoft Entra ID 保護で有効なレガシ リスク ポリシーが存在する場合は、次のようにそれらを条件付きアクセスに移行することを計画する必要があります。

警告

Microsoft Entra ID 保護 で構成された従来のリスク ポリシーは、**2026 年 10 月 1 日**に廃止されます。

#### 条件付きアクセスへの移行

1. レポート専用モードの条件付きアクセスで、**ユーザー リスクベース**とサインイン リスクベースの同等のポリシーを作成します。 Microsoft の推奨事項と組織の要件に基づき、前の手順または[条件付きアクセス テンプレート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)を使用してポリシーを作成することができます。
    1. 管理者は、[レポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)を使用して設定を確認したら、**[ポリシーの有効化]** トグルを **[レポートのみ]** から **[オン]** に移動できます。
2. ID Protection の古いリスク ポリシーを**無効にします**。
    1. **ID Protection**&gt;**Dashboard** に移動&gt;**ユーザー リスク**ポリシーまたは**サインイン リスク ポリシーを**選択します。
    2. **[ポリシーの適用]** を **[無効]** に設定します。
3. [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)で必要な場合は、他のリスク ポリシーを作成します。

##### リスク ポリシーの移行に関するヘルプとサポート

リスク ポリシーを条件付きアクセスに移行する際にサポートが必要な場合は、Microsoft Entra 管理センターでサポート リクエストを送信してください。

1. Microsoft Entra 管理センターの **New サポート リクエスト**に移動します。
2. 問題について説明する: **レガシ ID 保護ポリシーを移行します**。
3. **Microsoft Entraサインインと多要素認証**&gt;**Next** を選択します。
4. [ **新規または既存のポリシー設定の構成]**&gt;**[次へ**]を選択します。
5. ソリューション ページを閉じます。
6. **Support request の作成**を選択します。
7. 次の選択肢を使用して、リクエストを適切なチームに送信します。
    1. 問題の種類: **技術**。
    2. サービスの種類: **Microsoft Entraサインインと多要素認証**。
    3. 概要: **レガシ ID 保護ポリシーを移行します**。
    4. 問題の種類: **Identity Protection**。
    5. 問題サブタイプ: **リスク ポリシーを構成します**。
8. [ **次へ** ] を選択し、[ **追加の詳細** ] タブに移動して詳細を入力します。
9. 必要な詳細を入力し、要求を送信します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-identity-protection-graph-api"} -->
## Microsoft Graph PowerShell SDK と Microsoft Entra ID Protection - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-graph-api
- Service: entra-id-protection
- Article date: 2025-05-27
- Summary: Microsoft Entra ID から Microsoft Graph のリスク検出と関連情報を照会する

Microsoft Graph は、Microsoft 統合 API エンドポイントであり、 [Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) API のホームです。 この記事では、 [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started) を使用して、危険なユーザーを PowerShell で管理する方法について説明します。 Microsoft Graph API に直接クエリを実行する組織は、「 [チュートリアル: Microsoft Graph API を使用してリスクを特定して修復](https://learn.microsoft.com/ja-jp/graph/tutorial-riskdetection-api) する」という記事を使用して、その体験を開始できます。

### [前提条件]

この記事の PowerShell コマンドを使用するには、次の前提条件が必要です。

- Microsoft Graph PowerShell SDK がインストールされています。

    - 詳細については、 [Microsoft Graph PowerShell SDK のインストールに関する記事を](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true)参照してください。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロール。
- `IdentityRiskEvent.Read.All` と `IdentityRiskyUser.ReadWrite.All` の委任されたアクセス許可が必要です。

    - アクセス許可を `IdentityRiskEvent.Read.All` および `IdentityRiskyUser.ReadWrite.All` に設定するには、次のコマンドを実行します。

    ```powershell
    Connect-MgGraph -Scopes "IdentityRiskEvent.Read.All","IdentityRiskyUser.ReadWrite.All"
    ```
- アプリ専用認証を使用する場合は、「 [Microsoft Graph PowerShell SDK でアプリ専用認証を使用する](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/app-only?view=graph-powershell-1.0&tabs=azure-portal&preserve-view=true)」を参照してください。

    - 必要なアプリケーションのアクセス許可でアプリケーションを登録するには、証明書を準備して次を実行します。

    ```powershell
    Connect-MgGraph -ClientID YOUR_APP_ID -TenantId YOUR_TENANT_ID -CertificateName YOUR_CERT_SUBJECT ## Or -CertificateThumbprint instead of -CertificateName
    ```

### PowerShell を使用して危険な検出を一覧表示する

ID Protection のリスク検出のプロパティによって、リスク検出を取得できます。

```powershell
# List all anonymizedIPAddress risk detections
Get-MgRiskDetection -Filter "RiskType eq 'anonymizedIPAddress'" | Format-Table UserDisplayName, RiskType, RiskLevel, DetectedDateTime

# List all high risk detections for the user 'User01'
Get-MgRiskDetection -Filter "UserDisplayName eq 'User01' and RiskLevel eq 'high'" | Format-Table UserDisplayName, RiskType, RiskLevel, DetectedDateTime

```

### PowerShell を使用して危険なユーザーを一覧表示する

ID Protection では、危険なユーザーとその危険な履歴を取得できます。

```powershell
# List all high risk users
Get-MgRiskyUser -Filter "RiskLevel eq 'high'" | Format-Table UserDisplayName, RiskDetail, RiskLevel, RiskLastUpdatedDateTime

#  List history of a specific user with detailed risk detection
Get-MgRiskyUserHistory -RiskyUserId 00aa00aa-bb11-cc22-dd33-44ee44ee44ee | Format-Table RiskDetail, RiskLastUpdatedDateTime, @{N="RiskDetection";E={($_). Activity.RiskEventTypes}}, RiskState, UserDisplayName

```

### PowerShell を使用してユーザーが侵害されたことを確認する

ユーザーが侵害されたことを確認し、ID Protection でリスクの高いユーザーとしてフラグを設定できます。

```powershell
# Confirm Compromised on two users
Confirm-MgRiskyUserCompromised -UserIds "11bb11bb-cc22-dd33-ee44-55ff55ff55ff","22cc22cc-dd33-ee44-ff55-66aa66aa66aa"
```

### PowerShell を使用して危険なユーザーを無視する

ID Protection では、危険なユーザーを一括で無視できます。

```powershell
# Get a list of high risky users which are more than 90 days old
$riskyUsers= Get-MgRiskyUser -Filter "RiskLevel eq 'high'" | where RiskLastUpdatedDateTime -LT (Get-Date).AddDays(-90)
# bulk dismiss the risky users
Invoke-MgDismissRiskyUser -UserIds $riskyUsers.Id
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-identity-protection-investigate-risk"} -->
## Microsoft Entra ID 保護 を使用してリスクを調査する - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk
- Service: entra-id-protection
- Article date: 2026-05-27
- Summary: Microsoft Entra ID 保護で危険なユーザー、検出、およびサインインを調査する方法について説明します。

Microsoft Entra ID 保護 には、環境内の ID リスクを調査するために使用できる複数のリスク [レポート](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports) が用意されています。 イベントの調査は、セキュリティ戦略の弱点を理解して識別するための鍵です。 ID Protection レポートは、ストレージ用にアーカイブすることも、セキュリティ情報およびイベント管理 (SIEM) ツールと統合してさらに分析することもできます。 組織は、Microsoft Defender、Microsoft Sentinel、および Microsoft Graph API の統合を利用して、他のソースとデータを集計することもできます。

環境内のリスクを調査する方法は多数あり、調査中に考慮すべきさらに詳細があります。 この記事では、作業の開始に役立つフレームワークについて説明し、最も一般的なシナリオと推奨されるアクションの概要を示します。

### [前提条件]

- Microsoft Entra ID 保護 機能へのフル アクセスには、[Microsoft Entra ID P2 または](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) Microsoft Entra スイート ライセンスが必要です。
- [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) は、 **リスク レポートを表示**するために必要な最小限の特権ロールです。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) は、 **サインイン ログと監査ログを表示**するために必要な最小限の特権ロールです。

### 初期トリアージ

最初のトリアージを開始するときは、次のアクションをお勧めします。

1. [ID Protection ダッシュボード](https://learn.microsoft.com/ja-jp/entra/id-protection/id-protection-dashboard)を確認して、環境内の検出に基づいて、攻撃の数、リスクの高いユーザーの数、およびその他の重要なメトリックを視覚化します。
2. [リスク レポート](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports)を確認して、最近危険なユーザー、サインイン、または検出の詳細を確認します。
3. [影響分析ブック](https://learn.microsoft.com/ja-jp/entra/id-protection/workbook-risk-based-policy-impact)を確認して、環境内でリスクが明らかになるシナリオと、リスクの高いユーザーとサインインを管理するために有効にする必要があるリスクベースのアクセス ポリシーを理解します。
4. [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)を確認して、同じ特性を持つ同様のアクティビティを特定します。 このアクティビティは、より多くのアカウントが侵害されたことを示している可能性があります。

    1. IP アドレス、地域、成功/失敗などの共通の特性がある場合は、条件付きアクセス ポリシーを使ってそれらをブロックすることを検討します。
    2. 可能性があるデータのダウンロードや管理上の変更など、侵害された可能性のあるリソースを確認します。
    3. [条件付きアクセスを使用して自己修復ポリシーを](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)有効にします。
5. [Microsoft Purview を使用した Insider Risk Management](https://learn.microsoft.com/ja-jp/purview/insider-risk-management) を使用すると、ユーザーが他の危険なアクティビティ (新しい場所から大量のファイルをダウンロードするなど) を実行したかどうかを確認できます。 この動作は、侵害の可能性を強く示しています。

攻撃者がユーザーを偽装していると思われる場合は、ユーザにパスワードをリセットするよう要求し、MFAを実行するか、偽装しているユーザーをブロックして、すべての更新トークンとアクセス トークンを取り消す必要があります。

### 調査とリスク修復フレームワーク

組織は、次のフレームワークを使用して、疑わしいアクティビティを調査できます。 リスクが検出された場合、推奨される最初の手順は自己修復です (オプションの場合)。 自己修復は、セルフサービスパスワードリセットまたは [リスクベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)の修復フローを通じて行うことができます。

自己修復がオプションでない場合、管理者はリスクを修復する必要があります。 修復は、パスワード リセットを呼び出し、ユーザーに MFA の再登録、ユーザーのブロック、またはユーザー セッションの取り消しを要求することで行われます。 次のフローチャートは、リスクが検出された後の推奨フローを示しています。

[Image: リスク修復フローを示す図。]

リスクが封じ込められたら、リスクを「安全」、「損なわれた」、または「問題なし」としてマークするために、さらに調査が必要になる場合があります。

1. サインイン ログを確認し、特定のユーザーのアクティビティが正常かどうかを検証します。

    1. 次のプロパティを含むユーザーの過去のアクティビティを調べて、それらが特定のユーザーにとって正常なことであるかどうかを確認します。
        - アプリケーション - アプリはユーザーによって一般的に使用されますか?
        - デバイス - デバイスは登録または準拠していますか?
        - 場所 - ユーザーは別の場所に移動したり、複数の場所からデバイスにアクセスしたりしますか?
        - IP アドレス
        - **[ユーザ エージェント文字列]**
2. 使用可能な場合は、他のセキュリティ ツールを使用して調査します。

    - [Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview) がある場合は、より大きな問題を示す可能性のある対応するアラートを確認します。
    - [Microsoft Defender XDR](https://learn.microsoft.com/ja-jp/defender-for-identity/understanding-security-alerts) を使用している場合は、他の関連するアラートやインシデントを通じてユーザー リスク イベントに従うことができます。
    - Microsoft Defender XDR の Microsoft Sentinel を介した MITRE ATT&CK チェーンも分析情報を提供する場合があります。 [Microsoft Defender ポータル](https://security.microsoft.com)で、**インシデントとアラート**&gt;**Alerts**&gt;を参照し、**製品名**フィルターを **AAD Identity Protection** に設定して、Microsoft Entra ID 保護 からアラートを検索します。
3. サインインが認識されているかどうかを確認するには、ユーザーに問い合わせてください。ただし、メールまたは Teams が侵害される可能性があることに注意してください。

    1. 次のような情報をお持ちの場合は確認してください。
        - タイムスタンプ
        - アプリケーション
        - デバイス
        - 場所
        - IP アドレス
4. 調査の結果に応じて、ユーザーまたはサインインを侵害の確認済み、安全確認済み、またはリスクの無視としてマークします。 Microsoft Entra 管理センターまたは [Microsoft Graph を使用してプログラムによって](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-simulate-risk#confirm-compromise-using-microsoft-graph)、侵害を確認できます。
5. [リスクベースの条件付きアクセス ポリシーを設定し](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#enable-policies) 同様の攻撃を防いだり、カバレッジのギャップに対処したりします。

### 特定の検出を調査する

特定のリスク検出には、特定の調査手順が必要です。 次のセクションでは、最も一般的なリスク検出と推奨されるアクションの一部について説明します。

#### Microsoft Entra の脅威インテリジェンス

Microsoft Entra 脅威インテリジェンス リスク検出を調査するには、[リスク検出の詳細] ウィンドウの [追加情報] フィールドに表示される情報に基づいて、次の手順に従います。

- サインインが疑わしい IP アドレスからの場合:

    1. IP アドレスが環境内で疑わしい動作を示しているかどうかを確認します。
    2. ディレクトリ内でユーザーまたはユーザーのセットに対して、IP によって多数のエラーが生成されていますか?
    3. IP のトラフィックが予期しないプロトコルまたはアプリケーションから送信されていますか (従来のプロトコル Exchange など)?
    4. IP アドレスがクラウド サービス プロバイダーに対応している場合は、同じ IP から実行される正当なエンタープライズ アプリケーションが存在しないことをルールによって除外します。
- アカウントはパスワード スプレー攻撃の被害者でした

    1. ディレクトリ内で他のユーザーが同じ攻撃の対象になっていないことを検証します。
    2. 他のユーザーが、同じ期間内に検出されたサインインに似た非定型パターンのサインインを持っているかどうかを判断します。 パスワード スプレー攻撃では、次のような特殊なパターンが表示されることがあります。
        - ユーザー エージェント文字列
        - アプリケーション
        - プロトコル
        - IP/ASN の範囲
        - サインインの時刻と頻度
- 検出はリアルタイム ルールによってトリガーされました

    1. ディレクトリ内で他のユーザーが同じ攻撃の対象になっていないことを検証します。 これは、ルールに割り当てられている TI\_RI\_#### 番号で確認できます。
    2. リアルタイム ルールは、Microsoft の脅威インテリジェンス調査によって特定された新しい攻撃から保護します。 ディレクトリ内の複数のユーザーが同じ攻撃のターゲットであった場合は、サインインの他の属性で異常なパターンを調査します。

#### 非定型旅行の検出

- アクティビティが正当なユーザーによって実行 *されなかった*ことが確認された場合:
    1. サインインを侵害済みとしてマークし、自己修復によってまだ実行されていない場合はパスワード リセットを呼び出します。
    2. 攻撃者がパスワードをリセットしたり、MFA とパスワードのリセットを実行したりするためのアクセス権を持っている場合は、ユーザーをブロックします。
- ユーザーが職務の範囲で IP アドレスを使用することがわかっている場合は、サインインが安全であることを確認します。
- ユーザーがアラートに詳しく記載されている宛先に最近移動したことを確認した場合は、サインインが安全であることを確認します。
- IP アドレス範囲が承認された VPN からの場合は、サインインが安全であることを確認し、Microsoft Entra ID と Microsoft Defender for Cloud Apps の名前付き場所に VPN IP アドレス範囲を追加します。

#### 異常なトークンとトークン発行者の異常検出

- リスク アラート、場所、アプリケーション、IP アドレス、ユーザー エージェント、またはユーザーにとって予期しないその他の特性の組み合わせを使用して、正当なユーザーによってアクティビティが実行 *されなかった* ことを確認した場合:

    1. サインインを侵害済みとしてマークし、自己修復によってまだ実行されていない場合はパスワード リセットを呼び出します。
    2. 攻撃者がパスワード リセットのアクセス権を持ってたり、パスワード セットを実行したりする場合は、ユーザーをブロックします。
    3. [リスクベースの条件付きアクセス ポリシーを設定](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#enable-policies) して、パスワードのリセットを要求したり、MFA を実行したり、リスクの高いすべてのサインインのアクセスをブロックしたりします。
- ユーザーに対して場所、アプリケーション、IP アドレス、ユーザー エージェント、またはその他の特性が想定されていることを確認し、他の侵害の兆候がない場合は、ユーザーがリスクベースの条件付きアクセス ポリシーを使用して自己修復できるようにするか、管理者にサインインが安全であることを確認させます。

トークン ベースの検出の詳細な調査については、ブログ記事の「[トークンの戦術: クラウド トークンの盗難の防止、検出、対応を行う方法](https://www.microsoft.com/security/blog/2022/11/16/token-tactics-how-to-prevent-detect-and-respond-to-cloud-token-theft)」と「[トークン盗難の調査プレイブック](https://learn.microsoft.com/ja-jp/security/operations/token-theft-playbook)」をご覧ください。

#### 疑わしいブラウザーの検出

この検出は、ユーザーがブラウザーを一般的に使用していないか、ブラウザー内のアクティビティがユーザーの通常の動作と一致しないことを示します。

- サインインが侵害されていることを確認し、自己修復によってまだ実行されていない場合はパスワード リセットを呼び出します。 攻撃者がパスワード リセットのアクセス権を持ってたり、MFA を実行したりする場合は、ユーザーをブロックします。
- [リスクベースの条件付きアクセス ポリシーを設定](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#enable-policies) して、パスワードのリセットを要求したり、MFA を実行したり、リスクの高いすべてのサインインのアクセスをブロックしたりします。

#### 悪意のある IP アドレスの検出

- アクティビティが正当なユーザーによって実行 *されなかった* ことを確認した場合:

    1. サインインが侵害されていることを確認し、自己修復によってまだ実行されていない場合はパスワード リセットを呼び出します。
    2. 攻撃者がパスワードをリセットしたり、MFA を実行してパスワードをリセットしすべてのトークンを取り消すためのアクセス権を持っている場合は、ユーザーをブロックします。
    3. [リスクベースの条件付きアクセス ポリシーを設定して、](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#enable-policies) パスワードのリセットを要求するか、リスクの高いすべてのサインインに対して MFA を実行します。
- ユーザーが職務の範囲で IP アドレスを使用 *することを* 確認した場合は、サインインが安全であることを確認します。

#### パスワード スプレーの検出

パスワード スプレー検出とは、攻撃者がスプレー攻撃を実行し、テナント内のユーザーに対して資格情報の検証が成功したことを Microsoft が観察したことを意味します。 スプレー攻撃は、多くのテナント間でユーザーを標的にしている可能性があります。検出は、パスワードの一致が成功したことが確認されたテナントでのみ発生します。 スプレー試行が失敗しても検出は生成されません。

- アクティビティが正当なユーザーによって実行 *されなかった* ことを確認した場合:

    1. サインインを侵害済みとしてマークし、自己修復によってまだ実行されていない場合はパスワード リセットを呼び出します。
    2. 攻撃者がパスワードをリセットしたり、MFA を実行してパスワードをリセットしすべてのトークンを取り消すためのアクセス権を持っている場合は、ユーザーをブロックします。
- ユーザーが職務の範囲で IP アドレスを使用 *することを* 確認した場合は、サインインが安全であることを確認します。
- アカウントが侵害されていないことを確認し、アカウントに対するブルート フォースまたはパスワード スプレー インジケーターが表示されない場合:

    1. ユーザーがリスクベースの条件付きアクセス ポリシーを使用して自己修復することを許可するか、管理者にサインインが安全であることを確認させます。
    2. 不要なアカウントロックアウトを回避するために、Microsoft Entra スマート ロックアウト適切に構成されていることを確認します。

パスワード スプレーのリスク検出の詳細な調査については、「[パスワード スプレー調査に](https://learn.microsoft.com/ja-jp/security/operations/incident-response-playbook-password-spray)」の記事をご覧ください。

#### 漏洩した資格情報の検出

漏洩した資格情報の検出は、確認された資格情報の公開を表すので、常に高リスクです。 この検出が発生したら、すぐに調査してください。

この検出でユーザーの資格情報が漏洩した場合:

1. **露出の範囲を評価します。** ユーザーのリスク履歴とサインイン ログを確認して、漏洩した資格情報が未承認のアクセスに使用されたかどうかを判断します。 未知の場所からのサインイン、匿名 IP アドレス、非定型旅行など、関連するサインイン リスク イベントを探します。
2. **パスワードが既に変更されているかどうかを確認します。** 漏洩が検出された日付以降にユーザーがパスワードを変更したかどうかを確認します。 [Microsoft Entra 条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#user-risk-policy-in-conditional-access)によってトリガーされるクラウドベースのパスワード リセットは、この検出のユーザー リスクを完全に修復します。 パスワードが変更された場合、リスクは既に自己修復されている可能性があります。 そうでない場合は、ユーザーが侵害されていることを確認し、パスワードのリセットを開始します。
3. **攻撃者がアクティブな場合はアクセスをブロックします。** サインイン ログに未承認のアクセスが表示されている場合、または攻撃者がパスワードをリセットまたは MFA を実行する機能がある場合は、ユーザーをブロックし、パスワードをリセットし、すべての更新トークンを取り消します。 セッションの取り消しは、アクティブな侵害の証拠がある場合に重要です。
4. **横移動を確認します。** ユーザーの最近のアクティビティで、特権エスカレーションの兆候、新しいアプリの登録、メールボックス ルールの変更、侵害後のアクティビティを示す可能性がある機密性の高いリソースへのアクセスを確認します。
5. **接続されているアカウントを確認します。** ユーザーが複数のサービスでパスワードを再利用する場合は、テナントを超えて侵害された資格情報を検討してください。 同じ資格情報を使用する他のサービスでパスワードを変更するようユーザーに勧めます。

### 将来のリスクを軽減する

- 条件付きアクセス ポリシーの [名前付き場所](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network) に企業 VPN と IP アドレス範囲を追加して、誤検知を減らします。
- 更新された組織の出張レポートのための既知の出張者データベースの作成を検討し、それを使って出張アクティビティを相互参照します。
- [ID Protection でフィードバックを提供](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-risk-feedback) して、検出精度を向上させ、誤検知を減らします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-identity-protection-remediate-unblock"} -->
## リスクを修復してユーザーをブロック解除する - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock
- Service: entra-id-protection
- Article date: 2026-05-15
- Summary: ユーザーの自己修復を構成し、Microsoft Entra ID 保護で危険なユーザーを手動で修復する方法について説明します。

[リスク調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)を完了したら、リスクの高いユーザーを修復したり、ブロックを解除したりするアクションを実行する必要があります。 [リスクベースのポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)を設定して、自動修復を有効にしたり、ユーザーのリスク状態を手動で更新したりできます。 Microsoftは、リスクを扱うときに時間が重要であるため、迅速に行動することをお勧めします。

この記事では、リスクを自動的に手動で修復するためのいくつかのオプションを提供し、ユーザー リスクが原因でユーザーがブロックされたシナリオについて説明します。そのため、ブロックを解除する方法について説明します。

### [前提条件]

- Microsoft Entra ID 保護機能へのフル アクセスには、Microsoft Entra ID P2 または Microsoft Entra スイート ライセンスが必要です。
    - 各ライセンスレベルの機能の詳細な一覧については、「[Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)を参照してください。
- [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)ロールは、**パスワードをリセット**するために必要な最小限の特権ロールです。
- [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator) ロールは、**ユーザー リスクを無視**するために必要な最小限の特権ロールです。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロールは、**リスクベースのポリシーを作成または編集**するために必要な最小限の特権ロールです。
- [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)ロールは、**条件付きアクセス ポリシーを作成または編集**するために必要な最小限の特権ロールです。

### パスワード変更とセルフサービス パスワード リセット (SSPR)

この記事では、次の 2 つの異なるパスワード変更フローを区別します。

- **パスワードの変更 (リスクの修復)**: ユーザーは現在のパスワードを認識し、多要素認証 (MFA) を使用して認証を行い、パスワードを変更します。 これは、リスクベースの条件付きアクセス ポリシーで使用されるメカニズムです。これには、[ **パスワードの変更を要求する]** や [ **リスクの修復** の許可を要求する] の制御が含まれます。 このフローでは、セルフサービス パスワード リセット (SSPR) は使用されません。
- **パスワード リセット (SSPR/回復)**: ユーザーは自分のパスワードを認識せず、セルフサービス パスワード リセット (SSPR) または管理者が開始したリセットを使用してアクセスを回復します。 このフローは、アカウントの復旧を目的としています。 SSPR を使用したパスワード リセットによって、ユーザー リスクも修復されます。

### リスク修復のしくみ

アクティブなリスク検出はすべて、ユーザーのリスク レベルの計算に影響します。これは、ユーザーのアカウントが侵害される確率を示します。 リスク レベルとテナントの構成によっては、リスクの調査と対処が必要になる場合があります。 リスク [ベースのポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)を設定することで、ユーザーがサインインとユーザーのリスクを自己修復できるようにします。 多要素認証やセキュリティで保護されたパスワード変更などの必要なアクセス制御にユーザーが合格すると、リスクは自動的に修復されます。

条件が満たされていないサインイン中にリスクベースのポリシーが適用された場合、ユーザーはブロックされます。 このブロックは、ユーザーが必要な手順を実行できないために発生するため、 ユーザーのブロックを解除するには管理者の介入が必要です。

リスクベースのポリシーはリスク レベルに基づいて構成され、サインインまたはユーザーが構成されたレベルと一致する場合にのみ適用されます。 一部の検出では、ポリシーが適用されるレベルにリスクが発生しない可能性があるため、管理者はこれらの状況を手動で処理する必要があります。 管理者は、 [場所からのアクセスをブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-by-location) したり、ポリシーで許容されるリスクを下げたりするなど、追加の対策が必要であると判断できます。

### エンドユーザーの自己解決

リスクベースの条件付きアクセス ポリシーを構成すると、ユーザーのリスクとサインイン リスクを修復することが、ユーザーのセルフサービス プロセスになる可能性があります。 この自己修復により、ユーザーはヘルプ デスクまたは管理者に問い合わせる必要なく、自分のリスクを解決できます。 IT 管理者は、リスクを修復するためのアクションを実行する必要がない場合がありますが、自己修復を許可するポリシーを構成する方法と、関連レポートに表示される内容を知る必要があります。 詳細については、以下を参照してください。

- [リスク ポリシーを構成して有効にする](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)
- [Microsoft Entra ID 保護と条件付きアクセスを使用した自己修復エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience)
- [すべてのユーザーに多要素認証を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)
- [パスワード ハッシュ同期を実装する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)

#### サインイン リスクの自己修復

ユーザーのサインイン リスクがリスクベースのポリシーによって設定されたレベルに達した場合、ユーザーはサインイン リスクを修復するために多要素認証 (MFA) を実行するように求められます。 MFA 認証課題が正常に完了すると、サインインのリスクが修復されます。 ユーザー、サインイン、および対応するリスク検出のリスク状態とリスクの詳細は、次のように更新されます。

- リスクの状態: "危険な状態" -&gt; "修復済み"
- リスクの詳細: "-" -&gt; "ユーザーが多要素認証に合格しました"

修復されないサインイン リスクはユーザー リスクに影響するため、リスクベースのポリシーを設定すると、ユーザーはサインイン リスクを自己修復できるため、ユーザー のリスクは影響を受けられません。

#### ユーザー リスクの自己修復

**Require パスワード変更**許可制御を持つユーザー リスク ポリシーが構成されている場合、ユーザーは、[Microsoft Entra ID 保護 ユーザー エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience)に関する記事に示すように、セキュリティで保護されたパスワード変更を実行するように求められます。 ユーザーはまず多要素認証を完了してから、パスワードを変更する必要があります。 このプロセスでは、セルフサービス パスワード リセット (SSPR) フローは使用されません。 パスワードが変更されると、ユーザー リスクが修復され、ユーザーは新しいパスワードでサインインできます。 ユーザー、サインイン、および対応するリスク検出のリスク状態とリスクの詳細は、次のように更新されます。

- リスクの状態: "危険な状態" -&gt; "修復済み"
- リスクの詳細: "-" -&gt; "ユーザーがセキュリティで保護されたパスワードのリセットを実行しました"

注

リスクの詳細値 "ユーザーがセキュリティで保護されたパスワードのリセットを実行しました" は、システムによって報告されるラベルです。 名前にもかかわらず、この値は、ユーザーがセルフサービス パスワード リセット フローではなく、セキュリティで保護されたパスワード変更 (MFA + パスワードの変更) を完了したことを示します。

##### クラウドおよびハイブリッド ユーザーに関する考慮事項

- クラウド ユーザーとハイブリッド ユーザーの両方が、MFA を実行できる場合にのみ、セキュリティで保護されたパスワードの変更を完了できます。 登録されていないユーザーの場合、このオプションは使用できません。
- ハイブリッド ユーザーは、パスワード ハッシュ同期と オンプレミスのパスワード変更を有効にしてユーザー リスクをリセット設定が有効になっている場合に、オンプレミスまたはハイブリッド参加済みWindows デバイスからのパスワード変更を完了できます。

### システムに基づいた修復

場合によっては、Microsoft Entra ID 保護はユーザーのリスク状態を自動的に無視することもできます。 リスク検出と対応するリスクの高いサインインの両方が、ID Protection によってセキュリティ上の脅威を与えなくなったと識別されます。 この自動介入は、ユーザーが第 2 の要素 (多要素認証 (MFA) など) を提供した場合、またはリアルタイムとオフラインの評価でサインインが危険ではなくなったと判断された場合に発生する可能性があります。 この自動修復により、リスク監視のノイズが軽減されるため、注意が必要な項目に集中できます。

- **リスクの状態**: "リスクあり" -&gt; "却下"
- **リスクの詳細**: "-" -&gt; 「評価された Microsoft Entra ID 保護 サインイン セーフ"

#### Microsoft による脅威の情報に基づいた修復

限られたケースでは、Microsoft脅威インテリジェンスは、Microsoft Entraテナントを対象とする攻撃キャンペーンでアクティブに使用されているアカウント、セッション、またはリソースを識別します。 Microsoftに、組織にアクティブなリスクをもたらす侵害の信頼度の高い証拠がある場合、Microsoftは、脅威を封じ込めるためにユーザーに代わって修復アクションを実行する可能性があります。

これらのアクションは、イニシエーターとして*Microsoft*一覧表示されたMicrosoft Entra監査ログに記録されます。 管理者はテナントの完全な制御を維持し、独自の調査を完了した後にこのプロセスで実行されたすべてのアクションを取り消すことができます。

### 管理者による手動修復

状況によっては、IT 管理者がサインインまたはユーザー リスクを手動で修復する必要があります。 リスクベースのポリシーが構成されていない場合、リスク レベルが自己修復の基準を満たしていない場合、または時間が重要な場合は、次のいずれかのアクションを実行する必要があります。

- ユーザーの一時パスワードを生成します。
- ユーザーにパスワードの変更を要求します。
- ユーザーのリスクを無視します。
- ユーザーが侵害されていることを確認し、アカウントをセキュリティで保護するためのアクションを実行します。
- ユーザーのブロックを解除します。

#### 一時パスワードを生成する

一時パスワードを生成することによって、ID をすぐに安全な状態に戻すことができます。 この方法では、影響を受けるユーザーが一時的なパスワードを知る必要があるために、ユーザーと接触する必要があります。 パスワードは一時的なので、ユーザーは次回サインイン時にパスワードを何か新しいものに変更することを求められます。

一時パスワードを生成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。

    - ユーザーの詳細から一時的なパスワードを生成するには、 [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) ロールが必要です。
    - ID Protection から一時的なパスワードを生成するには、 [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator) ロールと [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) ロールの両方が必要です。
    - セキュリティ オペレーターは ID 保護にアクセスする必要があり、ユーザー管理者はパスワードをリセットする必要があります。
2. **Protection**&gt;**Identity Protection**&gt;**Risky ユーザー**を参照し、影響を受けるユーザーを選択します。

    - または、 **ユーザー**&gt;**すべてのユーザー**を参照し、影響を受けるユーザーを選択します。
3. **[パスワードのリセット]** を選択します。

    [Image: [パスワードのリセット] が強調表示されている [危険なユーザーの詳細] パネルのスクリーンショット。]
4. メッセージを確認し、[ **パスワードのリセット** ] をもう一度選択します。

    [Image: 2 番目の [パスワードのリセット] ボタンのスクリーンショット。]
5. ユーザーに一時パスワードを指定します。 ユーザーは、次回サインインする際にパスワードを変更する必要があります。

ユーザー、サインイン、および対応するリスク検出のリスク状態とリスクの詳細は、次のように更新されます。

- リスクの状態: "危険な状態" -&gt; "修復済み"
- リスクの詳細: "-" -&gt; "管理者がユーザーに対して一時パスワードを生成しました"

##### クラウドおよびハイブリッド ユーザーに関する考慮事項

クラウドおよびハイブリッド ユーザーの一時的なパスワードを生成する場合は、次の考慮事項に注意してください。

- Microsoft Entra 管理センターでは、クラウド ユーザーとハイブリッド ユーザーのパスワードを生成できます。
- 次の設定が設定されている場合は、オンプレミスディレクトリからハイブリッド ユーザーのパスワードを生成できます。
    - [一時パスワードの同期] セクションの PowerShell スクリプトを含め、 [パスワード ハッシュ同期を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization) 有効にします。
    - オンプレミスのパスワード変更を有効にして、Microsoft Entra ID 保護のユーザー リスク設定をリセットします。
    - [セルフサービス パスワード リセットを](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)有効にします。
    - Active Directoryで、前の項目すべてを有効にした後で、**次回ログオン時にパスワードを変更する**オプションのみを選択します。

#### パスワードの変更を要求する

リスクを修復するために、危険なユーザーにパスワードの変更を要求できます。 これらのユーザーは、リスクベースのポリシーによってパスワードの変更を求められないため、パスワードを変更してもらうには、それらのユーザーに連絡する必要があります。 パスワードの変更方法は、ユーザーの種類によって異なります。

- **Microsoft Entra参加済みデバイスを持つクラウド ユーザーとハイブリッド ユーザー**: MFA サインインが成功した後、セキュリティで保護されたパスワードの変更を実行します。 ユーザーは既に MFA に登録されている必要があります。
- **オンプレミスまたはハイブリッドに参加しているWindows デバイスを持つHybrid ユーザー**: Windows デバイスの Ctrl-Alt-Delete 画面で、セキュリティで保護されたパスワードの変更を実行します。
    - [ ユーザー リスクのリセットをオンプレミスのパスワード変更を許可する ] 設定を有効にする必要があります。
    - **ユーザーが次回ログオン時にパスワードを変更する必要がある場合**設定がActive Directoryで有効になっている場合、ユーザーは次回サインイン時にパスワードの変更を求められます。 このオプションは、[ クラウドとハイブリッド ユーザーの考慮事項 ] セクションの設定が設定されている場合にのみ使用できます。

ユーザー、サインイン、および対応するリスク検出のリスク状態とリスクの詳細は、次のように更新されます。

- リスクの状態: "危険な状態" -&gt; "修復済み"
- リスクの詳細: "-" -&gt; "ユーザーがセキュリティで保護されたパスワードの変更を実行しました"

#### リスクを無視する

調査の後、サインインまたはユーザー アカウントが侵害されるリスクがないことを確認した場合は、そのリスクを無視できます。

1. 少なくとも [セキュリティオペレーター](https://entra.microsoft.com)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)にサインインします。
2. **Protection**&gt;**Identity Protection**&gt;**危険なサインイン**または**危険ユーザー**を参照し、危険なアクティビティを選択します。
3. [ **危険なサインインを無視する]** または [ **ユーザー リスクを無視する**] を選択します。

このメソッドはユーザーの既存のパスワードを変更しないため、ID を安全な状態に戻しません。 引き続きユーザーに連絡してリスクを通知し、パスワードを変更するようユーザーに通知する必要がある場合があります。

ユーザー、サインイン、および対応するリスク検出のリスク状態とリスクの詳細は、次のように更新されます。

- リスクの状態: "リスクあり" -&gt; "無視"
- リスクの詳細: "-" -&gt; "管理者がサインインのリスクを無視しました" または "管理者がユーザーのすべてのリスクを無視しました"

#### ユーザーが侵害されていることを確認する

調査の後、サインインまたはユーザーが危険にさらされていることを確認した場合 *は* 、アカウントが侵害されていることを手動で確認できます。

1. **危険なサインイン**や**危険なユーザー**のレポートからイベントやユーザーを選び、**セキュリティ侵害の確認**を選択します。
2. リスクベースのポリシーがトリガーされておらず、この記事で説明されているいずれかの方法を使用してリスクが自己修復されなかった場合は、次の 1 つ以上のアクションを実行します。
    1. パスワードの変更を要求します。
    2. 攻撃者がパスワードのリセットやユーザーの多要素認証を実行できると考えられる場合は、ユーザーをブロックします。
    3. [更新トークンを取り消します](https://learn.microsoft.com/ja-jp/entra/identity/users/users-revoke-access)。
    4. 侵害されたと考えられる [デバイスを無効にします](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities)。
    5. [継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)を使用している場合は、すべてのアクセス トークンを取り消します。

ユーザー、サインイン、および対応するリスク検出のリスク状態とリスクの詳細は、次のように更新されます。

- リスクの状態: "危険な状態" -&gt; "侵害が確認されました"
- リスクの詳細: "-" -&gt; "管理者がユーザーの侵害を確認しました"

侵害の確認時の動作の詳細については、「 [リスクに関するフィードバックを提供する方法](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-risk-feedback#how-to-give-risk-feedback-in-microsoft-entra-id-protection)」を参照してください。

#### ユーザーのブロックを解除する

リスクベースのポリシーを使用してアカウントをブロックし、侵害されたアカウントから組織を保護できます。 これらのシナリオを調査して、ユーザーのブロックを解除する方法を決定し、ユーザーがブロックされた理由を特定する必要があります。

##### 使い慣れた場所またはデバイスからサインインする

サインインの試行が未知の場所またはデバイスから行われると思われる場合、サインインは疑わしいものとしてブロックされることがよくあります。 ユーザーは、使い慣れた場所またはデバイスからサインインして、サインインを試してブロックを解除できます。 サインインが成功した場合、Microsoft ID Protection によってサインイン リスクが自動的に修復されます。

- リスクの状態: "リスクあり" -&gt; "無視"
- リスクの詳細: "-" -&gt; "Microsoft Entra ID 保護 で評価されたサインインは安全です"

##### ポリシーからユーザーを除外する

サインインポリシーまたはユーザー リスク ポリシーの現在の構成が *特定* のユーザーの問題を引き起こしていると思われる場合は、ポリシーからユーザーを除外できます。 このポリシーを適用せずに、これらのユーザーにアクセス権を付与しても安全であることを確認する必要があります。 詳しくは、「[方法:リスク ポリシーを構成して有効にする](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#policy-exclusions)」をご覧ください。

ユーザーがサインインできるように、リスクまたはユーザーを 手動で無視 することが必要になる場合があります。

##### ポリシーを無効にする

ポリシー構成によって *すべての* ユーザーに問題が発生していると思われる場合は、ポリシーを無効にすることができます。 詳しくは、「[方法:リスク ポリシーを構成して有効にする](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)」をご覧ください。

ポリシーに対処する前にサインインできるように、リスクまたはユーザーを 手動で無視 することが必要な場合があります。

##### 信頼度の高いリスクによる自動ブロック

Microsoft Entra ID 保護は、リスクが非常に高い信頼性を持つサインインを自動的にブロックします。 このブロックは、レガシ認証プロトコルを使用して実行されるサインインや、悪意のある試行のプロパティの表示で最も一般的に発生します。 いずれかのシナリオでユーザーがブロックされると、50053 認証エラーが発生します。 サインイン ログには、次のブロック理由が表示されます。"サインインは、リスクの信頼性が高いため、組み込みの保護によってブロックされました。"

信頼度の高いサインイン リスクに基づいてアカウントのブロックを解除するには、次のオプションがあります。

- **信頼できる場所の設定へのサインインに使用する IP を追加**します。会社の既知の場所からサインインが実行された場合は、信頼できる一覧に IP を追加できます。 詳細については、「 [条件付きアクセス: ネットワーク割り当て](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network#trusted-locations)」を参照してください。
- **先進認証プロトコルを使用**する: サインインがレガシ プロトコルを使用して実行される場合、最新の方法に切り替えると、試行のブロックが解除されます。

### オンプレミスのパスワード リセットを許可してユーザー リスクを修復する

組織にハイブリッド環境がある場合は、オンプレミスのパスワード変更を許可して、 [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)を使用してユーザーのリスクをリセットできます。 これらのシナリオでユーザーが自己修復する *前に* 、パスワード ハッシュ同期を有効にする必要があります。

- 危険なハイブリッド ユーザーは、管理者の介入なしに自己修復できます。 ユーザーがオンプレミスでパスワードを変更すると、ユーザー リスクはMicrosoft Entra ID 保護内で自動的に修復され、現在のユーザー リスクの状態がリセットされます。
- 組織は、ハイブリッド [ユーザーを保護するためにパスワードの変更を必要とするユーザー リスク ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#user-risk-policy-in-conditional-access) を事前に展開できます。 このオプションにより、組織のセキュリティ体制が強化され、複雑なハイブリッド環境でもユーザー のリスクに迅速に対処できるため、セキュリティ管理が簡素化されます。

注

オンプレミスのパスワード変更によるユーザー リスクの修復を許可することは、オプトインのみの機能です。 運用環境で有効にする前に、この機能を評価する必要があります。 オンプレミスのパスワード変更プロセスをセキュリティで保護することをお勧めします。 たとえば、ユーザーが Microsoft Identity Manager Self-Service パスワード リセット ポータル0 などのツールを使用してオンプレミスでパスワードを変更できるようにする前に、多要素認証を必要とします。

この設定を構成するには:

1. 少なくとも [セキュリティオペレーター](https://entra.microsoft.com)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)にサインインします。
2. **Protection**&gt;**Identity Protection**&gt;**Settings** に移動します。
3. [ **オンプレミスのパスワード変更を許可してユーザー リスクをリセットする** ] チェック ボックスをオンにし、[保存] を選択 **します**。

[Image: [オンプレミスのパスワード変更を許可してユーザー リスクをリセットする] のチェックボックスの場所を示すスクリーンショット。]

### 削除済みのユーザー

リスクが存在するディレクトリからユーザーが削除された場合、アカウントが削除された場合でも、そのユーザーは引き続きリスク レポートに表示されます。 管理者は、ディレクトリから削除されたユーザーのリスクを無視できません。 削除されたユーザーを削除するには、Microsoftサポート ケースを開きます。

### トークン盗難関連の検出

検出アーキテクチャの最近の更新により、サインイン時にトークンの盗難に関連する場合や、検証済みの脅威アクターの IP 検出トリガーがトリガーされたときに、MFA 要求を使用してセッションを自動修復することはなくなりました。

疑わしいトークン アクティビティまたは検証済みの脅威アクターの IP 検出を識別する次の ID 保護検出は、自動修復されなくなりました。

- Microsoft Entra の脅威インテリジェンス
- 異常なトークン
- 中間攻撃者
- 検証済み脅威アクター IP
- トークン発行者の異常

ID Protection で、サインイン データを出力する検出用の [リスク検出の詳細] ウィンドウにセッションの詳細が表示されるようになりました。 この変更により、MFA 関連のリスクがある検出を含むセッションを閉じないようにします。 セッションの詳細とユーザー レベルのリスクの詳細を提供すると、調査に役立つ貴重な情報が提供されます。 この情報には、次のものが含まれます。

- トークン発行者の種類
- サインイン時間
- IP アドレス
- サインインの場所
- サインイン クライアント
- サインイン要求 ID
- サインイン関連付け ID

ユーザー リスクベースの条件付きアクセス ポリシーが構成されていて、疑わしいトークン アクティビティがユーザーに対して発生したことを示すこれらの検出の 1 つがユーザーに対して発生した場合、エンド ユーザーはセキュリティで保護されたパスワード変更を実行し、リスクをクリアするために多要素認証を使用してアカウントを再認証する必要があります。

### PowerShell プレビュー

Microsoft Graph PowerShell SDK プレビュー モジュールを使用すると、組織は PowerShell を使用してリスクを管理できます。 プレビュー モジュールとサンプル コードは、[Microsoft Entra GitHub リポジトリ](https://github.com/AzureAD/IdentityProtectionTools)にあります。

リポジトリに含まれる `Invoke-AzureADIPDismissRiskyUser.ps1` スクリプトを使用すると、組織のディレクトリ内の危険なユーザーをすべて無視できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-identity-protection-risk-feedback"} -->
## Microsoft Entra ID Protection でリスクに関するフィードバックを提供する - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-risk-feedback
- Service: entra-id-protection
- Article date: 2025-05-27
- Summary: ID Protection リスク検出に関するフィードバックを提供する方法と理由。

Microsoft Entra ID Protection では、リスク評価に関するフィードバックを提供することができます。

フィードバックは、今後の検出の最適化、精度の向上、擬陽性の低減に役立ちます。

### 検出とは

ID 保護の検出は、ID リスクに関連する疑わしいアクティビティのインジケーターです。 これらの疑わしいアクティビティは、リスク検出と呼ばれます。 これらの ID に基づく検出は、ヒューリスティックや機械学習に基づく場合や、パートナー製品から取得される場合があります。 これらの検出は、サインイン リスクとユーザー リスクを判断するために使用されます。

- ユーザー リスクは、ID のセキュリティが侵害されている可能性があることを表します。
- サインイン リスクは、サインインのセキュリティが侵害されている可能性があることを表します (たとえば、ID 所有者がサインインを承認しなかったなど)。

### リスクに関するフィードバックをリスク評価に提供する理由

リスクに関するフィードバックを提供すべき理由をいくつか挙げます。

- **Microsoft Entra ID Protection ユーザーまたはサインイン リスク評価が誤っていることを発見しました**。
    - たとえば、**危険なサインイン** レポートに表示されるサインインに問題がなく、そのサインインに関するすべての検出が偽陽性であった場合。
- **Microsoft Entra ID 保護ユーザーまたはサインインのリスク評価が正しいことを検証した**。
    - たとえば、"**危険なサインイン**" レポートに表示されるサインインが実際に悪意のあるもので、そのサインインに関するすべての検出が真陽性であったことを Microsoft Entra ID に認識させたい場合です。
- **そのユーザーに対するリスクを Microsoft Entra ID Protection の外部で修復し**、そのユーザーのリスク レベルを更新する必要がある。

### Microsoft でのリスクに関するフィードバックの使用方法

Microsoft ではフィードバックを使用して、基になるユーザーやサインインのリスク、それらのイベントの正確性を更新します。 このフィードバックはエンド ユーザーの保護に役立ちます。 たとえば、サインインが侵害されたことを確認すると、ユーザーのリスクが直ちに増加し、サインインの集計リスク (リアルタイム リスクではない) が高くなります。 このユーザーが、危険性の高いユーザーにパスワードを安全にリセットさせることを強制するというユーザー リスク ポリシーに含まれる場合、ユーザーは次回サインインするときに自動的に修復できます。

アクション ボタンが淡色表示されている場合は、より高い特権ロールが必要です。 管理者は危険なサインイン イベントに対してアクションを実行し、次の操作を選ぶことができます。

- **サインインを侵害ありと確認** - このアクションでは、サインインが真陽性であることを確認します。 修復手順が取られるまで、サインインは危険とみなされます。
- **サインインを安全と確認** - このアクションでは、サインインが擬陽性であることを確認します。 同じようなサインインは、将来的に危険とみなされるべきではありません。
- **サインイン リスクを無視する** - このアクションは無害な真陽性に使用されます。 検出されたこのサインイン リスクは実際のものですが、既知の侵入テストや承認済みアプリケーションで生成された既知のアクティビティによるリスクと同様に、悪意のあるものではありません。 同様のサインインについては、今後もリスクを評価する必要があります。

ユーザー レベルでアクションを実行すると、そのユーザーに現在関連付けられているすべての検出に適用されます。 アクション ボタンが淡色表示されている場合は、より高い特権ロールが必要です。 管理者はユーザーに対してアクションを実行し、次の操作を選ぶことができます。

- **パスワードのリセット** - このアクションにより、ユーザーの現在のセッションが取り消されます。
- **ユーザーが侵害されたことを確認する** - このアクションは真陽性に対して実行されます。 ID Protection は、ユーザー リスクを高く設定し、新しい検出を追加します。管理者がユーザーの侵害を確認しました。 修復手順が取られるまで、サインインは危険とみなされます。
- **ユーザーの安全を確認する** - このアクションは誤検知に対して実行されます。 その結果、このユーザーのリスクと検出が削除され、使用プロパティを再学習するための学習モードに配置されます。 このオプションを使用して、誤検知をマークすることができます。
- **ユーザー リスクを無視する** - このアクションは、無害な陽性のユーザー リスクに対して実行されます。 検出されたこのユーザー リスクは実際のものですが、既知の侵入テストによるリスクのような悪意のあるものではありません。 同様のユーザーについては、今後もリスクを評価する必要があります。
- **ユーザーのブロック** - このアクションは、攻撃者がパスワードへのアクセス権や MFA の実行能力を持っている場合に、ユーザーがサインインするのをブロックします。
- **Microsoft 365 Defender で調査する** - このアクションを実行すると、管理者は Microsoft Defender ポータルに移動して、さらに調査できるようになります。

ID Protection のリスク検出に関するフィードバックはオフラインで処理されるため、更新に時間がかかる場合があります。 **[リスク処理状態]** 列にはフィードバック処理の現在の状態が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/howto-identity-protection-simulate-risk"} -->
## Microsoft Entra ID 保護 でリスク検出をシミュレートしてセキュリティを強化する - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-simulate-risk
- Service: entra-id-protection
- Article date: 2026-05-27
- Summary: Microsoft Entra ID 保護 でリスク検出をシミュレートしてセキュリティを強化する方法について説明します。 リスクベースのポリシーを効果的にテストします。

管理者は、Microsoft Entra ID 保護 のリスク検出をシミュレートして、データを設定し、リスクベースの条件付きアクセス ポリシーをテストできます。

このセクションでは、次の種類のリスク検出をシミュレーションする手順を説明します。

- 匿名 IP アドレス (容易)
- 見慣れないサインイン プロパティ (中程度)
- 非典型的な移動 (困難)
- ワークロード ID 用の GitHub の漏洩した資格情報 (中程度)

他のリスク検出は、安全な方法でシミュレートできません。

各リスク検出の詳細については、 [ユーザー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks) ID と [ワークロード ID](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk) のリスクの概要に関する記事を参照してください。

### 匿名 IP アドレスをシミュレートする

次の手順を完了するには、以下を使用する必要があります。

- 匿名 IP アドレスをシミュレートする [Tor Browser](https://www.torproject.org/projects/torbrowser.html.en) 。 組織が Tor Browser の使用を制限している場合は、仮想マシンの使用が必要になることがあります。
- まだ Microsoft Entra 多要素認証用に登録されていないテスト アカウント。

**匿名 IP からのサインインをシミュレートするには、次の手順に従います**。

1. [Tor Browser](https://www.torproject.org/projects/torbrowser.html.en) を使用して、https://myapps.microsoft.comに移動します。
2. **匿名 IP アドレスからのサインイン** レポートに表示するアカウントの資格情報を入力します。

サインイン履歴は、10 分から 15 分以内にレポートに表示されます。

### 未知の Sign-In プロパティをシミュレートする

未知の場所をシミュレートするには、サインインが通常のサインインとは異なる必要があります。この検出には、VPN を使用したサインイン以上のものが必要です。

以下の手順では、新たに作成したものを使用します。

- 通常使用しているものとは異なるオペレーティング システム (OS)。
- 通常サインインしている別のプロバイダーまたは場所からの IP アドレス。

次の手順を完了するには、少なくとも 30 日間のサインイン履歴と使用可能な多要素認証方法を持つユーザー アカウントを使用する必要があります。

**未知の場所からのサインインをシミュレートするには、次の手順を実行します**。

1. 新しい OS または IP を使用して、 https://myapps.microsoft.com に移動し、テスト アカウントの資格情報を入力します。
2. テスト アカウントでサインインするときに、多要素認証チャレンジを完了しないことで、MFA チャレンジを失敗させます。

サインイン履歴は、10 分から 15 分以内にレポートに表示されます。

### 非定型旅行をシミュレートする

通常とは異なる移動状態のシミュレーションは困難です。 アルゴリズムでは、機械学習を使用して、既知のデバイスからの通常とは異なる移動や、ディレクトリ内の他のユーザーによって使用される VPN からのサインインなどの誤検知が除去されます。 さらに、このアルゴリズムでは、リスク検出の生成を開始する前に、そのユーザーの 14 日間のサインイン履歴または 10 回のログインが必要です。 複雑な機械学習モデルと上記のルールのため、次の手順ではリスク検出がトリガーされないこともあります。 特殊な移動パターンの検出をシミュレーションするには、複数の Microsoft Entra アカウントでこれらの手順を繰り返さなければならない場合があります。

**非定型の移動リスク検出をシミュレートするには、次の手順を実行**します。

1. 標準的なブラウザーを使用して、https://myapps.microsoft.com に移動します。
2. 特殊な移動のリスク検出を生成するアカウントの資格情報を入力します。
3. ユーザー エージェントを変更します。 開発者ツール (F12) から Microsoft Edge のユーザー エージェントを変更できます。
4. IP アドレスを変更します。 VPN または Tor アドオンを使用するか、Azure 上に別のデータ センターの新しい仮想マシンを作成することで、IP アドレスを変更できます。
5. 前と同じ認証情報を使用して、前回のサインインから数分以内に、https://myapps.microsoft.com にサインインします。

サインイン履歴は、2 時間から 4 時間以内にレポートに表示されます。

### ワークロード ID 用の漏洩した認証情報

このリスク検出は、アプリケーションの有効な資格情報が漏洩したことを示します。 この漏洩は、だれかが GitHub のパブリック コード アーティファクトで認証情報にチェックインしたときに発生する場合があります。 そのため、この検出をシミュレートするには、GitHub アカウントが必要であり、 [GitHub アカウント](https://docs.github.com/get-started/signing-up-for-github) がまだない場合はサインアップできます。

#### ワークロード ID 用の GitHub の漏洩した資格情報をシミュレートする

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**App 登録に移動します**。
3. **[新規登録**] を選択して新しいアプリケーションを登録するか、既存の古いアプリケーションを再利用します。
4. **[証明書とシークレット**&gt;**新しいクライアント シークレット**] を選択し、クライアント シークレットの説明を追加し、シークレットの有効期限を設定するか、カスタム有効期間を指定して **、[追加]** を選択します。 後で GitHub コミットに使用するために、シークレットの値を記録します。

    注意

    **このページを終了した後は、シークレットを再度取得することはできません**。
5. **[概要**] ページで TenantID と Application(Client)ID を取得します。
6. **Entra ID**&gt;**Enterprise apps**&gt;**Properties**&gt; ユーザーが**サインインできるように [有効]** を **[いいえ**] に設定して、アプリケーションを無効にします。
7. **パブリック** GitHub リポジトリを作成し、次の構成を追加し、.txt 拡張子を持つファイルとして変更をコミットします。

    ```GitHub
      "AadClientId": "XXXX-2dd4-4645-98c2-960cf76a4357",
      "AadSecret": "p3n7Q~XXXX",
      "AadTenantDomain": "XXXX.onmicrosoft.com",
      "AadTenantId": "99d4947b-XXX-XXXX-9ace-abceab54bcd4",
    ```
8. 約 8 時間以内に、 **ID Protection**&gt;**Dashboard**&gt;**Risk Detections**&gt;**Workload id detections** の下で漏洩した資格情報の検出を表示できます。他の情報には GitHub コミットの URL が含まれています。

### Microsoft Graphを使用して侵害を確認する

また、Microsoft Graphを使用して、テスト目的でユーザーのリスク状態を "侵害の確認済み" に手動で設定することもできます。 この方法は、検出が発生するのを待たずにユーザー リスク ポリシーをトリガーする必要がある場合に便利です。

[confirmCompromised](https://learn.microsoft.com/ja-jp/graph/api/riskyuser-confirmcompromised) API を使用して、1 人以上のユーザーを侵害されたユーザーとしてマークします。

```http
POST https://graph.microsoft.com/v1.0/identityProtection/riskyUsers/confirmCompromised

{
  "userIds": [
    "userid1",
    "userid2"
  ]
}
```

この API を呼び出すと、ユーザーのリスク レベルが **高** に設定され、リスク状態 **が confirmedCompromised** に変わります。 この変更はリスク レポートに反映され、高いユーザー リスク用に構成された条件付きアクセス ポリシーがトリガーされます。

注意

`confirmCompromised`を使用すると、パスワードのリセットによって修復されるか、管理者がリスクを無視するまで、ユーザーのリスクが永続的に高くなります。 この方法は、テスト環境でのみ、または真の侵害を確認した場合にのみ使用します。

### リスク ポリシーのテスト

このセクションでは、「 [方法: リスク ポリシーを構成して有効にする」](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)の記事で作成したユーザーとサインイン リスク ポリシーをテストする手順について説明します。

#### ユーザー リスクのポリシー

ユーザー リスク セキュリティ ポリシーをテストするには、次の手順に従います。

1. テストする予定のユーザーを対象とするユーザー [リスク ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#user-risk-policy-in-conditional-access) を構成します。
2. テスト アカウントのユーザーのリスクを評価します。たとえば、リスク検出のいずれかを数回シミュレートします。
3. 数分待った後、そのユーザーのリスクが上昇したことを確認します。 そうでない場合は、そのユーザーに対するリスク検出をさらにシミュレートします。
4. リスク ポリシーに戻り、[**ポリシーの適用** **] を [オン]** に設定し、ポリシーの変更を**保存**します。
5. これで、リスク レベルを上げたユーザーを使用してサインインすることで、ユーザーのリスクに基づく条件付きアクセスをテストできます。

#### サインイン時のリスク セキュリティ ポリシー

サインイン リスク ポリシーをテストするには、次の手順に従います。

1. テスト対象のユーザーを対象とする [サインイン リスク ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#sign-in-risk-policy-in-conditional-access) を構成します。
2. これで、リスクの高いセッションを使用してサインインすることで (たとえば、Tor Browser を使用して)、サインインのリスクに基づく条件付きアクセスをテストできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/id-protection-dashboard"} -->
## Microsoft Entra ID 保護 の概要 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/id-protection-dashboard
- Service: entra-id-protection
- Article date: 2026-03-20
- Summary: Microsoft Entra ID 保護 の概要ダッシュボードで、セキュリティ体制を表示する方法について説明します。

Microsoft Entra ID 保護 は、ID 攻撃を検出し、リスクを報告することで、ID の侵害を防ぎます。 リスクを監視し、調査し、リスクベースのアクセス ポリシーを構成して機密性の高いアクセスを保護し、リスクを自動的に修復することで、組織を保護できます。

ダッシュボードは、お客様がセキュリティ態勢をより適切に分析し、保護の程度を理解し、脆弱性を特定し、推奨されるアクションを実行するのに役立ちます。

[Image: Microsoft Entra ID 保護 の概要ダッシュボードを示すスクリーンショット。]

このダッシュボードは、テナントに合わせて調整された豊富な分析情報と実用的な推奨事項を組織に提供します。 この情報は、組織のセキュリティ体制をより適切に把握し、それに応じて効果的な保護を有効にすることができます。 主要なメトリック、攻撃グラフィック、危険な場所を強調表示するマップ、セキュリティ態勢を強化するための上位の推奨事項、最近のアクティビティにアクセスできます。

### 前提条件

このダッシュボードにアクセスするには、次のものが必要です。

- ユーザー向けの Microsoft Entra ID Free、Microsoft Entra ID P1、または Microsoft Entra ID P2 のライセンス。
- 推奨事項の包括的な一覧を表示し、推奨されるアクション リンクを選択するための Microsoft Entra ID P2 ライセンス。
- 一部のリスク検出用の Microsoft 365 E5 または Microsoft Enterprise Mobility + Security E5 ライセンス。 詳細については、[Microsoft Entra ID 保護 の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#microsoft-defender)に関する記事を参照してください。

### ダッシュボードにアクセスする

ダッシュボードには、次のようにしてアクセスできます。

1. **[Microsoft Entra 管理センター](https://entra.microsoft.com)**に[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)以上としてサインインします。
2. **ID Protection**&gt;**Dashboard** に移動します。

#### メトリック カード

リスクベースのポリシーなどのより多くのセキュリティ対策を実装すると、テナントの保護が強化されます。 Microsoft では、実施されているセキュリティ対策の有効性を理解するのに役立つ 4 つの主要なメトリックを提供します。

[Image: ダッシュボードのメトリック グラフを示すスクリーンショット。]

| メトリック | メトリック定義 | 更新の頻度 | 詳細を確認する場所 |
| --- | --- | --- | --- |
| Number of attacks blocked (ブロックされた攻撃の数) | このテナントで毎日ブロックされた攻撃の数。  危険なサインインがアクセス ポリシーによって中断された場合、攻撃はブロックされたものと見なされます。 ポリシーにより要求されるアクセス制御では、攻撃者のサインインをブロックし、リアルタイムで攻撃をブロックする必要があります。 | 24 時間ごと | **[リスク検出レポート]** で、次の方法で "リスク状態" をフィルター処理して、攻撃を特定したリスク検出を表示します。 - **修復済み** - **Dismissed (破棄済み)** - **安全であるとの確認済み** |
| Number of users protected (保護されたユーザーの数) | リスク状態が **[At risk] (リスクあり)** から **[修復済み]** または **[Dismissed] (破棄済み)** に変更された、このテナント内のユーザーの数。 **[修復済み]** のリスク状態は、ユーザーが MFA またはセキュリティで保護されたパスワード変更を完了してリスクを自己修復し、そのアカウントが保護されていることを示します。 **[無視]** のリスク状態は、管理者が、ユーザーのアカウントが安全であることを識別したため、ユーザーのリスクを無視したことを示します。 | 24 時間ごと | **[危険なユーザー レポート]** で、次の方法で "リスク状態" をフィルター処理して、保護されているユーザーを表示します。 - **修復済み** - **Dismissed (破棄済み)** |
| Mean time your users take to self-remediate their risks (ユーザーがリスクを自己修復するのにかかった平均時間) | テナント内の危険なユーザーのリスク状態が **[At risk] (リスクあり)** から **[修復済み]** に変わる平均時間。  ユーザーのリスク状態は、ユーザーが MFA またはセキュリティで保護されたパスワードの変更を通じてリスクを自己修復したときに、**[修復済み]** に変わります。  テナント内の自己修復時間を短縮するには、リスクベースの条件付きアクセス ポリシーをデプロイします。 | 24 時間ごと | [危険なユーザー レポート] で、次の方法で "リスク状態" をフィルター処理して、修復済みユーザーを表示します。- 修復済み |
| Number of new high-risk users detected (検出された危険度の高い新しいユーザーの数) | リスク レベルが **[高]** と検出された新しい危険なユーザーの毎日の数。 | 24 時間ごと | [危険なユーザー レポート] で、次の方法でリスク レベルをフィルター処理して、リスクの高いユーザーを表示します。- "高" |

次の 3 つのメトリックのデータ集計は 2023 年 6 月 22 日から開始されているため、これらのメトリックはその日付から使用できます。 Microsoft では、これを反映するようにグラフの更新に取り組んでいます。

- Number of attacks blocked (ブロックされた攻撃の数)
- Number of users protected (保護されたユーザーの数)
- Mean time to remediate user risk (ユーザー リスクの平均修復時間)

グラフは、移動する12か月間のデータウィンドウを示します。

#### 攻撃グラフィック

リスクにさらされるリスクをよりよく理解するために、攻撃グラフィックにはテナントについて検出された一般的な ID ベースの攻撃パターンが表示されます。 攻撃パターンは MITRE ATT&CK 手法によって表され、Microsoft の高度なリスク検出によって特定されます。 詳細については、「リスク検出の種類と MITRE 攻撃の種類のマッピング」セクションを参照してください。

[Image: ダッシュボードの攻撃グラフィックを示すスクリーンショット。]

##### Microsoft Entra ID 保護 の攻撃と見なされるのは何ですか?

攻撃とは、悪意のあるアクターが環境にサインインしようとしていることを検出するイベントです。 このイベントにより、対応する MITRE ATT&CK 手法にマップされるリアルタイム サインイン [リスク検出](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-detection-types)がトリガーされます。 Microsoft Entra ID 保護のリアルタイム サインイン リスク検出と、MITRE ATT&CK 手法によって分類される攻撃のマッピングについては、下の表を参照してください。

攻撃グラフではリアルタイム サインイン リスク アクティビティのみが示されるため、[危険なユーザー アクティビティ](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#user-risk-detections-mapped-to-riskeventtype)は含まれません。 環境内の危険なユーザー アクティビティを目に見えるようにするには、[危険なユーザー レポート](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/IdentityProtectionMenuBlade/%7E/RiskyUsers/fromNav/)を使用できます。

##### 攻撃グラフィックを解釈する方法

このグラフィックは、過去 30 日間にテナントに影響を与えた攻撃の種類と、サインイン中にブロックされたかどうかを示しています。 左側には、各攻撃の種類のボリュームが表示されます。 右側には、ブロックされた攻撃とまだ修復されていない攻撃の数が表示されます。 グラフは 24 時間ごとに更新され、リアルタイムで発生する危険なサインインの検出がカウントされます。そのため、攻撃の合計数は検出の合計数と一致しません。

- ブロック済み: 関連する危険なサインインが、多要素認証の要求などのアクセス ポリシーによって中断された場合、攻撃はブロック済みとして分類されます。 このアクションにより、攻撃者のサインインを防ぎ、攻撃をブロックします。
- 修復されていない: 危険なサインインは中断されずに成功しており、修復が必要です。 そのため、これらの危険なサインインに関連付けられたリスク検出でも修復が必要です。 これらのサインインと関連するリスク検出は、[At risk] (リスクあり) のリスク状態でフィルター処理することで、危険なサインイン レポートで表示できます。

##### 攻撃はどこで確認できますか?

攻撃の詳細を表示するには、グラフの左側にある攻撃の数を選びます。 このグラフでは、その攻撃の種類でフィルター処理されたリスク検出レポートが表示されます。

リスク検出レポートに直接移動し、**攻撃の種類**でフィルター処理できます。 攻撃と検出の数は、1 対 1 に対応してはいません。

#### リスク検出の種類と MITRE 攻撃の種類のマッピング

| リアルタイム サインイン リスク検出 | 検出の種類 | MITRE ATT&CK 手法のマッピング | 攻撃の表示名 | タイプ |
| --- | --- | --- | --- | --- |
| 異常なトークン | リアルタイムまたはオフライン | T1539 | Steal Web Session Cookie/Token Theft (Web セッション Cookie/トークンを盗む) | Premium |
| 見慣れないサインイン プロパティ | リアルタイム | T1078 | Access using a valid account (有効なアカウントを使用したアクセス) (サインイン時に検出) | Premium |
| 検証済み脅威アクター IP | リアルタイム | T1078 | Access using a valid account (有効なアカウントを使用したアクセス) (サインイン時に検出) | Premium |
| 匿名 IP アドレス | リアルタイム | T1090 | Obfuscation/Access using proxy (プロキシを使用した難読化/アクセス) | 非プレミアム |
| Microsoft Entra の脅威インテリジェンス | リアルタイムまたはオフライン | T1078 | Access using a valid account (有効なアカウントを使用したアクセス) (サインイン時に検出) | 非プレミアム |

#### マップ

テナントでの危険なサインインの地理的な場所を表示するマップが用意されています。 バブルのサイズは、その場所でのリスク サインインの量を反映しています。 バブルの上にマウス ポインターを合わせると、コールアウト ボックスが表示され、国名とその場所からの危険なサインインの数が示されます。

[Image: ダッシュボードのマップ グラフィックを示すスクリーンショット。]

これには次の要素が含まれています。

- 日付範囲: 日付範囲を選択し、マップ上のその時間範囲内からの危険なサインインを表示します。 使用できる値は、過去 24 時間、過去 7 日間、過去 1 か月間です。
- リスク レベル: 表示する危険なサインインのリスク レベルを選択します。 使用可能な値は、High (高)、Medium (中)、Low (低) です。
- **危険な場所**の数:
    - 定義: テナントの危険なサインインの発生元の場所の数。
    - 日付範囲とリスク レベル フィルターは、このカウントに適用されます。
    - この数を選択すると、選択した日付範囲とリスク レベルでフィルター処理された危険なサインイン レポートが表示されます。
- **危険なサインイン**の数:
    - 定義: 選択した日付範囲の選択したリスク レベルを持つ危険なサインインの合計数。
    - 日付範囲とリスク レベル フィルターは、このカウントに適用されます。
    - この数を選択すると、選択した日付範囲とリスク レベルでフィルター処理された危険なサインイン レポートが表示されます。

#### 推奨事項

Microsoft Entra ID 保護の推奨事項は、お客様がセキュリティ態勢を強化するために環境を構成するのに役立ちます。 これらの推奨事項は、過去 30 日間にテナントで検出された攻撃に基づいています。 推奨事項は、セキュリティ スタッフに推奨される対処方法をガイドするために提供されます。

[Image: ダッシュボードの推奨事項を示すスクリーンショット。]

パスワード スプレー、テナント内の資格情報の漏洩、機密ファイルへの大量アクセスなど、一般的な攻撃は、侵害の可能性を示している場合があります。 前のスクリーンショットにある例の **Identity Protection detected at least (ID 保護でテナント内の少なくとも 20 ユーザーの資格情報漏洩を検出しました)** の場合、推奨されるアクションは、危険なユーザーに対してセキュリティで保護されたパスワード リセットを要求する条件付きアクセス ポリシーを作成することです。

ダッシュボードの推奨事項コンポーネントには、次の情報が表示されます。

- テナントで特定の攻撃が発生した場合は、最大 3 つの推奨事項。
- 攻撃の影響に関する分析情報。
- 修復のための適切なアクションを実行するための直接リンク。

P2 ライセンスをお持ちのお客様は、分析情報とアクション提供する推奨事項の包括的な一覧を表示できます。 [すべて表示] を選ぶと、環境内の攻撃に基づいてトリガーされたその他の推奨事項が表示されるパネルが開きます。

#### 最近のアクティビティ

[最近のアクティビティ] には、テナント内の最近のリスク関連アクティビティの概要が表示されます。 表示される可能性があるアクティビティの種類は以下のとおりです。

1. 攻撃アクティビティ
2. 管理者の是正活動
3. 自己修復アクティビティ
4. 新しいリスクの高いユーザー

[Image: ダッシュボードの最近のアクティビティを示すスクリーンショット。]

### 統合されたリスクシグナル

Microsoft Entra ID 保護は、Microsoft Entra ID 保護、Microsoft Defender、およびその他のMicrosoftセキュリティ製品からの相関リスクシグナルを集計する統合されたリスクシグナルを提供します。 この機能は、個別にアラートを評価する代わりに、製品間で ID 関連のシグナルを関連付け、それらを同じ時間枠内でまとめて評価し、複合ユーザー リスク スコアを計算します。

そのためには、Microsoft Defender for Identityを構成する必要があります。 統合リスクのしくみ、それを有効にする方法、一般的な問題のトラブルシューティング方法の詳細については、[Microsoft Entra ID 保護の統合リスクシグナルを](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-unified-risk)参照してください。

### 既知の問題

テナントの構成によっては、ダッシュボードに推奨事項や最近のアクティビティがない場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/id-protection-faq"} -->
## Microsoft Entra ID 保護 よくある質問 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/id-protection-faq
- Service: entra-id-protection
- Article date: 2026-03-17
- Summary: Microsoft Entra ID 保護 に関してよく寄せられる質問。

この記事には、Microsoft Entra ID 保護 に関してよく寄せられる質問への回答が含まれています。 FAQ セクションには、検出、漏洩した資格情報、修復、ライセンス、B2B ユーザーが含まれます。

詳細については、「 [Microsoft Entra ID 保護 の概要」を](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)参照してください。

### Detections

#### リスクをシミュレートして、ユーザーにフラグが設定されている理由を理解するにはどうすればよいですか?

[リスクをシミュレートする方法](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-simulate-risk)のガイドがあります。 このガイドでは、さまざまな種類のリスクをシミュレートするためのいくつかのオプションを提供します。 [また、条件付きアクセス WhatIf](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool) ツールを使用して、特定のポリシーがどのように適用されるかを確認することもできます。 また、 **レポート専用** モードで条件付きアクセス ポリシーを有効にして、必要なリソースにアクセスするユーザーの機能に影響を与えることなく、テナントでポリシーがどのように機能するかを理解することをお勧めします。

#### 高リスクの警告を受け取ったばかりですが、"リスクベースのポリシーの影響分析" ワークブックに表示されません。 Why?

ユーザーに高リスクが割り当てられているが、まだサインインしていない場合、このレポートには表示されません。 このレポートでは、サインイン ログのみを使用してこのデータを設定しています。

#### 不正な資格情報を使用してサインインを試みた場合はどうなりますか?

Identity Protection は、正しい資格情報が使用されている場合にのみ、リスク検出を生成します。 サインイン時に間違った資格情報が使用されている場合に、資格情報の侵害のリスクを示すものではありません。

#### パスワード ハッシュ同期の要件とは

資格情報漏洩などのリスク検出では、検出を行うため、パスワード ハッシュが存在する必要があります。 パスワード ハッシュ同期の詳細については、「 [Microsoft Entra Connect Sync を使用したパスワード ハッシュ同期の実装](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)」を参照してください。

#### 無効なアカウントに対してリスク検出が生成されるのはなぜですか?

まず、無効な状態のユーザー アカウントを再び有効にできることを知る必要があります。 無効になっているアカウントの資格情報が侵害され、そのアカウントが再度有効になった場合、不正なアクターがアクセス権を取得するためにそれらの資格情報を使用する可能性があります。 ID Protection は、アカウント侵害の可能性があることをお客様に警告するために、これらの無効なアカウントに対する不審なアクティビティのリスク検出を生成します。

アカウントが使用されなくなり、再び有効にならない場合は、侵害を防ぐためにアカウントを削除することを検討する必要があります。 削除されたアカウントに対してリスク検出は生成されません。

#### [検出時間] 列を使用してリスク検出レポートを並べ替えようとしましたが、機能していません。

リスク検出レポートの *検出時間* による並べ替えでは、既知の技術的制約があるため、常に正しい結果が得られるとは限りません。 *検出時間*で並べ替えるには、Microsoft Entra 管理センターでレポートを開き、[**ダウンロード**] を選択してデータを CSV ファイルとしてエクスポートし、それに応じて並べ替えます。

#### Microsoft は、漏洩した資格情報をどこで検出するのですか? どのくらいの頻度で処理されますか?

Microsoft は、次のようなさまざまなソースで侵害された資格情報を継続的に検出する大規模な資格情報スキャン パイプラインを運用しています。

- 盗まれた資格情報が取引され販売されるダーク Web フォーラムと地下マーケットプレース。
- 悪意のある攻撃者が盗んだ資格情報を投稿する、Breach Dump リポジトリや公開ペースト サイト。
- 調査中に押収された資格情報データベースを共有する法執行機関。
- Microsoft Threat Intelligence Center (MSTIC) と、脅威アクターの追跡に特化した研究チーム。
- サイバー犯罪インフラストラクチャを中断し、盗まれたデータを回復する Microsoft Digital Crimes Unit (DCU)。
- セキュリティ研究者との業界パートナー インテリジェンスの共有とコラボレーション。

#### 漏洩した資格情報の検出のしくみ

新しい漏洩した資格情報が任意のソースから検出されると、Microsoft の漏洩した資格情報サービスに取り込み、1 日に複数のバッチで処理されます。 サービスは、実際の資格情報ペア (パスワードマテリアルを含む) を受け取り、テナントの現在の有効なパスワード ハッシュに対して検証します。 この直接資格情報の検証によって、検出の再現性が高くなります。 検出は、検出された資格情報がユーザーの現在のパスワードと一致することが確認された場合にのみ生成されます。 プレーンテキスト パスワードはどの時点でも保存されず、検出されたすべての資格情報データは処理の直後に削除されます。

一致が確認されると、ID Protection は、ヒューリスティックまたは確率的なシグナルではなく、検証された資格情報の公開を表しているため、影響を受けるユーザーに **高リスク** のフラグを設定します。 リスクベースの条件付きアクセス ポリシーでは、侵害された資格情報を悪用する前に、セキュリティで保護されたパスワードの変更を自動的に要求できます。 Microsoft Entra によるクラウドベースのパスワード リセットにより、この検出のユーザー リスクが完全に修復されます。 Microsoft Defender for Identity では、オンプレミスのパスワードに対する漏洩した資格情報の検出も表示されるようになりました。 詳細については、「 [アカウントのセキュリティ体制の評価」](https://learn.microsoft.com/ja-jp/defender-for-identity/security-posture-assessments/accounts#change-password-for-on-premises-account-with-potentially-leaked-credentials-preview)を参照してください。

漏洩した資格情報の検出では、ハイブリッド ユーザーに対して [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization) を有効にする必要があります。 PHS が有効になった **後** に検出された資格情報のみが、テナントと照合されます。 以前に検出された資格情報ペアはさかのぼってチェックされません。

#### 漏洩した資格情報が表示されないのはなぜですか?

漏洩した資格情報は、Microsoft が公開されている新しいバッチを検出するたびに処理されます。 その機密性のため、漏洩した資格情報は、処理の直後に削除されます。 パスワード ハッシュ同期 (PHS) を有効にした **後** に検出された新しい漏洩した資格情報のみが、テナントに対して処理されます。 前に検出されていた資格情報ペアに対する検証は実行されません。

テナントの ID 保護で漏洩した資格情報がどのように表示されるかを確認するには、GitHub for Workload Ids で漏洩した資格情報をシミュレートしてみてください。 詳細については、「 [Microsoft Entra ID 保護 でリスク検出をシミュレートする」を](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-simulate-risk#leaked-credentials-for-workload-identities)参照してください。

漏洩した資格情報リスク イベントが確認されていない場合、次のような理由が原因です。

- テナントに対してパスワード ハッシュ同期が有効になっていません。
- お客様のユーザーと一致する、漏洩した資格情報のペアを Microsoft が見つけていません。

### Remediation

#### ID Protection では、リスクベースのポリシーがない場合に、サインイン リスクが自動的に修復されるのはいつですか?

ID Protection は、ユーザーがリスクの高いサインインの後に多要素認証 (MFA) などの第 2 要素をすぐに提供すると、サインイン リスクを自動修復します。 この 2 つ目の要素を指定すると、強力な認証が構成されます。

ID Protection では、サインイン時のリスク評価用に 2 つのモデルも動作します。1 つ目は、サインイン時に限られた情報を使用して迅速な判断を行うリアルタイム モデルです。 2 つ目は、サインインが完了したら他のサインイン データを使用するオフライン モデルです。 オフライン モデルは、リスク スコアを安全として再評価すると、リスクを閉じる可能性があります。 この評価は、リスク レベルのリアルタイム検出とオフライン検出に適用されます。

#### リスクベースのポリシーが存在しない場合に、ID Protection がユーザー リスクを自動修復するのはいつですか。

ID Protection は、ユーザー リスクの昇格なしで 6 か月後にユーザー リスクが低いユーザーを自動解決します。 リスクが中程度または高の危険なユーザーは、リスク ポリシーまたは管理者の解雇なしでは自動解決されません。

#### Microsoft 以外のプロバイダーを多要素認証に使用する場合はどうすればよいですか?

Microsoft Entra 多要素認証を使用しない場合でも、Microsoft 以外の MFA プロバイダーを使用している場合は、環境内でサインイン リスクが修復される可能性があります。 [外部認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage)を使用すると、Microsoft 以外の MFA プロバイダーを使用する場合のリスクを修復できるようになります。

#### フェデレーション認証を使用する場合

フェデレーション認証を構成した場合でも、リスクベースの条件付きアクセスは承認レイヤーで動作し、引き続き有効です。 多要素認証に Microsoft Entra ID を使用する場合、MFA の修復アクションは Microsoft Entra ID によって通常どおりにトリガーされます。 フェデレーションの認証プロバイダー側で MFA が構成されている場合は、修復アクションとして認証**強度**の[フェデレーション多要素](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths)要素を使用できます。

#### エンド ユーザーに多要素認証をロールアウトしていない場合はどうすればよいでしょうか。

最初の手順として MFA をロールアウトすることを強くお勧めします。 MFA をすぐに実装できない場合は、リスクの [高いユーザー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies) を軽減策としてブロックするコントロールを適用します。

#### パスワードレスのアカウントに最適な条件付きアクセス制御は何ですか?

フィッシングに強いパスワードレス認証の展開に関するガイダンスを確認する: [フィッシングに対するパスワードレス認証の展開を開始する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)

#### リスクの高い条件付きアクセス ポリシーに "サインイン頻度を強制する - 毎回" を追加する必要がありますか?

この設定は、組織のリスク許容度によって異なります。 組織によっては、高リスクをブロックすることを選択する場合があります。 サインインを毎回強制すると、サインインや MFA の疲労が発生し、フィッシング攻撃の可能性が高くなる可能性があります。

また、 **レポート専用** モードで条件付きアクセス ポリシーを有効にして、必要なリソースにアクセスするユーザーの機能に影響を与えることなく、テナントでポリシーがどのように機能するかを理解することをお勧めします。 ID Protection の[**リスクベースのポリシーブックの影響分析**](https://learn.microsoft.com/ja-jp/entra/id-protection/workbook-risk-based-policy-impact)を確認することもできます。

#### ハイブリッド環境の場合はどうすればよいですか?

ユーザー リスクは、セキュリティで保護されたパスワード変更 (MFA + パスワードの変更) を使用して自己修復できます。 このフローでは、セルフサービス パスワード リセット (SSPR) を有効にする必要はありません。ただし、オンプレミスまたはハイブリッド参加済みのWindows デバイスを持つハイブリッド ユーザーの場合は、パスワード ハッシュ同期を有効にする必要があります。 [オンプレミスのパスワード変更を有効にしてユーザー リスクをリセットし](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock#allow-on-premises-password-reset-to-remediate-user-risks)、オンプレミスのパスワードの変更もユーザー リスクを修復することを検討してください。

詳細については、「 [リスクを修復し、ユーザーのブロックを解除する方法](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock#considerations-for-cloud-and-hybrid-users)」を参照してください。

#### リスク ポリシーを展開した場合、システムはユーザーを異なる方法で自動修復するか、過去の修復をやり直す必要がありますか?

システムは、すべての修復に対して条件付きアクセス ポリシーで指定された許可コントロールに依存します。 このポリシー駆動型のアプローチでは、リソースへのアクセスをより詳細に制御できます。 ユーザー リスク ポリシーにブロックが必要な場合は、条件付きアクセスによって適用されます。 サインイン リスク ポリシーで MFA が必要な場合は、条件付きアクセスによって新しい MFA チャレンジが適用されます (既存のトークンに MFA 要求がない場合)。 そのため、Alice が別の MFA ポリシーのおかげで常にリスクの高いサインインを自動修復していた場合、MFA を必要とするリスク ポリシーをデプロイしても、ユーザー エクスペリエンスは何も変更されません。

### Licensing

#### Microsoft Entra ID 保護 を使用するには、どのようなライセンスが必要ですか?

Microsoft Entra ID 保護 には、さまざまなライセンスを必要とする機能がいくつかあります。 たとえば、Microsoft Entra ID Free ライセンスを使用して危険なサインイン レポートに限られた情報を取得できますが、Microsoft Entra ID P2 または Microsoft Entra スイート ライセンスを使用してこれらのレポートにフル アクセスできます。 詳細については、 [Microsoft Entra ID 保護 のライセンス要件](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements)を参照してください。

Microsoft Entra ID 保護 では、個別にライセンスが付与された Microsoft Defender 製品からのシグナルも使用されます。 詳細については、[Microsoft Entra ID 保護 の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#microsoft-defender)に関する記事を参照してください。

### B2B ユーザー

#### ディレクトリ内の危険な B2B コラボレーション ユーザーを修復できないのはなぜですか?

B2B ユーザーのリスク評価と修復はホーム ディレクトリで行われるため、ゲスト ユーザーはリソース ディレクトリの危険なユーザー レポートに表示されません。 リソース ディレクトリの管理者は、これらのユーザーに対してセキュリティで保護されたパスワードの変更を強制することはできません。

#### 組織内のリスクベース ポリシーによって B2B コラボレーション ユーザーがブロックされた場合はどうすればよいですか?

ディレクトリ内の危険な B2B ユーザーがリスクベースのポリシーによってブロックされた場合、そのユーザーは自分のホーム ディレクトリでそのリスクを修復する必要があります。 ユーザーは、ホーム ディレクトリで安全なパスワード変更 (MFA + パスワードの変更) を実行することで、リスクを修復できます。 このフローでは、セルフサービス パスワード リセット (SSPR) を有効にする必要はありません。 ユーザーが安全なパスワードの変更を完了できない場合は、管理者が自分のリスクを手動で無視したり、パスワードを変更したりするには、自分の組織の IT スタッフに連絡する必要があります。 詳細については、「 [ユーザー リスクの修復](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)」を参照してください。

#### リスクベースのポリシーが B2B コラボレーション ユーザーに影響を与えないようにするにはどうすればよいですか?

B2B ユーザーを組織のリスクベースの条件付きアクセス ポリシーから除外すると、B2B ユーザーがリスク評価の影響を受けなくなります。 これらの B2B ユーザーを除外するには、組織のすべてのゲストユーザーを含むグループを Microsoft Entra ID に作成します。 その後、このグループを、組み込みの ID Protection ユーザー リスク ポリシーとサインイン リスク ポリシー、およびサインイン リスクを条件として使用している条件付きアクセス ポリシーの除外対象として追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/identity-risk-management-agent-get-started"} -->
## ID リスク管理エージェント - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-get-started
- Service: entra-id-protection
- Article date: 2025-12-01
- Summary: ID リスク管理エージェントと、Microsoft Entra ID 保護内のリスクを特定して軽減する役割について説明します。

IT 管理者とセキュリティ アナリストは、ますます複雑化する環境を管理しながら、脅威を迅速に特定して対応するための高い圧力に直面しています。 多くの場合、大量のアラートに圧倒され、すぐに注意が必要なリスクに優先順位を付けるのに苦労し、組織のシステム全体に散在するデータ ポイントを接続するのが難しいと感じます。 Microsoft Entra の Identity Risk Management Agent と Security Copilot は、これらの専門家が潜在的なリスクを調査し、その効果を理解し、組織の重要な資産を保護するための決定的なアクションを実行するのに役立ちます。

注

Identity Risk Management エージェントは現在デプロイ中であり、プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### [前提条件]

- 少なくとも [Microsoft Entra ID P2](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) ライセンスが必要です。
- 使用可能な [セキュリティ コンピューティング ユニット (SCU)](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage)が必要です。
    - 平均して、各エージェントの実行で消費される SCU は 1 つ未満です。
- 適切な Microsoft Entra ロールが必要です。
    - [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)は、エージェントを初めて*アクティブ化する*必要があり、エージェントを表示して提案に基づくアクションを実行する必要があります。
    - [Security Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) ロールと [Global Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) ロールは*エージェントと提案を表示できますが、アクションを実行することはできません*。
    - 詳細については、「[Assign Security Copilot access](https://learn.microsoft.com/ja-jp/copilot/security/authentication#assign-security-copilot-access)」を参照してください。
- [Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/copilot/security/privacy-data-security)のデータ セキュリティを確認します。

#### 既知の制限事項

- 現在、各エージェントの実行では、最大 100 人のリスクのあるユーザーが調査されています。 この制限内でスコープをカスタマイズするには、エージェント スコープ設定を使用します。
- エージェントの実行が開始されると、停止または一時停止することはできません。 100 人のユーザーで実行が完了するまでに 10 ~ 15 分かかる場合があります。
- エージェントは現在、ユーザー ID のみを分析します。 現時点では、ワークロード ID に関するエージェント分析はサポートされていません。
- エージェントの提案には、管理者の手動承認が必要です。 現時点では、自動修復はサポートされていません。
- サインイン ログ、リスク検出、危険なユーザー、監査ログなど、Microsoft Entra データに対するエージェントの理由。
- 調査の概要と推奨事項は AI によって生成され、不完全または正しくない可能性があります。 適用前に確認し、変更を適用するときに人間の判断を使用します。

### 動作方法

エージェントが、以前に識別されなかった新しい危険な ID を識別する場合は、次の手順を実行します。 **最初のスキャン手順では、SCU は使用されません。**

1. エージェントは、現在リスク状態が "危険" であるテナント内の新しい危険なユーザーをチェックします。
2. エージェントは、定義された [スコープ設定](https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-settings#scope)内にある危険なユーザーを識別します。

エージェントが、以前に提案されていないものを識別した場合は、次の手順を実行します。 **これらのエージェント アクションステップでは、SCU が使用されます。**

1. **危険なユーザーを調査**する: エージェントは、ユーザーの危険なサインインとリスク検出をチェックして、このユーザーに関するリスクを分析します。
2. **結果とリスクの概要を生成します。** エージェントは、調査に基づいて結果を生成します。これには、提案を説明し、主要なリスク要因を定義する完全なリスク概要が含まれます。
3. **推奨される修復アクションを生成**する: エージェントは、調査中に収集された情報を使用して修復アクションを提案します。
4. **チャットで質問に回答**する: IT 管理者は、危険なユーザーとリスクの概要に関連するエージェントの質問をします。
5. **カスタム命令をエージェント メモリに格納**する: お客様は、エージェント チャットを通じてエージェントのカスタム命令を指定できます。エージェントはメモリに格納され、今後の実行に適用されます。 現時点では、エージェント メモリは推奨される修復の推奨事項を格納できます。

注

エージェント メモリには現在、推奨されるアクションのみが格納されます。 修復は自動的には実行されません。代わりに、カスタム命令を使用してエージェントの推奨事項を更新します。

### はじめに

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Security Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) としてサインインします。
2. **[ID Protection (ID 保護)]**&gt;**[Risky users (危険なユーザー)]** に移動します。
3. レポートの上部にあるバナー メッセージから [ **エージェントの開始** ] を選択して、最初の実行を開始します。

    - PIM によってアクティブ化されたロールを持つアカウントを使用しないでください。
    - 画面の右上に "エージェントが最初の実行を始めています" というメッセージが表示されます。
    - 最初の実行が完了するまでに数分かかる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/identity-risk-management-agent-risky-user-report"} -->
## 危険なユーザー レポートでエージェントの結果を確認する - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-risky-user-report
- Service: entra-id-protection
- Article date: 2025-11-08
- Summary: ID リスク管理エージェントが Microsoft Entra ID 保護 の危険なユーザー レポートと連携する方法について説明します

Microsoft Entra ID 保護の ID リスク管理エージェント (プレビュー) は、リスクの高い ID を分析し、それらを修復するためのアクションを提案することで、プロアクティブなリスク管理機能を提供します。 大規模言語モデルを使用することで、エージェントはセキュリティ管理者がセキュリティ インシデントに至る前に、危険なアクティビティを確認して対応するのに役立ちます。

注

Identity Risk Management エージェントは現在デプロイ中であり、プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### [前提条件]

- 少なくとも [Microsoft Entra ID P2](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) ライセンスが必要です。
- 使用可能な [セキュリティ コンピューティング ユニット (SCU)](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage)が必要です。
    - 平均して、各エージェントの実行で消費される SCU は 1 つ未満です。
- 適切な Microsoft Entra ロールが必要です。
    - [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)は、エージェントを初めて*アクティブ化する*必要があり、エージェントを表示して提案に基づくアクションを実行する必要があります。
    - [Security Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) ロールと [Global Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) ロールは*エージェントと提案を表示できますが、アクションを実行することはできません*。
    - 詳細については、「[Assign Security Copilot access](https://learn.microsoft.com/ja-jp/copilot/security/authentication#assign-security-copilot-access)」を参照してください。
- [Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/copilot/security/privacy-data-security)のデータ セキュリティを確認します。

### リスキー ユーザー レポートでエージェントの調査結果を確認する

Identity Risk Management エージェントは、既存のワークフローをサポートするために、Microsoft Entra ID 保護の危険なユーザー レポートに直接統合されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Security Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) としてサインインします。
2. **[ID Protection (ID 保護)]**&gt;**[Risky users (危険なユーザー)]** に移動します。
3. レポートの上部にある **[エージェント ビュー** ] オプションを選択します。

危険なユーザー レポートのエージェント ビューの上半分には、最近のエージェント アクティビティの概要が含まれています。 このタイルでは、**Chat with agent** 機能へのクイックアクセス、1 回限りの実行を行うためのオプション、およびエージェント設定へのリンクが提供されます。 レポートの下半分には、危険なユーザーの完全な一覧が含まれています。 一覧からユーザーを選択すると、そのユーザーに固有のエージェントの分析情報と提案が表示されます。

### エージェントの概要

エージェントの概要がエージェント ビューの上部に表示され、最近のエージェント アクティビティが表示されます。 このタイルでは、**エージェントとのチャット**機能と**エージェントの管理**ボタンに簡単にアクセスできます。これにより、一度きりの実行をトリガーしたり、エージェントの設定を開いたりすることができます。

### エージェントの提案

エージェントの候補は、エージェントの概要の下に表示されます。 提案にカーソルを合わせると、テーブル内の影響を受けるユーザーが強調表示されます。 提案を選択すると、テーブルがフィルター処理され、レビュー対象のユーザーのみが表示されます。 各候補には一括アクション ボタンが含まれているため、1 回のクリックでアクションを適用できます。

現在、エージェントの提案では、次の修復アクションを使用できます。

- リスクを無視する
- [パスワードのリセット]

### エージェント候補を含む危険なユーザー テーブル

レポートの下半分には、すべての危険なユーザーが一覧表示されます。 エージェントの結果、リスク要因、そのユーザーに固有の提案を表示するユーザーを選択します。 **エージェント**候補列には、推奨される修復アクションもテーブルに直接表示されます。 アクション ボタンを選択して、個々のユーザーに修復を適用します。

### 危険なユーザーの詳細

**危険なユーザーの詳細**ページには、危険なユーザーに固有のエージェントの結果が表示される新しいエージェント **ビュー**が表示されます。 このビューには、次の情報が含まれています。

- **基本的なユーザー情報**: ユーザー名、現在のリスク レベル、UPN
- **エージェントの結果**: エージェントは、調査に基づいて*、侵害されたか* *侵害されていない*かの判定を提供します。
- **リスクの概要**: ユーザーのサインインと動作の分析に基づいて、エージェントの結果の詳細な説明。
- **リスク要因**: 簡単に確認できるようにまとめられた主要なリスクインジケーター。
- **推奨される修復アクション**: リスクの修復をすばやく開始できる行動喚起ボタン。

**標準ビュー**[Image: リスクの高いユーザーの詳細の標準ビューのスクリーンショット。]

**エージェント ビュー**[Image: リスクの高いユーザーの詳細のエージェント ビューのスクリーンショット。]

エージェントの結果、概要、およびリスク要因は AI によって生成されるため、動的です。 言語は実行によって異なる場合があります。

危険なユーザーの詳細のエージェント ビューの下半分には、最近のアクティビティが表示されます。 このセクションには、次の視覚化が含まれています。

- **ユーザーのリスク レベル グラフ**: 過去 7 日間のユーザーのリスク レベルの履歴傾向。変更を理解するのに役立ちます。
- **ユーザーの過去のサインイン数**: 過去 7 日間のこのユーザーのサインイン数。
- **危険なサインイン マップ**: このユーザーの危険なサインインの場所を示すマップ。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/identity-risk-management-agent-settings"} -->
## ID リスク管理エージェントの設定 - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-settings
- Service: entra-id-protection
- Article date: 2025-10-20
- Summary: Microsoft Entra ID Protection で Identity Risk Management エージェントの設定を構成する方法について説明します。

Microsoft Entra ID Protection の ID リスク管理エージェントは、ユーザーの行動を分析し、潜在的な ID リスクを軽減するためのアクションを提案することで、プロアクティブなリスク管理機能を提供します。 実行頻度や電子メール通知など、組織のニーズに合わせて設定を構成できます。

注

Identity Risk Management エージェントは現在デプロイ中であり、プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### エージェント設定にアクセスする

エージェントが有効になったら、いくつかの設定を調整できます。 設定を確認して調整するには:

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **ID Protection**&gt;**Risky ユーザーに移動します**。
3. **[エージェント] ビュー**を選択した状態で、右上隅にある省略記号を選択し、[**設定]** を選択します。
4. エージェント ページで、[ **設定]** タブを選択します。設定は **、コントロール**、 **通信**、 **メモリ**に編成されます。
5. 変更を行った後、ページの下部にある **[保存]** を選択します。

Microsoft Entra エージェント ライブラリから設定にアクセスすることもできます。 左側のナビゲーションから **[エージェント** ] を選択し、[ **ID リスク管理エージェント**] を選択し、[ **設定]** タブを選択します。

#### コントロール

[ **制御]** セクションには、エージェントを実行するために必要なロールとアクセス許可が用意されています。 また、エージェントのトリガー方法とエージェントのスコープを調整することもできます。

##### トリガー

既定では、エージェントはテナントを継続的に監視しますが、頻度を変更したり、手動実行のみに設定したりすることもできます。 エージェントが毎日または手動で実行されるように設定されている場合、エージェントは、実行のたびに選択した受信者に 電子メール通知 を送信できます。

次のオプションを使用できます。

- **継続的な監視**: エージェントは、5 分ごとに新しい危険なユーザーをチェックし、自動的に調査します。
- **毎日のトリガー**: エージェントは 24 時間ごとに自動的に実行されます。
- **手動実行**: エージェントは、手動で開始されたときにのみ実行されます。

##### アクセス許可とロールベースのアクセス

エージェントには、リスク検出、リスク履歴、サインインと監査ログ、およびユーザー情報を読み取るために特定のアクセス許可が必要です。 これらのアクセス許可は、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールを通じて付与されます。

##### Scope

既定では、エージェントは過去 90 日以内に最新の 100 人の危険なユーザーを調査します。 複数のオプションを調整することで、エージェント スキャンのスコープを制御できます。

- 検索する **ユーザーとグループの選択** オプションを選択し、エージェントでスキャンするユーザーとグループを選択します。
- 最近のリスクの高いユーザーの最大数を 1 から 100 以内でスキャンするように設定します。
- スキャンに含めるリスク レベルを選択します。 既定では、すべてのリスク レベルが選択されます。
- スコープの特定の期間を設定します。
    - 過去 7 日間
    - 過去 14 日間
    - 過去 30 日間
    - 最大 90 日間のカスタム期間

#### 通信

Identity Risk Management エージェントは、選択した受信者に電子メール通知を送信できます。 通知は既定ではオンになっていません。

1. [ **電子メール通知** ] トグルを **[オン]** に設定します。
2. [**追加の受信者**] 見出しの下にある [**ユーザーが選択されていない**] リンクを選択して、受信者を検索して追加します。

#### 記憶

Identity Risk Management エージェントは、フィードバックを使用して、時間の経過に伴う提案を絞り込みます。 誤検知を "安全確認済み" としてマークすると、エージェントは今後の実行に関するフィードバックを記憶します。 自分とチームが提供した入力の履歴がこの一覧に表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/overview-identity-protection"} -->
## Microsoft Entra ID 保護とは - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection
- Service: entra-id-protection
- Article date: 2025-10-30
- Summary: Microsoft Entra ID 保護でリスク データの検出、修復、調査、分析の自動化

Microsoft Entra ID Protection を使うと、組織が ID に関するリスクを検出、調査、修復できます。 これらのリスクは、条件付きアクセスなどのツールに組み込んでアクセスの決定を行ったり、セキュリティ情報およびイベント管理 (SIEM) ツールに送信してさらに調査と相関関係を得ることができます。

[Image: ID 保護が高レベルで機能する方法を示す図。]

### リスクの検出

Microsoft は、組織を保護するために、カタログ内の検出に対して継続的に追加および更新を行っています。 これらの検出情報は、Active Directory、Microsoft アカウント、Xbox を使用したゲームから 1 日数兆に及ぶ信号の分析に基づく学習から得られるものです。 この広範囲の信号は、ID 保護が次のような危険な動作を検出できるようにします。

- 匿名 IP アドレスの使用
- パスワード スプレー攻撃
- 漏洩した資格情報
- その他...

ID Protection は、サインインのたびにすべてのリアルタイム サインイン検出を実行し、サインインが侵害される可能性を示すサインイン セッション リスク レベルを生成します。 このリスク レベルに基づいて、ユーザーと組織を保護するためのポリシーが適用されます。

リスクとその検出方法の完全な一覧については、「[リスクとは](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」の記事を参照してください。

### 調査

ID で検出されたすべてのリスクは、レポートを使用して管理されます。 ID 保護は、管理者がリスクを調査してアクションを実行するため、次の 3 つの主要なレポートが用意されています。

- **リスク検出:** 検出された各リスクは、リスク検出として報告されます。
- **危険なサインイン:** 危険なサインインは、そのサインインに対して 1 つ以上のリスク検出が報告されると報告されます。
- **危険なユーザー:**危険なユーザーは、次のいずれかまたは両方が成り立つ場合に報告されます:
    - ユーザーに 1 つ以上の危険なサインインがある。
    - 1 つ以上のリスク検出が報告された。

レポートの使用方法の詳細については、「[方法: リスクを調査する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)」の記事を参照してください。

### リスクを修復する

シグナルと攻撃の規模を維持するには自動化が必要であるため、セキュリティでは自動化が重要です。

[Microsoft Digital Defense Report 2024](https://cdn-dynmedia-1.microsoft.com/is/content/microsoftcorp/microsoft/final/en-us/microsoft-brand/documents/Microsoft%20Digital%20Defense%20Report%202024%20%281%29.pdf) には、次の統計情報が表示されます。

>
> 1日あたり78兆件のセキュリティ信号を分析し、前年から13兆件増加
>
> 1 日あたり 6 億件の Microsoft 顧客への攻撃
>
> 人間が操作するランサムウェア攻撃で前年比2.75倍増加

これらの統計は上昇傾向を続け、減速の兆候はありません。 この環境では、IT 組織が適切な優先順位に集中できるように、自動化がリスクの特定と修復の鍵となります。

#### 自動修復

[リスクベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)を有効にすると、強力な認証方法の提供などのアクセス制御を要求したり、多要素認証を実行したり、検出されたリスク レベルに基づく安全なパスワード リセットを実行したりできます。 ユーザーがアクセス制御を正常に完了すると、リスクは自動的に修復されます。

#### 手動による修復

ユーザーの修復が有効になっていない場合、管理者は、ポータル、API、または Microsoft Defender XDR のレポートで手動で確認する必要があります。 管理者は、リスクの無視、安全な確認、または侵害の確認を行う手動アクションを実行できます。

### データの活用

ID 保護からのデータは、アーカイブ、さらなる調査、関連付けのために他のツールにエクスポートすることができます。 Microsoft Graph ベースの API を使用すると、組織はこのデータを収集し、SIEM などのツールでさらに処理することができます。 ID 保護 API へのアクセス方法の詳細については、「[Microsoft Entra ID 保護と Microsoft Graph の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-graph-api)」の記事を参照してください

ID 保護の情報と Microsoft Sentinel の統合に関する詳細については、「[Microsoft Entra ID 保護でデータの接続](https://learn.microsoft.com/ja-jp/azure/sentinel/data-connectors-reference#microsoft)」の記事を参照してください。

組織は Microsoft Entra ID 内の診断設定を変更することにより、より長い期間にわたってデータを保存できます。 Log Analytics ワークスペースへのデータの送信、ストレージ アカウントへのデータのアーカイブ、Event Hubs へのデータのストリーミング、または別のソリューションへのデータの送信を選択できます。 その方法の詳細については、記事「[方法: リスク データをエクスポートする](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-export-risk-data)」を参照してください。

### 必要なロール

ID 保護では、ユーザーに次のロールの 1 つ以上を割り当てる必要があります。

| 役割 | できること | できないこと |
| --- | --- | --- |
| [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | ID 保護への読み取り専用アクセス | ID Protection への書き込みアクセス |
| [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | ユーザー パスワードのリセット | ID Protection の読み取りまたは書き込み |
| [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | ユーザーまたはサインインのリスクを条件として考慮するポリシーを作成する | 従来の ID 保護ポリシーの読み取りまたは書き込み |
| [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | すべての ID 保護レポートと概要の表示 | ポリシーを構成または変更する  ユーザーのパスワードをリセットする  アラートを構成する  検出に関するフィードバックを送信する |
| [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator) | すべての ID 保護レポートと概要の表示  ユーザー リスクを無視し、安全なサインインを確認して、セキュリティ侵害を確認する | ポリシーを構成または変更する  ユーザーのパスワードをリセットする  アラートを構成する |
| [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) | ID 保護のフル アクセス | ユーザーのパスワードのリセット |

### ライセンス要件

この機能を使用するには、Microsoft Entra ID P2 ライセンスが必要です。 要件に適したライセンスを見つけるには、Microsoft Entra のプランと価格を参照してください。 次の表では、Microsoft Entra ID Protection の主な機能と、各機能のライセンス要件について説明します。 価格の詳細については、Microsoft Entra のプランと価格に関するページを参照してください。

| 機能 | 詳細 | Microsoft Entra ID Free/Microsoft 365 Apps | Microsoft Entra ID P1 | Microsoft Entra ID P2 / Microsoft Entra Suite |
| --- | --- | --- | --- | --- |
| リスク ポリシー | ログインとユーザー リスク ポリシー (ID 保護または条件付きアクセスを介して)Microsoft マネージド修復ポリシーを含む | いいえ | いいえ | はい |
| セキュリティ レポート | 概要 | いいえ | いいえ | はい |
| セキュリティ レポート | 危険なユーザー | 限定的な情報。 リスクの程度が中から高のユーザーのみが表示されます。 詳細ドロアーやリスクの履歴は提供されません。 | 限定的な情報。 リスクの程度が中から高のユーザーのみが表示されます。 詳細ドロアーやリスクの履歴は提供されません。 | フル アクセス |
| セキュリティ レポート | リスクの高いサインイン | 限定的な情報。 リスクの詳細やリスク レベルは表示されません。 | 限定的な情報。 リスクの詳細やリスク レベルは表示されません。 | フル アクセス |
| セキュリティ レポート | リスク検出 | いいえ | 限定的な情報。 詳細ドロアーは提供されません。 | フル アクセス |
| 通知 | 危険な状態のユーザーが検出されたアラート | いいえ | いいえ | はい |
| 通知 | 週間ダイジェスト | いいえ | いいえ | はい |
| MFA 登録ポリシー | MFA を要求する (条件付きアクセスを使用) | いいえ | いいえ | はい |
| Microsoft Graph | すべてのリスク レポート | いいえ | いいえ | はい |

リスク検出レポートの **[危険なワークロード ID** ] レポートと [ **ワークロード ID** 検出] **タブを表示** するには、ワークロード ID Premium ライセンスが必要です。 詳細については、「[ワークロード ID をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)」を参照してください。

#### Microsoft Defender

Microsoft Entra ID Protection は、いくつかのリスク検出のために Microsoft Defender 製品からシグナルを受信するため、関心のあるシグナルを所有する Microsoft Defender 製品の適切なライセンスも必要です。

**Microsoft 365 E5** には、次のすべてのシグナルが含まれます。

- **Microsoft Defender for Cloud Apps**

    - 匿名 IP アドレスからのアクティビティ
    - あり得ない移動
    - 機密ファイルへの大量アクセス
    - 初めての国
- **Microsoft Defender for Office 365**

    - 疑わしい受信トレイルール
- **Microsoft Defender for Endpoint**

    - プライマリ更新トークンへのアクセスが試行される可能性があります
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-protection/workbook-risk-based-policy-impact"} -->
## リスク ベースのアクセス ポリシーの影響分析ガイド - Microsoft Entra ID Protection

- Source: https://learn.microsoft.com/ja-jp/entra/id-protection/workbook-risk-based-policy-impact
- Service: entra-id-protection
- Article date: 2025-08-06
- Summary: 環境内のリスクベースの条件付きアクセス ポリシーの影響を事前に確認します。

すべてのユーザーに対して[リスクベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)を有効にすることをお勧めします。これを展開するには (望ましくない影響を把握するために)、時間、変更管理、場合によってはリーダーによる慎重な精査が必要になることはわかっています。 管理者に、これらのシナリオに自信をもって解決策を提供し、環境を迅速に保護するために必要な、リスクベースのポリシーを導入するための知識を提供します。

レポート専用モードでリスクベースの条件付きアクセス ポリシーを作成して、結果を得るために数週間/数か月待つ代わりに、**リスクベースのアクセス ポリシーの影響分析**[ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks)を使用すると、サインイン ログに基づいた影響をすぐに確認できます。

[Image: リスクベースのアクセス ポリシー ブックの [影響分析] のスクリーンショット。]

### 説明

ブックは、ユーザーのサインインをブロックしたり、多要素認証を要求したり、セキュリティ保護されたパスワードの変更を実行したりするポリシーを有効にする前に、環境を理解するのに役立ちます。 ユーザーが選んだサインインの日付範囲に基づいて、次のような詳細が提供されます。

- 次の概要を含む、推奨されるリスク ベースのアクセス ポリシーの影響の概要:
    - ユーザー リスクのシナリオ
    - サインイン リスクと信頼されているネットワークのシナリオ
- 影響の詳細には一意のユーザーの詳細が含まれています。
    - 次のようなユーザー リスクのシナリオ:
        - リスク ベースのアクセス ポリシーによってブロックされなかった高リスク ユーザー。
        - リスク ベースのアクセス ポリシーによってパスワード変更を求められなかった高リスク ユーザー。
        - リスク ベースのアクセス ポリシーのためにパスワードを変更したユーザー。
        - リスク ベースのアクセス ポリシーのためにサインインできなかった危険なユーザー。
        - オンプレミスのパスワード リセットによってリスクを修復したユーザー。
        - クラウドベースのパスワード リセットによる修復によってリスクを修復したユーザー。
    - ログイン リスク ポリシーのケース例:
        - リスク ベースのアクセス ポリシーによってブロックされなかった高リスク サインイン。
        - リスク ベースのアクセス ポリシーによる多要素認証を使って自己修復しない高リスク サインイン。
        - リスク ベースのアクセス ポリシーのために成功しなかった危険なサインイン。
        - 多要素認証によって修復された危険なサインイン。
    - 信頼されているネットワークとしてリストされない上位の IP アドレスを含むネットワークの詳細。

管理者は、この情報を使って、リスク ベースの条件付きアクセス ポリシーを有効にした場合、一定の期間影響を受ける可能性があるユーザーを確認できます。

### ブックにアクセスする方法

このワークブックは、レポート専用モードのものであっても、条件付きアクセス ポリシーを作成する必要はありません。 唯一の前提条件は、サインイン ログを Log Analytics ワークスペースに送信することです。 この前提条件を有効にする方法について詳しくは、「[Microsoft Entra ブックの使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)」をご覧ください。 Identity Protection でブックに直接アクセスすることも、編集可能なバージョンを確認するには、Workbooksへ移動してください。

Identity Protection の場合:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上でサインインしてください。
2. **ID Protection**&gt;**Dashboard**&gt;**リスクポリシーの影響分析**に移動します。

Workbooks では次のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上でサインインしてください。
2. **Entra ID**&gt;**監視と健康**&gt;**ワークブック** に移動してください。
3. **[ID 保護]** の下の **[リスク ベースのアクセス ポリシーの影響分析]** ブックを選びます。

#### ブック内を移動する

ブック内に入ると、右上隅にいくつかのパラメーターがあります。 どのワークスペースからブックを作成するかを設定したり、ガイドのオンとオフを切り替えたりすることができます。

[Image: ブックのパラメーターと [ガイド] セクションが強調表示されているスクリーンショット。]

すべてのブックと同様に、視覚化のデータを抽出する [Kusto 照会言語 (KQL)](https://learn.microsoft.com/ja-jp/azure/data-explorer/kusto/query/) クエリを表示または編集できます。 変更を加えた場合は、いつでも元のテンプレートに戻すことができます。

##### まとめ

最初のセクションは概要であり、選択した時間の範囲内に影響を受けたユーザーまたはセッションの合計数が示されます。 さらに下にスクロールすると、関連する詳細が表示されます。

[Image: ワークブックの概要セクションを示すスクリーンショット。]

概要の中で扱われている最も重要なシナリオは、ユーザーおよびサインイン リスク シナリオの、シナリオ 1 と 2 です。 これらは、ブロックされなかった、パスワード変更を求められなかった、または MFA によって修復されなかった高リスク ユーザーまたはサインインを示します。つまり、高リスク ユーザーがまだ環境内にいる可能性があるということです。

それから下にスクロールして、それらのユーザーが正確に誰であるかの詳細を確認できます。 すべての概要コンポーネントには、それに対応する詳細が続きます。

##### ユーザー リスクのシナリオ

[Image: ワークブックの「ユーザー リスク」セクションを示すスクリーンショット。]

ユーザー リスク シナリオ 3 と 4 は、いくつかのリスクベースのアクセス ポリシーが既に有効な場合に役立ちます。パスワードを変更したユーザーが、またはリスクベースのアクセス ポリシーによってサインインがブロックされた高リスク ユーザーが表示されます。 高リスク ユーザーがユーザー リスク シナリオ 1 または 2 で（ブロックされたりパスワード変更を求められたりせずに）依然として表示されており、全員がこのカテゴリに収まると思っていた場合は、ポリシーにギャップがある可能性があります。

##### サインイン リスク シナリオ

[Image: ブックの [サインイン リスク] セクションを示すスクリーンショット。]

次に、サインイン リスク シナリオ 3 と 4 を見てみましょう。 MFA を使用する場合は、リスクベースのアクセス ポリシーが有効になっていない場合でも、ここでアクティビティが発生する可能性があります。 MFA が正常に実行されると、サインイン リスクは自動的に修復されます。 シナリオ 4 では、リスクベースのアクセス ポリシーが原因で成功しなかった、高リスク サインインを確認します。 ポリシーを有効にしたにもかかわらず、ブロックされると想定している (または MFA で修復された) サインインが引き続き表示されている場合は、ポリシーにギャップがある可能性があります。 その場合は、ポリシーを確認し、このブックの詳細セクションを使用してギャップを調査することをお勧めします。

ユーザー リスク シナリオのシナリオ 5 と 6 は、修復が発生していることを示しています。 このセクションでは、オンプレミスから、またはセルフサービス パスワード リセット (SSPR) を使用して、パスワードを変更しているユーザー数に関する分析情報が提供されます。 これらの数値が環境とつじつまが合わない場合 (SSPR を有効にしていないはず、など) は、詳細を使用して調査します。

サインイン シナリオ 5 の**信頼されていない IP アドレス**では、選択した時間の範囲内のすべてのサインインの IP アドレスと、信頼されていない IP アドレスが表示されます。

##### フェデレーション サインイン リスク ポリシーのシナリオ

複数の ID プロバイダーを使用している顧客の場合、(MFA またはその他の形式の修復のために) それらの外部プロバイダーにリダイレクトされる危険なセッションがあるかどうかを確認するのに、次のセクションが役立ちます。 このセクションでは、修復が行われている場所と、それが想定どおりに発生しているかどうかの分析情報が提供されます。 このデータを事前設定するには、フェデレーション環境内に “federatedIdpMfaBehavior” を設定して、フェデレーション ID プロバイダーからの MFA を適用する必要があります。

[Image: ワークブックのフェデレーションサインインリスクポリシーシナリオを示すスクリーンショット。]

##### レガシ ID 保護ポリシー

次のセクションでは、環境内にまだ存在し、[2026 年 10 月までに移行する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#migrate-risk-policies-to-conditional-access)必要があるレガシのユーザーおよびサインインのポリシー数を追跡します。 このタイムラインを認識し、できるだけ早く条件付きアクセス ポータルへのポリシーの移行を開始することが重要です。 新しいポリシーをテストし、不要または重複したポリシーを整理して、適用範囲にギャップがないことを確認するには、十分な時間が必要です。 レガシ ポリシーの移行の詳細については、「リスク ポリシーを移行する」のリンクを参照してください。

[Image: ブックの従来のID保護ポリシーセクションを示すスクリーンショット。]

##### 信頼されたネットワークの詳細

このセクションでは、信頼されていない IP アドレスの詳細な一覧が示されます。 これらの IP はどこから来て、誰が所有しているのでしょうか? "信頼された" と見なす必要があるのでしょうか? この課題は、ネットワーク管理者とのチーム間作業になる可能性があります。しかし、正確な信頼された IP 一覧があると、リスクの誤検知を減らすのに役立つため、実施すると有益です。 環境に対して疑わしいと思われる IP アドレスがある場合は調査します。

[Image: ブックの [信頼されたネットワーク] セクションを示すスクリーンショット。]
<!-- /MSL-PAGE -->
