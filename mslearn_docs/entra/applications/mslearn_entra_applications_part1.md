# Microsoft Learn — Microsoft Entra / エンタープライズアプリ・プロビジョニング・アプリプロキシ (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 48

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning"} -->
## アプリケーション プロビジョニングに関するドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning
- Service: entra-id / app-provisioning
- Article date: 2025-02-28
- Summary: Microsoft Entraアプリ プロビジョニングを使用すると、クラウド (SaaS) アプリでユーザー ID を自動的に作成および管理できます。 Microsoft Entra IDでは、クラウドベースの人事 (HR) アプリケーションを使用したプロビジョニングも提供されます。 クイック スタート、チュートリアル、ガイダンスを使用して、クラウド アプリと HR プロビジョニングを管理およびデプロイする方法について説明します。

Microsoft Entraアプリ プロビジョニングを使用すると、クラウド (SaaS) アプリでユーザー ID を自動的に作成および管理できます。 Microsoft Entra IDでは、クラウドベースの人事 (HR) アプリケーションを使用したプロビジョニングも提供されます。 クイック スタート、チュートリアル、ガイダンスを使用して、クラウド アプリと HR プロビジョニングを管理およびデプロイする方法について説明します。

### クラウド アプリにユーザーとグループをプロビジョニングする

#### 概要

- [アプリケーション プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
- [人事主導のプロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)
- [プロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)

#### チュートリアル

- [SAP Cloud Platform Identity Authentication のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial?context=/azure/active-directory/app-provisioning/context/app-provisioning-context)
- [Oracle Fusion Enterprise Resource Planning (ERP) プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-fusion-erp-provisioning-tutorial?context=/azure/active-directory/app-provisioning/context/app-provisioning-context)
- [Atlassian Cloud のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atlassian-cloud-provisioning-tutorial?context=/azure/active-directory/app-provisioning/context/app-provisioning-context)

#### 攻略ガイド

- [アプリケーション内の既存のユーザーを検出する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)
- [属性マッピングをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)
- [スコープ フィルターを使用する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)

### プロビジョニングに System for Cross-domain Identity Management (SCIM) を使用してアプリを開発する

#### 概要

- [SCIM を使用したプロビジョニング (アイデンティティ標準ブログ)](https://techcommunity.microsoft.com/t5/security-compliance-and-identity/provisioning-with-scim-getting-started/ba-p/880010)

#### 攻略ガイド

- [アプリの開発 SCIM を使用した自動プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)

### クラウド HR のインバインド プロビジョニングを管理する

#### 概要

- [クラウド人事ユーザー プロビジョニングのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)

#### チュートリアル

- [ワークデイ プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial?context=/azure/active-directory/app-provisioning/context/app-provisioning-context)
- [SAP SuccessFactors のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial?context=/azure/active-directory/app-provisioning/context/app-provisioning-context)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/accidental-deletions"} -->
## Microsoft Entra プロビジョニング サービス で誤削除防止機能を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/accidental-deletions
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: アプリケーション向けの Microsoft Entra プロビジョニング サービスと、テナント間同期で、誤削除防止機能を有効にします。

::: zone pivot="app-provisioning"

Microsoft Entra プロビジョニング サービスには、誤削除を防ぐことができる機能が含まれています。 この機能により、アプリケーションで予期せずユーザーが無効にされたり削除されたりしないようにできます。

::: zone-end

::: zone pivot="cross-tenant-synchronization"

Microsoft Entra プロビジョニング サービスには、誤削除を防ぐことができる機能が含まれています。 この機能により、ターゲット テナントで予期せずユーザーが無効にされたり削除されたりしないようにできます。

::: zone-end

ユーザーは誤削除機能を使用して、削除のしきい値を指定します。 設定したしきい値を超えるものは、管理者が削除の処理を明示的に許可する必要があります。

### 誤った削除の防止機能を構成する

誤った削除の防止機能を有効にするには:

::: zone pivot="app-provisioning"

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. アプリケーションを選択します。
4. [ **プロビジョニング** ] を選択し、[プロビジョニング] ページで [ **プロビジョニングの編集]** を選択します。

::: zone-end

::: zone pivot="cross-tenant-synchronization"

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**テナント間同期**&gt;**構成**に移動して、設定を選択します。
3. **プロビジョニング**を選択します。

::: zone-end

1. [ **設定]** で、[ **誤って削除されないように** する] チェック ボックスをオンにし、削除のしきい値を指定します。
2. **通知メール** アドレスが完了していることを確認します。 削除のしきい値に達すると、メールが送信されます。
3. [ **保存] を** 選択して変更を保存します。

削除のしきい値に達すると、ジョブは検疫に移動され、通知の電子メールが送信されます。 その後、検疫されたジョブを許可または拒否できます。 検疫の動作の詳細については、「検疫 [状態でのアプリケーション プロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)参照してください。

### 誤って削除されたものからの復旧

誤削除が発生すると、プロビジョニングの状態ページに表示されます。 `Provisioning has been quarantined. See quarantine details for more information` と表示されます。

[ **削除を許可する]** または [ **プロビジョニング ログの表示]** を選択できます。

#### 削除を許可する

**[削除を許可]** アクションは、誤って削除しきい値をトリガーしたオブジェクトを削除します。 削除を受け入れるには、次の手順に従います。

1. [ **削除を許可] を選択します**。
2. 削除を許可するには、確認で [ **はい** ] をクリックします。
3. 削除が受け入れられたことを示す確認メッセージが表示されます。 状態は、次のサイクルで正常に戻ります。

#### 削除の拒否

削除を調査し、必要に応じて拒否します。

- 削除のソースを調査します。 詳細については、プロビジョニング ログで確認できます。
- ユーザーまたはグループを再びアプリケーション (構成) に割り当てるか、ユーザーまたはグループを復元するか、プロビジョニングの構成を更新して、削除を防止します。
- ユーザー/グループが削除されないように、必要な変更を加えたら、プロビジョニングを再開します。 ユーザーやグループが削除されないようにするために必要な変更を行うまで、プロビジョニングを再開しないでください。

#### 削除防止機能をテストする

この機能をテストするには、disable/delete イベントをトリガーします。たとえば、しきい値を 3 のような小さい値に設定し、スコープ フィルターを変更してユーザーを割り当て解除し、ディレクトリからユーザーを削除します (次のセクションの一般的なシナリオを参照してください)。

プロビジョニング ジョブを実行して (20～40 分)、[プロビジョニング] ページに戻ります。 検疫中のプロビジョニング ジョブを確認します。削除を許可するか、プロビジョニング ログを確認して、削除が発生した理由を把握できます。

### テストに使用する一般的なプロビジョニング解除シナリオ

- ユーザーを削除するか、ごみ箱に入れます。
- ユーザーのサインインをブロックします。
- アプリケーション (構成) からユーザーまたはグループの割り当てを解除します。
- アプリケーション (構成) へのアクセスを提供しているグループからユーザーを削除します。

プロビジョニング解除シナリオの詳細については、「 [アプリケーション プロビジョニングのしくみ」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#deprovisioning)参照してください。

### よく寄せられる質問

#### 削除のしきい値に対してどのようなシナリオがカウントされますか。

ユーザーがターゲット アプリケーション (またはターゲット テナント) から削除されるように設定されると、削除のしきい値に対してカウントされます。 ユーザーがターゲット アプリケーション (またはターゲット テナント) から削除される可能性があるシナリオには、アプリケーション (構成) からのユーザー割り当ての解除、ディレクトリ内のユーザーの論理的または物理的な削除などがあります。 削除対象として評価されたグループは、削除のしきい値にカウントされます。 削除に加えて、無効化についても同じように機能します。

#### 削除のしきい値が評価される間隔はどのくらいですか。

サイクルごとに評価されます。 1 回のサイクルで削除の回数がしきい値を超えない場合、「サーキット ブレーカー」はトリガーされません。 定常状態にするために複数のサイクルが必要な場合、削除のしきい値はサイクルごとに評価されます。

#### これらの削除イベントはどのようにログに記録されますか。

無効化または削除する必要があるが、削除のしきい値が原因で、それが行われていないユーザーが見つかることがあります。 **プロビジョニング ログ**に移動し、**StagedAction または StagedDelete** を使用して*アクション*をフィルター処理します。**監査ログ**には、誤削除の閾値が原因でプロビジョニングジョブが検疫状態であることを示すログが含まれています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/application-provisioning-application-unmatched-users"} -->
## Microsoft Entra ID のユーザーと一致しなかったアプリケーションのマッピングとユーザーを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-application-unmatched-users
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra プロビジョニング サービスは、Microsoft Entra ID のユーザーと、アプリケーションに既に存在するユーザーを照合します。 場合によっては、アプリケーションに Microsoft Entra ID と一致しないユーザーが含まれる場合があります。

プロビジョニングまたはシングル サインオン (SSO) のために、既存のアプリケーションを Microsoft Entra ID と統合する場合、アプリケーションのデータ ストアに、Microsoft Entra ID のユーザーに対応していないユーザー、または Microsoft Entra ID のどのユーザーとも一致しないユーザーがいると判断される場合があります。

Microsoft Entra プロビジョニング サービスは、構成可能な一致ルールに依存して、Microsoft Entra ID のユーザーがアプリケーション内のユーザーに対応しているかどうかを判断し、Microsoft Entra ID ユーザーから一致するプロパティを持つユーザーをアプリケーションで検索します。 たとえば、一致するルールが、Microsoft Entra ID ユーザーの `userPrincipalName` 属性とアプリケーションの `userName` プロパティを比較するとします。 `userPrincipalName`値が `alice.smith@contoso.com` の Microsoft Entra ID のユーザーがアプリケーションのロールに割り当てられると、Microsoft Entra プロビジョニング サービスは、`userName eq "alice.smith@contoso.com"`などのクエリを使用してアプリケーションの検索を実行します。 アプリケーション検索でユーザーが一致しない場合は、Microsoft Entra プロビジョニング サービスによってアプリケーションに新しいユーザーが作成されます。

アプリケーションにユーザーがまだいない場合、このプロセスでは、Microsoft Entra ID で割り当てられているユーザーをアプリケーションのデータ ストアに設定します。 ただし、アプリケーションに既にユーザーが存在する場合は、2 つの状況が発生する可能性があります。 まず、アプリケーションにユーザー アカウントを持つユーザーが存在する可能性がありますが、一致が見つかりません。おそらく、ユーザーは`asmith@contoso.com`ではなく`alice.smith@contoso.com`としてアプリケーションで表されるため、Microsoft Entra プロビジョニング サービスが実行する検索では見つかりません。 このような状況では、アプリケーションでユーザーが重複する可能性があります。 次に、アプリケーションにユーザー アカウントを持つユーザーが存在し、Microsoft Entra ID にユーザーがいない場合があります。 このような状況では、Microsoft Entra プロビジョニング サービスはアプリケーション内のユーザーと対話しませんが、アプリケーションが Microsoft Entra ID をその唯一の ID プロバイダーとして使用するように構成されている場合、それらのユーザーはもうサインインできなくなります。アプリケーションは、Microsoft Entra ID でサインインするようにユーザーをリダイレクトします。 ただし、そのユーザーには Microsoft Entra ID のユーザーがありません。

Microsoft Entra ID と既存のアプリケーションのデータ ストアの間のこれらの不整合は、次のような多くの理由で発生する可能性があります。

- アプリケーション管理者は、レコード人事ソースのシステムでは表されていないが、アプリケーションへのアクセスが必要な請負業者やベンダーなど、アプリケーションに直接ユーザーを作成します。
- ID と属性の変更 (名前を変更するユーザーなど) が Microsoft Entra ID またはアプリケーションに送信されなかったため、表現が一方または他のシステムで古くなっているか、または
- 組織は、Windows Server AD と異なるコミュニティを持つアプリケーションを個別にプロビジョニングする ID 管理製品を使用していました。 たとえば、ストアの従業員はアプリケーション アクセスを必要としていましたが、Exchange メールボックスは必要ないため、ストアの従業員は Windows Server AD または Microsoft Entra ID で表されませんでした。

既存のユーザーとのアプリケーションへのプロビジョニングまたは SSO を有効にする前に、ユーザーが一致していることを確認し、一致しなかったアプリケーションのユーザーを調査して解決する必要があります。 この記事では、ユーザーが一致できなかったさまざまな状況を解決する方法のオプションについて説明します。

ヒント

サポートされているアプリケーションの場合は、 [アカウント検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery) を使用してターゲット アプリケーションを自動的にスキャンし、既存のユーザーをローカル アカウント、未割り当てユーザー、または割り当てられたユーザーとして分類できます。 これにより、プロビジョニングを構成する前に、アプリケーションのユーザーランドスケープを可視化できます。

### 一致しなかったユーザーがアプリケーションに存在するかどうかを判断する

Microsoft Entra ID のユーザーと一致しないアプリケーション内のユーザーの一覧を既に特定している場合は、次のセクションに進みます。

アプリケーション内のどのユーザーが Microsoft Entra ID のユーザーと一致しないかを判断する手順は、アプリケーションが Microsoft Entra ID とどのように統合されるかによって異なります。

- SAP Cloud Identity Services を使用している場合は、 [SAP Cloud Identity Services プロビジョニング チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial) に従って、既存の SAP Cloud Identity Services ユーザーが必要な一致属性を持っていることを確認します。 このチュートリアルでは、SAP Cloud Identity Services から CSV ファイルにユーザーの一覧をエクスポートし、PowerShell を使用してそれらのユーザーを Microsoft Entra ID のユーザーと照合します。
- アプリケーションで LDAP ディレクトリを使用している場合は、 [LDAP ディレクトリプロビジョニングのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure) に従って、LDAP ディレクトリから既存のユーザーを収集します。 このチュートリアルでは、PowerShell を使用して、それらのユーザーを Microsoft Entra ID のユーザーと照合します。
- SQL データベースを使用するアプリケーションや、アプリケーション ギャラリーでプロビジョニング サポートを持つアプリケーションなど、他のアプリケーションの場合は、チュートリアルに従って [アプリケーションの既存のユーザーを管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users) し、Microsoft Entra ID にアプリケーションのユーザーと一致するユーザーが存在することを確認します。
- プロビジョニング インターフェイスがない他のアプリケーションの場合は、チュートリアルに従って、プロビジョニングを [サポートしていないアプリケーションのユーザーを管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-not-provisioned-users) し、Microsoft Entra ID にアプリケーションのユーザーと一致するユーザーが存在することを確認します。

これらのチュートリアルで提供されている PowerShell スクリプトが完了すると、アプリケーションのレコードが Microsoft Entra ID に含まれていない場合にエラーが表示されます。 アプリケーションのデータ ストアのユーザーのすべてのレコードを Microsoft Entra ID のユーザーとして配置できない場合は、一致しなかったレコードとその理由を調査し、次のセクションのいずれかのオプションを使用して一致の問題を解決する必要があります。

### アプリケーションと Microsoft Entra ID の間でユーザーが一致することを確認するためのオプション

このセクションでは、アプリケーション内の一致しないユーザーに対処するためのいくつかのオプションを提供します。 組織の目標と、Microsoft Entra ID とアプリケーションの間のデータの問題に基づいて、各ユーザーに適したオプションを選択します。 特定のアプリケーションのすべてのユーザーを対象とする 1 つのオプションがない場合があります。

| オプション | プロビジョニング前に必要な更新 |
| --- | --- |
| アプリケーションからテスト ユーザーを削除する | アプリケーション内のユーザー |
| 組織に所属しなくなったユーザーのアプリケーションからユーザーを削除する | アプリケーション内のユーザー |
| アプリケーションからユーザーを削除し、Microsoft Entra ID から再作成する | アプリケーション内のユーザー |
| アプリケーション内のユーザーの一致するプロパティを更新する | アプリケーション内のユーザー |
| 新しいプロパティを使用してアプリケーション内のユーザーを更新する | アプリケーション内のユーザー |
| メール アドレスがユーザー プリンシパル名と一致しない場合に一致するルールまたはプロパティを変更する | アプリケーション内または Microsoft Entra アプリケーションの照合ルールにおけるユーザー |
| Microsoft Entra ID でユーザーの一致する属性を更新する | Microsoft Entra ID のユーザー |
| Microsoft Entra Connect 同期またはクラウド同期プロビジョニング規則を更新して、必要なユーザーと属性を同期する | Microsoft Entra Connect Sync または Microsoft Entra クラウド同期。Microsoft Entra ID のユーザーを更新します |
| Microsoft Entra ID のユーザーを新しい属性で更新する | Microsoft Entra ID のユーザー |
| Microsoft Entra ID に既に設定されている別の属性に一致ルールを変更する | Microsoft Entra アプリケーション照合ルール |
| 継続的なアプリケーション アクセスが必要なアプリケーション内のユーザーに対して Windows Server AD でユーザーを作成する | Windows Server AD のユーザーは、ユーザーの「Microsoft Entra ID」を更新します。 |
| 継続的なアプリケーション アクセスが必要なアプリケーション内のユーザーに対して Microsoft Entra ID でユーザーを作成する | Microsoft Entra ID のユーザー |
| アプリケーションと Microsoft Entra ID で分離されたユーザーと一致しないユーザーを管理する | なし |

#### アプリケーションからテスト ユーザーを削除する

初期デプロイから残されたテスト ユーザーがアプリケーションに存在する可能性があります。 不要になったユーザーがいる場合は、アプリケーションから削除できます。

#### 組織に所属しなくなったユーザーのアプリケーションからユーザーを削除する

ユーザーが組織に関連付けられなくなり、アプリケーションへのアクセスは不要になったが、アプリケーションのデータ ソースのユーザーのままである可能性があります。 これは、アプリケーション管理者がユーザーの削除を省略した場合、または変更が必要であることを通知されなかった場合に発生する可能性があります。 ユーザーが不要になった場合は、アプリケーションから削除できます。

#### アプリケーションからユーザーを削除し、Microsoft Entra ID から再作成する

アプリケーションが現在広く使用されていない場合、またはユーザーごとの状態を維持していない場合は、一致しないユーザーがいないようにアプリケーションからユーザーを削除する方法もあります。 その後、ユーザーが Microsoft Entra ID でアプリケーションを要求または割り当てると、アクセスがプロビジョニングされます。

#### アプリケーション内のユーザーの一致するプロパティを更新する

ユーザーがアプリケーションと Microsoft Entra ID に存在する可能性がありますが、アプリケーション内のユーザーに一致に必要なプロパティがないか、プロパティの値が正しくありません。

たとえば、SAP 管理者が管理コンソールを使用して SAP Cloud Identity Services にユーザーを作成した場合、ユーザーに `userName` プロパティがない可能性があります。 ただし、そのプロパティは、Microsoft Entra ID のユーザーとの照合に使用されるプロパティである可能性があります。 `userName` プロパティが照合を目的としている場合は、SAP 管理者に依頼して、既存の SAP Cloud Identity Services ユーザーを更新して `userName` プロパティの値を持つようにする必要があります。

別の例として、アプリケーション管理者は、ユーザーがアプリケーションに最初に追加されたときに、アプリケーション内のユーザーのプロパティ `mail` としてユーザーの電子メール アドレスを設定しています。 ただし、後でユーザーのメール アドレスと `userPrincipalName` は Microsoft Entra ID で変更されます。 ただし、アプリケーションにメール アドレスが必要なかった場合、またはメール プロバイダーに古いメール アドレスの転送を許可するリダイレクトがあった場合、アプリケーション管理者は、アプリケーションのデータ ソースでプロパティ `mail` 更新する必要があることを見逃している可能性があります。 この不整合は、アプリケーション管理者がアプリケーションのユーザーの `mail` プロパティを現在の値に変更するか、次のセクションで説明するように照合ルールを変更することで解決できます。

#### 新しいプロパティを使用してアプリケーション内のユーザーを更新する

組織の以前の ID 管理システムによって、アプリケーション内のユーザーがローカル ユーザーとして作成されている可能性があります。 その時点で組織に ID プロバイダーが 1 つもない場合、アプリケーション内のユーザーは、他のシステムと関連付けるプロパティを必要としませんでした。 たとえば、以前の ID 管理製品では、権限のある HR ソースに基づいてアプリケーションにユーザーが作成されました。 その ID 管理システムは、アプリケーションで作成したユーザーと HR ソースとの間の相関関係を維持し、HR ソース識別子をアプリケーションに提供しませんでした。 後で、同じ HR ソースから設定された Microsoft Entra ID テナントにアプリケーションを接続しようとすると、Microsoft Entra ID には、アプリケーションと同じユーザー全員のユーザーが存在する可能性がありますが、共通のプロパティがないため、すべてのユーザーに対して照合が失敗します。

この一致する問題を解決するには、次の手順を実行します。

1. アプリケーション内のユーザーの既存の未使用のプロパティを選択するか、アプリケーションのユーザー スキーマに新しいプロパティを追加します。
2. Microsoft Entra ID のユーザーに既に存在する、従業員 ID 番号や電子メール アドレスなど、権限のあるソースからのデータをアプリケーション内のすべてのユーザーのそのプロパティに設定します。
3. このプロパティが一致するルールに含まれるように、アプリケーションの [Microsoft Entra アプリケーション プロビジョニング属性マッピング構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes) を更新します。

#### メール アドレスがユーザー プリンシパル名と一致しない場合に一致するルールまたはプロパティを変更する

既定では、アプリケーションの Microsoft Entra プロビジョニング サービス マッピングの一部は、アプリケーションの電子メール アドレス プロパティと一致するように `userPrincipalName` 属性を送信します。 一部の組織には、ユーザー プリンシパル名とは異なるユーザーのプライマリ メール アドレスがあります。 アプリケーションが、 `userPrincipalName`ではなくユーザーのプロパティとして電子メール アドレスを格納している場合は、アプリケーション内のユーザーを変更するか、一致するルールを変更する必要があります。

- Microsoft Entra ID からアプリケーションへのシングル サインオンを使用する場合は、userPrincipalName を保持するプロパティをユーザーに追加するようにアプリケーションを変更できます。 次に、アプリケーション内の各ユーザーのそのプロパティに Microsoft Entra ID のユーザーの userPrincipalName を設定し、このプロパティが一致する規則に含まれるように Microsoft Entra アプリケーション プロビジョニング構成を更新します。
- Microsoft Entra ID からのシングル サインオンを使用する予定がない場合は、Microsoft [Entra アプリケーション プロビジョニング属性マッピング構成を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)更新して、一致するルールの Microsoft Entra ユーザーの電子メール アドレス属性と一致させる方法があります。

#### Microsoft Entra ID でユーザーの一致する属性を更新する

場合によっては、照合に使用される属性の Microsoft Entra ID ユーザーの値が古くなっている場合があります。 たとえば、ユーザーは自分の名前を変更しましたが、名前の変更は Microsoft Entra ID ユーザーでは行われませんでした。

ユーザーが Microsoft Entra ID のみで作成および保守されている場合は、正しい属性を持つようユーザーを更新する必要があります。 ユーザー属性が Windows Server AD や HR ソースなどのアップストリーム システムで発生する場合は、アップストリーム ソースの値を変更し、変更が Microsoft Entra ID に表示されるまで待つ必要があります。

#### Microsoft Entra Connect 同期またはクラウド同期プロビジョニング規則を更新して、必要なユーザーと属性を同期する

状況によっては、以前の ID 管理システムによって、Windows Server AD ユーザーに、別のアプリケーションとの照合属性として機能できる適切な属性が設定されている場合があります。 たとえば、以前の ID 管理システムが HR ソースに接続されていた場合、AD ユーザーには、その以前の ID 管理システムによってユーザーの従業員 ID が設定された `employeeId` 属性があります。 別の例として、以前の ID 管理システムでは、アプリケーションの一意のユーザー ID が Windows Server AD スキーマの拡張属性として書き込まれています。 ただし、これらの属性のどちらも Microsoft Entra ID への同期のために選択されていない場合、またはユーザーが Microsoft Entra ID への同期の範囲外であった場合、ユーザー コミュニティの Microsoft Entra ID 表現が不完全である可能性があります。

この問題を解決するには、Microsoft Entra Connect 同期または Microsoft Entra クラウド同期の構成を変更して、アプリケーション内に存在する Windows Server AD のすべての適切なユーザーが Microsoft Entra ID にプロビジョニングされるスコープ内にあり、それらのユーザーの同期属性に一致する目的で使用される属性が含まれていることを確認する必要があります。 Microsoft Entra Connect 同期を使用している場合は、「 [Microsoft Entra Connect Sync: Configure filtering](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering) and [Microsoft Entra Connect Sync: Directory extensions](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)」を参照してください。 Microsoft Entra クラウド同期を使用している場合は、 [Microsoft Entra Cloud Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-attribute-mapping) と [クラウド同期ディレクトリ拡張機能の属性マッピングとカスタム属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping)に関する説明を参照してください。

#### Microsoft Entra ID のユーザーを新しい属性で更新する

場合によっては、アプリケーションは、ユーザーの Microsoft Entra ID スキーマに現在格納されていないユーザーの一意の識別子を保持することがあります。 たとえば、SAP Cloud Identity Services を使用している場合は、SAP ユーザー ID を一致する属性にしたい場合や、Linux システムを使用している場合は、Linux ユーザー ID を一致する属性にすることができます。 ただし、これらのプロパティは Microsoft Entra ID ユーザー スキーマの一部ではないため、Microsoft Entra ID のどのユーザーにも存在しない可能性があります。

照合に新しい属性を使用するには、次の手順を実行します。

1. Microsoft Entra ID で既存の未使用の拡張属性を選択するか、新しい属性で Microsoft Entra ユーザー スキーマを拡張します。
2. Microsoft Entra ID のすべてのユーザーに、アプリケーションや人事システムなどの権限のあるソースからのデータをその属性に設定します。 ユーザーが Windows Server AD から同期されている場合、または HR システムからプロビジョニングされている場合は、そのアップストリーム ソースでその変更を行う必要がある場合があります。
3. [Microsoft Entra アプリケーション プロビジョニングの属性マッピング構成を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)更新し、一致する規則にこの属性を含めます。

#### Microsoft Entra ID に既に設定されている別の属性に一致ルールを変更する

アプリケーション ギャラリー内のアプリケーションの既定の照合規則は、 `userPrincipalName`など、すべての Microsoft 顧客のすべての Microsoft Entra ID ユーザーに共通する属性に依存します。 これらの規則は、汎用テストや、現在ユーザーがいない新しいアプリケーションへのプロビジョニングに適しています。 ただし、多くの組織では、従業員 ID など、組織に関連する他の属性を Microsoft Entra ID ユーザーに既に設定している可能性があります。 照合に適した別の属性がある場合は、 [Microsoft Entra アプリケーション プロビジョニング属性マッピング構成を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes) 更新し、この属性を照合ルールに含めます。

#### HR ソースから Microsoft Entra ID への受信プロビジョニングを構成する

理想的には、複数のアプリケーションにユーザーを個別にプロビジョニングしている組織は、人事システムなどの権限のあるソースから派生したユーザーの共通識別子に依存する必要があります。 多くの人事システムには、2 人の従業員が同じ従業員 ID を持たないように一意として扱うことができる `employeeId` など、識別子と同様に機能するプロパティがあります。 Workday や SuccessFactors などの人事ソースがある場合、そのソースから employeeId などの属性を取り込むことは、多くの場合、適切な一致ルールを作成できます。

権限のあるソースから取得した値を持つ属性を照合に使用するには、次の手順を実行します。

1. 適切な Microsoft Entra ID ユーザー スキーマ属性を選択するか、新しい属性を使用して Microsoft Entra ユーザー スキーマを拡張します。その値は、アプリケーション内のユーザーの同等のプロパティに対応します。
2. Microsoft Entra ID とアプリケーションの両方にユーザーとして登録されているすべての人について、そのプロパティが HR ソースにも存在していることを確認します。
3. その HR ソースから Microsoft Entra ID への受信プロビジョニングを構成します。
4. Microsoft Entra ID のユーザーが新しい属性で更新されるまで待ちます。
5. [Microsoft Entra アプリケーション プロビジョニングの属性マッピング構成を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)更新し、一致する規則にこの属性を含めます。

#### 継続的なアプリケーション アクセスが必要なアプリケーション内のユーザーに対して Windows Server AD でユーザーを作成する

権限のある HR ソースのユーザーに対応していないが、今後 Windows Server AD ベースのアプリケーションと Microsoft Entra ID 統合アプリケーションの両方にアクセスする必要があるアプリケーションのユーザーが存在し、組織が Microsoft Entra Connect Sync または Microsoft Entra Cloud Sync を使用して Windows Server AD から Microsoft Entra ID にユーザーをプロビジョニングしている場合、 その後、Windows Server AD で、まだ存在していないユーザーごとにユーザーを作成できます。

ユーザーが Windows Server AD ベースのアプリケーションにアクセスする必要がない場合は、次のセクションで説明するように、Microsoft Entra ID でユーザーを作成します。

#### 継続的なアプリケーション アクセスが必要なアプリケーション内のユーザーに対して Microsoft Entra ID でユーザーを作成する

権限のある人事ソースのユーザーに対応していないが、継続的なアクセスが必要であり、Microsoft Entra から管理されるアプリケーションのユーザーがいる場合は、Microsoft Entra ユーザーを作成できます。 次のいずれかを使用して、ユーザーを一括で作成できます。

- CSV ファイル (「[Microsoft Entra 管理センターでのユーザーの一括作成](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add)」で説明されています)
- [New-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/new-mguser?view=graph-powershell-1.0#examples&preserve-view=true) コマンドレット

これらの新しいユーザーには、後で Microsoft Entra ID がアプリケーション内の既存のユーザーと一致するために必要な属性と、Microsoft Entra ID で必要な属性 ( `userPrincipalName`、 `mailNickname`、 `displayName`など) が設定されていることを確認します。 `userPrincipalName` は、ディレクトリ内のすべてのユーザー間で一意である必要があります。

##### PowerShell を使用したユーザーの一括作成

このセクションでは、[Microsoft Graph PowerShell](https://www.powershellgallery.com/packages/Microsoft.Graph) コマンドレットを使用して Microsoft Entra ID を操作する方法を示します。

このシナリオのために組織でこれらのコマンドレットを初めて使用する場合は、テナントで Microsoft Graph PowerShell を使用できるように、グローバル管理者ロールである必要があります。 後続の対話では、ユーザー管理者などの低い特権ロールを使用できます。

1. Microsoft Entra ID に含まれていないアプリケーションのユーザーを特定した PowerShell セッションが既にある場合は、次の手順 6 に進みます。 それ以外の場合は、PowerShell を開きます。
2. [Microsoft Graph PowerShell モジュール](https://www.powershellgallery.com/packages/Microsoft.Graph)がまだインストールされていない場合は、次のコマンドを使用して `Microsoft.Graph.Users` モジュールなどをインストールします。

    ```powershell
    Install-Module Microsoft.Graph
    ```

    これらのモジュールが既にインストールされている場合は、最新バージョンを使用していることを確認します。

    ```powershell
    Update-Module microsoft.graph.users,microsoft.graph.identity.governance,microsoft.graph.applications
    ```
3. Microsoft Entra ID に接続します。

    ```powershell
    $msg = Connect-MgGraph -ContextScope Process -Scopes "User.ReadWrite.All"
    ```
4. このコマンドを初めて使用する場合は、Microsoft Graph コマンド ライン ツールにこれらのアクセス許可を付与することを許可する必要があります。
5. PowerShell 環境に、アプリケーションのユーザーの配列を取り込みます。この配列には、Microsoft Entra ID の必須属性であるフィールド (ユーザー プリンシパル名、メールニックネーム、ユーザーのフル ネーム) も含まれます。 このスクリプトでは、配列 `$dbu_not_matched_list` に、一致しなかったアプリケーションのユーザーが含まれていることを前提としています。

    ```powershell
    $filename = ".\Users-to-create.csv"
    $bu_not_matched_list = Import-Csv -Path $filename -Encoding UTF8
    ```
6. 作成するユーザーの配列のどの列が Microsoft Entra ID 必須プロパティに対応するかを PowerShell セッションで指定します。 たとえば、`EMail` という名前の列の値が Microsoft Entra のユーザー プリンシパル名として使用したい値であり、列 `Alias` の値に Microsoft Entra ID のメール ニックネームが含まれ、列 `Full name` の値にユーザーの表示名が含まれているようなユーザーが、データベースに存在する場合があります。

    ```powershell
    $db_display_name_column_name = "Full name"
    $db_user_principal_name_column_name = "Email"
    $db_mail_nickname_column_name = "Alias"
    ```
7. テキスト エディターで次のスクリプトを開きます。 このスクリプトを変更して、アプリケーションで必要な Microsoft Entra 属性を追加したり、 `$azuread_match_attr_name` が `mailNickname` または `userPrincipalName`されていない場合は、その Microsoft Entra 属性を指定する必要があります。

    ```powershell
    $dbu_missing_columns_list = @()
    $dbu_creation_failed_list = @()
    foreach ($dbu in $dbu_not_matched_list) {
       if (($null -ne $dbu.$db_display_name_column_name -and $dbu.$db_display_name_column_name.Length -gt 0) -and
           ($null -ne $dbu.$db_user_principal_name_column_name -and $dbu.$db_user_principal_name_column_name.Length -gt 0) -and
           ($null -ne $dbu.$db_mail_nickname_column_name -and $dbu.$db_mail_nickname_column_name.Length -gt 0)) {
          $params = @{
             accountEnabled = $false
             displayName = $dbu.$db_display_name_column_name
             mailNickname = $dbu.$db_mail_nickname_column_name
             userPrincipalName = $dbu.$db_user_principal_name_column_name
             passwordProfile = @{
               Password = -join (((48..90) + (96..122)) * 16 | Get-Random -Count 16 | % {[char]$_})
             }
          }
          try {
            New-MgUser -BodyParameter $params
          } catch { $dbu_creation_failed_list += $dbu; throw }
       } else {
          $dbu_missing_columns_list += $dbu
       }
    }
    ```
8. テキスト エディターから生成されたスクリプトを PowerShell セッションに貼り付けます。 エラーが発生した場合は、続行する前に修正する必要があります。

#### アプリケーションと Microsoft Entra ID で独立したユーザーと一致しないユーザーを保つ

アプリケーションのデータ ソースに、Microsoft Entra ID の特定のユーザーに対応していないスーパー管理者ユーザーが存在する可能性があります。 ユーザーに対して Microsoft Entra ユーザーを作成しない場合、それらのユーザーは Microsoft Entra ID または Microsoft Entra ID ガバナンスから管理できなくなります。 これらのユーザーは Microsoft Entra ID でサインインできないため、Microsoft Entra ID を ID プロバイダーとして使用するようにアプリケーションを構成する場合は、それらのユーザーが認証に Microsoft Entra ID を使用する範囲外であることを確認してください。

### ユーザーの再エクスポート

Microsoft Entra ユーザー、アプリケーション内のユーザー、または Microsoft Entra アプリケーション照合ルールを更新した後、すべてのユーザーが関連付けられるように、アプリケーションの照合手順を再度エクスポートして実行する必要があります。

- SAP Cloud Identity Services を使用している場合は、手順から始まる [SAP Cloud Identity Services プロビジョニング チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#ensure-existing-sap-cloud-identity-services-users-have-the-necessary-matching-attributes) に従って、既存の SAP Cloud Identity Services ユーザーに必要な一致する属性があることを確認します。 このチュートリアルでは、SAP Cloud Identity Services から CSV ファイルにユーザーの一覧をエクスポートし、PowerShell を使用してそれらのユーザーを Microsoft Entra ID のユーザーと照合します。
- アプリケーションで LDAP ディレクトリを使用している場合は、 [LDAP ディレクトリプロビジョニングのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)に従ってください。
- SQL データベースを使用するアプリケーションや、アプリケーション ギャラリーでプロビジョニング サポートを持つアプリケーションなど、他のアプリケーションの場合は、チュートリアルに従って [アプリケーションの既存のユーザーを管理します](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)。

### アプリケーション ロールにユーザーを割り当ててプロビジョニングを有効にする

必要な更新を完了し、アプリケーションのすべてのユーザーが Microsoft Entra ID のユーザーと一致することを確認したら、アプリケーションへのアクセスが必要な Microsoft Entra ID のユーザーを Microsoft Entra アプリケーション アプリ ロールに割り当ててから、アプリケーションへのプロビジョニングを有効にする必要があります。

- SAP Cloud Identity Services を使用している場合は、既存の Microsoft Entra ユーザーが必要な属性を持っていることを確認する手順から始めて、 [SAP Cloud Identity Services プロビジョニング チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#ensure-existing-microsoft-entra-users-have-the-necessary-attributes) を続行します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned"} -->
## 使用しているアプリケーションでユーザーがプロビジョニングされていない - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned
- Service: entra-id / app-provisioning
- Article date: 2025-04-15
- Summary: ユーザーが Microsoft Entra ID を使用してユーザー プロビジョニング用に構成された Microsoft Entra ギャラリー アプリケーションに表示されない場合に発生する一般的な問題のトラブルシューティングを行います。

アプリケーションの自動プロビジョニングが構成された後 (アプリに接続するために Microsoft Entra ID に指定されたアプリ資格情報が有効であることを確認するなど)、ユーザーやグループがアプリにプロビジョニングされます。 プロビジョニングは、次のものによって決定されます。

- アプリケーションに **割り当てられている** ユーザーとグループ。 入れ子になったグループのプロビジョニングはサポートされていません。 割り当ての詳細については、「 [Microsoft Entra ID でエンタープライズ アプリにユーザーまたはグループを割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)参照してください。
- **属性マッピング**が有効になっているかどうか、および Microsoft Entra ID からアプリに有効な属性を同期するように構成されているかどうか。 属性マッピングの詳細については、「 [Microsoft Entra ID での SaaS アプリケーションのユーザー プロビジョニング属性マッピングのカスタマイズ」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)参照してください。
- 特定の属性値に基づいてユーザーをフィルター処理する **スコープ フィルター** が存在するかどうか。 スコープ フィルターの詳細については、「スコープ フィルターを [使用した属性ベースのアプリケーション プロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。

ユーザーがプロビジョニングされていないことが確認された場合は、Microsoft Entra ID の [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context) 参照してください。 特定のユーザーのログ エントリを検索します。

Microsoft Entra 管理センターでプロビジョニング ログにアクセスするには、 **Entra ID**&gt;**Enterprise アプリ**&gt;**プロビジョニング ログを**参照します。 特定のアプリケーションを選択し、[**アクティビティ**] セクションで [**プロビジョニング ログ**] を選択することもできます。 プロビジョニング データは、ユーザー名か、ソース システムまたはターゲット システムの識別子に基づいて検索できます。 詳細については、 [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context)参照してください。

プロビジョニング ログには、プロビジョニング サービスによって実行されたすべての操作が記録されます。これには、プロビジョニングの対象となる割り当て済みのユーザーを Microsoft Entra ID で照会する操作、これらのユーザーが存在するかどうかを対象となるアプリで照会する操作、システム間でユーザー オブジェクトを比較する操作が含まれます。 その後、比較に基づいて、対象のシステムのユーザー アカウントを追加または更新するか、無効にします。

### プロビジョニング サービスが開始されていないように見える

**[アプリケーション名]** Microsoft Entra 管理センターの [プロビジョニング] セクション&gt;エンタープライズ アプリケーションでプロビジョニング&gt;を**オン**に設定した場合。 しかし、その後再読み込みしても、他の状態の詳細がページに表示されません。サービスは実行されていますが、初回サイクルがまだ完了していない可能性があります。 **プロビジョニング ログを**調べて、サービスが実行している操作とエラーがあるかどうかを判断します。

注

初回サイクルには 20 分から数時間かかることがあります。この時間は Microsoft Entra のディレクトリ サイズと、プロビジョニングの対象となるユーザーの数によって異なります。 初回サイクル後の両方のシステムの状態を表すウォーターマークがプロビジョニング サービスに保存されるため、初回サイクルの後の後続の同期はより速くなります。 初回サイクルにより、以降の同期のパフォーマンスが向上します。

### ユーザーを割り当てたにもかかわらず、ユーザーがスキップされておりプロビジョニングされていないとプロビジョニング ログに表示される

ユーザーがプロビジョニング ログに "スキップ済み" と表示された場合は、ログの [ **ステップ** ] タブを確認して理由を確認することが重要です。 一般的な理由と解決策:

- **属性値に基づいてユーザーをフィルターで除外する** **スコープ フィルターが構成されています**。 スコープ フィルターの詳細については、「スコープ フィルター」 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
- **ユーザーが "実質的に有効でない" 状態です。** この特定のエラー メッセージが表示された場合、その原因は、Microsoft Entra ID に格納されているユーザー割り当てレコードの問題です。 この問題を解決するには、アプリからユーザー (またはグループ) を割り当て解除し、再割り当てします。 割り当ての詳細については、「 [ユーザーまたはグループのアクセス権を割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)」を参照してください。
- **必須の属性が見つからないか、ユーザー用に設定されていません。** プロビジョニングを設定するときに考慮すべき重要なことは、どのユーザー (またはグループ) プロパティが Microsoft Entra ID からアプリケーションに提供されるかを定義する属性マッピングとワークフローを確認して構成することです。 この構成には、2 つのシステム間でユーザーまたはグループを一意に識別して照合するために使用される "照合プロパティ" の設定が含まれます。 この重要なプロセスの詳細については、「 [Microsoft Entra ID での SaaS アプリケーションのユーザー プロビジョニング属性マッピングのカスタマイズ」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。
- **グループの属性マッピング:** 一部のアプリケーションでサポートされている場合は、メンバーに加えて、グループ名とグループの詳細のプロビジョニング。 この機能を有効または無効にするには、[**プロビジョニング**] タブに表示されるグループ オブジェクトの**マッピング**を有効または無効にします。プロビジョニング グループが有効になっている場合は、属性マッピングを確認して、適切なフィールドが "一致する ID" に使用されていることを確認してください。 この照合 ID は、表示名または電子メール エイリアスである場合があります。 照合プロパティが空か、または Microsoft Entra ID のグループ用に設定されていない場合、グループとそのメンバーはプロビジョニングされません。

### 既定のアクセス ロールに割り当てられたユーザーのプロビジョニング

ギャラリーからのアプリケーションの既定のロールは、"既定のアクセス" ロールと呼ばれます。 これまで、このロールに割り当てられたユーザーはプロビジョニングされず、"効果的に資格がありません" ため [、プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs) ではスキップ済みとしてマークされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility"} -->
## System for Cross-Domain Identity Management (SCIM) 2.0 プロトコル準拠に関する既知の問題 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility
- Service: entra-id / app-provisioning
- Article date: 2025-08-25
- Summary: SCIM 2.0 をサポートするギャラリー以外のアプリケーションを Microsoft Entra ID に追加するときに発生する、一般的なプロトコル互換性の問題を解決する方法

Microsoft Entra ID は、 [システム for Cross-Domain Identity Management (SCIM) 2.0 プロトコル仕様](https://tools.ietf.org/html/draft-ietf-scim-api-19)で定義されているインターフェイスを使用して、Web サービスによって前面に出される任意のアプリケーションまたはシステムにユーザーとグループを自動的にプロビジョニングできます。

SCIM 2.0 プロトコルに対する Microsoft Entra のサポートについては、「 [System for Cross-Domain Identity Management (SCIM) を使用して Microsoft Entra ID からアプリケーションにユーザーとグループを自動的にプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)する」で説明されています。この場合、MICROSOFT Entra ID から SCIM 2.0 をサポートするアプリケーションにユーザーとグループを自動的にプロビジョニングするために実装されるプロトコルの特定の部分が一覧表示されます。

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの SCIM 2.0 プロトコルへの準拠に関する現在および過去の問題と、これらの問題を回避する方法について説明されています。

### プロビジョニング ジョブについて

プロビジョニング サービスでは、ジョブの概念を使用して、アプリケーションに対して操作を行います。 jobID は [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user#view-the-provisioning-progress-bar)にあります。 すべての新しいプロビジョニング アプリケーションは、 `scim`以降の jobID で作成されます。 scim ジョブはサービスの現在の状態を表します。 古いジョブには ID `customappsso`があります。 このジョブは、2018 年のサービスの状態を表します。

ギャラリーでアプリケーションを使用している場合、ジョブには通常、アプリの名前 (zoom snowFlake や dataBricks など) が含まれます。 ギャラリー アプリケーションを使用する場合は、このドキュメントをスキップできます。 これは主に、jobID が SCIM または customAppSSO の、ギャラリー以外のアプリケーションに適用されます。

### SCIM 2.0 へのコンプライアンスに関する問題と状態

この中で、固定としてマークされている項目は、SCIM ジョブで適切な動作が見つかることを意味します。 Microsoft は、加えた変更の下位互換性を確保するように取り組んでいます。 新しい実装用の新しい動作を使用し、既存の実装を更新することをお勧めします。 2018 年 12 月より前の既定の customappSSO 動作はサポートされなくなりました。

注意

2018 年に行われた変更については、customappsso の動作に戻す可能性があります。 2018 年以降に加えられた変更については、URL を使用して以前の動作に戻すことができます。 Microsoft では、以前の jobID に戻せるようにするか、フラグを使用して、Microsoft が加えた変更の下位互換性を確保するようにしました。 ただし、既に説明したように、サポートされなくなった古い動作を実装することはお勧めしません。 新しい実装用の新しい動作を使用し、既存の実装を更新することをお勧めします。

| **SCIM 2.0 コンプライアンスの問題** | **付け。** | **修正日** | **下位互換性** |
| --- | --- | --- | --- |
| Microsoft Entra ID では、"/scim" がアプリケーションの SCIM エンドポイント URL のルート内にある必要がある | はい | 2018 年 12 月 18 日 | customappSSO にダウングレード |
| 拡張属性では、コロン ":" 表記ではなく、属性名の前にドット `.` 表記が使用されます | はい | 2018 年 12 月 18 日 | customappSSO にダウングレード |
| 複数値属性のパッチ要求に無効なパス フィルター構文が含まれている | はい | 2018 年 12 月 18 日 | customappSSO にダウングレード |
| グループの作成要求に無効なスキーマ URI が含まれている | はい | 2018 年 12 月 18 日 | customappSSO にダウングレード |
| PATCH の動作を更新してコンプライアンスを確保する (ブール値としての active、適切なグループ メンバーシップの削除など) | いいえ | 未定 | 機能フラグの使用 |

### SCIM の動作を変更するフラグ

既定の SCIM クライアント動作を変更するには、アプリケーションのテナント URL のフラグを使用します。

[Image: SCIM フラグを後の動作に設定します。]

次の URL を使用して PATCH の動作を更新し、SCIM へのコンプライアンスを確保します。 フラグは、次の動作を変更します。

- ユーザーを無効にするために行われる要求
- 単一値の文字列属性を追加するための要求
- 複数の属性を置き換える要求
- グループのメンバーを削除する要求

この動作は、現在、フラグを使用している場合にのみ使用できますが、今後数か月以内に既定の動作になる予定です。 この機能フラグは、現在、オンデマンド プロビジョニングでは機能しません。

- **URL (SCIM 準拠):** aadOptscim062020
- **SCIM RFC リファレンス:**
    - https://tools.ietf.org/html/rfc7644#section-3.5.2

同期エンジンが現在送信している内容と、機能フラグが有効になった後に送信される要求の概要を示すサンプル要求。

**ユーザーを無効にするために行われた要求:**

**機能フラグなし**

```json
{
  "schemas": [
      "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
      {
          "op": "Replace",
          "path": "active",
          "value": "False"
      }
  ]
}
```

**機能フラグ付き**

```json
{
  "schemas": [
      "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
      {
          "op": "replace",
          "path": "active",
          "value": false
      }
  ]
}
```

**単一値の文字列属性を追加するために行われた要求:**

**機能フラグなし**

```json
{
  "schemas": [
      "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
    {
      "op": "Add",
      "path": "nickName",
      "value": "Babs"
    }
  ]
}
```

**機能フラグ付き**

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
  "Operations": [
    {
      "op": "add",
      "path": "nickName",
      "value": "Babs"
    }
  ]
}
```

**複数の属性を置き換える要求:**

**機能フラグなし**

```json
{
  "schemas": [
      "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
      {
          "op": "Replace",
          "path": "displayName",
          "value": "Pvlo"
      },
      {
          "op": "Replace",
          "path": "emails[type eq \"work\"].value",
          "value": "TestBcwqnm@test.microsoft.com"
      },
      {
          "op": "Replace",
          "path": "name.givenName",
          "value": "Gtfd"
      },
      {
          "op": "Replace",
          "path": "name.familyName",
          "value": "Pkqf"
      },
      {
          "op": "Replace",
          "path": "externalId",
          "value": "Eqpj"
      },
      {
          "op": "Replace",
          "path": "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber",
          "value": "Eqpj"
      }
  ]
}
```

**機能フラグ付き**

```json
{
  "schemas": [
      "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
      {
          "op": "replace",
          "path": "emails[type eq \"work\"].value",
          "value": "TestMhvaes@test.microsoft.com"
      },
      {
          "op": "replace",
          "value": {
              "displayName": "Bjfe",
              "name.givenName": "Kkom",
              "name.familyName": "Unua",
              "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber": "Aklq"
          }
      }
  ]
}
```

**グループ メンバーを削除するために行われた要求:**

**機能フラグなし**

```json
{
  "schemas": [
      "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
      {
          "op": "Remove",
          "path": "members",
          "value": [
              {
                  "value": "u1091"
              }
          ]
      }
  ]
}
```

**機能フラグ付き**

```json
{
  "schemas": [
      "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
      {
          "op": "remove",
          "path": "members[value eq \"7f4bc1a3-285e-48ae-8202-5accb43efb0e\"]"
      }
  ]
}
```

- **ダウングレード URL:** ギャラリー以外のアプリケーションで新しい SCIM 準拠の動作が既定値になったら、次の URL を使用して、SCIM に準拠していない古い動作にロールバックできます: AzureAdScimPatch2017

### 前の customappsso ジョブから SCIM ジョブへのアップグレード

既存の `customappsso` ジョブを削除し、新しい SCIM ジョブを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を参照します。
3. 既存の SCIM アプリケーションを見つけて選択します。
4. 既存の SCIM アプリの **[プロパティ]** セクションで、 **オブジェクト ID をコピーします**。
5. 新しい Web ブラウザー ウィンドウで https://developer.microsoft.com/graph/graph-explorer に移動し、アプリの追加先の Microsoft Entra テナントの管理者としてサインインします。
6. Graph エクスプローラーで次のコマンドを実行して、プロビジョニング ジョブの ID を確認します。 "[object-id]" を、手順 3 でコピーしたサービス プリンシパル ID (オブジェクト ID) に置き換えます。

    `GET https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs`

    [Image: ジョブの取得 ジョブ]
7. 結果内で、"customappsso" または "scim" のいずれかで始まる完全な "ID" 文字列をコピーします。
8. バックアップを作成するために、次のコマンドを実行して属性マッピング構成を取得します。 前と同じ [object-id] を使用し、[job-id] を、最後の手順でコピーしたプロビジョニング ジョブ ID に置き換えます。

    `GET https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/[job-id]/schema`
9. 最後の手順から JSON 出力をコピーし、テキスト ファイルに保存します。 この JSON には、前のアプリに追加したすべてのカスタム属性マッピングが含まれており、およそ数千行の JSON となります。
10. 次のコマンドを実行して、プロビジョニング ジョブを削除します。

    `DELETE https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/[job-id]`
11. 次のコマンドを実行して、最新のサービス修正プログラムを含む新しいプロビジョニング ジョブを作成します。

`POST https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs``{   "templateId": "scim"   }`

1. 最後の手順の結果内で、"scim" で始まる完全な "ID" 文字列をコピーします。 必要に応じて、次のコマンドで、[new-job-id] をコピーした新しいジョブ ID に置き換え、要求本文として手順 7 の JSON 出力を入力してコマンドを実行し、前の属性マッピングを再適用します。

`PUT https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/[new-job-id]/schema``{   <your-schema-json-here>   }`

1. 最初の Web ブラウザー ウィンドウに戻り、アプリケーションの [プロビジョニング] タブ **を** 選択します。
2. 構成を確認し、プロビジョニング ジョブを開始します。

### SCIM ジョブから customappsso ジョブへのダウングレード (非推奨)

古い動作にダウングレードできますが、customappsso は私たちが行う更新プログラムの一部の恩恵を受けず、永遠にサポートされない可能性があるため、お勧めしません。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を参照します。
3. [ **アプリケーションの作成** ] セクションで、新しい **ギャラリー以外の** アプリケーションを作成します。
4. 新しいカスタム アプリの **[プロパティ** ] セクションで、 **オブジェクト ID をコピーします**。
5. 新しい Web ブラウザー ウィンドウで https://developer.microsoft.com/graph/graph-explorer に移動し、アプリの追加先の Microsoft Entra テナントの管理者としてサインインします。
6. Graph エクスプローラーで次のコマンドを実行して、アプリのプロビジョニング構成を初期化します。 "[object-id]" を、手順 3 でコピーしたサービス プリンシパル ID (オブジェクト ID) に置き換えます。

    `POST https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs``{   templateId: "customappsso"   }`
7. 最初の Web ブラウザー ウィンドウに戻り、アプリケーションの [プロビジョニング] タブ **を** 選択します。
8. 通常どおりにユーザー プロビジョニング構成を完了します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/application-provisioning-configuration-api"} -->
## Microsoft Graph API を使用してプロビジョニングを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Graph API を使用して自動プロビジョニングの構成を自動化し、時間を節約する方法について説明します。

Microsoft Entra 管理センターは、個々のアプリのプロビジョニングを一度に 1 つずつ構成する便利な方法です。 しかし、アプリケーションのインスタンスを複数作成する場合 (または作成するインスタンスの数が数百にも及ぶ場合、アプリケーション構成をある環境から別の環境に移行する場合) は、Microsoft Graph API を使用してアプリの作成と構成を自動化すると、より簡単です。 この記事では、API を使用してプロビジョニングの構成を自動化する方法について説明します。 この方法は、[アマゾン ウェブ サービス](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-web-service-tutorial#configure-azure-ad-sso)などのアプリケーションで一般的に使用されています。

この記事では、[Microsoft Graph ベータ エンドポイント](https://learn.microsoft.com/ja-jp/graph/api/overview?view=graph-rest-beta&preserve-view=true)と Microsoft Graph Explorer での API を使用したプロセスについて説明します。同様の API は、[Microsoft Graph v1.0 エンドポイント](https://learn.microsoft.com/ja-jp/graph/api/overview?view=graph-rest-1.0&preserve-view=true)でも使用できます。 Graph v1.0 と PowerShell を使用してプロビジョニングを構成する例については、「[PowerShell または Microsoft Graph API を使用してテナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure-graph?tabs=ms-powershell)」の手順 6 から 13 を参照してください。

**Microsoft Graph API を使用してプロビジョニングの構成を自動化する手順の概要**

| 手順 | 詳細 |
| --- | --- |
| 手順 1. ギャラリー アプリケーションを作成する | API クライアントにサインインする  ギャラリー アプリケーション テンプレートを取得する  ギャラリー アプリケーションを作成する |
| 手順 2. テンプレートに基づいてプロビジョニング ジョブを作成する | プロビジョニング コネクタのテンプレートを取得する  プロビジョニング ジョブを作成する |
| 手順 3. アクセスを承認する | アプリケーションへの接続をテストする  資格情報を保存する |
| 手順 4. プロビジョニング ジョブを開始する | ジョブの開始 |
| 手順 5. プロビジョニングを監視する | プロビジョニング ジョブの状態を確認する  プロビジョニング ログを取得する |

オンプレミスのアプリケーションにプロビジョニングする場合は、プロビジョニング エージェントをインストールして構成し、プロビジョニング エージェントをアプリケーションに割り当てる必要もあります。

### 手順 1:ギャラリー アプリケーションを作成する

#### Microsoft Graph エクスプローラー (推奨)、または使用しているその他の API クライアントにサインインする

1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を開始します。
2. [Microsoft アカウントでサインイン] ボタンを選択し、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロールを持つユーザーでサインインします。
3. サインインに成功すると、左側のペインにユーザー アカウントの詳細が表示されます。

#### ギャラリー アプリケーション テンプレート識別子を取得する

Microsoft Entra アプリケーション ギャラリーのアプリケーションにはそれぞれ、そのアプリケーションのメタデータを記述する[アプリケーション テンプレート](https://learn.microsoft.com/ja-jp/graph/api/applicationtemplate-list?tabs=http&view=graph-rest-beta&preserve-view=true)があります。 このテンプレートを使用して、管理用のテナントにアプリケーションとサービス プリンシパルのインスタンスを作成できます。 **AWS Single-Account Access** のアプリケーション テンプレートとその応答の識別子を取得し、**id** プロパティの値を記録します。これは、このチュートリアルの後の部分で使用します。

##### リクエスト

```msgraph
GET https://graph.microsoft.com/beta/applicationTemplates?$filter=displayName eq 'AWS Single-Account Access'
```

##### [応答]

```http
HTTP/1.1 200 OK
Content-type: application/json

{
  "value": [
  {
    "id": "8b1025e4-1dd2-430b-a150-2ef79cd700f5",
        "displayName": "AWS Single-Account Access",
        "homePageUrl": "http://aws.amazon.com/",
        "supportedSingleSignOnModes": [
             "password",
             "saml",
             "external"
         ],
         "supportedProvisioningTypes": [
             "sync"
         ],
         "logoUrl": "https://az495088.vo.msecnd.net/app-logo/aws_215.png",
         "categories": [
             "developerServices"
         ],
         "publisher": "Amazon",
         "description": "Federate to a single AWS account and use SAML claims to authorize access to AWS IAM roles. If you have many AWS accounts, consider using the AWS Single Sign-On gallery application instead."    

}
```

#### ギャラリー アプリケーションを作成する

前のステップで取得したアプリケーションのテンプレート ID を使用して、ご自身のテナント内にアプリケーションとサービス プリンシパルの[インスタンスを作成](https://learn.microsoft.com/ja-jp/graph/api/applicationtemplate-instantiate?tabs=http&view=graph-rest-beta&preserve-view=true)します。

##### リクエスト

```msgraph
POST https://graph.microsoft.com/beta/applicationTemplates/{applicationTemplateId}/instantiate
Content-type: application/json

{
  "displayName": "AWS Contoso"
}
```

##### [応答]

```http
HTTP/1.1 201 OK
Content-type: application/json

{
    "application": {
        "objectId": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
        "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",
        "applicationTemplateId": "8b1025e4-1dd2-430b-a150-2ef79cd700f5",
        "displayName": "AWS Contoso",
        "homepage": "https://signin.aws.amazon.com/saml?metadata=aws|ISV9.1|primary|z",
        "replyUrls": [
            "https://signin.aws.amazon.com/saml"
        ],
        "logoutUrl": null,
        "samlMetadataUrl": null,
    },
    "servicePrincipal": {
        "objectId": "bbbbbbbb-1111-2222-3333-cccccccccccc",
        "appDisplayName": "AWS Contoso",
        "applicationTemplateId": "8b1025e4-1dd2-430b-a150-2ef79cd700f5",
        "appRoleAssignmentRequired": true,
        "displayName": "My custom name",
        "homepage": "https://signin.aws.amazon.com/saml?metadata=aws|ISV9.1|primary|z",
        "replyUrls": [
            "https://signin.aws.amazon.com/saml"
        ],
        "servicePrincipalNames": [
            "93653dd4-aa3a-4323-80cf-e8cfefcc8d7d"
        ],
        "tags": [
            "WindowsAzureActiveDirectoryIntegratedApp"
        ],
    }
}
```

### 手順 2. テンプレートに基づいてプロビジョニング ジョブを作成する

#### プロビジョニング コネクタのテンプレートを取得する

ギャラリー内の、プロビジョニングが有効になっているアプリケーションには、構成を効率化するためのテンプレートがあります。 次の要求を使用して、[プロビジョニング構成のテンプレートを取得](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronization-list-templates?preserve-view=true&tabs=http&view=graph-rest-beta)します。 ID を指定する必要があることに注意してください。 ID は、前のステップで作成した servicePrincipal リソースの ID です。

##### リクエスト

```msgraph
GET https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/templates
```

##### [応答]

```http
HTTP/1.1 200 OK

{
    "value": [
        {
            "id": "aws",
            "factoryTag": "aws",
            "schema": {
                    "directories": [],
                    "synchronizationRules": []
                    }
        }
    ]
}
```

#### プロビジョニング ジョブを作成する

プロビジョニングを有効にするには、まず[ジョブを作成する](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronization-post-jobs?preserve-view=true&tabs=http&view=graph-rest-beta)必要があります。 プロビジョニング ジョブを作成するには、次の要求を使用します。 ジョブに使用するテンプレートを指定するときは、前の手順の templateId を使用します。

##### リクエスト

```msgraph
POST https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/jobs
Content-type: application/json

{ 
    "templateId": "aws"
}
```

##### [応答]

```http
HTTP/1.1 201 OK
Content-type: application/json

{
    "id": "{jobId}",
    "templateId": "aws",
    "schedule": {
        "expiration": null,
        "interval": "P10675199DT2H48M5.4775807S",
        "state": "Disabled"
    },
    "status": {
        "countSuccessiveCompleteFailures": 0,
        "escrowsPruned": false,
        "synchronizedEntryCountByType": [],
        "code": "NotConfigured",
        "lastExecution": null,
        "lastSuccessfulExecution": null,
        "lastSuccessfulExecutionWithExports": null,
        "steadyStateFirstAchievedTime": "0001-01-01T00:00:00Z",
        "steadyStateLastAchievedTime": "0001-01-01T00:00:00Z",
        "quarantine": null,
        "troubleshootingUrl": null
    }
}
```

### 手順 3. アクセスを承認する

#### アプリケーションへの接続をテストする

サードパーティ アプリケーションとの接続をテストします。 クライアント シークレットとシークレット トークンを必要とするアプリケーションの例を次に示します。 アプリケーションにはそれぞれ独自の要件があります。 多くの場合、クライアント シークレットの代わりにベース アドレスがアプリケーションで使用されます。 アプリで必要な資格情報を確認するには、アプリケーションのプロビジョニング構成ページに移動し、開発者モードで **[テスト接続]** をクリックします。 ネットワーク トラフィックに、資格情報に使用されたパラメーターが表示されます。 資格情報の完全な一覧については、「[synchronizationJob: validateCredentials](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-validatecredentials?tabs=http&view=graph-rest-beta&preserve-view=true)」を参照してください。 Azure Databricks などのほとんどのアプリケーションは、BaseAddress と SecretToken に依存しています。 BaseAddress は、Microsoft Entra 管理センターではテナント URL と呼ばれます。

##### リクエスト

```msgraph
POST https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/jobs/{jobId}/validateCredentials

{ 
    "credentials": [ 
        { 
            "key": "ClientSecret", "value": "xxxxxxxxxxxxxxxxxxxxx" 
        },
        {
            "key": "SecretToken", "value": "xxxxxxxxxxxxxxxxxxxxx"
        }
    ]
}
```

##### [応答]

```http
HTTP/1.1 204 No Content
```

#### 資格情報を保存する

プロビジョニングを構成するには、Microsoft Entra ID とアプリケーションの間に信頼を確立して、サードパーティのアプリケーションを呼び出す機能を持つことを Microsoft Entra に承認する必要があります。 クライアント シークレットとシークレット トークンを必要とするアプリケーションの例を次に示します。 アプリケーションにはそれぞれ独自の要件があります。 [API ドキュメント](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-validatecredentials?tabs=http&view=graph-rest-beta&preserve-view=true)を参照して、使用可能なオプションを確認してください。

##### リクエスト

```msgraph
PUT https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/secrets 

{ 
    "value": [ 
        { 
            "key": "ClientSecret", "value": "xxxxxxxxxxxxxxxxxxxxx"
        },
        {
            "key": "SecretToken", "value": "xxxxxxxxxxxxxxxxxxxxx"
        }
    ]
}
```

##### [応答]

```http
HTTP/1.1 204 No Content
```

### 手順 4: プロビジョニング ジョブを開始する

プロビジョニング ジョブが構成されたので、次のコマンドを使用して[ジョブを開始](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-start?tabs=http&view=graph-rest-beta&preserve-view=true)します。

##### リクエスト

```http
POST https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/jobs/{jobId}/start
```

##### [応答]

```http
HTTP/1.1 204 No Content
```

### 手順 5: プロビジョニングを監視する

#### プロビジョニング ジョブの状態を監視する

プロビジョニング ジョブが実行されているので、次のコマンドを使って進行状況を追跡します。 応答の各[同期ジョブ](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-synchronizationjob)には、現在のプロビジョニング サイクルの[状態](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-synchronizationstatus)と共に、ターゲット システムで作成されたユーザーとグループの数など、現在までの統計情報が含まれます。

##### リクエスト

```msgraph
GET https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/jobs
```

##### [応答]

```http
HTTP/1.1 200 OK
Content-type: application/json

{ "value": [
{
    "id": "{jobId}",
    "templateId": "aws",
    "schedule": {
        "expiration": null,
        "interval": "P10675199DT2H48M5.4775807S",
        "state": "Disabled"
    },
    "status": {
        "countSuccessiveCompleteFailures": 0,
        "escrowsPruned": false,
        "synchronizedEntryCountByType": [],
        "code": "Paused",
        "lastExecution": null,
        "lastSuccessfulExecution": null,
        "progress": [],
        "lastSuccessfulExecutionWithExports": null,
        "steadyStateFirstAchievedTime": "0001-01-01T00:00:00Z",
        "steadyStateLastAchievedTime": "0001-01-01T00:00:00Z",
        "quarantine": null,
        "troubleshootingUrl": null
    },
    "synchronizationJobSettings": [
      {
          "name": "QuarantineTooManyDeletesThreshold",
          "value": "500"
      }
    ]
}
]
}
```

#### プロビジョニング ログを使用してプロビジョニング イベントを監視する

プロビジョニング ジョブの状態の監視に加えて、[プロビジョニング ログ](https://learn.microsoft.com/ja-jp/graph/api/provisioningobjectsummary-list?tabs=http&view=graph-rest-beta&preserve-view=true)を使用して、発生しているすべてのイベントについてクエリを実行できます。 たとえば、特定のユーザーについてクエリを実行し、正常にプロビジョニングされたかどうかを判断します。

##### リクエスト

```msgraph
GET https://graph.microsoft.com/beta/auditLogs/provisioning
```

##### [応答]

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#auditLogs/provisioning",
    "value": [
        {
            "id": "gc532ff9-r265-ec76-861e-42e2970a8218",
            "activityDateTime": "2019-06-24T20:53:08Z",
            "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
            "cycleId": "44576n58-v14b-70fj-8404-3d22tt46ed93",
            "changeId": "eaad2f8b-e6e3-409b-83bd-e4e2e57177d5",
            "action": "Create",
            "durationInMilliseconds": 2785,
            "sourceSystem": {
                "id": "0404601d-a9c0-4ec7-bbcd-02660120d8c9",
                "displayName": "Azure Active Directory",
                "details": {}
            },
            "targetSystem": {
                "id": "cd22f60b-5f2d-1adg-adb4-76ef31db996b",
                "displayName": "AWS Contoso",
                "details": {
                    "ApplicationId": "00001111-aaaa-2222-bbbb-3333cccc4444",
                    "ServicePrincipalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
                    "ServicePrincipalDisplayName": "AWS Contoso"
                }
            },
            "initiatedBy": {
                "id": "",
                "displayName": "Azure AD Provisioning Service",
                "initiatorType": "system"
            }
            ]
       }
    ]
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/application-provisioning-log-analytics"} -->
## プロビジョニング ログと Azure Monitor の統合方法について説明します - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra Provisioning ログを Azure Monitor ログと統合し、関連するブックを使用する方法について説明します。

プロビジョニングは、Azure Monitor ログおよび Log Analytics と統合されています。 Azure Monitoring を使用すると、ブック (ダッシュボードとも呼ばれます) の作成、プロビジョニング ログの 30 日以上の保存、カスタム クエリとアラートの作成などを行うことができます。 この記事では、プロビジョニング ログを Azure Monitor ログと統合する方法について説明します。 プロビジョニング ログの一般的な動作の詳細については、 [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)参照してください。

### プロビジョニング ログ統合の有効化

Azure Monitor と Log Analytics にまだ慣れていない場合は、次のリソースを確認してから改めてアプリケーション プロビジョニング ログと Azure Monitor ログの統合について学習してください。

- [Azure Monitor の概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)
- [Log Analytics ワークスペースを構成する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace)
- [アクティビティ ログを Azure Monitor ログと統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)

プロビジョニング ログと Azure Monitor ログを統合するには:

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. [Log Analytics ワークスペースを作成します](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)。
3. **Entra ID**&gt;**監視と健康**&gt;**診断設定**に移動します。

    [Image: 診断設定へのアクセスのスクリーンショット。]
4. ストリーミングするログを選択し、[ **Log Analytics ワークスペースに送信** ] オプションを選択して、フィールドに入力します。

    [Image: アプリケーション プロビジョニング ログを有効にするスクリーンショット。]
5. **Entra ID**&gt;**Monitoring & health**&gt;**Log Analytics** に移動し、データのクエリを開始します。

注

最初に統合を有効にした後、Log Analytics 内にログが表示されるまでに時間がかかる場合があります。 *microsoft.insights* を使用するためにサブスクリプションが登録されていないというエラーが発生した場合は、数分後にもう一度確認してください。

### データの説明

プロビジョニングからログ ビューアーに送信される基のデータ ストリームはほぼ同じです。 Azure Monitor ログは、Microsoft Entra 管理センターや Microsoft Graph API とほぼ同じストリームを取得します。 次の表に示すように、ログ フィールドにはいくつかの違いがあります。 Log Analytics では、Microsoft Entra 管理センター内のログよりも多くのイベントが表示される場合があります。 これらのフィールドの詳細については、「 [list provisioningObjectSummary](https://learn.microsoft.com/ja-jp/graph/api/provisioningobjectsummary-list?preserve-view=true&tabs=http&view=graph-rest-beta)」を参照してください。

| Azure Monitor ログ | Azure ポータル ユーザーインターフェース | Azure API |
| --- | --- | --- |
| エラー説明 | 理由 | 結果の説明 |
| 状態 | 結果タイプ | 結果タイプ |
| 活動日時 | タイムジェネレイテッド | タイムジェネレイテッド |

### Microsoft Entra ブック

Microsoft Entra ID ブックには、データ分析用の柔軟性の高いキャンバスが用意されています。 また、Azure portal 内で上質なビジュアル レポートを作成することもできます。 詳細については、 [Microsoft Entra ブックを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks)参照してください。

**プロビジョニング分析**と**プロビジョニングの分析情報**は、使用可能な事前構築済みのブックの 2 つです。 データを表示するには、すべてのフィルター (timeRange、jobID、appName) を設定する必要があります。 また、アプリがプロビジョニングされていることも確認します。プロビジョニングされていないと、ログにはデータがありません。

[Image: アプリケーション プロビジョニング ワークブック]

[Image: アプリケーション プロビジョニング ダッシュボード]

### カスタム クエリ

カスタム クエリを作成し、ブックの中にそのデータを表示できます。 方法については、「[Azure Monitor のログ クエリの始め方](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/get-started-queries)」および「[Azure Monitor のログ クエリ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-query-overview)」を参照してください。

アプリケーション プロビジョニング ログ クエリを開始するためのいくつかのサンプルを次に示しています。

ソース システムの ID に基づいて、ユーザーのログに対してクエリを実行する:

```kusto
AADProvisioningLogs
| extend SourceIdentity = parse_json(SourceIdentity)
| where tostring(SourceIdentity.Id) == "49a4974bb-5011-415d-b9b8-78caa7024f9a"
```

ErrorCode あたりの集計数:

```kusto
AADProvisioningLogs
| summarize count() by ErrorCode = ResultSignature
```

アクション別の 1 日あたりのイベント数の集計:

```kusto
AADProvisioningLogs
| where TimeGenerated > ago(7d)
| summarize count() by Action, bin(TimeGenerated, 1d)
```

100 イベントとプロジェクト キーのプロパティを取得する:

```kusto
AADProvisioningLogs
| extend SourceIdentity = parse_json(SourceIdentity)
| extend TargetIdentity = parse_json(TargetIdentity)
| extend ServicePrincipal = parse_json(ServicePrincipal)
| where tostring(SourceIdentity.identityType) == "Group"
| project tostring(ServicePrincipal.Id), tostring(ServicePrincipal.Name), ModifiedProperties, JobId, Id, CycleId, ChangeId, Action, SourceIdentity.identityType, SourceIdentity.details, TargetIdentity.identityType, TargetIdentity.details, ProvisioningSteps
| take 100
```

参照の解決に問題があるため、スキップされたメンバーがあるグループを取得します。

```kusto
AADProvisioningLogs
| where TimeGenerated >= ago(10d)
| where JobId == "Azure2Azure.73f0883f-d67d-4af1-ac8a-45367f8982e0.5ef3be57-f45f-451g-88c4-68a7fda680bb" // Customize by adding a specific app JobId
| extend SourceIdentity = parse_json(SourceIdentity)
| extend ProvisioningSteps = parse_json(ProvisioningSteps)
| where tostring(SourceIdentity.identityType) == "Group"
| where ProvisioningSteps matches regex "UnableToResolveReferenceAttributeValue"
| parse tostring(ProvisioningSteps.[2].description) with "We were unable to assign " userObjectId " as the members of " groupDisplayName "." *
| project groupDisplayName, userObjectId,  JobId
| take 100
```

アプリケーション別にアクションを要約します。

```kusto
AADProvisioningLogs
| where TimeGenerated > ago(30d)
| where JobId == "Azure2Azure.73f0883f-d67d-4af1-ac8a-45367f8982e0.5ef3be57-f45f-451g-88c4-68a7fda680bb" // Customize by adding a specific app JobId
| extend ProvisioningSteps = parse_json(ProvisioningSteps)
| extend eventName = tostring(ProvisioningSteps.[-1].name)
| summarize count() by eventName, JobId
| order by JobId asc
| take 5
```

特定の操作の急増を特定します。

```kusto
AADProvisioningLogs
| where TimeGenerated > ago(30d)
| where JobId == "scim.73f0883f-d67d-4af1-ac8a-45367f8982e0.5ef3be57-f45f-451g-88c4-68a7fda680bb" // Customize by adding a specific app JobId
| extend ProvisioningSteps = parse_json(ProvisioningSteps)
| extend eventName = tostring(ProvisioningSteps.[-1].name)
| summarize count() by eventName, bin(TimeGenerated, 1d)
| render timechart
```

### カスタム アラート

Azure Monitor を使用すると、プロビジョニングに関連する主要なイベントに関する通知を受け取ることができるように、カスタム アラートを構成することができます。 たとえば、失敗、無効化、または削除の急増に関するアラートを受け取る場合があります。 プロビジョニングが不足している場合は、何か問題があることを示すアラートを受け取ることもできます。

アラートの詳細については、 [Azure Monitor ログ アラート](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/alerts-create-new-alert-rule)に関するページを参照してください。 多くのオプションと構成があるため、完全なドキュメントを確認してください。 ただし、大まかに言うと、アラートを作成する方法を次に示します。

1. Log Analytics で、[ **+ 新しいアラート ルール**] を選択します。
2. [ **条件** ] タブで、[ **結果の表示とログでのクエリの編集** ] リンクを選択します。
3. アラートを生成するクエリを入力し、アラートを作成するために必要なフィールドに入力します。

障害が急増したときにアラートを作成するには:

```kusto
AADProvisioningLogs
| where JobId == "string" // Customize by adding a specific app JobId
| where ResultType == "Failure"
```

プロビジョニング サービスが動作しなくなる原因となった問題が存在する可能性があります。 次のクエリを使用して、特定の時間間隔中にプロビジョニング イベントがない場合を検出します。

```kusto
AADProvisioningLogs
| take 1
```

無効化または削除が急増したときにアラートを作成するには:

```kusto
AADProvisioningLogs
| where Action in ("Disable", "Delete")
```

### コミュニティからの投稿

Microsoft では、アプリケーションのプロビジョニングのクエリとダッシュボードに対して、オープン ソースとコミュニティベースのアプローチを採用しています。 他のユーザーにとって役に立つと思われるクエリ、アラート、またはブックを作成し、 [それを AzureMonitorCommunity GitHub リポジトリ](https://github.com/microsoft/AzureMonitorCommunity)に発行します。 そして、リンクを含むメールを Microsoft までお送りください。 Microsoft がクエリとダッシュボードをレビューしてサービスに公開し、他のユーザーも利用できるようにします。 provisioningfeedback@microsoft.com までお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/application-provisioning-quarantine-status"} -->
## Microsoft Entra アプリケーション プロビジョニングの検疫の状態 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: 自動ユーザー プロビジョニング用にアプリケーションを構成したら、検疫のプロビジョニング状態とクリアする方法について説明します。

Microsoft Entra プロビジョニング サービスは、構成の正常性を監視します。 また、異常なアプリを "検疫" 状態にします。 ターゲット システムに対する呼び出しのほとんどまたはすべてが常に失敗する場合、プロビジョニング ジョブは検疫状態になります。 失敗の例としては、無効な管理者資格情報が原因で受け取るエラーがあります。

検疫中は、次のようになります。

- 増分サイクルの頻度は徐々に減少し、最終的には 1 日 1 回になります。
- すべてのエラーが修正されると、プロビジョニング ジョブは検疫から削除され、次の同期サイクルが開始します。
- プロビジョニング ジョブが検疫にとどまっている期間が 4 週間を超えると、そのプロビジョニング ジョブは無効 (実行が停止) になります。

### アプリケーションが検疫されているかどうかを確認するにはどうすればよいですか。

アプリケーションが検疫中かどうかを確認するには、次の 3 つの方法があります。

- Microsoft Entra 管理センターで、 **Entra ID**&gt;**Enterprise apps**&gt;&lt;*application name*&gt;&gt;**Provisioning** に移動し、検疫メッセージの進行状況バーを確認します。

    [Image: 検疫状態を示すプロビジョニング ステータス バー]
- Microsoft Entra 管理センターで、Entra IDMonitoring & HealthAudit Logs を開き、活動: 検疫のフィルタを適用して、検疫履歴を確認します。 前に説明した進行状況バーのビューは、プロビジョニングが現在検疫中かどうかを示しています。 監査ログには、アプリケーションの検疫履歴が表示されます。
- Microsoft Graph 要求の [Get synchronizationJob](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-get?tabs=http&view=graph-rest-beta&preserve-view=true) を使用して、プロビジョニング ジョブの状態をプログラムで取得します。

```microsoft
        GET https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/jobs/{jobId}/
```

- メールを確認します。 アプリケーションが検疫されると、1 回限りの通知メールが送信されます。 検疫の理由が変化した場合、新しい検疫理由を示す更新されたメールが送信されます。 メールが送信されない場合は、次を確認します。

    - アプリケーションのプロビジョニング構成で有効な**通知メール**を指定していることを確認します。
    - 通知メールの受信トレイにスパム フィルターが適用されていないことを確認します。
    - メールの登録を解除していないことを確認します。
    - `azure-noreply@microsoft.com` からの電子メールを確認します。

### アプリケーションが検疫されているのはなぜですか。

アプリケーションが検疫に入れられる一般的な理由を次に示します

| 説明 | 推奨される操作 |
| --- | --- |
| **SCIM へのコンプライアンスの問題:** 予期される HTTP/200 OK 応答ではなく、HTTP/404 Not Found 応答が返されました。 この場合、Microsoft Entra プロビジョニング サービスによってターゲット アプリケーションへの要求が行われ、予期しない応答を受け取りました。 | 管理者資格情報のセクションを確認します。 アプリケーションでテナントの URL を指定する必要があるかどうか、および URL が正しいことを確認します。 問題が見つからない場合は、アプリケーションの開発者に連絡し、サービスが SCIM に準拠していることを確認してください。 https://tools.ietf.org/html/rfc7644#section-3.4.2 |
| **無効な資格情報:** ターゲット アプリケーションへのアクセスの承認を試みたとき、指定された資格情報が無効であることを示す応答をターゲット アプリケーションから受け取りました。 | プロビジョニング構成 UI の管理者資格情報セクションに移動し、有効な資格情報で再度アクセスを承認してください。 アプリケーションがギャラリーにある場合は、アプリケーション構成のチュートリアルで追加の手順を確認してください。 |
| **重複したロール:** Salesforce や Zendesk などの特定のアプリケーションからインポートされるロールは、一意である必要があります。 | Microsoft Entra 管理センターでアプリケーションの[マニフェスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)に移動し、重複するロールを削除します。 |

プロビジョニング ジョブの状態を取得するための Microsoft Graph 要求では、次の検疫理由が示されます。

- `EncounteredQuarantineException` は、無効な資格情報が指定されたことを示します。 プロビジョニング サービスは、ソース システムとターゲット システム間の接続を確立できません。
- `EncounteredEscrowProportionThreshold` は、プロビジョニングがエスクローのしきい値を超えたことを示します。 この状態は、40% 以上のプロビジョニング イベントが失敗すると発生します。 詳細については、エスクローのしきい値の詳細を以下で確認してください。
- `QuarantineOnDemand` は、アプリケーションの問題を検出し、検疫するよう手動で設定したことを意味します。

**エスクローのしきい値**

エスクローの相応なしきい値に達した場合、プロビジョニング ジョブは検疫に入ります。 このロジックは変更される可能性がありますが、ほぼ次のように動作します。

ジョブは、管理者の資格情報や SCIM コンプライアンスなどのイシューの失敗数に関係なく、検疫に入ることがあります。 ただし、一般に、失敗が多いことが原因での検疫を行うかどうかの評価を開始するには、最低でも 5,000 の失敗が必要です。 たとえば、4,000 の失敗が発生したジョブは検疫に入りません。 しかし、5,000 の失敗が発生したジョブでは、評価がトリガーされます。 評価では、次の条件が使用されます。

- プロビジョニング イベントの 40% が失敗した場合、または 40,000 個を超える失敗が発生した場合、プロビジョニング ジョブは検疫に入ります。 参照の失敗は、40% のしきい値または 40,000 のしきい値の一部としてカウントされません。 たとえば、マネージャーまたはグループ メンバーの更新の失敗は、参照の失敗です。
- 45,000 人のユーザーが正常にプロビジョニングされなかったジョブは、40,000 のしきい値を超えているため、検疫に入ります。
- 30,000 人のユーザーがプロビジョニングに失敗し、5,000 人は正常に完了したジョブは、40% のしきい値と 5,000 の最小値を超えているため、検疫に入ります。
- 20,000 の失敗が発生し、100,000 が成功したジョブは、40% の失敗のしきい値または 40,000 の失敗の最大値を超えていないため、検疫に入りません。
- 参照と非参照の両方の失敗から成る、60,000 の失敗の絶対しきい値があります。 たとえば、40,000 人のユーザーのプロビジョニングに失敗し、21,000 人のマネージャーの更新が失敗したとします。 失敗の合計は 61,000 であり、60,000 の制限を超えています。

**再試行期間**

ここに記載されているロジックは、最高のカスタマー エクスペリエンスを保証するために特定のコネクタで異なる場合がありますが、通常の失敗後の再試行サイクルは次のとおりです。

1 回目の再試行は、失敗の 6 時間後に行われます。

- 2 回目の再試行は、最初の失敗の 12 時間後に行われます。
- 3 回目の再試行は、最初の失敗の 24 時間後に行われます。

3 回目の再試行から 24 時間ごとに再試行が行われます。 再試行は、最初のエラーの後、エスクロー エントリが削除され、ジョブが無効になってから 28 日間続きます。 上記のいずれかの再試行が成功した場合、ジョブは自動的に検疫から除外され、通常の同期動作を再開します。

### アプリケーションを検疫しないよう設定するにはどうすればよいですか。

まず、アプリケーションが検疫される原因となった問題を解決します。

- アプリケーションのプロビジョニング設定を調べ、[有効な管理者の資格情報](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal#configuring-automatic-user-account-provisioning)が入力されていることを確認してください。 Microsoft Entra ID は、ターゲット アプリケーションとの信頼関係を確立する必要があります。 有効な資格情報を入力し、アカウントに必要なアクセス許可があることを確認します。
- [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)を確認して、検疫の原因となっているエラーを詳しく調査し、解決します。 **[Microsoft Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[プロビジョニング ログ]** (**[アクティビティ]** セクション内) に移動します。

問題を解決したら、プロビジョニング ジョブを再開します。 属性マッピングやスコープフィルターなど、アプリケーションのプロビジョニング設定に特定の変更を加えると、自動的にプロビジョニングが再開されます。 アプリケーションの **[プロビジョニング]** ページの進行状況バーは、プロビジョニングが最後に開始された日時を示します。 プロビジョニング ジョブを手動で再起動する必要がある場合は、次のいずれかの方法を使用します。

- Microsoft Entra 管理センターを使用して、プロビジョニング ジョブを再起動します。 アプリケーションの**プロビジョニング**ページで、**プロビジョニングを再開する**を選択します。 この操作により、プロビジョニング サービスが完全に再起動されます (しばらく時間がかかることがあります)。 すべての初回サイクルが再度実行されます。これにより、エスクローがクリアされ、アプリが検疫から削除され、透かしがクリアされます。 このサービスでは、ソース システム内のすべてのユーザーを再度評価し、プロビジョニングの対象になっているかどうかを判断します。 これは、この記事で説明されているように、アプリケーションが現在検査中である場合や、属性マッピングを変更する必要がある場合に便利です。 初期サイクルは、評価が必要なオブジェクトの数が多いため、通常の増分サイクルよりも完了までに時間がかかることに注意してください。 初期サイクルと増分サイクルのパフォーマンスの詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を参照してください。
- Microsoft Graph を使用して、[プロビジョニング ジョブを再起動します](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-restart?tabs=http&view=graph-rest-beta&preserve-view=true)。 再起動する対象は完全に制御できます。 エスクローをクリアするか (検疫状態と判定されるためのエスクロー カウンターを再起動するため)、検疫をオフにするか (検疫からアプリケーションを削除するため)、または透かしをクリアするかを選択できます。 次の要求を使用します。

```microsoft
        POST /servicePrincipals/{id}/synchronization/jobs/{jobId}/restart
```

"{Id}" をアプリケーション ID の値に置き換え、"{jobId}" を[同期ジョブの ID](https://learn.microsoft.com/ja-jp/graph/synchronization-configure-with-directory-extension-attributes?preserve-view=true&tabs=http&view=graph-rest-beta#list-synchronization-jobs-in-the-context-of-the-service-principal) に置き換えます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user"} -->
## Microsoft Entra アプリケーションのプロビジョニングで、特定のユーザーがいつアプリケーションにアクセスできるようになるかを確認する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: 非常に重要なユーザーが、Microsoft Entra ID でユーザー プロビジョニング向けに構成したアプリケーションにいつアクセスできるようになるかを確認する方法。

Microsoft Entra プロビジョニング サービスは、ソース システムとターゲット システムに対して初回プロビジョニング サイクルを実行した後、増分サイクルを定期的に行います。 アプリのプロビジョニングを構成するときに、現在のプロビジョニング サービスの状態をチェックすることで、ユーザーがいつ頃アプリにアクセスできるようになるかを予想できます。

### プロビジョニング進行状況バーを確認する

アプリの [ **プロビジョニング** ] ページで、Microsoft Entra プロビジョニング サービスの状態を表示できます。 ページの下部にある [ **現在の状態]** セクションには、プロビジョニング サイクルがユーザー アカウントのプロビジョニングを開始したかどうかを示します。 サイクルの進行状況を監視して、プロビジョニング済みのユーザーとグループの数を確認したり、作成されたロールの数を確認したりすることができます。

自動プロビジョニングを最初に構成すると、ページの下部にある [ **現在の状態]** セクションに、初期プロビジョニング サイクルの状態が表示されます。 増分サイクルが実行されるたびに、このセクションが更新されます。 次の情報が表示されます。

- 現在実行されているプロビジョニング サイクルまたは最後に完了したプロビジョニング サイクルの種類 (初回または増分)。
- 完了したプロビジョニング サイクルの割合を示す **進行状況バー** 。 このパーセンテージは、プロビジョニングされたページの数を表します。 各ページには複数のユーザーまたはグループが含まれている可能性もあるため、プロビジョニングされたユーザー、グループ、ロールの数とこのパーセンテージとの間に、直接的な相関関係はありません。
- ビューの更新を維持するために使用できる **[更新** ] ボタン。
- コネクタ データ ストア内の **ユーザー** と **グループ** の数。 この数は、オブジェクトがプロビジョニングのスコープに追加されるたびに増加します。 ユーザーが論理的に削除されたり、物理的に削除されたりしても、カウントは減りません。これは、削除操作がコネクタ データ ストアからオブジェクトを削除しないためです。 CDS が[リセット](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-restart?tabs=http&view=graph-rest-beta&preserve-view=true)された後、最初の同期で数が再計算されます。
- Microsoft Entra プロビジョニング ログを開くための**プロビジョニングログの表示**リンク。 個々のユーザーのプロビジョニング状態など、ユーザー プロビジョニング サービスによって実行される操作の詳細については、この記事の後半の 「プロビジョニング ログを使用する 」を参照してください。

プロビジョニング サイクルが完了すると、[ **統計終了日** ] セクションには、現在までにプロビジョニングされたユーザーとグループの累積数と、最後のサイクルの完了日と期間が表示されます。 **アクティビティ ID** は、最新のプロビジョニング サイクルを一意に識別します。 **ジョブ ID** はプロビジョニング ジョブの一意の識別子であり、テナント内のアプリに固有です。

プロビジョニングの進行状況は、Microsoft Entra 管理センターの **Entra ID**&gt;**Enterprise アプリ**&gt; [*アプリケーション名*] &gt;**プロビジョニング**で表示されます。

[Image: プロビジョニング ページの進行状況バー]

Microsoft Graph を使用して、アプリケーションへのプロビジョニングの状態をプログラムで監視することもできます。 詳細については、[プロビジョニングの監視](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#step-5-monitor-provisioning)を参照してください。

### プロビジョニング ログを使用してユーザーのプロビジョニング状態を確認する

選択したユーザーのプロビジョニング状態を確認するには、Microsoft Entra ID の [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context) 参照してください。 ユーザー プロビジョニング サービスによって実行された操作はすべて、Microsoft Entra のプロビジョニング ログ (プレビュー) に記録されます。 このログには、ソース システムとターゲット システムに対して実行された読み取りおよび書き込み操作が含まれます。 読み取りおよび書き込み操作に関連する関連ユーザー データもログに記録されます。

Microsoft Entra 管理センターでプロビジョニング ログにアクセスするには、[**アクティビティ**] セクションで &gt;&gt;**Provisioning logs** を選択します。 プロビジョニング データは、ユーザー名か、ソース システムまたはターゲット システムの識別子に基づいて検索できます。 詳細については、 [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context)参照してください。

プロビジョニング ログには、プロビジョニング サービスによって実行されたすべての操作が記録されます。これには、次の操作が含まれます。

- プロビジョニングの対象となる割り当て済みユーザーを Microsoft Entra ID で照会する
- これらのユーザーが存在するかどうかを対象となるアプリで照会する
- システム間でユーザー オブジェクトを比較する
- 比較に基づいて、対象のシステムのユーザー アカウントを追加または更新するか、無効にする

Microsoft Entra 管理センターでプロビジョニング ログを読み取る方法の詳細については、 [プロビジョニング レポート ガイドを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)参照してください。

### ユーザーをプロビジョニングするにはどのくらいの時間がかかりますか。

ユーザー、グループ、またはグループメンバーシップをプロビジョニングする時間は、いくつかの要因によって異なります。

- プロビジョニングのスコープ内のユーザー、グループ、およびグループ メンバーシップが多いほど、同期ジョブの完了にかかる時間は長くなります。 たとえば、10 グループの同期ジョブ (それぞれが 20,000 人のメンバーを持つ) は、20K メンバーのグループが 1 つの同期ジョブよりもプロビジョニングに時間がかかります。
- 同期スコープが "すべてのユーザーとグループを同期する" に設定されている場合、同期エンジンは初期サイクル中にテナント内のすべてのユーザーとグループを評価します。 これにより、同期エンジンはスコープ内のオブジェクトを特定できます。 その結果、ソース システム (Microsoft Entra ID など) に存在するユーザー、グループ、およびグループ メンバーの合計数がパフォーマンスに影響します。
- ソース システムの変更の数は、増分サイクル中に更新プログラムをプロビジョニングする時間に影響します。 プロビジョニングの対象ではないユーザーまたはグループに対する変更 (テナントまたはグループ メンバーシップで新しく作成されたユーザーなど) は、サービスが変更を評価してスキップする必要があるため、パフォーマンスに影響を与える可能性があります。 これは、スコープが "すべてのユーザーとグループを同期する" 場合に特に重要です
- ターゲット システムによって、要求レートの制限および調整が実装される場合があり、大規模な同期動作中にパフォーマンスに影響する可能性があります。 このような条件下では、高速で大量の要求を受信するアプリは応答レートが遅くなったり、接続が閉じたりする場合があります。 ギャラリー アプリケーションは、アプリケーション開発者が設定したレート制限に従うように構成され、管理者がプロビジョニングを構成する必要はありません。
- すべてのユーザーが初めて作成される同期ジョブは、すべてのユーザーが既存のユーザーと一致する同期ジョブの約 2 倍の時間がかかります。
- 特定の同期サイクルでプロビジョニング サービスが再試行する必要があるエラーの数は、パフォーマンスに影響します。 進行状況バーと [プロビジョニング ログで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning) エラーがないか確認し、修復します。
- 検疫中のプロビジョニング ジョブは、頻度を低くして実行されます。 検疫の理由を確認し、修復して一般的な実行頻度を復元します。

構成 **の同期割り当てユーザーとグループの場合のみ**、次の数式を使用して、予想される **おおよそ** の最小サイクル時間と最大 **初期サイクル** 時間を決定できます。

- 最小時間 (分) = 0.01 x [割り当てられたユーザー、グループ、およびグループ メンバーの数]
- 最大時間 (分) = 0.08 x [割り当てられたユーザー、グループ、およびグループ メンバーの数]

### ユーザーまたはグループをプロビジョニングする時間を短縮するための推奨事項:

1. `assigned users and groups`ではなく、`sync all users and groups`を同期するようにプロビジョニング スコープを設定します。
2. プロビジョニングのスコープ内のユーザーとグループの数を最大化します。
3. 同じシステムを対象とする複数のプロビジョニング ジョブを作成します。 これを行うと、各同期ジョブは個別に動作するため、変更を処理する時間が短縮されます。 1 つのプロビジョニング ジョブの変更が別のジョブに影響しないように、各ジョブのユーザーのスコープが重複しないことを確認してください。
4. スコープ フィルターを追加して、プロビジョニングのスコープ内のユーザーとグループの数をさらに制限します。 パフォーマンスが問題になり、テナント内のユーザーとグループのほとんどをプロビジョニングしようとしている場合は、スコープ フィルターを使用します。 スコープ フィルターによって、ユーザーが特定の属性値に基づいてフィルター処理され、プロビジョニング サービスが Microsoft Entra ID から抽出するデータを細かく調整できるようになります。 スコープ フィルターの詳細については、「スコープ フィルターを [使用した属性ベースのアプリケーション プロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。

### プロビジョニング構成に対する変更を監査する

プロビジョニング構成の変更は監査ログに記録されます。 アプリケーション管理者やレポート閲覧者など、必要なアクセス許可を持つユーザーは、監査ログ UI、API、PowerShell を使用してログにアクセスできます。 監査ログのアクティビティ フィルターを使用すると、次のアクションを確認できます。

注

ユーザーの作成、ユーザーの更新、ユーザーの削除など、プロビジョニング サービスが実行するアクションについては、 [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-provisioning-logs)を使用することをお勧めします。 プロビジョニング構成の変更を監視するには、 [監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)を使用することをお勧めします。

[Image: 監査ログのスクリーンショット。]

| アクション | アクティビティ (このプロパティでログをフィルター処理します) |
| --- | --- |
| 資格情報を更新する (例: 新しいベアラー トークンを追加する) | プロビジョニングの設定または資格情報を更新する |
| プロビジョニング ジョブの設定 (例: 通知メール、「すべて同期」と「割り当てられたユーザーとグループを同期」の切り替え、誤削除の防止) を変更する | プロビジョニングの設定または資格情報を更新する |
| プロビジョニングの開始 | プロビジョニング構成を有効化または開始する |
| プロビジョニングの停止 | プロビジョニング構成を無効化または一時停止する |
| プロビジョニングを再開する | プロビジョニング構成を有効化または再開する |
| 属性のマッピングまたはスコープの規則を更新する | 属性のマッピングまたはスコープを更新する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/check-status-user-account-provisioning"} -->
## Microsoft Entra ID からサービスとしてのソフトウェア (SaaS) アプリケーションに自動ユーザー アカウント プロビジョニングを報告する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: 自動ユーザー アカウント プロビジョニング ジョブの状態を確認する方法と、個々のユーザーのプロビジョニングをトラブルシューティングする方法について説明します。

Microsoft Entra ID には、 [ユーザー アカウント プロビジョニング サービスが](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)含まれています。 このサービスは、SaaS アプリやその他のシステムでのユーザー アカウントのプロビジョニングとプロビジョニング解除を自動化するのに役立ちます。 自動化は、エンド ツー エンドの ID ライフサイクル管理に役立ちます。 Microsoft Entra ID では、多くのアプリケーションとシステム用に、事前に統合されたユーザー プロビジョニング コネクタがサポートされています。 ユーザー プロビジョニング チュートリアルの詳細については、「プロビジョニング チュートリアル」 [を](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)参照してください。

この記事では、セットアップ後にプロビジョニング ジョブの状態を確認する方法と、個々のユーザーとグループのプロビジョニングのトラブルシューティングを行う方法について説明します。

### 概要

プロビジョニング コネクタは、 [Microsoft Entra 管理センター](https://entra.microsoft.com)を使用して設定および構成されます。サポートされているアプリケーションに関して [提供されているドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) に従います。 コネクタを構成し、実行している場合、次の方法を使ってプロビジョニング ジョブをレポートできます。

- [Microsoft Entra 管理センター](https://entra.microsoft.com)の使用
- プロビジョニング ログを [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics) にストリーミングする。 この方法を使用すると、データ保持期間を延長でき、カスタムのダッシュボード、アラート、クエリを作成できます。
- プロビジョニング ログに対する [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/provisioningobjectsummary) のクエリ。
- プロビジョニング ログを CSV または JSON ファイルとしてダウンロードします。

#### 定義

この記事では、次の用語を使用しています。

- **ソース システム** - Microsoft Entra プロビジョニング サービスの同期元のユーザーのリポジトリ。 Microsoft Entra ID は、事前統合された大部分のプロビジョニング コネクタのソース システムです。ただし、いくつかの例外があります (例: Workday Inbound Synchronization)。
- **ターゲット システム** - Microsoft Entra プロビジョニング サービスが同期するユーザーのリポジトリ。 リポジトリは通常、Salesforce、ServiceNow、G Suite、Dropbox for Business などの SaaS アプリケーションです。 場合によっては、リポジトリは、Active Directory や、Workday Inbound Synchronization to Active Directory などのオンプレミス システムである場合があります。

### Microsoft Entra 管理センターからプロビジョニング レポートを取得する

特定のアプリケーションのプロビジョニング レポート情報を取得するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**にアクセスします。
3. [**アクティビティ**] セクションで [**プロビジョニング ログ**] を選択します。 プロビジョニングが構成されているエンタープライズ アプリケーションに移動することもできます。 たとえば、LinkedIn Elevate にユーザーをプロビジョニングしている場合、アプリケーションの詳細へのナビゲーション パスは、次のようになります。

**Entra ID**&gt;**エンタープライズ アプリ**&gt;**すべてのアプリケーション**&gt;**LinkedIn Elevate**

すべてのアプリケーション領域から、プロビジョニング進行状況バーとプロビジョニング ログの両方にアクセスします。

### プロビジョニング進行状況バー

[プロビジョニングの進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user#view-the-provisioning-progress-bar)は、特定のアプリケーションの [**プロビジョニング**] タブに表示されます。 [ **現在の状態]** セクションに表示され、現在の初期または増分サイクルの状態が表示されます。 このセクションには、以下も表示されます。

- ソース システムとターゲット システムの間で同期され、現時点でプロビジョニングの対象となっているユーザーとグループの合計数。
- 同期が最後に実行された時間。 同期は通常、 [最初のサイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#provisioning-cycles-initial-and-incremental) が完了した後、20 ~ 40 分ごとに行われます。
- 初期サイクルの状態と [、サイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#provisioning-cycles-initial-and-incremental) が完了したかどうか。
- プロビジョニング プロセスの状態と、検疫中かどうか。 状態には、検疫の理由も示されます。 たとえば、状態は、管理者の資格情報が無効なためにターゲット システムとの通信に失敗したことを示している可能性があります。

**現在の状態**は、管理者がプロビジョニング ジョブの運用状態を確認する最初の場所である必要があります。

[Image: 概要レポート]

Microsoft Graph を使用して、アプリケーションへのプロビジョニングの状態をプログラムで監視することもできます。 詳細については、[プロビジョニングの監視](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#step-5-monitor-provisioning)を参照してください。

### プロビジョニング ログ

プロビジョニング サービスによって実行されたアクティビティはすべて、Microsoft Entra のプロビジョニング ログに記録されます。 プロビジョニング ログには、Microsoft Entra 管理センターでアクセスできます。 プロビジョニング データは、ユーザー名か、ソース システムまたはターゲット システムの識別子に基づいて検索できます。 詳細については、 [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)参照してください。

### トラブルシューティング

プロビジョニング概要レポートとプロビジョニング ログは、管理者がユーザー アカウントのプロビジョニングに関するさまざまな問題をトラブルシューティングするときに重要な役割を果たします。

自動ユーザー プロビジョニングのトラブルシューティング方法に関するシナリオベースのガイダンスについては、「 [アプリケーションへのユーザーの構成とプロビジョニングに関する問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/troubleshoot)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/clear-attribute-values"} -->
## 属性値のクリア (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values
- Service: entra-id / app-provisioning
- Article date: 2026-08-20
- Summary: Microsoft Entraプロビジョニングでソース属性に null または空の値がある場合にターゲット属性値をクリアする方法について説明します。

Microsoft Entraプロビジョニングでは、対応するソース属性が null または空の場合に、既存のターゲット属性値をクリアできます。 "null 値のプロビジョニング" とも呼ばれるこの機能は、レコードのシステムから値が削除され、対応する値もターゲット システムから削除する必要がある場合に便利です。

Note

属性値のクリアは現在プレビュー段階です。 プレビューに適用される条件に基づき、お客様がプレビューを利用できるようにします。 これらの用語は、 [オンライン サービス](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)に関する Microsoft 製品の用語全体で概説されています。

属性値をクリアするための構成は、サポートされているプロビジョニング統合全体で一貫しています。 ただし、ソース スキーマ エディターとソース システムが null 値を表す方法は、統合によって異なる場合があります。

### プレビューのスコープと制限事項

- 属性値のクリアは、プレビューで次の目的で利用できます。
    - Microsoft Entra IDとオンプレミスの Active Directoryへの API 主導の受信プロビジョニング。
    - Workday から Microsoft Entra ID とオンプレミス Active Directory への受信型ユーザー プロビジョニング。
    - SAP SuccessFactors から Microsoft Entra ID とオンプレミスの Active Directory へのインバウンド ユーザー プロビジョニング
- 属性値のクリアは、単一値属性でのみサポートされます。
- この機能は、 [カスタム セキュリティ属性のプロビジョニングに対して](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-custom-security-attributes)有効にすることができます。
- 現在、Workday ライトバックや SAP SuccessFactors ライトバックなどの送信アプリケーション プロビジョニング シナリオでは、属性値のクリアはサポートされていません。
- 複数値属性のクリアはサポートされていません。

### 属性値のクリア方法

属性値のクリアは既定で無効になっており、オプトイン機能です。 属性値をクリアするには、2 ゲートセーフガード メカニズムを使用して、偶発的なデータ損失を防ぎます。 ソース属性とそれに対応するターゲット マッピングの両方に対して、 **オプション Flow null 値** を有効にする必要があります。

| ソース値 | クリアが有効な場合の結果 |
| --- | --- |
| 属性には空でない値があります。 | プロビジョニング サービスは、構成された属性マッピングを使用して値を評価します。 |
| 属性に null または空の値があります。 | 更新中、プロビジョニング サービスは、マップされたターゲット属性から既存の値をクリアします。 |
| API 駆動型のプロビジョニングでは、null フローが有効になっている属性を省略するとします。 | 更新中、プロビジョニング サービスは属性を空として扱い、マップされた既存のターゲット値をクリアします。 |

ユーザーの作成時に、null、空、または省略されたソース値は、 **null の場合に既定値**を構成しない限り、ターゲット属性を設定しません。 既定値は作成時にのみ適用されます。 更新中、プロビジョニング サービスは、作成時の既定値を適用するのではなく、ターゲット値をクリアします。

Note

空または null のソース値が既存のターゲット値を削除する必要がある属性に対してのみ、属性値のクリアを構成します。 ユーザーの照合または作成に必要な属性に対しては有効にしないでください。

### 前提条件

開始する前に次の点を確認します。

- 少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールがあります。
- プロビジョニング アプリケーションとその属性マッピングを構成しました。
- ソース属性はプロビジョニング アプリケーション スキーマに存在し、ターゲット属性マッピングがあります。 詳細については、「[Microsoft Entra属性マッピングのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)」を参照してください。

オンプレミスの Active Directory への API 駆動型インバウンド プロビジョニングでは、追加の[ロールとサーバーの前提条件](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app#prerequisites)を確認してから、[プロビジョニング エージェントと Active Directory 接続を構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app#configure-api-driven-inbound-provisioning-to-on-premises-ad)します。

HR ドリブン プロビジョニングの場合は、まず、サポートされている受信プロビジョニング アプリのいずれかを構成します。

- [Workday からオンプレミスの Active Directory へ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial)。
- [Workday から Microsoft Entra ID へ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial)。
- [SAP SuccessFactors からオンプレミスの Active Directory へ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)。
- [SAP SuccessFactors から Microsoft Entra ID へ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)。

### ソース属性の null 値フローを有効にする

まず、null 値をフローするようにソース スキーマ属性を構成します。 ソース スキーマ エディターの名前は、プロビジョニング統合によって異なります。

1. 少なくともアプリケーション管理者として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. プロビジョニング アプリケーションを検索して選択します。
4. **プロビジョニング**&gt;**属性マッピング** を選択します。
5. 構成するオブジェクトの属性マッピングを開きます。
6. [ **詳細オプション**] を展開し、ソース属性リストを編集するオプションを選択します。 オプション名は、ソース システムまたはコネクタを識別します。
7. **[フローの null 値]** 列で、マッピングされたターゲット属性をクリアできる各ソースの属性のチェック ボックスをオンにします。
8. **保存**を選びます。

### ターゲット マッピングで null 値フローを有効にする

次に、対応する各ターゲット属性マッピングに対して null 値フローを有効にします。

1. **[属性マッピング**] ページに戻ります。
2. クリアするターゲット属性のマッピングを選択します。
3. **フローの null 値** を選択します。
4. [ **OK] を** 選択してマッピング エディターを閉じます。
5. [ **保存] を** 選択して属性マッピングを保存します。
6. null 値を受け入れる必要があるすべてのターゲット属性に対して、これらの手順を繰り返します。

両方の設定が必要です。 ソース スキーマ属性またはターゲット マッピングに対して **Flow の null 値** が選択されていない場合、プロビジョニング サービスはターゲット属性をクリアするために null または空のソース値を使用しません。

### API 駆動型のプロビジョニングの例

次の例では、API ドリブンの受信プロビジョニングを構成して属性値をクリアし、 `/bulkUpload` 要求で null または空の値を送信する方法を示します。

#### ソース スキーマで null 値フローを有効にする

API ドリブン プロビジョニングの場合は、[ **詳細オプション**] を展開し、[ **API ユーザー属性の編集]** を選択します。 **[フローの null 値**] 列を見つけて、null または空の値を送信できるソース属性を選択します。 次の例では、SCIM 拡張属性の`division`と`department`に対して **Flow null 値**が有効になっています。 この設定は、これらの属性の null 値または空の値を処理し、null フローのターゲット属性マッピングを評価するようにプロビジョニング サービスに指示します。

[Image: API ユーザー属性スキーマの [Flow null values] 列が強調表示されているスクリーンショット。]

[Image: division 属性と department 属性で Flow の null 値が選択されている API ユーザー属性のスクリーンショット。]

Note

ソース属性でのみオプションを有効にしても、ターゲット値はクリアされません。次のセクションで説明するように、ターゲット マッピングでは null 値フローも許可する必要があります。

#### ターゲット マッピングで null 値フローを有効にする

対応する各ターゲット属性マッピングを開き、[ **フローの null 値**] を選択します。 次の例では、ソース SCIM 拡張属性`department`をActive Directory属性`department`にマップし、**フローの null 値**を有効にして、ソース SCIM 属性が null または空の場合、`department`属性値がActive Directoryでクリアされるようにします。

[Image: Flow null 値が選択されている属性マッピングのスクリーンショット。]

#### null または空の値を送信する

`/bulkUpload`要求に属性を含め、その値を JSON `null`または空の文字列に設定します。

Important

API 駆動型プロビジョニングの場合、 **Flow null 値** を有効にすると、部分ペイロードの処理方法が変わります。 たとえば、null フローが有効になっているソース属性を省略すると、プロビジョニング サービスはマップされたターゲット値をクリアします。 したがって、不完全または部分的なソース ペイロードは、既存の属性値を意図せずにクリアする可能性があります。 ベスト プラクティスとして、完全同期と差分同期の両方について、すべての一括要求に完全なソース ユーザー レコードを含め、明示的な JSON `null` または空の文字列を使用して確定的なクリアを要求します。

次の抜粋は、一致したユーザーの既存の `department` と `division` 値をクリアします。

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:BulkRequest"
  ],
  "Operations": [
    {
      "method": "POST",
      "bulkId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
      "path": "/Users",
      "data": {
        "schemas": [
          "urn:ietf:params:scim:schemas:core:2.0:User",
          "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"
        ],
        "externalId": "701984",
        "userName": "bjensen@example.com",
        "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
          "department": null,
          "division": ""
        }
      }
    }
  ],
  "failOnErrors": null
}
```

プロビジョニング アプリ スキーマのソース属性名を使用します。 `externalId`など、構成に必要な一致する属性が要求に含まれていることを確認します。

要求を送信する手順については、「Graph Explorer を使用した [API 主導の受信プロビジョニングのクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer) 」または [cURL を使用した API 駆動型受信プロビジョニングのクイック スタートを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-curl-tutorial)参照してください。

### Workday と SAP SuccessFactors のプロビジョニング

Workday と SAP SuccessFactors の受信プロビジョニングの場合、コネクタはプロビジョニング サイクル中に HR システムから属性値を直接取得します。 属性値をクリアする `/bulkUpload` 要求は送信しません。

1. ソース属性の null 値フローを有効にするの手順に従います。
2. [ **詳細オプション**] で、[ **Workday の属性リストの編集]** または **[SuccessFactors の属性リストの編集]** を選択します。
3. マップされたターゲット **値を** 削除する必要がある null または空の値を持つソース属性ごとに、フローの null 値のみを選択します。
4. 対応する各ターゲット属性の ターゲット マッピングで null 値フローを有効にする の手順に従います。
5. Workday または SAP SuccessFactors の値をクリアします。
6. 次のプロビジョニング サイクルでソースの変更を処理するか、 [オンデマンドでプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) を使用して特定のユーザーをテストできるようにします。

Workday は XPath 式を使用し、SAP SuccessFactors は JSONPath 式を使用してソース属性を取得します。 プロビジョニング サービスが null または空の値を評価するには、この属性がプロビジョニング アプリ スキーマに存在し、コネクタによって取得される必要があります。

Note

増分同期中、Workday はトランザクション ログに記録された変更のみを返します。 ソース属性の変更が検出されない場合は、「 [一部の Workday 属性の更新が見つからない](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-update-issues#some-workday-attribute-updates-are-missing)」を参照してください。

null 値または空の値が、値をクリアする代わりにフォールバック値で補完されるか、既存のターゲット値を保持する必要がある場合は、「[HR ユーザー更新の問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-update-issues#null-and-empty-values-not-processed-as-expected)」を参照してください。

### 属性がクリアされたことを確認する

プロビジョニング サービスがソースの更新を処理した後、プロビジョニング ログとターゲット ディレクトリの結果を確認します。

1. プロビジョニング アプリで、[ **プロビジョニング ログ**] を選択します。
2. ユーザーのプロビジョニング イベントを開きます。
3. [ **変更されたプロパティ** ] タブを選択します。
4. ターゲット属性に空の新しい値が表示されていることを確認します。

    [Image: 会社と部署の空の新しい値を示す変更されたプロパティのスクリーンショット。]
5. 属性がターゲット システムのオブジェクトに値を持たなくなったことを確認します。

このオンプレミスの Active Directory例では、API ソース属性`division`ターゲット属性`company`にマップされます。 オンプレミスの Active Directoryの場合、プロビジョニング サービスは空の文字列を書き込む代わりに属性値を削除します。

### 属性値のクリアに関するトラブルシューティング

ターゲット属性がクリアされない場合は、次のガイダンスを使用します。

| Issue | Resolution |
| --- | --- |
| null または空の値は無視されます。 | ソース スキーマ属性とターゲット マッピングの両方に対して **Flow の null 値** が選択されていることを確認します。 |
| 間違ったターゲット属性がクリアされます。 | 属性マッピングを確認し、ソース属性が予想されるターゲット属性にマップされていることを確認します。 |
| ソースでは、null の代わりにプレースホルダー値が使用されます。 | プレースホルダーではなく null または空の値を返すようにソース統合を構成するか、プロビジョニングする前にプレースホルダーを変換します。 |
| API 駆動型のプロビジョニング要求では、属性はクリアされません。 | 明示的な JSON `null` または空の文字列値を使用します。 子属性の省略に依存する場合は、含まれている複雑なオブジェクトまたはコレクション要素がペイロードに残っていることを確認します。 また、ペイロード一致属性によって既存のターゲット ユーザーが識別されることを確認します。 |
| API 駆動型のプロビジョニング要求によって、属性が予期せずクリアされます。 | 属性がペイロードから省略されていないことを確認します。 既存のターゲット値を保持したい場合は、その現在の空でない値を含めてください。 |
| Workday または SAP SuccessFactors の変更では、属性はクリアされません。 | HR コネクタがソースの変更を取得したことを確認します。 オンデマンドでプロビジョニングを使用してユーザーをテストし、コネクタから返されたソース値のプロビジョニング ログを確認します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/configure-automatic-user-provisioning-portal"} -->
## Microsoft Entra ID でのエンタープライズ アプリのユーザー プロビジョニング管理 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra ID を使用してエンタープライズ アプリのユーザー アカウント プロビジョニングを管理する方法について説明します。

この記事では、サポートされているアプリケーションの自動ユーザー アカウントのプロビジョニングとプロビジョニング解除を管理する一般的な手順について説明します。 "*ユーザー アカウントのプロビジョニング*" とは、アプリケーションのローカル ユーザー プロファイル ストアのユーザー アカウント レコードを作成、更新、無効化することです。 多くのオンプレミス アプリケーションに加えてほとんどのクラウドおよび SaaS アプリケーションでは、アプリケーション独自のローカル ユーザー プロファイル ストアにロールとアクセス許可が保存されます。 シングル サインオンとアクセスが機能するためには、アプリケーションのローカル ストア内にこのようなユーザー レコードが存在する "必要" があります。 自動ユーザー アカウント プロビジョニングの詳細については、[「Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を参照してください。

重要

Microsoft Entra ID には、Microsoft Entra ID を使用した自動プロビジョニングが有効になっている何千もの事前統合アプリケーションを含むギャラリーがあります。 まず、[SaaS アプリと Microsoft Entra ID を統合する方法に関するチュートリアルの 一覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)で、使用しているアプリケーションに固有のプロビジョニングのセットアップのチュートリアルを見つけてください。 ここでは、アプリと Microsoft Entra ID の両方を構成して、プロビジョニング接続を作成する段階的な手順が表示される可能性があります。

### ポータルでアプリを検索する

Microsoft Entra 管理センターを使用して、ディレクトリでのシングル サインオン用に構成されているすべてのアプリケーションを表示して管理します。 エンタープライズ アプリとは、組織内で使用されるデプロイ済みのアプリです。 エンタープライズ アプリを表示して管理するには、次の手順に従います。

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. ギャラリーから追加されたアプリを含む、構成済みのすべてのアプリの一覧が表示されます。
4. 任意のアプリを選択し、そのリソース ウィンドウを読み込み、そこでレポートを表示したり、アプリの設定を管理したりできます。
5. 選択したアプリのユーザー アカウントのプロビジョニング設定を管理するには、 **[プロビジョニング]** を選択します。

    [Image: ユーザー アカウントでのプロビジョニング設定を管理する [プロビジョニング] 画面]

### プロビジョニング モード

**[プロビジョニング]** ウィンドウの先頭には **[モード]** メニューがあり、エンタープライズ アプリケーションでサポートされているプロビジョニング モードが表示され、プロビジョニング モードを構成できます。 利用可能なオプションは、次のとおりです。

- **[自動]** - Microsoft Entra ID でこのアプリケーションに対する API ベースの自動ユーザー アカウント プロビジョニングやプロビジョニング解除がサポートされている場合、このオプションが表示されます。 このモードを選択すると、管理者に役立つインターフェイスが表示されます。

    - アプリケーションのユーザー管理 API に接続するようにMicrosoft Entra ID を構成する
    - Microsoft Entra ID とアプリの間でのユーザー アカウント データのフロー方法を定義するアカウント マッピングとワークフローを作成する
    - Microsoft Entra プロビジョニング サービスを管理する
- **[手動]** - Microsoft Entra ID でこのアプリケーションに対するユーザー アカウントの自動プロビジョニングがサポートされていない場合、このオプションが表示されます。 この場合は、アプリケーションが提供するユーザーの管理とプロビジョニング機能 (SAML のジャストインタイム プロビジョニングなど) に基づき、外部プロセスを使用して、そのアプリケーションに格納されているユーザー アカウント レコードを管理する必要があることを意味します。

### 自動ユーザー アカウント プロビジョニングを構成する

**[自動]** オプションを選択し、管理者の資格情報、マッピング、開始と停止、および同期の設定を指定します。

#### [Admin Credentials (管理者の資格情報)]

**[管理者資格情報]** を展開し、Microsoft Entra ID をアプリケーションのユーザー管理 API に接続するために必要な資格情報を入力します。 必要な入力は、アプリケーションによって異なります。 資格情報の種類と特定のアプリケーションの要件の詳細については、 [その特定のアプリケーションの構成に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を参照してください。

Microsoft Entra ID が、指定された資格情報を使用してアプリのプロビジョニング アプリへの接続を試みることで、**[接続テスト]** を選択して、資格情報をテストします。

#### マッピング

**[マッピング]** を展開し、ユーザー アカウントをプロビジョニングまたは更新する場合に、Microsoft Entra ID とターゲット アプリケーションの間でフローするユーザー属性を表示および編集します。

Microsoft Entra ユーザー オブジェクトと各 SaaS アプリのユーザー オブジェクトの間には、構成済みの一連のマッピングが存在します。 一部のアプリではグループ オブジェクトも管理します。 テーブルでマッピングを選択するとマッピング エディターが開き、それらを表示してカスタマイズできます。

サポートされるカスタマイズは次のとおりです。

- Microsoft Entra ユーザー オブジェクトと SaaS アプリのユーザー オブジェクトなど、特定のオブジェクトのマッピングを有効および無効にする。
- Microsoft Entra ユーザー オブジェクトからアプリのユーザー オブジェクトにフローする属性を編集する。 属性マッピングの詳細については、「 [属性マッピングの種類について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#understanding-attribute-mapping-types)」を参照してください。
- Microsoft Entra ID がターゲット アプリケーションに対して実行するプロビジョニング操作をフィルター処理する。 Microsoft Entra ID でオブジェクトを完全に同期するのではなく、実行される操作を制限することができます。

    たとえば、**[更新]** のみを選択すると、Microsoft Entra はアプリケーションの既存のユーザー アカウントの更新のみを行い、新しいユーザー アカウントは作成しません。 **[作成]** のみを選択すると、Azure は新しいユーザー アカウントの作成のみを行い、既存のユーザー アカウントは更新しません。 この機能により、アカウントの作成ワークフローと更新ワークフローで異なるマッピングを作成できます。
- 新しい属性マッピングを追加する。 **[属性マッピング]** ウィンドウの下部にある **[新しいマッピングの追加]** を選択します。 **[属性の編集]** フォームに記入し、 **[OK]** を選択してリストに新しいマッピングを追加します。

#### 設定

**[設定]** を展開して通知を受信するためのメール アドレスと、エラー発生時にアラートを受信するかどうかを設定します。 同期するユーザーのスコープも選択します。すべてのユーザーとグループ、または割り当てられているユーザーのみを同期することを選択します。

#### プロビジョニング状態

アプリケーションに対して初めてプロビジョニングを有効にする場合は、 **[プロビジョニング状態]** を **[オン]** に変更して、サービスを有効にします。 この変更により、Microsoft Entra プロビジョニング サービスで初期周期が実行されます。 これにより、**[ユーザーとグループ]** セクションで割り当てられたユーザーが読み取られ、そのユーザーのターゲット アプリケーションが照会され、Azure AD の **[マッピング]** セクションで定義されているプロビジョニング操作が実行されます。 このプロセス中に、プロビジョニング サービスは、管理対象のユーザー アカウントに関するキャッシュ データを格納します。 このサービスではキャッシュ データが格納されるため、割り当てのスコープに存在しない、ターゲット アプリケーション内の管理対象外のアカウントはプロビジョニング解除操作の影響を受けません。 初期周期の後、プロビジョニング サービスでは 40 分間隔で自動的にユーザーとグループ オブジェクトを同期します。

**[プロビジョニング状態]** を **[オフ]** に変更すると、プロビジョニング サービスが一時停止します。 この状態では、アプリのユーザーやグループ オブジェクトの作成、更新、削除が行われることはありません。 状態を **[オン]** に戻すと、サービスは中断したところから再開します。

ヒント

既存のユーザーが既に存在するアプリケーションの場合は、プロビジョニングを有効にする前に [、アカウント検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery) を使用して既存のアカウントを特定して分類します。 これにより、一致する属性の構成を確認し、重複するアカウントを作成しないようにすることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/configure-successfactors-api-user"} -->
## SAP SuccessFactors API ユーザーの構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-successfactors-api-user
- Service: entra-id / app-provisioning
- Article date: 2026-05-26
- Summary: 統合のプロビジョニングに使用できる API ユーザーを SAP SuccessFactors で構成する方法について説明します。

すべての SuccessFactors プロビジョニング コネクタの一般的な要件は、SuccessFactors OData API を呼び出すために適切なアクセス許可を持つ SuccessFactors アカウントの資格情報を必要とすることです。 このセクションでは、SuccessFactors でサービス アカウントを作成し、適切なアクセス許可を付与する手順を説明します。

### SuccessFactors で API ユーザー アカウントを作成または識別する

SuccessFactors 管理チームまたは実装パートナーと協力して、OData API を呼び出すために SuccessFactors のユーザー アカウントを作成または識別します。 Microsoft Entra ID でプロビジョニング アプリを構成する場合、このアカウントのユーザー名とパスワードの資格情報が必要になります。

### API アクセス許可ロールを作成する

1. 管理センターにアクセスできるユーザー アカウントで SAP SuccessFactors にサインインします。
2. [ **アクセス許可ロールの管理**] を検索し、検索結果から [ **アクセス許可ロールの管理** ] を選択します。

    [Image: SAP SuccessFactors 管理センターの [アクセス許可ロールの管理] 検索結果のスクリーンショット。]
3. アクセス許可ロールの一覧で、[ **新規作成**] を選択します。

[Image: [アクセス許可ロールの一覧] ページの [新規作成] ボタンのスクリーンショット。]
4. 新しいアクセス許可 **ロールのロール名** と **説明** を追加します。 名前と説明では、このロールが API 使用アクセス許可されていることを示す必要があります。
5. [アクセス許可の設定] で [ **アクセス許可...**] を選択し、アクセス許可の一覧を下にスクロールし、[ **統合ツールの管理**] を選択します。 [ **管理者が基本認証を使用して OData API にアクセスできるようにする**] チェック ボックスをオンにします。

[Image: [基本認証による OData API へのアクセスを管理者に許可する] オプションが選択されている [統合ツールの管理] アクセス許可のスクリーンショット。]
6. 同じボックスを下にスクロールし、[ **Employee Central API**] を選択します。 ODATA API を使用して読み取るアクセス許可を追加し、ODATA API を使用して編集します。 SuccessFactors への書き戻しシナリオで同じアカウントを使用する場合は、編集オプションを選択します。

[Image: ODATA API オプションを使用した読み取りと編集を示す Employee Central API のアクセス許可のスクリーンショット。]
7. 同じアクセス許可ボックスで、[ **ユーザーのアクセス許可**&gt;**Employee データ** ] に移動し、サービス アカウントが SuccessFactors テナントから読み取ることができる属性を確認します。 たとえば、SuccessFactors から **Username** 属性を取得するには、この属性に対して "表示" アクセス許可が付与されていることを確認します。 同様に、ビュー権限の各属性を確認します。

[Image: 属性へのビュー アクセスを確認するための Employee Data ユーザーのアクセス許可リストのスクリーンショット。]

    Note

    このプロビジョニング アプリによって取得される属性の完全な一覧については、「 [SuccessFactors 属性リファレンス」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)。
8. **完了**を選択します。 [ **変更の保存] を選択します**。

### API ユーザーのアクセス許可グループを作成する

1. SuccessFactors 管理センターで、[ **アクセス許可グループの管理**] を検索し、検索結果から **[アクセス許可グループの管理** ] を選択します。
[Image: SAP SuccessFactors 管理センターの [アクセス許可グループの管理] 検索結果のスクリーンショット。]
2. [アクセス許可グループの管理] ウィンドウで、[ **新規作成**] を選択します。
[Image: [アクセス許可グループの管理] ウィンドウの [新規作成] ボタンのスクリーンショット。]
3. 新しいグループのグループ名を追加します。 グループ名は、グループが API ユーザー用であることを示す必要があります。
[Image: 新しいアクセス許可グループを作成するときの [グループ名] フィールドのスクリーンショット。]
4. グループにメンバーを追加します。 たとえば、[ユーザー プール] ドロップダウン メニューから **[ユーザー名** ] を選択し、統合に使用する API アカウントのユーザー名を入力できます。
[Image: [People Pool](ユーザー プール) ドロップダウンから [Username](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/ユーザー名) を選択してアクセス許可グループにメンバーを追加するスクリーンショット。]
5. [ **完了] を** 選択して、アクセス許可グループの作成を完了します。

### アクセス許可グループにアクセス許可ロールを付与する

1. SuccessFactors 管理センターで、[ **アクセス許可ロールの管理**] を検索し、検索結果から [ **アクセス許可ロールの管理** ] を選択します。
2. **アクセス許可ロールの一覧**から、API の使用アクセス許可用に作成したロールを選択します。
3. **このロールの付与先...**で、**追加...**ボタンを選択します。
4. ドロップダウン メニューから **[アクセス許可グループ...]** を選択し、[ **選択]...** を選択して [グループ] ウィンドウを開き、作成したグループを検索して選択します。
5. アクセス許可グループに対するアクセス許可ロールの付与を確認します。
6. [ **変更の保存] を選択します**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/configure-workday-termination-lookahead"} -->
## Workday 終了先読みを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-workday-termination-lookahead
- Service: entra-id / app-provisioning
- Article date: 2026-04-23
- Summary: Workday-to-AD/Microsoft Entra ID プロビジョニング ジョブの終了先読みクエリを有効にします。

Microsoft Entra Workday プロビジョニング コネクタは、 `Get_Workers` SOAP API を使用して、Workday Integration System User (ISU) アカウントを使用してワーカー データを取得します。 ただし、Workday ISU アカウントは常に太平洋タイム ゾーン (PT) で動作し、PT より前のタイム ゾーンのワーカーの終了イベントの処理に遅延が発生します。

たとえば、メルボルンの Workday ユーザー **(UTC+10、5 月の PDT の +17 時間前)** が、 **2025 年 5 月 14 日午後 11 時 59 分のメルボルン時刻**の有効終了日で終了したとします。 Microsoft Entra Workday コネクタは、Workday ISU アカウントを使用して、 **2025 年 5 月 14 日午後 11 時 59 分 (メルボルン時間 2025 年 5 月 15 日午後 4 時 59** **分**) より後に実行されるプロビジョニング サイクルの終了イベントをフェッチします。これは、大幅な遅延です。

この問題を軽減するために、コネクタに **24 時間終了先読みクエリ**が含まれるようになりました。 このクエリにより、終了に関連する属性 (`StatusTerminationLastDayOfWork`、 `StatusTerminationDate`) は、終了日が UTC で始まるときにコネクタ フィードに表示されます。 正確な処理時間は、夏時間の調整によって異なります。

フィードに終了の詳細が表示され始める例を次に示します。

- **メルボルン (AEST、UTC+10) → 2025** 年 5 月 14 **日午前 10:00 メルボルン時間**

最後の稼働日が 2025 年 5 月 14 日であるメルボルンのユーザーの場合、コネクタは、2025 年 5 月 14 日の午前 10 時から 5 月の UTC 時刻とメルボルン時刻の 10 時間の差に対応する、 `StatusTerminationLastDayOfWork` と `StatusTerminationDate`の属性を含めて開始します。

- **インド (IST、UTC+5:30)** → 2025 年 5 月 14 **日午前 5:30 インド時間**

最後の稼働日が 2025 年 5 月 14 日のインドのユーザーの場合、コネクタは、2025 年 5 月 14 日の午前 5 時 30 分から、5 月の UTC とインド標準時の 5.5 時間の時間差に対応する、 `StatusTerminationLastDayOfWork` と `StatusTerminationDate`の属性を含めて開始します。

- **日本 (UTC+9)** → 2025 年 5 月 14 **日午前 9:00 日本時間**

2025年5月14日が最終勤務日の日本のユーザーの場合、コネクタは、5月の協定世界時（UTC）と日本標準時（JST）との9時間の時差に対応して、日本時間2025年5月14日の午前9時から属性`StatusTerminationLastDayOfWork`および`StatusTerminationDate`を含み始めます。

この調整により、太平洋時間より前のタイム ゾーンの作業者が終了データを先に使用できるようになります。 Microsoft Entra ID の属性マッピング 規則を更新することで、タイム ゾーン対応の終了を実装できます。

### ジョブの構成

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に移動します。

    Important

    運用環境のセットアップで構成を有効にする前に、テスト環境でこのドキュメントで説明されている構成の変更をテストします。
2. WorkdayからAD/Microsoft Entra IDへのプロビジョニングジョブを開いてください。
3. **実行中**の状態の場合は、[**プロビジョニングの停止**] を選択して、最初にジョブを一時停止します。
4. [プロビジョニング] ブレードを開き、[ **概要**] をクリックします。
5. [ **プロパティ** ] タブで、編集/鉛筆アイコンを選択して編集ウィンドウを開きます。 [ **終了先読み**] で、[ **終了先読みクエリを有効にする**] チェック ボックスをオンにします。

    [Image: [終了先読みクエリを有効にする] クエリ ボックスの選択を示すスクリーンショット。]
6. **適用** をクリックします。
7. [ **マッピング] を** 展開し、属性マッピング ページを開きます。 プロビジョニング ジョブの種類に応じて、アカウント状態属性の式ロジックを更新します。 プロビジョニング ターゲットが Microsoft Entra ID の場合は、次のセクションの手順に従います。 プロビジョニング ターゲットがオンプレミス AD の場合は、「 Workday to AD ジョブ設定」セクションの手順に従います。

#### Workday から Microsoft Entra ID へのプロビジョニング ジョブの設定

- Workday プロビジョニング ジョブ ターゲットが **Entra ID** の場合は、属性マッピング セクションで、 `accountEnabled` フラグに関連付けられているロジックを更新して、 **作業の最終日の**チェックを含めます。

```python
Switch([StatusTerminationLastDayOfWork],   
  Switch([Active], "True",  
"0", "False",   
"1", IIF(DateDiff("n", DateAdd("h","-7",Now()),CDate(  
    Switch([StatusTerminationLastDayOfWork],Mid([StatusTerminationLastDayOfWork],1,10),  
"","9999-12-31")  
    )  
  ) <= 0, "False", "True")  
  ),  
"", Switch([Active], , "1", "True", "0", "False")  
)
```

式は、終了先読みクエリの一部として取得される属性である `StatusTerminationLastDayOfWork`の有無をチェックします。

- `StatusTerminationLastDayOfWork`属性が存在し、ワーカーが Workday で非アクティブである場合、Microsoft Entra では Worker アカウントは無効になります。
- 属性 `StatusTerminationLastDayOfWork` が存在し、ワーカーがまだアクティブな場合は、DateDiff ロジックがトリガーされ、 `StatusTerminationLastDayOfWork` が現在かどうかを確認します。 `Mid`関数を使用すると、値の日付部分のみが日付比較に使用されます。 特定の現地時刻にアカウントを無効にするには、DateAdd 時間の値を次のように計算します。

    `DateAdd-HourParameter = [UTC Offset of your time zone] - [TargetLocalHour in 24h format]`

    たとえば、AEST (UTC+ 10) のメルボルンの場合、現地時刻の午後 5 時 (17:00) に無効化する場合は、次のように計算します。

    `DateAdd-HourParameter = 10 - 17 = -7`

    そのため、上記の DateAdd 時間の値は **-7** に設定されます。
- 属性 `StatusTerminationLastDayOfWork` が見つからない場合は、ユーザーに関連付けられている **Active** フラグに基づく既定の式が使用されます。

[Image: [属性の編集] フィールドのスクリーンショット。]

- Workday プロビジョニング ジョブ ターゲットが **Entra ID** の場合は、 `StatusTerminationLastDayOfWork` を `employeeLeaveDateTime` 属性にフローし、 `employeeLeaveDateTime`に基づいて Leaver Lifecycle Workflows をトリガーすることもできます。

[Image: Microsoft Entra ID と Workday 属性マッピングのスクリーンショット。]

- 設定を変更した後、プロビジョニング ジョブを保存します。

#### Workday-to-AD プロビジョニング ジョブの設定

- Workday プロビジョニング ジョブ ターゲットが **Active Directory** の場合は、[属性マッピング] セクションで、 `accountDisabled` フラグに関連付けられているロジックを更新して、 **作業の最終日の**チェックを含めます。

```python
Switch([StatusTerminationLastDayOfWork],   
  Switch([Active], "False",  
"0", "True",   
"1", IIF(DateDiff("n", DateAdd("h","-7",Now()),CDate(  
    Switch([StatusTerminationLastDayOfWork],Mid([StatusTerminationLastDayOfWork],1,10),  
"","9999-12-31")  
    )  
  ) <= 0, "True", "False")  
  ),  
"", Switch([Active], , "1", "False", "0", "True")  
)
```

式は、終了先読みクエリの一部として取得される属性である `StatusTerminationLastDayOfWork`の有無をチェックします。

- 属性 `StatusTerminationLastDayOfWork` が存在し、ワーカーが Workday で非アクティブである場合、AD では Worker アカウントが無効になります。
- 属性 `StatusTerminationLastDayOfWork` が存在し、ワーカーがまだアクティブな場合は、DateDiff ロジックがトリガーされ、 `StatusTerminationLastDayOfWork` が現在かどうかを確認します。 `Mid`関数を使用すると、値の日付部分のみが日付比較に使用されます。 特定の現地時刻にアカウントを無効にするには、DateAdd 時間の値を次のように計算します。

    `DateAdd-HourParameter = [UTC Offset of your time zone] - [TargetLocalHour in 24h format]`

    たとえば、AEST (UTC+ 10) のメルボルンの場合、現地時刻の午後 5 時 (17:00) に無効化する場合は、次のように計算します。

    `DateAdd-HourParameter = 10 - 17 = -7`

    そのため、上記の DateAdd 時間の値は **-7** に設定されます。
- 属性 `StatusTerminationLastDayOfWork` が見つからない場合は、ユーザーに関連付けられている **Active** フラグに基づく既定の式が使用されます。
- 必要に応じて、この日付を使用するようにオンプレミス AD の `accountExpires` 属性を構成できます。 式マッピングを使用します。

`NumFromDate([StatusTerminationLastDayOfWork])`

- 必要に応じて、作業の最終日をオンプレミス AD の拡張機能属性にフローできます。
- 設定を変更した後、プロビジョニング ジョブを保存します。

### 構成をテストする

#### Workday でワーカーを終了する

Workday で、ユーザーを終了し、太平洋タイム ゾーンを参照として使用して、ユーザーの終了日と終了日を 24 時間先の日付に設定します。

[Image: 従業員の業務プロセス履歴のスクリーンショット。]

たとえば、上記のテスト シナリオでは、終了した作業者はメルボルンのタイム ゾーンにあり、仕事の最終日は 2025 年 5 月 28 日のメルボルン時間に設定されています。 これが通常のプロビジョニング ジョブの実行 (先読みクエリなし) の場合、コネクタには、2025 年 5 月 28 日の太平洋時間 (2025 年 5 月 29 日午後 5 時メルボルン時刻) までの終了イベントは表示されません。 ただし、先読みクエリでは、2025 年 5 月 28 日午前 10 時 (メルボルン時間) に変更が表示されるようになったため、コネクタは式マッピング ロジックに基づいてアカウントを無効にすることができます。

#### ワーカーに対してオンデマンドでプロビジョニングを実行します

先読みクエリでは、ユーザーの作業の最終日がフェッチされ、 `accountEnabled` フラグが **False** に更新されました。

[Image: 変更された属性を示すスクリーンショット。]

#### 終了先読み処理を実行する増分同期を確認する

- 構成の変更が "オンデマンドプロビジョニング" で動作することを確認したら、プロビジョニング ジョブを再起動します。
- ジョブが定常状態に到達することを許可します。 増分同期が開始した後、今後 24 時間以内に有効になる将来の日付の終了をプロビジョニング ログで監視します。 これらの終了イベントは、ジョブで構成された式マッピング ロジックに従って処理されます。

### 既知の問題

- 終了先読み機能が有効になっている場合、終了日までに終了する予定の Workday ワーカー レコードに同じ日の有効な日付の変更がある場合 (連絡先の変更など)、一時的な再アクティブ化の後に 2 つの変更が次々に処理されるため、ユーザーの非アクティブ化を観察できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/configure-workload-identity-sap-successfactors-provisioning"} -->
## SAP SuccessFactors プロビジョニングのワークロード ID を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-workload-identity-sap-successfactors-provisioning
- Service: entra-id / app-provisioning
- Article date: 2026-10-01
- Summary: SAP SuccessFactors へのMicrosoft Entraプロビジョニングのために、基本認証を有効期間の短いフェデレーション OpenID Connect トークンに置き換える方法について説明します。

この記事は、SAP SuccessFactors とのMicrosoft Entraプロビジョニング統合を構成する管理者向けです。 プロビジョニング サービスの有効期間が長い基本認証資格情報を、Microsoft Entra テナントによって発行された有効期間の短い OpenID Connect (OIDC) トークンに置き換える方法について説明します。 SAP Cloud Identity Services (SAP IAS) はこれらのトークンを検証し、SAP SuccessFactors API の呼び出しに使用されるアクセス トークンと交換します。 開始する前に、構成済みの SAP SuccessFactors プロビジョニング アプリと、Microsoft Entra、SAP IAS、および SAP SuccessFactors 管理へのアクセスがあることを確認します。

ワークロード ID ベースの認証は、 [SuccessFactors API の基本認証を廃止](https://help.sap.com/docs/successfactors-release-information/8e0d540f96474717bbf18df51e54e522/fcc05a902b4140e585d968c2fe4a96bc.html)する SAP の計画に備えて、より安全な認証モデルを提供します。

この記事は、次のプロビジョニング統合に適用されます。

- オンプレミスのActive DirectoryへのSuccessFactorsのユーザープロビジョニング
- SuccessFactorsからMicrosoft Entra IDへのユーザー プロビジョニング
- SuccessFactors 書き戻し

### Prerequisites

開始する前に、次の準備が整っていることを確認します。

- **SAP SuccessFactors プロビジョニング アプリ**Microsoft Entraエンタープライズ アプリケーション ギャラリーから構成されます。
- [SAP Cloud Identity Services (SAP IAS)](https://help.sap.com/docs/successfactors-platform/setting-up-sap-successfactors-with-identity-authentication-and-identity-provisioning-services/checking-to-see-if-you-aready-have-identity-authentication-enabled) が利用可能であり、**SAP SuccessFactors の認証サービスとして構成されている**。
- Microsoft Entra ID における[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール。ワークロード ID アプリを登録または選択し、SuccessFactors プロビジョニング アプリの認証方法を更新するためのロール。
- OpenID Connect アプリケーションの作成、アプリケーション API の依存関係の追加、 **JWT Trust-by-Issuer** クライアント認証の構成を可能にする SAP Cloud Identity Services 管理コンソールでのアクセス。
- OIDC OAuth クライアント アプリケーションを管理し、必要なロールベースのアクセス許可を持つ技術/API ユーザーにクライアントをマップできるようにする SAP SuccessFactors 管理コンソールでのアクセス。

### ワークロード ID ベースの認証の理由

ワークロードIDベース認証では、静的な認証情報を排除し、失効しない資格情報の代わりに数分で失効するトークンを使用することで、セキュリティ態勢を強化できます。

| 給付金 | 変更内容 |
| --- | --- |
| **静的な資格情報がない** | 保存されたパスワードと有効期間の長いシークレットは、OIDC によって発行されたトークンに置き換えられ、有効期限が数分で切れます。 |
| **設計によるフェデレーション** | SAP IAS は、ユーザーが制御するテナント固有の OIDC 信頼規則を使用して、Microsoft Entra発行されたトークンを検証します。 |
| **最小特権アクセス** | 有効期間の短いアクセス トークンは、SAP SuccessFactors Open Data Protocol (OData) API にスコープが設定され、ロールベースの技術/API ユーザーにバインドされます。 |
| **完全に取り消し可能** | 共有シークレットをローテーションすることなく、SAP IAS の発行者別信頼構成を削除または無効にすることで、信頼を取り消すことができます。 |

### フローのしくみ

Microsoft Entraプロビジョニング サービスは、SAP IAS を介して SAP SuccessFactors に対して認証を行います。 Microsoft Entraは、テナントローカルの SyncFabric サービス プリンシパルを識別し、顧客のワークロード ID アプリケーションを対象とする有効期間の短い OIDC トークンを発行します。 SAP IAS は、このトークンを検証し、SAP SuccessFactors で使用されるアクセス トークンと交換します。 フローには、次の 3 つの手順があります。

Note

この記事では、**SyncFabric** は Microsoft Entra プロビジョニング サービスを指します。 **SyncFabric ワークロード ID ISV 統合クライアント** サービス プリンシパルは、このサービスを、Microsoft Entra発行された JSON Web トークン (JWT) のサブジェクト (`sub`) として表します。 AT1 には、**SyncFabric ワークロード ID ISV 統合クライアント**のアプリケーション (クライアント) ID に設定された承認されたパーティ (`azp`) 要求も含まれます。

1. **プロビジョニング サービスは、署名された JWT を取得します。** Microsoft Entra、発行者 (`iss`) がテナントを識別し、サブジェクト (`sub`) がテナントローカルの SyncFabric ファースト パーティ サービス プリンシパルを識別し、対象ユーザー (`aud`) が顧客ワークロード ID アプリケーションを識別する JWT を発行します。
2. **SAP IAS は JWT をアクセス トークンと交換します。** 署名された JWT は SAP IAS に提示され、SAP SuccessFactors によって信頼されます。 SAP IAS は、発行者別信頼構成に対してトークン署名、発行者、サブジェクト、および対象ユーザーを検証し、SAP SuccessFactors OData API のクエリにのみ使用できる有効期間の短いアクセス トークンを返します。
3. **プロビジョニング サービスは OData API を呼び出します。** Microsoft Entraプロビジョニング サービスでは、有効期間の短いアクセス トークンを使用して SAP SuccessFactors OData API のクエリを実行します。 アクセス トークンには、SAP SuccessFactors エンティティにアクセスするためのロールベースのアクセス許可を持つ SAP SuccessFactors の技術/API ユーザーにマップされるクライアント ID が含まれます。

[Image: OIDC トークンの取得、SAP IAS トークン交換、および SAP SuccessFactors OData API アクセスの図。]

#### トークン交換シーケンス図

ランタイム交換には、3 つのクラウド サービス (Microsoft Entra ID、SAP IAS、SAP SuccessFactors) と 2 つのアクセス トークン (AT) が含まれます。

- **AT1** — Microsoft Entra IDによって発行された JWT。 トークンは SyncFabric ファースト パーティ サービス プリンシパルにバインドされ、顧客のワークロード ID アプリケーションにスコープが設定されます。
- **AT2** — 発行者ごとの信頼を通じて AT1 を検証した後に SAP IAS によって発行される有効期間の短いアクセス トークン。

次のスイムレーンは、実行時に誰が誰をどの順序で呼び出すかを示しています。

[Image: Microsoft Entra プロビジョニング、SAP IAS、SAP SuccessFactors 間のトークン交換のシーケンス図。]

#### トークン要求のリファレンス

| トークン | 請求 | 価値 |
| --- | --- | --- |
| **AT1** (Microsoft Entra によって発行された JWT) | `sub` | **SyncFabric ワークロード ID ISV 統合クライアント** サービス プリンシパルのオブジェクト ID |
|  | `aud` | 顧客ワークロード ID アプリケーションのアプリケーション (クライアント) ID |
|  | `iss` | `https://login.microsoftonline.com/<tenantId>/v2.0` |
|  | `azp` | **SyncFabric Workload Identity ISV Integration Client** のアプリケーション (クライアント) ID |
| **AT2** (SAP IAS アクセス トークン) | `sub` | `<sap-ias-app-client-id>` |
|  | `iss` | `https://<sap-ias-server>` |
|  | `ias_apis` | `sf_technical_access` |
|  | `client_assertion_type` | `urn:ietf:params:oauth:client-assertion-type:jwt-bearer` |

### アップグレードの計画

ワークロード ID ベースの認証に切り替えると、3 つの管理チーム (Microsoft Entra、SAP Cloud Identity Services、SAP SuccessFactors を管理するチーム) 間で調整が行われます。 開始する前に、この記事を関連するすべての関係者と共有して、すべてのユーザーがエンド ツー エンドのフローを明確に理解できるようにします。

次のアプローチを検討します。

- **チーム間で調整します。** 各サービス チーム (Microsoft Entra、SAP IAS、および SAP SuccessFactors 管理) の表現を使用して作業セッションを設定し、必要なすべての構成手順をハンドオフを待たずに完了できるようにします。
- **最初に非運用環境でテストします。** 運用環境で変更を適用する前に、品質保証 (QA) または開発環境でエンドツーエンドの構成を完了します。 この方法では、運用ユーザーに対するリスクなしで、トークン交換フローとプロビジョニング動作を検証できます。
- **最初のアプリが検証された後にレプリケートします。** チームが 1 つの SuccessFactors プロビジョニング アプリのプロセスに慣れた後は、テナント内の他のプロビジョニング アプリに同じ手順をすばやく適用できます。
- **運用アプリを直接変更できない場合は、テスト アプリを使用します。** 運用プロビジョニング アプリを直接変更できない組織または変更管理の制約がある場合は、運用テナントに新しい SAP SuccessFactors プロビジョニング アプリを作成できます。 運用アプリに変更を適用する前に、そのテスト アプリを使用して、オンデマンド **プロビジョニング** でエンド ツー エンド接続とターゲット ユーザー フローを検証します。
- **既存の API ユーザー アカウントを再利用します。** SAP SuccessFactors で OIDC OAuth クライアント アプリケーション マッピングを構成する場合は、SAP IAS クライアント ID を、以前に基本認証で使用したのと同じ [SuccessFactors API ユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-successfactors-api-user) にバインドします。 このバインドにより、どの SuccessFactors Employee Central エンティティにアクセスできるかを制御するロールベースのアクセス許可は、スイッチの前後で同じままになるため、プロビジョニング ジョブによってフェッチされたデータに予期しない変更はありません。
- **複数の SuccessFactors プロビジョニング アプリがある場合は、推奨されるシーケンスを使用します。** テナントに複数の SAP SuccessFactors プロビジョニング アプリがある場合は、次の順序でアップグレードしてください: (1) **SuccessFactors Writeback**、(2) **SuccessFactors から Microsoft Entra ID へのユーザー プロビジョニング**、(3) **SuccessFactors からオンプレミス Active Directory へのユーザー プロビジョニング**。 このシーケンスにより、受信プロビジョニング フローが切り替わる前に、書き戻し操作が最初に検証されます。

Note

認証方法を切り替えると、完全同期がトリガーされたり、プロビジョニング ジョブが再起動されたりすることはありません。 運用環境でこの変更を行うときは、まず [ **プロビジョニングの一時停止** ] を選択してアクティブな同期サイクルを中断し、次に認証方法をワークロード ID ベースの認証に切り替えてから、[ **プロビジョニングの開始** ] を選択して再開します。 プロビジョニングを一時停止すると、既存の同期状態と透かしが保持されます。 プロビジョニングを再開すると、増分プロビジョニング サイクルは一時停止した時点から継続されます。

### コンフィギュレーションの手順

この構成は、3 つの管理コンソールにまたがる 1 回限りのセットアップです。 このMicrosoft Entra 管理センターでは、ガイド付きエクスペリエンスとしてのフローについて説明し、途中で SAP Cloud Identity Services 管理コンソールとパラメーターを交換します。 大まかなシーケンスは次のとおりです。

1. **Microsoft Entra**で、SuccessFactors プロビジョニング アプリの認証方法を切り替え、ワークロード ID アプリケーションを作成または再利用します。
2. **SAP Cloud Identity Services 管理コンソール**で、Microsoft Entra が提供するトークン発行者、JSON Web Key Set (JWKS) URI、サブジェクト、オーディエンスの値を使用して、JWT Trust-by-Issuer を構成します。
3. **Microsoft Entra**に戻り、SAP Cloud Identity Services によって返される値 (アプリの依存関係名を含む) を貼り付け、**テスト接続**を実行し、ワークロード ID ベースの認証をアクティブ化します。
4. **SAP SuccessFactors 管理コンソール**で、OIDC OAuth クライアントと技術ユーザーのマッピングを確認します。
5. 既知のユーザーに対してオンデマンドでプロビジョニングを実行して検証します。

#### 手順 1: SAP SuccessFactors プロビジョニング アプリを開き、認証方法を切り替える

ガイド付きエクスペリエンスを使用してプロビジョニングを一時停止し、ワークロード ID ベースの認証を選択します。

1. SAP SuccessFactors プロビジョニング アプリをホストするテナントの少なくとも [Application Administrator](https://entra.microsoft.com) として、**Microsoft Entra 管理センター** にサインインします。
2. **SAP SuccessFactors** プロビジョニング アプリ (アップグレードする統合に応じて、受信ユーザー プロビジョニング用または書き戻し用のギャラリー アプリ) を開きます。
3. **［プロビジョニング］**ブレードを選択します。 [ **概要** ] セクションで、[ **プロビジョニングの一時停止** ] を選択して、アップグレードが完了するまでプロビジョニング サイクルを中断します。
    Note

    [プロビジョニングの **一時停止**] を選択すると、既存の同期状態と透かしが保持されます。 認証方法を基本認証からワークロード ID に切り替えた後は、[ **プロビジョニングの開始** ] を選択し、プロビジョニングを一時停止した状態から増分プロビジョニング サイクルを続行できます。
4. [ **プロビジョニング]** を選択し、[接続] セクション **を** 見つけます。 基本認証はアクティブなままですが、[ **管理者パスワード** ] フィールドと [ **テナント URL** ] フィールドと、ワークロード ID ベースの認証にアップグレードすることを推奨するバナーが表示されます。
5. [ **認証方法の選択** ] ボックスの一覧 **で、[ワークロード ID ベースの認証] を** 選択して、ガイド付き構成エクスペリエンスを開始します。 [Image: ワークロード ID ベースの認証に切り替える方法を示すスクリーンショット。]

#### 手順 2: ワークロード ID アプリケーションを作成または選択する

ガイド付きエクスペリエンスでは、ワークロード ID アプリケーションを登録または選択するように求められます。 アプリケーションのクライアント ID は AT1 の対象ユーザー (`aud`) として使用され、SAP IAS で構成する信頼のスコープを設定します。

1. [ **ワークロード ID の選択] を選択します**。 サイドパネルで、次のいずれかを選択します。

    - **登録**して、ガイド付きエクスペリエンスで新しいワークロード ID 用のアプリ登録を作成できるようにします。 必要に応じて、既定の **アプリ登録名の名前を変更**できます。
    - SAP Cloud Identity Services と通信するワークロード ID アプリケーションを既に構成している場合は、**既存のものを選択**します (たとえば、複数の SAP SuccessFactors プロビジョニング アプリがあり、既存のワークロード ID アプリを再利用する場合)。 [Image: レジスタと選択オプションを示すスクリーンショット。]

    Important

    ワークロード ID は、アプリケーション API ホストがプロビジョニング アプリ用に構成された SAP SuccessFactors API ホストと一致する場合にのみ再利用できます。 ワークロード ID は、2026 年 9 月 15 日の実装更新をまたいでも前方互換性があります。 更新後に作成されたエンタープライズ アプリをプロビジョニングすると、更新前に作成されたワークロード ID を選択できますが、更新前に作成されたエンタープライズ アプリでは、その後に作成されたワークロード ID を選択することはできません。
2. **[登録**] を選択すると、ガイド付きエクスペリエンスによってテナントにワークロード ID アプリの登録が作成されます。

    Note

    ワークロード ID アプリ登録の [**API の公開**] で、ガイド付きエクスペリエンスによって**アプリケーション ID URI が**`api://<app-client-id>/<successfactors-api-endpoint-url>`に設定されます。

    ワークロード ID ベースの認証を初めて構成すると、統合によって、テナントに Microsoft ファースト パーティ サービス プリンシパル **SyncFabric Workload Identity ISV Integration Client** が自動的に作成されます。 そのオブジェクト ID はテナントに一意なものであり、SAP IAS に送信される Microsoft Entra によって発行された `sub` クレームとして使用されます。 ワークロード ID アプリケーションのクライアント ID は、JWT `aud` 要求として個別に使用されます。
3. ワークロード ID アプリケーションを選択すると、Microsoft Entra に、SAP Cloud Identity Services がお客様のテナントによって発行されたトークンを信頼するために必要な値が表示されます。 このパネルを開いたまま、SAP Cloud Identity Services 管理コンソールに切り替えます。

    [Image: ワークロード ID のセットアップ パラメーターを示すスクリーンショット。]

#### 手順 3: Microsoft Entra信頼パラメーターを SAP Cloud Identity Services にコピーする

OIDC アプリケーションを作成し、Microsoft Entra信頼パラメーターを SAP Cloud Identity Services にコピーします。

1. SAP SuccessFactors インスタンスに関連付けられている **SAP Cloud Identity Services 管理コンソール** に管理者としてサインインします。
2. [ **アプリケーションとリソース** ] メニューから [ **アプリケーション** ] を選択し、[ **作成** ] を選択して新しい **OpenID Connect** アプリケーションを作成します。

    [Image: SAP IAS アプリの作成オプションを示すスクリーンショット。]
3. [ **信頼** ] タブの [ **アプリケーション API] で**、[ **依存関係**] を選択します。 このアプリが SAP SuccessFactors の API を使用できるように、新しい依存関係を追加します。 **アプリケーション**の一覧から SAP SuccessFactors インスタンスを選択し、**API** の一覧から `sf_technical_access` を選択します。 SAP Cloud Identity Services パラメーターをMicrosoft Entraにコピーするときに使用する**依存関係の名前**を記録します。 [Image: SAP IAS から SuccessFactors への依存関係の構成を示すスクリーンショット。]
4. [ **信頼** ] タブの [ **アプリケーション API] で**、[ **クライアント認証**] を選択します。 **[JSON Web トークン] &gt; [発行者による信頼の構成**] セクションに移動し、[**追加**] を選択して、Microsoft Entra発行されたトークンを信頼するように SAP IAS を構成します。 Microsoft Entra パネルから、次の値を SAP Cloud Identity Services の対応するフィールドにコピーします。

    | Microsoft Entra フィールド | SAP Cloud Identity Services フィールド |
    | --- | --- |
    | **トークン発行者** | Issuer |
    | **JWKS URI** | JSON Web キー セット URI |
    | **件名** | サブジェクト |
    | **オーディエンス** | オーディエンス |

    [Image: Microsoft Entra プロビジョニング アプリから SAP IAS アプリにコピーする値を示すスクリーンショット。]
5. 信頼構成を SAP Cloud Identity Services に保存します。 構成された信頼により、SAP IAS は AT1 の署名、発行者、サブジェクト、および対象ユーザーを検証し、AT2 ( `sf_technical_access` スコープを持つ SAP IAS アクセス トークン) を発行できます。

#### 手順 4: SAP SuccessFactors で OIDC OAuth クライアント アプリケーション マッピングを構成する

信頼構成の一部になった SAP IAS クライアント ID は、プロビジョニング ジョブで使用される SuccessFactors エンティティを読み取るロールベースのアクセス許可を持つ SAP SuccessFactors **技術/API ユーザー** にマップする必要があります。

Note

Microsoft Entra テナントで SAP SuccessFactors プロビジョニング アプリを初めて構成していて、基本認証を使用する SAP SuccessFactors プロビジョニング アプリがない場合は、「[Configure SuccessFactors API ユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-successfactors-api-user)を参照して、最初に SAP SuccessFactors で API ユーザーを構成してから、ここで説明する手順に従ってください。

1. **SAP SuccessFactors** 管理コンソールにサインインします。
2. **管理センター &gt; Security Center &gt; OIDC OAuth クライアント アプリケーションの管理**に移動します。
3. [ **アプリケーションの種類** ] タブを開き、[ **登録** ] を選択して `Entra-Provisioning` を新しいアプリケーションの種類として登録します。 [Image: SAP SuccessFactors での新しいアプリケーションの種類の登録を示すスクリーンショット。]
4. **[アプリケーション マップ**] タブを開き、[**登録**] を選択して、SAP Cloud Identity Services 信頼構成から SAP SuccessFactors の既存の技術/API ユーザーに SAP IAS `Client ID`をバインドします。 [Image: SAP IAS クライアント ID を SuccessFactors 技術/API ユーザーにマップする方法を示すスクリーンショット。]
5. マッピングで、次のことを確認します。
    - **クライアント ID は** 、SAP Cloud Identity Services 信頼構成の SAP IAS クライアント ID と一致します。
    - **ユーザー ID** は、`entra.sap.admin`がプロビジョニング ジョブが使用する Employee Central エンティティへの OData API アクセスを許可する SuccessFactors API ユーザー (たとえば、) です。 基本認証からワークロード ID ベースの認証に切り替える場合は、基本認証で使用するように構成したのと同じ API ユーザーに **クライアント ID を** マップできます。

#### 手順 5: SAP Cloud Identity Services パラメーターを Microsoft Entra にコピーする

SAP Cloud Identity Services は、Microsoft Entra が構成を完了するために必要な 1 つ以上の値を生成します。特に、信頼構成を SAP SuccessFactors OData エンドポイントに関連付ける **クライアント ID** と **アプリの依存関係名** が生成されます。

1. SAP Cloud Identity Services 管理コンソールで、Microsoft Entra信頼パラメーターを構成した OIDC アプリケーションを開きます。 **[信頼&gt;依存関係**] パネルに移動し**、[依存関係名**] をコピーします。 文字列 `urn:sap:identity:application:provider:name:` を前に付加します。 たとえば、 **依存関係名** が `SF-EC-TA`されている場合は、SuccessFactors プロビジョニング **アプリのアプリ依存関係名** の値を `urn:sap:identity:application:provider:name:SF-EC-TA` に設定します。
2. SAP IAS OIDC アプリケーションの**クライアント ID をクライアント** **識別子**パラメーターにコピーします。
3. `OAuth token endpoint` パラメーターを SAP IAS 承認サーバーのトークン エンドポイントに設定します。 例： `https://<ias-server>.accounts.ondemand.com/oauth2/token`
4. `Application API URL` パラメーターを [SAP SuccessFactors OData API サーバー](https://help.sap.com/docs/successfactors-platform/sap-successfactors-api-reference-guide-odata-v2/list-of-sap-successfactors-api-servers)に設定します。 例: `https://apisalesdemo8.successfactors.com`。
5. すべての接続パラメーターを入力すると、構成はこのスクリーンショットに示されているレイアウトと一致します。 [Image: 構成されているすべてのワークロード ID 接続パラメーターを示すスクリーンショット。]

#### 手順 6: 接続をテストし、ワークロード ID ベースの認証をアクティブにする

Microsoft Entra 管理センターに戻り、エンドツーエンドのトークン交換を検証し、新しいメソッドをアクティブにします。

1. ワークロード ID 構成パネルで、[ **接続のテスト**] を選択します。 Microsoft Entraは、完全なランタイム フローを実行します。SyncFabric サービス プリンシパルにバインドされ、顧客のワークロード ID アプリケーションにスコープが設定された AT1 トークンを取得し、SAP IAS で AT2 と交換し、SAP SuccessFactors OData API を呼び出します。
2. テスト接続が **成功**したことを確認します。 失敗した場合は、「トラブルシューティング」セクション を 参照してください。
3. [ **保存してアクティブ化] を選択します**。 ワークロード ID ベースの認証は、SuccessFactors プロビジョニング アプリのアクティブな方法になります。 [Image: アクティブな認証方法としてのワークロード ID を示すスクリーンショット。]

Note

SAP では基本認証がサポートされていますが、同じ **認証方法** ドロップダウンからいつでも基本認証に切り替えることができます。 ワークロード ID ベースの認証は、セキュリティが強化され、有効期間の長い資格情報を格納する必要がないため、可能な限り使用します。

#### 手順 7: プロビジョニングをエンドツーエンドで検証する

フル スケールでプロビジョニングを有効にする前に、少数のテスト ユーザー グループでアップグレードされた構成を検証します。

1. SuccessFactors プロビジョニング アプリで、[ **概要**] を選択します。
2. 特定の既知のユーザーに対して **オンデマンド プロビジョニング** を実行するか、プロビジョニング スコープ フィルターを使用して、プロビジョニングの対象を小規模なテスト対象ユーザー グループに限定します。
3. 運用環境で有効にする前に、テスト環境または非運用環境で完全同期と増分同期サイクルを検証します。

### ログを使用して構成を確認する

手順を完了したら、サービス ログを使用して、セットアップのトラブルシューティングと確認を行います。

- **SAP Cloud Identity Services のトラブルシューティング ログ** - SAP Cloud Identity Services 管理ポータルで、[ **監視とレポート] &gt; トラブルシューティング ログ** にアクセスして、 `login` と `issueJwtToken`のアクションを表示します。 統合に関連付けられている SAP IAS アプリケーションのクライアント ID を使用してログを検索します。 [Image: SAP Cloud Identity Services のトラブルシューティング ログを示すスクリーンショット。]
- **SAP SuccessFactors OData 監査ログ** — SAP SuccessFactors 管理センターで **、OData API 監査ログに** アクセスし、API ユーザー ログイン ID でフィルター処理して API 呼び出しレコードを取得します。 [Image: SAP SuccessFactors OData API 監査ログを示すスクリーンショット。] 変更の前に、ログの **HTTP メッセージ** 列に文字列が `authorization: Basic **********`されていることを確認できます。 ワークロード ID ベースの認証に切り替えると、ログの **HTTP メッセージ** 列に文字列 `authorization: Bearer **********`が表示されます。

### ロールバック

SAP が基本認証をサポートしている間にロールバックする必要がある場合は、SuccessFactors プロビジョニング アプリの管理者資格情報で再び有効にすることができます。 ワークロード ID ベースの認証は、セキュリティが強化され、有効期間の長い資格情報を格納する必要がないため、可能な限り使用します。

### Troubleshooting

| 症状: | 考えられる原因 | アクション |
| --- | --- | --- |
| 既存のワークロード ID が使用できないか、`This app is configured for a different Application API host and cannot be selected` が表示される | ワークロード ID は、別の SAP SuccessFactors アプリケーション API ホストを使用します。 ホストが一致する場合は、プロビジョニング エンタープライズ アプリが 2026 年 9 月 15 日より前に作成され、その日以降にワークロード ID が作成されている可能性があります。 ワークロード ID は、この実装更新において前方互換性がありますが、下位互換性はありません。 | 両方のアプリに構成されている **アプリケーション API URL を** 比較します。 ホストが一致する場合は、プロビジョニング アプリで利用可能なワークロード ID を使用します。 互換性のあるワークロード ID がない場合は、アプリの新しいワークロード ID を登録してください。 |
| `invalid_client_assertion` SAP IAS から | AT1 の件名または対象ユーザーが、SAP IAS Trust-by-Issuer ルールと一致しません。 | Microsoft Entraによって提供される**サブジェクト**と**対象ユーザー**の値を、SAP IAS 信頼規則の対応する値と比較します。 |
| `unauthorized_client` SAP IAS から | JWKS URI に到達できないか、署名の検証に失敗しました。 | MICROSOFT ENTRA OIDC 検出エンドポイントが SAP IAS から到達可能であり、発行者要求のテナント ID が一致することを確認します。 |
| OData 呼び出しは 403 を返します | SAP IAS クライアント ID が適切な API ユーザー ID にマップされていないか、API ユーザーに適切なロールベースのアクセス許可がありません。 | SAP SuccessFactors の OIDC OAuth クライアント マッピングと API ユーザーのアクセス許可グループを再検証します。 |

### サポートに連絡する

トラブルシューティングの手順で問題が解決せず、引き続きヘルプが必要な場合は、Microsoft Entra 管理センターからサポート リクエストを開きます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[新しいサポート リクエスト]** を選択します。
3. 次の値を使用して、サポート要求フォームに入力します。

    | フィールド | 価値 |
    | --- | --- |
    | **サービスの種類** | Microsoft Entra ユーザー プロビジョニングと同期 |
    | **まとめ** | SuccessFactors プロビジョニング - ワークロード ID 認証の問題 |
    | **問題の種類** | クラウド人事から AD または Microsoft Entra ID へのプロビジョニング |
    | **問題のサブタイプ** | プロビジョニング サービスの構成に関する問題 |
4. 残りのフィールドに入力して、リクエストを送信してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/customize-application-attributes"} -->
## チュートリアル - Application Provisioning でMicrosoft Entra属性マッピングをカスタマイズする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes
- Service: entra-id / app-provisioning
- Article date: 2026-08-06
- Summary: Microsoft Entra アプリケーション プロビジョニングでのサービスとしてのソフトウェア (SaaS) アプリの属性マッピングについて説明します。 属性とは何か、およびビジネス ニーズに対応するためにそれらを変更する方法を学習します。

Microsoft Entra IDでは、Salesforce、G Suite などのMicrosoft以外の SaaS アプリケーションへのユーザー プロビジョニングがサポートされます。 Microsoft以外の SaaS アプリケーションに対してユーザー プロビジョニングを有効にした場合、Microsoft Entra 管理センターは属性値を属性マッピングによって制御します。

作業を開始する前に、アプリの管理と**シングル サインオン (SSO)** の概念を理解しておいてください。 次のリンクを参照してください。

- Microsoft Entra ID のアプリ管理クイックスタートシリーズ
- [シングル サインオン (SSO) とは](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)

Microsoft Entraユーザー オブジェクトと各 SaaS アプリのユーザー オブジェクトの間には、事前に構成された属性と属性マッピングのセットがあります。 アプリによっては、ユーザーに加えてグループなど他の種類のオブジェクトを管理するものもあります。

既定の属性マッピングをビジネスのニーズに合わせてカスタマイズできます。 そのため、既存の属性マッピングを変更、削除したり、新規の属性マッピングを作成したりすることができます。

注

Microsoft Entra インターフェイスを使用して属性マッピングを構成するだけでなく、スキーマの JSON 表現を確認、ダウンロード、編集できます。

### ユーザー属性マッピングの編集

ユーザー プロビジョニングの **属性マッピング** 機能にアクセスするには、次の手順に従います。

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. ギャラリーから追加されたアプリを含む、構成済みのすべてのアプリの一覧が表示されます。
4. 任意のアプリを選択し、そのアプリの管理ウィンドウを読み込み、そこでレポートを表示したり、アプリの設定を管理したりできます。
5. 選択したアプリのユーザー アカウントのプロビジョニング設定を管理するには、 **[プロビジョニング]** を選択します。
6. [ **管理**] で、[ **属性マッピング**] を選択します。 属性マッピング ページには、オブジェクトの種類 (ユーザーと**グループ**) 別に編成された現在のマッピングのテーブル**が**表示されます。 次の表は、各マッピングの **ソース属性**、 **ターゲット属性**、 **マッピングの種類**、 **および一致する優先順位** を示しています。
7. 既存のマッピングを編集するには、マッピング行の右側にある鉛筆アイコンを選択します。 [**属性の編集]** 画面が開き、Microsoft Entra IDとターゲット アプリケーションの間で流れるユーザー属性を変更できます。
8. マッピングを削除するには、マッピング行の右側にあるごみ箱アイコンを選択します。 必要な属性の場合、[ **削除** ] オプションは使用できません。

#### 属性マッピングの種類の概要

属性マッピングでは、Microsoft以外の SaaS アプリケーションで属性を設定する方法を制御します。 次の 4 つの異なるマッピングの種類がサポートされます。

- **Direct** – ターゲット属性には、Microsoft Entra ID内のリンクされたオブジェクトの属性の値が設定されます。
- **定数** – ターゲットの属性に、指定した特定の文字列を設定します。
- **式** - ターゲットの属性を、スクリプトのような式の結果に基づいて設定します。 式に関する詳細については、「[Microsoft Entra IDの属性マッピング用の式の記述](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)」をご参照ください。

    注

    1 つの属性マッピング式でサポートされる最大長は **10,000 文字です**。
- **LCW 機能拡張ワークフロー** - ターゲット属性には、LCW 拡張ワークフローによってトリガーされる Azure ロジック アプリによって生成された値が設定されます。 このマッピングの種類の詳細については、「 [LCW 拡張ワークフローを使用した属性マッピングの拡張](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/extend-application-attributes)」を参照してください。
- **なし** - ターゲットの属性を変更しません。 ただし、ターゲットの属性が空の場合は、指定した既定値が設定されます。

これら 4 つの基本的な種類とともに、カスタム属性マッピングではオプションの**既定**値の割り当てという概念をサポートします。 既定値の割り当てにより、Microsoft Entra IDまたはターゲット オブジェクトに値がない場合は、ターゲット属性に値が設定されます。 最も一般的な構成では、これを空白のままにします。 マッピング属性の詳細については、「[Microsoft Entra ID でのアプリケーションプロビジョニングの仕組み](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#mapping-attributes)」を参照してください。

#### 属性マッピングのプロパティの概要

前のセクションでは、属性マッピングの種類のプロパティを紹介しました。 属性マッピングは、このプロパティと共に次の属性もサポートしています。

- **Source 属性** - ソース システムのユーザー属性 (例: Microsoft Entra ID)。
- **対象の属性** - ターゲットシステム内のユーザー属性 (例: ServiceNow)。
- **null の場合の既定値 (オプション)** - ソース属性が null 値の場合にターゲット システムに渡される値。 この値は、ユーザーを作成するときにのみプロビジョニングされます。 既存のユーザーを更新したときは、"null の場合の既定値" はプロビジョニングされません。 たとえば、ユーザーを作成するときに、`Switch(IsPresent([jobTitle]), "DefaultValue", "True", [jobTitle])` という式を使用して、役職の既定値を追加します。 式の詳細については、「属性マッピングの式を記述するためのリファレンス (Microsoft Entra ID）」を参照してください。
- **この属性を使用してオブジェクトを照合する** - このマッピングを使用して、ソースとターゲットのシステム間でユーザーを一意に識別するかどうかを示します。 Microsoft Entra IDの `userPrincipalName` またはメール属性で使用され、ターゲット アプリケーションのユーザー名フィールドにマップされます。
- **照合の優先順位** - 一致させる属性を複数設定できます。 複数の場合は、このフィールドで定義された順序で評価されます。 1 件でも一致が見つかると、一致する属性の評価はそれ以上行われません。 一致する属性はいくつでも設定できますが、一致する属性として使用する属性が本当に一意であり、一致する属性に設定する必要があるかどうかを検討してください。 通常、お客様の構成には 1 つまたは 2 つの照合する属性があります。
- **マッピングを適用します**。
    - **常に** - このマッピングをユーザーの作成と更新の両方のアクションに適用します。
    - **作成中のみ** - このマッピングをユーザーの作成アクションのみに適用します。

### ソース システムとターゲット システムで一致するユーザー

Microsoft Entra プロビジョニング サービスは、"グリーン フィールド" シナリオ (ターゲット システムにユーザーが存在しない場合) と "ブラウンフィールド" シナリオ (ターゲット システムにユーザーが既に存在する) の両方でデプロイできます。 両方のシナリオをサポートするために、プロビジョニング サービスでは、一致する属性の概念を使用します。 一致する属性を利用すると、ソースのユーザーを一意に識別してターゲットのユーザーとの一致を判定する方法を任意に決定できます。 デプロイの計画の一環として、ソース システムとターゲット システムのユーザーを一意に識別するために使用できる属性を特定します。 注意する点:

- **一致する属性は一意であることが望ましい:** 顧客は多くの場合、userPrincipalName、mail、オブジェクト ID などの属性を一致する属性として使用します。
- **複数の属性を照合する属性として使用できる:** ユーザーを照合するときに評価される複数の属性と、それらの属性が評価される順序を定義することができます (UI では照合の優先順位として定義されます)。 たとえば、照合する属性として 3 つの属性を定義し、最初の 2 つの属性を評価した後にユーザーが一意に一致した場合、サービスは 3 番目の属性を評価しません。 サービスは、指定された順序で照合する属性を評価し、一致が見つかったら評価を停止します。
- **ソースとターゲットの値が厳密に一致している必要はない:** ターゲットの値は、ソースの値の関数である可能性があります。 したがって、ソースに emailAddress 属性、ターゲットに userPrincipalName がある場合に、emailAddress 属性の関数で一部の文字を何らかの定数値に置き換えることによって一致を判定できる場合があります。
- **属性の組み合わせに基づいた照合はサポートされていない:** ほとんどのアプリケーションでは、2 つのプロパティに基づくクエリの実行はサポートされていません。 したがって、属性の組み合わせに基づいて照合することはできません。 プロパティを 1 つずつ順番に評価することは可能です。
- **少なくとも 1 つの一致する属性の値をすべてのユーザーが持っていることが必要:** 1 つの一致する属性を定義する場合、ソース システムですべてのユーザーがその属性の値を持っている必要があります。 たとえば、一致する属性として userPrincipalName を定義する場合は、すべてのユーザーが userPrincipalName を持っている必要があります。 複数の一致する属性 (たとえば、extensionAttribute1 と mail の両方) を定義する場合は、すべてのユーザーが同じ一致する属性を持っている必要はありません。 あるユーザーが extensionAttribute1 を持っていて mail は持っていない一方、別のユーザーが mail を持っていて extensionAttribute1 は持っていなくても問題ありません。
- **ターゲット アプリケーションは一致する属性のフィルター処理をサポートする必要がある:** アプリケーション開発者が、ユーザーまたはグループ API で属性のサブセットに対するフィルター処理を許可します。 ギャラリー内のアプリケーションの場合、既定の属性マッピングは、ターゲット アプリケーションの API でフィルター処理がサポートされている属性に対するものであることが保証されています。 ターゲット アプリケーションの既定の一致する属性を変更する場合は、Microsoft以外の API ドキュメントを確認して、属性をフィルター処理できることを確認します。
- **LCW 機能拡張ワークフロー マッピングの種類を持つ属性は、一致する属性として使用できません**。属性の値が LCW 拡張ワークフロー マッピングの種類 (Azure ロジック アプリによって値が生成される) を使用して設定されている場合、属性は一致する属性として使用できません。

### グループ属性マッピングの編集

ServiceNow、Box、G Suite などいくつかのアプリケーションでは、グループ オブジェクトとユーザー オブジェクトをプロビジョニングする機能がサポートされています。 グループ オブジェクトには、グループ メンバーと共に表示名や電子メール別名などのグループ プロパティを含めることができます。

グループ同期をサポートするアプリの場合は、 **スコープ フィルター** ページに移動して、グループ同期を有効または無効にします。

グループ オブジェクトの一部としてプロビジョニングされる属性は、前に説明したユーザー オブジェクトの場合と同じ方法でカスタマイズできます。

ヒント

グループ オブジェクト (プロパティとメンバー) のプロビジョニングは、アプリケーションへの[グループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)とは異なる概念です。 アプリケーションにグループを割り当てることが可能ですが、プロビジョニングされるのはグループに含まれるユーザー オブジェクトのみです。 割り当てでグループを使用するために、グループ オブジェクト全体のプロビジョニングは必要ありません。

### サポートされている属性一覧の編集

特定のアプリケーションでサポートされるユーザー属性は事前に構成されています。 ほとんどのアプリケーションのユーザー管理 API ではスキーマ検出がサポートされていません。 そのため、Microsoft Entra プロビジョニング サービスでは、アプリケーションを呼び出すことによって、サポートされている属性の一覧を動的に生成することはできません。

ただし、一部のアプリケーションではカスタム属性がサポートされており、Microsoft Entra プロビジョニング サービスはカスタム属性の読み取りと書き込みを行うことができます。 Microsoft Entra 管理センターに定義を入力するには、[**属性マッピング**] ページの上部にある **[詳細オプション]** ドロップダウン メニューを選択し、[**ターゲット ユーザー属性の編集]** を選択します。

属性一覧のカスタマイズをサポートするアプリケーションとシステムには次のものが含まれます。

- Salesforce
- ServiceNow
- Workday から Active Directory/Workday から Microsoft Entra ID
- SuccessFactors から Active Directory/SuccessFactors から Microsoft Entra ID
- Microsoft Entra ID ([Microsoft Entra ID Graph API 既定の属性](https://learn.microsoft.com/ja-jp/previous-versions/azure/ad/graph/api/entity-and-complex-type-reference#user-entity)およびカスタム ディレクトリ拡張機能がサポートされています)。 拡張機能の作成の詳細については、「[Microsoft Entra アプリケーション プロビジョニング の拡張属性の同期](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)および [Microsoft Entra ID におけるプロビジョニングの既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues)」を参照してください。
- [クロスドメイン ID 管理システム (SCIM) 2.0](https://tools.ietf.org/html/rfc7643) をサポートするアプリ
- Microsoft Entra IDでは、XPATH および JSONPath メタデータの Workday または SuccessFactors への書き戻しがサポートされます。 Microsoft Entra IDは、既定のスキーマに含まれていない新しい Workday 属性または SuccessFactors 属性をサポートしていません

注

サポートされている属性の一覧の編集は、アプリケーションとシステムのスキーマをカスタマイズし、カスタム属性がどのように定義されているか、またはソース属性が Microsoft Entra 管理センター UI に自動的に表示されないかについての知識を持つ管理者にのみ推奨されます。 場合によっては、アプリケーションやシステムで提供される API および開発者ツールに慣れている必要があります。 サポートされている属性の一覧を編集する機能は既定ではロックダウンされていますが、 https://portal.azure.com/?Microsoft_AAD_Connect_Provisioning_forceSchemaEditorEnabled=true という URL に移動して機能を有効にできます。 その後、アプリケーションに移動して、属性の一覧を表示できます。 [**属性マッピング**] ページの **[詳細オプション]** ドロップダウンから、**ターゲット ユーザー属性の編集**と**スキーマの編集**にアクセスできます。

注

Microsoft Entra IDのディレクトリ拡張属性が属性マッピング ドロップダウンに自動的に表示されない場合は、手動で "Microsoft Entra属性一覧" に追加できます。 プロビジョニング アプリに Microsoft Entra ディレクトリ拡張属性を手動で追加する場合、ディレクトリ拡張の属性名は大文字と小文字が区別されることに注意してください。 例: `extension_53c9e2c0exxxxxxxxxxxxxxxx_acmeCostCenter` という名前のディレクトリ拡張属性がある場合は、ディレクトリで定義されているのと同じ形式で必ず入力してください。 複数値のディレクトリ拡張機能属性のプロビジョニングはサポートされていません。

サポートされている属性のリストを編集している場合、次のプロパティが表示されます。

- **[名前]** - ターゲット オブジェクトのスキーマで定義されている属性のシステム名。
- **型**- ターゲット オブジェクトのスキーマで定義されている、属性が格納するデータの型。次のいずれかの型になります。
    - *Binary* - 属性にはバイナリ データが含まれます。
    - *Boolean* - 属性には True または False 値が含まれます。
    - *DateTime* - 属性には日付文字列が含まれます。
    - *Integer* - 属性には整数が含まれます。
    - *Reference* - 属性には、ターゲット アプリケーションの別のテーブルに格納されている値を参照するための ID が含まれます。
    - *String* - 属性にはテキスト文字列が含まれます。
- **主キー** - ターゲット オブジェクトのスキーマで属性が主キー フィールドとして定義されているかどうか。
- **必須** - ターゲット アプリケーションまたはシステムに属性を移入する必要があるかどうか。
- **複数値** - 属性が複数値をサポートするかどうか。
- **大文字と小文字の区別** - 大文字と小文字を区別して属性値を評価するかどうか。
- **[API 式]** - 特定のプロビジョニング コネクタ (Workday など) のドキュメントで指示されていない限り、使用しないでください。
- **[Referenced Object Attribute]** (参照オブジェクト属性) - これが Reference 型属性の場合は、このメニューを使用して、この属性に関連付けられている値を含むターゲット アプリケーションのテーブルと属性を選択できます。 たとえば、"Department" という属性があり、その保存値が別の "Departments" テーブル内のオブジェクトを参照する場合は、`Departments.Name` を選びます。 特定のアプリケーションでサポートされている参照テーブルとプライマリ ID フィールドは事前構成されており、Microsoft Entra 管理センターを使用して編集することはできません。 ただし、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/synchronization-configure-with-custom-target-attributes) を使用して編集できます。

##### SCIM 準拠アプリケーションへカスタム拡張属性をプロビジョニングする

SCIM Request for Comments (RFC) では、コアとなるユーザーとグループのスキーマを定義すると同時に、アプリケーションのニーズを満たすためにスキーマを拡張することも可能にしています。 カスタム属性を SCIM アプリケーションに追加するには、次の手順に従います。

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. アプリケーションを選択して、**[プロビジョニング]** を選択します。
4. [ **管理**] で [ **属性マッピング**] を選択し、カスタム属性を追加するオブジェクトの種類 (**ユーザー** または **グループ**) を選択します。
5. [ **詳細オプション] ドロップダウンを** 選択し、[ **ターゲット ユーザー属性の編集]** を選択します。

    注

    **[AppName の属性リストの編集]** オプションが表示されない場合は、URL `https://portal.azure.com/?Microsoft_AAD_Connect_Provisioning_forceSchemaEditorEnabled=true`を使用してアプリケーションに移動し、スキーマ エディターを有効にします。
6. 属性の一覧の下部にあるフィールドに、カスタム属性に関する情報を入力します。 **[属性の追加]** を選択します。

SCIM アプリケーションの場合、属性名は例に示されているパターンに従う必要があります。 `CustomExtensionName`と`CustomAttribute`は、アプリケーションの要件に従ってカスタマイズできます (例: `urn:ietf:params:scim:schemas:extension:CustomExtensionName:2.0:User:CustomAttribute`)。

これらの手順は、SCIM 対応アプリケーションにのみ適用されます。 ServiceNow や Salesforce などのアプリケーションは SCIM を使用するMicrosoft Entra IDと統合されていないため、カスタム属性を追加するときにこの特定の名前空間は必要ありません。

カスタム属性を参照属性、複数値、または複合型の属性にすることはできません。 現在、カスタムの複数値または複合型の拡張属性は、ギャラリー内のアプリケーションに対してのみサポートされています。 この例では、カスタム拡張スキーマ ヘッダーは、Microsoft Entra SCIM クライアントからの要求では送信されないため、省略されています。

**拡張属性を持つユーザーの表記を次に示します。**

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:User",
    "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"
  ],
  "userName": "bjensen",
  "id": "48af03ac28ad4fb88478",
  "externalId": "bjensen",
  "name": {
    "formatted": "Ms. Barbara J Jensen III",
    "familyName": "Jensen",
    "givenName": "Barbara"
  },
  "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
    "employeeNumber": "701984",
    "costCenter": "4130",
    "organization": "Universal Studios",
    "division": "Theme Park",
    "department": "Tour Operations",
    "manager": {
      "value": "26118915-6090-4610-87e4-49d8ca9f808d",
      "$ref": "../Users/26118915-6090-4610-87e4-49d8ca9f808d",
      "displayName": "John Smith"
    }
  },
  "urn:ietf:params:scim:schemas:extension:CustomExtensionName:2.0:User": {
    "CustomAttribute": "701984",
  },
  "meta": {
    "resourceType": "User",
    "created": "2010-01-23T04:56:22Z",
    "lastModified": "2011-05-13T04:42:34Z",
    "version": "W\/\"3694e05e9dff591\"",
    "location": "https://example.com/v2/Users/00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
  }
}
```

### SCIM アプリへのロールのプロビジョニング

例の中の手順を使用して、ユーザーのアプリケーション ロールを目的のアプリケーションへとプロビジョニングします。 説明は、カスタム SCIM アプリケーションに固有のものです。 Salesforce や ServiceNow などのギャラリー アプリケーションの場合、事前定義済みのロール マッピングを使用します。 アプリケーションで想定されている形式に AppRoleAssignments 属性を変換する方法を箇条書きで説明します。

- Microsoft Entra IDの appRoleAssignment をアプリケーションのロールにマッピングするには、[expression](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)を使用して属性を変換する必要があります。 ロールの詳細を解析するための式を使用せずに、appRoleAssignment 属性をロール属性に**直接マップしないでください**。

注

エンタープライズ アプリケーションのロールをプロビジョニングする場合、SCIM 標準はエンタープライズ ユーザー ロール属性を異なる方法で定義しています。 詳細については、「[Microsoft Entra ID での SCIM エンドポイントのプロビジョニングを開発および計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#design-your-user-and-group-schema)」を参照してください。

**単一のアプリ役割の指定**

**使用する場合:** ユーザーの 1 つのロールをプロビジョニングする、またプライマリ ロールを指定するには、SingleAppRoleAssignment 式を使用します。

**構成方法:** 説明にある手順を使用して、属性マッピング ページに移動し、SingleAppRoleAssignment 式を使用してロール属性にマップします。 3 つのロール属性 (`roles[primary eq "True"].display`、`roles[primary eq "True"].type` および `roles[primary eq "True"].value`) の中から選択できます。 ロール属性の一部または全部をマッピングに含めることを選択できます。 複数を含める場合は、新しいマッピングを追加し、それをターゲット属性として含めるだけです。

[Image: SingleAppRoleAssignment を追加する]

**考慮事項**

- 複数のロールが 1 人のユーザーに割り当てられていないことを確認してください。 どのロールがプロビジョニングされるかは保証されません。
- `SingleAppRoleAssignments` 属性を確認します。 この属性は、スコープを `Sync All users and groups` に設定することと互換性がありません。

**要求の例 (POST)**

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:User"
  ],
  "externalId": "alias",
  "userName": "alias@contoso.OnMicrosoft.com",
  "active": true,
  "displayName": "First Name Last Name",
  "meta": {
    "resourceType": "User"
  },
  "roles": [
    {
      "primary": true,
      "type": "WindowsAzureActiveDirectoryRole",
      "value": "Admin"
    }
  ]
}
```

**出力例 (PATCH)**

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
    {
      "op": "Add",
      "path": "roles",
      "value": [
        {
          "value": "{\"id\":\"06b07648-ecfe-589f-9d2f-6325724a46ee\",\"value\":\"25\",\"displayName\":\"Role1234\"}"
        }
      ]
    }
  ]
}
```

PATCH と POST での要求の形式は異なります。 POST と PATCH が同じ形式で送信されるようにするには、[ここ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility#flags-to-alter-the-scim-behavior)で説明されている機能フラグを使用できます。

**AppRoleAssignmentsComplex**

**使用する場合:** 1 人のユーザーに複数のロールをプロビジョニングするには、AppRoleAssignmentsComplex 式を使用します。

**構成方法:** ロールの新しい属性を含めるには、説明にあるサポートされている属性の一覧を編集します。

[Image: ロールを追加する]

その後、次の画像に示すように、AppRoleAssignmentsComplex 式を使用してカスタムのロール属性にマップします。

[Image: AppRoleAssignmentsComplex を追加する]

**考慮事項**

- すべてのロールは primary = false としてプロビジョニングされます。
- `id` 属性は SCIM ロールでは必要ありません。 代わりに `value` 属性を使用してください。 たとえば、`value` 属性にロールの名前または識別子が含まれている場合、それを使ってロールをプロビジョニングします。 [こちらの](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility#flags-to-alter-the-scim-behavior)機能フラグを使用して、id 属性の問題を修正できます。 ただし、value 属性のみに依存するだけでは必ずしも十分とは限りません。たとえば、同じ名前または識別子を持つ複数のロールがある場合などです。 場合によっては、ロールを適切にプロビジョニングするために、id 属性を使う必要があります

**限界事項**

- AppRoleAssignmentsComplex は、スコープを [すべてのユーザーとグループを同期する] に設定した場合と互換性がありません。

**要求の例 (POST)**

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:User"
  ],
  "externalId": "alias",
  "userName": "alias@contoso.OnMicrosoft.com",
  "active": true,
  "displayName": "First Name Last Name",
  "meta": {
    "resourceType": "User"
  },
  "roles": [
    {
      "primary": false,
      "type": "WindowsAzureActiveDirectoryRole",
      "displayName": "Admin",
      "value": "Admin"
    },
    {
      "primary": false,
      "type": "WindowsAzureActiveDirectoryRole",
      "displayName": "User",
      "value": "User"
    }
  ]
}
```

**出力例 (PATCH)**

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
    {
      "op": "Add",
      "path": "roles",
      "value": [
        {
          "value": "{\"id\":\"06b07648-ecfe-589f-9d2f-6325724a46ee\",\"value\":\"Admin\",\"displayName\":\"Admin\"}"
        },
        {
          "value": "{\"id\":\"06b07648-ecfe-599f-9d2f-6325724a46ee\",\"value\":\"User\",\"displayName\":\"User\"}"
        }
      ]
    }
  ]
}
```

**AssertiveAppRoleAssignmentsComplex** (複雑なロールに推奨)

**使用するタイミング:** AssertiveAppRoleAssignmentsComplex を使用して PATCH 置換機能を有効にします。 複数のロールをサポートする SCIM アプリケーションの場合、これにより、Microsoft Entra IDで削除されたロールもターゲット アプリケーションで確実に削除されます。 置換機能では、ユーザーが持っている、Entra IDに反映されていない追加のロールも削除されます。

AppRoleAssignmentsComplex と AssertiveAppRoleAssignmentsComplex の違いは、パッチ呼び出しのモードとターゲット システムへの影響です。 前者は PATCH の追加のみを行います。そのため、ターゲット上の既存のロールは削除されません。 後者は、PATCH によって置き換えを実行し、ターゲット システムのロールが Entra ID 上でユーザーに割り当てられていない場合、それらのロールを削除します。

**構成方法:** ロールの新しい属性を含めるには、説明にあるサポートされている属性の一覧を編集します。

[Image: ロールを追加する]

その後、画像に示すように、AssertiveAppRoleAssignmentsComplex 式を使用してカスタムのロール属性にマップします。

[Image: AssertiveAppRoleAssignmentsComplex の追加]

**考慮事項**

- すべてのロールは primary = false としてプロビジョニングされます。
- `id` 属性は SCIM ロールでは必要ありません。 代わりに `value` 属性を使用してください。 たとえば、`value` 属性にロールの名前または識別子が含まれている場合、それを使ってロールをプロビジョニングします。 [こちらの](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility#flags-to-alter-the-scim-behavior)機能フラグを使用して、id 属性の問題を修正できます。 ただし、value 属性のみに依存するだけでは必ずしも十分とは限りません。たとえば、同じ名前または識別子を持つ複数のロールがある場合などです。 場合によっては、ロールを適切にプロビジョニングするために、id 属性を使う必要があります

**限界事項**

- AssertiveAppRoleAssignmentsComplex は、スコープを [すべてのユーザーとグループを同期する] に設定した場合と互換性がありません。

**要求の例 (POST)**

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:User"
  ],
  "externalId": "contoso",
  "userName": "contoso@alias.onmicrosoft.com",
  "active": true,
  "roles": [
    {
      "primary": false,
      "type": "WindowsAzureActiveDirectoryRole",
      "display": "User",
      "value": "User"
    },
    {
      "primary": false,
      "type": "WindowsAzureActiveDirectoryRole",
      "display": "Test",
      "value": "Test"
    }
  ]
}
```

**出力例 (PATCH)**

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
    {
      "op": "replace",
      "path": "roles",
      "value": [
        {
          "primary": false,
          "type": "WindowsAzureActiveDirectoryRole",
          "display": "User",
          "value": "User"
        },
        {
          "primary": false,
          "type": "WindowsAzureActiveDirectoryRole",
          "display": "Test",
          "value": "Test"
        }
      ]
    }
  ]
}
```

### 複数の値を持つ属性のプロビジョニング

phoneNumbers や emails などの特定の属性は、さまざまな電話番号やメール アドレスを指定する必要がある複数値の属性です。 複数の値を持つ属性には式を使用します。 これにより、属性の種類を指定し、それを値の対応するMicrosoft Entraユーザー属性にマップできます。

- `phoneNumbers[type eq "work"].value`
- `phoneNumbers[type eq "mobile"].value`
- `phoneNumbers[type eq "fax"].value`

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:User"
  ],
  "phoneNumbers": [
    {
      "value": "555-555-5555",
      "type": "work"
    },
    {
      "value": "555-555-5555",
      "type": "mobile"
    },
    {
      "value": "555-555-5555",
      "type": "fax"
    }
  ]
}
```

### 既定の属性と属性マッピングの復元

マッピング設定を既定の状態にリセットする必要がある場合は、[**属性マッピング**]&gt;[既定の**マッピングを復元**して保存する]を選択します。 これにより、アプリケーション ギャラリーからMicrosoft Entra テナントにアプリケーションが追加されたかのように、すべてのマッピングとスコープ フィルターが設定されます。 これにより、同期も再開されます。

このオプションを選択すると、プロビジョニング サービスを実行している際に、すべてのユーザーの再同期が強制されます。

重要

このオプションを使用する前に、 **[サービス提供の状況]** を **[オフ]** に設定することを強くお勧めします。

### 知っておくべきこと

- Microsoft Entra IDは、同期プロセスの効率的な実装を提供します。 初期化された環境では、更新が必要なオブジェクトのみが同期サイクル中に処理されます。
- 属性マッピングの更新は、同期サイクルのパフォーマンスに影響を与えます。 属性マッピングの構成を更新するには、すべての管理オブジェクトを再評価する必要があります。
- 推奨されるベスト プラクティスとして、属性マッピングに対する連続的な変更の回数は最小限に抑えてください。
- 写真を同期する形式は指定できないため、プロビジョニングする写真属性のアプリへの追加は、現在サポートされていません。 [ユーザーの声](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)のページで、この機能に関する要求をお送りください。
- `IsSoftDeleted`属性は、多くの場合、アプリケーションの既定のマッピングの一部であり、次の 4 つのシナリオのいずれかで true にすることができます。
    - アプリケーションから割り当てられていないため、ユーザーはスコープ外です。
    - スコープ フィルターを満たしていないため、ユーザーはスコープ外です。
    - ユーザーは、Microsoft Entra IDで論理的に削除されます。
    - `AccountEnabled`プロパティは、ユーザーに対して false に設定されます。 属性マッピングでは `IsSoftDeleted` 属性を維持するようにしてください。
- 既定では、Microsoft Entra プロビジョニング サービスはターゲット システムに null 値を送信しません。 属性値のクリアは、API 主導の受信プロビジョニング アプリでのみプレビューで使用できます。 現時点では、他のアプリケーション プロビジョニング シナリオや、Workday または SAP SuccessFactors からの受信プロビジョニングではサポートされていません。 API ドリブンの受信プロビジョニング アプリでこの機能を有効にするには、「 [属性値のクリア (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values)を参照してください。
- 主キー (通常は `ID`) は、属性マッピングのターゲット属性として含めないでください。
- 通常、role 属性は、直接マッピングではなく、式を使用してマッピングする必要があります。 ロール マッピングの詳細については、「SCIM アプリへのロールのプロビジョニング」を参照してください。
- マッピングからグループを無効にすることはできますが、ユーザーの無効化はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts"} -->
## Microsoft Entra ID でスコープ フィルターを使用してプロビジョニングするユーザーまたはグループのスコープを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts
- Service: entra-id / app-provisioning
- Article date: 2026-08-06
- Summary: スコープ フィルターを使用して、Microsoft Entra ID にプロビジョニングするユーザーまたはグループを決定する属性ベースのルールを定義する方法について説明します。

Microsoft Entra プロビジョニング サービスでスコープ フィルターを使用して属性ベースのルールを定義する方法について説明します。 ルールは、プロビジョニングされるユーザーまたはグループを決定するために使用されます。

### スコープ フィルターの使用例

::: zone pivot="app-provisioning"

スコープ フィルターを使用して、自動ユーザー プロビジョニングをサポートするアプリケーション内のオブジェクトが、ビジネス要件を満たしていないのにプロビジョニングされてしまうことを防ぎます。 スコープ フィルターを使用すると、特定の値に一致する属性を持つユーザーを含めたり除外したりできます。 たとえば、営業チームが利用する SaaS アプリケーションに Microsoft Entra ID からユーザーをプロビジョニングするとき、"Sales" の "Department" 属性を持つユーザーのみがプロビジョニングの範囲に入るように指定できます。

スコープ フィルターは、プロビジョニング コネクタの種類によって異なる方法で使用できます。

- **Microsoft Entra ID から SaaS アプリケーションへの外部プロビジョニング**。 Microsoft Entra ID がソース システムである場合、 [ユーザーとグループの割り当ては](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) 、プロビジョニングのスコープ内のユーザーを決定するための最も一般的な方法です。 ユーザーとグループの割り当てはシングル サインオンの有効化にも利用され、1 つの方法でアクセスとプロビジョニングを管理できます。 スコープ フィルターを割り当てに加えて (任意で)、あるいは割り当ての代わりに利用し、属性値に基づいてユーザーを絞り込むことができます。

    ヒント

    プロビジョニングのスコープ内のユーザーとグループが多いほど、同期プロセスにかかる時間が長くなります。 割り当てられたユーザーとグループを同期するスコープを設定し、アプリに割り当てられているグループの数を制限し、グループのサイズを制限すると、スコープ内のすべてのユーザーを同期するのにかかる時間が短縮されます。
- **HCM アプリケーションから Microsoft Entra ID と Active Directory への受信プロビジョニング**。 [Workday などの HCM アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-tutorial)がソース システムである場合、スコープ フィルターは、HCM アプリケーションから Active Directory または Microsoft Entra ID にプロビジョニングする必要があるユーザーを決定するための主要な方法です。

既定では、Microsoft Entra プロビジョニング コネクタには属性ベースのスコープ フィルターは構成されていません。

::: zone-end

::: zone pivot="cross-tenant-synchronization"

Microsoft Entra ID がソース システムである場合、 [ユーザーとグループの割り当ては](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) 、プロビジョニングのスコープ内のユーザーを決定するための最も一般的な方法です。 スコープ内のユーザー数を減らすとパフォーマンスが改善されます。また、すべてのユーザーとグループを同期するのではなく、割り当てられたユーザーとグループを同期することをお勧めします。

スコープ フィルターは、割り当てによるスコープに加えて、必要に応じて使用できます。 スコープ フィルターを利用すると、Microsoft Entra プロビジョニング サービスで、ユーザーに特定の値に一致する属性が与えられているとき、そのユーザーを追加したり、除外したりできます。 たとえば、営業チームのユーザーをプロビジョニングする場合、"Sales" の "Department" 属性を持つユーザーのみをプロビジョニングのスコープに入れるように指定できます。

::: zone-end

### スコープ フィルターの構造

スコープ フィルターは、1 つ以上の句で構成 *されます*。 句は、各ユーザーの属性を評価することによって、スコープ フィルターを通過できるユーザーを決定します。 たとえば、ユーザーの "State" 属性が New York と等しいことを求める句が指定されていると、New York 在住のユーザーのみがアプリケーションにプロビジョニングされます。

1 つの句は、1 つの属性値の 1 つの条件を定義します。 1 つのスコープ フィルターに複数の句が作成されている場合、"AND" ロジックでまとめて評価されます。 "AND" ロジックでは、ユーザーをプロビジョニングするには、すべての句の評価結果が "true" になる必要があります。

最後に、複数のスコープ フィルターを 1 つのアプリケーションに対して作成できます。 複数のスコープ フィルターがある場合は、"OR" ロジックでまとめて評価されます。 "OR" ロジックでは、いずれかのスコープ フィルターのすべての句の評価結果が "true" であれば、そのユーザーがプロビジョニングされます。

Microsoft Entra プロビジョニング サービスによって処理されるユーザーまたはグループは常に個別に各スコープ フィルターに対して評価されます。

例として、次のスコープ フィルターを検討します。

[Image: スコープ フィルター]

このスコープ フィルターに従うと、ユーザーをプロビジョニングするには、そのユーザーは次の条件を満たす必要があります。

- New York 在住である。
- エンジニアリング部門で働いている。
- 社員 ID の番号が 1,000,000 から 2,000,000 までに入る。
- 役職が null でも空でもない。

### スコープ フィルターを作成する

スコープ フィルターは各 Microsoft Entra ユーザー プロビジョニング コネクタの属性マッピングで設定されます。 次の手順では、 [サポートされているアプリケーションの 1 つに](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) 対して自動プロビジョニングを既に設定しており、スコープ フィルターを追加していることを前提としています。

#### スコープ フィルターを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。

::: zone pivot="app-provisioning"

1. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
2. 自動プロビジョニングを構成したアプリケーション ( **ServiceNow** など) を選択します。

::: zone-end

::: zone pivot="cross-tenant-synchronization"

1. **Entra ID**&gt;**Cross-tenant Synchronization**&gt;**Configurations** に移動します。
2. 構成を選択します。

::: zone-end

1. [プロビジョニング] タブ **を** 選択します。
2. [ **管理**] で、[ **スコープ フィルター**] を選択します。 スコープ フィルター ページには、ユーザーと**グループ**の 2 つのタブ**が**表示されます。 スコープを設定するオブジェクトの種類のタブを選択します。
3. 鉛筆アイコンを選んで編集します。 **スコープ フィルターの構成**ウィザードが開き、次の手順が示されます。

    a. **スコープ設定** — プロビジョニングを有効にするオブジェクトを構成します。

    b。 **割り当て別のスコープ - 割り当て** ごとにユーザーとグループのスコープを設定するかどうかを選択します (推奨)。

    c. **ユーザーとグループ** - スコープに含めるユーザーとグループを選択します。 このオプションは、アプリに割り当てられているユーザーを決定します。 これは、シングル サインオンに使用されるユーザーとグループの一覧と同じです。 詳細については、「 [アプリケーションにユーザーとグループを割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)参照してください。

    d. **属性によるスコープ** — 属性ベースのスコープ句を定義します。 ソース **属性名**、 **演算子**、および照合する **属性値** を選択します。

    e. **[確認** ] - スコープ フィルターの構成を確認し、変更を確認します。
4. [ **属性によるスコープ] ステップで** 、属性値に基づいてユーザーをフィルター処理する句を定義します。 次の演算子がサポートされています。

    a. **&**。 評価される属性が入力文字列値に存在する場合、句は "true" を返します。

    b。 **!&**。 評価される属性が入力文字列値に存在しない場合、句は "true" を返します。

    c. **で終わる**。 評価される属性が入力文字列値で終わる場合、句は "true" を返します。

    d. **EQUALS**。 評価される属性が (大文字と小文字の区別を含めて) 入力文字列値と完全に一致する場合、句は "true" を返します。

    e. **Greater\_Than。** 評価される属性が値よりも大きい場合、句は "true" を返します。 スコープ フィルターに指定する値は整数である必要があり、ユーザーの属性は整数 [0,1,2,...] である必要があります。

    f. **Greater\_Than\_OR\_EQUALS。** 評価された属性が値以上の場合、句は "true" を返します。 スコープ フィルターに指定する値は整数である必要があり、ユーザーの属性は整数 [0,1,2,...] である必要があります。

    g. **Includes。**[ここで](https://learn.microsoft.com/ja-jp/dotnet/api/system.string.contains)説明するように、評価された属性に文字列値 (大文字と小文字が区別される) が含まれている場合、句は "true" を返します。

    h. **偽である**。 評価される属性にブール値 false が含まれる場合、句は "true" を返します。

    一. **IS NOT NULL**。 評価される属性が空でない場合、句は "true" を返します。

    j. **IS NULL**。 評価される属性が空の場合、句は "true" を返します。

    k. **は TRUE です**。 評価される属性にブール値 true が含まれる場合、句は "true" を返します。

    l. **等しくない**。 評価される属性が (大文字と小文字の区別を含めて) 入力文字列値と一致しない場合、句は "true" を返します。

    m. **NOT REGEX MATCH**。 評価される属性が正規表現パターンと一致しない場合、句は "true" を返します。 属性が null または空の場合、"false" が返されます。

    n. **REGEX MATCH**。 評価される属性が正規表現パターンと一致する場合、句は "true" を返します。 例: `([1-9][0-9])` は、10 ~ 99 の任意の数値に一致します (大文字と小文字が区別されます)。

    重要

    - IsMemberOf フィルターは現在サポートされていません。
    - 現在、グループの members 属性はサポートされていません。
    - フィルター処理は複数値の属性ではサポートされていません。
    - 値が null または空の場合、スコープ フィルターは "false" を返します。
5. 必要に応じて、スコープ句を **属性別スコープ** ステップで追加します。
6. **[確認**] ステップで、スコープ フィルターの構成を確認し、[**保存]** を選択します。

重要

新しいスコープ フィルターを保存すると、アプリケーションの完全同期が新たに開始されます。ソース システムのすべてのユーザーが新しいスコープ フィルターに対してもう一度評価されます。 アプリケーションのユーザーが以前はプロビジョニングの範囲に入っていたが、範囲から外れた場合、アプリケーションでそのアカウントが無効になるか、プロビジョニングが解除されます。 この既定の動作をオーバーライドするには、「 [スコープ外のユーザー アカウントの削除をスキップする」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)を参照してください。

### 一般的なスコープ フィルター

| ターゲット属性 | 演算子 | [値] | 説明 |
| --- | --- | --- | --- |
| userPrincipalName | REGEX MATCH | `.*\@domain.com` | `userPrincipal` に `@domain.com` ドメインが含まれているすべてのユーザーが、プロビジョニングの対象になります。 |
| userPrincipalName | 正規表現が一致しない | `.*\@domain.com` | `userPrincipal` に `@domain.com` ドメインが含まれているすべてのユーザーが、プロビジョニングの対象外になります。 |
| 部署 | EQUALS | `sales` | 営業部門のすべてのユーザーは、プロビジョニングの範囲内です |
| 労働者ID | REGEX MATCH | `(1[0-9][0-9][0-9][0-9][0-9][0-9])` | 1000000 と 2000000 の間の `workerID` を持つすべての従業員は、プロビジョニング範囲内です。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/disable-scim-api"} -->
## Microsoft Entra ID で SCIM プロビジョニング API を無効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/disable-scim-api
- Service: entra-id / app-provisioning
- Article date: 2026-03-31
- Summary: Microsoft Entra 管理センターで SCIM プロビジョニング API 機能を無効にして、すべての API アクセスと課金を停止する方法について説明します。

プログラムによる SCIM アクセスが不要になった場合は、SCIM プロビジョニング API をオフにして、すべての API アクセスと課金を停止できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 左側のナビゲーションで、[ **ID ガバナンス** ] を展開し、[ダッシュボード] を選択 **します**。
3. [ダッシュボード] ページで、[ **SCIM プロビジョニング API** ] タイルを見つけて **、[編集]** を選択します。
4. **[SCIM プロビジョニング API**] ウィンドウで、[**オフにする**] を選択します。
5. メッセージが表示されたら、アクションを確認します。 この機能をオフにすると、テナントに対するすべての SCIM API 呼び出しでエラーが返され、課金が停止します。

### SCIM API が無効になっていることを確認する

無効操作が成功したことを検証するには、次の手順に従います。

1. SCIM API 呼び出しで以前に機能していたアプリ専用アクセス トークンを取得します。
2. 任意の SCIM エンドポイントに GET 要求を送信します。 たとえば、ユーザーリードエンドポイントを呼び出します。

```http
GET https://graph.microsoft.com/rp/scim/users/{id}
Authorization: Bearer {token}
Accept: application/json
```

1. API から **HTTP 400 Bad Request** が返されることを確認します。
2. 応答に次のようなエラー メッセージが含まれていることを確認します。

```text
No 'scimapiconsumptions' resource found for TenantId: {tenantId}. Please ensure 'SCIM Provisioning API' feature is enabled and only one 'scimapiconsumptions' resource exists.
```

このエラーが発生した場合、SCIM API はテナントで無効になります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/enable-scim-api"} -->
## Microsoft Entra ID で SCIM プロビジョニング API を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/enable-scim-api
- Service: entra-id / app-provisioning
- Article date: 2026-09-04
- Summary: Microsoft Entra 管理センターで SCIM プロビジョニング API 機能を有効にし、課金のために Azure サブスクリプションをリンクする方法について説明します。

この記事では、Microsoft Entra 管理センターで SCIM プロビジョニング API 機能を有効にする方法について説明します。 有効にすると、SCIM 2.0 プロトコルを使用して、Microsoft Entra ID テナント内のユーザーとグループの管理を自動化できます。

注

この機能を有効にすると、Microsoft Entra ID は SCIM サービス プロバイダー (サーバー) として機能し、HR アプリ、ID プラットフォーム、オーケストレーション ツール、カスタム自動化フレームワークなどの外部の SCIM 互換クライアントが、標準の SCIM 操作を大規模に使用して Entra でユーザーとグループをプロビジョニングおよび管理できるようになります。 この機能は、SCIM API への直接プログラムによるアクセスを目的としています。Entra ID の組み込みの [アプリ プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)、 [人事主導のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning) 、または [API 主導のプロビジョニング機能を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) 使用する場合は、有効にする必要はありません。 詳細については、 [Entra ID での SCIM サポートを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/scim-support-in-entra-id)参照してください。

完全な API リファレンスについては、 [Microsoft Entra ID SCIM API リファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-reference)。

### 前提条件

- Entra ID P1 ライセンス、または Entra ID P1 を含むライセンス (例: Entra ID P2、Microsoft 365 E3、Microsoft 365 E5 など)
- 課金用にリンクするアクティブな Azure サブスクリプション。
- SCIM API を呼び出すために必要なアクセス許可を持つアプリ登録を作成するための[アプリケーション管理者またはクラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)ロールを持つ管理者。
- SCIM API [の課金](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator) を有効にし、Azure サブスクリプションをリンクするための課金管理者ロールを持つ管理者。

### ライセンスと課金

SCIM プロビジョニング API は、サブスクリプションと課金の構成を必要とする有料アドオンです。

- **コスト：**[API 呼び出しの価格](https://aka.ms/EntraSCIMAPIPricing)を参照してください。
- **請求：** リンクされた Azure サブスクリプションを通じて毎月。

### SCIM プロビジョニング API を有効にする

Microsoft Entra 管理センターから SCIM プロビジョニング API を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 左側のナビゲーションで、[ **ID ガバナンス** ] を展開し、[ダッシュボード] を選択 **します**。
3. [ダッシュボード] ページで、[ **SCIM Provisioning API** ] タイルを見つけて、[ **作業の開始**] を選択します。 この機能が以前に構成されている場合は、タイルに現在の状態が表示され、代わりに **[編集]** ボタンが表示されます。
4. 右側に表示される **[SCIM プロビジョニング API** ] ウィンドウで、次の操作を行います。

    1. [ **サブスクリプションのリンク**] で、ドロップダウンから **Azure サブスクリプション** を選択します。
    2. 既存の **リソース グループ** を選択するか、[ **新規作成** ] を選択して作成します。
    3. **課金単位**の詳細を確認します。 SCIM プロビジョニング API 呼び出しはすべて課金されます。
    4. **有効にする**を選択します。
5. この機能を有効にすると、ダッシュボードの [SCIM プロビジョニング API] タイルが更新され、 **SCIM プロビジョニング API が有効になります**。

### SCIM API クライアントの資格情報を設定する

SCIM プロビジョニング API を有効にした後、SCIM クライアントが認証に使用する資格情報を設定します。 次のいずれかのオプションを選択できます。

注

SCIM API は、アプリケーション コンテキスト (アプリ専用トークン) でのみ動作し、委任されたユーザーの代理シナリオはサポートしません。 その結果、 `assignedLabels`など、委任された承認を必要とするプロパティは SCIM を介して更新できません。

#### オプション 1: アプリケーションを登録する (クライアント資格情報フロー)

Microsoft Entra テナントにアプリケーションを登録し、必要なアプリケーションのアクセス許可を付与し、 [OAuth 2.0 クライアント資格情報付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) を使用してアクセス トークンを取得します。

1. [Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/graph/auth-register-app-v2)。 アプリの登録から次の値を保存します。

    - アプリケーション ID (Microsoft Entra 管理センターのオブジェクト ID と呼ばれます)。
    - クライアント シークレット (アプリケーション パスワード)、証明書、またはフェデレーション ID 資格情報。
2. **[API のアクセス許可**] で、**Microsoft Graph**&gt;**Application アクセス許可**を選択し、SCIM API の使用方法に応じて、次のアクセス許可を 1 つ以上付与します。

    | 許可 | 説明 |
    | --- | --- |
    | `User.ReadBasic.All` | ユーザーの基本プロファイル プロパティへの最小特権読み取り専用アクセス。 フィルター処理は、基本プロファイルのプロパティに限定されます。 |
    | `User.Read.All` | SCIM API でサポートされているすべてのユーザー プロパティへの読み取り専用アクセス。 |
    | `User.Create` | 既存のユーザーを更新する権限を持たないユーザーを作成します。 |
    | `User.ReadUpdate.All` | ユーザーの作成または削除の権限なしで、ユーザーの読み取りと更新を行う。 |
    | `User.ReadWrite.All` | ユーザーへの読み取りと書き込みアクセス。 |
    | `User-Mail.ReadWrite.All` | **emails[type eq "other"].value** を更新するための最小限の特権アクセス許可。*これは otherMails* ユーザー プロパティにマップされます。 |
    | `User-Phone.ReadWrite.All` | **phoneNumbers[type eq "mobile"].value と** **phoneNumbers[type eq "work"].value** を更新するための最小限の特権アクセス許可。*mobilePhone* と *businessPhones* のユーザー プロパティにそれぞれマップされます。 |
    | `User.EnableDisableAccount.All` | *accountEnabled* ユーザー プロパティにマップされる**アクティブな** SCIM 属性を更新するための最小特権アクセス許可。 |
    | `Group.Read.All` | グループへの読み取り専用アクセス。 |
    | `Group.Create` | 既存のグループを更新する権限を持たないグループを作成します。 |
    | `GroupMember.ReadWrite.All` | グループ のプロパティを更新する権限を持たないグループ メンバーシップの読み取りと更新。 |
    | `Group.ReadWrite.All` | グループへの読み取りと書き込みアクセス。 |
    | `CustomSecAttributeAssignment.Read.All` | ユーザーのカスタム セキュリティ属性への読み取り専用アクセス。 |
    | `CustomSecAttributeAssignment.ReadWrite.All` | ユーザーのカスタム セキュリティ属性への読み取りと書き込みアクセス。 |
    | `CustomSecAttributeDefinition.Read.All` | カスタム セキュリティ属性スキーマへの読み取りアクセス。 |
    | `User-LifeCycleInfo.ReadWrite.All` | `employeeLeaveDateTime`などのライフサイクル属性を更新します。 |

    注

    これらのアクセス許可の詳細については、[Microsoft Graphアクセス許可のリファレンスを参照してください](https://learn.microsoft.com/ja-jp/graph/permissions-reference)。
3. 割り当てられたすべてのアクセス許可に対して **管理者の同意** を付与します。
4. 次の HTTP 要求を使用してアクセス トークンを取得し、プレースホルダーの値を実際の環境に合わせて置き換えます。 運用環境で使用する場合は、認証にクライアント証明書またはマネージド ID を使用することを強くお勧めします。

    **要求:**

    ```http
    POST https://login.microsoftonline.com/{tenant_id}/oauth2/v2.0/token HTTP/1.1
    Host: login.microsoftonline.com
    Content-Type: application/x-www-form-urlencoded
    
    client_id={client_id}&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default&client_secret={client_secret}&grant_type=client_credentials
    ```

    **応答 (200 OK):**

    ```json
    {
      "token_type": "Bearer",
      "expires_in": 3599,
      "access_token": "eyJhbGciOiJIUzI1NiJ9…"
    }
    ```
5. SCIM API を呼び出すときに、 `Authorization` ヘッダー (ベアラー スキーム) にアクセス トークンを含めます。

#### オプション 2: マネージド ID を使用する

SCIM クライアントをホストする Azure リソースに [マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) (システム割り当てまたはユーザー割り当て) を割り当て、オプション 1 に記載されているのと同じ Microsoft Graph アプリケーションのアクセス許可を付与します。

1. Azure リソース (仮想マシンや Azure 関数など) でマネージド ID を有効にします。
2. オプション 1 の表に記載されている必要な Microsoft Graph アプリケーションのアクセス許可をマネージド ID に付与します。
3. 実行時に、マネージド ID エンドポイントからアクセス トークンを取得し、SCIM API を呼び出すときに `Authorization` ヘッダーに含めます。

マネージド ID を使用してトークンを取得する方法の詳細については、「 [Azure VM 上の Azure リソースのマネージド ID を使用してアクセス トークンを取得する方法」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-use-vm-token)参照してください。

### SCIM API エンドポイントを呼び出す

資格情報を設定してアクセス トークンを取得したら、SCIM API エンドポイントの呼び出しを開始できます。 次の例では、Microsoft Entra ID SCIM 実装のサービス プロバイダー構成を取得します。

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/serviceproviderconfig HTTP/1.1
Authorization: Bearer <access_token>
Accept: application/json
Host: graph.microsoft.com
```

**応答 (200 OK):**

```json
{
  "schemas": ["urn:ietf:params:scim:schemas:core:2.0:ServiceProviderConfig"],
  "documentationUri": "/graph/overview",
  "pagination": {
    "cursor": true,
    "index": false,
    "defaultPaginationMethod": "cursor",
    "defaultPageSize": 100,
    "maxPageSize": 1000
  },
  "patch": {
    "supported": true
  },
  "bulk": {
    "supported": false,
    "maxOperations": 0,
    "maxPayloadSize": 0
  },
  "filter": {
    "supported": true,
    "maxResults": 200
  }
}
```

ユーザーとグループの管理を含む、サポートされている SCIM 操作の完全な一覧については、 [Microsoft Entra ID SCIM API リファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-reference)。

### SCIM API の課金情報を表示する

SCIM API の使用状況は、この機能を有効にしたときにリンクした Azure サブスクリプションとリソース グループを通じて課金されます。 [コスト **分析** ] ブレードを使用して、Azure portal で累積コストと使用状況予測を表示できます。

#### 課金を表示するための前提条件

コスト分析にアクセスするには、リンクされたリソース グループに次のいずれかの Azure ロールが必要です。

- **Owner**
- **投稿者**
- **Reader**
- **Cost Management 閲覧者**

#### 課金を表示する手順

1. [Azure portal](https://portal.azure.com) にサインインする
2. 上部の検索バーで、 **リソース グループ** を検索して選択します。
3. リソース グループの一覧から、SCIM API の課金を有効にするときにリンクしたリソース グループを選択します。
4. 左側のナビゲーション ウィンドウで、[ **コスト管理** ] を展開し、[ **コスト分析**] を選択します。
5. [コスト分析] ビューで、次の手順を実行します。

    - **スコープ**をリソース グループに設定します。
    - **ビュー**を**累積コスト**に設定します。
    - レビューしたい月に日付範囲を設定します。
6. グラフには、選択した期間の SCIM API の累積支出が表示されます。 上部の概要カードは次のとおりです。

    - **実績コスト (USD)** – 現在の期間にこれまでに請求された料金。
    - **予測** – 現在の使用状況に基づいて期間の合計コストが予測されます。
7. 下部にある内訳タイルを使用して、 **サービス名**、 **場所**、リソース別にコストを表示 **します**。 SCIM API の料金は、サービス名として **Microsoft Entra** の下に表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/entra-id-scim-api-reference"} -->
## Microsoft Entra ID の SCIM API - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-reference
- Service: entra-id / app-provisioning
- Article date: 2026-09-04
- Summary: カスタム統合を構築して、クロスドメインID管理 (SCIM) v2.0 プロトコルを使用し、ユーザーとグループを Microsoft Entra ID にプロビジョニングします。

このリファレンス ガイドを使用して、クロスドメイン ID 管理 (SCIM) v2.0 プロトコルを使用してユーザーとグループをMicrosoft Entra IDにプロビジョニング (同期) します。

### SCIM API の概要

Microsoft Entra ID SCIM の実装は、次の IETF ドラフトに基づいています。

- [RFC 7642: クロスドメイン ID 管理のシステム: 定義、概要、概念、要件](https://www.rfc-editor.org/rfc/rfc7642)
- [RFC 7643: クロスドメイン ID 管理用システム: コア スキーマ](https://www.rfc-editor.org/rfc/rfc7643)
- [RFC 7644: クロスドメイン ID 管理用システム: プロトコル](https://www.rfc-editor.org/rfc/rfc7644)
- [RFC 9865: SCIM リソースのカーソルベースのページネーション](https://datatracker.ietf.org/doc/rfc9865/)

すべての SCIM API エンドポイントは、ベース URL の下にあります。 `https://graph.microsoft.com/rp/scim`

| エンドポイント | サポートされている HTTP メソッド | 説明 |
| --- | --- | --- |
| `/serviceproviderconfig` | GET | サポートされている認証スキーム、使用可能なエンドポイント、SCIM プロトコルへの準拠など、Microsoft Entra ID SCIM 実装に関する構成の詳細を取得します。 |
| `/resourcetypes` | GET | Microsoft Entra IDでサポートされているリソースの種類 (ユーザーとグループ) に関する情報を取得します。 |
| `/schemas` | GET | Microsoft Entra IDでサポートされているスキーマに関する詳細情報を取得します。 |
| `/users` | GET、POST、PATCH、DELETE | Microsoft Entra IDでユーザー データの読み取り、作成、更新、削除を行います。 |
| `/groups` | GET、POST、PATCH、DELETE | Entra IDのグループ およびグループ メンバーシップ データの読み取り、作成、更新、および削除。 |

次のセクションには、Microsoft Entra ID SCIM 実装で現在サポートされている API 要求と応答の例と、設計で考慮すべき重要な注意事項と制約が含まれています。

### SCIM API の呼び出し

この記事で説明されている SCIM API エンドポイントを呼び出す前に、SCIM プロビジョニング API 機能を有効にし、課金を構成し、資格情報を設定し、アクセス トークンを取得する必要があります。 詳細な手順については、「 Microsoft Entra ID」を参照してください。

米国政府機関向けクラウドで Microsoft Graph エンドポイントを使用している場合は、SCIM API 要求のベース URL として `https://graph.microsoft.us/rp/scim` を使用します。 この記事の例では、 `https://graph.microsoft.com` を使用してグローバル クラウド エンドポイントを示します。

注

SCIM API は、アプリケーション コンテキスト (アプリ専用トークン) でのみ動作し、委任されたユーザーの代理シナリオはサポートしません。

### Graph ユーザーとグループのプロパティへのマッピング

ユーザーとグループのプロパティMicrosoft Graph SCIM ユーザー属性とグループ属性にマップする方法については、[Microsoft Entra ID SCIM API スキーマ リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-schema-documentation)を参照してください。

### 調整

Microsoft Graph API に適用される同じ調整ガイダンスとサービス固有の調整制限は、Microsoft Entra ID SCIM API にも適用されます。 Microsoft Graphユーザー API とグループ API に適用される調整ポリシーについては、ID API とアクセス API の[Microsoft Graph調整ガイダンス](https://learn.microsoft.com/ja-jp/graph/throttling)および[Microsoft Graphサービス固有の調整制限](https://learn.microsoft.com/ja-jp/graph/throttling-limits#identity-and-access-service-limits)を参照してください。

### エラー

SCIM API エンドポイントによって返される一般的なエラー コードの一覧については、 [SCIM API エラー コード リファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/troubleshoot-scim-api-errors#scim-api-error-code-reference)。

### サービス プロバイダーの構成を取得する

Microsoft Entra ID の SCIM 実装に関する追加情報を表示するには、`/ServiceProviderConfig` エンドポイントを使用します。 `/ServiceProviderConfig` エンドポイントは読み取り専用です。

**API エンドポイント:**`https://graph.microsoft.com/rp/scim/serviceproviderconfig`

成功すると、API は HTTP 状態 200 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `User.Read.All` と `Group.Read.All`、 `User.ReadWrite.All` と `Group.ReadWrite.All` |

##### 例 1 – サービス プロバイダーの構成を要求する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/serviceproviderconfig
Authorization: Bearer {token}
Accept: application/json
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "schemas": ["urn:ietf:params:scim:schemas:core:2.0:ServiceProviderConfig"],
  "documentationUri": "/graph/overview",
  "pagination": {
    "cursor": true,
    "index": false,
    "defaultPaginationMethod": "cursor",
    "defaultPageSize": 100,
    "maxPageSize": 1000
  },
  "patch": {
    "supported": true
  },
  "bulk": {
    "supported": false,
    "maxOperations": 0,
    "maxPayloadSize": 0
  },
  "filter": {
    "supported": true,
    "maxResults": 200
  }
}
```

### リソースの種類の一覧表示

サポートされているリソースの種類に関する情報は、/resourcetypes エンドポイントに要求を行うことで取得できます。

**API エンドポイント:**

- `https://graph.microsoft.com/rp/scim/resourcetypes`
- `https://graph.microsoft.com/rp/scim/resourcetypes/{identifier}`

成功すると、API は HTTP 状態 200 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `User.Read.All` と `Group.Read.All`、 `User.ReadWrite.All` と `Group.ReadWrite.All` |

##### 例 1 – すべてのリソースの種類を要求する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/resourcetypes
Authorization: Bearer {token}
Accept: application/json
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:ListResponse"],
  "totalResults": 2,
  "Resources": [
    {
      "schemas": ["urn:ietf:params:scim:schemas:core:2.0:ResourceType"],
      "id": "User",
      "name": "User",
      "endpoint": "/Users",
      "description": "User Account",
      "schema": "urn:ietf:params:scim:schemas:core:2.0:User",
      "schemaExtensions": [
        {
          "schema": "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
          "required": false
        },
        {
          "schema": "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User",
          "required": false
        }
      ],
      "meta": {
        "location": "/resourcetypes/user",
        "resourceType": "resourceType"
      }
    },
    {
      "schemas": ["urn:ietf:params:scim:schemas:core:2.0:ResourceType"],
      "id": "Group",
      "name": "Group",
      "endpoint": "/Groups",
      "description": "Group",
      "schema": "urn:ietf:params:scim:schemas:core:2.0:Group",
      "schemaExtensions": [
        {
          "schema": "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group",
          "required": true
        }
      ],
      "meta": {
        "location": "/resourcetypes/group",
        "resourceType": "resourceType"
      }
    }
  ]
}
```

##### 例 2 – ユーザー リソースの種類の要求

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/resourcetype/User
Authorization: Bearer {token}
Accept: application/json
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "schemas": ["urn:ietf:params:scim:schemas:core:2.0:ResourceType"],
  "id": "User",
  "name": "User",
  "endpoint": "/Users",
  "description": "User Account",
  "schema": "urn:ietf:params:scim:schemas:core:2.0:User",
  "schemaExtensions": [
    {
      "schema": "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
      "required": false
    }
  ],
  "meta": {
    "location": "/resourcetypes/user",
    "resourceType": "resourceType"
  }
}
```

### スキーマの取得

サポートされている SCIM スキーマに関する情報は、/schemas エンドポイントへの要求を行うことで取得できます。

**API エンドポイント:**

- `https://graph.microsoft.com/rp/scim/schemas`
- `https://graph.microsoft.com/rp/scim/schemas/{identifier}`

成功すると、API は HTTP 状態 200 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `User.Read.All` と `Group.Read.All`、 `User.ReadWrite.All` と `Group.ReadWrite.All` |

注

カスタム セキュリティ属性スキーマを読み取るには、アプリにも `CustomSecAttributeDefinition.Read.All`が必要です。

##### 例 1 – すべてのスキーマを要求する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/schemas
Authorization: Bearer {token}
Accept: application/json
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:ListResponse"],
  "totalResults": 5,
  "Resources": [
    {
      "id": "urn:ietf:params:scim:schemas:core:2.0:User",
      "name": "User",
      "description": "User Account",
      "attributes": [...]
    },
    {
      "id": "urn:ietf:params:scim:schemas:core:2.0:Group",
      "name": "Group",
      "description": "Group",
      "attributes": [...]
    }
  ]
}
```

##### 例 2 – ユーザー スキーマの要求

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/schemas/urn:ietf:params:scim:schemas:core:2.0:User
Authorization: Bearer {token}
Accept: application/json
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "id": "urn:ietf:params:scim:schemas:core:2.0:User",
  "name": "User",
  "description": "User Account",
  "attributes": [...]
}
```

##### 例 3 – カスタム セキュリティ属性スキーマの要求

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/schemas/urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:CustomSecurityAttributes
Authorization: Bearer {token}
Accept: application/json
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "id": "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:CustomSecurityAttributes",
  "name": "MicrosoftEntraCustomSecurityAttributes",
  "description": "Microsoft Entra Custom Security Attributes",
  "attributes": [
    {
      "name": "Project",
      "type": "complex",
      "multiValued": false,
      "description": "Projects assigned to the user",
      "required": false,
      "caseExact": false,
      "subAttributes": [
        {
          "name": "ProjectName",
          "type": "String",
          "multiValued": false,
          "description": "Name of the project",
          "required": false,
          "caseExact": false,
          "mutability": "readWrite",
          "returned": "default",
          "uniqueness": "none"
        }
      ],
      "mutability": "readWrite",
      "returned": "request",
      "uniqueness": "none"
    }, …
]
}
```

### ユーザーを一覧表示する

`/users` エンドポイントを使用して、次の操作を実行します。

- テナント内のすべてのユーザーを取得 (ページネーションあり)。
- 特定のフィルター条件に一致するユーザーを取得します。

**API エンドポイント:**

`https://graph.microsoft.com/rp/scim/users`

成功すると、API は HTTP 状態 200 を返します。

応答に複数のページが含まれている場合は、 [カーソルベースの改ページを](https://datatracker.ietf.org/doc/draft-ietf-scim-cursor-pagination/) 使用して、結果内のすべてのページを取得します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `User.ReadBasic.All`、`User.Read.All`、`User.ReadWrite.All` |

注

ユーザーのカスタム セキュリティ属性を読み取るには、アプリには `CustomSecAttributeAssignment.Read.All` または `CustomSecAttributeAssignment.ReadWrite.All`も必要です。

[`User.ReadBasic.All`](https://learn.microsoft.com/ja-jp/graph/permissions-reference#userreadbasicall) では、基本的なプロファイル プロパティへの応答が制限され、基本プロファイルのプロパティに対してのみフィルター処理がサポートされます。 このエンドポイントでサポートされているフィルターのうち、 `id` と `userName` がこの要件を満たしています。 `active`、`externalId`、`groups.value`、`mailNickname`、または`ownedGroups.value`でフィルターを使用するには、`User.Read.All`または`User.ReadWrite.All`を付与します。

#### リスト ユーザーのクエリ パラメーター

この API エンドポイントでは、次の SCIM クエリ パラメーターを使用できます。

- **フィルター** – 適用するフィルター条件を指定する
- **attributes** – サーバーから返されるユーザー属性を指定します。
- **excludedAttributes** – サーバーによって除外する必要があるユーザー属性を指定します。
- **count** – 取得する結果の数を指定します。 既定値は 100 です。 大きな予測要求では、次のセクションの制約に従って、最大 999 の値がサポートされます。
- **cursor** – 次の結果ページに進む

#### リスト ユーザーの制約

Microsoft Entra ID SCIM 実装には、次の制約があります。

- 既定のページ サイズは、ページあたり 100 エントリです。
- 1 ページあたり 101 ~ 999 人のユーザーを要求するには、 `attributes` を指定し、プロジェクションに `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager` を含めないでください。
- `attributes`プロジェクションに`urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager`が含まれている場合、サービスは 100 より大きい`count`値を無視し、既定のページ サイズ 100 を使用します。
- `eq`比較演算子には、次のユーザー属性を使用できます。

    - `active`。 ブール値では、 `true`、 `false`、 `TRUE`、 `FALSE`など、大文字と小文字が区別されません。
    - `userName`
    - `externalId`
    - `id`
    - `groups.value`
    - `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:mailNickname`
    - `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:ownedGroups.value`
- `ew` (末尾) 比較演算子には、次のユーザー属性を使用できます。

    - `userName`
    - `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:mailNickname`
- `userName`または`mailNickname`で`ew`比較を否定するには、`not`演算子を使用します。 `and`論理演算子を使用して、サポートされているフィルターを組み合わせます。
- "=" の周囲にエンコード済みまたはエンコードされていない空白がクエリ文字列に存在すると、"BadRequest" エラーで要求が拒否されます。 これは、フィルター、属性、excludedAttributes、count、カーソルなど、すべてのクエリ パラメーターに適用されます。
- 組み合わせフィルターは、 `externalId` 属性での使用はサポートされていません。 たとえば、次の組み合わせフィルターは使用できません。

```http
GET https://graph.microsoft.com/rp/scim/users?filter=externalId eq '12345' and userName eq 'user@contoso.com' 
```

次の例には、要求の詳細のみが含まれています。 簡潔にするために応答は含まれません。 これは、標準の SCIM 応答ペイロードに準拠しています。

##### 例 1A – すべてのユーザーを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users
Authorization: Bearer {token}
Accept: application/json
```

**応答 (200 OK):**

```json

{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:ListResponse"],
  "itemsPerPage": 100,
  "nextCursor": "RFNwdAIAAQAAAB06MTAyMDE4QHhmcDFiLm9ubWljcm9zb2Z0LmNvbS...",
  "resources": [
    {
      "schemas": [
        "urn:ietf:params:scim:schemas:core:2.0:User",
        "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
        "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User"
      ],
      "id": "d3d3d3d3-eeee-ffff-aaaa-b4b4b4b4b4b4",
      "active": true,
      "displayName": "Ellen Reckert",
      "name": {
        "familyName": "Reckert",
        "givenName": "Ellen"
      },
      "userName": "100009@contoso.com",
      "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
        "department": "Human Resources US",
        "employeeNumber": "100009",
        "manager": {
          "value": "bbbbbbbb-7777-8888-9999-cccccccccccc"
        }
      },
      "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User": {
        "mailNickname": "100009",
        "userType": "Member"
      },
      "meta": {
        "location": "/users/d3d3d3d3-eeee-ffff-aaaa-b4b4b4b4b4b4",
        "resourceType": "user"
      }
    },
    { ... }
  ]
}

```

##### 例 1B – すべてのユーザーを取得するための次のページの取得

上記のクエリでは、既定で 1 ページあたり 100 ユーザーが返されます。 テナントに 100 人を超えるユーザーがいる場合、ページネーションを使用して、`cursor` クエリパラメーターを応答で受信した `nextCursor` の値に設定し、次のユーザーセットを取得します。

```http
GET https://graph.microsoft.com/rp/scim/users?cursor=RFNwdAIAAQAAAB06MTAyMDE4QHhmcDFiLm9ubWljcm9zb2Z0LmNvbS...
Authorization: Bearer {token}
Accept: application/json
```

##### 例 1C – 返されたユーザーを制御するために count パラメーターを使用する

`urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager`属性を含まない`attributes`プロジェクションを指定すると、1 ページあたり最大 999 人のユーザーを要求できます。

```http
GET https://graph.microsoft.com/rp/scim/users?filter=active eq true&count=999&attributes=userName,id,displayName
Authorization: Bearer {token}
Accept: application/json
```

```http
GET https://graph.microsoft.com/rp/scim/users?filter=active eq true&count=999&attributes=userName,id,displayName&cursor=RFNwdAIAAQAAAB06MTAyMDE4QHhmcDFiLm9ubWljcm9zb2Z0LmNvbS...
Authorization: Bearer {token}
Accept: application/json
```

##### 例 2 – 特定の属性を持つ ID でユーザーを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users?filter=id eq "97c0abe1-14f7-417b-951c-bc8e2a17f200"&attributes=name.familyName,displayName
Authorization: Bearer {token}
```

##### 例 3 - カスタム セキュリティ属性が設定されたユーザーを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users?attributes=urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:CustomSecurityAttributes:attributeSetName
Authorization: Bearer {token}
```

##### 例 4 - 除外された属性を持つ ID でユーザーを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users?filter=id eq "97c0abe1-14f7-417b-951c-bc8e2a17f200"&excludedAttributes=name.familyName,displayName
Authorization: Bearer {token}
```

##### 例 5 – mailNickname でユーザーを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users?filter=urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:mailNickname eq "100009"&attributes=displayName
Authorization: Bearer {token}
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:ListResponse"
  ],
  "totalResults": 1,
  "resources": [
    {
      "schemas": [
        "urn:ietf:params:scim:schemas:core:2.0:User",
        "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User"
      ],
      "id": "f5f5f5f5-aaaa-bbbb-cccc-d6d6d6d6d6d6",
      "displayName": "Ellen Reckert",
      "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User": {
        "mailNickname": "100009"
      },
      "meta": {
        "location": "/users/f5f5f5f5-aaaa-bbbb-cccc-d6d6d6d6d6d6",
        "resourceType": "user"
      }
    }
  ]
}
```

##### 例 6 – userName でユーザーを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users?filter=userName eq "AdeleV@contoso.com"
Authorization: Bearer {token}
```

##### 例 7 - アクティブ ユーザーを取得する

`active`ブール値フィルターでは、大文字と小文字が区別されません。

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users?filter=active eq true
Authorization: Bearer {token}
```

##### 例 8 – mailNickname サフィックスでユーザーを除外し、userName サフィックスでフィルター処理する

`not`を使用してサポートされている`ew`比較を否定し、`and`を使用してフィルターを組み合わせます。

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users?filter=not(urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:mailNickname ew "-admin") and not(urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:mailNickname ew "-ga") and userName ew "@contoso.com"
Authorization: Bearer {token}
```

##### 例 9 - グループ ID でユーザーを取得する

**要求:**

`groups.value` プロパティのMicrosoft Entra グループ オブジェクト ID を使用して、グループの直接メンバーを取得します。 `GET /groups/{id}`から`members`属性を要求する代わりに、このクエリを使用します。

```http
GET https://graph.microsoft.com/rp/scim/users?filter=groups.value eq "c2c2c2c2-dddd-eeee-ffff-a3a3a3a3a3a3"&attributes=displayName
Authorization: Bearer {token}
```

**応答 (200 OK)** 読みやすくするために応答が切り捨てられました。

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:ListResponse"
  ],
  "totalResults": 1,
  "resources": [
    {
      "schemas": [
        "urn:ietf:params:scim:schemas:core:2.0:User"
      ],
      "id": "d3d3d3d3-eeee-ffff-aaaa-b4b4b4b4b4b4",
      "displayName": "Ellen Reckert",
      "meta": {
        "location": "/users/d3d3d3d3-eeee-ffff-aaaa-b4b4b4b4b4b4",
        "resourceType": "user"
      }
    }
  ]
}
```

##### 例 10 - グループを所有するユーザーを取得する

`ownedGroups`はユーザー応答本文では返されませんが、その`value`サブ属性をフィルター ターゲットとして使用できます。 完全な Microsoft Entra ユーザー拡張機能の名前空間を指定してください。

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users?filter=urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:ownedGroups.value eq "c2c2c2c2-dddd-eeee-ffff-a3a3a3a3a3a3"
Authorization: Bearer {token}
```

##### 例 11 - ユーザーの特定のカスタム セキュリティ属性を取得する

```http
GET https://graph.microsoft.com/rp/scim/users?filter=userName eq "AdeleV@contoso.com"&attributes=urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:CustomSecurityAttributes:Project
Authorization: Bearer {token}
```

**応答 (200 OK)** 読みやすくするために応答が切り捨てられました。

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:ListResponse"
  ],
  "totalResults": 1,
  "resources": [
    {
      "schemas": [
        "urn:ietf:params:scim:schemas:core:2.0:User",
        "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:CustomSecurityAttributes"
      ],
      "id": "b1b1b1b1-cccc-dddd-eeee-f2f2f2f2f2f2",
      "userName": "AdeleV@contoso.com",
      "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:CustomSecurityAttributes": {
        "Project": {
          "ProjectName": "IdentityHub"
        }
      },
      "meta": {
        "location": "/users/b1b1b1b1-cccc-dddd-eeee-f2f2f2f2f2f2",
        "resourceType": "user"
      }
    }
  ]
}
```

### ID でユーザーを取得する

既存のユーザーは、ユーザー ID を使用して /users エンドポイントに GET 要求を行うことで取得できます。

**API エンドポイント:**

`https://graph.microsoft.com/rp/scim/users/{id}`

成功すると、API は HTTP 状態 200 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `User.ReadBasic.All`、`User.Read.All`、`User.ReadWrite.All` |

注

[`User.ReadBasic.All`](https://learn.microsoft.com/ja-jp/graph/permissions-reference#userreadbasicall) では、基本的なプロファイル プロパティへの応答が制限されます。 他のユーザー プロパティを取得するには、 `User.Read.All` または `User.ReadWrite.All`を付与します。

#### ID でユーザーを取得するためのクエリ パラメーター

この API エンドポイントでは、次の SCIM クエリ パラメーターを使用できます。

- attributes – サーバーから返されるユーザー属性を指定します。
- excludedAttributes – サーバーによって除外する必要があるユーザー属性を指定します。

##### 例 1 – 特定の属性を持つ ID でユーザーを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/users/aaaaaaaa-6666-7777-8888-bbbbbbbbbbbb?attributes=displayName,userName
Authorization: Bearer {token}
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:User"
  ],
  "id": "aaaaaaaa-6666-7777-8888-bbbbbbbbbbbb",
  "displayName": "Adele Vance",
  "userName": "AdeleV@contoso.com",
  "meta": {
    "location": "/users/aaaaaaaa-6666-7777-8888-bbbbbbbbbbbb",
    "resourceType": "user"
  }
}
```

### ユーザーの作成

/users エンドポイントに POST 要求を送信することで、Microsoft Entra IDで新しいユーザーを作成できます。

**API エンドポイント:**

POST `https://graph.microsoft.com/rp/scim/users`

成功すると、API は HTTP 状態 201 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `User.Create` または `User.ReadWrite.All` |

#### ユーザーの作成に必要な属性

ユーザーの作成を成功させるには、次の属性が必要です。

- `userName`
- `password`
- `name.familyName`
- `name.givenName`
- `active`
- `displayName`

注

`urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:mailNickname`属性は、ユーザーを作成するときに省略可能です。 省略した場合、`null` に設定した場合、または空の値を指定した場合、Microsoft Entra ID は `mailNickname` から `userName` を導き出します。 最初の `@`の前の文字を使用します。 `userName`に`@`が含まれていない場合は、完全な値が使用されます。 たとえば、`abc123@contoso.com`は`abc123`の`mailNickname`値を生成します。

##### 例 1 - 新しいユーザーを作成する

**要求:**

```http
POST https://graph.microsoft.com/rp/scim/users
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:User"
  ],
  "active": true,
  "displayName": "Example User",
  "userName": "abc123@contoso.com",
  "password": "password",
  "name": {
    "familyName": "Example familyName",
    "givenName": "Example givenName"
  }
}
```

**応答 (201 作成):**

作成されたユーザーの SCIM 表現を返します。

この例では、Microsoft Entra ID は `abc123` から `userName` の値 `mailNickname` を導き出します。

### ユーザーを更新する

/users エンドポイントを使用すると、既存のユーザー プロファイルを更新するための PATCH 要求を行えます。

**API エンドポイント:**

パッチ `https://graph.microsoft.com/rp/scim/users/{id}`

成功すると、API は HTTP 状態 204 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `User.ReadUpdate.All` または `User.ReadWrite.All` |

注

アプリで特定のユーザー属性のみを更新する必要がある場合は、それらの属性に対して最小特権のアクセス許可を使用します。 詳細については、 [SCIM API アプリケーションのアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/enable-scim-api#option-1-register-an-application-client-credentials-flow)を参照してください。

#### ユーザーの更新に関する制約

- PATCH 操作の場合、アドレスなどの複雑な複数値属性を更新する場合、path プロパティは "[type eq " work"] フィルターのみをサポートします。
- ユーザーが作成された後、PATCH 操作を使用して `mailNickname` 属性を削除することはできません。

##### 例 1 - 属性値を更新する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/users/ec3f07a3-2aa4-4666-b2fd-90e479428791
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
  "Operations": [
    {
      "op": "replace",
      "path": "displayName",
      "value": "Johnathan Doe"
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

##### 例 2 - 複合値を更新する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/users/ec3f07a3-2aa4-4666-b2fd-90e479428791
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
  "operations": [
    {
      "op": "replace",
      "path": "urn:ietf:params:scim:schemas:core:2.0:User:name",
      "value": {
        "familyName": "Jane",
        "givenName": "Doe"
      }
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

##### 例 3 - 複雑な複数値属性を更新する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/users/ec3f07a3-2aa4-4666-b2fd-90e479428791
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
  "operations": [
    {
      "op": "replace",
      "path": "urn:ietf:params:scim:schemas:core:2.0:User:addresses",
      "value": [
        {
          "type": "work",
          "streetAddress": "4567 Main Street",
          "locality": "Buffalo",
          "region": "NY",
          "postalCode": "98052",
          "country/region": "United States"
        }
      ]
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

##### 例 4 – フィルターを使用して複雑な複数値属性を更新する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/users/ec3f07a3-2aa4-4666-b2fd-90e479428791
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": [ "urn:ietf:params:scim:api:messages:2.0:PatchOp" ],
  "operations": [
    {
      "op": "replace",
      "path": "emails[type eq \"work\" and primary eq true].value",         
      "value": "{edited_username}@{{tenant_domain}}"      
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

##### 例 5 - ユーザー マネージャーを更新する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/users/ec3f07a3-2aa4-4666-b2fd-90e479428791
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
  "operations": [
    {
      "op": "replace",
      "path": "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager",
      "value": {
        "value": "915f96af-ea85-4687-b972-b26f69d719f9"
      }
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

##### 例 6 - カスタム セキュリティ属性を更新する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/users/ec3f07a3-2aa4-4666-b2fd-90e479428791
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": [ "urn:ietf:params:scim:api:messages:2.0:PatchOp" ],
  "operations": [
    {
      "op": "add",
      "path": "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:CustomSecurityAttributes:Project.ProjectName",
      "value": "IdentityHubV2"
    }
  ]
}
```

**応答 (204 コンテンツなし):** 応答は SCIM 仕様に準拠しています。

### ユーザーを削除する

既存のユーザー ID を使用して /users エンドポイントに DELETE 要求を行うことで、ユーザーを削除できます。

**API エンドポイント:**

削除 `https://graph.microsoft.com/rp/scim/users/{id}`

成功すると、API は HTTP 状態 204 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `User.ReadWrite.All` |

##### 例 1 - ユーザーを削除する

**要求:**

```http
DELETE https://graph.microsoft.com/rp/scim/users/ec3f07a3-2aa4-4666-b2fd-90e479428791
Authorization: Bearer {token}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

### グループの一覧表示

/groups エンドポイントを使用して、次の操作を実行します。

- テナント内のすべてのグループを取得します (ページネーションあり)。
- 特定のフィルター条件に一致するグループを取得します。

**API エンドポイント:**

`https://graph.microsoft.com/rp/scim/groups`

成功すると、API は HTTP 状態 200 を返します。

応答に複数のページが含まれている場合は、 [カーソルベースの改ページを](https://datatracker.ietf.org/doc/draft-ietf-scim-cursor-pagination/) 使用して、結果内のすべてのページを取得します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `Group.Read.All`、`Group.ReadWrite.All` |

#### リスト グループのクエリ パラメーター

この API エンドポイントでは、次の SCIM クエリ パラメーターを使用できます。

- `filter` – 適用するフィルター条件を指定する場合
- `attributes` – サーバーから返されるグループ属性を指定する
- `excludedAttributes` – サーバーによって除外するグループ属性を指定する
- `count` – 取得する結果の数を指定する (default=100)
- `cursor` – 次の結果ページに進む

#### リスト グループの制約

Microsoft Entra ID SCIM 実装には、次の制約があります。

- 結果に複数のページが関係する場合:

    - 既定のページ サイズは、ページあたり 100 エントリです。
    - 最大ページ サイズは、ページあたり 1,000 エントリです。
- `filter` クエリ パラメーターでは、フィルターの組み合わせに`and`論理演算子のみがサポートされています。 `eq`比較演算子では、次のグループ属性を使用できます。

    - `displayName`: この属性を有効なMicrosoft Entra グループの表示名に設定します。
    - `id`: この属性をテナント内の有効なMicrosoft Entra グループ オブジェクト ID (GUID) に設定します。
    - `members.value`: この属性をテナント内の有効なMicrosoft Entra ユーザー オブジェクト ID (GUID) に設定します。
    - `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:securityEnabled`: この属性は、 `true`、 `false`、 `TRUE`、 `FALSE`など、大文字と小文字を区別しないブール値に設定します。
    - `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:mailEnabled`: この属性は、 `true`、 `false`、 `TRUE`、 `FALSE`など、大文字と小文字を区別しないブール値に設定します。
    - `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:owners.value`: この属性をテナント内の有効なMicrosoft Entra ユーザー オブジェクト ID (GUID) に設定します。
- 次のグループ属性は、"ew" (endsWith) 比較演算子に使用できます

    - displayName
- `member.value` フィルターを使用する場合、入れ子になったグループ メンバーシップは評価されません。 直接メンバーシップのみが評価されます。

#### 例示

次の例には、要求の詳細のみが含まれています。 簡潔にするために応答は含まれません。 これは、標準の SCIM 応答ペイロードに準拠しています。

##### 例 1 - すべてのグループを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups
Authorization: Bearer {token}
```

**応答 (200 OK):**

グループの数が 100 を超える場合、応答には `itemsPerPage` プロパティと `nextCursor` プロパティが含まれます。 `nextCursor`値を使用して、結果の次のページを取得します。

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:ListResponse"],
  "itemsPerPage": 100,
  "nextCursor": "RFNwdAIAAQAAACpHcm91cF8zMDhiMzNkOC0wNjkxLTQzZTktOTA4Mi1kNG...",
  "resources": [
    {
      "schemas": [
        "urn:ietf:params:scim:schemas:core:2.0:Group",
        "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group"
      ],
      "id": "c2c2c2c2-dddd-eeee-ffff-a3a3a3a3a3a3",
      "displayName": "Sales and Marketing",
      "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group": {
        "description": "Description of Sales and Marketing",
        "groupTypes": [
          "Unified"
        ],
        "mailEnabled": true,
        "mailNickname": "SalesandMarketing",
        "securityEnabled": false,
        "securityIdentifier": "ffffffff-5a5a-6b6b-7c7c-888888888888"
      },
      "meta": {
        "created": "2022-01-11T01:23:26+00:00",
        "location": "/groups/c2c2c2c2-dddd-eeee-ffff-a3a3a3a3a3a3",
        "resourceType": "group"
      }
    },    
    { ... }
  ]
}

```

##### 例 1B – すべてのグループを取得するための次のページの取得

既定では、上記のクエリは 1 ページあたり 100 グループを返します。 テナントに 100 を超えるグループがある場合は、ページネーションを使用して、応答で受信した `cursor` の値に `nextCursor` クエリ パラメーターを設定し、次のグループセットを取得します。

```http
GET https://graph.microsoft.com/rp/scim/groups?cursor=RFNwdAIAAQAAACpHcm91cF8zMDhiMzNkOC0wNjkxLTQzZTktOTA4Mi1kNG...
Authorization: Bearer {token}
Accept: application/json
```

##### 例 1C – 返されるグループを制御するために count パラメーターを使用する

`count` パラメーターを使用して、特定の数のグループを取得できます。

```http
GET https://graph.microsoft.com/rp/scim/groups?count=1000
Authorization: Bearer {token}
Accept: application/json
```

```http
GET https://graph.microsoft.com/rp/scim/groups?count=1000&cursor=RFNwdAIAAQAAACpHcm91cF8zMDhiMzNkOC0wNjkxLTQzZTktOTA4Mi1kNG...
Authorization: Bearer {token}
Accept: application/json
```

##### 例 2 – 特定の属性を持つ ID でグループ化を取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups?filter=id eq "dddddddd-3333-4444-5555-eeeeeeeeeeee"&attributes=displayName
Authorization: Bearer {token}
```

##### 例 3 - 除外する属性を指定してIDでグループを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups?filter=id eq "dddddddd-3333-4444-5555-eeeeeeeeeeee"&excludedAttributes=displayName
Authorization: Bearer {token}
```

##### 例 4 – displayName でグループを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups?filter=displayName eq "GroupA"
Authorization: Bearer {token}
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:ListResponse"
  ],
  "totalResults": 1,
  "resources": [
    {
      "schemas": [
        "urn:ietf:params:scim:schemas:core:2.0:Group"
      ],
      "id": "a6a6a6a6-bbbb-cccc-dddd-e7e7e7e7e7e7",
      "displayName": "GroupA",
      "meta": {
        "location": "/groups/a6a6a6a6-bbbb-cccc-dddd-e7e7e7e7e7e7",
        "resourceType": "group"
      }
    }
  ]
}
```

##### 例 5 – メンバー別にグループを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups?filter=members.value eq "d4b34e9e-0ad7-42df-b1f6-401b41fe1bee"
Authorization: Bearer {token}
```

**応答 (200 OK):**

応答には、ユーザーがメンバーである割り当て済みグループと動的グループの両方が含まれます。

##### 例 6 – セキュリティが有効なグループを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups?filter=urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:securityEnabled eq true
Authorization: Bearer {token}
```

##### 例 7 – メールが有効なグループを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups?filter=urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:mailEnabled eq true
Authorization: Bearer {token}
```

##### 例 8 – ユーザーが所有するグループを取得する

`owners`はグループ応答本文では返されませんが、その`value`サブ属性をフィルター ターゲットとして使用できます。 完全な Microsoft Entra グループ拡張機能の名前空間を指定します。

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups?filter=urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:owners.value eq "d3d3d3d3-eeee-ffff-aaaa-b4b4b4b4b4b4"
Authorization: Bearer {token}
```

### IDでグループを取得する

既存のグループは、グループ ID を使用して /groups エンドポイントに対して GET 要求を行うことによって取得されます。

**API エンドポイント:**

`https://graph.microsoft.com/rp/scim/groups/{id}`

成功すると、API は HTTP 状態 200 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `Group.Read.All`、`Group.ReadWrite.All` |

#### ID でグループを取得する際の制約

- グループ メンバーは、この API 呼び出しによって返されません。 属性レベルの改ページ位置付けは、カーソルベースの改ページ位置指定ではサポートされていないため、API ではそれを使用して大きなメンバー コレクションを返すことはできません。 グループの直接メンバーを取得するには、 `GET /users?filter=groups.value eq "{groupId}"`を使用します。

#### IDでグループを取得するためのクエリパラメーター

この API エンドポイントでは、次の SCIM クエリ パラメーターを使用できます。

- `attributes` – サーバーから返されるグループ属性を指定します。
- `excludedAttributes` – サーバーによって除外するグループ属性を指定します。

##### 例 1 – ID でグループを取得する

**要求:**

```http
GET https://graph.microsoft.com/rp/scim/groups/dddddddd-3333-4444-5555-eeeeeeeeeeee
Authorization: Bearer {token}
```

**応答 (200 OK):**

読みやすくするために応答が切り捨てられます。

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:Group",
    "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group"
  ],
  "id": "dddddddd-3333-4444-5555-eeeeeeeeeeee",
  "displayName": "GroupA",
  "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group": {
    "description": "Group for All Employees - Baseline access",
    "mailEnabled": false,
    "mailNickname": "3c998a58-6",
    "securityEnabled": true,
    "securityIdentifier": "ffffffff-5a5a-6b6b-7c7c-888888888888"
  },
  "meta": {
    "created": "2024-05-23T17:21:41+00:00",
    "location": "/groups/dddddddd-3333-4444-5555-eeeeeeeeeeee",
    "resourceType": "group"
  }
}
```

### グループの作成

/groups エンドポイントに POST 要求を送信することで、Microsoft Entra IDで新しいグループを作成できます。

**API エンドポイント:**

POST `https://graph.microsoft.com/rp/scim/groups`

成功すると、API は HTTP 状態 201 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `Group.Create` または `Group.ReadWrite.All` |

#### グループの作成に必要な属性

グループの作成を成功させるには、次の属性が必要です。

- `displayName`

##### 例 1 - 新しいグループを作成する

**要求:**

```http
POST https://graph.microsoft.com/rp/scim/groups
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:Group",
    "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group"
  ],
  "displayName": "Test SCIM Group",
  "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group": { 
    "mailEnabled": false, 
    "mailNickname": "test-scim-group", 
    "securityEnabled": true 
  }
}
```

**応答 (201 作成):**

作成されたグループの SCIM 表現を返します。

```json
{
  "schemas": [
    "urn:ietf:params:scim:schemas:core:2.0:Group",
    "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group"
  ],
  "id": "66aa66aa-bb77-cc88-dd99-00ee00ee00ee",
  "displayName": "Test SCIM Group",
  "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group": {
    "mailEnabled": false,
    "mailNickname": "test-scim-group",
    "securityEnabled": true,
    "securityIdentifier": "ffffffff-5a5a-6b6b-7c7c-888888888888"
  },
  "meta": {
    "created": "2026-03-30T16:28:37+00:00",
    "location": "/groups/66aa66aa-bb77-cc88-dd99-00ee00ee00ee",
    "resourceType": "group"
  }
}
```

### グループを更新する

`/groups` エンドポイントを使用すると、既存のグループを更新するための PATCH 要求を行えます。

**API エンドポイント:**

パッチ `https://graph.microsoft.com/rp/scim/groups/{id}`

成功すると、API は HTTP 状態 204 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `GroupMember.ReadWrite.All` またはメンバーシップの変更の `Group.ReadWrite.All` 。グループ プロパティの変更の `Group.ReadWrite.All` 。 |

注

`GroupMember.ReadWrite.All` では、メンバーを追加または削除する要求のみがサポートされます。 `displayName`や`description`などのグループ プロパティを更新するには、`Group.ReadWrite.All`を付与します。

#### グループの更新に関する制約

- グループへのメンバーの追加は、単一の PATCH **Operation** オブジェクトで行う必要があります。
- API を使用して 1 つの要求に複数のメンバーを追加する場合は、最大 20 人のメンバーのみを追加できます。
- PATCH 呼び出しごとにグループから削除できるメンバーは 1 つだけです。
- グループ メンバーの削除は、同じ PATCH 呼び出しに含まれる他の属性の変更 (メンバーの追加を含む) なしで行う必要があります。
- グループへのメンバーの追加は、他の属性の変更 (メンバーの削除を含む) なしで行う必要があります。
- グループ メンバーの追加は冪等**idempotent**操作として扱われます。 グループ内に特定のメンバーが既に存在する場合、エラーは返されません。すべてのグループ **memberId が** 有効なユーザー オブジェクトを指している限り、操作は成功します。
- グループ メンバーシップの追加または削除操作で渡された **memberId** が無効な場合、操作全体が失敗し、エラー メッセージ `“Resource '00000000-0000-0000-0000-000000000000'` 存在しないか、クエリされた参照プロパティ オブジェクトのいずれかが存在しません。"

##### 例 1 – グループの表示名を更新する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/groups/66aa66aa-bb77-cc88-dd99-00ee00ee00ee
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": [ "urn:ietf:params:scim:api:messages:2.0:PatchOp" ],
  "operations": [
    {
      "op": "replace",
      "path": "displayName",
      "value": "SCIM Testing Group Edited"
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

##### 例 2 – グループの説明を更新する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/groups/66aa66aa-bb77-cc88-dd99-00ee00ee00ee
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
  "operations": [
    {
      "op": "replace",
      "path": "urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:description",
      "value": "Finance group"
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

##### 例 3 - グループ メンバーを追加する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/groups/66aa66aa-bb77-cc88-dd99-00ee00ee00ee
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
    {
      "op": "add",
      "path": "members",
      "value": [
        {
          "value": "756b18d2-023a-4fa8-845e-9ac8b524100f"
        },
        {
          "value": "0e4c70dd-c015-48db-bb5a-6264d215ca8e"
        }
      ]
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

##### 例 4 - グループ メンバーを削除する

**要求:**

```http
PATCH https://graph.microsoft.com/rp/scim/groups/66aa66aa-bb77-cc88-dd99-00ee00ee00ee
Authorization: Bearer {token}
Content-Type: application/scim+json
```

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:PatchOp"
  ],
  "Operations": [
    {
      "op": "remove",
      "path": "members[value eq \"aaaaaaaa-6666-7777-8888-bbbbbbbbbbbb\"]"
    }
  ]
}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。

### グループを削除する

既存のグループ ID を持つ /groups エンドポイントに対して DELETE 要求を行うことで、グループを削除できます。

**API エンドポイント:**

削除 `https://graph.microsoft.com/rp/scim/groups/{id}`

成功すると、API は HTTP 状態 204 を返します。

#### アクセス許可

| アクセス許可の種類 | アクセス許可 (最小限から最も特権が高い) |
| --- | --- |
| アプリケーション | `Group.ReadWrite.All` |

##### 例 1 – グループを削除する

**要求:**

```http
DELETE https://graph.microsoft.com/rp/scim/groups/66aa66aa-bb77-cc88-dd99-00ee00ee00ee
Authorization: Bearer {token}
```

**応答 (204 コンテンツなし):**

応答は SCIM 仕様に準拠しています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/entra-id-scim-api-schema-documentation"} -->
## Microsoft Entra ID SCIM API スキーマのドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-schema-documentation
- Service: entra-id / app-provisioning
- Article date: 2026-09-04
- Summary: この記事では、コア ユーザー属性とグループ属性、エンタープライズ拡張機能、Microsoft Entra 固有の拡張機能など、SCIM スキーマ属性のリファレンスと、Microsoft Entra ID プロパティへのマッピングについて説明します。

Microsoft Entra ID SCIM API では、標準の SCIM スキーマ要素と Microsoft Entra ID 固有の拡張機能がサポートされています。 SCIM スキーマはエンドポイントで公開 https://graph.microsoft.com/rp/scim/schemas この記事では、SCIM スキーマ属性が Microsoft Entra ID [のユーザー プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) と [グループ プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/group#properties)にどのようにマップされるかについて説明します。

注

Microsoft Entra ID によって適用される属性値制約は、対応する SCIM 属性にも適用されます。

### ユーザー - コア

| SCIM 属性 | Microsoft Entra ID 属性 | 注意事項/制限事項 |
| --- | --- | --- |
| 活動中 | accountEnabled |  |
| 住所[種類 eq "勤務先"].国/地域 | 国/地域 | 許可される *アドレス* 値は 1 つだけであり、"work" タイプである必要があります。 |
| アドレス[タイプ eq "職場"].ローカリティ | city |  |
| addresses[type eq "work"].postalCode | postalCode |  |
| addresses[type eq "work"].region | 状態 |  |
| addresses[type eq "work"].streetAddress | streetAddress |  |
| displayName | displayName |  |
| emails[type eq "other"].value | その他のメール | 個人用メール アドレスなど、Exchange Online 受信者オブジェクトにリンクされていない可能性があるユーザーに関連付けられているメール アドレスの一覧。 |
| emails[type eq "proxyAddress"].value | proxyAddresses - SMTP で始まる値の場合のみ: (大文字と小文字は区別されません) | 電子メール アドレスの読み取り専用リスト。 この属性は現在、型 "work" として実装され、プライマリは false に等しくなります。 |
| emails[type eq "work" and primary eq true].value | メール | "work" 型と primary *true* 型の値は 1 つだけ使用できます。 |
| externalId | crossDomainData.scim.v2.externalId | この属性は、Graph エンティティ `crossDomainData`に保持されます。 |
| groups.value | *ノートを参照する* | 読み取り専用です。 ユーザーのグループ メンバーシップ。 この属性は、ユーザーの JSON 本文では返されません。フィルター クエリでのみ使用できます。 |
| ims[type eq "work"].value | imAddresses |  |
| name.familyName | 名字 |  |
| name.givenName | givenName |  |
| パスワード | *ノートを参照する* | 管理されている (非フェデレーション) ドメイン名を含む userName 値を持つユーザーに対して必要です。 書き込み専用 (読み取りできません)。 ユーザーの作成時にのみ設定でき、ユーザーのパスワードの更新には使用できません。 |
| phoneNumbers[type eq "fax"].value | ファックス番号 | この型の値は 1 つだけ使用できます。 |
| phoneNumbers[type eq "mobile"].value | 携帯電話 | この型の値は 1 つだけ使用できます。 |
| phoneNumbers[type eq "work"].value | ビジネスフォン | この型の値は 1 つだけ使用できます。 |
| 優先言語 | 優先言語 | 1 つの言語値のみを許可し、ランク付けされた優先リストは受け入れられません。 |
| title | 職務タイトル |  |
| ユーザー名 | userPrincipalName |  |
| ユーザータイプ | 従業員タイプ |  |

### ユーザー - エンタープライズ拡張機能

この表の属性は、名前空間の `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User`の一部です。

| SCIM 属性 | Microsoft Entra ID 属性 |
| --- | --- |
| コストセンター | employeeOrgData.costCenter |
| 部署 | 部門 |
| division | employeeOrgData.division |
| 従業員番号 | employeeId |
| 管理者.値 | マネージャー |
| 組織 | カンパニーネーム |

### ユーザー - Microsoft Entra 拡張機能

この表の属性は、名前空間の `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User`の一部です。

| SCIM 属性 | Microsoft Entra ID 属性 |
| --- | --- |
| creationType | creationType |
| 従業員採用日 | 従業員採用日 |
| 従業員退勤日時 | 従業員退勤日時 |
| lastPasswordChangeDateTime | lastPasswordChangeDateTime |
| メールニックネーム | メールニックネーム |
| オフィス所在地 | オフィス所在地 |
| onPremisesDistinguishedName | onPremisesDistinguishedName |
| onPremisesDomainName | onPremisesDomainName |
| onPremisesExtensionAttributes.extensionAttribute1 | extensionAttribute1 |
| onPremisesExtensionAttributes.extensionAttribute10 | extensionAttribute10 |
| onPremisesExtensionAttributes.extensionAttribute11 | extensionAttribute11 |
| onPremisesExtensionAttributes.extensionAttribute12 | extensionAttribute12 |
| onPremisesExtensionAttributes.extensionAttribute13 | extensionAttribute13 |
| onPremisesExtensionAttributes.extensionAttribute14 | extensionAttribute14 |
| onPremisesExtensionAttributes.extensionAttribute15 | extensionAttribute15 |
| onPremisesExtensionAttributes.extensionAttribute2 | extensionAttribute2 |
| onPremisesExtensionAttributes.extensionAttribute3 | extensionAttribute3 |
| onPremisesExtensionAttributes.extensionAttribute4 | extensionAttribute4 |
| onPremisesExtensionAttributes.extensionAttribute5 | extensionAttribute5 |
| onPremisesExtensionAttributes.extensionAttribute6 | extensionAttribute6 |
| onPremisesExtensionAttributes.extensionAttribute7 | extensionAttribute7 |
| onPremisesExtensionAttributes.extensionAttribute8 | extensionAttribute8 |
| onPremisesExtensionAttributes.extensionAttribute9 | extensionAttribute9 |
| onPremisesImmutableId | onPremisesImmutableId |
| onPremisesSAMAccountName | onPremisesSAMAccountName |
| オンプレミスセキュリティ識別子 | オンプレミスセキュリティ識別子 |
| オンプレミス同期有効化 | オンプレミス同期有効化 |
| オンプレミスユーザープリンシパル名 (onPremisesUserPrincipalName) | オンプレミスユーザープリンシパル名 (onPremisesUserPrincipalName) |
| ownedGroups.value | ownedObjects (グループのみ) |
| 次回サインイン時のパスワード強制変更 | passwordProfile.forceChangePasswordNextSignIn |
| 次回サインイン時にパスワードを強制変更（MFA有効） | passwordProfile.forceChangePasswordNextSignInWithMFA |
| 希望データ保存場所 | 希望データ保存場所 |
| プロキシアドレス | proxyAddresses (すべての値) |
| usageLocation | usageLocation |
| ユーザータイプ | ユーザータイプ |

注

`urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:ownedGroups` は、読み取り専用の複数値複合属性です。 その `value` サブ属性には、グループ ID が含まれています。 属性の `returned` 値は `never` であるため、ユーザーの応答本文には含まれません。 フィルター ターゲットとして完全修飾 `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:User:ownedGroups.value` パスを使用して、特定のグループを所有するユーザーを検索できます。

注

Microsoft Entra 拡張名前空間には、フォーム `extension_{appId-without-hyphens}_{extensionProperty-name}`の Microsoft Entra ID ディレクトリ拡張機能は含まれません。 SCIM API は、ユーザー プロファイルでのこれらの属性の取得をサポートしていません。

### グループ – コア

| SCIM 属性 | Microsoft Entra ID 属性 | 注意事項/制限事項 |
| --- | --- | --- |
| displayName | displayName |  |
| members.value | *[メモ] 列を参照する* | 読み取り専用です。 グループのメンバー。 この属性はグループの JSON 本文では返されません。フィルター クエリでのみ使用できます。 |

### グループ – Microsoft Entra 拡張機能

この表の属性は、名前空間の `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group`の一部です。

| SCIM 属性 | Microsoft Entra ID 属性 |
| --- | --- |
| 説明 | 説明 |
| 有効期限日時 | 有効期限日時 |
| groupTypes | groupTypes |
| mailEnabled | mailEnabled |
| メールニックネーム | メールニックネーム |
| onPremisesSAMAccountName | onPremisesSAMAccountName |
| オンプレミスセキュリティ識別子 | オンプレミスセキュリティ識別子 |
| オンプレミス同期有効化 | オンプレミス同期有効化 |
| owners.value | 所有者 |
| プロキシアドレス | proxyAddresses (すべての値) |
| セキュリティ有効 | セキュリティ有効 |
| セキュリティ識別子 | セキュリティ識別子 |

注

`urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:owners` は、読み取り専用の複数値複合属性です。 その `value` サブ属性には、ユーザー ID が含まれています。 この属性の`never`値は`returned`であるため、グループ応答の本文には含まれません。 フィルター ターゲットとして完全修飾 `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:Group:owners.value` パスを使用して、特定のユーザーが所有するグループを検索できます。

### カスタム セキュリティ属性名前空間

Microsoft Entra ID テナントでカスタム セキュリティ属性が定義されている場合は、SCIM 要求でこの名前空間 `urn:ietf:params:scim:schemas:extension:Microsoft:Entra:2.0:CustomSecurityAttributes` を使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/export-import-provisioning-configuration"} -->
## ディザスター リカバリーのために Microsoft Entra ID でアプリケーション プロビジョニング構成をエクスポートし、既知の良好な状態にロールバックする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/export-import-provisioning-configuration
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: ディザスター リカバリーのために Microsoft Entra ID でアプリケーション プロビジョニング構成をエクスポートし、既知の良好な状態にロールバックする方法について説明します。

この記事では、次のことについて説明します。

- Microsoft Entra 管理センターからプロビジョニング構成のエクスポートおよびインポートを行う
- Microsoft Graph API を使用して、プロビジョニング構成をエクスポートし、インポートする

### Microsoft Entra 管理センターからプロビジョニング構成のエクスポートおよびインポートを行う

#### プロビジョニング構成をエクスポートする

構成をエクスポートするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動し、アプリケーションを選択します。
3. 左側のナビゲーション ウィンドウで、[ **プロビジョニング**] を選択します。 [ **管理**] で、[ **属性マッピング**] を選択します。 [ **詳細オプション] ドロップダウンを** 選択し、[ **スキーマの編集]** を選択します。 スキーマ エディターが開きます。
4. ページの上部にあるコマンド バーで [ダウンロード] を選択してスキーマをダウンロードします。

#### ディザスター リカバリー - 既知の良好な状態にロールバックする

構成をエクスポートして保存すると、以前のバージョンの構成にロールバックできます。 属性マッピングまたはスコープ フィルターを変更するときにいつでも使用できるように、プロビジョニング構成をエクスポートして保存することをお勧めします。 ダウンロードした JSON ファイルを開き、コンテンツ全体をコピーします。 次に、スキーマ エディターで JSON ペイロードのコンテンツ全体を置き換え、保存します。 アクティブなプロビジョニング サイクルがある場合、そのサイクルは完了し、次のサイクルで更新されたスキーマが使用されます。 また、次のサイクルは、新しい構成に基づいてすべてのユーザーとグループを再評価する初期サイクルになります。

以前の構成にロールバックするときに考慮すべき事項:

- ユーザーをスコープに含める必要があるかどうかを判断するために、ユーザーはもう一度評価されます。 スコープ フィルターが変更された場合、フィルターは無効になっているため、ユーザーはスコープ内に含まれなくなります。 ほとんどの場合、この動作は望ましいものですが、それを防止したい場合もあります。 この動作を回避するには、 [スコープ外の削除のスキップ機能を使用します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions) 。
- プロビジョニング構成を変更すると、サービスが再起動され、 [初期サイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#provisioning-cycles-initial-and-incremental)がトリガーされます。

### Microsoft Graph API を使用して、プロビジョニング構成をエクスポートし、インポートする

Microsoft Graph API と Microsoft Graph Explorer を使用すると、ご自分のユーザー プロビジョニングの属性マッピングとスキーマを JSON ファイルにエクスポートし、それを Microsoft Entra ID にインポートし直すことができます。 ここでキャプチャした手順を使用して、プロビジョニング構成のバックアップを作成することもできます。

#### 手順 1:プロビジョニング アプリのサービス プリンシパル ID (オブジェクト ID) を取得します

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインし、プロビジョニング アプリケーションの [プロパティ] セクションに移動します。 たとえば、 *Workday を AD User Provisioning アプリケーション マッピングに* エクスポートする場合は、そのアプリの [プロパティ] セクションに移動します。
2. プロビジョニング アプリの [プロパティ] セクションで、[ *オブジェクト ID* ] フィールドに関連付けられている GUID 値をコピーします。 この値は、アプリの **ServicePrincipalId** とも呼ばれ、Microsoft Graph エクスプローラーの操作で使用されます。

    [Image: Workday アプリのサービス プリンシパル ID]

#### 手順 2:Microsoft Graph Explorer にサインインします

1. [Microsoft Graph エクスプローラーを起動する](https://developer.microsoft.com/graph/graph-explorer)
2. [Microsoft でSign-In] ボタンを選択し、少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。

    [Image: Microsoft Graph サインイン]
3. サインインに成功すると、左側のペインにユーザー アカウントの詳細が表示されます。

#### 手順 3:プロビジョニング アプリのプロビジョニング ジョブ ID を取得します

Microsoft Graph エクスプローラーで、[servicePrincipalId] を**手順 1** から抽出した ServicePrincipalId に置き換えて、次の GET クエリを実行します。

```http
   GET https://graph.microsoft.com/beta/servicePrincipals/[servicePrincipalId]/synchronization/jobs
```

表示されている応答が返されます。 応答に存在する `id` 属性をコピーします。 この値は **ProvisioningJobId** であり、基になるスキーマ メタデータを取得するために使用されます。

[Image: プロビジョニング ジョブ ID]

#### 手順 4:プロビジョニング スキーマをダウンロードする

Microsoft Graph Explorer で、[servicePrincipalId] と [ProvisioningJobId] を、前の手順で取得した ServicePrincipalId と ProvisioningJobId に置き換えて、次の GET クエリを実行します。

```http
   GET https://graph.microsoft.com/beta/servicePrincipals/[servicePrincipalId]/synchronization/jobs/[ProvisioningJobId]/schema
```

応答から JSON オブジェクトをコピーしてファイルに保存し、スキーマのバックアップを作成します。

#### 手順 5:プロビジョニング スキーマをインポートする

注意事項

Microsoft Entra 管理センターを使用して変更できない構成用にスキーマを変更する必要がある場合、または有効で機能しているスキーマを使用して以前にバックアップしたファイルから構成を復元する必要がある場合にのみ、この手順を実行します。

Microsoft Graph Explorer で、[servicePrincipalId] と [ProvisioningJobId] を、前の手順で取得した ServicePrincipalId と ProvisioningJobId に置き換えて、次の PUT クエリを構成します。

```http
    PUT https://graph.microsoft.com/beta/servicePrincipals/[servicePrincipalId]/synchronization/jobs/[ProvisioningJobId]/schema
```

[Request Body](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/要求本文) タブで、JSON スキーマ ファイルの内容をコピーします。

[Image: 要求本文]

[Request Headers](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/要求ヘッダー) タブで、値が "application/json" の Content-Type ヘッダー属性を追加します。

[Image: 要求ヘッダー]

[ **クエリの実行]** を選択して、新しいスキーマをインポートします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/expression-builder"} -->
## Microsoft Entra ID のアプリケーション プロビジョニングで式ビルダーが機能するしくみを理解する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/expression-builder
- Service: entra-id / app-provisioning
- Article date: 2026-08-06
- Summary: Microsoft Entra ID のアプリケーション プロビジョニングで式ビルダーが機能するしくみを理解します。

[式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)を使用して[属性をマッピングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)ことができます。 以前は、これらの式を手動で作成して、式の入力欄に入力する必要がありました。 式ビルダーは、式の作成に役立つツールです。

[Image: 関数を選ぶ前の、式ビルダーの最初のページ。]

式を作る方法については、「[Azure Active Directory で属性マッピングの式を記述するためのリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)」をご覧ください。

### 式ビルダーを見つける

アプリケーションのプロビジョニングでは、式を使用して属性をマッピングします。 属性マッピング ページで式ビルダーにアクセスするには、左側のナビゲーション メニューから **式ビルダー** を選択します。

### 式ビルダーを使用する

式ビルダーを使用するには、機能と属性を選び、必要ならばサフィックスを入力します。 次に、 **[式の追加]** をクリックして、コード欄に式を追加します。 利用できる機能とその使用方法の詳細は「[Azure Active Directory で属性マッピングの式を記述するためのリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)」をご覧ください。

値を指定し、[テスト式] を選択して **式をテストします**。 たとえば、ドロップダウン リストから **メール** 属性を選択します。 @記号で始まる電子メール ドメインで値を入力します。たとえば、 `@fabrikam.com`。 次に、[ **式のテスト** ] を選択すると、[式の出力の表示] ボックスに式テストの **出力** が表示されます。

式に満足したら、これを属性マッピングに移動します。 これをコピーし、作成している属性マッピングの式の欄に貼り付けます。

### 既知の制限事項

- 式ビルダーでは、拡張属性を選択できません。 ただし、属性マッピングの式では拡張属性を使用できます。
- 1 つの属性マッピング式でサポートされる最大長は **10,000 文字です**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/extend-application-attributes"} -->
## LCW 拡張性ワークフローを使用してカスタム コールアウトを使用して属性マッピングを拡張するためのリファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/extend-application-attributes
- Service: entra-id / app-provisioning
- Article date: 2026-07-31
- Summary: LCW 拡張性ワークフローを使用して、カスタム ビジネス ロジックを使用してターゲット属性の値を生成するAzure ロジック アプリを呼び出す方法について説明します

プロビジョニングを構成するときに指定できる属性マッピングの種類の 1 つは、ライフサイクル ワークフロー (LCW) 機能拡張ワークフロー マッピングの種類です。 このマッピング型を使用すると、式マッピングの種類でサポートされる基本的な変換を超えて属性 [マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)を拡張できるため、カスタム ビジネス ロジックが必要になる可能性があるシナリオを実現できます。

LCW 機能拡張ワークフロー マッピングの種類を構成するには、Azureロジック アプリを呼び出して値を生成する LCW 機能拡張ワークフローを利用します。 その後、Azure ロジック アプリによって生成された値がターゲット属性に適用されます。

### 前提条件

Azure ロジック アプリを作成するには、次のことを確認します。

- [Azure サブスクリプション](https://azure.microsoft.com/pricing/purchase-options/azure-account) (Azure ロジック アプリの作成と使用に必要)
- ロジック [アプリ共同作成者](https://learn.microsoft.com/azure/role-based-access-control/built-in-roles/integration#logic-app-contributor) ロール以上

Azure ロジック アプリをトリガーする LCW 機能拡張ワークフローを作成するには、次のことを確認します。

- [Entra ID ガバナンス ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)
- 少なくとも [ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator) ロール

属性マッピングを含むプロビジョニング ジョブを構成するには、次のことを確認します。

- 少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロール。 オンプレミス Active Directoryへの受信プロビジョニングを構成する場合は、[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールも必要です

### LCW 拡張性ワークフローの概要

LCW 機能拡張ワークフローは、既存の Joiner、Mover、Leaver 型とは異なる新しい種類の [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) です。

Joiner、Mover、Leaver ワークフローと同様に、ワークフローを実行するタスクを指定します。 この場合、タスクは [カスタム拡張機能](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)をトリガーすることです。 カスタム拡張機能は、カスタム ロジックを収容するAzure ロジック アプリのラッパーと考えることができます。拡張ワークフローによってカスタム拡張機能がトリガーされると、Azureロジック アプリが実行されます。

ただし、Joiner、Mover、Leaver の各ワークフローとは異なり、ユーザーがプロビジョニングされる前に機能拡張ワークフローを実行できます。 つまり、特定のユーザー アカウントが Entra ID ディレクトリに存在するかどうかに関係なく、拡張ワークフローを実行できます。

これにより、拡張ワークフローは、事前プロビジョニング (プレ参加者とも呼ばれます) シナリオで役立ちます。 たとえば、拡張機能ワークフローを使用して、新しく雇用された従業員の一意のエイリアスを生成してから、アカウントをディレクトリにプロビジョニングし、その後会社のリソースにアクセスできます。

Note

現在、LCW 拡張機能ワークフローでは、1 つのトリガーの種類 (**プロビジョニング属性マッピング**) と 1 つのタスク (**データ ドリブン カスタム タスク拡張機能の実行**) のみがサポートされています。

### 手順 0: Azure ロジック アプリを作成する

ターゲット属性に適用される 1 つの値を出力する従量課金ベースの Azure ロジック アプリを作成する必要があります。

Azure Logic Appsを初めて使用する場合は、[こちらを参照してください](https://learn.microsoft.com/azure/logic-apps/logic-apps-overview)。 さらに、この[チュートリアル](https://learn.microsoft.com/azure/logic-apps/quickstart-create-example-consumption-workflow)では、初めての従量課金ベースのAzure ロジック アプリを作成する方法について説明します。

### 手順 1: カスタム拡張機能を作成する

機能拡張ワークフローを作成する前に、拡張ワークフローにリンクできるカスタム拡張機能が必要です。 前述のように、カスタム拡張機能は、カスタム ロジックが存在する Azure ロジック アプリのラッパーと考えることができます。 機能拡張ワークフローがカスタム拡張機能をトリガーすると、Azureロジック アプリが実行されます。

#### Microsoft Entra管理センターで

1. ブラウザーを使用して、Microsoft Entra 管理センターを使用してEntra ID [テナントにサインイン](https://entra.microsoft.com)します。
2. **カスタム拡張機能&gt;ライフサイクル ワークフローに移動&gt;カスタム拡張機能を追加**します。

    [Image: Microsoft Entra ポータルのカスタム拡張機能ブレードのスクリーンショット。[カスタム拡張機能の追加] ボタンの場所。]
3. 作成ウィザードの [ **基本** ] タブで、カスタム拡張機能の名前と説明を追加します。
4. [ **タスクの動作** ] タブで、[ **結果の待機の起動 (プレビュー)]** を選択します。
5. [**詳細**] タブでは、カスタム拡張機能を Azure ロジック アプリにリンクできます。 既に作成済みである場合は、[**ロジック アプリの作成**] で **[いいえ**] を選択し、ロジック アプリのサブスクリプション、リソース グループ、ロジック アプリの詳細Azure入力します。

    [Image: カスタム拡張機能の作成ウィザードの詳細ウィンドウのスクリーンショット。顧客は Azure ロジック アプリをリンクします。]
6. [確認 **と作成** ] タブでカスタム拡張機能の詳細を確認し、[ **作成** ] をクリックして完了します。

これで、タスクとして機能拡張ワークフローにリンクする準備が整ったカスタム拡張機能が作成されました。 次に、機能拡張ワークフローの作成に取り組みましょう。

### Microsoft Graphの使用

1. [Microsoft Graph エクスプローラー ツール](https://aka.ms/ge)を起動します。
2. テナントにサインインします。
3. [**アクセス許可の修正**] を選択します。
4. 次の必要なアクセス許可に同意します。 `LifecycleWorkflows-CustomExt.ReadWrite.All`
5. [Create customTaskExtensions API](https://learn.microsoft.com/graph/api/identitygovernance-lifecycleworkflowscontainer-post-customtaskextensions) を使用して、カスタム拡張機能を作成します。

**要求の例**

```http
POST /identityGovernance/lifecycleWorkflows/customTaskExtensions
Content-Type: application/json

{"displayName": "test1","description": "test1","endpointConfiguration": {	"@odata.type": "#microsoft.graph.logicAppTriggerEndpointConfiguration",	"subscriptionId": "00000000-0000-0000-0000-000000000000",	"resourceGroupName": "lcw-synthetics",	"logicAppWorkflowName": "testReply"},"callbackConfiguration": null,"authenticationConfiguration": {	"@odata.type": "#microsoft.graph.azureAdPopTokenAuthentication"},"id": "","clientConfiguration": {	"timeoutInMilliseconds": 1000,	"maximumRetries": 1},"replyMode": "response"
}
```

**応答の例**

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/beta/$metadata#identityGovernance/lifecycleWorkflows/customTaskExtensions/$entity",
  "id": "902ca666-6b67-4d45-839c-8836d7f205f9",
  "displayName": "test1",
  "description": "test1",
  "createdDateTime": "2026-04-20T19:56:27.0723563Z",
  "lastModifiedDateTime": "2026-04-20T19:56:27.0723657Z",
  "replyMode": "response",
  "callbackConfiguration": null,
  "endpointConfiguration": {	"@odata.type": "#microsoft.graph.logicAppTriggerEndpointConfiguration",	"subscriptionId": "00000000-0000-0000-0000-000000000000",	"resourceGroupName": "lcw-synthetics",	"logicAppWorkflowName": "testReply",	"url": "https://prod-05.southcentralus.logic.azure.com:443/workflows/c070dc95455e4e5a98da954feeb7e756/triggers/manual/paths/invoke?api-version=2016-10-01"
  },"authenticationConfiguration": {	"@odata.type": "#microsoft.graph.azureAdPopTokenAuthentication"},"clientConfiguration": {	"maximumRetries": 1,	"timeoutInMilliseconds": 1000}
}
```

これで、タスクとして機能拡張ワークフローにリンクする準備が整ったカスタム拡張機能が作成されました。 次に、機能拡張ワークフローの作成に取り組みましょう。

### 手順 2: 拡張性ワークフローを作成する

カスタム拡張機能を作成したら、カスタム拡張機能をトリガーするタスクを持つ拡張ワークフローを作成できるようになりました。

#### Microsoft Entra管理センターで

1. ブラウザーを使用して、Microsoft Entra 管理センターを使用してEntra ID [テナントにサインイン](https://entra.microsoft.com)します。
2. **[Id ガバナンス] &gt; [ライフサイクル ワークフロー] &gt; [ワークフローの作成]** に移動します。
3. [テンプレートの選択] タブで、 **リアルタイム プロビジョニング機能拡張テンプレートを** 選択します。

    [Image: ライフサイクル ワークフロー作成ウィザードのテンプレート カタログのスクリーンショット。そのうちの 1 つは拡張ワークフローのテンプレートです。]
4. [ **基本** ] タブで、ワークフローの名前と説明を入力します。 トリガーの種類は、プロビジョニング **属性マッピング**に自動的に設定されます。これは、現在拡張ワークフローをサポートしている唯一のトリガーの種類です。
5. 次に、カスタム拡張機能をトリガーするように機能拡張ワークフローを構成しましょう。 [ **タスクの確認** ] タブで、[ **データ ドリブン カスタム タスク拡張機能の実行]** タスクをクリックします。このタスクは、リアルタイム プロビジョニング機能拡張テンプレートの一部としてワークフロー タスク テーブルに自動的に追加されます。

    [Image: ライフサイクル ワークフロー作成ウィザードの [タスクの確認] ウィンドウのスクリーンショット。機能拡張ワークフロー テンプレートに [データ ドリブン カスタム タスク拡張機能の実行] タスクが一覧表示されています。]
6. [ **データ ドリブン カスタム タスク拡張機能の実行** ] コンテキスト ウィンドウで、[ **カスタム拡張機能の選択** ] フィールドをクリックし、前のセクションで作成したカスタム拡張機能を選択します。
7. **保存** をクリックします。
8. [確認 **と作成** ] タブでワークフローの詳細を確認し、[ **作成** ] をクリックして完了します。

これで、カスタム ロジックを含む Azure ロジック アプリをトリガーできる機能拡張ワークフローが作成されました。 次に、拡張ワークフローをターゲット属性にマッピングします。

#### Microsoft Graphの使用

1. [Microsoft Graph エクスプローラー ツール](https://aka.ms/ge)を起動します。
2. テナントにサインインします。
3. [**アクセス許可の修正**] を選択します。
4. 次の必要なアクセス許可に同意します。 `LifecycleWorkflows-Workflow.ReadWrite.All`
5. ワークフローの [作成 API](https://learn.microsoft.com/graph/api/identitygovernance-lifecycleworkflowscontainer-post-workflows) を使用して、LCW 機能拡張ワークフローを作成します。

**要求の例**

```http
POST /identityGovernance/lifecycleWorkflows/workflows
Content-Type: application/json

{"category": "extensibility","displayName": "Real-time Provisioning extensibility (Preview)","description": "Execute real-time extensibility tasks for performing attribute mapping extensions","tasks": [	{
      "arguments": [
        {
          "name": "customTaskExtensionID",				"value": "f740553f-a6a2-4dc8-82df-148336dcd920"			}		],		"description": "Run a Custom Task Extension and pass data from the workflow as input",		"displayName": "Run a Data driven Custom Task Extension (Preview)",		"isEnabled": true,		"id": "77bb973f-3150-4a0f-b49b-24d1baa677f6",		"continueOnError": false,		"taskDefinitionId": "09303719-609e-4348-8bbc-d3ee45a2657e",		"category": "extensibility"	}],"executionConditions": {	"@odata.type": "#microsoft.graph.identityGovernance.provisioningAttributeMapping"
 	},"isEnabled": true,"isSchedulingEnabled": false,"targetSubjectType": "provisioningObject"
}
```

**応答の例**

```http
HTTP/1.1 201 Created
Content-Type: application/json

{"@odata.context": "https://graph.microsoft.com/beta/$metadata#identityGovernance/lifecycleWorkflows/workflows/$entity","category": "extensibility","description": "Execute real-time extensibility tasks for performing attribute mapping extensions","displayName": "Real-time Provisioning extensibility (Preview)","isEnabled": true,"isSchedulingEnabled": false,"lastModifiedDateTime": "2026-04-20T19:58:42.4447369Z","targetSubjectType": "provisioningObject","createdDateTime": "2026-04-20T19:58:42.444727Z","deletedDateTime": null,"id": "e41388b5-446e-4cf1-9bce-92cd09f828e5","nextScheduleRunDateTime": null,"version": 1,"executionConditions": {	"@odata.type": "#microsoft.graph.identityGovernance.provisioningAttributeMapping"},"quarantineDetails": {	"quarantinedDateTime": null,	"quarantineType": "notQuarantined",	"quarantineReason": null},"settings": {	"quarantineConfiguration": {		"matchMode": "any",		"conditions": []	}}
}
```

これで、カスタム ロジックを含む Azure ロジック アプリをトリガーできる機能拡張ワークフローが作成されました。 次に、拡張ワークフローをターゲット属性にマッピングします。

### 手順 3: 拡張ワークフローをターゲット属性にマッピングする

機能拡張ワークフローを作成したので、プロビジョニング ジョブの属性にマップします。 そのため、プロビジョニング ジョブが進行中の場合、拡張ワークフローでは、プロビジョニング ジョブのスコープ内のすべてのオブジェクトに対してその属性の値が生成されます。

たとえば、拡張ワークフローを **userPrincipalName** ターゲット属性にマップし、プロビジョニング ジョブのスコープ内に 5 人のユーザーがいる場合、ワークフローは 5 人のユーザーすべてに対して **userPrincipalName** 属性の値を生成します。

#### Microsoft Entra管理センターで

1. ブラウザーを使用して、Microsoft Entra 管理センターを使用してEntra ID [テナントにサインイン](https://entra.microsoft.com)します。
2. **[エンタープライズ アプリ] &gt; [すべてのアプリケーション**] に移動します。

    [Image: Microsoft Entra ポータルの [エンタープライズ アプリ] ブレードのスクリーンショット。]
3. 特定のアプリケーション (SuccessFactors や Workday などの HR コネクタ、SaaS アプリケーションなど) を選択します。
4. アプリケーション内で、[ **プロビジョニング &gt; 属性マッピング]** に移動します。
5. 属性マッピングの一覧で、カスタム ロジックを呼び出すターゲット属性を見つけます。 その属性の編集アイコン (鉛筆記号) を選択します。

    [Image: [属性マッピング] ブレードのスクリーンショット。鉛筆アイコンは、[属性マッピングの編集] ブレードのエントリ ポイントです。]
6. [**属性マッピングの編集]** ブレードで、[**マッピングの種類**] ドロップダウンで **LCW 拡張機能ワークフロー**を選択します。
7. **[LCW 機能拡張ワークフロー**] フィールドをクリックして、機能拡張ワークフロー ピッカーを開きます。
8. [ **機能拡張ワークフローの選択** ] コンテキスト ウィンドウで、一覧から適切な機能拡張ワークフローを選択し、[ **選択**] をクリックします。
9. **[入力属性**] フィールドで、拡張ワークフローに渡すソース システム属性と属性のエイリアスを指定します (エイリアスは、ソース属性名が長いか人間が判読できない場合があり、フレンドリ名が必要な場合があるシナリオで特に役立ちます)。
10. [属性マッピングの **編集]** ブレードの下部にある **[編集]** ボタンを選択します。

拡張ワークフローがターゲット属性に正常にマップされた場合、[ **属性マッピング** ] ページが更新され、[ **マッピングの種類]** 列にその属性の "LCW 機能拡張ワークフロー" が表示されます。

プロビジョニング ジョブの設定の構成が完了したら、通常どおりにプロビジョニング ジョブを開始できます。 LCW 拡張性ワークフロー wil は、スコープ内のすべてのオブジェクトに対して実行されるようになりました。

#### Microsoft Graphの使用

1. ブラウザーを使用して、Microsoft Entra 管理センターを使用してEntra ID [テナントにサインイン](https://entra.microsoft.com)します。
2. **[エンタープライズ アプリ] &gt; [すべてのアプリケーション**] に移動します。
3. 属性マッピングを変更するアプリケーションを選択します。
4. **[プロビジョニング&gt;属性マッピング]** に移動し、[**スキーマの編集] &gt; [詳細設定] オプション**を選択します。

    Important

    変更を加える前に、既存のスキーマのコピーを保存することをお勧めします。 これにより、スキーマの安定したバージョンまたは変更されていないバージョンに簡単に戻すことができます。
5. 編集するターゲット属性を検索して、LCW 機能拡張ワークフローにマップします。 その属性に対して変更する必要があるフィールドを次に示します。

    | フィールドの名前 | Description |
    | --- | --- |
    | `flowType` | LCW 拡張性ワークフローはイベントの`ObjectAddOnly`、する必要があります |
    | `matchingPriority` | LCW 機能拡張ワークフローにマップされているターゲット属性は`0`、する必要があります |
    | `expression` | これは、LCW 拡張機能ワークフローの GUID になります |
    | `name` | `expression`と同じです。これは LCW 機能拡張ワークフローの GUID になります |
    | `type` | `LifecycleWorkflow` にする必要があります。 |
    | `parameters` | ここでは、Azure ロジック アプリに入力として渡すソース属性を構成します。 `key`は、特定の入力のエイリアスです (これにより、開発者はフレンドリ名を使用して、Azure ロジック アプリの入力を参照できます。これは、ソース属性が長いか複雑な場合に役立ちます)。 `expression``name`はソース属性になります。 |

    **LCW 機能拡張ワークフローを使用する新しい属性マッピングの例**

    次の例では、 `userPrincipalName` ターゲット属性を更新しています。

    ```http
    {"defaultValue": null,"exportMissingReferences": false,"flowBehavior": "FlowWhenChanged","flowType": "ObjectAddOnly","matchingPriority": 0,"targetAttributeName": "userPrincipalName","source": {	"expression": "2b19441a-9ff0-449d-ba74-3e4226eff132",	"name": "2b19441a-9ff0-449d-ba74-3e4226eff132",	"type": "LifecycleWorkflow",	"parameters": [		{			"key": "firstname",			"value": {				"expression": "[name.givenName]",				"name": "name.givenName",				"type": "Attribute",				"parameters": []			}		},		{			"key": "lastname",			"value": {				"expression": "[name.familyName]",				"name": "name.familyName",				"type": "Attribute",				"parameters": []			}		}	]}
    }
    ```
6. 編集した部分だけでなく、編集したスキーマ オブジェクト **全体** を保存します (別のファイルで保存することを検討してください)。 後で Microsoft Graph Explorer ツールの要求本文でスキーマ オブジェクト全体を指定する必要があります。
7. [Microsoft Graph エクスプローラー ツール](https://aka.ms/ge)を起動します。
8. テナントにサインインします。
9. [**アクセス許可の修正**] を選択します。
10. 次の必要なアクセス許可に同意します。 `Synchronization.ReadWrite.All`
11. 編集したスキーマ オブジェクトを Microsoft Graph Explorer ツールの**要求本文**セクションにコピー/貼り付け、[synchronizationSchema API の更新](https://learn.microsoft.com/graph/api/synchronization-synchronizationschema-update)を使用して、特定のプロビジョニング ジョブの属性マッピングを更新します。

**要求の例**

```http
PUT /servicePrincipals/{id}/synchronization/jobs/{jobId}/schema
Content-Type: application/json

// In your request body, paste the entire schema object
```

**応答の例**

```http
HTTP/1.1 204 No Content
```

プロビジョニング ジョブの設定の構成が完了したら、通常どおりにプロビジョニング ジョブを開始できます。 LCW 拡張性ワークフロー wil は、スコープ内のすべてのオブジェクトに対して実行されるようになりました。

### 制限

次の機能の制限事項に注意してください。

- **クラウド同期では、カスタム コールアウトはサポートされていません。** LCW 拡張ワークフローは、HR 受信、API 駆動型プロビジョニング、SaaS 送信プロビジョニング、テナント間同期フローにのみ使用できます。
- **LCW 拡張ワークフロー マッピングの種類を持つ属性は、照合に使用できません。** LCW 機能拡張ワークフローを使用して属性の値が生成された場合、一致する属性として使用することはできません。
- **カスタム コールアウトは、作成イベントでのみサポートされます。** LCW 機能拡張ワークフロー マッピングは、更新または削除ではなく、作成時にのみ適用できます。
- **カスタム セキュリティ属性 (CA)。** CA の値を生成するための LCW 拡張ワークフローの使用は、明示的にはサポートされていません。 その場合は、慎重に進めてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/functions-for-customizing-application-data"} -->
## Microsoft Entra Application Provisioning での属性マッピングの式の記述に関するリファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data
- Service: entra-id / app-provisioning
- Article date: 2026-10-02
- Summary: 式マッピングを使用して、Microsoft Entra IDでの SaaS アプリ オブジェクトの自動プロビジョニング中に属性値を許容可能な形式に変換する方法について説明します。 関数の参照一覧が含まれています。

SaaS アプリケーションに対してプロビジョニングを構成するときに指定できる属性マッピングの種類の 1 つは、式マッピングです。 これらのマッピングでは、ユーザーのデータを SaaS アプリケーションが許容可能な形式に変換することができる、スクリプトのような式を記述する必要があります。

### 構文の概要

属性マッピングの式の構文は、Visual Basic for Applications (VBA) 関数を思い起こさせます。

- 式全体は、関数の形式で定義する必要があります。名前の後にかっこで囲んだ引数を続けます。*FunctionName(`<<argument 1>>`,`<<argument N>>`)*
- 各関数内で他の関数を入れ子にすることができます。 例: *FunctionOne(FunctionTwo(`<<argument1>>`))*
- 関数には、次の 3 つの異なる種類の引数を渡すことができます。

    1. 属性。角かっこで囲む必要があります。 例: [attributeName]
    2. 文字列定数。二重引用符で囲む必要があります。 例: "米国"
    3. 他の関数 次に例を示します。FunctionOne(`<<argument1>>`, FunctionTwo(`<<argument2>>`))
- 文字列定数では、文字列に円記号 (\) または引用符 (") を含める必要がある場合は、円記号 (\) でエスケープする必要があります。 例: "Company name: \"Contoso\""
- この構文では大文字と小文字が区別されます。これは、関数に文字列として入力するときに考慮する必要があり、ここから直接コピーして貼り付けることもできます。

Important

1 つの属性マッピング式でサポートされる最大長は **10,000 文字です**。

### 関数の一覧

AppendAppRoleAssignmentsComplexBitAndCBoolCDateCoalesceConvertToBase64ConvertToUTF8HexCountCStrDateAddDateDiffDateFromNumDefaultDomainFormatDateTimeGuidIgnoreFlowIfNullOrEmptyIIFInStrIsNullIsNullOrEmptyIsPresentIsStringItemJoinLeftLenMidNormalizeDiacriticsNormalizeDiacriticsByCultureNotNowNumFromDatePCaseRandomStringRedactRemoveDuplicatesReplaceSelectUniqueValueSingleAppRoleAssignmentSplitStripSpacesSwitchToLowerToUpperWord

#### 追加

**関数:** Append(source, suffix)

**説明:** source 文字列値を受け取り、その末尾に suffix を追加します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常は、source オブジェクトの属性の名前。 |
| **接尾辞** | 必須 | 糸 | source 値の末尾に追加する文字列。 |

##### ユーザー名に定数のサフィックスを追加する

例: Salesforce Sandbox を使用している場合は、ユーザー名を同期する前に、すべてのユーザー名に別のサフィックスを追加する必要があります。

**式:**`Append([userPrincipalName], ".test")`

**サンプル入力/出力:**

- **入力**: (userPrincipalName): "John.Doe@contoso.com"
- **出力**: "John.Doe@contoso.com.test"

#### AppRoleAssignmentsComplex

**関数:** AppRoleAssignmentsComplex([appRoleAssignments])

**説明:** ユーザーに対して複数のロールを構成するために使用されます。 詳細な使用方法については、「Tutorial - Microsoft Entra IDを参照してください。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **appRoleAssignments** | 必須 | 糸 | **appRoleAssignments** オブジェクト |

#### BitAnd

**関数:** BitAnd(value1, value2)

**説明:** この関数は両方のパラメーターをバイナリ表現に変換し、ビットを次に設定します。

- 0 - value1 と value2 内の対応するビットの 1 つまたは両方が 0 の場合
- 1 - 対応するビットの両方が 1 の場合。

つまり、両方のパラメーターの対応するビットが 1 の場合を除くすべてのケースで 0 を返します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **value1** | 必須 | 番号 | value2 と AND で処理する数値 |
| **value2** | 必須 | 番号 | value1 と AND で処理する数値 |

**例:**`BitAnd(&HF, &HF7)`

11110111 AND 00000111 = 00000111。`BitAnd` からは 7 が返されます。その 2 進数は 00000111 です。

#### CBool

**関数:**`CBool(Expression)`

**説明:**`CBool` からは、式の評価結果に基づいてブール値が返されます。 式の評価結果が 0 以外の値の場合は `CBool` によって *True* が返され、それ以外の場合は *False* が返されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **[式]** | 必須 | 式 | 任意の有効な式 |

**例:**`CBool([attribute1] = [attribute2])` 両方の属性が同じ値を持つ場合は、True を返します。

#### CDate

**関数:**`CDate(expression)`

**説明:** CDate 関数は、文字列から UTC DateTime を返します。 DateTime はネイティブな属性の種類ではありませんが、FormatDateTime や DateAdd などの日付関数の中で使用することができます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **[式]** | 必須 | 表現 | 日付/時刻を表す任意の有効な文字列。 サポートされている形式については、[.NETカスタム日時書式指定文字列](https://learn.microsoft.com/ja-jp/dotnet/standard/base-types/custom-date-and-time-format-strings)を参照してください。 |

**備考:** 返される文字列は常に UTC で、**M/d/yyyy h:mm:ss tt** の形式に従います。

**例 1:**`CDate([StatusHireDate])`**サンプル入力/出力:**

- **入力** (StatusHireDate): "2020-03-16-07:00"
- **出力**: "3/16/2020 7:00:00 AM" &lt;-- *上記の DateTime に相当する UTC が返されます*

**例 2:**`CDate("2021-06-30+08:00")`**サンプル入力/出力:**

- **入力**: "2021-06-30+08:00"
- **出力**: "6/29/2021 4:00:00 PM" &lt;-- *上記の DateTime に相当する UTC が返されます*

**例 3:**`CDate("2009-06-15T01:45:30-07:00")`**サンプル入力/出力:**

- **入力**: "2009-06-15T01:45:30-07:00"
- **出力**: "6/15/2009 8:45:30 AM" &lt;-- *上記の DateTime に相当する UTC が返されます*

#### 合体

**関数:** Coalesce(source1, source2, ..., defaultValue)

**説明:** NULL ではない最初のソース値が返されます。 すべての引数が NULL で、defaultValue が存在する場合、defaultValue が返されます。 すべての引数が NULL で、defaultValue が存在しない場合、Coalesce からは NULL が返されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース 1 … sourceN** | 必須 | 糸 | 必須、回数は可変。 通常は、source オブジェクトの属性の名前。 |
| **defaultValue** | 省略可能 | 糸 | すべてのソース値が NULL の場合に使用される既定値。 空の文字列 ("") を指定できます。 |

##### NULL でない場合はフロー メール値、それ以外の場合は userPrincipalName

例: メール属性が存在する場合は、それをフローすることをお勧めします。 存在しない場合、代わりに userPrincipalName の値をフローしてください。

**式:**`Coalesce([mail],[userPrincipalName])`

**サンプル入力/出力:**

- **入力** (mail):NULL
- **入力** (userPrincipalName): "John.Doe@contoso.com"
- **出力**: "John.Doe@contoso.com"

#### ConvertToBase64

**関数:** ConvertToBase64(source)

**説明:** ConvertToBase64 関数は、文字列を Unicode の base64 文字列に変換します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | base 64 に変換される文字列 |

**例:**`ConvertToBase64("Hello world!")`

"SABlAGwAbABvACAAdwBvAHIAbABkACEA" を返します。

#### ConvertToUTF8Hex

**関数:** ConvertToUTF8Hex(source)

**説明:** ConvertToUTF8Hex 関数は、文字列を UTF8 の 16 進数でエンコードされた値に変換します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | UTF8 Hex に変換される文字列 |

**例:**`ConvertToUTF8Hex("Hello world!")`

48656C6C6F20776F726C6421 を返します。

#### 数える

**関数:** Count(attribute)

**説明:** Count 関数は、複数値の属性内の要素数を返します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| 属性 | 必須 | 属性 | 要素がカウントされた複数値の属性 |

#### CStr

**関数:** CStr(value)

**説明:** CStr 関数では、値が文字列データ型に変換されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **価値** | 必須 | 数値、参照、またはブール値 | 数値、参照属性、ブール値を指定できます。 |

**例:**`CStr([dn])`

"cn=Joe,dc=contoso,dc=com" が返されます

#### DateAdd（日付追加）

**関数:**`DateAdd(interval, value, dateTime)`

**説明:** 指定した時間間隔が付加された日付を表す日時文字列を返します。 返される日付は、**M/d/yyyy h:mm:ss tt** 形式です。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **間** | 必須 | 糸 | 追加する時間間隔。 この表の後の、指定可能な値を参照してください。 |
| **価値** | 必須 | 数値 | 追加する単位の数。 正の数 (将来の日時を取得する場合) または負の数 (過去の日時を取得する場合) を指定できます。 |
| **dateTime** | 必須 | 日付と時間 | 間隔が追加される日付を表す DateTime。 |

日付文字列を入力として渡す場合は、CDate 関数を使用して datetime 文字列をラップします。 UTC でシステム時刻を取得するには、Now 関数を使用します。

**間隔**文字列には次のいずれかの値が必要です。

- yyyy: 年
- m: 月
- d: 日
- ww: 週
- h: 時
- n: 分
- s: 秒

**例 1: Workday から受信 StatusHireDate に基づいて日付値を生成する**`DateAdd("d", 7, CDate([StatusHireDate]))`

| 例 | サイクル間隔 | 価値 | dateTime (変数 StatusHireDate の値) | 出力 |
| --- | --- | --- | --- | --- |
| 入社日に 7 日を追加する | "d" | 7 | 2012-03-16-07:00 | 2012 年 3 月 23 日午前 7:00:00 |
| 入社日の 10 日前の日付を取得する | "d" | -10 | 2012-03-16-07:00 | 2012 年 3 月 6 日午前 7:00:00 |
| 入社日に 2 週間を追加する | "ww" | 2 | 2012-03-16-07:00 | 2012 年 3 月 30 日午前 7:00:00 |
| 入社日に 10 か月を追加する | "m" | 10 | 2012-03-16-07:00 | 2013 年 1 月 16 日午前 7:00:00 |
| 入社日に 2 年を追加する | "yyyy" | 2 | 2012-03-16-07:00 | 3/16/2014 7:00:00 AM |

#### 日付差異

**関数:**`DateDiff(interval, date1, date2)`

**説明:** この関数は *interval* パラメーターを使用して、入力された 2 つの日付の差を示す数値を返します。 を返します。

- date2 が date1 より大きい場合は正の数
- date2 が date1 より小さい場合は負の数
- date2 と date1 が等しい場合は 0

**パラメーター:**

| 名前 | 必須/省略可能 | タイプ | 注記 |
| --- | --- | --- | --- |
| **間** | 必須 | 糸 | 差の計算に使用する時間の間隔。 |
| **date1** | 必須 | 日付と時間 | 有効な日付を表す DateTime。 |
| **date2** | 必須 | 日付と時間 | 有効な日付を表す DateTime。 |

日付文字列を入力として渡す場合は、CDate 関数を使用して datetime 文字列をラップします。 UTC でシステム時刻を取得するには、Now 関数を使用します。

**間隔**文字列には次のいずれかの値が必要です。

- yyyy: 年
- m: 月
- d: 日
- ww: 週
- h: 時
- n: 分
- s: 秒

**例 1: 現在の日付と Workday の採用日を異なる間隔で比較する**`DateDiff("d", Now(), CDate([StatusHireDate]))`

| 例 | サイクル間隔 | date1 | date2 | 出力 |
| --- | --- | --- | --- | --- |
| 2 つの日付の間における日数の正の差 | d | 2021-08-18+08:00 | 2021-08-31+08:00 | 13 |
| 2 つの日付の間における日数の負の差 | d | 2021 年 8 月 25 日午後 5:41:18 | 2012-03-16-07:00 | -3449 |
| 2 つの日付の間の週の差 | ww | 2021 年 8 月 25 日午後 5:41:18 | 2012-03-16-07:00 | -493 |
| 2 つの日付の間の月の差 | m | 2021 年 8 月 25 日午後 5:41:18 | 2012-03-16-07:00 | -113 |
| 2 つの日付の間の年の差 | yyyy | 2021 年 8 月 25 日午後 5:41:18 | 2012-03-16-07:00 | -9 |
| 両方の日付が同じ場合の差 | d | 2021-08-31+08:00 | 2021-08-31+08:00 | 0 |
| 2 つの日付の間の時間の差 | h | 2021-08-24 | 2021-08-25 | 二十四 |
| 2 つの日付の間の分の差 | n | 2021-08-24 | 2021-08-25 | 1440 |
| 2 つの日付の間の秒の差 | s | 2021-08-24 | 2021-08-25 | 86400 |

**例 2: DateDiff と IIF 関数を組み合わせて属性値を設定する** Workday でアカウントが Active な場合、雇用日が次の 5 日以内の場合にのみ、ユーザーの *accountEnabled* 属性を True に設定します。

```
Switch([Active], , 
  "1", IIF(DateDiff("d", Now(), CDate([StatusHireDate])) > 5, "False", "True"), 
  "0", "False")
```

#### DateFromNum

**関数:** DateFromNum(value)

**説明:** DateFromNum 関数は、AD の日付形式の値を DateTime 型に変換します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **価値** | 必須 | 日付 | DateTime 型に変換される AD 日付 |

**例:**`DateFromNum([lastLogonTimestamp])`

`DateFromNum(129699324000000000)`

2012 年 1 月 1 日の午後 11:00 を表す DateTime が返されます。

#### DefaultDomain

**関数：** DefaultDomain()

**Description:** 現在のMicrosoft Entra テナントの既定の検証済みドメインの名前を返します。 既定の検証済みドメインは、既定としてマークされている検証済みドメインのテナントの一覧のドメインです。 既定の検証済みドメインが見つからない場合、関数はエラーを返します。

この関数は入力パラメーターを受け取っていません。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| *なし* |  |  | この関数はパラメーターを受け取りません。 |

**例:**

テナントの既定のドメインを使用してユーザー プリンシパル名を生成するには:

`Join("@", [mailNickname], DefaultDomain())`

#### 日付と時間のフォーマット

**関数:** FormatDateTime(source, dateTimeStyles, inputFormat, outputFormat)

**説明:** 1 つの形式の日付文字列を受け取り、別の形式に変換します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常は、source オブジェクトの属性の名前。 |
| **dateTimeStyles** | 省略可能 | 糸 | このパラメーターを使用して、いくつかの日時解析メソッドによる文字列の解析をカスタマイズする形式指定オプションを指定します。 サポートされる値については、[DateTimeStyles のドキュメント](https://learn.microsoft.com/ja-jp/dotnet/api/system.globalization.datetimestyles)を参照してください。空のままにすると、使用される既定値は DateTimeStyles.RoundtripKind、DateTimeStyles.AllowLeadingWhite、DateTimeStyles.AllowTrailingWhite です |
| **inputFormat** | 必須 | 糸 | 有効な形式の source 値。 サポートされている形式については、「[.NETカスタム日時書式指定文字列](https://learn.microsoft.com/ja-jp/dotnet/standard/base-types/custom-date-and-time-format-strings)を参照してください。 |
| **outputFormat** | 必須 | 糸 | 出力日付の形式。 |

##### 特定の形式の文字列として日付を出力する

例: ServiceNow などの SaaS アプリケーションに特定の形式で日付を送信する場合があります。 次の式を使用することを検討してください。

**式:**

`FormatDateTime([extensionAttribute1], , "yyyyMMddHHmmss.fZ", "yyyy-MM-dd")`

**サンプル入力/出力:**

- **入力** (extensionAttribute1):"20150123105347.1Z"
- **出力**: "2015-01-23"

#### Guid

**関数:** Guid()

**説明:** GUID 関数は、新しいランダムな GUID を生成します。

**例:**`Guid()` サンプル出力: "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"

#### IgnoreFlowIfNullOrEmpty

**関数:** IgnoreFlowIfNullOrEmpty(expression)

**説明**: IgnoreFlowIfNullOrEmpty 関数は、属性を無視し、囲まれた関数または属性が NULL または空の場合にフローから削除するようにプロビジョニング サービスに指示します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **[式]** | 必須 | 表現 | 評価の対象となる式 |

**例 1: 属性が null の場合はフローしない**`IgnoreFlowIfNullOrEmpty([department])` 上記の式では、プロビジョニング フローが null または空の場合は、department 属性が削除されます。

**例 2: 式マッピングが空の文字列または null と評価された場合、属性をフローしない** SuccessFactors 属性 *prefix* は、次の式マッピングを使用して、オンプレミスの Active Directory属性 *personalTitle* にマップされているとします。`IgnoreFlowIfNullOrEmpty(Switch([prefix], "", "3443", "Dr.", "3444", "Prof.", "3445", "Prof. Dr."))` 上の式は、まず Switch 関数を評価します。 *prefix* 属性に *Switch* 関数内に一覧表示されている値がない場合、\*\* は空の文字列を返し、属性 *personalTitle* はオンプレミスの Active Directoryへのプロビジョニング フローに含まれません。

Important

IIF の条件引数として IgnoreFlowIfNullOrEmpty を使用しないでください。 これはフロー制御ラッパーであり、ブール条件ではありません。 両方の動作が必要な場合は、完全な IIF 式を IgnoreFlowIfNullOrEmpty: `IgnoreFlowIfNullOrEmpty(IIF(...))`でラップします。

#### IIF

**関数:** IIF(condition,valueIfTrue,valueIfFalse)

**説明:** IIF 関数は、指定した条件に基づいて、使用できる一連の値のうち、いずれかを返します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **条件** | 必須 | 変数または式 | true または false に評価できる任意の値または式。 |
| **valueIfTrue** | 必須 | 変数または文字列 | 条件の評価結果が true の場合に返される値。 |
| **valueIfFalse** | 必須 | 変数または文字列 | 条件の評価結果が false の場合に返される値。 |

"条件" では、次の比較演算子を使用できます。

- 等しい (=) と等しくない (&lt;&gt;)
- より大きい (&gt;) と次の値以上 (&gt;=)
- より小さい (&lt;) と次の値以下 (&lt;=)

**例:** country="USA" の場合はターゲット属性値をソースの country 属性に設定し、それ以外の場合はターゲット属性値をソースの department 属性に設定します。 `IIF([country]="USA",[country],[department])`

##### 既知の制限事項

このセクションには、IIF 関数の制限事項と回避策が含まれています。 ユーザー作成に関する問題のトラブルシューティングについては、「[null または空の値が原因で作成に失敗する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-creation-issues)」を参照してください。

- 現在、IIF 関数では AND および OR 論理演算子をサポートしません。
- AND ロジックを実装するには、*trueValue* パスに沿ってチェーンされた入れ子になった IIF ステートメントを使用します。 例: country="USA" かつ state="CA" の場合、"True" の値を返し、それ以外の場合は "False" を返します。 `IIF([country]="USA",IIF([state]="CA","True","False"),"False")`
- OR ロジックを実装するには、*falseValue* パスに沿ってチェーンされた入れ子になった IIF ステートメントを使用します。 例: country="USA" または state="CA" の場合、"True" の値を返し、それ以外の場合は "False" を返します。 `IIF([country]="USA","True",IIF([state]="CA","True","False"))`
- IIF 関数内で使用されるソース属性が空または null の場合、条件チェックは失敗します。
    - サポートされていない IIF 式の例:
        - `IIF([country]="","Other",[country])`
        - `IIF(IsNullOrEmpty([country]),"Other",[country])`
        - `IIF(IsPresent([country]),[country],"Other")`
    - 推奨される回避策: Switch関数を使用して、空または null 値がないか確認します。 例: country 属性が空の場合は、値 "Other" を設定します。 存在する場合は、country 属性値をターゲット属性に渡します。
        - `Switch([country],[country],"","Other")`
- IIF の条件引数として IgnoreFlowIfNullOrEmptyを使用しないでください。 IgnoreFlowIfNullOrEmpty は、ブール条件ではなくフロー制御ラッパーであり、IIF が比較できる値を生成しません。 両方の動作が必要な場合は、完全な IIF 式を IgnoreFlowIfNullOrEmpty でラップします。
    - サポート： `IIF(IgnoreFlowIfNullOrEmpty([country]),[country],"Other")`
    - 対応： `IgnoreFlowIfNullOrEmpty(IIF([country]="USA",[region],""))`

#### InStr（文字列内検索関数）

**関数:** InStr(value1, value2, start, compareType)

**説明:** InStr 関数は、文字列内の最初の部分文字列を検索します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **value1** | 必須 | 糸 | 検索対象の文字列 |
| **value2** | 必須 | 糸 | 検索する文字列 |
| **を開始** | 省略可能 | 整数 | 部分文字列の検索を開始する位置 |
| **compareType** | 省略可能 | 列挙型 | vbTextCompare か vbBinaryCompare になります |

**例:**`InStr("The quick brown fox","quick")`

評価結果は 5 になります。

`InStr("repEated","e",3,vbBinaryCompare)`

評価結果は 7 になります。

#### IsNull

**関数:** IsNull(Expression)

**説明:** 式の評価結果が Null の場合、IsNull 関数は true を返します。 属性の場合、Null は属性の不在によって表されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **[式]** | 必須 | 表現 | 評価の対象となる式 |

**例:**`IsNull([displayName])`

属性がない場合は True が返されます。

#### IsNullorEmpty

**関数:** IsNullOrEmpty(Expression)

**説明:** 式が null または空の文字列の場合、IsNullOrEmpty 関数は true を返します。 属性が存在しないか、存在するが空の文字列である場合、これは True と評価されます。 この関数の逆の関数は IsPresent です。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **[式]** | 必須 | 表現 | 評価の対象となる式 |

**例:**`IsNullOrEmpty([displayName])`

属性がないか、空の文字列の場合は True が返されます。

#### IsPresent

**関数:** IsPresent(Expression)

**説明:** 式の評価結果が Null でもなく、空でもない文字列の場合、IsPresent 関数は true を返します。 この関数の逆関数は IsNullOrEmpty です。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **[式]** | 必須 | 表現 | 評価の対象となる式 |

**例:**

`IsPresent([directManager])`

directManager 属性が null または空でない場合は True を返します。

注意事項

Switch 関数のソース パラメーターとして IsPresent、IsNull、または IsNullOrEmpty を使用しないでください。 Switch 関数は大文字と小文字を区別する文字列比較を実行し、これらの関数はブール値を返します。これにより、予期しない一致動作が発生します。

注

属性に値があるかどうかを確認し、フォールバックを適用するには、属性をソースとして Switch 関数を使用し、キーとして空の文字列を使用します。 例えば： `Switch([country], [country], "", "Other")` この式は、存在する場合は country 値を返し、属性が null または空の場合は "Other" を返します。

#### IsString

**関数:** IsString(Expression)

**説明:** 式を文字列型として評価できる場合、IsString 関数の評価結果は True になります。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **[式]** | 必須 | 表現 | 評価の対象となる式 |

#### アイテム

**関数:** Item(attribute, index)

**説明:** Item 関数は複数値の文字列/属性から 1 つの項目を返します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| 属性 | 必須 | 属性 | 検索対象の複数値の属性 |
| **インデックス** | 必須 | 整数 | 複数値の文字列内の項目のインデックス |

**例:**`Item([proxyAddresses], 1)` は、複数値属性の 1 番目の項目を返します。 インデックス 0 は使用しないでください。

#### 参加する

**関数:** Join(separator, source1, source2, …)

**説明:** Join() は Append() によく似ていますが、Join() では複数の **source** 文字列値を 1 つの文字列に結合できます。それぞれの値は **separator** 文字列で区切って指定します。

source 値の 1 つが複数値属性である場合は、その属性のすべての値を結合し、separator 値で区切ります。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **分離器** | 必須 | 糸 | source 値を 1 つの文字列に連結するときに、各値を区切るのに使用する文字列。 区切り記号が必要ない場合は、“” とすることができます。 |
| **ソース 1 … sourceN** | 必須、回数は可変 | 糸 | 結合する文字列値。 |

#### 左

**関数:** Left(String, NumChars)

**説明:** Left 関数は文字列の左端から数えて指定した文字数分の文字を返します。 numChars = 0 の場合、空の文字列を返します。 numChars &lt; 0 の場合、入力文字列を返します。 string が null の場合、空の文字列を返します。 string に含まれる文字数が numChars で指定した数より少ない場合は、string と同一の文字列 (パラメーター 1 のすべての文字が含まれる) が返されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ストリング** | 必須 | 属性 | 返される文字を含む文字列 |
| **NumChars** | 必須 | 整数 | 文字列の先頭 (左端) から取得する文字数を示す値 |

**例:**`Left("John Doe", 3)`

"Joh" を返します。

#### Len

**関数：** Len(String)

**説明：** Len 関数は、入力文字列の長さを返します。 文字列が null または空の場合は、0 を返します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ストリング** | 必須 | 文字列または整数 | 文字列または数値を入力として渡すことができます。 |

**例 1**

`Len("John Doe")`、 8 を返します。`Len(42568)`、 5 を返します。`Len(42568.75)`、 8 を返します。

**例 2** たとえば、次の規則を使用してアカウント エイリアスを生成する必要があるとします。

- 生成されるエイリアスは、10 文字以内にする必要があります。
- 姓の長さが 5 以下の場合は、姓を使用し、残りの文字に名を入力します。
- それ以外の場合、姓の長さが 5 より大きい場合は、姓の最初の 9 文字と名の最初の文字を使用します。

Len 関数を使用すると、次に示すように式マッピングを作成できます。

```
IIF(Len([LastName]) <= 5,  

  Left(Join ("",[LastName],[FirstName]),10),  
  Join("", Mid([LastName],1,9), Mid([FirstName],1,1)) 

)
```

**入力/出力のサンプル**

- **INPUT** (FirstName): "John", (LastName): "Smith", then OUTPUT = "SmithJohn"
- **INPUT** (FirstName): "John", (LastName): "Parker", then OUTPUT = "ParkerJ"

#### 中間

**関数:** Mid(source, start, length)

**説明:** source 値の部分文字列を返します。 部分文字列は、source 文字列の文字のみを含む文字列です。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常、属性の名前。 |
| **を開始** | 必須 | 整数 | 部分文字列が始まる **source** 文字列のインデックス。 文字列内の最初の文字のインデックスは 1、2 番目の文字のインデックスは 2 です (以降同様)。 |
| **長さ** | 必須 | 整数 | 部分文字列の長さ。 length が **source** 文字列の外で終わる場合は、**start** インデックスから **source** 文字列の末尾までの部分文字列を返します。 |

#### NormalizeDiacritics

**関数:** NormalizeDiacritics(source)

**説明:** 1 つの文字列引数が必要です。 文字列を返しますが、分音記号はそれ以外の同等の記号で置換されます。 通常、分音記号 (アクセント記号) を含む姓と名を、さまざまなユーザー識別子 (ユーザー プリンシパル名、SAM アカウント名、電子メール アドレスなど) で使用できる有効な値に変換するために使用します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常は、名または姓の属性です。 |

| 分音記号を含む文字 | 正規化された文字 | 分音記号を含む文字 | 正規化された文字 |
| --- | --- | --- | --- |
| ä, à, â, â, å, á, ą, ă, ā, ā́, ā̀, ā̂, ā̃, ǟ, ā̈, ǡ, a̱, å̄ | ある | Ä,À, Â, Å, Á, Ą, Ă, Ā, Ā, Ā̀, Ā̂, Ā̃, Ǟ, Ā̈, Ǡ, A̱, Å̄ | ある |
| æ、ǣ | ae | Æ、 Ǣ | AE |
| ç、č、ć、c̄、c̱ | c | Ç、Č、Ć、C̄、C̱ | C |
| ď、d̄、ḏ | d | Ď、D̄、Ḏ | D |
| ë, è, é, ê, ę, ě, ê, ē, ḗ, ḕ, ē̂, ē̃, ê̄, e̱, ë̄, e̊̄ | e | Ë、È、É、Ê、Ę、Ê、Ē、Ḗ、Ḕ、Ē̂、Ē̃、Ê̄、E̱、Ë̄、E̊̄ | E |
| ğ, ḡ, g̱ | G | Ğ、Ḡ、G̱ | G |
| ï, î, í, í, ı, ī, ī́, ī̀, ī̂, ī̃, i̱ | 私 | Ï、Î、Í、Í、İ、Ī、Ī、Ī̀、Ī̂、Ī̃、I̱ | I |
| ľ, ł, l̄, ḹ, ḻ | l | Ł、Ľ、L̄、Ḹ、Ḻ | L |
| ☙, ṉ, n̄, ṉ | n | Ń、Ń、Ń、N̄、Ṉ | N |
| ö,ò, õ, ô, ó, ō, ṓ, ṑ, ō̂, ō̃, ȫ, ō̈, ǭ, ȭ, ȱ, o̱ | o | Ö、Ò、Õ、Ô、Ó、Ō、Ō、Ṓ、Ṑ、Ō̂、Ō̃、Ȫ、Ō̈、Ǭ、Ȭ、Ȱ、O̱ | O |
| ø、ø̄、œ̄ | 大江 | Ø、Ø̄、Œ̄ | 大江 |
| ř、r̄、ṟ、ṝ | r | Ř、R̄、Ṟ、Ṝ | R |
| ß | エスエス |  |  |
| š、ś、ș、ş、s̄、s̱ | s | Š、Ś、Ș、Ş、S̄、S̱ | S |
| ť, ț, t̄, ṯ | t | Ť、Ț、T̄、Ṯ | T |
| ü、ù、 û, ú, ů, ű, ū, ū, ̀, ū̂, ū̃, u̇̄, ǖ, ṻ, ṳ̄, u̱ | u | Ü、Ù、 Û, Ú, Ů, Ű, Ū, Ū, Ū̀, Ū̂, Ū̃, U̇̄, Ǖ, Ṻ, Ṳ̄, U̱ | ユー |
| ÿ, ý, ȳ, ȳ́, ȳ̀, ȳ̃, y̱ | ○ | Ÿ, Ý, Ȳ, Ȳ́, Ȳ̀, Ȳ̃, Y̱ | Y |
| ź, ž, ż, z̄, ẕ | z | Ź、Ž、Ž、Z̄、Ẕ | ゼット |

##### 文字列から分音記号を削除する

例: アクセント記号を含む文字を、アクセント記号を含まない同等の文字に置換します。

**表現：** NormalizeDiacritics([givenName])

**サンプル入力/出力:**

- **入力** (givenName):"Zoë"
- **出力**: "Zoe"

#### NormalizeDiacriticsByCulture

**関数:** NormalizeDiacriticsByCulture(source, culture)

**説明:** 2 つの文字列引数が必要です。 **カルチャ**が`"de"`されている場合は、次の表に従って表記変換されたドイツ語の分音記号を含むソース文字列を返します。 `"de"` は、サポートされている唯一のカルチャ値です。 その他のカルチャ値の場合、関数は NormalizeDiacritics と同じ正規化動作を使用します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 正規化する文字列。 |
| **文化** | 必須 | 糸 | 正規化に使用するカルチャ。 サポートされている値は `"de"`のみです。 |

| Character | 表記変換された値 |
| --- | --- |
| ä | ae |
| Ä | Ae |
| ö | 大江 |
| Ö | Oe |
| ü | ue |
| Ü | Ue |
| ß | エスエス |

##### 文字列でドイツ語の分音記号を正規化する

例: `"de"` カルチャを使用してドイツ語の分音記号を表記変換する。

**式:**`NormalizeDiacriticsByCulture("Müller", "de")`

**サンプル入力/出力:**

- **INPUT**: "Müller", "de"
- **出力**: "Mueller"

#### じゃない

**関数:** Not(source)

**説明:** **source** のブール値を反転します。 **source** 値が True の場合は False を返します。 そうでない場合は、True を返します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | ブール値文字列 | 有効な **source** 値は "True" または "False" です。 |

#### 今

**関数:** Now()

**説明:** Now 関数は、**M/d/yyyy h:mm:ss tt** の形式で現在の UTC DateTime を表す文字列を返します。

**例:**`Now()` 値の例は *7/2/2021 3:33:38 PM* を返しました

#### NumFromDate

**関数:** NumFromDate(value)

**Description:** NumFromDate 関数は、DateTime 値を、[accountExpires](https://learn.microsoft.com/ja-jp/windows/win32/adschema/a-accountexpires) などの属性を設定するために必要なActive Directory形式に変換します。 この関数を使用して、Workday や SuccessFactors などのクラウド HR アプリから受信した DateTime 値を、それと等価な AD 表現に変換します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **価値** | 必須 | 糸 | [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) 形式の日付時刻の文字列。 日付変数の形式が異なる場合は、 FormatDateTime 関数を使用して、日付を ISO 8601 形式に変換します。 |

**例:**

- Workday の例: *2020-12-31-08:00* 形式の Workday の *ContractEndDate* 属性を、AD の *accountExpires* フィールドにマップする場合に、この関数を使用して、ロケールに合わせてタイムゾーン オフセットを変更する方法をここで示します。 `NumFromDate(Join("", FormatDateTime([ContractEndDate], ,"yyyy-MM-ddzzz", "yyyy-MM-dd"), " 23:59:59-08:00"))`
- SuccessFactors の例: *M/d/yyyy hh:mm:ss tt* 形式の SuccessFactors の *endDate* 属性を、AD の *accountExpires* フィールドにマップする場合に、この関数を使用して、ロケールに合わせてタイムゾーン オフセットを変更する方法をここで示します。 `NumFromDate(Join("",FormatDateTime([endDate], ,"M/d/yyyy hh:mm:ss tt","yyyy-MM-dd")," 23:59:59-08:00"))`

#### PCase

**関数:** PCase(source, wordSeparators)

**説明:** PCase 関数は、文字列内の各単語の最初の文字を大文字に変換し、その他のすべての文字は小文字に変換されます。

**パラメーター:**

| 名前 | 必須/省略可能 | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 正しい大文字/小文字に変換する **source** 値。 |
| **wordSeparators** | 省略可能 | 糸 | 単語の区切り文字として使用される一連の文字を指定します (" ,-'" など)。 |

**備考:**

- *wordSeparators* パラメーターが指定されていない場合、PCase は内部的に .NET 関数 [ToTitleCase](https://learn.microsoft.com/ja-jp/dotnet/api/system.globalization.textinfo.totitlecase) を呼び出して、*source* 文字列を適切なケースに変換します。 .NET関数 *ToTitleCase* は、単語区切り記号として[Unicode 文字カテゴリ](https://www.unicode.org/reports/tr44/#General_Category_Values)の包括的なセットをサポートします。
    - 空白文字
    - 改行文字
    - CRLF などの "*制御*" 文字
    - "*書式*" 制御文字
    - アンダースコアなどの *ConnectorPunctuation* 文字
    - ダッシュやハイフンなどの *DashPunctuation* 文字 (En ダッシュ、Em ダッシュ、ダブルハイフンなどの文字を含む)
    - かっこ、中かっこ、山かっこなどのペアで現れる *OpenPunctuation* および *ClosePunctuation* 文字。
    - 単一引用符、二重引用符、角引用符などの *InitialQuotePunctuation* および *FinalQuotePunctuation* 文字。
    - 感嘆符、番号記号、パーセント記号、アンパサンド、アスタリスク、コンマ、終止符、コロン、セミコロンなどの *OtherPunctuation* 文字。
    - プラス記号、小なり記号、大なり記号、縦線、チルダ、等号などの *MathSymbol* 文字。
    - ドル記号、セント記号、ポンド記号、ユーロ記号などの *CurrencySymbol* 文字。
    - 長音記号、アクセント、矢印などの *ModifierSymbol* 文字。
    - 著作権記号、度記号、商標記号などの *OtherSymbol* 文字。
- *wordSeparators* パラメーターが指定されている場合、PCase では、指定された文字のみを単語の区切り文字として使用します。

**例:**

たとえば、属性 *firstName* と *lastName* を SAP SuccessFactors から取得しており、人事ではこれらの両方の属性が大文字になっているとします。 PCase 関数を使用すると、次に示すように、名前を正しい大文字/小文字に変換できます。

| 表現 | 入力 | アウトプット | 注記 |
| --- | --- | --- | --- |
| `PCase([firstName])` | *firstName* = "PABLO GONSALVES (SECOND)" | "マラガ・ゴンサルベス (Second)" | *wordSeparators* パラメーターが指定されていないため、*PCase* 関数では、単語の区切り文字の既定の文字セットを使用します。 |
| `PCase([lastName]," '-")` | *lastName* = "PINTO-DE'SILVA" | "Pinto-De'Silva" | *PCase* 関数では、*wordSeparators* パラメーターの文字を使用して単語を識別し、それらを正しい大文字/小文字に変換します。 |
| `PCase(Join(" ",[firstName],[lastName]))` | *firstName* = GREGORY、 *lastName* = "JAMES" | "グレゴリー・ジェームズ" | Join 関数を PCase 内で入れ子にすることができます。 *wordSeparators* パラメーターが指定されていないため、*PCase* 関数では、単語の区切り文字の既定の文字セットを使用します。 |

#### RandomString

**関数:** RandomString(Length, MinimumNumbers, MinimumSpecialCharacters, MinimumCapital, MinimumLowerCase, CharactersToAvoid)

**説明:** RandomString 関数は、指定された条件に基づいてランダムな文字列を生成します。 使用できる文字は、[こちら](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/security-policy-settings/password-must-meet-complexity-requirements#reference)で確認できます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **長さ** | 必須 | 数値 | ランダムな文字列の合計文字数。 MinimumNumbers、MinimumSpecialCharacters、MinimumCapital の合計と同じか、それ以上であることが必要です。 最大 256 文字です。 |
| **MinimumNumbers** | 必須 | 数値 | ランダムな文字列に含まれる数字の最小文字数。 |
| **MinimumSpecialCharacters** | 必須 | 数値 | 特殊文字の最小文字数。 |
| **MinimumCapital** | 必須 | 数値 | ランダムな文字列に含まれる大文字の最小文字数。 |
| **MinimumLowerCase** | 必須 | 数値 | ランダムな文字列に含まれる小文字の最小文字数。 |
| **CharactersToAvoid** | 省略可能 | 糸 | ランダムな文字列を生成するときに除外する文字。 |

**例 1:** - 特殊文字の制限なしでランダムな文字列を生成する: `RandomString(6,3,0,0,3)` 6 文字のランダムな文字列を生成します。 文字列には数字が 3 つと小文字が 3 つ含まれます (1a73qt)。

**例 2:** - 特殊文字の制限付きでランダムな文字列を生成する: `RandomString(10,2,2,2,1,"?,")` 10 文字のランダムな文字列を生成します。 この文字列には、少なくとも数字が 2 つ、特殊文字が 2 つ、大文字が 2 つ、小文字が 1 つ含まれており、"?" 文字と "," 文字は除外されます (1@!2BaRg53)。

#### 編集

**関数:** Redact()

**説明:** Redact 関数は、プロビジョニング ログで、属性値を文字列リテラル "[Redact]" に置き換えます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **attribute/value** | 必須 | 糸 | ログで編集する属性または定数/文字列を指定します。 |

**例 1:** 属性を編集します: `Redact([userPrincipalName])` プロビジョニング ログから userPrincipalName を削除します。

**例 2:** 文字列を編集します: `Redact("StringToBeRedacted")` プロビジョニング ログから定数文字列を削除します。

**例 3:** ランダムな文字列を編集します: `Redact(RandomString(6,3,0,0,3))` プロビジョニング ログからランダムな文字列を削除します。

#### RemoveDuplicates

**関数:** RemoveDuplicates(attribute)

**説明:** RemoveDuplicates 関数は複数値の文字列を受け取り、各値が一意になるように処理します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| 属性 | 必須 | 複数値の属性 | 重複が削除される複数値の属性 |

**例:**`RemoveDuplicates([proxyAddresses])` 重複する値がすべて削除された、校正済みの proxyAddress 属性が返されます。

#### 取り替える

**関数:** Replace(source, oldValue, regexPattern, regexGroupName, replacementValue, replacementAttributeName, template)

**説明:** 大文字と小文字を区別して、文字列内の値を置換します。 この関数は指定されたパラメーターに応じて異なる動作をします。

- **oldValue** と **replacementValue** が指定された場合:

    - **source** に含まれるすべての **oldValue** を **replacementValue** に置換します。
- **oldValue** と **template** が指定された場合:

    - **template** に含まれるすべての **OldValue** を **source** 値に置換します。
- **regexPattern** と **replacementValue** が指定された場合:

    - この関数で **regexPattern** が **source** の文字列に適用され、regex グループ名を使って **replacementValue** の文字列を作成できます。

注

正規表現グループ化コンストラクトと名前付きサブ式の詳細については、「[正規表現でのコンストラクトのグループ化](https://learn.microsoft.com/ja-jp/dotnet/standard/base-types/grouping-constructs-in-regular-expressions)」を参照してください。

- **regexPattern**、**regexGroupName**、**replacementValue** が指定された場合:

    - この関数で **regexPattern** が **source** の文字列に適用され、**regexGroupName** と一致するすべての値が **replacementValue** に置き換えられます
- **regexPattern**、**regexGroupName**、**replacementAttributeName** が指定された場合:

    - **source** に値が指定されている場合は、**source** が返されます。
    - **source** に値がない場合、この関数によって **regexPattern** が **replacementAttributeName** に適用され、**regexGroupName** と一致する値が返されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常は、**source** オブジェクトの属性の名前。 |
| **oldValue** | 省略可能 | 糸 | **source** または **template** に含まれる置換前の値。 |
| **regexPattern** | 省略可能 | 糸 | **source**に含まれる置換前の値の正規表現パターン。 **replacementAttributeName** が使用されている場合、**regexPattern** が適用され、**replacementAttributeName** から値が抽出されます。 |
| **regexGroupName** | 省略可能 | 糸 | **regexPattern**内のグループの名前。 名前付き **replacementAttributeName** が使用されている場合、**replacementAttributeName** から名前付き regex グループの値が抽出され、置換値として返されます。 |
| **replacementValue** | 省略可能 | 糸 | 古い値を置き換える新しい値。 |
| **replacementAttributeName** | 省略可能 | 糸 | 置換値に使用する属性の名前 |
| **テンプレート** | 省略可能 | 糸 | **template** の値を指定した場合、template 内で **oldValue** が検索され、**source** の値で置換されます。 |

##### 正規表現を使用して文字を置換します

**例 1:** **oldValue** と **replacementValue** を使用して、ソース文字列全体を別の文字列に置換します。

たとえば、HR システムに `BusinessTitle` 属性があるとします。 最近の役職の変更の一環として、会社は "製品開発者" という肩書のユーザーを "ソフトウェア エンジニア" に更新したいと考えています。 この場合は、属性マッピングで次の式を使用できます。

`Replace([BusinessTitle],"Product Developer", , , "Software Engineer", , )`

- **source**: `[BusinessTitle]`
- **oldValue**: "Product Developer"
- **replacementValue**: "ソフトウェア エンジニア"
- **式の出力**: Software Engineer

**例 2:** **oldValue** と **template** を使用して、ソース文字列を別の "テンプレート化" された文字列に挿入します。

このシナリオでは、**oldValue** というパラメーターは適切ではありません。 実際には、置き換えられる値です。`<username>@contoso.com` の形式でログイン ID を常に生成するとします。 **UserID** という source 属性があり、その値をログイン ID の `<username>` 部分に使用します。 この場合は、属性マッピングで次の式を使用できます。

`Replace([UserID],"<username>", , , , , "<username>@contoso.com")`

- **source:**`[UserID]` = "jsmith"
- **Oldvalue：** "`<username>`"
- **テンプレート：** "`<username>@contoso.com`"
- **式の出力:** "jsmith@contoso.com"

**例 3:** **regexPattern** と **replacementValue** を使用して、ソース文字列の一部を抽出し、それを空の文字列、または正規表現パターンまたは regex グループ名を使用して構築されたカスタム値に置き換えます。

たとえば、構成要素 `telephoneNumber` と `country code` を持ち、空白文字で区切られたソース属性 `phone number` があるとします。 例: `+91 9998887777` この場合、属性マッピングで次の式を使用して、10 桁の電話番号を抽出できます。

`Replace([telephoneNumber], , "\\+(?<isdCode>\\d* )(?<phoneNumber>\\d{10})", , "${phoneNumber}", , )`

- **source:**`[telephoneNumber]` = "+91 9998887777"
- **regexPattern:** "`\\+(?<isdCode>\\d* )(?<phoneNumber>\\d{10})`"
- **replacementValue:** "`${phoneNumber}`"
- **式の出力:** 9998887777

また、このパターンを使用して、文字を削除したり、文字列を短縮したりできます。 たとえば、次の式は、携帯電話番号文字列のかっこ、ダッシュ、および空白文字を削除し、数字のみを返します。

`Replace([mobile], , "[()\\s-]+", , "", , )`

- **source**: `[mobile] = "+1 (999) 888-7777"`
- **regexPattern:** "`[()\\s-]+`"
- **replacementValue:** "" (空の文字列)
- **式の出力:** 19998887777

**例 4:** **regexPattern**、**regexGroupName**、**replacementValue** を使用して、ソース文字列の一部を抽出し、別のリテラル値または空の文字列に置き換えます。

たとえば、ソース システムに 2 つの構成要素、番地と通りの名前を持つ属性 AddressLineData があるとします。 最近の移転の一部として、住所の番地が変更され、住所の行の番地部分のみを更新するとします。 この場合、属性マッピングで次の式を使用して、番地を抽出できます。

`Replace([AddressLineData], ,"(?<streetNumber>^\\d*)","streetNumber", "888", , )`

- **source:**`[AddressLineData]` = "545 Tremont Street"
- **regexPattern:** "`(?<streetNumber>^\\d*)`"
- **regexGroupName:** "streetNumber"
- **replacementValue:** "888"
- **式の出力:** 888 Tremont Street

UPN のドメイン サフィックスを空の文字列に置き換えて、ドメイン サフィックスなしのログイン ID を生成するもう 1 つの例を次に示します。

`Replace([userPrincipalName], , "(?<Suffix>@(.)*)", "Suffix", "", , )`

- **source:**`[userPrincipalName]` = "jsmith@contoso.com"
- **regexPattern:** "`(?<Suffix>@(.)*)`"
- **regexGroupName:** "サフィックス"
- **replacementValue:** "" (空の文字列)
- **式の出力:** jsmith

**例 5:** **regexPattern**、**regexGroupName**、および **replacementAttributeName** を使用して、ソース属性が空であるか値を持たない場合のシナリオを処理します。

たとえば、ソース システムに属性 telephoneNumber があるとします。 telephoneNumber が空の場合は、携帯電話番号属性の 10 桁を抽出します。 この場合は、属性マッピングで次の式を使用できます。

`Replace([telephoneNumber], , "\\+(?<isdCode>\\d* )(?<phoneNumber>\\d{10})", "phoneNumber" , , [mobile], )`

- **source:**`[telephoneNumber]` = "" (空の文字列)
- **regexPattern:** "`\\+(?<isdCode>\\d* )(?<phoneNumber>\\d{10})`"
- **regexGroupName:** "phoneNumber"
- **replacementAttributeName:**`[mobile]` = "+91 8887779999"
- **式の出力:** 8887779999

**例 6:** 正規表現の値と一致する文字を見つけて削除する必要があります。

`Replace([mailNickname], , "[a-zA-Z_]*", , "", , )`

- **source** [mailNickname]
- **oldValue**: "john\_doe72"
- **replaceValue**: ""
- **式の出力**: 72

#### SelectUniqueValue

**関数:** SelectUniqueValue(uniqueValueRule1, uniqueValueRule2, uniqueValueRule3, …)

**説明:** 少なくとも 2 つの引数が必要です。引数は、式を使用して定義されている一意値生成ルールです。 関数では、各ルールが評価された後、生成された値の対象となるアプリ/ディレクトリでの一意性が確認されます。 最初に見つかった一意の値が返されます。 すべての値がターゲットに既に存在する場合、エントリはエスクローされて、理由が監査ログに記録されます。 渡すことができる引数の数に上限はありません。

- この関数は最上位に配置する必要があり、入れ子にすることはできません。
- この関数は、照合の優先順位を持つ属性には適用できません。
- この関数は、エントリの作成に使用されることだけを目的としたものです。 属性で使用するときは、 **[Apply Mapping](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/マッピングの適用)** プロパティを **[オブジェクトの作成中のみ]** に設定します。
- この関数は、現在、次のアプリケーションでの使用がサポートされています。

    - Workday から Active Directory へのユーザー プロビジョニング
    - SuccessFactors からユーザー プロビジョニングをActive Directoryする
    - オンプレミスの Active Directoryへの API 駆動型プロビジョニング

    SelectUniqueValue は、他のプロビジョニング アプリケーションでの使用はサポートされていません。
- *SelectUniqueValue* 関数がオンプレミスの Active Directoryで実行する LDAP 検索では、分音記号のような特殊文字はエスケープされません。 特殊文字を含む文字列 ("Jéssica Smith" など) を渡した場合、処理エラーが発生します。 この例のように NormalizeDiacritics 関数を入れ子にして、特殊文字を正規化します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **uniqueValueRule1 ... uniqueValueRuleN** | 2 つ以上必要であり、上限はありません | 糸 | 評価する一意値生成ルールの一覧。 |

##### userPrincipalName (UPN) 属性用に一意の値を生成する

例: ユーザーの名、ミドル ネーム、姓を基にして UPN 属性の値を生成し、値を UPN 属性に割り当てる前に、対象の AD ディレクトリで値が一意であることを確認する必要があります。

**式:**

```ad
    SelectUniqueValue( 
        Join("@", NormalizeDiacritics(StripSpaces(Join(".",  [PreferredFirstName], [PreferredLastName]))), "contoso.com"), 
        Join("@", NormalizeDiacritics(StripSpaces(Join(".",  Mid([PreferredFirstName], 1, 1), [PreferredLastName]))), "contoso.com"),
        Join("@", NormalizeDiacritics(StripSpaces(Join(".",  Mid([PreferredFirstName], 1, 2), [PreferredLastName]))), "contoso.com")
    )
```

**サンプル入力/出力:**

- **入力** (PreferredFirstName):"John"
- **入力** (PreferredLastName):"Smith"
- **出力**: John.Smith@contoso.com の UPN 値がディレクトリにまだ存在しない場合は "John.Smith@contoso.com"
- **出力**: J.Smith@contoso.com の UPN 値がディレクトリに既に存在する場合は "John.Smith@contoso.com"
- **出力**: 上記の 2 つの UPN 値がディレクトリに既に存在する場合は "Jo.Smith@contoso.com"

#### SingleAppRoleAssignment

**関数:** SingleAppRoleAssignment(appRoleAssignments)

**説明:** 指定したアプリケーションで 1 人のユーザーに割り当てられた appRoleAssignments の全一覧から、単一の appRoleAssignment を返します。 appRoleAssignments オブジェクトを単一のロール名文字列に変換するために、この関数が必要になります。 ベストプラクティスは、一度に1人のユーザーに1つのappRoleAssignmentしか割り当てられないようにすることです。 この関数は、ユーザーが複数のアプリ ロールの割り当てを持つシナリオではサポートされていません。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **appRoleAssignments** | 必須 | 糸 | **appRoleAssignments** オブジェクト |

#### 割る

**関数:** Split(source, delimiter)

**説明:** 指定された区切り記号文字を使用して、文字列を複数値の配列に分割します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | **source** 値。 |
| **デリミタ** | 必須 | 糸 | 文字列の分割に使用する文字を指定します (例: ",") |

##### 文字列を複数値の配列に分割します

例: コンマ区切りの一覧になっている文字列を受け取り、Salesforce の PermissionSets 属性などの複数値の属性にプラグインできる配列に分割する必要があります。 この例では、Microsoft Entra ID の extensionAttribute5 にアクセス許可セットの一覧が設定されています。

**表現：** Split([extensionAttribute5], ",")

**サンプル入力/出力:**

- **INPUT** (extensionAttribute5):
- **出力**: ["PermissionSetOne", "PermissionSetTwo"]

#### StripSpaces

**関数:** StripSpaces(source)

**説明:** source 文字列からすべての空白文字 (" ") を削除します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | **source** 値。 |

#### スイッチ

**関数:** Switch(source, defaultValue, key1, value1, key2, value2, …)

**説明:** **source** 値が **key** と一致するときに、その **key** の **value** を返します。 **source** 値がどの key とも一致しない場合は、**defaultValue** を返します。 **key** と **value** パラメーターは、常にペアで指定する必要があります。 この関数には、常に、偶数個のパラメーターを指定する必要があります。 この関数は、manager などの参照属性には使用しないでください。

注

Switch 関数は、**source** 値と **key** 値の大文字と小文字を区別する文字列比較を実行します。 大文字と小文字を区別しない比較を実行する場合は、入れ子になった ToLower 関数を使用して、比較する前に **source** 文字列を正規化し、すべての **key** 文字列で小文字を使用します。 例: `Switch(ToLower([statusFlag]), "0", "true", "1", "false", "0")`. この例では、**source** 属性 `statusFlag` の値が ("True" / "true" / "TRUE") になっている場合があります。 ただし、Switch 関数では、**key** パラメーターと比較する前に、常に小文字の文字列 "true" に変換されます。

注意事項

**source** パラメーターでは、入れ子になった関数 IsPresent、IsNull、または IsNullOrEmpty を使用しないでください。 代わりに、キー値の 1 つとしてリテラルの空の文字列を使用します。 例: `Switch([statusFlag], "Default Value", "true", "1", "", "0")`. この例では、**source** 属性 `statusFlag` が空の場合、Switch 関数は値 0 を返します。

注意事項

**問題:** switch ステートメントを使用するときに、プロビジョニング サービスがターゲット システムで属性値を正しく null に設定しません。 **解決策:** switch ステートメントの代わりに IIF ステートメントを使用して、予期しない null 値を回避するか、switch ステートメントで IgnoreFlowIfNullOrEmpty 関数を使用します。 たとえば、switch([companyName], "External", "Company A", "A", "Company B", "B") should be represented as IIF([companyName] = "Company A", "A", IIF([companyName] = "Company B", "B", "External")) として表す必要があります。 既定値が属性の場合は、IgnoreFlowIfNullOrEmpty(switch([companyName], [companyName], "Company A", "A", "Company B", "B")) などの式を使用します。 **Root cause:** この問題は、Microsoft Entra IDがソース システムであり、switch ステートメントに既定値が含まれているシナリオに固有です。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | **Source** 値。 |
| **defaultValue** | 省略可能 | 糸 | source がどの key とも一致しないときに使用される既定値。 空の文字列 ("") を指定できます。 |
| **キー** | 必須 | 糸 | **source** 値と比較する **key**。 |
| **価値** | 必須 | 糸 | key と一致する **source** の置換値。 |

##### 定義済みのオプション セットに基づいて値を置換する

例: Microsoft Entra IDに格納されている状態コードに基づいて、ユーザーのタイム ゾーンを定義します。 都道府県コードが定義済みオプションのいずれにも一致しない場合は、既定値 "Australia/Sydney" を使用します。

**式:**`Switch([state], "Australia/Sydney", "NSW", "Australia/Sydney","QLD", "Australia/Brisbane", "SA", "Australia/Adelaide")`

**サンプル入力/出力:**

- **入力** (state):"QLD"
- **出力**:"Australia/Brisbane"

#### ToLower関数

**関数:** ToLower(source, culture)

**説明:***source* 文字列値を受け取り、指定されたカルチャ ルールを使用して小文字に変換します。 *カルチャ*情報が指定されていない場合は、インバリアント カルチャが使用されます。

ターゲット システムの既存の値を小文字に設定する場合は、[ターゲット アプリケーションのスキーマを更新](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#editing-the-list-of-supported-attributes)し、対象の属性のプロパティ caseExact を "true" に設定します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常は、source オブジェクトの属性の名前。 |
| **文化** | 省略可能 | 糸 | RFC 4646 に基づくカルチャ名の形式は、*languagecode2-country/regioncode2* です。ここで、*languagecode2* は 2 文字の言語コードで、*country/regioncode2* は 2 文字のサブカルチャ コードです。 たとえば、日本語 (日本) の ja-JP や英語 (米国) の en-US などです。 2 文字の言語コードが使用できない場合は、ISO 639-2 から派生した 3 文字のコードが使用されます。 |

##### 生成された userPrincipalName (UPN) の値を小文字に変換します

例: PreferredFirstName および PreferredLastName のソース フィールドを連結して、すべての文字を小文字に変換することで、UPN 値を生成する場合があります。

`ToLower(Join("@", NormalizeDiacritics(StripSpaces(Join(".",  [PreferredFirstName], [PreferredLastName]))), "contoso.com"))`

**サンプル入力/出力:**

- **入力** (PreferredFirstName):"John"
- **入力** (PreferredLastName):"Smith"
- **出力**: "john.smith@contoso.com"

#### ToUpper

**関数:** ToUpper(source, culture)

**説明:***source* 文字列値を受け取り、指定されたカルチャ ルールを使用して大文字に変換します。 *カルチャ*情報が指定されていない場合は、インバリアント カルチャが使用されます。

ターゲット システムの既存の値を大文字に設定する場合は、[ターゲット アプリケーションのスキーマを更新](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#editing-the-list-of-supported-attributes)し、対象の属性のプロパティ caseExact を "true" に設定します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常は、source オブジェクトの属性の名前。 |
| **文化** | 省略可能 | 糸 | RFC 4646 に基づくカルチャ名の形式は、*languagecode2-country/regioncode2* です。ここで、*languagecode2* は 2 文字の言語コードで、*country/regioncode2* は 2 文字のサブカルチャ コードです。 たとえば、日本語 (日本) の ja-JP や英語 (米国) の en-US などです。 2 文字の言語コードが使用できない場合は、ISO 639-2 から派生した 3 文字のコードが使用されます。 |

#### Word

**関数:** Word(String,WordNumber,Delimiters)

**Description:** Word 関数は、使用する区切り記号と返すword数を記述するパラメーターに基づいて、文字列内に含まれるwordを返します。 delimiters 内のいずれかの文字で区切られた string 内の各文字列が、単語として識別されます。

数値 &lt; 1 の場合、空の文字列を返します。 string が null の場合、空の文字列を返します。 string に含まれる単語の数が指定より少ないか、区切り記号文字で識別されるどの単語も string に含まれていない場合は、空の文字列が返されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ストリング** | 必須 | 複数値の属性 | 返される単語を含む文字列 |
| **WordNumber** | 必須 | 整数 | 返すべき単語の番号を指定する数値 |
| **区切り 記号** | 必須 | 糸 | 単語を識別するために使用される区切り記号を表す文字列 |

**例:**`Word("The quick brown fox",3," ")`

"brown" が返されます。

`Word("This,string!has&many separators",3,",!&#")`

"has" が返されます。

### 例

ここでは、式関数の使用例をさらに詳しく説明します。

#### 既知のドメイン名をストリップする

ユーザーの電子メールから既知のドメイン名をストリップして、ユーザー名を取得します。 たとえば、ドメインが "contoso.com" の場合は、次の式を使用することができます。

**式:**`Replace([mail], "@contoso.com", , ,"", ,)`

**サンプル入力/出力:**

- **入力** (mail): "john.doe@contoso.com"
- **出力**: "john.doe"

#### 姓の一部と名の一部を連結することでユーザー エイリアスを生成する

ユーザーの名の最初の 3 文字とユーザーの姓の最初の 5 文字を取得することでユーザー エイリアスを生成します。

**式:**`Append(Mid([givenName], 1, 3), Mid([surname], 1, 5))`

**サンプル入力/出力:**

- **入力** (givenName):"John"
- **入力** (surname):"Doe"
- **出力**: "JohDoe"

#### 姓と名の間にコンマを追加します。

姓と名の間にコンマを追加します。

**式:**`Join(", ", "", [surname], [givenName])`

**サンプル入力/出力:**

- **入力** (givenName):"John"
- **入力** (surname):"Doe"
- **出力**: "Doe, John"

#### Microsoft Entra オブジェクト ID に基づいてユーザーの ID を生成します。 ID から任意の文字を削除し、先頭に 1000 を追加します。

この式を使用すると、1000 で始まり、一意である可能性が高いユーザーの識別子を生成できます。

**式:** Join("", 1000, Replace(ConvertToUTF8Hex([objectId]), , "[a-zA-Z\_]\*", , "", , ))

**サンプル入力/出力:**

- **入力**: "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
- **出力**: "100064303565343762312333930392343435612626135652636136306362633065346234"

#### プライマリおよびエイリアスの電子メール アドレスを使用して proxyAddresses を構築する

`proxyAddresses`属性は、ユーザーの電子メール アドレスを保持する複数値フィールドです。 大文字と小文字を区別するプレフィックスを使用して、プライマリ メール アドレスとエイリアスを区別します。

- `SMTP:` (大文字) は **、プライマリ** メール アドレスを指定します。 各ユーザーは 1 つだけです。
- `smtp:` (小文字) **エイリアス** (セカンダリ) メール アドレスを指定します。 ユーザーは複数のユーザーを持つことができます。

たとえば、ユーザーの `proxyAddresses` には次のものが含まれる場合があります。 `["SMTP:john.doe@contoso.com", "smtp:jdoe@contoso.com", "smtp:john.doe@contoso.onmicrosoft.com"]`

式マッピングで proxyAddresses 値を作成するには、 `Append()` を使用して SMTP:/smtp: プレフィックスを追加し、 `Join()` してパーツからメール アドレスを作成し、結果を複数値の配列に変換 `Split()` します。

##### proxyAddresses でプライマリ SMTP アドレスを設定する

この式を使用して、ユーザーの名と姓から 1 つのプライマリ SMTP アドレスを設定します。

**式:**

```
Append("SMTP:", Join("@", Join(".", [givenName], [surname]), "contoso.com"))
```

**サンプル入力/出力:**

- **入力** (givenName):"John"
- **入力** (surname):"Doe"
- **出力**: "SMTP:John.Doe@contoso.com"

##### proxyAddresses でプライマリ アドレスとエイリアス アドレスを設定する

`Split(Join(",", …), ",")` パターンを使用して、複数の proxyAddresses 値を作成します。 `Join()`関数は、アドレスをコンマ区切りの文字列に結合し、その文字列を複数値の配列に変換`Split()`。

**式:**

```
Split(
    Join(",",
        Append("smtp:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [givenName], [surname]))), "contoso.onmicrosoft.com")),
        Append("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [givenName], [surname]))), "contoso.com"))
    ),
    ","
)
```

**サンプル入力/出力:**

- **入力** (givenName):"John"
- **入力** (surname):"Doe"
- **OUTPUT**: ["smtp:John.Doe@contoso.onmicrosoft.com", "SMTP:John.Doe@contoso.com"]

注

プレフィックスの大文字と小文字は重要です。 1 つのプライマリ アドレスに `SMTP:` (大文字) を使用し、すべてのエイリアス アドレスに `smtp:` (小文字) を使用します。 間違ったケースを使用すると、Exchange Onlineやその他のメールが有効なサービスで予期しない動作が発生する可能性があります。

部門、会社、または部門に基づく条件付きロジックを使用したより複雑な proxyAddresses シナリオについては、次を参照してください。

- [SuccessFactors 式マッピング関数 - ProxyAddresses 構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/successfactors-expression-mapping-functions-guide#proxyaddresses-configuration)
- [Workday 式マッピング関数 — ProxyAddresses 構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-expression-mapping-functions-guide#proxyaddresses-configuration)
- proxyAddresses 属性が Microsoft Entra ID
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/how-provisioning-works"} -->
## Microsoft Entra ID での Microsoft Entra ID でのアプリケーション プロビジョニングのしくみを理解する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra ID での Microsoft Entra ID でのアプリケーション プロビジョニングのしくみについて説明します。

自動プロビジョニングとは、ユーザーがアクセスする必要のあるクラウド アプリケーションのユーザー ID とロールを作成することです。 自動プロビジョニングには、ユーザー ID の作成に加えて、状態または役割が変化したときのユーザー ID のメンテナンスおよび削除が含まれます。 デプロイを開始する前に、この記事を参照して Microsoft Entra プロビジョニングのしくみを学習し、構成に関するレコメンデーションを確認することができます。

**Microsoft Entra プロビジョニング サービス**では、アプリケーション ベンダーまたはオンプレミスのプロビジョニング エージェントから提供される System for Cross-Domain Identity Management (SCIM) 2.0 ユーザー管理 API エンドポイントに接続することによって、SaaS アプリや他のシステムにユーザーがプロビジョニングされます。 Microsoft Entra ID では、この SCIM エンドポイントを使用して、プログラムによってユーザーが作成、更新、削除されます。 一部の限られたアプリケーションについては、ID に関連したその他のオブジェクト (グループなど) をプロビジョニング サービスで作成、更新、削除することもできます。 Microsoft Entra ID とアプリケーションの間のプロビジョニングに使用されるチャネルは、HTTPS TLS 1.2 暗号化を使用して暗号化されます。

[Image: Microsoft Entra プロビジョニング サービス]*図 1: Microsoft Entra プロビジョニング サービス*

[Image: 出力方向のユーザー プロビジョニング ワークフロー]*図 2: Microsoft Entra ID から主要 SaaS アプリケーションへの "出力方向" のユーザー プロビジョニング ワークフロー*

[Image: 入力方向のユーザー プロビジョニング ワークフロー]*図 3: 主要な人材管理 (HCM) アプリケーションから Microsoft Entra ID および Windows Server Active Directory への "入力方向" のユーザー プロビジョニング ワークフロー*

### SCIM 2.0 を使用したプロビジョニング

Microsoft Entra プロビジョニング サービスでは、自動プロビジョニングに [SCIM 2.0 プロトコル](https://techcommunity.microsoft.com/t5/security-compliance-and-identity/provisioning-with-scim-getting-started/ba-p/880010)が使用されます。 このサービスは、アプリケーションの SCIM エンドポイントに接続され、SCIM ユーザー オブジェクト スキーマおよび REST API を使用してユーザーとグループのプロビジョニングとプロビジョニング解除を自動化します。 Microsoft Entra ギャラリーでは、ほとんどのアプリケーションに対して SCIM ベースのプロビジョニング コネクタが用意されています。 開発者は、Microsoft Entra ID で SCIM 2.0 ユーザー管理 API を使用して、プロビジョニング サービスに統合するアプリのためのエンドポイントを構築します。 詳細については、[SCIM エンドポイントの構築とプロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)に関するページを参照してください。 また、オンプレミス プロビジョニング エージェントは、Microsoft Entra SCIM の操作を、[LDAP](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)、[SQL](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/tutorial-ecma-sql-connector)、[REST または SOAP](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector)、[PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-powershell-connector)、[カスタム ECMA コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector)の呼び出し、または[パートナーによって構築されたコネクタとゲートウェイ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/partner-driven-integrations)に変換します。

自動 Microsoft Entra プロビジョニング コネクタが現在ないアプリに対してこのコネクタを要求するには、[Microsoft Entra アプリケーション要求](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)に関するページを参照してください。

### 承認

Microsoft Entra をアプリケーションのユーザー管理 API に接続するには、資格情報が必要です。 アプリケーションの自動ユーザー プロビジョニングを構成している間に、有効な資格情報を入力する必要があります。 ギャラリー アプリケーションの場合、アプリケーションの資格情報の種類と要件については、アプリのチュートリアルを参照してください。 ギャラリー以外のアプリケーションの場合は、[SCIM](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#authorization-to-provisioning-connectors-in-the-application-gallery) のドキュメントを参照して、資格情報の種類と要件を理解することができます。 Microsoft Entra 管理センターで、資格情報をテストできます (Microsoft Entra ID により、指定された資格情報を使用してアプリのプロビジョニング アプリへの接続が試行されます)。

### 属性のマッピング

サード パーティの SaaS アプリケーションでユーザー プロビジョニングを有効にすると、Microsoft Entra 管理センターでは属性マッピングによってその属性値が管理されます。 マッピングにより、ユーザー アカウントをプロビジョニングまたは更新するときに Microsoft Entra ID とターゲット アプリケーションの間でフローするユーザー属性が決定されます。

Microsoft Entra ユーザー オブジェクトと各 SaaS アプリのユーザー オブジェクトの間には、事前構成済みの一連の属性と属性マッピングが存在します。 アプリによっては、ユーザーに加えてグループなど他の種類のオブジェクトを管理するものもあります。

プロビジョニングを設定するときは、どのユーザー (またはグループ) プロパティが Microsoft Entra ID からアプリケーションに提供されるかを定義する属性マッピングとワークフローを確認して構成することが重要です。 2 つのシステム間でユーザーまたはグループを一意に識別して照合するために使用される照合プロパティ (**この属性を使用してオブジェクトを照合します**) を確認して構成します。

既定の属性マッピングをビジネスのニーズに合わせてカスタマイズできます。 そのため、既存の属性マッピングを変更、削除したり、新規の属性マッピングを作成したりすることができます。 詳細については、[SaaS アプリケーションに対するユーザー プロビジョニング属性マッピングのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)に関するページを参照してください。

SaaS アプリケーションに対してプロビジョニングを構成するときに指定できる属性マッピングの種類の 1 つは、式マッピングです。 これらのマッピングでは、ユーザーのデータを SaaS アプリケーションが許容可能な形式に変換することができる、スクリプトのような式を記述する必要があります。 詳細については、[属性マッピングの式の書き方](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)に関するページを参照してください。

### Scoping

#### 割り当てベースのスコープ

Microsoft Entra ID から SaaS アプリケーションへの送信プロビジョニングでは、[ユーザーまたはグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に依存することが、プロビジョニングの対象となるユーザーを特定する最も一般的な方法です。 ユーザー割り当てはシングル サインオンの有効化にも使用されるため、アクセスとプロビジョニングの両方を管理するのに同じ方法を使用できます。 割り当てベースのスコープは、Workday や Successfactors などの受信プロビジョニング シナリオには適用されません。

- **グループ。** Microsoft Entra ID P1 または P2 ライセンス プランを使用すると、グループを使って SaaS アプリケーションにアクセスを割り当てることができます。 プロビジョニング スコープが、**ロール割り当て済みユーザーおよびグループのみを同期する**ように設定されている場合、Microsoft Entra プロビジョニング サービスでは、ユーザーがアプリケーションに割り当てられているグループのメンバーであるかどうかに基づいて、ユーザーがプロビジョニングまたはプロビジョニング解除されます。 アプリケーションでグループ オブジェクトがサポートされていない場合、グループ オブジェクト自体はプロビジョニングされません。 アプリケーションに割り当てられているグループで、プロパティ "SecurityEnabled" が "True" に設定されていることを確認します。
- **動的グループ。** Microsoft Entra ユーザー プロビジョニング サービスでは、[動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)のユーザーの読み取りとプロビジョニングを行うことができます。 次の注意事項と推奨事項に留意してください。

    - 動的グループは、Microsoft Entra ID から SaaS アプリケーションへのエンドツーエンドのプロビジョニングのパフォーマンスに影響を与えることがあります。
    - SaaS アプリケーションで動的グループのユーザーをプロビジョニングまたはプロビジョニング解除する速度は、動的グループがメンバーシップの変更を評価する速さによって決まります。 動的グループの処理状態を確認する方法については、[メンバーシップ ルールの処理状態のチェック](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)に関するページを参照してください。
    - 動的グループのユーザーのメンバーシップが失われると、プロビジョニング解除イベントと見なされます。 動的メンバーシップ グループのルールを作成する場合は、このシナリオを検討してください。
- **入れ子になったグループ。** Microsoft Entra ユーザー プロビジョニング サービスでは、入れ子になったグループのユーザーの読み取りやプロビジョニングを行うことはできません。 このサービスでは、明示的に割り当てられたグループの直接のメンバーであるユーザーだけを読み取ってプロビジョニングすることができます。 "アプリケーションへのグループベースの割り当て" のこの制限は、シングル サインオンにも影響を与えます (「[SaaS アプリケーションへのアクセスをグループで管理する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-saasapps)」を参照)。 代わりに、プロビジョニングする必要のあるユーザーを含むグループを明示的に割り当てるか、または[スコープ設定](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)します。

#### 属性ベースのスコープ

スコープ フィルターを使用して、アプリケーションにプロビジョニングするユーザーを決める、属性ベースのルールを定義できます。 この方法は、一般的に、HCM アプリケーションから Microsoft Entra ID および Active Directory への受信プロビジョニングに使用されます。 スコープ フィルターは各 Microsoft Entra ユーザー プロビジョニング コネクタの属性マッピングで設定されます。 属性ベースのスコープ フィルターの構成の詳細については、「[スコープ フィルターを使用した属性ベースのアプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)」を参照してください。

#### B2B (ゲスト) ユーザー

Microsoft Entra ユーザー プロビジョニング サービスを使って、Microsoft Entra ID の B2B (ゲスト) ユーザーを SaaS アプリケーションにプロビジョニングすることは可能です。 ただし、B2B ユーザーが Microsoft Entra ID を使用して SaaS アプリケーションにサインインするには、Microsoft Entra ID を Security Assertion Markup Language (SAML) ID プロバイダーとして使用するように SaaS アプリケーションを手動で構成する必要があります。

B2B (ゲスト) ユーザー用に SaaS アプリを構成する場合は、こちらの一般的なガイドラインに従います。

- ほとんどのアプリでは、ユーザー セットアップを手動で起動する必要があります。 アプリでユーザーも手動で作成する必要があります。
- Dropbox のように、自動セットアップをサポートしているアプリでは、アプリから個別の招待が作成されます。 ユーザーは各招待に確実に応じる必要があります。
- ゲスト ユーザーの壊れたユーザー プロファイル ディスク (UPD) の問題を緩和するには、常にユーザー属性のユーザー識別子を **user.mail** に設定します。

注

[B2B Collaboration ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)の userPrincipalName は、外部ユーザーの電子メール アドレス alias@theirdomain を "alias\_theirdomain#EXT#@yourdomain" として表します。 userPrincipalName 属性がソース属性として属性マッピングに含まれていて、B2B ユーザーがプロビジョニングされている場合、#EXT# とドメインは userPrincipalName から削除されるため、元の alias@theirdomain のみが照合またはプロビジョニングに使用されます。 #EXT# とドメインを含む完全なユーザー プリンシパル名が存在する必要がある場合は、userPrincipalName をソース属性として originalUserPrincipalName に置き換えます。  userPrincipalName = alias@theirdomain originalUserPrincipalName = alias\_theirdomain#EXT#@yourdomain

### プロビジョニング サイクル:初回と増分

Microsoft Entra ID がソース システムである場合、プロビジョニング サービスでは、[Microsoft Graph データの変更を追跡するためにデルタ クエリ](https://learn.microsoft.com/ja-jp/graph/delta-query-overview)を使用して、ユーザーとグループを監視します。 プロビジョニング サービスは、ソース システムとターゲット システムに対して初回サイクルを実行した後、増分サイクルを定期的に行います。

#### 初回サイクル

プロビジョニング サービスが開始されたときの初回サイクルでは、次の処理が実行されます。

1. ソース システムのすべてのユーザーとグループにクエリを実行し、[属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)で定義されているすべての属性を取得します。
2. 返されたユーザーおよびグループを、構成済み[割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)または[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用してフィルター処理します。
3. ユーザーが割り当て済み、またはプロビジョニング対象の場合、サービスはターゲット システムに対してクエリを実行し、指定された[照合属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#understanding-attribute-mapping-properties)を使用して、一致するユーザーがいないかどうかを確認します。 例:ソース システムの userPrincipal 名が一致属性であり、ターゲット システムの userName にマップされる場合、プロビジョニング サービスは、ターゲット システムにクエリを実行し、ソース システムの userPrincipal 名の値と一致する userName がないかどうかを確認します。
4. 一致するユーザーがターゲット システムで見つからない場合、ソース システムから返された属性を使用してユーザーが作成されます。 ユーザー アカウントが作成されると、プロビジョニング サービスでは、その新しいユーザー用のターゲット システムの ID が検出されてキャッシュされます。 この ID は、そのユーザーに対する今後のすべての操作を実行するために使用されます。
5. 一致するユーザーが見つかった場合、そのユーザーは、ソース システムによって提供された属性を使用して更新されます。 ユーザー アカウントが照合されると、プロビジョニング サービスでは、その新しいユーザー用のターゲット システムの ID が検出されてキャッシュされます。 この ID は、そのユーザーに対する今後のすべての操作を実行するために使用されます。
6. 属性マッピングに 「参照」 属性が含まれている場合、サービスは、ターゲット システムで追加の更新を実行して参照先オブジェクトを作成し、リンクします。 たとえば、あるユーザーがターゲット システムで "Manager" 属性を持ち、それがターゲット システムで作成された別のユーザーにリンクされている場合があります。
7. 初回サイクルの終了時に基準値を保持します。これは、以降の増分サイクルの始点になります。

ServiceNow、G Suite、Box など、アプリケーションの中には、ユーザーのプロビジョニングだけでなく、グループとそのメンバーのプロビジョニングをサポートしているものがあります。 このような場合、グループのプロビジョニングが[マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)で有効になっていると、プロビジョニング サービスは、ユーザーとグループを同期したうえで、動的メンバーシップ グループを同期します。

#### 増分サイクル

初回サイクルの後、他のすべてのサイクルでは以下が行われます。

1. ソース システムにクエリを実行し、前回の基準値が保存された後に更新されたユーザーおよびグループがないかどうかを確認します。
2. 返されたユーザーおよびグループを、構成済み[割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)または[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用してフィルター処理します。
3. ユーザーが割り当て済み、またはプロビジョニング対象の場合、サービスはターゲット システムに対してクエリを実行し、指定された[照合属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#understanding-attribute-mapping-properties)を使用して、一致するユーザーがいないかどうかを確認します。
4. 一致するユーザーがターゲット システムで見つからない場合、ソース システムから返された属性を使用してユーザーが作成されます。 ユーザー アカウントが作成されると、プロビジョニング サービスでは、その新しいユーザー用のターゲット システムの ID が検出されてキャッシュされます。 この ID は、そのユーザーに対する今後のすべての操作を実行するために使用されます。
5. 一致するユーザーが見つかった場合、そのユーザーは、ソース システムによって提供された属性を使用して更新されます。 照合されたのが新しく割り当てられたアカウントである場合、プロビジョニング サービスでは、その新しいユーザー用のターゲット システムの ID が検出されてキャッシュされます。 この ID は、そのユーザーに対する今後のすべての操作を実行するために使用されます。
6. 属性マッピングに 「参照」 属性が含まれている場合、サービスは、ターゲット システムで追加の更新を実行して参照先オブジェクトを作成し、リンクします。 たとえば、あるユーザーがターゲット システムで "Manager" 属性を持ち、それがターゲット システムで作成された別のユーザーにリンクされている場合があります。
7. 以前プロビジョニングの対象であったユーザーが対象から外れた場合 (割り当てられていない場合を含む)、このサービスでは、更新を通じてターゲット システムでこのユーザーが無効化されます。
8. 以前プロビジョニングの対象だったユーザーが、ソース システムで無効にされた場合、または論理削除された場合、サービスは、更新によってターゲット システムのユーザーを無効にします。
9. 以前プロビジョニングの対象だったユーザーが、ソース システムから物理的に削除された場合、サービスは、ターゲット システムのユーザーを削除します。 Microsoft Entra ID では、論理削除されたユーザーは、削除されてから 30 日後に物理的に削除されます。
10. 増分サイクルの終了時に新しい基準値を保持します。これは、以降の増分サイクルの始点になります。

注

**[マッピング]** セクションの **[対象オブジェクトのアクション]** チェック ボックスを使用して、 **[作成]** 、 **[更新]** 、または [\[削除\]](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes) 操作を必要に応じて無効にできます。 更新中にユーザーを無効にするロジックは、*accountEnabled* などのフィールドの属性マッピングでも制御されます。

プロビジョニング サービスでは、バックツーバック増分サイクルが、[各アプリケーションに固有のチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)で定義された間隔で無期限に実行され続けます。 増分サイクルは、次のいずれかのイベントが発生するまで続行されます:

- Microsoft Entra 管理センターまたは適切な Microsoft Graph API コマンドを使用してサービスが手動で停止された。
- Microsoft Entra 管理センターの **[プロビジョニングを再起動]** オプション、または適切な Microsoft Graph API コマンドを使用して、新しい初回サイクルがトリガーされます。 このアクションでは、保存されたウォーターマークがクリアされ、すべてのソース オブジェクトが再評価されます。 また、アクションはソース オブジェクトとターゲット オブジェクト間のリンクを中断しません。 リンクを解除するには、次の要求で[同期ジョブの再起動](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-restart?view=graph-rest-beta&tabs=http&preserve-view=true)を使用します:

```http
POST https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/jobs/{jobId}/restart
Authorization: Bearer <token>
Content-type: application/json
{
   "criteria": {
       "resetScope": "Full"
   }
}
```

- 属性マッピングまたはスコープ フィルターの変更によって、新しい初回サイクルがトリガーされた。 また、この操作では、保存された基準値がクリアされ、すべてのソース オブジェクトが再評価されます。
- 高いエラー率によりプロビジョニング プロセスが検査に移行し (以下を参照)、そのまま 4 週間を超えて検査にとどまっている。 この場合、サービスは自動的に無効になります。

#### エラーと再試行

ターゲット システムでのエラーが原因で個々のユーザーの追加、更新、または削除がターゲットシステム上で行えない場合、その操作は次の同期サイクルで再試行されます。 エラーは継続的に再試行され、再試行頻度は徐々に減らされます。 エラーを解決するには、管理者が[プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context)を確認し、根本原因を特定して、適切な対応を取る必要があります。 たとえば、次のような一般的なエラーがあります。

- ターゲット システムで必要な属性がソース システムのユーザーに設定されていない
- ソース システムのユーザーの属性値に対して、ターゲット システムに一意の制約があり、同じ値が他のユーザー レコードに存在する

このようなエラーを解決するには、ソース システムで影響を受けるユーザーの属性値を調整するか、競合が発生しないように属性マッピングを調整します。

#### 検疫する

ターゲット システムに対する呼び出しのほとんどまたはすべてが、常にエラー (管理者の資格情報が無効である場合など) が原因で失敗する場合、プロビジョニング ジョブは "検疫" 状態になります。 この状態は、[プロビジョニング概要レポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)、または電子メール (電子メール通知が Microsoft Entra 管理センターで構成されている場合) で示されます。

検疫状態であると、増分サイクルの頻度は徐々に減少し、最終的には 1 日 1 回になります。

問題を引き起こしているすべてのエラーが修正されたら、プロビジョニング ジョブは検疫から削除され、次の同期サイクルが開始されます。 プロビジョニング ジョブが検疫にとどまっている期間が 4 週間を超えると、そのプロビジョニング ジョブは無効になります。 検疫の状態の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)を参照してください。

#### プロビジョニングにかかる時間

プロビジョニング ジョブで実行されているのが初期プロビジョニング サイクルか増分サイクルかによって、パフォーマンスが異なります。 プロビジョニングにかかる時間、およびプロビジョニング サービスの状態を監視する方法について詳しくは、[ユーザー プロビジョニングの状態の確認](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)に関するページをご覧ください。

#### ユーザーが正しくプロビジョニングされているかどうかを確認する方法

ユーザー プロビジョニング サービスによって実行された操作はすべて、Microsoft Entra の[プロビジョニング ログ (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context) に記録されます。 このログには、ソース システムとターゲット システムに対して実行されたすべての読み取りおよび書き込み操作と、各操作の際に読み取られたり書き込まれたりしたユーザー データが含まれます。 Microsoft Entra 管理センターでプロビジョニング ログを確認する方法の詳細については、[プロビジョニング レポートに関するガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### Deprovisioning

Microsoft Entra プロビジョニング サービスでは、ユーザー アクセスが削除された場合に、アカウントをプロビジョニング解除することによってソースおよびターゲット システムの同期が維持されます。

ユーザーの削除と無効化 (論理的な削除とも呼ばれます) の両方が、このプロビジョニング サービスによってサポートされています。 無効化と削除の厳密な定義は、ターゲット アプリの実装によって異なりますが、一般に、無効化とはユーザーがサインインできないことを示します。 削除とは、ユーザーがアプリケーションから完全に削除されたことを示します。 SCIM アプリケーションの場合、無効化は、ユーザーに対して *active* プロパティを false に設定する要求です。

**ユーザーを無効にするようにアプリケーションを構成する**

更新のチェックボックスが選択されていることを確認します。

ご利用のアプリケーション用の *active* のマッピングを確保します。 アプリケーション ギャラリーからのアプリケーションを使用している場合は、マッピングが若干異なることがあります。 このケースでは、ギャラリー アプリケーションのデフォルトマッピングを使用します。

[Image: ユーザーを無効にします]

**ユーザーを削除するようにアプリケーションを構成する**

次のシナリオでは、無効化または削除がトリガーされます。

- Microsoft Entra ID でユーザーが論理的に削除される (ごみ箱に送られる / AccountEnabled プロパティが false に設定される)。 Microsoft Entra ID でユーザーが削除されてから 30 日後に、テナントから完全に削除されます。 この時点で、プロビジョニング サービスによって削除要求が送信されて、アプリケーション内のユーザーが完全に削除されます。 30 日間の期間中はいつでも、ユーザーを[手動で完全に削除する](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore)ことができます。これにより、削除要求がアプリケーションに送信されます。
- ユーザーを Microsoft Entra ID のごみ箱から完全に削除または除去する。
- ユーザーをアプリから割り当て解除する。
- ユーザーをスコープ内からスコープ外に移動する (スコープ フィルターを通過しなくなります)。

[Image: ユーザーの削除]

既定では、Microsoft Entra プロビジョニング サービスによって、スコープ外になったユーザーが論理的に削除されるか、無効にされます。 この既定の動作をオーバーライドする場合は、[スコープ外の削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)ようにフラグを設定します。

4 つのイベントのいずれかが発生し、ターゲット アプリケーションで論理的な削除がサポートされていない場合は、プロビジョニング サービスによって削除要求が送信されて、アプリからユーザーが完全に削除されます。

属性マッピングに `IsSoftDeleted` がある場合は、これが使用されて、ユーザーの状態と、`active = false` を指定した更新要求を送信してユーザーを論理的に削除するかどうかが判別されます。

**プロビジョニング解除のイベント**

この表に、Microsoft Entra プロビジョニング サービスを使用してプロビジョニング解除アクションを構成する方法が説明されています。 これらの規則は、ギャラリー以外またはカスタムのアプリケーションを考慮して記述されていますが、一般的にはギャラリー内のアプリケーションに適用されます。 ただし、ギャラリー アプリケーションのビヘイビアーは、アプリケーションのニーズに合わせて最適化されているため、異なる場合があります。 たとえば、ターゲット アプリケーションで論理的な削除がサポートされていない場合、Microsoft Entra プロビジョニング サービスは、論理的な削除を送信するのではなく、物理的な削除の要求を送信してユーザーを削除する可能性があります。

| シナリオ | Microsoft Entra ID を構成する方法 |
| --- | --- |
| ユーザーがアプリから割り当て解除されたか、Microsoft Entra ID で論理的に削除されたか、またはサインインがブロックされた。 何も実行されないようにする。 | 属性マッピングから `isSoftDeleted` を削除するか、または[スコープ外の削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)プロパティを true に設定するか、あるいはその両方を行います。 |
| ユーザーがアプリから割り当て解除されたか、Microsoft Entra ID で論理的に削除されたか、またはサインインがブロックされた。 特定の属性を `true` または `false` に設定する必要がある。 | false に設定する必要がある属性に `isSoftDeleted` をマップします。 |
| ユーザーは、Microsoft Entra ID で無効化されているか、アプリから割り当て解除されているか、Microsoft Entra ID でソフト削除されているか、サインインがブロックされています。 ターゲット アプリケーションに削除要求を送信する必要がある。 | これは現在、この機能を必要とする限定されたギャラリー アプリケーションのセットでサポートされています。 顧客がこれを構成することはできません。 |
| ユーザーは Microsoft Entra ID で削除されます。 ターゲット アプリケーションで何も実行されないようにする。 | [属性構成エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)で、ターゲット オブジェクトのアクションの 1 つとして "削除" が選択されていないことを確認します。 |
| ユーザーは Microsoft Entra ID で削除されます。 ターゲット アプリケーションで属性の値を設定する必要がある。 | サポートされていません。 |
| ユーザーは Microsoft Entra ID で削除されます。 ターゲット アプリケーションでユーザーを削除する必要がある | [属性構成エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)で、対象オブジェクトのアクションの 1 つとして [削除] が選択されていることを確認します。 |

**既知の制限**

- ユーザーまたはグループがアプリから割り当て解除され、プロビジョニング サービスで管理されなくなると、無効化要求が送信されます。 その時点で、ユーザーはサービスによって管理されておらず、ユーザーがディレクトリから論理的に削除されたときに削除要求は送信されません。
- Microsoft Entra ID 内で無効になっているユーザーのプロビジョニングはサポートされていません。 プロビジョニングされるには、事前に Microsoft Entra ID 内でアクティブになっている必要があります。
- ユーザーが論理的に削除された状態からアクティブに移行すると、Microsoft Entra プロビジョニング サービスはターゲット アプリでユーザーをアクティブ化しますが、動的メンバーシップ グループは自動的に復元されません。 ターゲット アプリケーションは、ユーザーの動的メンバーシップ グループを非アクティブな状態に維持する必要があります。 ターゲット アプリケーションが非アクティブ状態の維持をサポートしていない場合は、プロビジョニングを再起動して動的メンバーシップ グループを更新できます。

**推奨**

アプリケーションを開発する場合は、論理的な削除と物理的な削除の両方を常にサポートしてください。 そうすることで、顧客は、ユーザーが誤って無効にされた場合でも回復することができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/how-to-account-discovery"} -->
## アカウント検出を使用してターゲット アプリケーションの ID を検出する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery
- Service: entra-id / app-provisioning
- Article date: 2026-08-11
- Summary: アカウント検出を使用して、ターゲット アプリケーション内の既存のユーザーとグループを検索して分類し、それらをMicrosoft Entra ID ID と照合し、プロビジョニング ガバナンスの準備を行う方法について説明します。

組織がアプリケーション プロビジョニングにMicrosoft Entra IDを採用する場合、多くの場合、ターゲット アプリケーションには、プロビジョニングが構成される前に作成されたユーザーとグループが既に含まれています。 アカウント検出では、**Id の検出**エクスペリエンスを使用して、ターゲット アプリケーションからユーザーとグループを取得し、それらをMicrosoft Entra IDの対応するユーザーとグループと照合し、結果を分類して、管理されていない ID をガバナンス下に置くことができます。 プロビジョニングにオンボードした後、アプリケーション管理者はアプリケーションでアカウントを手動で作成できます。 検出レポートは、初期導入時および継続的なプロビジョニング処理時に、ローカル ID や孤立 ID を識別するのに役立ちます。

アカウント検出では、ターゲット アプリケーションからユーザーとグループが取得され、3 つのカテゴリに分類されます。

- **ローカル ID** — Microsoft Entra IDに対応するユーザーまたはグループがないターゲット アプリケーション内のユーザーまたはグループ。 これらの ID は、元の従業員、サービス アカウント、別のプロセスを通じてプロビジョニングされたユーザー、ターゲット アプリケーションで直接作成されたグループ、またはデータ品質の問題 (不一致や古い属性値など) によって一致しなかった ID に属している可能性があります。
- **未割り当て ID** — Microsoft Entra ID内のユーザーまたはグループに一致するが、エンタープライズ アプリケーションに割り当てられていないユーザーまたはグループ。 これらの ID はディレクトリに存在しますが、それらを管理するためにプロビジョニングに必要なアプリケーション割り当てがありません。
- **割り当てられた ID** — Microsoft Entra IDのユーザーまたはグループに一致し、エンタープライズ アプリケーションに割り当てられているユーザーまたはグループ。 これらの ID は、プロビジョニング サービスによって完全に管理されます。

この分類により、アプリケーションにアクセスできるユーザーを把握でき、管理、再割り当て、または削除する必要がある ID を識別するのに役立ちます。

Important

グループ検出機能は現在プレビュー段階にあります。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### 前提条件

アカウント検出を使用する前に、次の手順を実行する必要があります。

- [Microsoft Entra ID ガバナンス](https://www.microsoft.com/security/business/identity-access/microsoft-entra-id-governance) アドオン ライセンスまたは [Microsoft Entra スイート](https://www.microsoft.com/security/business/microsoft-entra-pricing)。 ライセンス別の機能の可用性の詳細については、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals#features-by-license)を参照してください。
- 有効な資格情報とテスト接続が成功したプロビジョニング用に構成されたエンタープライズ アプリケーション。
- Microsoft Entra IDとターゲット アプリケーションの間で構成された**直接一致する属性マッピング**。 アカウント検出では、最初に一致する属性を使用して、2 つのシステム間でユーザーとグループを関連付けます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)。

### 既知の制限

- アカウントの検出には、関連付けに **直接一致する属性** が必要です。 式ベースの変換は、照合ではサポートされていません。
- 複数の一致する属性が構成されている場合は、 **最初** に一致する属性のみが使用されます。

### アプリケーションのサポート

アカウント検出は、以下に示すコネクタを除くすべてのプロビジョニング コネクタで有効になります。 アカウント検出を機能させるには、 [RFC 7644、セクション 3.4.2.4](https://datatracker.ietf.org/doc/html/rfc7644#section-3.4.2.4) の説明に従って、ターゲット アプリケーションで SCIM 改ページ調整を実装する必要があります。 検出の結果は、ターゲット アプリケーションが SCIM API を使用したユーザーとグループの一覧表示と改ページをサポートしているかどうかによって異なります。 検出レポートの結果が 0 の場合は、属性マッピングで 1 つの直接一致属性 (式なし) を構成したことを確認します。 次に、 [RFC 7644、セクション 3.4.2.4](https://datatracker.ietf.org/doc/html/rfc7644#section-3.4.2.4) に従って、アプリケーションが改ページをサポートしていることをアプリケーション ベンダーに確認します。

現時点では、次のコネクタおよびプロビジョニング シナリオでは、ユーザーまたはグループの検出はサポートされていません: Adobe Identity Management、Amazon Web Services (AWS)、クラウド同期、クロステナント同期とクロスクラウド同期、Freshservice、Active Directory へのグループ プロビジョニング、HR プロビジョニング (Workday、SAP SuccessFactors、HiBob、API 駆動型プロビジョニング)、LinkedIn Learning、LinkedIn Sales Navigator、N-able、ServiceNow、Snowflake。

次のコネクタとプロビジョニング シナリオではユーザー検出がサポートされていますが、現時点では、ECMA、Google Cloud、Google Workspace はサポートされていません。

### ターゲット アプリケーションで ID を検出する

ターゲット アプリケーション内の既存のユーザーとグループを検出するには:

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Identity**&gt;**Applications**&gt;**Enterprise アプリケーション** に移動します。
3. ID を検出するアプリケーションを選択します。
4. 左側のナビゲーションで、[ **プロビジョニング**] を選択します。
5. プロビジョニング構成に有効な資格情報とテスト接続が成功していることを確認します。
6. [ **ID の検出] を選択します**。

プロビジョニング サービスは、ターゲット アプリケーションからユーザーとグループを取得し、カテゴリ別に整理して表示します。 検出には、レポートの生成に少なくとも 30 分かかります。 ターゲット アプリケーションに含まれる ID が多いほど、レポートにかかる時間は長くなります。 たとえば、250,000 個の ID を持つアプリケーションでは、検出レポートの生成に 12 時間以上かかる場合があります。

### 検出された ID を確認する

検出プロセスが完了したら、各カテゴリの結果を確認します。

#### ローカル ID

ローカル ID はターゲット アプリケーションに存在しますが、Microsoft Entra IDには対応するユーザーまたはグループがありません。 これらの ID は、次を表す場合があります。

- ディレクトリ アカウントが削除されたが、アプリケーション アカウントがプロビジョニング解除されなかった元従業員。
- アプリケーションで直接作成されたサービス アカウントまたは共有アカウント。
- Microsoft Entra IDを使用していない別のプロセスを通じてプロビジョニングされたユーザー。
- ターゲット アプリケーションで直接作成されたグループ。
- 一致を妨げるデータ品質の問題。

これらの ID を確認して、ターゲット アプリケーションから削除するか、Microsoft Entra IDの既存のユーザーまたはグループと照合するか、as-is保持するかを決定します。

#### 未割り当て ID

割り当てられていない ID は、一致する属性に基づいてMicrosoft Entra IDのユーザーまたはグループと一致しますが、エンタープライズ アプリケーションには割り当てられません。 これらの ID をプロビジョニング管理の下に持ち込むには、次の手順を実行します。

1. エンタープライズ アプリケーションの **[ユーザーとグループ** ] ページに移動します。
2. 適切なユーザーまたはグループをアプリケーションに割り当てます。
3. 割り当ての後、プロビジョニング サービスは後続のプロビジョニング サイクルでこれらの ID を管理します。

#### 割り当てられた ID

割り当てられた ID は、アプリケーションに既に割り当てられているMicrosoft Entra IDのユーザーまたはグループと一致します。 これらの ID は、プロビジョニング サービスによって完全に管理されます。 属性マッピングを確認または更新しない限り、アクションは必要ありません。

### エンタープライズ アプリケーションやアクセス パッケージに関連付けられたユーザーを割り当てる

アプリケーション内のユーザー [を検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery) したら、それらのユーザーをエンタープライズ アプリケーションまたはアクセス パッケージに割り当てることができます。 [ダウンロード](https://aka.ms/AssignCorrelatedUsersPowerShell)した `Assign-CorrelatedUsers.ps1` ファイルを PowerShell 7.x で実行して、ユーザーを割り当てます。

#### オプションのパラメーター

| パラメーター | Description |
| --- | --- |
| **`-DryRun`** | *変更を*加えない場合に何が起こるかを示します。 |
| **`-SkipAppRoleAssignment`** | アクセス パッケージのみを管理し、アプリ ロールの割り当てをスキップします。 |
| **重複検出** | 新しい割り当てを作成する前に、既存の割り当てを確認します。 |
| **クライアント側の状態フィルター** | API の結果が予想される状態と一致し、API の風変わりから保護されることを確認します。 |
| **`-OutputFile`** | タイムスタンプ、アクション、エラーの詳細を含む完全な監査証跡を CSV ファイルとして作成します。 |
| **厳格モード** | `Set-StrictMode -Version Latest`と`$ErrorActionPreference = "Stop"`を使用して実行すると、予期しない問題で高速に失敗します。 |

#### シナリオの例

関連付けられたすべてのユーザーをエンタープライズ アプリケーションに割り当てます。

```powershell
pwsh -File '.\Assign-CorrelatedUsers.ps1' -ServicePrincipalId "7A22..."
```

[ルール](https://aka.ms/AssignCorrelatedUsersCSV) ファイルの例を使用して、関連付けられたすべてのユーザーを特定のアクセス パッケージに割り当てます。

```powershell
pwsh -File '.\Assign-CorrelatedUsers.ps1' -ServicePrincipalId '7A22...' -RulesFile '.\access-package-rules-internal.csv' -DryRun -OutputFile '.\results-dryrun.csv'
```

定義したルールに基づいてユーザーをパッケージに割り当てます。

```powershell
pwsh -ExecutionPolicy Bypass -File '.\Assign-CorrelatedUsers.ps1' -ServicePrincipalId "7A22..." -RulesFile ".\access-package-rules.csv"
```

定義されたいずれのルールにも該当しないユーザー向けのフォールバック パッケージを使用して、ユーザーをアクセス パッケージに割り当てます。

```powershell
pwsh -ExecutionPolicy Bypass -File '.\Assign-CorrelatedUsers.ps1' -ServicePrincipalId "7A22..." -RulesFile ".\access-package-rules.csv" -AccessPackageId "fallback-pkg-id" -PolicyId "fallback-policy-id" -FallbackBehavior UseFallback
```

アクセス パッケージにユーザーを割り当て、アプリケーション ロールの割り当てをスキップします。

```powershell
pwsh -ExecutionPolicy Bypass -File '.\Assign-CorrelatedUsers.ps1' -ServicePrincipalId "7A22..." -RulesFile ".\access-package-rules.csv" -SkipAppRoleAssignment
```

#### ルール ファイルの説明

ルール ファイルは、次の列を含む標準 [の CSV](https://aka.ms/AssignCorrelatedUsersCSV) です。

| コラム | Purpose |
| --- | --- |
| `RuleGroup` | 同じグループ番号を共有する行は、一緒に AND されます。 異なるグループは個別に評価されます。 |
| `PropertyName` | ターゲット SCIM プロパティ バッグ内のキー ( `userType` や `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department`など)。 探索エクスペリエンスでプロパティ名を見つけるには、個々のユーザーの **属性の表示** を選択するか、プロビジョニング属性マッピングを確認します。 |
| `Operator` | `eq`、 `ne`、 `contains`、 `startswith`、 `endswith`、または `regex`。 |
| `Value` | 比較する値。 比較では大文字と小文字は区別されません。 |
| `AccessPackageId` | グループが一致したときに割り当てるアクセス パッケージ。 この値は、Microsoft Entra 管理センター内のアクセス パッケージに移動するときに URL で確認できます。 |
| `PolicyId` | アクセス パッケージの割り当てポリシー。 この値は、Microsoft Entra 管理センター内のアクセス パッケージに移動するときに URL で確認できます。 |

### アイデンティティガバナンスとの統合

アカウント検出は[Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)と共に機能し、完全な ID ライフサイクルを管理するのに役立ちます。 ターゲット アプリケーションで ID を検出すると、次のことができます。

- アクセス パッケージに既存のユーザーを割り当てて今後のアクセスを管理し、アクセス パッケージでレビューを実行してアクセスを認定し、ライフサイクル管理を自動化するようにライフサイクル ワークフローを構成します。
- アプリケーションへのアクセスを要求できるユーザーを管理するように [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) を構成します。
- ユーザー ライフサイクル イベントに基づいてプロビジョニングとプロビジョニング解除を自動化するようにライフサイクル [ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) を設定します。

アプリケーション アクセスの管理の詳細については、「 [環境内のアプリケーションのアクセスの管理」を参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare)。

### Microsoft Graphを使用して結果を取得する

Microsoft Entra 管理センターで検出されたアカウントを確認するだけでなく、Microsoft Graphを使用してプログラムでアカウント検出の結果を取得できます。

Microsoft Graph ベータ API を使用して、次の手順を実行します。

- テナント内の ID 相関レポートを一覧表示します。
- 特定の ID 相関レポートを取得します。
- レポートに含まれる ID を一覧表示します。
- 関連付け状態で ID をフィルター処理します。

API の詳細については、次のリソースを参照してください。

- [identityCorrelation リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/identitycorrelation?view=graph-rest-beta)
- [ID 相関レポートを一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/reportroot-list-correlations?view=graph-rest-beta)
- [関連付けられた ID を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/identitycorrelation-list-identities?view=graph-rest-beta)

Important

ID 相関 API は、Microsoft Graph ベータ エンドポイントで使用できます。 ベータ エンドポイントの下の API は変更される可能性があり、運用アプリケーションでの使用はサポートされていません。

### Microsoft MCP Server for Enterprise を使用してアカウント検出を調査する (プレビュー)

[Microsoft MCP Server for Enterprise](https://github.com/mcp/microsoft/EnterpriseMCP) を使用して、自然言語で質問することで、アカウント検出レポートを調査できます。 MCP サーバーは、質問を読み取り専用のMicrosoft Graph要求に変換し、サインインしているユーザーの特権と MCP クライアントに付与されたアクセス許可を適用し、結果の自然言語の概要を返します。

このオプションは、Microsoft Graph要求を手動で作成せずにアカウント検出の結果を調べる管理者に役立ちます。 また、MCP クライアントには、操作を確認および監査できるように、基になる要求も表示されます。

#### MCP の前提条件

アカウント検出で MCP サーバーを使用する前に、次の手順を実行します。

- [Microsoft MCP Server for Enterprise をプロビジョニングし、MCP クライアントを接続する](https://github.com/mcp/microsoft/EnterpriseMCP)。
- MCP クライアントに `MCP.ProvisioningLog.Read.All` 委任されたアクセス許可を付与します。
- サポートされているMicrosoft Entraロールを持つ職場または学校アカウントでサインインします。 サポートされるロールには、アプリケーション管理者、クラウド アプリケーション管理者、ハイブリッド ID 管理者、グローバル閲覧者、レポート閲覧者、セキュリティ管理者、セキュリティ オペレーター、およびセキュリティ閲覧者が含まれます。
- グローバル Microsoft クラウドを使用します。 Microsoft MCP Server for Enterprise と ID 相関 API は、現在ソブリン クラウドでは使用できません。

MCP サーバーは、委任されたユーザー対話型アクセスのみをサポートします。 アプリ専用アクセスはサポートされていません。

#### 質問の例

MCP クライアントを接続したら、次のような質問をすることができます。

- "最新のアカウント検出レポートを表示してください。"
- "サービス プリンシパル `<service-principal-id>`のアカウント検出レポートを一覧表示します。"
- "最新のアカウント検出レポートはエラーなしで完了しましたか?
- "アカウント検出レポートはまだ進行中ですか?
- "ID 関連付けレポートの `<report-id>`を取得します。"
- "ID 相関レポート `<report-id>`の ID を一覧表示します。"
- "レポート `<report-id>` のどの ID がローカル ID ですか?
- レポート`<report-id>`内で、Microsoft Entra のユーザーまたはグループに一致するが、アプリケーションの割り当てがない ID はどれですか?
- レポート `<report-id>` で関連付けに失敗した ID はどれですか?

質問に応じて、MCP サーバーは次のような要求を実行できます。

```http
GET /beta/reports/correlations
GET /beta/reports/correlations/{identityCorrelationId}
GET /beta/reports/correlations/{identityCorrelationId}/identities
GET /beta/reports/correlations/{identityCorrelationId}/identities?$filter=status eq 'uncorrelated'
```

#### 関連付けの状態を理解する

Microsoft Graph APIは、次の関連付けステータス値を持つアカウント検出カテゴリを表します。

| 関連付けカテゴリ | Microsoft Graphの状態 | Description |
| --- | --- | --- |
| 割り当てられた ID | `correlatedAssigned` | ターゲット ID は、エンタープライズ アプリケーションに割り当てられているMicrosoft Entra ID内のユーザーまたはグループと一致します。 |
| 未割り当て ID | `correlatedNotAssigned` | ターゲット ID はMicrosoft Entra ID内のユーザーまたはグループと一致しますが、そのユーザーまたはグループはエンタープライズ アプリケーションに割り当てられません。 |
| ローカル ID | `uncorrelated` | Microsoft Entra ID内の対応するユーザーまたはグループがターゲット ID に見つかりませんでした。 |
| 関連付けの失敗 | `failToCorrelate` | 関連付けプロセスで ID を正常に評価できませんでした。 ID に対して返されるエラー情報を確認します。 |

#### 調査の例

次の会話は、管理者がレポートを調査する方法を示しています。

1. 「サービスプリンシパル `<service-principal-id>` に関する、最新の完了済みアカウント調査レポートを表示する」と入力します。
2. MCP クライアントから返されたレポート ID をコピーします。
3. 「ID相関レポート `<report-id>` の ID情報を一覧表示する」と次のように尋ねます。
4. レポート ID を含む別の質問をします。たとえば、"レポート `<report-id>` の ID はローカル ID ですか?"、"レポート内のどの ID `<report-id>` 関連付けに失敗しましたか?" などです。

結果に依存する前に、MCP クライアントによって表示されるMicrosoft Graph要求を確認します。 大規模なレポートの場合、クライアントは追加のページを取得するために `@odata.nextLink` に従う必要がある場合があります。

#### MCP のトラブルシューティング

MCP クライアントがアカウント検出の結果を取得できない場合:

- MCP クライアントに `MCP.ProvisioningLog.Read.All` 委任されたアクセス許可があることを確認します。
- サインインしているユーザーがサポートされているMicrosoft Entraロールを持っていることを確認します。
- アカウント検出レポートが含まれているテナントにアカウントがサインインしていることを確認します。
- MCP クライアントによって表示される要求と応答を確認します。
- Microsoft MCP Server for Enterprise によって行われたリクエストの Microsoft Graph アクティビティ ログを確認します。 MCP サーバー アプリ ID `e8c77dc2-69b3-43f4-bc51-3213c9d915b4`でログをフィルター処理します。

MCP サーバーは、サポートされている要求をMicrosoft Graphに転送します。 Microsoft Graph APIの動作とベータの制限は、MCP サーバーを介して行われた要求にも適用されます。

### フィルター処理と検索結果

検索機能とフィルター機能を使用して、特定のアカウントを検索します。

- 名前または属性値でアカウントを検索します。
- カテゴリ (ローカル、未割り当て、または割り当て済み) で結果をフィルター処理します。
- ターゲット アプリケーションからインポートされた属性と関連付けの状態を表示する列を管理します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/hr-attribute-retrieval-issues"} -->
## HR プロビジョニングに関する属性の取得に関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-attribute-retrieval-issues
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: HR プロビジョニングに関する属性の取得に関する問題をトラブルシューティングする方法について説明します

### Workday 属性のフェッチに関する問題

| に適用されます |
| --- |
| \* Workday からオンプレミスの Active Directory へのユーザー プロビジョニング  \* Workday からの Microsoft Entra へのユーザー プロビジョニング |
| **問題の説明** |
| Workday 受信プロビジョニング アプリを構成し、Workday テナント URL に正常に接続しました。 テスト同期を実行し、プロビジョニング アプリが Workday から特定の属性を取得していないことを確認しました。 一部の属性だけが読み取り、ターゲットにプロビジョニングされます。 |
| **考えられる原因** |
| 既定では、Workday プロビジョニング アプリには、Workday Web Services (WWS) v21.1 で動作する属性マッピングと XPATH 定義が含されています。 プロビジョニング アプリで Workday への接続を構成するときに、WWS API バージョン (例: `https://wd3-impl-services1.workday.com/ccx/service/contoso4/Human_Resources/v34.0`) を明示的に指定した場合、WWS API バージョンと XPATH 定義の不一致が原因で、この問題が発生する可能性があります。 |
| **解決オプション** |
| \* *オプション 17*:URL から WWS API バージョン情報を削除し、既定の WWS API バージョン v21.1 を使用する  \* *オプション 2*: XPATH API 式を手動で更新して、お好みの WWS API バージョンと互換性を持たないようにします。 「**Workday 属性のリファレンス**」セクションを参照して、**[属性マッピング] -&gt; [詳細オプション] -&gt; [Edit attribute list for Workday](Workday の属性リストの編集)** の下にある [\[XPATH API 式\]](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference#xpath-values-for-workday-web-services-wws-api-v30) を更新します。 |

### Workday 計算フィールドのフェッチに関する問題

| に適用されます |
| --- |
| \* Workday からオンプレミスの Active Directory へのユーザー プロビジョニング  \* Workday からの Microsoft Entra へのユーザー プロビジョニング |
| **問題の説明** |
| Workday 受信プロビジョニング アプリを構成し、Workday テナント URL に正常に接続しました。 Workday で構成された統合システムを使用し、Workday 統合システムの属性を指す XPATH を構成しました。 ただし、Microsoft Entra プロビジョニング アプリでは、これらの統合システム属性または計算フィールドに関連付けられている値はフェッチされません。 |
| **原因** |
| これは、既知の制限です。 現在、Workday プロビジョニング アプリでは、 *Field\_And\_Parameter\_Criteria\_Data* Get\_Workers 要求フィルターを使用した計算フィールド/統合システム属性のフェッチはサポートされていません。 この機能は、統合フィールドのオーバーライドとも呼ばれます。 |
| **解決オプション** |
| Workday プロビジョニング グループまたは Workday ユーザー設定 ID フィールドを使用する回避策を検討してください。 |

**推奨される対処方法**

- **オプション 1: Workday プロビジョニング グループの使用**: 計算フィールドの値を Workday のプロビジョニング グループとして表すことができるかどうかを確認します。 計算フィールドに使用されるのと同じロジックを使用して、Workday 管理者がプロビジョニング グループをユーザーに割り当てることができる場合があります。 Workday ログインが必要な Workday ドキュメントを参照する: [アカウント プロビジョニング グループを設定します](https://doc.workday.com/reader/3DMnG%7E27o049IYFWETFtTQ/keT9jI30zCzj4Nu9pJfGeQ)。 いったん構成すると、このプロビジョニング グループの割り当てを [プロビジョニング ジョブで取得](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-integration-reference#example-3-retrieving-provisioning-group-assignments) し、属性マッピングとスコープ フィルターで使用できます。
- **オプション 2: Workday カスタム ID の使用**: 計算フィールドの値を Worker プロファイルのカスタム ID として表すことができるかどうかを確認します。 Workday の`Maintain Custom ID Type`タスクを使用して新しい型を定義し、このカスタム ID に値を設定します。 統合に使用されるはドメイン セキュリティアクセス許可があることを確認します。
    - 例 1: "Payroll ID" という名前の計算フィールドがあるとします。 Workday でカスタム ID として "External\_Payroll\_ID" を定義し、選択メカニズムとして "Custom\_ID\_Type\_ID" を使用する XPATH を使用してそれを取得できます。`wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Identification_Data/wd:Custom_ID/wd:Custom_ID_Data[string(wd:ID_Type_Reference/wd:ID[@wd:type='Custom_ID_Type_ID']='External_Payroll_ID']/wd:ID/text()`
    - 例 2: "Badge ID" という名前の計算フィールドがあるとします。 Workday でカスタム ID として "Badge ID" を定義し、選択メカニズムとして "wd:ID\_Type\_Reference/@wd:Descriptor" を使用する XPATH を使用してそれに対応する "Descriptor" 属性を取得できます。`wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Identification_Data/wd:Custom_ID[string(wd:Custom_ID_Data/wd:ID_Type_Reference/@wd:Descriptor)='BADGE ID']/wd:Custom_ID_Reference/@wd:Descriptor`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/hr-manager-update-issues"} -->
## HR プロビジョニングに関するマネージャーの更新に関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-manager-update-issues
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: この記事では、HR プロビジョニングに関するマネージャー更新プログラムの問題のトラブルシューティング方法を示す潜在的な問題と解決策について説明します

**適用対象:**

- Workday からオンプレミスの Active Directory へのユーザー プロビジョニング
- Workday から Microsoft Entra へのユーザー プロビジョニング
- SAP SuccessFactors からオンプレミスの Active Directory へのユーザー プロビジョニング
- SAP SuccessFactors から Microsoft Entra へのユーザー プロビジョニング

### マネージャー参照解決のしくみを理解する

Microsoft Entra プロビジョニング サービスは、Microsoft Entra ID のユーザー マネージャー関係が人事データと常に同期されるように、マネージャー情報を自動的に更新します。 *マネージャー参照解決* と呼ばれるプロセスを使用して、*マネージャーの* 属性を正確に更新します。 プロセスの詳細に進む前に、マネージャー情報が Microsoft Entra ID とオンプレミスの Active Directory にどのように格納されるかを理解することが重要です。

- **オンプレミスの Active Directory** では、*manager* 属性は、マネージャーのアカウントの *distinguishedName (dn)* を AD に格納します。
- **Microsoft Entra ID** では、*manager* 属性は Microsoft Entra ID の DirectoryObject ナビゲーション プロパティです。 Microsoft Entra 管理センターでユーザー レコードを表示すると、マネージャー レコードの *displayName* が Microsoft Entra ID に表示されます。

*マネージャー参照解決*は、次の 2 つのステップのプロセスです。

- 手順 1: 属性ペアである*ソース アンカー* と*ターゲット アンカー*を使用して、マネージャーの人事ソース レコードとターゲット アカウント レコードをリンクします。
- 手順 2: スキーマで定義されているマネージャー参照属性を使用して、ターゲットのマネージャー属性を必要な形式で更新します。

各アプリの既定のアンカー属性と参照属性は次のとおりです。

| アプリ名 | アンカー属性 | ユーザー プロファイルの参照属性 |
| --- | --- | --- |
| 就業日 | WID | ManagerReference (マネージャー レコードの WID を指す) |
| SAP SuccessFactors | personIdExternal | manager (マネージャー レコードの personIdExternal を参照) |
| オンプレミスの Active Directory | objectGUID（オブジェクトGUID） | manager (マネージャー レコードの DN を示します) |
| マイクロソフト エントラ ID | objectId | マネージャー (マネージャーの Microsoft Entra ID レコードを指します) |

### マネージャーの更新を正常に実行するための前提条件

*マネージャー参照解決* が正常に機能するためには、次の前提条件を満たす必要があります。

- アンカー属性の表に記載されている既定のソース アンカーとターゲット アンカーを使用するようにプロビジョニング アプリを構成する必要があります。 これらのアンカー属性と参照属性に関連付けられているメタデータ プロパティ (データ型、API 式) は変更しないでください。
- マネージャー属性に関連付けられている API 式 (XPATH for Workday および JSONPath for SuccessFactors) は、有効な null 以外の値に解決されます。
    - Workday ManagerReference の既定の XPATH API 式: `wd:Worker/wd:Worker_Data/wd:Management_Chain_Data/wd:Worker_Supervisory_Management_Chain_Data[position()=1]/wd:Management_Chain_Data[last()=position()]/wd:Manager_Reference/wd:ID[@wd:type='WID']/text()`
    - SuccessFactors マネージャーの既定の JSONPath API 式: `$.employmentNav.results[0].userNav.manager.empInfo.personIdExternal`
- マネージャー レコードもプロビジョニング ジョブのスコープ内に含む必要があります。
- プロビジョニング アプリでは、ユーザー レコードを処理する前に、マネージャー レコードを処理する必要があります。

手記

*マネージャー* 属性マッピングは直接マッピングである必要があり、複数のソース属性を含めることはできません。 式マッピングを使用してマネージャー属性の条件付き割り当てを実行することはできません。 たとえば、"ユーザーがアクティブな場合は manager1 を割り当て、それ以外の場合は manager2 を割り当てる" などのロジックの実装はサポートされていません。

### プロビジョニング オンデマンドでマネージャー属性が更新されない

| トラブルシューティング | 細部 |
| --- | --- |
| **問題** | 受信プロビジョニング アプリが正常に構成されました。 オンデマンド プロビジョニングとの同期をテストしています。 マネージャー属性は更新されません。"無効な値" エラー メッセージが表示されます。 |
| **原因** | プロビジョニング ジョブが、マネージャーの更新 を正常に実行するための の前提条件の 1 つを満たしていません |
| **解決** | \* 既定のマネージャー属性マッピングを変更した場合は、既定のマッピングを復元します。  \* マネージャー レコードがスコープ内にあり、マネージャー API 式が有効な値に解決されていることを確認します。  \* 最初にマネージャーのレコードに対してオンデマンドプロビジョニングを実行し、次にユーザーのレコードに対してオンデマンドプロビジョニングを実行します。 |

### 完全同期でマネージャー属性が更新されない

| トラブルシューティング | 細部 |
| --- | --- |
| **問題** | 受信プロビジョニング アプリが正常に構成されました。 スコープ フィルターを使用して、特定の HR レコードのみを処理しています。 一部のユーザーに対してマネージャーの割り当てが行われていないことがわかります。 |
| **原因** | スコープ フィルターを使用している場合は、ほとんどの場合、マネージャー レコードがスコープ内にありません。 |
| **解決** | スコープ内にマネージャー レコードを追加するようにスコープ フィルターを更新する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/hr-user-creation-issues"} -->
## HR プロビジョニングに関するユーザー作成の問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-creation-issues
- Service: entra-id / app-provisioning
- Article date: 2026-08-20
- Summary: HR プロビジョニングに関するユーザー作成の問題をトラブルシューティングする方法について説明します

### ユーザー作成時の null 値と空文字列

**適用対象:**

- Workday からオンプレミスのActive Directoryにユーザーをプロビジョニングする
- Workday から Microsoft Entra へのユーザー プロビジョニング
- SAP SuccessFactorsからオンプレミスのActive Directoryへのユーザー プロビジョニング
- SAP SuccessFactorsからMicrosoft Entraへのユーザープロビジョニング

| Troubleshooting | 詳細情報 |
| --- | --- |
| **問題** | HR アプリは、ユーザーの作成時に null または空の値を返し、結果のターゲット値が意図した動作と一致しません。 オンプレミスの Active Directoryへのプロビジョニングでは、作成操作が失敗し、次のエラー メッセージが表示されることがあります: `InvalidAttributeSyntax-LdapErr: The syntax is invalid. The parameter is incorrect. Error in attribute conversion operation, data 0, v3839`。 |
| **原因** | 属性値のクリアは、既定では無効になっています。 ソース属性とターゲット マッピングの両方で null 値フローが有効になっていない場合、プロビジョニング サービスはソース値を無視するか、空の文字列をターゲットに渡す可能性があります。 オンプレミスの Active Directory コネクタは空の文字列を設定できないため、LDAP エラーが返されます。 |
| **Resolution** | プロビジョニング ログを確認し、null または空の値に関連付けられているソース属性とターゲット属性を特定します。 次に、ターゲット属性を空のままにするか、フォールバック値を受け取るか、ソース値を無視するかに基づいてマッピングを構成します。 |

**推奨される解決策**

たとえば、Active Directory属性`jobTitle`にマップされる Workday 属性`BusinessTitle`は、null または空にすることができます。

- 作成時にオプションのターゲット属性を空のままにし、今後の更新時にクリアするには、ソース属性とターゲット マッピングの両方で [属性値のクリアを有効にします](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values) 。 **Null の場合に既定値**を構成した場合、プロビジョニング サービスは作成時にのみその値を使用します。
- 必要なターゲット属性に空白以外のフォールバック値を設定するには、 [Switch](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch) 関数を使用します。 たとえば、「 `Switch([BusinessTitle],[BusinessTitle],"","N/A")` 」のように入力します。
- ターゲット属性をクリアするのではなく、null または空のソース値を無視するには、 [IgnoreFlowIfNullOrEmpty](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#ignoreflowifnullorempty) 関数を使用します。 たとえば、「 `IgnoreFlowIfNullOrEmpty([BusinessTitle])` 」のように入力します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/hr-user-update-issues"} -->
## HR プロビジョニングでのユーザーの更新に関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-update-issues
- Service: entra-id / app-provisioning
- Article date: 2026-08-20
- Summary: HR プロビジョニングでのユーザーの更新に関する問題をトラブルシューティングする方法について説明します

### Null 値と空の値が予期したとおりに処理されませんでした

**適用対象:**

- Workday からオンプレミスのActive Directoryにユーザーをプロビジョニングする
- Workday から Microsoft Entra へのユーザー プロビジョニング
- SAP SuccessFactorsからオンプレミスのActive Directoryへのユーザー プロビジョニング
- SAP SuccessFactorsからMicrosoft Entraへのユーザープロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | 受信プロビジョニング アプリの構成は正しく行いました。 HR アプリは null または空の値を返しますが、ターゲット属性がクリアされないか、操作が失敗し、エラー メッセージが表示されます: `InvalidAttributeSyntax-LdapErr: The syntax is invalid. The parameter is incorrect. Error in attribute conversion operation, data 0, v3839`。 |
| **原因** | 属性値のクリアは、既定では無効になっています。 プロビジョニング サービスは、ソース属性とターゲット マッピングの両方に対して **Flow null 値** が有効になっている場合にのみ、ターゲット属性をクリアします。 両方の設定がない場合、null または空の値は無視されるか、空の文字列を受け入れないターゲットに渡される可能性があります。 |
| **解決** | プロビジョニング ログを調べて、HR コネクタによって返される値を確認します。 次に、目的の動作に基づいてソース属性とターゲット マッピングを構成します。 |

**推奨される解決策**

たとえば、Active Directory属性`jobTitle`にマップされる Workday 属性`BusinessTitle`は、null または空にすることができます。

- 既存のターゲット値をクリアするには、ソース属性とターゲット マッピングの両方に対して属性値の [クリアを有効にします](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values) 。
- null または空の値を空白以外のフォールバック値に置き換えるには、 [Switch](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch) 関数を使用します。 たとえば、「 `Switch([BusinessTitle],[BusinessTitle],"","N/A")` 」のように入力します。
- 既存のターゲット値を保持するには、 [IgnoreFlowIfNullOrEmpty](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#ignoreflowifnullorempty) 関数を使用します。 たとえば、「 `IgnoreFlowIfNullOrEmpty([BusinessTitle])` 」のように入力します。

### 一部の Workday 属性の更新が見つからない

**適用対象:**

- Workday からオンプレミスのActive Directoryにユーザーをプロビジョニングする
- Workday から Microsoft Entra へのユーザー プロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | Workday 受信プロビジョニング アプリは正常に構成され、Workday テナント URL に正常に接続されています。 Workday からの特定の属性更新のフローに遅延が発生していること、または増分同期中に Workday からの属性変更が予想どおりに反映されない場合があることを確認しました。 |
| **原因** | 増分同期中、プロビジョニング アプリは Workday のトランザクション ログに対してプライマリ Worker エンティティの変更のクエリを実行し、Workday のトランザクション ログで追跡されている変更だけが処理されます。  セットアップの Workday 属性への変更が Workday のトランザクション ログで追跡されない場合、Microsoft Entra IDはその変更をフェッチしません。 たとえば、 *LocalReference* Workday 属性は既定の属性マッピングの一部であり、XPATH `wd:Worker/wd:Worker_Data/wd:Employment_Data/wd:Position_Data/wd:Business_Site_Summary_Data/wd:Local_Reference/wd:ID[@wd:type='Locale_ID']/text()`。 この属性は、エンティティ Business\_Site\_Summary\_Dataの一部 *です*。 Workday のこの属性の値が変更されても、Workday のトランザクション ログには表示されません。 そのため、増分同期中にこの属性の新しい値が表示されるのは、同期間隔中にプライマリ Worker エンティティに関連付けられている属性も変更された場合に限られます。 |
| **解決** | 特定の Workday 属性の変更が反映されないというこの動作が頻繁に発生する場合は、週 1 回または月 1 回の完全同期を定期的に実行することをお勧めします。 |

### 属性が見つからない

**適用対象:**

- Workday からオンプレミスのActive Directoryにユーザーをプロビジョニングする
- Workday から Microsoft Entra へのユーザー プロビジョニング
- Workday からオンプレミスのActive Directoryにユーザーをプロビジョニングする
- SAP SuccessFactorsからオンプレミスのActive Directoryへのユーザー プロビジョニング
- オンプレミスの Active Directoryへの API 駆動型プロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | 次のエラー コードが返されます: `HybridSynchronizationActiveDirectoryCannotFindAttribute` |
| **原因** | 属性名が Active Directory スキーマ: 属性に見つかりませんでした。 |
| **解決** | 属性が最近 Active Directory に追加された場合は、起動時にスキーマをキャッシュするため、プロビジョニング エージェントを再起動します。 |

### extensionAttribute を使用したユーザー照合が機能しない

**適用対象:**

- Workday から Microsoft Entra へのユーザー プロビジョニング
- SAP SuccessFactorsからMicrosoft Entraへのユーザープロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | たとえば、Microsoft Entra ID の *extensionAttribute3* を使用して従業員 ID を保存し、それを Workday の *WorkerID* または SuccessFactors の *personIdExternal* 属性にマッピングしてユーザーの照合を行うとします。 この構成では、プロビジョニング プロセスの照合手順が失敗します。 この問題は、ユーザーの作成と更新の両方に影響します。 |
| **原因** | Microsoft Entra ID *OnPremisesExtensionAttributes* (`extensionAttributes1-15`) は一致する属性として使用できません。これは、`$filter` の  パラメーターが [extensionAttributes によるフィルター処理をサポートしていない](https://learn.microsoft.com/ja-jp/previous-versions/azure/ad/graph/howto/azure-ad-graph-api-supported-queries-filters-and-paging-options#filter) ためです。 |
| **解決** | 一致する属性ペアで Microsoft Entra ID *OnPremisesExtensionAttributes* (`extensionAttributes1-15`) を使用しないでください。 employeeID を使用します。 |

### Microsoft Entra ID *mail* 属性の更新はサポートされていません

**適用対象:**

- Workday から Microsoft Entra へのユーザー プロビジョニング
- SAP SuccessFactorsからMicrosoft Entraへのユーザープロビジョニング
- Microsoft Entra IDへの API 駆動型プロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | HR システムから *mail* 属性を Microsoft Entra ID にプロビジョニングするように構成しました。 プロビジョニング ログにはメール属性のレコードが表示されているにもかかわらず、メール属性の更新は機能していません。 |
| **原因** | Microsoft Entraへのプロビジョニング コネクタでは、ユーザー プロビジョニング中に *mail* 属性を設定することはサポートされていません。これは、この属性がオンラインMicrosoft Exchangeによって管理されるためです。 |
| **解決** | ユーザーを作成した後、Exchange Online ライセンスをユーザーに割り当てると、ユーザー プリンシパル名が電子メール アドレスとして自動的に設定されます。 メール属性を更新するには、Exchange Online ポータルまたは PowerShell を使用します。 |

### Workday からの退職日フィールドの設定

**適用対象:**

- Workday からオンプレミスのActive Directoryにユーザーをプロビジョニングする
- Workday から Microsoft Entra へのユーザー プロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | プロビジョニング アプリで Workday の 'Last Day of Work' (`StatusTerminationLastDayOfWork`) 属性の属性マッピングを構成しました。 ただし、「Last Day of Work」の更新は退職日が有効になった後でのみ実行されますが、この「Last Day of Work」を退職日より前に取得したいと考えています。 |
| **原因** | Workday では、退職日が有効になった後でのみ、ワーカー プロファイルに 'Last Day of Work' フィールドが設定されます。 そのため、Workday プロビジョニング コネクタは、退職日より前に、この日付を事前に取得することができません。 |
| **解決** | Workday で、「勤務最終日を過ぎた従業員」という名前のプロビジョニング グループを作成します。 Workday に自動化を追加して、ワーカーの最終在籍日に達したときに、ユーザーがこのグループに割り当てられるようにします。 Microsoft Entra プロビジョニング ジョブで、Workday XPATH 属性を追加して、このグループ割り当てを取得します。 |

- 例:``LastDayOfWorkWorkers =  wd:Worker/wd:Worker_Data/wd:Account_Provisioning_Data/wd:Provisioning_Group_Assignment_Data[wd:Status='Assigned' and wd:Provisioning_Group=" Workers past Last Day of Work"]/wd:Provisioning_Group/text()``

属性マッピング ロジックで accountDisabled フラグに対してこのフィールドを使用します。

- 例:``Switch([LastDayOfWorkWorkers], Switch([Active], , "1", "False", "0", "True"), 'Workers past Last Day of Work', "True")``

### Workdayにおける退職処理の遅延

**適用対象:**

- Workday からオンプレミスのActive Directoryにユーザーをプロビジョニングする
- Workday から Microsoft Entra へのユーザー プロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | 増分同期中、アジア太平洋およびオーストラリア/ニュージーランド地域のワーカーの退職イベントの処理に 12 時間から 18 時間の遅延が発生する場合があります。 |
| **原因** | Workday Integration System User (ISU) アカウントは、常に太平洋タイム ゾーンに基づいてデータを取得します。 コネクタには現在、タイム ゾーンに固有の退職レコードを処理するための特別なクエリが実装されていません。 |
| **解決** | 終了先読みクエリ機能を使用します。 セットアップと構成の手順については、「 [Workday 終了先読みクエリの構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-workday-termination-lookahead)」を参照してください。 |

### SuccessFactors 終了処理の遅延

**適用対象:**

- オンプレミスのActive DirectoryへのSuccessFactorsのユーザープロビジョニング
- SuccessFactorsからMicrosoft Entra IDへのユーザー プロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | 特定のシナリオでは、"アカウントの無効化" 操作として終了した雇用状態の伝達に遅延が発生する可能性があります。 これは、Microsoft Entraでのユーザー無効化機能がないためではなく、HR 主導のプロビジョニング中にリアルタイム ID ライフサイクルの変更がどのように検出されるかが原因です。 |
| **原因** | Microsoft Entraのプロビジョニング サービスは、ステートレス変更検出システムとして動作します。 変更を有効にする必要がある時点で、ソース システム (SAP SuccessFactors など) に依存して、終了が有効になるなどの時間ベースの変更イベントを生成します。 その後、プロビジョニング サイクルは、増分同期中にそれらのイベントを検出して処理します。 *現在の日付*の時点で終了が有効なシナリオでは、SuccessFactors は、ユーザーの雇用状態が変更された正確な時刻 (たとえば、営業日の終了時) に増分変更イベントを生成しない可能性があります。 その結果、Microsoft Entraプロビジョニングはポーリング サイクル中に検出可能な変更を受け取らないので、ソース システムで後続の更新が行われるまで "無効" アクションが遅延する可能性があります。 |
| **解決** | 決定論的でポリシーに基づいたオフボードをサポートするには、[Microsoft Entra ID ガバナンスのライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)を使用します。 このモデルは、時間ベースのイベントではなく、状態に基づいています。 [SuccessFactors の従業員の `endDate`](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes) を (たとえば、`employeeLeaveDateTime` 属性を使用して) Microsoft Entraに同期します。 その後、組織はディレクトリの状態から直接自動オフボード ワークフローをトリガーできます。人事システムでの増分変更検出とは無関係に、雇用終了日に達したときにアカウントを正確に無効にすることができます。 |

この方法では、次のことが可能になります。

- タイムリーで予測可能なユーザーのための退去手続き。
- 人事の意図に合わせたポリシー ベースの自動化。
- カスタム スクリプトまたは手動による介入への依存を減らしました。
- ハイブリッド ID とクラウド ID 全体の一元的なライフサイクル ガバナンス。

[Lifecycle Workflows](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) はMicrosoft Entra ID ガバナンスの一部であり、ディレクトリ内の権限のある ID 状態に基づいて joiner-mover-leaver ポリシーを適用するために特別に設計されています。

### 特定の属性タイプに対する不要な更新

**適用対象:**

- Workday からオンプレミスのActive Directoryにユーザーをプロビジョニングする
- Workday から Microsoft Entra へのユーザー プロビジョニング
- SAP SuccessFactorsからオンプレミスのActive Directoryへのユーザー プロビジョニング
- SAP SuccessFactorsからMicrosoft Entraへのユーザープロビジョニング
- オンプレミスの Active Directoryへの API 駆動型プロビジョニング
- Microsoft Entra IDへの API 駆動型プロビジョニング

| トラブルシューティング | 詳細 |
| --- | --- |
| **問題** | プロビジョニング ログには、ソース データに意味のある変更がない場合でも、特定の属性に対する更新操作が繰り返し表示されます。 この動作は、一般的に、複数値属性、カスタム セキュリティ属性、および `accountEnabled` や `accountDisabled`などの派生アカウントの状態属性で観察されます。 |
| **原因** | 特定の属性の種類では、プロビジョニング エンジンは、以前にプロビジョニングされた状態と安定した比較を実行するのではなく、実行時に値を評価します。 このランタイム評価モデルにより、同期サイクル中にこれらの属性が再処理または書き込まれる可能性があります。これにより、プロビジョニング ログに冗長な更新エントリが生成される可能性があります。 |
| **解決** | 現在、解決策はありません。 この動作は既知の制限事項です。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/hr-writeback-issues"} -->
## HR プロビジョニングでの書き戻しに関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-writeback-issues
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: この記事では、HR プロビジョニングに関する書き戻しの問題をトラブルシューティングできるように、潜在的な問題と解決策について説明します。

### Null 値と空の値が予期したとおりに処理されませんでした

**適用対象:**

- Workday Writeback
- SAP SuccessFactors Writeback

| トラブルシューティング | 説明 |
| --- | --- |
| **問題** | 書き戻しアプリが正常に構成されました。 Microsoft Entra ID から null または空の値を取得しています。 プロビジョニング サービスは、HR アプリで対応する電子メールまたは電話番号の値をクリアする必要があります。 しかし、操作は失敗します。 |
| **原因** | プロビジョニング サービスには、null 値処理の既定のロジックがありません。 プロビジョニング サービスは、ソース アプリから空の文字列を取得すると、その値を対象アプリに "その他" としてフローしようとします。 Workday または SuccessFactors が空の値を処理できない場合は、エラーが返されます。 |
| **解決** | 次の推奨に従って、式マッピングを使用するように属性マッピングを更新します。 |

**推奨される解決方法**

たとえば、SAP SuccessFactors 属性 `businessPhoneNumber` にマッピングされている属性 `telephoneNumber` は、Microsoft Entra ID では null または空です。

- オプション 1: [IIF、](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#iif)[IsNullOrEmpty、](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#isnullorempty)[Coalesce](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#coalesce)、[IsPresent](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#ispresent) などの関数を使用する空または null の値をチェックする式を定義し、空でないリテラル値 (この場合は 000-000-0000 など) を渡します。

    `IIF(IsNullOrEmpty([telephoneNumber]),"000-000-0000",[telephoneNumber])`
- オプション 2: 関数 [IgnoreFlowIfNullOrEmpty](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#ignoreflowifnullorempty) を使用して、SuccessFactors に送信されるペイロード内の空の属性または null 属性を削除します。

    `IgnoreFlowIfNullOrEmpty([telephoneNumber])`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-concepts"} -->
## API 駆動型インバウンド プロビジョニングの概念 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts
- Service: entra-id / app-provisioning
- Article date: 2025-07-24
- Summary: API 駆動型インバウンド プロビジョニングの概要。

このドキュメントでは、Microsoft Entra API 駆動型のインバウンド ユーザー プロビジョニングの概念的概要についてご説明します。

### Introduction

今日の企業は、様々な権威ある記録システムを持っています。 エンドツーエンドの ID ライフサイクルを確立し、セキュリティ ポスチャを強化し、規制に準拠し続けるためには、Microsoft Entra ID の ID データを、これらのレコード システムで管理されている従業員データと同期する必要があります。 *レコード システム*とは、人事アプリ、給与計算アプリ、スプレッドシート、またはオンプレミスまたはクラウドでホストされているデータベース内の SQL テーブルです。

API 駆動型の受信プロビジョニングでは、Microsoft Entra プロビジョニング サービスが*任意*のレコード システムとの統合をサポートするようになりました。 顧客とパートナーは、*任意*の自動化ツールを使用して、記録システムから従業員データを取得し、Microsoft Entra IDに取り込むことができます。 IT 管理者は、属性マッピングを使用してデータを処理および変換する方法を完全に制御できます。 Microsoft Entra IDで従業員データを利用できるようになったら、IT 管理者は[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)を使用して適切な joiner-mover-leaver ビジネス プロセスを構成できます。

### サポートされているシナリオ

いくつかのインバウンド ユーザー プロビジョニング シナリオは、API 駆動型のインバウンド プロビジョニングを使用して有効化されています。 この図は、最も一般的なシナリオを示しています。

[Image: API ワークフローのシナリオを示す図。]

#### シナリオ 1: IT チームが任意の自動化ツールを使用して人事データ抽出をインポートできるようにする

フラット ファイル、CSV ファイル、SQL ステージング テーブルは、エンタープライズ統合シナリオでよく使用されます。 従業員、請負業者、ベンダーの情報は、これらの形式のいずれかに定期的にエクスポートされ、自動化ツールが使用してこのデータをエンタープライズ ID ディレクトリと同期します。 API 主導のインバウンド プロビジョニングを使用すると、IT チームは任意の自動化ツール (PowerShell スクリプトや Azure Logic Apps など) を使用して、この統合を最新化および簡略化できます。

#### シナリオ 2: ISV を有効にして Microsoft Entra ID との直接統合を構築する

API 駆動型のインバウンド プロビジョニングを使用すると、HR ISV はネイティブ同期エクスペリエンスを送信して、HR システムでの変更が Microsoft Entra ID および接続されたオンプレミス Active Directoryドメインに自動的に流れ込むようににすることができます。 たとえば、人事アプリや学生情報システム アプリは、トランザクションが完了するとすぐ、または 1 日の終わりの一括更新として Microsoft Entra ID にデータを送信できます。

#### シナリオ 3: システム インテグレーターがレコード システムに対してより多くのコネクタを構築できるようにする

パートナーは、カスタム HR コネクタを構築することで、レコード システムから Microsoft Entra ID へのデータ フローに関するさまざまな統合要件を満たすことができます。

上記のすべてのシナリオにおいて統合が簡素化されます。これは、Microsoft Entra プロビジョニング サービスが、ID プロファイル比較の実行、IT 管理者が設定したスコープ ロジックへのデータ同期の制限、Microsoft Entra 管理センターで管理されるルールベースの属性フローと変換の実行を引き継ぐためです。

### エンドツーエンドのフロー

[Image: インバウンド プロビジョニングのエンド ツー エンド ワークフローの図。]

#### ワークフローの手順

1. IT 管理では、Microsoft Entra Enterprise App ギャラリーから [API 駆動型インバウンド ユーザー プロビジョニング アプリ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)を構成します。
2. IT 管理は、API 開発者/パートナー/システム インテグレーターに[アクセス許可を付与し](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access)、エンドポイント アクセスの詳細を提供します。
3. API 開発者/パートナー/システム インテグレーターは、信頼できる ID データを Microsoft Entra ID に送信する API クライアントを構築します。
4. API クライアントは、信頼できるソースから ID データを読み取ります。
5. API クライアントは、プロビジョニング アプリに関連付けられているプロビジョニング [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントに POST 要求を送信します。
    Note

    API クライアントは、呼び出す操作 (作成、更新、有効化、無効化) を決定するために、ソース属性とターゲット属性値の比較を実行する必要はありません。 これは、プロビジョニング サービスによって自動的に処理されます。 API クライアントは、ソース システムから読み取られた ID データをSCIM スキーマ コンストラクトを使用して一括要求としてパッケージ化してアップロードするだけです。
6. 成功した場合は、`Accepted 202 Status` が返されます。
7. Microsoft Entra プロビジョニング サービスは、受信したデータを処理し、属性マッピング規則を適用して、ユーザー プロビジョニングを完了します。
8. 構成されるプロビジョニング アプリに応じて、ユーザーはオンプレミスの Active Directory (ハイブリッド ユーザーの場合) または Microsoft Entra ID (クラウド専用ユーザーの場合) のいずれかにプロビジョニングされます。
9. 次に、API クライアントはプロビジョニング ログ API エンドポイントに対して、送信された各レコードの状態を照会します。
10. レコードの処理が失敗した場合、API クライアントはエラーの詳細をチェックし、失敗した操作に対応するレコードを次の一括要求に含めることができます (手順 5)。
11. IT 管理は、いつでもプロビジョニング ログでプロビジョニング ジョブの状態をチェックし、イベントを表示できます。

#### API 駆動型のインバウンド ユーザー プロビジョニングの主な機能

- 有効な OAuth トークンを使用してアクセスされる*非同期*Microsoft Graph プロビジョニング [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントを公開するプロビジョニング アプリとして使用できます。
- テナント管理者は、このプロビジョニング アプリと対話する API クライアントに、Graph のアクセス許可 `SynchronizationData-User.Upload`、 `SynchronizationData-User.Upload.OwnedBy` (ISV の場合)、および `ProvisioningLog.Read.All`を付与する必要があります。
- Graph API エンドポイントは、SCIM スキーマ コンストラクトを使用して有効な一括要求ペイロードを受け入れます。
- SCIM スキーマ拡張機能を使用すると、一括要求ペイロード内の任意の属性を送信できます。
- 要求ペイロードに null または空の値が含まれている場合は [、属性値 (プレビュー) をクリア](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values) するようにプロビジョニングを構成できます。
- 完全同期と差分同期の両方について、すべての一括要求に完全なユーザー レコードを含めます。 [属性値のクリア (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values) が有効になっている場合、マップされた属性の null 値または空の値は、ターゲット システム内の対応する値をクリアします。 NULL フローが有効になっている属性を省略した場合も、クリアが行われる可能性があります。 したがって、不完全なペイロードは、既存の属性値を意図せずにクリアする可能性があります。 明確な JSON `null` または空の文字列を使用して、確定的なクリアを実行します。
- `/bulkUpload`API エンドポイントでは、次の調整制限が適用されます。
    - 任意の 5 秒間に 40 回の API 呼び出しに制限があります。 このしきい値を超えた場合、サービスは HTTP 429 (要求が多すぎます) 応答を返します。 調整を回避するには、クライアントにペーシング ロジックを実装して、送信間に遅延やレート制限処理を追加するなど、要求をスペースアウトします。
    - テナント レベルの制限は、Entra ID P1/P2 ライセンスでは 24 時間あたり 2,000 回、Entra ID ガバナンス ライセンスでは 6,000 回の API 呼び出しです。 これらの制限を超えると、HTTP 429 (要求が多すぎます) 応答が発生します。 クォータ内を維持するには、API 呼び出しごとに最大 50 個の操作を含むように SCIM の一括ペイロードが最適化されていることを確認します。
- 各 API エンドポイントは、Microsoft Entra ID の特定のプロビジョニング アプリに関連付けられています。 データ ソースごとにプロビジョニング アプリを作成することで、複数のデータ ソースを統合できます。
- 受信される一括要求ペイロードは、ほぼリアルタイムで処理されます。
- 管理者は、プロビジョニング ログを表示することで [、プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)の進行状況を確認できます。
- API クライアントは、[プロビジョニング ログ API](https://learn.microsoft.com/ja-jp/graph/api/resources/provisioningobjectsummary) のクエリを実行して進行状況を追跡できます。

#### ライセンス要件

この機能は、Microsoft Entra ID P1、P2、Microsoft Entra ID ガバナンスのライセンスで使用できます。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://go.microsoft.com/fwlink/?linkid=2262973)」をご覧ください。

#### API 使用に関するガイダンス

`/bulkUpload` API エンドポイントは、Microsoft Entra ID でユーザーを管理できる方法の数を拡張します。 `/bulkUpload` API エンドポイントが統合シナリオに適しているかどうかを判断するには、この表を参照して、他の API ベースの統合オプションと比較してください。

| ユース ケース シナリオから API へのマッピング | ユーザーの作成 API | HR 受信一括 API | ユーザー招待 API | 直接割り当て API |
| --- | --- | --- | --- | --- |
| *ID の作成シナリオが...の場合* | HR ソースのワーカーに関連付けられていないユーザーに対する Microsoft Entra ID でのアドホック ユーザーの作成 | 権限のある人事ソースから従業員レコードを調達し、それらの従業員に Microsoft Entra ID またはオンプレミスの Active Directory の "メンバー" アカウントを持たせる必要がある | ゲストが一意のアクセス権を持つ共有目的で、Microsoft Entra ID でのアドホック ゲスト ユーザーの作成 | 既存のユーザーに対するアクセス割り当て、および Microsoft Entra ID でのゲスト作成 (プレビュー) により、新しいゲストに標準化されたアクセス権を付与する |
| *...API を使用...* | [ユーザーの作成](https://go.microsoft.com/fwlink/?linkid=2261811) | [bulkUpload を実行](https://go.microsoft.com/fwlink/?linkid=2261471)します。 | [招待を作成する](https://go.microsoft.com/fwlink/?linkid=2261635) | [accessPackageAssignmentRequest の作成](https://learn.microsoft.com/ja-jp/graph/api/entitlementmanagement-post-assignmentrequests?view=graph-rest-1.0&tabs=http&preserve-view=true) |
| *結果のユーザーはまず...に作成されます* | Microsoft Entra ID | オンプレミスの Active Directory または Microsoft Entra ID | Microsoft Entra ID | Microsoft Entra ID |
| *結果のユーザーは...に対して認証を行います* | 指定したパスワードを含む Microsoft Entra ID | [Entra ライフサイクル ワークフローによって提供される一時アクセス パス](https://go.microsoft.com/fwlink/?linkid=2261542)のある Microsoft Entra ID のオンプレミスの Active Directory | ホーム テナントまたはその他の ID プロバイダー | ホーム テナントまたはその他の ID プロバイダー |
| *ユーザーに対する後続の更新は、以下を経由して行えます* | Graph API または Microsoft Entra 管理センター | Graph API または HR 受信一括 API または Microsoft Entra 管理センター | Graph API または Microsoft Entra 管理センター | Graph API または Microsoft Entra 管理センター |
| *雇用開始時のユーザーのライフサイクルは、...によって決定されます* | 手動プロセス | 属性に基づいてトリガーされる `employeeHireDate` | 権利管理 | エンタイトルメント管理アクセス パッケージを使用した[自動割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy) |
| *雇用終了時のユーザーのライフサイクルは、...によって決定されます* | 手動プロセス | 属性に基づいてトリガーされる `employeeLeaveDateTime` | Accessのレビュー | ユーザーが最後のアクセス パッケージ割り当てを失った時のエンタイトルメント管理は削除されます |

#### 推奨のラーニング パス

| # | 学習目標 | Guidance |
| --- | --- | --- |
| 1. | インバウンド プロビジョニング API 仕様の詳細を学習したい。 | [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API 仕様のドキュメントを参照してください。 |
| 2. | API 駆動型プロビジョニングの概念、シナリオ、制限事項についてより理解を深めたい。 | 「[API 駆動インバウンド プロビジョニングに関するよくあるご質問](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-faqs)」を参照してください。 |
| 3. | *管理者ユーザー*は、受信プロビジョニング API をすばやくテストする必要があります。 | \* [API 駆動型インバウンド プロビジョニング アプリ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)を作成する  \* [Graph エクスプローラーを使用して API をテストする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer) |
| 4. | サービス アカウントまたはマネージド ID を使用して、インバウンド プロビジョニング API を迅速にテストしたい。 | \* [API 駆動型インバウンド プロビジョニング アプリ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)を作成する  \* [API アクセス許可を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access)付与する  \* [cURL を使用して API をテストする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-curl-tutorial) |
| 5. | API 駆動型プロビジョニング アプリを拡張して、より多くのカスタム属性を処理したい。 | チュートリアル「[API 主導のプロビジョニングを拡張してカスタム属性を同期する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-custom-attributes)」を参照してください |
| 6. | ソースに値がない場合は、既存のターゲット属性をクリアする必要があります。 | 「 [属性値のクリア (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values)を参照してください。 |
| 7. | レコードのシステムからインバウンド プロビジョニング API エンドポイントへのデータアップロードを自動化したい。 | 以下のチュートリアルを参照してください  \* [PowerShell のクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-powershell) \* [Azure Logic Apps のクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-logic-apps) |
| 8. | 受信プロビジョニング API の問題をトラブルシューティングする必要があります。 | [トラブルシューティング ガイドを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-issues)参照してください。 |

#### 外部学習リソース

パートナーと Microsoft MVP によって作成された次のコンテンツでは、さまざまな統合シナリオで API 駆動型プロビジョニングをデプロイおよび構成する方法に関する追加のガイダンスが提供されています。

- ビデオ チュートリアル

    - John Savill が [API 駆動型プロビジョニングのしくみ](https://www.youtube.com/watch?v=olkOYEyJB1o)について説明します
    - Microsoft MVP Nick Ross が [API 駆動型プロビジョニングを構成する方法](https://www.youtube.com/watch?v=4FLEroQ8zmQ)について説明します
    - Microsoft MVP Nick Ross が、[Power Automate と API 駆動型プロビジョニングを使用して SharePoint の Excel ファイルから HR データをソース化する方法](https://www.youtube.com/watch?v=QN6SsamvS9c)について説明します
    - [API 駆動のプロビジョニングに関する Microsoft パートナー IdentityXP の 4 部構成シリーズ](https://www.youtube.com/watch?v=7ZPDnhwKz_w)
- ブログの投稿、プレゼンテーション、その他の便利なリンク

    - Microsoft MVP Christian Frohn が[、Azure SQL データベースで API 駆動型プロビジョニングを真実のソースとして使用する](https://www.christianfrohn.dk/2024/05/15/using-api-driven-user-provisioning-with-an-azure-sql-database-as-source-of-truth/)方法について説明します
    - [オンプレミスの Active Directory に Bamboo HR API 駆動型プロビジョニングを行う方法](https://identity-man.eu/2023/10/25/using-the-brand-new-entra-inbound-provisioning-api-for-identity-lifecycle-management/)を説明する Microsoft MVP の Pim Jacob の記事
    - [API 駆動型のプロビジョニングとライフサイクル ワークフローを使用して参加と離職者のプロセスを構成する方法](https://github.com/IdentityMan/presentations/blob/main/DutchMicrosoftSecurityMeetup/Dutch%20SecMeetup%20-%20Securing%20Joiner%20&amp;%20Leaver%20process%20with%20Inbound%20Provisioning%20and%20LCW.pdf)に関する Microsoft MVP の Pim Jacob のプレゼンテーション
    - [PowerShell スクリプトと API 駆動型プロビジョニングを使用して Excel データをソース化する方法](https://goodworkaround.com/2023/08/01/testing-out-the-entra-id-inbound-provisioning-api/)について説明する Microsoft MVP Marius Solbakken の記事
    - [カスタム GitHub Action を使用して API 駆動プロビジョニングを呼び出す方法](https://suryendub.github.io/2023-11-25-github-action-for-inbound-api-provisioning/)に関する Suryendu Bhattacharyya の記事
    - Microsoft MVP の Jan Vidal Elven の [API 駆動型プロビジョニング用の Bicep テンプレート](https://github.com/JanVidarElven/ExpertsLiveEurope2023/tree/main/EntraIDGovernance/Elven%20Inbound%20Provisioning%20API)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-configure-app"} -->
## API 駆動型インバウンド プロビジョニング アプリを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: API 駆動型インバウンド プロビジョニング アプリを構成する方法について説明します。

### 概要

このチュートリアルでは、 [API 主導の受信ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)を構成する方法について説明します。

この機能は、次のエンタープライズ ギャラリー アプリを構成するときにのみ使用できます。

- Microsoft Entra ID への API 駆動型インバウンド ユーザー プロビジョニング
- オンプレミス AD への API 駆動型インバウンド ユーザー プロビジョニング

### 前提条件

このチュートリアルの手順を完了するには、次のロールで Microsoft Entra 管理センターにアクセスする必要があります。

- [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) (Microsoft Entra ID への受信ユーザー プロビジョニングを構成している場合) または
- [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) + [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) (オンプレミス Active Directory への受信ユーザー プロビジョニングを構成している場合)

オンプレミス Active Directory へのインバウンド ユーザー プロビジョニングを構成する場合は、Active Directory ドメイン コントローラーに接続するためのプロビジョニング エージェントをインストールできる Windows Server へのアクセス権が必要です。

### API 駆動型プロビジョニング アプリを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://go.microsoft.com/fwlink/?linkid=2247823)としてログインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. [ **新しいアプリケーション** ] をクリックして、新しいプロビジョニング アプリケーションを作成します。 [Image: Microsoft Entra 管理センターのスクリーンショット。]
4. 検索フィールドに **「API ドリブン** 」と入力し、セットアップ用のアプリケーションを選択します。

    - **オンプレミス Active Directory への API 駆動型プロビジョニング**: レコードシステムからハイブリッド ID (オンプレミス AD と Microsoft Entra アカウントの両方を必要とする ID) をプロビジョニングする場合は、このアプリを選択します。 これらのアカウントがオンプレミス AD でプロビジョニングされると、Microsoft Entra Connect Sync または Microsoft Entra Connect クラウド同期を使用して Microsoft Entra テナントに自動的に同期されます。
    - **Microsoft Entra ID への API 駆動型プロビジョニング**: 記録システムからクラウドのみの ID (オンプレミスの AD アカウントを必要とせず、Microsoft Entra アカウントのみが必要な ID) をプロビジョニングする場合は、このアプリを選択します。

    [Image: API 駆動型プロビジョニング アプリのスクリーンショット。]
5. [ **名前** ] フィールドで、名前付け要件を満たすようにアプリケーションの名前を変更し、[ **作成**] をクリックします。

    [Image: アプリの作成のスクリーンショット。]

    注

    複数のソースからデータを取り込む予定で、それぞれ独自の同期規則を使用する場合は、複数のアプリを作成して、それぞれアプリにわかりやすい名前 (`Provision-Employees-From-CSV-to-AD` や `Provision-Contractors-From-SQL-to-AD` など) を付けることができます。
6. アプリケーションの作成が成功したら、[プロビジョニング] ブレードに移動し、[作業の **開始**] をクリックします。 [Image: [作業の開始] ボタンのスクリーンショット。]
7. プロビジョニング モードを手動から **自動**に切り替えます。

選択したアプリに応じて、次のいずれかのセクションを使用してセットアップを完了します。

- オンプレミス AD への API 主導の受信プロビジョニングを構成する
- Microsoft Entra ID への API 駆動型の受信プロビジョニングを構成する

### オンプレミス AD への API 駆動型インバウンド プロビジョニングを構成する

1. [プロビジョニング モード] を **[自動**] に設定した後、[ **保存** ] をクリックして、プロビジョニング ジョブの初期構成を作成します。
2. Microsoft Entra プロビジョニング エージェントに関する情報バナーをクリックします。
3. [ **同意する] を** クリックして、Microsoft Entra プロビジョニング エージェントをダウンロードします。
4. [プロビジョニング エージェントをインストールして構成](https://go.microsoft.com/fwlink/?linkid=2241216)するには、ここに記載されている手順を参照してください。 この手順により、オンプレミス Active Directory ドメインが Microsoft Entra テナントに登録されます。
5. エージェントの登録が成功したら、ドロップダウン **Active Directory ドメイン** でドメインを選択し、新しいユーザー アカウントが既定で作成される OU の識別名を指定します。
    注

    AD ドメインが **[Active Directory ドメイン** ] ドロップダウン リストに表示されない場合は、ブラウザーでプロビジョニング アプリを再読み込みします。 ドメインの **オンプレミス エージェントの表示** をクリックして、エージェントの状態が正常であることを確認します。
6. [ **テスト接続** ] をクリックして、Microsoft Entra ID がプロビジョニング エージェントに接続できることを確認します。
7. [ **保存** ] をクリックして変更を保存します。
8. 保存操作が成功すると、さらに 2 つの拡張パネル ( **マッピング** 用と **設定**用) が表示されます。 次の手順に進む前に、有効な通知用メール ID を指定して、構成をもう一度保存してください。
    注

    **[設定]** で**通知メール**を指定することは必須です。 [通知用メール] が空欄のままの場合、実行を開始するとプロビジョニングは検疫状態になります。
9. [ **マッピング** ] 拡張パネルでハイパーリンクをクリックして、既定の属性マッピングを表示します。
    注

    **[属性マッピング]** ページの既定の構成では、SCIM Core ユーザー属性とエンタープライズ ユーザー属性がオンプレミスの AD 属性にマップされます。 既定のマッピングを使用して開始し、全体的なデータ フローに慣れてきたら、後でカスタマイズすることをお勧めします。
10. 「プロビジョニング要求の受け入れ開始」セクションの手順に従って、構成を完了します。

### Microsoft Entra ID への API 駆動型インバウンド ユーザー プロビジョニングを構成する

1. [プロビジョニング モード] を **[自動**] に設定した後、[ **保存** ] をクリックして、プロビジョニング ジョブの初期構成を作成します。
2. 保存操作が成功すると、さらに 2 つの拡張パネル ( **マッピング** 用と **設定**用) が表示されます。 次の手順に進む前に、有効な通知用メール ID を指定して、構成をもう一度保存してください。

    注

    **[設定]** で**通知メール**を指定することは必須です。 [通知用メール] が空欄のままの場合、実行を開始するとプロビジョニングは検疫状態になります。
3. [ **マッピング** ] 拡張パネルでハイパーリンクをクリックして、既定の属性マッピングを表示します。

    注

    **[属性マッピング]** ページの既定の構成では、SCIM Core ユーザー属性とエンタープライズ ユーザー属性がオンプレミスの AD 属性にマップされます。 既定のマッピングを使用して開始し、全体的なデータ フローに慣れてきたら、後でカスタマイズすることをお勧めします。
4. 「プロビジョニング要求の受け入れ開始」セクションの手順に従って、構成を完了します。

### プロビジョニング要求の受け入れを開始する

1. プロビジョニング アプリケーションの [プロビジョニング&gt;] ページ**を**開きます。
2. このページでは、以下の操作を実行できます。
    - **[プロビジョニング制御の開始** ] ボタン – このボタンをクリックすると、プロビジョニング ジョブが **リッスン モード** で配置され、受信一括アップロード要求ペイロードが処理されます。
    - **[プロビジョニング制御の停止** ] ボタン – このオプションを使用して、プロビジョニング ジョブを一時停止または停止します。
    - **[プロビジョニング制御の再起動** ] ボタン – このオプションを使用して、処理が保留中の既存の要求ペイロードを消去し、新しいプロビジョニング サイクルを開始します。
    - **[プロビジョニング制御の編集** ] ボタン – このオプションを使用して、ジョブ設定、属性マッピングを編集し、プロビジョニング スキーマをカスタマイズします。
    - **オンデマンドプロビジョニング制御ボタン** – この機能は、API ドリブンのインバウンドプロビジョニングではサポートされていません。
    - **プロビジョニング API エンドポイント** URL テキスト – 表示されている HTTPS URL 値をコピーし、後で API クライアントで使用するためにメモ帳または OneNote に保存します。
3. [統計] を**現在まで**展開し&gt;**[技術情報**] パネルを開き、[**プロビジョニング API エンドポイント**の URL] をコピーします。 API を呼び出す [アクセス許可を付与](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access) した後、この URL を API 開発者と共有します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-curl-tutorial"} -->
## cURL を使用した API 主導の受信プロビジョニングのクイック スタート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-curl-tutorial
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: このチュートリアルでは、cURL を使用して API 主導の受信プロビジョニングを開始できるように、手順を追って説明します。

### 紹介

[cURL](https://curl.se/) は、API 開発者が使用する一般的な無料のオープンソースのコマンド ライン ツールであり、 [Windows 10/11 では既定で使用できます](https://curl.se/windows/microsoft.html)。 このチュートリアルでは、cURL を使用して [API 主導の受信プロビジョニングをすばやくテストする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) 方法について説明します。

### 前提条件

- [API ドリブンの受信プロビジョニング アプリを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)構成しました。
- サービス プリンシパルを構成し、受信プロビジョニング API へのアクセス権を持っています。 このチュートリアルで使用するサービス プリンシパル アプリの `ClientId` と `ClientSecret` を書き留めておきます。

### 受信プロビジョニング API にユーザー データをアップロードする

1. 受信プロビジョニング API にアクセスできるサービス プリンシパルの**client\_id**と**client\_secret**を取得します。
2. OAuth **client\_credentials** 許可フローを使用してアクセス トークンを取得します。 変数 `[yourClientId]`、`[yourClientSecret]`、`[yourTenantId]` をセットアップに適用できる値に置き換え、次の cURL コマンドを実行します。 生成されたアクセス トークン値をコピーする

    ```
    curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d "client_id=[yourClientId]&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default&client_secret=[yourClientSecret]&grant_type=client_credentials" "https://login.microsoftonline.com/[yourTenantId]/oauth2/v2.0/token"
    ```
3. SCIM エンタープライズ ユーザー スキーマを使用して一括要求をコピーし、その内容を `scim-bulk-upload-users.json` という名前のファイルに保存します。
4. 変数 `[InboundProvisioningAPIEndpoint]` を、プロビジョニング アプリに関連付けられているプロビジョニング API エンドポイントに置き換えます。 前の手順の `[AccessToken]` 値を使用し、次の curl コマンドを実行して、プロビジョニング API エンドポイントに一括要求をアップロードします。

    ```
    curl -v "[InboundProvisioningAPIEndpoint]" -d @scim-bulk-upload-users.json -H "Authorization: Bearer [AccessToken]" -H "Content-Type: application/scim+json"
    ```
5. アップロードが成功すると、HTTP 202 Accepted 応答コードを受け取ります。
6. プロビジョニング サービスは一括要求ペイロードの処理を直ちに開始し、受信プロビジョニング アプリのプロビジョニング ログにアクセスすることでプロビジョニングの詳細を確認できます。

### 一括要求ペイロードの処理を確認する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://go.microsoft.com/fwlink/?linkid=2247823)としてログインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. すべてのアプリケーションで、検索フィルター テキスト ボックスを使用して、API 駆動型プロビジョニング アプリケーションを検索して開きます。
4. [プロビジョニング] ブレードを開きます。 ランディング ページには、最後の実行の状態が表示されます。
5. [ **プロビジョニング ログの表示]** を選択して、[プロビジョニング ログ] ブレードを開きます。 または、メニュー オプション [**モニター**]、[&gt;] を選択することもできます。

    [Image: メニューのプロビジョニング ログのスクリーンショット。]
6. プロビジョニング ログ内の任意のレコードを選択して、処理の詳細を表示します。
7. プロビジョニング ログの詳細画面には、特定のユーザーに対して実行されたすべての手順が表示されます。

    - [ **API からのインポート** ] ステップで、一括要求から抽出されたユーザー データの詳細を確認します。
    - **[ユーザーの照合**] ステップには、一致する識別子に基づいて、ユーザーの一致の詳細が表示されます。 ユーザーが一致した場合、プロビジョニング サービスは更新操作を実行します。 ユーザーが一致しない場合、プロビジョニング サービスは作成操作を実行します。
    - [ **ユーザーがスコープ内にあるかどうかを確認** する] ステップには、スコープ フィルターの評価の詳細が表示されます。 既定では、すべてのユーザーが処理されます。 スコープ フィルターを設定した場合 (たとえば、営業部門に属するユーザーのみを処理する)、スコープ フィルターの評価の詳細がこの手順で表示されます。
    - [ **ユーザーのプロビジョニング]** ステップでは、最後の処理手順と、ユーザー アカウントに適用された変更が呼び出されます。
    - [ **変更されたプロパティ** ] タブを使用して、属性の更新を表示します。

### 付録

SCIM エンタープライズ ユーザー スキーマを使用した一括要求の詳細については、このセクションを参照してください。

#### SCIM Enterprise ユーザー スキーマを使用した一括要求

次に示す一括要求では、SCIM 標準コア ユーザー スキーマとエンタープライズ ユーザー スキーマが使用されます。

**リクエストボディ**

```http
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:BulkRequest"],
    "Operations": [
    {
        "method": "POST",
        "bulkId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
        "path": "/Users",
        "data": {
            "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"],
            "externalId": "701984",
            "userName": "bjensen@example.com",
            "name": {
                "formatted": "Ms. Barbara J Jensen, III",
                "familyName": "Jensen",
                "givenName": "Barbara",
                "middleName": "Jane",
                "honorificPrefix": "Ms.",
                "honorificSuffix": "III"
            },
            "displayName": "Babs Jensen",
            "nickName": "Babs",
            "emails": [
            {
              "value": "bjensen@example.com",
              "type": "work",
              "primary": true
            }
            ],
            "addresses": [
            {
              "type": "work",
              "streetAddress": "100 Universal City Plaza",
              "locality": "Hollywood",
              "region": "CA",
              "postalCode": "91608",
              "country": "USA",
              "formatted": "100 Universal City Plaza\nHollywood, CA 91608 USA",
              "primary": true
            }
            ],
            "phoneNumbers": [
            {
              "value": "555-555-5555",
              "type": "work"
            }
            ],
            "userType": "Employee",
            "title": "Tour Guide",
            "preferredLanguage": "en-US",
            "locale": "en-US",
            "timezone": "America/Los_Angeles",
            "active":true,
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
                 "employeeNumber": "701984",
                 "costCenter": "4130",
                 "organization": "Universal Studios",
                 "division": "Theme Park",
                 "department": "Tour Operations",
                 "manager": {
                     "value": "89607",
                     "displayName": "John Smith"
                 }
            }
        }
    },
    {
        "method": "POST",
        "bulkId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
        "path": "/Users",
        "data": {
            "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"],
            "externalId": "701985",
            "userName": "Kjensen@example.com",
            "name": {
                "formatted": "Ms. Kathy J Jensen, III",
                "familyName": "Jensen",
                "givenName": "Kathy",
                "middleName": "Jane",
                "honorificPrefix": "Ms.",
                "honorificSuffix": "III"
            },
            "displayName": "Kathy Jensen",
            "nickName": "Kathy",
            "emails": [
            {
              "value": "kjensen@example.com",
              "type": "work",
              "primary": true
            }
            ],
            "addresses": [
            {
              "type": "work",
              "streetAddress": "100 Oracle City Plaza",
              "locality": "Hollywood",
              "region": "CA",
              "postalCode": "91618",
              "country": "USA",
              "formatted": "100 Oracle City Plaza\nHollywood, CA 91618 USA",
              "primary": true
            }
            ],
            "phoneNumbers": [
            {
              "value": "555-555-5545",
              "type": "work"
            }
            ],
            "userType": "Employee",
            "title": "Tour Lead",
            "preferredLanguage": "en-US",
            "locale": "en-US",
            "timezone": "America/Los_Angeles",
            "active":true,
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
                 "employeeNumber": "701985",
                 "costCenter": "4130",
                 "organization": "Universal Studios",
                 "division": "Theme Park",
                 "department": "Tour Operations",
                 "manager": {
                     "value": "701984",
                     "displayName": "Barbara Jensen"
                 }
            }
        }
    }
],
    "failOnErrors": null
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-custom-attributes"} -->
## API 駆動型プロビジョニングを拡張してカスタム属性を同期する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-custom-attributes
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: API 駆動型インバウンド プロビジョニングを拡張してカスタム属性を同期する方法について説明します。

既定では、API 駆動型プロビジョニング アプリは、標準の SCIM Core ユーザー スキーマと Enterprise ユーザー スキーマの一部である属性の処理をサポートします。 レコードのシステムには、API 駆動型プロビジョニングの一部として含めたいカスタム属性が含まれている場合があります。 この高度なチュートリアルでは、API 駆動型プロビジョニング アプリを拡張して、追加のカスタム属性を処理する方法について説明します。

注

この高度なシナリオを試す前に、すぐに使用できるプロビジョニング アプリの構成が期待どおりに動作することを、[Graph エクスプローラー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer)、[cURL](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-curl-tutorial) のいずれかの API クライアントを使用して確認することをお勧めします。

### サンプル シナリオ

API 駆動型プロビジョニング アプリの構成が完了しました。 構成したプロビジョニング アプリでは、標準の SCIM Core ユーザー スキーマと Enterprise ユーザー スキーマの一部である属性が正常に使われており、また、Microsoft Entra ID ではユーザーが正常にプロビジョニングされています。 そこで、2 つのカスタム属性 `HireDate` と `JobCode` を HR システムからインバウンド プロビジョニング API エンドポイントに送信したいとします。 これら 2 つのカスタム属性を、Microsoft Entra 属性 `employeeHireDate` と `jobTitle` にマップする必要があります。

### 手順 1 - プロビジョニング アプリ スキーマを拡張する

この手順では、標準の SCIM スキーマに含まれていない 2 つの属性 "HireDate" と "JobCode" をプロビジョニング アプリに追加し、それらをプロビジョニング データ フローで使用します。

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://go.microsoft.com/fwlink/?linkid=2247823)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. API 駆動型プロビジョニング アプリを開きます。
4. **[プロビジョニング]** ブレードを開きます。
5. **[プロビジョニングの編集]** ボタンをクリックします。
6. **[マッピング]** セクション を展開し、属性マッピングのリンクをクリックします。 [Image: 属性マッピング編集のスクリーンショット。]
7. [ **属性マッピング** ] ページで、[ **詳細オプション]** ドロップダウンを選択し、[ **ターゲット ユーザー属性の編集]** を選択します。 [Image: API 属性リストの編集のスクリーンショット。]
8. **[属性リストの編集]** ページの一番下までスクロールします。
9. 次の 2 つの属性を SCIM スキーマ拡張機能としてリストに追加します。 独自の SCIM スキーマ名前空間を使用できます。 `urn:ietf:params:scim:schemas:extension:contoso:1.0:User:HireDate``urn:ietf:params:scim:schemas:extension:contoso:1.0:User:JobCode`[Image: カスタム属性追加のスクリーンショット。]
10. 変更を**保存**します

注

プロビジョニング アプリにいくつかの属性のみを追加する場合は、Microsoft Entra 管理センターを使用してスキーマを拡張します。 より多くのカスタム属性 (たとえば 20 以上の属性など) を追加する場合は、上記の手動プロセスを自動化する [CSV2SCIM PowerShell スクリプトの `UpdateSchema` モードを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-powershell#extending-provisioning-job-schema)することをお勧めします。

### 手順 2 - カスタム属性をマップする

次に、これらの拡張をプロビジョニング アプリ属性マッピングに追加しましょう。

1. **[属性マッピング]** ページの **[新しいマッピングの追加]** リンクをクリックします。 [Image: [新しいマッピングの追加] のスクリーンショット。]
2. `urn:ietf:params:scim:schemas:extension:contoso:1.0:User:HireDate` 属性を `employeeHireDate` にマップします。 **OK** をクリックします。 [Image: HireDate マッピングのスクリーンショット。]
3. 次に、`title` の既存のマッピングを選択し、それをクリックしてマッピングを編集します。
4. 属性マッピングを編集して、`urn:ietf:params:scim:schemas:extension:contoso:1.0:User:JobCode` Microsoft Entra 属性の一部として `jobTitle` を含む式にします。

    ```
      Join("", [title], "(", [urn:ietf:params:scim:schemas:extension:contoso:1.0:User:JobCode], ")")
    ```

    [Image: JobTitle マッピングのスクリーンショット。]

    この式マッピングでは、`title` が "Tour Lead" で `JobCode` が "TL-1001" の場合、Microsoft Entra 属性 `jobTitle` は "Tour Lead (TL-1001)" に設定されます。
5. 属性マッピングを保存します。

### 手順 3 - カスタム属性を含む一括要求をアップロードする

1. API クライアント (Graph エクスプローラー/cURL) を開きます。
2. カスタム属性を含む一括要求をコピーして貼り付けます。
3. プロビジョニング API エンドポイント URL にこの一括要求を送信します。 [Image: 一括アップロード要求のスクリーンショット。]
4. しばらくすると、プロビジョニング ログをチェックして、属性の変更を確認できます。 [Image: プロビジョニング ログのスクリーンショット。]
5. Microsoft Entra ユーザー プロファイルの変更も確認できます。 `Employee hire date` の値は、テナントのタイム ゾーンを反映します。 [Image: ユーザー プロファイル のスクリーンショット。]

### 付録

#### カスタム属性を含む一括要求

この一括要求には、上記の手順で構成されたカスタム属性が含まれます。

**要求本文**

```http
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:BulkRequest"],
    "Operations": [
    {
        "method": "POST",
        "bulkId": "701984",
        "path": "/Users",
        "data": {
            "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
            "urn:ietf:params:scim:schemas:extension:contoso:1.0:User"],
            "externalId": "701984",
            "userName": "bjensen@example.com",
            "name": {
                "formatted": "Ms. Barbara J Jensen, III",
                "familyName": "Jensen",
                "givenName": "Barbara",
                "middleName": "Jane",
                "honorificPrefix": "Ms.",
                "honorificSuffix": "III"
            },
            "displayName": "Babs Jensen",
            "nickName": "Babs",
            "emails": [
            {
              "value": "bjensen@example.com",
              "type": "work",
              "primary": true
            }
            ],
            "addresses": [
            {
              "type": "work",
              "streetAddress": "234300 Universal City Plaza",
              "locality": "Hollywood",
              "region": "CA",
              "postalCode": "91608",
              "country": "USA",
              "formatted": "100 Universal City Plaza\nHollywood, CA 91608 USA",
              "primary": true
            }
            ],
            "phoneNumbers": [
            {
              "value": "555-555-5555",
              "type": "work"
            }
            ],
            "userType": "Employee",
            "title": "Tour Guide",
            "preferredLanguage": "en-US",
            "locale": "en-US",
            "timezone": "America/Los_Angeles",
            "active":true,
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
                "employeeNumber": "701984",
                "costCenter": "4130",
                "organization": "Universal Studios",
                "division": "Theme Park",
                "department": "Tour Operations",
                "manager": {
                  "value": "89607",
                  "displayName": "John Smith"
                 }
            },
            "urn:ietf:params:scim:schemas:extension:contoso:1.0:User": {
                "HireDate": "2021-05-01T00:00:00-05:00",
                "JobCode": "TG-1001"
            }            
        }
    },
    {
        "method": "POST",
        "bulkId": "701985",
        "path": "/Users",
        "data": {
            "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
            "urn:ietf:params:scim:schemas:extension:contoso:1.0:User"],
            "externalId": "701985",
            "userName": "Kjensen@example.com",
            "name": {
                "formatted": "Ms. Kathy J Jensen, III",
                "familyName": "Jensen",
                "givenName": "Kathy",
                "middleName": "Jane",
                "honorificPrefix": "Ms.",
                "honorificSuffix": "III"
            },
            "displayName": "Kathy Jensen",
            "nickName": "Kathy",
            "emails": [
            {
              "value": "kjensen@example.com",
              "type": "work",
              "primary": true
            }
            ],
            "addresses": [
            {
              "type": "work",
              "streetAddress": "100 Oracle City Plaza",
              "locality": "Hollywood",
              "region": "CA",
              "postalCode": "91618",
              "country": "USA",
              "formatted": "100 Oracle City Plaza\nHollywood, CA 91618 USA",
              "primary": true
            }
            ],
            "phoneNumbers": [
            {
              "value": "555-555-5545",
              "type": "work"
            }
            ],
            "userType": "Employee",
            "title": "Tour Lead",
            "preferredLanguage": "en-US",
            "locale": "en-US",
            "timezone": "America/Los_Angeles",
            "active":true,
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
              "employeeNumber": "701984",
              "costCenter": "4130",
              "organization": "Universal Studios",
              "division": "Theme Park",
              "department": "Tour Operations",
              "manager": {
                "value": "701984",
                "displayName": "Barbara Jensen"
             }
            },
            "urn:ietf:params:scim:schemas:extension:contoso:1.0:User": {
                "HireDate": "2022-07-15T00:00:00-05:00",
                "JobCode": "TL-1003"
            }            
        }
    }
],
    "failOnErrors": null
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-faqs"} -->
## API 駆動インバウンド プロビジョニングに関するよくあるご質問 (FAQ) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-faqs
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: API 主導の受信プロビジョニングでサポートされる機能と統合シナリオの詳細について説明します。

この記事では、API 主導の受信プロビジョニングに関してよく寄せられる質問 (FAQ) に回答します。

### 新しい受信プロビジョニング /bulkUpload API と MS Graph Users API の違い

プロビジョニング /bulkUpload API と MS Graph Users API エンドポイントには大きな違いがあります。

- **ペイロード形式**: MS Graph Users API エンドポイントでは、OData 形式のデータが必要です。 新しい受信プロビジョニング /bulkUpload API の要求ペイロード形式では、SCIM スキーマコンストラクトが使用されます。 この API を呼び出すときに、'Content-Type' ヘッダーを `application/scim+json` に設定します。
- **操作の終了結果**:
    - ID データが MS Graph Users API エンドポイントに送信されると、すぐに処理され、Microsoft Entra ユーザー プロファイルで作成/更新/削除操作が実行されます。
    - プロビジョニング /bulkUpload API に送信された要求データは、Microsoft Entra プロビジョニング サービスによって *非同期的に* 処理されます。 プロビジョニング ジョブは、IT 管理者によって構成されたスコープルール、属性マッピング、変換を適用します。これにより、Microsoft Entra ユーザー プロファイルまたはオンプレミス AD ユーザー プロファイルに対する `Create/Update/Delete` 操作が開始されます。
- **IT 管理者は制御を保持**します。API 駆動型の受信プロビジョニングを使用すると、IT 管理者は、受信 ID データを処理して Microsoft Entra 属性にマップする方法をより詳細に制御できます。 ユーザー プロファイルで属性値を設定する前に、特定の種類の ID データ (請負業者データなど) を除外し、変換関数を使用して新しい値を派生させるスコープ ルールを定義できます。

### 受信プロビジョニング /bulkUpload API は標準の SCIM エンドポイントですか?

MS Graph 受信プロビジョニング /bulkUpload API は要求ペイロードで SCIM スキーマを使用しますが、標準化された SCIM API エンドポイント *ではありません* 。 その理由は次のとおりです。

通常、SCIM サービス エンドポイントは、SCIM ペイロードを使用して HTTP 要求 (POST、PUT、GET) を処理し、ID ストアでのそれぞれの操作 (作成、更新、参照) に変換します。 SCIM サービス エンドポイントは、ID を作成、更新、または削除するかどうかの操作セマンティクスを指定する責任を SCIM API クライアントに負わせます。 このメカニズムは、API クライアントが ID ストア内のユーザーに対して実行する操作を認識しているシナリオに適しています。

MS Graph 受信プロビジョニング /bulkUpload は、次の 3 つの固有の要件によって形成された、異なるエンタープライズ ID 統合シナリオを処理するように設計されています。

1. レコードを一括で非同期的に処理する機能 (たとえば、50,000 を超えるレコードの処理)
2. ペイロードに ID 属性を含める機能 (costCenter、pay grade、badgeId など)
3. 操作セマンティクスを認識せずに API クライアントをサポートします。 これらのクライアントは、未加工 *のソース データ* (CSV ファイル内のレコード、SQL テーブル、HR レコードなど) にのみアクセスできる SCIM 以外の API クライアントです。 これらのクライアントには、各レコードを読み取り、ID ストアでの `Create/Update/Delete` の操作セマンティクスを決定する処理機能がありません。

MS Graph 受信プロビジョニング /bulkUpload API の主な目標は、Microsoft Entra プロビジョニング サービスによる最終的な処理のために、任意 *の ID データ* (costCenter、Pay Grade、badgeId など) を *任意* の ID データ (CSV/SQL/HR など) から送信できるようにすることです。 Microsoft Entra プロビジョニング サービスは、このエンドポイントで受信した一括ペイロード データを使用し、IT 管理者によって構成された属性マッピング ルールを適用し、データ ペイロードがターゲット ID ストア (Microsoft Entra ID/オンプレミス AD) で (作成、更新、有効化、無効化) 操作につながるかどうかを判断します。

### プロビジョニング /bulkUpload API は、オンプレミスの Active Directory ドメインをターゲットとしてサポートしていますか?

はい。プロビジョニング API では、ターゲットとしてオンプレミスの AD ドメインがサポートされます。

### プロビジョニング アプリの /bulkUpload API エンドポイントを取得するにはどうすればよいですか?

/bulkUpload API は、"MICROSOFT Entra ID への API 駆動型の受信プロビジョニング" と "オンプレミス Active Directory への API 駆動型受信プロビジョニング" のアプリでのみ使用できます。 プロビジョニング ブレードのホーム ページから、プロビジョニング アプリごとに一意の API エンドポイントを取得できます。 **これまでの統計**&gt;**技術情報を確認する**と、**プロビジョニング API エンドポイント** の URL をコピーしてください。

形式は次のとおりです。

```http
https://graph.microsoft.com/beta/servicePrincipals/{servicePrincipalId}/synchronization/jobs/{jobId}/bulkUpload
```

### プロビジョニング /bulkUpload API を使用して完全同期を実行するにはどうすればよいですか?

完全同期を実行するには、API クライアントを使用して、信頼できるソースから API エンドポイントにすべてのユーザーのデータを一括要求として送信します。 すべてのデータを API エンドポイントに送信すると、次の同期サイクルですべてのユーザー レコードが処理され、プロビジョニング ログ API エンドポイントを使用して進行状況を追跡できます。

### プロビジョニング /bulkUpload API を使用して差分同期を実行するにはどうすればよいですか?

差分同期を実行するには、API クライアントを使用して、信頼されたソースでデータが変更されたユーザーに関する情報のみを送信します。 すべてのデータを API エンドポイントに送信すると、次の同期サイクルですべてのユーザー レコードが処理され、プロビジョニング ログ API エンドポイントを使用して進行状況を追跡できます。

### プロビジョニングの再起動はどのように機能しますか?

必要な場合にのみ、 **プロビジョニングの再開オプションを使用します** 。 ここでは、そのしくみを示します:

**シナリオ 1:** [ **プロビジョニングの再開** ] ボタンをクリックし、ジョブが現在実行中の場合、ジョブは既にステージングされている既存のデータの処理を続行します。 **プロビジョニングの再開**操作は、既存のサイクルを中断しません。 その後のサイクルでは、プロビジョニング サービスによってエスクローがクリアされ、処理のための新しい一括要求が選択されます。

**シナリオ 2:** [ **プロビジョニングの再開** ] ボタンをクリックしてジョブが実行 *されていない* 場合、その後のサイクルを実行する前に、プロビジョニング エンジンは再起動前にアップロードされたデータを消去し、エスクローをクリアし、新しい受信データのみを処理します。

### プロビジョニング /bulkUpload API エンドポイントを使用してユーザーを作成するにはどうすればよいですか?

/bulkUpload API エンドポイントに関連付けられているプロビジョニング ジョブが受信ユーザー ペイロードを処理する方法を次に示します。

1. このジョブは、プロビジョニング ジョブの属性マッピングを取得し、一致する属性ペアを書き留めます (既定では、 API 属性は Microsoft Entra ID のとの照合に使用されます)。
2. この既定の属性照合ペアは変更できます。
3. ジョブは、一括要求ペイロードに存在する各操作を抽出します。
4. ジョブは、要求内の値と一致する識別子 (既定では属性 `externalId`) をチェックし、それを使用して、一致する `employeeId` 値を持つユーザーが Microsoft Entra ID に存在するかどうかを確認します。
5. ジョブで一致するユーザーが見つからない場合、ジョブは同期規則を適用し、ターゲット ディレクトリに新しいユーザーを作成します。

適切なユーザーが Microsoft Entra ID で作成されるようにするには、ソースと Microsoft Entra ID のユーザーを一意に識別する適切な一致する属性ペアをマッピングで定義します。

### UPN の一意の値を生成する方法

プロビジョニング サービスでは、重複する `userPrincipalName` (UPN) を確認して競合を処理する機能は提供されません。 UPN 属性の既定の同期規則で既存の UPN 値が生成された場合、ユーザー作成操作は失敗します。

一意の UPN を生成するために考慮できるオプションを次に示します。

1. API クライアントで一意の UPN 生成のロジックを追加します。
2. [RandomString](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data) 関数を使用するように UPN 属性の同期規則を更新し、マッピングパラメーターの適用を`On object creation only`に設定します。 例：

`Join("", Replace([userName], , "(?<Suffix>@(.)*)", "Suffix", "", , ), RandomString(3, 3, 0, 0, 0, ), "@", DefaultDomain())`

1. オンプレミスの Active Directory にプロビジョニングする場合は、 [SelectUniqueValue](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data) 関数を使用して、マッピングの適用パラメーターを `On object creation only`に設定できます。

### プロビジョニング /bulkUpload API エンドポイントに HR 属性をさらに送信するにはどうすればよいですか?

既定では、API エンドポイントは、SCIM Core ユーザースキーマとエンタープライズ ユーザー スキーマの一部である属性の処理をサポートします。 さらに属性を送信する場合は、プロビジョニング アプリ スキーマを拡張し、新しい属性を Microsoft Entra 属性にマップし、それらの属性を送信するように一括要求を更新します。 [カスタム属性を同期するための API 駆動型プロビジョニングの拡張に関するチュートリアルを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-custom-attributes)参照してください。

### プロビジョニング フローから特定のユーザーを除外するにはどうすればよいですか?

すべてのユーザーを API エンドポイントに送信するが、プロビジョニング フローに特定のユーザーのみを含め、残りのユーザーを除外するシナリオがある場合があります。

これは、 **スコープ フィルターを**使用して実現できます。 プロビジョニング アプリの構成では、スコープ フィルタールールを定義して、特定のユーザーを "包含ルール" (たとえば、部門 EQUALS **Sales** を持つユーザーのみを処理) または "除外ルール" を使用して処理から除外することができます (たとえば、Sales に属するユーザーを除外する、部門 NOT EQUALS **Sales**)。

[スコープ フィルターを使用してプロビジョニングするスコープ ユーザーまたはグループを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。

### プロビジョニング /bulkUpload API エンドポイントを使用してユーザーを更新するにはどうすればよいですか?

/bulkUpload API エンドポイントに関連付けられているプロビジョニング ジョブが受信ユーザー ペイロードを処理する方法を次に示します。

1. プロビジョニング ジョブは、プロビジョニング ジョブの属性マッピングを取得し、一致する属性ペアを書き留めます (既定では、 `externalId` API 属性は Microsoft Entra ID の `employeeId` との照合に使用されます)。 この既定の属性照合ペアは変更できます。
2. ジョブは一括要求ペイロードから操作を抽出します。
3. ジョブは、SCIM 要求の値と一致する識別子 (既定では API 属性 `externalId`) をチェックし、それを使用して、一致する `employeeId` 値を持つユーザーが Microsoft Entra ID に存在するかどうかを確認します。
4. ジョブで一致するユーザーが見つかると、同期規則が適用され、ソース プロファイルとターゲット プロファイルが比較されます。
5. ジョブで一部の値が変更されたと判断された場合は、ディレクトリ内の対応するユーザー レコードが更新されます。

適切なユーザーが Microsoft Entra ID で更新されるようにするには、ソース内のユーザーと Microsoft Entra ID を一意に識別する適切な一致する属性ペアをマッピングで定義してください。

### プロビジョニング /bulkUpload API エンドポイントを使用して既存のユーザー属性をクリアできますか?

はい。API ドリブンの受信プロビジョニングを使用して、既存のユーザー属性をクリアできます。 サポートされているシナリオと構成手順については、「属性値の [クリア (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values)を参照してください。

### プロビジョニング /bulkUpload API エンドポイントを使用してユーザーを削除できますか?

いいえ。API ドリブン プロビジョニング /bulkUpload API エンドポイントは、ユーザー オブジェクトの削除をサポートしていません。 終了またはアカウントの無効化操作の数日後にユーザー オブジェクトの削除 "X" を自動化するには、[Entra IDガバナンス ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#delete-user)を使用してユーザー タスクを削除することをお勧めします。

### API 主導の受信プロビジョニングをサポートする複数のアプリを作成できますか?

はい、できます。 複数のプロビジョニング アプリが必要になるシナリオをいくつか次に示します。

**シナリオ 1:** たとえば、3 つの信頼できるデータ ソースがあるとします。1 つは従業員用、もう 1 つは請負業者用、もう 1 つはベンダー用です。 3 つの個別のプロビジョニング アプリを作成できます。独自の属性マッピングを使用して、ID の種類ごとに 1 つ作成できます。 各プロビジョニング アプリには一意の API エンドポイントがあり、それぞれのペイロードを各エンドポイントに送信できます。

各ジョブの一意の API エンドポイントは、[プロビジョニング] ブレードのホーム ページから取得できます。 **[統計] に移動し**&gt;**技術情報を確認**し、**プロビジョニング API エンドポイント**の URL をコピーします。

**シナリオ 2:** 複数の情報源があり、それぞれに独自の属性が設定されているとします。 たとえば、HR はジョブ情報属性 (jobTitle、employeeType など) を提供し、Badging System はバッジ情報属性 (拡張属性を使用して表される `badgeId` など) を提供します。 このシナリオでは、次の 2 つのプロビジョニング アプリを構成できます。

- HR ソースから属性を受け取り、ユーザーを作成する**プロビジョニング アプリ #1**。
- Badging システムから属性を受け取り、ユーザー属性のみを更新する**プロビジョニング アプリ #2**。 このアプリの属性マッピングはバッジ情報属性に制限され、[ターゲット オブジェクト アクション] では更新のみが有効になります。
- 両方のアプリで同じ一致する識別子ペアが使用されます (`externalId`&lt;-&gt;`employeeId`)

### /bulkUpload API エンドポイントを使用して終了を処理する方法

終了を処理するには、Microsoft Entra ID で `accountEnabled` フラグを設定するために使用されるソース内の属性を特定します。 オンプレミスの Active Directory にプロビジョニングする場合は、そのソース属性を `accountDisabled` 属性にマップします。

既定では、SCIM Core ユーザー スキーマ属性 `active` 関連付けられている値によって、ターゲット ディレクトリ内のユーザーのアカウントの状態が決まります。

属性が **true** に設定されている場合、既定のマッピング 規則によってアカウントが有効になります。 属性が **false** に設定されている場合、既定のマッピング 規則によってアカウントが無効になります。

### ユーザーの誤った無効化/削除を防ぐにはどうすればよいですか?

誤って削除されないようにして回復するには、プロビジョニング アプリで [誤削除しきい値を構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/accidental-deletions) し、 [オンプレミスの Active Directory のごみ箱を有効](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-recycle-bin)にすることをお勧めします。 プロビジョニング アプリの **[属性マッピング]** ブレードの [ **ターゲット オブジェクト アクション** ] で、 **削除** 操作を無効にします。

**削除されたアカウントの回復**

- 操作のターゲット ディレクトリが Microsoft Entra ID の場合、一致したユーザーは論理的に削除されます。 ユーザーは、Microsoft Entra 管理センターの **[削除されたユーザー** ] ページで今後 30 日間表示され、その間に復元できます。
- 操作のターゲット ディレクトリがオンプレミスの Active Directory の場合、一致したユーザーはハード削除されます。 **Active Directory のごみ箱**が有効になっている場合は、削除されたオンプレミス AD ユーザー オブジェクトを復元できます。

### すべての要求で人事システムからすべてのユーザーを送信する必要がありますか?

いいえ、すべてのリクエストで人事システム／「信頼できる情報源」からすべてのユーザーを送信する必要はありません。 作成または更新したいユーザーを送信してください。

### API はすべての HTTP アクション (GET/POST/PUT/PATCH) をサポートしていますか?

いいえ。/bulkUpload プロビジョニング API エンドポイントは POST HTTP アクションのみをサポートします。

### ユーザーを更新する場合は、PUT/PATCH 要求を送信する必要がありますか?

いいえ。API エンドポイントは PUT/PATCH 要求をサポートしていません。 ユーザーを更新するには、POST 一括要求ペイロードでユーザーに関連付けられているデータを送信します。

API エンドポイントによって受信されたデータを処理するプロビジョニング ジョブは、構成された同期規則に基づいて、POST 要求ペイロードで受信したユーザーを作成、更新、有効化、無効化する必要があるかどうかを自動的に検出します。 API クライアントとして、ユーザー プロファイルを更新する場合は、これ以上の手順を実行する必要はありません。

### 書き戻しはどのようにサポートされていますか?

現在の API では、受信データのみがサポートされます。 ここでは、Microsoft Entra ID によって生成された電子メール/ユーザー名/電話などの属性の書き戻しを実装するために考慮すべきいくつかのオプションを示します。これは、人事システムに戻すことができます。

- **オプション 1 – HR ソースを更新する HR エンドポイント/プロキシ サービスへの SCIM 接続**

    - レコードのシステムがユーザー更新用の SCIM エンドポイントを提供する場合は、エンタープライズ アプリ ギャラリーでカスタム SCIM アプリケーションを作成し、 [ドキュメントに記載されているようにプロビジョニングを構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#integrate-your-scim-endpoint-with-the-azure-ad-provisioning-service)できます。
    - レコードのシステムが SCIM エンドポイントを提供していない場合は、更新プログラムを受け取り、変更を HR システムに伝達するプロキシ SCIM サービスを設定する可能性を調べます。
- **オプション 2 – 書き戻しシナリオに Microsoft Entra ECMA コネクタを使用する**

    - 顧客の要件に応じて、ECMA コネクタの 1 つを使用できるかどうかを調べます (PowerShell/SQL/Web サービス)。
- **オプション 3 – Joiner ワークフローでライフサイクル ワークフローカスタム拡張機能タスクを使用する**

    - ライフサイクル ワークフローで、Joiner ワークフローを定義し、 [Logic Apps プロセスを呼び出すカスタム拡張機能タスク](https://go.microsoft.com/fwlink/?linkid=2239990)を定義します。このタスクは、人事システムを更新するか、HR システムによって使用される CSV ファイルを生成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-grant-access"} -->
## インバウンド プロビジョニング API へのアクセスを付与する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: インバウンド プロビジョニング API へのアクセスを付与する方法について説明します。

### はじめに

[API 駆動インバウンド プロビジョニング アプリ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)を構成したら、API クライアントがプロビジョニング [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API に要求を送信し、[プロビジョニング ログ API](https://learn.microsoft.com/ja-jp/graph/api/resources/provisioningobjectsummary) に対してクエリを実行できるように、アクセス許可を付与する必要があります。 このチュートリアルでは、これらの権限を構成する手順について説明します。

お使いの API クライアントが Microsoft Entra ID で認証を行う方法に応じて、次の 2 つの構成オプションから選択できます。

- サービス プリンシパルの構成: お使いの API クライアントで [Microsoft Entra 登録済みアプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-service-principal-portal)のサービス プリンシパルを使用し、OAuth クライアント資格情報付与フローを使用して認証することを計画している場合は、こちらの手順に従ってください。
- マネージド ID の構成: お使いの API クライアントで Microsoft Entra [マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用することを計画している場合は、こちらの手順に従ってください。

### サービス プリンシパルの構成

この構成により、外部 API クライアントを表すアプリが Microsoft Entra ID に登録され、それにインバウンド プロビジョニング API を呼び出す権限が付与されます。 OAuth クライアント資格情報付与フローでは、サービス プリンシパルのクライアント ID とクライアント シークレットを使用できます。

1. 少なくともhttps://entra.microsoft.com のログイン資格情報で Microsoft Entra 管理センター () にログインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. オプション **[新規登録]** をクリックします。
4. アプリ名を指定し、既定のオプションを選択して、**[登録]** をクリックします。 [Image: アプリ登録のスクリーンショット。]
5. [概要] ブレードから **[アプリケーション (クライアント) ID]** と **[ディレクトリ (テナント) ID]** の値をコピーし、後で API クライアントで使用できるように保存します。 [Image: アプリ クライアント ID のスクリーンショット。]
6. アプリのコンテキスト メニューで、**[証明書とシークレット]** オプションを選択します。
7. 新しいクライアント シークレットを作成します。 シークレットと有効期限の説明を入力します。
8. 生成されたクライアント シークレットの値をコピーし、後で API クライアントで使用するために保存します。
9. コンテキスト メニュー **[API 権限]** から、オプション **[権限の追加]** を選択します。
10. **[API 権限の要求]** で、**[Microsoft Graph]** を選択します。
11. **[アプリケーションのアクセス許可]** を選択します。
12. 権限 **[AuditLog.Read.All]** と **[SynchronizationData-User.Upload]** を検索して選択します。
13. 次の画面で **[管理者の同意の付与]** をクリックして、権限の割り当てを完了します。 確認ダイアログで [はい] をクリックします。 アプリには次の権限セットがあるはずです。 [Image: アプリ権限のスクリーンショット。]
14. これで、API クライアントでサービス プリンシパルを使用する準備ができました。
15. 運用環境のワークロードに対しては、[クライアント証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-authenticate-service-principal-powershell)でサービス プリンシパルまたはマネージド ID を使用することをお勧めします。

### マネージド ID の構成

このセクションでは、マネージド ID に必要な権限を割り当てる方法について説明します。

1. Azure リソースで使用する[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を構成します。
2. Microsoft Entra 管理センターからマネージド ID の名前をコピーします。 たとえば、次のスクリーンショットは、"CSV2SCIMBulkUpload" という名前の Azure Logic Apps ワークフローに関連付けられているシステム割り当てマネージド ID の名前を示しています。

    [Image: マネージド ID 名のスクリーンショット。]
3. 次の PowerShell スクリプトを実行して、マネージド ID に権限を割り当てます。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    
    Connect-MgGraph -Scopes "Application.Read.All","AppRoleAssignment.ReadWrite.All,RoleManagement.ReadWrite.Directory"
    $graphApp = Get-MgServicePrincipal -Filter "AppId eq '00000003-0000-0000-c000-000000000000'"
    
    $PermissionName = "SynchronizationData-User.Upload"
    $AppRole = $graphApp.AppRoles | `
    Where-Object {$_.Value -eq $PermissionName -and $_.AllowedMemberTypes -contains "Application"}
    $managedID = Get-MgServicePrincipal -Filter "DisplayName eq 'CSV2SCIMBulkUpload'"
    New-MgServicePrincipalAppRoleAssignment -PrincipalId $managedID.Id -ServicePrincipalId $managedID.Id -ResourceId $graphApp.Id -AppRoleId $AppRole.Id
    
    $PermissionName = "AuditLog.Read.All"
    $AppRole = $graphApp.AppRoles | `
    Where-Object {$_.Value -eq $PermissionName -and $_.AllowedMemberTypes -contains "Application"}
    $managedID = Get-MgServicePrincipal -Filter "DisplayName eq 'CSV2SCIMBulkUpload'"
    New-MgServicePrincipalAppRoleAssignment -PrincipalId $managedID.Id -ServicePrincipalId $managedID.Id -ResourceId $graphApp.Id -AppRoleId $AppRole.Id
    ```
4. 権限が適用されたことを確認するには、Microsoft Entra ID の **[エンタープライズ アプリケーション]** でマネージド ID サービス プリンシパルを見つけます。 **[アプリケーションの種類]** フィルターを削除して、すべてのサービス プリンシパルを表示します。 [Image: マネージド ID プリンシパルのスクリーンショット。]
5. **[セキュリティ]** の **[権限]** ブレードをクリックします。 権限が設定されていることを確認します。 [Image: マネージド ID 権限のスクリーンショット。]
6. これで、API クライアントでマネージド ID を使用する準備ができました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer"} -->
## Graph Explorer を使用した API 駆動型インバウンド プロビジョニングのクイックスタート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer
- Service: entra-id / app-provisioning
- Article date: 2025-07-24
- Summary: Graph Explorer を使用した API 駆動型受信プロビジョニングを素早く開始する方法について説明します

このチュートリアルでは、Microsoft Graph Explorer を使用して [API 主導の受信プロビジョニングをすばやくテストする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) 方法について説明します。

### Prerequisite

- [API ドリブンの受信プロビジョニング アプリを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)構成しました。

Note

このプロビジョニング API は、主にアプリケーションまたはサービス内で使用するためのものです。 テナント管理者は、サービス プリンシパルまたはマネージド ID を構成して、アップロードを実行するアクセス許可を付与できます。 この API には、個別のユーザー割り当て可能な Microsoft Entra 組み込みディレクトリ ロールはありません。 管理者の同意を得て `SynchronizationData-User.Upload` アクセス許可を取得したアプリケーション以外では、 [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) ロールを持つ管理者ユーザーが API を呼び出すことができます。 このチュートリアルでは、テスト セットアップでユーザー管理者ロールを使用して API をテストする方法について説明します。

### ユーザー データを受信プロビジョニング API にアップロードする

1. 新しいブラウザー タブまたはブラウザー ウィンドウを開きます。
2. URL https://aka.ms/ge を起動して Microsoft Graph Explorer にアクセスします。
3. ユーザー プロファイル アイコンを選択してサインインします。

    [Image: ユーザー プロファイル アイコンを示す画像。]
4. [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)ロールアクセス権を持つユーザー アカウントでログイン プロセスを完了します。
5. ログインに成功すると、テナント情報にテナント名が表示されます。

    [Image: テナント名のスクリーンショット。]

    これで、API を呼び出す準備ができました。
6. API 要求パネルで、HTTP 要求の種類を **POST** に設定します。
7. プロビジョニング アプリの概要ページから取得したプロビジョニング API エンドポイントをコピーして貼り付けます。
8. [要求ヘッダー] パネルで、 **Content-Type = application/scim+json** の新しいキー値ペアを追加します。 [Image: 要求ヘッダー パネルのスクリーンショット。]
9. **[要求本文**] パネルで、SCIM Enterprise ユーザー スキーマを使用して一括要求をコピーして貼り付けます
10. [クエリの **実行** ] ボタンを選択して、プロビジョニング API エンドポイントに要求を送信します。
11. 要求が正常に送信されると、API エンドポイントから `Accepted 202` 応答が返されます。
12. **[応答ヘッダー**] パネルを開き、location 属性の URL 値をコピーします。 これは、一括要求に存在するユーザーのプロビジョニング状態をチェックするためにクエリを実行できるプロビジョニング ログの API エンドポイントを指します。

### 一括要求ペイロードの処理を確認する

処理を確認するには、Microsoft Entra 管理センターまたは Graph Explorer を使用します。

#### Microsoft Entra 管理センターから処理を確認する

1. 少なくとも[アプリケーション管理者](https://entra.microsoft.com)のログイン資格情報を使用して[、Microsoft Entra 管理センター](https://go.microsoft.com/fwlink/?linkid=2247823)にログインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. すべてのアプリケーションで、検索フィルター テキスト ボックスを使用して、API 駆動型プロビジョニング アプリケーションを見つけて開きます。
4. [プロビジョニング] ブレードを開きます。 ランディング ページに最後の実行の状態が表示されます。
5. [ **プロビジョニング ログの表示]** を選択して、[プロビジョニング ログ] ブレードを開きます。 または、メニュー オプション **[モニター]**&gt;**[ログのプロビジョニング]** を選択することもできます。

    [Image: メニューのプロビジョニング ログのスクリーンショット。]
6. プロビジョニング ログ内の任意のレコードを選択して、追加の処理の詳細を表示します。
7. プロビジョニング ログの詳細画面に、特定のユーザーに対して実行されたすべての手順が表示されます。

    - [ **API からのインポート** ] ステップで、一括要求から抽出されたユーザー データの詳細を確認します。
    - **[ユーザーの照合**] ステップには、一致する識別子に基づいて、ユーザーの一致の詳細が表示されます。 ユーザーの一致があった場合、プロビジョニング サービスは更新操作を実行します。 ユーザーが一致しない場合、プロビジョニング サービスは作成操作を実行します。
    - [ **ユーザーがスコープ内にあるかどうかを確認** する] ステップには、スコープ フィルターの評価の詳細が表示されます。 既定では、すべてのユーザーが処理されます。 スコープ フィルターを設定した場合 (たとえば、営業部門に属するユーザーのみを処理する)、スコープ フィルターの評価の詳細がこの手順で表示されます。
    - [**ユーザーのプロビジョニング]** ステップでは、最後の処理手順と、ユーザー アカウントに適用された変更が呼び出されます。
    - [ **変更されたプロパティ** ] タブを使用して、属性の更新を表示します。

#### Graph Explorer のプロビジョニング ログ API を使用して処理を確認する

プロビジョニング API 呼び出しの場所応答ヘッダーの一部として返されたプロビジョニング ログの API URL を使用して、処理を検査できます。

1. Graph エクスプローラーの **[要求 URL** ] テキスト ボックスに、プロビジョニング API エンドポイントによって返された場所の URL をコピーして貼り付けます。または、次の形式を使用して構築できます。 `https://graph.microsoft.com/beta/auditLogs/provisioning/?$filter=jobid eq '<jobId>'` プロビジョニング アプリの概要ページから `jobId` を取得できます。
2. **GET** メソッドを使用し、[**クエリの実行**] を選択してプロビジョニング ログを取得します。 既定では、返される応答にはすべてのログ レコードが含まれます。
3. 追加のフィルターを設定することで、特定の期間の後、または特定の状態値を持つデータのみを取得できます。 `https://graph.microsoft.com/beta/auditLogs/provisioning/?$filter=jobid eq '<jobId> and statusInfo/status eq 'failure' and activityDateTime ge 2022-10-10T09:47:34Z` また、ソース アンカー/結合プロパティとして使用されるソース システムで使用される `externalId` 値によって、ユーザーの状態を確認することもできます。 `https://graph.microsoft.com/beta/auditLogs/provisioning/?$filter=jobid eq '<jobId>' and sourceIdentity/id eq '701984'`

### Appendix

#### SCIM エンタープライズ ユーザー スキーマによる一括要求

次の一括要求では、SCIM 標準コア ユーザースキーマとエンタープライズ ユーザー スキーマが使用されます。

**Request body**

```http
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:BulkRequest"],
    "Operations": [
    {
        "method": "POST",
        "bulkId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
        "path": "/Users",
        "data": {
            "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"],
            "externalId": "701984",
            "userName": "bjensen@example.com",
            "name": {
                "formatted": "Ms. Barbara J Jensen, III",
                "familyName": "Jensen",
                "givenName": "Barbara",
                "middleName": "Jane",
                "honorificPrefix": "Ms.",
                "honorificSuffix": "III"
            },
            "displayName": "Babs Jensen",
            "nickName": "Babs",
            "emails": [
            {
              "value": "bjensen@example.com",
              "type": "work",
              "primary": true
            }
            ],
            "addresses": [
            {
              "type": "work",
              "streetAddress": "100 Universal City Plaza",
              "locality": "Hollywood",
              "region": "CA",
              "postalCode": "91608",
              "country": "USA",
              "formatted": "100 Universal City Plaza\nHollywood, CA 91608 USA",
              "primary": true
            }
            ],
            "phoneNumbers": [
            {
              "value": "555-555-5555",
              "type": "work"
            }
            ],
            "userType": "Employee",
            "title": "Tour Guide",
            "preferredLanguage": "en-US",
            "locale": "en-US",
            "timezone": "America/Los_Angeles",
            "active":true,
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
                 "employeeNumber": "701984",
                 "costCenter": "4130",
                 "organization": "Universal Studios",
                 "division": "Theme Park",
                 "department": "Tour Operations",
                 "manager": {
                     "value": "89607",
                     "displayName": "John Smith"
                 }
            }
        }
    },
    {
        "method": "POST",
        "bulkId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
        "path": "/Users",
        "data": {
            "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"],
            "externalId": "701985",
            "userName": "Kjensen@example.com",
            "name": {
                "formatted": "Ms. Kathy J Jensen, III",
                "familyName": "Jensen",
                "givenName": "Kathy",
                "middleName": "Jane",
                "honorificPrefix": "Ms.",
                "honorificSuffix": "III"
            },
            "displayName": "Kathy Jensen",
            "nickName": "Kathy",
            "emails": [
            {
              "value": "kjensen@example.com",
              "type": "work",
              "primary": true
            }
            ],
            "addresses": [
            {
              "type": "work",
              "streetAddress": "100 Oracle City Plaza",
              "locality": "Hollywood",
              "region": "CA",
              "postalCode": "91618",
              "country": "USA",
              "formatted": "100 Oracle City Plaza\nHollywood, CA 91618 USA",
              "primary": true
            }
            ],
            "phoneNumbers": [
            {
              "value": "555-555-5545",
              "type": "work"
            }
            ],
            "userType": "Employee",
            "title": "Tour Lead",
            "preferredLanguage": "en-US",
            "locale": "en-US",
            "timezone": "America/Los_Angeles",
            "active":true,
            "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
                 "employeeNumber": "701985",
                 "costCenter": "4130",
                 "organization": "Universal Studios",
                 "division": "Theme Park",
                 "department": "Tour Operations",
                 "manager": {
                     "value": "701984",
                     "displayName": "Barbara Jensen"
                 }
            }
        }
    }
],
    "failOnErrors": null
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-issues"} -->
## 受信プロビジョニング API に関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-issues
- Service: entra-id / app-provisioning
- Article date: 2026-07-07
- Summary: この記事では、受信プロビジョニング API に関する問題のトラブルシューティング方法について説明する潜在的な問題と解決策について説明します。

### 概要

このドキュメントでは、インバウンド プロビジョニング API で発生する一般的なエラーや問題とそのトラブルシューティング方法について説明します。

### トラブルシューティングのシナリオ

#### データ形式が無効です

**問題の説明**

- HTTP 400 (Bad Request) 応答コードで `Invalid Data Format` エラー メッセージを受信しました。

**考えられる原因**

1. プロビジョニング [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API 仕様に従って有効な一括要求が送信されていますが、HTTP 要求ヘッダー 'Content-Type' が `application/scim+json` に設定されていません。
2. プロビジョニング [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API 仕様に準拠していない一括要求が送信されています。

**解決方法:**

1. HTTP 要求のヘッダー `Content-Type` の値が `application/scim+json` に設定されていることを確認します。
2. 一括要求ペイロードがプロビジョニング [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API 仕様に準拠していることを確認します。

#### プロビジョニング ログが空である

**問題の説明**

- プロビジョニング /bulkUpload API エンドポイントに要求を送信し、HTTP 202 応答コードを取得しましたが、要求に対応するデータがプロビジョニング ログにありません。

**考えられる原因**

1. API 駆動型プロビジョニング アプリが一時停止しています。
2. プロビジョニング サービスは、プロビジョニング ログに対して一括要求処理の詳細での更新をまだ行っていません。
3. オンプレミス プロビジョニング エージェントの状態は非アクティブです ( [/API 駆動型のオンプレミス Active Directory への受信ユーザー プロビジョニングを](https://go.microsoft.com/fwlink/?linkid=2245182)実行している場合)。

**解決方法:**

1. プロビジョニング アプリが実行されていることを確認します。 実行されていない場合は、メニュー オプション **[プロビジョニングの開始]** を選択してデータを処理します。
2. オンプレミス エージェントを再起動して、オンプレミス プロビジョニング エージェントの状態をアクティブにします。
3. 要求の処理とプロビジョニング ログへの書き込みの間に 5 分から 10 分の遅延が予想されます。 API クライアントがプロビジョニング /bulkUpload API エンドポイントにデータを送信している場合は、要求呼び出しとプロビジョニング ログ クエリの間に時間の遅延を設けるようにします。

#### 禁止 403 の応答コード

**問題の説明**

- プロビジョニング /bulkUpload API エンドポイントに要求を送信し、HTTP 403 (禁止) 応答コードを受信しました。

**考えられる原因**

- Graph アクセス許可 `SynchronizationData-User.Upload` が API クライアントに割り当てられません。

**解決方法:**

- API クライアントに Graph アクセス許可 `SynchronizationData-User.Upload` を割り当て、操作を再試行します。

#### 要求が多すぎます 429 の応答コード

bulkUpload API エンドポイントは、次のスロットリング制限を適用し、これらの制限を超えた場合は 429 応答コードを返します。

- 5 秒あたり 40 回の API 呼び出し – 5 秒間の範囲内で呼び出し回数がこの制限を超えると、クライアントは 429 応答を受け取ります。 これを回避する 1 つの方法は、クライアント要求の送信ロジックで遅延を使用して要求の送信の "ペースを調整する" ことです。
- 24 時間にわたって 6,000 回の API 呼び出し - 呼び出しの数がこの制限を超えた場合、クライアントは 429 応答を受け取ります。 これを防ぐ 1 つの方法は、SCIM バルク ペイロードが API 呼び出しごとに最大 50 件のレコードを使用するように最適化されていることを確認することです。 この方法では、24 時間ごとに 300,000 件のレコードを送信できます。

#### バケット満杯の 500 応答コード

**問題の説明**

- SCIM クライアントは、HTTP 500 (内部サーバー エラー) を受け取り、"取り込まれたデータを格納するバケットがいっぱいです。同期サービスが取り込まれたデータを処理するのを待ってから、この要求を再試行してください"というメッセージが表示されます。
- このエラーは、大規模な HR データセットがプロビジョニング `/bulkUpload` エンドポイントに送信されるときに、初期同期または完全同期サイクル中に発生する可能性があります。

**このエラーが表示される理由**

- "バケット" は、プロビジョニング サービスが処理する前に受信 `/bulkUpload` ペイロードをバッファーするために使用される一時的なインジェスト キューです。
- 各 API 駆動型プロビジョニング ジョブには、専用のインジェスト キューがあります。
- プロビジョニング サービスは、キューに登録されたペイロードを継続的に処理し、処理されたデータを削除します。 このプロセスと削除のサイクルが遅れたり停止したりする場合、キューに置かれたデータはバケットがいっぱいになるまで蓄積される可能性があります。

**考えられる原因と解決策**

| 原因 | Resolution |
| --- | --- |
| マッピングが正しくないか（たとえば、オンプレミスの Active Directory で管理されている Microsoft Entra ID 属性を更新しようとする場合）、データが無効であるため、ペイロードの処理が失敗します。 失敗したペイロードはキューに残り、最終的にバケットがいっぱいになる可能性があります。 | プロビジョニング ログを確認して、失敗した要求処理の特定、マッピングまたはデータの問題の修正、プロビジョニング ジョブの再起動、要求の再送信を行います。 |
| API 駆動型プロビジョニング ジョブが **一時停止** 状態または **停止** 状態です。 要求はキューに入れ続けますが、処理は実行されません。 | キューに登録された要求を処理してクリアできるように、プロビジョニング ジョブを再開します。 |
| API によるプロビジョニング ジョブが、長時間 **隔離** 状態のままになります。 要求はキューに入れ続けますが、処理は実行されません。 | プロビジョニング ジョブを再起動して検疫をクリアします。 再起動時に、キューに登録されている既存のデータがクリアされ、時間がかかる場合があります。 約 40 分待ってから、SCIM `/bulkUpload` 要求を再送信します。 |
| ソース システムは、プロビジョニング ジョブで処理できるよりも高速に SCIM データを送信します。 | ペース要求の提出。 一括アップロードのたびに、HTTP 状態コードを確認します。 バケット全体のメッセージを含む HTTP 500 が表示される場合は、再試行する前にクライアントを一時停止します (例: 5 ~ 10 分)。 |

#### 未承認 401 の応答コード

**問題の説明**

- プロビジョニング /bulkUpload API エンドポイントに要求を送信し、HTTP 401 (未承認) 応答コードを受信しました。 エラー コードには、「InvalidAuthenticationToken」と表示され、「アクセス トークンの有効期限が切れているか、まだ有効ではありません」というメッセージが表示されます。

**考えられる原因**

- アクセス トークンの有効期限が切れています。

**解決方法:**

- API クライアントの新しいアクセス トークンを生成します。

#### ジョブが検疫状態になる

**問題の説明**

- プロビジョニング アプリを開始したばかりで、アプリが検疫状態です。

**考えられる原因**

- ジョブを開始する前に、通知用メールの設定が行われていません。

**解決策:** **[プロビジョニングの編集]** メニュー項目に移動します。 **[設定]** の **[エラーが発生したときにメール通知を送信します]** の横にチェックボックスがあり、**通知用メール**を入力するフィールドがあります。 チェック ボックスをオンにして、メール アドレスを入力し、変更を保存してください。 **[プロビジョニングの再開]** をクリックして、ジョブを検疫から除外します。

#### ユーザーの作成 - 無効な UPN

**問題の説明** ユーザー プロビジョニング エラーが発生しました。 プロビジョニング ログには、エラー コード `AzureActiveDirectoryInvalidUserPrincipalName` が表示されます。

**解決方法:**

1. **[属性マッピングの編集]** ページに移動します。
2. `UserPrincipalName` マッピングを選択し、`RandomString` 関数を使用するように更新します。
3. 次の式をコピーして式ボックスに貼り付けます。`Join("", Replace([userName], , "(?<Suffix>@(.)*)", "Suffix", "", , ), RandomString(3, 3, 0, 0, 0, ), "@", DefaultDomain())`

この式は、Microsoft Entra ID で受け入れられる UPN 値に乱数を追加することで問題を修正します。

#### ユーザーの作成に失敗しました - 無効なドメイン

**問題の説明** ユーザー プロビジョニング エラーが発生しました。 プロビジョニング ログには、`domain does not exist` というエラー メッセージが表示されます。

**解決方法:**

1. **[属性マッピングの編集]** ページに移動します。
2. `UserPrincipalName` マッピングを選択し、次の式をコピーして式の入力ボックスに貼り付けます。`Join("", Replace([userName], , "(?<Suffix>@(.)*)", "Suffix", "", , ), RandomString(3, 3, 0, 0, 0, ), "@", DefaultDomain())`

この式は、Microsoft Entra ID で受け入れられる UPN 値に既定のドメインを追加することで問題を修正します。

#### 既知の制限事項: 複数値アドレス、電子メール、電話番号

**問題の説明**

- 現在、API 駆動型のプロビジョニングでは、`addresses`値が`emails`または他の`phoneNumbers`以外の値である場合、`type`、`home`、および`work`で SCIM の複数値属性は処理されません。
- この制限は、 `addresses[type eq "home"]`、 `addresses[type eq "any-other-value"]`、 `phoneNumbers[type eq "home"]`などの式に適用されます。

**現在の動作**

- `addresses[type eq "work"]`、`emails[type eq "work"]`、および`phoneNumbers[type eq "work"]`の値のみが処理されます。

**Workaround**

- API 駆動型のプロビジョニングで属性を処理する必要がある場合は、 `work` 型を使用してサポートされている値を送信します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-logic-apps"} -->
## Azure Logic Apps を使用した API 駆動型インバウンド プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-logic-apps
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Azure Logic Apps を使用して API 駆動型インバウンド プロビジョニングを実装する方法について説明します。

このチュートリアルでは、Azure Logic Apps ワークフローを使用して Microsoft Entra ID の [API 駆動型インバウンド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)を実装する方法について説明します。 このチュートリアルの手順を使用して、人事データを含む CSV ファイルを一括要求ペイロードに変換して、Microsoft Entra のプロビジョニング [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントに送信できます。 この記事では、同じ統合パターンがレコードの任意のシステムでどのように使用できるかに関するガイダンスも提供します。

### 統合シナリオ

#### 業務要件

お客様のレコードのシステムでは、ワーカー データを含む CSV ファイルのエクスポートが定期的に生成されます。 CSV ファイルからデータを読み取り、ターゲット ディレクトリにユーザー アカウントを自動的にプロビジョニングする統合を実装する必要があります (ハイブリッド ユーザーの場合はオンプレミスの Active Directory、クラウド専用ユーザーの場合は Microsoft Entra ID)。

#### 実装の要件

実装の観点から:

- Azure Logic Apps ワークフローを使用して、Azure ファイル共有で使用可能な CSV ファイル エクスポートからデータを読み取り、受信プロビジョニング API エンドポイントに送信する必要があります。
- Azure Logic Apps ワークフローでは、レコード システムとターゲット ディレクトリ間で ID データを比較する複雑なロジックを実装したくはありません。
- Microsoft Entra プロビジョニング サービスを使用して、IT マネージド プロビジョニング ルールを適用して、ターゲット ディレクトリ (オンプレミスの Active Directory または Microsoft Entra ID) でアカウントを自動的に作成、更新、有効化、無効化する必要があります。

[Image: Azure Logic Apps ベースの統合の図。]

#### 統合シナリオのバリエーション

このチュートリアルでは CSV ファイルをレコードのシステムとして使用しますが、サンプル Azure Logic Apps ワークフローをカスタマイズして、レコードの任意のシステムからデータを読み取ることができます。 Azure Logic Apps には、統合ワークフローで使用可能な組み込みトリガーとアクションを備えた、さまざまな[組み込みコネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/built-in/reference/)と[マネージド コネクタ](https://learn.microsoft.com/ja-jp/connectors/connector-reference/connector-reference-logicapps-connectors)が用意されています。

Logic Apps ワークフローを使用して API 駆動型インバウンド プロビジョニングを実装できるエンタープライズ統合シナリオのバリエーションの一覧を次に示します。

| # | 記録システム | Logic Apps を使用したソース データの読み取りに関する統合ガイダンス |
| --- | --- | --- |
| 1 | SFTP サーバーに格納されているファイル | [組み込みの SFTP コネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/built-in/reference/sftp/)または[マネージド SFTP SSH コネクタ](https://learn.microsoft.com/ja-jp/azure/connectors/connectors-sftp-ssh)のいずれかを使用して、SFTP サーバーに格納されているファイルからデータを読み取ります。 |
| 2 | データベース テーブル | Azure SQL サーバーまたはオンプレミスのSQL Serverを使用している場合は、[SQL Server](https://learn.microsoft.com/ja-jp/azure/connectors/connectors-create-api-sqlazure) コネクタを使用してテーブル データを読み取ります。  Oracle Database を使用している場合は、[Oracle Database](https://learn.microsoft.com/ja-jp/azure/connectors/connectors-create-api-oracledatabase) コネクタを使用してテーブル データを読み取ります。 |
| 3 | オンプレミスおよびクラウドでホストされる SAP S/4 HANA または 従来のオンプレミスの SAP システム (R/3 や ECC など)。 | [SAP コネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-using-sap-connector)を使用して、SAP システムから ID データを取得します。 このコネクタを構成する方法の例については、Azure Logic Apps と SAP コネクタを使用した[一般的な SAP 統合シナリオ](https://learn.microsoft.com/ja-jp/azure/logic-apps/sap-create-example-scenario-workflows)に関するページを参照してください。 |
| 4 | IBM MQ | [IBM MQ コネクター](https://learn.microsoft.com/ja-jp/azure/connectors/connectors-create-api-mq)を使用して、キューからプロビジョニング メッセージを受信します。 |
| 5 | Dynamics 365 人事 | [Dataverse コネクタ](https://learn.microsoft.com/ja-jp/azure/connectors/connect-common-data-service)を使用して、Microsoft Dynamics 365 Human Resources で使用される [Dataverse テーブル](https://learn.microsoft.com/ja-jp/dynamics365/human-resources/hr-developer-entities)からデータを読み取ります。 |
| 6 | REST API を公開するすべてのシステム | Logic Apps コネクタ ライブラリにレコードのシステムのコネクタが見つからない場合は、独自の[カスタム コネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-create-api-app)を作成して、レコードのシステムからデータを読み取ることができます。 |

ソース データを読み取った後、前処理ルールを適用し、レコードのシステムからの出力をMicrosoft Entra をプロビジョニングする [bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントに送信できる一括要求に変換します。

重要

API 駆動型インバウンド プロビジョニングと Logic Apps 統合ワークフローをコミュニティと共有する場合は、[ロジック アプリ テンプレート](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-create-azure-resource-manager-templates)を作成し、それを使用する方法に関する手順を文書化し、GitHub リポジトリ [`entra-id-inbound-provisioning`](https://github.com/AzureAD/entra-id-inbound-provisioning) に含めるための pull request を送信します。

### このチュートリアルの使用方法

[Microsoft Entra のインバウンド プロビジョニング GitHub リポジトリ](https://github.com/AzureAD/entra-id-inbound-provisioning/tree/main/LogicApps/CSV2SCIMBulkUpload)で公開されている Logic Apps デプロイ テンプレートでは、複数のタスクが自動化されます。 これには、大きな CSV ファイルを処理し、一括要求をチャンクして要求ごとに 50 個のレコードを送信するためのロジックもあります。 これをテストし、自分の統合要件に従ってカスタマイズする方法を次に示します。

Note

サンプルの Azure Logic Apps ワークフローは、実装の参照のために "現状のまま" 提供されています。 関連する質問があるか、拡張する場合は、[GitHub プロジェクト リポジトリ](https://github.com/AzureAD/entra-id-inbound-provisioning)を使用してください。

| # | Automation タスク | 実装ガイダンス | 高度なカスタマイズ |
| --- | --- | --- | --- |
| 1 | CSV ファイルからデータを読み取ります。 | Logic Apps ワークフローでは、Azure 関数を使用して、Azure ファイル共有に格納されている CSV ファイルを読み取ります。 Azure 関数は、CSV データを JSON 形式に変換します。 CSV ファイル形式が異なる場合は、ワークフロー の "JSON の解析" と "SCIMUser の構築" の手順を更新します。 | レコードのシステムが異なる場合は、「統合シナリオのバリエーション」セクションで提供されている、適切なコネクタを使用して Logic Apps ワークフローをカスタマイズする方法に関するガイダンスを確認してください。 |
| 2 | データを前処理して SCIM 形式に変換します。 | 既定では、Logic Apps ワークフローは CSV ファイル内の各レコードを SCIM コア ユーザーとエンタープライズ ユーザー表現に変換します。 カスタム SCIM スキーマ拡張機能を使用する予定がある場合は、カスタム SCIM スキーマ拡張機能を含むように「SCIMUser の構築」の手順を更新します。 | 高度な書式設定とデータ検証のために C# コードを実行する場合は、[カスタム Azure Functions](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-azure-functions) を使用します。 |
| 3 | 適切な認証方法を使用する | [サービス プリンシパルを使用する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access#configure-a-service-principal)か、[マネージド ID を使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access#configure-a-managed-identity)して受信プロビジョニング API にアクセスできます。 適切な認証方法を使用して、「API エンドポイントに SCIMBulkPayload を送信する」手順を更新します。 | - |
| 4 | オンプレミスの Active Directory または Microsoft Entra ID でアカウントをプロビジョニングします。 | [API 駆動型インバウンド プロビジョニング アプリ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)を構成します。 これにより、一意の [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントが生成されます。 「API エンドポイントに SCIMBulkPayload を送信する」手順を更新して、適切な bulkUpload API エンドポイントを使用します。 | カスタム SCIM スキーマで一括要求を使用する場合、プロビジョニング アプリ スキーマを拡張してカスタム SCIM スキーマ属性を含めます。 |
| 5 | プロビジョニング ログをスキャンして、失敗したレコードのプロビジョニングを再試行します。 | この自動化は、サンプル Logic Apps ワークフローにはまだ実装されていません。 実装するには、[プロビジョニング ログ Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/provisioningobjectsummary) を参照してください。 | - |
| 6 | 運用環境に Logic Apps ベースの自動化をデプロイする。 | API 駆動型プロビジョニング フローを確認して要件を満たすように Logic Apps ワークフローをカスタマイズしたら、お使いの環境で自動化をデプロイします。 | - |

### 手順 1: CSV ファイルをホストする Azure Storage アカウントを作成する

このセクションに記載されている手順は省略可能です。 既存のストレージ アカウントが既にある場合、または SharePoint サイトや BLOB ストレージなどの別のソースから CSV ファイルを読み取る場合は、任意のコネクタを使用するようにロジック アプリを更新します。

1. [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)として、[Azure portal](https://portal.azure.com) にサインインします。
2. "ストレージ アカウント" を検索し、新しいストレージ アカウントを作成します。 [Image: 新しいストレージ アカウントの作成のスクリーンショット。]
3. リソース グループを割り当てて、名前を付けます。 [Image: リソース グループのスクリーンショット。]
4. ストレージ アカウントの作成後、[リソースに移動] に移動します。
5. [ファイル共有] メニュー オプションを選択し、新しいファイル共有を作成します。 [Image: 新しいファイル共有の作成のスクリーンショット。]
6. ファイル共有の作成が成功したことを確認します。 [Image: 作成されたファイル共有のスクリーンショット。]
7. アップロード オプションを使用して、サンプルの CSV ファイルをファイル共有にアップロードします。
8. CSV ファイル内の列のスクリーンショットを次に示します。 [Image: Excel 内の列のスクリーンショット。]

### 手順 2: Azure 関数 CSV2JSON コンバーターを構成する

1. Azure portal ログインに関連付けられているブラウザーで、GitHub リポジトリ URL - https://github.com/joelbyford/CSVtoJSONcore を開きます。
2. [Deploy to Azure]\(Azure にデプロイ\) リンクを選択して、この Azure 関数を Azure テナントにデプロイします。 [Image: Azure 関数のデプロイのスクリーンショット。]
3. この Azure 関数をデプロイするリソース グループを指定します。 [Image: Azure 関数リソース グループの構成のスクリーンショット。]

    "[This region has quota of 0 instances](https://learn.microsoft.com/ja-jp/answers/questions/751909/azure-function-app-region-has-quota-of-0-instances)" というエラーが表示される場合は、別のリージョンを選択してみてください。
4. App Service としての Azure 関数のデプロイが成功したことを確認します。
5. リソース グループに移動し、WebApp 構成を開きます。 "実行中" 状態であることを確認します。 Web アプリに関連付けられている既定のドメイン名をコピーします。 [Image: Azure 関数 Web アプリ ドメイン名のスクリーンショット。]
6. 次の PowerShell スクリプトを実行して、CSVtoJSON エンドポイントが期待どおりに動作するかどうかをテストします。 スクリプトで `$csvFilePath` と `$uri` 変数の正しい値を設定します。

    ```powershell
    # Step 1: Read the CSV file 
    $csvFilePath = "C:\Path-to-CSV-file\hr-user-data.csv" 
    $csvContent = Get-Content -Path $csvFilePath 
    
    # Step 2: Set up the request 
    $uri = "https://az-function-webapp-your-domain/csvtojson" 
    $headers = @{ 
         "Content-Type" = "text/csv" 
    } 
    $body = $csvContent -join "`n"  # Join the CSV lines into a single string 
    
    # Step 3: Send the POST request 
    $response = Invoke-WebRequest -Uri $uri -Method POST -Headers $headers -Body $body 
    
    # Output and format the JSON response 
    $response.Content | ConvertFrom-JSON | ConvertTo-JSON 
    ```
7. Azure 関数のデプロイが成功した場合、スクリプトの最後の行で CSV ファイルの JSON バージョンが出力されます。

    [Image: Azure 関数の応答のスクリーンショット。]。
8. Logic Apps でこの Azure 関数を呼び出せるようにするには、WebApp の CORS 設定にアスタリスク (\*) を入力し、構成を "保存" します。 [Image: Azure 関数の CORS 設定のスクリーンショット。]

### 手順 3: API 駆動型インバウンド ユーザー プロビジョニングを構成する

- [API 駆動型インバウンド ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)を構成します。

### 手順 4: Azure Logic Apps ワークフローを構成する

1. 下のボタンを選択して、CSV2SCIMBulkUpload Logic Apps ワークフロー用の Azure Resource Manager テンプレートをデプロイします。
2. [インスタンスの詳細] で、強調表示されている項目を更新し、前の手順の値をコピーして貼り付けます。 [Image: Azure Logic Apps インスタンスの詳細のスクリーンショット。]
3. `Azurefile_access Key` パラメーターについては、Azure ファイル ストレージ アカウントを開き、[セキュリティとネットワーク] の下にあるアクセス キーをコピーします。[Image: Azure ファイル アクセス キーのスクリーンショット。]
4. [確認と作成] オプションを選択してデプロイを開始します。
5. デプロイが完了すると、次のメッセージが表示されます。 [Image: Azure Logic Apps のデプロイ完了のスクリーンショット。]

### 手順 5: システム割り当てマネージド ID を構成する

1. Logic Apps ワークフローの [設定] -&gt; [ID] ブレードにアクセスします。
2. **システム割り当てマネージド ID** を有効にする。 [Image: マネージド ID の有効化のスクリーンショット。]
3. マネージド ID の使用を確認するプロンプトが表示されます。 **[はい]** を選択します。
4. [一括アップロードを実行するアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access#configure-a-managed-identity)をマネージド ID に付与します。

### 手順 6: ワークフローの手順を確認して調整する

1. デザイナー ビューでロジック アプリを開きます。 [Image: Azure Logic Apps デザイナー ビューのスクリーンショット。]
2. ワークフローの各ステップの構成を確認して、正しいことを確認します。
3. [パスを使用してファイルの内容を取得] ステップを開いて、テナント内の Azure File Storage を参照するように修正します。 [Image: ファイルの内容の取得のスクリーンショット。]
4. 必要に応じて接続を更新します。
5. [Convert CSV to JSON] (CSV を JSON に変換) ステップが適切な Azure 関数 Web アプリ インスタンスを指していることを確認します。 [Image: CSV から JSON に変換するための Azure 関数呼び出しのスクリーンショット。]
6. CSV ファイルの内容またはヘッダーが異なる場合は、Azure 関数への API 呼び出しから取得できる JSON 出力で、[Parse JSON] (JSON の解析) ステップを更新します。 手順 2 の PowerShell 出力を使用します。 [Image: [Parse JSON] (JSON の解析) ステップのスクリーンショット。]
7. [Construct SCIMUser] (SCIMUser のコンストラクト) ステップで、CSV フィールドが処理に使用される SCIM 属性に正しくマップされていることを確認します。

    [Image: [Construct SCIMUser] (SCIMUser のコンストラクト) ステップのスクリーンショット。]
8. 「API エンドポイントに SCIMBulkPayload を送信する」の手順で、適切な API エンドポイントと認証メカニズムを使用していることを確認します。

    [Image: マネージド ID を使用して一括アップロード API を呼び出すスクリーンショット。]

### 手順 7: トリガーを実行して Logic Apps ワークフローをテストする

1. Logic Apps デザイナーの "一般公開" バージョンで、[トリガーの実行] を選択してワークフローを手動で実行します。 [Image: 実行されている Logic Apps のスクリーンショット。]
2. 実行が完了したら、各イテレーションで Logic Apps が実行したアクションを確認します。
3. 最後の反復では、Logic Apps によってインバウンド プロビジョニング API エンドポイントにデータがアップロードされます。 `202 Accept` 状態コードを探します。 一括アップロード要求をコピーして貼り付けて、確認できます。 [Image: Logic Apps の実行結果のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/inbound-provisioning-api-powershell"} -->
## PowerShell スクリプトを使用した API 駆動型インバウンド プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-powershell
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: PowerShell スクリプトを使用して API 駆動型インバウンド プロビジョニングを実装する方法について説明します。

このチュートリアルでは、PowerShell スクリプトを使用して Microsoft Entra ID [API ベースの受信プロビジョニングを実装する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)方法について説明します。 このチュートリアルの手順を使用して、HR データを含む CSV ファイルを一括要求ペイロードに変換し、Microsoft Entra provisioning [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントに送信できます。 この記事では、同じ統合パターンがレコードの任意のシステムでどのように使用できるかに関するガイダンスも提供します。

### 統合シナリオ

#### 業務要件

お客様のレコードのシステムでは、ワーカー データを含む CSV ファイルのエクスポートが定期的に生成されます。 CSV ファイルからデータを読み取り、ターゲット ディレクトリにユーザー アカウントを自動的にプロビジョニングする統合を実装する必要があります (ハイブリッド ユーザーの場合はオンプレミスの Active Directory、クラウド専用ユーザーの場合は Microsoft Entra ID)。

#### 実装の要件

実装の観点から:

- 自動 PowerShell スクリプトを使用して、CSV ファイル エクスポートからデータを読み取り、インバウンド プロビジョニング API エンドポイントに送信する必要があります。
- PowerShell スクリプトでは、レコードのシステムとターゲット ディレクトリの間で ID データを比較する複雑なロジックを実装したくはありません。
- Microsoft Entra プロビジョニング サービスを使用して、IT マネージド プロビジョニング ルールを適用して、ターゲット ディレクトリ (オンプレミスの Active Directory または Microsoft Entra ID) でアカウントを自動的に作成、更新、有効化、無効化する必要があります。

[Image: PowerShell ベースの統合の図。]

#### 統合シナリオのバリエーション

このチュートリアルでは CSV ファイルをレコードのシステムとして使用しますが、サンプル PowerShell スクリプトをカスタマイズして、レコードの任意のシステムからデータを読み取ることができます。 PowerShell スクリプトを使用して API 駆動型インバウンド プロビジョニングを実装できるエンタープライズ統合シナリオのバリエーションの一覧を次に示します。

| # | 記録システム | PowerShell を使用したソース データの読み取りに関する統合ガイダンス |
| --- | --- | --- |
| 1 | データベース テーブル | Azure SQL データベースまたはオンプレミスの SQL Server を使用している場合は、 [Read-SqlTableData](https://learn.microsoft.com/ja-jp/powershell/module/sqlserver/read-sqltabledata) コマンドレットを使用して、SQL データベースのテーブルに格納されているデータを読み取ることができます。 [Invoke-SqlCmd](https://learn.microsoft.com/ja-jp/powershell/module/sqlserver/invoke-sqlcmd) コマンドレットを使用して、Transact-SQL または XQuery スクリプトを実行できます。  Oracle/MySQL/Postgres データベースを使用している場合は、ベンダーによって発行された PowerShell モジュール、または [PowerShell ギャラリー](https://www.powershellgallery.com/)で入手できる PowerShell モジュールを見つけることができます。 モジュールを使用して、データベース テーブルからデータを読み取ります。 |
| 2 | LDAP サーバー | `System.DirectoryServices.Protocols` .NET API または [PowerShell ギャラリー](https://www.powershellgallery.com/packages?q=ldap)で使用可能な LDAP モジュールのいずれかを使用して、LDAP サーバーに対してクエリを実行します。 LDAP サーバーからユーザー データを取得するための LDAP スキーマと階層について理解します。 |
| 3 | REST API を公開するすべてのシステム | PowerShell を使用して REST API エンドポイントからデータを読み取るために、 モジュールの `Microsoft.PowerShell.Utility` コマンドレットを使用できます。 REST API のドキュメントを確認し、これが想定しているパラメーターとヘッダー、返す形式、および使用する認証方法を確認します。 その後、それに応じて `Invoke-RestMethod` コマンドを調整できます。 |
| 4 | SOAP APIs を公開するすべてのシステム | PowerShell を使用して SOAP API エンドポイントからデータを読み取るために、 モジュールの `Microsoft.PowerShell.Management`使用できます。 SOAP API のドキュメントを確認し、これが想定しているパラメーターとヘッダー、返す形式、および使用する認証方法を確認します。 その後、それに応じて `New-WebServiceProxy` コマンドを調整できます。 |

ソース データを読み取った後、前処理ルールを適用し、レコードのシステムからの出力を、Microsoft Entra プロビジョニング [bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントに送信できる一括要求に変換します。

重要

PowerShell 統合スクリプトをコミュニティと共有する場合は、 [PowerShell ギャラリー](https://www.powershellgallery.com/) で発行し、GitHub リポジトリ [`entra-id-inbound-provisioning`](https://github.com/AzureAD/entra-id-inbound-provisioning)で通知して、参照を追加できます。

### このチュートリアルの使用方法

[Microsoft Entra 受信プロビジョニング GitHub リポジトリ](https://github.com/AzureAD/entra-id-inbound-provisioning/tree/main/PowerShell/CSV2SCIM)で公開されている PowerShell サンプル スクリプトでは、いくつかのタスクが自動化されます。 これには、大きな CSV ファイルを処理し、一括要求をチャンクして要求ごとに 50 個のレコードを送信するためのロジックがあります。 これをテストし、自分の統合要件に従ってカスタマイズする方法を次に示します。

Note

実装の参照のため、サンプルの PowerShell スクリプトはそのままの状態で提供されています。 スクリプトに関連する質問がある場合、またはスクリプトを拡張する場合は、 [GitHub プロジェクト リポジトリ](https://github.com/AzureAD/entra-id-inbound-provisioning)を使用してください。

| # | Automation タスク | 実装ガイダンス | 高度なカスタマイズ |
| --- | --- | --- | --- |
| 1 | CSV ファイルからデータを読み取ります。 | PowerShell スクリプトをダウンロードします。 任意の CSV ファイルからデータを読み取るためにすぐに使用できるロジックが含まれています。 このスクリプトのさまざまな実行モードを理解するには、 PowerShell の使用方法の詳細 CSV2SCIM参照してください。 | レコードのシステムが異なる場合は、PowerShell スクリプトをカスタマイズする方法に関する 統合シナリオのバリエーション に関するセクションに記載されているガイダンスを確認してください。 |
| 2 | データを前処理して SCIM 形式に変換します。 | 既定では、PowerShell スクリプトは CSV ファイル内の各レコードを SCIM コア ユーザーとエンタープライズ ユーザー表現に変換します。 このプロセスを理解するには、「 標準スキーマを使用して一括要求ペイロードを生成 する」セクションの手順に従います。 | CSV ファイルに異なるフィールドがある場合は、有効な SCIM ユーザーを生成するように AttributeMapping.psd ファイル を調整します。 カスタム SCIM スキーマを使用して一括要求を生成することもできます。 PowerShell スクリプトを更新して、任意のカスタムの CSV データの検証ロジックを含めます。 |
| 3 | Microsoft Entra ID への認証に証明書を使用する。 | 受信プロビジョニング API [にアクセスできるサービス プリンシパルを作成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access)します。 認証にクライアント証明書を使用する方法については、「 サービス プリンシパル認証用にクライアント証明書を構成 する」セクションの手順を参照してください。 | 認証にサービス プリンシパルではなくマネージド ID を使用する場合は、サンプル スクリプトで `Connect-MgGraph` の使用を確認し、 [マネージド ID を](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-managed-identity)使用するように更新します。 |
| 4 | オンプレミスの Active Directory または Microsoft Entra ID でアカウントをプロビジョニングします。 | [API ドリブンの受信プロビジョニング アプリを構成します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)。 これにより、一意の [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントが生成されます。 証明書ベースの認証でサービス プリンシパルを使用してスクリプトを実行するには、「クライアント証明書認証を使用して 一括要求ペイロードをアップロードする」セクションの手順を参照してください。 属性フローを検証して、統合の要件に従って属性マッピングをカスタマイズします。 | カスタム SCIM スキーマで一括要求を使用する場合は、カスタム SCIM スキーマ要素を含むようにプロビジョニング アプリ スキーマを拡張します。 |
| 5 | プロビジョニング ログをスキャンして、失敗したレコードのプロビジョニングを再試行します。 | プロビジョニング ログ データをフェッチして分析する方法については、「 最新の同期サイクルのプロビジョニング ログを取得 する」セクションの手順を参照してください。 失敗したユーザー レコードを特定し、次のアップロード サイクルに含めます。 | - |
| 6 | 運用環境に PowerShell ベースの自動化をデプロイする。 | API 駆動型のプロビジョニング フローを検証し、要件を満たすように PowerShell スクリプトをカスタマイズしたら、 [Azure Automation の PowerShell ワークフロー Runbook](https://learn.microsoft.com/ja-jp/azure/automation/learn/automation-tutorial-runbook-textual) として、または [Windows サーバーで実行するようにスケジュールされた](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/system-management-components/schedule-server-process)サーバー プロセスとして自動化をデプロイできます。 | - |

### PowerShell スクリプトをダウンロードする

1. GitHub リポジトリ [`entra-id-inbound-provisioning`](https://github.com/AzureAD/entra-id-inbound-provisioning) にアクセスします。
2. **Code**&gt;**Clone** または **Code**&gt;**Download ZIP** オプションを使用して、このリポジトリの内容をローカル フォルダーにコピーします。
3. **PowerShell/CSV2SCIM**フォルダーに移動します。 次のディレクトリ構造があります。
    - src
        - CSV2SCIM.ps1 (メイン スクリプト)
        - ScimSchemaRepresentations (AttributeMapping.psd1 ファイルを検証するための標準の SCIM スキーマ定義を含むフォルダー)
            - EnterpriseUser.json、Group.json、Schema.json、User.json
    - サンプル
        - AttributeMapping.psd1 (CSV ファイル内の列の標準の SCIM 属性へのマッピング サンプル)
        - csv-with-2-records.csv (2 つのレコードを含むサンプルの CSV ファイル)
        - csv-with-1000-records.csv (1000 個のレコードを含むサンプルの CSV ファイル)
        - Test-ScriptCommands.ps1 (使用コマンドのサンプル)
        - UseClientCertificate.ps1 (自己署名証明書を生成し、OAuth フローで使用するためのサービス プリンシパルの資格情報としてアップロードするスクリプト)
        - `Sample1` (CSV ファイルの列を SCIM 標準属性にマップする方法のその他の例を含むフォルダー。従業員、請負業者、インターン用に異なる CSV ファイルを取得すると、エンティティごとに個別の AttributeMapping.psd1 ファイルを作成できます。)
4. 最新バージョンの PowerShell をダウンロードしてインストールします。
5. コマンドを実行して、リモート署名済みのスクリプトの実行を有効にします。

    ```powershell
    set-executionpolicy remotesigned
    ```
6. 次の前提条件モジュールをインストールします。

    ```powershell
    Install-Module -Name Microsoft.Graph.Applications,Microsoft.Graph.Reports
    ```

### 標準スキーマを使用して一括要求ペイロードを生成する

このセクションでは、CSV ファイルからの標準 SCIM コア ユーザー属性およびエンタープライズ ユーザー属性を使用して一括要求ペイロードを生成する方法について説明します。 手順を説明するために、CSV ファイル `Samples/csv-with-2-records.csv` を使用します。

1. CSV ファイル `Samples/csv-with-2-records.csv` を Notepad++ または Excel で開き、ファイルに存在する列をチェックします。 [Image: Excel の列のスクリーンショット。]
2. Notepad++ または Visual Studio Code などのソース コード エディターで、CSV ファイルの列を SCIM 標準スキーマ属性にマッピングできるようにする PowerShell データ ファイル `Samples/AttributeMapping.psd1` を開きます。 すぐに使用できる状態で出荷されたファイルには、CSV ファイルの列の対応する SCIM スキーマ属性への事前構成済みのマッピングが既に含まれています。

    ```powershell
        @{
        externalId   = 'WorkerID'
        name         = @{
            familyName = 'LastName'
            givenName  = 'FirstName'
        }
        active       = { $_.'WorkerStatus' -eq 'Active' }
        userName     = 'UserID'
        displayName  = 'FullName'
        nickName     = 'UserID'
        userType     = 'WorkerType'
        title        = 'JobTitle'
        addresses    = @(
            @{
                type          = { 'work' }
                streetAddress = 'StreetAddress'
                locality      = 'City'
                postalCode    = 'ZipCode'
                country       = 'CountryCode'
            }
        )
        phoneNumbers = @(
            @{
                type  = { 'work' }
                value = 'OfficePhone'
            }
        )
        "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User" = @{
            employeeNumber = 'WorkerID'
            costCenter     = 'CostCenter'
            organization   = 'Company'
            division       = 'Division'
            department     = 'Department'
            manager        = @{
                value = 'ManagerID'
            }
        }
    }
    ```
3. PowerShell を開き、ディレクトリ **CSV2SCIM\src** に変更します。
4. 次のコマンドを実行して、`AttributeMapping` 変数を初期化します。

    ```powershell
    $AttributeMapping = Import-PowerShellDataFile '..\Samples\AttributeMapping.psd1'
    ```
5. 次のコマンドを実行して、`AttributeMapping` ファイルに有効な SCIM スキーマ属性があるかどうかを検証します。 検証が成功した場合、このコマンドは **True を** 返します。

    ```powershell
    .\CSV2SCIM.ps1 -Path '..\Samples\csv-with-2-records.csv' -AttributeMapping $AttributeMapping -ValidateAttributeMapping
    ```
6. たとえば、 `AttributeMapping` ファイルに **userId** という無効な SCIM 属性があるとします。その後、 `ValidateAttributeMapping` モードでは次のエラーが表示されます。

    [Image: マッピング エラーのスクリーンショット。]
7. `AttributeMapping` ファイルが有効であることを確認したら、次のコマンドを実行して、CSV ファイルに存在する 2 つのレコードを含む一括要求を `BulkRequestPayload.json` というファイルに生成します。

    ```powershell
    .\CSV2SCIM.ps1 -Path '..\Samples\csv-with-2-records.csv' -AttributeMapping $AttributeMapping > BulkRequestPayload.json
    ```
8. ファイル `BulkRequestPayload.json` のコンテンツを開いて、SCIM 属性がファイル `AttributeMapping.psd1` で定義されているマッピングに従って設定されているかどうかを確認できます。
9. 上記の as-is で生成されたファイルを、Graph Explorer または cURL を使用して、プロビジョニング アプリに関連付けられている [/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントに投稿できます。 リファレンス:

    - [Graph エクスプローラーを使用したクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer)
    - [cURL を使用したクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-curl-tutorial)
10. 同じ PowerShell スクリプトを使用して、生成されたペイロードを API エンドポイントに直接アップロードするには、次のセクションをご覧ください。

### サービス プリンシパル認証対応にクライアント認定資格書を構成する

Note

こちらの手順では、自己署名証明書を生成する方法を示します。 自己署名証明書は、既定では信頼されないため、管理が困難になる可能性があります。 また、強力でない可能性のある旧式のハッシュや暗号スイートが使用される場合もあります。 セキュリティを強化するには、よく知られている証明機関によって署名された証明書を購入してください。

1. この PowerShell スクリプトを実行して、新しい自己署名証明書を生成します。 既知の証明機関によって署名された証明書を購入した場合、この手順をスキップできます。

    ```powershell
    $ClientCertificate = New-SelfSignedCertificate -Subject 'CN=CSV2SCIM' -KeyExportPolicy 'NonExportable' -CertStoreLocation Cert:\CurrentUser\My
    $ThumbPrint = $ClientCertificate.ThumbPrint
    ```
 生成された証明書は、 **現在のユーザー\個人用\証明書に**格納されます。 **コントロール パネル**の [&gt;] オプション使用して表示できます。
2. この証明書を有効なサービス プリンシパルに関連付けるには、アプリケーション管理者として Microsoft Entra 管理センターにログインします。
3. [アプリ登録](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access#configure-a-service-principal)の**構成したサービス プリンシパル**を開きます。
4. **[概要**] ブレードから**オブジェクト ID を**コピーします。 この値を使用して文字列 `<AppObjectId>` を置き換えます。 **アプリケーション (クライアント) ID をコピーします**。後で使用し、`<AppClientId>`として参照されます。
5. 次のコマンドを実行して、登録済みのサービス プリンシパルに証明書をアップロードします。

    ```powershell
    Connect-MgGraph -Scopes "Application.ReadWrite.All"
    Update-MgApplication -ApplicationId '<AppObjectId>' -KeyCredentials @{
       Type = "AsymmetricX509Cert"
       Usage = "Verify"
       Key = $ClientCertificate.RawData
    }
    ```
 登録したアプリの **[証明書とシークレット** ] ブレードに証明書が表示されます。 [Image: クライアント証明書のスクリーンショット。]
6. サービス プリンシパル アプリに、**Application.Read.All** と **Synchronization.Read.All** **の 2 つの**アプリケーション アクセス許可スコープを追加します。 これらは、PowerShell スクリプトがプロビジョニング アプリを `ServicePrincipalId` で検索し、プロビジョニング `JobId` をフェッチするために必要です。

### クライアント証明書認証を使用して一括要求ペイロードをアップロードする

このセクションでは、信頼されたクライアント証明書を使用して、生成された一括要求ペイロードをインバウンド プロビジョニング API エンドポイントに送信する方法について説明します。

1. [構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)した API 駆動型プロビジョニング アプリを開きます。 プロビジョニング アプリに関連付けられている `ServicePrincipalId` を **Provisioning App**&gt;**Properties**&gt;**Object ID** からコピーします。

    [Image: オブジェクト ID のスクリーンショット。]
2. `ServicePrincipalId`、`ClientId`、`TenantId` に正しい値を指定して、次のコマンドを実行します。

    ```powershell
    $ClientCertificate = Get-ChildItem -Path cert:\CurrentUser\my\ | Where-Object {$_.Subject -eq "CN=CSV2SCIM"}  
    $ThumbPrint = $ClientCertificate.ThumbPrint
    
    .\CSV2SCIM.ps1 -Path '..\Samples\csv-with-2-records.csv' -AttributeMapping $AttributeMapping -TenantId "contoso.onmicrosoft.com" -ServicePrincipalId "<ProvisioningAppObjectId>" -ClientId "<AppClientId>" -ClientCertificate (Get-ChildItem Cert:\CurrentUser\My\$ThumbPrint)
    ```
3. 上記の要求 **の処理を** 確認するには、プロビジョニング アプリの [プロビジョニング ログ] ブレードにアクセスします。

### カスタム SCIM スキーマを使用して一括要求を生成する

このセクションでは、カスタム SCIM スキーマ名前空間を使用して、CSV ファイル内のフィールドから成る一括要求を生成する方法について説明します。

1. Notepad++ または Visual Studio Code などのソース コード エディターで、CSV ファイルの列を SCIM 標準スキーマ属性にマッピングできるようにする PowerShell データ ファイル `Samples/AttributeMapping.psd1` を開きます。 すぐに使用できる状態で出荷されたファイルには、CSV ファイルの列の対応する SCIM スキーマ属性への事前構成済みのマッピングが既に含まれています。
2. PowerShell を開き、ディレクトリ **CSV2SCIM\src** に変更します。
3. 次のコマンドを実行して、`AttributeMapping` 変数を初期化します。

    ```powershell
    $AttributeMapping = Import-PowerShellDataFile '..\Samples\AttributeMapping.psd1'
    ```
4. 次のコマンドを実行して、`AttributeMapping` ファイルに有効な SCIM スキーマ属性があるかどうかを検証します。 検証が成功した場合、このコマンドは **True を** 返します。

    ```powershell
    .\CSV2SCIM.ps1 -Path '..\Samples\csv-with-2-records.csv' -AttributeMapping $AttributeMapping -ValidateAttributeMapping
    ```
5. SCIM コア ユーザー属性およびエンタープライズ ユーザー属性に加えて、カスタム SCIM スキーマ名前空間 `urn:ietf:params:scim:schemas:extension:contoso:1.0:User` ですべての CSV フィールドのフラット リストを取得するには、次のコマンドを実行します。

    ```powershell
     .\CSV2SCIM.ps1 -Path '..\Samples\csv-with-2-records.csv' -AttributeMapping $AttributeMapping -ScimSchemaNamespace "urn:ietf:params:scim:schemas:extension:contoso:1.0:User"  > BulkRequestPayloadWithCustomNamespace.json
    ```

    カスタム SCIM スキーマ名前空間の下に CSV フィールドが表示されます。 [Image: カスタム スキーマの下のユーザーの詳細のスクリーンショット。]

### プロビジョニング ジョブ スキーマを拡張する

多くの場合、人事チームによって送信されるデータ ファイルには、標準 SCIM スキーマで直接表現されていない属性が多く含まれています。 このような属性を表現するために、SCIM 拡張スキーマを作成して、この名前空間の下に属性を追加することをお勧めします。

CSV2SCIM スクリプトは、`UpdateSchema` という実行モードを提供します。これは、CSV ファイル内のすべての列を読み取り、拡張スキーマ名前空間の下に追加し、プロビジョニング アプリ スキーマを更新します。

Note

プロビジョニング アプリ スキーマに属性拡張機能が既に存在する場合、このモードでは属性拡張機能が既に存在するという警告だけが出力されます。 そのため、新しいフィールドが CSV ファイルに追加され、拡張機能として追加する場合、 **UpdateSchema** モードで CSV2SCIM スクリプトを実行しても問題はありません。

手順を説明するために、`Samples/csv-with-2-records.csv`存在 CSV ファイルを使用します。

1. CSV ファイル `Samples/csv-with-2-records.csv` を Notepad、Excel、または TextPad で開き、ファイルに存在する列をチェックします。

    [Image: CSV 列を確認する方法のスクリーンショット。]
2. 次のコマンドを実行します。

    ```powershell
    .\CSV2SCIM.ps1 -Path '..\Samples\csv-with-2-records.csv' -UpdateSchema -ServicePrincipalId <servicePrincipalId> -TenantId "contoso.onmicrosoft.com" -ScimSchemaNamespace "urn:ietf:params:scim:schemas:extension:contoso:1.0:User"
    ```
3. プロビジョニング アプリ スキーマの更新を確認するには、[ **属性マッピング** ] ページを開き、[詳細オプション] の [ **API の属性リストの編集]** オプションにアクセス **します**。
4. **属性リスト**には、新しい名前空間の下に属性が表示されます。

### 最新の同期サイクルのプロビジョニング ログを取得する

一括要求を送信した後、Microsoft Entra ID によって処理された最新の同期サイクルのログに対してクエリを実行できます。 同期の統計情報と処理の詳細は、PowerShell スクリプトを使用して取得して分析のために保存できます。

1. コンソールでログの詳細と同期の統計情報を表示するには、次のコマンドを実行します。

    ```powershell
    .\CSV2SCIM.ps1 -ServicePrincipalId <servicePrincipalId> -TenantId "contoso.onmicrosoft.com" -GetPreviousCycleLogs -NumberOfCycles 1
    ```

    [Image: 同期統計のスクリーンショット。]

    Note

    既定では NumberOfCycles は 1 です。 同期サイクルをさらに取得するには数値を指定します。
2. コンソールで同期の統計情報を表示してログの詳細を変数に保存するには、次のコマンドを実行します。

    ```powershell
    $logs=.\CSV2SCIM.ps1 -ServicePrincipalId <servicePrincipalId> -TenantId "contoso.onmicrosoft.com" -GetPreviousCycleLogs
    ```

    クライアント証明書認証を使用してコマンドを実行するには、`ServicePrincipalId`、`ClientId`、`TenantId` に正しい値を指定してコマンドを実行します。

    ```powershell
    $ClientCertificate = Get-ChildItem -Path cert:\CurrentUser\my\ | Where-Object {$_.Subject -eq "CN=CSV2SCIM"}  
    $ThumbPrint = $ClientCertificate.ThumbPrint
    
    $logs=.\CSV2SCIM.ps1 -ServicePrincipalId "<ProvisioningAppObjectId>" -TenantId "contoso.onmicrosoft.com" -ClientId "<AppClientId>" -ClientCertificate (Get-ChildItem Cert:\CurrentUser\My\$ThumbPrint) -GetPreviousCycleLogs -NumberOfCycles 1
    ```

    - 特定のレコードの詳細を表示するには、コレクションをループ処理するか、その特定のインデックスを選択します (`$logs[0]` など)

        [Image: 選択したインデックスのスクリーンショット。]
    - `where-object` ステートメントを使用して、sourceID または DisplayName を使用して特定のレコードを検索することもできます。 **ProvisioningLogs** プロパティでは、その特定のレコードに対して実行された操作のすべての詳細を確認できます。

        ```powershell
        $user = $logs | where sourceId -eq '1222'
        $user.ProvisioningLogs | fl
        ```

        [Image: プロビジョニング ログのスクリーンショット。]
    - **ModifiedProperties** 属性では、特定のユーザーが影響を受けるプロパティを確認できます。 `$user.ProvisioningLogs.ModifiedProperties`

        [Image: プロパティのスクリーンショット。]

### 付録

#### CSV2SCIM PowerShell の使用の詳細

CSV2SCIM PowerShell スクリプトで受け入れられるコマンドライン パラメーターの一覧を次に示します。

```powershell
PS > CSV2SCIM.ps1 -Path <path-to-csv-file> 
[-ScimSchemaNamespace <customSCIMSchemaNamespace>] 
[-AttributeMapping $AttributeMapping] 
[-ServicePrincipalId <spn-guid>] 
[-ValidateAttributeMapping]
[-UpdateSchema]
[-ClientId <client-id>]
[-ClientCertificate <certificate-object>]
[-RestartService]
```

Note

`AttributeMapping` と `ValidateAttributeMapping` のコマンドライン パラメーターは、CSV 列属性の標準 SCIM スキーマ要素へのマッピングを参照します。 ソース SCIM スキーマ要素とターゲット Microsoft Entra /オンプレミスの Active Directory 属性の間で Microsoft Entra 管理センターのプロビジョニング アプリで実行する属性マッピングは参照しません。

| パラメーター | 説明 | 処理に関する注釈 |
| --- | --- | --- |
| パス | CSV ファイルへの完全なパスまたは相対パス。 例: `.\Samples\csv-with-1000-records.csv` | 必須: はい |
| ScimSchemaNamespace | CSV ファイル内のすべての列を、特定の名前空間に属するカスタム SCIM 属性として送信するために使用するカスタム SCIM スキーマ名前空間。 たとえば、`urn:ietf:params:scim:schemas:extension:csv:1.0:User` のように指定します。 | 必須: 次のことを実行する場合のみ:- プロビジョニング アプリ スキーマを更新するか、カスタム SCIM 属性をペイロードに含める場合。 |
| 属性マッピング | CSV ファイル内の列を SCIM コア ユーザー属性およびエンタープライズ ユーザー属性にマップする PowerShell データ (.psd1 拡張機能) ファイルを指します。 CSV2SCIM スクリプトの ファイルAttributeMapping.psd 例を参照してください。 例: `powershell $AttributeMapping = Import-PowerShellDataFile '.\Samples\AttributeMapping.psd1'`-AttributeMapping $AttributeMapping` | 必須: はい  これを指定する必要がない唯一のシナリオは、`UpdateSchema` スイッチを使用する場合のみです。 |
| 属性マッピングの検証 | スイッチ フラグを使用して、AttributeMapping ファイルに SCIM コアおよびエンタープライズ ユーザー スキーマに準拠した属性が含まれていることを検証します。 | 必須: いいえ コンプライアンスを確保するために使用することをお勧めします。 |
| ServicePrincipalId（サービスプリンシパルID） | プロビジョニング アプリのサービス プリンシパル ID の GUID 値を、**プロビジョニング アプリ**の&gt;**プロパティ**&gt;**オブジェクト ID** から取得できます。 | 必須: 次のことを実行する場合のみ: - プロビジョニング アプリ スキーマを更新するか、生成された一括要求を API エンドポイントに送信する場合。 |
| UpdateSchema | このスイッチを使用して、CSV 列を読み取り、プロビジョニング アプリ スキーマにカスタム SCIM 属性として追加するようにスクリプトに指示します。 |  |
| ClientId | OAuth 認証フローに使用する Microsoft Entra 登録済みアプリのクライアント ID。 このアプリには、有効な証明書の資格情報が必要です。 | 必須: 証明書ベースの認証を実行する場合のみ。 |
| クライアント証明書 | OAuth フロー中に使用するクライアント認証証明書。 | 必須: 証明書ベースの認証を実行する場合のみ。 |
| 前回サイクルのログを取得 | 最新の同期サイクルのプロビジョニング ログを取得します。 |  |
| サイクル数 | 取得する必要がある同期サイクルの数を指定します。 既定では、この値は 1 です。 |  |
| サービス再起動 | このオプションを使用すると、スクリプトはデータをアップロードする前にプロビジョニング ジョブを一時停止し、データをアップロードしてからジョブを再開して、ペイロードが即時処理されるようにします。 | このオプションはテスト時だけ使用します。 |

#### AttributeMapping.psd ファイル

このファイルは、CSV ファイル内の列を標準の SCIM コア ユーザー属性およびエンタープライズ ユーザー属性スキーマ要素にマップするために使用されます。 また、このファイルは、CSV ファイルの内容を一括要求ペイロードとして適切に表現します。

次の例では、CSV ファイルの次の列が、対応する SCIM コア ユーザー属性およびエンタープライズ ユーザー属性にマップされています。

[Image: マップされた属性の CSV 列のスクリーンショット。]

```powershell
    @{
    externalId   = 'WorkerID'
    name         = @{
        familyName = 'LastName'
        givenName  = 'FirstName'
    }
    active       = { $_.'WorkerStatus' -eq 'Active' }
    userName     = 'UserID'
    displayName  = 'FullName'
    nickName     = 'UserID'
    userType     = 'WorkerType'
    title        = 'JobTitle'
    addresses    = @(
        @{
            type          = { 'work' }
            streetAddress = 'StreetAddress'
            locality      = 'City'
            postalCode    = 'ZipCode'
            country       = 'CountryCode'
        }
    )
    phoneNumbers = @(
        @{
            type  = { 'work' }
            value = 'OfficePhone'
        }
    )
    "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User" = @{
        employeeNumber = 'WorkerID'
        costCenter     = 'CostCenter'
        organization   = 'Company'
        division       = 'Division'
        department     = 'Department'
        manager        = @{
            value = 'ManagerID'
        }
    }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/insufficient-access-rights-error-troubleshooting"} -->
## アクセス権の不足エラーのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/insufficient-access-rights-error-troubleshooting
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: オンプレミスの Active Directory にプロビジョニングするときに InsufficientAccessRights エラーのトラブルシューティングを行う方法について説明します。

### 問題点

Active Directory への受信ユーザー プロビジョニングは、ほとんどのユーザーが想定どおりに動作しています。 ただし、一部のユーザーについては、プロビジョニング ログに次のエラーが表示されます。

```
ERROR: InsufficientAccessRights-SecErr: The user has insufficient access rights.. Access is denied. \nError Details: Problem 4003 - INSUFF_ACCESS_RIGHTS. 
OR

ERROR: 
"Message":"The user has insufficient access rights.",
"ResponseResultCode":"InsufficientAccessRights",
"ResponseErrorMessage":"00002098: SecErr: DSID-03150F94, problem 4003 (INSUFF_ACCESS_RIGHTS), data 0",
The user has insufficient access rights.

```

プロビジョニング ログは、`HybridSynchronizationActiveDirectoryInsufficientAccessRights` というエラー コードを表示します。

### 原因

既定で `provAgentgMSA$` プロビジョニング エージェント GMSA アカウントには、ドメイン内のすべてのユーザー オブジェクトに対する読み取り/書き込みアクセス許可があります。 以前に示したエラーの原因として考えられる原因は 2 つあります。

- 原因 1: ユーザー オブジェクトは、ドメイン レベルのアクセス許可を継承しない OU の一部です。
- 原因 2: ユーザー オブジェクトは [、保護された Active Directory グループ](https://go.microsoft.com/fwlink/?linkid=2240442)に属しています。 設計上、ユーザー オブジェクトは、 [`AdminSDHolder`](https://go.microsoft.com/fwlink/?linkid=2240377)と呼ばれる特殊なコンテナーに関連付けられているアクセス許可によって制御されます。 ここでは、 `provAgentgMSA$` アカウントが保護された Active Directory グループに属するこれらのアカウントを更新できない理由について説明します。 ユーザー アカウントに対する `provAgentgMSA$` アカウントの書き込みアクセス権をオーバーライドして明示的に指定しようとする場合がありますが、それは機能しません。 委任されたアクセス許可の誤用から特権ユーザー アカウントをセキュリティで保護するために、 [SDProp](https://go.microsoft.com/fwlink/?linkid=2240378) と呼ばれるバックグラウンド プロセスがあり、60 分ごとに実行され、保護されたグループに属するユーザーが常に `AdminSDHolder` コンテナーで定義されたアクセス許可によって管理されるようにします。 ドメイン管理者グループに `provAgentgMSA$` アカウントを追加する方法でも機能しません。

### 解決策

まず、問題の原因を確認します。 原因 1 が問題の原因であるかどうかを確認するには:

1. **Active Directory ユーザーとコンピューターの管理コンソールを開きます**。
2. ユーザーに関連付けられている OU を選択します。
3. 右クリックし、[ **プロパティ] -&gt; [セキュリティ] -&gt; [詳細設定]** に移動します。 **[継承の有効化]** ボタンが表示されている場合は、原因 1 が問題の原因であることを確認します。
4. ドメイン レベルのアクセス許可がこの OU に適用されるように、[ **継承の有効化] を** 選択します。
    注

    ドメイン レベルから影響を受けるアカウントを保持している OU まで、階層全体を必ず確認してください。 ドメイン レベルで適用されるアクセス許可が最終的なオブジェクトに連鎖するように、すべての親 OU/コンテナーで継承が有効になっている必要があります。

原因 1 が問題の原因でない場合は、原因 2 が問題の原因である可能性があります。 2 つの解決オプションが考えられます。

**オプション 1: 保護された AD グループから影響を受けるユーザーを削除する** この `AdminSDHolder` アクセス許可によって管理されるユーザーの一覧を見つけるには、Cx は次のコマンドを呼び出すことができます。

`Get-AdObject -filter {AdminCount -gt 0}`

参照アーティクル:

- AdminCount フラグをクリアし、影響を受けるユーザーの継承を再度有効にするために使用できる [PowerShell スクリプトの例](https://notesbytom.wordpress.com/2017/12/01/clear-admincount-and-enable-inheritance-on-user/) を次に示します。
- この記事に記載されている手順を使用する [- 孤立したアカウントを検索](https://social.technet.microsoft.com/wiki/contents/articles/33307.active-directory-find-orphaned-objects.aspx) して孤立したアカウントを検索します (保護されたグループに含まれていないが、AdminCount フラグが 1 に設定されているアカウント)

*オプション 1 が常に機能するとは限らない*

PDC エミュレーター FSMO ロールを保持しているドメイン コントローラーで 1 時間ごとに実行される、セキュリティ記述子伝達 (SDPROP) プロセスと呼ばれるプロセスがあります。 `AdminCount`属性を 1 に設定するのは、このプロセスです。 SDPROP の主な機能は、高い特権を持つ Active Directory アカウントを保護することです。 これにより、権限が低いユーザーまたはプロセスによって、これらのアカウントを誤ってまたは意図的に削除したり、権限を変更したりできないようにすることができます。 PDC エミュレーター FSMO ロールを保持しているドメイン コントローラーで 1 時間ごとに実行される、セキュリティ記述子伝達 (SDPROP) プロセスと呼ばれるプロセスがあります。 `AdminCount`属性を 1 に設定するのは、このプロセスです。 SDPROP の主な機能は、高い特権を持つ Active Directory アカウントを保護することです。 SDPROP プロセスを使用すると、アカウントを削除したり、権限を誤って持ったり、権限が低いユーザーやプロセスによって意図的に変更されたりできないようにすることができます。

理由を詳しく説明するリファレンス記事:

- [AdminHolder と SDProp に関する 5 つの一般的な質問](https://techcommunity.microsoft.com/t5/ask-the-directory-services-team/five-common-questions-about-adminsdholder-and-sdprop/ba-p/396293)
- [AdminSD Holder オブジェクトについて](https://petri.com/active-directory-security-understanding-adminsdholder-object/)

**オプション 2: AdminSDHolder コンテナーの既定のアクセス許可を変更する**

オプション 1 が実現可能ではなく、期待どおりに動作しない場合は、CX に依頼して、 `AdminSDHolder` コンテナーの既定のアクセス許可を変更することが許可されている場合は、AD 管理者とセキュリティ管理者に確認してください。 この [記事](https://go.microsoft.com/fwlink/?linkid=2240198) では、 `AdminSDHolder` コンテナーの重要性について説明します。 Cx が `AdminSDHolder` コンテナーのアクセス許可を更新するための内部承認を取得したら、アクセス許可を更新する 2 つの方法があります。

- この記事の説明に従って `ADSIEdit` を使用 [します](https://petri.com/active-directory-security-understanding-adminsdholder-object)。
- コマンド ライン スクリプト `DSACLS` 使用する。 開始点として使用できるスクリプトの例を次に示します。Cx は要件に従って調整できます。

```powershell

$dcFQDN = "<FQDN Of The Nearest RWDC Of Domain>"
$domainDN = "<Domain Distinguished Name>"
$domainNBT = "<Domain NetBIOS Name>"
$dsaclsCMD = "DSACLS '\\$dcFQDN\CN=AdminSDHolder,CN=System,$domainDN' /G '$domainNBT\provAgentgMSA$:RPWP;<Attribute To Write To>'"
Invoke-
Expression $dsaclsCMD | Out-Null
```

Cx でオンプレミスの AD アクセス許可のトラブルシューティングに関するヘルプが必要な場合は、Windows Server サポート チームに問い合わせてください。 [Microsoft Entra Connect に関する AdminSDHolder の問題](https://c7solutions.com/2017/03/administrators-aadconnect-and-adminsdholder-issues)に関するこの記事では、DSACLS の使用方法に関するその他の例を示します。

**オプション 3: provAgentgMSA アカウントにフル コントロールを割り当てる**

 アカウントに`provAgentGMSA`のアクセス許可を割り当てます。 ユーザー オブジェクトが保護されたユーザー グループに属していない場合に、あるコンテナー OU から別のコンテナー OU にユーザー オブジェクトを移動する際に問題がある場合は、この手順をお勧めします。

このシナリオでは、Cx に次の手順を完了し、移動操作を再テストするように依頼します。

1. 管理者として AD ドメイン コントローラーにログインします。
2. 管理者として `run` を使用して PowerShell コマンド ラインを開きます。
3. PowerShell プロンプトで、次の [DSACLS](https://go.microsoft.com/fwlink/?linkid=2240600) コマンドを実行して、プロビジョニング エージェントの GMSA アカウントに **Generic All/Full Control** を付与します。 `dsacls "dc=contoso,dc=com" /I:T /G "CN=provAgentgMSA,CN=Managed Service Accounts,DC=contoso,DC=com:GA"`

`dc=contoso,dc=com`をルート ノードまたは適切な OU コンテナーに置き換えます。 カスタム GMSA を使用している場合は、 `provAgentgMSA`の DN 値を更新します。

**オプション 4: GMSA アカウントをスキップし、手動で作成したサービス アカウントを使用** するこのオプションは、GMSA のアクセス許可の問題が調査されて解決されるまでブロックを解除するための一時的な回避策としてのみ使用する必要があります。 GMSA アカウントを使用することをお勧めします。 レジストリ オプションを設定して [GMSA 構成をスキップ](https://go.microsoft.com/fwlink/?linkid=2239993) し、適切なアクセス許可を持つ手動で作成されたサービス アカウントを使用するように Microsoft Entra Connect プロビジョニング エージェントを再構成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/isv-automatic-provisioning-multi-tenant-apps"} -->
## Microsoft Entra ID でマルチテナント アプリケーションの自動ユーザー プロビジョニングを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/isv-automatic-provisioning-multi-tenant-apps
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra ID で自動プロビジョニングを有効にするための独立系ソフトウェア ベンダー向けのガイド

自動ユーザープロビジョニングとは、SaaS アプリケーションなどのターゲット システムでユーザー ID の作成、保守、削除を自動化するプロセスのことです。

### 自動ユーザー プロビジョニングを有効にする理由

ユーザーが初めてサインインする前にユーザー レコードがアプリケーション内にあることが要求されるアプリケーションでは、ユーザー プロビジョニングが必要です。 サービス プロバイダーと顧客の両方にメリットがあります。

#### サービス プロバイダーにとってのメリット

- Microsoft ID プラットフォームを使用することでアプリケーションのセキュリティが強化されます。
- 顧客があなたのアプリケーションを採用するために行う実際の作業と認識されている作業が減ります。
- System for Cross-Domain Identity Management (SCIM) ベースのプロビジョニングを使用することで、自動ユーザー プロビジョニングに対応するための、複数の ID プロバイダー (IdP) との統合のコストが削減されます。
- 顧客がユーザー プロビジョニングの問題をトラブルシューティングするのに役立つ豊富なログを提供することで、サポート コストが削減されます。
- [Microsoft Entra アプリ ギャラリー](https://azuremarketplace.microsoft.com/marketplace/apps)でのアプリケーションの可視性が向上します。
- アプリのチュートリアル ページで優先順位が付けられた一覧を取得できます。

#### 顧客にとってのメリット

- ロールを変更するユーザーや組織を去るユーザーの、アプリケーションへのアクセス権を自動的に削除することで、セキュリティが強化されます。
- 手動によるプロビジョニングに関連した人為的エラーや繰り返し作業を回避することで、アプリケーションのユーザー管理が簡素化されます。
- 独自に開発したプロビジョニング ソリューションのホスティングとメンテナンスのコストが削減されます。

### プロビジョニング方法を選択する

Microsoft Entra ID には、アプリケーションの自動ユーザー プロビジョニングを有効にするための統合パスがいくつか用意されています。

- [Microsoft Entra プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)では、Microsoft Entra ID からアプリケーションへ (外向きのプロビジョニング) とアプリケーションから Microsoft Entra ID へ (内向きのプロビジョニング) のユーザーのプロビジョニングとプロビジョニング解除が管理されます。 このサービスは、あなたのアプリケーションによって提供される System for Cross-Domain Identity Management (SCIM) ユーザー管理 API エンドポイントに接続します。
- [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/) を使用しているときに、そのアプリケーションでは、Microsoft Graph API のクエリを実行し、Microsoft Entra ID からそのアプリケーションに対するユーザーとグループの内向きおよび外向きのプロビジョニングが管理されます。
- アプリケーションでフェデレーションに SAML が使用されている場合は、Security Assertion Markup Language Just in Time (SAML JIT) のユーザー プロビジョニングを有効にすることができます。 これは、SAML トークンで送信された要求情報を使用してユーザーのプロビジョニングを行います。

アプリケーションで使用する統合オプションを決定するには、比較の概要の表を参照してから、各オプションの詳細情報を確認してください。

| 自動プロビジョニングによって有効化または強化される機能 | Microsoft Entra プロビジョニング サービス (SCIM 2.0) | Microsoft Graph API (OData v4.0) | SAML JIT |
| --- | --- | --- | --- |
| Microsoft Entra ID でのユーザーとグループの管理 | √ | √ | User only |
| オンプレミスの Active Directory から同期されるユーザーおよびグループを管理する | √\* | √\* | User only\* |
| Microsoft 365 データへのアクセスのプロビジョニング時にユーザーやグループを超えてデータにアクセスする (Teams、SharePoint、メール、カレンダー、ドキュメントなど) | X+ | √ | X |
| ビジネス ルールに基づいてユーザーの作成、読み取り、更新を行う | √ | √ | √ |
| ビジネス ルールに基づいてユーザーを削除する | √ | √ | X |
| Microsoft Entra 管理センターからすべてのアプリケーションの自動ユーザー プロビジョニングを管理する | √ | X | √ |
| 複数の ID プロバイダーをサポートする | √ | X | √ |
| ゲスト アカウント (B2B) をサポートする | √ | √ | √ |
| エンタープライズ以外のアカウント (B2C) をサポートする | X | √ | √ |

^\*^ - AD から Microsoft Entra ID にユーザーを同期するには、Microsoft Entra Connect のセットアップが必要です。^+^– プロビジョニングに SCIM を使用しても、他の目的のためにアプリケーションを Microsoft Graph と統合することが妨げられることはありません。

### Microsoft Entra プロビジョニング サービス (SCIM)

Microsoft Entra プロビジョニング サービスは、多くの ID プロバイダー (IdP) とアプリケーション (Slack、G Suite、Dropbox など) によってサポートされているプロビジョニングの業界標準である [SCIM](https://aka.ms/SCIMOverview) を使用します。 Microsoft Entra ID に加えて IdP をサポートする場合は、Microsoft Entra プロビジョニング サービスを使用することをお勧めします。これは、SCIM 準拠の IdP が SCIM エンドポイントに接続できるためです。 単純な /User エンドポイントを構築すると、独自の同期エンジンを維持しなくてもプロビジョニングを有効にすることができます。

[外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)で SCIM を使用して、Microsoft Entra 外部 ID からサポートされているアプリにユーザーを自動的にプロビジョニングすることもできます。 これにより、手動で作業することなく、ユーザー情報を最新の状態に保つことができます。 このサービスでは差分クエリが使用されるため、前回の更新以降の変更のみが同期されるため、パフォーマンスの向上とシステム負荷の軽減に役立ちます。

Microsoft Entra プロビジョニング サービスでの SCIM の使用方法について詳しくは、以下を参照してください。

- [SCIM 標準についての詳細情報](https://aka.ms/SCIMOverview)
- [System for Cross-Domain Identity Management (SCIM) を使用して Microsoft Entra ID からユーザーとグループをアプリケーションに自動的にプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)
- [Microsoft Entra SCIM 実装について理解する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)

### プロビジョニングのための Microsoft Graph

プロビジョニングに Microsoft Graph を使用すると、Graph で使用可能なすべてのリッチ ユーザー データにアクセスできます。 ユーザーとグループの詳細情報に加え、ユーザーのロール、マネージャーと直属の部下、所有するデバイスと登録されたデバイス、および [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/overview) で利用可能なその他の数百のデータなどの追加情報も取得できます。

1,500 万を超える組織、および Fortune 500 に選ばれた企業の 90% が、Microsoft 365、Microsoft Azure、Enterprise Mobility Suite などの Microsoft クラウド サービスをサブスクライブしながら Microsoft Entra ID を使用しています。 Microsoft Graph を使用して、従業員のオンボーディング (と退職)、プロファイルの保守などの管理ワークフローとアプリを統合できます。

プロビジョニングでの Microsoft Graph の使用の詳細については、次をご覧ください。

- [Microsoft Graph ホーム ページ](https://developer.microsoft.com/graph)
- [Microsoft Graph の概要](https://learn.microsoft.com/ja-jp/graph/overview)
- [Microsoft Graph 認証の概要](https://learn.microsoft.com/ja-jp/graph/auth/)
- [Microsoft Graph の概要](https://developer.microsoft.com/graph/rest-api/)

### プロビジョニングに SAML JIT を使用する

アプリケーションへの初めてのサインインのときにのみユーザーをプロビジョニングする必要があり、ユーザーを自動的にプロビジョニング解除する必要はない場合は、SAML JIT を選択できます。 SAML JIT を使用するには、SAML 2.0 がフェデレーション プロトコルとしてアプリケーションでサポートされている必要があります。

SAML JIT は、SAML トークン内の要求情報を使用して、アプリケーションのユーザー情報を作成および更新します。 顧客は、必要に応じて、これらの必要な要求を Microsoft Entra アプリケーションで構成できます。 場合によっては、顧客がこの機能を使用できるようにするために、アプリケーション側から JIT プロビジョニングを有効にする必要があります。 SAML JIT はユーザーの作成と更新には役立ちますが、アプリケーションのユーザーを削除したり非アクティブにしたりすることはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/known-issues"} -->
## Microsoft Entra IDでのプロビジョニングに関する既知の問題 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues
- Service: entra-id / app-provisioning
- Article date: 2026-03-31
- Summary: Microsoft Entra IDで自動アプリケーション プロビジョニングまたはテナント間同期を使用する場合の既知の問題について説明します。

この記事では、アプリのプロビジョニングまたはテナント間同期を操作する場合に注意する必要がある既知の問題について説明します。 UserVoice でのアプリケーション プロビジョニング サービスに関するフィードバックを提供するには、[Microsoft Entra アプリケーション プロビジョニング UserVoice](https://aka.ms/appprovisioningfeaturerequest) を参照してください。 Microsoft では、サービスを改善するために UserVoice を注意深く確認しています。

注意

この記事は既知の問題の包括的な一覧ではありません。 一覧にない問題をご存じの場合は、ページの下部でフィードバックを提供してください。

::: zone pivot="cross-tenant-synchronization"

### テナント間同期

#### サポートされていない同期シナリオ

- グループ、デバイス、連絡先を別のテナントに同期する
- テナント間での写真の同期
- 連絡先の同期と B2B ユーザーへの連絡先の変換
- テナント間での会議室の同期

#### proxyAddresses や HiddenFromAddressListEnabled などの交換属性の更新

テナント間同期では、Entra のユーザー プロパティを管理できます。 Exchange 属性は直接管理されません。 例えば次が挙げられます。

- ProxyAddresses は、Microsoft Graph の読み取り専用プロパティです。 マッピングにソース属性として含めることができますが、ターゲット属性としては設定できません。
- テナント間同期では Entra の ShowInAddressList 属性を更新できますが、Exchange の HiddenFromAddressListEnabled を直接更新することはできません。
- Microsoft Exchange Onlineの ExternalEmailAddress プロパティにマップされる TargetAddress は、選択できる属性として使用できません。 この属性を変更する必要がある場合は、必要なオブジェクト経由で手動で行う必要があります。

#### SMS サインインが有効なユーザーはスキップされます

ソース (ホーム) テナントの外部ユーザーを別のテナントにプロビジョニングすることはできません。 ソース テナントの内部ゲスト ユーザーを別のテナントにプロビジョニングすることはできません。 ソース テナントの内部メンバー ユーザーのみをターゲット テナントにプロビジョニングできます。 詳細については、「Microsoft Entra B2B コラボレーション ユーザーの[Properties](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)」を参照してください。

さらに、SMS サインインが有効になっているユーザーをテナント間同期で同期することはできません。

#### showInAddressList プロパティの更新が失敗する

既存の B2B コラボレーション ユーザーの場合、B2B コラボレーション ユーザーがターゲット テナントでメールボックスを有効にしていない限り、showInAddressList 属性が更新されます。 メールボックスがターゲット テナントで有効になっている場合は、 [Set-MailUser](https://learn.microsoft.com/ja-jp/powershell/module/exchange/set-mailuser) PowerShell コマンドレットを使用して HiddenFromAddressListsEnabled プロパティを $false の値に設定します。

`Set-MailUser [GuestUserUPN] -HiddenFromAddressListsEnabled:$false`

ここで、[GuestUserUPN] は計算された UserPrincipalName です。 例:

`Set-MailUser guestuser1_contoso.com#EXT#@fabrikam.onmicrosoft.com -HiddenFromAddressListsEnabled:$false`

詳細については、「[Exchange Online PowerShell モジュールについて](https://learn.microsoft.com/ja-jp/powershell/exchange/exchange-online-powershell-v2)を参照してください。

#### メール属性が更新されない

ターゲット テナントのユーザーに Exchange ライセンスが割り当てられている場合、テナント間同期ではメール属性を更新できません。 これを回避するには、ユーザーの Exchange ライセンスを削除し、メール属性を更新して、ライセンスをユーザーに再度割り当てます。

#### ターゲット テナントからの同期の構成

ターゲット テナントからの同期の構成はサポートされていません。 すべての構成は、ソース テナントで行う必要があります。 ターゲット管理者は、テナント間の同期をいつでもオフにできます。

#### ソース テナント内の 2 人のユーザーが、ターゲット テナント内の同じユーザーと一致しました

ソース テナント内の 2 人のユーザーが同じメールを持っていて、両方のユーザーをターゲット テナントに作成する必要がある場合、1 人のユーザーがターゲットに作成されて、ソースの 2 人のユーザーにリンクされます。 メール属性がソース テナント内のユーザー間で共有されていないことを確認してください。 また、ソース テナント内のユーザーのメールが検証済みドメインから送信されていることを確認してください。 メールが未確認のドメインからのものである場合、外部ユーザーは正常に作成されません。

#### 異なるテナント間でアクセスするための Microsoft Entra B2B コラボレーションの使用

- B2B ユーザーは、ディレクトリ ピッカーがないため、リモート テナント (Exchange Online など) 内の特定のMicrosoft 365 サービスを管理できません。
- B2B ユーザー向けの Azure Virtual Desktop サポートの詳細については、[Azure Virtual Desktop](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/prerequisites?tabs=portal)[の](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/prerequisites?tabs=portal)前提条件 に関するページを参照してください。
- 外部メンバー ユーザーのPower BIサポートの最新情報については、[「Microsoft Entra B2Bを使用して外部ゲストユーザーにPower BIコンテンツを配布する」](https://learn.microsoft.com/ja-jp/fabric/enterprise/powerbi/service-admin-entra-b2b)を参照してください。

::: zone-end

### 認可

##### プロビジョニング モードを手動に変更することができない

プロビジョニングを初めて構成すると、プロビジョニング モードが手動から自動に切り替わることがわかります。 手動に戻すことはできません。 ただし、UI を使用してプロビジョニングをオフにすることはできます。 UI でプロビジョニングをオフにすると、ドロップダウンを手動に設定する場合と同様に動作します。

### 属性マッピング

##### 属性 SamAccountName または userType をソース属性として使用できない

**SamAccountName** 属性と **userType** 属性は、ソース属性として使用できません。 回避策として、代わりにディレクトリ拡張属性を使用できます。 詳細については、[ソース属性の欠落い](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)に関するページを参照してください。

##### スキーマ拡張のソース属性ドロップダウンがない

UI のソース属性ドロップダウンにスキーマの拡張機能が表示されない場合があります。 属性マッピングの詳細設定に移動し、手動で属性を追加します。 詳細については、[属性マッピングのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)に関するページを参照してください。

##### null 属性をプロビジョニングできない

Microsoft Entra ID現在、null 属性をプロビジョニングできません。 ユーザー オブジェクトの属性が null の場合は、スキップされます。

##### プロパティの結合では特殊文字はサポートされていません

Microsoft Entra ID現在、特殊文字を含む値に対してフィルター クエリを実行することはできません。 そのため、フィルター属性に特殊文字を含むリソース (ユーザーまたはグループ) に対するプロビジョニングの試行は失敗します。 たとえば、名前に特殊文字を持つグループはMicrosoft Entra IDに作成できますが、ターゲット システムに同期することはできません。

##### 属性マッピング式の最大文字数

属性マッピング式には、最大 10,000 文字を使用できます。

##### サポートされていないスコープ フィルター

**appRoleAssignments**、**userType**、**manager**、**date-type** 属性 (StatusHireDate、startDate、endDate、StatusTerminationDate、accountExpires など) は、スコープ フィルターとしてサポートされていません。

::: zone pivot="cross-tenant-synchronization"

##### OtherMails をターゲット属性として属性マッピングに含めないでください。

otherMails プロパティは、ターゲット テナントで自動的に計算されます。 ターゲット テナントで直接ユーザー オブジェクトを変更すると、otherMails プロパティが更新され、テナント間同期によって設定された値がオーバーライドされる可能性があります。 結果として、otherMails は、ターゲット属性としてテナント間同期属性マッピングに含めるべきではありません。

::: zone-end

##### 複数値ディレクトリ拡張機能

複数値ディレクトリ拡張機能は、属性マッピングまたはスコープ フィルターでは使用できません。

##### SCIM の多値アドレス、メールアドレス、電話番号

現在、 `addresses`、 `emails` 、および `phoneNumbers` の SCIM の複数値属性は、サポートされている `type` 値に対してのみ処理されます。 `addresses[type eq "home"]`、`addresses[type eq "any-other-value"]`、`emails[type eq "home"]`、または`phoneNumbers[type eq "home"]`を参照する属性マッピングは処理されません。 `addresses[type eq "work"]`、`emails[type eq "work"]`、および`phoneNumbers[type eq "work"]`のみが処理されます。 他のすべての型はスキップされます。

### サービスに関する問題

##### サポートされていないシナリオ

- パスワードのプロビジョニングはサポートされていません。
- 最初のレベルを超える入れ子構造のグループのプロビジョニングはサポートされていません。
- B2C テナントでは、内部または外部へのプロビジョニングがサポートされていません。
- System for Cross-domain Identity Management (SCIM) を使用した受信プロビジョニングはサポートされていません。 代わりに Microsoft Graph と [Microsoft Graph のバッチ](https://learn.microsoft.com/ja-jp/graph/json-batching) を使用してください。
- すべてのクラウドですべてのプロビジョニング アプリが使用できるわけではありません。

::: zone pivot="app-provisioning"

##### OIDC ベースのアプリケーションで自動プロビジョニングを使用できない

アプリの登録を作成した場合、エンタープライズ アプリ内の対応するサービス プリンシパルが、自動ユーザー プロビジョニングで有効になりません。 ギャラリーにアプリを追加するように要求するか (複数の組織での使用を想定している場合)、プロビジョニング用にギャラリー以外の 2 つ目のアプリを作成する必要があります。

##### マネージャーがプロビジョニングされない

ユーザーとそのマネージャーが両方ともプロビジョニング対象となっている場合は、ユーザーがプロビジョニングされた後でマネージャーが更新されます。 初日に、ユーザーがプロビジョニング対象でマネージャーが対象外となっている場合、マネージャーを参照せずにユーザーがプロビジョニングされます。 マネージャーがスコープに入ったとしても、プロビジョニングを再開し、サービスがすべてのユーザーをもう一度評価するまで、マネージャーの参照は更新されません。

##### ギャラリー アプリケーションは、米国政府または 21Vianet (中国) クラウドでのプロビジョニングをサポートしていません

非ギャラリー/カスタム アプリケーションと ecma コネクタは、米国政府/21Vianet (中国) クラウドでのプロビジョニングに使用できます。 これらの環境では、限られた数のギャラリー アプリケーションを使用できます。

::: zone-end

##### プロビジョニングの間隔が固定されている

プロビジョニング サイクル間の[時間](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user#how-long-will-it-take-to-provision-users)は現在構成できません。

##### ターゲット アプリから Microsoft Entra ID へ変更が移動していません。

アプリのプロビジョニング サービスでは、外部アプリで行われた変更が認識されません。 そのため、ロールバックするアクションは行われません。 アプリ プロビジョニング サービスは、Microsoft Entra IDで行われた変更に依存します。

##### [すべて同期] から [割り当てられた同期] への切り替えが機能しない

**[すべて同期]** から **[割り当てられた同期]** にスコープを変更したら、変更を有効にするために必ず再起動も実行します。 再起動は UI から実行できます。

##### プロビジョニング サイクルが完了するまで続行される

`enabled = off` のプロビジョニングを設定するか、 **[停止]** を選択すると、現在のプロビジョニング サイクルが完了するまで実行は続行されます。 プロビジョニングを再び有効にするまで、サービスによって今後のサイクルの実行が停止されます。

##### グループのメンバーがプロビジョニングされていない

グループがスコープ内にあり、メンバーがスコープ外にある場合、グループはプロビジョニングされます。 スコープ外のユーザーはプロビジョニングされません。 メンバーがスコープ内に戻っても、サービスではすぐに変更が検出されません。 プロビジョニングを再開すると、問題が解決されます。 すべてのユーザーが適切にプロビジョニングされるようにするため、サービスを定期的に再起動してください。

##### グローバルリーダー

グローバル閲覧者ロールはプロビジョニング構成を読み取ることができません。 Microsoft Entra管理センターからプロビジョニング構成を読み取るために、`microsoft.directory/applications/synchronization/standard/read` アクセス許可を持つカスタム ロールを作成します。

##### Microsoft Azure Government クラウド

シークレット トークン、通知メール、SSO 証明書通知メールなどの資格情報には、Microsoft Azure Government Cloud では 1 KB の制限があります。

::: zone pivot="app-provisioning"

### オンプレミス アプリケーションのプロビジョニング

これは、Microsoft Entra ECMA コネクタ ホストとオンプレミス アプリケーション プロビジョニングに関する既知の制限事項の現在の一覧です。

#### SQL コネクタの接続

SQL コネクタは、DSN ファイルが UTF-8 でエンコードされることを想定しています。 その他のエンコードが正しく読み取らず、"データ ソース名が見つからない、既定のドライバーが指定されていません" というエラーが発生する可能性があります。

#### アプリケーションとディレクトリ

次のアプリケーションとディレクトリは、まだサポートされていません。

##### Active Directory Domain Services (オンプレミス プロビジョニング プレビューを使用したMicrosoft Entra IDからのユーザーまたはグループの書き戻し)

- ユーザーが Microsoft Entra Connect によって管理されている場合、権限のソースはオンプレミスのActive Directory Domain Servicesです。 そのため、Microsoft Entra IDでユーザー属性を変更することはできません。 このプレビューでは、Microsoft Entra Connect によって管理されるユーザーの権限のソースは変更されません。
- Microsoft Entra Connect とオンプレミス プロビジョニングを使用してグループまたはユーザーをActive Directory Domain Servicesにプロビジョニングしようとすると、ループが作成される可能性があります。Microsoft Entra Connect は、クラウド内のプロビジョニング サービスによって行われた変更を上書きできます。 Microsoft では、グループまたはユーザーの書き戻し専用の機能に取り組んでいます。 [この Web サイト](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789/)で UserVoice のフィードバックに賛成投票して、プレビューの状態を追跡します。 または、Microsoft Entra IDからActive Directoryへのユーザーまたはグループの書き戻しに[Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016)を使用することもできます。

##### Microsoft Entra ID

オンプレミス のプロビジョニングを使用すると、既にMicrosoft Entra IDユーザーを取得し、サード パーティ製アプリケーションにプロビジョニングできます。 *サードパーティ製アプリケーションのディレクトリにユーザーを持ち込むことはできません。* ユーザーをディレクトリに取り込むには、ネイティブの人事統合、Microsoft Entra Connect、Microsoft Identity Manager、またはMicrosoft Graphに依存する必要があります。

#### 属性とオブジェクト

次の属性とオブジェクトはサポートされていません。

- 複数値の属性。
- 参照属性 (manager など)。
- グループ。
- 複雑なアンカー (ObjectTypeName+UserName など)。
- "." や "[" などの文字を持つ属性
- バイナリ属性。
- オンプレミスのアプリケーションは、Microsoft Entra IDとフェデレーションされておらず、ローカル パスワードが必要な場合があります。 オンプレミス プロビジョニングのプレビューでは、パスワードの同期はサポートされていません。 初期ワンタイム パスワードのプロビジョニングがサポートされています。 ログからのパスワードの編集には、必ず [Redact](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#redact) 関数を使用してください。 SQL および LDAP コネクタでは、パスワードは、アプリケーションへの最初の呼び出し時ではなく、パスワードが設定された 2 回目の呼び出し時にエクスポートされます。

##### SSL 証明書

現在、Microsoft Entra ECMA コネクタ ホストでは、SSL 証明書をAzureによって信頼するか、プロビジョニング エージェントを使用する必要があります。 証明書のサブジェクトは、ECMA コネクタ ホストがインストールされているMicrosoft Entraホスト名と一致する必要があります。

##### アンカー属性

現在、Microsoft Entra ECMA コネクタ ホストでは、アンカー属性の変更 (名前の変更) またはターゲット システムはサポートされていません。アンカーを形成するには複数の属性が必要です。

##### 属性の検出とマッピング

ターゲット アプリケーションがサポートする属性は、**Attribute Mappings** のMicrosoft Entra管理センターで検出され、表示されます。 新しく追加された属性は引き続き検出されます。 属性の種類 (文字列からブール型など) が変更され、その属性がマッピングの一部である場合、Microsoft Entra管理センターで型は自動的に変更されません。 ユーザーは [マッピング] の [詳細設定] にアクセスし、属性の種類を手動で更新する必要があります。

##### プロビジョニング エージェント

- エージェントでは、現在、オンプレミス アプリケーションのプロビジョニング シナリオの自動更新はサポートされていません。 このギャップを解消し、すべてのお客様に対して自動更新を既定で有効にし、必須にするための取り組みを積極的に行っています。
- 同じプロビジョニング エージェントを、オンプレミス アプリのプロビジョニングおよびクラウド同期または人事主導のプロビジョニングに使用することはできません。

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-application-provisioning-architecture"} -->
## Microsoft Entra オンプレミス アプリケーション プロビジョニングのアーキテクチャ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture
- Service: entra-id / app-provisioning
- Article date: 2026-05-05
- Summary: オンプレミス アプリケーション プロビジョニングのアーキテクチャの概要について説明します。

### 概要

次の図は、オンプレミス アプリケーション プロビジョニングのしくみを示しています。

[Image: オンプレミスのアプリケーション プロビジョニングのアーキテクチャを示す図。]

オンプレミス アプリケーションへのユーザー プロビジョニングには、主要なコンポーネントが 3 つあります。

- プロビジョニング エージェントは、Microsoft Entra ID とオンプレミス環境とを接続する役割を担います。
- Extensible Connectivity (ECMA) コネクタ ホストは、Microsoft Entra ID からのプロビジョニング要求を、ターゲット アプリケーションへの要求に変換します。 Microsoft Entra ID とアプリケーションとの間のゲートウェイとして機能します。 これを使用して、Microsoft Identity Manager で使用される既存の ECMA2 コネクタをインポートすることができます。 SCIM アプリケーションまたは SCIM ゲートウェイを構築済みである場合、ECMA ホストは必要ありません。
- Microsoft Entra プロビジョニング サービスは同期エンジンとして機能します。

注

Microsoft Identity Manager の同期は必要ありません。 ただし、ECMA ホストに ECMA2 コネクタをインポートする前の構築とテストに使用することができます。 ECMA2 コネクタは MIM に固有のもので、ECMA ホストはプロビジョニング エージェントでの使用に固有のものです。

#### ファイアウォールの要件

企業ネットワークへの受信接続を開く必要がありません。 プロビジョニング エージェントは、プロビジョニング サービスへのアウトバウンド接続のみを使用します。つまり、受信接続用のファイアウォール ポートを開放する必要はありません。 接続はすべてアウトバウンドであり、セキュリティで保護されたチャネル上で行われるため、境界 (DMZ) ネットワークは不要です。

プロビジョニング エージェントに必要な送信エンドポイントについて詳しく [説明します](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#firewall-and-proxy-requirements)。

### ECMA Connector Host のアーキテクチャ

ECMA Connector Host には、オンプレミスのプロビジョニングを実現するために使用されるいくつかの領域があります。 次の図は、これらの個々の領域を示す概念図です。 下の表で、各領域について詳細に説明しています。

[Image: ECMA コネクタ ホスト]

| Area | 説明 |
| --- | --- |
| エンドポイント | Microsoft Entra プロビジョニング サービスとの通信およびデータ転送を実行します |
| メモリ内キャッシュ | オンプレミスのデータ ソースからインポートしたデータを格納するために使用されます |
| 自動同期 | ECMA Connector Host とオンプレミスのデータソース間の非同期データ同期を提供します |
| ビジネス ロジック | ECMA Connector Host のすべてのアクティビティを調整するために使用されます。 自動同期の時刻は、ECMA ホストで構成できます。 これはプロパティ ページにあります。 |

#### アンカー属性と識別名について

次の情報は、アンカー属性と、genericSQL コネクタで使用される識別名について説明するために提供されています。

アンカー属性は、変更されないオブジェクトの種類の一意の属性であり、ECMA Connector Host のメモリ内キャッシュ内でそのオブジェクトを表します。

識別名 (DN) は、ディレクトリ階層内のオブジェクトの現在の場所を示すことによって、それを一意に識別する名前です。 または、パーティション内の SQL を使用します。 名前は、アンカー属性をディレクトリ パーティションのルートに連結して形成されます。

Active Directory や LDAP などのための従来の形式の DN では、以下のようなものが想定されます。

`CN=Lola Jacobson,CN=Users,DC=contoso,DC=com`

ただし、階層型ではなくフラットである SQL などのデータ ソースでは、DN がテーブルのいずれかに既に存在している必要があります。または、ECMA Connector Host に提供する情報から DN を作成する必要があります。

これは、genericSQL コネクタを構成するときにチェックボックスで [ **自動生成** ] をオンにすることで実現できます。 DN を自動生成することを選択すると、ECMA ホストによって LDAP 形式 (CN=&lt;anchorvalue&gt;,OBJECT=&lt;type&gt;) で DN が生成されます。 この場合、[接続] ページで [DN is Anchor] (DN はアンカー) が**オフ**になっていることも前提としています。

[Image: [DN is Anchor](DN はアンカー) がオフになっている]

genericSQL コネクタでは、LDAP 形式を使用して DN を設定することが想定されています。 汎用 SQL コネクタでは、コンポーネント名が "OBJECT=" の LDAP スタイルを使用しています。 これにより、パーティションを使用できるようになります (各オブジェクトの種類はパーティションです)。

ECMA Connector Host は現在、USER オブジェクトの種類のみをサポートしているため、OBJECT=&lt;type&gt; は OBJECT=USER になります。 したがって、アンカー値が ljacobson のユーザーの DN は次のようになります。

CN=ljacobson,OBJECT=USER

#### ユーザー作成ワークフロー

1. Microsoft Entra プロビジョニング サービスは、ユーザーが存在するかどうかを確認するために ECMA Connector Host に対してクエリを実行します。 **一致する属性**をフィルターとして使用します。 この属性は、Microsoft Entra 管理センターのエンタープライズ アプリケーション -&gt; オンプレミス プロビジョニング -&gt; プロビジョニング -&gt; 属性照合で定義されます。 照合の優先順位 1 によって示されています。 1 つ以上の一致する属性を定義し、優先順位に基づいて優先順位を付けることができます。 一致属性を変更する必要がある場合は、これを行うこともできます。 [Image: 一致する属性]
2. ECMA Connector Host は GET 要求を受け取り、その内部キャッシュでクエリを実行して、ユーザーが存在し、インポート済みかどうかを確認します。 これは、上記の一致する属性を使用して行われます。 複数の一致する属性を定義した場合、Microsoft Entra プロビジョニング サービスによって各属性に対する GET 要求が送信され、ECMA ホストによって、その属性が発見されるまで、キャッシュが一致しているかどうかの確認が行われます。
3. ユーザーが存在しない場合、Microsoft Entra ID ではユーザーを作成するために POST 要求が行われます。 ECMA Connector Host は、HTTP 201 を使用して Microsoft Entra ID に応答を送り返し、ユーザーに ID を提供します。 この ID は、[object types](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/オブジェクトの種類) ページで定義されているアンカー値から派生します。 このアンカーは、今後および後続の要求のために ECMA Connector Host に対してクエリを実行するために Microsoft Entra ID によって使用されます。
4. Microsoft Entra ID でユーザーに変更が加えられた場合、Microsoft Entra ID は、手順 1 の一致する属性ではなく、前の手順のアンカーを使用してユーザーを取得する GET 要求を行います。 これにより、たとえば、Microsoft Entra ID のユーザーとアプリの間のリンクを壊さずに UPN を変更することができます。

### エージェントのベスト プラクティス

- - エージェントと Azure との間のアウトバウンド TLS 通信に対しては、どのような形式のインライン検査も行わないでください。 この種のインライン検査では、通信フローが低下します。
- エージェントは Azure とアプリケーションの両方と通信を行う必要があるため、エージェントの配置場所はこの 2 つの接続の待ち時間に影響します。 各ネットワーク接続を最適化することによって、エンド ツー エンドのトラフィック待機時間を最小化することができます。 各接続を最適化する方法は次のとおりです。
- ホップの両端間の距離を短縮します。
- 経由する適切なネットワークを選択します。 たとえば、パブリック インターネットではなく、プライベート ネットワークを経由した方が、専用リンクのため高速である可能性があります。
- エージェントは、セキュリティで保護された通信に証明書を使用します。 有効期限の動作や運用環境での推奨事項など、システムが使用する 2 つの証明書の詳細については、「 証明書の管理」を参照してください。

### 証明書の管理

オンプレミスのアプリケーション プロビジョニングは、2 つの異なる証明書に依存します。 両方の証明書を理解することは、運用環境のデプロイを計画し、エージェントの正常性を監視し、サービスの中断を防ぐのに役立ちます。

#### ECMA コネクタ ホスト証明書

Microsoft Entra ECMA コネクタ ホストは、プロビジョニング エージェントが呼び出すポート 8585 (`https://localhost:8585/ecma2host_<connectorName>/scim`) で HTTPS エンドポイントを公開します。 ECMA コネクタ ホストが使用する TLS 証明書は、そのエンドポイントをセキュリティで保護します。 **Microsoft ECMA2Host 構成ウィザード**を初めて実行すると、証明書の作成を求めるメッセージが表示されます。そのためには、**Generate certificate** を選択します。 自動生成された証明書は自己署名され、証明書のサブジェクト別名 (SAN) はホスト名と一致します。

ECMA コネクタ ホスト証明書に関する重要な事実:

- 自己署名証明書はテスト専用です。 既定では 2 年で期限切れになり、取り消すことはできません。 Microsoftでは、運用環境で使用するために、信頼された証明機関 (CA) によって発行された証明書を使用することをお勧めします。
- 証明書のサブジェクトは、ECMA コネクタ ホストがインストールされているWindows サーバーのホスト名と一致する必要があります。 詳細については、 [SSL 証明書](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues#ssl-certificates)を参照してください。
- 期限切れの証明書を手動で置き換えます。 ECMA ホストの **[設定]** タブに移動して、証明書の有効期限を表示します。 証明書の有効期限が切れている場合は、[ **証明書の生成** ] を選択して新しい証明書を作成します。 詳細なガイダンスについては、「 [テスト接続の問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ecma-troubleshoot#troubleshoot-test-connection-issues)」を参照してください。

#### プロビジョニング エージェント登録証明書

プロビジョニング エージェントは、インストール時に個別の証明書を使用して、Microsoft ハイブリッド ID サービス (HIS) に自身を登録します。 エージェントはセットアップ中にこの証明書を作成し、Microsoft Entra トークンを使用して、エージェントと証明書の両方を HIS 登録サービスに登録します。 エージェントを HIS に登録する方法の詳細については、エージェントの [インストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-how-it-works#agent-installation)を参照してください。

Important

クラウド同期は、関連するクリーンアップ シナリオを文書化します。クラウド同期エージェントがアンインストールまたは停止され、その証明書の有効期限が切れると、エージェントは完全に削除され、Microsoft サービスと対話できなくなります。 詳細については、「 [アンインストール後のポータルからのエージェントの削除」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure#agent-removal-from-the-portal-after-uninstall)。 オンプレミスアプリケーションのプロビジョニングについては、登録証明書の有効期間または復旧手順に関するガイダンスが必要な場合は、Microsoftサポートにお問い合わせください。

サービス中断のリスクを最小限に抑えるには:

- Microsoft Entra 管理センターを使用してエージェントの正常性を定期的に監視します。
- 2 年間の自己署名有効期限が切れる前に、ECMA コネクタ ホスト証明書の交換を計画します。
- 運用環境のデプロイでは、自己署名 ECMA コネクタ ホスト証明書を、信頼された CA によって発行された証明書に置き換えます。

### 高可用性

高可用性/フェールオーバーのシナリオについては、次の情報を提供します。

ECMA コネクタを使用するオンプレミス アプリの場合: 推奨されるのは、データ センターごとに 1 つのアクティブ なエージェントと 1 つのパッシブ エージェント (構成済み、ただし停止済み、Microsoft Entra のエンタープライズ アプリには割り当てられない) です。

フェールオーバーを実行するときは、次の操作を行うことをお勧めします。

1. アクティブ エージェント (A) を停止します。
2. エンタープライズ アプリケーションからエージェント A の割り当てを解除します。
3. パッシブ エージェント (B) を再起動します。
4. エージェント B をエンタープライズ アプリケーションに割り当てます。

[Image: ECMA コネクタを使用した高可用性の図。]

SCIM コネクタを使用するオンプレミス アプリの場合: アプリケーションごとに 2 つのアクティブなエージェントを使用することをお勧めします。

[Image: SCIM コネクタを使用した高可用性の図。]

### プロビジョニング エージェントに関する質問

ここでは、いくつかの一般的な質問に回答します。

#### プロビジョニング エージェントのバージョンを確認する方法を教えてください。

1. プロビジョニング エージェントがインストールされている Windows サーバーにサインインします。
2. **コントロール パネル**に移動&gt;**プログラムのインストールまたは変更を行**います。
3. **Microsoft Entra Connect プロビジョニング エージェント**のエントリに対応するバージョンを探します。

#### Microsoft Entra Connect や Microsoft Identity Manager を実行しているのと同じサーバーにプロビジョニング エージェントをインストールできますか?

はい。 Microsoft Entra Connect や Microsoft Identity Manager を実行しているのと同じサーバーにプロビジョニング エージェントをインストールすることができます。ただし、それらは必須ではありません。

#### 送信 HTTP 通信にプロキシ サーバーを使用するようにプロビジョニング エージェントを構成するにはどうすればよいですか?

プロビジョニング エージェントは送信プロキシの使用をサポートしています。 エージェント ** 構成ファイルC:\Program Files\Microsoft Azure AD Connect Provisioning Agent\AADConnectProvisioningAgent.exe.config**を編集して構成できます。終了 `</configuration>` タグの直前に、ファイルの末尾に次の行を追加します。 `[proxy-server]` 変数と `[proxy-port]` 変数をお使いのプロキシ サーバー名とポート値に置き換えてください。

```
 <system.net>
  <defaultProxy enabled="true" useDefaultCredentials="true">
    <proxy
     usesystemdefault="true"
     proxyaddress="http://[proxy-server]:[proxy-port]"
     bypassonlocal="true"
    />
   </defaultProxy>
 </system.net>
```

#### プロビジョニング エージェントが Microsoft Entra テナントと通信できること、ファイアウォールがエージェントに必要なポートをブロックしていないことを確認するにはどうすればよいですか?

必要なポートがすべて開いているかどうかを確認することもできます。

#### プロビジョニング エージェントをアンインストールするにはどうすればよいですか?

1. プロビジョニング エージェントがインストールされている Windows サーバーにサインインします。
2. **コントロール パネル**に移動&gt;**プログラムのインストールまたは変更を行**います。
3. 次のプログラムをアンインストールします。

- Microsoft Entra Connect プロビジョニング エージェント
- Microsoft Entra Connect エージェント アップデーター
- Microsoft Entra Connect プロビジョニング エージェント パッケージ

### エージェントの更新

Microsoft、機能、修正プログラム、および更新プログラムを使用して、プロビジョニング エージェントの新しいバージョンを定期的にリリースします。 現在のバージョン履歴とリリース ノートについては、「[Microsoft Entra Connect Provisioning Agent: Version release history](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history)」を参照してください。

Important

現在、プロビジョニング エージェントでは、オンプレミスのアプリケーション プロビジョニング シナリオの自動更新はサポートされていません。 新しいバージョンがリリースされたら、エージェントを手動で更新する必要があります。 詳細については、既知の問題に関する記事の [「プロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues#provisioning-agent) 」を参照してください。

エージェントを最新の状態に保つには、

- Microsoft Entra 管理センターまたは [プロビジョニング エージェントのリリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history)を使用して、新しいバージョンを定期的に確認します。
- スケジュールされたメンテナンス期間中にエージェントの更新を計画して、プロビジョニングへの影響を最小限に抑えます。
- Microsoft では、エージェントの最新バージョンと 1 つ前のバージョンを直接サポートしています。 サポートされているこれらのバージョン内に留まり、必要なときにサポートを受けることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-custom-connector"} -->
## カスタム コネクタを使用したアプリケーションへの Microsoft Entra プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: このドキュメントでは、REST API と SOAP API を提供する外部システムでユーザーをプロビジョニングするように Microsoft Entra ID を構成する方法について説明します。

Microsoft Entra ID には、次のプロトコルとインターフェイスをサポートするアプリケーションにプロビジョニングするための接続が含まれています。

- [SCIM 2.0](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)
- [SQL](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/tutorial-ecma-sql-connector)
- [LDAP](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)
- [レスト](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector)
- [SOAP](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector)
- [PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-powershell-connector)

前述のプロトコルとインターフェイスのいずれかをサポートしていないアプリケーションへの接続のために、お客様と [パートナー](https://learn.microsoft.com/ja-jp/archive/technet-wiki/1589.fim-2010-mim-2016-management-agents-from-partners)、Microsoft Identity Manager (MIM) 2016 で使用するカスタム [ECMA 2.0](https://learn.microsoft.com/ja-jp/previous-versions/windows/desktop/forefront-2010/hh859557%28v=vs.100%29) コネクタを構築しています。 ECMA2 コネクタを使用すると、MIM 同期をデプロイしなくても、Microsoft Entra プロビジョニング エージェントと Extensible Connectivity (ECMA) コネクタ ホストを使用してアプリにプロビジョニングできます。

### MIM コネクタのエクスポートとインポート

MIM にカスタム ECMA 2.0 コネクタがある場合は、ここで 手順に従って、その構成をエクスポートできます。 コネクタの XML ファイル、DLL、および関連ソフトウェアを保存する必要があります。

コネクタをインポートするには、ここで [手順](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-migrate-microsoft-identity-manager#import-a-connector-configuration)使用できます。 コネクタの DLL とその前提条件の DLL を、サービス ディレクトリの同じ ECMA サブディレクトリにコピーする必要があります。 XML のインポート後、ウィザードを続行し、すべての必須フィールドが設定されていることを確認します。

### カスタム コネクタ DLL の更新

新しいビルドでコネクタを更新する場合は、必要なすべての場所で DLL が更新されていることを確認します。 カスタム コネクタ DLL を適切に更新するには、次の手順に従います。

1. Microsoft ECMA2Host 構成ウィザードを閉じます。
2. Microsoft ECMA2Host サービスを停止します。
3. カスタム コネクタ DLL を次の各フォルダーに手動で更新します。
    1. ECMA
    2. ECMA &gt; Cache &gt; {コネクタ名}
    3. ECMA &gt; キャッシュ &gt; {コネクタ名} &gt; AutosyncService
4. Microsoft ECMA2Host サービスを開始します。

手記

複数のコネクタが同じカスタム DLL を使用している場合は、コネクタごとに手順 3.ii と 3.iii を完了します。

### トラブルシューティング

MIM 用に構築されたカスタム コネクタは、[ECMA フレームワーク](https://learn.microsoft.com/ja-jp/previous-versions/windows/desktop/forefront-2010/hh859557%28v=vs.100%29)に依存します。 コネクタのインポートと使用に問題がある場合は、ベスト プラクティスに従っていることを確認してください。

- コネクタ内のメソッドがパブリックとして宣言されていることを確認する
- メソッド名からプレフィックスを除外する。 例えば：
    - **正解:** public Schema GetSchema (KeyedCollection&lt;string, ConfigParameter&gt; configParameters)
    - **不正解:** Schema PrefixGetSchema.GetSchema (KeyedCollection&lt;string, ConfigParameter&gt; configParameters)

次の表には、MIM と Microsoft Entra プロビジョニング エージェントの間で異なる ECMA フレームワークの機能が含まれています。 Microsoft Entra プロビジョニング サービスとオンプレミス アプリケーション プロビジョニングに関する既知の制限事項の一覧については、の を参照してください。

| **機能** | **コメント** |
| --- | --- |
| オブジェクトの種類 | プロビジョニング エージェントが 1 つのオブジェクトの種類を許可する |
| パーティション | プロビジョニング エージェントが 1 つのパーティションを許可する |
| 階層 | プロビジョニング エージェントで使用されない |
| 完全エクスポート | プロビジョニング エージェントで使用されない |
| ExportPasswordInFirstPass | サポートされていません |
| 正規化 | プロビジョニング エージェントで使用されない |
| 同時実行の操作 | プロビジョニング エージェントで使用されない |
| DeleteAddAsReplace | プロビジョニング エージェントで使用されない |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-ecma-troubleshoot"} -->
## オンプレミス アプリケーションへのプロビジョニングに関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ecma-troubleshoot
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: ECMA Connector Host をインストールして使用するときに発生する可能性のあるさまざまな問題のトラブルシューティング方法について説明します。

### テスト接続の問題をトラブルシューティングする

プロビジョニング エージェントと Extensible Connectivity (ECMA) ホストを構成したら、Microsoft Entra プロビジョニング サービスからプロビジョニング エージェント、ECMA ホスト、アプリケーションへの接続をテストします。 このエンド ツー エンド テストを実行するには、Azure portal でアプリケーションの **[テスト接続]** を選択します。 最初のエージェントを割り当てた後、またはエージェントを変更してから接続をテストする前に、必ず 10 分から 20 分待ってください。 この時間が過ぎた後にテスト接続に失敗した場合は、次のトラブルシューティング手順を試してください。

1. エージェントと ECMA ホストが実行中であることを確認します。

    1. エージェントがインストールされているサーバーの**スタート** ボタンをクリックし、**[ファイル名を指定して実行]** で「&gt;」と入力して、**[サービス]** を開きます。
    2. **[サービス]** に **Microsoft Entra Connect プロビジョニング エージェント**と **Microsoft ECMA2Host** サービスが存在し、それらの状態が [実行中] になっていることを確認します。

        [Image: ECMA サービスが実行中であることを示すスクリーンショット。]
2. ECMA コネクタ ホスト サービスが要求に応答していることを確認します。

    1. エージェントがインストールされているサーバーで、PowerShell を起動します。
    2. ECMA ホストがインストールされたフォルダー (`C:\Program Files\Microsoft ECMA2Host` など) に変更します。
    3. サブディレクトリ `Troubleshooting` に変更します。
    4. そのディレクトリでスクリプト `TestECMA2HostConnection.ps1` を実行します。 プロンプトが表示されたら、コネクタ名とシークレット トークンを引数として指定します。

        ```
        PS C:\Program Files\Microsoft ECMA2Host\Troubleshooting> .\TestECMA2HostConnection.ps1
        Supply values for the following parameters:
        ConnectorName: CORPDB1
        SecretToken: ************
        ```
    5. このスクリプトでは、SCIM GET または POST 要求を送信して、ECMA Connector Host が動作していて、要求に応答することを確認します。 出力に HTTP 接続が成功したことが示されない場合は、サービスが実行されていること、および正しいシークレット トークンが指定されていることを確認します。
3. Azure portal でアプリケーションに移動し、 **[admin connectivity](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/管理者接続)** を選択し、エージェントのドロップダウン リストを選択して、お使いのエージェントをアクティブにすることにより、エージェントを確実にアクティブにします。
4. 指定したシークレット トークンがオンプレミスのシークレット トークンと同じであるかどうかを確認します。 オンプレミスに移動し、シークレット トークンを再度指定し、Azure portal にそれをコピーします。
5. Azure portal で、必ず 1 つ以上のエージェントをアプリケーションに割り当てます。
6. エージェントを割り当てた後、登録が完了するまで 10 分から 20 分待つ必要があります。 登録が完了するまで、接続テストは機能しません。
7. 有効期限が切れていない有効な証明書を使用していることを確認します。 ECMA ホストの **[設定]** タブに移動して、証明書の有効期限を表示します。 証明書の有効期限が切れている場合は、`Generate certificate` をクリックして新しい証明書を生成します。
8. VM 上のタスク バーに移動し、Microsoft Entra Connect プロビジョニング エージェントを検索して、プロビジョニング エージェントを再起動します。 **[停止]** を右クリックして、 **[開始]** を選択します。
9. ECMA コネクタ ホストとプロビジョニング エージェントを再起動し、初期インポートが完了するまで待機した後も引き続き `The ECMA host is currently importing data from the target application` が表示される場合は、Azure portal でアプリケーションへのプロビジョニングの構成をいったん取り消してからもう一度やり直す必要が生じる場合があります。
10. Azure portal でテナントの URL を指定する場合は、必ず次のパターンに従ってください。 `localhost` を、お使いのホスト名に置き換えることができますが、必須ではありません。 `connectorName` を、ECMA ホストで指定したコネクタの名前に置き換えます。 エラー メッセージ 'invalid resource' (無効なリソース) は、一般に、URL が想定される形式に従っていないことを示しています。

    ```
    https://localhost:8585/ecma2host_connectorName/scim
    ```
11. プロビジョニング エージェント ログを確認するには、次のフォルダーに移動します。C:\ProgramData\Microsoft\Azure AD Connect プロビジョニング エージェント\Trace

    1. 次のエラーが表示される場合は、サービス アカウント "NT SERVICE\AADConnectProvisioningAgent" を "Performance Log Users" というローカル グループに追加してください。 これにより、アカウントが目的のレジストリ キー HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Perflib にアクセスできるようにすることで、"Unable to initialize metrics collector" という例外エラーが発生しなくなります。

```
Unable to initialize metrics collector, exception: 'System.UnauthorizedAccessException: Access to the registry key 'Global' is denied.
```

1. ECMA ホストを構成するときは、Windows サーバーのホスト名と一致するサブジェクトを証明書に指定してください。 ECMA ホストによって生成された証明書は自動的にこれを行いますが、テスト目的でのみ使用する必要があります。

```
Error code: SystemForCrossDomainIdentityManagementCredentialValidationUnavailable

Details: We received this unexpected response from your application: Received response from Web resource. Resource: https://localhost/Users?filter=PLACEHOLDER+eq+"8646d011-1693-4cd3-9ee6-0d7482ca2219" Operation: GET Response Status Code: InternalServerError Response Headers: Response Content: An error occurred while sending the request. Please check the service and try again.
```

### ECMA ホストの構成、イベント ビューアーでのログの表示、または ECMA ホスト サービスの起動ができない

次の問題を解決するには、管理者として ECMA ホスト設定ウィザードを実行します。

- ECMA ホスト ウィザードを開くとエラーが発生する。

    [Image: ECMA ウィザードのエラーを示すスクリーンショット。]
- ECMA ホスト ウィザードは構成できたが、ECMA ホスト ログを表示できない: このケースでは、ECMA ホスト構成ウィザードを管理者としてオープンし、コネクタの端から端までを設定する必要があります。 この手順は、既存のコネクタをエクスポートし、再度インポートすることで簡単に行うことができます。

    [Image: ホスト ログを示すスクリーンショット。]
- ECMA ホスト ウィザードは構成できたが、ECMA ホスト サービスを開始できない:

    [Image: ホスト サービスを示すスクリーンショット。]

### 詳細ログをオンにする

既定では、ECMA Connector Host の `switchValue` は `Verbose` に設定されます。 この設定では、トラブルシューティングの問題に役立つ詳細ログが出力されます。 出力されるログの数をエラーのみに制限する場合は、詳細レベルを `Error` に変更できます。 Windows 統合認証を使用せずに SQL コネクタを使用する場合、接続文字列がログに出力されないように、 `switchValue` を `Error` に設定することをお勧めします。 詳細レベルをエラーに変更するには、次に示すように、両方の場所で `switchValue` を "Error" に更新してください。

サービスの詳細ログ ファイルは、C:\Program Files\Microsoft ECMA2Host\Service\Microsoft.ECMA2Host.Service.exe.config にあります。

```
<?xml version="1.0" encoding="utf-8"?> 
<configuration> 
    <startup>  
        <supportedRuntime version="v4.0" sku=".NETFramework,Version=v4.6" /> 
    </startup> 
    <appSettings> 
      <add key="Debug" value="true" /> 
    </appSettings> 
    <system.diagnostics> 
      <sources> 
    <source name="ConnectorsLog" switchValue="Error"> 
          <listeners> 
            <add initializeData="ConnectorsLog" type="System.Diagnostics.EventLogTraceListener, System, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089" name="ConnectorsLog" traceOutputOptions="LogicalOperationStack, DateTime, Timestamp, Callstack"> 
              <filter type=""/> 
            </add> 
          </listeners> 
        </source> 
        <!-- Choose one of the following switchTrace:  Off, Error, Warning, Information, Verbose --> 
        <source name="ECMA2Host" switchValue="Error"> 
          <listeners>  
            <add initializeData="ECMA2Host" type="System.Diagnos
```

ウィザードのログ ファイルは、C:\Program Files\Microsoft ECMA2Host\Wizard\Microsoft.ECMA2Host.ConfigWizard.exe.config にあります。

```
      <source name="ConnectorsLog" switchValue="Error"> 
        <listeners> 
          <add initializeData="ConnectorsLog" type="System.Diagnostics.EventLogTraceListener, System, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089" name="ConnectorsLog" traceOutputOptions="LogicalOperationStack, DateTime, Timestamp, Callstack"> 
            <filter type=""/> 
          </add> 
        </listeners> 
      </source> 
      <!-- Choose one of the following switchTrace:  Off, Error, Warning, Information, Verbose --> 
      <source name="ECMA2Host" switchValue="Error"> 
        <listeners> 
          <add initializeData="ECMA2Host" type="System.Diagnostics.EventLogTraceListener, System, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089" name="ECMA2HostListener" traceOutputOptions="LogicalOperationStack, DateTime, Timestamp, Callstack" /> 
```

### ECMA ホスト キャッシュのクエリを実行する

ECMA ホストには、アプリケーション内のユーザーのキャッシュがあります。このキャッシュは、ECMA ホスト ウィザードのプロパティ ページで指定したスケジュールに従って更新されます。 キャッシュのクエリを実行するには、次の手順を実行します。

1. [デバッグ] フラグを `true` に設定します。

    デバッグ フラグを `true` に設定すると、ECMA ホストでの認証が無効になることに注意してください。 キャッシュのクエリの実行が完了したら、設定を `false` に戻して、ECMA ホスト サービスを再起動する必要があります。

    詳細サービス ログのファイル ロケーションは `C:\Program Files\Microsoft ECMA2Host\Service\Microsoft.ECMA2Host.Service.exe.config` です。

    ```
    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
       <startup>  
           <supportedRuntime version="v4.0" sku=".NETFramework,Version=v4.6" /> 
       </startup> 
       <appSettings> 
         <add key="Debug" value="true" /> 
       </appSettings> 
    
    ```
2. `Microsoft ECMA2Host` サービスを再開します。
3. ECMA ホストがターゲット システムに接続し、接続されている各システムからキャッシュを読み取り直すのを待ちます。 接続されているシステムに多数のユーザーがいる場合、このインポート プロセスには数分かかる場合があります。
4. ECMA ホストがインストールされているサーバーからこのエンドポイントのクエリを実行し、`{connector name}` を ECMA ホストのプロパティ ページで指定したコネクタの名前 `https://localhost:8585/ecma2host_{connectorName}/scim/cache` に置き換えます。

    1. エージェントがインストールされているサーバーで、PowerShell を起動します。
    2. ECMA ホストがインストールされたフォルダー (`C:\Program Files\Microsoft ECMA2Host` など) に変更します。
    3. サブディレクトリ `Troubleshooting` に変更します。
    4. そのディレクトリでスクリプト `TestECMA2HostConnection.ps1` を実行し、コネクタ名と `ObjectTypePath` 値 `cache` を引数として指定します。 ダイアログが表示されたら、そのコネクタ用に構成されたシークレット トークンを入力します。

        ```
        PS C:\Program Files\Microsoft ECMA2Host\Troubleshooting> .\TestECMA2HostConnection.ps1 -ConnectorName CORPDB1 -ObjectTypePath cache
        Supply values for the following parameters:
        SecretToken: ************
        ```
    5. このスクリプトでは、SCIM GET 要求を送信して、ECMA Connector Host が動作していて、要求に応答することを確認します。 出力に HTTP 接続が成功したことが示されない場合は、サービスが実行されていること、および正しいシークレット トークンが指定されていることを確認します。
5. キャッシュのクエリが完了したら、デバッグ フラグを `false` に戻すか、設定を削除します。
6. `Microsoft ECMA2Host` サービスを再開します。

### ターゲット属性がない

ターゲット アプリケーションの属性は、プロビジョニング サービスによって自動的に検出されます。 Azure portal のターゲット属性リストにターゲット属性が存在しないことがわかったら、次のトラブルシューティング手順を実行してください。

1. ECMA ホスト構成の **[属性の選択]** ページを確認し、Azure portal に公開する属性が選択されていることを確認します。
2. ECMA ホスト サービスが実行されていることを確認します。
3. エージェントをエンタープライズ アプリケーションに割り当て、テスト接続手順を完了し、管理者資格情報を保存したら、ブラウザーを更新します。 これにより、プロビジョニング サービスは /schemas 要求を行い、ターゲット属性が検出されます。
4. ECMA ホストのログを確認して、/schemas 要求が行われたことを確認し、その応答で属性を確認します。 これは、問題のトラブルシューティングをサポートするうえで有益な情報です。

### イベント ビューアーから ZIP ファイルとしてログを収集する

付属のスクリプトを使用して、イベント ログを ZIP ファイルにキャプチャしてエクスポートすることができます。

1. エージェントがインストールされているサーバーで、[スタート] メニューの [PowerShell] を右クリックし、`Run as administrator` を選択します。
2. ECMA ホストがインストールされたフォルダー (`C:\Program Files\Microsoft ECMA2Host` など) に変更します。
3. サブディレクトリ `Troubleshooting` に変更します。
4. そのディレクトリでスクリプト `CollectTroubleshootingInfo.ps1` を実行します。
5. スクリプトは、イベント ログを含む ZIP ファイルをそのディレクトリに作成します。

### イベント ビューアーでイベントを確認する

ECMA Connector Host のスキーマ マッピングが構成されたら、サービスを始動して受信接続を待ち受けます。 そのうえで、受信要求を監視します。

1. **スタート** メニューを選択し、「**イベント ビューアー**」と入力して、 **[イベント ビューアー]** を選択します。
2. **イベント ビューアー**で、 **[アプリケーションとサービス ログ]** を展開し、 **[Microsoft ECMA2Host ログ]** を選択します。
3. コネクタ ホストで変更が受信されると、イベントがアプリケーション ログに書き込まれます。

#### 一般的なエラー

| エラー | 解決方法 |
| --- | --- |
| ファイルまたはアセンブリ 'file:///C:\Program Files\Microsoft ECMA2Host\Service\ECMA\Cache\8b514472-c18a-4641-9a44-732c296534e8\Microsoft.IAM.Connector.GenericSql.dll' またはその依存関係の 1 つを読み込めませんでした。 アクセスが拒否されました。 | ネットワーク サービス アカウントに、キャッシュ フォルダーに対する 'フル コントロール' のアクセス許可があることを確認します。 アカウントにアクセス許可があるが、.NET がコネクタ DLL のコピーを作成しようとしている場合は、DLL を[グローバル アセンブリ キャッシュ](https://learn.microsoft.com/ja-jp/dotnet/framework/tools/gacutil-exe-gac-tool)に追加することが必要な場合があります。 |
| オブジェクトの DN の LDAP スタイルが無効です。 DN: username@domain.com" または `Target Site: ValidByLdapStyle` | ECMA ホストの [connectivity](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/接続) ページで [DN is Anchor](DN はアンカー) チェックボックスがオフになっていることを確認します。 ECMA ホストの [object types](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/オブジェクトの種類) ページで [autogenerated](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/自動生成) チェックボックスがオンになっていることを確認します。 詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。 |
| ExportErrorCustomContinueRun。 objectClass: 構文につき値番号が無効 | `objectClass` 属性へのプロビジョニング属性マッピングには、ディレクトリ サーバーによって認識されるオブジェクト クラスの名前のみが含まれていることを確認します。 |

### SCIM 受信リクエストを理解する

Microsoft Entra ID からプロビジョニング エージェントやコネクタ ホストに対して行われる要求には、SCIM プロトコルが使用されます。 ホストからアプリに対して行われる要求には、アプリでサポートされるプロトコルが使用されます。 ホストからエージェントへ、さらに Microsoft Entra ID に対して行われる要求には、SCIM が使用されます。 SCIM の実装の詳細については、「[チュートリアル: Microsoft Entra ID の SCIM エンドポイントのプロビジョニングを開発および計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)」を参照してください。

一般に、Microsoft Entra プロビジョニング サービスは get-user 呼び出しを行い、各プロビジョニング サイクルの開始時、オンデマンド プロビジョニングを実行する前、[およびテスト接続](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#request-3)が選択されている場合の 3 つの状況で**ダミー ユーザー**を確認します。 このチェックにより、ターゲット エンドポイントが使用でき、SCIM 準拠の応答が Microsoft Entra プロビジョニング サービスに返されることが保証されます。

### プロビジョニング エージェントをトラブルシューティングする方法

次のエラー シナリオが発生する場合があります。

#### エージェントが起動しない

次の内容のエラー メッセージが表示されることがあります。

サービス 'Microsoft Entra Connect Provisioning Agent' を開始できませんでした。 Check that you have sufficient privileges to start the system services. (システム サービスを起動するために十分な特権を持っていることを確認してください。)"

この問題は、通常、インストーラー (NT SERVICE\AADConnectProvisioningAgent) によって作成されたローカル NT サービスのサインイン アカウントにアクセス許可が適用されないようにするグループ ポリシーが原因で発生します。 サービスを開始するには、これらのアクセス許可が必要です。

これを解決するには、次の手順に従います。

1. 管理者アカウントでサーバーにサインインします。
2. **[サービス]** を開きます。これには、そこに直接移動するか、**スタート** ボタンをクリックし、&gt; で「**Services.msc**」と入力します。
3. **[サービス]** で **[Microsoft Entra Connect プロビジョニング エージェント]** をダブルクリックします。
4. **[ログオン]** タブで、 **[このアカウント]** をドメイン管理者に変更します。その後、サービスを再起動します。

このテストでは、エージェントがポート 443 を介して Azure と通信できることを確認します。 ブラウザーを開き、エージェントがインストールされているサーバーから前の URL に移動します。

#### エージェントがタイムアウトするまたは証明書が無効である

エージェントを登録しようとしたときに、次のエラー メッセージが表示されることがあります。

[Image: エージェントがタイムアウトになったことを示すスクリーンショット。]

この問題は、通常、エージェントがハイブリッド ID サービスに接続できないことが原因で発生し、HTTP プロキシを構成する必要があります。 この問題を解決するには、送信プロキシを構成します。

プロビジョニング エージェントでは、送信プロキシの使用がサポートされます。 構成するには、エージェントの構成ファイル *C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\AADConnectProvisioningAgent.exe.config* を編集します。ファイルの末尾近くにある `</configuration>` の終了タグの直前に次の行を追加します。 `[proxy-server]` 変数と `[proxy-port]` 変数をお使いのプロキシ サーバー名とポート値に置き換えてください。

```xml
    <system.net>
        <defaultProxy enabled="true" useDefaultCredentials="true">
            <proxy
                usesystemdefault="true"
                proxyaddress="http://[proxy-server]:[proxy-port]"
                bypassonlocal="true"
            />
        </defaultProxy>
    </system.net>
```

#### セキュリティ エラーが発生してエージェントの登録が失敗する

クラウド プロビジョニング エージェントのインストール時にエラー メッセージが表示されることがあります。

この問題は、通常、ローカルの PowerShell 実行ポリシーのせいで、エージェントで PowerShell 登録スクリプトを実行できないことが原因で発生します。

この問題を解決するには、サーバー上の PowerShell 実行ポリシーを変更します。 マシンとユーザーのポリシーを *Undefined* または *RemoteSigned* に設定する必要があります。 *Unrestricted* として設定されている場合、このエラーが表示されます。 詳細については、[PowerShell 実行ポリシー](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_execution_policies)に関する記事を参照してください。

#### ログ ファイル

既定では、エージェントによって発行されるエラー メッセージとスタック トレース情報は最小限に抑えられています。 トレース ログは、C:\ProgramData\Microsoft\Azure AD Connect Provisioning Agent\Trace フォルダーにあります。

エージェント関連の問題をトラブルシューティングするための詳細な情報を入手するには、次の手順に従います。

1. 「`AADCloudSyncTools`」の説明に従って、`AADCloudSyncTools` PowerShell モジュールをインストールします。
2. `Export-AADCloudSyncToolsLogs` PowerShell コマンドレットを使用して情報をキャプチャします。 次のスイッチを使用してデータ収集を微調整します。 次のコマンドを使用します。

    - **SkipVerboseTrace**: 詳細ログをキャプチャせずに現在のログのみをエクスポートします (既定値 = false)。
    - **TracingDurationMins**: 別のキャプチャ期間を指定します (既定値 = 3 分)。
    - **OutputPath**: 別の出力パスを指定します (既定値 = ユーザーのドキュメント)。

Microsoft Entra ID を使用することにより、クラウドでプロビジョニング サービスを監視し、オンプレミスでログを収集できます。 プロビジョニング サービスは、同期プロセスの一環として評価された各ユーザーのログを出力します。 それらのログは、[Azure portal の UI、API、Log Analytics](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs) を通じて利用することができます。 ECMA ホストでも、オンプレミスでログが生成されます。 これは、受信された各プロビジョニング要求および Microsoft Entra ID に送信された応答を示します。

#### エージェントのインストールの失敗

- エラー "`System.ComponentModel.Win32Exception: The specified service already exists`" は、以前の ECMA ホストが正しくアンインストールされなかったことを示します。 ホスト アプリケーションをアンインストールします。 Program Files に移動し、ECMA ホストのフォルダーを削除します。 バックアップ用に構成ファイルを保存することもできます。
- 次のエラーは、前提条件が満たされていないことを示します。 .NET 4.7.1 がインストールされていることを確認してください。

    ```
      Method Name : <>c__DisplayClass0_1 : 
      RegisterNotLoadedAssemblies Error during load assembly: System.Management.Automation.resources.dll
      --------- Outer Exception Data ---------
      Message: Could not load file or assembly 'file:///C:\Program Files\Microsoft ECMA2Host\Service\ECMA\System.Management.Automation.resources.dll' or one of its dependencies. The system cannot find the file specified.
    
    ```

#### SQL で ECMA Connector Host を構成しようとすると、無効な LDAP スタイルの DN エラーが発生する

既定では、汎用 SQL コネクタでは、LDAP スタイルを使用して DN が設定されていると想定されています (最初の接続ページで [DN is anchor] (DN はアンカー) 属性がオフの場合)。 エラー メッセージ `Invalid LDAP style DN` または `Target Site: ValidByLdapStyle` では、DN フィールドにコネクタで想定されている LDAP スタイルの DN ではなく、ユーザー プリンシパル名 (UPN) が入っていることがあります。

このエラー メッセージを解決するには、コネクタを構成する際に [object types](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/オブジェクトの種類) ページで **[Autogenerated](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/自動生成)** が選択されていることを確認してください。

詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。
<!-- /MSL-PAGE -->
