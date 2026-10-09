# Microsoft Learn — Microsoft Entra / ID ガバナンス (PIM・アクセスレビュー等) (part 3)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 71

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-insights"} -->
## ライフサイクル ワークフローの分析情報 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-insights
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローのレポートと履歴の機能の概念に関する記事。

ライフサイクル ワークフローを使用して作成されたワークフローを使用すると、組織内の ID ライフサイクルの Joiner-Mover-Leaver (JML) モデルに分類される場所に関係なく、ユーザーのライフサイクル タスクを自動化できます。 ワークフローが正しく処理されるようにすることは、組織のライフサイクル管理プロセスの重要な部分です。 ライフサイクル ワークフロー ワークフロー分析情報機能を使用すると、テナント全体のすべてのワークフローに関する集計情報を表示できます。

[Image: Workflow Insights ページのスクリーンショット。]

Workflow Insights を使うと、テナント全体について集計されたワークフローの情報を見ることもできます。 Workflow Insights を使うと、次の情報をすばやく表示できます。

- まとめ
- 上位のワークフロー
- 上位のタスク
- カテゴリ別のワークフロー

これらのセクションで見つかる分析情報については、この記事の以降のセクションで詳しく説明します。 テナント内のワークフローに関する分析情報の確認についてのステップ バイ ステップ ガイドについては、「[Workflow Insights を確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-insights)」をご覧ください。

### ワークフロー分析情報の概要

ワークフロー分析情報の概要には、テナント内で処理されて成功したワークフロー、ユーザー、タスクの数値ビューが表示されます。

[Image: ワークフロー分析情報の概要のスクリーンショット。]

この概要をフィルター処理して、過去 7 日、14 日、または 30 日間の情報を表示できます。

### 上位のワークフロー分析情報の概要

上位のワークフロー分析情報の概要には、テナントで実行された上位のワークフローの一覧が、7 日、14 日、または 30 日間の期間で表示されます。 上位のワークフローは、合計処理数、実行成功数、または実行失敗数の順番でフィルター処理することもできます。

[Image: 上位のワークフロー処理インサイト概要のスクリーンショット。]

上位のワークフロー分析情報の概要を表示すると、次の情報が示されます。

| 詳細 | 情報 |
| --- | --- |
| Workflow | ワークフローの名前です。 |
| Total Processed (処理された合計) | ワークフローの合計実行数。 |
| 成功 | ワークフローの正常な実行。 |
| 失敗しました | ワークフローの実行失敗。 |
| カテゴリ | ワークフローのカテゴリ。 |
| ユーザー合計 | ワークフローで処理されたユーザーの合計数。 |
| 成功したユーザー | ワークフローで処理されて成功したユーザーの数。 |
| 失敗したユーザー | ワークフローで処理されて失敗したユーザーの数。 |

注

ワークフローの実行は成功したがエラーがあったユーザーは、処理されたユーザーの数に影響する可能性があります。

### 主要タスクのインサイト概要

上位のタスク分析情報の概要には、テナントで実行された上位のタスクの一覧が、7 日、14 日、または 30 日間の期間で表示されます。 上位のタスクは、合計処理数、実行成功数、または実行失敗数の順番でフィルター処理することもできます。

[Image: ワークフロー分析情報の上位のタスクの概要のスクリーンショット。]

上位のタスク分析情報の概要を表示すると、次の情報が示されます。

| 詳細 | 情報 |
| --- | --- |
| タスク | タスクの名前。 |
| Total Processed (処理された合計) | タスクの合計実行数。 |
| 成功 | タスクの正常な実行。 |
| 失敗しました | タスクの実行失敗数 |
| ユーザー合計 | タスクで処理されたユーザーの合計数。 |
| 成功したユーザー | タスクで処理されて成功したユーザーの数。 |
| 失敗したユーザー | タスクで処理されて失敗したユーザーの数。 |

### ワークフロー カテゴリ分析情報の概要

ワークフロー カテゴリ分析情報の概要には、実行された上位のワークフローの一覧が、パーセンテージを使ってカテゴリ別に、7 日、14 日、または 30 日間の期間で表示されます。 カテゴリは、合計処理数、ワークフロー成功数、またはワークフロー失敗数でフィルター処理することもできます。

[Image: カテゴリ別ワークフロー分析情報の概要のスクリーンショット。]

カテゴリ別ワークフロー実行分析情報の概要を表示すると、次の情報が示されます。

| 詳細 | 情報 |
| --- | --- |
| 就職者 | *ジョイナー*カテゴリーを持つワークフローの割合。 フィルターが成功として設定されている場合、ジョイナーの成功率は、フィルターされた期間におけるジョイナー ワークフローの成功した割合 (%) です。 |
| ムーバー | "異動者" のカテゴリを持つワークフロー割合。 フィルターが合計として設定されている場合、Mover の割合は、フィルター処理された期間に処理された割合別のムーバー ワークフローの数です。 |
| 退職者 | "退職者" のカテゴリを持つワークフロー割合。 フィルターが失敗として設定されている場合、退職者の割合は、フィルター処理された期間に失敗した退職者ワークフローの数 (%) です。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-on-premises"} -->
## ライフサイクル ワークフローを使用して Active Directory Domain Services から Microsoft Entra ID に同期されたユーザーの管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-on-premises
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローを使用して、Active Directory Domain Services (AD DS) から Microsoft Entra に同期されるユーザーの管理について説明する概念的な記事。

ライフサイクル ワークフローは、Active Directory Domain Services (AD-DS) から Microsoft Entra ID に同期されるユーザー アカウントの ID ライフサイクルの管理を支援します。 ライフサイクル ワークフローでは、Microsoft Entra ID にユーザー アカウントが存在することが不可欠ですが、ユーザー アカウントのワークフローと関連タスクの処理に関しては、アカウントがどのように作成されたか、またはライフサイクルに関連する変更がアカウントに対してどのように行われたかは重要ではありません。 [人事主導のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)、Microsoft Graph API、Microsoft Entra 管理ポータルなどのオプションを介して行われたアカウントや変更、および Microsoft Entra Connect や Microsoft Cloud Sync で同期された変更がサポートされます。

次の表に、Microsoft Entra ID ガバナンスを使用して AD DS から同期されたユーザーの一般的な自動化シナリオを示します。

| シナリオを自動化する | Microsoft Entra ID ガバナンス ソリューション |
| --- | --- |
| Active Directory Domain Services におけるユーザー アカウントの作成 | [人事主導のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning) |
| ユーザー アカウントの初回資格情報またはパスワードの提供 | "[一時アクセス パスを生成してユーザーのマネージャーにメールで送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#generate-temporary-access-pass-and-send-via-email-to-users-manager)" タスクを使用して、パスワードレスの資格情報を設定できます。 通常の Active Directory パスワードを設定する場合は、[Microsoft Entra のセルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)を使用できます。 |
| ライセンスの割り当て | [\[ユーザーにライセンスを割り当てる\]](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#assign-licenses-to-user) ライフサイクル ワークフロー タスクを使用してライセンスを割り当てることができます。 また、[グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)を介してユーザーにライセンスを割り当てることもできます。 |
| Active Directory グループ ベースのアプリケーションへのアクセス権をユーザーに付与する | [オンプレミスの Active Directory (Kerberos) アプリケーション アクセスを管理する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups) |
| 組織の移動時に Active Directory のユーザー属性を更新する | [スコープ フィルターと属性マッピングの計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#plan-scoping-filters-and-attribute-mapping) |
| 組織の移動時にユーザーを別の OU に移動する | [Active Directory OU コンテナーの割り当てを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#configure-active-directory-ou-container-assignment) |
| 最終日にユーザーを無効にする | [\[ユーザー アカウントを無効にする\]](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#disable-user-account) ライフサイクル ワークフロー タスクを使用して、ユーザー アカウントをその最終日に無効にすることができます。 |
| 終了後、一定の日数が経過したユーザーを削除する | [\[ユーザーの削除\]](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#delete-user) ライフサイクル ワークフロー タスクをワークフロー テンプレート内で使用して、ユーザーの終了から設定日数経過後にそのユーザーを削除できます。 |

この記事では、AD-DS から Microsoft Entra ID に同期されたユーザー アカウントにライフサイクル ワークフローを使用する場合に考慮すべき点について説明します。

### Active Directory Domain Services (AD DS) から Microsoft Entra ID に同期されたユーザーでのワークフロー実行条件

ライフサイクル ワークフローは、ワークフロー実行条件を満たしたときにユーザー アカウントに対して処理されます。 実行条件は、トリガーとスコープで構成されます。 トリガーは、ユーザー アカウントで発生するイベントを記述します。 スコープを使用すると、イベントが発生したときにワークフローを実行するユーザーをさらに定義できます。

#### ワークフロー トリガー

次の表は、AD DS から同期されたユーザーで使用される場合に、各ワークフロー トリガーで考慮すべき点を示しています。

| ワークフロー トリガー | 要件 |
| --- | --- |
| 属性の変更 | 属性が同期されている限り、それ以上の構成は必要ありません。 同期された属性については、「[Microsoft Entra クラウド同期での属性のマッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-attribute-mapping)」および「[Microsoft Entra Connect Sync: ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)」を参照してください。 Active Directory に変更が行われた場合、ライフサイクル ワークフローから変更を取り込む前に、Microsoft Entra クラウド同期または Microsoft Entra Connect 同期による同期を行う必要があります。 |
| グループのメンバーシップに基づく | あらゆる種類のグループをサポートするため、それ以上の構成は必要ありません。 グループが Active Directory に由来する場合は、Microsoft Entra に同期する必要があります。 Microsoft Entra Cloud Sync または Microsoft Entra Connect Sync は、ライフサイクル ワークフローから変更を取り込む前に同期を行う必要があります。 |
| オンデマンド | それ以上の構成は必要ありません。 |
| 時間ベース | **employeeHireDate**、**employeeLeaveDateTime**: これらの属性は使用する前に同期する必要があります。 このプロセスの詳細については、「[ライフサイクル ワークフローの属性を同期する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes)」を参照してください。**createdDateTime**: それ以上の構成は必要ありません。 この日付は、ユーザー アカウントが Active Directory 内で作成された日ではなく、Microsoft Entra ID に同期された日です。 |

#### ワークフローのスコーピング

ワークフローのスコーピング機能内で使用されるユーザー属性については、選択された属性が既に同期されている場合、それ以上の構成は必要ありません。 同期された属性については、「[Microsoft Entra クラウド同期での属性のマッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-attribute-mapping)」および「[Microsoft Entra Connect Sync: ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)」を参照してください。 Active Directory に変更が行われた場合、ライフサイクル ワークフローから変更を取り込む前に、Microsoft Entra クラウド同期または Microsoft Entra Connect 同期による同期を行う必要があります。

### Active Directory Domain Services から Microsoft Entra ID に同期されたユーザーのワークフロー タスク

すべてのライフサイクル ワークフロー タスクは、クラウド ユーザーと Active Directory から同期されたユーザーの両方に対して、この記事の以降で特定のタスクについて記載されている制限を除き、直ちに機能します。 すべてのライフサイクル ワークフロー タスクについて詳しくは、[ライフサイクル ワークフローの組み込みタスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks)に関する記事をご覧ください。

#### グループのメンバーシップを管理するためのタスク

**シナリオ:** AD DS から Microsoft Entra ID にユーザーを同期すると、ライフサイクル ワークフローのグループ タスクを使用して、クラウドベースのセキュリティ グループに対しユーザーを追加または削除できます。 その結果、クラウド内の同期されたユーザーのグループ メンバーシップを管理でき、また [Microsoft Entra Cloud 同期グループ書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)を使用してこのグループを Active Directory に追加することもできます。

AD DS から Microsoft Entra ID に同期されるグループでは、シナリオにあるようにライフサイクル ワークフロー グループ タスクを使用することはできません。 ただし、Microsoft Entra ID Governance は、ライフサイクル ワークフロー内でサポートされているクラウドからのグループを使用して、[オンプレミスの Active Directory (Kerberos) アプリケーション アクセスを管理する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups)ために使用できます。

### ユーザー アカウント タスク

AD DS から同期されたユーザーと連携するために、ユーザー アカウントを有効、無効、削除するライフサイクル ワークフロー タスクには、追加の構成が必要です。 Active Directory でアクションを実行するタスクを構成する前に、次の前提条件を達成する必要があります。

- [Microsoft Entra プロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-provisioning-agent)が環境にインストールされている必要があります。 Microsoft Entra プロビジョニング エージェントのインストールに関する前提条件については、「[クラウド プロビジョニング エージェントの要件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#cloud-provisioning-agent-requirements)」を参照してください。 Microsoft Entra プロビジョニング エージェントをインストールする段階的な手順については、「[Microsoft Entra プロビジョニング エージェントをインストールする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)」を参照してください。 インストール中、“**HR ドリブン プロビジョニング/Microsoft Entra Connect Sync**” を “**拡張機能の構成**” として選択します。 クラウド同期構成など、プロビジョニング エージェントのその他の構成を追加する必要はありません。また、現時点で Microsoft Entra Connect Sync をユーザー同期に使用している場合でも、プロビジョニング エージェントをインストールできます。

注

インストールされるプロビジョニング エージェントは、バージョン 1.1.1586.0 (2024 年 5 月 13 日リリース) 以上である必要があります。

- プロビジョニング エージェントによって使用されるグループ管理サービス アカウント (gMSA) に、ユーザー アカウントに対する操作を実行するための [適切なアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#custom-gmsa-account) があることを確認します。
- ユーザー アカウントを削除するには、Active Directory のごみ箱を有効にする必要があります。 ごみ箱を有効にするための段階的な手順については、「[Active Directory の段階的なごみ箱](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/get-started/adac/introduction-to-active-directory-administrative-center-enhancements--level-100-#active-directory-recycle-bin-step-by-step)」を参照してください。

Active Directory Domain Services から同期されたユーザーに対してユーザー アカウント タスクが実行されるようにフラグを設定するステップ バイ ステップ ガイドについては、「[ワークフローを使用して Active Directory Domain Services (AD DS) から同期されたユーザーを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-on-premises)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-tasks"} -->
## ライフサイクル ワークフローのタスクと定義 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-04-25
- Summary: この記事では、ワークフローのタスクの定義とタスクのパラメーターについて説明します。

ライフサイクル ワークフローには、一般的なライフサイクル管理シナリオを自動化するように設計された多くの事前構成済みタスクが付属しています。 これらの組み込みタスクは、組織のニーズに合わせてカスタマイズされたワークフローを作成するために利用できます。 これらのタスクを数秒で構成して、新しいワークフローを作成できます。 これらのタスクには、就職者 - 異動者 - 退職者モデルに基づくカテゴリもあり、ニーズに基づいてワークフローに簡単に配置できます。 この記事では、タスクの完全な一覧、各タスクに共通するパラメーターの情報、特定のタスクごとに必要な固有のパラメーターの一覧を示します。

### サポートされているタスク

ライフサイクル ワークフローの各組み込みタスクは、**taskDefinitionID** という名前の識別子を含んでおり、新しいワークフローを最初から作成するために使ったり、組織のニーズに合うようにワークフロー テンプレートに挿入したりできます。 ライフサイクル ワークフローで使用できるテンプレートについて詳しくは、「[ライフサイクル ワークフロー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates)」をご覧ください。

注

ライフサイクル ワークフローは、日常的なプロセスを自動化することで、Microsoft Entra ID ガバナンスの[人事主導のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)を強化します。 人事のプロビジョニングがユーザー アカウントの作成と属性の更新を管理する一方で、ライフサイクル ワークフローはタスクのさらなる自動化を実現します。

現在、ライフサイクル ワークフローでは次のタスクがサポートされています。

| タスク | taskdefinitionID | カテゴリ | Active Directory の互換性 |
| --- | --- | --- | --- |
| [新規採用者にウェルカム メールを送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-welcome-email-to-new-hire) | 70b29d51-b59a-4773-9280-8841dfd3f2ea | 就職者 |  |
| [オンボード リマインダーのメールを送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-onboarding-reminder-email) | 3C860712-2D37-42A4-928F-5C93935D26A1 | 就職者 |  |
| [一時アクセス パスを生成し、ユーザーのマネージャーにメールで送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#generate-temporary-access-pass-and-send-via-email-to-users-manager) | 1b555e50-7f65-41d5-b514-5894a026d10d | 就職者 |  |
| [ユーザーの移動をマネージャーに通知するメールを送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-to-notify-manager-of-user-move) | aab41899-9972-422a-9d97-f626014578b7 | Mover |  |
| [ユーザーにアクセス パッケージの割り当てを要求する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#request-user-access-package-assignment) | c1ec1e76-f374-4375-aaa6-0bb6bd4c60be | Joiner、Mover |  |
| [ユーザーへのライセンスの割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#assign-licenses-to-user) | 683c87a4-2ad4-420b-97d4-220d90afcd24 | Joiner、Mover |  |
| [ユーザーをグループに追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#add-user-to-groups) | 22085229-5809-45e8-97fd-270d28d66910 | 就職者、退職者、異動者 |  |
| [ユーザーをチームに追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#add-user-to-teams) | e440ed8d-25a1-4618-84ce-091ed5be5594 | 就職者、退職者、異動者 |  |
| [ユーザー属性を更新する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#update-user-attributes) | 2c8f4a1b-7d3e-4f9c-8a5b-6e1d2c3f4a5b | 就職者、退職者、異動者 |  |
| [ユーザー アカウントの有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#enable-user-account) | 6fc52c9d-398b-4305-9763-15f42c1676fc | 就職者、退職者 |  |
| [カスタム タスク拡張機能を実行する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#run-a-custom-task-extension) | 4262b724-8dba-4fad-afc3-43fcbb497a0e | 就職者、退職者、異動者 |  |
| [ユーザー アカウントを無効にする](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#disable-user-account) | 1dfdfcc7-52fa-4c2e-bf3a-e3919cc12950 | 退職者 |  |
| [選択したグループからユーザーを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-user-from-selected-groups) | 1953a66c-751c-45e5-8bfe-01462c70da3c | 就職者、退職者、異動者 |  |
| [ユーザーをすべてのグループから削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-users-from-all-groups) | b3a31406-2a15-4c9a-b25b-a658fa5f07fc | 退職者 |  |
| [選択したチームからユーザーを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-user-from-selected-teams) | 06aa7acb-01af-4824-8899-b14e5ed788d6 | 退職者 |  |
| [すべてのチームからユーザーを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-users-from-all-teams) | 81f7b200-2816-4b3b-8c5d-dc556f07b024 | 退職者 |  |
| [ユーザーのアクセス パッケージの割り当てを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-access-package-assignment-for-user) | 4a0b64f2-c7ec-46ba-b117-18f262946c50 | 退職者、異動者 |  |
| [ユーザーのすべてのアクセス パッケージ割り当てを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-all-access-package-assignments-for-user) | 42ae2956-193d-4f39-be06-691b8ac4fa1d | 退職者、異動者 |  |
| [ユーザーに対するすべての保留中のアクセス パッケージ割り当て要求を取り消す](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#cancel-all-pending-access-package-assignment-requests-for-user) | 498770d9-bab7-4e4c-b73d-5ded82a1d0b3 | 退職者 |  |
| [選択したライセンス割り当てをユーザーから削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-selected-license-assignments-from-user) | 5fc402a8-daaf-4b7b-9203-da868b05fc5f | 退職者、異動者 |  |
| [ユーザーのすべての更新トークンを取り消す](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#revoke-all-refresh-tokens-for-user) | 509589a4-0466-4471-829e-49c5e502bdee | 退職者、異動者 |  |
| [スポンサー プランの変更に関するメールをマネージャーに送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-to-manager-about-sponsorship-changes) | b8c4e1f9-3a7d-4b2e-9c5f-8d6a9b1c2e3f | 退職者、異動者 |  |
| [スポンサーの変更に関するメールを共同スポンサーに送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-to-co-sponsors-about-sponsor-changes) | ad3b85cd-75b1-43e7-b4b9-0e52faba3944 | 退職者、異動者 |  |
| [エージェントのアイデンティティスポンサーシップをマネージャーに譲渡する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#transfer-agent-identity-sponsorships-to-manager) | b8f4c3d5-9e7a-4b1c-8f2d-6a5e8b9c7f4a | 退職者、異動者 |  |
| [ユーザーからすべてのライセンス割り当てを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-all-license-assignments-from-user) | 8fa97d28-3e52-4985-b3a9-a1126f9b8b4e | 退職者 |  |
| [ユーザーを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#delete-user) | 8d18588d-9ad3-4c0f-99d0-ec215f0e3dff | 退職者 |  |
| [ユーザーの最終在籍日の前にマネージャーにメールを送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-to-manager-before-users-last-day) | 52853a3e-f4e5-4eb8-bb24-1ac09a1da935 | 退職者 |  |
| [ユーザーの最終在籍日にメールを送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-on-users-last-day) | 9c0a1eaf-5bda-4392-9d9e-6e155bb57411 | 退職者 |  |
| [ユーザーの最終在籍日の後でユーザーのマネージャーにメールを送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-to-users-manager-after-their-last-day) | 6f22ddd4-b3a5-47a4-a846-0d7c201a49ce | 退職者 |  |
| [ユーザーの非アクティブに関する電子メールを送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-about-user-inactivity) | 92f74cb4-f1b6-4ec0-b766-96210f56edc2 | 退職者 |  |
| [応答されていないゲストの削除に関する電子メールを送信する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-about-unsponsored-guest-removal-preview) | a8e4c7f2-9d1b-4a3e-b5c6-8f2e7a9d4c1b | 退職者 |  |

### タスクの共通パラメーター

タスクの共通パラメーターは、すべてのタスクに含まれる固有ではないパラメーターです。 新しいワークフローまたはワークフロー テンプレートにタスクを追加するときに、要件に合わせてこれらのパラメーターをカスタマイズして構成できます。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | タスクの 1 つまたは複数のカテゴリを示す読み取り専用の文字列。 taskDefinitionID が選ばれると自動的に決定されます。 |
| taskDefinitionId | 実行するタスクを決定する taskDefinition を参照する文字列。 |
| 有効化されている | タスクが実行するように設定されているかどうかを示すブール値。 "*true"* に設定すると、タスクが実行されます。 既定値は true です。 |
| displayName | タスクを識別する一意の文字列。 |
| 説明 | 管理で使用するタスクの目的を説明する文字列。 (省略可能) |
| executionSequence | ワークフローでのタスクの実行順序を示す読み取り専用の整数。 |
| continueOnError | このタスクでエラーが発生したら後続のワークフローの実行を停止するかどうかを決定するブール値。 |
| 引数 | 特定のタスクに関連する固有のパラメーターが含まれています。 |

### メール タスクの共通パラメーター

すべての基本的なタスク パラメーターと共に、[ **基本** メール タスク] には、電子メールの受信者を構成する機能も含まれます。

[Image: 基本的なメール タスクのカスタマイズのスクリーンショット。]

メールの受信者には以下が挙げられます。

- **マネージャー メール属性 (プレビュー)**: ワークフローを実行するユーザーのマネージャーのメール。
- **ユーザー メール属性 (デフォルト)**: ワークフローが実行されるユーザーのメール。
- **スポンサー メール属性 (プレビュー)**: スポンサー、またはワークフローが実行されるユーザーのスポンサーのメール。
- **選択したユーザー (プレビュー)**: 手動で指定したメール ユーザー。

注

追加の CC 受信者は、受信者がユーザー自身またはそのマネージャーである場合にのみ使用できます。 複数の CC 受信者が存在する場合は、1 つの個々のメールにコピーされます。

[ **電子メールのカスタマイズ** ] で、タスクによって送信される特定の電子メール メッセージをカスタマイズできます。 メールをカスタマイズする場合は、次の引数を設定できます。

- **件名**: メールの件名をカスタマイズします。
- **メッセージ本文**: 送信されるメールの本文をカスタマイズし、 [HTML 要素](https://learn.microsoft.com/ja-jp/entra/id-governance/customize-workflow-email#customize-email-text)を使用してさらにカスタマイズできます。
- **メール言語翻訳**: メール受信者の言語設定をオーバーライドします。 カスタム テキストはカスタマイズされないため、この言語をカスタム テキストと同じ言語に設定することをお勧めします。

[Image: カスタマイズ メール オプションのスクリーンショット。]

このプロセスに関するステップ バイ ステップ ガイドについては、「[ワークフロー タスクによって送信されるメールをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/id-governance/customize-workflow-email)」を参照してください。

#### メール内の動的属性

カスタマイズされたメールを使用すると、件名と本文に動的属性を含めて、これらのメールを個人用に設定することができます。 組み込みのユーザー属性、カスタム セキュリティ属性、ディレクトリ拡張機能、およびオンプレミス拡張機能属性を含めることができます。 次の表は、使用可能な動的属性の例を示しています。

**ユーザー属性**

| 属性 | Definition |
| --- | --- |
| `{{user.displayName}}` | ユーザーの表示名。 |
| `{{user.givenName}}` | ユーザーの名。 |
| `{{user.userPrincipalName}}` | そのユーザーのユーザー プリンシパル名。 |
| `{{user.employeeHireDate}}` | ユーザーの従業員の雇用日。 |
| `{{user.employeeLeaveDateTime}}` | ユーザーの従業員の休暇の日付と時刻。 |
| `{{user.createdDateTime}}` | ユーザーが作成された日時。 |
| `{{user.employeeType}}` | ユーザーの従業員の種類。 |
| `{{user.department}}` | ユーザーの部署。 |
| `{{user.companyName}}` | ユーザーの会社名。 |
| `{{user.jobTitle}}` | ユーザーの役職。 |
| `{{temporaryAccessPass}}` | 生成された一時アクセス パス。 **Generate TAP And Send Email** (TAP を生成してメールを送信する) タスクでのみ使用できます。 |

**マネージャー属性**

| 属性 | Definition |
| --- | --- |
| `{{managerDisplayName}}` | ユーザーのマネージャーの表示名。 |
| `{{managerEmail}}` | 上司の電子メール。 |

**拡張機能とカスタム属性**

| 属性 | Definition |
| --- | --- |
| `{{user.employeeOrgData/costCenter}}` | 従業員の組織データに基づくユーザーのコストセンター。 |
| `{{user.employeeOrgData/division}}` | 従業員組織データによる、そのユーザーの部門。 |
| `{{user.customSecurityAttributes/attributeSet/attributeName}}` | カスタム セキュリティ属性値。 `attributeSet`と`attributeName` を自分の属性セットと属性名で置き換えます。 |
| `{{user.onPremisesExtensionAttributes/extensionAttribute1}}` | オンプレミスの拡張機能属性。 `extensionAttribute1`を目的の属性番号 (1 から 15) に置き換えます。 |

注

これらの属性をカスタマイズした電子メールまたは件名に追加する場合は、 `{{user.graphPropertyName}}` 構文を使用して適切に書式設定する必要があります。 これを行うためのステップ バイ ステップ ガイドについては、「[カスタマイズされたメール内の属性を書式設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/customize-workflow-email)」を参照してください。

重要

新しい `{{user.graphPropertyName}}` 属性の形式は、新しいカスタム 電子メール タスクに適用されます。 この変更の前に構成されたワークフローに対する既存のカスタマイズされた電子メール タスクは影響を受けず、既存の属性の書式設定を引き続き使用できます。

### タスクの詳細

このセクションでは、特定のタスクごとに、それが正常に実行するために必要な、パラメーターや前提条件などの詳細情報について説明します。 パラメーターは、Microsoft Entra 管理センターと Microsoft Graph の両方に表示されるとおりに記載示されています。 ライフサイクル ワークフローのタスクの編集に関する一般的な情報については、「[ワークフローのバージョンを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-tasks)」をご覧ください。

#### 新規採用者にウェルカム メールを送信する

ライフサイクル ワークフローでは、組織の新規採用者へのウェルカム メールの送信を自動化できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: ウェルカム メール タスク。]

[**新規採用者にウェルカム メールを送信する**] タスクを実行するための Microsoft Entra の前提条件:

- ユーザーに関する設定済みのメール属性。

Microsoft Graph の場合、"**新規採用者にウェルカム メールを送信する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | joiner |
| displayName | Send Welcome Email (ウェルカム メールを送信する) (ユーザーがカスタマイズ可能) |
| 説明 | Send welcome email to new hire (新規採用者にウェルカム メールを送信する) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 70b29d51-b59a-4773-9280-8841dfd3f2ea |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```json
{
    "category": "joiner",
    "continueOnError": false,
    "description": "Send welcome email to new hire",
    "displayName": "Send Welcome Email",
    "isEnabled": true,
    "taskDefinitionId": "70b29d51-b59a-4773-9280-8841dfd3f2ea",
    "arguments": [
        {
            "name": "cc",
            "value": "e94ad2cd-d590-4b39-8e46-bb4f8e293f85,ac17d108-60cd-4eb2-a4b4-084cacda33f2"
        },
        {
            "name": "customSubject",
            "value": "Welcome to the organization {{userDisplayName}}!"
        },
        {
            "name": "customBody",
            "value": "Welcome to our organization {{userGivenName}} {{userSurname}}.\n\nFor more information, reach out to your manager {{managerDisplayName}} at {{managerEmail}}."
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### オンボード リマインダーのメールを送信する

ライフサイクル ワークフローを使用すると、組織内の新入社員のマネージャーにオンボーディング リマインダー メールを自動送信できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: オンボード リマインダー メール タスクを送信します。]

[**オンボード リマインダーのメールを送信する**] タスクを実行するための Microsoft Entra の前提条件:

- ユーザーに関する設定済みのマネージャー属性。
- ユーザーに関する設定済みのマネージャーのメール属性。

Microsoft Graph の場合、"**オンボード リマインダーのメールを送信する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | joiner |
| displayName | オンボード リマインダーのメールを送信する (ユーザーがカスタマイズ可能) |
| 説明 | オンボード リマインダーのメールをユーザーのマネージャーに送信する (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 3C860712-2D37-42A4-928F-5C93935D26A1 |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```json
{
    "category": "joiner",
    "continueOnError": false,
    "description": "Send onboarding reminder email to user\u2019s manager",
    "displayName": "Send onboarding reminder email",
    "isEnabled": true,
    "taskDefinitionId": "3C860712-2D37-42A4-928F-5C93935D26A1",
    "arguments": [
        {
            "name": "cc",
            "value": "e94ad2cd-d590-4b39-8e46-bb4f8e293f85,068fa0c1-fa00-4f4f-8411-e968d921c3e7"
        },
        {
            "name": "customSubject",
            "value": "Reminder: {{userDisplayName}} is starting soon"
        },
        {
            "name": "customBody",
            "value": "Hello {{managerDisplayName}}\n\nthis is a reminder that {{userDisplayName}} is starting soon.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### 一時アクセス パスを生成し、ユーザーのマネージャーにメールで送信します

Microsoft Entra では、[一時アクセス パス (TAP)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) を使用して、パスワードレスの資格情報をユーザーに設定することができます。 また、[Microsoft Entra セルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)を使用して、通常のパスワードを設定することもできます。 このタスクでは、ライフサイクル ワークフローを使用すると、一時アクセス パス (TAP) を自動的に生成して、それを新しいユーザーのマネージャーに送信することができます。 また、ユーザーのマネージャーに送信されるメールをカスタマイズすることもできます。

注

ユーザーの従業員の入社日は、一時アクセス パスの開始時刻として使用されます。 TAP の有効期間タスクの設定と [ユーザーの雇用日の時刻部分](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes#importance-of-time) が適切に設定されていることを確認して、ユーザーが最初の日を開始したときに TAP が有効であることを確認します。 ワークフロー実行時の採用日が既に過去のものである場合、現在の時刻が開始時刻として使用されます。

Microsoft Entra 管理センターでこのタスクを使用すると、タスクに名前を付け説明を入力でます。 以下も設定する必要があります。

- **アクティブ化期間** - パスコードがアクティブな期間。
- **1 回のみ使用** - パスコードを 1 回だけ使用できる場合。 [Image: ワークフロー タスクのスクリーンショット: TAP タスク。]

[**一時アクセス パスを生成してユーザーのマネージャーにメールで送信する**] タスクを実行するための Microsoft Entra の前提条件:

- ユーザーに関する設定済みのマネージャー属性。
- ユーザーに関する設定済みのマネージャーのメール属性。
- TAP テナント ポリシーが有効になっており、[アクティブ化期間] と [1 回のみ使用] で選択した値がポリシーの許容範囲内である必要があります。 詳しくは、「[一時アクセス パス ポリシーを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#enable-the-temporary-access-pass-policy)」をご覧ください

重要

ワークフローでこのタスクが自分のために実行されるようになっているユーザーは、他の認証方法、サインイン、Microsoft Entra ロールの割り当でもこのタスクが自分に対して機能するようにはしないでください。

Microsoft Graph の場合、"**一時アクセス パスを生成してユーザーのマネージャーにメールで送信する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | joiner |
| displayName | GenerateTAPAndSendEmail (ユーザーがカスタマイズ可能) |
| 説明 | Generate Temporary Access Password and send via email to user's manager (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 1b555e50-7f65-41d5-b514-5894a026d10d |
| 引数 | 引数には、name パラメーター "tapLifetimeInMinutes" が含まれています。これは、startDateTime から始まる temporaryAccessPass の有効期間 (分単位) です。 最小 10、最大 43200 (30 日に相当)。 引数には tapIsUsableOnce パラメーターも含まれていて、パスコードが 1 回の使用に制限されるかどうかを決定します。 true の場合、パスは 1 回だけ使用できます。false の場合、パスは temporaryAccessPass の有効期間内で複数回使用できます。 さらに、オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```json
{
    "category": "joiner",
    "continueOnError": false,
    "description": "Generate Temporary Access Pass and send via email to user's manager",
    "displayName": "Generate TAP and Send Email",
    "isEnabled": true,
    "taskDefinitionId": "1b555e50-7f65-41d5-b514-5894a026d10d",
    "arguments": [
        {
            "name": "tapLifetimeMinutes",
            "value": "480"
        },
        {
            "name": "tapIsUsableOnce",
            "value": "false"
        },
        {
            "name": "cc",
            "value": "068fa0c1-fa00-4f4f-8411-e968d921c3e7,9d208c40-7eb6-46ff-bebd-f30148c39b47"
        },
        {
            "name": "customSubject",
            "value": "Temporary access pass for your new employee {{userDisplayName}}"
        },
        {
            "name": "customBody",
            "value": "Hello {{managerDisplayName}}\n\nPlease find the temporary access pass for your new employee {{userDisplayName}} below:\n\n{{temporaryAccessPass}}\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### ユーザーの移動をマネージャーに通知するメールを送信する

ユーザーが組織内で異動する場合、ライフサイクル ワークフローを使用すると、ユーザーのマネージャーにメールを送信して異動を通知することができます。 また、ユーザーのマネージャーに送信されるメールをカスタマイズすることもできます。

[Image: マネージャーにユーザーの異動を通知するタスクのスクリーンショット。]

[**マネージャーにユーザーの異動を通知するメールを送信する**] タスクを実行するための Microsoft Entra の前提条件:

- ユーザーに関する設定済みのマネージャー属性。
- ユーザーに関する設定済みのマネージャーのメール属性。

Microsoft Graph の場合、**[マネージャーにユーザーの異動を通知するメールを送信する]** タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | Mover |
| displayName | マネージャーにユーザーの異動を通知するメールを送信する (ユーザーによるカスタマイズ可能) |
| 説明 | ユーザーのマネージャーにユーザーの異動を通知するメールを送信する (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | aab41899-9972-422a-9d97-f626014578b7 |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```json
{
    "category": "mover",
    "continueOnError": false,
    "description": "Send email to notify user\u2019s manager of user move",
    "displayName": "Send email to notify manager of user move",
    "isEnabled": true,
    "taskDefinitionId": "aab41899-9972-422a-9d97-f626014578b7",
    "arguments": [
        {
            "name": "cc",
            "value": "ac17d108-60cd-4eb2-a4b4-084cacda33f2,7d3ee937-edcc-46b0-9e2c-f832e01231ea"
        },
        {
            "name": "customSubject",
            "value": "{{userDisplayName}} has moved"
        },
        {
            "name": "customBody",
            "value": "Hello {{managerDisplayName}}\n\nwe are reaching out to let you know {{userDisplayName}} has moved in the organization.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### ユーザーにアクセス パッケージの割り当てを要求する

ユーザーのアクセス パッケージの割り当てを要求できるようにします。 アクセス パッケージ用に承認プロセスが構成されていて、そのプロセスを実行する必要がある場合は、`Enforce policy approval setting for admin direct assignments` エンタイトルメント管理設定をオプトインする必要があります。 アクセス パッケージの詳細については、「[アクセス パッケージの概要とそれを使用して管理できるリソースについて](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview#what-are-access-packages-and-what-resources-can-i-manage-with-them)」を参照してください。

このタスクのタスク名と説明はカスタマイズできます。 ユーザーに対して要求されているアクセス パッケージとポリシーも選択する必要があります。 [Image: ユーザー アクセス パッケージの割り当てを要求するタスクのスクリーンショット。]

Microsoft Graph の場合、**[ユーザー アクセス パッケージの割り当てを要求する]** タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 就職者、異動者 |
| displayName | ユーザー アクセス パッケージの割り当てを要求する (ユーザーによるカスタマイズ可能) |
| 説明 | 選択したアクセス パッケージへのユーザー割り当てを要求する (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | c1ec1e76-f374-4375-aaa6-0bb6bd4c60be |
| 引数 | 引数には、"assignmentPolicyId" と "accessPackageId" という 2 つの名前パラメーターが含まれています。 |

ワークフロー内での使用例:

```json
{
    "category": "joiner,mover",
    "continueOnError": false,
    "description": "Request user assignment to selected access package",
    "displayName": "Request user access package assignment",
    "isEnabled": true,
    "taskDefinitionId": "c1ec1e76-f374-4375-aaa6-0bb6bd4c60be",
    "arguments": [
        {
            "name": "assignmentPolicyId",
            "value": "00d6fd25-6695-4f4a-8186-e4c6f901d2c1"
        },
        {
            "name": "accessPackageId",
            "value": "2ae5d6e5-6cbe-4710-82f2-09ef6ffff0d0"
        }
    ]
}
```

#### ユーザーへのライセンスの割り当て

ユーザーへのライセンスの割り当てを許可します。 ライセンスをユーザーに割り当てるには、「*usageLocation*」属性セットが設定されている必要があります。

[Image: ユーザーへのライセンスの割り当てタスクのスクリーンショット。]

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 就職者、異動者 |
| displayName | ユーザーへのライセンスの割り当て (ユーザーによるカスタマイズ可能) |
| 説明 | 選択したライセンスをユーザーに割り当てます (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | 683c87a4-2ad4-420b-97d4-220d90afcd24 |
| 引数 | 引数には、"*SKU ID*" 値を受け取る "*licenses*" という名前のパラメーターが 1 つ含まれています。 これらの値の全一覧については、「[ライセンスのための製品名とサービス プラン識別子](https://learn.microsoft.com/ja-jp/entra/identity/users/licensing-service-plan-reference)」を参照してください。 |

ワークフロー内での使用例:

```json
{
            "category": "joiner,mover",
            "continueOnError": false,
            "description": "Assign selected licenses to the user",
            "displayName": "Assign licenses to user",
            "isEnabled": true,
            "taskDefinitionId": "683c87a4-2ad4-420b-97d4-220d90afcd24",
            "arguments": [
                {
                    "name": "licenses",
                    "value": "a403ebcc-fae0-4ca2-8c8c-7a907fd6c235"
                }
            ]
        }
```

#### ユーザーをグループに追加する

ユーザーを Microsoft 365 およびクラウド専用のセキュリティ グループに追加できるようにします。 メール対応、配布、動的およびロール割り当て可能のグループはサポートされていません。 オンプレミスの AD グループベースのアプリケーションとリソースへのアクセスを制御するには、グループの書き戻しを有効にする必要があります。 詳細については、[Microsoft Entra Cloud Sync グループの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)と[エンタイトルメント管理でのグループ ライトバックの使用](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-writeback)に関する記事を参照してください。

このタスクのタスク名と説明はカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: グループへのユーザーの追加タスク。]

Microsoft Graph の場合、"**ユーザーをグループに追加する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 就職者、退職者 |
| displayName | AddUserToGroup (ユーザーがカスタマイズ可能) |
| 説明 | Add user to group (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 22085229-5809-45e8-97fd-270d28d66910 |
| 引数 | 引数には、"groupID" である name パラメーターと、ユーザーを追加するグループのグループ ID である value パラメーターが含まれます。 |

ワークフロー内での使用例:

```json
{
            "category": "joiner,leaver",
            "description": "Add user to groups",
            "displayName": "AddUserToGroup",
            "isEnabled": true,
            "continueOnError": true,
            "taskDefinitionId": "22085229-5809-45e8-97fd-270d28d66910",
            "arguments": [
                {
                    "name": "groupID",
                    "value": "0732f92d-6eb5-4560-80a4-4bf242a7d501"
                }
            ]
}

```

#### ユーザーをチームに追加する

既存の静的チームにユーザーを追加できます。 このタスクのタスク名と説明はカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: ユーザーをチームに追加する。]

Microsoft Graph の場合、"**ユーザーをチームに追加する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 就職者、退職者 |
| displayName | AddUserToTeam (ユーザーがカスタマイズ可能) |
| 説明 | Add user to team (ユーザーがカスタマイズ可能) |
| taskDefinitionId | e440ed8d-25a1-4618-84ce-091ed5be5594 |
| 引数 | 引数には、"teamID" である名前パラメーターと、ユーザーを追加する既存のチームのチーム ID である値パラメーターが含まれます。 |

ワークフロー内での使用例:

```json
{
            "category": "joiner,leaver",
            "description": "Add user to team",
            "displayName": "AddUserToTeam",
            "isEnabled": true,
            "continueOnError": true,
            "taskDefinitionId": "e440ed8d-25a1-4618-84ce-091ed5be5594",
            "arguments": [
                {
                    "name": "teamID",
                    "value": "e3cc382a-c4b6-4a8c-b26d-a9a3855421bd"
                }
            ]
}

```

#### ユーザー属性を更新する

ライフサイクル ワークフローを使用すると、組織内のユーザーのユーザー属性の更新を自動化できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。

注

オンプレミス AD から同期されたユーザーの場合、このタスクはディレクトリ拡張機能の属性のみをサポートします。 詳細については、「 [ライフサイクル ワークフローを使用してユーザー属性を更新する](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-update-user-attributes)」を参照してください。

[Image: ユーザー属性の更新タスクのスクリーンショット。]

Microsoft Graphの場合、**ユーザー属性の更新タスクの**パラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 参加者、離脱者、異動者 |
| displayName | ユーザー属性を更新する |
| 説明 | ユーザー属性値を更新またはクリアします。 |
| taskDefinitionId | 2c8f4a1b-7d3e-4f9c-8a5b-6e1d2c3f4a5b |
| 引数 | 引数には name パラメーター "attributeUpdates" が含まれています。これは、更新またはクリアする最大 10 個のユーザー属性を指定する JSON 文字列です。 属性には、組み込みのオンプレミス拡張機能、ディレクトリ拡張機能、従業員の組織データを含めることができます。 |

ワークフロー内での使用例:

```json
{
    "category": "joiner",
    "continueOnError": false,
    "description": "Update or clear user attribute values including custom attributes",
    "displayName": "Update user attributes",
    "isEnabled": true,
    "taskDefinitionId": "2c8f4a1b-7d3e-4f9c-8a5b-6e1d2c3f4a5b",
    "arguments": [
        {
            "name": "attributeUpdates",
            "value": "[{\"attribute\":\"department\",\"value\":\"Sales\"},{\"attribute\":\"jobTitle\",\"value\":\"Account Executive\"}]"
        }
    ]
}
```

#### ユーザー アカウントの有効化

ユーザー アカウントの有効化を許可します。 同期されたオンプレミス ユーザーに対してこのタスクを実行するための前提条件については、「[ユーザー アカウントのタスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-on-premises#user-account-tasks)」を参照してください。 Microsoft Entra ロールの割り当てを持つユーザーはサポートされていません。また、ロール割り当て可能なグループのメンバーシップまたは所有権を持つユーザーもサポートされていません。 Microsoft Entra ID のオンプレミスの Active Directory への人事主導のプロビジョニングを利用し、HR ソースからのデータに基づいて、`accountDisabled` への属性マッピングを使用して同期されたアカウントを無効および有効にすることもできます。 詳細については、[Workday の属性マッピングの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#part-4-configure-attribute-mappings)に関するページおよび [SuccessFactors の属性マッピングの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial#part-4-configure-attribute-mappings)に関するページを参照してください。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。

[Image: ワークフロー タスクのスクリーンショット: ユーザー アカウントを有効にする。]

Microsoft Graph の場合、"**ユーザー アカウントを有効にする**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 就職者、退職者 |
| displayName | EnableUserAccount (ユーザーがカスタマイズ可能) |
| 説明 | Enable user account (ユーザー アカウントを有効にする) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 6fc52c9d-398b-4305-9763-15f42c1676fc |
| 引数 | 省略可能な *enableOnPremisesAccount* 引数を含めることができます。 この値は、同期されたオンプレミス ユーザーに対してタスクを実行する場合は *true*、同期されたオンプレミス ユーザーに対してタスクを実行しない場合は *false* のいずれかのブール値です。 |

ワークフロー内での使用例:

```json
 {
            "category": "joiner,leaver",
            "description": "Enable user account",
            "displayName": "EnableUserAccount",
            "isEnabled": true,
            "continueOnError": true,
            "taskDefinitionId": "6fc52c9d-398b-4305-9763-15f42c1676fc",
           "arguments": [
                {
                    "name": "enableOnPremisesAccount",
                    "value": "true"
                }
            ]
}

```

#### カスタム タスク拡張機能を実行する

カスタム タスク拡張機能を起動するようにワークフローを構成できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターを使用してカスタマイズできます。

[Image: ワークフロー タスクのスクリーンショット: カスタム拡張機能タスク。]

[**カスタム タスク拡張機能を実行する**] タスクを実行するための Microsoft Entra の前提条件:

- カスタム タスク拡張機能と互換性のあるロジック アプリ。 詳しくは、[ライフサイクル ワークフローの拡張性](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)に関する記事をご覧ください。

Microsoft Graph の場合、"**カスタム タスク拡張機能を実行する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 就職者、退職者 |
| displayName | Run a Custom Task Extension (カスタム タスク拡張機能を実行する) (ユーザーがカスタマイズ可能) |
| 説明 | カスタム タスク拡張機能を実行し、外部システムにコールアウトします。 (ユーザーがカスタマイズできる) |
| taskDefinitionId | d79d1fcc-16be-490c-a865-f4533b1639ee |
| 引数 | 引数には、"customTaskExtensionID" である名前パラメーターと、ロジック アプリに関する情報を含む以前に作成された拡張機能の ID である値パラメーターが含まれます。 |

ワークフロー内での使用例:

```json
{
            "category": "joiner,leaver",
            "description": "Run a Custom Task Extension to call-out to an external system.",
            "displayName": "Run a Custom Task Extension",
            "isEnabled": true,
            "continueOnError": true,
            "taskDefinitionId": "d79d1fcc-16be-490c-a865-f4533b1639ee",
            "arguments": [
                {
                    "name": "customTaskExtensionID",
                    "value": "<ID of your Custom Task Extension>"
                }
            ]
}

```

ライフサイクル ワークフローで実行するためのロジック アプリの設定について詳しくは、[カスタム ライフサイクル ワークフロー タスクでの Logic Apps のトリガー](https://learn.microsoft.com/ja-jp/entra/id-governance/trigger-custom-task)に関する記事をご覧ください。

#### ユーザー アカウントを無効にする

ユーザー アカウントの無効化を許可します。 同期されたオンプレミス ユーザーに対してこのタスクを実行するための前提条件については、「[ユーザー アカウントのタスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-on-premises#user-account-tasks)」を参照してください。 Microsoft Entra ロールの割り当てを持つユーザーはサポートされていません。また、ロール割り当て可能なグループのメンバーシップまたは所有権を持つユーザーもサポートされていません。 Microsoft Entra ID のオンプレミスの Active Directory への人事主導のプロビジョニングを利用し、HR ソースからのデータに基づいて、`accountDisabled` への属性マッピングを使用して同期されたアカウントを無効および有効にすることができます。 詳細については、[Workday の属性マッピングの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#part-4-configure-attribute-mappings)に関するページおよび [SuccessFactors の属性マッピングの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial#part-4-configure-attribute-mappings)に関するページを参照してください。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。

[Image: ワークフロー タスクのスクリーンショット: ユーザー アカウントを無効にする。]

Microsoft Graph の場合、"**ユーザー アカウントを無効にする**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 就職者、退職者 |
| displayName | DisableUserAccount (ユーザーがカスタマイズ可能) |
| 説明 | Disable user account (ユーザー アカウントを無効にする) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 1dfdfcc7-52fa-4c2e-bf3a-e3919cc12950 |
| 引数 | 省略可能な *disableOnPremisesAccount* 引数を含めることができます。 この値は、同期されたオンプレミス ユーザーに対してタスクを実行する場合は *true*、同期されたオンプレミス ユーザーに対してタスクを実行しない場合は *false* のいずれかのブール値です。 |

ワークフロー内での使用例:

```json
{
            "category": "joiner,leaver",
            "description": "Disable user account",
            "displayName": "DisableUserAccount",
            "isEnabled": true,
            "continueOnError": true,
            "taskDefinitionId": "1dfdfcc7-52fa-4c2e-bf3a-e3919cc12950",
            "arguments": [
                {
                    "name": "disableOnPremisesAccount",
                    "value": "true"
                }
            ]
}

```

#### 選択したグループからユーザーを削除する

Microsoft 365 とクラウド専用のセキュリティ グループからユーザーを削除できるようにします。 メール対応、配布、動的およびロール割り当て可能のグループはサポートされていません。 オンプレミスの AD グループベースのアプリケーションとリソースへのアクセスを制御するには、グループの書き戻しを有効にする必要があります。 詳細については、[Microsoft Entra Cloud Sync グループの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)と[エンタイトルメント管理でのグループ ライトバックの使用](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-writeback)に関する記事を参照してください。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: ユーザーを選択したグループから削除する。]

Microsoft Graph の場合、"**ユーザーを選択したグループから削除する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | Remove user from selected groups (ユーザーを選択したグループから削除する) (ユーザーがカスタマイズ可能) |
| 説明 | 選択した Microsoft Entra グループのメンバーシップからユーザーを削除する (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | 1953a66c-751c-45e5-8bfe-01462c70da3c |
| 引数 | 引数には、"groupID" である名前パラメーターと、ユーザーを削除するグループのグループ ID である値パラメーターが含まれます。 |

ワークフロー内での使用例:

```json
{
            "category": "leaver",
            "displayName": "Remove user from selected groups",
            "description": "Remove user from membership of selected Azure AD groups",
            "isEnabled": true,
            "continueOnError": true,
            "taskDefinitionId": "1953a66c-751c-45e5-8bfe-01462c70da3c",
            "arguments": [
                {
                    "name": "groupID",
                    "value": "GroupId1, GroupId2, GroupId3, ..."
                }
            ]
}

```

#### ユーザーをすべてのグループから削除する

メンバーであるすべての Microsoft 365 およびクラウド専用セキュリティ グループからユーザーを削除できるようにします。 メール対応、配布、動的およびロール割り当て可能のグループはサポートされていません。 オンプレミスの AD グループベースのアプリケーションとリソースへのアクセスを制御するには、グループの書き戻しを有効にする必要があります。 詳しくは、[Microsoft Entra Cloud Sync グループの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)に関する記事を参照してください。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。

[Image: ワークフロー タスクのスクリーンショット: ユーザーをすべてのグループから削除する。]

Microsoft Graph の場合、"**ユーザーをすべてのグループから削除する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | Remove user from all groups (ユーザーをすべてのグループから削除する) (ユーザーがカスタマイズ可能) |
| 説明 | すべての Microsoft Entra グループのメンバーシップからユーザーを削除する) (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | b3a31406-2a15-4c9a-b25b-a658fa5f07fc |

ワークフロー内での使用例:

```json
{
            "category": "leaver",
            "continueOnError": true,
            "displayName": "Remove user from all groups",
            "description": "Remove user from all Azure AD groups memberships",
            "isEnabled": true,
            "taskDefinitionId": "b3a31406-2a15-4c9a-b25b-a658fa5f07fc",
            "arguments": []
}

```

#### 選択した Teams からユーザーを削除する

ユーザーを 1 つまたは複数の静的チームから削除できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: ユーザーを Teams から削除する。]

Microsoft Graph の場合、"**ユーザーを Teams から削除する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 就職者、退職者 |
| displayName | Remove user from selected Teams (ユーザーを選択した Teams から削除する) (ユーザーがカスタマイズ可能) |
| 説明 | Remove user from membership of selected Teams (選択した Teams のメンバーシップからユーザーを削除する) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 06aa7acb-01af-4824-8899-b14e5ed788d6 |
| 引数 | 引数には、"teamID" である name パラメーターと、ユーザーを削除する Teams の Teams ID である value パラメーターが含まれます。 |

ワークフロー内での使用例:

```json
{
            "category": "joiner,leaver",
            "continueOnError": true,
            "displayName": "Remove user from selected Teams",
            "description": "Remove user from membership of selected Teams",
            "isEnabled": true,
            "taskDefinitionId": "06aa7acb-01af-4824-8899-b14e5ed788d6",
            "arguments": [
                {
                    "name": "teamID",
                    "value": "TeamId1, TeamId2, TeamId3, ..."
                }
            ]
}

```

#### すべての Teams からユーザーを削除する

ユーザーをメンバーになっているすべての静的チームから削除できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: ユーザーをすべての Teams から削除する。]

Microsoft Graph の場合、"**すべての Teams からユーザーを削除する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | Remove user from all Teams memberships (すべての Teams メンバーシップからユーザーを削除する) (ユーザーがカスタマイズ可能) |
| 説明 | Remove user from all Teams (すべての Teams からユーザーを削除する) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 81f7b200-2816-4b3b-8c5d-dc556f07b024 |

ワークフロー内での使用例:

```json
{
            "category": "leaver",
            "continueOnError": true,
            "description": "Remove user from all Teams",
            "displayName": "Remove user from all Teams memberships",
            "isEnabled": true,
            "taskDefinitionId": "81f7b200-2816-4b3b-8c5d-dc556f07b024",
            "arguments": []
}

```

#### ユーザーのアクセス パッケージの割り当てを削除する

ユーザーのアクセス パッケージの割り当てを削除できるようにします。 アクセス パッケージの詳細については、「[アクセス パッケージの概要とそれを使用して管理できるリソースについて](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview#what-are-access-packages-and-what-resources-can-i-manage-with-them)」を参照してください。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 また、割り当てを削除するアクセス パッケージを選択する必要もあります。 [Image: ユーザーのアクセス パッケージの割り当てを削除するタスクのスクリーンショット。]

Microsoft Graph の場合、[**ユーザーのアクセス パッケージの割り当てを削除する**] タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 退職者、異動者 |
| displayName | ユーザーのアクセス パッケージの割り当てを削除する (ユーザーによるカスタマイズ可能) |
| 説明 | 選択したアクセス パッケージのユーザー割り当てを削除する (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | 4a0b64f2-c7ec-46ba-b117-18f262946c50 |
| 引数 | 引数には、「accessPackageId」という name パラメーターが含まれています。 |

ワークフロー内での使用例:

```json
{
    "category": "leaver, mover",
    "continueOnError": false,
    "description": "Remove user assignment of selected access package",
    "displayName": "Remove access package assignment for user",
    "isEnabled": true,
    "taskDefinitionId": "4a0b64f2-c7ec-46ba-b117-18f262946c50",
    "arguments": [
        {
            "name": "accessPackageId",
            "value": "2ae5d6e5-6cbe-4710-82f2-09ef6ffff0d0"
        }
    ]
}
```

#### ユーザーのすべてのアクセス パッケージ割り当てを削除する

ユーザーのすべてのアクセス パッケージの割り当てを削除できるようにします。 アクセス パッケージの詳細については、「[アクセス パッケージの概要とそれを使用して管理できるリソースについて](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview#what-are-access-packages-and-what-resources-can-i-manage-with-them)」を参照してください。

タスク名、説明、およびアクセス パッケージの割り当てを、Microsoft Entra 管理センターでこのタスクの直ちに削除するか、または一定の日数後に削除するかをカスタマイズできます。 Mover ワークフロー テンプレートで使用する場合、既定では、15 日後に削除するようスケジュールされています。 [Image: すべてのユーザー アクセス パッケージの割り当てを削除するタスクのスクリーンショット。]

Microsoft Graph の場合、[**ユーザーのすべてのアクセス パッケージ割り当てを削除する**] タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 退職者、異動者 |
| displayName | ユーザーのすべてのアクセス パッケージ割り当てを削除する (ユーザーによるカスタマイズ可能) |
| 説明 | ユーザーのすべてのアクセス パッケージの割り当てを削除します。 daysUntilExpiration が設定されている場合、削除は即時ではなくスケジュールされます。 (ユーザーがカスタマイズできる) |
| taskDefinitionId | 42ae2956-193d-4f39-be06-691b8ac4fa1d |
| 引数 | 引数には、"daysUntilExpiration" である必須の名前パラメーターと、ユーザーのすべてのアクセス パッケージ割り当ての有効期限が切れるまでの日数を示す値パラメーターが含まれます。 ムーバー テンプレートの場合、既定値は 15 日です。 |

ワークフロー内での使用例:

```json
{
    "category": "leaver",
    "continueOnError": false,
    "description": "Remove all access package assignments for the user. When daysUntilExpiration is set, removal is scheduled rather than immediate.",
    "displayName": "Remove all access package assignments for user",
    "isEnabled": true,
    "taskDefinitionId": "42ae2956-193d-4f39-be06-691b8ac4fa1d",
    "arguments": [
        {
             "description": "Remove all access package assignments for the user. When daysUntilExpiration is set, removal is scheduled rather than immediate.",
            "displayName": "Remove all access package assignments for user",
            "id": "42ae2956-193d-4f39-be06-691b8ac4fa1d",
            "version": 1,
            "parameters": [
                {
                    "name": "daysUntilExpiration",
                    "values": [],
                    "valueType": "string"
                }
            ]
        }
    ]
```

#### ユーザーに対するすべての保留中のアクセス パッケージ割り当て要求を取り消す

ユーザーのすべての保留中のアクセス パッケージ割り当て要求を取り消すことができます。 アクセス パッケージの詳細については、「[アクセス パッケージの概要とそれを使用して管理できるリソースについて](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview#what-are-access-packages-and-what-resources-can-i-manage-with-them)」を参照してください。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ユーザーのすべての保留中のアクセス パッケージ割り当て要求を取り消すタスクのスクリーンショット。]

Microsoft Graph の場合、[**ユーザーに対するすべての保留中のアクセス パッケージ割り当て要求を取り消す**] タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | ユーザーの保留中のアクセス パッケージ割り当て要求を取り消す (ユーザーによるカスタマイズ可能) |
| 説明 | ユーザーのすべての保留中のアクセス パッケージ割り当て要求を取り消す (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | 498770d9-bab7-4e4c-b73d-5ded82a1d0b3 |

ワークフロー内での使用例:

```json
{
    "category": "leaver",
    "continueOnError": false,
    "description": "Cancel all access package assignment requests pending for the user",
    "displayName": "Cancel all pending access package assignment requests for user",
    "isEnabled": true,
    "taskDefinitionId": "498770d9-bab7-4e4c-b73d-5ded82a1d0b3",
    "arguments": []
}
```

#### 選択したライセンス割り当てをユーザーから削除する

選択したライセンス割り当てをユーザーから削除します。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: 選択したライセンスの割り当てをユーザーから削除するタスクのスクリーンショット。]

Microsoft Graph の場合、[**選択したライセンス割り当てをユーザーから削除する**] タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 退職者、異動者 |
| displayName | ユーザーのすべてのライセンスを削除する (ユーザーによるカスタマイズ可能) |
| 説明 | ユーザーに割り当てられている選択されたライセンスを削除する (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | 5fc402a8-daaf-4b7b-9203-da868b05fc5f |
| 引数 | 引数には、"*SKU ID*" 値を受け取る "*licenses*" という名前のパラメーターが 1 つ含まれています。 これらの値の全一覧については、「[ライセンスのための製品名とサービス プラン識別子](https://learn.microsoft.com/ja-jp/entra/identity/users/licensing-service-plan-reference)」を参照してください。 |

ワークフロー内での使用例:

```json
{
            "category": "leaver, mover",
            "description": "Remove selected licenses assigned to the user",
            "displayName": "Remove licenses from user",
            "id": "5fc402a8-daaf-4b7b-9203-da868b05fc5f",
            "version": 1,
            "parameters": [
                {
                    "name": "licenses",
                    "values": [],
                    "valueType": "string"
                }
            ]
        }
```

#### ユーザーのすべての更新トークンを取り消す

ユーザーのすべての更新トークンとブラウザー セッション トークンを取り消すことができます。 これにより、外部ユーザーのサインイン セッションを除き (外部ユーザーはホーム テナントを通じてサインインするため)、ユーザーのアプリケーションに発行されたすべての更新トークンとブラウザー セッション トークンが無効になります。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: 更新トークンの取り消しタスクのスクリーンショット。]

Microsoft Graph の場合、[**ユーザーのすべての更新トークンを取り消す**] タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 退職者、異動者 |
| displayName | ユーザーのすべての更新トークンを取り消す |
| 説明 | ユーザーのすべての更新トークンを取り消す |
| taskDefinitionId | 509589a4-0466-4471-829e-49c5e502bdee |

ワークフロー内での使用例:

```json
{
    "category": "leaver, mover",
    "continueOnError": false,
    "description": "Revoke all refresh tokens for user",
    "displayName": "Revoke all refresh tokens for user",
    "isEnabled": true,
    "taskDefinitionId": "509589a4-0466-4471-829e-49c5e502bdee",
    "arguments": []
}
```

#### スポンサー プランの変更に関するメールをマネージャーに送信する

移動または退出した従業員の上司に電子メールを送信できるようにします。 この電子メールは、移動または退職した従業員が 1 つ以上のエージェント ID のスポンサーであることをマネージャーに通知します。 これにより、マネージャーは、エージェント ID がスポンサーとして別の従業員を持つ必要があるかどうかを判断できます。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: スポンサー譲渡タスクについてマネージャーにメールを送信するスクリーンショット。]

Microsoft Graph の場合、 **スポンサー プランの変更に関するマネージャーに電子メールを送信する** タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 退職者、異動者 |
| displayName | スポンサー プランの変更に関するメールをマネージャーに送信する |
| 説明 | エージェント ID スポンサー プランの譲渡について、スポンサーのマネージャーに通知します。 |
| taskDefinitionId | b8c4e1f9-3a7d-4b2e-9c5f-8d6a9b1c2e3f |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```json
{
    "category": "mover",
    "continueOnError": false,
    "description": "Notify sponsor's manager about agent identity sponsorship transfer.",
    "displayName": "Send email to manager about sponsorship transfer",
    "isEnabled": true,
    "taskDefinitionId": "b8c4e1f9-3a7d-4b2e-9c5f-8d6a9b1c2e3f  ",
    "arguments": [
        {
            "name": "cc",
            "value": "ac17d108-60cd-4eb2-a4b4-084cacda33f2,7d3ee937-edcc-46b0-9e2c-f832e01231ea"
        },
        {
            "name": "customSubject",
            "value": "{{userDisplayName}} has moved"
        },
        {
            "name": "customBody",
            "value": "Hello {{managerDisplayName}}\n\nwe are reaching out to let you know {{userDisplayName}} has moved in the organization and you have taken over their agent ID sponsorships.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### スポンサーの変更に関するメールを共同スポンサーに送信する

エージェント ID のスポンサーでもあるユーザーが移動または退出したときに、エージェント ID の共同スポンサーに電子メールを送信できるようにします。 これにより、エージェント ID のスポンサーシップに対する変更が共同スポンサーに通知されます。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: 変更に関する電子メールを共同パートナーに送信します。]

Microsoft Graph の場合、 **スポンサーの変更に関する共同スポンサーへの電子メールの送信タスクの** パラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 退職者、異動者 |
| displayName | スポンサーの変更に関するメールを共同スポンサーに送信する |
| 説明 | エージェントのアイデンティティスポンサーシップの変更について共同スポンサーに通知します。 |
| taskDefinitionId | ad3b85cd-75b1-43e7-b4b9-0e52faba3944 |

ワークフロー内での使用例:

```json
{
    "category": "mover",
    "continueOnError": false,
    "description": "Notify co-sponsors about agent identity sponsorship changes.",
    "displayName": "Send email to co-sponsors about sponsor changes",
    "isEnabled": true,
    "taskDefinitionId": "ad3b85cd-75b1-43e7-b4b9-0e52faba3944",
    "arguments": [
        {
            "name": "cc",
            "value": "ac17d108-60cd-4eb2-a4b4-084cacda33f2,7d3ee937-edcc-46b0-9e2c-f832e01231ea"
        },
        {
            "name": "customSubject",
            "value": "{{userDisplayName}} has moved"
        },
        {
            "name": "customBody",
            "value": "Hello \n\nwe are reaching out to let you know {{userDisplayName}} has moved in the organization and will no longer have sponsorship authority over agent IDs they previously sponsored with you.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### エージェントのアイデンティティ・スポンサーシップをマネージャーに譲渡する

すべてのエージェント ID スポンサーシップをユーザーからマネージャーに転送できるようにします。 ユーザーのマネージャーを取得し、スポンサーの各エージェント ID を決定し、マネージャーをスポンサーとして追加し、スポンサーとしてユーザーを削除することで、タスクを実行します。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: エージェント ID スポンサーシップをマネージャーに転送する。] Microsoft Entra で **エージェント ID スポンサーシップをマネージャーに転送する** タスクを実行するための前提条件は次のとおりです。

- ユーザーに関する設定済みのマネージャー属性。

Microsoft Graphの場合、**マネージャーへのエージェント ID スポンサーシップの転送** タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | 退職者、異動者 |
| displayName | エージェントのアイデンティティ・スポンサーシップをマネージャーに譲渡する |
| 説明 | ユーザーがスポンサーするすべてのエージェント ID をマネージャーに転送します。 |
| taskDefinitionId | b8f4c3d5-9e7a-4b1c-8f2d-6a5e8b9c7f4a |

```Example
{
    "category": "mover",
    "continueOnError": false,
    "description": "Transfer all agent identities sponsored by user to manager.",
    "displayName": "Transfer agent identity sponsorships to manager",
    "isEnabled": true,
    "taskDefinitionId": "b8f4c3d5-9e7a-4b1c-8f2d-6a5e8b9c7f4a",
    "arguments": []
}
```

#### ユーザーからすべてのライセンス割り当てを削除する

ユーザーからすべての直接ライセンス割り当てを削除できます。 グループ ベースのライセンス割り当ての場合、タスクを実行して、ライセンスの割り当てが含まれるグループからユーザーを削除します。

このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: ユーザーからすべてのライセンスを削除する。]

Microsoft Graph の場合、"**ユーザーからすべてのライセンス割り当てを削除する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | Remove all licenses for user (ユーザーのすべてのライセンスを削除する) (ユーザーがカスタマイズ可能) |
| 説明 | Remove all licenses assigned to the user (ユーザーに割り当てられているすべてのライセンスを削除する) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 8fa97d28-3e52-4985-b3a9-a1126f9b8b4e |

ワークフロー内での使用例:

```json
{
            "category": "leaver",
            "continueOnError": true,
            "displayName": "Remove all licenses for user",
            "description": "Remove all licenses assigned to the user",
            "isEnabled": true,
            "taskDefinitionId": "8fa97d28-3e52-4985-b3a9-a1126f9b8b4e",
            "arguments": []
}

```

#### ユーザーの削除

ユーザー アカウントを削除できます。 同期されたオンプレミス ユーザーに対してこのタスクを実行するための前提条件については、「[ユーザー アカウントのタスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-on-premises#user-account-tasks)」を参照してください。 Microsoft Entra ロールの割り当てを持つユーザーはサポートされていません。また、ロール割り当て可能なグループのメンバーシップまたは所有権を持つユーザーもサポートされていません。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: ユーザー アカウントを削除する。]

Microsoft Graph の場合、"**ユーザーを削除する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | Delete user account (ユーザー アカウントを削除する) (ユーザーがカスタマイズ可能) |
| 説明 | Microsoft Entra ID でユーザー アカウントを削除する (ユーザーによるカスタマイズ可能) |
| taskDefinitionId | 8d18588d-9ad3-4c0f-99d0-ec215f0e3dff |
| 引数 | 省略可能な *deleteOnPremisesAccount* 引数を含めることができます。 この値は、同期されたオンプレミス ユーザーに対してタスクを実行する場合は *true*、オンプレミス ユーザーに対してタスクを実行しない場合は *false* のいずれかのブール値です。 |

ワークフロー内での使用例:

```json
{
            "category": "leaver",
            "continueOnError": true,
            "displayName": "Delete user account",
            "description": "Delete user account in Azure AD",
            "isEnabled": true,
            "taskDefinitionId": "8d18588d-9ad3-4c0f-99d0-ec215f0e3dff",
            "arguments": [
                {
                    "name": "deleteOnPremisesAccount",
                    "value": "true"
                }
            ]
}

```

#### ユーザーの最終在籍日の前にマネージャーにメールを送信する

最終在籍日の前にユーザーのマネージャーにメールを送信できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: ユーザーの最終在籍日の前にメールを送信するタスク。]

[**ユーザーの最終在籍日の前にメールを送信する**] タスクを実行するための Microsoft Entra の前提条件:

- ユーザーに関する設定済みのマネージャー属性。
- ユーザーに関する設定済みのマネージャーのメール属性。

Microsoft Graph の場合、**Send email before user's last day** (ユーザーの最終在籍日の前にメールを送信する) タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | Send email before user’s last day (ユーザーの最終在籍日の前にメールを送信する) (ユーザーがカスタマイズ可能) |
| 説明 | Send offboarding email to user’s manager before the last day of work (最終在籍日の前にユーザーのマネージャーにオフボーディング メールを送信する) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 52853a3e-f4e5-4eb8-bb24-1ac09a1da935 |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```
{
    "category": "leaver",
    "continueOnError": false,
    "description": "Send offboarding email to user’s manager before the last day of work",
    "displayName": "Send email before user’s last day",
    "isEnabled": true,
    "taskDefinitionId": "52853a3e-f4e5-4eb8-bb24-1ac09a1da935",
    "arguments": [
        {
            "name": "cc",
            "value": "068fa0c1-fa00-4f4f-8411-e968d921c3e7,e94ad2cd-d590-4b39-8e46-bb4f8e293f85"
        },
        {
            "name": "customSubject",
            "value": "Reminder that {{userDisplayName}}'s last day is coming up"
        },
        {
            "name": "customBody",
            "value": "Hello {{managerDisplayName}}\n\nthis is a reminder that {{userDisplayName}}'s last day is coming up.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### ユーザーの最終日にメールを送信する

最終在籍日にユーザーのマネージャーにメールを送信できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: 最終在籍日にメールを送信するタスク。]

[**ユーザーの最終在籍日にメールを送信する**] タスクを実行するための Microsoft Entra の前提条件:

- ユーザーに関する設定済みのマネージャー属性。
- ユーザーに関する設定済みのマネージャーのメール属性。

Microsoft Graph の場合、"**ユーザーの最終在籍日にメールを送信する**" タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | Send email on user’s last day (ユーザーの最終在籍日にメールを送信する) (ユーザーがカスタマイズ可能) |
| 説明 | Send offboarding email to user’s manager on the last day of work (最終在籍日にユーザーのマネージャーにオフボーディング メールを送信する) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 9c0a1eaf-5bda-4392-9d9e-6e155bb57411 |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```json
{
    "category": "leaver",
    "continueOnError": false,
    "description": "Send offboarding email to user’s manager on the last day of work",
    "displayName": "Send email on user’s last day",
    "isEnabled": true,
    "taskDefinitionId": "9c0a1eaf-5bda-4392-9d9e-6e155bb57411",
    "arguments": [
        {
            "name": "cc",
            "value": "068fa0c1-fa00-4f4f-8411-e968d921c3e7,e94ad2cd-d590-4b39-8e46-bb4f8e293f85"
        },
        {
            "name": "customSubject",
            "value": "{{userDisplayName}}'s last day"
        },
        {
            "name": "customBody",
            "value": "Hello {{managerDisplayName}}\n\nthis is a reminder that {{userDisplayName}}'s last day is today and their access will be revoked.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### ユーザーの最終在籍日の後でユーザーのマネージャーにメールを送信する

ユーザーの最終在籍日の後でユーザーのマネージャーにオフボーディング情報を含むメールを送信できます。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスク: [ユーザーの最終在籍日の後でユーザーのマネージャーにオフボーディング メールを送信する] のスクリーンショット。]

[**ユーザーの最終在籍日の後でユーザーのマネージャーにメールを送信する**] タスクを実行するための Microsoft Entra の前提条件:

- ユーザーに関する設定済みのマネージャー属性。
- ユーザーに関する設定済みのマネージャーのメール属性。

Microsoft Graph の場合、[**ユーザーの最終在籍日の後でユーザーのマネージャーにメールを送信する**] タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | ユーザーの最終在籍日の後でユーザーのマネージャーにメールを送信する |
| 説明 | Send offboarding email to user’s manager after the last day of work (最終在籍日の後でユーザーのマネージャーにオフボーディング メールを送信する) (ユーザーがカスタマイズ可能) |
| taskDefinitionId | 6f22ddd4-b3a5-47a4-a846-0d7c201a49ce |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```json
{
    "category": "leaver",
    "continueOnError": false,
    "description": "Send offboarding email to user’s manager after the last day of work",
    "displayName": "Send email after user’s last day",
    "isEnabled": true,
    "taskDefinitionId": "6f22ddd4-b3a5-47a4-a846-0d7c201a49ce",
    "arguments": [
        {
            "name": "cc",
            "value": "ac17d108-60cd-4eb2-a4b4-084cacda33f2,7d3ee937-edcc-46b0-9e2c-f832e01231ea"
        },
        {
            "name": "customSubject",
            "value": "{{userDisplayName}}'s accounts will be deleted today"
        },
        {
            "name": "customBody",
            "value": "Hello {{managerDisplayName}}\n\nthis is a reminder that {{userDisplayName}} left the organization a while ago and today their disabled accounts will be deleted.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### ユーザーの非アクティブに関する電子メールを送信する

非アクティブなユーザーに関する情報を含む電子メールを、最後のサインインとして指定した日数後にユーザーのマネージャーに送信できるようにします。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。 [Image: ワークフロー タスクのスクリーンショット: 非アクティブな電子メールを送信します。]

**ユーザーの非アクティブ状態に関する電子メールの送信**タスクを実行するための Microsoft Entra の前提条件は次のとおりです。

- ユーザーに関する設定済みのマネージャー属性。
- ユーザーに関する設定済みのマネージャーのメール属性。

Microsoft Graph の場合、[ **ユーザーの非アクティブに関する電子メールの送信** ] タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | ユーザーの非アクティブに関する電子メールを送信する |
| 説明 | ユーザーが非アクティブであることをマネージャーに通知する (ユーザーによってカスタマイズ可能) |
| taskDefinitionId | 92f74cb4-f1b6-4ec0-b766-96210f56edc2 |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 |

ワークフロー内での使用例:

```json
{
    "category": "leaver",
    "continueOnError": false,
    "description": " Notify manager that user has been inactive",
    "displayName": "Send email about user inactivity",
    "isEnabled": true,
    "taskDefinitionId": "92f74cb4-f1b6-4ec0-b766-96210f56edc2",
    "arguments": [
        {
            "name": "cc",
            "value": "1bda0458-cb4b-473d-baf5-61125c86a6d3,7f552c25-e0f6-40b6-b4e0-36bb40ba7fb6"
        },
        {
            "name": "customSubject",
            "value": "[UserDisplayName]’s account has been inactive for [NumberofDays] days."
        },
        {
            "name": "customBody",
            "value": "We’re notifying you that a recent sign-in for [UserDisplayName]’s user account has not been detected, and the account is considered inactive. The last sign in was [LastSigninDate]. To maintain a secure environment, your organization may have already started the process to disable or delete this user account.\n\nPlease check your organization’s policies and take appropriate action if this user account is still needed.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```

#### 応答されていないゲストの削除に関する電子メールを送信する (プレビュー)

応答していないゲストが組織から削除されるときに、指定した受信者に電子メール通知を送信できます。 このタスクは、Microsoft Entra IDでスポンサーレス ゲストを検出してクリーンアップするための自動化の一部です。 このタスクのタスク名と説明は、Microsoft Entra 管理センターでカスタマイズできます。

[Image: ワークフロー タスクのスクリーンショット: 応答していないゲスト削除タスクに関する電子メールを送信します。]

注

このタスクは現在プレビュー段階であり、leaver ワークフローでのみ使用できます。

**応答されていないゲストの削除 (プレビュー) タスクに関する電子メールの送信タスクを**実行するためのMicrosoft Entraの前提条件は次のとおりです。

- ゲスト ユーザー アカウントは、スポンサーなし（有効なスポンサーがない）として識別されている必要があります。

Microsoft Graphの場合、**応答していないゲストの削除 (プレビュー) タスクに関する電子メールの送信**タスクのパラメーターは次のとおりです。

| パラメーター | Definition |
| --- | --- |
| カテゴリ | leaver |
| displayName | 応答されていないゲストの削除に関する電子メールを送信する (プレビュー) |
| 説明 | 応答されていないゲストの削除について指定された受信者に通知する (ユーザーがカスタマイズ可能) |
| taskDefinitionId | a8e4c7f2-9d1b-4a3e-b5c6-8f2e7a9d4c1b |
| 引数 | オプションの 一般的な電子メール タスク パラメーター を指定できます。含まれていない場合は、既定の動作が有効になります。 受信者には、ライフサイクル ワークフロー管理者、グローバル管理者、選択したユーザー、マネージャー、またはユーザーを含めることができます。 |

ワークフロー内での使用例:

```json
{
    "category": "leaver",
    "continueOnError": false,
    "description": "Notify specified recipients about unsponsored guest removal",
    "displayName": "Send email about unsponsored guest removal (Preview)",
    "isEnabled": true,
    "taskDefinitionId": "a8e4c7f2-9d1b-4a3e-b5c6-8f2e7a9d4c1b",
    "arguments": [
        {
            "name": "cc",
            "value": "ac17d108-60cd-4eb2-a4b4-084cacda33f2"
        },
        {
            "name": "customSubject",
            "value": "Guest user {{userDisplayName}} removal notification"
        },
        {
            "name": "customBody",
            "value": "Hello,\n\nThis is a notification that guest user {{userDisplayName}} is being removed from your organization due to no valid sponsor.\n\nIf you believe this action is incorrect, please contact your IT department immediately.\n\nRegards\nYour IT department"
        },
        {
            "name": "locale",
            "value": "en-us"
        }
    ]
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-templates"} -->
## ライフサイクル ワークフローのテンプレートとカテゴリ - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-04-25
- Summary: ライフサイクル ワークフロー テンプレートとカテゴリについて説明した概念的な記事。

ライフサイクル ワークフローを使って、組み込みタスクとカスタム タスクの拡張機能の両方を含むワークフローを作成することで、組織のライフサイクル管理プロセスを自動化することができます。 これらのワークフローとその中のタスクは、いずれもライフサイクル管理のジョイナー (就職者) - ムーバー (異動者) - リーバー (退職者) (JML) モデルに基づくカテゴリに分類されます。 このプロセスをさらに効率化するために、ライフサイクル ワークフローにはテンプレートが用意されており、これを使って一般的なライフサイクル管理プロセスの設定、作成、構成を迅速に行うことができます。 これらのテンプレートに基づいて、変更を加えずにワークフローを作成することや、組織内のユーザーの要件に合わせてさらにカスタマイズすることができます。 この記事では、ワークフロー テンプレートの詳細な一覧、一般的なテンプレート パラメーター、特定のテンプレート向けの既定のテンプレート パラメーター、各テンプレートに対応したタスクの一覧を紹介します。 詳細なタスク定義については、[ライフサイクル ワークフローのタスクと定義](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks)に関する記事を参照してください。

### ライフサイクル ワークフローの組み込みテンプレート

ライフサイクル ワークフローには、現在、使用またはカスタマイズできる 14 個の組み込みテンプレートがあります。

[Image: ライフサイクル ワークフロー テンプレートの一覧のスクリーンショット。]

テンプレートの一覧は次のとおりです。

- [雇用前の従業員の受け入れを行う](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#onboard-pre-hire-employee)
- [新入社員を迎え入れる](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#onboard-new-hire-employee)
- [従業員のオンボーディング後](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#post-onboarding-of-an-employee)
- [リアルタイムの従業員の変更](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#real-time-employee-change)
- [Real-time employee termination (リアルタイムの従業員の退職)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#real-time-employee-termination)
- [従業員退職手続き前の準備](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#pre-offboarding-of-an-employee)
- [従業員をオフボードする](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#offboard-an-employee)
- [従業員のオフボーディング後](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#post-offboarding-of-an-employee)
- [Employee group membership changes (従業員グループ メンバーシップの変更)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#employee-group-membership-changes)
- [Employee job profile change (従業員ジョブ プロファイルの変更)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#employee-job-profile-change)
- [オフボード前の非アクティブユーザー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#pre-offboard-inactive-users)
- [非アクティブなユーザーの削除](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#offboard-inactive-users)
- [スポンサーが退出した場合の移行エージェントのスポンサー シップ](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#transition-agent-sponsorships-when-a-sponsor-leaves)
- [スポンサーがロールを変更した場合の移行エージェントのスポンサー プラン](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#transition-agent-sponsorships-when-a-sponsor-changes-roles)

テンプレートから新しいワークフローを作成する場合の詳細なガイドについては、[Microsoft Entra 管理センターでライフサイクル ワークフローを使ってユーザーを組織にオンボードする方法のチュートリアル](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-onboard-custom-workflow-portal)を参照してください。

注

ライフサイクル ワークフローは、ルーチン プロセスを自動化することで、Microsoft Entra ID ガバナンスの[人事主導のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)を強化します。 HR プロビジョニングは、ユーザー アカウントの作成と属性の更新を管理しますが、ライフサイクル ワークフローによってタスクの追加の自動化が提供されます。

#### 採用前の従業員を導入する

**"Onboard pre-hire employee" (雇用前の従業員をオンボードする)** テンプレートは、従業員の入社日前に完了する必要があるタスクを構成できるように設計されています。

[Image: ライフサイクル ワークフローの雇用前のオンボード テンプレートのスクリーンショット。]

**"Onboard pre-hire employee" (雇用前の従業員をオンボードする)** テンプレートの既定の固有パラメーターとプロパティは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 結合器 | ❌ |
| トリガーの種類 | 時間ベースの属性、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **時間ベース**: イベントからの日数、イベントのタイミング、イベントのユーザー属性 • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| イベントからの日数 | -7 | ✔️ |
| イベントのタイミング | 変更前 | ❌ |
| イベント ユーザー属性 | 従業員採用日 | ❌ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **[Generate TAP And Send Email] (TAP を生成してメールを送信する)** | ✔️ |

**Onboard pre-hire employee (雇用前の従業員をオンボードする)** テンプレートを使ったワークフローの設定に関するチュートリアルについては、「[初出勤日前に従業員のオンボーディング タスクを Microsoft Entra 管理センターを使って自動化する](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-onboard-custom-workflow-portal)」を参照してください。

#### Onboard new hire employee (新入社員をオンボードする)

**"Onboard new-hire employee" (新入社員をオンボードする)** テンプレートは、従業員の入社日に完了するタスクを構成できるように設計されています。

[Image: ライフサイクル ワークフローの新入社員のオンボード テンプレートのスクリーンショット。]

**"Onboard new hire employee" (新入社員をオンボードする)** テンプレートの既定の固有パラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 結合器 | ❌ |
| トリガーの種類 | 時間ベースの属性、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **時間ベース**: イベントからの日数、イベントのタイミング、イベントのユーザー属性 • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| イベントからの日数 | 0 | ❌ |
| イベントのタイミング | オン | ❌ |
| イベント ユーザー属性 | EmployeeHireDate、createdDateTime | ✔️ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **[Add User To Group] (ユーザーをグループに追加する)**、**[Enable User Account] (ユーザー アカウントを有効にする)**、**[Send Welcome Email] (ようこそメールを送信する)** | ✔️ |

#### 従業員のオンボーディング完了後

**従業員のオンボーディング後の**テンプレートは、従業員の開始日または作成日の後に完了するタスクを構成するように設計されています。

[Image: ライフサイクル ワークフローの新入社員オンボード後用テンプレートのスクリーンショット。]

**"Post-Onboarding of an employee" (従業員のオンボーディング後)** テンプレートの既定の固有パラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 結合器 | ❌ |
| トリガーの種類 | 時間ベースの属性、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **時間ベース**: イベントからの日数、イベントのタイミング、イベントのユーザー属性 • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| イベントからの日数 | 7 | ✔️ |
| イベントのタイミング | クリック後 | ❌ |
| イベント ユーザー属性 | EmployeeHireDate、createdDateTime | ✔️ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **[Add User To Group] (ユーザーをグループに追加する)**、**[Add user to selected teams] (選択したチームにユーザーを追加する)** | ✔️ |

#### リアルタイムの従業員の変更

**"Real-time employee change" (リアルタイムの従業員の変更)** テンプレートは、従業員が役割を変更したときにすぐに完了するタスクを構成できるように設計されています。

[Image: ライフサイクル ワークフローのリアルタイムの従業員の変更テンプレートのスクリーンショット。]

**リアルタイムの従業員の退職**テンプレート既定の固有パラメータは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | ムーバー | ❌ |
| トリガーの種類 | オンデマンド | ❌ |
| タスク | **カスタム タスク拡張機能を実行**し、 **ユーザーのすべてのアクセス パッケージの割り当てを削除** する (既定では 15 日にスケジュールされた削除) | ✔️ |

注

このテンプレートはオンデマンドで実行されるように設計されているので、実行条件は存在しません。

#### リアルタイムの従業員解雇

**"Real-time employee termination" (リアルタイムの従業員の退職)** テンプレートは、従業員が退職したときにすぐに完了するタスクを構成できるように設計されています。

[Image: ライフサイクル ワークフローのリアルタイムの従業員の退職テンプレートのスクリーンショット。]

**"Real-time employee termination" (リアルタイムの従業員の退職)** テンプレートの既定の固有パラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 退職者 | ❌ |
| トリガーの種類 | オンデマンド | ❌ |
| タスク | **[Remove user from all groups] (ユーザーをすべてのグループから削除する)**、**[Delete User Account] (ユーザー アカウントを削除する)**、**[Remove user from all Teams] (すべての Teams からユーザーを削除する)** | ✔️ |

注

このテンプレートはオンデマンドで実行されるように設計されているので、実行条件は存在しません。

#### 従業員のオフボーディング前の準備

**"Pre-Offboarding of an employee" (従業員のオフボーディング前)** テンプレートは、従業員の最終勤務日前に完了するタスクを構成できるように設計されています。

[Image: オフボーディング前の従業員テンプレートのスクリーンショット。]

**従業員のオフボーディング前** テンプレートの既定の固有パラメーターは次の通りです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 退職者 | ❌ |
| トリガーの種類 | 時間ベースの属性、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **時間ベース**: イベントからの日数、イベントのタイミング、イベントのユーザー属性 • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| イベントからの日数 | 7 | ✔️ |
| イベントのタイミング | 変更前 | ❌ |
| イベント ユーザー属性 | 従業員退勤日時 | ❌ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **[Remove user from selected groups] (選択したグループからユーザーを削除する)**、**[Remove user from selected Teams] (選択した Teams からユーザーを削除する)** | ✔️ |

#### Offboard an employee (従業員をオフボードする)

**"Offboard an employee" (従業員をオフボードする)** テンプレートは、従業員の最終勤務日に完了するタスクを構成できるように設計されています。

[Image: 従業員のオフボード テンプレートのライフサイクル ワークフローのスクリーンショット。]

**"Offboard an employee" (従業員をオフボードする)** テンプレートの既定の固有パラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 退職者 | ❌ |
| トリガーの種類 | 時間ベースの属性、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **時間ベース**: イベントからの日数、イベントのタイミング、イベントのユーザー属性 • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| イベントからの日数 | 0 | ✔️ |
| イベントのタイミング | オン | ❌ |
| イベント ユーザー属性 | 従業員退勤日時 | ❌ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **[Disable User Account] (ユーザー アカウントを無効にする)**、**[Remove user from all groups] (ユーザーをすべてのグループから削除する)**、**[Remove user from all Teams] (すべての Teams からユーザーを削除する)** | ✔️ |

#### Post-Offboarding of an employee (従業員のオフボーディング後)

**従業員オフボード後のテンプレート**は、従業員の最終勤務日の後に完了するタスクを構成するように設計されています。

[Image: 最終勤務日後の従業員のオフボーディング テンプレートを示すスクリーンショット。]

**"従業員退職後の手続き"** テンプレートの既定の固有パラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 退職者 | ❌ |
| トリガーの種類 | 時間ベースの属性、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **時間ベース**: イベントからの日数、イベントのタイミング、イベントのユーザー属性 • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| イベント ユーザー属性 | 従業員退勤日時 | ❌ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **[Remove all licenses for user] (ユーザーのすべてのライセンスを削除する)**、**[Remove user from all Teams] (すべての Teams からユーザーを削除する)**、**[Delete User Account] (ユーザー アカウントを削除する)** | ✔️ |

**Post-Offboarding of an employee (従業員のオフボーディング後)** テンプレートを使ったワークフローの設定に関するチュートリアルについては、「[従業員の在籍最終日の後のオフボーディング タスクを Microsoft Entra 管理センターで自動化する](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-scheduled-leaver-portal)」を参照してください。

#### Employee group membership changes (従業員グループ メンバーシップの変更)

**Employee group membership changes (従業員グループ メンバーシップの変更)** テンプレートは、従業員のグループ メンバーシップに変更があるときに実行するタスクを構成するために設計されています。

[Image: Employee group membership changes (従業員グループ メンバーシップの変更) テンプレートのスクリーンショット。]

**Employee group membership changes (従業員グループ メンバーシップの変更)** テンプレートの既定の固有パラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 移動者 | ❌ |
| トリガーの種類 | 属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| Scope | トリガーによって異なります。 **ルール ベース**: 属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **ユーザーのすべてのアクセス パッケージの割り当てを削除する** (既定で 15 日にスケジュールされた削除)、 **選択した Teams からユーザーを削除**する、 **メールを送信してユーザーの移動をマネージャーに通知する** | ✔️ |

#### Employee job profile change (従業員ジョブ プロファイルの変更)

**Employee job profile change (従業員ジョブ プロファイルの変更)** テンプレートは、従業員の役職に変更があるときに実行するタスクを構成するために設計されています。

[Image: Employee job profile change (従業員ジョブ プロファイルの変更) テンプレートのスクリーンショット。]

**Employee job profile change (従業員ジョブ プロファイルの変更)** テンプレートの既定の固有パラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 移動者 | ❌ |
| トリガーの種類 | 属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| Scope | トリガーによって異なります。 **ルール ベース**: 属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **メールを送信して、ユーザーの移動をマネージャーに通知する**、**ユーザーのすべてのアクセス パッケージの割り当てを削除**する (既定では 15 日にスケジュールされた削除)、**選択したグループからユーザーを削除**する、**選択した Teams からユーザーを削除**する、**ユーザー アクセス パッケージの割り当てを要求**する | ✔️ |

**従業員プロファイル変更**テンプレートを使用するワークフローの設定に関するチュートリアルについては、「[Microsoft Entra 管理センターを使用してジョブを変更するときに従業員の移動タスクを自動化](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-mover-custom-workflow-portal)する」を参照してください。

#### オフボード前の非アクティブユーザー

**オフボード前の非アクティブなユーザー テンプレートは、非アクティブなユーザー**をオフボードする前に完了する必要があるタスクを構成するように設計されています。

[Image: オフボード前の非アクティブなユーザー テンプレートのスクリーンショット。]

**オフボード前非アクティブユーザー** テンプレートの既定の特定のパラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 退職者 | ❌ |
| トリガーの種類 | サインイン非アクティブ、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **サインイン非アクティビティ**: 非アクティビティの日数 • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| 非アクティブな日数 | 90 | ✔️ |
| イベントのタイミング | 変更前 | ❌ |
| イベント ユーザー属性 | 最後に正常にサインインした日時 | ❌ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **ユーザー アカウントを無効にする**、**非アクティブな通知メールを送信**する | ✔️ |

#### 非アクティブユーザーをオフボードする

**非アクティブユーザーのオフボーディング** テンプレートは、非アクティブなユーザーに対して完了する必要があるタスクを構成するように設計されています。

[Image: オフボードの非アクティブなユーザー テンプレートのスクリーンショット。]

**Offboard 非アクティブユーザー** テンプレートの既定の特定のパラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 退職者 | ❌ |
| トリガーの種類 | サインイン非アクティブ、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **サインイン非アクティブ**: 非アクティブな日数  • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| イベントからの日数 | 120 | ✔️ |
| イベントのタイミング | クリック後 | ❌ |
| イベント ユーザー属性 | 最後に正常にサインインした日時 | ❌ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **ユーザー アカウントを無効にする**、**非アクティブな通知メールを送信**する | ✔️ |

#### スポンサーが退出した場合の移行エージェントのスポンサー シップ

**スポンサーが退職したときの移行エージェントのスポンサーシップ** テンプレートは、スポンサーが組織を離れたときにエージェント のスポンサーシップを移行するタスクを構成するように設計されています。 このテンプレートは、Microsoft Agent 365 ライセンスを持つテナントで使用できます。 詳細については、「[Microsoft Agent 365 のドキュメント](https://aka.ms/entraagent365)を参照してください。

**スポンサーが離脱する際の代理スポンサーシップの移行テンプレートの**既定の特定パラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | 退職者 | ❌ |
| トリガーの種類 | 時間ベースの属性、属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **時間ベース**: イベントからの日数、イベントのタイミング、イベントのユーザー属性 • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| イベントからの日数 | 0 | ✔️ |
| イベントのタイミング | オン | ❌ |
| イベント ユーザー属性 | 従業員退勤日時 | ❌ |
| Scope | トリガーによって異なります。 **ルール ベース**: 時間ベースの属性、属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **スポンサー プランの変更に関するメールをマネージャーに送信**する、 **スポンサーの変更に関する共同スポンサーにメールを送信**する、 **代理人のスポンサープランをマネージャーに譲渡する** | ✔️ |

#### スポンサーがロールを変更した場合の移行エージェントのスポンサーシップ

**スポンサーが役割を変更した際のスポンサーエージェント移行**テンプレートは、組織内でスポンサーが役割を変更する際にエージェントスポンサーシップを移行するためのタスクを設定するように設計されています。 このテンプレートは、Microsoft Agent 365 ライセンスを持つテナントで使用できます。 詳細については、「[Microsoft Agent 365 のドキュメント](https://aka.ms/entraagent365)を参照してください。

スポンサーがロール テンプレートを **変更する場合の、Transition エージェント スポンサープランの** 既定の特定のパラメーターは次のとおりです。

| パラメーター | 説明 | カスタマイズ可能 |
| --- | --- | --- |
| カテゴリ | ムーバー | ❌ |
| トリガーの種類 | 属性の変更、グループ メンバーシップの変更 | ✔️ |
| トリガーの詳細 | トリガーの種類の選択によって異なります。  • **属性の変更**: トリガー属性 • **グループ メンバーシップの変更**: グループに追加/グループから削除 | ✔️ |
| Scope | トリガーによって異なります。 **ルール ベース**: 属性の変更。**グループ メンバーシップの変更**: グループ ベース。 | ✔️ |
| タスク | **スポンサー プランの変更に関するメールをマネージャーに送信**する、 **スポンサーの変更に関する共同スポンサーにメールを送信**する、 **代理人のスポンサープランをマネージャーに譲渡する** | ✔️ |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-versioning"} -->
## ワークフローのバージョン管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-versioning
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローのバージョン管理と履歴について説明する記事

ライフサイクル ワークフローを使用して作成されたワークフローは、組織内のユーザーのライフサイクルを監査するという観点から、組織の要件を満たすために必要に応じて更新できます。 ワークフローの更新を管理するために、ライフサイクル ワークフローではワークフローのバージョン管理の概念が導入されています。 ワークフロー バージョンは、実行条件またはそのタスクを更新することによってトリガーされる、既存のワークフローの新しいバージョンです。 ワークフローのバージョンでは、既存のワークフローのアクションやスコープを変更できます。 ワークフローの更新プロセス中にワークフローのバージョン管理がどのように処理されるかを理解すると、ワークフロータスクと条件がワークフローによって処理されるユーザーに常に関連するようにワークフローを戦略的に設定できます。

### バージョン管理の利点

ライフサイクル ワークフローを使用したバージョン管理では、ユース ケースごとに新しいワークフローを作成する代わりに多くの利点があります。 これらの利点は、次の方法で、トラブルシューティングと記録保持の両方の機能のレポート プロセスを改善する機能に表示されます。

- **長期的なリテンション期間** - バージョン管理を使用すると、監査ログのみを使用する場合よりも、ワークフロー情報の保持期間を長くすることができます。 監査ログには過去 30 日間の情報のみが格納されますが、バージョン管理ではワークフローの詳細を作成から追跡できます。
- **追跡可能性** - ワークフローのどの特定のバージョンがユーザーを処理したかを追跡できます。

### ワークフローのプロパティとバージョン

ワークフローの更新によって新しいバージョンの作成がトリガーされる可能性がある一方で、必ずしもそうであるわけではありません。 ワークフローの新しいバージョンを作成せずに変更できる、基本プロパティと呼ばれるワークフローのパラメーターがあります。 これらのパラメーターの一覧は次のとおりです。

- displayName
- 説明
- 有効化されている
- IsSchedulingEnabled
- タスク名
- タスクの説明

これらの対応するパラメーターは、Microsoft Entra 管理センターの更新するワークフローの **[プロパティ** ] セクションにあります。 [Image: 更新された基本プロパティ LCW のスクリーンショット]

Microsoft Entra 管理センターと Microsoft Graph 経由の API の両方を使用してこれらのプロパティを更新する手順ガイドについては、「 [ワークフロー プロパティの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-properties)」を参照してください。

新しいバージョンの作成をトリガーするプロパティは次のとおりです。

- タスク
- 実行条件

これらのワークフローの新しいバージョンは、Microsoft Entra 管理センターで更新を行うとすぐに行われますが、Microsoft Graph で API を使用してワークフローの新しいバージョンを作成するには、createNewVersion メソッドを実行する必要があります。 タスクまたは実行条件を更新するためのステップ バイ ステップ ガイドについては、「 [ワークフロー バージョンの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-tasks)」を参照してください。

注

ワークフローがオンデマンドの場合、実行条件に関連付けられている構成情報は存在しません。

### ワークフロー バージョン履歴に含まれる詳細

ワークフローの基本プロパティの変更とは異なり、新しく作成されたワークフロー バージョンは以前のバージョンとは大きく異なる場合があります。 タスクは追加または削除でき、ワークフローの実行者は異なる場合があります。 バージョン間のワークフローに発生する可能性のある大幅な変更により、バージョンの詳細も存在し、ワークフローの現在のバージョンだけでなく、以前のイテレーションに関する詳細情報も提供されます。

Microsoft Entra 管理センターに示されているように、バージョン情報に含まれる詳細:

[Image: ワークフローのバージョン管理情報のスクリーンショット。]

詳細な **バージョン情報** は次のとおりです。

| パラメータ | 説明 |
| --- | --- |
| バージョン番号 | 情報の対象となるワークフローのバージョンを示す整数。 新しいワークフロー バージョンが追加されるたびに、順次増加します。 |
| 最終更新日 | ワークフローが最後に更新された時刻。 以前のバージョンのワークフローでは、最後に変更された日付は常に次のバージョンが作成された時刻になります。 |
| 最終更新元 | このワークフロー バージョンを最後に変更したユーザー。 |
| 作成日 | ワークフロー バージョンが作成された日時。 |
| 作成者 | この特定のバージョンのワークフローを作成したユーザー。 |
| 名前 | このバージョンのワークフローの名前。 |
| 説明 | このバージョンのワークフローの説明。 |
| カテゴリ | ワークフローのカテゴリ。 |
| 実行条件 | このバージョンでワークフローを実行するユーザーとタイミングを定義します。 |
| タスク | このワークフロー バージョンに存在するタスク。 API を使用して表示する場合は、タスクの引数も表示できます。 特定のタスク定義については、「[ライフサイクル ワークフローのタスクと定義](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks)」を参照してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflows-deployment"} -->
## ライフサイクル ワークフローのデプロイを計画する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflows-deployment
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローのデプロイを成功させるための計画ガイド。

[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)は、自動化を増やすことにより、組織が Microsoft Entra ユーザーを管理するのに役立ちます。 ライフサイクル ワークフローを使うと、次のことができます。

- タスクを簡素化および自動化する他のワークフローにより、人事主導のプロビジョニング プロセスを**拡張**します。
- ワークフロー プロセスを**一元化**して、ワークフローのすべての作成と管理を 1 か所で簡単に行うことができるようにします。
- ワークフローの履歴と監査ログを使って、最小限の労力でワークフローのシナリオの**トラブルシューティング**を行います。
- 大規模なユーザー ライフサイクルを**管理**します。 組織が成長しても、ユーザー のライフサイクルの管理に必要な他のリソースは少なく済みます。
- 自動化されたライフサイクル ワークフローを使用して、過去に行われた手動タスクを**減らす**か削除します。
- ロジック アプリを**適用**して、既存のロジック アプリを使用して、より複雑なシナリオに合わせてワークフローを拡張します。

ライフサイクル ワークフローは [Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) の機能です。 他に、[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)、[アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)、[Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)、[使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)などの機能があります。 これらの機能によって、次の疑問を解決できます。

- どのユーザーがどのリソースへのアクセス権を持つ必要があるか。
- それらのユーザーはそのアクセス権によって何を実行するか。
- アクセスを管理するための効果的な組織的管理は存在しているか。
- 監査者は管理が機能していることを確認できるか。
- ユーザーは、入社初日の準備ができているか、またはタイムリーにアクセス権が削除されているか。

ライフサイクル ワークフローのデプロイを計画することは、組織内のユーザーに対する望ましいガバナンス戦略を確実に実現するために不可欠です。

デプロイ計画について詳しくは、「[Microsoft Entra のデプロイ計画](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)」をご覧ください。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンス または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

#### ライフサイクル ワークフローのデプロイ プロジェクトを計画する

組織のニーズを考慮して、環境にライフサイクル ワークフローをデプロイするための戦略を決定します。

#### 適切な関係者を関わらせる

テクノロジ プロジェクトが失敗した場合、その原因は通常、影響、結果、および責任に対する想定の不一致です。 これらの落とし穴を回避するには、[適切な関係者が担当していることを確認](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)し、またそのプロジェクトの役割が明確になっていることを確認します。

ライフサイクル ワークフローの場合は、組織内の次のチームの代表者を含めることが考えられます。

- **IT 管理**は、IT インフラストラクチャ、クラウドへの投資、サービスとしてのソフトウェア (SaaS) アプリを管理します。 このチームは次のことを行います。

    - Microsoft 365 や Microsoft Entra ID など、インフラストラクチャとアプリのライフサイクル ワークフローを確認します。
    - ユーザーに対してライフサイクル ワークフローをスケジュールして実行します。
    - GRAPH または拡張性を使って、プログラムによるライフサイクル ワークフローが管理およびレビューされるようにします。
- **セキュリティ所有者**は、プランが組織のセキュリティ要件を満たすことを確認します。 このチームは次のことを行います。

    - ライフサイクル ワークフローが組織のセキュリティ ポリシーを満たしていることを確認する
- **コンプライアンス マネージャー**は、組織が内部ポリシーに従い、規制を遵守していることを確認します。 このチームは次のことを行います。

    - 新しいライフサイクル ワークフローのレビューを要求またはスケジュールします。
    - コンプライアンスのための文書と記録の保存など、ライフサイクル ワークフローのレビューに関するプロセスと手順を評価します。
    - 最も重要なリソースについて、過去のレビューの結果をレビューします。
- **人事担当者** - 人事シナリオにおける属性のマッピングと割り当てを支援します。 このチームは次のことを行います。

    - employeeHireDate と employeeLeaveDateTime の設定に使われる属性の決定を手伝います。
    - ソース属性が設定され、値を持っていることを確認します
    - employeeHireDate と employeeLeaveDateTime にマップできる代替属性を特定して提案します
- **開発チーム**は、組織のアプリケーションを構築および管理します。 このチームは次のことを行います。

    - GRAPH を使ってカスタム ワークフローを開発します
    - 拡張性を使って、ライフサイクル ワークフローと Logic Apps を統合します。

#### 連絡を計画する

コミュニケーションは、新しいビジネス プロセスの成功に必要不可欠です。 エクスペリエンスがいつ、どのように変わるのかについて、ユーザーに事前に連絡します。 問題が発生した場合にサポートを受ける方法を伝えます。

#### アカウンタビリティの変更の連絡

ライフサイクル ワークフローは、手動プロセスの責任のビジネス所有者への移行をサポートします。 各チームの責任の明確なプロセスと理解を確立します。 IT 部門からこれらのプロセスを切り離すことで、正確さと自動化が向上します。 このシフトによって、リソース所有者の説明責任と責務の文化が変わります。 この変更を事前に伝えて、リソース所有者がトレーニングを受けられるよう、また、分析情報を用いて適切な決定を行えるようにします。

### ライフサイクル ワークフローの概要

このセクションでは、デプロイを計画する前に知っておく必要があるライフサイクル ワークフローの概念について説明します。

### ライフサイクル ワークフローをデプロイするための前提条件

ライフサイクル ワークフローを展開する前に配置する必要がある組織とテクノロジに関する重要な情報を次に示します。 ライフサイクル ワークフローのデプロイを試みる前に、各項目に対して "はい" と答えられることを確認します。

| 項目 | 説明 | ドキュメント |
| --- | --- | --- |
| インバウンドのプロビジョニング | Workday や SuccessFactors からの HR インバウンド、MIM など、Microsoft Entra で従業員のユーザー アカウントを作成するためのプロセスがあります。 または、Active Directory でユーザー アカウントを作成するプロセスがあり、それらのアカウントが Microsoft Entra ID に対してプロビジョニングされます。 | [Workday から Active Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial)[Workday から Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial)[SuccessFactors から Active Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)[SuccessFactors から Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2)[Microsoft Entra Connect クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)[API 駆動型インバウンド プロビジョニング (パブリック プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app) |
| 属性の同期 | Microsoft Entra ID のアカウントに、employeeHireDate と employeeLeaveDateTime 属性が設定されています。 これらの値は、アカウントが HR システムから作成されるとき、または Microsoft Entra Connect やクラウド同期を使って AD から同期されるときに、設定される場合があります。部署などのスコープを決定するために使われ、データが設定される、またはデータを設定できる、追加の属性があります。 | [ライフサイクル ワークフローの属性を同期する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes) |

### ワークフローの各部分について

ライフサイクル ワークフローのデプロイの計画を始める前に、ワークフローの各部分とライフサイクル ワークフローに関する用語について、理解しておく必要があります。

ドキュメント「[ライフサイクル ワークフローについて](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows)」では、ポータルを使ってワークフローの各部分が説明されています。 ドキュメント「[ライフサイクル ワークフローの開発者向け API リファレンス](https://learn.microsoft.com/ja-jp/graph/api/resources/identitygovernance-workflow)」では、Graph の例を使ってワークフローの各部分が説明されています。

このドキュメントを使って、デプロイする前にワークフローの各部分を理解できます。

### 制限と制約

次の表は、ライフサイクル ワークフローを作成してデプロイするときに注意する必要がある情報です。

| 項目 | 説明 |
| --- | --- |
| ワークフロー | テナントあたり 100 ワークフローの制限 |
| カスタム タスクの数 | ワークフローあたり 25 個に制限 |
| offsetInDays の値の範囲 | -180 日から 180 日の間 |
| ワークフローの実行スケジュール | 既定は 3 時間ごと - 1 から 24 時間の範囲で実行するように設定できます |
| カスタム タスク拡張機能 | 100 個に制限 |
| オンデマンド ユーザーの制限 | 最大 10 ユーザーに対してオンデマンド ワークフローを実行できます |
| 拡張性コールバック タイムアウトの制限 | 最小 3 分 - 最大 5 時間 |

次の追加情報に注意する必要があります。

- リアルタイムの**退職者**と**異動者**のシナリオでスケジュールを有効にすることはできません。 これは仕様です。

### ライフサイクル ワークフロー作成チェックリスト

次の表は、ワークフローを設計および計画するときに使用できる手順の簡単なチェックリストです。

| 手順 | 説明 |
| --- | --- |
| シナリオを決定する | ワークフローで対処するシナリオを決定します |
| 実行条件を決定する | ワークフローを実行するユーザーとタイミングを決定します |
| タスクを確認する | タスクを確認し、追加タスクをワークフローに追加します |
| ワークフローを作成する | 計画して設計した後、ワークフローを作成します。 |
| パイロットを計画する | ワークフローのパイロット、実行、テストを計画します。 |

### シナリオを決定する

ポータルでライフサイクル ワークフローを作成前に、デプロイするシナリオを決定する必要があります。 現在、使用可能なシナリオの一覧については、次の表をご覧ください。 これらはポータルで使用できるテンプレートに基づいており、それぞれに関連付けられているタスクの一覧が示されています。

| シナリオ | 定義済みタスク |
| --- | --- |
| 雇用前の従業員をオンボードする | TAP を生成してメールを送信する |
| 新入社員を受け入れる | ユーザー アカウントを有効にするウェルカム メールを送信するユーザーをグループに追加する |
| 従業員のリアルタイム退職 | ユーザーをすべてのグループから削除するユーザーをすべての Teams から削除するユーザー アカウントを削除する |
| 従業員のオフボーディング準備 | ユーザーを選択したグループから削除するユーザーを選択した Teams から削除する |
| Offboard an employee (従業員をオフボードする) | ユーザー アカウントを無効にするユーザーをすべてのグループから削除するユーザーをすべての Teams から削除する |
| Post-Offboarding of an employee (従業員のオフボーディング後) | ユーザーのすべてのライセンスを削除するユーザーをすべての Teams から削除するユーザー アカウントを削除する |
| リアルタイムの従業員の変更 | カスタム タスク拡張機能を実行する |
| Employee group membership changes (従業員グループ メンバーシップの変更) | ユーザーのアクセス パッケージ割り当ての削除 選択した Teams からユーザーを削除する マネージャーにユーザーの移動を通知するメールを送信する |
| Employee job profile change (従業員ジョブ プロファイルの変更) | マネージャーにユーザーの移動を通知するメールを送信する 選択したグループからユーザーを削除する 選択した Teams からユーザーを削除する ユーザー アクセス パッケージの割り当てを要求する |

[Employee group membership changes (従業員グループ メンバーシップの変更)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#employee-group-membership-changes)

- [Employee job profile change (従業員ジョブ プロファイルの変更)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#employee-job-profile-change)

組み込みテンプレートについて詳しくは、「[ライフサイクル ワークフロー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates)」をご覧ください。

### 実行条件を決定する

シナリオを決定したので、シナリオを適用する組織内のユーザーを確認する必要があります。

実行条件は、ワークフローが実行される**ユーザーとトリガー**のスコープを定義するワークフローの一部です。

スコープは、ワークフローの実行対象を決定します。 これは、条件に基づいてユーザーをフィルター処理するルールによって定義されます。 たとえば、ルール `"rule": "(department eq 'sales')"` は、営業部門のメンバーであるユーザーに対してのみタスクを実行します。

トリガーは、ワークフローが実行されるタイミングを決定します。 これは、オンデマンド (即時) に設定するか、スケジュールに従って実行することができます。 ポータルであらかじめ定義されているテンプレートのほとんどは、トリガー条件が満たされた場合スケジュールに従って実行されます。

#### 属性情報

ワークフローのスコープのルール セクションでは属性が使われます。 次の条件を追加して、タスクを適用する**ユーザー**をさらに絞り込むことができます。

- および
- かつ次の値ではない
- または
- それとも

また、多数のユーザー属性から選ぶこともできます。

[Image: ライフサイクル ワークフロー ルールのスクリーンショット]

ただし、実行条件で使う属性を選ぶ前に、属性にデータが設定されていること、または必要なデータの設定を開始できることを確認する必要があります。

これらの属性のすべてが既定で設定されるわけではないため、HR インバンド クラウド専用プロビジョニング、Microsoft Entra Connect、またはクラウド同期を使うときに、HR 管理者または IT 管理者に確認する必要があります。

#### 時間情報

次に示すのは、ワークフローの設計時に注意する必要があるタイム ゾーンに関する重要な情報です。

- Workday と SAP SF は、常に協定世界時 (UTC) で時刻を送信します。
- 1 つのタイム ゾーンにいる場合は、時間部分を自分に合ったものにハードコーディングすることをお勧めします。 たとえば、新規採用シナリオの場合は午前 5 時、在職最終日シナリオの場合は午後 10 時にします。
- 一時的なアクセス パス (TAP) を使用している場合は、最大有効期間を 24 時間に設定することをお勧めします。 このようにすると、異なるタイムゾーンにいる可能性のある従業員に送信された後で TAP の有効期限が切れないようにすることができます。 詳しくは、「[パスワードレスの認証方法を登録するように Microsoft Entra ID で一時アクセス パスを構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#enable-the-temporary-access-pass-policy)」をご覧ください。

詳しくは、「[ライフサイクル ワークフローの属性を同期する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes)」をご覧ください。

### タスクを確認する

これで、シナリオと、いつ誰が決定したかを決定したので、定義済みのタスクで十分か、または追加のタスクが必要なのかを検討する必要があります。 次の表は、現在ポータルで利用できる定義済みのタスクの一覧です。 次の表を使って、タスクをさらに追加する必要があるかどうかを判断してください。

| タスク | 説明 | 関連するシナリオ |
| --- | --- | --- |
| ユーザーをグループに追加する | ユーザーを選択したグループに追加します | 就職者 - 退職者 - 異動者 |
| ユーザーを選択した Teams に追加する | ユーザーを Teams に追加します | 就職者 - 退職者 - 異動者 |
| ユーザーにライセンスを割り当てる | ユーザーにライセンスを割り当てる | 就職者、異動者 |
| ユーザー アカウントを削除する | Microsoft Entra ID でユーザー アカウントを削除する | 退職者 |
| ユーザー アカウントを無効にする | ディレクトリでユーザー アカウントを無効にします | 就職者 - 退職者 |
| ユーザー アカウントを有効にする | ディレクトリでユーザー アカウントを有効にします | 就職者 - 退職者 |
| TAP を生成してメールを送信する | 一時アクセス パスを生成し、ユーザーのマネージャーにメールで送信します | 就職者 |
| ユーザーのすべてのライセンスを削除する | ユーザーに割り当てられているすべてのライセンスを削除します | 退職者 |
| ユーザーをすべてのグループから削除する | すべての Microsoft Entra グループのメンバーシップからユーザーを削除する | 退職者 |
| すべての Teams からユーザーを削除する | すべての Teams メンバーシップからユーザーを削除します | 退職者 |
| 選択したグループからユーザーを削除する | 選択した Microsoft Entra グループのメンバーシップからユーザーを削除する | 就職者 - 退職者 - 異動者 |
| 選択した Teams からユーザーを削除する | 選択した Teams のメンバーシップからユーザーを削除します | 就職者 - 退職者 - 異動者 |
| カスタム タスク拡張機能を実行する | カスタム タスク拡張機能を実行して、外部システムにコールアウトします | 就職者 - 退職者 - 異動者 |
| ユーザーの最終日後にメールを送信する | 最終在籍日の後でユーザーのマネージャーにオフボーディング メールを送信します | 退職者 |
| ユーザーの最終日より前にメールを送信する | 最終在籍日の前にユーザーのマネージャーにオフボーディング メールを送信します | 退職者 |
| ユーザーの最終日にメールを送信する | 最終在籍日にユーザーのマネージャーにオフボーディング メールを送信します | 退職者 |
| ウェルカム メールを送信する | 新規採用者にウェルカム メールを送信します | 就職者 |
| オンボード リマインダーのメールを送信する | オンボード リマインダー メールをユーザーの上司に送信する | 就職者 |
| ユーザー アクセス パッケージの割り当てを要求する | 選択したアクセス パッケージへのユーザー割り当てを要求する | 就職者、異動者 |
| ユーザーのアクセス パッケージの割り当てを削除する | 選択したアクセス パッケージからユーザー割り当てを削除する | 退職者 - 異動者 |
| ユーザーのすべてのアクセス パッケージの割り当てを削除する | ユーザーのすべてのアクセス パッケージの割り当てを削除します。 daysUntilExpiration が設定されている場合、削除は即時ではなくスケジュールされます。 | 退職者 - 異動者 |
| 選択したライセンスの割り当てをユーザーから削除する | 選択したライセンスの割り当てをユーザーから削除します | 退職者 - 異動者 |
| ユーザーのすべての保留中のアクセス パッケージ割り当て要求をキャンセルする | ユーザーのすべての保留中のアクセス パッケージ割り当て要求をキャンセルする | 退職者 |

タスクについて詳しくは、[ライフサイクル ワークフローのタスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks)に関する記事をご覧ください。

#### グループとチームのタスク

グループまたはチームのタスクを使用している場合は、ワークフローで 1 つまたは複数のグループを指定する必要があります。 次のスクリーンショットで、タスクの黄色の三角形は情報が不足していることを示します。

[Image: 新規採用者の入社プロセスのスクリーンショット。]

タスクを選択すると、グループを追加または削除するためのナビゲーション バーが表示されます。 グループを追加するには、[x 個のグループが選択されました] リンクを選びます。

[Image: グループの追加のスクリーンショット。]

#### カスタム タスク拡張機能

ライフサイクル ワークフローを使うと、就職者、異動者、または退職者のシナリオに基づいてトリガーできるワークフローを作成できます。 ライフサイクル ワークフローには、ユーザーのライフサイクル全体にわたって一般的なシナリオを自動化する組み込みタスクがいくつか用意されていますが、これらの組み込みタスクではいつかは限界に達する可能性があります。 拡張性機能を使うと、カスタム タスク拡張機能の概念を利用して、ライフサイクル ワークフローの一部として外部システムを呼び出すことができます。

カスタム タスク拡張機能とライフサイクル ワークフローとの対話方法に関するシナリオとして、次の 3 つの方法のいずれかが考えられます。

- **ファイア アンド フォーゲット シナリオ** - ロジック アプリを開始したら、ロジック アプリからの想定される応答を待たずに、直ちに次のタスクの実行を続けます。
- **ロジック アプリからの応答を待機するシーケンシャル タスク実行** - ロジック アプリを開始すると、シーケンシャル タスク実行はロジック アプリからの応答を待機します。
- **サード パーティ システムの応答を待機するシーケンシャル タスク実行** - ロジック アプリを開始すると、シーケンシャル タスク実行は、ロジック アプリをトリガーして正常に実行されたかどうかをカスタム タスク拡張機能に通知するサード パーティ システムからの応答を待機します。
- カスタム拡張機能について詳しくは、[ライフサイクル ワークフローの拡張性](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)に関する記事をご覧ください

### ワークフローを作成する

ワークフローを設計して計画したので、ポータルでそれを作成できます。 ワークフローの作成について詳しくは、「[ライフサイクル ワークフローを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/create-lifecycle-workflow)」をご覧ください。

### パイロットを計画する

お客様には、最初は小規模なユーザー グループまたは 1 人のテスト ユーザーでライフサイクル ワークフローのパイロットを行うことをお勧めします。 試行を通じて、必要に応じてプロセスやコミュニケーションを調整することができます。 これは、セキュリティとコンプライアンスの要件を満たすためのユーザーとレビュー担当者の能力を高めるのに役立ちます。

パイロットでは次のようにすることをお勧めします。

- ユーザーの小さなサブセットに結果が適用されるライフサイクル ワークフローから始めます。
- 監査ログを監視し、すべてのイベントが適切に監査されていることを確認します。

詳しくは、「[パイロットのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)」をご覧ください。

##### ワークフローをテストして実行する

ワークフローを作成したら、ワークフローを[オンデマンド](https://learn.microsoft.com/ja-jp/entra/id-governance/on-demand-workflow)で実行してテストする必要があります。

オンデマンド機能を使うと、ライフサイクル ワークフローが意図したとおりに動作しているかどうかをテストして評価できます。

テストが完了したら、ライフサイクル ワークフローを修正するか、さらに広範な配布を準備することができます。

#### 監査ログ

監査ログからさらに情報を取得することもできます。 これらのログには、ポータルの Microsoft Entra ID の [監視] でアクセスできます。 詳しくは、[Microsoft Entra IDでの監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)に関する記事と「[ライフサイクル ワークフローの履歴](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-history)」をご覧ください。

##### ライフサイクル ワークフローの計画の例

| 段階 | 説明 |
| --- | --- |
| シナリオを決定する | 新しいマネージャーにメールを送信する採用前ワークフロー。 |
| 実行条件を決定する | 営業部門の新入社員に対して、employeeHireDate の 2 日前に、ワークフローが実行されます。 |
| タスクを確認する。 | ワークフローでは定義済みのタスクを使います。 追加するその他のタスクはありません。 |
| ポータルでワークフローを作成する | ポータルで新規採用者向けの定義済みテンプレートを使います。 |
| ワークフローを有効にしてテストする | オンデマンド機能を使って、1 人のユーザーでワークフローをテストします。 |
| テスト結果の確認 | テスト結果をレビューし、ライフサイクル ワークフローが意図したとおりに動作していることを確認します。 |
| ワークフローを幅広いユーザーにロールアウトする | 関係者とコミュニケーションを取り、それが稼働中であり、人事担当者が採用マネージャーにメールを送信する必要がなくなったことを知らせます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-access-review"} -->
## アクセス権の管理にレビューを用いる - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-access-review
- Service: entra-id-governance / access-reviews
- Article date: 2026-03-12
- Summary: Microsoft Entra のアクセス レビューを使用して、グループのメンバーシップやアプリケーションへの割り当てとしてのユーザーとゲストのアクセスを管理する方法について説明します。

アクセス レビューを使うと、簡単かつ確実にユーザーまたはゲストに適切なアクセス権を付与できます。 ユーザー自身または意思決定者に対し、アクセス レビューに参加してユーザーのアクセス権を再確認 (証明) するよう求めることができます。 レビュー担当者は、Microsoft Entra ID からの提案に基づいて、各ユーザーの継続的なアクセスのニーズを評価できます。 アクセス レビューが完了したら、変更を加え、不要になったアクセス権をユーザーから削除できます。

注意

この記事では、ユーザーとアプリケーションのアクセス レビューを実施する方法について説明します。 アクセス パッケージ内の複数のリソースに対するアクセス レビューを実施する方法については、「[Microsoft Entra エンタイトルメント管理でのアクセス パッケージのアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-review-access)」を参照してください。 Microsoft Entra ID または Azure リソース ロールへのユーザーまたはサービス プリンシパルのアクセス権を確認する場合は、「[Microsoft Entra Privileged Identity Management でアクセス レビューを開始する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)」を参照してください。

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### ユーザーのアクセスレビューを作成し実施する

まず、次の役割のうち少なくとも1つに割り当てられる必要があります。

- ユーザー管理者
- アイデンティティ ガバナンス管理者
- 特権ロール管理者 (ロール割り当て可能グループのレビューのみ)
- (プレビュー) レビュー対象グループの Microsoft 365 または Microsoft Entra セキュリティ グループ所有者

次に、[ [ID ガバナンス\] ページ](https://portal.azure.com/#blade/Microsoft_AAD_ERM/DashboardBlade/) に移動して、組織のアクセス レビューの準備ができていることを確認します。

1 人または複数のユーザーをアクセス レビューのレビュー担当者として設定できます。

1. Microsoft Entra ID でメンバーがいるグループを選択します。 または、Microsoft Entra ID に接続され、1 人以上のユーザーが割り当てられているアプリケーションを選択します。
2. 各ユーザーが自分自身のアクセスを確認するか、1 人以上のユーザーがすべてのユーザーのアクセスを確認するかを決定します。
3. 前述のロールの 1 つで、[\[Identity Governance\] ページ](https://portal.azure.com/#blade/Microsoft_AAD_ERM/DashboardBlade/)に移動します。
4. アクセス レビューを作成します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
5. アクセス レビューの開始時には、レビュー担当者に入力するよう依頼してください。 既定では、それぞれが、アクセス パネルへのリンクが記載されたメールを Microsoft Entra ID から受け取り、そこで[グループまたはアプリケーションへのアクセスをレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/self-access-review)します。
6. レビュー担当者がまだ入力していない場合は、そのレビュー担当者に通知を送信するよう Microsoft Entra ID を設定できます。 既定では、終了日まであと半分になった時点で、Microsoft Entra ID はすべてのレビュー担当者に自動的に通知が送信されます。
7. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。

### Microsoft Entra アクセス レビューを使ってゲスト アクセスを管理する

Microsoft Entra ID を使うと、組織の境界を越えたコラボレーションを [Microsoft Entra B2B 機能](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)で簡単に実現できます。 [管理者](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)または[それ以外のユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)が他のテナントからゲスト ユーザーを招待できます。 Microsoft アカウントなどのソーシャル ID も、この機能の対象となります。

### ゲストのアクセス権レビューを作成して実行します。

ユーザーのアクセス レビューを作成するために必要なのと同じロールも、ゲストのアクセス レビューを作成する必要があります。 詳細については、「[ユーザー アクセス レビューを作成して実行する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-access-review#create-and-perform-an-access-review-for-users)」をご覧ください。

ゲスト ユーザーのレビューに関して、Microsoft Entra ID はいくつかのシナリオに対応しています。

いずれかを確認できます。

- Microsoft Entra ID 内のグループのメンバーとして 1 人以上のゲストが存在する。
- Microsoft Entra ID に接続済みのアプリケーションに対して 1 人以上のゲスト ユーザーが割り当てられている。

Microsoft 365 グループへのゲスト ユーザー アクセスを確認する場合は、各グループのレビューを個別に作成するか、すべての Microsoft 365 グループでゲスト ユーザーの自動定期的なアクセス レビューを有効にすることができます。 次のビデオでは、ゲストユーザーの連続アクセス レビューの詳細について説明しています。

そのうえで、各ゲストに自分自身のアクセスをレビューしてもらうか、各ゲストのアクセスを 1 人以上のユーザーにレビューしてもらうかを決めてください。

これらの各シナリオについて以降の各セクションで説明します。

#### ゲストに自身のグループ メンバーシップをレビューするよう依頼する

アクセス レビューを使用すると、グループに招待されて追加されていたユーザーが今もアクセス権を必要としていることを確かめることができます。 ゲストに自身のグループ メンバーシップをレビューしてもらうのは簡単です。

1. グループのアクセス レビューを作成するには、ゲスト ユーザーのメンバーだけをレビュー対象とし、メンバーに自身でレビューしてもらうよう選択します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. 各ゲストに自身のメンバーシップをレビューするよう依頼します。 既定では、招待状を受け取った各ゲストには、アクセス レビューへのリンクが記載されたメールが Microsoft Entra ID から届きます。 Microsoft Entra ID のゲスト向けの手順については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/self-access-review)に関するページを参照してください。
3. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。
4. アクセス権の継続が不要であると自分で判断したユーザーに加え、回答が得られなかったユーザーも削除した方がよいでしょう。
5. グループがアクセス管理に使用されていない場合も、招待状が届かなかったことが理由でレビューの対象外となったユーザーを削除した方がよいでしょう。 招待状が届かなかった場合は、招待されたユーザーのメール アドレスに誤りがあった可能性があります。 グループが配布リストとして使用されている場合、ゲストユーザーが連絡先オブジェクトであるため、参加するために選ばれなかった可能性があります。

#### スポンサーにゲストのグループメンバーシップをレビューするよう依頼する

ゲストのグループ メンバーシップが引き続き必要であるかどうかをレビューするよう責任者 (グループの所有者など) に依頼することができます。

1. グループのアクセス レビューを作成するには、ゲスト ユーザー メンバーだけをレビュー対象として選択します。 そのうえで、レビュー担当者を 1 人以上指定します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. レビュー担当者に確認を依頼します。 既定では、それぞれが、アクセス パネルへのリンクが記載されたメールを Microsoft Entra ID から受け取り、そこで[グループまたはアプリケーションへのアクセスをレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/self-access-review)します。
3. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。

注意

外部 ID がテナントにサインインするのをブロックし、30 日後にテナントから外部 ID を削除することができます。 この期間中は、現在のレビューの下にある設定、結果、レビュー担当者または監査ログを表示したり、構成したりすることはできません。 詳細については、「[Microsoft Entra アクセス レビューを使用して外部 ID の無効化および削除を行う](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-external-users#disable-and-delete-external-identities-with-azure-ad-access-reviews)」を参照してください。

#### ゲストに自身のアプリケーション アクセスをレビューするよう依頼する

アクセス レビューを使用すると、特定のアプリケーションに招待されていたユーザーが今もアクセス権を必要としていることを確かめることができます。 アクセスの必要性をゲスト自身にレビューしてもらうのは簡単です。

1. アプリケーションのアクセス レビューを作成するには、ゲストだけをレビュー対象とし、ユーザーに自身のアクセス権をレビューしてもらうよう選択します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. 各ゲストに自身のアプリケーション アクセスをレビューするよう依頼します。 既定では、招待状を受け取った各ゲストには Microsoft Entra ID からメールが届きます。 そのメールには、組織のアクセス パネル内のアクセス レビューへのリンクが記載されています。 Microsoft Entra ID のゲスト向けの手順については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/self-access-review)に関するページを参照してください。
3. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。
4. 継続的なアクセスの必要性を自分で拒否したユーザーに加えて、特に最近招待されなかった場合は、応答しなかったゲスト ユーザーまたは参加を選択していないゲスト ユーザーを削除することもできます。 招待状を受け取っていないようであれば、そのようなユーザーにアプリケーションへのアクセス権はありません。

#### スポンサーにアプリケーションへのゲストのアクセスを確認してもらうように依頼する

アプリケーションの所有者などのスポンサーに、アプリケーションへの継続的なアクセスに対するゲストのニーズを確認するように依頼できます。

1. アプリケーションのアクセス レビューを作成するには、ゲストだけをレビュー対象として選択します。 そのうえで、レビュー担当者としてユーザーを 1 人以上指定します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. レビュー担当者に確認を依頼します。 既定では、それぞれが、アクセス パネルへのリンクが記載されたメールを Microsoft Entra ID から受け取り、そこで[グループまたはアプリケーションへのアクセスをレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/self-access-review)します。
3. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。

#### ゲストに全般的なアクセスの必要性をレビューするよう依頼する

組織によっては、ゲストが自分のグループ メンバーシップを把握していないこともあります。

1. ゲストをメンバーとするセキュリティ グループを Microsoft Entra ID に作成します (適切なグループがまだ存在しない場合)。 たとえば、手動で管理されたゲストのメンバーシップを基にグループを作成します。 または、UserType 属性の値が Guest である Contoso テナントのユーザー用に "Guests of Contoso" などの名前の動的グループを作成してもよいでしょう。 グループのメンバーであるゲスト ユーザーがグループの他のメンバーを表示できる点に注意してください。
2. そのグループのアクセス レビューを作成するには、メンバー自身をレビュー担当者として選択します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
3. 各ゲストに自身のメンバーシップをレビューするよう依頼します。 既定では、招待状を受け取った各ゲストには、組織のアクセス パネル内のアクセス レビューへのリンクが記載されたメールが Microsoft Entra ID から届きます。 Microsoft Entra ID のゲスト向けの手順については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)に関するページを参照してください。
4. レビュー担当者が結果を入力したら、アクセス レビューを停止します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。
5. 拒否されたゲスト、レビューが完了していないゲスト、招待状を受け取っていないゲストについては、ゲスト アクセスを削除します。 レビューの対象として選ばれた連絡先がゲストの中に存在する場合や、ゲストが過去に招待状を受け取っていない場合は、そのアカウントは、Microsoft Entra 管理センターまたは PowerShell を使って無効にしてかまいません。 そのゲストがアクセス権をもう必要としておらず、連絡先でもない場合は、ゲスト ユーザー オブジェクトを削除するために、該当するユーザー オブジェクトを Microsoft Entra 管理センターまたは PowerShell を使ってディレクトリから削除してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-delegate-workflow"} -->
## 委任されたワークフロー管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-delegate-workflow
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: この記事では、ライフサイクル ワークフローを使用してワークフローの管理を委任する方法についてユーザーに通知します。

ワークフローは、作成時に指定されていない限り、既定でライフサイクル ワークフローまたはグローバル管理者ロールを持つユーザーによって管理されます。 ワークフローが成長し、組織のメンバーのニーズを満たすように変化するにつれて、それらを管理できるユーザーを制限する必要もあります。 委任されたワークフロー管理を使用すると、 [管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)を使用してワークフローの管理のスコープを設定できます。 スコープが設定されている場合、特定の管理者には、特定のワークフローを管理するためのアクセス権のみが付与されます。 スコープを使用すると、Microsoft の最小限の特権アクセス ガイドラインに従って、特に必要なものにのみアクセスを許可することで、環境内のセキュリティを強化できます。

次の表は、ライフサイクル ワークフロー管理者ロールと、ライフサイクル ワークフロー機能の観点からスコープ付きワークフロー管理者ロールの違いを示しています。

| 能力 | ライフサイクル ワークフロー管理者 | ワークフロー管理者 |
| --- | --- | --- |
| [ワークフローの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-lifecycle-workflow) | イエス | いいえ |
| [ワークフローの編集](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-properties) | イエス | はい (割り当てられたワークフローのみ) |
| [カスタム タスク拡張機能](https://learn.microsoft.com/ja-jp/entra/id-governance/trigger-custom-task) | イエス | いいえ |
| [ワークフローの削除](https://learn.microsoft.com/ja-jp/entra/id-governance/delete-lifecycle-workflow#delete-a-workflow-by-using-the-microsoft-entra-admin-center) | イエス | はい (割り当てられたワークフローのみ) |
| [復元ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/delete-lifecycle-workflow#view-deleted-workflows-in-the-microsoft-entra-admin-center) | イエス | はい (割り当てられたワークフローのみ) |
| [ワークフロー履歴を表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-history) | イエス | はい (割り当てられたワークフローのみ) |
| [ワークフローをオンデマンドで実行する](https://learn.microsoft.com/ja-jp/entra/id-governance/on-demand-workflow) | イエス | はい (割り当てられたワークフローのみ) |
| [スコープ ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-delegate-workflow#edit-the-administrative-scopes-of-an-existing-workflow-using-the-microsoft-entra-admin-center) | イエス | いいえ |

### [前提条件]

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。 また、テナント内に少なくとも 1 つの管理単位が必要です。 管理単位を作成する手順については、管理 [単位の作成を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-manage#create-an-administrative-unit)参照してください。

### ライフサイクル ワークフロー管理者ロールを管理単位に割り当てる

管理スコープを使用してワークフロー管理を委任するには、まずライフサイクル ワークフロー管理者ロールを管理単位に割り当てる必要があります。 これを行うには、次の手順に従います。

ヒント

次の手順では、特定のユーザーのロールを設定する手順を説明しますが、管理単位内のグループにロールを設定して、スコープ内の管理を複数のユーザーに委任できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. [ユーザー] ページで、管理者スコープを割り当てるユーザーを選択します。
4. ユーザーの概要ページで、[ **割り当てられたロール**] を選択します。
5. [割り当てられたロール] ページで、[ **割り当ての追加]** を選択します。
6. [割り当ての追加] ページで、次を選択します。**ロールの選択**: ライフサイクル ワークフロー管理者**スコープの種類**: 管理単位管理単位 [Image: の追加のスクリーンショット。]
7. [ **選択したスコープ** ] ウィンドウで、ワークフローのスコープを設定する管理単位を選択し、[ **次へ**] を選択します。
8. [ **設定** ] タブでは、割り当てを **有資格** または **アクティブ**として設定できます。

    注

    管理単位のユーザーに対して実行するには、割り当てをアクティブにする必要があります。
9. **保存** を選択します。

### 新しいワークフローに管理スコープを追加する

新しいワークフローに管理スコープを追加するには、次の手順を実行して、ワークフローの作成時に管理スコープを設定します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフ サイクル ワークフロー**&gt;**ワークフローの作成**に移動します。
3. [ **ワークフローの選択** ] ページで、使用するワークフロー テンプレートを選択します。
4. [ **基本** ] タブで、ワークフローの一意の表示名、説明、および管理スコープを入力し、[ **次へ**] を選択します。 [Image: 新しいワークフローの管理者スコープの設定のスクリーンショット。]
5. 実行条件、タスクの設定を完了し、ワークフローを作成します。

注

ワークフローごとに最大 5 つの管理スコープを割り当てることができます。

### Microsoft Entra 管理センターを使用して既存のワークフローの管理スコープを編集する

管理単位に対して管理者のロールが設定されている場合は、その管理単位のスコープ内で割り当てられるワークフローを編集する必要があります。 Microsoft Entra 管理センターを使用して、管理単位のスコープに含まれるワークフローのプロパティを編集するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**のワークフローを参照してください**。
3. [ワークフローの一覧] ページで、管理スコープを編集するワークフローを選択します。
4. ワークフローの概要ページで、[ **管理スコープ**] を選択します。

    ヒント

    [概要] ページで **[管理スコープ** ] カードを選択して、[管理スコープ] ページに移動することもできます。
5. [管理スコープ] ページで、[ **管理スコープの割り当て**] を選択します。
6. [管理スコープ] ウィンドウで、テナント内のすべての管理単位の一覧を表示できます。
7. ワークフローのスコープを設定する管理単位を選択します。
8. **保存** を選択します。

### ライフサイクル ワークフロー管理者ロールをプログラムで管理単位に割り当てる

API を使用してライフサイクル ワークフロー管理者を管理単位スコープに割り当てるには、次の情報が必要です。

- ライフサイクル ワークフロー管理者ロール ID: 59d46f88-662b-457b-bceb-5c3809e5908f
- スコープを割り当てるユーザーのユーザー ID
- 割り当てる管理単位の ID

この情報を使用して、次の API 呼び出しを行うことができます。

投稿 `https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments`

```
{
    "@odata.type": "#microsoft.graph.unifiedRoleAssignment",
    "principalId": "<Object ID of user>",
    "roleDefinitionId": "59d46f88-662b-457b-bceb-5c3809e5908f",
    "directoryScopeId": "/administrativeUnits/<Object ID of administrative unit>"
}
```

### Microsoft Entra 管理センターを使用してワークフローの管理スコープを表示する

ワークフローを管理する場合は、どの管理スコープに該当するかを確認することが重要です。 これにより、特定の各ワークフローを管理できるユーザーをすばやく確認できます。 ワークフローの管理スコープを表示するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**のワークフローを参照してください**。
3. ワークフロー一覧画面で、[管理スコープ割り当て済み] 列で、ワークフローに **割り当てられているスコープ** の数を確認できます。
4. 列の番号を選択すると、そのワークフローに割り当てられているスコープの一覧が表示されます。

### Microsoft Entra 管理センターを使用してワークフローの管理スコープを削除する

管理スコープはいつでもワークフローから削除できます。 ワークフローから管理単位スコープを削除するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**のワークフローを参照してください**。
3. [ワークフロー] ページの一覧で、管理スコープを削除するワークフローを選択します。
4. ワークフローの概要ページで、[プロパティ] を選択 **します**。
5. [プロパティ] で、[ **管理スコープ**] を選択します。
6. [管理スコープ] ウィンドウでは、ワークフローのすべての管理スコープの一覧を表示できます。
7. ワークフローを削除する管理スコープを選択します。
8. **保存** を選択します。

### Microsoft Graph を使用してワークフローの管理スコープを編集する

Microsoft Graph を使用して API を使用してワークフローの管理スコープを編集するには、「 [ワークフローの更新](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-workflow-createnewversion)」を参照してください。

### Microsoft Graph を使用してワークフローの管理スコープを表示する

Microsoft Graph を使用して API 経由でワークフローの管理スコープを表示するには、「 [ワークフローの取得](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-workflow-get)」を参照してください。

### Microsoft Graph を使用してワークフローの管理スコープを削除する

Microsoft Graph を使用して API を使用してワークフローの管理スコープを削除するには、「 [ワークフローの更新](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-workflow-createnewversion)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-guest-access-with-access-reviews"} -->
## アクセス レビューを使用してゲスト アクセスを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-guest-access-with-access-reviews
- Service: entra-id-governance / access-reviews
- Article date: 2026-03-12
- Summary: Microsoft Entra アクセス レビューを使用し、ゲスト ユーザーをグループのメンバーとして、またはアプリケーションに割り当てられたユーザーとして管理します。

アクセス レビューを利用すると、[Microsoft Entra B2B 機能](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を使用して、組織の境界を越えたコラボレーションを簡単に実現できます。 [管理者](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)または[それ以外のユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)が他のテナントからゲスト ユーザーを招待できます。 Microsoft アカウントなどのソーシャル ID も、この機能の対象となります。

ゲスト ユーザーに適切なアクセス権を確保することも簡単にできます。 ゲスト自身または意思決定者に対し、アクセス レビューに参加してゲストのアクセス権を再確認 (証明) するよう求めることができます。 レビュー担当者は、Microsoft Entra ID からの提案に基づいて、各ユーザーの継続的なアクセスのニーズを評価できます。 アクセス レビューが完了したら、変更を加え、不要になったアクセス権をゲストから削除することができます。

注

このドキュメントでは、ゲスト ユーザーのアクセス レビューについて重点的に説明します。 ゲストに限らず全ユーザーのアクセス権をレビューする必要がある場合は、[アクセス レビューでユーザー アクセスを管理する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-user-access-with-access-reviews)に関するページを参照してください。 グローバル管理者などの管理者ロールのユーザーのメンバーシップを確認する場合は、[Microsoft Entra Privileged Identity Management でアクセス レビューを開始する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)に関するページを参照してください。

### 前提条件

- Microsoft Entra ID P2 または Microsoft Entra ID Governance

詳細については、「 [ライセンス要件](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview#license-requirements)」を参照してください。

### ゲストのアクセス権レビューを作成して実行します。

まず、次の役割のうち少なくとも1つに割り当てられる必要があります。

- ユーザー管理者
- (プレビュー) レビュー対象グループの Microsoft 365 または Microsoft Entra セキュリティ グループ所有者

次に、[\[Identity Governance\] ページ](https://portal.azure.com/#blade/Microsoft_AAD_ERM/DashboardBlade/)に移動し、組織がアクセス レビューを使用できる状態であることを確認します。

ゲスト ユーザーのレビューに関して、Microsoft Entra ID はいくつかのシナリオに対応しています。

いずれかを確認できます。

- Microsoft Entra ID 内のグループのメンバーとして 1 人以上のゲストが存在する。
- Microsoft Entra ID に接続済みのアプリケーションに対して 1 人以上のゲスト ユーザーが割り当てられている。

Microsoft 365 グループへのゲストユーザーのアクセスを確認する場合は、各グループに対して個別にレビューを作成するか、すべての Microsoft 365 グループのゲスト ユーザーに対する連続アクセス レビューを自動で有効にすることができます。 次のビデオでは、ゲストユーザーの連続アクセス レビューの詳細について説明しています。

そのうえで、各ゲストに自分自身のアクセスをレビューしてもらうか、各ゲストのアクセスを 1 人以上のユーザーにレビューしてもらうかを決めてください。

これらの各シナリオについて以降の各セクションで説明します。

#### ゲストに自身のグループ メンバーシップをレビューするよう依頼する

アクセス レビューを使用すると、グループに招待されて追加されていたユーザーが今もアクセス権を必要としていることを確かめることができます。 ゲストに自身のグループ メンバーシップをレビューしてもらうのは簡単です。

1. グループのアクセス レビューを作成するには、ゲスト ユーザーのメンバーだけをレビュー対象とし、メンバーに自身でレビューしてもらうよう選択します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. 各ゲストに自身のメンバーシップをレビューするよう依頼します。 既定では、招待状を受け取った各ゲストには、アクセス レビューへのリンクが記載されたメールが Microsoft Entra ID から届きます。 Microsoft Entra ID のゲスト向けの手順については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)に関するページを参照してください。
3. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。
4. アクセス権の継続が不要であると自分で判断したユーザーに加え、回答が得られなかったユーザーも削除した方がよいでしょう。 回答がないユーザーはもうメールを受け取っていないと考えられます。
5. グループがアクセス管理に使用されていない場合も、招待状が届かなかったことが理由でレビューの対象外となったユーザーを削除した方がよいでしょう。 招待状が届かなかった場合は、招待されたユーザーのメール アドレスに誤りがあった可能性があります。 グループが配布リストとして使用されている場合、ゲストユーザーが連絡先オブジェクトであるため、参加するために選ばれなかった可能性があります。

#### 承認されているユーザーにゲストのグループ メンバーシップをレビューするよう依頼する

ゲストのグループ メンバーシップが引き続き必要であるかどうかをレビューするよう、承認されているユーザー (グループの所有者など) に依頼できます。

1. グループのアクセス レビューを作成するには、ゲスト ユーザー メンバーだけをレビュー対象として選択します。 そのうえで、レビュー担当者を 1 人以上指定します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. レビュー担当者に確認を依頼します。 既定では、それぞれが、アクセス パネルへのリンクが記載されたメールを Microsoft Entra ID から受け取り、そこで[グループまたはアプリケーションへのアクセスをレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)します。
3. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。

#### ゲストに自身のアプリケーション アクセスをレビューするよう依頼する

アクセス レビューを使用すると、特定のアプリケーションに招待されていたユーザーが今もアクセス権を必要としていることを確かめることができます。 アクセスの必要性をゲスト自身にレビューしてもらうのは簡単です。

1. アプリケーションのアクセス レビューを作成するには、ゲストだけをレビュー対象とし、ユーザーに自身のアクセス権をレビューしてもらうよう選択します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. 各ゲストに自身のアプリケーション アクセスをレビューするよう依頼します。 既定では、招待状を受け取った各ゲストには Microsoft Entra ID からメールが届きます。 そのメールには、組織のアクセス パネル内のアクセス レビューへのリンクが記載されています。 Microsoft Entra ID のゲスト向けの手順については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)に関するページを参照してください。
3. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。
4. アクセス権の継続が不要であると自分で判断したユーザーに加え、回答が得られなかったゲスト ユーザーも削除した方がよいでしょう。 回答がないユーザーはもうメールを受け取っていないと考えられます。 参加するよう選ばれなかったゲストユーザーも、特に最近招待されていない場合、削除することをお勧めします。 招待状を受け取っていないようであれば、そのようなユーザーにアプリケーションへのアクセス権はありません。

#### 承認されているユーザーにアプリケーションへのゲスト アクセスをレビューするよう依頼する

承認されたユーザー (アプリケーションの所有者など) に、アプリケーションへの継続的なアクセスに対するゲストの必要性を確認するように依頼できます。

1. アプリケーションのアクセス レビューを作成するには、ゲストだけをレビュー対象として選択します。 そのうえで、レビュー担当者としてユーザーを 1 人以上指定します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. レビュー担当者に確認を依頼します。 既定では、それぞれが、アクセス パネルへのリンクが記載されたメールを Microsoft Entra ID から受け取り、そこで[グループまたはアプリケーションへのアクセスをレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)します。
3. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。

#### ゲストに全般的なアクセスの必要性をレビューするよう依頼する

組織によっては、ゲストが自分のグループ メンバーシップを把握していないこともあります。

注

以前のバージョンのポータルでは、UserType が Guest であるユーザーの管理アクセスが認められていませんでした。 ディレクトリの管理者が PowerShell を使ってゲストの UserType の値を Member に変更している場合があります。 過去にそのような変更がご利用のディレクトリで行われていた場合、前述したクエリでは、以前管理アクセス権を持っていたゲスト ユーザーが対象から漏れる場合があります。 その場合は、ゲストの UserType を変更するか、該当するゲストをグループ メンバーシップに手動で追加する必要があります。

1. ゲストをメンバーとするセキュリティ グループを Microsoft Entra ID に作成します (適切なグループがまだ存在しない場合)。 たとえば、手動で管理されたゲストのメンバーシップを基にグループを作成します。 または、UserType 属性の値が Guest である Contoso テナントのユーザー用に "Guests of Contoso" などの名前の動的グループを作成してもよいでしょう。 効率を高めるために、そのグループが主にゲストで構成されるようにしてください。メンバー ユーザーはレビューが不要なので、メンバー ユーザーを含むグループを選択しないようにしてください。 また、グループのメンバーであるゲスト ユーザーがグループの他のメンバーを表示できる点に注意してください。

注

Microsoft Entra グループのメンバーであるゲスト ユーザーは、自分のプロファイル&gt;グループを表示するときに、同じ**グループの他の**メンバーを表示できます。 この動作は、アクセス レビューとは無関係です。 組織でゲスト ユーザーが他のゲスト アカウントを表示できないようにする場合は、Microsoft Entra ID で **ゲスト ユーザーのアクセス制限を** 更新できます。 たとえば、ゲストが自分のプロファイル情報のみを表示するように制限できます。 この設定を構成するには、 **Entra ID &gt; 外部 ID &gt; 外部コラボレーション設定**に移動し、それに応じて **ゲスト ユーザーのアクセス制限を** 調整します。

1. そのグループのアクセス レビューを作成するには、メンバー自身をレビュー担当者として選択します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
2. 各ゲストに自身のメンバーシップをレビューするよう依頼します。 既定では、招待状を受け取った各ゲストには、組織のアクセス パネル内のアクセス レビューへのリンクが記載されたメールが Microsoft Entra ID から届きます。 Microsoft Entra ID のゲスト向けの手順については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)に関するページを参照してください。 招待を承諾しなかったゲストは、レビュー結果に "通知されていません" と表示されます。
3. レビュー担当者が結果を入力したら、アクセス レビューを停止します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。
4. **[Select Team + Groups] (チームとグループの選択)** のアクセス レビューを構成する際に、アクセス レビューの一環としてゲスト ユーザーの Microsoft Entra B2B アカウントを自動的に削除できます。 このオプションは、**ゲスト ユーザーを有するすべての Microsoft 365 グループ**で使用できません。

[Image: アクセス レビューを作成するページを示すスクリーンショット。]

これを行うには、**[リソースへの結果の自動適用]** を選択します。これにより、リソースからユーザーが自動的に削除されます。 **レビュー担当者が応答しない場合** は 、[ **アクセスの削除**] に設定し、 **拒否されたゲスト ユーザーに適用するアクション** も **[30 日間サインインをブロックする] に設定し、テナントからユーザーを削除する**必要があります。

これにより、ゲスト ユーザー アカウントへのサインインがすぐにブロックされ、30 日後に Microsoft Entra B2B アカウントが自動的に削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-sponsored-guests-my-access"} -->
## My Accountでスポンサードゲストを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-sponsored-guests-my-access
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-09-30
- Summary: スポンサーがマイ アカウントでMicrosoft Entraゲスト ユーザーのスポンサーシップを確認、延長、終了、確立する方法について説明します。

Important

マイ アカウントでのスポンサー付きゲスト管理は現在プレビュー段階です。 プレビュー機能は、サービス レベル アグリーメントなしで提供され、運用環境のワークロードには推奨されません。 一部の機能はサポートされていないか、機能が限られている可能性があります。

スポンサーは、Microsoft Entraゲスト ユーザーのライフサイクルと組織リソースへの継続的なアクセスを管理するのに役立ちます。 スポンサーは通常、ゲストが組織と連携している理由と、そのアクセスがまだ必要かどうかについてよく理解しています。

マイ アカウントでは、スポンサーしているゲストを確認したり、アクティビティとアクセス情報を使用して継続的なニーズを評価したり、スポンサーシップを延長したり、スポンサーシップを終了したり、スポンサーのいない対象条件を満たすゲストをスポンサーしたりすることができます。 テナントのゲスト ライフサイクル ポリシーによって、スポンサーシップを延長する必要がある頻度、適用される通知と猶予期間、およびポリシー要件が満たされない場合にどのような強制措置が行われるかが決まります。

### 主な機能

- 現在スポンサーになっているすべてのゲストを確認します。
- 継続的なアクセスのために延長が必要なゲストに優先順位を付けます。
- ゲストの詳細、アクティビティ シグナル、スポンサーを表示します。
- あなたがスポンサーするゲストのアクセスを延長します。
- ビジネス関係で必要がなくなったら、スポンサーシップを終了します。
- 対象となるスポンサー未設定のゲストを検索してスポンサーします。

### 始める前の準備

#### Requirements

- 組織は、Microsoft Entra ID ガバナンス内のライフサイクル ワークフローでゲスト ライフサイクル ポリシーを構成する必要があります。
- ゲスト スポンサーとして機能することが許可されている職場または学校アカウントでサインインしている必要があります。

#### ポリシー駆動型の動作を理解する

My Accountはスポンサー向けのエクスペリエンスです。 管理者は、ゲスト アクセスを拡張する必要がある頻度、アクティビティ要件、最小スポンサー数、通知、猶予期間、強制アクションなど、ゲスト ライフサイクル ポリシーを構成します。 マイ アカウントでこれらのゲスト ポリシー設定を変更することはできません。

スポンサーシップ記録を延長すると、ゲストが引き続きビジネス上のアクセスを必要としているという証明が記録されます。 継続的なアクセスを保証したり、ゲストが他のすべてのライフサイクル ポリシー規則を満たしていることを確認したりすることはありません。

### スポンサー付きゲストを表示

1. [マイ アカウント](https://myaccount.microsoft.com)にサインインします。
2. 左側のナビゲーションで、**招待されたゲスト**を選択します。
3. 表示名またはメール アドレスでゲストを検索するには、使用可能なビューと検索を使用します。

このページには、あなたがスポンサーしているゲストが表示されます。 情報には、ゲストの名前、メール アドレス、認証状態、最終サインインなどが含まれます。

目的に合ったアクションを選択してください。 決定を下す前にゲストをレビューし、ゲストがまだアクセスが必要な場合はスポンサーシップを延長し、その必要性を確認できなくなった場合はスポンサーシップを終了するか、現在スポンサーがいない対象ゲストをスポンサーします。

#### ゲストを評価する

1. ゲストのその他メニューを開き、**詳細を表示** を選択します。
2. 利用可能なプロフィール情報とライフサイクル情報（勤務地、招待日、最終サインイン、現在のスポンサー、最終証明日など）を確認します。
3. 割り当てられたアクセス パッケージと、それに含まれるリソースを確認します（利用可能な場合）。
4. ゲストのアクセスを延長するか、スポンサーシップを終了するかを決めてください。

### ゲストのスポンサーシップを延長する

ゲストが継続中の業務関係のために引き続きアクセス権を必要としていることを確認した場合は、このアクションを使用します。

1. **スポンサー対象のゲスト**ページで、ゲストを探します。
2. ゲストのオーバーフローメニューまたはゲストの詳細ページを開きます。
3. **スポンサー期間を延長** を選択します。
4. [マイ アカウント] に更新されたアテステーションの状態または日付が表示されていることを確認します。

**スポンサーシップを延長**が利用できない場合、ゲストがまだ延長を必要としていない、テナント ポリシーでその操作が許可されていない、またはゲストの現在の状態によって利用可能なオプションが制限されている可能性があります。 ステータス情報については、ゲストの詳細を確認します。 理由が不明な場合は、組織の Microsoft Entra 管理者または ID ガバナンス サポート チームにお問い合わせください。

### スポンサーシップを終了する

ゲストとの業務上の関係について責任を負わなくなった場合、またはそのゲストに引き続きアクセスが必要かどうかを確認できない場合は、スポンサー設定を終了してください。

1. **スポンサー対象のゲスト**ページで、ゲストを探します。
2. ゲストのオーバーフローメニューまたはゲストの詳細ページを開きます。
3. **スポンサーシップの終了** を選択します。
4. 確認ダイアログに表示された影響を確認してから、確定してください。

スポンサー提供を終了すると、スポンサー関係のみが削除されます。 ゲストまたはそのアクセス権はすぐには削除されません。 他のスポンサーが残っている場合、彼らの関係は続けられます。 その操作の結果、ゲストのスポンサー数が必要最小数を下回る場合、組織のゲスト ライフサイクル ポリシーは、その設定に応じて、アカウントの無効化または削除を開始する可能性があります。 個々のアクセス パッケージの割り当ては、該当するポリシーまたは別のガバナンス プロセスによって削除されない限り残ります。

### 無責任なゲストをスポンサーする

1. [ **スポンサー付きゲスト** ] ページで、[ **ゲストのスポンサー**] を選択します。
2. 名前または電子メール アドレスでゲストを検索します。
3. 結果から適切なゲストを選択します。
4. **[スポンサー]** を選択します。
5. スポンサー付きゲスト リストにゲストが表示されることを確認します。

検索結果には、組織によって検出とスポンサー設定が許可されているゲストのみが表示されます。 ゲストが表示されない場合は、不適格であるか、既に必要なスポンサーシップを持っているか、許可された検索範囲を外れる可能性があります。

アクションが完了したら、[ **スポンサー付きゲスト** ] ページを更新し、予想される変更を確認します。

- 拡張機能の場合は、構成証明の状態または日付を確認します。
- スポンサープランを終了したら、スポンサーとして登録されなくなったことを確認します。
- ゲストをスポンサーした後、スポンサー付きゲスト リストにゲストが表示されることを確認します。

変更が表示されない場合は、少し待ってからページをもう一度更新してから、組織のサポート チームに問い合わせてください。

### ゲスト ライフサイクル ポリシーがアクションに与える影響

| スポンサーのアクションまたは状態 | 考えられるポリシーの動作 |
| --- | --- |
| **スポンサーシップの延長が完了しました** | 最終構成証明日が更新されます。 アクティビティやアクセス パッケージの要件など、その他のポリシー ルールは個別に評価されます。 |
| **スポンサー期間を延長する** | ポリシーは、スポンサーまたはゲストに通知し、ゲスト アカウントを無効または削除する前に構成された猶予期間を開始できます。 |
| **スポンサー シップは終了しましたが、他のスポンサーは引き続き** | スポンサー関係が削除されます。 ゲストは、他の割り当てられたスポンサーによってスポンサーされたままです。 |
| **ゲストが必要なスポンサーより少ない** | ゲストは、最低限のスポンサー要件に準拠しなくなり、組織のポリシーに従って無効または削除される可能性があります。 |

### 推奨プラクティス

- マイ アカウントまたは組織から延長通知が送信されたら、ゲストをすぐに確認してください。
- 最後のサインイン、パッケージの割り当てへのアクセス、ビジネス関係に関する知識を一緒に使用します。 1 つのシグナルだけに依存しないでください。
- 継続的なニーズを証明するのに十分なコンテキストがなくなった場合は、スポンサープランを終了します。
- 別の従業員がリレーションシップを所有している場合は、スポンサー プランの対象範囲を調整してから終了してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-user-access-with-access-reviews"} -->
## アクセス レビューを使用してユーザー アクセスを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-user-access-with-access-reviews
- Service: entra-id-governance / access-reviews
- Article date: 2026-03-12
- Summary: Microsoft Entra のアクセス レビューを使用して、グループのメンバーシップやアプリケーションへの割り当てとしてのユーザーのアクセスを管理する方法について説明します

Microsoft Entra を使うと、簡単かつ確実にユーザーに適切なアクセス権を付与できます。 ユーザー自身または意思決定者にアクセス レビューへの参加を依頼し、ユーザーのアクセスを再認定または証明することができます。 レビュー担当者は、Microsoft Entra からの提案に基づいて、各ユーザーの継続的なアクセスのニーズを評価できます。 アクセス レビューが完了したら、変更を加え、不要になったアクセス権をユーザーから削除できます。

注意

すべての種類のユーザーのアクセス権を確認する必要はなく、ゲスト ユーザーのアクセス権のみを確認する場合は、[アクセス レビューによるゲスト ユーザー アクセスの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-guest-access-with-access-reviews)に関するページをご覧ください。 グローバル管理者などの管理者ロールのユーザーのメンバーシップを確認する場合は、[Microsoft Entra Privileged Identity Management でのアクセス レビューの開始](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)に関するページを参照してください。

### 前提条件

- Microsoft Entra ID P2 または Microsoft Entra ID Governance

詳細については、「[License requirements ライセンスの要件](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview#license-requirements)」を参照してください。

アプリケーションへのアクセスを確認する場合は、レビューを作成する前に、[アプリケーションへのユーザーアクセスのアクセス レビューを準備](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)して、アプリケーションが Microsoft Entra ID と確実に統合されるようにする方法に関する記事を参照してください。

### アクセス レビューの作成と実行

1 人または複数のユーザーをアクセス レビューのレビュー担当者として設定できます。

1. Microsoft Entra ID でメンバーがいるグループを選択します。 または、Microsoft Entra ID に接続され、1 人以上のユーザーが割り当てられているアプリケーションを選択します。
2. 各ユーザーに自分のアクセス権をレビューしてもらうか、1 人以上のユーザーにそのグループまたはアプリケーションのすべてのユーザーのアクセス権をレビューさせるかを決定します。
3. 少なくともユーザー管理者、または (プレビュー) レビュー対象の Microsoft 365 グループまたは Microsoft Entra セキュリティ グループの所有者として、[\[Identity Governance\] ページ](https://portal.azure.com/#blade/Microsoft_AAD_ERM/DashboardBlade/)に移動します。
4. アクセス レビューを作成します。 詳細については、[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するページをご覧ください。
5. アクセス レビューの開始時には、レビュー担当者に入力するよう依頼してください。 既定では、それぞれが、アクセス パネルへのリンクが記載されたメールを Microsoft Entra ID から受け取り、そこで[グループまたはアプリケーションへのアクセスをレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)します。
6. 校閲者が入力を行っていない場合は、Microsoft Entra ID を構成してリマインダーを送信できます。 既定では、終了日まであと半分になった時点で、Microsoft Entra ID はまだ応答していないレビュー担当者に自動的に通知が送信されます。
7. レビュー担当者が入力したら、アクセス レビューを停止し、変更を適用します。 詳細については、[グループまたはアプリケーションのアクセス レビューの完了](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)に関するページをご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-workflow-custom-security-attribute"} -->
## カスタム セキュリティ属性を使用してワークフローを範囲設定する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-custom-security-attribute
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: カスタム セキュリティ属性を使用して、ライフサイクル ワークフローを使用してワークフローのスコープを構成する方法について説明します。

ライフサイクル ワークフローを使用して作成されたワークフローは、ユーザー用に構成されたカスタム セキュリティ属性を含む属性に基づいて範囲設定できます。 ユーザーの機密データを含むテナント用に構成された既存のカスタム セキュリティ属性を使用して、ワークフローを実行するユーザーのセットをさらに制御できます。 カスタム セキュリティ属性とそのユース ケースの詳細については、「[Microsoft Entra ID のカスタム セキュリティ属性とは](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)」を参照してください。

### 前提条件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

カスタム セキュリティ属性を使用してワークフローを範囲設定するには、テナントでカスタム セキュリティ属性が設定され、その定義が作成されている必要があります。 一連のカスタム セキュリティ属性を追加し、その定義を設定する方法については、「[Microsoft Entra ID でカスタム セキュリティ属性の定義を追加または非アクティブ化する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)」を参照してください。 カスタム セット属性を作成し、その定義を設定したら、この属性をユーザーにも割り当てる必要があります。 カスタム セキュリティ属性をユーザーに割り当てるためのガイドについては、「[カスタム セキュリティ属性のユーザーへの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes#assign-custom-security-attributes-to-a-user)」を参照してください。

注記

[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-custom-security-attribute#prerequisites)ロールを使って、カスタム セキュリティ属性の作成、定義、割り当ての[前提条件](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)となる手順を実行する必要があります。 ライフサイクル ワークフロー管理者ロールだけでは、カスタム セキュリティ属性を作成、更新、または割り当てることはできません。

### Microsoft Entra 管理センターを使用して、ワークフローのスコープにカスタム セキュリティ属性を追加します。

ワークフローは、カスタム セキュリティ属性をスコープとして使用して作成または編集して含めることができます。 次の手順では、既存のワークフローを編集して、カスタム セキュリティ属性をスコープとして使用する方法を説明します。 ワークフローを最初から作成し、カスタム セキュリティ属性を使用してワークフローを範囲設定する方法については、「[ライフサイクル ワークフローを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/create-lifecycle-workflow)」を参照してください。 ワークフローを編集してカスタム セキュリティ属性をそのスコープに含めるには、次の手順を実行します。

1. 少なくとも[ライフサイクル ワークフロー管理者](https://entra.microsoft.com)と[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)にサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**Workflows** に移動します。
3. [ワークフロー] ページで、カスタム セキュリティ属性をスコープの一部として使用するワークフローを選択します。
4. 特定のワークフロー ページで、**[実行条件]** を選択します。
5. [実行条件] ページで、**[スコープの詳細]** を選択します。
6. スコープの詳細ページで **[式の追加]** を選択し、ドロップダウン リストからカスタム セキュリティ属性を検索し、その値を設定します。 [Image: スコープ画面のカスタム セキュリティ属性一覧のスクリーンショット。]

    注記

    非アクティブ化されたカスタム セキュリティ属性は、この一覧には表示されません。
7. カスタム セキュリティ属性の値を設定したら、**[保存]** を選択します。

### Microsoft Graph を使用して、ワークフローのスコープにカスタム セキュリティ属性を追加します。

ワークフローのスコープにカスタム セキュリティ属性を追加すると、実行条件が更新されるため、ワークフローの新しいバージョンを作成していることになります。 Microsoft Graph を使用して API でワークフローの新しいバージョンを作成するには、「[ワークフロー: createNewVersion](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-workflow-createnewversion)」を参照してください。

### ワークフローのスコープとして使用されるカスタム セキュリティ属性を表示する

カスタム セキュリティ属性を使用してワークフローのスコープを設定すると、ワークフロー監査ログ内でこの情報を表示できます。 これらの詳細を表示するには、次の手順を実行します。

1. 少なくとも[ライフサイクル ワークフロー管理者](https://entra.microsoft.com)と[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)にサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**Workflows** に移動します。
3. ワークフロー ページで **[監査ログ]** を選択します。

    ヒント

    ワークフローのカスタム セキュリティ属性情報は、特定のワークフローのバージョン ページからも、適切なアクセス許可で表示できます。
4. 作成時にワークフローのスコープ設定にカスタム セキュリティ属性が使用されたイベントを選択するか、更新されたワークフローに追加してから、[ **変更されたプロパティ**] を選択します。
5. バージョン情報ページの **[構成]** の下に、カスタム セキュリティ属性がルールとして表示されます。 [Image: スコープとしてのカスタム セキュリティ属性のスクリーンショット。]
6. 割り当てられたロールによって、使用されているカスタム セキュリティ属性の詳細を確認できるかどうかが決まります。 [属性割り当て管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)または[属性割り当て閲覧者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader)なしでカスタムセキュリティ属性情報を表示しようとすると、情報は非表示になります。 [Image: 非表示の属性情報のスクリーンショット。]

注記

カスタム セキュリティ属性が非表示になることの詳細については、「[プロパティ一覧にカスタム セキュリティ属性が表示されない理由](https://learn.microsoft.com/ja-jp/entra/id-governance/workflows-faqs#why-cant-i-see-any-custom-security-attributes-in-the-property-list)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-workflow-insights"} -->
## Workflow Insights を確認する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-insights
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: Microsoft Entra テナント内の Workflow Insights を確認する方法について説明します。

Workflow Insights を使うと、環境内のワークフローの実行をすばやく表示できます。 Workflow Insights を使うと、次のような情報を表示できます。

- 環境内で実行された、成功したすべてのワークフロー、処理されたユーザー、成功したタスクに関する数値の概要。
- 7 日、14 日、または 30 日のいずれかで定義した過去の期間の上位のワークフロー。
- 7 日、14 日、または 30 日のいずれかで定義した過去の期間の上位のタスク。
- 7 日、14 日、または 30 日のいずれかで定義した過去の期間のカテゴリ別ワークフロー数。

詳細については、「 [Workflow Insights](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-insights)」を参照してください。

### 前提条件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### Microsoft Entra 管理センターを使って Workflow Insights を確認する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**Overview** に移動します。
3. 概要ページで、[ **Workflow Insights**] を選択します。
4. [Workflow Insights] ページでは、環境全体のワークフロー情報を表示できます。
5. さらに詳しく調べる情報が見つかると、 **フィルター** オプションを選択し、情報を表示する期間を選択できます。
6. 上位のワークフローとタスクについて、期間でフィルター処理できるだけでなく、アクティビティに基づいてフィルター処理することもできます。

    [Image: ワークフロー分析情報で時間を選択するスクリーンショット。]
7. アクティビティ フィルターを使用すると、[合計処理済み]、[**成功**したワークフローのみ]、または [**失敗**したワークフローのみ] を選択して、上位の**処理**済みワークフローまたはタスクを表示できます。
8. [ **カテゴリ別のワークフロー実行** ] で、カテゴリ別にワークフローをフィルター処理できます。 フィルター処理すると、 **ワークフローの合計処理**率、 **カテゴリ別の成功した** ワークフロー、またはカテゴリ別 **の失敗したワークフローの割合を** 確認できます。

    [Image: カテゴリ分析情報別のワークフローのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-workflow-on-premises"} -->
## ワークフローを使用して Active Directory Domain Services から同期されたユーザーを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-on-premises
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローを使用して Active Directory Domain Services (AD DS) から同期されたユーザーに対して実行されるユーザー アカウント関連のタスクを編集する方法についてのハウツー記事。

ライフサイクル ワークフローによって作成されたワークフローを使用して、Active Directory Domain Services (AD DS) から同期されたユーザーのライフサイクルを管理できます。 同期された AD DS のサポートにより、ワークフロー タスクを使用して、同期されたユーザーを有効化、無効化、削除することができます。 この記事では、AD DS から同期されたユーザーに対してユーザー アカウント タスクを実行できるようにする手順について説明します。

### 前提条件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

ほとんどのライフサイクル ワークフロー タスクでは、追加構成なしで Active Directory Domain Services から同期されたユーザーを管理できますが、有効化、無効化、削除などの特定のタスクにはいくらかの追加の構成が必要です。 これらの前提条件の設定方法の詳細については、「[ユーザー アカウントのタスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-on-premises#user-account-tasks)」をご覧ください。

### Microsoft Entra 管理センターを使用して Active Directory Domain Services から同期されたユーザーを管理するための、ユーザー アカウント タスクを構成します。

ワークフロー内のアカウント関連タスクは、すばやく編集して、Active Directory Domain Services から同期されたユーザーに適用できます。 Microsoft Entra 管理センターを使用してタスクを編集するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**Workflows** に移動します。
3. 内部でタスクの編集を行いたいワークフローを選択します。
4. ワークフローの画面で、**[タスク]** を選びます。
5. [タスク] 画面で、Active Directory Domain Services から同期されたユーザーに対して実行する既存のタスクを選択するか、**[タスクの追加]** を選択して新しいタスクを作成します。
6. 個々のタスクの画面で、同期された Active Directory Domain Services ユーザーに対する実行に対応するチェック ボックスをオンにします。 次の図は、ユーザー アカウントの削除タスクに対して有効になっている状態を示しています。 [Image: アカウントを削除するためのオンプレミス フラグの設定のスクリーンショット。]
7. **[保存]** を選択します。

### Microsoft Graph を使用して Active Directory Domain Services から同期されたユーザーと互換性のあるユーザー アカウント タスクを編集する

Microsoft Graph を使用して API 経由で Active Directory Domain Services から同期されたユーザーと互換性のあるユーザー タスクを管理するには、「[組み込みのライフサイクル ワークフロー タスクの引数を構成する](https://learn.microsoft.com/ja-jp/graph/identitygovernance-lifecycleworkflows-task-arguments)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-workflow-properties"} -->
## ワークフローのプロパティを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-properties
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: この記事では、ライフサイクル ワークフローを使用してワークフローのプロパティを編集する方法について説明します。

ワークフローの管理は、次の 2 つの方法のいずれかで実行できます。

- ワークフローの新しいバージョンを作成せずにワークフローの基本プロパティを更新する
- 更新されたワークフローの新しいバージョンの作成

新しいワークフローを作成せずに、次の基本情報を更新できます。

- 表示名
- 説明
- [管理単位のスコープ](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-delegate-workflow)
- 有効かどうか
- ワークフロー スケジュールが有効かどうか
- タスク名
- タスクの説明

他のパラメーターを変更する場合は、ワークフロー バージョンの管理に関する記事で説明されているように、新しい [バージョンを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-tasks) 必要があります。

Microsoft Entra 管理センターを使用して実行すると、新しいバージョンが自動的に作成されます。 Microsoft Graph を使用して完了した場合は、ワークフローの新しいバージョンを手動で作成する必要があります。 詳細については、「 Microsoft Graph を使用してワークフローのプロパティを編集する」を参照してください。

### Microsoft Entra 管理センターを使用してワークフローのプロパティを編集する

Microsoft Entra 管理センターを使用してワークフローのプロパティを編集するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**ワークフローにアクセスする**。
3. 現在のすべてのワークフローの一覧が表示されます。 編集するワークフローを選択します。

    [Image: ワークフローリストのスクリーンショット。]
4. 表示名、説明、または管理単位のスコープを変更するには、[プロパティ] を選択 **します**。

    [Image: 基本プロパティ画面のスクリーンショット。]
5. 必要なプロパティを更新します。

注

表示名を他の既存のワークフローと同じにすることはできません。 独自の一意の名前を持っている必要があります。

1. **[保存]** を選択します。

### Microsoft Graph を使用してワークフローのプロパティを編集する

Microsoft Graph を使用して API を使用してワークフローを更新するには、「[ワークフローの更新](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-workflow-update)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/manage-workflow-tasks"} -->
## ワークフローのバージョンを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-tasks
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: この記事では、ライフサイクル ワークフローを使用してワークフロー バージョンを管理する方法について説明します。

ライフサイクル ワークフローで作成されたワークフローは、組織のニーズに合わせて拡張および変更できます。 ワークフローは、作成からのバージョンとして存在します。 基本情報以外に変更を加えた場合は、ワークフローの新しいバージョンを作成します。 詳細については、「 [ワークフローのプロパティを管理する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-properties)参照してください。

ワークフローのタスクまたは実行条件を変更するには、そのワークフローの新しいバージョンを作成する必要があります。 ワークフロー内のタスクは、必要に応じて追加、並べ替え、削除できます。 Microsoft Entra 管理センター内でワークフローのタスクまたは実行条件を更新すると、ワークフローの新しいバージョンが自動的に作成されます。 Microsoft Graph でこれらの更新を行うには、新しいワークフロー バージョンを手動で作成する必要があります。

### Microsoft Entra 管理センターを使用してワークフローのタスクを編集する

ワークフロー内のタスクは、必要に応じて追加、編集、並べ替え、削除できます。 Microsoft Entra 管理センターを使用してワークフローのタスクを編集するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **[ID ガバナンス]**&gt;**[ライフサイクル ワークフロー]**&gt;**[ワークフロー]** に移動します。
3. タスクを編集するワークフローを選択し、画面の左側で [タスク] を選択 **します**。
4. タスクをワークフローに追加するには、[タスクの **追加** ] ボタンを選択します。

    [Image: ワークフローにタスクを追加するスクリーンショット。]
5. [有効化] ボタンと [無効] ボタンを使用して、必要に応じてタスクを **有効** または **無効にすることができます** 。
6. [並べ替え] ボタンを選択すると、ワークフローでタスクが実行される順序を **並べ替** えることができます。 [削除] ボタンを使用して、ワークフローからタスクを **削除** することもできます。

    [Image: ワークフロー内のタスクの並べ替えのスクリーンショット。]
7. 変更を行った後、[ **保存]** を選択してタスクへの変更をキャプチャします。

### Microsoft Entra 管理センターを使用してワークフローの実行条件を編集する

Microsoft Entra 管理センターを使用してワークフローの実行条件を編集するには、次の手順を実行します。

1. ライフサイクル ワークフローの左側のメニューで、[ワークフロー] を選択 **します**。
2. 画面の左側で、[ **実行条件**] を選択します。 [Image: ワークフローの実行条件の詳細のスクリーンショット。]
3. この画面には、 **トリガーの詳細**が表示されます。 トリガーの種類と属性の詳細が表示されます。 テンプレートでは、属性の詳細を編集して、ワークフローの実行時を定義できます。
4. [ **スコープの詳細** ] タブを選択します。 [Image: ワークフローの実行スコープ ページのスクリーンショット。]
5. この画面では、ワークフローを実行するユーザーのルールを定義できます。 トリガースコープの **種類** がルールベースとして設定されている場合は、ユーザープロパティの式を使用してルールを定義できます。 サポートされているユーザー プロパティの詳細については、ユーザー プロパティに [対するサポートされているクエリを](https://learn.microsoft.com/ja-jp/graph/aad-advanced-queries#user-properties)参照してください。 トリガー スコープの種類がグループベースの場合は、ワークフローのスコープであるグループを選択できます。
6. 変更を行った後、[ **保存]** を選択して、実行条件の変更をキャプチャします。

### Microsoft Entra 管理センターを使用してワークフローのバージョンを表示する

1. ライフサイクル ワークフローの左側のメニューで、[ワークフロー] を選択 **します**。
2. このページには、現在のすべてのワークフローの一覧が表示されます。 バージョンを表示するワークフローを選択します。
3. 画面の左側で、[バージョン] を選択 **します**。

    [Image: ワークフローのバージョンのスクリーンショット。]
4. このページには、ワークフロー バージョンの一覧が表示されます。

    [Image: ライフサイクル ワークフローのバージョン一覧の管理のスクリーンショット。]

### Microsoft Graph を使用して既存のワークフローの新しいバージョンを作成する

Microsoft Graph を使用して API を使用してワークフローの新しいバージョンを作成するには、「[workflow: createNewVersion](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-workflow-createnewversion)」を参照してください。

#### Microsoft Graph を使用してワークフローのバージョンを一覧表示する

Microsoft Graph を使用して API を使用してワークフローのバージョンを一覧表示するには、次を参照してください。 [バージョンの一覧表示 (ライフサイクル ワークフローの)](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-workflow-list-versions)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users"} -->
## ゲスト ユーザーのMicrosoft Entra ID ガバナンス ライセンス - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users
- Service: entra-id-governance / entitlement-management
- Article date: 2026-09-23
- Summary: ゲスト ユーザー Microsoft Entra IDライセンスを取得する方法について説明します。

この記事では、ゲスト アドオンのMicrosoft Entra ID ガバナンスの価格構造の概要と、テナントをAzure サブスクリプションにリンクして、正しい課金と機能へのアクセスを確保する方法について説明します。

### 月間アクティブ ユーザー (MAU) の課金モデル

Microsoft Entra ID ガバナンスでは、従業員のライセンスとは異なるゲスト ユーザーの月間アクティブ ユーザー (MAU) ライセンスを利用します。 従業員のライセンスの詳細については、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

ゲスト課金モデルでは、ユーザーの認証場所に関係なく **、ゲストは userType of Guest** によって識別されます。 userType of **Guest** は、すべての B2B 招待メソッドの既定の userType であり、ID 管理者が設定することもできます。 毎月の請求には、その月に 1 つ以上のガバナンス アクションを持つ各ゲスト ユーザーのレコードが含まれます。 価格の詳細については、Azure価格のページを参照してください。

### 課金対象のガバナンス機能

ゲスト ユーザーは、Microsoft Entra ID ガバナンス専用の機能をアクティブに使用している場合にのみ課金されます。 Microsoft Entra P2 の機能は課金されず、P2 アクションに対してAzure サブスクリプションのリンクは必要ありません。 さらに、前の月にアクセスが自動割り当てされた場合など、ゲストが 1 か月間にアクティブなガバナンス関連のアクションを実行しない場合、その月の課金は行われません。

監査ログを調べることで、ゲスト アドオンのMicrosoft Entra ID ガバナンスに課金されるアクションを特定できます。 具体的には、課金対象の各アクションには、次のプロパティが含まれています。

- TargetId: ターゲット ユーザーのオブジェクト ID
- ターゲットユーザータイプ:ゲスト
- GovernanceLicenseFeature使用:真

次の表に、 **ゲスト ユーザー**に対して現在課金されるアクションの一覧を示します。 この一覧は、Microsoft Entra ID ガバナンスに追加される機能が増えるにつれて変更される可能性があります。

| Service | Action | 課金対象イベントと API | TargetUserType が Guest で GovernanceLicenseFeatureUsed が True の監査ログ |
| --- | --- | --- | --- |
| エンタイトルメント管理 | [他のユーザーに代わってアクセス パッケージを要求する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf) | 要求の作成が成功した場合に課金されます。 ユーザーは、別のユーザーに代わってアクセス パッケージの割り当てを要求または更新します。**API**`https://graph.microsoft.com/v1.0/identityGovernance/entitlementManagement/assignmentRequests` 要求元 (マネージャー) がトークンから抽出され、ターゲット オブジェクトがアクセスを受け取っている直接従業員の ID によって決定される場合。 | ユーザーがアクセス パッケージの割り当てを要求する、アクセス パッケージの割り当てを作成するユーザーの更新要求、管理者がアクセス パッケージにユーザーを直接割り当てる |
| エンタイトルメント管理 | [Guest は、Microsoft Entra ロールの割り当てに割り当てられます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-roles) | Microsoft Entra ロールがアクセス パッケージに含まれている場合、要求の作成が成功した場合に課金されます。**API** アクセス パッケージにMicrosoft Entraロールが含まれている場合は、`https://graph.microsoft.com/v1.0/identityGovernance/entitlementManagement/assignmentRequests`。 | ユーザーがアクセス パッケージの割り当てを要求する、アクセス パッケージの割り当てを作成するユーザーの更新要求、管理者がアクセス パッケージにユーザーを直接割り当てる |
| エンタイトルメント管理 | [スポンサー ポリシーが割り当てに適用される](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create) | スポンサーがアクセス パッケージ ポリシーに承認者として含まれている場合、要求の作成が成功した場合に課金されます。**API**`https://graph.microsoft.com/v1.0/identityGovernance/entitlementManagement/assignmentRequests` スポンサーがアクセス パッケージ ポリシーの承認者として含まれている場合。 | ユーザーがアクセス パッケージの割り当てを要求する、アクセス パッケージの割り当てを作成するユーザーの更新要求、管理者がアクセス パッケージにユーザーを直接割り当てる |
| エンタイトルメント管理 | [EM – グループの PIM](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review-pim-for-groups) | PIM グループがアクセス パッケージに含まれている場合、要求の作成が成功した場合に課金されます。**API**`https://graph.microsoft.com/v1.0/identityGovernance/entitlementManagement/assignmentRequests` アクセス パッケージに PIM グループが含まれている場合。 | ユーザーがアクセス パッケージの割り当てを要求する、アクセス パッケージの割り当てを作成するユーザーの更新要求、管理者がアクセス パッケージにユーザーを直接割り当てる |
| エンタイトルメント管理 | [ゲストが要求に検証済み ID を使用する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-verified-id-settings) | ポリシーで検証済み ID が必要な場合、要求の作成が成功した場合に課金されます。**API**`https://graph.microsoft.com/v1.0/identityGovernance/entitlementManagement/assignmentRequests` アクセス パッケージ ポリシーで検証済み ID が必要な場合。 | ユーザーがアクセス パッケージの割り当てを要求する、アクセス パッケージの割り当てを作成するユーザーの更新要求、管理者がアクセス パッケージにユーザーを直接割り当てる |
| エンタイトルメント管理 | [カスタム拡張機能で割り当てられたゲスト ポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration) | カスタム拡張機能が割り当てポリシーに含まれている場合、要求の作成が成功した場合に課金されます。**API**`https://graph.microsoft.com/v1.0/identityGovernance/entitlementManagement/assignmentRequests` カスタム拡張機能が割り当てポリシーに含まれている場合。 | ユーザーがアクセス パッケージの割り当てを要求する、アクセス パッケージの割り当てを作成するユーザーの更新要求、管理者がアクセス パッケージにユーザーを直接割り当てる |
| エンタイトルメント管理 | [ゲストに自動割り当てポリシーが付与される](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy) | 自動割り当てポリシーを使用して要求の作成が成功した場合に課金されます。 | エンタイトルメント管理では、ユーザーに対するアクセス パッケージの割り当て要求が作成されます。 |
| エンタイトルメント管理 | [任意の恒等式を直接割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity) | ディレクトリにまだ含まれていないユーザーにアクセス パッケージを直接割り当てるときに、要求の作成が成功した場合に課金されます。**API**`https://graph.microsoft.com/v1.0/identityGovernance/entitlementManagement/assignmentRequests` ディレクトリに存在しないユーザーに対して requestType "*AdminAdd*" を使用する場合。 | エンタイトルメント管理は外部ユーザーを招待します。 |
| エンタイトルメント管理 | [ゲストを管理対象としてマークする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-manage-lifecycle) | 管理ユーザーへの変換に関する課金。**API**`https://graph.microsoft.com/beta/identityGovernance/entitlementManagement/subjects` ここで *、"subjectLifecycle"* は "governed" に設定されます。 | アクセス パッケージのユーザー ライフサイクルを更新します。 |
| ライフサイクル ワークフロー | [ワークフローがゲストに対して実行される](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) | ワークフローの実行に関する課金。**API**`https://graph.microsoft.com/v1.0/identityGovernance/lifecycleWorkflows/workflows/{workflowId}/activate` | ユーザーのワークフロー実行が開始されました。 |
| ライフサイクル ワークフロー | ゲストがゲスト ライフサイクル ポリシーによって無効になっている | N/A | ライフサイクル ポリシーによって無効になっているゲスト ユーザー |
| ライフサイクル ワークフロー | ゲストがゲスト ライフサイクル ポリシーによって削除される | N/A | ライフサイクル ポリシーによって削除されたゲスト ユーザー |
| ライフサイクル ワークフロー | ゲスト ユーザーに対するスポンサー構成証明 (スポンサー プランを拡張) | POST /beta/users/{guestUserId}/microsoft.graph.identityGovernance.attest | ゲスト ユーザーの構成証明 |
| ライフサイクル ワークフロー | ユーザーが新しいゲスト ユーザーをスポンサーする | POST /beta/users/{guestUserId}/microsoft.graph.identityGovernance.addSelfAsSponsor | ゲスト ユーザーのスポンサー |
| アクセス レビュー | [アクセス レビュー – 機械学習によるアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/review-recommendations-access-reviews#user-to-group-affiliation) | ゲスト ユーザーがレビューに含まれている場合に課金されます。 **API**`https://graph.microsoft.com/v1.0/identityGovernance/accessReviews/definitions` グループ レビューで推奨設定が有効になっている場所。 | 決定項目の概要。 |
| アクセス レビュー | [アクセス レビュー - 非アクティブなユーザー](https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-stale-guest-accounts#monitor-guest-accounts-at-scale-with-inactive-guest-insights) | ゲスト ユーザーがレビューに含まれている場合に課金されます。**API**`https://graph.microsoft.com/v1.0/identityGovernance/accessReviews/definitions` 非アクティブなゲスト レビューがグループ リソースのポリシーに含まれている場合。 | 決定項目の概要。 |
| アクセス レビュー | [アクセス レビュー – カタログ アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/catalog-access-reviews) | ゲスト ユーザーがレビューに含まれている場合に課金されます。**API**`https://graph.microsoft.com/v1.0/identityGovernance/accessReviews/unified/definitions/` | 決定項目の概要。 |

### マルチテナント組織でのゲスト課金

ガバナンス ゲストの課金は、userType が **guest** のユーザーにのみ適用されるため、Microsoft Entra ID ガバナンスライセンスを持つメンバー ユーザーが userType が **member** の他の組織テナントに取り込まれた場合、課金メーターには課金されません。

これらのユーザーがメーターに対して発生する **ゲスト** の userType を使用して取り込まれた場合は、マルチテナント組織を設定または参加することで課金されないようにすることができます。 ゲスト ユーザーが参加している組織のテナントからの場合、ゲストは課金メーターに計上されません。 「 Microsoft 365を参照してください。

### 課金の例

#### シナリオ 1: アクセス パッケージの割り当ての自動化

**March**:

- Contoso は、アクセス パッケージを 500 人のゲスト ユーザーに割り当てる自動割り当てポリシーを作成します。
- Contoso IT は、500 人のゲスト ユーザーの別のセットに対してゲスト変換 API を実行します。
- 課金: 3 月の場合、Contoso は合計 1,000 人のゲスト ユーザーに対して課金されます。自動割り当てポリシーのユーザーは 500 人、ゲスト変換 API の場合は 500 ユーザーです。

**April**:

- 3 月にアクセス パッケージが自動的に割り当てられた 500 人のゲスト ユーザーは、割り当てを保持しますが、4 月にこれらのユーザーに対して明示的なアクションが行われなかったため、課金されません。
- さらに、Contoso IT は、100 人のゲストに対して非アクティブなアクセス レビューを実行します。
- 課金: 4 月の場合、Contoso は非アクティブなアクセス レビューに対して 100 人のユーザーに対して課金されます。

#### シナリオ 2: 非アクティブなユーザーのライフサイクル ワークフローとアクセス レビュー

**March**:

- Fabrikam は、300 人のゲスト ユーザーに対してライフサイクル ワークフローを実行します。
- また、非アクティブなユーザーに対してアクセス レビューを実行し、以前と同じゲストの 100 人に加えて、異なる 200 人のゲストセットを対象とします。
- 課金: 3 月の Fabrikam は、ライフサイクル ワークフローに対して 300 ユーザー、非アクティブなアクセス レビューに対して 200 ユーザーに対して課金されます。 ゲスト ユーザーのうち 100 人が既にライフサイクル ワークフローの料金を負担しているため、非アクティブなユーザー レビューに対する追加料金は発生しませんでした。各ゲスト ユーザーは、1 か月に 1 つ以上のガバナンス アクションに対して 1 回だけ課金されるためです。

**April**:

- 3 月に 200 人のゲスト ユーザーの 2 番目のグループから、200 人のゲスト全員が、アプリへのアクセスを許可し、4 月に繰り返し 3 月に実行されたのと同じ非アクティブなユーザー アクセス レビューを持つ自動割り当てアクセス パッケージを受け取ります。
- 課金: 4 月の場合、Fabrikam は 200 ユーザーに対して課金されます。 これらのユーザーには、4 月に非アクティブなアクセス レビューとアクセス パッケージの自動割り当ての両方がありましたが、各ゲストは月に 1 回だけ課金されます。

#### シナリオ 3: 非アクティブなユーザーとユーザーからグループへの所属に関するアクセス レビュー

**May**:

- Tailspin Toys は、200 人のユーザーに対して非アクティブなゲスト アクセス レビューを作成します。
- Tailspin は、ユーザーからグループへの所属機能が有効になっている 300 人のゲスト ユーザーの異なるセットを持つセキュリティ グループの 2 つ目のアクセス レビューを作成します。
- 課金: 5 月の場合、Tailspin は非アクティブなゲスト アクセス レビューに対して 500 ユーザーから 200 人、ユーザーからグループへの所属を持つレビューに対して 300 ユーザーに対して課金されます。

**6 月**:

- Contoso はテナントに 10,000 のゲスト アカウントを持っており、非アクティブなゲストに対してアクセス レビューを実行したいと考えています。
- Contoso は、ゲストのみを対象とし、非アクティブなユーザーのみを対象にしたアクセス レビューを作成します。 アクセス レビューは、10,000 人のゲスト ユーザーをすべてスキャンし、非アクティブと見なされる 240 人を識別します。 キャンペーンには、これらの 240 人のゲスト ユーザーのみが含まれます。
- 10,000 人のゲスト ユーザーのいずれに対しても、ガバナンスに関する他のイベントは発生しません。
- 課金: 6 月の場合、Contoso は 240 人のユーザーに対して課金されます。アクセス レビューを実行した非アクティブなゲストに対してのみ課金されます。

#### テナントをサブスクリプションにリンクする

適切な課金と機能へのアクセスを行うには、テナントを Azure サブスクリプションにリンクする必要があります。 テナントをサブスクリプションにリンクするには、次の手順に従います。

1. 少なくともサブスクリプション内の所有者ロールまたはサブスクリプション内のリソース グループを持つアカウントで[Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
2. リンクするディレクトリを選択します。Microsoft Entra 管理センター ツール バーで、ポータル ツール バーの **Settings** アイコンを選択します。 次に、 **ポータルの設定 |[ディレクトリとサブスクリプション** ] ページで、ディレクトリ **名** の一覧で従業員テナントを見つけて、[ **切り替え**] を選択します。
3. **Entra ID**&gt;**ID Governance**&gt;**Dashboard** に移動します。
4. ガバナンス ダッシュボードで、ゲスト ガバナンス パネルを見つけて、[ **作業の開始**] を選択します。
5. [ **サブスクリプションのリンク** ] ウィンドウで、 **サブスクリプション** と **リソース グループ**を選択します。 次 **に、[オンにする**] を選択します。

これらの手順を完了すると、Azure サブスクリプションは、該当する場合、Azure Direct または Enterprise Agreement の詳細に基づいて課金されます。

### サブスクリプションが見つからない場合はどうすればよいですか?

[サブスクリプションのリンク] ウィンドウで使用できる **サブスクリプション** がない場合は、次のような理由が考えられます。

- 適切なアクセス許可がありません。 少なくともサブスクリプション内の所有者ロールまたはサブスクリプション内のリソース グループを持つAzure アカウントでサインインしてください。
- サブスクリプションは存在しますが、まだディレクトリに関連付けられません。 [既存のサブスクリプションをテナントに関連付け、テナント](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)に[リンクする手順を](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing#link-your-azure-ad-tenant-to-a-subscription)繰り返すことができます。
- サブスクリプションが存在しません。 [ **サブスクリプションのリンク** ] ウィンドウで、サブスクリプションを **まだ持っていない場合は、ここで作成できるサブスクリプションを選択して、サブスクリプションを作成**できます。 新しいサブスクリプションを作成したら、新しいサブスクリプションに [リソース グループを作成](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/manage-resource-groups-portal) し、 [テナントにリンクするための手順を](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing#link-your-azure-ad-tenant-to-a-subscription)繰り返す必要があります。

### ゲストの課金をオフにする

ガバナンス ゲストの課金をオフにするには、ガバナンス ダッシュボードに戻り、ゲスト ガバナンス パネルで **[編集]** を選択します。 [ゲスト アクセスの編集] パネルで、[無効にする] を選択して、ゲスト ユーザーの課金機能とMicrosoft Entra ID ガバナンス機能を無効にします。

### ゲスト ユーザー ライセンスに関する FAQ

**ゲストのみを管理する場合は、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートのサブスクリプションを持っている必要がありますか?**

Yes. ゲスト ユーザーのサブスクリプションは必要ありませんが、テナントの管理者には少なくとも 1 つのMicrosoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスが必要です。

**ゲスト ユーザーに対して、Microsoft Entra P2 に含まれるアクセス レビューとエンタイトルメント管理機能を使用しています。 この使用量に対して課金を開始しますか?**

基本的なアクセス レビューやエンタイトルメント管理機能など、Microsoft Entra P2 に含まれるガバナンス機能は、ガバナンス ゲスト アドオンには課金されません。 Microsoft Entra スイートまたはスタンドアロンのMicrosoft Entra ID ガバナンス専用のガバナンス機能のみがメーターに課金されます。 詳細については、このページの課金対象テーブルのアクションを参照してください。

**ガバナンス ゲストの課金は、最初の 50,000 人の月間アクティブ ユーザー (MAU) 内のゲスト ユーザーを含むすべてのゲスト ユーザーに適用されますか?**

はい。ガバナンスの課金には無料レベルはありません。 ガバナンス ゲストの課金は、最初の 50,000 MAU 内のゲスト ユーザーであっても、すべてのゲスト ユーザーに適用されます。

**Microsoft Entra ID ガバナンス課金のゲスト使用量を見積もったり理解したりするにはどうすればよいですか?**

EIG ゲスト使用状況監視ブックを使用して、テナント内のゲストの使用状況の傾向を把握できます。 このレポートには、請求された過去の使用状況が表示されますが、今後の使用状況は異なる場合があります。 EIG ゲスト使用状況監視ブックに関するフィードバックを提供するには、このフォームを参照 [してください](https://forms.office.com/r/N4dYnQcXTN)。

### ゲスト アドオンのMicrosoft Entra ID ガバナンスなしで使用できないゲスト ガバナンス機能

ゲスト ユーザー Microsoft Entra ID ガバナンス機能を使用するには、ゲスト アドオンのMicrosoft Entra ID ガバナンスを使用して、テナントを Azure サブスクリプションにリンクする必要があります。 ゲスト課金メーターが有効になっていない場合は、次の動作が適用されます。

注

ゲスト用Microsoft Entra ID ガバナンスアドオンの接続は、2026 年 1 月から適用されます。 この一覧は、Microsoft Entra ID ガバナンスに追加される機能が増えるにつれて変更される可能性があります。

#### アクセス レビュー

- 次のいずれかの機能が選択されている場合、ゲスト ユーザーを対象にした新しいアクセス レビューを作成することはできません。
    - 非アクティブなユーザー アクセス レビュー
    - ユーザーからグループへの所属の推奨ヘルパー

#### エンタイトルメント管理

- スコープ内のゲスト ("*ゲストを含むディレクトリ内のすべてのユーザー*" または "*ディレクトリにないユーザー*") と、このドキュメントに記載されているMicrosoft Entra ID ガバナンス機能 (スポンサー承認者、カスタム拡張機能、検証 ID など) を使用してポリシーを作成することはできません。
- 構成済みの規則に含まれる新しい自動割り当てポリシーを作成することはできません。 `userType=Guest`
- これらの機能を追加する既存のエンタイトルメント管理ポリシーを更新することはできません。
- 次の操作を実行することはできません。
    - ゲストを管理対象としてマークする
    - ゲスト ユーザーを直接割り当てる

#### ライフサイクル ワークフロー

- ワークフロー スコープにゲスト ユーザーが含まれている場合、新しいワークフローを作成することはできません。
    - 構成されたルールには、次のものが含まれます。 `userType=Guest`
- 実行条件に `userType=Guest`のスコープが含まれる既存のワークフローを更新することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/my-access-approver-details"} -->
## 申請者に対する承認者詳細の表示設定 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-approver-details
- Service: entra-id-governance
- Article date: 2026-06-17
- Summary: 要求者が、マイ アクセス ポータルの保留中のアクセス パッケージ要求の承認者の詳細をテナントレベルまたはパッケージ レベルで表示できるかどうかを構成する方法について説明します。

注

この機能は、2025 年 9 月から広く利用可能になります。

管理者は、要求元が [マイ アクセス](https://myaccess.microsoft.com) ポータルで保留中のアクセス パッケージ要求の承認者の詳細を表示するかどうかを制御できます。 このオプションは、アクセス パッケージ レベルまたはテナント レベル (すべてのパッケージの既定) で設定できます。 アクセス パッケージの設定は、明示的に [はい] または [いいえ] に設定されている場合にテナント設定をオーバーライドします。

### テナント レベルの設定 (既定では、すべてのアクセス パッケージに適用されます)

この設定を使用して、テナント内のすべてのアクセス パッケージの既定の動作を定義します。 この設定は **メンバーにのみ** 適用され、アクセス パッケージ レベルの設定でオーバーライドできます。

1. Microsoft Entra 管理センターで、**ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**コントロール構成** に移動します。
2. **エンド ユーザーの [マイ アクセス設定**] で、**保留中のアクセス パッケージ要求のメンバーに承認者の詳細を表示するを見つけます。**
3. 目的の動作を選択します。

    - **オン** – すべてのメンバー (ゲストを除く) には、マイ アクセスの保留中のアクセス パッケージ要求に対する承認者の名前と電子メール アドレスが表示されます。
    - **オフ** – メンバーには既定で承認者の詳細は表示されません。
4. **保存** を選択します。

注

- テナント設定は既定ですべてのアクセス パッケージに適用されますが、高度な要求設定を使用してパッケージごとにオーバーライドできます。
- 承認者の詳細をゲストと接続された組織のユーザーに表示するには、アクセス パッケージ レベルの設定を [はい] に設定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/my-access-portal-overview"} -->
## マイ Access ポータルとは - Microsoft Entra - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview
- Service: entra-id-governance
- Article date: 2024-12-10
- Summary: ユーザーがMicrosoft Entra内でアクセスを管理できるマイ アクセス ポータルの説明。

マイ アクセスは、ユーザーがMicrosoft Entra内のリソースへのアクセスを確認または要求するために使用し、管理者がこのアクセスを構成するために使用する Web ベースのポータルです。 マイ Access ポータルを使用しているユーザーに応じて、異なる一連のアクションを実行できます。

ユーザーはMy Accessポータルにアクセスして、次のステップを実行します。

- 自分でアクセスをリクエストする
- 自分自身または直属の部下のaccessを承認または拒否する
- 自分でアクセスを確認する
- ユーザーのアクセスを確認する
- 管理されているエージェントのアクセス権を管理および確認

管理者は、Microsoft Entra 管理センターを使用して、次の構成を行うことができます。

- ユーザーが要求するか、エージェントに割り当てられるパッケージにアクセスする
- アクセス パッケージのアクセス レビュー
- グループとアプリケーションのAccess レビュー
- 概要ページ

### ライセンス要件

この機能には、組織のユーザーのMicrosoft Entra ID ガバナンスまたはMicrosoft Entra スイートサブスクリプションが必要です。 この機能内の一部の機能は、Microsoft Entra ID P2 サブスクリプションで動作する場合があります。 詳細については、各機能の記事を参照してください。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

エージェント ID [にMicrosoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を使用するには、次のいずれかのライセンス プランが必要です。

- **Microsoft 365 E7** (エージェント 365 とMicrosoft Entra スイートを含む) は、ユーザー ID とエージェント ID のガバナンスを提供します。
- **Microsoftエージェント 365** ライセンスは、少なくとも Microsoft Entra P1 または Microsoft 365 E3 とペアリングされています。

詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)」を参照してください。 エージェント固有の機能の完全な一覧については、**Microsoft Entra ID ガバナンス ライセンス表**の [Microsoft Agent 365](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) 列を参照してください。

### [概要] ページ

[概要] ページには、保留中の要求やレビューなど、注意を必要とする主要なタスクが表示されます。 マイ Access ポータルのランディング ページです。

### アクセス パッケージを見つける

マイ Access ポータルでは、左側のメニューの **Access packages** と **Available** タブを選択して、要求できるaccess パッケージを表示できます。accessを要求するaccess パッケージを一覧で見つけます。 検索バーを使用して、access パッケージを名前、説明、またはリソースで検索できます。 accessパッケージを見つけたら、その行を選択してください。 質問に回答し、要求を正当化するビジネス上の理由を提供することが必要な場合があります。

左側のメニューで [要求の履歴] を選択して、要求と状態の一覧を表示します。

詳細については、「[access パッケージの要求 - エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access)を参照してください。

### 要求を承認または拒否する

マイ Access ポータルでは、左側のメニューで **Approvals** を選択すると、保留中の要求を表示できます。 保留中の要求を選択し、詳細を確認します。 提示された情報に基づいて、要求を承認または拒否します。

詳細については、「[access要求の承認または拒否 - エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve)を参照してください。

### アクセスを確認する

マイ Access ポータルでは、左側のメニューで **Access reviews** を選択すると、保留中のaccess レビューを表示できます。 このページで、特定のレビューを選択し、提示された情報に基づいて決定を行います。

Microsoft Entra IDは、レビューの開始直後に手順を記載した電子メールも送信します。 メールに示された手順に従ってレビューを完了します。

自己レビューの詳細については、[アクセスレビューでリソースへのアクセスを確認する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/self-access-review)を参照してください。

他のユーザーのレビューの詳細については、＜c0＞アクセス レビューでのグループおよびアプリケーションへのアクセス確認＜/c0＞を参照してください。

### エージェントを管理する

マイ Access ポータルでは、自分が所有し、スポンサーしているエージェント ID の一覧を表示できます。 エージェント ID の管理とスポンサーの詳細については、「エージェント ID の [管理関係 (所有者、スポンサー、およびマネージャー)」](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers)を参照してください。

エージェント ID を管理、承認、確認、有効化することができます。 所有またはスポンサーであるすべてのエージェント ID について、accessのレベルやエージェント ID アクティビティなどの一般的な情報を確認することもできます。 マイ Access ポータルを使用したエージェント ID の管理の詳細については、「[Manage Agent (プレビュー)](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/on-demand-workflow"} -->
## オンデマンドでワークフローを実行する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/on-demand-workflow
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: この記事では、ユーザーがライフサイクル ワークフローを使用してワークフローをオンデマンドで実行するようにガイドします。

スケジュールされたワークフローは、既定では 3 時間ごとに実行されますが、必要に応じて実行して、必要に応じて特定のユーザーに適用することもできます。 ワークフローは任意のユーザーに対してオンデマンドで実行でき、ユーザーがワークフローの実行条件を満たしているかどうかは考慮されません。 ワークフローをオンデマンドで実行すると、スケジュールされた実行前にワークフローをテストできます。 このテストでは、一度に最大 10 人のユーザーのセットで、より大きなユーザー セットを処理する前にワークフローがどのように実行されるかを確認できます。 スケジュールされた実行前にワークフローをテストすると、潜在的なライフサイクルの問題をより迅速に事前に解決できます。

### Microsoft Entra 管理センターでワークフローをオンデマンドで実行する

ワークフローをオンデマンドで実行するには、次の手順に従います。

注

オンデマンドで実行するには、ワークフローを有効にする必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**を参照します**。
3. ワークフロー画面で、実行する特定のワークフローを選択します。

    [Image: オンデマンドで実行するライフサイクル ワークフローワークフローの一覧のスクリーンショット。]
4. [ **オンデマンドで実行**] を選択します。
5. [ **ユーザーの選択** ] タブで、[ **ユーザーの追加**] を選択します。
6. [ユーザーの追加] 画面で、オンデマンド ワークフローを実行するユーザーを選択します。

    [Image: オンデマンド ワークフローのユーザーの追加のスクリーンショット。]
7. [**] を選択し、[**] を追加します。
8. 選択内容を確認し、[ **ワークフローの実行**] を選択します。

    [Image: オンデマンドで実行されているワークフローのスクリーンショット。]

### Microsoft Graph を使用してオンデマンドでワークフローを実行する

Microsoft Graph 経由で API を使用してワークフローをオンデマンドで実行するには、「 [ワークフロー: アクティブ化 (ワークフローをオンデマンドで実行する)」](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-workflow-activate)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/perform-access-review"} -->
## アクセス レビューでグループおよびアプリケーションに対するアクセスを確認する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review
- Service: entra-id-governance / access-reviews
- Article date: 2026-03-12
- Summary: Microsoft Entra アクセス レビューでグループ メンバーまたはアプリケーション アクセスのアクセスを確認する方法について説明します。

Microsoft Entra IDでは、企業がアクセス レビューと呼ばれる機能を使用して、Microsoft Entra IDやその他のMicrosoft Web サービス内のグループやアプリケーションへのアクセスを管理する方法が簡略化されます。 この記事では、指定されたレビュー担当者が、アプリケーションへのアクセス権を持つグループのメンバーやユーザーに対してアクセス レビューを実行する方法について説明します。 アクセス パッケージへのアクセスをレビューする場合は、「[エンタイトルメント管理でのアクセス パッケージのアクセスをレビューする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-review-access)」を参照してください。

注意

**アクセス パッケージの承認要求**を他のユーザーに委任する場合は、「[マイ アクセスで承認を委任する](https://learn.microsoft.com/ja-jp/entra/id-governance/delegate-approvals-my-access)」を参照してください。

### マイ アクセス ポータルを使用してアクセス レビューを実行する

[マイ アクセス ポータル](https://myaccess.microsoft.com/)は、アクセスのニーズを付与、承認、および確認するためのわかりやすいポータルです。

#### 電子メールを使用してマイ アクセスに移動する

重要

メールの受信に遅延が発生する可能性があります。 場合によっては、最大 24 時間かかることがあります。 すべてのメールを確実に受信するため、MSSecurity-noreply@microsoft.com を信頼できる宛先のリストに追加してください。

1. Microsoftからアクセスの確認を求めるメールを探します。 メールのメッセージの例を次に示します。

    [Image: Microsoftからの、グループへのアクセスを確認するための電子メールの例のスクリーンショット]
2. **[Start review](https://learn.microsoft.com/ja-jp/entra/id-governance/レビューの開始)** リンクを選んで、アクセス レビューを開きます。

#### マイ アクセスに直接移動する

お使いのブラウザーを使用してマイ アクセスを開くと、保留中のご自分のアクセス レビューを表示することもできます。

1. https://myaccess.microsoft.com/ からマイ アクセス にサインインします。
2. 左のメニューから **[アクセス レビュー]** を選択すると、割り当てられている保留中のアクセス レビューの一覧が表示されます。

### 1名以上のユーザーのアクセスを確認する

**[グループとアプリ]** で [マイ アクセス] を開くと、次のように表示されます。

- **名前**: アクセス レビューの名前。
- **期限**: レビューの期限。 この日付を過ぎると、拒否されたユーザーはレビュー対象のグループまたはアプリから削除される場合があります。
- **リソース**: レビュー中のリソースの名前。
- **[進行状況]**: このアクセス レビューに関わるユーザーの合計数に対する、レビューされたユーザー数。

開始するには、アクセス レビューの名前を選びます。

[Image: アプリとグループについての保留中のアクセス レビューの一覧のスクリーンショット。]

これが開くと、アクセス レビューの対象となるユーザーの一覧が表示されます。

注意

独自のアクセス権を確認する要求の場合、ページの外観は異なります。 詳細については、「[グループまたはアプリケーションへの自分のアクセス権のレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/review-your-access)」を参照してください。

2 つの方法でアクセスを承認または拒否できます。

- 1 名以上のユーザーのアクセスを手動で承認または拒否できます。
- システムの推奨事項を承認することができます。

#### 1名以上のユーザーのアクセスを手動で確認する

1. ユーザーのリストをレビューして、ユーザーに継続してアクセスを承認するか拒否するかを決定します。
2. 1 名以上のユーザーを選択するには、彼らの名前の横にある円を選択します。
3. バーで **[承認]** または **[拒否]** を選択します。

    ユーザーを引き続きアクセスさせる必要があるかわからない場合は、**[不明]** を選択します。 ユーザーは自分のアクセスを維持することができ、お客様の選択した内容は監査ログに記録されます。 提供された情報は、他のレビュー担当者も利用できるということに留意してください。 彼らはお客様のコメントを読んで、要求のレビュー時にそれを考慮に入れることができます。

    [Image: レビューが必要なユーザーの一覧を示す、開かれたアクセス レビューのスクリーンショット。]
4. アクセス レビューの管理者は、理由が不要な場合でも、**[理由]** ボックスに理由を指定することを求める場合があります。 その場合でも、決定の理由を提示できます。 提供された情報は、他の承認者もレビューに利用できます。
5. **送信**を選択します。

    アクセス レビューが終了するまで、いつでも応答を変更できます。 応答内容を変更する場合は、その行を選択して、応答内容を更新します。 たとえば、一度拒否したユーザーを承認したり、一度承認したユーザーを拒否したりできます。

重要

- ユーザーは、アクセスを拒否されても、すぐに削除されるわけではありません。 レビュー期間が終了したとき、または管理者がレビューを停止すると、ユーザーは削除されます。
- レビュー担当者が複数いる場合、最後に送信された応答内容が記録されます。 管理者が Alice と Bob という 2 人のレビュー担当者を指名した場合を考えてみましょう。 最初に Alice がアクセス レビューを開いて、ユーザーのアクセス要求を承認します。 レビュー期間が終了する前に、Bob はアクセス レビューを開き、Alice が以前に承認したのと同じ要求についてアクセスを拒否します。 アクセスを拒否するという最後の決定が、応答として記録されます。

#### 推奨事項に基づいてアクセスを確認する

アクセス レビューを簡単かつ迅速に行うために、1 つの選択で受け入れることができる推奨事項もあります。 システムによってレビュー担当者の推奨事項が生成される方法は 2 つあります。 1 つの方法は、ユーザーのサインイン アクティビティによります。 ユーザーが 30 日以上非アクティブになっている場合、システムでは、レビュー担当者がアクセスを拒否することを推奨します。

もう 1 つの方法は、ユーザーのピアメンバーが持つアクセス権に基づいています。 ユーザーがそのピアと同じアクセス権を持っていない場合、システムでは、レビュー担当者がそのユーザーのアクセスを拒否することを推奨します。

**[30 日間サインインなし]** または **[ピア外れ値]** が有効になっている場合は、手順に従って推奨事項を受け入れます。

1. 1 名以上のユーザーを選択して、**[推奨事項を承認する]** を選択します。

    [Image: [推奨事項を承認する] ボタンを示す開かれたアクセス レビュー一覧のスクリーンショット。]

    すべての未承認ユーザーに対する推奨事項を受け入れるには、いずれも選択されていないことを確認してから、上部のバーにある **[推奨事項を承認する]** ボタンを選択します。
2. **[送信]** を選択して、推奨事項を承認します。

注意

推奨事項に同意しても、以前の決定は変更されません。

#### 複数段階のアクセスレビューで1名以上のユーザーのアクセスをレビューする (プレビュー)

管理者が複数ステージのアクセス レビューを有効にしている場合、レビューの合計ステージは 2 から 3 段階あります。 レビューの各ステージには、指定されたレビュー担当者がいます。

レビュー担当者として割り当てられたステージのサインインアクティビティに基づいて、アクセスを手動で確認するか、推奨事項を承認することになります。

アクセスレビューの作成時に管理者がこの設定を有効にした場合、第 2 段階または第 3 段階のレビュー担当者は、前の段階のレビュー担当者によって行われた決定も見ることができます。 第 2 段階または第 3 段階のレビュー担当者によって行われた決定は、前のステージを上書きすることになります。 そのため、第 2 段階のレビュー担当者が行う決定は、第 1 段階を上書きします。 第 3 段階のレビュー担当者の決定は、第 2 段階を上書きします。

[Image: ユーザーの選択を示すスクリーンショットにより、複数ステージのアクセスレビュー結果が表示されます。]

「 1 名以上のユーザーのアクセスをレビューする」の説明に従って、アクセスを承認、または拒否します。

注意

レビューの次の段階は、アクセスレビューのセットアップ中に指定された期間が経過するまでアクティブになりません。 管理者がステージが完了したと思われるが、このステージのレビュー期間がまだ切れていない場合は、Microsoft Entra 管理センターのアクセス レビューの概要にある **Stop current stage** ボタンを使用できます。 このアクションにより、アクティブなステージが終了し、次のステージが開始されます。

#### Teams 共有チャネルとMicrosoft 365 グループの B2B 直接接続ユーザーのアクセスを確認する (プレビュー)

B2B 直接接続ユーザーのアクセスをレビューするには、次の手順に従います。

1. レビュー担当者は、チームまたはグループのアクセス権のレビューを要求する電子メールを受け取る必要があります。 電子メール内のリンクを選択するか、直接 https://myaccess.microsoft.com/ に移動します。
2. 「1 名以上のユーザーのアクセスをレビューする」の手順に従って、チームへのユーザーのアクセスを承認するか拒否するかを決めます。

注意

内部ユーザーや B2B コラボレーション ユーザーとは異なり、B2B 直接接続ユーザーとチームには、レビューの実行時の最後のサインイン アクティビティに基づく推奨事項 *はありません* 。

レビューするチームに共有チャネルがある場合、それらの共有チャネルにアクセスするすべての B2B 直接接続ユーザーとチームがすべてレビューの一部です。 これには、B2B コラボレーション ユーザーと内部ユーザーが含まれます。 B2B 直接接続ユーザーまたはチームがアクセス レビューでアクセスを拒否されると、ユーザーはチーム内のすべての共有チャネルへのアクセス権を失います。 B2B 直接接続ユーザーの詳細については、「[B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)」に関する記事をお読みください。

### アクセス レビューでアクションが実行されない場合の動作を設定する

アクセスのレビューがセットアップされている場合、管理者は詳細設定を使用して、レビュー担当者がアクセス レビュー要求に応答しない場合に発生する処理を決定できます。

管理者はレビューを設定することで、レビュー担当者がレビュー期間の終了時に応答しなかった場合、未レビューのユーザー全員のアクセスについて自動決定を行うことができます。 これには、レビュー対象のグループまたはアプリケーションへのアクセスの喪失が含まれます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management"} -->
## Privileged Identity Management のドキュメント - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management
- Service: entra-id-governance / privileged-identity-management
- Article date: 2025-04-30
- Summary: 継続的な管理者アクセスを特権ロールに限定したり、アクセス許可を持つユーザーを検出したり、特権アクセスの状況を確認したりできる Microsoft Entra Privileged Identity Management (PIM) について学習します。

Microsoft Entra ID で Privileged Identity Management (PIM) を使用すると、継続的な管理者アクセスを特権ロールに限定できるほか、アクセス許可を持つユーザーを検出したり、特権アクセスの状況を確認したりできます。

### Privileged Identity Management について

#### 概要

- [Microsoft Entra Privileged Identity Management とは](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)

#### 概念

- [ライセンス要件](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)

#### video

- [お客様の領域でキーを保護する](https://www.youtube.com/embed/w7wTpZYJJeQ)

### 始めましょう

#### 攻略ガイド

- [Privileged Identity Management をデプロイする](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-deployment-plan)
- [Privileged Identity Management の使用開始](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started)

### ロールをアクティブにする (適格でアクティブなロールのユーザー)

#### 攻略ガイド

- [Microsoft Entra ロールをアクティブ化する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-activate-role)
- [自分の Azure リソース ロールをアクティブにする](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-activate-your-roles)

### ロールを割り当てる

#### 攻略ガイド

- [Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user)
- [Azure リソース ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles)

### 要求の承認

#### 攻略ガイド

- [Microsoft Entra ロールの要求を承認する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow)
- [Azure リソース ロールの要求を承認する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow)

### ロール設定を構成する

#### 攻略ガイド

- [Microsoft Entra ロールの設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)
- [Azure リソース ロールの設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-role-settings)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/azure-pim-resource-rbac"} -->
## Privileged Identity Management (PIM) で Azure リソース ロールの監査レポートを表示する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/azure-pim-resource-rbac
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Azure リソース ロールのアクティビティと監査履歴を表示します。

### 概要

Microsoft Entra ID の Privileged Identity Management (PIM) を使用すると、組織内の Azure リソース ロールのアクティビティ、アクティブ化、監査履歴を表示できます。 対象には、サブスクリプション、リソース グループ、さらに仮想マシンも含まれます。 Azure ロールベースのアクセス制御機能を使用する Microsoft Entra 管理センター内のリソースは、Privileged Identity Management のセキュリティおよびライフサイクル管理機能を利用できます。 監査データを既定の保持期間よりも長く保持する場合は、Azure Monitor を使用して Azure ストレージ アカウントにルーティングできます。 詳細については、「[Microsoft Entra のログを Azure ストレージ アカウントにアーカイブする](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-archive-logs-to-storage-account)」を参照してください。

注

組織で、[Azure Lighthouse](https://learn.microsoft.com/ja-jp/azure/lighthouse/overview) を使用するサービス プロバイダーに管理機能を外部委託している場合、そのサービス プロバイダーによって承認されているロールの割り当てはここに表示されません。

### アクティビティとアクティブ化を表示する

特定のユーザーがさまざまなリソースで実行したアクションを確認するには、アクティブ化期間に関連付けられている Azure リソース アクティビティを表示します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Azure リソース**を参照します。
3. アクティビティとアクティブ化を表示するリソースを選択します。
4. **[ロール]** または **[メンバー]** をクリックします。
5. ユーザーを選択します。

    日付ごとの Azure リソースに対するユーザー操作の概要が表示されます。 その同じ期間での最近のロールのアクティブ化も表示されます。

    [Image: リソース アクティビティの概要とロールのアクティブ化を含むユーザーの詳細のスクリーンショット。]
6. 特定のロールのアクティブ化を選択すると、そのユーザーがアクティブだったときに発生した詳細と対応する Azure リソース アクティビティが表示されます。

    [Image: 選択されたロールのアクティブ化とアクティビティの詳細のスクリーンショット。]

### 子を含むロールの割り当てをエクスポートする

監査者にロールの割り当ての完全な一覧を提供する必要があるコンプライアンス要件がある場合があります。 Privileged Identity Management を使用すると、特定のリソースでロールの割り当て (すべての子リソースのロールの割り当てを含む) のクエリを実行できます。 以前は、管理者がサブスクリプションに対するロールの割り当ての完全な一覧を取得するのは困難で、特定のリソースごとにロールの割り当てをエクスポートする必要がありました。 Privileged Identity Management を使用すると、サブスクリプション内のアクティブかつ資格のあるすべてのロールの割り当て (すべてのリソース グループとリソースに対するロールの割り当てを含む) についてクエリを実行できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Azure リソース**を参照します。
3. サブスクリプションなど、ロールの割り当てをエクスポートするリソースを選択します。
4. **[割り当て]** を選択します。
5. **[エクスポート]** を選択して [メンバーシップのエクスポート] ウィンドウを開きます。

    [Image: すべてのメンバーをエクスポートする [メンバーシップのエクスポート] ペインを示すスクリーンショット。]
6. **[すべてのメンバー エクスポート]** を選択して、CSV ファイル内のすべてのロールの割り当てをエクスポートします。

    [Image: エクスポートされたロールの割り当てが CSV ファイルで Excel に表示されているところを示すスクリーンショット。]

### リソースの監査履歴を表示する

リソースの監査では、リソースのすべてのロール アクティビティのビューが提供されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Azure リソース**を参照します。
3. 監査履歴を表示するリソースを選択します。
4. **[リソースの監査]** を選択します。
5. 定義済みの日付またはカスタム範囲を使用して履歴をフィルター処理します。

    [Image: フィルターを使ったリソース監査リストを示すスクリーンショット。]
6. **[監査の種類]** で **[アクティブ化 (割り当て済み + アクティブ化済み)]** を選択します。

    [Image: [監査の種類のアクティブ化] でフィルター処理されたリソース監査リストを示すスクリーンショット。]
7. **アクション** で**(アクティビティ)** を選択して、ユーザーのアクティビティの詳細を Azure リソースに表示します。

    [Image: 特定のアクションに対するユーザーのアクティビティの詳細を示すスクリーンショット。]

### 自分の監査を表示する

私の監査を使用すると、個人の役割アクティビティを表示できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Azure リソース**を参照します。
3. 監査履歴を表示するリソースを選択します。
4. **[自分の監査]** を選択します。
5. 定義済みの日付またはカスタム範囲を使用して履歴をフィルター処理します。

    [Image: 現在のユーザーの監査リストを示すスクリーンショット。]

注

監査履歴へのアクセスには、少なくとも特権ロールを持つ管理者ロールが必要です。

### 承認イベントの理由、承認者、およびチケット番号を取得する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **Entra ID**&gt;**監視と健康**&gt;**監査ログ**に移動します。
3. **Privileged** Identity Management サービスの監査イベントのみを表示するには、サービス フィルターを使用します。 **[監査ログ]** ページでは、次のことを実行できます。

    - **[状態の理由]** 列で監査イベントの理由を確認します。
    - 「ロールへのメンバーの追加要求が承認されました」イベントの **[開始者 (アクター)]** 列で承認者を確認します。

    [Image: PIM サービスの監査ログのフィルター処理を示すスクリーンショット。]
4. 監査ログ イベントを選択すると、 **[詳細]** ウィンドウの **[アクティビティ]** タブにチケット番号が表示されます。

    [Image: 監査イベントのチケット番号を示すスクリーンショット。]
5. 監査イベントの **[詳細]** ウィンドウの **[ターゲット]** タブに、要求者 (ロールをアクティブにしているユーザー) を表示できます。 Azure リソース ロールには、次の 3 種類のターゲットがあります。

    - ロール (**Type** = Role)
    - 要求者 (**タイプ** = その他)
    - 承認者 (**Type** = User)

    [Image: ターゲットの種類を確認する方法を示すスクリーンショット。]

通常、承認イベントのすぐ上のログ イベントは、完了した **ロールにメンバーを追加するための** イベントです。ここで **、開始者 (アクター)** は要求元です。 ほとんどの場合、監査の観点から承認要求で要求者を検索する必要はありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/concept-pim-for-groups"} -->
## グループ向けの特権アイデンティティ管理 (PIM) - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: グループ向けの Microsoft Entra Privileged Identity Management (PIM) を管理する方法。

### 概要

Microsoft Entra ID では、グループ向けの Privileged Identity Management (PIM) を使用して、グループの Just-In-Time メンバーシップと所有権をユーザーに付与できます。 グループを使用すると、Microsoft Entra ロール、Azure ロール、Azure SQL、Azure Key Vault、Intune、その他のアプリケーション ロール、サードパーティ アプリケーションなど、さまざまなシナリオへのアクセスを制御できます。

### グループ向けの PIM とは

PIM for Groups は Microsoft Entra Privileged Identity Management の一部であり、Microsoft Entra ロール用の PIM と Azure リソース用の PIM と共に、PIM for Groups を使用すると、ユーザーは Microsoft Entra セキュリティ グループまたは Microsoft 365 グループの所有権またはメンバーシップをアクティブ化できます。 グループを使用して、Microsoft Entra ロール、Azure ロール、Azure SQL、Azure Key Vault、Intune、その他のアプリケーション ロール、サードパーティアプリケーションなどのさまざまなシナリオへのアクセスを管理できます。

グループ用の PIM では、Microsoft Entra ロール用の PIM や Azure リソース用の PIM で使うものと同様のポリシーを使用できます。メンバーシップまたは所有権のアクティブ化に対する承認の要求、多要素認証 (MFA) の適用、正当な理由の要求、最大アクティブ化時間の制限などを行うことができます。 グループ向けの PIM の各グループには、メンバーシップのアクティブ化用とグループ内の所有権のアクティブ化用の 2 つのポリシーがあります。 2023 年 1 月まで、グループの PIM 機能は "特権アクセス グループ" と呼ばれていました。

注

Microsoft Entra ロールへの昇格に使われるグループの場合、資格のあるメンバーの割り当てに対して承認プロセスを要求することをお勧めします。 承認なしでアクティブ化できる割り当ては、ユーザーの資格情報をリセットし、そのユーザーに代わって割り当てをアクティブ化できる可能性がある、特権の低い管理者からのセキュリティ リスクに対して脆弱になる可能性があります。

ロールの昇格に使用されるグループが [ロール割り当て可能](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)として作成されていることを確認します。 ロールを割り当て可能なグループのメンバーと所有者の資格情報を変更するには、少なくとも特権認証管理者ロールを割り当てる必要があります。

### グループと所有権無効化のためのPIM

Microsoft Entra ID では、グループの最後の (アクティブな) 所有者を削除することはできません。 たとえば、アクティブな所有者 A と資格のある所有者 B を持つグループがあるとします。ユーザー B が PIM で所有権をアクティブ化した後、後でユーザー A がグループまたはテナントから削除された場合、ユーザー B の所有権の非アクティブ化は成功しません。

PIM は、ユーザー B の所有権を最大 30 日間非アクティブ化しようとします。 別のアクティブな所有者 C がグループに追加されると、非アクティブ化は成功します。 非アクティブ化が 30 日後に失敗した場合、PIM はユーザー B の所有権の非アクティブ化の試行を停止し、ユーザー B は引き続きアクティブな所有者になります。

### Microsoft Entra のロール割り当て可能なグループとは

Microsoft Entra ID を使用すると、Microsoft Entra セキュリティ グループまたは Microsoft 365 グループを Microsoft Entra ロールに割り当てることができます。 これは、ロール割り当て可能として作成されたグループでのみ可能です。

Microsoft Entra ロール割り当て可能グループの詳細については、「[Microsoft Entra ID でロールを割り当て可能なグループを作成する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-create-eligible)」を参照してください。

ロール割り当て可能なグループは、ロール割り当て不可能なグループと比較して、追加の保護の恩恵を受けます。

- **ロール割り当て可能なグループ** - このグループは、全体管理者、特権ロール管理者、またはグループ所有者のみが管理できます。 また、他のユーザーは、資格のあるメンバーやアクティブ化していない所有者を含め、グループのメンバーと所有者の資格情報を変更することはできません。 この機能を利用することで、管理者が要求と承認の手順を行わずに高い特権ロールに昇格するのを防ぎます。
- 役割**を割り当てできないグループ** (さまざまな Microsoft Entra ロール) は、Exchange 管理者、グループ管理者、ユーザー管理者など、これらのグループを管理できます。 また、さまざまな Microsoft Entra ロールによって、認証管理者、ヘルプデスク管理者、ユーザー管理者など、グループの (アクティブな) メンバーであるユーザーの資格情報を変更できます。

Microsoft Entra の組み込みロールとそのアクセス許可の詳細については、「[Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Microsoft Entra ロール割り当て可能なグループ機能は、Microsoft Entra Privileged Identity Management (Microsoft Entra PIM) の一部ではありません。 ライセンスの詳細については、「 [Microsoft Entra ID ガバナンス licensing fundamentals」を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### ロール割り当て可能なグループとグループ向けの PIM の関係

Microsoft Entra ID のグループは、ロール割り当て可能またはロール割り当て不可として分類できます。 さらに、グループ向けの Microsoft Entra Privileged Identity Management (PIM) で使用するために、どのグループも有効または無効にすることができます。 これらは、グループの独立したプロパティです。 グループの PIM では、任意の Microsoft Entra セキュリティ グループと任意の Microsoft 365 グループ (動的メンバーシップ グループとオンプレミス環境から同期されたグループを除く) を有効にすることができます。 グループは、グループ向けの PIM で有効にするためにロール割り当て可能なグループである必要はありません。

グループに Microsoft Entra のロールを割り当てるには、そのグループがロール割り当て可能である必要があります。 Microsoft Entra ロールをグループに割り当てるつもりはなく、グループが機密性の高いリソースにアクセスできる場合でも、グループをロール割り当て可能として作成することを検討することをお勧めします。 ロール割り当て可能なグループにすると、保護が強化されるためです。上記のセクション「Microsoft Entra ロール割り当て可能グループとは」を参照してください。

重要

2023 年 1 月までは、すべての特権アクセス グループ (このグループ向け PIM 機能の旧称) をロール割り当て可能なグループにする必要がありました。 この制限は現在削除されています。 そのため、PIM でテナントごとに 500 を超えるグループを有効にできるようになりましたが、ロール割り当て可能なグループは最大 500 個までです。

### ユーザーのグループを Microsoft Entra ロールの対象にする

ユーザーのグループを Microsoft Entra ロールの対象にするには、次の 2 つの方法があります。

1. グループに対してユーザーのアクティブな割り当てを行ってから、そのグループをアクティブ化の対象としてロールに割り当てます。
2. グループに対してロールのアクティブな割り当てを行い、グループ メンバーシップの対象となるユーザーを割り当てます。

SharePoint、Exchange、または Microsoft Purview ポータルのアクセス許可を持つ Microsoft Entra ロールへの Just-In-Time アクセス権を持つユーザーのグループ (Exchange 管理者ロールなど) を提供するには、グループに対するユーザーのアクティブな割り当てを行い、アクティブ化の対象としてグループをロールに割り当てる必要があります (上記のオプション 1)。 ロールへのグループのアクティブな割り当てを行い、代わりにグループ メンバーシップの対象となるユーザーを割り当てる場合は、ロールのすべてのアクセス許可がアクティブ化され、使用できる状態になるまでにかなりの時間がかかる場合があります。

言い換えると、アクティブ化の遅延を回避するには、PIM for Groups ではなく [Microsoft Entra ロールに PIM](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user) を使用して、SharePoint、Exchange、または Microsoft Purview ポータルへの Just-In-Time アクセスを提供します。 詳細については、「 [PIM でのロールのアクティブ化後に SharePoint または OneDrive にアクセスするときのエラー」](https://learn.microsoft.com/ja-jp/sharepoint/troubleshoot/administration/access-denied-to-pim-user-accounts)を参照してください。

### 特権的なアイデンティティ管理とグループのネスト

Microsoft Entra ID では、役割が割り当てられるグループの中に他のグループを入れ子にすることはできません。 詳細については、「[Microsoft Entra グループを使用してロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)」をご覧ください。 これはアクティブなメンバーシップに適用されます。1 つのグループを、ロール割り当て可能な別のグループのアクティブなメンバーにすることはできません。

いずれかのグループがロール割り当て可能な場合でも、1 つのグループを別のグループの適格なメンバーにすることができます。

ユーザーがグループ A のアクティブなメンバーであり、グループ A がグループ B の対象メンバーである場合、このユーザーはグループ B のメンバーシップをアクティブにできます。このアクティブ化は、アクティブ化を要求したユーザーに対してのみ行われます。グループ A 全体がグループ B のアクティブ メンバーになるという意味ではありません。

### Privileged Identity Management とアプリのプロビジョニング

グループが [アプリ のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/)用に構成されている場合、グループ メンバーシップのアクティブ化により、SCIM プロトコルを使用してアプリケーションへのグループ メンバーシップ (および以前にプロビジョニングされていない場合はユーザー アカウント自体) のプロビジョニングがトリガーされます。

PIM でグループ メンバーシップがアクティブ化された直後にプロビジョニングをトリガーする機能があります。 プロビジョニングの構成は、アプリケーションによって異なります。 一般に、少なくとも 2 つのグループをアプリケーションに割り当てることをお勧めします。 アプリケーション内のロールの数に応じて、追加の "特権グループ" を定義することを選択できます。

| グループ | 目的 | メンバー | グループのメンバーシップ | アプリケーションで割り当てられたロール |
| --- | --- | --- | --- | --- |
| 全ユーザーグループ | アプリケーションへのアクセスを必要とするすべてのユーザーが、常にアプリケーションにプロビジョニングされるようにします。 | アプリケーションにアクセスする必要があるすべてのユーザー。 | アクティブ | 権限なし、または権限の低いロール |
| 特権グループ | アプリケーションの特権役割へのジャストインタイムアクセスを提供します。 | アプリケーション内の特権ロールに、ジャスト イン タイム アクセスする必要があるユーザー。 | 適格 | 特権ロール |

**重要な考慮事項**

- ユーザーがアプリケーションにプロビジョニングされるまでにかかる時間
    - Microsoft Entra ID において、Privileged Identity Management (PIM) を使用してグループ メンバーシップをアクティブ化することなく、ユーザーをグループに追加する場合:
        - グループ メンバーシップは、次の同期サイクルの間に、アプリケーションでプロビジョニングされます。 同期サイクルは 40 分ごとに実行されます。
    - ユーザーが Microsoft Entra PIM でグループ メンバーシップをアクティブにするとき:
        - グループ メンバーシップは 2 から 10 分で設定されます。 一度に行われる要求の数が多い場合、10 秒あたり 5 要求に調整されます。
        - 特定のアプリケーションのグループ メンバーシップをアクティブにしようとするユーザーのうち、10 秒の期間内の最初の 5 人については、2 から 10 分以内にアプリケーションでグループ メンバーシップがプロビジョニングされます。
        - 特定のアプリケーションのグループ メンバーシップをアクティブにしようとするユーザーのうち、10 秒の期間内の 6 人目以降については、次の同期サイクルの間にアプリケーションでグループ メンバーシップがプロビジョニングされます。 同期サイクルは 40 分ごとに実行されます。 エンタープライズアプリケーションごとにスロットリングの制限が設けられています。
- ユーザーがターゲット アプリケーションで必要なグループにアクセスできない場合は、PIM ログとプロビジョニング ログを確認して、グループ メンバーシップが正常に更新されたことを確認します。 ターゲット アプリケーションの設計方法によっては、グループ メンバーシップがアプリケーションで有効になるには、追加の時間がかかる場合があります。
- [Azure Monitor を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics)使用すると、エラーのアラートを作成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/groups-activate-roles"} -->
## Privileged Identity Management でグループのメンバーシップまたは所有権をアクティブにする - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-activate-roles
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) でグループ メンバーシップまたは所有権を有効にする方法について説明します。

### 概要

Microsoft Entra ID で Privileged Identity Management (PIM) を使用して、グループの Just-In-Time メンバーシップまたは Just-In-Time 所有権を持つことができます。

この記事は、PIM でグループ メンバーシップまたは所有権をアクティブにする資格のあるメンバーまたは所有者を対象としています。

重要

グループのメンバーシップまたは所有権がアクティブ化されると、アクティブな割り当てが、Microsoft Entra PIM によって一時的に追加されます。 Microsoft Entra PIM は、アクティブな割り当て (グループのメンバーまたは所有者としてのユーザーの追加) を数秒で作成します。 非アクティブ化 (手動またはアクティブ化時間の有効期限) が発生すると、Microsoft Entra PIM はユーザーのグループ メンバーシップまたは所有権も数秒以内に削除します。

アプリケーションは、グループ メンバーシップに基づいてユーザーにアクセスを提供する場合があります。 状況によっては、ユーザーがグループに追加されたか、グループから削除されたという事実がアプリケーション アクセスにすぐに反映されない場合があります。 アプリケーションが以前にユーザーがグループのメンバーではないという事実をキャッシュした場合、ユーザーがアプリケーションに再度アクセスしようとすると、アクセスが提供されない可能性があります。 同様に、アプリケーションが以前にユーザーがグループのメンバーであるという事実をキャッシュした場合、グループ メンバーシップが非アクティブ化されても、ユーザーは引き続きアクセスできる可能性があります。 特定の状況は、アプリケーションのアーキテクチャによって異なります。 一部のアプリケーションでは、サインアウトしてサインインし直すのが、アクセスの追加または削除に役立つ場合があります。

### グループと所有権無効化のためのPIM

Microsoft Entra ID では、グループの最後の (アクティブな) 所有者を削除することはできません。 たとえば、アクティブな所有者 A と資格のある所有者 B を持つグループがあるとします。ユーザー B が PIM で所有権をアクティブ化した後、後でユーザー A がグループまたはテナントから削除された場合、ユーザー B の所有権の非アクティブ化は成功しません。

PIM は、ユーザー B の所有権を最大 30 日間非アクティブ化しようとします。 別のアクティブな所有者 C がグループに追加されると、非アクティブ化は成功します。 非アクティブ化が 30 日後に失敗した場合、PIM はユーザー B の所有権の非アクティブ化の試行を停止し、ユーザー B は引き続きアクティブな所有者になります。

### ロールを有効化する

グループ メンバーシップまたは所有権を取得する必要がある場合は、PIM の **[自分のロール** ] ナビゲーション オプションを使用してアクティブ化を要求できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**特権アイデンティティ管理**&gt;**自分のロール**&gt;**グループ** にアクセスします。

    注

    この [短いリンク](https://aka.ms/pim) を使用して、[自分の **ロール** ] ページを直接開くこともできます。
3. **有資格の割り当て** ブレードで、自分がメンバーシップまたは所有権を持つグループの一覧を確認します。

    [Image: 対象となるメンバーシップまたは所有権を持つグループの一覧のスクリーンショット。]
4. 有効にしたい対象の割り当てで、**アクティブ化**を選択してください。
5. グループの設定によっては、多要素認証または別の形式の資格情報の入力を求められる場合があります。
6. 必要に応じて、カスタムのアクティブ化開始時刻を指定します。 メンバーシップまたは所有権は、選択した時間が経過した後にのみアクティブになります。
7. グループの設定によっては、アクティブ化の正当な理由が必要になる場合があります。 必要に応じて、[ **理由** ] ボックスに理由を入力します。

    [Image: [理由] ボックスに理由を入力する場所のスクリーンショット。]
8. **[アクティブ化] を選択します**。

ロールでアクティブ化に [承認が必要な](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow) 場合は、要求が承認待ちであることを通知する Azure 通知がブラウザーの右上隅に表示されます。

### 要求の状態を表示する

保留中のアクティブ化要求の状態を表示することができます。 要求が他のユーザーの承認を受ける場合に重要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**My requests**&gt;**Groups** を参照します。
3. 要求の一覧を確認します。

    [Image: 要求の一覧を確認する場所のスクリーンショット。]

### 保留中の要求をキャンセルする

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**My requests**&gt;**Groups** を参照します。

    [Image: キャンセルする要求を選択する場所のスクリーンショット。]
3. 取り消す要求については、[キャンセル] を選択 **します**。

**[キャンセル**] を選択すると、要求は取り消されます。 ロールをもう一度アクティブにするには、新しいアクティブ化要求を送信する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/groups-approval-workflow"} -->
## グループのメンバーと所有者のアクティブ化要求を承認する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-approval-workflow
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra Privileged Identity Management (PIM) で、グループ メンバーと所有者のアクティブ化要求を承認する方法について説明します。

### 概要

Privileged Identity Management (PIM) と Microsoft Entra ID を使うと、グループのメンバーシップと所有権のアクティブ化には承認を必須とするように構成できます。 Microsoft Entra 組織のユーザーまたはグループを、代理承認者として選ぶこともできます。

グループごとに 2 人以上の承認者を選択します。 代理承認者は、要求を承認するまでに 24 時間あります。 要求が 24 時間以内に承認されない場合、有資格ユーザーは新しい要求を再送信する必要があります。 24 時間の承認時間枠は構成できません。

この記事の手順に従って、グループのメンバーシップまたは所有権の申請を承認または拒否してください。

### 保留中の要求を表示する

Azure リソース ロール要求が、代理承認者であるあなたの承認を待っている状態になると、あなたに電子メール通知が届きます。 保留中の要求は、Privileged Identity Management で表示できます。

1. 承認者として設定されているユーザーとして [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **ID ガバナンス**&gt;**特権 ID 管理**&gt;**要求を承認**&gt;**グループ**を参照します。
3. **[ロールのアクティブ化に関する要求]** セクションに、承認が保留中の要求の一覧が表示されます。

    [Image: ロールのアクティブ化に関する要求を示すスクリーンショット。]

### 要求の承認

注意

承認者は、自分のロールのアクティブ化要求を承認できません。 さらに、サービス プリンシパルは要求の承認を許可されません。

1. 承認する要求を見つけて選択し、 **[承認]** を選択します。
2. **[理由]** ボックスに、業務上の正当な理由を入力します。
3. **[確認]** を選択します。 この承認によって、Azure 通知が生成されます。

    [Image: 承認によって生成される Azure 通知のスクリーンショット。]

### 要求を拒否する

1. 拒否する要求を見つけて選択し、 **[拒否]** を選択します。
2. **[理由]** ボックスに、業務上の正当な理由を入力します。
3. **[確認]** を選択します。 この拒否によって、Azure 通知が生成されます。

### ワークフロー通知

ワークフロー通知に関するいくつかの情報を次に示します。

- 承認者は、グループ割り当ての要求のレビューが保留中とき、メールで通知を受け取ります。 電子メール通知には、承認者が承認または拒否できる、要求への直接リンクが含まれています。
- 要求は、承認または拒否した最初の承認者によって解決されます。
- 承認者が要求に応答すると、すべての承認者にその動作が通知されます。

注意

管理者は、承認されたユーザーをアクティブにしないほうがよいと考えた場合、Privileged Identity Management でアクティブなグループ割り当てを削除できます。 リソース管理者は、承認者でない限り、保留中の要求の通知を受け取りません。 ただし、Privileged Identity Management で保留中の要求を表示することにより、すべてのユーザーの保留中の要求を表示およびキャンセルできます。

### トラブルシューティング

トラブルシューティングのヒントをこちらに示します。

#### ロールをアクティブにした後、アクセス許可が付与されない

Privileged Identity Management でロールをアクティブ化しても、アクティブ化が、特権ロールを必要とするすべてのポータルに直ちには伝播されない可能性があります。 場合によっては、変更が伝達されても、ポータルにおける Web キャッシュが原因で、変更がすぐに有効にならないことがあります。

アクティブ化が遅れている場合:

1. Microsoft Entra 管理センターからサインアウトし、再度サインインします。
2. Privileged Identity Management で、自分がそのロールのメンバーとして一覧表示されていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/groups-assign-member-owner"} -->
## Privileged Identity Management でグループの資格を割り当てる - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-assign-member-owner
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management でグループの資格を割り当てる方法について説明します。

### 概要

Microsoft Entra ID では、Privileged Identity Management (PIM) を使って、グループ内の Just-In-Time メンバーシップまたはグループの Just-In-Time 所有権を管理できます。

メンバーシップまたは所有権を割り当てると、割り当ては次のようになります。

- 5 分未満の期間は割り当てることができません。
- 割り当てられてから 5 分以内に削除することはできません。

注

グループの PIM のメンバーシップまたは所有権の対象となるすべてのユーザーは、Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス ライセンスを持っている必要があります。 詳細については、「 [Privileged Identity Management を使用するためのライセンス要件」を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### Permissions

MICROSOFT ENTRA PIM でグループ メンバーシップまたは所有権を管理するには、適切なアクセス許可が必要です。

- ロール割り当て可能なグループの所有権を管理するには、特権ロール管理者やグローバル管理者などの`microsoft.directory/groupsAssignableToRoles/owners/update`アクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。
- ロール割り当て可能なグループのメンバーシップを管理するには、特権ロール管理者やグローバル管理者などの`microsoft.directory/groupsAssignableToRoles/members/update`アクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。
- ロールが割り当てできないグループの所有権を管理するには、グループ管理者や ID ガバナンス管理者などの`microsoft.directory/groups/owners/update`アクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。
- ロールが割り当てできないグループのメンバーシップを管理するには、グループ管理者や ID ガバナンス管理者などの`microsoft.directory/groups/members/update`アクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。

管理者のロールの割り当ては、ディレクトリ レベルまたは管理単位レベルでスコープを設定できます。 組み込みロールとカスタム Microsoft Entra ロールがサポートされています。

Privileged Identity Managementでは、`microsoft.directory/groups.security/`または`microsoft.directory/groups.unified/`で始まるアクセス許可はサポートされていません。 代わりに、 `microsoft.directory/groups/` で始まるアクセス許可を使用してください。

Privileged Identity Managementは、制限付き管理管理単位 (RMAU) のグループをサポートしていません。

### グループの所有者またはメンバーを割り当てる

ユーザーをグループの資格のあるメンバーまたは所有者にするには、次の手順に従います。

注

管理者とグループ所有者は、グループ エクスペリエンスやその他のインターフェイスを使用してグループを管理し、MICROSOFT ENTRA PIM で行われた変更をオーバーライドできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Groups** を参照します。
3. ここでは、グループの PIM で既に有効になっているグループを表示できます。

    [Image: グループに対して PIM が既に有効になっているグループを表示する場所のスクリーンショット。]
4. 管理する必要があるグループを選びます。
5. **割り当て**を選択します。
6. **[対象の割り当て**] ブレードと **[アクティブな割り当て**] ブレードを使用して、選択したグループの既存のメンバーシップまたは所有権の割り当てを確認します。

    [Image: 選択したグループの既存のメンバーシップまたは所有権の割り当てを確認する場所のスクリーンショット。]
7. [ **割り当ての追加] を選択します**。
8. [ **ロールの選択**] で、[ **メンバー** ] と **[所有者** ] を選択してメンバーシップまたは所有権を割り当てます。
9. グループに対する資格を与えるメンバーまたは所有者を選びます。

    [Image: グループの対象にするメンバーまたは所有者を選択する場所のスクリーンショット。]
10. [ **次へ**] を選択します。
11. [ **割り当ての種類** ] ボックスの一覧で、[ **対象]** または **[アクティブ**] を選択します。 Privileged Identity Management には、割り当ての種類が 2 つあります。

    - 資格のある割り当てでは、メンバーまたは所有者はロールを使用するためにアクティベーションを行う必要があります。 アクティブ化では、多要素認証 (MFA) の提供、業務上の正当な理由の提供、または指定された承認者への承認の要求が必要になる場合もあります。

    重要

    Microsoft Entra ロールへの昇格に使われるグループの場合、資格のあるメンバーの割り当てに対して承認プロセスを要求することをお勧めします。 承認せずにアクティブ化できる割り当てでは、アクセス許可を持つ別の管理者が対象ユーザーのパスワードをリセットしてしまうというセキュリティリスクに対して脆弱なままである可能性があります。

    - アクティブな割り当てでは、メンバーはロールを使うためにアクティブ化を実行する必要はありません。 アクティブとして割り当てられたメンバーや所有者は、常にロールに関連付けられた特権を持ちます。
12. 割り当てが永続的である (永続的に適格または永続的に割り当てられる) 場合は、[ **永続的** ] チェック ボックスをオンにします。 グループの設定によっては、チェック ボックスが表示されない場合や編集できない場合があります。 詳細については、「 [Privileged Identity Management」の「グループの PIM 設定の構成」を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-role-settings#assignment-duration) 参照してください。

    [Image: 割り当ての追加の設定を構成する場所のスクリーンショット。]
13. **割り当て**を選択します。

### 既存のロールの割り当てを更新または削除する

既存のロールの割り当てを更新または削除するには、次の手順を実行します。

注

管理者とグループ所有者は、グループ エクスペリエンスやその他のインターフェイスを使用してグループを管理し、MICROSOFT ENTRA PIM で行われた変更をオーバーライドできます。

1. [必要なアクセス許可](https://entra.microsoft.com)を持つロールでMicrosoft Entra 管理センターにサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Groups** を参照します。
3. ここでは、グループの PIM で既に有効になっているグループを表示できます。

    [Image: グループに対して PIM が既に有効になっているグループを表示する場所のスクリーンショット。]
4. 管理する必要があるグループを選びます。
5. **割り当て**を選択します。
6. **[対象の割り当て**] ブレードと **[アクティブな割り当て**] ブレードを使用して、選択したグループの既存のメンバーシップまたは所有権の割り当てを確認します。

    [Image: 選択したグループの既存のメンバーシップまたは所有権の割り当てを確認する場所のスクリーンショット。]
7. [ **更新** ] または **[削除] を** 選択して、メンバーシップまたは所有権の割り当てを更新または削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/groups-audit"} -->
## Privileged Identity Management でのグループ割り当ての監査アクティビティ履歴 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-audit
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) でグループ割り当てのアクティビティと監査アクティビティ履歴を表示します。

### 概要

Privileged Identity Management (PIM) で組織のグループを操作する場合は、Microsoft Entra グループ メンバーシップまたは所有権の変更に関するアクティビティ、アクティブ化、監査履歴を表示できます。

注

組織で、[Azure Lighthouse](https://learn.microsoft.com/ja-jp/azure/lighthouse/overview) を使用するサービス プロバイダーに管理機能を外部委託している場合、そのサービス プロバイダーによって承認されているロールの割り当てはここに表示されません。

Privileged Identity Management でグループの監査履歴を表示するには、以下の手順を実行します。

### リソースの監査履歴を表示する

**[リソースの監査]** には、PIM のグループに関連付けられたすべてのアクティビティが表示されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Groups** を参照します。
3. 監査履歴を表示するグループを選びます。
4. **[リソースの監査]** を選択します。

    [Image: [リソースの監査] を選ぶ位置を示すスクリーンショット。]
5. 定義済みの日付またはカスタムの範囲を使用して履歴をフィルターします。

### 自分の監査を表示する

**マイオーディット**を使用すると、PIMのグループにおける自分のロールアクティビティを確認できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Groups** を参照します。
3. 監査履歴を表示するグループを選びます。
4. **[自分の監査]** を選択します。

    [Image: [自分の監査] を選ぶ位置を示すスクリーンショット。]
5. 定義済みの日付またはカスタムの範囲を使用して履歴をフィルターします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/groups-discover-groups"} -->
## グループを Privileged Identity Management に取り込む - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-discover-groups
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: グループを Privileged Identity Management に取り込む方法について説明します。

### 概要

Microsoft Entra ID では、Privileged Identity Management (PIM) を使って、グループ内の Just-In-Time メンバーシップまたはグループの Just-In-Time 所有権を管理できます。 グループを使用して、Microsoft Entra ロール、Azure ロール、およびその他のさまざまなシナリオにアクセスできるようにします。 PIM で Microsoft Entra グループを管理するには、そのグループを PIM の管理下に置く必要があります。

### 管理するグループを識別する

開始する前に、Microsoft Entra セキュリティ グループまたは Microsoft 365 グループが必要です。 Microsoft Entra ID でのグループ管理の詳細については、「[Microsoft Entra グループとグループ メンバーシップを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)」を参照してください。

オンプレミス環境から同期された動的グループとグループは、グループの PIM では管理できません。

グループを Microsoft Entra PIM に追加するには、適切なアクセス許可が必要です。 ロール割り当て可能なグループの場合は、特権ロール管理者やグローバル管理者など、`microsoft.directory/groupsAssignableToRoles/owners/update`と`microsoft.directory/groupsAssignableToRoles/members/update`のアクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。 ロールを割り当てできないグループの場合は、グループ管理者や ID ガバナンス管理者などの`microsoft.directory/groups/owners/update`と`microsoft.directory/groups/members/update`のアクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。 管理者のロールの割り当ては、ディレクトリ レベルまたは管理単位レベルでスコープを設定できます。 組み込みロールとカスタム Microsoft Entra ロールがサポートされています。

Privileged Identity Managementでは、`microsoft.directory/groups.security/`または`microsoft.directory/groups.unified/`で始まるアクセス許可はサポートされていません。 代わりに、 `microsoft.directory/groups/` で始まるアクセス許可を使用してください。

Privileged Identity Managementは、制限付き管理管理単位 (RMAU) のグループをサポートしていません。

注

管理者とグループ所有者は、グループ エクスペリエンスやその他のインターフェイスを使用してグループを管理し、MICROSOFT ENTRA PIM で行われた変更をオーバーライドできます。

1. 前のセクションで説明したように、グループを管理するためのアクセス許可を持つ Microsoft Entra ロールで [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Groups** を参照します。
3. グループに対して PIM が既に有効になっているグループを表示します。

    [Image: 既に PIM for Groups が有効になっているグループを表示する場所のスクリーンショット。]
4. **[グループを検出する]** を選択し、PIM で管理下に置くグループを選択します。

    [Image: PIM で管理下に置くグループを選択する場所のスクリーンショット。]
5. **[グループの管理]** を選択し、**[OK]** を選択します。
6. **[グループ]** を選択して、グループの PIM で有効になっているグループの一覧に戻ります。

または、[グループ] ウィンドウを使用して、[Privileged Identity Management] の下にグループを表示することもできます。

[Image: PIM で管理下に置くグループを選択できる [グループ] ペインのスクリーンショット。]

重要

グループは、いったん管理されると、管理から除外することはできません。 これにより、別のリソース管理者が PIM 設定を削除することを防ぎます。 Microsoft Entra ID からグループが削除された場合、グループが **PIM for Groups** オプションから削除されるまでに最大 24 時間かかる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/groups-renew-extend"} -->
## グループ割り当ての PIM を延長または更新する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-renew-extend
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: グループ割り当ての PIM を延長または更新する方法について説明します。

### 概要

Microsoft Entra ID の Privileged Identity Management (PIM) には、グループのメンバーシップと所有権へのアクセスと割り当てのライフサイクルを管理するためのコントロールが用意されています。 管理者は、グループのメンバーシップと所有権の開始と終了の日時プロパティを割り当てることができます。 割り当ての終了日時が近づくと、Privileged Identity Management は、影響を受けるユーザーまたはグループに電子メール通知を送信します。 適切なアクセスが維持されることを保証するためにリソースの管理者にも電子メール通知を送信します。 アクセスが延長されなかった場合でも、割り当てを更新して、最大 30 日間、期限切れの状態のままで表示できます。

### 延長および更新できるのは誰ですか

グループを管理するアクセス許可を持つユーザーのみが、期限付きのグループ メンバーシップまたは所有権の割り当てを延長または更新できます。 担当者は、有効期限が近づいている割り当ての延長を要求し、既に期限切れの割り当てを更新するよう要求できます。

- ロール割り当て可能なグループの所有権を管理するには、特権ロール管理者やグローバル管理者などの`microsoft.directory/groupsAssignableToRoles/owners/update`アクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。
- ロール割り当て可能なグループのメンバーシップを管理するには、特権ロール管理者やグローバル管理者などの`microsoft.directory/groupsAssignableToRoles/members/update`アクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。
- ロールが割り当てできないグループの所有権を管理するには、グループ管理者や ID ガバナンス管理者などの`microsoft.directory/groups/owners/update`アクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。
- ロールが割り当てできないグループのメンバーシップを管理するには、グループ管理者や ID ガバナンス管理者などの`microsoft.directory/groups/members/update`アクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。

管理者のロールの割り当ては、ディレクトリ レベルまたは管理単位レベルでスコープを設定できます。 組み込みロールとカスタム Microsoft Entra ロールがサポートされています。

Privileged Identity Managementでは、`microsoft.directory/groups.security/`または`microsoft.directory/groups.unified/`で始まるアクセス許可はサポートされていません。 代わりに、 `microsoft.directory/groups/` で始まるアクセス許可を使用してください。

Privileged Identity Managementは、制限付き管理管理単位 (RMAU) のグループをサポートしていません。

注

管理者とグループ所有者は、グループ エクスペリエンスやその他のインターフェイスを使用してグループを管理し、MICROSOFT ENTRA PIM で行われた変更をオーバーライドできます。

### 通知の送信タイミング

Privileged Identity Management から、期限切れになるグループの PIM の割り当ての管理者および影響を受けるユーザーに電子メール通知が送信されます。

- 有効期限切れ前の 14 日以内
- 有効期限の切れる前日
- 割り当ての有効期限が切れたとき

管理者は、ユーザーまたはグループが期限が迫っているか期限切れになっている割り当ての延長または更新を要求したときに通知を受信します。 1 人の管理者が要求を解決すると、すべての管理者と要求元のユーザーにその承認または拒否が通知されます。

### グループの割り当てを延長する

次の手順で、グループのメンバーシップまたは所有権の割り当ての延長または更新の要求、解決、または管理のプロセスの概要を示します。

#### 有効期限が切れた割り当ての自動延長

グループのメンバーシップまたは所有権が割り当てられているユーザーは、グループの **[割り当て]** ページにある **[対象]** または **[アクティブ]** タブから直接、有効期限が迫っているグループの割り当てを延長できます。 ユーザーまたはグループは、14 日以内に期限切れになる対象およびアクティブの割り当ての延長を要求できます。

[Image: 有効期限が迫っている割り当てを自動延長する場所を示すスクリーンショット。]

割り当て終了日時が 14 日以内になると、 **[延長]** コマンドが使用できるようになります。 グループ割り当ての延長を要求するには、 **[延長]** を選択して、要求フォームを開きます。

[Image: [理由] ボックスと詳細を含む、グループの割り当てを延長するペインの場所を示すスクリーンショット。]

注

拡張機能が必要な理由と、拡張機能を付与する必要がある期間 (この情報がある場合) の詳細を含めます。

管理者は、延長要求の確認を求めるメール通知を受信します。 延長の要求が既に送信されている場合は、Azure の通知がポータルに表示されます。

要求の状態の表示またはキャンセルを行うには、グループの割り当ての **[保留中の要求]** ページを開きます。

[Image: キャンセルするためのリンクを表示する [保留中のリクエスト] ページのスクリーンショット。]

#### 管理者が承認した拡張機能

ユーザーまたはグループがグループの割り当ての延長を求める要求を送信すると、管理者は、元の割り当ての詳細と要求の理由を含む電子メール通知を受信します。 通知には、管理者が要求を承認または拒否するための直接リンクが含まれています。

管理者は、電子メールからのリンクに従うだけでなく、Privileged Identity Management 管理ポータルに移動し、左側のウィンドウで [要求の承認] を選択することで **、要求を承認** または拒否できます。

[Image: 承認または拒否する要求とリンクを一覧表示する [要求の承認] ページのスクリーンショット。]

管理者が **[承認** ] または **[拒否**] を選択すると、要求の詳細と、監査ログの業務上の正当な理由を提供するフィールドが表示されます。

[Image: [要求元の理由]、[割り当ての種類]、[開始時刻]、[終了時刻]、[理由] を含むグループの割り当て要求を承認する場所を示すスクリーンショット。]

リソース管理者は、グループの割り当ての延長を求める要求を承認するとき、新しい開始日、終了日、および割り当ての種類を選択できます。 管理者が特定のタスク を完了するためのアクセス制限 を指定する (たとえば 1 日だけ延長する) 場合は、割り当ての種類の変更が必要になることがあります。 この例では、管理者は、割り当ての種類を**有資格**から**アクティブ**に変更できます。 つまり、アクティブ化の必要がないアクセスを要求者に提供できます。

#### 管理者による延長

グループに割り当てられているユーザーがグループの割り当ての延長を要求しない場合、管理者がユーザーの代わりに割り当てを延長できます。 グループ割り当ての管理拡張機能では承認は必要ありませんが、割り当てが延長された後、他のすべての管理者に通知が送信されます。

グループの割り当てを延長するには、Privileged Identity Management の割り当てビューを参照します。 延長する必要がある割り当てを探します。 そして、[アクション] 列の**延長**をクリックします。

[Image: 延長するためのリンクが付いた対象グループ割り当てが一覧表示されている [割り当て] ページのスクリーンショット。]

### グループの割り当てを更新する

有効期限が切れたグループの割り当てを更新するプロセスは、延長の要求のプロセスと概念上は似ていますが、異なります。 割り当てと管理者は、次の手順を使用して、必要な場合に有効期限が切れた割り当てへのアクセスを更新できます。

#### 自己更新

リソースにアクセスできなくなったユーザーは、最大 30 日間分の有効期限が切れた割り当て履歴にアクセスできます。 これを行うには、左側のペインで **[自分のロール]** を参照し、 **[Expired assignments](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/期限切れの割り当て)** タブを選択します。

割り当ての一覧には、**対象の割り当て**が既定で表示されます。 [対象] と [アクティブ] の割り当てを切り替えるには、ドロップダウン メニューを使用します。

一覧のいずれかのグループの割り当ての更新を要求するには、 **[更新]** アクションを選択します。 そして、要求の理由を入力します。 リソース管理者が承認または拒否を決定するのに役立つ他のコンテキストや業務上の正当な理由に加えて、期間を提供すると便利です。

要求が送信されると、リソース管理者に、グループの割り当てを更新するための保留中の要求が通知されます。

#### 管理者による承認

リソース管理者は、メール通知内のリンクから更新要求にアクセスできます。または Microsoft Entra 管理センター から Privileged Identity Management にアクセスし、左側のペインの **[申請の承認]** を選んでアクセスできます。

管理者が **[承認]** または **[拒否]** を選択すると、要求の詳細が、監査ログ用の業務上の正当な理由を入力するフィールドと共に表示されます。

リソース管理者は、グループの割り当てを更新を求める要求を承認するとき、新しい開始日、終了日、および割り当ての種類を入力する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/groups-role-settings"} -->
## グループの PIM 設定を構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-role-settings
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: グループの PIM の設定を構成する方法について説明します 。

### 概要

Microsoft Entra ID のグループ向け Privileged Identity Management (PIM) では、ロール設定によりメンバーシップまたは所有権の割り当てプロパティが定義されます。 これらのプロパティには、多要素認証とアクティブ化の承認要件、割り当ての最大期間、通知設定が含まれます。 この記事では、ロール設定を構成し、承認ワークフローを設定して、特権を昇格させる要求を承認または拒否できるユーザーを指定する方法を説明します。

PIM ポリシーを管理するには、次のアクセス許可が必要です。 ロール割り当て可能なグループの場合は、特権ロール管理者やグローバル管理者など、`microsoft.directory/groupsAssignableToRoles/owners/update`と`microsoft.directory/groupsAssignableToRoles/members/update`のアクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。 ロールを割り当てできないグループの場合は、グループ管理者や ID ガバナンス管理者などの`microsoft.directory/groups/owners/update`と`microsoft.directory/groups/members/update`のアクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブな所有者である必要があります。 管理者のロールの割り当ては、ディレクトリ レベルまたは管理単位レベルでスコープを設定できます。 組み込みロールとカスタム Microsoft Entra ロールがサポートされています。

PIM ポリシーを読み取るために、次のアクセス許可が必要です。 ロール割り当て可能なグループの場合は、特権ロール管理者やグローバル管理者などの`microsoft.directory/groupsAssignableToRoles/owners/update`または`microsoft.directory/groupsAssignableToRoles/members/update`のアクセス許可を持つMicrosoft Entraロールが必要です。または、グループのアクティブまたは有資格の所有者であるか、グループのアクティブまたは有資格のメンバーである必要があります。 ロールを割り当てできないグループの場合は、グループ管理者や ID ガバナンス管理者などの`microsoft.directory/groups/owners/update`と`microsoft.directory/groups/members/update`のアクセス許可を持つMicrosoft Entra ロールが必要です。または、グループのアクティブまたは有資格の所有者であるか、グループのアクティブまたは適格なメンバーである必要があります。 管理者のロールの割り当ては、ディレクトリ レベルまたは管理単位レベルでスコープを設定できます。 組み込みロールとカスタム Microsoft Entra ロールがサポートされています。

Privileged Identity Managementでは、`microsoft.directory/groups.security/`または`microsoft.directory/groups.unified/`で始まるアクセス許可はサポートされていません。 代わりに、 `microsoft.directory/groups/` で始まるアクセス許可を使用してください。

Privileged Identity Managementは、制限付き管理管理単位 (RMAU) のグループをサポートしていません。

注

管理者とグループ所有者は、グループ エクスペリエンスやその他のインターフェイスを使用してグループを管理し、MICROSOFT ENTRA PIM で行われた変更をオーバーライドできます。

ロール設定は、ロールとグループごとに定義されます。 同じグループの同じロール (メンバーまたは所有者) に対するすべての割り当ては、同じロール設定に従います。 あるグループのロール設定は、別のグループのロール設定とは独立しています。 あるロール (メンバー) のロール設定は、別のロール (メンバー) のロール設定とは独立しています。

### ロール設定を更新する

グループ ロールの設定を開くには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Groups** を参照します。
3. ロール設定を構成するグループを選択します。
4. [ **設定] を選択します**。
5. ロール設定を構成する必要があるロールを選択します。 オプションは **[メンバー]** または **[所有者] です**。

    [Image: ロール設定を構成する必要があるロールを選択する場所を示すスクリーンショット。]
6. 現在のロール設定を確認します。
7. [ **編集] を** 選択してロール設定を更新します。

    [Image: [編集] を選択してロール設定を更新する場所を示すスクリーンショット。]
8. [ **更新] を**選択します。

### ロールの設定

このセクションでは、ロール設定のオプションについて説明します。

#### アクティブ化の最大期間

**[アクティブ化の最大期間**] スライダーを使用して、ロールの割り当てのアクティブ化要求が期限切れになる前にアクティブなままになる最大時間 (時間単位) を設定します。 1 から 24 時間の範囲の値を指定できます。

#### アクティブ化の際に多要素認証を要求する

ロールの資格を持つユーザーに対して、アクティブにする前に Microsoft Entra ID の多要素認証機能を使うことで、自分が誰であるかを証明するように要求することができます。 多要素認証は、データやアプリケーションへのアクセスを保護するのに役立ちます。 第 2 の形式の認証を使用して、別のセキュリティのレイヤーが提供されます。

ユーザーは、強力な資格情報で認証を行った場合、またはこのセッションで以前に多要素認証を提供した場合には、多要素認証を求められないことがあります。 目的がユーザーにアクティブ化時に認証を提供させることである場合は、Microsoft Entra 条件付きアクセス認証コンテキストを「アクティブ化時に要求」し、[認証強度](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#on-activation-require-azure-ad-conditional-access-authentication-context)と共に使用することができます。

ユーザーはアクティブ化の際に、マシンへのサインインに使用した方法とは異なる方法を使用して認証する必要があります。 たとえば、ユーザーが Windows Hello for Business を使用してマシンにサインインする場合は、[ **アクティブ化時] を使用し、Microsoft Entra 条件付きアクセス認証コンテキスト** と **認証強度** を要求して、ユーザーがロールをアクティブ化するときに Microsoft Authenticator でパスワードなしのサインインを行うことを要求できます。

この例では、ユーザーは Microsoft Authenticator によるパスワードレス サインインを 1 回提供した後は、このセッションでの次のアクティブ化は別の認証なしで行うことができます。 Microsoft Authenticator でのパスワードレス サインインは、既にトークンの一部になっています。

すべてのユーザーに対して Microsoft Entra ID で多要素認証機能を有効にします。 詳細については、「 [Microsoft Entra 多要素認証の展開を計画](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)する」を参照してください。

#### アクティブ化の際に、Microsoft Entra 条件付きアクセス認証コンテキストを要求する

ロールの資格を持つユーザーに条件付きアクセス ポリシーの要件を満たすことを要求できます。 たとえば、認証強度を通じて適用される特定の認証方法を使用すること、Intune 準拠デバイスからロールを昇格させること、使用条件に準拠することなどをユーザーに要求できます。

この要件を適用するには、条件付きアクセス認証コンテキストを作成します。

1. この認証コンテキストの要件を適用する条件付きアクセス ポリシーを構成します。

    条件付きアクセス ポリシーのスコープには、すべてのユーザーまたはグループ メンバーシップ/所有権の対象ユーザーを含める必要があります。 認証コンテキストとグループを対象とする条件付きアクセス ポリシーを同時に作成しないでください。 アクティブ化中は、ユーザーにはまだグループ メンバーシップがないため、条件付きアクセス ポリシーは適用されません。
2. ロールの PIM 設定で認証コンテキストを構成します。

    [Image: [ロールの編集] 設定 - [メンバー] ページを示すスクリーンショット。]

PIM 設定で **[アクティブ化時に Microsoft Entra 条件付きアクセス認証コンテキストの要求]** が構成されている場合、条件付きアクセス ポリシーは、ユーザーがアクセス要件を満たすために必要な条件を定義します。

つまり、条件付きアクセス管理者やセキュリティ管理者など、条件付きアクセス ポリシーを管理する権限を持つセキュリティ プリンシパルは、要件の変更や削除を行ったり、資格のあるユーザーがグループのメンバーシップ/所有権をアクティブ化するのをブロックしたりすることができます。 条件付きアクセス ポリシーを管理できるセキュリティ プリンシパルは、高度な特権を持つと見なし、それに応じて保護する必要があります。

PIM 設定で認証コンテキストを構成する前に、認証コンテキストの条件付きアクセス ポリシーを作成して有効にします。 バックアップ保護メカニズムとして、PIM 設定で構成された認証コンテキストを対象とする条件付きアクセス ポリシーがテナント内に存在しない場合、グループ メンバーシップ/所有権のアクティブ化時に、Microsoft Entra ID の多要素認証機能が [\[アクティブ化時に多要素認証を必要とする](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-role-settings#on-activation-require-multifactor-authentication) ] 設定が設定されている必要があります。

このバックアップ保護メカニズムは、構成ミスによって、条件付きアクセス ポリシーの作成前に PIM 設定が更新された場合にのみ、保護するように設計されています。 条件付きアクセス ポリシーがオフになっている、レポート専用モードになっている、または資格を持つユーザーがポリシーから除外されている場合、このバックアップ保護メカニズムはトリガーされません。

すべてのグループ メンバーシップまたは所有権のアクティブ化に対して再認証を適用するには、**セッション制御**の下でサインイン頻度を **[毎回**] に設定して、認証コンテキストを対象とする条件付きアクセス ポリシーを構成します。 これにより、ユーザーがアクティブなセッションを持っていても、グループ メンバーシップまたは所有権をアクティブ化するたびに再認証する必要があります。

[Image: Microsoft Entra 条件付きアクセス認証コンテキスト オプションが選択されている [ロール設定の編集] ページを示すスクリーンショット。]

ユーザーが 1 回のアクティブ化を再認証すると、10 分のウィンドウが適用されます。 ユーザーがこのウィンドウ内で別の資格のあるメンバーシップまたは所有権をアクティブ化した場合、再認証を求めるメッセージは表示されません。 10 分間の期間は、Microsoft Entra ロール、Azure リソース ロール、グループの PIM に適用されます。

ユーザーが認証コンテキストで構成された対象のグループ メンバーシップまたは所有権をアクティブ化すると、"条件付きアクセス ポリシーが有効になっており、追加の検証が必要になる場合があります。 クリックして続行します。"その後、ユーザーは、条件付きアクセス ポリシーで定義されている再認証を完了するようにリダイレクトされます。

[Image: 追加の検証を必要とする条件付きアクセス ポリシー バナーが表示された [ロールのアクティブ化] ページを示すスクリーンショット。]

**アクティブ化時には、Microsoft Entra 条件付きアクセス認証コンテキスト**の設定で、ユーザーがグループ メンバーシップ/所有権をアクティブ化するときに満たす必要がある認証コンテキスト要件を定義します。 グループのメンバーシップ/所有権がアクティブ化された後、ユーザーが別のブラウザ セッション、デバイス、または場所を使用して、グループのメンバーシップ/所有権を使用することは妨げられません。

たとえば、ユーザーは Intune 準拠デバイスを使用してグループのメンバーシップ/所有権をアクティブ化できます。 ロールがアクティブ化された後、Intune 非準拠の別のデバイスから同じユーザー アカウントにサインインし、そこから以前にアクティブ化されたグループのメンバーシップ/所有権を使用できます。

このような状況を防ぐために、条件付きアクセス ポリシーのスコープを設定して、資格を持つユーザーに特定の要件を直接適用できます。 たとえば、特定のグループのメンバーシップ/所有権の資格を持つユーザーに対して、常に Intune 準拠のデバイスを使用するように要求できます。

条件付きアクセス認証コンテキストの詳細については、「 [条件付きアクセス: クラウド アプリ、アクション、認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)」を参照してください。

#### アクティブ化の正当な理由を要求する

ユーザーが資格のある割り当てをアクティブにするときに、業務上の正当な理由を入力するように要求できます。

#### アクティブ化時にチケット情報を要求する

ユーザーが資格のある割り当てをアクティブにするときに、サポート チケットを入力するように要求できます。 このオプションは情報のみのフィールドです。 チケット システムの情報との相関関係は適用されません。

#### アクティブ化の承認を必須にする

適格な割り当てのアクティブ化には承認を要求できます。 承認者は、グループのメンバーまたは所有者である必要はありません。 このオプションを使用する場合は、1 人以上の承認者を選択する必要があります。 冗長性を確保するために、少なくとも 2 人の承認者を選択します。 既定の承認者はいません。

承認の詳細については、「 [グループのメンバーと所有者に対する PIM のアクティブ化要求を承認する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-approval-workflow)」を参照してください。

#### 割り当て期間

ロールの設定を構成するときに、割り当ての種類 ("対象" と "アクティブ") ごとに 2 つの割り当て期間オプションから選択できます。 これらのオプションは、Privileged Identity Management でユーザーがロールに割り当てられるときの既定の最大期間になります。

これらの資格のある割り当て期間オプションからいずれかを選択できます。

| 設定 | 説明 |
| --- | --- |
| 永続的な有資格割り当てを許可する | リソース管理者は、永続的に有効な割り当てを指定することができます。 |
| 資格のある割り当てが次の期間後に期限切れになる | リソース管理者は、すべての資格のある割り当てに、開始日と終了日の指定を必須にすることができます。 |

また、これらのアクティブな割り当て期間オプションからいずれかを選択できます。

| 設定 | 説明 |
| --- | --- |
| 永続的なアクティブ割り当てを許可する | リソース管理者は、永続的にアクティブな割り当てを行うことができます。 |
| アクティブな割り当てが次の期間後に期限切れになる | リソース管理者は、すべてのアクティブな割り当てに、開始日と終了日の指定を必須にすることができます。 |

リソース管理者は､終了日時が指定されている割り当てのどれでも更新することができます｡ また、ユーザーはセルフサービス要求を開始して [、ロールの割り当てを延長または更新することもできます](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend)。

#### アクティブな割り当てに多要素認証を要求する

管理者またはグループ所有者が (資格のある割り当てではなく) アクティブな割り当てを作成するときに、多要素認証を行うように要求できます。 ユーザーがロールの割り当てを使うときは、ロールが割り当てられた時点からロール内で既にアクティブになっているため、Privileged Identity Management で多要素認証を強制することはできません。

管理者またはグループ所有者は、強力な資格情報で認証を行った場合、またはこのセッションで以前に多要素認証を提供した場合には、多要素認証を求められないことがあります。

#### 現在の割り当ての正当化を求める

ユーザーが (資格のある割り当てではなく) アクティブな割り当てを作成するときに、業務上の正当な理由を入力するように要求できます。

[**ロールの設定**] ページの [**通知**] タブで、Privileged Identity Management を使用して、通知を受け取るユーザーと受け取る通知をきめ細かく制御できます。 次のようなオプションがあります。

- **メールをオフにする**: 既定の受信者チェックボックスをオフにして他の受信者を削除することで、特定のメールをオフにすることができます。
- **メールを指定したメール アドレスに制限する**: 既定の受信者に送信されるメールをオフにするには、既定の受信者のチェック ボックスをオフにします。 その後で、受信者として他のメール アドレスを追加できます。 複数のメール アドレスを追加する場合は、セミコロン (;) で区切ります。
- **既定の受信者と他の受信者の両方にメールを送信する**: 既定の受信者と別の受信者の両方にメールを送信できます。 既定の受信者のチェック ボックスをオンにし、他の受信者のメール アドレスを追加します。
- **重要なメールのみ**: メールの種類ごとに、重要なメールのみを受信するチェック ボックスをオンにできます。 メールで即時のアクションが求められている場合にのみ、指定された受信者に、Privileged Identity Management によって引き続きメールが送信されます。 たとえば、ユーザーにロールの割り当てを延長するよう求めるメールはトリガーされません。 管理者に延長要求の承認を要求するメールはトリガーされます。

注

Privileged Identity Managementの 1 つのイベントでは、複数の受信者 (担当者、承認者、管理者) に電子メール通知を生成できます。 1 つのイベントごとに送信できる通知の最大数は 1000 です。 受信者の数が 1000 を超える場合、最初の 1000 人の受信者のみが電子メール通知を受信します。 これにより、他の担当者、管理者、または承認者が Microsoft Entra ID と Privileged Identity Management でアクセス許可を使用できなくなります。

### Microsoft Graph を使用してロール設定を管理する

Microsoft Graph で PIM API を使用してグループのロール設定を管理するには、 [unifiedRoleManagementPolicy リソースの種類とその関連メソッドを使用します](https://learn.microsoft.com/ja-jp/graph/api/resources/unifiedrolemanagementpolicy)。

Microsoft Graph では、ロール設定はルールと呼ばれます。 これらは、コンテナー ポリシーを通じてグループに割り当てられます。 グループと各ポリシーを対象とするすべてのポリシーを取得できます。 `$expand` クエリ パラメーターを使用して、関連付けられているルールのコレクションを取得します。 要求の構文は次のとおりです。

```http
GET https://graph.microsoft.com/beta/policies/roleManagementPolicies?$filter=scopeId eq '{groupId}' and scopeType eq 'Group'&$expand=rules
```

Microsoft Graph の PIM API を使用してロール設定を管理する方法の詳細については、「 [ロールの設定と PIM」](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagement-for-groups-api-overview#policy-settings-in-pim-for-groups)を参照してください。 ルールを更新する方法の例については、「 [Microsoft Graph を使用して PIM のルールを更新](https://learn.microsoft.com/ja-jp/graph/how-to-pim-update-rules)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-apis"} -->
## Privileged Identity Management の API の概念 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-apis
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra Privileged Identity Management (PIM) の API について理解するための情報。

### 概要

Microsoft Entra の一部である Privileged Identity Management (PIM) には、3 つのプロバイダーが含まれます。

- Microsoft Entra のロールに対する PIM。
- Azure リソースの PIM。
- グループ向けの PIM。

Microsoft Entra ロール用 PIM とグループ用 PIM での割り当ては、Microsoft Graph を使って管理できます。 Azure リソース用 PIM での割り当ては、Azure Resource Manager API を使って管理できます。 この記事では、Privileged Identity Management の API を使用するための重要な概念について説明します。

割り当てを管理できる API の詳細については、ドキュメントを参照してください。

- [Microsoft Entra ロール用 PIM の API リファレンス](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview)
- [Azure リソース ロール用の PIM の API レファレンス](https://learn.microsoft.com/ja-jp/rest/api/authorization/privileged-role-eligibility-rest-sample)
- [グループ用 PIM の API リファレンス](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagement-for-groups-api-overview)
- [Microsoft Entra ロール用の PIM アラートの API リファレンス](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview?view=graph-rest-beta&preserve-view=true#building-blocks-of-the-pim-alerts-apis)
- [Azure リソース用 PIM アラートの API リファレンス](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-management-alert-rest-sample)

### PIM API の歴史

PIM API は、過去数年に渡って何度か見直しが行われてきました。 機能に重複している部分はいくつかありますが、直線的にバージョンが上がっているわけではありません。

#### イテレーション 1 – 廃止

`/beta/privilegedRoles` エンドポイントでは、Microsoft には従来のバージョンの PIM API があり、Microsoft Entra ロールのみがサポートされていました。 この API は 2021 年 6 月に廃止されました。

#### イテレーション 2 – 非推奨

`/beta/privilegedAccess` エンドポイントでは、Microsoft は Microsoft Entra ロールと Azure リソース ロールをそれぞれ管理するために、`/aadRoles`セグメントと`/azureResources` セグメントの両方をサポートしました。 このエンドポイントは非推奨であり、2026 年 10 月 28 日にデータの返しを停止します。 Microsoft では、これらの API を使用して新しい開発を開始しないことをお勧めします。

さらに、現在、UI を Iteration 3 API に移行中です。

#### イテレーション 3 (現行) – Microsoft Entra のロール、Microsoft Graph API のグループ、および Azure Resource Manager API における Azure リソースのための PIM

これは、PIM API の最後の見直しです。 次の機能が含まれます。

- Microsoft Graph API における Microsoft Entra ロールの PIM - 一般公開されました。
- Azure Resource Manager API の Azure リソース用 PIM - 一般提供開始。
- Microsoft Graph API のグループに対する特権ID管理 (PIM) - 広く利用可能となりました。
- Microsoft Graph API での Microsoft Entra ロール用 PIM アラート - プレビュー。
- ARM API での Azure リソース用 PIM アラート - プレビュー。

注

roleAssignmentApprovals API は/beta でのみ使用できます。

Microsoft Graph API に Microsoft Entra ロール用の PIM を、ARM API の Azure リソース用 PIM を使用すると、次のようないくつかの利点があります。

- Microsoft Entra ロールと Azure Resource ロールの両方における役割の通常割り当てに対する PIM API の整合性。
- リソースのオンボード、リソースの取得、ロール定義の取得を行うために、他の PIM API を呼び出す必要が減ります。
- アプリ専用のアクセス許可のサポート。
- 承認やメール通知の構成などの新機能。

#### PIM API イテレーション 3 の概要

プロバイダー間の PIM API (Microsoft Graph API と Azure Resource Manager API の両方) は、同じ原則に従います。

##### 割り当ての管理

割り当てを作成するには (アクティブまたは有資格)、割り当てを更新、延長、または更新する (アクティブまたは適格)、適格な割り当てをアクティブ化する、対象となる割り当てを非アクティブ化する、リソース **\*AssignmentScheduleRequest** および **\*EligibilityScheduleRequest** を使用します。

- Microsoft Entra の役割の場合: [unifiedRoleAssignmentScheduleRequest](https://learn.microsoft.com/ja-jp/graph/api/resources/unifiedroleassignmentschedulerequest)、[unifiedRoleEligibilityScheduleRequest](https://learn.microsoft.com/ja-jp/graph/api/resources/unifiedroleeligibilityschedulerequest)。
- Azure リソースの場合: [ロール割り当てスケジュール要求](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-assignment-schedule-requests)、[ロール適格性スケジュール要求](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-eligibility-schedule-requests)。
- グループ用: [privilegedAccessGroupAssignmentScheduleRequest](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedaccessgroupassignmentschedulerequest)、[privilegedAccessGroupEligibilityScheduleRequest](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedaccessgroupeligibilityschedulerequest)。

**\*AssignmentScheduleRequest** または **\*EligibilityScheduleRequest** オブジェクトを作成すると、読み取り専用の **\*AssignmentSchedule**、**\*EligibilitySchedule**、**\*AssignmentScheduleInstance**、**\*EligibilityScheduleInstance** オブジェクトが作成される場合があります。

- **\*AssignmentSchedule** と **\*EligibilitySchedule** オブジェクトでは、現在の割り当てと、将来作成される割り当ての要求が示されます。
- **\*AssignmentScheduleInstance** と **\*EligibilityScheduleInstance** オブジェクトでは、現在の割り当てのみが示されます。

適格な割り当てがアクティブ化された (**Create** **\*AssignmentScheduleRequest** が呼び出された) とき、**\*EligibilityScheduleInstance** は存続し、そのアクティブ化された期間に対して新しい **\*AssignmentSchedule** オブジェクトと **\*AssignmentScheduleInstance** オブジェクトが作成されます。

割り当てとアクティブ化の API の詳細については、[ロールの割り当てと適格性を管理するための PIM API](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview#pim-api-for-managing-role-assignment) に関する記事を参照してください。

##### PIM ポリシー (ロールの設定)

PIM ポリシーを管理するには、**\*roleManagementPolicy** と **\*roleManagementPolicyAssignment** エンティティを使います。

- Microsoft Entra ロール用 PIM とグループ用 PIM の場合: [unifiedroleManagementPolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/unifiedrolemanagementpolicy)、[unifiedroleManagementPolicyAssignment](https://learn.microsoft.com/ja-jp/graph/api/resources/unifiedrolemanagementpolicyassignment)
- Azure リソース用 PIM の場合: [ロール管理ポリシー](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-management-policies)、[ロール管理ポリシーの割り当て](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-management-policy-assignments)

**\*roleManagementPolicy** リソースには、PIM ポリシーを構成するルール (承認要件、最大アクティブ化期間、通知設定) が含まれています。

**\*roleManagementPolicyAssignment** オブジェクトにより、ポリシーが特定のロールにアタッチされます。

ポリシー設定 API の詳細については、「[ロール設定と PIM](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview#role-settings-and-pim)」を参照してください。

### 権限

#### Microsoft Entra ロール用の PIM

PIM for Microsoft Entra ロールに必要な Microsoft Graph のアクセス許可については、対応する REST API リファレンス ページを参照してください。

#### Azure リソース向けの PIM

Azure リソース ロールの PIM API は、Azure Resource Manager フレームワーク上で開発されています。 Azure Resource Management に同意する必要がありますが、Microsoft Graph のアクセス許可は必要ありません。 また、API を呼び出すユーザーまたはサービス プリンシパルに、管理予定のリソースに対する所有者またはユーザー アクセス管理者以上のロールが割り当てられていることを確認します。

#### グループの PIM（アクセス特権管理）

PIM for Groups に必要な Microsoft Graph のアクセス許可については、対応する REST API リファレンス ページを参照してください。

### PIM エンティティとロールの割り当てエンティティ間の関係

PIM エンティティと Microsoft Entra ロールまたは Azure ロールの永続的 (アクティブ) 割り当てのロール割り当てエンティティを結ぶ唯一のリンクは、`*AssignmentScheduleInstance`です。 2 つのエンティティ間には 1 対 1 のマッピングがあります。 そのマッピングは、 `roleAssignment` と `*AssignmentScheduleInstance` の両方が含まれることを意味します。

- PIM の外部で行われた永続的かつアクティブな割り当て。
- PIM 内でスケジュールを設定した永続的な（アクティブな）割り当て。
- アクティブ化された適格な割り当て。

PIM 固有のプロパティ (終了時刻など) は、**\*AssignmentScheduleInstance** オブジェクトを通じてのみ使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-approval-workflow"} -->
## PIM での Microsoft Entra ロールの要求を承認または拒否する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Microsoft Entra ロールに対する要求を承認または拒否する方法について説明します。

### 概要

Microsoft Entra ID の Privileged Identity Management (PIM) を使用すると、アクティブ化の承認を要求するようにロールを構成できるとともに、委任された承認者として 1 つまたは複数のユーザーまたはグループを選択できます。 代理承認者は、要求を承認するまでに 24 時間あります。 要求が 24 時間以内に承認されない場合、その資格のあるユーザーは新しい要求をもう一度送信する必要があります。 24 時間の承認時間枠は構成できません。

### 保留中の要求を表示する

代理承認者として、Microsoft Entra のロール要求があなたの承認待ちとなった際には、電子メールで通知が届きます。 これらの保留中の要求は、Privileged Identity Management で表示できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**特権 ID 管理**&gt;**要求を承認する**。

    [Image: [承認要求] ページを示すスクリーンショットで、Microsoft Entra ロールを確認する要求が表示されます。]

    [ **ロールのアクティブ化の要求** ] セクションには、承認待ちの要求の一覧が表示されます。

### Microsoft Graph API を使用して保留中の要求を表示する

#### HTTP 要求

```HTTP
GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignmentScheduleRequests/filterByCurrentUser(on='approver')?$filter=status eq 'PendingApproval' 
```

#### HTTP 応答

```HTTP
{ 
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#Collection(unifiedRoleAssignmentScheduleRequest)", 
    "value": [ 
        { 
            "@odata.type": "#microsoft.graph.unifiedRoleAssignmentScheduleRequest", 
            "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee", 
            "status": "PendingApproval", 
            "createdDateTime": "2021-07-15T19:57:17.76Z", 
            "completedDateTime": "2021-07-15T19:57:17.537Z", 
            "approvalId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee", 
            "customData": null, 
            "action": "SelfActivate", 
            "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222", 
            "roleDefinitionId": "88d8e3e3-8f55-4a1e-953a-9b9898b8876b", 
            "directoryScopeId": "/", 
            "appScopeId": null, 
            "isValidationOnly": false, 
            "targetScheduleId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee", 
            "justification": "test", 
            "createdBy": { 
                "application": null, 
                "device": null, 
                "user": { 
                    "displayName": null, 
                    "id": "d96ea738-3b95-4ae7-9e19-78a083066d5b" 
                } 
            }, 
            "scheduleInfo": { 
                "startDateTime": null, 
                "recurrence": null, 
                "expiration": { 
                    "type": "afterDuration", 
                    "endDateTime": null, 
                    "duration": "PT5H30M" 
                } 
            }, 
            "ticketInfo": { 
                "ticketNumber": null, 
                "ticketSystem": null 
            } 
        } 
    ] 
} 
```

### 要求の承認

注

承認者は、自分のロールのアクティブ化要求を承認できません。 さらに、サービス プリンシパルは要求の承認を許可されません。

1. 承認する要求を見つけて選択します。 承認または拒否のページが表示されます。
2. [ **理由** ] ボックスに、業務上の正当な理由を入力します。
3. [ **送信] を選択します**。 この時点で、システムによって、承認の Azure 通知が送信されます。

### Microsoft Graph API を使用して保留中の要求を承認する

注

**延長要求と更新**要求の承認は、現在、Microsoft Graph API ではサポートされていません。

#### 承認が必要なステップの ID を取得する

特定のアクティベーション要求に対しては、このコマンドによって、承認が必要な手順がすべて取得されます。 現在、複数段階の承認はサポートされていません。

##### HTTP 要求

```HTTP
GET https://graph.microsoft.com/beta/roleManagement/directory/roleAssignmentApprovals/<request-ID-GUID> 
```

##### HTTP 応答

```HTTP
{ 
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#roleManagement/directory/roleAssignmentApprovals/$entity", 
    "id": "<request-ID-GUID>",
    "steps@odata.context": "https://graph.microsoft.com/beta/$metadata#roleManagement/directory/roleAssignmentApprovals('<request-ID-GUID>')/steps", 
    "steps": [ 
        { 
            "id": "<approval-step-ID-GUID>", 
            "displayName": null, 
            "reviewedDateTime": null, 
            "reviewResult": "NotReviewed", 
            "status": "InProgress", 
            "assignedToMe": true, 
            "justification": "", 
            "reviewedBy": null 
        } 
    ] 
} 
```

#### アクティブ化要求の手順を承認する

##### HTTP 要求

```HTTP
PATCH 
https://graph.microsoft.com/beta/roleManagement/directory/roleAssignmentApprovals/<request-ID-GUID>/steps/<approval-step-ID-GUID> 
{ 
    "reviewResult": "Approve", // or "Deny"
    "justification": "Trusted User" 
} 
```

##### HTTP 応答

PATCH の呼び出しに成功すると、空の応答が生成されます。

### 要求を拒否する

1. 拒否する要求を見つけて選択します。 承認または拒否のページが表示されます。
2. [ **理由** ] ボックスに、業務上の正当な理由を入力します。
3. [ **拒否**] を選択します。 拒否すると同時に通知が表示されます。

### ワークフロー通知

ワークフロー通知に関するいくつかの情報を次に示します。

- 役割に関する要求のレビューが保留中の場合、承認者にはメールで通知が送られます。 電子メール通知には、承認者が承認または拒否できる、要求への直接リンクが含まれています。
- 要求は、承認または拒否した最初の承認者によって解決されます。
- 承認者が承認要求に応答すると、すべての承認者に通知されます。
- 全体管理者と特権ロール管理者には、承認されたユーザーがそのロール内でアクティブになると通知されます。

注

承認されたユーザーをアクティブにしてはならないと考えるグローバル管理者または特権ロール管理者は、Privileged Identity Management でアクティブなロールの割り当てを削除できます。 管理者は、承認者でない限り保留中の要求の通知を受け取りませんが、Privileged Identity Management で保留中の要求を表示することで、すべてのユーザーの保留中の要求を表示および取り消すことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-complete-roles-and-resource-roles-review"} -->
## PIM で Azure リソースと Microsoft Entra ロールのアクセス レビューを完了する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-complete-roles-and-resource-roles-review
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management で Azure リソースと Microsoft Entra ロールのアクセス レビューを完了する方法について説明します。

### 概要

特権ロール管理者は、[アクセス レビューが開始すると](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)特権アクセスをレビューできます。 Microsoft Entra ID の Privileged Identity Management (PIM) は、ユーザーにアクセス権の確認を求める電子メールを自動的に送信します。 電子メールが届かなかったユーザーがいる場合は、[アクセス レビューを実行する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-perform-roles-and-resource-roles-review)に関する手順を送信できます。

レビューが作成されたら、この記事の手順に従ってレビューを完了し、結果を確認します。

### アクセス レビューを完了する

1. 1 つ以上の前提条件ロールに割り当てられているユーザーとして、[Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **ID ガバナンス**&gt;**特権 ID 管理**を参照します。
3. **Microsoft Entra ロール**の場合は、**Microsoft Entra ロール** を選びます。 **Azure リソース**の場合は、[**Azure リソース**] を選択します。
4. 管理するアクセス レビューを選択します。 Azure **リソース**と **Microsoft Entra ロール**の両方の**レビュー アクセス**の概要のサンプルスクリーンショットを次に示します。

    [Image: ロール、所有者、開始日、終了日、状態を示すアクセス レビューの一覧のスクリーンショット。]

詳細ページでは、**Azure リソース**と **Microsoft Entra ロール**のレビューを管理するために、次のオプションを使用できます。

[Image: Azure リソースでレビューを管理するオプション - 停止、リセット、適用、削除のスクリーンショット。]

#### アクセス レビューを停止する

すべてのアクセス レビューには終了日が設定されていますが、 **[停止]** ボタンを使用するとレビューを早期に終了することができます。 **[停止]** ボタンは、レビュー インスタンスがアクティブになっている場合にのみ選択できます。 レビューを停止した後に再開することはできません。

#### アクセス レビューをリセットする

レビュー インスタンスがアクティブになっていて、レビュー担当者によって少なくとも 1 つの決定が行われた場合は、 **[リセット]** ボタンを選択してアクセス レビューをリセットすることで、それに対して行われたすべての決定を削除できます。 アクセス レビューをリセットすると、すべてのユーザーは、再度レビューを行っていないユーザーとしてマークされます。

#### アクセス レビューを適用する

終了日に達するか手動で停止されたかのいずれかの理由でアクセス レビューが完了した後で、 **[適用]** ボタンをクリックすると、拒否されたユーザーのロールへのアクセスが削除されます。 レビュー中にユーザーのアクセスが拒否された場合は、この手順により、そのユーザーのロール割り当てが削除されます。 レビューの作成時に **[自動適用** ] 設定が構成されている場合、レビューは手動ではなく自動的に適用されるため、このボタンは常に無効になります。

#### アクセス レビューを削除する

そのレビューが今後も必要なければ、削除します。 アクセス レビューを Privileged Identity Management サービスから削除するには、 **[削除]** ボタンを選択します。

重要

この破壊的変更を確認する必要はないため、そのレビューを削除することを確認してください。

### 結果

[ **結果** ] ページでは、レビュー結果の一覧を表示およびダウンロードできます。

[Image: Microsoft Entra ロールのユーザー、結果、理由、レビュー担当者、適用元、適用結果を一覧表示する [結果] ページのスクリーンショット。]

注

**Microsoft Entra ロール**には、ロールを割り当て可能なグループの概念があり、グループをロールに割り当てることができます。 この場合、グループのメンバーを展開するのではなく、レビューにグループが表示され、レビュー担当者はグループ全体を承認または拒否します。

[Image: Azure リソース ロールのユーザー、結果、理由、レビュー担当者、適用元、適用結果を一覧する [結果] ページのスクリーンショット。]

注

グループが **Azure リソース ロール**に割り当てられている場合、Azure リソース ロールのレビュー担当者には、入れ子になったグループ内のユーザーの展開された一覧が表示されます。 レビュー担当者が入れ子になったグループのメンバーを拒否した場合、ユーザーが入れ子になったグループから削除されないため、その拒否結果は正常に適用されません。

### レビュー担当者

[ **校閲者** ] ページでは、既存のアクセス レビューにレビュー担当者を表示および追加できます。 ここでレビューを完了するようにレビュー担当者に通知することもできます。

注

選択したレビュー担当者の種類がユーザーまたはグループの場合は、任意の時点でプライマリ レビュー担当者としてさらに多くのユーザーまたはグループを追加できます。 また、いつでもプライマリ レビュー担当者を削除できます。 校閲者の種類がマネージャーの場合は、フォールバック レビュー担当者としてユーザーまたはグループを追加して、マネージャーを持たないユーザーのレビューを完了できます。 フォールバック レビュー担当者は削除できません。

[Image: Azure リソース ロールの名前とユーザー プリンシパル名の一覧を示すレビュー担当者ページのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-configure"} -->
## Privileged Identity Management とは? - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra Privileged Identity Management (PIM) の概要について説明します。

### 概要

Privileged Identity Management (PIM) は Microsoft Entra ID のサービスで、これにより、お客様の組織内の重要なリソースへのアクセスを管理、制御、監視できるようになります。 これらのリソースには、Microsoft Entra ID、Azure、および Microsoft 365 や Microsoft Intune などのその他の Microsoft Online Services 内のリソースが含まれます。 次のビデオでは、PIM の重要な概念と機能を紹介しています。

### 使用する理由

組織では、セキュリティで保護された情報やリソースへのアクセス権を持つユーザーの数を最小限に抑える必要があります。そうすることにより、次の可能性を軽減できます。

- 悪意のあるアクターによるアクセス権の取得
- 許可されているユーザーによる機密リソースの誤操作

ただし、ユーザーは、Microsoft Entra ID、Azure、Microsoft 365、または SaaS アプリで特権操作を実行する必要があります。 組織は、Azure および Microsoft Entra リソースに対する Just-In-Time 特権アクセスをユーザーに付与することができ、それらのユーザーが与えられた特権アクセスでどんな操作を実行しているかを監視することもできます。

### ライセンス要件

Privileged Identity Management を使用するにはライセンスが必要です。 ライセンスの詳細については、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」を参照してください。

### それは何をしますか？

Privileged Identity Management では、時間ベースおよび承認ベースのロールのアクティブ化を提供して、対象リソースに対する過剰、不要、または誤用であるアクセス許可のリスクを軽減します。 以下に、Privileged Identity Management の主な機能をいくつか示します。

- Microsoft Entra ID と Azure のリソースに対する **Just-In-Time** の特権アクセスを提供する
- 開始日と終了日を使用した**期限付き**アクセス権をリソースに割り当てる
- 特権ロールをアクティブ化するために**承認**を要求する
- ロールをアクティブ化するために**多要素認証**を強制する
- なぜユーザーをアクティブ化するのかを把握するために**理由**を使用する
- 特権ロールがアクティブ化されたときに**通知**を受ける
- ユーザーにロールがまだ必要であることを確認するために**アクセス レビュー**を実施する
- 社内監査または外部監査に使用する**監査履歴**をダウンロードする
- **最後にアクティブなグローバル管理者ロールの割り当て**および**特権ロール管理者ロールの割り当て**が削除されないようにする

### それで何ができるか

Privileged Identity Management を設定すると、左側のナビゲーション メニューに **[タスク]** 、 **[管理]** 、 **[アクティビティ]** の各オプションが表示されます。 管理者は、**Microsoft Entra ロール**の管理、**Azure リソース** ロールの管理、またはグループの PIM などのオプションを選択できます。 管理対象を選択すると、そのオプションに対する適切な一連のオプションが表示されます。

[Image: Azure portal での Privileged Identity Management のスクリーンショット]

### 誰が何をできるか

Privileged Identity Management での Microsoft Entra ロールの場合、他の管理者の割り当てを管理できるのは、特権ロール管理者またはグローバル管理者ロールに属しているユーザーだけです。 グローバル管理者、セキュリティ管理者、グローバル閲覧者、およびセキュリティ閲覧者も、Privileged Identity Management における Microsoft Entra ロールへの割り当てを表示することができます。

Privileged Identity Management での Azure リソースのロールの場合、サブスクリプション管理者、リソース所有者、またはリソース ユーザー アクセス管理者だけが、他の管理者の割り当てを管理できます。 既定では、特権ロール管理者、セキュリティ管理者、またはセキュリティ閲覧者であるユーザーは、Privileged Identity Management での Azure リソースのロールへの割り当てを表示することはできません。

### 用語

Privileged Identity Management とそのドキュメントについてより深く理解するために、次の用語を確認してください。

| 用語または概念 | ロールの割り当てのカテゴリ | 説明 |
| --- | --- | --- |
| 有資格 | タイプ | あるロールを使用するためには、ユーザーが1つまたは複数のアクションを実行する必要があるロール割り当て。 ユーザーがロールの対象である場合は、特権タスクを実行する必要があるときにロールをアクティブ化できます。 永続的なロール割り当てと資格があるロール割り当てにおいて、アクセスに違いはありません。 常時のアクセスを必要としないユーザーがいる、というのが唯一の違いです。 |
| アクティブ | タイプ | ロールを使用するユーザーが、何のアクションを実行する必要がないロールの割り当て。 アクティブとして割り当てられたユーザーは、そのロールに割り当てられた特権を持ちます。 |
| アクティブにする |  | ユーザーに資格のあるロールを使用するために、1 つまたは複数のアクションを実行するプロセス。 アクションには、多要素認証 (MFA) チェックの実行、業務上の妥当性の指定、指定された承認者に対する承認要求などがあります。 |
| 割り当て済み | 状態 | アクティブなロールの割り当てを持つユーザー。 |
| アクティブ化済み | 状態 | ロールの割り当ての資格を持ち、ロールをアクティブ化するためのアクションを実行して、アクティブになったユーザー。 アクティブになったユーザーは、事前構成済みの期間、そのロールを使用でき、その期間の経過後は再度アクティブ化する必要があります。 |
| 永久的に資格がある | 期間 | ユーザーがロールをアクティブ化する資格を常に持っているロール割り当て。 |
| 永続的にアクティブ | 期間 | 何もアクションを実行しなくても、ユーザーがロールを常に使用できるロールの割り当て。 |
| 期限付き有資格 | 期間 | 開始日から終了日の間だけ、ユーザーにロールをアクティブ化する資格があるロールの割り当て。 |
| 期限付きアクティブ | 期間 | 開始日から終了日の間にのみ、ユーザーがロールを使用できるロールの割り当て。 |
| ジャストインタイム (JIT) アクセス |  | ユーザーが特権タスクを実行するための一時的なアクセス許可を受け取るモデル。これにより、アクセス許可の有効期限が切れた後に、悪意のあるユーザーまたは承認されていないユーザーがアクセスを取得できなくなります。 ユーザーが必要な場合にのみ、アクセスが許可されます。 |
| 最小特権アクセスの原則 |  | すべてのユーザーに、実行権限があるタスクを実行するのに必要な最小特権のみを与える、推奨されるセキュリティ プラクティス。 このプラクティスでは、グローバル管理者の数を最小限にする代わりに、特定のシナリオで特定の管理者ロールが使用されます。 |

### ロールの割り当ての概要

PIM ロールの割り当てにより、組織内のリソースへのアクセス権を安全な方法で付与できます。 ここでは、割り当てのプロセスについて説明します。 これには、メンバーへのロールの割り当て、割り当てのアクティブ化、要求の承認または拒否、割り当ての延長と更新が含まれます。

PIM では、自分や他の参加者に[電子メール通知](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-email-notifications)を送信して、最新情報を知らせることができます。 これらのメールには、要求のアクティブ化、承認、拒否など、関連するタスクへのリンクも含まれる場合があります。

次のスクリーンショットは、PIM によって送信された電子メール メッセージを示しています。 この電子メールでは、Alex が Emily のロールの割り当てを更新したことを Patti に通知しています。

[Image: Privileged Identity Management によって送信された電子メール メッセージを示すスクリーンショット。]

#### 割り当て

割り当てのプロセスは、メンバーにロールを割り当てることから始まります。 リソースへのアクセスを付与するために、管理者はロールをユーザー、グループ、サービス プリンシパル、またはマネージド ID に割り当てます。 割り当てには、次のデータが含まれます。

- ロールを割り当てるメンバーまたは所有者。
- 割り当てのスコープ。 スコープによって、割り当てられたロールが特定のリソース セットに制限されます。
- 割り当ての種類。
    - **対象の** 割り当てでは、このロールを使用するために、ロールのメンバーがアクションを実行する必要があります。 要求されるアクションには、アクティブ化、指定された承認者に対する承認要求などがあります。
    - **[アクティブ]** 割り当ての場合、ロールを使用するために何らかのアクションを実行することをメンバーに要求しません。 アクティブとして割り当てられたメンバーは、そのロールに割り当てられた特権を持ちます。
- 割り当ての期間は、開始日と終了日の設定または永続的なものです。 対象となる割り当ての場合、メンバーは開始日と終了日の間にアクティブ化または承認を要求できます。 アクティブな割り当ての場合、メンバーはこの期間中に割り当てられたロールを使用できます。

次のスクリーンショットは、管理者がメンバーにロールを割り当てる方法を示しています。

[Image: Privileged Identity Management でのロールの割り当てのスクリーンショット。]

詳細については、 [Microsoft Entra ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user)、Azure リソース ロールの [割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles)、 [グループの PIM の適格性の割り当てに関する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-assign-member-owner)記事を参照してください。

#### 有効化

ユーザーがロールの対象である場合は、ロールを使用する前にロールの割り当てをアクティブにする必要があります。 ロールをアクティブにするには、ユーザーは最大範囲 (管理者が設定) 内で特定のアクティブ化期間と、アクティブ化要求の理由を選択します。

次のスクリーンショットは、メンバーが限られた期間のみロールをアクティブにする方法を示しています。

[Image: Privileged Identity Management のロールのアクティブ化のスクリーンショット。]

アクティブにするために[承認](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow)が必要なロールの場合は、要求が承認待ちになっていることを示す通知が、ユーザーのブラウザーの右上隅に表示されます。 承認が必要ない場合、メンバーはロールの使用を開始できます。

詳細については、次の記事を参照してください。「[Microsoft Entra ロールをアクティブ化する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-activate-role)」、「[自分の Azure リソース ロールをアクティブ化する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-activate-your-roles)」、「[グループの PIM ロールをアクティブ化する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-activate-roles)」

#### 承認または拒否

ロールの要求の承認が保留されていると、代理承認者には電子メール通知が届きます。 承認者は、PIM でこれらの保留中の要求を表示、承認、または拒否できます。 要求が承認されると、メンバーはロールの使用を開始できます。 たとえば、ユーザーまたはグループにリソース グループへの投稿ロールが割り当てられている場合、その特定のリソース グループを管理できます。

詳細については、次の記事を参照してください。「[Microsoft Entra ロールの要求を承認または拒否する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow)」、「[Azure リソース ロールの要求を承認または拒否する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow)」、「[グループの PIM のアクティブ化要求を承認する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-approval-workflow)」

#### 割り当ての延長と更新

所有者またはメンバーの期限付き割り当てが管理者によって設定されたときにまず疑問に思うのは、割り当ての期限が切れるとどうなるかということです。 この新しいバージョンでは、このシナリオに対して 2 つの選択肢があります。

- **延長** – ロールの割り当ての期限が近づいたら、ユーザーは Privileged Identity Management を使用してそのロールの割り当ての延長を要求できます
- **更新** – ロールの割り当てが期限切れになると、ユーザーは Privileged Identity Management を使用してロールの割り当ての更新を要求できます

ユーザーが開始するどちらのアクションにも、グローバル管理者または特権ロール管理者からの承認が必要です。 管理者は、割り当ての有効期限を管理する担当者である必要はありません。 ユーザーは、延長要求または更新要求が許可されるか拒否されるのをただ待つだけですみます。

詳細については、次の記事を参照してください。「[Microsoft Entra ロールの割り当てを延長または更新する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-renew-extend)」、「[Azure リソース ロールの割り当てを延長または更新する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend)」、「[グループの PIM の割り当てを延長または更新する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-renew-extend)」

### シナリオ

Privileged Identity Management では、次のシナリオがサポートされています。

#### 特権ロール管理者のアクセス許可

- 特定のロールの承認を有効化する
- 要求を承認するユーザーまたはグループを指定する
- すべての特権ロールの要求と承認の履歴を表示する

#### 承認者のアクセス許可

- 保留中の承認 (要求) を表示する
- ロールの昇格の要求を承認または拒否する (単独および一括)
- 自分の承認または却下の理由を説明する

#### 有資格のロール ユーザーのアクセス許可

- 承認が必要なロールのアクティブ化を要求する
- アクティブ化要求の状態を表示する
- アクティブ化が承認された場合に Microsoft Entra ID でタスクを完了する

### Microsoft Graph API

Privileged Identity Management は、次の Microsoft Graph API を使用してプログラムで使用できます。

- [Microsoft Entra ロール用の PIM API](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview)
- [グループ用の PIM API](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagement-for-groups-api-overview)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review"} -->
## PIM で Azure リソース ロールと Microsoft Entra ロールのアクセス レビューを作成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Azure リソースと Microsoft Entra ロールのアクセス レビューを作成する方法を説明します。

### 概要

ユーザーによる特権 Azure リソース ロールと Microsoft Entra ロールへのアクセスの必要性は、時間の経過に伴って変化します。 古くなったロールの割り当てに関連するリスクを軽減するために、アクセスを定期的に確認する必要があります。 Microsoft Entra Privileged Identity Management (PIM) を使用して、Azure リソース ロールと Microsoft Entra ロールへの特権アクセスのアクセス レビューを作成できます。 自動的に実行される定期的なアクセス レビューを構成することもできます。 この記事では、1 つ以上のアクセス レビューを作成する方法について説明します。

### 前提条件

Privileged Identity Management を使用するにはライセンスが必要です。 ライセンスの詳細については、 [Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) を参照してください。

PIM のライセンスの詳細は「[Privileged Identity Management を使用するためのライセンスの要件](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

Azure リソースのアクセス レビューを作成するには、Azure リソースのロールの[所有者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner)または[ユーザー アクセス管理者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#user-access-administrator)に割り当てられている必要があります。 Microsoft Entra ロール用のアクセス レビューを作成するには、少なくとも [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) のロールが割り当てられている必要があります。

**サービス プリンシパル** のアクセス レビューを使用するには、Microsoft Entra ID P2 または Microsoft Entra ID Governance のライセンスに加えて、Microsoft Entra ワークロード ID Premium プランが必要です。

- ワークロード ID Premium ライセンス: Microsoft Entra 管理センターの [ワークロード ID ブレード](https://portal.azure.com/#view/Microsoft_Azure_ManagedServiceIdentity/WorkloadIdentitiesBlade) でライセンスを表示したり取得したりできます。

注

アクセス レビューは、各レビュー インスタンスの開始時点でのアクセスのスナップショットをキャプチャします。 レビュー プロセス中に行われたすべての変更は、後続のレビュー サイクルに反映されます。 基本的に、ユーザー、レビュー中のリソース、それぞれのレビュー担当者についての関連データは、新たに繰り返されるたびに取得されます。

### アクセス レビューを作成する

1. 前提条件ロールのいずれかに割り当てられているユーザーとして、[Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **ID ガバナンス**&gt;**特権 ID 管理**を参照します。
3. **Microsoft Entra ロール**の場合は、 **Microsoft Entra ロール** を選択します。 **Azure リソース**の場合は、[**Azure リソース**] を選択します。

    [Image: Microsoft Entra 管理センターで [ID ガバナンス] を選択するスクリーンショット。]
4. **Microsoft Entra ロール** の場合は、 **管理** で **Microsoft Entra ロール** をもう一度選択します。 **Azure リソース**の場合、管理するサブスクリプションを選択します。
5. [管理] の下で **[アクセス レビュー]** を選択し、次に **[新規]** を選択して新しいアクセス レビューを作成します。

    [Image: Microsoft Entra ロール - すべてのレビューの状態を示す [アクセス レビュー リスト] のスクリーンショット。]
6. アクセス レビューに名前を付けます。 必要に応じて、そのレビューに説明を加えます。 その名前と説明がレビュアーに示されます。

    [Image: アクセス レビューの作成 - レビューの名前と説明のスクリーンショット。]
7. **[開始日]** を設定します。 既定では、アクセス レビューは 1 回行われます。 作成時に開始され、1 か月で終了します。 この開始日と終了日を変更することで、アクセス レビューを後で開始し、必要な日数を好きなだけ確保することができます。

    [Image: 開始日、頻度、期間、終了、回数、および終了日のスクリーンショット。]
8. アクセス レビューを繰り返すには、 **[頻度]** 設定を **[1 回]** から **[毎週]** 、 **[毎月]** 、 **[四半期に 1 回]** 、 **[毎年]** 、 **[Semi-annually](半年に 1 回)** に変更します。 **期間** スライダーまたはテキスト ボックスを使用して、レビュー期間を指定します。 たとえば、レビューの重複を避けるために、月 1 回のレビューに設定できる最大期間は 27 日です。
9. **[終了]** の設定を使用して、アクセス レビューの繰り返し系列を終了する方法を指定します。 シリーズは、3 つの方法で終了できます。それは連続してレビューを無期限に開始する場合、特定の日付まで続ける場合、または指定された回数が完了した後に終了する場合です。 レビューを管理できる管理者は、 **[設定]** で日付を変更することによって作成後に系列を停止し、それがその日付に終了するようにできます。
10. **[ユーザーのスコープ]** セクションで、レビューのスコープを選択します。 **Microsoft Entra ロール**の場合、最初のスコープ オプションはユーザーとグループです。 この選択には、直接割り当てられたユーザーと [ロール割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept) が含まれます。 Azure リソース ロール の場合、最初のスコープは [ユーザー] です。 Azure リソース ロールに割り当てられたグループは、この選択によってレビューで推移的なユーザーの割り当てを表示するように展開されます。 サービス **プリンシパル** を選択して、Azure リソースまたは Microsoft Entra ロールに直接アクセスできるマシン アカウントを確認することもできます。

    [Image: ロール メンバーシップをレビューするためのユーザー スコープのスクリーンショット。]
11. または、アクティブでないユーザーに対してのみアクセス レビューを作成できます。 *[ユーザー スコープ]* セクションで、**[非アクティブ ユーザー (テナント レベル) のみ]** を **[はい]** に設定します。 トグルが true 設定されている場合、レビューの範囲は非アクティブなユーザーのみに焦点を当てます。 次に、 **非アクティブな日数**を指定します。 最大 730 日 (2 年間) を指定できます。 指定した日数だけ非アクティブなユーザーは、レビューの唯一のユーザーです。
12. **ロール メンバーシップをレビューする** で、レビューする特権 Azure リソース ロールまたは Microsoft Entra ロールを選択します。

    注

    複数のロールを選択すると、複数のアクセス レビューが作成されます。 たとえば、5 つのロールを選択すると、5 つの別々のアクセス レビューが作成されます。

    [Image: ロール メンバーシップのレビューのスクリーン ショット。]
13. **[割り当ての種類]** で、プリンシパルがロールに割り当てられた方法によってレビューの範囲を指定します。 (レビューが作成されたときのアクティブ化の状態に関係なく) 対象の割り当てをレビューする場合は **[対象の割り当てのみ]** 、アクティブな割り当てをレビューする場合は **[アクティブな割り当てのみ]** を選択します。 種類に関係なくすべての割り当てをレビューするには、 **[アクティブおよび対象のすべての割り当て]** を選択します。

    [Image: 割り当ての種類のレビュー担当者の一覧のスクリーンショット。]
14. **[レビュー担当者]** セクションで、全ユーザーをレビューする担当者 (複数可) を選びます。 メンバー自身にそのアクセス権をレビューしてもらうことができます。

    [Image: 選択したユーザーまたはメンバー (セルフ) のレビュー担当者の一覧]

    - **選択したユーザー** - レビューを実行する特定のユーザーを指定するには、このオプションを使用します。 このオプションはレビューの範囲に関係なく使用でき、選択したレビュー担当者はユーザー、グループ、およびサービス プリンシパルをレビューできます。
    - **メンバー (セルフ)** - ユーザーに自分のロール割り当てを確認してもらう場合は、このオプションを使用します。 このオプションは、レビューのスコープが **[ユーザーとグループ]** または **[ユーザー]** に設定されている場合にのみ使用できます。 **Microsoft Entra ロール**の場合、このオプションを選択しても、ロール割り当て可能なグループはレビューの一部ではありません。
    - **マネージャー** – ユーザーにロールの割り当てをレビューしてもらうには、このオプションを使用します。 このオプションは、レビューのスコープが **[ユーザーとグループ]** または **[ユーザー]** に設定されている場合にのみ使用できます。 マネージャーを選択すると、フォールバック レビュー担当者を指定することもできます。 フォールバック レビュー担当者は、ディレクトリにマネージャーが指定されていない場合に、ユーザーをレビューするように求められます。 **Microsoft Entra ロール**の場合、ロールを割り当て可能なグループが選択されている場合、そのグループがフォールバック レビュー担当者によってレビューされます。

#### 完了時の設定

1. レビュー完了後の動作を指定するには、 **[設定完了時]** セクションを展開します。

    [Image: 適用を自動化するための設定完了時、およびレビュー担当者からの応答なしの選択を示すスクリーンショット。]
2. 拒否されたユーザーのアクセスを自動的に削除する場合は、 **[リソースへの結果の自動適用]** を **[有効]** に設定します。 レビューが完了したときに結果を手動で適用する場合は、スイッチを **[無効]** に設定します。
3. レビューの期間内にレビュー担当者によってレビューされなかったユーザーに対する処理を指定するには、**[レビュー担当者が応答しない場合]** リストを使用します。 この設定は、既にレビューされたユーザーには影響しません。

    - **変更なし** - ユーザーのアクセスをそのままの状態で、変更は行いません
    - **アクセスを削除する** - ユーザーのアクセスを削除します
    - **アクセスを承認する** - ユーザーのアクセスを承認します
    - **推奨事項を実行する** - ユーザーの継続的なアクセスの拒否または承認に関するシステムの推奨事項を実行します
4. **[拒否されたゲスト ユーザーに適用するアクション]** リストを使用して、ゲスト ユーザーが拒否された場合の動作を指定します。 この設定は、現時点では Microsoft Entra ID と Azure リソース ロール レビューでは編集できません。ゲスト ユーザーは、すべてのユーザーと同様に、拒否されると常にリソースへのアクセスを失います。

    [Image: 完了時の設定 - 拒否されたゲスト ユーザーに適用するアクションのスクリーンショット。]
5. 他のユーザーまたはグループに通知を送信して、レビュー完了の更新を受け取ることができます。 この機能により、レビュー作成者以外の利害関係者も、通知を受け取ってレビューの進行状況を把握できます。 この機能を使用するには、[ユーザーまたはグループの選択]  選択し、完了ステータス通知を受信するユーザーまたはグループを追加します。

    [Image: 完了設定時 - 通知を受信するユーザーを追加するスクリーンショット。]

#### 詳細設定

1. その他の設定を構成するには、 **詳細設定** セクションを展開します。

    [Image: 推奨事項の表示、承認理由の必須化、メール通知、およびリマインダーのための [詳細設定] のスクリーンショット。]
2. ユーザー **の** アクセス情報に基づいてシステムの推奨事項をレビュー担当者に表示するには、[推奨事項の表示 **] を [有効] に** 設定します。 推奨事項は、30 日間の間隔に基づいています。 過去 30 日間にログインしたユーザーは、アクセスの推奨承認を得て表示されますが、ログインしていないユーザーは、推奨されるアクセス拒否で表示されます。 これらのサインインは、対話型だったかどうかに関係ありません。 ユーザーの最終サインインも、推奨事項と共に表示されます。
3. レビュー担当者による承認理由の指定を必須にするには、 **[承認時に理由が必要]** を **[有効]** に設定します。
4. アクセス レビュー開始時に Microsoft Entra ID からレビュー担当者にメール通知を送信し、レビュー完了時に管理者にメール通知を送信するには、 **メール通知** を **有効** に設定します。
5. レビューを完了していないレビュー担当者に、進行中のアクセス レビューに関するリマインダーを Microsoft Entra ID から送信するには、 **リマインダー** を **有効** に設定します。
6. レビュー担当者に送信されたメールの内容は、レビュー名、リソース名、期限などのレビューの詳細に基づいて自動生成されます。 追加の手順や連絡先情報などの追加情報を伝える方法が必要な場合は、[ **校閲者の電子メールの追加コンテンツ** ] セクションでこれらの詳細を指定できます。 これらの詳細は、割り当てられた校閲者に送信された招待メールとリマインダーメールに含まれます。 強調表示されたセクションでは、この情報が表示されます。

    [Image: レビュー担当者に送信される電子メールの内容 (ハイライトを含む)]

### アクセスレビューを管理する

アクセス レビューの **[概要]** ページでは、レビュー担当者が完了したレビューの進捗状況を追跡できます。 ディレクトリのアクセス権は、レビューが完了するまで変更されません。 [Image: Microsoft Entra ロールのアクセス レビューの詳細を示すアクセス レビューの概要ページのスクリーンショット。]

アクセス レビューの後、「[Azure リソースおよび Microsoft Entra ロールのアクセス レビューを完了する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-complete-roles-and-resource-roles-review)」の手順に従い、結果を確認して適用します。

一連のアクセス レビューを管理している場合は、アクセス レビューに移動し、スケジュールされたレビューで今後発生する予定を見つけ、終了日を編集するか、それに応じてレビュー担当者を追加または削除します。

**[Upon completion](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/完了時)** 設定での選択に基づいて、レビューの終了日の後、またはレビューを手動で停止したときに自動適用が実行されます。 レビューの状態は、**完了** から、**適用中** などの中間状態を経て、最終的に **適用済み**状態に変わります。 拒否されたユーザーが存在する場合は、それらのユーザーが数分以内にロールから削除されることを確認できます。

### アクセス レビューで Microsoft Entra ロールと Azure リソース ロールに割り当てられたグループの影響

• **Microsoft Entra ロール**の場合、 [ロール割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)を使用し、ロール割り当て可能なグループにロールを割り当てることができます。 ロール割り当て可能なグループに割り当てられた Microsoft Entra ロールでレビューが作成されると、グループ メンバーシップを展開せずに、グループ名がレビューに表示されます。 レビュー担当者は、ロールへのグループ全体のアクセスを承認または拒否できます。 レビュー結果が適用されると、拒否されたグループはロールへの割り当てを失います。

• **Azure リソース ロール**の場合、任意のセキュリティ グループをロールに割り当てることができます。 セキュリティ グループが割り当てられた Azure リソース ロールでレビューが作成されると、ロールレビュー担当者はグループのメンバーシップの完全に拡張されたビューを表示できます。 レビュー担当者がセキュリティ グループを介してロールに割り当てられたユーザーを拒否した場合、そのユーザーはグループから削除されません。 これは、グループが他の Azure または Azure 以外のリソースと共有される可能性があるためです。 管理者は、アクセス拒否に起因する変更を実装する必要があります。

注

セキュリティ グループには他のグループを割り当てることができます。 この場合、ロールに割り当てられているセキュリティ グループに直接割り当てられたユーザーのみがロールのレビューに表示されます。

### アクセス レビューを更新する

1 つ以上のアクセス レビューが開始されたら、既存のアクセス レビューの設定を変更または更新できます。 考慮する必要があるいくつかの一般的なシナリオを次に示します。

- **レビュー担当者の追加と削除** - アクセス レビューを更新するときに、プライマリ レビュー担当者に加えてフォールバック レビュー担当者を追加することもできます。 プライマリ レビュー担当者は、アクセス レビューを更新するときに削除される可能性があります。 ただし、フォールバック レビュー担当者は、設計上、取り外し可能ではありません。

    注

    フォールバック レビュー担当者は、レビュー担当者の種類がマネージャーの場合にのみ追加できます。 レビュー担当者の種類が選択されたユーザーの場合は、プライマリ レビュー担当者を追加できます。
- **レビュー担当者に通知** する - アクセス レビューを更新するときに、[詳細設定] でアラーム オプションを有効にすることを選択できます。 有効にすると、ユーザーはレビュー期間の途中で電子メール通知を受け取ります。 レビュー担当者は、レビューが完了したかどうかに関係なく通知を受け取ります。

    [Image: アクセス レビュー設定のリマインダー オプションのスクリーンショット。]
- **設定の更新** - アクセス レビューが定期的な場合は、[現在] と [シリーズ] の下に別々の設定があります。 [現在] の設定を更新すると、現在のアクセス レビューに対する変更だけが適用され、[シリーズ] の設定を更新すると、今後のすべての定期的発生の設定が更新されます。

    [Image: アクセス レビューの設定ページのスクリーンショット。]

注

Azure プランの AOBO (代理管理者) を使用した Azure リソースのアクセス レビューの作成はサポートされていません。 AOBO 経由で顧客のサブスクリプションを管理している CSP パートナーの場合、独自の (パートナー) テナントからアクセス レビューを作成することはできません。**対処法:** CSP パートナーに必要なアクセス許可を持つ **ゲスト ユーザー** として顧客のテナントに追加し、 **顧客のテナント内から**アクセス レビューを作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-deployment-plan"} -->
## Privileged Identity Management のデプロイを計画する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-deployment-plan
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra 組織に Privileged Identity Management (PIM) をデプロイする方法について説明します。

### 概要

**Privileged Identity Management (PIM)** によって、時間ベースおよび承認ベースのロールのアクティブ化が提供され、重要なリソースに対する過剰、不要、または誤用であるアクセス許可のリスクが軽減されます。 これらのリソースには、Microsoft Entra ID、Azure、および Microsoft 365 や Microsoft Intune などのその他の Microsoft Online Services 内のリソースが含まれます。

PIM を使用すると、特定のスコープで特定のアクション セットを許可することができます。 組み込まれている主な機能は次のとおりです。

- リソースに対する **Just-In-Time** の特権アクセスを提供する
- グループの PIM の**メンバーシップまたは所有権の資格**を割り当てる
- 開始日と終了日を使用した**期限付き**アクセス権をリソースに割り当てる
- 特権ロールをアクティブ化するために**承認**を要求する
- ロールをアクティブ化するために**多要素認証**を適用する
- ロールをアクティブ化するために**条件付きアクセス ポリシー**を適用する (パブリック プレビュー)
- なぜユーザーをアクティブ化するのかを把握するために**理由**を使用する
- 特権ロールがアクティブ化されたときに**通知**を受ける
- ユーザーにロールがまだ必要であることを確認するために**アクセス レビュー**を実施する
- 社内監査または外部監査に使用する**監査履歴**をダウンロードする

このデプロイ計画を最大限に活用するには、[Privileged Identity Management が何であるか](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)についてのすべての概要を把握することが重要です。

### PIM を理解する

このセクションの PIM の概念は、組織の特権 ID 要件を理解するのに役立ちます。

#### PIM で管理できる内容

現在、PIM を使用できる対象は次のとおりです。

- **Microsoft Entra ロール** – ディレクトリ ロールと呼ばれることもあります。Microsoft Entra ロールには、Microsoft Entra ID やその他の Microsoft 365 オンライン サービスを管理するための組み込みロールとカスタム ロールが含まれます。
- **Azure ロール** – 管理グループ、サブスクリプション、リソース グループ、およびリソースへのアクセスを許可する Azure のロールベースのアクセス制御 (RBAC) ロール。
- **グループの PIM** – Microsoft Entra セキュリティ グループのメンバーおよび所有者ロールに対して、ジャストインタイム アクセスを設定することを目的としています。 グループの PIM を使用すると、Microsoft Entra ロールと Azure ロール用に PIM を設定する別の手段が得られるだけでなく、Intune、Azure Key Vault、Azure Information Protection などの Microsoft オンライン サービス全体で、他のアクセス許可用に PIM を設定することもできます。 グループがアプリのプロビジョニング用に構成されている場合、グループ メンバーシップをアクティブ化すると、クロスドメイン ID 管理システム (SCIM) プロトコルを使って、アプリケーションへのグループ メンバーシップ (プロビジョニングされていない場合はユーザー アカウントも) のプロビジョニングがトリガーされます。

これらのロールまたはグループには、以下を割り当てることができます。

- **ユーザー** - Microsoft Entra ロール、Azure ロール、およびグループの特権 ID 管理（PIM）に対するジャストインタイム アクセス権を取得します。
- **グループ** - グループ内の全員が、Microsoft Entra ロールと Azure ロールに対する Just-In-Time アクセス権を取得します。 Microsoft Entra ロールの場合、グループは、ロールに割り当て可能であると示されている、新しく作成されたクラウド グループでなければなりません。それに対し、Azure ロールの場合、グループは、任意の Microsoft Entra セキュリティ グループにすることができます。 グループの PIM にグループを割り当てたり入れ子にしたりすることは推奨されません。

注

Microsoft Entra ロール、Azure ロール、および PIM for Groups の対象としてサービス プリンシパルを割り当てることはできませんが、時間制限付きのアクティブな割り当てを 3 つすべてに付与することはできます。

#### 最小限の特権の原則

ユーザーには、[タスクの実行に必要な最小限の特権](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task)を持つロールを割り当てます。 このプラクティスではグローバル管理者の数を最小限にし、代わりに特定のシナリオで特定の管理者ロールが使用されます。

注

Microsoft には、グローバル管理者はほとんどありません。 詳細については、[Microsoft での Privileged Identity Management の使用方法](https://www.microsoft.com/insidetrack/blog/improving-security-by-protecting-elevated-privilege-accounts-at-microsoft/)に関するページを参照してください。

#### 割り当ての種類

割り当てには、**資格**と**アクティブ**の 2 つの種類があります。 ユーザーがロールの対象である場合は、特権タスクを実行する必要があるときにロールをアクティブ化できます。

それぞれの割り当ての種類に、開始と終了の時間を設定することもできます。 この追加により、次の 4 種類の割り当てが可能になります。

- 永続資格保持者
- 永続的にアクティブ
- 期間限定で有資格 (割り当ての開始と終了の日付が指定されている)
- 期間限定でアクティブ (割り当ての開始と終了の日付が指定されている)

ロールが期限切れになった場合は、これらの割り当てを**延長**または**更新**することができます。

[緊急アクセスアカウント](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)以外のロールに対して、永続的にアクティブな割り当てをゼロのままにします。

Microsoft は、組織が[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)ロールが永続的に割り当てられる 2 つのクラウド専用緊急アクセス アカウントを作成することを推奨しています。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 これらのアカウントは、通常のアカウントが使用できない、またはすべての管理者が誤ってロックアウトされたときに限定される緊急時あるいは「ブレイクグラス」という非常事態に使用されます。これらのアカウントの作成は、[緊急アクセスアカウントに関する推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従う必要があります。

### プロジェクトを計画する

テクノロジ プロジェクトが失敗した場合、その原因は通常、影響、結果、および責任に対する想定の不一致です。 これらの落とし穴を回避するには、[適切な利害関係者が担当していることを確認](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)し、プロジェクトにおけるその利害関係者の役割がよく理解されていることを確認します。

#### パイロットを計画する

デプロイの各段階で、結果が期待どおりであることを評価していることを確認します。 「[パイロットのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans#best-practices-for-a-pilot)」を参照してください。

- 少数のユーザー (パイロット グループ) から開始し、PIM が期待どおりに動作することを確認します。
- ロールまたはグループの PIM に対して設定した構成がすべて正しく機能するかどうかを確認します。
- 十分にテストした後にのみ、運用環境に移行してください。

#### 連絡を計画する

コミュニケーションは、新しいサービスの成功に必要不可欠です。 ユーザー エクスペリエンスがどのように変わるのか、いつ変わるのか、および問題が発生したときにサポートを受ける方法について、ユーザーに事前に連絡します。

内部 IT サポートとの時間を設定し、PIM ワークフローについて説明します。 適切なドキュメントと連絡先情報を提供します。

### テストとロールバックを計画する

注

Microsoft Entra ロールでは、多くの場合、組織はまずグローバル管理者をテストおよびロールアウトします。それに対し、Azure リソースでは、通常、一度に 1 つの Azure サブスクリプションで PIM をテストします。

#### テストを計画する

テスト ユーザーを作成して、実際のユーザーに影響が及んでアプリやリソースへのアクセスが中断される前に、PIM の設定が想定どおりに機能するか確認します。 テスト計画を作成して、予想される結果と実際の結果を比較します。

次の表にテスト ケースの例を示します。

| 役割 | アクティブ化中の期待される動作 | 実際の結果 |
| --- | --- | --- |
| グローバル管理者 | - MFAを必須とする<br>- 承認を要求する<br>- 条件付きアクセス コンテキストが必須 (パブリック プレビュー)<br>- 承認者が通知を受け取り、承認できる<br>- ロールの有効期限が事前に設定された時間後に切れる |  |

Microsoft Entra IDと Azure リソースのどちらのロールについても、これらのロールを取得するユーザーが示されていることを確認します。 さらに、ステージング環境で PIM をテストするときは、次のロールを考慮してください。

| 役割 | Microsoft Entra ロール | Azure リソース ロール | グループ向け PIM |
| --- | --- | --- | --- |
| グループのメンバー |  |  | x |
| ロールのメンバー | x | x |  |
| IT サービス所有者 | x |  | x |
| サブスクリプションまたはリソース所有者 |  | x | x |
| グループの PIM 所有者 |  |  | x |

#### ロールバックを計画する

PIM が運用環境で必要とされているとおりに動作しない場合は、ロールの割り当てをもう一度資格からアクティブに変更することができます。 構成した各ロールについて、割り当ての種類が**対象**であるすべてのユーザーの省略記号 **(...)** を選択します。 それから、 **[Make active](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/アクティブにする)** オプションを選択し、ロールの割り当てを**アクティブ**にし直すことができます。

### Microsoft Entra ロールの PIM を計画および実装する

Microsoft Entra ロールを管理するように PIM を準備するには、これらのタスクに従います。

#### 特権ロールを検出し、軽減する

組織内の特権ロールを持つユーザーのリスト。 割り当て対象のユーザーを確認し、ロールが不要になった管理者を特定して、割り当てから削除します。

[Microsoft Entra ロールのアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)を使用して、割り当ての検出、レビュー、承認または削除を自動化することができます。

#### PIM によって管理されるロールを決定する

最も多くのアクセス許可を持つ Microsoft Entra ロールの保護を優先します。 組織でどのデータとアクセス許可を最も機密性の高いものと見なすかも重要です。

まず、すべての全体管理者およびセキュリティ管理者ロールが PIM を使用して管理されていることを確認します。これらは、侵害された場合に最も大きな損害を生じさせる可能性のあるユーザーであるためです。 次に、攻撃に対して脆弱であると考えられる、管理が必要なその他のロールについて検討します。

Privileged ラベルを使うと、PIM で管理可能な高い特権を持つロールを識別できます。 Privileged ラベルは Microsoft Entra 管理センターの[**\[ロールと管理者\]**](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions?tabs=admin-center)に存在します。 詳細については、記事「[Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

#### Microsoft Entra ロールの PIM 設定を構成する

組織で使用される特権付きの Microsoft Entra ロールごとに、[PIM 設定を作成して構成](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)します。

次の表は、設定の例を示しています。

| 役割 | MFA 必須 | 条件付きアクセスを要求 | 通知 | インシデントチケット | 承認を要求 | 承認者 | アクティブ化期間 | アクセス権限管理者 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| グローバル管理者 | ✔️ | ✔️ | ✔️ | ✔️ | ✔️ | その他のグローバル管理者 | 1 時間 | 緊急アクセス アカウント |
| Exchange 管理者 | ✔️ | ✔️ | ✔️ | ❌ | ❌ | なし | 2時間 | なし |
| ヘルプデスク管理者 | ❌ | ❌ | ❌ | ✔️ | ❌ | なし | 8 時間 | なし |

#### Microsoft Entra ロールを割り当ててアクティブ化する

PIM での Microsoft Entra ロールの場合、他の管理者の割り当てを管理できるのは、特権ロール管理者またはグローバル管理者ロールに属しているユーザーだけです。 グローバル管理者、セキュリティ管理者、グローバル閲覧者、セキュリティ閲覧者も、PIM における Microsoft Entra ロールへの割り当てを表示できます。

次の各手順の手順に従います。

1. [資格対象の課題を与える](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user)。
2. [対象ユーザーが Microsoft Entra ロールの Just-In-Time をアクティブ化できるようにします](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-activate-role)。

ロールが有効期限に近づいたら、[PIM を使用してロールを延長または更新します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend)。 ユーザーが開始するどちらのアクションにも、グローバル管理者または特権ロール管理者からの承認が必要です。

これらの重要なイベントが Microsoft Entra ロールで発生すると、PIM から特権管理者に、ロール、イベント、通知の設定に応じて[メール通知と週次ダイジェスト メールが送信](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-email-notifications)されます。 これらのメールには、ロールのアクティブ化や更新など、関連するタスクへのリンクが含まれている場合もあります。

注

これらの PIM タスクは、[Microsoft Entra ロールの Microsoft Graph API を使用して](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-apis)実行することもできます。

#### PIM アクティブ化要求を承認または拒否する

代理承認者は、承認の要求が保留されると電子メール通知を受け取ります。 [Azure リソース ロールをアクティブ化する要求を承認または拒否](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow)するには、こちらの手順に従います。

#### Microsoft Entra ロールの監査履歴を表示する

Microsoft Entra ロールに対する過去 30 日間における[すべてのロール割り当てとアクティブ化の監査履歴を表示](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-use-audit-log)します。 グローバル管理者または特権ロール管理者の場合は、監査ログにアクセスできます。

少なくとも 1 人の管理者がすべての監査イベントを週単位で精査し、監査イベントを毎月手動でエクスポートします。

#### Microsoft Entra ロールのセキュリティ アラート

[疑わしいアクティビティや安全でないアクティビティが発生した場合にアラートをトリガーするように、Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts) のセキュリティ アラートを構成します。

### Azure リソース ロール用の PIM を計画して実装する

Azure リソース ロールを管理するように PIM を準備するには、これらのタスクに従います。

#### 特権ロールを検出し、軽減する

各サブスクリプションまたはリソースにアタッチされている所有者およびユーザー アクセス管理者割り当てを最小限にし、不要な割り当てを削除します。

グローバル管理者は、[すべての Azure サブスクリプションを管理するようにアクセス権を昇格](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)できます。 その後、各サブスクリプション所有者を見つけ、協力して、それらのサブスクリプション内の不要な割り当てを削除することができます。

[Azure リソースのアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)を使用して監査し、不要なロール割り当てを削除します。

#### PIM によって管理されるロールを決定する

Azure リソース用の PIM を使用して管理する必要があるロール割り当てを決定するときには、まず、組織で最も重要な[管理グループ](https://learn.microsoft.com/ja-jp/azure/governance/management-groups/overview)、サブスクリプション、リソース グループ、リソースを特定する必要があります。 管理グループを使用して、組織内のすべてのリソースを整理することを検討してください。

PIM を使用して、すべてのサブスクリプション所有者ロールとユーザー アクセス管理者ロールを管理します。

サブスクリプション所有者と協力して、各サブスクリプションによって管理されているリソースをドキュメント化し、侵害された場合の各リソースのリスク レベルを分類します。 リスク レベルに基づいて、PIM でのリソース管理に優先順位を付けます。 これには、サブスクリプションにアタッチされているカスタム リソースも含まれます。

重要なサービスのサブスクリプションまたはリソース所有者と協力して、機密性の高いサブスクリプションまたはリソース内のすべてのロールに対して PIM ワークフローを設定します。

重要でないサブスクリプションまたはリソースの場合は、すべてのロールに対して PIM を設定する必要はありません。 しかし、所有者ロールとユーザー アクセス管理者ロールについては、PIM で引き続き保護する必要があります。

#### Azure リソース ロールの PIM 設定を構成する

PIM で保護しようとしている Azure リソース ロールの[設定を作成して構成](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-role-settings)します。

次の表は、設定の例を示しています。

| 役割 | MFA 必須 | 通知 | 条件付きアクセスを要求 | 承認を要求 | 承認者 | アクティブ化期間 | アクティブな管理者 | アクティブな有効期限 | 有資格の有効期限 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 重要なサブスクリプションの所有者 | ✔️ | ✔️ | ✔️ | ✔️ | サブスクリプションのその他の所有者 | 1 時間 | なし | 該当なし | 3 か月 |
| それほど重要ではないサブスクリプションのユーザー アクセス管理者 | ✔️ | ✔️ | ✔️ | ❌ | なし | 1 時間 | なし | 該当なし | 3 か月 |

#### Azure リソース ロールを割り当ててアクティブ化する

PIM での Azure リソース ロールの場合、所有者またはユーザー アクセス管理者だけが、他の管理者の割り当てを管理することができます。 特権ロール管理者、セキュリティ管理者、またはセキュリティ閲覧者であるユーザーは、既定では、Azure リソース ロールへの割り当てを表示するアクセス権を持っていません。

以下のリンクの指示に従います。

1. [資格対象の課題を与える](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles)。
2. [対象ユーザーが Azure ロールを Just-In-Time でアクティブ化できるようにします](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-activate-your-roles)。

特権ロールの割り当てが有効期限に近づいたら、[PIM を使用してロールを延長または更新します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend)。 どちらのユーザー開始アクションにも、リソース所有者またはユーザー アクセス管理者からの承認が必要です。

Azure リソース ロールでこれらの重要なイベントが発生すると、PIM から所有者とユーザー アクセス管理者に、[電子メール通知](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-email-notifications)が送信されます。 これらのメールには、ロールのアクティブ化や更新など、関連するタスクへのリンクが含まれている場合もあります。

注

これらの PIM タスクは、[Azure リソース ロールの Microsoft Azure Resource Manager API を使用して](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-apis)実行することもできます。

#### PIM アクティブ化要求を承認または拒否する

[Microsoft Entra ロールのアクティブ化要求を承認または拒否する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow) - 代理承認者は、承認の要求が保留されると、メール通知を受け取ります。

#### Azure リソース ロールの監査履歴を表示する

Azure リソース ロールに対する過去 30 日間における[すべての割り当てとアクティブ化の監査履歴を表示](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/azure-pim-resource-rbac)します。

#### Azure リソース ロールに対するセキュリティ アラート

疑わしいアクティビティと安全でないアクティビティがある場合にアラートをトリガーする [Azure リソース ロールのセキュリティ](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-alerts) アラートを構成します。

### グループ用の PIM の計画と実装

グループの PIM を管理するように PIM を準備するには、これらのタスクに従います。

#### グループの PIM を検出する

一部のユーザーは、PIM を通じて Microsoft Entra ロールに対して資格のある割り当てを 5 つまたは 6 つ持っている可能性があります。 各ロールを個別にアクティブにする必要があるため、生産性が低下する可能性があります。 さらに悪いことに、多数の Azure リソースが割り当てられている場合もあり、問題が悪化します。

この場合は、グループの PIM を使用する必要があります。 グループの PIM を作成し、複数のロールに対して永続的にアクティブにアクセスできるようにします。 「[グループ向けの Privileged Identity Management (PIM) (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups)」を参照してください。

Microsoft Entra のロールの割り当て可能なグループをグループの PIM として管理するには、[これを PIM で管理下に置く](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-discover-groups)必要があります。

#### グループ用の PIM の設定を構成する

[下書きを作成し、PIM で保護するグループの PIM の設定](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-role-settings) を構成します。

次の表は、設定の例を示しています。

| 役割 | MFA 必須 | 通知 | 条件付きアクセスを要求 | 承認を要求 | 承認者 | アクティブ化期間 | アクティブな管理者 | アクティブな有効期限 | 有資格の有効期限 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 所有者 | ✔️ | ✔️ | ✔️ | ✔️ | リソースのその他の所有者 | 1 時間 | なし | 該当なし | 3 か月 |
| メンバー | ✔️ | ✔️ | ✔️ | ❌ | なし | 5 時間 | なし | 該当なし | 3 か月 |

#### グループの PIM の適格性を割り当てる

グループ [PIM のメンバーまたは所有者に資格を割り当てることができます。](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-assign-member-owner) 1 回のアクティブ化だけで、リンクされているすべてのリソースにアクセスできます。

注

ユーザーにロールを割り当てるのと同じ方法で、1 つまたは複数の Microsoft Entra および Azure リソースのロールにグループを割り当てることができます。 1 つの Microsoft Entra 組織 (テナント) には、最大 500 個のロール割り当て可能なグループを作成できます。

[Image: グループの PIM への適格性割り当ての図。]

グループの割り当てが有効期限に近づいたら、[PIM を使用してグループの割り当てを延長または更新します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-renew-extend)。 この操作には、グループ所有者の承認が必要です。

#### PIM アクティブ化要求を承認または拒否する

アクティブ化の承認を必要とするようにグループの PIM のメンバーと所有者を構成し、Microsoft Entra 組織からユーザーまたはグループを代理承認者として選択します。 特権ロール管理者の作業負荷を減らすには、グループごとに 2 人以上の承認者を選択することをお勧めします。

[グループの PIM のロール アクティブ化要求を承認または拒否します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-approval-workflow)。 委任された承認者は、要求が承認を待っているときに電子メール通知を受け取ります。

#### グループの PIM の監査履歴を表示する

グループの PIM に対する過去 30 日間における[すべての割り当てとアクティブ化の監査履歴を表示](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-audit)します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-email-notifications"} -->
## Privileged Identity Management (PIM) のメール通知 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-email-notifications
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra Privileged Identity Management (PIM) でのメール通知について説明します。

### 概要

Privileged Identity Management (PIM) を使うと、Microsoft Entra 組織で、ロールが割り当てられたり、アクティブ化されたりした場合などの重要なイベントがいつ発生したかを知ることができます。 Privileged Identity Management では、自分やその他の参加者に電子メール通知が送信されるため、常に最新情報を取得できます。 これらのメールには、ロールのアクティブ化や更新など、関連するタスクへのリンクが含まれている場合もあります。 この記事では、これらの電子メールがどのように表示されるかや、それらがいつ誰に送信されるのかについて説明します。

注

Privileged Identity Management の 1 つのイベントで、複数の受信者 (担当者、承認者、管理者) 宛の電子メール通知を生成できます。 1 つのイベントごとに送信される通知の最大数は 1,000 です。 受信者の数が 1,000 人を超える場合は、最初の 1,000 人の受信者のみが電子メール通知を受信します。 これにより、他の担当者、管理者、または承認者が Microsoft Entra ID と Privileged Identity Management でアクセス許可を使用できなくなります。

### 送信者の電子メール アドレスと件名行

Microsoft Entra ID と Azure リソースの両方のロールに関して Privileged Identity Management から送信される電子メールには、次の送信者メール アドレスが含まれています。

- メール アドレス: `MSSecurity-noreply@microsoft.com`
- 表示名: **Microsoft Security**

重要

`azure-noreply@microsoft.com`は非推奨となり、PIM 電子メール通知を送信しなくなりました。

これらの電子メールの件名行には、**PIM** というプレフィックスが付けられます。 次に例を示します。

- PIM:Alain Charon にバックアップ リーダー ロールが永続的に割り当てられました

### アクティブ化承認の電子メールのタイミング

ユーザーが各自のロールをアクティブにし、ロールの設定で承認が必要な場合、承認者は承認ごとに次の 2 通の電子メールを受け取ります。

- ユーザーのアクティブ化要求を承認または拒否する要求 (要求承認エンジンによって送信されます)
- ユーザーの要求が承認された (要求承認エンジンによって送信されます)

また、グローバル管理者と特権ロール管理者は、各承認で次の電子メールを受信します。

- ユーザーのロールがアクティブ化された (Privileged Identity Management により送信されます)

要求承認エンジンによって送信される最初の 2 つの電子メールは、遅延する場合があります。 現在、90% のメールには 3 ~ 10 分かかりますが、顧客の 1% の場合、最大 15 分の長さになる可能性があります。

最初の電子メールが送信される前に Azure portal で承認要求が承認された場合、最初の電子メールはトリガーされず、他の承認者は承認要求の電子メール通知を受信しません。 他の承認者が電子メールを受け取っていないように見えるかもしれませんが、それは想定されている動作です。

### Microsoft Entra ロールの通知

Privileged Identity Management は、Microsoft Entra ロールに関して次のイベントが発生したときに電子メールを送信します。

- 特権ロールのアクティブ化が承認待ちのとき
- 特権ロールのアクティブ化要求が完了したとき
- Microsoft Entra Privileged Identity Management が有効になったとき

Microsoft Entra ロールに対するこれらの電子メールをどのユーザーが受信するかは、ロール、イベント、通知設定によって決まります。

| ユーザー | ロールのアクティブ化が承認待ち | ロールのアクティベーションリクエストが完了した | PIM が有効になった |
| --- | --- | --- | --- |
| 特権ロール管理者(アクティブ化) | はい (明示的な承認者が指定されていない場合のみ) | はい\* | はい |
| セキュリティ管理者(アクティブ化) | いいえ | はい\* | はい |
| グローバル管理者(アクティブ化) | いいえ | はい\* | はい |

\* [**通知**設定](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)が**有効**に設定されている場合。

ユーザーが架空の Contoso 組織の Microsoft Entra ロールをアクティブ化したときに送信される電子メールの例を次に示します。

[Image: Microsoft Entra ロールに関する新しい Privileged Identity Management 電子メールを示すスクリーンショット。]

#### Microsoft Entra のロールに関する毎週の特権アイデンティティ管理ダイジェストメール

Microsoft Entra ロールに関する週間 Privileged Identity Management サマリー電子メールは、Privileged Identity Management が有効になっている特権ロール管理者、セキュリティ管理者、およびグローバル管理者に送信されます。 この毎週の電子メールには、その週の Privileged Identity Management アクティビティと特権ロールの割り当てのスナップショットが記載されています。 パブリック クラウド上の Microsoft Entra 組織でのみ使用できます。 電子メールの例を次に示します。

[Image: Microsoft Entra ロールに関する週間 Privileged Identity Management ダイジェスト電子メールを示すスクリーンショット。]

電子メールに含まれるもの:

| タイル | 説明 |
| --- | --- |
| **[Users activated](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/アクティブ化されたユーザー)** | 組織内でユーザーの有資格ロールがアクティブ化された回数です。 |
| **[Users made permanent](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/永続化されたユーザー)** | 有資格割り当てを持つユーザーが永続化された回数です。 |
| **Privileged Identity Management でのロールの割り当て** | Privileged Identity Management の内部でユーザーに資格のあるロールが割り当てられた回数。 |
| **[Role assignments outside of PIM](PIM の外部でのロール割り当て)** | Privileged Identity Management の外部 (Microsoft Entra ID の内部) でユーザーに永続的なロールが割り当てられた回数。 このアラートと付属する電子メールはアラート設定を開くことで有効にしたり、無効にしたりできます。 |

**[Overview of your top roles](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/上位ロールの概要)** セクションには、各ロールの永続管理者と有資格管理者の合計数に基づいて、組織内の上位 5 つのロールが一覧表示されます。 **[アクションの実行]** リンクをクリックすると、[\[検出と分析情報\]](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-security-wizard) が開き、そこから永続管理者を有資格管理者に一括変換することができます。

### Azure リソース ロール向けの通知

注

PIM では、*対象* 所有者は、必要に応じてアクティブ化できるグループを管理するための特定のタスクを実行するための Just-In-Time (JIT) 特権アクセス権を持つユーザーです。 これは、グループを管理するための継続的なアクセス権を持つ "永続的な" 所有者とは異なります。 グループの JIT 所有権の詳細については、「[Privileged Identity Management でグループの資格を割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-assign-member-owner)」を参照してください。

グループ所有者は、メンバーの追加または削除、有効期限が近づいているグループの更新、グループへの参加要求の承認など、グループを管理できます。 PIM では、Azure リソース ロールに関する次のイベントが発生した際、"永続的な" 所有者、"有資格" 所有者、ユーザー アクセス管理者にメールが送信されます。

- ロールの割り当てが承認待ちのとき
- ロールが割り当てられたとき
- ロールの期限切れが近づいているとき
- ロールが延長の資格を有する場合
- ロールがエンド ユーザーによって更新されたとき
- ロールのアクティブ化要求が完了したとき

Privileged Identity Management は、Azure リソース ロールに関して次のイベントが発生したときにエンド ユーザーに電子メールを送信します。

- ユーザーにロールが割り当てられたとき
- ユーザーのロールが期限切れになったとき
- ユーザーのロールが延長されたとき
- ユーザーのロールのアクティブ化要求が完了したとき

ユーザーに架空の Contoso 組織の Azure リソース ロールが割り当てられたときに送信される電子メールの例を次に示します。

[Image: Azure リソース ロールに関する新しい Privileged Identity Management 電子メールを示すスクリーンショット。]

### グループの PIM 通知

次のイベントがグループ割り当ての PIM において発生した場合にのみ、Privileged Identity Management は永続的な所有者にメールを送信します。

- 所有者ロールまたはメンバー ロールの割り当てが承認待ちのとき
- 所有者ロールまたはメンバー ロールが割り当てられたとき
- 所有者ロールまたはメンバーロールの期限が切れそうなとき
- 所有者ロールまたはメンバー ロールが延長資格を得たとき
- 所有者ロールまたはメンバー ロールがエンド ユーザーによって更新されたとき
- 所有者ロールまたはメンバー ロールのアクティブ化要求が完了したとき

グループの PIM のロールの割り当てで次のイベントが発生した場合、Privileged Identity Management によってエンド ユーザーにメールが送信されます。

- ユーザーに所有者ロールまたはメンバー ロールが割り当てられたとき
- ユーザーの所有者ロールまたはメンバー ロールの有効期限が切れている場合
- ユーザーの所有者ロールまたはメンバー ロールが拡張されたとき
- ユーザーの所有者またはメンバー ロールのアクティブ化要求が完了したとき
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-getting-started"} -->
## PIM の使用を開始する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra 管理センターで Privileged Identity Management (PIM) を有効にして使用を開始する方法を説明します。

### 概要

Privileged Identity Management (PIM) を使用すると、Azure Microsoft Entra 組織内のアクセス権を管理、制御、および監視できます。 PIM を使用すると、Azure リソースや Microsoft Entra リソースのほか、Microsoft 365 や Microsoft Intune などのその他の Microsoft オンライン サービスへのアクセスを、必要に応じて適切なタイミングで提供できます。

この記事では、Privileged Identity Management (PIM) を有効にしてその使用を開始する方法について説明します。

### 前提条件

Privileged Identity Management を使用するには、Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス ライセンスが必要です。 ライセンスの詳細については、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### ロールの割り当てのアクティブ化

Microsoft Entra テナントに Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス ライセンスがある場合、アクティブなロールの割り当てを持つユーザーは次の操作を実行できます。

- Microsoft Entra ID で **[ロールと管理者** ] ページを開き、ロールを選択します。
- **Privileged Identity Management ページを**開きます。
- [Microsoft Entra roles API](https://learn.microsoft.com/ja-jp/graph/identity-network-access-overview/) を使用して PIM を呼び出します。

Microsoft Entra では、次の方法でテナントの PIM が有効になります。

- すぐに開始して、Microsoft Entra ロールの有資格または期限付き割り当てを作成できます。
- グローバル管理者または特権ロール管理者は、PIM の週次ダイジェストなど、他のメールの受信を開始できます。
- PIM サービス プリンシパル名 (MS-PIM) は、ロールの割り当て管理に関連する監査ログ イベントに記載されている場合があります。

これらの動作は想定されており、ワークフローに影響を与えることはありません。

### Microsoft Entra ロール用に PIM を準備する

Microsoft Entra ロールを管理するために Privileged Identity Management を準備するために推奨されるタスクを次に示します。

1. [Microsoft Entra ロールの設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)
2. [適格な割り当てを与える](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user)
3. [資格のあるユーザーが Microsoft Entra ロールを Just-In-Time でアクティブ化できるようにする](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-activate-role)

### Azure ロール用に PIM を準備する

サブスクリプションの Azure ロールを管理するために Privileged Identity Management を準備するために推奨されるタスクを次に示します。

1. [Azure リソースを見つける](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-discover-resources)
2. [Azure ロールの設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-role-settings)
3. [適格な割り当てを与える](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles)
4. [資格のあるユーザーが Azure ロールを適切なタイミングでアクティブ化できるようにする](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-activate-your-roles)

### タスクへの移動

Privileged Identity Management の設定が済んだら、使用してみることができます。

[Image: [タスク]、[管理]、[アクティビティ]、[トラブルシューティング + サポート] オプションを示す [Privileged Identity Management] のナビゲーション ウィンドウを示すスクリーンショット。]

| メニュー項目 | 説明 |
| --- | --- |
| **私の役割** | 自分に割り当てられている適格でアクティブなロールの一覧が表示されます。 **[自分のロール]** では、割り当てられた対象ロールをアクティブ化できます。 |
| **個人の要求** | 保留中の適格なロール割り当てのアクティブ化要求が表示されます。 |
| **申請の承認** | 承認できるディレクトリ内のユーザーが対象ロールをアクティブ化するための要求の一覧を表示します。 |
| **アクセスレビュー** | 自分または他のユーザーのアクセスをレビューしているかどうかに関係なく、完了するように割り当てられているアクティブなアクセス レビューを一覧表示します。 |
| **Microsoft Entra ロール** | Microsoft Entra ロールの割り当てを管理するための特権ロール管理者のダッシュボードと設定を表示します。 このダッシュボードは、特権ロール管理者ではないすべてのユーザーに対して無効になります。 これらのユーザーは、[ **マイ ビュー**] というタイトルの特別なダッシュボードにアクセスできます。 **マイ ビュー** ダッシュボードには、組織全体ではなく、ダッシュボードにアクセスしているユーザーに関する情報のみが表示されます。 |
| **グループ** | グループの Just-In-Time メンバーシップまたはグループの Just-In-Time 所有権を管理します。 グループでは、Microsoft Entra ロール、Azure ロール、およびその他のさまざまなシナリオにアクセスできます。 PIM で Microsoft Entra グループを管理するには、そのグループを PIM の管理下に置く必要があります。 |
| **Azure リソース** | Azure リソース ロールの割り当てを管理するための特権ロール管理者のダッシュボードと設定を表示します。 このダッシュボードは、特権ロール管理者ではないすべてのユーザーに対して無効になります。 これらのユーザーは、[ **マイ ビュー**] というタイトルの特別なダッシュボードにアクセスできます。 **マイ ビュー** ダッシュボードには、組織全体ではなく、ダッシュボードにアクセスしているユーザーに関する情報のみが表示されます。 |
| **自分の監査履歴** | すべてのロールのアクティブ化と割り当てを含め、PIM 監査履歴を表示します。 |
| **トラブルシューティング** | PIM の一般的な問題の診断と解決に関するヘルプを表示します。 |
| **新しいサポート要求** | PIM 関連の問題のサポート 要求を作成します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-how-to-activate-role"} -->
## PIM で Microsoft Entra ロールをアクティブ化する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-activate-role
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Microsoft Entra ロールをアクティブにする方法についてご確認ください。

Microsoft Entra Privileged Identity Management (PIM) を使用すると、企業が Microsoft Entra ID や他の Microsoft Online Services (Microsoft 365 や Microsoft Intune など) 内のリソースへの特権アクセスを管理する方法が簡略化されます。

管理者ロールの *資格* が与えられている場合は、特権アクションを実行する必要があるときにロールの割り当てを *アクティブにする* 必要があります。 たとえば、Microsoft 365 の機能を時々しか管理しないユーザーは、組織の特権ロール管理者によって永続的な全体管理者には設定されない可能性があります。このロールは他のサービスにも影響を与えるからです。 その代わりに、Exchange Online 管理者などの Microsoft Entra ロールが割り当てられます。 その特権が必要になった時点で、そのロールのアクティブ化を要求でき、その後、事前に定義された時間だけ管理者として制御できます。

この記事は、Privileged Identity Management で自分の Microsoft Entra ロールをアクティブ化する必要のある管理者を対象としています。 どのユーザーも特権ロール管理者 (PRA) ロールを持たずに PIM を通じて必要なロールの要求を送信できますが、組織内の他のユーザーへのロールの割り当ておよび管理を行うには、このロールが必要です。

重要

ロールがアクティブ化されると、そのロールのアクティブな割り当てが Microsoft Entra PIM によって一時的に追加されます。 アクティブな割り当てが Microsoft Entra PIM によって数秒以内に作成されます (ユーザーがロールに割り当てられます)。 非アクティブ化が (手動またはアクティブ化の有効期限により) 発生する場合も、アクティブな割り当てが Microsoft Entra PIM によって数秒以内に削除されます。

アプリケーションへのアクセスは、ユーザーが持っているロールに基づいて許可される場合があります。 状況によっては、ロールがユーザーに割り当てられたり削除されたりしたことがアプリケーション アクセスにすぐに反映されない場合があります。 ユーザーがロールを持っていないということがアプリケーションによって以前にキャッシュされている場合、ユーザーがアプリケーションにもう一度アクセスしようとしても、アクセスできない可能性があります。 同様に、ユーザーがロールを持っているということがアプリケーションによって以前にキャッシュされている場合、ロールが非アクティブ化されていても、ユーザーは引き続きアクセスできる可能性があります。 特定の状況は、アプリケーションのアーキテクチャによって異なります。 一部のアプリケーションでは、サインアウトしてから再びサインインすると、アクセスの追加または削除を反映するのに役立つ場合があります。

重要

管理ロールをアクティブ化するユーザーがモバイル デバイス上のMicrosoft Teamsにサインインしている場合は、Teams アプリから、"Open Teams to continue receiving notifications for *&lt;email address&gt;*"、または "*&lt;email address&gt;* needs to see notifications" という通知を受け取ります。 ユーザーが通知を受け取り続けるには、Teams アプリを開く必要があります。 この動作は設計によるものです。

### 前提条件

なし

### ロールのアクティブ化

Microsoft Entra ロールを引き受ける必要がある場合は、Privileged Identity Management で **[マイ ロール** ] を開いてアクティブ化を要求できます。

注

Azure mobile app (iOS と Android) で、Microsoft Entra ID と Azure リソースのロール用に、PIM を利用できるようになりました。 対象となる割り当てを簡単にアクティブにしたり、有効期限が切れたものの更新を要求したり、保留されている要求の状態を確認したりできます。 続きを読む

1. 対象となるロールの割り当てを持つユーザーとしてMicrosoft Entra 管理センターにサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**My ロール**を参照します。 ダッシュボードに Privileged Identity Management タイルを追加する方法については、「 [Privileged Identity Management の使用を開始](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started)する」を参照してください。
3. **Microsoft Entra ロール**を選択すると、対象となる Microsoft Entra ロールの一覧が表示されます。

    [Image: アクティブ化できるロールが表示されている [自分のロール] ページ]
4. **Microsoft Entra ロール**の一覧で、アクティブ化するロールを見つけます。

    [Image: Microsoft Entra ロール - 対象となるロールの一覧]
5. **[アクティブ化] を**選択して [アクティブ化] ウィンドウを開きます。

    [Image: Microsoft Entra ロール - アクティブ化ページに期間とスコープが含まれています]
6. [ **追加の確認が必要]** を選択し、指示に従ってセキュリティ検証を提供します。 認証が要求されるのは、各セッションにつき 1 回だけです。

    [Image: PIN コードなどのセキュリティ検証を提供する画面]
7. 多要素認証の後、 **続行する前に [アクティブ化**] を選択します。

    [Image: ロールがアクティブ化される前に MFA を使用して ID を確認する]
8. スコープを縮小して指定する場合は、[ **スコープ** ] を選択してフィルター ウィンドウを開きます。 [フィルター] ペインでは、アクセス権が必要な Microsoft Entra リソースを指定できます。 必要最低限のリソースに対するアクセスを要求することをお勧めします。
9. 必要に応じて、カスタムのアクティブ化開始時刻を指定します。 Microsoft Entra ロールは、選択した時刻になるとアクティブになります。
10. [ **理由** ] ボックスに、アクティブ化要求の理由を入力します。
11. **[アクティブ化] を選択します**。

    アクティブ化するために [ロールに承認が必要](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow) な場合は、要求が承認待ちであることを通知する通知がブラウザーの右上隅に表示されます。

    [Image: アクティブ化要求が承認待ちの通知]

### Microsoft Graph API を使用してロールをアクティブにする

    PIM 用の Microsoft Graph API の詳細については、「 [特権 ID 管理 (PIM) API を使用したロール管理の概要」を](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview)参照してください。

#### アクティブにできる、資格のあるロールをすべて取得する

    ユーザーがグループ メンバーシップを使用して自分のロールの適格性を取得した場合、この Microsoft Graph 要求では適格性は返されません。

##### HTTP 要求

    ```HTTP
    GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleEligibilityScheduleRequests/filterByCurrentUser(on='principal')  
    ```

##### HTTP 応答

    領域を節約するために、あるロールの応答のみが表示されていますが、アクティブにできる、資格のあるロールの割り当てすべてが一覧表示されます。

    ```HTTP
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#Collection(unifiedRoleEligibilityScheduleRequest)",
        "value": [
            {
                "@odata.type": "#microsoft.graph.unifiedRoleEligibilityScheduleRequest",
                "id": "50d34326-f243-4540-8bb5-2af6692aafd0",
                "status": "Provisioned",
                "createdDateTime": "2022-04-12T18:26:08.843Z",
                "completedDateTime": "2022-04-12T18:26:08.89Z",
                "approvalId": null,
                "customData": null,
                "action": "adminAssign",
                "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
                "roleDefinitionId": "8424c6f0-a189-499e-bbd0-26c1753c96d4",
                "directoryScopeId": "/",
                "appScopeId": null,
                "isValidationOnly": false,
                "targetScheduleId": "50d34326-f243-4540-8bb5-2af6692aafd0",
                "justification": "Assign Attribute Assignment Admin eligibility to myself",
                "createdBy": {
                    "application": null,
                    "device": null,
                    "user": {
                        "displayName": null,
                        "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
                    }
                },
                "scheduleInfo": {
                    "startDateTime": "2022-04-12T18:26:08.8911834Z",
                    "recurrence": null,
                    "expiration": {
                        "type": "afterDateTime",
                        "endDateTime": "2024-04-10T00:00:00Z",
                        "duration": null
                    }
                },
                "ticketInfo": {
                    "ticketNumber": null,
                    "ticketSystem": null
                }
            }
        ]
    }
    ```

#### 正当な理由を指定してロールの適格性を自分でアクティブにする

##### HTTP 要求

    ```HTTP
    POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignmentScheduleRequests 
    
    {
        "action": "selfActivate",
        "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
        "roleDefinitionId": "8424c6f0-a189-499e-bbd0-26c1753c96d4",
        "directoryScopeId": "/",
        "justification": "I need access to the Attribute Administrator role to manage attributes to be assigned to restricted AUs",
        "scheduleInfo": {
            "startDateTime": "2022-04-14T00:00:00.000Z",
            "expiration": {
                "type": "AfterDuration",
                "duration": "PT5H"
            }
        },
        "ticketInfo": {
            "ticketNumber": "CONTOSO:Normal-67890",
            "ticketSystem": "MS Project"
        }
    }
    ```

##### HTTP 応答

    ```HTTP
    HTTP/1.1 201 Created
    Content-Type: application/json
    
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#roleManagement/directory/roleAssignmentScheduleRequests/$entity",
        "id": "911bab8a-6912-4de2-9dc0-2648ede7dd6d",
        "status": "Granted",
        "createdDateTime": "2022-04-13T08:52:32.6485851Z",
        "completedDateTime": "2022-04-14T00:00:00Z",
        "approvalId": null,
        "customData": null,
        "action": "selfActivate",
        "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
        "roleDefinitionId": "8424c6f0-a189-499e-bbd0-26c1753c96d4",
        "directoryScopeId": "/",
        "appScopeId": null,
        "isValidationOnly": false,
        "targetScheduleId": "911bab8a-6912-4de2-9dc0-2648ede7dd6d",
        "justification": "I need access to the Attribute Administrator role to manage attributes to be assigned to restricted AUs",
        "createdBy": {
            "application": null,
            "device": null,
            "user": {
                "displayName": null,
                "id": "071cc716-8147-4397-a5ba-b2105951cc0b"
            }
        },
        "scheduleInfo": {
            "startDateTime": "2022-04-14T00:00:00Z",
            "recurrence": null,
            "expiration": {
                "type": "afterDuration",
                "endDateTime": null,
                "duration": "PT5H"
            }
        },
        "ticketInfo": {
            "ticketNumber": "CONTOSO:Normal-67890",
            "ticketSystem": "MS Project"
        }
    }
    ```

### アクティブ化要求の状態を表示する

保留中のアクティブ化要求の状態を表示することができます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**My 要求**を参照します。
3. [ **自分の要求** ] を選択すると、Microsoft Entra ロールと Azure リソース ロールの要求の一覧が表示されます。

    [Image: [自分の要求] のスクリーンショット - 保留中の要求を示す Microsoft Entra ID ページ]
4. 右にスクロールして [ **要求の状態]** 列を表示します。

### 新しいバージョンの保留要求をキャンセルする

承認が要求されるロールのアクティブ化を必要としない場合、保留中の要求をいつでもキャンセルできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**My 要求**を参照します。
3. 取り消すロールについては、[ **キャンセル** ] リンクを選択します。

    [キャンセル] を選択すると、要求が取り消されます。 ロールをもう一度アクティブにするには、新しいアクティブ化要求を送信する必要があります。

    [Image: [キャンセル] アクションが強調表示された [自分の要求] リスト]

### ロールの割り当てを非アクティブ化する

ロールの割り当てがアクティブになると、PIM ポータルにロールの割り当ての **非アクティブ化** オプションが表示されます。 また、アクティブ化してから 5 分以内にロールの割り当てを非アクティブ化することはできません。

### Azure mobile app を使用して PIM のロールをアクティブにする

Microsoft Entra ID と Azure リソース ロール モバイル アプリ (iOS と Android の両方) で、PIM を利用できるようになりました。

注

サインインしているユーザーがアプリ内でこれを使用するには、アクティブな Premium P2 または EMS E5 ライセンスが必要です。

1. 対象となる Microsoft Entra ロールの割り当てをアクティブにするには、まず Azure モバイル アプリ ([iOS](https://apps.apple.com/us/app/microsoft-azure/id1219013620) | [Android](https://play.google.com/store/apps/details?id=com.microsoft.azure)) をダウンロードします。 [Privileged Identity Management] &gt; [マイ ロール] &gt; [Microsoft Entra ロール] から [モバイルで開く] を選んで、アプリをダウンロードすることもできます。

    [Image: モバイル アプリをダウンロードする方法を示すスクリーンショット。]
2. Azure mobile app を開いてサインインします。 **[Privileged Identity Management**] カードを選択し、[**My Microsoft Entra roles]\(マイ Microsoft Entra ロール**\) を選択して、対象となるロールとアクティブなロールの割り当てを表示します。

    [Image: ユーザーが使用可能なロールを表示する方法を示すモバイル アプリのスクリーンショット。]
3. ロールの割り当てを選択し、ロールの割り当ての詳細で [ **アクション] &gt; [アクティブ化** ] をクリックします。 アクティブにして必要な詳細を入力する手順を完了した後、下部にある [アクティブ化] をクリックします。

    [Image: ユーザーが必要な情報を入力する方法を示すモバイル アプリのスクリーンショット]
4. [ **My Microsoft Entra roles]\(マイ Microsoft Entra** ロール\) で、アクティブ化要求の状態とロールの割り当てを表示します。

    [Image: ユーザーのロールの状態を示すモバイル アプリのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user"} -->
## PIM で Microsoft Entra ロールを割り当てる - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Microsoft Entra ロールを割り当てる方法について説明します。

### 概要

Microsoft Entra ID を使用すると、グローバル管理者は **永続的な** Microsoft Entra 管理者ロールの割り当てを行うことができます。 これらのロールの割り当ては、 [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) または [PowerShell コマンド](https://learn.microsoft.com/ja-jp/powershell/module/azuread/#directory_roles)を使用して作成できます。

永続的な管理ロールの割り当ては、特権ロール管理者が Microsoft Entra Privileged Identity Management (PIM) サービスを使用して行うこともできます。 さらに、特権ロール管理者は、ユーザーを Microsoft Entra 管理者ロールの **対象** にすることができます。 管理者候補は必要なときにロールをアクティブ化できます。作業が完了すると、そのアクセス許可は期限切れになります。

Privileged Identity Management では、組み込みロールとカスタム Microsoft Entra ロールの両方がサポートされます。 Microsoft Entra カスタム ロールの詳細については、「Microsoft [Entra ID でのロールベースのアクセス制御」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)参照してください。

注

ロールが割り当てられている場合、割り当ては次のようになります。

- 5 分未満の期間は割り当てることができません。
- 割り当てられてから 5 分以内に削除することはできません。

### ロールの割り当て

ユーザーを Microsoft Entra 管理者ロールの候補にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[ID ガバナンス]**&gt;、**[Privileged Identity Management]**&gt;、**[Microsoft Entra ロール]** を参照します。
3. [ **ロール]** を選択すると、Microsoft Entra アクセス許可のロールの一覧が表示されます。

    [Image: [割り当ての追加] アクションが選択されている [ロール] ページのスクリーンショット。]
4. [ **割り当ての追加]** を選択して、[ **割り当ての追加] ページを** 開きます。
5. [ **ロールの選択] を選択** して、[ **ロールの選択** ] ページを開きます。

    [Image: 新しい割り当てウィンドウを示すスクリーンショット。]
6. 割り当てるロールを選択し、ロールに割り当てるメンバーを選択して、[ **次へ**] を選択します。

    ユーザー、グループ、またはエージェント ID を選択できます。 エージェント ID に割り当てることができるロールの一覧については、「 [Microsoft Entra エージェント ID での承認](https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id)」を参照してください。

    注

    ゲスト ユーザーに Microsoft Entra 組み込みロールを割り当てると、ゲスト ユーザーはメンバー ユーザーと同じアクセス許可を持つよう昇格されます。 メンバーとゲスト ユーザーの既定のアクセス許可の詳細については、「[Microsoft Entra ID の既定のユーザーアクセス許可とは」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)参照してください。
7. [**メンバーシップの設定**] ウィンドウの [**割り当ての種類**] の一覧で、[**対象]** または [**アクティブ**] を選択します。

    - **有資格** の割り当てでは、ロールのメンバーがロールを使用するためのアクションを実行する必要があります。 アクションには、多要素認証 (MFA) チェックの実行、業務上の妥当性の指定、指定された承認者に対する承認要求などがあります。
    - **アクティブな** 割り当てでは、メンバーがロールを使用するためのアクションを実行する必要はありません。 アクティブとして割り当てられたメンバーは、常にそのロールに割り当てられた特権を持ちます。
8. 特定の割り当て期間を指定するには、開始日時と終了日時のボックスを追加します。 完了したら、[ **割り当て** ] を選択して新しいロールの割り当てを作成します。

    - **永続的な** 割り当てには有効期限はありません。 このオプションは、ロールのアクセス許可を頻繁に必要とする永続的なワーカーに対して使用します。
    - **期限付き** 割り当ては、指定した期間の終了時に期限切れになります。 このオプションは、たとえばプロジェクトの終了日時がわかっている、一時的なまたはコントラクト ワーカーで使用します。

    注

    グローバル管理者ロールのアクティブな期限付きロールの割り当ては、グローバル管理者に割り当てられた他のアクティブなロールの割り当てが存在しない場合、有効期限時には削除されません。 つまり、グローバル管理者ロールに対してアクティブなロールの割り当てが最後に割り当てられた場合、そのロールは残ります。 同様に、グローバル管理者ロールに割り当てられた他のロールの割り当てが存在しない場合、つまり最後のグローバル管理者ロールの割り当てである場合、グローバル管理者ロールの有資格の期限付きロールの割り当ては、有効期限時に削除されません。 これは、管理者が誤ってテナントからロックアウトするリスクを最小限に抑えるために行われます。

    [Image: メンバーシップの設定 -日付と時刻を示すスクリーンショット。]
9. ロールが割り当てられると、割り当て状態の通知が表示されます。

    [Image: 新しい割り当て通知を示すスクリーンショット。]

### 制限スコープがあるロールを割り当てる

特定のロールでは、付与されるアクセス許可の範囲を 1 つの管理単位、サービス プリンシパル、またはアプリケーションに制限することができます。 この手順は、管理単位のスコープを持つロールを割り当てる例です。 管理単位によるスコープをサポートするロールの一覧については、「管理単位スコープ [を持つロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。 この機能は、現在 Microsoft Entra 組織に展開されています。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID**&gt;**役割と管理者**に移動します。
3. **ユーザー管理者**を選択します。

    [Image: ポータルでロールを開くと、[割り当ての追加] コマンドが使用可能であることを示すスクリーンショット。]
4. **[割り当ての追加]** を選択します。

    [Image: ロールがスコープをサポートするタイミングを示すスクリーンショット。スコープを選択できます。]
5. [ **割り当ての追加]** ページでは、次のことができます。

    - ロールに割り当てるユーザーまたはグループを選択します。
    - ロール スコープ (この場合は管理単位) を選択します。
    - スコープの管理単位を選択します。

管理単位と互換性のあるカスタム ロールを割り当てようとすると、[ロール **と管理者** ] ページから開始して、管理単位のスコープを持つロールを割り当てることはできません。 管理単位スコープを持つカスタム ロールを割り当てるには、まず管理単位を開き、次にロールを割り当てる必要があります。 詳細については、「 [管理単位スコープを持つロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。 管理単位の作成については、管理単位の [作成または削除](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-manage)を参照してください。

### Microsoft Graph API を使用してロールを割り当てる

PIM 用の Microsoft Graph API の詳細については、「 [特権 ID 管理 (PIM) API を使用したロール管理の概要」を](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview)参照してください。

PIM API を使用するために必要なアクセス許可については、「 [Privileged Identity Management API について」を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-apis)参照してください。

#### 終了日なしの有資格

この例は、終了日のない、資格のある割り当てを作成するための HTTP 要求を示しています。 C# や JavaScript などの言語での要求サンプルを含む API コマンドの詳細については、「 [roleEligibilityScheduleRequests の作成](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleeligibilityschedulerequests)」を参照してください。

##### HTTP 要求

```HTTP
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleEligibilityScheduleRequests
Content-Type: application/json

{
    "action": "adminAssign",
    "justification": "Permanently assign the Global Reader to the auditor",
    "roleDefinitionId": "f2ef992c-3afb-46b9-b7cf-a126ee74c451",
    "directoryScopeId": "/",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "scheduleInfo": {
        "startDateTime": "2022-04-10T00:00:00Z",
        "expiration": {
            "type": "noExpiration"
        }
    }
}
```

##### HTTP 応答

この例は応答を示しています。 ここに示されている応答オブジェクトは、読みやすくするために短縮されている可能性があります。

```HTTP
HTTP/1.1 201 Created
Content-Type: application/json

{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#roleManagement/directory/roleEligibilityScheduleRequests/$entity",
    "id": "42159c11-45a9-4631-97e4-b64abdd42c25",
    "status": "Provisioned",
    "createdDateTime": "2022-05-13T13:40:33.2364309Z",
    "completedDateTime": "2022-05-13T13:40:34.6270851Z",
    "approvalId": null,
    "customData": null,
    "action": "adminAssign",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "roleDefinitionId": "f2ef992c-3afb-46b9-b7cf-a126ee74c451",
    "directoryScopeId": "/",
    "appScopeId": null,
    "isValidationOnly": false,
    "targetScheduleId": "42159c11-45a9-4631-97e4-b64abdd42c25",
    "justification": "Permanently assign the Global Reader to the auditor",
    "createdBy": {
        "application": null,
        "device": null,
        "user": {
            "displayName": null,
            "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
        }
    },
    "scheduleInfo": {
        "startDateTime": "2022-05-13T13:40:34.6270851Z",
        "recurrence": null,
        "expiration": {
            "type": "noExpiration",
            "endDateTime": null,
            "duration": null
        }
    },
    "ticketInfo": {
        "ticketNumber": null,
        "ticketSystem": null
    }
}
```

#### アクティブで期限付き

この例では、時間バインドされたアクティブな割り当てを作成するための HTTP 要求を示します。 C# や JavaScript などの言語での要求サンプルを含む API コマンドの詳細については、「 [roleAssignmentScheduleRequests の作成」](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignmentschedulerequests)を参照してください。

##### HTTP 要求

```HTTP
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignmentScheduleRequests 

{
    "action": "adminAssign",
    "justification": "Assign the Exchange Recipient Administrator to the mail admin",
    "roleDefinitionId": "31392ffb-586c-42d1-9346-e59415a2cc4e",
    "directoryScopeId": "/",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "scheduleInfo": {
        "startDateTime": "2022-04-10T00:00:00Z",
        "expiration": {
            "type": "afterDuration",
            "duration": "PT3H"
        }
    }
}
```

##### HTTP 応答

この例は応答を示しています。 ここに示されている応答オブジェクトは、読みやすくするために短縮されている可能性があります。

```HTTP
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#roleManagement/directory/roleAssignmentScheduleRequests/$entity",
    "id": "ac643e37-e75c-4b42-960a-b0fc3fbdf4b3",
    "status": "Provisioned",
    "createdDateTime": "2022-05-13T14:01:48.0145711Z",
    "completedDateTime": "2022-05-13T14:01:49.8589701Z",
    "approvalId": null,
    "customData": null,
    "action": "adminAssign",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "roleDefinitionId": "31392ffb-586c-42d1-9346-e59415a2cc4e",
    "directoryScopeId": "/",
    "appScopeId": null,
    "isValidationOnly": false,
    "targetScheduleId": "ac643e37-e75c-4b42-960a-b0fc3fbdf4b3",
    "justification": "Assign the Exchange Recipient Administrator to the mail admin",
    "createdBy": {
        "application": null,
        "device": null,
        "user": {
            "displayName": null,
            "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
        }
    },
    "scheduleInfo": {
        "startDateTime": "2022-05-13T14:01:49.8589701Z",
        "recurrence": null,
        "expiration": {
            "type": "afterDuration",
            "endDateTime": null,
            "duration": "PT3H"
        }
    },
    "ticketInfo": {
        "ticketNumber": null,
        "ticketSystem": null
    }
}
```

### 既存のロールの割り当てを更新または削除する

既存のロールの割り当てを更新または削除するには、次の手順を実行します。 グローバル管理者の最後のアクティブなロールの割り当てを削除することはできません。 グローバル管理者ロールに対してアクティブで永続的なロールの割り当てを持つ緊急アクセス アカウントを持つことを検討します。 詳細については、「 [緊急アクセスアカウント」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)参照してください。 全体管理者ロールの割り当てが1つも残らない場合、全体管理者の対象ロールの割り当てを削除することはできません。 これは、管理者が誤ってテナントから自分自身をロックアウトするリスクを最小限に抑えるために行われます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[ID ガバナンス]**&gt;、**[Privileged Identity Management]**&gt;、**[Microsoft Entra ロール]** を参照します。
3. [ **ロール** ] を選択すると、Microsoft Entra ID のロールの一覧が表示されます。
4. 更新または削除するロールを選択します。
5. [対象ロール] タブまたは [**アクティブな**ロール] タブで**ロール**の割り当てを見つけます。

    [Image: ロールの割り当てを更新または削除する方法を示すスクリーンショット。]
6. [ **更新]** または **[削除] を** 選択して、ロールの割り当てを更新または削除します。

### Microsoft Graph API を使用して資格のある割り当てを削除する

プリンシパルに対するロールの適格な割り当てを取り消すための HTTP 要求を示す例です。 C# や JavaScript などの言語での要求サンプルを含む API コマンドの詳細については、「 [roleEligibilityScheduleRequests の作成](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleeligibilityschedulerequests)」を参照してください。

#### リクエスト

```HTTP
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleEligibilityScheduleRequests 

{ 
    "action": "AdminRemove", 
    "justification": "abcde", 
    "directoryScopeId": "/", 
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222", 
    "roleDefinitionId": "88d8e3e3-8f55-4a1e-953a-9b9898b8876b" 
} 
```

#### [応答]

```HTTP
{ 
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#roleManagement/directory/roleEligibilityScheduleRequests/$entity", 
    "id": "fc7bb2ca-b505-4ca7-ad2a-576d152633de", 
    "status": "Revoked", 
    "createdDateTime": "2021-07-15T20:23:23.85453Z", 
    "completedDateTime": null, 
    "approvalId": null, 
    "customData": null, 
    "action": "AdminRemove", 
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222", 
    "roleDefinitionId": "88d8e3e3-8f55-4a1e-953a-9b9898b8876b", 
    "directoryScopeId": "/", 
    "appScopeId": null, 
    "isValidationOnly": false, 
    "targetScheduleId": null, 
    "justification": "test", 
    "scheduleInfo": null, 
    "createdBy": { 
        "application": null, 
        "device": null, 
        "user": { 
            "displayName": null, 
            "id": "5d851eeb-b593-4d43-a78d-c8bd2f5144d2" 
        } 
    }, 
    "ticketInfo": { 
        "ticketNumber": null, 
        "ticketSystem": null 
    } 
} 
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings"} -->
## PIM で Microsoft Entra ロール設定を構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Microsoft Entra ロール設定を構成する方法について説明します。

### 概要

Microsoft Entra の一部である Microsoft Entra ID の Privileged Identity Management (PIM) では、ロール設定によってロールの割り当てプロパティが定義されます。 これらのプロパティには、アクティブ化の多要素認証と承認要件、割り当ての最大期間、通知設定が含まれます。 この記事では、ロール設定を構成し、承認ワークフローを設定して、特権を昇格させる要求を承認または拒否できるユーザーを指定する方法について説明します。

Microsoft Entra ロールの PIM ロール設定を管理するには、少なくとも特権ロール管理者ロールが必要です。 ロール設定はロールごとに定義されます。 同じロールに対するすべての割り当ては、同じロール設定に従います。 あるロールのロール設定は、別のロールのロール設定とは独立しています。

PIM ロールの設定は、PIM ポリシーとも呼ばれます。

### ロールの設定を開く

Microsoft Entra ロールの設定を開くには、次を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[ID ガバナンス]**&gt;**[特権 ID 管理]**&gt;**[Microsoft Entra ロール]**&gt;**[ロール]** を参照します。
3. このページには、組み込みロールやカスタム ロールなど、テナントで使用できる Microsoft Entra ロールの一覧が表示されます。 [Image: 組み込みロールやカスタム ロールなど、テナントで使用できる Microsoft Entra ロールの一覧を示すスクリーンショット。]
4. 設定を構成するロールを選択します。
5. **ロールの設定**を選択します。 [ **ロールの設定]** ページで、選択したロールの現在の PIM ロール設定を表示できます。

    [Image: 割り当てとアクティブ化の設定を更新するためのオプションを含む [ロールの設定] ページを示すスクリーンショット。]
6. [ **編集] を** 選択してロール設定を更新します。
7. [ **更新] を**選択します。

### ロールの設定

このセクションでは、ロール設定オプションについて説明します。

#### アクティブ化の最大期間

**[アクティブ化の最大期間**] スライダーを使用して、ロールの割り当てのアクティブ化要求が期限切れになる前にアクティブなままになる最大時間 (時間単位) を設定します。 1 から 24 時間の範囲の値を指定できます。

#### アクティブ化の際に多要素認証を要求する

ロールの資格を持つユーザーに対して、アクティブ化の前に Microsoft Entra ID の多要素認証機能を使用して本人であることを証明するよう要求できます。 多要素認証によりアプリケーションやデータへのアクセスを保護できます。 第 2 の形式の認証を使用すると、新たなセキュリティ レイヤーが追加されます。

ユーザーが強力な資格情報を使用して認証したり、セッションの早い段階で多要素認証を提供したりすると、多要素認証が求められないことがあります。

ユーザーがアクティブ化中に認証を必ず行う必要がある場合は、[アクティブ化時に Microsoft Entra 条件付きアクセス認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#on-activation-require-azure-ad-conditional-access-authentication-context)を[認証強度](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths)と一緒に使用することができます。 これらのオプションでは、ユーザーは、マシンへのサインインに使用した方法とは異なる方法を使用して、アクティブ化の間に認証を行う必要があります。

たとえば、ユーザーが Windows Hello for Business を使用してマシンにサインインした場合は、 **アクティブ化時に Microsoft Entra 条件付きアクセス認証コンテキスト** と **認証強度**を要求できます。 このオプションでは、ユーザーはロールをアクティブ化するときに、Microsoft Authenticator を使用してパスワードレス サインインを行う必要があります。

この例では、ユーザーが Microsoft Authenticator を使用してパスワードレス サインインを一度行うと、このセッションにおいては別の認証なしで次のアクティブ化を実行できます。 Microsoft Authenticator を使用したパスワードレス サインインは、既にトークンの一部になっています。

すべてのユーザーに対して Microsoft Entra ID の多要素認証機能を有効にします。 詳細については、「 [Microsoft Entra 多要素認証の展開を計画](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)する」を参照してください。

#### アクティブ化の際に、Microsoft Entra Conditional Access 認証コンテキストを要求する

ロールの資格を持つユーザーに、条件付きアクセス ポリシーの要件を満たすことを要求できます。 たとえば、認証強度によって適用される特定の認証方法の使用、Intune に準拠したデバイスからのロールの昇格、利用規約の準拠などをユーザーに要求できます。

この要件を適用するには、条件付きアクセス認証コンテキストを作成します。

1. この認証コンテキストの要件を適用する条件付きアクセス ポリシーを構成します。

    条件付きアクセス ポリシーのスコープには、すべてのユーザーまたはロールの対象ユーザーを含める必要があります。 認証コンテキストとディレクトリ ロールにスコープ指定されている条件付きアクセス ポリシーを同時に作成しないでください。 アクティブ化の間は、ユーザーにはロールがまだないため、条件付きアクセス ポリシーは適用されません。

    2 つの条件付きアクセス ポリシーが必要になる可能性のある状況については、このセクションの最後にある手順を参照してください。 1 つは認証コンテキストにスコープ指定される必要があり、もう 1 つはロールにスコープ指定される必要があります。
2. ロールの PIM 設定で認証コンテキストを構成します。

    [Image: [ロールの編集] 設定の [属性定義管理者] ページを示すスクリーンショット。]

PIM 設定で **アクティブ化時に Microsoft Entra 条件付きアクセス認証コンテキストが必要** に構成されている場合、条件付きアクセス ポリシーはユーザーがアクセス要件を満たすために必要な条件を定義します。

つまり、条件付きアクセス管理者やセキュリティ管理者など、条件付きアクセス ポリシーを管理する権限を持つセキュリティ プリンシパルは、要件の変更や削除を行ったり、資格のあるユーザーがロールをアクティブ化するのをブロックしたりすることができます。 条件付きアクセス ポリシーを管理できるセキュリティ プリンシパルは、高度な特権を持つと見なし、それに応じて保護する必要があります。

PIM 設定で認証コンテキストを構成する前に、認証コンテキストの条件付きアクセス ポリシーを作成して有効にします。 バックアップ保護メカニズムとして、PIM 設定で構成された認証コンテキストを対象とする条件付きアクセス ポリシーがテナント内に存在しない場合、PIM ロールのアクティブ化中に、Microsoft Entra ID の多要素認証機能が [\[オン\] アクティブ化として必要であり、多要素認証](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#on-activation-require-multifactor-authentication) 設定が設定されている必要があります。

このバックアップ保護メカニズムは、構成ミスのために条件付きアクセス ポリシーの作成前に PIM 設定が更新された場合にのみ、保護するように設計されています。 条件付きアクセス ポリシーがオフになっている、レポート専用モードになっている、または対象ユーザーがポリシーから除外されている場合、このバックアップ保護メカニズムはトリガーされません。

すべてのロールのアクティブ化に再認証を適用するには、**セッション制御**の下でサインイン頻度を **[毎回**] に設定して、認証コンテキストを対象とする条件付きアクセス ポリシーを構成します。 これにより、ユーザーは、アクティブなセッションを持っていても、特権ロールをアクティブ化するたびに再認証する必要があります。

[Image: Microsoft Entra 条件付きアクセス認証コンテキスト オプションが選択されている [ロール設定の編集] ページを示すスクリーンショット。]

ユーザーが 1 つのロールのアクティブ化を再認証すると、10 分のウィンドウが適用されます。 ユーザーがこのウィンドウ内で別の有資格ロールをアクティブ化した場合、再認証を求めるメッセージは表示されません。 10 分間の期間は、Microsoft Entra ロール、Azure リソース ロール、グループの PIM に適用されます。

ユーザーが認証コンテキストで構成された有資格ロールをアクティブ化すると、"条件付きアクセス ポリシーが有効になっており、追加の検証が必要になる場合があります。 クリックして続行します。"その後、ユーザーは、条件付きアクセス ポリシーで定義されている再認証を完了するようにリダイレクトされます。

[Image: 追加の検証を必要とする条件付きアクセス ポリシー バナーが表示された [ロールのアクティブ化] ページを示すスクリーンショット。]

**アクティブ化時には、Microsoft Entra 条件付きアクセス認証コンテキスト**の設定で、ユーザーがロールをアクティブ化するときに満たす必要がある認証コンテキスト要件を定義します。 ロールがアクティブ化されると、ユーザーは別のブラウズ セッション、デバイス、または場所を使用してアクセス許可を使用することができなくなります。

たとえば、ユーザーは Intune に準拠しているデバイスを使用してロールをアクティブ化する場合があります。 そして、ロールがアクティブ化された後で、ユーザーは Intune に準拠していない別のデバイスから同じユーザー アカウントにサインインし、そこから以前にアクティブ化されたロールを使用する可能性があります。

このような状況を防ぐために、次の 2 つの条件付きアクセス ポリシーを作成します。

1. 認証コンテキストをターゲットとする 1 つ目の条件付きアクセス ポリシー。 スコープにすべてのユーザーまたは対象ユーザーを含める必要があります。 このポリシーは、ロールをアクティブ化するためにユーザーが満たす必要のある要件を指定します。
2. ディレクトリ ロールをターゲットとする 2 つ目の条件付きアクセス ポリシー。 このポリシーは、ディレクトリ ロールを有効にしてサインインするためにユーザーが満たす必要のある要件を指定します。

どちらのポリシーでも、ニーズに応じて同じ要件または異なる要件を適用できます。

もう 1 つのオプションは、特定の要件を対象ユーザーに直接適用する条件付きアクセス ポリシーの範囲を設定することです。 たとえば、特定のロールの対象であるユーザーに、常に Intune に準拠しているデバイスを使用するように要求できます。

条件付きアクセス認証コンテキストの詳細については、「 [条件付きアクセス: クラウド アプリ、アクション、認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)」を参照してください。

#### アクティブ化の正当な理由を要求する

ユーザーが資格のある割り当てをアクティブにするときに、業務上の正当な理由を入力するように要求できます。

#### アクティブ化時にチケット情報を要求する

ユーザーが資格のある割り当てをアクティブにするときに、サポート チケット番号を入力するように要求できます。 このオプションは情報のみのフィールドです。 チケット発行システム内の情報との相関関係は適用されません。

#### アクティブ化の承認を必須にする

適格な割り当てのアクティブ化に対する承認が必要です。 承認者はロールを持っている必要はありません。 このオプションを使用する場合は、少なくとも 1 人の承認者を選択します。 少なくとも 2 人の承認者を選択することをお勧めします。 特定の承認者が選択されていない場合、アクティブな特権ロール管理者/グローバル管理者が既定の承認者になります。

Important

次のすべての条件に該当する場合は、テナントからロックアウトされます。

- すべての特権ロール管理者/グローバル管理者は、有効な割り当てを持っていますが、現在アクティブなものはありません。
- アクティブ化には承認が必要です。
- 承認者が設定されていません。 **緊急アクセスアカウント**を構成し、特定の承認者を構成することで[、このような状況を回避](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)します。

承認に関する詳細については、[Privileged Identity Management での Microsoft Entra ロールへの要求を承認または拒否](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow) を参照してください。

#### 割り当て期間

ロールの設定を構成するときに、割り当ての種類 (*対象*と*アクティブ*) ごとに 2 つの割り当て期間オプションから選択できます。 これらのオプションは、Privileged Identity Management でユーザーがロールに割り当てられるときの既定の最大期間になります。

次の適格な割り当て期間から 1 つを選択できます。

| 設定 | 説明 |
| --- | --- |
| 永続的な有資格割り当てを許可する | リソース管理者は、永続的に適格な割り当てを行うことができます。 |
| 資格のある割り当てが次の期間後に期限切れになる | リソース管理者は、すべての資格のある割り当てに、開始日と終了日の指定を必須にすることができます。 |

また、次のアクティブな割り当て期間オプションから 1 つを選択できます。

| 設定 | 説明 |
| --- | --- |
| 永続的なアクティブ割り当てを許可する | リソース管理者は、永続的にアクティブな割り当てを行うことができます。 |
| アクティブな割り当てが次の期間後に期限切れになる | リソース管理者は、すべてのアクティブな割り当てに、開始日と終了日の指定を必須にすることができます。 |

グローバル管理者と特権ロール管理者は、指定された終了日を持つすべての割り当てを更新できます。 また、ユーザーはセルフサービス要求を開始して [、ロールの割り当てを延長または更新することもできます](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend)。

#### アクティブな割り当てに多要素認証を要求する

管理者が (資格のある割り当てではなく) アクティブな割り当てを作成するときに、多要素認証を行うように管理者に要求できます。 ユーザーがロールの割り当てを使うときは、ロールが割り当てられた時点からロール内で既にアクティブになっているため、Privileged Identity Management で多要素認証を強制することはできません。

管理者は、強力な資格情報で認証を行った場合、またはこのセッションで既に多要素認証を行っている場合、多要素認証を求められないことがあります。

#### アクティブな割り当ての理由を要求する

ユーザーが (資格のある割り当てではなく) アクティブな割り当てを作成するときに、業務上の正当な理由を入力するように要求できます。

[**ロールの設定**] ページの [**通知**] タブで、Privileged Identity Management を使用して、通知を受け取るユーザーと受け取る通知をきめ細かく制御できます。 次のオプションがあります。

- **メールをオフにする**: 既定の受信者チェックボックスをオフにして他の受信者を削除することで、特定のメールをオフにすることができます。
- **メールを指定したメール アドレスに制限する**: 既定の受信者に送信されるメールをオフにするには、既定の受信者のチェック ボックスをオフにします。 その後で、受信者として他のメール アドレスを追加できます。 複数のメール アドレスを追加する場合は、セミコロン (;) を使って区切ります。
- **既定の受信者と他の受信者の両方にメールを送信する**: 既定の受信者と別の受信者の両方にメールを送信できます。 既定の受信者のチェックボックスをオンにして、他の受信者のメール アドレスを追加します。
- **重要なメールのみ**: メールの種類ごとに、重要なメールのみを受信するチェック ボックスをオンにできます。 このオプションにより、メールで即時のアクションが求められている場合にのみ、指定された受信者に、Privileged Identity Management から引き続きメールが送信されます。 たとえば、ユーザーにロールの割り当ての延長を求めるメールはトリガーされません。 管理者に延長要求を承認するよう求めるメールがトリガーされます。

注

Privileged Identity Management の 1 つのイベントで、複数の受信者 (担当者、承認者、管理者) へのメール通知を生成できます。 1 つのイベントごとに送信される通知の最大数は 1,000 です。 受信者の数が 1,000 を超える場合、最初の 1,000 人の受信者にのみメール通知が送信されます。 これにより、他の担当者、管理者、または承認者が Microsoft Entra ID と Privileged Identity Management でアクセス許可を使用できなくなります。

### Microsoft Graph を使用してロール設定を管理する

Microsoft Graph で PIM API を使用して Microsoft Entra ロールの設定を管理するには、 [unifiedRoleManagementPolicy リソースの種類と関連するメソッド](https://learn.microsoft.com/ja-jp/graph/api/resources/unifiedrolemanagementpolicy)を使用します。

Microsoft Graph では、ロール設定はルールと呼ばれます。 これらは、コンテナー ポリシーを通じて Microsoft Entra ロールに割り当てられます。 各 Microsoft Entra ロールには、特定のポリシー オブジェクトが割り当てられます。 Microsoft Entra ロールにスコープ指定されているすべてのポリシーを取得できます。 各ポリシーについて、`$expand` クエリ パラメータを使用して、関連付けられているルールのコレクションを取得できます。 要求の構文は次のとおりです。

```http
GET https://graph.microsoft.com/v1.0/policies/roleManagementPolicies?$filter=scopeId eq '/' and scopeType eq 'DirectoryRole'&$expand=rules
```

Microsoft Graph の PIM API を使用してロール設定を管理する方法の詳細については、「 [ロールの設定と PIM」](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview#role-settings-and-pim)を参照してください。 ルールを更新する方法の例については、「 [Microsoft Graph を使用して PIM のルールを更新](https://learn.microsoft.com/ja-jp/graph/how-to-pim-update-rules)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts"} -->
## PIM の Microsoft Entra ロールのセキュリティ アラート - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management で Microsoft Entra ロールのセキュリティ アラートを構成します。

### 概要

Microsoft Entra ID の組織内で疑わしいアクティビティや危険なアクティビティが行われると、Privileged Identity Management (PIM) によりアラートが生成されます。 アラートは、トリガーされると Privileged Identity Management ダッシュボードに表示されます。 アラートを選択して、アラートをトリガーしたユーザーまたはロールが一覧表示されたレポートを表示します。

注

Privileged Identity Management の 1 つのイベントで、複数の受信者 (担当者、承認者、管理者) 宛の電子メール通知を生成できます。 1 つのイベントごとに送信できる通知の最大数は 1000 です。 受信者の数が 1000 を超える場合は、最初の 1000 人の受信者のみが電子メール通知を受け取ります。 これにより、他の担当者、管理者、または承認者が Microsoft Entra ID と Privileged Identity Management でアクセス許可を使用できなくなります。

[Image: アラートの一覧とその重大度を示す [アラート] ページを示すスクリーンショット。]

### ライセンス要件

Privileged Identity Management を使用するにはライセンスが必要です。 ライセンスの詳細については、「 [Microsoft Entra ID Governance licensing fundamentals 」を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) 参照してください。

### セキュリティのアラート

このセクションでは、Microsoft Entra ロールのすべてのセキュリティ アラートと、それらを修正する方法と、それらを防ぐ方法を示します。 重大度には、次のような意味があります。

- **高**: ポリシー違反が原因で直ちに対処する必要があります。
- **中**: 即時のアクションは必要ありませんが、ポリシー違反の可能性を示します。
- **低**: 即時のアクションは必要ありませんが、望ましいポリシー変更を提案します。

注

Microsoft Entra ロールの PIM セキュリティ アラートを読み取ることができるのは、 **グローバル管理者**、 **特権ロール管理者**、 **グローバル閲覧者**、 **セキュリティ管理者**、 **セキュリティ閲覧者**のみです。

#### 管理者が特権ロールを使用してません

重大度: **低**

| - | 説明 |
| --- | --- |
| **このアラートを受け取る理由** | 必要のない特権ロールを割り当てたユーザーは、攻撃の可能性を高めます。 アクティブに使用されていないアカウントでは、攻撃者が気付かれずに居続けることも簡単です。 |
| **修正方法** | リスト内のユーザーを確認し、必要のない特権ロールから、これらのユーザーを削除します。 |
| **予防** | 業務上の正当な理由があるユーザーにのみ、特権ロールを割り当てます。 定期的な [アクセス レビューを](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review) スケジュールして、ユーザーが引き続きアクセス権を必要としていることを確認します。 |
| **ポータル内の軽減アクション** | その特権ロールからアカウントを削除します。 |
| **トリガー** | ユーザーがロールをアクティブ化しないで一定の日数が経過するとトリガーされます。 |
| **日数** | この設定では、ユーザーがロールをアクティブ化しないままでいられる最大日数を 0 から 100 で指定します。 |

#### ロールのアクティブ化に多要素認証は必要ありません

重大度: **低**

| - | 説明 |
| --- | --- |
| **このアラートを受け取る理由** | 多要素認証を使用しないと、侵害されたユーザーが特権ロールをアクティブ化できます。 |
| **修正方法** | ロールの一覧を確認し、すべてのロールに [多要素認証を要求](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings) します。 |
| **予防** | すべてのロールに [MFA を要求](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)します。 |
| **ポータル内の軽減アクション** | 特権ロールのアクティブ化には、多要素認証が必須になるようにしてください。 |

#### 組織に Microsoft Entra ID P2 または Microsoft Entra ID ガバナンスがない

重大度: **低**

| - | 説明 |
| --- | --- |
| **このアラートを受け取る理由** | 現在の Microsoft Entra 組織には、Microsoft Entra ID P2 または Microsoft Entra ID ガバナンスがありません。 |
| **修正方法** | [Microsoft Entra エディション](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)に関する情報を確認します。 Microsoft Entra ID P2 または Microsoft Entra ID ガバナンスにアップグレードします。 |

#### Potential stale accounts in a privileged role (特権ロール内のアカウントが古い可能性があります)

重大度: **中**

| - | 説明 |
| --- | --- |
| **このアラートを受け取る理由** | このアラートは、アカウントの最後のパスワード変更日に基づいてトリガーされなくなりました。 このアラートは、過去 *n* 日間サインインしていない特権ロールのアカウントに対して行われます。 *n は 1* から 365 までの構成可能な日数です。 このようなアカウントは、保守されておらず、攻撃者に対して脆弱なサービス アカウントまたは共有アカウントの可能性があります。 |
| **修正方法** | リスト内のアカウントを確認します。 もうアクセスが不要になっているアカウントは、特権ロールから削除します。 |
| **予防** | パスワードを知っているユーザーの変更がある場合、共有されているアカウントで強力なパスワードがローテーションされていることを確認します。 アクセス レビューを使用して特権ロールを持つアカウントを定期的に [確認](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review) し、不要になったロールの割り当てを削除します。 |
| **ポータル内の軽減アクション** | その特権ロールからアカウントを削除します。 |
| **ベスト プラクティス** | パスワードを使って認証を行い、全体管理者やセキュリティ管理者などの高い特権を持つ管理者ロールに割り当てられる共有、サービス、および緊急アクセス用のアカウントでは、次の場合にパスワードをローテーションする必要があります。<br>- 管理アクセス権の悪用や侵害が関係するセキュリティ インシデントの後<br>- 管理者ではなくなったユーザーの特権を変更した後 (たとえば、IT 管理者であった従業員が IT 部門から異動したり退職したりした後)<br>- IT スタッフの認識された侵害や変更がない場合でも、一定の間隔で (たとえば、毎四半期または毎年)<br><br>これらのアカウントの資格情報には複数のユーザーがアクセスできるので、資格情報をローテーションして、それらのロールから抜けたユーザーがアカウントにアクセスできないようにする必要があります。 [アカウントのセキュリティ保護の詳細](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning) |

#### ロールが Privileged Identity Management を介さずに割り当てられている

重大度: **高**

| - | 説明 |
| --- | --- |
| **このアラートを受け取る理由** | Privileged Identity Management の外部で行われた特権ロールの割り当ては適切に監視されず、アクティブな攻撃を示している可能性があります。 |
| **修正方法** | リスト内のユーザーを確認し、Privileged Identity Management の外部で割り当てられた特権ロールから、これらのユーザーを削除します。 アラート設定で、アラートとそれに付随する電子メール通知の両方を有効または無効にすることもできます。 |
| **予防** | ユーザーが特権ロールを割り当てられた Privileged Identity Management の外部の場所を調査し、そこからの今後の割り当てを禁止します。 |
| **ポータル内の軽減アクション** | 対象の特権ロールからユーザーを削除します。 |

注

PIMは、アラート設定からアラートが有効にされた場合、**PIMの外部で割り当てられたロール**に関する電子メール通知を送信します。 PIM の Microsoft Entra ロールの場合、Privileged Identity Management を有効に **した特権ロール管理者**、 **セキュリティ管理者**、 **グローバル管理者** に電子メールが送信されます。 PIM の Azure リソースの場合、電子メールは **所有者** と **ユーザー アクセス管理者**に送信されます。

#### グローバル管理者が多すぎます

重大度: **低**

| - | 説明 |
| --- | --- |
| **このアラートを受け取る理由** | グローバル管理者は、最上位の特権ロールです。 全体管理者が侵害された場合、攻撃者はこの管理者のすべてのアクセス許可を使用できるようになり、システム全体が危険にさらされます。 |
| **修正方法** | リストのユーザーを確認し、グローバル管理者ロールがどうしても必要ではないユーザーをすべて削除します。 代わりに、下位の特権ロールをこれらのユーザーに割り当てます。 |
| **予防** | ユーザーには最低限必要な特権ロールを割り当てます。 |
| **ポータル内の軽減アクション** | その特権ロールからアカウントを削除します。 |
| **トリガー** | 2 つの異なる条件が満たされるとトリガーされます。これらの両方の条件を構成できます。 1 つ目として、グローバル管理者ロールの割り当て数が特定のしきい値に達する必要があります。 2 つ目として、総ロール割り当て数のうち一定の割合がグローバル管理者である必要があります。 これらの測定値の一方のみが満たされている場合は、アラートは表示されません。 |
| **グローバル管理者の最小数** | この設定では、全体管理者のロールの割り当て数を、Microsoft Entra 組織には少なすぎると考えられる 2 から 100 の数で指定します。 |
| **全体管理者の割合** | この設定では、グローバル管理者である管理者の最小割合 (0% から 100%) を指定します。この割合を下回ると、Microsoft Entra 組織が急落しないようにします。 |

#### ロールをアクティブ化する頻度が高すぎます

重大度: **低**

| - | 説明 |
| --- | --- |
| **このアラートを受け取る理由** | 同じユーザーが同じ特権ロールに対してアクティブ化を複数行っているのは、攻撃の兆候です。 |
| **修正方法** | 一覧のユーザーを確認し、特権ロールの [アクティブ化期間](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings) が、タスクを実行するのに十分な長さに設定されていることを確認します。 |
| **予防** | ユーザーが自分のタスクを実行するのに十分な時間、特権ロールの[アクティブ化期間](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)が設定されていることを確認します。複数の管理者によって共有されるアカウントを持つ特権ロールに対して[多要素認証が必要](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)です。 |
| **ポータル内の軽減アクション** | 該当なし |
| **トリガー** | ユーザーが指定期間内に同じ特権ロールを複数回アクティブ化するとトリガーされます。 期間とアクティブ化の回数の両方を構成できます。 |
| **アクティブ化の更新期間** | この設定では、疑わしい更新の追跡に使用する期間を日、時間、分、秒で指定します。 |
| **アクティブ化の更新の数** | この設定では、選択した期間内に通知を受け取るアクティブ化の回数を 2 から 100 で指定します。 スライダーを動かすか、テキスト ボックスに数字を入力して設定を変更できます。 |

### セキュリティ アラート設定のカスタマイズ

次の手順に従って、Privileged Identity Management で Microsoft Entra ロールに対するセキュリティ アラートを構成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**特権 ID 管理**&gt;**Microsoft Entra ロール**&gt;**アラート**&gt;**設定** に移動します。 ダッシュボードに Privileged Identity Management タイルを追加する方法については、「 [Privileged Identity Management の使用を開始](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started)する」を参照してください。

    [Image: 設定が強調表示されているアラート ページのスクリーンショット。]
3. さまざまなアラートの設定を環境に合わせてカスタマイズし、自社のセキュリティ目標に合致させます。

    [Image: アラート設定ページのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-how-to-renew-extend"} -->
## PIM で Microsoft Entra ロールの割り当てを更新する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-renew-extend
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra Privileged Identity Management (PIM) 内で、Microsoft Entra ロール割り当てを延長または更新する方法について説明します

### 概要

Microsoft Entra Privileged Identity Management (PIM) には、Microsoft Entra ID へのアクセスと割り当てのライフサイクルを管理するためのコントロールが用意されています。 管理者は、[開始日時] と [終了日時] プロパティを使用してメンバーシップを割り当てることができます。 割り当ての終了日時が近づくと、Privileged Identity Management は、影響を受けるユーザーまたはグループに電子メール通知を送信します。 適切なアクセスが確実に維持されるように、Microsoft Entra 管理者にもメール通知が送信されます。 アクセスが延長されていない場合でも、割り当てが更新され、有効期限が切れた状態で最大 30 日間表示される場合があります。

### 延長と更新を実行できるのは誰か

Microsoft Entra ロールの割り当てを拡張または更新できるのは、全体管理者または特権ロール管理者のみです。 影響を受けるユーザーまたはグループは、まもなく期限切れになるロールの延長と、既に期限切れになっているロールの更新を要求できます。

### 通知の送信時機

Privileged Identity Management は、14 日以内に期限切れになるロールの管理者と影響を受けるユーザーまたはグループに電子メール通知を送信します。電子メール通知は、有効期限の前日にも送信されます。 電子メールは、割り当てが正式に期限切れになったときにも送信されます。

管理者は、ユーザーまたはグループが期限が迫っているか期限切れになっているロールが期限の延長または更新を要求したときに通知を受信します。 管理者が承認または拒否として要求を解決すると、他のすべての管理者に決定が通知されます。 その後、要求したユーザーまたはグループに決定が通知されます。

### ロールの割り当てを延長する

ロール割り当ての延長または更新を要求、解決、または管理する手順を次に示します。

#### 有効期限が切れた割り当ての自動延長

ロールに割り当てられたユーザーは、**[自分のロール]** ページ (**[Microsoft Entra ロール]** か、Privileged Identity Management ポータルの最上位レベルの **[自分のロール]** ページのいずれか) の **[資格あり]** または **[アクティブ]** タブから、有効期限が切れるロールの割り当てを直接延長できます。 このポータルでは、ユーザーは 14 日後に期限切れになる有資格ロールと (割り当て済みの) アクティブ ロールの延長を要求できます。

[Image: [Microsoft Entra ロール] - [自分のロール] ページで、利用可能なロールと [アクション] 列が一覧表示されます。]

割り当ての終了日時が 14 日以内にある場合は、ユーザー インターフェイスの**延長**ボタンがアクティブ リンクになります。 次の例では、現在の日付が 3 月 27 日であると想定しています。

注意

ロールに割り当てられたグループの場合は、割り当てを継承している 1 人のユーザーがグループの割り当てを延長できないように、 **[延長]** リンクが利用できるようになることはありません。

[Image: アクティブ化または延長するためのリンクがあるアクション列を示すスクリーンショット。]

このロールの割り当ての延長を要求するには、**延長**をクリックして要求フォームを開きます。

[Image: [理由] ボックスがあるロール割り当て延長ペインを示すスクリーンショット。]

延長要求の理由を入力し、**延長**をクリックします。

注意

拡張機能が必要な理由と、拡張機能を付与する必要がある期間 (この情報がある場合) の詳細を含めます。

管理者は、延長要求を確認する電子メール通知を受け取ります。 延長の要求が既に送信されている場合は、Azure の通知がポータルに表示されます。

[Image: 保留になっている既存のロール割り当ての延長が既にあることを示す通知が表示されているスクリーンショット。]

**[保留中の要求]** ページに移動して、要求の状態を確認します。要求をキャンセルすることもできます。

[Image: [Microsoft Entra ロール] - [保留中の要求] ページのスクリーンショットには、保留中の要求の一覧とキャンセルのリンクが表示されています。]

#### 管理者によって承認された延長

ユーザーまたはグループがグループの割り当ての延長を求める要求を送信すると、管理者は、元の割り当ての詳細と要求の理由を含む電子メール通知を受信します。 通知には、管理者が要求を承認または拒否するための直接リンクが含まれています。

管理者は、電子メールからのリンクに従うだけでなく、Privileged Identity Management 管理ポータルに移動し、左側のウィンドウで [要求の承認] を選択することで **、要求を承認** または拒否できます。

[Image: 要求と承認または拒否するためのリンクが一覧表示されている [Microsoft Entra ロール] - [要求の承認] ページを示すスクリーンショット。]

管理者が **[承認** ] または **[拒否**] を選択すると、要求の詳細と、監査ログの業務上の正当な理由を提供するフィールドが表示されます。

[Image: 要求者の理由、割り当ての種類、開始時刻、終了時刻、理由を含む承認済みロールの割り当て要求を示すスクリーンショット。]

要求を承認してロールの割り当てを延長するとき、管理者は、新しい開始日時、終了日時、および割り当ての種類を選択できます。 管理者が特定のタスク を完了するためのアクセス制限 を指定する (たとえば 1 日だけ延長する) 場合は、割り当ての種類の変更が必要になることがあります。 この例では、管理者は、割り当ての種類を**有資格**から**アクティブ**に変更できます。 つまり、アクティブ化の必要がないアクセスを要求者に提供できます。

#### 管理者による延長

ロールに割り当てられたユーザーがロールの割り当ての延長を要求しない場合、管理者がユーザーの代わりに割り当てを延長できます。 ロールの割り当ての管理拡張機能では承認は必要ありませんが、通知はロールの延長後に他のすべての管理者に送信されます。

ロールの割り当てを延長するには、Privileged Identity Management のロールまたは割り当てビューを参照します。 延長する必要がある割り当てを探します。 そして、[アクション] 列の**延長**をクリックします。

[Image: [Microsoft Entra ロール] の [割り当て] ページでは、資格のあるロールとその延長リンクが一覧表示されています。これを示すスクリーンショットです。]

### Microsoft Graph API を使用してロールの割り当てを拡張する

次の要求では、管理者が Microsoft Graph API を使ってアクティブな割り当てを拡張します。

#### HTTP 要求

```HTTP
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignmentScheduleRequests 
 
{
    "action": "adminExtend",
    "justification": "TEST",
    "roleDefinitionId": "31392ffb-586c-42d1-9346-e59415a2cc4e",
    "directoryScopeId": "/",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "scheduleInfo": {
        "startDateTime": "2022-04-10T00:00:00Z",
        "expiration": {
            "type": "afterDuration",
            "duration": "PT3H"
        }
    }
}
```

#### HTTP 応答

```HTTP
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#roleManagement/directory/roleAssignmentScheduleRequests/$entity",
    "id": "c3a3aa36-22e2-4240-8e4c-ea2a3af7c30f",
    "status": "Provisioned",
    "createdDateTime": "2022-05-13T16:18:36.3647674Z",
    "completedDateTime": "2022-05-13T16:18:40.0835993Z",
    "approvalId": null,
    "customData": null,
    "action": "adminExtend",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "roleDefinitionId": "31392ffb-586c-42d1-9346-e59415a2cc4e",
    "directoryScopeId": "/",
    "appScopeId": null,
    "isValidationOnly": false,
    "targetScheduleId": "c3a3aa36-22e2-4240-8e4c-ea2a3af7c30f",
    "justification": "TEST",
    "createdBy": {
        "application": null,
        "device": null,
        "user": {
            "displayName": null,
            "id": "aaaaaaaa-bbbb-cccc-1111-222222222222"
        }
    },
    "scheduleInfo": {
        "startDateTime": "2022-05-13T16:18:40.0835993Z",
        "recurrence": null,
        "expiration": {
            "type": "afterDuration",
            "endDateTime": null,
            "duration": "PT3H"
        }
    },
    "ticketInfo": {
        "ticketNumber": null,
        "ticketSystem": null
    }
}
```

### ロールの割り当ての更新

概念上は延長の要求プロセスと似ていますが、有効期限が切れたロール割り当てを更新するプロセスは異なります。 割り当てと管理者は、次の手順を使用して、必要な場合に有効期限が切れたロールへのアクセスを更新できます。

#### 自己更新

リソースにアクセスできなくなったユーザーは、最大 30 日間分の有効期限が切れた割り当て履歴にアクセスできます。 これを行うには、左側のウィンドウで **[自分のロール** ] を参照し、[Microsoft Entra roles] セクションの [ **期限切れのロール** ] タブを選択します。

[Image: [自分のロール] ページ - [期限切れのロール] タブを示すスクリーンショット。]

ロールの一覧には、**有資格のロール**が既定で表示されます。 **[有資格]** または **[アクティブ]** の割り当て済みロールを選択します。

一覧のいずれかのロールの割り当ての更新を要求するには、**更新**アクションを選択します。 そして、要求の理由を入力します。 管理者が承認するか拒否するかを決定するのに役立つ、他のコンテキストや業務上の正当な理由に加えて、期間を提供すると便利です。

[Image: [理由] ボックスが表示されている [ロールの割り当ての更新] ペインを示すスクリーンショット。]

要求が送信された後、管理者はロール割り当ての更新を申請している保留中のリクエストがあることを通知されます。

#### 管理者による承認

Microsoft Entra 管理者は、メール通知内のリンクから更新要求にアクセスするか、Microsoft Entra 管理センターから Privileged Identity Management にアクセスし、PIM の **[申請の承認]** を選んで更新要求にアクセスできます。

[Image: 要求と承認または拒否するためのリンクが一覧表示されている [Microsoft Entra ロール] - [要求の承認] ページを示すスクリーンショット。]

管理者が **[承認]** または **[拒否]** を選択すると、要求の詳細が、監査ログ用の業務上の正当な理由を入力するフィールドと共に表示されます。

[Image: ロールの割り当て要求の承認ページを示すスクリーンショット。]

要求を承認してロールの割り当てを更新するとき、管理者は、新しい開始日時、終了日時、および割り当ての種類を入力する必要があります。

#### 管理者による更新

管理者は、Microsoft Entra ロールの [期限切れのロール] タブから **、期限切れの** ロールの割り当てを更新することもできます。 有効期限が切れたすべてのロールの割り当ての一覧を表示するには、 **[割り当て]** 画面で **[期限切れのロール]** を選択します。

[Image: 有効期限が切れたロールと更新のためのリンクが一覧表示されている [Microsoft Entra ロール] - [割り当て] ページのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-how-to-use-audit-log"} -->
## Microsoft Entra PIM で Microsoft Entra ロールの監査ログ レポートを表示する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-use-audit-log
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-08-03
- Summary: Microsoft Entra Privileged Identity Management (PIM) で Microsoft Entra ロールの監査ログ履歴を表示する方法について説明します。

### 概要

Microsoft Entra Privileged Identity Management (PIM) リソース監査ログを使用して、ロールの割り当ての変更、ロールのアクティブ化、PIM ポリシーの変更を確認できます。 データは過去 30 日間利用できます。

PIM リソース監査ログは、Microsoft Entra監査ログのサブセットです。 [Microsoft Entraセキュリティレポートとアクティビティレポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)を使用して、管理者、エンド ユーザー、同期アクティビティを含むMicrosoft Entra IDアクティビティの完全な監査履歴を表示します。

監査データを既定の保持期間よりも長く保持する場合は、Azure Monitorの診断設定を使用して、Azureストレージ アカウントまたはLog Analyticsにルーティングできます。 詳細については、「[Microsoft Entra logs with Azure Monitor logsの統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)」をご覧ください。

Microsoft Entra ロールの監査履歴を表示するには、次の手順に従います。

### リソース監査履歴の表示

**[リソース監査**] ブレードを使用して、PIM での Microsoft Entra ロールの割り当てと PIM ポリシー管理に関連付けられているすべてのアクティビティを表示します。

1. グローバル管理者、グローバル閲覧者、特権ロール管理者、セキュリティ管理者、またはセキュリティ閲覧者として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[ID ガバナンス]**&gt;、**[Privileged Identity Management]**&gt;、**[Microsoft Entra ロール]** を参照します。
3. **[リソース監査] を選択します**。
4. 定義済みの日付またはカスタム範囲を使用して履歴をフィルター処理します。

    [Image: フィルターを含む Microsoft Entra ロール監査リストを示すスクリーンショット。]

### 自分の監査を表示する

**[マイ監査]** ブレードを使用して、Microsoft Entra のロール割り当てと PIM ポリシー管理におけるロール アクティビティを表示します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[ID ガバナンス]**&gt;、**[Privileged Identity Management]**&gt;、**[Microsoft Entra ロール]** を参照します。
3. **自分の監査**を選択します。
4. 定義済みの日付またはカスタム範囲を使用して履歴をフィルター処理します。

    [Image: 現在のユーザーの [監査] リスト ページを示すスクリーンショット。]

### 同じアクティブ化サイクルに関連するイベントの関連付け

`CorrelationId` は、通常、1 つの要求に関連する監査ログ イベントを関連付けるために使用されます。 PIM を使用すると、複数の非同期処理操作を 1 つのアクティブ化/非アクティブ化サイクルに含めることができます。 その結果、同じアクティブ化/非アクティブ化サイクルに関連するいくつかのイベントの `CorrelationId`が異なります。

ロールのアクティブ化中に、次の操作が非同期的に処理され、結果として複数の `CorrelationId`が生成される場合があります。

- PIM で**スケジュールされたアクティブ化**を使用すると、対象となるユーザーは、指定した将来に開始するロールのアクティブ化を要求できます。 スケジュールが設定されると、システムはアクティブ化要求を追跡し、指定された開始時刻にロールの割り当てを自動的に作成します。追加のユーザー入力は必要ありません。 この操作は非同期であるため、実際のアクティブ化時に新しい `CorrelationId` が生成されます。これは、元の要求の `CorrelationId`とは異なる場合があります。 これにより、 `CorrelationId` を使用した直接的な相関関係は、要求とアクティブ化の各フェーズで困難になります。
- **承認ゲートによるアクティブ化**: PIM ポリシーでロールのアクティブ化の承認が必要な場合、アクティブ化要求は 2 段階のプロセスに従います。要求は適格なユーザーによって作成され、承認は指定された承認者によって提供されます。 承認されると、システムはロールの割り当てを続行します。これは、ユーザーがスケジュールされた開始を選択した場合、すぐにまたは後で行われる可能性があります。 このフローの非同期性により、 `CorrelationId` はステージによって異なる場合があります。
- まれに、システム間での要求の処理方法により、ロールのアクティブ化フロー中に `CorrelationId` が変更されることがあります。

`roleAssignmentRequestId`を使用して、上記のすべての例の 1 つのアクティブ化要求に関連するイベントを関連付けます。 `roleAssignmentRequestId` は、スケジュールされたアクティブ化や承認などの操作の非同期処理中も変わりません。

次の例Log Analyticsクエリを使用して、ロールのアクティブ化に関連する監査ログ エントリを取得します。

```Kusto
AuditLogs
| where OperationName has "Add member to role"
```

このクエリの出力を使用して、分析する必要があるイベントの `roleAssignmentRequestId` を取得します。

次の例Log Analyticsクエリを使用して、同じロールのアクティブ化に関連する監査ログ エントリを取得します。

```Kusto
let roleAssignmentRequestId = "{roleAssignmentRequestId}";
AuditLogs
| where AdditionalDetails has roleAssignmentRequestId
```

`CorrelationId` 非アクティブ化プロセス中にログに記録される内容は、非アクティブ化がトリガーされた方法によって異なります。

- アクティブ化されたロールの割り当ての有効期限に基づいて非アクティブ化が自動的にトリガーされると、非アクティブ化イベントの `CorrelationId` は、アクティブ化中に使用された最新の `CorrelationId` と一致します。
- 非アクティブ化が担当者によってトリガーされた場合 (ポータルでユーザーが **非アクティブ化** を選択した場合)、 `CorrelationId` はアクティブ化フローで使用されたものとは異なります。

どちらの場合も、元のアクティブ化要求の `roleAssignmentRequestId` は、非アクティブ化の監査ログ イベントの **詳細** の下に記録されます。

次の例Log Analyticsクエリを使用して、完全なアクティブ化/非アクティブ化サイクルに関連する監査ログ エントリを取得します。

```Kusto
let roleAssignmentRequestId = "{roleAssignmentRequestId}";
let relatedCorrelationIds = AuditLogs
   | where AdditionalDetails has roleAssignmentRequestId
   | summarize makeset(CorrelationId);
AuditLogs
| where AdditionalDetails has roleAssignmentRequestId
  or CorrelationId in (relatedCorrelationIds)
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-perform-roles-and-resource-roles-review"} -->
## PIM で Azure リソース ロールと Microsoft Entra ロールのアクセス レビューを作成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-perform-roles-and-resource-roles-review
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Azure リソースのアクセスおよび Microsoft Entra ロールをレビューする方法を説明します。

### 概要

Privileged Identity Management (PIM) を使用すると、企業が Microsoft Entra ID や他の Microsoft Online Services (Microsoft 365 や Microsoft Intune など) 内のリソースへの特権アクセスを管理する方法が簡略化されます。 この記事の手順に従って、ロールへのアクセスのレビューを実行します。

自分が管理者ロールに割り当てられている場合、組織の特権ロール管理者から、自分の仕事のためにそのロールがまだ必要であるかどうかを定期的に確認するよう求められることがあります。 リンクを含むメールを受け取る場合もあれば、直接 [Microsoft Entra 管理センター](https://entra.microsoft.com)にアクセスして開始することもできます。

特権ロール管理者以上としてアクセス レビューに関心がある場合は、詳細を「[アクセス レビューを開始する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)」で確認できます。

### アクセスの承認または拒否

ユーザーがまだロールへのアクセスを必要とするかどうかに基づいて、アクセスを承認または拒否することができます。 ユーザーのロールをそのままにしたい場合は **[承認]** を選び、ユーザーにアクセスが必要なくなった場合は **[拒否]** を選びます。 レビューが終了し、管理者が結果を適用するまで、ユーザーの割り当ての状態は変更されません。 拒否された特定のユーザーが結果を適用できない一般的なシナリオには、次のようなものがあります。

- **同期されたオンプレミスの Windows AD グループのメンバーのレビュー**: グループがオンプレミスの Windows AD から同期されている場合、グループを Microsoft Entra ID で管理することはできません。そのため、メンバーシップを変更することはできません。
- 割り当てられた入れ子になったグループを持つロールのレビュー: 入れ子になったグループを通じてメンバーシップを持つユーザーの場合、アクセス レビューは入れ子になったグループへのメンバーシップを削除しないため、レビュー対象のロールへのアクセスを保持します。
- **ユーザーが見つからない、またはその他のエラー**: これらは、適用結果がサポートされない可能性もあります。

アクセス レビューを検索して完了するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**特権アイデンティティ管理**&gt;**アクセスを確認します**。
3. 保留中のアクセス レビューがある場合は、アクセス レビュー ページに表示されます。

    [Image: Privileged Identity Management アプリケーションのスクリーンショット。Microsoft Entra ロールの[アクセスのレビュー]ペインが選択されています。]
4. 完了するレビューを選択します。
5. **[承認]** または **[拒否]** を選択します。 **[理由の指定] ボックス**に、必要であれば、業務上の正当な理由を入力します。

    [Image: Microsoft Entra ロールのアクセス レビューが選択されている Privileged Identity Management アプリケーションのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-powershell-migration"} -->
## Azure リソースの PIM PowerShell 移行ガイダンス - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-powershell-migration
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: 次のドキュメントでは、Privileged Identity Management (PIM) PowerShell の移行に関するガイダンスを提供します。

### 概要

次の表では、新しい Azure PowerShell モジュールで新しい PowerShell コマンドレットを使用する方法に関するガイダンスを示します。

### Azure PowerShell モジュールの新しいコマンドレット

| 古い AzureADPreview cmd | 新しい Az cmd と同等 | 説明 |
| --- | --- | --- |
| Get-AzureADMSPrivilegedResource | [Get-AzResource](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/get-azresource) | リソースを取得する |
| Get-AzureADMSPrivilegedRoleDefinition | [Get-AzRoleDefinition](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/get-azroledefinition) | ロール定義を取得する |
| Get-AzureADMSPrivilegedRoleSetting | [Get-AzRoleManagementPolicy](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/get-azrolemanagementpolicy) | リソース スコープの指定されたロール管理ポリシーを取得する |
| Set-AzureADMSPrivilegedRoleSetting | [Update-AzRoleManagementPolicy](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/update-azrolemanagementpolicy) | ロール管理ポリシーに対して定義されたルールを更新する |
| Open-AzureADMSPrivilegedRoleAssignmentRequest | [New-AzRoleAssignmentScheduleRequest](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azroleassignmentschedulerequest) | 割り当て要求に使用されますロールの割り当てスケジュール要求を作成する |
| Open-AzureADMSPrivilegedRoleAssignmentRequest | [New-AzRoleEligibilityScheduleRequest](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azroleeligibilityschedulerequest) | 適格性要求に使用されますロール資格スケジュールのリクエストを作成する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-resource-roles-activate-your-roles"} -->
## PIM で Azure リソース ロールをアクティブ化する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-activate-your-roles
- Service: entra-id-governance / privileged-identity-management
- Article date: 2024-12-19
- Summary: Microsoft Entra Privileged Identity Management (PIM) で Azure リソース ロールをアクティブ化する方法について説明します。

Microsoft Entra Privileged Identity Management (PIM) を使用して、Azure リソースの有資格ロール メンバーが将来の日付と時刻のアクティブ化をスケジュールできるようにします。 また、最大 (管理者によって構成) 内の特定のアクティブ化期間を選択することもできます。

この記事は、Privileged Identity Management で Azure リソース ロールをアクティブ化する必要があるメンバーを対象にしています。

手記

2023 年 3 月の時点で、Azure portal で、割り当てをアクティブ化し、PIM の外部にあるブレードから直接アクセスを表示できるようになりました。 詳細については、を参照してください。

大事な

ロールがアクティブになると、Microsoft Entra PIM によってロールのアクティブな割り当てが一時的に追加されます。 Microsoft Entra PIM は、数秒以内にアクティブな割り当てを作成します (ユーザーをロールに割り当てます)。 非アクティブ化 (手動またはアクティブ化時間の有効期限) が発生すると、Microsoft Entra PIM は、アクティブな割り当てを数秒で削除します。

アプリケーションは、ユーザーが持っているロールに基づいてアクセスを提供できます。 場合によっては、ユーザーがロールを割り当てたり削除したりしたという事実がアプリケーション アクセスにすぐに反映されない場合があります。 ユーザーがロールを持っていないという事実をアプリケーションが以前にキャッシュした場合、ユーザーがアプリケーションに再度アクセスしようとすると、アクセスが提供されない可能性があります。 同様に、ユーザーがロールを持っているという事実をアプリケーションが以前にキャッシュした場合、ロールが非アクティブ化されても、ユーザーは引き続きアクセスできます。 特定の状況は、アプリケーションのアーキテクチャによって異なります。 一部のアプリケーションでは、サインアウトしてサインインし直すのが、アクセスの追加または削除に役立つ場合があります。

### 前提条件

なし

### ロールのアクティブ化

Azure リソース ロールを引き受ける必要がある場合は、Privileged Identity Management の **[自分のロール** ] ナビゲーション オプションを使用してアクティブ化を要求できます。

手記

AZURE モバイル アプリ (iOS |Microsoft Entra ID と Azure リソース ロール用の Android)。 適格な割り当てを簡単にアクティブ化したり、有効期限が切れている割り当ての更新を要求したり、保留中の要求の状態を確認したりできます。 詳細については、以下を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**My ロール**を参照します。

    [Image: アクティブ化できるロールを示す [自分のロール] ページのスクリーンショット。]
3. Azure リソース ロール  を選択して、対象となる Azure リソース ロールの一覧を表示します。

    [Image: [自分のロール] の [Azure リソース ロール] ページのスクリーンショット。]
4. **Azure リソース ロール**の一覧で、アクティブ化するロールを見つけます。

    [Image: Azure リソース ロール - 対象ロールの一覧のスクリーンショット。]
5. [アクティブ化]選択して、[アクティブ化] ページを開きます。

    [Image: スコープ、開始時刻、期間、理由を含む、開いている [アクティブ化] ウィンドウのスクリーンショット。]
6. ロールに多要素認証が必要な場合は、に進む前に [本人確認] を選択します。 認証する必要があるのは、セッションごとに 1 回だけです。
7. [ **ID の確認** ] を選択し、指示に従って追加のセキュリティ検証を行います。

    [Image: PIN コードなどのセキュリティ検証を提供する画面のスクリーンショット。]
8. スコープを制限する場合は、[スコープ]選択して、[リソース フィルター] ウィンドウを開きます。

    必要なリソースへのアクセスのみを要求することをお勧めします。 [リソース フィルター] ウィンドウで、アクセスする必要があるリソース グループまたはリソースを指定できます。

    [Image: スコープを指定する [アクティブ化 - リソース フィルター] ウィンドウのスクリーンショット。]
9. 必要に応じて、カスタムアクティブ化の開始時刻を指定します。 メンバーは、選択した時刻より後にアクティブになります。
10. [ **理由** ] ボックスに、アクティブ化要求の理由を入力します。
11. [アクティブ化] を選択します。

    手記

    アクティブ化するために [ロールに承認が必要](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow) な場合は、要求が承認待ちであることを通知する通知がブラウザーの右上隅に表示されます。

### Azure Resource Manager API を使用してロールをアクティブ化する

Privileged Identity Management では、 [PIM ARM API リファレンス](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-eligibility-schedule-requests)に記載されているように、Azure リソース ロールを管理するための Azure Resource Manager API コマンドがサポートされています。 PIM API を使用するために必要なアクセス許可については、「 [Privileged Identity Management API について」を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-apis)参照してください。

対象となる Azure ロールの割り当てをアクティブ化し、アクティブ化されたアクセス権を取得するには、 [ロール割り当てスケジュール要求の作成 - REST API を](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-assignment-schedule-requests/create?tabs=HTTP) 使用して新しい要求を作成し、セキュリティ プリンシパル、ロール定義、requestType = SelfActivate、スコープを指定します。 この API を呼び出すには、スコープに対する資格のあるロールの割り当てが必要です。

GUID ツールを使用して、ロールの割り当て識別子の一意の識別子を生成します。 識別子の形式は 000000000-0000-0000-0000-0000000000000 です。

PUT 要求の {roleAssignmentScheduleRequestName} をロール割り当ての GUID 識別子に置き換えます。

Azure リソース管理の対象となるロールの詳細については、PIM ARM API チュートリアル参照してください。

これは、Azure ロールの適格な割り当てをアクティブ化するためのサンプル HTTP 要求です。

#### 依頼

```HTTP
PUT https://management.azure.com/providers/Microsoft.Subscription/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/roleAssignmentScheduleRequests/{roleAssignmentScheduleRequestName}?api-version=2020-10-01
```

#### 要求本文

```JSON
{ 
"properties": { 
  "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222", 
  "roleDefinitionId": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/roleDefinitions/c8d4ff99-41c3-41a8-9f60-21dfdad59608", 
  "requestType": "SelfActivate", 
  "linkedRoleEligibilityScheduleId": "b1477448-2cc6-4ceb-93b4-54a202a89413", 
  "scheduleInfo": { 
      "startDateTime": "2020-09-09T21:35:27.91Z", 
      "expiration": { 
          "type": "AfterDuration", 
          "endDateTime": null, 
          "duration": "PT8H" 
      } 
  }, 
  "condition": "@Resource[Microsoft.Storage/storageAccounts/blobServices/containers:ContainerName] StringEqualsIgnoreCase 'foo_storage_container'", 
  "conditionVersion": "1.0" 
} 
} 
```

#### 応答

状態コード: 201

```HTTP
{ 
  "properties": { 
    "targetRoleAssignmentScheduleId": "c9e264ff-3133-4776-a81a-ebc7c33c8ec6", 
    "targetRoleAssignmentScheduleInstanceId": null, 
    "scope": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e", 
    "roleDefinitionId": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/roleDefinitions/c8d4ff99-41c3-41a8-9f60-21dfdad59608", 
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222", 
    "principalType": "User", 
    "requestType": "SelfActivate", 
    "status": "Provisioned", 
    "approvalId": null, 
    "scheduleInfo": { 
      "startDateTime": "2020-09-09T21:35:27.91Z", 
      "expiration": { 
        "type": "AfterDuration", 
        "endDateTime": null, 
        "duration": "PT8H" 
      } 
    }, 
    "ticketInfo": { 
      "ticketNumber": null, 
      "ticketSystem": null 
    }, 
    "justification": null, 
    "requestorId": "a3bb8764-cb92-4276-9d2a-ca1e895e55ea", 
    "createdOn": "2020-09-09T21:35:27.91Z", 
    "condition": "@Resource[Microsoft.Storage/storageAccounts/blobServices/containers:ContainerName] StringEqualsIgnoreCase 'foo_storage_container'", 
    "conditionVersion": "1.0", 
    "expandedProperties": { 
      "scope": { 
        "id": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e", 
        "displayName": "Pay-As-You-Go", 
        "type": "subscription" 
      }, 
      "roleDefinition": { 
        "id": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/roleDefinitions/c8d4ff99-41c3-41a8-9f60-21dfdad59608", 
        "displayName": "Contributor", 
        "type": "BuiltInRole" 
      }, 
      "principal": { 
        "id": "a3bb8764-cb92-4276-9d2a-ca1e895e55ea", 
        "displayName": "User Account", 
        "email": "user@my-tenant.com", 
        "type": "User" 
      } 
    } 
  }, 
  "name": "fea7a502-9a96-4806-a26f-eee560e52045", 
  "id": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/RoleAssignmentScheduleRequests/fea7a502-9a96-4806-a26f-eee560e52045", 
  "type": "Microsoft.Authorization/RoleAssignmentScheduleRequests" 
} 
```

### 要求の状態を表示する

アクティブ化する保留中の要求の状態を表示できます。

1. Microsoft Entra Privileged Identity Management を開きます。
2. [ **要求]** を選択すると、Microsoft Entra ロールと Azure リソース ロールの要求の一覧が表示されます。

    [Image: 保留中の要求を示す [自分の要求 - Azure リソース] ページのスクリーンショット。]
3. 右にスクロールして [ **要求の状態]** 列を表示します。

### 保留中の要求を取り消す

承認が必要なロールのアクティブ化を必要としない場合は、いつでも保留中の要求を取り消すことができます。

1. Microsoft Entra Privileged Identity Management を開きます。
2. [個人用要求 を選択します。
3. 取り消すロールについては、[ **キャンセル** ] リンクを選択します。

    ```
    When you select Cancel, the request will be canceled. To activate the role again, you will have to submit a new request for activation.
    ```

    [Image: [キャンセル] アクションが強調表示されている [要求] リストのスクリーンショット。]

### ロールの割り当てを非アクティブ化する

ロールの割り当てがアクティブになると、PIM ポータルにロールの割り当ての **非アクティブ化** オプションが表示されます。 また、アクティブ化後 5 分以内にロールの割り当てを非アクティブ化することはできません。

### Azure portal でアクティブ化する

Privileged Identity Management ロールのアクティブ化は、Azure portal 内の課金およびアクセス制御 (AD) 拡張機能に統合されます。 サブスクリプション (課金) とアクセス制御 (AD) へのショートカットを使用すると、これらのブレードから直接 PIM ロールをアクティブ化できます。

[サブスクリプション] ブレードで、水平方向のコマンド メニューで [対象となるサブスクリプションの表示] を選択して、対象の割り当て、アクティブな割り当て、期限切れの割り当てを確認します。 そこから、同じウィンドウで適格な割り当てをアクティブ化できます。

[Image: [サブスクリプション] ページの対象となるサブスクリプションの表示のスクリーンショット。]

[Image: [Cost Management: Integration Service] ページの対象となるサブスクリプションの表示のスクリーンショット。]

リソースのアクセス制御 (IAM) で、[アクセスの表示] を選択して、現在アクティブなロールと対象となるロールの割り当てを表示し、直接アクティブ化できるようになりました。

[Image: [測定] ページの現在のロールの割り当てのスクリーンショット。]

PIM 機能をさまざまな Azure portal ブレードに統合することで、この新機能を使用すると、サブスクリプションとリソースをより簡単に表示または編集するための一時的なアクセスを取得できます。

### Azure モバイル アプリを使用して PIM ロールをアクティブ化する

PIM は、iOS と Android の両方で、Microsoft Entra ID と Azure リソース ロールモバイル アプリで使用できるようになりました。

1. 対象となる Microsoft Entra ロールの割り当てをアクティブにするには、まず Azure モバイル アプリ ([iOS](https://apps.apple.com/us/app/microsoft-azure/id1219013620) | [Android](https://play.google.com/store/apps/details?id=com.microsoft.azure)) をダウンロードします。 Privileged Identity Management  My roles &gt; で開く] を選択して、アプリをダウンロードすることもできます。

    [Image: モバイル アプリをダウンロードする方法を示すスクリーンショット。]
2. Azure モバイル アプリを開き、サインインします。 [Privileged Identity Management] カードをクリックし、[マイ Azure リソース ロール  を選択して、対象となるロールとアクティブなロールの割り当てを表示します。

    [Image: 特権 ID 管理とユーザーのロールを示すモバイル アプリのスクリーンショット。]
3. ロールの割り当てを選択し、ロールの割り当ての詳細で [ **アクション] &gt; [アクティブ化** ] をクリックします。 下部にある [アクティブ化] をクリックする前に、アクティブにして必要な詳細を入力 手順を完了します。

    [Image: 検証プロセスが完了したことを示すモバイル アプリのスクリーンショット。画像には[アクティブ化]ボタンが表示されます。]
4. アクティブ化要求の状態とロールの割り当てを [マイ Azure リソース ロール] で表示します。

    [Image: アクティブ化が進行中のメッセージを示すモバイル アプリのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow"} -->
## PIM で Azure リソース ロールの要求を承認する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-approval-workflow
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Azure リソース ロールに対する要求を承認または拒否する方法について説明します。

### 概要

Microsoft Entra Privileged Identity Management (PIM) を使用すると、アクティブ化の承認が必要となるようにロールを構成し、委任された承認者として Microsoft Entra 組織からユーザーまたはグループを選択することができます。 特権ロール管理者のワークロードを削減するには、ロールごとに 2 人以上の承認者を選択します。 代理承認者は、要求を承認するまでに 24 時間あります。 要求が 24 時間以内に承認されない場合、対象ユーザーは新しい要求を再送信する必要があります。 24 時間の承認時間枠は構成できません。

Azure リソース ロールの要求を承認または拒否するには、この記事の手順に従ってください。

### 保留中の要求を表示する

Azure リソース ロール要求が、代理承認者であるあなたの承認を待っている状態になると、あなたに電子メール通知が届きます。 これらの保留中の要求は、Privileged Identity Management で表示できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**特権 ID 管理**&gt;**要求を承認する**。

    **[ロールのアクティブ化に関する要求]** セクションに、承認が保留中の要求の一覧が表示されます。

### 要求の承認

1. 承認する要求を見つけて選択します。 承認または拒否のページが表示されます。
2. **[理由]** ボックスに、業務上の正当な理由を入力します。
3. **[承認]** を選択します。 承認の Azure 通知を受け取ります。

### Microsoft Azure Resource Manager API を使用して保留中の要求を承認する

注

**延長要求と更新**要求の承認は、現在、Microsoft ARM API ではサポートされていません。

#### 承認が必要なステップの ID を取得する

ロールの割り当てを承認するすべてのステージの詳細を取得するには、[Role Assignment Approval Step - Get By ID](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-assignment-approval-step/get-by-id?tabs=HTTP) REST API を使用できます。

##### HTTP 要求

```HTTP
GET https://management.azure.com/providers/Microsoft.Authorization/roleAssignmentApprovals/{approvalId}/stages/{stageId}?api-version=2021-01-01-preview
```

#### アクティブ化要求の手順を承認する

アクティブ化要求ステップを承認するには、次の API 呼び出しを行います。

##### HTTP 要求

```HTTP
PATCH https://management.azure.com/providers/Microsoft.Authorization/roleAssignmentApprovals/{approvalId}/stages/{stageId}?api-version=2021-01-01-preview
{ 
    "reviewResult": "Approve", // or "Deny"
    "justification": "Trusted User" 
} 
```

##### HTTP 応答

PATCH の呼び出しに成功すると、空の応答が生成されます。

詳細については、「 [ロールの割り当ての承認を使用して REST API を使用して PIM ロールのアクティブ化要求を承認する](https://learn.microsoft.com/ja-jp/rest/api/authorization/privileged-approval-sample)」を参照してください。

### 要求を拒否する

1. 拒否する要求を見つけて選択します。 承認または拒否のページが表示されます。
2. **[理由]** ボックスに、業務上の正当な理由を入力します。
3. **[拒否]** を選択します。 拒否すると同時に通知が表示されます。

### ワークフロー通知

ワークフロー通知に関するいくつかの情報を次に示します。

- 役割に関する要求のレビューが保留中の場合、承認者にはメールで通知が送られます。 電子メール通知には、承認者が承認または拒否できる、要求への直接リンクが含まれています。
- 要求は、承認または拒否した最初の承認者によって解決されます。
- 承認者が要求に応答すると、すべての承認者にその動作が通知されます。
- リソース管理者には、承認されたユーザーがそのロール内でアクティブになると通知されます。

注

承認されたユーザーをアクティブにしてはならないと考えるリソース管理者は、Privileged Identity Management でアクティブなロールの割り当てを削除できます。 リソース管理者は、承認者でない限り保留中の要求の通知を受け取りませんが、Privileged Identity Management で保留中の要求を表示することで、すべてのユーザーの保留中の要求を表示および取り消すことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles"} -->
## Privileged Identity Management で Azure リソース ロールを割り当てる - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-08-06
- Summary: Privileged Identity Management (PIM) で Azure リソース ロールを割り当てる方法について説明します。

### 概要

Microsoft Entra Privileged Identity Management (PIM) を使用すると、組み込みの Azure リソース ロールと次のような (ただし、これらに限定されない) カスタム ロール を管理できます:

- オーナー
- ユーザーアクセス管理者
- 投稿者
- セキュリティ管理者
- セキュリティマネージャー

注

所有者またはユーザー アクセス管理者のサブスクリプション ロールに割り当てられているグループのユーザーまたはメンバー、および Microsoft Entra ID でサブスクリプション管理を有効にする Microsoft Entra グローバル管理者には、既定でリソース管理者のアクセス許可があります。 これらの管理者は、ロールの割り当て、ロール設定の構成、Azure リソース用 Privileged Identity Management を使用したアクセスの確認ができます。 リソース管理者のアクセス許可がないユーザーは、リソース用 Privileged Identity Management を管理できません。 [Azure 組み込みロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)の一覧を表示します。

Privileged Identity Management では、組み込み Azure ロールとカスタム Azure ロールの両方がサポートされます。 Azure カスタム ロールの詳細については、Azure カスタム [ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/custom-roles)に関するページを参照してください。

### ロールの割り当て条件

Azure 属性ベースのアクセス制御 (Azure ABAC) を使用すると、Azure リソースに対して Microsoft Entra PIM を使用して、対象となるロールの割り当てに条件を追加できます。 Microsoft Entra PIM を使用する場合、エンド ユーザーは、対象となるロールの割り当てをアクティブ化して、特定のアクションを実行するためのアクセス許可を取得する必要があります。 Microsoft Entra PIM で条件を使用すると、きめ細かな条件を使用してリソースに対するユーザーのロールのアクセス許可を制限できるだけでなく、Microsoft Entra PIM を使用して、期限付きの設定、承認ワークフロー、監査証跡などでロールの割り当てを保護することもできます。

注

ロールが割り当てられている場合、割り当ては次のようになります。

- 5 分未満の期間は割り当てることができません。
- 割り当てられてから 5 分以内に削除することはできません。

現時点では、次の組み込みロールに条件を追加できます。

- [ストレージ BLOB データ共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#storage-blob-data-contributor)
- [ストレージ BLOB データ所有者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#storage-blob-data-owner)
- [ストレージ BLOB データ閲覧者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#storage-blob-data-reader)

詳細については、「 [Azure 属性ベースのアクセス制御 (Azure ABAC)とは](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview)」を参照してください。

### ロールを割り当てる

ユーザーを Azure リソース ロールの対象にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともユーザー アクセス管理者としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Azure リソース**を参照します。
3. 管理する **リソースの種類** を選択します。 **[管理グループ**] ドロップダウンまたは [**サブスクリプション**] ドロップダウンから開始し、必要に応じて [**リソース グループ**] または [**リソース**] をさらに選択します。 管理するリソースの **[選択** ] を選択して、その概要ページを開きます。

    [Image: Azure リソースを選択する方法を示すスクリーンショット。]
4. [ **管理**] で [ **ロール** ] を選択すると、Azure リソースのロールの一覧が表示されます。
5. [ **割り当ての追加]** を選択して、[ **割り当ての追加] ウィンドウを** 開きます。

    [Image: Azure リソース ロールのスクリーンショット。]
6. 割り当てる **ロール** を選択します。
7. [ **メンバーが選択されていない** ] リンクを選択して、[ **メンバーまたはグループの選択** ] ウィンドウを開きます。

    [Image: 新しい割り当てウィンドウのスクリーンショット。]
8. ロールに割り当てるメンバーまたはグループを選択し、[選択] を **選択します**。

    [Image: メンバーまたはグループ ペインを選択する方法を示すスクリーンショット。]
9. [ **設定** ] タブの [ **割り当ての種類** ] の一覧で、[ **対象]** または [ **アクティブ**] を選択します。

    [Image: [割り当ての追加] 設定ウィンドウのスクリーンショット。]

    Azure リソース向けの Microsoft Entra PIM には、以下の 2 つの異なる割り当ての種類があります。

    - **対象の** 割り当ては、メンバーがロールを使用する前にそのロールを有効化する必要があります。 管理者は、ロールのアクティブ化の前にロール メンバーに特定のアクションの実行を要求する場合があります。これには、多要素認証 (MFA) チェックの実行、業務上の正当な理由の提供、指定された承認者への承認の要求などがあります。
    - **アクティブな** 割り当てでは、使用前にメンバーがロールをアクティブ化する必要はありません。 アクティブに指定されたメンバーは、使用可能な特権を持っています。 この種類の割り当ては、Microsoft Entra PIM を使用していないお客様にも利用できます。
10. 特定の割り当て期間を指定するには、開始日時と終了日時を変更します。
11. ロールが条件を持つそのロールへの割り当てを許可するアクションで定義されている場合は、[ **条件の追加]** を選択して、割り当ての一部であるプリンシパル ユーザーとリソース属性に基づいて条件を追加できます。

    [Image: 新しい割り当て条件ペインのスクリーンショット。]

    条件は式ビルダーで入力できます。

    [Image: 式から構築された新しい代入条件のスクリーンショット。]
12. 完了したら、[ **割り当て**] を選択します。
13. 新しいロールの割り当てが作成されると、状態通知が表示されます。

    [Image: 新しい割り当て通知のスクリーンショット。]

### Azure Resource Manager API を使用してロールを割り当てる

Privileged Identity Management では、PIM Azure Resource Manager API リファレンスに記載されているように、Azure リソース ロールを管理するための [Azure Resource Manager](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-eligibility-schedule-requests) (ARM) API コマンドがサポートされています。 PIM API を使用するために必要なアクセス許可については、「 [Privileged Identity Management API について」を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-apis)参照してください。

次の例は、Azure のロールに資格のある割り当てを作成するためのサンプルの HTTP 要求です。

#### 依頼

```HTTP
PUT https://management.azure.com/providers/Microsoft.Subscription/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/roleEligibilityScheduleRequests/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e?api-version=2020-10-01-preview
```

#### 要求本文

```JSON
{
  "properties": {
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "roleDefinitionId": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/roleDefinitions/c8d4ff99-41c3-41a8-9f60-21dfdad59608",
    "requestType": "AdminAssign",
    "scheduleInfo": {
      "startDateTime": "2022-07-05T21:00:00.91Z",
      "expiration": {
        "type": "AfterDuration",
        "endDateTime": null,
        "duration": "P365D"
      }
    },
    "condition": "@Resource[Microsoft.Storage/storageAccounts/blobServices/containers:ContainerName] StringEqualsIgnoreCase 'foo_storage_container'",
    "conditionVersion": "1.0"
  }
}
```

注

`duration` フィールド (この例では`P365D`値) は、365 日後に有効な割り当ての有効期限を設定します。 永続的な適格性を設定するには、ターゲット スコープのロール管理ポリシーを、それを許可するように構成します。

#### [応答]

状態コード: 201

```HTTP
{
  "properties": {
    "targetRoleEligibilityScheduleId": "b1477448-2cc6-4ceb-93b4-54a202a89413",
    "targetRoleEligibilityScheduleInstanceId": null,
    "scope": "/providers/Microsoft.Subscription/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e",
    "roleDefinitionId": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/roleDefinitions/c8d4ff99-41c3-41a8-9f60-21dfdad59608",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "principalType": "User",
    "requestType": "AdminAssign",
    "status": "Provisioned",
    "approvalId": null,
    "scheduleInfo": {
      "startDateTime": "2022-07-05T21:00:00.91Z",
      "expiration": {
        "type": "AfterDuration",
        "endDateTime": null,
        "duration": "P365D"
      }
    },
    "ticketInfo": {
      "ticketNumber": null,
      "ticketSystem": null
    },
    "justification": null,
    "requestorId": "a3bb8764-cb92-4276-9d2a-ca1e895e55ea",
    "createdOn": "2022-07-05T21:00:45.91Z",
    "condition": "@Resource[Microsoft.Storage/storageAccounts/blobServices/containers:ContainerName] StringEqualsIgnoreCase 'foo_storage_container'",
    "conditionVersion": "1.0",
    "expandedProperties": {
      "scope": {
        "id": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e",
        "displayName": "Pay-As-You-Go",
        "type": "subscription"
      },
      "roleDefinition": {
        "id": "/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/roleDefinitions/c8d4ff99-41c3-41a8-9f60-21dfdad59608",
        "displayName": "Contributor",
        "type": "BuiltInRole"
      },
      "principal": {
        "id": "a3bb8764-cb92-4276-9d2a-ca1e895e55ea",
        "displayName": "User Account",
        "email": "user@my-tenant.com",
        "type": "User"
      }
    }
  },
  "name": "64caffb6-55c0-4deb-a585-68e948ea1ad6",
  "id": "/providers/Microsoft.Subscription/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/providers/Microsoft.Authorization/RoleEligibilityScheduleRequests64caffb6-55c0-4deb-a585-68e948ea1ad6",
  "type": "Microsoft.Authorization/RoleEligibilityScheduleRequests"
}
```

### 既存のロールの割り当てを更新または削除する

既存のロールの割り当てを更新または削除するには、次の手順に従います。

1. **Microsoft Entra Privileged Identity Management を**開きます。
2. **Azure リソース**を選択します。
3. 管理する **リソースの種類** を選択します。 **[管理グループ**] ドロップダウンまたは [**サブスクリプション**] ドロップダウンから開始し、必要に応じて [**リソース グループ**] または [**リソース**] をさらに選択します。 管理するリソースの **[選択** ] を選択して、その概要ページを開きます。

    [Image: 更新する Azure リソースを選択する方法を示すスクリーンショット。]
4. [ **管理**] で、[ **ロール** ] を選択して Azure リソースのロールを一覧表示します。 次のスクリーンショットは、Azure Storage アカウントのロールの一覧です。 更新または削除するロールを選択します。

    [Image: Azure Storage アカウントのロールを示すスクリーンショット。]
5. [対象ロール] タブまたは [**アクティブな**ロール] タブで**ロール**の割り当てを見つけます。

    [Image: スクリーンショットは、ロールの割り当てを更新または削除する方法を示しています。]
6. 条件を追加または更新して Azure リソース のアクセスを絞り込むには、ロールの割り当ての **[条件**] 列で **[追加**] または [**表示/編集]** を選択します。
7. **[式の追加]** または **[削除] を選択**して式を更新します。 [ **条件の追加]** を選択して、新しい条件をロールに追加することもできます。

    [Image: ロールの割り当ての属性を更新または削除する方法を示すスクリーンショット。]

    ロールの割り当ての拡張については、「 [Privileged Identity Management」の「Azure リソース ロールの拡張または更新」を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-resource-roles-configure-alerts"} -->
## Privileged Identity Management で Azure ロールに対するセキュリティ アラートを構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-alerts
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Azure リソース ロールに対するセキュリティの警告を構成する方法を説明します。

### 概要

Microsoft Entra ID の組織内で疑わしいアクティビティや危険なアクティビティが行われると、Privileged Identity Management (PIM) によりアラートが生成されます。 アラートがトリガーされると、[アラート] ページに表示されます。

注

Privileged Identity Management の 1 つのイベントで、複数の受信者 (担当者、承認者、管理者) 宛の電子メール通知を生成できます。 1 つのイベントごとに送信される通知の最大数は 1,000 です。 受信者の数が 1,000 人を超える場合は、最初の 1,000 人の受信者のみが電子メール通知を受信します。 この制限により、他の担当者、管理者、または承認者が Microsoft Entra ID と Privileged Identity Management でアクセス許可を使用できなくなります。

[Image: アラート、リスク レベル、および数を一覧表示するページのスクリーンショット。]

### アラートを確認する

アラートを選択して、アラートをトリガーしたユーザーまたはロールの他に解決策のガイダンスを示すレポートを表示します。

[Image: 最終スキャン時刻、説明、軽減手順、タイプ、重大度、セキュリティへの影響、および次回の防止方法を示すアラート レポートのスクリーンショット。]

### 警告

| アラート: | 重大度 | トリガー | 推奨 |
| --- | --- | --- | --- |
| **リソースに割り当てられている所有者が多すぎます** | ミディアム | 所有者ロールを持っているユーザーが多すぎます。 | リストに表示されているユーザーを見直して、数人のユーザーに低い特権ロールを再割り当てします。 |
| **リソースに割り当てられている永続的な所有者が多すぎます** | ミディアム | ロールに永続的に割り当てられているユーザーが多すぎます。 | リストに表示されているユーザーを見直して、数人のユーザーにロールを使用するにはアクティブ化が必要であるように再割り当てします。 |
| **重複したロールが作成されました** | ミディアム | 複数のロールが同じ条件を持っています。 | これらのロールのいずれかのみを使用します。 |
| **ロールが Privileged Identity Management の外部に割り当てられている** | 高い | ロールが、Azure IAM リソースまたは Azure Resource Manager API を使用して直接管理されています。 | リスト内のユーザーを確認し、Privileged Identity Management の外部で割り当てられた特権ロールから、これらのユーザーを削除します。 |

注

**Privileged Identity Management アラートの外部でロールが割り当てられている**場合は、重複する通知が発生する可能性があります。 これらの重複は、主に、通知が再び送信される潜在的なライブ サイト インシデントに関連している可能性があります。

#### 重大度

- **高**: ポリシー違反のため直ちに対処が必要です。
- **中**: 早急の対処は必要ありませんが、ポリシー違反の可能性が通知されています。
- **低**: 早急な対処は必要ありませんが、望ましいポリシー変更が提案されています。

### セキュリティ アラート設定の構成

次の手順に従って、Privileged Identity Management で Azure ロールに対するセキュリティ アラートを構成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Azure リソース**を参照します。 サブスクリプション &gt;**Alerts**&gt;**Setting** を選択します。 ダッシュボードに [Privileged Identity Management] タイルを追加する方法については、「[Privileged Identity Management の使用開始](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started)」を参照してください。

    [Image: [設定] が強調表示されている [アラート] ページのスクリーンショット。]
3. さまざまなアラートの設定を環境に合わせてカスタマイズし、自社のセキュリティ目標に合致させます。

    [Image: アラートの設定のスクリーンショット。]

注

"ロールは Privileged Identity Management の外部で割り当てられています" アラートは、Azure サブスクリプション用に作成されたロールの割り当てに対してトリガーされ、管理グループ、リソース グループ、またはリソース スコープでのロールの割り当てにはトリガーされません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-resource-roles-configure-role-settings"} -->
## PIM で Azure リソース ロールの設定を構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-role-settings
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Azure リソース ロールの設定を構成する方法について説明します。

### 概要

Microsoft Entra の一部である Microsoft Entra ID の Privileged Identity Management (PIM) では、ロール設定によりロールの割り当てプロパティが定義されます。 これらのプロパティには、多要素認証とアクティブ化の承認要件、割り当ての最大期間、通知設定が含まれます。 この記事では、ロール設定を構成し、承認ワークフローを設定して、特権を昇格させる要求を承認または拒否できるユーザーを指定する方法を説明します。

リソースの PIM ロール設定を管理するには、所有者またはユーザー アクセス管理者ロールが必要です。 ロールの設定は、ロールごとおよびリソースごとに定義されます。 同じロールに対するすべての割り当ては、同じロール設定に従います。 あるロールのロール設定は、別のロールのロール設定とは独立しています。 あるリソースのロール設定は、別のリソースのロール設定とは独立しています。 たとえば、**サブスクリプション**など、上位レベルで構成されたロール設定は、**リソース グループ**などの下位レベルには継承されません。

PIM ロールの設定は、PIM ポリシーとも呼ばれます。

### ロールの設定を開く

Azure リソース ロールの設定を開くには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Azure リソース**を参照します。 管理するリソースの種類を選択できます。 [管理グループ] ドロップダウンまたは [サブスクリプション] ドロップダウンから開始し、**[リソース グループ]** または **[リソース]** を必要に応じて選択します。

    [Image: Privileged Identity Management で検出された Azure リソースの一覧を示すスクリーンショット。]
3. PIM ロール設定を構成する必要があるリソースを選択します。
4. **[設定]** を選択します。 選択したリソースの PIM ポリシーの一覧を表示します。

    [Image: 選択したリソースの PIM ポリシーの一覧を示すスクリーンショット。]
5. 構成するロールまたはポリシーを選択します。
6. **[編集]** を選んでロール設定を更新します。
7. **[更新]** を選択します。

### ロールの設定

このセクションでは、ロール設定のオプションについて説明します。

#### アクティブ化の最大期間

**[アクティブ化の最大期間]** スライダーを使用して、ロールの割り当てのアクティブ化要求が、有効期限が切れるまでアクティブなままである最大時間 (時間単位) を設定します。 1 から 24 時間の範囲の値を指定できます。

#### アクティブ化の際に多要素認証を要求する

ロールの資格を持つユーザーに対して、アクティブにする前に Microsoft Entra ID の多要素認証機能を使うことで、自分が誰であるかを証明するように要求することができます。 多要素認証は、データやアプリケーションへのアクセスを保護するのに役立ちます。 第 2 の形式の認証を使用して、別のセキュリティのレイヤーが提供されます。

ユーザーは、強力な資格情報で認証を行った場合、またはこのセッションで以前に多要素認証を提供した場合には、多要素認証を求められないことがあります。

アクティブ化の際にユーザーが認証を提供することを必須にすることが目的である場合は、[\[On activation, require Microsoft Entra Conditional Access authentication context\] (アクティブ化の際に、Microsoft Entra 条件付きアクセス認証コンテキストを要求する)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#on-activation-require-azure-ad-conditional-access-authentication-context) を [\[認証強度\]](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths) と一緒に使用できます。 これらのオプションでは、ユーザーはアクティブ化の際に、マシンへのサインインに使用した方法とは異なる方法を使用して認証する必要があります。

たとえば、ユーザーが Windows Hello for Business を使ってマシンにサインインする場合は、**[On activation, require Microsoft Entra Conditional Access authentication context] (アクティブ化の際に、Microsoft Entra 条件付きアクセス認証コンテキストを要求する)** と **[認証強度]** を使って、ロールのアクティブ化時に Microsoft Authenticator を使ってパスワードレス サインインを行うように、ユーザーに要求できます。

この例では、ユーザーは Microsoft Authenticator によるパスワードレス サインインを 1 回提供した後は、このセッションでの次のアクティブ化は別の認証なしで行うことができます。 Microsoft Authenticator でのパスワードレス サインインは、既にトークンの一部になっています。

すべてのユーザーに対して Microsoft Entra ID の多要素認証機能を有効にします。 詳細については、「[Microsoft Entra 多要素認証の展開を計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)」を参照してください。

#### アクティブ化の際に、Microsoft Entra 条件付きアクセス認証コンテキストを要求する

ロールの資格を持つユーザーに条件付きアクセス ポリシーの要件を満たすことを要求できます。 たとえば、認証強度を通じて適用される特定の認証方法を使用すること、Intune 準拠デバイスからロールを昇格させること、使用条件に準拠することなどをユーザーに要求できます。

この要件を適用するには、条件付きアクセス認証コンテキストを作成します。

1. この認証コンテキストの要件を適用する条件付きアクセス ポリシーを構成します。
2. ロールの PIM 設定で認証コンテキストを構成します。

    [Image: [Edit role setting - Attestation Reader](ロール設定の編集 - 構成証明閲覧者) ページを示すスクリーンショット。]

PIM 設定で **[On activation, require Microsoft Entra Conditional Access authentication context] (アクティブ化の際に、Microsoft Entra 条件付きアクセス認証コンテキストを要求する)** が構成されている場合、条件付きアクセス ポリシーは、ユーザーがアクセス要件を満たすために満たす必要がある条件を定義します。

つまり、条件付きアクセス管理者やセキュリティ管理者など、条件付きアクセス ポリシーを管理するアクセス許可を持つセキュリティ プリンシパルは、要件の変更や削除を行ったり、資格を持つユーザーがロールをアクティブ化するのをブロックしたりすることができます。 条件付きアクセス ポリシーを管理できるセキュリティ プリンシパルは、高度な特権を持つと見なし、それに応じて保護する必要があります。

PIM 設定で認証コンテキストを構成する前に、認証コンテキストの条件付きアクセス ポリシーを作成して有効にすることをお勧めします。 PIM 設定で構成された認証コンテキストを対象とする条件付きアクセス ポリシーがテナント内に存在しない場合、[\[On activation, require multifactor authentication\] (アクティブ化の際に、多要素認証を要求する)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-role-settings#on-activation-require-multifactor-authentication) 設定が設定されるため、PIM ロールのアクティブ化時に、バックアップ保護メカニズムとして Microsoft Entra ID の多要素認証機能が必要になります。

このバックアップ保護メカニズムは、構成ミスによって、条件付きアクセス ポリシーの作成前に PIM 設定が更新された場合にのみ、保護するように設計されています。 条件付きアクセス ポリシーがオフになっている、レポート専用モードになっている、または資格を持つユーザーがポリシーから除外されている場合、このバックアップ保護メカニズムはトリガーされません。

すべてのロールのアクティブ化に再認証を適用するには、**セッション制御**の下でサインイン頻度を **[毎回**] に設定して、認証コンテキストを対象とする条件付きアクセス ポリシーを構成します。 これにより、ユーザーは、アクティブなセッションを持っていても、特権ロールをアクティブ化するたびに再認証する必要があります。

[Image: Microsoft Entra 条件付きアクセス認証コンテキスト オプションが選択されている [ロール設定の編集] ページを示すスクリーンショット。]

ユーザーが 1 つのロールのアクティブ化を再認証すると、10 分のウィンドウが適用されます。 ユーザーがこのウィンドウ内で別の有資格ロールをアクティブ化した場合、再認証を求めるメッセージは表示されません。 10 分間の期間は、Microsoft Entra ロール、Azure リソース ロール、グループの PIM に適用されます。

ユーザーが認証コンテキストで構成された有資格ロールをアクティブ化すると、"条件付きアクセス ポリシーが有効になっており、追加の検証が必要になる場合があります。 クリックして続行します。"その後、ユーザーは、条件付きアクセス ポリシーで定義されている再認証を完了するようにリダイレクトされます。

[Image: 追加の検証を必要とする条件付きアクセス ポリシー バナーが表示された [ロールのアクティブ化] ページを示すスクリーンショット。]

**[On activation, require Microsoft Entra Conditional Access authentication context] (アクティブ化の際に、Microsoft Entra 条件付きアクセス認証コンテキストを要求する)** 設定では、ユーザーがロールをアクティブにするときに満たす必要がある認証コンテキスト要件を定義します。 ロールがアクティブ化された後、ユーザーはアクセス許可を使用するために別のブラウズ セッション、デバイス、または場所を使用できます。

たとえば、ユーザーは Intune 準拠デバイスを使用してロールをアクティブ化できます。 ロールがアクティブ化された後、Intune 非準拠の別のデバイスから同じユーザー アカウントにサインインし、そこから以前にアクティブ化されたロールを使用できます。

このような状況を防ぐために、条件付きアクセス ポリシーのスコープを設定して、資格を持つユーザーに特定の要件を直接適用できます。 たとえば、特定のロールの資格を持つユーザーに対して、常に Intune 準拠のデバイスを使用するように要求できます。

条件付きアクセス認証コンテキストの詳細については、「[条件付きアクセス: クラウド アプリ、アクション、認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)」を参照してください。

#### アクティブ化の正当な理由を要求する

ユーザーが資格のある割り当てをアクティブにするときに、業務上の正当な理由を入力するように要求できます。

#### アクティブ化時にチケット情報を要求する

ユーザーが資格のある割り当てをアクティブにするときに、サポート チケット番号を入力するように要求できます。 このオプションは情報のみのフィールドです。 チケット システムの情報との相関関係は適用されません。

#### アクティブ化の承認を必須にする

適格な割り当てのアクティブ化には承認を要求できます。 承認者はロールを持っている必要はありません。 このオプションを使用する場合は、1 人以上の承認者を選択する必要があります。 少なくとも 2 人の承認者を選択することをお勧めします。 既定の承認者はいません。

承認の詳細については、「[Privileged Identity Management で Microsoft Entra ロールに対する要求を承認または拒否する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow)」を参照してください。

#### 割り当て期間

ロールの設定を構成するときに、割り当ての種類 ("対象" と "アクティブ") ごとに 2 つの割り当て期間オプションから選択できます。 これらのオプションは、Privileged Identity Management でユーザーがロールに割り当てられるときの既定の最大期間になります。

これらの資格のある割り当て期間オプションからいずれかを選択できます。

| 設定 | 説明 |
| --- | --- |
| 永続的な有資格割り当てを許可する | リソース管理者は、永続的に有効な割り当てを指定することができます。 |
| 次の期間後に資格要件を満たした割り当てが期限切れになる | リソース管理者は、すべての資格のある割り当てに、開始日と終了日の指定を必須にすることができます。 |

また、これらのアクティブな割り当て期間オプションからいずれかを選択できます。

| 設定 | 説明 |
| --- | --- |
| 永続的なアクティブ割り当てを許可する | リソース管理者は、永続的にアクティブな割り当てを行うことができます。 |
| 割り当ての有効期限は次の期間後に切れる | リソース管理者は、すべてのアクティブな割り当てに、開始日と終了日の指定を必須にすることができます。 |

グローバル管理者と特権ロール管理者は、指定された終了日を持つすべての割り当てを更新できます。 これに対し､ユーザーは[ロールの割り当てを延長または更新する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend)セルフサービス要求を開始することができます｡

#### アクティブな割り当てに多要素認証を要求する

管理者が (資格のある割り当てではなく) アクティブな割り当てを作成するときに、多要素認証を行うように要求できます。 ユーザーがロールの割り当てを使うときは、ロールが割り当てられた時点からロール内で既にアクティブになっているため、Privileged Identity Management で多要素認証を強制することはできません。

管理者は、強力な資格情報で認証を行った場合、またはこのセッションで以前に多要素認証を提供した場合には、多要素認証を求められないことがあります。

#### 現在の割り当ての正当化を求める

ユーザーが (資格のある割り当てではなく) アクティブな割り当てを作成するときに、業務上の正当な理由を入力するように要求できます。

**[ロールの設定]** ページの **[通知]** タブで、Privileged Identity Management によって、通知を受信するユーザーとユーザーが受信する通知をきめ細かく制御できます。

- **メールをオフにする**: 特定のメールをオフにするには、既定の受信者のチェック ボックスをオフにし、その他の受信者をすべて削除します。
- **指定したメール アドレスへのメールを制限する**: 既定の受信者に送信されるメールをオフにするには、既定の受信者のチェック ボックスをオフにします。 その後で、受信者として他のメール アドレスを追加できます。 複数のメール アドレスを追加する場合は、セミコロン (;) で区切ります。
- **既定の受信者と他の受信者の両方にメールを送信する**: 既定の受信者と他の受信者の両方にメールを送信できます。 既定の受信者のチェック ボックスをオンにし、他の受信者のメール アドレスを追加します。
- **重要なメールのみ**: メールの種類ごとに、重要なメールのみを受信するためのチェック ボックスをオンにできます。 メールで即時のアクションが求められている場合にのみ、指定された受信者に、Privileged Identity Management によって引き続きメールが送信されます。 たとえば、ユーザーにロールの割り当てを延長するよう求めるメールはトリガーされません。 管理者に延長要求の承認を要求するメールはトリガーされます。

注

Privileged Identity Management の 1 つのイベントで、複数の受信者 (担当者、承認者、管理者) 宛の電子メール通知を生成できます。 1 つのイベントごとに送信できる通知の最大数は 1000 です。 受信者の数が 1000 を超える場合は、最初の 1000 人の受信者のみが電子メール通知を受け取ります。 これにより、他の担当者、管理者、または承認者が Microsoft Entra ID と Privileged Identity Management でアクセス許可を使用できなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-resource-roles-discover-resources"} -->
## PIM で管理する Azure リソースを検出する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-discover-resources
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で管理する Azure リソースを検出する方法を説明します。

### 概要

Microsoft Entra ID の Privileged Identity Management (PIM) を使用して、Azure リソースの保護を向上させることができます。 これは以下に対して役立ちます。

- 既に Privileged Identity Management を使用して Microsoft Entra ロールを保護している組織
- 運用リソースをセキュリティで保護しようとしている管理グループとサブスクリプションの所有者

Privileged Identity Management を Azure リソース用に初めて設定するときは、Privileged Identity Management を使用して保護するリソースを検出して選択する必要があります。 Privileged Identity Management でリソースを検出する場合、PIM は、リソースのユーザー アクセス管理者として割り当てられた PIM サービス プリンシパル (MS-PIM) を作成します。 Privileged Identity Management を使用して管理できるリソースの数に制限はありません。 ただし、最も重要な運用リソースから始めます。

注

PIM では、オンボードを必要とせず、テナント内の Azure リソースを自動的に管理できるようになりました。 更新されたユーザー エクスペリエンスでは、最新の PIM ARM API が使用されるため、管理する適切なスコープを選択する際のパフォーマンスと細分性が向上します。 PIM で **Azure リソース** に移動する場合、新しいエクスペリエンスが既定です。 この記事の手順では、従来のエクスペリエンスについて説明します。 新しいエクスペリエンスとレガシ エクスペリエンスを切り替えるには、Azure リソース ページの上部にあるバナー リンクを使用します。

### 必要なアクセス許可

ユーザー アクセス管理者や所有者ロールなど、Microsoft.Authorization/roleAssignments/write 権限がある管理グループまたはサブスクリプションを表示し、管理できます。 サブスクリプションの所有者ではなく、グローバル管理者であり、管理する Azure サブスクリプションまたは管理グループが表示されない場合は、アクセス権を [昇格してリソースを管理](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)できます。

### リソースを探索する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**Privileged Identity Management**&gt;**Azure リソース**を参照します。

    Azure リソースの Privileged Identity Management を初めて使用する場合は、[ **リソースの検出** ] ページが表示されます。

    [Image: [リソースの検出] ウィンドウのスクリーンショット。初めてのエクスペリエンスでリソースが一覧表示されていません。]

    組織内の別の管理者が Privileged Identity Management で既に Azure リソースを管理している場合は、現在管理されているリソースの一覧が表示されます。

    [Image: 現在管理されているリソースが一覧表示されている [リソースの検出] ペインのスクリーンショット。]
3. [ **リソースの検出** ] を選択して検出エクスペリエンスを起動します。

    [Image: [検出] ウィンドウに、サブスクリプションや管理グループなど、管理できるリソースの一覧を示すスクリーンショット]
4. [ **検出** ] ページで、[ **リソース状態フィルター** ] と **[リソースの種類の選択]** を使用して、書き込みアクセス許可がある管理グループまたはサブスクリプションをフィルター処理します。 最初は **All** から始めるのが最も簡単です。

    管理グループまたはサブスクリプション リソースを検索して選択し、Privileged Identity Management で管理できます。 Privileged Identity Management で管理グループまたはサブスクリプションを管理する場合は、その子リソースも管理できます。

    注

    PIM で管理されている管理グループに新しい子 Azure リソースを追加すると、PIM で子リソースを検索することにより、それを管理対象にすることができます。
5. 管理するアンマネージド リソースを選択します。
6. [リソース  管理] を選択して、選択したリソースの管理を開始します。 PIM サービス プリンシパル (MS-PIM) は、リソースのユーザー アクセス管理者として割り当てられます。

    注

    管理グループまたはサブスクリプションは、いったん管理対象となったら、管理対象外にすることはできません。 これにより、別のリソース管理者が Privileged Identity Management 設定を削除できなくなります。

    [Image: リソースが選択され、[リソースの管理] オプションが強調表示された [探索] ウィンドウ]
7. 選択したリソースの管理のオンボードを確認するメッセージが表示された場合は、[ **はい**] を選択します。 その後、PIM は、リソースの下にあるすべての新規および既存の子オブジェクトを管理するように構成されます。

    [Image: 選択したリソースを管理用にオンボードすることを確認するメッセージを示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-resource-roles-overview-dashboards"} -->
## PIM でのアクセス レビュー用のリソース ダッシュボード - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-overview-dashboards
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra Privileged Identity Management (PIM) でアクセス レビューを実行するためにリソース ダッシュボードを使用する方法を説明します。

### 概要

Privileged Identity Management (PIM) でアクセス レビューを実行するためにリソース ダッシュボードを使用できます。 Microsoft Entra の一部である Microsoft Entra ID の [管理者ビュー] ダッシュボードには、次の 3 つの主要なコンポーネントがあります。

- リソース ロールのアクティブ化のグラフィカル表現。
- 割り当ての種類別のロールの割り当ての分布を表示するグラフ。
- 新しいロールの割り当てに関する情報を含むデータ領域。

[Image: グラフが表示された [管理者ビュー] のスクリーンショット。]

[Image: データの一覧が表示された [管理者ビュー] ダッシュボードのスクリーンショット。]

過去 7 日間のリソース ロールのアクティブ化がグラフィカルに表示されています。 このデータは、選択したリソースが対象となっていて、特に一般的なロール (所有者、共同作成者、ユーザー アクセス管理者) と、組み合わせられたすべてのロールのアクティブ化を表示しています。

アクティブ化のグラフの片側には 2 つのグラフがあり、ユーザーとグループの両方のロール割り当ての分布が割り当ての種類ごとに表示されています。 グラフのスライスを選択すると、値が割合 (またはその逆) に変更されます。

グラフの下には、過去 30 日間の新しいロール割り当てが行われたユーザーとグループの数と、合計の割り当て数の降順で並べ替えられたロールが一覧表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend"} -->
## PIM で Azure リソース ロールの割り当てを更新する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-renew-extend
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Privileged Identity Management (PIM) で Azure リソース ロールの割り当てを延長または更新する方法について説明します。

### 概要

Microsoft Entra Privileged Identity Management (PIM) は、Azure リソースのアクセスと割り当てのライフサイクルを管理するための制御を提供します。 管理者は、開始時刻と終了日時のプロパティを使用してロールを割り当てることができます。 割り当てが終了すると、Privileged Identity Management は、影響を受けるユーザーまたはグループに電子メール通知を送信します。 また、リソースの管理者に電子メール通知を送信して、適切なアクセスが維持されるようにします。 アクセスが延長されていない場合でも、割り当てが更新され、有効期限が切れた状態で最大 30 日間表示される場合があります。

### 誰が延長および更新できますか？

ロールの割り当てを延長または更新できるのは、リソースの管理者だけです。 影響を受けるユーザーまたはグループは、有効期限が近づいているロールの拡張と、既に期限切れのロールの更新を要求できます。

### 通知はいつ送信されますか?

Privileged Identity Management は、有効期限が切れる 14 日以内および 1 日前に期限切れになる管理者および影響を受けるロールのユーザーまたはグループに電子メール通知を送信します。 割り当てが正式に期限切れになると、追加の電子メールが送信されます。

管理者は、ロールの期限が近づいているか、既に期限切れのユーザーまたはグループが延長または更新を要求したときに通知を受け取ります。 特定の管理者が要求を解決すると、他のすべての管理者に解決の決定 (承認または拒否) が通知されます。 その後、要求しているユーザーまたはグループに決定が通知されます。

### ロールの割り当てを拡張する

次の手順では、ロールの割り当ての延長または更新を要求、解決、または管理するプロセスについて説明します。

#### 自分で期限切れの割り当てを延長する

ロールに割り当てられたユーザーは、期限切れのロールの割り当てを、リソースの [自分のロール ] ページの [有資格] タブまたは [アクティブな] タブ、および Privileged Identity Management ポータルの最上位レベルの [自分のロール] ページから直接延長できます。 ポータルでは、ユーザーは、今後 14 日以内に期限切れになる対象ロールまたはアクティブ (割り当て済み) ロールの延長を要求できます。

[Image: [アクション] 列を持つ対象ロールが一覧表示されている [自分のロール] ページのスクリーンショット。]

割り当ての終了日時が 14 日以内になると、Microsoft Entra 管理センターで **[延長** ] へのリンクがアクティブになります。 次の例では、現在の日付が 3 月 27 日であるとします。

手記

ロールに割り当てられたグループの場合は、割り当てを継承している 1 人のユーザーがグループの割り当てを延長できないように、**[延長]** リンクが利用できなくなっています。

[Image: アクティブ化または拡張へのリンクを含むアクション列のスクリーンショット。]

このロールの割り当ての延長を要求するには、[ **延長** ] を選択して要求フォームを開きます。

[Image: [ロールの割り当ての延長] ウィンドウの [理由] ボックスのスクリーンショット。]

元の割り当てに関する情報を表示するには、**割り当ての詳細**を展開します。 延長要求の理由を入力し、**[延長]** を選択します。

手記

拡張機能が必要な理由と、拡張機能を付与する必要がある期間 (この情報がある場合) の詳細を含めます。

[Image: [割り当ての詳細] が展開されている [ロールの割り当ての延長] ペインのスクリーンショット。]

リソース管理者は、しばらくすると、拡張機能の要求を確認することを要求する電子メール通知を受け取ります。 延長要求が既に送信されている場合は、ポータルに Azure 通知が表示されます。

[Image: 保留中のロールの割り当て拡張機能が既に存在することを説明する通知のスクリーンショット。]

**保留中の要求** ページに移動して、要求の状態を表示するか、キャンセルします。

[Image: Azure リソースのスクリーンショット - 保留中の要求とキャンセルへのリンクが一覧表示された [保留中の要求] ページ。]

#### 管理者が承認した拡張機能

ユーザーまたはグループがロールの割り当てを延長する要求を送信すると、リソース管理者は、元の割り当ての詳細と要求の理由を含む電子メール通知を受け取ります。 通知には、管理者が承認または拒否するための要求への直接リンクが含まれています。

管理者は、電子メールからのリンクに従うだけでなく、Privileged Identity Management 管理ポータルに移動し、左側のウィンドウで [要求の承認] を選択することで **、要求を承認** または拒否できます。

[Image: Azure リソースのスクリーンショット - 承認または拒否する要求とリンクを一覧表示する [要求の承認] ページ。]

管理者が **[承認** ] または **[拒否**] を選択すると、要求の詳細と、監査ログの業務上の正当な理由を提供するフィールドが表示されます。

[Image: 要求者の理由、割り当ての種類、開始時刻、終了時刻、理由を含むロールの割り当て要求の承認のスクリーンショット。]

ロールの割り当てを延長する要求を承認する場合、リソース管理者は新しい開始日、終了日、割り当ての種類を選択できます。 管理者が特定のタスクを完了するために制限付きアクセスを提供する場合は、割り当ての種類を変更することが必要になる場合があります (たとえば、1 日)。 この例では、管理者は割り当てを **Eligible** から **Active**に変更できます。 つまり、アクティブ化を要求することなく、要求者へのアクセスを提供できます。

#### 管理者が開始した拡張機能

ロールに割り当てられたユーザーがロールの割り当ての拡張機能を要求しない場合、管理者はユーザーに代わって割り当てを延長できます。 ロールの割り当ての管理拡張機能では承認は必要ありませんが、通知はロールの延長後に他のすべての管理者に送信されます。

ロールの割り当てを拡張するには、Privileged Identity Management のリソース ロールまたは割り当てビューを参照します。 拡張機能を必要とする割り当てを見つけます。 次に、[アクション] 列の **[延長]** を選択します。

[Image: Azure リソースの [割り当て] ページに、対象となるロールと拡張するリンクが一覧表示されているスクリーンショット。]

### ロールの割り当てを更新する

概念的には拡張機能を要求するプロセスと似ていますが、期限切れのロールの割り当てを更新するプロセスは異なります。 次の手順を使用すると、割り当てと管理者は、必要に応じて期限切れのロールへのアクセスを更新できます。

#### 自己更新

リソースにアクセスできなくなったユーザーは、最大 30 日間の期限切れの割り当て履歴にアクセスできます。 これを行うには、左側のウィンドウで **[マイ ロール** ] を参照し、[Azure リソース ロール] セクションの [ **期限切れのロール** ] タブを選択します。

[Image: [自分のロール] ページの [期限切れのロール] タブのスクリーンショット。]

表示されるロールの一覧は、既定で **対象ロール**です。 ドロップダウン メニューを使用して、割り当てられた有資格ロールとアクティブ割り当てロールを切り替えます。

一覧のロールの割り当ての更新を要求するには、**更新** アクションを選択します。 次に、要求の理由を指定します。 リソース管理者が承認または拒否を決定するのに役立つ他のコンテキストや業務上の正当な理由に加えて、期間を提供すると便利です。

[Image: [理由] ボックスが表示されている [ロールの割り当ての更新] ウィンドウのスクリーンショット。]

要求が送信されると、リソース管理者にロールの割り当てを更新する保留中の要求が通知されます。

#### 管理者が承認する

リソース管理者は、電子メール通知のリンクから更新要求にアクセスするか、Microsoft Entra 管理センターから Privileged Identity Management にアクセスし、左側のウィンドウから **[要求の承認** ] を選択することでアクセスできます。

[Image: Azure リソースのスクリーンショット - 承認または拒否する要求とリンクを一覧表示する [要求の承認] ページ。]

管理者が **[承認]** または **[拒否]** を選択すると、要求の詳細が、監査ログ用の業務上の正当な理由を入力するフィールドと共に表示されます。

[Image: 要求者の理由、割り当ての種類、開始時刻、終了時刻、理由を含むロールの割り当て要求の承認のスクリーンショット。]

ロールの割り当てを更新する要求を承認する場合、リソース管理者は新しい開始日、終了日、割り当ての種類を入力する必要があります。

#### 管理者による更新

リソース管理者は、リソースの左側のナビゲーション メニューの **メンバー** タブから、期限切れのロールの割り当てを更新できます。 リソース管理者は、リソース ロールの [期限切れのロール] タブから **、期限切れの** ロールの割り当てを更新することもできます。

期限切れになったすべてのロールの割り当ての一覧を表示するには、[メンバー] 画面で、[期限切れのロール]選択します。

[Image: Azure リソースのスクリーンショット - 更新するリンクを含む期限切れのロールを一覧表示する [メンバー] ページ。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-roles"} -->
## Privileged Identity Management で管理できないロール - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-roles
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra Privileged Identity Management (PIM) で管理できないロールについて説明します。

### 概要

Microsoft Entra ID の Privileged Identity Management (PIM) を使って、すべての [Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)とすべての [Azure ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)に対する Just-In-Time 割り当てを管理できます。 Azure ロールには、管理グループ、サブスクリプション、リソース グループ、リソースに関連付けられている組み込みロールとカスタム ロールが含まれます。 ただし、管理できないロールがいくつかあります。 この記事では、Privileged Identity Management で管理できないロールについて説明します。

### 従来のサブスクリプション管理者ロール

Privileged Identity Management では、次のクラシック サブスクリプション管理者ロールを管理することはできません。

- アカウント管理者
- サービス管理者
- 共同管理者

従来のサブスクリプション管理者ロールについて詳しくは、[Azure ロール、Microsoft Entra ロール、従来のサブスクリプション管理者ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/rbac-and-directory-admin-roles)に関する記事を参照してください。

### Microsoft 365 管理者ロールについて

PIM では、Exchange 管理者や SharePoint 管理者など、Microsoft Entra ロールと管理者ポータル エクスペリエンスのすべての Microsoft 365 ロールがサポートされますが、PIM は Exchange RBAC または SharePoint RBAC 内の特定のロールをサポートしていません。 これらの Microsoft 365 サービスの詳細については、[Microsoft 365 の管理者ロール](https://learn.microsoft.com/ja-jp/microsoft-365/admin/add-users/about-admin-roles)に関するページをご覧ください。

注意

Microsoft Entra 参加済みデバイスローカル管理者ロールのアクティブ化の遅延については、「[Microsoft Entra 参加済みデバイスでローカル管理者グループを管理する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/assign-local-admin#manage-the-microsoft-entra-joined-device-local-administrator-role)」を参照してください。

注意

PIM for Groups ではなく [Microsoft Entra ロールに PIM](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user) を使用して、SharePoint、Exchange、または Microsoft Purview ポータルへの Just-In-Time アクセスを提供します。 詳細については、[グループの Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups#make-a-group-of-users-eligible-for-a-microsoft-entra-role)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-security-wizard"} -->
## Privileged Identity Management での Microsoft Entra ロールの検出と分析情報 (プレビュー) (以前のセキュリティ ウィザード) - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-security-wizard
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: 検出と分析情報 (以前のセキュリティ ウィザード) を使用すると、永続的な Microsoft Entra ロールの割り当てを Privileged Identity Management を使用した Just-In-Time 割り当てに変換できます。

### 概要

Microsoft Entra ID で Privileged Identity Management (PIM) を使用して組織内のロールの割り当てを管理する場合は、[ **検出と分析情報 (プレビュー)]** ページを使用して作業を開始できます。 この機能によって、組織内の特権ロールに割り当てられているユーザーと、PIM を使用して永続的なロールの割り当てを Just-In-Time 割り当てにすばやく変更する方法が示されます。 **探索と分析情報 (プレビュー)** では、永続的な特権ロールの割り当てを表示または変更できます。 これは分析ツールであり、アクション ツールです。

### 検出と洞察 (プレビュー)

組織で Privileged Identity Management の使用を開始する前に、すべてのロールの割り当てが永続になります。 ユーザーは特権を必要としない場合でも、常に割り当てられたロールに含まれます。 以前のセキュリティウィザードに代わる [Discovery and insights (preview)](検出と分析情報 (プレビュー)) には、特権ロールの一覧と、それらのロールに現在存在しているユーザーの数が表示されます。 1 人または複数のロールの割り当てに不慣れな場合、割り当てられたユーザーについて詳しく知るために、ロールの割り当てリストを表示できます。

✔️ Microsoft では、グローバル [管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられている 2 つのクラウド専用緊急アクセス アカウントを組織に付与することをお勧めします。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 アカウントは、通常のアカウントを使用できない緊急時、または他のすべての管理者が誤ってロックアウトされてしまう"ブレークグラス"シナリオに限定されます。これらのアカウントは、[緊急アクセスアカウントの推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。

また、ユーザーに Microsoft アカウント (つまり、Skype、Outlook.com などの Microsoft サービスへのサインインに使用するアカウント) がある場合は、ロールの割り当ても永続的に維持してください。 ロールの割り当てをアクティブ化するために Microsoft アカウントを持つユーザーに多要素認証が必要な場合、ユーザーはロックアウトされます。

### [Discovery and insights (preview)](検出と分析情報 (プレビュー)) を開く

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**特権 ID 管理**&gt;**Microsoft Entra ロール**&gt;**ディスカバリーと分析情報 (プレビュー)** にアクセスします。
3. ページを開くと、検出プロセスが開始され、関連するロールの割り当てが検索されます。

    [Image: Microsoft Entra ロールの検出と分析情報ページを示すスクリーンショット。]
4. [ **グローバル管理者の削減**] を選択します。

    [Image: [全体管理者の削減] アクションが選択された [検出と分析情報 (プレビュー)] を示すスクリーンショット。]
5. グローバル管理者ロールの割り当ての一覧を確認します。

    [Image: すべてのグローバル管理者を示す [ロール] ウィンドウを示すスクリーンショット。]
6. [ **次へ** ] を選択して、対象にするユーザーまたはグループ **を選択し** 、[対象にする] または **[割り当ての削除**] を選択します。

    [Image: ロールの対象にするメンバーを選択するオプションを含む、メンバーを対象ページに変換する方法を示すスクリーンショット。]
7. 必要に応じて、すべてのグローバル管理者が自分のアクセス権を確認する必要があります。

    [Image: [アクセス レビュー] セクションを示す [全体管理者] ページを示すスクリーンショット。]
8. これらの変更のいずれかを選択すると、Azure の通知が表示されます。
9. 他の特権ロールと**サービス プリンシパル ロールの**割り当てに対して上記の手順を繰り返すには、[**永続的なアクセスを排除**する] または [サービス プリンシパルの確認] を選択します。 サービス プリンシパルのロールの割り当てでは、ロールの割り当てのみを削除できます。

    [Image: 永続的なアクセスを排除し、サービス プリンシパルを確認するための追加の洞察オプションを示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/pim-troubleshoot"} -->
## Privileged Identity Management で拒否されたリソース アクセスのトラブルシューティング - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-troubleshoot
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-04-23
- Summary: Microsoft Entra Privileged Identity Management (PIM) でロールに関するシステム エラーのトラブルシューティング方法を説明します。

### 概要

Microsoft Entra ID の Privileged Identity Management (PIM) で問題が発生している場合は、この記事に記載されている情報がこれらの問題の解決に役立ちます。

### Azure リソースへのアクセスが拒否されました

#### 問題

Azure リソースのアクティブな所有者またはユーザー アクセス管理者は、Privileged Identity Management 内で自分のリソースを見ることはできますが、資格のある割り当てを行ったり、リソース概要ページからロールの割り当ての一覧を表示したりするといったアクションを実行することはできません。 これらのアクションを実行すると、承認エラーが発生します。

#### 原因

この問題は、PIM サービス プリンシパルのユーザー アクセス管理者ロールが、誤ってサブスクリプションから削除された場合に発生する可能性があります。 Privileged Identity Management サービスが Azure リソースにアクセスするには、MS-PIM サービス プリンシパルに常に [ユーザー アクセス管理者ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#user-access-administrator) が割り当てられている必要があります。

#### 解決方法

サブスクリプション レベルで Privileged Identity Management サービス プリンシパル名 (MS-PIM) にユーザー アクセス管理者ロールを割り当てます。 この割り当てにより、Privileged Identity Management サービスは Azure リソースにアクセスできます。 要件に応じて、管理グループ レベルまたはサブスクリプション レベルでロールを割り当てます。 サービス プリンシパルの詳細については、「 [アプリケーションをロールに割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-service-principal-portal#assign-a-role-to-the-application)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/privileged-identity-management/privileged-identity-management-custom-extensions"} -->
## PIM ロールのアクティブ化用にカスタム拡張機能を構成する (プレビュー) - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/privileged-identity-management-custom-extensions
- Service: entra-id-governance / privileged-identity-management
- Article date: 2026-06-02
- Summary: カスタム ビジネス ロジックをロールアクティブ化ワークフローに統合するために、Microsoft Entra Privileged Identity Management (PIM) でカスタム拡張機能を構成する方法について説明します。

Microsoft Entra Privileged Identity Management (PIM) のカスタム拡張機能を使用すると、組織はカスタム ビジネス ロジックをロールのアクティブ化プロセスに統合できます。 これらの拡張機能は、PIM がポリシー評価中に呼び出す REST API として実装されます。 カスタム拡張機能はアクティブ化要求を評価し、アクティブ化を許可するか拒否するかを示す決定を PIM に返します。

カスタム拡張機能を使用して以下を行います。

- 内部システムに対してチケット番号を検証します。
- 雇用状態やロールの適格性など、HR ベースのアクセス規則を適用します。
- コンプライアンス ワークフローまたは監査システムを統合します。
- ビジネス コンテキストに基づいて動的承認ロジックを適用します。

カスタム拡張機能では、グループの PIM、Microsoft Entra ロールの PIM、Azure リソースの PIM がサポートされます。

[Image: アクティブ化要求からポリシー評価、カスタム拡張機能 API、および外部サービスまでの PIM カスタム拡張機能アーキテクチャ フローを示す図。]

Important

PIM のカスタム拡張機能はプレビュー段階です。 ベータ版、プレビュー版、または他の方法で一般公開されていない Azure の機能に適用される法的条件については、[Microsoft Azure プレビューの追加利用規約](https://azure.microsoft.com/support/legal/preview-supplemental-terms/)を参照してください。

### ライセンス要件

PIM でカスタム拡張機能を使用するには、アクティブなMicrosoft Entra ID ガバナンス (EIG) またはEntra スイート ライセンスが必要です。 ライセンスの詳細については、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

### 前提条件

- 少なくとも **特権ロール管理者** ロールが割り当てられていること。
- HTTP POST 要求を受け入れ、PIM が想定する形式で評価応答を返す REST API エンドポイント。 API は任意の言語で構築でき、任意のプラットフォームでホストできます。
- PIM とカスタム拡張機能エンドポイント間の通信をセキュリティで保護するためのMicrosoft Entra ID アプリケーション登録。
- この機能はプレビュー段階であるため、Microsoft Graph API `beta` エンドポイントが必要です。
- 操作に応じて必要な、次の委任された Microsoft Graph のアクセス許可:

    | Operation | 権限（最小特権） |
    | --- | --- |
    | カスタム拡張機能を作成または更新する | `PrivilegedAccess-CustomExt.ReadWrite.All` |
    | カスタム拡張機能を取得または一覧表示する | `PrivilegedAccess-CustomExt.Read.All` |

### カスタム拡張機能エンドポイントを構築する

PIM からの HTTP POST 要求を受け入れる REST API を構築します。 ロールのアクティブ化中、PIM は、アクティブ化を評価するために必要な情報を含む要求ペイロードをエンドポイントに送信します。 API は要求を評価し、アクティブ化を承認、自動承認、または拒否するかどうかを示す応答を返します。

PIM が送信する要求モデルは、リソースの種類 (Microsoft Entra グループ、Microsoft Entra ロール、またはリソースAzureによって異なります。 次のセクションでは、使用されているリソースの種類に基づく例を示します。

#### グループの PIM の要求モデル

グループの PIM の要求モデルでは、PIM は `privilegedAccessGroupAssignmentScheduleRequest` オブジェクトを送信します。 完全なスキーマについては、「 [assignmentScheduleRequest の作成](https://learn.microsoft.com/ja-jp/graph/api/privilegedaccessgroup-post-assignmentschedulerequests)」を参照してください。

```json
{
  "@odata.type": "#microsoft.graph.privilegedAccessGroupAssignmentScheduleRequest",
  "id": "String (identifier)",
  "status": "String",
  "action": "String",
  "justification": "String",
  "principalId": "String",
  "accessId": "String",
  "groupId": "String",
  "scheduleInfo": {
    "@odata.type": "microsoft.graph.requestSchedule"
  },
  "ticketInfo": {
    "@odata.type": "microsoft.graph.ticketInfo"
  }
}
```

#### Microsoft Entra ロール向け PIM の申請モデル

Microsoft Entra ロールの PIM の要求モデルでは、PIM は `unifiedRoleAssignmentScheduleRequest` オブジェクトを送信します。 完全なスキーマについては、「 [roleAssignmentScheduleRequests の作成](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignmentschedulerequests)」を参照してください。

```json
{
  "@odata.type": "#microsoft.graph.unifiedRoleAssignmentScheduleRequest",
  "id": "String (identifier)",
  "status": "String",
  "action": "String",
  "principalId": "String",
  "roleDefinitionId": "String",
  "directoryScopeId": "String",
  "justification": "String",
  "scheduleInfo": {
    "@odata.type": "microsoft.graph.requestSchedule"
  },
  "ticketInfo": {
    "@odata.type": "microsoft.graph.ticketInfo"
  }
}
```

#### Azure リソースの PIM の要求モデル

AZURE リソースの PIM の要求モデルでは、PIM はロール割り当てスケジュール要求オブジェクトを送信します。 完全なスキーマについては、「 [ロールの割り当てスケジュール要求 - 作成」](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-assignment-schedule-requests/create)を参照してください。

```json
{
  "properties": {
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "principalType": "User",
    "requestType": "SelfActivate",
    "roleDefinitionId": "/subscriptions/{subscriptionId}/providers/Microsoft.Authorization/roleDefinitions/{roleDefinitionId}",
    "scope": "/subscriptions/{subscriptionId}",
    "justification": "String",
    "scheduleInfo": {
      "startDateTime": "2020-09-09T21:35:27.91Z",
      "expiration": {
        "type": "AfterDuration",
        "duration": "PT8H"
      }
    },
    "ticketInfo": {
      "ticketNumber": "String",
      "ticketSystem": "String"
    }
  }
}
```

#### 応答モデル

カスタム拡張機能は、アクティブ化要求を評価した後、評価結果とサポートの詳細を含む PIM への応答を返す必要があります。

| フィールド | タイプ | Description |
| --- | --- | --- |
| `evaluationId` | String | 評価用のカスタム拡張機能によって生成される一意の識別子。 この値は、追跡可能性のために PIM 監査に記録されます。 |
| `evaluationOutcome` | String | 評価の結果。 サポートされている値: `Approved`、`AutoApproved`、`Denied`。 |
| `reason` | List&lt;String&gt; | 評価結果を説明するメッセージ。 これらのメッセージは、要求が拒否されたときに UI に表示されます。 |

次の例は、各結果の有効な応答ペイロードを示しています。

**承認済み**: すべての検証チェックに合格し、アクティブ化は通常の PIM ワークフローを通じて続行されます。

```json
{
  "evaluationId": "aaaa0000-bb11-2222-33cc-444444dddddd",
  "evaluationOutcome": "Approved",
  "reason": ["Valid request"]
}
```

**AutoApproved**: 要求は自動承認の条件を満たし、アクティブ化は手動の PIM 承認を必要とせずに続行されます。

```json
{
  "evaluationId": "bbbb1111-cc22-3333-44dd-555555eeeeee",
  "evaluationOutcome": "AutoApproved",
  "reason": ["High severity incident for on-call engineer, bypass manual approval"]
}
```

**拒否**: 1 つ以上の検証チェックが失敗し、PIM によってアクティブ化がブロックされます。

```json
{
  "evaluationId": "cccc2222-dd33-4444-55ee-666666ffffff",
  "evaluationOutcome": "Denied",
  "reason": ["Requestor has not cleared Cloud Screening"]
}
```

### カスタム拡張機能サービスをセキュリティで保護する

Microsoft Entra ID アプリケーションを登録し、トークン検証を構成することで、PIM とカスタム拡張機能 REST API 間の通信をセキュリティで保護します。

#### Microsoft Entra ID アプリケーションを登録する

カスタム拡張機能サービスは、Microsoft Entra ID アプリケーションによってサポートされている必要があります。 PIM では、このアプリ登録を使用してカスタム拡張機能エンドポイントに対する認証を行い、API のアクセス トークンを要求します。 Microsoft Entra ID アプリケーションを登録するには、次の手順を実行します。

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. **Identity**&gt;**アプリケーション**&gt;**アプリの登録** に移動します。
3. **新規登録**を選択します。
4. わかりやすいアプリケーション名 (例: `Contoso PIM Custom Extension API`) を入力します。
5. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。
6. このサービス間の登録には、リダイレクト URI は必要ありません。
7. **登録**を選択します。

#### アプリケーション ID URI を構成する

アプリが作成されたら、アプリケーション ID URI を構成します。 アプリケーション ID URI のホスト名は、カスタム拡張機能エンドポイント URL のホスト名と一致する必要があります。

1. アプリの登録を開き、[ **API の公開]** に移動します。
2. [**アプリケーション ID URI**] の横にある **[設定**] を選択します。
3. API を表す値を入力します。 URI は、アプリ登録のアプリケーション (クライアント) ID で終わる必要があります。 たとえば、カスタム拡張機能エンドポイントが `https://api.contoso.com/webhooks/entra-role-assignments` で、アプリケーション (クライアント) ID が `00001111-aaaa-2222-bbbb-3333cccc4444` の場合、アプリケーション ID URI を次のように設定します: `api://api.contoso.com/00001111-aaaa-2222-bbbb-3333cccc4444`
4. **保存**を選びます。 [Image: API を公開するためのアプリ登録ページのスクリーンショット。]

アプリケーション ID URI を保存します。 PIM でカスタム拡張機能を構成するときに、この値を `resourceId` として指定します。

#### API でベアラー トークンを検証する

PIM は、カスタム拡張機能 REST API を呼び出すと、Microsoft Entra IDによって発行されたベアラー トークンを含む HTTP Authorization ヘッダーを送信します。 API に次のトークン検証チェックを実装します。

- **アプリケーション要求の呼び出し** - `appid` 要求 (V1 トークンの場合) または `azp` 要求 (V2 トークンの場合) に `00001111-aaaa-2222-bbbb-3333cccc4444`値が含まれていることを検証します。 この値は、呼び出し元としてMicrosoft Entra Privileged Identity Managementを識別します。
- **対象ユーザー要求** - `aud` 要求に、アプリ登録用に構成したアプリケーション ID URI が含まれていることを検証します。
- **発行者クレーム** - `iss` クレームに、お使いのテナント用の Microsoft Entra 発行者 URL（`https://login.microsoftonline.com/{tenantId}/v2.0`）が含まれていることを確認します。

### PIM へのカスタム拡張機能のオンボード

PIM がロールのアクティブ化要求中にエンドポイントを呼び出すことができるように、テナントにカスタム拡張機能を作成します。 拡張機能を作成するときは、エンドポイント PIM 呼び出し、そのエンドポイントのセキュリティ保護に使用されるMicrosoft Entra ID リソース、および拡張機能が適用される PIM リソースの種類を定義します。

カスタム拡張機能を作成するために、MICROSOFT Graph API `beta` エンドポイントに POST 要求を送信します。

```http
POST https://graph.microsoft.com/beta/identityGovernance/privilegedAccess/customExtensions
Content-Type: application/json
```

次の例では、グループの PIM 用のカスタム拡張機能を作成します。

```json
{
  "@odata.type": "#microsoft.graph.roleManagementCustomCalloutExtension",
  "displayName": "Role Assignment Ticket Validation",
  "description": "Validates ticket info provided by requestor against ServiceNow",
  "endpointConfiguration": {
    "@odata.type": "#microsoft.graph.httpRequestEndpoint",
    "targetUrl": "https://api.contoso.com/webhooks/entra-role-assignments"
  },
  "clientConfiguration": {
    "@odata.type": "#microsoft.graph.customExtensionClientConfiguration",
    "timeoutInMilliseconds": 10000,
    "maximumRetries": 3
  },
  "authenticationConfiguration": {
    "@odata.type": "#microsoft.graph.azureAdTokenAuthentication",
    "resourceId": "api://api.contoso.com/a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1"
  },
  "resourceType": "entraGroups",
  "customAttributes": []
}
```

Note

そのコード スニペットのリソース ID には、使用する独自の ID を入力します。

次の表では、カスタム拡張 API モデルのキー フィールドについて説明します。

| フィールド | タイプ | Description |
| --- | --- | --- |
| `displayName` | String | 管理エクスペリエンスに表示されるカスタム拡張機能のフレンドリ名。 |
| `description` | String | カスタム拡張機能の機能の説明。 |
| `endpointConfiguration.targetUrl` | String | カスタム拡張機能呼び出しを受信する HTTPS エンドポイント。 |
| `clientConfiguration.timeoutInMilliseconds` | Integer | PIM がエンドポイントの応答を待機する最大時間。 |
| `clientConfiguration.maximumRetries` | Integer | 呼び出しが失敗した場合の再試行の最大数。 |
| `authenticationConfiguration.resourceId` | String | エンドポイントをセキュリティで保護するMicrosoft Entra アプリのアプリケーション ID URI。 |
| `resourceType` | String | 拡張機能が適用される PIM プロバイダーを識別します。 |

応答が成功すると、作成されたカスタム拡張オブジェクトを含む HTTP `201 Created` 状態コードが返されます。

#### ポータルでカスタム拡張機能を作成する

Microsoft Entra 管理センターを使用してカスタム拡張機能を作成することもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Identity governance**&gt;**Privileged Identity Management**&gt;**Custom Extensions** に移動します。
3. [ **カスタム拡張機能の作成] を選択します**。
4. [ **基本** ] タブで、カスタム拡張機能の名前、説明、リソースの種類を入力します。

    [Image: カスタム拡張機能の作成の基本のスクリーンショット。]
5. [ **エンドポイントの構成** ] タブで、カスタム拡張機能エンドポイントのターゲット URL、タイムアウト、および最大再試行回数を入力します。

    [Image: カスタム拡張機能エンドポイント構成の作成のスクリーンショット。]
6. [ **API 認証** ] タブで、カスタム拡張機能エンドポイントをセキュリティで保護するアプリケーション登録を選択します。

    [Image: カスタム拡張機能 API 認証の作成のスクリーンショット。]
7. [ **確認と作成** ] タブで、構成を確認し、[ **作成**] を選択します。

    [Image: 基本、エンドポイント、API 認証の詳細など、カスタム拡張機能の構成の概要を示す [確認と作成] タブのスクリーンショット。]

カスタム拡張機能が作成されると、 **カスタム拡張機能** の一覧に表示されます。

[Image: 作成後に Privileged Identity Management で利用可能なカスタム拡張機能の一覧。]

### カスタム拡張機能を PIM ロール設定にリンクする

カスタム拡張機能を導入したら、アクティブ化時に PIM がその拡張機能を呼び出す必要がある各ロールのロール設定に関連付けます。 グループのロール設定を更新するには、そのグループの所有者ロールが必要です。

#### グループロール設定の PIM へのリンク

カスタム拡張機能をグループ ロールにリンクするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Identity governance**&gt;**Privileged Identity Management** に移動します。
3. **[グループ] を選択します**。
4. ターゲット グループを選択し、ロール管理エクスペリエンスを開きます。
5. [ **設定] を** 選択して、グループで使用可能なロールを表示します。

    [Image: グループ ロールの設定のスクリーンショット。]
6. **所有者**や**メンバー**など、構成するロールを選択し、ロールの設定を開きます。
7. **[編集]** を選択します。
8. アクティブ化するには、[ **事前承認のカスタム拡張機能を要求する**] チェック ボックスをオンにします。
9. このロールにリンクするカスタム拡張機能を選択します。

    [Image: カスタム拡張機能を選択するときのロール設定のスクリーンショット。]
10. [ **保存] を** 選択して、更新されたロール設定を適用します。

保存すると、ロール設定の詳細ページで、カスタム拡張機能がリンクされていることを確認します。

[Image: カスタム拡張機能のロール設定の詳細のスクリーンショット。]

同様のプロセスに従って、カスタム拡張機能をAzureリソース ロールとMicrosoft Entra ディレクトリ ロールにリンクできます。

### ロールのアクティブ化フローを確認する

構成が完了したら、ロールをアクティブ化し、PIM が API に要求を送信し、決定を強制することを確認することで、カスタム拡張機能が期待どおりに動作することを確認します。

1. ロールの対象となるユーザーとして [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **Privileged Identity Management** に移動し、カスタム拡張機能が構成されているロールをアクティブにします。
3. 必要な理由とチケット情報を指定します。

    [Image: チケット情報を使用してロールをアクティブ化するスクリーンショット。]
4. アクティブ化要求を送信します。
5. API エンドポイントが PIM から POST 要求を受信することを確認します。
6. PIM がカスタム拡張機能によって返される決定を強制していることを確認します。

    - **承認済み**: アクティブ化は、他の承認手順を含め、通常の PIM ワークフローを通じて続行されます。
    - **AutoApproved**: アクティブ化は、手動の PIM 承認を必要とせずに続行されます。
    - **拒否**: アクティブ化がブロックされ、API 応答からの理由メッセージが PIM UI に表示されます。

    次の例は、チケット情報を検証したカスタム拡張機能によって拒否されたアクティブ化を示しています。

    [Image: カスタム拡張機能で拒否されたアクティブ化のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/reconciliation-overview"} -->
## 調整の概要 - Microsoft Entra ID ガバナンス - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/reconciliation-overview
- Service: entra-id-governance / entitlement-management
- Article date: 2026-08-31
- Summary: Microsoft Entra ID ガバナンスの調整によって、アクセスドリフトの可視性、適用、および修復がどのように提供されるかについて説明します。

調整は、アプリケーション、グループ、およびディレクトリへのアクセスをガバナンス ポリシーに合わせて維持するのに役立つ ID ガバナンス機能です。

Microsoft Entra ID ガバナンスでは、[アクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-define)は、アクセス パッケージとエンタイトルメント管理の割り当てを通じてリソースにアクセスする必要があるユーザーを定義します。 管理者がユーザーをグループに直接割り当てたり、アプリケーション所有者がローカル アカウントを作成したり、スクリプトでディレクトリのアクセス許可を変更したりすると、これらのポリシーの外部で変更が発生する可能性があります。 照合により、管理者はアクセスの整合性が取れていない箇所を把握し、強制適用が可能な場合はドリフトを防止し、アクセスを再びガバナンス下に置く方法を判断できます。

### アクセスのドリフト

アクセス ドリフトは、管理されたアクセス ポリシーと実際のリソース割り当ての間の相違です。 時間の経過と同時に、管理されていない変更によって、ユーザーが不要になったアクセス権を保持したり、ポリシー承認なしでアクセス許可を持ったりするギャップが生じる可能性があります。

通常、アクセス ドリフトは次のパターンによって発生します。

- **アンマネージド リソースの割り当て**: ユーザーは、関連付けられたアクセス パッケージの割り当てまたは承認プロセスなしで、グループまたはエンタープライズ アプリケーションに直接追加されます。
- **リソースの割り当てがない**: ユーザーはアクティブなアクセス パッケージの割り当てを持っていますが、対応するグループ メンバーシップまたはアプリケーションの割り当てが削除されたか、適用に失敗しました。
- **孤立アカウントとローカル アカウント**: ターゲット アプリケーションまたはディレクトリにアカウントが存在し、Microsoft Entra IDに対応する ID または割り当てがありません。

未対応の誤差により、ガバナンス制御が損なわれ、コンプライアンス監査が複雑になり、組織のリソース全体のセキュリティ リスクが高まる可能性があります。

### 調整機能

調整機能を使用すると、2 つの質問に答えるのに役立ちます。アクセスが調整外である場所と、アクセスをアラインメントに戻す方法です。

- **可視性** 機能は、ガバナンス ポリシーと一致しないアクセスを検出して監視するのに役立ちます。
- **強制と修復** の機能は、未承認の変更を防いだり、発生後の誤差を修正したりして、アクセスを調整し続けるのに役立ちます。

これらのカテゴリは常にシーケンシャルとは限りません。適切なアプローチは、組織やリソースによって異なる場合があります。 組織では、可視性を使用して、多くのリソース間の既存のドリフトを把握し、適用を使用して機密性の高いリソースのドリフトを防ぎ、ソースでドリフトを防ぐことができない場合は修復を使用できます。

### 可視化機能

可視性機能は、実際のアクセスが管理されたアクセスと一致しない場所を示します。 可視性機能を使用して、アンマネージド アクセス、不足している割り当て、ガバナンス ポリシーとリソースの状態間の誤差を見つけます。

#### ターゲット アプリケーションでのアカウント検出

アカウント検出では、ターゲット アプリケーションが評価され、Microsoft Entra IDプロビジョニングの前または外部で作成された既存のユーザー アカウントが検索されます。 検出プロセスはターゲット システムをスキャンし、アカウントを次の 3 つのカテゴリに分類します。

- **ローカル アカウント**: Microsoft Entra IDで一致する ID を持たないターゲット アプリケーション内のアカウント。
- **未割り当てユーザー**: 現在アプリケーションに割り当てられていないMicrosoft Entra ID ユーザーと一致するアカウント。
- **割り当てられたユーザー**: プロビジョニング サービスによって割り当てられ、管理されているMicrosoft Entra ID ユーザーと一致するアカウント。

検出されたアカウントを分類すると、管理者は管理されていないアカウントをガバナンス ワークフローにオンボードしたり、古いアクセスを削除したりできます。 アカウント検出の構成の詳細については、アカウント検出を使用 [したターゲット アプリケーションでの ID の検出に関する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)ページを参照してください。

#### アクセス パッケージのドリフトの検出

アクセス パッケージの誤差検出では、ディレクトリ オブジェクトの現在の状態に対してエンタイトルメント管理ポリシーが評価されます。 このレポートでは、次のようなグループとエンタープライズ アプリケーション間の不一致が示されます。

- 対応するアクセス パッケージの割り当てがないメンバーを含む Microsoft Entra グループ
- 対応するアクセス パッケージの割り当てがないアプリケーション ロールが割り当てられているアプリケーション。
- 想定されるグループ メンバーシップまたはアプリケーションの割り当てが見つからないアクティブなアクセス パッケージの割り当て。

管理者は、Microsoft Entra 管理センターの誤差の概要を確認したり、テナント全体の分析のために詳細なレポートをエクスポートしたりできます。 誤差レポートを確認する方法については、「 [エンタイトルメント管理でレポートとログを表示](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-reports#view-access-package-drift)する」を参照してください。

### 強制と修復の機能

適用と修復の機能は、実際のアクセスをガバナンス ポリシーに合わせて維持するのに役立ちます。 これらの機能は、次の 2 つの方法で機能します。

- ドリフトが発生する前に、**予防的な実施**によって未承認の変更がブロックまたは監査されます。
- **修正修復** は、直接防止が利用できないシステムでドリフトが検出された後に、管理されたアクセスを復元するのに役立ちます。

#### Active Directory グループの強制適用

Active Directory グループの適用により、Microsoft Entra Cloud Sync を介して同期されたオンプレミス グループの予防的調整が提供されます。変更が発生した後にドリフトを検出する代わりに、グループの強制によって、ドメイン コントローラー レベルでの書き込み操作が制限されます。

グループの強制適用が設定されている場合:

- Active Directory ドメイン コントローラーは、ライトウェイト ディレクトリ アクセス プロトコル (LDAP) の書き込み操作をポリシー セキュリティ識別子 (SID) に対して検証します。
- マークされたグループに対する未承認の変更は、強制モードでブロックされるか、監査モードでログに記録されます。
- 指定されたグループへの変更は、Microsoft Entra プロビジョニング サービスから行う必要があります。

直接変更を防止することで、保護されたオンプレミス グループのずれを防止できます。 構成要件については、「[Microsoft Entra Cloud Sync でActive Directoryグループの適用を構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-ad-group-enforcement)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/reprocess-workflow"} -->
## ライフサイクル ワークフローを使用してワークフロー実行を再処理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/reprocess-workflow
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: この記事では、ライフサイクル ワークフローを使用してワークフロー実行を再処理する方法について説明します

ワークフローの再処理は、ライフサイクル ワークフローを使用して作成されたワークフローを再度実行して、ワークフローが意図したとおりに動作することを保証する機能です。 これは、何らかの理由で失敗した実行を処理する場合に便利です。 この記事では、Microsoft Entra 管理センターを使用してワークフローを再処理する手順について説明します。これにより、ユーザーまたは特定の実行のワークフロー実行をすばやく効率的に管理できます。

### Microsoft Entra 管理センターを使用してワークフローを再処理する

Microsoft Entra 管理センターを使用してワークフローを再処理するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**のワークフローを参照してください**。
3. ワークフロー一覧画面で、再処理するワークフローを選択します。
4. ワークフローの概要ページで、[ **ワークフロー履歴**] を選択します。
5. ワークフロー履歴画面で、[ **ユーザー** ] タブが自動的に開き、ワークフローによって処理されたユーザーの一覧が表示されます。
6. ユーザーのワークフローを再処理するには、ワークフローを再処理するユーザーを選択し、[ **再処理**] を選択します。 [Image: ワークフローの再処理のスクリーンショット。]

    注

    一度に最大 10 人のユーザーを再処理できます。
7. 実行に基づいてワークフローを再処理する場合は、[ **実行** ] タブを選択します。
8. [ **実行** ] タブには、ワークフロー実行の完全な一覧が表示されます。 再処理する実行を選択し、[ **再処理**] を選択します。

    注

    一度に再処理できる実行は 1 つだけです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/review-recommendations-access-reviews"} -->
## アクセス レビューのためのレビューの推奨事項 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/review-recommendations-access-reviews
- Service: entra-id-governance / access-reviews
- Article date: 2026-03-12
- Summary: Microsoft Entra アクセス レビューで、レビューの推奨事項を使ってグループ メンバーのアクセスをレビューする方法について説明します。

ユーザーのアクセスをレビューし、アクセス レビューを実行する意思決定者は、システム ベースの推奨事項を使用して、それらのアクセスを続行するか、またはそのリソースへのアクセスを拒否するかどうかの決定に役立てることができます。 レビューの推奨事項を使用する方法の詳細については、[意思決定ヘルパーの有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review#next-settings)に関するページを参照してください。

### 前提条件

ユーザーからグループへの所属に関する推奨事項を使用してレビューを作成するには、Microsoft Entra ID ガバナンス ライセンスが必要です。

詳細については、「[License requirements ライセンスの要件](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview#license-requirements)」を参照してください。

### 非アクティブなユーザーに関する推奨事項

ユーザーが過去 30 日以内にテナントにサインインしていない場合、そのユーザーは "非アクティブ" と見なされます。 この動作は、アプリケーションの割り当てのレビュー用に調整されます。これにより、テナント全体ではなく、アプリ内の各ユーザーの最後のアクティビティが確認されます。 アクセス レビューで非アクティブなユーザーの推奨事項が有効になっている場合、レビューが開始されると各ユーザーの最後のサインイン日が評価され、30 日以内にサインインしていないユーザーには拒否の推奨アクションが与えられます。 さらに、これらの意思決定ヘルパーが有効になっている場合、レビュー担当者は、レビュー対象のすべてのユーザーの最後のサインイン日付を確認できます。 このサインイン日付と結果として得られるレコメンデーションはレビューが開始されたときに決定され、レビューの進行中は更新されません。

### ユーザー対グループの所属

より簡単で正確なレビュー エクスペリエンスにより、IT 管理者とレビュー担当者は情報に基づいてより多くの意思決定を行うことができます。 この Machine Learning ベースのレコメンデーションを利用すると、アクセス レビューを自動化するための扉が開き、インテリジェントな自動化を実現でき、アクセス権の構成証明の労力が軽減されます。

ユーザーからグループへの所属は、組織のレポート構造で同様の特性を共有する 2 人以上のユーザーとして定義されます。

この推奨では、組織の報告構造の類似性に基づき、グループ内の他のユーザーとのユーザーの関係を検出します。 推奨事項は、グループ内の残りのユーザーからのユーザーの平均距離を計算することによって計算されるスコアリング メカニズムに依存します。 その組織図に基づいて、他のすべてのグループ メンバーから離れているユーザーは、グループ内で "所属度が低い" と見なされます。

アクセス レビューの作成者がこの意思決定ヘルパーを有効にしている場合、レビュー担当者はグループ アクセス レビューのユーザー対グループの所属に関するレコメンデーションを受信できます。

注

この機能はディレクトリ内のユーザーに対してのみ使用できます。 ユーザーにはマネージャー属性が必要であり、ユーザーからグループへの所属が機能するための組織階層の一部である必要があります。

ユーザー数が 600 を超えるグループはサポートされていません。

次の図には、化粧品会社における組織のレポート構造の例が含まれています。

[Image: アクセス レビューの組織例グラフのスクリーンショット。]

この例の画像のレポート構造に基づいて、グループ内の他のユーザーから統計的に有意な距離にあるユーザーは、グループ アクセス レビューのレビュー担当者によってユーザーからグループへの所属の推奨事項が選択された場合、システムによって "*拒否*" の推奨事項を受け取ります。

たとえば、パーソナルケア部門で働いている Phil は、すべて化粧品部門で働いている Debby、Irwin、Emily と共にあるグループにいます。 このグループは*フレッシュスキン*と呼ばれます。 "フレッシュスキン" グループのアクセスレビューが実行された場合、報告構造や他のメンバーからの距離に基づいて、Phil は所属の度合いが低いとされます。 システムは、グループ アクセス レビューで **[拒否]** の推奨事項を作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/review-your-access"} -->
## アクセス レビューでグループおよびアプリに対する自分のアクセスをレビューする - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/review-your-access
- Service: entra-id-governance / access-reviews
- Article date: 2025-06-18
- Summary: グループまたはアプリケーションに対する自分のアクセスをアクセス レビューでレビューする方法について説明します。

企業でアクセス レビューという機能を使用して、Microsoft Entra ID およびその他の Microsoft Online Services 内のグループまたはアプリケーションへのアクセスを管理する方法が、Microsoft Entra ID により簡素化されます。

この記事では、グループまたはアプリケーションに対する自分のアクセスをレビューする方法を説明します。

### マイ アプリを使用して自分のアクセスを確認する

アクセス レビューを実行する最初の手順は、アクセス レビューを見つけて開くことです。

重要

メールの受信に遅延が発生する可能性があり、場合によっては最大 24 時間かかる場合があります。 すべてのメールを確実に受信するため、azure-noreply@microsoft.com を信頼できる宛先のリストに追加してください。

1. アクセス レビューを実行するように求める Microsoft からのメールを見つけます。 グループに対する自分のアクセスをレビューするためのメールの例を次に示します。

    [Image: グループに対する自分のアクセスをレビューするための Microsoft からのメールの例]
2. **[アクセスのレビュー]** リンクを選択して、アクセス レビューを開きます。

メールが届いていない場合は、次の手順に従って、保留中のアクセス レビューを見つけることができます。

1. https://myapps.microsoft.com から、マイ アプリ ポータルにサインインします。

    [Image: アクセス許可があるアプリの一覧を示すマイ アプリ ポータル]
2. ページの右上隅で、ユーザー記号を選択すると、自分の名前と既定の組織が表示されます。 複数の組織が一覧表示されている場合は、アクセス レビューの依頼元の組織を選択してください。
3. ページの右側で、**[アクセス レビュー]** タイルを選択して、保留中のアクセス レビューの一覧を確認します。

    このタイルが表示されない場合は、その組織に対して実施するアクセス レビューがないので、現時点で必要な対応はありません。

    [Image: アプリとグループについての保留中のアクセス レビューの一覧]
4. 実行するアクセス レビューの **[レビューの開始]** リンクを選びます。

#### アクセス レビューを実行する

アクセス レビューを開いたら、自分のアクセスを確認できます。

1. 自分のアクセスをレビューし、アクセスが引き続き必要かどうかを判断します。

    他のユーザーのアクセスをレビューするように要求した場合は、ページの外観が異なります。 詳細については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)に関するページを参照してください。

    [Image: 開いているアクセス レビューのスクリーンショット。グループへのアクセスが引き続き必要かどうか確認しています。]
2. **[はい]** を選択してご自分のアクセスを維持するか、 **[いいえ]** を選択してご自分のアクセスを削除します。
3. **[はい]** を選択する場合は、**[理由]** ボックスに正当性を指定する必要がある場合があります。

    [Image: グループへのアクセスが引き続き必要かどうか確認するアクセス レビューの完了後のスクリーンショット。[はい] が選択されています。]
4. **[Submit](https://learn.microsoft.com/ja-jp/entra/id-governance/送信)** をクリックします。

    選択内容が送信され、マイ アプリ ポータルに戻ります。

    ご自分の応答内容を変更する場合は、アクセス レビュー ページをもう一度開き、ご自分の応答内容を更新します。 応答は、アクセス レビューが終了するまではいつでも変更できます。

    注

    アクセスが不要になったことを指定した場合、すぐには削除されません。 レビューが終了したとき、または管理者がレビューを停止すると、削除されます。

### マイ アクセスを使用して自分のアクセス権をレビューする (新機能)

マイ アクセスの更新されたユーザー インターフェイスでの新しいエクスペリエンスは、以下のいくつかの方法でお試しいただけます。

#### マイ アプリ ポータル

1. https://myapps.microsoft.com から、マイ アプリ ポータルにサインインします。

    [Image: アクセス許可があるアプリの一覧を示すマイ アプリ ポータル]
2. **[アクセス レビュー]** タイルを選んで、保留中のアクセス レビューの一覧を表示します。

    注

    **[アクセス レビュー]** タイルが表示されない場合は、その組織に対して実施するアクセス レビューがないので、現時点で必要な対応はありません。
3. ページ上部のバナーにある **[試してみる]** を選択して新しいマイ アクセス エクスペリエンスに移動します。

    [Image: プレビュー期間中に表示される、新しいエクスペリエンスが利用可能であることを示すバナーと、アプリとグループに対して保留中になっているアクセス レビューの一覧]
4. 次に「**アクセス レビューを実行する**」セクションに移ります。

#### Email

重要

メールの受信に遅延が発生する可能性があり、場合によっては最大 24 時間かかる場合があります。 すべてのメールを確実に受信するため、azure-noreply@microsoft.com を信頼できる宛先のリストに追加してください。

1. アクセス レビューを実行するように求める Microsoft からのメールを見つけます。 電子メール メッセージの例は次のとおりです。

[Image: グループに対するアクセスをレビューするための Microsoft からのメールの例]

1. **[アクセスのレビュー]** リンクを選択して、アクセス レビューを開きます。
2. 次に「**アクセス レビューを実行する**」セクションに移ります。

注

レビューの開始をクリックすると **[マイ アプリ]** に移動する場合は、「**マイ アプリ ポータル**」というタイトルのセクションに記載されている手順のようにします。

#### マイ アクセスから直接

お使いのブラウザーを使用してマイ アクセスを開くと、保留中のご自分のアクセス レビューを表示することもできます。

1. https://myaccess.microsoft.com/ からマイ アクセスにサインインします。
2. 左側のバーのメニューから **[アクセス レビュー]** を選択すると、割り当てられている保留中のアクセス レビューの一覧が表示されます。

    [Image: メニューの [アクセス レビュー]]

#### アクセス レビューを実行する

1. [グループとアプリ] の下に次が表示されます。

    - **[名前]** : アクセス レビューの名前。
    - **[期限]** : レビューの期限。 この日付を過ぎると、拒否されたユーザーはレビュー対象のグループまたはアプリから削除される可能性があります。
    - **[リソース]** : レビュー中のリソースの名前。
    - **[進行状況]** : このアクセス レビューに関わるユーザーの合計数に対する、レビューされたユーザー数。
2. アクセス レビューの名前を選択して開始します。

    [Image: アプリとグループについての保留中のアクセス レビューの一覧]
3. 自分のアクセスをレビューし、アクセスが引き続き必要かどうかを判断します。

    他のユーザーのアクセスをレビューするように要求した場合は、ページの外観が異なります。 詳細については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)に関するページを参照してください。

    [Image: グループへのアクセスが引き続き必要かどうかを確認する、開かれたアクセス レビュー]
4. **[はい]** を選択してご自分のアクセスを維持するか、 **[いいえ]** を選択してご自分のアクセスを削除します。
5. **[はい]** を選択する場合は、**[理由]** ボックスに正当性を指定する必要がある場合があります。

    [Image: グループへのアクセスが引き続き必要かどうかを確認する、完了したアクセス レビュー]
6. **[Submit](https://learn.microsoft.com/ja-jp/entra/id-governance/送信)** をクリックします。

    選択内容が送信され、マイ アクセス ページに戻ります。

    ご自分の応答内容を変更する場合は、アクセス レビュー ページをもう一度開き、ご自分の応答内容を更新します。 応答は、アクセス レビューが終了するまではいつでも変更できます。

    注

    アクセスが不要になったことを指定した場合、すぐには削除されません。 レビューが終了したとき、または管理者がレビューを停止すると、削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/sap"} -->
## SAP アプリケーションへのアクセスを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/sap
- Service: entra-id-governance
- Article date: 2024-01-29
- Summary: SAP SuccessFactors の ID を Microsoft Entra ID に取り込み、SAP ERP Central Component (ECC)、SAP S/4HANA、その他の SAP アプリケーションへのアクセスをプロビジョニングする方法を学習します。

SAP ソフトウェアとサービスは、組織にとって人事や ERP などの重要な機能を実行する可能性があります。 同時に、組織はさまざまな Azure サービス、Microsoft 365、Microsoft Entra ID ガバナンスでアプリケーションへのアクセスを管理することについて、Microsoft に依存しています。 この記事では、Identity Governance を使用して SAP アプリケーション全体の ID を管理する方法について説明します。

[Image: SAP 統合の図。]

### SAP Identity Management からの移行

これまで SAP ID 管理 (IDM) を使ってきた場合は、ID 管理シナリオを SAP IDM から Microsoft Entra に移行できます。 詳しくは、「[ID 管理シナリオの SAP IDM から Microsoft Entra への移行](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/migrate-from-sap-idm)」をご覧ください。

### HR から ID を Microsoft Entra ID に取り込む

チュートリアル「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」では、SAP SuccessFactors など、組織内の worker の一覧に対する権限を持つソースと Microsoft Entra を接続する方法について説明しています。 また、Microsoft Entra を使用してこれらの worker の ID を設定する方法についても説明しています。 その後、Microsoft Entra を使用して、SAP ECC や SAP S/4HANA などの 1 つ以上の SAP アプリケーションにサインインするためのアクセス権を worker に提供する方法について説明しています。

#### SuccessFactors

SAP SuccessFactors を使用しているお客様は、ネイティブ コネクタを使用して、[SuccessFactors から Microsoft Entra ID に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)、または [SuccessFactors からオンプレミスの Active Directory に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) ID を簡単に取り込むことができます。 このコネクタは次のシナリオをサポートします。

- **新しい従業員の雇用**: 新しい従業員が SuccessFactors に追加されると、ユーザー アカウントは Microsoft Entra ID (および必要に応じて Microsoft 365 および [Microsoft Entra ID がサポートするその他サービスとしてのソフトウェア (SaaS) アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)) で自動的に作成されます。 このプロセスには、SuccessFactors へのメール アドレスの書き戻しが含まれます。
- **従業員の属性とプロファイルの更新**: SuccessFactors で従業員レコード (名前、役職、マネージャーなど) が更新されると、Microsoft Entra ID (および必要に応じて Microsoft 365 や Microsoft Entra ID でサポートされているその他 SaaS アプリケーション) で、その従業員のユーザー アカウントが自動的に更新されます。
- **従業員の退職**: SuccessFactors で従業員が退職状態になると、Microsoft Entra ID (および必要に応じて Microsoft 365 や Microsoft Entra ID によってサポートされているその他 SaaS アプリケーション) で、その従業員のユーザー アカウントが自動的に無効になります。
- **従業員の再雇用**: SuccessFactors で従業員が再雇用されると、Microsoft Entra ID および必要に応じて Microsoft 365 や Microsoft Entra ID によってサポートされているその他 SaaS アプリケーションに対して、その従業員の以前のアカウントを (設定に応じて) 自動的に再アクティブ化または再プロビジョニングできます。

[Microsoft Entra ID から SAP SuccessFactors に書き戻す](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)こともできます。

#### SAP HCM

SAP Human Capital Management (HCM) を引き続き使用している場合でも、Microsoft Entra ID に ID を取り込むことができます。 SAP Integration Suite を使用すると、SAP HCM と SAP SuccessFactors の間で雇用者のリストを同期できます。 そこから、前述のネイティブ プロビジョニング統合を使用して、ID を Microsoft Entra ID に直接取り込んだり、Active Directory Domain Services にプロビジョニングしたりできます。

[Image: SAP HR 統合の図。]

SAP HCM を持ち、SuccessFactors がない組織のその他の統合オプションについては、「 [SAP 人事管理 (HCM) から Microsoft Entra ID へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hcm-microsoft-entra-identity-provisioning)」に記載されています。

### SAP アプリケーションへのアクセスを提供する

SAP アプリケーションへのアクセスを管理できるようにするネイティブ プロビジョニング統合に加えて、Microsoft Entra ID ではそれらのアプリケーションとの充実した統合セットがサポートされています。

#### SSO を有効にする

SAP アプリケーションのプロビジョニングを設定すると同時に、それらのアプリケーションとの SSO を有効にできます。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーおよび認証機関として機能することができます。 Microsoft Entra ID は、[SAML または OAuth を使用して SAP NetWeaver](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-netweaver-tutorial) と統合できます。 SAP SaaS と最新のアプリについては、[SAP Cloud Identity Services を介して SAP アプリケーションの企業 ID プロバイダーとして Microsoft Entra ID を構成する方法を確認してください](https://help.sap.com/docs/IDENTITY_AUTHENTICATION/6d6d63354d1242d185ab4830fc04feb1/058c7b14209f4f2d8de039da4330a1c1.html)。

Microsoft Entra ID からシングル サインオンを構成する方法の詳細については、次のドキュメントとチュートリアルを参照してください。

- [SAP Cloud Identity Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)
- [SAP SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/successfactors-tutorial)
- [SAP Analytics Cloud](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sapboc-tutorial)
- [SAP Fiori](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-fiori-tutorial)
- [SAP Ariba](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ariba-tutorial)
- [SAP Concur Travel and Expense](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/concur-travel-and-expense-tutorial)
- [SAP Business Technology Platform](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-tutorial)
- [SAP Business ByDesign](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sapbusinessbydesign-tutorial)
- [SAP HANA](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saphana-tutorial)
- [SAP Cloud for Customer](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-customer-cloud-tutorial)
- [SAP Fieldglass](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fieldglass-tutorial)

次のブログ投稿と SAP リソースも参照してください。

- SAP GUI MFA と Microsoft Entra の [統合と SAP Secure Login Service](https://community.sap.com/t5/technology-blogs-by-members/sap-gui-mfa-with-microsoft-entra-part-i-integration-with-sap-secure-login/ba-p/13605383) の [統合、および Microsoft Entra Private Access との統合](https://community.sap.com/t5/technology-blogs-by-members/sap-gui-mfa-with-microsoft-entra-part-ii-integration-with-microsoft-entra/ba-p/13691141)
- [SAP BTP へのアクセスの管理](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276)
- [パブリックおよび内部 SAP URL に対する SAML シングル サインオンの Azure Application Gateway セットアップ](https://blogs.sap.com/2020/12/10/sap-on-azure-single-sign-on-configuration-using-saml-and-azure-active-directory-for-public-and-internal-urls/)
- [Microsoft Entra Domain Services と Kerberos を使用したシングル サインオン](https://blogs.sap.com/2018/08/03/your-sap-on-azure-part-8-single-sign-on-using-azure-ad-domain-services/)

#### 最新の SAP アプリケーションに ID をプロビジョニングする

ユーザーが Microsoft Entra ID に追加されたら、アクセスする必要があるさまざまな SaaS およびオンプレミスの SAP アプリケーションにアカウントをプロビジョニングできます。 SAP Cloud Identity Services がサポートする SAP アプリケーションの場合は、Microsoft Entra ID の [SAP Cloud Identity Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial) エンタープライズ アプリケーションを使用して、Microsoft Entra から SAP Cloud Identity Services にユーザーとグループをプロビジョニングできます。 すべての ID を SAP Cloud Identity Services に取り込んだ後は、SAP Cloud Identity Services - Identity Provisioning を使用して、必要に応じてそこから SAP アプリケーションにアカウントをプロビジョニングできます。

[Image: SAP アプリケーション、SAP Cloud Identity Services、Microsoft Entra の間の SSO とプロビジョニング フローのアーキテクチャの図。]

別のオプションとして、 [SAP Cloud Identity Services - Identity Provisioning 統合を](https://help.sap.com/docs/IDENTITY_PROVISIONING/f48e822d6d484fa5ade7dda78b64d9f5/f2b2df8a273642a1bf801e99ecc4a043.html) 使用して、Microsoft Entra ID から [SAP Cloud Identity Services 統合アプリケーション](https://help.sap.com/docs/IDENTITY_PROVISIONING/f48e822d6d484fa5ade7dda78b64d9f5/ab3f641552464c79b94d10b9205fd721.html)に ID を直接エクスポートできます。 SAP Cloud Identity Services - Identity Provisioning を使用してユーザーをこれらのアプリケーションに取り込む場合、これらのアプリケーションのすべてのプロビジョニング構成は SAP で直接管理されます。 Microsoft Entra ID のエンタープライズ アプリケーションを引き続き使用して SSO を管理し、[Microsoft Entra ID をコーポレート ID プロバイダーとして](https://help.sap.com/docs/IDENTITY_AUTHENTICATION/6d6d63354d1242d185ab4830fc04feb1/058c7b14209f4f2d8de039da4330a1c1.html)使用することができます。

#### オンプレミスの SAP システムに ID をプロビジョニングする

Microsoft Entra ID にユーザーを作成したら、Microsoft Entra ID から SAP Cloud Identity Services または SAP ECC にそれらのユーザーをプロビジョニングして、ユーザーが SAP アプリケーションにサインインできるようにします。 [`SAP S/4HANA On-Premise`](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-sap-s-4hana-on-premise) を持っている場合なら、Microsoft Entra ID から SAP Cloud Identity Directory にユーザーをプロビジョニングします。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から生成されたユーザーを、SAP クラウド コネクタを介して SAP S/4HANA On-Premise のダウンストリーム SAP アプリケーションにプロビジョニングします。

SAP R/3 および SAP ERP Central Component (SAP ECC) などのアプリケーションから SAP S/4HANA にまだ移行していない場合は、引き続き Microsoft Entra プロビジョニング サービスを使用してユーザー アカウントをプロビジョニングできます。 SAP R/3 および SAP ECC 内では、ユーザーの作成、更新、削除に必要な Business Application Programming Interfaces (BAPI) を公開します。 その後、軽量の Microsoft Entra プロビジョニング エージェントと [Web サービス コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector) を使用して、 [SAP ECC などのアプリにユーザーをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sap-connector-configure)できます。

[Image: SAP ECC と SAP S/4HANA オンプレミスへのプロビジョニングの図。]

また、Microsoft Entra ID を使用して、Active Directory や、SAP Cloud Identity Services でプロビジョニングがサポートされていない他のオンプレミス システムに worker をプロビジョニングすることもできます。

#### SAP IAG 経由でアクセスを割り当てる

SAP Cloud Identity Services を通じてプロビジョニングされたグループ メンバーシップを使用してユーザーをロールに割り当てるだけでなく、Microsoft Entra エンタイトルメント管理を SAP Cloud Identity Access Governance (IAG) と統合することもできます。 この統合により、SAP IAG ビジネス ロールをエンタイトルメント管理カタログのリソースとして追加できるため、Microsoft Entra アクセス パッケージを介して SAP アプリケーションへのアクセス権をユーザーに付与できます。 その後、Microsoft Entra の承認に基づいて、SAP IAG およびダウンストリーム SAP アプリケーションでのロールの割り当てを自動化できます。

詳細については、 [Microsoft Entra SAP IAG 統合 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-sap-integration) を参照してください。

### カスタム ワークフローをトリガーする

新しい従業員が組織で採用されると、ユーザー プロビジョニング以外の追加タスクのために、SAP オンプレミス システム内でワークフローをトリガーする必要がある場合があります。 [Microsoft Entra ライフサイクル ワークフロー Logic Apps の拡張性](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)を使用するか、[Microsoft Entra エンタイトルメント管理 Logic Apps の拡張性](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration)と [Azure Logic Apps の SAP コネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-using-sap-connector)を使用すると、新しい従業員の雇用時に SAP でカスタム アクションをトリガーできます。

### 職務の分離をチェックする

Microsoft Entra の[エンタイトルメント管理](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/ensure-compliance-using-separation-of-duties-checks-in-access/ba-p/2466939)で、職務の分離チェックを使用すると、お客様が過剰なアクセス権を取得できないようにすることができます。

- 管理者とアクセス マネージャーは、既に他のアクセス パッケージに割り当てられているユーザー、または要求されたアクセスと互換性のない他のグループのメンバーであるユーザーが、追加のアクセス パッケージを要求できないようにします。
- SAP アプリで重要な規制要件を扱う企業は、単一で整合性の確保されたビューをアクセス制御に導入できます。 その後、Microsoft Entra 統合アプリケーションと共に、財務アプリケーション、その他ビジネス クリティカルなアプリケーションの全体で、職務の分離チェックを適用できます。
- [SAP アクセス ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-sap-integration)、[Pathlock](https://pathlock.com/applications/microsoft-entra-id-governance/) や他のパートナー製品への Microsoft Entra 統合により、お客様は Microsoft Entra ID Governance のアクセス パッケージを使用して、それらの製品で実行されている追加のリスクおよびきめ細かい職務の分離チェックを活用できます。

### その他のガイダンス

Microsoft Entra ID との SAP 統合の詳細については、次のドキュメントを参照してください。

- [SAP Cloud ID サービスと Microsoft Entra ID を使用したアクセスのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/fundamentals/scenario-azure-first-sap-identity-integration)
- [SAP ワークロードのセキュリティ - Microsoft Azure Well-Architected Framework](https://learn.microsoft.com/ja-jp/azure/architecture/framework/sap/security)
- [SAP アプリケーションを使用して Microsoft Entra をデプロイするためのサービスと統合パートナー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/migrate-from-sap-idm#services-and-integration-partners-for-deploying-microsoft-entra-with-sap-applications)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/automate-identity-lifecycle"} -->
## Azure AD Identity Governance を使用して ID ライフサイクル管理を自動化する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/automate-identity-lifecycle
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: Azure AD Identity Governance の ID ライフサイクル管理の概要について説明します。

以下のドキュメントでは、Microsoft Entra ID ガバナンスを使用して [ID ライフサイクル プロセスを自動化](https://youtu.be/NxSu3JEsxmY?si=PELWAnpdI4iAMfki)する方法の概要を説明します。

### Active Directory からの自動インバウンド プロビジョニング

Active Directory から Microsoft Entra ID へのプロビジョニングは、次のいずれかを使って、いくつかの異なる方法で実現できます。

- [Microsoft Entra クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)
- [Microsoft Entra Connect 同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2)
- これらの HR システムで新しい ID が作成されたときにプロビジョニングをトリガーする [Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016)。

### 組織の HR ソースからの自動インバウンド プロビジョニング

人事主導のプロビジョニングは、人事システムに基づいてデジタル ID を作成するプロセスです。 人事システムが、新しく作成されるデジタル ID の SOA (Start of Authority) として、多くの場合、さまざまなプロビジョニング プロセスの開始点となります。 これらの HR システムは、オンプレミスでもクラウド ベースでもかまいません。

従業員、ベンダー、臨時労働者の ID ライフサイクルを管理するために、[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)は、クラウドベースの人事 (HR) アプリケーションとの統合を提供しています。

Microsoft のオンプレミス HR プロビジョニング ソリューションでは [Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016) が使われており、これらの HR システムで新しい ID が作成されるとプロビジョニングがトリガーされます。 MIM を使用すると、オンプレミスの人事システムから Active Directory または Microsoft Entra ID にユーザーをプロビジョニングすることができます。 [ディレクトリ間のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-inter-directory-provisioning)を使用することで、Active Directory に既に存在するユーザーを Microsoft Entra ID に自動的に作成して保持できます。

#### 有効な人事シナリオ

Microsoft Entra ユーザー プロビジョニング サービスを使用すると、次に示す人事ベースの ID ライフサイクル管理シナリオを自動化できます。

- **新しい従業員の採用:** クラウド人事アプリに従業員を追加すると、Active Directory と Microsoft Entra ID にユーザーが自動的に作成されます。 ユーザー アカウントの追加には、メール アドレスとユーザー名の属性をクラウド人事アプリに書き戻すオプションが含まれています。
- **従業員の属性とプロファイルの更新:** クラウド人事アプリで名前、役職、上司などの従業員レコードが更新されると、そのユーザー アカウントが Active Directory と Microsoft Entra ID で自動的に更新されます。
- **従業員の退職:** クラウド人事アプリで従業員が退職すると、そのユーザー アカウントが Active Directory および Microsoft Entra ID で自動的に無効になります。
- **従業員の再雇用:** クラウド人事アプリで従業員が再雇用されると、その古いアカウントが自動的に再アクティブ化されるか、Active Directory および Microsoft Entra ID に再プロビジョニングされます。

詳しくは、[HR 主導のプロビジョニングの概要](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)および[Microsoft Entra のユーザー プロビジョニングに対するクラウド HR アプリケーションの計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)に関する記事をご覧ください。

### ライフサイクル ワークフローでの自動ワークフロー タスク

- [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)は、特定の重要なイベント (新しい従業員が組織で仕事を開始するようにスケジュールされる前や、組織に所属する時間中に状態が変わるとき、組織を離れるときなど) で実行されるワークフロー タスクを自動化します。 たとえば、新しいユーザーのマネージャーに一時アクセス パスを記載したメールを送信したり、そのユーザーの業務初日にウェルカム メールを送信するようにワークフローを構成することができます。

### エンタイトルメント管理での自動割り当てポリシー

- [エンタイトルメント管理での自動割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)は、ユーザーの属性変更に基づいて、ユーザーのグループ メンバーシップ、アプリケーション ロール、SharePoint サイトの役割を追加および削除します。 [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios)と [Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使用して、要求に応じてグループ、Teams、Microsoft Entra の役割、Azure リソース の役割、SharePoint Online サイトにユーザーを割り当てることもできます。

### オンプレミス アプリとその他のディレクトリへの自動プロビジョニング

- ユーザーが Microsoft Entra ID で適切にグループ メンバーシップとアプリの役割が割り当てられると、[ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/id-governance/what-is-provisioning)により、SCIM、LDAP、SQL を介して数百のクラウドおよびオンプレミス アプリケーションに接続するコネクタを使用して、他のアプリケーションのユーザー アカウントを作成、更新、削除できます。

### ゲスト ユーザーのライフサイクル権限の自動割り当て

- ゲスト ライフサイクルでは、ユーザーが組織のリソースへのアクセスを要求できる他の組織を[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)で指定できます。 これらのユーザーの要求のいずれかが承認されると、そのユーザーはエンタイトルメント管理によって組織のディレクトリに [B2B](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) ゲストとして自動的に追加され、適切なアクセスが割り当てられます。 またエンタイトルメント管理では、アクセス権の有効期限が切れたり取り消されたりすると、B2B ゲスト ユーザーが組織のディレクトリから自動的に削除されます。

### ユーザーとゲストの自動繰り返しレビュー

- [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)では、組織のディレクトリに既に存在する既存のゲストの定期的なレビューが自動化され、そのユーザーにアクセスが不要になると、そのユーザーが組織のディレクトリから削除されます。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/deploy-sap-netweaver"} -->
## SAP NetWeaver AS ABAP 7 をデプロイする

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/deploy-sap-netweaver
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事では、テスト用に SAP ECC を使用してラボ環境を設定する方法について説明します。

このドキュメントでは、テスト用に SAP ECC を使用してラボ環境を設定する方法について説明します。

### SAP Cloud Appliance Library からの ASE テスト環境への SAP NetWeaver AS ABAP 7.51 のデプロイ

1. SAP Cloud Appliance Library: https://cal.sap.com/に移動します。
2. SAP CAL で自分用のアカウントを作成し、SAP Cloud Appliance Library にログインします。 https://calstatic.hana.ondemand.com/res/docEN/042bb15ad2324c3c9b7974dbde389640.html
3. [アプリケーション テンプレート - SAP Cloud Appliance Library](https://cal.sap.com/console/tenant_QMOV0I8VZP4H#/applianceTemplates) ページに移動します
4. **7.51** アプライアンス テンプレートを検索し、[アプライアンスの作成] ボタンをクリックして、**SAP NetWeaver AS ABAP 7.51 SP02 on ASE** アプライアンスを作成します。

[Image: SAP アプライアンス テンプレートのスクリーンショット。]

1. [新しいアカウントの作成] を選択します。 **Standard Authorization for Authorization**の種類を使用するには、次のアクセス許可が必要です。標準の承認には、アプライアンスを作成および管理するためのアクセス許可が含まれます。 SAP Cloud Appliance Library にアクセス許可を付与する Microsoft Azure ユーザーが必要とするロールは次のとおりです。

- オプション 1: サブスクリプションの管理者。つまり、ユーザーはロール所有者を持ち、scope/subscriptions/ にアクセスできます。
- オプション 2: Microsoft Azure ユーザーは共同作成者とユーザー アクセス管理者のロールを持ち、スコープ /subscriptions/ にアクセスできます。 Azure Active Directory アカウントの全体管理者のロールを持っている必要があります。 承認の種類として **アプリケーションによる承認**を使用するには、Azure AD テナントにアプリケーションを手動で登録し、サブスクリプションに共同作成者ロールを付与する必要があります。 アプリケーションの登録を作成し、サブスクリプションの対応するアプリケーションに共同作成者ロールを割り当てる必要があります。 このガイドでは、**Authorization with Application**を使用します。

1. [接続のテスト] ボタンをクリックします。 アプライアンスの名前を入力し、SAP インスタンスにアクセスするためのマスター パスワードを選択します。 [作成] をクリックして Azure AD テナントにリソースをプロビジョニングする
2. アプライアンスにアクセスするために必要な秘密キーをダウンロードして保存します。

[Image: 秘密キーの生成のスクリーンショット。]

1. SAP CAL によって、サブスクリプションへのリソースのプロビジョニングとアクティブ化が開始されます。 完了するまでに最大数時間かかる場合があります。
2. 次の手順では、SAP GUI にログオンし、開発者ライセンスを取得し、パッケージを保存して SAP インスタンス (Web サービスの発行など) を更新できるようにインストールします。 SAP Cloud Appliance Library でアプライアンスを作成すると、システムにログオンするのに十分な一時ライセンス キーが SAP システムによって生成されます。 最初の手順として、システムを使用する前に、コミュニティ Wiki ページの「Minisap ライセンス キーを要求してインストールする方法」で説明されているように、Minisap ライセンスをインストールする必要があります。

Minisap ライセンスをインストールすると、インストール番号が INITIAL から DEMOSYSTEM に変更されます。 ユーザー DEVELOPER およびインストール番号 DEMOSYSTEM の開発者アクセス キーは、既にシステム内にあり、顧客の名前範囲 (Z\*、Y\*) で開発を開始できます。

### SAP ECC 7.51 コネクタの Web サービスの公開

Web サービス構成ツールは、WSDL (Web サービス記述言語) を介して Web サービスを検出し、提供するサービス、エンドポイント、および操作 (BAPI) を取得します。 サービス、エンドポイント、および操作 (BAPI) は、Microsoft Identity Manager (MIM) 2016 を使用して SAP サーバーにアクセスし、ID を操作するために Web サービス コネクタによって使用されます。

Web サービスを検出するには、SAP ECC 7.51 で Web サービスを公開する必要があります。 この記事では、SAP ECC 7.51 ワークベンチから Web サービスを公開するプロセスについて説明します。

SAP ECC 7 にログインし、トランザクション コード SE80 を使用して ABAP ワークベンチを入力します。 これにより、オブジェクト ナビゲーター画面が開きます。この画面で、パッケージ、機能グループの表示、BSP プログラムなどさまざまな SAP アプリケーション コンポーネントを維持します。

Web サービス構成ツールで利用される Web サービスを作成するには、まず、すべてのオブジェクトが異なるシステム間を簡単に移動できるようにパッケージを作成する必要があります。

1. ドロップダウンから [パッケージ] を選択し、新しいパッケージに名前を付けて Enter キーを押します。 オブジェクトがシステムで使用できない場合は、次の画面が表示されます。 [はい] をクリックしてパッケージの作成を続行します。

[Image: パック作成のスクリーンショット。]

1. **[パッケージの作成]** 画面で必要な詳細を入力し、[作成] ボタンをクリックします。 アプリケーション コンポーネントを指定できます。 このアクションにより、作成されたオブジェクトのスコープは、指定されたアプリケーション (ABAP、MM、PS、LW などの SAP モジュール) のみに制限されます。 注: オブジェクトをグローバルにするアプリケーション コンポーネントは指定しないことをお勧めします。

[Image: パッケージ作成のスクリーンショット。]

1. システムは、転送要求の入力を求めます。 [要求] の横にあるボタンをクリックして、新しい転送要求を生成します。

[Image: 要求プロンプトのスクリーンショット。]

1. 新しいローカル要求を作成します。

[Image: Workbench 要求のスクリーンショット。]

1. 要求名 (NPL\*) をダブルクリックして選択します。

[Image: URL のスクリーンショット。]

1. ワークベンチ要求が選択されたら、[作成] ボタンをクリックしてパッケージを作成します。

[Image: 要求の作成のスクリーンショット。]

1. パッケージが作成されたら、[オブジェクト名] で Web サービスの作成を開始するには、[パッケージ名] を右クリックし、[作成] -&gt; [エンタープライズ サービス] を選択します。

[Image: オブジェクト ナビゲーターのスクリーンショット。]

1. オブジェクトの種類を選択する画面が表示されます。 オブジェクトの種類として [サービス プロバイダー] を選択し、[続行] をクリックします。

[Image: オブジェクトの種類の作成のスクリーンショット。]

1. [サービス プロバイダーの種類] 画面で、[既存の ABAP オブジェクト (隅から隅まで)] を選択し、[続行] キーを押します。 隅から隅まででは、既存のアプリケーションを使用してバックエンドから開始し、特定の機能に対してサービスを有効にします。 これは、実装から始めて、インターフェイスに向かって移動することを意味します。

[Image: [サービス プロバイダーの種類] のスクリーンショット。]

1. 選択した [オブジェクトの種類] の [サービス定義] の名前と説明を指定します。 [続行] をクリックします。

[Image: サービス定義のスクリーンショット。]

1. [エンドポイントの種類] 画面で、[関数グループ] を選択し、[続行] を押します。 MIM の [Web サービス] 構成ツールには、選択したすべての BAPI に対して 1 つの URL が必要であるため、[関数グループ] を選択する必要があります。

[Image: エンドポイントの種類のスクリーンショット。]

1. [エンドポイントの関数グループ] 画面で、必要な [関数グループ] の名前を選択し、[続行] を押します。 この例で選択した関数グループは既に定義されており、ユーザーに関連する BAPI がカプセル化されています。

[Image: エンドポイントの関数グループのスクリーンショット。]

1. [関数グループ] 画面で、必要なすべての BAPI を選択し、関数グループに含まれていない BAPI を追加します。 [続行] をクリックします。 この例では、SU\_USER 関数グループのすべての BAPI が選択されています。 プロジェクトで使用する BAPI について SAP 管理者に問い合わせてください。

[Image: 関数グループのスクリーンショット。]

基本的なユーザー管理シナリオを実装するには、次に公開する BAPI の一覧を制限することができます。

- BAPI\_USER\_GETLIST
- BAPI\_USER\_GETDETAILS
- BAPI\_USER\_CREATE1
- BAPI\_USER\_DELETE
- BAPI\_USER\_CHANGE

1. **[サービスの構成]** 画面で、[セキュリティ設定] のプロファイルを選択します。 選択のために SAP によって定義されるプロファイルは 4 つあります。 要件に応じて 1 つのプロファイルを選択します。

- 証明書とトランスポート保証を使用した認証
- ユーザーとパスワードを使用した認証、トランスポート保証なし
- ユーザーとパスワード、トランスポート保証を使用した認証
- 認証なし、トランスポート保証なし

1. この例では、ユーザーとパスワードを使用した認証と、トランスポート保証なし (HTTP なし) オプションを使用します。 [続行] をクリックします。

[Image: サービスの構成のスクリーンショット。]

1. [トランスポート] 画面で、[要求/タスク名] の横にあるアイコンをクリックし、[ローカル ワークベンチ] 要求を選択します。 [続行] をクリックします。

[Image: トランスポートのスクリーンショット。]

1. **[終了]** 画面で、[完了] ボタンをクリックします。

[Image: [完了] 画面のスクリーンショット。]

1. Web サービスが作成されたら、サービス定義のプロファイル設定を変更する必要があります。 [構成] タブで、ステートフル通信のプロパティを選択し、ステートフル プロファイルをアクティブにします。 ツール バーの [保存] ボタン (ディスケット アイコン) をクリックします。

[Image: プロファイル変更のスクリーンショット。]

1. リポジトリ ブラウザーで ZSAPCONNECTORWS パッケージを展開し、ZSAPCONNECTORWEBSERVICE サービス定義を右クリックして、[アクティブ化] を選択します。

[Image: ZSAPCONNECTORWEBSERVICE サービス定義のスクリーンショット。]

### SOA Manager を使用した Web サービスの構成

Web サービスを構成するには、次の手順に従います。

1. トランザクション SOAMANAGER を開きます。 [技術管理] タブに移動し、[SAP クライアント設定] をクリックします。

[Image: [技術管理] のスクリーンショット。]

1. Web サービス ナビゲーター トレイを展開し、SAP サーバーのホスト名とポート番号を入力します。 [保存] をクリックします。

[Image: ホストとポートのスクリーンショット。]

1. [戻る] をクリックし、[サービス管理] タブに移動します。[Web サービス構成] リンクを選択します。

[Image: Web サービス構成のスクリーンショット。]

1. [オブジェクト名] 入力フィールドに「ZSAPCONNECTORWEBSERVICE」と入力し、[検索] をクリックします。

[Image: 検索結果のスクリーンショット。]

1. ZSAPCONNECTORWEBSERVICE サービス定義をクリックして選択します。
2. [構成] タブで [サービスの作成] ボタンをクリックします。

[Image: 構成作成サービスのスクリーンショット。]

1. [サービス定義の新しいバインドの構成] ページで、サービス名、新しいバインド名を入力し、[次へ] をクリックします。

[Image: サービス定義のバインドのスクリーンショット。]

1. [プロバイダーのセキュリティ] ページで、[トランスポート チャネル認証] の [ユーザー ID / パスワード] を選択し、[次へ] をクリックします。

[Image: サービス定義構成のバインドのスクリーンショット。]

1. [SOAP プロトコル] ページで、すべての設定をデフォルトのままにして、[次へ] をクリックします。

[Image: SOAP プロトコル ページのスクリーンショット。]

1. [操作の設定] ページで、[完了] をクリックします。

[Image: 操作設定の完了画面のスクリーンショット。]

1. サービスが作成されたら、Web ページ アイコンをクリックして WSDL 生成パラメーターを開きます。

[Image: WSDL パラメーターのスクリーンショット。]

WSDL フレーバーを次のように構成します。

- WSP バージョン: ポリシーなし
- SOAP バージョン: SOAP 1.1
- SOAP スタイル: ドキュメント
- WSDL セクション: AllInOne

1. WSDL フレーバーをクリックして SOAP 1.1 として保存します。 限定

[Image: [保存] のスクリーンショット。]

1. [WSDL 生成] セクションでサービスの WSDL URL を見つけて、そのリンクをコピーします。 例: `http://vhcalnplci.dummy.nodomain:8000/sap/bc/srt/wsdl/flv\_10002A1011D1/bndg\_url/sap/bc/srt/rfc/sap/zsapconnectorwebservice/001/zsapconnectorws/zsapconnectorws?sapclient\=001`

[Image: WSDL URL のスクリーンショット。]

### SAP ECC 7.51 コネクタの Web サービスのアクティブ化

1. SAP ECC 7 にログインし、トランザクション コード SICF を使用して ABAP ワークベンチを入力します。 階層の種類をサービスとしてメンションし、[実行] ボタンをクリックします。

[Image: 階層の種類のスクリーンショット。]

1. **[サービスの定義]** ページで、ZSAPCONNECTORWS サービス名を入力し、[適用] をクリックします。
2. ZSAPCONNECTORWS サービスを選択し、[サービスのアクティブ化] を選択します。

[Image: サービスのアクティブ化のスクリーンショット。]

1. ICF サービスのアクティブ化を確認します。 [はい] をクリックします。

[Image: 確認のスクリーンショット。]

1. **[サービスの定義]** ページで、WSDL サービス名を入力し、[適用] をクリックします。 両方の WSDL サービスのサービスをアクティブ化することを選択します。

[Image: アクティブなサービスのスクリーンショット。]

1. Web サービス コネクタ テンプレートを構成する前に、お気に入りの SOAP クライアント ツールを使用してデプロイされた Web サービスをテストし、適切なデータが返されることを確認する

### MIM または ECMA2Host マシンから Web サービスに接続する

1. SAP Web サービス エンドポイントをインターネットに発行しないようにするには、SAP デモ ラボ ネットワークと MIM または ECMA2Host マシンの間にピアリングを設定します。 この設定により、内部 IP アドレスで Web サービスにアクセスできるようになります。
2. MIM または ECMA2Host マシン上の hosts ファイルに SAP ホスト名と IP アドレスを追加します。
3. ブラウザーから MIM または ECMA2Host マシンで WSDL URL を開き、SAP Web サービスへの接続を確認します。

次のステップでは、この SOAP エンドポイントと公開された BAPI を使用して SAP ECC ユーザーを管理するための [Web サービス コネクタ テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/sap-template) を作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/entitlement-management-private-access"} -->
## Entra Suite シナリオ: エンタイトルメント管理での Entra プライベート アクセスの使用

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/entitlement-management-private-access
- Service: entra-id-governance / entitlement-management
- Article date: 2025-04-09
- Summary: プライベート アクセスでエンタイトルメント管理を使用する方法について説明します

Microsoft Entra Suite には、最新のリモート作業シナリオでオンプレミスおよびレガシ アプリケーションにアクセスできるユーザーを管理する堅牢なソリューションをデプロイする機能が用意されています。 Microsoft Entra Private Access を使用すると、ユーザーがオンプレミスであるかリモートでアクセスしているかに関係なく、ユーザーがプライベート ネットワーク上の既存の Web アプリ、レガシ アプリ、その他のリソースに安全に接続する方法を最新化できます。 エンタイトルメント管理を使用すると、組織はアクセス要求ワークフロー、アクセス割り当て、レビュー、有効期限を自動化することで、ID とアクセスのライフサイクルを大規模に管理できます。

クラウド アプリ、プロビジョニングまたはフェデレーションをサポートするオンプレミス アプリ、またはセキュリティ グループと同様に、Microsoft Entra Private Access 経由の接続用に構成されたアプリをエンタイトルメント管理に統合できるため、組織はさまざまな種類のリソース間で一貫したガバナンス プロセスを維持できます。

[Image: プライベート アクセスの概念図。]

従来、Entra アプリケーション プロキシは、VPN を必要とせずに、オンプレミスの Web ベースのアプリケーションに安全なリモート アクセスを提供するために使用されてきました。 リモートで作業しているユーザーは、従来は MSSQL、SSH、RDP などのプロトコルに VPN を必要とする Web ベース以外のアプリへのアクセスが必要な場合があります。 これには、次の 2 つの重要な問題があります。

- ほとんどの VPN は、ネットワーク全体にアクセスできます
- 一部の VPN は、従来、クライアントを持つすべてのユーザーにアクセス権を付与し、ユーザーのアクセスの承認された必要性を確認しません。

### 一般的なシナリオ

次のシナリオは、プライベート ネットワーク上のアプリケーションにアクセスできるユーザーを管理することで、Microsoft Entra Suite が環境の最新化にどのように役立つかを示しています。

#### シナリオ 1: 非 Active Directory 統合アプリケーションの VPN 置換

Entra ID Governance では、オンプレミス アプリにアクセスする必要があるユーザーを確立できます。これにより、ユーザーはオンプレミスにいないときに Entra Private Access を介してアプリに安全にアクセスできます。

多くの組織には、プライベート ネットワーク上にアプリケーションがあり、割り当てられたユーザーが組織のネットワークにいる間にそれらのアプリにアクセスできます。 Microsoft Entra のプロビジョニング コネクタを通じて、Entra ID Governance は、LDAP ディレクトリや SQL データベースなどのほとんどのオンプレミス システムでのユーザー アカウントの作成を調整できます。 ​

シナリオに応じて、実際のアプリケーションを表す 2 つまたは 3 つのオブジェクトが Entra に存在します。

- そのアプリケーションのエンドポイントへの Entra プライベート アクセス接続を表すアプリケーション オブジェクトがあります。
- アプリケーションが ID プロバイダーとして Microsoft Entra ディレクトリにフェデレーションされている場合は、SAML、OAuth、OpenID Connect の使用など、アプリケーションのエンドポイントに対するユーザーの認証を表すアプリケーション オブジェクトが存在します。
- アプリケーションでオンプレミスのプロビジョニング エージェント コネクタを使用して LDAP、SQL、PowerShell、SOAP、または REST 経由でプロビジョニングする場合は、そのプロビジョニング統合を表すアプリケーション オブジェクトが存在します。
- アプリケーションで Microsoft Entra によって作成された Active Directory グループが使用されている場合 (以下のシナリオ 3 を参照)、グループが存在します

[Image: シナリオ 1 の概念図。]

関連するアプリケーションのリソースをアクセス パッケージに含めると、ユーザーがパッケージを要求して承認されると、各アプリでアプリ ロールの割り当てを受け取ります。 これにより、アプリにプロビジョニングされ (必要な場合)、Microsoft Entra が要求に応じてアプリのユーザーのフェデレーション トークンを発行し、ユーザーは Entra Private Access を使用してアプリに接続できるようになります。 ユーザーのアクセス パッケージの割り当てが終了すると、そのアカウントがアプリから削除され、アプリに接続する機能も取り消されます。

[Image: VPN の置き換えによるシナリオ 1 の概念図。]

#### シナリオ 2: オンプレミスの Active Directory 統合アプリケーションの VPN 置換

多くの場合、組織は、クラウドとオンプレミスの両方に ID が存在するハイブリッド ユーザー向けに、AD 統合アプリケーションへのセキュリティで保護されたアクセスを可能にする課題に直面しています。 Entra Private Access、エンタイトルメント管理、グループライトバックを利用することで、組織はハイブリッド ユーザーに対して AD 統合オンプレミス アプリへの管理アクセスを実装できます。

Entra のグループは、Entra グループメンバーシップをオンプレミスの AD グループと同期するグループライトバック用に構成されています。 ユーザーは、Entra セキュリティ グループを含む Entra 内のアクセス パッケージの要求を送信することで、プロセスを開始します。 要求が承認されると、ユーザーにアクセス パッケージが割り当てられ、グループに追加されます。

グループの書き戻しによって、ユーザーの Entra グループ メンバーシップは、対応するオンプレミス AD グループに反映されます。 この同期により、オンプレミスの AD グループのメンバーシップは、Entra で行われた変更に従って常に最新の状態に保たれます。 さらに、オンプレミスの AD グループは、ターゲットのオンプレミス アプリケーションへのアクセスを提供するように構成されます。 グループ メンバーシップは、ユーザーがオンプレミス リソースに安全に接続できる条件を定義するプライベート アクセス スコープ ポリシーで構成されています。

ユーザーのアクセスがプロビジョニングされたので、Entra Private Access を使用してオンプレミス アプリケーションに安全に接続できるようになりました。

[Image: シナリオ 2 の概念図。]

#### シナリオ 3: 切断されたアプリの VPN 置換

組織には、プロビジョニング プロトコルや Kerberos や SAML などの先進認証プロトコルをサポートしていないレガシ アプリケーションがある場合があります。 これらのシナリオでは、組織はユーザーをアプリに手動でプロビジョニングし、アプリを依存関係と Entra Private Access プロキシと共に別のネットワーク セグメント (VLAN) に配置できます。 これにより、オンプレミスでもユーザーがアプリに接続できなくなります。

その後、ユーザーは保護されたアプリへのアクセスを要求できます。 承認されると、Entra ID Governance は、アプリ所有者が従業員 [にそのシステム](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-ticketed-provisioning)のアカウントを手動で付与するためのサービス チケット (ServiceNow など) を開きます。 その後、Entra ID ガバナンスは、ユーザーを Entra 内のアプリの表現に割り当てます。

従業員が PC からアプリに接続すると、Entra Private Access は自動的に PC から保護されたアプリへの接続を安全にトンネルします。

アクセスの割り当てが期限切れになると、Entra ID Governance は同様に、アプリ所有者が自分のアカウントを手動で削除するための別のチケットを開き、そのアプリケーションに接続するユーザーの機能を削除します。

[Image: シナリオ 3 の概念図。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/govern-guest-lifecycle-new-project"} -->
## Azure Active Directory Identity Governance によるビジネス パートナーと外部ユーザー向けの新しいガバナンス シナリオを計画する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-guest-lifecycle-new-project
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: 新しいビジネスパートナーや外部ユーザーとのシナリオを始めるための概要を説明します。

::: zone pivot="identity-governance-guest-overview"

### 概要: ビジネス パートナーと外部ユーザーのシナリオの概要

通常、すべてのビジネス プロセスでは、ある時点で、パートナー、請負業者、またはベンダーとの連携が必要になります。 この連携を容易にするために、ビジネス パートナーと外部ユーザーにあなたの組織へのアクセス権が必要となる場合があります。 パートナー、ベンダー、外部ユーザーに関わるビジネス計画には、組織とパートナー間の効果的なコラボレーション、統合、および目標の調整を可能にするテクノロジ ソリューションの設計と実装が含まれます。

Microsoft では、組織との関係に基づいて、次のペルソナを定義しています。

- **従業員。** 組織のフルタイム従業員、パートタイム従業員、または請負業者。
- **ビジネス パートナー。** 企業とビジネス関係を持つ組織。 これらの組織には、共通の目標を達成するために企業と協力するサプライヤー、ベンダー、コンサルタント、戦略的提携先などが含まれます。
- **外部ユーザー。** ビジネス パートナーやコンシューマーなど、組織外部のユーザー。

#### ビジネス プロセスおよびビジネス パートナーと外部ユーザーのシナリオの例

ゲスト シナリオまたは外部アクセス シナリオは、ビジネス パートナーまたは外部ユーザーが組織のリソースと対話する特定のユース ケースであり、組織の Microsoft Entra テナントの 1 つで表現する必要があります。 これらのシナリオは、ビジネス プロセスのほとんどすべての側面に及びます。

次の各ビジネス プロセスと、パートナー計画とやり取りがどのように行われるのかを例として考えてみましょう。 次の表は、これらのプロセスの詳細と、ビジネス パートナーおよび外部ユーザーが関与するシナリオの例を示しています。

| ビジネス プロセス | 定義 | ビジネス パートナーと外部ユーザーの例 |
| --- | --- | --- |
| ビジネス プロセス管理 | 組織のワークフローと運用を改善するための体系的なアプローチ。 | 信用スコアリング機関と提携してローン承認プロセスを効率化する金融サービス会社。 |
| 統合ビジネス計画 | 組織の戦略目標、運用計画、財務予測を調整し、意思決定のための一貫性のある統一されたフレームワークを作成するための総合的なアプローチ。 | 小売企業は、統合された計画プラットフォームを通じて売上予測と在庫レベルをサプライヤーと共有します。 |
| 顧客関係管理 | 組織と顧客とのやり取りを管理するための戦略的なアプローチとシステム。 | ディーラーとのやり取りを調整、サポート、最適化する自動車会社。 |
| サプライ チェーン オーケストレーション | 調達から配送までのすべての段階で効率性、可視性、応答性を確保するためのすべてのサプライ チェーン プロセスの協調管理。 | サプライ チェーンを持ち、サプライヤーやベンダーと連携する必要がある会社。 |
| ビジネス パートナー アカウントのライフサイクル | 組織とその外部ビジネス パートナー間の関係とやり取りを管理するエンド ツー エンドのプロセス。 | ソフトウェア ベンダーと提携して製品の提供を強化するテクノロジ企業。 |
| 他の組織との B2B コラボレーション | 共有リソース、情報、取り組みを通じて共通の目標を達成するための、2 社以上の企業間の戦略的パートナーシップ。 | ベーカリーと提携して双方のビジネスのサービスを強化する地元のコーヒー ショップ。 |

シナリオを理解することは、適切なアクセス制御を設計し、外部の個人との円滑なコラボレーションを確保するのに役立ちます。

### ビジネス パートナーと外部ユーザー向けの新しいガバナンス シナリオの領域

新しいビジネス パートナーと外部ユーザー ソリューションの領域には、通常、次のようなものがあります。 これらは次のとおりです。

[Image: リソースへのアクセスを管理する概念図。]

- [ビジネス要件を把握する](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-guest-lifecycle-new-project?pivots=identity-governance-guest-discover) - 外部 ID の現在のライフサイクルとガバナンス プロセスを特定します。 この演習は、適用可能なシナリオ、実現可能性、スコープを決定するのに役立ちます。
- [セキュリティ態勢を決定する](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-guest-lifecycle-new-project?pivots=identity-governance-guest-secur) - シナリオごとに組織のセキュリティとコラボレーションのニーズを評価します。
- [ビジネスパートナーと外部ユーザーのオンボーディング](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-guest-lifecycle-new-project?pivots=identity-governance-guest-onboard) - ゲストまたは外部 ID に関するオンボーディングは、組織のシステムにこれらの ID を設定するプロセスです。
- [ビジネスパートナーと外部ユーザーのオフボーディング](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-guest-lifecycle-new-project?pivots=identity-governance-guest-offboard) - ゲストまたは外部 ID に関するオンボーディングは、組織のシステムからこれらの ID を削除するプロセスです。

### ライセンス要件

以下の機能の一部を使用するには、Azure Active Directory Identity Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Azure Active Directory Identity Governance のライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」を参照してください。

::: zone-end

::: zone pivot="identity-governance-guest-discover"

### 検出: ビジネス パートナーと外部ユーザーの現在のライフサイクルとガバナンス プロセスを特定する

外部 ID の現在のライフサイクルとガバナンス プロセスを特定します。 この演習は、適用可能なシナリオ、実現可能性、スコープを決定するのに役立ちます。

[Microsoft Entra ID ガバナンスによる従業員とゲストのライフサイクルを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-the-employee-lifecycle)ことについて、外部 ID に重点を置いて確認する。 ここで説明するプロセスは、ゲスト ユーザー、サプライヤー、その他の外部ユーザーが共同作業を行ったりリソースにアクセスしたりするためにも必要になります。 このドキュメントでは、ガバナンス プロセスを検出するために実行できるアクションについて説明します。

ビジネス パートナーと外部ユーザーのガバナンス シナリオではエンタイトルメント管理が使用されるため、「[エンタイトルメント管理の一般的なシナリオ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios)」も確認する必要があります。 これは、ユーザーのロールに基づくエンタイトルメント管理の一般的なシナリオを提供します。 ソリューションを計画する際にはこれらを考慮する必要があります。

現在の状態を評価する際に考慮すべきその他の領域のガイドとして、次の表を使用することもできます。

| プロセス | 説明 |
| --- | --- |
| 外部コラボレーションを開始するのは誰かを決定する | 一般的に、外部コラボレーションを求めるユーザーは、使用するアプリケーションと、アクセスの終了時期がわかっています。 そのため、外部ユーザーの招待、アクセス パッケージの作成、アクセス レビューの完了を委任されたアクセス許可を持つユーザーを決定します。 |
| ビジネス パートナーと外部ユーザーを列挙する | 外部ユーザーは、パートナーが管理する資格情報をもつ Microsoft Entra B2B ユーザーであることも、ローカルにプロビジョニングされた資格情報をもつ外部ユーザーであることもあります。 通常、これらのユーザーの UserType は Guest です。 |
| メール ドメインと companyName プロパティを検出する | 外部ユーザーのメール アドレスのドメイン名によって外部の組織を特定できます。 |
| 許可リスト、ブロックリスト、エンタイトルメント管理を使用する | 許可リストまたはブロックリストを使用して、組織とのテナント レベルでのコラボレーション、またはブロックを有効にします。 |
| 外部ユーザー アクセスを決定する | 外部ユーザーと組織のインベントリを使用して、これらのユーザーに付与するアクセス権を決定します。 |
| アプリケーションのアクセス許可を列挙する | 外部アクセスを認識できるように、機密性の高いアプリへのアクセスを調査します。 |
| 非公式の共有を検出する | メールとネットワーク プランが有効になっている場合、メールや許可されていないサービスとしてのソフトウェア (SaaS) アプリ経由で共有されているコンテンツを調査できます。 |

詳細については、「[組織内での外部コラボレーションの現在の状態を検出する](https://learn.microsoft.com/ja-jp/entra/architecture/2-secure-access-current-state)」を参照してください

#### 例 - 現在のライフサイクルとガバナンス プロセスを特定する

あなたは活気のあるテクノロジ企業 Contoso の IT 管理者であり、コンサルタント、ベンダー、パートナーなどの外部のビジネス ユーザーを効率的かつ安全にオンボーディングするという課題にたびたび直面しています。 現在のオンボーディング プロセスは断片化され、一貫性がないため、セキュリティの脆弱性と非効率性につながっています。 これに取り組むために、あなたは主な要件を特定し、Microsoft Entra をどのように活用できるかを理解するための検出フェーズに着手します。

[Image: 組織の概念図。]

主な要件には、次のようなものがあります。

- 独自のアクセス レベルを必要とするさまざまな部門による多様なゲスト オンボーディングのニーズ。
- ゲスト ユーザーが自分のタスクを実行するために必要な最小限の特権を持っていることを確認することは重要であり、機密データを保護するために堅牢な条件付きアクセス ポリシーと多要素認証が必要です。
- IT 管理者と外部ユーザーの両方にとってシームレスで使いやすいオンボーディング プロセス。 外部ユーザーは、不要な遅延なしに、必要なリソースにすばやく簡単にアクセスできる必要があります。
- Microsoft Teams や SharePoint などの既存のコラボレーション ツールとの統合、およびセルフサービス サインアップ (SSSU) のオプション
- ゲスト ユーザーのアクティビティを定期的に監視し、アクセスの有効期限を設定し、定期的なアクセス レビューによってゲスト アクセスが長期にわたって適切な状態を維持するようにすることで、ゲストを管理する機能。 詳細については、「[Microsoft Entra ID Governance を使用して従業員とゲストのライフサイクルを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-the-employee-lifecycle)」および「[組織内での外部コラボレーションの現在の状態を検出する](https://learn.microsoft.com/ja-jp/entra/architecture/2-secure-access-current-state)」を参照してください

これらの重要な要件を念頭に置いて、考慮すべき 2 つの点を次に示します。

- 外部ユーザーをテナントにオンボードする
- 外部ユーザーにリソースへのアクセスを許可する

ビジネス ケースに応じて、さまざまなオプションがあります。 外部ユーザーのオンボーディングと使用可能なオプションについて詳しく見ていきましょう。

### 次のステップ

::: zone-end

::: zone pivot="identity-governance-guest-secure"

### セキュリティ態勢: ビジネス パートナーと外部ユーザーのセキュリティ態勢を決定する

外部アクセスのガバナンスを検討するときは、組織のセキュリティとコラボレーションのニーズをシナリオ別に評価します。 まずは、エンド ユーザーの日常的なコラボレーションに対する IT チームの制御レベルから始めることができます。 規制が非常に厳しい業界の組織では、IT チームによる管理がより多く必要になる場合があります。

次に例を示します。

- 防衛関連の請負業者には、外部ユーザー、そのアクセス権、アクセス権の削除を明確に特定して文書化する要件がある場合があります。
- コンサルティング機関は、特定の機能を使用して、エンド ユーザーが共同作業を行う外部ユーザーを特定できるようすることができます。
- 銀行の契約社員は、ソフトウェア会社の契約社員よりも多くの制御を必要とする場合があります

[Image: IT チームの完全な制御からエンド ユーザーのセルフサービスまでの範囲を示す棒グラフ。]

注

コラボレーションに対する管理を厳しくすると、IT 予算の増加、生産性の低下、ビジネス成果の遅延につながる可能性があります。 公式のコラボレーション チャネルが煩わしいと認識されると、エンド ユーザーは公式チャネルを回避する傾向があります。 たとえば、エンド ユーザーがセキュリティで保護されていないドキュメントを電子メールで送信したりします。

#### シナリオベースの計画

IT チームは、パートナーにアクセス権を委任して、従業員がパートナーと共同作業できるようにすることができます。 このような委任は、知的財産を保護するための十分なセキュリティを維持しながら行われる場合があります。

従業員とビジネス パートナーのリソースへのアクセスを評価するのに役立つ組織のシナリオをまとめ、評価します。 金融機関には、アカウント情報などのリソースへの従業員のアクセスを制限するコンプライアンス標準がある場合があります。 逆に、同じ機関で、マーケティング キャンペーンなどのプロジェクトに対して、委任されたパートナー アクセスを有効にできます。

[Image: パートナーのセルフサービスへのアクセスを管理する IT チームのバランスの図。]

##### シナリオに関する考慮事項

次のリストを使用して、アクセス制御のレベルを測定します。

- 情報の機密性と、その露出に関連するリスク
- 他のエンド ユーザーに関する情報へのパートナー アクセス
- セキュリティ侵害の被害コストと、一元管理の負担やエンドユーザーの不便との兼ね合い

組織ではまずコンプライアンス目標を達成するために厳しい管理を徹底し、その後、必要に応じて一部の管理をエンドユーザーへ委任していくことができます。 組織内には同時に複数のアクセス管理モデルが存在する場合があります。

#### アーキテクチャに関する考慮事項

適切なセキュリティ アーキテクチャ設計は、セキュリティで保護されたビジネス パートナーと外部ユーザーのシナリオを確保するうえで不可欠な要素です。 セキュリティ プランのドラフトを作成する際には、さまざまな種類の推奨アーキテクチャについて理解しておく必要があります。 Microsoft Identity Governance を使用する推奨アーキテクチャは次のとおりです。

- [従業員とコラボレーション重視のアーキテクチャに関する考慮事項](https://learn.microsoft.com/ja-jp/entra/architecture/external-identity-deployment-architectures#workforce-and-collaboration-oriented-architecture) - 従業員が外部組織のビジネス パートナーと共同作業できるようになります。
- [ビジネス パートナーの分離アクセス](https://learn.microsoft.com/ja-jp/entra/architecture/external-identity-deployment-architectures#isolated-access-for-business-partners) - 外部ユーザーを組織のテナントから分離します

##### 推奨ドキュメント

最後に、次のドキュメントを確認してください。 このドキュメントを確認すると、ビジネス パートナーや外部のユーザー シナリオで使用できるセキュリティ プランを考案して作成できます。

- [Microsoft Entra を使用した Microsoft Entra 外部 ID デプロイ アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/architecture/external-identity-deployment-architectures)
- [セキュリティで保護された外部コラボレーション](https://learn.microsoft.com/ja-jp/entra/architecture/1-secure-access-posture)
- [Microsoft Entra B2B コラボレーションのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/architecture/secure-external-access-resources)。

### 次のステップ

::: zone-end

::: zone pivot="identity-governance-guest-onboard"

### オンボード: ビジネスパートナーと外部ユーザーのオンボーディング

ビジネス パートナーと外部ユーザーのオンボーディングは、ID およびアクセス管理の重要な側面であり、組織内のセキュリティ、コンプライアンス、運用効率を維持するのに役立ちます。 ビジネス パートナーと外部ユーザーのオンボーディングは、次のように分類できます。

- 外部パートナーを追加するためのオンボーディング プロセスを開発する。
- 外部ユーザーをシステムに追加するためのオンボーディング プロセスを開発する。

#### ビジネス パートナーのオンボーディング プロセス

次の項目は、組織のビジネス パートナーをオンボードする際に考慮すべき一般的な手順の一覧です。

- ビジネス パートナーとの共同作業の開始日を決定します。
- 具体的な開始日時を関連するすべての部門に通知します。
- ビジネス パートナーによるすべてのシステム (ポータル、ツール、API) へのアクセスの開始を調整します
- パートナーのリンクされたアカウントが追加され、ロールと割り当てが承認されます

#### 外部ユーザーのオンボーディング プロセス

次の項目は、組織の外部ユーザーをオンボードする際に考慮すべき一般的な手順の一覧です。

- 開始日を決定します。
- 個々のユーザー アクセスをシステムに追加するよう IT 部門に通知します。 これは、アクセス パッケージと自己要求を使用して自動化できます。
- 実行したオンボーディング手順を記録します。
- 人事、IT、および関連するすべての部門から最終承認を得て、オンボーディングに関するすべての側面が完了していることを確認します。

### エンタイトルメント管理を使用してオンボーディングを容易にする

エンタイトルメント管理では、カタログ、アクセス パッケージ、およびポリシーを使用して、次の処理を行います。

- リソースにアクセスできるユーザーを制御する
- ユーザーに自動的にアクセス権を付与する
- ユーザーのアクセス権を削除する
- アクセス パッケージを作成する権限を、管理者以外の担当者に委任できます。
- 接続先の組織を選択して、その組織のユーザーがアクセス権を要求できるようにすることができます。

詳細については、「[エンタイトルメント管理とは](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)」を参照してください

#### カタログ、アクセス パッケージ、ポリシー

エンタイトルメント管理では、アクセス パッケージに複数のポリシーを設定でき、各ポリシーでは、ユーザーがアクセス パッケージへの割り当てを取得する方法と、その期間を設定します。 エンタイトルメント管理では、ビジネス パートナーと外部ユーザーをオンボードし、そのアクセス権とアカウントをテナントに追加するように構成できます。

一般的なアクセス パッケージには、次の処理が含まれます。

- パッケージに関連付けられているすべてのリソースへのアクセス権を追加する
- 必要なすべてのグループにユーザーを追加する
- ゲスト ユーザー ディレクトリにアカウントを追加する
- コンプライアンスのために監査ログを使用する
- オンボーディングが成功したことを確認する通知を送信する

カタログは、リソースとアクセス パッケージのコンテナーです。 関連するリソースとアクセス パッケージをグループ化したい場合は、カタログを作成できます。 たとえば、Contoso などの別の組織と取引する組織用に、ビジネス パートナー カタログを作成できます。 このカタログ内で、アクセス パッケージを作成できます。 アクセス パッケージを使用すると、リソースとポリシーを 1 回だけ設定して、アクセス パッケージの存続期間中にアクセスを自動的に管理できます。 これらのアクセス パッケージは、Contoso 内のさまざまな外部ユーザーとパートナー、および Contoso 組織全体に合わせて調整できます。 たとえば、Contoso ベンダー用、Contoso 請負業者用、Contoso 組織用のアクセス パッケージを用意することができます。 詳細については、「[エンタイトルメント管理でリソースのカタログを作成して管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)」を参照してください

アクセス パッケージを使用すると、リソースとポリシーを 1 回だけ設定して、アクセス パッケージの存続期間中にアクセスを自動的に管理できます。 すべてのアクセス パッケージは、カタログという名前のコンテナーに配置する必要があります。 アクセス パッケージを使うと、カタログにある複数のリソースのロールにアクセスを割り当てることができます。 すべてのアクセス パッケージには、それらにユーザーを割り当てるためのポリシーが少なくとも 1 つ必要です。 詳細については、「[エンタイトルメント管理でアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)」を参照してください

ポリシーは、アクセス パッケージを要求できるユーザーを指定するほか、承認とライフサイクルの設定、またはアクセスを自動的に割り当てる方法も指定します。

詳細については、「[エンタイトルメント管理を使用し、外部ユーザーをオンボードする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard-external-user)」を参照してください

##### ビジネス パートナーと外部ユーザーにオンボーディング アクセスを提供する

エンタイトルメント管理を使用すると、指定した組織のユーザーがアクセス パッケージをセルフサービスで使用できるようにするポリシーを定義できます。 そのポリシーには、承認が必要かどうか、アクセス レビューが必要かどうか、およびそのアクセスの有効期限が含まれます。

詳細については、「[エンタイトルメント管理で外部ユーザーのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users)」を参照してください

具体的な例については、「[チュートリアル - 承認プロセスを通じて Microsoft Entra ID に外部ユーザーをオンボードする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard-external-user)」を参照してください

#### ライフサイクル ワークフローを使用してオンボーディング タスクを支援する

ライフサイクル ワークフローは Identity Governance 機能です。組織はこれを使用して、次の 3 つの基本的なライフサイクル プロセスを自動化することで、Microsoft Entra ユーザーを管理できます:

- **参加者**: 個人がアクセスを必要とする範囲に入るとき。 たとえば、会社や組織に参加する新入社員です。
- **異動者**: 個人が組織内の境界間を移動するとき。 この移動には、より多くのアクセスまたは認可が必要になる場合があります。 たとえば、マーケティングに所属していたユーザーが、営業組織のメンバーになった場合などです。
- **退職者**: 個人がアクセスを必要とする範囲から外れるとき。 この移動には、アクセスの削除が必要になる場合があります。 たとえば、退職する従業員や解雇された従業員などです。

ワークフローには、ライフサイクルの遷移に合わせてユーザーに対して自動的に実行される特定のプロセスが含まれています。 ワークフローは、[タスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks)と[実行条件](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows#understanding-lifecycle-workflows)で構成されています。

ビジネス パートナーと外部ユーザーが作業を開始できるようにするタスクの多くは、ライフサイクル ワークフローを使用して自動化できます。 詳しくは、「[ライフサイクル ワークフローとは](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)」を参照してください

これらのタスクやその他のタスクを自動化する方法の詳細については、「[従業員とゲストのオンボーディング タスクを自動化する](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-onboard-custom-workflow-portal)」を参照してください

#### 直接割り当てを使用して追加リソースを反復処理する

ゲスト ユーザーに割り当てる必要がある追加要件があるかもしれません。 これは、アクセス パッケージとエンタイトルメント管理を使用して実行できます。

たとえば、通常のユーザーに割り当てられているアクセス パッケージがあり、同様のパッケージをゲスト ユーザーにも割り当てたい場合があります。 エンタイトルメント管理を使用すると、ゲスト ユーザーをこれらのアクセス パッケージに割り当てることができます。 「[エンタイトルメント管理でアクセス パッケージの割り当てを表示、追加、削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments)」を参照してください

既存のパッケージに触れずに新しいパッケージを作成し、そのパッケージにゲスト ユーザーを割り当てることができます。 詳細については、以下を参照してください。

- [アクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)
- [エンタイトルメント管理でアクセス パッケージの自動割り当てポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)
- [エンタイトルメント管理でアクセス パッケージのライフサイクル設定を変更する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy)

以下のセクションでは、ゲスト ユーザーをオンボードするさまざまな方法の例を示します

#### 例 - エンタイトルメント管理を使用したユーザーのプロビジョニング

IT 管理者として、Fabrikam の外部コンサルタントをオンボードして共同作業する必要があるプロジェクトを特定しました。 アクセス管理、承認ワークフロー、ライフサイクル管理を自動化することが重要です。 Microsoft Entra のエンタイトルメント管理を使用して、複数のリソース (グループ、アプリケーション、SharePoint サイトなど) をバンドルできるアクセス パッケージを作成し、アクセス権が付与される前に適切な個人がアクセス要求を確認して承認することを保証する承認ワークフローを含めて、アクセスの期間を設定します。

コンサルタントに招待を送信すると、コンサルタントはアクセス パッケージを受け入れるためのリンクが記載されたメールを受信します。 コンサルタントの一人である John は、リンクをたどり、ID を検証し、数分以内に必要なリソースにアクセスできるようになります。 オンボーディング プロセスはスムーズかつ安全で、コンサルタントはすぐに共同作業を開始できます。 プロジェクトは遅延なく開始され、Contoso と Fabrikam 間の生産的なパートナーシップが確保されます。

[Image: エンタイトルメント管理を使用した招待の概念図。]

詳細については、以下を参照してください:

- [エンタイトルメント管理を使用し、外部ユーザーをオンボードする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard)
- [エンタイトルメント管理でアクセス パッケージの自動割り当てポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)
- [チュートリアル - 承認プロセスを通じて Microsoft Entra ID に外部ユーザーをオンボードする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard-external-user)
- [Microsoft Entra の展開シナリオ - 従業員やゲストのオンボーディング、ID、すべてのアプリを対象としたアクセス ライフサイクル ガバナンス](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-scenario-workforce-guest)

#### 例 - エンド ユーザー主導のコラボレーション

別の設計プロジェクトでは、設計チームのマネージャーから IT 管理者であるあなたに、Global Solutions の John Doe を共同作業に加えるよう依頼がありました。 主な目的は、ユーザーをディレクトリに招待し、ディレクトリに参加させて共同作業を行うことです。 外部ユーザーの John のメール アドレスを使用して、彼をゲスト ユーザーとして招待します。 メールによる招待はシンプルかつ柔軟性があり、Gmail や Microsoft アカウントなどのソーシャル ID など、さまざまなドメインや ID プロバイダーのユーザーを招待できます。 John は自分の資格情報を使用して安全にサインインすることで、パスワードのメンテナンスやアカウントのライフサイクル管理が不要になり、オンボーディング プロセスが簡素化されます。

[Image: B2B を使用した招待の概念図。]

詳細については、「[Microsoft Entra 管理センターで B2B コラボレーション ユーザーを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)」および「[外部コラボレーションの設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)」を参照してください

#### 例 - 直接割り当てを使用して追加リソースを反復処理する

IT 管理者としてあなたは、アクセス要求をバイパスし、特定のユーザーをアクセス パッケージに直接割り当てて、外部チームに即時アクセスを許可する必要があります。 アクセス パッケージに最初のアクセス パッケージ割り当てポリシーを作成します。 アクセス パッケージのポリシーを確認し、外部ユーザーを直接追加することが許可されていることを確認します。

すべての準備が整ったところで、Emma は割り当てのタブに移動しました。彼女は外部チーム メンバーのメール アドレスを入力し、正しくリストされていることを確認しました。 最終確認した後、彼女は [割り当て] ボタンをクリックしました。

ほぼ即座に、外部パートナーは招待を受け取りました。 彼らはシームレスにディレクトリに参加し、遅延なく必要なリソースにアクセスできるようになりました。 この効率的な設定により、両チームはすぐに共同作業を開始できるようになり、生産的で革新的な環境が構築されました。 詳細については、以下を参照してください。

- [アクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)
- [エンタイトルメント管理でアクセス パッケージの自動割り当てポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)
- [エンタイトルメント管理でアクセス パッケージのライフサイクル設定を変更する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy)

### 次のステップ

::: zone-end

::: zone pivot="identity-governance-guest-offboard"

### オフボード: ビジネス パートナーと外部ユーザーの効率的なオフボーディング。

ビジネス パートナーと外部ユーザーのオフボーディングは、ID およびアクセス管理の重要な側面であり、組織内のセキュリティ、コンプライアンス、運用効率を維持するのに役立ちます。 ビジネス パートナーと外部ユーザーのオフボーディングは、次のように分類できます。

- 外部パートナーを削除するためのオフボーディング プロセスを開発する。
- システムから外部ユーザーを削除するためのオフボーディング プロセスを開発する。

#### ビジネスパートナーの契約終了手続き

次の項目は、組織のビジネス パートナーをオフボードする際に考慮すべき一般的な手順の一覧です。

- ビジネス パートナーとの共同作業の終了日を決定します。
- 具体的な終了日時を関連するすべての部門に通知します。
- ビジネス パートナーによるすべてのシステム (ポータル、ツール、API) へのアクセスの終了を調整します
- パートナーのリンクされたアカウントが削除され、ロールと割り当てが取り消されます
- パートナーと共有しているデータを確認し、保持する必要があるデータや安全に削除する必要があるデータを判断します。
- パートナーのシステムから専有データや機密データを収集します
- データの削除が実行され、コンプライアンスが確保されていることを確認します。

#### 外部ユーザーのオフボーディング プロセス

次の項目は、組織の外部ユーザーをオフボードする際に考慮すべき一般的な手順の一覧です。

- 終了日を決定します。
- 保留中の成果物またはタスクが完了していることを確認します。
- 個々のユーザー アクセスをシステムから削除するよう IT 部門に通知します。 これは、アクセス パッケージを使用して自動化できます。
- 外部ユーザーが所有するデータが転送または返却されたことを確認します。
- 外部ユーザー固有のすべてのアカウントが無効になっており、同じ組織の他のユーザーに影響がないことを確認します。
- 実行したオフボーディング手順を記録します。
- 人事、IT、および関連するすべての部門から最終承認を得て、オフボーディングに関するすべての側面が完了していることを確認します。

#### エンタイトルメント管理を使用してオフボーディングを容易にする

エンタイトルメント管理では、アクセス パッケージに複数のポリシーを設定でき、各ポリシーでは、ユーザーがアクセス パッケージへの割り当てを取得する方法と、その期間を設定します。 エンタイトルメント管理では、ゲスト ユーザーの最後のアクセス パッケージ割り当ての有効期限が切れたときにゲスト ユーザーを自動的にオフボードし、テナントからアクセス権とゲスト ユーザー アカウントを削除するように構成できます。

アクセス パッケージの有効期限が切れたときに、オフボーディング プロセスには次の処理が含まれている必要があります。

- 有効期限切れのパッケージに関連付けられたすべてのリソースへのアクセスを取り消す
- メンバーになっているグループからユーザーを削除する
- ゲスト ユーザー ディレクトリからアカウントを削除する
- コンプライアンスのために監査ログを使用する
- オフボーディングが成功したことを確認する通知を送信する

詳細については、「[エンタイトルメント管理で外部ユーザーのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users)」を参照してください

### ライフサイクル ワークフローを使用してオフボーディング タスクを支援する

ライフサイクル ワークフローは ID ガバナンス機能です。組織はこれを使用して、いくつかの基本的なライフサイクル プロセスを自動化することで、Microsoft Entra ユーザーを管理できます。

ワークフローには、ライフサイクルの遷移に合わせてユーザーに対して自動的に実行される特定のプロセスが含まれています。 ワークフローは、[タスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks)と[実行条件](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows#understanding-lifecycle-workflows)で構成されています。

詳細については、「[従業員の在籍最終日の後のオフボーディング タスクを Microsoft Entra 管理センターで自動化する](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-scheduled-leaver-portal)」を参照してください

#### 例 - ゲスト アカウントのオフボーディング

John は、アクセス パッケージの有効期限が切れたゲストです。 システムはそのパッケージにリンクされているすべてのリソースへの John のアクセス権を取り消します。 John は所属しているすべてのグループから削除されます。 最後のアクティブ パッケージの有効期限が切れると、John のアカウントはゲスト ユーザー ディレクトリから削除されます。

詳細については、「[アクセス レビューを使用してユーザーとゲスト ユーザーのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-access-review)」および「[従業員の在籍最終日の後のオフボーディング タスクを Microsoft Entra 管理センターで自動化する](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-scheduled-leaver-portal)」を参照してください

### 次のステップ

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/govern-the-employee-lifecycle"} -->
## Microsoft Entra ID Governance を使用して従業員のライフサイクルを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-the-employee-lifecycle
- Service: entra-id-governance / entitlement-management
- Article date: 2025-04-09
- Summary: ID ライフサイクル管理の概要と、従業員のライフサイクルを管理する目的について説明します。

Identity Governance により、組織は、生産性（例として、従業員が組織に加わったときに必要なリソースにどのくらい速くアクセスできるか）のバランスを達成するのに役立ちます。 *セキュリティ* (従業員の雇用形態の変更などによって、時間の経過に伴いアクセス権をどのように変更すべきか) とのバランスを取ることができます。

### ID ライフサイクル管理

**ID ライフサイクル管理**は Identity Governance の基盤です。効果的なガバナンスを大規模に行うためには、アプリケーションの ID ライフサイクル管理インフラストラクチャの最新化が必要です。 ID ライフサイクル管理が目指すのは、組織に属する個人のデジタル ID ライフサイクル プロセス全体の自動化と管理です。

[Image: 他のソースとターゲットとのプロビジョニングにおける Microsoft Entra の関係図。]

### デジタル ID とは

デジタル ID は、1 つ以上のコンピューティング リソース (オペレーティング システムやアプリケーションなど) によって使用されるエンティティに関する情報です。 これらのエンティティは、人や組織、アプリケーション、デバイスを表す場合があります。 この ID は通常、それに関連付けられている属性 (名前や識別子など) とプロパティ (アクセス管理に使用されるロールなど) によって記述されます。 これらの属性は、システムで、だれが何にアクセスできるかや、だれがそのリソースの使用を許可されているかなどの判定を行うときに役立ちます。

### デジタル ID のライフサイクル管理

デジタル ID の管理は複雑な作業です。デジタルな表現に、対応する実世界の物事 (組織の従業員としての人と組織との関係など) を関連付けるとなればなおさらです。 小規模な組織では、ID を必要とする個人のデジタル表現を維持することは、手動のプロセスになる可能性があります。 たとえば、だれかが採用されたり、請負業者が到着したりしたときは、IT スペシャリストがそのアカウントをディレクトリ内に作成し、必要なアクセス権を割り当てることができます。 一方、中規模および大規模な組織では、自動化を使用することで、スケーリングの効果を高め、ID を正確に管理することができます。

組織で ID ライフサイクル管理を確立するためのプロセスでは、通常、次のステップに従います。

1. 記録システム (組織が信頼できるとして扱うデータ ソース) が既に存在するかどうかを判定します。 たとえば、組織に Workday や SuccessFactors などの人事システムがある場合、そのシステムが、従業員とそのプロパティ (従業員の名前や部署など) の一部の最新リストを提供する際の正式なものになります。 また、Exchange Online などのメール システムは、追加属性である従業員のメール アドレスに対して正式なものになったりします。
2. これらの記録システムを Microsoft Entra ID と接続し、Microsoft Entra ID の既存のユーザーとレコードのシステムの間の不整合を解決します。 たとえば、Microsoft Entra ID には、今は組織に所属していない元従業員のユーザー アカウントなど、現在使用されていないデータが設定されている可能性があります。
3. Microsoft Entra ID に適切なユーザーが割り当てられたら、アプリケーションで使用される 1 つ以上のディレクトリとデータベースに Microsoft Entra ID を接続し、それらのディレクトリと Microsoft Entra ID の記録データ システムのコピーとの間の不整合を解決します。 たとえば、過去に切断されたアプリケーションのディレクトリに、元従業員のアカウントなどの古いデータが存在する場合があります。
4. 記録システムが存在しない場合に、どのようなプロセスを使用すれば信頼できる情報を提供できるかを確認します。 たとえば、ビジターのデジタル ID が存在するが、組織にはビジターのためのデータベースがない場合は、ビジターのデジタル ID がいつ必要なくなるかを判定するための代わりの方法を見つけることが必要になる可能性があります。
5. 更新が必要なディレクトリまたはデータベースのそれぞれに対し、記録システムや他のプロセスから変更が Microsoft Entra ID を介してレプリケートされるようにします。

### 従業員や組織と関係がある個人を表現するための ID ライフサイクル管理

従業員や、組織との関係を持つその他の個人 (請負業者や学生など) のための ID ライフサイクル管理を計画する場合、多くの組織は "入社、異動、退社" を次のプロセスとしてモデル化します。

- 参加 - 個人がアクセス権が必要なスコープに入ると、用途ごとに ID が必要となるため、デジタル ID がまだ利用できない場合は、新たに作成する必要があります
- 移動 - 個人が境界を越えて移動するとき、新たなアクセスの認可をその個人のデジタル ID に追加したり、そこから削除したりする必要があります
- 離脱 - 個人がアクセス権が必要なスコープから離脱すると、アクセス権を削除する必要があり、その後は監査やフォレンジクス以外の用途では、その ID は不要になります

たとえば、新しい従業員が組織に参加していて、その従業員が組織に所属したことがない場合、その従業員には、Microsoft Entra ID のユーザー アカウントとして表される新しいデジタル ID が必要です。 このアカウントの作成が "入社" プロセスに該当します。これは、新しい従業員の始業日時を指定できる記録システム (Workday など) があれば、自動化することができます。 その後、組織内で、ある従業員が営業からマーケティングに異動することになったとしましょう。これは "異動" プロセスに該当します。 この異動では、営業組織で持っていた (必要なくなった) アクセス権の削除と、マーケティング組織内の (新しく必要になった) アクセス権の付与が必要になります。

### ゲストのための ID ライフサイクル管理

パートナー、サプライヤー、その他のゲストが共同作業を行ったり、リソースにアクセスしたりできるようにするために、追加の ID にも同様のプロセスが必要です。 Microsoft Entra エンタイトルメント管理では、組織のリソースへのアクセス権を必要とする組織外のユーザーと共同作業を行うために必要なライフサイクル管理が、Microsoft Entra 外部 ID 企業間 (B2B) を利用して実現されます。 Microsoft Entra B2B では、外部ユーザーは自分のホーム ディレクトリまたは ID プロバイダーに対して認証を行いますが、組織のディレクトリに表示されます。 組織のディレクトリに表示されることにより、ユーザーにリソースへのアクセスを割り当てることができます。 組織に属していない個人は、エンタイトルメント管理を通じてアクセス権をリクエストし、必要に応じて自分のデジタル ID を作成することができます。 ユーザーがアクセス権を失ったとき、それらのデジタル ID は自動的に削除されます。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/identity-governance-use-cases"} -->
## Microsoft Entra ID ガバナンスのユース ケース

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/identity-governance-use-cases
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra ID ガバナンスのユース ケースについて説明します。

Microsoft Entra ID ガバナンスを使うと、セキュリティや従業員の生産性に対す組織のニーズと、適切なプロセスや可視性とのバランスを取ることができます。 このバランスを実現するには、ID ガバナンスに関する以下のユース ケースを考慮する必要があります。

### Microsoft Entra ID ガバナンスのユース ケース:

典型的な ID ガバナンスのユース ケースは次のとおりです。

- **[従業員、ゲスト、パートナーのライフサイクルを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-the-employee-lifecycle)** - 従業員のライフサイクルの管理、または **ID ライフサイクル管理**は、Identity Governance の基盤です。効果的なガバナンスを大規模に行うためには、アプリケーションの ID ライフサイクル管理インフラストラクチャの最新化が必要です。 パートナー、サプライヤー、その他のゲストが共同作業を行ったり、リソースにアクセスしたりできるようにするために、追加の ID にも同様のプロセスが必要です。
- **[アプリケーションへのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare)** - - 組織には、ユーザーの ID の作成時にそのユーザー用に最初にプロビジョニングしたアクセス権を上回るアクセス権を管理するプロセスが必要です。 さらに、企業組織では、アクセス ポリシーと管理を継続的に開発し適用できるように、効率的なスケールが可能でなければなりません。 アプリケーションに固有のアクセス権を管理するための組織のコントロールです。

これらのユース ケースは、組織内のセキュリティ、コンプライアンス、効率的なユーザー管理を維持する上での ID ガバナンスの広さと重要性を示しています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/least-privileged"} -->
## Microsoft Entra ID Governance での最小限の特権について

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/least-privileged
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事では、最小限の特権の概念と、それが Microsoft Entra ID Governance とどのように関連しているかについて説明します。

ID ガバナンス戦略を採用する前に対処する必要がある 1 つの概念は、最小特権 (POLP) の原則です。 最小限の特権とは、ユーザーとグループに、職務を実行するために必要な最小限のレベルのアクセスとアクセス許可のみを割り当てるという ID ガバナンスの原則です。 これは、ユーザーまたはグループが業務を遂行できるようにアクセス権を制限するだけでなく、攻撃者によって悪用される、またはセキュリティ侵害につながる可能性のある不要な特権を最小限に抑えるという考え方です。

Microsoft Entra ID Governance に関しては、最小限の特権の原則を適用すると、セキュリティの強化とリスクの軽減に役立ちます。 このアプローチにより、ユーザーとグループには、各自のロールと責任に関連するリソース、データ、アクションのみに対してアクセス権が付与され、それ以上は付与されません。

### 最小限の特権の原則の主要な概念

- **必要なリソースのみへのアクセス:** タスクを実行するために本当に必要な場合にのみ、情報とリソースへのアクセスがユーザーに許可されます。 これにより、機密データへの不正アクセスを防ぎ、セキュリティ侵害の潜在的な影響を最小限に抑えます。 ユーザー プロビジョニングを自動化すると、アクセス権の不要な付与を減らすことができます。 [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)は ID ガバナンス機能です。組織はこれを使用して、基本的なライフサイクル プロセスを自動化することで、Microsoft Entra ユーザーを管理できます。
- **ロールベースのアクセス制御 (RBAC):** アクセス権は、ユーザーの特定のロールまたはジョブ機能に基づいて決定されます。 各ロールには、その責任を果たすために必要な最小限のアクセス許可が割り当てられます。 [Microsoft Entra ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)によって、Microsoft Entra リソースへのアクセスを管理します。
- **Just-In-Time 特権:** アクセス権は、必要な期間だけ付与され、不要になったときに取り消されます。 これにより、攻撃者が過剰な特権を悪用する機会が減ります。 [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) は Microsoft Entra ID のサービスで、これにより、お客様の組織内の重要なリソースへのアクセスを管理、制御、監視できるようになり、Just-In-Time アクセスを実現できます。
- **定期的な監査とレビュー:** ユーザーのアクセスとアクセス許可の定期的なレビューが行われ、付与されているアクセス権が引き続き必要なことが確認されます。 これは、最小限の特権の原則からの逸脱を特定して修正するのに役立ちます。 [Microsoft Entra ID のアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)は Microsoft Entra の一部であり、組織はこれを使用することで、グループ メンバーシップ、エンタープライズ アプリケーションへのアクセス、ロールの割り当てを効率的に管理できます。 ユーザーのアクセスを定期的にレビューし、適切なユーザーのみが継続的なアクセス権を持っていることを確認できます。
- **既定の拒否:** 既定の方法はアクセスを拒否することであり、アクセスは承認された目的でのみ明示的に許可されます。 これは、不要な特権が付与される可能性がある "既定の許可" アプローチとは対照的です。 [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)は、アクセス要求ワークフロー、アクセスの割り当て、レビュー、期限切れ処理を自動化することで、ID とアクセスのライフサイクルを大規模に管理できる、ID ガバナンス機能です。

最小限の特権の原則に従うことで、組織はセキュリティの問題のリスクを軽減し、アクセス制御がビジネス ニーズに合っていることを確認できます。

### Identity Governance の機能で管理するための最小特権ロール

Identity Governance で管理タスクを実行するには、最小特権ロールを使用するのがベスト プラクティスです。 必要に応じて Microsoft Entra PIM を使用してロールをアクティブ化し、これらのタスクを実行することをお勧めします。 Identity Governance の機能を構成するための最小特権[ディレクトリ ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を次に示します。

| 特徴量 | 最小特権役割 |
| --- | --- |
| 特権管理 | アイデンティティ ガバナンス管理者 |
| アクセスレビュー | ユーザー管理者 (特権ロール管理者を必要とする Azure または Microsoft Entra ロールのアクセス レビューを除く) |
| ライフサイクル ワークフロー | ライフサイクル ワークフロー管理者 |
| Privileged Identity Management | 特権ロール管理者 |
| 使用条件 | セキュリティ管理者または条件付きアクセス管理者 |

注

エンタイトルメント管理の最小特権ロールは、ユーザー管理者ロールから Identity Governance 管理者ロールに変更されています。
<!-- /MSL-PAGE -->
