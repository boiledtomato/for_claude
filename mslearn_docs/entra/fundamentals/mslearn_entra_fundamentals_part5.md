# Microsoft Learn — Microsoft Entra / 基礎・アーキテクチャ・標準・その他 (part 5)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 31

---

<!-- MSL-PAGE {"url":"entra/fundamentals/whats-new-archive"} -->
## Microsoft Entraリリースおよびお知らせのアーカイブ - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new-archive
- Service: entra / fundamentals
- Article date: 2026-03-05
- Summary: このコンテンツ セットの概要セクションにある新着情報リリース ノートには、6 か月間のアクティビティが含まれています。 6 か月後、項目はメイン記事から削除され、このアーカイブ記事に配置されます。

この記事には、6 か月 (最大 18 か月) より古い製品のMicrosoft Entra ファミリ全体のリリースと変更のお知らせに関する情報が含まれています。 最新の情報をお探しの場合は、[Microsoft Entra リリースとお知らせ](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)を参照してください。

より動的なエクスペリエンスを実現するために、Microsoft Entra 管理センターでアーカイブ情報を見つけることができます。 詳細については、「[新機能 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new-overview)」を参照してください。

### 2025 年 12 月

#### 一般提供 - Windows 11 の WebView2 を使用したMicrosoft Entra ID認証フローの最新化

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** SSO

Windowsには、Web ビューを使用して Web 情報を収集し、ネイティブ コンテンツのように見えるユーザーに Web 情報を表示する多くのユーザー エクスペリエンスがあります。 これに対する一般的なシナリオの 1 つは、ユーザーがユーザー名の入力を求め、資格情報を提供する認証フローです。

Web Account Manager (WAM) 経由の Microsoft Entra ID アプリ サインインでは、[KB5072033 (OS ビルド 26200.7462 および 26100.7462) 以降](https://support.microsoft.com/topic/december-9-2025-kb5072033-os-builds-26200-7462-and-26100-7462-0c1a4334-19ba-406d-bb1e-88fcffc87b79)、Chromium ベースの Web コントロールである WebView2 を使用するオプションが利用可能になりました。 このリリースは、アプリとサービス全体で、セキュリティで保護された最新の一貫性のあるサインイン エクスペリエンスを提供する上で重要な一歩を踏み出します。

WebView2 は、将来の Windows リリースで WAM 認証の既定のフレームワークになり、EdgeHTML WebView は非推奨になります。 そのため、ユーザーは今すぐデプロイしてオプトイン プロセスに参加し、環境でこのエクスペリエンスを有効にし、プロキシ ルールの更新やサインイン プロセスに関連するサービスのコードの変更など、必要な調整を行うようお勧めします。 フィードバックを提供する場合は、カスタマー サポート サービスにお問い合わせください。

WebView2 への移行は技術的なアップグレードではなく、セキュリティで保護されたユーザー フレンドリな ID エクスペリエンスへの戦略的投資です。 最新の組織や開発者のニーズを満たすために、Microsoft Entra IDの進化に取り組んでいます。

詳細については次を参照してください:

[一般提供開始: Windows 11の WebView2 を使用してMicrosoft Entra ID認証フローを最新化する - IT Pro ブログWindows](https://techcommunity.microsoft.com/blog/windows-itpro-blog/now-generally-available-modernizing-microsoft-entra-id-auth-flows-with-webview2-/4476166)

#### 一般提供 - ユーザー アカウントの乗っ取りを防止するための Microsoft Entra Connect のセキュリティ強化

**タイプ:** 固定**サービス カテゴリ:** Entra Connect**製品機能:** アクセス制御

Microsoft Entra Connect がActive Directoryから新しいオブジェクトを追加すると、Microsoft Entra ID サービスは、受信オブジェクトの [sourceAnchor 値を、既存のクラウド マネージド オブジェクトの OnPremisesImmutableId 属性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant#hard-match-vs-soft-match)に対して検索することで、Entra オブジェクトとの照合を試みます。Microsoft Entra ID。 一致するものがある場合、Microsoft Entra Connect Sync はそのオブジェクトのソースまたは機関 (SoA) を引き継ぎ、"ハードマッチ" と呼ばれる受信Active Directory オブジェクトのプロパティで更新します。

継続的なセキュリティ強化の一環として、Microsoftでは、Microsoft Entra Connect に強制変更を導入し、ハード マッチの悪用によるアカウント引き継ぎのリスクを軽減します。 この変更の適用は **、2026 年 7 月 1 日**に開始されます。

**変更点:**

- Microsoft Entraは、同期されたユーザー オブジェクトにマップされた後、Entra Connect による OnPremisesObjectIdentifier 属性の変更の試行をブロックします。 これにより、既存のEntra ID ユーザーを別のオンプレミス ID に再マッピングできなくなります。
- [監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities#core-directory) が強化され、OnPremisesObjectIdentifier と DirSyncEnabled への変更がキャプチャされ、同期動作の可視性が向上しました。
- [legitimate](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-migrate-groups)既存の同期された Entra オブジェクトを別のオンプレミス オブジェクトに再マップする必要があるシナリオをサポートするために、Microsoftでは、ハードマッチの不正使用や未承認の再マッピングを再度有効にすることなく、制御された回復アクションを許可するMicrosoft Graph APIが導入されました。
- ユーザーの OnPremisesObjectIdentifier フィールドをリセットすると、後続の同期ジョブには影響しません。 つまり、クラウド同期と接続の両方の同期クライアントは、問題なくリセットされたユーザー オブジェクトの同期を続行できます。 そのフィールドが null に設定された後にユーザー オブジェクトが同期されるたびに、新しい GUID が割り当てられます。

**変更されない内容:**

- この適用は、OnPremisesObjectIdentifier が (ハードマッチによって) 別のオンプレミス オブジェクトに再マップされたために、同期されたオブジェクトに対して変更されるシナリオにのみ適用されます。 [onPremisesImmutableId](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#sourceanchor) を使用したクラウド オブジェクトのハード マッチと引き継ぎは、引き続きサポートされ、変更されません。

**お客様のアクションが必要です。**

- 更新されたセキュリティ強化ガイダンスを確認し、実施します。これには、必要に応じてハードマッチの引き継ぎを無効にするための推奨フラグも含まれます。
- OnPremisesObjectIdentifier に対する最近の変更について監査ログを確認して、影響を受ける可能性のあるユーザーを特定します。 影響を受けるユーザーについては、Microsoft Entra Connect Sync [エラー コード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sync-errors#existing-admin-role-conflict)を参照してください
- Graph API ベースの新しい復旧フローをテストして、**July 1、2026** に適用が開始される前に準備ができていることを確認します。

**Microsoft Graph API for Recovery**

**2026 年 7 月 1 日以降、**Entra 内の既存の同期オブジェクトを別のオンプレミス オブジェクトに再マップしようとする同期操作は、次のエラーで失敗します。

"*セキュリティの強化により、ハード マッチ操作がブロックされました。OnPremisesObjectIdentifier マッピングを確認します。*"

お客様は、最初に Entra オブジェクトの OnPremisesObjectIdentifier プロパティをクリアしてから、ハードマッチ操作と引き継ぎ操作を再試行することで復旧できます。

ユーザーの OnPremisesObjectIdentifier をクリアするには、次の Microsoft Graph API呼び出しを使用します。

`PATCH https://graph.microsoft.com/beta/users/{userId}`

本文は次のようになります。

```
{
onPremisesObjectIdentifier: null
}
```

必要なアクセス許可:

- 委任されたアクセス許可またはアプリケーションアクセス許可: "**User-OnPremisesSyncBehavior.ReadWrite.All**"
- 呼び出し元には、**グローバル管理者**または**ハイブリッド ID 管理者**のいずれかのロールも必要です。
- アプリに **User-OnPremisesSyncBehavior.ReadWrite.All** が付与されていない場合、グローバル管理者またはハイブリッド管理者を含むすべてのユーザーは、MS グラフを介してフィールドをリセットできません

注

この API では、OnPremisesObjectIdentifier (null に設定) のクリアのみが許可されます。 他の値に設定しようとするとブロックされます。

**その他のガイダンス:**

- 強制適用によって操作がブロックされると、次のエラー メッセージが返されます: “*セキュリティ強化により、ハード マッチ操作はブロックされました。OnPremisesObjectIdentifier のマッピングを確認してください。*”
- 監査ログを使用して、影響を受けるオブジェクトを特定します。 OnPremisesObjectIdentifier が変更された "*Update user*" イベントを探します。 これらのユーザーは、強制が開始される前に修復が必要になる場合があります。

Microsoft Entra Connect Sync .msi インストール ファイルは、Microsoft Entra 管理センター [Microsoft Entra Connect](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) でのみ使用できます。 利用可能なバージョンの詳細については、 [バージョン履歴ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) を参照してください。

#### パブリック プレビュー - Microsoft Entra 外部 ID へのジャストインタイム パスワード移行

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Just-In-Time (JIT) パスワード移行機能は、Microsoft Entra 外部 IDに移行するお客様にシームレスで安全なエクスペリエンスを提供するように設計されています。 この機能により、外部 ID プロバイダーはサインイン時にユーザー資格情報を移行できるため、パスワードの一括リセットが不要になり、エンド ユーザーの中断が最小限に抑えられます。 ユーザーがサインイン時に移行条件を満たすと、プロセスの一環として資格情報が安全に転送され、継続性が確保され、摩擦が軽減されます。

認証フローに移行を統合することで、組織はセキュリティ標準を維持しながら管理タスクを簡素化できます。 このアプローチは、ユーザー エクスペリエンスを向上させるだけでなく、運用効率を損なうことなくMicrosoft Entra 外部 IDの導入を促進します。

#### パブリック プレビュー - Prompt Shield を使用してエンタープライズ生成 AI アプリケーションを保護する

**種類:** 新機能**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

ユニバーサル ポリシー制御を使用してエンタープライズ GenAI アプリへのプロンプトインジェクション攻撃をリアルタイムでブロックし、Azure AI Prompt Shield をすべてのネットワーク トラフィックに拡張します。 詳細については、「 [エンタープライズ生成 AI アプリを迅速なインジェクション保護で保護する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-ai-prompt-injection-protection)」を参照してください。

#### パブリック プレビュー - Global Secure Access での B2B ゲスト アクセス対応

**種類:** 新機能**サービス カテゴリ:** B2B**製品の機能:** ネットワークアクセス

ホーム組織のMicrosoft Entra ID アカウントにサインインしたグローバル セキュア アクセス クライアントを使用して、ゲスト ユーザーの B2B ゲスト アクセス機能を有効にできるようになりました。 グローバル セキュリティで保護されたアクセス クライアントは、ユーザーがゲストであるパートナー テナントを自動的に検出し、顧客のテナント コンテキストに切り替えるオプションを提供します。 クライアントは、顧客の Global Secure Access サービスを介してプライベート トラフィックのみをルーティングします。 詳細については、[Global Secure Access の外部ユーザー アクセス (プレビュー) について](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-external-user-access)を参照してください。

#### パブリック プレビュー - Entra のMicrosoft Security Copilotを使用したデータ探索

**種類:** 新機能**サービス カテゴリ:** 該当なし**製品の機能:** ID のセキュリティと保護

Microsoft EntraのMicrosoft Security Copilotでは、プロンプトが 10 個を超える項目を含むデータセットを返すときのデータ探索がサポートされるようになりました。 この機能はプレビュー段階であり、一部の Microsoft Entra シナリオで使用できます。 Copilot チャットの応答から、[ **一覧を開く** ] を選択して、包括的なデータ グリッドにアクセスします。 これにより、完全で正確な結果を持つ大規模なデータセットを探索でき、より効率的な意思決定が可能になります。 各データ グリッドには基になる Microsoft Graph URL が表示され、クエリの精度を確認し、結果の信頼性を高めることができます。 詳細については、[Microsoft Entra における Microsoft Security Copilot シナリオの概要](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-scenarios)を参照してください。

### 2025 年 11 月

#### パブリック プレビュー - Microsoft Entra ID アカウントの回復

**種類:** 新機能**サービス カテゴリ:** 検証済み ID**製品の機能:** ID のセキュリティと保護

Microsoft Entra ID アカウントの回復は、登録されているすべての認証方法へのアクセスを失ったときに、ユーザーが組織のアカウントへのアクセスを回復できるようにする高度な認証回復メカニズムです。 従来のパスワードリセット機能とは異なり、アカウント復旧は単なる認証情報回復ではなく、認証方法の置き換え前の本人確認と信頼の再確立に焦点を当てています。 詳細については、「[Microsoft Entra IDアカウントの回復の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-account-recovery-overview)を参照してください。

#### パブリックプレビュー - パスワードレスユーザー向けのセルフリメディエーション

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

**パスワードレス ユーザーの自己修復:** Microsoft Entra 条件付きアクセス のリスクベースのアクセス ポリシーでは、パスワードレスを含むすべての認証方法にわたるリスクの自己修復がサポートされるようになりました。 この新しいコントロールにより、侵害されたセッションはリアルタイムで取り消され、摩擦のないセルフサービスが可能になり、ヘルプデスクの負担も軽減されます。 詳細については、「 [リスク修復制御を要求する」](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies#require-risk-remediation-control)を参照してください。

#### 一般公開 - 外部ID地域拡張(オーストラリアおよび日本)

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

**Go‑Local add‑on** により、External ID データを **その地域内で保存および処理** できる **オーストラリア** と **日本** に、Microsoft Entra 外部 ID を拡張しています。 このプレミアムアドオンは、新しい外部IDテナントを作成する際に選択可能で、厳格な **データレジデンシー** 要件を持つ組織向けに設計されています。 一部の中央集権型プラットフォームサービス(例:MFA/RBAC機能)はグローバルに維持されており、セキュリティやコンプライアンスの状況に変化はありません。 **Get started:** **Australia** または ** Japan** で新しいテナントを作成し、アドオンにオプトインするか、Microsoft担当者に連絡して、既存の環境のオプションについて話し合います。 詳細については、「[Microsoft Entra ID とデータ所在地](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency)」を参照してください>

#### 一般公開 - グループプロビジョニングをサポートする新しいSCIM 2.0 SAP CISコネクタが利用可能です

**種類:** 新機能**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** SaaS アプリケーションへの送信

更新された SCIM 2.0 SAP Cloud Identity Services (CIS) コネクタが、2025 年 9 月 30 日に Microsoft Entra アプリ ギャラリーにリリースされました。 これは従来のSAP CISプロビジョニング統合を置き換え、SAP CISへのプロビジョニングおよびデプロビジョニンググループ、カスタム拡張属性、OAuth 2.0クライアント認証情報付与のサポートを提供しています。 詳細については、「 Microsoft Entra IDを参照してください。

#### パブリック プレビュー - カスタム拡張機能を使用してaccess パッケージの承認要件を外部で決定する

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

エンタイトルメント管理では、accessパッケージ割り当て要求の承認者を直接割り当てるか、動的に決定することができます。 エンタイトルメント管理は、要求元マネージャー、第 2 レベルのマネージャー、接続された組織のスポンサーなどの承認者を動的に決定することをネイティブにサポートします。 この機能の導入によって、Azure Logic Appsのコールアウト用のカスタム拡張機能を使用し、組織の特定のビジネス ロジックに基づいて各アクセス パッケージ割り当て要求の承認要件を動的に決定できるようになりました。 アクセス パッケージの割り当て要求プロセスは、Azure Logic Appsでホストされているビジネス ロジックが承認ステージを返すまで一時停止し、その後の承認プロセスでマイ アクセス ポータル経由で利用されます。 詳細については、「[カスタム拡張機能を使用してaccess パッケージの承認要件を厳密に決定する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-dynamic-approval)を参照してください。

#### 一般提供開始 - エンタイトルメント管理のアクセス パッケージにおける対象となるグループ メンバーシップおよび所有権のサポート

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

エンタイトルメント管理とグループのPrivileged Identity Management (PIM) の統合により、アクセス パッケージを介して対象のグループ メンバーシップと所有権を割り当てるためのサポートが追加されます。 セルフサービスのaccess要求と拡張プロセスを提供し、それらを組織のロール モデルに統合することで、これらの Just-In-Time access 割り当てを大規模に管理できるようになりました。 詳細については、「[Privileged Identity Management for Groups を使用してアクセス パッケージで対象となるグループ メンバーシップと所有権を割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-eligible)」を参照してください。

#### 一般公開 - 失敗したユーザーとワークフローをライフサイクルワークフローで再処理

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローでは、ワークフローの再処理がサポートされるようになりました。これにより、組織は、エラーやエラーが検出されたときにワークフローの再処理を効率化できます。 この機能には、失敗した実行や再度処理したい実行を含め、過去のワークフローの再処理機能が含まれています。 お客様は、ニーズに合わせて次のオプションから選択できます。

- 再処理する特定のワークフロー実行を選択する
- ワークフロー実行から再処理するユーザーを選択してください。例えば、失敗したユーザーや実行中の全ユーザー

詳細は [「再処理ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/reprocess-workflow)」をご覧ください。

#### 一般公開 - Groups Purview の感度ラベルサポートはライフサイクル ワークフローで提供されています

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

顧客は現在、ライフサイクルワークフローでグループやTeamsに割り当てられたPurviewの感度ラベルを確認できます。 グループやTeamsの割り当て管理のためのワークフロータスクを設定する際、管理者は現在、情報に基づいたグループ選択の意思決定を支援するために、アクティブに割り当てられたセンシティシティラベルを目にすることになります。 これにより、顧客はより強固な組織コンプライアンスを達成することができます。 詳細については [、ライフサイクルワークフローにおける感度ラベル](https://learn.microsoft.com/ja-jp/entra/id-governance/workflow-sensitivity-labels)をご覧ください。

#### 一般公開 - ライフサイクルワークフローで非アクティブな従業員やゲストのワークフローをトリガー

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクルワークフローは、サインイン非アクティブに基づいてアイデンティティライフサイクルアクションを自動化することで、顧客が休眠状態のユーザーアカウントを積極的に管理するためのカスタムワークフローを設定できるようにします。 非アクティブを検出することで、ユーザーが非アクティブの閾値を超えた際に、通知の送信、アカウントの無効化、オフボーディングの開始など、事前に定められたタスクを自動的に実行します。 管理者は非活動の閾値と範囲を設定でき、休眠アカウントを効率的かつ一貫して管理することが可能です。これにより、セキュリティリスクの低減、ライセンスの無駄削減、大規模なガバナンスポリシーの適用が可能になります。 詳細については、以下をご覧ください: [ライフサイクルワークフローを用いた非アクティブユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-inactive-users)。

#### パブリック プレビュー - Microsoft Entra IDのパスキー プロファイル

**種類：** 機能の変更**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra IDでは、グループ ベースのパスキー (FIDO2) 構成がサポートされるようになりました。これにより、さまざまな種類のパスキーを異なるユーザー セットに個別にロールアウトできるようになりました。 詳細については、「 Microsoft Entra ID (プレビュー)を参照してください。

#### パブリックプレビュー - クラウドセキュリティグループのソフト削除

**種類:** 新機能**サービス カテゴリ:** グループ管理**製品の機能:** ID のセキュリティと保護

クラウドセキュリティグループのソフト削除は、管理者が **30日間の保持期間**内に削除されたグループを回復できる安全メカニズムを導入します。 クラウドセキュリティグループが削除された場合でも、すぐにディレクトリから削除されるわけではありません。代わりにソフト削除状態に入り、メンバーシップと構成を保持します。 この機能は誤ったデータ損失を防ぎ、手動の再構築を必要とせずにグループの迅速な復元を可能にすることでビジネスの継続性を支援します。 管理者は、保持期間中にMicrosoft Entra 管理センターまたは Microsoft Graph APIを使用して、論理的に削除されたグループを復元できます。

#### Public Preview - 管理エージェントアイデンティティのエンドユーザーエクスペリエンス

**種類:** 新機能**サービス カテゴリ:** 他**製品の機能:** エンド ユーザー エクスペリエンス

「エージェントのエンドユーザーエクスペリエンス管理」では、所有またはスポンサーのエージェントIDを閲覧・管理できます。 エージェントの管理機能を使用すると、担当するエージェントを簡単に確認し、その詳細を確認し、それらに対するアクセスを有効、無効、または要求するためのアクションを実行できます。

#### パブリック プレビュー - エージェント向けの条件付きアクセス

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

エージェント ID の条件付きアクセスは、条件付きアクセスの評価と AI エージェントへの適用をもたらす Microsoft Entra ID の新機能です。 この機能により、人間のユーザーとアプリを既に保護しているのと同じゼロ トラスト コントロールがエージェントに拡張されます。 条件付きAccessは、エージェントをファースト クラス ID として扱い、人間のユーザーまたはワークロード ID に対する要求を評価するのと同じ方法で、エージェント固有のロジックを使用して、access要求を評価します。

#### パブリック プレビュー - ライフサイクル ワークフローでエージェント ID スポンサーのライフサイクルをサポート

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

エージェント ID のスポンサーを管理することは、エージェント ID のライフサイクル ガバナンスとアクセス制御にとって重要です。 スポンサーは、エージェント ID のライフサイクルとアクセスを管理します。 ライフサイクルワークフローは、スポンサーの役割変更や組織離脱時にマネージャーや共同スポンサーに通知することで、スポンサーのライフサイクル管理を自動化・効率化します。 スポンサーの情報を正確かつ最新に保つことで、効果的なガバナンスとコンプライアンスが確保されます。 詳細については、 [Lifecycle Workflows(プレビュー)のエージェント識別スポンサータスク](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks)をご覧ください。

#### パブリック プレビュー - Microsoft Entra エージェント レジストリ

**種類:** 新機能**サービス カテゴリ:** 他**製品の機能:** プラットフォーム

Microsoft Entra エージェント レジストリは、組織内に展開されているすべてのエージェントの一元化されたメタデータ ストアです。 AIエージェントがデータ取得、オーケストレーション、自律的な意思決定をますます担当する中、企業は明確な可視性や管理がないまま、セキュリティ、コンプライアンス、ガバナンスのリスクが高まっています。 Microsoft Entra エージェント IDの一部であるエージェント レジストリMicrosoft Entra、Microsoftエコシステムと非Microsoftエコシステム全体のすべてのエージェントの統一されたビューを提供する拡張可能なリポジトリを提供することで、これを解決します。これにより、一貫した検出、ガバナンス、セキュリティで保護されたコラボレーションを大規模に実現できます。 詳細については、「[Microsoft Entra エージェント レジストリとは](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-agent-registry)」を参照>。

#### パブリック プレビュー - 接続されていないアプリケーションを含むユーザー中心型アクセス レビュー

**種類:** 新機能**サービス カテゴリ:** アクセス レビュー**製品の機能:** ID ガバナンス

この機能により、組織は、Microsoft Entra IDとまだ統合されていないアプリケーションのアクセス レビューを管理できます。 詳細については、「[カタログ ユーザーのアクセス レビュー用カタログに提供されたカスタム データ リソースを含める (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-data-resource-access-reviews)」を参照してください。

#### パブリック プレビュー - ユーザー中心のアクセス レビュー

**種類:** 新機能**サービス カテゴリ:** アクセス レビュー**製品の機能:** ID ガバナンス

ユーザー中心のaccess レビュー (UAR) は、レビュー担当者がカタログ内の複数のリソースにわたるユーザーのaccessを 1 つの統合ビューで表示できるユーザー中心のレビュー モデルを提供し、適切なaccessを適切なタイミングで確保するプロセスを合理化します。 リソースにはEntraグループや接続済み・切断型(BYOD)アプリケーションが含まれ、顧客に統合的で包括的なレビュー体験を提供します。 詳細については、「[Catalog Access Reviews (Preview)](https://learn.microsoft.com/ja-jp/entra/id-governance/catalog-access-reviews)」を参照>。

#### パブリック プレビュー - Windowsの Entra アカウント登録ページの新しいエクスペリエンス

**種類:** 新機能**サービスカテゴリ:** デバイス登録および管理**製品の機能:** ユーザー認証

Windowsの Entra アカウント登録フローに新しい最新化されたユーザー エクスペリエンスが導入されています。 新しいユーザー エクスペリエンスは、Microsoft設計パターンと一致するように更新され、登録と登録のためにエクスペリエンスが 2 つの個別のページに分割されます。

また、アカウント登録フローのMDM登録オプションを制御するための新しい管理者プロパティをパブリックプレビューで導入します。 これは、職場または学校アカウントに対してWindows MAM を有効にしたいお客様を対象としています。 新しい設定は、エンドユーザーがこのフローにMDMで登録するためのユーザーエクスペリエンス画面を制御します。 詳細については、「[Windows デバイスの自動登録を設定する](https://learn.microsoft.com/ja-jp/intune/intune-service/enrollment/windows-enroll)を参照してください。

#### パブリック プレビュー - Entra Kerberos を使用したMicrosoft Entra IDにより、クラウド専用 ID のサポートが追加されました

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Entra Kerberos を使用したMicrosoft Entra IDでは、Entra 参加済みセッション ホストが、従来のActive Directory インフラストラクチャに依存することなく、Azure ファイル共有や仮想デスクトップなどのクラウド リソースを認証 Azureしてアクセスできるようにするクラウド専用 ID のサポートが追加されました。 この機能は、エンタープライズ レベルのセキュリティ、アクセス制御、暗号化を維持しながらドメイン コントローラーの必要性を排除するために、クラウドのみの戦略を採用する組織にとって不可欠です。 詳細は「 [クラウドのみのアイデンティティ(プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/kerberos#cloud-only-identity-preview)」をご覧ください。

#### パブリック プレビュー - エージェント向け Microsoft Entra ID 保護

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

組織が自律 AI エージェントを導入、構築、デプロイするにつれて、それらのエージェントを監視および保護する必要性が重要になります。 Microsoft Entra ID 保護は、[Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id) プラットフォームを使用するエージェントに対する ID ベースのリスクを自動的に検出して対応することで、組織を保護するのに役立ちます。

#### パブリック プレビュー - Microsoft Entra IDで同期されたパスキー

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra IDでは、ネイティブおよびサード パーティのパスキー プロバイダーに格納されている同期されたパスキーがサポートされるようになりました。 この変更により、パスキー(FIDO2)認証方法のポリシーはグループベースの構成をサポートするように拡張され、異なる種類のパスキーを別々に展開できるようになりました。 この機能の使用方法の詳細については、「 Microsoft Entra ID (プレビュー)

#### パブリックプレビュー - 統一Entraアプリギャラリー

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

CA スコープを使用すると、管理者は定義されたユーザー グループに特定の CA をバインドできます。 これにより、ユーザーは、彼らに限定された信頼できるソースの証明書のみを使用して認証できるようになります。 これにより、コンプライアンスが強化され、誤って発行された証明書や不正な証明書への露出が軽減されます。 詳細については、「 [証明機関 (CA) のスコープ (プレビュー)」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#certificate-authority-ca-scoping)参照してください。

**サービス カテゴリ:** エンタープライズ アプリケーション**製品機能:** アクセス制御

Microsoftは、統合された App Risk Insights を使用してグローバル セキュア アクセス (GSA) を強化しています。プレビュー段階です。

この新機能により、グローバル セキュア アクセスと Microsoft Entra アプリ ギャラリー (Microsoft Defender for Cloud Apps からのアプリケーションとリスク スコアが含まれるようになりました) が、1 つの統合されたリスク対応エクスペリエンスに統合されます。 これにより、組織は、Microsoft Entra管理センター内ですべてのアプリケーションを直接検出、評価、保護できます。

この統合により、組織はアプリのリスクをリアルタイムで評価し、そのリスクに基づいてaccessポリシーを適用できます。 管理者は Entra アプリ ギャラリーで各アプリのリスク スコア、コンプライアンス データ、構成 (SSO とプロビジョニング) を表示できますが、GSA ではアプリのリスク レベルに基づいて条件付きAccessとセッション制御が適用されます。

お客様ができること:

- アンマネージド IT やシャドウ IT など、Global Secure Access テレメトリを使用して、環境全体のアプリケーションを検出します。
- Microsoft Entra アプリ ギャラリーでリスクとコンプライアンスのデータを評価します。
- リアルタイムのリスク シグナルを使用して、GSA で条件付きAccessとセッション ポリシーを適用します。

この統合により、Microsoft Entra エコシステム全体でアプリの検出、リスク インテリジェンス、ポリシーの適用が統合され、盲点が減り、ガバナンスが簡素化され、使用中のすべてのクラウド アプリの保護が強化されます。

このエクスペリエンスは、Microsoft Entra管理センター内のプレビューで使用できるようになりました。 この機能にアクセスするには、次のいずれかのライセンスが必要です。

- Microsoft Entra スイート ライセンス
- Microsoft Entra Internet Access ライセンス

詳細については、以下をご覧ください。

- [Microsoft Entra のドキュメント](https://learn.microsoft.com/ja-jp/entra/)
- [Microsoft Entra グローバル セキュリティで保護されたアクセス](https://learn.microsoft.com/ja-jp/entra/global-secure-access/)
- [Microsoft Defender for Cloud Apps の概要](https://learn.microsoft.com/ja-jp/defender-cloud-apps/)

#### 一般提供 - 迅速なインジェクション保護を使用してエンタープライズ GenAI アプリケーションを保護する

**種類:** 新機能**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

Microsoft の Security Service Edge (SSE) ソリューションの一部である AI Gateway は、生成型 AI アプリケーション、エージェント、言語モデルを保護します。 プロンプトインジェクション保護機能は、エンタープライズ GenAI アプリに対する悪意のあるプロンプトインジェクション攻撃に対するリアルタイムの保護を提供します。これは、LLM にとって最もリスクの高いリスクです。 Prompt Injection Protection は、ネットワーク レベルでガードレールを適用することで、コードを変更することなく、すべての生成 AI アプリケーションで一貫したセキュリティを確保します。 詳細については、「 [エンタープライズ生成 AI アプリケーションを迅速なインジェクション保護で保護する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-ai-prompt-injection-protection)」を参照してください。

#### パブリックプレビュー - インターネットトラフィック用のリモートネットワーク向けGSAクラウドファイアウォール

**種類:** 新機能**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

Cloud Firewall（CFW）は、Firewall as a Service（FWaaS）として提供される次世代ファイアウォールとも呼ばれ、ネットワークトラフィックを監視し、それにポリシーを適用することで、許可されていない外向き通信アクセス（インターネットへの接続など）からGSAのお客様を保護するとともに、各拠点に対する一元管理、可視性、および一貫したポリシーを提供します。 詳細については、「[Configure Global Secure Access クラウド ファイアウォール (プレビュー)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-cloud-firewall)を参照してください。

#### パブリック プレビュー - Microsoft Copilot Studio エージェント用のセキュリティで保護された Web および AI ゲートウェイ

**種類:** 新機能**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

組織が人間が以前に処理したタスクを実行するために自律型および対話型の AI エージェントを採用する場合、管理者はエージェントのネットワーク アクティビティを可視化して制御する必要があります。 エージェントのグローバルセキュアアクセスによって、Microsoft Copilot Studioエージェントにネットワークセキュリティ制御が提供されるため、ユーザーに使用するのと同じセキュリティポリシーをエージェントにも適用できます。

エージェントのグローバル セキュリティで保護されたアクセスを使用すると、エージェントがナレッジ、ツール、アクションを使用して外部リソースにアクセスする方法を規制できます。 Web コンテンツのフィルター処理、脅威インテリジェンス のフィルター処理、ネットワーク ファイルのフィルター処理などのネットワーク セキュリティ ポリシーをエージェント トラフィックに適用できます。 詳細については、「[Microsoft Copilot Studio エージェント用の Secure Web および AI Gateway の詳細 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-secure-web-ai-gateway-agents)を参照してください。

#### パブリックプレビュー - GSAリモートネットワーク接続によるインターネットトラフィックサポート

**種類:** 新機能**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

リモート ネットワーク接続により、セキュリティで保護された *clientless* IPsec トンネル経由でブランチ オフィスからMicrosoft 365およびインターネット リソースにアクセスできます。 Microsoft 365トラフィックのサポートは一般公開されていますが、完全なインターネット アクセスはパブリック プレビューに進むようになりました。 すべてのインターネット トラフィックへの対応は、当社の MSIT を含むリモート ネットワーク接続の顧客から寄せられた要望の中で最も多いものでした。 詳細については、「グローバル セキュア Accessを参照してください。

#### 一般公開 - GSA + Netskope ATP & DLP 統合

**種類:** 新機能**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

今日の進化する脅威環境において、組織は機密データやシステムをサイバー攻撃から守る課題に直面しています。 Global Secure Access は、Entra Internet Access 保護と Netskope の 高度な脅威保護 (ATP) およびデータ損失防止 (DLP) 機能を組み合わせて、マルウェア、ゼロデイ脆弱性、データ 漏洩に対するリアルタイム保護を実現し、統合プラットフォームを介した管理を簡素化します。 Microsoftの SSE ソリューションにはオープン プラットフォーム アプローチが採用されており、サードパーティ企業との統合が可能になり、Netskope が最初になります。 詳細については、「[Global Secure Access と Netskope の高度な脅威保護とデータ損失防止の統合](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-netskope-integration)を参照してください。

#### パブリック プレビュー - エンタイトルメント管理では、IRM と IDP リスク シグナルに基づく危険なユーザーのAccess パッケージ要求に追加の承認フローが導入されます

**種類：** 機能の変更**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

権利管理は現在、リスクベースの承認エスカレーションをサポートしています。 access パッケージを要求するユーザーが、追加の調査を必要とするとして Insider Risk Management または Identity Protection によってフラグが設定されると、要求は、accessが付与される前に、追加の承認手順のために指定されたセキュリティ承認者に自動的にルーティングされます。 詳細については、次のトピックを参照してください。

IDP- [エンタイトルメント管理 (プレビュー) でアクセス パッケージ要求に対する ID 保護に基づく承認を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-id-protection-approvals)

IRM- [エンタイトルメント管理でアクセス パッケージ要求に対するインサイダー リスク管理ベースの承認を構成する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-insider-risk-management-approvals)

#### 一般提供 - Microsoft Entra Internet Access TLS 検査

**種類：** 機能の変更**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

Microsoft Entra Internet Accessのトランスポート層セキュリティ (TLS) 検査が一般公開され、暗号化されたトラフィックと高度なセキュリティ制御を詳細に把握できるようになりました。

TLSインスペクションは、ユーザーフレンドリーなブロックメッセージ、完全なURLフィルタリング、ファイルポリシーの強制、そしてAIゲートウェイによるプロンプト検査の基盤を提供します。

組織は、どのトラフィックを検査するか、どのユーザーやデバイスにポリシーが適用されるかを明確にする柔軟なTLS検査ポリシーを定義できます。 カスタムルールは宛先FQDN(先のFQDN)やウェブカテゴリに基づくトラフィックの遮蔽や回避を細かく制御し、トラフィックログはマッチングしたポリシーやルールの詳細な洞察を提供します。 詳細については、「[トランスポート層セキュリティ検査とは](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-transport-layer-security)」を参照>。

#### パブリックプレビュー - URLフィルタリング

**種類:** 新機能**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

このパブリック プレビューでは、URL フィルター規則を構成して、完全な URL (ホスト名や完全なパスを含む) へのaccessを細かく拒否または許可することができます。 これらの規則は、ポリシーをセキュリティ プロファイルに条件付きaccess ポリシーにリンクすることで、セキュリティ ポリシーをコンテキスト対応にできるようにする、既存の Web コンテンツ フィルタリング ポリシー スキーマの一部です。 詳細については、「[Global Secure Access Web コンテンツ フィルタリングを構成する方法](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering)を参照してください。

### 2025 年 10 月

#### 変更の計画 - 多要素認証セッションを取り消すための更新

**種類:** 変更の計画**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

2026 年 2 月以降、現在の "*Revoke 多要素認証セッション*" ボタンは、Microsoft Entra 管理センターの [*Revoke セッション*] ボタンに置き換えます。

従来の "*MFA セッションの取り消し*" アクションは、ユーザーごとの MFA 適用にのみ適用され、混乱を招きました。 簡略化と一貫した動作の確保のため、新しい「*セッションの取り消し*」ボタンは、MFA が条件付きアクセスまたはユーザーごとのポリシーによって適用されているかどうかに関係なく、MFA を含むすべてのユーザー セッションを無効化します。

**アクションが必要**

管理者は、"MFA セッションの*取*り消し" ではなく "*セッションの取り消し*" を使用するようにワークフローとガイダンスを更新する必要があります。 この変更後、"*MFA セッションの取り消し*" オプションはポータルから削除されます。

#### パブリック プレビュー - ライフサイクル ワークフローの委任されたワークフロー管理

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローを管理単位 (AU) で管理できるようになりました。これにより、組織はワークフローをセグメント化し、特定の管理者に管理を委任できます。 この機能強化により、承認された管理者のみが、スコープに関連するワークフローを表示、構成、実行できるようになります。 お客様は、ワークフローを AU に関連付け、スコープ付きアクセス許可を委任された管理者に割り当て、ワークフローが定義されたスコープ内のユーザーにのみ影響を与えるようにすることができます。 詳細については、「 [委任されたワークフロー管理 (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-delegate-workflow)を参照してください。

#### パブリック プレビュー - Microsoft Entra 外部 ID のブランド化テーマを使用したアプリベースのブランド化

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Microsoft Entra 外部 ID (EEID) では、お客様はすべてのアプリに適用される、テナント全体でカスタマイズされた単一のブランド化エクスペリエンスを作成できます。 お客様が特定のアプリケーションに対して異なるブランド化エクスペリエンスを作成できるように、ブランド化の "*テーマ*" の概念を導入しています。 詳細については、「 [アプリのブランド化テーマをカスタマイズする」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding-themes-apps)参照してください。

#### パブリック プレビュー - ライフサイクル ワークフロー属性変更トリガーでの拡張属性のサポート

**種類：** 機能の変更**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローの属性変更トリガーで追加の属性の種類がサポートされるようになりました。これにより、組織の変更をより広範に検出できるようになりました。 以前は、このトリガーはコア属性のセットに制限されていました。 この更新プログラムでは、次のいずれかの属性が変更されたときに応答するようにワークフローを構成できます。

- カスタム セキュリティ属性
- ディレクトリ拡張属性
- EmployeeOrgData 属性
- オンプレミス属性 1 ~ 15

この機能強化により、管理者はカスタム属性または拡張属性に基づいてムーバー イベントのライフサイクル プロセスを自動化する柔軟性が向上し、複雑な組織構造とハイブリッド環境のガバナンスが向上します。 詳細については、「 [ライフサイクル ワークフローでカスタム属性トリガーを使用する (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/id-governance/workflow-custom-triggers)を参照してください。

#### パブリック プレビュー - ユーザー名/エイリアスを使用したサインイン

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Microsoft Entra 外部 ID (EEID) では、ローカルの電子メールとパスワードの資格情報を持つユーザーは、識別子として電子メール アドレスでサインインできます。 これらのユーザーが、顧客/メンバー ID などの代替識別子 (保険番号、Graph APIまたはMicrosoft Entra 管理センターを介して割り当てられた頻繁なチラシ番号など) でサインインする機能を追加しています。 詳細については、「 [エイリアスまたはユーザー名を使用したサインイン (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を参照してください。

#### 非推奨 - Microsoft Entra PIM のイテレーション 2 ベータ API は廃止されます。 Iteration 3 API に移行します。

**種類：** 廃止**サービスのカテゴリ:** 特権ID管理**製品の機能:** ID ガバナンス

**Introduction**

2026 年 10 月 28 日から、Microsoft Entra Privileged Identity Management (PIM) [Iteration 2](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagement-root?view=graph-rest-beta&preserve-view=true) (ベータ) API をAzureリソース、Microsoft Entraロール、およびグループの呼び出しを行うすべてのアプリケーションとスクリプトが失敗します。

**これが組織に与える影響**

2026 年 10 月 28 日以降、PIM イテレーション 2 (ベータ) API エンドポイントMicrosoft Entra呼び出すアプリケーションまたはスクリプトは失敗します。 これらの呼び出しではデータが返されなくなり、これらのエンドポイントに依存するワークフローや統合が中断される可能性があります。 これらの API はベータ版でリリースされ、廃止される予定です。Iteration 3 は、信頼性の向上と広範なシナリオサポートを提供する一般公開 (GA) API です。

**準備のために必要なこと**

一般公開されている **イテレーション 3 (GA) API** に移行することを強くお勧めします。

- できるだけ早く移行の計画とテストを開始します。
- イテレーション 2 API を使用して新しい開発を停止します。
- 互換性を確保するために、イテレーション 3 API のドキュメントを確認します。

詳細情報：

- [Privileged Identity Management の API の概念 - Microsoft Entra ID ガバナンス |Microsoft Learn](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-apis)
- [Privileged Identity Management iteration 2 API](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagement-root?view=graph-rest-beta&preserve-view=true)
- [PIM イテレーション 2 API から PIM イテレーション 3 API に移行する](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagement-root?view=graph-rest-beta&preserve-view=true#migrate-from-pim-iteration-2-apis-to-pim-iteration-3-apis)

#### パブリック プレビュー - 条件付きアクセス ポリシーと名前付き場所に対するソフト削除と復元

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

Microsoft Entra で、**条件付きアクセス (CA) ポリシーと名前付きネットワークの場所に対するソフト削除と復元の公開プレビュー** を発表できることを嬉しく思います。 この新機能により、実証済みの論理的な削除モデルが、**Microsoft Graph API (ベータ版) とMicrosoft Entra管理センター**全体にわたって重要なセキュリティ構成に拡張され、管理者は偶発的または悪意のある削除から迅速に回復し、全体的なセキュリティ体制を強化できます。

この機能を使用すると、管理者は次のことができます。

- 削除されたアイテムを 30 日以内に正確な以前の状態に復元する
- 復元する前に削除済みアイテムを確認する
- 必要に応じて完全に削除する

論理的な削除は、Microsoft Entra全体で既に大規模に実証されています (過去 30 日間に復元された 7M 以上のオブジェクト)。 CA ポリシーとネームド ロケーションに導入することで、迅速なディザスター リカバリーを実現し、ダウンタイムを最小限に抑え、セキュリティの整合性を維持します。

#### 一般提供 - [マイ Access] で推奨されるAccess パッケージをユーザーに表示できます

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

マイ アクセスでは、Microsoft Entra ID ガバナンス ユーザーはマイ アクセスで推奨されるアクセス パッケージのキュレーションされた一覧を表示できます。 この機能を使用すると、ユーザーは、使用可能なすべてのアクセス パッケージをスクロールすることなく、ピアのアクセス パッケージと以前の割り当てに基づいて、最も関連性の高いアクセス パッケージをすばやく表示できます。

推奨されるアクセス パッケージの一覧は、ユーザー (マネージャー、直属の部下、組織、チーム メンバー) に関連するユーザーを検索し、ユーザーのピアの内容に基づいてアクセス パッケージを推奨することによって作成されます。 ユーザーには、以前に割り当てられていたアクセス パッケージも推奨されます。

管理者は、この設定を使用して、推奨されるaccess パッケージのピアベースの分析情報を有効にすることをお勧めします。 詳細については、[My Access の推奨アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-suggested-access-packages)を参照してください。

#### 一般提供 - 外部ユーザーから内部メンバーへの変換

**種類:** 新機能**サービス カテゴリ:** [ユーザー管理]**製品の機能:** [ユーザー管理]

外部ユーザー変換を使用すると、お客様はユーザー オブジェクトを削除して新たに作成することなく、外部ユーザーを内部メンバーに変換できます。 同じ基になるオブジェクトを維持することで、ユーザーのアカウントとリソースへのaccessが中断されず、アクティビティの履歴はホスト組織との関係が変化してもそのまま残ります。

外部から内部へのユーザーの変換機能には、オンプレミスの同期されたユーザーを変換する機能も含まれています。

#### 一般提供開始 - UserAuthenticationMethod API 向けのきめ細かな最小アクセス許可

**種類:** 新機能**サービス カテゴリ:** MS Graph**製品の機能:** 開発者エクスペリエンス

**まとめ**

Microsoft Entra IDでは、UserAuthenticationMethod API の新しい詳細なアクセス許可が導入されています。 この更新により、組織は認証方法を管理する際に最小限の特権の原則を適用し、セキュリティと運用の効率の両方をサポートできます。

**新機能**

- **メソッドごとの新しいアクセス許可:** 各認証方法の詳細なアクセス許可 (パスワード、Microsoft Authenticator、電話、電子メール、一時アクセス パス、パスキー、Windows Hello for Business、QR+PIN など)。
- **Read-only policy permission:** 新しいアクセス許可により、認証方法ポリシーに対する読み取り専用accessが許可され、ロールの分離と監査が向上します。

詳細については、「[Microsoft Graph のアクセス許可リファレンス - Microsoft Graph | Microsoft Learn](https://learn.microsoft.com/ja-jp/graph/permissions-reference)」を参照してください。

#### パブリック プレビュー - クラウド管理リモート メールボックス

**種類:** 新機能**サービス カテゴリ:** [ユーザー管理]**製品能力:** Microsoft Entra Cloud Sync

オブジェクト レベルの Source of Authority (SOA) を使用すると、管理者は、Active Directory (AD) から同期された特定のユーザーを、クラウドで編集可能なオブジェクトにMicrosoft Entra IDに変換できます。これは、AD から同期されなくなり、最初にクラウドで作成されたかのように動作します。 この機能は、段階的な移行プロセスをサポートし、ユーザーと運用への影響を最小限に抑えるようにしながら、AD への依存関係を減らします。 Connect Sync と Cloud Sync Microsoft Entraはどちらも、これらのオブジェクトの SOA スイッチを認識します。 同期されたユーザーの SOA を AD から Microsoft Entra ID に切り替えるオプションは、現在パブリック プレビューで利用できます。 詳細については、「 [クラウド優先の体制を採用する: ユーザーの機関ソース (SOA) をクラウドに転送する (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview)を参照してください。

#### パブリック プレビュー - Workday 終了データをプリフェッチしてアカウントの無効化ロジックをカスタマイズする

**タイプ:** 固定**サービス カテゴリ:** プロビジョニング**製品の機能:** Microsoft Entra IDへのインバウンド

この Workday コネクタ更新プログラムは、APAC および ANZ リージョンのワーカーに対して観察された終了処理の遅延を解決します。 管理者は、終了先読み設定を有効にしてデータをプリフェッチし、Microsoft Entra IDおよびオンプレミスの Active Directoryのアカウントのプロビジョニング解除ロジックを調整できるようになりました。 詳細については、「[Workday の退職の先読み (プレビュー) を設定する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-workday-termination-lookahead)」を参照してください。

#### 一般提供開始 - 同期済みのオンプレミス AD グループの権限ソースをクラウド グループに変換する機能が利用可能になりました

**種類:** 新機能**サービス カテゴリ:** グループ管理**製品能力:** Microsoft Entra Cloud Sync

グループ SOA 機能を使用すると、組織は Connect Sync または Cloud Sync を使用してMicrosoft Entra IDにActive Directoryグループ機関を転送することで、アプリケーション アクセス ガバナンスをオンプレミスからクラウドに移行できます。段階的な移行により、管理者は AD の依存関係を徐々に減らし、中断を最小限に抑えることができます。 Microsoft Entra ID ガバナンスは、セキュリティ グループにリンクされたクラウドアプリとオンプレミスアプリの両方のアクセスを管理し、いずれかの同期クライアントのお客様がこの機能を使用できるようになりました。 詳しくは、 [権限のグループ・ソースを](https://aka.ms/groupsoadocs)参照してください。

#### 変更の計画 - Authenticator アプリでの脱獄検出

**種類:** 変更の計画**サービス カテゴリ:** Microsoft Authenticator アプリ**製品の機能:** ID のセキュリティと保護

**2026 年 2 月以降**、Authenticator アプリのMicrosoft Entra資格情報に対する **Jailbreak/Root 検出** について説明します。 この更新プログラムは、脱獄されたデバイスまたはルート化されたデバイスでMicrosoft Entra資格情報が機能しないようにすることで、セキュリティを強化します。 このようなデバイスに保存されている既存の認証情報はすべて、お客様の組織を保護するために削除されます。

この機能は既定でセキュリティで保護されており、管理者の構成や制御は必要ありません。 この変更は、iOS と Android の両方に適用されます。この変更は、個人またはサード パーティのアカウントには適用されません。

**必要なアクション:** この今後の変更についてエンド ユーザーに通知します。 Authenticator は、脱獄またはルート化されたデバイス上では、Microsoft Entra アカウントに対して使用できなくなります。

詳細については、「[About Microsoft Authenticator](https://support.microsoft.com/account-billing/about-microsoft-authenticator-9783c865-0308-42fb-a519-8cf666fe0acc)を参照してください。

#### パブリック プレビュー - AVD と W365 を使用したグローバル セキュア Access B2B サポート

**種類:** 新機能**サービス カテゴリ:** B2B**製品の機能:** ネットワークアクセス

Global Secure Access (GSA) における、W365 と AVD を使用したゲスト アクセスのサポートは、パブリック プレビューになりました。 この B2B サポートは、ゲスト、パートナー、Windows Cloud - Azure Virtual Desktop (AVD)、Windows 365 (W365) を使用する請負業者などの外部 ID への GSA を使用したセキュリティで保護されたアクセスに対処します。 この機能を使用すると、外部テナントのサード パーティ ユーザーは、会社のテナント内のリソースを安全にaccessできます(リソース テナントとも呼ばれます)。 リソース テナント管理者は、これらのサード パーティ ユーザーへのプライベート アクセス、インターネット アクセス、Microsoft 365 トラフィックを有効にすることができます。

詳細については、「[グローバル セキュア アクセス B2B ゲスト アクセス (プレビュー) について学習する - グローバルセキュリティで保護されたアクセス |Microsoft Learn](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-external-user-access)。

### 2025 年 9 月

#### パブリック プレビュー - 同期された Active Directory ユーザーの権限のソースをクラウド上に移行する

**種類:** 新機能**サービス カテゴリ:** [ユーザー管理]**製品機能:** Microsoft Entra Connect と Microsoft Entra Cloud Sync

オブジェクト レベルの Source of Authority (SOA) を使用すると、管理者は、Active Directory (AD) から同期された特定のユーザーを、クラウドで編集可能なオブジェクトにMicrosoft Entra IDに変換できます。これは、AD から同期されなくなり、最初にクラウドで作成されたかのように動作します。 この機能は、段階的な移行プロセスをサポートし、ユーザーと運用への影響を最小限に抑えるようにしながら、AD への依存関係を減らします。 Connect Sync と Cloud Sync Microsoft Entraはどちらも、これらのオブジェクトの SOA スイッチを認識します。 同期されたユーザーの SOA を AD から Microsoft Entra ID に切り替えるオプションは、現在パブリック プレビューで利用できます。 詳細については、「 [クラウド優先の体制を採用する: ユーザーの機関ソース (SOA) をクラウドに転送する (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview)を参照してください。

#### パブリック プレビュー - Microsoft Entra 外部 IDのパスワード リセット フローで SMS を検証方法として使用する

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

**セルフサービス パスワード リセット (SSPR) 用 SMS のパブリック プレビューを Microsoft Entra 外部 ID** でお知らせします。 この変更は、10 月末までに運用環境のすべてのテナントに対して積極的にロールアウトされます。

**新機能**

- **パスワード リセットの SMS 認証:** エンド ユーザーは、 *"パスワードを忘れた場合"* またはセルフサービスのパスワード リセット フローを使用するときに、SMS を使用して自分の ID を確認できるようになりました。 以前は、電子メールのワンタイム パスコードのみがサポートされていました。
- **セキュリティ強化:** ユーザーにパスワード リセット用の登録済みメソッドが 2 つ以上ある場合は、少なくとも 2 つの方法で ID を確認し、保護レイヤーを追加する必要があります。
- **Fraud Protection:** 電話評判プラットフォームへの組み込みの統合により、テレフォニー アクティビティがリアルタイムで処理され、リスクが特定されます。 各要求は、テレフォニー詐欺からの保護に役立つ許可、ブロック、またはチャレンジの決定で返されます。
- **請求：** パスワード リセット用の SMS は、場所/地域に基づいて価格が階層化されたアドオン機能の一部です。 SMS あたりの料金には、fraud protection サービスが含まれます。 詳細については、「 [国/地域別の SMS 価格レベル](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#sms-pricing-tiers-by-countryregion)」を参照してください。

#### 一般提供 - テナント間同期 (クラウド間)

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** コラボレーション

Microsoft クラウド間のテナント間でのユーザーの作成、更新、および削除を自動化します。 次の組み合わせがサポートされています。

- 商用 -&gt; 米国政府
- 米国政府 - &gt; 商業
- 商用 -&gt; 中国

詳細については、「[テナント間同期の構成](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」を参照してください。

#### 一般提供 - Microsoft Entra Connect Sync または Cloud Sync を使用して、AD を Microsoft Entra ID と同期できるようにするための専用の新しい独自リソース アプリケーション

**種類:** 変更の計画**サービス カテゴリ:** Microsoft Entra Connect**製品機能:** Microsoft Entra Connect

継続的なセキュリティ強化の一環として、Microsoftは、Active DirectoryとMicrosoft Entra IDの間の同期を有効にする専用のファースト パーティ アプリケーションをデプロイしました。 この新しいアプリケーションは、"Microsoft Entra AD 同期サービス" (アプリケーション ID: 6bf85cfa-ac8a-4be5-b5de-425a0d0dc016) と呼ばれるファースト パーティ サービス プリンシパルとしてマニフェストされ、Microsoft Entra 管理センター内のエンタープライズ アプリケーション エクスペリエンスに表示されます。 このアプリケーションは、Microsoft Entra Connect を介して Microsoft Entra ID の同期機能を維持し、オンプレミス環境での継続的な運用を可能にするために不可欠です。

Microsoft Entra Connect では、このファースト パーティ アプリケーションを使用して、Active DirectoryとMicrosoft Entra IDの間で同期できるようになりました。 お客様は、**2026 年 9 月**までにバージョン **2.5.79.0** 以降にアップグレードする必要があります。

サポートされている場合は、お客様を自動アップグレードします。 自動アップグレードを希望するお客様は、 [自動アップグレードが構成されていることを確認してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)。

この変更の Microsoft Entra Connect Sync .msi インストール ファイルは、[Microsoft Entra Connect](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) のMicrosoft Entra 管理センターでのみ使用できます。

利用可能なバージョンの詳細については、 [バージョン履歴ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) を参照してください。

#### パブリック プレビュー - アプリ管理ポリシー ポータルエクスペリエンス

**種類:** 新機能**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** ディレクトリ

アプリ管理ポリシーを使用すると、管理者は組織内のアプリケーションを構成する方法に関する規則を設定することで、組織のセキュリティを向上させることができます。 パスワード資格情報などの安全でない構成をブロックするために使用できます。 これらのポリシーは Microsoft Graph API を通じて利用できるようになりましたが、エンタープライズ アプリケーション エクスペリエンスで、Microsoft Entra 管理センターを使用して構成することもできます。

[アプリ管理ポリシーを構成する方法](https://aka.ms/app-mgmt-policy-ux-docs)の詳細について説明します。

#### パブリック プレビュー - マイ Accessで承認を委任する

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

ユーザーはマイ Accessでaccess パッケージの承認を委任できるようになりました。 承認者は、別の個人を割り当てて、accessパッケージの承認要求に応答できます。 元の承認者は、委任期間中も承認に応答できます。

注

この機能は現在、access パッケージの承認にのみ適用され、2025 年 11 月にaccessレビューをサポートするように拡張されます。

詳細については、「[My Access における承認の委任](https://learn.microsoft.com/ja-jp/entra/id-governance/delegate-approvals-my-access)」を参照してください。

#### パブリック プレビュー - ライフサイクル ワークフローで失敗したユーザーとワークフローを再処理する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローでは、ワークフローの再処理がサポートされるようになりました。これにより、組織は、エラーやエラーが検出されたときにワークフローの再処理を効率化できます。 この機能には、失敗した実行や、もう一度処理する可能性がある実行のみを含む、ワークフローの以前の実行を再処理する機能が含まれます。 お客様は、ニーズに合わせて次のオプションから選択できます。

- 再処理する特定のワークフロー実行を選択する
- 再処理するワークフロー実行のユーザーを選択します。 たとえば、失敗したユーザー、またはテスト実行中のすべてのユーザー

詳細については、「[ワークフローの再処理](https://learn.microsoft.com/ja-jp/entra/id-governance/reprocess-workflow)」を参照してください。

#### パブリック プレビュー - ライフサイクル ワークフローで非アクティブな従業員とゲストのワークフローをトリガーする

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローを使用すると、ユーザーは、サインイン非アクティブに基づいて ID ライフサイクル アクションを自動化することで、休止中のユーザー アカウントを事前に管理するようにカスタム ワークフローを構成できるようになりました。 非アクティブ状態を検出すると、ワークフローは、非アクティブなしきい値を超えるユーザーに対して、非アクティブな通知の送信、アカウントの無効化、オフボードの開始などの定義済みのタスクを自動的に実行します。 管理者は、非アクティブなしきい値とスコープを構成し、休止中のアカウントが効率的かつ一貫して処理されるようにすることができます。セキュリティの露出を減らし、ライセンスの無駄を減らし、大規模なガバナンス ポリシーを適用することができます。

詳細については、「 [ライフサイクル ワークフローを使用して非アクティブなユーザーを管理する (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-inactive-users)を参照してください。

#### Microsoft Authentication Library の廃止 - MSAL 推奨APIへの移行

**種類：** 廃止**サービス カテゴリ:** 他**製品の機能:** 開発者エクスペリエンス

**2025 年 12 月 15 日**に**、ADAL to MSAL Recommendations API** が廃止されます。

認証ライブラリの使用状況の監視を続行するには、**Microsoft Graph API** を使用してサインイン ログを手動で照会します。 関連するデータは、キー `authenticationProcessingDetails` の下の `"Azure AD App Authentication Library"` フィールドで使用できます。

ガイダンスについては、以下を参照してください。

- Microsoft Graph API

API を無効にする操作は必要ありません。

#### 非推奨 - Microsoft Entra 管理センター内のアプリのサインイン フィールドを自動的にキャプチャします。

**種類：** 廃止**サービス カテゴリ:** マイ アプリ**製品の機能:** プラットフォーム

Microsoft Entra 管理センターの [*アプリ* のサインイン フィールドを自動的にキャプチャする] オプションは廃止されます。 この機能で既に構成されている既存のアプリは引き続き機能しますが、新しい構成では使用できなくなります。 今後、管理者は "*アプリのサインイン フィールドをキャプチャする*" を使用する必要があります。 これには、Microsoft Edgeと Chrome で使用できる MyApps Secure Sign-In 拡張機能が必要です。

詳細については、「[アプリのサインイン フィールドをキャプチャする」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/troubleshoot-password-based-sso#capture-sign-in-fields-for-an-app)参照してください。

パスワードレス戦略の詳細については、[パスワードレスはここにあり、規模で展開されています](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/passwordless-is-here-and-at-scale/2810639)を参照してください。

#### パブリック プレビュー - iOS クライアントのグローバル セキュリティで保護されたAccessインターネット プロファイルのサポート

**種類:** 新機能**サービスカテゴリー:** インターネットアクセス**製品の機能:** ネットワークアクセス

iOS アプリでのインターネット Accessのサポートについてお知らせします。 この機能は、ID ベースの Secure Web Gateway (SWG) を使用してインターネットおよび SaaS アプリへのaccessを保護し、iPhone と iPad からの脅威、安全でないコンテンツ、および悪意のあるトラフィックをブロックします。

モバイル プラットフォーム上のグローバル Secure Access クライアントでは、リソースへのセキュリティで保護されたアクセスのために新しいエージェントのインストール/デプロイは必要ありません。また、既存の MDE (Microsoft Defender for Endpoint) を使用して、Microsoft 365、インターネット アクセス、プライベート アクセスの両方について、Microsoft SSE 経由でトラフィックをルーティングします。

詳細については、「[global Secure Access client for iOS (Preview)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-install-ios-client)を参照してください。

#### パブリック プレビュー - ライフサイクル ワークフローのカスタム電子メール通知での基本的な HTML サポート

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

これで、お客様はライフサイクル ワークフローの電子メール通知をさらにカスタマイズし、基本的な HTML 要素を使用して特定の情報をカスタマイズしたり、強調したりできます。 電子メール通知をカスタマイズして、HTML ハイパーリンクを使用したリンクの送信や、太字、斜体、下線などの基本的なテキスト書式を含めることができるようになりました。 詳細については、「 [ワークフロー タスクから送信される電子メールをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/id-governance/customize-workflow-email)」を参照してください。

#### マイクロソフト Entra Internet Access のカスタムブロックページがパブリックプレビューに対応

**種類:** 新機能**サービス カテゴリ:** Microsoft Entra Internet Access **製品機能:** Network Access

管理者がグローバル セキュア アクセス (GSA) の危険なサイト、NSFW、または承認されていないサイトまたはアプリへのアクセスをブロックするポリシーを構成すると、ユーザーはMicrosoft Entra Internet Accessでブランド化された明確な HTML エラー メッセージを受け取ります。 多くの管理者は、会社のスタイルのguidesに合わせてこのエクスペリエンスをカスタマイズすること、利用規約への参照を含める、IT ワークフローへのハイパーリンクを追加するなどの関心を表明しています。 グローバルセキュアAccessは、インターネットAccess用のカスタマイズされたブロックページをサポートするようになりました。 管理者は、Microsoft Graph APIを通じて次のことができます。

- GSA ブロック ページのテナント全体の本文テキストを構成します。
- 制限付きマークダウンを使用してハイパーリンクを追加し、使用条件、ServiceNow/IT チケット システム、ID ガバナンス ワークフロー統合用の MyAccess などのリソースを参照します。

詳細については、「[グローバル セキュア Access ブロック ページをカスタマイズする方法 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-customize-block-page)を参照してください。

### 2025 年 8 月

#### 一般提供 - Microsoft Entra ID 保護: 検出品質の向上

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

Microsoft Entra ID 保護の検出精度とノイズ低減を通じて高い検出品質が実現されました。 この四半期の機能強化は、次の検出に適用されます。

- [異常なトークン](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#anomalous-token-sign-in)
- [匿名 IP アドレス](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#anonymous-ip-address)
- [不可能な移動](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#impossible-travel)
- [パスワード スプレー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#password-spray)
- [見慣れないサインイン プロパティ](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#unfamiliar-sign-in-properties)

さらに、パスワードなしのシナリオに対するリスク検出をより適切に調整するために変更が加えられます。

#### パブリック プレビュー - ライフサイクル ワークフロー タスクは、アクセスパッケージの割り当ての有効期限設定をサポートするようになりました。

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

お客様は、ライフサイクル ワークフローのすべてのaccessパッケージの削除タスクを構成して、従業員が組織を離れる指定した日数が経過した後accessパッケージの割り当てを自動的に期限切れにできるようになりました。 詳細については、「[ユーザー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-all-access-package-assignments-for-user)のすべてのaccessパッケージの割り当てを削除する」を参照してください。

#### 変更の計画 - マイ アカウントの新しいエンド ユーザー のホーム ページ

**種類:** 新機能**サービス カテゴリ:** マイ プロファイル/アカウント**製品の機能:** エンド ユーザー エクスペリエンス

2025 年 9 月末までに、 `https://myaccount.microsoft.com` のホームページが更新され、タスクに重点を置いたエクスペリエンスが提供されます。 ユーザーには、期限切れのグループの更新、accessパッケージ要求の承認、ホームページでの MFA の直接設定などの保留中のアクションが表示されます。 アプリ、グループ、access パッケージ、サインインの詳細へのクイック リンクが見つけやすくなりました。 この変更は、アカウント管理を効率化し、ユーザーがaccessとセキュリティのタスクを管理できるように設計されています。

#### 変更の計画 - 要求者は、マイ Accessで自分のアクセスパッケージ承認者を確認できます

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

2025 年 9 月末までに、要求者は、保留中のaccess パッケージ要求の承認者の名前と電子メール アドレスをマイ Access ポータルで直接確認できるようになります。 この機能により、透明性が向上し、要求者と承認者間のコミュニケーションが効率化されます。 テナント レベルでは、すべてのメンバー (ゲスト以外) に対して承認者の可視性が既定で有効になり、Microsoft Entra管理センターのエンタイトルメント管理設定を使用して制御できます。 access パッケージ レベルでは、管理者とaccess パッケージ所有者は、承認者の可視性を構成し、access パッケージ ポリシーの詳細要求設定でテナント レベルの設定をオーバーライドすることを選択できます。

#### パブリック プレビュー - カスタム拡張機能を使用してaccess パッケージの承認要件を外部で決定する

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

エンタイトルメント管理では、accessパッケージ割り当て要求の承認者を直接割り当てるか、動的に決定することができます。 エンタイトルメント管理は、要求元マネージャー、第 2 レベルのマネージャー、接続された組織のスポンサーなどの承認者を動的に決定することをネイティブにサポートします。 この機能の導入によって、Azure Logic Appsのコールアウト用のカスタム拡張機能を使用し、組織の特定のビジネス ロジックに基づいて各アクセス パッケージ割り当て要求の承認要件を動的に決定できるようになりました。 アクセス パッケージの割り当て要求プロセスは、Azure Logic Appsでホストされているビジネス ロジックが承認ステージを返すまで一時停止し、その後の承認プロセスでマイ アクセス ポータル経由で利用されます。

詳細については、「[カスタム拡張機能 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-dynamic-approval)を使用して access パッケージの承認要件を外部で決定する」をご覧ください。

#### パブリック プレビュー - エンタイトルメント管理 アクセス パッケージにおける対象グループのメンバーシップおよび所有権のサポート

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

エンタイトルメント管理とグループのPrivileged Identity Management (PIM) の統合により、アクセス パッケージを介して対象のグループ メンバーシップと所有権を割り当てるためのサポートが追加されます。 セルフサービスのaccess要求と拡張プロセスを提供することで、これらのジャストインタイムで保護されたaccessの割り当てを大規模に管理できるようになり、それらを組織のロール モデルに統合できます。 詳細については、「[Privileged Identity Management for Groups (プレビュー) を使用して、アクセス パッケージ内の対象グループ メンバーシップおよび所有権を割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-eligible)」をご覧ください。

#### 一般提供開始 - Microsoft Entra ID での macOS 向けプラットフォーム SSO

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** SSO

本日、macOS のプラットフォーム SSO が Microsoft Entra ID で一般提供されることが発表されました。 プラットフォーム SSO は、Apple Devices 用の Microsoft Enterprise SSO プラグインの機能強化であり、Mac デバイスの使用と管理がこれまで以上にシームレスかつ安全になります。 パブリック プレビューの開始時に、Platform SSO はMicrosoft Intuneで動作します。 他の Mobile デバイス管理 (MDM) プロバイダーは近日公開予定です。 サポートと可用性の詳細については、MDM プロバイダーにお問い合わせください。 詳細については次を参照してください:

- [macOS Platform シングル サインオンの概要](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso)
- [Microsoft Intune を使用した macOS デバイス向けのプラットフォーム SSO 構成ガイド](https://learn.microsoft.com/ja-jp/intune/intune-service/configuration/platform-sso-macos)
- [NIST SP 800-63 および EO 14028 の要件を満たすように macOS Platform SSO (PSSO) を構成する](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso-deployment-eocustomers)
- [プライマリ更新トークン (PRT) について](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)

#### 一般提供 - Microsoft Entra 外部 IDでのサインイン、サインアップ、およびサインアウトエクスペリエンスのためのネイティブ認証 JavaScript SDK の有効化。

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** 開発者エクスペリエンス

新しいネイティブ認証 [JavaScript SDK](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-sdk-sign-in) を使用して、Microsoft Entra 外部 IDのシングル ページ アプリケーションのサインイン、サインアップ、サインアウトエクスペリエンスを構築します。

#### 一般提供 - FLW の QR + PIN シンプル認証方法

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra IDの QR コード認証が一般公開され、現場担当者は QR コードと個人用 PIN を使用して迅速かつ安全にサインインできます。 この認証方法は、Microsoft Entra ID、マイ スタッフ、またはMicrosoft Graph API を使用してプロビジョニングできます。 ユーザーは、モバイル デバイス (Android、iOS、iPadOS) でサインインするには、 `https://login.microsoftonline.com`にアクセスし、[サインイン] オプション &gt; [組織にサインイン] &gt; QR コードを使用してサインインします。これは、すべてのアプリで使用できる Web ベースのサインイン オプションです。 さらに、Microsoft Teams、MHS、Bluefletch、Jamf などの一部のアプリケーションでは、ログイン ページの専用の "* QR コードでサインイン*" ボタンがサポートされ、シームレスなエクスペリエンスが得られます。 詳細については次を参照してください:

- [Microsoft Entra ID の認証方法 - QR コード認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-qr-code)
- Microsoft Entra ID

#### パブリック プレビュー - 新しい一括操作機能

**種類:** 新機能**サービス カテゴリ:** ディレクトリ管理**製品の機能:** エンド ユーザー エクスペリエンス

Microsoft Entra IDの新しい一括操作では、Groups、Devices、User Export を管理し、作成、更新、削除などの一括操作を可能にするエクスペリエンスが強化されています。 この合理化されたサービスにより、パフォーマンスが向上し、タイムアウトが削減され、特に大規模なテナントのスケーリング制限が削除されます。

**手記：** 現在、新しい一括操作サービスでは **、グループ**、 **デバイス**、 **ユーザー エクスポート** のみがサポートされています。 **エンタープライズ アプリケーション**などの追加エンティティのサポートは近日公開予定です。 詳細については、「[Microsoft Entra ID (プレビュー) での一括操作](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations)」を参照してください。

### 2025 年 7 月

#### 一般提供開始 - Microsoft Entra 外部 ID: カスタムサードパーティーメールによる OTP（ワンタイムパスワード）プロバイダー

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品機能:** サード パーティ統合

サード パーティの電子メール OTP プロバイダーを使用して、Microsoft Entra 外部 IDのサインインおよびサインアップ フローの電子メール OTP 通知をカスタマイズします。 新しい "カスタム 電子メール OTP プロバイダー" カスタム認証拡張機能を使用すると、Azure Communication Service (ACS) または SendGrid などのサード パーティ プロバイダーを使用して、エンド ユーザー認証エクスペリエンスを通じてブランド化の一貫性を維持できます。 詳細については、「 [1 回限りパスコード送信イベント用にカスタム電子メール プロバイダーを構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-email-otp-get-started)する」を参照してください。

#### 一般提供 - Microsoft Entra Connect 同期でのアプリケーション ベースの認証

**種類:** 新機能**サービス カテゴリ:** Microsoft Entra Connect**製品機能:** Microsoft Entra Connect

Application-Based 認証 (ABA) 機能が、Microsoft Entra Connect の既定の認証方法になりました。 これにより、Microsoft Entra Connect は、ローカルに保存されたパスワードに依存することなく、Microsoft Entra IDで安全に認証できます。 この機能では、Microsoft Entra ID アプリケーション ID と Oauth 2.0 クライアント資格情報フローを使用して、Microsoft Entra IDで認証します。 Microsoft Entra Connect は、顧客のEntra ID テナントにシングルテナントのサードパーティ アプリケーションを自動的に作成し、証明書をアプリケーションの資格情報として登録し、ディレクトリ同期に必要なアクセス許可を付与します。

この変更の Microsoft Entra Connect Sync .msi インストール ファイルは、Microsoft Entra管理センターの [Microsoft Entra Connect](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) でのみ使用できます。

変更の詳細については、 [バージョン履歴ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) を参照してください。

#### 一般提供 – Microsoft Entraでの「Security Copilot」

**種類:** 新機能**サービスカテゴリー:** Copilot**製品の機能:** ID のセキュリティと保護

Microsoft EntraでCopilotと対話して、脅威を調査し、従業員とゲストの ID ライフサイクルを管理し、ユーザー、アプリ、アクセス全体で迅速にアクションを実行できるようになりました。 このすべては、カスタム クエリやスクリプトを記述せずに自然言語で動作します。 詳細については、「Microsoft Entra のCopilot」を参照してください。

#### 一般提供 - Microsoft Entraでの条件付きアクセスの最適化エージェント

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

Microsoft Entraの条件付きアクセス最適化エージェントは、既存のポリシーの対象とされない新しいユーザーまたはアプリを監視し、セキュリティギャップを埋めるために必要な更新プログラムを特定し、ID チームが 1 つの選択で適用するためのクイック修正を推奨します。 詳細については、「[Microsoft Security Copilot を使用した Microsoft Entra 条件付きアクセスの最適化エージェント](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization)」を参照してください。

#### 一般提供 - 条件付きアクセス エージェントでは、レポートオンリーポリシーをエージェントによって作成することの無効化をサポートします

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

条件付きAccess最適化エージェントで、エージェントがレポートのみのモード ポリシーを自律的に作成できるかどうかを管理者が構成できるようにする新しい設定がサポートされるようになりました。 オフにすると、エージェントは管理者の承認時にのみポリシーを作成します。 詳細については、[Microsoft Security Copilot を使用した Microsoft Entra 条件付きアクセス 最適化エージェント](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization) を参照してください。

#### 一般公開 - 更新トークンを取り消す新しいライフサイクル ワークフロー タスク

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

お客様は、従業員が組織内に移動または退職したときに、access トークンを自動的に取り消すようにライフサイクル ワークフロー タスクを構成できるようになりました。 詳細については、「 [ユーザーのすべての更新トークンを取り消す」を参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#revoke-all-refresh-tokens-for-user)。

#### 一般提供 - Microsoft Entra Connect Sync の管理者イベントの監査

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品機能:** Microsoft Entra Connect

管理者監査ログ機能を使用すると、グローバル管理者またはハイブリッド管理者が Microsoft Entra Connect Sync 構成に加えた変更を監視できます。 同期規則、認証設定 (機能の有効化または無効化など)、フェデレーション設定の変更など、Microsoft Entra同期ウィザード、PowerShell、または同期規則エディターを使用して実行されたアクションをキャプチャします。 これらのイベントは、Windows イベント ビューアー内の専用の Microsoft Entra Connect Sync 監査ログ チャネルに記録され、ID インフラストラクチャの変更をより詳細に把握できます。 この機能は、トラブルシューティング、運用アカウンタビリティ、規制コンプライアンスをサポートします。

この変更に対する Microsoft Entra Connect Sync .msi インストール ファイルは、[Microsoft Entra 接続ウィンドウ](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)内のMicrosoft Entra管理センターでのみ使用できます。

変更の詳細については、 [バージョン履歴ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) を参照してください。

#### 一般提供 - Microsoft Graph リソースのBicep テンプレート

**種類:** 新機能**サービス カテゴリ:** MS Graph**製品の機能:** 開発者エクスペリエンス

Microsoft Graph リソース用のBicep テンプレートを使用すると、Bicep テンプレート ファイルとAzure リソースを使用して、限られた一連のMicrosoft Graph リソース (主にMicrosoft Entra ID リソース) を作成、デプロイ、管理できます。

- Azureお客様は、使い慣れたツールを使用して、Azure リソースと、依存するMicrosoft Entra リソース (アプリケーションやサービス プリンシパルなど) を、Infrastructure-as-Code (IaC) と DevOps プラクティスを使用してデプロイできます。
- また、既存のMicrosoft Entraのお客様がBicep テンプレートと IaC プラクティスを使用してテナントのMicrosoft Entra リソースをデプロイおよび管理するための扉も開きます。

詳細については、Microsoft GraphBicep テンプレート>を参照してください。

#### 一般提供 - 条件付きアクセス「What If API」

**種類:** 新機能**Service category:** 条件付きアクセス**製品機能:** アクセス制御

条件付きaccess What If API を使用して、ポリシーがユーザー ID とワークロード ID のサインインに与える影響をプログラムでテストできます。

#### 一般提供 - 事前統合ギャラリー アプリまたは顧客 SAML アプリを使用した Enterprise App SSO

**種類：** 機能の変更**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** SSO

エンタープライズ アプリの SSO とユーザー プロビジョニング SAML ベースのシングル サインオン (SSO) と、ユーザー プロビジョニング フローを含むギャラリー アプリが一般公開 (GA) になりました。 これらの機能は、セキュリティで保護されたaccessを合理化し、エンタープライズ アプリケーション全体のユーザー ライフサイクル管理を自動化するのに役立ちます。 詳細については次を参照してください:

- [エンタープライズ アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-enterprise-application)
- [外部テナントに SAML アプリを登録する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-register-saml-app)
- [外部テナントでサポートされている機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers#enterprise-applications)

#### パブリック プレビュー - 同期されたアクティブ ディレクトリ グループの権限のソースをクラウドに移行する

**種類:** 新機能**サービス カテゴリ:** グループ管理**製品機能:** Microsoft Entra Connect と Microsoft Entra Cloud Sync

オブジェクト レベルの Source of Authority (SOA) を使用すると、管理者は、Active Directory (AD) から同期された特定のグループをMicrosoft Entra IDからクラウド編集可能なオブジェクトに変換できます。これは、AD から同期されなくなり、最初にクラウドで作成されたかのように動作します。 この機能は、段階的な移行プロセスをサポートし、ユーザーと運用への影響を最小限に抑えるようにしながら、AD への依存関係を減らします。 Entra Connect Sync と Cloud Sync の両方で、これらのオブジェクトの SOA スイッチが認識されます。 さらに、管理者は、グループ プロビジョニングから AD へのこれらの SOA 変換されたセキュリティ グループを含めることで、Microsoft Entra ガバナンスを使用して、クラウドから AD セキュリティ グループに関連付けられている Kerberos ベースのアプリケーションを管理できます。 同期されたグループの SOA を AD から Microsoft Entra ID に切り替えるオプションは、現在パブリック プレビューで使用できます。 詳細については、「 [クラウド優先の体制を採用する: グループの機関ソースをクラウドに変換する (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview)を参照してください。

#### 一般提供 - 制限付き管理の管理単位

**種類:** 新機能**サービス カテゴリ:** RBAC**製品機能:** 認可/アクセス委任

制限管理の管理単位を使用すると、指定した特定のユーザーまたはアプリケーションのみに、ユーザー、グループ、またはデバイスへのアクセスを簡単に制限できます。 テナント レベルの管理者 (グローバル管理者を含む) は、管理単位をスコープとしたロールが明示的に割り当てられている場合を除き、制限付き管理単位のメンバーを変更できません。 これにより、テナント レベルのロールの割り当てを削除しなくても、テナント内の機密性の高いグループまたはユーザー アカウントのセットを簡単にロックダウンできます。 詳細については、「[Microsoft Entra ID の制限付き管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-restricted-management)」を参照してください。

### 2025 年 6 月

#### 一般提供 – Microsoft Entra職場または学校の既定の背景イメージへの更新

**種類：** 機能の変更**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

2025 年 9 月 29 日から、Microsoft Entraの職場または学校の認証画面の既定の背景画像に変更が行われます。 この新しい背景は、ユーザーが自分のアカウントへのサインイン、生産性の向上、および気が散る作業の最小化に集中できるように設計されています。 これにより、Microsoftの最新化された[Fluent](https://microsoft.design/articles/four-principles-for-the-future-of-design/)設計言語に合わせて、Microsoftの認証フロー全体で視覚的な一貫性と簡素化されたユーザー エクスペリエンス>確保することを目指しています。 エクスペリエンスの外観が一貫していると感じると、ユーザーはユーザーが知り、信頼できる使い慣れたエクスペリエンスが得られます。

**何が変更されていますか?**

この更新プログラムは、機能に変更を加えずに、視覚的なユーザー インターフェイスの更新のみを行います。 この変更は、 [会社のブランドが](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding) 適用されない画面、またはユーザーが [既定の背景画像](https://aka.ms/entradefaultbackground)を表示する画面にのみ影響します。 スクリーンショットを含むドキュメントを更新し、ヘルプ デスクに通知することをお勧めします。 テナントの会社のブランドでカスタム背景イメージを構成した場合、ユーザーに変更はありません。

**その他の詳細:**

1. **カスタム 背景が構成されていないテナント:** a. カスタムの背景がないテナントでは、すべての認証画面に変更が表示されます。 b。 この背景を変更し、カスタムの背景を使用するには、 [会社のブランドを](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)構成します。
2. **カスタム 背景が構成されているテナント:** a. カスタム の背景が構成されているテナントでは、URL にテナント ID パラメーターが指定されていない場合にのみ変更が表示されます (たとえば、ドメイン ヒントやカスタム URL **なしで** 直接 login.microsoftonline.com)。 b。 その他のすべての画面で、カスタム背景が構成されているテナントについては、すべてのクライアントでエクスペリエンスに**変更はありません**。
3. **Entra 外部 ID テナントでは、すべてのクライアントでのエクスペリエンスの変更は表示されません**

**何をする必要がありますか？**

アクションは必要ありません。 更新プログラムは、2025 年 9 月 29 日から自動的に適用されます。

#### 一般提供 - US Gov クラウドでの API 駆動型プロビジョニング

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ガバナンス

API 主導のプロビジョニングは、US Gov クラウドで一般提供されるようになりました。 この機能により、US Gov クラウドのお客様は、権限のある任意のソースからMicrosoft Entra IDおよびオンプレミスの Active Directoryに ID データを取り込むことができます。 詳細については、「 [Graph Explorer を使用した API 主導の受信プロビジョニングのクイック スタート」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer)。

#### 非推奨 - 条件付きアクセスの概要監視タブは廃止されます

**種類：** 廃止**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

7 月 18 日以降、Microsoft Entra管理センターの [条件付きアクセスの概要の監視] タブは廃止され、8 月 1 日までに完了します。 この日付を過ぎると、管理者はこのタブにaccessできなくなります。条件付きAccess Per-Policyレポートと分析情報とレポート ダッシュボードに移行することをお勧めします。どちらも信頼性が高く、精度が高く、お客様から大幅に優れたフィードバックを受け取っています。 [Per-Policy Reporting](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#policy-impact) および [Insights and Reporting](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting) について学習します。

#### 一般提供 - Microsoft Entra でMicrosoft Security Copilotを使用してライフサイクル ワークフローを管理する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

お客様は、Microsoft EntraのMicrosoft Security Copilotで自然言語を使用してライフサイクル ワークフローを管理およびカスタマイズできるようになりました。 ライフサイクル ワークフロー (LCW) Copilot ソリューションでは、自然言語を使用して主要なワークフロー構成と実行タスクを実行するためのステップ バイ ステップ ガイダンスが提供されます。 これにより、顧客は、コンプライアンスのワークフローを監視およびトラブルシューティングするのに役立つ豊富な分析情報をすばやく取得できます。 詳細については、 Microsoft Security Copilotを参照してください。

#### 一般提供 - HR ソースからカスタム セキュリティ属性をプロビジョニングする

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ガバナンス

この機能により、お客様は、権限のある人事ソースからMicrosoft Entra IDに "*カスタム セキュリティ属性*" を自動的にプロビジョニングできます。 サポートされている権限のあるソース: Workday、SAP SuccessFactors、および API 駆動型プロビジョニングを使用して統合された人事システム。 詳細については、「 [HR ソースからカスタム セキュリティ属性をプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-custom-security-attributes)」を参照してください。

#### 一般提供 - 条件付きアクセス対象ユーザー レポート

**種類:** 新機能**Service category:** 条件付きアクセス**製品機能:** アクセス制御

サインイン ログの条件付きアクセス分析機能を使用すると、管理者はサインイン イベントの一部として条件付きアクセスによって評価されたすべてのリソースを確認できます。 詳細については、「 [対象ユーザーレポート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access#audience-reporting)」を参照してください。

#### パブリック プレビュー - テナント間同期 (クラウド間)

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ガバナンス

Microsoft クラウド間のテナント間でのユーザーの作成、更新、および削除を自動化します。 次の組み合わせがサポートされています。

- 商用 -&gt; 米国政府
- 米国政府 - &gt; 商業
- 商用 -&gt; 中国

詳細については、「[テナント間同期の構成](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」を参照してください。

#### 一般提供 - すべてのMicrosoft アプリに対する条件付きアクセスのサポート

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

管理者は、テナントにサービス プリンシパルが表示されている限り、Microsoftからすべてのクラウド アプリに条件付きアクセス ポリシーを割り当てることができます。 詳細については、「[Microsoft クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#microsoft-cloud-applications)を参照してください。

#### 一般提供 - Microsoft Entra Domain Services の双方向フォレスト信頼

**種類:** 新機能**サービス カテゴリ:** Microsoft Entra Domain Services**製品の機能:** Microsoft Entra Domain Services

Microsoft Entra Domain Services の双方向フォレスト信頼が一般提供となりました。 この機能により、組織は、Microsoft Entra Domain Services ドメインと オンプレミスの Active Directory (AD) ドメインの間に信頼関係を確立できます。 組織のニーズに応じて、フォレスト トラストは、一方向の外向き（以前と同様）、一方向の内向き、双方向の 3 つの方向で構成できるようになりました。 フォレストの信頼を使用して、ハイブリッド環境の信頼されたドメイン間でリソースのアクセスを有効にすることができます。 この機能により、Microsoft Entra Domain Servicesを使用してハイブリッド ID 環境を管理する方法に対する制御と柔軟性が向上します。 トラストには、Enterprise または Premium SKU のライセンスが必要です。 詳細については、「[Active Directory のフォレストにおける信頼関係の動作](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-forest-trust)」を参照してください。

#### 一般提供 - 証明機関 (CA) 信頼ストア

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

新しい PKI ベースの CA トラスト ストアは、従来のフラット リスト モデルをより堅牢な構造に置き換え、CA のサイズや数に制限はありません。 PKI の一括アップロード、CRL の更新、発行者のヒント、および従来のストアに対する新しいストアの優先順位付けがサポートされています。 サインイン ログに、使用されたストアが示されるようになりました。これにより、管理者は従来の構成を段階的に除外できます。 詳細については、「[Microsoft Entra の証明書ベース認証の構成方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)」を参照してください。

#### 一般公開 - 証明書失効リスト (CRL) フェール セーフ

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

CRL Fail Safe は、CA を発行するエンド ユーザー証明書に証明書失効リスト (CRL) が構成されていない場合に、CBA 認証が失敗することを保証します。 これにより、失効検証なしで以前に証明書を受け入れることができた重大なセキュリティ ギャップが閉じられます。 管理者は、テナント レベルでこれを有効にし、必要に応じて特定の CA の例外を構成できます。 詳細については、「 [CRL 検証について](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list#enforce-crl-validation-for-cas)」を参照してください。

#### パブリック プレビュー - 証明機関 (CA) の範囲

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

CA スコープを使用すると、管理者は定義されたユーザー グループに特定の CA をバインドできます。 これにより、ユーザーは、彼らに限定された信頼できるソースの証明書のみを使用して認証できるようになります。 これにより、コンプライアンスが強化され、誤って発行された証明書や不正な証明書への露出が軽減されます。 詳細については、「 [証明機関 (CA) のスコープ (プレビュー)」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#certificate-authority-ca-scoping)参照してください。

### 2025 年 5 月

#### 一般提供 - Microsoft Entra 外部 ID: SAML/WS-Fed ID プロバイダーを使用したユーザー認証

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

ユーザーが自分のアカウントと ID プロバイダーを使用してアプリケーションにサインアップしてサインインできるように、SAML または WS-Fed ID プロバイダーを設定します。 ユーザーは ID プロバイダーにリダイレクトされ、サインインが成功した後、Microsoft Entraにリダイレクトされます。 詳細については、「 [SAML/WS-Fed ID プロバイダー」を](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-overview)参照してください。

#### 一般公開 - Microsoft Entra 外部 IDにおける属性収集前/後のカスタム拡張機能

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** 拡張性

属性コレクションの事前/事後カスタム拡張機能を使用して、セルフサービス サインアップ フローをカスタマイズします。 これには、サインアップのブロック、属性値の事前入力、検証、変更が含まれます。 詳細については、「 [属性コレクションの開始イベントと送信イベントのカスタム認証拡張機能を作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection)」を参照してください。

#### パブリック プレビュー - Microsoft Entra Connect Sync でのアプリケーション ベースの認証のロールアウト

**種類:** 新機能**サービス カテゴリ:** Microsoft Entra Connect**製品機能:** Microsoft Entra Connect

Microsoft Entra Connect では、[Microsoft Entra Connector アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions) を作成して使用して、Active DirectoryからMicrosoft Entra IDへの ID の認証と同期を行います。 アカウントは、ローカルに保存されたパスワードを使用してMicrosoft Entra IDで認証します。 アプリケーションとのMicrosoft Entra Connect 同期プロセスのセキュリティを強化するために、Microsoft Entra ID アプリケーション ID と Oauth 2.0 クライアント資格情報フローを使用してMicrosoft Entra IDで認証する "*Application ベースの認証" (ABA)* のサポートをロールアウトしました。 これを有効にするために、Microsoft Entra Connect は、顧客の Microsoft Entra ID テナントにシングル テナントサード パーティ アプリケーションを作成し、アプリケーションの資格情報として証明書を登録し、オンプレミスのディレクトリ同期を実行するアプリケーションを承認します。

この変更の Microsoft Entra Connect Sync .msi インストール ファイルは、[Microsoft Entra 接続ウィンドウ](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)内のMicrosoft Entra管理センターでのみ使用できます。

変更の詳細については、 [バージョン履歴ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) を参照してください。

#### 一般提供 – 条件付きAccess ポリシーへの影響を分析する

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

個々の条件付きアクセス ポリシーのポリシー影響ビューを使用すると、管理者は各ポリシーが最近のサインインにどのように影響したかを理解できます。この機能により、Microsoft Entra 管理センターに明確な組み込みのグラフが提供されるため、Log Analyticsなどの追加のツールやリソースを必要とせずに、影響を簡単に視覚化および評価できます。 詳細については、「 [ポリシーへの影響](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#policy-impact-preview)」を参照してください。

#### パブリック プレビュー – グローバル セキュリティで保護されたAccessのデプロイ ログのサポート

**種類:** 新機能**サービス カテゴリ:** レポーティング**製品の機能:** 監視とレポート

デプロイ ログ機能を使用すると、グローバル セキュア Accessで行われた構成変更の状態と進行状況を把握できます。 デプロイ ログは、更新プログラムを管理者に発行し、エラーが発生した場合はプロセスを監視します。 他のログ機能とは異なり、デプロイ ログは特に構成の更新の追跡に重点を置きます。 これらのログは、管理者がグローバル ネットワーク全体でプロファイルの再配布やリモート ネットワーク更新プログラムの転送などの展開の更新プログラムを追跡およびトラブルシューティングするのに役立ちます。 詳細については、「 [グローバルなセキュリティで保護されたアクセスのデプロイ ログを使用する方法 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-view-deployment-logs)」を参照してください。

### 2025 年 4 月

#### パブリック プレビュー - Microsoft Entraの条件付きアクセス最適化エージェント

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

Microsoft EntraConditional Access Optimization Agent> は、既存のポリシーの対象ではない新しいユーザーまたはアプリを監視し、セキュリティ ギャップを埋めるために必要な更新プログラムを特定し、ID チームが 1 つの選択で適用するためのクイック修正を推奨します。 詳細については、「[Microsoft Entra 条件付きアクセス最適化エージェント](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization)を参照してください。

#### パブリック プレビュー - Microsoft Entra ID ガバナンス: マイ アクセスで推奨されるアクセス パッケージ

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

2024 年 12 月に、My Accessで新機能が導入されました。これは、推奨されるaccess パッケージの一覧です。 ユーザーは、長いリストをスクロールすることなく、ピアのaccess パッケージと以前の割り当てに基づいて、最も関連性の高いaccess パッケージを表示します。 2025 年 5 月までに、提案は既定で有効になり、管理者がマイ アクセス設定を表示するための新しいカードが Microsoft Entra Admin Center エンタイトルメント管理コントロール構成に導入されます。 管理者は、この設定を使用して、推奨されるaccess パッケージのピアベースの分析情報を有効にすることをお勧めします。 詳細については、「マイアクセス (プレビュー)」の「提案されたアクセスパッケージ」を参照してください。

#### パブリック プレビュー - 条件付きアクセス What If 検証 API

**種類:** 新機能**Service category:** 条件付きアクセス**製品機能:** アクセス制御

条件付きアクセス What If 評価 API – Microsoft Graph APIを使用して What If ツールを利用し、ユーザーとサービス プリンシパルのサインイン時にテナント内の条件付きアクセス ポリシーの適用性をプログラムで評価します。詳細については、「[conditionalAccessRoot: evaluate](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-evaluate)を参照してください。

#### パブリック プレビュー - ライフサイクル ワークフローを使用して mover および leaver シナリオの更新トークンを管理する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

お客様は、従業員が組織内に移動または退職したときに、access トークンを自動的に取り消すようにライフサイクル ワークフロー タスクを構成できるようになりました。 詳細については、「 [ユーザーのすべての更新トークンを取り消す」を参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#revoke-all-refresh-tokens-for-user)。

#### 一般提供 - Microsoft Entra アプリで資格情報としてマネージド ID を使用する

**種類:** 新機能**Service category:** Azure リソースのマネージド ID**製品の機能:** ID のセキュリティと保護

マネージド ID をMicrosoft Entra アプリのフェデレーション資格情報として使用できるようになりました。これにより、シングルテナントとマルチテナントの両方のシナリオで、セキュリティで保護されたシークレットのない認証を有効にできます。 これにより、Microsoft Entra アプリを使用してテナント間でAzureリソースにアクセスするときに、クライアント シークレットまたは証明書を格納および管理する必要がなくなります。 この機能は、Microsoftの [Secure Future Initiative](https://www.microsoft.com/trust-center/security/secure-future-initiative) システム間での ID とシークレットの保護の柱に沿っています。 この機能を構成する方法については、 [公式ドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity)。

#### 変更の計画 - Microsoft Entra Connect Sync でのアプリケーション ベースの認証のロールアウト

**種類:** 変更の計画**サービス カテゴリ:** Microsoft Entra Connect**製品機能:** Microsoft Entra Connect

**変更される内容**

Microsoft Entra Connect では、[Microsoft Entra Connector アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions) を作成して使用して、Active DirectoryからMicrosoft Entra IDへの ID の認証と同期を行います。 アカウントは、ローカルに保存されたパスワードを使用してMicrosoft Entra IDで認証します。 Microsoft Entra Connect アプリケーション同期プロセスのセキュリティを強化するために、今後 1 週間に、Microsoft Entra ID アプリケーション ベースの ID と Oauth 2.0 クライアント資格情報フローを使用してMicrosoft Entra IDで認証する "アプリケーション ベースの認証" (ABA) のサポートをロールアウトします。 これを有効にするために、Microsoft Entra Connect は、顧客のMicrosoft Entra ID テナントに単一テナントのサード パーティ アプリケーションを作成し、アプリケーションの資格情報として証明書を登録し、オンプレミスのディレクトリ同期を実行するアプリケーションを承認します

この変更の Microsoft Entra Connect Sync .msi インストール ファイルは、[Microsoft Entra Connect ペイン内のMicrosoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)でのみ使用できます。

変更の詳細については、来週の [バージョン履歴ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) を参照してください。

### 2025 年 3 月

#### Microsoft Entra Permissions Management販売終了と廃止

**種類:** 変更の計画**サービス カテゴリ:** 他**製品の機能:** アクセス許可の管理

2025 年 4 月 1 日より、Microsoft Entra Permissions Management (MEPM) は新しい Enterprise Agreement または直接のお客様に販売できなくなります。 さらに、5月1日から、新しいCSP顧客向けには販売されなくなります。 2025 年 10 月 1 日より、Microsoft Entra Permissions Managementを廃止し、本製品のサポートを中止します。

既存のお客様は、2025 年 9 月 30 日までこの製品のaccessを保持し、現在の機能を継続的にサポートします。 Delinea と提携し、Microsoft Entra Permissions Managementで提供されるものと同様の機能を提供する代替ソリューションである [Privilege Control for Cloud Entitlements (PCCE)](https://delinea.com/products/privilege-control-for-cloud-entitlements) を提供しています。 Microsoft Entra Permissions Managementを段階的に廃止する決定は、イノベーション ポートフォリオを深く検討した後に行われました。また、差別化された分野に合わせて最適なイノベーションを提供し、隣接関係に関するエコシステムとパートナーを結ぶ方法に重点を置く方法について説明しました。 引き続き、Microsoft Entraポートフォリオ全体で最上位レベルのソリューションを提供することに取り組んでいます。 詳細については、「[重要なお知らせ: Microsoft Entra Permissions Management の販売終了と廃止](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/important-change-announcement-microsoft-entra-permissions-management-end-of-sale/4399382)」を参照してください。

#### パブリック プレビュー - Microsoft Entraでリンク可能な識別子を使用して ID アクティビティを追跡および調査する

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoftは、リンク可能なトークン識別子を標準化し、Microsoft Entraとワークフロー監査ログの両方で公開します。 これにより、お客様はログに参加して、悪意のあるアクティビティを追跡し、調査することができます。 現在、リンク可能な識別子は、Microsoft Entra サインイン ログ、Exchange Online 監査ログ、Microsoft Graph アクティビティ ログで利用可能です。

詳細については、「Microsoft Entra (プレビュー)を参照してください。

#### 一般提供ー条件付きアクセス再認証ポリシー

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

機密性の高いアプリケーションへのアクセス、VPN の背後にあるリソースのセキュリティ保護、PIM での特権ロールの昇格のセキュリティ保護など、ユーザーが特定のアクションを実行するたびに、新しい認証を要求するシナリオでは、毎回再認証を要求できます。 詳細については、「[毎回再認証を行う](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime#require-reauthentication-every-time)を参照してください。

#### 一般提供 - Microsoft Entra Domain Servicesのカスタム属性のサポート

**種類:** 新機能**サービス カテゴリ:** Microsoft Entra Domain Services**製品の機能:** Microsoft Entra Domain Services

Microsoft Entra Domain Servicesのカスタム属性が一般公開されました。 この機能により、お客様はマネージド ドメインでカスタム属性を使用できます。 レガシ アプリケーションは、多くの場合、情報の格納、オブジェクトの分類、リソースに対するきめ細かいaccess controlの適用を行うために、過去に作成されたカスタム属性に依存します。 たとえば、これらのアプリケーションでは、カスタム属性を使用して従業員 ID をディレクトリに格納し、アプリケーション LDAP 呼び出しでこれらの属性に依存する場合があります。 レガシ アプリケーションの変更はコストがかかり、リスクが高く、お客様がこれらの変更を行うために必要なスキルや知識が不足している可能性があります。 Microsoft Entra Domain Servicesではカスタム属性がサポートされるようになりました。これにより、お客様はレガシ アプリケーションを変更せずに Azure クラウドに移行できます。 また、Microsoft Entra IDからカスタム属性を同期するためのサポートも提供され、お客様はクラウド内のMicrosoft Entra ID サービスの恩恵を受けることができます。 詳細については、「[Microsoft Entra Domain Services の Custom 属性](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-custom-attributes)」を参照してください。

#### 一般公開プレビュー - 条件付きアクセスごとのポリシーレポート

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

条件付きアクセス Per-Policy レポートを使用すると、管理者は、Log Analyticsを使用せずに、有効な条件付きアクセス ポリシーとレポートのみの条件付きアクセス ポリシーが組織に与える影響を簡単に評価できます。 この機能は、Microsoft Entra管理センターの各ポリシーのグラフを表示し、テナントの過去のサインインに対するポリシーの影響を視覚化します。詳細については、「[Policy impact (Preview)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#policy-impact-preview)を参照してください。

#### パブリック プレビュー - マルチテナント アプリの作成またはプロモーションを制限する

**種類:** 新機能**サービス カテゴリ:** ディレクトリ管理**製品の機能:** 開発者エクスペリエンス

アプリ [管理ポリシー フレームワーク](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy) に新しい機能が追加されました。これにより、マルチテナント アプリケーションの作成または昇格の制限が可能になり、管理者はアプリ環境をより詳細に制御できます。

管理者は、アプリで指定された signInAudience 値がポリシーで許可されていない場合に、新しい [対象ユーザー](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy#what-restrictions-can-be-managed-in-microsoft-graph) 制限を使用してテナントの既定またはカスタム アプリ ポリシーを構成して、新しいアプリの作成をブロックできるようになりました。 さらに、ターゲット値がポリシーで許可されていない場合は、既存のアプリが signInAudience を変更できないように制限できます。 これらのポリシーの変更は、アプリの作成または更新操作中に適用され、アプリケーションのデプロイと使用状況を制御できます。 詳細については、「 [audiencesConfiguration リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/audiencesconfiguration)」を参照してください。

#### 一般提供 - Microsoft Entra 管理センターで Microsoft Entra Connect Sync をダウンロードする

**種類:** 変更の計画**サービス カテゴリ:** Microsoft Entra Connect**製品の機能:** ID ガバナンス

Microsoft Entra Connect Sync .msi インストール ファイルは、[Microsoft Entra 接続ウィンドウ](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)内のMicrosoft Entra 管理センターでも使用できます。 この変更の一環として、[Microsoft ダウンロード センター](https://www.microsoft.com/en-us/download/details.aspx?id=47594) に新しいインストール ファイルのアップロードを停止します。

#### 一般提供 - デバイス コード フローとレガシ認証フローを制限するように設計された新しいMicrosoft管理条件付きアクセス ポリシー

**種類：** 機能の変更**Service category:** 条件付きアクセス**製品機能:** アクセス制御

セキュリティを強化し、進化するサイバー脅威からお客様を保護するための継続的な取り組みの一環として、デバイス コード フローとレガシ認証フローを制限するように設計された 2 つの新しいMicrosoft管理された条件付きアクセス ポリシーを展開しています。 これらのポリシーは、既定で組織を保護するための堅牢なセキュリティ対策を提供することを目的とする、より広範な [Secure Future Initiative](https://www.microsoft.com/trust-center/security/secure-future-initiative) の既定のセキュリティ保護原則に合わせて調整されています。

#### 非推奨 - 同期ウィザードへの影響を回避するために、Microsoft Entra Connect 同期バージョンをアップグレードする

**種類：** 廃止**サービス カテゴリ:** Microsoft Entra Connect**製品機能:** Microsoft Entra Connect

Microsoft Entraの新機能[Blog](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/whats-new-in-microsoft-entra---september-2024/4253153)およびMicrosoft 365 センターのコミュニケーションで発表されているように、 お客様は、2025 年 4 月 7 日より前に、商用クラウドの場合は [2.4.18.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#24180)[2.4.21.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#24210) に接続同期バージョンをアップグレードする必要があります。 接続同期ウィザードの重大な変更は、スキーマの更新、ステージング モードの構成、ユーザー サインインの変更など、認証を必要とするすべての要求に影響します。 詳細については、「 [最小バージョン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/harden-update-ad-fs-pingfederate#minimum-versions)」を参照してください。

### 2025 年 2 月

#### 一般提供 - 認証方法の移行ウィザード

**種類:** 新機能**サービス カテゴリ:** MFA**製品の機能:** ユーザー認証

Microsoft Entra管理センターの認証方法の移行ガイドでは、メソッド管理を [legacy MFA ポリシーと SSPR ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage#legacy-mfa-and-sspr-policies) から [converged 認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage)に自動的に移行できます。 2023 年、従来の MFA および SSPR ポリシーで認証方法を管理する機能が 2025 年 9 月に廃止される予定であることが発表されました。 これまで、組織は集中型ポリシーの [移行トグルを](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-methods-manage#start-the-migration) 使用して、メソッド自体を手動で移行する必要がありました。 移行ガイドを使用して、いくつか選択するだけで移行できるようになりました。 このガイドでは、組織が現在両方の従来のポリシーで有効にしている内容を評価し、必要に応じて確認および編集できるよう、推奨される統合ポリシー構成を生成します。 そこから構成を確認し、こちらで設定を行い、移行を完了としてマークいたします。 詳細については、「 MFA と SSPR ポリシー設定を Microsoft Entra IDを参照してください。

#### パブリック プレビュー – 現場担当者向けのシンプルで高速な認証方法である QR コード認証

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra IDでの QR コード認証のパブリック プレビューを発表し、現場担当者に効率的で簡単な認証方法を提供することに興奮しています。

Microsoft Entra ID認証方法ポリシーに新しい認証方法 "QR コード" が表示されます。 Microsoft Entra ID、My Staff、または MS Graph API を使用して、現場担当者の QR コードを有効にして追加できます。 テナント内のすべてのユーザーには、[サインイン オプション] `https://login.microsoftonline.com` [組織にサインイン] ページ&gt;&gt;移動すると、新しいリンク "QR コードでサインイン" が表示されます。 この新しいリンクは、モバイル デバイス (Android/iOS/iPadOS) でのみ表示されます。 ユーザーは、QR コードを追加して提供する場合にのみ、この認証方法を使用できます。 QR コード認証は BlueFletch と Jamf でも利用できます。 MHS QR コード認証のサポートは、3 月上旬までに一般提供されます。

この機能は、一般公開されるまで 'プレビュー' タグを持っています。 詳細については、「[認証方法 (Microsoft Entra IDの認証方法 - QR コード認証方法 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-qr-code)」を参照してください。

#### パブリック プレビュー - Admin Center UX でのユーザー管理の強化

**種類:** 新機能**サービス カテゴリ:** [ユーザー管理]**製品の機能:** [ユーザー管理]

管理者は、Microsoft Entra管理センターを使用してユーザーを一度に複数選択および編集できるようになりました。 この新機能により、管理者はユーザーのプロパティの一括編集、グループへのユーザーの追加、アカウントの状態の編集などを行うことができます。 この UX の機能強化により、Microsoft Entra 管理センターのユーザー管理タスクの効率が大幅に向上します。 詳細については、「[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info)でユーザーのプロファイル情報と設定を追加または更新する方法に関する記事を参照してください。

#### パブリック プレビュー - Microsoft Entra 外部 ID でのカスタム SAML/WS-Fed 外部 ID プロバイダーのサポート

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

SAML 2.0 または WS-Fed プロトコルをサポートするカスタム構成の ID プロバイダーとのフェデレーションを設定することで、ユーザーがフェデレーション外部プロバイダーの既存のアカウントを使用してアプリケーションにサインアップしてサインインできるようになります。

この機能にはドメイン ベースのフェデレーションも含まれているため、外部 ID プロバイダーの定義済みドメインと一致する電子メール アドレスをサインイン ページに入力したユーザーは、その ID プロバイダーで認証するようにリダイレクトされます。

詳細については、「 [カスタム SAML/WS-Fed ID プロバイダー」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers#custom-samlws-fed-identity-providers)参照してください。

#### パブリック プレビュー - システム優先 MFA の外部認証方法のサポート

**種類:** 新機能**サービス カテゴリ:** MFA**製品機能:** サード パーティ統合

サポートされている方法としての外部認証方法のサポートは、2025 年 3 月の初めにロールアウトを開始します。 システム優先が有効なテナントにこれが導入されていて、ユーザーが外部認証方法ポリシーの対象となっている場合、最も安全な登録方法がMicrosoft Authenticatorの通知であるユーザーには、外部認証方法を求められます。 外部認証方法は、最も安全な方法の一覧の 3 番目として表示されます。 ユーザーが一時Access Pass (TAP) または Passkey (FIDO2) デバイスを登録している場合は、それらの入力を求められます。 さらに、外部認証方法ポリシーのスコープ内のユーザーは、削除する方法が既定のサインイン方法として指定されているか、システムが優先している場合でも、アカウントから登録済みのすべての第 2 要素メソッドを削除できます。 詳細については、「 [システム優先認証 - 認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-system-preferred-authentication)」を参照してください。

#### 一般提供 - ライフサイクル ワークフローの細分化された Microsoft Graph アクセス許可

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローのシナリオで特定の読み取りおよび書き込みアクションを管理するために、より低い特権のアクセス許可を使用できるようになりました。 Microsoft Graphでは、次の詳細なアクセス許可が導入されました。

- LifecycleWorkflows-Workflow.ReadBasic.All
- LifecycleWorkflows-Workflow.Read.All
- LifecycleWorkflows-Workflow.ReadWrite.All
- LifecycleWorkflows-Workflow.Activate
- LifecycleWorkflows-Reports.Read.All
- LifecycleWorkflows-CustomExt.Read.All
- LifecycleWorkflows-CustomExt.ReadWrite.All

詳細については、「[Microsoft Graph アクセス許可のリファレンス](https://learn.microsoft.com/ja-jp/graph/permissions-reference)を参照してください。

### 2025 年 1 月

#### パブリック プレビュー - Microsoft Entra で Microsoft Security CoPilot を使用してライフサイクル ワークフローを管理する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

お客様は、Microsoft Security CoPilotを使用し、自然言語でライフサイクル ワークフローを管理およびカスタマイズできるようになりました。 ライフサイクル ワークフロー (LCW) Copilot ソリューションでは、自然言語を使用して主要なワークフロー構成と実行タスクを実行するためのステップ バイ ステップ ガイダンスが提供されます。 これにより、コンプライアンスのワークフローを監視、トラブルシューティングするために役立つ豊富な分析情報をすばやく取得できます。 詳細については、「 Microsoft Security Copilot (プレビュー)を参照してください。

#### 一般提供 - Microsoft Entra PowerShell

**種類:** 新機能**サービス カテゴリ:** MS Graph**製品の機能:** 開発者エクスペリエンス

PowerShell モジュールMicrosoft Entraシナリオに重点を置いて、Microsoft Entra リソースをプログラムで管理および自動化します。 詳細については、「[Microsoft Entra PowerShell モジュールが一般公開されました](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/microsoft-entra-powershell-module-now-generally-available/4365718)を参照してください。

#### 一般提供 - ダウンストリーム テナントサインインの可視性の向上

**種類:** 新機能**サービス カテゴリ:** レポーティング**製品の機能:** 監視とレポート

Microsoft Securityは、パートナーがダウンストリーム テナントのリソースにアクセスしているときに、すべての顧客が注意する方法を認識していることを確認する必要があります。 対話型サインイン ログは現在、サインイン イベントの一覧を提供していますが、ダウンストリーム テナント リソースにアクセスしているパートナーからのログインは明確に示されません。 たとえば、ログを確認すると、一連のイベントが表示される場合がありますが、追加のコンテキストがない場合、これらのログインがパートナーから別のテナントのデータにアクセスしているかどうかを判断するのは困難です。

パートナー テナントに関連付けられているログインを明確にするために実行できる手順の一覧を次に示します。

1. CrossTenantAccessType 列の "ServiceProvider" 値を書き留めます。
    - このフィルターを適用して、ログ データを絞り込むことができます。 アクティブ化すると、パートナー ログインに関連するイベントが直ちに分離されます。
2. [ホーム テナント ID] 列と [リソース テナント ID] 列を使用します。
    - これらの 2 つの列は、パートナーのテナントからダウンストリーム テナントへのログインを識別します。

パートナーがダウンストリーム テナントのリソースにログインしているのを見た後、実行する重要なフォローアップ アクティビティは、ダウンストリーム環境で発生した可能性のあるアクティビティを検証することです。 確認するログの例としては、Microsoft Entra ID イベント用のMicrosoft Entra監査ログ、Microsoft 365とMicrosoft Entra IDのイベント用のMicrosoft 365統合監査ログ (UAL)、そしてAzure イベント用のAzure Monitorアクティビティ ログがあります。 これらの手順に従うことで、パートナーがダウンストリーム テナントのリソースと環境内の後続のアクティビティにログインするタイミングを明確に識別でき、テナント間のaccessを効率的に管理および監視する機能が強化されます。

前述の列の可視性を高めるために、Microsoft Entraでは、2025 年 3 月 7 日以降、サインイン ログ UX の読み込み時にこれらの列を既定で表示できるようになります。

#### パブリック プレビュー - Microsoft Entra Connect での管理者イベントの監査

**種類:** 新機能**サービス カテゴリ:** Microsoft Entra Connect**製品機能:** Microsoft Entra Connect

接続同期ウィザードと PowerShell で管理者が行った変更のログ記録をサポートする新しいバージョンの Microsoft Entra Connect バージョン 2.4.129.0 がリリースされました。 詳細については、「Microsoft Entra Connect Sync (パブリック プレビュー)を参照してください。

サポートされている場合は、2025 年 2 月にこのバージョンの Microsoft Entra Connect に顧客を自動アップグレードします。 自動アップグレードを希望するお客様の場合は、 [自動アップグレードが構成されていることを確認](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)してください。

アップグレード関連のガイダンスについては、「[Microsoft Entra Connect: 以前のバージョンから最新バージョンへのアップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)」を参照してください。

#### パブリック プレビュー - 柔軟な連合ID認証情報

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** 開発者エクスペリエンス

柔軟なフェデレーション ID 資格情報は、特定の要求に対してワイルドカード 照合を使用する機能を提供することで、既存のフェデレーション ID 資格情報モデルを拡張します。 現在、GitHub、GitLab、Terraform Cloud のシナリオで使用できます。この機能を使用すると、同様のシナリオを管理するために必要な FIC の合計数を減らすことができます。 詳細については、「 [フレキシブル フェデレーション ID 資格情報 (プレビュー)」を](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-flexible-federated-identity-credentials)参照してください。

#### 一般提供 - Microsoft Entra ID 保護でのリアルタイムのパスワードスプレー検出

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

従来、パスワード スプレー攻撃は侵害後、またはハンティング アクティビティの一部として検出されます。 これで、攻撃者がトークンを取得する前に、パスワード スプレー攻撃をリアルタイムで検出するMicrosoft Entra ID 保護が強化されました。 これにより、サインイン フロー中に攻撃が中断され、修復が数時間から数秒に短縮されます。

リスクベースの条件付きアクセスは、この新しいシグナルに自動的に応答し、セッションのリスクを高め、すぐにサインイン試行を確認し、その場でパスワードスプレー攻撃を阻止することができます。 この最先端の検出 (現在一般公開) は、敵対者インザ ミドル (AitM) フィッシングやトークン盗難などの高度な攻撃に対する既存の検出と共に機能し、最新の攻撃に対する包括的なカバレッジを確保します。 詳細については、「Microsoft Entra ID 保護とは

#### 一般提供 - 物理的な削除に対する保護アクション

**種類:** 新機能**サービス カテゴリ:** 他**製品の機能:** ID のセキュリティと保護

お客様は、条件付きAccess ポリシーを構成して、早期のハード削除から保護できるようになりました。 ハード削除の保護されたアクションにより、ユーザー、Microsoft 365 グループ、アプリケーションのハード削除が保護されます。 詳細については、「[Microsoft Entra ID での保護されたアクションとは何ですか？](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)」を参照してください。

#### パブリック プレビュー - アクセス権の昇格イベントがMicrosoft Entra監査ログを介してエクスポートできるようになりました

**種類:** 新機能**サービス カテゴリ:** RBAC**製品の機能:** 監視とレポート

この機能を使用すると、管理者は、Microsoft Entra監査ログを使用して、アクセス権の昇格イベントをファースト パーティおよびサード パーティの SIEM ソリューションの両方にエクスポートしてストリーミングできます。 これにより、検出が強化され、ログ機能が向上し、テナント内のユーザーが Elevate Accessを利用したユーザーを可視化できます。 この機能の使用方法の詳細については、「 [アクセス ログ エントリの昇格を表示する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin?tabs=azure-portal%2Centra-audit-logs#view-elevate-access-log-entries)」を参照してください。

#### 非推奨 - 2025 年 2 月 1 日までに必要なアクション: Azure AD Graph の提供終了

**種類：** 廃止**サービス カテゴリ:** Azure AD Graph**製品の機能:** 開発者エクスペリエンス

Azure AD Graph API サービスは 2020 年に [非推奨] になりました。 [Azure AD Graph API サービスの再提供](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/june-2024-update-on-azure-ad-graph-api-retirement/ba-p/4094534)は 2024 年 9 月に開始され、この廃止の次のフェーズは 2025 年 2 月 1 日に開始されます。 このフェーズは、アクションが実行されない限り、新規および既存のアプリケーションに影響します。 Azure AD Graph の提供終了に関する最新情報については、「[2 月 1 日までに要対応: Azure AD Graph の提供終了](https://aka.ms/AzureADGraphRetirement)」を参照してください。

2 月 1 日から、新規アプリケーションと既存アプリケーションの両方が、拡張機能用に構成されていない限り、Azure AD Graph API を呼び出すことはできなくなります。 テナント間で段階的にこの変更をロールアウトしているため、すぐには影響が見られない場合があります。 この変更の完全なデプロイは、2 月の終わり頃と、国内クラウドデプロイの 3 月末までに予定されています。

まだ行っていない場合は、テナント上のアプリケーションを確認して、Azure AD Graph APIアクセスに依存しているアプリケーションを確認し、2 月 1 日のカットオフ日より前にこれらを軽減または移行することが急務になりました。 Microsoft Graph API に移行していないアプリケーションの場合は、[拡張機能](https://learn.microsoft.com/ja-jp/graph/applications-authenticationbehaviors?tabs=http#allow-extended-azure-ad-graph-access-until-june-30-2025)を設定して、2025 年 6 月 30 日までアプリケーションが Azure AD Graph にアクセスできるように設定できます。

Microsoft Entra推奨事項は、テナントで Azure AD Graph API を使用し、アクションを必要とするアプリケーションを識別するための最適なツールです。 このブログ投稿を参照してください: アクションが必要です: [Azure AD Graph API 廃止](https://techcommunity.microsoft.com/blog/identity/action-required-azure-ad-graph-api-retirement/4090533) に関するステップバイステップのガイダンス。

#### 一般提供 - Microsoft Entra Connect バージョン 2.4.129.0

**種類：** 機能の変更**サービス カテゴリ:** Microsoft Entra Connect**製品機能:** Microsoft Entra Connect

2025 年 1 月 15 日に、管理者イベントの監査をサポートする Microsoft Entra Connect Sync バージョン 2.4.129.0 がリリースされました。 詳細については、[リリース ノート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#241290)を参照してください。 2025 年 2 月に、対象となるお客様をこの最新バージョンの Microsoft Entra Connect に自動的にアップグレードします。 自動アップグレードを希望するお客様の場合は、 [自動アップグレードが構成されていることを確認](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)してください。

#### 非推奨 - レガシ MSOnline および AzureAD PowerShell モジュールが廃止されたときに影響を回避するためのアクションを実行する

**種類：** 廃止**サービス カテゴリ:** レガシ MSOnline および AzureAD PowerShell モジュール**製品の機能:** 開発者エクスペリエンス

Microsoft Entra [change のお知らせ](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/ChangeManagementHubList.ReactView)および Microsoft Entra [Blog](https://techcommunity.microsoft.com/blog/identity/important-update-deprecation-of-azure-ad-powershell-and-msonline-powershell-modu/4094536) で発表されているように、MSOnline および Microsoft Azure AD PowerShell モジュール (Microsoft Entra ID用) は 2024 年 3 月 30 日に廃止されました。

MSOnline PowerShell モジュールの提供終了は、2025 年 4 月上旬に開始され、2025 年 5 月下旬に終了します。 MSOnline PowerShell を使用している場合は、2025 年 3 月 30 日までに、MSOnline の使用を [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) または [Microsoft Entra PowerShell](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/installation) に移行することで、廃止後の影響を回避するためのアクションを実行する必要があります。

重要なポイント

- MSOnline PowerShell は、2025 年 4 月上旬から 2025 年 5 月下旬まで廃止され、動作を停止します
- AzureAD PowerShell は 2025 年 3 月 30 日以降サポートされなくなりますが、2025 年 7 月初めに廃止される予定です。 この延期は、MSOnline PowerShell の移行を完了する時間を可能にするためです
- MSOnline PowerShell の提供終了に対する顧客の準備を確実にするために、2025 年 1 月から 2025 年 3 月までの間にすべてのテナントに対して一連の一時的な停止テストが行われます。

詳細については、「 [アクションが必要です:MSOnline と AzureAD PowerShell の提供終了 - 2025 の情報とリソース](https://techcommunity.microsoft.com/blog/identity/action-required-msonline-and-azuread-powershell-retirement---2025-info-and-resou/4364991)」を参照してください。

### 2024 年 12 月

#### 一般提供 - Microsoft Entraの新機能

**種類:** 新機能**サービス カテゴリ:** レポーティング**製品の機能:** 監視とレポート

Microsoft Entra の新機能では、Microsoft Entra 製品の更新に関する包括的なビューが提供されます。これには、製品ロードマップ (パブリックプレビューや最近の GA など) や変更のお知らせ (非推奨、破壊的変更、機能変更、Microsoft 管理のポリシーなど) が含まれます。 これは、Microsoft Entra管理者が製品の更新プログラムを検出するためのワンストップ ショップです。

#### パブリック プレビュー - Microsoft Entra ID ガバナンス: 承認者は MyAccess でアクセスを取り消すことができます

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

Microsoft Entra ID ガバナンスユーザーの場合、アクセス パッケージ要求の承認者は、MyAccess で決定を取り消すようになりました。 承認アクションを実行したユーザーのみが、accessを取り消すことができます。 この機能をオプトインするには、管理者が [ID ガバナンス設定ページ](https://entra.microsoft.com/#view/Microsoft_AAD_ERM/DashboardBlade/%7E/elmSetting)に移動し、この機能を有効にします。 詳細については、「[マイ Access ポータルとは](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview#approve-or-deny-a-request)」を参照>。

#### 一般提供 - SSPR ポリシー監査ログの拡張

**種類:** 新機能**サービス カテゴリ:** セルフサービス パスワード リセット**製品の機能:** 監視とレポート

1 月中旬から、SSPR ポリシーに加えられた変更の監査ログが改善されます。

この改善により、有効化や無効化を含む SSPR ポリシー構成へのあらゆる変更により、加えられた変更の詳細を含む監査ログ エントリが生成されるようになります。 加えて、変更前の値と現在の値の両方が監査ログ内に記録されます。 この追加情報は、監査ログ エントリを選択し、エントリ内の [変更されたプロパティ] タブを選択すると確認できます。

これらの変更は、段階的にロールアウトされます。

- フェーズ 1 には、認証方法、登録、通知、カスタマイズの構成設定のログが含まれます。
- フェーズ 2 には、オンプレミス統合構成設定のログ記録が含まれます。

この変更は自動的に行われるため、管理者が実行するアクションはありません。 この変更に関する詳細と詳細については、「[Microsoft Entra監査ログのカテゴリとアクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)を参照してください。

#### 一般提供 - MyAccount でのプロフィール写真の更新

**種類:** 新機能**サービス カテゴリ:** マイ プロファイル/アカウント**製品の機能:** エンド ユーザー エクスペリエンス

ユーザーは、MyAccount ポータルから直接プロフィール写真を更新できるようになりました。 この変更により、ユーザーのアカウントのプロフィール写真セクションに新しい編集ボタンが表示されます。

一部の環境では、ユーザーによるこの変更を禁止する必要があります。 グローバル管理者は、Microsoft Graph API を使用し、テナント全体のポリシーによってこれを管理できます。この管理は、[Microsoft 365 のユーザープロファイル写真設定を管理する](https://learn.microsoft.com/ja-jp/graph/profilephoto-configure-settings)ドキュメントのガイダンスに従って行われます。

#### 一般提供 - 内部ゲスト ユーザーに対する一時的なAccess パス (TAP) のサポート

**種類:** 新機能**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

Microsoft Entra IDでは、内部ゲスト ユーザーへの一時アクセス パス (TAP) の発行がサポートされるようになりました。 TAP は、通常のメンバーと同じように、Microsoft Entra ID管理センターを通じて、またはネイティブにMicrosoft Graphを通じて内部ゲストに発行できます。 この機能強化により、内部ゲストは、期限付きの一時的な資格情報を使用してアカウントをシームレスにオンボードし、回復できるようになりました。 詳細については、「[パスワードレス認証方法を登録するための一時Accessパスの構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass)を参照してください。

#### パブリック プレビュー - Microsoft Entra ID ガバナンス: アクセス パッケージ申請の提案

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

**オプトイン**[以前](https://techcommunity.microsoft.com/blog/identity/whats-new-in-microsoft-entra---september-2024/4253153)お知らせしたとおり、[マイ アクセス](https://myaccess.microsoft.com)に新機能として、推奨されるアクセス パッケージのキュレーションされたリストが導入されました。 この機能を使用すると、ユーザーは長いリストをスクロールすることなく、最も関連性の高いaccess パッケージ (ピアのaccess パッケージと以前の要求に基づいて) をすばやく表示できます。 12 月には、[Identity Governance のオプトイン プレビュー機能でプレビューを有効](https://entra.microsoft.com/?feature.msaljs=true#view/Microsoft_AAD_ERM/DashboardBlade/%7E/elmSetting)にできるようになります。 1 月から、この設定は既定で有効になります。

#### パブリック プレビュー - Security Copilot が Microsoft Entra に埋め込まれました

**種類:** 新機能**サービス カテゴリ:** 他**製品の機能:** ID のセキュリティと保護

Microsoft Entra管理センターに埋め込まれたMicrosoft Security Copilotのパブリック プレビューが発表されました。 この統合により、2024 年 4 月に Security Copilot スタンドアロン エクスペリエンスで一般提供されていたすべての ID スキルと、Microsoft Entra 管理センター内で直接使用できる管理者とセキュリティ アナリスト向けの新しい ID 機能が提供されます。 さらに、ID 関連のリスク調査の改善に役立つ新しいスキルも追加されました。 12 月には、Security CopilotとMicrosoft Entraのスタンドアロンエクスペリエンスと組み込みエクスペリエンスの両方に、App Risk Management 専用のスキルセットを含めるために、さらに範囲を広げます。 これらの機能により、ID 管理者とセキュリティ アナリストは、Microsoft Entraに登録されているアプリケーションとワークロード ID に影響を与えるリスクをより適切に識別、理解、修復できます。

Security CopilotがMicrosoft Entraに埋め込まれるようになったため、ID 管理者は、セキュリティ インシデントを処理するために調整された AI 主導の自然言語の ID コンテキストと分析情報の概要を取得し、ID 侵害からより適切に保護します。 埋め込みエクスペリエンスにより、管理センターを離れることなく、ID 関連のリスクやサインインの問題の解決などのトラブルシューティング タスクも高速化されます。

#### パブリック プレビュー - Microsoft EntraでのSecurity Copilot「アプリのリスクスキル」

**種類:** 新機能**サービス カテゴリ:** 他**製品の機能:** ID のセキュリティと保護

登録されたアプリMicrosoft Entra ID管理する ID 管理者とセキュリティ アナリストは、自然言語プロンプトを使用してリスクを特定し、理解できます。 Security Copilotには、管理者が必要な修復アクションを実行するためのMicrosoft Entra管理センターへのリンクがあります。 詳細については、[Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps) でMicrosoft Security Copilotを使用してアプリケーションリスクを評価する方法に関する説明を参照してください。

#### パブリック プレビュー - HR ソースからカスタム セキュリティ属性をプロビジョニングする

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** Entra IDへのインバウンド

この機能により、お客様は、権限のある人事ソースからMicrosoft Entra IDに "*カスタム セキュリティ属性*" を自動的にプロビジョニングできます。 サポートされている信頼できるソースには、Workday、SAP SuccessFactors、API 駆動型プロビジョニングを使用して統合されたあらゆる HR システムが含まれます。

#### パブリック プレビュー - Apple でサインイン

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** 拡張性

この新しい機能により、事前構成されたソーシャル ID プロバイダーのリストに Apple が追加されます。 eSTS プラットフォームに実装された最初のソーシャル ID プロバイダーとして、サインイン オプションに "*Sign in with Apple*" ボタンが導入され、ユーザーは自分の Apple アカウントでアプリケーションをaccessできます。 詳細については、「[Apple を ID プロバイダーとして追加する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)」を参照してください。

#### 一般提供 - Microsoft Entra 外部 ID のカスタム URL ドメイン

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ID ライフサイクル管理

この機能を使用すると、ユーザーは独自のブランド名を使用して、Microsoftの既定のサインイン認証エンドポイントをカスタマイズできます。 カスタム URL ドメインを使用すると、ユーザーは Ext ID エンドポイント &lt; tenant-name &gt;.ciamlogin.com を login.contoso.com に変更できます。

#### 一般提供開始 - Azure ロールベースのアクセス コントロールにおける Privileged Identity Management の統合

**種類:** 新機能**サービス カテゴリ:** RBAC**製品機能:** アクセス制御

Privileged Identity Management (PIM) 機能が、Azure ロール ベースの Access Control (Azure RBAC) UI に統合されるようになりました。 この統合の前に、RBAC 管理者は、Azure RBAC UI からの永続的なアクセス (アクティブな永続的なロールの割り当て) のみを管理できました。 この統合により、PIM でサポートされている機能である Just-In-Time アクセスとタイムバインド アクセスが、P2 または IDENTITY Governance ライセンスを持つお客様向けのAzure RBAC UI に取り込まれるようになりました。

RBAC 管理者は、Azure RBAC のロールの割り当ての追加フローから、資格のある種類と期間の割り当てを作成したり、1 つのビューでロールの割り当てのさまざまな状態の一覧を表示したり、Azure RBAC UI からロールの割り当ての種類と期間を変換することができます。 さらに、エンド ユーザーには、Azure RBAC UI ランディング ページから、さまざまな状態のすべてのロールの割り当てが直接表示されるようになりました。そこから、対象となるロールの割り当てをアクティブ化することもできます。 詳細については、「[スコープでのロールの割り当てをリストする](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-list-portal#list-role-assignments-at-a-scope)を参照してください。

#### 一般提供 - Active DirectoryをMicrosoft Entra Connect SyncまたはCloud Syncを用いてMicrosoft Entra IDと同期させるための新しい専用リソースアプリケーション

**種類：** 機能の変更**サービス カテゴリ:** プロビジョニング**製品の機能:** ディレクトリ

継続的なセキュリティ強化の一環として、MicrosoftはActive DirectoryとMicrosoft Entra IDの同期を可能にする、Microsoft Entra AD 同期サービスという専用の自社製アプリケーションを展開しました。 アプリケーション ID `6bf85cfa-ac8a-4be5-b5de-425a0d0dc016` を持つこの新しいアプリケーションは、Microsoft Entra Connect Sync または Microsoft Entra Cloud Sync サービスを使用する顧客テナントでプロビジョニングされました。

### 2024 年 11 月

#### パブリック プレビュー - ユニバーサル継続的アクセス評価

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** ネットワークアクセス

継続的アクセス評価 (CAE) は、MICROSOFT ENTRA IDが ID への変更を検出するたびに、ほぼリアルタイムでネットワーク アクセスを取り消し、再検証します。 詳細については、「[Universal Continuous Access Evaluation (Preview)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-universal-continuous-access-evaluation)を参照してください。

#### パブリック プレビュー - Microsoft Entraが証明書ベースの認証用の新しいストアを公開

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra IDには、新しいスケーラブルな **PKI (公開キー インフラストラクチャ) ベースの CA** (証明機関) ストアがあり、CA の数と各 CA ファイルのサイズに対する制限が大きくなります。 PKI ベースの CA ストアを使用すると、それぞれの異なる PKI 内の CA を独自のコンテナ オブジェクトに配置できるため、管理者は 1 つのフラットな CA リストからより効率的な PKI コンテナ ベースの CA に移行できます。 PKI ベースの CA ストアでは、CA ごとに最大 250 CA、8 KB のサイズがサポートされるようになりました。さらに、各 CA の発行者ヒント属性もサポートされています。 管理者は、"CBA PKI のアップロード" 機能を使用して PKI 全体とすべての CA をアップロードすることも、PKI コンテナーを作成して CA を個別にアップロードすることもできます。 詳細については、「[手順 1: PKI ベースの信頼ストアを使用して証明機関を構成する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication#step-1-configure-the-cas-with-a-pki-based-trust-store)」を参照してください。

#### パブリック プレビュー - MyAccount におけるプロフィール写真の更新

**種類:** 新機能**サービス カテゴリ:** マイ プロファイル/アカウント**製品の機能:** エンド ユーザー エクスペリエンス

2024 年 11 月 13 日より、ユーザーは [MyAccount](https://myaccount.microsoft.com/) ポータルから直接プロフィール写真を更新できるようになりました。 この変更により、ユーザーのアカウントのプロフィール写真セクションに新しい編集ボタンが表示されます。

一部の環境では、ユーザーによるこの変更を禁止する必要があります。 グローバル管理者は、Microsoft Graph API を使用し、テナント全体のポリシーによってこれを管理できます。この管理は、[Microsoft 365 のユーザープロファイル写真設定を管理する](https://learn.microsoft.com/ja-jp/graph/profilephoto-configure-settings)ドキュメントのガイダンスに従って行われます。

#### 一般提供 - Microsoft Entra の正常性の監視、正常性メトリック機能

**種類:** 新機能**サービス カテゴリ:** レポーティング**製品の機能:** 監視とレポート

Microsoft Entra正常性の監視 ([正常性] ウィンドウから使用可能) には、テナント内の重要なユーザー シナリオの正常性を監視するために使用できる、待機時間の短い事前計算済み正常性メトリックのセットが含まれています。 最初の一連の正常性シナリオには、MFA、CA 準拠デバイス、CA 管理デバイス、SAML 認証が含まれます。 この監視シナリオのセットは、時間の経過と共に増加します。 これらの正常性メトリックは、インテリジェント アラート機能のパブリック プレビューと共に、一般提供データ ストリームとしてリリースされるようになりました。 詳細については、「[Microsoft Entra ヘルスとは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)？」を参照してください。

#### 一般提供 - Microsoft Entra Connect 同期バージョン 2.4.27.0

**種類：** 機能の変更**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ガバナンス

2025 年 11 月 14 日に、OLE DB バージョン 18.7.4 を使用する Microsoft Entra Connect Sync バージョン 2.4.27.0 がリリースされ、サービスがさらに強化されました。 この最新バージョンの接続同期にアップグレードし、セキュリティを高めます。 詳細については、[リリース ノート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#24270)を参照してください。

#### 機能の変更 - Microsoft Entra IDの MFA ワンタイム パスコード配信チャネルとしての WhatsApp の拡張

**種類：** 機能の変更**サービス カテゴリ:** MFA**製品の機能:** ユーザー認証

2023 年後半に、Microsoft Entra IDは、インドとインドネシアのユーザーに多要素認証 (MFA) ワンタイム パスコードを提供するための代替チャネルとして WhatsApp の使用を開始しました。 両国でチャネルを使用する際、配信可能性、完了率、満足度が向上しました。 このチャネルは、2024 年初頭にインドで一時的に無効になりました。 2024 年 12 月初めから、インドでチャネルを再び有効にし、その使用が他の国に拡大される予定です。

2024 年 12 月以降、インドや他の国のユーザーは、WhatsApp 経由で MFA テキスト メッセージの受信を開始できます。 認証方法として MFA テキスト メッセージを受信でき、既に WhatsApp がスマートフォンにインストールされているユーザーのみ、このエクスペリエンスを利用できます。 デバイスで WhatsApp を使用しているユーザーに到達できない場合、またはインターネットに接続できない場合、通常の SMS チャネルにすばやくフォールバックされます。 加えて、WhatsApp 経由で初めて OTP を受信したユーザーには、SMS テキスト メッセージを介して動作の変化が通知されます。

ユーザーが WhatsApp 経由で MFA テキスト メッセージを受信したくない場合、組織内の認証方法としてテキスト メッセージを無効にするか、ユーザーのサブセットに対してのみ有効にするようスコープを絞り込むことができます。 通信やメッセージング アプリの方法を優先して、Microsoft Authenticatorやパスキーなどのより最新のセキュリティで保護された方法を使用することを組織に強くお勧めします。 詳細については、「[テキスト メッセージの検証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options#text-message-verification)」を参照してください。

#### 提供終了 - MFA 不正アクセス アラートは 2025 年 3 月 1 日に廃止されます

**種類：** 廃止**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

Microsoft Entra の多要素認証 (MFA) 詐欺アラートは、エンドユーザーが開始していないMFAの音声通話やMicrosoft Authenticatorのプッシュ通知を不正として報告することを可能にします。 2025 年 3 月 1 日より、MFA 不正アクセス アラートは廃止され、エンド ユーザーが不正なリクエストを報告できる代替機能である[疑わしいアクティビティを報告](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#report-suspicious-activity)が導入されます。この機能は [Identity Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) と統合されており、より包括的なカバレッジと修復を実現します。 ユーザーが不正な MFA 要求を引き続き報告できるようにするには、組織は疑わしいアクティビティの報告を使用して移行し、Microsoft Entraライセンスに基づいて報告されたアクティビティがどのように修復されるかを確認する必要があります。 詳細については、「[Microsoft Entra の多要素認証設定を構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings)」を参照してください。

#### パブリック プレビュー - Microsoft Entraヘルスモニタリングおよびアラート機能

**種類：** 機能の変更**サービス カテゴリ:** 他**製品の機能:** 監視とレポート

Microsoft Entra正常性監視のインテリジェント アラートは、監視対象のシナリオが一般的なパターンから切り離されるたびに、テナント管理者とセキュリティ エンジニアに通知します。 Microsoft Entraのアラート機能は、各シナリオの低待機時間の正常性シグナルを監視し、異常が検出された場合に通知を発行します。 アラート対応の正常性シグナルとシナリオのセットは、時間の経過と共に増加します。 このアラート機能は、API 専用パブリック プレビュー リリースとして Microsoft Entra Health で使用できるようになりました (UX リリースは 2025 年 2 月に予定されています)。 詳細については、[Microsoft Entraの正常性監視アラートを使用する方法 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-health-scenario-alerts)をご参照ください。

#### 一般提供 - Log Analytics サインイン ログ スキーマと MSGraph スキーマの同等性

**種類:** 変更の計画**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** 監視とレポート

コア ログの原則の整合性を維持するために、Azure Log Analytics サインイン ログ スキーマが MSGraph サインイン ログ スキーマと一致しないレガシ パリティの問題に対処しました。 更新プログラムには、ClientCredentialType、CreatedDateTime、ManagedServiceIdentity、NetworkLocationDetails、tokenProtectionStatus、SessionID などのフィールドが含まれます。 これらの変更は、2024 年 12 月の第 1 週に有効になります。

この機能強化により、より一貫性のあるログ記録エクスペリエンスが実現します。 これまでと同様に、インジェスト前の変換を実行して、Azure Log Analytics ストレージ ワークスペースから不要なデータを削除できます。 これらの変換を実行する方法のガイダンスについては、「Azure MonitorData コレクション変換>を参照してください。

#### 非推奨 - MIM ハイブリッド レポート エージェント

**種類：** 廃止**サービス カテゴリ:** Microsoft Identity Manager**製品の機能:** 監視とレポート

パスワード リセットレポートとセルフサービス グループ管理レポートで表示するMicrosoft Entraに MIM サービス イベント ログを送信するために使用されるハイブリッド レポート エージェントは非推奨です。 代わりに、Azure Arcを使用してイベント ログをAzure Monitorに送信することをお勧めします。 詳細については、「[Microsoft Identity Manager 2016 レポートと Azure Monitor](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/mim-azure-monitor-reporting)」を参照してください。

### 2024 年 10 月

#### パブリック プレビュー - Android 上のブローカー Microsoft アプリでのパスキー認証

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra IDユーザーはパスキーを使用して、Microsoft AuthenticatorやMicrosoft Intune ポータル サイトなどの認証ブローカーがインストールされている Android デバイス上の Microsoft アプリにサインインできるようになりました。 詳細については、「 Microsoft Entra IDを参照してください。

#### パブリック プレビューの更新 - Microsoft Authenticator におけるパスキー

**種類:** 新機能**サービス カテゴリ:** Microsoft Authenticator アプリ**製品の機能:** ユーザー認証

Microsoft Authenticatorのパスキーのパブリック プレビューでは、追加機能がサポートされるようになりました。 管理者は、パスキーの登録時に構成証明を要求可能になりました。Android ネイティブ アプリでは、Authenticator におけるパスキーを使用したサインインがサポートされるようになりました。 さらに、MySignIns からフローを開始するとき、ユーザーは Authenticator アプリにサインインしてパスキーを登録するよう求められるようになりました。 Authenticator アプリのパスキー登録ウィザードでは、登録を試みる前にアプリのコンテキスト内のすべての前提条件を満たすための手順がユーザーに表示されます。 Authenticator アプリの最新バージョンをダウンロードして、組織内でこれらの変更を試したら、ぜひご意見をお聞かせください。 詳細については、「[Microsoft Authenticator でパスキーを有効にする (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-authenticator-passkey)」を参照してください。

#### パブリック プレビュー - 認証方法移行ウィザード

**種類:** 新機能**サービス カテゴリ:** MFA**製品の機能:** ユーザー認証

Microsoft Entra 管理センターの認証方法移行ガイド (プレビュー) を使用すると、メソッド管理を [legacy MFA ポリシーと SSPR ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage#legacy-mfa-and-sspr-policies) から [converged 認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage)に自動的に移行できます。 2023 年、従来の MFA および SSPR ポリシーで認証方法を管理する機能が 2025 年 9 月に廃止される予定であることが発表されました。 これまで、組織は統合ポリシーの[移行トグル](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-methods-manage#start-the-migration)を活用して、方法を自ら手動で移行する必要がありました。 移行ガイドを使用して、いくつか選択するだけで移行できるようになりました。 このガイドでは、組織が現在両方の従来のポリシーで有効にしている内容を評価し、必要に応じて確認および編集できるよう、推奨される統合ポリシー構成を生成します。 そこから構成が確認されて、セットアップされ、移行が完了としてマークされます。 詳細については、「 MFA と SSPR ポリシー設定を Microsoft Entra IDを参照してください。

#### 一般提供 - Microsoft Entra 外部 IDの MFA メソッドとしての SMS

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

電話評判プラットフォームとの統合を通じて、通信詐欺防止が組み込まれたMicrosoft Entra 外部 IDの MFA メソッドとしての SMS の一般提供を発表します。

**新機能**

- ユーザーがアクセスしているアプリケーションのルック アンド フィールが維持された SMS サインイン エクスペリエンス。
- SMS はアドオン機能です。 組み込みのfraud protection サービスを含む、ユーザーに送信された SMS ごとに追加料金が適用されます。
- 電話評判プラットフォームと統合することにより、通信詐欺に対する組み込みの詐欺防止機能を提供します。 このプラットフォームは、テレフォニー アクティビティをリアルタイムで処理し、リスクと一連のヒューリスティックに基づいて "許可"、"ブロック"、または "チャレンジ" を返します。

詳細については、「[外部テナントにおける多要素認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers)」を参照してください。

### 2024 年 9 月

#### パブリック プレビュー - デバイス コンプライアンスを必要とする新しい条件付きAccess テンプレート

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

デバイスのコンプライアンスを必要とする新しい条件付きAccess テンプレートがパブリック プレビューで使用できるようになりました。 このテンプレートは、モバイルデバイス管理 (MDM) に登録され、会社のポリシーに準拠しているデバイスのみが会社のリソースにアクセスできるように制限します。 デバイス コンプライアンスを要求すると、データセキュリティが向上し、データ侵害、マルウェア感染、未承認のaccessのリスクが軽減されます。 これは、MDM を介したコンプライアンス ポリシーの対象となるユーザーとデバイスに推奨されるベスト プラクティスです。 詳細については、「[共通ポリシー：デバイスのコンプライアンスを要求する条件付きアクセスポリシーを作成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)」を参照してください。

#### パブリック プレビュー - エンド ユーザー証明書発行者が証明書失効リストで構成されていない場合、テナント管理者が証明書ベースの認証に失敗する可能性がある

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

証明書ベースの認証を使用すると CRL エンドポイントなしで CA をアップロードすることができます。発行元 CA に CRL が指定されていない場合、証明書ベースの認証が失敗することはありません。

セキュリティを強化し、構成ミスを回避するため、認証ポリシー管理者は、エンド ユーザー証明書を発行する CA に対して CRL が構成されていない場合、CBA 認証の失敗を要求できます。 詳細については、 [CRL 検証について (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list#enforce-crl-validation-for-cas) を参照してください。

#### 一般提供: Android でのMicrosoft Authenticatorは、Microsoft Entra認証に FIPS 140 に準拠しています

**種類:** 新機能**サービス カテゴリ:** Microsoft Authenticator アプリ**製品の機能:** ユーザー認証

バージョン 6.2408.5807 以降、Android 用Microsoft Authenticatorは、フィッシングに強いデバイス バインド パスキー、プッシュ多要素認証 (MFA)、パスワードレス電話サインイン (PSI)、時間ベースのワンタイム パスコード (TOTP) など、すべてのMicrosoft Entra認証に対して Federal Information Processing Standard (FIPS 140-3) に準拠しています。 この機能を有効にするために、Microsoft AuthenticatorまたはMicrosoft Entra ID管理ポータルで構成を変更する必要はありません。 iOS でのMicrosoft Authenticatorは、昨年発表された FIPS 140 に既に準拠しています。 詳細については、「[authentication methods in Microsoft Entra ID - Microsoft Authenticator app](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app)」を参照してください。

#### 一般提供 - Visual Studio CodeのMicrosoft Entra 外部 ID拡張機能

**種類：** 機能の変更**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

[Microsoft Entra 外部 ID VS Code 用拡張機能](https://aka.ms/ciamvscode/newsletter/marketplace) は、顧客向けアプリの ID 統合を開始するのに役立つ、合理化されたガイド付きエクスペリエンスを提供します。 この拡張機能を使用すると、外部テナントの作成、外部ユーザー向けのカスタマイズおよびブランド化されたサインイン エクスペリエンスの設定、構成済みの外部 ID サンプルを使用したプロジェクトの迅速なブートストラップをVisual Studio Code内で行うことができます。 さらに、拡張機能内で外部テナント、アプリケーション、ユーザー フロー、ブランド化設定を直接表示および管理することができます。

詳細については、「[クイックスタート: Microsoft Entra 外部 ID 拡張機能を Visual Studio Code で始める](https://learn.microsoft.com/ja-jp/entra/external-id/customers/visual-studio-code-extension)」を参照してください。

#### パブリック プレビュー - エンタープライズ アプリのクレーム構成用のカスタム クレーム API

**種類:** 新機能**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** SSO

カスタム クレーム API を使用すると、管理者は MS Graph を使用してエンタープライズ アプリケーションの追加要求をシームレスに管理および更新できます。 カスタム クレーム API は、お客様向けの要求管理用の簡略化されたユーザー フレンドリな API エクスペリエンスを提供します。 カスタム クレーム API の導入により、UX と API の相互運用性が実現しました。 管理者は、Microsoft Entra 管理センターと MS Graph APIを同じ意味で使用して、エンタープライズ アプリケーションの要求構成を管理できるようになりました。 これにより、管理者は API を使用して自動化を実行し、同じポリシー オブジェクトで必要に応じてMicrosoft Entra 管理センターの要求を柔軟に更新できるようになります。 詳細については、[Microsoft Graph のカスタム クレーム ポリシー (プレビュー) を使用してクレームをカスタマイズする方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-custom-claims-policy) を参照してください。

#### 一般提供 - テナント間マネージャーの同期

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ガバナンス

テナント間同期を使用したマネージャー属性の同期のサポートが一般提供になりました。 詳細については、「[属性](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#attributes)」を参照してください。

#### パブリック プレビュー - 代理要求

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

エンタイトルメント管理を使用すると、管理者はaccess パッケージを作成して組織のリソースを管理できます。 管理者は、access パッケージにユーザーを直接割り当てるか、ユーザーとグループ メンバーがaccessを要求できるようにするaccess パッケージ ポリシーを構成できます。 セルフサービス プロセスを作成するこのオプションは、特に組織がより多くの従業員をスケーリングして雇用する場合に役立ちます。 ただし、組織に参加する新しい従業員は、何にアクセスが必要か、またはどのようにアクセスを要求するのかを常に把握しているとは限りません。 この場合、新しい従業員はマネージャーに頼って、アクセス要求プロセスの案内を受けることになるでしょう。

マネージャーは、新しい従業員に要求プロセスを管理させる代わりに、従業員のためにアクセスパッケージを要求できるため、オンボーディングをより迅速かつシームレスにすることができます。 管理者がこの機能を有効にするには、管理者が従業員に代わってaccessを要求できるaccess パッケージ ポリシーを設定するときにオプションを選択できます。

セルフサービス要求フローを拡張して従業員に代わって要求を許可することで、ユーザーは必要なリソースにタイムリーにaccessでき、生産性が向上します。 詳細については、「[Request access パッケージを他のユーザーに代わって作成する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf)を参照してください。

### 2024 年 8 月

#### 変更のお知らせ - Microsoft Entra 管理センターに対する今後の MFA の適用

**種類:** 変更の計画**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

お客様に最高レベルのセキュリティを提供する取り組みの一環として、以前にMicrosoftはAzureにサインインするユーザーに対し、多要素認証 (MFA) を要求すると発表しました。

Azure ポータルと Intune 管理センターに加えて、MFA の適用範囲に [Microsoft Entra 管理センター](https://entra.microsoft.com/) が含まれる更新プログラムを共有したいと考えます。 この変更は段階的にロールアウトされるため、組織が実装を計画する時間を確保できます。

**Phase 1**: 2024 年の後半から、MFA はMicrosoft Entra 管理センター、Azure ポータル、Intune 管理センターにサインインする必要があります。 この適用は、世界中のすべてのテナントに徐々に実施されます。 このフェーズは、Azure コマンド ライン インターフェイス、Azure PowerShell、Azure モバイル アプリ、コードとしてのインフラストラクチャ (IaC) ツールなど、他のAzure クライアントには影響しませんでした。

**Phase 2**: 2025 年初頭から、Azure CLI、Azure PowerShell、Azure モバイル アプリ、およびコードとしてのインフラストラクチャ (IaC) ツールのサインイン時に MFA が段階的に適用されます。

Microsoftは、Microsoft Entraグローバル管理者全員に 60 日間の事前通知を電子メールで送信し、Azure Service Health通知を通じて、実施の開始日と必要なアクションを通知します。 追加の通知は、Azure ポータル、Microsoft Entra 管理センター、およびMicrosoft 365 メッセージ センターを介して送信されます。

一部のお客様は、この MFA 要件の準備により多くの時間を必要していることは承知しております。 そのため、Microsoftにより、複雑な環境や技術的な障壁を持つお客様にとって、時間の延長が可能になります。 Microsoft からの通知には、お客様が特定の変更を延期する方法に関する詳細も記載されています。 これらの変更には、テナントへの適用開始日、延期の期間、変更を適用するためのリンクが含まれます。 詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mandatory-multifactor-authentication)にアクセスしてください。

#### 一般提供 - Microsoft Entra Connect Sync と Microsoft Entra Cloud Sync のディレクトリ同期アカウント (DSA) ロールに対する制限付きアクセス許可

**種類：** 機能の変更**サービス カテゴリ:** プロビジョニング**製品機能:** Microsoft Entra Connects

継続的なセキュリティ強化の一環として、Microsoftは特権*Directory 同期アカウント*ロールから未使用のアクセス許可を削除します。 このロールは、Active Directory オブジェクトを Microsoft Entra ID と同期するために、Microsoft Entra Connect Sync および Microsoft Entra Cloud Sync によってのみ使用されます。 お客様がこのセキュリティ強化のメリットを得るために必要なアクションはありません。変更されたロールのアクセス許可については、[Directory 同期アカウント](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-synchronization-accounts)に関するページを参照してください。

#### 変更の計画 - マイ セキュリティ情報の [サインイン方法の追加] ピッカーの UX 更新

**種類:** 変更の計画**サービス カテゴリ:** MFA**製品の機能:** エンド ユーザー エクスペリエンス

2024 年 10 月中旬より、マイ セキュリティ情報ページの *[サインイン方法の追加]* ダイアログがモダンな外観と操作性によって更新されます。 この変更により、各メソッドの下に新しい記述子が追加され、サインイン 方法の使用方法の詳細がユーザーに提供されます (例: *Microsoft Authenticator - サインイン要求の承認*または*ワンタイム コードの使用*)。

来年初め、*[サインイン方法の追加]* ダイアログが強化され、登録に使用できるサインイン方法の完全な一覧を最初に表示するのではなく、最初に推奨されるサインイン方法が表示されるようになります。 推奨されるサインイン方法は、組織の認証方法ポリシーに基づいて、ユーザーが利用できる最も強力な方法が既定で設定されます。 ユーザーは *[その他のオプションを表示]* を選択し、ポリシーで許可されているすべてのサインイン方法から選択できます。

この変更は自動的に行われるため、管理者が実行するアクションはありません。

#### パブリック プレビュー - UX 更新のプロビジョニング

**種類:** 変更の計画**サービス カテゴリ:** プロビジョニング**製品の機能:** SaaS アプリケーションへの送信

来月から、アプリケーション プロビジョニング、HR プロビジョニング、テナント間同期のユーザー エクスペリエンス更新のリリースが開始されます。 これらの更新には、新しい概要ページ、アプリケーションへの接続を構成するためのユーザー エクスペリエンス、新しいプロビジョニング エクスペリエンスの作成が含まれています。 新しいエクスペリエンスには、現在お客様が利用できるすべての機能が含まれており、お客様による操作は必要ありません。

#### 変更のお知らせ - マイ グループ管理者コントロールに対する変更の延期

**種類:** 変更の計画**サービス カテゴリ:** グループ管理**製品機能:** 認可/アクセス委任

[October 2023](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/what-s-new-in-microsoft-entra/ba-p/3796395) では、2024 年 6 月以降、Microsoft Entra 管理センターの既存のセルフサービス グループ管理設定で、*マイ グループ* のグループ機能にアクセスできるユーザー機能が廃止されることを共有しました。 これらの変更は現在検討中であり、当初の計画どおりに実施される可能性があります。 新しい廃止日は、今後発表される予定です。

#### パブリック プレビュー - MICROSOFT ENTRA ID FIDO2 プロビジョニング API

**種類:** 新機能**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

Microsoft Entra IDでは、API を使用した FIDO2 プロビジョニングがサポートされるようになりました。これにより、組織はユーザーのセキュリティ キー (パスキー) を事前にプロビジョニングできます。 これらの新しい API を使用すると、ユーザーのオンボードが簡素化されるため、シームレスでフィッシングに強い認証を従業員に対して初日から提供することができます。 この機能の使用方法の詳細については、「 Microsoft Graph APIを参照してください。

#### 一般提供: ライフサイクル ワークフローを使用して同期されたユーザー アカウントを有効化、無効化、削除する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ライフサイクル管理

ライフサイクル ワークフローで、Active Directory Domain Services (AD DS) からMicrosoft Entraに同期されるユーザー アカウントを有効、無効、削除できるようになりました。 この機能により、保持期間後にユーザー アカウントを削除することで、従業員のオフボーディング プロセスを完了できます。

詳細については、「[ワークフローを使用してActive Directory Domain Servicesから同期されたユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-on-premises)を参照してください。

#### 一般提供 - カスタム セキュリティ属性を使用してライフサイクル ワークフロー スコープを構成する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ライフサイクル管理

お客様は、カスタム セキュリティ属性に格納されている機密人事データを使用できるようになりました。 お客様は、他の属性に加えて使用することで、ライフサイクル ワークフロー内のワークフローの範囲を定義し、入社者、異動者、退職者のシナリオを自動化することができます。

詳細については、「[カスタム セキュリティ属性を使用してワークフローのスコープを設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-custom-security-attribute)」を参照してください。

#### 一般提供 - ライフサイクル ワークフローのワークフロー履歴分析情報

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ライフサイクル管理

この機能により、お客様はワークフローの正常性を監視し、ワークフロー間、タスク間、ワークフロー カテゴリ間のワークフロー処理データの表示など、ライフサイクル ワークフロー内のすべてのワークフロー全体にわたって分析情報を取得できるようになりました。

詳細については、「[ライフサイクル ワークフローの分析情報](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-insights)」をご覧ください。

#### 一般提供 - ユーザーのジョブ プロファイルが変更されたときにムーバー タスクを実行するようカスタム ワークフローを構成する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ライフサイクル管理

ライフサイクル ワークフローで、従業員の部門、職務、または場所の変更などのジョブ変更イベントに基づいてワークフローをトリガーし、ワークフロー スケジュールで実行されるワークフローを表示できるようになりました。 この機能により、お客様は新しいワークフロー トリガーを使用して、組織内を移動する従業員に関連付けられたタスク (トリガーなど) を実行するためのカスタム ワークフローを作成できます。

- 指定した属性が変更されたときのワークフロー
- ユーザーがグループのメンバーシップに追加または削除されたときのワークフロー
- 移動についてユーザーのマネージャーに通知するタスク
- ライセンスを割り当てる、または選択したライセンスをユーザーから削除するタスク

詳細については、[Microsoft Entra管理センターのチュートリアルを使用したジョブ変更時の従業員自動移動タスク](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-mover-custom-workflow-portal)を参照してください。

#### 一般提供 - Red Hat Enterprise Linux 上の M365/Azure リソースへのデバイス ベースの条件付きアクセス

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** SSO

2022 年 10 月以降、Ubuntu Desktop 20.04 LTS およびMicrosoft Edge ブラウザーを使用する Ubuntu 22.04 LTS では、デバイスをMicrosoft Entra IDに登録し、Microsoft Intune管理に登録し、デバイス ベースの条件付きアクセス ポリシーを使用して企業リソースに安全にアクセスできます。

このリリースでは、Red Hat Enterprise Linux 8.x および 9.x (LTS) のサポートが拡張され、次の機能が利用可能になります。

- RedHat LTS (8/9) デスクトップの登録と受講をMicrosoft Entra IDで行います。
- Microsoft Edgeを介して Web アプリケーションを保護する条件付きアクセス ポリシー。 -ネイティブおよびWebアプリケーションにSSOを提供します（例：Azure CLI、Microsoft Edgeブラウザー、TeamsプログレッシブWebアプリ（PWA）など） M365/Azure保護されたリソースにアクセスします。
- 標準 Intune コンプライアンス ポリシー。
- カスタム コンプライアンス ポリシーを使用した Bash スクリプトのサポート。
- パッケージ マネージャーでは、Debian DEB パッケージに加えて、RHEL  RPM パッケージがサポートされるようになりました。

詳細については、「Microsoft Entra登録済みデバイス」を参照>。

### 2024 年 7 月

#### 一般提供 - 条件付きアクセスのインサイダー リスク条件の GA

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

**条件付きアクセスの内部リスク条件が一般提供されました**

条件付きアクセスの Insider リスク条件は、Microsoft Purviewのアダプティブ保護機能からのシグナルを使用して、Insider の脅威の検出と自動軽減を強化する新機能です。 この統合により、組織は高度な分析とリアルタイム データを使用して、潜在的なインサイダー リスクをより効果的に管理し、対応することができます。

たとえば、Purview がユーザーからの異常なアクティビティを検出した場合、条件付きAccessでは、多要素認証 (MFA) の要求やaccessのブロックなど、追加のセキュリティ対策を適用できます。 この機能はプレミアムで、P2 ライセンスが必要です。 詳細については、「[共通の条件付きアクセス ポリシー：インサイダーリスクがあるユーザーのアクセスをブロックする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-insider-block)」を参照してください。

#### 一般提供 - 新しい SAML アプリケーションが OAuth2/OIDC プロトコルを介してトークンを受信できない

**種類:** 変更の計画**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** 開発者エクスペリエンス

2024 年 9 月下旬以降、*SAML* アプリケーションとして示されたアプリケーション (サービス プリンシパルの `preferredSingleSignOnMode` プロパティ経由) は JWT トークンを発行できません。 この変更により、OIDC、OAuth2.0、または JWT を使用する他のプロトコルのリソース アプリケーションにできなくなります。 この変更は、JWT ベースのプロトコルへの新たな依存関係を取得しようとしている SAML アプリケーションにのみ影響します。これらのフローを既に使用している既存の SAML アプリケーションには影響はありません。 この更新プログラムにより、アプリのセキュリティが強化されます。

詳細については、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/architecture/auth-saml)[を使用した](https://learn.microsoft.com/ja-jp/entra/architecture/auth-saml)SAML 認証 を参照してください。

#### 一般提供 - Microsoft Entra アプリケーション ギャラリーで利用可能な新しいフェデレーション アプリ - 2024 年 7 月

**種類:** 新機能**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** サード パーティ統合

2022 年 2 月には、フェデレーションをサポートする次の 20 個の新しいアプリケーションがアプリ ギャラリーに追加されました。

[Fullstory SAML](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fullstory-saml-tutorial)、[LSEG ワークスペース](https://azuremarketplace.microsoft.com/en-US/marketplace/apps/aad.lsegworkspace?tab=Overview)

すべてのアプリケーションのドキュメントについては、こちら (https://aka.ms/AppsTutorial ) をご覧ください。

Microsoft Entra ID アプリ ギャラリーにアプリケーションを一覧表示するには、https://aka.ms/AzureADAppRequest の詳細を参照してください。

#### 一般提供 - Active Directory フェデレーション サービス (AD FS) (AD FS) アプリケーション移行ウィザード

**種類:** 新機能**サービス カテゴリ:** AD FS アプリケーションの移行**製品の機能:** プラットフォーム

Active Directory フェデレーション サービス (AD FS) (AD FS) アプリケーション移行ウィザードを使用すると、ユーザーは、Microsoft Entra IDへの移行と互換性のある AD FS 証明書利用者アプリケーションをすばやく識別できます。 このツールは、各アプリケーションの移行の準備状況を示し、問題点と修正のための推奨アクションを強調します。 このツールでは、移行用の個々のアプリケーションを準備し、新しいMicrosoft Entra アプリケーションを構成する方法についても説明します。 この機能の使用方法の詳細については、「 AD FS アプリケーションの移行を使用して AD FS アプリを Microsoft Entra IDを参照してください。

#### 一般提供 - Identity Protection の中間検出アラートにおける攻撃者

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

中間者攻撃検出機能が、Identity Protection のユーザー向けに一般提供されました。

この高精度の検出は、発行されたトークンを含むユーザーの資格情報を傍受した攻撃者によって侵害されたユーザー アカウントでトリガーされます。 Microsoft 365 Defender によってリスクが識別され、ユーザーを高リスクと認識し、構成された条件付きアクセス ポリシーを発動します。

この機能の詳細については、「[リスク検出とは](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」をご覧ください。

#### 一般提供 - Azure App ServiceとMicrosoft Entra 外部 IDによる簡単な認証

**種類：** 機能の変更**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Azure App Serviceの組み込み認証の ID プロバイダーとしてMicrosoft Entra 外部 IDを使用する場合のエクスペリエンスが向上し、外部向けアプリの認証と承認を構成するプロセスが簡略化されます。 外部テナントに切り替えることなく、App Service認証のセットアップから直接初期構成を完了できます。 詳細については、「Quickstart: Azure App Serviceを参照してください。

### 2024 年 6 月

#### 変更の計画 - Microsoft Authenticator (プレビュー) 登録プロセスのエクスペリエンスでのパスキーが変更されます

**種類:** 変更の計画**サービス カテゴリ:** MFA**製品の機能:** エンド ユーザー エクスペリエンス

2024 年 7 月下旬から 2024 年 8 月末まで、[マイ Security-Info] ページの Microsoft Authenticator (プレビュー) でパスキーの登録エクスペリエンスの変更をロールアウトしています。 この登録エクスペリエンスの変更は、ユーザーが Microsoft Authenticator アプリにサインインして登録できるように WebAuthn アプローチから行われます。 この変更は自動的に行われ、管理者は何も行う必要はありません。 詳細については、以下を参照してください。

- 既定では、ユーザーが Authenticator アプリにサインインしてパスキーを設定するようにガイドします。
- ユーザーがサインインできない場合は、ページ上の「*問題がありますか？*」リンクを通じて、改善されたWebAuthnエクスペリエンスにフォールバックできるようになります。

#### 一般提供 - Microsoft Entra Connect Sync と Connect Health のセキュリティ強化

**種類：** 機能の変更**サービス カテゴリ:** プロビジョニング**製品機能:** Microsoft Entra Connect

**推奨されるアクション: Microsoft Entra のセキュリティ改善 - Connect Sync と Connect Health**

2023 年 9 月以降、予防的なセキュリティ関連のサービス変更 Microsoft Entraの一環として、Connect Sync と Microsoft Entra Connect Health のお客様を更新されたビルドに自動アップグレードしています。 以前に自動アップグレードをオプトアウトしたお客様、または自動アップグレードに失敗したユーザーの場合は、2024 年 9 月 23 日までに最新バージョンにアップグレード  することを強くお勧めします。

最新バージョンにアップグレードする場合は、サービスの変更が有効になったときに、次のサービスの中断を回避できます。

- Microsoft Entra Connect Sync
- Microsoft Entra Connect Health エージェント（同期用）
- ADDS 用 Microsoft Entra Connect Health エージェント
- ADFS 用の Microsoft Entra Connect Health エージェント

アップグレード関連のガイダンス、バージョン管理情報、およびサービス変更の予想される影響の詳細については、[自動アップグレード プロセスの](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/security-updates-pks) に対するセキュリティの強化に関するドキュメントを参照してください。

#### パブリック プレビュー - MS Graph APIユーザーごとの多要素認証のサポート

**種類:** 新機能**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

MS Graph APIユーザーごとの多要素認証のサポート

2024 年 6 月から、MS Graph APIを介してユーザーごとの多要素認証のユーザー状態 (強制、有効、無効) を管理する機能がリリースされます。 この更新プログラムは、廃止される従来の MSOnline PowerShell モジュールを置き換えます。 Microsoft Entra多要素認証を使用してユーザーを保護するための推奨されるアプローチは、条件付きアクセス (ライセンスされた組織の場合) とセキュリティの既定値 (ライセンスのない組織の場合) です。 詳細については、「[サインイン イベントをセキュリティで保護するためのユーザーごとのMicrosoft Entra多要素認証を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates)を参照してください。

#### パブリック プレビュー - Azure App ServiceとMicrosoft Entra 外部 IDによる簡単な認証

**種類：** 機能の変更**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Azure App Serviceの組み込み認証の ID プロバイダーとして Microsoft Entra 外部 ID を使用する場合のエクスペリエンスが向上し、外部向けアプリの認証と承認を構成するプロセスが簡略化されました。 外部テナントに切り替えることなく、App Service認証のセットアップから直接初期構成を完了できます。 詳細については、「Quickstart: Azure App Service

#### 一般提供 - Microsoft Authenticatorの [リファクタリングされたアカウントの詳細] 画面

**種類:** 変更の計画**サービス カテゴリ:** Microsoft Authenticator アプリ**製品の機能:** ユーザー認証

7 月に、Microsoft Authenticator アプリ UX の機能強化がロールアウトされました。ユーザー アカウントのアカウントの詳細ページは、ユーザーが画面上の情報とボタンをよりよく理解し、操作できるように再構成されています。 ユーザーが現在実行できる主なアクションは、リファクタリングされたページで利用できますが、ユーザーとのコミュニケーションを向上させるために役立つ 3 つのセクションまたはカテゴリに分かれています。

- アプリ内で設定された資格情報
- 構成できるその他のサインイン方法
- アプリのアカウント管理オプション

#### 一般提供 - テナント レベルでの SLA 達成レポート

**種類:** 新機能**サービス カテゴリ:** レポーティング**製品の機能:** 監視とレポート

グローバル SLA パフォーマンスを提供するだけでなく、Microsoft Entra IDは、少なくとも 5,000 人の月間アクティブ ユーザーを持つ組織のテナント レベルの SLA パフォーマンスを報告します。 この機能は、2024 年 5 月に一般公開されました。 サービス レベル アグリーメント (SLA) では、Microsoft Entra 管理センターで月単位で報告される、Microsoft Entra IDユーザー認証の可用性に対して 99.99% の最小バーが設定されます。 詳細については、「[Microsoft Entra Health とは何ですか？](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)」を参照してください。

#### プレビュー – QR コードサインイン、現場担当者向けの新しい認証方法

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

現場担当者が QR コードと PIN を使用してMicrosoft Entra IDで認証するための新しい簡単な方法が導入されています。 この機能により、ユーザーは長い UPN と英数字パスワードを入力して再入力する必要がなくなります。

2024 年 8 月以降、テナント内のすべてのユーザーは、組織にサインインする際に、のサインイン オプションに移動すると、QR コード でサインインする新しいリンク が表示されるようになります。 この新しいリンク (*QR コード*でサインイン) は、モバイル デバイス (Android/iOS/iPadOS) でのみ表示されます。 プレビューに参加していない場合、まだレビュー中は、テナントのユーザーはこの方法でサインインできません。 サインインしようとすると、エラー メッセージが表示されます。

この機能には、一般公開されるまで *プレビュー* タグがあります。 この機能をテストするには、組織を有効にする必要があります。 広範なテストは、後で発表されるパブリック プレビューで利用できます。

この機能はプレビュー段階ですが、テクニカル サポートは提供されていません。 プレビュー期間中のサポートの詳細については、[Microsoft Entra ID プレビュー プログラム情報](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing-preview-info)を参照してください。

### 2024 年 5 月

#### 一般提供 - Azure China 21Vianetでは、サインインと MFA/SSPR の統合登録がサポートされるようになりました

**種類：** 機能の変更**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

2024 年 6 月末から、Microsoft Azure China 21Vianetを利用しているすべての組織が、マイ サインイン アクティビティ レポートにアクセスできるようになりました。 MFA と SSPR のセキュリティ情報登録のエンド ユーザー エクスペリエンスを組み合わせて使用する必要があります。 この有効化の結果、ユーザーは、SSPR または MFA への登録を求められたときに、統合された SSPR と MFA の登録エクスペリエンスが表示されるようになりました。 詳細については、「[Microsoft Entra の概要におけるセキュリティ情報の統合登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)」を参照してください。

#### 一般提供 - `signIn` API の $select

**種類:** 新機能**サービス カテゴリ:** MS Graph**製品の機能:** 監視とレポート

待望の `$select` プロパティが、`signIn` API に実装されるようになりました。 `$select` を使用して、各ログに返される属性の数を減らします。 この更新プログラムは、調整の問題に対処する顧客を大幅に支援し、すべての顧客がより高速で効率的なクエリを実行できるようにする必要があります。

#### 一般提供 - Android デバイス用の複数のパスワードレス電話サインイン

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

エンド ユーザーは、サポートされている任意の Android デバイスの Authenticator アプリで、複数のアカウントに対してパスワードなしの電話によるサインインを有効にできるようになりました。 Microsoft Entraに複数のアカウントを持つコンサルタント、学生、その他のユーザーは、各アカウントをMicrosoft Authenticatorに追加し、同じ Android デバイスからすべてのユーザーにパスワードなしの電話サインインを使用できます。 Microsoft Entra アカウントは、同じテナントまたは異なるテナントに存在できます。 ゲスト アカウントは、1 つのデバイスからの複数のアカウント サインインではサポートされていません。 詳細については、「[enable passwordless sign-in with Microsoft Authenticator](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone)」を参照してください。

#### パブリック プレビュー - Microsoft GraphのBicep テンプレートのサポート

**種類:** 新機能**サービス カテゴリ:** MS Graph**製品の機能:** 開発者エクスペリエンス

Microsoft Graph Bicep 拡張機能は、Microsoft Graph リソースに宣言型の IaC (コードとしてのインフラストラクチャ) 機能をもたらします。 これにより、Azure リソースと共に、Bicep テンプレート ファイルを使用してコア Microsoft Entra ID リソースを作成、デプロイ、管理できます。

- 既存のAzureのお客様は、使い慣れたツールを使用して、アプリケーションやサービス プリンシパル、IaC、DevOps のプラクティスなど、Azureリソースと依存するMicrosoft Entra リソースをデプロイできるようになりました。
- また、既存のMicrosoft Entraのお客様がBicep テンプレートと IaC プラクティスを使用してテナントのMicrosoft Entra リソースをデプロイおよび管理するための扉も開きます。

詳細については、「Microsoft Graph リソースのBicep テンプレート」を参照してください>

#### パブリック プレビュー - Microsoft Entra ID を使用した macOS 用プラットフォーム シングル サインオン

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

本日、macOS 用のプラットフォーム SSO が、Microsoft Entra IDでパブリック プレビューで利用できるようになったことをお知らせします。 プラットフォーム SSO は、Apple Devices 用の Microsoft Enterprise SSO プラグインの機能強化であり、Mac デバイスの使用と管理がこれまで以上にシームレスかつ安全になります。 パブリック プレビューの開始時に、Platform SSO はMicrosoft Intuneで動作します。 他のモバイルデバイス管理プロバイダーは近日中に利用可能になります。 サポートと可用性の詳細については、MDM プロバイダーにお問い合わせください。 詳細については、「[macOS Platform シングル サインオンの概要 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso)」を参照してください。

#### パブリック プレビュー - ライフサイクル ワークフローのワークフロー履歴分析情報

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ライフサイクル管理

お客様はワークフローの正常性を監視し、ワークフロー、タスク、ワークフロー カテゴリ間のワークフロー処理データの表示など、ライフサイクル ワークフロー内のすべてのワークフロー全体で分析情報を取得できるようになりました。 詳細については、「[Workflow Insights (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-insights)」を参照してください。

#### パブリック プレビュー - カスタム セキュリティ属性を使用してライフサイクル ワークフロー スコープを構成する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ライフサイクル管理

お客様は、他の属性に加えて、カスタム セキュリティ属性に格納されている機密人事データを適用できるようになりました。 この更新により、お客様は、参加者、ムーバー、および脱退者のシナリオを自動化するためのライフサイクル ワークフローでワークフローのスコープを定義できます。 詳細については、「[カスタム セキュリティ属性を使用してワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-insights)のスコープを設定する」を参照してください。

#### パブリック プレビュー - ライフサイクル ワークフローを使用して同期されたユーザー アカウントを有効、無効、削除する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ライフサイクル管理

ライフサイクル ワークフローで、Active Directory Domain Services (AD DS) からMicrosoft Entraに同期されるユーザー アカウントを有効、無効、削除できるようになりました。 この機能を使用すると、保持期間の後にユーザー アカウントを削除することで、従業員のオフボード プロセスを確実に完了させることができます。

詳細については、「[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-on-premises)を使用して同期されたオンプレミス ユーザーを管理する」を参照してください。

#### パブリック プレビュー - 多要素認証用の外部認証方法

**種類:** 新機能**サービス カテゴリ:** MFA**製品の機能:** ユーザー認証

外部認証方法を使用すると、Microsoft Entra IDで優先多要素認証 (MFA) ソリューションを使用できます。 詳細については、「Microsoft Entra ID (プレビュー)を参照してください。

#### 一般提供の - `LastSuccessfulSignIn`

**種類：** 機能の変更**サービス カテゴリ:** MS Graph**製品の機能:** 監視とレポート

一般的な需要とプロパティの安定性に対する信頼度の向上により、更新プログラムは V1 に `LastSuccessfulSignIn` & `LastSuccessfulSigninDateTime` を追加します。 今すぐ運用環境でこれらのプロパティを自由に使ってみてください。 詳細については、「[signInActivity リソースタイプ](https://learn.microsoft.com/ja-jp/graph/api/resources/signinactivity)」を参照してください。

#### 一般提供 - 新しいアプリケーションの既定の受け入れ済みトークン バージョンの変更

**種類:** 変更の計画**サービス カテゴリ:** 他**製品の機能:** 開発者エクスペリエンス

2024 年 8 月以降、任意のインターフェイス (Microsoft Entra 管理センター、Azure ポータル、Powershell/CLI、または Microsoft Graph アプリケーション API を含む) を使用して作成された新しいMicrosoft Entra アプリケーションには、アプリ登録の `requestedAccessTokenVersion` プロパティの既定値が 2 に設定されています。 この機能は、前の既定値の null' (つまり 1) からの変更です。 つまり、新しいリソース アプリケーションは、既定で v1 ではなく v2 access トークンを受け取ります。 この更新プログラムにより、アプリのセキュリティが強化されます。 トークンのバージョンの違いの詳細については、「[Microsoft アイデンティティ プラットフォームにおけるアクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)」と「[アクセス トークンのクレーム参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference)」をご参照ください。

#### 一般提供 - Windows アカウント拡張機能がMicrosoft シングル サインオンになりました

**種類：** 機能の変更**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** SSO

Windows アカウント拡張機能は、ドキュメントおよび Chrome ストアの [Microsoft シングル サインオン](https://chromewebstore.google.com/detail/microsoft-single-sign-on/ppnbnpeolgkicgegkbkbjmhlideopiji) 拡張機能になりました。 Windows アカウント拡張機能は、新しい macOS の互換性を表すために更新されます。 この機能は、Chrome の Microsoft シングル サインオン (SSO) 拡張機能と呼ばれるようになり、Apple デバイス用の Enterprise SSO プラグインを使用してシングル サインオンとデバイス ID 機能を提供します。 この更新プログラムは拡張機能の名前の変更に過ぎません。拡張機能自体にソフトウェアの変更はありません。

#### 一般提供 - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2024 年 5 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

Microsoftプロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

- [ClearView Trade](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clearview-trade-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「[Microsoft Entra ID?](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) でのアプリ プロビジョニングについて」を参照してください。

### 2024 年 4 月

#### パブリック プレビュー - Android Web ブラウザーでの FIDO2 認証

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Android では、Chrome と Microsoft Edge の両方で FIDO2 セキュリティ キーを使用してサインインできるようになりました。 この変更は、FIDO2 認証方法のスコープに含まれるすべてのユーザーに適用されます。 Android Web ブラウザーでの FIDO2 登録はまだ利用できません。

詳細については、「 Microsoft Entra IDを参照してください。

#### 一般提供 - クラウド同期を使用したActive Directoryへのセキュリティ グループのプロビジョニング

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品能力:** Microsoft Entra Cloud Sync

Active Directory (グループ ライトバックとも呼ばれます) へのセキュリティ グループのプロビジョニングは、Azure グローバル クラウドとAzure Government クラウドの Microsoft Entra Cloud Sync を通じて一般提供されるようになりました。 この新機能により、Microsoft Entra ガバナンスを使用して、オンプレミス アプリケーション (Kerberos ベースのアプリ) に基づいてActive Directoryを簡単に管理できます。 詳細については、「[Microsoft Entra Cloud Sync を使用してActive Directoryするグループをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)を参照してください。

#### Microsoft Entra Connect Sync におけるグループの書き戻し V2 (パブリック プレビュー) の使用停止

**種類:** 変更の計画**サービス カテゴリ:** プロビジョニング**製品の機能:** Microsoft Entra Connect Sync

Microsoft Entra Connect Sync のグループ ライトバック V2 (GWB) のパブリック プレビューは、2024 年 6 月 30 日以降は使用できなくなります。 この日以降、Connect Sync では、Active Directoryへのクラウド セキュリティ グループのプロビジョニングはサポートされなくなります。

Microsoft Entra Cloud Sync のもう 1 つの同様の機能は、*Group Provision to AD* です。 クラウド セキュリティ グループを AD にプロビジョニングするには、GWB V2 の代わりにこの機能を使用できます。 Cloud Sync の拡張機能およびその他の新機能が開発中です。

Connect Sync のこのプレビュー機能をご利用のお客様は、[構成を Connect Sync から Cloud Sync に切り替える](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-group-writeback)必要があります。お客様のニーズに合致する場合、すべてのハイブリッド同期を Cloud Sync に移行することもできます。 クラウド同期を並行して実行し、クラウド セキュリティ グループのプロビジョニングのみをAzure ADのクラウド同期に移行することもできます。

AD に Microsoft 365 グループを使用しているお客様は、この機能に GWB V1 を引き続き使用できます。

お客様は、 [サポートされている同期シナリオの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)というガイドを使用して、Cloud Sync への移動のみを評価できます。

#### 一般提供 - Azure モバイル アプリ (iOS および Android) での PIM 承認とアクティブ化が利用可能になりました

**種類:** 新機能**サービスのカテゴリ:** 特権ID管理**製品機能:** 特権アイデンティティ管理

PIM は、iOS と Android の両方の Azure モバイル アプリで使用できるようになりました。 お客様は、受信 PIM アクティブ化要求を承認または拒否できるようになりました。 お客様は、Microsoft Entra IDをアクティブ化し、デバイス上のアプリから直接リソース ロールの割り当てをAzureすることもできます。 詳細については、「[Azure モバイル アプリを使用して PIM ロールをアクティブ化する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-activate-your-roles)を参照してください。

#### 一般提供 - オンプレミスのパスワード リセットによるユーザー リスクの修復

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

パスワード ハッシュ同期を有効にした組織では、オンプレミスでパスワードの変更を許可してユーザー リスクを修復できるようになりました。 また、この機能を使用して、リスクベースの条件付きAccess ポリシーで自動セルフサービス修復を使用して、ハイブリッド ユーザーの時間を節約し、生産性を維持することもできます。 詳細については、「[リスクを修復してユーザーをブロック解除する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)」を参照してください。

#### 一般提供 - カスタム クレーム プロバイダーによる外部データ ソースからのトークン要求拡張の有効化

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** 拡張性

カスタム認証拡張機能を使用すると、外部システムと統合することで、Microsoft Entra認証エクスペリエンスをカスタマイズできます。 カスタム クレーム プロバイダーは、外部システムから要求をフェッチするために REST API を呼び出すカスタム認証拡張機能の一種です。 カスタム クレーム プロバイダーは、外部システムからのクレームをトークンにマップします。また、プロバイダーはディレクトリ内の 1 つまたは複数のアプリケーションに割り当てることができます。 詳細については、「[カスタム認証拡張機能の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)」を参照してください。

#### 一般提供 - 動的グループのクォータが 15,000 に増加。

**種類：** 機能の変更**サービス カテゴリ:** グループ管理**製品の機能:** ディレクトリ

Microsoft Entra組織では、以前は最大 15,000 個の動的メンバーシップ グループと動的管理単位を組み合わせることができます。

このクォータが 15,000 に引き上げられました。 たとえば、15,000 個の動的メンバーシップ グループと 10,000 個の動的 AU (または合計が 15,000 個になるその他の組み合わせ) を持つことができるようになりました。 この変更を利用するために何もする必要はありません。このアップデートはすぐに利用可能です。 詳細については、「[Microsoft Entra サービスの制限と制限](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)を参照してください。

#### 一般提供 - ライフサイクル ワークフロー: ワークフロー履歴データを CSV ファイルにエクスポート

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローで、IT 管理者は、組織のレポートと監査のニーズを満たすため、ユーザー、実行、タスクのワークフロー履歴データを CSV ファイルにエクスポートできるようになりました。

詳細については、「[ワークフロー履歴レポートをダウンロードする](https://learn.microsoft.com/ja-jp/entra/id-governance/download-workflow-history)」を参照してください。

#### パブリック プレビュー - Microsoft Entra 外部 IDのネイティブ認証

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

ネイティブ認証を使用すると、開発者はモバイル アプリケーションのサインイン エクスペリエンスの設計を包括的に制御できます。 これにより、ブラウザー ベースのソリューションに頼るのではなく、アプリにシームレスに統合された、美しいピクセル パーフェクトな認証画面を作成できます。 詳細については、「[ネイティブ認証 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication)」を参照してください。

#### 公開プレビュー - Microsoft Authenticator のパスキー

**種類:** 新機能**サービス カテゴリ:** Microsoft Authenticator アプリ**製品の機能:** ユーザー認証

ユーザーは、Microsoft Authenticatorにデバイス バインド パスキーを作成して、Microsoft Entra IDリソースにアクセスできるようになりました。 Authenticator アプリのパスキーは、コスト効率に優れており、フィッシングに強く、シームレスな認証をモバイル デバイスからユーザーに提供します。 詳細については、https://aka.ms/PasskeyInAuthenticatorを参照してください。

#### 一般提供 - ライフサイクル ワークフローのワークフローの上限が 100 に

**種類：** 機能の変更**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローで構成できるワークフローの最大数が引き上げられました。 IT 管理者は、ライフサイクル ワークフローで最大 100 個のワークフローを作成できるようになりました。 詳細については、「[Microsoft Entra ID ガバナンス サービスの制限](https://learn.microsoft.com/ja-jp/entra/id-governance/governance-service-limits)を参照してください。

#### パブリック プレビュー - ユーザーのジョブ プロファイルが変更されたときにムーバー タスクを実行するようカスタム ワークフローを構成する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローで、従業員の部門、職務、または場所の変更などのジョブ変更イベントに基づいてワークフローをトリガーし、ワークフロー スケジュールで実行されるワークフローを表示できるようになりました。 この機能により、お客様は新しいワークフロー トリガーを使用して、組織内を移動する従業員に関連付けられたタスク (トリガーなど) を実行するためのカスタム ワークフローを作成できます。

- 指定した属性が変更されたときのワークフロー
- ユーザーがグループのメンバーシップに追加または削除されたときのワークフロー
- 移動についてユーザーのマネージャーに通知するタスク
- ライセンスを割り当てる、または選択したライセンスをユーザーから削除するタスク

詳細については、Microsoft Entra 管理センターのチュートリアル「職務変更時に従業員の移動タスクを自動化する方法」をご覧ください。

#### 一般公開 - Microsoft Graph アクティビティ ログ

**種類:** 新機能**サービス カテゴリ:** Microsoft Graph**製品の機能:** 監視とレポート

Microsoft Graph アクティビティ ログが一般公開されました。 Microsoft Graphアクティビティ ログを使用すると、テナント内の Microsoft Graph サービスに対して行われた HTTP 要求を可視化できます。 セキュリティの脅威が急速に増大し、攻撃の数も増加していますが、このログ データ ソースを使用すると、セキュリティ分析、脅威の探索、テナント内のアプリケーション アクティビティの監視を実行できます。 詳細については、「[Access Microsoft Graph アクティビティ ログ](https://learn.microsoft.com/ja-jp/graph/microsoft-graph-activity-logs-overview)を参照してください。

#### 一般提供 - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2024 年 4 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

Microsoftプロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

[CultureHQ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/culturehq-provisioning-tutorial)[elia](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elia-provisioning-tutorial)[GoSkills](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goskills-provisioning-tutorial)[Island](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/island-provisioning-tutorial)[Jellyfish](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jellyfish-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「Microsoft Entraを使用して SaaS アプリケーションへのユーザー プロビジョニングを自動化する」を参照してください。

#### 一般提供 - Microsoft Entra の検証済み ID のクイック セットアップ

**種類:** 新機能**サービス カテゴリ:** 検証済み ID**製品機能:** 分散型 ID

クイック Microsoft Entra Verified IDセットアップが一般公開され、管理者が [開始] ボタンを 1 回選択するだけで完了する必要があるいくつかの構成手順が削除されます。 簡易設定では、署名キーの生成、分散化 ID の登録、ドメインの所有権の検証が行われます。 また、確認済みのワークプレース資格情報も作成されます。 詳細については、「[Quick Microsoft Entra Verified ID setup](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-tenant-quick) を参照してください。

#### パブリック プレビュー - エンタイトルメント管理を使用してMicrosoft Entraロールを割り当てる

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

従業員とゲストにMicrosoft Entraロールを割り当てることで、エンタイトルメント管理を使用して、ユーザーの権利を調べて、そのユーザーに割り当てられているロールをすばやく特定できます。 Microsoft Entra ロールをリソースとしてアクセス パッケージに含める場合、そのロールの割り当てが*割り当て可能*か、*active*かを指定することもできます。

アクセス パッケージを使用してMicrosoft Entraロールを割り当てることは、ロールの割り当てを大規模に効率的に管理し、ロールを改善するのに役立ちます。 詳細については、「[Microsoft Entra の役割を割り当てる（プレビュー）](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-roles)」を参照してください。

#### 一般提供 - セルフサービス パスワード リセット管理者ポリシーの拡張により、より多くの役割が対象となる

**種類：** 機能の変更**サービス カテゴリ:** セルフサービス パスワード リセット**製品の機能:** ID のセキュリティと保護

管理者向けのセルフサービス パスワード リセット (SSPR) ポリシーが拡張され、3 つの組み込み管理者ロールが追加されました。 追加のロールは次のとおりです。

- Teams 管理者
- Teams 通信管理者
- Teams デバイス管理者

スコープ管理者ロールの詳細な一覧を含む、管理者用セルフサービス パスワード リセットの詳細については、「[管理者リセット ポリシーの違い](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy#administrator-reset-policy-differences)」を参照してください。

### 2024 年 3 月

#### パブリック プレビュー - 外部ユーザーを内部ユーザーに変換する

**種類:** 新機能**サービス カテゴリ:** [ユーザー管理]**製品の機能:** [ユーザー管理]

外部ユーザー変換を使用すると、お客様はユーザー オブジェクトを削除して新たに作成することなく、外部ユーザーを内部メンバーに変換できます。 同じ基になるオブジェクトを維持することで、ユーザーのアカウントとリソースへのaccessが中断されず、アクティビティの履歴はホスト組織との関係が変化してもそのまま残ります。

外部から内部へのユーザーの変換機能には、オンプレミスの同期されたユーザーを変換する機能も含まれています。 詳細については、「[外部ユーザーを内部ユーザーに変換する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/users/convert-external-users-internal)」を参照してください。

#### パブリック プレビュー - ロックボックス要求の代替メール通知

**種類:** 新機能**サービス カテゴリ:** 他**製品機能:** アクセス制御

Microsoft Azure用カスタマー ロックボックスは、ユーザーが代替メール ID を使用してロックボックス通知を取得できるようにする新機能を起動しています。 この機能により、ロックボックスのお客様は、Azure アカウントが電子メールで有効になっていないシナリオや、テナント管理者またはサブスクリプション所有者として定義されたサービス プリンシパルがある場合に通知を受信できます。

#### 変更の計画 - 条件付きアクセスの場所の条件が強化

**種類:** 変更の計画**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

2024 年 4 月中旬以降、条件付きAccess *Locations* 条件が上向きになっています。 場所は *Network* の割り当てとなり、新しい「Global Secure Access」の割り当ては - *すべての準拠しているネットワークの場所*です。

この変更は自動的に行われるため、管理者が実行するアクションはありません。 詳細については、以下を参照してください。

- 使い慣れた*場所*の条件は変更されず、*場所*の条件のポリシーを更新すると*ネットワーク*割り当てに反映され、その逆も同様です。
- 機能の変更はなく、既存のポリシーは変更なしで引き続き機能します。

#### 一般提供 - グループのPIMを使用したジャストインタイムアプリケーションアクセス

**種類:** 新機能**サービスのカテゴリ:** 特権ID管理**製品機能:** 特権アイデンティティ管理

AWSやGCPといったMicrosoft以外のアプリケーションへのジャストインタイムアクセスを提供する。 この機能により、グループの PIM が統合されます。 PIM を使用したアプリケーション プロビジョニングでは、非Microsoft アプリのロールへの Just-In-Time アクセスを要求するときに、アクティブ化時間が 40 分以上から約 2 分に短縮されます。

詳細については次を参照してください:

- [AWS](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-provisioning-tutorial#just-in-time-jit-application-access-with-pim-for-groups)
- [GCP](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/g-suite-provisioning-tutorial#just-in-time-jit-application-access-with-pim-for-groups)

#### パブリック プレビュー - サブスクリプション スコープの要求に対する Azure ロックボックス承認者ロール

**種類:** 新機能**サービス カテゴリ:** 他**製品の機能:** ID ガバナンス

カスタマー ロックボックス for Microsoft Azureでは、新しい組み込みのAzure ロールベースのアクセス制御 ロールが開始されます。これにより、顧客はカスタマー ロックボックス要求の承認/拒否を担当するユーザーに対して、より低い特権ロールを使用できます。 この機能は、ロックボックス承認者が顧客サブスクリプション内のAzureリソースにアクセスするMicrosoft サポート エンジニアからの要求に対して動作する顧客管理者ワークフローを対象とします。

この最初のフェーズでは、新しい組み込みのAzureロール ベースのAccess Control ロールを起動します。 このロールは、サブスクリプションとそのリソースに対するカスタマー ロックボックス承認者権限Azure持つ個人に対して可能なアクセスをスコープダウンするのに役立ちます。 テナント スコープのリクエストに対する同様のロールは、以降のリリースで利用できます。

#### 一般提供 - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2024 年 3 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

プロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

- [Astro](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/astro-provisioning-tutorial)
- [Egnyte](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/egnyte-provisioning-tutorial)
- [MobileIron](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mobileiron-provisioning-tutorial)
- [SAS Viya SSO](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sas-viya-sso-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「[Microsoft Entra ID?](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) でのアプリ プロビジョニングについて」を参照してください。

#### 一般提供 - Microsoft Entraの TLS 1.3 サポート

**種類:** 新機能**サービス カテゴリ:** 他**製品の機能:** プラットフォーム

Microsoft Entra、セキュリティのベスト プラクティス ([NIST - SP 800-52 Rev. 2](https://csrc.nist.gov/pubs/sp/800/52/r2/final)) に合わせて、エンドポイントのトランスポート層セキュリティ (TLS) 1.3 のサポートがロールアウトされることをお知らせします。 この変更により、Microsoft Entra ID関連エンドポイントは TLS 1.2 プロトコルと TLS 1.3 プロトコルの両方をサポートします。 詳細については、Microsoft Entra サービスの [TLS 1.3 のサポート](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/enable-support-tls-environment?tabs=azure-monitor#tls-13-support-for-microsoft-entra-services)を参照してください。

#### 一般提供 - API 主導型インバウンド プロビジョニング

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** Microsoft Entra IDへのインバウンド

API 駆動型の受信プロビジョニングでは、Microsoft Entra ID プロビジョニング サービスが任意のレコード システムとの統合をサポートするようになりました。 顧客とパートナーは、任意の自動化ツールを選択して、Microsoft Entra IDへのプロビジョニングに関する任意のレコード システムから従業員データを取得できます。 この機能は、接続されているオンプレミスの Active Directory ドメインにも適用されます。 IT 管理者は、属性マッピングを使用してデータを処理および変換する方法を完全に制御できます。 Microsoft Entra IDで従業員データを利用できるようになったら、IT 管理者は、Microsoft Entra ID ガバナンス ライフサイクル ワークフローを使用して、適切な joiner-mover-leaver ビジネス プロセスを構成できます。 詳細については、「[API 駆動型インバウンド プロビジョニングの概念](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)」を参照してください。

#### 一般提供 - セキュリティ情報のパスワードの変更

**種類:** 新機能**サービス カテゴリ:** セキュリティ情報**製品の機能:** エンド ユーザー エクスペリエンス

現在一般提供されている「[マイ サインイン (microsoft.com)](https://mysignins.microsoft.com/)」では、エンド ユーザーがインラインでパスワードを変更できるようになりました。 パスワードと MFA 資格情報を使って認証しているユーザーは、既存のパスワードを入力せずにパスワードを変更できます。 4 月 1 日から段階的なロールアウトにより、[パスワードの変更 (windowsazure.com)](https://account.activedirectory.windowsazure.com/ChangePassword.aspx) ポータルからのトラフィックは、新しいマイ サインイン変更エクスペリエンスにリダイレクトされます。 [パスワードの変更 (windowsazure.com)](https://account.activedirectory.windowsazure.com/ChangePassword.aspx) は 2024 年 6 月以降は利用できなくなりますが、引き続き新しいエクスペリエンスにリダイレクトされます。

詳細については次を参照してください:

- [Microsoft Entra の概要のためのセキュリティ情報登録の組み合わせ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined).
- [マイ アカウント ポータルで職場または学校アカウント設定を変更する](https://support.microsoft.com/account-billing/change-work-or-school-account-settings-in-the-my-account-portal-e50bfccb-58e9-4d42-939c-a60cb6d56ced)

### 2024 年 2 月

#### 一般提供 - Azure モバイル アプリでの ID 保護とリスクの修復

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

これまでポータルでのみサポートされていた Identity Protection は、管理者が ID リスクを積極的に管理可能にする強力なツールです。 Azureモバイル アプリで利用できるようになったので、管理者は潜在的な脅威に簡単かつ効率的に対応できます。 この機能には包括的なレポートが含まれており、侵害されたユーザー アカウントや疑わしいサインインなどの危険な動作に関する分析情報が提供されます。

危険なユーザー レポートを使用すると、管理者は侵害された、または脆弱であるとフラグが付けられたアカウントを可視化できます。 サインインのブロック/ブロック解除、侵害の正当性の確認、パスワードのリセットなどのアクションに簡単にアクセスできるため、タイムリーなリスク軽減が確保されます。

加えて、危険なサインイン レポートでは、疑わしいサインイン アクティビティの詳細な概要が提供され、管理者が潜在的なセキュリティ侵害を特定するのに役立ちます。 モバイル端末での機能はサインイン情報の閲覧に限られますが、管理者はポータルを通じてサインインのブロックなど必要なアクションを実行できます。または、管理者は、すべてのリスクが軽減されるまで、該当する危険なユーザーのアカウントを管理することもできます。

Azure モバイル アプリで Identity Protection を使用して、ID リスクを簡単に先取りできます。 これらの機能は、セキュリティで保護された環境と組織の安心感を維持するためのツールをユーザーに提供することを目的としています。

モバイル アプリは、次のリンクからダウンロードできます。

- アンドロイド： https://aka.ms/AzureAndroidWhatsNew
- Ios： https://aka.ms/ReferAzureIOSWhatsNew

#### 変更の計画 - Microsoft Entra ID Identity Protection: 低リスクの期限切れ

**種類:** 変更の計画**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

2024 年 3 月 31 日以降、"*low*" のすべてのリスク検出と、6 か月を超える Microsoft Entra ID Identity Protection のユーザーは自動的に期限切れになり、無視されます。 この変更により、お客様はより関連性の高いリスクに集中でき、よりクリーンな調査環境を提供できるようになります。 詳細については、「[リスク検出とは](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。

#### パブリック プレビュー - 追加のシナリオに対する条件付きAccess再認証ポリシーの拡張

**種類：** 機能の変更**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

再認証ポリシーを使用すると、通常は重要なアプリケーションにアクセスしたり機密性の高い操作を実行したりする前に、ユーザーに対話形式で再度資格情報を提供するよう要求できます。 サインイン頻度の条件付きAccess セッション制御と組み合わせることで、リスクのあるユーザーとサインイン、または Intune 登録の再認証を要求できます。 このパブリック プレビューでは、条件付きAccessで保護されているリソースに対して再認証を要求できるようになりました。 詳細については、「[毎回再認証を行う](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime#require-reauthentication-every-time)を参照してください。

#### 一般提供 - 新しいプレミアム ユーザー リスク検出機能「不審な API トラフィック」が Identity Protection で利用可能に

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

Identity Protection で、「*不審な API トラフィック*」と呼ばれる新しいプレミアム ユーザー リスク検出がリリースされました。 この検出は、Identity Protection でユーザーによる異常な Graph トラフィックを検出したときに報告されます。 不審な API トラフィックは、ユーザーが侵害され、環境内で偵察を行っていることを示唆している可能性があります。 これを含む Identity Protection 検出の詳細については、リンク「[リスク検出とは](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」にある公開ドキュメントをご覧ください。

#### 一般提供 - 条件付きアクセス ポリシー一覧の詳細なフィルター処理

**種類:** 新機能**Service category:** 条件付きアクセス**製品機能:** アクセス制御

条件付きAccessポリシーをアクター、ターゲット リソース、条件、許可制御、およびセッション制御でフィルター処理できるようになりました。 詳細なフィルター処理エクスペリエンスは、管理者が特定の構成を含むポリシーをすばやく検出するのに役立ちます。 詳細については、「[条件付きAccessとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)ɰ」を参照してください。

#### サポート終了 - Forefront Identity Manager 用 Azure Active Directory コネクタ (FIM WAAD コネクタ)

**種類：** 廃止**サービス カテゴリ:** Microsoft Identity Manager**製品の機能:** Microsoft Entra IDへのインバウンド

2014 年の for Forefront Identity Manager (FIM WAAD Connector) 用のAzure Active Directory コネクタは、2021 年に非推奨となりました。 このコネクタの標準サポートは、2024 年 4 月に終了しました。 お客様は、MIM 同期のデプロイからこのコネクタを削除し、代わりに代替プロビジョニング メカニズムを使用する必要があります。 詳細については、「[Microsoft Entra IDのFIM ConnectorからMicrosoft Entraプロビジョニング シナリオを移行する](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/migrate-from-the-fim-connector-for-azure-active-directory)」を参照してください。

#### 一般提供 - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2024 年 2 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

プロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

- [Alohi](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alohi-provisioning-tutorial)
- [Insightly SAML](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insightly-saml-provisioning-tutorial)
- [Starmind](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/starmind-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「[Microsoft Entra ID?](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) でのアプリ プロビジョニングについて」を参照してください。

#### 一般提供 - Microsoft Entra アプリケーション ギャラリーで利用可能な新しいフェデレーション アプリ - 2024 年 2 月

**種類:** 新機能**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** サード パーティ統合

2022 年 2 月には、フェデレーションをサポートする次の 20 個の新しいアプリケーションがアプリ ギャラリーに追加されました。

[Crosswise](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/presswise-tutorial)、[Stonebranch Universal Automation Center (SaaS クラウド)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/stonebranch-universal-automation-center-saas-cloud-tutorial)、[ProductPlan](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/productplan-tutorial)、[Bigtincan for Outlook](https://www.bigtincan.com/content/)、Blinktime、Stargo、[Garage Hive BC v2](https://garagehive.co.uk/)、[Avochato](https://www.avochato.com/)、[Luscii](https://vitals.luscii.com/)、[LEVR](https://levr.work/)、[XM Discover](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xm-discover-tutorial)、Sailsdock、[Mercado Eletronico SAML](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mercado-eletronico-saml-tutorial)、[Moveworks](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moveworks-tutorial)、[Silbo](https://app.ambuliz.com/)、[Alation Data Catalog](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alation-data-catalog-tutorial)、[Papirfly SSO](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/papirfly-sso-tutorial)、[Secure Cloud User Integration](https://opentextcybersecurity.com/)、[AlbertStudio](https://sandbox.albertinvent.com/)、[Automatic Email Manager](https://www.automatic-email-manager.com/?utm_source=azuregallery)、[Streamboxy](https://en.streamboxy.com/)、[NewHotel PMS](http://www.newhotelcloud.com/)、[Ving Room](https://www.letsving.com/)、[Trevanna Tracks](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trevanna-tracks-tutorial)、[Alteryx Server](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alteryx-server-tutorial)、[RICOH Smart Integration](https://www.ricoh.com/)、[Genius](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/genius-tutorial)、[Othership Workplace Scheduler](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/othership-workplace-scheduler-tutorial)、[GitHub Enterprise Managed User - ghe.com](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-managed-user-tutorial)、[Freightender SSO for TRP (Tender Response Platform)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freightender-sso-for-trp-tender-response-platform-tutorial)、[BeWhere Portal (UPS アクセス)](http://www.bewhere.com/)、[Flexiroute](https://www.flexiroute.net/)、[SEEDL](https://www.seedlgroup.com/)、[Isolocity](https://www.isolocity.com/)、[SpotDraft](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spotdraft-tutorial)、[Blinq](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blinq-tutorial)、[Cisco Phone OBTJ](https://www.cisco.com/)、[Applitools Eyes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/applitools-eyes-tutorial)。

すべてのアプリケーションのドキュメントについては、こちら (https://aka.ms/AppsTutorial ) をご覧ください。

Microsoft Entra ID アプリ ギャラリーにアプリケーションを一覧表示するには、https://aka.ms/AzureADAppRequest の詳細を参照してください。

### 2024 年 1 月

#### 一般提供開始 - 新しいMicrosoft Entraホーム ページ

**種類：** 機能の変更**サービス カテゴリ:** 該当なし**製品の機能:** ディレクトリ

次のタスクを実行できるように、Microsoft Entra 管理センターのホームページを再設計しました。

- 製品スイートについて確認する
- 特徴の価値を最大化する機会を特定する
- 最新のお知らせ、新機能など、最新情報を入手する

新しいエクスペリエンスについては、https://entra.microsoft.com/ を参照してください

#### パブリック プレビュー - 条件付きアクセスでの詳細な証明書ベースの認証構成

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

条件付きAccessの認証強度機能を使用して、証明書の発行者またはポリシー OID に基づいてaccessを許可する高度な証明書ベースの認証 (CBA) オプションを使用して、カスタム認証強度ポリシーを作成できるようになりました。 MFA がパートナーのMicrosoft Entra ID テナントから信頼されている外部ユーザーの場合、これらのプロパティに基づいてアクセスを制限することもできます。 詳細については、「[カスタム条件付きアクセス認証の強度](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-advanced-options)」を参照してください。

#### 一般提供開始 - アプリの条件付きアクセスフィルタ

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

条件付きAccessのアプリのフィルターを使用すると、管理者はアプリケーションにカスタム セキュリティでタグを付け、直接割り当てを使用するのではなく、条件付きAccess ポリシーでターゲットにできるため、ポリシー管理が簡略化されます。 この機能を使うと、お客様はポリシーをスケールアップして、任意の数のアプリを保護できます。 詳細については、「[Conditional Access: アプリケーションのフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-filter-for-applications)」を参照してください>

#### パブリック プレビュー - テナント間マネージャー同期

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ガバナンス

テナント間同期により、テナント間でのマネージャー属性の同期がサポートされるようになりました。 詳細については、「[属性](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#attributes)」を参照してください。

#### Microsoft Defender for Office のアラートが Identity Protection に一般提供されています。

**種類:** 新機能**サービス カテゴリ:** Identity Protection**製品の機能:** ID のセキュリティと保護

*疑わしい送信パターン*リスク検出タイプは、Microsoft Defender for Office (MDO) から提供された情報を使用して発見されます。 このアラートは、組織内の誰かが疑わしいメールを送信したときに生成されます。 このアラートが生成されるのは、メールの送信が制限されるリスクがあるか、またはメールの送信が既に制限されているからです。 この検出により、ユーザーは中程度のリスクに移行します。これは、MDO をデプロイした組織でのみ発生します。 詳細については、「[リスク検出とは](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。

#### パブリック プレビュー - MFA Server から移行するための新しいMicrosoft Entraの推奨事項

**種類:** 新機能**サービス カテゴリ:** MFA**製品の機能:** ユーザー認証

お客様がMFA ServerからMicrosoft Entraの多要素認証に移行するための新しい推奨事項が、Microsoft Entra管理センターでリリースされました。 MFA サーバーは、2024 年 9 月 30 日に廃止されます。 過去 7 日間に MFA Server アクティビティを利用したユーザーには、現在の使用状況に関する詳細と、多要素認証に移行する手順を含む推奨事項Microsoft Entra表示されます。 詳細については、「[MFA Server から Microsoft Entra の多要素認証に移行する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-azure-mfa)」を参照。

#### パブリック プレビュー - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2024 年 1 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

プロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

- [Personify Inc](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/personify-inc-provisioning-tutorial)
- [Screensteps](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/screensteps-provisioning-tutorial)
- [WiggleDesk](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wiggledesk-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「[Microsoft Entra ID?](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) でのアプリ プロビジョニングについて」を参照してください。

#### 一般提供 - Microsoft Entra アプリケーション ギャラリーで利用可能な新しいフェデレーション アプリ - 2024 年 1 月

**種類:** 新機能**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** サード パーティ統合

2024 年 1 月に、フェデレーションをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。

[Boeing ToolBox](https://www.boeing.com/)、[Kloud Connect Practice Management](https://www.kloudconnect.com.au/)、[トーニチ・ネクスタ・メイシ (Tonichi Nexta Meishi)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tonicdm-tutorial)、[Vinkey](https://vinkey.app/)、[Cognito Forms](https://www.cognitoforms.com/)、[Ocurus](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ocurus-tutorial)、[Magister](https://www.magister.nl/)、[eFlok](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eflok-tutorial)、[GoSkills](https://www.goskills.com/)、[FortifyData](https://fortifydata.com/)、[Toolsfactory platform, Briq](https://toolsfactory.nl/en/ogsm-tool/)、[Mailosaur](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mailosaur-tutorial)、[Astro](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/astro-tutorial)、[JobDiva/Teams VOIP Integration](https://www.jobdiva.com/)、[Colossyan SAML](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/colossyan-saml-tutorial)、[CallTower Connect](https://www.calltower.com/calltower-connect/)、[MetLife Legal Plans Member App](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/metlife-legal-plans-member-app-tutorial)、[Navigo Cloud SAML](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/navigo-cloud-saml-tutorial)、[Delivery Scheduling Tool](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/delivery-scheduling-tool-tutorial)、[Highspot for MS Teams](https://app.highspot.com/signin)、[Reach 360](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reach-360-tutorial)、[Fareharbor SAML SSO](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fareharbor-saml-sso-tutorial)、[HPE Aruba Networking EdgeConnect Orchestrator](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hpe-aruba-networking-edgeconnect-orchestrator-tutorial)、[Terranova Security Awareness Platform](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/terranova-security-awareness-platform-tutorial)。

すべてのアプリケーションのドキュメントについては、こちら (https://aka.ms/AppsTutorial ) をご覧ください。

Microsoft Entra ID アプリ ギャラリーにアプリケーションを一覧表示するには、https://aka.ms/AzureADAppRequest の詳細を参照してください。

### 2023 年 12 月

#### パブリック プレビュー - B2B collaborationの構成可能な引き換え順序

**種類:** 新機能**サービス カテゴリ:** B2B**製品の機能:** B2B/B2C

構成可能な引き換えを使用すると、ゲスト ユーザーが招待を承諾したときにサインインできる ID プロバイダーの順序をカスタマイズできます。 このオプションを使用すると、Microsoftによって設定された既定の構成順序をオーバーライドし、独自の構成順序を使用できます。 このオプションを使用すると、MICROSOFT ENTRA ID検証済みドメインよりも SAML/WS フィードフェデレーションの優先順位付けなどのシナリオに役立ちます。 このオプションは、引き換え時に特定の ID プロバイダーを無効にしたり、メールのワンタイム パスコードなどを引き換えオプションとしてのみ使用したりします。 詳細については、「[構成可能な引き換え (プレビュー)](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#configurable-redemption)を参照してください。

#### 一般提供 - 動的グループ ルール ビルダーの編集

**種類：** 機能の変更**サービス カテゴリ:** グループ管理**製品の機能:** ディレクトリ

動的グループ ルール ビルダーが更新され、パフォーマンスが低下するため、"*contains*" および "*notContains*" 演算子が含まれなくなりました。 必要に応じて、テキスト ボックスに直接入力し、これらの演算子を使用して動的メンバーシップ グループのルールを作成することもできます。 詳細については、Azure ポータルの[Rule ビルダー](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership#rule-builder-in-the-azure-portal)を参照してください。

### 2023 年 11 月

#### Microsoft Entra Connect Sync におけるグループの書き戻し V2 (パブリック プレビュー) の使用停止

**種類:** 変更の計画**サービス カテゴリ:** プロビジョニング**製品の機能:** Microsoft Entra Connect Sync

Microsoft Entra Connect Sync のグループ ライトバック V2 (GWB) のパブリック プレビューは、2024 年 6 月 30 日以降は使用できなくなります。 この日以降、Connect Sync では、Active Directoryへのクラウド セキュリティ グループのプロビジョニングはサポートされなくなります。

もう 1 つの同様の機能は、Microsoft Entra クラウド同期で提供されます。これは、クラウド セキュリティ グループを AD にプロビジョニングするために GWB V2 の代わりに使用される場合があります。 Cloud Sync の拡張機能およびその他の新機能が開発中です。

Connect Sync のこのプレビュー機能をご利用のお客様は、[構成を Connect Sync から Cloud Sync に切り替える](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-group-writeback)必要があります。(お客様のニーズに合致する場合) すべてのハイブリッド同期を Cloud Sync に移行することもできます。 また、Cloud Sync を並行して実行し、AD へのクラウド セキュリティ グループのプロビジョニングのみを Cloud Sync に移動することもできます。

MICROSOFT 365 グループを AD にプロビジョニングする顧客は、この機能に GWB V1 を引き続き使用できます。

お客様は、 [サポートされている同期シナリオの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)というガイドを使用して、Cloud Sync への移動のみを評価できます。

#### 一般提供 - Microsoft Entra Cloud Sync では、Exchangeのお客様に対してExchangeハイブリッド構成を有効にする機能がサポートされるようになりました

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品機能:** Microsoft Entra Connect

Exchangeハイブリッド機能を使用すると、オンプレミスとMicrosoft 365の両方でExchangeメールボックスを共存できます。 Microsoft Entra Cloud Sync は、特定のExchange関連属性のセットをMicrosoft Entra IDからオンプレミスのディレクトリに同期します。 さらに、切断されたフォレストも同期されます (フォレスト間にネットワークの信頼は必要ありません)。 この機能により、Microsoft Entra Connect 同期でこの機能が有効になっている既存のお客様は、この機能をMicrosoft Entraクラウド同期で移行して適用できるようになりました。詳細については、「[Exchange クラウド同期を使用したハイブリッド ライトバック](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/exchange-hybrid-writeback)」を参照してください。

#### 一般提供 - ゲスト ガバナンス: 非アクティブなゲストの分析情報

**種類:** 新機能**サービス カテゴリ:** レポーティング**製品の機能:** ID ガバナンス

組織内の非アクティブなゲスト ユーザーに関するインテリジェントな分析情報を活用して、ゲスト アカウントを大規模に監視します。 組織のニーズに応じて非アクティブしきい値をカスタマイズし、監視するゲスト ユーザーのスコープを絞り込んで、非アクティブな可能性のあるゲスト ユーザーを特定します。 詳細については、[アクセス レビューを使用して古いゲスト アカウントのモニターとクリーンアップを行う](https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-stale-guest-accounts)を参照してください。

#### パブリック プレビュー - signInActivity API の lastSuccessfulSignIn プロパティ

**種類:** 新機能**サービス カテゴリ:** MS Graph**製品の機能:** エンド ユーザー エクスペリエンス

サインインが対話型か非対話型かに関係なく、特定のユーザーの最後の**成功**したサインイン時間を表示するための追加のプロパティが signInActivity API に追加されました。 このプロパティのデータはバックフィルされないため、2023 年 12 月 8 日以降は、成功したサインイン データのみ返されることになります。

#### 一般公開 - 条件付きアクセスポリシーの自動ロールアウト

**種類:** 新機能**Service category:** 条件付きアクセス**製品機能:** アクセス制御

2023 年 11 月から、Microsoftは、Microsoft管理された条件付きアクセス ポリシーを使用して顧客を自動的に保護し始めます。 Microsoftは、外部テナントでこれらのポリシーを作成して有効にします。 次のポリシーは、ポリシー作成前に通知を受け取る、対象となるすべてのテナントにロールアウトされます。

1. 管理ポータルの多要素認証: このポリシーは特権管理者ロールを対象としており、管理者がMicrosoft管理ポータルにサインインするときに多要素認証が必要です。
2. ユーザーごとの多要素認証ユーザー向けの多要素認証: このポリシーは、ユーザーごとの多要素認証を使用するユーザーを対象としており、すべてのリソースに対して多要素認証を要求します。
3. リスクの高いサインインに対する多要素認証: このポリシーはすべてのユーザーに適用され、リスクの高いサインインには多要素認証と再認証が求められます。

詳細については次を参照してください:

- [Microsoft Entra の自動条件付きアクセス ポリシーは ID 保護を簡素化します](https://www.microsoft.com/security/blog/2023/11/06/automatic-conditional-access-policies-in-microsoft-entra-streamline-identity-protection/)
- [Microsoft 管理ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/managed-policies)

#### 一般提供 - Microsoft Entra IDのカスタム セキュリティ属性

**種類:** 新機能**サービス カテゴリ:** ディレクトリ管理**製品の機能:** ディレクトリ

Microsoft Entra IDのカスタム セキュリティ属性は、Microsoft Entra オブジェクトに定義して割り当てることができるビジネス固有の属性 (キーと値のペア) です。 これらの属性は、情報の格納、オブジェクトの分類、特定のAzure リソースに対するきめ細かなアクセス制御の適用に使用できます。 カスタム セキュリティ属性は、[Azure 属性ベースのアクセス制御 (Azure ABAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview)で使用できます。 詳細については、「Microsoft Entra ID?を参照してください。

一般提供のため、カスタム セキュリティ属性監査ログに変更が加えられましたが、これは日常業務に影響する可能性があります。 プレビュー期間中にカスタム セキュリティ属性監査ログを使用していた場合、監査ログ操作が中断されないよう、2024 年 2 月までに実行する必要があるアクションが存在します。 詳細については、「[カスタム セキュリティ属性監査ログ](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage#custom-security-attribute-audit-logs)」をご覧ください。

#### パブリック プレビュー - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2023 年 11 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

プロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

- [口語](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/colloquial-provisioning-tutorial)
- [Diffchecker](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/diffchecker-provisioning-tutorial)
- [M-Files](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/m-files-provisioning-tutorial)
- [XM Fax および XM SendSecure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xm-fax-and-xm-send-secure-provisioning-tutorial)
- [Rootly](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rootly-provisioning-tutorial)
- [単純な入力/出力](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/simple-in-out-provisioning-tutorial)
- [Team Today](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/team-today-provisioning-tutorial)
- [YardiOne](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yardione-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「[Microsoft Entra ID?](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) でのアプリ プロビジョニングについて」を参照してください。

#### 一般提供 - Microsoft Entra アプリケーション ギャラリーで利用可能な新しいフェデレーション アプリ - 2023 年 11 月

**種類:** 新機能**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** サード パーティ統合

2022 年 11 月に、フェデレーションをサポートする次の 22 個の新しいアプリケーションがアプリ ギャラリーに追加されました。

[Citrix Cloud](https://www.citrix.com/)、[Freight Audit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freight-audit-tutorial)、[Movement by project44](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/movement-by-project44-tutorial)、[Alohi](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alohi-tutorial)、[AMCS Fleet Maintenance](https://www.amcsgroup.com/solutions/fleet-maintenance/)、[Real Links Campaign App](https://reallinks.io/)、[Propely](https://www.propely.io/)、[Contentstack](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/contentstack-tutorial)、[Jasper AI](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jasper-ai-tutorial)、[IANS Client Portal](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ians-client-portal-tutorial)、[Avionic Interface Technologies LSMA](https://aviftech.com/)、[CultureHQ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/culturehq-tutorial)、[Hone](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hone-tutorial)、[Collector Systems](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/collector-systems-tutorial)、[NetSfere](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netsfere-tutorial)、[Spendwise](https://www.spendwise.com/)、[Stage and Screen](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/stage-and-screen-tutorial)

すべてのアプリケーションのドキュメントについては、こちら (https://aka.ms/AppsTutorial ) をご覧ください。

Microsoft Entra ID アプリ ギャラリーにアプリケーションを一覧表示するには、https://aka.ms/AzureADAppRequest の詳細を参照してください。

注

以前のバージョンのリリース ノートの新しい更新では、Microsoft Authenticatorは Android で FIPS 140 に準拠していません。 Android 上のMicrosoft Authenticatorは、FIPS で検証された暗号化を必要とする可能性のあるお客様をサポートするために、現在 FIPS コンプライアンス認定を保留中です。

### 2023 年 10 月

#### パブリック プレビュー - [マイ セキュリティ情報] におけるパスワードの管理と変更

**種類:** 新機能**サービス カテゴリ:** マイ プロファイル/アカウント**製品の機能:** エンド ユーザー エクスペリエンス

マイ サインイン ([マイ サインイン (microsoft.com)](https://mysignins.microsoft.com/)) で、エンド ユーザーによるパスワードの管理と変更がサポートされるようになりました。 ユーザーは、[マイ セキュリティ情報] でパスワードを管理し、パスワードをインラインで変更できます。 パスワードと MFA 資格情報を使って認証しているユーザーは、既存のパスワードを入力せずにパスワードを変更できます。

詳細については、「[Microsoft Entra の概要におけるセキュリティ情報の統合登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)」を参照してください。

#### パブリック プレビュー - Microsoft Entra ガバナンスを使用して AD オンプレミス アプリケーション (Kerberos ベース) を管理する

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品能力:** Microsoft Entra Cloud Sync

AD へのセキュリティ グループのプロビジョニング (グループ ライトバックとも呼ばれます) は、Microsoft Entra Cloud Sync を通じて一般公開されました。この新機能により、Microsoft Entra ガバナンスを使用して、オンプレミス アプリケーション (Kerberos ベースのアプリ) に基づいて AD を簡単に管理できます。

詳細については、「[Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups)[を使用した](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups)オンプレミス Active Directory ベースのアプリ (Kerberos) のガバナンス」を参照してください。

#### パブリック プレビュー - Microsoft Entra Permissions Management: 複数の承認システムのアクセス許可分析レポート PDF

**種類：** 機能の変更**サービス カテゴリ:** **製品の機能:** アクセス許可の管理

アクセス許可分析レポート (PAR) には、アクセス許可管理内の ID とリソース全体のアクセス許可リスクに関連する調査結果が一覧表示されます。 PAR は、お客様がクラウド インフラストラクチャで最もリスクの高い領域を検出するリスク評価プロセスの不可欠な部分です。 このレポートは、アクセス許可管理 UI で直接表示し、Excel (XSLX) 形式でダウンロードし、PDF としてエクスポートできます。 このレポートは、サポートされているすべてのクラウド環境 (アマゾン ウェブ サービス (AWS)、Microsoft Azure、Google Cloud Platform (GCP) で利用できます。

PAR PDF は、使いやすさを高めて、製品の UX 再設計の取り組みと整合させ、お客様のさまざまな機能要求に対応するために再設計されました。 [最大 10 個の認証システムの PAR PDF をダウンロードできます。](https://learn.microsoft.com/ja-jp/entra/permissions-management/product-permissions-analytics-reports)

#### 一般提供 - 拡張デバイス リスト管理エクスペリエンス

**種類：** 機能の変更**サービスカテゴリ:** デバイスアクセス管理**製品の機能:** エンド ユーザー エクスペリエンス

パブリック プレビューの発表以降、**[すべてのデバイス]** リストには次のようないくつかの変更が加えられました。

- さまざまなコンポーネント間において一貫性とアクセシビリティを優先
- リストを最新化し、主要なお客様からのフィードバックに対応
    - 無限スクロール、列の並べ替え、すべてのデバイスを選択する機能を追加
    - OS バージョンと Autopilot デバイスのフィルターを追加
- Microsoft Entraと Intune の間の接続を増やしました
    - [準拠] 列と [MDM] 列に Intune へのリンクを追加
    - [セキュリティ設定の管理] 列を追加

詳細については、「[デバイスの表示とフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities#view-and-filter-your-devices)」を参照してください。

#### 一般公開 - Windows MAM

**種類:** 新機能**Service category:** 条件付きアクセス**製品機能:** アクセス制御

Windows MAM は、アンマネージド Windows デバイスの管理機能をMicrosoftするための最初のステップです。 この機能は、Windows プラットフォームが、モバイル プラットフォームで現在エンド ユーザーに提供するシンプルさとプライバシーの約束と同等であることを確認する必要がある重要な時期に発生します。 エンド ユーザーは、デバイス全体を MDM で管理しなくても、会社のリソースをaccessできます。

詳細については、「[Windows デバイスでアプリ保護ポリシーを取得する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-windows-app-protection)を参照してください。

#### 一般提供 - Microsoft セキュリティのメール更新と Azure Active Directory リソースの Microsoft Entra ID への名前変更

**種類:** 変更の計画**サービス カテゴリ:** 他**製品の機能:** エンド ユーザー エクスペリエンス

Microsoft Entra IDは、Azure Active Directory (Azure AD) の新しい名前です。 名前の変更と新しい製品のアイコンが、Microsoftのエクスペリエンス全体に展開されるようになりました。 ほとんどの更新は、今年の 11 月中旬までに完了します。 以前に発表されたとおり、これは新しい名前の変更であり、デプロイや毎日の作業には影響しません。 機能、ライセンス、サービス使用条件、またはサポートに変更はありません。

10 月 15 日から 11 月 15 日まで、以前に azure-noreply@microsoft.com から送信された AD メールAzure、MSSecurity-noreply@microsoft.com から送信が開始されます。 この変更に合わせてOutlookルールを更新することが必要な場合があります。

さらに、必要に応じて、Azure AD のすべての参照を削除するように電子メール コンテンツを更新し、この変更を通知する情報バナーを含めます。

必要に応じて、独自の製品エクスペリエンスまたはコンテンツの名前の変更をガイドするためのリソースを以下に示します。

- [方法: Azure AD の名前を変更する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-rename-azure-ad)
- [Azure Active Directory の新名称](https://learn.microsoft.com/ja-jp/entra/fundamentals/new-name)

#### 一般提供 - エンド ユーザーは、パスワード SSO とギャラリー アプリを マイ アプリ に追加できなくなります

**種類：** 廃止**サービス カテゴリ:** マイ アプリ**製品の機能:** エンド ユーザー エクスペリエンス

2023 年 11 月 15 日より、エンド ユーザーは、マイ アプリのギャラリーにパスワード SSO アプリを追加できなくなります。 ただし、管理者は、[以下の手順](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications)に従って、引き続きパスワード SSO アプリを追加できます。 エンド ユーザーによって以前に追加されたパスワード SSO アプリは、マイ アプリで引き続き使用できます。 機能 ユーザーはギャラリー アプリを マイ アプリは廃止されました。管理者は、Enterprise Apps 内のユーザー設定の下にある Entra ポータルでユーザーに対してこの機能を有効にするオプションを表示できなくなりました。

詳細については、「[アプリケーションの検出](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview#discover-applications)」を参照してください。

#### 一般提供 - Microsoft Entra ID テナントの作成を有料サブスクリプションのみに制限する

**種類：** 機能の変更**Service category:** Azure リソースのマネージド ID**製品の機能:** エンド ユーザー エクスペリエンス

Microsoft Entra 管理センターから新しいテナントを作成する機能により、組織内のユーザーは、Microsoft Entra ID テナントからテスト テナントとデモ テナントを作成できます。[テナントの作成の詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/education/deploy/intro-azure-active-directory)。 この機能を誤った方法で使用すると、組織で管理または表示できないテナントを作成できてしまう可能性があります。 この機能を制限して、信頼できる管理者のみこの機能を使用可能にすることをお勧めします。[メンバー ユーザーの既定のアクセス許可の制限の詳細をご確認ください](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#restrict-member-users-default-permissions)。 また、Microsoft Entra監査ログを使用して、組織内のユーザーによって作成された新しいテナントを通知するディレクトリ管理: Create Company イベントを監視することもお勧めします。

組織をさらに保護するために、Microsoftでは、この機能を有料の顧客のみに制限しています。 試用版サブスクリプションをお持ちのお客様は、Microsoft Entra 管理センターからさらに多くのテナントを作成できません。 この状況で新しい試用版テナントが必要な場合は、[Free Azure アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップできます。

#### 一般提供 - 位置情報ベースのaccess controlを使用する場合、ユーザーは GPS の場所を変更できません

**種類:** 変更の計画**Service category:** 条件付きアクセス**製品の機能:** ユーザー認証

絶えず進化するセキュリティランドスケープでは、Microsoft Authenticatorは場所ベースのAccess Control (LBAC) 条件付きアクセス ポリシーのセキュリティ ベースラインを更新しています。 Microsoftは、ユーザーがモバイル デバイスの実際の GPS 位置とは異なる場所を使用している可能性がある認証を禁止するためにこれを行います。 現在、ユーザーは iOS および Android デバイスにおいて、デバイスによって報告された位置情報を変更できます。 Authenticator アプリは、Authenticator アプリがインストールされているモバイル デバイスの実際の場所をユーザーが使用していないことを検出すると、LBAC 認証を拒否し始めます。

Authenticator アプリの 2023 年 11 月リリースでは、デバイスの場所を変更しているユーザーが LBAC 認証を行うと、アプリに拒否メッセージが表示されます。 Microsoftは、ユーザーが以前のバージョンのアプリを使用して、変更された場所で認証を続行していないことを確認します。 2024 年 1 月より、Android Authenticator バージョン 6.2309.6329 以前、および iOS Authenticator バージョン 6.7.16 以前を使用しているユーザーは、LBAC の使用がブロックされます。 古いバージョンの Authenticator アプリを使っているユーザーを確認するには、[MSGraph API](https://learn.microsoft.com/ja-jp/graph/api/resources/microsoftauthenticatorauthenticationmethod) を使用できます。

#### マイ Access ポータルの [パブリック プレビュー - 概要] ページ

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** ID ガバナンス

現在、ユーザーが myaccess.microsoft.com に移動すると、組織内で使用可能なaccess パッケージの一覧が表示されます。 新しい概要ページには、ユーザーがアクセスするより関連性の高い場所が表示されます。 [概要] ページでは、完了する必要があるタスクを示し、ユーザーがマイ Accessでタスクを完了する方法を理解するのに役立ちます。

管理者は、Microsoft Entra 管理センターにサインインし、エンタイトルメント管理 &gt; 設定 &gt; オプトイン プレビュー機能に移動し、表に [マイ アクセスの概要] ページを見つけることで、概要ページプレビューを有効または無効にすることができます。

詳細については、「[My Accessの概要ページ](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview#overview-page)を参照してください。

#### パブリック プレビュー - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2023 年 10 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

プロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

- [Amazon Business](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-business-provisioning-tutorial)
- [ブッスルB2B輸送システム](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bustle-b2b-transport-systems-provisioning-tutorial)
- [Canva](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/canva-provisioning-tutorial)
- [Cybozu](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cybozu-provisioning-tutorial)
- [Forcepoint Cloud Security Gateway - ユーザー認証](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/forcepoint-cloud-security-gateway-provisioning-tutorial)
- [Hypervault](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hypervault-provisioning-tutorial)
- [Oneflow](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oneflow-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「[Microsoft Entra ID?](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) でのアプリ プロビジョニングについて」を参照してください。

#### パブリック プレビュー - Microsoft Graph アクティビティ ログ

**種類:** 新機能**サービス カテゴリ:** Microsoft Graph**製品の機能:** 監視とレポート

MicrosoftGraphActivityLogs を使用すると、管理者は、Microsoft Graph APIを介してテナントのリソースにアクセスするすべての HTTP 要求を完全に可視化できます。 これらのログは、侵害されたアカウントのアクティビティを見つけたり、異常な動作を識別したり、アプリケーション アクティビティを調査したりするために使用できます。 詳細については、「[Access Microsoft Graph アクティビティ ログ (プレビュー)](https://learn.microsoft.com/ja-jp/graph/microsoft-graph-activity-logs-overview)を参照してください。

#### パブリック プレビュー - Microsoft Entra Verified IDクイック セットアップ

**種類:** 新機能**サービス カテゴリ:** 他**製品の機能:** ID ガバナンス

プレビューで利用可能なクイック Microsoft Entra Verified IDセットアップでは、[作業の開始] ボタンを 1 回選択するだけで、管理者が完了する必要があるいくつかの構成手順が削除されます。 簡易設定では、署名キーの生成、分散化 ID の登録、ドメインの所有権の検証が行われます。 また、確認済みのワークプレース資格情報も作成されます。 詳細については、「[Quick Microsoft Entra Verified ID setup](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-tenant-quick) を参照してください。

### 2023 年 9 月

#### パブリック プレビュー - FIDO2 認証方法とWindows Hello for Businessの変更

**種類：** 機能の変更**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

2024年**1月**以降、Microsoft Entra IDは、FIDO2 セキュリティ キーの既存のサポートに加えて、コンピューターやモバイル デバイスに保存される[デバイスに結び付けられたパスキー](https://passkeys.dev/docs/reference/terms/#device-bound-passkey)を認証方法として公開プレビューでサポートします。 この更新により、ユーザーは、既に持っているデバイスを使用してフィッシングに強い認証を実行できます。

このプレビュー リリースをサポートするため、既存の FIDO2 認証方法ポリシーとエンド ユーザーのエクスペリエンスを拡張します。 組織がこのプレビューにオプトインするには、FIDO2 ポリシーで指定されているパスキー プロバイダーを許可するようキー制限を適用する必要があります。 FIDO2 キーの制限の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)を参照してください。

さらに、Windows Helloおよび FIDO2 セキュリティ キーの既存のエンド ユーザー サインイン オプションは、"Face、fingerprint、PIN、またはセキュリティ キー" で示されます。 "passkey" という用語は、セキュリティ キー、モバイル デバイス、Windows Helloなどのプラットフォーム認証子から提示されるパスキー資格情報を含む、更新されたサインイン エクスペリエンスで言及されます。

#### 一般提供 - 削除されたアプリケーションとサービス プリンシパルの回復が可能に

**種類:** 新機能**サービス カテゴリ:** エンタープライズ アプリケーション**製品の機能:** ID ライフサイクル管理

このリリースにより、アプリケーションを元のサービス プリンシパルと共に回復可能になり、広範囲にわたる再構成やコード変更が必要なくなりました ([詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq))。 これは、アプリケーションの回復方法を大幅に改善するものであり、お客様の長年のニーズに対応します。 この変更は、以下の点で役立ちます。

- **早い復旧**: システムを従来の数分の 1 の時間で復旧できるようになったため、ダウンタイムが短縮され、中断が最小限に抑えられます。
- **コストの削減**: 復旧がすばやくなったため、長時間にわたる停止や手間のかかる復旧作業に伴う運用コストを節約できます。
- **データの保持**: 以前には消失していたデータ (SMAL 構成など) が保持されるようになったため、元の通常運用へのより円滑な移行が確保されます。
- **ユーザー エクスペリエンスの向上**: 復旧にかかる時間の短縮により、アプリケーションがすばやくバックアップおよび実行されるため、ユーザー エクスペリエンスと顧客満足度が向上します。

#### パブリック プレビュー - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2023 年 9 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

プロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

- [Datadog](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datadog-provisioning-tutorial)
- [Litmos](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/litmos-provisioning-tutorial)
- [郵便配達人](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/postman-provisioning-tutorial)
- [レクニス](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/recnice-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「[Microsoft Entra ID?](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) でのアプリ プロビジョニングについて」を参照してください。

#### Windowsのウェブサインイン - 一般公開

**種類：** 機能の変更**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Windows 11 9 月の瞬間の一環として、サポートされるシナリオの数を増やし、ユーザーのセキュリティ、信頼性、パフォーマンス、および全体的なエンド ツー エンド エクスペリエンスを大幅に向上させる新しい Web Sign-In エクスペリエンスがリリースされることをお知らせします。

Web Sign-In (WSI) は、AADJ 参加済みデバイスの Windows ロック/サインイン画面の資格情報プロバイダーであり、認証に使用される Web エクスペリエンスを提供し、オペレーティング システムに認証トークンを返して、ユーザーがコンピューターのロックを解除/サインインできるようにします。

Web Sign-In は当初、さまざまな認証資格情報シナリオに使用することを目的としていました。ただし、以前は、[Simplified EDU Web Sign-In](https://learn.microsoft.com/ja-jp/education/windows/federated-sign-in?tabs=intune)、[Temporary Access Password (TAP)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) 経由の復旧フローなど、限られたシナリオでのみリリースされました。

Web サインインの基盤となるプロバイダーは、セキュリティとパフォーマンスの向上を考慮して根本から書き直されました。 このリリースでは、Web サインイン インフラストラクチャが、9 月の時点で Cloud Host Experience (CHX) WebApp から新しく作成されたサインイン Web Host (LWH) に移行されます。 このリリースでは、以前の EDU および TAP エクスペリエンスをサポートするセキュリティと信頼性が向上し、さまざまな認証方法を使用してデスクトップのロックを解除/サインインできる新しいワークフローが提供されます。

#### 一般提供 - 条件付きアクセスでのMicrosoft管理ポータルのサポート

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

条件付きアクセス ポリシーが Microsoft 管理ポータル クラウド アプリを対象とする場合、ポリシーは、次のMicrosoft管理ポータルのアプリケーション ID に対して発行されたトークンに適用されます。

- Azure portal
- エクスチェンジ管理センター
- Microsoft 365管理センター
- Microsoft 365 Defender ポータル
- Microsoft Entra 管理センター
- Microsoft Intune管理センター
- Microsoft Purview ポータル

詳細については、「[Microsoft 管理ポータル](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#microsoft-admin-portals)」を参照してください。

### 2023 年 8 月

#### 一般公開 - テナント制限 V2

**種類:** 新機能**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ID のセキュリティと保護

**テナント制限 V2 (TRv2)** がプロキシを使用した認証プレーン向けに一般公開されました。

TRv2 を使用すると、組織はデータ流出のリスクを抑えながら、安全で生産性の高い企業間コラボレーションを実現できます。 TRv2 を使用すると、外部から発行された ID を使用して、ユーザーがデバイスまたはネットワークからaccessできる外部テナントを制御し、組織、ユーザー、グループ、アプリケーションごとに詳細なaccess controlを提供できます。

TRv2 はテナント間のaccess ポリシーを使用し、認証とデータ プレーン保護の両方を提供します。 ユーザー認証中、および Exchange Online、SharePoint Online、Teams、MSGraph を使用したデータ プレーン アクセスにポリシーが適用されます。 Windows GPO とグローバル セキュア アクセスでのデータ プレーンのサポートは引き続きパブリック プレビュー段階ですが、プロキシでの認証プレーンのサポートが一般公開されました。

TRv2 のテナント制限 V2 および Global Secure Access クライアント側のタグ付けの詳細については、https://aka.ms/tenant-restrictions-enforcement を参照してください。さらに詳しくは、[全体的なテナント制限](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-universal-tenant-restrictions)をご覧ください。

#### パブリック プレビュー - テナント間アクセス設定では、カスタムロールベースアクセス制御の役割と保護アクションがサポートされます。

**種類:** 新機能**サービス カテゴリ:** B2B**製品の機能:** B2B/B2C

テナント間access設定は、組織によって定義されたカスタム ロールを使用して管理できます。 この機能を使用すると、組み込みの管理ロールを使わずに、複数テナントのアクセス設定を管理するための、独自のきめ細かいスコープを持つロールを定義できます。 [独自のカスタム ロールの作成の詳細については](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#custom-roles-for-managing-cross-tenant-access-settings)。

条件付きAccessを使用して、テナント間access設定内の特権アクションを保護できるようになりました。 たとえば、B2B collaborationの既定の設定の変更を許可する前に MFA を要求できます。 詳細については、[Protected アクション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)を参照してください。

#### 一般提供 - エンタイトルメント管理自動割り当てポリシーの追加設定

**種類：** 機能の変更**サービス カテゴリ**: エンタイトルメント管理**製品の機能:** エンタイトルメント管理

Microsoft Entra ID ガバナンス エンタイトルメント管理の自動割り当てポリシーには、3 つの新しい設定があります。 この機能により、お客様は、ポリシーで割り当てを作成しないこと、割り当てを削除しないこと、割り当ての削除を延期することを選択できます。

#### パブリック プレビュー - アクセスを失うゲストの設定

**種類：** 機能の変更**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

管理者は、エンタイトルメント管理を通じて持ち込まれたゲストが最後のaccess パッケージの割り当てを失った場合、指定した日数後に削除するように構成できます。 詳細については、「エンタイトルメント管理における外部ユーザーのアクセスの管理」に関する情報を参照してください。

#### パブリック プレビュー - リアルタイムでの厳密な場所の適用

**種類:** 新機能**サービスカテゴリー：** 継続的アクセス評価**製品機能:** アクセス制御

継続的アクセス評価を使用して、条件付きアクセス ポリシーをリアルタイムで厳密に適用します。 Microsoft Graph、Exchange Online、SharePoint Online などのサービスを有効にして、トークンの再生やその他の承認されていないアクセスに対する多層防御の一環として、許可されていない場所からのアクセス要求をブロックします。 詳細については、ブログ「[パブリック プレビュー: 継続的アクセス評価を使用してロケーション ポリシーを厳格に適用する](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/public-preview-strictly-enforce-location-policies-with/ba-p/3773133)」およびドキュメント「[継続的アクセス評価を使用してロケーション ポリシーを厳格に適用する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation-strict-enforcement)」を参照してください。

#### パブリック プレビュー - Microsoft Entra アプリケーション ギャラリーの新しいプロビジョニング コネクタ - 2023 年 8 月

**種類:** 新機能**サービス カテゴリ:** アプリ プロビジョニング**製品の機能:** サード パーティ統合

プロビジョニングをサポートする次の新しいアプリケーションがアプリ ギャラリーに追加されました。 新しく統合された次のアプリでのユーザー アカウントの作成、更新、および削除を自動化できるようになりました。

- [Airbase](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airbase-provisioning-tutorial)
- [Airtable](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airtable-provisioning-tutorial)
- [Cleanmail Swiss](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cleanmail-swiss-provisioning-tutorial)
- [Informacast](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/informacast-provisioning-tutorial)
- [Kintone](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kintone-provisioning-tutorial)
- [O'Reilly 学習プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oreilly-learning-platform-provisioning-tutorial)
- [Tailscale](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tailscale-provisioning-tutorial)
- [Tanium SSO](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tanium-sso-provisioning-tutorial)
- [Vbrick Rev Cloud](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vbrick-rev-cloud-provisioning-tutorial)
- [Xledger](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xledger-provisioning-tutorial)

自動ユーザー アカウント プロビジョニングを使用して組織のセキュリティを強化する方法の詳細については、「[Microsoft Entra ID?](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) でのアプリ プロビジョニングについて」を参照してください。

#### 一般提供開始 - パブリック クラウドと Gov クラウドで使用可能なワークロード ID の継続的アクセス評価

**種類:** 新機能**サービスカテゴリー：** 継続的アクセス評価**製品の機能:** ID のセキュリティと保護

リスク イベント、失効イベント、条件付きアクセス場所ポリシーのリアルタイム適用が、ワークロード ID に対して一般公開されました。 基幹業務 (LOB) アプリケーションのサービス プリンシパルが、Microsoft Graphへのアクセス要求で保護されるようになりました。 詳細については、「[ワークロード ID の継続的アクセス評価 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation-workload)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/whats-new-ignite-2025"} -->
## Microsoft Entra Ignite 2025: 主要なお知らせと更新プログラム - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new-ignite-2025
- Service: entra / fundamentals
- Article date: 2025-11-10
- Summary: 最新の企業向けの認証、ガバナンス、AI を利用した ID 保護に関する更新プログラムなど、最新のMicrosoft Entraリリース ノートを入手します。

Microsoft Ignite 2025 は、Microsoft Entra ポートフォリオ全体で画期的なイノベーションを提供し、MICROSOFT ENTRA AGENT IDの主要な導入により、一流の ID とアクセス管理を AI エージェントに提供します。 このリリースは、Microsoft Entra機能の現在までの最大の拡張を表し、ゼロ トラスト原則を AI ワークロードに拡張すると同時に、認証、ガバナンス、セキュリティ、アクセス管理全体で大幅な機能強化を実現します。

### リリースの特長

**Microsoft Entra エージェント ID**: 50 以上の新しい記事と包括的なプラットフォーム サポートによる革新的な AI エージェント ID とアクセス管理

**高度な認証**:セルフサービスアカウントリカバリーや高度なパスキーサポートを含む次世代のパスワードレス機能

**Enhanced Security**: Security Copilot統合と包括的なリスク管理による AI による保護

**インテリジェントガバナンス**:高度なライフサイクルワークフロー、リスクベースの承認、自動化されたコンプライアンス機能

**クラウドセキュリティ**:AIプロンプトシールドと高度なフィルタリングによる包括的なインターネットおよびプライベートアクセスの強化

**エンタープライズスケール**:アプリケーションのライフサイクル管理、デバイス識別、包括的な監視機能の強化

### Microsoft Entra エージェント ID

**AIセキュリティの未来はここにあります。** Microsoft Entra エージェント IDでは、AI エージェント向けに特別に設計された革新的な ID およびアクセス管理機能が導入されています。 この画期的なサービスは、AI エージェントに対して一流の ID コンストラクトを提供し、人間のユーザーを自律 AI システムに保護するのと同じゼロ トラスト原則をもたらします。

- [エージェントID(エージェントID)とは何ですか?](https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities) (新)
- [Microsoft Entra AI エージェントのエージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id) (新規)
- [AIのセキュリティ](https://learn.microsoft.com/ja-jp/entra/agent-id/security-for-ai-overview) (新)
- [エージェントID作成チャネル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-creation-channels) (新)
- [エージェントへのユーザーアクセス制御](https://learn.microsoft.com/ja-jp/entra/agent-id/control-user-access-agents) (新)
- Microsoft 365 (新規)
- [エージェントのサインインおよび監査ログ](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents) (新規)
- [エージェントアクセスパッケージ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages) (新)
- [継承可能な権限のブループリントを設定する](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-inheritable-permissions-blueprints) (新)
- エンド ユーザー エクスペリエンスでエージェントを管理する (新規)
- [アイデンティティのないエージェントを管理する](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agents-without-identity) (新)
- [エージェントIDの認可](https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id) (新規)
- [リファレンスレジストリの役割](https://learn.microsoft.com/ja-jp/entra/agent-id/reference-registry-roles) (新)

### Microsoft Entra エージェント プラットフォーム

**AIエージェント統合のための開発者ファーストプラットフォーム。** Microsoft Agent Identity Platform には、AI エージェントとMicrosoft Entra IDを統合するための包括的な開発者ツール、SDK、プロトコルが用意されており、特殊な OAuth フロー、トークン管理、およびエージェント レジストリ機能が備えています。

- [エージェント ID プラットフォームMicrosoft](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-agent-id-platform) (新規)
- [主要概念(](https://learn.microsoft.com/ja-jp/entra/agent-id/key-concepts) 新)
- [エージェントOAuthプロトコル(](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols) 新)
- [エージェント自律アプリの OAuth フロー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-autonomous-app-oauth-flow) (新規)
- [OAuth フローの代理エージェント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-on-behalf-of-oauth-flow) (新)
- [エージェントユーザーOAuthフロー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-user-oauth-flow) (新)
- [エージェント設計](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint) 図(新)
- [エージェントのユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users) (新規)
- [エージェント識別子](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities) (新)
- [エージェントサービスプリンシパル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-service-principals) (新)
- [エージェントトークンクレーム(](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-token-claims) 新規)
- [エージェントのメタデータと発見可能性](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-metadata-discoverability) (新設)
- [エージェントオーナー、スポンサー、マネージャー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers) (新規)
- [エージェントリスト](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-lists) (新規)
- [ブループリントを作成](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint) (新規)
- [エージェント識別子の作成および削除](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities) (新規)
- [エージェントブループリント管理](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-blueprint) (新)
- 自律エージェント要求トークン (新規)
- 自律エージェント要求エージェントのユーザー トークン (新規)
- Entra 管理者からの自律エージェント要求承認 (新規)
- [ユーザーを認証し、対話型エージェントのトークンを取得する](https://learn.microsoft.com/ja-jp/entra/agent-id/interactive-agent-authentication-authorization-flow) (新規)
- [Call API: Azure サービス](https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-azure-services) (新規)
- [Call API:カスタム](https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-custom) (新)
- [Call API: Microsoft Graph](https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-microsoft-graph) (新規)
- [Microsoft Entra ID Auth SDK (サイドカー)](https://learn.microsoft.com/ja-jp/entra/agent-id/microsoft-entra-sdk-for-agent-identities) (新規)

### セキュリティコパイロット + Microsoft Entra

**AI駆動のアイデンティティとアクセス管理。** 強化されたSecurity Copilot統合により、高度な AI 支援がMicrosoft Entra運用にもたらされ、インテリジェントな分析情報、自動化された最適化、ID プラットフォーム全体にわたる包括的なリスク管理機能が提供されます。

- Security Copilot (更新)
- [条件付きアクセスエージェント最適化](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization) (更新版)
- [Entra エンタープライズ ユーザー管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management) (更新)
- [Entraガバナンス最適化](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization) (更新版)
- [Entra ID ガバナンス シナリオ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios) (更新)
- [Entra ID シナリオ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios) (更新)
- [Entraインターネットアクセスおよびプライベートアクセスシナリオ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-internet-access-private-access-scenarios) (更新版)
- [Entraでのインシデント調査(](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-incident) 更新版)
- [Entraでリスクのあるアプリを調査する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps) (更新)
- [Entraのライフサイクルワークフロー(](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows) 更新)
- [Entraの監視と運用](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations) (更新版)
- [Entraのセキュリティとアクセス制御](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control) (更新版)
- [Entraセキュリティシナリオ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-scenarios) (更新)

### Microsoft Entra スイート

#### Microsoft Entra ID ガバナンス

**AI統合による次世代のアイデンティティガバナンス。** 強化されたライフサイクルワークフロー、インテリジェントなリスクベースの承認、効率的なアクセスパッケージ管理により、エンタープライズ規模での包括的なアイデンティティガバナンスが実現します。

- [エージェントID(エージェントID)とは何ですか?](https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities) (新)
- [Microsoft Entra AI エージェントのエージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id) (新規)
- [権利管理:ID Protection 承認の設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-id-protection-approvals) (新)
- [権利管理:インサイダーリスク管理承認の設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-insider-risk-management-approvals) (新)
- [ワークフロー感度ラベル(](https://learn.microsoft.com/ja-jp/entra/id-governance/workflow-sensitivity-labels) 新)
- [カタログアクセスレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/catalog-access-reviews) (新規)
- [カタログアクセスレビューにおける提供リソースとしてのカスタムデータ](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-data-resource-access-reviews)
- [権利管理:アクセスパッケージの適格割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-eligible) (更新)
- [権利管理:動的承認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-dynamic-approval) (更新版)
- [権利管理:認証済みID設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-verified-id-settings) (更新済み)
- [ライフサイクルワークフロー:非アクティブなユーザー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-inactive-users) (更新済み)
- [ライフサイクルワークフロータスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks) (更新済み)
- [再処理ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/reprocess-workflow) (更新版)
- [PIM:ユーザーに役割を追加](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user) (更新)

#### マイクロソフト エントラ インターネット アクセス

**高度なAI搭載クラウドセキュリティ。** 新しいAIプロンプトシールド、包括的なデータ損失保護、ネットワークコンテンツフィルタリング、クラウドファイアウォール機能により、現代のワークロードに対して最先端の保護を提供します。

- [AI プロンプトインジェクション保護](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-ai-prompt-injection-protection) (新規)
- [クラウドファイアウォールの設定](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-cloud-firewall) (新)
- [完全なデータ損失保護](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-full-data-loss-protection) (新)
- [ネットワークコンテンツフィルタリング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-network-content-filtering) (新)
- [リモートネットワーク接続で広告されるIPアドレス](https://learn.microsoft.com/ja-jp/entra/global-secure-access/reference-ip-addresses-advertised-by-remote-network-connectivity) (新規)
- [トランスポート層セキュリティ検査エラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-transport-layer-security) (新)
- [Netskope統合コンセプト](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-netskope-integration) (更新版)
- [トランスポート層セキュリティ検査とは](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-transport-layer-security) (更新)
- [トラフィックプロファイルをリモートネットワークに割り当てる](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-assign-traffic-profile-to-remote-network) (更新済み)
- [リモートネットワークの作成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-create-remote-networks) (更新)
- [リモートネットワーク管理](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-remote-networks) (更新版)
- [トランスポート層セキュリティ検査設定の設定](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-transport-layer-security-settings) (更新済み)
- [トランスポート層セキュリティ検査ポリシーの設定](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-transport-layer-security) (更新済み)
- [現在の既知の制限](https://learn.microsoft.com/ja-jp/entra/global-secure-access/reference-current-known-limitations) (更新版)

#### マイクロソフト エントラ プライベート アクセス (Microsoft Entra Private Access)

**プライベートリソースの安全なアクセス強化。** 改善されたリモートネットワーク接続、高度なトラフィックプロファイリング、そして効率的な構成により、エンタープライズグレードのプライベートアクセス機能が実現します。

- [トラフィックプロファイルをリモートネットワークに割り当てる](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-assign-traffic-profile-to-remote-network) (更新済み)
- [リモートネットワークの作成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-create-remote-networks) (更新)
- [リモートネットワーク管理](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-remote-networks) (更新版)
- [インテリジェントローカルアクセスを有効にする](https://learn.microsoft.com/ja-jp/entra/global-secure-access/enable-intelligent-local-access)

#### Microsoft Entra 確認済み ID

**検証エコシステムの拡大。** 新たな統合ガイダンスと強化されたパートナーエコシステムにより、現代の認証シナリオに適した包括的なデジタルID認証機能が提供されます。

- [統合ガイダンス](https://learn.microsoft.com/ja-jp/entra/verified-id/integration-guidance) (新)
- [本人確認パートナー](https://learn.microsoft.com/ja-jp/entra/verified-id/idv-partners) (更新版)

#### Microsoft Entra ID 保護（マイクロソフト エントラ ID 保護）

**AIエージェントのリスク保護と報告の強化。** AIエージェント向けの拡張リスク検出機能、包括的なリスク管理エージェント、高度なユーザーリスク報告により、次世代の身元保護が実現します。

- [リスクエージェントのコンセプト](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents) (新)
- [リスクの高いユーザー報告コンセプト](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-user-report) (新)
- [アイデンティティリスク管理エージェント:始める](https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-get-started) (新規)
- [アイデンティティリスク管理エージェント:リスクの高いユーザーレポート](https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-risky-user-report) (新設)
- [アイデンティティリスク管理エージェント:設定](https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-settings) (新)
- [アイデンティティ保護ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies) (更新版)
- [アイデンティティ保護ユーザー体験](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience) (更新)
- [リスク検出タイプ](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-detection-types) (更新)
- [リスクレポート](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports) (更新)
- [リスクポリシーの設定](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies) (更新済み)
- [アイデンティティ保護の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) (更新版)

#### Microsoft Entra 外部 ID

**サードパーティパートナーソリューションによる組み込みの保護。** 新しいパートナー統合は、エッジセキュリティ、サインアップ不正防止、統一監視など、アイデンティティライフサイクル全体にわたる包括的な保護を提供します。 顧客は固有のユーザー名やエイリアスを使ってサインインを設定できます。

- [Akamai WAF統合](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-akamai-integration) (新)
- [Cloudflare WAF統合](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-waf-integration) (更新版)
- [Arkose LabsとHUMAN Security詐欺防止](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-integrate-fraud-protection) (新規)
- [Azure MonitorおよびMicrosoft Sentinel統合](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor) (更新)
- [偽名でサインイン(](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias) 新規)

### Microsoft Entra ID

#### 条件付きアクセス

**革新的なAIエージェント支援と強化された政策機能。** 条件付きアクセスは、ゼロ トラスト原則を AI エージェントに拡張すると同時に、高度なポリシー管理、インテリジェントな最適化、およびすべての ID の種類にわたる包括的なアクセス制御を提供します。

- [条件付きアクセスとエージェント識別](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id) (新)
- [高リスクエージェント識別をブロック(](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-agent-block-high-risk) 新)
- [条件付きアクセス:クラウドアプリ](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps) (更新版)
- [条件付きアクセス:条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions) (更新版)
- [条件付きアクセス:グラント](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) (更新版)
- [共通条件付きアクセスポリシーテンプレート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common) (更新版)
- [条件付きアクセス:ユーザー、グループ、エージェント、ワークロードの識別(](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups) 更新済み)
- [マネージドポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/managed-policies) (更新版)
- [条件付きアクセス概要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) (更新)
- [リスクベースのユーザーポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user) (更新版)
- [What-ifツール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool) (更新版)

#### Authentication

**次世代のパスワードレス認証。** 革新的なセルフサービスアカウント復旧、高度なパスキー機能、プラットフォーム認証情報サポート、包括的な認証方法管理が、安全な認証の未来を実現します。

- [アカウント回復概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-account-recovery-overview) (新規)
- [パスキー(FIDO2)認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) (新)
- [macOSのPlatform credential](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-platform-credential-for-macos) (新)
- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-windows-hello) (新規)
- [口座回収コスト節約推定器](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-cost-savings-estimator) (新)
- [アカウント復旧を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-enable) (新規)
- [ユーザー向けのアカウント回復](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-for-users) (新)
- [パスキープロファイル(](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2) 新)
- [同期パスキー(](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2) 新)
- [セルフサービスアカウントリカバリー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/self-service-account-recovery) (新)
- [Passkey に関する FAQ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/passkey-faq) (新規)
- [Microsoft Authenticator app](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app) (更新)
- [認証方法の管理](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage) (更新版)
- [認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication) (更新版)
- [電話認証オプション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options) (更新)
- [FIDO2ハードウェアベンダーの考慮](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-hardware-vendor) 事項(更新)
- [フィッシング耐性のあるパスワードレス認証を展開](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication) (更新)
- [パスキーを有効にする(FIDO2)(](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2) 更新)
- [ペルソナベースのフィッシング耐性、パスワードレス認証を計画(](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-persona-phishing-resistant-passwordless-authentication) 更新)
- [フィッシング耐性のあるパスワードレス認証の前提条件を計画](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication) (更新)
- [セキュリティキーでパスキーを登録(](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-with-security-key) 更新済み)
- [パスキー登録(](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey) 更新済み)
- [パスキーでサインイン(](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-sign-in-passkey) 更新済み)
- [MFAの始め](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted) 方(更新版)
- [Kerberos認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/kerberos) (更新)
- [認証概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication) (更新版)
- [認証強度のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-authentication-strengths) (更新版)

#### ロールベースのAccess Control

**包括的なAIエージェント管理。** エージェントID管理の新しい専門的な役割は、AIエージェントの識別、設計図、レジストリ操作に対する細かな管理管理を提供します。

- [エージェントID管理者の役割](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator) (新)
- [エージェントID開発者の役割](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-developer) (新設)
- [エージェントレジストリ管理者の役割](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-registry-administrator) (新設)
- [ポータルでロール管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal) (更新)
- [権限の参照](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) (更新)

#### モニタリングと健康

**強化されたアイデンティティ監視と運用洞察。** 監視機能の向上、包括的な監査ログ、高度な健康分析が、現代のアイデンティティ運用とコンプライアンス要件をサポートします。

- [アイデンティティ監視と健康状態](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs) (更新版)
- [サインインコンセプト](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins) (更新版)
- [フィルターログのカスタマイズ(](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-customize-filter-logs) 更新済み)
- [健康状態のモニタリング概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health) (更新版)

### Microsoft ID プラットフォーム

**強化されたエンタープライズアプリケーション管理。** 包括的なAI搭載のアプリケーションライフサイクル管理、インテリジェントなアプリ所有者の識別、高度なリスク評価により、次世代のアプリケーションガバナンス機能が実現します。

- エージェント アプリのライフサイクル: 検出とオンボード (新規)
- エージェント アプリのライフサイクル管理 (新規)
- エージェント アプリのライフサイクル: 修復計画 (新規)
- エージェント: アプリの所有者に問い合わせる (新規)
- エージェント: 危険なアプリを特定して優先順位を付ける (新規)
- [ユーザーまたはグループアクセスの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) (更新済み)
- [ユーザーのサインインを無効に](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal) する(更新済み)
- [アプリの同意ポリシー管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-app-consent-policies) (更新)
- [プランアプリケーション統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-an-application-integration) (更新版)
- [ガバナンとモニターチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-govern-monitor) (更新済み)
- [アプリケーション管理概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management) (更新)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/whats-new-overview"} -->
## 新着情報 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new-overview
- Service: entra / fundamentals
- Article date: 2024-11-13
- Summary: Microsoft Entra 管理センターの最新情報エクスペリエンスの概要。

### 概要

Microsoft Entra は、新しい機能の提供、既存の機能の強化、欠陥の修正、顧客からのフィードバックへの対応のために定期的に更新されます。 これらの開発を最新の状態に保つ最善の方法は、**Microsoft Entra 管理センター**[の新機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/entra-admin-center)にアクセスすることです。

**[最新情報]** は、Microsoft Entra ロードマップと変更の発表を統合的に表示する情報ハブです。 これにより、管理者は Microsoft Entra 製品ファミリ全体のリリースと変更を一元的に追跡、学習、計画できるようになります。

以降のセクションでは、**[最新情報]** エクスペリエンスの特徴と機能について説明します。

### 新機能の探索

#### ハイライト

**[ハイライト]** タブには、製品の重要なリリースと影響の大きい変更がまとめられています。 [ハイライト] タブでは、アナウンスまたはリリースを選択して詳細を表示したり、詳細情報のドキュメントへのリンクにアクセスしたりできます。

[Image: Microsoft Entra 管理センターの Microsoft Entra 新機能ハイライト エクスペリエンスのスクリーンショット。]

#### ロードマップ

**[ロードマップ]** タブには、パブリック プレビューと最近の一般公開リリースの詳細が並べ替え可能な表形式で一覧表示されます。 この表からリリースを選択すると、リリース **の詳細**を表示できます。これには、概要と詳細情報へのリンクが含まれています。

[Image: Microsoft Entra 管理センターの Microsoft Entra 新機能ロードマップ エクスペリエンスのスクリーンショット。]

リリースを見つけるには、次のコントロールを使用してテーブル ビューをカスタマイズできます。

- **[検索]**: キーワードを入力して特定のリリースを見つけます。
- **[フィルターの追加]**: **[リリースの種類]** または **[状態]** でフィルター処理します。
- **[カテゴリ]**: 製品や機能のカテゴリでフィルター処理します (例: *[ユーザー認証]*、*[ID ガバナンス]*)
- **[リリース日]**: 日付範囲でフィルター処理します。
- **[ビューの管理]**: 列を削除または追加します。

##### ロードマップの列の説明

次に示すのは、ロードマップ テーブルの並べ替え可能な列の説明です。

| 列 | 説明 |
| --- | --- |
| **タイトル** | 製品または機能の簡単な説明。 |
| **カテゴリ** | 製品または機能の ID とネットワーク アクセス カテゴリ (例: *[ID ガバナンス]*、*[ID のセキュリティと保護]*)。 |
| **サービス** | 製品または機能の Microsoft Entra サービス (例: *[エンタイトルメント管理]*、*[条件付きアクセス]*)。 |
| **リリースの種類** | リリースの[ライフサイクル フェーズ](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing-preview-info):<br>- *[パブリック プレビュー]*: 必要なライセンスをお持ちのすべてのお客様が利用できます。 このリリースには限定的な顧客サポートが含まれる場合があり、標準のサービス レベル契約は適用されません。<br>- *[GA]* (一般提供): ライセンスをお持ちのすべてのお客様が利用できます。 このリリースは、すべての Microsoft サポート チャネルでサポートされています。 |
| **リリース日** | リリースが利用可能になる日付。 |
| **状態** | リリースが *[利用可能]* であるか、*[近日公開予定]* であるかを示します。 |

#### 変更のお知らせ

**[変更のお知らせ]** タブには、並べ替え可能な表形式で、既存の製品と機能に対する今後の変更が一覧表示されます。 この表から、変更のお知らせを選択して変更の **詳細**を表示できます。これには、変更内容の概要と詳細を確認するためのリンクが含まれています。

[Image: Microsoft Entra 管理センターの Microsoft Entra の新機能変更のお知らせエクスペリエンスのスクリーンショット。]

変更のお知らせを見つけるには、次のコントロールを使用してテーブル ビューをカスタマイズできます。

- **[検索]**: キーワードを入力して特定のリリースを見つけます。
- **[フィルターの追加]**: **[必要な操作]** または **[種類の変更]** でフィルター処理します。
- **[サービス]**: 製品または機能のサービスでフィルター処理します (例: *[エンタイトルメント管理]*、*[条件付きアクセス]*)。
- **[リリース日]**: 日付範囲でフィルター処理します。
- **[ビューの管理]**: 列を削除または追加します。

##### ロードマップの列の説明

次に示すのは、ロードマップ テーブルの並べ替え可能な列の説明です。

| 列 | 説明 |
| --- | --- |
| **タイトル** | 製品または機能の簡単な説明。 |
| **サービス** | 製品または機能の Microsoft Entra サービス (例: *[エンタイトルメント管理]*、*[条件付きアクセス]*)。 |
| **変更の種類** | 変更の範囲:<br>- *[UX の変更]*: アクションを必要としないユーザー エクスペリエンスの変更。<br>- *[機能の変更]*: アクションが必要になる可能性がある既存の機能に対する変更。<br>- *[重要な変更]* 何も対策を講じなければユーザー エクスペリエンスが損なわれると予想される変更。<br>- *[非推奨]*: その製品または機能は、新しいお客様はもう利用できず、提供終了が予定されています。 存のお客様は、非推奨の製品または機能を引き続き利用でき、サポートを受けることができます。<br>- *[廃止]*: その製品または機能は利用できなくなっており、サポートもされていません。<br>- サポート終了: 製品または機能は、既存のお客様ではサポートされなくなりました。 |
| **発表日** | お知らせの日付。 |
| **目標とする日** | 変更のリリース日。 |
| **必要な操作** | 変更に対してユーザーがアクションを実行する必要があるかどうかを示します。 |

### FAQ の新着情報

#### Microsoft 365 と Azure のロードマップが既に存在する場合に必要な新機能はなぜですか?

すべての Microsoft Entra 製品が Microsoft 365 と Azure の一部であるわけではありません (たとえば、Microsoft Entra External ID)。 新機能により、すべての Microsoft Entra 製品の新機能と変更点に関する透明性が一元的に確保されます。

#### 新機能は、Microsoft 365 ロードマップのように公開されていますか?

いいえ。最新情報にアクセスするには、Microsoft Entra 管理センターにサインインする必要があります。 サインイン要件により、よりパーソナライズされたエクスペリエンスが可能になり、今後の「新機能」バージョンでは各テナント向けに特化されます。

#### Microsoft Entra の更新プログラムを使用して、プロセスを自動化できますか?

はい。将来的には、[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/identity-network-access-overview) を使用して更新プログラムを取得し (現在ベータ版)、プロセスを自動化できるようになります。 たとえば、機能ライフサイクル イベントへの応答を自動化したり、製品ライフサイクル管理システムと統合したりできます。

#### 既存の RSS フィードを引き続き使用して、最新情報の公開リリース ノートを表示できますか?

はい。既存の RSS フィードと[リリース ノート](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)は引き続き利用できます。

#### Microsoft 365 または Azure portal で最新情報にアクセスできますか?

いいえ。新機能は、Microsoft Entra 管理センターでのみ使用できます。

#### この機能に関するフィードバックを提供するにはどうすればよいですか?

フィードバックを提供するには、**[ロードマップ]** タブまたは **[変更のお知らせ]** タブに移動して、**[フィードバックがある場合]** を選択してください。

#### 新機能にアクセスするには、特定の Microsoft Entra ロールが必要ですか?

いいえ。すべての Microsoft Entra ID ロールが新機能にアクセスできます。

#### 新機能にアクセスするためのライセンス要件はありますか?

いいえ。新機能は Microsoft Entra ID Free を含む Microsoft Entra のすべてのお客様が利用できます。

#### テナントのゲスト ユーザーは新機能にアクセスできますか?

はい。新機能はビジネス ゲストもアクセスできます。

#### 政府機関向けクラウドの顧客は新機能を利用できますか?

現在、新機能はパブリック クラウドのお客様のみが利用できます。 しかし、今後、政府のクラウドに新しいエクスペリエンスを提供する計画があります。

#### 新機能には、すべての Microsoft Entra 製品が含まれていますか?

はい。新機能では、Microsoft Entra 製品ファミリ全体のリリースと変更の発表を統合的に表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/zero-trust-ai"} -->
## セキュリティを強化するためにMicrosoft Entra エージェント ID を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-ai
- Service: entra / fundamentals
- Article date: 2026-05-18
- Summary: Microsoft Entra ゼロ トラスト評価を使用してセキュリティ体制を改善し、AI エージェントとワークロードをセキュリティで保護します。

AI エージェントは、メール、ファイル、基幹業務 API、ダウンストリーム エージェントなど、すべての操作で組織リソースのアクセス トークンを取得します。 対話型ユーザーとは異なり、エージェントは通常、ユーザー MFA、マネージド デバイスの状態、場所のコンテキストなど、同じサインイン シグナルを表示しません。 そのため、AI コントロール プレーンのセキュリティ保護は、ゼロ トラスト体験に不可欠です。

エージェントの問題は、通常、次の 3 つの領域に表示されます。

- **認証とポリシーの不一致**: ユーザー向けに設計されたポリシーは、エージェント固有のトークン パターンと実行モデルを見逃す可能性があります。
- **過剰なアクセス許可**: エージェントには、Microsoft Graph やカスタム API にまたがって広範な API アクセス許可が付与されがちであり、その結果、影響範囲が拡大します。
- **ライフサイクルとアカウンタビリティのギャップ**: 孤立したエージェント ID、所有者またはスポンサーの不足、古い資格情報によって永続的なリスクが生じます。

この柱の一部である推奨事項とゼロ トラストチェックは、未承認の AI アクセスのリスクを軽減するのに役立ちます。 テーマには、エージェント エンドポイントでの Entra 認証の適用、エージェント ID への条件付きアクセス ポリシーの適用、ライフサイクル ガバナンス制御の割り当て、AI 管理者ロールに責任あるプリンシパルの確保が含まれます。

### AI のゼロ トラストセキュリティに関する推奨事項

#### エージェントと対話するためにMicrosoft Entra ID認証を要求する

Microsoft Entraユーザー認証を必要としないエージェント エンドポイントは、認証されていないが、テナントの AI 資産内の到達可能なサーフェスです。 脅威アクターは、パブリック エージェントの URL を検出したり、漏洩したCopilot Studioリンクを使用したり、Foundry プロジェクト エンドポイントを列挙したりして、このエンドポイントを見つける可能性があります。 その後、アクターは ID を提示せずにエージェントと対話し、エージェントの既存の許可を使用して、グラウンド データ、接続されたツール、ダウンストリーム API にアクセスできます。

Microsoft Entraに達しない呼び出しは、条件付きアクセス ポリシーでは評価できません。また、計算するサインイン リスクがなく、監査レコードを特定のユーザーに関連付けることはできません。 この情報が不足しているため、調査と修復が困難になります。 Microsoft Entraユーザー認証を適用するランタイム動作は、各エージェントのホスト (Copilot Studio、Microsoft Copilot、Microsoft Foundry、カスタム コード、またはMicrosoft以外のプラットフォーム) 内に存在するため、ディレクトリから直接監視することはできません。 *観測可能*なのは、エージェントとその呼び出し元が実際に Microsoft Entra を経由するたびに Microsoft Entra のサインイン ログに残る痕跡です。

このチェックでは、エージェント ID ごとに過去 30 日間のサインイン アクティビティが検査され、検出された最も強力な証拠によってエージェントが分類されます。サブジェクトが実際のユーザーである非対話型エージェント サインインが最も強い肯定的なシグナルです。 エージェントのブループリントへの対話型ユーザー サインインは、ユーザーが Microsoft Entra を通じてエージェントにアクセスするという肯定的な証拠でもあります。 監視期間中にどちらのシグナルも存在しない場合、プラットフォームはエージェントがユーザー認証Microsoft Entra適用することを確認できず、責任ある所有者はエージェントのホスト構成を直接検証する必要があります。

**修復アクション**

- [対話型エージェントでユーザーを認証する](https://learn.microsoft.com/ja-jp/entra/agent-id/interactive-agent-authentication-authorization-flow)
- [対話型エージェントの委任されたユーザー承認を要求する](https://learn.microsoft.com/ja-jp/entra/agent-id/grant-agent-access-microsoft-365)
- [Microsoft Entra エージェント ID のエージェント ユーザー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users)
- [Microsoft Entra エージェント IDの概要](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id)
- [Microsoft Entra ID のログインログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)

#### 条件付きアクセス ポリシーは、エージェント ID とエージェントのユーザー アカウントの両方を対象とします

組織が AI エージェントをデプロイすると、これらのエージェントは、対話型のユーザー セッションとデバイス、場所、または MFA のシグナルを使用せずに、すべての対話で組織のリソースにアクセスするためのアクセス トークンを取得します。従来の条件付きアクセスでは、人間のユーザーに対する信頼の決定を行うために使用されます。 Microsoft Entra エージェント IDでは、次の 2 つの異なる ID の種類が導入されています。

- [agent ID](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities): AI エージェントに固有の ID と認証機能を提供するMicrosoft Entra ID内の ID アカウント。
- [エージェントのユーザー アカウント: エージェントがユーザー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users) オブジェクトを必要とするシステムにアクセスする必要がある場合に、エージェント ID と 1:1 をペアにする省略可能なアカウント。

条件付きアクセスでは、両方のエージェント ID オブジェクトが個別のプリンシパルの種類として扱われます。 そのため、エージェント ID を対象とするポリシーは、エージェントのユーザー アカウントをターゲットにすることはできません。その逆も同様です。 承認済みでない限りブロックすることを強制する条件付きアクセス ポリシーが少なくとも 1 つもないままエージェント ワークロードを有効にしているテナントには、自律型 AI によるアクセスに対する強制境界がありません。 エージェント ID またはエージェントのユーザー アカウントからのすべてのトークン要求は、既定で許可されます。 脅威アクターは、単一のエージェント ID またはそのバッキング エージェントのユーザー アカウントを侵害し、ID が到達できるリソースをピボットするときに、この種の障害モードを利用しようとします。

**修復アクション**

- [エージェント ID の条件付きアクセス (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)
- [エージェント向けの ID 保護シグナル](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)
- [条件付きアクセスでアプリケーションをフィルター処理する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-filter-for-applications)
- [Microsoft Entra ID のカスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)

#### リスクベースの条件付きアクセスによって、危険なエージェント ID がブロックされる

組織がMicrosoft Entraで AI エージェントを有効にすると、[agent ID](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities) は、対話型のユーザー セッション、デバイス、場所、または MFA シグナルなしで、トークンにアクセスして組織のリソースにアクセスできます。これは、従来の条件付きアクセスが人間のユーザーに対する信頼の決定を行うために使用するシグナルです。 エージェントのMicrosoft Entra ID 保護は、各エージェントの動作を継続的に評価し、次のようなシグナルによって駆動されるリスク レベルを出力します。

- 未知のリソース アクセス (エージェントは確立されたパターンの外部に到達します)
- サインインの急増 (トークンの再生または自動化の不正使用)
- 失敗したアクセスプローブ (攻撃者がエージェントの資格情報を使用してリソースを列挙している)
- 委任された認証中に危険なユーザーによるサインイン (攻撃者が侵害されたユーザー アカウントを利用している)
- 管理者が確認した侵害 (セキュリティ管理者がエージェントに侵害済みとして手動でフラグを設定する)

リスク レベルを消費し、トークンの発行をブロックする条件付きアクセス ポリシーがない場合、プラットフォームはエージェントが高リスクであることを記録しながら、敵対者が必要とするトークンを引き続き作成できます。 ログには "侵害" と表示されますが、リソースには "はい" と表示されます。リスクの高いエージェント ID をブロックするリスクベースの条件付きアクセス ポリシーは、検出と適用の間のこのギャップを埋めるために必要です。

**修復アクション**

- [エージェントの ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)
- [エージェント ID の条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)

#### エージェント ID のカスタム セキュリティ属性が存在する

カスタム セキュリティ属性は、エージェント ID を大規模に区別するための条件付きアクセスの主要なメカニズムです。 これらがない場合、条件付きアクセス ポリシーは、すべてのエージェント ID、オブジェクト ID 別の個々のエージェント、またはブループリントでグループ化されたエージェントのみを対象にすることができます。 エージェント群が増加するにつれて、これらの仕組みでは対応しきれなくなります。 カスタム セキュリティ属性を使用すると、部門、承認状態、秘密度レベル、または組織で定義された分類によってエージェントをフィルター処理する機能がロック解除されます。 たとえば、`AgentAttributes`属性 (`AgentApprovalStatus`、`New`、`In_Review`、`HR_Approved`、`Finance_Approved`などの値) を持つ`IT_Approved`と呼ばれる属性セットでは、分類に基づいてエージェントとリソースを照合する属性ベースの条件付きアクセス ポリシーが有効になります。

エージェント ID にカスタム セキュリティ属性がない場合、組織は属性ベースの条件付きアクセス ポリシーを確実に適用できません。 新しくプロビジョニングされたエージェント ID または未分類のエージェント ID は、それらを区別するためのメタデータがないため、完全に検証されたエージェント ID と同じアクセス制御を受け取ります。 悪意のあるエージェント ID を侵害または登録する脅威アクターは、分類ベースのポリシーの適用の対象とされずにアクセスを取得します。 カスタム セキュリティ属性を割り当てると、すべてのエージェント ID に、条件付きアクセスクエリと監査クエリで使用できるマシン読み取り可能な分類メタデータが確実に含まれるため、このギャップが埋められます。

属性フィルターを使用せずにすべてのエージェント ID を対象とするポリシーでもベースライン保護が提供される場合がありますが、承認されたエージェントと未承認のエージェントを区別することはできません。 属性ベースのコントロールを実装するには、次のものが必要です。

- 属性分類の定義
- アプリケーション所有者との調整
- プロビジョニング時に新しいエージェントにタグを付ける運用プロセスを確立する

**修復アクション**

- [エージェント ID の条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)
- [Microsoft Entra ID でカスタム セキュリティ属性を追加または非アクティブ化する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)
- [カスタム セキュリティ属性をアプリケーションに割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/custom-security-attributes-apps)
- Microsoft Graph
- [条件付きアクセスでアプリケーションをフィルター処理する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-filter-for-applications)

#### エージェント ID スポンサーの ID ガバナンスが構成されている

Microsoft Entra エージェント IDでは、すべての[agent ID](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities) および [agent ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint)に少なくとも 1 つのスポンサーが必要です。 スポンサーは、エージェントが不要になった時期の決定、アクセスの期限切れ時の拡張機能の承認、インシデント発生時の中断の承認など、エージェントのライフサイクルに関するビジネス アカウンタビリティを保持する、人間のユーザーまたはサポートされているグループです。 スポンサーは、技術的な運用とインシデント対応を担当する人間のユーザーを指定する所有者とは異なります。

スポンサー シップは、ID ガバナンスのエントリ ポイントです。

- ライフサイクル ワークフローでは、スポンサーから離れる通知をマネージャーにルーティングできます
- アクセス パッケージの有効期限のエスカレーションがスポンサーに送信される
- エンタイトルメント管理の承認者は、継続的なアクセスを検証するためにスポンサー関係に依存します。

スポンサーが割り当てられないと、エージェント ID を適切に管理できません。 スポンサーなしで存在するエージェント ID は、ガバナンスからは見えません。 エージェント ID を対象とするアクセス パッケージがない場合、エージェントが受け取るすべてのアクセス許可は、エンタイトルメント管理制御ループの外部にある `appRoleAssignment`、 `oauth2PermissionGrant`、グループ メンバーシップ、またはディレクトリ ロールの割り当てを通じて直接付与する必要があります。 直接付与には、組み込みの有効期限がなく、承認者ループも、アクセス レビュー スケジュールもありません。 エージェント スポンサー タスクを含むライフサイクル ワークフローがない場合、スポンサー関係は静的ディレクトリ レコードであり、スポンサーが移動または退出しても自動化されません。 資格情報の盗難、ブループリントの侵害、または傍受するガバナンス パイプラインが存在しない悪意のあるアクセス パッケージ要求を通じて、管理されていないエージェントを侵害する脅威アクターは、アクセス許可がレビューされなかった ID に対して動作します。 また、このチェックでは、少なくとも 1 つのアクセス パッケージ割り当てポリシーがすべてのディレクトリ エージェント ID を対象としていることを確認します。

**修復アクション**

- [Microsoft Entra エージェント ID の管理関係](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers)
- [エージェント ID の管理](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)
- [ライフサイクル ワークフローのエージェント ID スポンサー タスク](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks)
- [エージェント ID のアクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages)
- [エンタイトルメント管理でアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)

#### エージェント ID とブループリント プリンシパルには技術所有者が割り当てられ、無効なエージェントはディレクトリに残っていません

Microsoft Entra エージェント IDでは、[agent ID](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities) と [agent ID ブループリント プリンシパル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint) という 2 種類の ID が導入されました。 これらの ID オブジェクトはサービス プリンシパルから派生するため、所有権、ライフサイクル管理、クリーンアップに関する要件とベスト プラクティスは、任意のサービス プリンシパルと同じです。 ブループリント プリンシパルは、エージェント ID の作成元となるプロビジョニング サーフェイスであり、子エージェントに伝達される許可を保持できます。 これらのオブジェクトの所有者を指定すると、エージェント ID 管理の 2 つの重要な領域に役立ちます。

- リスクは責任ある当事者によって包含および調査される
- 無効なオブジェクトで休眠特権が生じることはありません

各エージェント ID オブジェクトの所有者関係は、技術的な運用とインシデント対応を担当する人間のユーザーを指定します。これは、ライフサイクルとアクセスの決定に関するビジネス アカウンタビリティを担うスポンサー関係とは異なります。 そのため、ID Protection がリスクまたは異常なリソース アクセス パターンとしてエージェント ID にフラグを設定すると、セキュリティ運用チームは、封じ込めと調査のために責任のある当事者にルーティングできます。 所有者が指定されていない場合、(資格情報の盗難、ブループリントの悪用、または悪意のある委任された同意によって) 所有者のないエージェントまたはブループリント プリンシパルを侵害した脅威アクターは、直ちに封じ込めのために指定された人間がいないプリンシパルに対して動作します。 この問題により、ドウェル時間が分単位から次の手動ディレクトリ監査サイクルに延長される可能性があります。

エージェント ID またはエージェント ID ブループリントが無効になっている場合、新しいトークンを取得できないため、リソースにアクセスできません。 オブジェクトは引き続きディレクトリに存在するため、アプリ ロールの割り当て、グループ メンバーシップ、OAuth2 アクセス許可は保持されます。 管理エラーまたはディレクトリ書き込みアクセス権を持つ脅威アクターがオブジェクトを再度有効にすると、すべてのアクセス許可が再び有効にされずに戻ります。 無効化されたブループリントのプリンシパルを再度有効化すると、すべての子エージェント ID のプロビジョニング サーフィスも復元されます。 所有者なしのオブジェクトと古い無効なオブジェクトの組み合わせにより、適切な所有権とクリーンアップが防止されるように設計された、永続的な特権の蓄積パターンが作成されます。

**修復アクション**

- [Microsoft Entra エージェント ID の管理関係](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers)
- [組織内のエージェント ID を管理する](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-identities-admin)
- [エージェント ID の管理](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)
- [エンド ユーザー エクスペリエンスでエージェントを管理する](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-identities-end-user)

#### AI 管理ロールにプリンシパルが割り当てられている

AI コントロール プレーンサーフェスには、Microsoft 365、Microsoft Power Platform、Microsoft SharePoint、およびMicrosoft Security スタックのすべてのレイヤーが含まれます。 Microsoft Entra IDは、このサーフェス全体の ID コントロール プレーンであり、その管理スコープでは、エージェント ID、Microsoft Copilot管理者設定、AI の条件付きアクセス、Copilot Studio環境、AI の接地ソース、AI 体制シグナル、AI の検出と応答を管理します。 これらの AI 機能を管理する管理ロールに人間が割り当てられていない場合は、AI サーフェスのそのスライスに対して説明責任を負うオペレーターは存在しません。 このギャップは、次の意味を持つ可能性があります。

- エージェント ID が監視またはレビューされない
- 管理者設定のドリフト
- AI 固有の検出には、調査または調整する所有者がいません
- AI 資産の進化に伴い、AI のネットワーク制御ポリシーは調整されません
- AI 関連のエスカレーションには、指定されたレスポンダーがありません

脅威アクターは、AI コントロール プレーンを直接ターゲットにすることで、このギャップを悪用します。 ディレクトリに存在するロールと実際に監視しているユーザーとの間のギャップに依存します。 すべての AI 管理者ロールに少なくとも 1 つのプリンシパルが割り当てられていることを確認することは、AI 管理の最小組織体制です。 このチェックでは、Microsoft Entraディレクトリ ロールの割り当てのみが評価されます。 割り当てられていないロールは、Microsoft Entra プリンシパルが対応するディレクトリ ロールに割り当てられていないため、ワークロードネイティブ管理者は引き続きMicrosoft Entra外に存在し、この評価の範囲外である可能性があります。 Microsoft Purviewロール グループやカスタム ロールMicrosoft Defenderなど、他のプラットフォームの AI 管理は、個別に確認する必要があります。

**修復アクション**

- [管理センターを使用してMicrosoft Entra IDロールを割り当](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)
- [Microsoft Entra ID ロールに対して PIM に適した割り当てを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/zero-trust-monitor-detect"} -->
## セキュリティ ガイダンス - サイバー脅威の監視と検出 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect
- Service: entra / fundamentals
- Article date: 2025-09-11
- Summary: Microsoft Entra Zero Trust 評価を使用してセキュリティ体制を改善し、脅威を監視および検出します。

堅牢な正常性監視と脅威検出機能を備えているのは、 [Secure Future Initiative](https://www.microsoft.com/trust-center/security/secure-future-initiative?msockid=2bad2df65a416adb0e5838355b3e6b95#SFI-pillars) の 6 つの柱の 1 つです。 これらのガイドラインは、アーカイブと分析のための包括的なログシステムを設定するのに役立ちます。 危険なサインイン、危険なユーザー、認証方法のトリアージに関連する推奨事項が含まれています。

この柱に合わせる最初の手順は、テナントで行われたすべての変更が保存され、分析のためにアクセスできるように、すべての Microsoft Entra ログの診断設定を構成することです。 この柱のその他の推奨事項は、リスク アラートと Microsoft Entra の推奨事項のタイムリーなトリアージに焦点を当てています。 重要なポイントは、使用できるログ、レポート、正常性監視ツールを把握し、それらを定期的に監視することです。

### セキュリティ ガイダンス

#### 診断設定は、すべての Microsoft Entra ログに対して構成されます

Microsoft Entra のアクティビティ ログとレポートは、未承認のアクセス試行を検出したり、テナント構成が変更されたタイミングを特定したりするのに役立ちます。 ログをアーカイブするか、セキュリティ情報イベント管理 (SIEM) ツールと統合すると、セキュリティ チームは強力な監視と検出のセキュリティ制御、プロアクティブな脅威ハンティング、インシデント対応プロセスを実装できます。 ログと監視機能を使用して、テナントの正常性を評価し、コンプライアンスと監査の証拠を提供できます。

ログが定期的にアーカイブされていない場合、またはクエリのために SIEM ツールに送信されない場合は、サインインの問題を調査するのは困難です。 履歴ログが存在しないということは、セキュリティ チームがサインイン試行の失敗、異常なアクティビティ、その他の侵害の兆候のパターンを見逃す可能性があることを意味します。 この可視性の欠如により、侵害がタイムリーに検出されるのを防ぎ、攻撃者は検出されないアクセスを長期間維持することができます。

**修復アクション**

- [Microsoft Entra 診断設定の構成](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)
- [Microsoft Entra ログを Azure Monitor ログと統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)
- Microsoft Entra ログをイベント ハブ [にストリーミングする](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)

#### 特権ロールのアクティブ化により、監視とアラートが設定されている

高い特権を持つロールに対する適切なアクティブ化アラートがない組織では、ユーザーがこれらの重要なアクセス許可にいつアクセスするかを把握する必要はありません。 脅威アクターは、この監視ギャップを利用して、検出なしで高度な特権ロールをアクティブ化し、管理者アカウントの作成またはセキュリティ ポリシーの変更を通じて永続化を確立することで、特権エスカレーションを実行できます。 リアルタイム アラートが存在しない場合、攻撃者は、迅速な対応手順をトリガーすることなく、横移動を実行したり、監査構成を変更したり、セキュリティ制御を無効にしたりできるようになります。

**修復アクション**

- [Privileged Identity Management で Microsoft Entra ロール設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#require-justification-on-activation)

#### グローバル管理者ロールの割り当てに対する有効化アラート

グローバル管理者ロールの割り当てに対するアクティブ化アラートがないと、脅威アクターは検出されない特権をエスカレートできます。 この可視性の欠如により、攻撃者が最も特権のある役割をアクティブ化し、バックドア アカウントの作成、セキュリティ ポリシーの変更、機密データへのアクセスなどの悪意のあるアクションを実行できる盲点が生じます。

これらのアクティブ化アラートを監視すると、セキュリティ チームが承認された特権エスカレーション アクティビティと未承認の特権エスカレーション アクティビティを区別するのに役立ちます。

**修復アクション**

- [特権ロールの通知を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#require-justification-on-active-assignment)

#### すべての特権ロールの割り当てに対するアクティベーションアラート

特権ロールの割り当てに対するアクティブ化アラートがないと、脅威アクターは検出されない特権をエスカレートできます。 この可視性の欠如により、攻撃者が最も特権のある役割をアクティブ化し、バックドア アカウントの作成、セキュリティ ポリシーの変更、機密データへのアクセスなどの悪意のあるアクションを実行できる盲点が生じます。

これらのアクティブ化アラートを監視すると、セキュリティ チームが承認された特権エスカレーション アクティビティと未承認の特権エスカレーション アクティビティを区別するのに役立ちます。

**修復アクション**

- [特権ロールの通知を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#require-justification-on-active-assignment)

#### フィッシング詐欺に強い方法で特権ユーザーがサインインする

フィッシングに強い認証方法がないと、特権ユーザーはフィッシング攻撃に対して脆弱になります。 このような攻撃により、ユーザーは資格情報を明らかにして、攻撃者に不正アクセス権を付与します。 フィッシング対策以外の認証方法を使用すると、攻撃者は敵対者の中間攻撃などの方法を通じて資格情報とトークンを傍受し、特権アカウントのセキュリティを損なう可能性があります。

脆弱な認証方法によって特権アカウントまたはセッションが侵害されると、攻撃者はアカウントを操作して長期的なアクセスを維持したり、他のバックドアを作成したり、ユーザーのアクセス許可を変更したりする可能性があります。 攻撃者は、侵害された特権アカウントを使用してアクセスをさらにエスカレートさせ、より機密性の高いシステムを制御する可能性もあります。

**修復アクション**

- [フィッシングに強いパスワードレス認証の展開を開始する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)
- [特権アカウントはフィッシング詐欺に強い方法を確実に登録し、使用する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths#authentication-strengths)
- [条件付きアクセス ポリシーを展開して特権アカウントを対象とし、フィッシング詐欺に強い資格情報を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-admin-phish-resistant-mfa)
- [認証方法のアクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report#authentication-methods-activity) を監視する

#### リスクの高いユーザーはすべてトリアージされます

Microsoft Entra ID Protection によって高リスクと見なされるユーザーは、脅威アクターによる侵害の可能性が高くなります。 脅威アクターは、侵害された有効なアカウントを介して初期アクセスを取得できます。このアカウントでは、リスク インジケーターをトリガーしているにもかかわらず疑わしいアクティビティが続行されます。 この監視により、通常とは異なるログイン パターンや疑わしい受信トレイ操作など、通常は調査を保証するアクティビティが脅威アクターによって実行されるため、永続化を実現できます。

これらの危険なユーザーのトリアージが不足していると、拡大された偵察アクティビティと横移動が可能になり、異常な動作パターンが引き続き検出されていないアラートを生成します。 脅威アクターは、セキュリティ チームがリスク インジケーターに積極的に対応していないことを示すにつれて、強化されます。

**修復アクション**

- [Microsoft Entra ID Protection で](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk) リスクの高いユーザーを調査する
- [高リスクのユーザーを修復し、Microsoft Entra ID Protection で](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock) のブロックを解除する

#### リスクの高いサインインはすべてトリアージされます

Microsoft Entra ID Protection によってフラグ付けされた危険なサインインは、不正アクセスが試行される可能性が高い可能性を示します。 脅威アクターは、これらのサインインを使用して初期の足掛かりを得ています。 これらのサインインが引き続き承認されていない場合、敵対者は正当なユーザーを装って繰り返し認証することで永続化を確立できます。

応答がない場合、攻撃者は偵察を実行し、アクセスをエスカレートし、通常のパターンにブレンドすることができます。 未調整のサインインがアラートを生成し続け、介入がない場合、敵対者がアクティブなセキュリティ対応がないことを認識するため、セキュリティギャップが広がり、横移動と防御回避が促進されます。

**修復アクション**

- [危険なサインイン](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk) を調査する
- [リスクを修復してユーザーをブロック解除する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)

#### リスクの高いワークロード ID はすべてトリアージされます

侵害されたワークロード ID (サービス プリンシパルとアプリケーション) を使用すると、脅威アクターは、ユーザー操作や多要素認証なしで永続的なアクセスを取得できます。 Microsoft Entra ID Protection は、漏洩した資格情報、異常な API トラフィック、悪意のあるアプリケーションなどの疑わしいアクティビティがないか、これらの ID を監視します。 問題のない危険なワークロード ID を使用すると、従来のセキュリティ制御をバイパスする特権エスカレーション、横移動、データ流出、永続的なバックドアが可能になります。 組織は、承認されていないアクセスを防ぐために、これらのリスクを体系的に調査し、修復する必要があります。

**修復アクション**

- [危険なワークロード ID を調査して修復する](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk#investigate-risky-workload-identities)
- [ワークロード ID に条件付きアクセス ポリシーを適用する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity)

#### テナント作成イベントがトリアージされる

承認されていないテナントの作成を検出するには、テナント作成イベントを監視してトリアージする必要があります。 十分なアクセス許可を持つユーザーは、組織のセキュリティ監視の外部でシャドウ環境を確立するために使用できる新しいテナントを作成できます。 監査ログを SIEM にルーティングし、テナント作成イベントのアラートを構成すると、セキュリティ チームは悪意のある可能性のあるアクティビティをすばやく調査して対応できます。

**修復アクション**

- [テナントを作成するためのアクセス許可を確認して制限する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)
- [SIEM 統合のためにイベント ハブに監査ログをストリーム配信する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)
- [監査イベントの監視とアラートの構成](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)

#### すべてのユーザー サインイン アクティビティで強力な認証方法が使用される

多要素認証 (MFA) がユニバーサルに適用されていない場合、または例外が発生した場合、攻撃者はアクセスを得る可能性があります。 攻撃者は、ソーシャル エンジニアリング手法を使用して、SMS や電話などの弱い MFA メソッドの脆弱性を悪用してアクセスを取得する可能性があります。 これらの手法には、認証コードをインターセプトするための SIM スワップやフィッシングが含まれる場合があります。

攻撃者は、これらのアカウントをテナントへのエントリ ポイントとして使用する可能性があります。 傍受されたユーザー セッションを使用することで、攻撃者はアクティビティを正当なユーザー アクションとして偽装し、検出を回避し、疑いを引き起こさずに攻撃を続行できます。 そこから、MFA 設定を操作して、侵害されたアカウントの特権に基づいて永続化を確立し、計画し、さらに攻撃を実行しようとする可能性があります。

**修復アクション**

- [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted) を展開する
- [フィッシングに強いパスワードレス認証の展開を開始する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)
- [条件付きアクセス ポリシーをデプロイして、すべてのユーザーに対してフィッシングに強い MFA を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)
- [認証方法アクティビティの](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report?tabs=microsoft-entra-admin-center#authentication-methods-activity) を確認する

#### 優先度の高い Microsoft Entra の推奨事項に対処する

優先順位の高い Microsoft Entra の推奨事項を対処せずにしておくと、組織のセキュリティ体制にギャップが生じ、脅威アクターに既知の弱点を悪用する機会が提供される可能性があります。 これらの項目に対して対処しないと、攻撃対象領域が増加したり、最適でない操作が発生したり、ユーザー エクスペリエンスが低下したりする可能性があります。

**修復アクション**

- [Microsoft Entra 管理センターのすべての優先度の高い推奨事項に対処](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#how-does-it-work)

#### ID 保護通知が有効になっている

ID 保護通知を有効にしない場合、脅威アクターがユーザー アカウントを侵害したり、偵察アクティビティを実施したりすると、組織は重大なリアルタイム アラートを失います。 Microsoft Entra ID Protection は、リスクのあるアカウントを検出すると、対象として **検出されたリスクのあるユーザー** と共に電子メール アラートを送信し、 **リスクのフラグが設定されたユーザー** レポートへのリンクを送信します。 これらの通知がなければ、セキュリティチームはアクティブな脅威に気づかず、脅威の主体は検出されることなく侵害されたアカウントにとどまることができます。 これらのリスクを条件付きアクセスなどのツールにフィードしてアクセスの決定を行ったり、調査と相関関係のためにセキュリティ情報およびイベント管理 (SIEM) ツールに送信したりできます。 脅威アクターは、この検出ギャップを使用して、横移動アクティビティ、特権昇格の試行、またはデータの流出を行うことができますが、管理者は進行中の侵害に気付かないままです。 遅延応答により、脅威アクターは、問題を解決する前に、より多くの永続化メカニズムを確立したり、ユーザーのアクセス許可を変更したり、機密性の高いリソースにアクセスしたりできます。 リスク検出を事前に通知しない場合、組織はリスク レポートの手動監視のみに依存する必要があります。これによって、ID ベースの攻撃の検出と対応にかかる時間が大幅に増加します。

**修復アクション**

- [リスクのあるユーザーに対するアラートを設定する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-notifications#configure-users-at-risk-detected-alerts)

#### レガシー認証のサインイン アクティビティなし

SMTP や IMAP の基本認証などのレガシ認証プロトコルでは、多要素認証 (MFA) などの最新のセキュリティ機能はサポートされていません。これは、未承認のアクセスから保護するために重要です。 この保護の欠如により、これらのプロトコルを使用するアカウントはパスワードベースの攻撃に対して脆弱になり、攻撃者は盗まれた資格情報または推測された資格情報を使用して初期アクセスを取得する手段を提供します。

攻撃者が資格情報への不正アクセスに成功すると、脆弱な認証方法をエントリ ポイントとして使用して、それらを使用してリンクされたサービスにアクセスできます。 レガシ認証を通じてアクセスを取得した攻撃者は、メール転送ルールの構成や他の設定の変更など、Microsoft Exchange に変更を加え、機密性の高い通信への継続的なアクセスを維持できる可能性があります。

また、レガシ認証では、セキュリティ アラートをトリガーしたり、再認証を要求したりすることなく、侵害された資格情報を使用してシステムを再入力する一貫した方法を攻撃者に提供します。

そこから、攻撃者はレガシ プロトコルを使用して、侵害されたアカウントを介してアクセスできる他のシステムにアクセスし、横移動を促進できます。 レガシ プロトコルを使用する攻撃者は、正当なユーザー アクティビティと組み合わせて、セキュリティ チームが通常の使用と悪意のある動作を区別することが困難になります。

**修復アクション**

- Exchange [で Exchange プロトコルを非アクティブ化できる](https://learn.microsoft.com/ja-jp/exchange/clients-and-mobile-in-exchange-online/disable-basic-authentication-in-exchange-online)
- [レガシ認証プロトコルは、条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication) でブロックできます
- レガシ認証ワークブックを使用してレガシ認証でのサインインを行い、それを無効にしても安全かどうかを判断するのに役立ちます。

#### Microsoft Entra のすべての推奨事項に対処しました。

Microsoft Entra の推奨事項は、ベスト プラクティスを実装し、セキュリティ体制を最適化する機会を組織に提供します。 これらの項目に対して対処しないと、攻撃対象領域が増加したり、最適でない操作が発生したり、ユーザー エクスペリエンスが低下したりする可能性があります。

**修復アクション**

- [Microsoft Entra 管理センターの](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations#how-does-it-work) でアクティブまたは延期されたすべての推奨事項に対処する

#### ネットワーク アクセス アクティビティは、脅威の検出と対応のためのセキュリティ操作に対して表示されます

グローバルなセキュリティで保護されたアクセス ログが Microsoft Sentinel ワークスペースに統合されていないと、セキュリティ運用チームは、プライベート アクセス、インターネット アクセス、および Microsoft 365 トラフィック転送全体で、ネットワーク トラフィック パターン、接続試行、アクセスの異常を一元的に把握することはできません。 ユーザーの資格情報またはデバイスを侵害する脅威アクターは、これらのネットワーク アクセス パスを使用して、偵察の実行、横方向への移動、または検出なしでデータの流出を行うことができます。

この統合がない場合:

- セキュリティ チームは、ネットワーク層アクティビティを Microsoft Entra ID またはエンドポイント検出の ID ベースのシグナルと関連付けることはできません。
- セキュリティ情報およびイベント管理 (SIEM) システムでは、行動分析、脅威インテリジェンスの関連付け、自動応答プレイブックをグローバル セキュア アクセス トラフィックに適用することはできません。
- セキュリティ チームは、ネットワーク アクセス パターンの履歴を調査したり、ネットワークと ID シグナル全体で脅威を探したりすることはできません。

**修復アクション**

- Microsoft Sentinel 統合用の Log Analytics ワークスペースにグローバル なセキュリティで保護されたアクセス ログを送信するように Microsoft [Entra 診断設定を構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-sentinel-integration)します。
- 診断設定で、、`NetworkAccessTrafficLogs`、`EnrichedOffice365AuditLogs`、`RemoteNetworkHealthLogs`、`NetworkAccessAlerts`、`NetworkAccessConnectionEvents`など、`NetworkAccessGenerativeAIInsights`。
- [Microsoft Entra アクティビティ ログを Azure Monitor と統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) して、一元的なログ収集を行います。
- [Microsoft Sentinel ワークスペースを構成](https://learn.microsoft.com/ja-jp/azure/sentinel/quickstart-onboard) し、コンテンツ ハブからグローバル セキュリティで保護されたアクセス ソリューションをインストールします。

#### ネットワーク アクセス ログは、セキュリティ分析とコンプライアンス要件のために保持されます

グローバル セキュリティで保護されたアクセスの監査とトラフィック ログの保持期間を延長しないと、脅威アクターは、検出が行われる前にアクティビティが自動的に消去されることを認識して、既定の 30 日間のリテンション期間を超えて動作できます。 セキュリティ調査では、侵害ベクトル、横移動パターン、およびデータ流出チャネルを特定するために、多くの場合、数週間または数か月にわたる履歴分析が必要になります。

ログ保存が十分でない場合:

- セキュリティ チームは、ベースラインの動作パターンを確立したり、振り返り脅威ハンティングを実行したり、延長された期間にわたってネットワーク アクセス イベントを関連付けたりすることはできません。
- [GDPR](https://learn.microsoft.com/ja-jp/compliance/regulatory/gdpr)、HIPAA、PCI DSS、SOX などの規制フレームワークの対象となる組織は、義務付けられた保持期間の監査証跡を生成できない場合、コンプライアンス違反に直面します。
- インシデント対応中の根本原因の分析は制限されており、脅威アクターは、組織が目に見える症状に焦点を当てながら永続化を維持できる可能性があります。

**修復アクション**

- [Log Analytics ワークスペースを使用して診断設定を構成](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) し、クエリ機能を使用して 90 ~ 730 日間の長期保有期間を設定します。
- 組織のセキュリティとコンプライアンスの要件を満たすように [Log Analytics ワークスペースのリテンション期間を構成](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/data-retention-archive)します (90 日以上を推奨)。
- 特定のグローバル セキュリティで保護されたアクセス テーブル[のテーブル レベルのリテンション期間を有効](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/data-retention-archive#configure-table-level-retention)にして、ワークスペースの既定値を超えて拡張します。

#### グローバルセキュアアクセスのデプロイメントログが記録され、レビューされます。

グローバル セキュリティで保護されたアクセスのデプロイ ログは、グローバル ネットワーク全体の構成変更の状態と進行状況を追跡します。 これらの変更には、プロファイルの再配布の転送、リモート ネットワークの更新、プロファイルの変更のフィルター処理、条件付きアクセス設定の変更が含まれます。 デプロイ ログに失敗したデプロイが示されている場合、脅威アクターは、一部のエッジの場所に古いポリシーや正しく構成されていないポリシーがある一貫性のないセキュリティ構成を悪用する可能性があります。

デプロイ ログを監視しない場合:

- 失敗したデプロイでは、セキュリティ検査を通じてトラフィックをルーティングしない古い転送プロファイルや、悪意のある宛先をブロックしないプロファイルのフィルター処理など、セキュリティのギャップが残る可能性があります。
- 管理者は、変更が一様に適用されていると考え、古くなった構成に気付かないままでいる可能性があります。
- 悪用可能なギャップを作成するデプロイエラーは検出されない可能性があります。

**修復アクション**

- [グローバル セキュリティで保護されたアクセスのデプロイ ログを使用する方法の](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-view-deployment-logs)手順に従って、次の操作を行います。
    - Microsoft Entra 管理センターのデプロイ ログにアクセスして確認し、失敗したデプロイを特定します。
    - 失敗したデプロイの場合は、 `status.message` フィールドのエラー メッセージを調べて、エラーをトリガーした構成の変更を再試行します。
    - 構成の変更を行うときに管理センターに表示される展開通知を監視して、エラーをリアルタイムでキャッチします。
- リモート ネットワークのデプロイが常に失敗する場合は、 [基になるリモート ネットワーク構成で](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-remote-networks) エラーを確認してください。
- プロファイルの展開エラーを転送する場合は、 [トラフィック転送の構成を確認](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-traffic-forwarding)します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/zero-trust-protect-engineering-systems"} -->
## セキュリティ ガイダンス - エンジニアリング システムの保護 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems
- Service: entra / fundamentals
- Article date: 2025-09-11
- Summary: Microsoft Entra Zero Trust 評価を使用してセキュリティ体制を改善し、エンジニアリング システムを保護します。

エンジニアリング システムを保護するための [セキュア 未来イニシアチブ](https://www.microsoft.com/trust-center/security/secure-future-initiative?msockid=2bad2df65a416adb0e5838355b3e6b95#SFI-pillars) の柱は、Microsoft 独自のソフトウェア資産とインフラストラクチャを保護するために最初に開発されました。 この内部作業から得られたプラクティスと分析情報が顧客と共有され、独自の環境を強化できるようになりました。

これらの推奨事項は、組織のエンジニアリング システムとリソースへの最小限の特権アクセスを確保することに焦点を当てています。

### セキュリティに関する推奨事項

#### 緊急アクセス アカウントが適切に構成されている

Microsoft では、グローバル [管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられている 2 つのクラウド専用緊急アクセス アカウントを組織に付与することをお勧めします。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 アカウントは、通常のアカウントを使用できない、または他のすべての管理者が誤ってロックアウトされた緊急用または「ブレイクグラス」のシナリオに限定されます。

**修復アクション**

- 緊急アクセスアカウントの推奨事項に従って [アカウントを作成します](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)。

#### グローバル管理者ロールのアクティブ化によって承認ワークフローがトリガーされる

承認ワークフローがない場合、フィッシング、資格情報の詰め込み、またはその他の認証バイパス手法によってグローバル管理者の資格情報を侵害した脅威アクターは、他の検証や監視を行うことなく、テナントで最も特権のあるロールを直ちにアクティブ化できます。 Privileged Identity Management (PIM) を使用すると、資格のあるロールのアクティブ化が数秒以内にアクティブになるため、資格情報が侵害されると、ほぼ瞬時に特権エスカレーションが可能になります。 アクティブ化されると、脅威アクターはグローバル管理者ロールを使用して、次の攻撃パスを使用してテナントへの永続的なアクセスを取得できます。

- 新しい特権アカウントを作成する
- 条件付きアクセス ポリシーを変更して、これらの新しいアカウントを除外する
- 高い特権を持つ証明書ベースの認証やアプリケーションの登録などの代替認証方法を確立する

グローバル管理者ロールは、Microsoft Entra ID および Microsoft Entra ID を使用するサービス (Microsoft Defender XDR、Microsoft Purview、Exchange Online、SharePoint Online など) の管理機能へのアクセスを提供します。 承認ゲートがないと、脅威アクターは迅速にエスカレートしてテナントの引き継ぎを完了し、機密データを流出させ、すべてのユーザー アカウントを侵害し、最初の侵害が検出された後も保持されるサービス プリンシパルまたはフェデレーションの変更を通じて長期的なバックドアを確立できます。

**修復アクション**

- [グローバル管理者のアクティブ化の承認を要求するようにロール設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)
- [特権ロールの承認ワークフローを設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow)

#### グローバル管理者が Azure サブスクリプションに永続的にアクセスできない

Azure サブスクリプションへの永続的なアクセス権を持つグローバル管理者は、脅威アクターの攻撃対象領域を拡大します。 グローバル管理者アカウントが侵害された場合、攻撃者はすぐにリソースを列挙し、構成を変更し、ロールを割り当て、すべてのサブスクリプションにわたって機密データを流出させることができます。 サブスクリプション アクセスにジャストインタイムでの昇格を要求すると、検出可能なシグナルが現れ、攻撃者の動きが遅くなり、監視可能な制御ポイントを通して影響の大きい操作がルーティングされます。

**修復アクション**

- [フィッシングに強いパスワードレス認証の展開を開始する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)
- [特権アカウントがフィッシング詐欺に強い方法を登録し、使用するようにする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths#authentication-strengths.md)
- [特権アカウントを対象に条件付きアクセス ポリシーを展開し、認証強度を使用してフィッシング詐欺に強い資格情報を要求](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-admin-phish-resistant-mfa)
- [認証方法のアクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report#authentication-methods-activity.md) を監視する

#### 新しいアプリケーションとサービス プリンシパルの作成は特権ユーザーに制限されます

特権のないユーザーがアプリケーションとサービス プリンシパルを作成できる場合、これらのアカウントが正しく構成されていないか、必要以上に多くのアクセス許可が付与され、攻撃者が初期アクセスを取得するための新しいベクトルが作成される可能性があります。 攻撃者はこれらのアカウントを悪用して、環境内で有効な資格情報を確立し、一部のセキュリティ制御をバイパスすることができます。

特権のないこれらのアカウントに、アプリケーションの所有者としての許可が誤って付与された場合、攻撃者はそれらを使用して、低いレベルのアクセスから高い権限のあるアクセスに移行できます。 特権のないアカウントを侵害する攻撃者は、自分の資格情報を追加したり、特権のないユーザーによって作成されたアプリケーションに関連付けられているアクセス許可を変更して、検出されない環境に引き続きアクセスできるようにすることができます。

攻撃者は、サービス プリンシパルを使用して、正当なシステム プロセスとアクティビティに溶け込むことができます。 サービス プリンシパルは多くの場合、自動化されたタスクを実行するため、これらのアカウントで実行される悪意のあるアクティビティには疑わしいというフラグが設定されない可能性があります。

**修復アクション**

- [特権のないユーザーによるアプリの作成をブロックする](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-app-roles)

#### 非アクティブなアプリケーションには、高い特権を持つ Microsoft Graph API アクセス許可がありません

攻撃者は、引き続き昇格された特権を持つ有効で非アクティブなアプリケーションを悪用する可能性があります。 これらのアプリケーションは、正当なアプリケーションであるため、アラームを発生させることなく初期アクセスを取得するために使用できます。 そこから、攻撃者はアプリケーション特権を使用して、他の攻撃を計画または実行できます。 攻撃者は、資格情報の追加など、非アクティブなアプリケーションを操作してアクセスを維持する可能性もあります。 この永続化により、プライマリ アクセス方法が検出された場合でも、後でアクセスを回復できます。

**修復アクション**

- [特権サービス プリンシパル](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-update) を無効にする
- アプリケーションに正当なユース ケースがあるかどうかを調査する
- [サービス プリンシパルに正当なユース ケースがない場合は、それを削除](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-delete)

#### 非アクティブなアプリケーションには、高い特権を持つ組み込みロールがありません

攻撃者は、引き続き昇格された特権を持つ有効で非アクティブなアプリケーションを悪用する可能性があります。 これらのアプリケーションは、正当なアプリケーションであるため、アラームを発生させることなく初期アクセスを取得するために使用できます。 そこから、攻撃者はアプリケーション特権を使用して、他の攻撃を計画または実行できます。 攻撃者は、資格情報の追加など、非アクティブなアプリケーションを操作してアクセスを維持する可能性もあります。 この永続化により、プライマリ アクセス方法が検出された場合でも、後でアクセスを回復できます。

**修復アクション**

- [非アクティブな特権サービス プリンシパル](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-update) を無効にする
- アプリケーションに正当なユース ケースがあるかどうかを調査します。 その場合は、OAuth2 アクセス許可の方が適しているかどうかを分析
- [サービス プリンシパルに正当なユース ケースがない場合は、それを削除](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-delete)

#### アプリの登録で安全なリダイレクト URI を使用する

ワイルドカードを含む URL または URL 短縮機能を使用して構成された OAuth アプリケーションは、脅威アクターの攻撃対象領域を増やします。 セキュリティで保護されていないリダイレクト URI (応答 URL) を使用すると、敵対者は、攻撃者が制御するエンドポイントにユーザーを誘導することで、認証要求の操作、承認コードのハイジャック、トークンの傍受が可能になる場合があります。 ワイルドカード エントリは、意図しないドメインに認証応答の処理を許可することでリスクを拡大します。一方、URL を短縮すると、制御されていない環境でのフィッシングやトークンの盗難が容易になる可能性があります。

リダイレクト URI を厳密に検証しないと、攻撃者はセキュリティ制御をバイパスし、正当なアプリケーションを偽装し、特権をエスカレートすることができます。 この構成ミスにより、敵対者が弱い OAuth 強制を悪用して、検出されない保護されたリソースに侵入するため、永続化、未承認のアクセス、横移動が可能になります。

**修復アクション**

- [アプリケーション登録のリダイレクト URI を確認します。](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url) リダイレクト URI に \*.azurewebsites.net、ワイルドカード、または URL 短縮が含まれていないことを確認します。

#### サービス プリンシパルが安全なリダイレクト URI を使用する

ワイルドカード、localhost、または URL 短縮を含む URL で構成された Microsoft 以外のマルチテナント アプリケーションは、脅威アクターの攻撃対象領域を増やします。 これらの安全でないリダイレクト URI (応答 URL) により、敵対者は、攻撃者が制御するエンドポイントにユーザーを誘導することで、認証要求の操作、承認コードのハイジャック、トークンの傍受が可能になる場合があります。 ワイルドカード エントリは、意図しないドメインが認証応答を処理することを許可することでリスクを拡大します。一方、localhost と短縮 URL は、制御されていない環境でのフィッシングやトークンの盗難を容易にする可能性があります。

リダイレクト URI を厳密に検証しないと、攻撃者はセキュリティ制御をバイパスし、正当なアプリケーションを偽装し、特権をエスカレートすることができます。 この構成ミスにより、敵対者が弱い OAuth 強制を悪用して、検出されない保護されたリソースに侵入するため、永続化、未承認のアクセス、横移動が可能になります。

**修復アクション**

- [アプリケーション登録のリダイレクト URI を確認します。](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url) リダイレクト URI に localhost、\*.azurewebsites.net、ワイルドカード、または URL 短縮子がないことを確認します。

#### アプリの登録には、未解決または破棄されたドメイン リダイレクト URI を含めてはなりません

アプリの登録で保持されていないリダイレクト URI または孤立したリダイレクト URI は、アクティブなリソースを指し示しなくなったドメインを参照するときに、重大なセキュリティ脆弱性を作成します。 脅威アクターは、破棄されたドメインでリソースをプロビジョニングし、リダイレクト エンドポイントを効果的に制御することで、これらの "未解決" DNS エントリを利用できます。 この脆弱性により、攻撃者は OAuth 2.0 フロー中に認証トークンと資格情報を傍受でき、不正アクセス、セッションハイジャック、組織の広範な侵害の可能性があります。

**修復アクション**

- [リダイレクト URI (応答 URL) の概要と制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url)

#### リソース固有の同意が制限されている

グループ所有者が Microsoft Entra ID のアプリケーションに同意できるようにする場合は、管理者の資格情報なしでデータを保持および盗むために無効なアクターが使用できる横エスカレーション パスを作成します。 攻撃者がグループ所有者アカウントを侵害した場合、悪意のあるアプリケーションを登録または使用し、グループを対象とした高い特権を持つ Graph API のアクセス許可に同意することができます。 攻撃者は、すべての Teams メッセージを読み取ったり、SharePoint ファイルにアクセスしたり、グループ メンバーシップを管理したりする可能性があります。 この同意アクションにより、委任されたアクセス許可またはアプリケーションアクセス許可を持つ有効期間の長いアプリケーション ID が作成されます。 攻撃者は OAuth トークンを使用して永続化を維持し、チーム チャネルやファイルから機密データを盗み、メッセージングまたは電子メールのアクセス許可を通じてユーザーを偽装します。 アプリの同意ポリシーを一元的に適用しないと、セキュリティ チームは可視性を失い、悪意のあるアプリケーションがレーダーの下に広がり、コラボレーション プラットフォーム間でマルチステージ攻撃が可能になります。

**修復アクション**

組織の Resource-Specific Consent (RSC) 構成を `EnabledForPreApprovedAppsOnly` に設定し、RSC アクセス許可を必要とするアプリごとに事前適用ポリシーを作成します。 既定の `ManagedByMicrosoft` 状態は使用しないでください。

- [特定のアプリ セットに対してのみ RSC を有効にする](https://learn.microsoft.com/ja-jp/microsoftteams/platform/graph-api/rsc/preapproval-instruction-docs#enable-rsc-for-a-specific-set-of-apps-only)

#### ワークロード ID に特権ロールが割り当てられない

管理者がサービス プリンシパルやマネージド ID などのワークロード ID に特権ロールを割り当てると、それらの ID が侵害された場合、テナントは重大なリスクにさらされる可能性があります。 特権ワークロード ID にアクセスする脅威アクターは、偵察を実行してリソースを列挙し、特権をエスカレートし、機密データを操作または流出させることができます。 通常、攻撃チェーンは、資格情報の盗難や脆弱なアプリケーションの悪用から始まります。 次の手順は、割り当てられたロールによる特権エスカレーション、クラウド リソース間の横移動、最後に他のロールの割り当てまたは資格情報の更新による永続化です。 ワークロード ID は自動化でよく使用され、ユーザー アカウントほど厳密に監視されない場合があります。 その後、侵害が検出されず、脅威アクターは重要なリソースへのアクセスと制御を維持できます。 ワークロード ID は MFA などのユーザー中心の保護の対象ではなく、最小限の特権の割り当てと定期的なレビューが不可欠になります。

**修復アクション**

- [特権ロールの割り当てを確認して削除します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles#update-or-remove-an-existing-role-assignment)。
- [ワークロード ID のベスト プラクティスに従います](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview#key-scenarios)。
- [Microsoft Entra ID の特権ロールとアクセス許可について説明します](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)

#### エンタープライズ アプリケーションでは、明示的な割り当てまたはスコープ付きプロビジョニングが必要です

エンタープライズ アプリケーションに明示的な割り当て要件とスコープ設定されたプロビジョニング制御の両方がない場合、脅威アクターはこの二重の弱点を利用して、機密性の高いアプリケーションとデータへの不正アクセスを得ることができます。 最も高いリスクは、既定の設定でアプリケーションが構成されている場合に発生します。"割り当てが必要" が "いいえ" に設定 *され、* プロビジョニングは必要ありません。スコープも設定されていません。 この危険な組み合わせにより、テナント内の任意のユーザー アカウントを侵害する脅威アクターは、広範なユーザー ベースを持つアプリケーションにすぐにアクセスでき、攻撃対象領域が広がり、組織内での横移動の可能性が広がります。

割り当てが開いているが適切なプロビジョニング スコープを持つアプリケーション (部門ベースのフィルターやグループ メンバーシップの要件など) では、プロビジョニング 層を通じてセキュリティ制御が維持されますが、両方の制御を持たないアプリケーションでは、脅威アクターが悪用できる無制限のアクセス経路が作成されます。 アプリケーションが割り当て制限なしですべてのユーザーのアカウントをプロビジョニングすると、脅威アクターは侵害されたアカウントを悪用して偵察アクティビティを実施したり、複数のシステム間で機密データを列挙したり、接続されたリソースに対するさらなる攻撃のステージング ポイントとしてアプリケーションを使用したりすることができます。 この無制限のアクセス モデルは、昇格されたアクセス許可を持っているか、重要なビジネス システムに接続されているアプリケーションでは危険です。 脅威アクターは、侵害されたユーザー アカウントを使用して、機密情報にアクセスしたり、データを変更したり、アプリケーションのアクセス許可で許可されている未承認のアクションを実行したりできます。 割り当て制御とプロビジョニング スコープの両方が存在しない場合、組織は適切なアクセス ガバナンスを実装できなくなります。 適切なガバナンスがないと、どのアプリケーションにアクセスできるか、いつアクセス権が付与されたか、ロールの変更や雇用状態に基づいてアクセスを取り消す必要があるかを追跡することは困難です。 さらに、広範なプロビジョニング スコープを持つアプリケーションでは、侵害された 1 つのアカウントが接続されたアプリケーションとサービスのエコシステム全体にアクセスできる、連鎖的なセキュリティ リスクが発生する可能性があります。

**修復アクション**

- ビジネス要件を評価して、適切なアクセス制御方法を決定します。 [Microsoft Entra アプリを一連のユーザーに制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restrict-your-app-to-a-set-of-users)します。
- 機密性の高いアプリケーションに割り当てを要求するようにエンタープライズ アプリケーションを構成します。 ["割り当てが必要" エンタープライズ アプリケーション プロパティについて説明します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-properties#assignment-required)。
- グループ、部門、または属性に基づいて、スコープ付きプロビジョニングを実装します。 [スコープ フィルターを作成します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts#create-scoping-filters)。

#### エンタープライズ アプリケーションには所有者がいます

所有者がいないエンタープライズ アプリケーションは、脅威アクターが悪用できる孤立した資産になります。 多くの場合、これらのアプリケーションは、適切な監視とセキュリティ ガバナンスを欠きながら、昇格されたアクセス許可と機密性の高いリソースへのアクセスを保持します。

所有者がいないアプリケーションでは、セキュリティ監視で盲点が生じ、攻撃者は既存のアプリケーションのアクセス許可を利用してデータにアクセスしたり、バックドア アカウントを作成したりして永続化を確立できます。 また、所有権がないと、適切なアクセス レビューとアクセス許可の監査が防止され、過剰なアクセス許可または古い構成を持つアプリケーションは管理対象外のままになります。

所有者を割り当てると、効果的なアプリケーション ライフサイクル管理が可能になり、適切なセキュリティ監視が保証されます。

**修復アクション**

- [エンタープライズ アプリケーション所有者の割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners)

#### ユーザーあたりのデバイスの最大数を 10 に制限する

デバイスの急増を制御することが重要です。 各ユーザーが Microsoft Entra ID テナントに登録できるデバイスの数に適切な制限を設定します。 デバイス登録を制限すると、セキュリティが維持され、ビジネスの柔軟性が確保されます。 Microsoft Entra ID を使用すると、ユーザーは既定で最大 50 台のデバイスを登録できます。 この数を 10 に減らすと、攻撃対象領域が最小限に抑え、デバイス管理が簡素化されます。

**修復アクション**

- [ユーザーあたりのデバイスの最大数を制限](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities#configure-device-settings)する方法について説明します。

#### 特権アクセス ワークステーションの条件付きアクセス ポリシーが構成されている

特権ロールのアクティブ化が専用の Privileged Access Workstations (PAW) に制限されていない場合、脅威アクターは、侵害されたエンドポイント デバイスを悪用して、アンマネージド ワークステーションまたは非準拠ワークステーションからの特権エスカレーション攻撃を実行する可能性があります。 標準的な生産性ワークステーションには、多くの場合、無制限の Web 閲覧、フィッシングに対して脆弱な電子メール クライアント、潜在的な脆弱性を持つローカルにインストールされたアプリケーションなどの攻撃ベクトルが含まれています。 管理者がこれらのワークステーションから特権ロールをアクティブ化した場合、マルウェア、ブラウザーの悪用、またはソーシャル エンジニアリングによって初期アクセスを取得する脅威アクターは、ローカルにキャッシュされた特権資格情報を使用するか、既存の認証済みセッションをハイジャックして特権をエスカレートできます。 特権ロールのアクティブ化により、Microsoft Entra ID と接続済みサービス間で広範な管理者権限が付与されるため、攻撃者は新しい管理アカウントを作成し、セキュリティ ポリシーを変更し、すべての組織リソースの機密データにアクセスし、環境全体にマルウェアやバックドアを展開して永続的なアクセスを確立できます。 侵害されたエンドポイントから特権クラウド リソースへのこの横移動は、多くの従来のセキュリティ制御をバイパスする重要な攻撃パスを表します。 認証された管理者のセッションから発信された場合、特権アクセスは正当に表示されます。

このチェックに合格した場合、テナントには PAW デバイスへの特権ロール アクセスを制限する条件付きアクセス ポリシーがありますが、PAW ソリューションを完全に有効にするために必要な制御はそれだけではありません。 また、Intune デバイスの構成とコンプライアンス ポリシーとデバイス フィルターを構成する必要もあります。

**修復アクション**

- [特権アクセス ワークステーション ソリューションをデプロイする](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-deployment)
    - 条件付きアクセスと Intune デバイスの構成とコンプライアンス ポリシーを構成するためのガイダンスを提供します。
- [条件付きアクセスでデバイス フィルターを構成して特権アクセスを制限する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/zero-trust-protect-identities"} -->
## セキュリティ ガイダンス - ID とシークレットを保護する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities
- Service: entra / fundamentals
- Article date: 2025-09-11
- Summary: ID とシークレットを保護するために、Microsoft Entra ゼロ トラスト評価を使用してセキュリティ体制を改善します。

ユーザーとアプリケーションの認証と承認は、ID とシークレットのインフラストラクチャへのエントリ ポイントです。 すべての ID とシークレットを保護することは、ゼロ トラストの取り組みの基本ステップであり、[Secure Future Initiative](https://www.microsoft.com/trust-center/security/secure-future-initiative?msockid=2bad2df65a416adb0e5838355b3e6b95#SFI-pillars) の柱です。

この柱の一部である推奨事項とゼロ トラストチェックは、不正アクセスのリスクを軽減するのに役立ちます。 ID とシークレットの保護は、Microsoft Entra内のゼロ トラストの中核を表します。 テーマには、シークレットと証明書の適切な使用、特権アカウントに対する適切な制限、最新のパスワードレス認証方法が含まれます。

### ゼロ トラストセキュリティに関する推奨事項

#### アプリケーションにクライアント シークレットが構成されていない

クライアント シークレットを使用するアプリケーションは、それらを構成ファイルに格納したり、スクリプトにハードコーディングしたり、他の方法で公開を危険にさらしたりする可能性があります。 シークレット管理の複雑さにより、クライアント シークレットはリークの影響を受けやすく、攻撃者にとって魅力的です。 クライアント シークレットが公開されると、攻撃者はアクティビティを正当な操作と組み合わせて、セキュリティ制御を簡単にバイパスできるようになります。 攻撃者がアプリケーションのクライアント シークレットを侵害した場合、システム内の特権をエスカレートし、アプリケーションのアクセス許可に応じて、より広範なアクセスと制御を行うことができます。

Microsoft Graph API またはその他の API に対するアクセス許可を持つアプリケーションとサービス プリンシパルは、攻撃者がこれらの追加のアクセス許可を悪用する可能性があるため、リスクが高くなります。

**修復アクション**

- [共有シークレットからマネージド ID にアプリケーションを移動し、より安全なプラクティス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-applications-from-secrets)採用します。
    - Azure リソースにマネージド ID を使用する
    - ワークロード ID の条件付きアクセス ポリシーをデプロイする
    - シークレット スキャンを実装する
    - アプリケーション認証ポリシーを展開して、セキュリティで保護された認証プラクティスを適用する
    - 最小限の特権を持つカスタム ロールを作成してアプリケーションの資格情報をローテーションする
    - アプリケーションをトリアージして監視するプロセスがあることを確認する

#### サービス プリンシパルに証明書または資格情報が関連付けられていない

適切な認証資格情報 (証明書またはクライアント シークレット) のないサービス プリンシパルは、脅威アクターがこれらの ID を偽装できるようにするセキュリティの脆弱性を作成します。 これにより、未承認のアクセス、環境内での横移動、特権のエスカレーション、検出と修復が困難な永続的なアクセスが発生する可能性があります。

**修復アクション**

- 組織のサービス プリンシパルの場合: [証明書またはクライアント シークレットをアプリの登録に追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials)する
- 外部サービス プリンシパルの場合: 不要な資格情報を確認して削除し、セキュリティ リスクを軽減する

#### アプリケーションの有効期限が 180 日を超える証明書がない

証明書が安全に保存されていない場合は、攻撃者によって抽出および悪用され、不正アクセスにつながる可能性があります。 有効期間の長い証明書は、時間の経過と同時に公開される可能性が高くなります。 資格情報が公開されると、攻撃者はアクティビティを正当な操作と融合させ、セキュリティ制御をバイパスしやすくなります。 攻撃者がアプリケーションの証明書を侵害した場合、システム内の特権をエスカレートし、アプリケーションの特権に応じてより広範なアクセスと制御を行うことができます。

**修復アクション**

- [証明書ベースのアプリケーション構成](https://devblogs.microsoft.com/identity/app-management-policy/) を定義する
- [テナント内のアプリとサービス プリンシパルの信頼された証明機関を定義する](https://learn.microsoft.com/ja-jp/graph/api/resources/certificatebasedapplicationconfiguration)
- [アプリケーション管理ポリシー](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy) を定義する
- [シークレットと証明書の標準を適用する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-enforce-secret-standards)
- [アプリケーション資格情報をローテーションする最小特権のカスタム ロールを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)

#### アプリケーション証明書は定期的にローテーションする必要があります

証明書が定期的にローテーションされない場合は、脅威アクターに、それらを抽出して悪用するための拡張ウィンドウが提供され、不正アクセスにつながる可能性があります。 このような資格情報が公開されると、攻撃者は悪意のあるアクティビティと正当な操作を組み合わせて、セキュリティ制御を簡単にバイパスできます。 攻撃者がアプリケーションの証明書を侵害した場合、システム内の特権をエスカレートし、アプリケーションの特権に応じてより広範なアクセスと制御を行うことができます。

証明書の資格情報を持つすべてのサービス プリンシパルとアプリケーション登録に対してクエリを実行します。 証明書の開始日が 180 日未満であることを確認します。

**修復アクション**

- [証明書の有効期間を管理するアプリケーション管理ポリシーを定義](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy)
- [信頼された証明書チェーンの信頼](https://learn.microsoft.com/ja-jp/graph/api/resources/certificatebasedapplicationconfiguration) を定義する
- [アプリケーション資格情報をローテーションする最小特権カスタム ロールを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)
- [証明書ベースの資格情報を管理するためのアプリ管理ポリシーの詳細](https://devblogs.microsoft.com/identity/app-management-policy/)

#### アプリ シークレットと証明書の標準を適用する

適切なアプリケーション管理ポリシーがないと、脅威アクターは、脆弱なアプリケーション資格情報または正しく構成されていないアプリケーション資格情報を悪用して、組織のリソースへの未承認のアクセスを取得する可能性があります。 有効期間の長いパスワード シークレットまたは証明書を使用するアプリケーションでは、侵害された資格情報が長期間有効な状態を維持する拡張攻撃ウィンドウが作成されます。 アプリケーションで構成ファイルにハードコーディングされたクライアント シークレットを使用している場合、またはパスワード要件が弱い場合、脅威アクターは、ソース コード リポジトリ、構成ダンプ、メモリ分析など、さまざまな方法でこれらの資格情報を抽出できます。 脅威アクターがこれらの資格情報を取得した場合、環境内で横移動を実行したり、アプリケーションに昇格されたアクセス許可がある場合は特権をエスカレートしたり、バックドア資格情報を作成して永続化を確立したり、アプリケーションの構成を変更したり、データを流出させたりすることができます。 資格情報のライフサイクル管理が不足しているため、侵害された資格情報は無期限にアクティブなままになり、脅威アクターは組織の資産に持続的にアクセスでき、データ流出、システム操作、または検出なしでより多くの悪意のあるツールを展開することができます。

適切なアプリ管理ポリシーを構成すると、組織はこれらの脅威に先んじるのに役立ちます。

**修復アクション**

- [アプリケーション管理ポリシーを使用してシークレットと証明書の標準を適用する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-enforce-secret-standards)

#### Microsoft サービス アプリケーションに資格情報が構成されていない

テナントで動作するアプリケーションMicrosoft サービス、所有者組織 ID "f8cdef31-a31e-4b4a-93e4-5f571e91255a" を持つサービス プリンシパルとして識別されます。これらのサービス プリンシパルがテナントに資格情報を構成すると、脅威アクターが悪用する可能性のある攻撃ベクトルが作成される可能性があります。 管理者が資格情報を追加し、不要になった場合、攻撃者のターゲットになる可能性があります。 適切な予防および検出の制御が特権アクティビティに対して行われている可能性は低くなりますが、脅威アクターは資格情報を悪意を持って追加する可能性もあります。 どちらの場合も、脅威アクターはこれらの資格情報を使用してサービス プリンシパルとして認証し、Microsoft サービス アプリケーションと同じアクセス許可とアクセス権を取得できます。 この初期アクセスは、アプリケーションに高レベルのアクセス許可がある場合に特権エスカレーションにつながる可能性があり、テナント間での横移動が可能になります。 攻撃者は、他のバックドア資格情報を作成することで、データ流出または永続化の確立に進むことができます。

テナント内のこれらのサービス プリンシパルに対して資格情報 (クライアント シークレットや証明書など) が構成されている場合は、管理者または悪意のあるアクターのいずれかのユーザーが環境内で個別に認証を有効にしていることを意味します。 これらの資格情報を調査して、正当性と必要性を判断する必要があります。 不要になった場合は、リスクを軽減するために削除する必要があります。

このチェックに合格しない場合は、未使用の資格情報が構成されているアプリケーションを特定して確認する必要があるため、"調査" することをお勧めします。

**修復アクション**

- 追加された資格情報がまだ有効なユース ケースであるかどうかを確認します。 そうでない場合は、セキュリティ リスクを軽減するために、Microsoft サービス アプリケーションから資格情報を削除します。
    - Microsoft Entra 管理センターで、**Entra ID**&gt;**アプリの登録** に移動し、影響を受けるアプリケーションを選択します。
    - [ **証明書とシークレット** ] セクションに移動し、不要になった資格情報を削除します。

#### ユーザーの同意設定が制限される

ユーザーの同意設定が制限されていないと、脅威アクターは、制限のないアプリケーションの同意構成を悪用して、機密性の高い組織データへの不正アクセスを取得できます。 ユーザーの同意が無制限の場合、攻撃者は次のことができます。

- ソーシャル エンジニアリングと不正な同意付与攻撃を使用して、悪意のあるアプリケーションを承認するようにユーザーをだまします。
- 正当なサービスを偽装して、電子メール、ファイル、予定表、その他の重要なビジネス データへのアクセスなど、広範なアクセス許可を要求します。
- 境界セキュリティ制御をバイパスする正当な OAuth トークンを取得し、セキュリティ監視システムに通常どおりにアクセスできるようにします。
- 組織のリソースへの永続的なアクセスを確立し、Microsoft 365 サービス間で偵察を実施し、接続されたシステムを横方向に移動し、特権をエスカレートする可能性があります。

また、無制限のユーザーの同意により、アプリケーション アクセスに対して一元的なガバナンスを適用する組織の能力が制限されるため、機密データにアクセスできるMicrosoft以外のアプリケーションの可視性を維持することが困難になります。 このギャップにより、承認されていないアプリケーションがデータ保護規則または組織のセキュリティ ポリシーに違反する可能性があるコンプライアンス リスクが生じます。

**修復アクション**

- [制限付きユーザーの同意設定を構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent) して、ユーザーの同意を無効にするか、リスクの低いアクセス許可のみを持つ検証済みの発行元に制限することで、不正な同意許可を防ぎます。

#### 管理者の同意ワークフローが有効になっている

Microsoft Entra テナントで管理者の同意ワークフローを有効にすると、管理者の同意を必要とするアプリケーションへのアクセスを必要とするユーザーは、完全にブロックされるのではなく、レビューの要求を送信できます。 ワークフローがないと、自分でアプリに同意できないユーザーは、個人アカウントや承認されていない代替手段の使用など、監視とセキュリティ保護が困難なシャドウ IT の回避策に頼る可能性があります。 ワークフローが有効になっている場合、同意要求はログに記録された監査可能なプロセスを経て、指定されたレビュー担当者に通知され、同意が付与される前に各要求を評価します。 これにより、ユーザーがどのアプリケーションにアクセスを要求しているかの可視性が向上し、昇格された権限は、監視なしでサイレント ブロックまたは許可されるのではなく、明示的に確認および承認されます。

**修復アクション**

管理者の同意要求の場合は、[ **ユーザーが同意できないアプリに対して管理者の同意を要求できる** ] を [ **はい**] に設定します。 要求をレビューできるユーザーなど、他の設定を指定します。

- [管理者の同意ワークフローを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow#enable-the-admin-consent-workflow)
- または、 [Update adminConsentRequestPolicy](https://learn.microsoft.com/ja-jp/graph/api/adminconsentrequestpolicy-update) API を使用して、 `isEnabled` プロパティを true やその他の設定に設定します。

#### 高いグローバル管理者と特権ユーザーの比率

組織は、特権を持つユーザーの総数に対してグローバル管理者の比率が過度に高い比率を維持している場合、脅威アクターがさまざまな攻撃ベクトルを通じて悪用する可能性がある重大なセキュリティ リスクに自分自身を公開します。 グローバル管理者の過剰な割り当てにより、資格情報の侵害、フィッシング攻撃、またはインサイダー脅威を通じて初期アクセスを利用して、Microsoft Entra IDテナント全体と接続されたMicrosoft 365 サービスに無制限にアクセスできる脅威アクターに対して、複数の価値の高いターゲットが作成されます。

**修復アクション**

- [グローバル管理者ロールの割り当ての数を最小限に抑える](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/best-practices#5-limit-the-number-of-global-administrators-to-less-than-5)

#### セキュリティ侵害を防ぐために、管理者特権が厳密に制限されている

グローバル管理者やグローバル セキュリティで保護されたアクセス管理者などのロールが過剰に割り当てられていると、脅威アクターがこれらの ID を侵害するためのパスが作成されます。 これらのロールを使用すると、攻撃者は認証、セキュリティ ポリシーの操作、アカウントの作成または昇格、監視の無効化、すべての企業データへのアクセスなどを行うことができます。 これらのロールへのアクセスを少数の管理者に制限し、グループ、ゲスト、サービス プリンシパル、および無効なアカウントの割り当てとアクティブ化の監視を有効にして、攻撃対象領域を減らし、最小限の特権を適用します。

**修復アクション**

- [緊急アクセス アカウントが適切に構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#emergency-access-accounts-are-configured-appropriately)
- [グローバル管理者とグローバル セキュリティで保護されたアクセス管理者ロールの割り当てを少数の管理者に制限します。](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#high-global-administrator-to-privileged-user-ratio)
- [グローバル管理者のアクティブ化の承認を要求するようにロール設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)

#### アプリケーション管理者権限は、特定のプライベート アクセス アプリに制限されます

テナント レベルでスコープが設定されたアプリケーション管理者ロールは、すべてのアプリ登録とエンタープライズ アプリケーションを管理できます。 脅威アクターは、テナント全体のスコープでアプリケーション管理者を侵害した場合、任意のサービス プリンシパルに資格情報を追加し、悪意のある API に同意し、データ流出を有効にするアプリケーションを変更または作成し、Private Access アプリを無効または改ざんすることができます。 必要な Private Access エンタープライズ アプリのみにロールをスコープすると、最小限の特権が適用され、ブラスト半径が制限されます。

アプリケーション管理者の割り当てを特定のアプリにスコープ設定しない場合:

- 侵害されたアプリケーション管理者は、テナント内のすべてのアプリ登録とエンタープライズ アプリケーションを管理できます。
- 脅威アクターは、任意のサービス プリンシパルに資格情報を追加して、永続化と横移動を有効にすることができます。
- 爆発半径の封じ込めはありません。1 つの侵害された ID が、すべてのアプリケーションに影響を与える可能性があります。

**修復アクション**

- テナント全体ではなく[、特定のアプリ登録にスコープを設定したアプリケーション管理者ロールを割り当てます](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-enterprise-app-permissions)。
- [必要なタスクを実行するために必要な最小特権でMicrosoft Entraロールを割り当てます。](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)
- [Privileged Identity Managementを使用して、ジャストインタイムロールのアクティブ化を管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)。
- [Microsoft Entra 管理センターでのロール割り当ての管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)。

#### 特権アカウントはクラウド ネイティブ ID です

オンプレミスアカウントが侵害され、Microsoft Entraに同期された場合、攻撃者はテナントにもアクセスできる可能性があります。 このリスクが高まるのは、オンプレミス環境では、通常、古いインフラストラクチャと限られたセキュリティ制御により、より多くの攻撃領域があるためです。 また、攻撃者は、オンプレミス環境とMicrosoft Entra間の接続を有効にするために使用されるインフラストラクチャとツールを対象とする可能性もあります。 これらのターゲットには、Microsoft Entra Connect や Active Directory フェデレーション サービス (AD FS) などのツールが含まれる場合があります。このツールは、他のオンプレミス ユーザー アカウントを偽装または操作できます。

特権クラウド アカウントがオンプレミス アカウントと同期されている場合、オンプレミスの資格情報を取得した攻撃者は、同じ資格情報を使用してクラウド リソースにアクセスし、クラウド環境に横方向に移動できます。

**修復アクション**

- [オンプレミス攻撃からのMicrosoft 365の保護](https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks#specific-security-recommendations)

高い特権 (永続的に割り当てられたロールまたはMicrosoft Entra Privileged Identity Managementを通じて資格のあるロール) ごとに、次のアクションを実行する必要があります。

- onPremisesImmutableId と onPremisesSyncEnabled が設定されているユーザーを確認します。 「[Microsoft Graph API ユーザー リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/user)を参照してください。
- これらの個人のクラウド専用ユーザー アカウントを作成し、特権ロールからハイブリッド ID を削除します。

#### すべての特権ロールの割り当ては、その時に必要に応じてアクティブになり、常時アクティブになることはありません。

脅威アクターは、必要なデータとリソースにアクセスできるため、特権アカウントを対象とします。 これには、Microsoft Entra テナントへのより多くのアクセス、Microsoft SharePoint内のデータ、または長期的な永続化を確立する機能が含まれる場合があります。 Just-In-Time (JIT) アクティブ化モデルがないと、管理者特権は継続的に公開され続け、攻撃者は検出されずに運用するための延長期間が提供されます。 Just-In-Time アクセスでは、承認、正当な理由、条件付きアクセス ポリシーなどの追加の制御を使用して時間制限付き特権のアクティブ化を適用することでリスクを軽減し、リスクの高いアクセス許可が必要なときにのみ、限られた期間だけ付与されるようにします。 この制限により、攻撃面が最小化され、横方向の移動を妨げ、敵対者が予期せぬ時に特別な監視を受け、拒否され得る行動を取るように強います。 ジャストインタイムアクセスがない場合、侵害された管理者アカウントは攻撃者に無期限の制御権を与え、セキュリティ制御を無効にし、ログを消去し、隠密性を維持することで、侵害の影響を増幅させます。

Microsoft Entra Privileged Identity Management (PIM) を使用して、特権ロールの割り当てに対し、期間限定の適時アクセスを提供します。 Microsoft Entra ID ガバナンスのアクセス レビューを使用して、特権アクセスを定期的に確認し、継続的なニーズを確認します。

**修復アクション**

- [Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started) の使用を開始します
- [PIM でAzureリソースとMicrosoft Entra ロールのアクセス レビューを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)

#### すべてのMicrosoft Entra特権ロールの割り当ては PIM で管理されます

永続的に割り当てられた特権アカウントを侵害する脅威アクターは、影響の大きいディレクトリ操作に継続的にアクセスできます。 この拡張アクセスにより、攻撃者は永続的なバックドアを確立し、セキュリティ構成を変更し、監視システムを無効にすることができます。 時間制限付きのアクセス制御がない場合、侵害された特権アカウントは無期限のテナント制御を提供します。

資格のあるロールの割り当てをジャスト イン タイムでアクティブ化する必要があり、攻撃対象領域を減らし、攻撃者のドウェル時間を制限します。

**修復アクション**

- [Privileged Identity Management を使用して Microsoft Entra の特権ロールを管理します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started)

#### Passkey 認証方法が有効になっている

Microsoft Entra IDでパスキー認証が有効になっていない場合、組織は、フィッシング、資格情報の盗難、再生攻撃に対して脆弱なパスワードベースの認証方法に依存します。 攻撃者は、盗まれたパスワードを使用して初期アクセスを取得し、敵対者インザミドル (AiTM) 攻撃を通じて従来の多要素認証をバイパスし、トークンの盗難による永続的なアクセスを確立できます。

パスキーは、攻撃者がフィッシング、傍受、または再生できない暗号学的証明を用いることで、フィッシング攻撃に耐性がある認証を提供します。 パスキーを有効にすると、資格情報ベースの攻撃チェーンを可能にする基本的な脆弱性が排除されます。

**修復アクション**

- [パスキー認証方法を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-passkey-fido2#enable-passkey-fido2-authentication-method)方法について説明します。
- [フィッシングに強いパスワードレス認証の展開を計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)方法について説明します。

#### セキュリティ キーの証明が実施される

セキュリティ キーの構成証明が適用されていない場合、脅威アクターは、脆弱な認証ハードウェアまたは侵害された認証ハードウェアを悪用して、組織環境内で永続的なプレゼンスを確立する可能性があります。 構成証明の検証を行わないと、悪意のあるアクターは、ハードウェアに基づくセキュリティ制御をバイパスする未承認または偽造の FIDO2 セキュリティ キーを登録し、正当なセキュリティ キーを模倣する認証子を使用して資格情報の詰め込み攻撃を実行できます。 この初期アクセスにより、脅威アクターは信頼できるハードウェア認証方法を利用して特権をエスカレートし、高特権アカウントに侵害されたセキュリティキーを登録することで、環境内を横展開することができます。 構成証明の適用が欠如すると、脅威アクターが永続的なハードウェア ベースの認証方法を使用してコマンドと制御を確立するための経路が作成され、最終的には、攻撃チェーン全体で正当なハードウェアで保護された認証の外観を維持しながら、データ流出やシステムの侵害につながります。

**修復アクション**

- [認証方法ポリシーの設定で認証の強制を有効化します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-passkey-fido2#enable-passkey-fido2-authentication-method)。
- [Authenticator Attestation Globally Unique Identifier (AAGUID) セキュリティキーの承認済みリストを構成します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-hardware-vendor)。

#### 特権アカウントには、フィッシングに対する耐性のある方法が登録されている

Microsoft Entra IDでパスキー認証が有効になっていない場合、組織は、フィッシング、資格情報の盗難、再生攻撃に対して脆弱なパスワードベースの認証方法に依存します。 攻撃者は、盗まれたパスワードを使用して初期アクセスを取得し、敵対者インザミドル (AiTM) 攻撃を通じて従来の多要素認証をバイパスし、トークンの盗難による永続的なアクセスを確立できます。

パスキーは、攻撃者がフィッシング、傍受、または再生できない暗号学的証明を用いることで、フィッシング攻撃に耐性がある認証を提供します。 パスキーを有効にすると、資格情報ベースの攻撃チェーンを可能にする基本的な脆弱性が排除されます。

**修復アクション**

- [パスキー認証方法を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-passkey-fido2#enable-passkey-fido2-authentication-method)方法について説明します。
- [フィッシングに強いパスワードレス認証の展開を計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)方法について説明します。

#### 特権 Microsoft Entra 組み込みロールが、条件付きアクセス ポリシーの適用対象としてフィッシングに強い方法を適用する

フィッシングに強い認証方法がないと、特権ユーザーはフィッシング攻撃に対して脆弱になります。 このような攻撃により、ユーザーは資格情報を明らかにして、攻撃者に不正アクセス権を付与します。 フィッシング対策以外の認証方法を使用すると、攻撃者は敵対者の中間攻撃などの方法を通じて資格情報とトークンを傍受し、特権アカウントのセキュリティを損なう可能性があります。

脆弱な認証方法によって特権アカウントまたはセッションが侵害されると、攻撃者はアカウントを操作して長期的なアクセスを維持したり、他のバックドアを作成したり、ユーザーのアクセス許可を変更したりする可能性があります。 攻撃者は、侵害された特権アカウントを使用してアクセスをさらにエスカレートさせ、より機密性の高いシステムを制御する可能性もあります。

**修復アクション**

- [フィッシングに強いパスワードレス認証の展開を始める](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)
- [特権アカウントがフィッシング詐欺に強い方法を登録して使用することを確認してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths#authentication-strengths)
- [条件付きアクセス ポリシーを展開して特権アカウントを対象とし、フィッシング詐欺に強い資格情報を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-admin-phish-resistant-mfa)
- [認証方法のアクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report#authentication-methods-activity) を監視する

#### 条件付きアクセス ポリシーでは、プライベート アプリに強力な認証が適用されます

条件付きアクセス ポリシーが強力な認証を要求してプライベート アクセス アプリケーションを保護しない場合、脅威アクターはフィッシング攻撃、資格情報の詰め込み、またはパスワード スプレーを使用してユーザーの資格情報を取得し、侵害されたパスワードだけでプライベート アプリケーションにサインインできます。

強力な認証がない場合:

- 脅威アクターは、より強力な制御によって保護する必要がある内部リソースへの初期アクセスを取得します。
- 多要素認証が欠落しているか、SMS や音声などのフィッシング可能な方法が使用されている場合、脅威アクターが認証トークンとセッション Cookie を傍受する場合に、敵対者の中間攻撃が発生する可能性があります。
- 脅威アクターは、最初に侵害されたプライベート アプリケーションから他の内部リソースに横方向に移動できます。

Microsoftでは、プライベート アプリケーションにアクセスするための FIDO2 セキュリティ キー、Windows Hello for Business、証明書ベースの認証など、フィッシングに強い認証方法を適用し、多要素認証を最小ベースラインとして使用することをお勧めします。

**修復アクション**

- [フィッシング対策認証を要求するように条件付きアクセス ポリシーを構成](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)します。

#### アプリケーション プロキシアプリケーションでは、匿名アクセスをブロックするために事前認証が必要です

アプリケーション プロキシ アプリケーションで事前認証Microsoft Entra構成しないと、脅威アクターは、最初に ID を証明することなく、発行されたオンプレミス アプリケーションの内部 URL に直接到達できます。 パススルー認証を使用すると、アプリケーション プロキシは要求者を検証せずにトラフィックを転送し、すべての認証責任が内部アプリケーションに渡されます。

アプリケーション プロキシ アプリケーションで事前認証を構成しない場合:

- 脅威アクターは、ID 検証なしで内部アプリケーション エンドポイントにアクセスでき、バックエンドの脆弱性の偵察と悪用を可能にします。
- 条件付きアクセス ポリシーは適用できないため、多要素認証を要求したり、サインイン リスクを評価したり、場所ベースの制限を適用したりすることはできません。
- リアルタイムのセッション監視と制御のためにMicrosoft Defender for Cloud Appsと統合することはできません。

**修復アクション**

- [アプリケーション プロキシ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application#add-an-on-premises-app-to-microsoft-entra-id)の事前認証を構成するには、事前認証方法を **Passthrough** から **Microsoft Entra ID** に変更します。
- [Microsoft Graph API を使用して、プログラムによってアプリケーション プロキシ設定](https://learn.microsoft.com/ja-jp/graph/application-proxy-configure-api)を更新します。

#### 管理者ロールにパスワード リセット通知を要求する

Microsoft Entra IDで管理者ロールのパスワード リセット通知を構成すると、別の管理者がパスワードをリセットしたときに特権管理者に通知することで、セキュリティが強化されます。 この可視性は、資格情報の侵害や内部関係者の脅威を示す可能性がある、承認されていないアクティビティまたは疑わしいアクティビティを検出するのに役立ちます。 これらの通知がなければ、悪意のあるアクターは昇格された特権を利用して永続化を確立したり、アクセスをエスカレートしたり、機密データを抽出したりする可能性があります。 プロアクティブ通知では、クイック アクションがサポートされ、特権アクセスの整合性が維持され、全体的なセキュリティ体制が強化されます。

**修復アクション**

- [他の管理者が自分のパスワードをリセットしたときにすべての管理者に通知する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks#notify-all-admins-when-other-admins-reset-their-passwords)

#### レガシ認証をブロックするポリシーが設定されています。

SMTP や IMAP の基本認証などのレガシ認証プロトコルでは、多要素認証 (MFA) などの最新のセキュリティ機能はサポートされていません。これは、未承認のアクセスから保護するために重要です。 この保護の欠如により、これらのプロトコルを使用するアカウントはパスワードベースの攻撃に対して脆弱になり、攻撃者は盗まれた資格情報または推測された資格情報を使用して初期アクセスを取得する手段を提供します。

攻撃者が資格情報への不正アクセスに成功すると、脆弱な認証方法をエントリ ポイントとして使用して、それらを使用してリンクされたサービスにアクセスできます。 レガシ認証によってアクセスを取得した攻撃者は、メール転送ルールの構成や他の設定の変更など、Microsoft Exchangeに変更を加え、機密性の高い通信への継続的なアクセスを維持できる可能性があります。

また、レガシ認証では、セキュリティ アラートをトリガーしたり、再認証を要求したりすることなく、侵害された資格情報を使用してシステムを再入力する一貫した方法を攻撃者に提供します。

そこから、攻撃者はレガシ プロトコルを使用して、侵害されたアカウントを介してアクセスできる他のシステムにアクセスし、横移動を促進できます。 レガシ プロトコルを使用する攻撃者は、正当なユーザー アクティビティと組み合わせて、セキュリティ チームが通常の使用と悪意のある動作を区別することが困難になります。

**修復アクション**

- [条件付きアクセス ポリシーを展開してレガシ認証をブロックする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication)

#### 一時的なアクセス パスが有効になっている

一時アクセス パス (TAP) を有効にしないと、組織はユーザーの資格情報を安全にブートストラップするという大きな課題に直面し、初期セットアップ時にユーザーが弱い認証メカニズムに依存する脆弱性が生じます。 既存の強力な認証方法がないために、FIDO2 セキュリティ キーやWindows Hello for Businessなどのフィッシングに強い資格情報を登録できない場合、フィッシング、パスワード スプレー、または同様の攻撃を含む資格情報ベースの攻撃にさらされたままです。 脅威アクターは、最も脆弱な状態の間にユーザーをターゲットにすることで、この登録ギャップを悪用する可能性があります。これは、使用可能な認証オプションが限られており、従来のユーザー名とパスワードの組み合わせに依存する必要がある場合です。 この公開により、脅威アクターは重要なブートストラップ フェーズ中にユーザー アカウントを侵害し、より強力な認証方法の登録プロセスを傍受または操作できるようになり、最終的には組織のリソースへの永続的なアクセスが得られ、セキュリティ制御が完全に確立される前に特権がエスカレートされる可能性があります。

TAP を有効にし、セキュリティ情報の登録と組み合わせて使用することで、防御の潜在的なギャップを保護します。

**修復アクション**

- [認証方法ポリシーで一時アクセス パスを有効にする方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#enable-the-temporary-access-pass-policy)
- [一時アクセス パスを含むように認証強度ポリシーを更新する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-advanced-options)
- [認証強度を適用してセキュリティ情報を登録するための条件付きアクセス ポリシーを作成する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)

#### 一時アクセス パスを 1 回の使用に制限する

複数の使用を許可するように一時アクセス パス (TAP) が構成されている場合、資格情報を侵害した脅威アクターは、その有効期間中に繰り返し再利用でき、意図した 1 回のブートストラップ イベントを超えて未承認のアクセス ウィンドウが拡張されます。 この状況により、資格情報の有効期間中に侵害されたアカウントの下に追加の強力な認証方法を登録することで、脅威アクターが永続化を確立する機会が広がります。 不正な手に渡る再利用可能な TAP を使用すると、脅威アクターは複数のセッションにわたり偵察活動を行い、環境のマッピングを徐々に行いながら、正当なアクセスパターンを維持して価値の高いターゲットを識別することができます。 侵害された TAP は信頼性の高いバックドア メカニズムとしても機能し、セキュリティ ログに正当な管理ツールとして表示されるため、他の侵害された資格情報が検出および取り消された場合でも、脅威アクターがアクセスを維持できます。

**修復アクション**

- [認証方法ポリシーで 1 回限り使用するための一時アクセス パスの構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#enable-the-temporary-access-pass-policy)

#### 従来の MFA ポリシーと SSPR ポリシーから移行する

Microsoft Entra IDのレガシ多要素認証 (MFA) ポリシーとセルフサービス パスワード リセット (SSPR) ポリシーは、認証方法を個別に管理し、断片化された構成と最適でないユーザー エクスペリエンスを実現します。 さらに、これらのポリシーを個別に管理すると、管理オーバーヘッドと構成ミスのリスクが増加します。

統合された認証方法ポリシーに移行すると、MFA、SSPR、パスワードレス認証方法の管理が 1 つのポリシー フレームワークに統合されます。 この統合により、より詳細な制御が可能になり、管理者は特定の認証方法をユーザー グループに対してターゲットにし、組織全体で一貫したセキュリティ対策を適用できます。 さらに、統合ポリシーでは、FIDO2 セキュリティ キーやWindows Hello for Businessなどの最新の認証方法がサポートされ、組織のセキュリティ体制が強化されます。

Microsoft、2025 年 9 月 30 日に廃止日が設定された、従来の MFA および SSPR ポリシーの廃止を発表しました。 組織は、中断の可能性を回避し、統合ポリシーの強化されたセキュリティと管理機能の恩恵を受けるために、この日付より前に認証方法ポリシーへの移行を完了することをお勧めします。

**修復アクション**

- [統合されたセキュリティ情報の登録を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined)
- MFA および SSPR ポリシー設定を Microsoft Entra ID

#### 管理者による SSPR の使用をブロックする

管理者 Self-Service パスワード リセット (SSPR) を使用すると、強力なセカンダリ認証要素や管理上の監視なしでパスワードの変更を行うことができます。 管理者資格情報を侵害する脅威アクターは、この機能を使用して、他のセキュリティ制御をバイパスし、環境への永続的なアクセスを維持できます。

侵害されると、攻撃者はすぐにパスワードをリセットして正当な管理者をロックアウトできます。 その後、永続化を確立し、特権をエスカレートし、検出されない悪意のあるペイロードをデプロイできます。

**修復アクション**

- [承認ポリシーを更新して管理者の SSPR を無効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy#administrator-reset-policy-differences)

#### セルフサービス パスワード リセットではセキュリティに関する質問は使用されません

セキュリティの質問をセルフサービス パスワード リセット (SSPR) メソッドとして許可すると、回答が推測可能で、サイト間で再利用されたり、オープンソース インテリジェンス (OSINT) によって検出されたりすることが多いため、パスワード リセット プロセスが弱まります。 脅威アクターは、ユーザーを列挙またはフィッシングし、可能性の高い応答 (家族名、学校、場所) を導き出した後、脆弱な知識ベースのゲートを利用して、より強力な方法をバイパスするためにパスワード リセット フローをトリガーします。 多要素認証によって保護されていないアカウントのパスワードを正常にリセットすると、有効なプライマリ資格情報の取得、セッション トークンの確立、永続的な認証方法の登録、転送規則の追加、機密データの流出によって横方向に拡張することができます。

この方法を削除すると、パスワード リセット プロセスの弱いリンクが削除されます。 一部の組織では、セキュリティの質問を有効のままにするための特定のビジネス上の理由がある場合がありますが、これはお勧めしません。

**修復アクション**

- [SSPR ポリシーでセキュリティの質問を無効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-security-questions)
- [認証方法と登録オプションを選択する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr#select-authentication-methods-and-registration-options)

#### SMS と音声通話の認証方法が無効になっている

SMS や音声通話などの脆弱な認証方法がMicrosoft Entra IDで有効のままである場合、脅威アクターは複数の攻撃ベクトルを介してこれらの脆弱性を悪用する可能性があります。 最初は、攻撃者は多くの場合、ソーシャル エンジニアリングまたは技術スキャンを通じて、これらの弱い認証方法を使用して組織を特定するために偵察を行います。 その後、ユーザーの資格情報を対象とする資格情報詰め込み攻撃、パスワード スプレー、またはフィッシング キャンペーンを通じて、初期アクセスを実行できます。

基本的な資格情報が侵害されると、脅威アクターは SMS と音声ベースの認証でこれらの弱点を使用します。 SMS メッセージは、SIM スワップ攻撃、SS7 ネットワークの脆弱性、モバイル デバイス上のマルウェアによって傍受される可能性があります。一方、音声通話は音声フィッシング (ビッシング) や着信転送操作の影響を受けやすくなります。 これらの弱い第 2 の要因がバイパスされると、攻撃者は独自の認証方法を登録することで永続化を実現します。 侵害されたアカウントを使用して、内部フィッシングまたはソーシャル エンジニアリングを通じて高い特権を持つユーザーをターゲットにすることができ、攻撃者は組織内の特権をエスカレートできます。 最後に、脅威アクターは、セキュリティ ログに通常表示される正当な認証経路を使用してステルスを維持しながら、データ流出、重要なシステムへの横移動、または他の悪意のあるツールの展開を通じて目標を達成します。

**修復アクション**

- [認証方法の登録キャンペーンを展開して、より強力な方法を促進する](https://learn.microsoft.com/ja-jp/graph/api/authenticationmethodspolicy-update?view=graph-rest-beta&preserve-view=true)
- [認証方法を無効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage)
- [従来の MFA 設定で電話ベースの方法を無効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings)
- [認証強度を使用して条件付きアクセス ポリシーを展開する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-how-it-works)

#### 使用がない場合はシームレス SSO を無効にする

Microsoft Entraシームレス シングル サインオン (シームレス SSO) は、ハイブリッド Microsoft Entra ID参加していないドメイン参加済みデバイスにパスワードレス アクセスを提供するように設計されたレガシ認証機能です。 シームレス SSO は Kerberos 認証に依存しており、主にプライマリ更新トークン (PRT) をサポートしていないWindows 7やWindows 8.1などの古いオペレーティング システムに役立ちます。 これらのレガシ システムが環境内に存在しなくなった場合、シームレス SSO を引き続き使用すると、不要な複雑さと潜在的なセキュリティの露出が生じます。 脅威アクターは、正しく構成されていない Kerberos チケットまたは古い Kerberos チケットを悪用したり、Microsoft Entra IDで使用される Kerberos 復号化キーを保持するActive Directoryの `AZUREADSSOACC` コンピューター アカウントを侵害したりする可能性があります。 侵害されると、攻撃者はユーザーを偽装し、最新の認証制御をバイパスし、クラウド リソースへの不正アクセスを取得する可能性があります。 不要になった環境でシームレス SSO を無効にすると、攻撃対象領域が減少し、より強力な保護を提供する最新のトークンベースの認証メカニズムの使用が強制されます。

**修復アクション**

- [シームレス SSO のしくみを確認する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-how-it-works)
- [シームレス SSO を無効にする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-faq#how-can-i-disable-seamless-sso-)
- Microsoft Entra ID

#### MFA 登録のセキュリティ保護 (マイ セキュリティ情報) ページ

セキュリティ情報の登録を保護する条件付きアクセス ポリシーがない場合、脅威アクターは保護されていない登録フローを悪用して認証方法を侵害する可能性があります。 ユーザーが適切な制御なしで多要素認証とセルフサービスパスワードリセット方法を登録すると、脅威アクターは敵対者の中間攻撃を通じてこれらの登録セッションを傍受したり、信頼されていない場所から登録にアクセスする管理されていないデバイスを悪用したりすることができます。 脅威アクターは、保護されていない登録フローにアクセスすると、独自の認証方法を登録し、ターゲットの認証プロファイルを効果的にハイジャックできます。 脅威アクターは、MFA メソッドを制御することで永続的なアクセスを維持できるため、セキュリティ制御をバイパスし、環境全体で特権をエスカレートする可能性があります。 侵害された認証方法は、脅威アクターが複数のサービスとアプリケーションにわたって正当なユーザーとして認証できるため、横移動の基礎になります。

**修復アクション**

- [セキュリティ情報登録用の条件付きアクセス ポリシーを展開する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)
- [既知のネットワークの場所を構成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)
- [統合されたセキュリティ情報の登録を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined)

#### クラウド認証を使用する

オンプレミスのフェデレーション サーバーは、クラウド アプリケーションの中央認証ポイントとして機能することで、重要な攻撃対象領域を導入します。 多くの場合、脅威アクターは、フィッシング、資格情報の詰め込み、脆弱なパスワードの悪用などの攻撃を通じて、ヘルプ デスクの担当者や運用エンジニアなどの特権を持つユーザーを侵害することで、足掛かりを得ることができます。 また、インフラストラクチャの修正プログラムが適用されていない脆弱性をターゲットにしたり、リモートコード実行の悪用を使用したり、Kerberos プロトコルを攻撃したり、ハッシュパス攻撃を使用して特権をエスカレートしたりする可能性もあります。 リモート デスクトップ プロトコル (RDP)、仮想プライベート ネットワーク (VPN)、ジャンプ サーバーなどの正しく構成されていないリモート アクセス ツールは、他のエントリ ポイントを提供しますが、サプライ チェーンの侵害や悪意のある内部関係者はさらに露出を増やします。 内部に入ると、脅威アクターは認証フローを操作し、セキュリティ トークンを偽造して任意のユーザーを偽装し、クラウド環境にピボットすることができます。 永続化を確立すると、セキュリティ ログを無効にし、検出を回避し、機密データを流出させることができます。

**修復アクション**

- [Microsoft Entra のパスワード ハッシュ同期 (PHS) など、フェデレーションからクラウド認証に移行します](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication)。

#### すべてのユーザーが MFA に登録する必要がある

すべてのユーザーに多要素認証 (MFA) の登録を要求します。 調査に基づいて、MFA を使用している場合、アカウントは 99% 以上侵害される可能性が低くなります。 常に MFA を必要としない場合でも、このポリシーにより、必要なときにユーザーの準備が整います。

**修復アクション**

- [多要素認証登録ポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy)

#### ユーザーに強力な認証方法が構成されている

多要素認証 (MFA) がユニバーサルに適用されていない場合、または例外が発生した場合、攻撃者はアクセスを得る可能性があります。 攻撃者は、ソーシャル エンジニアリング手法を使用して、SMS や電話などの弱い MFA メソッドの脆弱性を悪用してアクセスを取得する可能性があります。 これらの手法には、認証コードをインターセプトするための SIM スワップやフィッシングが含まれる場合があります。

攻撃者は、これらのアカウントをテナントへのエントリ ポイントとして使用する可能性があります。 傍受されたユーザー セッションを使用することで、攻撃者はアクティビティを正当なユーザー アクションとして偽装し、検出を回避し、疑いを引き起こさずに攻撃を続行できます。 そこから、MFA 設定を操作して、侵害されたアカウントの特権に基づいて永続化を確立し、計画し、さらに攻撃を実行しようとする可能性があります。

**修復アクション**

- [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted) を展開する
- [フィッシングに強いパスワードレス認証の展開を始める](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)
- [条件付きアクセス ポリシーをデプロイして、すべてのユーザーに対してフィッシングに強い MFA を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)
- [認証方法アクティビティの](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report?tabs=microsoft-entra-admin-center#authentication-methods-activity) を確認する

#### ユーザーに表示されるパスワードの領域を減らす

広範なユーザー向けパスワード サーフェスを持つ組織は、資格情報ベースの攻撃に対して複数のエントリ ポイントを公開します。 脅威アクターは、多くの場合、データ侵害で漏洩した資格情報を使ったクレデンシャルスタッフィングから開始し、続いて複数のアカウントに対して共通のパスワードを試すパスワードスプレー攻撃を行います。 初期アクセスが取得されたら、ブラウザーのパスワード ストア、メモリ内のキャッシュされた資格情報、資格情報マネージャーを調べて、追加の認証資料を収集することで、資格情報の検出を行います。 これらの盗まれた資格情報により、より多くのシステムやアプリケーションへの横移動が可能になり、多くの場合、パスワード認証に依存している管理アカウントをターゲットにすることで特権がエスカレートされます。

Windows Hello for Business、FIDO2 セキュリティ キー、Microsoft Authenticatorなど、パスワードレスの方法が広く展開されていない場合、すべてのパスワード プロンプトは傍受と悪用の機会となります。 パスワードの領域を減らすと、これらの攻撃ベクトルが制限され、資格情報ベースの脅威に対する全体的な露出が減少します。

**修復アクション**

- [フィッシングに強いパスワードレス認証の展開を計画します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)。
- [パスキー (FIDO2) を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-passkey-fido2)。
- [Configure Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/)。

#### ユーザー サインイン アクティビティでトークン保護を使用する

脅威アクターは、メモリ、正当なデバイス上のローカル ストレージ、またはネットワーク トラフィックを検査することによって、認証トークンを傍受または抽出できます。 攻撃者は、これらのトークンを再生して、ユーザーとデバイスの認証制御をバイパスしたり、機密データへの未承認のアクセスを取得したり、さらに攻撃を実行したりする可能性があります。 これらのトークンは有効で期限切れであるため、従来の異常検出ではアクティビティにフラグを設定できないことがよくあります。これにより、トークンの有効期限が切れるか取り消されるまで、継続的なアクセスが許可される可能性があります。

トークン保護 (トークン バインディングとも呼ばれます) は、トークンが目的のデバイスからのみ使用できることを確認することで、トークンの盗難を防ぐのに役立ちます。 トークン保護では暗号化が使用されるため、クライアント デバイス キーがないと、誰もトークンを使用できなくなります。

**修復アクション**

- [条件付きアクセス ポリシーを展開してトークン保護を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection)

#### トークン保護ポリシーが構成されている

トークン保護条件付きアクセス ポリシーがない場合、サインイン セッション トークンを盗む脅威アクターは、任意のデバイスからそれらを再生して、リソースへの不正アクセスを取得できます。 トークンの盗難により、盗まれたトークンは既に有効な ID 証明であるため、攻撃者は認証を完全にバイパスできます。 このアクセス ベクターにより、再認証の課題をトリガーすることなく、横移動、特権エスカレーション、およびデータ流出が可能になります。

Microsoft Entra IDのトークン保護は、サインイン セッション トークンを最初に発行されたデバイスにバインドし、攻撃者が制御するデバイスで使用できない盗まれたトークンをレンダリングします。 Windowsおよび Apple プラットフォームに対して条件付きアクセス トークン保護ポリシーを構成すると、SharePoint Online や Exchange Online などの重要なサービスのサインイン セッション トークンを承認されていないエンドポイントから再生できなくなります。

**修復アクション**

- [条件付きアクセスでトークン保護を構成します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection#create-a-conditional-access-policy)。

#### すべてのユーザー サインイン アクティビティでフィッシングに強い認証方法を使用する

パスキーや FIDO2 セキュリティ キーなどのフィッシングに強い認証方法は、資格情報の盗難や高度なフィッシング攻撃に対する最も強力な保護を提供します。 従来の MFA メソッドは、敵対者の中間攻撃やソーシャル エンジニアリングに対して脆弱なままです。 条件付きアクセス ポリシーを使用して、すべてのユーザーに対してフィッシングに強い方法を適用すると、攻撃者が認証フローを傍受しようとした場合でも、承認されていないアクセスを防ぐことができます。

**修復アクション**

- [MFA 強度を持つすべてのユーザーの条件付きアクセスを構成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)
- [フィッシングに強いパスワードレス認証を展開する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication)

#### すべてのサインイン アクティビティはマネージド デバイスから取得されます

マネージド デバイスからのサインインを要求すると、ユーザーはセキュリティとコンプライアンスの要件を満たすデバイスからのみ組織のリソースにアクセスできます。 管理されていないデバイスには、組織のセキュリティ制御とエンドポイント保護がないため、攻撃者のエントリ ポイントが生じる可能性があります。 条件付きアクセスを使用して、準拠またはMicrosoft Entraハイブリッド参加済みデバイスを要求すると、信頼されていないエンドポイントからの資格情報の盗難や未承認のアクセスから保護できます。

**修復アクション**

- [条件付きアクセスを使用して準拠しているデバイスまたはハイブリッド参加済みデバイスを要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)
- Microsoft Intune

#### セキュリティ キーの認証方法が有効になっている

FIDO2 セキュリティ キーは、資格情報の盗難や未承認のアクセスから保護する、ハードウェアベースのフィッシング対策認証を提供します。 セキュリティ キーは、特定のデバイスにバインドされた ID の暗号証明を使用するため、資格情報をレプリケートしたりフィッシングしたりすることができなくなります。 この認証方法を有効にすると、ユーザーは強力なパスワードレス認証用のセキュリティ キーを登録できます。

**修復アクション**

- [FIDO2 セキュリティ キーの認証方法を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-passkey-fido2#enable-passkey-fido2-authentication-method)
- [認証方法を管理する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage)

#### 特権ロールが古い ID に割り当てられていません

特権ロールは、最近サインイン活動がないIDに割り当てられたままにしないでください。 管理特権を持つ古いアカウントは、行動分析アラートをトリガーすることなく侵害される可能性があるため、攻撃者にとって魅力的なターゲットです。 非アクティブな ID から特権ロールの割り当てを定期的に確認して削除すると、資格情報ベースの攻撃のリスクが軽減され、最小限の特権アクセスを維持するのに役立ちます。

**修復アクション**

- [アクセス レビューを使用して特権ロールの割り当てを確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)
- [非アクティブな ID から特権ロールの割り当てを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles#update-or-remove-an-existing-role-assignment)
- [特権ロールの自動アクセス レビューを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)

#### デバイス コード フローを制限する

デバイス コード フローは、入力制約のあるデバイス用に設計されたクロスデバイス認証フローです。 フィッシング攻撃で悪用される可能性があります。この攻撃では、攻撃者がフローを開始し、ユーザーが自分のデバイスでフローを完了するように誘導し、それによってユーザーのトークンを攻撃者に送信します。 セキュリティ リスクとデバイス コード フローの正当な使用方法が少ない場合は、条件付きアクセス ポリシーを有効にして、既定でこのフローをブロックする必要があります。

**修復アクション**

- [条件付きアクセス ポリシーを展開して、デバイス コード フローをブロックします](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-authentication-flows#device-code-flow-policies)。

#### 認証の転送がブロックされている

Microsoft Entra IDでの認証転送のブロックは、重要なセキュリティ制御です。 これは、デバイス トークンを使用して他のデバイスまたはブラウザーでサイレント認証を行わないようにすることで、トークンの盗難やリプレイ攻撃から保護するのに役立ちます。 認証転送が有効になっている場合、1 つのデバイスにアクセスする脅威アクターは、標準の認証とデバイス コンプライアンスチェックをバイパスして、承認されていないデバイスへのリソースにアクセスできます。 管理者がこのフローをブロックすると、組織は、各認証要求が元のデバイスから送信され、デバイス コンプライアンスとユーザー セッション コンテキストの整合性を維持する必要があることを確認できます。

**修復アクション**

- [条件付きアクセス ポリシーを展開して認証の転送をブロックする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-authentication-flows#authentication-transfer-policies)

#### Microsoft Authenticator アプリはサインインのコンテキストを表示します。

サインイン コンテキストがないと、脅威アクターはユーザーにプッシュ通知が殺到し、ユーザーが誤って悪意のある要求を承認する可能性が高まり、認証の疲労を悪用する可能性があります。 ユーザーがアプリケーション名または地理的な場所を持たない汎用プッシュ通知を受け取った場合、情報に基づく承認の決定を行うために必要な情報がありません。 このようなコンテキストの欠如により、ユーザーはソーシャル エンジニアリング攻撃に対して脆弱になります。特に、脅威アクターが正当なユーザー アクティビティの期間中に要求を行う場合です。 この脆弱性は、脅威アクターが資格情報の収集またはパスワード スプレー攻撃によって初期アクセスを取得し、予期しないアプリケーションまたは場所からの多要素認証 (MFA) 要求を承認して永続化を確立しようとする場合に特に危険です。 コンテキスト情報がないと、ユーザーは通常とは異なるサインイン試行を検出できないため、脅威アクターは、最初の認証バリアをバイパスした後にシステムを横方向に移動することで、アクセスを維持し、特権をエスカレートできます。 アプリケーションと場所のコンテキストがないと、セキュリティ チームは、進行中の侵害または偵察アクティビティを示す可能性のある疑わしい認証パターンを検出するための貴重なテレメトリも失います。

**修復アクション** 情報に基づいて承認を決定するために必要なコンテキストをユーザーに提供します。 アプリケーション名と地理的な場所を含むように認証方法ポリシーを設定して、Microsoft Authenticator通知を構成します。

- [Authenticator 通知で追加のコンテキストを使用する - 認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-additional-context)

#### Microsoft Authenticator アプリの不審なアクティビティ レポート設定が有効になっています

脅威アクターは、迅速な爆弾攻撃とリアルタイムのフィッシング プロキシに依存して、ユーザーを強制的に処理したり、不正な多要素認証 (MFA) の課題を承認させたりします。 Microsoft Authenticator アプリの **Report suspicious activity** 機能が有効になっていないと、攻撃者は疲れたユーザーが受け入れるまで反復できます。 この種の攻撃は、特権のエスカレーション、永続化、機密性の高いワークロードへの横移動、データ流出、または破壊的なアクションにつながる可能性があります。

すべてのユーザーに対してレポートを有効にすると、予期しないプッシュまたは電話プロンプトにアクティブなフラグが設定され、ユーザーを高いユーザー リスクに直ちに昇格させ、リスクベースの条件付きアクセス ポリシーまたはその他の応答自動化を使用してセキュリティで保護された修復をブロックまたは要求できる、忠実度の高いユーザー リスク検出 (userReportedSuspiciousActivity) を生成できます。

**修復アクション**

- [Microsoft Authenticator アプリでレポートの不審なアクティビティの設定を可能にします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#report-suspicious-activity)

#### パスワードの有効期限が無効になっている

パスワードの有効期限ポリシーが有効なままの場合、脅威アクターは、ユーザーがパスワードを定期的に変更することを強制されたときに通常従う予測可能なパスワード ローテーション パターンを利用できます。 ユーザーは、数字の増分や連続した文字の追加など、既存のパスワードに最小限の変更を加えることで、脆弱なパスワードを作成する場合がよくあります。 脅威アクターは、資格情報の詰め込み攻撃や標的型パスワード スプレー キャンペーンを通じて、このような種類の変更を簡単に予測して利用できます。 これらの予測可能なパターンにより、脅威アクターは次の方法で永続化を確立できます。

- 侵害された資格情報
- 脆弱なローテーションされたパスワードを持つ管理アカウントをターゲットにして特権をエスカレートする
- 将来のパスワードのバリエーションを予測して長期的なアクセスを維持する

調査によると、ユーザーは有効期限が切れざるを得ない場合に、より弱く予測可能なパスワードを作成します。 これらの予測可能なパスワードは、経験豊富な攻撃者が、まったく新しい強力なパスワードを作成するのではなく、既存のパスワードを簡単に変更するため、解読しやすくなります。 さらに、ユーザーが頻繁にパスワードを変更する必要がある場合は、パスワードを書き留めたり、簡単にアクセスできる場所に保存したり、物理的な偵察やソーシャル エンジニアリング キャンペーン中に脅威アクターが悪用するための攻撃ベクトルを増やしたりするなど、安全でないプラクティスに頼る可能性があります。

**修復アクション**

- [組織のパスワード有効期限ポリシーを設定します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/set-password-expiration-policy)。
    - [Microsoft 365 管理センター](https://admin.microsoft.com/)にサインインします。 **設定**&gt;**組織の設定**&gt; \*\*セキュリティとプライバシー\*\* &gt;**パスワード有効期限のポリシー** に移動します。 [ **パスワードを無期限に設定する** ] 設定がオンになっていることを確認します。
- [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/domain-update?view=graph-rest-1.0&preserve-view=true) を使用してパスワードの有効期限を無効にする。
- Microsoft Graph PowerShell
    - `Update-MgUser -UserId <UserID> -PasswordPolicies DisablePasswordExpiration`

#### スマート ロックアウトしきい値を 10 以下に設定

スマート ロックアウトのしきい値が 10 を超える値に設定されている場合、脅威アクターは構成を利用して偵察を行い、ロックアウト保護をトリガーせずに有効なユーザー アカウントを特定し、検出せずに初期アクセスを確立できます。 攻撃者は、最初のアクセス権を取得したら、侵害されたアカウントを使用してリソースにアクセスし、特権をエスカレートすることで、環境を横方向に移動できます。

スマート ロックアウトは、ユーザーのパスワードを推測しようとしたり、ブルート フォースメソッドを使用して入ろうとする悪いアクターをロックアウトするのに役立ちます。 スマート ロックアウトは、有効なユーザーからのサインインを認識し、攻撃者やその他の不明なソースとは異なる方法で処理します。 しきい値が 10 を超える場合、自動パスワード スプレー攻撃に対する保護が不十分になり、脅威アクターは検出メカニズムを回避しながらアカウントを侵害しやすくなります。

**修復アクション**

- [Microsoft Entra のスマート ロックアウトしきい値を 10 以下に設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)。

#### スマート ロックアウト期間は最低でも60に設定されている

スマート ロックアウト期間が既定の 60 秒未満に構成されている場合、脅威アクターは、短いロックアウト期間を利用して、パスワード スプレーと資格情報の詰め込み攻撃をより効果的に実行できます。 ロックアウト ウィンドウを減らすと、攻撃者は認証の試行をより迅速に再開でき、成功確率が高まり、より長い観察期間に依存する検出システムが回避される可能性があります。

**修復アクション**

- [スマート ロックアウト期間を 60 秒以上に設定する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout#manage-microsoft-entra-smart-lockout-values)

#### 禁止パスワード リストに組織の用語を追加する

カスタム禁止パスワード リストを設定して適用していない組織は、脅威アクターが予測可能な組織のパスワード パターンを悪用する体系的な攻撃チェーンに自分自身を公開します。 これらの脅威アクターは通常、偵察フェーズから始まり、Web サイト、ソーシャル メディア、およびパブリック レコードからオープンソース インテリジェンス (OSINT) を収集して、パスワード コンポーネントの可能性を特定します。 この知識により、複数のユーザー アカウント間で組織固有のパスワードのバリエーションをテストするパスワード スプレー攻撃を開始し、検出を回避するためにロックアウトのしきい値を維持します。 カスタム禁止パスワード リストが提供する保護がなければ、従業員は多くの場合、場所、製品名、業界用語などの使い慣れた組織用語をパスワードに追加し、一貫した攻撃ベクトルを作成します。

カスタム禁止パスワード リストを使用すると、組織はこの重要なギャップを埋めて、環境内での最初のアクセスとその後の横移動につながる可能性がある簡単に推測されたパスワードを防ぐことができます。

**修復アクション**

- [カスタム禁止パスワード保護を有効にし、組織の用語を追加する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-configure-custom-password-protection)

#### ユーザー アクションを使用してデバイスの参加とデバイスの登録に多要素認証を要求する

脅威アクターは、新しいデバイス登録中に多要素認証の欠如を悪用する可能性があります。 認証されると、悪意のあるデバイスを登録し、永続化を確立し、信頼されたエンドポイントに関連付けられているセキュリティ制御を回避できます。 この足掛かりにより、攻撃者は、攻撃者が使用しているアカウントのアクセス許可に応じて、機密データを流出させたり、悪意のあるアプリケーションを展開したり、横方向に移動したりできます。 MFA の適用がなければ、敵対者が継続的に再認証し、検出を回避し、目標を実行できるため、リスクはエスカレートされます。

**修復アクション**

- [デバイス登録に多要素認証を要求する条件付きアクセス ポリシーを展開](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-registration)します。

#### ローカル管理者パスワード ソリューションがデプロイされている

ローカル管理者パスワード ソリューション (LAPS) がデプロイされていない場合、脅威アクターは静的なローカル管理者パスワードを悪用して初期アクセスを確立します。 脅威アクターは、共有ローカル管理者の資格情報を使用して 1 つのデバイスを侵害した後、環境全体を横方向に移動し、同じパスワードを共有する他のシステムに対して認証を行うことができます。 侵害されたローカル管理者アクセスにより、脅威アクターにシステム レベルの特権が与えられます。これにより、次のようなさまざまな悪意のあるアクティビティを実行できます。

- セキュリティコントロールを無効にする
- 永続的なバックドアをインストールする
- 機密データを流出させる
- コマンド チャネルと制御チャネルを確立する

LAPS の自動パスワード ローテーションと一元管理により、このセキュリティ ギャップが埋め、これらの重要なアカウントにアクセスできるユーザーを管理するのに役立つコントロールが追加されます。 LAPS のようなソリューションがないと、ローカル管理者アカウントの不正使用を検出したり、応答したりすることはできません。脅威アクターは、検出されずに目標を達成するためにドウェル時間を延長できます。

**修復アクション**

- [Windows ローカル管理者パスワードソリューションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-manage-local-admin-passwords)。

#### Entra Connect Sync がサービス プリンシパルの資格情報で構成されている

Microsoft Entra Connect Syncでサービス プリンシパルの代わりにユーザー アカウントを使用すると、セキュリティの脆弱性が発生します。 パスワードを使用したレガシ ユーザー アカウント認証は、証明書を使用したサービス プリンシパル認証よりも、資格情報の盗難やパスワード攻撃の影響を受けやすくなります。 侵害されたコネクタ アカウントを使用すると、脅威アクターは ID 同期の操作、バックドア アカウントの作成、特権のエスカレート、ハイブリッド ID インフラストラクチャの中断を行うことができます。

**修復アクション**

- [Entra Connect のサービス プリンシパル認証を構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/authenticate-application-id?tabs=default#onboard-to-application-based-authentication)
- [従来のディレクトリ同期アカウントを削除する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/authenticate-application-id?tabs=default#remove-a-legacy-service-account)

#### ディレクトリ同期アカウントが特定の名前付き場所にロックダウンされている

ディレクトリ同期アカウントは、オンプレミスの Active DirectoryとMicrosoft Entra IDの間の ID 同期を容易にする高い特権を持つサービス アカウントです。 場所ベースのアクセス制御がないと、これらのアカウントを侵害する脅威アクターは、承認されていないネットワークや地理的リージョンなど、任意の場所からの悪意のある変更を同期できます。

ディレクトリ同期アカウントが侵害されると、脅威アクターは次のことができます。

- ID 同期プロセスを操作する
- 承認されていないユーザー アカウントを作成する
- 既存のアカウントの特権をエスカレートする
- 同期規則を変更してアクセスを保持する

無制限のネットワーク アクセスにより、脅威アクターは侵害されたインフラストラクチャからリモートで操作できるため、ハイブリッド ID 環境への長期的なアクセスを維持しながら検出が困難になります。 条件付きアクセス ポリシーを使用してこれらのアカウントを信頼できる名前付き場所に制限すると、承認されたネットワークの場所からのみ同期操作が実行されるようにすることで、攻撃対象領域が制限されます。

**修復アクション**

- [条件付きアクセスを使用して場所によるアクセスをブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-by-location)します。

#### テナントで ADAL を使用しない

Microsoftは、2023 年 6 月 30 日に ADAL のサポートとセキュリティ修正プログラムを終了しました。 ADAL を継続的に使用すると、条件付きアクセスの適用、継続的アクセス評価 (CAE)、高度なトークン保護など、MSAL でのみ使用できる最新のセキュリティ保護がバイパスされます。 ADAL アプリケーションは、脆弱なレガシ認証パターンを使用してセキュリティの脆弱性を作成し、多くの場合、非推奨の Azure AD Graph エンドポイントを呼び出し、将来のセキュリティ アドバイザリを軽減できる強化された認証フローの導入を防止します。

**修復アクション**

- アプリケーションをMicrosoft Authentication Library (MSAL)

#### レガシ Azure AD PowerShell モジュールをブロックする

脅威アクターは、先進認証、条件付きアクセスの強制、高度な監査ログ記録をサポートしていない Azure AD PowerShell モジュール (AzureAD および AzureADPreview) などのレガシ管理インターフェイスを頻繁に対象とします。 これらのモジュールを継続的に使用すると、脆弱な認証、セキュリティ制御のバイパス、管理アクションの不完全な可視性などのリスクに環境が公開されます。 攻撃者はこれらの弱点を悪用して、不正アクセスを取得し、特権をエスカレートし、悪意のある変更を実行することができます。

Azure AD PowerShell モジュール (appID: 00001111-aaaa-2222-bbbb-3333cccc4444) をブロックし、Microsoft Graph PowerShell または Microsoft Entra PowerShell の使用を強制して、セキュリティで保護された、サポートされている、監査可能な管理チャネルのみを使用できるようにし、攻撃チェーンの重大なギャップを埋めます。

**修復アクション**

- [アプリケーションのユーザー サインインを無効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal)

#### Microsoft Entra ID のセキュリティ既定値を無料テナントに対して有効にする

Microsoft Entraでセキュリティの既定値を有効にすることは、ID 関連の攻撃から保護するために、Microsoft Entra無料ライセンスを持つ組織にとって不可欠です。 これらの攻撃は、不正アクセス、金銭的損失、評判の損害につながる可能性があります。 セキュリティの既定値では、すべてのユーザーが多要素認証 (MFA) に登録し、管理者が MFA を使用していることを確認し、レガシ認証プロトコルをブロックする必要があります。 これにより、MFA を使用してレガシ認証をブロックすることで、99% を超える一般的な ID 関連攻撃が停止されるため、攻撃が成功するリスクが大幅に軽減されます。 セキュリティの既定値では、ベースライン保護が追加コストなしで提供されるため、すべての組織でアクセスできます。

**修復アクション**

- [Microsoft Entra ID でセキュリティの既定値を有効にする](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults#enabling-security-defaults)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/zero-trust-protect-networks"} -->
## セキュリティ ガイダンス - ネットワークの保護 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks
- Service: entra / fundamentals
- Article date: 2026-04-30
- Summary: ネットワークを保護するために、Microsoft Entra ゼロ トラスト 評価を使用してセキュリティ体制を改善します。

安全な未来イニシアチブは、ネットワークaccessをセキュリティで保護し、組織のリソースへの不正なaccessを防ぐためにネットワークベースの制御を実装することの重要性を強調しています。 この柱のベスト プラクティスは、ネットワーク境界の確立、トラフィック フローの制御、accessを許可する前にネットワーク接続の信頼性を検証する場所ベースのaccess ポリシーの実装などのアクションに焦点を当てています。

### ゼロ トラストセキュリティに関する推奨事項

#### 名前付きの場所が構成されている

Microsoft Entra IDで名前付き場所が構成されていないと、脅威アクターは、場所ベースのリスク検出やセキュリティ制御をトリガーすることなく、場所インテリジェンスの欠如を利用して攻撃を実行できます。 信頼されたネットワーク、ブランチ オフィス、既知の地理的リージョンの名前付き場所を組織が定義できない場合、Microsoft Entra ID 保護は場所ベースのリスクシグナルを評価できません。 これらのポリシーを設定しないと、誤検知が増加し、アラートの疲労が発生し、本物の脅威が隠される可能性があります。 この構成ギャップにより、システムは正当な場所と不正な場所を区別できなくなります。 たとえば、企業ネットワークからの正当なサインインや、危険度の高い場所 (匿名プロキシ ネットワーク、Tor 出口ノード、組織がビジネス上のプレゼンスを持たないリージョン) からの疑わしい認証の試行などです。 脅威アクターは、この不確実性を利用して、資格情報の詰め込み攻撃、パスワードスプレーキャンペーン、および悪意のあるインフラストラクチャからの初期アクセス試行を実行できます。通常であれば疑わしい活動としてフラグを設定する位置に基づく検出をトリガーすることがなくなります。 組織は、より厳密な認証要件を自動的に適用したり、信頼されていない地理的リージョンからaccessを完全にブロックしたりする可能性があるアダプティブ セキュリティ ポリシーを実装する機能を失う可能性もあります。 脅威アクターは、場所ベースのセキュリティ バリアに遭遇することなく、永続化を維持し、グローバルな場所からの横移動を実行できます。これは、未承認のaccess試行に対する防御の追加レイヤーとして機能する必要があります。

**修復アクション**

- [拡張された場所ベースのリスク検出と条件付きAccessポリシーの適用のために、信頼できる IP 範囲と地理的リージョンを定義するための名前付き場所の構成](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)

#### テナント制限 v2 ポリシーが構成されている

テナント制限 v2 (TRv2) を使用すると、組織は、accessを指定された Microsoft Entra テナントに制限するポリシーを適用し、ローカル アカウントを使用して外部テナントに企業データを不正に流出させるのを防ぐことができます。 TRv2 がないと、脅威アクターがこの脆弱性を悪用する可能性があります。これにより、潜在的なデータ流出とコンプライアンス違反が発生し、その後、外部テナントの制御が弱い場合は資格情報の収集が行われます。 資格情報を取得すると、脅威アクターはこれらの外部テナントに対する初期accessを取得できます。 TRv2 は、ユーザーが承認されていないテナントに対して認証されないようにするメカニズムを提供します。 それ以外の場合、脅威アクターは、内部テナントの監視に重点を置いた従来のデータ損失防止コントロールをバイパスする正当なユーザー アクティビティとして表示される一方で、横に移動したり、特権をエスカレートしたり、機密データを流出させる可能性があります。

TRv2 を実装すると、accessを指定されたテナントに制限するポリシーが適用され、認証とデータのaccessが承認されたテナントのみに限定されるため、これらのリスクが軽減されます。

このチェックに合格した場合、テナントには TRv2 ポリシーが構成されていますが、シナリオをエンドツーエンドで検証するにはさらに多くの手順が必要です。

**修復アクション**

- [テナント制限 v2 を設定する](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2)

#### ネットワーク検証は、ユニバーサル継続アクセス評価によって構成されます。

Universal Continuous Access Evaluation (Universal CAE) は、グローバル セキュア Access トンネルを介して接続が確立されるたびに、ネットワーク access トークンを検証します。 ユニバーサル CAE がない場合、トークンは、ユーザーの状態の変更に関係なく、60 ~ 90 分間有効なままになります。

この保護なし:

- 盗難または再生によってトークンを取得した脅威アクターは、ユーザーのアカウントが無効になったり、パスワードがリセットされた後でも、グローバル セキュア Accessで保護されたすべてのリソースに引き続きアクセスできます。
- セッション失効やユーザー リスクの高い検出などの重大なイベントでは、すぐに再認証を求めるメッセージは表示されません。
- 退職した従業員または悪意のある内部関係者は、修復アクションが実行されてから最大 90 分間、プライベート企業リソースへのネットワーク レベルのaccessを維持します。
- 異なる IP アドレスからのトークンリプレイ攻撃は、厳密な強制モードなしではブロックされません。

**修復アクション**

- グローバル セキュア アクセスの [Universal CAE](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-universal-continuous-access-evaluation) の機能をレビューします。
- グローバル セキュア Access ワークロードの CAE を無効にする条件付きAccess ポリシーを削除または変更します。 詳細については、「[Continuous access evaluation](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)を参照してください。
- 強化されたトークンリプレイ保護に厳格な強制モードを使用するようにユニバーサル CAE を構成します。 詳細については、「[Universal Continuous Access Evaluation](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-universal-continuous-access-evaluation#strict-enforcement-mode)を参照してください。

#### グローバル セキュア Access クライアントは、すべてのマネージド エンドポイントにデプロイされます

グローバル セキュア Access クライアントの包括的な展開は、ゼロ トラストネットワーク セキュリティを実現するための基礎となります。 グローバル セキュア Access クライアントをマネージド エンドポイントに展開しない場合、これらのデバイスは組織の Security Service Edge コントロールの外部で動作します。 脅威アクターは、保護されていないエンドポイントを悪用して、ネットワーク レベルのセキュリティ ポリシーをトリガーすることなく、初期accessを確立したり、横方向に移動したり、データを流出させたりすることができます。

グローバル セキュア Access クライアントを使用しない場合:

- デバイスは、条件付きアクセスポリシーにおけるネットワークチェック、ソースIPの復元、またはテナント制限から利益を得ることができません。
- 資格情報の盗難とトークンリプレイ攻撃は、トラフィックがセキュリティ境界をバイパスするタイミングを検出するのがより困難です。
- マネージド エンドポイントは、Microsoft Entra Private Accessを介してプライベート アプリケーションをaccessすることはできません。

**修復アクション**

- グローバル セキュア Access クライアントをインストールします。
    - [Windows クライアント](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-install-windows-client)
    - [macOS クライアント](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-install-macos-client)
    - [iOS クライアント](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-install-ios-client)
    - [Android クライアント](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-install-android-client)
- [Global Secure Access ダッシュボード](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-traffic-dashboard)を使用して、グローバル セキュア Access クライアントの正常性と接続の状態を監視します。

#### グローバル セキュア Access ライセンスはテナントで使用でき、ユーザーに割り当てられます

Global Secure Accessでは、Microsoft Entra Internet AccessとMicrosoft Entra Private Accessを含む特定の Microsoft Entra ライセンスが機能する必要があり、どちらも前提条件として P1 Microsoft Entra ID必要です。 テナントに有効なライセンスがプロビジョニングされていない場合、管理者はトラフィック転送プロファイル、セキュリティ ポリシー、またはリモート ネットワーク接続を構成できません。 ユーザーにライセンスを割り当てない場合、そのトラフィックはグローバル セキュア Access経由でルーティングされないため、セキュリティ制御によって保護されません。

この保護なし:

- 脅威アクターは、Web コンテンツのフィルター処理、脅威の保護、条件付きAccessポリシーをバイパスできます。
- サブスクリプションの有効期限が切れているか中断されると、グローバル セキュア Access サービスが停止し、以前に保護されたトラフィック フローが監視されないセキュリティ ギャップが発生する可能性があります。

**修復アクション**

- グローバル セキュア Access ライセンス要件を確認し、適切なライセンスを購入します。 詳細については、「[Licensing の概要](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access#licensing-overview)を参照してください。
- Microsoft 365 管理センターを使用してユーザーにライセンスを割り当てます。 詳細については、「[Microsoft 365 管理センターのユーザーのライセンスの割り当てまたは割り当てを解除する」を](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/assign-licenses-to-users?view=o365-worldwide&preserve-view=true)参照してください。
- 大規模な管理を容易にするために、グループベースのライセンスを使用します。 詳細については、[Microsoft 365 管理センターのグループへのライセンスの割り当てまたは割り当て解除を参照してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)。
- Microsoft 365 管理センターを使用してライセンス使用率を監視します。 詳細については、「[Microsoft 365 管理センター](https://admin.microsoft.com/Adminportal/Home#/licenses)を参照してください。
- インターネット Accessとプライベート Accessの両方を含む代替手段として、Microsoft Entra スイート を確認します。 詳細については、「 [Microsoft Entra の新機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new#microsoft-entra-suite)」を参照してください。

#### インターネット Access転送プロファイルが有効になっている

インターネット Access転送プロファイルが有効になっていない場合、ユーザーは Secure Web Gateway 経由でトラフィックをルーティングすることなく、インターネット リソースをaccessできます。 このギャップにより、脅威アクターは、脅威、悪意のあるコンテンツ、安全でない宛先をブロックするセキュリティ制御をバイパスできます。

この保護なし:

- 組織はトラフィック パターンの可視性を失います。 データ流出、悪意のあるドメインへの接続、または承認されていない外部accessを検出することはできません。
- 脅威アクターは、マルウェアを配信したり、コマンドと制御の接続を確立したり、監視されていないチャネルを介してデータを流出させたりすることができます。
- 脅威アクターは、侵害された資格情報またはソーシャル エンジニアリングを使用して、初期accessの取得、ツールのダウンロード、永続化の確立、外部インフラストラクチャとの通信を行うことができます。
- 脅威アクターは、侵害されたアカウントを使用して、ユーザーコンテキスト、デバイスコンプライアンス、または場所に基づいてセキュリティアラートをトリガーすることなく、一般的なユーザー動作と外部リソースをaccessすることができます。

**修復アクション**

- インターネット Access転送プロファイルを有効にして、Secure Web Gateway 経由でトラフィックをルーティングします。 詳細については、「[インターネット Access トラフィック転送プロファイルを管理する方法](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-internet-access-profile)を参照してください。
- インターネット Access プロファイルにユーザーとグループを割り当てて、特定のユーザーへのトラフィック転送を制限します。 詳細については、「[Global Secure Access トラフィック転送プロファイル](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-traffic-forwarding)」を参照>。

#### Web コンテンツ フィルター ポリシーが構成されている

ウェブコンテンツフィルタリングポリシーは、グローバルセキュアアクセスのインターネットアクセス制御の基盤です。 ポリシーが構成されていない場合、ユーザーはすべてのインターネットの宛先に無制限のaccessを持ち、組織をマルウェア、フィッシング サイト、不適切なコンテンツにさらします。 危険な Web サイト カテゴリをブロックし、ベースライン インターネット accessコントロールを確立するフィルター ポリシーを作成します。

**修復アクション**

- [Web コンテンツ フィルタリング ポリシーの構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering)

#### Web コンテンツ のフィルター処理でカテゴリベースのルールが使用される

カテゴリベースのフィルター処理では、URL 固有の規則よりも広範な保護が提供されます。 マルウェア、フィッシング、ハッキング、犯罪行為などの Web サイト カテゴリ全体をブロックすると、一度に何千もの悪意のあるサイトにaccessできなくなります。 特定の URL またはドメインのみを対象とするフィルター ポリシーでは、一定のメンテナンスが必要であり、脅威アクターが新しい悪意のあるドメインを毎日登録するにつれてギャップが残ります。

**修復アクション**

- [利用可能な Web コンテンツ フィルタリング カテゴリを確認します](https://learn.microsoft.com/ja-jp/entra/global-secure-access/reference-web-content-filtering-categories)
- [カテゴリ ベースのフィルター規則の構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering)

#### Web コンテンツ フィルター ポリシーがセキュリティ プロファイルにリンクされている

セキュリティ プロファイルまたはベースライン プロファイルにリンクせずにフィルター ポリシーを作成すると、ポリシーは強制的に適用されなくなります。 ポリシーを有効にするには、ベースライン プロファイル (すべてのインターネット トラフィックに適用) またはセキュリティ プロファイル (条件付きAccessによって適用) のいずれかに関連付ける必要があります。 リンクされていないポリシーは保護を提供せず、セキュリティ体制に誤った信頼を生み出します。

**修復アクション**

- [セキュリティ プロファイルとリンク フィルター ポリシーの作成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering#create-a-security-profile)

#### Web コンテンツ フィルター処理と条件付きAccessの統合

ベースライン プロファイルは、すべてのユーザーとセッションに同じフィルター規則を適用します。 条件付きAccess統合により、ユーザー リスク、デバイス コンプライアンス、場所、またはグループ メンバーシップに基づいて適応する ID 対応のフィルター処理が可能になります。 リスクの高いセッションに厳密なフィルター処理を適用すると同時に、準拠しているデバイス上の検証済みユーザーに標準のaccessを許可し、侵害されたアカウントがセキュリティ制御をバイパスするのを防ぎます。

**修復アクション**

- [条件付きAccess ポリシーにセキュリティ プロファイルをリンクします](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering#create-and-link-conditional-access-policy)

#### Web コンテンツのフィルター処理により、リスクの高いカテゴリがブロックされる

犯罪行為、ハッキング、違法なソフトウェアなどの危険度の高い Web コンテンツ フィルタリング カテゴリをブロックしない場合、Global Secure Access を介して接続するユーザーは、危険な攻撃ベクトルや責任リスクにさらされ続けます。 これらの危険度の高いサイトは、悪用コード、マルウェア埋め込みソフトウェア、脅威アクターが環境を侵害するために使用できる違法な行為のコミットに関するガイダンスを配布できます。

この保護なし:

- ユーザーは、不正アクセスのためのツール、スクリプト、チュートリアルを提供するサイトにアクセスでき、脅威アクターはネットワーク内で機能をエスカレートできます。
- 違法なソフトウェア サイトからダウンロードされた海賊版ソフトウェアおよびライセンス キー ジェネレーターには、永続化を確立し、横移動を可能にする埋め込みマルウェアが頻繁に含まれています。
- 組織は、セキュリティの脆弱性と、危険度の高いコンテンツ カテゴリへの制御されていないアクセスによる法的責任の両方に直面しています。

**修復アクション**

- 危険度の高い [Web コンテンツ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering) カテゴリ**の犯罪行為**、ハッキング、**および違法なソフトウェア**をブロックするように Web コンテンツ **フィルタリングを**構成します。
- 使用可能なすべての [グローバル セキュア アクセス Web コンテンツ フィルタリング カテゴリを確認します](https://learn.microsoft.com/ja-jp/entra/global-secure-access/reference-web-content-filtering-categories)。
- 条件付きアクセス適用のフィルター ポリシーをグループ化するための[セキュリティ プロファイルを作成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering#create-a-security-profile)します。
- [インターネット アクセス トラフィック転送プロファイルを有効](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-internet-access-profile) にして、Web コンテンツ フィルタリングを適用するためにグローバル セキュア アクセス経由でトラフィックをルーティングします。
- [セキュリティ プロファイルを条件付きアクセスにリンク](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering#create-and-link-conditional-access-policy) して、対象となるユーザーとグループに対して Web コンテンツのフィルター処理を適用します。

#### TLS 検査が有効で、送信トラフィック用に正しく構成されている

TLS 検査は HTTPS トラフィックを復号化して検査し、暗号化されたセッションを可視化します。 これを使用しないと、URL フィルタリングや高度な脅威検出など、多くのMicrosoft Entra Internet Access機能を機能させられません。 ほとんどのインターネット トラフィックは暗号化されるため、ほとんどのユーザー アクティビティにセキュリティ ポリシーを適用するには TLS 検査が不可欠です。

**修復アクション**

- [トランスポート層セキュリティ検査ポリシーの構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-transport-layer-security)

#### TLS 検査バイパス規則が定期的にレビューされる

トランスポート層セキュリティ (TLS) 検査バイパス 規則では、暗号化されたトラフィックがディープ パケット検査をスキップする例外が作成されます。 定期的なレビューがないと、一時的な例外が永続的になり、ルールが残っている間にアプリケーションが使用停止になるか、最初の正当な理由が廃止されると、バイパス ルールが蓄積されます。 脅威アクターは、疑いのないトラフィック チャネルを対象とします。 トラフィックが TLS 検査をバイパスすると、HTTPS 経由のマルウェアコマンドアンドコントロール通信、データ流出、および資格情報の盗難が検出を回避することを認識しています。 ポリシーが 90 日を超えて変更されない場合は、ネットワーク セキュリティ体制に盲点を作成する古いバイパス 規則が含まれている可能性があります。

**修復アクション**

- TLS 検査バイパス 規則の四半期ごとのレビュー プロセスを確立し、各バイパス 規則の業務上の正当な理由を文書化し、不要になったルールを削除します。
- Microsoft Entra 管理センターの「グローバルセキュアアクセス」の「セキュアTLS検査」で、TLS検査ポリシーを確認および管理します。
- レビュー プロセスの一環としてバイパス ルールを変更または削除する方法を理解するには、「 [トランスポート層セキュリティ検査ポリシーを構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-transport-layer-security) する」の手順を確認します。

#### TLS 検査証明書には十分な有効期間があります

TLS 検査では、中間証明機関を使用して、暗号化されたトラフィックを復号化および検査するためのリーフ証明書を動的に作成します。 この証明書の有効期限が切れると、サービスは TLS 終了を実行できません。 この有効期限により、HTTPS トラフィックに対する URL フィルタリング、脅威検出、データ損失防止など、すべての TLS 検査機能がすぐに無効になります。 脅威アクターは、証明書の有効期限中にセキュリティ コントロールが頻繁に失効し、これらのギャップと一致するように攻撃を行うことが多いと認識しています。

証明書の有効期限が切れた場合:

- 組織は、暗号化されたトラフィックの可視性を失います。
- 脅威アクターは、TLS 検査をバイパスして、暗号化されたマルウェアの配信、コマンド アンド コントロール通信へのアクセス、データの流出を行うことができます。

**修復アクション**

- 証明書の有効期限が切れる前に 90 日間のバッファーを維持し、更新プロセスを完了する時間を確保します。 Microsoft では、署名された証明書を少なくとも 6 か月間有効なままにすることをお勧めします。
- [トランスポート層セキュリティ検査設定の構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-transport-layer-security-settings)の手順に従って、次の操作を行います。
    - 新しい証明書署名要求 (CSR) を作成し、Microsoft Entra 管理センターで更新された証明書をアップロードします。
    - 少なくとも 6 か月の有効期間を持つ組織の PKI インフラストラクチャを使用して CSR に署名します (Microsoft の推奨事項)。
- [Active Directory 証明書サービス (ADCS)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/scripts/powershell-active-directory-certificate-service) または [OpenSSL](https://learn.microsoft.com/ja-jp/entra/global-secure-access/scripts/powershell-open-secure-sockets-layer) を使用して CSR に署名します。

#### TLS 検査の失敗率が 1% を下回る

トランスポート層セキュリティ (TLS) 検査を使用することで、グローバル セキュア アクセスは暗号化された HTTPS トラフィックを復号化し、脅威、悪意のあるコンテンツ、ポリシー違反がないか確認できます。 接続に対して TLS 検査が失敗した場合、そのトラフィックはセキュリティ制御をバイパスします。 検査エラーにより、潜在的なマルウェアの配信、コマンド アンド コントロール通信、またはデータ流出が検出されない可能性があります。

1% を超える故障率は、全身的な問題を示しています。 これらの問題には、エンドポイントでの証明書信頼の問題、適切なバイパス規則なしで証明書のピン留めを使用する互換性のないアプリケーション、証明機関の構成エラーなどがあります。 脅威アクターは、TLS 検査エラーの原因となる接続を意図的に作成することもできます。

**修復アクション**

- Log Analytics ワークスペース[にトラフィック ログをエクスポートするように診断設定を構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-view-traffic-logs#configure-diagnostic-settings-to-export-logs)します。 これらのログを使用して、TLS 検査の成功率を監視し、障害の根本原因を調査します。
- [グローバル セキュア アクセス トランスポート層セキュリティ検査エラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-transport-layer-security)の手順に従って、一般的な検査エラーを解決します。
- 証明書のピン留めを使用する宛先の場合は、 [TLS バイパス 規則を追加](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-transport-layer-security) して、他のトラフィックの検査を維持しながら、障害率を減らします。

#### TLS 検査のカスタムバイパス規則では、システムバイパスの宛先を重複させないようにする。

グローバル セキュア アクセスでは、トランスポート層セキュリティ (TLS) 検査から自動的に除外される宛先のシステム バイパス リストが保持されます。 これらのバイパス先は、証明書のピン留め、相互 TLS 要件、その他の技術的制約など、既知の非互換性を表します。 システム バイパス リスト内の宛先を複製するカスタム バイパス 規則は冗長であり、機能目的を果たしません。

冗長ルールは、ポリシー容量を消費し、管理オーバーヘッドを作成し、必要なルールについて混乱を引き起こす可能性があります。 TLS 検査では、テナントあたり最大 1,000 の規則と 8,000 の宛先がサポートされます。 必要なカスタム バイパス 規則のみを使用してクリーンなポリシー構成を維持すると、管理容易性が向上し、セキュリティ監査が簡素化され、正当なビジネス要件に対してポリシー容量が確実に使用できるようになります。

**修復アクション**

- Microsoft Entra 管理センターで、冗長なカスタム TLS 検査バイパス規則を確認して削除します。 **グローバル セキュア アクセス**&gt;&gt;に移動します。
- [システム バイパス リストに含まれる宛先を確認します](https://learn.microsoft.com/ja-jp/entra/global-secure-access/faq-transport-layer-security#what-destinations-are-included-in-the-system-bypass)。

#### 脅威インテリジェンス のフィルター処理によってインターネット トラフィックが保護される

グローバル セキュア Accessで脅威インテリジェンス のフィルター処理を有効にしない場合、ユーザーとデバイスからのインターネット トラフィックは、悪意のある宛先への接続に対して保護されません。 脅威アクターは、このギャップを使用して、フィッシング サイトをaccessしたり、マルウェア ペイロードを配信したり、コマンド アンド コントロール インフラストラクチャと通信したりできます。

この保護なし:

- 侵害されたエンドポイントは、命令を受信したり、ツールをダウンロードしたり、機密データを流出させたりするために、コマンド アンド コントロール サーバーに到達する可能性があります。
- 脅威アクターは無制限のネットワーク通信パスを維持し、永続化メカニズムと横移動を可能にします。
- 組織は、アクティブなマルウェアの配布、フィッシング キャンペーン、およびその他の信頼度の高い脅威インジケーターに関連付けられているドメインと URL へのトラフィックをブロックできません。

ベースライン セキュリティ プロファイルは、条件付きAccess ポリシー構成を必要とせずにテナント全体の保護を提供しますが、カスタム セキュリティ プロファイルでは条件付きAccess統合によるコンテキスト対応の適用が有効になります。

**修復アクション**

[グローバルセキュアアクセスの脅威インテリジェンスの設定方法](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-threat-intelligence) の手順に従い、次の操作を行います。

- 脅威インテリジェンス フィード上の宛先への接続をブロックするように、Global Secure Access で脅威インテリジェンス フィルター処理を構成します。
- セキュリティ プロファイルを作成し、脅威インテリジェンスを含むフィルター ポリシーをリンクします。
- グローバル セキュア Access セッション制御を使用して、セキュリティ プロファイルを条件付きAccess ポリシーにリンクします。
- 条件付きAccessを使用せずに、テナント全体の保護のベースライン プロファイルを構成します。

#### ファイル転送ポリシーは、データ流出を防ぐために構成されます

グローバル セキュア Accessで構成されたネットワーク コンテンツ フィルタリング ポリシーがない場合、脅威アクターはブラウザー、アプリケーション、API を介して承認されていない宛先にデータを流出させることができます。 ネットワーク コンテンツ フィルタリング ポリシーを実装しない場合、ファイル共有サービス、クラウド storage プロバイダー、ピアツーピア転送サイトは制限されません。

この保護なし:

- 脅威アクターは、知的財産、資格情報、または顧客データを含むファイルを検出なしで外部サービスにアップロードできます。
- アンマネージド クラウド アプリケーションと生成型 AI ツールは、機密情報の流出チャネルになります。
- ネットワーク層にはリアルタイムのデータ保護の適用がないため、脅威アクターはエンドポイント ベースの制御をバイパスできます。
- 脅威アクターは、一般的なファイル形式内の悪意のあるペイロードや機密データを偽装し、検出されないネットワーク境界を越えて転送することができます。

**修復アクション**

- [Configure Global Secure Access Web コンテンツ フィルタリング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering)(ファイル転送を監視および制御するためのファイル ポリシーを含む)。
- [条件付きAccessを使用して適用するフィルターポリシーをグループ化する Microsoft トラフィック転送プロファイル](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-microsoft-profile)を有効にして管理します。
- [ネットワーク セキュリティ ポリシーのユーザー対応およびコンテキスト対応の適用のために、セキュリティ プロファイルを条件付きAccess ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)にリンクします。
- [グローバル セキュア Access クライアント](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-install-windows-client)をエンドユーザー デバイスにインストールして、トラフィックの取得とポリシーの適用を有効にします。

#### AI Gateway は、企業の生成型 AI アプリケーションを迅速なインジェクション攻撃から保護します

組織が Prompt Shield 保護を使用していない場合、脅威アクターは、プロンプトインジェクションの脆弱性を悪用して、AI を利用したワークフローを侵害する可能性があります。 悪意のあるユーザーは、大規模な言語モデルを操作してシステム命令を無視したり、機密データを開示したり、フィッシング コンテンツの生成などの意図しないアクションを実行したりする敵対的な入力を作成できます。

ネットワーク レベルのプロンプト フィルター処理を使用しない場合:

- ダイレクト プロンプト インジェクション攻撃では、高度な脱獄手法を使用して、アプリケーション層の安全メカニズムをバイパスできます。
- 間接的なプロンプトの挿入は、脅威アクターが AI によって処理される外部コンテンツに悪意のある命令を埋め込むと発生します。
- 各 AI アプリケーションは、個別に保護を実装し、一貫性のないセキュリティ体制を作成し、新規またはカスタムの AI デプロイに対する不適切な保護を作成する必要があります。

**修復アクション**

- [インターネット Access トラフィック転送プロファイルを使用して、グローバル セキュア Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-internet-access-profile)経由でインターネット トラフィックをルーティングできるようにします。
- [暗号化された AI アプリケーション トラフィックを検査できるように、TLS 検査設定を構成し、CA 証明書をデプロイします](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-transport-layer-security-settings)。
- 「[Protect enterprise generative AI applications with Prompt Shield](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-ai-prompt-shield)の手順に従って、次の操作を行います。
    - 生成 AI アプリケーションを対象とする悪意のあるプロンプトをスキャンしてブロックするプロンプト ポリシーを作成します。
    - プロンプト ポリシーをセキュリティ プロファイルにリンクして、条件付きAccessのターゲット設定用に整理します。
    - 条件付きAccess ポリシーを作成して、インターネット リソースにアクセスするユーザーにプロンプト ポリシーを含むセキュリティ プロファイルを適用します。
- [グローバル セキュア Access クライアントをユーザー デバイスにインストールして、トラフィックの取得を有効にします](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-install-windows-client)。

#### セキュリティで保護されたグローバル Access クラウド ファイアウォールによってブランチ オフィスのインターネット トラフィックが保護される

リモート ネットワークを IPsec トンネル経由でグローバル セキュア Accessに接続するが、クラウド ファイアウォール ポリシーを設定しない場合、ブランチ オフィスからのインターネットに接続されたすべてのトラフィックは、エグレス フィルター制御なしで Security Service Edge を通過します。 脅威アクターがブランチ オフィス ワークステーションにaccessを取得した場合、コマンド アンド コントロール インフラストラクチャへの送信接続、標準ポート経由でのデータの流出、ネットワーク層検査なしで悪意のあるペイロードのダウンロードを行うことができます。

グローバル セキュア Access クラウド ファイアウォールを使用しない場合:

- 既定による拒否の体制を適用したり、ブランチ ネットワークから未承認のインターネット宛先への送信通信を制限したりすることはできません。
- 脅威アクターは、流出のデータをステージングしたり、クラウド リソースにピボットしたり、検出せずに横方向に移動したりできます。
- 従来の境界防御では、すべてのエグレスが正当であると想定され、セキュリティ カバレッジのギャップが生じる可能性があります。

ベースライン プロファイルにリンクされたクラウド ファイアウォール ポリシーは、すべてのリモート ネットワーク トラフィックに対して一元的なエグレス制御を提供します。 管理者はこれらのポリシーを使用して、承認されていない送信通信を制限する詳細なフィルター規則を定義できます。

**修復アクション**

- クラウド ファイアウォールの前提条件として、[インターネットアクセスのためにリモート ネットワークを構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-create-remote-networks)。
- [Configure Global Secure Access クラウド ファイアウォール](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-cloud-firewall)の手順に従って、次の操作を行います。
    - 適切なフィルター規則を使用してクラウド ファイアウォール ポリシーを作成します。
    - ソース IP、宛先 IP、ポート、プロトコルに基づいてファイアウォール規則を追加または更新します。
    - クラウド ファイアウォール ポリシーをリモート ネットワークのベースライン プロファイルにリンクします。

#### すべての Secure Web Gateway 防御レイヤーでインターネット トラフィックが検査される

Global Secure Access Secure Web Gateway (SWG) は、5 つのセキュリティレイヤーを介して多層防御を実装し、インターネットにバインドされたトラフィックに対する包括的な検査チェーンを作成します。 各レイヤーは、個別の保護機能を提供します。

- **レイヤー 1: コンテキスト対応のネットワーク セキュリティ** この最も外側のレイヤーは、インターネット トラフィックを SWG 経由でルーティングし、ID とコンテキストに対応するポリシーの適用を適用します。 このレイヤーがないと、トラフィックはすべてのダウンストリーム 検査をバイパスします。
- **レイヤー 2: TLS 検査、Web コンテンツ、脅威インテリジェンス のフィルター処理** TLS 検査では、暗号化されたトラフィックが復号化されるため、すべてのダウンストリーム Web コンテンツ のフィルター処理と DLP 検査がプレーンテキスト ペイロードで動作します。 Web コンテンツフィルタリングは、悪意のある、不適切、またはポリシー違反の宛先へのアクセスをブロックします。 脅威インテリジェンス のフィルター処理により、既知の悪意のあるインフラストラクチャへの接続がブロックされます。
- **レイヤー 3: AI ゲートウェイ、コンテンツ フィルタリング、ネットワーク DLP** AI Gateway コントロールは、生成型 AI サービスとのユーザー操作を制御し、迅速な挿入とデータ漏洩を防ぎます。 コンテンツ フィルタリングとネットワーク DLP は、機密データの流出を防ぐために、ファイル転送とペイロードを検査します。
- **レイヤー 4: クラウド ファイアウォール** このレイヤーは、リモート ネットワーク経由でルーティングされるブランチ オフィスのインターネット トラフィックを保護するネットワーク レベルのファイアウォール規則を適用します。
- **レイヤー 5: 高度な脅威保護** この最も内側のレイヤーは、マルウェア対策スキャン、侵入検出と防止 (IDPS)、異常検出などの将来の機能のために予約されています。 このレイヤーでアクティブなチェックはまだ構成されていません。

どのレイヤーも存在しない場合、脅威アクターはギャップを利用してツールをダウンロードしたり、データを流出させたり、永続的なコマンド アンド コントロール チャネルを維持したりすることができます。

**修復アクション**

表示されている順序で修復手順に従って、レイヤー間の構成の依存関係に合わせます。

1. [インターネット アクセス トラフィック転送プロファイルを有効](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-internet-access-profile) にして、セキュリティで保護された Web ゲートウェイ経由でトラフィックをルーティングします。
2. 暗号化されたトラフィックの復号化とプレーンテキスト分析を有効にするように [TLS 検査を構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-transport-layer-security)します。
3. [Web コンテンツ フィルタリング ポリシーを構成して、](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering) 悪意のある宛先やポリシー違反の宛先へのアクセスをブロックします。
4. [フィルター ポリシーをセキュリティ プロファイルにリンク](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering#create-a-security-profile) し、適用のためにポリシーをバインドします。
5. [セキュリティ プロファイルを条件付きアクセス ポリシーにリンクして、](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering#create-and-link-conditional-access-policy) ID 対応ポリシー アプリケーションを適用します。
6. 既知の悪意のあるインフラストラクチャへの接続をブロックするように[脅威インテリジェンス フィルター処理を構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-threat-intelligence)します。
7. AI Gateway プロンプト保護ポリシーを生成型AIサービス用に構成します。
8. ファイル転送によるデータ流出を防ぐために[、ネットワーク コンテンツ フィルタリング ポリシーを構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-network-content-filtering)します。
9. リモート ネットワーク経由でルーティングされるブランチ オフィスのインターネット トラフィックの[クラウド ファイアウォール規則を構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-cloud-firewall)します。

#### Microsoft 365のトラフィックはグローバル セキュア アクセスを介して積極的に流れています。

Microsoft 365 トラフィックが Global Secure Accessをバイパスすると、組織は最も重要な生産性ワークロードの可視性と制御を失います。 監視されていないMicrosoft 365接続を悪用する脅威アクターは、セキュリティ ポリシーをトリガーしたり、実行可能なテレメトリを生成したりすることなく、SharePoint、OneDrive、またはExchangeを介して機密データを流出させることができます。 サインイン ログと条件付きAccess評価とのソース IP の相関関係を一貫して適用できないため、トークンの盗難とリプレイ攻撃は、トラフィックが Security Service Edge を通過しないタイミングを検出するのがより困難になります。

不完全なクライアント展開、誤って構成された転送プロファイル、アンマネージド デバイス上のユーザーなど、トラフィックが大幅にバイパスされた組織は、敵対者の中間者攻撃、資格情報の収集、および未承認のデータ転送が検出されない可能性がある死角を作成します。 グローバル セキュリティで保護されたAccessをバイパスするトラフィックは、条件付きAccess ポリシー、テナント制限、またはソース IP 復元の準拠ネットワーク チェックの恩恵を受けることができないので、重要なセキュリティ制御は無効になります。

**修復アクション**

- Microsoft トラフィック プロファイルを有効にして構成します。 詳細については、「[Enable Microsoft トラフィック プロファイル](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-microsoft-profile)」を参照してください。
- グローバル セキュア Access クライアントをすべてのマネージド デバイスに展開します。 詳細については、「[Deploy Global Secure Access client](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-install-windows-client)を参照してください。
- トラフィック転送規則を適切に確認して構成します。 詳細については、「[トラフィック転送ルールを確認する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-microsoft-traffic-profile)を参照してください。

#### ユニバーサル テナント制限により、承認されていない外部テナントのaccessがブロックされる

ユニバーサル テナント制限が構成されていない場合、企業のデバイスとネットワーク上のユーザーは、承認されていない外部の Microsoft Entra テナントに対して認証を行い、外部 ID を使用してクラウド アプリケーションをaccessできます。 この脆弱性により、脅威アクターは、侵害されたユーザー資格情報を使用したり、企業デバイスで永続化を確立したりして、制御するテナントに対して認証を行う可能性があります。 Microsoft ID エンドポイントへの暗号化された認証トラフィックを検査できない従来のネットワーク セキュリティ制御をバイパスできます。

外部テナントに認証されると、脅威アクターは Microsoft Graph API やクラウドサービスにアクセスできます。 このaccessにより、外部テナント内の OneDrive、SharePoint、Teams、または Microsoft Entra 統合アプリケーションを介したデータ流出が可能になります。 この攻撃パスは、企業ネットワークとデバイスが Microsoft ID サービスに対して持つ固有の信頼を利用します。 ユニバーサルテナント制限は、グローバルセキュアアクセスを通じて認証トラフィックにテナント識別ヘッダーを挿入することで、この脆弱性に対処します。 Microsoft Entra IDは、これらのヘッダーを使用して、承認されていない外部テナントへの認証試行をブロックするテナント制限 v2 ポリシーを適用します。

**修復アクション**

- 既定ですべての外部テナントをブロックするようにテナント[制限 v2](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2) ポリシーを設定します。
- [グローバル セキュアアクセス](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-universal-tenant-restrictions) のユニバーサル テナント制限のシグナリングをオンにします。
- [グローバル セキュア Access クライアント](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-clients)をデバイスにデプロイします。
- [Microsoft トラフィック プロファイル](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-microsoft-profile)を使用できます。

#### 条件付きアクセス ポリシーは準拠したネットワーク制御を使用します

条件付きAccess ポリシーに準拠したネットワーク制御がない場合、組織は、グローバル セキュリティで保護されたAccess サービスを介してユーザーが企業リソースに接続することを強制することはできません。 この制限により、認証トラフィックは任意のネットワークの場所からの傍受やリプレイ攻撃に対して脆弱になります。

フィッシング詐欺や資格情報の盗難によって有効なユーザー資格情報を取得する脅威アクターは、グローバル セキュリティで保護されたAccessネットワーク制御をバイパスして、インターネット上の任意の場所から認証できます。 認証されると、攻撃者は、Microsoft Entra IDに統合されたアプリケーションやサービスにアクセスしたり、データを流出させたり、追加の資格情報を作成したり、ユーザーのアクセス許可を変更して永続化を確立したりできます。

準拠しているネットワーク チェックでは、認証トラフィックがグローバル セキュア Access サービスから送信され、認証要求にテナント固有のネットワーク ID シグナルがタグ付けされることを要求することで、このリスクが軽減されます。 この要件により、Microsoft Entra ID条件付きAccessは、accessを付与する前に、ユーザーが組織のセキュリティで保護されたネットワーク パスを介して接続することを確認できます。

**修復アクション**

- 条件付きアクセスのグローバルセキュアアクセスシグナリングを有効にします。 詳細については、「条件付きアクセスで準拠したネットワーク チェックを有効にする」を参照してください。
- アクセスポリシーを条件付きで作成し、アクセスに必要な準拠したネットワークを要求します。 詳細については、「[準拠ネットワークの背後にあるリソースを保護する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-compliant-network#protect-your-resources-behind-the-compliant-network)を参照してください。
- グローバル セキュア Access クライアントをデバイスに展開します。 詳細については、「[Global Secure Access クライアントの概要](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-clients)を参照してください。
- 準拠ネットワークの実施を理解する。 詳細については、「[準拠ネットワーク チェックの適用](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-compliant-network#compliant-network-check-enforcement)」を参照>。

#### 条件付きアクセスのためのグローバルセキュアアクセスシグナリングが有効になっている

Global Secure Access は、Microsoft の Security Service Edge 経由でユーザー トラフィックをルーティングするときに、元のソース IP をプロキシエグレス IP に置き換えます。 条件付きAccessに対してグローバル セキュア Access シグナリングを有効にしない場合、Microsoft Entra IDはユーザーの実際の IP アドレスではなくプロキシ IP を受け取ります。 名前付き場所または信頼された IP 範囲に依存する条件付きAccess ポリシーでは、ユーザーの実際の IP ではなくプロキシ IP が評価され、場所ベースの制御の有効性が低下します。

条件付きアクセスに対してグローバルセキュアアクセスシグナリングを有効にしない場合は、

- Microsoft Entra ID 保護不可能な移動や未知のサインイン プロパティなど、ソース IP に依存するリスク検出の信頼性は低くなります。
- サインイン ログにはプロキシ IP が記録されるため、セキュリティ運用チームは、インシデント対応中にサインイン イベントをユーザーの場所に関連付けなくなります。

**修復アクション**

- [グローバル セキュア アクセスのシグナリングをConditional Accessに対して有効にします。](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-source-ip-restoration#enable-global-secure-access-signaling-for-conditional-access)
- [条件付きアクセスを有効化し](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-compliant-network)、コンプライアントなネットワークチェックを行って、ユーザーがグローバルセキュアアクセス経由で接続するように要求します。
- [グローバル セキュリティで保護されたAccess トラフィック プロファイル](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-universal-conditional-access)でユニバーサル条件付きAccessがどのように機能するかを理解します。

#### セキュリティ ポリシーの適用のために、グローバル セキュリティで保護されたAccess経由でネットワーク トラフィックがルーティングされる

トラフィック転送プロファイルは、グローバル セキュア Accessがネットワーク トラフィックをキャプチャして Microsoft の Security Service Edge インフラストラクチャにルーティングする基本的なメカニズムです。 適切なトラフィック転送プロファイルを有効にしない場合、ネットワーク トラフィックは Global Secure Access サービスを完全にバイパスし、ユーザーはこれらのネットワークaccess保護を受けられません。

次の 3 つの異なるプロファイルがあります。

- **Microsoft トラフィック プロファイル**: Microsoft Entra ID、Microsoft Graph、SharePoint Online、Exchange Online、およびその他のMicrosoft 365ワークロードをキャプチャします。
- **Private access プロファイル**: 内部企業リソース宛てのトラフィックをキャプチャします。
- **Internet access プロファイル**: Microsoft 以外の SaaS アプリケーションを含むパブリック インターネットへのトラフィックをキャプチャします。

これらのプロファイルを有効にしない場合:

- セキュリティ ポリシー、Web コンテンツのフィルター処理、脅威保護、ユニバーサル継続的Access評価を適用することはできません。
- ユーザーの資格情報を侵害する脅威アクターは、Global Secure Accessが適用するセキュリティ制御なしで企業リソースをaccessできます。

**修復アクション**

- Microsoft トラフィック転送プロファイルを有効にします。 詳細については、「[Microsoft トラフィック プロファイルの管理](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-microsoft-profile)を参照してください。
- プライベート Access トラフィック転送プロファイルを有効にします。 詳細については、「[プライベート Access トラフィック転送プロファイルの管理](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-private-access-profile)を参照してください。
- インターネット Access トラフィック転送プロファイルを有効にします。 詳細については、「[インターネット Access トラフィック転送プロファイルの管理](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-internet-access-profile)を参照してください。

#### トラフィック転送プロファイルのスコープは、制御された展開の適切なユーザーとグループに設定されます

トラフィック転送プロファイルの適切なスコープを設定しないと、インフラストラクチャの準備が検証される前にすべてのユーザーがセキュリティ制御にさらされたり、保護する必要があるユーザーが誤って除外されたりするリスクがあります。

不適切なスコープのリスク:

- **広すぎる**: プロファイルが意図的な計画なしで "すべてのユーザー" に割り当てられている場合、構成ミスによって組織全体のネットワーク接続が同時に中断される可能性があります。
- **狭すぎる**: プロファイルの範囲が狭すぎるか、割り当てが不完全な場合、ユーザーのサブセットがセキュリティ境界の外側で動作し、脅威アクターが悪用できるギャップが生じます。
- **監視対象外のaccess**: 割り当てられていないユーザーに属するデバイスを侵害する攻撃者は、トラフィックの検査、ログ記録、セキュリティ ポリシーの適用なしでリソースをaccessできます。

適切なスコープにより、制御されたロールアウトが保証されます。パイロット グループから機能を検証してから、より広範な人口に拡大しながら、どのユーザーが保護されているかを把握できます。

**修復アクション**

- トラフィック転送プロファイルにユーザーとグループを割り当てます。 詳細については、「[Manage users and groups assignment](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-users-groups-assignment)を参照してください。

#### プライベート ネットワーク コネクタはアクティブで正常であり、内部リソースへのゼロ トラスト accessを維持します

Microsoft Entra プライベート ネットワーク コネクタが非アクティブまたは異常な場合、組織は安全性の低いaccess方法を使用する可能性があります。 この条件により、脅威アクターが外部に公開されたサービスをターゲットにしたり、侵害された資格情報を使用したりする機会が生まれます。

機能コネクタなし:

- すべてのMicrosoft Entra Private Accessシナリオのトークンベースの認証と承認は排除されます。
- 脅威アクターは、意図したセキュリティ境界をバイパスして、承認スコープを超えてリソースをaccessできます。
- サービスは要求を適切にルーティングできないため、ネットワークのアクセス制御に直接影響を与えます。

**修復アクション**

- [高可用性のためのコネクタの構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)。
- Microsoft Entra 管理センターの [Global Secure Access &gt; Connect &gt; Connectors] でコネクタの正常性を監視します。
- [コネクタのインストールと接続の問題](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)。

#### プライベート ネットワーク コネクタで最新バージョンが実行されている

プライベート ネットワーク コネクタは、Microsoft Entra Private Accessとアプリケーション プロキシの主要なコンポーネントです。 セキュリティ、安定性、およびパフォーマンスを維持するには、すべてのコネクタ マシンで最新のソフトウェア バージョンを実行する必要があります。

コネクタで最新バージョンが実行されない場合:

- 重要なセキュリティ パッチが見つからない可能性があるため、コネクタは既知の悪用に対して脆弱になります。
- 信頼性に影響を与える可能性がある最新のパフォーマンスの向上とバグ修正は得られません。
- グローバル セキュア Access サービスの進化に伴い、互換性の問題が発生する可能性があります。

**修復アクション**

- [Microsoft Entra Private Access および Microsoft Entra application proxy](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors) 用のプライベート ネットワーク コネクタを構成します。
- すべてのコネクタが最新であることを確認し、最新の [connector 更新プログラム](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#connector-updates)をインストールします。

#### 少なくとも 2 つのプライベート Access コネクタがアクティブで、コネクタ グループごとに正常である

Microsoft Entra Private Accessでは、プライベート ネットワーク コネクタを使用して、オンプレミスまたはクラウドでホストされているネットワークからグローバル セキュア Access サービスへの接続を仲介します。 各コネクタ グループは、割り当てられているプライベート アプリケーションの唯一のaccess パスとして機能します。 1 つのグループにコネクタを 1 つだけ展開すると、そのホストで障害が発生すると、そのグループに依存するユーザーのすべてのプライベート アプリケーション accessが直ちに削除されます。

この保護なし:

- 1 つのコネクタ障害により、コネクタを手動で復元するまで、そのコネクタ グループに依存しているすべてのユーザーのプライベート リソース accessが完全に失われます。
- コネクタ Windows サービスを終了するか、送信トランスポート層セキュリティ (TLS) 通信をブロックする脅威アクターは、ID 層アラートをトリガーすることなく、プライベート アプリケーションへのaccessをサイレント に拒否できます。
- 一度に 1 つのコネクタを対象とする自動更新では、グループに 1 つのコネクタのみが存在する場合にダウンタイムが発生する可能性があります。
- コネクタに到達できない場合、条件付きAccess ポリシーを適用することはできません。また、ユーザーは別の強制ポイント経由ではなく、access拒否されます。

**修復アクション**

- [影響を受けるリージョンの Windows Server に追加のプライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)をインストールして登録します。
- [コネクタ グループの高可用性要件](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#connector-groups)およびベスト プラクティスについて説明します。
- [Microsoft Entra プライベート ネットワーク コネクタのサイズ設定と回復性に関するガイダンス](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#specifications-and-sizing-requirements) (最小 2 コネクタの推奨事項を含む)。
- [プライベート Access コネクタ グループに適用できる高可用性負荷分散のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-high-availability-load-balancing#best-practices-for-high-availability-of-connectors)を確認します。
- [非アクティブまたは誤動作しているプライベート ネットワーク コネクタをトラブルシュートする](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)。

#### プライベート DNSが内部名前解決用に構成されている

private DNS構成がないと、リモート ユーザーはMicrosoft Entra Private Accessを介して内部ドメイン名を解決できないため、パブリック DNS サーバーに依存する必要があります。 脅威アクターは、悪意のあるサイトにユーザーをリダイレクトする DNS スプーフィング攻撃を通じてこのギャップを悪用し、資格情報の収集とデータ流出を可能にします。 また、組織は DNS クエリの可視性を失い、一貫したセキュリティ ポリシーを適用することはできません。

**修復アクション**

- [内部名前解決用にプライベートDNSを構成する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-quick-access#add-private-dns-suffixes)

#### 内部ドメインの DNS トラフィックはプライベート Access経由でルーティングされます

グローバル セキュア Accessでプライベート Access プロファイルを有効にしても、アプリケーション セグメントでポート 53 (UDP/TCP) を発行したり、private DNSサフィックスを構成したりしない場合、Global Secure Access クライアントはトンネルを介して内部ドメイン名の DNS クエリをルーティングできません。 その結果、内部 FQDN の DNS クエリは、内部ゾーンに関する知識がないデバイス上のローカル リゾルバーに送信されます。

この構成がない場合:

- FQDN ベースのアプリケーション セグメントは、クライアントが内部ホスト名を IP アドレスに解決できないため、トラフィックの照合に失敗します。
- リモート ユーザーと同じローカル ネットワーク上で動作する脅威アクターは、暗号化されていない DNS クエリを観察し、内部リソース名をマップし、攻撃の後の段階でターゲットを識別できます。
- クライアントは、Global Secure Accessによって適用される条件付きAccessとセキュリティ プロファイルの適用をバイパスする直接接続にフォールバックする場合があります。

**修復アクション**

- [クイックアクセスまたはアプリごとのアクセス用にプライベート DNS サフィックスを構成し](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-quick-access)、内部ドメインの DNS クエリが Global Secure Access を経由するようにします。
- [内部 DNS サーバーへのポート 53 (UDP/TCP) を含むアプリケーション セグメント](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-per-app-access) でアプリごとのaccessを構成します。
- [グローバルセキュアアクセスのMicrosoft Entra Private Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-private-access)について学習します。

#### インテリジェント ローカル Accessが有効になっており、構成されている

インテリジェント ローカル Access (ILA) は、クラウド経由ではなくローカルにトラフィックMicrosoft Entra Private Accessルーティングし、ポリシーの適用を維持しながらパフォーマンスを向上させます。 ILA がない場合、ユーザーはグローバル セキュア Access クライアントを無効にしてパフォーマンスを向上させ、条件付きAccess ポリシーをバイパスできます。 セキュリティ制御を維持しながらローカル ルーティングを確保するために、ユーザー サイトのプライベート ネットワークを構成します。

**修復アクション**

- [インテリジェント ローカル ネットワークの実現](https://learn.microsoft.com/ja-jp/entra/global-secure-access/enable-intelligent-local-access)

#### クイック Accessが有効になっており、コネクタにバインドされている

クイック Accessを構成したり、アクティブなコネクタを使用してコネクタ グループにバインドしたりしない場合、ユーザーはグローバル セキュア Access コントロールをバイパスするパスを介してプライベート ネットワーク リソースをaccessできます。 ユーザーの資格情報を侵害する脅威アクターは、トラフィックがグローバル セキュア Access サービスを経由しないため、条件付きAccess評価なしで内部 FQDN と IP 範囲に到達できます。

コネクタ仲介型トラフィック ブローカーを使用しない場合:

- ユーザーとプライベート リソースの間には適用ポイントがないため、クイック Access エンタープライズ アプリケーションを対象とする条件付きAccess ポリシーは適用されません。
- 脅威アクターは、盗まれた資格情報を使用して、認証、内部システム間の移動、データの流出を行うことができます。
- 組織は、グローバル セキュリティで保護されたAccessトラフィック ログを通じて可視性を失います。

アクティブなコネクタを持つコネクタ グループにクイック Accessをバインドする場合、プライベート ネットワーク トラフィックは、条件付きAccess ポリシー、ユーザー割り当て、およびトラフィック ログが適用されるグローバル セキュア Access経由でルーティングされるようにします。

**修復アクション**

- [グローバル セキュアアクセスのクイックアクセスを構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-quick-access)。
- [グローバル セキュア アクセス](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)用のプライベート ネットワーク コネクタを構成します。
- [プライベート Access トラフィック転送プロファイル](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-private-access-profile)を有効化します。
- [Microsoft Entra Private Accessの概念を確認する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-private-access)。

#### クイック Accessが条件付きAccess ポリシーにバインドされている

条件付きAccess ポリシーを使用せずにMicrosoft Entra Private Accessでクイック Accessを構成すると、ユーザーの資格情報を侵害する脅威アクターは、プライベート リソースへの無制限のaccessを得ることができます。 クイック Access アプリケーションは、FQDN や IP アドレスを含むプライベート リソースのコンテナーとして機能します。

ポリシーの適用なし:

- 侵害されたアカウントは、内部システムへの直接的な経路を提供します。
- 非管理対象デバイスまたは異常な場所から動作する脅威アクターは、承認されたユーザーと区別できない方法でプライベート リソースをaccessできます。
- 内部ネットワーク全体の横移動と、プライベート アプリケーションからのデータ流出が可能になります。
- 多要素認証の要件とデバイスの正常性チェックを適用することはできません。

**修復アクション**

- [Microsoft Entra Private Access Apps に条件付きアクセス ポリシーを適用する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-target-resource-private-access-apps)。

#### Entra プライベートアクセス アプリケーションセグメントは、最小権限のアクセスを適用するように定義されています。

組織は、幅広い IP 範囲、複数のプロトコル、クイック Access構成など、広範なアプリケーション セグメントを使用してMicrosoft Entra Private Accessを構成する場合、従来の VPN の制限を超えるaccess モデルを効果的にレプリケートします。 このアプローチは、ユーザーがロールに必要な特定のリソースにのみ到達する必要がある最小特権のゼロ トラスト原則と矛盾しています。

広範なセグメント化のリスク:

- ユーザーの資格情報またはデバイスを侵害する脅威アクターは、広範なネットワークアクセス許可を使用して偵察を実行し、許可された範囲内の他のシステムとサービスを識別できます。
- 攻撃者が 1 つの侵害された資格情報を介して複数のシステムをaccessできるため、横移動が容易になります。
- セキュリティ チームは、侵害された ID がaccessできる特定のリソースをすばやく特定できないため、インシデント対応は複雑です。

厳密にスコープが設定された宛先ホスト、特定のポート、およびカスタム セキュリティ属性を使用してアプリケーションごとのセグメント化を構成すると、動的な条件付きAccessの適用が可能になります。 このアプローチでは、リスクの高いアプリケーションに対する認証またはデバイスコンプライアンスの強化と、リスクの低いリソースへのaccessの合理化が必要です。

**修復アクション**

- [アプリケーション セグメントの確認と調整](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-per-app-access)を使用して、広いポート範囲ではなく、アプリケーションの要件に一致する特定の FQDN、IP アドレス、および特定のポート範囲を使用します。

#### ドメイン コントローラーのRDPアクセスは、グローバルセキュアアクセスを通じてフィッシング耐性認証によって保護されています。

管理者がMicrosoft Entra Private Accessを使用して リモート デスクトップ プロトコル (RDP) 経由でドメイン コントローラーに到達する場合、グローバル セキュア Access クライアントがオンプレミス ネットワークへの接続をトンネルする前に、Microsoft Entra IDを介して認証を行います。 ドメイン コントローラーは、Active Directory フォレスト全体に対して暗号化キーを保持します。 1 つのドメイン コントローラーを侵害すると、組織内のすべての ID とリソースを侵害する方法が提供されます。

フィッシングに対する耐性のある認証を使用しない場合:

- 脅威アクターは、フィッシング キャンペーン中または敵対者の中間攻撃中に資格情報を傍受できます。
- 盗まれたセッション トークンを再生して、ドメイン コントローラーへの RDP 接続を確立できます。
- 接続されると、脅威アクターは DCSync 攻撃を実行して、ドメイン内のすべてのパスワード ハッシュを取得できます。
- 攻撃者は、無期限のドメイン永続化のためのゴールデン チケットを作成できます。
- Group Policyオブジェクトは、ドメインに参加しているすべてのマシンにランサムウェアまたはバックドアを展開するように変更できます。

フィッシングに強い認証を要求することで、組織は、ユーザーが正常にフィッシングを受けた場合でも、脅威アクターが資格情報を再生できないようにします。これらの方法には、所有の暗号証明が必要であるためです。

**修復アクション**

- [フィッシングに強い認証方法をドメイン コントローラー管理者に展開します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication)。
- [RDP](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-domain-controllers) 経由でドメイン コントローラーにアクセスする管理者向けのフィッシングに対する耐性のある認証を取得します。

#### プライベート Access センサーは、ドメイン コントローラーに強力な認証ポリシーを適用しています

Microsoft Entra Private Access センサーをドメイン コントローラーに展開しない場合、脅威アクターは、管理されていないエンドポイントや侵害されたエンドポイントを含め、ネットワーク上の任意のデバイスからの Kerberos 認証要求を利用できます。 この脆弱性を使用して、多要素認証やデバイス コンプライアンス検証なしでオンプレミス リソースのサービス チケットを取得できます。

プライベート Access センサーをドメイン コントローラーに展開しない場合:

- 脅威アクターは、ファイル共有、データベース サーバー、remote desktop サービスなどの特権リソースの Kerberos チケットを要求できます。 この脆弱性により、オンプレミス環境全体で横移動が可能になります。
- 条件付きAccess ポリシーは Kerberos 認証には適用されません。これは、認証されたユーザーが認証の強度やデバイスの状態に関係なくチケットを要求できる境界ベースの信頼モデル内で動作するためです。
- フィッシング詐欺や資格情報の盗難によって取得された侵害されたユーザー資格情報をすぐに使用して、多要素認証要件をトリガーすることなくドメイン認証リソースをaccessできます。

**修復アクション**

- [Active Directory ドメイン コントローラーのためのMicrosoft Entra Private Accessの構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-domain-controllers)。

#### クイック Accessにはユーザーまたはグループの割り当てがあります

クイック Accessにユーザーまたはグループの割り当てが不足している場合、サービスは、アプリケーション セグメントで構成する完全修飾ドメイン名 (FQDN) と IP アドレスへの接続を禁止します。 この制限により、ファイル共有、Web アプリケーション、データベースなどの内部リソースへのaccessが中断されます。 ユーザーがグローバル セキュア Access クライアントを介してリソースにアクセスできない場合、条件付きAccess ポリシーや多要素認証などのセキュリティ制御をバイパスする代替のaccess方法が必要になる場合があります。

クイック Accessにユーザーを割り当てない場合:

- 承認されたユーザーはプライベート Accessを通じて内部リソースにアクセスできないため、ビジネス継続性にギャップが生じます。
- 管理者は、組織のセキュリティ体制を弱める一時的な回避策を実装する場合があります。

**修復アクション**

- ユーザーとグループを Quick Access構成されたアプリケーション セグメントへのプライベート Access接続を有効にします。

#### すべてのプライベート Access アプリにユーザーまたはグループの割り当てがある

Microsoft Entra Private Accessアプリケーションにユーザーまたはグループの割り当てが不足している場合、ユーザーは、構成済みの完全修飾ドメイン名 (FQDN) と IP アドレスに到達するためのトンネルをアプリケーション経由で確立できません。 この制限により、保護された内部リソースへのaccessが防止されます。 割り当てがないと、組織は条件付きAccess ポリシーを適用できません。これらのポリシーでは、リスクシグナル、デバイス コンプライアンス、認証強度の要件を評価するために明示的なユーザーとアプリケーションの関係が必要であるためです。

プライベート Access アプリケーションでのユーザー割り当てなし:

- 組織は、ユーザーが必要な特定のリソースにのみaccessできる最小限の特権access制御を適用する機能を失います。
- 組織では、サインイン リスク、ユーザー リスク、またはデバイスコンプライアンスに基づいて認証をブロックまたはチャレンジするリスクベースのaccess ポリシーを適用することはできません。
- 資格情報の侵害、あり得ない移動、または匿名 IP アドレスを検出する ID 保護シグナルは、プライベート リソースを保護できません。

**修復アクション**

- [ユーザーとグループをプライベートアクセスアプリケーションに割り当て](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-per-app-access#assign-users-and-groups)、ゼロ トラスト アクセス制御と条件付きアクセスの適用を有効にします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/zero-trust-protect-tenants"} -->
## セキュリティ ガイダンス - テナントを保護し、運用システムを分離する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants
- Service: entra / fundamentals
- Article date: 2026-10-01
- Summary: Microsoft Entra ゼロ トラスト 評価を使用してセキュリティ体制を改善し、テナントを保護し、運用システムを分離します。

テナントを保護し、運用システムを分離することは、テナントの境界を設定し、運用システムをテスト環境と運用前環境から分離し続けることです。 横移動は、 [セキュア・フューチャー・イニシアティブ](https://www.microsoft.com/trust-center/security/secure-future-initiative?msockid=2bad2df65a416adb0e5838355b3e6b95#SFI-pillars)の重大な懸念事項でした。

小規模な組織でも、より厳密なゲスト アクセス ポリシーを実装し、テナントを作成できるユーザーを制限することで、環境を保護できます。 複数の環境を管理する大規模な組織は、承認されていないテナントの拡散と横移動を防ぐためにアクションを実行する必要があります。 すべての組織は、これらのチェックを利用して、管理されていないテナントを通じて攻撃対象領域を減らすことができます。

### ゼロ トラストのセキュリティに関する推奨事項

#### 新しいテナントを作成するためのアクセス許可は、テナント作成者ロールに制限されます

制限がない場合、脅威アクターや善意はあるが情報不足の従業員が新しい Microsoft Entra テナントを作成することができます。 既定では、テナントを作成するユーザーには、グローバル管理者ロールが自動的に割り当てられます。 適切な制御がない場合、このアクションは、組織のガバナンスと可視性の外部にテナントを作成することで、ID 境界を破壊します。 トークンの発行、ブランド偽装、同意フィッシング、永続的なステージング インフラストラクチャに悪用される可能性があるシャドウ ID プラットフォームでもリスクが生じます。 ローグテナントが企業の管理や監視プレーンにつながっていない可能性があるため、従来の防御ではその作成、活動、及び潜在的な誤用を検知できません。

**修復アクション**

[ **管理者以外のユーザーによるテナントの作成を制限する** ] 設定を有効にします。 テナントを作成する機能が必要なユーザーには、テナント作成者ロールを割り当てます。 Microsoft Entra 監査ログでテナント作成イベントを確認することもできます。

- [メンバー ユーザーの既定のアクセス許可を制限する](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#restrict-member-users-default-permissions)
- [テナント作成者ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator)
- [テナント作成イベントを確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities#core-directory)。 OperationName=="Create Company"、Category == "DirectoryManagement" を検索します。

#### 影響の大きい管理タスクに対して保護されたアクションが有効になっている

テナントへの特権アクセス権を取得する脅威アクターは、ID、アクセス、およびセキュリティ構成を操作できます。 この種の攻撃により、環境全体が侵害され、組織の資産の制御が失われる可能性があります。 条件付きアクセス ポリシー、テナント間アクセス設定、ハード削除、セキュリティの維持に不可欠なネットワークの場所に関連付けられている影響の大きい管理タスクを保護するためのアクションを実行します。

保護されたアクションにより、管理者は、強力な認証方法 (パスワードレス MFA またはフィッシングに強い MFA)、特権アクセス ワークステーション (PAW) デバイスの使用、セッション タイムアウトの短縮など、追加のセキュリティ制御を使用してこれらのタスクをセキュリティで保護できます。

**修復アクション**

- [Microsoft Entra ID で保護されたアクションを追加、テスト、または削除する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-add)

#### 保護されたアクションを有効にして、条件付きアクセス ポリシーの作成と変更をセキュリティで保護する

テナントへの特権アクセス権を取得する脅威アクターは、条件付きアクセス ポリシーを操作でき、重要なセキュリティ制御を無効にし、永続的なアクセスまたは横移動を有効にする可能性があります。 この種の攻撃では、認証と承認の障壁をバイパスすることで、環境全体の侵害が発生する可能性があります。

保護されたアクションにより、管理者は、強力な認証方法 (パスワードレス MFA またはフィッシングに強い MFA)、特権アクセス ワークステーション (PAW) デバイスの使用、セッション タイムアウトの短縮など、追加のセキュリティ制御を使用して条件付きアクセス ポリシーの作成と変更をセキュリティで保護できます。

**修復アクション**

- [Microsoft Entra ID で保護されたアクションを追加、テスト、または削除する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-add)

#### ゲスト アクセスは、承認されたテナントに制限されます

既知および承認されたテナントの一覧へのゲスト アクセスを制限すると、脅威アクターが無制限のゲスト アクセスを利用して、侵害された外部アカウントを介して、または信頼されていないテナントにアカウントを作成することによって初期アクセスを確立するのを防ぐことができます。 無制限のドメインを介してアクセスできる脅威アクターは、内部リソース、ユーザー、およびアプリケーションを検出して、追加の攻撃を実行できます。

組織はインベントリを取得し、許可リストまたはブロックリストを構成して、特定の組織からの B2B コラボレーションの招待を制御する必要があります。 これらの制御がないと、脅威アクターはソーシャル エンジニアリング手法を使用して、正当な内部ユーザーから招待を取得する可能性があります。

**修復アクション**

- [承認済みドメインの一覧を設定する](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list#add-an-allowlist)方法について説明します。

#### ゲストに高い特権ディレクトリ ロールが割り当てられない

グローバル管理者や特権ロール管理者などの高い特権を持つディレクトリ ロールがゲスト ユーザーに割り当てられている場合、組織は、脅威アクターが侵害された外部アカウントまたはビジネス パートナー環境を介して初期アクセスのために悪用できる重大なセキュリティ脆弱性を作成します。 ゲスト ユーザーはセキュリティ ポリシーを直接制御せずに外部組織から発信されるため、これらの外部 ID を侵害した脅威アクターは、ターゲット組織の Microsoft Entra テナントへの特権アクセスを取得できます。

脅威アクターは、昇格された特権を持つ侵害されたゲスト アカウントを通じてアクセス権を取得すると、自分の特権をエスカレートして、他のバックドア アカウントを作成したり、セキュリティ ポリシーを変更したり、組織内で永続的なロールを割り当てたりすることができます。 侵害された特権ゲスト アカウントを使用すると、脅威アクターは永続化を確立し、検出されないようにするために必要なすべての変更を行うことができます。 たとえば、クラウド専用アカウントを作成し、内部ユーザーに適用される条件付きアクセス ポリシーをバイパスし、ゲストの自宅組織が侵害を検出した後でもアクセスを維持できます。 脅威アクターは、管理特権を使用して横移動を実行して、機密性の高いリソースにアクセスしたり、監査設定を変更したり、テナント全体のセキュリティ監視を無効にしたりすることができます。 脅威アクターは、外部ゲスト アカウントの配信元を通じて妥当な拒否性を維持しながら、組織の ID インフラストラクチャの完全な侵害に到達できます。

**修復アクション**

- [特権ロールからゲスト ユーザーを削除する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/best-practices)

#### ゲストは他のゲストを招待できません

外部ユーザー アカウントは、多くの場合、企業とビジネス関係を持つ組織に属するビジネス パートナーへのアクセスを提供するために使用されます。 これらのアカウントが組織内で侵害された場合、攻撃者は有効な資格情報を使用して環境に最初にアクセスできます。多くの場合、正当性のために従来の防御をバイパスできます。

外部ユーザーが他の外部ユーザーのオンボードを許可すると、不正アクセスのリスクが高まります。 攻撃者が外部ユーザーのアカウントを侵害した場合、それを使用してさらに多くの外部アカウントを作成し、アクセス ポイントを乗算し、侵入の検出を困難にすることができます。

**修復アクション**

- [特定の管理者ロールに割り当てられているユーザーのみにゲストを招待できるユーザーを制限する](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure#to-configure-guest-invite-settings)

#### ゲストがディレクトリ オブジェクトへのアクセスを制限している

外部ユーザー アカウントは、多くの場合、企業とビジネス関係を持つ組織に属するビジネス パートナーへのアクセスを提供するために使用されます。 これらのアカウントが組織内で侵害された場合、攻撃者は有効な資格情報を使用して環境に最初にアクセスできます。多くの場合、正当性のために従来の防御をバイパスできます。

ディレクトリ オブジェクトのアクセス許可を読み取るアクセス許可を持つ外部アカウントは、侵害された場合に、攻撃者により広範な初期アクセスを提供します。 これらのアカウントを使用すると、攻撃者は偵察のためにディレクトリから追加情報を収集できます。

**修復アクション**

- [ゲスト アクセスを独自のディレクトリ オブジェクトに制限](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure#to-configure-guest-user-access)

#### アプリ インスタンスのプロパティ ロックがすべてのアプリケーションに対して構成されている

アプリ インスタンスプロパティロックは、アプリケーションのサービス プリンシパルの機密性の高いプロパティへの変更を防ぎます。 このロックは、シングルテナント アプリケーションとマルチテナント アプリケーションの両方に適用されます。 ロックがないと、アプリケーション資格情報などの重要なプロパティが悪意を持って変更されたり、意図せずに変更されたりする可能性があります。これにより、中断、リスクの増加、未承認のアクセス、特権のエスカレーションが発生する可能性があります。

**修復アクション** シングルテナント アプリケーションとマルチテナント アプリケーションを含むすべてのアプリケーションに対してアプリ インスタンスプロパティロックを有効にし、ロックするプロパティを指定します。

- [アプリ インスタンスのロックを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-app-instance-property-locks#configure-an-app-instance-lock)

#### ゲストは有効期間の長いサインイン セッションを持っていません

サインイン セッションが拡張されたゲスト アカウントは、脅威アクターが悪用できるリスク領域を増やします。 ゲスト セッションが必要な期間を超えて持続する場合、脅威アクターは資格情報の詰め込み、パスワード スプレー、ソーシャル エンジニアリング攻撃を通じて初期アクセスを試みることがよくあります。 アクセス権を取得すると、再認証の課題なしに、長期間にわたって未承認のアクセスを維持できます。 これらの侵害されたセッションと拡張セッション:

- Microsoft Entra アーティファクトへの未承認のアクセスを許可することで、脅威アクターは機密性の高いリソースを識別し、組織構造をマップできます。
- 正当な認証トークンを使用して脅威アクターをネットワーク内に保持することを許可し、アクティビティが一般的なユーザーの動作として表示されるため、検出がより困難になります。
- 共有リソースへのアクセス、より多くの資格情報の検出、システム間の信頼関係の悪用などの手法を使用して、特権をエスカレートする時間が長い期間を脅威アクターに提供します。

適切なセッション制御がないと、脅威アクターは、組織のインフラストラクチャ全体で横移動を実現し、元のゲスト アカウントの意図されたアクセス範囲をはるかに超える重要なデータとシステムにアクセスできます。

**修復アクション**

- [アダプティブ セッションの有効期間ポリシーを構成して、](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime) サインイン頻度ポリシーのライブ サインイン セッションが短いようにします。

#### ゲスト アクセスは強力な認証方法によって保護されます

外部ユーザー アカウントは、組織とビジネス関係を持つ組織に属するビジネス パートナーへのアクセスを提供するためによく使用されます。 これらのアカウントが組織内で侵害された場合、攻撃者は有効な資格情報を使用して環境に最初にアクセスできます。多くの場合、正当性のために従来の防御をバイパスできます。

多要素認証 (MFA) がユニバーサルに適用されていない場合、または例外が発生した場合、攻撃者は外部ユーザー アカウントでアクセスする可能性があります。 また、SIM スワップやフィッシングなどのソーシャル エンジニアリング手法を使用して、SMS や電話などの弱い MFA メソッドの脆弱性を悪用して認証コードをインターセプトすることで、アクセスを取得することもできます。

攻撃者は、MFA のないアカウントまたは弱い MFA メソッドを使用したセッションにアクセスすると、MFA 設定 (攻撃者が制御するメソッドの登録など) を操作して、侵害されたアカウントの特権に基づいてさらなる攻撃を計画および実行するための永続化を確立しようとする可能性があります。

**修復アクション**

- [ゲストに認証強度を適用する条件付きアクセス ポリシーを展開します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-guests-mfa-strength)。
- ビジネス関係が近く、MFA プラクティスに関する審査を行っている組織の場合は、MFA 要求を受け入れるためにテナント間アクセス設定を展開することを検討してください。
    - [B2B コラボレーションのテナント間アクセス設定の構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#to-change-inbound-trust-settings-for-mfa-and-device-claims)

#### ユーザー フローを使用したゲスト セルフサービス サインアップが無効になっている

ゲストのセルフサービス サインアップが有効になっている場合、脅威アクターは、承認された担当者からの承認を必要とせずに正当なゲスト アカウントを作成することで、それを利用して未承認のアクセスを確立できます。 これらのアカウントは、特定のサービスにスコープを設定して検出を減らし、外部ユーザーの正当性を検証する招待ベースの制御を効果的にバイパスすることができます。

作成されると、自己プロビジョニングゲスト アカウントによって、組織のリソースとアプリケーションへの永続的なアクセスが提供されます。 脅威アクターは、これらを使用して偵察アクティビティを実施して、内部システムのマッピング、機密データ リポジトリの識別、さらに攻撃ベクトルの計画を行うことができます。 この永続化により、敵対者は再起動、資格情報の変更、その他の中断を通じてアクセスを維持できます。一方、ゲスト アカウント自体は、外部の脅威に焦点を当てたセキュリティ監視を回避する可能性のある一見正当な ID を提供します。

さらに、侵害されたゲスト ID を使用して資格情報の永続化を確立し、特権をエスカレートする可能性があります。 攻撃者は、ゲスト アカウントと内部リソースの間の信頼関係を悪用したり、ゲスト アカウントをステージング グラウンドとして使用して、より特権のある組織資産への横移動を行ったりすることができます。

**修復アクション**

- [Microsoft Entra 外部 ID を使用してゲスト セルフサービス サインアップを構成する](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure#to-configure-guest-self-service-sign-up)

#### テナント間の送信アクセス設定が構成されている

未確認の組織との無制限の外部コラボレーションを許可すると、適切なセキュリティ制御を持たない可能性のあるゲスト アカウントが許可されるため、テナントのリスク領域が増加する可能性があります。 脅威アクターは、これらの緩やかに管理された外部テナントの ID を侵害することで、アクセスを試みることができます。 ゲスト アクセスが許可されると、正当なコラボレーション 経路を使用してテナント内のリソースに侵入し、機密情報の取得を試みることができます。 脅威アクターは、正しく構成されていないアクセス許可を悪用して特権をエスカレートし、さまざまな種類の攻撃を試みることもできます。

共同作業を行う組織のセキュリティを確保しないと、悪意のある外部アカウントが検出されずに保持され、機密データが流出し、悪意のあるペイロードが挿入される可能性があります。 この種の露出は、組織の制御を弱め、従来の境界防御をバイパスし、データの整合性と運用の回復性の両方を損なうテナント間攻撃を可能にする可能性があります。 Microsoft Entra の送信アクセスのテナント間設定では、既定で不明な組織とのコラボレーションをブロックする機能が提供され、攻撃対象領域が減少します。

**修復アクション**

- [テナント間アクセスの概要](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)
- [クロステナント アクセス設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#configure-default-settings)
- [送信アクセス設定を変更する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)

#### ゲストがテナント内のアプリを所有していない

ゲスト ユーザーによるアプリケーションの登録と所有を妨げる制限なしに、脅威アクターは外部ユーザー アカウントを利用して、従来のセキュリティ監視を回避する可能性のあるアプリケーション登録を通じて、組織のリソースへの永続的なバックドア アクセスを確立できます。 ゲスト ユーザーがアプリケーションを所有している場合、侵害されたゲスト アカウントを使用して、広範なアクセス許可を持つ可能性のあるゲスト所有のアプリケーションを悪用できます。 この脆弱性により、脅威アクターは、内部ユーザー所有アプリケーションに対して同じレベルの調査を行うことなく、電子メール、ファイル、ユーザー情報などの機密性の高い組織データへのアクセスを要求できます。

ゲスト所有のアプリケーションは高い特権のアクセス許可を要求するように構成でき、同意が付与されると、脅威アクターに正当な OAuth トークンが提供されるため、この攻撃ベクトルは危険です。 さらに、ゲスト所有のアプリケーションはコマンドおよび制御インフラストラクチャとして機能できるため、脅威アクターは、侵害されたゲスト アカウントが検出されて修復された後でもアクセスを維持できます。 アプリケーションの資格情報とアクセス許可は、元のゲスト ユーザー アカウントとは独立して保持される可能性があるため、脅威アクターはアクセスを保持できます。 また、ゲスト所有のアプリケーションでは、外部ユーザーによって登録されたアプリケーションの目的とセキュリティ体制の可視性が制限される可能性があるため、セキュリティ監査とガバナンスの取り組みも複雑になります。 アプリケーション ライフサイクル管理のこれらの隠れた弱点により、一見正当なアプリケーション登録を通じて Microsoft 以外のエンティティに付与されるデータ アクセスの真の範囲を評価することが困難になります。

**修復アクション**

- アプリケーションとサービス プリンシパルから所有者としてゲスト ユーザーを削除し、将来のゲスト ユーザー アプリケーションの所有権を防ぐためのコントロールを実装します。
- [ゲスト ユーザーのアクセス許可を制限する](https://learn.microsoft.com/ja-jp/entra/identity/users/users-restrict-guest-permissions)

#### すべてのゲストはスポンサーを持っています

外部ゲストの招待は、組織の共同作業に役立ちます。 ただし、各ゲストに内部スポンサーが割り当てされていない場合、これらのアカウントは明確な説明責任を持たずにディレクトリ内に保持される可能性があります。 この監視によってリスクが発生します。脅威アクターは、未使用または監視されていないゲスト アカウントを侵害する可能性があり、テナント内に最初の足掛かりを確立する可能性があります。 明らかな "正当な" ユーザーとしてアクセス権が付与されると、攻撃者はアクセス可能なリソースを探索し、特権エスカレーションを試みる可能性があり、最終的には機密情報や重要なシステムが公開される可能性があります。 そのため、監視されていないゲスト アカウントは、未承認のデータ アクセスや重大なセキュリティ侵害のベクトルになる可能性があります。 一般的な攻撃シーケンスでは、標準の外部コラボレーターを装って、次のパターンを使用できます。

1. 侵害されたゲスト資格情報によって取得された初期アクセス
2. 監視不足による永続性。
3. ゲスト アカウントがグループ メンバーシップまたは昇格されたアクセス許可を持っている場合は、さらにエスカレーションまたは横移動します。
4. 悪意のある目的の実行。

すべてのゲスト アカウントがスポンサーに割り当てられていることを管理すると、このリスクが直接軽減されます。 このような要件により、各外部ユーザーが、アクセスに対するゲストの継続的なニーズを定期的に監視および証明することが期待される責任ある内部当事者にリンクされます。 Microsoft Entra ID 内のスポンサー機能は、招待者を追跡し、"孤立した" ゲスト アカウントの急増を防ぐことで、アカウンタビリティをサポートします。 コラボレーションの終了時にアクセスを削除するなど、スポンサーがゲスト アカウントのライフサイクルを管理すると、脅威アクターが無視されたアカウントを悪用する機会が大幅に減少します。 このベスト プラクティスは、効果的なゲスト アクセス ガバナンス戦略の一環として、ビジネス ゲストにスポンサー シップを要求するための Microsoft のガイダンスと一致しています。 各ゲスト ユーザーのプレゼンスとアクセス許可が継続的な内部監視下にあることを保証するため、コラボレーションを有効にすることとセキュリティを適用することのバランスを取ります。

**修復アクション**

- スポンサーがないゲスト ユーザーごとに、Microsoft Entra ID でスポンサーを割り当てます。
    - [Microsoft Entra 管理センターでゲスト ユーザーにスポンサーを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-sponsors)
    - [Microsoft Graph を使用してゲスト ユーザーにスポンサーを追加する](https://learn.microsoft.com/ja-jp/graph/api/user-post-sponsors?view=graph-rest-1.0&preserve-view=true)

#### 非アクティブなゲスト ID が無効になっているか、テナントから削除される

ゲスト ID がアクティブなままで長期間使用されていない場合、脅威アクターは、これらの休止中のアカウントを組織へのエントリ ベクターとして利用できます。 非アクティブなゲスト アカウントは、多くの場合、セキュリティ チームによって監視されないまま、リソース、アプリケーション、およびデータへの永続的なアクセス許可を維持するため、重要な攻撃対象となります。 脅威アクターは、資格情報の詰め込み、パスワード スプレー、またはゲストのホーム組織を侵害して横アクセスを取得することで、これらのアカウントを頻繁にターゲットにします。 非アクティブなゲスト アカウントが侵害されると、攻撃者は既存のアクセス許可を利用して次のことができます。

- テナント内を横方向に移動する
- グループ メンバーシップまたはアプリケーションのアクセス許可を使用して特権をエスカレートする
- サービス プリンシパルを追加したり、既存のアクセス許可を変更したりするような手法を用いて、環境の永続性を確立する。

これらのアカウントの長時間の休止により、攻撃者は、通常、外部のゲスト アカウントではなくアクティブな内部ユーザーに監視作業を集中するため、偵察を実施し、機密データを流出させ、検出せずにバックドアを確立するためのドウェル時間が長くなります。

**修復アクション**

- [古いゲスト アカウントの監視とクリーンアップ](https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-stale-guest-accounts)

#### すべてのエンタイトルメント管理ポリシーの有効期限が設定されている

有効期限のないエンタイトルメント管理ポリシーでは、脅威アクターが悪用できる永続的なアクセスが作成されます。 ユーザーの割り当てに時間制限がない場合、侵害された資格情報は無期限のアクセスを維持し、攻撃者が永続化を確立し、追加のアクセス パッケージを通じて特権をエスカレートし、検出されずに長期的な悪意のあるアクティビティを実行できるようにします。

**修復アクション**

- [アクセス パッケージの有効期限設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy#specify-a-lifecycle)

#### 外部ユーザーに適用されるすべてのエンタイトルメント管理割り当てポリシーには、接続された組織が必要です

特定の接続された組織ではなく "すべてのユーザー" を許可するように構成されたアクセス パッケージは、制御されていない外部アクセスに組織を公開します。 脅威アクターは、承認されていない組織から侵害された外部アカウントを介してアクセスを要求し、最小限の特権の原則をバイパスすることで、これを悪用できます。 これにより、環境内での初期アクセス、偵察、特権エスカレーション、横移動が可能になります。

**修復アクション**

- [信頼された組織を接続された組織として定義する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization#view-the-list-of-connected-organizations)
- [特定の接続された組織のみを許可するようにアクセス パッケージを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#allow-users-not-in-your-directory-to-request-the-access-package)

#### 外部ユーザーに適用されるすべてのエンタイトルメント管理割り当てポリシーには、承認が必要です

外部ユーザーがアクセスを要求できるようにするアクセス パッケージの割り当てポリシーには、承認が必要です。 承認ゲートがないと、外部ユーザーは監視なしで組織のリソースへのアクセスを自己プロビジョニングできます。 承認を要求すると、指定された承認者が各要求を確認し、アクセスを許可する前に要求者の ID と業務上の正当な理由を検証する機会が提供されます。

**修復アクション**

- [アクセス パッケージの割り当てポリシーの承認を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy)
- [外部ユーザーのアクセス パッケージ ポリシーを確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy)

#### ゲストに適用されるすべてのエンタイトルメント管理パッケージには、割り当てポリシーで有効期限またはアクセス レビューが構成されています

有効期限やアクセス レビューのないゲスト ユーザーのアクセス パッケージを使用すると、組織のリソースに無期限にアクセスできます。 侵害されたゲスト アカウントまたは古いゲスト アカウントを使用すると、脅威アクターは、横移動、特権エスカレーション、およびデータ流出に対して永続的で検出されないアクセスを維持できます。 定期的な検証がないと、組織は、ビジネス関係がいつ変更されたか、ゲスト アクセスが不要になった時点を識別できません。

**修復アクション**

- [ライフサイクル設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy)
- [アクセス レビューを設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create)

#### Microsoft Entra 参加済みデバイスでローカル管理者を管理する

Microsoft Entra 参加済みデバイスのローカル管理者が適切に管理されていない場合、資格情報が侵害された脅威アクターは、組織の管理者を削除し、デバイスの Microsoft Entra への接続を無効にすることで、デバイスの引き継ぎ攻撃を実行できます。 この制御が不足すると、組織の制御が完全に失われるので、管理または回復できない孤立した資産が作成されます。

**修復アクション**

- [Microsoft Entra 参加済みデバイスでローカル管理者を管理する](https://learn.microsoft.com/ja-jp/entra/identity/devices/assign-local-admin#manage-the-microsoft-entra-joined-device-local-administrator-role)

#### 管理者以外のユーザーが所有デバイスの BitLocker キーを回復できないように制限する

管理者以外のユーザーが自分の BitLocker キーにアクセスできる場合、ユーザーの資格情報を侵害する脅威アクターは、特権エスカレーションを必要とせずに暗号化キーに直接アクセスできます。 攻撃者は BitLocker キーを取得すると、キャッシュされた資格情報、ローカル データベース、機密ファイルなど、デバイスに格納されている機密データを復号化できます。

適切な制限なしに、侵害された単一のユーザー アカウントは、そのデバイス上のすべての暗号化されたデータに即座にアクセスし、ディスク暗号化の主なセキュリティ上の利点を否定し、横移動の経路を作成します。

**修復アクション**

- [管理者以外のユーザーが所有するデバイスの BitLocker キーを回復できないように制限する](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities#configure-device-settings)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/zero-trust-response-remediation"} -->
## セキュリティ ガイダンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-response-remediation
- Service: entra / fundamentals
- Article date: 2025-09-12
- Summary: Microsoft Entra ゼロ トラスト 評価を使用してセキュリティ体制を改善し、対応と修復を高速化します。

セキュリティ上の脅威を迅速に検出、対応、修復する機能は、今日の進化する脅威の状況において重要です。 [Secure Future Initiative](https://www.microsoft.com/trust-center/security/secure-future-initiative?msockid=2bad2df65a416adb0e5838355b3e6b95#SFI-pillars) の柱の 1 つとして、対応と修復を加速することで、組織は脅威の検出と封じ込めまでの時間を最小限に抑えることができます。

この柱は、手動による介入を減らし、セキュリティ インシデントのエスカレートを防ぐのに役立つ、リスクベースの自動対応を強調しています。 次の Microsoft Entra ゼロ トラスト 評価チェックでは、リスクの高いシナリオをすばやく特定して軽減するために必要な制御が組織に用意されていることを確認し、プロアクティブなセキュリティ ポリシーを使用してユーザー ID とワークロード ID の両方を保護します。

### ゼロ トラストのセキュリティに関する推奨事項

#### ワークロード ID はリスクベースのポリシーで構成されます

Microsoft Entra ID のリスク ポリシーに基づいて、ワークロード ID のリスクベースの条件付きアクセス ポリシーを設定して、信頼されたワークロードと検証済みのワークロードのみが機密リソースを使用するようにします。 これらのポリシーがないと、脅威アクターは最小限の検出でワークロード ID を侵害し、さらなる攻撃を実行できます。 異常なアクティビティやその他のリスクを検出するための条件付き制御がないと、トークン フォージェリ、機密性の高いリソースへのアクセス、ワークロードの中断などの悪意のある操作に対するチェックはありません。 自動封じ込めメカニズムがないため、ドウェル時間が長くなり、重要なサービスの機密性、整合性、可用性に影響します。

**修復アクション** ワークロード ID のリスクベースの条件付きアクセス ポリシーを作成します。

- [リスクベースの条件付きアクセス ポリシーを作成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity#create-a-risk-based-conditional-access-policy)

#### リスクの高いサインインを制限する

リスクの高いサインインが条件付きアクセス ポリシーによって適切に制限されていない場合、組織はセキュリティの脆弱性にさらされます。 脅威アクターは、侵害された資格情報、資格情報の詰め込み攻撃、または Microsoft Entra ID 保護 が危険な動作として識別する異常なサインイン パターンを通じて、これらのギャップを悪用して初期アクセスを行うことができます。 適切な制限なしに、危険度の高いシナリオで正常に認証された脅威アクターは、認証されたセッションを誤って使用して機密性の高いリソースにアクセスしたり、セキュリティ構成を変更したり、環境内で偵察アクティビティを実施したりすることで、特権エスカレーションを実行できます。 脅威アクターは、制御されていない危険度の高いサインインを通じてアクセスを確立したら、追加のアカウントを作成するか、バックドアをインストールするか、認証ポリシーを変更して組織のリソースへの長期的なアクセスを維持することで、永続化を実現できます。 無制限のアクセスにより、脅威アクターは、認証されたセッションを使用してシステムとアプリケーション間で横移動を行い、機密データ ストア、管理インターフェイス、または重要なビジネス アプリケーションにアクセスする可能性があります。 最後に、脅威アクターは、リスクの高い認証が適切にチャレンジまたはブロックされなかったという事実を利用して、妥当な拒否性を維持しながら、データ流出によって影響を受けたり、ビジネス クリティカルなシステムを侵害したりします。

**修復アクション**

- [昇格されたサインイン リスクに対して MFA を要求する条件付きアクセス ポリシーをデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-sign-in)

#### リスクの高いユーザーへのアクセスを制限する

危険度の高いユーザーが脅威アクターによって侵害されていると想定します。 調査と修復を行わないと、脅威アクターは、侵害された可能性のあるユーザーのアクセス許可に基づいて、スクリプトの実行、悪意のあるアプリケーションのデプロイ、または API 呼び出しの操作を行って永続化を確立できます。 その後、脅威アクターは、誤った構成や OAuth トークンの悪用を悪用して、ドキュメント、SaaS アプリケーション、Azure リソースなどのワークロード間で横方向に移動する可能性があります。 脅威アクターは、機密性の高いファイル、顧客レコード、または独自のコードにアクセスし、正当なクラウド サービスを通じてステルスを維持しながら外部リポジトリに流出させることができます。 最後に、脅威アクターは、構成の変更、身代金のデータの暗号化、または盗まれた情報を使用してさらなる攻撃を行うことで、運用を中断する可能性があり、その結果、財務的、評判的、および規制上の影響が生じます。

パスワードを使用している組織は、パスワードのリセットに依存して、危険なユーザーを自動的に修復できます。

パスワードなしの資格情報を使用する組織では、ユーザー リスク レベルに発生するほとんどのリスク イベントが既に軽減されているため、危険なユーザーの量は大幅に削減する必要があります。 パスワードなしの資格情報を使用する組織内の危険なユーザーは、ユーザー リスクが調査されて修復されるまで、アクセスをブロックする必要があります。

**修復アクション**

- [高いユーザー リスクに対して安全なパスワード変更を要求する条件付きアクセス ポリシーを適用する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user)。
- Microsoft Entra ID 保護 を使用して [、リスクをさらに調査します](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/conditional-access-agent-optimization"} -->
## Microsoft Entra 条件付きアクセスの最適化エージェント

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization
- Service: entra-id / conditional-access
- Article date: 2026-06-23
- Summary: Microsoft Security Copilotを使用したMicrosoft Entra 条件付きアクセス最適化エージェントが組織のセキュリティ保護にどのように役立つかについて説明します。

Microsoft Entra 条件付きアクセス最適化エージェントは、すべてのユーザー、アプリケーション、およびエージェント ID が条件付きアクセス ポリシーによって保護されるようにするのに役立ちます。 エージェントは、 [ゼロ トラスト](https://learn.microsoft.com/ja-jp/security/zero-trust/deploy/identity) と Microsoft の学習に合わせたベスト プラクティスに基づいて、新しいポリシーを推奨し、既存のポリシーを更新できます。 また、エージェントはポリシー レビュー レポート (プレビュー) を作成します。このレポートは、ポリシーの構成ミスを示す可能性のあるスパイクや急落に関する分析情報を提供します。

条件付きアクセスの最適化エージェントは、次のようなポリシーを評価します。

- 多要素認証 (MFA) が必要です。
- デバイス ベースの制御 (デバイス コンプライアンス、アプリ保護ポリシー、ドメイン参加済みデバイス) を適用する。
- レガシ認証とデバイス コード フローをブロックする。

エージェントは、既存のすべての有効なポリシーを評価して、同様のポリシーの潜在的な統合を提案します。 エージェントが提案を検出すると、ワンクリック修復で関連ポリシーを更新させることができます。

Important

条件付きアクセス最適化エージェントでの ServiceNow 統合とアクティビティ ベースの実行は、現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### [前提条件]

- [少なくとも Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview#license-requirements) ライセンスが必要です。
- 使用可能な [セキュリティ コンピューティング ユニット (SKU) が](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage)必要です。 平均して、各エージェントの実行で消費される SCU は 1 つ未満です。
- 適切な Microsoft Entra ロールが必要です。
    - [エージェントを初めてアクティブ化するには、](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)*セキュリティ管理者*ロールが必要です。
    - [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) ロールと [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) ロールは *、エージェントと提案を表示できますが、アクションを実行することはできません*。
    - [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) ロールと [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールは *、エージェントを表示し、提案に対してアクションを実行*できます。
    - [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)に Microsoft Security Copilot へのアクセス権を割り当てることができます。これにより、条件付きアクセス管理者はエージェントを使用できるようになります。
    - ロールの詳細については、「[Assign Security Copilot access](https://learn.microsoft.com/ja-jp/copilot/security/authentication#assign-security-copilot-access)を参照してください。
- デバイス ベースのコントロールには [、Microsoft Intune ライセンスが必要です](https://learn.microsoft.com/ja-jp/intune/intune-service/fundamentals/licenses)。
- [Microsoft Security Copilot のプライバシーとデータ セキュリティを](https://learn.microsoft.com/ja-jp/copilot/security/privacy-data-security)確認します。

#### 制限事項

- エージェントの起動後、実行を停止または一時停止することはできません。 実行には数分かかる場合があります。
- ポリシーの統合では、各エージェントの実行で 40 の同様のポリシー ペアが評価されます。
- Microsoft Entra 管理センターからエージェントを実行することをお勧めします。
- スキャンは 24 時間に制限されます。
- エージェントから提案をカスタマイズまたはオーバーライドすることはできません。
- エージェントは、1 回の実行で最大 300 人のユーザーと 150 のアプリケーションを確認できます。

### 動作方法

条件付きアクセスの最適化エージェントは、過去 24 時間の新しいユーザー、アプリケーション、およびエージェント ID をテナントでスキャンし、条件付きアクセス ポリシーが適用されるかどうかを判断します。 エージェントが条件付きアクセス ポリシーでカバーされていないユーザー、アプリケーション、またはエージェント ID を見つけた場合は、推奨される次の手順が提供されます。

次の手順では、条件付きアクセス ポリシーを有効にするか、変更します。 提案、エージェントがソリューションを識別した方法、およびポリシーに含まれる内容を確認できます。

エージェントが実行されるたびに、次の手順が実行されます。 *これらの初期スキャン手順では、SCU は使用されません。*

1. エージェントは、テナント内のすべての条件付きアクセス ポリシーをスキャンします。
2. エージェントは、ポリシーのギャップと、ポリシーを組み合わせることができるかどうかを確認します。
3. エージェントは、同じポリシーが再度提案されないように、以前の提案を確認します。

エージェントが以前に提案しなかった内容を識別した場合は、次の手順を実行します。 *これらのエージェント アクションステップでは、SCU が使用されます。*

1. エージェントは、統合可能なポリシーギャップまたはポリシーのペアを識別します。
2. エージェントは、指定したカスタム命令を評価します。
3. エージェントは、レポート専用モードで新しいポリシーを作成するか、カスタム命令のロジックを含め、ポリシーを変更するための提案を提供します。

注

Security Copilotでは、テナントに少なくとも 1 つの SCU がプロビジョニングされている必要があります。 その SCU は、SKU を使用しない場合でも、毎月課金されます。 エージェントをオフにしても、SCU の毎月の課金は停止されません。

エージェントからのポリシーの提案は次のとおりです。

- **MFA を要求する**: エージェントは、MFA を必要とし、ポリシーを更新できる条件付きアクセス ポリシーの対象になっていないユーザーを識別します。
- **デバイス ベースの制御が必要**: エージェントは、デバイス コンプライアンス、アプリ保護ポリシー、ドメイン参加済みデバイスなどのデバイス ベースの制御を適用できます。
- **レガシ認証をブロック**する: レガシ認証を持つユーザー アカウントのサインインがブロックされます。
- **デバイス コード フローをブロック**する: エージェントは、デバイス コード フローをブロックするポリシーを探します。
- **危険なユーザー**: エージェントは、リスクの高いユーザーに対してセキュリティで保護されたパスワードの変更を要求するポリシーを提案します。 Microsoft Entra ID P2 ライセンスが必要です。
- **Risky サインイン**: エージェントは、リスクの高いサインインに多要素認証を要求するポリシーを提案します。Microsoft Entra ID P2 ライセンスが必要です。
- **Risky エージェント**: エージェントは、リスクの高いサインインの認証をブロックするポリシーを提案します。Microsoft Entra ID P2 ライセンスが必要です。
- **エージェント支援フロー (プレビュー):**エージェントは、リスクの高いエージェント支援フローをブロックするポリシーを提案します。 Microsoft Entra ID P2 ライセンスが必要です。
- **ポリシーの統合**: エージェントはポリシーをスキャンし、重複する設定を識別します。 たとえば、同じ許可制御を持つポリシーが複数ある場合、エージェントはそれらのポリシーを 1 つに統合することを提案します。
- **詳細な分析**: エージェントは、主要なシナリオに対応するポリシーを評価して、推奨される数を超える例外 (カバレッジの予期しないギャップにつながる) または例外がない (ロックアウトの可能性につながる) 外れ値ポリシーを特定します。
- **詳細な分析 MFA ギャップ分析**: この分析は、MFA または認証の強度を必要とする条件付きアクセス ポリシーによって保護されていないユーザーを識別します。 エージェントは、テナント全体で有効なポリシーとレポート専用ポリシーの両方を評価して、MFA カバレッジ外のユーザー数を計算します。 一般的な原因としては、ポリシーから除外されたユーザー、必要なグループから欠落しているユーザー、重複するポリシー間のギャップが生じることなどがあります。 この分析では、影響を受けたユーザーのサンプルも提供され、最近のサインイン アクティビティによって優先順位が付けられます。そのため、最もリスクの高いギャップを最初に調査できます。
- **エージェント ID の最小特権アクセス (プレビュー)**: エージェント ID のうち、未使用または過剰な権限を持つ Microsoft Graph のアクセス許可が付与されているものを識別します。 次に、未使用のアクセス許可の削除や、広範なアクセス許可をより具体的なアクセス許可に置き換えるなど、最小限の特権の適用を推奨します。

Important

管理者が提案を明示的に承認しない限り、エージェントは既存のポリシーに変更を加えません。

エージェントが提案するすべての *新しい* ポリシーは、レポート専用モードで作成されます。

2 つのポリシーが 2 つ以下の条件または制御によって異なる場合は、統合できます。

### 作業の開始

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. 新しいホーム ページで、エージェント通知カードから [ **エージェントに移動** ] を選択します。

    左側のメニューから **Security Copilot agents** を選択することもできます。

    [Image: 新しいSecurity Copilot エージェント エクスペリエンスを示すMicrosoft Entra 管理センターのスクリーンショット。]
3. [ **条件付きアクセスの最適化エージェント** ] タイルで、[ **詳細の表示**] を選択します。

    [Image: 詳細を表示するためのボタンが強調表示されている [条件付きアクセス エージェント] タイルのスクリーンショット。]
4. [ **エージェントの開始]** を選択して、最初の実行を開始します。

    [Image: [条件付きアクセスの最適化エージェント] ウィンドウでエージェントを起動するためのボタンを示すスクリーンショット。]

エージェントの [ **概要** ] タブの [ **最近** 使った候補] ボックスに候補が表示されます。 その後、ポリシーを確認し、ポリシーへの影響を判断し、必要に応じて変更を適用できます。 詳細については、「 [条件付きアクセスの最適化エージェントの提案を確認して適用する」を](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-review-suggestions)参照してください。

[Image: エージェントの概要と最近の提案のスクリーンショット。候補を確認するためのボタンが強調表示されています。]

### 設定

エージェントには、組織に固有の機能を拡張するための強力な設定がいくつか含まれています。 **[設定]** タブで次の機能を構成できます。詳細については、「[条件付きアクセスの最適化エージェントの設定](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-settings)」を参照してください。

- エージェントを 24 時間ごとに自動的に実行できるようにします。
- [アクティビティベースの実行](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-settings#trigger)を有効にして、関連するテナントの変更が発生したときにエージェントをトリガーします (プレビュー)。
- エージェントを設定して、ユーザーとアプリケーションに対する変更を確認します。
- エージェントがレポート専用モードでポリシーを作成できるようにします。
- エージェントがMicrosoft Teamsを介して[通知を送信](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-settings#notifications)できるようにします。
- エージェントに [段階的なロールアウト 計画の作成を](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-phased-rollout)許可します。
- [ServiceNow との統合を](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-settings#servicenow-integration-preview)有効にして、チケットの自動作成を行います。
- 組織固有の提案に関する [ナレッジ ソース](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-settings#knowledge-sources) をエージェントに提供します。

### 組み込み連携

条件付きアクセスの最適化エージェントは、デバイス管理に Intune を使用し、ネットワーク アクセスにグローバル セキュリティで保護されたアクセスを使用する組織に対してポリシーの提案を行うことができます。

#### Intune の統合

条件付きアクセスの最適化エージェントは、Intune と統合して次の操作を行います。

- Intune で構成されたデバイス コンプライアンスとアプリケーション保護ポリシーを監視します。
- 条件付きアクセスの適用における潜在的なギャップを特定します。

このプロアクティブで自動化されたアプローチにより、条件付きアクセス ポリシーは組織のセキュリティ目標とコンプライアンス要件に合わせて維持されます。 エージェントの提案は、Intune がエージェントにシグナルの一部を提供する点を除き、他のポリシー提案と同じです。

Intune シナリオに関するエージェントの提案は、特定のユーザー グループとプラットフォーム (iOS または Android) を対象としています。 たとえば、エージェントは、Finance グループを対象とするアプリ保護用のアクティブな Intune ポリシーを識別しますが、アプリ保護を適用する十分な条件付きアクセス ポリシーがないと判断します。 エージェントは、iOS デバイス上の準拠アプリケーションを介してのみリソースにアクセスすることをユーザーに要求するレポート専用ポリシーを作成します。

Intune デバイスコンプライアンスとアプリ保護ポリシーを識別するには、エージェントがグローバル管理者または条件付きアクセス管理者 *および* グローバル閲覧者として実行されている必要があります。 エージェントが Intune の提案を生成するには、条件付きアクセス管理者ロールだけでは不十分です。

#### グローバルなセキュリティで保護されたアクセスの統合

Microsoft Entra Internet Access と Microsoft Entra Private Access (総称してグローバル セキュリティで保護されたアクセス) は、条件付きアクセスの最適化エージェントと統合され、組織のネットワーク アクセス ポリシーに固有の提案を提供します。 **ネットワーク アクセス要件を適用するために新しいポリシーを有効にする**という提案は、ネットワーク ロケーションと保護されたアプリケーションを含む Global Secure Access ポリシーを整合させるのに役立ちます。

この統合により、エージェントは条件付きアクセス ポリシーの対象ではないユーザーまたはグループを識別し、承認されたグローバル セキュリティで保護されたアクセス チャネル経由でのみ企業リソースへのアクセスを要求します。 このポリシーでは、ユーザーが企業のアプリやデータにアクセスする前に、組織のセキュリティで保護されたグローバル セキュリティで保護されたアクセス ネットワークを使用して企業リソースに接続する必要があります。 管理されていないネットワークまたは信頼されていないネットワークから接続するユーザーは、グローバル セキュア アクセス クライアントまたは Web ゲートウェイを使用するように求められます。 サインイン ログを確認して、準拠している接続を確認できます。

#### Microsoft Defender の統合

Microsoft Defenderは、条件付きアクセスの最適化エージェントと統合され、定期的な ID 攻撃パターンを条件付きアクセス体制の改善に変えるのに役立ちます。 Defenderは、個々のインシデントに対応するのではなく、調査とアラート全体で ID 関連のシグナルを分析し、それらを一定の時間枠で集計します。 この集計では、個々のインシデントに反応する繰り返しの手法と対象ユーザー コーホートが表示されます。

統合された各パターンについて、Defender は、エージェントが態勢を評価するためのコンテキストを提供する、脅威に関する構造化された分析情報を生成します。 Defenderは脅威コンテキストのみを提供します。修復ロジックは含まれず、変更する条件付きアクセス ポリシーも決定されません。

エージェントは、セキュリティ態勢の強化が必要かどうかを判断するために、各インサイトをテナントの既存の条件付きアクセス ポリシーに照らして評価します。 ポリシーの拡散を最小限に抑えるために、エージェントは、適切なポリシーが存在しない場合に、既存のポリシーを変更するか、新しいレポート専用ポリシーを作成することをお勧めします。 推奨事項には、管理者が承認する前に変更を確認できるように、根拠と影響を受けるユーザー コンテキストが含まれます。 既存のカバレッジがすでにそのリスクに対処している場合、エージェントはその推奨を抑制します。

Defender脅威の分析情報から生成された推奨事項は、Defenderアラート タグ**にリンクされたリストに**表示されます。 推奨事項の詳細には、それに貢献したコンテキストを含むDefender分析情報セクションが含まれており、Microsoft Defenderのアクセス許可に応じて、関連するアラートまたはインシデントに移動できます。 管理者は、ポリシーの変更が有効になる前に、常にDefender主導の推奨事項を確認して承認します。

### エージェントの削除

条件付きアクセスの最適化エージェントを使用しなくなった場合は、エージェント ウィンドウの上部にある [ **エージェントの削除** ] を選択します。 既存のデータ (エージェント アクティビティ、提案、メトリック) は削除されますが、エージェントの提案に基づいて作成または更新されたポリシーはそのまま残ります。 以前に適用された提案は変更されず、エージェントが作成または変更したポリシーを引き続き使用できます。

#### フィードバックの提供

エージェントに関するフィードバックをMicrosoftするには、エージェント ウィンドウの上部にある **Give Microsoft フィードバック** ボタンを使用します。

### FAQs

#### 条件付きアクセスの最適化エージェントとCopilot Chatを使用する必要がある場合

条件付きアクセスの最適化エージェントと Microsoft Copilot Chatは、条件付きアクセス ポリシーに関するさまざまな分析情報を提供します。 次の表は、2 つの機能を比較しています。

| Scenario | 条件付きアクセスの最適化エージェント | Copilot チャット |
| --- | --- | --- |
| **一般的なシナリオ** |  |  |
| テナント固有の構成 | ✅ |  |
| 高度な推論 | ✅ |  |
| オンデマンド分析情報 |  | ✅ |
| 対話型のトラブルシューティング |  | ✅ |
| 継続的なポリシー評価 | ✅ |  |
| 自動化された改善の提案 | ✅ |  |
| 証明機関 (CA) のベスト プラクティスと構成に関するガイダンス | ✅ | ✅ |
| **具体的シナリオ** |  |  |
| 保護されていないユーザーまたはアプリケーションのプロアクティブな識別 | ✅ |  |
| すべてのユーザーに対する MFA およびその他のベースライン制御の適用 | ✅ |  |
| CA ポリシーの継続的な監視と最適化 | ✅ |  |
| ボタンひとつでポリシー変更 | ✅ |  |
| 既存の CA ポリシーと割り当てのレビュー ("ポリシーは Alice に適用されますか?") | ✅ | ✅ |
| ユーザーのアクセスのトラブルシューティング ("Alice が MFA を求められた理由?") |  | ✅ |

#### エージェントをアクティブ化しましたが、アクティビティの状態は [失敗] です。 どうしたんですか。

Privileged Identity Management (PIM) によるロールのアクティブ化が必要なアカウントを使用して、Microsoft Ignite 2025 より前にエージェントをアクティブ化した可能性があります。 そのため、エージェントが実行を試みたときに、その時点でアカウントに必要なアクセス許可がないため、失敗しました。 2025 年 11 月 17 日以降にアクティブ化された条件付きアクセス最適化エージェントでは、アクティブ化したユーザーの ID は使用されなくなりました。

この問題は、[Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-professional/what-is-microsoft-entra-agent-id) に移行することで解決できます。 **[エージェント**] ページのバナー メッセージまたはエージェント設定の **[アクセス許可**] セクションから[エージェント ID の作成]を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/conditional-access-agent-optimization-chat"} -->
## 条件付きアクセスの最適化エージェント チャット

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-chat
- Service: entra-id / conditional-access
- Article date: 2026-05-22
- Summary: Microsoft Entra 条件付きアクセス 最適化エージェント向け Security Copilot のチャット機能について説明します。

条件付きアクセスの最適化エージェントは、管理者が自然言語を使用してエージェントと対話できるようにする会話型インターフェイスを備えています。 このチャット機能を使用すると、エージェントによって生成された提案の理解、詳細情報の要求、ポリシーの詳細の更新を簡単に行うことができます。 チャット機能を使用すると、必要なポリシー提案の詳細のみに焦点を当てることができるため、適切な決定を行うことができます。

### [前提条件]

- [少なくとも Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview#license-requirements) ライセンスが必要です。
- 使用可能な [セキュリティ コンピューティング ユニット (SCU)](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage) が必要です。
- [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、 [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) の各ロールは、チャット機能を使用できます。

#### 制限事項

- チャットを使用して変更できるのは、含まれているユーザーと除外されたユーザーとロールだけです。 現在、アプリケーションの割り当てやその他のフィールドはサポートされていません。
- 現在、 チャットで実行できる操作に関するページに 記載されているシナリオのみを利用できます。

### チャットの使用方法

チャットには、条件付きアクセスの最適化エージェントのメイン ページまたはポリシー提案の詳細ページからアクセスできます。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **条件付きアクセスの最適化エージェント**を参照します。

    - エージェント ページから [ **エージェントとのチャット** ] を選択して、すべてのポリシー候補についてエージェントとチャットします。

    [Image: [エージェントとのチャット] ボタンが強調表示されている条件付きアクセス最適化エージェントのスクリーンショット。]

    - または、ポリシー **提案の [提案の確認** ] を選択し、[ **エージェントとチャット** ] を選択して、その特定のポリシー提案についてエージェントとチャットします。

    [Image: [エージェントとのチャット] ボタンが強調表示されているポリシー提案の詳細ページのスクリーンショット。]

    - Microsoft Entra 管理センターの任意のページから、ナビゲーション メニューから **Copilot chat** を選択し、フォーカスされた会話の **Conditional Access Optimization Agent** を選択します。 全画面表示のチャット ビューから離れると、チャットがサイド パネルに移動して、継続的な対話が行われます。

    [Image: 条件付きアクセス最適化エージェントが強調表示されたCopilotチャット インターフェイスのスクリーンショット。]
3. 「チャットでできること」セクションでサポートされているシナリオの自然言語を使用して 、チャット ウィンドウにプロンプトを入力します。 作業の開始に役立つクイック スタート プロンプトが表示されます。
4. 応答を確認し、推奨される変更を適用します。 詳細については、「 チャットでの変更の確認 」セクションを参照してください。 すべてのチャット応答には、応答をコピーし、応答に関するフィードバックを提供するオプションが用意されています。

    [Image: 変更を確認または取り消すオプションを含む条件付きアクセス最適化エージェント チャットのスクリーンショット。]

### チャットの変更を確認する

ポリシーの提案に関する詳細と必要な説明を取得したら、エージェントにチャットから直接ポリシーを調整させることができます。 既存のポリシーを更新するか、レポート専用モードで新しいポリシーを作成することをお勧めします。そのため、提案された変更を確認したときに何が起こるかを把握することが重要です。

#### 既存のポリシーを更新する

既存のポリシーに対する変更を確認した場合の動作を示すために、既存のポリシーに 21 人のユーザーを追加する提案を詳しく見てみましょう。 チャットで、エージェントは breakglass アカウントを除外するように求められ、一致する breakglass アカウントを特定しました。

[Image: [変更の確認] ボタンが強調表示されているチャットのスクリーンショット。]

**[確認**] を選択すると、エージェントは*ポリシーに直接変更を加えます*。 ただし、元の提案は、ポリシーに 21 人のユーザーを追加することです。 ポリシーの更新からアカウントを除外したため、この提案は最近の提案リストに引き続き表示されます。 今後のエージェント実行では、除外するユーザーが特定される可能性があります。 ポリシーを変更しない場合は、[ **キャンセル]** を選択します。

#### 新しいポリシーの作成

エージェントが *レポート専用モードで*新しいポリシーを作成する場合は、チャットを使用してポリシーを調整し、ポリシーを有効にすることもできます。 新しいポリシーにチャットを使用している場合、最初の **[確認** ] ボタンをクリックすると、レポート専用モードでポリシーが更新されます。

もう一度ポリシーを有効にするように求められます。 この手順で **[確認** ] を選択すると、ポリシーが有効になります。 ポリシーをオンにせずにレポートのみの変更を保存するには、[ **キャンセル] を** 選択します。

### チャットでできること

条件付きアクセスの最適化エージェント のチャット インターフェイスを使用すると、自然言語を使用して、ポリシーの提案に関する詳細情報を取得したり、エージェントに提案を別の順序で表示したりすることができます。

[Image: [エージェントとのチャット] ボタンが強調表示されている条件付きアクセス最適化エージェントのスクリーンショット。]

#### エージェントの機能について説明する

条件付きアクセスの最適化エージェントにはいくつかの機能が用意されているため、エージェントに対して何ができるか、およびポリシー管理を支援する方法を尋ねるのに役立つ場合があります。 エージェントは、サポートされている機能を説明できます。

サンプル プロンプト:

- *条件付きアクセス最適化エージェントは何に役立ちますか?*
- *自分の能力を要約してください。*

#### 提案に優先順位を付ける

チャット機能を使用すると、エージェントに提案の優先順位付けを依頼できます。 エージェントは、ポリシー変更の潜在的な影響を比較し、 [ゼロ トラスト原則](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)に基づいて候補のランク付けされた一覧を提供するため、完全な一覧を確認してその決定を自分で行う必要はありません。

サンプル プロンプト:

- *最初に実装する必要がある提案はどれですか?*
- *提案に優先順位を付けます。*

エージェントに提案のリストに優先順位を付け、特定の提案の詳細を求める場合、エージェントは優先順位付けされたリストの順序を使用します。

#### 詳細を要求する

各提案の詳細を調べるのではなく、エージェントに詳細情報を求めることができます。 提案の完全な一覧に優先順位を付けることをエージェントに依頼した後、特定のポリシー提案の詳細を提供するようにエージェントに依頼できます。 ポリシーの詳細からチャットにアクセスして、ポリシー提案の詳細を確認することもできます。

サンプル プロンプト:

- *最初の提案について詳しく教えてください。*
- *"危険なユーザー ポリシーを有効にする" という提案について説明します。*

提案の順序に優先順位を付けるようエージェントに依頼しなかった場合、最初の提案の詳細を確認すると、エージェントから [ **最近の提案** ] リストの最初の項目が表示されます。

#### エージェントの結果を説明する

ポリシーの提案に含まれる結果の詳細をエージェントに説明してもらいます。 エージェントは、提案の背後にあるロジックと、セキュリティとユーザー エクスペリエンスへの影響を明確にしています。

サンプル プロンプト:

- *この提案に含まれる 12 人のユーザーは誰ですか?*
- *エージェントによって検出された保護されていない 100 個のアプリケーションは何ですか?*
- *このポリシーを有効にする必要がある理由*
- *段階的なロールアウトの提案を受け入れるとどうなりますか?*

#### すべてのエージェントの調査結果を要約する

エージェントが複数の提案を特定した場合、潜在的な変更や影響を受けるユーザー、グループ、アプリケーションを追跡することが困難な場合があります。 エージェントに対して、複数の提案に対するすべての結果を要約して、概要を把握するように依頼できます。 エージェントは、優先度またはカテゴリ別にグループ化された主要な提案の概要を提供します。

サンプル プロンプト:

- *条件付きアクセス評価の概要を示します。*
- *すべての提案の概要を提供します。*

#### エージェントの決定事項を理解する

場合によっては、エージェントは、一見すると他のポリシーと同様に表示される可能性があるいくつかのポリシー提案を特定することがあります。 複数のポリシーを適用できる場合に、1 つのポリシーが 1 つの更新プログラムに選択された理由を理解できるようにエージェントに依頼できます。 エージェントが更新用の特定のポリシーを選択した理由をチャットで確認できます。

サンプル プロンプト:

- *このポリシーが他のポリシーではなく更新プログラムに選択されたのはなぜですか?*
- *ポリシーの選択について説明します。*

#### ポリシーを編集する

チャット機能を使用して、エージェントの提案で参照されたポリシーを編集する際に、ユーザーまたはロールを含めたり除外したりする変更ができます。

サンプル プロンプト:

- *このポリシーから user1 を除外します。*
- *このポリシーに breakglass アカウントを追加します。*
- *ヘルプデスク管理者ロールを含めます。*

注

条件付きアクセスの最適化エージェントは、緊急 ("breakglass") アカウントである可能性が高いユーザーとグループを識別し、ポリシーからの除外を事前に提案できます。

現時点では、含まれているアプリケーションまたは除外されたアプリケーションの編集はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/conditional-access-agent-optimization-knowledge-base"} -->
## 条件付きアクセスの最適化エージェントのナレッジ ベース

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-knowledge-base
- Service: entra-id / conditional-access
- Article date: 2026-06-10
- Summary: 条件付きアクセスの最適化エージェントが、組織固有の標準に基づいて調整されたポリシーの推奨事項を作成するのにナレッジ ベースがどのように役立つかについて説明します。

条件付きアクセス ポリシーを使用してリソースへのアクセスを保護する組織は、基準とパターンを確立して整理する必要があります。 たとえば、一貫性のある名前付け規則を使用すると、ポリシーの重複やギャップを防ぎ、整理を維持できます。 条件付きアクセスの最適化エージェントは、これらの標準をマッピングするあなたの組織のドキュメントを活用し、設計されたパターンを適用して、その文脈を考慮に入れながら操作を行うことができます。

エージェントには、一般的なベスト プラクティスのみに依存するのではなく、ポリシーの名前付け方法、管理者を通常のユーザーから分離する方法、常に除外する必要があるアカウントなど、組織独自の規則が組み込まれています。 これにより、テナントでの条件付きアクセスの管理方法をより適切に反映する推奨事項が生成されます。

ナレッジ ベースは、次のような環境で特に役立ちます。

- 異なるユーザー ペルソナには、管理者、従業員ユーザー、請負業者などの個別のポリシー セットが必要です
- ポリシーの名前付け標準が適用される
- Breakglass アカウントは一貫して除外する必要があります
- 必要な条件付きアクセス ポリシーの定義済みのセットをテナント全体で維持する必要がある

### ナレッジ ベースのしくみ

ナレッジ ベースを設定して使用する一般的なプロセスは次のとおりです。

1. **アップロード ガイダンス**: 管理者は、組織の条件付きアクセス標準を記述した 1 つの Word (.docx) または PDF ドキュメントをアップロードします。 テンプレートをダウンロードすることも、独自のドキュメントをアップロードすることもできます。
2. **エージェントによる解釈**: エージェントは、より広範なガバナンスまたは運用ドキュメントに埋め込まれている場合でも、ドキュメントを解析し、条件付きアクセス関連のガイダンスを抽出します。
3. **構造化された理解**: エージェントは、アップロードされたガイダンスの理解を表す自然言語の概要を生成します。
4. **今後の推奨事項へのアプリケーション**: 承認された理解は、エージェントによって生成される今後の条件付きアクセスの推奨事項に適用されます。 既存の推奨事項はさかのぼって変更されません。

### ナレッジ ベース ファイルのコンポーネント

使用可能で効果的なナレッジ ベース ファイルは、詳細、具体的、構造化されている必要があります。 このファイルには、条件付きアクセス最適化エージェントが情報に基づいた意思決定を行うために使用できる、明確で実用的な情報が含まれている必要があります。

エージェント設定からテンプレートをダウンロードして、開始点として使用できます。 このテンプレートには、サポートされている各カテゴリのセクションを含む構造化された形式が用意されているため、組織の特定の詳細を入力できます。

[Image: ナレッジ ベース テンプレートのダウンロード オプションのスクリーンショット。]

#### ペルソナベースのポリシー設計

条件付きアクセス ポリシーを使用して、組織内のさまざまなユーザー集団をセキュリティで保護する方法について説明します。 複数のポリシーが同じ制御 (MFA など) を適用する場合、エージェントはこのガイダンスを使用して、ユーザーのペルソナに基づいて適切なポリシーを選択します。 その例は次のとおりです。

- 通常の従業員ユーザーがベースライン ポリシーに含まれる
- 管理者は、ベースライン ポリシーと、特定のニーズに合わせて専用のポリシー セットに含まれる場合があります。
- 請負業者は、ベースラインとは別の独自のポリシーによって管理されます

条件付きアクセス戦略で特定のポリシーを正社員に適用する場合は、正社員の定義方法について説明します。 たとえば、これらの従業員は特定のユーザー属性またはグループ メンバーシップで定義されていますか?

明示的にする。 個人ベースのポリシー設計がロールに基づいている場合は、Microsoft Entra ID の組み込みロールの正式な名称を正確に指定してください。 たとえば、「管理者特権を持つユーザー」ではなく「条件付きアクセス管理者」と言います。

#### ポリシーの名前付け規則

必要な構造、順序、用語など、条件付きアクセス ポリシーの名前付け方法を指定します。

エージェントは、次の場合にこのガイダンスを使用します。

- 新しいポリシーの作成
- 同様のポリシーのマージ
- ポリシー名の変更に関する推奨事項の生成

#### Breakglass アカウントの処理

緊急アクセス (ブレークグラス) ID を表すアカウントまたはグループと、それらの除外方法を定義できます。

エージェントは、次の場合にこのガイダンスを適用します。

- 新しいポリシーの作成
- 不足している除外の特定
- 既存のポリシーに対する更新プログラムの推奨

#### 望ましい条件付きアクセス ポリシー

組織がテナント全体に配置する予定の条件付きアクセス ポリシーのセットを定義します。 目的のポリシーごとに、ポリシーで適用する必要があるターゲット ユーザー、アプリケーション、条件、および許可コントロールについて説明します。

エージェントでは、このガイダンスを使用して次の操作を行います。

- 現在の条件付きアクセス ポリシーを目的の状態に対して監査する
- 必要なポリシーが不足しているか不完全であるギャップを特定する
- カバー範囲の不足を解消し、組織で意図している構成に合わせるための新しいポリシーを提案する

たとえば、クラウド アプリケーションにアクセスするときに、すべてのゲスト ユーザーが MFA で認証を行う必要がある組織の場合は、その期待をナレッジ ベースで記述します。 エージェントは、テナントで現在構成されているポリシーと目的の状態を比較し、不足しているポリシーの推奨事項を表示します。

必要な各ポリシーを完全な命令として記述します。 例えば次が挙げられます。

- "Windowsまたは macOS からOffice 365にアクセスするすべてのユーザーに MFA と準拠デバイスを必要とする条件付きアクセス ポリシーを作成します。 ブレーク グラスとサービス アカウントを除外します。 ポリシーをレポート専用に設定します。
- "すべてのアプリにアクセスするすべてのユーザーのレガシ認証をブロックする条件付きアクセス ポリシーを作成します。 EmergencyAccess グループを除外します。 ポリシーを有効に設定します。

### ナレッジ ベースにファイルを追加する

セットアップ プロセスを簡略化するために、開始点として使用するテンプレートをダウンロードできます。 テンプレートは、エージェント設定で直接使用できます。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **条件付きアクセス最適化エージェント**&gt;**Settings**&gt;**Knowledge ソース**を参照します。
3. [ **ファイル テンプレートのダウンロード** ] を選択して、ナレッジ ベース テンプレート ファイルをダウンロードします。
4. テンプレート (CA\_Knowledge\_Base\_Template.docx) を開き、プレースホルダーコンテンツを組織の特定の条件付きアクセス標準、名前付け規則、ブレークグラス アカウント、および必要なポリシーに置き換えます。
5. ファイルをWord (.docx) または PDF ドキュメントとして保存します。
6. **[ナレッジ ソース**] セクションに戻り、[**アップロード**] ボタンを選択します。

    [Image: ナレッジ ベース テンプレートオプションのスクリーンショット。]
7. 開いたパネルにファイルをドラッグ アンド ドロップするか、[ファイルの **アップロード** ] を選択してコンピューター上のファイルに移動します。

    [Image: ナレッジ ベースのアップロード パネルのスクリーンショット。]

エージェントはファイルを処理して分析し、必要な情報が含まれていることを確認します。

Note

テンプレートを使用する必要はありません。 組織の条件付きアクセス標準を含む任意のWord (.docx) または PDF ドキュメントをアップロードできます。 このテンプレートは、サポートされている各カテゴリのセクションを含む便利な開始点を提供します。

### ナレッジ ベースの影響を受けた推奨事項

ナレッジ ベースにガイダンスを正常に追加すると、条件付きアクセスの最適化エージェントは、次のシナリオのガイダンスに従うことができます。

- **ベースライン ポリシーの作成**: 新しく推奨されるポリシーは、テナントの名前付け基準に従い、正しい除外を含めます。
- **ポリシーのマージの提案**: 同様のポリシーが統合されると、結果のポリシーには組織の標準が反映されます。
- **ユーザー ドリフトの修復**: 新しいユーザーが既存の対象範囲外になると、エージェントはペルソナのガイダンスに基づいて適切なポリシーを選択します。
- **Breakglass の修復**: 緊急アクセスアカウントを除外するための推奨事項には、適切なユーザーまたはグループが含まれます。
- **ポリシーの名前付けの修復**: ポリシーが定義された名前付け基準に従っていない場合、エージェントは適切な名前の置換を推奨します。
- **必要なポリシー監査**: 目的の条件付きアクセス ポリシーを定義すると、エージェントはそれらを現在の構成と比較し、ギャップを埋めるために新しいポリシーを推奨します。

### ナレッジ ベースを使用する必要がある場合

組織が次の場合は、ナレッジ ベースの使用を検討してください。

- 厳密な条件付きアクセスの名前付け標準を維持する
- ユーザー ペルソナまたはリスク プロファイルによってポリシーを分離します
- 条件付きアクセス ポリシーを定期的に監査する
- 内部ガバナンス プロセスに合わせて推奨事項が必要
- テナント全体で特定の条件付きアクセス ポリシーのセットを定義して適用する必要がある

### ナレッジ ベース ドキュメントを記述するためのベスト プラクティス

エージェントの推奨事項の品質は、ナレッジ ベース ドキュメントの明確さと特定性によって異なります。 最適な結果を得るには、次のガイドラインに従ってください。

- **明示的かつ具体的である。** オブジェクト名、グループ名、ロール名Microsoft Entra正確に使用します。 たとえば、"管理者特権を持つユーザー" ではなく"条件付きアクセス管理者" と言います。
- **完全な手順を記述します。** 各ステートメントは、単独でアクション可能である必要があります。 箇条書きを一覧表示する代わりに、意図した動作を記述する完全な文を記述します。
- **用語を定義します。** 組織で "Admin" や "Guest" などのペルソナを使用している場合は、ロール、グループ メンバーシップ、またはユーザー属性を使用して、各ペルソナに属するユーザーを正確に定義します。
- **各要件に適用範囲を含めます。** 適用するユーザー、アプリ、条件、コントロール、除外を指定します。 不足している情報をエージェントに推測させないでください。
- **指定されたテンプレートを使用します。** エージェント設定の **[ナレッジ ソース** ] セクションからナレッジ ベース テンプレートをダウンロードして、ドキュメントが想定される構造に従っていることを確認します。

ナレッジ ベース テンプレートには、次のセクションが含まれています。

- **名前付け規則**: 条件付きアクセス ポリシーに名前を付けるための正確なパターンと許可される値。
- **Breakglass アカウント**: 緊急アクセスとして指定された ID またはグループと、それらを除外するための規則。
- **ペルソナの定義とポリシー カバレッジ**: ユーザーの母集団をセグメント化する方法と、各ペルソナに適用されるポリシー。
- **ベースライン ポリシーの要件**: 組織が適用する最小限のアクセス制御を説明する、予想されるポリシーの状態の一覧。

Tip

テンプレート内のすべてのプレースホルダー テキストをテナント固有の情報に置き換えます。 一般的またはあいまいな説明により、エージェントが関連する提案を生成する能力が低下します。

### スコープと制限事項

ナレッジ ベースには、次の制約があります。

- テナントごとに 1 つのナレッジ ベース ドキュメント
- サポートされているファイル形式: Word (.docx) と PDF
- 最大ファイル サイズ: 5 MB
- ナレッジ ベースは、今後のエージェントの実行にのみ適用されます

ドキュメントが一覧の条件を満たしていない場合、アップロード プロセスが失敗する可能性があります。 ドキュメントに秘密度ラベルが適用されている場合は、アップロードも失敗する可能性があります。 組織は秘密度ラベルの条件をカスタマイズできるため、特定の秘密度ラベルを提案することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/conditional-access-agent-optimization-logs-metrics"} -->
## 条件付きアクセス最適化エージェントのログとメトリック

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-logs-metrics
- Service: entra-id / conditional-access
- Article date: 2026-03-16
- Summary: 監査ログ内の Microsoft Entra 条件付きアクセス最適化エージェントに関するメトリクスとイベントについて、Security Copilot が説明します。

条件付きアクセスの最適化エージェントは、サインイン パターンを自動的に分析し、ポリシーの最適化を提案することで、組織のセキュリティ体制を改善するのに役立ちます。 この Microsoft Security Copilot エージェントは、保護されていないユーザーとアプリケーションを識別し、ポリシーの改善を推奨し、冗長なポリシーを統合するのに役立ちます。

透明性を確保し、自動化された推奨事項を管理するために、Microsoft Entra ID は、すべてのエージェント アクティビティの包括的なログとメトリックを提供します。 この記事では、エージェントのパフォーマンスを監視し、監査ログを確認し、セキュリティ環境に対するエージェントの影響を測定するのに役立つメトリックを理解する方法について説明します。

### [前提条件]

- Microsoft Entra 監査ログを表示するには、少なくとも [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) ロールが必要です。
- [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) ロールと [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) ロールは、エージェントと提案を表示できますが、アクションを実行することはできません。
- [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、 [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)の各ロールは、エージェントを表示し、提案に対してアクションを実行できます。
    - 条件付きアクセスの最適化エージェントのロールの詳細については、「[セキュリティCopilotアクセスの割り当て](https://learn.microsoft.com/ja-jp/copilot/security/authentication#assign-security-copilot-access)」を参照してください。
- [Microsoft Security Copilot でプライバシーとデータ セキュリティを](https://learn.microsoft.com/ja-jp/copilot/security/privacy-data-security)確認する

### エージェントの概要

[条件付きアクセスの最適化エージェント] ページの上部にあるエージェントの **概要** には、過去 30 日間にエージェントが検出した内容の簡単な概要が表示されます。 エージェントによって使用される [セキュリティ コンピューティング ユニット (SCU)](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage) の合計数も提供されます。

[Image: エージェントの概要タイルのスクリーンショット。]

- **検出された保護されていないユーザー**: エージェントによって識別され、エージェントによって提案されたポリシーによって保護されたユーザーの数。
- **検出された保護されていないアプリ**: エージェントによって識別され、エージェントによって提案されたポリシーによって保護されたアプリケーションの数。
- **保護されたサインイン**: エージェントによって提案されたポリシーによって保護されたサインインの数。
- **使用されたセキュリティ コンピューティング ユニット**: 過去 30 日間にエージェントによって消費された SKU の合計数。

エージェントの概要の値には、提案が適用された後のアクティビティが反映されます。 エージェントを実行し、提案を適用しない場合、エージェントの概要の値は変更されません。

### Insights ダッシュボード (プレビュー)

条件付きアクセスの最適化エージェントには、エージェントの提案を適用することによって行われた機能強化のデータ視覚化を提供する分析情報ダッシュボードが含まれています。 ダッシュボードは、カスタム レポートを作成せずにゼロ トラスト投資の価値を示すために、リーダーと進捗状況を共有するための優れた方法です。

[Image: 分析情報ダッシュボードのスクリーンショット。]

分析情報ダッシュボードには、次のメトリックが表示されます。

- **対象範囲が改善されたオブジェクト**: エージェントによって識別された重大なポリシー ギャップを持つユーザー、アプリケーション、またはエージェント ID が、過去 30 日間に少なくとも 1 つの新しいコントロールでカバーされるようになりました。
- **対象範囲がないオブジェクト**: 現在、1 つ以上の重要なポリシーから対象範囲が欠落しているユーザー、アプリケーション、またはエージェント ID。

ダッシュボードには、特定のメトリックにドリルダウンするためのリンクと、レポートを CSV としてダウンロードするためのオプションが用意されています。

### 監査ログ

Microsoft Entra の Security Copilot エージェントに関連付けられているアクティビティは、Microsoft Entra 監査ログと Microsoft Purview の Security Copilot 監査ログに表示されます。 各サービスは、エージェントのアクティビティに関するさまざまな情報を提供します。

Microsoft Purview ログには、テナント レベルの管理アクションと、Security Copilot プラットフォーム内でのユーザー操作が含まれます。 詳細については、「 [セキュリティ の Copilot 監査ログにアクセスする](https://learn.microsoft.com/ja-jp/copilot/security/audit-log)」を参照してください。

Microsoft Entra 監査ログには、条件付きアクセス ポリシーなどの Microsoft Entra リソースに対するエージェントによって行われた変更が含まれます。 エージェントによって作成または変更されたポリシーは、[条件付きアクセス ポリシー] ウィンドウで **条件付きアクセスの最適化エージェント** でタグ付けされます。

[Image: エージェント ページの上部にある [エージェントの概要] タイルのスクリーンショット。]

**[監査ログ**] の [**開始者] (アクター)** フィールドに、エージェントを開始したユーザーの名前が表示されます。 エージェントのアクティビティをすばやく表示するには、[ **サービス: 条件付きアクセス]** にフィルター処理します。

[Image: 条件付きアクセスにフィルター処理された監査ログのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/conditional-access-agent-optimization-passkeys"} -->
## Microsoft Entra 条件付きアクセス最適化エージェントを使用してパスキーをデプロイする

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-passkeys
- Service: entra-id / conditional-access
- Article date: 2026-05-22
- Summary: 条件付きアクセスの最適化エージェントを使用してパスキー プログラムを安全にデプロイし、フィッシングに対する耐性のある認証方法をロールアウトする方法について説明します。

条件付きアクセスの最適化エージェントは、組織がより強力な認証方法に向けてユーザーを導くキャンペーンを計画および展開するのに役立ちます。 エージェントは、組織が構造化されたインテリジェントで自動化された方法でフィッシングに強い認証を展開するのに役立つパスキー導入キャンペーンの展開をサポートしています。

エージェントは、大規模なキャンペーンの手動作業を削減するように設計されています。 エージェントは次のことができます。

- ユーザーとデバイスの準備状況を評価する
- 推奨される展開計画を生成する
- 必要な手順をユーザーに案内する
- ユーザーの準備ができたら、条件付きアクセス ポリシーを適用する

エージェントは、前提条件が満たされると、進行状況を継続的に評価し、キャンペーンを通じてユーザーを進めます。

### 前提条件

- 少なくとも [Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview#license-requirements) ライセンスが必要です。
- 使用可能な [セキュリティ コンピューティング ユニット (SCU)](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage)が必要です。
    - 平均して、各エージェントの実行で消費される SCU は 1 つ未満です。
- [認証方法ポリシーでパスキーを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)必要があります。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)は、パスキー キャンペーンを管理する必要があります。
    - [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)ロールには、パスキー キャンペーンを管理するための十分な権限がありません。

### エージェントがパスキー キャンペーンの候補を識別する方法

エージェントは、テナントを自動的に分析して、対象となる特権管理者を特定し、最大 200 人のユーザーを対象とするキャンペーンを生成します。 対象となるロールの完全な一覧については、「 サポートされている管理者ロール」を参照してください。

パスキー キャンペーンの提案に対処したくない場合は、他のエージェントの提案と同様に無視できます。

注

初期分析には数分かかる場合があります。 **レビュー キャンペーン**が表示されない場合は、提案カードで **[実行分析**] を選択するか、エージェントの次回のスケジュールされた実行を待つことができます。

### キャンペーンの概要を表示する

パスキー キャンペーンを使用できる場合は、条件付きアクセス最適化エージェントの概要に関する提案として表示されます。

キャンペーンを確認するには:

1. **条件付きアクセスの最適化エージェント**を参照します。
2. [ **最近の提案**] で、 **管理者向けのパスキー導入キャンペーンの展開の提案を** 見つけます。
3. **キャンペーンのレビュー**を選択します。

    [Image: パスワードキャンペーンの結果が強調表示されているエージェント提案のスクリーンショット。]

最初のキャンペーンの概要では、次のようなエージェントの提案されたプランの概要が示されます。

- キャンペーンの目標
- 対象ユーザー向けに AI によって生成された準備状況の Outlook
- キャンペーンの主な指標は次のとおりです。
    - キャンペーンの推定期間
    - 対象ユーザーの数
- ユーザーの準備状況の内訳:
    - **デバイスの更新が必要なユーザー**: 少なくとも 1 つのデバイスが Microsoft Entra に登録されているが、パスキーの最小 OS 要件を満たしていないユーザー。
    - **パスキーを登録する必要があるユーザー**:互換性のあるデバイスを持つが、パスキーが登録されていないユーザー。
    - **適用の準備ができているユーザー**: 互換性のあるデバイスと登録済みのパスキーを持つユーザー。

このビューから、キャンペーンをすぐにデプロイするか、詳細なキャンペーン エクスペリエンスを開くことができます。 キャンペーンを展開する前に、詳細なキャンペーン計画を確認することをお勧めします。

[Image: パスキー キャンペーンの概要のスクリーンショット。]

### 詳細なキャンペーン計画を確認してカスタマイズする

[ **キャンペーンのレビュー** ] を選択すると、詳細なキャンペーン エクスペリエンスが開きます。このエクスペリエンスでは、展開前にユーザーの準備状況をより詳細に確認し、キャンペーンの構成をカスタマイズできます。 パスキー キャンペーンには、次の 4 つのステージがあります。

- パスキー キャンペーンの対象ユーザー
- デバイスの準備状況を確認する
- パスキーの登録が必要
- パスキーの使用を強制する

#### 対象ユーザーを確認する

詳細なキャンペーン ビューを使用すると、キャンペーンを対象とするすべてのユーザーを確認し、デプロイ前に調整を行うことができます。 キャンペーンのカスタマイズは、キャンペーンが **[未開始** ] 状態の間にのみ使用できます。 デプロイが開始されると、これらの設定を変更することはできません。

- キャンペーンの対象ユーザーを表示するには:そのカテゴリの集計ユーザー数リンクを選択します。
- キャンペーンの対象ユーザーを編集するには、[対象ユーザーの **編集]** ボタンを選択します。

[Image: ターゲット ユーザー オプションが強調表示されているパスキー キャンペーンの詳細のスクリーンショット。]

ヒント

ブレーク グラス用または緊急アクセス用の管理者アカウントは、キャンペーンから除外することをお勧めします。

[ **デバイスの準備状況の確認** ] ステージには、キャンペーンの対象ユーザーの合計数と同じ数のユーザーが含まれていない場合があります。 すべてのユーザーが、パスキーをサポートする最新のオペレーティング システムを持つ現在のデバイスを持っている場合、このステージには含まれません。 このステージのユーザーがいない場合、キャンペーンは自動的に次のステージに移動します。

#### 猶予期間を調整する

猶予期間は、ユーザーが必要なアクションを完了する必要がある期間を定義します。 猶予期間は、デバイスの更新、パスキーの登録、または情報通知と適用の間の時間に適用できます。

キャンペーンのステージの猶予期間を表示および調整するには:

1. ステージの右上隅にある矢印を選択して、詳細を展開します。
2. スライダーを調整するか、テキスト ボックスに数値を入力して、猶予期間を調整します。

    [Image: 猶予期間オプションが強調表示されているパスキー キャンペーンの詳細のスクリーンショット。]

ユーザーが必要なアクションを完了するための猶予期間を超えた場合、エージェントはキャンペーンを通じてそのユーザーの進行を続行しません。 これらのユーザーを表示するには、関連するキャンペーン カテゴリの集計ユーザー数を選択します。 [ **猶予期間超過** ] 列は、ユーザーが猶予期間を超えた日時を示します。

#### 延期オプションを構成する

猶予期間に加えて延期を有効にして、ユーザーの柔軟性を高めることができます。 延期が有効な場合:

- ユーザーは、必要なアクションまたは強制通知を一定期間延期することを選択できます。
- 延期期間が終了すると、エージェントは、必要なアクションまたは今後の適用に関するリマインダーと通知を再開します。

延期の詳細を構成するには:

1. パスキーキャンペーンの展開案として**レビューキャンペーン**を選択します。
2. [ユーザー延期を **有効にする]** チェック ボックスをオンにします。
3. 表示される新しいオプションから、日数を設定します。

    [Image: 延期の詳細が強調表示されているパスキー キャンペーンの詳細のスクリーンショット。]

#### 非アクティブなデバイスをフィルター処理する

非アクティブなデバイスをフィルター処理して、古いデバイスまたは未使用のデバイスを持つユーザーが [ **デバイスの準備状況の確認** ] ステージでスタックしないようにします。

この設定はキャンペーン レベルで適用され、管理者はアクティブなデバイスとして適格なものを定義できます。 エージェントは、指定された時間枠内に使用されていないデバイスを除外し、アクティブにサインインしているデバイスに基づいてユーザーが評価されるようにします。 既定では、エージェントは過去 1 年間に使用されたデバイスを考慮します。

[Image: [非アクティブなデバイス] オプションが強調表示されているパスキー キャンペーンの詳細のスクリーンショット。]

### キャンペーンを展開して実行する

詳細なキャンペーン計画を確認してカスタマイズしたら、キャンペーンをデプロイできます。

キャンペーンを開始するには、キャンペーンの概要または詳細なキャンペーン ビューから [キャンペーンの **デプロイ** ] を選択します。

通常、キャンペーンの展開は数分以内に完了します。 デプロイ時に、エージェントは必要なリソースを作成し、キャンペーンを通じてユーザーをガイドする準備をします。 デプロイが続行すると、進行状況の通知を受け取ります。 デプロイがタイムアウトするまれな場合は、再試行できるようにアクション ボタンが再び有効になります。

デプロイが完了すると、条件付きアクセスの最適化エージェントの概要ページでキャンペーンの状態が **[進行中** ] に変わります。

### キャンペーンの実行を監視および管理する

キャンペーンをデプロイすると、条件付きアクセスの最適化エージェントの概要ページで状態が **[進行中** ] に変わります。 その後、エージェントはキャンペーンの実行を自動的に管理し、ユーザーが必要なアクションを完了すると進行状況を更新します。 キャンペーンの概要と詳細なキャンペーン ビューには、常に最新のキャンペーンの状態、ユーザー カテゴリの内訳、ユーザー レベルの詳細が反映されるため、管理者は進行状況を監視し、必要に応じて問題を調査できます。

キャンペーンの進行中:

- キャンペーンの構成設定はロックされます (条件付きアクセス ポリシーの編集を除く)。
- 詳細なキャンペーン ビューから実行を管理できます。

使用可能なアクションは次のとおりです。

- **一時停止**: すべてのエージェント アクションを一時的に停止します。 新しいユーザーは連絡されず、進歩もしません。
- **終了**: キャンペーンを完全に停止し、その状態を **[未開始**] にリセットします。

キャンペーンを一時停止または終了しても、既に完了したアクションは元に戻りません。

### エージェントがユーザーをガイドする方法

キャンペーンがアクティブな間、条件付きアクセス最適化エージェントは 24 時間ごとに自動的に実行され、進行状況を評価し、現在の準備状況に基づいてユーザーを進められます。

実行中:

- **デバイスの更新が必要なユーザー**
    - 最小 OS 要件を満たすようにデバイスを更新するように求めるMicrosoft Teams通知を受け取る
    - 構成された猶予期間にアラーム通知を受信する
- **パスキーを設定する必要があるユーザー**
    - パスキーのセットアップ ガイダンスを使用して Teams の通知を受信する
    - 猶予期間にアラーム通知を受信する
- **適用の準備ができているユーザー**
    - 今後の適用について通知する通知を受け取る
    - 強制猶予期間が終了すると、フィッシングに対する耐性のある認証を必要とする条件付きアクセス ポリシー グループにユーザーが追加されます
    - 条件付きアクセス ポリシーはレポート専用モードで作成されます

#### 条件付きアクセス ポリシーの動作

ユーザーが適用対象になると、エージェントは条件付きアクセス ポリシーを作成して、フィッシングに対する耐性のある認証を要求します。

- ポリシーは、少なくとも 1 人のユーザーが強制通知の猶予期間を完了した後にのみ作成されます。
- ポリシーは最初はレポート専用モードで作成され、管理者は認証要件を適用する前に影響を監視できます。
- ポリシー構成は、条件付きアクセスから直接確認および管理できます。

### 既知の制限

- 条件付きアクセスの最適化エージェントでは、現在、対象ユーザーが [認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)のパスキーに対して有効になっているかどうかは確認されていません。 キャンペーンを展開する前に、この前提条件が構成されていることを確認します。
- キャンペーンの実行が開始された後は、ターゲット設定、猶予期間、延期設定などのキャンペーン設定を変更することはできません。
- 条件付きアクセス ポリシーは、少なくとも 1 人のユーザーが強制通知の猶予期間を完了した後にのみ作成されます。
- ポリシー管理オプションは、ポリシーが作成された後にのみ表示されます。
- 現在、延期は、Security Copilot 所有者ロールまたは Security Copilot 共同作成者ロールを持つユーザーに対してのみサポートされています。 管理者は、Security Copilot 管理ポータルでこれらのロールを持つユーザーを確認できます。

### サポートされている管理者ロール

- 認証管理者
- 請求管理者
- クラウド アプリケーション管理者
- 条件付きアクセス管理者
- Exchange 管理者
- グローバル管理者
- ヘルプデスク管理者
- Intune サービス管理者
- パスワード管理者
- 特権認証管理者
- 特権ロール管理者
- セキュリティ管理者
- SharePoint管理者
- Teams 管理者
- ユーザー管理者
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/conditional-access-agent-optimization-phased-rollout"} -->
## 条件付きアクセス最適化エージェントの段階的ロールアウト

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-phased-rollout
- Service: entra-id / conditional-access
- Article date: 2026-05-22
- Summary: Microsoft Entra最適化エージェントのセキュリティ Copilotの段階的ロールアウト機能について説明します。

Microsoft Entraの条件付きアクセス最適化エージェントには、組織が新しい条件付きアクセス ポリシーを安全かつ効率的に展開するのに役立つ段階的なロールアウト機能が含まれています。 Microsoft EntraのこのMicrosoft Security Copilot機能により、管理者はポリシーを段階的に導入し、影響を監視し、中断を最小限に抑えることができます。 この段階的なロールアウト機能により、エンド ユーザーに広範な中断が発生する可能性を最小限に抑え、手動による分析と計画の必要性を減らし、数週間の労力を節約するために、新しいポリシーを段階的に展開できます。 条件付きアクセスの最適化エージェントのすべての側面と同様に、管理者は、グループの選択、ロールアウトのペース、展開など、ポリシーの変更を完全に制御できます。 透明性を維持するために、ロールアウト計画の明確な理由も提供されます。

この記事では、段階的なロールアウト プロセスのしくみについて説明し、前提条件の概要を説明し、スムーズな展開を実現するために役立つ組み込みのセーフガードについて説明します。

### [前提条件]

- 少なくとも [Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview#license-requirements) ライセンスが必要です。
- 使用可能な [セキュリティ コンピューティング ユニット (SCU)](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage) が必要です。
- [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) ロールと [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールは、段階的なロールアウト設定を変更できます。
- テナントには、段階的ロールアウト計画を生成するためにエージェントの条件付きアクセス ポリシーで現在使用されている、少なくとも 5 つの定義済みグループが必要です。

### 動作方法

*すべてのユーザー*に適用されるレポートのみのポリシーは、段階的なロールアウトの対象となります。 エージェントは、作成するポリシーの段階的なロールアウト計画を提案したり、管理者はすべてのユーザーを対象とする既存のレポート専用ポリシーに段階的なロールアウトを適用したりできます。 エージェントは、サインイン データと既存のポリシーを分析して、段階的なロールアウト計画を定義します。

ロールアウト計画には 5 つの異なるフェーズがあるため、ロールアウト計画を適用するには少なくとも 5 つのグループが必要です。 使用するグループを決定するために、エージェントは、以前または現在条件付きアクセス ポリシーで使用されているグループを確認します。 エージェントはこれらのグループを調べて、他の条件付きアクセス ポリシーがそれらに与えた影響を確認し、潜在的な影響を測定します。 エージェントはグループのサイズを確認し、これらすべての要因を使用して、影響の低いグループから始まり、影響の大きいグループで終わるフェーズにグループを割り当てます。

段階的ロールアウト プロセスには、次の 3 つの手順があります。

1. エージェントが段階的ロールアウトのレポート専用ポリシーを識別する
2. 管理者がロールアウト計画を確認、編集、承認する
3. 管理者が承認済みのロールアウト計画を実行する

各フェーズに含まれるグループを確認し、段階的ロールアウトの前後に変更を加えることができます。 最初のフェーズが開始されると、最初のフェーズに含まれるグループに対して *新しい* ポリシーが作成され、オンになります。 元のレポート専用モード ポリシーはそのまま残ります。

### エージェントがレポート専用の段階的ロールアウトポリシーを識別する

エージェントは、新しいポリシーを作成するとき、またはすべてのユーザーを対象とするレポート専用ポリシーを既存に作成するときに、段階的なロールアウト計画を提案できます。 ロールアウト計画には、小規模でリスクの低いグループから始まり、より大きなリスクの高いグループに進む 5 つのフェーズが含まれます。 Microsoft Entra 管理センターで段階的なロールアウトの提案を見つけるには、いくつかの方法があります。

- エージェントによって作成されたポリシーについては、提案の一覧のエージェント**によって実行されたアクション**列で、推奨**される段階的ロールアウト**を探します。

    [Image: 段階的なロールアウトの種類が強調表示されているエージェントの提案のスクリーンショット。]
- 既存のレポート専用ポリシーの場合は、[**条件付きアクセス - ポリシー**] ページの [**段階的ロールアウト**] タブを参照します。 この専用タブでは、段階的なロールアウト プロセスの完全なライフサイクル ビューが提供されます。

    [Image: [段階的ロールアウト] メニューが強調表示されている [条件付きアクセス ポリシー] ページのスクリーンショット。]
- [条件付きアクセス ポリシー] リストの [ **段階的なロールアウトフィルターの対象となるポリシーを表示** する] ボタンを使用して、段階的なロールアウトの対象となるポリシーを見つけます。

    [Image: 段階的なロールアウトの種類が強調表示されているポリシー一覧のスクリーンショット。]

#### 管理者がロールアウト計画を確認、編集、承認する

管理者は、各フェーズに含まれるグループ、各フェーズのタイミング、プランの実行方法など、プランの詳細を確認する必要があります。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Security Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) としてサインインします。
2. **条件付きアクセスの最適化エージェント**または**条件付きアクセス - ポリシー**を参照し、段階的なロールアウトの提案があるポリシーを選択します。

    1. [ **条件付きアクセス - ポリシー] ページで** 、開いたパネルから [ **プランの生成** ] を選択します。
3. [条件付きアクセスの最適化エージェント ポリシーの詳細] ページ、またはプランが生成された後に、[**フェーズを確認する**] を選択します。

    [Image: 段階的なロールアウト ポリシーの提案のスクリーンショット。]
4. [ **グループの編集]** を選択して、フェーズに含まれるグループを編集します。

    [Image: [グループの編集] ボタンが強調表示された状態で編集できるフェーズのスクリーンショット。]
5. ポリシーの詳細パネルまたは **段階的ロールアウト** の詳細ページから、[段階的ロールアウトの開始] ボタンを選択します。 エージェントは、レポート専用モードで新しいポリシーを作成します。

ヒント

ロールアウトを一時停止したり、ロールアウトをいつでも完了に設定したりできます。

### 管理者が承認済みのロールアウト計画を実行する

段階的なロールアウトを開始すると、エージェントが進行に役立ちます。 段階的なロールアウトの提案はロールアウト プロセス全体に存在し、アクションが不要であることを示したり、次のフェーズに進むことを提案したり、ロールバックを提案したりできます。 このガイダンスは、条件付きアクセスの最適化エージェントの提案リストと条件付きアクセス - ポリシーの一覧の両方に表示されます。 どちらのポリシーも、段階的なロールアウトのデプロイが進行中であることを示しています。

デプロイ中に段階的なロールアウトを管理するためのオプションがいくつか用意されています。 各フェーズで、エージェントはポリシーに関連するアクティビティを監視して、エラーや問題がないことを確認します。 まだ開始していないフェーズのグループを調整できます。 フェーズ間の移動またはデプロイの完了は、ページの上部にあるボタンを使用して行われます。

- ロールアウト **の各フェーズを** 進める場合は、[次のフェーズに移動] を選択します。
- [ **前のフェーズにロールバック]** を選択して現在のフェーズをキャンセルし、前のフェーズに戻ります。
- [ **ロールアウトを完了としてマーク** する] を選択して、新しいポリシーをすべてのグループに適用し、デプロイを完了します。

[Image: 手動実行モードでの段階的ロールアウト計画のスクリーンショット。]

### 組み込みのセーフガード

段階的なロールアウトが開始されると、ポリシーの許可コントロールを更新することはできません。 許可コントロールに変更が加えられた場合、段階的なロールアウトは取り消されます。 任意のフェーズで 10% を超えるサインインが新しいポリシーによってブロックされた場合、ロールアウトはすぐに一時停止されます。 管理者に通知され、トラブルシューティングのガイダンスが提供されます。これには、サインインに失敗した理由を示すレポートが含まれます。 このガイダンスは、ロールバックが推奨される場合に常に表示され、ロールアウトを再開する前に管理者が問題をすばやく特定して解決するのに役立ちます。

### 段階的ロールアウトのグループ推奨事項に使用するシグナル

条件付きアクセスポリシーの安全で効果的なロールアウトを保証するために、条件付きアクセス最適化エージェントは、いくつかの重要なシグナルを分析して、デプロイの各段階に最適なグループを推奨します。 このアプローチは、セキュリティ カバレッジを最大化しながら中断を最小限に抑えるのに役立ちます。

- **グループのサイズとリーチ**: エージェントは、ユーザー ベースの合計に対する各グループのサイズを考慮します。 小規模な代表的なグループから始めて、より広範な人口に拡大する前に、より安全なテストが可能になります。
- **ポリシーのパフォーマンスの履歴**: エージェントは、グループ内のユーザーが時間の経過と共に既存のポリシーと対話した方法を分析します。
    - 高い成功率は、ユーザーが既にセキュリティ要件を満たしていることを示します。
    - 高いブロック レートは、厳密なロールアウトの前に対処する必要がある潜在的な摩擦ポイントを示している可能性があります。
    - エージェントは、ポリシーがグループのサインインに適用される頻度も確認します。
- **使用パターン**: エージェントは、各グループのアクティビティの量を評価します。 アクティビティ レベルが高いグループでは検証用のデータ ポイントが多くなりますが、アクティビティの少ないグループでは、自信を持ってパイロットを行うのに十分なシグナルが生成されない可能性があります。
- **既存のセキュリティ コントロール**: エージェントは、グループが新しいポリシーの種類を効果的に提案するために既に有効なコントロールの種類を識別し、ユーザーの混乱やロックアウトの可能性を減らします。
- **最近の管理コンテキスト**: エージェントは、ポリシーの変更または管理アクションに最近関与したグループを考慮して、提案が最新であり、静的なグループ定義だけに依存するのではなく、最新の組織の変更を反映するようにします。

### よく寄せられる質問

#### 段階的なロールアウト機能はどのように機能しますか?

各フェーズが適用されるグループを選択すると、エージェントは、最初のフェーズのグループのみを含む重複する条件付きアクセス ポリシーを作成します。 元の条件付きアクセス ポリシーはレポート専用モードで保持され、すべてのユーザーを対象とするため、引き続きデータを収集できます。 展開が次のフェーズに進むと、グループのバッチが有効な条件付きアクセス ポリシーに追加されます。 エージェントは、各ステージがこのポリシーに関連付けられているサインインに与える影響を監視します。 成功率が 90%を下回ると、段階的なロールアウトが停止し、有効なポリシーがレポート専用モードに戻されます。 その後、段階的なロールアウトを再試行する前に、ログを確認してサインインが失敗した理由を判断できます。

#### 段階的なロールアウトを有効にする必要がありますか?

段階的ロールアウト機能は、既定で有効になっています。 オフにするには、[条件付きアクセスの最適化エージェント] ページの **[設定]** タブに移動します。 [ **段階的ロールアウト]で**、トグルを **オフ**に切り替えます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/conditional-access-agent-optimization-review-suggestions"} -->
## 条件付きアクセスの最適化エージェントからの提案を確認する

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-review-suggestions
- Service: entra-id / conditional-access
- Article date: 2026-06-30
- Summary: Microsoft Entra 最適化エージェントの Security Copilot によって提供される提案を確認して適用する方法について説明します。

Microsoft Entra 条件付きアクセス最適化エージェントは、条件付きアクセス ポリシーを作成または更新するための提案を提供し、それらのポリシーに関連するアクティビティのレポートを作成します。 提案は、エージェントが見つけた内容によって異なります。 管理者は、提案を確認し、何を行うかを決定する必要があります。

この記事では、提案とレポートの背後にあるロジックの概要と、それらの提案を確認して対処する方法について説明します。

Important

条件付きアクセス最適化エージェントのエージェント ID に対する ServiceNow 統合と最小特権アクセスの提案は、現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### [前提条件]

- [少なくとも Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview#license-requirements) ライセンスが必要です。
- 使用可能な [セキュリティ コンピューティング ユニット (SCU)](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage)が必要です。
    - 平均して、各エージェントの実行で消費される SCU は 1 つ未満です。
- 適切な Microsoft Entra ロールが必要です。
    - [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) ロールと [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) ロールは *、エージェントと提案を表示できますが、アクションを実行することはできません*。
    - [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) ロールと [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールは *、エージェントを表示し、提案に対してアクションを実行*できます。
    - [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロールは、Microsoft Defender ポータルでMicrosoft Defenderアラートを*表示および操作*できます。
    - 詳細については、「[セキュリティCopilotのアクセスの割り当て](https://learn.microsoft.com/ja-jp/copilot/security/authentication#assign-security-copilot-access)」を参照してください。

#### 制限事項

- エージェントを起動すると、エージェントを停止または一時停止することはできません。 実行には数分かかる場合があります。
- ポリシーの統合では、各エージェントの実行で 40 の同様のポリシー ペアが検索されます。
- Microsoft Entra 管理センターからエージェントを実行することをお勧めします。
- スキャンは 24 時間に制限されます。
- エージェントからの提案をカスタマイズまたはオーバーライドすることはできません。
- エージェントは、1 回の実行で最大 300 人のユーザーと 150 のアプリケーションを確認できます。

### 動作方法

エージェントが実行され、次の場合があります。

- 保護されていないユーザーを特定したり、変更を推奨したりしない
- *レポート専用モードで*新しい条件付きアクセス ポリシーを作成する
- 既存のポリシーの変更を提案する
- 重複するポリシーの統合を提案する
- 既存のポリシーに関連するアクティビティの急増または低下を特定する

条件付きアクセス ポリシーは複雑になる可能性があるため、提案を識別するために使用されるロジックに関するできるだけ多くの情報を提供したいと考えています。 エージェントは、提案ごとに、詳細な推論、ポリシーへの影響の概要、およびポリシーの詳細を提供します。 ベスト プラクティスとして、提案を適用したり、レポートのみのポリシーをアクティブなポリシーに変更したりする前に、提供された情報を確認してください。

### 提案とエージェント ロジックを確認する

提案の **確認** を選択して、提案の完全な概要を確認します。これには、提案の特定に使用されるロジックや、ポリシーの潜在的な影響が含まれます。 ポリシー提案の詳細で使用できるオプションは、提案の種類によって異なります。 たとえば、新しいポリシー候補には [ **ポリシーを有効にする** ] ボタンが含まれますが、既存のポリシーを変更するための提案には、ポリシーから除外するユーザーまたはグループを追加するための [ **アカウント** の追加] ボタンが含まれます。

### ポリシーの詳細

提案の既定のビューには、ポリシーの詳細が表示されます。たとえば、上部に大まかな説明が表示され、その後にポリシーで使用される詳細が表示されます。

[Image: ポリシー提案の詳細が開いているエージェントのスクリーンショット。]

ポリシーの詳細から、いくつかのオプションを使用して提案に対してアクションを実行できます。 ページの上部で、ポリシーを編集、複製、ダウンロード、または削除できます。 [また、エージェントとのチャット](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-chat)機能を使用することもできます。

[Image: ボタンが強調表示されているポリシーの詳細のスクリーンショット。]

ポリシー提案の概要の下には、いくつかのアクションを実行できます。 オプションは、提案の種類に基づいて変更されます。 たとえば、新しいポリシーを有効にするための提案には、既存のポリシーの設定を更新する提案とは異なるオプションがあります。

- **ポリシーの変更を確認**する: 提案の概要または JSON の詳細を表示します。詳細については、「 ポリシー変更の確認 」セクションで説明します。
- **ポリシーを有効にする**: エージェントによってレポート専用モードで作成された新しいポリシーを有効にします。
- **提案をレビュー済みとしてマーク**する: [ **ポリシーを有効にする** ] ボタンの下矢印から選択して、提案を適用せずにレビューしたことを示します。
- 14 日間通知を一時停止: 「ポリシーをオンにする」ボタンの下矢印から選択して、提案を一時的に非表示にします。 提案は 14 日後に一覧に再び表示されます。
- **エージェントの完全なアクティビティを表示**する: 完全なアクティビティと決定を表示します。 詳細については、「エージェントの 完全なアクティビティの表示 」セクションで説明します。
- **ノートの追加**: ペンと紙のアイコンを選択して、他の管理者がレビューする提案に関するメモを追加します

ポリシー提案の詳細ページは詳細であり、提案に関する情報に基づいた意思決定を行うために必要なすべてのオプションを提供します。 ただし、詳細が必要な場合は、ポリシーへの影響を確認し、エージェントのアクティビティに関する詳細を確認することもできます。

### ポリシーへの影響

表示された詳細パネルで、[ **ポリシーの影響** ] を選択して、ポリシーの潜在的な影響を視覚化します。

必要に応じて、フィルターと表示を調整します。 グラフ上のポイントを選択して、ポリシーが影響を受けるデータのサンプルを表示します。 たとえば、多要素認証 (MFA) を必要とするポリシーの場合、グラフには、条件付きアクセス ポリシーが適用されなかったサインイン イベントのサンプルが表示されます。 詳細については、「 [ポリシーへの影響」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#reviewing-results)を参照してください。

### エージェントの完全なアクティビティを表示する

エージェントのアクティビティの詳細な概要と提案の計算方法を確認するには、[ **エージェントの完全なアクティビティの表示**] を選択します。 エージェントのアクティビティは、ユーザーとアプリのポリシードリフト、またはポリシーカバレッジのギャップを評価します。 エージェントは、マージまたは統合できるポリシーも検索します。

[Image: クリック可能なユーザー リスト オプションが強調表示されているエージェント アクティビティ マップのスクリーンショット。]

ポリシー候補に特定のユーザーまたはアプリケーションのポリシーへの追加が含まれている場合は、マップから直接アプリケーションまたはユーザーの一覧を表示できます。 たとえば、次の例では、[ **8 ユーザー** ] リンクを選択すると、エージェントがポリシーに含まれていないと識別された 8 人のユーザーが表示されます。

[Image: エージェント アクティビティ マップのスクリーンショット。]

**エージェント アクティビティの概要**は、マップに示されているアクティビティの自然言語の説明です。 これらの詳細は、提案の背後にあるロジックを理解するのに役立ちます。これにより、提案を適用するかどうかに関する情報に基づいた意思決定を行うことができます。

### ポリシーの変更を確認する

エージェントが既存のポリシーの変更を提案する場合は、[ **ポリシーの変更の確認** ] を選択して、推奨される変更の詳細を表示します。 このページには、ユーザー、ターゲット リソース、および提案を適用した場合に変更されるポリシーのその他の詳細が一覧表示されます。

- ポリシーの詳細は、変更されているすべての詳細の一覧と、ポリシー全体の JSON ビューの両方として提供され、変更が強調表示されます。
- ユーザーまたはアプリケーションに影響を与えるポリシーの変更については、ポリシー変更の影響を受けるユーザーとアプリケーションの JSON ファイルをダウンロードできます。

[Image: [ポリシーの変更の確認] ボタンが強調表示されているポリシーの詳細のスクリーンショット。]

### 詳細分析

詳細な分析では、レガシ認証のブロック、デバイス コード フローのブロック、デバイスまたは MFA 制御を必要とするポリシーなどのシナリオに対して、条件付きアクセス ポリシーの詳細なレビューが実行されます。 対象となるユーザー、グループ、ロールを評価して、カバレッジのギャップ、重複または冗長ポリシー、統合の機会を特定します。 また、除外を分析し、ユーザーの大部分を除外するポリシーにフラグを設定したり、不注意によるロックアウトのリスクを軽減するために、Break-glass アカウントの明示的な除外を推奨したりします。

[Image: 詳細分析機能によって提供されるポリシー提案のスクリーンショット。]

詳細な分析によって提示されるポリシーの提案が環境に大きな影響を与える可能性があるため、提案を調査する時間を確保する「スヌーズ」オプションの使用や、意思決定プロセスのコンテキストと根拠を提供する「メモ」オプションの利用を検討してください。

#### MFA のギャップ分析

詳細分析には、テナントで有効になっているすべての条件付きアクセス ポリシーをスキャンし、MFA ポリシーの対象ではないユーザーを識別する MFA ギャップ分析が含まれます。 この分析では、過去 24 時間のアクティビティだけでなく、テナント構成全体が評価されます。 エージェントがカバレッジ ギャップを見つけると、それらのユーザーがカバーされない理由が説明され、既存のポリシーへのユーザーの追加や新しいポリシーの作成など、特定の修正プログラムが推奨されます。

**既知の制限事項:**

- **MFA ギャップのみ**: デバイスのコンプライアンス、レガシ認証のブロック、およびその他のコントロールは現在評価されていません。
- **レポートのみのポリシー**: レポートのみのポリシーはカバレッジとしてカウントされません。 テナントの唯一の MFA ポリシーがレポート専用モードの場合、エージェントはそれらのユーザーに検出済みとしてフラグを設定します。
- **大規模なテナント**: エージェントが検出されたユーザーが 100 人を超える場合は、優先度付けされたサンプルと合計数が表示されます。
- **重複提案**: ユーザーは、既存の MFA ポリシーに新しいユーザーを追加するための詳細分析提案と標準提案の両方に現れる可能性があります。

### Microsoft Defender に関連する推奨事項

一部の提案は、Microsoft Defender脅威の分析情報から生成されます。 これらの提案は、提案リストの **[Defenderにリンクされたアラート**] タグによって識別されます。

条件付きアクセスの最適化エージェントでDefenderリンクされた提案を開くと、その詳細には、提案に貢献したDefenderコンテキストについて説明するDefender**分析情報**セクションが含まれます。 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロールを持つ管理者は、Microsoft Defenderの関連するアラートとインシデントに直接移動できます。 これらのアクセス許可がない場合でも、条件付きアクセスの提案を確認して操作できます。

エージェントが脅威分析情報Microsoft Defender使用する方法の詳細については、[Microsoft Defender統合](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization#microsoft-defender-integration)を参照してください。

### Microsoft Teams エージェントの提案通知

Microsoft Teamsを使用すると、新しい提案が使用可能になったときに条件付きアクセス最適化エージェントから通知を受け取ることができます。 この機能を使用すると、エージェントによって新しい提案が識別されたときに通知を受け取るユーザーを構成できます。 現在、Teams 統合では、エージェントとの一方向の通信と、Microsoft Entra 管理センターのポリシー提案への直接リンクが提供されています。

詳細については、「**条件付きアクセスの最適化エージェント設定**」の「[通知](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-settings#notifications)」セクションを参照してください。

### 提案を適用する

提案を適用するエクスペリエンスは、エージェントがレポート専用モードで新しいポリシーを作成したか、既存のポリシーの変更を提案したかによって異なります。

#### 既存のポリシーを変更する

エージェントは、既存のポリシーの変更または重複するポリシーの統合を提案できます。 ポリシーの変更の詳細と影響を確認したら、提案をポリシーに適用できます。

- **[ポリシーの詳細**] ページで、[**提案の適用**] を選択して、ポリシーに変更を適用します。

    [Image: [提案の適用] ボタンが強調表示されている [ポリシーの詳細の変更] ページのスクリーンショット。]
- [ **ポリシー変更の確認** ] ページで、ページの下部から [ **提案された変更の承認** ] を選択することもできます。

    [Image: [提案された変更の承認] ボタンが強調表示されているレビュー ポリシー ページのスクリーンショット。]

ヒント

ポリシーにユーザーを追加する提案を承認したユーザーは、ポリシーにユーザーを追加する新しいグループの所有者になります。

#### 新しいポリシーを有効にする

エージェントが新しいポリシーを提案すると、レポート専用モードでポリシーが作成されます。 ポリシーへの影響を確認したら、エージェント エクスペリエンスまたは条件付きアクセス ポリシーの一覧から直接ポリシーを有効にすることができます。

- エージェントが**レポート専用モードで**ポリシーに変更を適用するには、[*ポリシーを有効にする] を*選択します。
- 条件付きアクセスからポリシーを選択し、[ポリシーの **有効化** ] トグルを **[レポートのみ]** から **[オン]** に変更します。

    [Image: 条件付きアクセスのレポート専用モードの切り替えのスクリーンショット。]

ヒント

ベスト プラクティスとして、組織は構成ミスが原因でロックアウトされないように、ブレイクグラス アカウントをポリシーから除外する必要があります。

Warnung

準拠デバイスを必要とするレポート専用モードのポリシーでは、デバイスコンプライアンスが適用されていない場合でも、ポリシーの評価中に macOS、iOS、Android デバイスのユーザーにデバイス証明書の選択を求める場合があります。 これらのプロンプトは、デバイスが準拠するまで繰り返される場合があります。 エンド ユーザーがサインイン中にプロンプトを受信できないようにするには、デバイス コンプライアンス チェックを実行するレポート専用ポリシーからデバイス プラットフォーム Mac、iOS、Android を除外します。

### ポリシー レポートを確認する

条件付きアクセスの最適化エージェントは、既存のポリシーに関連するアクティビティの急増と低下も検出します。 これらの異常は、多くの場合、調査が必要なポリシーの構成ミスを示しています。 エージェントがアクティビティの大幅な変更を識別した場合は、提案の一覧にレポートが表示されます。 レポートは、エージェントがオンにすることを提案するアクティブポリシーとレポート専用ポリシーの両方に適用されます。 [ **エージェントによって実行されたアクション]** 列に、推奨 **されるポリシー レビュー** が値として表示されます。

ポリシー レビュー レポートを表示するには:

1. [ **提案の確認** ] を選択して、推奨されるポリシー レビューの詳細を表示します。
2. ポリシーの詳細ページで、[ **レポートの確認**] を選択します。 ポリシーに関連するアクティビティを視覚化した詳細なレポートが表示されます。

    [Image: [レポートの確認] ボタンが強調表示されているレポートのポリシーの詳細のスクリーンショット。]
3. レポートを確認し、必要に応じてポリシーを調査します。
4. ポリシーの詳細ページで、**[提案をレビュー済みとしてマークする]** または **[14 日後に再通知する]** を選択します。 必要に応じて、学習した内容と、関連するポリシーに加えた変更に関するメモを追加できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/conditional-access-agent-optimization-settings"} -->
## Microsoft Entra 条件付きアクセス 最適化 エージェントの設定

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-settings
- Service: entra-id / conditional-access
- Article date: 2026-05-22
- Summary: Microsoft Security Copilot と連携する Microsoft Entra 条件付きアクセス最適化エージェントで利用可能な設定について説明します。

条件付きアクセスの最適化エージェントは、ギャップ、重複、例外の条件付きアクセス ポリシーを分析することで、組織のセキュリティ体制を改善するのに役立ちます。 条件付きアクセスは、組織のゼロ トラスト戦略の中心的なコンポーネントになるため、組織固有のニーズを満たすようにエージェントの機能を構成できる必要があります。

この記事で説明するエージェント設定では、トリガー、通知、スコープなどの標準的なオプションについて説明します。 ただし、この設定には、カスタム手順、Intune 統合、アクセス許可などの高度なオプションも含まれています。

Important

条件付きアクセス最適化エージェントでの ServiceNow 統合とアクティビティ ベースの実行は、現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoftは、ここに記載されている情報に関して、明示または黙示を問わず、いかなる保証も行いません。

### エージェント設定を構成する方法

Microsoft Entra 管理センターの 2 つの場所から設定にアクセスできます。

- **Agents**&gt;**Conditional Access Optimization Agent**&gt;**Settings**から設定します。
- **条件付きアクセス**から&gt;**ポリシーの概要**設定の下にある &gt;] カードを選択**します**。

[Image: 条件付きアクセスの最適化エージェント設定のトリガー オプションのスクリーンショット。]

左側のメニューからカテゴリを選択して、すべての設定を参照してください。 変更を行った後、ページの下部にある **[保存** ] ボタンを選択します。

### トリガー

エージェントは、最初に構成された日時に基づいて、24 時間ごとに実行するように構成されます。 エージェントはいつでも手動で実行できます。

エージェントは、毎日スケジュールされた実行に加えて、有効になっているポリシーに変更が加えられたときに実行をトリガーできます。 アクティビティ ベースの実行では、毎日スケジュールされた実行は置き換えられません。 過剰な実行を防ぐために、アクティビティ ベースのトリガーは 6 時間に 1 回まで発生しません。

- 条件付きアクセスの最適化エージェントを有効にしていない場合、アクティビティ ベースの実行は既定で有効になり、エージェント設定でオフにすることができます。
- 条件付きアクセスの最適化エージェントがテナントで既に実行されている場合、アクティビティ ベースの実行はオプトインされ、エージェント設定で有効にすることができます。

#### スケジュールされた実行

エージェントは、最初に構成された日時に基づいて、24 時間ごとに自動的に実行されるように構成されます。 エージェントはいつでも手動で実行することもできます。

#### アクティビティ ベースの実行 (プレビュー)

エージェントは、毎日スケジュールされた実行に加えて、条件付きアクセス ポリシーの変更に基づいて実行をトリガーできます。 アクティビティベースの実行は、エージェントが次の毎日の実行を待つのではなく、環境の変化に早く対応できるように設計されています。

条件付きアクセス ポリシーに対する次の変更により、アクティビティ ベースの実行がトリガーされます。

- 既存の有効なポリシーが変更されます。
- ポリシーの状態は、 **オフ** や **レポート専用** などの他の状態から **オン**に変更されます。
- 状態が **[オン]** に設定された新しいポリシーが作成されます。

エージェントは、5 分ごとにこれらの変更を確認します。 対象となる変更が検出されると、エージェントは実行を開始します。 頻繁な変更の期間中に過剰な実行を防ぐために、エージェントはアクティビティ ベースの実行間に 4 時間のクールダウンを適用します。 例えば次が挙げられます。

| Time | イベント | エージェント アクション |
| --- | --- | --- |
| 0分 | 有効なポリシーが変更されました。 | アクションはまだありません。 |
| 5分 | エージェントが変更を検出します。 | エージェントが実行されます。 |
| Minute 6 | 別の有効なポリシーが変更されました。 | アクションはまだありません。 |
| 10分目 | エージェントが変更を検出します。 | クールダウンがアクティブです。 実行なし。 |
| 12分 | 別の有効なポリシーが変更されました。 | アクションはまだありません。 |
| 15分 | エージェントが変更を検出します。 | クールダウンがアクティブです。 実行なし。 |
| 第4時間 | クールダウンの有効期限が切れます。 | エージェントが実行されます。 |

アクティビティ ベースの実行では、毎日スケジュールされた実行は置き換えられません。 有効にすると、アクティビティ ベースの実行の数に関係なく、毎日の実行が常に実行されます。

- **新しいテナント**: アクティビティ ベースの実行は、既定で有効になっています。 エージェント設定でオフにすることができます。
- **既存のテナント**: アクティビティ ベースの実行はオプトインされます。 エージェント設定で有効にすることができます。

### 能力

**機能**カテゴリには、確認する必要がある重要な設定が含まれています。

- **Microsoft Entra 監視するオブジェクト**: ポリシーの提案を行うときにエージェントが監視する対象を指定するには、チェック ボックスを使用します。 既定では、エージェントは、過去 24 時間にわたってテナント内の新しいユーザーとアプリケーションの両方を検索します。
- **エージェントの機能**: 既定では、条件付きアクセスの最適化エージェントは、レポート専用モードであっても *、新しいポリシーを作成できません*。 エージェントがユーザーに代わってレポートのみのポリシーを作成できるように、この設定を変更できます。
    - この設定を有効にすると、管理者は、新しいレポート専用ポリシーを有効にする前に承認する必要があります。 ポリシーへの影響を確認したら、エージェント エクスペリエンスまたは条件付きアクセスから直接ポリシーを有効にすることができます。
    - この設定を無効にしても、提案、分析情報、ポリシーの詳細が表示されますが、レポート専用モードで作成する前に、ポリシーを手動で承認する必要があります。
- **段階的ロールアウト**: エージェントがレポート専用モードで新しいポリシーを作成し、そのポリシーが段階的なロールアウトの条件を満たしている場合、ポリシーは段階的にロールアウトされるため、新しいポリシーの効果を監視できます。 段階的ロールアウトは既定でオンになっています。 詳細については、「 [条件付きアクセスの最適化エージェントの段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-phased-rollout)」を参照してください。

[Image: 条件付きアクセスの最適化エージェントの機能設定のスクリーンショット。]

### Notifications

条件付きアクセスの最適化エージェントは、Microsoft Teamsを介して一連の受信者に通知を送信できます。 Microsoft Teamsの **Conditional Access エージェント** アプリを使用すると、受信者は、エージェントが新しい提案を表示したときに Teams チャットで直接通知を受け取ります。

エージェント アプリをMicrosoft Teamsに追加するには:

1. Microsoft Teamsで、左側のナビゲーション メニューから **Apps** を選択し、**Conditional Access エージェント**を検索して選択します。

    [Image: Teams の [条件付きアクセス アプリ] ボタンのスクリーンショット。]
2. [ **追加** ] ボタンを選択し、[ **開く** ] ボタンを選択してアプリを開きます。
3. アプリへのアクセスを容易にするには、左側のナビゲーション メニューでアプリ アイコンを右クリックし、[ **ピン留め**] を選択します。

条件付きアクセスの最適化エージェントの設定で通知を構成するには:

1. 条件付きアクセスの最適化エージェントの設定で、[ **ユーザーとグループの選択** ] リンクを選択します。
2. 通知を受信するユーザーまたはグループを選択し、[選択] ボタンを **選択** します。

    [Image: 通知のユーザーとグループを選択する条件付きアクセス エージェント設定のスクリーンショット。]
3. メインの **[設定]** ページの下部にある **[保存** ] ボタンを選択します。

通知を受信する受信者は最大 10 人まで選択できます。 通知を受信するグループを選択できますが、そのグループのメンバーシップは 10 人を超えることはできません。 ユーザー数が 10 人未満で、後で追加されるグループを選択した場合、グループは通知を受け取らなくなります。 同様に、通知は、個々のユーザーまたはグループの組み合わせなど、5 つのオブジェクトにのみ送信できます。 通知の受信を停止するには、受信者の一覧からユーザー オブジェクトまたは含まれているグループを削除します。

現時点では、エージェントの通信は一方向であるため、通知を受信できますが、Microsoft Teamsで応答することはできません。 提案に対してアクションを実行するには、チャットから **Review suggestion** を選択して、Microsoft Entra 管理センターで条件付きアクセス最適化エージェントを開きます。

[Image: Teams の条件付きアクセス エージェント通知メッセージのスクリーンショット。]

### ナレッジ ソース

条件付きアクセスの最適化エージェントは、2 つの異なるナレッジ ソースから取得して、組織固有のセットアップに合わせて調整された提案を行うことができます。

#### Files

条件付きアクセスの最適化エージェントには、組織に関する具体的な手順を提供するメカニズムが含まれています。 これらの手順には、条件付きアクセス ポリシーの名前付け規則、一意の手順、組織構造などの情報を含めることができるため、エージェントの提案は環境により関連性が高まります。 これらのアップロードされたファイルは、エージェントのナレッジ ベースを構成します。 詳細については、「 [条件付きアクセスの最適化エージェント」ナレッジ ベースを](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-knowledge-base)参照してください。

Important

データはエージェント内にとどまり、モデルのトレーニングには使用されません。

ナレッジ ベースにファイルを追加するには:

1. **条件付きアクセス最適化エージェント**&gt;**Settings**&gt;**Files** に移動します。
2. **[アップロード]** ボタンを選択します。
3. 開いたパネルにファイルをドラッグ アンド ドロップするか、[ファイル領域の **アップロード** ] を選択してコンピューター上のファイルに移動します。

エージェントはファイルを処理して分析し、必要な情報が含まれていることを確認します。

#### カスタム手順

オプションの [ **ユーザー設定の指示** ] フィールドを使用して、ニーズに合わせてポリシーを調整できます。 この設定を使用すると、実行の一部としてエージェントにプロンプトを表示できます。 次の手順を使用できます。

- 特定のユーザー、グループ、ロールを含めるまたは除外する
- エージェントによって検討されたり、条件付きアクセス ポリシーに追加されたりするオブジェクトを除外する
- ポリシーから特定のグループを除外する、MFA を要求する、モバイル アプリケーション管理ポリシーを要求するなど、特定のポリシーに例外を適用します。

カスタム命令には、名前またはオブジェクト ID を入力できます。 両方の値が検証されます。 グループの名前を追加すると、そのグループのオブジェクト ID が自動的に追加されます。 カスタム命令の例:

- "多要素認証を必要とするポリシーから "Break Glass" グループのユーザーを除外します。
- "オブジェクト ID dddddddd-3333-4444-5555-eeeeeeeee を持つユーザーをすべてのポリシーから除外する"

考慮すべき一般的なシナリオは、エージェントが標準の条件付きアクセス ポリシーへの追加を提案したくないゲスト ユーザーが組織に多数ある場合です。 エージェントが実行され、推奨されるポリシーの対象になっていない新しいゲスト ユーザーが表示される場合は、必要のないポリシーでこれらのゲスト ユーザーをカバーすることを提案するために SKU が使用されます。 ゲスト ユーザーがエージェントによって考慮されないようにするには:

1. `(user.userType -eq "guest")`"ゲスト" という動的グループを作成します。
2. ニーズに基づいてカスタム命令を追加します。
    - "エージェントの考慮事項から "ゲスト" グループを除外します。
    - "モバイル アプリケーション管理ポリシーから "ゲスト" グループを除外します。

カスタム手順の使用方法の詳細については、次のビデオを参照してください。

ユーザー インターフェイス要素など、ビデオ内の一部のコンテンツは、エージェントが頻繁に更新されるため、変更される可能性があります。

### Plugins

条件付きアクセスの最適化エージェントでは、 [Intune とグローバルセキュア アクセス](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization#built-in-integrations) の組み込み統合に加えて、既存のワークフローを合理化するための外部統合も提供されます。

#### ServiceNow 統合 (プレビュー)

Security Copilot に ServiceNow プラグインを使用する組織は、エージェントが生成する新しい提案ごとに、条件付きアクセス最適化エージェントに ServiceNow 変更要求を作成できるようになりました。 この機能を使用すると、IT チームとセキュリティ チームは、既存の ServiceNow ワークフロー内でエージェントの提案を追跡、確認、承認、または拒否できます。 現時点では、変更要求 (CHG) のみがサポートされています。

ServiceNow 統合を使用するには、組織で [ServiceNow プラグイン](https://learn.microsoft.com/ja-jp/copilot/security/plugin-servicenow) が構成されている必要があります。

[Image: ServiceNow 統合設定のスクリーンショット。]

条件付きアクセスの最適化エージェントの設定で ServiceNow プラグインを有効にすると、エージェントからの新しい提案ごとに ServiceNow 変更要求が作成されます。 変更要求には、ポリシーの種類、影響を受けるユーザーまたはグループ、推奨事項の根拠など、提案に関する詳細が含まれます。 統合によってフィードバック ループも提供されます。エージェントは ServiceNow 変更要求の状態を監視し、変更要求が承認されたときに変更を自動的に実装できます。

[Image: エージェント提案内の ServiceNow 統合のスクリーンショット。]

### Permissions

エージェント設定のこのセクションでは、エージェントが実行される ID と、エージェントが動作するために使用するアクセス許可について説明します。

#### エージェント ID

条件付きアクセスの最適化エージェントで [Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-professional/what-is-microsoft-entra-agent-id) がサポートされるようになりました。これにより、エージェントは特定のユーザーの ID ではなく、独自の ID で実行できます。 この機能により、セキュリティが向上し、管理が簡素化され、柔軟性が向上します。

**Manage エージェント ID** を選択して、エージェントの詳細をMicrosoft Entra エージェント IDで表示します。

[Image: アクセス許可と identities.pngのエージェント設定ビューのスクリーンショット]

- エージェントの新規インストールでは、既定で [エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities) が使用されます。
- 既存のインストールは、ユーザー コンテキストから切り替えて、エージェント ID でいつでも実行できます。
    - この変更は、レポートや分析には影響しません。
    - 既存のポリシーと推奨事項は影響を受けません。
    - 顧客は、以前のユーザー コンテキストに切り替えることはできません。
    - セキュリティ管理者ロールを持つ管理者は、この変更を行うことができます。 エージェント ページのバナー メッセージまたはエージェント設定の **[ID とアクセス許可**] セクションから、[エージェント ID の**作成**] を選択します。

条件付きアクセス最適化エージェントを有効にして使用するには、Security Copilotロールも必要です。 セキュリティ管理者は、既定でSecurity Copilotにアクセスできます。 Security Copilot アクセス権を持つ条件付きアクセス管理者を割り当てることができます。 この承認により、条件付きアクセス管理者もエージェントを使用できるようになります。 詳細については、「[Security Copilot へのアクセスを割り当てる](https://learn.microsoft.com/ja-jp/copilot/security/authentication#assign-security-copilot-access)を参照してください。

#### エージェントのアクセス許可

エージェント ID は、次のアクセス許可を使用してタスクを実行します。 これらのアクセス許可は、エージェント ID の作成時に自動的に割り当てられます。

- `AuditLog.Read.All`
- `CustomSecAttributeAssignment.Read.All`
- `DeviceManagementApps.Read.All`
- `DeviceManagementConfiguration.Read.All`
- `GroupMember.Read.All`
- `LicenseAssignment.Read.All`
- `NetworkAccess.Read.All`
- `Policy.Create.ConditionalAccessRO`
- `Policy.Read.All`
- `RoleManagement.Read.Directory`
- `User.Read.All`

### ユーザー

条件付きアクセスの最適化エージェントは、ロールベースのアクセス制御を使用してエージェントを使用します。 エージェントを使用するために必要な最小限の特権ロールは、 [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-agents"} -->
## Microsoft Entra エージェント

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-agents
- Service: entra
- Article date: 2026-01-21
- Summary: ID とアクセス管理の操作を強化する、Microsoft Entra エージェント、AI を利用した自動化ツールについて説明します。

Microsoft Entraエージェントは、組織内の多くの ID およびアクセス管理操作を自動化して、手動ワークロードの削減に役立ちます。 これらのエージェントは、[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/copilot/security/microsoft-security-copilot) とシームレスに連携して、反復的なタスクを自動化し、提案を提供し、管理者が価値の高い戦略的な作業に集中できるようにします。

Microsoft Entraエージェントは、ID 環境を分析し、ベスト プラクティスを適用し、自動化されたアクションを実行して、ID とアクセスのセキュリティ体制と運用効率を向上させます。 組織の ID データと構成を使用して、Microsoft Entra サービスと直接統合して、コンテキストに応じた実用的な分析情報を提供します。

### Microsoft Entra エージェントとは

Microsoft Entra エージェントは、ID 管理タスクとアクセス管理タスクを自動化および最適化するために組織の ID 環境で動作する AI を利用したツールです。 エージェントは、条件付きアクセスなどの特定の製品領域の概念やタスクに基づいています。 これらのエージェントは次のことができます。

- **日常的なタスクを自動化** する - 時間のかかる反復的な ID およびアクセス管理操作を処理する
- **提案** - 環境を分析し、Microsoftのベスト プラクティスとゼロ トラスト原則に基づいて改善を提案します
- **自律的に運用** する - スケジュールまたはトリガーで実行し、ID インフラストラクチャを継続的に監視および最適化する
- **シームレスに統合** - 組織の既存のMicrosoft Entraワークフロー内で作業する
- **学習と適応** - 環境とフィードバックに基づいて、時間の経過とともに提案を改善する

各エージェントの動作は少し異なりますが、コアでは、最初にエージェントの機能の境界内で現在の環境を分析します。 エージェントがギャップ、機会、または潜在的な問題を特定した場合、エージェントはユーザーに代わってアクションを実行できます。 各エージェントは、提案を思い付いた方法のコンテキスト、推論、アクティビティ履歴を提供します。

管理者は、エージェントを自動的に実行するように構成することも、エージェントを手動で実行するようにトリガーすることもできます。

各エージェントは特定の一連のタスクを実行するため、そのタスクの境界内で動作する特定の構成セットが必要です。 管理者は、エージェントを設定および管理するために、特定のMicrosoft Entraロールも必要です。

- **エージェント ID: エージェント**が有効になると、一意のエージェント ID が作成されます。 [エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities) の詳細を確認します。
- **Roles**: エージェントの有効化、表示、操作には、特定のMicrosoft Entra組み込みロールが必要です。 すべてのロールがエージェントで同じタスクを実行できるわけではありません。
- **アクセス許可**: エージェント ID には、そのタスクを実行するために必要な特定の読み取りおよび書き込みアクセス許可が付与されます。 これらのアクセス許可は変更または削除できません。
- **ロールベースのアクセス**: 管理者は、エージェントを設定、管理、および使用するために特定のロールが必要です。

### 使用可能なMicrosoft Entra エージェント

現在、Microsoft Entraでは次のエージェントを使用できます。 これらのエージェントがリリースおよび更新されるペースが速いため、各エージェントには、可用性のさまざまな段階で機能がある可能性があります。 プレビュー機能は頻繁に追加されます。

#### 条件付きアクセスの最適化エージェント

[条件付きアクセスの最適化エージェント](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization)は、条件付きアクセス ポリシーを分析し、改善を推奨することで、包括的なユーザー保護を保証します。 エージェントは、現在のポリシー構成Microsoftベスト プラクティスとゼロ トラスト原則に照らして評価します。

| 特性 | Description |
| --- | --- |
| アイデンティティ | 承認用の一意 [のエージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-professional/authorization-agent-id) は、エージェントが有効になると作成されます。エージェントはこの ID を使用して、テナントの条件付きアクセス ポリシーと構成でギャップ、重複、構成の誤りをスキャンします。 |
| ライセンス | [Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing) |
| Permissions | AuditLog.Read.AllCustomSecAttributeAssignment.Read.AllDeviceManagementApps.Read.AllDeviceManagementConfiguration.Read.AllGroupMember.Read.AllLicenseAssignment.Read.AllNetworkAccess.Read.AllPolicy.Create.ConditionalAccessROPolicy.Read.AllRoleManagement.Read.DirectoryUser.Read.All |
| Plugins | [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/fundamentals/copilot-security-entra) |
| プロダクツ | [Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/) |
| ロールベースのアクセス | エージェントを構成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)エージェントを使用するための[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) |
| トリガー | 24時間ごとに実行されるか、手動でトリガーされる |

#### ID リスク管理エージェント (プレビュー)

Microsoft Entra ID 保護の [Identity Risk Management Agent](https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-get-started) は、管理者が潜在的なリスクを調査し、潜在的な影響について学習し、組織の重要な資産を保護するための決定的なアクションを実行するのに役立ちます。

| 特性 | Description |
| --- | --- |
| アイデンティティ | 承認に [Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-professional/authorization-agent-id) を使用します |
| ライセンス | [Microsoft Entra エージェント ID](https://www.microsoft.com/security/business/identity-access/microsoft-entra-agent-id) |
| Permissions | Application.Read.AllPolicy.Read.AllGroup.ReadWrite.AllGroupMember.Read.AllUser.Read.AllPolicy.ReadWrite.ConditionalAccessCustomSecAttributeAssignment.Read.AllIdentityRiskyUser.Read.AllAuditLog.Read.All |
| Plugins | [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/fundamentals/copilot-security-entra) |
| プロダクツ | [セキュリティ コパイロット](https://learn.microsoft.com/ja-jp/copilot/security/microsoft-security-copilot)[Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) |
| ロールベースのアクセス | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| トリガー | 24時間ごとの実行、手動でのトリガー、または継続的な監視 |

### セキュリティ ストアでエージェントを検出する

[Security Store](https://learn.microsoft.com/ja-jp/security/store/what-is-security-store) はMicrosoft Entra 管理センターに埋め込まれており、Microsoftおよびパートナーが構築したエージェントとソリューションを検出、購入、デプロイするための一元的な場所を提供します。 使用可能なエージェントとソリューションを参照し、詳細と要件を表示し、Entra ポータルから直接デプロイ プロセスを開始できます。

詳細については、「 Microsoft Entraを参照してください。

### Microsoft Entra エージェントの導入ガイド

#### [前提条件]

- 使用可能な [セキュリティ コンピューティング ユニット (SCU)](https://learn.microsoft.com/ja-jp/copilot/security/manage-usage)が必要です。
    - セキュリティ コンピューティング ユニットを購入するには、Azure サブスクリプションが必要です。 [無料のAzureアカウントを作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- Microsoft Security Copilotでプライバシーとデータセキュリティを確認します。

#### セットアップ プロセス

1. [Security Copilot セットアップ ガイド](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot) を使用してSecurity Copilotを有効にします。
2. エージェントの構成に必要な最小限の特権ロールを使用して、[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
3. **[エージェント**] を参照し、構成するエージェントの**詳細の表示**を選択します。

### Microsoft エコシステム内のエージェント

この記事ではMicrosoft Entraエージェントに焦点を当てていますが、他のMicrosoftセキュリティ製品でも同様のエージェントを使用できます。 詳細については、「[Microsoft Intune](https://learn.microsoft.com/ja-jp/intune/intune-service/copilot/security-copilot-agents-intune)、[Microsoft Defender](https://learn.microsoft.com/ja-jp/defender-xdr/security-copilot-agents-defender)、および [Microsoft Purview](https://learn.microsoft.com/ja-jp/purview/copilot-in-purview-agents) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-enterprise-user-management"} -->
## Microsoft Security Copilot を使用したエンタープライズ ユーザー管理

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Entra 管理センターの Microsoft Security Copilot を使用して、自然言語クエリを使用してユーザー、テナント、グループを管理します。

Microsoft Security Copilot は、IT 管理者が自然言語クエリを使用してユーザー、テナント、グループ、ライセンスをすばやく管理できるようにすることで、Microsoft Entra のエンタープライズ ユーザー管理を効率化します。 この機能は、管理タスクを効率化し、ユーザーが ID をセキュリティで保護して最新の状態に保ち、ポータルの移動に費やす時間を短縮し、ID 関連の要求の応答時間を向上させるのに役立ちます。

この記事では、IT 管理者が四半期ごとの ID ガバナンス レビューに備える方法と、Microsoft Entra 管理センターで次の主要な ID 管理ユース ケースに Microsoft Security Copilot を使用する方法について説明します。

- テナントの構成を理解する
- ドメイン情報の管理
- ユーザーの調査と管理
- グループの整理と管理
- ライセンスの使用状況と最適化を分析する

この記事のプロンプトと例を使用して、チームまたは管理によるレビューと監査のための実用的な分析情報とレポートに結果をまとめることができます。

### [前提条件]

- Security Copilot が有効になっているテナント。 詳細については、「 [Microsoft Security Copilot の概要](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot#option-2-provision-capacity-in-azure) 」を参照してください。
- さまざまなガバナンスと最適化のユース ケースには、次のロールとライセンスが必要です。

    | 使用事例 | 役割 | ライセンス | Tenant |
    | --- | --- | --- | --- |
    | テナント情報 | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | いずれかのMicrosoft Entra IDライセンス | 任意のテナント |
    | ユーザー管理 | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | いずれかのMicrosoft Entra IDライセンス | 任意のテナント |
    | グループの管理 | [ディレクトリ ライター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-writer)、 [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)、または [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | 無料の Microsoft Entra ライセンス | グループを含むパブリック クラウド テナント |
    | ドメイン管理 | [ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator) | いずれかのMicrosoft Entra IDライセンス | 任意のテナント |
    | ライセンスの使用状況 | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | [Microsoft Entra ID ガバナンス ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) | 任意のテナント |

### Microsoft Entra で Security Copilot を起動する

1. 特定のユース ケースに基づいて、シナリオに適した管理ロールを使用して [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. Microsoft Entra 管理センターの **[Copilot** ] ボタンから Security Copilot を起動します。

    [Image: Microsoft Entra 管理センターの Security Copilot を示すスクリーンショット。]
3. 情報を取得したり、自然言語クエリを使用してアクションを実行したりするには、次のユース ケースとプロンプトを参照してください。

注

権限が不十分でアクションがブロックされる場合は、推奨されるロールが表示されます。 Security Copilot チャットで次のプロンプトを使用して、必要なロールをアクティブ化できます。 これは、必要なアクセスを提供する資格のあるロールの割り当てに依存します。

- *{必要なタスク} を実行できるように、{required role} をアクティブにします。*

### テナントの構成を理解する

Microsoft Entra の全体的な構成を理解するために、重要なテナント情報を収集して評価を開始します。 この基礎知識は、さらなる分析のためのコンテキストを確立するのに役立ちます。

#### テナント ID と基本情報

まず、表示名、テナント ID、作成日などの主要なテナントの詳細を取得して、レビューのスコープを確立します。 必要な情報を取得するには、次のプロンプトを使用します。

- *テナントの表示名は何ですか?*
- *テナント ID とは*
- *テナント内のユーザーは新しいテナントを作成できますか?*

#### テナントのライセンスと連絡先

テナントのライセンスに関する情報を収集し、技術的およびセキュリティのコンプライアンスに関する問題に関する連絡先を割り当てることができます。これは、監査やエグゼクティブ レポートに役立ちます。 関連する詳細を取得するには、次のプロンプトを使用します。

- *テナントに割り当てられているアクティブなライセンスは何ですか?*
- *テナントの技術的な連絡先は誰ですか?*
- *テナントのセキュリティ コンプライアンス連絡先は誰ですか?*

### ドメイン情報の管理

テナント レビューの一環として、ドメイン構成と DNS レコードを確認して、ドメインが適切に設定され、セキュリティで保護されていることを確認することもできます。 これは、ドメインの検証やセットアップに関連する問題を防ぐのに役立ちます。

#### ドメインの詳細と検証

ドメインの構成と DNS 検証の状態を調べて、ドメインが組織のポリシーに正しく設定され、セキュリティで保護され、準拠していることを確認します。 必要な情報を取得するには、次のプロンプトを使用します。

- *contoso.com の詳細を一覧表示します。*
- *contoso.com の DNS 検証レコードを表示します。*
- *初期ドメイン名は何ですか?*

### ユーザーの調査と管理

次に、Microsoft Entra のセットアップでユーザーランドスケープを理解することに焦点を当てます。 ユーザー アカウント、組織構造、認証方法を分析し、注意が必要な不整合を特定できます。

#### ユーザー情報と詳細

まず、ユーザーの詳細と組織の関係を調べて、従業員が社内でどのように構造化されているかを理解し、異常を特定します。 次のプロンプトを使用して、必要な情報を収集します。

- *最近削除されたユーザーを表示します。*
- *自分について教えてください。*
- *人事部にゲスト ユーザーはいますか?*
- *ブランドン・アルトワの推移的なレポートを表示します。*
- *各部署のメンバー数を指定します。*
- *アシャ・ブルネルのマネージャーは誰ですか?*
- *Blake Martin のアカウントはクラウドで管理されていますか?*
- *メールのニックネームでユーザーを表示します。*

#### ユーザー認証とアクセス許可

その後、ユーザー認証方法とアクセス許可を確認して、セキュリティコンプライアンスを確保し、追加の認証要件が必要なユーザーを特定できます。 次のプロンプトを使用して、必要な情報を取得します。

- *Abbi Atin の認証方法とは*
- *アバディ・ボッドのアクセス許可を検索します。*
- *ブランドン・アルトワに報告しているユーザーの数はいくつですか?*

#### ユーザーのフィルター処理と組織

潜在的なコンプライアンスの問題を特定するには、ライセンスの状態、部門、アカウント構成などの特定の条件に基づいてユーザーをフィルター処理します。 必要な情報を取得するには、次のプロンプトを使用します。

- *ライセンスが割り当てされていないユーザーを一覧表示します。*
- *財務部門またはマーケティング部門のユーザーを一覧表示します。*
- *{会社名} に含まれていないユーザーを表示します。*
- *アカウントが無効になっているユーザーを表示します。*
- *{特定のライセンス} を持つユーザーはいますか?*

### グループの整理と管理

組織全体のグループ管理を調べて、評価を続行します。 グループはユーザーを編成し、リソースへのアクセスを管理するために不可欠であるため、適切なガバナンスを確保するには、その構成を理解することが重要です。 また、グループ メンバーシップに関連する潜在的なセキュリティ リスクを特定するのにも役立ちます。

#### グループ メンバーシップと構成

まず、グループ メンバーシップ パターンを分析し、所有者なしのグループや、セキュリティ リスクを引き起こす可能性がある通常とは異なるメンバーシップの種類などの潜在的な問題を特定します。 必要な情報を取得するには、次のプロンプトを使用します。

- *テナント内の所有者なしのグループの合計数をカウントします。*
- *グループのユーザー メンバーシップの合計数をカウントします。*
- *グループ内のユーザー、グループ、デバイス、サービス プリンシパルに個別のカウントを指定します。*
- *グループには合計でいくつの異なるオブジェクトの種類がありますか?*
- *グループのすべてのユーザー メンバーを表示します。*
- *グループに含まれるユーザーはどれですか?*

#### グループの構成とロール

グループの構成、ロールの割り当て、動的メンバーシップルールを確認して、グループがどのように構造化および管理されているかを理解します。 必要な情報を取得するには、次のプロンプトを使用します。

- *グループに割り当てられるディレクトリ ロールは何ですか?*
- *このグループには組み込みのロールがありますか?*
- *グループのメンバーシップ ルールを表示します。*
- *動的メンバーシップルールは現在グループに対して処理されていますか?*
- *グループの詳細を教えてください。*

#### グループの組織とガバナンス

適切な組織とガバナンスを維持するために、種類ごとにグループを分類して分析し、不整合や最適化の機会を特定できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *グループの種類別に分類されたグループの数を表示します。*
- *各グループの種類の下にあるグループの数を一覧表示します。*
- *グループの種類ごとにいくつのグループが存在しますか?*

### ライセンスの使用状況と最適化を分析する

最後に、ライセンスの使用状況と最適化の機会を確認して、組織が Microsoft Entra への投資を最大限に活用していることを確認できます。 これにより、使用率の低いライセンスを特定し、コストを最適化できます。

#### ライセンスの分析と使用率

次のプロンプトを使用して、ライセンスの割り当て、使用パターン、および機能使用率を分析してコストの最適化を行い、今後の予算計画のライセンス管理を改善します。

- *Microsoft Entra P1/P2 ライセンスの数はいくつですか?*
- *P1/P2 Microsoft Entra ライセンスの数。*
- *Microsoft Entra ID P1/P2 ライセンスの数。*
- *Microsoft Entra P1/P2 ライセンスの使用方法は何ですか?*
- *P1/P2 機能使用率を表示します。*
- *Microsoft Entra P1/P2 ライセンスの使用状況の詳細を指定します。*

### ロールを無効化する

Microsoft Security Copilot でタスクを完了したら、セッション中にアクティブ化した管理者特権のロールを非アクティブ化して、セキュリティのベスト プラクティスを維持するようにしてください。 ロールを非アクティブ化するには、次のプロンプトを使用します。

- *調査または {desired task} が完了しました。アクセス権を非アクティブ化します。*
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-governance-optimization"} -->
## Microsoft Security Copilot を使用した ID ガバナンスと最適化

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Entra 管理センターで Microsoft Security Copilot を使用して、セキュリティ体制の向上、アクセス レビューの管理、エンタイトルメント管理、特権 ID 管理を行います。

Microsoft Security Copilot は、管理者が包括的なガバナンスを実装し、自然言語クエリを使用して Microsoft Entra 環境を継続的に最適化できるようにします。 この機能は、推奨事項によるセキュリティ体制の向上、レビューとエンタイトルメント管理によるアクセス ライフサイクルの管理、Just-In-Time コントロールによる特権アクセスのセキュリティ保護に役立ちます。

この記事では、IT 管理者が Microsoft Entra 管理センターで次のユース ケースに対して Microsoft Security Copilot のガバナンスと最適化のスキルを使用して、年次ガバナンスとコンプライアンスの監査に備える方法について説明します。

- アクセス レビューを使用してアクセスを検証する
- エンタイトルメント管理を使用して構造化アクセスを管理する
- 特権 ID 管理による特権アクセスのセキュリティ保護

この記事のプロンプトと例を使用して、チームまたは管理によるレビューと監査のための実用的な分析情報とレポートに結果をまとめることができます。

### [前提条件]

- Security Copilot が有効になっているテナント。 詳細については、「 [Microsoft Security Copilot の概要](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot#option-2-provision-capacity-in-azure) 」を参照してください。
- さまざまなガバナンスと最適化のユース ケースには、次のロールとライセンスが必要です。

    | 利用シーン | 役割 | ライセンス | Tenant |
    | --- | --- | --- | --- |
    | アクセス権レビュー | [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) | [Microsoft Entra ID P2](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | アクセスレビューが構成されたテナント |
    | 権利管理 | [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) | [Microsoft Entra ID P2](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | 任意のテナント |
    | Privileged Identity Management | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、 [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)、 [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [Microsoft Entra ID P2](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | PIM が構成されたテナント |

### Microsoft Entra で Security Copilot を起動する

1. 特定のユース ケースに基づいて、シナリオに適した管理ロールを使用して [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. Microsoft Entra 管理センターの **[Copilot** ] ボタンから Security Copilot を起動します。

    [Image: Microsoft Entra 管理センターの Security Copilot を示すスクリーンショット。]
3. 情報を取得したり、自然言語クエリを使用してアクションを実行したりするには、次のユース ケースとプロンプトを参照してください。

注

権限が不十分でアクションがブロックされる場合は、推奨されるロールが表示されます。 Security Copilot チャットで次のプロンプトを使用して、必要なロールをアクティブ化できます。 これは、必要なアクセスを提供する資格のあるロールの割り当てに依存します。

- *{必要なタスク} を実行できるように、{required role} をアクティブにします。*

### アクセス レビューを使用してアクセスを検証する

評価を開始するには、組織内のアクセス レビューの現在の状態を分析できます。 アクセス レビューでは、ユーザーがリソースに適切なアクセス権を持っていること、およびアクセス許可がコンプライアンスのために定期的に検証され、アクセスが不要になったときに確実に削除されるようにします。 アクセス レビュー データを抽出および分析して、大規模なアクセス レビューの調査、追跡、分析、承認パターンの理解、注意が必要なレビュー担当者の特定を行うことができます。

#### アクセスレビューの探索・管理

まず、現在のアクセス レビューを調査し、特定のレビュー インスタンスに関する情報を取得して、組織内のレビューのスコープと状態を理解します。 必要な情報を取得するには、次のプロンプトを使用します。

- *保留中のアクセス レビューの上位 10 件を表示します。*
- *Finance Microsoft 365 Groups Q2 のアクセス レビューの詳細を取得します。*
- *Sales App Access Q2 レビューのレビュー担当者は誰ですか?*

#### アクセス レビューの意思決定分析

アクセス レビューの決定を分析して、承認パターンを理解し、注意が必要なレビュー担当者を特定し、AI の推奨事項がオーバーライドされた場所を調査します。 これは、アクセス レビュー プロセスの有効性を特定し、改善の領域を特定するのに役立ちます。 必要な情報を取得するには、次のプロンプトを使用します。

- *Q2 財務レビューでアクセスを承認または拒否したユーザー*
- *Alex Chen が割り当てられたレビュー担当者であるレビューを一覧表示する*
- *どのアクセス レビューの決定が AI によって提案されたアクションよりも優先されますか?*

### エンタイトルメント管理を使用して構造化アクセスを管理する

これで、エンタイトルメント管理の構成を評価して、アクセス パッケージとポリシーが適切に構造化され、管理されていることを確認できます。 アクセス パッケージ、ポリシー、接続された組織、カタログ リソースに関する情報にすばやくアクセスして、自動化されたワークフローを通じて ID とアクセスのライフサイクルを大規模に管理します。

#### カタログとアクセス パッケージの管理

カタログやアクセス パッケージなど、エンタイトルメント管理の構造を調べて、リソースの編成と構成方法を理解できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *カタログ "XYZ" に含まれるリソースは何ですか?*
- *テナント内のカタログの数はいくつですか?*
- *カタログ "XYZ" に含まれるアクセス パッケージはどれですか?*
- *テナント内のアクセス パッケージの数はいくつですか?*
- *アクセス パッケージ "XYZ" に含まれるリソース ロールスコープは何ですか?*
- *名前に "Sales" が含まれているすべてのアクセス パッケージを検索しますか?*

#### ユーザーの割り当てと接続された組織

アクセス パッケージの割り当てと外部組織の関係を確認して、組織のリソースにアクセスできる内部ユーザーと外部パートナーの両方の適切なガバナンスを確保できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *"ユーザー" には、どのようなアクセス パッケージの割り当てがありますか?*
- *接続された組織 "XYZ" の外部ユーザーは誰ですか?*
- *接続された組織 "XYZ" のスポンサーは誰ですか?*
- *カタログ "XYZ" にはどのようなカスタム拡張機能がありますか?*

### 特権 ID 管理による特権アクセスのセキュリティ保護

最後に、特権 ID 管理 (PIM) 構成を確認して、Just-In-Time (JIT) アクセス メカニズムとリスクの高いロールが適切に管理および監視されていることを確認できます。

#### PIM のロール割り当てに関するクエリ

現在および適格な特権ロールの割り当てを調べて、PIM 構成、JIT アクセスが効果的に使用されていることを理解し、特権アクセスに関連する潜在的なリスクを特定します。 必要な情報を取得するには、次のプロンプトを使用します。

- *現在"ユーザー" に割り当てられている PIM ロールはどれですか?*
- *"ユーザー" に割り当てられている PIM 対象ロールはどれですか?*
- *どの PIM アクティブ ロールが "ユーザー" に割り当てられますか?*
- *{Specific Role} の PIM 対象となる割り当てを持っているのは誰ですか？*
- *{特定のロール}のPIMアクティブな割り当てを持っているのは誰ですか？*

### ロールを無効化する

Microsoft Security Copilot でタスクを完了したら、セッション中にアクティブ化した管理者特権のロールを非アクティブ化して、セキュリティのベスト プラクティスを維持するようにしてください。 ロールを非アクティブ化するには、次のプロンプトを使用します。

- *調査または {desired task} が完了しました。アクセス権を非アクティブ化します。*
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-id-governance-scenarios"} -->
## Microsoft Entra ID ガバナンスにおける Microsoft Security Copilot のシナリオ

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios
- Service: entra
- Article date: 2025-09-23
- Summary: ID ライフサイクルとアクセス管理シナリオで Microsoft Entra ID ガバナンスと共に Microsoft Security Copilot を使用する方法について説明します。

ID 管理者は、複雑な ID ライフサイクルを大規模に管理しながら、適切なアクセス ガバナンスを確保するための圧力が高まっています。 Microsoft Security Copilot は、自然言語クエリを使用して、アクセス レビューの迅速な分析、エンタイトルメント パッケージの管理、特権アクセスの監視、ライフサイクル ワークフローの合理化を可能にすることで、Microsoft Entra ID ガバナンスへのアプローチ方法を変革します。

この記事では、ID ライフサイクルとアクセス ガバナンスの取り組みを強化するために、Microsoft Entra ID Governance で使用できるセキュリティ コピロット シナリオについて説明します。

### Microsoft Security Copilot でサポートされる Microsoft Entra ID ガバナンス シナリオ

Security Copilot は Microsoft Entra 管理センターに統合され、Microsoft Entra ID ガバナンス機能とシームレスに連携します。 次の一覧は、Security Copilot でサポートされる Microsoft Entra ID ガバナンス シナリオの概要を示しています。

| Scenario | Role | ライセンス | Tenant |
| --- | --- | --- | --- |
| アクセス レビュー | [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) | [Microsoft Entra ID P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | アクセス レビューが構成されたもの |
| 資格管理 | [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) | [Microsoft Entra ID P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | 構成されているエンタイトルメント管理のあるもの |
| Privileged Identity Management (PIM) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [Microsoft Entra ID P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | PIM が構成されている任意のもの |
| Privileged Identity Management (PIM) の書き込みアクション | None | [Microsoft Entra ID P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements)、Microsoft Entra ID Governance SKU、Entra Suite | PIM が構成されている任意のもの |
| ライフサイクル ワークフロー | [ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator) | Microsoft Entra ID ガバナンス ライセンス | ライフサイクルワークフローが設定されている任意のもの |

#### アクセス権レビュー

管理者は、Security Copilot を使用して、Microsoft Entra ID ガバナンスのアクセス レビュー データを簡単に抽出して分析できるようになりました。 この統合により、大規模なアクセス レビューからすばやく調査、追跡、分析情報を得ることができます。これにより、情報に基づいた意思決定を行い、アクセス ガバナンス プロセスを合理化できます。

この機能は、管理者に役立ちます。

- アクセスを承認したユーザーを理解する
- 決定を行っていないレビュー担当者を特定する
- AI の推奨事項の無効化を調査する

次のユース ケースのアクセス レビューで Microsoft Security Copilot を使用する方法については、 [Microsoft Security Copilot でのガバナンスと最適化](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization) に関するプロンプトと例を参照してください。

- [アクセス レビューの調査と管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization#access-review-exploration-and-management)
- [アクセスレビューの決定分析](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization#access-review-decision-analysis)

アクセス レビューの詳細については、次を参照してください。

- [アクセス レビューとは](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)
- [アプリケーションへのユーザーのアクセスのアクセス レビューを準備する](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)

### 権利管理

Microsoft Entra ID のエンタイトルメント管理を使用すると、組織はワークフロー、アクセスの割り当て、レビュー、有効期限を自動化することで、ID とアクセスライフサイクルを大規模に管理できます。 管理者は自然言語クエリを使用してエンタイトルメント管理データを操作して、情報にすばやくアクセスできるようになりました。 これには、アクセス パッケージ、ポリシー、接続された組織、カタログ リソースが含まれます。また、カスタム スクリプトを使用して以前に使用できるキュレーションされたデータのカスタマイズも含まれます。

次のユース ケースのエンタイトルメント管理 [で Microsoft Security Copilot を使用する方法については、Microsoft Security Copilot でのガバナンスと最適化](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization) に関するプロンプトと例を参照してください。

- [カタログとアクセス パッケージの管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization#catalog-and-access-package-management)
- [ユーザーの割り当てと接続された組織](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization#user-assignments-and-connected-organizations)

エンタイトルメント管理の詳細については、「[エンタイトルメント管理とは」](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を参照してください。

### Privileged Identity Management (PIM)

Security Copilot を使用すると、Microsoft Entra Privileged Identity Management (PIM) と統合された自然言語クエリを使用して、特権アクセスをより効率的に管理および監視できます。 このアプローチでは、Just-In-Time ロールの割り当て、グループ メンバーシップ、重要なリソースへのアクセスに関する分析情報がすぐに得られます。 AI を利用した分析により、適格またはアクティブな PIM 割り当ての迅速な識別、変更の追跡、潜在的なリスクへの迅速な対応が可能になり、特権アクセス管理が合理化され、セキュリティ体制が強化されます。

次のユース ケースで PIM で Microsoft Security Copilot を使用する方法については、 [Microsoft Security Copilot でのガバナンスと最適化](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization) に関するプロンプトと例を参照してください。

- [PIM ロールの割り当てクエリ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization#pim-role-assignment-queries)

Privileged Identity Management の詳細については、「[Microsoft Entra Privileged Identity Management とは」を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)参照してください。

### Privileged Identity Management (PIM) の書き込み操作

ユーザーは、サインイン ログの表示や管理者ロールの割り当ての一覧表示など、昇格された特権を必要とするアクションを試みると、Microsoft Entra 管理センターでアクセス拒否エラーが発生することがよくあります。 正しいロールを特定し、それが最小限の特権オプションであることを確認することは、複雑で時間がかかる場合があります。 これは、その割り当てを取得するための適切な手段を見つけることと組み合わせると、圧倒的な可能性があります。

Microsoft Security Copilot は、最小限の特権アクセス フレームワークのベスト プラクティスを使用して、目的のタスクに基づいて最小限の特権ロールをインテリジェントに決定し、対象となる割り当てを確認し、PIM を使用して Just-in-Time (JIT) アクティブ化を通じてユーザーを有効にします。 これによりシームレスなエクスペリエンスが提供され、Copilot チャットを終了し、手動でロールに移動してアクティブ化し、クエリを再試行するために戻る必要があるユーザーの負担が軽減されます。 この機能により、すべての情報が 1 つの継続的な会話に保持されるため、摩擦が減り、生産性が向上し、アクセス管理がセキュリティで保護されます。

PIM 書き込みアクションの使用を開始するには、次のプロンプトを使用します。

- *{目的のタスク} を実行し、目的のアクションを実行できるようにロールをアクティブ化できるようにします。*
- *調査または {desired task} が完了しました。アクセス権を非アクティブ化します。*
- *誤ってロールをアクティブ化し、変更をロールバックしました。*

次のいずれかの記事でこれらのプロンプトを使用して調査を開始し、完了したらアクセスを非アクティブ化できます。

- [Microsoft Security Copilot を使用したエンタープライズ ユーザー管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management)
- [Microsoft Security Copilot を使用した ID ガバナンスと最適化](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization)
- [Microsoft Security Copilot を使用してセキュリティ インシデントを調査する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-incident)
- [Microsoft Entra で Microsoft Security Copilot を使用してアプリケーションのリスクを評価する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps)
- [Microsoft Security Copilot を使用して従業員のライフサイクルを管理する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows)

### ライフサイクル ワークフロー

Microsoft Entra ID Governance は、Security Copilot の機能を適用して、JML シナリオ全体でユーザーのライフサイクルを管理するようにカスタム ワークフローを構成する際の ID 管理者の時間と労力を節約します。 また、自然言語を使用してワークフローをより効率的にカスタマイズし、カスタム タスクを含むワークフロー情報を構成したり、ワークフローを実行したり、ワークフローの分析情報を取得したりするのにも役立ちます。

[Microsoft Security Copilot を使用して従業員のライフサイクルを管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows)する方法のプロンプトと例を参照して、次のユース ケースのライフサイクル ワークフローで Microsoft Security Copilot を使用する方法を確認してください。

- [新しいライフサイクル ワークフローの詳細なガイダンスを作成する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows#create-step-by-step-guidance-for-a-new-lifecycle-workflow)
- [使用可能なワークフロー構成を調べる](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows#explore-available-workflow-configurations)
- [アクティブなワークフロー リストを分析する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows#analyze-active-workflow-list)
- [ライフサイクル ワークフローの実行のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows#troubleshoot-a-lifecycle-workflow-run)
- [ライフサイクル ワークフローのバージョンを比較する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows#compare-versions-of-a-lifecycle-workflow)

ライフサイクル ワークフローの詳細については、「ライフサイクル ワークフロー[とは」](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-id-protection-scenarios"} -->
## Microsoft Entra ID Protection の Microsoft Security Copilot シナリオ

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-protection-scenarios
- Service: entra
- Article date: 2025-09-23
- Summary: ID リスク シナリオに Microsoft Entra ID Protection で Microsoft Security Copilot を使用する方法について説明します。

Microsoft Security Copilot は、ID リスクの調査と修復のための AI を活用した分析情報を提供することで、Microsoft Entra ID Protection の機能を強化します。 この記事では、Microsoft Entra ID Protection で Microsoft Security Copilot を使用して ID リスク管理を合理化し、組織のセキュリティ体制を改善する方法について説明します。 この機能を使用するには、Microsoft Security Copilot が有効になっているテナントが必要です。

### Microsoft Security Copilot でサポートされる Microsoft Entra ID Protection のシナリオ

Security Copilot は Microsoft Entra 管理センターに統合され、Microsoft Entra ID Protection 機能とシームレスに連携します。 次の一覧は、Security Copilot でサポートされるシナリオの概要を示しています。

| Scenario | 役割 | ライセンス | Tenant |
| --- | --- | --- | --- |
| 危険なユーザー | [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) | [Microsoft Entra ID P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | [任意] |
| アプリケーション リスク | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) | ワークロード ID Premium または [Microsoft Entra ID P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | リスクの高いサービス プリンシパルプロンプトがある場合 |

#### 危険なユーザー

Microsoft Entra ID Protection は、Security Copilot の機能を適用して [、ユーザーのリスク レベルを要約](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization)し、目の前のインシデントに関連する分析情報を提供し、迅速な軽減のための推奨事項を提供します。 ID リスク調査は、組織を守るために不可欠なステップです。 Security Copilot は、IT 管理者とセキュリティ オペレーション センター (SOC) アナリストに、ID リスクと ID ベースのインシデントを調査および修復するための適切なコンテキストを提供することで、解決までの時間を短縮するのに役立ちます。 危険なユーザー要約は、管理者とレスポンダーの調査を支援するため、コンテキストに沿って最も重要な情報にすばやくアクセスできるようにします。

次のユース ケースについては、Copilot ウィンドウに独自のプロンプトを追加できます。

- [リスクに基づいてユーザーを一覧表示または識別する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization#list-or-identify-users-based-on-risk)
- [User-Specific リスク情報](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization#user-specific-risk-information)
- [ユーザー リスク履歴](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization#user-risk-history)

[Image: ID 保護の危険なユーザーに関する要約の詳細を示すスクリーンショット。]

#### アプリケーション リスク

ID 管理者とセキュリティ アナリストは、Microsoft Security Copilot を使用して、ワークロード ID からアプリケーションのリスク レベルをすばやく評価できます。 自然言語クエリを使用すると、付与されたアクセス許可、テナント内の未使用のアプリ、アプリケーションのリスク レベルを簡単に検出できます。 これにより、管理者はリスクを軽減し、組織のアプリケーションのセキュリティを確保するための適切なアクションを実行できます。

Microsoft [Entra の Microsoft Security Copilot を使用したアプリケーション リスクの評価](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps) に関するプロンプトと例を参照して、Microsoft Security Copilot を使用して次のユース ケースのアプリケーション リスクを評価する方法について説明します。

- [Microsoft Entra のリスクの高いサービス プリンシパルを調べる](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps#explore-microsoft-entra-risky-service-principals)
- [Microsoft Entra サービス プリンシパルを調べる](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps#explore-microsoft-entra-service-principals)
- [Microsoft Entra アプリケーションを調べる](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps#explore-microsoft-entra-applications)
- [Microsoft Entra サービス プリンシパルに付与されたアクセス許可を表示する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps#explore-microsoft-entra-risky-service-principals)
- [使用されていない Microsoft Entra アプリケーションを調べる](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps#explore-unused-microsoft-entra-applications)
- [テナントの外部で Microsoft Entra アプリケーションを探索する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps#explore-microsoft-entra-applications-outside-my-tenant)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-id-scenarios"} -->
## Microsoft Entra ID の Microsoft Security Copilot シナリオ

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios
- Service: entra
- Article date: 2025-09-23
- Summary: ID とセキュリティのシナリオで Microsoft Entra ID で Microsoft Security Copilot を使用する方法について説明します。

Microsoft Security Copilot は、Microsoft Entra ID 環境の管理とセキュリティ保護に役立つ強力なツールです。 この記事では、Microsoft Entra ID コア機能と共に Microsoft Security Copilot を使用して、ID 保護の取り組みを強化する方法について説明します。 この機能を使用するには、Microsoft Security Copilot が有効になっているテナントが必要です。

### Microsoft Security Copilot でサポートされる Microsoft Entra ID シナリオ

Security Copilot は Microsoft Entra 管理センターに統合され、Microsoft Entra ID 機能とシームレスに連携します。 次の表は、Security Copilot でサポートされるシナリオの概要を示しています。

| Scenario | 役割 | ライセンス | Tenant |
| --- | --- | --- | --- |
| Tenants | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | 任意のMicrosoft Entra ID ライセンス | [任意] |
| ユーザー | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | 任意のMicrosoft Entra ID ライセンス | [任意] |
| グループ | [ディレクトリ ライター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-writer)[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | 無料の Microsoft Entra ID ライセンス | 複数のグループを持つパブリック クラウド テナント |
| ドメイン | [ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator) | 任意のMicrosoft Entra ID ライセンス | [任意] |
| ライセンス | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | [Microsoft Entra ID ガバナンス ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) | [任意] |
| サインイン ログ | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | [Microsoft Entra ID P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | サインイン データを含むパブリック クラウド テナント |
| 監査ログ | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) | 無料の Microsoft Entra ID ライセンス | 監査アクティビティを含むパブリック クラウド テナント |
| プロビジョニング ログ | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-owner) | 7 日間無料、その後 [Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | [Microsoft Entra プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning)を使用するすべてのテナント |
| 推奨 事項 | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | 無料の Microsoft Entra ID ライセンスまたは [Microsoft Entra ID P1/P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | Any (Microsoft Entra ワークロード ID でも使用可能) |
| ヘルス監視アラート | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)[セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | [Microsoft Entra ID P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | パブリック クラウドのテナント |
| サービス水準合意 | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) | 任意のMicrosoft Entra ID ライセンス | [任意] |
| ロールと管理者 | [ディレクトリ リーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-readers)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | 任意のMicrosoft Entra ID ライセンス | [任意] |
| デバイス | 任意のユーザー | 無料の Microsoft Entra ID ライセンス | [任意] |
| 条件付きアクセス | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [Microsoft Entra ID P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | 条件付きアクセス ポリシーが設定されたものすべて |
| 認証 | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) (テナント レベル)[特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) (ユーザー レベル) | 任意のMicrosoft Entra ID ライセンス | [任意] |

### エンタープライズ ユーザー管理シナリオ

Microsoft Security Copilot を使用すると、管理者は自然言語を使用して Microsoft Entra テナント、ユーザー、グループ、ドメイン、ライセンスを管理および調査できるようになりました。

#### テナント

管理者は、Security Copilot を使用して、テナント ID、表示名、テナントに割り当てられているアクティブなライセンスなど、テナントに関する質問を行うことができます。 また、テナントの技術的およびセキュリティコンプライアンスの連絡先、およびユーザーが新しいテナントを作成できるかどうかに関する分析情報も提供します。

この機能には、Microsoft Entra ID のグローバル [閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) ロールの最小値が必要であり、任意のテナントと Microsoft Entra ID ライセンスで使用できます。

テナント情報シナリオに Microsoft Security Copilot を使用する方法については、 [Microsoft Security Copilot を使用したエンタープライズ ユーザー管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#understand-tenant-configuration) のプロンプトと例を参照してください。

#### ユーザー

IT 管理者は、Security Copilot を使用して、ユーザーの詳細をすばやく表示し、ロールを管理し、アクセスの問題のトラブルシューティングを行うことができます。 これにより、ユーザー ID をセキュリティで保護して最新の状態に保ち、ポータルの移動に費やす時間を短縮し、ユーザー関連の要求の応答時間を短縮できます。

次のユース ケースの [ユーザー管理で Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management) を使用する方法については、Microsoft Security Copilot を使用したエンタープライズ ユーザー管理のプロンプトと例を参照してください。

- [ユーザー情報と詳細](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#user-information-and-details)
- [ユーザー認証とアクセス許可](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#user-authentication-and-permissions)
- [ユーザーのフィルター処理と組織](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#user-filtering-and-organization)

#### Groups

IT 管理者は、Security Copilot を使用して、グループ構成をすばやく表示し、メンバーシップを管理し、所有者なしのグループなどのグループ検疫の問題を特定できます。 関連するグループ情報をコンテキストで提供することで、Copilot はポータルの移動に費やされる時間を最小限に抑え、グループ関連のタスクの応答時間を向上させることができます。

次のユーザーは、この機能を使用できます。

次のユース ケースのグループ [管理で Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management) を使用する方法については、Microsoft Security Copilot を使用したエンタープライズ ユーザー管理のプロンプトと例を参照してください。

- [グループ メンバーシップと構成](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#group-membership-and-composition)
- [グループの構成とロール](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#group-configuration-and-roles)
- [グループの組織とガバナンス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#group-organization-and-governance)

#### ドメイン

Security Copilot は、IT 管理者が Microsoft Entra 管理センターでのドメイン管理を簡素化するのに役立ちます。 この機能を使用すると、管理者は自然言語クエリを使用して、ドメイン情報にすばやくアクセスし、DNS レコードを確認し、ドメイン設定を管理できます。

次のユース ケースのドメイン [管理で Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management) を使用する方法については、Microsoft Security Copilot を使用したエンタープライズ ユーザー管理のプロンプトと例を参照してください。

- [ドメインの詳細と検証](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#domain-details-and-verification)

#### ライセンス

Microsoft Entra テナント全体でのライセンス購入と使用状況の管理は困難な場合があります。 Security Copilot を使用すると、ライセンスの使用状況に関する質問をして、組織がライセンス使用率を最適化し、Microsoft Entra の投資から最大限の価値を得ることができます。

[Microsoft Security Copilot を使用したエンタープライズ ユーザー管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management)のプロンプトと例を参照して、次のユース ケースのライセンス使用で Microsoft Security Copilot を使用する方法を確認してください。

- [ライセンスの分析と使用率](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management#license-analysis-and-utilization)

詳細については、以下を参照してください。

- Microsoft Entra ライセンス

### 監視と健康のシナリオ

Microsoft Entra は、Microsoft Security Copilot の機能を使用して、管理者が Microsoft Entra ID 環境の正常性を監視および維持するのに役立ちます。 自然言語クエリを使用すると、管理者はサインイン ログ、監査ログ、推奨事項、正常性監視アラート、SLA パフォーマンス データにすばやくアクセスして分析できます。 これにより、潜在的な問題を特定し、異常を調査し、ID インフラストラクチャのセキュリティと信頼性を確保するための予防的な対策を講じます。

#### サインイン ログ

Security Copilot を使用すると、IT 管理者は Microsoft Entra でのサインイン アクティビティの確認とトラブルシューティングのプロセスを効率化できます。 IT 管理者とヘルプデスク チームは、複雑なログ データを手動で並べ替える代わりに、サインイン ログをすばやく分析し、問題を特定し、明確で実用的な回答を受け取ることができます。 また、Security Copilot は、トラブルシューティング プロセスをサポートし、次の手順をガイドするために役立つフォローアップの質問も提案します。

次のユース ケースのサインイン ログで Microsoft Security Copilot を使用する方法については、「 [Microsoft Security Copilot を使用した監視と操作について」](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations) のプロンプトと例を参照してください。

- [アプリケーションと認証の分析](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#application-and-authentication-analysis)
- [デバイスと場所の分析](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#device-and-location-analysis)
- [ユーザー アクティビティとセキュリティの監視](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#user-activity-and-security-monitoring)

#### 監査ログ

Security Copilot を使用すると、IT 管理者は Microsoft Entra の監査ログの調査とトラブルシューティングのプロセスを効率化できます。 IT 管理者とヘルプデスク チームは、広範なログ データを手動で検索する代わりに、監査アクティビティをすばやく分析し、問題を特定し、明確で実用的な回答を受け取ることができます。 また、Security Copilot は、調査をサポートし、次の手順をガイドするために役立つフォローアップの質問も提案します。

次のユース ケースの監査ログで Microsoft Security Copilot を使用する方法については、「 [Microsoft Security Copilot を使用した監視と操作について」](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations) のプロンプトと例を参照してください。

- [グループ管理活動](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#group-management-activities)
- [セキュリティと認証のアクティビティ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#security-and-authentication-activities)

#### プロビジョニング ログ

Security Copilot を使用すると、IT 管理者は Microsoft Entra 内のプロビジョニング アクティビティに関する貴重な分析情報を提供できます。 プロビジョニング ログを分析することで、管理者は問題をすばやく特定し、変更を追跡し、ユーザー アカウントが意図したとおりに作成、更新、削除されていることを確認できます。 これらの分析情報は、ID インフラストラクチャの整合性を維持し、ユーザー管理プロセスを合理化するのに役立ちます。

次のユース ケースのプロビジョニング ログで Microsoft Security Copilot を使用する方法については、「 [Microsoft Security Copilot を使用した監視と操作について」](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations) のプロンプトと例を参照してください。

- [ユーザー プロビジョニングの監視](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#user-provisioning-monitoring)
- [プロビジョニングエラーの分析](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#provisioning-failure-analysis)
- [プロビジョニングの成功の追跡](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#provisioning-success-tracking)
- [ジョブのプロビジョニング状態の監視](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#provisioning-job-status-monitoring)

#### 推奨事項

推奨事項により、Security Copilot は、実用的な分析情報とガイダンスを提供することで、テナントを安全で正常な状態に進化させる方法をすばやく調査するのに役立ちます。 これらの推奨事項では、最小特権管理者ロールの使用、Self-Service パスワード リセットの構成、条件付きアクセス ポリシーによるテナントの保護など、Microsoft Entra の機能、ベスト プラクティス、設定について説明します。 いくつかの推奨事項は、テナントのセキュリティの監視と向上に役立つ ID セキュリティ スコアに組み込まれます。 Microsoft Security Copilot の機能を使用して、自然言語を使用してこれらの推奨事項を操作できるようになりました。これにより、セキュリティ チームは、セキュリティで保護された正常な状態にテナントを進化させる方法をすばやく調査できます。

[Microsoft Security Copilot を使用したガバナンスと最適化](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-governance-optimization)のプロンプトと例を参照して、次のユース ケースの推奨事項と共に Microsoft Security Copilot を使用する方法を確認してください。

- [一般的な推奨事項とセキュリティ スコア](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#general-recommendations-and-secure-score)
- [カテゴリ別の対象となる推奨事項](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#targeted-recommendations-by-category)
- [アプリケーション資格情報の管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#application-credential-management)

詳細については、次を参照してください。

- [Microsoft Entra Recommendations とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)
- [Microsoft Entra Recommendations の使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-recommendations)

#### 健康監視アラート

管理者は、Security Copilot を使用して、外部正常性監視の正常性監視アラートを調査して、各テナントのシナリオ固有のメトリックを分析し、異常を検出し、アラートを生成できるようになりました。 メトリックには、サインイン成功率、失敗率、多要素認証 (MFA) の数が含まれます。

[Microsoft Security Copilot を使用した監視と操作の理解](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations)に関するプロンプトと例を参照して、次のユース ケースで Microsoft Security Copilot と正常性の監視を使用する方法を確認してください。

- [健康アラートの監視](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#health-alert-monitoring)
- [シナリオ固有のヘルスモニタリング](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#scenario-specific-health-monitoring)

詳細については、以下を参照してください。

- [Microsoft Entra Health の監視とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)
- [Microsoft Entra Health 監視アラートを調査する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-investigate-health-scenario-alerts?tabs=admin-center)

#### サービス レベル アグリーメント

Microsoft Security Copilot を使用すると、IT 管理者はサービス レベル アグリーメント (SLA) レポートに簡単にアクセスして分析し、Microsoft Entra テナントで認証を利用できるようにします。 Security Copilot では、Microsoft Graph API を使用して、コア認証の可用性に関する毎月のルックバック分析情報を提供します。管理者は、SLA の達成が 99.99%を下回っている可能性がある期間をすばやく特定するのに役立ちます。 これにより、サービスの停止と共に SLA データを事前に確認でき、 [Microsoft Entra SLA](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-sla-performance) に従ってサービス クレジットの適格性を判断するのに役立ちます。 Security Copilot はプロセスを合理化し、管理者は自然言語クエリを使用して SLA のパフォーマンスを調査し、組織の認証の信頼性を確保できます。

次のユース ケースの SLA 情報で Microsoft Security Copilot を使用する方法については、「 [Microsoft Security Copilot を使用した監視と操作について」](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations) のプロンプトと例を参照してください。

- [SLA パフォーマンスの監視](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations#sla-performance-monitoring)

詳細については、以下を参照してください。

- [Microsoft Entra ID の SLA パフォーマンス](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-sla-performance)

### ロールと管理者

Microsoft Entra ロールベースのアクセス制御 (RBAC) は、ユーザー、グループ、またはアプリケーションにロールを割り当てることで、Microsoft Entra リソースにアクセスできるユーザーを管理するのに役立ちます。 組み込みのロールを使用することも、組織のニーズに合わせて特定のアクセス許可を持つカスタム ロールを作成することもできます。 Microsoft Security Copilot を使用して、ディレクトリ内のロールを調査できるようになりました。 たとえば、ユーザーまたはグループが持つロール、特定のロールを持つユーザー、または特定のロールに関する詳細を尋ねることができます。 これにより、管理者とアナリストは、環境全体のロールの割り当てを理解して管理しやすくなります。

次のユース ケースのロール管理で Microsoft Security Copilot を使用する方法については、 [Microsoft Security Copilot でのセキュリティとアクセス制御](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control) のプロンプトと例を参照してください。

- [ロールの割り当てクエリ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#role-assignment-queries)
- [役割情報と識別](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#role-information-and-identification)

詳細については、以下を参照してください。

- [Microsoft Entra ID でのロールベースのアクセス制御の概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)
- [Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)

### デバイス

Microsoft Entra は、Security Copilot の機能を使用して、管理者が自然言語クエリを使用して Microsoft Entra ID デバイスを調査するのに役立ちます。 この機能を使用すると、管理者はデバイス ID、コンプライアンスの状態、アクティビティ、デバイスが Entra ID に登録されているか、参加しているか、ハイブリッドに参加しているかなどのデバイス情報にすばやくアクセスできます。

次のユース ケースのデバイス [管理で Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-enterprise-user-management) を使用する方法については、Microsoft Security Copilot を使用したエンタープライズ ユーザー管理のプロンプトと例を参照してください。

- [デバイスの識別と状態](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#device-identification-and-status)
- [デバイス結合の種類と構成](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#device-join-types-and-configuration)
- [デバイス アクティビティとオペレーティング システム](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#device-activity-and-operating-systems)

### 条件付きアクセス

Microsoft Entra 条件付きアクセスは、管理者が自分の条件付きアクセス ポリシーを簡単に理解して評価できるように、Microsoft Security Copilot の機能を適用します。 Security Copilot では、条件付きアクセス API と生成 AI の機能を組み合わせることで、アナリストは、ユーザーに適用されるポリシーや特定のコントロールを使用するポリシーを特定するなど、自然言語の質問を行い、明確な分析情報を数秒で受け取ることができます。

次のユース ケースに対して条件付き [アクセスで Microsoft Security Copilot を使用する方法については、Microsoft Security Copilot でのセキュリティとアクセス制御](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control) のプロンプトと例を参照してください。

- [ポリシーの識別と状態](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#policy-identification-and-status)
- [ポリシーの構成と管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#policy-configuration-and-management)

### Authentication

Microsoft Security Copilot を使用すると、管理者は Microsoft Entra テナント全体の認証方法をすばやく評価および管理できます。 自然言語クエリを使用すると、有効になっている認証方法を簡単に検出し、ユーザー登録の状態を把握し、組織の認証戦略の潜在的なギャップを特定できます。 この機能により、セキュリティ管理が合理化され、ユーザーとリソースを保護するための強力な認証プラクティスが確実に実施されます。

次のユース ケースの認証方法で Microsoft Security Copilot を使用する方法については、 [Microsoft Security Copilot でのセキュリティとアクセス制御](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control) のプロンプトと例を参照してください。

- [認証方法の構成](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#authentication-method-configuration)
- [ユーザー認証の状態](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control#user-authentication-status)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-internet-access-private-access-scenarios"} -->
## Microsoft Entra インターネットおよびプライベート アクセスでの Microsoft セキュリティ Copilot のシナリオ

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-internet-access-private-access-scenarios
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Entra インターネット アクセスとプライベート アクセスで Microsoft Security Copilot を使用する方法について説明します

[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/security-copilot/microsoft-security-copilot) は、Global Secure Access ネットワーク トラフィック分析スキルを通じて Microsoft Entra データから分析情報を取得し、管理者が自然言語クエリを使用してネットワーク トラフィックの使用状況と動作を調査および監視できるようにします。 ネットワーク トラフィック分析スキルを使用すると、ネットワーク管理者とセキュリティ チームは、複雑なクエリを記述することなく、ユーザー、デバイス、ブランチのネットワーク使用状況を分析し、ネットワークの問題を特定し、脅威やポリシー違反をリアルタイムで検出できます。

### Microsoft Security Copilot でサポートされている Microsoft Entra Internet Access と Private Access のシナリオ

Security Copilot は Microsoft Entra 管理センターに統合され、Microsoft Entra ID Protection 機能とシームレスに連携します。 次の表は、Security Copilot でサポートされるシナリオの概要を示しています。

| Scenario | 役割 | ライセンス | Tenant |
| --- | --- | --- | --- |
| グローバル セキュリティで保護されたアクセス | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)[Global Secure Access 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-secure-access-administrator)グローバル セキュリティで保護されたアクセス ログ リーダーの [を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-secure-access-log-reader) する | [Microsoft Entra ID P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements)Entra プライベート アクセス ライセンス (プライベート アクセス トラフィック用)Entra Internet Access License (Microsoft サービス外の一般的なインターネット トラフィックの場合) | グローバル セキュリティで保護されたアクセスの構成 |

Security Copilot を使用すると、次のユース ケースでその機能をグローバル セキュア アクセスに適用できます。

- データ使用量と帯域幅の使用状況を監視する
- ブロックされたトラフィックとセキュリティの脅威を調査する
- ユーザー アプリケーションのアクセス パターンを分析する
- テナント間アクセスと外部接続を監視する

### グローバルなセキュリティで保護されたアクセス

たとえば、セキュリティ アナリストまたはネットワーク管理者は、Security Copilot を使用して、自然言語クエリを使用してネットワーク トラフィックの使用状況と動作を調査および監視できます。 ユーザー、デバイス、ブランチネットワークの使用状況を分析し、ネットワークの問題を特定し、脅威やポリシー違反をリアルタイムで検出できます。 その結果、調査プロセスが合理化され、より効果的になります。

注

権限が不十分でアクションがブロックされる場合は、推奨されるロールが表示されます。 Security Copilot チャットで次のプロンプトを使用して、必要なロールをアクティブ化できます。 これは、必要なアクセスを提供する資格のあるロールの割り当てに依存します。

- *{必要なタスク} を実行できるように、{required role} をアクティブにします。*

#### データ使用量と帯域幅の使用状況を監視する

全体的なネットワーク トラフィック パターンを分析し、データ消費量が多いユーザーを特定することで、調査を開始できます。 帯域幅の使用状況とトラフィックの分散を理解することは、容量の計画、潜在的なセキュリティの問題の特定、および侵害されたアカウントまたはポリシー違反を示す可能性のある異常な使用パターンの検出に不可欠です。 次のプロンプト例を使用して、必要な情報を取得します。

- *過去 1 日のデータ消費量が最も多い上位 5 人のユーザーを表示します。*
- *ネットワーク トラフィック ログに基づいて、過去 1 週間にアクセスされた上位 10 件のアプリケーション名を一覧表示します。*

#### ブロックされたトラフィックとセキュリティの脅威を調査する

次に、ブロックされたトラフィックとセキュリティの脅威を調査して潜在的なセキュリティ インシデントを特定し、組織のセキュリティ ポリシーが効果的に適用されるようにする必要があります。 ブロックされたトラフィックを分析すると、ネットワークのセキュリティを侵害する可能性のある悪意のあるアクティビティ、構成の誤り、またはポリシー違反を検出するのに役立ちます。 次のプロンプト例を使用して、必要な情報を取得します。

- *過去 24 時間のユーザー david.analyst@woodgrovebank.com のすべてのブロックされたトラフィックを表示します。*
- *ネットワーク トラフィック ログに基づいて、過去 24 時間以内にアクセスされたリスクの高いスコアを持つすべてのアプリケーションを一覧表示します。*

#### ユーザー アプリケーションのアクセス パターンを分析する

特定のユーザー アクセス パターンを分析して、アプリケーションの使用状況を理解し、異常な動作を特定し、企業のアクセス ポリシーに確実に準拠させることができます。 この分析は、潜在的な内部関係者の脅威、侵害されたアカウント、またはセキュリティ リスクを引き起こす可能性のある未承認のアプリケーションの使用を特定するのに役立ちます。 次のプロンプト例を使用して、必要な情報を取得します。

- *ネットワーク トラフィック ログに基づいて、ユーザー sarah.manager@woodgrovebank.com が過去 24 時間以内にアクセスしたすべてのアプリケーション名を一覧表示します。*

#### テナント間アクセスと外部接続を監視する

最後に、テナント間のトラフィックを監視して、承認されていない外部接続を特定し、未承認のデータ アクセスと移動を防ぐ必要があります。 次のプロンプト例を使用して、必要な情報を取得します。

- *ネットワーク トラフィック ログに基づいて、過去 7 日間のテナント aaaabbbb-0000-cccc-1111-dddd2222eeee へのテナント間トラフィックをすべて表示します。*
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-investigate-incident"} -->
## Microsoft Security Copilot の ID リスクを調査する

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-incident
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Security Copilot と Microsoft Entra のスキルを使用して、ID ベースのセキュリティ インシデントをすばやく調査します。

[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/security-copilot/microsoft-security-copilot) は、Entra のリスクの高いユーザーの取得や監査ログの取得など、さまざまなスキルを通じて Microsoft Entra データから分析情報を取得します。 IT 管理者とセキュリティ オペレーション センター (SOC) アナリストは、これらのスキルや他のユーザーを使用して、自然言語プロンプトを使用して ID ベースのインシデントを調査および修復するのに役立つ適切なコンテキストを得ることができます。

この記事では、SOC アナリストまたは IT 管理者が Microsoft Entra スキルを使用して潜在的なセキュリティ インシデントを調査する方法について説明します。

### [前提条件]

- Security Copilot が有効になっているテナント。 詳細については、「 [Microsoft Security Copilot の概要](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot#option-2-provision-capacity-in-azure) 」を参照してください。

### シナリオと調査

Woodgrove Bank のセキュリティ オペレーション センター (SOC) アナリストである Natasha は、潜在的な ID ベースのセキュリティ インシデントに関するアラートを受け取ります。 このアラートは、危険なユーザーとしてフラグが設定されているユーザー アカウントからの疑わしいアクティビティを示します。 調査を開始し、 [Microsoft Security Copilot](https://securitycopilot.microsoft.com/) または [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。 ユーザー、グループ、危険なユーザー、サインイン ログ、監査ログ、診断ログの詳細を表示するには、少なくとも [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインします。 特定のアクションを実行するアクセス許可がないためにブロックされている場合、Microsoft Security Copilot を使用してこのロールをアクティブ化できます。

- *{必要なタスク} を実行できるように、{required role} をアクティブにします。*

#### ユーザーの詳細を取得する

Natasha は、フラグ付きユーザーの詳細 ( karita@woodgrovebank.com) を調べることから始めます。 役職、部署、マネージャー、連絡先情報など、ユーザーのプロファイル情報を確認します。 また、ユーザーがアクセスできるアプリケーションとサービスを理解するために、ユーザーの割り当てられたロール、アプリケーション、ライセンスも確認します。

次のプロンプトを使用して、必要な情報を取得します。

- "karita@woodgrovebank.com についてのユーザーの詳細をすべて表示し、ユーザー オブジェクト ID を抽出します。"
- *このユーザーのアカウントは有効になっていますか?*
- * karita@woodgrovebank.comのパスワードが最後に変更またはリセットされたのはいつですか?*
- "Microsoft Entra に登録されている karita@woodgrovebank.com のデバイスはありますか?"
- * karita@woodgrovebank.comに登録されている認証方法 (ある場合)*

#### 危険なユーザーの詳細を取得する

 karita@woodgrovebank.comが危険なユーザーとしてフラグ付けされた理由を理解するために、Natasha は危険なユーザーの詳細の確認を開始します。 ユーザーのリスク レベル (低、中、高、非表示)、リスクの詳細 (未知の場所からのサインインなど)、およびリスク履歴 (時間の経過に伴うリスク レベルの変化) を確認します。 また、リスク検出と最近の危険なサインインも確認し、不審なサインイン アクティビティやあり得ない旅行アクティビティを探します。

次のプロンプトを使用して、必要な情報を取得します。

- * karita@woodgrovebank.comのリスク レベル、状態、およびリスクの詳細は何ですか?*
- * karita@woodgrovebank.comのリスク履歴は何ですか?*
- "karita@woodgrovebank.com の最近の危険なサインインを一覧表示します。"
- * karita@woodgrovebank.comのリスク検出の詳細を一覧表示します。*

#### サインイン ログの詳細を取得する

Natasha は、ユーザーのサインイン ログとサインイン状態 (成功または失敗)、場所 (市区町村、州、国)、IP アドレス、デバイス情報 (デバイス ID、オペレーティング システム、ブラウザー)、サインイン リスク レベルを確認します。 また、サインイン イベントごとに関連付け ID も確認します。これは、詳細な調査に使用できます。

次のプロンプトを使用して、必要な情報を取得します。

- *"karita@woodgrovebank.com の過去 48 時間のサインイン ログを用意できますか? この情報をテーブル形式で配置します。"*
- *過去 7 日間の karita@woodgrovebank.com で失敗したサインインを表示し、IP アドレスを教えてください。*

#### 監査ログの詳細を取得する

Natasha は監査ログをチェックし、ユーザーが実行した通常とは異なるアクションまたは未承認のアクションを探します。 各アクションの日時、状態 (成功または失敗)、ターゲット オブジェクト (ファイル、ユーザー、グループなど)、クライアント IP アドレスを確認します。 さらに詳しい調査に使用できる各アクションの関連付け ID も確認します。

次のプロンプトを使用して、必要な情報を取得します。

- *"karita@woodgrovebank.com の過去 72 時間の Microsoft Entra 監査ログを取得します。 情報をテーブル形式で配置します。"*
- *このイベントの種類の監査ログを表示します。*

#### グループの詳細を取得する

その後、Natasha は、 karita@woodgrovebank.com が属しているグループを確認して、Karita が異常または機密性の高いグループのメンバーであるかどうかを確認します。 彼女は、Karita のユーザー ID に関連付けられているグループ メンバーシップとアクセス許可を確認します。 グループの詳細で、グループの種類 (セキュリティ、配布、または Office 365)、メンバーシップの種類 (割り当て済みまたは動的)、そしてグループの所有者を確認します。 また、グループのロールを確認して、リソースを管理するためのアクセス許可を決定します。

次のプロンプトを使用して、必要な情報を取得します。

- * karita@woodgrovebank.comメンバーである Microsoft Entra ユーザー グループを取得します。 情報をテーブル形式で配置します。"*
- *財務部グループの詳細を教えてください。*
- *財務部グループの所有者は誰ですか?*
- *このグループにはどのようなロールがありますか?*

### ロールを無効化する

Microsoft Security Copilot でタスクを完了した後、Natasha は、セキュリティのベスト プラクティスを維持するために、セッション中にアクティブ化された昇格されたロールを非アクティブ化する必要があります。 次のプロンプトを使用して、ロールを非アクティブ化します。

- *調査または {desired task} が完了しました。アクセス権を非アクティブ化します。*

### 改善する

Natasha は、Security Copilot を使用して、ユーザー、サインイン アクティビティ、監査ログ、危険なユーザー検出、グループ メンバーシップ、およびシステム診断に関する包括的な情報を収集できます。 調査が完了したら、Natasha は危険なユーザーを修復したりブロックを解除したりするためのアクションを実行する必要があります。

[リスクの修復](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock#risk-remediation)、[ユーザーのブロック解除](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock#unblocking-users)、[プレイブックへの対応](https://learn.microsoft.com/ja-jp/security/operations/incident-response-playbooks)について読み、次に実行できるアクションを決定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-investigate-risky-apps"} -->
## Microsoft Security Copilot を使用してアプリのリスクを調査する

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Security Copilot と Microsoft Entra のスキルを使用して、潜在的な危険なアプリケーションをすばやく調査します。

[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/security-copilot/microsoft-security-copilot) は、Microsoft Entra ID Protection による ID リスクの調査や Microsoft Entra 監査ログの詳細の調査など、さまざまなスキルを通じて Microsoft Entra データから分析情報を取得します。 アプリ リスク スキルを使用すると、Microsoft Entra でアプリケーションまたはワークロード ID を管理する ID 管理者やセキュリティ アナリストは、自然言語プロンプトを使用してリスクを特定して理解できます。 "*テナントのリスクの高いアプリの詳細を一覧表示*する" などのプロンプトを使用すると、アナリストはアプリケーション ID からリスクをよりよく把握し、Microsoft Entra で他のアプリケーションの詳細を検出できます。 詳細には、付与されたアクセス許可 (特に高い特権のアクセス許可)、テナント内の未使用のアプリ、テナントの外部からのアプリが含まれます。

その後、Security Copilot は、アプリやアクセス許可の一覧など、プロンプト コンテキストを使用して応答し、Microsoft Entra 管理センターへのリンクを表示して、管理者が完全な一覧を表示し、危険なアプリに対して適切な修復アクションを実行できるようにします。 IT 管理者とセキュリティ オペレーション センター (SOC) アナリストは、これらのスキルや他のユーザーを使用して、自然言語プロンプトを使用して ID ベースのインシデントを調査および修復するのに役立つ適切なコンテキストを得ることができます。

この記事では、SOC アナリストまたは IT 管理者が Microsoft Entra スキルを使用して潜在的なセキュリティ インシデントを調査する方法について説明します。

注

これらのアプリ リスク スキルは、Microsoft Entra のアプリケーションまたはサービス プリンシパルであるシングル テナント、サード パーティの SaaS、マルチテナント アプリに関するデータを提供します。 マネージド ID は現在スコープ内にありません。

### [前提条件]

- Security Copilot が有効になっているテナント。 詳細については、「 [Microsoft Security Copilot の概要](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot#option-2-provision-capacity-in-azure) 」を参照してください。

### シナリオと調査

Woodgrove Bank の IT 管理者である Jason は、テナント内のリスクの高いアプリを積極的に特定して理解しようとしています。 評価を開始し、 [Microsoft Security Copilot](https://securitycopilot.microsoft.com/) または [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。 アプリケーションとサービス プリンシパルの詳細を表示するには、少なくとも [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインし、Microsoft Entra でアプリケーション/ワークロード ID を管理する権限を持つ [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または同様の Microsoft Entra 管理者ロールの役割を持つ必要があります。 特定のアクションを実行するアクセス許可がないためにブロックされている場合は、Microsoft Security Copilot を使用してこのロールをアクティブ化できます。

- *{必要なタスク} を実行できるように、{required role} をアクティブにします。*

Microsoft Entra 管理センター の一部である Security Copilot を使用する ID 管理者は、セキュリティ コピロット ウィンドウの上部に表示されるアプリ リスク スターター プロンプトのセットから選択できます。 応答の後に表示される可能性がある、推奨されるプロンプトから選択します。 アプリ リスク スタータープロンプトは、アプリケーション関連の管理センター ブレード ( **エンタープライズ アプリ**、 **アプリ登録**、 **Identity Protection の危険なワークロード ID**) に表示されます。

[Image: Security Copilot のスターター プロンプトを示すスクリーンショット。]

#### Microsoft Entra のリスクの高いサービス プリンシパルを調べる

Jason は、リスク "ホット スポット" のより良い画像を得るために自然言語の質問をして調査を開始します。 これには、Microsoft の検出に基づいて、テナント内のアプリのスケールに関する初期フィルターとして [ID Protection のリスクの高いワークロード ID](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk) データが使用されます。 これらのサービス プリンシパルは、侵害されるリスクが高いです。

次のいずれかのプロンプトを使用して、必要な情報を取得します。

- *危険なアプリを表示します。*
- *悪意のあるアプリや侵害を受けているアプリはありますか?*
- *リスクレベルが高いアプリを 5 つ一覧表示します。 テーブルの書式を次のように設定します。表示名 |ID |リスクの状態*
- *リスク状態が "侵害の確認済み" のアプリを一覧表示します。*
- *ID {ServicePrincipalObjectId} (またはアプリ ID {ApplicationId}) を持つ危険なアプリの詳細を表示する*

Important

リスク情報を返すには、このスキルの ID 保護を管理する権限を持つアカウントを使用する必要があります。 テナントには [Workload Identities Premium](https://www.microsoft.com/en-us/security/business/identity-access/microsoft-entra-workload-id#office-StandaloneSKU-k3hubfz) のライセンスも必要です。

#### Microsoft Entra サービス プリンシパルについての詳細を調べる

危険であると識別されたこれらのサービス プリンシパルに関する詳細情報を取得するために、Jason は、所有者などの情報を含む Microsoft Entra から詳細情報を求めます。

次のプロンプトを使用して、必要な情報を取得します。

- *(前の応答から) これらのサービス プリンシパルの詳細を教えてください。*
- *{DisplayName} (または {ServicePrincipalId}) を使用してサービス プリンシパルの詳細を指定します。*
- *これらのアプリの所有者の一覧を教えてください。*

#### Microsoft Entra アプリケーションを調べる

また、Jason は、発行元と発行元の検証状態の詳細など、アプリケーションの詳細をグローバルに理解したいと考えています。

Jason は、次のプロンプトを使用して、選択したアプリケーションのプロパティを取得します。

- *アプリケーション {DisplayName} または {AppId} の詳細を教えてください。*
- *(前の応答から) これらのアプリの詳細を教えてください。*

#### Microsoft Entra サービス プリンシパルに付与されたアクセス許可を表示する

Jason は引き続き評価を行い、侵害された場合に潜在的な影響を見つけるために、すべてのアプリまたは 1 つのアプリに付与されたアクセス許可を知りたいと考えています。 これは通常、さまざまな種類のアクセス許可と管理者ロールで評価することは困難ですが、Copilot は調査のコンテキストでリスト内でそれを簡略化します。 このスキルは、特定の Microsoft Entra サービス プリンシパルに対する委任されたアクセス許可、アプリケーションのアクセス許可、および Microsoft Entra 管理者ロールを取得します。

Jason は、Microsoft のリスク評価に基づいて、サービス プリンシパルに付与された高い特権のアクセス許可を識別することもできます。 これらの範囲は現在、ユーザー コンテキストや高い特権を持つ Microsoft Entra 管理者ロールなしでテナント全体のアクセスを全般的に可能にするアプリケーション アクセス許可に設定されています。

Important

このスキルは現在、 [API のアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview) と [Microsoft Entra 管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)のみを対象としています。 現時点では、Azure RBAC やその他の承認システムなどの場所で付与されたディレクトリ以外のアクセス許可は確認されていません。 高い特権のアクセス許可は、時間の経過と同時に進化する可能性があり、現在、顧客が表示またはカスタマイズできない、Microsoft によって管理される静的なリストに制限されます。

次のプロンプトを使用して、必要なアクセス許可情報を取得します。

- *ID {ServicePrincipalId} またはアプリ ID {AppId} を持つアプリに付与されるアクセス許可はどれですか?*
- *上記の危険なアプリには、(以前の応答から) どのアクセス許可がありますか?*
- *このアプリに付与されるアクセス許可のうち、高い特権を持っているのはどれですか?*

#### 使用されていない Microsoft Entra アプリケーションを調べる

Jason は、未使用のアプリを削除してリスクを軽減する、簡単に実現できる機会があることを認識しています。 これらは、次の理由で簡単に勝ちます。

1. 未使用のアプリを削除すると、1 つの修復アクションで他の多くのリスクに対処できます。
2. 多くの場合、ユーザーがアプリを実際に使用していないため、停止やビジネスの中断のリスクを低く保ちながら、一元的なアクションを通じて、未使用のアプリに積極的に対処できます。

未使用のアプリに関する既存の [Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-remove-unused-apps)と統合された Copilot プロンプトを使用して、Jason は関連するデータをプルしてさらに調査するか、チームと協力してテナントのセキュリティ体制を改善します。 応答には、修復を容易にする特定のアプリへのリンクが含まれています。 アナリストは、Security Copilot で特定のアプリの詳細について直接質問することもできます。

注

Copilot 応答は、過去 90 日間に使用されていないアプリの登録またはアプリケーションの一覧を返します。この期間にトークンが発行されていません。

次のプロンプトを使用して、必要な情報を取得します。

- *未使用のアプリを表示します。*
- *未使用のアプリの数はいくつですか?*

#### 資格情報の有効期限が切れている Microsoft Entra アプリケーションまたはサービス プリンシパルを調べる

Jason は、有効期限の前にアプリケーション資格情報を更新して、中断のない操作を維持し、古い資格情報によって生じるダウンタイムのリスクを最小限に抑えるもう 1 つの機会を見ることができます。 アプリケーションとサービス プリンシパルの資格情報には、そのアプリケーションまたはサービス プリンシパルに登録する必要がある証明書やその他の種類のシークレットを含めることができます。 これらの資格情報は、アプリケーションまたはサービス プリンシパルの ID を証明するために使用されます。

有効期限切れのアプリ資格情報を更新するための既存の [Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-renew-expiring-application-credential)と、期限切れのサービス プリンシパル資格情報に関する[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-renew-expiring-service-principal-credential)に統合された Copilot プロンプトを使用して、Jason は関連データを取得し、資格情報が期限切れになることによる停止リスクを軽減して、必要に応じて更新またはローテーションします。 応答には、修復を容易にする特定のアプリへのリンクが含まれています。 アナリストは、Security Copilot で特定のアプリの詳細について直接質問することもできます。

注

Copilot 応答は、今後 30 日以内に有効期限が切れている資格情報を持つアプリケーションまたはサービス プリンシパルの一覧を返します。 次の資格情報は、この推奨事項から除外されます。

- 有効期限が切れていると識別されたが、その後アプリケーションの登録から削除された資格情報。
- 有効期限が切れた資格情報は、影響を受けるリソースの一覧に **完了** と表示 **されます**。

次のプロンプトを使用して、必要な情報を取得します。

- *有効期限が切れようとしている資格情報を持っているエンタープライズ アプリケーションはどれですか?*
- *すぐに期限切れになる資格情報を持つサービス プリンシパルを表示してください。*
- *間もなく期限切れになる資格情報を持つアプリケーションを表示します。*

#### テナントの外部で Microsoft Entra アプリケーションを探索する

Jason は、自分のテナントに存在する外部アプリや他の組織のテナントに登録されているマルチテナントアプリのリスク要因についても調査したいと考えています。 これらのアプリのセキュリティ体制は所有テナントの体制の影響を受けるので、特にこれらを確認して、領域の削減のリスクと機会を特定することが重要です。 Copilot は、現在のテナント内のサービス プリンシパルの一覧を返し、ユーザーのテナントの外部にマルチテナント アプリを登録するか、特定のサービス プリンシパルがテナントの外部に登録されているかどうかの詳細を返すことができます。

次のプロンプトを使用して、必要な情報を取得します。

- *自分のテナント外のアプリを見せてください。*
- *テナントの外部からのアプリの数はいくつですか?*

### 改善する

Jason は、Security Copilot を使用して、Microsoft Entra テナント内のアプリケーションとサービス プリンシパルに関する包括的なリスクと基本情報を収集できます。 Jason は評価を完了した後、危険なアプリケーションを修復するためのアクションを実行します。 Security Copilot では、管理者が適切な修復アクションを実行するための応答として、Microsoft Entra 管理センターへのリンクが表示されます。

[アプリケーションのアクセスとセキュリティの管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-access-security)、[セキュリティ ワークロード ID](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)、[同意フィッシングからの保護](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/protect-against-consent-phishing)、次に実行できるアクションの決定に関する[プレイブック](https://learn.microsoft.com/ja-jp/security/operations/incident-response-playbook-compromised-malicious-app)について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-lifecycle-workflows"} -->
## Microsoft Security Copilot を使用してライフサイクル ワークフローを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Entra 管理センターで Microsoft Security Copilot を使用して、Joiner、Mover、Leaver の各シナリオのライフサイクル ワークフローを作成します。 ワークフローをオンデマンドで実行し、ワークフローの分析情報を使用して実行を監視し、必要に応じてトラブルシューティングを行います。

Microsoft Entra ID Governance は、[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/security-copilot/microsoft-security-copilot) の機能を適用して、JML シナリオ全体でユーザーのライフサイクルを管理するようにカスタム ワークフローを構成する際の ID 管理者の時間と労力を節約します。 また、自然言語を使用してワークフローをより効率的にカスタマイズし、カスタム タスクを含むワークフロー情報を構成したり、ワークフローを実行したり、ワークフローの分析情報を取得したりするのにも役立ちます。

この記事では、Microsoft Entra 管理センターの Security Copilot を使用して、次のユース ケースに対してライフサイクル ワークフローを操作する方法について説明します。

- 新しいライフサイクル ワークフローの詳細なガイダンスを作成する
- 使用可能なワークフロー構成を調べる
- アクティブなワークフロー リストを分析する
- ワークフローの処理結果のトラブルシューティング
- ライフサイクル ワークフローのバージョンを比較する

### [前提条件]

- Security Copilot が有効になっているテナント。 詳細については、「 [Microsoft Security Copilot の概要](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot#option-2-provision-capacity-in-azure) 」を参照してください。
- [Microsoft Entra ID ガバナンス ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview#license-requirements)。
- [ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflow-administrator)の割り当てられたロール

### Microsoft Entra で Security Copilot を起動する

1. 特定のユース ケースに基づいて、シナリオに適した管理ロールを使用して [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. Microsoft Entra 管理センターの **[Copilot** ] ボタンから Security Copilot を起動します。

    [Image: Microsoft Entra 管理センターの Security Copilot を示すスクリーンショット。]
3. 情報を取得したり、自然言語クエリを使用してアクションを実行したりするには、次のユース ケースとプロンプトを参照してください。

注

権限が不十分でアクションがブロックされる場合は、推奨されるロールが表示されます。 Security Copilot チャットで次のプロンプトを使用して、必要なロールをアクティブ化できます。 これは、必要なアクセスを提供する資格のあるロールの割り当てに依存します。

- *{必要なタスク} を実行できるように、{required role} をアクティブにします。*

### 新しいライフサイクル ワークフローの詳細なガイダンスを作成する

Security Copilot では、新しいライフサイクル ワークフローを作成するための手順を説明します。 ワークフローがトリガーされたときに実行するアクションと、このワークフローを実行するユーザー (スコープ) を定義する条件、およびワークフローを実行するタイミング (トリガー) を示すプロンプトを表示します。 例えば次が挙げられます。

*マーケティング部門の新入社員向けのライフサイクル ワークフローを作成し、ウェルカム メールと TAP を送信し、[マイ テナント内のすべてのユーザー] グループに追加します。 また、ワークフローのスケジュールを有効にするオプションを指定します。*

返された結果を確認してワークフローに含まれる内容を確認し、手順に従って Microsoft Entra 管理センターで [新しいワークフローを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-lifecycle-workflow) します。 ワークフローが作成されたら、スケジュールを有効にする前に検証テストを実行できます。

### 使用可能なワークフロー構成を調べる

Microsoft Security Copilot を使用すると、さまざまなライフサイクル ワークフローを効率的に管理できます。 Security Copilot で実行できる一般的なタスクを次に示します。

例えば次が挙げられます。

- *テナント内のすべてのライフサイクル ワークフローを一覧表示する*
- *新しいワークフローを作成するためにサポートされているすべてのワークフロー テンプレートを一覧表示する*
- *ライフサイクル ワークフローの設定は何ですか?*
- *ライフサイクル ワークフローを使用して自動化できる離職者タスクはどれですか?*
- *ムーバー ワークフローを作成するために使用できるテンプレートは何ですか?*

### アクティブなワークフロー リストを分析する

Microsoft Security Copilot を使用すると、アクティブなワークフロー リストを簡単に分析および管理し、特定のワークフロー情報を取得できます。

例えば次が挙げられます。

- *{workflow name} という名前のライフサイクル ワークフローを取得します。*
- *テナント内のすべての Mover ワークフローを一覧表示します。*
- *テナント内のすべての削除済みライフサイクル ワークフローを一覧表示します。*
- *テナント内のすべての無効なライフサイクル ワークフローを一覧表示します。*
- *無効なワークフロー {workflow} の詳細を表示します。*

### ライフサイクル ワークフローの実行のトラブルシューティング

Security Copilot を使用すると、ワークフローの実行のトラブルシューティングに役立ちます。 Security Copilot は、指定された情報を使用して、指定されたワークフローの特定の期間におけるワークフロー履歴の豊富な概要を生成して返します。

特定のワークフローのワークフロー処理結果を調べます。

- *過去 7 日間の {workflow} の実行を要約します。*
- *過去 24 時間にワークフローが実行された回数。*
- *過去 7 日間にこのワークフローで処理できなかったユーザーはどれですか?*
- *過去 7 日間に {workflow} で失敗したタスクはどれですか?*
- *過去 7 日間の {workflow} のユーザー処理結果の概要を表示します。*

ワークフロー全体のワークフロー処理の結果を調べます。

- *過去 7 日間に処理されたワークフローの数はいくつですか?*
- *過去 14 日間にワークフローによって正常に処理されたユーザーの数はいくつですか?*
- *過去 7 日間で最も多く実行されたワークフローはどれですか?*
- *過去 30 日間で最も失敗したタスクはどれですか?*
- *過去 7 日間で最も失敗したワークフローはどれですか?*
- *過去 30 日間に実行されたムーバーワークフローはいくつですか？*

### ライフサイクル ワークフローのバージョンを比較する

Security Copilot を使用して、ワークフローのバージョンを比較できます。 Security Copilot は、提供された情報を使用して、指定されたワークフローの 2 つのバージョンのコンテンツの豊富な概要と、タスクや実行条件を含むワークフロー バージョン間の主要な違いを生成して返します。

例えば次が挙げられます。

- *{workflow} のすべてのワークフロー バージョンを一覧表示します。*
- *{workflow} を最後に変更したユーザーとそのタイミングを表示します。*
- *このワークフローの {version #} の詳細を表示します。*
- *このワークフローの最後のバージョンで何が変更されましたか?*
- *このワークフローの最後の 2 つのバージョンを比較します。*
- *このワークフローの {version #} と {version #} を比較します。*
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-monitoring-operations"} -->
## Microsoft Security Copilot を使用した監視について

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-monitoring-operations
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Entra 管理センターの Microsoft Security Copilot を使用して、サインイン アクティビティ、監査ログ、プロビジョニング ログ、正常性アラート、サービス レベル アグリーメントを監視します。

Microsoft Security Copilot は、管理者がサインイン アクティビティをすばやく分析し、監査ログを調査し、システムの正常性を監視し、自然言語クエリを使用してサービスのパフォーマンスを追跡できるようにすることで、Microsoft Entra での運用監視を効率化します。 この機能は、運用上の認識を維持し、組織に影響を与える前に問題をすばやく特定して対応するのに役立ちます。

この記事では、IT 管理者が Microsoft Security Copilot の監視および運用スキルを使用して、次のユース ケースをカバーすることで、Microsoft Entra 環境の運用正常性レビューを実施する方法について説明します。

- サインイン アクティビティを分析する
- 管理者とユーザーの変更に関する監査ログを調査する
- プロビジョニング アクティビティの分析
- 推奨事項を使用してセキュリティ体制を改善する
- システムの正常性を監視し、事前にアラートに対処する
- サービス レベル アグリーメント (SLA) のパフォーマンスを追跡する

この記事のプロンプトと例を使用して、チームまたは管理による運用レビューとインシデント対応のための実用的な分析情報とレポートに結果をまとめることができます。

### [前提条件]

さまざまな監視と運用のユース ケースには、次のロールとライセンスが必要です。

| 利用シーン | Role | ライセンス | Tenant |
| --- | --- | --- | --- |
| **サインイン ログ** | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)、 [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、または [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | [Microsoft Entra ID P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | サインイン データを含むパブリック クラウド テナント |
| **監査ログ** | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)、 [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、または [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) | 無料ライセンスが必要 | 監査アクティビティを含むパブリック クラウド テナント |
| **プロビジョニング ログ** | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)、 [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-owner) | 7 日間無料、その後 [Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | [Microsoft Entra プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning)を使用するすべてのテナント |
| **推奨 事項** | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)、 [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)、 [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、 [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)、 [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)、 [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | 無料の Microsoft Entra ID または [P1/P2](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | 任意のテナント (ワークロード ID でも使用可能) |
| **正常性監視アラート** | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)、 [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)、 [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)、 [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、または [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | [Microsoft Entra ID P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | パブリック クラウド テナント |
| **サービス水準合意** | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) | いずれかのMicrosoft Entra IDライセンス | 任意のテナント |

### Microsoft Entra で Security Copilot を起動する

1. 特定のユース ケースに基づいて、シナリオに適した管理ロールを使用して [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. Microsoft Entra 管理センターの **[Copilot** ] ボタンから Security Copilot を起動します。

    [Image: Microsoft Entra 管理センターの Security Copilot を示すスクリーンショット。]
3. 情報を取得したり、自然言語クエリを使用してアクションを実行したりするには、次のユース ケースとプロンプトを参照してください。

注

権限が不十分でアクションがブロックされる場合は、推奨されるロールが表示されます。 Security Copilot チャットで次のプロンプトを使用して、必要なロールをアクティブ化できます。 これは、必要なアクセスを提供する資格のあるロールの割り当てに依存します。

- *{必要なタスク} を実行できるように、{required role} をアクティブにします。*

### サインイン アクティビティを分析する

組織全体のサインイン アクティビティを分析することで、評価を開始できます。 組織のセキュリティとユーザー エクスペリエンスを維持するには、認証パターンを理解し、潜在的な問題を特定することが重要です。

#### アプリケーションと認証の分析

まず、特定のアプリケーションと認証方法に関連するサインイン パターンを調査して、セキュリティ ポリシーに従っていることを確認し、異常や問題を特定します。 必要な情報を取得するには、次のプロンプトを使用します。

- *特定のアプリケーションへのサインインを表示します。*
- *多要素認証なしでサインインを表示します。*
- *特定の条件付きアクセス ポリシーによるサインインエラーを表示します。*
- *満足できない条件付きアクセス ポリシーを使用してサインインを表示します。*

#### デバイスと場所の分析

デバイスのコンプライアンス、オペレーティング システム、ブラウザー、地理的な場所に基づいてサインイン アクティビティをさらに分析し、異常なパターンや潜在的なセキュリティ上の脅威を監視し、企業のデバイスが適切に使用されていることを確認できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *準拠していないデバイスからのサインインを表示します。*
- *特定の Web ブラウザーからのログインを表示します。*
- *特定のオペレーティング システムからのログインを表示します。*
- *特定の場所からのサインインを表示します。*

#### ユーザー アクティビティとセキュリティの監視

個々のユーザー アクティビティに焦点を当て、疑わしい動作や、侵害されたアカウントや、直ちに注意が必要なセキュリティ上の脅威を示す可能性のある危険なサインインを監視できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *特定の期間以降のサインイン アクティビティを表示します。*
- *ユーザー Casey Jensen のサインイン アクティビティを表示します。*
- *疑わしいログイン アクティビティを表示します。*
- *危険なサインインを表示します。*

### 管理者とユーザーの変更に関する監査ログを調査する

次に、監査ログを調査して、Microsoft Entra 環境の管理者とユーザーによって行われた変更を追跡します。 この分析は、潜在的なセキュリティの問題を特定し、コンプライアンスを確保し、管理アクティビティの適切なガバナンスを確保するのに役立ちます。 次のプロンプトを使用して、必要な情報を収集します。

#### グループ管理活動

調査を続行するには、適切なアクセス制御を維持し、セキュリティとコンプライアンスの目的でグループ関連の変更を確実に追跡するために重要なグループ管理アクティビティに焦点を当てます。 必要な情報を取得するには、次のプロンプトを使用します。

- *最近削除したグループを表示します。*
- *最近削除されたグループは何ですか?*
- *ディレクトリ内の最後に削除されたグループ*
- *このグループを作成したユーザー*
- *特定のグループを作成したユーザーを確認します。*
- *グループ作成の詳細。*
- *これらのユーザーによって作成されたグループは何ですか?*
- *特定のユーザーによって作成されたグループを表示します。*
- *ユーザー Casey Jensen によって作成されたすべてのグループを一覧表示します。*

#### セキュリティと認証のアクティビティ

潜在的なセキュリティの問題を特定するには、監査ログのセキュリティと認証に関連するアクティビティに焦点を当てます。 これにより、セキュリティ ポリシーに従い、疑わしいアクティビティが迅速に調査されるようになります。 また、サービス プリンシパルアクティビティを調査して、これらの重要な操作が正しく機能していることを確認することもできます。 必要な情報を取得するには、次のプロンプトを使用します。

- *危険なサインインを表示します。*
- *疑わしいログインを一覧表示します。*
- *危険な認証はありますか?*
- *このサービス プリンシパルのジョブを一覧表示します。*

### プロビジョニング アクティビティの分析

監査ログを調査した後は、Microsoft Entra 環境内のプロビジョニング アクティビティに特に集中できます。 プロビジョニング ログは、ユーザー アカウントのライフサイクル操作に関する貴重な分析情報を提供し、管理者が接続されたアプリケーションとシステム全体で意図したとおりにユーザーが作成、更新、削除されていることを確認するのに役立ちます。 この分析は、ID インフラストラクチャの整合性を維持し、スムーズなユーザー管理プロセスを確保するために重要です。

#### ユーザー プロビジョニングの監視

まず、個々のユーザー プロビジョニング アクティビティを監視してアカウント ライフサイクル イベントを追跡し、プロビジョニング操作が特定のユーザーに対して正しく動作していることを確認します。 必要な情報を取得するには、次のプロンプトを使用します。

- *このユーザーのプロビジョニング ログを表示します。*
- *ユーザーのプロビジョニング履歴を取得します。*
- *ユーザー プロビジョニング アクティビティを表示します。*
- *このユーザーの最近のプロビジョニング イベントを表示します。*

#### プロビジョニングエラーの分析

プロビジョニングの問題が発生した場合は、ユーザー アクセスの問題を防ぐために、問題をすばやく特定して解決することが重要です。 プロビジョニングの失敗に焦点を当てて、問題の原因を理解し、是正措置を講じてください。 必要な情報を取得するには、次のプロンプトを使用します。

- *プロビジョニングエラーを表示します。*
- *失敗したすべてのプロビジョニング試行を一覧表示します。*
- *プロビジョニング エラー ログを表示します。*

#### プロビジョニング成功の追跡

プロビジョニング操作の成功を監視して、プロビジョニング サービスが最適に機能していることを確認し、完了したライフサイクル イベントを追跡します。 これは、意図した変更が正しく適用されていることを確認するのに役立ちます。 必要な情報を取得するには、次のプロンプトを使用します。

- *プロビジョニングが正常に削除されたことを示します。*
- *プロビジョニング サービスによってユーザーが正常に削除されましたか?*
- *成功したプロビジョニング無効化を表示します。*
- *プロビジョニング サービスによってユーザーが正常に無効にされたかどうか。*
- *プロビジョニングが成功したことを表示します。*
- *成功したオブジェクトの作成を一覧表示します。*

#### プロビジョニング ジョブの状態の監視

プロビジョニング ジョブの状態とパフォーマンスを監視して、それらが正しく実行されていることを確認し、注意が必要な問題を特定することもできます。 必要な情報を取得するには、次のプロンプトを使用します。

- *プロビジョニング ジョブの状態を確認する。*
- *プロビジョニング ジョブは完了していますか?*
- *このサービス プリンシパルのプロビジョニング ジョブを表示します。*

### 推奨事項を使用してセキュリティ体制を改善する

セキュリティ体制の包括的なビューを取得するには、Microsoft Entra の推奨事項を活用できます。これは、改善の領域を特定し、実行可能な分析情報を提供して、組織のセキュリティとベスト プラクティスへのコンプライアンスを強化するのに役立ちます。

#### 一般的な推奨事項とセキュリティ スコア

一般的な推奨事項とセキュリティ スコア分析から始めて、テナントのセキュリティ体制の概要を確認します。 次のプロンプトを使用して、必要な情報を収集します。

- *Entra のすべての推奨事項を一覧表示します。*
- *テナントのセキュリティ スコアの履歴データを表示します。*
- *Entra の推奨事項 "例" とその詳細を表示します。*
- *Entra の推奨事項の影響を受けるリソースを表示します。*
- *Entra の推奨設定「example」に関するリソース「example」を表示します。*
- *セキュリティ スコアの推奨事項を一覧表示します。*
- *ベスト プラクティスの推奨事項を一覧表示します。*

#### カテゴリ別の対象となる推奨事項

一般的な概要を確認したら、条件付きアクセス ポリシー、アプリケーション セキュリティ、テナント構成など、特定の懸念領域に対処するための推奨事項の特定のカテゴリに焦点を当てることができます。 必要な情報を取得するには、次のプロンプトを使用します。

- *条件付きアクセス ポリシーの推奨事項を一覧表示します。*
- *特定の機能領域に関する Entra の推奨事項を表示します。*
- *優先度の高い推奨事項を一覧表示します。*
- *優先順位の高い推奨事項を一覧表示します。*
- *アクティブな推奨事項を一覧表示します。*
- *アプリ ポートフォリオの正常性を向上させるための推奨事項を一覧表示します。*
- *領域のリスクを軽減するための推奨事項を一覧表示します。*
- *アプリのセキュリティ体制を改善するための推奨事項を一覧表示します。*
- *テナント構成の推奨事項を一覧表示します。*
- *影響の種類別に Entra の推奨事項を表示します。*

#### アプリケーション資格情報の管理

また、アプリケーションの資格情報の管理に重点を置いて、アプリケーションがセキュリティで保護されていることと、承認されていないアクセスを防ぐために資格情報が適切に管理されていることを確認することもできます。 必要な情報を取得するには、次のプロンプトを使用します。

- *有効期限が切れようとしている資格情報を持っているエンタープライズ アプリケーションはどれですか?*
- *すぐに期限切れになる資格情報を持つサービス プリンシパルを表示してください。*
- *間もなく期限切れになる資格情報を持つアプリケーションを表示します。*
- *テナントで古いアプリまたは未使用のアプリはどれですか?*
- *未使用のアプリを一覧表示します。*

### システムの正常性を監視する

システムの正常性を監視して異常を検出し、潜在的な問題が組織に影響を与える前に事前に対処することで、評価を続行できます。 予防的な正常性の監視は、サービスの中断を防ぎ、システムの信頼性を維持するのに役立ちます。

#### 健康アラートの監視

システムの正常性の一般的な概要については、正常性アラートを監視することから始めることができます。 これにより、注意が必要な問題に関する情報を常に把握し、システムが最適に機能していることを確認できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *私のテナントにはどのような健康のアラートがありますか?*
- *すべてのアクティブな正常性監視アラートを一覧表示します。*
- *私の最近の健康管理アラートは何ですか?*
- *アクティブな正常性監視アラートに従って影響を受けるユーザーは何ですか?*
- *アラートID [alertId] の正常性監視アラートの詳細を教えてください。*

#### シナリオ固有の状態監視

一般的な概要を確認したら、特定の正常性監視シナリオに焦点を当てて、多要素認証 (MFA) の問題やデバイスのコンプライアンスなど、特定の懸念事項に対処できます。 次のプロンプトまたはシナリオに固有のプロンプトを使用して、必要な情報を取得します。

- *MFA サインインの失敗に関するヘルス モニタリング アラートを表示します。*
- *管理対象デバイスの正常性監視アラートを表示します。*
- *準拠しているデバイスの正常性監視アラートを表示します。*
- *デバイスシナリオのヘルスモニタリングアラートを見せてください。*

### サービス レベル アグリーメントのパフォーマンスを追跡する

最後に、サービス レベル アグリーメント (SLA) のパフォーマンスを評価して、組織がコミットメントを確実に満たしていることを確認し、改善する領域を特定できます。 SLA で認証の可用性を監視し、サービスの停止と共に SLA レポートを確認して、サービスの品質とサービス クレジットの適格性を確認します。

#### SLA パフォーマンスの監視

SLA のパフォーマンスを監視し、注意が必要になる可能性がある潜在的な問題を特定するには、次のプロンプトを使用します。

- *Microsoft Entra 認証の SLA は何ですか?*
- *Microsoft Entra SLA とは*
- *Microsoft Entra 認証の SLA は何ですか?*
- *テナントの認証の可用性を表示します。*
- *過去 1 か月間にテナントに SLA 違反が発生しましたか?*
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-risky-user-summarization"} -->
## Copilot を使用して危険なユーザーを調査する

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Entra の Copilot を使用して、ユーザーのリスク レベルを要約し、インシデントに関連する分析情報を受け取ることで、ID の脅威に迅速に対応します。

Microsoft Entra ID Protection は [、Microsoft Entra の Copilot](https://learn.microsoft.com/ja-jp/security-copilot/microsoft-security-copilot) の機能を適用して、ユーザーのリスク レベルを要約し、目の前のインシデントに関連する分析情報を提供し、迅速な軽減のための推奨事項を提供します。 ID リスク調査は、組織を守るために不可欠なステップです。 Microsoft Entra の Copilot は、IT 管理者とセキュリティ オペレーション センター (SOC) アナリストに、ID リスクと ID ベースのインシデントを調査および修復するための適切なコンテキストを提供することで、解決までの時間を短縮するのに役立ちます。 危険なユーザー要約は、管理者とレスポンダーの調査を支援するため、コンテキストに沿って最も重要な情報にすばやくアクセスできるようにします。

ID の脅威に迅速に対応する:

- リスクの概要: ユーザー リスク レベルが昇格された理由を自然言語で要約します。
- 推奨事項: これらの種類の攻撃を軽減して対応する方法に関するガイダンスと、ヘルプとドキュメントへのクイック リンクが記載されています。

この記事では、Microsoft Entra の Microsoft Entra ID Protection と Copilot の危険なユーザー概要機能にアクセスする方法について説明します。 この機能を使用するには、 [Microsoft Entra ID P2 ライセンスが必要です](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements)。

注

テナントで [ID リスク管理エージェント](https://learn.microsoft.com/ja-jp/entra/id-protection/identity-risk-management-agent-get-started) が有効になっている場合は、このビューに別の危険なユーザーの概要が表示されます。

### [前提条件]

- Security Copilot が有効になっているテナント。 詳細については、「 [Microsoft Security Copilot の概要](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot#option-2-provision-capacity-in-azure) 」を参照してください。

### 危険なユーザーを調査する

危険なユーザーを表示して調査するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインします。
2. **ID Protection**&gt;[リスクのあるユーザー](https://aka.ms/entracopilotriskyuser)に移動します。
3. 危険なユーザー レポートからユーザーを選択します。

    [Image: ID Protection の危険なユーザー レポートを示すスクリーンショット。]
4. [ **危険なユーザーの詳細** ] ウィンドウで、[ **集計** ] に情報が表示されます。

    [Image: ID 保護の危険なユーザーに関する要約の詳細を示すスクリーンショット。]

危険なユーザーの概要には、次の 3 つのセクションが含まれています。

- Copilot による概要: ID Protection がユーザーにリスクのフラグを設定した理由を自然言語で要約します。
- 操作: このインシデントを調査し、将来のインシデントを防ぐための次の手順を示します。
- ヘルプとドキュメント: ヘルプとドキュメントのリソースを一覧表示します。

この例では、推奨される修復方法は次のとおりです。

- サインイン リスクとユーザー リスクベースの [条件付きアクセス ポリシーを作成します](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)。

推奨されるヘルプとドキュメントは次のとおりです。

- [ID Protection のリスクとは](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)
- [インシデント応答のプレイブック](https://learn.microsoft.com/ja-jp/security/operations/incident-response-playbooks)
- [リスクベースのアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies)

### Copilot を使用して危険なユーザーを調査する

Microsoft Entra 管理センターの **[Copilot** ] ボタンから Security Copilot を起動します。 自然言語の質問またはプロンプトを使用して、次の操作を行います。

- リスクに基づいてユーザーを一覧表示または識別する
- ユーザー固有のリスク情報を抽出する
- ユーザー リスク履歴の概要

#### リスクに基づいてユーザーを一覧表示または識別する

Microsoft Security Copilot を使用すると、システム内のユーザー リスクの状態に関する情報を簡単に取得して要約できます。

例えば次が挙げられます。

- *現在危険のフラグが設定されているすべてのユーザーを一覧表示します。*
- *現在危険にさらされているユーザーを表示します。*
- *危険とマークされているユーザーを特定します。*
- *侵害されたすべてのユーザーを一覧表示します。*
- *現在安全と見なされているユーザーを表示します。*
- *現在、リスクの高いフラグが設定されているユーザーの数はいくつですか?*
- *危険なすべてのユーザーの数を指定します。*

#### ユーザー固有のリスク情報

Microsoft Security Copilot を使用すると、特定のユーザーに集中し、リスク レベルを特定できます。

例えば次が挙げられます。

- *このユーザーが現在高リスクであるかどうかを判断する*
- *このユーザーの詳細なリスク情報を表示する*

#### ユーザー リスク履歴

Microsoft Security Copilot を使用すると、ユーザーに関する過去の情報を経時的に取得して、リスク履歴を確立できます。

- *このユーザーのリスク履歴を表示する*
- *このユーザーにリスクのフラグが設定されていること*
- *このユーザーは以前に危険にさらされていましたか*
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-security-access-control"} -->
## Microsoft Security Copilot を使用したセキュリティとアクセス制御

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-access-control
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Entra 管理センターの Microsoft Security Copilot を使用して、ロール、認証方法、条件付きアクセス ポリシーを管理し、アプリケーションのリスクを評価します。

Microsoft Security Copilot は、管理者が自然言語クエリを使用して Microsoft Entra で包括的なセキュリティ制御を実装および管理できるようにします。 この機能は、リソースへのアクセスの制御、認証方法の構成、特権ロールの管理、および組織のセキュリティ体制を強化するためのアプリケーション リスクの評価に役立ちます。

この記事では、Microsoft Security Copilot を使用して、組織全体のロールベースのアクセス制御、認証方法、条件付きアクセス ポリシー、デバイス コンプライアンスを評価してセキュリティ評価を行う方法について説明します。 具体的には、Microsoft Entra 管理センターで次のユース ケースを使用します。

- ロールの割り当てを調査して管理する
- 認証方法とポリシーを構成する
- 条件付きアクセス ポリシーを分析する
- デバイス コンプライアンスの監視と管理

### [前提条件]

- Security Copilot が有効になっているテナント。 詳細については、「 [Microsoft Security Copilot の概要](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot#option-2-provision-capacity-in-azure) 」を参照してください。
- さまざまなセキュリティとアクセス制御のユース ケースには、次のロールとライセンスが必要です。

    | 利用シーン | Role | ライセンス | Tenant |
    | --- | --- | --- | --- |
    | **ロール管理** | [ディレクトリリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-readers) または [グローバルリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | いずれかのMicrosoft Entra IDライセンス | 任意のテナント |
    | **デバイス管理** | 任意のユーザーが使用できます | 無料の Microsoft Entra ID ライセンス | 任意のテナント |
    | **認証管理** | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) (テナント) または [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) (ユーザー) | いずれかのMicrosoft Entra IDライセンス | 任意のテナント |
    | **条件付きアクセス** | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、 [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)、または [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [Microsoft Entra ID P1 ライセンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements) | CA ポリシーを持つテナント |

### Microsoft Entra で Security Copilot を起動する

1. 特定のユース ケースに基づいて、シナリオに適した管理ロールを使用して [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. Microsoft Entra 管理センターの **[Copilot** ] ボタンから Security Copilot を起動します。

    [Image: Microsoft Entra 管理センターの Security Copilot を示すスクリーンショット。]
3. 情報を取得したり、自然言語クエリを使用してアクションを実行したりするには、次のユース ケースとプロンプトを参照してください。

注

権限が不十分でアクションがブロックされる場合は、推奨されるロールが表示されます。 Security Copilot チャットで次のプロンプトを使用して、必要なロールをアクティブ化できます。 これは、必要なアクセスを提供する資格のあるロールの割り当てに依存します。

- *{必要なタスク} を実行できるように、{required role} をアクティブにします。*

### ロールの割り当てを調査して管理する

ロールベースのアクセス制御 (RBAC) を調べて、特権ロールが適切に割り当てられ、最小特権の原則が組織の Microsoft Entra セットアップ全体で従っていることを確認して、評価を開始します。

#### ロールの割り当てクエリ

まず、現在のロールの割り当てを調査して、機密性の高い管理機能にアクセスできるユーザーを把握し、特権アクセスが適切に制御されていることを確認します。 必要な情報を取得するには、次のプロンプトを使用します。

- *ユーザー/グループ/アプリ (名前/電子メール/ID) にはどのようなロールがありますか?*
- *ユーザー/グループ/アプリ (名前/電子メール/ID) が持つ推移的なロールは何ですか?*
- *ユーザー/グループ/アプリ (名前/電子メール/ID) の対象ロールは何ですか?*
- *ユーザー/グループ/アプリ (名前/電子メール/ID) のスケジュールされたロールは何ですか?*
- クラウド アプリケーション管理者ロールは誰に割り当てられていますか？
- *グローバル読者ロールの資格を持つことができるのは誰ですか？*

#### 役割の情報と識別

その後、特定のロールとその識別子を調べて、アクセス許可のスコープを理解し、ロールの割り当てがビジネス要件とセキュリティ ポリシーと一致していることを確認できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *ロールの ID (ロール名) とは*
- *ID 5d6b6bb7-de71-4623-b4af-96380a352509 のロールの名前*

### 認証方法とポリシーを構成する

次に、Microsoft Entra テナント全体の認証方法を評価および管理して、ユーザーとリソースを保護するための強力な認証プラクティスが実施されていることを確認する必要があります。

#### 認証方法の構成

次に、認証方法を評価して、強力なプラクティスが実施されていること、および承認されていないアクセスから保護するように多要素認証 (MFA) が適切に構成されていることを確認できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *テナントで有効になっている認証方法は何ですか?*
- *テナントで Microsoft Authenticator は有効になっていますか? 誰のために?*
- *テナントで登録キャンペーンが有効になっているかどうかですか？ 誰のために?*
- *テナントでシステム優先認証は有効になっていますか? 誰のために?*
- *テナントで「疑わしいアクティビティの報告」が有効になっていますか? 誰のために?*

#### ユーザー認証の状態

その後、個々のユーザー認証の構成と登録の状態を確認して、セキュリティ ポリシーに準拠していることを確認し、認証のセットアップに関するサポートが必要なユーザーを特定できます。 必要な情報を取得するには、次のプロンプトを使用します。

- * karita@woodgrovebank.comはどのような認証方法を登録していますか?*
- *ユーザー karita@woodgrovebank.com ユーザーごとの MFA は有効になっていますか?*
- *FIDO2 セキュリティ キーメソッドが登録されているユーザーの数*

### デバイス コンプライアンスの監視と管理

組織全体のデバイス コンプライアンスを監視および調査することで、評価を続行できます。 デバイスコンプライアンスは、セキュリティで保護された準拠デバイスのみが機密情報や企業リソースにアクセスできるようにするために、多くの組織にとって重要です。

#### デバイスの識別と状態

デバイスの詳細とコンプライアンスの状態を調べることで、組織のリソースにアクセスするデバイスがセキュリティ標準とポリシーを満たしていることを確認できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *ID が {ID} のデバイスを表示する*
- *準拠しているすべてのデバイスを表示する/準拠していないすべてのデバイスを表示します。*
- *管理されていないデバイスを一覧表示します。*
- *デバイスの数はいくつですか?*

#### デバイス結合の種類と構成

また、デバイスの参加の種類と構成を調査して、Microsoft Entra セットアップ内でデバイスが適切に登録および管理されていることを確認することもできます。 必要な情報を取得するには、次のプロンプトを使用します。

- *Entra ID 登録済み/Entra ID 参加済み/Entra ID ハイブリッド参加済みのすべてのデバイスを一覧表示します。*
- *デバイス信頼の種類ごとにいくつのデバイスが存在しますか?*

#### デバイス アクティビティとオペレーティング システム

次に、デバイスのアクティビティとオペレーティング システムを調べて、デバイスがアクティブに管理され、サポートされているセキュリティで保護されたオペレーティング システムが実行されていることを確認する必要があります。 必要な情報を取得するには、次のプロンプトを使用します。

- *デバイス {ID} が最後にアクティブだった日時を表示します。*
- *特定の {オペレーティング システム名} のデバイスを一覧表示します。*
- *Windows (8,10,11) を実行しているデバイスの数を表示します。*
- *リリース別に分類された Windows デバイスの数を表示します。*

### 条件付きアクセス ポリシーの分析

最後に、組織の条件付きアクセス ポリシーを評価して、適切なアクセス制御が実施されていること、および組織のセキュリティ要件がすべてのユーザーとデバイスに一貫して適用されていることを確認する必要があります。

#### ポリシーの識別と状態

条件付きアクセス ポリシーの現在の状態を調べて、アクティブなポリシーを把握し、重要なセキュリティ制御が適切に適用されていることを確認できます。 必要な情報を取得するには、次のプロンプトを使用します。

- *認証強度ポリシー。*
- *名前による条件付きアクセス ポリシー。*
- *アクティブな多要素認証の条件付きアクセス ポリシー。*
- *強制された MFA ポリシー。*
- *条件付きアクセス ポリシーを有効にしました。*
- *現在適用されている CA ポリシー。*
- *積極的に適用される CA ポリシー。*

#### ポリシーの構成と管理

最後に、特定のポリシー構成を調査して、カバレッジのギャップを特定し、ポリシーが組織のセキュリティ体制とコンプライアンス要件と一致することを確認できます。

- *有効にする条件付きアクセス ポリシー。*
- *現在有効になっていない CA ポリシーは何ですか?*
- *どのポリシーを有効にできますか?*
- *非アクティブな条件付きアクセス ポリシーの一覧表示/*
- *条件付きアクセス ポリシー。*
- *CA ポリシーを一覧表示します。*
- *信頼できる場所に適用できない CA ポリシーは何ですか?*
- *すべての信頼できる場所を除く条件付きアクセス ポリシー。*
- *グループを検討しているユーザーのすべての条件付きアクセス ポリシーを取得します。*
<!-- /MSL-PAGE -->
