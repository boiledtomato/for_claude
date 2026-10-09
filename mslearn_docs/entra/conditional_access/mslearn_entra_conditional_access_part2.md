# Microsoft Learn — Microsoft Entra / 条件付きアクセス (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 3

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/troubleshoot-security-copilot-policies"} -->
## Microsoft Security Copilotの条件付きアクセス ポリシーのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-security-copilot-policies
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: Security Copilot条件付きアクセス - 保護を強化するために、カスタム セキュリティ属性を使用してポリシーを作成、割り当て、トラブルシューティングする方法について説明します。

### 概要

[生成的人工知能（AI）](https://learn.microsoft.com/ja-jp/ai/playbook/technology-guidance/generative-ai/)サービスのような[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/copilot/security/microsoft-security-copilot)は、適切に使用すると、組織に価値をもたらすことができます。

[すべてのリソースを対象とする推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#conditional-access-for-all-resources)に従って、これらの生成 AI サービスに条件付きアクセス ポリシーを適用します。 これらのポリシーには、 [すべてのユーザー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)、危険な [ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user)、 [サインイン](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-sign-in)、 [デバイス コンプライアンス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)、 [インサイダー リスク](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-insider-block)のあるユーザーに対するポリシーが含まれる場合があります。

一部の組織では、条件付きアクセス ポリシーで基になるサービス プリンシパルとカスタム セキュリティ属性を使用して、これらのサービスを直接対象とします。

- 43d7b169-1d9e-4d32-8cd8-06c5974ed90c - セキュリティコパイロットエージェント管理
- bb5ffd56-39eb-458c-a53a-775ba21277da - Security Copilot Portal
- bb3d68c2-d09e-4455-94a0-e323996dbaa3 - Security Copilot API
- b0cf1501-8e0f-4fbb-b70a-52ca5ea7bda6 - セキュリティ コパイロット ロジックアプリ コネクタ

このような場合、管理者は、カスタム セキュリティ属性を使用して、これらの基になるサービス プリンシパルを作成、割り当て、対象とします。

### 必要な役割

カスタム セキュリティ属性はセキュリティの機密性が高く、委任されたユーザーのみが管理できます。 これらの属性を管理またはレポートするユーザーに、次のロールのうち 1 つ以上を割り当てます。

| 役割名 | 説明 |
| --- | --- |
| [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) | サポートされているMicrosoft Entra オブジェクトにカスタム セキュリティ属性キーと値を割り当てます。 |
| [アトリビュート割り当てビューア](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader) | サポートされているMicrosoft Entra オブジェクトのカスタム セキュリティ属性キーと値を読み取ります。 |
| [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator) | カスタム セキュリティ属性を定義して管理します。 |
| [属性定義閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-reader) | カスタム セキュリティ属性の定義を読み取ります。 |

ディレクトリ スコープでこれらの属性を管理またはレポートするユーザーに適切なロールを割り当てます。 詳細な手順については、「[assign Microsoft Entra roles](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal#assign-roles-with-tenant-scope)を参照してください。

Von Bedeutung

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

### カスタム セキュリティ属性を作成する

記事 [「Microsoft Entra ID のカスタム セキュリティ属性を追加または非アクティブ化する」](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add) の指示に従って、次の**属性セット**と**新しい属性**を追加します。

- **SecurityCopilotAttributeSet** という名前の*属性セット*を作成します。
- **SecurityCopilotAttribute** という名前の*新しい属性*を作成し、[**複数の値の割り当てを許可する**] を **[いいえ**] に設定し、[**定義済みの値のみ割り当て**可能] を **[はい**] に設定します。 次の定義済みの値を追加します。
    - 多要素認証必須

注

アプリケーションの条件付きアクセス フィルターは、"string" 型のカスタム セキュリティ属性でのみ機能します。 カスタム セキュリティ属性ではブール型の作成がサポートされていますが、条件付きアクセス ポリシーでは "string" のみがサポートされます。

### カスタム セキュリティ属性をアプリケーションに割り当てる

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[Conditional Access Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) および [Attribute Assignment Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動します。
3. カスタム セキュリティ属性を適用するアプリを選択します。
    1. 43d7b169-1d9e-4d32-8cd8-06c5974ed90c - セキュリティコパイロットエージェント管理
    2. bb5ffd56-39eb-458c-a53a-775ba21277da - セキュリティコパイロットポータル
    3. bb3d68c2-d09e-4455-94a0-e323996dbaa3 - Security Copilot API
    4. b0cf1501-8e0f-4fbb-b70a-52ca5ea7bda6 - セキュリティ コパイロット ロジック アプリ コネクタ
4. **[管理]**&gt;**[カスタム セキュリティ属性]** で、**[割り当ての追加]** を選択します。
5. [ **属性セット]** で、作成した属性セットを選択します。
6. [ **属性名**] で、作成した属性を選択します。
7. [ **割り当てられた値**] で [ **値の追加**] を選択し、一覧から作成した値を選択し、[ **完了]** を選択します。
8. **保存** を選択します。

### 条件付きアクセス ポリシーでのカスタム セキュリティ属性のターゲット設定

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[Conditional Access Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) および [Attribute Definition Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-reader) としてサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. **[新しいポリシー**] を選択するか、更新する既存のポリシーを選択します。
4. **ターゲット リソース**を構成するときに、次のオプションを選択します。
    1. このポリシーが **リソース (以前のクラウド アプリ) に適用される内容を**選択します。
    2. **[リソースの選択]** を含めます。
    3. **[フィルターの編集]** を選択します。
    4. **[構成]** を **[はい]** に設定します。
    5. 作成した **属性** を選択します。
    6. **演算子**を **Contains** に設定します。
    7. **Value** をカスタム属性のいずれかに設定します。
    8. **完了**を選択します。

### 問題の原因となっているポリシーはどれですか?

管理者が問題が発生したときに更新するポリシーを確認するのが難しい場合があります。 [条件付きアクセスに関するサインインの問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access#microsoft-entra-sign-in-events)のガイダンスを使用して、適用されるポリシー、適用されないポリシーを確認し、継続的な問題を回避するためにサインイン診断を実行します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/what-if-tool"} -->
## 条件付きアクセスの What If ツール - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: 条件付きアクセス ポリシーの結果を What If ツールを使用してシミュレートし、環境のトラブルシューティングと最適化を行います。

### 概要

**条件付きアクセス What If ポリシー ツール**は、環境内の[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーの結果を理解するのに役立ちます。 これは、一般的でないシナリオをシミュレートする場合に役立ち、より包括的なセキュリティ ポリシーを設計できます。 このツールは、複数のサインインを使用してポリシーを手動でテストする代わりに、ユーザー、エージェント ID、またはシングル テナント サービス プリンシパルのサインインをシミュレートするのに役立ちます。 シミュレーションでは、ポリシーがこのサインインに与える影響を推定し、レポートを生成します。

**What If** ツールと [API を](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-evaluate)使用すると、特定のユーザー、エージェント ID、またはシングル テナント サービス プリンシパルに適用されるポリシーをすばやく特定できます。 この情報を使用して、問題のトラブルシューティングを行い、特定のサインイン条件に適用されるポリシーを理解し、複雑なサインイン シナリオをテストします。

### しくみ

条件付きアクセスの What If ツールは、[What If 評価 API](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-evaluate)を利用して動作します。 このツールを使用するには、まず、シミュレートするサインイン シナリオの条件を構成します。 構成には、次のものが含まれている必要があります。

- テストするユーザー、エージェント ID (プレビュー)、またはシングル テナント サービス プリンシパル。
- クラウド アプリ、実行しようとするユーザー アクション、またはアクセスを試みる認証コンテキストによって保護された機密データ。
- アクセスが試行されるサインイン条件。

重要

What If ツールでは、[条件付きアクセスのサービスの依存関係](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/service-dependencies)はテストされません。 たとえば、 **What If** を使用してMicrosoft Teamsの条件付きアクセス ポリシーをテストする場合、結果では、Microsoft Teamsの条件付きアクセス サービスの依存関係である Office 365 Exchange Online に適用されるポリシーは考慮されません。

次に、設定を評価するシミュレーション実行を開始します。 評価実行には、有効になっているポリシーまたはレポート専用モードのポリシーのみが含まれます。

評価が完了すると、ツールは、影響を受けたポリシーのレポートを生成します。 条件付きアクセス ポリシーの詳細を収集するには、 [ポリシーごとの条件付きアクセスレポート、](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#policy-impact-preview) または [条件付きアクセスの分析情報とレポート ブックを](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting) 使用して、レポート専用モードまたは現在有効になっているポリシーの詳細を確認します。

### What If ツールを実行する

**What If** ツールは**、Microsoft Entra 管理センター**&gt;**Entra ID**&gt;**Conditional Access**&gt;**Policies**&gt;**Whatt If** にあります。

[Image: ツール バーの [What If] ツールが強調表示されている [条件付きアクセス ポリシー] ページのスクリーンショット。]

What If 評価を実行するには、評価する条件を指定します。

### 条件

ID、ターゲット リソース、デバイス プラットフォーム、クライアント アプリの条件が必要です。 その他の条件はすべて省略可能であり、値が指定されていない場合、既定では **none** に設定されていると見なされます。 これらの条件の定義については、 [条件付きアクセス ポリシーの構築](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies)に関する記事を参照してください。

[Image: 条件を入力するためのフィールドが表示されている [What If] ページのスクリーンショット。]

### 評価

**[What If**]\(What If\)を選択して評価を開始します。 以下で構成されるレポートによって評価結果が示されます。

- 環境内にクラシック ポリシーが存在するかどうかを示すインジケーター。
- ユーザー、エージェント、またはワークロード ID に適用されるポリシー。
- ユーザーまたはワークロード ID には適用されないポリシー。

[Image: What If ツールのポリシー評価の例を示すスクリーンショット。適用されるポリシーが表示されています。]

適用されるポリシーの一覧には、満たす必要がある [許可コントロール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) と [セッション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session) も含まれます。

適用されないポリシーの一覧には、これらのポリシーが適用されない理由が含まれます。 表示されている各ポリシーの理由は、満たされていない最初の条件を表します。

**[フィルターあり] は** 、ポリシーにカスタム セキュリティ属性を使用するアプリ フィルターがあるかどうかを示します。

### What If 評価 API とレガシ エクスペリエンスの主な違い

[What If Evaluation API](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-evaluate) は、条件付きアクセス エクスペリエンスによって呼び出される Microsoft Graph APIです。 API は、いくつかの点で従来の What If 評価とは異なります。

1. What If API は、パブリックで完全にサポートされている API です。 条件付きアクセス エクスペリエンスと Microsoft Graph APIで使用できます。
2. このロジックは、より正確なポリシー評価を提供するために、サインイン時に使用される認証ロジックと一致します。
3. What If API では、最も正確な結果を得るために、すべてのサインイン パラメーターが評価用に定義されることを想定しています。 テナントに特定の条件を持つポリシーがあり、それらの条件のサインインの詳細が指定されていない場合、What If API はこれらの条件を評価できません。

注

アプリケーションの仕様については、アプリ ID を指定します。 **Office 365** や **Microsoft 管理ポータル**などのアプリのグループは、一致しません。

#### 例示

この例では、主な違いを強調しています。

次の構成の条件付きアクセス ポリシーがあるとします。

- ユーザー: すべてのユーザー
- リソース: Office 365
- 場所: 米国
- サインイン リスク: 高

| 例 | パラメーター | レガシ What If 評価に基づく結果 | 新しい What If 評価 API に基づく結果 |
| --- | --- | --- | --- |
| 1 | UserId = "00aa00aa-bb11-cc22-dd33-44ee44ee44ee" | 適用 | 該当しません |
| 2 | UserId = "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"  ApplicationId = "00000003-0000-0ff1-ce00-000000000000" | 適用 | 該当しません |
| 3 | UserId = "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"  ApplicationId = "00000003-0000-0ff1-ce00-000000000000"  Location = "US" | 適用 | 該当しません |
| 4 | UserId = "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"  ApplicationId = "00000003-0000-0ff1-ce00-000000000000"  Location = "US"  サインイン リスク = "高" | 適用 | 適用 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/workload-identity"} -->
## ワークロード ID 用の Microsoft Entra 条件付きアクセス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: 条件付きアクセス ポリシーを使用したワークロード ID の保護

### 概要

従来、条件付きアクセス ポリシーは、ユーザーが SharePoint Online などのアプリやサービスにアクセスするときにのみ適用されていました。 現在、組織が所有するサービス プリンシパルに適用される条件付きアクセス ポリシーのサポートを拡張しています。 この機能を、ワークロード ID 用の条件付きアクセスと呼びます。

[ワークロード ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) は、アプリケーションまたはサービス プリンシパルがリソースにアクセスできるようにする ID です。場合によっては、ユーザーのコンテキストでアクセスできます。 これらのワークロード ID は、次の理由から、従来のユーザー アカウントとは異なります。

- 多要素認証を実行できません。
- 正式なライフサイクル プロセスがないことが多い。
- 資格情報またはシークレットをどこかに保存する必要があります。

これらの違いによって、ワークロード ID の管理が難しくなり、侵害リスクが高くなります。

重要

サービス プリンシパルを対象とする条件付きアクセス ポリシーを作成または変更するには、Workload Identities Premium ライセンスが必要です。 適切なライセンスがないディレクトリでは、ワークロード ID の既存の条件付きアクセス ポリシーは引き続き機能しますが、変更することはできません。 詳細については、「 [Microsoft Entra ワークロード ID」を](https://www.microsoft.com/security/business/identity-access/microsoft-entra-workload-identities#office-StandaloneSKU-k3hubfz)参照してください。

注

ポリシーは、お使いのテナントに登録されているシングル テナント サービス プリンシパルに適用できます。 マルチテナント アプリを含むMicrosoftおよびサード パーティの SaaS アプリケーションは、これらのポリシーの対象ではありません。 マネージド ID は、ポリシーの対象にはなりません。 代わりに、マネージド ID を [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview) に含めることができました。

注

サービス プリンシパルはグループに追加できますが、サービス プリンシパルを含むグループに割り当てられた条件付きアクセス ポリシーは、そのサービス プリンシパルに適用されません。 サービス プリンシパルに条件付きアクセス ポリシーを適用するには、ワークロード ID としてポリシーに直接割り当てる必要があります。

ワークロード ID の条件付きアクセスにより、ブロックしているサービス プリンシパルが次の条件で有効になります。

- 既知のパブリック IP 範囲の外部から。
- Microsoft Entra ID 保護によって検出されたリスクに基づいて。
- [認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)と組み合わせて。

### 実装

#### 場所ベースの条件付きアクセス ポリシーを作成する

サービス プリンシパルに適用される、場所ベースの条件付きアクセス ポリシーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. [ **新しいポリシー]** を選択します。
4. ポリシーに名前を付けます。 ポリシーの名前にわかりやすい標準を作成します。
5. [ **割り当て]** で、[ **ユーザーまたはワークロード ID] を選択します**。
    1. [ **このポリシーの適用対象] で**、[ **ワークロード ID] を選択します**。
    2. [ **含める**] で[ **サービス プリンシパルの選択**] を選択し、一覧から適切なサービス プリンシパルを選択します。
6. **ターゲット リソース**&gt;**リソース (旧称クラウド アプリ)**&gt;**の中で**、「**すべてのリソース (旧称 'すべてのクラウド アプリ')**」を選択します。 このポリシーは、サービス プリンシパルがトークンを要求した場合にのみ適用されます。
7. [ **条件**&gt;**場所** ] で、**任意の場所** を含め、**選択した場所** を除外してアクセスを許可します。
8. [ **許可]** で、[ **アクセスのブロック** ] が使用可能な唯一のオプションです。 トークン要求が許容範囲外から行われた場合、アクセスはブロックされます。
9. ポリシーは **レポート専用** モードで保存でき、管理者は効果を見積もることができます。または、ポリシーを **オン**にすることでポリシーが適用されます。
10. [ **作成]** を選択してポリシーを完了します。

#### リスクベースの条件付きアクセス ポリシーを作成する

サービス プリンシパルに適用される、場所ベースの条件付きアクセス ポリシーを作成します。

[Image: ワークロード ID とリスクを条件として持つ条件付きアクセス ポリシーを作成する。]

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. [ **新しいポリシー]** を選択します。
4. ポリシーに名前を付けます。 ポリシーの名前にわかりやすい標準を作成します。
5. [ **割り当て]** で、[ **ユーザーまたはワークロード ID] を選択します**。
    1. [ **このポリシーの適用対象] で**、[ **ワークロード ID] を選択します**。
    2. [ **含める**] で[ **サービス プリンシパルの選択**] を選択し、一覧から適切なサービス プリンシパルを選択します。
6. **ターゲット リソース**&gt;**リソース (旧称クラウド アプリ)**&gt;**の中で**、「**すべてのリソース (旧称 'すべてのクラウド アプリ')**」を選択します。 このポリシーは、サービス プリンシパルがトークンを要求した場合にのみ適用されます。
7. **[条件]**&gt;**[サービス プリンシパル リスク]**の下で次のようにします
    1. [ **構成** ] トグルを **[はい**] に設定します。
    2. このポリシーをトリガーするリスクのレベルを選択します。
    3. [ **完了] を選択します**。
8. [ **許可]** で、[ **アクセスのブロック** ] が使用可能な唯一のオプションです。 指定したリスク レベルが表示されると、アクセスはブロックされます。
9. ポリシーは **レポート専用** モードで保存でき、管理者は効果を見積もることができます。または、ポリシーを **オン**にすることでポリシーが適用されます。
10. [ **作成]** を選択してポリシーを完了します。

### ロールバック

この機能をロールバックする場合は、作成したポリシーを削除または無効にすることができます。

### サインイン ログ

サインイン ログを使用して、サービス プリンシパルに対してポリシーがどのように適用されているか、またはレポート専用モードを使用している場合には予想されるポリシーの影響を確認できます。

1. **Entra ID**&gt;**モニタリング&健康**&gt;**サインインログ**&gt;**サービス プリンシパルのサインイン**に移動します。
2. ログ エントリを選択し、[ **条件付きアクセス** ] タブを選択して評価情報を表示します。

条件付きアクセスによってサービス プリンシパルがブロックされた場合のエラーの理由: "条件付きアクセス ポリシーのため、アクセスがブロックされました。"

#### レポート専用モード

場所ベースのポリシーの結果を表示するには、**サインイン レポート**のイベントの **[レポートのみ**] タブに移動するか、**条件付きアクセスの分析情報とレポート ブックを**使用します。

リスクベースのポリシーの結果を表示するには、**サインイン レポート**のイベントの [**レポートのみ**] タブを参照してください。

### 関連項目

#### ObjectID の検索

サービス プリンシパルの objectID は Microsoft Entra エンタープライズ アプリケーションから取得できます。 Microsoft Entra アプリの登録のオブジェクト ID は使用できません。 この識別子は、サービス プリンシパルではなく、アプリの登録のオブジェクト ID です。

1. **Entra ID**&gt;**Enterprise アプリ**に移動し、登録したアプリケーションを見つけます。
2. [ **概要** ] タブで、アプリケーションの **オブジェクト ID を** コピーします。 この識別子はサービス プリンシパルに固有のもので、呼び出し元のアプリを検索するために条件付きアクセス ポリシーによって使用されます。

#### Microsoft Graph

Microsoft Graph ベータ版エンドポイントを使用した場所ベースの構成用のサンプル JSON。

```json
{
  "displayName": "Name",
  "state": "enabled OR disabled OR enabledForReportingButNotEnforced",
  "conditions": {
    "applications": {
      "includeApplications": [
        "All"
      ]
    },
    "clientApplications": {
      "includeServicePrincipals": [
        "[Service principal Object ID] OR ServicePrincipalsInMyTenant"
      ],
      "excludeServicePrincipals": [
        "[Service principal Object ID]"
      ]
    },
    "locations": {
      "includeLocations": [
        "All"
      ],
      "excludeLocations": [
        "[Named location ID] OR AllTrusted"
      ]
    }
  },
  "grantControls": {
    "operator": "and",
    "builtInControls": [
      "block"
    ]
  }
}
```
<!-- /MSL-PAGE -->
