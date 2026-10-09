# Microsoft Learn — Microsoft Entra / 監視・正常性 (ログ / レポート) (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 79

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health"} -->
## Microsoft Entra の監視と正常性に関するドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health
- Service: entra-id / monitoring-health
- Article date: 2024-11-04
- Summary: Microsoft Entra ID でログ、レポート、監視ツールを使用する方法について学習します。

Microsoft Entra ID でログ、レポート、監視統合、ブック、レコメンデーションにアクセスして使用する方法について学習します。

### ID の監視と正常性について

#### 概要

- [ID の監視と正常性とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)
- [ID に関するレコメンデーションとは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)
- [ID Workbooks とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks)

#### 概念

- [監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)

### ID ログとレポート

#### 概念

- [Microsoft Entra Health](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)
- [使用状況と分析情報のレポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report)
- [アクティビティ ログのスキーマ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-activity-log-schemas)

#### 攻略ガイド

- [Microsoft Entra 管理センターでアクティビティ ログにアクセスする](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)
- [Microsoft Graph を使ってログにアクセスする](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/quickstart-access-log-with-graph-api)
- [ログをカスタマイズしてフィルター処理する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-customize-filter-logs)
- [ログのダウンロード](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs)

### ID ログと Azure Monitor ログ

#### 攻略ガイド

- [診断設定を構成する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)
- [アクティビティ ログと Azure Monitor ログの統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)
- [Azure Monitor ログでのアクティビティ ログの分析](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-activity-logs-log-analytics)
- [イベント ハブにログをストリーム配信する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)

### 一般的なトラブルシューティングのシナリオ

#### 攻略ガイド

- [一般的なユーザー サインイン エラー](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-troubleshoot-sign-in-errors)
- [Microsoft Entra ID で非アクティブなユーザー アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts)
- [サインイン エラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-troubleshoot-sign-in-errors)
- [サインイン ログで条件付きアクセスの詳細を確認する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/how-to-view-applied-conditional-access-policies)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-activity-log-schemas"} -->
## 監視と正常性アクティビティ ログのスキーマの詳細 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-activity-log-schemas
- Service: entra-id / monitoring-health
- Article date: 2025-05-27
- Summary: Microsoft Entra の監査、ログイン、ログのスキーマで見つかった詳細を解釈する方法について説明します。

この記事では、Microsoft Entra アクティビティ ログに含まれる情報、ならびにそのスキーマが他のサービスで使用される方法について説明します。 この記事では、Microsoft Entra 管理センターと Microsoft Graph のスキーマについて説明します。 いくつかの主要なフィールドについて説明します。

### 前提条件

- ライセンスとロールの要件については、[Microsoft Entra の監視と正常性のライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)に関する記事を参照してください。
- ログのダウンロードのオプションは、Microsoft Entra ID のすべてのエディションで利用できます。
- Microsoft Graph を使用してプログラムでログをダウンロードするには、[Premium ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)が必要です。
- **レポート閲覧者** は、Microsoft Entra のアクティビティ ログを閲覧するために必要な最小限の特権ロールです。
- 監査ログは、ライセンスを取得している機能に関して使用できます。
- 必要なライセンスがない場合、ダウンロードしたログの結果に一部のプロパティが `hidden` と表示されることがあります。

### ログ スキーマとは

Microsoft Entra の監視と正常性は、Azure Monitor、Microsoft Sentinel、その他のサービスと統合できるログ、レポート、監視ツールを提供します。 これらのサービスは、ログのプロパティをサービスの構成にマッピングする必要があります。 スキーマは、プロパティのマップ、使用可能な値、サービスによって使用される方法になります。 ログ スキーマを理解すると、効果的なトラブルシューティングとデータ解釈に役立ちます。

Microsoft Graph は、Microsoft Entra ログにプログラムでアクセスする主要な方法です。 Microsoft Graph 呼び出しの応答は JSON 形式であり、ログのプロパティと値が含まれます。 ログのスキーマは、[Microsoft Graph のドキュメント](https://learn.microsoft.com/ja-jp/graph/api/overview?view=graph-rest-1.0&preserve-view=true)で定義されています。

Microsoft Graph API には 2 つのエンドポイントがあります。 V1.0 エンドポイントは最も安定しており、運用環境で一般的に使用されています。 多くの場合、ベータ版にはより多くのプロパティが含まれていますが、変更される可能性があります。 このため、運用環境でスキーマのベータ版を使用することはお勧めしません。

Microsoft Entra のお客様は、Log Analytics ワークスペースに送信されるアクティビティ ログ ストリームを構成できます。 この統合により、セキュリティ情報イベント管理 (SIEM) 接続、長期的なストレージ、ログ分析を使用したクエリ機能の向上が可能になります。 Azure Monitor のログ スキーマは、Microsoft Graph スキーマとは異なる場合があります。

これらのスキーマの詳細については、次の記事を参照してください。

- [Azure Monitor の監査ログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/auditlogs)
- [Azure Monitor のログイン ログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/signinlogs)
- [Azure Monitor のプロビジョニング ログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/tables/aadprovisioninglogs)
- [Microsoft Graph の監査ログ](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit?view=graph-rest-1.0&preserve-view=true)
- [Microsoft Graph のログイン](https://learn.microsoft.com/ja-jp/graph/api/resources/signin?view=graph-rest-1.0&preserve-view=true)
- [Microsoft Graph のプロビジョニング ログ](https://learn.microsoft.com/ja-jp/graph/api/resources/provisioningobjectsummary?view=graph-rest-1.0&preserve-view=true)

### スキーマを解釈する方法

値の定義を調べるとき、ご使用のバージョンに注意してください。 V1.0 とベータ版のスキーマには違いがある場合があります。

#### すべてのログ スキーマに記載されている値

一部の値はすべてのログ スキーマで共通です。

- `correlationId`: この一意の ID はさまざまなサービスにまたがるアクティビティを関連付けを可能にし、トラブルシューティングに使用されます。 この値が複数のログに存在しても、サービス間でログを結合する機能は示されません。
- `status` または `result`: この重要な値は、アクティビティの結果を示します。 指定できる値は、`success`、`failure`、`timeout`、`unknownFutureValue` です。
- 日付と時刻: アクティビティが発生した日時は協定世界時 (UTC) で表示されます。
- 一部のレポート機能には Microsoft Entra ID P2 ライセンスが必要です。 正しいライセンスがない場合、`hidden` の値が返されます。

#### 監査ログ

- `activityDisplayName`: アクティビティ名または操作名を示します (例: 「ユーザーの作成」と「グループにメンバーの追加」)。 詳細については、「[監査ログ アクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)」を参照してください。
- `category` は、アクティビティの対象となるリソース カテゴリを示します。 たとえば、`UserManagement`、`GroupManagement`、`ApplicationManagement`、`RoleManagement` などです。 詳細については、「[監査ログ アクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)」を参照してください。
- `initiatedBy` はアクティビティを開始したユーザーまたはアプリに関する情報を示します。
- `targetResources` は、変更されたリソースに関する情報を提供します。 指定できる値には、`User`、`Device`、`Directory`、`App`、`Role`、`Group`、`Policy`、`Other` などがあります。
- `ipAddress`: `initiatedBy` セクションで見つかったのは、OAuth クライアントの IP アドレスです。 IP アドレスは、サービスのエンドポイントのピア (直接接続されたクライアント) です。

#### サインイン ログ

- ID 値: ユーザー、テナント、アプリケーション、リソースには一意の識別子があります。 例には、次のものがあります。
    - `resourceId`: ユーザーがログインした *リソース*。
    - `resourceTenantId`: アクセスされている*リソース*を所有するテナント。 `homeTenantId` と同じの場合があります。
    - `homeTenantId`: ログインしていてユーザー *アカウント*を所有するテナント。
- リスク情報: 危険なユーザー、ログイン、リスク検出の特定の状態に関連する理由を説明します。
    - `riskState`: 危険なユーザー、ログイン、リスク イベントの状態を報告します。
    - `riskDetail`: 危険なユーザー、ログイン、リスク検出の特定の状態に関連する理由を説明します。 値 `none` は、ユーザーまたはログインに対してこれまで何のアクションも実行されていないことを意味します。
    - `riskEventTypes_v2`: ログインに関連するリスク検出の種類。
    - `riskLevelAggregated`: 集計リスク レベル。 値 `hidden` は、Microsoft Entra ID 保護に対してユーザーまたはログインが有効になっていなかったことを意味します。
- `crossTenantAccessType`: リソースにアクセスするために使用されるクロステナント アクセスの種類を説明します。 たとえば、B2B、Microsoft サポート、パススルーのログインがここにキャプチャされます。
- `status`: エラー コードとエラーの説明を含むログイン状態 (ログインエラーが発生した場合)。

#### 適用される条件付きアクセス ポリシー

`appliedConditionalAccessPolicies` サブセクションには、そのログイン イベントに関連する条件付きアクセス ポリシーが記載されます。 このセクションは*適用*条件付きアクセス ポリシーと呼ばれますが、適用されてい*ない*ポリシーもこのセクションに表示されます。 ポリシーごとに個別のエントリーが作成されます。 詳細については、「[conditionalAccessPolicy リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/conditionalaccesspolicy?view=graph-rest-1.0&preserve-view=true)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-audit-logs"} -->
## Microsoft Entra ID 監査ログについて説明します - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs
- Service: entra-id / monitoring-health
- Article date: 2026-06-05
- Summary: Microsoft Entra 監査ログでキャプチャされるアクティビティとイベントの種類と、トラブルシューティングにログを使用する方法について説明します。

Microsoft Entra アクティビティ ログには監査ログが含まれます。これは、Microsoft Entra ID でログに記録されたすべてのイベントに関する包括的なレポートです。 アプリケーション、グループ、ユーザー、ライセンスへの変更はすべて、Microsoft Entra 監査ログにキャプチャされます。

テナントの正常性を監視するには、他にも次の 3 つのアクティビティ ログを使用できます。

- **[サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)** - サインインとユーザーのリソース使用状況に関する情報。
- **[サインアップ (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ups)** - [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations) の場合のみ、サインアップの成功や失敗など、すべてのセルフサービス サインアップ試行に関する情報。
- **[プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)** - ServiceNow でのグループの作成や、Workday からインポートされたユーザーなど、プロビジョニング サービスによって実行されるアクティビティ。

この記事では、監査ログで提供される情報や、回答される質問の種類などの概要について説明します。

### 監査ログでできること

Microsoft Entra ID の監査ログを使用して、コンプライアンスに必要となることが多い、システム アクティビティ レコードにアクセスできます。 ユーザー、グループ、アプリケーションに関連する質問の回答を確認できます。

**ユーザー：**

- ユーザーに最近適用された変更の種類は何ですか?
- 何人のユーザーが変更されたか。
- 何個のパスワードが変更されたか。

**[グループ]:**

- 最近追加されたグループは何ですか?
- グループの所有者は変更されたか。
- グループまたはユーザーに対するライセンスとは何ですか?

**アプリケーション:**

- 更新または削除されたアプリケーションは何ですか?
- アプリケーションのサービス プリンシパルは変更されたか。
- 誰または何がサービス プリンシパルを作成し、なぜ作成されたのですか?
- アプリケーションの名前は変更されたか。

**カスタム セキュリティ属性:**

- [カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)の定義または割り当てにどのような変更が行われましたか?
- 属性セットに対してどのような更新が行われましたか?
- どのカスタム属性値がユーザーに割り当てられましたか?

**エージェント：**

- 特定のエージェントによって実行された操作
- エージェント サービス プリンシパルに行われた変更は何ですか？
- エージェント ID の詳細が変更されましたか?

メモ

監査ログのエントリはシステムによって生成されるため、変更または削除することはできません。

### ログに表示される情報

監査ログには、テナント内のアクティビティに関するいくつかの重要な詳細が表示されます。 キーの詳細は一目で表に表示され、特定のログ エントリを選択して詳細を確認できます。 Microsoft Entra 管理センターは、既定で **Directory** タブに設定され、次の情報が表示されます。

- 発生した日時
- 発生をログに記録したサービス
- アクティビティのカテゴリと名前
- アクティビティの状態

特定のログ エントリを選択すると、次のようなさらに詳細が表示されます。

- トラブルシューティング用の関連付け ID
- アクティビティに関連付けられているアクターまたはターゲット リソースの詳細
- 変更されたプロパティに該当する場合、古い値と新しい値

メモ

監査ログの詳細では、IP アドレスに OAuth クライアントの IP アドレスが反映されます。 IP アドレスは、サービスのエンドポイントの TCP ピアです。

**[カスタム セキュリティ]** の 2 番目のタブには、カスタム セキュリティ属性の監査ログが表示されます。 このタブでデータを表示するには、[属性ログ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-administrator)または[属性ログ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-reader)のロールが必要です。 この監査ログには、カスタム セキュリティ属性に関連するすべてのアクティビティが表示されます。 詳細については、「[カスタム セキュリティ属性とは](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)」を参照してください。

[Image: [ディレクトリ] タブと [カスタム セキュリティ] タブが強調表示されている監査ログのスクリーンショット。]

使用可能な監査アクティビティの完全な一覧については、「監査アクティビティのリファレンス を参照してください。

### Microsoft 365 のアクティビティ ログ

Microsoft 365 のアクティビティ ログは、[Microsoft 365 管理センター](https://learn.microsoft.com/ja-jp/microsoft-365/admin/admin-overview/admin-center-overview)から確認できます。 Microsoft 365 のアクティビティ ログと Microsoft Entra のアクティビティ ログでは多くのディレクトリ リソースが共有されていますが、Microsoft 365 のアクティビティ ログがすべて表示されるのは、Microsoft 365 管理センターのみです。

また、[Office 365 Management API](https://learn.microsoft.com/ja-jp/office/office-365-management-api/office-365-management-apis-overview) を使用すると、Microsoft 365 のアクティビティ ログにプログラムでアクセスすることもできます。

ほとんどのスタンドアロンまたはバンドルされた Microsoft 365 サブスクリプションには、Microsoft 365 データセンターの境界内の一部のサブシステムへのバックエンド依存関係があります。 この依存関係には、ディレクトリの同期を維持するため、および基本的に Exchange Online のサブスクリプション オプトインで手間のかからないオンボーディングを有効にできるようにするために情報の書き戻しが必要です。 これらのライトバックの監査ログ エントリには、"Microsoft Substrate Management" によって実行されたアクションが表示されます。これらの監査ログ エントリは、Exchange Online によって実行される作成/更新/削除操作を Microsoft Entra ID に示します。 エントリは情報を提供するものであり、アクションは必要ありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-diagnostic-settings-logs-options"} -->
## Microsoft Entra ID からのストリーミングに使用できるログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-diagnostic-settings-logs-options
- Service: entra-id / monitoring-health
- Article date: 2025-04-16
- Summary: 格納、分析、または監視用エンドポイントへのストリーミングに使用できる Microsoft Entra ログについて説明します。

Microsoft Entra ID 診断設定を使用し、長期保持とデータ分析情報のために、アクティビティ ログを複数のエンドポイントにルーティングできます。 ルーティングするログを選択し、エンドポイントを選択します。

この記事では、Microsoft Entra 診断設定を使用し、エンドポイントにルーティングできるログについて説明します。 ログは、セキュリティ調査の重要性に基づいて階層に分類されます。

### ログ ストリーミングの要件とオプション

イベント ハブやストレージ アカウントなどのエンドポイントを設定するには、異なるロールやライセンスが必要な場合があります。 新しい診断設定を作成または編集するには、Microsoft Entra テナントの**セキュリティ管理者**であるユーザーが必要です。

最適なログ ルーティング オプションを決定するには、「[アクティビティ ログにアクセスする方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)」を参照してください。 全体的なプロセスと各エンドポイントの要件については、次の記事を参照してください。

- [ログを Log Analytics ワークスペースに送信して Azure Monitor ログと統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)
- [ログをストレージ アカウントにアーカイブする](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-archive-logs-to-storage-account)
- [イベント ハブにログをストリーム配信する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)
- [パートナー ソリューションに送信する](https://learn.microsoft.com/ja-jp/azure/partner-solutions/overview)

### アクティビティ ログ オプション

次のログは、ストレージ、分析、または監視のためにエンドポイントにルーティングできます。

#### 監査ログ

`AuditLogs` レポートには、Microsoft Entra テナント内のアプリケーション、グループ、ユーザー、ライセンスに対する変更がキャプチャされています。 監査ログをルーティングしたら、日付/時刻、イベントをログしたサービス、変更を行ったユーザー別にフィルター処理または分析することができます。 詳細については、「[監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)」を参照してください。

#### サインイン ログ

`SignInLogs` は、対話型のサインインのログを送信します。これは、ユーザーによるサインインによって生成されるログです。 サインイン ログは、ユーザーが Microsoft Entra サインイン画面でユーザー名とパスワードを入力したとき、または MFA チャレンジに合格したときに生成されます。 詳細については、「[対話型のユーザー サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)」を参照してください。

#### 非対話型サインイン ログ

`NonInteractiveUserSIgnInLogs` は、ユーザーに代わって (たとえば、クライアント アプリによって) 行われるサインインです。 デバイスまたはクライアントでは、ユーザーに代わって、トークンまたはコードを使用してリソースの認証またはアクセスが行われます。 詳細については、「[非対話型のユーザー サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-noninteractive-sign-ins)」を参照してください。

#### サービス プリンシパル サインイン ログ

アプリまたはサービス プリンシパルのサインイン アクティビティを確認する必要がある場合、`ServicePrincipalSignInLogs` が適切なオプションである可能性があります。 これらのシナリオでは、認証に証明書またはシークレットが使用されます。 詳細については、「[サービス プリンシパルのサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-service-principal-sign-ins)」を参照してください。

#### マネージド ID のサインイン ログ

`ManagedIdentitySignInLogs` では、サービス プリンシパルのサインイン ログと同様に分析情報が提供されますが、マネージド ID の場合、シークレットが Azure によって管理されます。 詳細については、[マネージド ID によるサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-managed-identity-sign-ins)に関するページを参照してください。

#### プロビジョニング ログ

組織が、Workday や ServiceNow などの Microsoft 以外のアプリケーションを通じてユーザーのプロビジョニングを行う場合、`ProvisioningLogs` レポートをエクスポートすることができます。 詳細については、「[プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)」を参照してください。

#### AD FS サインイン ログ

Active Directory フェデレーション サービス (AD FS) アプリケーションのサインイン アクティビティは、この使用状況と分析情報のレポートにキャプチャされます。 `ADFSSignInLogs` レポートをエクスポートして、AD FS アプリケーションのサインイン アクティビティを監視できます。 詳細については、[AD FS サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report#ad-fs-application-activity)に関するページを参照してください。

#### 危険なユーザー

`RiskyUsers` ログでは、サインイン アクティビティに基づいて危険な状態のユーザーが特定されます。 このレポートは、Microsoft Entra ID 保護 の一部であり、Microsoft Entra ID からのサインイン データを使っています。 詳細については、[Microsoft Entra ID 保護 の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)に関する記事を参照してください。

#### ユーザー リスク イベント

`UserRiskEvents` ログは、Microsoft Entra ID 保護 の一部です。 このログでは、危険なサインイン イベントに関する詳細がキャプチャされます。 詳細については、[リスクの調査方法](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)に関するページを参照してください。

#### ネットワーク アクセス トラフィック ログ

`NetworkAccessTrafficLogs` は、Microsoft Entra Internet Access と Microsoft Entra Private Access に関連付けられます。 このログは Microsoft Entra ID に表示されますが、組織が Microsoft Entra Internet Access と Microsoft Entra Private Access を使って企業リソースへのアクセスをセキュリティで保護していない限り、このオプションを選択してもワークスペースに新しいログは追加されません。 詳細については、「[Global Secure Access とは](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)」を参照してください。

#### 危険なサービスプリンシパル

`RiskyServicePrincipals` ログを使うと、Microsoft Entra ID 保護 でリスクとして検出されたサービス プリンシパルに関する情報が提供されます。 サービス プリンシパル リスクは、ID またはアカウントが侵害されているおそれがあることを表します。 これらのリスクは、Microsoft の内部および外部の脅威インテリジェンス ソースからのデータとパターンを使用して非同期で計算されます。 このようなソースには、セキュリティ研究者、法執行の専門家、Microsoft のセキュリティ チームが含まれる可能性があります。 詳細については、「[ワークロード ID をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)」を参照してください。

#### サービスのプリンシパルなリスクイベント

`ServicePrincipalRiskEvents` は、サービス プリンシパルの危険なサインイン イベントに関する詳細を提供します。 これらのログには、サービス プリンシパル アカウントに関連し、疑わしいとして特定されたイベントが含まれる場合があります。 詳細については、「[ワークロード ID をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)」を参照してください。

#### 危険なエージェント

`RiskyAgents` ログは、危険として検出Microsoft Entra ID 保護エージェントに関する情報を提供します。 エージェント リスクは、エージェント ID が侵害される確率を表します。 詳細については、「 [エージェントの ID 保護」を](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)参照してください。

#### エージェントのリスクイベント

`AgentRiskEvents` ログには、リスクのフラグが設定されているエージェントに影響を与える個々のリスク検出に関する詳細がキャプチャされます。 詳細については、「 [エージェントの ID 保護」を](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)参照してください。

#### 強化された Microsoft 365 監査ログ

`EnrichedOffice365AuditLogs` は、Microsoft Entra Internet Access に対して有効にすることができるエンリッチされたログに関連付けられます。 組織が Microsoft Entra Internet を使用して Microsoft 365 トラフィックへのアクセスをセキュリティで保護しており、"かつ" エンリッチされたログを有効にしていない限り、このオプションを選択しても、ワークスペースに新しいログは追加されません。 詳細については、[Global Secure Access でエンリッチされた Microsoft 365 ログを使用する方法](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-view-enriched-logs)に関するページを参照してください。

#### Microsoft Graph アクティビティ ログ

`MicrosoftGraphActivityLogs` を使うと、管理者は、Microsoft Graph API 経由でテナントのリソースにアクセスしている HTTP 要求をすべて完全に可視化できます。 これらのログを使うと、侵害されたユーザー アカウントがテナントで実行したアクティビティの特定や、クライアント アプリケーションでの問題のある動作や予期しない動作 (過剰な呼び出しボリュームなど) の調査を行うことができます。 `SignInLogs` を使ってこれらのログを同じ Log Analytics ワークスペースにルーティングし、サインイン ログのトークン要求の詳細を相互参照します。 詳細については、「[Microsoft Graph アクティビティ ログにアクセスする](https://learn.microsoft.com/ja-jp/graph/microsoft-graph-activity-logs-overview)」をご覧ください。

#### リモートネットワークのヘルスログ

`RemoteNetworkHealthLogs` は、グローバル セキュア アクセスを介して構成されたリモート ネットワークの正常性に関する分析情報を提供します。 組織が Microsoft Entra Internet Access と Microsoft Entra Private Access を使用して、企業リソースへのアクセスをセキュリティで保護していない限り、このオプションを選んでもワークスペースに新しいログは追加されません。 詳細については、「[リモート ネットワークの正常性ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-remote-network-health-logs)」を参照してください。

#### Microsoft サービス プリンシパルのサインイン ログ (プレビュー)

`MicrosoftServicePrincipalSignInLogs`では、ユーザーが Microsoft Teams 内で Word 文書を開いた場合など、Microsoft 所有 (ファースト パーティ) サービスがテナント内の他の Microsoft サービスに対して認証されるシナリオを可視化できます。 これらのログは、サービス間認証に関する透明性を高めるためにリリースされましたが、複雑で大量のデータが生成されるため、ほとんどのお客様には必要ありません。 これらのアプリケーションは、アプリケーションのセキュリティを確保するために Microsoft のセキュリティによって監視され、最小限の特権の原則に従います。 このデータはセキュリティ調査に不可欠ではないことを強調し、構成ミスやテナント ロックアウトなどの潜在的な悪影響を引き起こす可能性があるため、このデータに基づいてアプリケーションを無効にするなどのアクションを実行しないことを強くお勧めします。このデータは、診断設定を通じてのみオプトインとして提供され、現在プレビュー段階です。 詳細とよく寄せられる質問については、 [FAQ ページ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reports-faq)を参照してください。

#### カスタム セキュリティ属性の監査ログ

`CustomSecurityAttributeAuditLogs` は、診断設定の **[カスタム セキュリティ属性]** セクションで構成されます。 これらのログには、Microsoft Entra テナントのカスタム セキュリティ属性への変更が取り込まれます。 Microsoft Entra 監査ログでこれらのログを表示するには、[属性ログ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-reader)ロールが必要です。 これらのログをエンドポイントにルーティングするには、[属性ログ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-administrator)ロールと[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)が必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-flagged-sign-ins"} -->
## Microsoft Entra ID でフラグ付きサインインについて説明します - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-flagged-sign-ins
- Service: entra-id / monitoring-health
- Article date: 2026-02-09
- Summary: Microsoft Entra ID でのサインインの問題のトラブルシューティングに、フラグ付きサインイン機能を使用する方法について説明します。

IT 管理者は、サインインの問題をできるだけ早く解決してユーザーのブロックを解除したいと考えています。 サインイン ログで使用可能なデータの量が原因で、適切な情報を見つけるのが難しい場合があります。

この記事では、関連する問題を見つけやすくすることで、ユーザーのサインインの問題の解決にかかる時間を大幅に短縮するフラグ付きサインイン機能の概要について説明します。

### フラグ付きサインインとは

Microsoft Entra のサインイン イベントは、ユーザーのサインインとテナントの認証構成で何が生じたかを理解するために非常に重要です。 しかし、Microsoft Entra ID では 1 日に 80 億を超える認証が処理されるため、非常に多くのサインイン イベントが発生する可能性があり、管理者が重要な認証を見つけるのが難しい場合があります。

フラグ付きサインイン機能は、サポートを必要とするユーザー サインインのシグナル対ノイズ比を向上させることを目的としています。 この機能を使用すると、ユーザーはヘルプが必要なサインイン エラーに関する認識を高めることができます。 管理者やヘルプ デスク担当者にとっても、適切なイベントをより効率的に見つけることができるというメリットがあります。 フラグ付きサインイン イベントには、他のサインイン イベントと同じ情報に加えて、ユーザーが確認のためにイベントにフラグを設定したことも示されます。

フラグが設定されたサインインを使用して、次のことができます。

- ユーザーに、IT 管理者のサポートが必要なサインイン エラーを事前に示す**権限を付与**します。
- サインイン エラーを見つけるプロセスを**簡略化**します。
- エンド ユーザーがイベントにフラグを設定する以外の操作を行わなくても、ヘルプ デスク担当者がサインイン エラーを見つけることが**できるようにします**。

### しくみ

ユーザーは、サインイン エラーが表示された場合は、フラグを有効にすることを選択できます。 その後 20 分間、同じブラウザーとクライアント デバイスまたはコンピューターでのそのユーザーからのサインイン イベントは、サインイン ログに *[確認用にフラグが設定されています：はい] *と表示されます。 20 分後、フラグは自動的にオフになります。

- Microsoft Entra ID にサインインしているすべてのユーザーは、メンバーユーザーであれ、ゲストユーザーであれ、サインインにレビュー用のフラグを付ける可能性があります。
- フラグ付きサインイン イベントを確認するには、サインイン ログを読み取るアクセス許可が必要です。 詳細については、[アクティビティ ログにアクセスする方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs#prerequisites) を参照してください。
- 名前は似ていますが、 **フラグ付きサインイン** と **危険なサインイン**は異なる機能です。
    - フラグ付きサインインは、ユーザーが支援を求めているサインイン エラー イベントです。
    - 危険なサインインは、Microsoft Entra ID 保護の機能です。 詳細については、[[Microsoft Entra ID 保護の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)]を参照してください。

## [エラーにフラグを設定する方法](#tab/how-to-flag-an-error)
ユーザーは、サインイン エラーのフラグ設定を有効にするには、この最初の手順を完了する必要があります。

1. ユーザーはサインイン中にエラーを受け取ります。
2. ユーザーは、エラー メッセージで **[詳細の表示** ] を選択します。
3. エラー メッセージの **[トラブルシューティングの詳細** ] セクションで、[フラグの設定を **有効にする]** を選択します。

    - テキストが **[フラグ付けを無効にする]** に変更され、フラグ設定が有効になりました。
    - ユーザーは同じブラウザーとクライアントを使用する必要があり、そうでない場合、イベントにフラグが設定されていません。

    [Image: Microsoft Entra ID サインイン ページのエラー メッセージのスクリーンショット。]
4. (同じブラウザー アプリケーションで) *新しい*ブラウザー ウィンドウを開き、失敗したのと同じサインインを試します。

サインイン エラーが再現されると、フラグ付きの診断がサインイン ログに送信されます。

## [管理センターでフラグ付きイベントを検索する](#tab/find-flagged-events-in-the-admin-center)
フラグ付きサインイン イベントが表示されるまで、サインイン ログに数分かかる場合があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上でサインインしてください。
2. **Entra ID**&gt;に移動し、**モニタリングとヘルス**&gt;の**サインイン ログ**を参照します。
3. **[フィルターの追加]** メニューを開き、**[レビュー用のフラグ付き]** を選択します。 フラグ付きイベントがすべて表示されます。

    [Image: [レビュー用のフラグ付き] フィルターが選択されている Microsoft Entra ID サインイン ログのスクリーンショット。]
4. 必要な場合は、さらにフィルターを適用してイベント ビューを絞り込みます。
5. イベントを選択して、何が起こったかを確認します。

## [Microsoft Graph を使用してフラグ付きイベントを検索する](#tab/find-flagged-events-using-microsoft-graph)
フラグ付きサインインは、Microsoft Graph API を使用して見つけることができます。 詳細については、Microsoft Graph ドキュメントの[「サインインのリソースの種類」](https://learn.microsoft.com/ja-jp/graph/api/resources/signin)を参照してください。

すべてのフラグ付きサインインを表示する:

- `https://graph.microsoft.com/beta/auditLogs/signIns?&$filter=flaggedforReview eq true`

UPN (例: user@contoso.com) による特定のユーザーのフラグ付きサインインのクエリ:

- `https://graph.microsoft.com/beta/auditLogs/signIns?&$filter=flaggedforReview eq true and userPrincipalname eq 'user@contoso.com'`

特定のユーザー、かつ特定の日付以降のフラグ付きサインインのクエリ:

- `https://graph.microsoft.com/beta/auditLogs/signIns?&$filter=flaggedforReview eq true and createdDateTime ge 2021-10-01 and userPrincipalname eq 'user@contoso.com'`

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-identity-secure-score"} -->
## ID セキュリティ スコアとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score
- Service: entra-id / monitoring-health
- Article date: 2026-02-09
- Summary: ID セキュリティ スコアを使用して、Microsoft Entra テナントのセキュリティ体制を改善する方法について説明します。

ID セキュリティ スコアは、セキュリティに関する Microsoft の推奨事項にどの程度適合しているかを示すインジケーターとして機能する割合として表示されます。 ID セキュリティ スコアの各改善アクションは、構成に合わせて調整されます。 スコアにアクセスし、Microsoft Entra の推奨事項でスコアに関連する個々の推奨事項を表示できます。 また、時間の経過と同時にスコアがどのように変化するかを確認することもできます。

[Image: [推奨事項] ページのスクリーンショット。[セキュリティ スコアの詳細] が強調表示されています。]

### 前提 条件

- ID セキュア スコアは、無料および有料のお客様が利用できます。
- 一部の推奨事項では、表示と操作に有料ライセンスが必要です。 詳細については、「Microsoft Entra の推奨事項 を参照してください。
- 改善アクションを表示し、更新を *表示* しない場合は、少なくとも [サービス サポート管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#service-support-administrator) ロールが必要です。
- 改善アクションの状態を *更新* するには、少なくとも [SharePoint 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-administrator) ロールが必要です。
- ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

### ID セキュリティ スコアはどのように私に利益をもたらしますか？

このスコアは、ID セキュリティ体制を客観的に測定し、ID セキュリティの改善を計画し、改善の成功を確認するのに役立ちます。 Microsoft Entra の推奨事項の改善アクションに従うことで、ID への投資の一環として組織で利用できる機能を利用できます。

ID セキュリティ スコアには、次の推奨事項が含まれています。

- VPN 統合の構成
- 複数のグローバル管理者を指定する
- 信頼できないアプリケーションへの同意をユーザーに許可しない
- パスワードの有効期限を切らないでください
- 正しく構成されていないエージェント証明書テンプレートを編集する
- 正しく構成されていない登録エージェント証明書テンプレートを編集する
- レガシ認証をブロックするポリシーを有効にする
- ハイブリッドの場合にパスワード ハッシュ同期を有効にする
- セルフサービス パスワード リセットを有効にする
- すべてのユーザーが MFA を完了できることを確認する
- 偽装を防ぐためにセキュリティで保護されていない Kerberos 委任を変更する
- サインイン リスク ポリシーを使用してすべてのユーザーを保護する
- ユーザー リスク ポリシーを使用してすべてのユーザーを保護する
- Microsoft LAPS を使用してローカル管理者パスワードを保護および管理する
- 機密性の高いグループから休止中のアカウントを削除する
- 機密性の高い Microsoft Entra Connect アカウントに対する安全でないアクセス許可を削除する
- Microsoft Entra Connect AD DS コネクタのエンタープライズまたはドメイン管理者アカウントを置き換える
- 管理ロールに多要素認証 (MFA) を要求する
- GPO で可逆的なパスワードが見つかった
- Microsoft Entra Connect AD DS Connector アカウントのパスワードをローテーションする
- クリア テキスト資格情報の公開を停止する
- 脆弱な暗号の使用を停止する
- 最小限の特権を持つ管理者ロールを使用する

### それはどのように動作しますか?

24 時間ごとに、セキュリティ構成を確認し、設定を推奨されるベスト プラクティスと比較します。 この評価の結果に基づいて、ディレクトリに対して新しいスコアが計算されます。 セキュリティ構成がベスト プラクティスのガイダンスと完全には一致せず、改善アクションが部分的にしか満たされていない可能性があります。 これらのシナリオでは、コントロールで使用できる最大スコアの一部が付与されます。

### ID セキュリティ スコアを使用する方法

ID セキュリティ スコアにアクセスするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グローバル 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)としてサインインします。
2. **Entra ID**&gt;**Identity Secure Score** に移動して、ダッシュボードまたは **Entra ID**&gt;**Overview**&gt;**Recommendations** を表示します。 [ **セキュリティ** フィルター] オプションを選択すると、ID セキュリティ スコアに含まれている推奨事項のみが表示されます。

[Image: [セキュリティ スコア] フィルターが強調表示されている Entra の推奨事項のスクリーンショット。]

各推奨事項は、構成に基づいて測定されます。 Microsoft 以外の製品を使用してベスト プラクティスの推奨事項を有効にする場合は、改善アクションの設定でこの構成を指定できます。 推奨事項が環境に適用されない場合は、無視するように設定できます。 無視された推奨事項は、スコアの計算には影響しません。

[Image: 改善アクション パネルのスクリーンショット。]

- に対処するには、改善措置が必要であることを認識し、将来のある時点で対処する予定です。 この状態は、部分的に検出されたが完全には完了していないアクションにも適用されます。
- **受け入れられるリスク** - セキュリティは常に使いやすさとバランスを取る必要があり、すべての推奨事項がすべてのユーザーに対して機能するわけではありません。 その場合は、リスクまたは残りのリスクを受け入れ、改善アクションを適用しないことを選択できます。 ポイントは付与されず、そのアクションは改善アクションの一覧に表示されません。 このアクションは履歴で表示することも、いつでも元に戻すことができます。
- **計画済み** - 改善アクションを完了するための具体的な計画があります。
- **サード パーティを通じて解決** され、 **代替の軽減策によって解決された** - 改善アクションは、Microsoft 以外のアプリケーションまたはソフトウェア、または内部ツールによって対処されました。 アクションの価値があるポイントが与えられるので、スコアは全体的なセキュリティ体制をよりよく反映します。 Microsoft 以外のツールまたは内部ツールがコントロールをカバーしなくなった場合は、別の状態を選択できます。 改善アクションがこれらの状態のいずれかとしてマークされている場合、Microsoft は実装の完全性を把握していないことを念頭に置いておきます。

### よく寄せられる質問

多くの要因がスコアに影響を与える可能性があります。 ID セキュリティ スコアについてよく寄せられる質問を次に示します。

#### 推奨事項はどのようにスコア付けされますか?

推奨事項は、2 つの方法でスコア付けできます。 一部はバイナリ形式でスコア付けされるため、推奨事項に基づいて機能または設定が構成されている場合は、スコアの 100% が得られます。 その他のスコアは、構成全体に対する割合として計算されます。 たとえば、すべてのユーザーを MFA で保護すると、最大 10.71% 増加すると推奨事項に示されています。 100 人の合計ユーザーのうち 5 人が保護されているため、約 0.53% (5 保護/ 100 合計 \* 10.71% 最大 = 0.53% 部分スコア) の部分スコアが与えられます。

#### [スコア付けされない] とはどういう意味ですか?

[スコア付けされていない] というラベルの付いたアクションは、組織内で実行できますが、スコア付けされません。 そのため、セキュリティを引き続き向上させることができますが、現時点ではこれらのアクションに対するクレジットは与えられていません。

#### スコアが変更されました。 その理由を理解するにはどうすればよいですか?

[Microsoft Defender XDR ポータル](https://security.microsoft.com/)には、完全な Microsoft セキュリティ スコアが表示されます。 [履歴] タブで詳細な変更を確認することで、セキュリティ スコアに対するすべての変更を簡単に確認できます。

#### スコアは私が被害を受ける可能性を測定しますか?

いいえ。スコアは、侵害される可能性の絶対的な尺度を表していません。 これは、リスクを*相殺*できる機能をどの程度採用しているかを表しています。 どのサービスも保護を保証できません。スコアは、どのような方法でも保証として解釈されるべきではありません。

#### 目指すべき最小スコアはありますか?

特定のスコアに焦点を当てるのではなく、組織に関連する重要度の高い推奨事項に焦点を当てる必要があります。 重要度の高い推奨事項で高スコアを得ることの方が、重要度の低い推奨事項で高スコアを得ることよりも有益です。

#### スコアを解釈する方法

スコアは、推奨されるセキュリティ機能を構成したり、セキュリティ関連のタスク (レポートの読み取りなど) を実行したりするために向上します。 一部のアクションは、ユーザーに対して多要素認証 (MFA) を有効にするなど、部分的な完了に対してスコアが付けられます。 セキュリティ スコアは、使用する Microsoft セキュリティ サービスを直接代表します。 セキュリティは使いやすさとバランスを取る必要があることを覚えておいてください。 すべてのセキュリティ コントロールには、ユーザーに影響を与えるコンポーネントがあります。 ユーザーへの影響が少ないコントロールは、ユーザーの日常業務にほとんど影響を与えないはずです。

#### ID セキュリティ スコアと Microsoft 365 セキュリティ スコアの関係

[Microsoft のセキュリティ スコア](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/microsoft-secure-score)には、次の 5 つの異なるコントロールとスコアのカテゴリが含まれています。

- 同一性
- データ
- デバイス
- インフラストラクチャ
- アプリ

ID セキュリティ スコアは、Microsoft セキュリティ スコアの ID 部分を表します。 この重複は、Id セキュリティ スコアと Microsoft の ID スコアに関する推奨事項が同じであることを意味します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-interactive-sign-ins"} -->
## 対話型のユーザー サインインログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-interactive-sign-ins
- Service: entra-id / monitoring-health
- Article date: 2026-02-09
- Summary: Microsoft Entra の監視と正常性の対話型ユーザー サインイン ログでキャプチャされる情報の種類について説明します。

Microsoft Entra の監視と正常性には、テナントの正常性を監視するのに役立ついくつかの種類のサインイン ログが用意されています。 対話型ユーザー サインインは、Microsoft Entra 管理センターの既定のビューです。

### 対話型ユーザー サインインとは

対話型サインインは、ユーザー "*によって*" 実行されます。 これにより Microsoft Entra ID に認証要素が提供されます。 その認証要素は、Microsoft Authenticator アプリなどのヘルパー アプリと連携することもできます。 ユーザーは、Microsoft Entra ID またはヘルパー アプリに対して、パスワード、MFA チャレンジへの応答、生体認証要素、または QR コードを提供できます。 このログには、Microsoft Entra ID と連携している ID プロバイダーによるフェデレーション サインインも含まれます。

[Image: 対話型ユーザー サインイン ログのスクリーンショット。]

### ログの詳細

次の例は、対話型ユーザー サインイン ログでキャプチャされた情報の種類を示しています。

- ユーザーが Microsoft Entra サインイン画面でユーザー名とパスワードを入力する。
- ユーザーが SMS MFA チャレンジを渡します。
- ユーザーが、Windows Hello for Business で Windows PC のロックを解除するための生体認証ジェスチャを提供します。
- ユーザーが AD FS SAML アサーションを使用して Microsoft Entra ID にフェデレーションする。

対話型サインイン ログには、既定のフィールドに加えて、次の情報も表示されます。

- サインインの場所
- 条件付きアクセスが適用されたかどうか
- テナント間アクセスの詳細 (ホームテナント ID やリソース テナント ID など)

注

サインイン ログのエントリはシステムによって生成されるため、変更または削除することはできません。

### 特別な注意事項

#### ダウンストリーム テナント リソースへのパートナー アクセス

対話型サインイン ログには、パートナーがダウンストリーム テナントのリソースにアクセスするタイミングに関する詳細が含まれるようになりました。 クロス **テナント アクセスの種類**、 **ホーム テナント ID**、リソース **テナント ID** の列を確認すると、既定で表示されるようになりました。パートナーがダウンストリーム テナント リソースにログインするタイミングを確認できます。

- [**クロス テナント アクセスの種類**] 列で**サービス プロバイダー**をフィルター処理して、パートナーのサインインに関連するイベントを分離します。
- **[ホーム テナント ID**] 列と [**リソース テナント ID**] 列の詳細を比較して、パートナーのテナントからダウンストリーム テナントへのサインインを特定します。

#### 対話型サインイン ログでの非対話型サインイン

以前は、可視性を向上するために、一部の非対話型サインインが対話型ユーザー サインイン ログに含まれていました。 2020 年 11 月に非対話型ユーザー サインイン ログが導入される前は、この可視性の向上が必要でした。 FIDO2 キーを含む非対話型サインインは、技術的に非対話型であったとしても、以前は対話型サインインとしてマークされていました。 2025 年 4 月 11 日の時点で、FIDO2 キーを使用して更新トークンを取得するすべての新しいサインインが非対話型サインイン ログに記録されるようになりました。

#### パススルー サインイン

Microsoft Entra ID により、認証と認可のためのトークンが発行されます。 状況によっては、Contoso テナントにサインインしているユーザーが、アクセス権のない Fabrikam テナント内のリソースにアクセスしようとする場合があります。 パススルー トークンと呼ばれる承認なしトークンが Fabrikam テナントに発行されます。 パススルー トークンでは、ユーザーがリソースにアクセスすることはできません。

以前は、この状況のログを確認すると、ホーム テナント (このシナリオでは Contoso) のサインイン ログには、サインイン試行は表示されませんでした。これは、"トークンがクレームを使用してリソースへのアクセスを許可していなかった" ためです。 サインイン トークンは、適切なエラー メッセージを表示するためにのみ使用されました。

パススルー サインインの試行が、ホーム テナントのサインイン ログと関連するテナント制限のサインイン ログに表示されるようになりました。 この更新により、ユーザーからのユーザー サインイン試行の可視性が向上し、テナント制限ポリシーに関するより深い分析情報が得られます。

`crossTenantAccessType` プロパティには、パススルー サインインを区別するための `passthrough` が表示されるようになり、Microsoft Entra 管理センターと Microsoft Graph で使用できます。

#### ファーストパーティーのアプリ専用サービス プリンシパル サインイン

サービス プリンシパル サインイン ログには、ファーストパーティーのアプリ専用サインイン アクティビティは含まれません。 この種類のアクティビティは、ファーストパーティー アプリで、ユーザーからの指示やコンテキストがない内部 Microsoft ジョブのトークンを取得するときに発生します。 これらのログは除外されるため、テナント内の内部 Microsoft トークンに関連するログに対して料金を支払うことはありません。

`MicrosoftGraphActivityLogs` を使用して `SignInLogs` を同じ Log Analytics ワークスペースにルーティングする場合、サービス プリンシパル サインインに関連しない Microsoft Graph イベントが特定される可能性があります。 この統合により、Microsoft Graph API 呼び出しのために発行されたトークンをサインイン アクティビティと相互参照できるようになります。 サインイン ログの `UniqueTokenIdentifier` と Microsoft Graph アクティビティ ログの `SignInActivityId` は、サービス プリンシパル サインイン ログにはありません。

#### 条件付きアクセス

条件付きアクセスのサインインに、**[適用されていません]** が示される場合、その意味を解釈するのは困難な場合があります。 サインインが中断された場合、そのサインインはログに表示されますが、条件付きアクセスの **[適用されていません]** が表示されます。 もう 1 つのよくあるシナリオは、Windows Hello for Business へのサインインです。 ユーザーが条件付きアクセスによって保護されているクラウド リソースではなく、デバイスにサインインしているため、このサインインには条件付きアクセスが適用されません。

### TimeGenerated フィールド

サインイン ログを Azure Monitor ログと Log Analytics と統合している場合、ログの `TimeGenerated` フィールドがサインインの発生時刻と一致していないことに気付く場合があります。 この不一致は、ログが Azure Monitor に取り込まれる方法が原因です。 `TimeGenerated` フィールドは、サインインが発生した時刻ではなく、Log Analytics によってエントリが受信および発行された時刻です。 ログの `CreatedDateTime` フィールドには、サインインが発生した時刻が表示されます。

同様に、危険なサインイン イベントは、サインインが発生したときではなく、危険なイベントが検出された時刻として `TimeGenerated` も表示されます。 実際のサインイン時間を調べるには、`CorrelationId` を使用してログでサインイン イベントを見つけ、サインイン時間を見つけることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations"} -->
## Microsoft Entra アクティビティ ログ統合オプション - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations
- Service: entra-id / monitoring-health
- Article date: 2025-05-27
- Summary: Microsoft Entra アクティビティ ログとストレージおよび分析ツールを統合するためのオプションと考慮事項の概要。

Microsoft Entra ID の**診断設定**を使って、長期のデータ保持と分析情報のために、アクティビティ ログを複数のエンドポイントにルーティングできます。 ストレージのログをアーカイブし、セキュリティ情報イベント管理 (SIEM) ツールにルーティングし、ログを Azure Monitor ログと統合できます。

これらの統合を使用すると、接続データに対する高度な視覚化、監視、およびアラートを有効化できます。 この記事では、統合の種類やアクセス方法ごとに推奨される使用方法について説明します。 Microsoft Entra アクティビティ ログをさまざまなエンドポイントに送信するためのコストに関する考慮事項についても説明します。

### サポートされるレポート

次のログは、多くのエンドポイントのいずれかと統合できます。

- [**監査ログ**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)を使用すると、テナントで実行されたすべてのタスクの履歴にアクセスできます。
- [**サインイン ログ**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)を使用すると、ユーザーがアプリケーションへのサインインを試みるタイミングや、サインイン エラーのトラブルシューティングを行うタイミングを確認できます。
- [**プロビジョニング ログを**](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics)使用すると、Microsoft 以外のすべてのアプリケーションで、どのユーザーが更新され、削除されたかを監視できます。
- [**危険なユーザー レポート**](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports#risky-users)は、ユーザー リスク レベルと修復アクティビティの変化を監視するのに役立ちます。
- [**リスク検出レポート**](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports#risk-detections)を使用すると、ユーザーのリスク検出を監視し、組織内で検出されたリスク アクティビティの傾向を分析できます。

### 統合オプション

ストレージまたは分析のために Microsoft Entra アクティビティ ログを統合するための適切な方法を選択するには、実行しようとしている全体的なタスクについて検討してください。 オプションを 3 つのメイン カテゴリにグループ化します。

- トラブルシューティング
- 長期ストレージ
- 分析と監視

#### 基本的なトラブルシューティング

基本的なトラブルシューティング タスクを実行していても、ログを 30 日以上保持する必要がない場合は、Microsoft Entra 管理センターまたは Microsoft Graph API を使用してアクティビティ ログにアクセスすることをお勧めします。 シナリオのログをフィルター処理し、必要に応じてエクスポートまたはダウンロードできます。

トラブルシューティング タスク * および * を実行していて、ログを 30 日以上保持する必要がある場合は、長期的なストレージ オプションを確認してください。

#### 長期ストレージ

トラブルシューティング タスクを実行して*、*ログを 30 日以上保持する必要がある場合は、Azure ストレージ アカウントにログをエクスポートします。 このオプションは、そのデータのクエリを頻繁に実行する予定がない場合や、コンプライアンス目的でログを保存する必要がある場合に最適です。

30 日を超えて保持しているデータにクエリを実行する必要がある場合は、分析と監視のオプションを確認してください。

#### 分析と監視

シナリオでデータを 30 日以上保持する必要があり、*さらに*そのデータの定期的なクエリを計画している場合は、分析と監視のためにデータを SIEM ツールと統合するためのオプションがいくつかあります。

Microsoft 以外の SIEM ツールを使用する場合は、データをストリーミングできる Event Hubs 名前空間とイベント ハブを設定することをお勧めします。 イベント ハブを使用すると、サポートされている SIEM ツールのいずれかにログをストリーミングできます。

サードパーティの SIEM ツールを使用する予定がない場合は、Microsoft Entra アクティビティ ログを [Azure Monitor ログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/data-platform-logs)に送信することをお勧めします。 この統合により、[Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-analytics-workspace-overview)でアクティビティ ログに対してクエリを実行できます。 ログが Azure Monitor ログと統合されると、Log Analytics でクエリを実行して Workbooks を設定し、さらなる分析とアラートを行うことができます。 ログを保存するためのワークスペースと、Log Analytics および Workbooks と統合するための別のワークスペースを設定することをお勧めします。

Azure Monitor ログに加えて、[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview?tabs=azure-portal) には、凖リアルタイムのセキュリティ検出と脅威ハンティングが用意されています。 後で SIEM ツールと統合する場合は、イベント ハブを介して Microsoft Entra アクティビティ ログを他の Azure データと共にストリーミングできます。

### コストに関する考慮事項

Log Analytics ワークスペースにデータを送信したり、ストレージ アカウント内のデータをアーカイブしたり、ログをイベント ハブにストリーミングしたりするにはコストがかかります。 データの量と発生するコストは、テナントのサイズ、使用中のポリシーの数、時刻によって大きく異なる場合があります。 既存の診断設定を変更すると、新たな料金が発生する場合があります。

エンドポイントにログを送信するためのサイズとコストは予測が困難であるため、予想されるコストを判断する最も正確な方法は、ログを 1 日または 2 日エンドポイントにルーティングすることです。 このスナップショットを使用すると、予想されるコストを正確に予測できます。 また、ログのサンプルをダウンロードし、それに応じて乗算して 1 日分の見積もりを取得することで、コストの見積もりを取得することもできます。

Microsoft Entra ログを Azure Monitor ログに送信する際のその他の考慮事項については、次の Azure Monitor コストの詳細に関する記事を参照してください。

- [Azure Monitor ログのコスト計算と選択肢](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/cost-logs)
- [Azure Monitor のコストと使用量](https://learn.microsoft.com/ja-jp/azure/azure-monitor/usage-estimated-costs)
- [Azure Monitor でコストを最適化する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/best-practices-cost)

Azure Monitor には、Microsoft Entra ID からログを取り込むときに、イベント全体、フィールド、またはフィールドの一部を除外するオプションが用意されています。 このコスト削減機能の詳細については、「[Azure Monitor でのデータ収集の変換](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/data-collection-transformations)」を参照してください。

#### コストを見積もる

組織のコストを見積もるには、ログをエンドポイントと統合するための 1 日あたりのログ サイズまたは 1 日あたりのコストを見積もることができます。

次の要因が、組織のコストに影響する可能性があります。

- 監査ログ イベントでは、約 2 KB のデータ ストレージが使用されます
- サインイン ログ イベントは平均 11.5 KB のデータ ストレージを使用します
- 約 100,000 人のユーザーのテナントでは、1 日あたり約 150 万件のイベントが発生する可能性があります
- イベントは約 5 分間隔でバッチ処理され、その期間のすべてのイベントが含まれる単一のメッセージとして送信されます

#### 1 日のログ サイズ

1 日のログ サイズを見積もるには、ログのサンプルを収集し、テナントのサイズと設定を反映するようにサンプルを調整してから、そのサンプルを [Azure 料金計算ツール](https://azure.microsoft.com/pricing/calculator/)に適用します。

Microsoft Entra 管理センターからログをダウンロードしていない場合は、「[Microsoft Entra ID でログをダウンロードする方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs)」の記事を参照してください。 組織のサイズによっては、見積もりを開始するために別のサンプル サイズを選択する必要がある場合があります。 次のサンプルサイズは始めるための良い基準です。

- 1,000 レコード
- 大規模テナントの場合、15 分のサインイン
- 小規模から中規模のテナントの場合、1時間のログイン活動

また、データ サンプルをキャプチャする場合に、ユーザーの地理的分布とピーク時間も考慮する必要があります。 組織が 1 つのリージョンをベースにしている場合は、サインインがほぼ同時にピークになる可能性があります。 サンプルのサイズと、それに応じてサンプルをキャプチャするタイミングを調整します。

データ サンプルをキャプチャしたら、それに応じて乗算して、1 日あたりのファイルの大きさを確認します。

#### 1 日あたりのコストを見積もる

組織のログ統合にかかるコストを把握するために、1 日または 2 日の統合を有効にできます。 一時的に予算を増加できる場合は、このオプションを使用します。

ログ統合を有効にするには、[アクティビティ ログと Azure Monitor ログの統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)に関する記事の手順に従ってください。 可能であれば、試すログとエンドポイントの新しいリソース グループを作成します。専用のリソース グループがある場合は、コスト分析を簡単に表示し、完了後に削除できます。

統合を有効にした状態で、**[Azure portal]**、&gt;**[コスト管理]**、&gt;**[コスト分析]** の順に移動します。 コストの分析にはいくつかの方法があります。 この[コスト管理クイック スタート](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/costs/quick-acm-cost-analysis)は、作業の開始に役立ちます。 次のスクリーンショットの図は、例として使用されており、実際の金額を反映するものではありません。

[Image: 円グラフで内訳が表示されたコスト分析のスクリーンショット。]

スコープとして新しいリソース グループを使用していることを確認します。 毎日のコストと予測を調べて、ログ統合にかかるコストを把握します。

### 概算コストを計算する

[Azure 料金計算ツール](https://azure.microsoft.com/pricing/calculator/)のランディング ページから、さまざまな製品のコストを見積もることができます。

- [Azure Monitor](https://azure.microsoft.com/pricing/details/monitor/)
- [Azure Storage](https://azure.microsoft.com/pricing/details/storage/blobs/)
- [Azure Event Hubs](https://azure.microsoft.com/pricing/details/event-hubs/)
- [Microsoft Sentinel](https://azure.microsoft.com/pricing/details/microsoft-sentinel/)

エンドポイントに送信される 1 日当たりの GB の見積もりを取得したら、[Azure 料金計算ツール](https://azure.microsoft.com/pricing/calculator/)にその値を入力します。 次のスクリーンショットの図は、例として使用されており、実際の価格を反映するものではありません。

[Image: 例として 1 日当たり 8 GB を使用した、Azure 料金計算ツールのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-managed-identity-sign-ins"} -->
## マネージド ID のサインイン ログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-managed-identity-sign-ins
- Service: entra-id / monitoring-health
- Article date: 2026-02-09
- Summary: Microsoft Entra の監視と正常性のマネージド ID サインイン ログでキャプチャされる情報の種類について説明します。

Azure リソースのマネージド ID によるサインインは、資格情報管理の簡略化のために Azure でシークレットが管理されているリソースによって実行されたサインインです。 マネージド資格情報を持つ仮想マシンは、Microsoft Entra ID を使用してアクセス トークンを取得します。

[Image: マネージド ID サインイン ログのスクリーンショット。]

このレポートに表示されるフィールドをカスタマイズすることはできません。

データを理解しやすくするために、これらのサインイン イベントがグループ化されます。 同じエンティティからのサインインは、1 つの行に集計されます。 行を展開すると、異なる複数のサインインとそれらの異なるタイムスタンプをすべて表示できます。 次のすべてのデータが一致すると、マネージド ID レポートでサインインが集計されます。

- マネージド ID 名または ID
- ステータス
- リソース名または ID

1 つのノードの下にグループ化されているすべてのサインインを表示するには、リスト ビューで項目を選択します。 グループ化された項目を選択すると、サインインの詳細がすべて表示されます。

注

サインイン ログのエントリはシステムによって生成されるため、変更または削除することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-microsoft-entra-health"} -->
## Microsoft Entra Health の監視について - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health
- Service: entra-id / monitoring-health
- Article date: 2026-02-09
- Summary: Microsoft Entra Health を使用して、複数の ID シナリオと認証可用性率を使用してテナントの正常性を監視する

Microsoft Entra Health は、サービス [レベル アグリーメント (SLA)](https://azure.microsoft.com/support/legal/sla/active-directory/v1_1/) に関する継続的な低待機時間の正常性監視とルックバック レポートを通じて、Microsoft Entra テナントの監視を提供します。 低待機時間の正常性監視ソリューションには、シグナルと呼ばれる一連の正常性メトリック データ ストリームが含まれており、IT 運用チームが Microsoft Entra の一般的なシナリオで高いレベルのアップタイムとサービスを維持できるように設計されたアラートが組み込まれています。 SLA の達成は、Microsoft Entra ID のコア認証の可用性を毎月評価するソリューションです。

これらのメトリックとシグナルがペアになると、Microsoft Entra テナントの正常性を包括的に確認できます。 Microsoft Entra Health で提供される情報を定期的に監視すると、傾向、潜在的な問題、テナントの正常性を改善するための領域を特定するのに役立ちます。 また、サービスがテナントのパターンの異常を識別したときにアラートを送信するように電子メール通知を構成することもできます。 この記事では、Microsoft Entra Health の監視機能の概要について説明します。

重要

Microsoft Entra Health シナリオの監視とアラートは現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### Microsoft Entra Health にアクセスする

シナリオの監視と SLA の達成は、Microsoft Entra 管理センターの Microsoft Entra Health 領域で利用できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視と健康**&gt;**健康**にアクセスします。
    - [ **正常性の監視** ] タブには、使用可能な正常性シナリオに関するシグナルとアラートの概要が表示されます。
    - **[SLA 達成**] タブには、1 か月あたりの Microsoft Entra ID のユーザー認証の可用性が表示されます。 詳細については、 [Microsoft Entra ID の SLA パフォーマンスに関する説明を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-sla-performance)参照してください。

### Microsoft Entra Health の監視 (プレビュー) のしくみ

Microsoft Entra Health のシナリオ監視は、シグナルとアラートという 2 つの主要なコンポーネントに基づいて構築されています。 これらが連携するしくみの概要を次に示します。

1. メトリックとデータが収集され、処理され、Microsoft Entra Health 監視に表示される意味のあるシグナルに変換されます。
2. これらの信号は、異常検出サービスに供給されます。
3. 異常検出サービスは、シグナル内のパターンに対する重大な変更を識別すると、アラートをトリガーします。
4. アラートがトリガーされると、テナント管理者によって事前に選択された一連のユーザーに電子メール通知が送信されます。この電子メール通知は、受信者に問題があるかどうかを調査して判断するよう求めます。
5. アラートが表示されたら、考えられる根本原因を調査し、次の手順を決定し、根本原因を軽減するためのアクションを実行する必要があります。 各健康警報には影響評価が含まれており、プロセスを進める際に役立つリソースへのリンクが含まれています。

#### 信号

多くの IT 管理者は、多要素認証 (MFA) を必要とするサインインなど、いくつかの主要なシナリオの調査にかなりの時間を費やしています。 Microsoft Entra Health では、これらのメトリックに関連付けられているデータを視覚化できるため、傾向や潜在的な問題をすばやく特定できます。

Microsoft Entra Health では、次の主要なシナリオを監視できます。

- MFA を必要とする対話型ユーザー サインイン要求。
- 条件付きアクセス ポリシーを使用してマネージド デバイスを必要とするユーザー サインイン要求。
- 条件付きアクセス ポリシーを使用して準拠しているデバイスを必要とするユーザー サインイン要求。
- SAML 認証を使用したアプリケーションへのユーザー サインイン要求。

これらの各シナリオに関連付けられているデータは、そのシナリオに固有のビューに集計されます。 準拠しているデバイスからのサインインにのみ関心がある場合は、他のサインイン アクティビティからノイズを発生させずに、そのシナリオに進むことができます。

[Image: MFA シナリオ監視データのスクリーンショット。]

各シナリオの詳細ページには、過去 30 日間のそのシナリオの傾向と合計が表示されます。 このデータは、テナントの正常性に関する低待機時間の分析情報を得るための 15 分ごとに集計されます。

#### アラート

異常検出サービスは、データを確認し、テナントに固有のパターンに基づいて動的アラートのしきい値を開発します。 サービスは、テナント レベルでそのパターンに対する大幅な変更を識別すると、アラートをトリガーします。 これらのシナリオを定期的に監視し、アラートが発生したときに確認することで、テナントの正常性をより効果的に監視および改善できます。

アラートは、テナントと監視対象のシナリオに固有です。 機械学習では、テナントのパターンを確立するために、少なくとも 4 週間のデータが必要です。 シグナルで収集されるデータが多いほど、異常検出サービスの精度が高くなります。 サービスはタイムラインで 25 分から 30 分を振り返り、信号がパターンから逸脱した場合にアラートをトリガーします。

このサービスでは、次のシナリオに関するアラートが提供されます。

- [条件付きアクセスに準拠したデバイスを必要とするサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/scenario-health-sign-ins-compliant-managed-device)
- [条件付きアクセスマネージド デバイスを必要とするサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/scenario-health-sign-ins-compliant-managed-device)
- [多要素認証 (MFA) を必要とするサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/scenario-health-sign-ins-mfa)
- [条件付きアクセス ブロック ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/scenario-health-conditional-access-block-policy)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-noninteractive-sign-ins"} -->
## 非対話型サインイン ログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-noninteractive-sign-ins
- Service: entra-id / monitoring-health
- Article date: 2026-02-09
- Summary: Microsoft Entra の監視と正常性の非対話型サインイン ログでキャプチャされるアクティビティの種類について説明します。

Microsoft Entra の監視と正常性には、テナントの正常性を監視するのに役立ついくつかの種類のサインイン ログが用意されています。 非対話型サインインは、Microsoft Entra 管理センターのユーザー サインイン ログのサブセットです。

### 非対話型のユーザー サインインとは

非対話型サインインは、ユーザーに "*代わって*" 実行されます。 これらの委任されたサインインは、ユーザーの代わりにクライアント アプリまたは OS コンポーネントによって実行され、ユーザーが認証要素を指定する必要はありません。 代わりに、Microsoft Entra ID によってユーザーのトークンをいつ更新する必要があるかが認識され、ユーザーのセッションを中断することなくバックグラウンドで更新が行われます。 一般に、ユーザーは、これらのサインインがバックグラウンドで実行されていると認識します。

[Image: 非対話型ユーザー サインイン ログのスクリーンショット。]

### ログの詳細

次の例は、非対話型ユーザー サインイン ログでキャプチャされた情報の種類を示しています。

- クライアント アプリで OAuth 2.0 更新トークンを使用してアクセス トークンを取得します。
- クライアントで OAuth 2.0 認証コードを使用して、アクセス トークンと更新トークンを取得します。
- ユーザーは Microsoft Entra に参加している PC で Web または Windows アプリへのシングル サインオン (SSO) を実行します (認証要素を指定したり、Microsoft Entra プロンプトを操作したりする必要はありません)。
- ユーザーが、FOCI (クライアント ID のファミリ) を使用してモバイル デバイス上でセッションを行っているときに、2 つ目の Microsoft Office アプリにサインインします。

非対話型サインイン ログでは、既定の欄に加えて次のものを表示します。

- リソースID
- グループ化されたサインインの数

このレポートに表示されるフィールドをカスタマイズすることはできません。

注

サインイン ログのエントリはシステムによって生成されるため、変更または削除することはできません。

### それはどのように機能しますか?

データを理解しやすくするために、非対話型のサインイン イベントがグループ化されます。 クライアントによって、同じユーザーに代わって非対話型のサインインが多数作成される場合がよくあります。 非対話型サインインは、サインインが試行された時刻を除き、同じ特性を共有します。 たとえば、クライアントがユーザーに代わって 1 時間に 1 回アクセス トークンを取得する場合があります。 ユーザーまたはクライアントの状態が変わらない場合、IP アドレス、リソース、およびその他すべての情報は、各アクセス トークン要求で同じになります。 変更される唯一の状態は、サインインの日時です。

[Image: すべての行を表示するように展開された集計サインインのスクリーンショット。]

Microsoft Entra で日時以外が同じ複数のサインイン情報がログされる場合、それらのサインインは同じエンティティからのものであり、1 つの行に集計されます。 複数回の同一サインインを示す行 (発行された日時を除く) には、[サインイン回数] の列に 1 より大きい値が表示されます。 これらの集計されたサインイン情報は、タイム スタンプが同じであるようにも見えるかもしれません。 **[時間集計]** フィルターは、1 時間、6 時間、または 24 時間に設定できます。 行を展開すると、異なる複数のサインインとそれらの異なるタイムスタンプをすべて表示できます。

非対話型のユーザーの場合、次のデータが一致するとサインインが集計されます。

- アプリケーション
- ユーザー
- IP アドレス
- ステータス
- リソースID

### 特別な考慮事項

- [機密クライアント](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications)によって実行された非対話型サインインの IP アドレスが、更新トークン要求の送信元の実際のソース IP と一致しません。 代わりに、元のトークンの発行に使用された元の IP が表示されます。
- 2025 年 4 月 11 日の時点で、FIDO2 キーを使用して更新トークンを取得するすべての新しいサインインが非対話型サインイン ログに記録されるようになりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-provisioning-logs"} -->
## Microsoft Entra ID でのユーザー プロビジョニング ログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs
- Service: entra-id / monitoring-health
- Article date: 2025-03-17
- Summary: Microsoft 以外のサービスがユーザーをプロビジョニングする際に Microsoft Entra ID のユーザー プロビジョニング ログに追加される詳細について説明します。

Microsoft Entra ID は、複数の Microsoft 以外のサービスと統合して、テナントにユーザーをプロビジョニングします。 プロビジョニングされたユーザーに関する問題を解決する必要がある場合、Microsoft Entra プロビジョニング ログで記録された情報を利用して解決策を見つけることができます。

テナントの正常性を監視するには、他にも次の 2 つのアクティビティ ログを使用できます。

- **[サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)** - サインインとユーザーのリソース使用状況に関する情報。
- **[監査](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)** - ユーザーやグループの管理、テナントのリソースに適用された更新など、テナントに適用された変更に関する情報。

この記事では、Microsoft 以外のサービスによるユーザー プロビジョニングをキャプチャするログの概要について説明します。

### プロビジョニング ログを使用して行えること。

プロビジョニング ログを使うと、次のような疑問を解決することができます。

- ServiceNow で正常に作成されたグループ
- Adobe から正常に削除されたユーザー
- Active Directory で正常に作成された Workday のユーザー

注

プロビジョニング ログのエントリはシステムによって生成されるため、変更または削除することはできません。

### ログに表示される情報の種類

ログには、ID、実行されたアクション、ソース システム、ターゲット システム、プロビジョニング イベントの状態が表示されます。 詳しいトラブルシューティングのために他の列を追加することもできますが、以下の詳細が標準です。

[Image: さまざまな詳細を示すプロビジョニング ログのスクリーンショット。]

- **ID**: プロビジョニングされている ID の表示名とソース ID がこの列に表示されます。
- **アクション**: Create、Update、Delete、Disable、StagedDelete、Other などの値があります。
    - Other の例としては、ソースとターゲットのシステムの詳細が既に一致しているため変更が行われなかった場合などがあります。
- **ソース システム**と**ターゲット システム**: これらの詳細は、ID の取得元のシステムとプロビジョニング先のシステムを示します。
- **状態**: Success、Failure、Skipped、Warning などの値があります。
    - Skipped の状態がトリガーされる可能性のあるシナリオは、いくつかあります。 それらのシナリオについて詳しくは、[ユーザーがプロビジョニングされていない](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned)場合に関する記事を参照してください

プロビジョニング ログで項目を選択すると、その項目の詳細が表示されます。たとえば、ユーザーのプロビジョニング手順や問題の解決に関するヒントなどです。 詳細は 4 つのタブにグループ化されています。

- **ステップ**: このタブには、オブジェクトをプロビジョニングするために実行される手順の概要が示されます。 オブジェクトのプロビジョニングには次の手順が含まれる可能性がありますが、すべての手順がすべてのプロビジョニング イベントに当てはまるわけではありません。

    - オブジェクトをインポートする。
    - ソースとターゲットの間でオブジェクトを一致させる。
    - オブジェクトがスコープ内にあるかどうかを判断する。
    - 同期の前にオブジェクトを評価します。
    - オブジェクト (作成、更新、削除、または無効化) をプロビジョニングします。

    [Image: [手順] タブのプロビジョニング手順を示すスクリーンショット。]
- **トラブルシューティングと推奨事項**: エラーが発生した場合、このタブにはエラー コードと理由が表示されます。 多くの場合、エラーの詳細な説明が表示されます。 この情報を確認して問題を理解し、提供されているガイダンスに従って問題を解決してください。 次のトラブルシューティングに関する記事を参照してください。

    - [人事でのユーザー作成に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-creation-issues)
    - [HR ユーザーの更新に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-update-issues)
    - [不十分なアクセス権によるエラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/insufficient-access-rights-error-troubleshooting)
- **変更されたプロパティ**: 変更があった場合、このタブには古い値と新しい値が表示されます。
- **概要**:ソースとターゲットのシステムのオブジェクトの発生内容の概要と識別子が表示されます。

### プロビジョニング ログ、ブック、Log Analytics の使用

Log Analytics とブックのクエリ機能とアラート機能を使用すると、カスタム レポートとアラートを作成できます。 開始するには、 [Log Analytics ワークスペースを作成する必要があります](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace#create-a-log-analytics-workspace)。 ワークスペースを作成したら、そのワークスペースにログをストリーミングして、Log Analytics とブックのデータのクエリと分析を行うことができます。

詳細については、「 [プロビジョニング ログと Azure Monitor ログの統合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics)」を参照してください。

ログのプロビジョニングに使用できるブック テンプレートは 2 つあります。

- **プロビジョニング分析** では、テナント内のプロビジョニング イベントの概要を示します。
- **Provisioning Insights** では、他のソースからのユーザーの同期に関連するイベントの詳細が提供されるため、これらのイベントを 1 か所で分析できます。 詳細については、「 [プロビジョニング分析情報ブック](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-workbook)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-service-principal-sign-ins"} -->
## サービス プリンシパルのサインイン ログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-service-principal-sign-ins
- Service: entra-id / monitoring-health
- Article date: 2025-03-17
- Summary: Microsoft Entra の監視と正常性のサービス プリンシパル サインイン ログでキャプチャされたアクティビティについて説明します。

対話型および非対話型のユーザー サインインとは異なり、サービス プリンシパルのサインインにはユーザーが関与しません。 そうではなく、アプリやサービス プリンシパルなど、非ユーザー アカウントによるサインインです (ただし、マネージド ID のサインインは除きます。これは、マネージド ID サインイン ログにのみ記録されます)。 これらのサインインにおいては、アプリまたはサービスによって、それ自身の資格情報 (認証またはリソースへのアクセスのための証明書やアプリ シークレットなど) が提供されます。

[Image: サービス プリンシパルのサインイン ログのスクリーンショット。]

### ログの詳細

次の例は、サービス プリンシパルのサインイン ログでキャプチャされた情報の種類を示しています。

- サービス プリンシパルによる証明書を使用した認証が行われ、Microsoft Graph にアクセスされます。
- アプリケーションでクライアント シークレットを使用して、OAuth クライアント資格情報フローでの認証が行われます。

このレポートに表示されるフィールドをカスタマイズすることはできません。

注

サインイン ログのエントリはシステムによって生成されるため、変更または削除することはできません。

### それはどのように機能しますか?

サービス プリンシパル サインイン ログのデータを簡単に理解できるように、サービス プリンシパルのサインイン イベントはグループ化されます。 同じ条件下での同じエンティティからのサインインが 1 つの行に集計されます。 行を展開すると、異なる複数のサインインとそれらの異なるタイムスタンプをすべて表示できます。 次のデータが一致すると、サービス プリンシパル レポートでサインインが集計されます。

- サービス プリンシパル名または ID
- ステータス
- IP アドレス
- リソース名または ID
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-sign-in-log-activity-details"} -->
## サインイン ログのアクティビティの詳細について - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details
- Service: entra-id / monitoring-health
- Article date: 2026-03-04
- Summary: Microsoft Entra サインイン ログのアクティビティの詳細で各タブに表示される情報について説明します。

Microsoft Entra では、コンプライアンス上の目的のために Azure テナントへのすべてのサインインがログに記録されます。 IT 管理者は、ログの値を正しく解釈できるように、サインイン ログの値の意味を把握しておく必要があります。

- [サインイン ログの詳細を確認する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [サインイン ログをカスタマイズおよびフィルター処理する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-customize-filter-logs)

この記事では、サインイン ログに記録される値について説明します。 これらの値を確認することで、サインイン エラーのトラブルシューティングに役立つ情報が得られます。

### サインイン アクティビティの各種コンポーネント

Microsoft Entra ID のサインイン アクティビティは、以下の 3 つの主要コンポーネントで構成されます。

- **対象者:** サインインを実行する本人 (ユーザー)。
- **方法:** アクセスに使用されるクライアント (アプリケーション)。
- **内容:** 本人によってアクセスされるターゲット (リソース)。

サインインを調査する際は、これら 3 つのコンポーネントを中心に検索範囲を絞り込むことで、詳細すべてを確認する必要がなくなります。 これら 3 つのコンポーネントには、それぞれ関連する識別子があり、その識別子からさらに詳しいことがわかる場合があります。 また、各サインインには、サインインの試行を付随アクティビティに関連付ける一意の識別子も含まれています。

#### 担当者

以下の詳細情報がユーザーに関連付けられています。

- ユーザー
- Username
- ユーザーID
- サインイン識別子
- ユーザー タイプ

#### どのように

以下の詳細情報を確認することで、ユーザーのサインイン方法を特定できます。

- 認証の要件
- クライアント アプリ
- クライアント資格情報の種類
- 継続的アクセス評価

#### 対象

以下の詳細情報を確認することで、ユーザーがアクセスしようとしているリソースを特定できます。

- Application
- アプリケーション ID
- リソース
- リソースID
- リソース テナント ID
- リソース サービス プリンシパル ID

#### 一意識別子

サインイン ログには、サインインの試行に関する詳細な分析情報を確認できる一意の識別子もいくつか含まれています。

- **関連付け ID:** 関連付け ID は、同じサインイン セッションからのサインインをグループにします。 この値はクライアントから渡されるパラメーターに基づいているため、Microsoft Entra ID では正確さを保証できません。
- **要求 ID:** 発行されたトークンに対応する識別子です。 特定のトークンを使用したサインインを探す場合は、まず、トークンから要求 ID を抽出する必要があります。
- **一意のトークン識別子:** サインイン時に渡されるトークンの一意の識別子です。 この識別子は、サインインとトークン要求を関連付けるために使用されます。

### サインイン アクティビティの詳細

サインインの試行ごとに、これら 3 つの主要コンポーネントに関連付けられた詳細情報が含まれています。 その詳細情報は、サインインの種類に基づいて複数のタブに整理されています。

#### 基本情報

[基本情報] タブには、サインインの試行に関連付けられている詳細情報の大部分が含まれています。 サインインに関する問題をトラブルシューティングする場合に必要になることがあるため、一意の識別子はメモしておいてください。 [基本情報] タブの詳細情報を元に、*対象者*、*方法*、*内容*のパターンを追跡できます。

[基本情報] タブからサインイン診断を開始することもできます。詳細については、[サインイン診断の使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-sign-in-diagnostics)に関するページを参照してください。

##### サインインのエラー コード

サインインに失敗した場合は、関連するログ項目の [基本情報] タブで、理由に関する詳細情報を取得できます。 エラー コードと関連するエラーの理由が詳細に表示されます。 詳細については、[サインイン エラーのトラブルシューティング方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-troubleshoot-sign-in-errors)に関するページを参照してください。

[Image: [基本情報] タブのサインイン エラー コードのスクリーンショット。]

#### 場所とデバイス

**[場所]** タブと **[デバイス情報]** タブには、ユーザーの場所と IP アドレスに関する一般情報が表示されます。 [デバイス情報] タブには、サインインに使用されたブラウザーとオペレーティング システムの詳細が表示されます。 このタブには、デバイスがポリシーに準拠しているか、マネージド デバイスであるか、Microsoft Entra ハイブリッドに参加済みかについての詳細も表示されます。

#### 認証の詳細

サインイン ログの詳細にある **[認証の詳細]** タブには、各認証の試行に関する次の情報が表示されます。

- 適用された認証ポリシーの一覧 (条件付きアクセス、セキュリティの既定値群など)。
- サインインに使用された認証方法のシーケンス。
- 認証の試行が成功したかどうかとその理由。

この情報を使用すると、ユーザーのサインインの各ステップをトラブルシューティングできます。 これらの詳細を使用して、次の情報を追跡します。

- MFA によって保護されたサインインの回数。
- 各認証方法の使用状況と成功率。
- パスワードレス認証方法の使用状況 (パスワードレス電話サインイン、FIDO2 など)。
- トークン クレームによって認証要件が満たされる頻度 (ユーザーが対話形式でパスワードの入力や SMS OTP の入力を求められない場合など)。

[Image: [認証の詳細] タブのスクリーンショット。]

#### 条件付きアクセス

テナントで条件付きアクセス ポリシーが使用されている場合は、それらのポリシーがサインイン試行に適用されたかどうかを確認できます。 サインインに適用できるすべてのポリシーが一覧表示されます。 ポリシーの最終結果が表示されるため、サインインの試行にポリシーが影響を与えたかどうかをすぐに確認できます。

- **成功：** 条件付きアクセス ポリシーがサインイン試行に正常に適用されました。
- **失敗：** 条件付きアクセス ポリシーがサインイン試行に適用されましたが、サインイン試行は失敗しました。
- **Not Applied:**サインインは、適用されるポリシーの条件と一致しませんでした。
    - その性質上、完了できない循環依存関係 (鶏と卵のシナリオ) を防ぐために、条件付きアクセスの評価から除外する必要がある特定のシナリオがあります。 これらのシナリオは "ブートストラップ シナリオ" と見なされ、デバイス登録、デバイス コンプライアンス、またはネットワーク ポリシー サーバー コネクタに関連付けられているサインインが含まれる場合があります。
    - Windows Hello for Business サインインは "Not Applied" と表示されます。これは、条件付きアクセス ポリシーで保護されるのは、Windows サインイン プロセスではなく、クラウド リソースへのサインイン試行であるためです。
- **Disabled:** サインインの試行時にポリシーが無効になりました。

#### レポート専用

条件付きアクセス ポリシーは、ユーザーのサインイン エクスペリエンスを変更し、プロセスを中断する可能性があります。 ポリシーが正しく構成されていることを確認するために、一定期間、 **レポート専用** モードで条件付きアクセス ポリシーを構成することをお勧めします。 **レポート専用**モードでは、ポリシーを実際に有効にする前に、構成したポリシーの潜在的な影響を評価できます。

サインイン ログのこのタブには、ポリシーの対象となるサインイン試行の結果が表示されます。 詳細については、[条件付きアクセスのレポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only)に関するページを参照してください。

### サインインの詳細と考慮事項

サインイン ログを確認する際は、以下のシナリオを考慮することが重要です。

- **IP アドレスと場所:** IP アドレスと、そのアドレスを持つコンピューターが物理的に配置されている場所の間に明確な関連性がありません。 モバイル プロバイダーや VPN は、クライアント デバイスが使用されている場所から遠く離れていることが多い中央プールから IP アドレスを発行します。 現時点では、トレース、レジストリ データ、逆引きなどの情報に基づいて IP アドレスを物理的な場所に変換するのがいいでしょう。
- **日時:** サインイン試行の日時は、サインインを試みたユーザーのタイム ゾーンではなく、Microsoft Entra 管理センターにサインインした担当者のタイム ゾーンにローカライズされます。
- **条件付きアクセス:**

    - `Not applied`: サインイン時にユーザーおよびアプリケーションに適用されたポリシーはありません。 Windows Hello for Business の場合は "未適用" と表示されます。これは、条件付きアクセス ポリシーで保護されるのは、Windows サインイン プロセスではなく、クラウド リソースへのサインイン試行であるためです。 他のサインインが中断される可能性があるため、ポリシーは適用されません。
    - `Success`: サインイン時に 1 つ以上の条件付きアクセス ポリシーがユーザーとアプリケーションに対して適用または評価されました (ただし、必ずしも他の条件が適用されたとは限りません)。 条件付きアクセス ポリシーが適用されない場合でも、評価された場合は、条件付きアクセスの状態に *[Success]* と表示されます。
    - `Failure`: サインインによって少なくとも 1 つの条件付きアクセス ポリシーのユーザーとアプリケーションの条件が満たされたうえで、制御の許可が満たされていないか、またはアクセスをブロックするように設定されています。
    - 条件付きアクセスは、Windows Hello for Business などの Windows サインインには適用されません。 条件付きアクセスで保護されるのは、デバイスのサインイン プロセスではなく、クラウド リソースへのサインイン試行です。
- **継続的アクセス評価:** 継続的アクセス評価 (CAE) がサインイン イベントに適用されたかどうかが表示されます。

    - サインイン要求は認証ごとに複数あり、対話型タブに表示されるものもあれば、非対話型タブに表示されるものもあります。
    - CAE は、それらの要求のうち 1 つについてのみ true と表示され、対話型タブまたは非対話型タブに表示されます。
    - 詳細については、「[Microsoft Entra ID の継続的アクセス評価を使用してサインインの監視とトラブルシューティングを行う](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-continuous-access-evaluation-troubleshoot)」を参照してください。
- **クロステナント アクセスの種類:** アクターがリソースにアクセスするために使用するクロステナント アクセスの種類を表します。 次のいずれかの値になります。

    - `none` - Microsoft Entra テナントの境界を越えなかったサインイン イベント。
    - `b2bCollaboration`- B2B Collaboration を使用してゲスト ユーザーによって実行されるクロステナント サインイン。
    - `b2bDirectConnect` - B2B によって実行されるクロステナント サインイン。
    - `microsoftSupport`- Microsoft 外部のテナントで Microsoft サポート エージェントによって実行されるクロステナント サインイン。
    - `serviceProvider` - テナント内のクラウド サービス プロバイダー (CSP) の顧客に代わって、CSP または同様の管理者によって実行されるクロステナント サインイン。
    - `passthrough` - 資格情報の再入力を必要とせずに、認証がユーザーのホーム ID プロバイダーに転送されるテナント間サインイン。
    - `unknownFutureValue` - クライアントが列挙リストの変更を処理するのに役立つ MS Graph によって使用される値。 詳細については、「[Microsoft Graph の操作に関するベスト プラクティス](https://learn.microsoft.com/ja-jp/graph/best-practices-concept)」を参照してください。
- **テナント:** サインイン ログは、クロステナントのシナリオに関連する 2 つのテナント識別子を追跡します。

    - **ホーム テナント** -ユーザー ID を所有するテナント。 Microsoft Entra ID では、ID と名前を追跡します。
    - **リソース テナント** - (ターゲット) リソースを所有するテナント。
    - Microsoft Entra ID では、プライバシーに関するコミットメントにより、クロステナント シナリオで ホーム テナント名の入力は行われません。
    - テナントの外部のユーザーがリソースにアクセスしていることを確認するには、ホーム テナントがリソース テナントと一致しないすべてのエントリを選択します。
- **多要素認証:** ユーザーが多要素認証 (MFA) を使用してサインインすると、実際には複数の個別の MFA イベントが発生します。 たとえば、ユーザーが間違った検証コードを入力した場合、または時間内に応答しなかった場合、サインイン試行の最新の状態を反映するために、さらに MFA イベントが送信されます。 これらのサインイン イベントは、Microsoft Entra サインイン ログに 1 つの行項目として表示されます。 ただし、Azure Monitor で同じサインイン イベントは、複数の行項目として表示されます。 これらのイベントにはすべて同じ `correlationId` があります。
- **認証要件:** 通常、サインインを成功させるためにリソース プロバイダーによって要求された認証要件が表示されます。 この値は通常、サインイン中に到達した認証ステージを反映しますが、次のような特殊なケースがあります。

    - プライマリ認証が成功し、MFA が必要な失敗後の後続のサインイン試行では、値は `multiFactorAuthentication`。
    - このフィールドには、サインインプロセス中に達成された認証が反映されない場合があります。 たとえば、以前の MFA 要求から要件が既に満たされている場合、リソース プロバイダーは要求しません。
    - MFA が必要であるが、プライマリ認証が失敗したサインイン試行の場合、MFA を要求するための条件付きアクセスによって試行が評価されなかったため、値は `singleFactorAuthentication` されます。
    - Graph API では、`$filter` (`eq` と `startsWith` 演算子のみ) がサポートされています。
- **サインイン イベントの種類:** イベントが表すサインインのカテゴリを示します。

    - ユーザー サインイン カテゴリは `interactiveUser` または `nonInteractiveUser` であり、サインイン リソースの **isInteractive** プロパティの値に対応します。
    - マネージド ID カテゴリは `managedIdentity` です。
    - サービス プリンシパル カテゴリは **servicePrincipal** です。
    - Microsoft Graph API では、`$filter` (`eq` 演算子のみ) がサポートされています。
    - Azure portal にはこの値は表示されませんが、サインイン イベントは、そのサインイン イベントの種類と一致するタブに配置されます。 使用できる値:
        - `interactiveUser`
        - `nonInteractiveUser`
        - `servicePrincipal`
        - `managedIdentity`
        - `unknownFutureValue`
- **ユーザーの種類:** 例として、`member`、`guest`、`external` などがあります。
- **認証の詳細:**

    - **OATH 検証コード**は、OATH ハードウェア トークンとソフトウェア トークン (Microsoft Authenticator アプリの認証など) の両方で認証方法として記録されます。
    - **[認証の詳細]**タブでは、ログ情報が完全に集計されるまで、最初のうちは不完全または不正確なデータが表示されることがあります。 たとえば、次のようなことが確認されています。
        - サインイン イベントが最初にログに記録された場合、 **[トークンの要求によって満たされました]** というメッセージが正しく表示されません。
        - **[プライマリ認証]** の行が最初はログに記録されません。
    - ログの詳細がわからない場合は、**要求 ID** と**関連付け ID** を収集して、さらなる分析またはトラブルシューティングに使います。
    - 認証またはセッションの有効期間に関する条件付きアクセス ポリシーが適用されている場合は、サインイン試行の上に表示されます。 どちらも表示されない場合、そのようなポリシーは現在適用されていません。 詳細については、[条件付きアクセスのセッション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)に関するページを参照してください。
- **TimeGenerated と CreatedDateTime:**

    - Log Analytics ワークスペースにサインイン ログを送信する場合、同じサインイン イベントに対して 2 つの異なるタイムスタンプが表示されることがあります。
    - `TimeGenerated` フィールドは、サインイン イベント レコードが取り込まれ、Log Analytics に格納された時刻です。 ログが処理され、ワークスペースで使用可能になった日時が反映されます。
    - `CreatedDateTime` フィールドは、認証イベントが発生し、サインインが Microsoft Entra ID によって処理されたときのタイムスタンプです。 サインイン イベント自体のタイムスタンプではありません。
    - 2 つのタイムスタンプの違いは、サインイン イベントが処理されて Log Analytics に送信されるまでの時間が原因です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-sign-ins"} -->
## Microsoft Entra ID のサインイン ログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins
- Service: entra-id / monitoring-health
- Article date: 2025-11-07
- Summary: Microsoft Entra の監視と正常性で使用できるさまざまな種類のサインイン ログについて説明します。

Microsoft Entra では、内部アプリとリソースを含む Microsoft Entra テナントへのすべてのサインインが記録されます。 IT 管理者は、ログの値を正しく解釈できるように、サインイン ログの詳細についての意味を把握しておく必要があります。

サインイン エラーとパターンを確認すると、ユーザーがアプリケーションやサービスにアクセスする方法について貴重な分析情報を得ることができます。 Microsoft Entra ID によって提供されるサインイン ログは、分析できる強力な種類の [アクティビティ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health) です。 この記事では、サインイン ログのいくつかの重要な側面について説明します。

テナントの正常性を監視するには、他にも次の 3 つのアクティビティ ログを使用できます。

- **[監査](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)** – テナントに適用された変更 (ユーザーやグループの管理、テナントのリソースに適用された更新など) に関する情報。
- **[サインアップ (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ups)** - [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations) の場合のみ、サインアップの成功や失敗など、すべてのセルフサービス サインアップ試行に関する情報。
- **[プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)** – ServiceNow でのグループの作成や Workday からインポートされたユーザーなど、プロビジョニング サービスによって実行されるアクティビティ。

### サインイン ログから得られる情報

サインイン ログを使用して、次のような質問への回答を得ることができます。

- 今週、特定のアプリケーションにサインインしたユーザーは何人か?
- 過去 24 時間に失敗したサインイン試行は何回発生したか?
- ユーザーは特定のブラウザーまたはオペレーティング システムからサインインしているか?
- マネージド ID とサービス プリンシパルによってアクセスされた Azure リソースはどれか?

次の詳細を特定することで、サインイン要求に関連付けられているアクティビティを記述することもできます。

- **Who** – サインインを実行している ID (ユーザー)。
- **方法** – サインインに使用されるクライアント (アプリケーション)。
- **What** – ID によってアクセスされるターゲット (リソース)。

注

サインイン ログのエントリはシステムによって生成されるため、変更または削除することはできません。

### サインイン ログへのアクセス方法

ニーズに応じて、ログにアクセスする方法はいくつかあります。 詳細については、「 [アクティビティ ログにアクセスする方法」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)参照してください。

Microsoft Entra 管理センターからサインイン ログを表示するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **[Entra ID]**&gt;**[監視 & 正常性]**&gt;**[サインイン ログ]** に移動します。

Microsoft Entra 管理センターでサインイン ログをより効果的に使用するには、特定のログ セットのみを表示するようにフィルターを調整します。 詳細については、「 [サインイン ログのフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-customize-filter-logs)」を参照してください。

### サインイン ログの種類

サインイン ログ プレビューには、次の 4 種類のログがあります。

- [対話型ユーザー サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-interactive-sign-ins)
- [非対話型ユーザー サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-noninteractive-sign-ins)
- [サービス プリンシパルのサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-service-principal-sign-ins)
- [マネージド ID サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-managed-identity-sign-ins)

従来のサインイン ログ エクスペリエンスには、対話型ユーザー サインインのみが含まれます。

#### エージェント ログ

エージェント アクティビティ ログは、エージェント関連のアクティビティを監視するのに役立つ、監査ログとサインイン ログの新しいログの種類です。 IT 管理者は、Microsoft Entra 管理センターで Microsoft Graph API を使用して、エージェント アクティビティを直接表示および管理できます。 詳細については、 [Microsoft Entra エージェント ID ログ](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-professional/sign-in-audit-logs-agents)を参照してください。

### 他のサービスで使用されるサインイン データ

サインイン データは、危険なサインインを監視し、アプリケーションの使用状況に関する分析情報などを提供するために、Azure と Microsoft Entra の複数のサービスによって使用されます。

#### Microsoft Entra ID Protection（マイクロソフト エントラ ID 保護）

危険なサインインに関連するサインイン ログ データの視覚化は、次のデータを使用する **Microsoft Entra ID Protection** の概要で入手できます。

- 危険なユーザー
- 危険なユーザーのサインイン
- 危険なワークロード ID

Microsoft Entra ID Protection ツールの詳細については、 [Microsoft Entra ID Protection の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)を参照してください。

#### Microsoft Entra の使用状況と分析情報

アプリケーション固有のサインイン データを表示するには、 **Microsoft Entra ID**&gt;**Monitoring & health**&gt;**Usage & insights** を参照します。 これらのレポートでは、Microsoft Entra アプリケーション アクティビティと AD FS アプリケーション アクティビティのサインインを詳細に確認できます。 詳細については、 [Microsoft Entra の使用状況と分析情報に関する説明を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report)参照してください。

[Image: 使用状況と分析情報レポートのスクリーンショット。]

**使用状況と分析情報**には複数のレポートがあります。 これらのレポートの一部はプレビュー段階です。

- Microsoft Entra アプリケーション アクティビティ (プレビュー)
- AD FS アプリケーション アクティビティ
- 認証方法のアクティビティ
- サービス プリンシパル サインイン ログ
- アプリケーション資格情報のアクティビティ

#### Microsoft 365 のアクティビティ ログ

Microsoft 365 のアクティビティ ログは [、Microsoft 365 管理センター](https://learn.microsoft.com/ja-jp/microsoft-365/admin/admin-overview/admin-center-overview)から表示できます。 Microsoft 365 アクティビティおよび Microsoft Entra アクティビティ ログでは非常に多くのディレクトリ リソースが共有されます。 Microsoft 365 のアクティビティ ログがすべて表示されるのは、Microsoft 365 管理センターだけです。

Office 365 管理 API を使用して、Microsoft [365](https://learn.microsoft.com/ja-jp/office/office-365-management-api/office-365-management-apis-overview) アクティビティ ログにプログラムでアクセスできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-sign-ups"} -->
## Microsoft Entra External ID のサインアップ ログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ups
- Service: entra-id / monitoring-health
- Article date: 2025-06-30
- Summary: Microsoft Entra External ID の監視と正常性で使用できるサインアップ ログについて説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra External ID は、サインアップの成功と失敗した試行の両方を含め、すべてのセルフサービス サインアップ イベントをログに記録します。 ログには、組織がサインアップ プロセスを最適化し、ユーザー エクスペリエンスを強化し、全体的な顧客エンゲージメントを向上させるのに役立つ情報が含まれています。 この記事では、サインアップ ログにアクセスして使用する方法について説明します。

Microsoft Entra External ID によって提供されるサインアップ ログは、分析できる強力な種類の [アクティビティ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health) です。 外部 ID サインアップ ログに加えて、外部テナントの正常性を監視するために、他の 3 つのアクティビティ ログも使用できます。

- **[サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)** - サインインとユーザーのリソース使用状況に関する情報。
- **[監査](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)** – テナントに適用された変更 (ユーザーやグループの管理、テナントのリソースに適用された更新など) に関する情報。
- **[プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)** – ServiceNow でのグループの作成や Workday からインポートされたユーザーなど、プロビジョニング サービスによって実行されるアクティビティ。

### サインアップ ログから得られる情報

サインアップ ログを使用して、次の情報を見つけることができます。

- アカウントの作成につながるサインアップ試行の割合。
- 最も高いドロップオフ率を持つサインアップ プロセスのステージ。
- ソーシャル サインアップとローカル アカウントサインアップの間のドロップオフ率の比較。

次の詳細を識別することで、サインアップ要求に関連付けられているアクティビティを記述することもできます。

- サインアップを実行したユーザー
- ユーザーがサインアップを実行したとき
- ユーザーのサインアップ方法 (ローカル アカウントやソーシャル ID プロバイダーなど)
- サインアップするために彼らがアクセスしたアプリ
- サインアップが成功したかどうか
- サインアップ フローでサインアップが失敗した場所
- サインアップしようとしたユーザーが既にアカウントを持っているかどうか
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/concept-usage-insights-report"} -->
## 使用状況と分析情報のレポート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report
- Service: entra-id / monitoring-health
- Article date: 2025-05-27
- Summary: Microsoft Entra ID の使用状況と分析情報レポートを使用して探索できる情報について説明します。

Microsoft Entra **の使用状況と分析情報** のレポートを使用すると、サインイン データのアプリケーション中心のビューを取得できます。 使用状況と分析情報には、認証方法、サービス プリンシパルのサインイン、アプリケーション資格情報アクティビティに関するレポートが含まれます。 次の質問への回答を導き出すことができます。

- 組織内で最もよく使われるアプリケーションはどれか。
- サインインに最も失敗したアプリケーションはどれか。
- 各アプリケーションの上位のサインイン エラーは何か。
- アプリケーションの最後のサインイン日は何日でしたか?

### [前提条件]

使用状況と分析情報からデータにアクセスするには、次のものが必要です。

- Microsoft Entra テナント
- サインイン データを表示するための Microsoft Entra ID P1 または P2 ライセンス
- 最小特権ロールは [レポート閲覧者です](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)。
    - [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) と [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) は、レポートを表示することもできます。

### アクセスの使用状況と分析情報

使用状況と分析情報のレポートには、Azure portal と Microsoft Graph を使用してアクセスできます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視とヘルス**&gt;**使用状況とインサイト** を参照します。

**使用状況と分析情報レポート**は、Microsoft Entra ID の **[エンタープライズ アプリケーション**] 領域からも利用できます。 すべてのユーザーは、 [マイ Sign-Ins ポータル](https://mysignins.microsoft.com/security-info)で自分のサインインにアクセスできます。

## [Microsoft Graph](#tab/microsoft-graph)
レポートは、Graph エクスプローラーの `/beta` エンドポイントで Microsoft Graph を使用して表示および管理できます。

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
2. ドロップダウンから HTTP メソッドとして **GET** を選択します。
3. API バージョンを **ベータ版**に設定します。

含める特定のオブジェクトとパラメーターについては、この記事の各レポートのセクションを参照してください。 詳細については、 [ID レポートとアクセス レポートに関する Microsoft Graph ドキュメントを](https://learn.microsoft.com/ja-jp/graph/api/resources/report-identity-access)参照してください。

---

### Microsoft Entra アプリケーション アクティビティ (プレビュー)

**Microsoft Entra アプリケーション アクティビティ (プレビュー)** レポートには、1 つ以上のサインイン試行があるアプリケーションの一覧が表示されます。 選択した日付範囲内のすべてのアプリケーション アクティビティがレポートに表示されます。 このレポートでは、成功したサインイン数、失敗したサインイン数、成功率で並べ替えることができます。

選択した *日付範囲の* 間、およびアプリケーションが削除される前にアクティビティが行われた場合、削除されたアプリケーションのアクティビティがレポートに表示される可能性があります。 その他のシナリオには、アプリに関連付けられたサービス プリンシパルがないアプリケーションにサインインしようとしているユーザーが含まれる場合があります。 このような種類のシナリオでは、監査ログまたはサインイン ログを確認してさらに調査する必要がある場合があります。

アプリケーションのサインイン アクティビティの詳細を表示するには、アプリケーションの **[サインイン アクティビティの表示** ] リンクを選択します。

[Image: スクリーンショットは、アプリケーション アクティビティの使用状況と分析情報を示しています。このアクティビティでは、範囲を選択し、さまざまなアプリのサインイン アクティビティを表示できます。]

サインイン アクティビティ グラフでは、対話型のユーザー サインインが使用されます。アプリケーションの使用状況グラフで 1 日を選択すると、アプリケーションのサインイン アクティビティの詳細な一覧が表示されます。 この詳細な一覧は、実際には、選択したアプリケーションと日付にフィルターが設定されたサインイン ログです。 サインインエラーの詳細は、表の下に表示されます。

[Image: 選択したアプリケーションのサインイン アクティビティの詳細のスクリーンショット。]

このレポートには、お客様のテナントでインスタンス化された Microsoft サービスが所有するアプリケーションが含まれるようになりました。 これらのアプリケーションは、サービス間認証に関与できます。 **使用状況と分析情報**レポートからアプリケーションを選択すると、アプリケーションはテナントによって所有されておらず、テナント内でのみインスタンス化されるため、結果は "見つかりません" と表示されます。 これらのアプリケーションのサインイン アクティビティを表示するには、[ **サインイン アクティビティの表示** ] リンクを選択します。

#### Microsoft Graph を使用したアプリケーション アクティビティ

Microsoft Graph を使用して、Microsoft Entra アプリケーション アクティビティの `applicationSignInSummary` または `applicationSignInDetailedSummary` を表示できます。

次のクエリを追加して **サインインの概要**を表示し、[クエリの **実行** ] ボタンを選択します。

```http
GET https://graph.microsoft.com/beta/reports/getAzureADApplicationSignInSummary(period='{period}')
```

次のクエリを追加して **サインインの詳細**を表示し、[クエリの **実行** ] ボタンを選択します。

```http
GET https://graph.microsoft.com/beta/reports/applicationSignInDetailedSummary/{id}
```

詳細については、「 [Microsoft Graph でのアプリケーション サインイン」](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationsigninsummary?view=graph-rest-beta&preserve-view=true)を参照してください。

### AD FS アプリケーション アクティビティ

使用状況と分析情報の **AD FS アプリケーション アクティビティ** レポートには、過去 30 日間にアクティブなユーザー サインインを行った組織内のすべての Active Directory フェデレーション サービス (AD FS) アプリケーションが一覧表示されます。 これらのアプリケーションは、認証のために Microsoft Entra ID に移行されていません。

Microsoft Graph を使用して AD FS アプリケーション アクティビティを表示すると、特定のフェデレーション サービスの証明書利用者を識別する `relyingPartyDetailedSummary` オブジェクトの一覧が取得されます。

次のクエリを追加し、[ **クエリの実行** ] ボタンを選択します。

```http
GET https://graph.microsoft.com/beta/reports/getRelyingPartyDetailedSummary(period='{period}')
```

詳細については、 [Microsoft Graph の AD FS アプリケーション アクティビティに関する説明を](https://learn.microsoft.com/ja-jp/graph/api/resources/relyingpartydetailedsummary?view=graph-rest-beta&preserve-view=true)参照してください。

### 認証方法のアクティビティ

使用状況と分析情報 **の [認証方法] アクティビティ** には、組織で使用されるさまざまな認証方法の視覚化が表示されます。 [ **登録] タブ** には、使用可能な認証方法ごとに登録されたユーザーの統計情報が表示されます。 ページの上部にある [ **使用状況** ] タブを選択して、各認証方法の実際の使用状況を確認します。

認証に関連する他のいくつかのレポートやツールにもアクセスできます。

ユーザーが MFA にサインアップできるようにナッジする登録キャンペーンを実行する予定ですか? サイド メニューの **[登録キャンペーン** ] オプションを使用して、登録キャンペーンを設定します。 詳細については、「 [ユーザーをナッジして Microsoft Authenticator を設定する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-registration-campaign)」を参照してください。

ユーザーとその認証方法の詳細をお探しですか? サイド メニューから **ユーザー登録の詳細** レポートを確認し、名前または UPN を検索します。 既定の MFA メソッドと、登録されているその他の方法が表示されます。 また、ユーザーがいずれかの認証方法に登録できるかどうかを確認することもできます。

ユーザーの認証登録またはリセット イベントの状態をお探しですか? サイド メニューから **登録およびリセット イベント** レポートを確認し、名前または UPN を検索します。 認証方法の登録またはリセットを試みるために使用される方法を確認できます。

### サービス プリンシパル サインイン アクティビティ (プレビュー)

サービス プリンシパルのサインイン アクティビティ (プレビュー) レポートには、すべてのサービス プリンシパルの最後のアクティビティの日付が表示されます。 このレポートには、サービス プリンシパルの使用状況 (クライアントアプリまたはリソース アプリとして使用されたかどうか、アプリ専用または委任されたコンテキストで使用されたかどうか) に関する情報が表示されます。 レポートには、サービス プリンシパルが最後に使用された時刻が表示されます。

[Image: サービス プリンシパルのサインイン アクティビティ レポートのスクリーンショット。]

[ **詳細の表示** ] リンクを選択して、アプリケーションおよび特定のサービス プリンシパル サインイン アクティビティのクライアント ID とオブジェクト ID を見つけます。

[Image: サービス プリンシパルのサインイン アクティビティの詳細のスクリーンショット。]

#### Microsoft Graph を使用したサービス プリンシパルのサインイン アクティビティ

`servicePrincipalSignInActivity` レポートは、Microsoft Graph を使用して表示できます。

Graph Explorer で次のクエリを追加してサービス プリンシパルのサインイン アクティビティを取得し、[クエリの **実行** ] ボタンを選択します。

```http
GET https://graph.microsoft.com/beta/reports/servicePrincipalSignInActivities/{id}
```

応答の例:

```json
{
     "@odata.context": "https://graph.microsoft.com/beta/$metadata#reports/servicePrincipalSignInActivities",
     "id": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
     "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",    
     "delegatedClientSignInActivity": {
          "lastSignInDateTime": "2021-01-01T00:00:00Z",
          "lastSignInRequestId": "2d245633-0f48-4b0e-8c04-546c2bcd61f5"
     },
     "delegatedResourceSignInActivity": {
          "lastSignInDateTime": "2021-02-01T00:00:00Z",
          "lastSignInRequestId": "d2b4c623-f930-42b5-9519-7851ca604b16"
     },
     "applicationAuthenticationClientSignInActivity": {
          "lastSignInDateTime": "2021-03-01T00:00:00Z",
          "lastSignInRequestId": "b71f24ec-f212-4306-b2ae-c229e15805ea"
     },
     "applicationAuthenticationResourceSignInActivity": {
          "lastSignInDateTime": "2021-04-01T00:00:00Z",
          "lastSignInRequestId": "53e6981f-2272-4deb-972c-c8272aca986d"
     },
     "lastSignInActivity": {
          "lastSignInDateTime": "2021-04-01T00:00:00Z",
          "lastSignInRequestId": "cd9733e8-d75a-468f-a63d-6e82bd48c05e"
     }
}
```

詳細については、「 [Microsoft Graph でのサービス プリンシパル アクティビティの一覧表示](https://learn.microsoft.com/ja-jp/graph/api/reportroot-list-serviceprincipalsigninactivities?view=graph-rest-beta&preserve-view=true)」を参照してください。

### アプリケーション資格情報アクティビティ (プレビュー)

アプリケーション資格情報アクティビティ (プレビュー) レポートには、すべてのアプリケーション資格情報の最後の資格情報アクティビティの日付が表示されます。 レポートには、資格情報の種類 (証明書またはクライアント シークレット)、最後に使用された日付、有効期限が表示されます。 このレポートを使用すると、すべてのアプリケーションの有効期限を 1 か所で表示できます。

アプリケーション資格情報アクティビティの詳細を表示するには、[ **詳細の表示** ] リンクを選択します。 これらの詳細には、アプリケーション オブジェクト、サービス プリンシパル、およびリソース ID が含まれます。 資格情報の配信元がアプリケーションかサービス プリンシパルかを確認することもできます。

[Image: アプリ資格情報アクティビティ レポートのスクリーンショット。]

[ **詳細の表示** ] リンクを選択すると、レポートに表示される詳細に加えて、アプリケーション オブジェクト ID とリソース ID を確認できます。

[Image: アプリ資格情報アクティビティの詳細のスクリーンショット。]

#### Microsoft Graph を使用したアプリケーション資格情報アクティビティ

アプリケーション資格情報アクティビティは、 `/beta` エンドポイントで Microsoft Graph を使用して表示および管理できます。 エンティティの `id`、 `keyId`、 `appId` ごとに、アプリケーション資格情報のサインイン アクティビティを取得できます。

開始するには、次の手順に従って、Graph エクスプローラーで Microsoft Graph を使用して `appCredentialSignInActivity` を操作します。

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
2. ドロップダウンから HTTP メソッドとして **GET** を選択します。
3. API バージョンを **ベータ版**に設定します。
4. 次のクエリを追加して推奨事項を取得し、[ **クエリの実行** ] ボタンを選択します。

    ```http
    GET https://graph.microsoft.com/beta/reports/appCredentialSignInActivities/{id}
    ```

応答の例:

```json
{
 "@odata.type": "#microsoft.graph.appCredentialSignInActivity",
 "id": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
 "keyId": "aaaaaaaa-0b0b-1c1c-2d2d-333333333333",
 "keyType": "certificate",
 "keyUsage": "sign",
 "appId": "11112222-bbbb-3333-cccc-4444dddd5555",
 "appObjectId": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
 "servicePrincipalObjectId": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
 "resourceId": "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1",
 "credentialOrigin": "application",
 "expirationDate": "2021-04-01T21:36:48-8:00",
 "signInActivity": {
     "lastSignInDateTime": "2021-04-01T00:00:00-8:00",
     "lastSignInRequestId": "b0a282a3-68ec-4ec8-aef0-290ed4350271"
 }
}   
```

詳細については、「 [Microsoft Graph のアプリケーション資格情報アクティビティ](https://learn.microsoft.com/ja-jp/graph/api/resources/appcredentialsigninactivity?view=graph-rest-beta&preserve-view=true)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/how-to-view-applied-conditional-access-policies"} -->
## 条件付きアクセス と Microsoft Entra アクティビティ ログ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/how-to-view-applied-conditional-access-policies
- Service: entra-id / monitoring-health
- Article date: 2024-11-19
- Summary: Microsoft Entra アクティビティ ログで条件付きアクセスの詳細を表示して、ポリシーの影響を評価できるようにする方法について説明します。

条件付きアクセス ポリシーを使用すると、ユーザーがどのように Azure および Microsoft Entra のリソースにアクセスできるかを制御できます。 テナント管理者は、条件付きアクセス ポリシーがテナントへのサインインに与える影響を判断し、必要に応じてアクションを実行できるようにする必要があります。 また、条件付きアクセス ポリシーに最近加えられた変更について監査ログを表示する必要がある場合があります。

この記事では、Microsoft Entra アクティビティ ログで適用された条件付きアクセス ポリシーを表示する方法について説明します。

### 前提条件

適用された条件付きアクセス ポリシーをログに表示するには、管理者がログとポリシーの *両方* を表示するアクセス許可を持っている必要があります。 "両方" のアクセス許可を付与する最小特権の組み込みロールは、"セキュリティ閲覧者" です。 ベスト プラクティスとして、関連する管理者アカウントにセキュリティ閲覧者ロールを追加する必要があります。

次の組み込みロールは、"条件付きアクセス ポリシーを読み取る" ためのアクセス許可を付与します。

- セキュリティ閲覧者
- セキュリティ管理者
- 条件付きアクセス管理者

次の組み込みロールは、 *アクティビティ ログを表示*するアクセス許可を付与します。

- レポート閲覧者
- セキュリティ閲覧者
- セキュリティ管理者

#### アクセス許可

クライアント アプリまたは Microsoft Graph PowerShell モジュールを使用して Microsoft Graph からログをプルする場合、そのアプリには、Microsoft Graph から `appliedConditionalAccessPolicy` リソースを受け取るアクセス許可が必要です。 ベスト プラクティスとして `Policy.Read.ConditionalAccess` を割り当てます。これが最小特権のアクセス許可であるためです。

次のアクセス許可を使用すると、クライアント アプリは、Microsoft Graph を介してログ内のアクティビティ ログと適用された条件付きアクセス ポリシーにアクセスできます。

- `Policy.Read.ConditionalAccess`
- `Policy.ReadWrite.ConditionalAccess`
- `Policy.Read.All`
- `AuditLog.Read.All`
- `Directory.Read.All`

Microsoft Graph PowerShell モジュールを使用するには、必要なアクセス権を持つ次の最小限の特権アクセス許可も必要です。

- 必要なアクセス許可に同意するには: `Connect-MgGraph -Scopes Policy.Read.ConditionalAccess, AuditLog.Read.All, Directory.Read.All`
- サインイン ログを表示するには: `Get-MgAuditLogSignIn`
- 監査ログを表示するには: `Get-MgAuditLogDirectoryAudit`

詳細については、 [Get-MgAuditLogSignIn](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.reports/get-mgauditlogsignin) と [Get-MgAuditLogDirectoryAudit を](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.reports/get-mgauditlogdirectoryaudit)参照してください。

### 条件付きアクセスとサインイン ログのシナリオ

Microsoft Entra 管理者は、サインイン ログを使用して以下を行うことができます。

- サインインに関する問題のトラブルシューティング。
- 機能のパフォーマンスの確認。
- テナントのセキュリティの評価。

一部のシナリオでは、条件付きアクセス ポリシーがどのようにサインイン イベントに適用されたかを理解することが求められます。 たとえば、次のような場合です。

- ヘルプデスク管理者が、ユーザーが開いたチケットの根本原因がポリシーであるかどうかを把握するために、適用された条件付きアクセス ポリシーを確認する必要がある場合。
- "テナント管理者" が、テナントのユーザーに条件付きアクセス ポリシーが意図した影響を与えていることを確認する必要がある場合。

サインイン ログにアクセスするには、Microsoft Entra 管理センター、Azure portal、Microsoft Graph、PowerShell を使用します。

### 条件付きアクセス ポリシーを表示する方法

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
サインイン ログのアクティビティの詳細には、いくつかのタブが含まれています。 [ **条件付きアクセス** ] タブには、そのサインイン イベントに適用される条件付きアクセス ポリシーが一覧表示されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視とヘルス**&gt;**サインインログを参照します**。
3. テーブルからサインイン項目を選択して、サインインの詳細ウィンドウを表示します。
4. [ **条件付きアクセス** ] タブを選択します。

条件付きアクセス ポリシーが表示されない場合は、サインイン ログと条件付きアクセス ポリシーの両方へのアクセスを提供するロールを使用していることを確認します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
[Graph エクスプローラー](https://aka.ms/ge)で Microsoft Graph API を使用して条件付きアクセス ポリシーとログの詳細を表示するには、次の手順に従います。

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
2. ドロップダウンから HTTP メソッドとして **GET** を選択します。
3. **v1.0** の API バージョンを選択します。
4. 特定の期間中に条件付きアクセスが失敗したサインイン試行を取得するには、次のクエリを追加し、[ **クエリの実行**] を選択します。

    ```http
    GET https://graph.microsoft.com/v1.0/auditLogs/signIns?$filter=(createdDateTime ge 2024-11-01T14:13:32Z and createdDateTime le 2024-11-10T17:43:26Z) and conditionalAccessStatus eq 'failure'
    ```
5. 特定の条件付きアクセス ポリシーを表示するには、ポリシー ID を追加します。

    ```http
    GET https://graph.microsoft.com/v1.0/identity/conditionalAccess/policies/{id}
    ```

詳細については、次のリソースを確認してください。

- [conditionalAccessPolicy リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/conditionalaccesspolicy)
- [サインイン リソースタイプ](https://learn.microsoft.com/ja-jp/graph/api/resources/signin)

## [Microsoft Graph PowerShell](#tab/microsoft-graph-powershell)
PowerShell を使用して Microsoft Entra ロールを一覧表示するには、次の手順を実行します。

1. PowerShell ウィンドウを開きます。 必要に応じて、 [Install-Module](https://learn.microsoft.com/ja-jp/powershell/module/powershellget/install-module) を使用して Microsoft Graph PowerShell をインストールします。 詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
2. PowerShell ウィンドウで、 [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) を使用してテナントにサインインします。

    ```powershell
    Connect-MgGraph -Scopes "Policy.Read.All", "Policy.Read.ConditionalAccess"
    ```
3. [Get-MgIdentityConditionalAccessPolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgidentityconditionalaccesspolicy) を使用して、すべての条件付きアクセス ポリシーを取得します。

    ```powershell
    Get-MgIdentityConditionalAccessPolicy
    ```
4. 結果を一覧として書式設定するには、次のコマンドを使用します。

    ```powershell
    Get-MgIdentityConditionalAccessPolicy |Format-List
    ```

次のコマンドを使用すると、サインインログを CSV ファイルにエクスポートできます。このファイルは、条件付きアクセスに関連する詳細を強調表示するように書式設定されています。

1. 出力 CSV ファイルのパスを定義します。

    ```powershell
    $PathCsv = "C:\\temp\\ConditionalAccessSignInLogs.csv"
    ```
2. 指定した日付からフィルター処理されたログを取得し、CSV ファイルにエクスポートします。

    ```powershell
    $SignInLogs = Get-MgAuditLogSignIn -Filter "createdDateTime gt 2024-11-10T05:30:00.0Z" | Select-Object `
    @{Name="ResourceName";Expression={$_.ResourceDisplayName}}, `
    @{Name="User";Expression={$_.UserDisplayName}}, `
    @{Name="IPv4";Expression={$_.IPAddress}}, `
    @{Name="Location";Expression={$_.Location.City}}, `
    @{Name="NamedLocation";Expression={$_.ConditionalAccessLocations}}, `
    @{Name="Success/Failure/ReportOnly";Expression={$_.ConditionalAccessStatus}}, `
    @{Name="FailureReason";Expression={$_.FailureReason}}, `
    @{Name="Details";Expression={$_.ConditionalAccessDetails}}, `
    @{Name="DeviceName";Expression={$_.DeviceDetail.DeviceDisplayName}}, `
    @{Name="ClientApp";Expression={$_.ClientAppUsed}} | 
    Export-Csv -Path $PathCsv -NoTypeInformation
    
    Write-Host "Conditional Access SignInLogs exported to $PathCsv"
    ```

---

### 条件付きアクセスと監査ログのシナリオ

Microsoft Entra 監査ログには、条件付きアクセス ポリシーの変更に関する情報が含まれています。 監査ログを使用して、ポリシーが作成、更新、または削除された期間を確認できます。

既存の条件付きアクセス ポリシーが更新された期間を確認するには、次の操作を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視と健康**&gt;**監査ログ**に移動します。
3. **サービス** フィルターを**条件付きアクセス**に設定します。
4. **[カテゴリ**] フィルターを **[ポリシー**] に設定します。
5. **アクティビティ** フィルターを **[条件付きアクセス ポリシーの更新]** に設定します。

探している変更点を確認するには、日付を調整する必要がある場合があります。 **[ターゲット]** 列には、更新された条件付きアクセス ポリシーの名前が表示されます。

現在のポリシーと前のポリシーを比較するには、監査ログ エントリを選択し、[ **変更されたプロパティ** ] タブを選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-access-activity-logs"} -->
## Microsoft Entra ID 内でアクティビティ ログにアクセスする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs
- Service: entra-id / monitoring-health
- Article date: 2026-06-24
- Summary: Microsoft Entra ID のアクティビティ ログにアクセスして統合するための適切な方法を選択する方法。

Microsoft Entra ログに収集されたデータを使用すると、Microsoft Entra テナントの多くの側面を評価することができます。 幅広いシナリオに対応できるよう、Microsoft Entra ID では、アクティビティ ログ データにアクセスするためのオプションがいくつか提供されています。 IT 管理者は、シナリオに適したアクセス機構を選択できるように、これらのオプションのユース ケースを理解する必要があります。

次の方法を使用で、Microsoft Entra アクティビティ ログとレポートにアクセスできます。

- アクティビティ ログを**イベント ハブ**にストリームして他のツールと統合する
- **Microsoft Graph API** を通じてアクティビティ ログにアクセスする
- アクティビティ ログと **Azure Monitor ログ**の統合
- **Microsoft Sentinel** でアクティビティをリアルタイムで監視する
- **Microsoft Entra 管理センター**でアクティビティ ログとレポートを表示する
- アクティビティログをエクスポートして**格納とクエリ**に使用する

これらの各方法には、特定のシナリオに適合する可能性のある機能が用意されています。 この記事では、アクティビティ ログのデータを使用する関連レポートについての推奨事項や詳細など、これらのシナリオについて説明します。 この記事のオプションを細かく確認し、それらのシナリオについて学び、適切な方法を選択できるようにしましょう。

### 前提条件

- 適切な Microsoft Entra ライセンスが関連付けられている、動作中の Microsoft Entra テナント。 ライセンス要件の完全なリストについては、「[Microsoft Entra の監視と正常性のライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)」を参照してください。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)は、アクティビティ ログにアクセスするために必要な最小特権ロールです。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)は、診断設定を構成するために必要な最小特権ロールです。
- 監査ログは、ライセンスを取得した機能で利用できます。
- Microsoft Graph を使用してログを表示するために必要なアクセス許可に同意するには、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)が必要です。
- ロールの完全な一覧については、「[タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---audit-and-sign-in-logs-least-privileged-roles)」を参照してください。

必要なライセンスは、監視および正常性機能によって異なります。

| 機能 | Microsoft Entra ID Free（無料） | Microsoft Entra ID P1 または P2 / Microsoft Entra スイート |
| --- | --- | --- |
| 監査ログ | あり | あり |
| サインイン ログ | あり | あり |
| プロビジョニング ログ | いいえ | あり |
| カスタム セキュリティ属性 | あり | あり |
| 正常性 | いいえ | あり |
| Microsoft Graph アクティビティ ログ | いいえ | あり |
| 使用状況と分析情報 | いいえ | あり |

### Microsoft Entra 管理センターを使用してログを表示する

範囲が限定されたこれらの 1 回限りの調査では、多くの場合、必要なデータを見つける最も簡単な方法は [Microsoft Entra 管理センター](https://entra.microsoft.com/)です。 これらのレポートそれぞれのユーザー インターフェイスには、シナリオを解決するために必要なエントリを見つけ出すフィルター オプションが備わっています。

Microsoft Entra アクティビティ ログにキャプチャされたデータは、多くのレポートやサービスで使用されます。 1 回限りのシナリオのサインイン、監査、プロビジョニング ログを確認したり、レポートを使用してパターンや傾向を確認したりできます。 アクティビティ ログのデータは Identity Protection レポートの作成に役立ちます。これにより、Microsoft Entra ID が検出してレポートできる情報セキュリティ関連のリスク検出ができます。 Microsoft Entra アクティビティ ログには、テナントのアプリケーションの使用状況の詳細を提供する使用状況レポートと分析情報レポートも入力されます。

#### 推奨される用途

Azure portal で利用できるレポートには、テナント内のアクティビティと使用状況を監視するための幅広い機能が備わっています。 用途とシナリオに関する次の一覧はすべてを取り上げたものではないため、ニーズに合わせてレポートを確認してみてください。

- ユーザーのサインイン アクティビティを調査したり、アプリケーションの使用状況を追跡したりします。
- 監査ログを使用して、グループ名の変更、デバイスの登録、パスワードのリセットに関する詳細を確認します。
- Identity Protection レポートを使用して、危険なユーザー、危険なワークロード ID、危険なエージェント、危険なサインインを監視します。
- 使用状況と分析情報から Microsoft Entra アプリケーション アクティビティ (プレビュー) レポートのサインイン成功率を確認し、ユーザーがテナントで使用中のアプリケーションにアクセスできることを確認します。
- 使用状況と分析情報の認証方法レポートを使用して、ユーザーが選ぶ異なる認証方法を比較します。

#### 簡単な手順

Microsoft Entra 管理センターのレポートにアクセスするには、次の基本的な手順を使用します。

## [Microsoft Entra アクティビティ ログ](#tab/microsoft-entra-activity-logs)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **Entra ID**&gt;**監視とヘルス**&gt;**監査ログ**/**サインインログ**/**プロビジョニングログ**に移動します。
3. 必要に応じてフィルターを調整します。
    - [アクティビティ ログをフィルター処理する方法を確認する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-customize-filter-logs)
    - [Microsoft Entra 監査ログのカテゴリとアクティビティの細部を確認する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)
    - [Microsoft Entra サインイン ログの基本情報の詳細](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)

監査ログには、作業している Microsoft Entra 管理センターの領域から直接アクセスできます。 たとえば、Microsoft Entra ID の **[グループ]** または **[ライセンス]** セクションが表示されている場合は、その領域から、そうした特定のアクティビティの監査ログに直接アクセスできます。 この方法で監査ログにアクセスすると、フィルター カテゴリが自動的に設定されます。 たとえば、 **グループ**を使用している場合、監査ログ フィルター カテゴリは **GroupManagement** に設定されます。

## [Microsoft Entra ID 保護 レポート](#tab/microsoft-entra-id-protection-reports)
1. **ID Protection**&gt;**Dashboard** に移動します。
2. 利用可能なレポートの細部を確認します。
    - [ID 保護の詳細を確認する](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)
    - [リスクを調査する方法](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)

## [使用状況と分析情報のレポート](#tab/usage-and-insights-reports)
1. Entra IDモニタリング & ヘルス使用状況とインサイト に移動します。
2. 利用可能なレポートの細部を確認します。
    - [使用状況と分析情報のレポートに関する詳細](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report)

---

### ログをイベント ハブにストリームして SIEM ツールと統合する

アクティビティ ログを Splunk や SumoLogic などのセキュリティ情報イベント管理 (SIEM) ツールと統合するには、アクティビティ ログをイベント ハブにストリームする必要があります。 ログをイベント ハブにストリームする前に、Azure サブスクリプションに [Event Hubs の名前空間とイベント ハブを設定する](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-create)必要があります。

#### 推奨される用途

イベント ハブと統合できる SIEM ツールには、分析機能と監視機能が備えられています。 既にこれらのツールを使用して他のソースからデータを取り込んでいる場合は、ID データをストリームして、より包括的な分析と監視を行うことができます。 次の種類のシナリオでは、アクティビティ ログをイベント ハブにストリームすることをお勧めします。

- 毎秒数百万のイベントを受け取って処理するには、ビッグ データのストリーミング プラットフォームとイベント インジェスト サービスが必要です。
- リアルタイム分析プロバイダーまたはバッチ処理/ストレージ アダプターを使用してデータを変換および保存しようとしています。

#### 簡単な手順

1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. Event Hubs 名前空間とイベント ハブを作成する
3. **Entra ID**&gt;**監視と健康**&gt;**診断設定**に移動します。
4. ストリームするログを選択し、**[イベント ハブへのストリーム]**オプションを選択して、フィールドに入力します。
    - [Event Hubs の名前空間とイベント ハブを設定する](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-create)
    - [イベント ハブへのアクティビティ ログのストリームに関する詳細](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)

独立したセキュリティ ベンダーは、Azure Event Hubs からツールにデータを取り込む方法についての指示を提供する必要があります。

### Microsoft Graph API を使用したログへのアクセス

Microsoft Graph API は、Microsoft Entra ID P1 または P2 テナントのデータにアクセスするために使用できる、統合されたプログラミング モデルを提供します。 管理者や開発者が、スクリプトやアプリをサポートするために追加のインフラストラクチャを設定する必要はありません。

#### 推奨される用途

Microsoft Graph エクスプローラーを使用すると、次の種類のシナリオに役立つクエリを実行できます。

- グループに対して誰がいつ変更を加えたかなど、テナントのアクティビティを表示します。
- Microsoft Entra サインイン イベントに、安全のマーク、または侵害されたことを確認済みのマークを付けます。
- 過去 30 日間に行われたアプリケーションのサインインの一覧を取得します。

注

Microsoft Graph を使用すると、独自の調整制限を課す複数のサービスからデータにアクセスできます。 アクティビティ ログの調整の詳細については、「[Microsoft Graph サービス固有の調整制限](https://learn.microsoft.com/ja-jp/graph/throttling-limits#identity-and-access-service-limits)」を参照してください。

#### 簡単な手順

1. [構成の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-prerequisites-for-reporting-api)。
2. [グラフ エクスプローラー](https://aka.ms/ge)にサインインします
3. HTTP メソッドと API バージョンを設定します。
4. クエリを追加してから、**[クエリの実行]**ボタンを選択します。
    - [ディレクトリ監査の Microsoft Graph プロパティについて理解する](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)
    - [MS Graph クイックスタートガイドを完了する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/quickstart-access-log-with-graph-api)

### ログを Azure Monitor ログと統合する

Azure Monitor ログの統合により、接続されたデータに対して豊富な視覚化、監視、アラートを有効にすることができます。 Log Analytics は、Microsoft Entra アクティビティ ログの強化されたクエリ機能と分析機能を備えています。 Microsoft Entra アクティビティ ログを Azure Monitor ログと統合するには、Log Analytics ワークスペースが必要です。 そこから、Log Analytics を通じてクエリを実行できます。

#### 推奨される用途

Microsoft Entra ログを Azure Monitor ログと統合すると、ログのクエリを実行するための一元的な場所が提供されます。 次の種類のシナリオでは、ログを Azure Monitor と統合することをお勧めします。

- Microsoft Entra サインイン ログを他の Azure サービスによって公開されたログと比較する。
- サインイン ログを Azure アプリケーション Insights と関連付ける。
- 特定の検索パラメータを使用してログのクエリを実行する。

#### 簡単な手順

1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. [Log Analytics ワークスペースを作成します](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)。
3. **Entra ID**&gt;**監視と健康**&gt;**診断設定**に移動します。
4. ストリームするログを選択し、**[Log Analytics ワークスペースに送信]** オプションを選択して、フィールドに入力します。
5. **Entra ID**&gt;**Monitoring & health**&gt;**Log Analytics**に移動し、データのクエリを開始します。
    - [Microsoft Entra ログを Azure Monitor ログと統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)
    - [Log Analytics でクエリを実行する方法を確認する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-activity-logs-log-analytics)

### Microsoft Sentinel を使用してイベントを監視する

サインイン ログと監査ログを Microsoft Sentinel に送信すると、セキュリティ オペレーション センターでほぼリアルタイムのセキュリティ検出と脅威ハンティングが可能になります。 *脅威の捜索*という用語は、環境のセキュリティ態勢を改善するための事前アプローチを指します。 従来の保護とは対照的に、脅威ハンティングは、システムに害を及ぼす可能性のある潜在的な脅威を事前に特定しようとします。 アクティビティ ログ データは、脅威の捜索ソリューションの一部である可能性があります。

#### 推奨される用途

組織でセキュリティ分析と脅威インテリジェンスが必要な場合は、Microsoft Sentinel のリアルタイム セキュリティ検出機能を使用することをお勧めします。 次のことを行う必要がある場合は Microsoft Sentinel を使用します。

- 企業全体のセキュリティ データを収集する。
- 膨大な脅威インテリジェンスで脅威を検出する。
- AI のガイドに従って重大なインシデントを調査する。
- 迅速に対応し、保護を自動化する。

#### 簡単な手順

1. [前提条件](https://learn.microsoft.com/ja-jp/azure/sentinel/prerequisites)、[ロール、およびアクセス許可](https://learn.microsoft.com/ja-jp/azure/sentinel/roles)について確認します。
2. [潜在的なコストを見積もります](https://learn.microsoft.com/ja-jp/azure/sentinel/billing)。
3. [Microsoft Sentinel にオンボードします](https://learn.microsoft.com/ja-jp/azure/sentinel/quickstart-onboard)。
4. [Microsoft Entra データを収集します](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)。
5. [脅威のハンティングを開始します](https://learn.microsoft.com/ja-jp/azure/sentinel/hunting)。

### 格納とクエリのためにログをエクスポートする

長期の保存に適したソリューションは、予算と、データの使用目的によります。 次の 3 つのオプションがあります。

- ログを Azure Storage にアーカイブする
- ログをダウンロードして手動で格納する
- ログを Azure Monitor ログと統合する

[Azure Storage](https://learn.microsoft.com/ja-jp/azure/storage/common/storage-introduction) は、クエリを頻繁に実行する予定がない場合に最適なソリューションです。 詳細については、「[ディレクトリのログをストレージ アカウントにアーカイブする](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-archive-logs-to-storage-account)」をご覧ください。

保存されたログに対してレポートや分析を実行するために、ログを頻繁にクエリすることも計画している場合は、[Azure Monitor ログとデータを統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)必要があります。

予算が厳しく、アクティビティ ログの長期的なバックアップを作成するために安価な方法が必要な場合は、[ログを手動でダウンロードできます](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs)。 ポータルのアクティビティ ログのユーザー インターフェイスには、**JSON** または **CSV** としてデータをダウンロードするオプションが表示されます。 手動ダウンロードのトレードオフの 1 つは、より多くの手動操作が必要であるということです。 よりプロフェッショナルなソリューションをお探しの場合は、Azure Storage または Azure Monitor を使用してください。

#### 推奨される用途

ガバナンスとコンプライアンスのシナリオで長期保存が必要な場合、アクティビティ ログをアーカイブするためのストレージ アカウントを設定することをお勧めします。

長期保存が必要であり、*かつ*データに対してクエリを実行する必要がある場合は、アクティビティ ログと Azure Monitor ログの統合に関するセクションを確認してください。

予算に制約がある場合は、アクティビティ ログを手動でダウンロードして格納することをお勧めします。

#### 簡単な手順

アクティビティ ログをアーカイブまたはダウンロードするには、次の基本的な手順を実行します。

## [アクティビティ ログをストレージ アカウントにアーカイブする](#tab/archive-activity-logs-to-a-storage-account)
1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. ストレージ アカウントを作成します。
3. **Entra ID**&gt;**監視と健康**&gt;**診断設定**に移動します。
4. ストリームするログを選択し、**[ストレージ アカウントにアーカイブ]**オプションを選択して、フィールドに入力します。
    - [データ保持ポリシーを確認する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention)

## [アクティビティ ログを手動でダウンロードする](#tab/manually-download-activity-logs)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **[監視]** メニューから &gt;&gt;//**[プロビジョニング ログ]** を参照します。
3. **[ダウンロード]**を選択します。
    - [ログをダウンロードする方法を確認する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs)。

---

### アクティビティ ログを取得するときの空の結果または HTTP 429 エラーのトラブルシューティング

Microsoft Entra 管理センターまたは Microsoft Graph APIを使用してアクティビティ ログ (サインイン、監査、またはプロビジョニング) に対してクエリを実行しても、結果が表示されない場合があります。 ネットワーク トレースをキャプチャすると、基になる API 呼び出しは HTTP 429 (要求が多すぎます) 応答を返します。 調整は、テナント のサイズではなく、現在のシステムの需要に依存するため、以前に機能したのと同じクエリで後で結果が返されない場合があります。 管理センターまたはトレース内の HTTP 429 応答に結果が表示されない場合は、次の回避策を使用します。

#### クエリの日付範囲を減らす

クエリをより小さな日付範囲のチャンクに分割し、必要な期間をカバーするまで各チャンクに対してクエリを繰り返します。 成功する日付範囲はテナントと現在のシステムの需要によって異なるため、結果が返されるまで範囲を段階的に減らします。

#### Log Analytics ワークスペースにログをストリーミングする

管理センターおよび Microsoft Graph クエリに影響する要求のスロットリングを回避するには、アクティビティ ログを Log Analytics ワークスペースにストリーミングし、そこでデータを照会します。 Log Analyticsでは、Kusto クエリ言語 (KQL) を使用した豊富なクエリ機能がサポートされます。 セットアップ手順については、ログとAzure Monitorログの統合に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-analyze-activity-logs-log-analytics"} -->
## Log Analytics を使用してアクティビティ ログを分析する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-activity-logs-log-analytics
- Service: entra-id / monitoring-health
- Article date: 2026-02-27
- Summary: Log Analytics クエリを使用して、監査、サインイン、プロビジョニングのログMicrosoft Entra ID分析する方法について説明します。

[Azure Monitor ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)で Microsoft Entra アクティビティ ログを統合したら、Log Analytics と Azure Monitor ログの機能を使用して、環境に関する分析情報を得ることができます。

- Microsoft Entra サインイン ログと、Microsoft Defender for Cloudによって発行されたセキュリティ ログを比較します。
- Azure Application Insights からのアプリケーション パフォーマンス データを関連付けることで、アプリケーションのサインイン ページでパフォーマンスのボトルネックをトラブルシューティングします。
- Identity Protection の危険なユーザーとリスク検出ログを分析して、環境内の脅威を検出します。

この記事では、Log Analytics ワークスペースでの Microsoft Entra アクティビティ ログの分析について説明します。

### 前提条件

Log Analytics を使ってアクティビティ ログを分析するには、次のものが必要です。

- Microsoft Entra ID P1 または P2 ライセンスが関連付けられている動作中の Microsoft Entra テナント。
- Log Analytics ワークスペースとそのワークスペースへのアクセス
- Azure Monitor および*Microsoft Entra ID* の適切なロール

#### Log Analytics ワークスペース

[Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)を作成する必要があります。 Log Analytics ワークスペースへのaccessを決定するいくつかの要因があります。 ワークスペース *と* データを送信するリソースに適したロールが必要です。

詳細については、「[Log Analytics ワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には[監視データの表示と監視設定の編集のための 2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader)が用意されています。 Azureロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics の組み込みロールも用意されています。

- **[表示]**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定を表示および変更する**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

Azure Monitor の組み込みロールの詳細については、「Azure Monitor の Roles、permissions、security を参照してください。

Log Analytics ロールの詳細については、「Azure 組み込みロール

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内でMicrosoft Entra IDログ データを表示したり、Log Analytics からデータを照会したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 Update accessは、診断設定を作成および編集して、Microsoft Entra データを Log Analytics ワークスペースに送信する機能を追加します。

- [**読み取り**]:

    - レポート閲覧者
    - セキュリティビューアー
    - グローバルリーダー
- **更新**:

    - セキュリティ管理者

Microsoft Entra 組み込みロールの詳細については、「[Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を参照してください。

### アクセスログ分析

Microsoft Entra ID Log Analytics を表示するには、Microsoft Entra IDから Log Analytics ワークスペースにアクティビティ ログを既に送信している必要があります。 このプロセスについては、[アクティビティ ログを Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) に統合する方法に関する記事を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Reports Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) としてサインインします。
2. **Entra ID**&gt;**Monitoring & health**&gt;**Log Analytics** に移動します。 既定の検索クエリが実行されます。

    [Image: 既定のクエリ]
3. **LogManagement** カテゴリを展開して、ログ関連クエリの一覧を表示します。
4. クエリの名前を選択またはポイントすると、説明や、その他の有用な詳細情報が表示されます。
5. 一覧からクエリの 1 つを展開表示し、スキーマを表示します。

    [Image: クエリのスキーマのスクリーンショット。]

### アクティビティ ログのクエリを実行する

Log Analytics ワークスペースに配信されたアクティビティ ログに対しては、クエリを実行できます。 たとえば、前週にサインイン回数が多かった上位アプリケーションの一覧を取得するには、次のクエリを入力し、**[実行]** ボタンを選択します。

```kusto
SigninLogs 
| where CreatedDateTime >= ago(7d)
| summarize signInCount = count() by AppDisplayName
| sort by signInCount desc 
```

危険なサインイン イベントを見つけるには、次のクエリを使用します。

```kusto
SigninLogs
| where RiskState contains "atRisk"
```

過去 1 週間にわたる最上位の監査イベントを取得するには、次のクエリを使用します。

```kusto
AuditLogs 
| where TimeGenerated >= ago(7d)
| summarize auditCount = count() by OperationName 
| sort by auditCount desc 
```

1 日あたりのプロビジョニング イベントの数をアクション別に集計するには:

```kusto
AADProvisioningLogs
| where TimeGenerated > ago(7d)
| summarize count() by Action, bin(TimeGenerated, 1d)
```

100 個のプロビジョニング イベントを取得し、主要なプロジェクト特性を抽出します。

```kusto
AADProvisioningLogs
| extend SourceIdentity = parse_json(SourceIdentity)
| extend TargetIdentity = parse_json(TargetIdentity)
| extend ServicePrincipal = parse_json(ServicePrincipal)
| where tostring(SourceIdentity.identityType) == "Group"
| project tostring(ServicePrincipal.Id), tostring(ServicePrincipal.Name), ModifiedProperties, JobId, Id, CycleId, ChangeId, Action, SourceIdentity.identityType, SourceIdentity.details, TargetIdentity.identityType, TargetIdentity.details, ProvisioningSteps
| take 100
```

### Log Analytics の複数のサインイン レコード

Microsoft Entra IDサインイン ログを分析すると、Log Analytics に 1 人のユーザー サインインの複数のレコードが表示されるのに対し、Microsoft Entra IDサインイン ログ ビューには 1 つだけが表示されていることに気付く場合があります。 この動作は想定内です。

#### 複数のレコードが表示される理由

対話型サインイン中に、すべてのユーザーが同じ関連付け ID を共有する複数の要求を生成する場合があります。 例えば次が挙げられます。

1. ユーザーはサインインを試み、多要素認証 (MFA) を求められます。
2. 最初の MFA 試行は失敗します。
3. ユーザーは MFA を再試行し、成功します。

Microsoft Entra IDサインイン ログでは、これらの関連する要求がシングル サインイン イベントにマージされます。 ポータルでは、同じ関連付け ID を持つすべてのアクティビティが集計され、最終的な結果が報告されるため、サインインは成功と見なされます。

すべての要求は個別に送信されます。つまり、Log Analytics では次の情報が表示されます。

- 中間の失敗エントリ。
- 最終的に成功したエントリ。

すべてのエントリが同じ関連付け ID を共有します。

#### これらのログを解釈する

Log Analytics では各要求が表示されるため、重複しているように見える場合があります。 ただし、これらのエントリは、シングル サインイン フローの個別の手順です。

これらのイベントを正しく解釈するには:

1. 関連付け ID ですべてのレコードをグループ化します。
2. 有効な結果として、その関連付け ID の最終的な状態を使用します。
3. 必要に応じて、KQL のレコードをマージして、Microsoft Entra 管理センターと同様の集計ビューを作成します。

次の例は、関連付け ID でレコードをマージする方法を示しています。

```kusto
SigninLogs
| summarize
    FirstSeen = min(TimeGenerated),
    LastSeen = max(TimeGenerated),
    FinalStatus = arg_max(TimeGenerated, ResultType, ConditionalAccessStatus),
    Count = count()
    by CorrelationId, UserPrincipalName
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-analyze-activity-logs-with-microsoft-graph"} -->
## Microsoft Graph を使ってアクティビティ ログを分析する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-activity-logs-with-microsoft-graph
- Service: entra-id / monitoring-health
- Article date: 2025-06-30
- Summary: Microsoft Graph レポート API を使用して、Microsoft Entra のサインイン ログと監査ログにアクセスして分析する方法について説明します。

Microsoft Entra [Reporting API](https://learn.microsoft.com/ja-jp/graph/api/resources/azure-ad-auditlog-overview)は、一連の REST API を使用してデータにプログラムによるアクセスを提供します。 これらの API は、多くのプログラミング言語やツールから呼び出すことができます。

この記事では、Microsoft Graph Explorer と Microsoft Graph PowerShell を使って Microsoft Entra アクティビティ ログを分析する方法について説明します。

### 前提条件

- Microsoft Entra ID P1 または P2 ライセンスが関連付けられている動作中の Microsoft Entra テナント。
- 必要なアクセス許可に同意するには、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)が必要です。

### Microsoft Graph エクスプローラーを使用してレポートにアクセスする

すべての前提条件を構成すると、Microsoft Graph でアクティビティ ログのクエリを実行できます。 Microsoft Graph API は、大量のアクティビティ データをプルする用途に合わせて設計されていません。 API を使用して大量のアクティビティ データをプルすると、改ページ位置の自動修正とパフォーマンスの問題が発生します。 アクティビティ ログへの Microsoft Graph クエリの詳細については、「[アクティビティ レポート API の概要](https://learn.microsoft.com/ja-jp/graph/api/resources/azure-ad-auditlog-overview)」を参照してください

1. [Microsoft Graph Explorer ツール](https://aka.ms/ge)を起動します。
2. プロファイルを選択し、**[アクセス許可の変更]** を選択します。
3. 次の必要なアクセス許可に同意します。

    - `AuditLog.Read.All`
    - `Directory.Read.All`
4. 次のいずれかのクエリを使用して、アクティビティ ログにアクセスするために Microsoft Graph の使用を開始します。

    - 取得 `https://graph.microsoft.com/v1.0/auditLogs/directoryAudits`
    - 取得 `https://graph.microsoft.com/v1.0/auditLogs/signIns`
    - 取得 `https://graph.microsoft.com/v1.0/auditLogs/provisioning`
    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps`

    [Image: Microsoft Graph のアクティビティ ログ GET クエリのスクリーンショット。]

#### クエリを微調整する

特定のアクティビティ ログ エントリを検索するには、$filter および createdDateTime クエリ パラメーターと、使用できるプロパティの 1 つを使います。 次のクエリの一部では、`beta` エンドポイントを使用します。 ベータ エンドポイントは変更される可能性があり、運用環境での使用は推奨されません。

- [サインイン ログのプロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/signin#properties)
- [サインアップ ログのプロパティ (プレビュー)](https://learn.microsoft.com/ja-jp/graph/api/resources/selfservicesignup#properties)
- [監査ログのプロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit#properties)

##### サインイン クエリのサンプル

サインイン アクティビティに次のクエリを使用してみてください。

- 条件付きアクセスが失敗したサインイン試行の場合:

    - 取得 `https://graph.microsoft.com/v1.0/auditLogs/signIns?$filter=conditionalAccessStatus eq 'failure'`
    - 要求がタイムアウトしないように、日付フィルターを使用することを検討してください。
- 特定の期間中の特定のアプリケーションへのサインインを見つけるには:

    - 取得 `https://graph.microsoft.com/v1.0/auditLogs/signIns?$filter=(createdDateTime ge 2024-01-13T14:13:32Z and createdDateTime le 2024-01-14T17:43:26Z) and appId eq 'APP ID'`
- 非対話型サインインの場合:

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signIns?$filter=(createdDateTime ge 2024-01-13T14:13:32Z and createdDateTime le 2024-01-14T17:43:26Z) and signInEventTypes/any(t: t eq 'nonInteractiveUser')`
- サービス プリンシパルのサインインの場合:

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signIns?$filter=(createdDateTime ge 2024-01-13T14:13:32Z and createdDateTime le 2024-01-14T17:43:26Z) and signInEventTypes/any(t: t eq 'servicePrincipal')`
- マネージド ID のサインインの場合:

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signIns?$filter=(createdDateTime ge 2024-01-13T14:13:32Z and createdDateTime le 2024-01-14T17:43:26Z) and signInEventTypes/any(t: t eq 'managedIdentity')`
- ユーザーの認証方法を取得するには:

    - 取得 `https://graph.microsoft.com/beta/users/{userObjectId}/authentication/methods`
    - `UserAuthenticationMethod.Read.All` 権限が必要です。
- ユーザー登録の詳細レポートを表示するには:

    - 取得 `https://graph.microsoft.com/beta/reports/authenticationMethods/userRegistrationDetails`
    - `UserAuthenticationMethod.Read.All` 権限が必要です。
- 特定のユーザーの登録の詳細:

    - 取得 `https://graph.microsoft.com/beta/reports/authenticationMethods/userRegistrationDetails/{userId}`
    - `UserAuthenticationMethod.Read.All` 権限が必要です。

##### サインアップ クエリのサンプル (プレビュー)

[外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations)でのサインアップ アクティビティには、次のクエリを使用してみてください。

- ユーザー オブジェクトの作成など、特定の手順で失敗または中断されたサインアップ試行を検索するには:

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps?$filter=status/errorCode ne 0 and signUpStage eq 'userCreation'`
- 電子メールの検証中に失敗したサインアップ試行を見つけるには:

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps?$filter=status/errorCode eq 1002013 and signUpStage eq 'credentialValidation'`

    注

    エラー コード 1002013は、サインアップ フローの予期された (および成功した) 割り込みを示します。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-troubleshoot-sign-up-errors#sign-up-error-codes)
- 日付範囲内の登録に関しては：

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps?$filter=(createdDateTime ge 2024-01-13T14:13:32Z and createdDateTime le 2024-01-14T17:43:26Z)`
- 特定のアプリケーションのサインアップの場合:

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps?$filter=appId eq 'AppId'`
- ローカル アカウントのサインアップの場合:

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps?$filter=signUpIdentityProvider eq 'Email OTP' or signUpIdentityProvider eq 'Email Password'`
- ソーシャル アカウントのサインアップの場合 (この例では Google):

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps?$filter=signUpIdentityProvider eq 'Google'`
- 特定のユーザーのエントリを表示するには (例: `user@contoso.com`)。

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps?$filter=signUpIdentity/signUpIdentifier eq 'user@contoso.com'`
- 特定の関連付け ID に一致するエントリを検索するには:

    - 取得 `https://graph.microsoft.com/beta/auditLogs/signUps?$filter=correlationId eq 'CorrelationId'`
- 関連付け ID を使用して、特定のサインアップに対応するサインイン ログ エントリを検索するには:

    - 取得 `https://graph.microsoft.com/v1.0/auditLogs/signIns?$filter=correlationId eq 'CorrelationId'`

#### 関連する API

標準的なサインインと監査のログに慣れたら、次のような他の API を試してみてください。

- [Identity Protection API](https://learn.microsoft.com/ja-jp/graph/api/resources/identityprotection-overview)
- [プロビジョニング ログ API](https://learn.microsoft.com/ja-jp/graph/api/resources/provisioningobjectsummary)

### Microsoft Graph PowerShell を使用してレポートにアクセスする

PowerShell を使って Microsoft Entra レポート API にアクセスできます。 詳しくは、「[Microsoft Graph PowerShell の概要](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)」をご覧ください。

Microsoft Graph PowerShell コマンドレット:

- **監査ログ:**`Get-MgAuditLogDirectoryAudit`
- **サインイン ログ:**`Get-MgAuditLogSignIn`
- **プロビジョニング ログ:**`Get-MgAuditLogProvisioning`
- [レポート関連の Microsoft Graph PowerShell コマンドレット](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.reports/)のリスト全体を確認します。

### 一般的なエラー

**エラー: テナントが B2C でない、またはテナントに Premium ライセンスがありません**: サインイン レポートにアクセスするには、Microsoft Entra ID P1 または P2 ライセンスが必要です。 サインインへのアクセス中にこのようなエラー メッセージが表示された場合は、テナントに Microsoft Entra ID P1 ライセンスがあることを確認してください。

**エラー: 許可されているロールのユーザーではありません**: API を使用して監査ログやサインインにアクセスしようとしたときにこのエラー メッセージが表示された場合は、ご利用のアカウントが、Microsoft Entra テナントの**セキュリティ閲覧者**または**レポート閲覧者**のロールに属していることを確認してください。

**エラー: アプリケーションに Microsoft Entra ID の 'ディレクトリ データの読み取り' または 'すべての監査ログ データの読み取り' のアクセス許可がありません**: Microsoft Graph を使ってアクティビティ ログにアクセスするには、アプリケーションに `AuditLog.Read.All` または `Directory.Read.All` のアクセス許可が必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-analyze-provisioning-logs"} -->
## Microsoft Entra プロビジョニング ログを分析する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-provisioning-logs
- Service: entra-id / monitoring-health
- Article date: 2026-08-03
- Summary: 管理センター、Microsoft Graph、および Microsoft MCP Server for Enterprise を使用して、Microsoft Entraプロビジョニング ログを表示、ダウンロード、分析する方法について説明します。

Microsoft Entra プロビジョニング ログには、テナントで発生するプロビジョニング イベントに関する詳細が示されます。 プロビジョニング ログにキャプチャされた情報を使用して、プロビジョニングされているユーザーに関する問題のトラブルシューティングに役立てることができます。

この記事では、プロビジョニング ログの表示とダウンロード、Microsoft Graphまたは Microsoft MCP Server for Enterprise を使用したログの分析、一般的なエラーのトラブルシューティングを行う方法について説明します。

### Prerequisites

- Microsoft Entra ID P1 または P2 ライセンスが関連付けられている動作中の Microsoft Entra テナント。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) は、プロビジョニング ログにアクセスするために必要な最小限の特権ロールです。
- アプリケーション所有者は、自分が所有するログ アプリケーションを表示することもできます。
    - タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。

### プロビジョニング ログの表示方法

プロビジョニング ログを表示または分析するには、いくつかの方法があります。

- Microsoft Entra 管理センターで表示します。
- 診断設定を使用して [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics) にログをストリーミングします。
- [ワークブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks) テンプレートを使用してログを分析します。
- [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/provisioningobjectsummary) を使用してプログラムでログにアクセスします。
- [Microsoft MCP Server for Enterprise](https://learn.microsoft.com/ja-jp/graph/mcp-server/overview) を使用して、ログに関する自然言語の質問をします。
- [ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs) CSV または JSON ファイルとしてダウンロードします。

Microsoft Entra 管理センターでログにアクセスするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視& 正常性**&gt;**プロビジョニング ログ**を参照します。

### プロビジョニング ログをダウンロードする方法

プロビジョニング ログをダウンロードするには、[プロビジョニング **ログ**] ページから **[ダウンロード**] を選択します。 ダウンロードのサイズと時間を削減するために、フィルターはできるだけ具体的に設定してください。

[Image: プロビジョニング ログのダウンロード ボタンのスクリーンショット。]

#### CSV 形式

CSV のダウンロードには、次の 3 つのファイルが含まれます。

- **ProvisioningLogs**: プロビジョニング手順と変更されたプロパティを除くすべてのログをダウンロードします。
- **ProvisioningLogs\_ProvisioningSteps**: プロビジョニング手順と変更 ID が含まれています。 変更 ID を使用すると、イベントを他の 2 つのファイルと結合できます。
- **ProvisioningLogs\_ModifiedProperties**: 変更された属性と変更 ID が含まれます。 変更 ID を使用すると、イベントを他の 2 つのファイルと結合できます。

#### JSON 形式

JSON ファイルを開くには、 [Microsoft Visual Studio Code](https://aka.ms/vscode) などのテキスト エディターを使用します。 Visual Studio Code では構文の強調表示があるため、ファイルが読み取りやすくなります。 [また、Microsoft Edge](https://aka.ms/msedge) などの編集できない形式のブラウザーを使用して JSON ファイルを開くこともできます。

##### JSON ファイルを読みやすくする

JSON ファイルは、ダウンロードのサイズを削減するための形式でダウンロードされます。 この形式では、ペイロードが読みにくくなる場合があります。 ファイルを準備するには、次の 2 つのオプションがあります:

- [Visual Studio Code を使用して JSON を書式設定します](https://code.visualstudio.com/docs/languages/json#_formatting)。
- PowerShell を使用して JSON を書式設定する。 このスクリプトによって、JSON はタブとスペースを含む形式で出力されます。

    `$JSONContent = Get-Content -Path "<PATH TO THE PROVISIONING LOGS FILE>" | ConvertFrom-JSON`

    `$JSONContent | ConvertTo-Json > <PATH TO OUTPUT THE JSON FILE>`

##### JSON ファイルを解析する

使い慣れた任意のプログラミング言語を使用できます。 PowerShell の例を以下に示します。

- [JSON ファイルを読み取る](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.utility/convertfrom-json):

    `$JSONContent = Get-Content -Path "<PATH TO THE PROVISIONING LOGS FILE>" | ConvertFrom-JSON`

これで、自分のシナリオに従ってデータを解析できます。 いくつかの例を次に示します。

- JSON ファイル内のすべてのジョブ ID を出力します。

    `foreach ($provitem in $JSONContent) { $provitem.jobId }`
- アクションが "create" であったイベントのすべての変更 ID を出力します。

    ```powershell
    foreach ($provitem in $JSONContent) {
       if ($provItem.action -eq 'Create') {
           $provitem.changeId
       }
    }
    ```

### 知っておくべきこと

プロビジョニング ログを分析するためのいくつかのヒントと考慮事項を以下に示します:

- 報告されたプロビジョニング データは、Premium Edition の場合は 30 日間、Free Edition の場合は 7 日間、Microsoft Entra 管理センターに保存されます。 プロビジョニング ログは、30 日を超えて保持するために [Azure Monitor ログ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics) にルーティングできます。
- 変更 ID 属性を一意の識別子として使用できるので、製品サポートとやり取りするときに役立ちます。
- スコープに含まれていないユーザーの場合、スキップされたイベントが表示されることがあります。

    - 例 1: スコープを `all users and groups` に設定し、スコープ フィルタをセットアップしている場合、スコープ抽出条件を満たしていないユーザーのスキップされたログが表示されることがあります。
    - 例 2: スコープを `assigned users and groups` に設定している場合、ユーザーがアプリケーションに割り当てられていなくても、ログ内のユーザーがスキップ済みとして表示され続ける可能性があります。 プロビジョニング サービスがディレクトリから変更を受け取る方法により、これらのユーザーが表示されます。
- プロビジョニング ログには、ロールのインポートは表示されません (アマゾン ウェブ サービス、Salesforce、Zendesk に適用されます)。 ロールのインポートのログは、監査ログで確認できます。
- 一部のエラー コードには、名前に `AzureActiveDirectory` が含まれています。 これらのエラー コードは Microsoft Entra ID を参照していますが、Azure Active Directory からブランドを変更できませんでした。

### Microsoft MCP Server for Enterprise (プレビュー) を使用してプロビジョニング ログを分析する

MCP Server for Enterprise Microsoft、自然言語プロンプトを使用して、MCP 対応 AI クライアントのプロビジョニング ログを分析できます。 サーバーは、プロンプトを読み取り専用のMicrosoft Graph要求に変換し、委任されたアクセス許可を使用して要求を実行し、応答を要約します。

Important

MCP Server for Enterprise Microsoftはプレビュー段階です。 現在、グローバル サービスでのみ使用でき、読み取り専用操作をサポートしています。 現在の可用性と制限については、[Microsoft MCP Server for Enterprise の概要](https://github.com/mcp/microsoft/EnterpriseMCP)を確認してください。

#### Prerequisites

この記事の前提条件に加えて、次の手順を実行します。

- 管理者は[、MCP Server for Enterprise Microsoftプロビジョニングし、MCP クライアントを構成する](https://github.com/mcp/microsoft/EnterpriseMCP)必要があります。
- MCP クライアントには、委任されたアクセス許可 `MCP.ProvisioningLog.Read.All` 付与する必要があります。
- サポートされているMicrosoft Entraロールを持つ職場または学校アカウントで MCP クライアントにサインインする必要があります。 レポート閲覧者は、サポートされている最小限の特権ロールです。

Microsoft MCP Server for Enterprise では、委任されたユーザー対話型アクセスのみがサポートされます。 アプリケーションのみのアクセスはサポートされていません。

#### プロビジョニング アクティビティについて質問する

構成した MCP クライアントを開き、必要に応じてエージェント モードを選択し、調査するプロビジョニング アクティビティについて特定の質問をします。 例えば次が挙げられます。

| Scenario | プロンプトの例 |
| --- | --- |
| 最近のエラーを確認する | `Show me recent provisioning errors.` |
| ユーザーの履歴を確認する | `Show recent provisioning events for the user with ID <user-id>.` |
| 成功した作成を確認する | `Show me recent successful provisioning create operations.` |
| 正常な削除を確認する | `Were any users successfully deleted by the provisioning service?` |
| 成功した無効化を確認する | `Were any users successfully disabled by the provisioning service?` |

可能な場合は、表示名の代わりにオブジェクト ID を使用します。 時間範囲、アプリケーション名、状態、またはプロビジョニング アクションを含めて、要求をより具体的にすることもできます。

AI エージェントは、MCP サーバーのクエリ候補ツールを使用して適切なMicrosoft Graph要求を選択し、読み取り専用のMicrosoft Graph ツールを使用して要求を実行できます。 たとえば、プロンプト `Show me recent provisioning errors` では、次の要求が発生する可能性があります。

```http
GET https://graph.microsoft.com/v1.0/auditLogs/provisioning?$filter=provisioningStatusInfo/status eq 'failure'&$orderby=activityDateTime desc&$top=5
```

プロンプト `Show recent provisioning events for the user with ID <user-id>` によって、次の要求が発生する可能性があります。

```http
GET https://graph.microsoft.com/v1.0/auditLogs/provisioning?$filter=sourceIdentity/id eq '<user-id>'&$orderby=activityDateTime desc&$top=5
```

プロビジョニング ログ API では、 `$filter`、 `$orderby`、 `$top`、および `$skiptoken`がサポートされます。 フィルターでは大文字と小文字が区別されます。 サポートされているプロパティとクエリ動作については、「 [provisioningObjectSummary の一覧表示](https://learn.microsoft.com/ja-jp/graph/api/provisioningobjectsummary-list)」を参照してください。

Tip

概要に依存する前に、AI エージェントによって表示されるMicrosoft Graph要求を確認します。 フィルター、オブジェクト ID、状態値、アクション、時間範囲が調査と一致することを確認します。 `provisioningSteps`、`modifiedProperties`、または`provisioningStatusInfo`からさらにレコードや詳細が必要な場合は、フォローアップの質問をします。

#### 知っておくべきこと

- MCP サーバーは、ジョブの再起動、オブジェクトのプロビジョニング、またはプロビジョニング構成の変更を行うことはできません。 書き込み操作には、Microsoft Entra 管理センター、Microsoft Graph、または PowerShell を使用します。
- Microsoft MCP Server for Enterprise では、プロビジョニング ログ データと同じMicrosoft Graphクエリが実行され、同じプロビジョニング ログ保有期間が適用されます。
- MCP 要求には、Microsoft Graph調整と MCP サーバーの制限が適用されます。
- MCP サーバーは、サインインしているユーザーのロールと、MCP クライアントに付与された委任されたスコープを優先します。 プロビジョニング データへのユーザーのアクセスは拡張されません。
- 自然言語の応答は生成された概要です。 調査とサポート ケースについては、基になるMicrosoft Graph要求を確認し、応答からの`changeId`、`jobId`、オブジェクト ID などの識別子を使用します。

### エラー コード

次の表を利用して、プロビジョニング ログに記録されているエラーの解決方法をより深く理解してください。

| エラー コード | Description |
| --- | --- |
| Conflict,EntryConflict | Microsoft Entra ID またはアプリケーションで、競合している属性値を修正してください。または、競合しているユーザー アカウントが一致し、取って代わられたと思われるかどうか、一致する属性の構成を確認します。 一致する属性の構成の詳細については、「 [Microsoft Entra ID で SaaS アプリケーションのユーザー プロビジョニング属性マッピングをカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)する」を参照してください。 |
| TooManyRequests | ターゲット アプリは多すぎる要求を受け取っているため、ユーザーを更新するこの試行を拒否しました。 行うことは何もありません。 この試行は自動的に再試行され、この問題が Microsoft に通知されました。 |
| InternalServerError | ターゲット アプリから予期しないエラーが返されました。 ターゲット アプリケーションに関するサービスの問題によって、その動作が妨げられている可能性があります。 この試行は 40 分で自動的に再試行されます。 |
| InsufficientRights,MethodNotAllowed,NotPermitted,Unauthorized | Microsoft Entra ID はターゲット アプリケーションによって認証されましたが、更新を実行する権限がありませんでした。 ターゲット アプリケーションによって提供された手順と、それぞれのアプリケーションを確認してください。 詳細については、 [アプリケーションと Microsoft Entra ID の統合に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)を参照してください。 |
| UnprocessableEntity | ターゲット アプリケーションが予期しない応答を返しました。 ターゲット アプリケーションの構成が正しくないか、ターゲット アプリケーションに関するサービスの問題によって、その動作が妨げられている可能性があります。 |
| WebExceptionProtocolError | ターゲット アプリケーションへの接続中に HTTP プロトコル エラーが発生しました。 行うことは何もありません。 この試行は 40 分で自動的に再試行されます。 |
| InvalidAnchor | プロビジョニング サービスによって以前作成または照合されたユーザーは存在しなくなりました。 そのユーザーが存在することを確認してください。 すべてのユーザーの新しい照合を強制するには、Microsoft Graph API を使用して [ジョブを再起動します](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-restart?tabs=http&view=graph-rest-beta&preserve-view=true)。プロビジョニングを再開すると初期サイクルがトリガーされ、完了するまで時間がかかる場合があります。 また、プロビジョニングを再開することで、プロビジョニング サービスによって操作に使用されるキャッシュも削除されます。 これは、テナント内のすべてのユーザーとグループを再評価する必要があること、および特定のプロビジョニング イベントが削除される可能性があることを意味します。 |
| NotImplemented | ターゲット アプリから予期しない応答が返されました。 アプリの構成が正しくないか、ターゲット アプリに関するサービスの問題によって、その動作が妨げられている可能性があります。 ターゲット アプリケーションによって提供された手順と、それぞれのアプリケーションを確認してください。 詳細については、 [アプリケーションと Microsoft Entra ID の統合に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)を参照してください。 |
| MandatoryFieldsMissing,MissingValues | 必須の値がないため、ユーザーを作成できませんでした。 ソース レコード内の不足している属性値を修正するか、一致する属性の構成を調べて、必須フィールドが省略されていないことを確認してください。 詳細については、「 [Microsoft Entra ID で SaaS アプリケーションのユーザー プロビジョニング属性マッピングをカスタマイズする」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)参照してください。 |
| SchemaAttributeNotFound | ターゲット アプリケーションに存在しない属性が指定されたため、操作を実行できませんでした。 [「Microsoft Entra ID で SaaS アプリケーションのユーザー プロビジョニング属性マッピングをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)」を参照して、構成が正しいことを確認します。 |
| InternalError | Microsoft Entra プロビジョニング サービス内で内部サービス エラーが発生しました。 行うことは何もありません。 この試行は 40 分で自動的に再試行されます。 |
| InvalidDomain | 属性値に無効なドメイン名が含まれているため、操作を実行できませんでした。 ユーザーのドメイン名を更新するか、ターゲット アプリケーションの許可リストにそのドメイン名を追加します。 |
| Timeout | ターゲット アプリケーションが応答するのに時間がかかりすぎたため、操作を完了できませんでした。 行うことは何もありません。 この試行は 40 分で自動的に再試行されます。 |
| LicenseLimitExceeded | このユーザーが使用できるライセンスがないため、ターゲット アプリケーションでユーザーを作成できませんでした。 ターゲット アプリケーションの追加ライセンスを購入してください。または、ユーザーの割り当てと属性マッピングの構成を調べて、正しい属性で正しいユーザーが割り当てられていることを確認してください。 |
| DuplicateTargetEntries | ターゲット アプリケーションの複数のユーザーに、構成済みの一致する属性があると検出されたため、操作を完了できませんでした。 重複しているユーザーをターゲット アプリケーションから削除するか、属性マッピングを再構成してください。 詳細については、「 [Microsoft Entra ID で SaaS アプリケーションのユーザー プロビジョニング属性マッピングをカスタマイズする」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)参照してください。 |
| DuplicateSourceEntries | 複数のユーザーに、構成済みの一致する属性があると検出されたため、操作を完了できませんでした。 重複しているユーザーを削除するか、属性マッピングを再構成してください。 詳細については、「 [Microsoft Entra ID で SaaS アプリケーションのユーザー プロビジョニング属性マッピングをカスタマイズする」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)参照してください。 |
| ImportSkipped | システムでは、各ユーザーが評価されるときに、ソース システムからのそのユーザーのインポートが試みられます。 このエラーが発生するのは一般的に、インポートされるユーザーが、属性マッピングで定義されている一致するプロパティを持っていない場合です。 一致する属性のユーザー オブジェクトに値が指定されていない場合、システムではスコープ、一致、またはエクスポートの変更を評価することはできません。 このエラーが発生したとしても、ユーザーのスコープをまだ評価していないため、このユーザーがスコープ内にあることを示すものではありません。 |
| EntrySynchronizationSkipped | プロビジョニング サービスによってソース システムに対するクエリが正常に実行され、ユーザーが識別されました。 ユーザーに対してこれ以上の操作は行われず、スキップされました。 ユーザーがスコープ外にあるか、ユーザーがターゲット システムに既に存在していて、それ以上の変更は必要でない可能性があります。 |
| SystemForCrossDomainIdentityManagementMultipleEntriesInResponse | ユーザーまたはグループを取得する GET 要求に対し、複数のユーザーまたはグループが応答で受信されました。 システムでは、1 つのユーザーまたはグループだけを応答で受信することが想定されています。 たとえば、 [GET グループ要求](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#get-group) を実行してグループを取得し、メンバーを除外するフィルターを指定し、System for Cross-Domain Identity Management (SCIM) エンドポイントがメンバーを返すと、このエラーが表示されます。 |
| SystemForCrossDomainIdentityManagementServiceIncompatible | Microsoft Entra プロビジョニング サービスでは、Microsoft 以外のアプリケーションからの応答を解析できません。 アプリケーション開発者と協力して、SCIM サーバーが [Microsoft Entra SCIM クライアント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#understand-the-azure-ad-scim-implementation)と互換性があることを確認してください。 |
| SchemaPropertyCanOnlyAcceptValue | ターゲット システムのプロパティは、1 つの値のみを受け入れることができますが、ソース システムのプロパティには複数の値があります。 エラーをスローしているプロパティに 1 つの値の属性をマップするか、ソースの値を単一の値になるように更新するか、マッピングから属性を削除してください。 |

### テナント間同期のエラー コード

次の表を使用して、 [テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)のプロビジョニング ログで見つけたエラーを解決する方法を理解します。 一部のエラー コードは複数の原因にマップされるため、同じエラー コードに対して複数の行があります。

| エラー コード | Cause | Solution |
| --- | --- | --- |
| AzureActiveDirectoryForbidden | dirSync enabled プロパティは true に設定されています。 その結果、プロビジョニング サービスは immutableId や extensionProperty1-15 などのユーザー プロパティを更新できません。 | エラーを防ぐために、属性マッピングから属性を削除します。 |
| AzureActiveDirectoryForbidden | 外部コラボレーションの設定により招待がブロックされました。 | ユーザー設定に移動し、 [外部コラボレーション設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure) が許可されていることを確認します。 |
| AzureActiveDirectoryCannotUpdateObjectsOriginatedInExternalService | ユーザーの権限のソースは Exchange Online です。 プロビジョニング サービスは、ユーザーの 1 つ以上の交換属性を更新できません (例: extensionAttribute 1 - 15)。 これは、dirSyncEnabled プロパティが "True" から "False" に変更されたときに、ターゲット テナントに存在していたユーザーに影響します。 | ターゲット テナントの [Exchange Online](https://learn.microsoft.com/ja-jp/powershell/module/exchange/set-mailuser?view=exchange-ps&preserve-view=true) で属性を直接更新します。 例: `Set-MailUser -Identity CloudMailUser5 -CustomAttribute2 "Updated with EXO PowerShell"` |
| AzureActiveDirectoryCannotUpdateObjectsOriginatedInExternalService | 同期エンジンは、ターゲット テナント内の 1 つ以上のユーザー プロパティを更新できませんでした。権限ソース (SOA) が適用されるため、Microsoft Graph API で操作が失敗しました。 現在、次のプロパティが一覧に表示されます。`Mail``showInAddressList` | 場合によっては (たとえば、`showInAddressList` プロパティがユーザー更新の一部である場合)、同期エンジンは問題のあるプロパティなしで (ユーザー) 更新を自動的に再試行することがあります。 それ以外の場合は、ターゲット テナントでプロパティを直接更新する必要があります。 |
| AzureDirectoryB2BManagementPolicyCheckFailure | 自動引き換えを許可するテナント間同期ポリシーが失敗しました。同期エンジンは、ターゲット テナントの管理者が、自動引き換えを許可する受信テナント間同期ポリシーを作成していることを確認します。 また、同期エンジンは、ソース テナントの管理者が自動引き換えのために送信ポリシーを有効にしたかどうかも確認します。 | ソース テナントとターゲット テナントの両方で、自動引き換え設定が有効になっていたことを確認します。 詳細については、「 [自動引き換え設定](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#automatic-redemption-setting)」を参照してください。 |
| AzureActiveDirectoryQuotaLimitExceeded | テナント内のオブジェクトの数がディレクトリの制限を超えています。Microsoft Entra ID には、テナントで作成できるオブジェクトの数に制限があります。 | クォータを増やすことができるかどうかを確認します。 ディレクトリの制限とクォータを増やす手順については、 [Microsoft Entra サービスの制限と制限](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)を参照してください。 |
| InvitationCreationFailure | Microsoft Entra プロビジョニング サービスが、ターゲット テナント内のユーザーを招待しようとしました。 その招待は失敗しました。 | 詳細な調査には、サポートへの問い合わせが必要になる可能性があります。 |
| InvitationCreationFailureUserAccountDisabled | Microsoft Entra プロビジョニング サービスが、ターゲット テナント内のユーザーを招待しようとしました。 その招待は失敗しました。 | ユーザーはターゲット テナントに存在しますが、そのアカウントは無効になっており、招待は保留中です。 ターゲット テナントでユーザー アカウントを有効にし、もう一度ユーザーのプロビジョニングを試みます。 |
| InvitationCreationFailureInvalidPropertyValue | 考えられる原因:\* プライマリ SMTP アドレスが無効な値です。\* UserType はゲストまたはメンバーではありません\* グループ メール アドレスはサポートされていません | 考えられる解決策:\* プライマリ SMTP アドレスが無効な値です。 この問題を解決するには、ソース ユーザーのメール プロパティを更新する必要がある可能性が高いです。 詳細については、「[Microsoft 365 へのディレクトリ同期の準備](https://aka.ms/DirectoryAttributeValidations)」を参照してください。\* userType プロパティがタイプ guest または member としてプロビジョニングされていることを確認してください。 userType 属性のマップ方法を把握するには、属性マッピングを確認してください。\* ユーザーのメール アドレスが、テナント内のグループのメール アドレスと一致しています。 2 つのオブジェクトのうちの 1 つのメール アドレスを更新してください。 |
| InvitationCreationFailureAmbiguousUser | 招待されたユーザーには、ターゲット テナント内の内部ユーザーと一致するプロキシ アドレスがあります。 プロキシ アドレスは一意である必要があります。 | このエラーを解決するには、ターゲット テナント内の既存の内部ユーザーを削除するか、同期スコープからこのユーザーを削除します。 |
| AzureActiveDirectoryCannotUpdateObjectsMasteredOnPremises | ターゲット テナント内のユーザーが最初に AD から Microsoft Entra ID に同期され、外部ユーザーに変換された場合、権限ソースは引き続きオンプレミスにあり、ユーザーを更新することはできません。 | テナント間同期ではユーザーを更新できません。 |
| EntityTypeNotSupported | グループを使って、プロビジョニングの対象ユーザーを決定できます。 グループ オブジェクトは同期できません。 | お客様による対応は必要ありません。 これはスキップされるイベントです。 オンデマンドでプロビジョニングを使っている場合は、必ずプロビジョニングするグループではなくユーザーを選んでください。 |
| AzureActiveDirectoryConflictEncountered (アジュールアクティブディレクトリの競合が発生しました) | ターゲット テナントに競合するオブジェクトがあります。 | ターゲット テナントに同じメールを持つ連絡先があるかどうかを確認します。 ターゲット テナントから連絡先を削除して、B2B ユーザーを確実に更新できるようにします。 必要に応じて、連絡先から B2B ユーザーにグループ メンバーシップを移行するように注意してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-archive-logs-to-storage-account"} -->
## アクティビティ ログをストレージ アカウントにアーカイブする方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-archive-logs-to-storage-account
- Service: entra-id / monitoring-health
- Article date: 2024-11-08
- Summary: 診断設定を通じて Microsoft Entra アクティビティ ログをストレージ アカウントにアーカイブする方法について説明します。

Microsoft Entra アクティビティ ログを [既定の保持期間](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention)よりも長く保存する必要がある場合は、ログをストレージ アカウントにアーカイブできます。 BLOB ストレージ アカウントではなく、一般的なストレージ アカウントを使用することをお勧めします。 ストレージの価格情報については、 [Azure Storage 料金計算ツール](https://azure.microsoft.com/pricing/calculator/?service=storage)を参照してください。

### 前提条件

この機能を使用するには、次が必要です。

- Azure サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、 [無料試用版にサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- `ListKeys` アクセス許可を持っている Azure ストレージ アカウント。 [ストレージ アカウントを作成する](https://learn.microsoft.com/ja-jp/azure/storage/common/storage-account-create)方法について説明します。
- Microsoft Entra テナントの "セキュリティ管理者" であるユーザー。

### ログを Azure ストレージ アカウントにアーカイブする

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**監視とヘルス**&gt;**診断設定**に移動します。 [**監査ログ**] ページまたは [**サインイン**] ページから **[設定のエクスポート]** を選択することもできます。
3. [ **+ 診断設定の追加]** を選択して新しい統合を作成するか、既存の統合の **[設定の編集]** を選択します。
4. **診断設定名**を入力します。 既存の統合を編集する場合、名前を変更することはできません。
5. ストリーミングするログ カテゴリを選択します。

1. [ **宛先の詳細** ] で、[ **ストレージ アカウントへのアーカイブ** ] チェック ボックスをオンにします。
2. メニューから適切な **サブスクリプション** と **ストレージ アカウント** を選択します。

    [Image: 診断設定のスクリーンショット]

注

診断設定ストレージの保持機能は非推奨となっています。 リテンション期間オプションが使用できたときに作成された診断設定を編集している場合、それらのフィールドは引き続き表示されます。 この変更の詳細については、「 [**診断設定のストレージリテンション期間から Azure Storage ライフサイクル管理への移行**](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/migrate-to-azure-storage-lifecycle-policy)」を参照してください。

1. [ **保存] を** 選択して設定を保存します。
2. ウィンドウを閉じて、[診断設定] ペインに戻ります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-configure-diagnostic-settings"} -->
## Microsoft Entra 診断設定を構成する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings
- Service: entra-id / monitoring-health
- Article date: 2025-06-16
- Summary: アクティビティ ログを SIEM ツールと統合するように Microsoft Entra 診断設定を構成する方法について説明します。

Microsoft Entra ID の **診断設定** を使用すると、ログを Azure Monitor と統合したり、ログをイベント ハブにストリーミングしたり、ログをストレージ アカウントにアーカイブしたりできます。 複数の診断設定を作成して、アクティビティ ログを異なる宛先に送信することができます。

この記事では、アクティビティ ログについて Microsoft Entra 診断設定を構成する手順について説明します。

### 前提条件

診断設定を構成するには、以下が必要です。

- Azure サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、 [無料試用版にサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- Microsoft Entra テナントの一般的な診断設定を作成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)アクセス。
- [属性ログ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-administrator)アクセス権を使用して[カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)ログの診断設定を作成する。
- 既に設定されている宛先。 たとえば、イベント ハブにログをストリーミングする場合は、診断設定を構成する前にイベント ハブを作成する必要があります。

### 診断設定にアクセスする方法

この記事では、Microsoft Entra ログの診断設定にアクセスする手順について説明します。 Microsoft Entra ID 以外の Azure Monitor または Azure リソースの診断設定を構成する必要がある場合は、 [Azure Monitor の診断設定を](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/diagnostic-settings)参照してください。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**モニタリングと健康**&gt;**Diagnostic 設定**に移動します。 既定では、[ **全般]** 設定が表示されます。
3. 既存の診断設定がテーブルに表示されます。 [ **設定の編集]** を選択して既存の設定を変更するか、[ **診断設定の追加]** を選択して新しい設定を作成します。

    [Image: Microsoft Entra 診断設定ページのスクリーンショット。]

#### カスタム セキュリティ属性

カスタム セキュリティ属性ログは、標準監査ログのサブセットです。 カスタム セキュリティ属性の診断設定を構成するには、 **属性ログ管理者** ロールがアクティブである必要があります。 詳細については、「 [カスタム セキュリティ属性の概要」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)参照してください。

カスタム セキュリティ属性監査ログの診断設定を構成するには、[ **カスタム セキュリティ属性**] を選択します。 診断設定を構成するプロセスは、ログの両方のカテゴリで同じです。

[Image: 診断設定のカスタム セキュリティ属性ページのスクリーンショット。]

ヒント

属性の割り当てが誤ってわかってしまわないように、カスタム セキュリティ属性監査ログとディレクトリ監査ログを別にしておくことをお勧めします。

### ログと送信先を選択する

診断設定を作成または編集するときに、含めるログとその送信先を選択できます。

#### ログのカテゴリ

1 つまたは複数、あるいはすべての利用可能なログを選択します。 一部のログは、プレビュー機能の一部である可能性があります。 ログ カテゴリを選んだ場合でも、機能が一般提供になるまでデータが表示されない可能性があります。 使用可能なログの詳細については、「 [エンドポイントにストリーミングするためのログ オプション](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-diagnostic-settings-logs-options)」を参照してください。

[Image: 診断設定のログ カテゴリのスクリーンショット。]

#### 宛先の詳細

Log Analytics ワークスペースにログを送信したり、ログをイベント ハブにストリーミングしたり、ログをストレージ アカウントにアーカイブしたりできます。 Azure Native ISV Services を使用すると、Azure Marketplace を通じてサービスにログを送信できます。 詳細については、 [Azure Native ISV サービスの概要に関](https://learn.microsoft.com/ja-jp/azure/partner-solutions/overview)するページを参照してください。

診断設定を構成する前に、宛先を設定しておく必要があります。

- [Log Analytics ワークスペースを構成する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace)
- [イベント ハブを作成する](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-create)
- [ストレージ アカウントを作成する](https://learn.microsoft.com/ja-jp/azure/storage/common/storage-account-create)

送信先を選ぶと、さらに多くのフィールドが表示されます。 表示されたフィールドから、適切なサブスクリプションと送信先を選びます。

[Image: 診断設定の宛先オプションのスクリーンショット。]

特定の宛先の診断設定の構成について詳しくは、次の記事を参照してください。

- [ログを Azure Monitor ログと統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)
- [イベント ハブにログをストリーム配信する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)
- [Azure ストレージ アカウントへのログのアーカイブ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-archive-logs-to-storage-account)

### 基本プロセス

診断設定を構成するための基本的な手順は次のとおりです。

注

送信先でログが表示され始めるまで、最大で 3 日かかる場合があります。

1. 新しい診断設定を作成するには、[ **診断設定の追加]** を選択します。
2. 名前を指定します。
3. 含めるログを選択します。
4. 表示されるドロップダウン メニューから宛先とサブスクリプションを選択します。
5. **[保存**] ボタンを選択します。

    [Image: Log Analytics ワークスペースに移動するために複数のログが選択されている [診断設定の作成] ページのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-configure-health-alert-notifications"} -->
## 正常性通知を構成する方法 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-health-alert-notifications
- Service: entra-id / monitoring-health
- Article date: 2025-07-31
- Summary: テナントの正常性を監視および改善するために、Microsoft Entra の正常性監視メールと Webhook 通知を構成する方法について説明します。

Microsoft Entra Health は、いくつかの主要な ID シナリオに対してテナント レベルのメトリックと正常性シグナルを提供します。 これらのシグナルは異常検出サービスに送られ、重大な変更が検出されたときにアラートがトリガーされます。 アラートがトリガーされたときの電子メール通知と Webhook 通知を構成できます。

この記事では、Microsoft Entra Health 監視アラートの電子メール通知と Webhook 通知を構成する方法について説明します。

Von Bedeutung

Microsoft Entra Health シナリオの監視とアラートは現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。 Microsoft Entra 管理センターのエクスペリエンスは段階的に顧客にリリースされているため、この記事で説明されているすべての機能が表示されない場合があります。

### [前提条件]

正常性監視シグナルを表示し、アラートを構成して受信するには、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に合わせて、最小限の特権アクセスを持つロールを使用することをお勧めします。

- Microsoft Entra 正常性シナリオ監視シグナルを[表示](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)するには、*Microsoft Entra P1 または P2 ライセンス*を持つテナントが必要です。
- 非試用版 [の Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)*と* 少なくとも 100 人の月間アクティブ ユーザーの両方を持つテナントは、 *アラートを表示* し、 *アラート通知を受け取るために*必要です。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、*シナリオ監視シグナル、アラート、およびアラート構成を表示*するために必要な最小限の特権ロールです。
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) は、*アラート* を更新し、*アラート通知構成*を更新するために必要な最小限の特権ロールです。
- `Group.Read.All`アクセス許可は、*グループを表示*するために必要な最小限の特権アクセス許可です。
- `HealthMonitoringAlert.Read.All`するには、アクセス許可が必要です。
- microsoft Graph APIを使用してアラートを表示および変更 するには、 アクセス許可が必要です。
- microsoft `HealthMonitoringAlertConfig.Read.All`したり、*Microsoft Graph /subscriptions API を使用して構成された Webhook 通知を表示*したりするには、アクセス許可が必要です。
- microsoft `HealthMonitoringAlertConfig.ReadWrite.All`したり、*Microsoft Graph /subscriptions API を使用して構成された Webhook 通知を表示および変更*したりするには、アクセス許可が必要です。
- タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。

注

- 新しくオンボードされたテナントには、約 30 日間アラートを生成するのに十分なデータがない可能性があります。
- 必要な Microsoft Graph のアクセス許可がないためにエラーが発生する場合は、 [Microsoft Graph のアクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-overview) を参照して、Microsoft Graph のアクセス許可の詳細を確認してください。

### 電子メール通知の受信者を決定する

ベースライン メトリックを理解し、傾向を特定できるように、Microsoft Entra Health の監視シナリオを毎日確認することをお勧めします。 また、アラートがトリガーされたときに電子メール通知を構成することも重要です。

電子メール通知は、選択したMicrosoft Entra グループに送信されます。 適切なアクセス権を持つユーザーにアラートを送信して、アラートを調査してアクションを実行することをお勧めします。 すべてのロールが同じアクションを実行できるわけではないため、次のロールを持つグループを含める場合を検討してください。

- [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)
- [Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator)
- [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)

### メール通知を構成する

電子メール通知の設定は、Microsoft Entra 管理センターまたは Microsoft Graph API を使用して、シナリオごとに構成できます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)としてサインインします。
2. **Entra ID**&gt;**Monitoring & health**&gt;**Health** に移動し、[**正常性の監視**] タブを選択します。
3. 電子メール通知を構成するシナリオを選択します。

    [Image: Microsoft Entra Health ランディング ページのスクリーンショット。]
4. [ **グループ アラート通知** ] セクションで、[ **+選択** ] または **[編集]** ボタンを選択します。

    - グループが選択されていない場合は、[ **+選択** ] ボタンが表示されます。
    - グループが既に選択されている場合は、[ **編集]** ボタンが表示されます。

    [Image: グループ アラート通知の編集ボタンのスクリーンショット。]
5. 開いたパネルから、アラートを受信するグループを選択し、[選択] ボタンを **選択** します。

    - 選択できるグループは 1 つだけです。
    - グループは、シナリオ ページの **[グループ アラート通知** ] セクションで更新されます。

選択したグループのメンバーは、次にシナリオに対してアラートがトリガーされるときに電子メール通知を受け取ります。 他のシナリオでは、このプロセスを繰り返します。

注

選択したグループに他のグループがそのグループのメンバーとして追加されている場合、通知は階層内の上位 3 つのグループにのみ送信されます。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
アラート通知を構成するには、アラートを受信する Microsoft Entra グループの ID とシナリオアラート ID が必要です。

##### グループ ID とアラートの種類を見つける

1. [Microsoft Graph Explorer](https://developer.microsoft.com/en-us/graph/graph-explorer) に少なくとも[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)としてサインインし、適切なアクセス許可に同意します。
2. ドロップダウンから HTTP メソッドとして **GET** を選択し、API バージョンを **v1.0** に設定します。
3. 次のいずれかのクエリを実行して、電子メール通知を受信するグループの `id` を見つけます。

    - グループの完全な一覧を取得するには:

    ```
    GET https://graph.microsoft.com/v1.0/groups
    ```

    - 表示名で特定のグループを取得するには:

    ```
    GET https://graph.microsoft.com/v1.0/groups?$filter=displayName eq 'GroupName'
    ```
4. アラートを受信するグループの `id` を見つけて保存します。
5. 電子メール通知のアラートの種類を選択します。

    - 考えられるアラートの種類: `unknown`、 `mfaSignInFailure`、 `managedDeviceSignInFailure`、 `compliantDeviceSignInFailure`、 `conditionalAccessBlockedSignIn`。
    - 各 `alertType` のアラートを設定できますが、アクティビティとアラートを含むシナリオから始める場合は、各 `alertType` に対して次のクエリを実行してアクティビティを確認します。 この例では、 `mfaSignInFailure`を使用します。

    ```http
    GET https://graph.microsoft.com/beta/reports/healthMonitoring/alerts?$filter=alertType eq 'mfaSignInFailure'&$select=alertType,category,createdDateTime,id
    
    ```

##### 電子メール通知を構成する

Microsoft Graph エクスプローラーで、次の PATCH クエリを実行して、アラートの電子メール通知を構成します。

- `{alertType}` を、構成する特定の `alertType` に置き換えます。
- `Object ID of the group` を、アラートを受信するグループの `Object ID` に置き換えます。
- 詳細については、「 [アラートの電子メール通知を構成する」を](https://learn.microsoft.com/ja-jp/graph/api/healthmonitoring-alertconfiguration-update?view=graph-rest-beta&preserve-view=true)参照してください。

```http
PATCH https://graph.microsoft.com/beta/reports/healthMonitoring/alertConfigurations/{alertType}
Content-Type: application/json

{
  "emailNotificationConfigurations": [
    {
      "groupId":"Object ID of the group",
      "isEnabled": true
    }
  ]
}
```

注

選択したグループに他のグループがそのグループのメンバーとして追加されている場合、通知は階層内の上位 3 つのグループにのみ送信されます。

---

### webhook 通知を構成する

Microsoft Graph の変更通知を使用すると、リソースが作成、更新、または削除されるたびに、アプリケーションでリアルタイムアラートを受信できます。 電子メール通知とは異なり、これらのアラートは、指定したセキュリティで保護された HTTPS エンドポイントに直接配信されるため、自動化されたワークフローや統合に最適です。 現在、正常性監視アラートの作成に関 *する* 変更通知は、Webhook を介してサポートされています。 Microsoft Graph では、作成、更新、および削除の操作に関する通知がサポートされていますが、現時点では、正常性監視アラートに対してのみアラート作成通知を使用できます。

通知の受信を開始するために、アプリケーションは、特定のリソース (この場合は正常性監視アラート) をサブスクライブするために、 `POST` 要求を /`subscriptions` エンドポイントに送信します。 その後、Microsoft Graph によって要求が検証され、サブスクリプションが確認されます。 サブスクリプションがアクティブになると、サブスクライブされたリソースが作成されるたびに、Microsoft Graph によって指定されたエンドポイントに通知が送信されます。 詳細については、「 [Microsoft Graph の変更通知](https://learn.microsoft.com/ja-jp/graph/change-notifications-overview)」を参照してください。

注

必要な Microsoft Graph のアクセス許可がないためにエラーが表示される場合は、 [Microsoft Graph のアクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-overview)を参照してください。

通知を受信したら、Microsoft Entra 管理センターまたは Microsoft Graph API を使用してアラートを調査する必要があります。 アラートの影響を評価する必要がある場合は、正常性監視アラート API を呼び出して影響評価データを使用できるようにする前に、ポーリングまたは短い遅延を導入することをお勧めします。 詳細については、「 [正常性シナリオのアラートを調査する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts)」を参照してください。

次の例は、正常性監視アラートの変更に対する変更をサブスクライブする基本的な要求を示しています。

#### サブスクリプション要求の例

```http
POST https://graph.microsoft.com/**beta**/subscriptions
Content-Type: application/json
{
  "changeType": "created",
  "notificationUrl": "https://webhook.azurewebsites.net/notificationClient",
  "lifecycleNotificationUrl": "https://webhook.azurewebsites.net/api/lifecycleNotifications",
  "resource": "/reports/healthmonitoring/alerts",
  "expirationDateTime": "2025-08-30T11:00:00.0000000Z",
  "clientState": "SecretClientState"
}
```

特定のアラートの種類が 1 つだけの通知をサブスクライブする場合は、次の詳細を含むように要求を修正します。

`"notificationQueryOptions":"$filter=alertType eq 'mfaSignInFailure'`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-configure-recommendation-email-notifications"} -->
## Microsoft Entra の推奨事項の電子メールを構成する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-recommendation-email-notifications
- Service: entra-id / monitoring-health
- Article date: 2026-01-20
- Summary: テナントに対して自動的に生成された Microsoft Entra 推奨メール通知設定を構成する方法について説明します。

Microsoft Entra の推奨事項は、テナントの正常性とセキュリティを監視および維持するための強力なリソースです。 テナントに対して新しい推奨事項が利用可能になると、特定のテナント管理者ロールに電子メール通知が送信されます。 これらのメールは、管理者が最新の推奨事項を確認し、迅速なアクションを実行できるようにするのに役立ちますが、テナントに対してこれらのメールをオフにすることができます。

Von Bedeutung

Microsoft Entra のレコメンデーションメールは現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### [前提条件]

テナントの Microsoft Entra 推奨メール通知設定を更新するには、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールが必要です。

### 推奨事項の電子メールには何が含まれていますか?

電子メール通知は、特定の推奨事項の基本的な概要と、Microsoft Entra 管理センターの関連領域へのリンクを提供します。 電子メールには関連ドキュメントへのリンクも含まれているため、推奨事項とその解決方法の詳細を確認できます。 これらのメールは既定で有効になっており、プロモーションメールやマーケティングメールではなく、アップセルコンテンツは含まれません。 これらのメールは純粋に情報に基づき、新しいレコメンデーションが利用可能になったときに迅速に行動できるように設計されています。

### 電子メール通知を受信するユーザー

すべての Microsoft Entra の推奨事項が電子メール通知を送信するわけではありません。 電子メール通知を送信する推奨事項では、通知を受け取る管理者ロールは異なります。 詳細については、「推奨事項の [概要」の表を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)参照してください。

### 電子メール通知の設定を更新する方法

テナントは、既定で Microsoft Entra の推奨事項の電子メールを受信するように選択されています。 これらのメールをオフにするには、次の手順に従います。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Recommendations** に移動します。
3. [ **電子メールの設定] を選択します**。

    [Image: [電子メールの設定] ボタンが強調表示されている [推奨事項] ページのスクリーンショット。]
4. 開いた **[推奨事項の電子メール設定** ] パネルで、[ **新しい推奨事項の電子メール通知を送信** する] ボックスをオフにして、[ **送信** ] ボタンを選択します。

    [Image: [電子メール通知] チェック ボックスのスクリーンショット。]

Microsoft Entra のすべての推奨事項に関するすべての電子メール通知がテナント全体でブロックされ、テナントの管理者ロールに送信されなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-customize-filter-logs"} -->
## Microsoft Entra ID でアクティビティ ログをカスタマイズおよびフィルター処理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-customize-filter-logs
- Service: entra-id / monitoring-health
- Article date: 2025-11-07
- Summary: Microsoft Entra アクティビティ ログの列とフィルターをカスタマイズして、結果を分析できるようにする方法について説明します。

サインイン ログは、ユーザー アクセスの問題をトラブルシューティングし、危険なサインイン アクティビティを調査するためによく使用されるツールです。 監査ログでは、Microsoft Entra ID でログに記録されたすべてのイベントを収集し、環境の変更を調査するために使用できます。 Microsoft Entra 管理センターでサインイン ログのビューをカスタマイズするために選択できる列は 30 以上あります。 ニーズに合わせて、監査ログとプロビジョニング ログをカスタマイズおよびフィルター処理することもできます。

この記事では、列をカスタマイズし、ログをフィルター処理して、必要な情報をより効率的に見つける方法を説明します。

### 前提条件

- 適切な Microsoft Entra ライセンスが関連付けられている、動作中の Microsoft Entra テナント。
    - ライセンス要件の完全なリストについては、「[Microsoft Entra の監視と正常性のライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)」を参照してください。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)は、アクティビティ ログにアクセスするために必要な最小特権ロールです。
    - ロールの完全な一覧については、「[タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---audit-and-sign-in-logs-least-privileged-roles)」を参照してください。

### Microsoft Entra 管理センターでアクティビティ ログにアクセスする方法

自分のサインイン履歴には https://mysignins.microsoft.com からいつでもアクセスできます。 Microsoft Entra ID の **[ユーザー]** および **[エンタープライズ アプリケーション]** からサインイン ログにアクセスすることもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **Entra ID**&gt;**監視とヘルス**&gt;**監査ログ**/**サインインログ**/**プロビジョニングログ**に移動します。

## [監査ログ](#tab/audit-logs)
Microsoft Entra 監査ログの情報を使用すると、コンプライアンスを目的としてシステム アクティビティのレコードすべてにアクセスできます。 すべてのカテゴリとアクティビティを並べ替えてフィルター処理できる、Microsoft Entra ID の **[監視と正常性]** セクションから監査ログにアクセスできます。 調査中のサービスに対する管理センターの領域の監査ログにアクセスすることもできます。

[Image: サイド メニューの監査ログ オプションのスクリーンショット。]

たとえば、Microsoft Entra グループに対する変更を調べている場合は、**[Microsoft Entra ID]**&gt;**[グループ]** から監査ログにアクセスできます。 サービスから監査ログにアクセスすると、サービスに応じてフィルターが自動的に調整されます。

[Image: [グループ] メニューの監査ログ オプションのスクリーンショット。]

#### 監査ログのレイアウトをカスタマイズする

監査ログの列をカスタマイズして、必要な情報のみを表示することができます。 **[サービス]**、**[カテゴリ]**、および **[アクティビティ]** 列は相互に関連しているため、これらの列は常に表示しておく必要があります。

[Image: 監査ログの [列] ボタンのスクリーンショット。]

#### 監査ログをフィルター処理する

**[サービス]** でログをフィルター処理すると、**[カテゴリ]** と **[アクティビティ]** の詳細が自動的に変更されます。 場合によっては、[カテゴリ] または [アクティビティ] が 1 つだけ存在する場合があります。 これらの詳細の潜在的な組み合わせをすべて示す詳細なテーブルについては、「[監査アクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)」を参照してください。

[Image: [サービス] で [条件付きアクセス] を指定した監査ログ フィルターのスクリーンショット。]

- **サービス**: 使用可能なすべてのサービスが既定で設定されますが、ドロップダウン リストからオプションを選択して一覧をフィルター処理し、1 つまたは複数にすることができます。
- **カテゴリ**: すべてのカテゴリが既定で設定されますが、フィルター処理して、ポリシーの変更や対象となる Microsoft Entra ロールのアクティブ化といったアクティビティのカテゴリを表示できます。
- **アクティビティ**: ご自身で行ったカテゴリとアクティビティ リソースの種類の選択に基づきます。 参照する特定のアクティビティを選択することも、すべてを選択することもできます。

    Microsoft Graph API の `https://graph.windows.net/<tenantdomain>/activities/auditActivityTypesV2?api-version=beta` を使用して、すべての監査アクティビティの一覧を取得できます。
- **状態**: アクティビティが成功、失敗のどちらであったかに基づいて結果を確認できます。
- **ターゲット**: アクティビティのターゲットまたは受信者を検索できます。 名前またはユーザー プリンシパル名 (UPN) の最初の数文字で検索します。 ターゲット名と UPN では大文字小文字を区別します。
- **開始ユーザー**: 名前または UPN の最初の数文字を使用して、アクティビティを開始したユーザーで検索できます。 名前と UPN では大文字小文字を区別します。
- **日付の範囲**: 返されるデータの期間を定義できます。 過去 7 日間、24 時間、またはカスタム範囲を検索できます。 カスタムの期間を選択すると、開始時刻と終了時刻を構成できます。

## [サインイン ログ](#tab/sign-in-logs)
[サインイン ログ] ページで、4 種類のサインイン ログを切り替えられます。

[Image: 4 種類のサインイン ログのスクリーンショット。]

- [**対話型のユーザー サインイン:**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-interactive-sign-ins) パスワード、MFA アプリを使用した応答、生体認証要素、QR コードなどの認証要素をユーザーが提供するサインインです。
- [**非対話型のユーザー サインイン:**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-noninteractive-sign-ins) ユーザーの代わりにクライアントによって実行されるサインインです。 このサインインの場合、ユーザーは操作または認証要素を要求されません。 たとえば、更新およびアクセスのトークンを使用した認証と承認には、ユーザーによる資格情報の入力は必要とされません。
- [**サービス プリンシパルのサインイン:**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-service-principal-sign-ins) ユーザーが関与しないアプリとサービス プリンシパルによるサインインです。 このサインインの場合、認証またはリソースへのアクセス用の資格情報が、アプリまたはサービスによって、それ自身のために提供されます。
- [**Azure リソースのマネージド ID によるサインイン:**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-managed-identity-sign-ins) Azure でシークレットが管理されている Azure リソースによるサインインです。 詳細については、「[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください。

#### サインイン ログのレイアウトをカスタマイズする

30 以上の列オプションを使用して、対話型ユーザーのサインイン ログの列をカスタマイズできます。 サインイン ログをより効果的に表示するには、少し時間を取って、ニーズに合うようにビューをカスタマイズします。

1. ログの上部にあるメニューで、**[列]** を選択します。
2. 表示する列を選択し、ウィンドウの下部にある **[保存]** を選択します。

[Image: [列] オプションが強調表示されたサインイン ログ ページのスクリーンショット。]

#### サインイン ログをフィルター処理する

サインイン ログをフィルター処理すると、特定のシナリオに一致するログをすばやく検出するのに役立ちます。 たとえば、一覧をフィルター処理して、特定の地理的な場所、特定のオペレーティング システム、または特定の種類の資格情報から発生したサインインのみを表示できます。

一部のフィルター オプションでは、さらにオプションを選択するように求められます。 プロンプトに従って、フィルター処理に必要な選択を行います。 複数のフィルターを追加できます。

1. **[フィルターの追加]** ボタンを選択し、フィルター オプションを選択して **[適用]** を選択します。

    [Image: [フィルターの追加] オプションが強調表示されたサインイン ログ ページのスクリーンショット。]
2. 特定の詳細 (要求 ID など) を入力するか、別のフィルター オプションを選択します。

    [Image: フィルターの詳細を入力するためにフィールドが開かれているフィルター オプションのスクリーンショット。]

いくつかの詳細をフィルター処理できます。 次のテーブルでは、一般的に使用されるフィルターについて説明します。 *すべてのフィルター オプションが説明されているわけではありません。*

| Filter | 説明 |
| --- | --- |
| 要求 ID | サインイン要求の一意識別子 |
| 関連付け ID | シングル サインイン試行の一部であるすべてのサインイン要求の一意識別子 |
| ユーザー | ユーザーの*ユーザー プリンシパル名 (UPN)* |
| Application | サインイン要求の対象となるアプリケーション |
| ステータス | オプションは、*[成功]*、*[失敗]*、*[中断]* |
| リソース | サインインに使用されるサービスの名前 |
| IP アドレス | サインインに使用されるクライアントの IP アドレス |
| 条件付きアクセス | オプションは、*[適用されていません]*、*[成功]*、および *[失敗]* |
| エージェントである | エージェントによって実行されるサインインには *[はい* ] を、ユーザーが実行したサインインには *[いいえ* ] を選択します。 |
| エージェントの種類 | オプションは、 *エージェント ID ユーザー*、 *エージェント ID*、 *エージェント ID ブループリント*、 *Agentic App Builder*、 *および Not Agentic* です。 |

サインイン ログ テーブルがニーズに合わせて書式設定されたので、データをより効果的に分析できるようになりました。 ログを他のツールにエクスポートすると、サインイン データを詳細に分析したり、保持したりすることができます。

列をカスタマイズし、フィルターを調整すると、同様の特性を持つログを確認するのに役立ちます。 サインインの詳細を確認するには、テーブル内の行を選択して **[アクティビティの詳細]** パネルを開きます。 パネルには、確認するタブがいくつかあります。 詳細については、「[サインイン ログ アクティビティの詳細](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)」を参照してください。

[Image: サインイン アクティビティの詳細のスクリーンショット。]

#### クライアント アプリ フィルター

サインインの実行元を確認する際は、**[クライアント アプリ]** フィルターの使用が必要になる場合があります。 [クライアント アプリ] には、**[先進認証クライアント]** と **[レガシ認証クライアント]** の 2 つのサブカテゴリがあります。 [先進認証クライアント] にはさらに、**[ブラウザー]** と **[モバイル アプリとデスクトップ クライアント]** の 2 つのサブカテゴリがあります。 [レガシ認証クライアント] にはいくつかのサブカテゴリがあり、「レガシ認証クライアントの詳細」テーブルで定義されています。

[Image: [クライアント アプリ] フィルターが選択され、カテゴリが強調表示されているスクリーンショット。]

**[ブラウザー]** のサインインには、Web ブラウザーからのサインイン試行がすべて含まれます。 ブラウザーからサインインの詳細を表示すると、**[基本情報]** タブに **[クライアント アプリ: ブラウザー]** が表示されます。

[Image: クライアント アプリの詳細が強調表示された、サインインの詳細のスクリーンショット。]

**[デバイス情報]** タブの **[ブラウザー]** に Web ブラウザーの詳細が表示されます。 ブラウザーの種類とバージョンが示されますが、場合によってはブラウザーの名前とバージョンは表示されません。 **リッチ クライアント 4.0.0.0** のように表示される場合があります。

[Image: リッチ クライアント ブラウザーの例が強調表示された、サインイン アクティビティの詳細のスクリーンショット。]

##### レガシ認証クライアントの詳細

次のテーブルに各*レガシ認証クライアント*オプションの詳細を示します。

| 名前 | 説明 |
| --- | --- |
| 認証済み SMTP | 電子メール メッセージを送信するために POP および IMAP のクライアントによって使用されます。 |
| 自動検出 | Exchange Online でメールボックスを検索して接続するために Outlook および EAS のクライアントで使用されます。 |
| Exchange ActiveSync | このフィルターにより EAS プロトコルが試行されたすべてのサインイン試行が表示されます。 |
| Exchange ActiveSync | Exchange ActiveSync を使用して Exchange Online に接続するクライアント アプリでのユーザーのすべてのサインイン試行を表示します |
| Exchange Online PowerShell | リモート PowerShell を使用して Exchange Online に接続するために使用されます。 Exchange Online PowerShell の基本認証をブロックする場合は、Exchange Online PowerShell モジュールを使用して接続する必要があります。 手順については、「多要素認証[をConnect PowerShell Exchange Onlineする方法」を参照してください](https://learn.microsoft.com/ja-jp/powershell/exchange/connect-to-exchange-online-powershell)。 |
| Exchange Web サービス | Outlook、Outlook for Mac、および Microsoft 以外のアプリによって使用されるプログラミング インターフェイスです。 |
| IMAP4 | IMAP を使用して電子メールを取得する従来のメール クライアント。 |
| MAPI over HTTP | Outlook 2010 以降で使用されます。 |
| オフライン アドレス帳 | Outlook によってダウンロードおよび使用されるアドレス一覧コレクションのコピーです。 |
| Outlook Anywhere (RPC over HTTP) | Outlook 2016 以前で使用されます。 |
| Outlook サービス | Windows 10 用のメール/カレンダー アプリで使用されます。 |
| POP3 | POP3 を使用して電子メールを取得する従来のメール クライアント。 |
| レポート Web サービス | Exchange Online でレポート データを取得するために使用されます。 |
| その他のクライアント | クライアント アプリが含まれていないまたは不明である、ユーザーによるサインインの試行をすべて表示します。 |

## [プロビジョニング ログ](#tab/provisioning-logs)
プロビジョニング ログをより効果的に表示するには、少し時間を取って、ニーズに合うようにビューをカスタマイズします。 含める列を指定し、データをフィルター処理して、表示内容を絞り込むことができます。

#### レイアウトのカスタマイズ

プロビジョニング ログには既定のビューがありますが、列をカスタマイズできます。

1. ログの上部にあるメニューで、**[列]** を選択します。
2. 表示する列を選択し、ウィンドウの下部にある **[保存]** を選択します。

[Image: 列をカスタマイズするためのボタンを示すスクリーンショット。]

### 結果をフィルター処理する

プロビジョニング データをフィルター処理する場合、一部のフィルター値はテナントに基づいて動的に入力されます。 たとえば、テナントに「作成」イベントがない場合、**[作成]** フィルター オプションは使用できません。

**ID** フィルターを使用すると、関心のある名前または ID を指定できます。 この ID は、ユーザー、グループ、ロール、またはその他のオブジェクトの場合があります。

オブジェクトの名前または ID で検索できます。 ID はシナリオによって異なります。

- *Microsoft Entra ID から SalesForce* にオブジェクトをプロビジョニングする場合、**[ソース ID]** は Microsoft Entra ID 内のユーザーのオブジェクト ID です。 **ターゲット ID** は Salesforce のユーザーの ID です。
- *Workday から Microsoft Entra ID* にプロビジョニングする場合、**[ソース ID]** は Workday ワーカーの従業員 ID です。 **[ターゲット ID]** は、Microsoft Entra ID 内のユーザーの ID です。
- [テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)のためにユーザーをプロビジョニングする場合、**ソース ID** はソース テナント内のユーザーの ID です。 **ターゲット ID** は、ターゲット テナント内のユーザーの ID です。

Note

ユーザーの名前が必ずしも **ID** 列に存在するとは限りません。 常に 1 つの ID が存在します。

**[日付]** フィルターでは、返されるデータの期間を定義できます。 次のいずれかの値になります。

- 1 か月
- 7 日間
- 30 日間
- 24 時間
- カスタム時間間隔 (開始日と終了日を構成する)

**[状態]** フィルターでは、次のいずれかを選択できます。

- 全て
- Success
- 障害
- スキップ

**[アクション]** フィルターでは、これらのアクションをフィルター処理できます。

- 作成​​
- 更新プログラム
- 削除
- 無効にする
- その他

既定のビューのフィルターに加えて、次のフィルターを設定できます。

- **ジョブ ID**: プロビジョニングを有効にした各アプリケーションに一意のジョブ ID が関連付けられます。
- **サイクル ID**: サイクル ID は、プロビジョニング サイクルを一意に識別します。 この ID を製品サポートと共有して、このイベントが発生したサイクルを調べることができます。
- **変更 ID**: 変更 ID は、プロビジョニング イベントの一意の識別子です。 この ID を製品サポートと共有して、プロビジョニング イベントを調べることができます。
- **ソース システム**: ID のプロビジョニング元となる場所を指定できます。 たとえば、Microsoft Entra ID から ServiceNow にオブジェクトをプロビジョニングする場合、ソース システムは Microsoft Entra ID です。
- **ターゲット システム**: ID のプロビジョニング先となる場所を指定できます。 たとえば、Microsoft Entra ID から ServiceNow にオブジェクトをプロビジョニングする場合、ターゲット システムは ServiceNow です。
- **アプリケーション**: 特定の文字列が含まれている表示名またはオブジェクト ID を持つアプリケーションのレコードのみを表示できます。 [テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)の場合は、アプリケーション ID ではなく、構成のオブジェクト ID を使用します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-download-logs"} -->
## Microsoft Entra ID でログをダウンロードする方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs
- Service: entra-id / monitoring-health
- Article date: 2024-11-08
- Summary: Microsoft Entra ID で監査、サインイン、プロビジョニングのログ データをダウンロードして手動で保存する方法。

Microsoft Entra 管理センターでは、次の 3 種類のアクティビティ ログにアクセスできます。

- **[サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)**: サインインとユーザーのリソース使用状況に関する情報。
- **[監査](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)**: ユーザーやグループの管理、テナントのリソースに適用された更新など、テナントに適用された変更に関する情報。
- **[プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)**: ServiceNow でのグループの作成や、Workday からインポートされたユーザーなど、プロビジョニング サービスによって実行されるアクティビティ。

Microsoft Entra ID は、ライセンスに応じて、特定の期間のアクティビティ ログを保存します。 詳細については、[Microsoft Entra データ保持](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention)に関するページを参照してください。 ログをダウンロードすれば、ログの保存期間を制御できます。 この記事では、Microsoft Entra ID でアクティビティ ログをダウンロードする方法について説明します。

### 前提条件

- 適切な Microsoft Entra ライセンスが関連付けられている、動作中の Microsoft Entra テナント。
    - ライセンス要件の完全なリストについては、「[Microsoft Entra の監視と正常性のライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)」を参照してください。
- ログをダウンロードするオプションは、Microsoft Entra ID のすべてのエディションで利用できます。
- Microsoft Graph を使用してプログラムでログをダウンロードするには、[Premium ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)が必要です。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)は、Microsoft Entra のアクティビティ ログを表示するために必要な、最小限特権ロールです。

### ログのダウンロードに関する考慮事項

ログをダウンロードする前に、次の考慮事項とヒントを確認してください。

- Microsoft Entra ID では、次の形式でのダウンロードがサポートされています。
    - **CSV**
    - **JSON**
- ダウンロードしたファイルのタイムスタンプは UTC に基づいています。
- ファイルあたり、最大 100,000 件のサインイン レコードまたはプロビジョニング レコードをダウンロードできます。
- ファイルあたり、最大 250,000 件の監査レコードをダウンロードできます。
- データセットを絞り込むには、ログをダウンロードする前にフィルターを設定します。

注

大きなデータ セットをダウンロードしようとすると、Microsoft Entra 管理センターのダウンロード サービスがタイムアウトになります。 一般的にブラウザーのダウンロードが適切に機能するのは、監査ログの場合は 250,000 件未満のデータ セット、サインイン ログとプロビジョニング ログのログの場合は 100,000 件未満のデータ セットです。

ブラウザーで大きなダウンロードを完了できない問題が発生した場合は、[**レポート API**](https://learn.microsoft.com/ja-jp/graph/api/resources/azure-ad-auditlog-overview) を使用してデータをダウンロードするか、[**診断設定を使用してログをエンドポイントに送信します**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)。

注

ダウンロードしたログの列は変更されません。 Microsoft Entra 管理センターでカスタマイズした列に関係なく、出力には監査ログまたはサインイン ログのすべての詳細が含まれます。。 ただし、カスタム フィルターを設定した場合、ダウンロードしたログの出力には、そのフィルターに一致する結果のみが含まれます。

### アクティビティ ログをダウンロードする方法

アクティビティ ログには、Microsoft Entra ID の **[監査と正常性]** セクション、または作業中の Microsoft Entra ID の領域からアクセスできます。

たとえば、Microsoft Entra ID の **[グループ]** または **[ライセンス]** セクションが表示されている場合は、その領域から、そうした特定のアクティビティの監査ログに直接アクセスできます。 この方法で監査ログにアクセスすると、フィルター カテゴリが自動的に設定されます。 **[グループ]** が表示されている場合、監査ログ フィルターのカテゴリは **GroupManagement** に設定されます。

[Image: [監査ログ] オプションが強調表示されている Microsoft Entra ID のライセンス領域のスクリーンショット。]

#### 監査ログ

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **Entra ID**&gt;**監視と健康**&gt;**監査ログ**に移動します。
3. **[ダウンロード]** を選択します。
4. 開いたパネルで、**[形式]** を選択します。
5. 必要に応じて、一意のファイル名を指定します。
6. **[ダウンロード]** ボタンを選択します。 ダウンロードが処理され、ファイルが既定のダウンロード場所に送信されます。

    [Image: 監査ログのダウンロード プロセスのスクリーンショット。]

#### サインイン ログ

このセクションで扱うオプションは、サインイン ログのプレビュー エクスペリエンスと同じものです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **Entra ID**&gt;に移動し、**モニタリングとヘルス**&gt;の**サインイン ログ**を参照します。
3. **[ダウンロード]** ボタンを選択し、**[JSON]** または **[CSV]** を選択します。

    [Image: サインイン ログをダウンロードするボタン オプションのスクリーンショット。]
4. 必要に応じて、ダウンロード対象のファイルごとに一意のファイル名を指定します。
5. 1 つ以上のログに対し、**[ダウンロード]** ボタンを選択します。 ダウンロードが処理され、ファイルが既定のダウンロード場所に送信されます。

    - 対話型サインイン
    - [認証の詳細](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)のみが含まれる対話型サインイン
    - 非対話型サインイン
    - [認証の詳細](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details#authentication-details)のみが含まれる対話型ではないサインイン
    - アプリケーションのサインイン
    - マネージド ID

    [Image: サインイン ログのダウンロード オプションのスクリーンショット。]

#### プロビジョニング ログ

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **[Entra ID]**&gt;**[監視と正常性]**&gt;**[プロビジョニング ログ]** を参照します。
3. **[ダウンロード]** ボタンを選択し、**[JSON]** または **[CSV]** を選択します。
4. 必要に応じて、ダウンロード対象のファイルごとに一意のファイル名を指定します。
5. 1 つ以上のログに対し、**[ダウンロード]** ボタンを選択します。 ダウンロードが処理され、ファイルが既定のダウンロード場所に送信されます。

    - プロビジョニング ログ
    - プロビジョニング手順によるログのプロビジョニング
    - プロパティが変更されたログのプロビジョニング

    [Image: プロビジョニング ログのダウンロード オプションのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs"} -->
## Microsoft Entra ログを Azure Monitor ログと統合する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs
- Service: entra-id / monitoring-health
- Article date: 2026-01-06
- Summary: 問い合わせと分析のために Azure Active Directory のログを Microsoft Entra アクティビティ ログと統合する方法について説明します。

Microsoft Entra ID の **診断設定** を使用すると、Azure Monitor とログを統合して、サインイン アクティビティとテナント内の変更の監査証跡を他の Azure データと共に分析できます。

この記事では、Microsoft Entra ログと Azure Monitor を統合する手順について説明します。

次のタスクを実行するには、Microsoft Entra アクティビティ ログと Azure Monitor の統合を使います。

- 自分の Microsoft Entra サインイン ログと、Microsoft Defender for Cloud が発行したセキュリティ ログを比較します。
- Azure Application Insights からのアプリケーション パフォーマンス データを相関させることによって、アプリケーションのサインイン ページでのパフォーマンス ボトルネックのトラブルシューティングを行います。
- Identity Protection の危険なユーザーとリスク検出ログを分析して、環境内の脅威を検出します。
- 認証に Active Directory 認証ライブラリ (ADAL) をまだ使っているアプリケーションからのサインインを識別します。 [ADAL のサポート終了プランについて説明します。](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration)

注

Microsoft Entra ログと Azure Monitor を統合すると、Microsoft Sentinel 内で Microsoft Entra データ コネクタが自動的に有効になります。

### 前提条件

この機能を使用するには、次が必要です。

- Azure サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、 [無料試用版にサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。

- 少なくとも Microsoft Entra テナントの [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロール。

- Azure サブスクリプションの **Log Analytics ワークスペース** 。 [Log Analytics ワークスペースを作成する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)方法について説明します。

- Log Analytics ワークスペース内のデータにアクセスするためのアクセス許可。 さまざまなアクセス許可オプションとアクセス許可の構成方法については、 [Azure Monitor でのログ データとワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access) に関するページを参照してください。

### Log Analytics ワークスペースの作成

Log Analytics ワークスペースを使用すると、データの地理的な場所、サブスクリプションの境界、リソースへのアクセスなど、さまざまな要件に基づいてデータを収集できます。 [Log Analytics ワークスペースを作成する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)方法について説明します。

Microsoft Entra ID 以外の Azure リソース用に Log Analytics ワークスペースを設定する方法については、「 [Azure Monitor のリソース ログの収集と表示](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/diagnostic-settings)」を参照してください。

### ログを Azure Monitor に送信する

次の手順を使用して Microsoft Entra ID から Azure Monitor ログにログを送信します。

ヒント

この記事の手順は、開始するポータルに応じて若干異なる場合があります。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**Monitoring & health**&gt;**Diagnostic 設定**に移動します。 [**監査ログ**] ページまたは [**サインイン**] ページから **[設定のエクスポート]** を選択することもできます。
3. [ **+ 診断設定の追加]** を選択して新しい統合を作成するか、既存の統合の **[設定の編集]** を選択します。
4. **診断設定名**を入力します。 既存の統合を編集する場合、名前を変更することはできません。
5. ストリーミングするログ カテゴリを選択します。 各ログ カテゴリの説明については、「 [エンドポイントにストリーミングできる ID ログとは」](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations)を参照してください。
6. [ **宛先の詳細** ] で、[ **Log Analytics ワークスペースに送信** ] チェック ボックスをオンにします。
7. メニューから適切な **サブスクリプション** と **Log Analytics ワークスペース** を選択します。
8. **[保存**] ボタンを選択します。

    [Image: 一部の宛先の詳細が表示された診断設定のスクリーンショット。]

    Log Analytics ワークスペースにログが表示され始めるタイミングの詳細については、「 [Azure Monitor の診断設定](https://learn.microsoft.com/ja-jp/azure/azure-monitor/platform/diagnostic-settings#time-before-telemetry-gets-to-destination)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-investigate-health-scenario-alerts"} -->
## ヘルスモニタリングアラートを調査する方法 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts
- Service: entra-id / monitoring-health
- Article date: 2026-04-02
- Summary: Microsoft Entra正常性監視アラートを調査して、テナントの正常性を監視および改善する方法について説明します。

Microsoft Entra正常性の監視は、一連の正常性メトリックとインテリジェント アラートを使用して、Microsoft Entra テナントの正常性を監視するのに役立ちます。 正常性メトリックは異常検出サービス (機械学習を使用してテナントのパターンを理解) に取り込まれます。 異常検出サービスで、いずれかのテナント レベル パターンの重大な変更が識別されると、アラートがトリガーされます。

Microsoft Entra Health によって提供されるシグナルとアラートにより、テナント内の潜在的な問題を調査するための開始点が提供されます。 さまざまなシナリオとさらに多くのデータ ポイントを考慮する必要があるため、これらのアラートを効果的に調査する方法を理解することが重要です。 この記事では、一般的にアラートを調査する方法に関するガイダンスを提供します。 シナリオ固有のガイダンスについては、この記事の最後にある関連コンテンツを参照してください。

重要

Microsoft Entraヘルスシナリオの監視とアラートは現在プレビュー中です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoftは、ここに記載されている情報に関して、明示または黙示を問わず、いかなる保証も行いません。

### 前提条件

稼働状況の監視シグナルを表示し、アラートの構成と受信を行うための、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に合わせて、最小限の特権アクセスを持つロールを使用することをお勧めします。

- [Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つテナントは、Microsoft Entra正常性シナリオ監視シグナルを*view*する必要があります。
- 試用版以外の [Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持ち、かつ月にアクティブ ユーザーが100人以上いるテナントは、アラートを表示し、アラート通知を受信する必要があります。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、"シナリオ監視シグナル、アラート、アラート構成を表示する" ために必要な最小限の特権ロールです。
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) は、*アラート* を更新し、*アラート通知構成*を更新するために必要な最小限の特権ロールです。
- Microsoft Graph APIを使用してアラートを表示するには、 権限が必要です。
- Microsoft Graph API を使用してアラートを表示および変更するには、`HealthMonitoringAlert.ReadWrite.All` アクセス許可が必要です。
- ロールの完全な一覧については、「[タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#microsoft-entra-health-least-privileged-roles)」を参照してください。

注

新しくオンボードされたテナントには、約 30 日間はアラートを生成するのに十分なデータがない可能性があります。

### シグナルとアラートを調査する

[Microsoft Entra 管理センター](https://entra.microsoft.com)からMicrosoft Entra正常性監視シグナルを表示できます。 また、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/healthmonitoring-overview?view=graph-rest-beta&preserve-view=true) を使用して、シグナルのプロパティと正常性監視アラートのパブリック プレビューを表示することもできます。

アラートを受け取ったら、通常、次のデータ セットを調査する必要があります。

- **メトリック**: アラートの原因となったデータ ストリーム (正常性シグナル)。
- **影響を受けるエンティティ**: 影響を受けるエンティティの合計数。 ユーザーとアプリケーションを含めることができます。
- **アクティビティ ログ**: サインイン ログは、影響を受けるユーザーに関する詳細を提供します。 監査ログは、アプリケーション構成の変更に関する分析情報を提供します。
- **シナリオ固有のリソース**: シナリオによっては、さまざまなサービスの他の情報ソースを調査することが必要になる場合があります。 たとえば、デバイス関連のシナリオでは、Intune デバイス コンプライアンス ポリシーを確認する必要がある場合があります。

## [管理センター](#tab/admin-center)
シグナルとアラートは、Microsoft Entra 管理センターの Microsoft Entra Health 領域で使用できます。 アラートを調査している場合でも、テナントの正常性を監視する場合でも、Microsoft Entra 管理センターからのシグナルとアラートを表示できます。

##### シグナルを表示する

1. 少なくとも[レポート リーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[監視と正常性]**&gt;**[正常性]** に移動します。 サービス レベル アグリーメント (SLA) 達成ページが開きます。
3. **ヘルスモニタリング** タブを選択します。

    [Image: Microsoft Entra Health ランディング ページのスクリーンショット.]
4. 一覧からシナリオを選択します。 アクティブなアラートがあるシナリオにページが開きますが、別のシナリオのシグナルを表示する場合は、[ **すべてのシナリオ** ] フィルター ボタンを選択します。
5. [ **データ グラフの表示** ] セクションで信号を表示します。 アクティブなアラートを含むシナリオを表示する場合は、このセクションを展開する必要があります。

    - 日付範囲を変更して、過去 24 時間、7 日間、または前月を表示できます。
    - グラフの上にマウス ポインターを置くと、特定の時点のデータ ポイントが表示されます。
    - グラフの下部にある値は、選択した期間のそのシナリオの合計数です。

[Image: 多要素認証 (MFA) シナリオを必要とするサインインのスクリーンショット。]

##### アラートを調査する

**正常性監視**のランディング ページからこれらの詳細を表示するには、最初に調査するアクティブなアラートを選択します。

[Image: アクティブなアラート シナリオを含む [正常性の監視] ページのスクリーンショット。]

選択したシナリオから、調査するいくつかのオプションがあります。

[Image: アクティブなアラートの影響を受けるエンティティのスクリーンショット。]

1. ページの上部にある [ **診断の実行** ] を選択すると、 [サインイン ログ診断ツール](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-sign-in-diagnostics)が実行されます。
2. 選択したシナリオの [ **影響を受けるエンティティ** ] セクションで、調査する影響を受けるエンティティの種類として **[表示** ] を選択します。

    - 可能なエンティティには、ユーザーとアプリケーションが含まれます。
    - 問題を調査する方法の詳細については、シナリオ固有の記事へのリンクが提供されています。
    - 影響を受けるエンティティの上位 10 件が一覧表示されます。
    - 一覧から項目を選択すると、詳細な調査のためにユーザーまたはアプリケーションのプロファイル ページに移動します。
3. アラートのシグナルが [シグナル] セクション **に** 表示されます。 シグナルを確認してパターンを理解し、異常を特定します。

    - 時間枠には、異常が発生した時間が表示されます。

    [Image: アクティブなアラートのシグナルのスクリーンショット。]
4. 問題の根本原因を調査し、潜在的に解決したら、アラートを無視できます。 アクティブなアラート ページで、そのアラートのチェック ボックスをオンにし、[アラートをメニュー **としてマーク** ] を選択し、[ **無視]** を選択します。

    - Microsoft Graph API を使用する同等のアクションは、アラートの状態を `resolved` に更新することです。

    [Image: [アラートをマーク] メニューが強調表示されているアラート ページのスクリーンショット。]

##### ヘルスアラート調査ツールを使用してアラートを調査する

ヘルスアラート調査官は、Entra ヘルスアラート内に埋め込まれた Copilot スキルです。 あなたに代わって、アラートページから直接ヘルスアラートを調査し、相関分析を実行して、テナント内で対応可能な根本原因を特定できます。 調査をトリガーすると、Copilotは、推奨される修正プログラムと、Entra アクティビティ ログとテナント構成データを使用して結論を導き出す相関分析の背後にあるロジックを含む結果のレポートを投稿します。

注

ヘルスアラート調査ツールを使用するには、テナントを Microsoft Security Copilot に登録し、Security Copilot で Microsoft Entra プラグインが有効になっていることを確認する必要があります。

注

健康アラート調査担当者は、まだ「Copilotチャット」を通じてアクセスできません。 チャット エクスペリエンスは近日公開予定です。

健康アラート調査ツールを使用するには:

1. アクティブなアラート ページから、**investigate with Copilot** ボタンを選択して、正常性アラート調査を起動します。

    [Image: 「Copilot で調査」ボタンが強調表示されているアクティブなアラート ページのスクリーンショット。]
2. 正常性アラート調査担当者は、アラートの原因となった正常性メトリックと、テナントのアクティビティ ログと構成データの間で相関関係分析を実行します。 分析が完了すると、**健康状態アラートを調査**カードに検出結果の概要が表示されます。
3. [**Show Copilot investigation** ボタンをクリックすると、結果の詳細なレポートが表示され、次の手順が推奨され、根本原因が解決されます。

    [Image: Copilot調査ボタンが表示されている調査ヘルスアラートカードのスクリーンショット。]

    [Image: 結果の詳細なレポートと推奨される次の手順を示すCopilotの調査結果のスクリーンショット。]
4. 詳細を取得するには、[ **根本原因** ] 見出しと [ **その他の詳細** ] 見出しをクリックします。

## [Microsoft Graph API](#tab/microsoft-graph-api)
Microsoft Graph API を使用すると、正常性シグナルとアラートを構成するメトリックを表示し、正常性アラートの影響の概要を確認できます。

要求と応答のサンプルについては、「健康監視リストアラート オブジェクト 」を参照してください。

- `impacts` の後の応答の部分は、アラートのインパクトの概要となっています。
- `supportingData` の部分には、アラートの生成に使用される完全なクエリが含まれます。
- クエリの結果には、異常検出サービスによって特定されるすべてのものが含まれますが、アラートに直接関連しない結果が含まれる場合があります。

##### シグナルを表示する

`serviceActivity` リソースは、Microsoft Entra 管理センターで視覚化される、Microsoft Entra ヘルス監視シグナルにフィードするメトリックを取得します。 詳細については、「[serviceActivity リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceactivity?view=graph-rest-beta&preserve-view=true)」を参照してください。

1. Microsoft Graph Explorer に少なくとも Helpdesk Administrator適切なアクセス許可に同意します。
2. ドロップダウンから HTTP メソッド GET を選択し、API バージョンをベータ設定します。
3. 次のクエリを実行して、特定の間隔で多要素認証 (MFA) サインイン成功メトリックを取得します。

**要求:**

```http
GET https://graph.microsoft.com/beta/reports/serviceActivity/getMetricsForMfaSignInFailure(inclusiveIntervalStartDateTime=2023-01-01T00:00:00Z,exclusiveIntervalEndDateTime=2023-01-01T00:20:00Z,aggregationIntervalInMinutes=10)
```

**応答:**

応答には、特定の時間枠の間に成功したサインインの数が 10 分間隔で集計され、示されます。

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/beta/networkAccess/reports/$metadata#Collection(serviceActivityValueMetric)",
  "value": [
    {
      "intervalStartDateTime": "2023-01-10T00:00:00Z",
      "value": 4
    },
    {
      "intervalStartDateTime": "2023-01-10T00:10:00Z",
      "value": 5
    },
    {
      "intervalStartDateTime": "2023-01-10T00:20:00Z",
      "value": 4
    }
  ]
}
```

##### アラートを調査する

`alert` リソースタイプは、正常性監視アラートに関する詳細情報を提供し、アラートの影響概要も含まれています。 詳細については、「 [アラート リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/healthmonitoring-alert?view=graph-rest-beta&preserve-view=true)」を参照してください。

次のクエリを実行して、テナントのすべてのアクティブなアラートを取得します。

**要求:**

```http
GET https://graph.microsoft.com/beta/reports/healthMonitoring/alerts?$filter=state eq microsoft.graph.healthmonitoring.alertState'active'&$select=id, alertType
```

**応答:**

```http
HTTP/1.1 200 OK
Content-Type: application/json
{
  "@odata.context": "https://graph.microsoft.com/beta/$metadata#reports/healthMonitoring/alerts(id,alertType)",
  "value": [
    {
      "id": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
      "alertType": "mfaSignInFailure"
    },
    {
      "id": "bbbbbbbb-1111-2222-3333-cccccccccccc",
       "alertType": "managedDeviceSignInFailure"
    },
  ]
}    
```

調査するアラートの `id` を見つけて保存し、 `id` を `alertId`として使用して、次のクエリを実行します。 次のクエリでは、アラートの詳細を取得し、 `resourceSampling` プロパティを展開して、影響を受けるエンティティのユーザー ID を取得します。

**要求:**

この例では、 `mfaSignInFailure` アラートの種類を使用していますが、 `id` 値はプレースホルダー値です。 これを、調査するアラートの `id` に置き換えます。

```http
GET https://graph.microsoft.com/beta/reports/healthMonitoring/alerts/{id}?$expand=enrichment/impacts/microsoft.graph.healthmonitoring.directoryobjectimpactsummary/resourceSampling&$select=alertType, createdDateTime, enrichment'
```

**応答:**

読みやすくするために応答が短縮されます。 応答には、影響を受けるエンティティのユーザー ID が含まれます。これは、ユーザーのサインイン アクティビティに関するさらなる問い合わせで使用できます。 応答には、関連レポートのクエリも含まれます。

```http
{
  "@odata.context": "https://graph.microsoft.com/beta/$metadata#reports/healthMonitoring/alerts(enrichment/impacts/microsoft.graph.healthMonitoring.directoryObjectImpactSummary/resourceSampling())/$entity",
  "id": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
  "alertType": "mfaSignInFailure",
  "scenario": "mfa",
  "category": "authentication",
  "createdDateTime": "2025-01-10T23:59:41.1216288Z",
  "state": "resolved",
  "enrichment": {
      "state": "enriched",
      "supportingData": null,
      "impacts": [
          {
              "@odata.type": "#microsoft.graph.healthMonitoring.userImpactSummary",
              "resourceType": "User",
              "impactedCount": "4",
              "impactedCountLimitExceeded": false,
              "resourceSampling": [
                  {
                      "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
                  },
                  {
                      "id": "66aa66aa-bb77-cc88-dd99-00ee00ee00ee"
                  },
                  {
                      "id": "11bb11bb-cc22-dd33-ee44-55ff55ff55ff"
                  },
                  {
                      "id": "55ff55ff-aa66-bb77-cc88-99dd99dd99dd"
                  }
          ]    
          },
      ]
      "signals": {
          "mfaSignInFailure": "https://graph.microsoft.com//beta/reports/serviceActivity/getMetricsForMfaSignInFailure/(inclusiveIntervalStartDateTime=2024-12-26T23:59:41Z,exclusiveIntervalEndDateTime=2025-01-10T23:59:41Z)",
          "mfaSignInSuccess": "https://graph.microsoft.com//beta/reports/serviceActivity/getMetricsForMfaSignInSuccess/(inclusiveIntervalStartDateTime=2024-12-26T23:59:41Z,exclusiveIntervalEndDateTime=2025-01-10T23:59:41Z)"
      }
    }
  }    
```

影響を受けるエンティティの詳細を取得したら、サインイン アクティビティ、監査ログ、条件付きアクセスなどのトラブルシューティングを開始できます。

##### 状態を更新する

問題の根本原因を調査して解決する可能性がある場合は、アラートを解決済みとしてマークできます。 次の PATCH 要求を実行します。 Microsoft Entra 管理センターを使用する同等のアクションは、アラートの状態を **Dismissed** に更新することです。

```http
PATCH https://graph.microsoft.com/beta/reports/healthMonitoring/alerts/{alertId}
Content-Type: application/json
{
  "state": "resolved"
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-investigate-internet-access-signals"} -->
## Entra Internet Access ポリシーによってブロックされているインターネット アプリケーションを調査する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-internet-access-signals
- Service: entra-id / monitoring-health
- Article date: 2026-09-14
- Summary: Microsoft Entra Health 監視ツールを使用して、インターネット アプリケーションが Microsoft Entra インターネット アクセス ポリシーによってブロックされるシナリオを監視および調査する方法について説明します。

Microsoft Entra Health の監視には、潜在的な問題または障害の状態が検出されたときに監視およびアラートを生成できる一連のテナント レベルの正常性メトリックが用意されています。 Microsoft Entra インターネット アクセス ポリシーによってブロックされるインターネット アプリケーションなど、監視できる正常性シナリオは複数あります。

Microsoft Entra Health のしくみの詳細については、以下を参照してください。

- [Microsoft Entra Health とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)
- [Microsoft Entra の正常性監視シグナルとアラートを調査する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts)

この記事では、Microsoft Entra インターネット アクセス ポリシーによってブロックされるインターネット アプリケーションに関連する正常性メトリックと、アラートを受け取ったときに潜在的な問題をトラブルシューティングする方法について説明します。

このシナリオ:

- インターネット アプリケーションに正常にアクセスする一意のユーザーの数を集計します。
- インターネット アプリケーションにアクセスできなかった一意のユーザーの数を集計します。
- 正常にアクセスされた一意のインターネット アプリケーションの数を集計します。
- 一意のインターネット アプリケーションへの失敗したアクセスの数を集計します。

### [前提条件]

正常性監視シグナルを表示し、アラートを構成して受信するには、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に合わせて、最小限の特権アクセスを持つロールを使用することをお勧めします。

- Microsoft Entra 正常性シナリオ監視シグナルを[表示](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)するには、*Microsoft Entra P1 または P2 ライセンス*を持つテナントが必要です。
- 試用版以外の [Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持ち、かつ月にアクティブ ユーザーが100人以上いるテナントは、アラートを表示し、アラート通知を受信する必要があります。
- Microsoft Entra Internet Access ライセンスを持つテナントが必要です。 詳細については、[Global Secure Access とは？](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)のライセンスセクションをご参照ください。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、*シナリオ監視シグナル、アラート、およびアラート構成を表示*するために必要な最小限の特権ロールです。
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) は、*アラート* を更新し、*アラート通知構成*を更新するために必要な最小限の特権ロールです。
- `HealthMonitoringAlert.Read.All`するには、アクセス許可が必要です。
- Microsoft Graph API を使用してアラートを表示および変更するには、`HealthMonitoringAlert.ReadWrite.All` アクセス許可が必要です。
- タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。
- Microsoft Entra Internet Access のトラフィック ログを表示するには、[Global Secure Access Log Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-secure-access-log-reader) ロールが必要です。

### シグナルとアラートを調査する

アラートの期間、シグナルの傾向、影響を受けるエンティティを比較して、調査を開始します。 次に、影響を受けるユーザーとアプリケーションをトラフィック、サインイン、監査ログと関連付ける。

1. アラートの詳細を表示します。

    - Microsoft Entra 管理センターで、シグナル グラフ、アラートの時間枠、影響を受けるエンティティを確認します。 詳細については、「 [シグナルとアラートの調査](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts#investigate-the-signals-and-alerts)」を参照してください。
    - Microsoft Graph のガイダンスについては、 [Microsoft Graph の正常性の監視の概要に関するページ](https://learn.microsoft.com/ja-jp/graph/api/resources/healthmonitoring-overview?view=graph-rest-beta&preserve-view=true)を参照してください。
2. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
3. **Entra ID**&gt;**Monitoring & health**&gt;**Health** に移動します。 ページが開き、サービス レベル アグリーメント (SLA) の達成ページが開きます。
4. [ **ヘルス モニタリング** ] タブを選択します。
5. **Entra Internet Access Policy シナリオでブロックされているインターネット アプリケーション**を選択し、アクティブなアラートを選択します。

    [Image: 正常性監視ダッシュボードの Entra インターネット アクセス ポリシー によってブロックされたインターネット アプリケーションのシナリオを示すスクリーンショット。]
6. Microsoft Entra Internet Access コンテンツ フィルタリング ポリシーを確認します。 ポリシー ルール、リンクされたセキュリティ プロファイル、条件付きアクセスの割り当てを確認します。 詳細については、「 [グローバル セキュリティで保護されたアクセス Web コンテンツのフィルター処理を構成する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering)」を参照してください。
7. 取得ポリシーとバイパス ポリシー、ユーザーとグループの割り当てを含む、Microsoft Entra Internet Access転送プロファイルを確認します。 詳細については、「 [インターネット アクセス プロファイルの管理」を](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-internet-access-profile)参照してください。
8. [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)を確認します。 サインインにグローバル セキュリティ アクセス セキュリティ プロファイルが適用されている影響を受けるユーザーを探します。
9. [グローバル セキュリティで保護されたアクセスのトラフィック ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-view-traffic-logs)を確認します。 アラートの期間と影響を受けるユーザーまたはアプリケーションにログをフィルター処理します。 拒否されたトランザクションとその Web カテゴリを確認します。

    [Image: ブロックされたインターネットの宛先を示す Internet Access トラフィック ログのスクリーンショット。]
10. [グローバル セキュリティで保護されたアクセス監査ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-access-audit-logs)で、フィルター ポリシー、セキュリティ プロファイル、転送プロファイル、割り当ての最近の変更を確認します。

    [Image: グローバル セキュリティで保護されたアクセス サービスにフィルター処理された監査ログのスクリーンショット。]

### シグナルを理解する

アラートは、Microsoft Entra Internet Access ポリシーによってブロックされるユーザー数またはインターネット アプリケーション数の変化を示すことができます。

- 急増は、新しいフィルター処理ポリシーまたは更新されたフィルター ポリシーによって、広く使用されているアプリケーションがブロックされていること、またはセキュリティ プロファイルまたは転送プロファイルがより多くのユーザーに割り当てられたことを示している可能性があります。
- dip は、フィルター ポリシー、セキュリティ プロファイル、転送プロファイル、または割り当てが無効または絞り込まれたことを示すことができます。

変更は意図的なものである可能性があります。 ポリシーを変更する前に、アラートの開始時刻と監査ログとデプロイ スケジュールを比較します。

### 一般的な問題を軽減する

次の一般的な問題により、このアラートが発生する可能性があります。 この一覧は完全ではありませんが、調査の開始点となります。

#### ポリシーまたは割り当ての変更後、多くのユーザーが予期せずブロックされる

フィルター ポリシー、リンクされたセキュリティ プロファイル、条件付きアクセス ポリシー、または転送プロファイルの割り当てが拡張され、意図したよりも多くのユーザーが含まれる場合があります。

問題を調査して軽減するには:

1. アラートで、影響を受けるユーザーとアプリケーションの数を、予想されるデプロイ スコープと比較します。
2. トラフィック ログで、アラートの期間と影響を受けるユーザーにフィルター処理します。 **アクション**が**拒否**されたことを確認し、宛先と Web カテゴリを特定します。
3. アラートが開始される直前に行われた変更については、監査ログを確認します。
4. インターネット アクセス転送プロファイルとリンクされた条件付きアクセス ポリシーのユーザーとグループの割り当てを確認します。 条件付きアクセス セッション制御で目的のセキュリティ プロファイルが選択されていることを確認します。
5. 拡張されたスコープが意図的な場合は、シグナルを監視し、それ以上のアクションは必要ありません。 意図的でない場合は、意図した割り当てまたはポリシースコープを復元します。

#### 必要なアプリケーションがフィルター処理規則によってブロックされる

Web カテゴリ、URL、FQDN、またはワイルドカードルールは、ユーザーが必要とするビジネス アプリケーションと一致する場合があります。 複数の照合ポリシーに競合するアクションがある場合は、最も制限の厳しいアクションが適用されます。

問題を調査して軽減するには:

1. アラートで、影響を受けるアプリケーションとユーザーを特定します。
2. トラフィック ログで、影響を受けるユーザーと宛先でフィルター処理します。 拒否されたトランザクションの **アクション** と **Web カテゴリ** の値を確認します。
3. 宛先と一致するすべてのフィルター規則を確認します。 カテゴリ ルール、正確な URL または FQDN、ワイルドカード エントリを確認します。
4. ブロックが意図しない場合は、ブロックルールを絞り込むか、必要な宛先を適切な許可ポリシーに追加します。 変更は、アクセスを必要とするユーザーと宛先に限定してください。
5. ポリシーの変更が反映されるまでの時間を許可し、新しいトラフィック ログ エントリに予期されるアクションが表示されることを確認します。

#### ユーザーのサブセットのみが予期せずブロックされる

転送プロファイル、セキュリティ プロファイル、または条件付きアクセスの割り当てが異なると、同じアプリケーションにアクセスするユーザーが異なるポリシー決定を受け取る可能性があります。

問題を調査して軽減するには:

1. 影響を受けるユーザーと、同じ宛先にアクセスする影響を受けていないユーザーのトラフィック ログ エントリを比較します。
2. 転送プロファイルとリンクされた条件付きアクセス ポリシーのユーザーとグループのメンバーシップと割り当てを比較します。 条件付きアクセス セッション制御で目的のセキュリティ プロファイルが選択されていることを確認します。
3. インターネット アクセス トラフィック転送プロファイルが有効であること、およびその取得ポリシーとバイパス ポリシーが目的のトラフィックと一致することを確認します。
4. 意図しない割り当てまたはトラフィック取得ルールを修正し、トラフィック ログの結果を検証します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-investigate-private-access-connector-signals"} -->
## Microsoft Entra Private Access コネクタを必要とするプライベート アプリケーション アクセスを調査する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-private-access-connector-signals
- Service: entra-id / monitoring-health
- Article date: 2026-09-14
- Summary: Microsoft Entra Health 監視ツールを使用して、Microsoft Entra Private Access コネクタを必要とするプライベート アプリケーション アクセス シナリオを監視およびトラブルシューティングする方法について説明します。

Microsoft Entra Health の監視には、潜在的な問題または障害の状態が検出されたときに監視およびアラートを生成できる一連のテナント レベルの正常性メトリックが用意されています。 Microsoft Entra Private Access コネクタの可用性を必要とするプライベート アプリケーション アクセスなど、監視できる正常性シナリオは複数あります。

Microsoft Entra Health のしくみの詳細については、以下を参照してください。

- [Microsoft Entra Health とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)
- [Microsoft Entra の正常性監視シグナルとアラートを調査する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts)

この記事では、Microsoft Entra Private Access コネクタを必要とするプライベート アプリケーション アクセスに関連する正常性メトリックと、アラートを受信したときの潜在的な問題のトラブルシューティング方法について説明します。

このシナリオ:

- プライベート アプリケーションに正常にアクセスする一意のユーザーの数を集計します。
- コネクタの可用性のためにプライベート アプリケーションにアクセスできなかった一意のユーザーの数を集計します。
- 正常にアクセスされた一意のプライベート アプリケーションの数を集計します。
- コネクタの可用性により、一意のプライベート アプリケーションへの失敗したアクセスの数を集計します。

### [前提条件]

正常性監視シグナルを表示し、アラートを構成して受信するには、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に合わせて、最小限の特権アクセスを持つロールを使用することをお勧めします。

- Microsoft Entra 正常性シナリオ監視シグナルを[表示](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)するには、*Microsoft Entra P1 または P2 ライセンス*を持つテナントが必要です。
- 試用版以外の [Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持ち、かつ月にアクティブ ユーザーが100人以上いるテナントは、アラートを表示し、アラート通知を受信する必要があります。
- Microsoft Entra Private Access ライセンスを持つテナントが必要です。 詳細については、「 [グローバル セキュリティで保護されたアクセスとは」](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)の「ライセンス」セクションを参照してください。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、*シナリオ監視シグナル、アラート、およびアラート構成を表示*するために必要な最小限の特権ロールです。
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) は、*アラート* を更新し、*アラート通知構成*を更新するために必要な最小限の特権ロールです。
- `HealthMonitoringAlert.Read.All`するには、アクセス許可が必要です。
- Microsoft Graph API を使用してアラートを表示および変更するには、`HealthMonitoringAlert.ReadWrite.All` アクセス許可が必要です。
- タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。
- Microsoft Entra Private Access のトラフィック ログを表示するには、[Global Secure Access Log Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-secure-access-log-reader) ロールが必要です。

### シグナルとアラートを調査する

アラートの期間、シグナルの傾向、影響を受けるエンティティを比較して、調査を開始します。 次に、影響を受けるユーザーとアプリケーションをコネクタの状態とログに関連付けます。

1. アラートの詳細を表示します。

    - Microsoft Entra 管理センターで、シグナル グラフ、アラートの時間枠、影響を受けるエンティティを確認します。 詳細については、「 [シグナルとアラートの調査](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts#investigate-the-signals-and-alerts)」を参照してください。
    - Microsoft Graph のガイダンスについては、 [Microsoft Graph の正常性の監視の概要に関するページ](https://learn.microsoft.com/ja-jp/graph/api/resources/healthmonitoring-overview?view=graph-rest-beta&preserve-view=true)を参照してください。
2. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
3. **Entra ID**&gt;**Monitoring & health**&gt;**Health** に移動します。 ページが開き、サービス レベル アグリーメント (SLA) の達成ページが開きます。
4. [ **ヘルス モニタリング** ] タブを選択します。
5. **Microsoft Entra Private Access コネクタが必要なプライベート アプリケーション アクセス** シナリオを選択し、次にアクティブなアラートを選択します。

    [Image: 1 つのアクティブなアラートを含む、Microsoft Entra Private Access コネクタのシナリオを必要とするプライベート アプリケーション アクセスのスクリーンショット。]
6. Microsoft Entra Private Access コネクタの状態を確認します。 コネクタとアップデーター サービスが実行されていることを確認します。 詳細については、「[プライベート ネットワーク コネクタのメンテナンスMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#maintenance)」を参照してください。
7. コネクタ グループとそのアプリケーションの割り当てを確認します。 影響を受ける各アプリケーションが、正常なコネクタを持つグループに割り当てられていることを確認します。 詳細については、「[Microsoft Entra のプライベート ネットワーク コネクタ グループ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connector-groups)」を参照してください。
8. [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)を確認します。 アプリケーションへのサインインがブロックされている影響を受けるユーザーを探します。
9. [グローバル セキュリティで保護されたアクセスのトラフィック ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-view-traffic-logs)を確認します。 アラートの期間と影響を受けるユーザーまたはアプリケーションにログをフィルター処理し、プライベート アプリケーショントランザクションの失敗を探します。
10. 最近のコネクタ グループまたはアプリケーションの割り当ての変更については、 [グローバル セキュリティで保護されたアクセス監査ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-access-audit-logs) を確認します。

    [Image: グローバル セキュリティで保護されたアクセス サービスにフィルター処理された監査ログのスクリーンショット。]

### シグナルを理解する

アラートは、コネクタが使用できないために接続に失敗したユーザーまたはプライベート アプリケーションの数の変化を示すことができます。

- スパイクは、1 つ以上のコネクタが使用できなくなった、コネクタ グループの容量が失われた、またはアプリケーションが間違ったコネクタ グループに割り当てられたことを示している可能性があります。
- dip は、コネクタの可用性が回復したことを示すことができます。 また、トラフィックまたはアプリケーションの割り当てが変更されたことを示すこともできます。

構成を変更する前に、アラートの開始時刻をコネクタの状態、コネクタ イベント ログ、監査ログ、および計画メンテナンスと比較します。

### 一般的な問題を軽減する

次の一般的な問題により、このアラートが発生する可能性があります。 この一覧は完全ではありませんが、調査の開始点となります。

#### コネクタが非アクティブまたは使用できない

コネクタ サービスが停止しているか、信頼証明書の有効期限が切れているか、コネクタ ホストが Microsoft Entra サービスに到達できない可能性があります。

問題を調査して軽減するには:

1. アラートで、影響を受けるユーザーとプライベート アプリケーションを特定し、アラートの開始時刻をメモします。
2. **グローバル セキュア アクセス**&gt;**Connect**&gt;**Connectors** に移動し、影響を受けるアプリケーションに対応するコネクタ グループ内の非アクティブなコネクタを特定します。
3. 影響を受ける各コネクタ サーバーで、コネクタとアップデーター サービスが実行されていることを確認します。
4. コネクタ診断ツールを実行して、証明書の有効性、ポート 80 と 443、送信プロキシ構成、証明書失効リストアクセス、サービス状態、バックエンド エンドポイント アクセスを確認します。
5. サービス、信頼証明書、登録、または接続エラーのコネクタ **管理者** イベント ログを確認します。
6. コネクタ サービスまたは接続を復元します。 信頼証明書の有効期限が切れた場合は、 [プライベート ネットワーク](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors) コネクタのトラブルシューティングに従って、コネクタを再登録または再インストールします。
7. コネクタがアクティブになり、新しいトラフィック ログ エントリにコネクタ関連のトランザクション エラーが表示されなくなったことを確認します。

#### アプリケーションが間違ったコネクタ グループに割り当てられている

割り当てられたコネクタ グループに、影響を受けるアプリケーションのネットワークに到達できる正常なコネクタが含まれていない可能性があります。

問題を調査して軽減するには:

1. アラートで、障害が 1 つ以上のアプリケーションに集中しているかどうかを特定します。
2. 影響を受ける各アプリケーションのコネクタ グループの割り当てを確認します。
3. 割り当てられたグループに、アプリケーションの宛先に到達できるネットワーク内のアクティブなコネクタが含まれていることを確認します。
4. アラートの開始時刻に近いコネクタ グループまたはアプリケーションの割り当ての変更に関する監査ログを確認します。
5. 目的の割り当てを復元するか、割り当てられたグループにアプリケーションに到達できる正常なコネクタを追加します。
6. アクセスをテストし、トラフィック ログと正常性信号の回復を確認します。

#### コネクタ グループの容量または回復性が不十分である

単一のコネクタ、持続的な高使用率、またはサービスまたはバックエンド アプリケーションへの接続が不十分なコネクタ グループは、断続的な障害を引き起こす可能性があります。

問題を調査して軽減するには:

1. 高可用性のために、コネクタ グループに少なくとも 2 つのアクティブなコネクタがあるかどうかを確認します。
2. コネクタ ホストの CPU とネットワーク使用率を確認します。 継続的な CPU とメモリの使用率は、文書化されたしきい値を下回らしてください。
3. 各コネクタ サーバーから、影響を受けるバックエンド アプリケーションへの接続をテストします。
4. コネクタ ホストが使用できない場合は、アクティブなサービスからコネクタを削除し、正常なコネクタまたはバックアップ コネクタをグループに追加します。
5. 使用率が維持される場合は、コネクタを追加するか、ホスト容量を増やします。 サイズ設定とパフォーマンスのガイダンスについては、[プライベート ネットワーク コネクタMicrosoft Entra参照](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#performance-and-scalability)してください。
6. 障害が停止し、信号が予期した範囲に戻っていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-investigate-remote-networks-scenario-signals"} -->
## グローバル セキュリティで保護されたアクセスのリモート ネットワーク接続を調査する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-remote-networks-scenario-signals
- Service: entra-id / monitoring-health
- Article date: 2026-09-14
- Summary: Microsoft Entra Health 監視を使用してリモート ネットワーク接続を監視およびトラブルシューティングする方法について説明します。

Microsoft Entra Health の監視には、潜在的な問題または障害の状態が検出されたときに監視およびアラートを生成できる一連のテナント レベルの正常性メトリックが用意されています。 監視できるシステム健全性シナリオには、Global Secure Access のリモート ネットワーク接続を含む複数のものがあります。

Microsoft Entra Health のしくみの詳細については、以下を参照してください。

- [Microsoft Entra Health とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)
- [Microsoft Entra の正常性監視シグナルとアラートを調査する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts)

この記事では、リモート ネットワーク接続に関連する正常性メトリックについて説明し、潜在的な問題をトラブルシューティングする手順について説明します。 トンネル接続と BGP 接続は別々の正常性シナリオですが、BGP は確立された IPsec トンネルに依存するため、この記事ではそれらをまとめて説明します。

この記事では、次の 2 つのシナリオについて説明します。

- リモート ネットワーク トンネル接続を必要とするグローバル セキュリティで保護されたアクセス
    - 接続されているリモート ネットワーク トンネルの数を示すデータを表示します。
    - 切断されているリモート ネットワーク トンネルの数を示すデータを表示します。
- リモート ネットワーク BGP 接続を必要とするグローバル セキュリティで保護されたアクセス
    - BGP が接続されているリモート ネットワークの数を示すデータを表示します。
    - BGP が切断されたリモート ネットワークの数を示すデータを表示します。

### [前提条件]

正常性監視シグナルを表示し、アラートを構成して受信するには、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に合わせて、最小限の特権アクセスを持つロールを使用することをお勧めします。

- Microsoft Entra 正常性シナリオ監視シグナルを[表示](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)するには、*Microsoft Entra P1 または P2 ライセンス*を持つテナントが必要です。
- 試用版以外の [Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持ち、かつ月にアクティブ ユーザーが100人以上いるテナントは、アラートを表示し、アラート通知を受信する必要があります。
- Microsoft Entraグローバル セキュア アクセス ライセンスを持つテナントが必要です。 詳細については、[Global Secure Access とは？](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)のライセンスセクションをご参照ください。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、*シナリオ監視シグナル、アラート、およびアラート構成を表示*するために必要な最小限の特権ロールです。
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) は、*アラート* を更新し、*アラート通知構成*を更新するために必要な最小限の特権ロールです。
- `HealthMonitoringAlert.Read.All`するには、アクセス許可が必要です。
- Microsoft Graph API を使用してアラートを表示および変更するには、`HealthMonitoringAlert.ReadWrite.All` アクセス許可が必要です。
- タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。
- リモート ネットワーク ログにアクセスするには、 [グローバルセキュリティアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-secure-access-administrator) または [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールが割り当てられている必要があります。

### シグナルとアラートを調査する

アラートがトンネル接続用か BGP 接続用かを特定して、調査を開始します。 次に、アラートの期間と影響を受けるリモート ネットワークを、リモート ネットワークの正常性と監査ログと比較します。

1. アラートの詳細を表示します。

    - [Microsoft Entra 管理センター](https://entra.microsoft.com/)で、シグナル グラフ、アラートの時間枠、影響を受けるリモート ネットワークを確認します。 詳細については、「 [シグナルとアラートの調査](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts#investigate-the-signals-and-alerts)」を参照してください。
    - Microsoft Graph のガイダンスについては、 [Microsoft Graph の正常性の監視の概要に関するページ](https://learn.microsoft.com/ja-jp/graph/api/resources/healthmonitoring-overview?view=graph-rest-beta&preserve-view=true)を参照してください。
2. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
3. **Entra ID**&gt;**Monitoring & health**&gt;**Health** に移動します。 ページが開き、サービス レベル アグリーメント (SLA) の達成ページが開きます。
4. [ **ヘルス モニタリング** ] タブを選択します。
5. **リモート ネットワーク トンネル接続を必要とするグローバル セキュリティで保護されたアクセスまたはリモート ネットワーク** **BGP 接続を必要とするグローバル セキュリティで保護されたアクセス**シナリオを選択し、アクティブなアラートを選択します。

    [Image: 1 つのアクティブなアラートを含むグローバル Secure Access リモート ネットワーク BGP 接続シナリオのスクリーンショット。]
6. [リモート ネットワークの正常性ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-remote-network-health-logs?tabs=microsoft-entra-admin-center)を確認します。

    - トンネル アラートについては、影響を受けるリモート ネットワーク、送信元 IP アドレス、および宛先 IP アドレスの **トンネル切断** イベントを探します。
    - BGP アラートの場合は、**BGP disconnected** イベントを探してください。 対応する**リモート ネットワーク アライブ** イベントの **BGP ルートアドバタイズ数**を確認します。
    - **リモート ネットワーク アライブ** イベントを使用して、アラートの前と実行中に送受信されたバイトを比較します。
7. [グローバル セキュリティで保護されたアクセス監査ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-access-audit-logs)で、影響を受けるリモート ネットワーク、デバイス リンク、IPsec 構成、またはトラフィック プロファイルの割り当てに対する最近の変更を確認します。

    [Image: グローバル セキュリティで保護されたアクセス サービスにフィルター処理された監査ログのスクリーンショット。]
8. より長い保持期間または相関分析が必要な場合は、[リモート ネットワークの正常性ログを Log Analytics にエクスポート](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-remote-network-health-logs#configure-diagnostic-settings-to-export-logs)し、[Workbook を使用して分析します](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-remote-network-health-logs#analyze-logs-with-a-workbook)。

### シグナルを理解する

トンネルアラートと BGP アラートは、リモート ネットワーク接続のさまざまなレイヤーを表します。

- トンネル接続信号は、IPsec トンネルが接続されているか切断されているかを追跡します。 トンネルが切断されている場合、BGP はそのトンネル経由でルートを交換できません。
- BGP 接続信号は、BGP セッションが接続されているかどうかを追跡し、確立されたトンネル経由でルート情報を交換します。 BGP が切断されている間、IPsec トンネルは接続されたままにすることができます。

切断されたトンネルまたは BGP セッションの急増は、CPE、インターネット サービス プロバイダー、構成、またはグローバル セキュア アクセス エッジ接続の問題を示している可能性があります。 アラートの開始時刻を、リモート ネットワーク正常性イベント、監査ログ、計画されたネットワーク メンテナンスと比較します。

### 一般的な問題を軽減する

次の一般的な問題により、トンネルまたは BGP 接続アラートが発生する可能性があります。 この一覧は完全ではありませんが、調査の開始点となります。

#### 1 つ以上の IPsec トンネルが切断されている

CPE の停止、インターネット接続の問題、または CPE とグローバル セキュア アクセス IPsec の構成の不一致により、トンネルが切断される可能性があります。

問題を調査して軽減するには:

1. アラートで、影響を受けるリモート ネットワークとアラートの開始時刻を特定します。
2. リモート ネットワーク正常性ログで、リモート ネットワーク ID でフィルター処理します。 **トンネル切断イベントを**識別し、送信元と宛先の IP アドレスのペアをメモします。
3. 一致するトンネルの CPE の状態とログを確認します。 パブリック IP アドレス、IPsec パラメーター、共有シークレットまたは証明書、トンネル エンドポイントがグローバル セキュア アクセス デバイス リンク構成と一致することを確認します。
4. 監査ログで、アラートの開始時刻近くのリモート ネットワークまたはデバイス リンクへの変更を確認します。
5. CPE またはインターネット接続を復元するか、一致しない構成を修正します。
6. **トンネル接続**イベントが表示され、その後の**リモート ネットワーク アライブ** イベントで送受信バイトが再開されることを確認します。

#### IPsec トンネルは接続されていますが、BGP は切断されています

基になるトンネルは、BGP 近隣関係がダウンしている間も接続されたままになります。 この状態では、リモート ネットワークは、トラフィックの転送に必要なルートを交換しない可能性があります。

問題を調査して軽減するには:

1. リモート ネットワークの正常性ログに、影響を受ける送信元と宛先の IP アドレスペアに対して **接続されたトンネル** が表示されていることを確認します。
2. **BGP 切断イベントを**探します。 影響を受けるリモート ネットワークの**Remote network alive**イベントで、**BGP ルート広告数**を確認します。
3. CPE で、BGP 近隣 IP アドレス、自律システム番号、およびルート アドバタイズの構成を確認します。
4. 最近の CPE およびグローバル セキュア アクセス構成の変更を確認します。
5. CPE ベンダーのガイダンスに従って、BGP 構成を修正するか、影響を受ける BGP セッションを再起動します。
6. **BGP 接続**イベントが表示されることを確認します。 次に、アドバタイズされたルート数が、後続の **リモート ネットワーク アライブ** イベントで予期される値に戻っていることを確認します。

#### 接続が断続的であるか、単一のトンネルに依存する

1 つのトンネルを持つリモート ネットワークには、CPE、ISP、またはグローバル セキュア アクセス エッジのメンテナンスまたは障害時に代替パスがありません。

問題を調査して軽減するには:

1. トンネルまたは BGP の切断と再接続の繰り返しイベントの正常性ログを確認します。
2. リモート ネットワークに冗長トンネルがあるかどうかを確認します。
3. 場所ごとに少なくとも 2 つの IPsec トンネルを構成します。 可用性の要件に基づいて、ゾーン冗長またはセカンダリ地理的リモート ネットワークを使用します。
4. アクティブ-アクティブまたはアクティブ-スタンバイルーティング用のトンネルの重み付けを構成します。
5. 動的ルート学習には BGP を使用します。 CPE で BGP がサポートされていない場合は、適切なメトリックを使用して静的ルートを構成します。
6. リモート ネットワーク正常性ログをLog Analyticsにエクスポートし、トンネルと BGP の障害に関するアラート ルールを作成します。

冗長性とフェールオーバーのガイダンスについては、「 [リモート ネットワークの回復性の強化](https://learn.microsoft.com/ja-jp/entra/global-secure-access/remote-network-resilience)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-manage-inactive-user-accounts"} -->
## 非アクティブなユーザー アカウントを管理する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts
- Service: entra-id / monitoring-health
- Article date: 2025-02-21
- Summary: 非アクティブまたは古くなった Microsoft Entra ユーザー アカウントを、Microsoft Entra 管理センターと Microsoft Graph を使用して、検出および解決する方法について説明します。

大規模な環境では、従業員が退職したときに、ユーザー アカウントが必ず削除されるとは限りません。 このような使用されなくなったユーザー アカウントはセキュリティ上のリスクとなるため、IT 管理者はそれらを検出して処理する必要があります。

この記事では、Microsoft Entra ID の古くなったユーザー アカウントを処理する方法について説明します。

注

この記事は、Microsoft Entra ID の非アクティブなユーザー アカウントを検出する場合にのみ適用されます。 [Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview) での非アクティブなアカウントの検索には適用されません。

注

非アクティブな **ゲスト** アカウントについては、「 [古いゲスト アカウントの監視とクリーンアップ](https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-stale-guest-accounts)」を参照してください。

### 前提条件

- Microsoft Graph を使用して `lastSuccessfulSignInDateTime` プロパティにアクセスするには、Microsoft Entra ID P1 または P2 ライセンスが必要です。
- アプリに次の Microsoft Graph アクセス許可を付与する必要があります。
    - AuditLog.Read.All
    - User.Read.All
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)は、アクティビティ ログにアクセスするために必要な最小特権ロールです。
    - ロールの完全な一覧については、「[タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---audit-and-sign-in-logs-least-privileged-roles)」を参照してください。

### 非アクティブなユーザーアカウントとは

非アクティブなアカウントとは、組織のメンバーがリソースにアクセスするためにもう必要としなくなったユーザー アカウントのことです。 非アクティブなアカウントの主要な識別要素の 1 つは、環境へのサインインにそれらが "しばらくの間" 使用されていないことです。 非アクティブなアカウントはサインイン アクティビティに関連付けられているため、アカウントが最後にサインインを試みた時刻のタイムスタンプを使用して、非アクティブなアカウントを検出できます。

この方法の課題は、自分の環境にとって "しばらくの間" が何を意味するかを定義することです。 たとえば、ユーザーが休暇中のために、"しばらくの間" 環境にサインインしていない可能性があります。 あなたの環境へのサインインをしないためのすべての正当な理由を検討する必要があります。 多くの組織では、非アクティブなユーザー アカウントの妥当な期間は 90 日から 180 日です。

最後のサインイン日は、リソースへのアクセスに対するユーザーの継続的なニーズに関する潜在的な分析情報を提供します。 グループ メンバーシップまたはアプリへのアクセスがまだ必要とされているか、それとも削除された可能性があるかを判断するのに役立つこともあります。 外部ユーザー管理では、外部ユーザーがまだテナント内でアクティブであるか、削除する必要があるかどうかを判断できます。

### 非アクティブなユーザー アカウントを検索して調査する方法

Microsoft Entra 管理センターまたは Microsoft Graph API を使用して、非アクティブなユーザー アカウントを見つけることができます。 非アクティブなユーザー アカウントの組み込みレポートはありませんが、最後のサインイン日時を使用して、ユーザー アカウントが非アクティブかどうかを判断できます。

## [管理センター](#tab/admin-center)
ユーザーの最後のサインイン時刻を見つけるには、Microsoft Entra 管理センターでユーザーの一覧を確認できます。 すべてのユーザーがユーザーの一覧を表示できますが、一部の列と詳細は、適切なアクセス許可を持つユーザーのみが使用できます。

#### すべてのユーザーの最後のサインイン時刻を見つける

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. **[ビューの管理]** を選択し、次に **[列の編集]** を選択します。

    [Image: 管理ビュー オプションが強調表示されている [ユーザー] ビューのスクリーンショット。]
4. 一覧で **[+ 列の追加]** を選択し、**[前回の対話型サインイン時間]** を選んでから **[保存]** を選択します。

    [Image: [最後の対話型サインイン時刻] オプションが強調表示されている [列の編集] ウィンドウのスクリーンショット。]
5. 列が[すべてのユーザー] リストに表示されたら、**フィルターの追加** を選択して、フィルターオプションで検索の期間を設定します。

    - &lt;として **[=]** を選んで日付を選択すると、その日付よりも*前*のログインした日付を確認できます。

[Image: 最後のサインイン フィルター結果のスクリーンショット。]

#### 1 人のユーザーを調査する

あるユーザーの最新のサインイン アクティビティを確認する必要がある場合は、Microsoft Entra ID でユーザーのサインインの詳細を確認できます。 「 名前別のユーザー」セクションで説明されている Microsoft Graph API を使用することもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 一覧からユーザーを選択します。
4. ユーザーの [概要] の **[マイ フィード]** 領域で、**[サインイン]** タイルを見つけます。

    [Image: サインイン アクティビティ タイルが強調表示されているユーザー概要ページのスクリーンショット。]

このタイルに表示される最終サインイン日時は、更新されるまでに最大 24 時間かかる場合があります。つまり、この日時は最新のものではない可能性があります。 ほぼリアルタイムでアクティビティを確認する必要がある場合は、**[サインイン]** タイルの**[See all sign-ins] (すべてのサインインを表示する)** リンクを選択して、そのユーザーのすべてのサインイン アクティビティを表示します。

## [Microsoft Graph](#tab/microsoft-graph)
複数のプロパティを評価することで、非アクティブなアカウントを検出できます。 `lastSignInDateTime` プロパティは、`signInActivity` の リソースの種類によって公開されます。 `lastSignInDateTime` プロパティは、ユーザーが Microsoft Entra ID で対話型サインインを最後に試行した時刻を示します。

このプロパティを使用すると、次のシナリオの解決策を実行できます。

#### すべてのユーザーの最終サインイン日時

*"すべてのユーザー*の最後のサインイン日付のレポートを生成します。 応答には、すべてのユーザーの一覧と、それぞれのユーザーの最後の `lastSignInDateTime` が表示されます。

- `https://graph.microsoft.com/v1.0/users?$select=displayName,signInActivity`

#### サインインが最後に成功した日時

このクエリは、ユーザー一覧に最後のサインイン日を追加し、Microsoft Entra 管理センターで特定の日付でフィルター処理するのと似ています。 指定の日付`lastSuccessfulSignInDateTime`または`lastSignInDateTime`*それより前*にサインインしたユーザーの一覧を要求できます。 このクエリの応答はユーザーの詳細を提供しますが、ユーザーのサインイン アクティビティは提供しません。 これらの詳細を表示するには、**Users by name** シナリオでクエリを試してください。

- `https://graph.microsoft.com/v1.0/users?$filter=signInActivity/lastSuccessfulSignInDateTime le 2024-06-01T00:00:00Z`
- `https://graph.microsoft.com/v1.0/users?$filter=signInActivity/lastSignInDateTime le 2024-06-01T00:00:00Z`

#### 名前別のユーザー

このシナリオでは、特定のユーザーを名前で検索します。 このクエリの応答には、最後のサインインの日付、時刻、および要求 ID が含まれます。

**要求**:

```http
GET `https://graph.microsoft.com/v1.0/users?$filter=startswith(displayName,'Isabella Simonsen')&$select=displayName,signInActivity`
```

**応答**:

```json
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#users(displayName,signInActivity)",
    "value": [
        {
            "displayName": "Stine Romund",
            "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
            "signInActivity": {
                "lastSignInDateTime": "2024-12-10T17:38:26Z",
                "lastSignInRequestId": "20a94b1b-ade2-4f4e-9c55-609f7cd97600",
                "lastNonInteractiveSignInDateTime": "2024-12-10T17:38:11Z",
                "lastNonInteractiveSignInRequestId": "94c369e6-249e-4595-bb86-495ea9a81e00",
                "lastSuccessfulSignInDateTime": "2024-12-10T17:38:26Z",
                "lastSuccessfulSignInRequestId": "20a94b1b-ade2-4f4e-9c55-609f7cd97600"
            }
        }
    ]
}
```

- `lastSignInDateTime`: サインインエラーを含む、最後の対話型サインイン試行の日時。 最後のサインイン試行が成功した場合、このプロパティの日時は `lastSuccessfulSignInDateTime`と同じです。
- `lastNonInteractiveSignInDateTime`: 最後の非対話型サインイン試行の日時。
- `lastSuccessfulSignInDateTime`: 最後に成功した対話型サインインの日時。

注

`signInActivity` プロパティは `$filter` (`eq`、`ne`、`not`、`ge`、`le`) をサポートしていますが、"他のフィルター可能なプロパティと一緒に使用することはできません"。`$select=signInActivity`際に、`$filter=signInActivity` または  を指定する必要があります。signInActivity プロパティが既定で返されないためです。

#### lastSignInDateTime プロパティに関する考慮事項

以下の詳細は、`lastSignInDateTime` プロパティに関連するものです。

- `lastSignInDateTime` プロパティは、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/signinactivity) の [signInActivity リソースの種類](https://learn.microsoft.com/ja-jp/graph/overview#whats-in-microsoft-graph)によって公開されます。
- このプロパティは、Get-AzureAdUser コマンドレットでは使用*できません*。
- 対話型サインインが試行されるたびに、基になるデータ ストアが更新されます。 通常、サインインは、6 時間以内に関連するサインイン レポートに表示されます。
- `lastSignInDateTime`タイムスタンプを生成するには、サインインを試みる必要があります。 [Microsoft Entra サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)に記録されている限り、サインイン試行が失敗したか成功した場合は、`lastSignInDateTime`タイムスタンプが生成されます。 次の場合、 `lastSignInDateTime` プロパティの値が空白になる可能性があります。

    - ユーザーが最後に試行したサインインが、2020 年 4 月より前に行われた。
    - 影響を受けるユーザー アカウントが、サインインに使用されたことがない。
- 前回のサインインの日付は、ユーザー オブジェクトに関連付けられます。 この値は、ユーザーの次回のサインインまで保持されます。 更新には最大 24 時間かかる場合があります。

---

### 非アクティブなユーザーに対処する方法

非アクティブなユーザーを特定したら、まず次の質問をします。

- ユーザーは組織でまだ雇用されていますか?
- ユーザーは引き続きアクセス権を持つリソースにアクセスする必要がありますか?
- 他の理由でユーザー アカウントは引き続き必要ですか?

非アクティブなユーザーに対処する方法はシナリオによって異なりますが、セキュリティ リスクを軽減するには、未使用のアカウントまたは過剰な特権を持つアカウントのクリーンアップを優先する必要があります。 次の機能とオプションは開始するのに最適な場所ですが、これらの機能の一部には追加のライセンスが必要な場合があることに注意してください。

- [古いゲスト アカウントをクリーンアップする](https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-stale-guest-accounts)
- 動的メンバーシップ グループは、ユーザー プロパティに基づいてグループに対してユーザーを自動的に追加または削除することを検討してください。
    - [動的メンバーシップ グループを作成する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)
- Microsoft Entra ID ガバナンス アクセス レビューを使用して、ユーザーのアクセスを監査します。
    - [アクセス レビューとは](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)
    - [アクセス レビューの推奨事項を確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/review-recommendations-access-reviews)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-stream-logs-to-event-hub"} -->
## Microsoft Entra ログをイベント ハブにストリーム配信する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub
- Service: entra-id / monitoring-health
- Article date: 2024-09-27
- Summary: SIEM ツール統合と分析のために Microsoft Entra アクティビティ ログをイベント ハブにストリーミングする方法についてご確認ください。

Microsoft Entra テナントでは、毎秒大量のデータが生成されます。 サインイン アクティビティとテナント内で行われた変更のログは、蓄積して分析が困難な程多くのデータとなります。 セキュリティ情報イベント管理 (SIEM) ツールとの統合は、環境に関する分析情報を得るのに役立ちます。

この記事では、ログをイベント ハブにストリーミングして、いくつかの SIEM ツールのいずれかと統合する方法について説明します。

### 前提条件

- Azure サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、 [無料試用版にサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 既に設定されている **Azure イベント ハブ** 。 [イベント ハブを作成する](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-create)方法について説明します。
- Microsoft Entra テナントの一般的な診断設定を作成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)アクセス。
- [属性ログ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-administrator)アクセスを使用して、[カスタムセキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)ログの診断設定を作成する。

### イベント ハブにログをストリーム配信する

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**監視とヘルス**&gt;**診断設定**に移動します。 [**監査ログ**] ページまたは [**サインイン**] ページから **[設定のエクスポート]** を選択することもできます。
3. [ **+ 診断設定の追加]** を選択して新しい統合を作成するか、既存の統合の **[設定の編集]** を選択します。
4. **診断設定名**を入力します。 既存の統合を編集する場合、名前を変更することはできません。
5. ストリーミングするログ カテゴリを選択します。

1. [ **イベント ハブへのストリーム** ] チェック ボックスをオンにします。
2. ログをルーティングしたい Azure サブスクリプション、Event Hubs 名前空間、およびオプションのイベント ハブを選択します。

サブスクリプションと Event Hubs 名前空間は両方とも、ログのストリーミング元である Microsoft Entra テナントと関連付けられている必要があります。

Azure イベント ハブの準備ができたら、アクティビティ ログと統合したい SIEM ツールに移動します。 SIEM ツールでのプロセスを完了します。

現在、Splunk、SumoLogic、ArcSight がサポートされています。 タブを選択して作業を開始します。 ツールのドキュメントを参照してください。

## [Splunkの](#tab/splunk)
この機能を使用するには、 [Microsoft Cloud Services 用の Splunk アドオン](https://splunkbase.splunk.com/app/3110/#/details)が必要です。

#### Microsoft Entra ログを Splunk と統合する

1. Splunk インスタンスを開き、[ **データの概要**] を選択します。

    [Image: [データの概要] ボタン]
2. **[Sourcetypes**] タブを選択し、**mscs:azure:eventhub** を選択します

    [Image: [データ概要のソースの種類] タブ]

**body.records.category=AuditLogs** を検索に追加します。 次の図のような Microsoft Entra アクティビティ ログが表示されます。

[Image: アクティビティ ログ]

アドオンを Splunk インスタンスにインストールできない場合 (たとえば、プロキシを使用している場合、Splunk Cloud で実行している場合など)、Splunk HTTP Event Collector にこれらのイベントを転送できます。 これを行うには、イベント ハブ内の新しいメッセージによってトリガーされるこの [Azure 関数](https://github.com/splunk/azure-functions-splunk)を使用します。

## [スモロジック](#tab/SumoLogic)
この機能を使用するには、SumoLogic のシングル サインオンが有効なサブスクリプションが必要です。

#### Microsoft Entra ログを SumoLogic と統合する

1. [Microsoft Entra ID のログを収集](https://help.sumologic.com/docs/integrations/microsoft-azure/active-directory-azure#collecting-logs-for-azure-active-directory)するように SumoLogic インスタンスを構成します。
2. 環境のリアルタイム分析を提供する構成済みのダッシュボードを使用するには、[Microsoft Entra SumoLogic アプリをインストール](https://help.sumologic.com/docs/integrations/microsoft-azure/active-directory-azure#viewing-azure-active-directory-dashboards)します。

    [Image: ダッシュボード]

## [アークサイト](#tab/ArcSight)
この機能を使用するには、ArcSight Syslog NG Daemon SmartConnector (SmartConnector) または ArcSight Load Balancer の構成済みインスタンスが必要です。 イベントが ArcSight Load Balancer に送信されると、その結果、Load Balancer によって SmartConnector に送信されます。

[Azure Monitor Event Hubs 用 ArcSight SmartConnector の構成ガイドを](https://software.microfocus.com/products/siem-security-information-event-management/overview)ダウンロードして開きます。 このガイドには、ArcSight SmartConnector for Azure Monitor のインストールと構成に必要な手順が記載されています。

### Microsoft Entra ログを ArcSight と統合する

1. ArcSight 構成ガイドの **「前提条件」** セクションの手順を完了します。 このセクションには、次の手順が記載されています。

    - Azure でユーザーのアクセス許可を設定して、コネクタをデプロイして構成する **所有者** ロールを持つユーザーが存在することを確認します。
    - Syslog NG Daemon SmartConnector があるサーバーのポートを開き、Azure からアクセスできるようにします。
    - デプロイによって PowerShell スクリプトが実行されるため、コネクタをデプロイするマシン上で PowerShell がスクリプトを実行できるようにする必要があります。
2. ArcSight 構成ガイドの「 **コネクタのデプロイ** 」セクションの手順に従って、コネクタをデプロイします。 このセクションでは、コネクタのダウンロードと抽出、アプリケーションのプロパティの構成、および抽出されたフォルダーからのデプロイ スクリプトの実行方法について手順を追って説明されています。
3. **Azure でのデプロイの確認**に関するページの手順を使用して、コネクタが設定され、正しく機能することを確認します。 次の前提条件を確認します。

    - Azure サブスクリプションで必要な Azure 関数が作成されている。
    - Microsoft Entra ログが正しい送信先にストリーミングされています。
    - デプロイのアプリケーション設定が、Azure Function Apps の [アプリケーション設定] に保持されている。
    - ArcSight コネクタ用の Microsoft Entra アプリケーションと、CEF 形式のマップ ファイルを含むストレージ アカウントを使用して、ArcSight 用の新しいリソース グループが Azure 内に作成されます。
4. ArcSight 構成ガイドの **デプロイ後の構成でデプロイ後** の手順を完了します。 このセクションには、タイムアウト期間後に関数アプリがアイドルにならないように App Service プランを使用している場合に追加の構成を実行する方法、イベント ハブのリソース ログのストリーミングを構成する方法、および新しく作成したストレージ アカウントと関連付けられるように SysLog NG Daemon SmartConnector キーストア証明書を更新する方法が説明されています。
5. この構成ガイドには、Azure 上でコネクタのプロパティをカスタマイズする方法、およびコネクタをアップグレードおよびアンインストールする方法についても説明されています。 また、 [Azure 従量課金プラン](https://azure.microsoft.com/pricing/details/functions) へのアップグレードや、イベントの負荷が単一の Syslog NG デーモン SmartConnector で処理できる負荷よりも大きい場合の ArcSight Load Balancer の構成など、パフォーマンスの向上に関するセクションもあります。

---

### アクティビティ ログの統合オプションと考慮事項

Azure Monitor 診断で現在の SIEM がまだサポートされていない場合は、Event Hubs API を使用して **カスタム ツールを** 設定できます。 詳細については、「[イベント ハブからメッセージを受信するためのガイド](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-dotnet-standard-getstarted-send)」を参照してください。

**IBM QRadar** は、Microsoft Entra アクティビティ ログと統合するためのもう 1 つのオプションです。 DSM と Azure Event Hubs プロトコルは、 [IBM サポート](https://www.ibm.com/support)でダウンロードできます。 Azure との統合の詳細については、 [IBM QRadar Security Intelligence Platform 7.3.0](https://www.ibm.com/support/knowledgecenter/SS42VS_DSM/c_dsm_guide_microsoft_azure_overview.html?cp=SS42VS_7.3.0) サイトを参照してください。

一部のサインイン カテゴリには、テナントの構成に応じて大量のログ データが含まれています。 一般的に、非対話型のユーザー サインインとサービス プリンシパルのサインインは、対話型のユーザー サインインの 5 倍から 10 倍の大きさになることがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-troubleshoot-sign-in-errors"} -->
## サインイン エラーのトラブルシューティング方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-troubleshoot-sign-in-errors
- Service: entra-id / monitoring-health
- Article date: 2024-11-11
- Summary: Microsoft Entra 管理センターで Microsoft Entra レポートを使用してサインイン エラーのトラブルシューティングを行う方法について説明します

Microsoft Entra のサインイン ログでは、次のように組織内のアプリケーションへのアクセスの管理に関する質問への回答を見つけることができます。

- ユーザーのサインインにどのようなパターンがあるか。
- 1 週間で何人のユーザーがユーザー サインインを行ったか。
- これらのサインインはどのような状態か。

さらに、サインイン ログは、組織内のユーザーのサインイン エラーのトラブルシューティングに役立つこともあります。 このガイドでは、サインイン レポートでサインイン エラーを特定する方法について学習し、エラーの根本的な原因を理解するために使用します。 一般的なサインイン エラーについてもいくつか説明します。

### 前提条件

必要なもの:

- 適切な Microsoft Entra ライセンスが関連付けられている動作中の Microsoft Entra テナント。
    - ライセンス要件の完全な一覧については、 [Microsoft Entra の監視と正常性のライセンスを](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)参照してください。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) は、アクティビティ ログにアクセスするために必要な最小限の特権ロールです。
- また、すべてのユーザーは https://mysignins.microsoft.com から自分のサインインにアクセスできます。

### サインインの詳細を収集する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;に移動し、**監視とヘルス**&gt;**サインインログ**を参照します。
3. フィルターを使用して結果を絞り込みます。

    - 特定のユーザーのトラブルシューティングを行う場合は、ユーザー名で検索します。
    - 特定のアプリに関する問題のトラブルシューティングを行う場合は、アプリケーションで検索します。
    - [**状態]** メニューから [**失敗**] を選択すると、失敗したサインインのみが表示されます。
4. 調査する失敗したサインインを選択して、詳細ウィンドウを開きます。
5. 各タブの詳細を確認します。さらなるトラブルシューティングのために、いくつかの詳細を保存する必要がある場合があります。 これらの詳細は、一覧の次にあるスクリーンショットで強調表示されています。

    - 関連付け ID
    - サインイン エラー コード
    - エラーの理由
    - ユーザー名、ユーザー ID、サインイン識別子

    [Image: サインインの詳細のスクリーンショット。いくつかの詳細が強調表示されています。]

### サインイン エラーのトラブルシューティング

サインインの詳細を収集したら、結果を調べて問題のトラブルシューティングを行う必要があります。

#### 失敗の理由と追加の詳細

**エラーの理由**と**追加の詳細**により、問題を解決するための詳細と次の手順が提供される場合があります。 [失敗の理由] では、エラーについて説明します。 [追加の詳細] には詳細が記載され、多くの場合、問題を解決する方法が示されます。

[Image: アクティビティの詳細のスクリーンショット。エラーの理由と詳細が強調表示されています。]

次の失敗の理由と詳細が一般的です。

- **強力な認証要求中に認証が失敗した**エラーの理由はトラブルシューティングにはあまり役立ちませんが、追加の詳細フィールドには、ユーザーが MFA プロンプトを完了しなかったことが示されています。 ユーザーが再びサインインして MFA プロンプトを完了するよう促します。
- **フェデレーション サービスが OAuth プライマリ更新トークンの発行に失敗した**エラーの理由は適切な開始点ですが、追加の詳細では、このシナリオでの認証のしくみを簡単に説明し、デバイス同期が有効になっていることを確認するように指示します。
- 一般的なエラーの理由は、 **無効なユーザー名またはパスワードが原因で資格情報の検証中にエラーが**発生することです。 ユーザーが間違って入力したため、もう一度やり直す必要があります。

#### サインインのエラー コード

調査の詳細が必要な場合は、 **サインイン エラー コード** を使用してさらに調査できます。

- エラー コード検索ツールにエラー **[コード](https://login.microsoftonline.com/error)** を入力して、エラー コードの説明と修復情報を取得します。
- サインイン エラー コード **[リファレンスでエラー コードを検索します](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)**。

次のエラー コードはサインイン イベントに関連付けられていますが、この一覧はすべてを網羅したものではありません。

- **50058**: ユーザーは認証されていますが、まだサインインしていません。

    - このエラー コードは、ユーザーがサインイン プロセスを完了しなかった場合のサインイン試行に対して表示されます。
    - ユーザーが完全にサインインしなかったため、[ユーザー] フィールドに、ユーザー名ではなくオブジェクト ID またはグローバル一意識別子 (GUID) が表示される場合があります。
    - このような状況では、ユーザー ID が "00000000-0000-0000" のように表示されることもあります。
- **90025**: 内部の Microsoft Entra サービスが再試行許容量に達し、ユーザーがサインインしました。

    - このエラーは多くの場合、ユーザーが気付かずに発生し、通常は自動的に解決されます。
    - それでも解決しない場合は、ユーザーにサインインの再試行を促してください。
- **500121**: ユーザーが MFA プロンプトを完了しませんでした。

    - このエラーは多くの場合、ユーザーが MFA の設定を完了していない場合に表示されます。
    - セットアップ プロセスを完了してサインインするようにユーザーに指示します。
- **70046**: セッションの有効期限が切れているか、再認証チェックに失敗しました。

    - このエラーは、セッション トークンの有効期限が切れた場合、または再認証チェックが失敗した場合に発生する可能性があります。
    - さまざまなサインイン リスク レベルに対して再認証を要求する条件付きアクセス ポリシーが有効になっている場合は、再認証チェックが行われることがあります。

他のすべてが失敗した場合、または推奨されるアクションを行っても問題が解決しない場合は、サポート リクエストを開いてください。 詳細については、 [Microsoft Entra ID のサポートを受ける方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-troubleshoot-sign-up-errors"} -->
## サインアップ エラーのトラブルシューティング方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-troubleshoot-sign-up-errors
- Service: entra-id / monitoring-health
- Article date: 2025-06-30
- Summary: Microsoft Entra 管理センターで Microsoft Entra レポートを使用してサインアップ エラーのトラブルシューティングを行う方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra サインアップ ログは、外部テナント内のアプリケーションのユーザーのサインアップ エラーのトラブルシューティングに役立ちます。 この記事では、サインアップ エラーを分離し、根本原因を理解する方法について説明します。

#### サインアップ エラー コード

特定のサインアップ エラー コードに関する情報を調査するには、次のツールを使用できます。

- エラー コード検索ツールにエラー **[コード](https://login.microsoftonline.com/error)** を入力して、エラー コードの説明と修復情報を取得します。
- **[認証と承認のエラー コードリファレンスでエラー コードを検索します](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)**。

次のエラー コードはサインアップ イベントに関連付けられていますが、この一覧は完全ではありません。

- **50181**: OTP を検証できません。

    - このエラー コードは、ユーザーが入力しようとしているワンタイム パスコードを検証できない場合に表示されます。
    - ユーザーは新しいワンタイム パスコードを要求する必要があります。
- **50182**: OTP は既に期限切れです。

    - このエラー コードは、ユーザーが入力しようとしているワンタイム パスコードの有効期限が切れている場合に表示されます。
    - ユーザーは新しいワンタイム パスコードを要求する必要があります。
- **1002027**: 収集された属性の一部が無効でした。

    - サインアップ時にユーザーが入力した 1 つ以上の属性が有効な形式ではありません。
    - ユーザーは属性を再入力する必要があります。
- **399279**: セルフサービス サインアップ中にユーザーの作成に失敗しました。

    - ユーザーのアカウントを作成できませんでした。
    - ユーザーはサインアップ プロセスを再試行する必要があります。

次のコードは実際のエラーではありません。 これらは、サインアップ プロセスの対話型の性質により発生する予期される中断を示します。

- **1002013**: ユーザーは、電子メール アドレスの所有権を確認するために、電子メール ワンTime-Passcode を入力するように求められます。

    - これはサインアップ フローの想定される部分であり、ユーザーは電子メールで電子メールで送信されたワンタイム パスコードを入力して、電子メール アドレスの所有権を確認するように求められます。
- **50140**: サインアップ後のサインイン中に、ユーザーに [サインインしたままにする] オプションが表示されます。

    - これはサインイン フローの想定される部分であり、ユーザーは、さらにサインインを容易にするために、このブラウザーにサインインしたままにするかどうかを確認するメッセージが表示されます。

推奨される措置を講じても問題が解決しない場合は、サポート リクエストを開きます。 詳細については、 [Microsoft Entra ID のサポートを受ける方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-use-recommendations"} -->
## Microsoft Entra の推奨事項を使用する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-recommendations
- Service: entra-id / monitoring-health
- Article date: 2025-08-22
- Summary: Microsoft Entra の推奨事項を使用して、お使いのテナントの正常性を監視および改善する方法について説明します。

Microsoft Entra の推奨事項機能は、実行可能なガイダンスを含むパーソナライズされた分析情報を以下のために提供します。

- Microsoft Entra 関連の機能のベスト プラクティスを実装する機会の特定を支援します。
- Microsoft Entra テナントの状態を改善する。
- シナリオに合わせて構成を最適化します。

この記事では、Microsoft Entra の推奨事項を操作する方法について説明します。 Microsoft Entra の各推奨事項には、説明、推奨事項に対処する価値、推奨事項に対処する手順など、同じような詳細が含まれています。 このアーティクルでは、Microsoft Graph API ガイダンスも提供されています。

### 前提条件

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポート閲覧者 | 読み取り専用 |
| セキュリティ閲覧者 | 読み取り専用 |
| グローバル閲覧者 | 読み取り専用 |
| 認証ポリシー管理者 | 更新と読み取り |
| Exchange 管理者 | 更新と読み取り |
| セキュリティ管理者 | 更新と読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph での読み取り専用 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### レコメンデーションを読み取る方法

ほとんどの推奨事項は同じパターンに従います。 推奨事項のしくみ、その値、推奨事項に対処するためのいくつかのアクション手順に関する情報が提供されます。 このセクションでは、推奨事項で提供される詳細の概要を示しますが、1 つの推奨事項に固有のものではありません。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上でサインインしてください。
2. **Entra ID**&gt;**Overview**&gt;**Recommendations** に移動します。
3. 一覧から推奨事項を選択します。

    [Image: 推奨事項のリストを示すスクリーンショット。]

各推奨事項には、推奨事項の内容、それが重要である理由、および修正方法を説明する一連の同じ詳細が示されます。 推奨事項データは、1 日のラグで 24 時間ごとに更新されます。 まれに、更新プログラムが表示されるまでに最大 72 時間かかる場合があります。

[Image: 推奨事項の状態、優先度、影響を受けたリソースの種類のスクリーンショット。]

#### 状態

推奨事項の **状態** は、アクティブ、完了、無視、または延期できます。 影響を受けたすべてのリソースに対処すると、推奨事項サービスによって推奨事項が自動的に完了としてマークされます。

- **アクティブ**: 推奨事項には、対処する必要があるリソースがあります。 無視、延期、または完了した推奨事項は、手動でアクティブに戻すことができます。
- **完了**: 推奨事項内のすべてのリソースに対処しました。 すべてのリソースがアクション プランに従って対処されると、システムによって状態が自動的に更新されます。 推奨事項を手動で完了としてマークすることはできません。
- **却下**: 推奨事項が無関係であるか、データが間違っている場合は、推奨事項を却下できます。 推奨事項を無視する理由を指定する必要があります。
- **延期**: 後で推奨事項に対処する場合は、それを延期できます。 選択した日付になると、推奨事項は [アクティブ] になります。 推奨事項は最大 1 年間延期できます。

#### 優先順位

推奨事項の**[優先度]** は、低、中、高のいずれかです。 これらの値は、セキュリティへの影響、正常性に関する問題、破壊的変更の可能性など、いくつかの要因によって決まります。

- **[高]**: 必ず対応してください。 対応しないと、セキュリティに重大な影響が及んだり、ダウンタイムが発生する可能性があります。
- **[中]**: 対応する必要があります。 対応しなくても、重大なリスクはありません。
- **[低]**: 対応したほうがよいでしょう。 対応しなくても、セキュリティ リスクや正常性に関する問題はありません。

#### 推奨事項の詳細

- **[Status description]** (状態の説明) には、推奨ステータスが変更された日付が表示されます。
- 推奨事項の**[値]**は、推奨事項を完了することで組織にどのようなメリットがもたらされるか、および関連する機能の価値を説明するものです。
- **[アクション プラン]**には、推奨事項を実装するための手順がステップバイステップで示されています。 アクション プランには、関連するドキュメントへのリンクが含まれていたり、Azure portal で他のページに誘導されたりする場合があります。
- 推奨事項には、 **ユーザーへの影響** が含まれる場合があります。この影響は、推奨事項に対処するときのユーザー エクスペリエンスを説明します。

[Image: 推奨事項の状態の説明、説明、値のスクリーンショット。]

#### 影響を受けるリソース

推奨事項の **影響を受けるリソースの種類** は、アプリケーション、ユーザー、または完全なテナントのいずれかです。 影響を受けるリソースもテナント レベルなら、グローバルな変更が必要になる場合があります。 影響を受けたリソース テーブルには、すべての推奨事項が設定されているわけではありません。 たとえば、[Remove unused applications]\(未使用のアプリケーションの削除\) の推奨事項には、推奨事項 サービスによって識別されたすべてのアプリケーションが一覧表示されます。 ただし、テナント レベルの推奨事項には、表に一覧表示されているリソースはありません。

対処する個別のリソースがある推奨事項の場合、**[影響を受けるリソース]** テーブルに、推奨事項によって識別されるリソースの一覧が含まれます。 リソースの名前、ID、最初に検出された日付、および状態が指定されます。 リソースとしては、たとえば、アプリケーション、ユーザー、リソース サービス プリンシパルなどがあります。

影響を受ける個々のリソースを、"無視" または "延期" としてマークすることができます。 リソース レベルのルールと機能は、推奨事項レベルと同じです。 一部の推奨事項では、リソースまたは**[詳細]**リンクを選択して、リソースに直接アクセスできます。

[Image: [影響を受けるリソース] テーブルの [詳細] リンクのスクリーンショット。]

Microsoft Entra 管理センターでは、影響を受けるリソースは最大 50 個のリソースに制限されています。 推奨事項の影響を受けるリソースをすべて表示するには、Microsoft Graph API 要求 `GET /directory/recommendations/{recommendationId}/impactedResources` を使用します。

### 推奨事項と影響を受けるリソースを更新する方法

推奨事項と関連リソースの状態は、Microsoft Entra 管理センターまたは Microsoft Graph を使用して更新できます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上でサインインしてください。
2. **Entra ID**&gt;**Overview**&gt;**Recommendations** に移動します。
3. 一覧から推奨事項を選択します。
4. **[アクション プラン]** のガイダンスに従います。
5. レコメンデーションの状態を手動で変更する必要がある場合は、ページの上部にある **[マークする]** (Mark as) を選択し、状態を選択します。

    [Image: リソース メニューとの違いを強調するための [状態を設定] オプションのスクリーンショット。]

    - 推奨事項が無関係か、またはデータが間違っていると思われる場合は、推奨事項を **[無視]**としてマークします。
        - 開いたパネルで、サービスを改善できるように、無視された理由を選択します。
    - 後で推奨事項に対処する場合は、その推奨事項を **[延期]**としてマークします。
        - 開いたパネルで、次の年内の日付を選択して推奨事項を延期します。
        - 選択した日付になると、推奨事項は [アクティブ] になります。
    - リソースを再評価して問題を解決するには、無視、延期、または完了した推奨事項を **アクティブ** としてマークします。
    - 影響を受けたすべてのリソースに対処すると、推奨事項は **[完了]**に変わります。
        - 完了した推奨事項のアクティブなリソースをサービスが次回実行された時に識別すると、その推奨事項は **[アクティブ]** に自動的に戻ります。
        - 推奨事項の完了は、監査ログに収集される唯一のアクションです。 これらのログを表示するには、**[Microsoft Entra ID]**&gt;**[監査ログ]** に移動し、サービスを "Microsoft Entra 推奨事項" でフィルター処理します。
6. 影響を受けるリソースの状態を手動で変更する必要がある場合は、**[影響を受けるリソース]** テーブルでそのリソースのチェックボックスをオンにし、メニューから状態を選択します。
7. 引き続きテナントの推奨事項の変更を監視します。

注

推奨事項を手動で完了としてマークすることはできません。 影響を受けるすべてのリソースに対処すると、システムによって推奨事項が自動的に完了としてマークされます。 サービスの実行時に、アクティブなリソースが見つからない場合、推奨事項は完了としてマークされます。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
Microsoft Entra の推奨事項は、`/beta` エンドポイント上で Microsoft Graph を使用して表示および管理できます。 影響を受けるリソースと合わせたレコメンデーションの表示、レコメンデーションの後への延期などを実行できます。 詳細については、[Microsoft Graph の推奨事項に関するドキュメント](https://learn.microsoft.com/ja-jp/graph/api/resources/recommendations-api-overview)を参照してください。

作業を開始するには、次の手順に従って、Graph エクスプローラーの Microsoft Graph を使用して推奨事項を操作します。

1. [グラフ エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)にサインインします
2. ドロップダウンから HTTP メソッドとして **[GET]** を選択します。
3. API バージョンを **[ベータ]** に設定します。

##### すべてのレコメンデーションを表示する

次のクエリを追加してテナントのすべてのレコメンデーションを取得し、**[クエリの実行]** ボタンを選択します。

```http
GET https://graph.microsoft.com/beta/directory/recommendations
```

テナントに適用されるすべてのレコメンデーションが応答に表示されます。 影響、利点、影響を受けたリソースの概要、修復手順が応答で提供されます。 レコメンデーションのレコメンデーション ID を見つけて、影響を受けるリソースを表示します。

##### 特定のレコメンデーションを表示する

特定のレコメンデーションを探す場合は、`recommendationType` を要求に追加できます。 この例では、`applicationCredentialExpiry` レコメンデーションの詳細を取得します。

```http
GET https://graph.microsoft.com/beta/directory/recommendations?$filter=recommendationType eq 'applicationCredentialExpiry'
```

##### 推奨事項の影響を受けるリソースを表示する

一部のレコメンデーションでは、影響を受けるリソースの長い一覧が返される可能性があります。 影響を受けるリソースの一覧を表示するには、推奨事項 ID を見つける必要があります。 すべてのレコメンデーションと特定のレコメンデーションを表示すると、レコメンデーション ID が応答に表示されます。

特定の推奨事項の影響を受けるリソースを表示するには、保存した推奨事項 ID で次のクエリを使用します。

```http
GET /directory/recommendations/{recommendationId}/impactedResources
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-use-sign-in-diagnostics"} -->
## Microsoft Entra のサインイン診断を使用する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-sign-in-diagnostics
- Service: entra-id / monitoring-health
- Article date: 2026-04-02
- Summary: Microsoft Entra ID のサインイン診断ツールを使用してサインイン関連のシナリオをトラブルシューティングする方法。

失敗したサインインの理由を特定することは、たちまち難しい作業になるおそれがあります。 サインインの試行中に何が起こったかを分析し、問題を解決するために利用できる推奨事項を調査する必要があります。 Microsoft サポートなど他の組織を関与させずに問題を解決するのが理想的です。 このような状況にある場合は、Microsoft Entra ID のサインイン診断を使用できます。これは、Microsoft Entra ID でのサインインを調査するのに役立つツールです。

この記事では、サインイン診断の概要と、それを使用してサインインに関連したエラーのトラブルシューティングを行う方法について説明します。

### 前提条件

- *サポート リクエストから*のサインイン診断を使用したり、*問題を診断して解決したりする*のに最低限必要な特権ロールは、[課金管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator)です。
- *サインイン ログから*サインイン診断を使用するには、[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)も必要です。
- タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。
- フラグが設定されたサインイン イベントは、サインイン診断から確認することもできます。
    - フラグが設定されたサインイン イベントは、ユーザーがサインイン エクスペリエンスでフラグを有効にした*後*にキャプチャされます。
    - 詳細については、「[フラグが設定されたサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-flagged-sign-ins)」を参照してください。

### それはどのように機能しますか?

Microsoft Entra ID では、サインインの試行は次により制御されます。

- サインインの試行の**実行者**。
- サインインの試行の**実行方法**。

たとえば、管理者が企業ネットワークからのサインイン時にテナントのすべての側面を構成することを可能にする条件付きアクセス ポリシーを構成できます。 ただし、同じユーザーでも、信頼されていないネットワークから同じアカウントを使用してサインインした場合はブロックされることがあります。

サインインの試行に応答するシステムの柔軟性が高いことから、最終的に、サインインのトラブルシューティングが必要なシナリオに行き着く場合があります。サインイン診断ツールでは、次を実行することでサインインの問題を診断できます。

- サインイン イベントとフラグが設定されたサインインからのデータを分析する。
- 発生したことに関する情報を表示する。
- 問題を解決するための推奨事項を示す。

### サインイン診断にアクセスする方法

Microsoft Entra ID のサインイン診断にアクセスする方法は 3 つあります。 各方法についてはタブを選択して確認してください。

## [問題の診断と解決から](#tab/from-diagnose-and-solve-problems)
Microsoft Entra ID の **[問題の診断と解決]** 領域からサインイン診断を開始できます。 [問題の診断と解決] から、フラグが設定されたサインイン イベントを確認したり、特定のサインイン イベントを検索したりできます。 このプロセスは、条件付きアクセスの [問題の診断と解決] 領域から開始することもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)以上としてサインインします。
2. 左側のナビゲーションの上部にある **[問題の診断と解決]** に移動します。

    [Image: 左側のナビゲーションの [問題の診断と解決] のスクリーンショット。]

    - [条件付きアクセス]、[ユーザー]、[グループ]、[ID 保護]、[多要素認証] からも **[問題の診断と解決]** にアクセスできます。
3. **[サインイン診断]** タイルの **[トラブルシューティング]** リンクを選択します。

    [Image: [サインイン診断] タイルのスクリーンショット。]
4. **[すべてのサインイン イベント]** タブを選択して検索を開始します。

    - 場合によっては、システムはフラグが設定されたサインイン イベントの検索を自動的に開始します。 何も見つからない場合は、**[すべてのサインイン イベント]** タブにリダイレクトされます。
5. 検索フィールドにできるだけ多くの詳細を入力します。

    - **ユーザー**: サインインを試行したユーザーの名前またはメール アドレスを指定します。
    - **アプリケーション**: アプリケーションの表示名またはアプリケーション ID を指定します。
    - **correlationId** または **requestId**: これらの詳細は、エラー レポートまたはサインイン ログの詳細で確認できます。
    - **日付と時刻**: 48 時間以内に発生したサインイン イベントを検索するには、日付と時刻を指定します。
6. **[次へ]** ボタンを選択します。
7. 結果を確認し、必要に応じてアクションを実行します。

## [サインイン ログから](#tab/from-the-sign-in-logs)
サインイン ログ内の特定のサインイン イベントからサインイン診断を開始できます。 特定のサインイン イベントからプロセスを開始すると、診断がすぐに開始されます。 最初に詳細を入力するように求められることはありません。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視とヘルスチェック**&gt;**サインインログ**に移動し、サインインイベントを選択します。

    - 一覧をフィルタリングして、特定のサインイン イベントを簡単に見つけられるようにすることができます。
3. 開いたアクティビティの詳細ウィンドウから、**[サインイン診断の起動]** リンクを選択します。

    [Image: Microsoft Entra ID からサインイン診断を起動する方法を示すスクリーンショット。]
4. 結果を確認し、必要に応じてアクションを実行します。

##### Copilotを使用してサインイン ログを調査する (プレビュー)

サインイン ログには、失敗したサインインと中断されたサインインの AI 強化診断を提供するCopilot スキル (プレビュー段階) が含まれます。標準的なサインイン診断とは異なり、この方法では AI を使用してサインイン イベントを分析し、コンテキストに応じた修復手順を提供します。

Note

プレビュー期間中は、既存のサインイン診断スキルと AI ベースの調査スキルの両方を利用できるため、新しいエクスペリエンスに移行できます。

Note

このスキルを実行しても、Security Copilotコンピューティング ユニット (SKU) は消費されません。 このスキルは、Security Copilotライセンスなしで使用できます。

単一ログ調査ツールを使用するためには:

1. **Microsoft Entra ID**&gt;**監視とヘルス**&gt;**サインイン ログ**にブラウズし、**失敗**状態のサインイン イベントを選択します。
2. **Activity Details** ウィンドウで、**Investigate with Copilot** ボタンを選択して、単一のログ 調査を起動します。

    [Image: サインインイベントの[アクティビティの詳細]ペインにある[Copilotで調査]ボタンを示すスクリーンショット]
3. 分析を確認します。次の情報が提供されます。

    - サインインに失敗した理由の概要。
    - エラーの根本原因。
    - 問題を解決するための推奨される修復手順。
4. 推奨されるアクションに従って、サインインエラーに対処します。

## [サポート リクエストから](#tab/from-a-support-request)
サポート リクエストの作成途中であり、*かつ*選択したオプションがサインイン アクティビティに関連している場合は、サポート リクエスト プロセス中にサインイン診断を実行するように求められます。

1. **[問題の診断と解決]** を参照します。
2. 必要に応じて適切なフィールドを選択します。 例えば次が挙げられます。

    - **サービスの種類**: Microsoft Entra のサインインと多要素認証
    - **問題の種類**: 多要素認証
    - **問題のサブタイプ**: MFA が原因でアプリケーションにサインインできない
3. 結果を確認し、必要に応じてアクションを実行します。

[Image: サインイン診断を開始するサポート リクエスト フィールドのスクリーンショット。]

---

### 診断結果の使い方

サインイン診断による検索が完了すると、画面にいくつかの項目が表示されます。

**[認証の概要]** には、指定した詳細に一致するすべてのイベントが一覧表示されます。 表示される列を変更するには、概要の右上隅にある **[列の表示]** オプションを選択します。

[Image: [認証の概要] のスクリーンショット。]

**[診断結果]** には、サインイン イベント中に何が起こったかが示されます。

- シナリオには、条件付きアクセス ポリシーからの MFA 要件、条件付きアクセス ポリシーの適用が必要になる可能性のあるサインイン イベント、過去 48 時間以内に多数のサインイン試行が失敗したことなどが含まれる可能性があります。
- 関連コンテンツやトラブルシューティング ツールへのリンクが提供される場合があります。
- 結果を読み、実行できるアクションを特定します。
- 必ずしも追加の支援なしで問題を解決できるとは限らないため、サポート チケットを開くことが推奨される場合もあります。

    [Image: シナリオの診断結果のスクリーンショット。]

### 一般的なシナリオ

サインイン診断によって役立つトラブルシューティング情報が得られる一般的なシナリオについては、次のセクションのヒントを確認してください。

#### 条件付きアクセス

条件付きアクセス ポリシーを使用して、必要に応じて適切なアクセス制御を適用して組織のセキュリティを維持します。 条件付きアクセス ポリシーはリソースへのアクセスを許可またはブロックするために使用できるため、サインイン診断に表示されることがよくあります。

- [条件付きアクセスによってブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant#block-access): 条件付きアクセス ポリシーによりユーザーはサインインできませんでした。
- [条件付きアクセスの失敗](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access#select-all-consequences): 条件付きアクセス ポリシーが厳しすぎる可能性があります。 ユーザー、グループ、アプリの完全なセットの構成を確認します。 特定の種類のデバイスからのアクセスを制限した場合の影響を必ず把握してください。
- [条件付きアクセスからの多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant#require-multifactor-authentication): 条件付きアクセス ポリシーにより、ユーザーの MFA プロセスがトリガーされました。
- [条件付きアクセスによる B2B サインインのブロック](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access#conditional-access-for-external-users): 外部 ID によるサインインをブロックするための条件付きアクセス ポリシーが設定されています。

#### 多要素認証

サインイン診断ツールを使用してトラブルシューティングできる多要素認証 (MFA) 関連イベントがいくつかあります。

- **他の要件による MFA**: 結果に条件付きアクセス以外の要件からの MFA が表示された場合は、ユーザーごとに MFA が有効になっている可能性があります。 [ユーザーごとの MFA を条件付きアクセスに変換することをお勧めします](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-turn-off-per-user-mfa)。 サインイン診断では、MFA の中断の原因と対話の結果に関する詳細が提供されます。
- **MFA の "追加のセキュリティ確認"**: MFA によってサインイン試行が中断されたため、診断結果に "追加のセキュリティ確認" に関する情報が提供されます。 このエラーは、ユーザーが初めて MFA を設定する際に設定を完了しなかった場合、または事前に構成が設定されていなかった場合に表示されます。

    [Image: MFA の追加のセキュリティ確認の診断結果を示すスクリーンショット。]
- **資格情報の一致および不一致**: ユーザーが間違った資格情報を入力する場合があります。 サインイン診断ツールは、人的ミスとその他の問題を区別するのに役立ちます。
- **サインインの成功**: サインイン イベントが条件付きアクセスまたは MFA によって中断*されていないか*どうかを確認したい場合がありますが、本来は中断*されるべき*です。 サインイン診断ツールは、中断されるべきなのに中断されていないサインイン イベントに関する詳細情報を提供します。
- **ロックされたアカウント**: ユーザーが間違った資格情報を使用して何度もサインインを試行しました。 診断結果は、サインイン試行がどこから行われているか、またそれが正当なユーザー サインイン試行であるかどうかを判断するのに役立ちます。 アプリ、試行回数、使用されたデバイス、オペレーティング システム、IP アドレスなどの詳細が提供されます。 詳細については、「[Microsoft Entra スマート ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)」を参照してください。
- **無効なユーザー名またはパスワード**: ユーザーが無効なユーザー名またはパスワードを使用してサインインしようとすると、サインイン診断によって、アプリ、試行回数、使用されたデバイス、オペレーティング システム、IP アドレスに関する詳細が提供されます。 この情報は、ユーザーが間違った資格情報を入力したかどうか、またはアプリケーションが古いパスワードをキャッシュして再送信しているかどうかを判断するのに役立ちます。

#### エンタープライズ アプリ

エンタープライズ アプリケーションでは、ID プロバイダー (Microsoft Entra ID) アプリケーションの構成またはサービス プロバイダー (アプリケーション サービス。SaaS アプリケーションとも呼ばれる) の構成で問題が発生する可能性があります。

- **エンタープライズ アプリ サービス プロバイダー**: サインイン フローのサービス プロバイダー (アプリケーション) 側の問題が原因でサインインに失敗した場合は、アプリケーション サービスの問題を修正することで問題は解決されます。 他のサービスにサインインし、診断ガイダンスに従っていくつかの構成を変更する必要があります。
- **エンタープライズ アプリの構成**: アプリケーションの Microsoft Entra ID 側の構成の問題が原因でサインインに失敗した場合は、エンタープライズ アプリケーションでアプリケーションの構成を確認して更新する必要があります。

#### セキュリティの既定値群

セキュリティの既定の設定により、サインイン イベントが中断される可能性があります。 セキュリティの既定値群により、組織にベスト プラクティスのセキュリティが適用されます。 ベストプラクティスの 1 つは、パスワード スプレー、リプレイ攻撃、フィッシング攻撃が成功しないように MFA を構成して使用することを必須にすることです。

詳細については、「[セキュリティの既定値群とは](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)」を参照してください。

#### エラー コードの分析情報

サインイン診断でイベントにコンテキスト分析が含まれていない場合、更新されたエラー コードの説明と関連する内容が表示されることがあります。 エラー コードの分析情報には、シナリオに関する詳しいテキスト、問題の修復方法、問題に関するコンテンツなどが含まれています。

#### レガシ認証

このシナリオには、クライアントがレガシ (または Basic) 認証を使用しようとしたためにブロックまたは中断されたサインイン イベントが含まれます。

セキュリティのベスト プラクティスとして、レガシ認証によるサインインは避けることをお勧めします。 POP、SMTP、IMAP、MAPI などのレガシ認証プロトコルでは MFA を適用できないため、攻撃者が組織を攻撃するための好まれるエントリ ポイントになります。

詳細については、[条件付きアクセスを使用して Microsoft Entra ID へのレガシ認証をブロックする方法](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication)に関する記事をご覧ください。

#### 条件付きアクセスが原因でブロックされた B2B サインイン

この診断シナリオでは、ユーザーが別の組織に属しているためにブロックまたは中断されたサインインを検出します。 たとえば、条件付きアクセス ポリシーによりクライアントのデバイスがリソース テナントに参加していることが要求される B2B サインインなどです。

詳細については、「[B2B コラボレーション ユーザーの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)」を参照してください。

#### リスク ポリシーによるブロック

このシナリオでは、サインイン試行が危険であると識別されたため、リスクベースの条件付きアクセス ポリシーによってサインイン試行がブロックされます。

詳細については、[リスク ポリシーを構成して有効にする方法](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)に関するページを参照してください。

#### パススルー認証

パススルー認証はオンプレミスとクラウド認証テクノロジを統合したものであるため、問題がある場所を特定するのが困難な場合があります。 この診断は、これらのシナリオを簡単に診断して解決できるようにすることを目的としています。

この診断シナリオでは、使用されている認証方法がパススルー認証 (PTA) であり、PTA 固有のエラーがある場合に、ユーザー固有のサインインの問題を識別します。 他の問題が原因のエラー (PTA 認証が使用されている場合でも) も正しく診断されます。

診断結果には、失敗とユーザーのサインインに関するコンテキスト情報が表示されます。 結果には、サインインが失敗した他の理由や、問題を解決するために管理者が実行できる推奨アクションが表示される場合があります。 詳細については、[Microsoft Entra Connect: パススルー認証のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-pass-through-authentication)に関するページを参照してください。

#### シームレス シングル サインオン

シームレスなシングル サインオンでは、Kerberos 認証とクラウド認証が統合されます。 このシナリオには 2 つの認証プロトコルが関与するため、サインインの問題が発生したときに、障害点の把握が困難な場合があります。 この診断は、これらのシナリオを簡単に診断して解決できるようにすることを目的としています。

この診断シナリオでは、サインイン失敗のコンテキストと具体的な失敗の原因を調べます。 診断結果には、サインイン試行に関するコンテキスト情報や、管理者が実行できる推奨アクションが含まれる場合があります。 詳細については、「[Microsoft Entra シームレス シングル サインオンのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sso)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-use-workbooks"} -->
## Microsoft Entra ブックの使用方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks
- Service: entra-id / monitoring-health
- Article date: 2024-10-02
- Summary: Microsoft Entra ID 用の Azure Monitor ブックを使用して、ID 関連のアクティビティ、傾向、ギャップを分析する方法について説明します。

ブックは、Microsoft Entra ID と Azure Monitor にあります。 両方の種類のブックの概念、プロセス、およびベスト プラクティスは同じですが、Microsoft Entra ID のブックでは、Microsoft Entra ID に関連付けられている ID 管理シナリオのみがカバーされています。

ブックを使用する場合、空のブックから始めることも、既存のテンプレートを使用することもできます。 ブック テンプレートを使用すると、最初からビルドせずに、すぐにブックの使用を開始できます。

- **パブリック テンプレート**は[ギャラリー](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-overview#the-gallery)に発行され、ワークブックを使い始める際の良い出発点となります。
- **プライベート テンプレート** は、独自のブックの作成を開始し、テナント内の複数のブックの基盤として機能するテンプレートとして 1 つを保存する場合に役立ちます。

### 前提条件

Microsoft Entra ID 用の Azure Workbooks を使用するには、次のものが必要です。

- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つ Microsoft Entra テナント
- Log Analytics ワークスペース *とその* ワークスペースへのアクセス
- Azure Monitor *および* Microsoft Entra ID の適切なロール

#### Log Analytics ワークスペース

Microsoft Entra ブックを使用する*前*に、[Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)を作成する必要があります。 Log Analytics ワークスペースへのアクセスはいくつかの要因によって決まります。 ワークスペース *と* データを送信するリソースに適したロールが必要です。

詳細については、「 [Log Analytics ワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には、監視データを表示し、監視設定を編集するための [2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader) が用意されています。 Azure ロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics 組み込みロールも用意されています。

- **表示**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定の表示と変更**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内の Microsoft Entra ID ログ データを表示したり、Log Analytics からデータのクエリを実行したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 更新アクセスにより、Microsoft Entra データを Log Analytics ワークスペースに送信するための診断設定を作成および編集する機能が追加されます。

- **読み取り**:

    - レポート閲覧者
    - セキュリティ閲覧者
    - グローバル閲覧者
- **更新:**

    - セキュリティ管理者

Microsoft Entra 組み込みロールの詳細については、「 [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Log Analytics RBAC ロールの詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#log-analytics-contributor)参照してください。

### Microsoft Entra ブックにアクセスする

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** を参照します。

    - **ブック**: テナントで作成されたすべてのブック
    - **パブリック テンプレート**: 一般的または優先度の高いシナリオ用の事前構築済みブック
    - **マイ テンプレート**: 作成したテンプレート
3. 一覧からレポートまたはテンプレートを選択します。 ブックの読み込みに少し時間がかかる可能性があります。

    - 名前でテンプレートを検索します。
    - **[ギャラリー間で参照**] を選択して、Microsoft Entra ID に固有ではないテンプレートを表示します。

    [Image: ナビゲーション手順が強調表示されている Microsoft Entra ブックのスクリーンショット。]

### 新しいブックを作成する

ブックは、ゼロから作成することも、テンプレートから作成することもできます。 新しいワークブックを作成するときは、順次要素を追加したり、**詳細エディター**オプションを使用して、[ワークブック GitHub リポジトリ](https://github.com/Microsoft/Application-Insights-Workbooks/blob/master/schema/workbook.json)からコピーしたワークブックの JSON 表現を貼り付けたりすることができます。

新しいブックをゼロから作成する場合は、以下を実行します。

1. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** を参照します。
2. [ **+ 新規]** を選択します。
3. **[+ 追加]** メニューから要素を選択します。

    使用可能な要素の詳細については、「 [Azure ブックの作成」を](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-create-workbook)参照してください。

    [Image: ブックの編集領域で使用できるオプションのスクリーンショット。]

新しいブックをテンプレートから作成する場合は、以下を実行します。

1. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** を参照します。
2. ギャラリーからブック テンプレートを選択します。
3. ページの上部にある **[編集]** を選択します。

    - ブックの各要素には、独自の **[編集]** ボタンがあります。
    - ブック要素の編集の詳細については、「[Azure ワークブック テンプレート](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-templates)」を参照してください。

    [Image: 編集ボタンが強調表示されているブック テンプレートのスクリーンショット。]
4. 任意の要素の **[編集]** ボタンを選択します。 変更を加え、[ **編集の完了]** を選択します。

    [Image: 編集モードのブックのスクリーンショット。編集要素と完了した編集ボタンが強調表示されています。]
5. ブックの編集が完了したら、[ **保存** ] ボタンを選択します。 [ **名前を付けて保存]** ウィンドウが開きます。
6. **タイトル**、**サブスクリプション**、**リソース グループ**\* と**場所**を指定する

    - 選択したリソース グループのブックを保存する機能が必要です。
    - 必要に応じて、ブックのコンテンツを [Azure Storage アカウント](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-bring-your-own-storage)に保存することを選択します。
7. [ **適用** ] ボタンを選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/howto-view-service-principal-creation-with-audit-log-properties"} -->
## テナントでサービス プリンシパルが作成された理由を調査する手順 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-view-service-principal-creation-with-audit-log-properties
- Service: entra-id / monitoring-health
- Article date: 2026-06-05
- Summary: 新しい監査ログ プロパティを使用して、テナントに新規サービス プリンシパルが追加された理由を理解します。

テナントに新しいサービス プリンシパルが出現したら、その作成された理由と、そのプロセスが誰によって開始されたのかを理解したい場合があります。 [新しい監査ログのプロパティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/understand-service-principal-creation-with-new-audit-log-properties)`ServicePrincipalProvisioningType`、`SubscribedSkus`、および`AppOwnerOrganizationId`は、サービス プリンシパルが Microsoft によってプロビジョニングされたか、ユーザーによって作成されたか、外部アプリケーションによって追加されたかをすばやく判断するのに役立ちます。 次の手順では、Microsoft Entra 管理センターでこれらのプロパティにアクセスして、調査で使用する方法を示します。 Microsoft Graph API と Log Analytics を利用して、これらの新しいプロパティにアクセスすることもできます。

### [前提条件]

Microsoft Entra 監査ログでこれらのプロパティを表示して使用するには、次のものが必要です。

- Microsoft Entra 管理センターへのアクセス。
- 次のいずれかのロール (または同等のアクセス許可を持つロール): [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、 [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)、または [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)。
- テナントに対する監査ログ機能が有効化されています。 詳細については、 [Microsoft Entra 監査ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)を参照してください。

### 監査ログのプロパティを表示してサービス プリンシパルの詳細を理解する

Microsoft Entra 管理センターで監査ログのプロパティを表示するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
2. **監視と正常性**&gt;**監査ログ**に移動します。
3. **カテゴリ**を **ApplicationManagement** に設定し、フィルターを適用します。
4. **アクティビティを**フィルター処理して**サービス プリンシパルを追加**し、フィルターを適用します。
5. イベントを選択し、[ **その他の詳細]** を展開します。 これらのプロパティ `ServicePrincipalProvisioningType`、 `SubscribedSkus`、および `AppOwnerOrganizationId`を探します。

Log Analytics、Microsoft Sentinel、または別の宛先に監査ログを送信する場合は、**AdditionalDetails** JSON の **AuditLogs** テーブルで同じプロパティを使用できます。 クエリ内のこれらのフィールドを解析して、プロビジョニングの種類、SKU、または所有テナントによってイベントをグループ化できます。

### 新しいサービス プリンシパルを調査する

テナントで新しいプロパティを使用できるようになったら、それらを使用して、Log Analytics と Microsoft Sentinel の新しいサービス プリンシパルの調査を効率化できます。

1. まず、 `ServicePrincipalProvisioningType` から始めて、Microsoft 主導のプロビジョニングと、テナント内の直接のユーザーまたはアプリ アクティビティを区別します。
2. 値がサブスクリプションの場合は、 `SubscribedSkus` を確認して、テナント内のどのサブスクリプションまたはサービス プランが Microsoft サービス プリンシパルを Just-In-Time プロビジョニングの対象にしたかを確認します。
3. `AppOwnerOrganizationId`を使用して、アプリがテナント、Microsoft サービス テナント、または別の外部テナントに所属しているかどうかを理解します。
4. これらのフィールドを`initiatedBy`や`targetResources`などの既存の監査ログ プロパティと組み合わせることで、追加の API 呼び出しに依存せずに、誰がまたは何がサービス プリンシパルを作成したかを正確に把握できます。

注

`AppOwnerOrganizationId` プロパティは、バッキング アプリケーション オブジェクトがあるイベントに対してのみ表示されます。 `SubscribedSkus` プロパティは、`ServicePrincipalProvisioningType` = `Subscription`した場合にのみイベントに対して表示されます。

これらの機能強化は、サービス プリンシパル作成イベントを解釈し、さまざまな値の意味をすばやく理解し、その情報を独自のアラートおよびガバナンス ワークフローに組み込みやすくするために設計されています。 これらのアラートは、テナントのセキュリティ設定に基づいて、サービス プリンシパル作成イベントを "予期される" イベントと "予期しない" イベントとして分類できます。

### Microsoft Graph の使用

これらのイベントをプログラムで取得するには、 [List directoryAudits](https://learn.microsoft.com/ja-jp/graph/api/directoryaudit-list?view=graph-rest-1.0&preserve-view=true) 操作に対してクエリを実行し、アクティビティ名をフィルター処理します。 新しいプロパティは、各イベントの `additionalDetails` コレクションに返されます。

```http
GET https://graph.microsoft.com/v1.0/auditLogs/directoryAudits?$filter=activityDisplayName eq 'Add service principal'
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/overview-monitoring-health"} -->
## Microsoft Entra の監視と正常性とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health
- Service: entra-id / monitoring-health
- Article date: 2025-11-07
- Summary: Microsoft Entra の監視と正常性におけるログとレポートの特徴と機能について説明します。

Microsoft Entra の監視と正常性の機能は、環境内の ID 関連のアクティビティの包括的なビューを提供します。 このデータによって次のことが可能となります。

- ユーザーがアプリとサービスをどのように利用しているかを明らかにします。
- 環境の正常性に影響する潜在的リスクを検出します。
- ユーザーの作業を妨げている問題をトラブルシューティングします。
- Microsoft Entra ディレクトリに対する変更の監査イベントを確認し、分析情報を得る。

ログイン ログと監査ログは、多くの Microsoft Entra レポートに関連するアクティビティ ログを構成し、テナントのアクティビティの分析、監視、トラブルシューティングに使用できます。 アクティビティ ログを分析と監視のソリューションにルーティングすると、テナントの正常性とセキュリティに関するより深い分析情報が得られます。

この記事では、Microsoft Entra ID で使用できるアクティビティ ログの種類、ログを使用するレポート、およびデータの分析に役立つ利用可能な監視サービスについて説明します。

### ID アクティビティ ログ

アクティビティ ログは、組織内のユーザーの行動を把握するのに役立ちます。 Microsoft Entra ID には、次の 3 種類のアクティビティ ログがあります。

- [**監査ログ**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)には、テナントで実行されたすべてのタスクの履歴が含まれます。
- [**サインイン ログ**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)は、ユーザーとクライアント アプリケーションのサインインの試行をキャプチャします。
- [**プロビジョニング ログ**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)は、サード パーティ サービスを通してテナント内でプロビジョニングされているユーザーに関する情報を提供します。

アクティビティ ログは、Azure portal 内でまたは Microsoft Graph API を使用して表示できます。 アクティビティ ログは、保存または分析のためにさまざまなエンドポイントにルーティングすることもできます。 アクティビティ ログを表示するためのすべてのオプションについては、「[アクティビティ ログにアクセスする方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)」を参照してください。

#### 監査ログ

監査ログは、コンプライアンスのためのシステム アクティビティの記録を提供します。 このデータを使用して、次のような一般的なシナリオに対処することができます。

- テナント内のだれかが管理者グループにアクセスした。 だれがアクセス権を与えたのか。
- 最近オンボードしたアプリが想定どおりに動作していません。 アプリにどのような変更が加えられましたか?
- 特定のエージェントによって実行された操作

#### サインイン ログ

サインイン ログによって、次のような疑問に対する答えを見つけることができます。

- ユーザーのサインインにどのようなパターンがあるか。
- 1 週間で何人のユーザーがユーザー サインインを行ったか。
- アプリに対して認証を行っているエージェントは何ですか?

#### プロビジョニング ログ

プロビジョニング ログを使うと、次のような疑問を解決することができます。

- ServiceNow で正常に作成されたグループ
- Adobe から正常に削除されたユーザー
- Active Directory で正常に作成された Workday のユーザー

### ID レポート

Microsoft Entra アクティビティ ログのデータの確認は、IT 管理者に有用な情報を提供する可能性があります。 主要なシナリオでのデータの確認プロセスを効率化するために、アクティビティ ログを使用する一般的なシナリオに関するいくつかのレポートが作成されました。

- [Identity Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) は、サインイン データを使用して、危険なユーザーとサインイン アクティビティに関するレポートを作成します。
- サービス プリンシパルやアプリ資格情報アクティビティなど、アプリケーションに関連するアクティビティは、[使用状況と分析情報](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report)内のレポートを作成するために使用されます。
- [Microsoft Entra ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks)は、アクティビティ ログを確認と分析するためにカスタマイズ可能な方法を提供します。
- [Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)は、テナントのセキュリティの監視と向上のために使用します。
- [Microsoft Entra Health](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health) は、いくつかの主要なシナリオに対するグローバル サービス レベル アグリーメントの達成と正常性信号を捉えます。

### ID 監視とテナントの正常性

Microsoft Entra アクティビティ ログの確認は、テナントの正常性とセキュリティを維持および改善するための最初のステップです。 データを分析し、危険なシナリオを監視し、改善を行える場所を決定する必要があります。 Microsoft Entra 監視は、情報に基づいた意思決定を行うのに役立つ必要なツールを提供します。

Microsoft Entra アクティビティ ログを監視するには、ログ データを監視と分析のソリューションにルーティングする必要があります。 エンドポイントには、Azure Monitor ログ、Microsoft Sentinel、またはサードパーティ ソリューションであるサードパーティー セキュリティ情報イベント管理 (SIEM) ツールが含まれます。

- [イベント ハブにログをストリーミングし、サードパーティー SIEM ツールとの統合を行います。](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)
- [ログを Azure Monitor ログと統合します。](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)
- [Azure Monitor ログと Log Analytics を使用してログを分析します。](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-activity-logs-log-analytics)

### ユース ケース

利用可能なログ、レポート、監視サービスの使用方法は、組織のニーズによって異なります。 ユース ケースとソリューションの優先順位を高めるため、これらのソリューションがどのように相互に関連、それぞれの相違点、組み合わせて使用する方法について確認することをお勧めします。

#### 考慮事項

- **保持** - ログの保持: Microsoft Entra の監査ログとサインイン ログを 30 日より長く保存します
- **分析** - ログは分析ツールを使用して検索できます
- **運用とセキュリティに関する分析情報** - アプリケーションの使用状況、サインイン エラー、セルフサービスの使用状況、傾向などへのアクセスを提供します。
- **SIEM 統合** - Microsoft Entra のログイン ログと監査ログを SIEM システムに統合とストリーミング

Microsoft Entra の監視を使用すると、Microsoft Entra アクティビティ ログをルーティングし、長期的なレポートと分析のために保持して環境の分析情報を得て、SIEM ツールに統合できます。 次の意思決定フロー チャートを使用し、アーキテクチャの選択に役立てます。

[Image: ビジネス ニーズ アーキテクチャの意思決定マトリックスのグラフィック。]

アクティビティ ログへのアクセス、保存、分析の方法の概要については、「[アクティビティ ログにアクセスする方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/overview-recommendations"} -->
## Microsoft Entra レコメンデーションとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations
- Service: entra-id / monitoring-health
- Article date: 2026-04-28
- Summary: テナントの安全で正常な状態を維持できるよう、Microsoft Entra レコメンデーションの一般的な概要を示します。

テナント内のすべての設定とリソースを追跡することは、大仕事になる可能性があります。 Microsoft Entra レコメンデーション機能は、テナント状態の監視をサポートするため、人による監視は必要はありません。 これらのレコメンデーションは、テナントが安全で正常な状態であることを確認するのに役立ち、また、Microsoft Entra ID で使用できる機能の価値を最大限に高めることができます。

Microsoft Entra レコメンデーションには、*ID セキュリティ スコア*に関するレコメンデーションが含まれるようになりました。 これらのレコメンデーションでは、テナントのセキュリティに関する同様の分析情報が提供されます。 詳しくは、「[ID セキュリティ スコアとは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score)」を参照してください。

これらの Microsoft Entra レコメンデーションのすべてで、次のための実行可能なガイダンスを含むパーソナライズされた分析情報が提供されます。

- ID のベスト プラクティスを実装する機会を特定するのに役立ちます。
- Microsoft Entra テナントの状態を改善します。
- シナリオに合わせて構成を最適化します。

この記事では、Microsoft Entra レコメンデーションを使用する方法についての概要を説明します。

### それはどのように機能しますか?

毎日、Microsoft Entra ID ではテナントの構成を分析します。 この分析の間に、Microsoft Entra ID は、ユーザーのテナントの構成と、セキュリティのベスト プラクティスおよびレコメンデーション データを比較します。 テナントに適用可能であるというフラグがレコメンデーションに設定されている場合は、Microsoft Entra ID 概要の領域にある **[レコメンデーション]** セクションにレコメンデーションが表示されます。

[Image: [レコメンデーション] オプションが強調表示されているテナントの [概要] ページのスクリーンショット。]

各レコメンデーションには、説明、レコメンデーションに対処する値の概要、およびステップバイステップのアクション プランが含まれています。 該当する場合は、推奨事項に関連付けられている影響を受けるリソースが一覧表示されるため、影響を受けるそれぞれの領域を解決できます。 レコメンデーションにリソースが関連付けられていない場合、*テナント レベル*が影響を受けるリソースの種類になり、ステップ バイ ステップのアクション プランは、特定のリソースだけでなく、テナント全体に影響します。 システムは、前の 24 時間の期間のアクティビティを反映して、推奨事項データを毎日処理します。 場合によっては、データ同期が最大 72 時間延長される場合があります。

### ロールとライセンス

次のロールとMicrosoft Graphアクセス許可は、Microsoft Entraの推奨事項へのアクセスを提供します。 ライセンス要件は、特定の推奨事項によって異なります。推奨事項ごとの詳細については 、推奨事項の概要の表 を参照してください。

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポートビューワー | 読み 取り 専用 |
| セキュリティリーダー | 読み 取り 専用 |
| グローバルリーダー | 読み 取り 専用 |
| 認証ポリシー管理者 | 更新およびデータ読み取り |
| Exchange 管理者 | 更新およびデータ読み取り |
| セキュリティ管理者 | 更新およびデータ読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph の読み取り専用設定 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 推奨事項の概要テーブル

次の表に示されている推奨事項は、現在、パブリック プレビューまたは一般提供されています。また、推奨事項が対象とするリソースの種類やその他の情報が含まれています。 パブリック プレビューでのレコメンデーションのライセンス要件は変わる場合があります。 この表では、個別のガイダンスが必要な推奨事項に関する利用可能なドキュメントへのリンクを示します。

| Recommendation | 影響を受けるリソース | 可用性 | ID セキュリティ スコア | 電子メール通知のターゲット ロール |
| --- | --- | --- | --- | --- |
| AAD Connect の廃止 | テナント | プレビュー | いいえ | ハイブリッド ID の管理者 |
| VPN 統合の構成 | ユーザー | プレビュー | はい | なし |
| [ユーザーごとの MFA を条件付きアクセス MFA に変換する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-turn-off-per-user-mfa) | ユーザー | 一般公開 | いいえ | セキュリティ管理者 |
| 複数のグローバル管理者を指定する | ユーザー | 一般公開 | はい | グローバル管理者 |
| ドメイン コントローラーで印刷スプーラー サービスを無効にする | テナント | プレビュー | はい | なし |
| 信頼できないアプリケーションへの同意をユーザーに許可しない | テナント | 一般公開 | はい | グローバル管理者 |
| パスワードを有効期限切れにしない | テナント | 一般公開 | はい | グローバル管理者 |
| 正しく構成されていない証明機関 ACL を編集する | アプリケーション | プレビュー | はい | なし |
| 正しく構成されていない証明書テンプレートのアクセス制御リストを編集する | アプリケーション | プレビュー | はい | なし |
| 正しく構成されていない証明書テンプレートの所有者を編集する | アプリケーション | プレビュー | はい | なし |
| 正しく構成されていない登録エージェント証明書テンプレートを編集する | アプリケーション | プレビュー | はい | なし |
| 特権 EKU を使用して過度に制限の緩い証明書テンプレートを編集する | アプリケーション | プレビュー | はい | なし |
| 脆弱な証明機関の設定を編集する | アプリケーション | プレビュー | はい | なし |
| ハイブリッドの場合、パスワード ハッシュ同期を有効にする | テナント | 一般公開 | はい | ハイブリッド ID の管理者 |
| レガシ認証をブロックするポリシーを有効にする | ユーザー | 一般公開 | はい | 条件付きアクセス管理者、セキュリティ管理者 |
| セルフサービス パスワード リセットを有効にする | ユーザー | 一般公開 | はい | 認証ポリシー管理者 |
| すべてのユーザーが多要素認証を完了できるようにする | ユーザー | 一般公開 | はい | 条件付きアクセス管理者、セキュリティ管理者 |
| 特権アカウントが委任されていないことを確認する | ユーザー | プレビュー | はい | なし |
| グループ ポリシー オブジェクト (GPO) は、特権のない ID を昇格された特権を持つローカル グループに割り当てます | ユーザー | プレビュー | はい | なし |
| [AD FS から Microsoft Entra ID にアプリケーションを移行する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-apps-from-adfs-to-azure-ad) | アプリケーション | 一般公開 | いいえ | アプリケーション管理者、認証管理者ハイブリッド ID 管理者 |
| [廃止された Azure AD Graph API から Microsoft Graph](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-to-microsoft-graph-api) にアプリケーションを移行する | アプリケーション | プレビュー | いいえ | アプリケーション管理者 |
| [MFA サーバーから Microsoft Entra MFA に移行する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-to-microsoft-entra-mfa) | テナント | 一般公開 | いいえ | グローバル管理者 |
| [廃止された Azure AD Graph API から Microsoft Graph](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-to-microsoft-graph-api) にサービス プリンシパルを移行する | アプリケーション | プレビュー | いいえ | アプリケーション管理者 |
| [Microsoft Authenticator に移行する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-to-authenticator) | ユーザー | プレビュー | いいえ | グローバル管理者 |
| [既知のデバイスからの MFA プロンプトを最小限に抑える](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-mfa-from-known-devices) | ユーザー | 一般公開 | いいえ | グローバル管理者 |
| 偽装を防ぐためにセキュリティで保護されていない Kerberos 委任を変更する | アプリケーション | プレビュー | はい | なし |
| 任意のアプリケーション ポリシーを使用して証明書の登録を禁止する | アプリケーション | プレビュー | はい | なし |
| サインイン リスク ポリシーを使用してすべてのユーザーを保護する | ユーザー | 一般公開 | はい | 条件付きアクセス管理者、セキュリティ管理者 |
| ユーザー リスク ポリシーを使用してすべてのユーザーを保護する | ユーザー | 一般公開 | はい | 条件付きアクセス管理者、セキュリティ管理者 |
| Microsoft LAPS を使用してローカル管理者パスワードを保護および管理する | ユーザー | プレビュー | はい | なし |
| [Insider Risk 条件付きアクセス ポリシーを使用してテナントを保護](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-insider-risk-condition) | ユーザー | 一般公開 | はい | 条件付きアクセス管理者、セキュリティ管理者 |
| 機密性の高いエンティティに対する横移動パスのリスクを軽減する | ユーザー | プレビュー | はい | なし |
| 管理者 SDHolder アクセス許可を持つ疑わしいアカウントのアクセス権を削除する | ユーザー | プレビュー | はい | なし |
| 機密性の高いグループから休止中のアカウントを削除する | ユーザー | プレビュー | はい | なし |
| DCsync アクセス許可を持つ管理者以外のアカウントを削除する | ユーザー | プレビュー | はい | なし |
| 機密性の高い Microsoft Entra Connect アカウントに対する安全でないアクセス許可を削除する | ユーザー | プレビュー | はい | なし |
| [未使用のアプリケーションを削除する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-remove-unused-apps) | アプリケーション | プレビュー | いいえ | アプリケーション管理者 |
| [アプリケーションから未使用の資格情報を削除する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-remove-unused-credential-from-apps) | アプリケーション | プレビュー | いいえ | アプリケーション管理者 |
| [期限切れ間近のアプリケーションの資格情報を更新する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-renew-expiring-application-credential) | アプリケーション | プレビュー | いいえ | アプリケーション管理者 |
| [期限切れ間近のサービス プリンシパルの資格情報を更新する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-renew-expiring-service-principal-credential) | アプリケーション | プレビュー | いいえ | アプリケーション管理者 |
| Microsoft Entra Connect AD DS コネクタのエンタープライズまたはドメイン管理者アカウントを置き換える | ユーザー | プレビュー | はい | なし |
| 管理者ロールに対して MFA を必須にする | ユーザー | 一般公開 | はい | 条件付きアクセス管理者、セキュリティ管理者 |
| セキュリティで保護されていないアカウント属性を解決する | ユーザー | プレビュー | はい | なし |
| GPO で可逆的なパスワードが見つかった | ユーザー | プレビュー | はい | なし |
| アクセス レビューによる非アクティブ ユーザーのレビュー | ユーザー | プレビュー | いいえ | Identity Governance 管理者 |
| Microsoft Entra Connect AD DS Connector アカウントのパスワードをローテーションする | ユーザー | プレビュー | はい | なし |
| ユーザーとグループの自動プロビジョニングを使用してアプリをセキュリティで保護し、管理する | アプリケーション | プレビュー | いいえ | アプリケーション管理者、IT ガバナンス管理者 |
| クリア テキスト資格情報の公開を停止する | ユーザー | プレビュー | はい | なし |
| 脆弱な暗号の使用を停止する | テナント | プレビュー | はい | なし |
| 最小限の特権管理ロールを使用する | ユーザー | 一般公開 | はい | 特権ロール管理者 |
| アプリの発行元を確認する | アプリケーション | プレビュー | いいえ | グローバル管理者 |

Microsoft Entra にはテナントに適用されるレコメンデーションのみが表示されるため、サポートされるすべてのレコメンデーションが表示されない場合があります。

### ID セキュリティ スコア

ページの先頭に表示される ID セキュリティ スコアは、テナントの正常性を数値で表したものです。 ID セキュリティ スコアに適用されるレコメンデーションには、ページの下部にあるテーブルで個別のスコアが示されます。 **セキュリティ** フィルター カードを使用して、推奨事項の一覧をフィルター処理して、ID セキュリティ スコアの推奨事項のみを表示できます。 ID セキュリティ スコアのレコメンデーションには、複数のセキュリティ要因に基づいて全体的なスコアとして計算される*セキュリティ スコア ポイント*が含まれます。

これらのスコアが加算され、ID セキュリティ スコアが生成されます。 詳しくは、「[ID セキュリティ スコアとは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score)」を参照してください。

[Image: ID セキュリティ スコアのスクリーンショット。]

### Microsoft Entra のレコメンデーションは Azure Advisor に関連していますか?

Microsoft Entra のレコメンデーション機能は、[Azure Advisor](https://learn.microsoft.com/ja-jp/azure/advisor/advisor-overview) の Microsoft Entra 固有の実装であり、Azure デプロイを最適化するためのベスト プラクティスに従うのに役立つ、パーソナライズされたクラウド コンサルタントです。 Azure Advisor は、リソースの構成と使用データを分析し、Azure リソースの費用対効果、パフォーマンス、信頼性、およびセキュリティを向上させるのに役立つソリューションを推奨します。

Microsoft Entra のレコメンデーションでは、テナントを安全で正常な状態に保つため、Microsoft Entra テナント用の Microsoft のベスト プラクティスのロールアウトと管理をサポートするのに同様のデータを使用します。 Microsoft Entra のレコメンデーション機能は、テナントのセキュリティ、正常性、使用状況に関する包括的な視点を提供します。

### 電子メール通知 (プレビュー)

Microsoft Entra のレコメンデーションでは、新しいレコメンデーションが生成されたときに電子メール通知が生成されるようになりました。 この新しいプレビュー機能は、レコメンデーションごとに事前に定義されたロールのセットに電子メールを送信します。 たとえば、テナントのアプリケーションの正常性に関連付けられるレコメンデーションは、アプリケーション管理者ロールを持つユーザーに送信されます。

組織が Privileged Identity Management (PIM) を使用している場合、受信者は、電子メール通知を受信するために指定されたロールに昇格する必要があります。 ロールにアクティブに割り当てられているユーザーがいない場合、電子メールは送信されません。 このため、推奨事項を定期的にチェックして、新しい推奨事項を確実に把握することをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/overview-workbooks"} -->
## Microsoft Entra ワークブックとは何ですか? - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks
- Service: entra-id / monitoring-health
- Article date: 2025-02-25
- Summary: ID の監視、アラート、およびデータの視覚化のために、Microsoft Entra ブックを作成して操作する方法について説明します。

IT 管理者は、ID 管理環境の動作を理解できる視覚的な表現として、Microsoft Entra テナント データを確認することが必要になる場合があります。 この記事では、Azure Workbooks for Microsoft Entra ID を使用して Microsoft Entra テナント データを分析する方法の概要について説明します。

Microsoft Entra ID 用の Azure Workbooks を使用すると、次のことができます。

- Azure 内の複数のソースからのデータに対してクエリを実行する
- レポートと分析のためにデータを視覚化する
- 複数の要素を 1 つの対話型エクスペリエンスに結合する

ブックは、Microsoft Entra ID と Azure Monitor にあります。 概念、プロセス、ベスト プラクティスは、両方の種類のブックで同じです。 ただし、Microsoft Entra ID のワークブックでは、Microsoft Entra ID に関連付けられているアイデンティティ管理シナリオのみが対象となります。 サインイン、条件付きアクセス、多要素認証、Identity Protection は、Microsoft Entra ID のブックに含まれるシナリオです。

[Image: Microsoft Entra ワークブック ギャラリーのスクリーンショット。]

他の Azure サービスのワークブックの詳細については、「[Azure Monitor ワークブック](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-overview)」を参照してください。

### それはどのように役立つか

ワークブックは高度にカスタマイズ可能なので、あらゆるシナリオに合わせたワークブックを作成することができます。 パブリック テンプレートは頻繁に追加されるため、出発点として最適です。 ブックを使用する一般的なシナリオは次のとおりです。

- Microsoft Entra テナントに関する共有可能な概要レポートの早見表を取得し、独自のカスタム レポートを作成したい。
- サインイン エラーを見つけて診断し、組織のサインインの正常性の傾向を確認したい。
- Microsoft Entra ログで、サインイン、テナント管理者のアクション、プロビジョニング、リスクを柔軟でカスタマイズ可能な形式で同時に監視したい。
- 条件付きアクセス、セルフサービス パスワード リセットなどの Microsoft Entra 機能のテナントの使用状況の傾向を確認します。
- レガシ認証を使用して環境にサインインしているユーザーを知りたい。
- 条件付きアクセス ポリシーがユーザーのサインイン エクスペリエンスに与える影響について説明します。

### 対象ユーザーについて

ワークブックをカスタマイズできるため、さまざまなユーザーにメリットがあります。 典型的にワークブックを使用するユーザー像は以下の通りです。

- **レポート管理者**: 使用可能なデータとブック テンプレートの上にレポートを作成する責任者
- **テナント管理者**: 利用可能なレポートを使用して分析情報を取得し、アクションを実行するユーザー。
- **ワークブックテンプレートビルダー**: レポート管理者の役割を卒業し、ワークブックを他のユーザーが自身のワークブックを作成するためのテンプレートに変換する人。

### 公開ワークブックテンプレート

パブリック ブック テンプレートは、お客様と現在の Microsoft Entra サービスのニーズを反映するようにビルド、更新、および非推奨とされます。 Microsoft Entra パブリック ブック テンプレートには、詳細なガイダンスが用意されています。

- [認証プロンプト分析](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-authentication-prompts-analysis)
- [条件付きアクセス ギャップ アナライザー](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-conditional-access-gap-analyzer)
- [クロステナント アクセス アクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-cross-tenant-access-activity)
- [多要素認証のギャップ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-mfa-gaps)
- [リスク分析](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-risk-analysis)
- [機密性の高い操作レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-sensitive-operations-report)
- [レガシ認証を使用したサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-legacy-authentication)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/quickstart-access-log-with-graph-api"} -->
## Microsoft Graph API を使ってサインインを分析する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/quickstart-access-log-with-graph-api
- Service: entra-id / monitoring-health
- Article date: 2024-11-13
- Summary: Microsoft Graph API を使用してサインイン ログにアクセスし、シングル サインイン試行を分析する方法について説明します。

このクイックスタートでは、ユーザーのサインインが失敗した場合に何が起こったかを把握するために、Microsoft Entra のサインイン ログの情報を使用します。 このクイックスタートでは、Microsoft Graph API を使用してサインイン ログにアクセスする方法を示します。

### 前提条件

このクイック スタートのシナリオを完了するための要件を次に示します。

- **Microsoft Entra テナントへのアクセス**: Microsoft Entra テナントにアクセスできない場合は、「[Azure の無料アカウントを今すぐ作成しましょう](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)」を参照してください。
- **Isabella Simonsen というテスト アカウント**: テスト アカウントの作成方法がわからない場合は、「[クラウド ベースのユーザーを追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)」を参照してください。
- **Microsoft Graph API へのアクセス**: アクセスをまだ持っていない場合は、「[Microsoft Graph の認証と承認の基本方法](https://learn.microsoft.com/ja-jp/graph/auth/auth-concepts)」を参照してください。

### 失敗したサインインを処理する

この手順の目的は、Microsoft Entra のサインイン ログに失敗したサインインのレコードを作成することです。

1. 間違ったパスワードを使用して、Isabella Simonsen として [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 確実にログでサインイン エントリの記録を見つけられるように、5 分間待ちます。

### 失敗したサインインを見つける

このセクションでは、Microsoft Graph API を使用して失敗したサインイン試行を特定する手順について説明します。

1. クエリを実行できるアクセス許可を持つユーザーとして [Microsoft Graph Explorer](https://developer.microsoft.com/graph/graph-explorer) にサインインします。
2. **[アクセス許可の変更]** を選び、正しいアクセス許可があることを確認します。
3. ドロップダウンから HTTP メソッドとして **[GET]** を選択します。
4. API バージョンを **[ベータ]** に設定します。
5. 次のクエリを入力し、**[クエリの実行]** を選択します: `https://graph.microsoft.com/beta/auditLogs/signIns?$top=10&$filter=userDisplayName eq 'Isabella Simonsen'`
6. クエリの応答を確認し、応答の **status** セクションを見つけます。

[Image: エラー状態セクションが強調表示されているクエリ応答のスクリーンショット。]

### リソースをクリーンアップする

不要になったら、テスト ユーザーを削除します。 Microsoft Entra ユーザーを削除する方法がわからない場合は、[Microsoft Entra ID からユーザーを削除する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)方法に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/quickstart-analyze-sign-in"} -->
## 失敗したサインイン試行を分析するためのクイック スタート ガイド - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/quickstart-analyze-sign-in
- Service: entra-id / monitoring-health
- Article date: 2025-02-25
- Summary: このクイック スタートでは、サインイン ログを使用して、Microsoft Entra ID へのサインインに失敗した理由を特定する方法について説明します。

Microsoft Entra サインイン ログの情報を使用すると、ユーザーのサインインに失敗した場合の動作を把握できます。 このクイック スタートでは、サインイン ログを使用して、失敗したサインインを見つける方法について説明します。

### 前提 条件

このクイック スタートのシナリオを完了するには、次のものが必要です。

- Azure サブスクリプション。 お持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。
- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つ Microsoft Entra テナント。
- テナントの **レポート閲覧者**、 **セキュリティ閲覧者**、または **セキュリティ管理者** ロールを持つユーザー。
- **Isabella Simonsen というテスト アカウント - テスト アカウント** を作成する方法がわからない場合は、「 [クラウドベースのユーザーを追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)する」を参照してください。

### 失敗したサインインを実行する

この手順の目的は、Microsoft Entra サインイン ログに失敗したサインインのレコードを作成することです。

1. 正しくないパスワードを使用して、Isabella Simonsen として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. サインイン ログでイベントが見つかるまで 5 分待ちます。

### 失敗したサインインを見つける

このセクションでは、失敗したサインインを分析する手順について説明します。 サインイン ログをフィルター処理して、分析に関連しないすべてのレコードを削除します。 たとえば、特定のユーザーのレコードのみを表示するようにフィルターを設定します。 その後、エラーの詳細を確認できます。 ログの詳細は役に立つ情報を提供します。 [サインイン エラー検索ツール](https://login.microsoftonline.com/error)を使用してエラーを検索することもできます。 このツールは、サインイン エラーのトラブルシューティングに役立つ情報を提供する場合があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **[Entra ID]**&gt;**[監視と正常性]**&gt;**[サインイン ログ]** を参照します。
3. フィルターを調整して、Isabella Simonsen のレコードのみを表示します。

    1. **[フィルターの追加]** を開き、[**ユーザー**] を選択し、[**適用**] を選択します。

        [Image: ユーザー フィルターを追加する]
    2. [ **ユーザー** ] ボックスに「 **Isabella Simonsen**」と入力し、[ **適用**] を選択します。
4. 失敗したサインイン試行を選択し、詳細を表示します。
5. **サインイン エラー コード**をコピーします。

    [Image: サインイン エラー コード]
6. [サインイン エラー検索ツール](https://login.microsoftonline.com/error)のテキスト ボックスにエラー コードを貼り付け、[送信] を選択**します**。

ツールの結果を確認し、追加情報が提供されるかどうかを判断します。

### その他のテスト

これで、サインイン ログ内のエントリを名前で検索する方法がわかったら、次のフィルターを使用してレコードを検索する必要もあります。

- **日付** - **Start** と **End** を使用してイザベラを見つけよう。

    [Image: 日付フィルター]
- **Status** - Status **: Failure** を使用して Isabella を見つけようとします。

    [Image: 状態エラー]

### リソースのクリーンアップ

不要になったら、テスト ユーザーを削除します。 Microsoft Entra ユーザーを削除する方法がわからない場合は、「Microsoft [Entra ID からユーザーを削除](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-insider-risk-condition"} -->
## 条件付きアクセスでインサイダー リスクを使用してテナントを保護する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-insider-risk-condition
- Service: entra-id / monitoring-health
- Article date: 2025-04-09
- Summary: Microsoft Purview 適応型保護と統合された条件付きアクセスでインサイダー リスク条件を有効にすることで、テナントを保護する方法について説明します。

[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)は、パーソナライズされた分析情報と、推奨されるベスト プラクティスにテナントを整合させるために実行できるガイダンスを提供する機能です。

この記事では、Microsoft Purview 適応型保護と組み合わせて条件付きアクセスのインサイダー リスク条件を有効にして、テナントを保護するための推奨事項について説明します。 この推奨事項は、Microsoft Graph の推奨事項 API で `insiderRiskPolicy` と呼ばれます。

### 説明

適応型保護は、インサイダー リスク管理の機械学習モデルによって定義および分析されたリスク レベルに基づいて、適切なデータ損失防止 (DLP) ポリシーをユーザーに動的に割り当てます。 この新機能により、静的 DLP ポリシーはユーザー コンテキストに基づいて適応型になります。 データ共有のブロックなどの最も効果的なポリシーは、リスクの高いユーザーにのみ適用されますが、リスクの低いユーザーは生産性を維持できます。

これらのリスク シグナルは、条件付きアクセス ポリシーと統合された場合に、管理者が各リスク レベルに対して適切なアクションを実行できるようにします。 インサイダー リスクを伴う条件付きアクセス ポリシーを構成すると、組織は変化する脅威の状況に効果的に対応できます。

### 価値

リスクの高い内部ユーザーのリソースへのアクセスをブロックする条件付きアクセス ポリシーの実装は、セキュリティを積極的に強化し、インサイダーの脅威を軽減し、リアルタイムで機密データを保護する上で重要な役割を果たしているため、優先度が高くなります。

### 行動計画

1. Microsoft Purview で [\[適応型保護\]](https://compliance.microsoft.com/insiderriskmgmt?viewid=dynamicriskprevention&amp;innerviewid=summary) を有効にします。

    - 適応型保護を構成するには、Microsoft Purview の Insider Risk Management または Insider Risk Management 管理者ロール グループのメンバーである必要があります。
    - 詳細については、「[Microsoft Purview のロールと役割グループ](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/scc-permissions)」を参照してください
2. インサイダー リスク条件を含む[条件付きアクセス ポリシー](https://entra.microsoft.com/#view/Microsoft_AAD_ConditionalAccess/CaTemplates.ReactView/templateIds%7E/%5B%2216aaa400-bfdf-4756-a420-ad2245d4cde8%22%5D)を作成します。

    - このテンプレートを表示するには、[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインする必要があります。
    - 詳細については、「[条件付きアクセス条件: ンサイダー リスク](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#insider-risk)」を参照してください。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID P2 ライセンスが必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-mfa-from-known-devices"} -->
## 既知のデバイスからの MFA プロンプトを最小限に抑える推奨事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-mfa-from-known-devices
- Service: entra-id / monitoring-health
- Article date: 2026-04-28
- Summary: Microsoft Entra ID の既知のデバイスからの多要素認証プロンプトを最小限に抑えるための推奨事項について説明します。

[Microsoft Entra のレコメンデーション](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)は、パーソナライズされた分析情報と、推奨されるベストプラクティスにおいてテナントを配置するための実用的ガイダンスを提供する機能です。

この記事では、既知のデバイスで多要素認証のプロンプトを最小限に抑えるためのレコメンデーションについて説明します。 この推奨事項は、Microsoft Graph の推奨事項 API では `tenantMFA` と呼ばれます。

Note

Microsoft Entra ID P1 または P2 ライセンスがある場合は、Microsoft[Conditional Access のサインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)を使用して、この記事で説明する **remember 多要素認証** 設定ではなく、ユーザーに MFA を求める頻度を制御することをお勧めします。 詳細については、「[多要素認証のMicrosoft Entra要求プロンプトとセッションの有効期間](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime)」を参照>。

### Prerequisites

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポートビューワー | 読み 取り 専用 |
| セキュリティリーダー | 読み 取り 専用 |
| グローバルリーダー | 読み 取り 専用 |
| 認証ポリシー管理者 | 書き込みと読み込み |
| Exchange 管理者 | 書き込みと読み込み |
| セキュリティ管理者 | 書き込みと読み込み |
| `DirectoryRecommendations.Read.All` | Microsoft Graph での読み取り専用 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

管理者は、自社のリソースのセキュリティを維持する必要がありますが、従業員が必要に応じてリソースに簡単にアクセスできるようにする必要もあります。 MFA を有効にするのはよい決まりですが、ユーザーに表示する MFA プロンプトの数は最小限にする必要があります。 この目標を達成するための選択肢の 1 つに、**ユーザーが信頼するデバイスで多要素認証を記憶**させることがあります。

*信頼するデバイスでの多要素認証の記憶*機能では、ブラウザーでユーザーがサインイン時に *[今後 X 日間はこのメッセージを表示しない]* オプションを選択したときに永続的な Cookie を設定します。 この場合、Cookie の有効期限が切れるまでは、同じブラウザーからユーザーが再度 MFA を求められることはありません。 そのユーザーが同じデバイスで異なるブラウザーを開くか、Cookie をクリアした場合は、再度、認証が求められます。

詳細については、「[Microsoft Entra 多要素認証の設定を構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings)」を参照してください。

この推奨事項は、**多要素認証の記憶**機能を 30 日未満に設定した場合に表示されます。

### 価値

このレコメンデーションは、MFA のプロンプトを減らすことでユーザーの生産性を向上させ、サインイン時間を最少にします。 最も機密性の高いリソースは最も厳しく制御し、最も機密性の低いリソースにはより自由にアクセスできるようにします。

### 行動計画

Microsoft Entra ID P1 または P2 ライセンスをお持ちの場合は、**多要素認証を記憶する**設定を使用する代わりに、セッション管理には [Conditional Access のサインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime) への移行を検討してください。 この設定を引き続き使用するテナントの場合は、次の手順を実行して、期間が 90 日以上に設定されていることを確認します。

1. [Microsoft Entra 多要素認証の設定を構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings)に関する記事を確認してください。
2. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
3. **Entra ID**&gt;**多要素認証**に移動します。
4. **[構成]** 見出しの下にある **[クラウドベースの多要素認証の追加設定]** リンクを選択します。

    [Image: Microsoft Entra 多要素認証セクションの構成設定リンクのスクリーンショット。]
5. **[サービス設定]** タブを選択します。

    [Image: [サービス設定] タブが選択された MFA ページのスクリーンショット。]
6. [ **信頼できるデバイスでの多要素認証を記憶** する] 見出しで、チェック ボックスをオンにし、日数を 90 に設定します。

    [Image: 信頼できるデバイスで MFA を記憶するためのスクリーンショット]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-migrate-apps-from-adfs-to-azure-ad"} -->
## AD FS からアプリケーションを移行するための推奨事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-apps-from-adfs-to-azure-ad
- Service: entra-id / monitoring-health
- Article date: 2026-04-28
- Summary: AD FS から Microsoft Entra ID にアプリケーション認証を移行するための推奨事項について説明します

[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)は、パーソナライズされた分析情報と、推奨されるベストプラクティスにおいてテナントを配置するための実用的ガイダンスの提供します。

この記事では、Active Directory フェデレーション サービス (AD FS) から Microsoft Entra ID にアプリを移行するための推奨事項について説明します。 この推奨事項は、Microsoft Graph の推奨事項 API では `adfsAppsMigration` と呼ばれます。

### Prerequisites

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポートビューワー | 読み 取り 専用 |
| セキュリティリーダー | 読み 取り 専用 |
| グローバルリーダー | 読み 取り 専用 |
| 認証ポリシー管理者 | 更新および読み取り |
| Exchange 管理者 | 更新して読み取る |
| セキュリティ管理者 | 更新および読み込み |
| `DirectoryRecommendations.Read.All` | Microsoft Graph での読み取り専用 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

アプリケーションの管理を担当する管理者として、アプリケーションで Microsoft Entra ID のセキュリティ機能を使用して、その価値を最大化したいと考えています。 この推奨事項は、テナントに Microsoft Entra ID に 100% 移行できる ADFS 上のアプリがある場合に表示されます。 詳細については、「[AD FS から Microsoft Entra ID にアプリケーション認証を移行するステージについて理解する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)」を参照してください。

### 価値

Microsoft Entra ID を使用すると、アプリケーションへのアクセスをセキュリティで保護するときに、アプリケーションごとにアクセスを詳細に制御できます。 Microsoft Entra の B2B コラボレーションを一緒に使用すると、ユーザーの生産性を向上させることができます。 アプリの自動でのプロビジョニングにより、Dropbox、Salesforce などのクラウド SaaS アプリでのユーザー ID のライフサイクルが自動化されます。

### 行動計画

1. AD FS サーバーに [Microsoft Entra Connect をインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-roadmap)します。
2. [AD FS アプリケーションのアクティビティ レポートを確認して](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-application-activity)、AD FS アプリケーションの分析情報を入手します。
3. [アプリケーションを Microsoft Entra ID に移行する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)ためのソリューション ガイドを読みます。
4. アプリケーションを Microsoft Entra ID に移行します。 詳細については、[フェデレーションからクラウド認証に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication)アーティクルを参照してください。

#### ガイド付き攻略

この記事の推奨事項の多くのガイド付きチュートリアルについては、Microsoft 365 管理センターにサインインしたときに、移行ガイド「[ID 管理のために AD FS から Microsoft Entra ID に移行する](https://go.microsoft.com/fwlink/?linkid=2225005)」を参照してください。 サインインせずに自動セットアップ機能をアクティブ化せずにベスト プラクティスを確認するには、Microsoft 365 セットアップ ポータルに移動します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-migrate-to-authenticator"} -->
## Microsoft Authenticator への移行に関する推奨事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-to-authenticator
- Service: entra-id / monitoring-health
- Article date: 2026-04-28
- Summary: Microsoft Entra ID でユーザーを Microsoft 認証アプリに移行することの重要性について説明します。

[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)は、パーソナライズされた分析情報と、推奨されるベスト プラクティスにテナントを整合させるために実行できるガイダンスを提供する機能です。

この記事では、ユーザーを Microsoft Authenticator アプリに移行するための推奨事項について説明します。これが現在、プレビューの推奨事項です。 この推奨事項は、Microsoft Graph の推奨事項 API で `useAuthenticatorApp` と呼ばれます。

### Prerequisites

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポートビューワー | 読み 取り 専用 |
| セキュリティリーダー | 読み 取り 専用 |
| グローバルリーダー | 読み 取り 専用 |
| 認証ポリシー管理者 | 更新およびデータ読み取り |
| Exchange 管理者 | 更新およびデータ読み取り |
| セキュリティ管理者 | 更新およびデータ読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph の読み取り専用設定 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

多要素認証 (MFA) は、Microsoft Entra テナントのセキュリティ態勢を向上させるためのキー コンポーネントです。 かつては携帯ショートメール テキストと音声通話が多要素認証に一般的に使用されていましたが、そのセキュリティはますます低下しています。 また、大量の MFA 方法とメッセージでユーザーに負担をかけたくありません。

ユーザーの負担を軽減する方法の 1 つは、MFA で携帯ショートメールまたは音声通話を使用しているすべてのユーザーを Microsoft Authenticator アプリを使用するように移行することです。 この戦略により、認証方法のセキュリティも向上します。

この推奨事項は、過去 1 週間に Microsoft Authenticator アプリではなく携帯ショートメールまたは音声を使用して認証しているユーザーがテナントにいることを Microsoft Entra ID が検出した場合に表示されます。

### 価値

Microsoft Authenticator アプリを使用したプッシュ通知は、ユーザーにとって最も煩わしくない MFA エクスペリエンスを提供します。 この方法は、テレフォニーではなくデータ接続に依存しているため、最も信頼性が高く安全なオプションです。

確認コード オプションにより、携帯ショートメールと音声通話が機能しない可能性がある、データや携帯電話信号がない分離環境でも MFA が有効になります。

Microsoft Authenticator アプリは、Android および iOS で利用できます。 Microsoft Authenticator は 従来の MFA の要素 (ワンタイム パスコード、プッシュ通知) として機能を果たすことができます。組織でパスワードレスの準備が整ったら、Microsoft Authenticator アプリを使用してパスワードなしで Microsoft Entra ID にサインインできます。

### 行動計画

1. 認証方法\*として、モバイル アプリ\*による通知\*かモバイル アプリ\*からの確認コード\*、またはその両方をユーザー\*が利用可能であることを確認します。 認証オプション\*を構成する\*方法
2. 職場または学校アカウント\*を追加する方法をユーザー\*に説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-migrate-to-microsoft-entra-mfa"} -->
## Microsoft Entra MFA への移行に関する推奨事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-to-microsoft-entra-mfa
- Service: entra-id / monitoring-health
- Article date: 2026-04-28
- Summary: MFA サーバーから Microsoft Entra 多要素認証に移行するための Microsoft Entra の推奨事項について説明します

Microsoft Entra の推奨事項 、お客様のテナントを推奨されるベスト プラクティスに合わせて調整するための、パーソナライズされた分析情報と実用的なガイダンスを提供します。

この記事では、MFA サーバーから Microsoft Entra MFA に移行するための推奨事項について説明します。 この推奨事項は、Microsoft Graph の recommendations API で `mfaServerDeprecation` 呼び出されます。

### Prerequisites

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポートビューワー | 読み 取り 専用 |
| セキュリティリーダー | 読み 取り 専用 |
| グローバルリーダー | 読み 取り 専用 |
| 認証ポリシー管理者 | 更新・読み込み |
| Exchange 管理者 | 更新と読み取り |
| セキュリティ管理者 | 更新およびデータ読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph の読み取り専用設定 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

Azure Multi-Factor Authentication Server (MFA Server) は、2024 年 9 月 30 日に廃止される予定でした。 組織が Microsoft Entra MFA に移行できるように、この Microsoft Entra の推奨事項では、MFA サーバー アクティビティを持つテナントを識別します。 この推奨事項では、アクティブなユーザーを持つテナントと、過去 7 日間の MFA Server の MFA 試行を識別します。 影響を受けるクライアントの一覧を含む MFA Server クライアント統合も、この推奨事項の一部として表示されます。

### 価値

MFA サーバーは、オンプレミスで MFA をデプロイおよび管理するためのコンポーネントです。 2019 年、Microsoft は MFA Server の新しいデプロイの許可を停止し、機能拡張への投資を停止しました。 2022 年 9 月、[Microsoft は MFA Server](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/microsoft-entra-change-announcements-september-2022-train/ba-p/2967454)の廃止を正式に発表しました。

クラウドベースの Microsoft Entra 多要素認証により、回復性、可用性、データの信頼性が向上します。 Microsoft Entra MFA に移行すると、最新のフィッシングに対する耐性のある認証方法とよりきめ細かなアクセス制御にアクセスできるようにすることで、セキュリティ体制の向上に役立ちます。 また、オンプレミス コンポーネントを維持する必要がなくなったため、コストとデプロイの複雑さを軽減できます。

### アクション プラン

1. [MFA Server を Microsoft Entra MFA](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-mfa-user-authentication)に移行する方法について説明します。
2. オンプレミスから Microsoft Entra に MFA ユーザー情報を移行します。

    - この情報は手動で移行するか、MFA Server Migration Utility (推奨) を使用できます。
    - [MFA Server Migration Utility](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility)を使用する方法について説明します。
3. [段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility#enable-staged-rollout) を使用して、MFA Server ではなく Microsoft Entra に対する認証を行うためにユーザーを再ルーティングします。
4. [RADIUS または LDAP 認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility#authentication-services)を使用するアプリケーションなど、MFA サーバーの依存関係を特定して移行します。
5. ドメイン フェデレーション設定を更新し、MFA サーバーを使用停止にします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-migrate-to-microsoft-graph-api"} -->
## Microsoft Graph API への移行に関する推奨事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-migrate-to-microsoft-graph-api
- Service: entra-id / monitoring-health
- Article date: 2026-04-28
- Summary: Azure Active Directory Graph API から Microsoft Graph API に移行するための Microsoft Entra の推奨事項について説明します。

Microsoft Entra の推奨事項 、お客様のテナントを推奨されるベスト プラクティスに合わせて調整するための、パーソナライズされた分析情報と実用的なガイダンスを提供します。

この記事では、アプリケーションとサービス プリンシパルを Azure AD Graph API から Microsoft Graph に移行するための 2 つの推奨事項について説明します。 これらの推奨事項は、Microsoft Graph の recommendations API で `aadGraphDeprecationApplication` および `aadGraphDeprecationServicePrincipal` と呼ばれます。

### Prerequisites

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポートビューワー | 読み 取り 専用 |
| セキュリティリーダー | 読み 取り 専用 |
| グローバルリーダー | 読み 取り 専用 |
| 認証ポリシー管理者 | 更新およびデータ読み取り |
| Exchange 管理者 | 更新およびデータ読み取り |
| セキュリティ管理者 | 更新およびデータ読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph の読み取り専用設定 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

Azure Active Directory (Azure AD) Graph API の廃止は 2020 年に発表され、提供終了サイクルに入りました。 すべてのアプリケーションとサービス プリンシパルは、新しい Microsoft Graph API に移行する必要があります。

一般に、Azure AD Graph API をまだ使用しているアプリケーションとサービス プリンシパルは、組織またはベンダーによって開発されました。 これらのアプリケーションは、開発者が更新するか、新しいバージョンにアップグレードする必要がある可能性があります。

Azure AD Graph の廃止に関連する 2 つの推奨事項があります。 1 つはアプリケーションの一覧を提供し、1 つはサービス プリンシパルの一覧を提供します。 どちらの推奨事項にも個別に対処する必要があります。

#### アプリケーションとサービス プリンシパル

この推奨事項のアプリケーション バージョンでは、テナントに登録され、Azure AD Graph API を呼び出すアプリケーションが詳しく説明されています。 Microsoft Entra 管理センターでのアプリの登録について考えてみましょう。

この推奨事項のサービス プリンシパル バージョンでは、別のテナントに登録されているが、テナントでの使用に同意したアプリケーションについて詳しく説明します。 Microsoft Entra 管理センターのエンタープライズ アプリケーションについて考えてみましょう。 これらのアプリケーションは、マルチテナント企業またはソフトウェア ベンダーの開発者が提供できます。 サービス プリンシパルの場合は、ベンダーに連絡して、新しいバージョンのアプリケーションの更新プログラムを取得する方法を特定する必要があります。

### 価値

Microsoft Graph には、Microsoft Entra および Microsoft 365 サービスにアクセスするための単一の統合エンドポイントが用意されています。 Microsoft Graph API には、Azure AD Graph API のすべての機能に加えて、多くの新しい API 機能があります。 Microsoft Graph クライアント ライブラリには、再試行処理、安全なリダイレクト、透過的な認証、ペイロード圧縮などの機能が組み込まれています。 これらの機能は、Azure AD Graph では使用できませんでした。

Azure AD Graph をまだ呼び出しているアプリケーションまたはサービス プリンシパルは、今後の提供終了アクティビティの影響を受けます。 機能の損失を防ぐために、Microsoft Graph に移行することをお勧めします。

### 行動計画

どちらの推奨事項にも、影響を受けたリソースの一覧が含まれています。 アプリケーションとサービス プリンシパルを確認して更新するプロセスは似ています。

1. 推奨事項の詳細の [**影響を受けたリソース**] で、Azure AD Graph を呼び出す**アプリケーション**と**サービス プリンシパル**の一覧を確認します。
2. [ **詳細** ] リンクを選択すると、Azure AD Graph API アクティビティに関する次の詳細が表示されます。

    [Image: 影響を受けたアプリケーションのスクリーンショット。]

    - **操作名**: アプリケーションの一覧表示、ユーザーの作成、グループの削除など、API 操作の説明
    - **要求 - 30 日間: 過去 30** 日間にこのアプリケーションによって行われた要求の数
    - **最後の要求日**: 操作が最後に実行された日付と時刻。

    [Image: 選択したアプリの追加の詳細のスクリーンショット。]
3. 対応するアプリケーションの所有者または発行元と協力して、アプリケーションの更新に必要な手順を特定します。

これらの推奨事項は、Azure AD Graph API アクティビティが 30 日間存在しない限り **アクティブ** として表示されます。 30 日間 Azure AD Graph API アクティビティがない場合、そのアプリケーションまたはサービス プリンシパルは **[完了]** とマークされます。 すべてのリソースに対処すると、推奨事項は完了としてマーク **されます**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-remove-unused-apps"} -->
## 使われていないアプリを削除するためのレコメンデーション - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-remove-unused-apps
- Service: entra-id / monitoring-health
- Article date: 2026-01-07
- Summary: 使われていないアプリを削除するという Microsoft Entra のレコメンデーションのしくみと、そのガイダンスに従うべき理由について説明します。

[Microsoft Entra のレコメンデーション](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)には、パーソナライズされた分析情報と、推奨されるベストプラクティスにおいてテナントを配置するための実用的ガイダンスの提供という特徴があります。

この記事では、未使用のアプリケーションを調査するためのレコメンデーションについて説明します。 この推奨事項は、Microsoft Graph の推奨事項 API で `staleApps` と呼ばれます。

注

[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/copilot/security/microsoft-security-copilot) を使用すると、自然言語プロンプトを使用し、未使用のアプリケーションに関する分析情報を取得できます。 [Microsoft Security Copilot を使用してアプリケーションのリスクを評価する](https://learn.microsoft.com/ja-jp/entra/fundamentals/copilot-security-entra-investigate-risky-apps#explore-unused-microsoft-entra-applications)方法について説明します。

### 前提条件

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポート閲覧者 | 読み取り専用 |
| セキュリティ閲覧者 | 読み取り専用 |
| グローバル閲覧者 | 読み取り専用 |
| 認証ポリシー管理者 | 更新と読み取り |
| Exchange 管理者 | 更新と読み取り |
| セキュリティ管理者 | 更新と読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph での読み取り専用 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

この推奨事項は、テナントに 90 日を超えて使用されていないアプリケーションがある場合に表示されます。 この推奨事項には、次のシナリオが含まれています。

- アプリが作成されましたが、一度も使用されませんでした。
- アプリがアプリケーション ポートフォリオから[論理的に削除](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restore-app)されていません。
- アプリが存在するテナントや、他のテナントのインスタンス (サービス プリンシパル) でアプリが使用されていません。
- これは他のリソース アプリを呼び出すクライアント アプリですが、過去 90 日間にトークンが発行されていません。
- これは、過去 90 日間にトークンを要求したクライアント アプリの記録がないリソース アプリです。

次のアプリは、この推奨事項から除外されます。

- Microsoft が管理するアプリ。Microsoft が所有するアプリケーションにより作成または変更されたものも含まれます。
- 他のアプリと連携してトークンを取得するアプリ、またはトークンを必要としないシナリオを有効にするために使用されるアプリ。
    - たとえば、[ピアツーピア サーバー](https://learn.microsoft.com/ja-jp/windows/win32/p2psdk/what-is-peer-networking-)、[アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)、[Microsoft Entra Cloud Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)、[リンクされたシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-linked-sign-on)、[パスワード SSO](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications)、[Office アドイン](https://learn.microsoft.com/ja-jp/office/dev/add-ins/publish/host-an-office-add-in-on-microsoft-azure)、[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) は、この推奨事項から除外されます。
- 過去 90 日以内に作成されたアプリ。

### 価値

未使用のアプリケーションを削除すると、攻撃面を減らし、テナントのアプリ ポートフォリオをクリーンアップするのに役立ちます。

### 行動計画

この推奨事項は、Microsoft Entra 管理センター、または Microsoft Graph API で使用できます。 使用されていないアプリケーションを特定したら、組織のニーズに基づいて、それらを削除するか保持するかを決定できます。 そのため、アクション プランは次の 2 つの部分に分けられます。

1. 未使用としてフラグが設定されているアプリケーションを確認します。
2. アプリケーションが必要かどうかを判断し、それに対処する方法を決定します。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
推奨事項によって識別されたアプリケーションは、推奨事項の下部にある **影響を受けたリソース** の一覧に表示されます。

#### アプリケーションを確認する

1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#search-administrator)にサインインします。
2. **Entra ID**&gt;**Overview** に移動します。
3. **[推奨事項]** タブを選択し、**[未使用のアプリケーションを削除する]** 推奨事項を選択します。
4. **[影響を受けるリソース]** テーブルから、**[詳細]** を選択して詳細を表示します。
5. **[リソース]**リンクを選択して、アプリのアプリ登録に直接移動します。
    - または、 **Entra ID**&gt;**App 登録** を参照して、この推奨事項の一部として表示されたアプリケーションを見つけることもできます。

#### アプリケーションが必要かどうかを判断する

アプリが使用されない理由は多数あります。 アプリの使用シナリオとビジネス機能について考えてみましょう。 次に例を示します。

- アプリは非推奨になりましたか?
- そのアプリは、一年の特定の時間にのみ発生するビジネス機能に使用されますか?

アプリケーションを削除するには:

1. テナントからアプリを[論理的に削除](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restore-app)します。
2. 15 日間待ってから、[アプリが定期的に削除されます](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restore-app#permanently-delete-an-application)。

アプリケーションがまだ必要であることを示し、推奨事項をスキップするには:

- [破棄](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-recommendations#how-to-update-a-recommendation-and-impacted-resources)または**延期**に**推奨事項の状態を更新**します。
    - アプリがそのライフサイクルの残りの部分で非アクティブなままであると判断された場合に、**破棄**使用します。
    - アプリが誤って推奨事項に含まれていると考えられる場合に、**破棄**を使用します。
    - アプリの確認に時間が必要な場合は**延期**を使用します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
次の要求を使用すると、Microsoft Graph API で推奨事項と影響を受けるリソースを取得できます。 Microsoft Graph API を使用するには、`DirectoryRecommendations.Read.All` と `DirectoryRecommendations.ReadWrite.All` のアクセス許可が必要です。 詳細については、[ID の推奨事項の使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-recommendations)に関する記事を参照してください。

1. [グラフ エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)にサインインします。
2. ドロップダウンから HTTP メソッドとして **[GET]** を選択します。

#### アプリケーションを確認する

テナントのすべての推奨事項を取得するには:

```http
GET https://graph.microsoft.com/beta/directory/recommendations
```

応答から、次のパターンに一致する推奨事項の ID を見つけます: `{tenantId}_staleApps`。

影響を受けるリソースを特定するには:

```http
GET https://graph.microsoft.com/beta/directory/recommendations/{tenantId}_staleApps
```

状態に基づいてリソースをフィルター処理するには (たとえば、"アクティブ" なリソース):

```http
GET https://graph.microsoft.com/beta/directory/recommendations/{tenantId}_staleApps/impactedResources?$filter=status eq Microsoft.Graph.recommendationStatus'active' 
```

削除する未使用のアプリの `applicationObjectId` または `appId` を特定します。

##### サンプル応答

```json
{
    "id": "ccccdddd-2222-eeee-3333-ffff4444aaaa_staleApps",
    "recommendationType": "staleApps",
    "createdDateTime": "2022-06-16T01:18:55Z",
    "impactStartDateTime": "2022-06-16T01:18:55Z",
    "postponeUntilDateTime": null,
    "lastModifiedDateTime": "2024-07-26T14:17:24Z",
    "lastModifiedBy": "System",
    "displayName": "Remove unused applications",
    "featureAreas": [
        "applications"
    ],
    "insights": "Your tenant has some applications that have not been used in the past 90 days.",
    "benefits": "Removing unused applications improves the security posture and promotes good application hygiene.",
    "category": "identityBestPractice",
    "status": "active",
    "priority": "medium",
    "requiredLicenses": "microsoftEntraWorkloadId",
    "impactType": "apps",
    "actionSteps": [
        {
            "stepNumber": 1,
            "text": "1. Navigate to the app registration blade and delete the unused application."
        },
        {
            "stepNumber": 2,
            "text": "2. We suggest you take appropriate steps to ensure the application is not used in longer intervals of more than 90 days. If so, you should change the frequency of access such that the application’s last used time is within 90 days from its last access date."
        }
    ]
}
```

#### アプリケーションが必要かどうかを判断する

アプリの使用シナリオとビジネス機能について考えてみましょう。

- アプリは非推奨になりましたか?
- そのアプリは、一年の特定の時間にのみ発生するビジネス機能に使用されますか?

アプリを削除できる場合は、次のいずれかのクエリを実行してアプリケーションを削除します。

```http
DELETE /applications/{applicationObjectId}
DELETE /applications(appId='{appId}')
```

15 日間待ってから、[項目を完全に削除する](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-delete?view=graph-rest-1.0&preserve-view=true) Microsoft Graph API のガイダンスに従います。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-remove-unused-credential-from-apps"} -->
## アプリから未使用の資格情報を削除するという推奨事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-remove-unused-credential-from-apps
- Service: entra-id / monitoring-health
- Article date: 2025-04-09
- Summary: Microsoft Entra の推奨事項 "アプリから未使用の資格情報を削除する" のしくみとそれが重要である理由について説明します。

[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations) は、テナントを推奨されるベスト プラクティスに合わせるために、パーソナライズされた分析情報と実用的なガイダンスを提供する機能です。

このアーティクルでは、アプリから未使用の資格情報を削除するためのレコメンデーションについて説明します。 この推奨事項は、Microsoft Graph の推奨事項 API で `staleAppCreds` と呼ばれます。

### 前提条件

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポート閲覧者 | 読み取り専用 |
| セキュリティ閲覧者 | 読み取り専用 |
| グローバル閲覧者 | 読み取り専用 |
| 認証ポリシー管理者 | 更新と読み取り |
| Exchange 管理者 | 更新と読み取り |
| セキュリティ管理者 | 更新と読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph での読み取り専用 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、「 [推奨事項の概要」の表を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)参照してください。

### 説明

アプリケーション資格情報には、そのアプリケーションに登録する必要がある証明書やその他の型のシークレットを含めることができます。 これらの資格情報は、アプリケーションの ID を証明するために使用されます。 アプリケーションでアクティブに使用されている資格情報のみが、アプリケーションにレジスタされたままになります。

次の場合、資格情報は使用されていないと見なされます。

- 過去 30 日間使用されていません。
- OAuth/OIDC フローに使用するアプリケーション、または SAML フローのサービス プリンシパルに追加された資格情報です。

次の資格情報は推奨事項から除外されます。

- 有効期限が切れた資格情報は、影響を **受けたリソース** の一覧に表示されません。
- 未使用として識別されたが、フラグが設定されてから有効期限が切れた資格情報は、影響を受けたリソースの一覧に **[完了** ] と表示 **されます** 。

### 値

未使用のアプリケーション資格情報を削除すると、攻撃面を減らし、テナントのアプリ ポートフォリオから不要なものを片付けるのに役立ちます。

### 行動計画

この推奨事項は、Microsoft Entra 管理センター、または Microsoft Graph API で使用できます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
推奨事項が特定されたアプリケーションは、推奨事項の下部にある **影響を受けたリソース** の一覧に表示されます。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#search-administrator)にサインインします。
2. **Entra ID**&gt;**Overview** に移動します。
3. [推奨事項] タブ **を** 選択し、[ **アプリケーションからの未使用の資格情報の削除** ] 推奨事項を選択します。
4. **影響を受けるリソース**テーブルの次の詳細を書き留めます。

    - **[リソース]** 列にアプリケーション名が表示されます
    - **ID** 列にアプリケーション ID が表示されます
5. [**アクション]** 列から **[詳細**] を選択すると、詳細が表示されます。

    [Image: [詳細] オプションが強調表示されている推奨事項のスクリーンショット。]

    注

    資格情報の配信元がサービス プリンシパルの場合は、「 **サービス プリンシパル** 」セクションのガイダンスに従ってください。
6. 開いたパネルから [資格情報の **更新** ] を選択し、アプリ登録の **[証明書とシークレット** ] 領域に直接移動して、未使用の資格情報を削除します。

    1. または、 **Entra ID**&gt;**App 登録** に移動し、この推奨事項の一部として表示されたアプリケーションを選択します。

        [Image: Microsoft Entra アプリ登録ページのスクリーンショット。]
    2. 次に、アプリ登録の **[証明書とシークレット** ] セクションに移動します。

        [Image: Microsoft Entra ID の [証明書とシークレット] セクションのスクリーンショット。]
7. **未使用の資格情報を見つけて削除します。**

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
次の要求を使用すると、Microsoft Graph API で推奨事項と影響を受けるリソースを取得できます。 Microsoft Graph API を使用するには、`DirectoryRecommendations.Read.All` と `DirectoryRecommendations.ReadWrite.All` のアクセス許可が必要です。 詳細については、「 [ID に関する推奨事項を使用する方法」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-recommendations)参照してください。

1. [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)にサインインします。
2. ドロップダウンから HTTP メソッドとして **GET** を選択します。

テナントのすべての推奨事項を取得するには:

```http
GET https://graph.microsoft.com/beta/directory/recommendations
```

応答から、次のパターンに一致する推奨事項の ID を見つけます: `{tenantId}_staleAppCreds`。

影響を受けるリソースを特定するには:

```http
GET https://graph.microsoft.com/beta/directory/recommendations/{tenantId}_staleAppCreds
```

状態に基づいてリソースをフィルター処理するには (たとえば、"アクティブ" なリソース):

```http
GET https://graph.microsoft.com/eta/directory/recommendations/{tenantId}_staleAppCreds/impactedResources?$filter=status eq Microsoft.Graph.recommendationStatus'active'
```

- 削除する資格情報の `AppId`、`CredentialId`、情報元をメモしておきます。
- 次の Microsoft Graph API を使用して、新しいパスワードまたはキーの資格情報を追加します。
    - [リムーブパスワード](https://learn.microsoft.com/ja-jp/graph/api/application-removepassword?view=graph-rest-1.0&preserve-view=true)
    - [キーを削除](https://learn.microsoft.com/ja-jp/graph/api/application-removekey?view=graph-rest-1.0&preserve-view=true)

##### サンプル応答

```json
{
  "id": "aaaabbbb-0000-cccc-1111-dddd2222eeee_staleAppCreds",
  "recommendationType": "staleAppCreds",
  "createdDateTime": "2022-09-07T21:25:36Z",
  "impactStartDateTime": "2022-09-07T21:25:36Z",
  "postponeUntilDateTime": null,
  "lastModifiedDateTime": "2024-07-22T15:23:29Z",
  "lastModifiedBy": "System",
  "displayName": "Remove unused credentials from applications",
  "featureAreas": [
    "applications"
  ],
  "insights": "Your tenant has applications with credentials which have not been used in more than 30 days.",
  "benefits": "An application credential is used to get a token that grants access to a resource or another service.",
  "category": "identityBestPractice",
  "status": "active",
  "priority": "medium",
  "releaseType": "preview",
  "requiredLicenses": "microsoftEntraWorkloadId",
  "impactType": "apps",
  "actionSteps": [
    {
      "stepNumber": 1,
      "text": "1. For application resources, navigate to the app registration section in your tenant."
    },
    {
      "stepNumber": 2,
      "text": "2. In the ‘Certificate and Secrets’ blade, find the credential and remove it."
    },
    {
      "stepNumber": 3,
      "text": "3. To remove a credential from a service principal resource, use the MS Graph Service Principal API service action ",
      "actionUrl": {
        "displayName": "`removePassword`",
        "url": "https://docs.microsoft.com/graph/api/serviceprincipal-removepassword?view=graph-rest-1.0&tabs=http"
      }
    }
  ]
}
```

---

### サービス プリンシパル

資格情報の配信元が **サービス プリンシパル**の場合は、いくつかの考慮事項と追加の手順に従う必要があります。

1 つのアプリケーションには複数のサービス プリンシパルが存在することが多いため、エンタープライズ アプリに移動してすべてを 1 か所で表示する方が簡単な場合があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Entra ID**&gt;**Enterprise アプリ**を参照します。
2. この推奨事項の一部として表示されたアプリケーションを検索して開きます。
3. サイド メニューから [ **シングル サインオン** ] を選択します。

    資格情報がサービス プリンシパルであるものの、使用中の SAML 証明書がある場合は、Microsoft Graph API を使用して資格情報の詳細を識別できます。 Microsoft Graph API を使用するには、`DirectoryRecommendations.Read.All` と `DirectoryRecommendations.ReadWrite.All` のアクセス許可が必要です。 詳細については、「 [ID に関する推奨事項を使用する方法」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-recommendations)参照してください。
4. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
5. ドロップダウンから HTTP メソッドとして **GET** を選択します。
6. API バージョンを **ベータ版**に設定します。
7. `keyCredential` エンドポイントと `passwordCredential` エンドポイントに対してクエリを実行します。
8. `removePassword` エンドポイントまたは `removeKey` エンドポイントを使用して、サービス プリンシパルから資格情報を削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-renew-expiring-application-credential"} -->
## 期限切れのアプリケーション資格情報を更新するという推奨事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-renew-expiring-application-credential
- Service: entra-id / monitoring-health
- Article date: 2025-04-09
- Summary: Microsoft Entra の推奨事項 "期限切れのアプリケーション資格情報を更新する" のしくみと、それが重要な理由について説明します。

[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)は、パーソナライズされた分析情報と、推奨されるベスト プラクティスにテナントを整合させるために実行できるガイダンスを提供する機能です。

この記事では、期限切れのアプリケーション資格情報を更新するためのレコメンデーションについて説明します。 この推奨事項は、Microsoft Graph の推奨事項 API で `applicationCredentialExpiry` と呼ばれます。

### 前提条件

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「[タスクごとの最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポート閲覧者 | 読み取り専用 |
| セキュリティリーダー | 読み取り専用 |
| グローバル閲覧者 | 読み取り専用 |
| 認証ポリシー管理者 | 更新と読み取り |
| Exchange 管理者 | 更新と読み取り |
| セキュリティ管理者 | 更新と読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph での読み取り専用 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

アプリケーション資格情報には、そのアプリケーションに登録する必要がある証明書やその他の型のシークレットを含めることができます。 これらの資格情報は、アプリケーションの ID を証明するために使用されます。

このレコメンデーションは、間もなく期限切れになるアプリケーション資格情報がテナントにある場合に表示されます。

次のような場合は、アプリケーションの資格情報の有効期限が切れかけています。

- アプリケーションの登録が行われており、今後 30 日以内に有効期限が切れるもの。

次の資格情報は推奨事項から除外されます。

- 有効期限が切れていることが確認され、その後アプリ登録から削除された資格情報
- 有効期限が切れた資格情報は、**影響を受けるリソース**のリストに**完了**と表示されます。

### 値

アプリケーションの資格情報を有効期限前に更新することは、中断のない操作を維持し、古い資格情報に起因するダウンタイムのリスクを最小限に抑えるために非常に重要です。

### 行動計画

この推奨事項は、Microsoft Entra 管理センター、または Microsoft Graph API で使用できます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#search-administrator)にサインインします。
2. **Entra ID**&gt;**Overview** に移動します。
3. **[推奨事項]** タブを選択し、**[期限切れ間近のアプリケーションの資格情報を更新する]** 推奨事項を選択します。
4. **[影響を受けたリソース]** テーブルから、次の詳細をメモしておきます。

    - **[リソース]** 列にアプリケーション名が表示されます
    - **[ID]** 列にアプリケーション ID が表示されます

        [Image: [詳細] オプションが強調表示されている推奨事項のスクリーンショット。]
5. **[アクション]** 列から **[詳細]** を選択します。
6. 開いたパネルから **[資格情報の更新]** を選択し、アプリ登録の **[証明書とシークレット]** 領域に直接移動して、有効期限が切れた資格情報を更新します。

    1. または、**Entra ID**&gt;**アプリケーションの登録**を参照し、資格情報を更新する必要があるアプリケーションを見つけます。

    [Image: Microsoft Entra の アプリの登録ページのスクリーンショット。]

    1. アプリ登録の **[証明書とシークレット]** セクションに移動します。
7. ローテーションする資格情報型型を選択し、[ **証明書]** タブまたは [ **クライアント シークレット** ] タブに移動し、プロンプトに従います。

    [Image: Microsoft Entra ID の [証明書とシークレット] セクションのスクリーンショット。]
8. 証明書またはシークレットが正常に追加されたら、サービス コードを更新して、新しい資格情報で動作し、お客様に悪影響を与えないことを確認します。
9. Microsoft Entra サインイン情報ログを使用して、資格情報のキー ID が最近追加されたものと一致することを検証します。
10. 新しい資格情報を検証したら、アプリの **[アプリの登録]**&gt;**[証明書とシークレット]** に戻り、古い資格情報を削除します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
次の要求を使用すると、Microsoft Graph API で推奨事項と影響を受けるリソースを取得できます。 Microsoft Graph API を使用するには、`DirectoryRecommendations.Read.All` と `DirectoryRecommendations.ReadWrite.All` のアクセス許可が必要です。 詳細については、[ID の推奨事項の使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-recommendations)に関する記事を参照してください。

1. [グラフ エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)にサインインします。
2. ドロップダウンから HTTP メソッドとして **[GET]** を選択します。

テナントのすべての推奨事項を取得するには:

```http
GET https://graph.microsoft.com/beta/directory/recommendations
```

応答から、次のパターンに一致する推奨事項の ID を見つけます: `{tenantId}_ApplicationCredentialExpiry`。

影響を受けるリソースを特定するには:

```http
GET https://graph.microsoft.com/beta/directory/recommendations/{tenantId}_ApplicationCredentialExpiry
```

状態に基づいてリソースをフィルター処理するには (たとえば、"アクティブ" なリソース):

```http
GET https://graph.microsoft.com/beta/directory/recommendations/536279f6-15cc-45f2-be2d-61e352b51eef_ ApplicationCredentialExpiry’/impactedResources?$filter=status eq Microsoft.Graph.recommendationStatus'active'
```

削除する資格情報の `AppId`、`CredentialId`、`Origin` を書き留めておきます。 資格情報を削除するには、次の Microsoft Graph ガイダンスを使用します。

- [addPassword](https://learn.microsoft.com/ja-jp/graph/api/application-addpassword?view=graph-rest-1.0&preserve-view=true)
- [キーを追加](https://learn.microsoft.com/ja-jp/graph/api/application-addkey?view=graph-rest-1.0&preserve-view=true)
- [removePassword](https://learn.microsoft.com/ja-jp/graph/api/application-removepassword?view=graph-rest-1.0&preserve-view=true)
- [キーを削除](https://learn.microsoft.com/ja-jp/graph/api/application-removekey?view=graph-rest-1.0&preserve-view=true)

##### サンプル応答

```json
 {
  "id": "aaaabbbb-6666-cccc-7777-dddd8888eeee_ApplicationCredentialExpiry",
  "recommendationType": "applicationCredentialExpiry",
  "createdDateTime": "2022-06-08T00:08:01Z",
  "impactStartDateTime": "2022-06-08T00:08:01Z",
  "postponeUntilDateTime": null,
  "lastModifiedDateTime": "2024-07-29T12:03:16Z",
  "lastModifiedBy": "System",
  "displayName": "Renew expiring application credentials",
  "featureAreas": [
    "applications"
  ],
  "insights": "Your tenant has applications with credentials that will expire soon.",
  "benefits": "Renewing the app credential(s) before its expiration ensures the application continues to function and reduces the possibility of downtime due to an expired credential.",
  "category": "identityBestPractice",
  "status": "active",
  "priority": "high",
  "requiredLicenses": "microsoftEntraWorkloadId",
  "impactType": "apps",
  "actionSteps": [
    {
      "stepNumber": 1,
      "text": "1. Navigate to the App registration section and locate the application for which the credential needs to be rotated."
    },
    {
      "stepNumber": 2,
      "text": "2. Navigate to the “Certificates & Secrets” blade of the app registration."
    },
    {
      "stepNumber": 3,
      "text": "3. Pick the credential type that you want to rotate and navigate to either “Certificates” or “Client Secret” tab and follow the prompts.",
      "actionUrl": null
    },
    {
      "stepNumber": 4,
      "text": "4. Once the certificate or secret is successfully added, update the service code to ensure it works with the new credential and has no negative customer impact. You should use Microsoft Entra ID’s sign-in logs to validate that the thumbprint of the certificate matches the one that was just uploaded.",
      "actionUrl": null
    },
    {
      "stepNumber": 5,
      "text": "5. After validating the new credential, navigate back to the Certificates and Secrets blade for the app and remove the old credential.",
      "actionUrl": null
    }
  ]
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-renew-expiring-service-principal-credential"} -->
## 期限切れのサービス プリンシパル資格情報の更新に関する推奨事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-renew-expiring-service-principal-credential
- Service: entra-id / monitoring-health
- Article date: 2025-04-09
- Summary: 期限切れのサービス プリンシパル資格情報を更新する Microsoft Entra のレコメンデーションのしくみと、それが重要な理由について説明します。

[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)は、パーソナライズされた分析情報と、推奨されるベスト プラクティスにテナントを整合させるために実行できるガイダンスを提供する機能です。

この記事では、期限切れのサービス プリンシパル資格情報を更新するためのレコメンデーションについて説明します。 この推奨事項は、Microsoft Graph の推奨事項 API で `servicePrincipalKeyExpiry` と呼ばれます。

### 前提条件

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「[タスク別の最小特権の役割](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポート閲覧者 | 読み取り専用 |
| セキュリティリーダー | 読み取り専用 |
| グローバルリーダー | 読み取り専用 |
| 認証ポリシー管理者 | 更新と読み取り |
| Exchange 管理者 | 更新と読み取り |
| セキュリティ管理者 | 更新と読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph での読み取り専用 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

サービス プリンシパルの資格情報には、サービス プリンシパルに追加された証明書とクライアント シークレットが含まれます。 資格情報は、そのサービス プリンシパルの ID を証明するために使用されます。 資格情報の有効期限が切れると、サービス プリンシパルを認証できないため、ビジネス シナリオにダウンタイムが発生する可能性があります。 このレコメンデーションは、間もなく期限切れになる資格情報を持つサービス プリンシパルがテナントにある場合に表示されます。

次の場合、サービス プリンシパルの資格情報が期限切れになります。

- サービス プリンシパルに設定されていて、30 日以内に有効期限が切れます。

次の資格情報はこの推奨事項から除外されます。

- 有効期限が切れることが確認され、その後アプリケーションの登録から削除された資格情報。
- 有効期限が切れて、**[影響を受けたリソース]** のリストに **[完了]** と表示されている資格情報。

### 価値

サービス プリンシパルの資格情報を有効期限前に更新することは、中断のない操作を維持し、古い資格情報によって生じるダウンタイムのリスクを最小限に抑えるために不可欠です。

### 行動計画

この推奨事項は、Microsoft Entra 管理センター、または Microsoft Graph API で使用できます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#search-administrator)にサインインします。
2. **Entra ID**&gt;**Overview** に移動します。
3. **[推奨事項]** タブを選択し、**[期限切れ間近のサービス プリンシパルの資格情報を更新する]** 推奨事項を選択します。
4. **[アクション]** 列から **[詳細]** を選択します。
5. 開いたパネルから **[資格情報の更新]** を選択し、アプリ登録の **[シングル サインオン]** 領域に直接移動します。

    1. または、**Entra ID**&gt;**アプリケーションの登録**を参照し、資格情報を更新する必要があるアプリケーションを見つけます。

    [Image: Microsoft Entra の アプリの登録ページのスクリーンショット。]

    1. アプリ登録の **[シングル サインオン]** セクションに移動します。
6. **[SAML 署名証明書]** セクションを編集し、プロンプトに従って新しい証明書を追加します。

    [Image: シングル サインオンの編集プロセスのスクリーンショット。]
7. 証明書またはシークレットが正常に追加されたら、SAML 署名証明書の構成を更新して、新しい証明書をアクティブにします。
8. アプリケーションが期待どおりに動作することを確認し、非アクティブな SAML 証明書を SAML 証明書コレクションから削除します。

注

SAML 資格情報が構成されていない場合にこの推奨事項を受け取った場合は、Microsoft Graph の [**ServicePrincipalAPI**](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal?view=graph-rest-1.0&preserve-view=true) エンドポイントを使用して、サービス プリンシパル オブジェクトの `keyCredentials` プロパティと `passwordCredentials` プロパティを確認します。 資格情報を見つけてローテーションします。

サービス プリンシパルではなく、バッキング アプリケーション オブジェクトで定義されている資格情報で動作するようにサービスを変更することを強くお勧めします。

## [Microsoft Graph API](#tab/microsoft-graph-api)
次の要求を使用すると、Microsoft Graph API で推奨事項と影響を受けるリソースを取得できます。 Microsoft Graph API を使用するには、`DirectoryRecommendations.Read.All` と `DirectoryRecommendations.ReadWrite.All` のアクセス許可が必要です。 詳細については、[ID の推奨事項の使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-recommendations)に関する記事を参照してください。

Microsoft Graph を使用してサービス プリンシパルの資格情報を更新する場合は、クエリを実行してサービス プリンシパルのパスワード資格情報を取得し、新しいパスワード資格情報を追加してから、古い資格情報を削除する必要があります。

1. [グラフ エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)にサインインします
2. ドロップダウンから HTTP メソッドとして **[GET]** を選択します。

テナントのすべての推奨事項を取得するには:

```http
GET https://graph.microsoft.com/beta/directory/recommendations
```

応答から、次のパターンに一致する推奨事項の ID を見つけます: `{tenantId}_servicePrincipalKeyExpiry`。

影響を受けるリソースを特定するには:

```http
GET https://graph.microsoft.com/beta/directory/recommendations/{tenantId}_servicePrincipalKeyExpiry
```

状態に基づいてリソースの一覧をフィルター処理するには (たとえば、`active` としてマークされているリソースのみ):

```http
https://graph.microsoft.com/beta/directory/recommendations/{tenantId}_ servicePrincipalKeyExpiry/impactedResources?$filter=status eq Microsoft.Graph.recommendationStatus'active'
```

- 削除する資格情報の `AppId`、`CredentialId`、情報元をメモしておきます。
- 次の Microsoft Graph API を使用して、新しいパスワードまたはキー資格情報を追加します。
    - [addPassword](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-addpassword?view=graph-rest-1.0&preserve-view=true)
    - [キーを追加](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-addkey?view=graph-rest-1.0&preserve-view=true)
- 古い資格情報を削除するには、次の Microsoft Graph API を使用します。
    - [removePassword](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-removepassword?view=graph-rest-1.0&preserve-view=true)
    - [キーを削除](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-removekey?view=graph-rest-1.0&preserve-view=true)

##### サンプル応答

```json
{
  "id": "ddddeeee-3333-ffff-4444-aaaa5555bbbb_ServicePrincipalKeyExpiry",
  "recommendationType": "servicePrincipalKeyExpiry",
  "createdDateTime": "2022-05-29T00:11:17Z",
  "impactStartDateTime": "2022-05-29T00:11:17Z",
  "postponeUntilDateTime": null,
  "lastModifiedDateTime": "2024-07-26T12:31:58Z",
  "lastModifiedBy": "System",
  "displayName": "Renew expiring service principal credentials",
  "featureAreas": [
    "applications"
  ],
  "insights": "Your tenant has service principals with credentials that will expire soon.",
  "benefits": "Renewing the service principal credential(s) before expiration ensures the application continues to function and reduces the possibility of downtime due to an expired credential.",
  "category": "identityBestPractice",
  "status": "completedBySystem",
  "priority": "high",
  "requiredLicenses": "microsoftEntraWorkloadId",
  "impactType": "apps",
  "actionSteps": [
    {
      "stepNumber": 1,
      "text": "1. Navigate to the Enterprise applications section and locate the Enterprise application for which the credential needs to be rotated."
    },
    {
      "stepNumber": 2,
      "text": "2. Navigate to the “Single sign-on” blade."
    },
    {
      "stepNumber": 3,
      "text": "3. Edit the 'SAML signing certificate' section and follow prompts to add a new certificate."
    },
    {
      "stepNumber": 4,
      "text": "4. After adding the certificate, change its properties to make certificate active. This will make the previous certificate inactive."
    },
    {
      "stepNumber": 5,
      "text": "5. Once the certificate is successfully added and activated, validate that your service is working with the new credential, and remove the old credential."
    },
    {
      "stepNumber": 6,
      "text": "6. If the service principal does not show any credentials after navigating to the enterprise apps blade, we recommend checking the 'passwordCredentials' and 'keyCredentials' property of the service principal object using PowerShell or Microsoft Graph service principal API and use the Microsoft Graph API to rotate credentials."
    }
  ]
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/recommendation-turn-off-per-user-mfa"} -->
## Microsoft Entra ID でユーザーごとの MFA をオフにする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-turn-off-per-user-mfa
- Service: entra-id / monitoring-health
- Article date: 2026-04-28
- Summary: Microsoft Entra の推奨事項に従って Microsoft Entra ID でユーザーごとの MFA をオフにする理由について説明します

[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)は、パーソナライズされた分析情報と、推奨されるベスト プラクティスにテナントを整合させるために実行できるガイダンスを提供する機能です。

この記事では、ユーザーごとの多要素認証 (MFA) アカウントを条件付きアクセス MFA アカウントに切り換えるためのレコメンデーションについて説明します。 この推奨事項は、Microsoft Graph の推奨事項 API では `switchFromPerUserMFA` と呼ばれます。

### Prerequisites

推奨事項を表示または更新するためのさまざまなロール要件があります。 必要なアクセスの型には、最小特権ロールを使用します。 ロールの完全な一覧については、「 [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#monitoring-and-health---recommendations-least-privileged-roles)」を参照してください。

| Microsoft Entra ロール | アクセスの種類 |
| --- | --- |
| レポートビューワー | 読み 取り 専用 |
| セキュリティリーダー | 読み 取り 専用 |
| グローバルリーダー | 読み 取り 専用 |
| 認証ポリシー管理者 | 更新およびデータ読み取り |
| Exchange 管理者 | 更新およびデータ読み取り |
| セキュリティ管理者 | 更新およびデータ読み取り |
| `DirectoryRecommendations.Read.All` | Microsoft Graph の読み取り専用設定 |
| `DirectoryRecommendations.ReadWrite.All` | Microsoft Graph で更新と読み取りを行う |

推奨事項によっては、P2 またはその他のライセンスが必要な場合があります。 詳細については、[推奨事項の概要の表](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#recommendations-overview-table)を参照してください。

### 説明

管理者は、自社のリソースのセキュリティを維持する必要がありますが、従業員が必要に応じてリソースに簡単にアクセスできるようにする必要もあります。 MFA を使用すると、テナントのセキュリティ対策を強化できます。

テナントでは、MFA をユーザーごとに有効にできます。 このシナリオでは、ユーザーはサインインするたびに MFA を実行します。 信頼できる IP アドレスからサインインする場合や、"信頼されたデバイスで MFA を記憶する" 機能が有効な場合など、いくつかの例外があります。 MFA を有効にするのは良い手法ですが、ユーザーごとの MFA を[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)に基づいた MFA に切り換えると、ユーザーが MFA の入力を求められる回数を削減できます。

この推奨事項が示されるのは、次の場合です。

- ユーザーごとの MFA を、少なくとも 5% のユーザーに対して構成している。
- 条件付きアクセス ポリシーをアクティブにしているユーザーが 1% を超える (条件付きアクセス ポリシーを熟知していることを示す)。

### 値

このレコメンデーションは、MFA のプロンプトを減らすことでユーザーの生産性を向上させ、サインイン時間を最少にします。 条件付きアクセスと MFA を併用することにより、最も機密性の高いリソースは最も厳しく制御し、最も機密性の低いリソースにはより自由にアクセスできるようになります。 条件付きアクセスで使用可能な機能の概要については、「[条件付きアクセス ポリシーの構築](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies)」を参照してください。

### 行動計画

1. 条件付きアクセス ポリシーを使用して MFA を必須にします。

    - [条件付きアクセスを使用して Microsoft Entra 多要素認証を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)。
    - MFA でセキュリティ保護したいと考えるすべてのリソースとユーザーをカバーしていることを確認します。
2. ユーザーごとの MFA 構成が無効になっていることを確認します。

    1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
    2. **[ユーザー**&gt;**すべてのユーザー**を参照し、[**ユーザーごとの MFA**] ボタンを選択します。

    [Image: Microsoft Entra 管理センターの [ユーザーごとの MFA] ボタンのスクリーンショット。]

    1. このオプションが有効になっているすべてのユーザーに対して [ **MFA を無効にする** ] を選択します。

    [Image: 管理センターのユーザーごとの MFA 設定のスクリーンショット。]

すべてのユーザーが条件付きアクセス MFA アカウントに移行された後、次にサービスが実行されたときに、推奨事項の状態が自動的に更新されます。 引き続き条件付きアクセス ポリシーを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/reference-audit-activities"} -->
## Microsoft Entra の監査ログ アクティビティのリファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities
- Service: entra-id / monitoring-health
- Article date: 2025-03-25
- Summary: Microsoft Entra ID で監査ログに記録できる監査アクティビティの概要を示します。

Microsoft Entra の監査ログでは、Microsoft Entra テナント内の追跡可能なすべてのアクティビティが収集されます。 監査ログを使うと、サービス、ユーザー、グループ、またはその他の項目に変更を加えたユーザーを判断できます。

この記事には、監査カテゴリとそれに関連するアクティビティの包括的な一覧を掲載します。 特定の監査カテゴリにジャンプするには、「この記事の内容」セクションを使います。

監査ログのアクティビティとカテゴリは定期的に変更されます。 テーブルは定期的に更新されますが、Microsoft Entra ID で使用できるものと同期していない可能性があります。 監査カテゴリやアクティビティが記載されていないと思われる場合は、フィードバックをお寄せください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**Monitoring & health**&gt;**Audit ログ**に移動します。
3. 必要に応じてフィルターを調整します。
4. 詳細を表示するには、結果のテーブルから行を選びます。

### Microsoft Entra (AAD) 管理 UX

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アドミニストレーションユニット | 管理単位にメンバーを一括で追加する - 完了 (一括) |
| アドミニストレーションユニット | 管理単位からメンバーを一括で削除する - 完了 (一括) |
| アドミニストレーションユニット | 開始 (一括) |
| デバイスマネジメント | 認証デバイスの一括追加 - 完了 (一括) |
| デバイスマネジメント | デバイスのダウンロード - 完了 (一括) |
| デバイスマネジメント | 開始 (一括) |
| ディレクトリ管理 | ハードウェア トークンの一括ダウンロード - 完了 (一括) |
| ディレクトリ管理 | 登録イベントとリセット イベントのダウンロード - 完了 (一括) |
| ディレクトリ管理 | ロールの割り当てのダウンロード - 完了 (一括) |
| ディレクトリ管理 | サービス プリンシパルのダウンロード - 完了 (一括) |
| ディレクトリ管理 | ユーザー登録詳細のダウンロード - 完了 (一括) |
| ディレクトリ管理 | ユーザーのダウンロード - 完了 (一括) |
| ディレクトリ管理 | 概要データのエクスポート - 完了 (一括) |
| ディレクトリ管理 | 概要データのエクスポート 新規 - 完了 (一括) |
| ディレクトリ管理 | 開始 (一括) |
| グループ管理 | グループ メンバーの一括インポート - 完了 (一括) |
| グループ管理 | グループ メンバーの一括削除 - 完了 (一括) |
| グループ管理 | グループ メンバーのダウンロード - 完了 (一括) |
| グループ管理 | グループのダウンロード - 完了 (一括) |
| グループ管理 | 開始 (一括) |
| Policy | ブロックされたユーザーの追加 |
| Policy | バイパス ユーザーの追加 |
| Policy | ユーザーに対するブロックの解除 |
| Policy | バイパスされたユーザーの削除 |
| Policy | サインイン リスク ポリシーの更新 |
| Policy | ユーザー リスクと MFA 登録ポリシーの更新 |
| ユーザー管理 | ユーザーの一括作成 - 完了 (一括) |
| ユーザー管理 | ユーザーの一括削除 - 完了 (一括) |
| ユーザー管理 | ユーザーの一括招待 - 完了 (一括) |
| ユーザー管理 | 削除済みユーザーの一括復元 - 完了 (一括) |
| ユーザー管理 | ユーザーのダウンロード - 完了 (一括) |
| ユーザー管理 | 開始 (一括) |

### アクセス レビュー

[Microsoft Entra ID ガバナンス アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-user-access-with-access-reviews)を使用すると、ユーザーが適切なアクセス権を持っていることを確認できます。 アクセス レビューの監査ログを確認すると、アクセス レビューを開始または終了したユーザーがわかります。 また、これらのログから、アクセス レビューの設定が変更されたかどうかもわかります。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| ディレクトリ管理 | プログラムの作成 |
| ディレクトリ管理 | プログラム コントロールのリンク |
| ディレクトリ管理 | プログラム コントロールのリンクの解除 |
| ディレクトリ管理 | プログラムの更新 |
| Policy | 終了したアクセス レビュー |
| Policy | 決定の適用 |
| Policy | 決定の承認 |
| Policy | 決定を一括承認する |
| Policy | 決定を一括拒否する |
| Policy | 決定を一括リセットする |
| Policy | 決定を一括して不明とマークする |
| Policy | 要求を取り消す |
| Policy | アクセス レビューの作成 |
| Policy | 要求を作成 |
| Policy | アクセス レビューの削除 |
| Policy | 承認を削除する |
| Policy | 決定の拒否 |
| Policy | 決定は不明 |
| Policy | 要求の有効期限が切れた |
| Policy | 決定のリセット |
| Policy | アクセス レビューの更新 |
| Policy | パートナー ディレクトリ設定を更新する |
| Policy | 要求を更新する |
| ユーザー管理 | レビューを適用する |
| ユーザー管理 | ビジネス フロー内のすべての要求を承認する |
| ユーザー管理 | 自動確認 |
| ユーザー管理 | レビューを自動適用する |
| ユーザー管理 | ビジネス フローを作成する |
| ユーザー管理 | ガバナンス ポリシー テンプレートを作成する |
| ユーザー管理 | アクセス レビューの削除 |
| ユーザー管理 | ビジネス フローを削除する |
| ユーザー管理 | ガバナンス ポリシー テンプレートを削除する |
| ユーザー管理 | すべての決定を拒否する |
| ユーザー管理 | ビジネス フロー内のすべての要求を拒否する |
| ユーザー管理 | 要求が承認されました |
| ユーザー管理 | 要求が拒否されました |
| ユーザー管理 | ビジネス フローを更新する |
| ユーザー管理 | ガバナンス ポリシー テンプレートを更新する |

### アカウント プロビジョニング

このログには、アプリケーション プロビジョニング、HR プロビジョニング、テナント間同期、 [Microsoft Entra Connect クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)の構成変更が含まれています。 プロビジョニング サービスのログでの監査カテゴリは 1 つのみです。 ユーザーの作成、ユーザーの更新、ユーザーの削除など、プロビジョニング サービスが実行するアクションについては、 [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-provisioning-logs)を使用することをお勧めします。 プロビジョニング構成の変更を監視するには、 [監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)を使用することをお勧めします。

| 監査のカテゴリ | アクティビティ | 説明 |
| --- | --- | --- |
| プロビジョニング管理 | プロビジョニング構成を追加する | 新しいプロビジョニング構成が作成されました。 |
| プロビジョニング管理 | プロビジョニング構成を削除する | プロビジョニング構成が削除されました。 |
| プロビジョニング管理 | プロビジョニング構成を無効化または一時停止する | プロビジョニング ジョブが無効化または一時停止されました。 |
| プロビジョニング管理 | プロビジョニング構成を有効化または再開する | プロビジョニング ジョブが再度開始されました。 |
| プロビジョニング管理 | プロビジョニング構成を有効化または開始する | プロビジョニング ジョブが開始されました。 |
| プロビジョニング管理 | 実行 | プロビジョニング ジョブが実行されている。 ProvisioningJobStartedInitialSync、ProvisioningJobStartedIncrementalSync、ProvisioningJobComplete、ProvisioningJobDisabled など、さまざまなイベントを生成できます。 |
| プロビジョニング管理 | 輸出 | プロビジョニング ジョブは、変更をターゲット システムにエクスポートしました (例: ユーザーの作成)。 |
| プロビジョニング管理 | インポート | プロビジョニング ジョブは、オブジェクトをソース システムからインポートしました (例: アカウントを Salesforce にプロビジョニングする前に、Entra にユーザー プロパティをインポートする)。 |
| プロビジョニング管理 | その他 |  |
| プロビジョニング管理 | プロセス エスクロー | プロビジョニング サービスは、ターゲット アプリケーションに変更をエクスポートできなかったので、操作を再試行しています。 |
| プロビジョニング管理 | 検疫 | プロビジョニング ジョブは、ターゲット アプリケーションへの接続がないなどの問題が原因で、頻度を減らして実行しています。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) |
| プロビジョニング管理 | 同期規則の操作 | プロビジョニング サービスはオブジェクトを評価し、ターゲット システムに変更をエクスポートしませんでした。 これは、プロビジョニングの範囲外であるためにユーザーがスキップされた場合に最も頻繁に出力されます。 |
| プロビジョニング管理 | 属性のマッピングまたはスコープを更新する | プロビジョニング ジョブの属性マッピングまたはスコープ規則が更新されました。 |
| プロビジョニング管理 | プロビジョニングの設定または資格情報を更新する | プロビジョニング ジョブの設定 (例: 通知メール アドレスの変更、すべて同期するか割り当てられたユーザーとグループを同期するか、誤削除の防止) が更新されました。 プロビジョニング ジョブの資格情報が更新されました (例: 新しいベアラー トークンの追加)。 |
| プロビジョニング管理 | ユーザー プロビジョニング | プロビジョニング ジョブのスキーマが既定値に戻されました。 |

### アプリケーション プロキシ

[アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)を使用してユーザーに内部アプリへのリモート アクセスを提供している場合、アプリケーション プロキシ監査ログは、使用可能なアプリケーションまたは[コネクタ グループ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connector-groups)への変更を追跡するのに役立ちます。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アプリケーション管理 | [アプリケーションの追加] |
| アプリケーション管理 | アプリケーションを削除する |
| アプリケーション管理 | アプリケーションを更新する |
| 認証 | 機能ロールアウトにグループを追加する |
| 認証 | 機能のロールアウト ポリシーを作成する |
| 認証 | 機能のロールアウト ポリシーを削除する |
| 認証 | 機能ロールアウトからグループを削除する |
| 認証 | 機能ロールアウトからユーザーを削除する |
| 認証 | 機能のロールアウト ポリシーを更新する |
| 承認 | アプリケーション アクセスのユーザー承認 |
| ディレクトリ管理 | デスクトップ SSO の無効化 |
| ディレクトリ管理 | 特定のドメインに対するデスクトップ SSO の無効化 |
| ディレクトリ管理 | アプリケーション プロキシの無効化 |
| ディレクトリ管理 | パススルー認証の無効化 |
| ディレクトリ管理 | デスクトップ SSO の有効化 |
| ディレクトリ管理 | 特定のドメインに対するデスクトップ SSO の有効化 |
| ディレクトリ管理 | アプリケーション プロキシの有効化 |
| ディレクトリ管理 | パススルー認証の有効化 |
| リソース管理 | コネクタ グループを追加する |
| リソース管理 | コネクタ グループにコネクタを追加する |
| リソース管理 | アプリケーション SSL 証明書の追加 |
| リソース管理 | コネクタ グループを削除する |
| リソース管理 | SSL バインディングの削除 |
| リソース管理 | コネクタの登録 |
| リソース管理 | コネクタ グループを更新する |

### 認証方法

認証方法の監査ログを使うと、ユーザーがモバイル デバイスを適切に登録し、多要素認証を有効にしたことを確認できます。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アプリケーション管理 | ハードウェア Oath トークンを割り当てる |
| アプリケーション管理 | 認証方法ポリシーのリセット |
| アプリケーション管理 | 認証方法ポリシーの更新 |
| アプリケーション管理 | 認証強度の組み合わせ構成の作成 |
| アプリケーション管理 | 認証強度の組み合わせ構成の削除 |
| アプリケーション管理 | 認証強度の組み合わせ構成の更新 |
| アプリケーション管理 | 認証強度ポリシーの作成 |
| アプリケーション管理 | 認証強度ポリシーの削除 |
| アプリケーション管理 | 認証強度ポリシーの更新 |
| アプリケーション管理 | ハードウェア Oath トークンを一括アップロードする |
| アプリケーション管理 | ハードウェア Oath トークンを作成する |
| アプリケーション管理 | ハードウェア Oath トークンを削除する |
| アプリケーション管理 | MFA サービス ポリシーの更新 |
| アプリケーション管理 | PATCH UserAuthMethod.PatchSignInPreferencesAsync |
| アプリケーション管理 | パッチ UserAuthMethod.ResetQRPinAsync |
| アプリケーション管理 | パッチ UserAuthMethod.UpdateQRPinAsync |
| アプリケーション管理 | POST UserAuthMethod.SoftwareOathProofup登録 |
| アプリケーション管理 | ハードウェア Oath トークンを更新する |
| ディレクトリ管理 | DELETE サブスクリプション.DeleteProviders |
| ディレクトリ管理 | Tenant.DeleteAgentStatuses の削除 |
| ディレクトリ管理 | DELETE Tenant.DeleteCaches |
| ディレクトリ管理 | DELETE Tenant.DeleteGreetings |
| ディレクトリ管理 | PATCH テナント.パッチ |
| ディレクトリ管理 | パッチ Tenant.PatchCaches |
| ディレクトリ管理 | ポスト SoundFile.Post |
| ディレクトリ管理 | POST サブスクリプション.CreateProvider |
| ディレクトリ管理 | POST サブスクリプション.CreateSubscription |
| ディレクトリ管理 | POST Tenant.CreateBlockedUser |
| ディレクトリ管理 | POST Tenant.CreateBypassedUser |
| ディレクトリ管理 | POST Tenant.CreateCacheConfig |
| ディレクトリ管理 | POST Tenant.CreateGreeting |
| ディレクトリ管理 | POST Tenant.CreateTenant |
| ディレクトリ管理 | POST Tenant.GenerateNewActivationCredentials |
| ディレクトリ管理 | POSTのTenant.RemoveBlockedUser |
| ディレクトリ管理 | POST Tenant.RemoveBypassedUser |
| ポリシーマネジメント | POST UserAuthMethod.SecurityInfoRegistrationCallback |
| ユーザー管理 | 管理者がセキュリティ情報を削除した |
| ユーザー管理 | 管理者がセキュリティ情報を登録した |
| ユーザー管理 | 管理者がパスワードのリセットを開始した |
| ユーザー管理 | 管理者がセキュリティ情報を更新した |
| ユーザー管理 | パスキー作成オプションを取得 |
| ユーザー管理 | 記憶されているすべてのデバイスで多要素認証の復元 |
| ユーザー管理 | ユーザーごとの多要素認証状態の更新 |
| ユーザー管理 | ユーザーがセキュリティ情報の登録を取り消しました |
| ユーザー管理 | ユーザーが既定のセキュリティ情報を変更した |
| ユーザー管理 | ユーザーがセキュリティ問題を削除した |
| ユーザー管理 | ユーザーが必要なすべてのセキュリティ情報を登録した |
| ユーザー管理 | ユーザーがセキュリティ情報を登録した |
| ユーザー管理 | ユーザーがセキュリティ情報を確認した |
| ユーザー管理 | ユーザーがパスワードの変更を開始した |
| ユーザー管理 | ユーザーがパスワードのリセットを開始した |
| ユーザー管理 | ユーザーがセキュリティ情報の登録を開始した |
| ユーザー管理 | ユーザーが更新したセキュリティ情報 |

### Microsoft Entra (Azure AD) の推奨事項

[Microsoft Entra Recommendations](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations) は、Microsoft Entra テナントを監視し、Microsoft Entra 機能のベスト プラクティスを実装し、テナント構成を最適化するためのパーソナライズされた分析情報と実用的なガイダンスを提供します。 これらのログには、レコメンデーションの状態に加えられた変更の履歴が記録されます。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| ディレクトリ管理 | 推奨事項を無視する |
| ディレクトリ管理 | 推奨事項を完了としてマークする |
| ディレクトリ管理 | 推奨事項を延期する |

### Microsoft Entra (Azure MFA) の多要素認証

Microsoft Entra 多要素認証の監査ログは、疑わしいアクティビティの動向や、いつ不正行為が報告されたかを追跡するのに役立ちます。 [Microsoft Entra サインイン ログ](https://portal.azure.com/#view/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/%7E/SignIns)を使用して、MFA が必要なときにユーザーがサインインするたびに表示します。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| ディレクトリ管理 | バックエンドからデータを削除 |
| ディレクトリ管理 | CosmosDb からデータを削除 |
| ディレクトリ管理 | ExportDataFromBackend (バックエンドからデータをエクスポート) |
| ディレクトリ管理 | ExportDataFromCosmosDb (英語) |
| ユーザー管理 | 不正行為が報告された - 処理の実行なし |
| ユーザー管理 | 不正行為が報告された - MFA のためにユーザーはブロックされた |
| ユーザー管理 | 疑わしいアクティビティが報告された |
| ユーザー管理 | ユーザーがセキュリティ情報を登録した |

### Azure RBAC (昇格されたアクセス)

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| AzureRBACRoleManagementElevateAccess | ユーザー アクセス管理者の役割の割り当てがユーザーから削除されました |
| AzureRBACRoleManagementElevateAccess | ユーザーが Azure リソースのユーザー アクセス管理者へのアクセス権を昇格しました |

### B2B 認証

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| ユーザー管理 | 外部ユーザー招待の引き換え |

### B2C

この一連の監査ログは [B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview) に関連しています。 接続されているリソースと潜在的な外部アカウントの数が多いため、このサービスには多数のカテゴリとアクティビティがあります。 監査カテゴリには、ApplicationManagement、Authentication、Authorization、DirectoryManagement、IdentityProtection、KeyManagement、PolicyManagement、ResourceManagement などがあります。 ワンタイム パスワードに関連するログは、[その他] カテゴリにあります。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| 認証 | セルフサービス サインアップ要求が完了した |
| 認証 | ユーザー フローの一部として API が呼び出された |
| 認証 | 使用できるすべての強力な認証デバイスを削除する |
| 認証 | 条件付きアクセス ポリシーを評価する |
| 認証 | トークンを交換する |
| 認証 | ID プロバイダーと連携する |
| 認証 | 使用できる強力な認証デバイスを取得する |
| 認証 | アプリケーションに SAML アサーションを発行する |
| 認証 | アプリケーションへのアクセス トークンの発行 |
| 認証 | アプリケーションへの認証コードの発行 |
| 認証 | アプリケーションへの id\_token の発行 |
| 認証 | 電話をかけて電話番号を確認する |
| 認証 | TOTP シークレットを登録する |
| 認証 | ユーザーを修復する |
| 認証 | SMS を送信して電話番号を確認する |
| 認証 | 確認メールを送信する |
| 認証 | クライアント資格情報を検証する |
| 認証 | ローカル アカウントの資格情報の検証 |
| 認証 | ユーザー認証の検証 |
| 認証 | メール アドレスを確認 |
| 認証 | ワン タイム パスワードを検証する |
| 認証 | 電話番号を検証する |
| 承認 | v2 アプリケーションのアクセス許可を追加する |
| 承認 | リソース名が使用可能かどうかを確認する |
| 承認 | API コネクタを作成 |
| 承認 | ID プロバイダーを作成する |
| 承認 | authenticationEventListener を作成する |
| 承認 | authenticationEventsFlow を作成する |
| 承認 | カスタム ID プロバイダーを作成する |
| 承認 | カスタム ポリシーを作成する |
| 承認 | customAuthenticationExtension を作成する |
| 承認 | B2C ディレクトリ リソースの作成または更新 |
| 承認 | B2C ディレクトリ テナントとリソースを作成または更新する |
| 承認 | CIAM ディレクトリ テナントとリソースを作成または更新する |
| 承認 | ゲストの使用状況リソースを作成または更新する |
| 承認 | ローカライズされたリソースを作成または更新する |
| 承認 | ポリシー キーを作成する |
| 承認 | スターター パックを作成する |
| 承認 | ユーザー属性の作成 |
| 承認 | ユーザー フローの作成 |
| 承認 | v2 アプリケーションを作成する |
| 承認 | API コネクタを削除 |
| 承認 | 呼び出し元が管理者である B2C テナントを削除する |
| 承認 | B2C ディレクトリ リソースを削除する |
| 承認 | CIAM ディレクトリ リソースを削除する |
| 承認 | ゲストの使用状況リソースを削除します |
| 承認 | ID プロバイダーを削除する |
| 承認 | authenticationEventlistener を削除する |
| 承認 | authenticationEventsFlow を削除する |
| 承認 | カスタム ポリシーを削除する |
| 承認 | customAuthenticationExtension を削除する |
| 承認 | ローカライズされたリソースを削除する |
| 承認 | ポリシー キーを削除する |
| 承認 | ユーザー属性の削除 |
| 承認 | ユーザー フローを削除する |
| 承認 | v2 アプリケーションを削除する |
| 承認 | v2 アプリケーションのアクセス許可の付与を削除する |
| 承認 | キーを生成する |
| 承認 | API コネクタを取得 |
| 承認 | API コネクタを取得 |
| 承認 | 呼び出し元が管理者である B2C テナントを取得する |
| 承認 | B2C ディレクトリ リソースを取得する |
| 承認 | リソース グループ内の B2C ディレクトリ リソースの取得 |
| 承認 | サブスクリプション内の B2C ディレクトリ リソースの取得 |
| 承認 | CIAM ディレクトリ リソースを取得する |
| 承認 | リソース グループ内の CIAM ディレクトリ リソースを取得する |
| 承認 | サブスクリプション内の CIAM ディレクトリ リソースを取得する |
| 承認 | ゲストの使用状況リソースを取得する |
| 承認 | サブスクリプション内のゲストの使用状況リソースを取得する |
| 承認 | ID プロバイダーを取得する |
| 承認 | ID プロバイダーを取得する |
| 承認 | OnAttributeCollectionStartCustomExtension を取得する |
| 承認 | OnAttributeCollectionSubmitCustomExtension を取得する |
| 承認 | OnPageRenderStartCustomExtension を取得する |
| 承認 | ポリシー キーからアクティブなキー メタデータを取得する |
| 承認 | 年齢制限の構成を取得する |
| 承認 | 認証フロー ポリシーを取得する |
| 承認 | authenticationEventListener を取得する |
| 承認 | authenticationEventsFlow を取得する |
| 承認 | authenticationEventsFlows を取得する |
| 承認 | 使用可能な出力要求を取得する |
| 承認 | 構成済みのカスタム ID プロバイダーを取得する |
| 承認 | 構成済みの ID プロバイダーを取得する |
| 承認 | 構成済みのローカル ID プロバイダーを取得する |
| 承認 | カスタム ドメインを取得する |
| 承認 | カスタム ID プロバイダーを取得する |
| 承認 | カスタム ポリシーを取得する |
| 承認 | カスタム ポリシーを取得する |
| 承認 | カスタム ポリシー メタデータを取得する |
| 承認 | customAuthenticationExtension を取得する |
| 承認 | customAuthenticationExtensions を取得する |
| 承認 | ID プロバイダーの種類を取得する |
| 承認 | テナント一覧を取得する |
| 承認 | ローカライズされたリソースを取得する |
| 承認 | 非同期操作の操作状態を取得する |
| 承認 | Microsoft.AzureActiveDirectory リソース プロバイダーの操作の取得 |
| 承認 | ポリシー キーを取得する |
| 承認 | ポリシー キーを取得する |
| 承認 | テナントのリソース プロパティの取得 |
| 承認 | サポートされているカルチャを取得する |
| 承認 | サポートされている ID プロバイダーを取得する |
| 承認 | サポートされているページ コントラクトを取得する |
| 承認 | テナントの詳細を取得する |
| 承認 | テナント ドメインを取得する |
| 承認 | authenticationEventsPolicy を取得する |
| 承認 | ユーザー属性の取得 |
| 承認 | ユーザー属性の取得 |
| 承認 | ユーザー フローを取得する |
| 承認 | ユーザー フローを取得する |
| 承認 | v1 および v2 アプリケーションを取得する |
| 承認 | v1 アプリケーションを取得する |
| 承認 | v2 アプリケーションを取得する |
| 承認 | テナントを初期化する |
| 承認 | リソースの移動 |
| 承認 | ポリシー キーを復元する |
| 承認 | v2 アプリケーションのアクセス許可の付与を取得する |
| 承認 | v2 アプリケーション サービス プリンシパルを取得する |
| 承認 | API コネクタを更新 |
| 承認 | ID プロバイダーを更新する |
| 承認 | OnAttributeCollectionStartCustomExtension を更新する |
| 承認 | OnAttributeCollectionSubmitCustomExtension を更新する |
| 承認 | OnPageRenderStartCustomExtension を更新する |
| 承認 | B2C ディレクトリ リソースの更新 |
| 承認 | CIAM ディレクトリ リソースを更新する |
| 承認 | ゲストの使用状況リソースを更新する |
| 承認 | 年齢制限の構成を更新する |
| 承認 | 認証フロー ポリシーを更新する |
| 承認 | authenticationEventListener を更新する |
| 承認 | authenticationEventsFlow を更新する |
| 承認 | authenticationEventsPolicy を更新する |
| 承認 | カスタム ID プロバイダーを更新する |
| 承認 | カスタム ポリシーを更新する |
| 承認 | customAuthenticationExtension を更新する |
| 承認 | ID プロバイダーの更新 |
| 承認 | ローカル ID プロバイダーを更新する |
| 承認 | ポリシー キーを更新する |
| 承認 | サブスクリプションの状態の更新 |
| 承認 | テナント メタデータの更新 |
| 承認 | ユーザー属性の更新 |
| 承認 | ユーザー フローを更新する |
| 承認 | 証明書をポリシー キーにアップロードする |
| 承認 | キーをポリシー キーにアップロードする |
| 承認 | シークレットをポリシー キーにアップロードする |
| 承認 | customExtension authenticationConfiguration を検証する |
| 承認 | リソースの移動の検証 |
| 承認 | テナントが B2C であるかどうかの確認 |
| デバイス | 事前に作成されたデバイスを削除する |
| デバイス | デバイスの事前作成 |
| デバイス | デバイスのローカル管理者パスワードの回復 |
| デバイス | デバイスの登録 |
| デバイス | デバイスの登録を解除する |
| デバイス | デバイスのローカル管理者パスワードの更新 |
| ディレクトリ管理 | 年齢制限の構成を取得する |
| ディレクトリ管理 | テナント一覧を取得する |
| ディレクトリ管理 | テナントのリソース プロパティを取得する |
| ディレクトリ管理 | テナントの詳細を取得する |
| ディレクトリ管理 | テナント ドメインを取得する |
| ディレクトリ管理 | テナントを初期化する |
| ディレクトリ管理 | 年齢制限の構成を更新する |
| ディレクトリ管理 | テナント メタデータの更新 |
| ディレクトリ管理 | テナントが B2C であるかどうかの確認 |
| アイデンティティ保護 | 条件付きアクセス ポリシーを評価する |
| アイデンティティ保護 | ユーザーを修復する |
| キーマネジメント | BitLocker キーを追加する |
| キーマネジメント | ポリシー キーを作成する |
| キーマネジメント | BitLocker キーを削除する |
| キーマネジメント | ポリシー キーを削除する |
| キーマネジメント | ポリシー キーからアクティブなキー メタデータを取得する |
| キーマネジメント | ポリシー キーを取得する |
| キーマネジメント | ポリシー キーを取得する |
| キーマネジメント | BitLocker キーを読み取る |
| キーマネジメント | ポリシー キーを復元する |
| キーマネジメント | ポリシー キーを更新する |
| キーマネジメント | キーをポリシー キーにアップロードする |
| キーマネジメント | シークレットをポリシー キーにアップロードする |
| その他 | ワン タイム パスワードを生成する |
| その他 | ワン タイム パスワードを検証する |
| ポリシーマネジメント | authenticationEventListener を作成する |
| ポリシーマネジメント | authenticationEventsFlow を作成する |
| ポリシーマネジメント | customAuthenticationExtension を作成する |
| ポリシーマネジメント | authenticationEventListener を削除する |
| ポリシーマネジメント | authenticationEventsFlow を削除する |
| ポリシーマネジメント | customAuthenticationExtension を削除する |
| ポリシーマネジメント | OnAttributeCollectionStartCustomExtension を取得する |
| ポリシーマネジメント | OnAttributeCollectionSubmitCustomExtension を取得する |
| ポリシーマネジメント | OnPageRenderStartCustomExtension を取得する |
| ポリシーマネジメント | authenticationEventListener を取得する |
| ポリシーマネジメント | authenticationEventListeners を取得する |
| ポリシーマネジメント | authenticationEventsFlow を取得する |
| ポリシーマネジメント | authenticationEventsFlows を取得する |
| ポリシーマネジメント | customAuthenticationExtension を取得する |
| ポリシーマネジメント | customAuthenticationExtensions を取得する |
| ポリシーマネジメント | authenticationEventsPolicy を取得する |
| ポリシーマネジメント | OnAttributeCollectionStartCustomExtension を更新する |
| ポリシーマネジメント | OnAttributeCollectionSubmitCustomExtension を更新する |
| ポリシーマネジメント | OnPageRenderStartCustomExtension を更新する |
| ポリシーマネジメント | authenticationEventListener を更新する |
| ポリシーマネジメント | authenticationEventsFlow を更新する |
| ポリシーマネジメント | authenticationEventsPolicy を更新する |
| ポリシーマネジメント | customAuthenticationExtension を更新する |
| ポリシーマネジメント | customExtension authenticationConfiguration を検証する |
| リソース管理 | リソース名が使用可能かどうかを確認する |
| リソース管理 | API コネクタを作成 |
| リソース管理 | ID プロバイダーを作成する |
| リソース管理 | カスタム ID プロバイダーを作成する |
| リソース管理 | カスタム ポリシーを作成する |
| リソース管理 | B2C ディレクトリ リソースの作成または更新 |
| リソース管理 | B2C ディレクトリ テナントとリソースを作成または更新する |
| リソース管理 | CIAM ディレクトリ テナントとリソースを作成または更新する |
| リソース管理 | ゲストの使用状況リソースを作成または更新する |
| リソース管理 | ローカライズされたリソースを作成または更新する |
| リソース管理 | ポリシー キーを作成する |
| リソース管理 | ユーザー属性の作成 |
| リソース管理 | ユーザー フローの作成 |
| リソース管理 | API コネクタを削除 |
| リソース管理 | 呼び出し元が管理者である B2C テナントを削除する |
| リソース管理 | B2C ディレクトリ リソースを削除する |
| リソース管理 | CIAM ディレクトリ リソースを削除する |
| リソース管理 | ゲストの使用状況リソースを削除します |
| リソース管理 | ID プロバイダーを削除する |
| リソース管理 | カスタム ポリシーを削除する |
| リソース管理 | ローカライズされたリソースを削除する |
| リソース管理 | ポリシー キーを削除する |
| リソース管理 | ユーザー属性の削除 |
| リソース管理 | ユーザー フローを削除する |
| リソース管理 | キーを生成する |
| リソース管理 | API コネクタを取得 |
| リソース管理 | API コネクタを取得 |
| リソース管理 | 呼び出し元が管理者である B2C テナントを取得する |
| リソース管理 | B2C ディレクトリ リソースを取得する |
| リソース管理 | リソース グループ内の B2C ディレクトリ リソースの取得 |
| リソース管理 | サブスクリプション内の B2C ディレクトリ リソースの取得 |
| リソース管理 | CIAM ディレクトリ リソースを取得する |
| リソース管理 | リソース グループ内の CIAM ディレクトリ リソースを取得する |
| リソース管理 | サブスクリプション内の CIAM ディレクトリ リソースを取得する |
| リソース管理 | ゲストの使用状況リソースを取得する |
| リソース管理 | リソース グループのゲストの使用状況ディレクトリ リソースを取得する |
| リソース管理 | サブスクリプションのゲストの使用状況ディレクトリ リソースを取得する |
| リソース管理 | ID プロバイダーを取得する |
| リソース管理 | ID プロバイダーを取得する |
| リソース管理 | ポリシー キーからアクティブなキー メタデータを取得する |
| リソース管理 | 認証フロー ポリシーを取得する |
| リソース管理 | 使用可能な出力要求を取得する |
| リソース管理 | 構成済みのカスタム ID プロバイダーを取得する |
| リソース管理 | 構成済みの ID プロバイダーを取得する |
| リソース管理 | 構成済みのローカル ID プロバイダーを取得する |
| リソース管理 | カスタム ID プロバイダーを取得する |
| リソース管理 | カスタム ポリシーを取得する |
| リソース管理 | カスタム ポリシーを取得する |
| リソース管理 | カスタム ポリシー メタデータを取得する |
| リソース管理 | ID プロバイダーを取得する |
| リソース管理 | ID プロバイダーの種類を取得する |
| リソース管理 | ID プロバイダーを取得する |
| リソース管理 | ローカライズされたリソースを取得する |
| リソース管理 | 非同期操作の操作状態を取得する |
| リソース管理 | Microsoft.AzureActiveDirectory リソース プロバイダーの操作の取得 |
| リソース管理 | ポリシー キーを取得する |
| リソース管理 | ポリシー キーを取得する |
| リソース管理 | サポートされているカルチャを取得する |
| リソース管理 | サポートされている ID プロバイダーを取得する |
| リソース管理 | サポートされているページ コントラクトを取得する |
| リソース管理 | ユーザー属性の取得 |
| リソース管理 | ユーザー属性の取得 |
| リソース管理 | ユーザー フローを取得する |
| リソース管理 | ユーザー フローを取得する |
| リソース管理 | リソースの移動 |
| リソース管理 | API コネクタを更新 |
| リソース管理 | ID プロバイダー |
| リソース管理 | B2C ディレクトリ リソースを更新する |
| リソース管理 | CIAM ディレクトリ リソースを更新する |
| リソース管理 | ゲストの使用状況リソースを更新する |
| リソース管理 | 認証フロー ポリシーを更新する |
| リソース管理 | カスタム ID プロバイダーを更新する |
| リソース管理 | カスタム ポリシーを更新する |
| リソース管理 | ID プロバイダーの更新 |
| リソース管理 | ローカル ID プロバイダーを更新する |
| リソース管理 | ポリシー キーを更新する |
| リソース管理 | サブスクリプションの状態の更新 |
| リソース管理 | ユーザー属性の更新 |
| リソース管理 | ユーザー フローを更新する |
| リソース管理 | 証明書を更新してポリシー キーに反映する |
| リソース管理 | シークレットを更新してポリシー キーに反映する |
| リソース管理 | リソースの移動の検証 |
| ユーザー管理 | Windows Hello for Business の資格情報を追加する |
| ユーザー管理 | パスワードレスの電話によるサインインの資格情報を追加する |
| ユーザー管理 | Windows Hello for Business の資格情報を削除する |
| ユーザー管理 | パスワードレスの電話によるサインインの資格証明を削除する |

### 条件付きアクセス

これらのログを使用して、 [条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-policy-changes-audit-log)に変更が加えられた日時を確認します。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| Policy | AuthenticationContextClassReference を追加する |
| Policy | 条件付きアクセス ポリシーを追加する |
| Policy | ネームド ロケーションを追加する |
| Policy | AuthenticationContextClassReference を削除する |
| Policy | 条件付きアクセス ポリシーを削除する |
| Policy | ネームド ロケーションを削除する |
| Policy | AuthenticationContextClassReference を更新する |
| Policy | 条件付きアクセス ポリシーを更新する |
| Policy | 継続的アクセス評価を更新する |
| Policy | ネームド ロケーションを更新する |
| Policy | セキュリティの既定値群を更新する |

### Core Directory (コア ディレクトリ)

Core Directory サービスでキャプチャされるログは、さまざまなシナリオに対応しています。 サービス プリンシパルとアプリケーションの変更、会社設定の更新、その他多くのディレクトリ関連の詳細がここでキャプチャされます。 このサービスには非常に多くのログが含まれているため、フィルター オプションと日付範囲を使って結果を絞り込んでください。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アドミニストレーションユニット | 管理単位の追加 |
| アドミニストレーションユニット | 管理単位へのメンバーの追加 |
| アドミニストレーションユニット | 制限付き管理の管理単位にメンバーを追加する |
| アドミニストレーションユニット | 管理単位の削除 |
| アドミニストレーションユニット | 管理単位を物理的に削除する |
| アドミニストレーションユニット | 管理単位からのメンバーの削除 |
| アドミニストレーションユニット | 制限付き管理の管理単位からメンバーを削除する |
| アドミニストレーションユニット | 管理単位を復元する |
| アドミニストレーションユニット | 管理単位の更新 |
| 契約 | 契約を追加する |
| 契約 | 契約を削除する |
| 契約 | 契約を物理的に削除する |
| 契約 | 契約を更新する |
| アプリケーション管理 | サービス プリンシパルへのアプリ ロールの割り当ての追加 |
| アプリケーション管理 | [アプリケーションの追加] |
| アプリケーション管理 | 委任されたアクセス許可の付与の追加 |
| アプリケーション管理 | アプリケーションへの所有者の追加 |
| アプリケーション管理 | サービス プリンシパルへの所有者の追加 |
| アプリケーション管理 | アプリケーションにポリシーを追加する |
| アプリケーション管理 | サービス プリンシパルへのポリシーの追加 |
| アプリケーション管理 | サービス プリンシパルの追加 |
| アプリケーション管理 | サービス プリンシパルの資格情報の追加 |
| アプリケーション管理 | 安全なロールアウトを使ってアプリケーションの更新を取り消す |
| アプリケーション管理 | 安全なロールアウト後にアプリケーションの更新を完了する |
| アプリケーション管理 | アプリケーションへの同意 |
| アプリケーション管理 | アプリケーションを削除する |
| アプリケーション管理 | アプリケーションの物理的な削除 |
| アプリケーション管理 | サービス プリンシパルの物理的な削除 |
| アプリケーション管理 | サービス プリンシパルからのアプリ ロールの割り当ての削除 |
| アプリケーション管理 | 委任されたアクセス許可の付与を削除する |
| アプリケーション管理 | アプリケーションからの所有者の削除 |
| アプリケーション管理 | サービス プリンシパルからの所有者の削除 |
| アプリケーション管理 | アプリケーションからポリシーを削除する |
| アプリケーション管理 | サービス プリンシパルからのポリシーの削除 |
| アプリケーション管理 | サービス プリンシパルの削除 |
| アプリケーション管理 | サービス プリンシパルの資格情報の削除 |
| アプリケーション管理 | アプリケーションの復元 |
| アプリケーション管理 | サービス プリンシパルを復元する |
| アプリケーション管理 | 同意を復元する |
| アプリケーション管理 | 確認済み発行者を設定する |
| アプリケーション管理 | 確認済み発行者を設定解除する |
| アプリケーション管理 | アプリケーションを更新する |
| アプリケーション管理 | 安全なロールアウトを使ってアプリケーションを更新する |
| アプリケーション管理 | アプリケーションを更新する - 証明書とシークレットの管理 |
| アプリケーション管理 | 外部シークレットを更新します。 |
| アプリケーション管理 | サービス プリンシパルの更新 |
| 認証 | 監査ログのテスト |
| 承認ポリシー | 承認ポリシーの更新 |
| CertBasedConfiguration (証明書ベースの構成) | CertBasedAuthConfiguration の追加 |
| CertBasedConfiguration (証明書ベースの構成) | CertificationBasedAuthConfiguration の物理的な削除 |
| CertificateAuthorityエンティティ | CertificateAuthorityEntity の作成 |
| CertificateAuthorityエンティティ | CertificateAuthorityEntity の削除 |
| CertificateAuthorityエンティティ | CertificateAuthorityEntity の物理的な削除 |
| CertificateAuthorityエンティティ | CertificateAuthorityEntity の復元 |
| CertificateAuthorityエンティティ | CertificateAuthorityEntity の更新 |
| CertificateBasedAuthConfiguration | CertificateBasedAuthConfiguration の追加 |
| CertificateBasedAuthConfiguration | CertificateBasedAuthConfiguration の削除 |
| CertificateBasedAuthConfiguration | CertificateBasedAuthConfiguration の更新 |
| 会社ブランディング | ブランド テーマの作成 |
| 会社ブランディング | ブランド テーマの削除 |
| 会社ブランディング | ブランド テーマの物理的な削除 |
| 会社ブランディング | ブランド テーマの更新 |
| CompanyBrandingロケール | ブランド テーマのローカライズの作成 |
| CompanyBrandingロケール | ブランド テーマのローカライズの削除 |
| CompanyBrandingロケール | ブランド テーマのローカライズの物理的な削除 |
| CompanyBrandingロケール | ブランドテーマのローカライズの更新 |
| 連絡先 | 連絡先を追加する |
| 連絡先 | 連絡先を削除する |
| 連絡先 | 連絡先を更新する |
| CrossTenantAccessSettings (クロステナントアクセス設定) | クロステナント アクセス設定にドメイン ベース パートナーの追加 |
| CrossTenantAccessSettings (クロステナントアクセス設定) | クロステナント アクセス設定にパートナーを追加する |
| CrossTenantAccessSettings (クロステナントアクセス設定) | クロステナント アクセス設定にドメイン ベース パートナーの削除 |
| CrossTenantAccessSettings (クロステナントアクセス設定) | パートナー固有のクロステナント アクセス設定を削除する |
| CrossTenantAccessSettings (クロステナントアクセス設定) | パートナーのクロステナント アクセス設定をスケーラブル モデルに移行する |
| CrossTenantAccessSettings (クロステナントアクセス設定) | クロステナント アクセスの既定の設定をリセットする |
| CrossTenantAccessSettings (クロステナントアクセス設定) | クロステナント アクセス設定にドメイン ベース パートナーの更新 |
| CrossTenantAccessSettings (クロステナントアクセス設定) | パートナーのクロステナント アクセス設定を更新する |
| CrossTenantAccessSettings (クロステナントアクセス設定) | 会社の既定のクロステナント アクセス設定を更新する |
| CrossTenantIdentitySyncSettings | パートナーのクロステナント ID 同期設定を作成する |
| CrossTenantIdentitySyncSettings | パートナーのクロステナント ID 同期設定を削除する |
| CrossTenantIdentitySyncSettings | パートナーのクロステナント ID 同期設定を更新する |
| DelegatedAdminServiceProviderConstraints (委任された管理者サービスプロバイダの制約) | 許可された割り当て可能な役割の追加 |
| DelegatedAdminServiceProviderConstraints (委任された管理者サービスプロバイダの制約) | 許可された割り当て可能な役割の更新 |
| デバイス | デバイスの追加 |
| デバイス | デバイスへの登録済み所有者の追加 |
| デバイス | デバイスへの登録済みユーザーの追加 |
| デバイス | デバイスの削除 |
| デバイス | デバイスが準拠していなくなった |
| デバイス | デバイスが管理されなくなった |
| デバイス | デバイスの物理的な削除 |
| デバイス | デバイスからの登録済み所有者の削除 |
| デバイス | デバイスからの登録済みユーザーの削除 |
| デバイス | デバイスの復元 |
| デバイス | デバイスの更新 |
| デバイス設定 | デバイス構成の追加 |
| デバイス設定 | デバイスの構成の削除 |
| デバイス設定 | デバイス構成の更新 |
| デバイステンプレート | DeviceTemplate からデバイスを追加する |
| デバイステンプレート | DeviceTemplate の追加 |
| デバイステンプレート | DeviceTemplate に所有者の追加 |
| デバイステンプレート | DeviceTemplate を削除する |
| ディレクトリ管理 | 会社へのパートナーの追加 |
| ディレクトリ管理 | sharedEmailDomainInvitation を追加する |
| ディレクトリ管理 | 未確認ドメインの追加 |
| ディレクトリ管理 | 確認済みドメインの追加 |
| ディレクトリ管理 | 会社の作成 |
| ディレクトリ管理 | 会社の設定を作成します。 |
| ディレクトリ管理 | 会社で許可されるデータの場所を削除する |
| ディレクトリ管理 | 会社の設定を削除します。 |
| ディレクトリ管理 | サブスクリプションを削除する |
| ディレクトリ管理 | ソース テナント サブスクリプションの削除 |
| ディレクトリ管理 | パートナーの降格 |
| ディレクトリ管理 | ディレクトリが削除された |
| ディレクトリ管理 | ディレクトリが完全に削除された |
| ディレクトリ管理 | ディレクトリの削除が予定されました (ライフサイクル) |
| ディレクトリ管理 | ディレクトリの削除が予定されました (UserRequest) |
| ディレクトリ管理 | ドメインのクロスクラウド確認コードを取得する |
| ディレクトリ管理 | ドメインの物理的な削除 |
| ディレクトリ管理 | パートナーへの会社の昇格 |
| ディレクトリ管理 | サブドメインをルート ドメインに昇格させる |
| ディレクトリ管理 | 会社からのパートナーの削除 |
| ディレクトリ管理 | 未確認ドメインの削除 |
| ディレクトリ管理 | 確認済みドメインの削除 |
| ディレクトリ管理 | sharedEmailDomain の追加をスケジュールする |
| ディレクトリ管理 | sharedEmailDomain の削除をスケジュールする |
| ディレクトリ管理 | 会社情報の設定 |
| ディレクトリ管理 | DirSync 機能の設定 |
| ディレクトリ管理 | DirSyncEnabled フラグの設定 |
| ディレクトリ管理 | パートナーシップの設定 |
| ディレクトリ管理 | 誤削除のしきい値の設定 |
| ディレクトリ管理 | 会社で許可されるデータの場所の設定 |
| ディレクトリ管理 | 会社の多国対応機能の有効化の設定 |
| ディレクトリ管理 | テナントでのディレクトリ機能の設定 |
| ディレクトリ管理 | ドメインの認証の設定 |
| ディレクトリ管理 | ドメインのフェデレーションの設定 |
| ディレクトリ管理 | パスワード ポリシーの設定 |
| ディレクトリ管理 | ドメインの論理的な削除 |
| ディレクトリ管理 | ソース テナント サブスクリプションの一時停止 |
| ディレクトリ管理 | ドメインの更新 |
| ディレクトリ管理 | 会社の更新 |
| ディレクトリ管理 | 会社の設定の更新 |
| ディレクトリ管理 | ドメインの更新 |
| ディレクトリ管理 | sharedEmailDomain を更新する |
| ディレクトリ管理 | sharedEmailDomainInvitation を更新する |
| ディレクトリ管理 | ドメインの検証 |
| ディレクトリ管理 | 電子メール検証済みドメインの検証 |
| グループ管理 | グループへのアプリ ロールの割り当ての追加 |
| グループ管理 | グループの追加 |
| グループ管理 | グループへのメンバーの追加 |
| グループ管理 | グループへの所有者の追加 |
| グループ管理 | グループにラベルを割り当てる |
| グループ管理 | グループ設定の作成 |
| グループ管理 | グループの削除 |
| グループ管理 | グループ設定の削除 |
| グループ管理 | ユーザーに対してグループ ベースのライセンスの適用を終了する |
| グループ管理 | アプリケーションにコンテキストに応じた同意を付与する |
| グループ管理 | グループの物理的な削除 |
| グループ管理 | グループからのアプリ ロールの割り当ての削除 |
| グループ管理 | グループから資格のあるメンバーの削除 |
| グループ管理 | グループから資格のある所有者の削除 |
| グループ管理 | グループからラベルを削除する |
| グループ管理 | グループからのメンバーの削除 |
| グループ管理 | グループからの所有者の削除 |
| グループ管理 | グループを復元する |
| グループ管理 | グループ ライセンスの設定 |
| グループ管理 | ユーザーが管理するグループが設定されました。 |
| グループ管理 | ユーザーに対するグループ ベースのライセンスの適用の開始 |
| グループ管理 | グループ ライセンスの再計算のトリガー |
| グループ管理 | グループの更新 |
| グループ管理 | グループ設定の更新 |
| Kerberosドメイン | kerberos ドメインを追加する |
| Kerberosドメイン | kerberos ドメインを削除する |
| Kerberosドメイン | kerberos ドメインを復元する |
| Kerberosドメイン | kerberos ドメインを更新する |
| ラベル | ラベルの追加 |
| ラベル | ラベルを削除する |
| ラベル | ラベルを更新する |
| MicrosoftSupportAccessManagement (英語) | アクセス承認済 |
| MicrosoftSupportAccessManagement (英語) | アクセス削除済 |
| MicrosoftSupportAccessManagement (英語) | 要求が承認されました |
| MicrosoftSupportAccessManagement (英語) | 要求は取り消されました |
| MicrosoftSupportAccessManagement (英語) | 要求が作成されました |
| MicrosoftSupportAccessManagement (英語) | 要求は拒否されました |
| マルチテナントオーグ | MultiTenantOrg の作成 |
| マルチテナントオーグ | MultiTenantOrg のハード削除 |
| マルチテナントオーグ | MultiTenantOrg の更新 |
| MultiTenantOrgIdentitySyncPolicyUpdate(英語) | マルチテナント組織 ID 同期ポリシー テンプレートのリセット |
| MultiTenantOrgIdentitySyncPolicyUpdate(英語) | マルチテナント組織 ID 同期ポリシー テンプレートの更新 |
| マルチテナントOrgPartnerConfigurationTemplate | マルチテナント組織パートナー構成テンプレートのリセット |
| マルチテナントOrgPartnerConfigurationTemplate | マルチテナント組織パートナー構成テンプレートの更新 |
| マルチテナントOrgTenant | MultiTenantOrg テナントの追加 |
| マルチテナントOrgTenant | MultiTenantOrg テナントの削除 |
| マルチテナントOrgTenant | MultiTenantOrg テナントのハード削除 |
| マルチテナントOrgTenant | MultiTenantOrg テナントに参加しているテナント |
| マルチテナントOrgTenant | MultiTenantOrg テナントを更新する |
| 組織単位コンテナ | OrganizationUnit の作成 |
| 組織単位コンテナ | OrganizationUnit の削除 |
| 組織単位コンテナ | OrganizationUnit の更新 |
| 保留中の外部ユーザープロファイル | PendingExternalUserProfile を作成する |
| 保留中の外部ユーザープロファイル | PendingExternalUserProfile を削除する |
| 保留中の外部ユーザープロファイル | PendingExternalUserProfile を物理的に削除する |
| 許可付与ポリシー | アクセス許可付与ポリシーを追加する |
| 許可付与ポリシー | アクセス許可付与ポリシーを削除する |
| 許可付与ポリシー | アクセス許可付与ポリシーを更新する |
| Policy | ポリシーへの所有者の追加 |
| Policy | ポリシーの追加 |
| Policy | ポリシーの削除 |
| Policy | 物理的な削除ポリシー |
| Policy | ポリシーから所有者を削除する |
| Policy | ポリシー資格情報の削除 |
| Policy | ポリシーの復元 |
| Policy | ポリシーの更新 |
| パブリックキーインフラストラクチャ | PublicKeyInfrastructure の作成 |
| パブリックキーインフラストラクチャ | PublicKeyInfrastructure の削除 |
| パブリックキーインフラストラクチャ | PublicKeyInfrastructure の物理的な削除 |
| パブリックキーインフラストラクチャ | PublicKeyInfrastructure の開始 |
| パブリックキーインフラストラクチャ | PublicKeyInfrastructure の復元 |
| パブリックキーインフラストラクチャ | PublicKeyInfrastructure の更新 |
| 役割管理 | EligibleRoleAssignment を RoleDefinition に追加する |
| 役割管理 | ロールへの有資格メンバーの追加 |
| 役割管理 | ロールへのメンバーの追加 |
| 役割管理 | 制限付き管理の管理単位をスコープとするロールにメンバーを追加する |
| 役割管理 | ロール定義へのロール割り当ての追加 |
| 役割管理 | ロール定義の追加 |
| 役割管理 | テンプレートからのロールの追加 |
| 役割管理 | ロールへのスコープを持つメンバーの追加 |
| 役割管理 | ロールの定義の削除 |
| 役割管理 | RoleDefinition から EligibleRoleAssignment を削除する |
| 役割管理 | ロールからの有資格メンバーの削除 |
| 役割管理 | ロールからのメンバーの削除 |
| 役割管理 | 制限付き管理の管理単位をスコープとするロールからメンバーを削除する |
| 役割管理 | ロール定義からのロール割り当ての削除 |
| 役割管理 | ロールからのスコープを持つメンバーの削除 |
| 役割管理 | ロールの更新 |
| 役割管理 | ロール定義の更新 |
| ユーザー管理 | グループへのアプリ ロールの割り当ての追加 |
| ユーザー管理 | ユーザーの追加 |
| ユーザー管理 | ユーザー スポンサーの追加 |
| ユーザー管理 | ユーザー ライセンスの変更 |
| ユーザー管理 | ユーザー パスワードの変更 |
| ユーザー管理 | 管理対象ユーザーへのフェデレーション ユーザーの変換 |
| ユーザー管理 | ユーザーのアプリケーション パスワードの作成 |
| ユーザー管理 | ユーザーのアプリケーション パスワードの削除 |
| ユーザー管理 | ユーザーの削除 |
| ユーザー管理 | 強力な認証を無効にする |
| ユーザー管理 | アカウントの無効化 |
| ユーザー管理 | 強力な認証の有効化 |
| ユーザー管理 | アカウントの有効化 |
| ユーザー管理 | ユーザーの物理的な削除 |
| ユーザー管理 | ユーザーに割り当てられた OrganizationalUnit の削除 |
| ユーザー管理 | ユーザーからのアプリ ロールの割り当ての削除 |
| ユーザー管理 | ユーザー スポンサーの削除 |
| ユーザー管理 | [パスワードのリセット] |
| ユーザー管理 | ユーザーの復元 |
| ユーザー管理 | ユーザー パスワードの強制変更の設定 |
| ユーザー管理 | ユーザー管理の設定 |
| ユーザー管理 | 複製されたユーザーの引き継ぎ |
| ユーザー管理 | ユーザーに割り当てられた OrganizationalUnit の更新 |
| ユーザー管理 | StsRefreshTokenValidFrom タイムスタンプの更新 |
| ユーザー管理 | 外部シークレットを更新します。 |
| ユーザー管理 | ユーザーの更新 |

### デバイス登録サービス

[Microsoft Entra ID と Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)を管理する必要がある場合は、デバイス登録サービスでキャプチャされたログを使用して、デバイスへの変更を確認します。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| デバイス | 事前に作成されたデバイスを削除する |
| デバイス | デバイスの事前作成 |
| デバイス | デバイスのローカル管理者パスワードの回復 |
| デバイス | デバイスの登録 |
| デバイス | デバイスの登録を解除する |
| デバイス | ローカル管理者パスワードを更新する |
| キーマネジメント | BitLocker キーを追加する |
| キーマネジメント | BitLocker キーを削除する |
| キーマネジメント | BitLocker キーを読み取る |
| Policy | デバイス登録ポリシーを設定する |
| ユーザー管理 | パスキーの追加 (デバイス バインド) |
| ユーザー管理 | Windows Hello for Business の資格情報を追加する |
| ユーザー管理 | パスワードレスの電話によるサインインの資格情報を追加する |
| ユーザー管理 | プラットフォーム資格情報の追加 |
| ユーザー管理 | Passkey の削除 (デバイス バインド) |
| ユーザー管理 | Windows Hello for Business の資格情報を削除する |
| ユーザー管理 | パスワードレスの電話によるサインインの資格証明を削除する |
| ユーザー管理 | プラットフォーム資格情報の削除 |

### エンタイトルメント管理

エンタイトルメント管理設定の変更を監視するには、これらのログを使います。 エンタイトルメント管理を使用すると、Microsoft Entra セキュリティ グループのメンバーの割り当て、Microsoft 365 のライセンスの付与、またはアプリケーションへのアクセス権の付与を効率化できます。 アクセス レビュー と ライフサイクル ワークフロー には、個別のログがあります。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| エンタイトルメント管理 | エンタイトルメント管理ロールの割り当てを追加する |
| エンタイトルメント管理 | 管理者がアクセス パッケージにユーザーを直接割り当てる |
| エンタイトルメント管理 | 管理者がユーザー アクセス パッケージの割り当てを直接削除する |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求の承認ステージが完了した |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求を承認する |
| エンタイトルメント管理 | 外部スポンサーとしてユーザーを割り当てる |
| エンタイトルメント管理 | 内部スポンサーとしてユーザーを割り当てる |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求を自動承認する |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求をキャンセルする |
| エンタイトルメント管理 | アクセス パッケージを作成する |
| エンタイトルメント管理 | アクセス パッケージの割り当てポリシーを作成する |
| エンタイトルメント管理 | アクセス パッケージの割り当てユーザー更新要求を作成する |
| エンタイトルメント管理 | アクセス パッケージ カタログを作成する |
| エンタイトルメント管理 | 接続されている組織を追加する |
| エンタイトルメント管理 | カスタム拡張機能を作成する |
| エンタイトルメント管理 | 互換性のないアクセス パッケージを作成する |
| エンタイトルメント管理 | 互換性のないグループを作成する |
| エンタイトルメント管理 | リソース環境を作成する |
| エンタイトルメント管理 | リソースの削除要求を作成する |
| エンタイトルメント管理 | リソース要求を作成する |
| エンタイトルメント管理 | アクセス パッケージを削除する |
| エンタイトルメント管理 | アクセス パッケージの割り当てポリシーを削除する |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求を削除する |
| エンタイトルメント管理 | 削除されたユーザーのアクセス パッケージ割り当てポリシーを削除する |
| エンタイトルメント管理 | アクセス パッケージ カタログを削除する |
| エンタイトルメント管理 | 接続されている組織を削除する |
| エンタイトルメント管理 | カスタム拡張機能を削除する |
| エンタイトルメント管理 | 互換性のないアクセス パッケージを削除する |
| エンタイトルメント管理 | 互換性のないグループを削除する |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求を拒否する |
| エンタイトルメント管理 | エンタイトルメント管理によってユーザーのアクセス パッケージ割り当て要求が作成される |
| エンタイトルメント管理 | エンタイトルメント管理によってユーザーのアクセス パッケージ割り当て要求が削除されます |
| エンタイトルメント管理 | カスタム拡張機能を実行する |
| エンタイトルメント管理 | アクセス パッケージ割り当てを拡張する |
| エンタイトルメント管理 | 失敗したアクセス パッケージの割り当て要求 |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求を満たす |
| エンタイトルメント管理 | アクセス パッケージ リソースの割り当てを満たす |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求を部分的に満たす |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求を満たす準備ができている |
| エンタイトルメント管理 | エンタイトルメント管理ロールの割り当てを削除する |
| エンタイトルメント管理 | アクセス パッケージ リソースの割り当てを削除する |
| エンタイトルメント管理 | 外部スポンサーとしてのユーザーを削除 |
| エンタイトルメント管理 | 内部スポンサーとしてのユーザーを削除 |
| エンタイトルメント管理 | 今後のアクセス パッケージの割り当てをスケジュールする |
| エンタイトルメント管理 | アクセス パッケージの更新 |
| エンタイトルメント管理 | アクセス パッケージの割り当てポリシーを更新する |
| エンタイトルメント管理 | アクセス パッケージの割り当て要求を更新する |
| エンタイトルメント管理 | アクセス パッケージ カタログを更新する |
| エンタイトルメント管理 | アクセス パッケージ カタログ リソースを更新する |
| エンタイトルメント管理 | 接続されている組織を更新する |
| エンタイトルメント管理 | カスタム拡張機能を更新する |
| エンタイトルメント管理 | 承認者による要求の回答を更新する |
| エンタイトルメント管理 | テナント設定を更新する |
| エンタイトルメント管理 | ユーザーがアクセス パッケージの割り当てを要求する |
| エンタイトルメント管理 | ユーザーがサービス プリンシパルの代わりにアクセス パッケージの割り当てを要求する |
| エンタイトルメント管理 | ユーザーがアクセス パッケージ割り当ての拡張を要求する |
| エンタイトルメント管理 | ユーザーがアクセス パッケージ割り当ての削除を要求する |

### グローバルなセキュリティで保護されたアクセス

企業リソースへのネットワーク トラフィックを Microsoft Entra インターネット アクセスか Microsoft Entra プライベート アクセスを利用して取得し、セキュリティで保護している場合、ネットワーク ポリシーが変更されたタイミングを特定する目的でこれらのログが役立ちます。 これらのログには、トラフィック転送ポリシーやリモート ネットワーク (ブランチ オフィスの場所など) の変更が記録されます。 詳細については、「 [グローバルセキュリティで保護されたアクセスとは」](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)を参照してください。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アプリケーション管理 | 証明書の作成 |
| アプリケーション管理 | 証明書の削除 |
| アプリケーション管理 | 証明書の更新 |
| オブジェクト管理 | オフボーディング プロセスが開始しました |
| オブジェクト管理 | オンボード プロセスが開始しました |
| オブジェクト管理 | アダプティブ アクセス ポリシーの更新 |
| オブジェクト管理 | 強化された監査ログの設定の更新 |
| オブジェクト管理 | 転送オプション ポリシーの更新 |
| ポリシーマネジメント | フィルタリング ポリシーの作成 |
| ポリシーマネジメント | フィルタリング ポリシー プロファイルの作成 |
| ポリシーマネジメント | リモート ネットワークの作成 |
| ポリシーマネジメント | セキュリティ プロバイダー ポリシーの作成 |
| ポリシーマネジメント | フィルタリング ポリシーの削除 |
| ポリシーマネジメント | フィルタリング ポリシー プロファイルの削除 |
| ポリシーマネジメント | 転送ポリシーの削除 |
| ポリシーマネジメント | プライベート アクセス ポリシーの削除 |
| ポリシーマネジメント | リモート ネットワークの削除 |
| ポリシーマネジメント | セキュリティ プロバイダー ポリシーの削除 |
| ポリシーマネジメント | フィルタリング ポリシーの更新 |
| ポリシーマネジメント | フィルタリング ポリシー プロファイルの更新 |
| ポリシーマネジメント | フィルタリング プロファイルの更新 |
| ポリシーマネジメント | 転送オプション ポリシーの更新 |
| ポリシーマネジメント | 転送ポリシーの更新 |
| ポリシーマネジメント | 転送プロファイルの更新 |
| ポリシーマネジメント | 転送ルールの更新 |
| ポリシーマネジメント | プライベート アクセス ポリシーの更新 |
| ポリシーマネジメント | リモート ネットワークの更新 |
| ポリシーマネジメント | セキュリティ プロバイダー ポリシーの更新 |
| リソース管理 | セキュリティ プロバイダーの登録の作成 |

### ハイブリッド認証

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| 認証 | 機能ロールアウトにユーザーを追加する |
| 認証 | 機能ロールアウトからユーザーを削除する |

##### Microsoft Entra ID 保護 (ID 保護)

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アイデンティティ保護 | IdentityProtectionPolicy の更新 |
| アイデンティティ保護 | NotificationSettings を更新する |
| その他 | ConfirmAccountが侵害されました |
| その他 | アカウントセーフの確認 |
| その他 | ConfirmCompromised (侵害された確認) |
| その他 | 確認安全 |
| その他 | リスクを無視する |
| その他 | DismissUser (ユーザーを削除) |
| その他 | confirmServicePrincipalが侵害されました |
| その他 | DismissServicePrincipal (サービスプリンシパルの解除) |

### 招待されたユーザー

招待されたユーザーのログを使って、テナントでゲストとして共同作業に招待されたユーザーの状態を管理できます。 これらのログは、外部ユーザーに送信された招待に関する問題のトラブルシューティングに役立ちます。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| ユーザー管理 | 外部ユーザーの削除 |
| ユーザー管理 | 電子メールが送信されず、ユーザーがサブスクリプションを解除した |
| ユーザー管理 | 招待 Email |
| ユーザー管理 | 外部ユーザーの招待 |
| ユーザー管理 | 招待状態がリセットされた外部ユーザーを招待する |
| ユーザー管理 | 内部ユーザーを B2B Collaboration に招待する |
| ユーザー管理 | 外部ユーザーの招待を引き換える |

### ライフサイクル ワークフロー

[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) (プレビュー) は、参加者、ムーバー、および脱退者の ID 関連プロセスを自動化する優れた方法です。そうする必要はありません。 詳細については、「 [ライフサイクル ワークフローの監査」](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-audits)を参照してください。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| その他 | カスタム タスク拡張機能を作成する |
| その他 | カスタム タスク拡張機能を削除する |
| その他 | カスタム タスク拡張機能を更新する |
| タスク管理 | タスクをワークフローに追加する |
| タスク管理 | タスクを無効にする |
| タスク管理 | ［タスクの有効化］ |
| タスク管理 | ワークフローからタスクを削除する |
| タスク管理 | タスクの更新 |
| ワークフロー管理 | 実行条件を追加する |
| ワークフロー管理 | ワークフロー バージョンを追加する |
| ワークフロー管理 | ワークフローを作成する |
| ワークフロー管理 | ワークフローの削除 |
| ワークフロー管理 | ワークフローを無効にする |
| ワークフロー管理 | ワークフロー スケジュールを無効にする |
| ワークフロー管理 | ワークフローを有効にする |
| ワークフロー管理 | ワークフロー スケジュールを有効にする |
| ワークフロー管理 | ワークフローを物理的に削除する |
| ワークフロー管理 | オンデマンドのワークフロー実行が完了した |
| ワークフロー管理 | ワークフローの復元 |
| ワークフロー管理 | ワークフロー実行のスケジュールが完了した |
| ワークフロー管理 | ワークフロー実行のスケジュールが開始された |
| ワークフロー管理 | オンデマンド実行のワークフローを設定する |
| ワークフロー管理 | 実行条件を更新する |
| ワークフロー管理 | テナント設定を更新する |
| ワークフロー管理 | ワークフローを更新する |

### Microsoft Identity Manager (MIM) サービス

[MIM](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016) を使用してビジネス ポリシーとワークフローに基づいて ID とグループのプロビジョニングを自動化している場合、これらの監査ログは、MIM サービスを通じてグループとメンバーに変更が行われたタイミングを追跡するのに役立ちます。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| グループ管理 | グループの追加 |
| グループ管理 | グループへのメンバーの追加 |
| グループ管理 | グループへの所有者の追加 |
| グループ管理 | グループの削除 |
| グループ管理 | グループからのメンバーの削除 |
| グループ管理 | グループからの所有者の削除 |
| グループ管理 | グループの更新 |
| ユーザー管理 | ユーザー パスワードの登録 |
| ユーザー管理 | ユーザー パスワードのリセット |

### モビリティ管理

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| 認証 | ユーザーが異常なログイン イベントを正当として確認しました |
| 認証 | ユーザーが異常なログイン イベントを正当でないと報告した |
| ユーザー管理 | ユーザーが既定のセキュリティ情報を変更した |
| ユーザー管理 | ユーザーがセキュリティ問題を削除した |
| ユーザー管理 | ユーザーがセキュリティ情報を登録した |
| ユーザー管理 | ユーザーがセキュリティ情報の登録を開始した |

### マイアクセス

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アプリケーション管理 | アプリケーション コレクションを作成する |

### マイアプリ

[MyApps](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview) 監査ログを使用して、MyApp ポータルのコレクションにアプリケーションが追加されたタイミングを特定します。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アプリケーション管理 | アプリケーション コレクションを作成する |
| アプリケーション管理 | アプリケーション コレクションを削除する |
| アプリケーション管理 | アプリケーション コレクションを更新する |
| アプリケーション管理 | アプリケーション コレクションの順序を更新する |
| アプリケーション管理 | プレビュー設定を更新する |

### Privileged Identity Management (PIM)

PIM 監査ログにキャプチャされるアクティビティの多くは似ているので、更新、*期限切* *れ*、*永続的*などの詳細を書き留めておきます。 PIM のアクティビティによって、24 時間で多数のログが生成される可能性があるため、フィルターを利用して絞り込んでください。 PIM サービス内の監査機能の詳細については、「PIM での [Microsoft Entra ロールの監査履歴の表示」](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-use-audit-log)を参照してください。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| アプリケーション管理 | メンバーのロールへの追加承認が要求されました (PIM のアクティブ化) |
| アプリケーション管理 | PIM でのメンバーのロールへの追加が完了しました (期限付き) |
| アプリケーション管理 | PIM でのメンバーのロールへの追加が要求されました (期限付き) |
| アプリケーション管理 | 要求を承認する - 直接的なロールの割り当て |
| アプリケーション管理 | PIM アクティブ化要求の有効期限が切れた |
| アプリケーション管理 | PIM ポリシーが削除されました |
| アプリケーション管理 | PIM でのメンバーのロールからの削除が完了しました (期限付き) |
| アプリケーション管理 | 要求の削除 |
| アプリケーション管理 | ロール定義が作成された |
| アプリケーション管理 | PIM でロールの設定を更新する |
| グループ管理 | PIM での資格のあるメンバーのロールへの追加が取り消されました (更新) |
| グループ管理 | PIM での資格のあるメンバーのロールへの追加が取り消されました (期限付き) |
| グループ管理 | PIM での資格のあるメンバーのロールへの追加が完了しました (永続) |
| グループ管理 | PIM での資格のあるメンバーのロールへの追加が完了しました (期限付き) |
| グループ管理 | PIM での資格のあるメンバーのロールへの追加が要求されました (永続) |
| グループ管理 | PIM での資格のあるメンバーのロールへの追加が要求されました (更新) |
| グループ管理 | PIM での資格あるメンバーのロールへの追加が依頼されました (期限付き) |
| グループ管理 | メンバーのロールへの追加承認が要求されました (PIM のアクティブ化) |
| グループ管理 | メンバーのロールへの追加が取り消されました (PIM アクティブ化) |
| グループ管理 | ロールへのメンバーの追加が完了した (PIM アクティブ化) |
| グループ管理 | PIM でのメンバーのロールへの追加が取り消されました (永続) |
| グループ管理 | PIM でのメンバーのロールへの追加が取り消されました (更新) |
| グループ管理 | PIM でのメンバーのロールへの追加が取り消されました (期限付き) |
| グループ管理 | PIM でのメンバーのロールへの追加が完了しました (永続) |
| グループ管理 | PIM でのメンバーのロールへの追加が完了しました (期限付き) |
| グループ管理 | PIM でのロールへのメンバーの追加が要求されました (永続) |
| グループ管理 | PIM でのメンバーのロールへの追加が要求されました (更新) |
| グループ管理 | PIM でのメンバーのロールへの追加が要求されました (期限付き) |
| グループ管理 | ロールへのメンバーの追加要求が承認されました (PIM のアクティブ化) |
| グループ管理 | メンバーのロールへの追加要求が拒否されました (PIM のアクティブ化) |
| グループ管理 | メンバーのロールへの追加が要求されました (PIM アクティブ化) |
| グループ管理 | 要求を取り消す |
| グループ管理 | ロールの削除要求を取り消す |
| グループ管理 | ロールの更新の要求を取り消す |
| グループ管理 | リソースが PIM からオフボードされた |
| グループ管理 | リソースが PIM にオンボードされた |
| グループ管理 | PIM アクティブ化要求の有効期限が切れた |
| グループ管理 | PIM ポリシーが削除されました |
| グループ管理 | 要求を処理する |
| グループ管理 | ロールの削除要求を処理する |
| グループ管理 | PIM での資格のあるメンバーのロールからの削除が完了しました (永続) |
| グループ管理 | PIM での資格のあるメンバーのロールからの削除が完了しました (期限付き) |
| グループ管理 | PIM での資格のあるメンバーのロールからの削除が要求されました (永続) |
| グループ管理 | PIM での資格のあるメンバーのロールからの削除が要求されました (期限付き) |
| グループ管理 | メンバーをロールから削除する (PIM のアクティブ化が期限切れ) |
| グループ管理 | メンバーのロールからの削除が完了した (PIM の非アクティブ化) |
| グループ管理 | PIM でのメンバーのロールからの削除が完了しました (永続) |
| グループ管理 | PIM でのメンバーのロールからの削除が完了しました (期限付き) |
| グループ管理 | PIM でのロールからのメンバーの削除が要求されました (永続) |
| グループ管理 | PIM でのメンバーのロールからの削除が要求されました (期限付き) |
| グループ管理 | メンバーのロールからの削除が要求されました (PIM の非アクティブ化) |
| グループ管理 | 永続的で直接的なロールの割り当てを削除する |
| グループ管理 | 永続的な資格のあるロールの割り当てを削除する |
| グループ管理 | 要求の削除 |
| グループ管理 | リソースが更新されました |
| グループ管理 | PIM での有資格メンバーのロールからの復元が完了しました |
| グループ管理 | ロールからのメンバーの復元 |
| グループ管理 | PIM でのメンバーのロールからの復元が完了しました |
| グループ管理 | 永続的で直接的なロールの割り当ての復元 |
| グループ管理 | PIM での資格のあるメンバーの更新が取り消されました (延長) |
| グループ管理 | PIM での資格のあるメンバーの更新が要求されました (延長) |
| グループ管理 | PIM でのメンバーの更新が管理者によって承認されました (延長/更新) |
| グループ管理 | PIM でのメンバーの更新が取り消されました (延長) |
| グループ管理 | PIM でのメンバーの更新が管理者によって拒否されました (延長/更新) |
| グループ管理 | PIM でのメンバーの更新が要求されました (延長) |
| グループ管理 | PIM でロールの設定を更新する |
| リソース管理 | PIM での資格のあるメンバーのロールへの追加が取り消されました (永続) |
| リソース管理 | PIM での資格のあるメンバーのロールへの追加が取り消されました (更新) |
| リソース管理 | PIM での資格のあるメンバーのロールへの追加が取り消されました (期限付き) |
| リソース管理 | PIM での資格のあるメンバーのロールへの追加が完了しました (永続) |
| リソース管理 | PIM での資格のあるメンバーのロールへの追加が完了しました (期限付き) |
| リソース管理 | PIM での資格のあるメンバーのロールへの追加が要求されました (永続) |
| リソース管理 | PIM での資格のあるメンバーのロールへの追加が要求されました (更新) |
| リソース管理 | PIM での資格あるメンバーのロールへの追加が依頼されました (期限付き) |
| リソース管理 | メンバーのロールへの追加承認が要求されました (PIM のアクティブ化) |
| リソース管理 | メンバーのロールへの追加が取り消されました (PIM アクティブ化) |
| リソース管理 | ロールへのメンバーの追加が完了した (PIM アクティブ化) |
| リソース管理 | PIM でのメンバーのロールへの追加が取り消されました (更新) |
| リソース管理 | PIM でのメンバーのロールへの追加が取り消されました (期限付き) |
| リソース管理 | PIM でのメンバーのロールへの追加が完了しました (永続) |
| リソース管理 | PIM でのメンバーのロールへの追加が完了しました (期限付き) |
| リソース管理 | PIM でのロールへのメンバーの追加が要求されました (永続) |
| リソース管理 | PIM でのメンバーのロールへの追加が要求されました (更新) |
| リソース管理 | PIM でのメンバーのロールへの追加が要求されました (期限付き) |
| リソース管理 | PIM の範囲外のロールにメンバーを追加する (永続) |
| リソース管理 | ロールへのメンバーの追加要求が承認されました (PIM のアクティブ化) |
| リソース管理 | メンバーのロールへの追加要求が拒否されました (PIM のアクティブ化) |
| リソース管理 | メンバーのロールへの追加が要求されました (PIM アクティブ化) |
| リソース管理 | 要求を取り消す |
| リソース管理 | ロールの削除要求を取り消す |
| リソース管理 | ロールの更新の要求を取り消す |
| リソース管理 | PIM アラートを非アクティブ化する |
| リソース管理 | PIM アラートを無効にする |
| リソース管理 | PIM アラートを有効にする |
| リソース管理 | リソースが PIM からオフボードされた |
| リソース管理 | リソースが PIM からオンボードされた |
| リソース管理 | PIM アクティブ化要求の有効期限が切れた |
| リソース管理 | PIM ポリシーが削除されました |
| リソース管理 | 要求を処理する |
| リソース管理 | ロールの削除要求を処理する |
| リソース管理 | ロールの更新要求を処理する |
| リソース管理 | PIM での資格のあるメンバーのロールからの削除が完了しました (永続) |
| リソース管理 | PIM での資格のあるメンバーのロールからの削除が完了しました (期限付き) |
| リソース管理 | PIM での資格のあるメンバーのロールからの削除が要求されました (永続) |
| リソース管理 | PIM での資格のあるメンバーのロールからの削除が要求されました (期限付き) |
| リソース管理 | メンバーをロールから削除する (PIM のアクティブ化が期限切れ) |
| リソース管理 | メンバーのロールからの削除が完了した (PIM の非アクティブ化) |
| リソース管理 | PIM でのメンバーのロールからの削除が完了しました (永続) |
| リソース管理 | PIM でのメンバーのロールからの削除が完了しました (期限付き) |
| リソース管理 | PIM でのロールからのメンバーの削除が要求されました (永続) |
| リソース管理 | PIM でのメンバーのロールからの削除が要求されました (期限付き) |
| リソース管理 | メンバーのロールからの削除が要求されました (PIM の非アクティブ化) |
| リソース管理 | 永続的で直接的なロールの割り当てを削除する |
| リソース管理 | 永続的な資格のあるロールの割り当てを削除する |
| リソース管理 | 要求の削除 |
| リソース管理 | PIM アラートを解決する |
| リソース管理 | リソースが更新されました |
| リソース管理 | PIM での有資格メンバーのロールからの復元が完了しました |
| リソース管理 | ロールからのメンバーの復元 |
| リソース管理 | PIM でのメンバーのロールからの復元が完了しました |
| リソース管理 | 永続的で直接的なロールの割り当ての復元 |
| リソース管理 | 永続的で資格のあるロールの割り当ての復元 |
| リソース管理 | テナントが PIM からオフボードされた |
| リソース管理 | PIM アラートがトリガーされた |
| リソース管理 | PIM での資格のあるメンバーの更新が取り消されました (延長) |
| リソース管理 | PIM での資格のあるメンバーの更新が要求されました (延長) |
| リソース管理 | PIM でのメンバーの更新が管理者によって承認されました (延長/更新) |
| リソース管理 | PIM でのメンバーの更新が取り消されました (延長) |
| リソース管理 | PIM でのメンバーの更新が管理者によって拒否されました (延長/更新) |
| リソース管理 | PIM でのメンバーの更新が要求されました (延長) |
| リソース管理 | PIM でロールの設定を更新する |
| 役割管理 | PIM での資格のあるメンバーのロールへの追加が取り消されました (永続) |
| 役割管理 | PIM での資格のあるメンバーのロールへの追加が取り消されました (更新) |
| 役割管理 | PIM での資格のあるメンバーのロールへの追加が取り消されました (期限付き) |
| 役割管理 | PIM での資格のあるメンバーのロールへの追加が完了しました (永続) |
| 役割管理 | PIM での資格のあるメンバーのロールへの追加が完了しました (期限付き) |
| 役割管理 | PIM での資格のあるメンバーのロールへの追加が要求されました (永続) |
| 役割管理 | PIM での資格のあるメンバーのロールへの追加が要求されました (更新) |
| 役割管理 | PIM での資格あるメンバーのロールへの追加が依頼されました (期限付き) |
| 役割管理 | メンバーのロールへの追加承認が要求されました (PIM のアクティブ化) |
| 役割管理 | メンバーのロールへの追加が取り消されました (PIM アクティブ化) |
| 役割管理 | ロールへのメンバーの追加が完了した (PIM アクティブ化) |
| 役割管理 | PIM でのメンバーのロールへの追加が取り消されました (更新) |
| 役割管理 | PIM でのメンバーのロールへの追加が取り消されました (期限付き) |
| 役割管理 | PIM でのメンバーのロールへの追加が完了しました (永続) |
| 役割管理 | PIM でのメンバーのロールへの追加が完了しました (期限付き) |
| 役割管理 | PIM でのロールへのメンバーの追加が要求されました (永続) |
| 役割管理 | PIM でのメンバーのロールへの追加が要求されました (更新) |
| 役割管理 | PIM でのメンバーのロールへの追加が要求されました (期限付き) |
| 役割管理 | PIM の範囲外のロールにメンバーを追加する (永続) |
| 役割管理 | ロールへのメンバーの追加要求が承認されました (PIM のアクティブ化) |
| 役割管理 | メンバーのロールへの追加要求が拒否されました (PIM のアクティブ化) |
| 役割管理 | メンバーのロールへの追加が要求されました (PIM アクティブ化) |
| 役割管理 | ロールの削除要求を取り消す |
| 役割管理 | ロールの更新の要求を取り消す |
| 役割管理 | PIM アラートを非アクティブ化する |
| 役割管理 | PIM アラートを無効にする |
| 役割管理 | PIM アラートを有効にする |
| 役割管理 | リソースが PIM からオフボードされた |
| 役割管理 | リソースが PIM からオンボードされた |
| 役割管理 | PIM アクティブ化要求の有効期限が切れた |
| 役割管理 | PIM ポリシーが削除されました |
| 役割管理 | 要求を処理する |
| 役割管理 | ロールの削除要求を処理する |
| 役割管理 | ロールの更新要求を処理する |
| 役割管理 | PIM アラートを更新する |
| 役割管理 | PIM での資格のあるメンバーのロールからの削除が完了しました (永続) |
| 役割管理 | PIM での資格のあるメンバーのロールからの削除が完了しました (期限付き) |
| 役割管理 | PIM での資格のあるメンバーのロールからの削除が要求されました (永続) |
| 役割管理 | PIM での資格のあるメンバーのロールからの削除が要求されました (期限付き) |
| 役割管理 | メンバーをロールから削除する (PIM のアクティブ化が期限切れ) |
| 役割管理 | メンバーのロールからの削除が完了した (PIM の非アクティブ化) |
| 役割管理 | PIM でのメンバーのロールからの削除が完了しました (永続) |
| 役割管理 | PIM でのメンバーのロールからの削除が完了しました (期限付き) |
| 役割管理 | PIM でのロールからのメンバーの削除が要求されました (永続) |
| 役割管理 | PIM でのメンバーのロールからの削除が要求されました (期限付き) |
| 役割管理 | メンバーのロールからの削除が要求されました (PIM の非アクティブ化) |
| 役割管理 | 永続的で直接的なロールの割り当てを削除する |
| 役割管理 | 永続的な資格のあるロールの割り当てを削除する |
| 役割管理 | 要求の削除 |
| 役割管理 | PIM アラートを解決する |
| 役割管理 | PIM での有資格メンバーのロールからの復元が完了しました |
| 役割管理 | ロールからのメンバーの復元 |
| 役割管理 | PIM でのメンバーのロールからの復元が完了しました |
| 役割管理 | 永続的で直接的なロールの割り当ての復元 |
| 役割管理 | 永続的で資格のあるロールの割り当ての復元 |
| 役割管理 | テナントが PIM からオフボードされた |
| 役割管理 | PIM アラートがトリガーされた |
| 役割管理 | PIM アラート設定を更新する |
| 役割管理 | PIM での資格のあるメンバーの更新が取り消されました (延長) |
| 役割管理 | PIM での資格のあるメンバーの更新が要求されました (延長) |
| 役割管理 | PIM でのメンバーの更新が管理者によって承認されました (延長/更新) |
| 役割管理 | PIM でのメンバーの更新が取り消されました (延長) |
| 役割管理 | PIM でのメンバーの更新が管理者によって拒否されました (延長/更新) |
| 役割管理 | PIM でのメンバーの更新が要求されました (延長) |
| 役割管理 | PIM でロールの設定を更新する |

### セルフサービスのグループ管理

テナント内のユーザーは、自分のグループ メンバーシップの多くの側面を自分で管理できます。 セルフサービス グループ管理ログを使って、これらのシナリオに関する問題をトラブルシューティングできます。

このグループのアクティビティの多くは、ユーザーのアクティビティに関連するバックグラウンド プロセスに関連付けられています。 たとえば、ユーザーが MyApps または MyGroups ポータルにアクセスすると、ログに複数の `Features_GetFeaturesAsync` のインスタンスが表示される場合があります。 このアクティビティでは、ユーザーが変更を加えたかどうかは示されません。 `GroupsODataV4_Get` などの他のアクティビティは、多くの場合、同様のユーザー アクションのグループで発生します。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| グループ管理 | ApprovalNotification\_Create |
| グループ管理 | Approval\_Act |
| グループ管理 | Approval\_Get |
| グループ管理 | Approval\_GetAll |
| グループ管理 | Approvals\_Post |
| グループ管理 | 保留になっているグループへの参加要求の承認 |
| グループ管理 | 保留になっているグループへの参加要求の取り消し |
| グループ管理 | ライフサイクル管理ポリシーの作成 |
| グループ管理 | 保留になっているグループへの参加要求の削除 |
| グループ管理 | ライフサイクル管理ポリシーを削除する |
| グループ管理 | Device\_Create |
| グループ管理 | Device\_Delete |
| グループ管理 | Device\_Get |
| グループ管理 | Device\_GetAll |
| グループ管理 | Features\_GetFeaturesAsync |
| グループ管理 | Features\_IsFeatureEnabledAsync |
| グループ管理 | Features\_UpdateFeaturesAsync |
| グループ管理 | GroupLifecyclePolicies\_Get |
| グループ管理 | GroupLifecyclePolicies\_addGroup |
| グループ管理 | GroupLifecyclePolicies\_removeGroup |
| グループ管理 | Group\_AddMember |
| グループ管理 | Group\_AddOwner |
| グループ管理 | Group\_BatchValidateDynamicMembership |
| グループ管理 | Group\_Create |
| グループ管理 | Group\_Delete |
| グループ管理 | Group\_Get |
| グループ管理 | Group\_GetAll |
| グループ管理 | Group\_GetDynamicGroupProperties |
| グループ管理 | Group\_GetDynamicMembershipDeviceAttributes |
| グループ管理 | Group\_GetDynamicMembershipOperators |
| グループ管理 | Group\_GetDynamicMembershipUserBaseAttributes |
| グループ管理 | Group\_GetExpiryNotificationDate |
| グループ管理 | Group\_GetMembers |
| グループ管理 | Group\_GetOwners |
| グループ管理 | Group\_RemoveMember |
| グループ管理 | Group\_RemoveOwner |
| グループ管理 | Group\_Restore |
| グループ管理 | Group\_Update |
| グループ管理 | Group\_ValidateDynamicMembership |
| グループ管理 | GroupsODataV4\_Get |
| グループ管理 | GroupsODataV4\_GetgroupLifecyclePolicies |
| グループ管理 | GroupsODataV4\_evaluateDynamicMembership |
| グループ管理 | Groups\_CreateLink |
| グループ管理 | Groups\_Get |
| グループ管理 | LcmPolicy\_Get |
| グループ管理 | LcmPolicy\_RenewGroup |
| グループ管理 | 保留になっているグループへの参加要求の拒否 |
| グループ管理 | グループの更新 |
| グループ管理 | グループへの参加要求 |
| グループ管理 | ダイナミック グループのプロパティの設定 |
| グループ管理 | Settings\_GetSettingsAsync |
| グループ管理 | ライフサイクル管理ポリシーの更新 |
| グループ管理 | User\_Create |
| グループ管理 | User\_Delete |
| グループ管理 | User\_Get |
| グループ管理 | User\_GetAll |
| グループ管理 | User\_GetMemberOf |
| グループ管理 | User\_GetOwnedObjects |
| その他 | ApprovalNotification\_Create |
| ユーザー管理 | ConvergedUXV2 機能値が更新された |
| ユーザー管理 | MyApps 機能値が更新された |
| ユーザー管理 | MyStaff 機能値の更新 |
| ユーザー管理 | SSPRConvergence 機能値が更新された |
| ユーザー管理 | SignInReports 機能値が更新された |

### セルフサービスによるパスワード管理

セルフサービス パスワード管理ログには、ユーザーと管理者がパスワードに加えた変更や、ユーザーがセルフサービス パスワード リセットに登録したときの分析情報が記録されています。

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| ディレクトリ管理 | ディレクトリのパスワード ライトバックの無効化 |
| ディレクトリ管理 | ディレクトリのパスワード ライトバックの有効化 |
| ユーザー管理 | セルフサービス パスワード リセットのブロック |
| ユーザー管理 | パスワードの変更 (セルフサービス) |
| ユーザー管理 | パスワードのリセット (管理者) |
| ユーザー管理 | パスワードのリセット (セルフサービス) |
| ユーザー管理 | セルフサービス パスワード リセットのセキュリティ情報が保存された |
| ユーザー管理 | セルフサービス パスワード リセット フロー アクティビティの進行状況 |
| ユーザー管理 | ユーザー アカウントのロック解除 (セルフサービス) |

### 使用条件

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| Policy | 使用条件の同意 |
| Policy | 使用条件の作成 |
| Policy | 使用条件の拒否 |
| Policy | 同意の削除 |
| Policy | 使用条件の削除 |
| Policy | 使用条件の編集 |
| Policy | 使用条件の公開 |

### 検証済み ID

| 監査のカテゴリ | アクティビティ |
| --- | --- |
| リソース管理 | 権限の作成 |
| リソース管理 | 承認ポリシーの作成 |
| リソース管理 | 契約の作成 |
| リソース管理 | 発行ポリシーの作成 |
| リソース管理 | 発行ポリシーの削除 |
| リソース管理 | POST /authorities/:issuerId/didInfo/signingKeys/rotate 要求の処理 |
| リソース管理 | POST /authorities/:issuerId/didInfo/signingKeys/synchronizeWithDidDocument 要求の処理 |
| リソース管理 | 資格情報の取り消し |
| リソース管理 | 署名キーのローテーション |
| リソース管理 | テナントのオンボード |
| リソース管理 | テナントのオプトアウト |
| リソース管理 | MyAccount 設定の更新 |
| リソース管理 | 更新機関 |
| リソース管理 | コントラクトを更新します |
| リソース管理 | 発行ポリシーの更新 |
| リソース管理 | リンクされたドメインの更新 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/reference-log-latency"} -->
## Microsoft Entra ID のログ待機時間 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-log-latency
- Service: entra-id / monitoring-health
- Article date: 2024-09-27
- Summary: Microsoft Entra ID でのサインインと監査ログの待機時間を促進する要因のリファレンス情報

待機時間は、Microsoft Entra ID レポート データが監視ログと正常性ログに表示されるまでにかかる時間です。 この記事では、待機時間に影響する可能性がある要因について説明します。

### 待機時間と初回セットアップ

Microsoft Entra Premium P1 または P2 の無料バージョンからアップグレードする場合、すべての Premium レポート機能でデータが表示されるまでに、テナントのアップグレードから約 24 時間の遅延が予想されます。 Premium レポート機能の多くは、アップグレード後、この 24 時間が経過した後にのみデータの保持を開始します。

新しいストレージ アカウントまたはセキュリティ情報およびイベント管理 (SIEM) ツールを設定する場合は、レポート データがこれらのツールに表示されるまでに 24 時間の遅延が発生することも予想されます。

Azure Monitor ログを使用した分析のためにアクティビティ ログを Log Analytics ワークスペースにルーティングする場合、ワークスペースにログが表示されるまでに最大 3 日間の遅延が予想されます。

### 待機時間の要因を報告する

多くの要因が、レポート データの待機時間に影響します。 データの種類、データの量、レポート ツールが構築されているインフラストラクチャは、すべて待機時間に影響を与える可能性があります。 基になるインフラストラクチャに遅延がある場合、Microsoft Entra レポートでデータの報告に遅延が発生する可能性があります。

ログ待機時間の重要な要因の 1 つは、データがソース イベントから Microsoft Entra 管理センターのログに移動するパスです。 ログ データは、ログに表示される前に、次のシステムを通過します。

1. お客様は、ID およびアクセス サービスとして Microsoft Entra ID を使用するサービスにサインインします。
2. ログ プロセッサは、イベント メタデータを読み取り、Azure ストレージ キューに発行します。
3. イベント メタデータが処理され、成功または失敗が確認されます。
4. 成功したイベントは、Azure Monitor や Microsoft Graph などのパートナー サービスに発行されます。
5. IT 管理者は、Microsoft Entra 管理センターまたは選択した SIEM ツールでデータを表示します。

このプロセスには、他にも多くの手順が反映されていません。 これらの要約された手順を使用しても、待機時間をシステムに導入する方法を簡単に確認できます。

### 最後のサインイン

ユーザーの最後のサインインは、ログの待機時間に関連する最も一般的な質問の 1 つです。 この情報は、Microsoft Graph の `signInActivity` プロパティによって提供されます。 signInActivity プロパティは、ユーザーの最後の対話型サインインと非対話型サインイン  を試みます。 このプロパティの更新には最大 24 時間かかる場合があります。 詳細については、「signInActivity リソースの種類 [を](https://learn.microsoft.com/ja-jp/graph/api/resources/signinactivity?view=graph-rest-beta&preserve-view=true)する」を参照してください。

サインイン ログへのアクセスを許可する microsoft Entra ロール  を使用して、この詳細を表示する必要があります。これは、いくつかの場所にあります。

- ユーザーのプロファイルの [**個人用フィード**] セクションの [**サインイン**] タイル。

    [Image: [マイ フィード] セクションの [サインイン] タイルが強調表示されているユーザーの詳細ページのスクリーンショット。]
- [**最後の対話型サインイン]** と **[ユーザー] の一覧の [最後の非対話型サインイン]** 列。

    [Image: 最後の対話型サインイン列が強調表示されているすべてのユーザーリストのスクリーンショット。]
- 特定のユーザーに対してフィルター処理されたサインイン ログ。

    [Image: 特定のユーザーにフィルター処理されたサインイン ログのスクリーンショット。]

ユーザー プロファイルの [**サインイン**] タイルに表示される最後のサインインの詳細と、[**ユーザー] の一覧の [最後の対話型サインイン** と **最後の対話型サインイン**] 列は、リアルタイム  されません。

ユーザーの最新のサインイン日時を表示する必要がある場合は、サインイン ログに移動し、そのユーザーのフィルター処理を行います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/reference-powershell-reporting"} -->
## Microsoft Graph PowerShell の監視と正常性のコマンドレット - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-powershell-reporting
- Service: entra-id / monitoring-health
- Article date: 2024-02-06
- Summary: Microsoft Entra の監視と正常性に関する Microsoft Graph PowerShell コマンドレットのリファレンス情報。

Microsoft Entra の監視と正常性を使用すると、ディレクトリ内のすべての書き込み操作 (監査ログ) と認証データ (サインイン ログ) に関するアクティビティの詳細を取得できます。 この情報は Microsoft Graph API を使用して入手できますが、Id の監視と正常性のために Microsoft Graph PowerShell コマンドレットを使用して同じデータを取得できるようになりました。

この記事では、監査ログとサインイン ログに使用する Microsoft Graph PowerShell コマンドレットの概要について説明します。 [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started)の使用を開始します。

### 監査ログ

[監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)、Microsoft Entra ID 内のさまざまな機能によって行われたすべての変更について、ログを通じて追跡可能性を提供します。 監査ログの例には、ユーザー、アプリ、グループ、ロール、ポリシーの追加や削除など、Microsoft Entra ID 内のリソースに加えられた変更が含まれます。

監査ログには、`Get-MgAuditLogDirectoryAudit` コマンドレットを使用してアクセスできます。

| シナリオ | PowerShell コマンド |
| --- | --- |
| アプリケーションの表示名 | `Get-MgAuditLogDirectoryAudit -Filter "initiatedBy/app/displayName eq 'Azure AD Cloud Sync'"` |
| カテゴリ | `Get-MgAuditLogDirectoryAudit -Filter "category eq 'ApplicationManagement'"` |
| アクティビティの日付と時刻 | `Get-MgAuditLogDirectoryAudit -Filter "activityDateTime gt 2019-04-18"` |
| 上記のすべて | `Get-MgAuditLogDirectoryAudit -Filter "initiatedBy/app/displayName eq 'Azure AD Cloud Sync' and category eq 'ApplicationManagement' and activityDateTime gt 2019-04-18"` |

### サインイン ログ

[サインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins) ログには、マネージド アプリケーションとユーザー サインイン アクティビティの使用状況に関する情報が表示されます。

`Get-MgAuditLogSignIn` コマンドレットを使用して、サインイン ログにアクセスできます。 その他のシナリオについては、次の表を参照してください。

| シナリオ | Microsoft Graph PowerShell コマンド |
| --- | --- |
| ユーザー表示名 | `Get-MgAuditLogSignIn -Filter "userDisplayName eq 'Timothy Perkins'"` |
| 日付時刻の作成 | `Get-MgAuditLogSignIn -Filter "createdDateTime gt 2023-04-18T17:30:00.0Z"` (4/18 pm 5:30 以降) |
| 地位 | `Get-MgAuditLogSignIn -Filter "status/errorCode eq 50105"` |
| アプリケーションの表示名 | `Get-MgAuditLogSignIn -Filter "appDisplayName eq 'StoreFrontStudio [wsfed enabled]'"` |
| 上記のすべて | `Get-MgAuditLogSignIn -Filter "userDisplayName eq 'Timothy Perkins' and status/errorCode ne 0 and appDisplayName eq 'StoreFrontStudio [wsfed enabled]'"` |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/reference-reports-data-retention"} -->
## データ保持のMicrosoft Entra - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention
- Service: entra-id / monitoring-health
- Article date: 2026-01-06
- Summary: Microsoft Entra監査、サインイン、プロビジョニング ログのデータ保持ポリシーについて説明します。

この記事では、Microsoft Entra IDのさまざまなアクティビティ レポートのデータ保持ポリシーについて説明します。

### データMicrosoft Entra ID収集を開始するタイミング

| Microsoft Entra エディション | コレクションの開始 |
| --- | --- |
| Microsoft Entra ID P1 Microsoft Entra ID P2 Microsoft Entra Workload ID Premium | サブスクリプションにサインアップする場合 |
| Microsoft Entra ID無料 | 初めて Microsoft Entra IDレポート API を使用します |

無料ライセンスのアクティビティ データが既にある場合は、アップグレード時にすぐに確認できます。 データがない場合、Premium ライセンスにアップグレードした後、レポートにデータが表示されるまでに最大 3 日かかります。

- セキュリティシグナルの場合、**Identity Protection Center**を使用することを選択すると、収集プロセスが開始されます。
- Microsoft Graphアクティビティ ログの場合、[log カテゴリが診断設定](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs#send-logs-to-azure-monitor)で有効になると、収集プロセスが開始されます。

### データMicrosoft Entra ID格納される期間はどのくらいですか?

Microsoft Entra内のログ ストレージは、レポートの種類とライセンスの種類によって異なります。 前の表で説明した既定の保持期間を超えて監査およびサインイン アクティビティ データを保持するには、Azure Monitorを使用してAzureストレージ アカウントにルーティングします。 詳細については、「[Azure ストレージ アカウントへの Microsoft Entraログの整理](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-archive-logs-to-storage-account)を参照してください。

手記

Microsoft Entra ID監査ログとサインイン ログは、Microsoft 365統合監査ログ (UAL) とは別です。 UAL リテンション期間はMicrosoft Purview監査によって管理され、Microsoft Entra IDライセンスの変更の影響を受けません。

#### アクティビティ レポート

| レポート | Microsoft Entra ID無料 | Microsoft Entra ID P1 | Microsoft Entra ID P2 |
| --- | --- | --- | --- |
| 監査ログ | 七日 | 30 日 | 30 日 |
| サインイン | 七日 | 30 日 | 30 日 |
| 多要素認証の使用をMicrosoft Entraする | 30 日 | 30 日 | 30 日 |
| Microsoft Graph アクティビティ ログ\* | NA | ストレージまたは分析ツールと統合する必要がある | ストレージまたは分析ツールと統合する必要がある |

\*Microsoft Graphアクティビティ ログは、Microsoft Entra ID P1 および P2 ライセンスでのみ使用できます。 データは、ストレージ アカウントにアーカイブされるか、分析ツールと統合されていない限り保持されません。

#### セキュリティ シグナル

| レポート | Microsoft Entra ID無料 | Microsoft Entra ID P1 | Microsoft Entra ID P2 |
| --- | --- | --- | --- |
| 危険なユーザー | 制限なし | 制限なし | 制限なし |
| 危険なサインイン | 7 日間 | 30 日 | 90 日 |

手記

Microsoft 365 E5、Office 365 E5、Microsoft Purview Suite、または E5 電子情報開示および監査アドオン ライセンスを持つ組織は、Microsoft Purview Audit (Premium) を使用して、既定の期間を超えて Microsoft Entra ID 監査ログを保持することもできます。Azure Storage にログをエクスポートする代わりに使用できます。 詳細については、「 [Microsoft Purview を使用した監査ログ保持ポリシーの管理](https://learn.microsoft.com/ja-jp/purview/audit-log-retention-policies)」を参照してください。

手記

リスクの高いユーザーとワークロード ID は、リスクが修復されるまで削除されません。

#### ログのMicrosoft Entra External ID

[Microsoft Entra External ID Basic プラン](https://azure-int.microsoft.com/pricing/details/microsoft-entra-external-id/)では、ログは 7 日間保持されます。 詳細については、「 [従業員と外部テナントでサポートされる機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers#activity-logs-and-reports)」を参照してください。 ログを長期間保持するには、外部テナントで [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor) を使用します。

### Premium ライセンスを取得した後、先月のデータを表示できますか?

はありません。できません。 Azureは、無料版の最大 7 日間のアクティビティ データを格納します。 無料版から Premium バージョンに切り替えると、最大 7 日間のデータしか表示されません。

手記

ログ保有期間の変更はさかのぼって行われません。 Microsoft Entra ID Free から P1 または P2 にアップグレードする場合、無料保有期間内 (最大 7 日間) のデータのみが使用できます。 既に期限切れになっているデータは、以前にアーカイブされていない限り回復できません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/reference-service-principal-table"} -->
## ファースト パーティの App Service プリンシパル参照テーブル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-service-principal-table
- Service: entra-id / monitoring-health
- Article date: 2025-05-06
- Summary: アプリケーション ID をアプリケーションにマップし、サインイン ログからサービス プリンシパルの使用状況をマップする参照テーブル。

Microsoft サービス プリンシパルのサインイン ログは、テナント内の Microsoft サービスのサービス間認証イベントをキャプチャします。 セキュリティ調査には必要ありませんが、この情報は、サービスが相互にやり取りする方法を理解するのに役立ちます。

### ログへのアクセス方法

これらのログは、Microsoft Entra で診断設定を構成して、任意のエンドポイントにログをルーティングすることによってのみ使用できます。 このプロセスの詳細なガイダンスについては、「 [診断設定の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)参照してください。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**Monitoring & health**&gt;**Diagnostics** に移動します。
3. 必要に応じてフィルターを調整します。
4. [ **+ 診断設定の追加]** を選択します。
5. **MicrosoftServicePrincipalSignInLogs** を選択します。
6. 表示されるドロップダウン メニューから宛先とサブスクリプションを選択します。
7. **[保存**] ボタンを選択します。

### Microsoft サービス プリンシパルのサインイン ログ

次の表は、アプリケーション ID をログからアプリケーション名とアプリケーションの簡単な説明にマップします。 このテーブルは完全なものではなく、時間の経過と同時に拡大します。 現在ここに記載されているアプリケーションは、ログに含まれるさまざまなアプリケーションを示しています。

一部のアプリケーション表示名には、前のアプリケーション名の頭字語や省略形が含まれる場合があります。 たとえば、一部のサービスでは、サービスのブランドが Microsoft Entra ID に変更された場合でも、表示名に "AAD" (Azure Active Directory) が保持されます。

| アプリケーションの表示名 | アプリケーション識別子 | 説明 |
| --- | --- | --- |
| AAD アプリ管理 | f0ae4899-d877-4d3c-ae25-679e38eea492 | Salesforce や ServiceNow などの基幹業務アプリケーションへのアクセス管理でシングル サインオン エクスペリエンスを提供します。 |
| AAD アプリケーション ARM RP | c8e14c19-0ae6-4966-bd07-17e5ffa8e4ce | 内部の Microsoft サービスとインフラストラクチャ テナントでのみ、ファースト パーティアプリと Microsoft 以外のアプリを作成および管理します。このアプリは、許可リストに登録されているテナントの外部では機能しません。 |
| App Protection | c6e44401-4d0a-4542-ab22-ecd4c90d28d7 | ユーザー定義のポリシーと定義済みのポリシーに基づいて、アプリケーションを自動的に無効にします。このアプリは、テナントにアプリの登録やサービス プリンシパルを作成しませんが、疑わしいアプリケーションに関連付けられているサービス プリンシパルを無効にすることができます。 |
| Azure AD アプリケーション プロキシ | 47ee738b-3f1a-4fc7-ab11-37e4822b007e | プライベート ネットワーク内でアプリケーションを発行する機能を提供し、ネットワーク外のユーザーにアクセスできるようにします。このアプリは、Microsoft Entra Private Access と Microsoft Entra アプリケーション プロキシの両方のワークフローの一部として使用されます。 |
| Azure ESTS サービス | fc03f97a-9db0-4627-a216-ec98ce54e018 | Microsoft Entra の標準準拠認証サービス。 |
| Azure HDInsight クラスター API | fc03f97a-9db0-4627-a216-ec98ce54e018 | Apache Hadoop と Apache Spark エコシステムを含むビッグ データ分析サービス。これにより、大量のデータの処理が可能になります。 |
| Azure Machine Learning | 0736f41a-0425-4b46-bdb5-1563eff02385 | 機械学習プロジェクトのライフサイクルを加速および管理するためのクラウド サービス。 |
| Azure Security Insights | 98785600-1bb7-4fb9-b9fa-19afe2c8a360 | セキュリティ情報イベント管理 (SIEM) とセキュリティ オーケストレーション、自動化、応答 (SOAR) 機能を提供するクラウドネイティブ ソリューション。 |
| Azure SQL Managed Instance から Azure AD リソース プロバイダーへ | 913c6de4-2a4a-4a61-a9ce-945d2b2ce2e0 | 分離されたプライベート仮想ネットワーク環境内でクラウド強化 SQL Server を提供するサービスとしてのプラットフォーム (PaaS)。 |
| Bot Framework Dev Portal | f3723d34-6ff5-4ceb-a148-d99dcd2511fc | ABS リソースの作成プロセス中に Azure Bot Service (ABS) に新しいアプリ登録の作成を求められたときに、ユーザーに代わってアプリ登録を作成します。 |
| CPIM サービス | bb2a2e3a-c5e7-4f0a-88e0-8e01fd3fc1f4 | Customer and Partner Identity Management (CPIM) サービスは、テナントに登録されているアプリに対して認証を行う外部 ID のユーザー ディレクトリを作成するソリューションを提供します。これらの外部 ID 機能には、Google や Facebook などのソーシャル ID プロバイダー、セルフサービス サインアップ、セルフサービス サインアップ フローの API コネクタを含めることができます。 |
| Dynamics ライフサイクル サービス | bb2a2e3a-c5e7-4f0a-88e0-8e01fd3fc1f4 | 顧客とパートナーが Microsoft Dynamics 実装のアプリケーション ライフサイクルを管理するための統合されたコラボレーション環境を提供する Azure ベースのポータル。 |
| Fabric Identity Management | c0be6b4c-212d-4ca9-8a35-fd260fe22342 | Fabric ワークスペース ID を作成および管理します。 |
| MDATPNetworkScanAgent | 04687a56-4fc2-4e36-b274-b862fb649733 | 顧客サイトに登録されている NetworkScan エージェントごとに、顧客テナント内に Microsoft 以外のアプリを作成します。 |
| Microsoft Rights Management サービス | 00000012-0000-0000-c000-000000000000 | Azure Rights Management サービスとも呼ばれるこのアプリは、Purview ラベルやその他の暗号化関連タスクを介して暗号化を適用します。 |
| Microsoft ボリューム ライセンス | 3ab9b3bc-762f-4d62-82f7-7e1d653ce29f | Microsoft 製品およびサービス契約 (MPSA) をサポートするコマース プラットフォーム。これは、250 以上のユーザー/デバイスを持つ商用、政府機関、教育機関向けのトランザクション ライセンス契約です。 |
| Office 365 SharePoint Online | 00000003-0000-0ff1-ce00-00000000000 | このアプリは、OneDrive アプリと SharePoint アプリのファースト パーティ インスタンスを表します。 |
| パートナー 顧客の代理管理者の移行 | 39d63e7-7fa3-4b2b-94ea-ee256fdb8c2f | 詳細な委任された管理者特権 (GDAP) は、拡張ツールと運用 (XTAP) を使用して、パートナーの顧客テナントへのアクセス権を付与します。 |
| Power Virtual Agents サービス9d8f559b-5984-46a4-902a-ad4271e83efa | 9d8f559b-5984-46a4-902a-ad4271e83efa9d8f559b-5984-46a4-902a-ad4271e83efa | Power Virtual Agents は、Microsoft Copilot Studio の以前の名前です。 このアプリのどちらのインスタンスでも、サービスはエージェントごとに作成されたアプリケーションとサービス プリンシパルを管理できます。 |
| ストレージ リソース プロバイダー | a6aa9161-5291-40bb-8c5c-923b567bee3b | お客様がストレージ アカウントとキーをプログラムで管理できるようにします。 |
| SubstrateActionService | 06dd8193-75af-46d0-84bb-9b9bcaa89e8b | Microsoft 以外のアプリ開発者が、Poll や Survey などのカスタマイズされた MessageExtension アプリをビルドして、Microsoft Teamsで使用できるように支援します。 |
| ZTNA ネットワーク アクセス コントロール プレーン | 9d4afbbc-06a4-49e0-8005-4e5afd1d4fec | グローバル セキュア アクセスの機能であるこのアプリを使用すると、管理者はネットワーク変更の効果を制御し、オンボード環境に影響を与えずに新機能を試すことができます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/reference-sla-performance"} -->
## Microsoft Entra ID のサービス レベル アグリーメントのパフォーマンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-sla-performance
- Service: entra-id / monitoring-health
- Article date: 2026-01-06
- Summary: Microsoft Entra ID での認証サービスのサービス レベル アグリーメントのパフォーマンスと達成について説明します

ID 管理者は、Microsoft Entra ID が重要なアプリをサポート可能であることを確認するために、Microsoft Entra のサービス レベル アグリーメント (SLA) のパフォーマンスを追跡する必要があることがあります。 この記事では、Microsoft Entra ID の SLA に従って [Microsoft Entra](https://azure.microsoft.com/support/legal/sla/active-directory/v1_1/) サービスがどのように実行されるかを示します。

この記事はアプリ所有者またはビジネス所有者とのディスカッションで活用し、Microsoft Entra ID から期待できるパフォーマンスを理解できるようにします。

注

この記事は、従業員テナントと外部テナントの両方に適用されます。 ( [テナント構成](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations)の詳細については、こちらを参照してください)。

### Microsoft Entra ID の SLA が測定される方法

ダウンタイムの定義方法と稼働時間の割合の計算方法の詳細は、 [Microsoft Entra ID の SLA](https://azure.microsoft.com/support/legal/sla/active-directory/v1_1/) で提供されます。

パフォーマンスは、システムが外部接続で使用できるかどうかを単に報告するのではなく、顧客の認証エクスペリエンスを反映する方法で測定されます。 この区別は、計算が次の条件に基づくかどうかを意味します。

- ユーザーが認証できる
- Microsoft Entra ID は、認証後にターゲット アプリのトークンを正常に発行します

つまり、1 分ごとに正常にサインインし、トークンまたはエラー応答が発行された一意のユーザーの数は、成功したユーザー分として追跡されます。 ユーザーのサインイン試行が 1 分以内に正常に完了しなかった場合は、失敗したユーザー分としてカウントされ、可用性の割合が低くなります。 各月が完了すると、可用性率は、成功したユーザーの分数を成功したユーザーの合計と失敗したユーザーの分数で割ることによって計算されます。 このレートは、次の SLA 達成表で公開されています。

### 計画的なダウンタイム:

Microsoft Entra ID を使用して、重要なシステムの ID とアクセス管理を提供します。 ビジネス運用で必要なときに Microsoft Entra ID を使用できるようにするために、Microsoft は Microsoft Entra システム メンテナンスのダウンタイムを計画していません。 代わりに、お客様に影響を与えることなく、サービスの実行時にメンテナンスが実行されます。

### 最近の世界規模の SLA パフォーマンス

Microsoft Entra ID へのワークロードの移行を計画するために、SLA を過ぎたパフォーマンスを公開しています。 これらの数値は、Microsoft Entra ID がすべてのテナントの [Microsoft Entra ID の SLA](https://azure.microsoft.com/support/legal/sla/active-directory/v1_1/) の要件を満たしたレベルを示しています。

テーブルの数値は、すべての顧客と地域にわたる Microsoft Entra 認証の総合計です。 数値は、小数点以下 3 桁で切り捨てられます。 数値は切り上げないので、実際の SLA の達成率は示されているよりも高くなります。 前月の可用性は、当月の前半に公開されます。

| 月 | 2021 | 2022 | 2023 | 2024 | 2025 | 2026 |
| --- | --- | --- | --- | --- | --- | --- |
| 1月 |  | 99.998% | 99.998% | 99.999% | 99.998% | 99.999% |
| Ferbruary | 99.999% | 99.999% | 99.999% | 99.999% | 99.998% | 99.999% |
| 3 月 | 99.568% | 99.998% | 99.999% | 99.999% | 99.996% | 99.999% |
| 4 月 | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% |
| 5 月 | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% |
| 6 月 | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% |
| 7 月 | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% |
| 8 月 | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% | 99.999% |
| 9 月 | 99.999% | 99.998% | 99.999% | 99.999% | 99.999% |  |
| 10 月 | 99.999% | 99.999% | 99.999% | 99.998% | 99.999% |  |
| 11 月 | 99.998% | 99.999% | 99.999% | 99.998% | 99.999% |  |
| 12 月 | 99.978% | 99.999% | 99.999% | 99.998% | 99.999% |  |

\*2025 年 4 月より、SLA パフォーマンスの計算を更新し、認証の可用性に関するユーザー エクスペリエンスをより完全に把握できるようにしました。 新しい計算には、 [バックアップ認証システムが](https://learn.microsoft.com/ja-jp/entra/architecture/backup-authentication-system) 再試行に成功した場合など、Microsoft Entra の回復力のあるインフラストラクチャからの認証成功が含まれます。 2025 年 4 月より前は、これらの成功したサインインは SLA 計算に含まれていませんでした。 この新しい計算を追加すると、SLA のパフォーマンス率が向上します。 たとえば、以前の計算ロジックを使用した 2025 年 4 月の数値は 99.998%になります。 新しいロジックでは、99.999%です。

### インシデント履歴

Microsoft Entra のパフォーマンスに重大な影響を与えるすべてのインシデントは、 [Azure の状態履歴](https://azure.status.microsoft/status/history/)に記載されています。 Azure 状態履歴に記載されているイベントの中には、その SLA を下回る Microsoft Entra ID の重大なイベントが含める必要があります。 インシデントの影響に関する情報と、インシデントの原因と、Microsoft が今後のインシデントを防ぐために実行した手順の根本原因分析を表示できます。

### SLA の達成

Microsoft Entra ID は、グローバル SLA のパフォーマンスをパブリックに報告するだけでなく、少なくとも 5,000 人の月間アクティブ ユーザーを持つ組織にテナント レベルの SLA パフォーマンスを提供します。 サービス レベル アグリーメント (SLA) の達成は、Microsoft Entra ID のユーザー認証の可用性です。 現在の可用性ターゲットと SLA の計算方法の詳細については、Microsoft Entra IDの SLA に関するページを参照してください。

テナント レベルの SLA を確認するには:

1. Microsoft Entra 管理センターにサインインします。
2. **Entra ID**&gt;**Monitoring & health**&gt;**Health** に移動します。

月のバーの上にマウス ポインターを置くと、その月の割合が表示されます。 同じ詳細を持つテーブルがグラフの下に表示されます。

[Microsoft Graph API を](https://learn.microsoft.com/ja-jp/graph/api/resources/azureadauthentication?view=graph-rest-beta&preserve-view=true)使用して SLA の達成を表示することもできます。

[Image: SLA 達成レポートのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/reports-faq"} -->
## Microsoft Entra の監視と正常性に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reports-faq
- Service: entra-id / monitoring-health
- Article date: 2025-05-06
- Summary: Microsoft Entra の監視と正常性に関してよく寄せられる質問 (ログやレポートを含む)

この記事では、Microsoft Entra の監視と正常性に関してよく寄せられる質問に回答しています。 詳細については、 [Microsoft Entra の監視と正常性の概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)に関するページを参照してください。

### ライセンス

#### Premium ライセンスはどうすれば取得できますか。

[Microsoft Entra エディションをアップグレードするには、Microsoft Entra ID のライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)を参照してください。

#### Premium ライセンスを取得した後、どのぐらいの期間でアクティビティ ログ データが表示されるようになりますか?

無料ライセンスのアクティビティ ログ データが既にある場合は、すぐに確認できます。 データがない場合、データがレポートに表示されるまでに最大 3 日かかることがあります。

#### Microsoft Entra ID P1 または P2 ライセンスを取得した後、先月のデータを確認できますか?

最近 Premium バージョン (試用版を含む) に切り替えた場合、初めは最大 7 日分のデータを確認できます。 データが蓄積されると、過去 30 日間のデータを確認できます。

### アクティビティ ログとレポート

#### Microsoft Entra 管理センターでアクティビティ ログを表示するには、どのようなロールが必要ですか?

監査ログとサインイン ログを表示する 最小特権ロール は、レポート閲覧者です。 その他のロールには、 **セキュリティ閲覧者** と **セキュリティ管理者**が含まれます。

#### Azure Monitor と統合できるログは何ですか?

サインイン、監査、プロビジョニング、ID 保護、ネットワーク アクセス、およびその他の多くのログは、Azure Monitor やその他の監視およびアラート ツールと統合できます。 B2C 関連の監査イベントは現在含まれていません。 他のエンドポイントと統合できる Microsoft Entra ログの完全な一覧については、「エンドポイントにストリーミングするためのログ オプション を参照してください。

#### Microsoft Entra 管理センターまたは Azure portal から Microsoft 365 アクティビティ ログ情報を取得できますか?

Microsoft 365 アクティビティ ログと Microsoft Entra アクティビティ ログでは、ディレクトリ リソースの大部分が共有されています。 Microsoft 365 アクティビティ ログを完全に表示する場合は、 [Microsoft 365 管理センター](https://admin.microsoft.com) に移動して Office 365 アクティビティ ログ情報を取得する必要があります。 Microsoft 365 の API については、 [Microsoft 365 管理 API に関する](https://learn.microsoft.com/ja-jp/office/office-365-management-api/office-365-management-apis-overview) 記事を参照してください。

#### Microsoft Entra 管理センターからダウンロードできるレコードの数は何件ですか?

Microsoft Entra 管理センターからダウンロードできるログの数は、ブラウザーのメモリ サイズ、ネットワーク速度、Microsoft Entra レポート API の負荷など、複数の要因によって決まります。 一般に、監査ログでは 250,000 件未満のデータ セットが、サインイン ログとプロビジョニング ログでは 100,000 件未満のデータ セットが、ブラウザーのダウンロード機能で適切に機能します。 含まれているフィールドの数に応じて、この数は異なる場合があります。 ブラウザーで大量のダウンロードを完了する際に問題が発生した場合は、 [**レポート API を**](https://learn.microsoft.com/ja-jp/graph/api/resources/azure-ad-auditlog-overview) 使用してデータをダウンロードするか、 [**診断設定を使用してログをエンドポイントに送信**](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)します。

ダウンロードできるログの具体的なセットは、ダウンロードを開始するときの Microsoft Entra 管理センターのアクティブなフィルターによって決まります。 たとえば、Microsoft Entra 管理センターで特定のユーザーにフィルタリングすることは、ダウンロードがその特定のユーザーのログをプルすることを意味します。 ダウンロードされたログ内の列は、変更されません。 Microsoft Entra 管理センターでカスタマイズした列に関係なく、出力には監査ログまたはサインイン ログのすべての詳細が含まれます。

#### Microsoft Entra ID によってアクティビティ ログが格納される期間はどのくらいですか? データの保存期間を教えてください。

ライセンスに応じて、7 日から 30 日間、Microsoft Entra ID にアクティビティ ログが保存されます。 詳細については、「 [Microsoft Entra レポートの保持ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention)」を参照してください。

#### \*\*Usage and Insights\*\* レポートからアプリを選択すると"見つかりません" と表示されるのはなぜですか?

**Usage and Insights** レポートに、Microsoft Cloud Services アプリケーションのサービス間認証が含まれるようになりました。 この一覧にアプリケーションが表示される場合は、テナントに所属するアプリケーションとの間で認証された可能性があります。 ただし、一覧内のアプリケーションはテナント内にありません。認証を表示するレポートには、そのインスタンスのみが表示されます。

### 監査ログ

#### ユーザーがテナントにライセンスを購入したか、または試用版ライセンスを有効にしたかを確認するにはどうすればよいですか? 監査ログにはこのアクティビティが見当たりません。

現在、ライセンス購入または有効化に対して、監査ログに特定のアクティビティはありません。 しかし、"PIM へのリソースのオンボード" アクティビティを、"リソース管理" カテゴリから、ライセンスの購入または有効化に関連付けることができる場合があります。 このアクティビティは常に利用できるとは限らず、また正確な詳細も提供されないことがあります。

#### 監査ログの IP アドレスがユーザーの IP アドレスと一致しません。 なぜでしょうか。

監査ログに表示される IP アドレスは、OAuth クライアントの IP アドレスを表します。 これは、アクションを実行するサービス エンドポイントに接続している TCP ピアの ipAddress を指します。 パブリック アプリケーションやシングルページ Web アプリなど、場合によっては、OAuth クライアントをエンド ユーザーにすることができます。 ただし、ほとんどの場合、監査ログの IP アドレスは、サービスに直接アクセスして監査可能なアクションを実行するためのアクセス トークンを受信したクライアント アプリケーションの IP アドレスです。

### サインイン ログ

#### signInActivity リソースを使用してユーザーの前回のサインイン時刻を調べましたが、数時間経っても更新されていません。 最新のサインイン時間で更新されるのはいつですか?

signInActivity リソースは、 [しばらくサインインしていない](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts)非アクティブなユーザーを検索するために使用されます。 ほぼリアルタイムでは更新されません。 より早くユーザーの前回のサインイン アクティビティを見つける必要がある場合は、Microsoft Entra サインイン ログを使用して、すべてのユーザーの準リアルタイムのサインイン アクティビティを確認できます。

#### [Microsoft Entra サインイン ログ] からダウンロードできる CSV ファイルには、どのようなデータが含まれていますか?

CSV には、選択されているサインインの種類のサインイン ログが含まれています。 サインイン ログ用の Microsoft Graph API で入れ子になった配列として表されるデータは含 *まれません* 。 たとえば、条件付きアクセス ポリシーとレポートのみの情報は含まれません。 サインイン ログに含まれるすべての情報をエクスポートする必要がある場合は、 **データ設定のエクスポート** 機能を使用します。

また、Microsoft Entra 管理センターで列をカスタマイズした場合でも、ダウンロードしたログに含まれる列は変更されない点に注意することも重要です。

#### IP アドレスの一部に .XXX が表示され、サインイン ログのユーザーのデバイスの詳細に "PII Removed" が表示されます。 なぜでしょうか。

Microsoft Entra ID は、次のシナリオでユーザーのプライバシーを保護するために、サインイン ログの一部を編集する場合があります。

- CSP 技術者が CSP が管理するテナントにサインインするときなど、テナント間のサインイン中。
- ログを表示しているテナントにユーザーが所属していることを、Microsoft のサービスが、そのユーザーの ID により十分な確度で判断できなかった場合。
- Microsoft Entra ID は、顧客データを保護するために、テナントに属していないデバイスにより生成された個人を特定できる情報 (PII) を削除します。 PII が、ユーザーとデータ所有者の同意なしにテナントの境界を超えて広がることはありません。

#### 1 つの requestID に重複するサインイン エントリまたは複数のサインイン イベントが表示されます。 なぜでしょうか。

サインイン エントリがログで重複する理由はいくつかあり得ます。

- サインインでリスクが特定された場合は、その直後にほぼ同じ別のイベントが発行され、リスクが含まれます。
- サインインに関連する MFA イベントを受信すると、関連するすべてのイベントが元のサインインに集計されます。
- Kusto への発行など、サインイン イベントのパートナー発行が失敗した場合、イベントのバッチ全体が再試行および再発行され、この結果として重複が発生することがあります。
- 複数の条件付きアクセス ポリシーが関与するサインイン イベントが複数のイベントに分割されるような場合には、結果として、サインイン イベントごとに少なくとも 2 つのイベントが発生することがあり得ます。

#### Log Analytics を使ってサインイン イベントを調査中ですが、TimeGenerated の時刻がサインインの実際の時刻と一致しません。 なぜでしょうか。

Log Analytics の TimeGenerated フィールドは、Log Analytics がエントリを受信して公開した時刻です。 Log Analytics にログを表示するには、Log Analytics ワークスペースにログを *送信* するように診断設定を構成する必要があります。 このプロセスには時間がかかるため、TimeGenerated フィールドはサインインの実際の時刻と一致しない可能性があります。

日付と時刻がサインインと一致することを確認するには、Log Analytics の結果の少し下にある `CreatedDateTime` フィールドを探します。 `AuthenticationDetails` フィールドを展開して、サインインの正確な時刻を表示することもできます。 Log Analytics の時刻は UTC で表示されますが、Microsoft Entra 管理センターのサインイン ログの時刻は現地時刻に表示されるため、調整が必要になる場合があります。

危険なサインイン イベントにも、実際のサインイン時間とは異なる TimeGenerated 時間があります。 危険なサインインの TimeGenerated 時間は、サインインの時刻ではなく、リスクが検出された時刻です。 危険なサインイン イベント自体で、サインインの実際の時刻であるアクティビティ時間を確認します。

#### 対話型以外のサインインが同じタイム スタンプを持っているように見えるのはなぜですか。

非対話型サインインでは、1 時間ごとに大量のイベントがトリガーされる可能性があるため、ログではグループ化されます。

多くの場合、非対話型サインインは、サインインの日時を除き、すべて同じ特性を持ちます。 時間集計が 24 時間に設定されている場合、ログには同時刻のサインインが表示されます。 これらのグループ化された各行を展開して、正確なタイム スタンプを表示できます。

#### サインイン ログのユーザー名フィールドにユーザー ID / オブジェクトID / GUID が表示されます。 パフォーマンスが低下しているのはなぜですか。

サインイン エントリがユーザー名フィールドにユーザー ID、オブジェクト ID、または GUID を表示するのには、いくつかの理由かあります。

- パスワードレス認証では、ユーザー ID がユーザー名として表示されます。 このシナリオを確認するには、問題のサインイン イベントの詳細を確認します。 *authenticationDetail* フィールドに*パスワードレス*が表示されます。
- ユーザーは認証されていますが、まだサインインしていません。 確認するために、割り込みに関連する *エラー コード 50058* があります。
- ユーザー名フィールドに *000000-00000-0000-0000* が表示される場合、テナントの制限が適用され、ユーザーが選択したテナントにサインインできなくなる可能性があります。
- 多要素認証のサインイン試行は、複数のデータ エントリで集約されるため、正しく表示されるまでに時間がかかることがあります。 データが完全に集計されるまでに、最大で 2 時間かかることもありますが、それほど長くかかることはほとんどありません。

#### サインイン ログに 90025 エラーが表示されています。 これは、ユーザーがサインインに失敗したことを意味しまするのですか。 テナントが調整制限に達したのですか。

いいえ。一般的な 90025 エラーは自動再試行で解決されるので、ユーザーはエラーに気づきません。 このエラーは、内部の Microsoft Entra サブサービスが再試行の許容回数に達し、テナントが調整されていることを示していない場合に発生する可能性があります。 これらのエラーは通常、Microsoft Entra ID によって内部で解決されます。 このエラーが原因でユーザーがサインインできない場合は、手動でもう一度試すと問題が解決されるはずです。

#### サービス プリンシパルのサインイン ログで、サービス プリンシパル ID またはリソース サービス プリンシパル ID が "00000000-0000-0000-0000-000000000000" または "" となっている場合、どのような意味がありますか?

サービス プリンシパル ID の値が "0000000-0000-0000-0000-000000000000" の場合は、その認証インスタンスにクライアント アプリケーションのサービス プリンシパルはありません。 Microsoft Entra では、いくつかの Microsoft 製および他社製アプリケーションを除き、クライアント サービス プリンシパルなしのアクセス トークンは発行していません。

リソース サービス プリンシパル ID に値 "0000000-0000-0000-0000-000000000000" が含まれる場合は、その認証インスタンスにリソース アプリケーションのサービス プリンシパルは存在しません。

この動作は現在、限られた数のリソース アプリに対してのみ許可されています。

テナント内のクライアントまたはリソース サービス プリンシパルを使用せずに認証のインスタンスを照会することができます。

- クライアント サービス プリンシパルがないテナントのサインイン ログのインスタンスを検索するには、次のクエリを使用します。

    ```http
    https://graph.microsoft.com/beta/auditLogs/signIns?$filter=signInEventTypes/any(t: t eq 'servicePrincipal') and servicePrincipalId eq '00000000-0000-0000-0000-000000000000'
    ```
- リソース サービス プリンシパルがないテナントのサインイン ログのインスタンスを検索するには、次のクエリを使用します。

    ```http
    https://graph.microsoft.com/beta/auditLogs/signIns?$filter=signInEventTypes/any(t: t eq 'servicePrincipal') and resourceServicePrincipalId eq '00000000-0000-0000-0000-000000000000'
    ```

次に示す Microsoft Entra 管理センターのサインイン ログを確認することもできます。

- [Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
- **Entra ID**&gt;**Monitoring & health**&gt;**Sign-in ログを参照します**。
- [ **サービス プリンシパルのサインイン**] を選択します。
- [日付] フィールドで適切な概算時間 (過去 24 時間、7 日間など) を選択します。
- フィルターを追加し、[ **サービス プリンシパル ID] を** 選択し、値 '000000000-0000-0000-000000000000' を指定して、クライアント サービス プリンシパルなしで認証のインスタンスを取得します。

#### サービス プリンシパルのサインイン ログに表示されるさまざまなアプリのサインイン (認証) を制限するにはどうすればよいですか?

特定のクライアント アプリまたはリソース アプリのテナントでの認証のしくみを制御する場合は、「 [Microsoft Entra アプリを一連のユーザーに制限する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restrict-your-app-to-a-set-of-users) 」の記事の手順に従ってください。

#### 技術的に非対話型のサインインが対話型のサインイン ログに表示されるのはなぜですか?

一部の非対話型サインインは、非対話型サインイン ログがパブリック プレビューで利用可能になる前に利用可能になりました。 これらの非対話型サインインは対話型サインイン ログに含まれ、非対話型ログが使用可能になった後も対話型サインイン ログに残りました。 FIDO2 キーを使用したサインインは、対話型サインイン ログに表示される非対話型サインインの例です。 現在、これらの非対話型ログは常に対話型サインイン ログに含まれます。

#### 資格情報の漏洩や匿名 IP アドレスからのサインインなど、Identity Protection のリスク検出にはどのレポート API を使用すればよいですか?

[Identity Protection リスク検出 API](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-graph-api) を使用して、Microsoft Graph を介してセキュリティ検出にアクセスできます。 この API には、高度なフィルター処理、フィールドの選択が含まれており、リスク検出が 1 つのタイプに標準化されるため、SIEM などのデータ コレクション ツールに簡単に統合できます。

### 条件付きアクセス

#### サインイン ログに表示できる条件付きアクセスの詳細は何ですか。

すべてのサインイン ログで、条件付きアクセス ポリシーのトラブルシューティングを実行できます。 条件付きアクセスの状態を確認し、サインインに適用されたポリシーの詳細と各ポリシーの結果を調べることができます。

作業を開始するには:

- [Microsoft Entra 管理センター](https://entra.microsoft.com/)にサインインします。
- **Entra ID**&gt;**Monitoring & health**&gt;**Sign-in ログを参照します**。
- トラブルシューティングを行うサインインを選択します。
- [ **条件付きアクセス** ] タブを選択すると、サインインに影響を与えたすべてのポリシーと、各ポリシーの結果が表示されます。

#### 条件付きアクセスの状態として表示されるすべての値を教えてください。

条件付きアクセスの状態には、次の値が表示される可能性があります。

- **未適用**: スコープ内のユーザーとアプリを含む条件付きアクセス ポリシーがありませんでした。
- **成功**: スコープ内のユーザーとアプリを含む条件付きアクセス ポリシーがあり、条件付きアクセス ポリシーが正常に満たされました。
- **失敗**: サインインが少なくとも 1 つの条件付きアクセス ポリシーのユーザーとアプリケーションの条件を満たしており、許可コントロールが満たされていないか、アクセスをブロックするように設定されています。

#### 条件付きアクセス ポリシーの結果として表示されるすべての値を教えてください。

条件付きアクセス ポリシーには、次の結果が表示される可能性があります。

- **成功**: ポリシーが正常に満たされました。
- **失敗**: ポリシーが満たされませんでした。
- **未適用**: ポリシー条件が満たされていない可能性があります。
- **無効**: ポリシーが無効な状態になっている可能性があります。

#### サインイン ログのポリシー名が条件付きアクセスのポリシー名と一致しません。 なぜですか?

サインイン ログのポリシー名は、サインイン時の条件付きアクセス ポリシー名に基づいています。 この名前は、サインイン後にポリシー名を更新した場合、条件付きアクセスのポリシー名と一致しなくなることがあります。

#### 条件付きアクセス ポリシーのためにサインインがブロックされましたが、サインイン ログにはサインインは成功したと表示されています。 なぜですか?

現在、条件付きアクセスが適用されている場合は、サインイン ログに表示される Exchange ActiveSync シナリオの結果が、正確ではないことがあります。 レポートのサインイン結果にサインインの成功と表示されても、ポリシーのために実際にはサインインが失敗している場合があります。

#### サインイン ログの詳細の [条件付きアクセス] タブに、Windows サインインまたは Windows Hello for Business が "スコープ外" または "適用できません" と表示されるのはなぜですか?

条件付きアクセス ポリシーは、Windows サインインまたは Windows Hello for Business には適用されません。 条件付きアクセス ポリシーにより、Windows サインイン プロセスではなく、クラウド リソースへのサインイン試行が保護されます。

### Microsoft Graph API

#### 現在 `https://graph.windows.net/&lt;tenant-name&gt;/reports/` エンドポイント API を使用しており、プログラムによってレポート システムに Microsoft Entra の監査レポートと統合されたアプリケーションの使用状況レポートを取り込んでいます。 何に切り替える必要がありますか。

[API リファレンス](https://developer.microsoft.com/graph/)を参照して、[API を使用してアクティビティ ログにアクセスする方法を確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-prerequisites-for-reporting-api)。 このエンドポイントには、古い API エンドポイント**で**取得したすべてのデータを提供する 2 つのレポート (**監査**とサインイン) があります。 この新しいエンドポイントには、アプリの使用状況、デバイスの使用状況、およびユーザー サインイン情報の取得に使用できる Microsoft Entra ID P1 または P2 ライセンスによるサインイン レポートもあります。

#### 現在 `https://graph.windows.net/&lt;tenant-name&gt;/reports/` エンドポイント API を使用しており、プログラムによってレポート システムに Microsoft Entra セキュリティ レポート (漏洩した資格情報や匿名 IP アドレスからのサインインなどの、特定の種類の検出) を取り込んでいます。 何に切り替える必要がありますか。

[Identity Protection リスク検出 API](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-graph-api) を使用して、Microsoft Graph を介してセキュリティ検出にアクセスできます。 この新しい形式により、データのクエリの実行方法がより柔軟になります。 この形式には、高度なフィルター処理、フィールドの選択が用意されており、リスク検出が 1 つのタイプに標準化されるため、SIEM などのデータ コレクション ツールにより簡単に統合できます。 データの形式が異なるため、古いクエリの代わりに新しいクエリを使うことはできません。 ただし、 [新しい API では Microsoft Graph が使用](https://learn.microsoft.com/ja-jp/graph/api/resources/identityprotection-overview)されます。これは、Microsoft 365 や Microsoft Entra ID などの API の Microsoft 標準です。 そのため、作業が必要になれば、現在の Microsoft Graph への投資を拡大するか、または、この新しい標準プラットフォームへの移行開始を促すことができます。

#### クエリの実行時にアクセス許可エラーが発生し続けます。 私は適切な役割を持っていると思っていました。

その場合、Microsoft Entra 管理センターとは別に Microsoft Graph にサインインする必要があります。 右上隅にあるプロファイル アイコンを選択し、右側のディレクトリにサインインします。 アクセス許可のないクエリを実行しようとしている可能性があります。 [ **アクセス許可の変更]** を選択し、[ **同意** ] ボタンを選択します。 サインイン プロンプトに従います。

#### サービス プリンシパルのサインインに関連しない "MicrosoftGraphActivityLogs" イベントが存在するのはなぜですか?

トークンを使用して Microsoft Graph エンドポイントを呼び出すたびに、その呼び出しで `MicrosoftGraphActivityLogs` が更新されます。 これらの呼び出しの一部はファースト パーティのアプリ専用呼び出しであり、サービス プリンシパルのサインイン ログには発行されません。 サインイン ログで見つからない `MicrosoftGraphActivityLogs` が `uniqueTokenIdentifier` に表示されている場合、トークン識別子はファースト パーティのアプリ専用トークンを参照しています。

### 推奨事項

#### "完了" した推奨事項が "アクティブ" に戻ったのはなぜですか?

"完了" とマークされたものの推奨事項に関するアクティビティがサービスによって検出されると、それは自動的に "アクティブ" に戻ります。

### Microsoft サービス プリンシパルのサインイン ログ (プレビュー)

#### 診断設定を使用して 'MicrosoftServicePrincipalSignInLogs' を有効にしましたが、このデータをどうしたらよいかわかりません。

このログはプレビュー段階であり、一部のお客様が利用できない場合があります。 これらのログは、サービス間認証の可視性を提供します。特に、Microsoft が所有するアプリケーションは、顧客所有のリソースに対するトークン発行認証を透過的に保ちます。

アプリケーションを使用して他のリソースにアクセスする可能性が低いシナリオでは、これらのログによって、侵害の原因を特定するために必要な情報が提供される可能性があります。

#### これらの呼び出しが発生している理由の詳細については、どこで確認できますか?

Microsoft が所有するアプリケーションがトークンを要求または発行する具体的な理由は、システムの設計の一部であり、パブリックに共有することはできません。 ログは、透明性を維持し、お客様のテナントの境界内で発生している認証を可視化するために共有されます。

#### このデータはセキュリティ調査に不可欠ですか?

Microsoft Entra ログ ストリームのデータセットは、セキュリティ調査の重要性に基づいて分類されています。 この特定のデータセットは、他のデータセットに比べて優先度がはるかに低いと見なされます。 このデータを使用することは有益ですが、有効にしないことがセキュリティ体制に悪影響を与えるべきではありません。

#### このデータに対してどのようなアクションを実行できますか?

Microsoft が所有するアプリケーションに変更を加える前に、慎重に行うことをお勧めします。 多くの場合、これらのアプリケーションには、課金アプリケーションのトークン発行などの重要な設定があります。 無効にすると、アカウントへのアクセスが失われることがあります。 Microsoft の内部セキュリティ チームは、これらのアプリケーションをセキュリティで保護するために、広範なセキュリティ制御とアクセス ポリシーを実装しています。 Microsoft はセキュリティに自信を持っており、追加の変更を行うことを強くお勧めします。

これらのアプリケーションをブロックまたは制御することを選択した場合、予期しない問題が発生する可能性があり、残念ながら、これらの問題をサポートしたり責任を負ったりすることはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/scenario-health-conditional-access-block-policy"} -->
## 条件付きアクセス ブロック ポリシー正常性シナリオ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/scenario-health-conditional-access-block-policy
- Service: entra-id / monitoring-health
- Article date: 2025-06-06
- Summary: 条件付きアクセス ブロック ポリシーの正常性シナリオの Microsoft Entra Health シグナルとアラートについて説明します

Microsoft Entra Health の監視には、潜在的な問題または障害の状態が検出されたときに監視およびアラートを実行できる一連のテナント レベルの正常性メトリックが用意されています。 条件付きアクセス ブロック ポリシーなど、監視できる正常性シナリオは複数あります。 Microsoft Entra Health のしくみについて詳しくは、以下をご覧ください。

- [Microsoft Entra Health とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)
- [Microsoft Entra 正常性監視のシグナルとアラートの使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-health-scenario-alerts)

この記事では、条件付きアクセス ブロック ポリシーに関連する正常性メトリックについて説明します。たとえば、ユーザーによるリソースへのアクセスが予期せずブロックされたり、ポリシーが意図したとおりに動作しなかったりします。

重要

Microsoft Entra Health シナリオの監視とアラートは現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### [前提条件]

正常性監視シグナルを表示し、アラートを構成して受信するには、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に合わせて、最小限の特権アクセスを持つロールを使用することをお勧めします。

- Microsoft Entra 正常性シナリオ監視シグナルを[表示](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)するには、*Microsoft Entra P1 または P2 ライセンス*を持つテナントが必要です。
- *アラートを表示*し、*アラート通知を受け取る*には、[Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)*と*少なくとも 100 人以上の月間アクティブ ユーザーの両方を持つテナントが必要です。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、*シナリオ監視シグナル、アラート、およびアラート構成を表示*するために必要な最小限の特権ロールです。
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) は、*アラート* を更新し、*アラート通知構成*を更新するために必要な最小限の特権ロールです。
- [条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)を*表示および変更するには、条件付きアクセス*管理者ロールが必要です。
- `HealthMonitoringAlert.Read.All`するには、アクセス許可が必要です。
- microsoft Graph APIを使用してアラートを表示および変更 するには、 アクセス許可が必要です。
- タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。

### アラートとシグナルを調査する

アラートの調査は、データの収集から始まります。 Microsoft Entra 管理センターの Microsoft Entra Health を使用すると、シグナルとアラートの詳細を 1 か所で表示できます。 Microsoft Graph API を使用してシグナルとアラートを表示することもできます。 詳細については、「[健康シナリオのアラートを調査する方法」、および「](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts) Microsoft Graph API を使用してデータを収集する方法のガイダンス」を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **[Entra ID]**&gt;**[監視と正常性]**&gt;**[正常性]** に移動します。 ページが開き、サービス レベル アグリーメント (SLA) の達成ページが開きます。
3. **ヘルスモニタリング** タブを選択します。
4. **条件付きアクセス ブロック ポリシー** シナリオを選択し、アクティブなアラートを選択します。
5. [ **データ グラフの表示** ] セクションからシグナルを表示して、パターンを理解し、異常を特定します。
6. 条件付きアクセスに関する一般的な問題を調べます。

    - [条件付きアクセスでのサインインに関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access)。
    - [アクセス例ポリシーをブロックします](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-example)。
7. サインイン ログを確認します。

    - [サインイン ログの詳細を確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)。
    - 条件付きアクセスの状態が "失敗" であるサインインを探します。
8. 監査ログで、最近変更されたポリシーの有無を確認します。

    - [監査ログを使用して、条件付きアクセス ポリシーの変更に関するトラブルシューティングを行います](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-policy-changes-audit-log)。

### シグナルを理解する

条件付きアクセス ブロック ポリシーの Microsoft Entra Health シグナルは、条件付きアクセス ポリシーが原因でリソースへのアクセスがブロックされたユーザーの数が急増または減少した場合にアラートをトリガーする可能性があります。

- スパイクは、新しいポリシーが有効になっているか、既存のポリシーが変更され、より広範なユーザーとリソースのセットを対象とすることを意味する可能性があります。
- dip は、ポリシーが無効になっているか、より小さなユーザーとリソースのセットをターゲットにするように変更されたことを意味する可能性があります。

これらの変更は、意図的または意図しない可能性があります。

- 変更が意図的なものである場合、他のアクションは必要ない可能性があります。
- 変更が意図しない場合は、監査ログで変更された条件付きアクセス ポリシーを確認する必要があります。

### 一般的な問題を軽減する

次の一般的な問題により、条件付きアクセス ブロック ポリシー アラートによってアラートがトリガーされる可能性があります。 この一覧はすべてを網羅したものではありませんが、調査の開始点となります。

#### 多くのユーザーが "ここからアクセスできません" というメッセージを受け取っています

サインイン中に "ここからアクセスできません" というエラー メッセージが増加すると、条件付きアクセス ブロック アラートがトリガーされる可能性があります。 このメッセージは、ユーザーがアクセスしようとしているアプリケーションに、組織のモバイル デバイス管理ポリシーを満たすデバイスまたはクライアント アプリケーションからのみアクセスできる場合に表示されます。

- このアラートを受け取る多数のユーザーの急増は、組織のモバイル デバイス管理ポリシーの変更を示している可能性があります。
- 少数のユーザーの急増は、特定のデバイスに問題があることを示している可能性があります。

調査するには:

選択したシナリオの **[影響を受けるエンティティ** ] セクションに移動し、[ユーザーの **表示** ] を選択します。

- 問題がより多くのユーザーに影響を与える場合は、対処する必要があるモバイル デバイス管理ポリシーが変更される可能性があります。
- 問題が少数のユーザーに影響を与えている場合は、特定のデバイスに関連している可能性があります。 デバイスを組織のネットワークに参加させる必要がある場合があります。 ユーザーを選択して、自分のプロファイルに直接移動します。

多数のユーザーに影響を与える問題を修復するには:

1. 監査ログを確認して、条件付きアクセス ポリシーに対して行われた変更を確認します。

    - **カテゴリ: ポリシー**にフィルターをかけて、次のイベントを探します。
        - **条件付きアクセス ポリシーを追加する**
        - **条件付きアクセス ポリシーの削除**
        - **条件付きアクセス ポリシーを更新する**

    [Image: 条件付きアクセス ポリシーが強調表示されている監査ログのスクリーンショット。]

    - 次の Microsoft Graph API クエリを使用することもできます。
        - GET `https://graph.microsoft.com/beta/auditLogs/directoryAudits?$filter=loggedByService eq 'Conditional Access'`
        - GET `https://graph.microsoft.com/beta/auditLogs/directoryAudits?$filter=loggedByService eq 'Conditional Access' and operationType eq 'Update'`
        - GET `https://graph.microsoft.com/beta/auditLogs/directoryAudits?$filter=loggedByService eq 'Conditional Access' and operationType eq ‘Add’`
        - GET `https://graph.microsoft.com/beta/auditLogs/directoryAudits?$filter=loggedByService eq 'Conditional Access' and operationType eq 'Delete'`
        - GET `https://graph.microsoft.com/beta/auditLogs/directoryAudits?$filter=loggedByService eq 'Conditional Access' and activityDateTime ge 2024-12-04T22:03:57.2013763Z`
2. モバイル デバイス管理ポリシーが正しく構成されていることを確認します。 [Intune 管理者として Microsoft Intune 管理センター](https://intune.microsoft.com/)にサインインし、[**デバイス**&gt;**構成**] を参照してポリシーを確認します。

特定のユーザーに影響する問題を修復するには:

- 職場所有のデバイスを組織のネットワークに参加させます。
- 個人のデバイスを組織のネットワークに登録します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/scenario-health-sign-ins-compliant-managed-device"} -->
## 準拠またはマネージド デバイスを必要とするサインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/scenario-health-sign-ins-compliant-managed-device
- Service: entra-id / monitoring-health
- Article date: 2025-06-06
- Summary: 準拠している、またはマネージド デバイスが必要なサインインに関する Microsoft Entra Health のシグナルとアラートについて説明します

Microsoft Entra Health の監視には、監視可能な一連のテナントレベルの正常性メトリックが用意されており、潜在的な問題または障害の状態が検出されたときにアラートが生成されます。 デバイスに関連する 2 つのものを含め、監視できる複数の正常性シナリオがあります。

- 条件付きアクセスで準拠デバイスを必要とするサインイン
- 条件付きアクセスでマネージド デバイスを必要とするサインイン

この記事では、準拠およびマネージド デバイスに関連する正常性メトリックと、アラートを受け取ったときに潜在的な問題のトラブルシューティングを行う方法について説明します。 正常性監視シナリオと対話する方法と、すべてのアラートを調査する方法の詳細については、「 [正常性シナリオのアラートを調査する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts)」を参照してください。

重要

Microsoft Entra Health シナリオの監視とアラートは現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### 前提条件

稼働状況の監視シグナルを表示し、アラートの構成と受信を行うための、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に沿うには、最低特権アクセス権を持つ役割を使用することをお勧めします。

- Microsoft Entra Health のシナリオ監視シグナルを "表示" するには、[Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つテナントが必要です。
- 非試用版 [の Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)*と* 少なくとも 100 人の月間アクティブ ユーザーの両方を持つテナントは、 *アラートを表示* し、 *アラート通知を受け取るために*必要です。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、"シナリオ監視シグナル、アラート、アラート構成を表示する" ために必要な最小限の特権ロールです。
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)は、"アラートの更新" と "アラート通知構成の更新" に必要な最小限の特権ロールです。
- "Microsoft Graph API を使用してアラートを表示する" には、`HealthMonitoringAlert.Read.All` のアクセス許可が必要です。
- "Microsoft Graph API を使用してアラートの表示と変更を行う" には、`HealthMonitoringAlert.ReadWrite.All` のアクセス許可が必要です。
- ロールの詳細な一覧については、「[タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#microsoft-entra-health-least-privileged-roles)」を参照してください。

### シグナルとアラートを調査する

アラートの調査は、データの収集から始まります。 Microsoft Entra 管理センターの Microsoft Entra Health を使用すると、シグナルとアラートの詳細を 1 か所で表示できます。 Microsoft Graph API を使用してシグナルとアラートを表示することもできます。 詳細については、「[健康シナリオのアラートを調査する方法」、および「](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts) Microsoft Graph API を使用してデータを収集する方法のガイダンス」を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視と健康**&gt;**健康** にアクセスします。 ページが開き、サービス レベル アグリーメント (SLA) の達成ページが開きます。
3. **ヘルスモニタリング** タブを選択します。
4. **準拠デバイスを必要とするサインイン**またはマネージド デバイス シナリオを**必要とするサインインを**選択し、アクティブなアラートを選択します。

    [Image: Microsoft Entra Health ランディング ページのスクリーンショット。]
5. [ **データ グラフの表示** ] セクションからシグナルを表示して、パターンを理解し、異常を特定します。

    [Image: マネージド デバイス シグナルを必要とするサインインのスクリーンショット。]
6. Intune デバイスのコンプライアンス ポリシーを確認します。

    - 詳しくは、[Intune デバイスのコンプライアンスの概要](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)に関する記事をご覧ください。
    - [デバイスのコンプライアンス ポリシーを監視する](https://learn.microsoft.com/ja-jp/mem/intune/protect/compliance-policy-monitor)方法を確認します。
    - Intune を使っていない場合は、デバイス管理ソリューションのコンプライアンス ポリシーを確認します。
7. 条件付きアクセスに関する一般的な問題を調べます。

    - [条件付きアクセスのデバイス コンプライアンス ポリシーのトラブルシューティングを行います](https://learn.microsoft.com/ja-jp/troubleshoot/mem/intune/device-protection/troubleshoot-conditional-access#devices-appear-compliant-but-users-are-still-blocked)。
    - [条件付きアクセスでのサインインの問題のトラブルシューティングを行います](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access)。
8. サインイン ログを確認します。

    - [サインイン ログの詳細を確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)。
    - サインインがブロックされていて、"かつ"、準拠デバイス ポリシーが適用されているユーザーを探します。
9. 監査ログで、最近のポリシーの変更を調べます。

    - [監査ログを使用して条件付きアクセス ポリシーの変更をトラブルシューティングします](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-policy-changes-audit-log)。

### 一般的な問題を軽減する

次の一般的な問題により、準拠またはマネージド デバイスを必要とするサインインが急増する可能性があります。 この一覧がすべてではありませんが、調査の開始点となります。

#### 多くのユーザーが既知のデバイスからのサインインをブロックされる

大きなユーザー グループが既知のデバイスへのサインインをブロックされている場合、急増は、これらのデバイスがコンプライアンスから外れたことを示している可能性があります。 影響を受けるユーザーの数が組織のユーザーの割合が高い場合は、広範囲にわたる問題が発生している可能性があります。

調査方法:

1. 選択したシナリオの **[影響を受けるエンティティ** ] セクションで、[ユーザーの **表示** ] を選択します。

    - 影響を受けるユーザーのサンプルがパネルに表示されます。 ユーザーを選択して自分のプロファイルに直接移動し、サインイン アクティビティやその他の詳細を表示できます。
    - Microsoft Graph API を使用して、影響の概要で "ユーザー" `resourceType` と `impactedCount` 値を探します。

    [Image: 影響を受けるエンティティのスクリーンショット。]
2. [Intune デバイスのコンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)を調べます。
3. [条件付きアクセスのデバイス コンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/troubleshoot/mem/intune/device-protection/troubleshoot-conditional-access#devices-appear-compliant-but-users-are-still-blocked)を調べます。

#### ユーザーが不明なデバイスからのサインインをブロックされる

増加しているサインインのブロックが不明なデバイスからのものである場合、その急増は、攻撃者がユーザーの資格情報を取得し、そのような攻撃に使われるデバイスからサインインしようとしていることを示している可能性があります。 影響を受けるユーザーの数が少数のユーザーを示している場合、問題はユーザー固有である可能性があります。

調査方法:

1. 選択したシナリオの **[影響を受けるエンティティ** ] セクションで、[ユーザーの **表示** ] を選択します。

    - 影響を受けるユーザーの一覧がパネルに表示されます。 ユーザーを選択して自分のプロファイルに直接移動し、サインイン アクティビティやその他の詳細を表示できます。
    - Microsoft Graph API を使用して、影響の概要で "ユーザー" `resourceType` と `impactedCount` 値を探します。
2. [サインイン ログを確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)。
3. [Microsoft Entra ID 保護でリスクを調べます](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)。

注

Microsoft Entra ID Protection には Microsoft Entra P2 ライセンスが必要です。

#### ネットワークの問題

多数のユーザーが同時にサインインする必要があるリージョンで、システム停止が発生している可能性があります。

調査方法:

1. 選択したシナリオの **[影響を受けるエンティティ** ] セクションで、[ユーザーの **表示** ] を選択します。

    - 影響を受けるユーザーの一覧がパネルに表示されます。 ユーザーを選択して自分のプロファイルに直接移動し、サインイン アクティビティやその他の詳細を表示できます。
    - Microsoft Graph API を使用して、影響の概要で "ユーザー" `resourceType` と `impactedCount` 値を探します。
2. システムとネットワークの正常性を確認して、停止または更新の期間が異常と一致するかを確認します。
3. [サインイン ログを確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)。

    - フィルターを調整して、影響を受けるユーザーが配置されているリージョンからのサインインを表示します。
4. 組織でグローバル セキュリティで保護されたアクセスを使用している場合は、 [トラフィック ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-view-traffic-logs)を確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/scenario-health-sign-ins-mfa"} -->
## Microsoft Entra MFA を必要とするサインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/scenario-health-sign-ins-mfa
- Service: entra-id / monitoring-health
- Article date: 2025-06-06
- Summary: Microsoft Entra 多要素認証を必要とするサインインの Microsoft Entra Health シグナルとアラートについて説明します

Microsoft Entra Health の監視には、監視可能な一連のテナントレベルの正常性メトリックが用意されており、潜在的な問題または障害の状態が検出されたときにアラートが生成されます。 Microsoft Entra 多要素認証 (MFA) など、監視できる正常性シナリオは複数あります。

このシナリオは次のとおりです。

- Microsoft Entra クラウド MFA サービスを使用して MFA サインインを正常に完了したユーザーの数を集計します。
- Microsoft Entra MFA を使用して対話型サインインをキャプチャし、成功と失敗の両方を集計します。
- 対話型 MFA を完了したり、パスワードレスのサインイン方法を使用したりせずにユーザーがセッションを更新する場合は除外されます。

この記事では、このような正常性メトリックと、アラートを受信したときの潜在的な問題のトラブルシューティング方法について説明します。 正常性監視シナリオと対話する方法と、すべてのアラートを調査する方法の詳細については、「 [正常性シナリオのアラートを調査する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts)」を参照してください。

重要

Microsoft Entra Health シナリオの監視とアラートは現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### 前提条件

稼働状況の監視シグナルを表示し、アラートの構成と受信を行うための、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に沿うには、最低特権アクセス権を持つ役割を使用することをお勧めします。

- Microsoft Entra Health のシナリオ監視シグナルを "表示" するには、[Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つテナントが必要です。
- 非試用版 [の Microsoft Entra P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)*と* 少なくとも 100 人の月間アクティブ ユーザーの両方を持つテナントは、 *アラートを表示* し、 *アラート通知を受け取るために*必要です。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、"シナリオ監視シグナル、アラート、アラート構成を表示する" ために必要な最小限の特権ロールです。
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)は、"アラートの更新" と "アラート通知構成の更新" に必要な最小限の特権ロールです。
- "Microsoft Graph API を使用してアラートを表示する" には、`HealthMonitoringAlert.Read.All` のアクセス許可が必要です。
- "Microsoft Graph API を使用してアラートの表示と変更を行う" には、`HealthMonitoringAlert.ReadWrite.All` のアクセス許可が必要です。
- ロールの詳細な一覧については、「[タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#microsoft-entra-health-least-privileged-roles)」を参照してください。

### シグナルとアラートを調査する

アラートの調査は、データの収集から始まります。 Microsoft Entra 管理センターの Microsoft Entra Health を使用すると、シグナルとアラートの詳細を 1 か所で表示できます。 Microsoft Graph API を使用してシグナルとアラートを表示することもできます。 詳細については、「[健康シナリオのアラートを調査する方法」、および「](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts) Microsoft Graph API を使用してデータを収集する方法のガイダンス」を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視と健康**&gt;**健康** にアクセスします。 ページが開き、サービス レベル アグリーメント (SLA) の達成ページが開きます。
3. **ヘルスモニタリング** タブを選択します。
4. **Entra ID MFA シナリオを必要とするサインインを**選択し、アクティブなアラートを選択します。

    [Image: Microsoft Entra Health ランディング ページのスクリーンショット。]
5. [ **データ グラフの表示** ] セクションからシグナルを表示して、パターンを理解し、異常を特定します。

    [Image: MFA シグナルを必要とするサインインのスクリーンショット。]
6. サインイン ログを確認します。

    - [サインイン ログの詳細を確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)。
    - サインインがブロックされ、"さらに" MFA の適用が必要な条件付きアクセス ポリシーが設定されているユーザーを探します。
7. 監査ログで、最近変更されたポリシーがないかを確認します。

    - [監査ログを使用して条件付きアクセス ポリシーの変更をトラブルシューティングします](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-policy-changes-audit-log)。

### 一般的な問題を軽減する

次の一般的な問題により、MFA によるサインインが急増する可能性があります。この一覧がすべてではありませんが、調査の開始点となります。

#### アプリケーションの構成に関する問題

MFA を必要とするサインインの増加は、ポリシーの変更や新機能のロールアウトによって、多数のユーザーによる同時サインインが発生したことを示している可能性があります。

以下のようにして調査します。

1. 選択したシナリオの **[影響を受けるエンティティ** ] セクションで、[アプリケーションの **表示** ] を選択します。

    - 影響を受けるアプリケーションの一覧がパネルに表示されます。 アプリケーションを選択して、監査ログやその他の詳細を表示できるアプリケーションの詳細に直接移動します。
    - Microsoft Graph API を使用して、影響の概要で "アプリケーション" `resourceType` を探します。
2. アプリケーションの監査ログを確認します。

    - アプリケーションが最近追加または再構成されたかどうかを判断します。これによって、多数のユーザーがサインインする可能性があります。
3. サインイン ログを確認します。

    - **[アプリケーション**] 列を使用して、同じアプリケーションまたは日付範囲をフィルター処理して、他のパターンを検索します。

#### ユーザー認証の問題

MFA を必要とするサインインの増加は、ユーザーのアカウントに対して複数の未承認のサインインが試行されているブルート フォース攻撃を示している可能性があります。

以下のようにして調査します。

1. 選択したシナリオの **[影響を受けるエンティティ** ] セクションで、[ユーザーの **表示** ] を選択します。

    - 影響を受けるユーザーの一覧がパネルに表示されます。 ユーザーを選択して自分のプロファイルに直接移動し、サインイン アクティビティやその他の詳細を表示できます。
    - Microsoft Graph API を使用して、影響の概要で "ユーザー" `resourceType` と `impactedCount` 値を探します。
2. サインイン ログを確認します。

    - サインイン ログで、次のフィルターを使用します。
        - **[状態]**: 失敗
        - **[認証の要件]**: 多要素認証
        - 影響の概要に示されている期間と一致するように日付を調整します。
    - 失敗したサインイン試行は同じ IP アドレスで発生していますか?
    - 失敗したサインイン試行は同じユーザーで発生していますか?
    - [サインイン診断](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-sign-in-diagnostics)を実行して、標準的なユーザー エラーの問題または MFA の初期設定の問題を除外します。

#### ネットワークの問題

多数のユーザーが同時にサインインする必要があるリージョンで、システム停止が発生している可能性があります。

以下のようにして調査します。

1. 選択したシナリオの **[影響を受けるエンティティ** ] セクションで、[ユーザーの **表示** ] を選択します。

    - 影響を受けるユーザーの一覧がパネルに表示されます。 ユーザーを選択して自分のプロファイルに直接移動し、サインイン アクティビティやその他の詳細を表示できます。
    - Microsoft Graph API を使用して、影響の概要で "ユーザー" `resourceType` と `impactedCount` 値を探します。
2. システムとネットワークの正常性を確認して、停止または更新の期間が異常と一致するかを確認します。
3. [サインイン ログを確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)。

    - フィルターを調整して、影響を受けるユーザーが配置されているリージョンからのサインインを表示します。
4. 組織でグローバル セキュリティで保護されたアクセスを使用している場合は、 [トラフィック ログ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-view-traffic-logs)を確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/scenario-health-sign-ins-saml-auth"} -->
## SAML 認証を使用したアプリケーションへのサインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/scenario-health-sign-ins-saml-auth
- Service: entra-id / monitoring-health
- Article date: 2025-06-06
- Summary: SAML 認証を使用するアプリケーションへのサインインに関する Microsoft Entra Health のシグナルとアラートについて説明します

Microsoft Entra Health 監視には、テナントの正常性を向上させるために監視できる一連のテナント レベルの正常性メトリックが用意されています。 Security Assertion Markup Language (SAML) 認証シナリオでは、テナントの Microsoft Entra クラウド サービスが正常に処理された SAML 2.0 認証試行を監視します。

- [Microsoft ID プラットフォームで SAML プロトコルを使用する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-protocol-reference)
- でのシングル サインオンには SAML 2.0 IdP を使用します。
- 現在、このメトリックでは、Microsoft Entra ID と統合された WS-FED/SAML 1.1 アプリは除外されています。
- このシナリオでは、アラートを使用できません。

### 前提 条件

正常性監視シグナルを表示し、アラートを構成して受信するには、さまざまなロール、アクセス許可、ライセンス要件があります。 [ゼロ トラスト ガイダンス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に合わせて、最小限の特権アクセスを持つロールを使用することをお勧めします。

- Microsoft Entra 正常性シナリオ監視シグナルを表示するには、microsoft Entra P1 または P2 ライセンス  を持つテナントが必要です。
- [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)ロールは、シナリオ監視シグナルを表示するために必要な最小限の特権ロールです。
- `HealthMonitoringAlert.Read.All`するには、アクセス許可が必要です。
- タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。

### シグナルを調査する

シグナルは、Microsoft Entra 管理センターと Microsoft Graph API を使用して表示できます。 詳細については、Microsoft Graph API を使用してデータを収集する方法のガイダンスについては、 [正常性シナリオのアラートを調査](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts) する方法を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**モニタリングと健康状態**&gt;**健康** にアクセスします。 ページが開き、サービス レベル アグリーメント (SLA) の達成ページが開きます。
3. **ヘルスモニタリング** タブを選択します。
4. **SAML 認証シナリオを使用したアプリケーションへのサインインを**選択します。

    [Image: SAML 正常性監視シナリオのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/troubleshoot-audit-data-verified-domain"} -->
## 確認済みドメインの変更中のユーザー名に対する一括変更 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/troubleshoot-audit-data-verified-domain
- Service: entra-id / monitoring-health
- Article date: 2025-02-25
- Summary: 確認済みドメインの変更中に、Microsoft Entra 監査ログに Core Directory からの UserManagement 更新がどのように表示されるかについて説明します。

この記事では、確認済みドメインの変更によってトリガーされる多くの `UserPrincipalName` の更新が監査ログに表示される一般的なシナリオについて説明します。 この記事では、確認済みドメインの変更中に発生する監査ログの UserManagement 更新の原因と考慮事項について説明します。 この記事では、Microsoft Entra ID で大量のオブジェクトの変更をトリガーするバックエンド操作について詳しく説明します。

### 現象

Microsoft Entra 監査ログに、Microsoft Entra テナントで発生した複数のユーザー更新が示されています。 これらのイベントの**アクター**情報は空であるか、N/A と表示されています。

一括更新には、`UserPrincipalName` のドメインを組織の優先ドメインから既定の `*.onmicrosoft.com` ドメイン サフィックスに変更することが含まれています。

#### サンプル監査ログの詳細

**アクティビティの日付 (UTC)**: 2022-01-27 07:44:05

**アクティビティ**: ユーザーの更新

**アクターの種類**: その他

**アクターの UPN**: N/A

**状態**: 成功

**カテゴリ**: UserManagement

**サービス**: コア ディレクトリ

**ターゲット ID**: aaaaaaaaa-bbbb-0000-11111-bbbbbbbbbbbbb

**ターゲット名**: user@contoso.com

**ターゲットの種類**: ユーザー

監査ログ エントリの詳細内で、`modifiedProperties` セクションを探します。 このセクションには、ユーザー オブジェクトに加えられた変更が示されます。 `oldValue` および `newValue` フィールドには、ドメインの変更が示されます。

```json
"modifiedProperties":
  "displayName": "UserPrincipalName",
  "oldValue": "[\"user@contoso.onmicrosoft.com\"]",
  "newValue": "[\"user@contoso.com\"]"

```

### 原因

大量のオブジェクト変更が発生する一般的な理由の 1 つは、非同期のバックエンド操作によるものです。 この操作により、Microsoft Entra のユーザー、グループ、または連絡先で更新される適切な `UserPrincipalName` と `proxyAddresses` が決定されます。

このバックエンド操作の目的は、UserPrincipalName と proxyAddresses が Microsoft Entra ID 内でいつでも一貫していることを保証することです。 確認ドメインの変更などの明示的な変更によって、この操作がトリガーされます。

たとえば、確認済みドメイン Fabrikam.com を Contoso.onmicrosoft.com テナントに追加すると、このアクションにより、テナント内の "すべての" オブジェクトに対してバックエンド操作がトリガーされます。 このイベントは、**確認済みドメインの追加**イベントの後に続く**ユーザーの更新**イベントとして Microsoft Entra 監査ログに取り込まれます。

Fabrikam.com が Contoso.onmicrosoft.com テナントから削除された場合、すべての**ユーザーの更新**イベントの前に**確認済みドメインの削除**イベントが発生します。

### 解決方法

この問題が発生した場合は、Microsoft Entra Connect を使用してオンプレミスのディレクトリと Microsoft Entra ID の間でデータを同期するとメリットが得られる場合があります。 このアクションにより、`UserPrincipalName` と `proxyAddresses` が両方の環境で一貫していることが保証されます。

これらのオブジェクトを手動で追加または維持しようとすると、別のバックエンド操作によって一括変更がトリガーされるリスクが発生します。

これらの概念を理解するには、次の記事を参照してください。

- [Microsoft Entra UserPrincipalName の作成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-userprincipalname)
- [Microsoft Entra ID に proxyAddresses 属性を設定する方法](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/proxyaddresses-attribute-populate)

### 考慮事項

このバックエンド操作では、以下に該当する特定のオブジェクトに対する変更は行われません。

- アクティブな Microsoft Exchange ライセンスがない
- `MSExchRemoteRecipientType` が Null に設定されている
- 共有リソースとみなされない

共有リソースとは、`CloudMSExchRecipientDisplayType` に次の値のいずれかが含まれているものです。

- `MailboxUser` (共有)
- `PublicFolder`
- `ConferenceRoomMailbox`
- `EquipmentMailbox`
- `ArbitrationMailbox`
- `RoomList`
- `TeamMailboxUser`
- `GroupMailbox`
- `SchedulingMailbox`
- `ACLableMailboxUser`
- `ACLableTeamMailboxUser`

この 2 つの異なるイベント間の相関関係をさらに高めるため、Microsoft では、監査ログ内の**アクター**情報を更新して、これらの変更が確認済みドメインの変更によってトリガーされたことを識別することに取り組んでいます。 このアクションにより、確認済みドメインの変更イベントがいつ発生し、各自のテナント内のオブジェクトの大量更新がいつ開始されたかをチェックすることができます。

ほとんどの場合、`UserPrincipalName` と `proxyAddresses` には一貫性があるため、ユーザーに対する変更はありません。そのため、Microsoft では、オブジェクトに対する実際の変更の原因となった更新のみを監査ログに表示することに取り組んでいます。 このアクションにより、監査ログ内のノイズが防止され、管理者が残りのユーザー変更を確認済みドメイン変更イベントに関連付けることができます。

### 詳しく調べる

バックグラウンドで何が起こっているかについてより詳しい説明が必要でしょうか? ここでは、Microsoft Entra ID で大量のオブジェクトの変更をトリガーするバックエンド操作について詳しく説明します。 本題に入る前に、「[Microsoft Entra Connect Sync サービス シャドウ属性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-shadow-attributes)」の記事を参照して、シャドウ属性を理解してください。

#### ユーザープリンシパルネーム

クラウド限定ユーザーの場合、UserPrincipalName は確認済みドメイン サフィックスに設定されます。 一貫性のない UserPrincipalName が処理されると、操作によって既定の onmicrosoft.com サフィックスに変換されます (例: `username@Contoso.onmicrosoft.com`)。

同期されたユーザーの場合、UserPrincipalName は確認済みドメイン サフィックスに設定され、オンプレミスの値 (`ShadowUserPrincipalName`) と一致します。 一貫性のない UserPrincipalName が処理されると、操作によって ShadowUserPrincipalName と同じ値に戻されます。ドメイン サフィックスがテナントから削除されている場合は、既定の `*.onmicrosoft.com` ドメイン サフィックスに変換されます。

#### プロキシアドレス

クラウド限定ユーザーの場合、一貫性とは、`proxyAddresses` が確認済みドメイン サフィックスと一致していることを意味します。 一貫性のない proxyAddresses が処理されると、バックエンド操作によって既定の `*.onmicrosoft.com` サフィックスに変換されます (例: `SMTP:username@Contoso.onmicrosoft.com`)。

同期ユーザーの場合、一貫性とは、proxyAddresses がオンプレミスの proxyAddresses 値 (つまり、ShadowProxyAddresses) と一致していることを意味します。 proxyAddresses は ShadowProxyAddresses と同期されていることが期待されています。 同期ユーザーに Exchange ライセンスが割り当てられている場合は、クラウドとオンプレミスの値が一致する必要があります。 これらの値は、確認済みドメイン サフィックスとも一致する必要があります。

このシナリオでは、バックエンド操作によって、未確認のドメイン サフィックスを持つ一貫性のない proxyAddresses がサニタイズされ、Microsoft Entra ID 内のオブジェクトから削除されます。 その未確認ドメインが後で確認された場合、バックエンド操作によって ShadowProxyAddresses から proxyAddresses が再計算され、Microsoft Entra ID 内のオブジェクトに追加されます。

注

同期オブジェクトの場合、バックエンド操作ロジックによる予期しない計算結果を避けるために、オンプレミス オブジェクトの Microsoft Entra 確認済みドメインに proxyAddresses を設定することをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace"} -->
## Log Analytics ワークスペースとカスタム ブックを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace
- Service: entra-id / monitoring-health
- Article date: 2025-03-27
- Summary: Log Analytics ワークスペースを構成し、ブックを作成し、Microsoft Entra ID で Kusto クエリを実行する方法について説明します。

このチュートリアルでは、次の作業を行う方法について説明します。

- Log Analytics ワークスペースを作成する
- Log Analytics ワークスペースとサインイン ログを統合するように診断設定を構成する
- Kusto クエリ言語 (KQL) を使用してクエリを実行する

### 前提条件

Log Analytics を使用してアクティビティ ログを分析するには、以下のロールと要件が必要です。

- [Microsoft Entra の監視およびシステム健全性に関するライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)
- [Log Analytics ワークスペースを作成するためのアクセス](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)
- Azure Monitor の適切な役割:

    - 監視リーダー
    - ログアナリティクス リーダー
    - モニタリング貢献者
    - Log Analytics 共同作成者
- Microsoft Entra ID における適切な役割は次のとおりです。

    - レポート閲覧者
    - セキュリティリーダー
    - グローバル閲覧者
    - セキュリティ管理者

### Log Analytics ワークスペースを作成する

この手順では、最終的にサインイン ログを送信する Log Analytics ワークスペースを作成します。 ワークスペースを作成する前に、 [Azure リソース グループ](https://learn.microsoft.com/ja-jp/azure//azure-resource-manager/management/overview#resource-groups)が必要です。

1. 最低でも[セキュリティ管理者](https://portal.azure.com)であり、[Log Analytics 共同作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)のアクセス許可を持つユーザーとして[Azure portal](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access#log-analytics-contributor)にサインインします。
2. **Log Analytics ワークスペース**に移動します。
3. **作成**を選択します。

    [Image: ログ分析ワークスペース ページの [作成] ボタンのスクリーンショット。]
4. [ **Log Analytics ワークスペースの作成** ] ページで、次の手順を実行します。

    1. サブスクリプションを選択します。
    2. リソース グループを選択します。
    3. ワークスペースに名前を付けます。
    4. 地域を選択します。

    [Image: 新しいログ分析ワークスペースの作成の詳細ページのスクリーンショット。]
5. [ **確認と作成]** を選択します。
6. [ **作成]** を選択し、デプロイを待ちます。 新しいワークスペースを表示するには、ページの更新が必要な場合があります。

### 診断設定を構成する

ID ログ情報を新しいワークスペースに送信するには、診断設定を構成する必要があります。 Azure と Microsoft Entra にはさまざまな診断設定オプションがあるため、次の一連の手順では Microsoft Entra 管理センターに切り替えて、すべてが ID に関連していることを確認しましょう。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **[Entra ID]**&gt;**[監視と正常性]**&gt;**[Diagnostic 設定]** に移動します。
3. [ **診断設定の追加] を選択します**。

    [Image: [診断設定の追加] オプションのスクリーンショット。]
4. [ **診断設定** ] ページで、次の手順を実行します。

    1. 診断設定の名前を指定します。
    2. [ **ログ**] で、[ **AuditLogs** ] と [ **SigninLogs**] を選択します。
    3. [ **宛先の詳細]** で [ **Log Analytics に送信**] を選択し、新しいログ分析ワークスペースを選択します。
    4. **[保存] を選択します**。

    [Image: 診断設定の選択オプションのスクリーンショット。]

選択したログが Log Analytics ワークスペースに入力されるまでに最大 15 分かかる場合があります。

### Log Analytics でクエリを実行する

ログが Log Analytics ワークスペースにストリーミングされている場合は、 **Kusto クエリ言語 (KQL)** を使用してクエリを実行できます。 クエリを実行する特権が最も低いロールは、 **レポート閲覧者** ロールです

1. **Entra ID**&gt;**Monitoring & health**&gt;**Log Analytics** に移動します。
2. [ **検索** ] ボックスにクエリを入力し、[ **実行**] を選択します。

#### Kusto クエリの例

入力データから 10 個のエントリをランダムに取得する:

- `SigninLogs | take 10`

条件付きアクセスが成功したサインインを確認する:

- `SigninLogs | where ConditionalAccessStatus == "success" | project UserDisplayName, ConditionalAccessStatus`

成功の数をカウントする:

- `SigninLogs | where ConditionalAccessStatus == "success" | project UserDisplayName, ConditionalAccessStatus | count`

成功したサインインの数をユーザー別および日別に集計する:

- `SigninLogs | where ConditionalAccessStatus == "success" | summarize SuccessfulSign-ins = count() by UserDisplayName, bin(TimeGenerated, 1d)`

ユーザーが特定の期間に特定の操作を行った回数を表示する:

- `AuditLogs | where TimeGenerated > ago(30d) | where OperationName contains "Add member to role" | summarize count() by OperationName, Identity`

操作名で結果をピボットする:

- `AuditLogs | where TimeGenerated > ago(30d) | where OperationName contains "Add member to role" | project OperationName, Identity | evaluate pivot(OperationName)`

内部結合を使用して監査ログとサインイン ログをマージする:

- `AuditLogs |where OperationName contains "Add User" |extend UserPrincipalName = tostring(TargetResources[0].userPrincipalName) | |project TimeGenerated, UserPrincipalName |join kind = inner (SigninLogs) on UserPrincipalName |summarize arg_min(TimeGenerated, *) by UserPrincipalName |extend SigninDate = TimeGenerated`

クライアント アプリの種類別にサインイン数を表示する:

- `SigninLogs | summarize count() by ClientAppUsed`

日別にサインイン数をカウントする:

- `SigninLogs | summarize NumberOfEntries=count() by bin(TimeGenerated, 1d)`

5 つのエントリをランダムに取得し、表示したい列を結果に投影する:

- `SigninLogs | take 5 | project ClientAppUsed, Identity, ConditionalAccessStatus, Status, TimeGenerated`

降順における最初の 5 つを取得し、表示したい列を投影する:

- `SigninLogs | take 5 | project ClientAppUsed, Identity, ConditionalAccessStatus, Status, TimeGenerated`

値を他の 2 つの列に結合して新しい列を作成する:

- `SigninLogs | limit 10 | extend RiskUser = strcat(RiskDetail, "-", Identity) | project RiskUser, ClientAppUsed`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/tutorial-create-log-analytics-workbook"} -->
## Log Analytics カスタム ワークブックを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-create-log-analytics-workbook
- Service: entra-id / monitoring-health
- Article date: 2025-03-27
- Summary: Microsoft Entra ID で分析とアラートを強化するための Log Analytics カスタム ブックを作成する方法について説明します。

このチュートリアルでは、以下の内容を学習します。

- カスタム ワークブックを作成する
- 既存のブック テンプレートにクエリを追加する

### [前提条件]

Log Analytics を使用してアクティビティ ログを分析するには、以下のロールと要件が必要です。

- [Microsoft Entra の監視と健全性ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-monitoring-and-health)
- [Log Analytics ワークスペースを作成するためのアクセス](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)
- Azure Monitor の適切な役割について以下に述べる。

    - 監視リーダー
    - Log Analytics 閲覧者
    - モニタリング貢献者
    - Log Analytics 共同作成者
- Microsoft Entra ID の適切な役割:

    - レポートリーダー
    - セキュリティビューア
    - グローバルリーダー
    - セキュリティ管理者

Log Analytics ワークスペースをまだ作成していない場合は、「 [Log Analytics ワークスペースの構成](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace) 」チュートリアルを完了してください。

### カスタム ワークブックを作成する

Kusto クエリ言語 (KQL) を使用してデータに対してクエリを実行するだけでなく、さらに分析とアラートを生成するためのカスタム ブックを作成することもできます。 ブックを作成または更新するための最小限の特権ロールは、 **セキュリティ管理者** ロールです。

1. **Entra ID**&gt;**監視とヘルス**&gt;**ワークブック** に移動します。
2. **[クイック スタート**] セクションで、[空] を選択**します**。

    [Image: クイック スタート セクション内の空白のブックのスクリーンショット。]
3. [ **追加** ] メニューの [ **テキストの追加**] を選択します。

    [Image: [テキストの追加] メニュー オプションのスクリーンショット。]
4. テキストボックスに「 `# Client apps used in the past week` 」と入力し、[ **編集完了]** を選択します。

    [Image: テキストと [編集完了] ボタンを示すスクリーンショット。]
5. テキスト ウィンドウの下にある **[追加** ] メニューを開き、[ **クエリの追加]** を選択します。

    [Image: [クエリの追加] メニュー オプションのスクリーンショット。]
6. クエリ テキスト ボックスに「`SigninLogs | where TimeGenerated > ago(7d) | project TimeGenerated, UserDisplayName, ClientAppUsed | summarize count() by ClientAppUsed`」と入力します。
7. [ **クエリの実行] を選択します**。

    [Image: [クエリの実行] ボタンを示すスクリーンショット。]
8. ツール バーの **[視覚化** ] メニューから [ **円グラフ**] を選択します。

    [Image: 円グラフのメニューオプションを示すスクリーンショット。]
9. ページの上部にある [ **編集完了]** を選択します。
10. **保存**アイコンを選択してブックを保存します。
11. 表示されるダイアログ ボックスで、タイトルを入力し、リソース グループを選択して、[ **適用**] を選択します。

### ブック テンプレートにクエリを追加する

Kusto クエリをワークブックに追加することができます。 この例は、条件付きアクセス ポリシーが適用された成功したサインインと失敗したサインインの分布を示すクエリに基づいています。 ブックを作成または更新するための最小限の特権ロールは、 **セキュリティ管理者** ロールです。

1. **Entra ID**&gt;**監視とヘルス**&gt;**ワークブック** に移動します。
2. [ **条件付きアクセス** ] セクションで、[ **条件付きアクセスの分析情報とレポート**] を選択します。

    [Image: [条件付きアクセスの分析情報とレポート] オプションを示すスクリーンショット。]
3. ツール バーの [編集] を選択 **します**。

    [Image: [編集] ボタンを示すスクリーンショット。]
4. ツール バーで、[編集] ボタンの横にある 3 つのドットを選択し、[ **追加]**、[ **クエリの追加]** の順に選択します。

    [Image: ワークブック クエリを追加する]
5. クエリ テキスト ボックスに「`SigninLogs | where TimeGenerated > ago(20d) | where ConditionalAccessPolicies != "[]" | summarize dcount(UserDisplayName) by bin(TimeGenerated, 1d), ConditionalAccessStatus`」と入力します。
6. [ **クエリの実行] を選択します**。

    [Image: このクエリを実行する [クエリの実行] ボタンを示すスクリーンショット。]
7. [ **時間範囲** ] メニューの [ **クエリで設定**] を選択します。
8. **[視覚化**] メニューから [**横棒グラフ**] を選択します。
9. [ **詳細設定] を選択します**。

    [Image: 時間範囲、視覚化、詳細設定オプションのスクリーンショット。]
10. [ **グラフのタイトル** ] フィールドに「 `Conditional Access status over the last 20 days` 」と入力し、[ **編集完了]** を選択します。

    [Image: グラフのタイトルを設定する]

条件付きアクセスの成功と失敗のグラフに、テナントの色分けされたスナップショットが表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/understand-service-principal-creation-with-new-audit-log-properties"} -->
## テナントでサービス プリンシパルが作成された理由を理解する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/understand-service-principal-creation-with-new-audit-log-properties
- Service: entra-id / monitoring-health
- Article date: 2026-01-26
- Summary: Microsoft Entra サービス プリンシパルがテナントに作成された理由と、監査ログ アクティビティが追加された新しいプロパティによってイベントをトリガーしたユーザーまたはトリガーについて説明します。

Microsoft Entra 監査ログでは、アプリケーション、グループ、ユーザー、およびその他のディレクトリ リソースに対する変更がキャプチャされます。 Microsoft Entra サービス プリンシパルがテナントで作成された理由と、イベントをトリガーしたユーザーまたはイベントのトリガーを理解するために、**ApplicationManagement** カテゴリの [**サービス プリンシパルの追加]** アクティビティに新しいプロパティが追加されます。 この記事では、監査ログで何が変更されているか、新しいデータを検索する方法、およびさまざまなプロパティ値を解釈する方法について説明します。

### これらの監査ログの機能強化でできること

- 新しく作成されたサービス プリンシパルが Microsoft によってプロビジョニングされたか、購入したサブスクリプションによってプロビジョニングされたか、ユーザーまたはアプリの直接アクションによってプロビジョニングされたかをすばやく特定します。
- テナント内の Microsoft 365 またはその他の SKU で、Just-In-Time プロビジョニングの対象となる Microsoft サービス プリンシパルを確認します。
- コマース ベースの Just-In-Time (JIT) プロビジョニング、既定の適格性、マネージャー アプリケーション、Azure リソース プロバイダー、マネージド ID など、さまざまなプロビジョニング メカニズムを区別します。
- サービス プリンシパルが作成された理由を調査するときに、追加の Microsoft Graph 呼び出しとカスタム データ パイプラインへの依存関係を減らします。

### [前提条件]

Microsoft Entra 監査ログでこれらのプロパティを表示して使用するには、次のものが必要です。

- [Microsoft Entra 管理センター](https://entra.microsoft.com)へのアクセス。
- 次のいずれかのロール (または同等のアクセス許可を持つロール): [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、 [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)、または [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)。
- テナントに対して有効になっている監査ログ。 詳細については、 [Microsoft Entra 監査ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)を参照してください。

注

Yuo は、Microsoft Graph API または Log Analytics AuditLogs テーブルでこれらの新しいログを表示することもできます。

### 監査ログに新しいプロパティが表示される場所

新しいプロパティは、次の特性を持つ Microsoft Entra 監査ログに表示されるサービス プリンシパル作成イベントに対して生成されます。

- **サービス：** コア ディレクトリ
- **カテゴリ：** ApplicationManagement
- **Activity (activityDisplayName):** サービス プリンシパルの追加

[Microsoft Entra 管理センター](https://entra.microsoft.com)では、監視**と正常性**&gt;**Audit ログ**の下にこれらのイベントがあります。 **サービス プリンシパルの追加**イベントを選択すると、ログをエクスポートまたはクエリを実行すると、新しいプロパティが **[追加の詳細**] セクションと対応する JSON に表示されます。

[Image: [アプリケーション管理] へのカテゴリ ドロップダウンを示す Microsoft Entra 管理センターのスクリーンショット。]

[Image: サービス プリンシパルを追加するための [アクティビティ] ドロップダウンのスクリーンショット。]

### サービス プリンシパルの作成に関する新しい監査ログのプロパティ

次の新しいプロパティは、サービス プリンシパル作成イベントを理解するのに役立ちます。

- **ServicePrincipalProvisioningType** - サービス プリンシパルが作成可能になった方法と理由、および使用されたプロビジョニング メカニズムについて説明します。
- **SubscribedSkus** - Microsoft サービス プリンシパルがコマース ベースの Just-In-Time プロビジョニングの対象になったテナント サブスクリプションとサービス プランを一覧表示します。
- **AppOwnerOrganizationId** — サービス プリンシパルを所有するアプリ登録のホーム テナントを識別します。

#### ServicePrincipalProvisioningType

`ServicePrincipalProvisioningType` は、サービス プリンシパルの追加イベントに使用されるプロビジョニング パスを示す列挙値です。 つまり、"なぜこのサービス プリンシパルが作成され、どのシステムまたはアクターによって開始されたか" という質問に答えます。このフィールドは、Microsoft ファースト パーティアプリとサード パーティ 製アプリの両方についてログに記録されます。

| 価値 | 意味 |
| --- | --- |
| `defaultMicrosoft` | サービス プリンシパルは、既定の適格性またはシステム管理プロセスの一部として Microsoft によって作成されました。 これには、Microsoft ファースト パーティ アプリの既定の Just-In-Time (JIT) プロビジョニングまたはその他のマネージド プロビジョニングが含まれます。 |
| `subscription` | テナントに対象となるサブスクリプション (SKU) またはサービス プランが含まれているため、サービス プリンシパルが作成されました。 これは、Microsoft ファースト パーティ アプリ向けのコマースベースの JIT プロビジョニングです。 **SubscribedSkus** フィールドを使用して、対象となった SKU またはサービス プランを確認します。 |
| `managerApplications` | 他のサービス プリンシパルを管理するアクセス許可を持つ Microsoft が管理する "マネージャー" アプリケーションによって、このサービス プリンシパルが作成されました。 マネージャー アプリ ID は、initiatedBy.app などの既存の監査ログ フィールドで使用できます。 |
| `AzureResourceProvider` | Azure リソース プロバイダーは、Azure サービスのオンボードの一環としてサービス プリンシパルを作成しました (Azure Data Explorer など)。 これは通常、インフラストラクチャ主導のプロビジョニングを表します。 |
| `ManagedServiceIdentity` | サービス プリンシパルは、マネージド ID (システム割り当てまたはユーザー割り当て) を表します。 この値は、Azure リソースのマネージド ID が作成されるときに表示されます。 |
| `Other` | 委任されたフローやアプリ専用フローなど、上記のカテゴリに分類されないプロビジョニング メカニズム用に予約されています。 **委任：** ユーザー アクション、またはユーザーに代わって委任されたアクセス許可で動作するアプリは、直接サービス プリンシパルの作成をトリガーしました (たとえば、Microsoft Graph 経由で POST /servicePrincipals を呼び出したり、アプリをプロビジョニングするポータル UX を呼び出したりします)。 **AppOnly:** Microsoft Graph またはディレクトリに直接呼び出されたアプリ専用アクセス許可を使用してサービス プリンシパルを作成するアプリケーション (自動化やカスタム プロビジョニング サービスなど)。 |

注

他の監査ログ イベント プロパティを使用して、これらのフローを区別できます。

`ServicePrincipalProvisioningType`を使用すると、Microsoft 主導のプロビジョニング (`defaultMicrosoft`、`subscription`、`AzureResourceProvider`、`managerApplications`など) をテナント駆動型プロビジョニング (その他など) から分離できます。 Microsoft 主導のプロビジョニングとテナント駆動型のプロビジョニングを分離すると、新しいサービス プリンシパルが Microsoft プラットフォーム プロセスに起因するか、テナントの管理者、ユーザー、またはカスタム アプリから得られたかを迅速に判断できます。 テナントで新しい [サービス プリンシパルの](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-view-service-principal-creation-with-audit-log-properties) 検出を作成する方法の詳細については、監査ログのプロパティでサービス プリンシパルの詳細を表示するを参照してください。

#### SubscribedSkus

`SubscribedSkus` は、 `ServicePrincipalProvisioningType = subscription` で作成された Microsoft サービス プリンシパルを、コマース ベースの Just-In-Time プロビジョニングの対象にしたテナント内のサブスクリプションとサービス プランにリンクする JSON 配列です。

1 つのサービス プリンシパルを複数のサービス プランに関連付けることができ、各サービス プランが複数の SKU に属できるため、SubscribedSkus ではテナント内の SKU とプランの簡潔な一覧が提供され、プロビジョニングの資格が得られる可能性があります。 これは、調査の開始点として意図されています。

| Key | それが表すもの |
| --- | --- |
| `SkuId` | テナント内のサブスクリプション (SKU) の GUID。 この ID を Microsoft Graph /subscribedSkus と共に使用して、完全な詳細を取得できます。 |
| `SkuName` または `SkuPartNumber` | Microsoft 365 E5 や SPE\_E5 など、SKU の人間が判読できる名前または部品番号。 |
| `ServicePlanId` | 特定の SharePoint プランや電子署名プランなど、SKU 内のサービス プランの GUID。 |
| `ServicePlanName` | サービス プランの内部名 (たとえば、 `SHAREPOINTENTERPRISE`)。 |
| `ServicePlanServiceType` | プランが属する Microsoft サービス (たとえば、 `SharePoint`)。 |
| `Association` | 大規模なプランに含まれるアプリの `include` など、プランとサービス プリンシパルの関係について説明します。 |

たとえば、`SubscribedSkus`に関連付けられた `SPE_E5` SKU を持つ `SHAREPOINTENTERPRISE` という名前のサービス プランが表示されている場合は、テナント内の Microsoft 365 E5 サブスクリプションによって、この Microsoft サービス プリンシパルがプロビジョニングの対象になったと推測できます。 その後、Microsoft Graph を使用して、 `/subscribedSkus` リソースに対してクエリを実行し、アクティブなサブスクリプションとサービス プランを確認できます。

[Image: SKU とサービス プランのプロパティを持つ SubscribedSkus JSON 配列構造を示すコードのスクリーンショット。]

#### AppOwnerOrganizationId

`AppOwnerOrganizationId` は、サービス プリンシパルを所有するアプリケーションのホーム テナント ID をログに記録します。 この値は、「追加の詳細」セクションで、 `"AppOwnerOrganizationId" : "<tenantId>"`などのキーと値のペアとして書き込まれます。

この値を使用して、次の値を区別できます。

- **独自のテナントに登録されているアプリ** — 値はテナント ID と一致します。
- **Microsoft が所有するアプリ**— この値は、Microsoft サービス テナント ID (f8cdef31-a31e-4b4a-93e4-5f571e91255a) と一致します。
- **その他の外部アプリまたはパートナー アプリ** - 値は別の外部テナントと一致します。

これにより、新しいサービス プリンシパル作成イベントごとに `appOwnerOrganizationId` を取得するために、Microsoft Graph を個別に呼び出す必要が減ります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/workbook-authentication-prompts-analysis"} -->
## 認証プロンプトの分析ブック - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-authentication-prompts-analysis
- Service: entra-id / monitoring-health
- Article date: 2024-11-04
- Summary: Microsoft Entra ID の認証プロンプト分析ブックを使用して、MFA プロンプトが多すぎるユーザーを調査する方法について説明します。

IT プロフェッショナルは、環境内で認証プロンプトに関する正しい情報を得ることで、予期しないプロンプトを検出して調査を進められるようにすることを望んでいます。 この種類の情報を提供することは、 **認証プロンプト分析** ブックの目的です。

### 前提条件

Microsoft Entra ID 用の Azure Workbooks を使用するには、次のものが必要です。

- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つ Microsoft Entra テナント
- Log Analytics ワークスペース *とその* ワークスペースへのアクセス
- Azure Monitor *および* Microsoft Entra ID の適切なロール

#### Log Analytics ワークスペース

Microsoft Entra ブックを使用する*前*に、[Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)を作成する必要があります。 Log Analytics ワークスペースへのアクセスはいくつかの要因によって決まります。 ワークスペース *と* データを送信するリソースに適したロールが必要です。

詳細については、「 [Log Analytics ワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には、監視データを表示し、監視設定を編集するための [2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader) が用意されています。 Azure ロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics 組み込みロールも用意されています。

- **表示**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定の表示と変更**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内の Microsoft Entra ID ログ データを表示したり、Log Analytics からデータのクエリを実行したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 更新アクセスにより、Microsoft Entra データを Log Analytics ワークスペースに送信するための診断設定を作成および編集する機能が追加されます。

- **読み取り**:

    - レポート閲覧者
    - セキュリティ閲覧者
    - グローバル閲覧者
- **更新:**

    - セキュリティ管理者

Microsoft Entra 組み込みロールの詳細については、「 [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Log Analytics RBAC ロールの詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#log-analytics-contributor)参照してください。

### 説明

[Image: ワークブックカテゴリ]

最近、認証プロンプトが多すぎるという苦情をユーザーから受けたことはありますか?

ユーザーに過度にプロンプトを表示すると生産性に影響が及び、ユーザーが多要素認証 (MFA) のフィッシング詐欺にあう可能性があります。 明確にすると、ここで言いたいのは MFA を求める必要が*あるかどうか*ではなく、*どのくらいの頻度でユーザーにプロンプトすべきか*ということです。

プロンプトの過剰表示の要因としては、次のものが考えられます。

- アプリケーションが正しく構成されていない
- 過剰に厳しいプロンプト ポリシー
- サイバー攻撃

認証プロンプトの分析ブックでは、さまざまな種類の認証プロンプトが明記されています。 種類が、ユーザー、アプリケーション、オペレーティング システム、プロセスなど、異なる要因に基づいています。

このブックは、次のシナリオで使用できます。

- プロンプトが多すぎるというユーザーからのフィードバックを調査するため。
- 1 つの具体的な認証方式、ポリシー適用、またはデバイスに起因するプロンプトの過剰表示を検出するため。
- 注目を集めるユーザーの認証プロンプト数を見るため。
- レガシ TLS およびその他の認証プロセスの詳細を追跡するため。

### ブックにアクセスする方法

1. 適切なロールの組み合わせを使用して [、Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** を参照します。
3. **使用法**セクションから**認証プロンプト分析ワークブック**を選択します。

### ブックのセクション

このブックでは、認証プロンプトを以下で分類します。

- メソッド
- デバイスの状態
- アプリケーション
- ユーザー
- ステータス
- オペレーティング システム
- プロセスの詳細
- ポリシー

[Image: 認証方法による認証プロンプト]

多くの環境では、最も使用されるアプリはビジネス生産性アプリです。 想定されていないものは調査する必要があります。 次の表は、アプリケーション別の認証プロンプトを示しています。

[Image: アプリケーション別の認証プロンプト]

**アプリケーションリストビューのプロンプト**には、タイムスタンプや調査に役立つ要求 ID などの追加情報が表示されます。

さらに、テナントの平均および中央値のプロンプト数の概要が表示されます。

[Image: アプリケーション別のプロンプト]

このブックは、ユーザーのエクスペリエンスを向上させてプロンプト数と相対的割合を減らすための影響力ある方法を追跡するのにも役立ちます。

[Image: プロンプトを減らすための推奨事項]

### フィルター

フィルターを利用して、データの表示粒度をより細かくします。

[Image: フィルター]

数多くの認証要求を持つ特定のユーザーにフィルタリングしたりサインイン エラーのあるアプリケーションだけを表示したりすることで、修復を継続することの興味深い結果が得られる可能性もあります。

### ベスト プラクティス

- データが表示されない場合、または正しく表示されていない可能性がある場合は、適切なリソースに **Log Analytics ワークスペース** と **サブスクリプション** を設定していることを確認します。

    [Image: ワークスペースとサブスクリプションを設定する]
- ビジュアルの読み込みに時間がかかりすぎる場合は、時間フィルターを 24 時間以下に減らしてみてください。

    [Image: フィルターの設定]
- MFA プロンプトに影響を与えるさまざまなポリシーの詳細については、「 [再認証プロンプトの最適化」を参照し、Microsoft Entra 多要素認証のセッションの有効期間を理解](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime)してください。
- 通信ベースのメソッドから Authenticator アプリにユーザーを移動する方法については、「 [登録キャンペーンを実行して Microsoft Authenticator - Microsoft Authenticator アプリを設定する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-registration-campaign)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/workbook-conditional-access-gap-analyzer"} -->
## 条件付きアクセス ギャップ アナライザー ブック - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-conditional-access-gap-analyzer
- Service: entra-id / monitoring-health
- Article date: 2024-11-04
- Summary: Microsoft Entra ID で条件付きアクセス ギャップ アナライザー ブックを使用してリソースを適切に保護する方法について説明します。

Microsoft Entra ID では、条件付きアクセス ポリシーを構成することでリソースへのアクセスを保護できます。 IT 管理者は、リソースが適切に保護されているようにするため、条件付きアクセス ポリシーが予期したとおりに機能することを確認する必要があります。 条件付きアクセス ギャップ アナライザー ブックを使用すると、条件付きアクセスの実装におけるギャップを検出できます。

この記事では、 **条件付きアクセス ギャップ アナライザー** ブックの概要について説明します。

### 前提条件

Microsoft Entra ID 用の Azure Workbooks を使用するには、次のものが必要です。

- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つ Microsoft Entra テナント
- Log Analytics ワークスペース *とその* ワークスペースへのアクセス
- Azure Monitor *および* Microsoft Entra ID の適切なロール

#### Log Analytics ワークスペース

Microsoft Entra ブックを使用する*前*に、[Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)を作成する必要があります。 Log Analytics ワークスペースへのアクセスはいくつかの要因によって決まります。 ワークスペース *と* データを送信するリソースに適したロールが必要です。

詳細については、「 [Log Analytics ワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には、監視データを表示し、監視設定を編集するための [2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader) が用意されています。 Azure ロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics 組み込みロールも用意されています。

- **表示**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定の表示と変更**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内の Microsoft Entra ID ログ データを表示したり、Log Analytics からデータのクエリを実行したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 更新アクセスにより、Microsoft Entra データを Log Analytics ワークスペースに送信するための診断設定を作成および編集する機能が追加されます。

- **読み取り**:

    - レポート閲覧者
    - セキュリティ閲覧者
    - グローバル閲覧者
- **更新:**

    - セキュリティ管理者

Microsoft Entra 組み込みロールの詳細については、「 [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Log Analytics RBAC ロールの詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#log-analytics-contributor)参照してください。

### 説明

[Image: ワークブック カテゴリ]

IT 管理者は、適切なユーザーだけがリソースにアクセスできるようにする必要があります。 Microsoft Entra 条件付きアクセスは、この目標を達成するのに役立ちます。

条件付きアクセス ギャップ アナライザー ブックは、条件付きアクセス ポリシーが予期したとおりに機能することを検証する助けとなります。

**このブックでは:**

- 条件付きアクセス ポリシーが適用されていないユーザー サインインが強調表示されます。
- 条件付きアクセス ポリシーから意図せず除外されたユーザー、アプリケーション、または場所が存在しないことを確認できます。

### ブックにアクセスする方法

1. 適切なロールの組み合わせを使用して [、Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** の順に移動します。
3. **条件付きアクセス ギャップ アナライザー** ブックを **[条件付きアクセス]** セクションから選択します。

### ブックのセクション

ブックには 4 つのセクションがあります。

- レガシ認証を使用してサインインしているユーザー
- 条件付きアクセス ポリシーの影響を受けていないアプリケーションごとのサインインの回数
- 条件付きアクセス ポリシーをバイパスしている高リスクのサインイン イベント
- 条件付きアクセス ポリシーの影響を受けなかった場所ごとのサインインの回数

[Image: 場所別の条件付きアクセスの対象範囲]

これらの各傾向により、サインインの内訳がユーザー レベルまで明らかになるため、シナリオごとに、どのユーザーが条件付きアクセスをバイパスしているかを確認できます。

### フィルター

このブックでは、時間範囲フィルターの設定がサポートされています。

[Image: 時間範囲フィルター]

### ベスト プラクティス

このブックを使用して、テナントが以下の条件付きアクセスのベスト プラクティスのために構成されていることを確認します。

- レガシ認証のサインインをすべてブロックする
- すべてのアプリケーションに、少なくとも 1 つの条件付きアクセス ポリシーを適用する
- 高リスクのサインインをすべてブロックする
- 信頼されていない場所からのサインインをブロックする
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/workbook-cross-tenant-access-activity"} -->
## テナント間アクセス アクティビティ ブック - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-cross-tenant-access-activity
- Service: entra-id / monitoring-health
- Article date: 2024-11-04
- Summary: Microsoft Entra ID でテナント間アクセス アクティビティ ブックを使用して、外部ユーザーがアクセスしているリソースを監視する方法について説明します。

IT 管理者は、自分のユーザーが他の組織とどのように共同作業しているかに関する分析情報を必要とします。 テナント間アクセス アクティビティ ブックを使用すると、自分の組織内のリソースにアクセスしている外部ユーザーと、 どの組織のリソースに自分のユーザーがアクセスしているかを把握できます。 このブックでは、組織のすべての受信と送信のコラボレーションが 1 つのビューに結合されます。

この記事では、**テナント間アクセス アクティビティ** ブックの概要について説明します。

### 前提条件

Microsoft Entra ID 用の Azure Workbooks を使用するには、次のものが必要です。

- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)がある Microsoft Entra テナント
- Log Analytics ワークスペース*と*そのワークスペースへのアクセス
- Azure Monitor *および* Microsoft Entra ID の適切なロール

#### Log Analytics ワークスペース

Microsoft Entra ブックを使用する[前](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)に *Log Analytics ワークスペース* を作成する必要があります。 Log Analytics ワークスペースへのアクセスはいくつかの要因によって決まります。 ワークスペース*と*データを送信するリソースに対する適切なロールが必要です。

詳細については、「[Log Analytics ワークスペースへのアクセスを管理する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には、監視データを表示し、監視設定を編集するための [2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader)が用意されています。 Azure ロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics 組み込みロールも用意されています。

- **表示**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定の表示および変更**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内の Microsoft Entra ID ログ データを表示したり、Log Analytics からデータのクエリを実行したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 更新アクセスにより、Microsoft Entra データを Log Analytics ワークスペースに送信するための診断設定を作成および編集する機能が追加されます。

- **読み取り**:

    - レポート閲覧者
    - セキュリティ閲覧者
    - グローバル閲覧者
- **更新**:

    - セキュリティ管理者

Microsoft Entra の組み込みロールの詳細については、「[Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Log Analytics RBAC ロールの詳細については、「[Azure 組み込みロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#log-analytics-contributor)」を参照してください。

### 説明

[Image: このブックが [使用状況] カテゴリにあることを示す画像]

テナント間アクセスを制御するポリシーに変更を加えるテナント管理者は、ポリシーを変更する前に、このブックを使用して既存のアクセス アクティビティ パターンを視覚化および確認できます。 たとえば、自分のユーザーが外部の組織内でアクセスしているアプリケーションを特定し、重要なビジネス プロセスを誤ってブロックしないようにすることができます。 外部ユーザーがご自分のテナント内のリソースにアクセスする (受信アクセス) 方法と、そのテナント内のユーザーが外部テナントのリソースにアクセスする (送信アクセス) 方法を理解すると、適切なテナント間ポリシーを確実に設定できます。

詳細については、「[Microsoft Entra 外部 ID のドキュメント](https://learn.microsoft.com/ja-jp/entra/external-id/)」を参照してください。

### ブックにアクセスする方法

1. 適切なロールの組み合わせを使って [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** の順に移動します。
3. **[使用状況]** セクションから **[テナント間アクセス アクティビティ]** ブックを選択します。

### ブックのセクション

このブックには、次の 4 つのセクションがあります。

- テナント ID 別のすべての受信および送信アクティビティ
- 受信と送信のコラボレーションを目的としたサインインの状態の概要 (テナント ID 別)
- 受信と送信のコラボレーションを目的としてアクセスされたアプリケーション (テナント ID 別)
- 受信と送信のコラボレーション用の個々のユーザー (テナント ID 別)

自分のテナントに対してテナント間アクセス アクティビティを行った外部テナントの総数が、ブックの上部に表示されます。

[Image: ブックの最初のセクションのスクリーンショット。]

**外部テナント**のリストでは、自分のテナントに対して受信または送信アクティビティを行ったすべてのテナントが表示されます。 表で外部テナントを選択すると、表の残りのセクションには、そのテナントの送信および受信アクティビティに関する情報が表示されます。

[Image: 外部テナントの一覧のスクリーンショット。]

一覧から送信アクティビティのある外部テナントを選択すると、関連付けられている詳細が **[送信アクティビティ]** に表示されます。 受信アクティビティのある外部テナントを選択した場合も同様です。 **[受信アクティビティ]** タブを選択すると、受信アクティビティのある外部テナントの詳細が表示されます。

[Image: 送信と受信のオプションが強調表示されている、送信および受信アクティビティのスクリーンショット。]

送信アクティビティのある外部テナントを表示すると、後続の 2 つのテーブルにアプリケーションの詳細が表示され、ユーザー アクティビティが表示されます。 受信アクティビティのある外部テナントを表示すると、同じテーブルに受信アプリケーションとユーザー アクティビティが表示されます。 これらのテーブルは動的であり、以前に選択されたものに基づいているため、正しいテナントとアクティビティが表示されていることを確認するようにしてください。

### フィルター

このブックでは、複数のフィルターがサポートされています。

- 時間の範囲 (最大 90 日間)
- 外部テナント ID
- ユーザー プリンシパル名
- アプリケーション
- サインインの状態 (成功または失敗)

[Image: ブック フィルターを示すスクリーンショット]

### ベスト プラクティス

このブックを使用して次のことを行います。

- 正当なコラボレーションを損なうことなく、テナント間アクセス設定を効果的に管理するために必要な情報を取得する
- 外部の Microsoft Entra 組織からのすべての受信サインインを特定する
- 外部の Microsoft Entra 組織への自分のユーザーによる送信サインインをすべて特定する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/workbook-legacy-authentication"} -->
## レガシ認証ブックを使用したサインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-legacy-authentication
- Service: entra-id / monitoring-health
- Article date: 2024-11-04
- Summary: Microsoft Entra ID のレガシ認証ブックを使うサインインを使用して、従来の方法を使ってアプリを特定する方法について説明します。

テナントでレガシ認証をオフにしても安全かどうかを判断する方法について疑問に思ったことはありますか? レガシ認証ブックを使用したサインインは、この質問に答えるのに役立ちます。

この記事では、**レガシ認証ブックを使ったサインイン**の概要について説明します。

### 前提条件

Microsoft Entra ID 用の Azure Workbooks を使用するには、次のものが必要です。

- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)がある Microsoft Entra テナント
- Log Analytics ワークスペース*と*そのワークスペースへのアクセス
- Azure Monitor *および* Microsoft Entra ID の適切なロール

#### Log Analytics ワークスペース

Microsoft Entra ブックを使用する[前](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)に、*Log Analytics ワークスペース*を作成する必要があります。 Log Analytics ワークスペースへのアクセスはいくつかの要因によって決まります。 ワークスペース*と*データを送信するリソースに対する適切なロールが必要です。

詳細については、「[Log Analytics ワークスペースへのアクセスを管理する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には、監視データを表示し、監視設定を編集するための [2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader)が用意されています。 Azure ロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics 組み込みロールも用意されています。

- **表示**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定の表示および変更**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内の Microsoft Entra ID ログ データを表示したり、Log Analytics からデータのクエリを実行したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 更新アクセスにより、Microsoft Entra データを Log Analytics ワークスペースに送信するための診断設定を作成および編集する機能が追加されます。

- **読み取り**:

    - レポート閲覧者
    - セキュリティ閲覧者
    - グローバル閲覧者
- **更新**:

    - セキュリティ管理者

Microsoft Entra の組み込みロールの詳細については、「[Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Log Analytics RBAC ロールの詳細については、「[Azure 組み込みロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#log-analytics-contributor)」を参照してください。

### 説明

[Image: ブック サムネイルのスクリーンショット。]

Microsoft Entra ID では、レガシ認証を含め、最も広く使用されている認証および承認のプロトコルを複数サポートしています。 レガシ認証とは、クライアントを介して ID プロバイダーにユーザー名とパスワードの情報を渡す、広く使用されている業界標準の方法の基本認証を指します。

通常は、レガシ認証を使用する、または、これのみを使用するアプリケーションの例を次に示します。

- Microsoft Office 2013 以前。
- POP、IMAP、SMTP AUTH などのメールプロトコルでレガシ認証を使用しているアプリ。

単一要素認証 (ユーザー名やパスワードなど) では、今日のコンピューティング環境に必要な保護レベルは提供されません。 パスワードは、簡単に推測できるうえに、適切なパスワードの選定は難しいため、不十分です。

残念ながら、レガシ認証では:

- 多要素認証 (MFA) や他の強力な認証方法はサポートされていません。
- 組織がパスワードレス認証に移行することは不可能になります。

Microsoft Entra テナントのセキュリティとユーザー エクスペリエンスを向上させるためには、レガシ認証を無効にする必要があります。 ただし、テナントの重要なユーザー エクスペリエンスは、レガシ認証に依存している場合があります。 レガシ認証をオフにする前に、それらのケースを見つけて、より安全な認証に移行することができます。

**レガシ認証を使用したサインイン** ブックを使うと、環境内のすべてのレガシ認証のサインインを表示できます。 このブックは、レガシ認証の使用を停止する前に、重要なワークフローを見つけて、より安全な認証方法に移行するのに役立ちます。

### ブックにアクセスする方法

1. 適切なロールの組み合わせを使って [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** の順に移動します。
3. **[使用状況]** セクションで、**[レガシ認証を使用したサインイン]** ブックを選択します。

### ブックのセクション

このブックを使用すると、対話型サインインと非対話型サインインを区別できます。このブックでは、テナント全体でどのレガシー認証プロトコルが使用されているかを明らかにします。

データ収集は、次の 3 つのステップで構成されます。

1. レガシ認証プロトコルを選択し、アプリケーションを選択すると、そのアプリケーションにアクセスするユーザーでフィルタリングされます。
2. ユーザーを選択すると、選択したアプリに対するレガシ認証によるすべてのサインインが表示されます。
3. レガシ認証の使用方法を理解するために、ユーザーのすべてのレガシ認証サインインを表示します。

### フィルター

このブックでは、複数のフィルターがサポートされています。

- 時間の範囲 (最大 90 日間)
- ユーザー プリンシパル名
- アプリケーション
- サインインの状態 (成功または失敗)

[Image: フィルター オプション]

### ベスト プラクティス

- お客様の環境でレガシ認証をブロックするためのガイダンスについては、「[条件付きアクセスを使用して Microsoft Entra ID へのレガシ認証をブロックする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication)」を参照してください。
- かつてレガシ認証に依存していた多くの電子メールプロトコルは、現在ではより安全な最新の認証方式をサポートしています。 このブックにレガシ電子メール認証プロトコルが表示される場合は、代わりに電子メールの最新の認証への移行を検討してください。 詳細については、[Exchange Online での基本認証の非推奨](https://learn.microsoft.com/ja-jp/exchange/clients-and-mobile-in-exchange-online/deprecation-of-basic-authentication-exchange-online)に関するページをご覧ください。
- 一部のクライアントでは、クライアントの構成に応じて、レガシ認証と最新の認証の両方を使用できます。 クライアントの「最新のモバイル/デスクトップ クライアント」または「ブラウザー」が Microsoft Entra ログに表示される場合は、先進認証が使用されています。 「Exchange ActiveSync」などの特定のクライアントまたはプロトコル名がある場合は、Microsoft Entra ID への接続にレガシ認証が使用されています。 条件付きアクセスのクライアントの種類と、Microsoft Entra 管理センター の Microsoft Entra レポート ページでは、最新の認証クライアントとレガシ認証クライアントを区別しており、レガシ認証だけがこのブックにキャプチャされます。
- ID\*保護\*の詳細については、「[ID*保護*とは](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)」を参照してください。
- Microsoft Entra ブックの詳細については、「[Microsoft Entra ブックの使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/workbook-mfa-gaps"} -->
## 多要素認証ギャップ ブック - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-mfa-gaps
- Service: entra-id / monitoring-health
- Article date: 2024-11-04
- Summary: Microsoft Entra ID で MFA ギャップ ブックを使用して、MFA によって保護されていないアプリとユーザーを識別する方法について説明します。

多要素認証ギャップ ブックは、多要素認証 (MFA) 要件によって保護されていないユーザー サインインとアプリケーションを識別するのに役立ちます。 このブックでは:

- MFA 要件によって保護されていないユーザー サインインを識別します。
- アプリケーション、オペレーティング システム、場所などのさまざまなピボットを使用して、さらにドリルダウン オプションを提供します。
- ユーザーとアプリケーションを絞り込むための信頼できる場所やデバイスの状態など、いくつかのフィルターを提供します。
- ユーザーとアプリケーションのサブセットのブックのスコープを設定するフィルターを提供します。

この記事では、**多要素認証のギャップ**ワークブックの概要について説明します。

### 前提条件

Microsoft Entra ID 用の Azure Workbooks を使用するには、次のものが必要です。

- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つ Microsoft Entra テナント
- Log Analytics ワークスペース *とその* ワークスペースへのアクセス
- Azure Monitor *および* Microsoft Entra ID の適切なロール

#### Log Analytics ワークスペース

Microsoft Entra ブックを使用する*前*に、[Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)を作成する必要があります。 Log Analytics ワークスペースへのアクセスはいくつかの要因によって決まります。 ワークスペース *と* データを送信するリソースに適したロールが必要です。

詳細については、「 [Log Analytics ワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には、監視データを表示し、監視設定を編集するための [2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader) が用意されています。 Azure ロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics 組み込みロールも用意されています。

- **表示**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定の表示と変更**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内の Microsoft Entra ID ログ データを表示したり、Log Analytics からデータのクエリを実行したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 更新アクセスにより、Microsoft Entra データを Log Analytics ワークスペースに送信するための診断設定を作成および編集する機能が追加されます。

- **読み取り**:

    - レポート閲覧者
    - セキュリティ閲覧者
    - グローバル閲覧者
- **更新:**

    - セキュリティ管理者

Microsoft Entra 組み込みロールの詳細については、「 [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Log Analytics RBAC ロールの詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#log-analytics-contributor)参照してください。

### ブックをインポートする方法

**MFA ギャップ** ワークブックは現在テンプレートとして使用できませんが、Microsoft Entra ワークブックの GitHub リポジトリからインポートできます。

1. 適切なロールの組み合わせを使用して [、Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** に移動します。
3. [ **+ 新規]** を選択します。
4. ページの上部にある **[詳細エディター]** ボタンを選択します。 JSON エディターが開きます。

    [Image: 新しいブック ページの [詳細エディター] ボタンのスクリーンショット。]
5. **多要素認証ギャップ** ブックの JSON ファイルを含む GitHub リポジトリにアクセスするには、次のリンクを使用します。

    - **多要素認証ギャップ JSON ファイルへの直接リンク**: https://github.com/microsoft/Application-Insights-Workbooks/blob/master/Workbooks/Azure%20Active%20Directory/MultiFactorAuthenticationGaps/MultiFactorAuthenticationGaps.workbook
    - GitHub リポジトリの *MultiFactorAuthenticationGaps.workbook* ファイルにいることを確認します。
6. GitHub リポジトリから JSON ファイル全体をコピーします。

    [Image: 階層リンクと [ファイルのコピー] ボタンが強調表示されている GitHub リポジトリのスクリーンショット。]
7. ブックの [詳細エディター] ウィンドウに戻り、既存のテキストの上に JSON ファイルを貼り付けます。
8. [ **適用** ] ボタンを選択します。 ブックの設定には少し時間がかかる場合があります。
9. [ **編集完了]** を選択し、[ **保存** ] ボタンを選択し、必要な情報を入力します。

    - **タイトル**、**サブスクリプション**、リソース グループ (選択した**リソース グループ**のブックを保存できる必要があります)、および**場所**を指定します。
    - 必要に応じて、ブックのコンテンツを [Azure Storage アカウント](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-bring-your-own-storage)に保存することを選択します。
10. [ **適用** ] ボタンを選択します。

### まとめ

概要ウィジェットでは、多要素認証に関連するサインインの詳細を確認できます。

#### アプリケーション別の MFA 要件によって保護されていないサインイン

- **アプリケーションによる多要素認証要件によって保護されていないサインインユーザーの数:** このウィジェットは、アプリケーションによって MFA 要件によって保護されていないユーザー サインインの数を時間ベースの棒グラフで表します。
- **アプリケーションによる多要素認証要件によって保護されていないサインインユーザーの割合:** このウィジェットは、アプリケーションによって MFA 要件によって保護されていないユーザー サインインの割合を示す時間ベースの棒グラフ表示を提供します。
- **詳細については、アプリケーションとユーザーを選択してください。** このウィジェットは、MFA を必要とせずにサインインした上位ユーザーをアプリケーション別にグループ化します。 アプリケーションを選択すると、ユーザー名と MFA なしのサインインの数が一覧表示されます。

#### ユーザー別の MFA 要件によって保護されていないサインイン

- **ユーザーによる多要素認証要件によって保護されていないサインイン:** このウィジェットには、上位のユーザーと、MFA 要件によって保護されていないサインインの数が表示されます。
- **多要素認証要件によって保護されていない認証の割合が高い上位ユーザー:** このウィジェットには、MFA 要件によって保護されていない認証の割合が最も高いユーザーが表示されます。

#### オペレーティング システム別の MFA 要件によって保護されていないサインイン

- **オペレーティング システムによる多要素認証要件によって保護されていないサインインの数:** このウィジェットは、デバイスのオペレーティング システムによって MFA によって保護されていないサインイン数の時間ベースの棒グラフを提供します。
- **オペレーティング システムによって多要素認証要件によって保護されていないサインインの割合:** このウィジェットは、デバイスのオペレーティング システムによって MFA によって保護されていないサインイン率の時間ベースの棒グラフを提供します。

#### 場所別の MFA 要件によって保護されていないサインイン

- **場所別の多要素認証要件によって保護されていないサインインの数:** このウィジェットでは、世界地図のマップ バブル チャートに、MFA 要件によって保護されていないサインイン数が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/workbook-risk-analysis"} -->
## ID* 保護*のリスク分析*ブック* - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-risk-analysis
- Service: entra-id / monitoring-health
- Article date: 2024-11-04
- Summary: Microsoft Entra ID の ID 保護のリスク分析ブックを使用してリスク ポリシーの傾向とギャップを調べる方法について説明します。

Microsoft Entra ID 保護では、侵害された ID を検出する、修復する、回避することができます。 IT 管理者\*は、ポリシー\*の構成\*を改善するために、組織\*と営業案件のリスク\*傾向を理解する必要があります。 ID\* 保護\* のリスク分析\*ブック\*を使用すると、ID\* 保護\* の実装に関してよく寄せられる質問に答えることができます。

この記事では、**Identity Protection リスク分析** ワークブックの概要を提供します。

### 前提条件

Microsoft Entra ID 用の Azure Workbooks を使用するには、次のものが必要です。

- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つ Microsoft Entra テナント
- Log Analytics ワークスペース *とその* ワークスペースへのアクセス
- Azure Monitor *および* Microsoft Entra ID の適切なロール

#### Log Analytics ワークスペース

Microsoft Entra ブックを使用する[前](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)に *Log Analytics ワークスペース* を作成する必要があります。 Log Analytics ワークスペースへのアクセスはいくつかの要因によって決まります。 ワークスペース *と* データを送信するリソースに適したロールが必要です。

詳細については、「 [Log Analytics ワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には、監視データを表示し、監視設定を編集するための [2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader) が用意されています。 Azure ロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics 組み込みロールも用意されています。

- **表示**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定の表示と変更**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内の Microsoft Entra ID ログ データを表示したり、Log Analytics からデータのクエリを実行したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 更新アクセスにより、Microsoft Entra データを Log Analytics ワークスペースに送信するための診断設定を作成および編集する機能が追加されます。

- **読み取り**:

    - レポート閲覧者
    - セキュリティ閲覧者
    - グローバル閲覧者
- **更新:**

    - セキュリティ管理者

Microsoft Entra 組み込みロールの詳細については、「 [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Log Analytics RBAC ロールの詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#log-analytics-contributor)参照してください。

### 説明

[Image: ワークブックカテゴリ]

IT 管理者は、ID の侵害から組織を確実に保護するために、ポリシー実装の ID リスクとギャップの傾向を理解する必要があります。 ID\* 保護\*のリスク分析\*ブック\*を使用すると、組織\*内のリスク\*の状態\*を分析できます。

**このワークブック:**

- 世界中のリスク\*が検出されている場所の視覚化を提供します。
- ブックを使用すると、リアルタイムの傾向とオフラインリスク検出を理解することができます。
- 危険なユーザー\*にどの程度効果があるかについての洞察を提供します。

### ブックにアクセスする方法

1. 適切なロールの組み合わせを使用して [、Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** の順に移動します。
3. **[使用法]** セクションの **[Identity Protection リスク分析]** ブックを選択します。

### ブックのセクション

このブック\*には、次の 5 つのセクションがあります：

- リスク検出\*のヒートマップ\*
- オフライン\*とリアルタイム\*のリスク検出\*
- リスク検出\*の傾向
- 危険なユーザー
- まとめ

### フィルター

このブックでは、時間範囲フィルターの設定がサポートされています。

[Image: 時間範囲フィルターの設定]

リスク検出\*の傾向と危険なユーザー\*のセクションには、さらにフィルター\*があります。

リスク検出\*の傾向：

- 検出\*のタイミングの種類 (リアルタイム\*またはオフライン\*)
- リスク レベル\* (低、中、高、またはなし)

危険なユーザー\*：

- リスク\*の詳細 (ユーザー\*のリスクレベル\*が変更されたことを示す)
- リスク レベル\* (低、中、高、またはなし)

### ベスト プラクティス

- **[危険なサインイン ポリシーを有効にする](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies#sign-in-risk-based-conditional-access-policy)** - 中リスク以上の多要素認証 (MFA) を求めるメッセージを表示します。 ポリシー\*を有効にすると、正当なユーザー\*が MFA\* によるリスク検出\*を自己修復できるようになるため、アクティブなリアルタイム\*リスク検出\*の割合が軽減されます。
- **[危険なユーザー ポリシーを有効にする](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#user-risk-policy-in-conditional-access)** - ユーザーが高リスクと見なされたときにアカウントを安全に修復できるようにします。 ポリシー\*を有効にする\*と、ユーザー\*の資格情報\*を安全な状態\*に戻すことによって、組織\*内のアクティブなリスク\*のあるユーザー\*の数を減らすことができます。
- ID 保護の詳細については、「ID 保護 [とは」を](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)参照してください。
- Microsoft Entra ワークブックの詳細については、「Microsoft Entra ワークブックの使用方法」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/monitoring-health/workbook-sensitive-operations-report"} -->
## 機密性の高い操作のレポート ブック - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-sensitive-operations-report
- Service: entra-id / monitoring-health
- Article date: 2024-11-04
- Summary: Microsoft Entra ID で機密性の高い操作レポート ブックを使用して、疑わしいアプリとサービス プリンシパルのアクティビティを調べる方法について説明します。

機密性の高い操作のレポート ブックは、環境内の侵害を示す可能性のある、疑わしいアプリケーションやサービス プリンシパルのアクティビティを識別するのに役立ちます。

この記事では、 **機密性の高い操作レポート** ブックの概要について説明します。

### 前提条件

Microsoft Entra ID 用の Azure Workbooks を使用するには、次のものが必要です。

- [Premium P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)を持つ Microsoft Entra テナント
- Log Analytics ワークスペース *とその* ワークスペースへのアクセス
- Azure Monitor *と* Microsoft Entra ID のロールの適切な選択

#### Log Analytics ワークスペース

Microsoft Entra ブックを使用する*前*に [Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)を作成する必要があります。 Log Analytics ワークスペースへのアクセスはいくつかの要因によって決まります。 ワークスペース *と* データを送信するリソースに適したロールが必要です。

詳細については、「 [Log Analytics ワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access)」を参照してください。

#### Azure Monitor ロール

Azure Monitor には、監視データを表示し、監視設定を編集するための [2 つの組み込みロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security#monitoring-reader) が用意されています。 Azure ロールベースのアクセス制御 (RBAC) には、同様のアクセスを付与する 2 つの Log Analytics 組み込みロールも用意されています。

- **表示**:

    - 監視リーダー
    - Log Analytics 閲覧者
- **設定の表示と変更**:

    - モニタリング貢献者
    - Log Analytics 共同作成者

#### Microsoft Entra ロール

読み取り専用アクセスでは、ブック内の Microsoft Entra ID ログ データを表示したり、Log Analytics からデータのクエリを実行したり、Microsoft Entra 管理センターでログを読み取ったりすることができます。 更新アクセスにより、Microsoft Entra データを Log Analytics ワークスペースに送信するための診断設定を作成および編集する機能が追加されます。

- **読み取り**:

    - レポート閲覧者
    - セキュリティ閲覧者
    - グローバル閲覧者
- **更新:**

    - セキュリティ管理者

Microsoft Entra 組み込みロールの詳細については、「 [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

Log Analytics RBAC ロールの詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#log-analytics-contributor)参照してください。

### 説明

[Image: ワークブックカテゴリ]

このブックは、テナント内で実行された最近の機密性の高い操作を識別します。

組織で Azure Monitor ブックを使用したことがない場合は、ブックにアクセスする前に、Microsoft Entra のサインインと監査のログを Azure Monitor と統合する必要があります。 この統合により、最長で 2 年間、ブックを使用してログの格納、クエリの実行、視覚化を実行することができます。 Azure Monitor との統合後に作成されたサインインと監査のイベントのみが格納されるため、ブックには、その日付より前の分析情報は含まれません。 詳細については、「 [Microsoft Entra ログと Azure Monitor の統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)」を参照してください。

### ブックにアクセスする方法

1. 適切なロールの組み合わせを使用して [、Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** を参照します。
3. **トラブルシューティング** セクションから **機密性の高い操作レポート** ブックを選択します。

### セクション

このブックは、4 つのセクションに分割されています。

[Image: ワークブックセクションのスクリーンショット。]

- **変更されたアプリケーションとサービス プリンシパルの資格情報/認証方法** - このレポートは、最近多くのサービス プリンシパル資格情報を変更したアクターと、変更された各種類のサービス プリンシパル資格情報の数にフラグを設定します。
- **サービス プリンシパルに付与された新しいアクセス許可** - このブックには、サービス プリンシパルに対して最近付与された OAuth 2.0 アクセス許可も強調表示されています。
- **サービス プリンシパルのディレクトリ ロールとグループ メンバーシップの更新**
- **変更されたフェデレーション設定** - ユーザーまたはアプリケーションがドメインのフェデレーション設定を変更すると、このレポートが強調表示されます。 たとえば、署名証明書など、Active Directory フェデレーション サービス (ADFS) の新しい TrustedRealm オブジェクトがドメインに追加された場合が報告されます。 ドメインのフェデレーション設定に対する変更は、めったにないはずです。

#### 変更されたアプリケーションとサービス プリンシパルの資格情報および認証方法

攻撃者が環境内でアクセスを獲得する最も一般的な方法の 1 つは、既存のアプリケーションやサービス プリンシパルに新しい資格情報を追加する方法です。 資格情報があれば、攻撃者は標的のアプリケーションまたはサービス プリンシパルとして認証して、それらに、アクセス許可を持つすべてのリソースへのアクセスを付与できます。

このセクションには、検出に役立つ以下のデータが含まれています。

- 資格情報の種類を含め、アプリやサービス プリンシパルに追加された新しい資格情報すべて
- 上位のアクターと、それらが実行した資格情報の変更の数
- すべての資格情報変更のタイムライン

#### サービス プリンシパルに付与された新しいアクセス許可

攻撃者は、侵入するための高特権の一連のアクセス許可を持つサービス プリンシパルやアプリケーションを見つけられない場合、別のサービス プリンシパルまたはアプリケーションへのアクセス許可を追加しようと試みます。

このセクションには、既存のサービス プリンシパルに AppOnly アクセス許可が付与された内訳が含まれています。 管理者は、Exchange Online、Microsoft Graph を含む、過剰に高いアクセス許可が付与されているすべてのインスタンスを調査する必要があります。

#### サービス プリンシパルのディレクトリ ロールとグループ メンバーシップの更新

攻撃者が既存のサービス プリンシパルやアプリケーションに新しいアクセス許可を追加する論理に従えば、もう 1 つのアプローチとなるのは、それらを既存のディレクトリ ロールまたはグループに追加することです。

このセクションには、サービス プリンシパルのメンバーシップに加えられたすべての変更の概要が含まれていて、高い特権を持つロールとグループへの追加はすべて確認する必要があります。

#### 変更されたフェデレーション設定

環境内に長期的な足がかりを得るための別の一般的なアプローチは、次のとおりです。

- テナントのフェデレーション ドメインでの信頼を変更する。
- 攻撃者が制御する別の SAML IDP を、信頼できる認証ソースとして追加する。

このセクションには以下のデータが含まれています。

- ドメイン フェデレーションによる既存の信頼に対して実行された変更
- 新しいドメインや信頼の追加

### フィルター

この段落では、各セクションでサポートされているフィルターの一覧を示します。

#### 変更されたアプリケーションとサービス プリンシパルの資格情報および認証方法

- 時間の範囲
- 操作名
- 資格情報
- 俳優
- アクターの除外

#### サービス プリンシパルに付与された新しいアクセス許可

- 時間の範囲
- クライアント アプリ
- リソース

#### サービス プリンシパルに対するディレクトリ ロールとグループ メンバーシップの更新

- 時間の範囲
- 操作
- 開始しているユーザーまたはアプリ

#### 変更されたフェデレーション設定

- 時間の範囲
- 操作
- 開始しているユーザーまたはアプリ

### ベスト プラクティス

- **変更されたアプリケーションとサービス プリンシパルの資格情報を使用して** 、組織で頻繁に使用されないサービス プリンシパルに追加される資格情報を確認します。 このセクションに記載されたフィルターを利用して、変更された疑いのあるアクターやサービス プリンシパルがないかさらに調査します。
- **サービス プリンシパルに付与された新しいアクセス許可を使用して** 、侵害される可能性のあるアクターによってサービス プリンシパルに追加される広範なアクセス許可または過剰なアクセス許可を確認します。
- **変更されたフェデレーション設定セクションを使用して** 、追加または変更されたターゲット ドメイン/URL が正当な管理者の動作であることを確認します。 ドメイン フェデレーションの信頼を変更または追加するアクションはまれであり、確かに適正であることをできるだけ早く調査するアクションとして扱う必要があります。
<!-- /MSL-PAGE -->
