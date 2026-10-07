# Microsoft Learn — Microsoft Entra / 基礎・アーキテクチャ・標準・その他 (part 6)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 41

---

<!-- MSL-PAGE {"url":"entra/security-copilot/entra-security-scenarios"} -->
## Microsoft Entra の Microsoft Security Copilot シナリオの概要

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-scenarios
- Service: entra
- Article date: 2025-09-23
- Summary: Microsoft Entra の製品とサービス全体の Microsoft Security Copilot シナリオについて説明します。

Microsoft Security Copilot は、Microsoft Entra ID 環境の管理とセキュリティ保護に役立つ強力なツールです。 この記事では、自然言語クエリを使用して調査できる Microsoft Entra のさまざまな機能について説明します。 これらの機能は、ID 保護の取り組みを強化するために、さまざまな Microsoft Entra 製品で利用できます。 Microsoft Entra で Security Copilot を使用するには、Security Copilot が有効になっているテナントがあることを確認します。

### Microsoft Security Copilot と Microsoft Entra の統合

Security Copilot は Microsoft Entra 管理センターの一部であり、それを使用して独自のプロンプトを作成できます。 Security Copilot は、メニュー バーのグローバルに使用可能なボタンから起動されます。 [Security Copilot] ウィンドウの上部に表示される一連のスターター プロンプトから選択するか、プロンプト バーに独自のプロンプトを入力して開始します。 推奨されるプロンプトは、応答の後に表示されます。これは、Security Copilot が以前の応答に基づいて選択する定義済みのプロンプトです。

[Image: Microsoft Entra 管理センターの Security Copilot を示すスクリーンショット。]

#### Microsoft Security Copilot を使用したデータ探索 (プレビュー)

Microsoft Security Copilot では、プロンプトから 10 個を超える項目を含むデータセットが返されたときのデータ探索がサポートされます。 この機能はプレビュー段階であり、一部の Microsoft Entra シナリオで使用できます。 Copilot チャットの応答から、[ **一覧を開く** ] を選択して、包括的なデータ グリッドにアクセスします。 これにより、完全で正確な結果を持つ大規模なデータセットを探索でき、より効率的な意思決定が可能になります。 各データ グリッドには基になる Microsoft Graph URL が表示され、クエリの精度を確認し、結果の信頼性を高めることができます。

注

この機能は現在プレビュー段階であり、単純なシングルステップ プロンプト (たとえば *、"営業部門のユーザーの一覧を提供*する" など) に制限されています。 マルチステップ プロンプトとクロス シナリオ機能 ( *"高い特権を持つ危険なアプリはどれですか?"*) を必要とするタスクは、現在、この機能ではサポートされていません。 Copilot では、すべてのプロンプトに対してチャットベースの概要が引き続き提供されます。

[Image: Microsoft Entra の Security Copilot でのデータ探索を示すスクリーンショット。]

### Microsoft Entra の Copilot セキュリティ シナリオ

Microsoft Entra では、さまざまなセキュリティ コピロット シナリオを利用できます。 次の表を使用して、製品領域別の各シナリオ、ユース ケース、ライセンス、ロールの要件の詳細を確認します。

| Microsoft Entra 製品 | セキュリティ の Copilot シナリオ | データ探索が有効 |
| --- | --- | --- |
| **Microsoft Entra ID** | [Tenants](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#tenants)[ユーザー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#users)[グループ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#groups)[ドメイン](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#domains)[ライセンス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#licenses)[サインイン ログ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#sign-in-logs)[監査ログ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#audit-logs)[プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#provisioning-logs)[推奨 事項](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#recommendations)[正常性監視アラート](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#health-monitoring-alerts)[サービス水準合意](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#service-level-agreement)[ロールと管理者](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#roles-and-administrators)[デバイス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#devices)[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#conditional-access)[認証](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios#authentication) | [Image: テナントデータ探索が有効][Image: ユーザーデータ探索が有効][Image: グループ データ探索が有効][Image: ドメイン データ探索が有効になっている][Image: ライセンス データ探索が有効になっている][Image: サインイン ログのデータ探索が有効になっている][Image: 監査ログのデータ探索が有効になっている][Image: プロビジョニング ログのデータ探索が有効になっている][Image: 推奨事項のデータ探索が有効になっている][Image: ヘルスモニタリングアラートのデータの探索が有効になっている][Image: サービス レベル アグリーメントのデータ探索が有効になっている][Image: ロールと管理者のデータ探索が有効になっている][Image: デバイスデータ探索が有効][Image: 条件付きアクセス のデータ探索が有効になっている][Image: 認証データ探索が有効] |
| **Microsoft Entra ID Protection** | [危険なユーザー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-protection-scenarios#risky-users)[アプリケーション リスク](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-protection-scenarios#application-risk) | [Image: リスクの高いユーザーのデータ探索が有効になっている][Image: アプリケーション リスク データ探索が有効になっている] |
| **Microsoft Entra ID ガバナンス** | [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios#access-reviews)[資格管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios#entitlement-management)[Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios#privileged-identity-management-pim)[PIM 書き込みアクション](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios#privileged-identity-management-pim-write-actions)[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios#lifecycle-workflows) | [Image: アクセス レビューのデータ探索が有効になっている][Image: エンタイトルメント管理データ探索が有効][Image: Privileged Identity Management (PIM) データ探索が有効になっている][Image: Privileged Identity Management (PIM) 書き込みアクションのデータ探索が有効になっている][Image: ライフサイクル ワークフローのデータ探索が有効] |
| **Microsoft Entra Internet Access** **Microsoft Entra プライベート アクセス** | [グローバル セキュリティで保護されたアクセス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-internet-access-private-access-scenarios#global-secure-access) | [Image: グローバルなセキュリティで保護されたアクセスのデータ探索が有効になっている] |

### Microsoft Entra ID のシナリオ

Microsoft Entra ID は Microsoft Entra の基本的な生産であり、ユーザー、デバイス、アプリ、リソースをセキュリティで保護するための重要な ID、認証、ポリシー、保護を提供します。 Security Copilot は、複数の領域にわたってこれらの機能を強化します。

- **エンタープライズ ユーザー管理**: ユーザー、グループ、ドメイン、ライセンスの情報をすばやく取得する
- **認証**: 有効な認証方法、登録状態、および全体的な認証戦略を検出する
- **ロールベースのアクセス制御 (RBAC):**ディレクトリ内のロールの割り当てを調査する
- **条件付きアクセス**: 条件付きアクセス ポリシーを理解して評価する
- **デバイス ID**: デバイスの詳細とコンプライアンスの状態を調べる

### Microsoft Entra ID Protection のシナリオ

Microsoft Entra ID Protection では、ID リスクの検出と修復に重点を置いています。 Security Copilot は、次の目的で AI を活用した分析情報を提供します。

- **危険なユーザー調査**: ユーザーのリスク レベルを要約し、修復に関する推奨事項を提供する
- **アプリケーション リスク評価**: ワークロード ID とアプリケーションのアクセス許可を分析する

### Microsoft Entra ID ガバナンス のシナリオ

Microsoft Entra ID ガバナンスは、ID ライフサイクルとアクセス ガバナンスを大規模に管理するのに役立ちます。 Security Copilot では、次の機能が強化されています。

- **アクセス レビュー**: アクセス レビューのデータと意思決定パターンを分析する
- **エンタイトルメント管理**: アクセス パッケージと接続された組織を管理する
- **Privileged Identity Management**: 特権アクセスとロールの割り当てを監視する
- **ライフサイクル ワークフロー**: 従業員のライフサイクル自動化の構成とトラブルシューティング
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/security-copilot-in-entra"} -->
## Microsoft Entra の Security Copilot

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/security-copilot-in-entra
- Service: entra
- Article date: 2026-04-23
- Summary: Microsoft Entra の Security Copilot を使用して、ID リスクを調査し、ID タスクのトラブルシューティングをすばやく行います。

[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/security-copilot/microsoft-security-copilot) は、AI の機能と人間の専門知識を組み合わせて、管理者とセキュリティ チームが攻撃に対して迅速かつ効果的に対応できるようにするプラットフォームです。 Microsoft Entra は、Security Copilot プラットフォームで正確な関連性のある情報を生成できるようにする Microsoft プラグインの 1 つです。 Security Copilot は、このプラグインを使用して、ID リスクの調査と解決、AI 駆動型インテリジェンスによる ID とアクセスの評価、複雑なタスクの迅速な完了を支援します。 Security Copilot は、Microsoft Entra ユーザー、グループ、サインイン ログ、監査ログなどの分析情報を取得すると同時に、セキュリティのベスト プラクティスにおけるコンテキスト化された分析情報と推奨事項を提供します。

サインインとリスクの高いユーザーの調査、インシデントの解決方法に関するコンテキスト化された分析情報の取得、自然言語を使用したアカウントの保護方法の学習を行うことができます。 Security Copilot は、リアルタイムの機械学習に基づいて構築されており、アクセス ポリシーのギャップを見つけ、ID ワークフローを生成し、迅速にトラブルシューティングを行うことができます。 Security Copilot は、Microsoft Entra 管理センターのさまざまな製品のシナリオ (Microsoft Entra ID 保護 の危険なユーザー レポートや Microsoft Entra サインイン ログを含むインシデント調査など) を支援できます。

### Security Copilotエクスペリエンス

Microsoft Entra の Security Copilot には、スタンドアロンの Security Copilot エクスペリエンス、Microsoft Entra 管理センターの埋め込みエクスペリエンス、タスクを実行できる特殊なエージェント、複数のエンド ツー エンドのシナリオで使用できる自然言語プロンプトが含まれます。

詳細については、以下を参照してください。

- [Microsoft Security Copilot エクスペリエンス](https://learn.microsoft.com/ja-jp/security-copilot/experiences-security-copilot)
- [Microsoft Security Copilot エージェント](https://learn.microsoft.com/ja-jp/security-copilot/agents-overview)
- [Microsoft Security Copilot のシナリオ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-scenarios)

### 概要

Security Copilot プラットフォームでは、Microsoft Entra は、組織の ID データと分析情報へのアクセスを提供するプラグインです。 Microsoft Entra で Security Copilot を使用するには、Security Copilot にオンボードし、Microsoft Entra プラグインを有効にする必要があります。

1. Microsoft Security Copilot の開始ガイドに従って、Security Copilot の使用を開始します。
    - このガイドには、サブスクリプションの要件、課金、容量に関するガイダンスが含まれています。
    - [Microsoft Security Copilot での認証について少し理解](https://learn.microsoft.com/ja-jp/security-copilot/authentication)してください。
2. Security Copilot スタンドアロン エクスペリエンスで、プロンプト バーから **[ソース** ] アイコンを選択し、Security Copilot 用の Microsoft Entra プラグインをオンにします (まだオンでない場合)。

Security Copilot ですべての設定が完了したら、 [自然言語プロンプト](https://learn.microsoft.com/ja-jp/security-copilot/prompting-security-copilot) の使用を開始して、ID ベースのインシデントの修復に役立ちます。 その他の例については、スタンドアロンの **Security Copilot** エクスペリエンスで [Promptbook ライブラリ](https://securitycopilot.microsoft.com/)をいつでも確認できます。

- "karita@woodgrovebank.com についてのユーザーの詳細をすべて表示し、ユーザー オブジェクト ID を抽出します。"
- "Microsoft Entra に登録されている karita@woodgrovebank.com のデバイスはありますか?"
- "karita@woodgrovebank.com の最近の危険なサインインを一覧表示します。"
- *"karita@woodgrovebank.com の過去 48 時間のサインイン ログを用意できますか? この情報をテーブル形式で配置します。"*
- *"karita@woodgrovebank.com の過去 72 時間の Microsoft Entra 監査ログを取得します。 情報をテーブル形式で配置します。"*

### Microsoft Entra 管理センター で Copilot チャットにアクセスする

Copilotチャットは、Microsoft Entra 管理センターの左側のナビゲーションから直接使用できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインします。
2. サイド ナビゲーション メニューから **Copilot chat** を選択します。

    [Image: Microsoft Entra 管理センターの Copilot チャット オプションのスクリーンショット。]
3. チャット プロンプト バーに質問またはタスクを入力するか、候補カードのいずれかを選択して作業を開始します。

**Entra Copilot** パネルが開き、次のオプションが表示されます。

- **新しいチャット**: Copilotとの新しい会話を開始します。
- **エージェント チャット**: 目的に応じたサポートを受けるには、**Identity Risk Management Agent** や **Conditional Access Optimization Agent** などの専用エージェントを選択します。
- **リセント セッション**: 以前のCopilot チャット セッションを表示および再開します。

Microsoft Entra Copilot のチャット エクスペリエンスは、ワークフローのどの段階でも自然に利用でき、必要なときにいつでも使えるように設計されています。 左側のチャット履歴ウィンドウを展開または折りたたみ、右上隅の [サイドカー モード] と [全画面表示モード] を切り替えることができます。

[Image: ビューを展開および折りたたむための Copilot チャットのボタンのスクリーンショット。]

さらに、Copilot チャット エクスペリエンスからMicrosoft Entra 管理センターを移動すると、サイドカーは新しいページで開いたままになり、中断することなくCopilotとの会話を続行できます。

### フィードバックを提供する

Copilot in Microsoft Entra は AI と機械学習を使用してデータを処理し、主要な機能ごとに応答を生成します。 ただし、AI によって生成されたコンテンツが正しくない可能性があります。 生成された応答に関する皆様からのフィードバックは、Copilot と Microsoft Entra の今後の精度向上に役立ちます。

すべての主要な機能にはフィードバックを提供するオプションがありますが、正確な手順は機能によって異なります。 サムズアップとサムズダウンのアイコンを確認して、応答が役に立ったかどうか教えてください。

[Image: フィードバックを提供するための Copilot の「いいね」と「よくない」のアイコンのスクリーンショット。]

### Security Copilot のプライバシーとデータのセキュリティ

Security Copilot がプロンプトとサービスから取得したデータ (プロンプト出力) を処理する方法については、 [Microsoft Security Copilot のプライバシーとデータ セキュリティに](https://learn.microsoft.com/ja-jp/security-copilot/privacy-data-security)関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/security-copilot/security-store-in-entra"} -->
## Microsoft Entra でエージェントとソリューションを検出して展開する

- Source: https://learn.microsoft.com/ja-jp/entra/security-copilot/security-store-in-entra
- Service: entra
- Article date: 2026-03-17
- Summary: Microsoft Entra の Security Store を使用して、ID とアクセス管理のための AI エージェントとセキュリティ ソリューションを検出、購入、デプロイする方法について説明します。

Microsoft Entra 管理センターの [Security Store](https://learn.microsoft.com/ja-jp/security/store/what-is-security-store) には、ID 管理タスクとアクセス管理タスクを効率的に実行するのに役立つエージェントと統合ソリューションが用意されています。 これらのオファリングには、Microsoft によって公開された [Microsoft Security Copilot エージェント](https://learn.microsoft.com/ja-jp/copilot/security/agents-overview) と承認されたパートナーが含まれます。 Security Store には、Microsoft Entra 製品と統合され、高度な不正行為防止などの機能を ID ワークフローに直接提供できる非エージェント ソリューションも用意されています。

この記事では、Microsoft Entra で AI エージェントとソリューションを検出して展開する方法について説明します。

注

Security Store へのエージェントの発行の詳細については、「エージェントを [Microsoft Security Store に発行する](https://learn.microsoft.com/ja-jp/security/store/publish-a-security-copilot-agent-or-analytics-solution-in-security-store)」を参照してください。

### 前提条件

Security Store からエージェントとソリューションを購入して展開するには、次のものが必要です。

- [SCU 容量でプロビジョニングされたSecurity Copilot ワークスペースへのアクセス](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot)。
- パートナーが発行したエージェントとソリューションの場合は、 [Azure サブスクリプションの共同作成者または所有者ロールが](https://learn.microsoft.com/ja-jp/marketplace/roles-permissions)必要です。

### セキュリティ ストア内のエージェント

セキュリティ ストアには、ID およびアクセス管理タスクを自動化する Microsoft Entra エージェントが含まれています。 使用可能なエージェント、その機能、必要なアクセス許可、セットアップ手順の完全な一覧については、 [Microsoft Entra エージェント](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-agents)を参照してください。

### セキュリティ ストアのソリューション

次のソリューションは、Microsoft Entra 管理センターのセキュリティ ストアから入手できます。

- **検証済み ID ソリューション**: セキュリティ ストアで利用できる [Microsoft Entra 検証済み ID](https://learn.microsoft.com/ja-jp/entra/verified-id/) ソリューションは、高度な不正行為防止機能を提供します。 これらのソリューションは、ID 検証ワークフローに直接統合されるため、資格情報の発行と検証のシナリオに対する信頼とセキュリティが強化されます。
- **外部 ID ソリューション**: Security Store で利用できる [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/) ソリューションは、組織のエッジ保護を強化するために WAF とボットの防御を提供します。 これらのソリューションは、顧客向けのアプリケーションをセキュリティで保護し、外部 ID インフラストラクチャを対象とする自動化された脅威から保護するのに役立ちます。

### Microsoft Entra 管理センターでエージェントとソリューションを検出して展開する

Security Store は Microsoft Entra 管理センターに埋め込まれているため、ID 管理ワークフローを離れることなく、エージェントとソリューションを検出して展開できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **セキュリティ ストア**を参照します。
3. デプロイするエージェントまたはソリューションを参照または検索します。

    - **[すべて**] タブと [**エージェント]** タブを使用して結果をフィルター処理します。
4. エージェントまたはソリューションを選択して、機能、要件、セットアップ手順などの詳細を表示します。
5. エージェントまたはソリューションを購入してデプロイするには:

    - 十分なアクセス許可がある場合は、[ **エージェントの取得** ] または [ **ソリューションの取得** ] を選択してデプロイ プロセスを開始します。
    - エージェントまたはソリューションを展開するアクセス許可がない場合は、[ **コピー] リンク** を選択して詳細ページの URL をコピーし、セキュリティ管理者と共有します。
    - パートナーが発行したエージェントの場合は、[Microsoft Security Store のドキュメント](https://securitystore.microsoft.com/)で説明されているように、[Security Store Web サイト](https://learn.microsoft.com/ja-jp/security/store/get-agents-in-security-store)で購入と展開を完了します。

    ヒント

    [SaaS ソリューションを購入する方法 (プライベート オファー)](https://learn.microsoft.com/ja-jp/security/store/how-to-purchase-saas-solutions-private-offers) の説明に従って、パブリック オファーまたはプライベート オファーを通じて、パートナーが発行したエージェントの一元的な購入を管理できます。
6. エージェントを購入した後、 **Security Copilot**&gt;**Agents** を選択し、[ **セットアップの準備完了** ] セクションでエージェントを見つけて、[ **セットアップ** ] を選択してエージェントのセットアップを開始します。

セットアップ後、エージェントは [使用中の **エージェント] セクションに** 表示されます。

### エージェントの管理と削除

Security Store からデプロイされたエージェントは、 [Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/copilot/security/microsoft-security-copilot) を通じて管理されます。

- エージェント設定を管理するには、 **Security Copilot**&gt;**Agents** に移動し、構成するエージェントを選択します。
- エージェントを削除するには、Security Copilot で削除を実行します。 Security Copilot でエージェントを削除すると、エンタイトルメント チェックと課金が停止します。
- パートナーが公開したエージェントについては、 **Security Store**&gt;**Management**&gt;**My Solutions** で課金と権利の詳細を確認します。

詳細については、「[セキュリティ コパイロット エージェントの管理](https://learn.microsoft.com/ja-jp/copilot/security/agents-manage)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards"} -->
## Microsoft Entra ID を使用して ID 標準を実装する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards
- Service: entra / standards
- Article date: 2023-06-28
- Summary: Azure と Microsoft Entra ID では、コンプライアンス認定が提供されます。 政府と業界の標準を満たすために環境を構成する方法について説明します。

Azure と Microsoft Entra ID では、コンプライアンス認定が提供されます。 政府と業界の標準を満たすために環境を構成する方法について説明します。

### 標準の概要

#### 概要

- [NIST AAL の概要](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview)
- [FedRAMP High インパクトの概要](https://learn.microsoft.com/ja-jp/entra/standards/configure-for-fedramp-high-impact)
- [覚書 22-09 要件の Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-meet-identity-requirements)
- [Microsoft Entra IDをCMMC準拠で構成します。](https://learn.microsoft.com/ja-jp/entra/standards/configure-for-cmmc-compliance)
- [HIPAA コンプライアンスの Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/hipaa-configure-for-compliance)
- [HITRUST コンプライアンス用に Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/hipaa-hitrust-controls)
- [PCI-DSS コンプライアンスの Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/pci-dss-guidance)

### NIST AAL について

#### 概要

- [Authenticator の保証レベル](https://learn.microsoft.com/ja-jp/entra/standards/nist-about-authenticator-assurance-levels)
- [Authenticator の基本](https://learn.microsoft.com/ja-jp/entra/standards/nist-authentication-basics)
- [型とメソッド](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-types)

### NIST AAL を構成する

#### 攻略ガイド

- [NIST AAL 1 の構成](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-1)
- [NIST AAL 2 の構成](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-2)
- [NIST AAL 3 の構成](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-3)

### FedRAMP コントロールを構成する

#### 攻略ガイド

- [アクセス制御の構成](https://learn.microsoft.com/ja-jp/entra/standards/fedramp-access-controls)
- [ID および認証のコントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/fedramp-identification-and-authentication-controls)
- [追加のコントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/fedramp-other-controls)

### 覚書 22-09 の ID に関する要件を満たす

#### 攻略ガイド

- [企業全体にわたる ID 管理システム](https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-enterprise-wide-identity-management-system)
- [多要素認証の要件を満たす](https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-multi-factor-authentication)
- [認可要件を満たす](https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-authorization)
- [ゼロ トラストのその他の領域](https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-other-areas-zero-trust)

### Microsoft Entra ID と CMMC コンプライアンス

#### 攻略ガイド

- [Microsoft Entra IDをCMMC準拠で構成します。](https://learn.microsoft.com/ja-jp/entra/standards/configure-for-cmmc-compliance)
- [CMMC レベル 1 コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-1-controls)
- [CMMC レベル 2 Access Control コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-access-control)
- [CMMC レベル 2 の識別と認証制御を構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-identification-and-authentication)
- [CMMC レベル 2 を満たすために Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-additional-controls)

### Microsoft Entra ID と HIPAA コンプライアンス

#### 攻略ガイド

- [アクセス制御のセーフガードを構成する](https://learn.microsoft.com/ja-jp/entra/standards/hipaa-access-controls)
- [監査制御のセーフガードを構成する](https://learn.microsoft.com/ja-jp/entra/standards/hipaa-audit-controls)
- [その他のセーフガードを構成する](https://learn.microsoft.com/ja-jp/entra/standards/hipaa-other-controls)

### Microsoft Entra ID と HITRUST コンプライアンス

#### 攻略ガイド

- [HITRUST コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/hipaa-hitrust-controls)

### Microsoft Entra と DORA の行為

#### 攻略ガイド

- [DORA の要件に合わせて Microsoft Entra を構成する](https://learn.microsoft.com/compliance/dora/dora-entra)

### Microsoft Entra ID と PCI-DSS コンプライアンス

#### 攻略ガイド

- [PCI-DSS 要件 1 を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-1)
- [PCI-DSS 要件 2 を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-2)
- [PCI-DSS 要件 5 を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-5)
- [PCI-DSS 要件 6 を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-6)
- [PCI-DSS 要件 7 を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-7)
- [PCI-DSS 要件 8 を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-8)
- [PCI-DSS 要件 10 を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-10)
- [PCI-DSS 要件 11 を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-11)
- [Microsoft Entra の PCI-DSS MFA 補足要件を満たす](https://learn.microsoft.com/ja-jp/entra/standards/pci-dss-mfa)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/configure-cmmc-level-1-controls"} -->
## CMMC レベル 1 コントロールを構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-1-controls
- Service: entra / standards
- Article date: 2023-01-03
- Summary: CMMC レベル 1 の要件を満たすように Microsoft Entra ID を構成する方法について説明します。

Microsoft Entra ID では、各 Cybersecurity Maturity Model Certification (CMMC) レベルで ID 関連のプラクティス要件を満たします。 CMMC の要件に準拠するために、米国国防総省 (DoD) と協力して、および代理として、他の構成またはプロセスを実行することは、企業の責任です。 CMMC レベル 1 には、ID に関連する 1 つまたは複数のプラクティスを含む 3 つのドメインがあります。

- アクセスの制御 (AC)
- 識別と認証 (IA)
- システムと情報の整合性 (SI)

詳細情報:

- DoD CMMC Web サイト - [サイバーセキュリティ成熟度モデル認定の概要](https://dodcio.defense.gov/CMMC/about/)
- Microsoft ダウンロード センター - [CMMC レベル 3 用の Microsoft Product Placemat (プレビュー)](https://www.microsoft.com/download/details.aspx?id=102536)

このコンテンツの残りの部分は、ドメインおよび関連するプラクティス別に整理されています。 ドメインごとに、プラクティスを実行するためのステップバイステップのガイダンスを提供するコンテンツへのリンクを含む表があります。

### アクセス制御ドメイン

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| AC.L1-3.1.1**実施規定:** 情報システムへのアクセスを、許可されているユーザー、許可されているユーザーの代わりに動作するプロセス、またはデバイス (他の情報システムを含む) に制限する。**目標:**かどうかを判断します：[a.] 許可されているユーザーが識別されている。[b.] 許可されているユーザーの代わりに動作するプロセスが識別されている。[c.] システムへの接続が許可されているデバイス (およびその他のシステム) が識別されている。[d.] システム アクセスが、許可されているユーザーに限定されている。[例] システム アクセスが、許可されているユーザーの代わりに動作するプロセスに限定されている。[f.] システム アクセスが、許可されたデバイス (他のシステムを含む) に限定されている。 | あなたは、外部の人事システム、オンプレミスの Active Directory、または直接クラウドで実行することで設定を行う Microsoft Entra アカウントの設定を担当しています。 既知の (登録済みまたはマネージド) デバイスからのアクセスのみを許可するように、条件付きアクセスを構成します。 さらに、アプリケーションのアクセス許可を付与するときは、最小特権の概念を適用します。 可能な場合は、委任されたアクセス許可を使用します。 ユーザーを設定する- [Microsoft Entra のユーザー プロビジョニングにクラウド HR アプリケーションを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)<br>- [Microsoft Entra Connect 同期: 同期を理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)<br>- [ユーザーの追加または削除 - Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)デバイスのセットアップ<br>- [Microsoft Entra ID のデバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)アプリケーションの構成<br>- [クイック スタート: Microsoft ID プラットフォームでアプリを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)<br>- [Microsoft ID プラットフォームのスコープ、アクセス許可、および同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)<br>- [Microsoft Entra ID でのサービス プリンシパルのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-principal)条件付きアクセス<br>- [Microsoft Entra ID の条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)<br>- [条件付きアクセスにはマネージド デバイスが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) |
| アクセスコントロール。L1-3.1.2**実施規定:** 情報システムへのアクセスを、許可されているユーザーが実行を許可されているトランザクションおよび機能の種類に制限する。**目標:**かどうかを判断します：[a.] 許可されているユーザーに実行が許可されているトランザクションおよび機能の種類が定義されている[b.] システム アクセスが、許可されているユーザーに定義されたトランザクションと機能の種類に制限されている。 | お客様は組み込みまたはカスタムのロールを使用したロールベースのアクセス制御 (RBAC) などのアクセス制御を構成する責任があります。 ロールを割り当て可能なグループを使用して、同じアクセス権を必要とする複数のユーザーに対するロールの割り当てを管理します。 既定またはカスタムのセキュリティ属性を使用して属性ベースのアクセス制御 (ABAC) を構成します。 目的は Microsoft Entra ID で保護されているリソースへのアクセスをきめ細かく制御することです。RBAC を設定する- [Active Directory のロールベースのアクセス制御の概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)[Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)<br>- [Microsoft Entra ID でカスタム ロールを作成する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)ABAC を設定する<br>- [Azure の属性ベースのアクセス制御 (Azure ABAC) とは](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview)<br>- [Microsoft Entra ID のカスタム セキュリティ属性とは](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)ロールの割り当てのためにグループを構成する<br>- [Microsoft Entra グループを使用してロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept) |
| AC.L1-3.1.20**実施規定:** 外部情報システムへの接続と使用を検証し、制御および制限する。**目標:**かどうかを判断します：[a.] 外部システムへの接続が識別されている。[b.] 外部システムの使用が識別されている。[c.] 外部システムへの接続が検証されている。[d.] 外部システムの使用が検証されている。[例] 外部システムへの接続が制御または制限されている。[f.] 外部システムの使用が制御または制限されている。 | お客様は外部システムの接続と使用を制御または制限するために、デバイス制御やネットワークの場所を使用して条件付きアクセス ポリシーを構成する責任があります。 外部システムへのアクセスに利用される契約条件についてユーザーが確認した記録を設定するために、使用条件 (TOU) を構成します。必要に応じて条件付きアクセスを設定する- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)<br>- [条件付きアクセスを使用してクラウド アプリへのアクセスにマネージド デバイスを要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)<br>- [デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)<br>- [条件付きアクセス: デバイスのフィルター](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)条件付きアクセスを使用してアクセスをブロックする<br>- [条件付きアクセス - 場所ごとにアクセスをブロックする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-by-location)使用条件を構成する<br>- [使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)<br>- [条件付きアクセスには利用規約が必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-require-terms-of-use) |
| AC.L1-3.1.22**実施規定:** 公的にアクセス可能な情報システムで掲載または処理される情報を制御する。**目標:**かどうかを判断します：[a.] 公的にアクセス可能なシステムで、情報を投稿または処理する権限を持つ個人が識別されている。[b.] 公的にアクセス可能なシステムに FCI が投稿または処理されないようにするための手順が識別されている。[c.] 公的にアクセス可能なシステムにコンテンツが投稿される前に、レビュー プロセスが用意されている。[d.] パブリックにアクセス可能なシステム上のコンテンツは、連邦契約情報 (FCI) が含まれていないようにするためにレビューされている。 | お客様は Privileged Identity Management (PIM) を構成してシステムへのアクセスを管理する責任があります。ここでは、投稿された情報はパブリックにアクセス可能であるとします。 PIM でロールを割り当てる前に、正当な理由での承認を要求してください。 システムに対して、公開情報の投稿に関する使用条件 (TOU) を設定します。ここでの投稿情報は、公開アクセス可能です。また、契約条件の承認が記録されることを条件とします。PIM のデプロイを計画する- [Privileged Identity Management とは?](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)<br>- [Privileged Identity Management のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-deployment-plan)使用条件を構成する<br>- [使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)<br>- [条件付きアクセスには利用規約が必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-require-terms-of-use)<br>- [PIM で Microsoft Entra ロールの設定を構成する - 正当な理由を必須にする](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings) |

### 識別と認証 (IA) ドメイン

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| IA・L1-3.5.1**実施規定:** 情報システム ユーザー、ユーザーの代わりに動作するプロセス、またはデバイスを識別する。**目標:**かどうかを判断します：[a.] システム ユーザーが識別されている。[b.] ユーザーの代わりに動作するプロセスが識別されている。[c.] システムにアクセスするデバイスが識別されている。 | Microsoft Entra ID は、それぞれのディレクトリ オブジェクトの ID プロパティを使用して、ユーザー、プロセス (サービス プリンシパル/ワークロード ID)、およびデバイスを一意に識別します。 次のリンクを使用して、評価に役立つログ ファイルをフィルター処理できます。 評価の目標を達成するには、次のリファレンスを使用してください。ユーザー プロパティによるログのフィルター処理- [ユーザー リソースの種類: ID プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/user?view=graph-rest-1.0&preserve-view=true)サービス プロパティによるログのフィルター処理<br>- [ServicePrincipal リソースの種類: ID プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal?view=graph-rest-1.0&preserve-view=true)デバイスのプロパティによるログのフィルター処理<br>- [デバイス リソースの種類: ID プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/device?view=graph-rest-1.0&preserve-view=true) |
| IA.L1-3.5.2**実施規定:** 組織の情報システムへのアクセスを許可する際の前提条件として、ユーザー、プロセス、またはデバイスの ID を認証 (または検証) する。**目標:**かどうかを判断します：[a.] 各ユーザーの ID は、システム アクセスの前提条件として認証または検証されている。[b.] ユーザーの代わりに動作する各プロセスの ID は、システム アクセスの前提条件として認証または検証されている。[c.] システムにアクセスまたは接続する各デバイスの ID は、システム アクセスの前提条件として認証または検証されている。 | Microsoft Entra ID では、システム アクセスの前提条件として、各ユーザー、ユーザーに代わって動作するプロセス、またはデバイスを一意に認証または検証します。 評価の目標を達成するには、次のリファレンスを使用してください。ユーザー アカウントをセットアップする- [Microsoft Entra 認証とは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)[NIST 認証システムの保証レベルを満たすように Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview)サービス プリンシパル アカウントを設定する<br>- [サービス プリンシパルの認証](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-principal)デバイス アカウントを設定する<br>- [デバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)<br>- [動作のしくみ: デバイス登録](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-registration-how-it-works)<br>- [プライマリ更新トークンとは](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)<br>- PRT に含まれる内容 |

### システムと情報の整合性 (SI) ドメイン

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| SI.L1-3.14.1 - 情報および情報システムの不備をタイムリーに特定、報告し、修正する。SI.L1-3.14.2 - 組織の情報システム内の適切な場所で、悪意のあるコードからの保護を提供する。SI.L1-3.14.4 - 新しいリリースが利用可能になったときに、悪意のあるコードからの保護メカニズムを更新する。SI.L1-3.14.5 - 情報システムの定期的なスキャンを実行し、外部ソースからのファイルがダウンロードされたとき、開かれたとき、または実行されたときに、そのファイルのリアルタイム スキャンを実行する。 | **レガシ マネージド デバイス用の統合ガイダンス**Microsoft Entra ハイブリッド参加済みデバイスを要求するように条件付きアクセスを構成します。 オンプレミス AD に参加しているデバイスの場合、これらのデバイスに対する統制は、Configuration Manager などの管理ソリューションやグループ ポリシー (GP) を使用して適用されると想定されます。 これらの方法のいずれかがデバイスに適用されているかどうかを Microsoft Entra ID で判断する方法がないため、Microsoft Entra ハイブリッド参加済みデバイスを要求することは、マネージド デバイスを要求するための比較的弱いメカニズムです。 そのデバイスが Microsoft Entra ハイブリッド参加済みデバイスでもある場合は、管理者はオンプレミスのドメイン参加済みデバイスに適用されている方法がマネージド デバイスを構成するのに十分に強いかどうかを判断します。**クラウド管理 (または共同管理) デバイス用の統合ガイダンス**デバイスが準拠しているとマークされることを要求するように条件付きアクセスを構成します。これはマネージド デバイスを要求するための最も厳密な形式です。 このオプションでは、Microsoft Entra ID へのデバイス登録が必要で、Microsoft Entra 統合を介して Windows 10 デバイスを管理する Intune またはサードパーティ製モバイル デバイス管理 (MDM) システムによって準拠として指定する必要があります。 |

#### 次のステップ

- [CMMC コンプライアンス用に Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-for-cmmc-compliance)
- [CMMC レベル 2 Access Control (AC) コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-access-control)
- [CMMC レベル 2 の識別と認証 (IA) コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-identification-and-authentication)
- [CMMC レベル 2 の追加コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-additional-controls)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/configure-cmmc-level-2-access-control"} -->
## CMMC レベル 2 アクセス制御 (AC) コントロールを構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-access-control
- Service: entra / standards
- Article date: 2023-01-03
- Summary: CMMC レベル 2 アクセス制御 (AC) の要件を満たすように Microsoft Entra ID を構成する方法についてご確認ください。

Microsoft Entra ID は、各 Cybersecurity Maturity Model Certification (CMMC) レベルで ID 関連のプラクティス要件を満たすために役立てることができます。 [CMMC V2.0 レベル 2](https://dodcio.defense.gov/CMMC/Model/) の要件に準拠するために、米国国防総省 (DoD) と協力して、および代理として、他の構成やプロセスを実行することは、企業の責任です。

CMMC レベル 2 には、ID に関連する 1 つ以上のプラクティスを含む 13 のドメインがあります。

- アクセスの制御 (AC)
- 監査とアカウンタビリティ (AU)
- 構成管理 (CM)
- 識別と認証 (IA)
- インシデント対応 (IR)
- メンテナンス (MA)
- メディア保護 (MP)
- 人的セキュリティ (PS)
- 物理的保護 (PE)
- リスク評価 (RA)
- セキュリティ評価 (CA)
- システムと通信の保護 (SC)
- システムと情報の整合性 (SI)

この記事の残りの部分では、Access Control (AC) ドメインに関するガイダンスを提供します。 プラクティスを実行するためのステップバイステップのガイダンスを提供するコンテンツへのリンクを含む表があります。

### アクセスの制御 (AC)

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| AC.L2-3.1.3**実施規定:** 承認された認可に従って CUI のフローを制御する。**目標:**かどうかを判断します：[a.] 情報フロー制御ポリシーが定義されている。[b.] CUI のフローを制御するための方法と実施メカニズムが定義されている。[c.] システム内および相互接続されたシステム間で CUI に対して指定された送信元と宛先 (ネットワーク、個人、デバイスなど) が識別されます。[d.] CUI のフローを制御するための認可が定義されている。および[例] CUI のフローを制御するための承認された認可が適用されている。 | 条件付きアクセス ポリシーを構成して、信頼できる場所、信頼済みデバイス、承認されたアプリケーションからの CUI のフローを制御し、アプリ保護ポリシーを要求します。 CUI に対するきめ細かい承認を行うために、アプリによって適用される制限 (Exchange や SharePoint Online)、アプリ コントロール (Microsoft Defender for Cloud Apps)、認証コンテキストを構成します。 Microsoft Entra アプリケーション プロキシをデプロイして、オンプレミス アプリケーションへのアクセスをセキュリティで保護します。[Microsoft Entra 条件付きアクセスの場所の条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)[条件付きアクセス ポリシーの許可コントロール - デバイスは準拠としてマーク済みであることが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス ポリシーの許可コントロール - Microsoft Entra ハイブリッド参加済みデバイスが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス ポリシーの許可コントロール - 承認済みクライアント アプリが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス ポリシーの許可コントロール - アプリの保護ポリシーが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス ポリシーのセッション制御 - アプリケーションによって適用される制限](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)[Microsoft Defender for Cloud Apps のアプリの条件付きアクセス制御での保護](https://learn.microsoft.com/ja-jp/defender-cloud-apps/proxy-intro-aad)[条件付きアクセス ポリシーでのクラウド アプリ、アクション、認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)[Microsoft Entra アプリケーション プロキシを使用したオンプレミス アプリへのリモート アクセス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)**認証コンテキスト**[認証コンテキストの構成と条件付きアクセス ポリシーへの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)**情報の保護**データを知り、保護します。データ損失の防止に役立ちます。[Microsoft Purview を使用して機密データを保護する](https://learn.microsoft.com/ja-jp/purview/information-protection?view=o365-worldwide&preserve-view=true)**条件付きアクセス**[Azure Information Protection (AIP) の条件付きアクセス](https://techcommunity.microsoft.com/t5/security-compliance-and-identity/conditional-access-policies-for-azure-information-protection/ba-p/250357)**アプリケーション プロキシ**[Microsoft Entra アプリケーション プロキシを使用したオンプレミス アプリへのリモート アクセス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy) |
| AC.L2-3.1.4**実施規定:** 個人の職務を分離し、共謀させないようにして悪意のあるアクティビティのリスクを軽減する。**目標:**かどうかを判断します：[a.] 分離が必要な個人の職務が定義されている。[b.] 分離が必要な職責が、各個人に割り当てられている。および[c.] 分離が必要な職務を遂行するためのアクセス特権が、各個人に付与されている。 | 適切なアクセスのスコープを設定して、職務の適切な分離を確保します。 アプリケーション、グループ、Teams、SharePoint サイトへのアクセスを管理するエンタイトルメント管理アクセス パッケージを構成します。 ユーザーが過剰なアクセス権を得ないように、アクセス パッケージ内で職務の分離チェックを構成します。 Microsoft Entra エンタイトルメント管理では、アクセス パッケージを使用して、アクセスする必要があるユーザー コミュニティごとに異なる設定を指定して複数のポリシーを構成できます。 この構成には、特定のグループのユーザー、または既に別のアクセス パッケージが割り当てられているユーザーに、ポリシーによって他のアクセス パッケージが割り当てられないような制限が含まれます。Microsoft Entra ID で管理単位を構成して管理特権のスコープを設定し、特権ロールを持つ管理者が、限定されたディレクトリ オブジェクト (ユーザー、グループ、デバイス) のセットに対してのみそれらの特権を持つようにします。[エンタイトルメント管理とは](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)[アクセスパッケージとは何ですか? また、それを使ってどのようなリソースを管理できますか?](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)[Microsoft Entra エンタイトルメント管理でアクセス パッケージに対する職務の分離を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible)[Microsoft Entra ID の管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units) |
| AC.L2-3.1.5**実施規定:** 特定のセキュリティ機能や特権アカウントなど、最小限の特権の原則を採用する。**目標:**かどうかを判断します：[a.] 特権アカウントが識別される。[b.] 特権アカウントへのアクセスが、最小限の特権の原則に従って承認される。[c.] セキュリティ機能が特定される。[d.] セキュリティ機能へのアクセスが、最小限の特権の原則に従って承認される。 | 最小限の特権の規則を実装して適用する責任は、お客様にあります。 このアクションは、適用、監視、アラートを構成するための Privileged Identity Management を使用して実行できます。 ロール メンバーシップの要件と条件を設定します。特権アカウントを特定して管理の対象としたら、[エンタイトルメント ライフサイクル管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)と[アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)を使用して、適切なアクセスを設定、維持、監査します。 [MS Graph API](https://learn.microsoft.com/ja-jp/graph/api/directoryrole-list-members?view=graph-rest-1.0&tabs=http&preserve-view=true) を使用して、ディレクトリ ロールを検出および監視します。**ロールの割り当て**[PIM で Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user)[Privileged Identity Management で Azure リソース ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles)[グループの PIM に有資格の所有者およびメンバーを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-assign-member-owner)**ロールの設定を行う**[PIM で Microsoft Entra ロールの設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)[PIM で Azure リソース ロールの設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-role-settings)[PIM でグループ用 PIM の設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-role-settings)**アラートを設定する**[PIM の Microsoft Entra ロールのセキュリティ アラート](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts)[Privileged Identity Management で Azure リソース ロールに対するセキュリティ アラートを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-alerts) |
| アクセスコントロール。L2-3.1.6**実施規定:** 非セキュリティ機能にアクセスする際に非特権アカウントまたはロールを使用する。**目標:**かどうかを判断します：[a.] 非セキュリティ機能が特定される。および [b.] ユーザーは、非セキュリティ機能にアクセスする際に非特権アカウントまたはロールを使用する必要がある。AC.L2-3.1.7**実施規定: **非特権ユーザーが特権のある機能を実行できないようにし、そのような機能の実行を監査ログに記録する。**目標:**かどうかを判断します：[a.] 特権機能が定義されている。[b.] 非特権ユーザーが定義されている。[c.] 非特権ユーザーが特権機能を実行できない。および[d.] 特権機能の実行が監査ログに記録される。 | AC.L2-3.1.6 と AC.L2-3.1.7 の要件は、相互に補完されます。 特権のある使用と特権のない使用のために、分離されたアカウントが必要です。 Just-In-Time (JIT) 特権アクセスを採用し、継続的なアクセスを削除するように Privileged Identity Management (PIM) を構成します。 特権ユーザーの生産性アプリケーションへのアクセスを制限するように、ロールベースの条件付きアクセス ポリシーを構成します。 高い特権を持つユーザーについては、特権アクセス ストーリーの一部としてデバイスをセキュリティで保護します。 すべての特権アクションは、Microsoft Entra 監査ログで取得されます。[特権アクセスのセキュリティ保護の概要](https://learn.microsoft.com/ja-jp/security/compass/overview)[PIM で Microsoft Entra ロールの設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)[条件付きアクセス ポリシーのユーザーとグループ](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups)[特権アクセス デバイスが重要な理由](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-devices) |
| AC.L2-3.1.8**実施規定:** サインオンの失敗を制限する。**目標:**かどうかを判断します：[a.] サインオンの失敗を制限する手段が定義されている。および[b.] サインオンの失敗を制限する定義済みの手段が実装されている。 | スマート ロックアウトのカスタム設定を有効にします。 これらの要件を実装するために、ロックアウトのしきい値とロックアウト期間 (秒単位) を構成します。[Microsoft Entra スマート ロックアウトを使用してユーザー アカウントを攻撃から保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)[Microsoft Entra スマート ロックアウト値の管理](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout) |
| AC.L2-3.1.9**実施規定:** 適用される CUI 規則に則って、プライバシーおよびセキュリティの通知を提供する。**目標:**かどうかを判断します：[a.] CUI で指定された規則に必要なプライバシーとセキュリティの通知が、特定の CUI カテゴリと識別され、一貫性があり、関連付けられている。[b.] プライバシーとセキュリティの通知が表示される。 | Microsoft Entra ID を使用すると、アクセス権を付与する前に、受信確認を要求して記録するすべてのアプリに対して通知メッセージやバナー メッセージを配信することができます。 これらの利用規約ポリシーは、特定のユーザー (メンバーまたはゲスト) を対象とするように細かく設定できます。 また、条件付きアクセス ポリシーを使用して、アプリケーションごとにカスタマイズすることもできます。**条件付きアクセス**[Microsoft Entra ID の条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)**使用条件**[Microsoft Entra の使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)[同意したユーザーと拒否したユーザーのレポートの表示](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use) |
| アクセスコントロールL2-3.1.10**実施規定:** 非アクティブな期間の経過後のデータのアクセスおよび閲覧を防止するために、パターン非表示ディスプレイによるセッション ロックを使用する。**目標:**かどうかを判断します：[a.] システムでセッション ロックが開始されるまでの非アクティブ期間が定義されている。[b.] 定義された非アクティブ期間経過後にセッション ロックがかかり、システムへのアクセスやデータの閲覧ができなくなる。および[c.] 定義された非アクティブ期間経過後、それまで表示されていた情報がパターン非表示ディスプレイにより非表示になる。 | 準拠しているデバイスまたは Microsoft Entra ハイブリッド参加済みデバイスへのアクセスを制限するために、条件付きアクセス ポリシーを使用してデバイスのロックを実装します。 Intune などの MDM ソリューションを使用して OS レベルでデバイスのロックを強制するように、デバイスのポリシー設定を構成します。 Microsoft Intune、Configuration Manager、またはグループ ポリシー オブジェクトは、ハイブリッド デプロイで検討することもできます。 アンマネージド デバイスの場合は、ユーザーに再認証を強制するように、[サインインの頻度] 設定を構成します。[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス ポリシーの許可コントロール - Microsoft Entra ハイブリッド参加済みデバイスが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[ユーザー サインインの頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)非アクティブな状態がどれだけ続いたら (分単位) 画面がロックされるかをデバイスに構成します ([Android](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-android)、[iOS](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-ios)、[Windows 10](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-windows-10))。 |
| 交流。L2-3.1.11**実施規定:** 定義された条件が成立した場合にユーザー セッションを (自動的に) 終了する。**目標:**かどうかを判断します：[a.] ユーザー セッションの終了を要求する条件が定義されている。および[b.] 定義された条件のいずれかが発生すると、ユーザー セッションが自動的に終了する。 | サポートされているすべてのアプリケーションに対して継続的アクセス評価 (CAE) を有効にします。 CAE がアプリケーションでサポートされていない場合、または条件に CAE を適用できない場合は、条件が発生したときにセッションを自動的に終了するポリシーを Microsoft Defender for Cloud Apps で実装します。 さらに、ユーザーとサインインのリスクを評価するために Microsoft Entra ID 保護を構成します。 ユーザーがリスクを自動的に修復できるようにするには、条件付きアクセスを ID 保護とともに使用します。[Microsoft Entra ID での継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)[ポリシーを作成してクラウド アプリの使用を制御する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/control-cloud-apps-with-policies)[Microsoft Entra ID Protection とは](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) |
| AC。L2-3.1.12**実施規定:** リモート アクセス セッションの監視および制御を行う。**目標:**かどうかを判断します：[a.] リモート アクセス セッションが許可される。[b.] 許可されたリモート アクセスの種類が識別される。[c.] リモート アクセス セッションが制御される。および[d.] リモート アクセス セッションが監視される。 | 現在の世界では、ユーザーは不明または信頼されていないネットワークからクラウドベースのアプリケーションに、ほぼ例外なくリモートでアクセスします。 ゼロトラスト プリンシパルを採用するには、このアクセス パターンをセキュリティで保護することが重要です。 最新のクラウド環境でこれらのコントロール要件を満たすには、各アクセス要求を明示的に確認し、最小限の特権を実装し、侵害を想定する必要があります。内部と外部のネットワークを示す名前付きの場所を構成します。 Microsoft Defender for Cloud Apps 経由でアクセスをルーティングするように、条件付きアクセス アプリ コントロールを構成します。 すべてのセッションのコントロールとモニターを行うように、Defender for Cloud Apps を構成します。[Microsoft Entra ID のゼロ トラスト展開ガイド](https://www.microsoft.com/security/blog/2020/04/30/zero-trust-deployment-guide-azure-active-directory/)[Microsoft Entra 条件付きアクセスの場所の条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)[Microsoft Entra アプリに対して Cloud App Security アプリの条件付きアクセス制御を展開する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/proxy-deployment-aad)[Microsoft Defender for Cloud Apps とは](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps)[Microsoft Defender for Cloud Apps で発生したアラートを監視する](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/investigate-alerts) |
| 交流。L2-3.1.13**実施規定:** リモート アクセス セッションの機密性を保護するための暗号化メカニズムを採用する。**目標:**かどうかを判断します：[a.] リモート アクセス セッションの機密性を保護するための暗号化メカニズムが特定される。および[b.] リモート アクセス セッションの機密性を保護するための暗号化メカニズムが実装されている。 | すべての Microsoft Entra 顧客向け Web サービスは、トランスポート層セキュリティ (TLS) プロトコルで保護され、FIPS 検証済みの暗号化を使用して実装されます。[Microsoft Entra データ セキュリティに関する考慮事項 (microsoft.com)](https://azure.microsoft.com/resources/azure-active-directory-data-security-considerations/) |
| アクセスコントロール L2-3.1.14**実施規定:** 管理対象のアクセス制御ポイントを介してリモート アクセスをルーティングする。**目標:**かどうかを判断します：[a.] 管理対象のアクセス制御ポイントが識別され、実施される。および[b.] 管理対象のアクセス制御ポイントを介してリモート アクセスがルーティングされる。 | 内部と外部のネットワークを示す名前付きの場所を構成します。 Microsoft Defender for Cloud Apps 経由でアクセスをルーティングするように、条件付きアクセス アプリ コントロールを構成します。 すべてのセッションのコントロールとモニターを行うように、Defender for Cloud Apps を構成します。 特権アクセス ストーリーの一部として、特権アカウントが使用するデバイスをセキュリティで保護します。[Microsoft Entra 条件付きアクセスの場所の条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)[条件付きアクセス ポリシーでのセッション コントロール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)[特権アクセスのセキュリティ保護の概要](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview) |
| AC.L2-3.1.15**実施規定:** 特権コマンドのリモート実行とセキュリティ関連情報へのリモート アクセスを承認する。**目標:**かどうかを判断します：[a.] リモート実行が許可されている特権コマンドが識別される。[b.] リモート アクセスが許可されているセキュリティ関連情報が識別される。[c.] リモート アクセスによる識別された特権コマンドの実行が許可される。および[d.] リモート アクセスによる識別されたセキュリティ関連情報へのアクセスが許可される。 | 認証コンテキストと組み合わせた条件付きアクセスは、アプリにアクセスするためのポリシーをターゲットとするゼロトラスト コントロール プレーンです。 これらのアプリでは、さまざまなポリシーを適用できます。 特権アクセス ストーリーの一部として、特権アカウントが使用するデバイスをセキュリティで保護します。 特権コマンドを実行するときに、特権ユーザーがセキュリティで保護されたこれらのデバイスを使用することを要求する、条件付きアクセス ポリシーを構成します。[条件付きアクセス ポリシーでのクラウド アプリ、アクション、認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)[特権アクセスのセキュリティ保護の概要](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview)[条件付きアクセス ポリシーの条件としてのデバイスのフィルター](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices) |
| 交流電流 L2-3.1.18**実施規定:** モバイル デバイスの接続を制御する。**目標:**かどうかを判断します：[a.] CUI を処理、保存、または送信するモバイル デバイスが特定される。[b.] モバイル デバイス接続が許可されている。および[c.] モバイル デバイス接続が監視され、ログに記録される。 | MDM (Microsoft Intune など)、Configuration Manager、またはグループ ポリシー オブジェクト (GPO) を使用して、モバイル デバイス構成と接続プロファイルを強制するようにデバイス管理ポリシーを構成します。 デバイスのコンプライアンスを適用するように、条件付きアクセス ポリシーを構成します。**条件付きアクセス**[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[Microsoft Entra ハイブリッド参加デバイスを必要とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)**InTune**[Microsoft Intune のデバイス コンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)[Microsoft Intune でのアプリの管理とは](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-management) |
| AC.L2-3.1.19**実施規定:** モバイル デバイスとモバイル コンピューティング プラットフォームで CUI を暗号化する**目標:**かどうかを判断します：[a.] CUI を処理、保存、または送信するモバイル デバイスおよびモバイル コンピューティング プラットフォームが特定される。および[b.] 特定されたモバイル デバイスやモバイル コンピューティング プラットフォームで CUI を保護するために暗号化が採用されている。 | **マネージド デバイス**条件付きアクセス ポリシーを構成して、準拠しているデバイスまたは Microsoft Entra ハイブリッド参加済みデバイスに適用し、マネージド デバイスがデバイス管理ソリューションを使用して CUI を暗号化するように、適切に構成されることを確認します。**アンマネージド デバイス**アプリ保護ポリシーを必要とするように条件付きアクセス ポリシーを構成します。[条件付きアクセス ポリシーの許可コントロール - デバイスは準拠としてマーク済みであることが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス ポリシーの許可コントロール - Microsoft Entra ハイブリッド参加済みデバイスが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス ポリシーの許可コントロール - アプリの保護ポリシーが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) |
| AC.L2-3.1.21**実施規定:** 外部システムでのポータブル ストレージ デバイスの使用を制限する。**目標:**かどうかを判断します：[a.] 外部システムでの CUI を含むポータブル ストレージ デバイスの使用が特定され、文書化されている。[b.] 外部システムでの CUI を含むポータブル ストレージ デバイスの使用制限が定義されている。および[c.] 外部システムでの CUI を含むポータブル ストレージ デバイスの使用が定義どおりに制限されている。 | MDM (Microsoft Intune など)、Configuration Manager、またはグループ ポリシー オブジェクト (GPO) を使用して、システム上でのポータブル ストレージ デバイスの使用を制御するようにデバイス管理ポリシーを構成します。 Windows デバイス上でポリシー設定を構成して、ポータブル ストレージの使用を OS レベルで完全に禁止または制限します。 ポータブル ストレージへのアクセスをきめ細かく制御できない可能性がある他のすべてのデバイスについては、Microsoft Defender for Cloud Apps を使用してダウンロードを完全にブロックします。 デバイスのコンプライアンスを適用するように、条件付きアクセス ポリシーを構成します。**条件付きアクセス**[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[Microsoft Entra ハイブリッド参加デバイスを必要とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[認証セッション管理を構成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)**Intune**[Microsoft Intune のデバイス コンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)[Microsoft Intune の管理用テンプレートを使用して USB デバイスを制限する](https://learn.microsoft.com/ja-jp/mem/intune/configuration/administrative-templates-restrict-usb)**Microsoft Defender for Cloud Apps**[Defender for Cloud Apps でセッション ポリシーを作成する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/session-policy-aad) |

#### 次のステップ

- [CMMC コンプライアンス用に Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-for-cmmc-compliance)
- [CMMC レベル 1 コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-1-controls)
- [CMMC レベル 2 の識別と認証 (IA) コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-identification-and-authentication)
- [CMMC レベル 2 の追加コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-additional-controls)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/configure-cmmc-level-2-additional-controls"} -->
## CMMC レベル 2 を満たすように ID アクセス制御を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-additional-controls
- Service: entra / standards
- Article date: 2023-01-03
- Summary: CMMC レベル 2 の要件を満たすように Microsoft Entra ID を構成する方法について説明します。

Microsoft Entra ID は、各 Cybersecurity Maturity Model Certification (CMMC) レベルで ID 関連の実施要件を満たすのに役立ちます。 CMMC V2.0 レベル 2 の要件に準拠するために、米国国防総省 (DoD) と協力して、および代理として、他の構成やプロセスを実行することは、企業の責任です。

CMMC レベル 2 には、ID に関連する 1 つ以上のプラクティスを含む 13 のドメインがあります。

- アクセスの制御 (AC)
- 監査とアカウンタビリティ (AU)
- 構成管理 (CM)
- 識別と認証 (IA)
- インシデント対応 (IR)
- メンテナンス (MA)
- メディア保護 (MP)
- 人的セキュリティ (PS)
- 物理的保護 (PE)
- リスク評価 (RA)
- セキュリティ評価 (CA)
- システムと通信の保護 (SC)
- システムと情報の整合性 (SI)

この記事の残りの部分では、他の記事で取り上げているアクセス制御 (AC) および識別と認証 (IA) を除くすべてのドメインに関するガイダンスを提供します。 ドメインごとに、プラクティスを実行するためのステップバイステップのガイダンスを提供するコンテンツへのリンクを含む表があります。

### 監査とアカウンタビリティ

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| AU.L2-3.3.1**実施規定:** 違法なまたは許可されていないシステム アクティビティの監視、分析、調査、報告を可能にするために、システム監査ログとレコードを作成および保持します。**目標:**かどうかを判断します：[a.] 監査ログは、ログするイベントの種類などを指定し、違法または許可されていないシステム活動の監視、分析、調査、および報告を可能にするために定義されています。[b.] 違法または許可されていないシステム アクティビティの監視、分析、調査、報告をサポートするために必要な監査レコードの内容が定義されていること。[c.] 監査レコードが作成 (生成) されること。[d.] 作成された監査レコードに、定義された内容が含められること。[例] 監査レコードの保持要件が定義されていること。[f.] 監査レコードが定義どおりに保持されること。AU.L2-3.3.2**実施規定:** 個々のシステム ユーザーの行動が、そのユーザーに対して一意に追跡可能であり、ユーザーが自らの行動に説明責任を負うことができるようにします。**目標:**かどうかを判断します：[a.] ユーザーに対してその行動を一意に追跡する機能をサポートするために必要な監査レコードの内容が定義されていること。[b.] 作成された監査レコードに、定義された内容が含まれること。 | すべての操作は、Microsoft Entra 監査ログ内で監査されます。 各監査ログ エントリには、各アクションに対して個々のシステム ユーザーを一意にトレースするために使用できる、ユーザーの不変 objectID が含まれています。 Microsoft Sentinel などのセキュリティ情報イベント管理 (SIEM) ソリューションを使用して、ログを収集および分析できます。 または、Azure Event Hubs を使用して、ログをサード パーティ製の SIEM ソリューションと統合し、監視と通知を有効にすることもできます。[Azure portal でアクティビティ レポートを監査する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)[Microsoft Entra データを Microsoft Sentinel に接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)[チュートリアル: ログを Azure イベント ハブにストリーム配信する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |
| AU.L2-3.3.4**実施規定:** 監査ログ プロセスが失敗した場合にアラートを生成します。**目標:**かどうかを判断します：[a.] 監査ログ処理の障害が特定された場合にアラートを受け取るべき担当者または役割。[b.] アラートが生成される監査ログ プロセス エラーの種類が定義されていること。[c] 特定された担当者またはロールは、監査ログ処理の失敗が発生したときに警告を受けます。 | Azure Service Health では、ダウンタイムを短縮する処置を取れるように、Azure サービス インシデントについて通知されます。 Microsoft Entra ID のカスタマイズ可能なクラウド アラートを構成します。 [Azure Service Health とは](https://learn.microsoft.com/ja-jp/azure/service-health/overview)[Azure サービスの問題に関する通知を受け取る 3 つの方法](https://azure.microsoft.com/blog/three-ways-to-get-notified-about-azure-service-issues/)[Azure Service Health](https://azure.microsoft.com/get-started/azure-portal/service-health/) |
| AU.L2-3.3.6**実施規定:** オンデマンドでの分析と報告をサポートするための、監査レコードの削減およびレポート生成機能を提供します。**目標:**かどうかを判断します：[a.] オンデマンド分析をサポートする監査レコード削減機能が提供されていること。[b.] オンデマンド レポートをサポートするレポート生成機能が用意されていること。 | Microsoft Entra イベントがイベント ログ戦略に含まれていることを確認します。 Microsoft Sentinel などのセキュリティ情報イベント管理 (SIEM) ソリューションを使用して、ログを収集および分析できます。 または、Azure Event Hubs を使用して、ログをサード パーティ製の SIEM ソリューションと統合し、監視と通知を有効にすることもできます。 アクセス レビューで Microsoft Entra のエンタイトルメント管理を使用して、アカウントのコンプライアンス対応状態を確認します。 [Azure portal でアクティビティ レポートを監査する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)[Microsoft Entra データを Microsoft Sentinel に接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)[チュートリアル: ログを Azure イベント ハブにストリーム配信する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |
| AU.L2-3.3.8**実施規定:** 監査情報と監査ログ ツールを、許可されていないアクセス、修正、削除から保護します。**目標:**かどうかを判断します：[a.] 監査情報が許可されていないアクセスから保護されること。[b.] 監査情報が許可されていない修正から保護されること。[c.] 監査情報が許可されていない削除から保護されること。[d.] 監査ログ ツールが、許可されていないアクセスから保護されること。[例] 監査ログ ツールが、許可されていない修正から保護されること。[f.] 監査ログツールは、不正な削除から保護されている。天文単位 L2-3.3.9**実施規定:** 監査ログ機能の管理を特権ユーザーの一部に限定します。**目標:**かどうかを判断します：[a.] 監査ログ機能を管理するためのアクセス権を付与された特権ユーザーのサブセットが定義されていること。[b.] 監査ログ機能の管理が、特権ユーザーの定義されたサブセットに限定されていること。 | Microsoft Entra ログは、既定で 30 日間保持されます。 これらのログは変更や削除ができず、限定された特権ロールのセットからのみアクセスできます。[Microsoft Entra ID のログインログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)[Microsoft Entra ID の監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs) |

### 構成管理 (CM)

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| CM.L2-3.4.2**実施規定:** 組織のシステムで採用された情報技術製品のセキュリティ構成の設定を確立および適用します。**目標:**かどうかを判断します：[a.] システムで採用された情報技術製品のセキュリティ構成の設定が確立され、ベースライン構成に含まれていること。[b.] システムで採用された情報技術製品のセキュリティ構成の設定が適用されていること。 | ゼロトラスト セキュリティ体制を採用します。 条件付きアクセス ポリシーを使用して、準拠デバイスへのアクセスを制限します。 Microsoft Intune などの MDM ソリューションを使用して、デバイスにセキュリティ構成設定を適用するために、デバイスでポリシー設定を構成します。 ハイブリッド デプロイでは、Microsoft Configuration Manager またはグループ ポリシー オブジェクトも考慮でき、条件付きアクセスと組み合わせる場合、Microsoft Entra ハイブリッドに参加しているデバイスが必要です。**ゼロトラスト**[ゼロ トラストによる ID のセキュリティ保護](https://learn.microsoft.com/ja-jp/security/zero-trust/deploy/identity)**条件付きアクセス**[Microsoft Entra ID の条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)[条件付きアクセス ポリシーでの許可の制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)**デバイスのポリシー**[Microsoft Intune とは](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)[Defender for Cloud Apps とは](https://learn.microsoft.com/ja-jp/cloud-app-security/what-is-cloud-app-security)[Microsoft Intune でのアプリの管理とは](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-management)[Microsoft のエンドポイント管理ソリューション](https://learn.microsoft.com/ja-jp/mem/endpoint-manager-overview) |
| CM.L2-3.4.5**実施規定:** 組織のシステムの変更に関連する物理的および論理的アクセス制限を定義、文書化、承認、適用します。**目標:**かどうかを判断します：[a.] システムの変更に関連する物理的アクセス制限が定義されていること。[b.] システムの変更に関連する物理的アクセス制限が文書化されていること。[c.] システムの変更に関連する物理的アクセス制限が承認されていること。[d.] システムの変更に関連する物理的アクセス制限が適用されていること。[例] システムの変更に関連する論理的アクセス制限が定義されていること。[f.] システムの変更に関連する論理的アクセス制限が文書化されていること。[例] システムの変更に関連する論理的アクセス制限が承認されていること。[h.] システムの変更に関連する論理的アクセス制限が適用されていること。 | Microsoft Entra ID は、Microsoft のクラウドベースの ID およびアクセス管理サービスです。 ユーザーは、Microsoft Entra データセンターに物理的にアクセスできません。 そのため、各物理アクセス制限は、Microsoft によって実現され、Microsoft Entra ID の顧客に継承されます。 Microsoft Entra のロールベースのアクセス制御を実装します。 永続的な特権アクセスを排除し、Privileged Identity Management を使用した承認ワークフローによるジャストインタイム アクセスを提供します。[Microsoft Entra ロール ベースのアクセス制御 (RBAC) の概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)[Privileged Identity Management とは?](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)[PIM での Microsoft Entra ロールの要求を承認または否認する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow) |
| CM.L2-3.4.6**実施規定:** 必須の機能のみを提供するように組織のシステムを構成することにより、最小の機能の原則を採用します。**目標:**かどうかを判断します：[a.] 必須のシステム機能が、最小限の機能の原則に基づいて定義されていること。[b.] システムが、定義された必須の機能のみを提供するように構成されていること。 | デバイス管理ソリューション (Microsoft Intune など) を構成して、組織のシステムに適用されるカスタム セキュリティ ベースラインを実装し、重要でないアプリケーションを削除し、不要なサービスを無効にします。 システムを効率的に動作させるために必要な最少の機能だけ残します。 条件付きアクセスを構成して、準拠または Microsoft Entra ハイブリッド参加済みデバイスへのアクセスを制限します。 [Microsoft Intune とは](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス ポリシーの許可コントロール - Microsoft Entra ハイブリッド参加済みデバイスが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) |
| CM.L2-3.4.7**実施規定:** 必須でないプログラム、機能、ポート、プロトコル、サービスの使用を制限、無効化、または防止します。**目標:**かどうかを判断します：[a.] 必須のプログラムが定義されていること。[b.] 必須でないプログラムの使用が定義されていること。[c.] 必須でないプログラムの使用が、定義されているとおりに制限、無効化、または防止されていること。[d.] 必須の機能が定義されていること。[例] 必須でない機能の使用が定義されていること。[f.] 必須でない機能の使用が、定義されているとおりに制限、無効化、または防止されていること。[例] 必須のポートが定義されていること。[h.] 必須でないポートの使用が定義されていること。[i.] 必須でないポートの使用が、定義されているとおりに制限、無効化、または防止されていること。[j.] 必須のプロトコルが定義されていること。[k.] 必須でないプロトコルの使用が定義されていること。[l.] 必須でないプロトコルの使用が、定義されているとおりに制限、無効化、または防止されていること。[m.] 必須のサービスが定義されていること。[n.] 必須でないサービスの使用が定義されていること。[o.] 必須でないサービスの使用が、定義されているとおりに制限、無効化、または防止されていること。 | アプリケーション管理者ロールを使用して、重要なアプリケーションの認可された使用を委任します。 アプリ ロールまたはグループ要求を使用して、アプリケーション内の最小特権アクセスを管理します。 管理者の承認を必要とし、グループ所有者の同意を許可しないユーザーの同意を構成します。 ユーザーが管理者の同意を必要とするアプリケーションへのアクセスを要求できるように、管理者同意要求ワークフローを構成します。 Microsoft Defender for Cloud Apps を使用して、承認されていない、または不明なアプリケーションの使用を特定します。 このテレメトリを使用して、重要なアプリまたは重要でないアプリを決定します。[Microsoft Entra 組み込みロール - アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)[Microsoft Entra アプリ ロール - アプリ ロールとグループの比較](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)[ユーザーがアプリケーションに同意する方法を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent?tabs=azure-portal.md)[グループ データにアクセスするアプリに対するグループ所有者の同意を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent-groups?tabs=azure-portal.md)[管理者の同意ワークフローの構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)[Defender for Cloud Apps とは](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps)[シャドウ IT の検出と管理を行うためのチュートリアル](https://learn.microsoft.com/ja-jp/defender-cloud-apps/tutorial-shadow-it) |
| CM.L2-3.4.8**実施規定:** "例外による拒否" (ブロックリスト登録) ポリシーを適用して許可されていないソフトウェアの使用を防止するか、"すべて拒否、例外による許可" (許可リスト登録) ポリシーを適用して許可されたソフトウェアの実行を許可します。**目標:**かどうかを判断します：[a.] 許可リストまたはブロックリストを実装するかどうかを指定するポリシーが指定されていること。[b.] 許可リストで実行できるソフトウェア、またはブロックリストで使用を拒否されたソフトウェアが指定されていること。[c.] 許可されたソフトウェアの実行を許可する許可リスト、または許可されていないソフトウェアの使用を防止するブロックリストが、指定どおりに実装されていること。CM.L2-3.4.9**実施規定:** ユーザーによってインストールされたソフトウェアを制御および監視します。**目標:**かどうかを判断します：[a.] ユーザーによるソフトウェアのインストールを制御するためのポリシーが確立されていること。[b.] ユーザーによるソフトウェアのインストールが、確立されたポリシーに基づいて制御されること。[c.] ユーザーによるソフトウェアのインストールが監視されること。 | 未承認のソフトウェアの使用を防ぐために、MDM または構成管理ポリシーを構成します。 MDM または構成管理ポリシーによるデバイス コンプライアンスを、条件付きアクセス認可の決定に組み込むために、準拠またはハイブリッド参加済みデバイスを必要とする条件付きアクセス許可制御を構成します。[Microsoft Intune とは](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)[条件付きアクセス - 準拠しているデバイスまたはハイブリッド参加済みのデバイスが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa) |

### インシデント対応 (IR)

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| IR・L2-3.6.1**実施規定:** 準備、検出、分析、包含、回復、ユーザー対応を含め、組織のシステムに運用のインシデント処理機能を確立します。**目標:**かどうかを判断します：[a.] 運用インシデント処理機能が確立されていること。[b.] 運用インシデント処理機能に準備が含まれていること。[c.] 運用インシデント処理機能に検出が含まれていること。[d.] 運用インシデント処理機能に分析が含まれていること。[例] 運用インシデント対応能力には封じ込めが含まれています。[f.] 運用インシデント処理機能に回復が含まれていること。[例] 運用インシデント処理機能には、ユーザー対応が含まれていること。 | インシデントの処理と監視の機能を実装します。 監査ログには、構成の変更がすべて記録されます。 認証および認可イベントはサインイン ログ内で監査され、検出されたリスクはすべて ID保護 ログで監査されます。 これらのログはそれぞれ、Microsoft Sentinel などの SIEM ソリューションに直接ストリーム配信することができます。 または、Azure Event Hubs を使用して、ログをサードパーティ製の SIEM ソリューションと統合します。**監査イベント**[Azure portal でアクティビティ レポートを監査する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)[Azure portal でのサインイン アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)[方法: リスクの調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)**SIEM の統合**[Microsoft Sentinel: Microsoft Entra ID のデータを接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)[Azure イベント ハブやその他の SIEM へのストリーミング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |

### メンテナンス (MA)

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| MA.L2-3.7.5**実施規定:** 外部ネットワーク接続を介した非ローカル メンテナンス セッションの確立に多要素認証を要求し、非ローカル メンテナンスの完了時にその接続を終了します。**目標:**かどうかを判断します：[a.] 外部ネットワーク接続を介して非ローカル メンテナンス セッションを確立に多要素認証が使用されること。[b.] 外部ネットワーク接続を介して確立された非ローカル メンテナンス セッションが、非ローカル メンテナンスの完了時に終了すること。 | 管理者権限が割り当てられたアカウントは、ローカル以外のメンテナンス セッションを確立するために使用されるアカウントを含め、攻撃者の標的になります。 これらのアカウントに対して多要素認証 (MFA) を必須にすることは、これらのアカウントが侵害されるリスクを軽減する簡単な方法です。[条件付きアクセス - すべての管理者に対して MFA を必須にする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-old-require-mfa-admin) |
| MP.L2-3.8.7**実施規定:** システム コンポーネントでのリムーバブル メディアの使用を制御します。**目標:**かどうかを判断します：[a.] システム コンポーネントでのリムーバブル メディアの使用が制御されること。 | MDM (Microsoft Intune など)、Configuration Manager、またはグループ ポリシー オブジェクト (GPO) を使用して、デバイス管理ポリシーを構成し、システム上のリムーバブル メディアの使用を制御します。 Intune、Configuration Manager またはグループ ポリシーを使用して、リムーバブル記憶域のアクセス制御をデプロイおよび管理します。 デバイスのコンプライアンスを適用するように、条件付きアクセス ポリシーを構成します。**条件付きアクセス**[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[Microsoft Entra ハイブリッド参加デバイスを必要とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant#require-hybrid-azure-ad-joined-device)**Intune**[Microsoft Intune のデバイス コンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)**リムーバブル記憶域のアクセス制御**[Intune を使用してリムーバブル記憶域のアクセス制御をデプロイおよび管理する](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/deploy-manage-removable-storage-intune?view=o365-worldwide&preserve-view=true)[グループ ポリシーを使用してリムーバブル記憶域のアクセス制御をデプロイおよび管理する](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/deploy-manage-removable-storage-group-policy?view=o365-worldwide&preserve-view=true) |

### 人的セキュリティ (PS)

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| PS.L2-3.9.2**実施規定:** 退職や異動などの人事措置の最中および後に、CUI を含む組織のシステムが確実に保護されるようにします。**目標:**かどうかを判断します：[a.] システム アクセスを終了するためのポリシーおよび/またはプロセスと、人事措置と同期する資格情報が確立されていること。[b.] システム アクセスと資格情報が、退職や異動などの人事措置と同期して終了されること。[c] システムが、人事異動措置の最中および後に保護されること。 | 外部人事システムから、オンプレミスの Active Directory から、またはクラウド内で直接、Microsoft Entra ID のアカウントのプロビジョニング (離職時の無効化を含む) を構成します。 既存のセッションを取り消して、すべてのシステム アクセスを終了します。**アカウント プロビジョニング**[Microsoft Entra ID を使った ID プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning)[Microsoft Entra Connect Sync: 同期を理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)[Microsoft Entra Connect クラウド同期とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)**関連付けられているすべての認証子を取り消します**[Microsoft Entra ID で緊急時にユーザー アクセスを取り消す](https://learn.microsoft.com/ja-jp/entra/identity/users/users-revoke-access) |

### システムと通信の保護 (SC)

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| SC.L2-3.13.3**実施規定:** システム管理機能からユーザー機能を分離します。 **目標:**かどうかを判断します：[a.] ユーザー機能が識別されていること。[b.] システム管理機能が識別されていること。[c.] ユーザー機能がシステム管理機能から分離されていること。 | Microsoft Entra ID で、日常の生産での使用と管理またはシステム/特権管理用に、個別のユーザー アカウントを保持します。 特権アカウントはクラウド専用またはマネージド アカウントにし、オンプレミスの侵害からクラウド環境を保護するために、オンプレミスから同期されないようにする必要があります。 システム/特権アクセスは、セキュリティ強化された特権アクセス ワークステーション (PAW) からのみ許可される必要があります。 条件付きアクセス デバイス フィルターを構成して、Azure Virtual Desktop を使用して有効にされている PAW からの管理アプリケーションへのアクセスを制限します。[特権アクセス デバイスが重要な理由](https://learn.microsoft.com/ja-jp/security/compass/privileged-access-devices)[デバイスのロールとプロファイル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-devices)[条件付きアクセス ポリシーの条件としてのデバイスのフィルター](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)[Azure Virtual Desktop](https://azure.microsoft.com/products/virtual-desktop/) |
| SC.L2-3.13.4**実施規定:** 共有システム リソースを介した、許可されていない情報転送や意図していない情報転送を防止します。**目標:**かどうかを判断します：[a.] 共有システム リソースを介した、許可されていない情報転送や意図していない情報転送が防止されること。 | MDM (Microsoft Intune など)、Configuration Manager、またはグループ ポリシー オブジェクト (GPO) を使用してデバイス管理ポリシーを構成し、デバイスがシステム強化手順に確実に準拠するようにします。 攻撃者による欠陥の悪用を防ぐためのソフトウェア パッチに関する会社のポリシーへの準拠を含めます。デバイスのコンプライアンスを適用するように、条件付きアクセス ポリシーを構成します。**条件付きアクセス**[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[Microsoft Entra ハイブリッド参加デバイスを必要とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)**InTune**[Microsoft Intune のデバイス コンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started) |
| SC.L2-3.13.13**実施規定:** モバイル コードの使用を制御および監視します。**目標:**かどうかを判断します：[a.] モバイル コードの使用が制御されること。[b.] モバイル コードの使用が監視されること。 | MDM (Microsoft Intune など)、Configuration Manager、またはグループ ポリシー オブジェクト (GPO) を使用して、デバイス管理ポリシーを構成し、モバイル コードの使用を無効にします。 モバイル コードの使用が必要な場合、Microsoft Defender for Endpoint などのエンドポイント セキュリティによってその使用を監視します。デバイスのコンプライアンスを適用するように、条件付きアクセス ポリシーを構成します。**条件付きアクセス**[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[Microsoft Entra ハイブリッド参加デバイスを必要とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)**InTune**[Microsoft Intune のデバイス コンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)**エンドポイント用 Defender**[Microsoft Defender for Endpoint](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/microsoft-defender-endpoint?view=o365-worldwide&preserve-view=true) |

### システムと情報の整合性 (SI)

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| SI.L2-3.14.7**実施規定:** **目標:** 組織のシステムの許可されていない使用を特定します。かどうかを判断します：[a.] システムの許可された使用が定義されていること。[b.] システムの許可されていない使用が特定されること。 | テレメトリを統合し、Microsoft Entra ログを Azure Sentinel などの SIEM にストリーミングします。デバイス管理ポリシーは、MDM (Microsoft Intune など)、Configuration Manager、またはグループ ポリシー オブジェクト (GPO) を介して構成し、Microsoft Defender for Endpoint などの侵入検知/保護 (IDS/IPS) がインストールされ、使用されていることを要求します。 IDS/IPS によって提供されるテレメトリを使用して、受信および送信通信トラフィックまたは未承認の使用に関連する異常なアクティビティまたは条件を特定します。デバイスのコンプライアンスを適用するように、条件付きアクセス ポリシーを構成します。**条件付きアクセス**[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[Microsoft Entra ハイブリッド参加デバイスを必要とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)**InTune**[Microsoft Intune のデバイス コンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)**エンドポイント用 Defender**[Microsoft Defender for Endpoint](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/microsoft-defender-endpoint?view=o365-worldwide&preserve-view=true) |

#### 次のステップ

- [Microsoft Entra ID の条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [追加のコントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-additional-controls)
- [条件付きアクセスにはマネージド デバイスが必要 - Microsoft Entra ハイブリッド参加済みデバイスが必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)
- [条件付きアクセスにはマネージド デバイスが必要 - デバイスを準拠としてマークする必要がある](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)
- [Microsoft Intune とは](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)
- [Windows 10 デバイスの共同管理](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/overview)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/configure-cmmc-level-2-identification-and-authentication"} -->
## CMMC レベル 2 の識別と認証 (IA) 制御を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-identification-and-authentication
- Service: entra / standards
- Article date: 2023-01-03
- Summary: CMMC レベル 2 の識別と認可の要件を満たすように Microsoft Entra ID を構成する方法について学習します。

Microsoft Entra ID は、各 Cybersecurity Maturity Model Certification (CMMC) レベルで ID 関連の実施要件を満たすのに役立ちます。 他の構成やプロセスを CMMC V2.0 レベル 2 の要件に準拠させるのは、米国国防総省 (DoD) と協力して代わりに作業を行う企業の責任です。

CMMC レベル 2 には、ID に関連する 1 つ以上のプラクティスを持つ 13 のドメインがあります。 ドメインは次のとおりです。

- アクセスの制御 (AC)
- 監査とアカウンタビリティ (AU)
- 構成管理 (CM)
- 識別と認証 (IA)
- インシデント対応 (IR)
- メンテナンス (MA)
- メディア保護 (MP)
- 人的セキュリティ (PS)
- 物理的保護 (PE)
- リスク評価 (RA)
- セキュリティ評価 (CA)
- システムと通信の保護 (SC)
- システムと情報の整合性 (SI)

この記事の残りの部分では、識別と認可 (IA) ドメインのガイダンスを提供します。 プラクティスを実行するためのステップバイステップのガイダンスを提供するコンテンツへのリンクを含む表があります。

### 識別と認証

次の表に、実施規定と目標のリストと、Microsoft Entra ID でこれらの要件を満たせるようにするための Microsoft Entra ガイダンスと推奨事項を示します。

| CMMC 実施規定と目標 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| IA.L2-3.5.3**実施規定:** 特権アカウントへのローカルおよびネットワークのアクセスと、非特権アカウントへのネットワーク アクセスに多要素認証を使用する。 **目標:**かどうかを判断します：[a.] 特権アカウントが識別される。[b.] 特権アカウントへのローカル アクセスのための多要素認証が実装されている。[c.] 特権アカウントへのネットワーク アクセスのための多要素認証が実装されている。および[d.] 非特権アカウントへのネットワーク アクセスのための多要素認証が実装されている。 | 以下の項目は、この制御領域に使用される用語の定義です。- **ローカル アクセス** - ネットワークを使用せずに直接接続を介して通信するユーザー (またはユーザーに代わって動作するプロセス) による組織情報システムへのアクセス。<br>- **ネットワーク アクセス** - ネットワーク (ローカル エリア ネットワーク、ワイド エリア ネットワーク、インターネットなど) を介して通信するユーザー (またはユーザーに代わって動作するプロセス) による情報システムへのアクセス。<br>- **特権ユーザー** - 通常のユーザーが実行を許可されていないセキュリティ関連の機能を実行する権限を持つ (したがって、信頼できる) ユーザー。前述の要件を分析すると、次のようになります。<br>- すべてのユーザーは、ネットワーク/リモート アクセスに MFA を必要とします。<br>- ローカル アクセスには、特権ユーザーのみが MFA を必要とします。 自分のコンピューターに対してのみ管理者権限を持っている通常のユーザー アカウントは "特権アカウント" ではなく、ローカル アクセスに MFA を必要としません。 多要素認証を要求するように条件付きアクセスを構成する必要があります。 AAL2 以上を満たす Microsoft Entra 認証方法を有効にしてください。[条件付きアクセス ポリシーでの許可の制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[Microsoft Entra ID を使用して NIST 認証システムの保証レベルを実現する](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview)[認証方法と機能](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication) |
| IA.L2-3.5.4**実施規定:** 特権および非特権アカウントへのネットワーク アクセスに、リプレイ耐性のある認証メカニズムを採用する。**目標:**かどうかを判断します：[a.] 特権および非特権アカウントへのネットワーク アクセスに、リプレイ耐性のある認証メカニズムが実装されている。 | AAL2 以上のすべての Microsoft Entra 認証方法にリプレイ耐性があります。[Microsoft Entra ID を使用して NIST 認証システムの保証レベルを実現する](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview) |
| IA.L2-3.5.5**実施規定:** 定義された期間、識別子の再利用を禁止する。**目標:**かどうかを判断します：[a.] 識別子を再利用できない期間が定義されている。および[b.] 定義された期間内の識別子の再利用が禁止されている。 | すべてのユーザー、グループ、デバイス オブジェクトのグローバル一意識別子 (GUID) は、Microsoft Entra テナントの稼働期間中、一意性が保証され、再利用されません。[ユーザー リソースの種類 - Microsoft Graph v1.0](https://learn.microsoft.com/ja-jp/graph/api/resources/user?view=graph-rest-1.0&preserve-view=true)[グループ リソースの種類 - Microsoft Graph v1.0](https://learn.microsoft.com/ja-jp/graph/api/resources/group?view=graph-rest-1.0&preserve-view=true)[デバイス リソースの種類 - Microsoft Graph v1.0](https://learn.microsoft.com/ja-jp/graph/api/resources/device?view=graph-rest-1.0&preserve-view=true) |
| IA.L2-3.5.6**実施規定:** 定義された非アクティブな期間の経過後に識別子を無効にする。**目標:**かどうかを判断します：[a.] 識別子が無効になるまでの非アクティブ期間が定義されている。および[b.] 定義された非アクティブ期間の経過後に識別子が無効になる。 | Microsoft Graph と Microsoft Graph PowerShell SDK を使って、アカウント管理の自動化を実装します。 Microsoft Graph を使ってサインイン アクティビティを監視し、Microsoft Graph PowerShell SDK を使って必要な期間内にアカウントに対するアクションを実行します。**非アクティブ状態を判断する**[Microsoft Entra ID で非アクティブなユーザー アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts)[Microsoft Entra ID で古くなったデバイスを管理する](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices)**アカウントを削除または無効にする**[Microsoft Graph でのユーザー管理](https://learn.microsoft.com/ja-jp/graph/api/resources/user)[ユーザーの取得](https://learn.microsoft.com/ja-jp/graph/api/user-get?tabs=http)[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update?tabs=http)[ユーザーの削除](https://learn.microsoft.com/ja-jp/graph/api/user-delete?tabs=http)**Microsoft Graph でのデバイスの操作**[デバイスの取得](https://learn.microsoft.com/ja-jp/graph/api/device-get?tabs=http)[デバイスの更新](https://learn.microsoft.com/ja-jp/graph/api/device-update?tabs=http)[デバイスの削除](https://learn.microsoft.com/ja-jp/graph/api/device-delete?tabs=http)**[Microsoft Graph PowerShell SDK を使用する](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)**[Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser)[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)[Get-MgDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdevice)[Update-MgDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdevice) |
| IA.L2-3.5.7**実施規定:** **目標:** 新規パスワードの作成時に最小限のパスワードの複雑さと文字の変更を強制する。かどうかを判断します：[a.] パスワードの複雑さの要件が定義されている。[b.] パスワード変更の文字要件が定義されている。[c.] 新規パスワードの作成時に、定義された最小限のパスワードの複雑さの要件が適用される。および[d.] 新規パスワードの作成時に、定義された最小限のパスワード変更の文字要件が適用される。IA.L2-3.5.8**実施規定:** 指定の生成回数についてパスワードの再利用を禁止する。**目標:**かどうかを判断します：[a.] パスワードを再利用できない生成回数が指定されている。および[b.] 指定された生成回数で、パスワードの再利用が禁止されている。 | パスワードレスの戦略を**強くお勧めします**。 この制御はパスワード認証システムにのみ適用されるため、使用可能な認証システムとしてパスワードを削除すると、この制御は適用されません。NIST SP 800-63 B セクション 5.1.1 に準拠: よく使用されたり、予想されたり、または侵害されたりするパスワードの一覧を保持します。Microsoft Entra パスワード保護では、既定のグローバル禁止パスワード リストが Microsoft Entra テナント内のすべてのユーザーに自動的に適用されます。 ビジネス ニーズやセキュリティ ニーズに対応するため、カスタムの禁止パスワード リストにエントリを定義できます。 ユーザーがパスワードを変更またはリセットすると、これらの禁止パスワード リストがチェックされ、強力なパスワードの使用が強制されます。パスワード文字の厳密な変更を必要とするお客様の場合、パスワードの再利用と複雑さの要件では、Password-Hash-Sync で構成されたハイブリッド アカウントが使用されます。このアクションにより、Microsoft Entra ID に同期されたパスワードでは、Active Directory パスワード ポリシーで構成されている制限が確実に継承されます。 Active Directory Domain Services 用にオンプレミスの Microsoft Entra パスワード保護を構成して、オンプレミスのパスワードをさらに保護します。[NIST 特別刊行物 800-63 B](https://pages.nist.gov/800-63-3/sp800-63b.html)[NIST Special Publication 800-53 バージョン 5 (IA-5 - 管理強化 (1)](https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-53r5.pdf)[Microsoft Entra パスワード保護を使って不適切なパスワードを排除する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad)[Microsoft Entra ID とのパスワード ハッシュ同期とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) |
| IA.L2-3.5.9**実施規定:** システム ログオン時、常用パスワードに即時変更することを条件として一時的なパスワードの使用を許可する。**目標:**かどうかを判断します：[a.] システム サインオンに一時的なパスワードを使用する場合は、常用パスワードに即時変更する必要がある。 | Microsoft Entra ユーザーの初期パスワードは、1 回限りの一時的なパスワードであり、一度正常に使用したら、すぐに永続的なパスワードに変更する必要があります。 Microsoft では、パスワードレス認証方法の導入を強くお勧めしています。 ユーザーは、一時アクセス パス (TAP) を使用してパスワードレス認証方法をブートストラップできます。 TAP は、管理者が発行する期限付きの用途に制限のあるパスコードであり、強力な認証要件を満たすものです。 期限付きの用途に制限のある TAP と共にパスワードレス認証を使用すると、パスワードの使用 (およびその再利用) が完全になくなります。[ユーザーを追加または削除する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)[パスワードレスの認証方法を登録するように Microsoft Entra ID で一時アクセス パスを構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass)[パスワードレスの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) |
| IA.L2-3.5.10**実施規定:** 暗号で保護されたパスワードのみを保存および送信する。**目標:**かどうかを判断します：[a.] パスワードが暗号化によって保護され保存されている。および[b.] パスワードが転送中も暗号化により保護されている。 | **保存時のシークレット暗号化**:ディスク レベルの暗号化に加えて、保存時にディレクトリに格納されているシークレットは、分散キー マネージャー (DKM) を使用して暗号化されます。 暗号化キーは Microsoft Entra コア ストアに格納され、スケール ユニット キーを使用して暗号化されます。 キーは、最高の特権を持つユーザーと特定のサービスのために、ディレクトリ ACL で保護されているコンテナーに格納されます。 対称キーは通常、6 か月ごとにローテーションされます。 環境へのアクセスは、運用制御と物理的なセキュリティによってさらに保護されます。**転送中の暗号化**:データのセキュリティを確保するために、Microsoft Entra ID のディレクトリ データは、スケール ユニット内のデータ センター間の転送中に署名および暗号化されます。 データは、関連付けられている Microsoft データ センターのセキュリティで保護されたサーバー ホスティング領域内に存在する、Microsoft Entra コア ストア層によって暗号化および非暗号化されます。顧客向け Web サービスは、トランスポート層セキュリティ (TLS) プロトコルを使用してセキュリティで保護されます。詳細については、"データ保護に関する考慮事項 - データ セキュリティ" に関する記事を[ダウンロード](https://azure.microsoft.com/resources/azure-active-directory-data-security-considerations/)してください。 15 ページに詳細があります。[パスワード ハッシュ同期の解明 (microsoft.com)](https://www.microsoft.com/security/blog/2019/05/30/demystifying-password-hash-sync/)[Microsoft Entra データ セキュリティに関する考慮事項](https://aka.ms/aaddatawhitepaper) |
| IA.L2-3.5.11**実施規定:** 認証情報のフィードバックを伏せる。**目標:**かどうかを判断します：[a.] 認証プロセス中に認証情報が隠される。 | 既定では、Microsoft Entra ID ですべての認証子フィードバックが隠されます。 |

#### 次のステップ

- [CMMC コンプライアンス用に Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-for-cmmc-compliance)
- [CMMC レベル 1 コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-1-controls)
- [CMMC レベル 2 Access Control (AC) コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-access-control)
- [CMMC レベル 2 の追加コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-additional-controls)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/configure-for-cmmc-compliance"} -->
## Microsoft Entra IDをCMMC準拠で構成します。 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/configure-for-cmmc-compliance
- Service: entra / standards
- Article date: 2023-01-03
- Summary: CMMC の要件を満たすように Microsoft Entra ID を構成する方法についてご確認ください。

Microsoft Entra ID は、各 Cybersecurity Maturity Model Certification (CMMC) レベルで ID 関連の実施要件を満たすのに役立ちます。 CMMC の要件に準拠するために、米国国防総省 (DoD) と協力して、および代理として、他の構成またはプロセスを実行することは、企業の責任です。

CMMC レベル 1 には、ID に関連する 1 つまたは複数のプラクティスを含む 3 つのドメインがあります。

- アクセス制御 (AC)
- 識別と認証 (IA)
- システムと情報の整合性 (SI)

CMMC レベル 2 には、ID に関連する 1 つ以上のプラクティスを含む 13 のドメインがあります。

- Access Control
- 監査とアカウンタビリティ
- 構成管理
- 識別と認証
- インシデント対応
- メンテナンス
- メディア保護
- 人的セキュリティ
- 物理的な保護
- リスク評価
- セキュリティ評価
- システムと通信の保護
- システムと情報の整合性

このシリーズの残りの記事では、レベルとドメイン別に整理されたガイダンスとリソースへのリンクを提供します。 ドメインごとに、関連するコントロールが一覧表示された、プラクティスを実践するためのガイダンスへのリンクを含む表があります。

詳細情報：

- DoD CMMC Web サイト - [取得およびサステインメントサイバーセキュリティ成熟度モデル認定のための国防次官事務所](https://dodcio.defense.gov/CMMC/)
- Microsoft ダウンロード センター - [CMMC 2.0 用の Microsoft Product Placemat (プレビュー)](https://www.microsoft.com/download/details.aspx?id=102536)

#### 次のステップ

- [CMMC レベル 1 コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-1-controls)
- [CMMC レベル 2 Access Control (AC) コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-access-control)
- [CMMC レベル 2 の識別と認証 (IA) コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-identification-and-authentication)
- [CMMC レベル 2 の追加コントロールを構成する](https://learn.microsoft.com/ja-jp/entra/standards/configure-cmmc-level-2-additional-controls)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/configure-for-fedramp-high-impact"} -->
## FedRAMP High Impact レベルを満たすように Microsoft Entra ID を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/configure-for-fedramp-high-impact
- Service: entra / standards
- Article date: 2023-03-09
- Summary: Microsoft Entra ID を使用して、組織の FedRAMP High Impact レベルを満たす方法の概要。

[Federal Risk and Authorization Management Program](https://www.fedramp.gov/) (FedRAMP) は、クラウド サービス プロバイダー (CSP) の評価と承認プロセスです。 具体的には、このプロセスは、連邦機関で使用するためのクラウド ソリューション オファリング (CSO) を作成する CSP 向けです。 Azure と Azure Government は、FedRAMP 認定の最高基準である共同承認委員会から、高影響レベルでの暫定的な運用認可 (P-ATO) を獲得しました。

Azure には、CSO の FedRAMP の高い評価を達成するために、または連邦機関として、すべての制御要件を満たす機能が用意されています。 準拠する追加の構成またはプロセスを完了するのは組織の責任です。 この責任は、CSO に対する FedRAMP の高い承認を求める CSP と、運用機関 (ATO) を求める連邦機関の両方に適用されます。

### Microsoft と FedRAMP

Microsoft Azure では、他のどの CSP よりも [多くのサービスが FedRAMP High Impact](https://learn.microsoft.com/ja-jp/azure/azure-government/compliance/azure-services-in-fedramp-auditscope) レベルでサポートされています。 また、Azure パブリック クラウドのこのレベルは多くの米国政府機関のお客様のニーズを満たしますが、より厳しい要件を持つ機関は Azure Government クラウドに依存している可能性があります。 Azure Government は、人員のスクリーニングの強化など、追加のセーフガードを提供します。

Microsoft は、承認を維持するために、毎年クラウド サービスを再認定する必要があります。 これを行うために、Microsoft はセキュリティコントロールを継続的に監視および評価し、サービスのセキュリティが引き続き準拠していることを示します。 詳細については、 [Microsoft クラウド サービスの FedRAMP 承認](https://marketplace.fedramp.gov/)と [Microsoft FedRAMP 監査レポート](https://aka.ms/MicrosoftFedRAMPAuditDocuments)を参照してください。 他の FedRAMP レポートを受信するには、 [Azure Federal のドキュメント](mailto:AzFedDoc@microsoft.com)に電子メールを送信します。

FedRAMP 承認には複数のパスがあります。 Azure の既存の承認パッケージとここでのガイダンスを再利用して、ATO または P-ATO を取得するために必要な時間と労力を大幅に削減できます。

### ガイダンスの範囲

FedRAMP の高ベースラインは、 [NIST 800-53 Security Controls Catalog Revision 4](https://csrc.nist.gov/pubs/itlb/2015/01/release-of-nist-special-publication-80053a-revisio/final) の 421 個のコントロールとコントロールの機能強化で構成されています。 該当する場合は、 [800-53 リビジョン 5](https://csrc.nist.gov/publications/detail/sp/800-53/rev-5/final) の明確な情報が含まれています。 この記事セットでは、ID に関連し、構成する必要があるこれらのコントロールのサブセットについて説明します。

Microsoft Entra ID で構成する責任があるコントロールへの準拠を実現するために役立つ規範的なガイダンスを提供します。 一部の ID 制御要件に完全に対処するには、他のシステムを使用することが必要な場合があります。 その他のシステムには、Microsoft Sentinel などのセキュリティ情報とイベント管理ツールが含まれる場合があります。 Microsoft Entra ID の外部で Azure サービスを使用している場合は、考慮する必要がある他のコントロールが存在し、Azure が既に用意している機能を使用してコントロールを満たすことができます。

FedRAMP リソースの一覧を次に示します。

- [連邦リスクおよび承認管理プログラム](https://www.fedramp.gov/)
- [FedRAMP 承認のためのエージェンシー ガイド](https://www.fedramp.gov/assets/resources/documents/Agency_Authorization_Playbook.pdf)
- [Microsoft でのクラウドでのコンプライアンスの管理](https://www.microsoft.com/trustcenter/common-controls-hub)
- [Microsoft Government クラウド](https://go.microsoft.com/fwlink/p/?linkid=2087246)
- [Azure コンプライアンス サービス](https://aka.ms/azurecompliance)
- [FedRAMP High Azure Policy 組み込みイニシアチブ定義](https://learn.microsoft.com/ja-jp/azure/governance/policy/samples/fedramp-high)
- [Microsoft Purview ポータル](https://learn.microsoft.com/ja-jp/purview/purview-portal)
- [Microsoft Purview コンプライアンス マネージャー](https://learn.microsoft.com/ja-jp/purview/compliance-manager)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/fedramp-access-controls"} -->
## Microsoft Entra IDで FedRAMP High Impact レベルを満たすように ID アクセス制御を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/fedramp-access-controls
- Service: entra / standards
- Article date: 2023-05-23
- Summary: FedRAMP の影響度の高いレベルを満たすようにMicrosoft Entra IDアクセス制御を構成する方法に関する詳細なガイダンス。

アクセス制御は、 [連邦リスクおよび承認管理プログラム](https://www.fedramp.gov/) (FedRAMP) の影響度の高いレベルを実現するための主要な部分です。

アクセス制御 (AC) ファミリの制御と制御の強化の次の一覧では、Microsoft Entra テナントでの構成が必要になる場合があります。

| 制御ファミリー | 説明 |
| --- | --- |
| AC-2 | アカウント管理 |
| AC-6 | 最小特権 |
| AC-7 | ログオン試行の失敗 |
| AC-8 | システム使用通知 |
| AC-10 | 同時セッション制御 |
| AC-11 | セッションのロック |
| AC-12 | セッションの終了 |
| AC-20 | 外部情報システムの使用 |

次の表の各行に、制御または制御の強化のための共同責任に対する組織の対応を構築するために役立つ規範的なガイダンスを示します。

### Configurations

| FedRAMP コントロール ID と説明 | Microsoft Entraガイダンスと推奨事項 |
| --- | --- |
| **AC-2 アカウント管理**<br>**組織** **(a..)** 組織のミッション/ビジネス機能をサポートするために、次の種類の情報システム アカウントを識別して選択します。[*割り当て: 組織が定義した情報システム アカウントの種類*]。<br><br>**(b.)** 情報システム アカウントのアカウント マネージャーを割り当てます。<br><br>**(c.)** グループとロールのメンバーシップの条件を確立します。<br><br>**(d.)** 情報システムの承認されたユーザー、グループとロールのメンバーシップ、およびアクセス承認 (特権など) とその他の属性 (必要に応じて) をアカウントごとに指定します。<br><br>**(例:** 情報システム アカウントの作成要求に対する [*割り当て: 組織が定義した担当者またはロール*] による承認が必要です。<br><br>**(f.)** [*割り当て: 組織が定義した手順または条件*]に従って、情報システム アカウントを作成、有効化、変更、無効化、および削除します。<br><br>**(例:** 情報システム アカウントの使用を監視します。<br><br>**(h.)** アカウント マネージャーに通知します。(1.) アカウントが不要になった場合。(2.) ユーザーが終了または移転された場合。さらに(3.) 個々の情報システムの利用状況や知る必要がある情報が変更された場合。<br><br>**(i.)** 次に基づいて情報システムへのアクセスを承認します。(1.) 有効なアクセス認可。(2.) 意図されたシステムの使用。さらに(3.) 組織または関連するミッションおよびビジネス機能によって要求されるその他の特性。<br><br>**(j.)** アカウント管理要件への準拠についてアカウントをレビューします [*FedRAMP 割り当て: 特権アクセスの場合は毎月、非特権アクセスの場合は 6 か月ごと*]。そして<br><br>**(k.)** 個人がグループから削除されたときに、共有/グループ アカウントの資格情報 (展開されている場合) を再発行するプロセスを確立します。 | **お客様が管理するアカウントに対してアカウント ライフサイクル管理を実装します。 アカウントの使用状況を監視し、アカウント マネージャーにアカウント ライフサイクル イベントを通知します。 特権アクセスの場合は毎月、非特権アクセスの場合は 6 か月ごとに、アカウントがアカウント管理要件に準拠しているかどうかをレビューします。**<br>Microsoft Entra IDを使用して、外部人事システム、on-premises Active Directory、またはクラウド内で直接アカウントをプロビジョニングします。 すべてのアカウント ライフサイクル操作は、Microsoft Entra監査ログ内で監査されます。 Microsoft Sentinelなどのセキュリティ情報イベント管理 (SIEM) ソリューションを使用して、ログを収集および分析できます。 または、Azure Event Hubsを使用してログをサードパーティの SIEM ソリューションと統合して、監視と通知を有効にすることもできます。 Microsoft Entraエンタイトルメント管理とアクセス レビューを使用して、アカウントのコンプライアンス状態を確認します。<br><br>アカウントをプロビジョニングする<br>- [Microsoft Entraユーザープロビジョニングを計画するクラウドHRアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)<br>- [Microsoft Entra Connect Sync: 同期を理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)<br>- Microsoft Entra ID<br>アカウントを監視する<br>- [Microsoft Entra管理センターの監査アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)<br>- [Microsoft EntraのデータをMicrosoft Sentinelに接続します](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)<br>- [Tutorial: Azure イベント ハブにログをストリーム配信します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)<br>アカウントをレビューする<br>- [Microsoft Entraのエンタイトルメント管理とは何ですか？](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)<br>- [Microsoft Entra のエンタープライズ管理でアクセス パッケージのアクセス レビューを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create)<br>- [Microsoft Entra エンタイトルメント管理でアクセス パッケージのアクセスを確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-review-access)<br>リソース<br>- [Microsoft Entra ID の管理者ロールの権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)<br>- [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule) の動的グループ |
| **AC-2(1)**組織は、情報システム アカウントの管理を支援する自動メカニズムを採用します。 | **自動化されたメカニズムを採用して、顧客が管理するアカウントの管理をサポートします。**<br>外部人事システムまたはon-premises Active Directoryから顧客が管理するアカウントの自動プロビジョニングを構成します。 アプリケーション プロビジョニングをサポートするアプリケーションの場合は、ユーザーがアクセスする必要があるソリューション (SaaS) アプリケーションとしてクラウド ソフトウェアでユーザー ID とロールを自動的に作成するようにMicrosoft Entra IDを構成します。 自動プロビジョニングには、ユーザー ID の作成に加えて、状態または役割が変化したときのユーザー ID のメンテナンスおよび削除が含まれます。 アカウントの使用状況の監視を容易にするために、リスクの高いユーザー、危険なサインイン、リスク検出を示すMicrosoft Entra ID Protectionログをストリーミングし、ログをMicrosoft Sentinelまたは Event Hubs に直接監査できます。<br><br>プロビジョニング<br>- [Microsoft Entraユーザープロビジョニングを計画するクラウドHRアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)<br>- [Microsoft Entra Connect Sync: 同期を理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)<br>- [Microsoft Entra ID での SaaS アプリ ユーザーの自動プロビジョニングとは何ですか？](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)<br>- Microsoft Entra ID<br>監視と監査<br>- [調査リスク](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)<br>- [Microsoft Entra管理センターの監査アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)<br>- [Microsoft Sentinelは何ですか?](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)<br>- [Microsoft Sentinel: Microsoft Entra IDからデータを接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)<br>- [Tutorial: Microsoft Entra ログをAzureイベント ハブにストリーミングします](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |
| **AC-2(2)**情報システムは、[*FedRAMP の割り当て: 前回の使用から 24 時間後*]に自動的に一時アカウントと緊急アカウントを無効にします。 [*FedRAMP 選択: 無効にします*]<br>**AC-02(3)**情報システムは、[*FedRAMP 割り当て: ユーザー アカウントの 35 日]* の後、非アクティブなアカウントを自動的に無効にします。<br><br>**AC-2 (3) 追加の FedRAMP 要件とガイダンス:** **要件：** サービス プロバイダーは、非ユーザー アカウント (デバイスに関連付けられているアカウントなど) の期間を定義します。 期間は JAB/AO によって承認され、受け入れられます。 ユーザー管理がサービスの機能である場合は、コンシューマー ユーザーのアクティビティのレポートを利用できるようにする必要があります。 | **自動メカニズムを採用して、前回の使用から 24 時間後に一時アカウントと緊急アカウントを自動的に削除または無効化し、35 日間の非アクティブ状態の後に顧客が管理するすべてのアカウントを自動的に削除または無効化できるようにします。**<br>Microsoft GraphとMicrosoft Graph PowerShell を使用してアカウント管理の自動化を実装します。 Microsoft Graphを使用してサインインアクティビティを監視し、Microsoft Graph PowerShellを使って、必要な時間枠内でアカウントに対してアクションを実行します。 <br><br>非アクティブ状態を判断する<br>- Microsoft Entra ID<br>- 古いデバイスを Microsoft Entra ID<br>アカウントを削除または無効にする<br>- [Microsoft Graph のユーザーと協力する](https://learn.microsoft.com/ja-jp/graph/api/resources/users)<br>- [ユーザーの取得](https://learn.microsoft.com/ja-jp/graph/api/user-get?tabs=http)<br>- [ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update?tabs=http)<br>- [ユーザーの削除](https://learn.microsoft.com/ja-jp/graph/api/user-delete?tabs=http)<br>Microsoft Graphでデバイスを操作する<br>- [デバイスの取得](https://learn.microsoft.com/ja-jp/graph/api/device-get?tabs=http)<br>- [デバイスの更新](https://learn.microsoft.com/ja-jp/graph/api/device-update?tabs=http)<br>- [デバイスの削除](https://learn.microsoft.com/ja-jp/graph/api/device-delete?tabs=http)<br> Microsoft Graph PowerShell ドキュメントを参照してください。<br>- [Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser)<br>- [Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)<br>- [Get-MgDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdevice)<br>- [Update-MgDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdevice) |
| **AC-2(4)**情報システムは、アカウントの作成、変更、有効化、無効化、削除のアクションを自動的に監査し、[*FedRAMP 割り当て: 組織またはサービス プロバイダー のシステム所有者*] に通知します。 | **顧客が管理するアカウントを管理するライフサイクルに対して、自動化された監査および通知システムを実装します。**<br>アカウントの作成、変更、有効化、無効化、削除アクションなど、すべてのアカウント ライフサイクル操作は、Azure監査ログ内で監査されます。 通知に役立つログをMicrosoft Sentinelまたは Event Hubs に直接ストリーミングできます。<br><br>監査<br>- [Microsoft Entra管理センターの監査アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)<br>- [Microsoft Sentinel: Microsoft Entra IDからデータを接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)<br>通知<br>- [Microsoft Sentinelは何ですか?](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)<br>- [Tutorial: Microsoft Entra ログをAzureイベント ハブにストリーミングします](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |
| **AC-2(5)**組織では、[*FedRAMP Assignment: inactivity is exceed 15 (15) minutes]\(FedRAMP 割り当て: 非アクティブが 15 分) を超えると予想される*場合に、ユーザーがログアウトすることを要求します。<br>**AC-2 (5) 追加の FedRAMP 要件とガイダンス:** **指導：** AC-12 よりも短い時間枠を使用する必要がある | **非アクティブな状態が 15 分間続いた後、デバイスのログアウトを実装します。**<br>準拠デバイスへのアクセスを制限する条件付きアクセス ポリシーを使用することにより、デバイスのロックを実装します。 Intune などのモバイル デバイス管理 (MDM) ソリューションを使用して OS レベルでデバイスのロックを強制するように、デバイスのポリシー設定を構成します。 エンドポイント マネージャーまたはグループ ポリシー オブジェクトは、ハイブリッド デプロイで検討することもできます。 アンマネージド デバイスの場合は、ユーザーに再認証を強制するように、[サインインの頻度] 設定を構成します。<br><br>条件付きアクセス<br>- [デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)<br>- [ユーザー サインインの頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)<br>MDM ポリシー<br>- 画面がロックされ、ロックを解除するためのパスワードが必要になるまで、デバイスを最大で数分間非アクティブに構成します ([Android](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-android)、[iOS](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-ios)、[Windows 10](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-windows-10))。 |
| **AC-2(7)**<br>**組織:** **(a..)** 許可された情報システムのアクセスと特権をロールに編成するロールベースのアクセススキームに従って、特権ユーザー アカウントを確立および管理します。**(b)** 特権ロールの割り当てを監視する。そして**(c)** 特権ロール*の割り当てが適切でなくなった場合、[FedRAMP 割り当て: 組織が指定した期間内にアクセスを無効または取り消*します] を受け取ります。 | **お客様が管理するアカウントのロールベースのアクセス スキームに従って、特権ロールの割り当てを管理および監視します。 適切でなくなったアカウントの特権アクセスは無効化するか、取り消します。**<br>Microsoft Entra IDの特権ロールのアクセス レビューを含むMicrosoft Entra Privileged Identity Managementを実装して、ロールの割り当てを監視し、ロールの割り当てが不要になったら削除します。 監査ログをMicrosoft Sentinelまたは Event Hubs に直接ストリーミングして、監視に役立てることができます。<br><br>管理する<br>- [Microsoft Entra Privileged Identity Managementですか?](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)<br>- [アクティブ化の最大期間](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings?tabs=new)<br>モニター<br>- [Privileged Identity Management においてMicrosoft Entraロールのアクセスレビューを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)<br>- [Microsoft Entra のロールの監査履歴を Privileged Identity Management で表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-use-audit-log?tabs=new)<br>- [Microsoft Entra管理センターの監査アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)<br>- [Microsoft Sentinelは何ですか?](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)<br>- Microsoft Entra ID<br>- [Tutorial: Microsoft Entra ログをAzureイベント ハブにストリーミングします](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |
| **AC-2(11)**情報システムは、[*割り当て: 組織が定義した* *情報システム アカウント] に [割り当て: 組織が定義した*状況や使用条件] を適用します。 | **顧客が定義した条件または状況を満たすために、顧客が管理するアカウントの使用を強制します。**<br>ユーザーとデバイスの間でアクセス制御の決定を適用するする条件付きアクセス ポリシーを作成します。<br><br>条件付きアクセス<br>- [条件付きアクセス ポリシーを作成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)<br>- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) |
| **AC-2(12)**<br>**組織:** **(a)** [*割り当て: 組織が定義した非定型の使用*]の情報システム アカウントを監視します。そして**(b)** 情報システム アカウントの非定型的な使用状況を [*FedRAMP 割り当て: 少なくとも、組織内の ISSO および/または同様の役割*] に報告します。<br><br>**AC-2 (12) (a) および AC-2 (12) (b) FedRAMP の追加要件とガイダンス:** 特権アカウントの場合に必要です。 | **お客様が管理するアカウントを、非定型の使用に対する特権アクセスで監視およびレポートします。**<br>非定型の使用状況の監視については、リスクの高いユーザー、危険なサインイン、リスク検出を示すMicrosoft Entra ID Protectionログ、および特権の割り当てとの相関関係に役立つ監査ログを、Microsoft Sentinelなどの SIEM ソリューションに直接ストリーミングできます。 Event Hubs を使用して、ログをサードパーティ製の SIEM ソリューションと統合することもできます。<br><br>ID 保護<br>- [Microsoft Entra ID Protectionは何ですか?](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)<br>- [調査リスク](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)<br>- [Microsoft Entra ID Protection通知](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-notifications)<br>アカウントを監視する<br>- [Microsoft Sentinelは何ですか?](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)<br>- [Microsoft Entra管理センターの監査アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)<br>- [Microsoft EntraのデータをMicrosoft Sentinelに接続します](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)<br>- [Tutorial: Azure イベント ハブにログをストリーム配信します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |
| **AC-2(13)**組織は、[*FedRAMP 割り当て: 1 時間]* のリスク検出で重大なリスクを与えるユーザーのアカウントを無効にします。 | **1 時間以内に重大なリスクを引き起こすユーザーの顧客が管理するアカウントを無効にします。**<br>Microsoft Entra ID Protectionで、しきい値が [高] に設定されたユーザー リスク ポリシーを構成して有効にします。 危険なユーザーや危険なサインインに対するアクセスをブロックする条件付きアクセス ポリシーを作成します。ユーザーがその後のサインイン試行を自己修復およびブロック解除できるように、リスク ポリシーを構成します。<br><br>ID 保護<br>- [Microsoft Entra ID Protectionは何ですか?](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)<br>条件付きアクセス<br>- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)<br>- [条件付きアクセス ポリシーを作成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)<br>- [条件付きアクセス: ユーザー リスクベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user)<br>- [条件付きアクセス: サインイン リスクベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user)<br>- [リスク ポリシーを使用した自己修復](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock) |
| **AC-6(7)**<br>**組織:** **(a..)** [*FedRAMP 割り当て: 少なくとも年 1 回*] [*FedRAMP 割り当て: 特権を持つすべてのユーザー*] に割り当てられた特権を確認して、このような特権の必要性を検証します。そして**(b.)** 組織のミッション/ビジネス ニーズを正しく反映するために、必要に応じて特権を再割り当てまたは削除します。 | **特権アクセスを持つすべてのユーザーを毎年レビューおよび検証します。 組織のミッションとビジネス要件に合わせて、特権を再割り当て (または必要に応じて削除) するようにします。**<br>Microsoft Entraのエンタイトルメント管理とアクセス レビューを使用して、特権ユーザーの特権アクセスの必要性を確認します。 <br><br>アクセスレビュー<br>- [Microsoft Entraのエンタイトルメント管理とは何ですか？](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)<br>- [Privileged Identity Management においてMicrosoft Entraロールのアクセスレビューを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)<br>- [Microsoft Entra エンタイトルメント管理でアクセス パッケージのアクセスを確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-review-access) |
| **AC-7 ログイン試行の失敗**<br>**組織:** **(a.)** ユーザーによる連続する無効なログオン試行を、[*FedRAMP 割り当て: 3 回以下*] [*FedRAMP 割り当て: 15 分*] の間に制限します。そして**(b.)** [選択: [*FedRAMP 割り当て: 少なくとも 3 時間、または管理者によってロック解除されるまで] のアカウント/ノードを自動的にロックします。失敗した試行の最大数を超えると、[割り当て: 組織が定義した遅延アルゴリズム]に従って次のログオン プロンプトを遅延*します。 | **お客様がデプロイしたリソースに対するログイン試行の失敗は 15 分以内に連続して 3 回以下という制限を適用します。 最低 3 時間、または管理者によってロック解除されるまでアカウントをロックします。**<br>スマート ロックアウトのカスタム設定を有効にします。 これらの要件を実装するために、ロックアウトのしきい値とロックアウト期間 (秒単位) を構成します。 <br><br>スマート ロックアウト<br>- [Microsoft Entra スマート ロックアウトでユーザー アカウントを攻撃から保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)<br>- [Microsoft Entra スマート ロックアウト値を管理する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout) |
| **AC-8 システム使用通知**<br>**情報システム:** **(a.)** システムへのアクセスを許可する前に、[*割り当て: 組織が定義したシステムの使用通知メッセージまたはバナー (FedRAMP の割り当て: 追加の要件とガイダンスを参照してください)]*] をユーザーに表示し、適用される連邦法、執行命令、指令、ポリシー、規制、基準、およびガイダンスと一致するプライバシーとセキュリティに関する通知を提供し、以下を明示します。(1.) ユーザーが米国政府の情報システムにアクセスしています。(2.) 情報システムの使用状況を監視し、記録し、監査の対象とすることができます。(3.) 情報システムの認可されていない使用は禁止され、刑事および民事上の罰則の対象となります。さらに(4.) 情報システムの使用は、監視および記録に同意することを示します。<br><br>**(b.)** ユーザーが使用条件を確認し、情報システムにログオンしたり、さらにアクセスしたりするための明示的なアクションを実行するまで、通知メッセージまたはバナーを画面に保持します。そして<br><br>**(c.)** パブリックにアクセス可能なシステムの場合:(1.) システムの使用情報を表示し、さらにアクセス権を付与する前に、[*指定: 組織が定義した条件 (FedRAMP の指定: 追加の要件とガイダンスを参照してください)]*を表示します。(2.) 一般的に監視、記録、または監査を禁止するシステムでのプライバシーの尊重と矛盾しないこれらの活動への参照 (存在する場合) を表示します。さらに(3.) システムの認可された使用の説明を含みます。<br><br>**AC-8 追加の FedRAMP 要件とガイダンス:** **要件：** サービス プロバイダーは、システム使用通知コントロールを必要とするクラウド環境の要素を決定する必要があります。 システム使用通知を必要とするクラウド環境の要素は、JAB/AO によって承認され、受け入れられます。**要件：** サービス プロバイダーは、システム使用通知の検証方法を決定し、チェックの適切な周期性を提供します。 システム使用通知の検証と周期性は、JAB/AO によって承認され、受け入れられます。**指導：** 構成基準チェックの一部として実行する場合は、チェックされる設定と合格 (または失敗) チェックを必要とする項目の % を指定できます。**要件：** 構成基準チェックの一部として実行されない場合は、検証の結果を提供する方法と、サービス プロバイダーによる検証に必要な周期性に関する契約が文書化されている必要があります。 結果の検証方法に関する文書化された契約は、JAB/AO によって承認され、受け入れられます。 | **情報システムへのアクセスを許可する前に、プライバシーとセキュリティに関する通知を表示してユーザーに確認する必要があります。**<br>Microsoft Entra IDを使用すると、アクセス権を付与する前に受信確認を要求して記録するすべてのアプリに通知またはバナー メッセージを配信できます。 これらの利用規約ポリシーは、特定のユーザー (メンバーまたはゲスト) を対象とするように細かく設定できます。 また、条件付きアクセス ポリシーを使用して、アプリケーションごとにカスタマイズすることもできます。<br><br>使用条件<br>- [Microsoft Entra使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)<br>- [同意したユーザーと拒否したユーザーのレポートの表示](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use) |
| **AC-10 コンカレント セッション制御**情報システムでは、各 [*割り当て: 組織が定義したアカウントまたはアカウントの種類*] の同時セッションの数を [*FedRAMP 割り当て: 特権アクセス用に 3 つのセッション、非特権アクセス用に 2 つの (2) セッション]* に制限します。 | **特権アクセスの場合は同時セッションを 3 つのセッションに制限し、特権のないアクセスの場合は 2 つに制限します。**<br>現在、ユーザーは複数のデバイスからときには同時に接続します。 同時実行セッションを制限すると、ユーザー エクスペリエンスが劣化し、セキュリティの価値が制限されます。 この制御の背後にある意図に対処するためのより優れた方法は、ゼロ トラスト セキュリティ対策を採用することです。 条件は、セッションが作成される前に明示的に検証され、セッションが終了するまで継続的に検証されます。 <br><br>さらに、次の補完的な制御を使用します。 <br><br>条件付きアクセス ポリシーを使用して、準拠デバイスへのアクセスを制限します。 Intune などの MDM ソリューションを使用して、OS レベルでユーザー サインインの制限を適用するように、デバイスのポリシー設定を構成します。 エンドポイント マネージャーまたはグループ ポリシー オブジェクトは、ハイブリッド デプロイで検討することもできます。<br><br> Privileged Identity Managementを使用して、特権アカウントをさらに制限および制御します。 <br><br> 無効なサインイン試行に対するスマート アカウント ロックアウトを構成します。<br><br>**実装ガイダンス**<br><br>ゼロ トラスト<br>- [Zero Trust を使用した身元のセキュリティ](https://learn.microsoft.com/ja-jp/security/zero-trust/deploy/identity)<br>- Microsoft Entra ID における継続的なアクセス評価<br>条件付きアクセス<br>- [Microsoft Entra IDの条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)<br>- [デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)<br>- [ユーザー サインインの頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)<br>デバイスのポリシー<br>- [その他のスマート カード グループ ポリシー設定とレジストリ キー](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/smart-cards/smart-card-group-policy-and-registry-settings)<br>- [Microsoft Intuneの概要](https://learn.microsoft.com/ja-jp/mem/endpoint-manager-overview)<br>リソース<br>- [Microsoft Entra Privileged Identity Managementですか?](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)<br>- [Microsoft Entra スマート ロックアウトでユーザー アカウントを攻撃から保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)<br>セッションの再評価とリスク軽減のガイダンスの詳細については、AC-12 を参照してください。 |
| **AC-11 セッション ロック** **情報システム:** **(a)** 非アクティブが [*FedRAMP 割り当て: 15 分]* 経過するか、ユーザーからの要求を受信すると、セッションロックを開始し、システムへのさらなるアクセスを防止します。**(b)** 確立された識別および認証手順を使用してユーザーがアクセスを再確立するまで、セッション ロックを保持します。<br>**AC-11(1)**情報システムは、セッションのロックを使用して、以前はディスプレイに表示されていた情報 (パブリックに閲覧可能な画像を含む) を非表示にします。 | **非アクティブな状態が 15 分間続いた後、またはユーザーからの要求を受け取ったときのセッションのロックを実装します。 ユーザーが再度認証されるまでセッションのロックを保持します。 セッションのロックの開始時に、以前に表示されていた情報を非表示にします。**<br> 準拠デバイスへのアクセスを制限するために、条件付きアクセス ポリシーを使用して、デバイスのロックを実装します。 Intune などの MDM ソリューションを使用して OS レベルでデバイスのロックを強制するように、デバイスのポリシー設定を構成します。 エンドポイント マネージャーまたはグループ ポリシー オブジェクトは、ハイブリッド デプロイで検討することもできます。 アンマネージド デバイスの場合は、ユーザーに再認証を強制するように、[サインインの頻度] 設定を構成します。<br><br>条件付きアクセス<br>- [デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)<br>- [ユーザー サインインの頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)<br>MDM ポリシー<br>- 画面がロックされるまで、デバイスを非アクティブな状態 ([Android](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-android)、[iOS](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-ios)、[Windows 10](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-restrictions-windows-10)) まで構成します。 |
| **AC-12 セッション終了**情報システムは、[*割り当て: 組織が定義した条件またはセッション切断を必要とするイベントをトリガー*する] の後に、ユーザー セッションを自動的に終了します。 | **組織で定義された条件またはトリガー イベントが発生したときに、ユーザー セッションを自動的に終了します。**<br>リスクベースの条件付きアクセスや継続的アクセス評価などのMicrosoft Entra機能を使用して、自動ユーザー セッション再評価を実装します。 非アクティブ条件は、AC-11 で説明されているように、デバイス レベルで実装できます。<br><br>リソース<br>- [サインイン リスクベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-sign-in)<br>- [ユーザー リスクベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user)<br>- [継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) |
| **AC-12(1)** **情報システム:** **(a..)** 認証を使用して [割り当て: 組織が定義した情報リソース] へのアクセスを取得するたびに、ユーザーが開始する通信セッションのログアウト機能を提供します。そして**(b.)** 認証された通信セッションの信頼できる終了を示す明示的なログアウト メッセージをユーザーに表示します。<br>**AC-8 追加の FedRAMP 要件とガイダンス:** **指導：** ログアウト機能のテスト (OTG-SESS-006) [ログアウト機能のテスト](https://owasp.org/www-project-web-security-testing-guide/latest/4-Web_Application_Security_Testing/06-Session_Management_Testing/06-Testing_for_Logout_Functionality) | **すべてのセッションにログアウト機能を提供し、明示的なログアウト メッセージを表示します。**<br>表示されるすべてのMicrosoft Entra ID Web インターフェイスは、ユーザーが開始する通信セッションのログアウト機能を提供します。 SAML アプリケーションがMicrosoft Entra IDと統合されている場合は、シングル サインアウトを実装します。<br><br>ログアウト機能<br>- ユーザーが [\[すべての場所でサインアウト](https://aka.ms/mysignins)] を選択すると、現在発行されているすべてのトークンが取り消されます。 <br>メッセージの表示Microsoft Entra IDは、ユーザーが開始したログアウト後にメッセージを自動的に表示します。<br><br>[Image: アクセス制御メッセージを示すスクリーンショット。]<br><br>リソース<br>- [\[マイ Sign-Ins\] ページから最近のサインイン アクティビティを表示および検索する](https://support.microsoft.com/account-billing/view-your-work-or-school-account-sign-in-activity-from-my-sign-ins-9e7d108c-8e3f-42aa-ac3a-bca892898972)<br>- [シングルサインアウトSAMLプロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-out-saml-protocol) |
| **AC-20 外部情報システムの利用**組織は、外部の情報システムを所有、運用、保守する他の組織との間で確立した信頼関係と矛盾しないご契約条件を規定し、認可された個人が以下を行えるようにします:**(a..)** 外部情報システムから情報システムにアクセスする。そして**(b.)** 外部情報システムを使用して、組織が管理する情報を処理、格納、または送信します。<br>**AC-20(1)**組織が以下を行う場合に限り、組織は、認可された個人が外部情報システムを使用して情報システムにアクセスしたり、組織が管理する情報を処理、保存、または送信することを許可します。**(a..)** 組織の情報セキュリティ ポリシーとセキュリティ計画で指定されている外部システムでの必要なセキュリティ制御の実装を検証します。または**(b.)** 外部情報システムをホストする組織エンティティとの承認済みの情報システム接続または処理契約を保持します。 | **承認された個人が、管理されていないデバイスや外部ネットワークなどの外部情報システムから顧客がデプロイしたリソースにアクセスできるようにする使用条件を確立します。**<br>外部システムからリソースにアクセスする認可されたユーザーに、利用規約への同意を要求します。 外部システムからのアクセスを制限する条件付きアクセス ポリシーを実装します。 条件付きアクセス ポリシーは、Defender for Cloud Apps と統合して、外部システムからクラウドとオンプレミスのアプリケーションの制御を提供することもできます。 Intune のモバイル アプリケーション管理では、カスタム アプリやストア アプリなどのアプリケーション レベルで、外部システムとやり取りするマネージド デバイスから組織のデータを保護できます。 たとえば、クラウド サービスへのアクセスです。 組織で所有しているデバイスと個人のデバイスでアプリの管理を使用できます。<br><br>使用条件<br>- [利用規約: Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)<br>条件付きアクセス<br>- [デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)<br>- [条件付きアクセス ポリシーの条件: デバイスの状態 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions)<br>- [Microsoft Defender for Cloud Apps 条件付きアクセス アプリコントロールで保護する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/proxy-intro-aad)<br>- [Microsoft Entra の条件付きアクセスにおける場所条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)<br>MDM<br>- [Microsoft Intuneですか?](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)<br>- [Defender for Cloud Apps とは](https://learn.microsoft.com/ja-jp/cloud-app-security/what-is-cloud-app-security)<br>- [Microsoft Intune におけるアプリ管理とは何ですか？](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-management)<br>リソース<br>- [オンプレミス アプリと Defender for Cloud Apps の統合](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-microsoft-cloud-application-security) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/fedramp-identification-and-authentication-controls"} -->
## Microsoft Entra IDで FedRAMP High Impact レベルを満たすように識別と認証の制御を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/fedramp-identification-and-authentication-controls
- Service: entra / standards
- Article date: 2023-05-23
- Summary: FedRAMP High Impact レベルを満たすように識別と認証の制御を構成する方法についての詳細なガイダンス。

識別と認証は、 [連邦リスクおよび承認管理プログラム](https://www.fedramp.gov/) (FedRAMP) の影響度の高いレベルを達成するための鍵です。

ID および認証 (IA) ファミリのコントロールとコントロールの機能強化の次の一覧では、Microsoft Entra テナントでの構成が必要になる場合があります。

| 制御ファミリー | 説明 |
| --- | --- |
| IA-2 | 識別と認証 (組織のユーザー) |
| IA-3 | デバイスの識別と認証 |
| IA-4 | 識別子の管理 |
| IA-5 | 認証システムの管理 |
| IA-6 | 認証システムのフィードバック |
| IA-7 | 暗号化モジュールの認証 |
| IA-8 | 識別と認証 (組織外のユーザー) |

次の表の各行に、制御または制御の強化のための共同責任に対する組織の対応を構築するために役立つ規範的なガイダンスを示します。

### Configurations

| FedRAMP コントロール ID と説明 | Microsoft Entraガイダンスと推奨事項 |
| --- | --- |
| **IA-2 ユーザー識別と認証**情報システムでは、組織のユーザー (または、組織のユーザーに代わって行動する行為) を一意に識別し、認証する。 | **ユーザーに対して動作するユーザーまたはプロセスを一意に識別して認証します。**<br> Microsoft Entra IDは、ユーザー オブジェクトとサービス プリンシパル オブジェクトを直接一意に識別します。 Microsoft Entra IDには複数の認証方法が用意されており、米国国立標準技術研究所 (NIST) 認証保証レベル (AAL) 3 に準拠する方法を構成できます。<br><br>識別子 <br>- ユーザー: [Microsoft Graph 内のユーザーとの作業: ID プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/users)<br>- サービス プリンシパル: [ServicePrincipal リソースの種類 : ID プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal)<br>認証と多要素認証<br>- [Microsoft ID プラットフォームを使用した NIST 認証システムの保証レベルの実現](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview) |
| **IA-2(1)**情報システムでは、特権のあるアカウントへのネットワーク アクセスのための多要素認証を実装する。**IA-2(3)**情報システムでは、特権のあるアカウントへのローカル アクセスのための多要素認証を実装する。 | **特権アカウントへのすべてのアクセスに対する多要素認証。**<br>特権アカウントへのすべてのアクセスに多要素認証が必要になるように、完全なソリューションとして次の要素を構成します。<br><br>すべてのユーザーに多要素認証を要求する条件付きアクセス ポリシーを構成します。 使用する前に、特権ロールの割り当てのアクティブ化に多要素認証を要求するMicrosoft Entra Privileged Identity Managementを実装します。<br><br>Privileged Identity Managementアクティブ化要件では、ネットワーク アクセスがないと特権アカウントのアクティブ化ができないため、ローカル アクセスが特権を持つことはありません。<br><br>多要素認証とPrivileged Identity Management<br>- [条件付きアクセス: すべてのユーザーに多要素認証を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)<br>- [Privileged Identity Management で Microsoft Entra のロール設定を構成します](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings?tabs=new) |
| **IA-2(2)**情報システムでは、特権のないアカウントへのネットワーク アクセスのための多要素認証を実装する。**IA-2(4)**情報システムでは、特権のないアカウントへのローカル アクセスのための多要素認証を実装する。 | **特権のないアカウントへのすべてのアクセスに多要素認証を実装する**<br>非特権アカウントへのすべてのアクセスに MFA が必須になるように、全体的なソリューションとして次の要素を構成します。<br><br> すべてのユーザーに MFA を必須にするように、条件付きアクセス ポリシーを構成します。 MDM (Microsoft Intune など)、Microsoft Intune (MEM) またはグループ ポリシー オブジェクト (GPO) を使用してデバイス管理ポリシーを構成し、特定の認証方法の使用を強制します。 デバイスのコンプライアンスを適用するように、条件付きアクセス ポリシーを構成します。<br><br>多要素暗号化ハードウェア認証システム (FIDO2 セキュリティ キー、Windows Hello for Business (ハードウェア TPM 付き)、スマート カードなど) を使用して AAL3 を実現することをお勧めします。 組織がクラウドベースの場合は、FIDO2 セキュリティ キーまたはWindows Hello for Businessを使用することをお勧めします。<br><br>Windows Hello for Businessは、必要な FIPS 140 セキュリティ レベルで検証されていないため、連邦政府のお客様は、AAL3 として受け入れる前にリスク評価と評価を行う必要があります。 FIPS 140 検証Windows Hello for Businessの詳細については、「[Microsoft NIST AALs](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview)を参照してください。<br><br>MDM ポリシーに関する次のガイダンスを参照してください。認証方法によって若干異なります。 <br><br>スマートカード/Windows Hello for Business[Passwordless Strategy - Windows Hello for Businessまたはスマート カードが必要です](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/passwordless-strategy)[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス - すべてのユーザーに MFA を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)<br><br> ハイブリッドのみ[パスワードレス戦略 - パスワード認証を禁止するようにユーザー アカウントを構成する](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/passwordless-strategy)<br><br> スマートカードのみ[認証方法要求](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/create-a-rule-to-send-an-authentication-method-claim) を送信する規則を作成する[認証ポリシーを構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-authentication-policies)<br><br>FIDO2 セキュリティ キー[パスワードレス戦略 - パスワード資格情報プロバイダーを除外する](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/passwordless-strategy)[デバイスが準拠していると見なされる必要があります](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)[条件付きアクセス - すべてのユーザーに MFA を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)<br><br>認証方法[Microsoft Entra パスワードレス サインイン (プレビュー) |FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)[パスワードレス認証セキュリティキーサインインWindows - Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-windows)[ADFS: Microsoft Entra IDとOffice 365を使用した証明書認証](https://learn.microsoft.com/ja-jp/archive/blogs/samueld/adfs-certauth-aad-o365)スマート カードのサインインがWindows (Windows 10)[Windows Hello for Business概要 (Windows 10)](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/)<br><br>追加リソース[Policy CSP - Windows クライアント管理](https://learn.microsoft.com/ja-jp/windows/client-management/mdm/policy-configuration-service-provider) Microsoft Entra ID |
| **IA-2(5)**組織は、グループ認証子が使用されている場合、個人に個々の認証子で認証を受けることを要求する。 | **複数のユーザーが共有アカウントまたはグループ アカウントのパスワードにアクセスできる場合は、各ユーザーが最初に個々の認証システムを使用して認証を行う必要があります。**<br>ユーザーごとに個別のアカウントを使用します。 共有アカウントが必要な場合、Microsoft Entra IDは複数の認証子を 1 つのアカウントにバインドして、各ユーザーが個別の認証子を持つよう許可します。 <br><br>リソース<br>- [しくみ: 多要素認証のMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)<br>- [Microsoft Entra 多要素認証のための認証方法の管理](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userdevicesettings) |
| **IA-2(8)**情報システムでは、特権のあるアカウントへのネットワーク アクセスのための、リプレイに対する抵抗力のある認証メカニズムを実装する。 | **特権アカウントへのネットワーク アクセスに対してリプレイ耐性の認証メカニズムを実装します。**<br>すべてのユーザーに多要素認証を要求する条件付きアクセス ポリシーを構成します。 認証保証レベル 2 および 3 のすべてのMicrosoft Entra認証方法では、nonce またはチャレンジが使用され、リプレイ攻撃に対して耐性があります。<br><br>リファレンス<br>- [条件付きアクセス: すべてのユーザーに多要素認証を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)<br>- [Microsoft ID プラットフォームを使用した NIST 認証システムの保証レベルの実現](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview) |
| **IA-2(11)**情報システムは、特権アカウントと特権のないアカウントへのリモート アクセスのための多要素認証を実装します。これにより、アクセス権を取得するシステムとは別のデバイスによって要素の 1 つが提供され、デバイスは [*FedRAMP Assignment: FIPS 140-2, NIAP Certification,* or NSA approval\*] を満たします。\*ナショナル・インフォメーション・アシュアランス・パートナーシップ(NIAP)**追加の FedRAMP 要件とガイダンス:** **指導：** PIV = 独立したデバイス。 NIST SP 800-157 派生型個人識別情報 (PIV) 資格に関するガイドラインを参照してください。 FIPS 140-2 は、暗号化モジュール検証プログラム (CMVP) によって検証されることを意味します。 | **Microsoft Entra多要素認証を実装し、リモートで顧客が導入したリソースにアクセスできるようにします。この際、アクセスするシステムとは別のデバイスがフィルタ要素を提供し、そのデバイスが FIPS-140-2 認証、NIAP 認定、または NSA 承認を満たしていることが必要です。**<br>IA-02(1-4) のガイダンスを参照してください。 AAL3 で考慮すべきMicrosoft Entra認証方法は、個別のデバイス要件を満たします。<br><br> FIDO2 セキュリティキー<br>- ハードウェア TPM を使用したWindows Hello for Business (TPM は、NIST 800-63B セクション 5.1.7.1 によって有効な "持っているもの" 要素として認識されます)。<br>- スマート カード<br>リファレンス<br>- [Microsoft ID プラットフォームを使用した NIST 認証システムの保証レベルの実現](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview)<br>- [NIST 800-63B セクション 5.1.7.1](https://pages.nist.gov/800-63-3/sp800-63b.html) |
| \*\*IA-2(12)\*情報システムでは、個人の本人確認 (PIV) の資格情報を受け入れ、電子的に検証する。**IA-2 (12) 追加の FedRAMP 要件とガイダンス:** **指導：** 共通アクセス カード (CAC) 、つまり PIV/FIPS 201/HSPD-12 の DoD 技術実装を含めます。 | **本人確認 (PIV) 資格情報を受け入れて検証します。 お客様が PIV 資格情報をデプロイしない場合、この制御は適用されません。**<br>プライマリ認証と多要素認証方法の両方として PIV (証明書認証) を受け入れ、PIV を使用するときに多要素認証 (MultipleAuthN) 要求を発行するように、Active Directory Federation Services (AD FS) を使用してフェデレーション認証を構成します。 Microsoft Entra IDでフェデレーション ドメインを構成し、**federatedIdpMfaBehavior** を `enforceMfaByFederatedIdp` (推奨) に設定するか、SupportsMfa を `$True` に設定して、Microsoft Entra IDから送信される多要素認証要求をMicrosoft Entra IDに送信しますActive Directory Federation Services。 または、PIV を使用してWindowsデバイスにサインインし、後で統合されたWindows authenticationとシームレスなシングル サインオンを使用することもできます。 Windows Serverとクライアントは、認証に使用する場合、既定で証明書を確認します。 <br><br>リソース<br>- [Microsoft Entra IDとのフェデレーションは何ですか?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed)<br>- [ユーザー証明書認証の AD FS サポートを構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-user-certificate-authentication)<br>- [認証ポリシーを構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-authentication-policies)<br>- [Microsoft Entra の多要素認証と AD FS でリソースを保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-adfs)<br>- [New-MgDomainFederationConfiguration（新しいMgドメインフェデレーション構成）](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdomainfederationconfiguration)<br>- [Microsoft Entra 接続: シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) |
| **IA-3 デバイスの識別と認証**情報システムは、[*選択 (1 つ以上): ローカル、リモート、ネットワーク] 接続を確立する前に、[割り当て: 組織が定義した特定のデバイスまたは種類]* を一意に識別して認証します。 | **接続を確立する前に、デバイスの識別と認証を実装します。**<br>Microsoft Entra IDを構成して、Microsoft Entra登録済み、Microsoft Entra参加済み、およびハイブリッド参加済みのデバイスを識別して認証します。<br><br> リソース<br>- [デバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)<br>- [Microsoft Entra デバイスの展開を計画します](https://learn.microsoft.com/ja-jp/entra/identity/devices/plan-device-deployment)<br>- [条件付きアクセスを使用してクラウド アプリへのアクセスにマネージド デバイスを要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) |
| **IA-04 識別子の管理**組織は、ユーザーとデバイスの情報システム識別子を次の方法で管理する。(a.) [FedRAMPアサインメントの最小基準である場合、組織内のISSO（または同様の役割）]から承認を受け取り、個人、グループ、役割、またはデバイス識別子を割り当てる。**(b.)** 個人、グループ、ロール、またはデバイスを識別する識別子の選択。**(c.)** 目的の個人、グループ、ロール、またはデバイスに識別子を割り当てる。**(d.)** [*FedRAMP 割り当て: 少なくとも 2 年間*] のために識別子の再利用を防止すること。**(例:** [*FedRAMP 割り当て: 35 日後に識別子を無効にする (要件とガイダンスを参照)*]**IA-4e の追加 FedRAMP 要件およびガイダンス:** **要件：** サービス プロバイダーは、デバイス識別子の非アクティブな期間を定義します。**指導：** DoD クラウドについては、FedRAMP 以降の特定の DoD 要件については、DoD クラウド Web サイトを参照してください。**IA-4(4)**組織は、各個人を [*FedRAMP Assignment: contractors; foreign nationals*] として一意に識別することで、個々の識別子を管理します。 | **非アクティブな状態が 35 日間続いた後でアカウント識別子を無効にして、2 年間再利用できないようにします。 各個人を一意に識別することで個々の識別子を管理します (請負業者、外国人など)。**<br>AC-02 で定義されている既存の組織ポリシーに従って、Microsoft Entra IDの個々のアカウント識別子と状態を割り当てて管理します。 AC-02(3) に従って、非アクティブな状態が 35 日間続いた後で、ユーザーとデバイスのアカウントを自動的に無効にします。 組織のポリシーにより、少なくとも 2 年間は無効状態のままですべてのアカウントが維持されるようにします。 この時間が経過したら、削除することができます。 <br><br>非アクティブ状態を判断する<br>- Microsoft Entra ID<br>- 古いデバイスを Microsoft Entra ID<br>- [AC-02 ガイダンスを参照してください](https://learn.microsoft.com/ja-jp/entra/standards/fedramp-access-controls) |
| **IA-5 Authenticator Management**組織は、次の方法で情報システム認証子を管理する。**(a..)** 認証子の初期配布の一環として、認証子を受け取る個人、グループ、ロール、またはデバイスの ID を確認する。**(b.)** 組織によって定義された認証子の初期認証コンテンツを確立する。**(c.)** 認証子が意図した使用のためのメカニズムの十分な強度を持っていることを確認する。**(d.)** 最初の認証システムの配布、紛失/侵害または破損した認証子、および認証子の取り消しのための管理手順の確立と実装。**(例:** 情報システムのインストール前に認証子の既定のコンテンツを変更する。**(f.)** 認証子の最小および最大有効期間の制限と再利用条件の確立。**(例:** 認証子の変更/更新 [*割り当て: 認証子の種類別に組織が定義した期間*]。**(h.)** 認証子のコンテンツを不正な開示や変更から保護する。**(i.)** 認証子を保護するために、個人に特定のセキュリティ保護の実施を要求するとともに、デバイスでその実施を行うこと。**(j.)** これらのアカウントのメンバーシップが変更されたときに、グループ/ロール アカウントの認証子を変更する。**IA-5 追加の FedRAMP 要件とガイダンスについて:** **要件：** 認証子は、NIST SP 800-63-3 デジタル ID ガイドライン IAL、AAL、FAL レベル 3 に準拠している必要があります。 リンク https://pages.nist.gov/800-63-3 | **情報システム認証子を構成および管理します。**<br>Microsoft Entra IDでは、さまざまな認証方法がサポートされています。 既存の組織のポリシーを使用して管理することができます。 IA-02(1-4) での認証システムの選択に関するガイダンスを参照してください。 SSPR と Microsoft Entra多要素認証の組み合わせ登録でユーザーを有効にし、自己修復を容易にするために、少なくとも 2 つの許容可能な多要素認証方法を登録する必要があります。 認証方法 API を使用して、ユーザーが構成した認証システムをいつでも取り消すことができます。 <br><br>認証システムの強度/認証システムの内容の保護<br>- [Microsoft ID プラットフォームを使用した NIST 認証システムの保証レベルの実現](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview)<br>認証方法と統合された登録<br>- [Microsoft Entra IDで利用可能な認証方法と検証方法は何ですか？](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)<br>- [SSPR と Microsoft Entra 多要素認証の登録の組み合わせ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)<br>オーセンティケーターの無効化<br>- [Microsoft Entra 認証方法 API の概要](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationmethods-overview) |
| **IA-5(1)**情報システムでは、パスワード ベースの認証の場合、次のようにする。**(a.)** このポリシーは、[*割り当て: 組織が定義した大文字と小文字の区別を含む要件、文字数、各種の最小要件を含む大文字、小文字、数字、特殊文字の組み合わせ*] のパスワードの複雑性の最小要件を強制します。**(b.)** 新しいパスワードの作成時に、少なくとも次の変更された文字数を適用します: [*FedRAMP 割り当て: 少なくとも 50% (50%)*]。**(c.)** 暗号化で保護されたパスワードのみを格納および送信します。** (d.)[*割り当て: 組織が定義*した有効期間の最小値、有効期間の最大値] のパスワードの最小および最大有効期間の制限を適用します。**(例)\*\* [*FedRAMP の割り当て: 24 (24)*] 世代のパスワードの再利用を禁止します。そして**(f.)** システムログオン時に一時パスワードを使用し、その後直ちに永続的なパスワードに変更できます。**IA-5 (1) a 及び d の FedRAMP 追加要件とガイダンス:** **指導：** パスワード ポリシーが NIST SP 800-63B Memorized Secret (セクション 5.1.1) ガイダンスに準拠している場合、コントロールは準拠と見なされる可能性があります。 | **パスワードベースの認証要件を実装します。**<br>NIST SP 800-63B セクション 5.1.1 に準拠: 一般的に使用されたり、予想されたり、または侵害されたりするパスワードの一覧を保持します。<br><br>Microsoft Entraパスワード保護では、既定のグローバル禁止パスワード リストが、Microsoft Entra テナント内のすべてのユーザーに自動的に適用されます。 ビジネス ニーズやセキュリティ ニーズに対応するため、カスタムの禁止パスワード リストにエントリを定義できます。 ユーザーがパスワードを変更またはリセットすると、これらの禁止パスワード リストがチェックされ、強力なパスワードの使用が強制されます。<br><br>パスワードレスの戦略を強くお勧めします。 この制御はパスワード認証システムにのみ適用されるため、使用可能な認証システムとしてパスワードを削除すると、この制御は適用されません。<br><br>NIST のリファレンス ドキュメント<br>- [NIST 特別刊行物 800-63B](https://pages.nist.gov/800-63-3/sp800-63b.html)<br>- [NIST 特別出版物 800-53 リビジョン 5](https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-53r5.pdf) - IA-5 - コントロールの強化 (1)<br>リソース<br>- [Microsoft Entra パスワード保護を使用して不正なパスワードを排除する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad) |
| **IA-5(2)**情報システムでは、PKI ベースの認証の場合、次のようにする。**(a..)** 証明書の状態情報の確認を含む、受け入れられたトラスト アンカーへの認定パスを構築して検証することで、認定を検証します。**(b.)** 対応する秘密キーへの承認されたアクセスを強制します。**(c.)** 認証された ID を個人またはグループのアカウントにマップします。そして**(d.)** ネットワーク経由で失効情報にアクセスできない場合にパスの検出と検証をサポートするために、失効データのローカル キャッシュを実装します。 | **PKI ベースの認証要件を実装します。**<br>PKI ベースの認証を実装するために、AD FS を介してMicrosoft Entra IDをフェデレーションします。 既定では、AD FS は証明書を検証し、失効データをローカルにキャッシュし、ユーザーを Active Directory の認証済み ID にマップします。 <br><br> リソース<br>- [Microsoft Entra IDとのフェデレーションは何ですか?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed)<br>- [ユーザー証明書認証の AD FS サポートを構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-user-certificate-authentication) |
| **IA-5(4)**組織は、自動ツールを使用して、[*FedRAMP 割り当て: IA-5 (1) コントロール強化 (H) パート A で識別される複雑さ*] を満たすのに十分に強力なパスワード認証子があるかどうかを判断します。**IA-5(4) 追加の FedRAMP 要件とガイダンス:** **指導：** 作成時にパスワード認証機能の強度を適用する自動化されたメカニズムを使用しない場合は、作成されたパスワード認証子の強度を監査するために自動メカニズムを使用する必要があります。 | **自動ツールを使用して、パスワードの強度要件を検証します。**<br>Microsoft Entra IDは、作成時にパスワード認証機能の強度を適用する自動化されたメカニズムを実装します。 この自動化されたメカニズムを拡張して、on-premises Active Directoryにパスワード認証機能の強度を適用することもできます。 NIST 800-53 のリビジョン 5 では、IA-04(4) を廃止し、要件を IA-5(1) に組み込みました。<br><br>リソース<br>- [Microsoft Entra パスワード保護を使用して不正なパスワードを排除する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad)<br>- [Active Directory Domain Services の Microsoft Entra パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)<br>- [NIST 特別出版物 800-53 リビジョン 5](https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-53r5.pdf) - IA-5 - コントロールの強化 (4) |
| **IA-5(6)**組織は、認証子を使用することでアクセスが許可される情報のセキュリティ カテゴリに応じて、認証子を保護する。 | **FedRAMP の影響度の高いレベルで定義されている認証子を保護します。**<br>Microsoft Entra IDで認証子を保護する方法の詳細については、「[Microsoft Entra データ セキュリティに関する考慮事項](https://aka.ms/aaddatawhitepaper)を参照してください。 |
| **IA-05(7)**組織は、暗号化されていない静的認証子がアプリケーションやアクセス スクリプトに埋め込まれたりファンクション キーに格納されたりしないようにする。 | **暗号化されていない静的認証子 (パスワードなど) がアプリケーションに埋め込まれていないか、スクリプトにアクセスしたり、関数キーに格納されていないことを確認します。**<br>マネージド ID またはサービス プリンシパルのオブジェクト (証明書のみで構成済み) を実装します。<br><br>リソース<br>- [Azure リソースのマネージド ID は何ですか?](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)<br>- [ポータルでMicrosoft Entraアプリとサービス プリンシパルを作成します](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-service-principal-portal) |
| **IA-5(8)**組織は、[*FedRAMP 割り当て: 異なるシステム上の異なる認証子*] を実装して、複数の情報システムにアカウントを持つ個人による侵害のリスクを管理します。 | **個人が複数の情報システムにアカウントを持っている場合は、セキュリティ保護を実装します。**<br>複数の情報システムに個別のアカウントを持つのではなく、すべてのアプリケーションをMicrosoft Entra IDに接続してシングル サインオンを実装します。<br><br>[Azureのシングルサインオンとは何ですか?](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) |
| **IA-5(11)**情報システムは、ハードウェア トークン ベースの認証のために、[*割り当て: 組織が定義したトークン品質要件*] を満たすメカニズムを採用します。 | **FedRAMP High Impact レベルで必要なハードウェア トークン品質要件が必要です。**<br>AAL3 を満たすハードウェア トークンの使用を必須にします。<br><br>[Microsoft ID プラットフォームを使用した NIST 認証システムの保証レベルの実現](https://azure.microsoft.com/resources/microsoft-nist/) |
| **IA-5(13)**情報システムは、[*割り当て: 組織が定義した期間*]の後にキャッシュされた認証子の使用を禁止します。 | **キャッシュされた認証子の有効期限を適用します。**<br>ネットワークが利用できないときは、キャッシュされた認証システムが、ローカル マシンに対して認証を行うために使用されます。 キャッシュされた認証子の使用を制限するには、Windowsデバイスの使用を無効にするように構成します。 このアクションが不可能か実用的でない場合は、次の補完的な制御を使用します。<br><br>Office アプリケーションに適用される制限を使うことにより、条件付きアクセス セッション制御を構成します。 他のアプリケーションのアプリケーション制御を使うことにより、条件付きアクセスを構成します。<br><br>リソース<br>- [キャッシュする以前のログオンの対話型ログオン数](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/security-policy-settings/interactive-logon-number-of-previous-logons-to-cache-in-case-domain-controller-is-not-available)<br>- [条件付きアクセス ポリシーのセッション制御: アプリケーションによって適用される制限](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)<br>- [条件付きアクセス ポリシーのセッション制御: 条件付きアクセス アプリケーション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session) |
| **IA-6 認証者フィードバック**情報システムでは、悪用や認可されていない個人による使用の可能性から情報を保護するために、認証プロセス中は認証情報のフィードバックを隠す。 | **認証プロセス中に認証フィードバック情報を隠します。**<br>既定では、Microsoft Entra IDはすべての認証子フィードバックを隠します。 |
| **IA-7 暗号化モジュール認証**情報システムでは、該当の連邦法、行政命令、命令、ポリシー、規制、標準、このような認証のガイダンスの要件に対する暗号化モジュールの認証メカニズムを実装する。 | **適用される連邦法を満たす暗号化モジュールへの認証メカニズムを実装します。**<br>FedRAMP High Impact レベルでは、AAL3 認証システムが必要となります。 AAL3 でMicrosoft Entra IDによってサポートされているすべての認証子は、必要に応じてモジュールへのオペレーター アクセスを認証するメカニズムを提供します。 たとえば、ハードウェア TPM を使用したWindows Hello for Business展開では、TPM 所有者承認のレベルを構成します。<br><br> リソース<br>- 詳細については、IA-02 (2 および 4) を参照してください。<br>- [Microsoft ID プラットフォームを使用した NIST 認証システムの保証レベルの実現](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview)<br>- [TPM グループ ポリシーの設定](https://learn.microsoft.com/ja-jp/windows/security/hardware-security/tpm/trusted-platform-module-services-group-policy-settings) |
| **IA-8 識別と認証 (組織以外のユーザー)**情報システムは、組織外のユーザー (または、組織外のユーザーに代わって行動する行為) を一意に識別し、認証します。 | **情報システムは、非組織化ユーザー (または非組織ユーザーに対して動作するプロセス) を一意に識別して認証します。**<br>Microsoft Entra IDは、Federal Identity、Credential、Access Management (FICAM) で承認されたプロトコルを使用して、組織のテナントまたは外部ディレクトリに所属する組織以外のユーザーを一意に識別して認証します。<br><br>リソース<br>- [Microsoft Entra ID での B2B コラボレーションとは何ですか？](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)<br>- [B2B の ID プロバイダーとの直接フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)<br>- [B2B ゲスト ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties) |
| **IA-8(1)**情報システムでは、他の連邦機関の個人の本人確認 (PIV) の資格情報を受け入れ、電子的に検証する。**IA-8(4)**情報システムは、FICAM 発行のプロファイルに準拠する。 | **他の政府機関によって発行された PIV 資格情報を受け入れて検証します。 FICAM によって発行されたプロファイルに従います。**<br>フェデレーション (OIDC、SAML) または統合Windows authentication経由でローカルに PIV 資格情報を受け入れるようにMicrosoft Entra IDを構成します。<br><br>リソース<br>- [Microsoft Entra IDとのフェデレーションは何ですか?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed)<br>- [ユーザー証明書認証の AD FS サポートを構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-user-certificate-authentication)<br>- [Microsoft Entra ID での B2B コラボレーションとは何ですか？](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)<br>- [B2B の ID プロバイダーとの直接フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation) |
| **IA-8(2)**情報システムでは、FICAM で承認されたサード パーティの資格情報のみを受け入れる。 | **FICAM で承認された資格情報のみを受け入れます。**<br>Microsoft Entra IDでは、NIST AALs 1、2、および 3 で認証子がサポートされます。 アクセスするシステムのセキュリティ カテゴリに応じて、認証システムの使用を制限します。 <br><br>Microsoft Entra IDでは、さまざまな認証方法がサポートされています。<br><br>リソース<br>- [Microsoft Entra IDで利用可能な認証方法と検証方法は何ですか？](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)<br>- [Microsoft Entra 認証方法ポリシー API の概要](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationmethodspolicies-overview)<br>- [Microsoft ID プラットフォームを使用した NIST 認証システムの保証レベルの実現](https://azure.microsoft.com/resources/microsoft-nist/) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/fedramp-other-controls"} -->
## FedRAMP High Impact レベルを満たすようにその他の制御を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/fedramp-other-controls
- Service: entra / standards
- Article date: 2023-05-23
- Summary: FedRAMP High Impact レベルに対応するように追加のコントロールを構成する方法についての詳細なガイダンスです。

制御と制御の強化に関する次の一覧は、Microsoft Entra テナントでの構成が必要になる場合があります。

下の表の各行に、規範的なガイダンスが記載されています。 このガイダンスは、制御や制御の強化に関連する共同責任に対する組織の対応を構築するのに役立ちます。

### 監査とアカウンタビリティ

下の表のガイダンスは次の事柄に関連します。

- AU-2 監査イベント
- AU-3 監査の内容
- AU-6 監査の確認、分析、報告

| FedRAMP コントロール ID と説明 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **AU-2 監査イベント** **組織:** **(a.)** 情報システムで次のイベントを監査できることを決定する。["FedRAMP 割り当て: [成功および失敗したアカウント ログオン イベント、アカウント管理イベント、オブジェクト アクセス、ポリシー変更、特権機能、プロセス追跡、システム イベント。Web アプリケーションの場合: すべての管理者アクティビティ、認証チェック、認可チェック、データ削除、データ アクセス、データ変更、アクセス許可の変更"]。**(b.)** セキュリティ監査機能と、監査関連の情報を必要とする他の組織エンティティとの間を調整し、相互のサポートを強化して、監査可能なイベントの選択を支援する。**(c.)** 監査可能なイベントがセキュリティ インシデントの事後調査をサポートするのに十分であると見なされる理由の根拠を提供する。**(d.)** 次のイベントを情報システム内で監査することを決定する。["FedRAMP 割り当て: 識別されたイベントごとに継続的に監査される、AU-2 a. で定義された監査可能なイベントの、組織が定義したサブセット"]。**AU-2 追加の FedRAMP 要件とガイダンス:** **要件:** サービス プロバイダーとコンシューマー間の調整は、JAB/AO によって文書化され、受け入れられる必要がある。**AU-3 内容と監査レコード**情報システムでは、発生したイベントの種類、イベントが発生した日時、イベントが発生した場所、イベントのソース、イベントの結果、イベントに関連付けられた個人またはサブジェクトの ID を設定する情報を含む監査レコードを生成する。**AU-3(1)**情報システムでは、次の追加情報を含む監査レコードを生成する。["FedRAMP 割り当て: 組織が定義した追加の詳細情報"]。**AU-3 (1) 追加の FedRAMP 要件とガイダンス:** **要件:** サービス プロバイダーでは、監査レコードの種類 ["FedRAMP 割り当て: セッション、接続、トランザクション、またはアクティビティの期間; クライアントとサーバーのトランザクションの場合、受信バイト数と送信バイト数; イベントを診断または識別するための追加情報メッセージ; 操作対象のオブジェクトまたはリソースを記述または識別する特性; グループ アカウント ユーザーの個々の ID; 特権コマンドのフルテキスト"] を定義する。 監査レコードの種類は JAB/AO によって承認され、受け入れられる。**ガイダンス:** クライアントとサーバーのトランザクションの場合、送受信されたバイト数によって、調査や問い合わせ中に役立つ双方向の転送情報が提供される。**AU-3(2)**情報システムでは、["FedRAMP 割り当て: すべてのネットワーク、データ ストレージ、コンピューティング デバイス"] によって生成された監査レコードでキャプチャされる内容の一元管理と構成を可能にする。 | AU-2 パート a. で定義されたイベントをシステムで監査できることを確認し、 組織の監査可能イベントのサブセットに含まれるその他エンティティとの調整を行い、事後調査をサポートします。 監査レコードの集中管理を実装します。<br>すべてのアカウント ライフサイクル操作 (アカウントの作成、変更、有効化、無効化、削除の各アクション) は、Microsoft Entra 監査ログ内で監査されます。 すべての認証および認可イベントは Microsoft Entra サインイン ログ内で監査され、検出されたリスクがあれば Microsoft Entra ID 保護ログで監査されます。 これらの各ログは、Microsoft Sentinel などのセキュリティ情報イベント管理 (SIEM) ソリューションに直接ストリーム配信できます。 または、Azure Event Hubs を使用して、ログをサードパーティ製の SIEM ソリューションと統合します。<br><br>イベントを監査する<br>- [Microsoft Entra 管理センターでアクティビティ レポートを監査する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)<br>- [Microsoft Entra 管理センターのサインイン アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)<br>- [方法: リスクの調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)<br>SIEM の統合<br>- [Microsoft Sentinel: Microsoft Entra ID からデータを接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)<br>- [Azure イベント ハブやその他の SIEM へのストリーミング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |
| **AU-6 監査の確認、分析、報告** **組織:** **(a.)** ["FedRAMP 割り当て: 少なくとも毎週"]、情報システム監査レコードで ["割り当て: 組織が定義した不適切または異常なアクティビティ"] の兆候を確認および分析する。**(b.)** 調査結果を ["割り当て: 組織が定義した担当者またはロール"] に報告する。**AU-6 追加の FedRAMP 要件とガイダンス:** **要件:** サービス プロバイダーとコンシューマー間の調整は、認可担当者によって文書化され、受け入れられる必要がある。 マルチテナント環境では、コンシューマーに関連するデータの確認、分析、報告をコンシューマーに提供するための機能と手段を文書化する必要がある。**AU-6(1)**組織は、不審なアクティビティの調査と対応に関する組織のプロセスをサポートするために、監査の確認、分析、報告のプロセスを統合する自動化されたメカニズムを導入する。**AU-6(3)**組織は、異なるリポジトリ間の監査レコードを分析して関連付け、組織全体の状況を把握できるようにする。**AU-6(4)**情報システムでは、システム内の複数のコンポーネントからの監査レコードを集中的に確認および分析する機能を提供する。**AU-6(5)**組織は、監査レコードの分析と、["FedRAMP 選択 (1 つ以上): 脆弱性スキャン情報; パフォーマンス データ; 情報システム監視情報; 侵入テスト データ;" ["割り当て: 組織が定義した、他のソースから収集されたデータ/情報]] の分析を統合し、不適切または異常なアクティビティの識別能力をさらに強化する。**AU-6(6)**組織は、監査レコードからの情報と、物理アクセスの監視から得られた情報を関連付けて、疑わしい、不適切、異常、または悪意のあるアクティビティの識別能力をさらに強化する。**AU-6 追加の FedRAMP 要件とガイダンス:** **要件:** サービス プロバイダーとコンシューマー間の調整は、JAB/AO によって文書化され、受け入れられる必要がある。**AU-6(7)**組織は、監査情報の確認、分析、報告に関連付けられた [FedRAMP 選択 (1 つ以上): 情報システム プロセス; ロール; ユーザー] ごとに許可されるアクションを指定する。**AU-6(10)**組織は、法執行機関の情報、インテリジェンス情報、または他の信頼性の高い情報源に基づいてリスクに変更がある場合に、情報システム内での監査の確認、分析、報告のレベルを調整する。 | 少なくとも毎週 1 回監査レコードをレビューおよび分析し、不適切または異常なアクティビティを特定し、結果を適切な担当者にレポートします。 <br>前出の AU-02 および AU-03 のガイダンスにより、監査レコードの毎週のレビューと適切な担当者へのレポートが可能になります。 Microsoft Entra ID のみを使用してこれらの要件を満たすことはできません。 Microsoft Sentinel などの SIEM ソリューションも使用する必要があります。 詳細については、「[Microsoft Sentinel とは](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)」を参照してください。 |

### インシデント対応

下の表のガイダンスは次の事柄に関連します。

- IR-4 インシデント処理
- IR-5 インシデント監視

| FedRAMP コントロール ID と説明 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **IR-4 インシデント処理** **組織:** **(a.)** 組織は、準備、検出と分析、封じ込め、根絶、復旧を含むセキュリティ インシデントのインシデント処理機能を実装する。**(b.)** インシデント処理アクティビティをコンティンジェンシー計画アクティビティと調整する。**(c.)** 進行中のインシデント処理アクティビティから学習した教訓をインシデント対応手順、トレーニング、テストと演習に組み込み、それに応じて結果として得られた変更を実装する。**IR-4 追加の FedRAMP 要件とガイダンス:** **要件:** サービス プロバイダーは、インシデント処理を行う個人が、情報システムによって処理、格納、送信される情報の重要度/機密性に見合った人員のセキュリティ要件を確実に満たすようにする。**IR-04(1)**組織は、インシデント処理プロセスをサポートするための自動化されたメカニズムを使用する。**IR-04(2)**組織は、インシデント対応機能の一部として、["FedRAMP 割り当て: すべてのネットワーク、データ ストレージ、コンピューティング デバイス"] の動的再構成を含める。**IR-04(3)**組織は、["割り当て: 組織が定義したインシデントのクラス"] と ["割り当て: インシデントのクラスに対応して実行する、組織で定義されたアクション"] を識別して、組織のミッションおよびビジネス機能の継続性を確保する。**IR-04(4)**組織は、インシデントの認識および対応に関する組織全体にわたる観点を実現するために、インシデント情報と個々のインシデント対応を関連付ける。**IR-04(6)**組織は、内部関係者の脅威に対するインシデント処理機能を実装する。**IR-04(8)**組織は、内部関係者の脅威に対するインシデント処理機能を実装する。組織は、["FedRAMP 割り当て: コンシューマー インシデント対応者やネットワーク防御者を含む外部組織と、適切なコンシューマー インシデント対応チーム (CIRT)/コンピューター緊急対応チーム (CERT) (US-CERT、DoD CERT、IC CERT など)"] と連携し、["割り当て: 組織が定義したインシデント情報"] を関連付けて共有し、インシデント認識とより効果的なインシデント対応に関する組織間の観点を得る。**IR-05 インシデント監視**組織は、情報システム セキュリティ インシデントを追跡および文書化する。**IR-05(1)**組織は、セキュリティ インシデントの追跡やインシデント情報の収集および分析をサポートするための自動化されたメカニズムを使用する。 | インシデントの処理と監視の機能を実装します。 これには、自動インシデント処理、動的再構成、運用の継続性、情報の相関、インサイダーの脅威、外部組織との相関、インシデントの監視と自動追跡などがあります。 <br>監査ログには、構成の変更がすべて記録されます。 認証および認可イベントはサインイン ログ内で監査され、検出されたリスクはすべて ID保護 ログで監査されます。 これらのログはそれぞれ、Microsoft Sentinel などの SIEM ソリューションに直接ストリーム配信することができます。 または、Azure Event Hubs を使用して、ログをサードパーティ製の SIEM ソリューションと統合します。 Microsoft Graph や PowerShell を使用して、SIEM 内のイベントに基づいて動的再構成を自動化します。<br><br>イベントを監査する<br>- [Microsoft Entra 管理センターでアクティビティ レポートを監査する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)<br>- [Microsoft Entra 管理センターのサインイン アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)<br>- [方法: リスクの調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)<br>SIEM の統合<br>- [Microsoft Sentinel: Microsoft Entra ID からデータを接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)<br>- [Azure イベント ハブやその他の SIEM へのストリーミング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) |

### 人的セキュリティ

下の表のガイダンスは次の事柄に関連します。

- PS-4 職員の離職

| FedRAMP コントロール ID と説明 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| ** PS-4 職員の離職**組織は、個々の雇用の終了時に、次のようにする。**(a.)** ["FedRAMP 割り当て: 8 時間"] 以内に情報システムへのアクセスを無効にする。**(b.)** 個人に関連付けられているすべての認証子/資格情報を終了するか取り消す。**(c.)** ["割り当て: 組織が定義した情報セキュリティに関するトピック"] の議論を含む退職者面談を実施する。**(d.)** セキュリティに関連するすべての組織情報システム関連の使用権を回収する。**(e.)** 離職した個人が以前に管理していた組織の情報および情報システムへのアクセスを保持する。**(f.)** ["割り当て: 組織が定義した期間"] 内に ["割り当て: 組織が定義した職員または役割"] に通知する。**PS-4(2)**組織は、個人の雇用の終了時に ["FedRAMP 割り当て: システムへのアクセスを無効にするアクセス制御担当者"] に通知するための自動化されたメカニズムを利用する。 | システムへのアクセスの無効化に責任を負う担当者に自動的に通知します。 <br>8 時間以内に、アカウントを無効にし、関連付けられているすべての認証子と資格情報を取り消します。 <br><br>外部人事システムから、オンプレミスの Active Directory から、またはクラウド内で直接、Microsoft Entra ID のアカウントのプロビジョニング (離職時の無効化を含む) を構成します。 既存のセッションを取り消して、すべてのシステム アクセスを終了します。 <br><br>アカウント プロビジョニング<br>- AC-02 の詳細なガイダンスを参照してください。 <br>関連付けられているすべての認証子を取り消します<br>- [Microsoft Entra ID で緊急時にユーザー アクセスを取り消す](https://learn.microsoft.com/ja-jp/entra/identity/users/users-revoke-access) |

### システムと情報の整合性

下の表のガイダンスは次の事柄に関連します。

- SI-4 情報システムの監視

| FedRAMP コントロール ID と説明 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **SI-4 情報システムの監視** **組織:** **(a.)** 次のものを検出する情報システムを監視する。**(1.)** ["割り当て: 組織が定義した監視目標"] に従った攻撃および潜在的な攻撃のインジケーター。**(2.)** 認可されていないローカル、ネットワーク、リモート接続。**(b.)** ["割り当て: 組織が定義した技法と手法"] を使用して情報システムの不正な使用を識別する。**(c.)** 監視デバイスを (i) 情報システム内に戦略的にデプロイして組織が決定した重要な情報を収集し、(ii) システム内のアドホックな場所で組織にとって関心のある特定の種類のトランザクションを追跡する。**(d.)** 侵入監視ツールから取得した情報を、不正なアクセス、変更、削除から保護する。**(e.)** 法律実施情報、インテリジェンス情報、信頼性の高い他の情報源に基づいて組織の運営と資産、個人、他の組織、または国に対するリスクが上昇する兆候がある場合は常に、情報システム監視アクティビティのレベルを引き上げる。**(f.)** 該当する連邦法、大統領命令、指令、ポリシー、または規制に従って、情報システム監視アクティビティに関する法律専門家の意見を入手する。**(d.)** ["割り当て: 組織が定義した情報システム監視情報"] を ["選択 (1 つ以上): 必要に応じて; [割り当て: 組織が定義した頻度]] で、[割り当て: 組織が定義した担当者またはロール"] に提供する。**SI-4 追加の FedRAMP 要件とガイダンス:** **ガイダンス:** US-CERT インシデント対応レポート ガイドラインを参照。**SI-04(1)** 組織は、個別の侵入検出ツールを情報システム全体の侵入検出システムに接続して構成する。 | 情報システム全体の監視と侵入検出システムを実装します。 <br>すべての Microsoft Entra ログ (監査、サインイン、ID 保護) を情報システム監視ソリューション内に含めます。 <br><br>Microsoft Entra ログを SIEM ソリューションにストリーム配信します (IA-04 を参照)。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/hipaa-access-controls"} -->
## Microsoft Entra HIPAA アクセス制御のセーフガードを構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/hipaa-access-controls
- Service: entra / standards
- Article date: 2023-04-13
- Summary: Microsoft Entra HIPAA アクセス制御のセーフガードを構成する方法に関するガイダンス

Microsoft Entra ID では、1996 年の医療保険の携行性と責任に関する法律 (HIPAA) のセーフガードを実装するための ID 関連のプラクティス要件を満たしています。 HIPAA に準拠するには、このガイダンスを使用してセーフガードを実装します。 他の構成やプロセスの変更が必要になる場合があります。

**ユーザー ID のセーフガード**を理解するには、次のことを可能にする目的を調査して設定することをお勧めします。

- ドメインに接続する必要があるすべてのユーザーに ID が一意であることを確認します。
- Joiner、Mover、Leaver（JML）プロセスを確立します。
- ID 追跡の監査を有効にします。

**許可されているアクセス制御のセーフガード**の場合は、次のように目標を設定します。

- システム アクセスが、許可されているユーザーに限定されている。
- 許可されているユーザーが識別されている。
- 個人データへのアクセスが、許可されているユーザーに限定されている。

**緊急アクセス手順のセーフガード**の場合:

- コア サービスの高可用性を確保します。
- 単一障害点を排除します。
- ディザスター リカバリー プランを設定します。
- リスクの高いデータのバックアップを確保します。
- 緊急アクセス アカウントを確立して管理します。

**自動ログオフのセーフガード**の場合:

- 一定時間非アクティブになった後に電子セッションを終了する手順を確立します。
- 自動サインアウト ポリシーを構成して実装します。

### 一意のユーザー ID

次の表に、一意のユーザー ID に関する HIPAA ガイダンスからのアクセス制御のセーフガードを示します。 セーフガードの実装要件を満たすための Microsoft の推奨事項をご覧ください。

**HIPAA セーフガード - 一意のユーザー ID**

`Assign a unique name and/or number for identifying and tracking user identity.`

| 推奨 | アクション |
| --- | --- |
| ハイブリッドを設定して Microsoft Entra ID を利用する | [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express) では、オンプレミスのディレクトリを Microsoft Entra ID と統合し、オンプレミスのアプリケーションや Microsoft 365 などのクラウド サービスにアクセスするための単一 ID の使用をサポートします。 Active Directory (AD) と Microsoft Entra ID の間の同期を調整します。 Microsoft Entra Connect の使用を開始するには、前提条件を確認し、サーバーの要件と、管理のために Microsoft Entra テナントを準備する方法をメモします。[Microsoft Entra Connect 同期](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/tutorial-pilot-aadc-aadccp)は、クラウドで管理されるプロビジョニング エージェントです。 プロビジョニング エージェントでは、複数フォレストの切断された AD 環境からの Microsoft Entra ID への同期がサポートされています。 軽量エージェントがインストールされ、Microsoft Entra Connect で使用できます。**パスワード ハッシュ同期**を使用して、パスワードの数を減らし、漏洩した資格情報の検出から保護することをお勧めします。 |
| ユーザー アカウントを設定する | [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users) はクラウド ベースの ID およびアクセス管理サービスであり、セキュリティ攻撃から保護するためのシングル サインオン、多要素認証、条件付きアクセスを提供します。 ユーザー アカウントを作成するには、Microsoft Entra 管理センターに**ユーザー管理者**としてサインインし、メニューの [\[すべてのユーザー\]](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users) に移動して新しいアカウントを作成します。  Microsoft Entra ID を使用すると、システムとアプリケーションの自動ユーザー プロビジョニングのサポートが提供されます。 機能には、ユーザー アカウントの作成、更新、削除が含まれます。 自動プロビジョニングでは、新しいユーザーが組織のチームに参加するときに、適切なシステムに新しいアカウントが作成され、ユーザーがチームを離れると、自動プロビジョニング解除によって、アカウントが非アクティブ化されます。 プロビジョニングを構成するには、Microsoft Entra 管理センターに移動し、[\[エンタープライズ アプリケーション\]](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal) を選択して、アプリ設定を追加および管理します。 |
| 人事主導のプロビジョニング | 人事 (HR) システム内に [Microsoft Entra アカウント プロビジョニングを統合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)すると、過剰なアクセスやアクセスが不要になるリスクが軽減されます。 HR システムは、新規作成されたアカウントの権威開始点 (SOA) となり、アカウントのプロビジョニング解除機能を強化します。 自動化によって ID ライフサイクルが管理され、過剰プロビジョニングのリスクが軽減されます。 この方法は、最小限の特権アクセスを提供するというセキュリティのベスト プラクティスに従います。 |
| ライフサイクル ワークフローを作成する | [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows) は、joiner/mover/leaver (JML) ライフサイクルを自動化するための ID ガバナンスを提供します。 ライフサイクル ワークフローでは、[組み込みのテンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates)を使用するか、独自のカスタム ワークフローを作成して、ワークフロー プロセスを一元化します。 このプラクティスは、組織の JML 戦略要件の手動タスクを削減または削除するのに役立ちます。 Azure portal 内で、Microsoft Entra メニューの **ID ガバナンス** に移動して、組織の要件に適合するタスクを確認または構成します。 |
| 特権 ID を管理する | [Microsoft Entra Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使用すると、アクセスを管理、制御、監視できます。 必要に応じて、時間ベースおよび承認ベースのロールのアクティブ化でアクセス権を提供します。 この方法により、アクセス許可が過剰、不要、誤用されるリスクが制限されます。 |
| 監視とアラート | [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)では、リスク イベントや組織の ID に影響する潜在的な脆弱性に関する統合ビューを提供します。 保護を有効にすると、既存の Microsoft Entra 異常検出機能が適用され、リアルタイムで異常を検出するリスク イベントの種類が導入されます。 Microsoft Entra 管理センターを使用して、プロビジョニング ログのサインイン、監査、確認を行うことができます。ログは、セキュリティ情報イベント管理 (SIEM) ツールに[ダウンロード、アーカイブ、ストリーミング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs)できます。 Microsoft Entra ログは、Microsoft Entra メニューの [監視] セクションにあります。 接続されたデータにアラートを設定できる Azure Log Analytics ワークスペースを使用して、ログを [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations) に送信することもできます。  Microsoft Entra ID は、それぞれのディレクトリ オブジェクトの [ID プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/user?view=graph-rest-1.0&preserve-view=true)を使用して、ユーザーを一意に識別します。 この方法では、ログ ファイル内の特定の ID をフィルター処理できます。 |

### 許可されたアクセス制御

次の表に、許可されたアクセス制御のためのアクセス制御のセーフガードに関するHIPAAガイダンスを示します。 セーフガードの実装要件を満たすための Microsoft の推奨事項をご覧ください。

**HIPAA セーフガード - 許可されたアクセス制御**

`Person or entity authentication, implement procedures to verify that a person or entity seeking access to electronic protected health information is the one claimed.`

| 推奨 | アクション |
| --- | --- |
| 多要素認証 (MFA) を有効にする | [Microsoft Entra ID の MFA](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) では、別のセキュリティ層を追加して ID を保護します。 追加のレイヤーの認証は、許可されていないアクセスを防ぐのに効果的です。 MFA アプローチを使用すると、認証プロセス中にサインイン資格情報をより多く検証する必要があります。 たとえば、ワンクリック検証用に [Authenticator アプリ](https://support.microsoft.com/account-billing/set-up-an-authenticator-app-as-a-two-step-verification-method-2db39828-15e1-4614-b825-6e2b524e7c95)を設定したり、[パスワードレス認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)を有効にしたりできます。 |
| 条件付きアクセス ポリシーを有効にする | [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies) ポリシーは、承認されたアプリケーションへのアクセスを組織が制限するのに役立ちます。 Microsoft Entra では、ユーザー、デバイス、場所からのシグナルが分析されて決定が自動化され、リソースとデータへのアクセスに対して組織のポリシーが適用されます。 |
| ロール ベースのアクセス制御 (RBAC) を有効にする | [RBAC](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview) では、職務の分離という概念を持つエンタープライズ レベルのセキュリティを提供します。 RBAC を使用すると、システムと共に、リソースと機密データに対する機密性、プライバシー、アクセス管理を保護するためのアクセス許可を調整および確認できます。Microsoft Entra では、[組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)のサポートが提供されています。これは、変更できない固定のアクセス許可のセットです。 独自の[カスタム ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)を作成して、プリセット リストを追加することもできます。 |
| 属性ベースのアクセス制御 (ABAC) を有効にする | [ABAC](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview) は、セキュリティ プリンシパル、リソース、環境に関連付けられている属性に基づいてアクセスを定義する認可システムです。 きめ細かいアクセス制御を提供し、ロールの割り当ての数を減らします。 ABAC の使用範囲は、専用の Azure ストレージ内のコンテンツに設定できます。 |
| SharePoint でユーザー グループへのアクセスを構成する | [SharePoint グループ](https://learn.microsoft.com/ja-jp/sharepoint/dev/general-development/authorization-users-groups-and-the-object-model-in-sharepoint)は、ユーザーのコレクションです。 アクセス許可の範囲は、コンテンツにアクセスするためのサイト コレクション レベルです。 この制約の適用範囲は、アプリケーション間のデータ フロー アクセスを必要とするサービス アカウントに設定できます。 |

### 緊急アクセス手順

次の表に、緊急アクセス手順の HIPAA ガイダンス アクセス制御のセーフガードを示します。 セーフガードの実装要件を満たすための Microsoft の推奨事項をご覧ください。

**HIPAA セーフガード - 緊急アクセス手順**

`Establish (and implement as needed) procedures and policies for obtaining necessary electronic protected health information during an emergency or occurrence.`

| 推奨 | アクション |
| --- | --- |
| Azure Recovery Services を使用する | [Azure Backup](https://learn.microsoft.com/ja-jp/azure/backup/backup-architecture) では、重要で機密性の高いデータをバックアップするために必要なサポートが提供されます。 カバレッジには、クラウドへのオンプレミスの Windows デバイスと共に、ストレージ/データベースとクラウド インフラストラクチャが含まれます。 バックアップと回復プロセスのリスクに対処するための[バックアップ ポリシー](https://learn.microsoft.com/ja-jp/azure/backup/backup-architecture#backup-policy-essentials)を確立します。 データが安全に格納され、最小限のダウンタイムで取得できることを確認します。 Azure Site Recovery では、コピーが確実に同期されるように、ほぼ一定のデータ レプリケーションが提供されます。サービスを設定する前の初期手順は、組織の要件をサポートするために、目標復旧時点 (RPO) と目標復旧時間 (RTO) を決定することです。 |
| 回復性を確保する | [回復性](https://learn.microsoft.com/ja-jp/azure/architecture/framework/resiliency/overview)は、ビジネス運用とコア IT サービスが中断された場合にサービス レベルを維持するのに役立ちます。 この機能は、サービス、データ、Microsoft Entra ID、Active Directory に関する考慮事項にまたがっています。 Microsoft Entra とハイブリッド環境に依存するシステムとデータを含めるための戦略的[回復性計画](https://learn.microsoft.com/ja-jp/azure/architecture/checklist/resiliency-per-service)を決定します。 Exchange、SharePoint、OneDrive などのコア サービスに対応して、データの破損から保護し、回復性データ ポイントを適用して ePHI コンテンツを保護する [Microsoft 365 の回復性](https://learn.microsoft.com/ja-jp/compliance/assurance/assurance-sharepoint-onedrive-data-resiliency)。 |
| 緊急時用バックアップアカウントを作成する | 緊急または[非常時のアカウント](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)を確立すると、ネットワーク障害やその他の管理アクセスの損失の理由など、予期しない状況でもシステムとサービスにアクセスできます。 このアカウントを[個々のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)またはアカウントに関連付けないようにすることをお勧めします。 |

### ワークステーションのセキュリティ - 自動ログオフ

次の表に、自動ログオフのセーフガードに関する HIPAA ガイダンスを示します。 セーフガードの実装要件を満たすための Microsoft の推奨事項をご覧ください。

**HIPAA セーフガード - 自動ログオフ**

`Implement electronic procedures that terminate an electronic session after a predetermined time of inactivity.| Create a policy and procedure to determine the length of time that a user is allowed to stay logged on, after a predetermined period of inactivity.`

| 推奨 | アクション |
| --- | --- |
| グループ ポリシーを作成する | Microsoft Entra ID に移行されておらず、Intune によって管理されているデバイスのサポートとして、[グループ ポリシー (GPO)](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/manage-group-policy) は、AD またはハイブリッド環境のデバイスに対してサインアウトやロック画面の時間を強制的に適用できます。 |
| デバイス管理の前提要件を評価する | [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune) では、モバイル デバイス管理 (MDM) とモバイル アプリケーション管理 (MAM) を行います。 会社と個人のデバイスを制御できます。 デバイスの使用状況を管理し、ポリシーを適用してモバイル アプリケーションを制御できます。 |
| デバイスの条件付きアクセス ポリシー | [準拠している](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)デバイスまたは Microsoft Entra ハイブリッド参加済みデバイスへのアクセスを制限するために、条件付きアクセス ポリシーを使用してデバイスのロックを実装します。 [ポリシー設定](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant#require-hybrid-azure-ad-joined-device)を構成します。アンマネージド デバイスの場合は、ユーザーに再認証を強制するように、[\[サインインの頻度\]](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime) 設定を構成します。 |
| Microsoft 365 のセッション タイムアウトを構成する | Microsoft 365 アプリケーションとサービスの[セッション タイムアウト](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/idle-session-timeout-web-apps)を確認して、長時間のタイムアウトを修正します。 |
| Azure portal のセッション タイムアウトを構成する | [Azure portal セッションのセッション タイムアウト](https://learn.microsoft.com/ja-jp/azure/azure-portal/set-preferences)を確認します。非アクティブによるタイムアウトを実装すると、許可されていないアクセスからリソースを保護するのに役立ちます。 |
| アプリケーション アクセス セッションを確認する | [継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)ポリシーでは、アプリケーションへのアクセスを拒否または許可できます。 サインインが成功すると、ユーザーには 1 時間有効なアクセス トークンが付与されます。 アクセス トークンの有効期限が切れると、クライアントは Microsoft Entra に戻され、条件が再評価され、トークンはさらに 1 時間更新されます。 |

### 詳細情報

- [ゼロ トラストの重要な要素: ID、デバイス](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)
- [ゼロ トラストの重要な要素: ID、データ](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)
- [ゼロ トラストの重要な要素: デバイス、ID、アプリケーション](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/hipaa-audit-controls"} -->
## Microsoft Entra HIPAA 監査制御セーフガードを構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/hipaa-audit-controls
- Service: entra / standards
- Article date: 2023-04-13
- Summary: Microsoft Entra HIPAA 監査制御セーフガードを構成する方法に関するガイダンス

Microsoft Entra ID は、1996 年の医療保険の移植性とアカウンタビリティに関する法律 (HIPAA) セーフガードを実装するための ID 関連のプラクティス要件を満たしています。 HIPAA に準拠するには、他の必要な構成またはプロセスと共に、このガイダンスを使用してセーフガードを実装します。

監査コントロールの場合:

- 個人データ ストレージのデータ ガバナンスを確立します。
- 機密データを識別してラベル付けします。
- 監査収集とセキュリティで保護されたログ データを構成します。
- データ損失防止を構成します。
- 情報保護を有効にします。

セーフガードの場合:

- 保護された健康情報 (PHI) データが格納される場所を決定します。
- 格納されているデータのリスクを特定して軽減します。

この記事では、関連する HIPAA セーフガードの文言と、HIPAA コンプライアンスの実現に役立つ Microsoft の推奨事項とガイダンスを示す表を示します。

### 監査管理

次の内容は、HIPAA からのセーフガード ガイダンスです。 セーフガードの実装要件を満たすための Microsoft の推奨事項を確認します。

**HIPAA セーフガード - 監査コントロール**

`Implement hardware, software, and/or procedural mechanisms that record and examine activity in information systems that contain or use electronic protected health information.`

| 勧告 | アクション |
| --- | --- |
| Microsoft Purview を有効にする | [Microsoft Purview](https://learn.microsoft.com/ja-jp/purview/purview) は、データ ガバナンスを提供することで、データの管理と監視に役立ちます。 Purview を使用すると、コンプライアンス リスクを最小限に抑え、規制要件を満たすことができますガバナンス ポータルの Microsoft Purview には、オンプレミス、マルチクラウド、サービスとしてのソフトウェア (SaaS) データの管理に役立つ [統合データ ガバナンス](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/manage-data-governance) サービスが用意されていますMicrosoft Purview は、データの機密データ ライフサイクル保護とデータ損失防止の視覚化を提供するために連携する一連の製品であるフレームワークです。 |
| Microsoft Sentinel を有効にする | [Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview) は、セキュリティ情報とイベント管理 (SIEM) とセキュリティ オーケストレーション、自動化、応答 (SOAR) ソリューションを提供します。 Microsoft Sentinel は監査ログを収集し、組み込みの AI を使用して大量のデータを分析します。 SIEM を使用すると、組織は検出されない可能性のあるインシデントを検出できます。 |
| Azure Monitor を構成する | [Azure Monitor ログを使用すると、](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/data-security) ログが収集および整理され、クラウド環境とハイブリッド環境に拡張されます。 Azure セキュリティ センターと組み合わせてリソースを保護する方法に関する主要な領域に関する推奨事項を提供します。 |
| ログ記録と監視を有効にする | [ログ記録と監視](https://learn.microsoft.com/ja-jp/security/benchmark/azure/security-control-logging-monitoring) は、環境をセキュリティで保護するために不可欠です。 データは調査をサポートし、異常なパターンを特定することで潜在的な脅威を検出するのに役立ちます。 サービスのログ記録と監視を有効にして、不正アクセスのリスクを軽減します。[Microsoft Entra アクティビティ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)を監視することをお勧めします。 |
| 電子的に保護された健康情報 (ePHI) データをスキャンする環境 | [Microsoft Purview](https://learn.microsoft.com/ja-jp/azure/purview/overview) を監査モードで有効にして、データ資産に格納されている ePHI と、そのデータの格納に使用されているリソースをスキャンできます。 この機能は、データの機密性に基づいてデータ分類とラベル付けを確立するのに役立ちます。 |
| データ損失防止 (DLP) ポリシーを作成する | DLP ポリシーは、承認されていないユーザーによって機密データが失われたり、誤用されたり、アクセスされたりしないようにするプロセスを確立するのに役立ちます。 データ侵害と流出を防ぎます。[Microsoft Purview DLP](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/dlp-policy-reference) は電子メール メッセージを調べ、Microsoft Purview ポータルに移動してポリシーを確認し、組織向けにカスタマイズします。 |
| Azure Policy を使用して監視を有効にする | [Azure Policy は](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview) 、組織の標準を適用するのに役立ち、環境全体のコンプライアンスの状態を評価できます。 このアプローチにより、[Microsoft Defender for Cloud](https://learn.microsoft.com/ja-jp/azure/defender-for-cloud/defender-for-cloud-introduction) を通じてセキュリティに関する推奨事項を提供することで、一貫性、規制コンプライアンス、監視が保証されます |
| デバイス管理の要件を評価する | [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/) を使用して、モバイル デバイス管理 (MDM) とモバイル アプリケーション管理 (MAM) を提供できます。 Microsoft Intune では、会社と個人のデバイスを制御できます。 機能には、デバイスの使用方法の管理や、モバイル アプリケーションを直接制御できるポリシーの適用が含まれます。 |
| アプリケーション保護 | Microsoft Intune は、Microsoft 365 Office アプリケーションを対象とする [データ保護フレームワーク](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-protection-policy) を確立し、デバイス間でそれらを組み込むのに役立ちます。 アプリ保護ポリシーにより、組織のデータが安全であり、個人 (BYOD) から企業所有のデバイスの両方にアプリに含まれていることが保証されます。 |
| インサイダー リスク管理を構成する | Microsoft Purview [Insider Risk Management](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/insider-risk-management-solution-overview) は、シグナルを関連付けて、IP 盗難、データ漏えい、セキュリティ違反など、悪意のある、または不注意な内部関係者の潜在的なリスクを特定します。 Insider Risk Management を使用すると、セキュリティとコンプライアンスを管理するポリシーを作成できます。 この機能は、設計上のプライバシーの原則に基づいて構築され、ユーザーは既定で仮名化され、ユーザー レベルのプライバシーを確保するためにロールベースのアクセス制御と監査ログが用意されています。 |
| 通信コンプライアンスを構成する | Microsoft Purview [Communication Compliance](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/communication-compliance-solution-overview) は、組織が証券取引委員会 (SEC) や金融業界規制機関 (FINRA) 標準のコンプライアンスなどの規制コンプライアンスを検出するのに役立つツールを提供します。 このツールは、機密情報や機密情報、嫌がらせや脅迫的な言葉使い、成人向けコンテンツの共有などのビジネス行為違反を監視します。 この機能は、設計によってプライバシーを使用して構築され、ユーザー名は既定で仮名化され、ロールベースのアクセス制御が組み込まれており、調査担当者は管理者によってオプトインされ、監査ログはユーザー レベルのプライバシーを確保するために用意されています。 |

### コントロールを保護する

次のコンテンツでは、HIPAA からのセーフガード制御のガイダンスを提供します。 HIPAA コンプライアンスを満たすための Microsoft の推奨事項を確認します。

**HIPAA - セーフガード**

`Conduct an accurate and thorough safeguard of the potential risks and vulnerabilities to the confidentiality, integrity, and availability of electronic protected health information held by the covered entity.`

| 勧告 | アクション |
| --- | --- |
| ePHI データのスキャン環境 | [Microsoft Purview](https://learn.microsoft.com/ja-jp/purview/governance-solutions-overview) を監査モードで有効にして、データ資産に格納されている ePHI と、そのデータの格納に使用されているリソースをスキャンできます。 この情報は、データ分類を確立し、データの秘密度にラベルを付けるのに役立ちます。さらに、 [コンテンツ エクスプローラー](https://learn.microsoft.com/ja-jp/purview/data-classification-content-explorer) を使用すると、機密データが配置されている場所を可視化できます。 この情報は、クライアント側のラベル付けまたはラベル付けの推奨事項を手動でサービス側の自動ラベル付けに適用する方法から、ラベル付けの取り組みを開始するのに役立ちます。 |
| Priva を有効にして Microsoft 365 データを保護する | [Microsoft Priva](https://learn.microsoft.com/ja-jp/privacy/priva/priva-overview) は、Microsoft 365 に格納されている ePHI データを評価し、機密情報のスキャンと評価を行います。 |
| Azure セキュリティ ベンチマークを有効にする | [Microsoft クラウド セキュリティ ベンチマーク](https://learn.microsoft.com/ja-jp/security/benchmark/azure/introduction) は、Azure サービス全体のデータ保護を制御し、ePHI を格納するサービスの実装のベースラインを提供します。 監査モードでは、環境をセキュリティで保護するための推奨事項と修復手順が提供されます。 |
| Defender 脆弱性管理を有効にする | [Microsoft Defender 脆弱性管理](https://learn.microsoft.com/ja-jp/azure/defender-for-cloud/remediate-vulnerability-findings-vm) は、 **Microsoft Defender for Endpoint** の組み込みモジュールです。 このモジュールは、脆弱性や構成の誤りをリアルタイムで特定して検出するのに役立ちます。 また、このモジュールは、ダッシュボードで結果を提示する優先順位を付け、デバイス、VM、データベース間でレポートを作成するのにも役立ちます。 |

### 詳細情報

- [ゼロ トラストの柱: デバイス、データ、アプリケーション、可視性、自動化、オーケストレーション](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)
- [ゼロ トラストの柱: データ、可視性、自動化、オーケストレーション](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/hipaa-configure-for-compliance"} -->
## HIPAA コンプライアンスの Microsoft Entra ID を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/hipaa-configure-for-compliance
- Service: entra / standards
- Article date: 2023-04-13
- Summary: HIPAA コンプライアンス レベルに対応して Microsoft Entra ID を構成する方法に関するガイダンスの概要。

Microsoft Entra ID などの Microsoft サービスによって、1996 年の医療保険の携行性と責任に関する法律 (HIPAA) の ID 関連の要件を満たすことができます。

HIPAA セキュリティ規則 (HSR) は、対象となるエンティティによって作成、受信、使用、または維持される個人の電子個人情報を保護するための基準を定めます。 HSR は米国保健福祉省 (HHS) によって管理されており、電子保護された健康情報の機密性、整合性、セキュリティを確保するために適切な管理、物理、技術的セーフガードが必要です。

技術的セーフガードの要件と目標は、連邦規則集 (CDR) のタイトル 45 で定義されています。 タイトル 45 のパート 160 では一般的な管理要件を提供し、パート 164 のサブパート A と C ではセキュリティとプライバシーの要件について記述しています。

サブパート § 164.304 では、技術的セーフガードをテクノロジとして定義し、電子保護された健康情報を保護し、それに対するアクセスを制御する、その使用に関するポリシーと手続きを定義しています。 また、HHS では、医療機関が HIPAA 技術的セーフガードを実装する際に考慮すべき重要な分野の概要も示しています。 [§ 164.312 技術的セーフガード](https://www.ecfr.gov/current/title-45/section-164.312)から:

- **アクセス制御** - [§164.308(a)(4)](https://www.ecfr.gov/current/title-45/section-164.308) に規定されているように、アクセス権が付与されている個人またはソフトウェア プログラムにのみアクセスできるように、電子保護された健康情報を保持する電子情報システムの技術ポリシーと手続きを実装します。
- **監査制御** - 電子保護された健康情報を格納しているか使用する情報システムでアクティビティを記録および調査するハードウェア、ソフトウェア、手続き型メカニズムを実装します。
- **整合性制御** - 電子保護された健康情報を不適切な変更や破壊から保護するためのポリシーと手続きを実装します。
- **個人またはエンティティの認証** - 電子保護された健康情報へのアクセスを求める個人またはエンティティは、要求する資格があることを確認する手続きを実装します。
- **伝送のセキュリティ** - 電子通信ネットワークを介して伝送される電子保護された健康情報への不正アクセスに対して保護する技術的なセキュリティ対策を実装します。

HSR では、必須のアドレス指定可能な実装仕様と共に、サブパートを標準として定義しています。 すべて実装する必要があります。 "アドレス指定可能" の指定は、仕様が理にかなっており、適切であることを示します。 アドレス指定可能とは、実装仕様が省略可能であることを意味するものではありません。 そのため、アドレス指定可能として定義されているサブパートも必須です。

このシリーズの残りの記事では、主要分野と技術的セーフガード別に整理されたガイダンスとリソースへのリンクを提供します。 主要分野ごとに、関連するセーフガードが一覧表示され、セーフガードを実現するための Microsoft Entra ガイダンスのリンクがあります。

### 詳細情報

- [医療における HHS ゼロ トラスト pdf](https://www.hhs.gov/sites/default/files/zero-trust.pdf)
- 45 CFR 160、162、および 164 で見つかったすべての HIPAA Administrative Simplification Regulations の[統合された規制テキスト](https://www.hhs.gov/hipaa/for-professionals/privacy/laws-regulations/combined-regulation-text/index.html)
- 規制の公共福祉部分を記述した[連邦規則 (CFR) のタイトル 45](https://www.ecfr.gov/current/title-45)
- タイトル 45 の一般的な管理要件を記述した[パート 160](https://www.ecfr.gov/current/title-45/subtitle-A/subchapter-C/part-160?toc=1)
- タイトル 45 のセキュリティとプライバシーの要件を記述した[パート 164](https://www.ecfr.gov/current/title-45/subtitle-A/subchapter-C/part-164) のサブパート A および C
- [HIPAA セキュリティ リスク セーフガード ツール](https://www.healthit.gov/topic/privacy-security-and-hipaa/security-risk-assessment-tool)
- [NIST HSR Toolkit](http://scap.nist.gov/hipaa/)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/hipaa-hitrust-controls"} -->
## HITRUST コントロールの Microsoft Entra 構成に関する推奨事項 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/hipaa-hitrust-controls
- Service: entra / standards
- Article date: 2024-03-19
- Summary: HITRUST コントロールに合わせたサービスと機能の詳細および推奨事項をナビゲートするためのガイダンス

この記事のガイダンスは、詳細に移動するのに役立ち、HITRUST コントロールとの連携をサポートするために Microsoft Entra ID のサービスと機能に関する推奨事項を提供します。 この情報を使用して、医療情報信頼アライアンス (HITRUST) フレームワークを理解し、組織が 1996 年の医療保険の移植性とアカウンタビリティに関する法律 (HIPAA) に準拠していることを確認する責任をサポートします。 評価には、フレームワークに関する知識があり、プロセスのガイドと要件の理解に役立つ必要がある、認定された HITRUST 評価者との連携が含まれます。

**アクロニム**

次の表に、この記事の頭字語とそのスペルを示します。

| 頭字語 | スペリング |
| --- | --- |
| 西暦 | 対象エンティティ |
| 脳脊髄液 | 一般的なセキュリティ フレームワーク |
| HIPAA | 1996年医療保険の携行性と説明責任に関する法律 |
| 高速鉄道 | HIPAA セキュリティ規則 |
| HITRUST（ヒートラスト） | ヘルス・インフォメーション・トラスト・アライアンス |
| IAM | ID およびアクセス管理 |
| IdP | ID プロバイダー |
| ISO | 国際標準化機構 |
| ISMS | 情報セキュリティ管理システム |
| JEA | 十分なアクセス |
| JML | 参加、移動、退出 |
| MFA | Microsoft Entra 多要素認証 |
| NIST | 米国商務省国立標準技術研究所 |
| ファイ (Φ) | 保護された健康情報 |
| PIM | Privileged Identity Management |
| SSO | 単一サインイン |
| TAP | 一時的なアクセス パス |

### ヘルス・インフォメーション・トラスト・アライアンス

HITRUST 組織は、医療業界の組織のセキュリティとプライバシーの要件を標準化および合理化するために、共通セキュリティ フレームワーク (CSF) を確立しました。 HITRUST CSFは、個人データおよび保護された健康情報(PHI)データを取り扱う際に組織が直面する複雑な規制環境、セキュリティの課題、プライバシーに関する懸念に対処するために、2007年に設立されました。 CSF は、49 個の制御目標と 156 個の制御仕様で構成される 14 の制御カテゴリで構成されています。 国際標準化機構 (ISO) 27001 および ISO 27002 の主要な原則に基づいて構築されました。

HITRUST MyCSF ツールは [、Azure Marketplace](https://azuremarketplace.microsoft.com/marketplace/apps/hitrust1666274640786.hitrust?exp=ubp8&amp;tab=Overview) で入手できます。 これを使用して、情報セキュリティ リスク、データ ガバナンスを管理し、情報保護規則に準拠し、国内および国際標準とベスト プラクティスにも準拠します。

注

ISO 27001 は、情報セキュリティ管理システム (ISMS) の要件を指定する管理標準です。 ISO 27002 は、ISO 27001 フレームワークでセキュリティ コントロールを選択して実装するためのベスト プラクティスのセットです。

### HIPAA セキュリティ規則

[HIPAA セキュリティ 規則 (HSR)](https://www.hhs.gov/hipaa/for-professionals/security/index.html#:%7E:text=The%20HIPAA%20Security%20Rule%20establishes,maintained%20by%20a%20covered%20entity.) は、医療計画、医療クリアリングハウス、または医療プロバイダーである対象エンティティ (CE) によって作成、受信、使用、または管理されている個人の電子個人の健康情報を保護するための基準を確立します。 米国保健福祉省 (HHS) が HSR を管理しています。 HHS では、電子 PHI の機密性、整合性、およびセキュリティを確保するために、管理上、物理的、技術的な保護が必要です。

### HITRUST と HIPAA

HITRUSTは、医療規制をサポートするためのセキュリティおよびプライバシー基準を含むCSFを開発しました。 CSF の制御とベスト プラクティスにより、ソースを統合して、連邦法、HIPAA セキュリティ、プライバシー規則への準拠を確保するタスクが簡略化されます。 HITRUST CSF は、HIPAA コンプライアンスを実証するための制御と要件を備えた、認定可能なセキュリティとプライバシーのフレームワークです。 医療機関は、このフレームワークを広く採用しました。 コントロールの詳細については、次の表を参照してください。

| 制御カテゴリー | 制御カテゴリ名 |
| --- | --- |
| 0 | 情報セキュリティ管理プログラム |
| 1 | Access Control |
| 2 | 人事セキュリティ |
| 3 | リスク管理 |
| 4 | セキュリティ ポリシー |
| 5 | 情報セキュリティ組織 |
| 6 | コンプライアンス |
| 7 | アセット管理 |
| 8 | 物理的および環境的セキュリティ |
| 9 | 通信と運用管理 |
| 10 | 情報システムの取得、開発、保守 |
| 11 | 情報セキュリティ インシデント管理 |
| 12 | ビジネス継続性管理 |
| 13 | プライバシーに関するプラクティス |

Microsoft Azure プラットフォームが HITRUST CSF に認定されていることについて、ID とアクセス管理も含めてさらに詳しく知るには、[こちら](https://azure.microsoft.com/blog/microsoft-azure-achieves-hitrust-csf-certification/)をご覧ください。

- [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra) (旧称 Azure Active Directory)
- [Microsoft Purview](https://techcommunity.microsoft.com/t5/healthcare-and-life-sciences/microsoft-purview-compliance-score-part-3-hitrust/ba-p/3614103) を使用した権利管理
- [Microsoft Entra 多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)

### アクセス制御のカテゴリと推奨事項

次の表に、ID とアクセス管理 (IAM) のアクセス制御カテゴリと、制御カテゴリの要件を満たすために役立つ Microsoft Entra の推奨事項を示します。 詳細は、対応するコントロールに追加された HIPAA セキュリティ規則を参照する HITRUST [MyCSF v11](https://hitrustalliance.net/mycsf) に由来します。

| HITRUST の制御、目的、および HSR | Microsoft Entra のガイダンスと推奨事項 |
| --- | --- |
| **CSF コントロール V11**01.b ユーザー登録**コントロール カテゴリ**アクセス制御 – ユーザー登録と De-Registration**コントロールの仕様**組織は、正式なユーザー登録と登録解除プロセスを使用して、アクセス権の割り当てを有効にします。**目標名**情報システムへの承認されたアクセス**HIPAA セキュリティ規則**§ 164.308(a)(3)(ii)(A)§ 164.308(a)(4)(i)§ 164.308(a)(3)(ii)(B)§ 164.308(a)(4)(ii)(C)§ 164.308(a)(4)(ii)(B)§ 164.308(a)(5)(ii)(D)§ 164.312(a)(2)(i)§ 164.312(a)(2)(ii)§ 164.312(d) | [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra) は、ID がデバイス、アプリケーション、またはサーバーにサインインするときに、検証、 [認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)、および資格情報管理のための ID プラットフォームです。 これは、セキュリティ攻撃から保護するためのシングル サインオン (SSO)、MFA、 [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) を備えたクラウドベースの ID およびアクセス管理サービスです。 認証により、承認された ID のみがリソースとデータにアクセスできるようになります。[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows) を使用すると、ID ガバナンスを使用して、joiner、mover、leaver (JML) のライフサイクルを自動化できます。 組み込みのテンプレートを使用してワークフロー プロセスを一元化するか、カスタム ワークフローを作成します。 このプラクティスは、組織の JML 戦略要件の手動タスクを減らしたり、削除したりするのに役立ちます。 Azure portal で、Microsoft Entra ID メニューの **ID ガバナンス** に移動して、組織の要件のタスクを確認または構成します。[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-roadmap) では、オンプレミスのディレクトリを Microsoft Entra ID と統合し、オンプレミスのアプリケーションや Microsoft 365 などのクラウド サービスにアクセスするための単一 ID の使用をサポートします。 Active Directory (AD) と Microsoft Entra ID の間の同期を調整します。 Microsoft Entra Connect の使用を開始するには、前提条件を確認してください。 サーバーの要件と、Microsoft Entra テナントを管理用に準備する方法に注意してください。[Microsoft Entra Connect Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp) は、クラウド上で管理されるプロビジョニング エージェントであり、マルチフォレストの切断された AD 環境からの Microsoft Entra ID への同期をサポートします。 Microsoft Entra Connect で軽量エージェントを使用します。 パスワードの数を減らし、漏洩した資格情報の検出から保護するために、パスワード ハッシュ同期をお勧めします。 |
| **CSF コントロール V11**01.c 特権管理**コントロール カテゴリ**アクセス制御 – 特権アカウント**コントロールの仕様**組織は、情報システムへの不正アクセスを防ぐために、承認されたユーザー アカウントが登録、追跡、および定期的に検証されていることを確認します**目標名**情報システムへの承認されたアクセス**HIPAA セキュリティ規則**§ 164.308(a)(1)(i)§ 164.308(a)(1)(ii)(B)§ 164.308(a)(2)§ 164.308(a)(3)(ii)(B)§ 164.308(a)(3)(ii)(A)§ 164.308(a)(4)(i)§ 164.308(a)(4)(ii)(B)§ 164.308(a)(4)(ii)(C)§ 164.310(a)(2)(ii)§ 164.310(a)(1)§ 164.310(a)(2)(iii)§ 164.312(a)(1) | [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) は、組織内の重要なリソースへのアクセスを管理、制御、監視するための Microsoft Entra ID のサービスです。 これにより、悪意のあるアクターがアクセスするのを防ぐために、セキュリティで保護された情報へのアクセス権を持つユーザーの数が最小限に抑えられます。PIM には、過剰なアクセス許可、不要なアクセス許可、または誤用されたアクセス許可のリスクを軽減するために、時間と承認ベースのアクセスがあります。 これにより、特権アカウントを特定して分析し、ユーザーが自分の役割を果たすのに十分なアクセス権 (JEA) を確保できます。[アラートの監視と生成は](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts) 、疑わしいアクティビティを防ぎ、アラートをトリガーするユーザーとロールを一覧表示すると同時に、不正アクセスのリスクを軽減します。 組織のセキュリティ戦略に合わせてアラートをカスタマイズします。[アクセス レビューを](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview) 使用すると、組織はロールの割り当てとグループ メンバーシップを効率的に管理できます。 アクセス権を持つアカウントを評価し、必要に応じアクセスが取り消されるようにすることで、セキュリティとコンプライアンスを維持し、過剰または古いアクセス許可によるリスクを最小限に抑えます。 |
| **CSF コントロール V11**0.1d ユーザー パスワード管理**コントロール カテゴリ**アクセス制御 - プロシージャ**コントロールの仕様**承認されたユーザー アカウントが登録、追跡、および定期的に検証され、情報システムへの不正アクセスを防ぐために確実に行われます。**目標名**情報システムへの承認されたアクセス**HIPAA セキュリティ規則**§164.308(a)(5)(ii)(D) | [パスワード管理](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/identity-management-best-practices) は、セキュリティ インフラストラクチャの重要な側面です。 Microsoft Entra ID は、堅牢なセキュリティ体制を作成するためのベスト プラクティスに沿って、包括的な戦略のサポートを支援します。 [SSO](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso) と [MFA](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) も [パスワードレス認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) (FIDO2 セキュリティ キーや Windows Hello for Business (WHfB) など) は、ユーザー リスクを軽減し、ユーザー認証エクスペリエンスを合理化します。Microsoft Entra Password Protection は、既知の脆弱なパスワードを検出し、ブロックします。 パスワード [ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-configure-custom-password-protection) が組み込まれており、カスタム パスワード リストを定義し、パスワードの使用を保護するためのパスワード管理戦略を構築する柔軟性があります。HITRUST のパスワードの長さと強度の要件は、国立標準技術研究所 [NIST 800-63B](https://pages.nist.gov/800-63-3/sp800-63b.html) と一致します。これには、パスワードに 8 文字以上、または最も特権の高いアクセス権を持つアカウントの場合は 15 文字が含まれます。 複雑さの基準には、特権アカウントのパスワードに対して、少なくとも1つの数字または特殊文字、および1つの大文字と小文字を含める必要があります。 |
| **CSF コントロール V11**01.p セキュリティで保護されたログオン手順**コントロール カテゴリ**アクセス制御 – セキュリティで保護されたログオン**コントロールの仕様**組織は、セキュリティで保護されたログオン手順を使用して情報資産へのアクセスを制御します。**目標名**オペレーティング システムのアクセス制御**HIPAA セキュリティ規則**§ 164.308(a)(5)(i)§ 164.308(a)(5)(ii)(C)§ 164.308(a)(5)(ii)(D) | セキュリティで保護されたサインインは、システムにアクセスしようとしたときに ID を安全に認証するプロセスです。**コントロールは [オペレーティング システム](https://learn.microsoft.com/ja-jp/azure/governance/policy/samples/hipaa-hitrust-9-2)に重点を置いています。Microsoft Entra サービスは、セキュリティで保護されたサインインを強化するのに役立ちます。**[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーは、組織が承認されたアプリケーション、リソースへのアクセスを制限し、デバイスのセキュリティを確保するのに役立ちます。 Microsoft Entra ID は、ID、場所、またはデバイスからの条件付きアクセス [ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies) からのシグナルを分析して決定を自動化し、リソースとデータにアクセスするための組織ポリシーを適用します。[ロールベースのアクセス制御 (RBAC) は](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview) 、組織内のアクセスと管理されたリソースを管理するのに役立ちます。 RBAC は、最小限の特権の原則を実装し、ユーザーがタスクを実行するために必要なアクセス許可を持っていることを確認するのに役立ちます。 このアクションにより、誤った構成や意図的な構成のリスクが最小限に抑えられます。Control 0.1d User Password Management で説明したように、パスワードレス認証では偽造が困難なため生体認証が使用されるため、より安全な認証が提供されます。 |
| **CSF コントロール V11**01.q ユーザー識別と認証**コントロール カテゴリ**なし**コントロールの仕様**すべてのユーザーは、個人使用専用の一意識別子 (ユーザー ID) を持つものとし、ユーザーの要求された ID を実証するための認証手法を実装する必要があります。**目標名**なし**HIPAA セキュリティ規則**§ 164.308(a)(5)(ii)(D)§ 164.310(a)(1)§ 164.312(a)(2)(i)§ 164.312(d) | Microsoft Entra ID の [アカウント プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning) 使用して、ユーザー アカウントを作成、更新、管理します。 各ユーザーとオブジェクトには、オブジェクト ID と呼ばれる一意識別子 (UID) が割り当てられます。 UID は、ユーザーまたはオブジェクトの作成時に自動的に生成されるグローバル一意識別子です。Microsoft Entra ID では、システムとアプリケーションの[自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)[がサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-auto-user-provisioning)。 自動プロビジョニングでは、組織内のチームに参加すると、適切なシステムに新しいアカウントが作成されます。 自動プロビジョニング解除では、ユーザーが離れるとアカウントが非アクティブ化されます。 |
| **CSF コントロール V11**01.u 接続時間の制限**コントロール カテゴリ**アクセス制御 - セキュリティで保護されたログオン**コントロールの仕様**組織は、セキュリティで保護されたログオン手順を使用して情報資産へのアクセスを制御します。**目標名**オペレーティング システムのアクセス制御**HIPAA セキュリティ規則**§ 164.312(a)(2)(iii) | **コントロールは [オペレーティング システム](https://learn.microsoft.com/ja-jp/azure/governance/policy/samples/hipaa-hitrust-9-2)に重点を置いています。Microsoft Entra サービスは、セキュリティで保護されたサインインを強化するのに役立ちます。**セキュリティで保護されたサインインは、システムにアクセスしようとしたときに ID を安全に認証するプロセスです。Microsoft Entra はユーザーを認証し、ユーザーとリソースに関する情報を含むセキュリティ機能を備えています。 情報には、アクセス トークン、更新トークン、ID トークンが含まれます。 アプリケーション アクセスの組織の要件に従って構成します。 このガイダンスは、主にモバイル クライアントとデスクトップ クライアントに使用してください。条件付きアクセス ポリシーは、 [認証されたセッションの Web ブラウザー制限の](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)構成設定をサポートします。Microsoft Entra ID には、オペレーティング システム間の統合があり、ユーザー エクスペリエンスとパスワードレス認証方法のサポートが向上します。[macOS 用のプラットフォーム SSO](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-macos?pivots=all) は、macOS の SSO 機能を拡張します。 ユーザーは、パスワードなしの資格情報を使用して Mac にサインインするか、Microsoft Entra ID によって検証されたパスワード管理を使用します。[Windows パスワードレス エクスペリエンス](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passwordless-experience/?branch=main&preserve-view=true) では、Microsoft Entra 参加済みデバイスでパスワードなしで認証エクスペリエンスが促進されます。 パスワードレス認証を使用すると、フィッシング攻撃、パスワードの再利用、パスワードのキー ロガーの傍受など、従来のパスワードベースの認証に関連する脆弱性とリスクが軽減されます。[Windows 用 Web サインイン](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/web-sign-in/?tabs=intune&branch=main) は、Windows 11 での Web サインインの機能を拡張する資格情報プロバイダーであり、Windows Hello for Business、一時アクセス パス (TAP)、フェデレーション ID をカバーします。Azure Virtual Desktop では、 [SSO とパスワードレス認証](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/authentication)がサポートされています。 SSO を使用すると、パスワードレス認証と、Microsoft Entra ID とフェデレーションするサードパーティ ID プロバイダー (IdP) を使用して、Azure Virtual Desktop リソースにサインインできます。 セッション ホストに対する認証時に SSO エクスペリエンスが提供されます。 セッション内の Microsoft Entra リソースに SSO を提供するようにセッションを構成します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/hipaa-other-controls"} -->
## Microsoft Entra HIPAA の追加のセーフガードを構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/hipaa-other-controls
- Service: entra / standards
- Article date: 2023-04-13
- Summary: Microsoft Entra HIPAA の追加制御セーフガードを構成する方法に関するガイダンス

Microsoft Entra ID は、1996 年の医療保険の移植性とアカウンタビリティに関する法律 (HIPAA) セーフガードを実装するための ID 関連のプラクティス要件を満たしています。 HIPAA に準拠するには、このガイダンスと必要な他の構成またはプロセスを使用してセーフガードを実装する必要があります。 この記事には、次の 3 つのコントロールに対する HIPAA コンプライアンスを実現するためのガイダンスが含まれています。

- 整合性セーフガード
- 個人またはエンティティの認証セーフガード
- 伝送セキュリティセーフガード

### 整合性セーフガードのガイダンス

Microsoft Entra ID は、HIPAA セーフガードを実装するための ID 関連のプラクティス要件を満たしています。 HIPAA に準拠するには、必要なその他の構成やプロセスと共に、このガイダンスを使用してセーフガードを実装します。

**データ変更セーフガード**の場合:

- すべてのデバイスでファイルと電子メールを保護します。
- 機密データを検出して分類します。
- 機密データまたは個人データを含むドキュメントと電子メールを暗号化します。

次のコンテンツでは、HIPAA からのガイダンスの後に、Microsoft の推奨事項とガイダンスを含む表を示します。

**HIPAA - 整合性**

`Implement security measures to ensure that electronically transmitted electronic protected health information isn't improperly modified without detection until disposed of.`

| 勧告 | アクション |
| --- | --- |
| Microsoft Purview Information Protection (IP) を有効にする | 機密性の高いデータを検出、分類、保護、管理し、転送されるストレージとデータをカバーします。Microsoft Purview IP を使用してデータを保護 、データランドスケープを特定し、フレームワークを確認し、データを特定して保護するためのアクティブな手順を実行するのに役立ちます。 |
| Exchange インプレース ホールドを構成する | Exchange Online には、電子情報開示をサポートするためのいくつかの設定が用意されています。 [インプレース ホールド](https://learn.microsoft.com/ja-jp/exchange/security-and-compliance/in-place-ediscovery/assign-ediscovery-permissions) では、保持する項目に関する特定のパラメーターが使用されます。 決定マトリックスは、キーワード、送信者、領収書、および日付に基づいて指定できます。[Microsoft Purview 電子情報開示ソリューション](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/ediscovery) は、Microsoft Purview ポータルの一部であり、すべての Microsoft 365 データ ソースを対象としています。 |
| Exchange Online で Secure/多目的インターネット メール拡張機能を構成する | [S/MIME](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/email-encryption) は、デジタル署名および暗号化されたメッセージを送信するために使用されるプロトコルです。 これは、非対称キーのペアリング、公開キーと秘密キーに基づいています。[Exchange Online](https://learn.microsoft.com/ja-jp/exchange/security-and-compliance/smime-exo/configure-smime-exo) は、電子メールの内容と送信者の身元を確認する署名の暗号化と保護を提供します。 |
| 監視とログ記録を有効にします。 | [ログ記録と監視](https://learn.microsoft.com/ja-jp/security/benchmark/azure/security-control-logging-monitoring) は、環境をセキュリティで保護するために不可欠です。 この情報は、調査をサポートするために使用され、異常なパターンを特定することで潜在的な脅威を検出するのに役立ちます。 サービスのログ記録と監視を有効にして、不正アクセスのリスクを軽減します。microsoft Purview 監査、Microsoft 365 のサービス全体で監査されたアクティビティを可視化します。 監査ログのリテンション期間を増やすことで、調査に役立ちます。 |

### 個人またはエンティティ認証のセーフガード ガイダンス

Microsoft Entra ID は、HIPAA セーフガードを実装するための ID 関連のプラクティス要件を満たしています。 HIPAA に準拠するには、必要なその他の構成やプロセスと共に、このガイダンスを使用してセーフガードを実装します。

監査と個人およびエンティティのセーフガードの場合:

- エンド ユーザー要求がデータ アクセスに対して有効であることを確認します。
- 格納されているデータのリスクを特定して軽減します。

次のコンテンツでは、HIPAA からのガイダンスの後に、Microsoft の推奨事項とガイダンスを含む表を示します。

**HIPAA - 個人またはエンティティの認証**

`Implement procedures to verify that a person or entity seeking access to electronic protected health information is the one claimed.`

ePHI データにアクセスするユーザーとデバイスが承認されていることを確認します。 デバイスが準拠していることを確認し、データ所有者にリスクにフラグを付けるためにアクションを監査する必要があります。

| 勧告 | アクション |
| --- | --- |
| 多要素認証を有効にする | Microsoft Entra 多要素認証、セキュリティの層を追加することで ID を保護します。 追加レイヤーは、不正アクセスを防ぐ効果的な方法を提供します。 MFA を使用すると、認証プロセス中にサインイン資格情報をより多く検証する必要があります。 [Authenticator アプリ](https://support.microsoft.com/account-billing/set-up-an-authenticator-app-as-a-two-step-verification-method-2db39828-15e1-4614-b825-6e2b524e7c95)を設定すると、ワンクリック検証が提供されます。または [Microsoft Entra パスワードレス構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)を構成できます。 |
| 条件付きアクセス ポリシーを有効にする | [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies) ポリシーは、承認されたアプリケーションのみにアクセスを制限するのに役立ちます。 Microsoft Entra は、ユーザー、デバイス、または場所からのシグナルを分析して決定を自動化し、リソースとデータにアクセスするための組織ポリシーを適用します。 |
| デバイス ベースの条件付きアクセス ポリシーを設定する | デバイス管理のための [Microsoft Intune による条件付きアクセス](https://learn.microsoft.com/ja-jp/mem/intune/protect/conditional-access)と Microsoft Entra ポリシーにより、デバイスの状態を使用して、サービスとデータへのアクセスを許可または拒否できます。 デバイス コンプライアンス ポリシーを展開することで、リソースへのアクセスを許可するか拒否するかを決定するセキュリティ要件を満たしているかどうかを判断します。 |
| ロールベースのアクセス制御 (RBAC) を使用する | Microsoft Entra ID の RBAC は、業務を分離して、エンタープライズ レベルでセキュリティを提供します。 システムを使用して、リソースと機密データに対する機密性、プライバシー、アクセス管理を保護するためのアクセス許可を調整および確認します。Microsoft Entra ID は、[組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)のサポートを提供します。これは、変更できない固定のアクセス許可セットです。 プリセット リストを追加できる独自の [カスタム ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create) を作成することもできます。 |

### 伝送セキュリティセーフガードガイダンス

Microsoft Entra ID は、HIPAA セーフガードを実装するための ID 関連のプラクティス要件を満たしています。 HIPAA に準拠するには、必要なその他の構成やプロセスと共に、このガイダンスを使用してセーフガードを実装します。

暗号化の場合:

- データの機密性を保護します。
- データの盗難を防ぎます。
- PHI への不正アクセスを防止します。
- データの暗号化レベルを確認します。

PHI データの送信を保護するには:

- PHI データの共有を保護します。
- PHI データへのアクセスを保護します。
- 送信されるデータが暗号化されていることを確認します。

次のコンテンツでは、HIPAA ガイダンスからの監査および伝送セキュリティ 保護ガイダンスの一覧と、Microsoft Entra ID を使用してセーフガードの実装要件を満たすことを可能にする Microsoft の推奨事項を示します。

**HIPAA - 暗号化**

`Implement a mechanism to encrypt and decrypt electronic protected health information.`

ePHI データが暗号化され、準拠している暗号化キー/プロセスで暗号化解除されていることを確認します。

| 勧告 | アクション |
| --- | --- |
| Microsoft 365 暗号化ポイントを確認する | Microsoft 365 の Microsoft Purview を使用した 暗号化は、物理データ センター、セキュリティ、ネットワーク、アクセス、アプリケーション、データ セキュリティなど、複数のレイヤーで広範な保護を提供する高度にセキュリティで保護された環境です。 暗号化リストを確認し、さらに制御が必要な場合は修正します。 |
| データベースの暗号化を確認する | [Transparent Data Encryption](https://learn.microsoft.com/ja-jp/sql/relational-databases/security/encryption/transparent-data-encryption?view=sql-server-ver16&preserve-view=true) は、未承認またはオフライン アクセスから保存データを保護するためのセキュリティレイヤーを追加します。 AES 暗号化を使用してデータベースを暗号化します。[機密データの](https://learn.microsoft.com/ja-jp/azure/azure-sql/database/dynamic-data-masking-overview)に対する動的データ マスクを使用すると、機密データの公開が制限されます。 これは、認証されていないユーザーにデータをマスクします。 マスクには、データベース スキーマ名、テーブル名、列名で定義する指定されたフィールドが含まれます。 新しいデータベースは既定で暗号化され、データベース暗号化キーは組み込みのサーバー証明書によって保護されます。 データベースを確認して、データ資産に暗号化が設定されていることを確認することをお勧めします。 |
| Azure Encryption ポイントを確認する | [Azure 暗号化機能](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/encryption-overview) は、保存データ、暗号化モデル、および Azure Key Vault を使用したキー管理の主要領域を対象とします。 さまざまな暗号化レベルと、それらが組織内のシナリオとどのように一致するかを確認します。 |
| データ収集と保持のガバナンスを評価する | Microsoft Purview データ ライフサイクル管理 [は、](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/data-lifecycle-management) 保持ポリシーを適用できます。 Microsoft Purview Records Managementを使用すると、保持ラベルを適用できます。 この戦略は、データ資産全体の資産を可視化するのに役立ちます。 この戦略は、クラウド、アプリ、エンドポイント間で機密データを保護および管理するのにも役立ちます。**重要:**[45 CFR 164.316](https://www.ecfr.gov/current/title-45/subtitle-A/subchapter-C/part-164/subpart-C/section-164.316)で説明されているように: **時間制限 (必須)**。 このセクションの [段落 (b)(1)](https://www.ecfr.gov/current/title-45/section-164.316) で必要なドキュメントは、作成日から 6 年間、または最後に有効になった日付のいずれか後に保持してください。 |

**HIPAA - PHI データ** の伝送を保護する

`Implement technical security measures to guard against unauthorized access to electronic protected health information that is being transmitted over an electronic communications network.`

PHI データを含むデータ交換を保護するためのポリシーと手順を確立します。

| 勧告 | アクション |
| --- | --- |
| オンプレミス アプリケーションの状態を評価する | Microsoft Entra アプリケーション プロキシ 実装 、オンプレミスの Web アプリケーションを外部および安全な方法で公開します。Microsoft Entra アプリケーション プロキシ、外部 URL エンドポイントを Azure に安全に発行できます。 |
| 多要素認証を有効にする | Microsoft Entra 多要素認証、セキュリティレイヤーを追加することで ID を保護します。 セキュリティのレイヤーを追加することは、不正アクセスを防ぐ効果的な方法です。 MFA を使用すると、認証プロセス中にサインイン資格情報をより多く検証する必要があります。 [Authenticator](https://support.microsoft.com/account-billing/set-up-an-authenticator-app-as-a-two-step-verification-method-2db39828-15e1-4614-b825-6e2b524e7c95) アプリを構成して、ワンクリック認証またはパスワードレス認証を提供できます。 |
| アプリケーション アクセスの条件付きアクセス ポリシーを有効にする | [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies) ポリシーは、承認されたアプリケーションへのアクセスを制限するのに役立ちます。 Microsoft Entra は、ユーザー、デバイス、または場所からのシグナルを分析して決定を自動化し、リソースとデータにアクセスするための組織ポリシーを適用します。 |
| Exchange Online Protection (EOP) ポリシーを確認する | [Exchange Online のスパムとマルウェア保護](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/exchange-online-protection-service-description/exchange-online-protection-feature-details?tabs=Anti-spam-and-anti-malware-protection) は、組み込みのマルウェアとスパム のフィルター処理を提供します。 EOP は受信メッセージと送信メッセージを保護し、既定で有効になっています。 EOP サービスでは、スプーフィング対策、検疫メッセージ、および Outlook でメッセージをレポートする機能も提供されます。 会社全体の設定に合わせてポリシーをカスタマイズできますが、これらは既定のポリシーよりも優先されます。 |
| 秘密度ラベルを構成する | Microsoft Purview から秘密度ラベルを使用すると、組織のデータを分類して保護できます。 ラベルは、ドキュメントの保護設定をコンテナーに提供します。 たとえば、このツールは、Microsoft Teamsおよび SharePoint サイトに保存されているドキュメントを保護して、プライバシー設定を設定および適用します。 SQL、Azure SQL、Azure Synapse、Azure Cosmos DB、AWS RDS などのファイルとデータ資産にラベルを拡張します。 200 種類の機密情報以外にも、名前エンティティ、トレーニング可能な分類子、カスタム機密型を保護する EDM などの高度な分類子があります。 |
| サービスに接続するためにプライベート接続が必要かどうかを評価する | Azure ExpressRouteは、クラウドベースの Azure データセンターとオンプレミスに存在するインフラストラクチャの間にプライベート接続を作成します。 データはパブリック インターネット経由で転送されません。 サービスはレイヤー 3 接続を使用し、エッジ ルーターを接続し、動的なスケーラビリティを提供します。 |
| VPN の要件を評価する | [VPN Gateway のドキュメント](https://learn.microsoft.com/ja-jp/azure/vpn-gateway/vpn-gateway-about-vpngateways)、サイト間、ポイント対サイト、VNet 対 VNet、マルチサイト VPN 接続を介してオンプレミス ネットワークを Azure に接続します。サービスは、セキュリティで保護されたデータ転送を提供することで、ハイブリッド作業環境をサポートします。 |

### 詳細情報

- [ゼロ トラストの重要な要素: データ](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)
- [ゼロ トラストの柱: ID、ネットワーク、インフラストラクチャ、データ、アプリケーション](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/memo-22-09-authorization"} -->
## 覚書 22-09 の認可要件を満たす - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-authorization
- Service: entra / standards
- Article date: 2025-02-10
- Summary: OMB 覚書 22-09 に記載されている認可要件を満たす方法について説明します。

この記事シリーズでは、ゼロ トラストの原則を実装する際の一元化された ID 管理システムとして Microsoft Entra ID を採用するためのガイダンスを示しています。 指示とガイダンスは、米国管理予算局 (OMB) [M-22-09 執行部および機関の長のための覚書](https://bidenwhitehouse.archives.gov/wp-content/uploads/2022/01/M-22-09.pdf)に基づいています。

覚書の要件は、多要素認証ポリシーの適用の種類と、デバイス、ロール、属性、および特権アクセス管理の制御です。

### デバイスベースの制御

覚書 22-09 の要件は、システムまたはアプリケーションにアクセスするための認可決定用の少なくとも 1 つのデバイス ベースのシグナルです。 この要件は、条件付きアクセスを使用して適用します。 認可中に複数のデバイス信号を適用してください。 シグナルと、そのシグナルを取得するための要件については、次の表を参照してください。

| 信号 | シグナルの取得 |
| --- | --- |
| デバイスが管理されている | Intune または統合をサポートする別のモバイル デバイス管理 (MDM) ソリューションとの統合。 |
| Microsoft Entra ハイブリッド参加済み | Active Directory でデバイスが管理され、これは適格です。 |
| デバイスが準拠している | Intune または統合をサポートする別の MDM ソリューションとの統合。 [Microsoft Intune でのコンプライアンス ポリシーの作成](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)に関する記事を参照してください。 |
| 脅威シグナル | Microsoft Defender for Endpoint や他のエンドポイントでの検出と対応 (EDR) ツールには、Microsoft Entra ID と Intune の統合が含まれています。これにより、アクセスを拒否するために脅威シグナルが送信されます。 脅威シグナルでは、準拠状態シグナルがサポートされています。 |
| テナント間アクセス ポリシー (パブリック プレビュー) | 他の組織のデバイスからのデバイス信号を信頼します。 |

### ロールベースの制御

ロールベースのアクセス制御 (RBAC) を使用して、特定のスコープでのロールの割り当てを通じて認可を適用します。 たとえば、アクセス パッケージやアクセス レビューなどのエンタイトルメント管理機能を使用して、アクセスを割り当てます。 セルフサービス要求を使用して認可を管理し、自動化を使用してライフサイクルを管理します。 たとえば、条件に基づいてアクセスを自動的に終了します。

詳細情報:

- [エンタイトルメント管理とは](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)
- [エンタイトルメント管理で新しいアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)
- [アクセス レビューとは](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)

### 属性ベースの制御

属性ベースのアクセス制御 (ABAC) では、ユーザーまたはリソースに割り当てられたメタデータを使用して、認証時にアクセスを許可または拒否します。 認証を通じてデータおよびリソースに対する ABAC の適用を使用して認証を作成するには、以降のセクションを参照してください。

#### ユーザーに割り当てられた属性

Microsoft Entra ID に格納されている、ユーザーに割り当てられた属性を使用して、ユーザーの認可を作成します。 グループの作成中に定義するルール セットに基づいて、ユーザーは動的メンバーシップ グループに自動的に割り当てられます。 ルールでは、ユーザーおよびその属性に対するルールの評価に基づき、グループに対してユーザーの追加または削除を行います。 属性を保守し、作成時に静的属性を設定しないことをお勧めします。

詳細情報: [Microsoft Entra ID で動的グループを作成または更新する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)

#### データに割り当てられた属性

Microsoft Entra ID を使用すると、認可をデータに統合できます。 認可を統合するには、以降のセクションを参照してください。 認証は、条件付きアクセス ポリシーで構成できます。ユーザーがアプリケーション内で、またはデータに対して実行するアクションを制限してください。 これらの認証ポリシーはその後、データ ソースにマップされます。

データ ソースは、Word や Excel などの Microsoft Office ファイル、または認証にマップされた SharePoint サイトにすることができます。 アプリケーション内のデータに割り当てられた認証を使用してください。 この方法では、アプリケーション コードとの統合と、開発者がこの機能を採用することが必要です。 Microsoft Defender for Cloud Apps との認証統合を使用して、セッション制御によりデータに対して実行されるアクションを制御します。

データとユーザー属性の間のユーザー アクセス マッピングを制御するには、動的メンバーシップ グループと認証コンテキストを組み合わせます。

詳細情報:

- [条件付きアクセス: クラウド アプリ、アクション、認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)
- [条件付きアクセス認証コンテキストに関する開発者ガイド](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-guide-conditional-access-authentication-context)
- [セッション ポリシー](https://learn.microsoft.com/ja-jp/defender-cloud-apps/session-policy-aad)

#### リソースに割り当てられた属性

Azure には、ストレージ用の属性ベースのアクセス制御 (Azure ABAC) が含まれています。 Azure Blob Storage アカウントに保存されているデータに対してメタデータ タグを割り当てます。 このメタデータをユーザーに割り当てるには、ロールの割り当てを使用してアクセス権を付与します。

詳細情報: [Azure の属性ベースのアクセス制御 (Azure ABAC) とは](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview)

### 特権アクセス管理

この覚書では、特権アクセス管理ツールと単一要素の一時的な資格情報を使用してシステムにアクセスする非効率性について言及しています。 これらのテクノロジには、管理者の多要素認証サインインを受け入れるパスワード コンテナーが含まれます。これらのツールにより、代替アカウントがシステムにアクセスするためのパスワードが生成されます。 システム アクセスは、単一要素で行われます。

Microsoft のツールは、中央 ID 管理システムとしての Microsoft Entra ID と合わせて、特権システム用に Privileged Identity Management (PIM) を実現します。 アプリケーション、インフラストラクチャ要素、またはデバイスであるほとんどの特権システムに対して多要素認証を適用してください。

Microsoft Entra の ID で実装されている特権ロールには、PIM を使用します。 横移動を防ぐために保護を必要とする特権システムを特定してください。

詳細情報:

- [Microsoft Entra Privileged Identity Management とは](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)
- [Privileged Identity Management のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-deployment-plan)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/memo-22-09-enterprise-wide-identity-management-system"} -->
## 覚書 22-09 企業全体の ID 管理システム - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-enterprise-wide-identity-management-system
- Service: entra / standards
- Article date: 2023-05-01
- Summary: OMB 覚書 22-09 で概説されている企業全体の ID 管理システムの要件を満たすためのガイダンス。

[省および機関の長宛ての M 22-09 覚書](https://www.whitehouse.gov/wp-content/uploads/2022/01/M-22-09.pdf)では、機関が ID プラットフォームの統合計画を策定することを求めています。 その目標は、機関が公開日 (2022 年 3 月 28 日) から 60 日以内に機関マネージド ID システムの数をできるだけ持たないようにすることです。 ID プラットフォームを統合する利点は複数あります。

- ID ライフサイクル、ポリシーの適用、監査可能な制御の管理が一元化されます
- 適用の一様な可能性と奇偶性
- 複数のシステムにわたってリソースをトレーニングする必要性が削減されます
- ユーザーが一度サインインしたら、IT 環境内のアプリケーションとサービスにアクセス可能になります
- 可能な限り多くの機関アプリケーションと統合されます
- 共有認証サービスと信頼関係を使用して、複数機関にわたる統合が促進されます

### Microsoft Entra ID を採用する理由

Microsoft Entra ID を使用することで、覚書 22-09 からの推奨事項を実装します。 Microsoft Entra ID には、ゼロ トラスト イニシアチブをサポートする広範な ID 制御が備わっています。 Microsoft Office 365 または Azure では、Microsoft Entra ID は ID プロバイダー (IdP) です。 エンタープライズ全体の ID システムとして Microsoft Entra ID にアプリケーションとリソースを接続します。

### シングル サインオンの要件

覚書では、ユーザーが一度サインインしてから、アプリケーションにアクセスすることを求めています。 Microsoft シングル サインオン (SSO) を使用して、ユーザーは一度サインインしてから、クラウド サービスやアプリケーションにアクセスします。 「[Microsoft Entra シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)」を参照してください。

### 複数機関にわたる統合

複数機関にわたる統合およびコラボレーションの促進という要件を満たすには、Microsoft Entra B2B コラボレーションを使用します。 ユーザーは、同じクラウド内の Microsoft テナントに存在できます。 テナントは、別の Microsoft クラウド、または Azure AD 以外のテナント (SAML または WS-Fed ID プロバイダー) に存在できます。

Microsoft Entra テナント間アクセス設定を使用して、機関は、他の Microsoft Entra 組織や他の Microsoft Azure クラウドとのコラボレーション方法を管理します。

- ユーザーがアクセスできる Microsoft テナントを制限する
- 多要素認証の適用やデバイス信号など、外部ユーザー アクセスの設定

詳細情報:

- [B2B コラボレーションの概要](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)
- [政府機関および各国のクラウドでの Microsoft Entra B2B](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-government-national-clouds)
- [ゲスト ユーザー向けの SAML/WS-Fed ID プロバイダーとのフェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)

### アプリケーションの接続

エンタープライズ全体の ID システムとして Microsoft Entra ID を統合して使用するには、スコープ内の資産を確認します。

#### アプリケーションとサービスをドキュメント化する

ユーザーがアクセスするアプリケーションおよびサービスのインベントリを作成します。 ID 管理システムでは、認識しているものを保護します。

資産の分類:

- その中のデータの秘密度
- 主要システムにおけるデータや情報の機密性、整合性、または可用性に関する法律および規制
    - システム情報保護の要件に適用される当該の法律および規制

アプリケーション インベントリでは、クラウド対応プロトコルまたはレガシ認証プロトコルを使用するアプリケーションを判別する必要があります。

- クラウド対応アプリケーションでは、認証のための最新のプロトコルがサポートされています。
    - SAML
    - WS-Federation/Trust
    - OpenID Connect (OIDC)
    - OAuth 2.0。
- レガシ認証アプリケーションは、以前の、または独自の認証方法に依存しています。
    - Kerberos または NTLM (Windows 認証)
    - ヘッダーベースの認証
    - LDAP
    - [基本認証]

[Microsoft Entra と認証プロトコルの統合](https://learn.microsoft.com/ja-jp/entra/architecture/auth-sync-overview)の詳細についてはこちらを参照してください。

##### アプリケーションとサービスの検出ツール

Microsoft は、アプリケーションとサービスの検出をサポートするために次のツールを提供しています。

| ツール | 使用 |
| --- | --- |
| Active Directory Federation Services (AD FS) の利用状況分析 | フェデレーション サーバー認証トラフィックを分析します。 「[Microsoft Entra Connect Health を使用して AD FS を監視する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs)」を参照してください。 |
| Microsoft Defender for Cloud Apps | ファイアウォール ログをスキャンして、クラウド アプリ、サービスとしてのインフラストラクチャ (IaaS) サービス、サービスとしてのプラットフォーム (PaaS) サービスを検出します。 Windows クライアント デバイスから分析されたデータを検出するには、Defender for Cloud Apps を Defender for Endpoint と統合します。 「[Microsoft Defender for Cloud Apps の概要](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps)」を参照してください |
| アプリケーション検出ワークシート | アプリケーションの現在の状態を文書化します。 [アプリケーション検出ワークシート](https://download.microsoft.com/download/2/8/3/283F995C-5169-43A0-B81D-B0ED539FB3DD/Application%20Discovery%20worksheet.xlsx)を参照してください |

お使いのアプリは Microsoft 以外のシステムに存在する場合があり、Microsoft のツールがそれらのアプリを検出しないことがあります。 完全なインベントリを確実に行ってください。 プロバイダーには、自社サービスを使用するアプリケーションを検出するメカニズムが必要です。

##### 接続に関するアプリケーションの優先順位を付ける

お使いの環境内のアプリケーションを検出したら、移行の優先順位を付けます。 以下を検討してください。

- ビジネス上の重要度
- ユーザー プロファイル
- 使用
- 有効期間

詳細情報: 「[アプリケーション認証を Microsoft Entra ID に移行する](https://aka.ms/migrateapps/whitepaper)」を参照してください。

クラウド対応アプリを優先順位に従って接続します。 レガシ認証プロトコルを使用するアプリを特定します。

レガシ認証プロトコルを使用するアプリの場合:

- 最新の認証を使用するアプリは、Microsoft Entra ID を使用するよう再構成します
- 最新の認証を使用しないアプリの場合は、次の 2 つの選択肢があります。
    - Microsoft Authentication Library (MSAL) を統合して、最新のプロトコルを使用するようアプリケーション コードを更新します
    - 安全にアクセスできるように、Microsoft Entra アプリケーション プロキシまたは安全なハイブリッド パートナー アクセスを使用します
- 不要になった、またはサポートされていないアプリへのアクセスを停止します

詳細情報

- [Microsoft Entra と認証プロトコルの統合](https://learn.microsoft.com/ja-jp/entra/architecture/auth-sync-overview)
- [Microsoft ID プラットフォームとは](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)
- [安全なハイブリッド アクセス: Microsoft Entra ID を使用してレガシ アプリを保護する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access)

### デバイスの接続

ユーザーが物理および仮想デバイスにサインインできるようにすることは、ID 管理システムの一元化の一部です。 一元化された Microsoft Entra システム内で Windows および Linux デバイスに接続でき、これによって複数の異なる ID システムが不要になります。

インベントリとスコープ設定の実行中に、Microsoft Entra ID と統合するデバイスとインフラストラクチャを特定します。 統合により、Microsoft Entra ID を通じて多要素認証が適用される条件付きアクセス ポリシーを使用して、認証と管理が一元化されます。

#### デバイスを検出するためのツール

Azure Automation アカウントを使用し、Azure Monitor に接続されたインベントリ コレクションを通じてデバイスを特定できます。 Microsoft Defender for Endpoint には、デバイス インベントリ機能があります。 Defender for Endpoint が構成されているデバイスと構成されていないものを検出します。 デバイス インベントリは、System Center Configuration Manager などのオンプレミス システムや、デバイスとクライアントを管理する他のシステムから取得されます。

詳細情報:

- [VM からのインベントリ収集を管理する](https://learn.microsoft.com/ja-jp/azure/automation/change-tracking/manage-inventory-vms)
- [Microsoft Defender for Endpoint の概要](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/machines-view-overview)
- [ハードウェア インベントリの概要](https://learn.microsoft.com/ja-jp/mem/configmgr/core/clients/manage/inventory/introduction-to-hardware-inventory)

#### Microsoft Entra ID にデバイスを統合する

Microsoft Entra ID に統合されたデバイスは、ハイブリッド参加済みデバイスまたは Microsoft Entra 参加済みデバイスです。 デバイスのオンボードは、クライアントとユーザーのデバイスで、さらにインフラストラクチャとして動作する物理マシンと仮想マシンで分けてください。 ユーザー デバイスの展開戦略の詳細については、次のガイダンスを参照してください。

- [Microsoft Entra デバイスの展開を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/plan-device-deployment)
- [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)
- [Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)
- [パスワードレスを含め Microsoft Entra ID を使用して Azure の Windows 仮想マシンにログインする](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows)
- [Microsoft Entra ID と OpenSSH を使用して Azure の Linux 仮想マシンにログインする](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-linux)
- [Azure Virtual Desktop の Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/azure/architecture/example-scenario/wvd/azure-virtual-desktop-azure-active-directory-join)
- [デバイス ID とデスクトップ仮想化](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-device-identity-virtual-desktop-infrastructure)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/memo-22-09-meet-identity-requirements"} -->
## メモ 22-09 ID 要件の概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-meet-identity-requirements
- Service: entra / standards
- Article date: 2025-02-10
- Summary: 米国政府の OMB 覚書 22-09 で概説されている要件を満たすガイダンスを取得します。

国家サイバーセキュリティの改善に関する行政命令(14028)は、連邦政府機関に対して、連邦政府のデジタルインフラストラクチャに対するサイバー攻撃の成功リスクを大幅に軽減するセキュリティ対策を進めるよう指示している。 2022年1月26日、行政命令(EO)14028を支持して、管理予算局(OMB)は、[M 22-09エグゼクティブ部門と機関のヘッドのための覚書で連邦ゼロトラスト戦略を発表](https://bidenwhitehouse.archives.gov/wp-content/uploads/2022/01/M-22-09.pdf)。

この記事シリーズには、メモ 22-09 で説明されているように、ゼロ トラスト原則を実装するときに、Microsoft Entra ID を一元化された ID 管理システムとして使用するためのガイダンスがあります。

Memo 22-09 は、連邦機関でのゼロ トラスト イニシアチブをサポートしています。 連邦サイバーセキュリティとデータプライバシーに関する法律に関する規制ガイダンスがあります。 メモは、[米国国防総省 (DoD) ゼロ トラスト参照アーキテクチャ](https://cloudsecurityalliance.org/artifacts/dod-zero-trust-reference-architecture/)を引用しています。

"*ゼロ トラスト モデルの基本理念は、セキュリティ境界の外部または内部で動作するアクター、システム、ネットワーク、またはサービスが信頼されていないことです。代わりに、アクセスを確立しようとしているすべてのものを確認する必要があります。これは、インフラストラクチャ、ネットワーク、およびデータをセキュリティで保護する方法に関する理念の劇的なパラダイム シフトです。境界での検証から、各ユーザー、デバイス、アプリケーション、トランザクションの継続的な検証までです。*"

このメモでは、サイバーセキュリティ 情報システム アーキテクチャ (CISA) 成熟度モデルを使用して、連邦政府機関が達成するための 5 つの主要な目標を特定します。 CISA ゼロ トラスト モデルでは、次の 5 つの補完的な取り組み領域について説明します。

- 同一性
- デバイス
- ネットワーク
- アプリケーションとワークロード
- データ

柱は次の要素と交差します。

- 可視
- 解析学
- オートメーション
- オーケストレーション
- 統治

### ガイダンスの範囲

記事シリーズを使用して、メモの要件を満たす計画を作成します。 Microsoft 365 製品と Microsoft Entra テナントを使用することを前提としています。

詳細情報: [クイック スタート: Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)で新しいテナントを作成する。

この記事シリーズの手順には、メモの ID 関連のアクションに合わせた Microsoft テクノロジへの機関の投資が含まれています。

- 代理店ユーザーの場合、機関は、アプリケーションや一般的なプラットフォームと統合できる一元化された ID 管理システムを採用します
- 政府機関がエンタープライズ全体で強力な多要素認証 (MFA) を使用する
    - MFA は、ネットワーク層ではなく、アプリケーション層で適用されます
    - 代理店のスタッフ、請負業者、パートナーには、フィッシングに対する耐性のある MFA が必要です
    - パブリック ユーザーの場合、フィッシングに対する耐性のある MFA はオプションです
    - パスワード ポリシーでは、特殊文字や通常のローテーションは必要ありません
- 機関がリソースへのユーザー アクセスを承認する場合、認証されたユーザーに関する ID 情報を使用して、少なくとも 1 つのデバイス レベルのシグナルを考慮します
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/memo-22-09-multi-factor-authentication"} -->
## 覚書 22-09 の多要素認証の要件の概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-multi-factor-authentication
- Service: entra / standards
- Article date: 2025-02-10
- Summary: 米国行政管理予算局の覚書 22-09 に記載されている多要素認証の要件を満たすためのガイダンスを提供します。

ゼロ トラスト原則の実装時に、一元化された ID 管理システムとして Microsoft Entra ID を使用する方法について説明します。 米国行政管理予算局 (OMB) による[省および機関の長宛ての M 22-09 覚書](https://bidenwhitehouse.archives.gov/wp-content/uploads/2022/01/M-22-09.pdf)を参照してください。

この覚書では、従業員が企業で管理されている ID を使用してアプリケーションにアクセスすること、多要素認証によってフィッシングなどの高度なオンライン攻撃から従業員を保護することを要求しています。 こうした攻撃手法では、偽のサイトへのリンクを使って資格情報の入手や侵害が試行されます。

多要素認証を使用すると、アカウントやデータへの未承認のアクセスを防止できます。 覚書に記載されている要件では、フィッシングに強い方法を使った多要素認証について言及しています。つまり、認証シークレットの漏えいと、正当なシステムを装った Web サイトやアプリケーションへの出力を検出および防止するように設計された認証プロセスです。 したがって、フィッシングに強い多要素認証方法を確立してください。

### フィッシング対策方法

一部の連邦政府機関は、FIDO2 セキュリティ キーや Windows Hello for Business などの最新の資格情報を展開しました。 多くの機関が、証明書による Microsoft Entra 認証を評価しています。

詳細情報:

- [FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)
- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-overview)
- [Microsoft Entra 証明書ベースの認証の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)

認証資格情報の最新化に取り組んでいる機関もあります。 Microsoft Entra ID を使ってフィッシングに強い多要素認証の要件を満たすためのオプションは複数あります。 Microsoft では、機関の機能に適した、フィッシングに強い多要素認証方法を採用することをお勧めします。 フィッシングに強い多要素認証によって全体的なサイバーセキュリティ体制を改善するために、現時点で何が可能かを検討しましょう。 最新の資格情報を実装してください。 ただし、最短パスが最新のアプローチでない場合は、最新のアプローチに向けた取り組みを開始してください。

[Image: Microsoft Entra のフィッシングに強い多要素認証方法の図。]

#### 最新のアプローチ

- **FIDO2 セキュリティ キー**は、Cybersecurity Infrastructure Security Agency (CISA) によると、多要素認証の代表的な方法です。
    - [Microsoft Entra ID のパスワードレス認証オプションである FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)に関する記事を参照してください
    - cisa.gov の[パスワード以上の認証](https://www.cisa.gov/mfa)に関する記事を参照してください。
- **Microsoft Entra の証明書認証**は、フェデレーション ID プロバイダーに依存しません。
    - このソリューションには、Common Access Card (CAC) や本人確認 (PIV) などのスマート カードの実装と、モバイル デバイスまたはセキュリティ キーの派生 PIV 資格情報が含まれます。
    - [Microsoft Entra 証明書ベースの認証の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)を参照してください
- **Windows Hello for Business**は、フィッシングに強い多要素認証を備えています。
    - 「[Windows Hello for Business の展開の概要](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-deployment-guide)」を参照してください。
    - [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/)に関する記事を参照してください。

#### 外部フィッシングからの保護

Microsoft Authenticator と条件付きアクセス ポリシーは、マネージド デバイス (Microsoft Entra ハイブリッド参加済みデバイス、または準拠とマークされたデバイス) を強制します。 Microsoft Entra ID で保護されたアプリケーションにアクセスするデバイスに Microsoft Authenticator をインストールしてください。

詳細：[Microsoft Entra ID の認証方法 - Microsoft Authenticator アプリ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app)

重要

フィッシング対策の要件を満たすには: 保護されたアプリケーションにアクセスするデバイスのみを管理します。 Microsoft Authenticator の使用を許可されたユーザーには、アクセスにマネージド デバイスの使用を必須とする条件付きアクセス ポリシーが適用されます。 Microsoft Intune Enrollment クラウド アプリへのアクセスは、条件付きアクセス ポリシーによってブロックされます。 Microsoft Authenticator の使用を許可されたユーザーには、この条件付きアクセス ポリシーが適用されます。 同じグループを使用して条件付きアクセス ポリシーで Microsoft Authenticator 認証を許可し、この認証方法が有効になっているユーザーに両方のポリシーが適用されるようにします。 この条件付きアクセス ポリシーにより、悪意のある外部アクターによるフィッシング脅威の最も重大な攻撃ベクトルを防ぐことができます。 また、悪意のあるアクターが Microsoft Authenticator にフィッシングを行って資格情報を登録したり、デバイスに参加してそのデバイスを Intune に登録し、準拠としてマークしたりするのを防ぐことができます。

詳細情報:

- [Microsoft Entra ハイブリッド ジョインの実装計画を立てる](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)
- [方法: Microsoft Entra への登録実装を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-plan)
- 「[一般的な条件付きアクセス ポリシー: すべてのユーザーに対して準拠デバイス、Hybrid Microsoft Entra Join を使用したデバイス、または多要素認証を必須にする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)」」も参照してください

注

Microsoft Authenticator はフィッシングに強い方法ではありません。 外部のフィッシング脅威からマネージド デバイスを保護することを必須とするように、条件付きアクセス ポリシーを構成してください。

#### レガシー

Active Directory フェデレーション サービス (AD FS) などのフェデレーション ID プロバイダー (IdP) を、フィッシングに強い方法を使って構成していました。 フェデレーション IdP を使用すると機関はフィッシングを防止できますが、この方法ではコスト、複雑さ、リスクが増します。 Microsoft では、Microsoft Entra ID を IdP として利用するセキュリティ上の利点を生かし、フェデレーション IdP の関連リスクを取り除くことを推奨しています

詳細情報:

- [オンプレミスの攻撃から Microsoft 365 を保護する](https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks)
- [Azure での AD フェデレーション サービスのデプロイ](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/how-to-connect-fed-azure-adfs)
- [ユーザー証明書認証用の AD FS の構成](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-user-certificate-authentication)

#### フィッシングに強い方法に関する考慮事項

現在のデバイス機能、ユーザー ペルソナ、その他の要件により、多要素の方法が必要になる場合があります。 たとえば、USB-C をサポートする FIDO2 セキュリティ キーには、USB-C ポートを備えたデバイスが必要です。 フィッシングに強い多要素認証を評価する際には、次の情報を考慮してください。

- **サポート可能なデバイスの種類と機能**: キオスク、ノート PC、携帯電話、生体認証リーダー、USB、Bluetooth、近距離通信デバイス
- **組織のユーザー ペルソナ**: 現場担当者、会社所有のハードウェアを持つリモート ワーカー、特権アクセス ワークステーションを持つ管理者、および企業間ゲスト ユーザー
- **ロジスティクス**: FIDO2 セキュリティ キー、スマート カード、政府によって供給された機器、TPM チップを備えた Windows デバイスなどの多要素認証方法の配布、構成、登録
- **認証保証レベルでの Federal Information Processing Standards (FIPS) 140 検証**: 一部の FIDO セキュリティ キーは、NIST SP 800-63B に基づいて設定された AAL3 のレベルで FIPS 140 の検証を受けています。
    - 「[Authenticator の保証レベル](https://learn.microsoft.com/ja-jp/entra/standards/nist-about-authenticator-assurance-levels)」を参照してください。
    - 「[Microsoft Entra ID を使用して NIST Authenticator Assurance Level 3](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-3) を参照してください」
    - nist.gov の [NIST Special Publication 800-63B のデジタル ID ガイドライン](https://pages.nist.gov/800-63-3/sp800-63b.html)を参照してください。

### フィッシングに強い多要素認証の実装に関する考慮事項

以下のセクションでは、アプリケーションと仮想デバイスのサインイン用にフィッシングに強い方法を実装するためのサポートについて説明します。

#### さまざまなクライアントからのアプリケーションのサインイン シナリオ

次の表は、アプリケーションへのサインインに使用されるデバイスの種類に基づき、フィッシングに強い多要素認証シナリオの可用性について詳しく示しています。

| デバイス | 証明書認証を使用したフェデレーション IdP としての AD FS | Microsoft Entra 証明書認証 | FIDO2 セキュリティキー | Windows Hello for Business | Microsoft Entra ハイブリッド参加または準拠デバイスを強制する条件付きアクセス ポリシーを利用した Microsoft Authenticator |
| --- | --- | --- | --- | --- | --- |
| Windows デバイス | [Image: 単色塗りつぶしのチェックマーク] | [Image: 単色塗りつぶしのチェックマーク] | [Image: 単色塗りつぶしのチェックマーク] | [Image: 単色塗りつぶしのチェックマーク] | [Image: 単色塗りつぶしのチェックマーク] |
| iOS モバイル デバイス | [Image: 単色塗りつぶしのチェックマーク] | [Image: 単色塗りつぶしのチェックマーク] | 該当なし | 該当なし | [Image: 単色塗りつぶしのチェックマーク] |
| Android モバイル デバイス | [Image: 単色塗りつぶしのチェックマーク] | [Image: 塗りつぶされたチェックマーク] | 該当なし | 該当なし | [Image: 単色塗りつぶしのチェックマーク] |
| macOS デバイス | [Image: 単色塗りつぶしのチェックマーク] | [Image: 単色塗りつぶしのチェックマーク] | Edge、Chrome | 該当なし | [Image: 単色塗りつぶしのチェックマーク] |

詳細: [FIDO2 パスワードレス認証のブラウザー サポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/fido2-compatibility)

#### 統合を必要とする仮想デバイスのサインイン シナリオ

フィッシングに強い多要素認証を強制するには、統合が必要になる場合があります。 アプリケーションとデバイスにアクセスするユーザーに対して多要素認証を強制してください。 フィッシングに強い多要素認証の 5 つの種類では、同じ機能を使用して次のデバイスの種類にアクセスします。

| ターゲット システム | 統合アクション |
| --- | --- |
| Azure Linux 仮想マシン (VM) | [Microsoft Entra サインインのために Linux VM](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-linux) を有効にする |
| Azure Windows VM | [Microsoft Entra サインインのために Windows VM](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows) を有効にする |
| Azure Virtual Desktop | [Azure Virtual Desktop での Microsoft Entra サインイン](https://learn.microsoft.com/ja-jp/azure/architecture/example-scenario/wvd/azure-virtual-desktop-azure-active-directory-join)を有効にする |
| オンプレミスまたは他のクラウドでホストされている VM | その VM で [Azure Arc](https://learn.microsoft.com/ja-jp/azure/azure-arc/overview) を有効にしてから、Microsoft Entra サインインを有効にします。 現在、Linux ではプライベート プレビューの段階にあります。 これらの環境でホストされている Windows VM のサポートは、ロードマップで予定されています。 |
| Microsoft 以外の仮想デスクトップ ソリューション | 仮想デスクトップ ソリューションを Microsoft Entra ID のアプリとして統合する |

#### フィッシングに強い多要素認証の強制

条件付きアクセスを使用して、テナント内のユーザーに対して多要素認証を強制します。 テナント間アクセス ポリシーの追加により、それを外部ユーザーに対して強制できます。

詳細: [概要: Microsoft Entra External ID を使用したテナント間アクセス](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)

##### 機関にまたがる強制

Microsoft Entra B2B コラボレーションを使用して、統合を容易にする次の要件を満たします:

- ユーザーがアクセスする他の Microsoft テナントを制限する
- 管理する必要がないテナント内のユーザーにアクセスを許可し、多要素認証とその他のアクセス要件を強制する

詳細: [B2B コラボレーションの概要](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)

組織のリソースにアクセスするパートナーと外部ユーザーに対して多要素認証を強制します。 このアクションは、機関にまたがるコラボレーション シナリオでは一般的です。 Microsoft Entra のテナント間アクセス ポリシーを使用して、アプリケーションやリソースにアクセスする外部ユーザーに対して多要素認証を構成します。

テナント間アクセス ポリシーで信頼の設定を構成して、ゲスト ユーザー テナントが使用する多要素認証方法を信頼します。 ユーザーが多要素認証方法をテナントに登録しないようにしてください。 これらのポリシーは、組織ごとに有効にします。 ユーザー ホーム テナントの多要素認証方法を決定し、フィッシング対策の要件を満たしているかどうかを判断できます。

### パスワード ポリシー

この覚書では、効果的でないパスワード ポリシー (ローテーションされる複雑なパスワードなど) を変更することを組織に要求しています。 要求内容には、特殊文字や数字に関する要件の削除のほか、時間ベースのパスワード ローテーション ポリシーの削除が含まれます。 次のオプションを代わりに検討してください。

- **パスワード保護**を使用して、Microsoft が維持する脆弱なパスワードの一般的な一覧を適用します。
    - さらに、カスタム禁止パスワードを含めます。
    - [Microsoft Entra パスワード保護を使って不適切なパスワードを排除する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad)を参照してください
- **セルフサービス パスワード リセット**を使用して、ユーザーがアカウントの回復後などにパスワードをリセットできるようにします。
    - [チュートリアル: Microsoft Entra のセルフサービス パスワード リセットを使ってユーザーが自分のアカウントのロック解除またはパスワードのリセットを実行できるようにする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)
- **Microsoft Entra ID Protection** を使用して、侵害された資格情報に関するアラートを取得します
- [リスクとは](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)

この覚書では、パスワードで使用するポリシーについて詳述されていませんが、NIST 800-63B の標準を検討してください。

[NIST Special Publication 800-63B のデジタル ID ガイドライン](https://pages.nist.gov/800-63-3/sp800-63b.html)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/memo-22-09-other-areas-zero-trust"} -->
## 覚書 22-09 他のゼロ トラストの領域 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-other-areas-zero-trust
- Service: entra / standards
- Article date: 2025-02-10
- Summary: 行政管理予算局の覚書 22-09 のその他のゼロ トラスト要件について理解します。

このガイダンスの他の記事では、米国管理予算局 (OMB) [メモ 22-09、および行政部門および機関の長宛ての覚書](https://bidenwhitehouse.archives.gov/wp-content/uploads/2022/01/M-22-09.pdf)で説明されているように、ゼロ トラスト原則のアイデンティティの柱に対処します。 この記事では、ID の柱を超えたゼロ トラストの成熟度モデル領域について、次のテーマを用いて説明します。

- 可視性
- アナリティクス
- オートメーションとオーケストレーション
- ガバナンス

### 可視性

Microsoft Entra テナントを監視することが重要です。 侵害の考え方を想定し、覚書22-09および21-31のコンプライアンス基準を満たし、サイバーセキュリティインシデントに関連する連邦政府の調査および修復機能を改善します。 セキュリティの分析とインジェストには、3 つの主要なログの種類が使用されます。

- **Azure 監査ログ**では、ユーザーやグループなどのオブジェクトの作成、削除、更新といった、ディレクトリの操作アクティビティを監視します
    - 条件付きアクセス ポリシーの変更など、Microsoft Entra 構成を変更する場合にも使用します
    - 「[Microsoft Entra ID の監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)」を参照してください
- **プロビジョニング ログ**には、Microsoft Identity Manager を使用して、Microsoft Entra から Service Now のようなアプリケーションに同期されたオブジェクトに関する情報が含まれます
    - 「[Microsoft Entra ID でのログのプロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)参照してください
- **Microsoft Entra サインイン ログ**では、ユーザー、アプリケーション、およびサービス プリンシパルに関連しているサインイン アクティビティを監視します。
    - サインイン ログには、識別用のカテゴリがあります
    - 対話型サインインでは、成功したサインインと失敗したサインイン、適用されたポリシー、およびその他のメタデータが表示されます
    - 非対話型ユーザー サインインでは、サインイン中の対話は表示されません (ユーザーの代わりにサインインする、モバイル アプリケーションや電子メール クライアントなどのクライアント)
    - サービス プリンシパルのサインインには、サービス プリンシパルまたはアプリケーション サインインが表示されます (REST API を介してサービス、アプリケーション、または Microsoft Entra ディレクトリにアクセスするサービスまたはアプリケーション)
    - Azure リソース サインイン用のマネージド ID (Azure SQL バックエンドに対して認証する Web アプリケーション サービスなどの Azure リソースにアクセスする Azure リソースまたはアプリケション)
    - 「[Microsoft Entra ID のサインイン ログ (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)」を参照してください

Microsoft Entra ID 無料テナントでは、ログ エントリは 7 日間保存されます。 Microsoft Entra ID P1 または P2 ライセンスを持つテナントは、ログ エントリを 30 日間保持します。

セキュリティ情報およびイベント管理 (SIEM) ツールがログを取り込んでいることを確認します。 サインインと監査のイベントを使用して、アプリケーション、インフラストラクチャ、データ、デバイス、ネットワーク ログに関連付けます。

Microsoft Entra ログを Microsoft Sentinel と統合することをお勧めします。 Microsoft Entra テナント ログを取り込むコネクタを構成します。

詳細情報:

- [Microsoft Sentinel とは](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)
- [Microsoft Entra ID を Microsoft Sentinel に接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)

Microsoft Entra テナントについては、診断設定を構成して、データを Azure Storage アカウント、Azure Event Hubs、Log Analytics ワークスペースのいずれかに送信することができます。 これらのストレージ オプションを使用して、データを収集する他の SIEM ツールを統合します。

詳細情報:

- [Microsoft Entra 監視とは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)
- [Microsoft Entra のレポートと監視のデプロイの依存関係](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/plan-monitoring-and-reporting)

### アナリティクス

次のツールで分析を使って、Microsoft Entra ID から情報を集計し、セキュリティ体制の傾向をベースラインと比較して示すことができます。 また、Microsoft Entra ID 全体のパターンや脅威を評価して探すために、分析を使うこともできます。

- **Microsoft Entra ID 保護**では、サインインと他のテレメトリ ソースを分析して、危険な行動を探します
    - ID 保護が、サインイン イベントにリスク スコアを割り当てます
    - サインインを禁止したり、ステップアップ認証を強制して、リソースやアプリケーションへリスク スコアに基づいてアクセスします
    - 「[ID 保護とは](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)」を参照してください
- **Microsoft Entra の使用状況と分析情報のレポート**には、使用量が最も多いアプリケーションや、サインインの傾向など、Azure Sentinel ブックと似た情報が含まれます。
    - レポートを使用して、攻撃またはその他のイベントを示す可能性がある集計傾向を把握します
    - [「Microsoft Entra ID の使用状況と分析情報](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report)」を参照してください
- **Microsoft Sentinel**を使って Microsoft Entra ID の情報を分析します。
    - Microsoft Sentinel のユーザーとエンティティの行動分析 (UEBA) は、ユーザー、ホスト、IP アドレス、アプリケーション エンティティからの潜在的な脅威に関するインテリジェンスを提供します。
    - Microsoft Entra ログの脅威とアラートを追求する分析ルール テンプレートを使います。 セキュリティまたは運用アナリストが、脅威をトリアージして修正することができます。
    - Microsoft Sentinel ブックが Microsoft Entra データ ソースを視覚化する際に役立ちます。 国/リージョン、またはアプリケーション別のサインインを参照してください。
    - 「[一般的に使用される Microsoft Sentinel ブック](https://learn.microsoft.com/ja-jp/azure/sentinel/top-workbooks)」を参照してください
    - 「[収集されたデータを視覚化する](https://learn.microsoft.com/ja-jp/azure/sentinel/get-visibility)」を参照してください
    - 「[Microsoft Sentinel のユーザー/エンティティ行動分析 (UEBA) を使用して高度な脅威を特定する](https://learn.microsoft.com/ja-jp/azure/sentinel/identify-threats-with-entity-behavior-analytics)」を参照してください

### オートメーションとオーケストレーション

ゼロ トラストのオートメーションは、脅威やセキュリティの変更によるアラートの修復に役立ちます。 Microsoft Entra ID では、オートメーション統合により、セキュリティ体制を改善するためのアクションを明確にすることができます。 オートメーションのベースになるのが、監視と分析から受け取った情報です。

Microsoft Graph API REST 呼び出しを使用して、Microsoft Entra ID にプログラムでアクセスします。 このアクセスには、承認とスコープを持つMicrosoft Entra ID が必要です。 Graph API を使用することで、他のツールを統合します。

システム割り当てマネージド ID を使用するように Azure 関数や Azure ロジック アプリを設定することをお勧めします。 このロジック アプリや機能には、アクションをオートメーション化するステップやコードがあります。 サービス プリンシパルのディレクトリ アクセス許可を付与するためのアクセス許可をマネージド ID に割り当てて、アクションを実行します。 マネージド ID に最小限の権限を付与します。

詳しくは、「[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください

もうひとつのオートメーション統合ポイントが、Microsoft Graph PowerShell モジュールです。 Microsoft Graph PowerShell を使用して、Microsoft Entra ID で一般的なタスクや構成を実行するか、Azure 関数または Azure Automation Runbook に組み込みます。

### ガバナンス

Microsoft Entra 環境を運用するためのプロセスを文書化します。 Microsoft Entra ID のスコープに適用されるガバナンス機能には、Microsoft Entra 機能を使用します。

詳細情報:

- [Microsoft Entra ID ガバナンス操作リファレンス ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-govern)
- [Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)
- [Microsoft Entra ID Governance とは](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)
- [覚書 22-09 の認可要件を満たす](https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-authorization)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/nist-about-authenticator-assurance-levels"} -->
## Microsoft Entra ID を持つ NIST 認証システムの保証レベル - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/nist-about-authenticator-assurance-levels
- Service: entra / standards
- Article date: 2022-11-23
- Summary: Microsoft Entra ID に適用される認証システムの保証レベルの概要

米国国立標準技術研究所 (NIST) は、ID ソリューションを実装する米国政府機関の技術要件の策定を行っています。 [NIST SP 800-63B](https://pages.nist.gov/800-63-3/sp800-63b.html) には、Authenticator Assurance Level (AAL) フレームワークを使用したデジタル認証の実装に関する技術的なガイドラインがあります。 AAL はデジタル ID の認証の強度を特徴付けしたものです。 失効を含む認証ライフサイクル管理についても学習できます。

標準には、以下のカテゴリの AAL 要件が含まれています。

- 許可される Authenticator の種類
- Federal Information Processing Standards 140 (FIPS 140) 検証レベル。 FIPS 140 の要件は、[FIPS 140-2](https://csrc.nist.gov/publications/detail/fips/140/2/final) 以降のリビジョンによって満たされます。
- 再認証
- セキュリティ コントロール
- 中間者攻撃 (MitM) への耐性
- 検証ツール偽装への耐性 (フィッシングへの耐性)
- 検証ツール侵害への耐性
- リプレイへの耐性
- 認証意図
- 記録アイテム保有ポリシー
- プライバシー管理

### 環境内の NIST AAL

一般に、AAL1 はパスワードのみのソリューションを受け入れるため、推奨されておらず、最も侵害されやすい認証です。 詳細については、「[あなたの Pa$$word は関係ありません](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/your-pa-word-doesn-t-matter/ba-p/731984)」というブログ記事を参照してください。

NIST では、AAL3 までの検証ツールの偽装 (資格情報のフィッシング) への耐性を必要としませんが、すべてのレベルでこの脅威に対処することが推奨されます。 デバイスを Microsoft Entra ID またはハイブリッド Microsoft Entra ID に参加させる必要があるなど、検証者の偽装耐性を提供する認証子を選択できます。 Office 365 を使用している場合は、Office 365 Advanced Threat Protection、[フィッシング対策ポリシー](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/anti-phishing-policies-about)を使用できます。

組織に必要な NIST AAL を評価する際には、組織全体が NIST の標準を満たす必要があるかどうかを検討してください。 特定のユーザーのグループとリソースを分離できる場合は、それらのユーザー グループとリソースに NIST AAL の構成を適用できます。

ヒント

少なくとも AAL2 とフィッシング耐性を満たすようにすることをお勧めします。 ビジネス上の理由、業界標準、またはコンプライアンス要件により必要な場合は、AAL3 を満たすようにします。

### セキュリティ コントロール、プライバシー管理、レコード保持ポリシー

Azure と Azure Government は、[NIST SP 800-53 High Impact レベル](https://nvd.nist.gov/800-53/Rev4/impact/high)の P-ATO (Provisional Authority to Operate) を合同認定委員会から獲得しています。 この FedRAMP 認定は、Azure と Azure Government が機密性の高いデータを処理することを認可しています。

重要

Azure および Azure Government の認定は、AAL1、AAL2、AAL3 のセキュリティ コントロール、プライバシー管理、記録アイテム保有ポリシーの要件を満たしています。

Azure と Azure Government の FedRAMP 監査には、対象サービスのインフラストラクチャ、開発、運用、管理、およびサポートのための情報セキュリティ管理システムが含まれます。 P-ATO が与えられている場合、クラウド サービス プロバイダーは、提携している政府機関からの認可 (ATO) が必要となります。 政府機関、または組織は、それぞれのセキュリティ認可プロセスで Azure P-ATO を使用し、FedRAMP の要件を満たす機関 ATO を発行するための基礎としてそれを利用できます。

Azure では、FedRAMP High Impact で複数のサービスをサポートしています。 Azure パブリック クラウドの FedRAMP High は米国政府のお客様のニーズを満たしますが、より厳格な要件を持つ機関は Azure Government を使用します。 Azure Government セーフガードには、職員のスクリーニングの強化が含まれます。 Azure Government では、Microsoft は利用可能な Azure パブリック サービス (FedRAMP High 境界まで) と、本年度のサービスを一覧表示します。

さらに、Microsoft は、明確に規定されたレコード保持ポリシーによって、[顧客データの保護と管理](https://www.microsoft.com/trust-center/privacy/data-management)にコミットしています。 Microsoft には、大規模なコンプライアンス ポートフォリオがあります。 詳細については、「[Microsoft コンプライアンス認証](https://learn.microsoft.com/ja-jp/compliance/regulatory/offering-home)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/nist-authentication-basics"} -->
## NIST 認証の基本とMicrosoft Entra ID - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/nist-authentication-basics
- Service: entra / standards
- Article date: 2022-11-23
- Summary: この記事では、用語を定義し、トラステッド プラットフォーム モジュールについて説明して、NIST 認証要素の一覧を示します

この記事の情報を使用して、米国標準技術局 (NIST) のガイドラインに関連する用語について確認してください。 また、トラステッド プラットフォーム モジュール (TPM) テクノロジと認証要素の概念も定義しています。

### 用語

次の表を使用して、NIST の用語を理解します。

| 期間 | 定義 |
| --- | --- |
| アサーション | "*サブスクライバー*" に関する情報が含まれている、"*検証者*" から "*証明書利用者*" への声明。 アサーションには検証済みの属性が含まれる場合があります |
| 認証 | "サブジェクト" の ID を検証するプロセス |
| 認証要素 | ユーザー自身、ユーザーが知っている、または持っているもの。 すべての "認証子" には、1 つまたは複数の認証要素があります |
| 認証システム | "認証要求者" の ID を認証するための "認証要求者" が所有して制御するもの |
| 認証要求者 | 1 つ以上の "認証" プロトコルを使用して検証される "サブジェクト" ID |
| 資格情報 | "サブスクライバー" が所有して制御する少なくとも 1 つの "サブスクライバー認証子" に、正式に ID をバインドするオブジェクトまたはデータ構造 |
| 資格情報サービス プロバイダー (CSP) | "サブスクライバー認証子" を発行または登録し、"サブスクライバー" に電子 "*資格情報*" を発行する、信頼されたエンティティ |
| 証明書利用者 | 通常、システムへのアクセスを許可するために、"検証者のアサーション" または "*認証要求者の認証子*" と "*資格情報*" に依存するエンティティ |
| サブジェクト | 個人、組織、デバイス、ハードウェア、ネットワーク、ソフトウェア、またはサービス |
| サブスクライバー (Subscriber) | "CSP" から "資格情報" または "認証子" を受け取った関係者 |
| トラステッド プラットフォーム モジュール (TPM) | キーの生成などの暗号化操作を行う改ざん防止モジュール |
| 検証方法 | 認証要求者が "認証子" を所有および制御していることを検証することにより、"認証要求者" の ID を確認するエンティティ |

### トラステッド プラットフォーム モジュール テクノロジについて

TPM にはハードウェア ベースのセキュリティ関連機能があります。TPM チップまたはハードウェア TPM は、暗号化キーの生成、保存、使用制限に役立つ、セキュリティで保護された暗号プロセッサです。

TPM と Windows については、「[トラステッド プラットフォーム モジュール](https://learn.microsoft.com/ja-jp/windows/security/hardware-security/tpm/trusted-platform-module-top-node)」を参照してください。

注

ソフトウェア TPM は、ハードウェア TPM の機能を模倣するエミュレーターです。

### 認証要素とその強度

認証要素は、3 つのカテゴリに分類できます。

[Image: 認証要素の図。ユーザー自身、知っている、または持っているものによってグループ化されています]

認証要素の強度は、それがサブスクライバー自身、知っていること、または持っているもののみであることをどの程度確信できるかによって決まります。 NIST 組織からは、認証要素の強度に関する限定されたガイダンスが提供されています。 次のセクションの情報を使用して、Microsoft が強度を評価する方法を確認してください。

#### ユーザーが知っているもの

パスワードは、最も一般的な知っていることであり、最大の攻撃対象領域を表します。 以下の軽減策により、サブスクライバーの信頼性が向上します。 ブルートフォース、盗聴、ソーシャル エンジニアリングなどのパスワード攻撃を防ぐのに効果的です。

- [パスワードの複雑さの要件](https://www.microsoft.com/research/wp-content/uploads/2016/06/Microsoft_Password_Guidance-1.pdf)
- [禁止パスワード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-configure-custom-password-protection)
- [漏洩した資格情報の特定](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)
- [セキュリティで保護されたハッシュされたストレージ](https://aka.ms/AADDataWhitepaper)
- [アカウントのロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)

#### ユーザーが持っているもの

ユーザーが持っているものの強度は、攻撃者がそれにアクセスすることなく、サブスクライバーがそれを所有し続ける可能性に基づいています。 たとえば、内部の脅威から保護するときは、個人のモバイル デバイスやハードウェア キーは、アフィニティが高くなります。 デバイス、またはハードウェア キーは、オフィス内のデスクトップ コンピューターより安全性が高くなります。

#### ユーザー自身

ユーザー自身に対する要件を決定する場合は、攻撃者にとって生体認証のようなものを取得したり、なりすましを行ったりするのがいかに簡単であるかを検討してください。 NIST は、生体認証のフレームワークを起草していますが、現時点で、単一要素として生体認証を受け入れていません。 多要素認証 (MFA) の一部である必要があります。 この予防措置は、生体認証がパスワードと同様に常に完全一致を提供するとは限らないためです。 詳細については、[認証システムの機能の強度 - 生体認証](https://pages.nist.gov/SOFA/SOFA.html) (SOFA-B) に関するドキュメントを参照してください。

生体認証の強度を定量化する SOFA-B フレームワーク:

- 誤合致率
- 偽不一致率
- 指示型攻撃検知誤り率
- 攻撃を実行するために必要な労力

### 単一要素認証

単一要素認証は、ユーザーが知っていることやユーザー自身を検証する認証子を使用することによって実装できます。 ユーザー自身の要素は認証として受け入れられますが、それだけでは認証子として受け入れられません。

[Image: 単一要素認証のしくみ]

### 多要素認証

MFA 認証子または 2 つの単一要素認証子を使用して、MFA を実装できます。 MFA 認証システムでは、1 つの認証トランザクションに 2 つの認証要素が必要です。

#### 2 つの単一要素認証子を使用した MFA

MFA には、2 つの認証要素が必要で、それらは独立していてもかまいません。 次に例を示します。

- 記憶シークレット (パスワード) と帯域外 (SMS)
- 記憶シークレット (パスワード) とワンタイム パスワード (ハードウェアまたはソフトウェア)

これらの方法により、Microsoft Entra ID を持つ 2 つの独立した認証トランザクションが有効になります。

[Image: 2 つの認証子を使用した MFA]

#### 1 つの多要素認証子を使用した MFA

多要素認証では、2 つ目の要素のロックを解除するために、1 つの要素 (ユーザーが知っていること、 またはユーザー自身) が必要です。 このユーザー エクスペリエンスは、複数の独立した認証システムより簡単です。

[Image: 単一の多要素認証子を使用した MFA]

1 つの例は、パスワードレス モードの Microsoft Authenticator アプリです。ユーザーはセキュリティで保護されたリソース (証明書利用者) にアクセスし、Authenticator アプリで通知を受け取ります。 ユーザーは、生体認証 (ユーザー自身) または PIN (ユーザーが知っていること) を提供します。 この要素により、検証者によって検証される電話 (ユーザーが持っているもの) で暗号化キーのロックが解除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/nist-authenticator-assurance-level-1"} -->
## Microsoft Entra ID を使用して NIST AAL1 を実現する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-1
- Service: entra / standards
- Article date: 2022-12-08
- Summary: Microsoft Entra ID を使用して NIST Authenticator Assurance Level 1 (AAL1) を達成するためのガイダンスです。

米国国立標準技術研究所 (NIST) は、ID ソリューションを実装する米国政府機関の技術要件の策定を行っています。 組織は、連邦政府機関と協力している場合、これらの要件を満たしている必要があります。

Authenticator Assurance Level 1 (AAL1) を開始する前に、次のリソースを確認できます。

- [NIST の概要](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview): AAL レベルについて説明します
- [認証の基本](https://learn.microsoft.com/ja-jp/entra/standards/nist-authentication-basics): 用語と認証の種類
- [NIST Authenticator の種類](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-types): Authenticator の種類
- [NIST AAL](https://learn.microsoft.com/ja-jp/entra/standards/nist-about-authenticator-assurance-levels): AAL コンポーネント、Microsoft Entra 認証方法、およびトラステッド プラットフォーム モジュール (TPM)。

### 許可される Authenticator の種類

AAL1 の達成には、単一要素認証または多要素認証に関して NIST で[許可されている Authenticator](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-types) であればどれでも使用できます。

| Microsoft Entra の認証方法 | NIST 認証システムの種類 |
| --- | --- |
| Password QR コード (PIN) | 記憶シークレット |
| 電話 (SMS): 推奨されません | 単一要素帯域外 |
| Microsoft Authenticator アプリ (電話によるサインイン) | 多要素帯域外 |
| 単一要素ソフトウェア証明書 | 単一要素暗号化ソフトウェア |
| 多要素ソフトウェア証明書 ソフトウェア TPM と Windows Hello for Business | 多要素の暗号化ソフトウェア |
| 多要素ハードウェア保護証明書 FIDO 2 セキュリティ キー macOS 用プラットフォーム SSO (Secure Enclave) Windows Hello for Business (ハードウェア TPM を使用)  Microsoft Authenticator でのパスキー | 多要素の暗号化ハードウェア |

ヒント

最小限のフィッシング対策 AAL2 認証システムを選択することをお勧めします。 ビジネス上の理由、業界標準、またはコンプライアンス要件により必要な場合は、AAL3 認証システムを選択します。

### FIPS 140 検証

#### 検証の要件

Microsoft Entra ID では、認証暗号化操作に、Windows FIPS 140 レベル 1 の暗号モジュールが使用されています。 したがって、これは、政府機関が必要としている FIPS 140 に準拠した検証ツールです。

### 中間者への耐性

中間者 (MitM) 攻撃への耐性を実現するために、認証要求者と Microsoft Entra ID の間の通信は認証済みの保護されたチャネルを介して実行されます。 この構成は、AAL1、AAL2、および AAL3 の MitM 耐性要件を満たしています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/nist-authenticator-assurance-level-2"} -->
## Microsoft Entra ID を使用して NIST AAL2 を実現する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-2
- Service: entra / standards
- Article date: 2024-09-27
- Summary: Microsoft Entra ID を使用して NIST Authenticator Assurance Level 2 (AAL2) を達成するためのガイダンスです。

米国国立標準技術研究所 (NIST) は、ID ソリューションを実装する米国政府機関の技術要件の策定を行っています。 連邦政府機関と協力している組織は、これらの要件を満たしている必要があります。

Authenticator Assurance Level 2 (AAL2) を開始する前に、次のリソースを参照できます。

- [NIST の概要](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview): AAL レベルについて説明します
- [認証の基本](https://learn.microsoft.com/ja-jp/entra/standards/nist-authentication-basics): 用語と認証の種類
- [NIST Authenticator の種類](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-types): Authenticator の種類
- [NIST AAL](https://learn.microsoft.com/ja-jp/entra/standards/nist-about-authenticator-assurance-levels): AAL コンポーネントと Microsoft Entra 認証方法

### 許可される AAL2 Authenticator の種類

次の表に、AAL2 で許可される Authenticator の種類を示します。

| Microsoft Entra の認証方法 | フィッシングに強い | NIST Authenticator の種類 |
| --- | --- | --- |
| **推奨される方法** |  |  |
| 多要素ソフトウェア証明書 Windows Hello for Business (ソフトウェア トラステッド プラットフォーム モジュール (TPM) を使用) | はい | 多要素の暗号化ソフトウェア |
| 多要素ハードウェア保護証明書 FIDO 2 セキュリティ キー macOS 用プラットフォーム SSO (Secure Enclave) Windows Hello for Business (ハードウェア TPM を使用)  Microsoft Authenticator でのパスキー | はい | 多要素の暗号化ハードウェア |
| **他の方法** |  |  |
| Microsoft Authenticator アプリ (電話でのサインイン) | いいえ | 多要素認証のための帯域外コミュニケーション |
| パスワード **または** QR コード (PIN) **そして**- Microsoft Authenticator アプリ (プッシュ通知)  - **又は**- Microsoft Authenticator Lite (プッシュ通知)  - **又は**- 電話 (SMS) | いいえ | 記憶された秘密 **そして** 単一要素帯域外 |
| パスワード **または** QR コード (PIN) **そして**- OATH ハードウェア トークン (プレビュー)  - **又は**- Microsoft Authenticator アプリ (OTP) - **又は**- Microsoft Authenticator Lite (OTP) - **又は**OATH ソフトウェア トークン | いいえ | 記憶された秘密 **そして**単要素認証 OTP |
| パスワード **または** QR コード (PIN) **そして**- 単一要素ソフトウェア証明書  - **又は**- Microsoft Entra とソフトウェア TPM の統合  - **又は**- Microsoft Entra ハイブリッドとソフトウェア TPM の結合  - **又は**- 準拠モバイル デバイス | ○^1^ | 記憶された秘密 **そして** 単一要素暗号化ソフトウェア |
| パスワード **または** QR コード (PIN) **そして**- Microsoft Entra とハードウェア TPM の結合  - **又は**- Microsoft Entra ハイブリッドとハードウェア TPM の結合 | ○^1^ | 記憶された秘密 **そして**単一要素暗号化ハードウェア |

^1^[外部フィッシングからの保護](https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-multi-factor-authentication#protection-from-external-phishing)

#### AAL2 の推奨事項

AAL2 の場合は、多要素暗号認証システムを使用します。 これはフィッシングに強く、最大の攻撃面 (パスワード) を排除し、効率的な認証方法をユーザーに提供することができます。

パスワードレス認証方法の選択に関するガイダンスについては、「[Microsoft Entra ID でパスワードレス認証のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-deployment)」を参照してください。 「[Windows Hello for Business の展開ガイド](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-deployment-guide)」も参照してください

### FIPS 140 検証

FIPS 140 の検証については、次のセクションを参照してください。

#### 検証の要件

Microsoft Entra ID では、認証暗号化操作に、Windows FIPS 140 レベル 1 (総合的) の検証が行われた暗号モジュールが使用されています。 したがって、これは、政府機関が必要としている FIPS 140 に準拠した検証ツールです。

#### 認証システムの要件

政府機関の暗号化認証システムは、FIPS 140 レベル 1 (総合的) で検証されます。 これは、非政府機関には必要ありません。 次の Microsoft Entra 認証子は、[FIPS 140 承認済みモードの Windows](https://learn.microsoft.com/ja-jp/windows/security/security-foundations/certification/fips-140-validation) 上で実行されている場合の要件を満たしています。

- パスワード
- Microsoft Entra はソフトウェアまたはハードウェア TPM を使用して参加します。
- ソフトウェアまたはハードウェア TPM を使用して参加した Microsoft Entra ハイブリッド
- ソフトウェアまたはハードウェア TPM を搭載した Windows Hello for Business
- ソフトウェアまたはハードウェア (スマートカード/セキュリティ キー/TPM) に格納されている証明書

Microsoft Authenticator アプリ (iOS/Android) の FIPS 140 準拠情報については、「[Microsoft Entra 認証に準拠した FIPS 140](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app#fips-140-compliant-for-microsoft-entra-authentication)」を参照してください

OATH ハードウェア トークンとスマートカードについては、ご自身のプロバイダーに現在の FIPS 検証状態についてお問い合わせいただくことをお勧めします。

FIDO 2 セキュリティ キー プロバイダーは、FIPS 認定のさまざまな段階にあります。 [サポートされている FIDO 2 の主要ベンダー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)の一覧を確認することをお勧めします。 現在の FIPS 検証の状態については、プロバイダーに問い合わせてください。

macOS 用 Platform SSO は FIPS 140 に準拠しています。 [Apple プラットフォーム認定](https://support.apple.com/guide/certifications/apc3a7433eb89/web)を参照することをお勧めします。

### 再認証

AAL2 では、NIST はユーザーの利用状況に関係なく、12 時間ごとに再認証を必要とします。 再認証は、非アクティブな状態が 30 分以上続いた後に必要になります。 セッション シークレットはユーザーが所有しているものなので、ユーザーが知っているものまたはユーザー自身の提示が必要です。

ユーザーの利用状況に関係なく再認証の要件を満たすために、Microsoft では[ユーザーのサインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)を 12 時間に構成することをお勧めします。

NIST では、補正コントロールを使用して、サブスクライバーの存在を確認できます。

- セッションの無通信のタイムアウトを 30 分に設定する。Microsoft System Center Configuration Manager、グループ ポリシー オブジェクト (GPO)、または Intune を使用して、オペレーティング システム レベルでデバイスをロックします。 サブスクライバーがロックを解除するには、ローカル認証が必要です。
- 利用状況に関係なくタイムアウトする。スケジュール化されたタスクを実行して (Configuration Manager、GPO、または Intune)、利用状況に関係なく 12 時間後にマシンをロックします。

### 中間者攻撃への耐性

認証要求者と Microsoft Entra ID の間の通信は、認証済みの保護されたチャネルを介して行われます。 この構成は、中間者 (MitM) 攻撃に対する耐性を提供し、AAL1、AAL2、AAL3 の MitM 耐性要件を満たしています。

### リプレイへの耐性

AAL2 の Microsoft Entra 認証方法では、nonce またはチャレンジが使用されます。 これらの方法は、再生された認証トランザクションを検証者が検出できるため、再生攻撃に対する耐性があります。 このようなトランザクションには、必要な nonce や適時性のデータが含まれていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/nist-authenticator-assurance-level-3"} -->
## Microsoft Entra ID を使用して NIST AAL3 を実現する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-3
- Service: entra / standards
- Article date: 2022-09-13
- Summary: この記事では、Microsoft Entra ID を使用して NIST 認証保証レベル 3 (AAL3) を達成するためのガイダンスを提供します。

米国国立標準技術研究所 (NIST) Authenticator Assurance Level 3 (AAL3) については、この記事の情報を参照してください。

AAL2 を取得する前に、次のリソースを確認できます。

- [NIST の概要](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview): AAL レベルについて説明します
- [認証の基本](https://learn.microsoft.com/ja-jp/entra/standards/nist-authentication-basics): 用語と認証の種類
- [NIST Authenticator の種類](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-types): Authenticator の種類
- [NIST AAL](https://learn.microsoft.com/ja-jp/entra/standards/nist-about-authenticator-assurance-levels): AAL コンポーネントと Microsoft Entra 認証方法

### 許可される Authenticator の種類

Microsoft の認証方法を使用して、必要な NIST Authenticator の種類に対応します。

| Microsoft Entra 認証方法 | NIST 認証システムの種類 |
| --- | --- |
| **推奨される方法** |  |
| 多要素ハードウェア保護証明書 FIDO 2 セキュリティ キー macOS 用プラットフォーム SSO (Secure Enclave) Windows Hello for Business (ハードウェア TPM を使用)  Microsoft Authenticator のパスキー^1^ | 多要素暗号化ハードウェア |
| **他の方法** |  |
| パスワード **または** QR コード (PIN) **そして**単一要素ハードウェア保護証明書 | 記憶シークレット **そして**単一要素暗号化ハードウェア |

^1^ Microsoft Authenticator のパスキーは、全体的には部分的な AAL3 と見なされ、FIPS 140 Level 2 Overall (またはそれ以上) と FIPS 140 level 3 physical security (またはそれ以上) を備えたプラットフォームでは AAL3 として通用します。 Microsoft Authenticator (iOS/Android) の FIPS 140 準拠に関する追加情報については、「[Microsoft Entra 認証の FIPS 140 準拠](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app#fips-140-compliant-for-microsoft-entra-authentication)」を参照してください

#### 推奨事項

AAL3 では、パスワードレス認証を提供する多要素暗号化ハードウェア認証システムを使用して、最大の攻撃対象領域であるパスワードを削除することをお勧めします。

ガイダンスについては、「[Microsoft Entra ID でのパスワードレス認証のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-deployment)」を参照してください。 「[Windows Hello for Business の展開ガイド](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-deployment-guide)」も参照してください。

### FIPS 140 検証

#### 検証の要件

Microsoft Entra ID では、認証暗号化操作に、Windows FIPS 140 レベル 1 (総合的) の検証が行われた暗号モジュールが使用されているため、Microsoft Entra ID は準拠した検証ツールです。

#### 認証システムの要件

単一要素と多要素の暗号化ハードウェア認証システム要件。

##### 単一要素暗号化ハードウェア

認証システムの要件は次のとおりです。

- FIPS 140 レベル 1 (総合的) またはそれ以上
- FIPS 140 レベル 3 の物理的なセキュリティまたはそれ以上

Windows デバイスで使用される単一要素ハードウェア保護証明書は、次の場合にこの要件を満たします。

- [FIPS-140 承認済みモードで Windows](https://learn.microsoft.com/ja-jp/windows/security/security-foundations/certification/fips-140-validation) を実行している
- TPM が FIPS 140 レベル 1 (総合的) またはそれ以上で、FIPS 140 レベル 3 の物理的なセキュリティを備えたマシン

    - 準拠している TPM は、[暗号化モジュール検証プログラム](https://csrc.nist.gov/Projects/cryptographic-module-validation-program/validated-modules/Search)でトラステッド プラットフォーム モジュールと TPM を検索して見つけてください。

FIPS 140 の準拠については、モバイル デバイスのベンダーに問い合わせてください。

##### 多要素暗号化ハードウェア

認証システムの要件は次のとおりです。

- FIPS 140 レベル 2 (総合的) またはそれ以上
- FIPS 140 レベル 3 の物理的なセキュリティまたはそれ以上

FIDO 2 セキュリティ キー、スマート カード、Windows Hello for Business は、これらの要件を満たすのに役立ちます。

- 複数の FIDO2 セキュリティ キー プロバイダーが FIPS 要件を満たしています。 [サポートされている FIDO2 の主要ベンダー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-hardware-vendor)の一覧を確認することをお勧めします。 現在の FIPS 検証の状態については、プロバイダーに問い合わせてください。
- スマート カードは、実績のあるテクノロジです。 複数のベンダー製品が FIPS 要件を満たしています。

    - 詳細については、「[暗号化モジュール検証プログラム](https://csrc.nist.gov/Projects/cryptographic-module-validation-program/validated-modules/Search)」を参照してください

**Windows Hello for Business**

FIPS 140 では、ソフトウェア、ファームウェア、ハードウェアを含む暗号境界を評価の対象にすることを求めています。 Windows オペレーティング システムは、これらの何千もの組み合わせとペアにすることができます。 そのため、Microsoft が FIPS 140 セキュリティ レベル 2 でWindows Hello for Business を検証することはできません。 連邦のお客様は、このサービスを AAL3 として受け入れる前に、リスク評価を実施し、リスクの容認の一環として次の各コンポーネント認定を評価する必要があります。

- **Windows 10 および Windows Server** では、National Information Assurance Partnership (NIAP) の [US Government Approved Protection Profile for General Purpose Operating Systems バージョン 4.2.1](https://www.niap-ccevs.org/Profile/Info.cfm?PPID=442&amp;id=442) を使用します。 この組織は、民生 (COTS) 情報技術製品が国際コモン クライテリアに準拠しているかを評価する国内プログラムを監督しています。
- **Windows 暗号化ライブラリ**は、NIST とカナダ サイバー セキュリティ センターの共同作業である [NIST 暗号化モジュール検証プログラム (CMVP) の FIPS レベル 1 (総合的)](https://csrc.nist.gov/Projects/cryptographic-module-validation-program/Certificate/3544) を達成しています。 この組織では、FIPS 標準に対して暗号化モジュールを検証します。
- FIPS 140 レベル 2 (総合的)、および FIPS 140 レベル 3 の物理的なセキュリティである**トラステッド プラットフォーム モジュール (TPM)** を選択してください。 組織は、ハードウェア TPM が必要な AAL レベルの要件を満たしていることを確認します。

現在の標準を満たす TPM を確認するには、[NIST コンピューター セキュリティ リソース センターの暗号化モジュール検証プログラム](https://csrc.nist.gov/Projects/cryptographic-module-validation-program/validated-modules/Search)に関するページを参照してください。 標準を満たすハードウェア TPM の一覧については、**[モジュール名]** ボックスに「**Trusted Platform Module**」と入力します。

**MacOS Platform SSO**

Apple macOS 13 (およびそれ以降) は FIPS 140 Level 2 Overall であり、ほとんどのデバイスは FIPS 140 Level 3 Physical Security にも対応しています。 [Apple プラットフォーム認定](https://support.apple.com/guide/certifications/apc3a7433eb89/web)を参照することをお勧めします。

##### Microsoft Authenticator でのパスキー

Microsoft Authenticator (iOS/Android) の FIPS 140 準拠に関する追加情報については、「[Microsoft Entra 認証の FIPS 140 準拠](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app#fips-140-compliant-for-microsoft-entra-authentication)」を参照してください

### 再認証

AAL3 では、NIST はユーザーの利用状況に関係なく、12 時間ごとに再認証を必要とします。 再認証は、アイドル時間が 15 分以上続いた後に推奨されます。 両方の要素の提示が必要です。

ユーザーの利用状況に関係なく再認証の要件を満たすために、Microsoft では[ユーザーのサインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)を 12 時間に構成することをお勧めします。

NIST により、サブスクライバーの存在を確認するための補正制御が可能になります。

- Configuration Manager、GPO、または Intune を使用してスケジュール化されたタスクを実行し、利用状況に関係なくタイムアウトを設定します。 利用状況に関係なく、12 時間後にマシンをロックします。
- 推奨アイドル時間タイムアウトの場合、セッションのアイドル時間によるタイムアウトを 15 分に設定する: Microsoft Configuration Manager、グループ ポリシー オブジェクト (GPO)、または Intune を使用して、OS レベルでデバイスをロックします。 サブスクライバーがロックを解除するには、ローカル認証が必要です。

### 中間者への耐性

中間者 (MitM) 攻撃への耐性を実現するために、認証要求者と Microsoft Entra ID の間の通信は認証済みの保護されたチャネルを介して実行されます。 この構成は、AAL1、AAL2、および AAL3 の MitM 耐性要件を満たしています。

### 検証者の偽装耐性

AAL3 を満たしている Microsoft Entra 認証方法では、認証されているセッションに認証システムの出力をバインドする暗号化認証システムを使用します。 これらの方法では、認証要求者によって制御される秘密キーを使用します。 公開キーは検証者に認識されます。 この構成は、AAL3 の検証者の偽装耐性要件を満たします。

### 検証者の侵害耐性

AAL3 を満たすすべての Microsoft Entra 認証方法:

- 暗号認証システムによって保持されている秘密キーに対応する公開キーを検証者が格納することを求める認証システムを使用します
- FIPS-140 検証済みハッシュ アルゴリズムを使用して、予期される認証システムの出力を格納します

詳細については、[Microsoft Entra データのセキュリティに関する考慮事項](https://aka.ms/AADDataWhitepaper)に関するファイルを参照してください。

### リプレイへの耐性

AAL3 を満たしている Microsoft Entra 認証方法では、nonce またはチャレンジを使用します。 これらの方法は、再生された認証トランザクションを検証者が検出できるため、再生攻撃に対する耐性があります。 このようなトランザクションには、必要な nonce や適時性のデータが含まれていません。

### 認証意図

認証意図を要求することで、多要素暗号化ハードウェアなど、直接接続された物理認証システムを、(エンドポイントのマルウェアなどによって) 利用者の知らないうちに使用されることがより困難になります。 AAL3 を満たす Microsoft Entra 方法では、認証意図を示すピンまたは生体認証によるユーザー入力が必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/nist-authenticator-types"} -->
## NIST 認証子の種類と対応する Microsoft Entra のメソッド - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-types
- Service: entra / standards
- Article date: 2022-11-23
- Summary: Microsoft Entra の認証方法を NIST 認証子の種類と連携させる方法についての説明。

請求者が、サブスクライバーに関連付けられている複数の認証子のいずれかの制御をアサートすると、認証プロセスが開始されます。 サブスクライバーは、個人または別のエンティティです。 次の表を使用して、米国標準技術局 (NIST) の認証子の種類と、関連する Microsoft Entra の認証方法について説明します。

| NIST 認証システムの種類 | Microsoft Entra の認証方法 |
| --- | --- |
| 記憶シークレット  (自分が知っているもの) | Password QR コード (PIN) |
| ルックアップ シークレット  (自分が持っているもの) | なし |
| 単一要素帯域外 (自分が持っているもの) | Microsoft Authenticator アプリ (プッシュ通知) Microsoft Authenticator Lite (プッシュ通知) 電話 (SMS): 推奨されません |
| 多要素帯域外  (自分が持っているもの + 自分が知っているもの/自分自身) | Microsoft Authenticator アプリ (電話でのサインイン) |
| 単一要素ワンタイム パスワード (OTP)  (自分が持っているもの) | Microsoft Authenticator アプリ (OTP) Microsoft Authenticator Lite (OTP) 単一要素ハードウェア/ソフトウェア OTP^1^ |
| 多要素 OTP  (自分が持っているもの + 自分が知っているもの/自分自身) | 単一要素 OTP として扱われる |
| 単一要素暗号化ソフトウェア  (自分が持っているもの) | 単一要素ソフトウェア証明書  ソフトウェア TPM を使用して参加した Microsoft Entra ^2^ ソフトウェア TPM を使用して参加した Microsoft Entra ハイブリッド ^2^ 準拠しているモバイル デバイス^2^ |
| 単一要素暗号化ハードウェア  (自分が持っているもの) | 単一要素ハードウェア保護証明書 ハードウェア TPM を使用して参加した Microsoft Entra ^2^ ハードウェア TPM を使用して参加した Microsoft Entra ハイブリッド ^2^ |
| 多要素の暗号化ソフトウェア  (自分が持っているもの + 自分が知っているもの/自分自身) | 多要素ソフトウェア証明書 ソフトウェア TPM と Windows Hello for Business |
| 多要素の暗号化ハードウェア  (自分が持っているもの + 自分が知っているもの/自分自身) | 多要素ハードウェア保護証明書 FIDO 2 セキュリティ キー macOS 用プラットフォーム SSO (Secure Enclave) Windows Hello for Business (ハードウェア TPM を使用)  Microsoft Authenticator でのパスキー |

^1^ 30 秒または 60 秒の OATH-TOTP SHA-1 トークン

^2^ デバイスの参加状態の詳細については、[Microsoft Entra デバイス ID](https://learn.microsoft.com/ja-jp/entra/identity/devices/) に関するページを参照してください

### 公衆交換電話網 (PSTN) の SMS/音声は推奨されません

NIST では、SMS や音声は推奨されていません。 デバイス スワップ、SIM の変更、数値の移植、およびその他の動作のリスクによって、問題が発生する可能性があります。 これらのアクションに悪意があると、安全でないエクスペリエンスになる可能性があります。 SMS/音声 は推奨されていませんが、ハッカーに必要な労力が増えるため、パスワードのみを使用するよりは悪くありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/nist-overview"} -->
## Microsoft Entra ID を使用して NIST 認証システムの保証レベルを実現する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/nist-overview
- Service: entra / standards
- Article date: 2022-11-23
- Summary: NIST 認証システムの保証レベルを満たすには、Microsoft Entra ID を使用します。

連邦政府機関向けのサービスを提供する場合は、多数の標準を満たす課題がある可能性があります。 クラウド サービス プロバイダー (CSP) または連邦機関は、関連するすべての標準に準拠していることを確認します。 Azure と Microsoft Entra ID を使用すると、認定資格を使用して要件を簡単に構成できます。 Azure は、90 以上のコンプライアンス オファリングに対して認定されています。 詳細については、[「クラウドを信頼する」](https://azure.microsoft.com/overview/trusted-cloud/)を参照してください。

この記事には、Microsoft Entra ID およびその他の Microsoft ソリューションを使用して、NIST SP 800-63B の 認証システムの保証レベル (AAL) を達成するためのガイダンスがあります。 下の「次の手順」をご覧ください。

### NIST 標準を満たす理由

米国国立標準技術研究所 (NIST) は、ID ソリューションを実装する米国政府機関の技術要件を策定します。 連邦政府機関と協力している組織も、これらの要件を満たしている必要があります。 NIST の ID 要件の詳細については、[「特殊出版 800-63 Revision 3](https://pages.nist.gov/800-63-3/sp800-63-3.html) (nist SP 800-63-3)」を参照してください。

NIST SP 800-63 は以下で参照されています。

- Electronic Prescription of Controlled Substances [EPCS](https://deadiversion.usdoj.gov/ecomm/e_rx/) プログラム
- [Financial Industry Regulatory Authority (FINRA) の要件](https://www.finra.org/rules-guidance)
- 医療、防衛、およびその他の業界団体では、ID およびアクセス管理要件のベースラインとして NIST SP 800-63-3 がよく使われています

NIST のガイドラインは他の標準で参照されており、中でも注目すべきなのは CSP 向けの Federal Risk and Authorization Management Program (FedRAMP) です。 Azure は FedRAMP High Impact に認定されています。

NIST のデジタル ID ガイドラインでは、従業員、パートナー、サプライヤー、顧客、市民などのユーザーのプルーフィングと認証を扱っています。

NIST SP 800-63-3 デジタル ID ガイドラインは、次の 3 つの領域にわたります。

- [SP 800-63A](https://pages.nist.gov/800-63-3/sp800-63a.html): 登録および ID プルーフィング
- [SP 800-63B](https://pages.nist.gov/800-63-3/sp800-63b.html): 認証およびライフサイクル管理
- [SP 800-63C](https://pages.nist.gov/800-63-3/sp800-63c.html): フェデレーションおよびアサーション

各領域に保証レベルがあります。 次のリンクを使用して、Microsoft Entra ID およびその他の Microsoft ソリューションを使用して、NIST SP 800-63B の 認証システムの保証レベル (AAL) の達成に役立てます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-dss-guidance"} -->
## Microsoft Entra PCI-DSS ガイダンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-dss-guidance
- Service: entra / standards
- Article date: 2023-04-18
- Summary: Microsoft Entra IDに対する支払いカード業界 (PCI) のコンプライアンスに関するガイダンス

Payment Card Industry Security Standards Council (PCI SSC) は、Payment Card Industry データ セキュリティ基準 (PCI-DSS) などのデータ セキュリティ基準とリソースを開発および促進し、支払いトランザクションのセキュリティを確保する責任があります。 PCI コンプライアンスを実現するために、Microsoft Entra IDを使用している組織は、このドキュメントのガイダンスを参照できます。 ただし、PCI コンプライアンスを確保する責任は組織にあります。 組織の IT チーム、SecOps チーム、ソリューション アーキテクトが、支払いカード情報を処理、処理、保存するセキュリティで保護されたシステム、製品、ネットワークの作成と保守を担当します。

Microsoft Entra IDは、一部の PCI-DSS 制御要件を満たし、カード所有者データ環境 (CDE) リソースに最新の ID プロトコルとaccess プロトコルを提供するのに役立ちますが、カード所有者データを保護するための唯一のメカニズムであるべきではありません。 したがって、このドキュメント セットとすべての PCI-DSS の要件を確認して、顧客の信頼を維持する包括的なセキュリティ プログラムを確立します。 要件の完全な一覧については、PCI Security Standards Council の公式 Web サイト (pcisecuritystandards.org) の「[PCI Security Standards Council 公式サイト](https://www.pcisecuritystandards.org/document_library/)」をご覧ください。

### 制御の PCI 要件

グローバルな PCI-DSS v4.0 は、アカウント データを保護するための技術標準と運用標準のベースラインを確立します。 支払いカード アカウント データのセキュリティの推奨と強化を行い、一貫性のあるデータ セキュリティ基準の幅広い対応をグローバルに促進するために開発されました。 アカウント データの保護を目的とする技術要件と運用要件のベースラインです。 支払いカード アカウント データを使用する環境に重点を置いて設計されていますが、PCI-DSS は脅威から保護し、支払いエコシステム内の他の要素をセキュリティで保護するために使用することもできます。"

### Microsoft Entra の構成と PCI-DSS

このドキュメントは、Payment Card Industry Data Security Standard (PCI DSS) に準拠したMicrosoft Entra IDを使用して ID およびaccess管理 (IAM) を管理する技術およびビジネス リーダー向けの包括的なガイドとして機能します。 このドキュメントで概説されている主な要件、ベスト プラクティス、アプローチに従うことで、組織はスコープ、複雑度、PCI 非準拠のリスクを軽減しながら、セキュリティのベスト プラクティスと基準のコンプライアンスを促進できます。 このドキュメントに記載されているガイダンスは、組織が必要な PCI DSS 要件を満たし、効果的な IAM プラクティスを促進する方法でMicrosoft Entra IDを構成するのを支援することを目的としています。

技術およびビジネスリーダーは、次のガイダンスを使用して、Microsoft Entra IDでの ID およびaccess管理 (IAM) の責任を果たすことができます。 他の Microsoft ワークロードでの PCI-DSS の詳細については、「[Microsoft クラウド セキュリティ ベンチマーク (v1)](https://learn.microsoft.com/ja-jp/security/benchmark/azure/overview)の概要」を参照してください。

PCI-DSS の要件とテスト手順は、支払いカード情報の安全な処理を保証する 12 のプリンシパル要件で構成されています。 これらの要件全体は、組織が支払いカード トランザクションをセキュリティで保護し、機密性の高いカード会員データを保護するのに役立つフレームワークです。

Microsoft Entra IDは、アプリケーション、システム、リソースをセキュリティで保護し、PCI-DSS コンプライアンスをサポートするエンタープライズ ID サービスです。 次の表に、PCI の主要な要件と、PCI-DSS コンプライアンスのために Microsoft Entra ID によって推奨されるコントロールへのリンクを示します。

### PCI-DSS の主な要件

PCI-DSS 要件 **3**, **4**、**9**、および**12**はMicrosoft Entra IDによって対処または満たされないため、対応する記事はありません。 すべての要件を確認するには、[PCI Security Standards Council 公式サイト](https://www.pcisecuritystandards.org/document_library/) (pcisecuritystandards.org) にアクセスしてください。

| PCI データ セキュリティ基準 - 概要 | Microsoft Entra ID による推奨 PCI-DSS コントロール |
| --- | --- |
| セキュリティで保護されたネットワークとシステムを構築して維持する | [1.ネットワーク セキュリティ制御をインストールして維持する](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-1)[2.セキュリティで保護された構成をすべてのシステム コンポーネントに適用する](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-2) |
| アカウント データの保護 | 3. 保存されるアカウント データを保護する  4. 公衆ネットワークを介した転送での強力な暗号化を使用してカード会員データを保護する |
| 脆弱性管理プログラムを維持する | [5.悪意のあるソフトウェアからすべてのシステムとネットワークを保護する](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-5)[6.セキュリティで保護されたシステムとソフトウェアを開発し、維持する](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-6) |
| 強力なAccess Control対策を実装する | [7.ビジネス ニーズに応じてシステム コンポーネントとカード所有者データにAccessを制限する](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-7)[8。システム コンポーネントに対するAccessの識別と認証](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-8) 9。 システムコンポーネントとカードホルダーのデータへの物理アクセスを制限する |
| ネットワークを定期的に監視およびテストする | [10。すべてのアクセスをシステムコンポーネントとカード保有者データに対して記録し、監視する](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-10)[11。システムとネットワークのセキュリティを定期的にテストする](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-11) |
| 情報セキュリティ ポリシーを維持する | 12. 組織のポリシーおよびプログラムを使用して情報セキュリティをサポートする |

### PCI-DSS の適用性

PCI-DSS は、カード会員データ (CHD) または機密認証データ (SAD) を保存、処理、または送信する組織に適用されます。 これらのデータ要素は、総称してアカウント データと呼ばれます。 PCI-DSS は、カード会員データ環境 (CDE) に影響を与える組織のセキュリティ ガイドラインと要件を提供します。 CDE を保護するエンティティは、顧客の支払い情報の機密性とセキュリティを確保します。

CHD は次の要素で構成されます。

- **プライマリ アカウント番号 (PAN)** - 発行元とカード会員のアカウントを識別する一意の支払いカード番号 (クレジット、デビット、プリペイド カードなど)
- **カード会員名** – カードの所有者
- **カードの有効期限** – カードの有効期限が切れる日と月
- **サービス コード** - 磁気ストライプに記載の、追跡データ上の支払いカードの有効期限に続く 3 桁または 4 桁の値。 サービス属性を定義して、国際インターチェンジと国内/地域インターチェンジの区別や、使用制限の識別を行います。

SAD は、カード会員の認証や支払いカード トランザクションの承認に使用されるセキュリティ関連の情報で構成されます。 SAD には次が含まれますが、これに限定されません。

- **詳細な追跡データ** - 磁気ストライプまたはチップ等価
- **カード認証コード/値** - カード検証コード (CVC) またはカード検証値 (CVV) とも呼ばれます。 支払いカードの表面または裏面に記載されている 3 桁または 4 桁の値です。 CAV2、CVC2、CVN2、CVV2、または CID とも呼ばれ、参加している支払いブランド (PPB) によって決定されます。
- **PIN**- 個人識別番号
    - **PIN ブロック** - デビットまたはクレジット カード トランザクションで使用される PIN の暗号化された表現。 トランザクション中に機密情報が安全に転送されることを保証します

CDE の保護は、顧客の支払い情報のセキュリティと機密性に不可欠であり、次の点に役立ちます。

- **顧客の信頼を維持する** - 顧客は、支払い情報が安全に処理され、機密性が保持されることを期待しています。 会社でデータ侵害が発生し、その結果、顧客の支払いデータが盗まれると、会社に対する顧客の信頼が低下し、評判が損なわれる可能性があります。
- **規制に準拠する** - クレジット カードのトランザクションを処理する企業は、PCI-DSS に準拠する必要があります。 準拠しない場合、罰金、法的責任、評判の損害の原因となります。
- **財務リスクの軽減** - データ侵害は、財務上大きな影響を与えます。これには、フォレンジック調査のコスト、法的手数料、影響を受ける顧客に対する報酬などがあります。
- **ビジネス継続性** - データ侵害はビジネスの運営を中断させ、クレジット カードのトランザクション プロセスに影響を与える可能性があります。 このシナリオは、収益の損失、運営の中断、評判の低下につながる可能性があります。

### PCI 監査スコープ

PCI 監査のスコープは、CHD や SAD の保存、処理、または伝送に関わるシステム、ネットワーク、およびプロセスに関連しています。 アカウント データがクラウド環境に保存、処理、または送信される場合、PCI-DSS はその環境に適用され、コンプライアンスには通常、クラウド環境の検証と使用が含まれます。 PCI 監査のスコープには、次の 5 つの基本的な要素があります。

- **カード会員データ環境 (CDE)** - CHD や SAD またはその両方が保存、処理、送信される領域。 これには、ネットワーク、ネットワーク コンポーネント、データベース、サーバー、アプリケーション、支払い端末など、CHD に触れる組織のコンポーネントが含まれます。
- **People** - 従業員、請負業者、サード パーティのサービス プロバイダーなどの CDE へのaccessが PCI 監査の対象となります。
- **Processes** - CHD を含む、承認、認証、暗号化、およびストレージなどのプロセスは、いかなる形式のものであれ、PCI 監査の対象範囲内にあります。
- **テクノロジ** - CHD を処理、保存、送信するテクノロジは、PCI 監査のスコープに含まれます。これには、プリンターなどのハードウェア、スキャン、印刷、FAX が可能な多機能デバイス、コンピューター、ラップトップ ワークステーション、管理ワークステーション、タブレット、モバイル デバイス、ソフトウェア、その他の IT システムなどのエンドユーザー デバイスがあります。
- **システム コンポーネント** - CHD/SAD 保存、処理、送信は行わないが、CHD/SAD の保存、処理、送信を行うシステム コンポーネントへの接続が無制限である可能性があるか、CDE のセキュリティに影響を与える可能性があるシステム コンポーネント。

PCI スコープが最小限に抑えられると、組織はセキュリティ インシデントの影響を効果的に軽減し、データ侵害のリスクを軽減できます。 セグメント化は、PCI CDE のサイズを縮小し、結果としてコンプライアンス コストを削減し、組織の全体的なメリットをもたらすための重要な戦略となる可能性があります。組織へのメリットには次が含まれますが、これらに限定されません。

- **コスト削減** - 監査スコープを制限することで、組織は監査を受ける時間、リソース、経費を削減し、コスト削減につながります。
- **リスクの露出を減らす** - PCI 監査スコープを小さくすると、カード会員データの処理、保存、送信に関連する潜在的なリスクが軽減されます。 監査対象のシステム、ネットワーク、アプリケーションの数が限られている場合、組織は重要な資産をセキュリティで保護し、リスクへの露出を減らすことに集中します。
- **コンプライアンスの合理化** - 監査スコープを縮小すると、PCI-DSS コンプライアンスの管理が容易になり、合理化されます。 その結果、監査の効率化、コンプライアンスの問題の減少、コンプライアンス違反のペナルティが発生するリスクの軽減を実現します。
- **セキュリティ態勢の改善** - システムとプロセスのサブセットが小さいため、組織はセキュリティ リソースと作業を効率的に割り当てます。 セキュリティ チームが重要な資産をセキュリティで保護し、対象を絞った効果的な方法で脆弱性を特定することに集中するため、結果としてより強力なセキュリティ態勢を実現します。

### PCI 監査スコープを減らすための戦略

組織の CDE の定義によって、PCI 監査スコープが決まります。 組織は、この定義を文書化し、監査を実行する PCI-DSS 認定監査機関 (QSA) に伝達します。 QSA は CDE のコントロールを評価し、コンプライアンスを判断します。 PCI 基準への準拠と効果的なリスク軽減策の使用は、企業が顧客の個人データと財務データを保護し、運営に対する信頼を維持するのに役立ちます。 次のセクションでは、PCI 監査スコープのリスクを軽減するための戦略について説明します。

#### トークン化

トークン化は、データ セキュリティの手法です。 トークン化を使用して、機密データを公開することなく、クレジット カード番号などの機密情報を、保存され、トランザクションに使用される一意のトークンに置き換えます。 トークンは、次の要件に対する PCI 監査のスコープを減らします。

- **要件 3** - 保存されるアカウント データを保護する
- **要件 4** - オープンな公衆ネットワークを介した転送での強力な暗号化を使用してカード会員データを保護する
- **Requirement 9** - カード所有者データへの物理的アクセスを制限する
- **Requirement 10** - システム コンポーネントとカード所有者データに対するすべてのAccessをログに記録および監視します。

クラウドベースの処理手法を使用する場合、機密データとトランザクションへのリスクを考慮します。 これらのリスクを軽減するには、データを保護し、トランザクションの中断を防ぐために、関連するセキュリティ対策とコンティンジェンシー計画を実装することをお勧めします。 ベスト プラクティスとして、支払いトークン化を使用します。これは、データの分類を解除し、場合によっては CDE のフットプリントを削減するための手法です。 支払いトークン化では、機密データが一意の識別子に置き換えられます。これにより、データ盗難のリスクが軽減され、CDE 内の機密情報の公開が制限されます。

#### セキュリティで保護された CDE

PCI-DSS では、組織はセキュリティで保護された CDE を維持する必要があります。 効果的に構成された CDE を使用すると、企業はリスクへの露出を軽減し、オンプレミス環境とクラウド環境の両方に関連するコストを削減できます。 このアプローチは、PCI 監査の範囲を最小限に抑え、基準に準拠していることを簡単に示し、その際のコスト効率を高めるのに役立ちます。

CDE をセキュリティで保護するようにMicrosoft Entra IDを構成するには:

- ユーザーにパスワードなしの資格情報を使用する: Windows Hello for Business、FIDO2 セキュリティ キー、Microsoft Authenticator アプリ
- ワークロード ID には強力な資格情報 (Azure リソースの証明書とマネージド ID) を使用します。
    - VPN、remote desktop、ネットワーク access ポイントなどのaccess テクノロジを認証用のMicrosoft Entra ID (該当する場合) と統合する
- Microsoft Entra ロール、特権アクセス グループ、およびAzure リソースの特権アイデンティティ管理およびアクセス レビューを有効にする
- 条件付きAccess ポリシーを使用して PCI 要件の制御 (資格情報の強度、デバイスの状態) を適用し、場所、グループ メンバーシップ、アプリケーション、リスクに基づいて適用する
- DCE ワークロードのための最新の認証を使用する
- セキュリティ情報イベント管理 (SIEM) システムに Microsoft Entra ログをアーカイブする

アプリケーションとリソースが ID およびaccess管理 (IAM) にMicrosoft Entra IDを使用する場合、Microsoft Entra テナントは PCI 監査の対象となり、ここに記載されているガイダンスが適用されます。 組織は、最適なアーキテクチャを決定するために、PCI 以外のワークロードと PCI ワークロードの間で ID とリソースの分離要件を評価する必要があります。

詳細情報

- [委任された管理と分離された環境の概要](https://learn.microsoft.com/ja-jp/entra/architecture/secure-introduction)
- [Microsoft Authenticator アプリの使用方法](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)
- [Azure リソースのマネージド ID は何ですか?](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)
- [アクセスレビューとは？](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)
- [条件付きAccessとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [Microsoft Entra ID の監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)

#### 責任マトリックスを確立する

PCI へのコンプライアンスは、支払いカードのトランザクションを処理するエンティティの責任です。これらのエンティティには次が含まれますが、これらに限定されません。

- マーチャント
- カード サービス プロバイダー
- マーチャント サービス プロバイダー
- 承継銀行
- 支払処理業者
- 支払いカード発行元
- ハードウェア ベンダー

これらのエンティティは、支払いカードのトランザクションが安全に処理され、PCI-DSS に準拠するようにします。 支払いカードのトランザクションに関係するすべてのエンティティには、PCI コンプライアンスを確保するという役割があります。

AZURE PCI DSS コンプライアンスの状態は、Azureで構築またはホストするサービスの PCI-DSS 検証に自動的には変換されません。 PCI-DSS 要件に準拠していることを自分で確認します。

#### コンプライアンスを維持するための継続的なプロセスを確立する

継続的なプロセスには、継続的な監視とコンプライアンス態勢の改善が伴います。 PCI コンプライアンスを維持するための継続的なプロセスのメリット:

- セキュリティ インシデントとコンプライアンス違反のリスクの軽減
- データ セキュリティの強化
- 規制要件との整合性の向上
- 顧客と関係者の信頼度の向上

継続的なプロセスにより、組織は規制環境の変化と進化し続けるセキュリティ上の脅威に効果的に対応します。

- **リスク評価** – このプロセスを実行して、クレジットカード データの脆弱性とセキュリティ リスクを特定します。 潜在的な脅威を特定し、脅威が発生する可能性を評価し、ビジネスに及ぼす潜在的な影響を評価します。
- **セキュリティ認識トレーニング** - クレジット カード データを処理する従業員は、カード所有者データの保護の重要性とto do対策を明確にするために、定期的なセキュリティ意識トレーニングを受けます。
- **脆弱性管理** - 定期的な脆弱性スキャンと侵入テストを実施して、攻撃者が悪用できるネットワークまたはシステムの弱点を特定します。
- **アクセス制御ポリシーの監視と管理** - クレジットカードデータへのアクセスは、承認された個人に制限されます。 accessログを監視して、承認されていないaccess試行を特定します。
- **インシデント対応** – インシデント対応計画は、クレジット カード データが関係するセキュリティ インシデント中にセキュリティ チームがアクションを実行するのに役立ちます。 インシデントの原因を特定し、損害を封じ込め、通常の操作を適切なタイミングで復元します。
- **コンプライアンスの監視** - コンプライアンスの監視と監査は、PCI-DSS 要件への継続的なコンプライアンスを確保するために行われます。 セキュリティ ログを確認し、定期的なポリシー レビューを実施し、システム コンポーネントが正確に構成および保守されていることを確認します。

#### 共有インフラストラクチャに強力なセキュリティを実装する

通常、Azureなどの Web サービスには、顧客データが同じ物理サーバーまたはデータ storage デバイスに格納される可能性がある共有インフラストラクチャがあります。 このシナリオでは、承認されていない顧客が所有していないデータにアクセスするリスクと、悪意のあるアクターが共有インフラストラクチャを標的にするリスクが発生します。 Microsoft Entra のセキュリティ機能は、共有インフラストラクチャに関連するリスクを軽減するのに役立ちます。

- 最新の認証プロトコル (仮想プライベート ネットワーク (VPN)、remote desktop、ネットワーク access ポイント) をサポートするネットワーク access テクノロジに対するユーザー認証。
- ユーザー コンテキスト、デバイス、場所、リスクなどのシグナルに基づいて、強力な認証手段とデバイスの準拠を適用するアクセス制御ポリシー。
- 条件付きAccessは、ID 主導のコントロール プレーンを提供し、シグナルをまとめ、意思決定を行い、組織のポリシーを適用します。
- 特権ロールガバナンス - アクセスレビュー、ジャスト・イン・タイム (JIT) アクティブ化など。

詳細情報: [条件付きAccessとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)

#### データの保存場所

PCI-DSS は、クレジット カード データのstorageに特定の地理的な場所がないことを示しています。 ただし、カード会員データが安全に保存されている必要があります。これには、組織のセキュリティと規制の要件に応じて、地理的な制限が含まれる場合があります。 さまざまな国と地域に、データ保護とプライバシーに関する法律があります。 該当するデータ所在地の要件を判断するには、法律またはコンプライアンス アドバイザーにご相談ください。

詳細情報: [Microsoft Entra IDとデータ所在地](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency)

#### サードパーティのセキュリティ リスク

PCI に準拠していないサードパーティ プロバイダーは、PCI コンプライアンスに対するリスクを引き起こす可能性があります。 サード パーティのベンダーとサービス プロバイダーを定期的に評価および監視し、カード会員データを保護するために必要なコントロールを維持していることを確認します。

**データ所在地**の Microsoft Entra の機能は、サードパーティのセキュリティに関連するリスクを軽減するのに役立ちます。

#### ログ記録と監視

正確なログと監視を実装して、セキュリティ インシデントをタイムリーに検出して対応します。 Microsoft Entra IDは、監査ログとアクティビティ ログ、SIEM システムと統合できるレポートに対する PCI コンプライアンスを管理するのに役立ちます。 Microsoft Entra IDには、機密性の高いリソースへのアクセスを保護するための役割ベースのアクセス制御（RBAC）と多要素認証（MFA）があり、さらに暗号化と脅威保護機能を備えており、組織を不正アクセスやデータの盗難から守ります。

詳細情報:

- [Microsoft Entra レポートとは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)
- [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)

#### マルチアプリケーション環境: CDE の外部でホストする

PCI-DSS により、クレジット カード情報の受け入れ、処理、保存、送信する企業がセキュリティで保護された環境を確実に維持できます。 CDE の外部でホストすると、次のようなリスクが発生します。

- アクセス制御とアイデンティティ管理が不十分な場合、機密データおよびシステムに対する不正アクセスが発生する可能性があります。
- セキュリティ イベントの不十分なログと監視により、セキュリティ インシデントの検出と対応が妨げられます
- 暗号化と脅威の保護が不十分な場合、データの盗難や承認されていないaccessのリスクが高まります
- ユーザーへのセキュリティ意識やトレーニングが不十分なため、フィッシングなどの回避可能なソーシャル エンジニアリング攻撃が発生する可能性があります
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-dss-mfa"} -->
## Microsoft Entra PCI-DSS 多要素認証ガイダンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-dss-mfa
- Service: entra / standards
- Article date: 2023-04-18
- Summary: PCI MFA 要件を満たすためにMicrosoft Entra IDでサポートされる認証方法について説明します

**補足情報: 多要素認証 v 1.0**

PCI Security Standards Council [Information Supplement、 Multi-Factor Authentication v 1.0](https://listings.pcisecuritystandards.org/pdfs/Multi-Factor-Authentication-Guidance-v1.pdf) の要件を満たすために、Microsoft Entra IDでサポートされている認証方法の次の表を使用します。

| メソッド | 要件を満たすには | 保護 | MFA 要素 |
| --- | --- | --- | --- |
| [Microsoft Authenticator を使用したパスワードレスの電話サインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone) | ユーザーが持っているもの (キーが格納されたデバイス)、ユーザーが知っているものまたはユーザー自身 (PIN または生体認証)iOS では、Authenticator セキュア エレメント (SE) によってキーがキーチェーンに格納されます。 [Apple プラットフォームのセキュリティ、キーチェーンのデータ保護](https://support.apple.com/guide/security/keychain-data-protection-secb0694df1a/web) Android では、Authenticator は キーストアにキーを格納することで、Trusted Execution Engine (TEE) を使用します。 [Developers、Android キーストア システム](https://developer.android.com/training/articles/keystore) ユーザーがMicrosoft Authenticatorを使用して認証を行うと、Microsoft Entra IDが乱数を生成し、それをユーザーがアプリに入力します。 このアクションは、アウトオブバンド認証要件を満たします。 | お客様は、デバイスの侵害リスクを軽減するために、デバイス保護ポリシーを構成します。 たとえば、Microsoft Intune のコンプライアンス ポリシー。 | ユーザーはジェスチャを使用してキーのロックを解除し、Microsoft Entra ID認証方法を検証します。 |
| [Windows Hello for Business 展開の前提条件の概要](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification) | ユーザーが持っているもの (キーが格納された Windows デバイス)、ユーザーが知っているものまたはユーザー自身 (PIN または生体認証)。 キーは、デバイスのトラステッド プラットフォーム モジュール (TPM) と共に格納されます。 お客様は、ハードウェア TPM 2.0 以降が搭載されたデバイスを使用して、認証方法の独立とアウトオブバンド要件を満たします。 [認定認証者レベル](https://fidoalliance.org/certification/authenticator-certification-levels/) | デバイスの侵害リスクを軽減するために、デバイス保護ポリシーを構成します。 たとえば、Microsoft Intune のコンプライアンス ポリシーです。 | ユーザーは、Windows デバイスのサインインのジェスチャを使用してキーのロックを解除します。 |
| [パスワードなしのセキュリティ キー サインインを有効にする、FIDO2 セキュリティ キーの方法を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key) | ユーザーが持っているもの (FIDO2 セキュリティ キー)、ユーザーが知っているものまたはユーザー自身 (PIN または生体認証)。 キーは、ハードウェア暗号化機能と共に格納されます。 お客様は、FIDO2 キー (少なくとも認証認定レベル 2 (L2)) を使用して、認証方法の独立とアウトオブバンド要件を満たします。 | 改ざんや侵害から保護されたハードウェアを調達します。 | ユーザーはジェスチャーでキーを解除し、その後、Microsoft Entra IDが資格情報を検証します。 |
| [Microsoft Entra 証明書ベースの認証の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) | ユーザーが持っているもの (スマート カード)、ユーザーが知っているもの (PIN)。 TPM 2.0 以降に格納されている物理スマート カードまたは仮想スマート カードは、セキュア エレメント (SE) です。 このアクションは、認証方法の独立とアウトオブバンド要件を満たします。 | 改ざんや侵害から保護されたスマート カードを調達します。 | ユーザーはジェスチャまたはPINを使用して証明書の秘密キーのロックを解除し、Microsoft Entra IDが資格情報を検証します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-requirement-1"} -->
## Microsoft Entra IDと PCI-DSS 要件 1 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-1
- Service: entra / standards
- Article date: 2023-04-18
- Summary: ネットワーク セキュリティ制御をインストールして維持するための PCI-DSS で定義されたアプローチの要件について説明します

**要件 1: ネットワーク セキュリティ制御をインストールして保守する** **定義されたアプローチの要件**

### 1.1 ネットワーク セキュリティ制御をインストールして、維持するためのプロセスとメカニズムが定義され、理解されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **1.1.1** 要件 1 で特定されるすべてのセキュリティ ポリシーと運用手順は次のとおり: 文書化されている最新の状態に維持されている使用中すべての関係者に通知されている | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |
| **1.1.2** 要件 1 でアクティビティを実行するための役割と責任を文書化し、割り当て、理解する | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |

### 1.2 ネットワーク セキュリティ制御 (NSC) が構成され、維持されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **1.2.1** NSC ルールセットの構成標準は次のとおり: 定義されている実装されている維持されている | access テクノロジがモダン認証をサポートしている場合は、VPN、リモートデスクトップ、およびネットワークアクセスポイントなどのアクセステクノロジを、認証と承認のためのMicrosoft Entra IDと統合します。 ID 関連の制御に関連する NSC 標準に、条件付きAccess ポリシー、アプリケーションの割り当て、access レビュー、グループ管理、資格情報ポリシーなどの定義が含まれるようにします [Microsoft Entra 操作リファレンス ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-intro) |
| **1.2.2** ネットワーク接続と NSC の構成に対するすべての変更は、要件 6.5.1 で定義されている変更制御プロセスに従って承認および管理される | Microsoft Entra IDには適用されません。 |
| **1.2.3** カード所有者データ環境 (CDE) と他のネットワーク (あらゆるワイヤレス ネットワークを含む) の間のすべての接続を示す正確なネットワーク図が維持されている。 | Microsoft Entra IDには適用されません。 |
| **1.2.4** 次の基準を満たす正確なデータフロー図が維持される。システムとネットワーク全体のすべてのアカウント データ フローを示す。 環境の変更時に必要に応じて更新される。 | Microsoft Entra IDには適用されません。 |
| **1.2.5** 許可されているすべてのサービス、プロトコル、ポートが特定および承認され、ビジネス ニーズが定義されている | Microsoft Entra IDには適用されません。 |
| **1.2.6** セキュリティ機能は、使用中のすべてのサービス、プロトコル、ポートに対して定義および実装され、リスクが軽減されるように安全ではないと見なされる。 | Microsoft Entra IDには適用されません。 |
| **1.2.7** NSC の構成は、関連性が高く効果的であることを確認するために、少なくとも 6 か月に 1 回レビューされる。 | Microsoft Entra access レビューを使用して、CDE のネットワーク セキュリティ制御に合わせた VPN アプライアンスなどのグループ メンバーシップ レビューとアプリケーションを自動化します。 [アクセスレビューとは？](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview) |
| **1.2.8** NSCの構成ファイルは次のとおりです: 未承認のアクセスを防止する アクティブなネットワーク構成との一貫性を保つ | Microsoft Entra IDには適用されません。 |

### 1.3 カード所有者データ環境との間のネットワークaccessが制限されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **1.3.1** CDE への受信トラフィックは次のように制限される。必要なトラフィックのみに限定される。 その他のすべてのトラフィックは明示的に拒否される | Microsoft Entra IDを使用して、条件付きAccess ポリシーを作成する名前付き場所を構成します。 ユーザーとサインインのリスクを計算します。 Microsoft では、ネットワークの場所を使用して CDE の IP アドレスを設定し、維持することをお勧めします。 これらを使用して、条件付きAccessポリシー要件を定義します。 [条件付きAccess ポリシーでの場所の条件の使用](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network) |
| **1.3.2** CDE からの送信トラフィックは次のように制限される。必要なトラフィックのみに限定される。 その他のすべてのトラフィックは明示的に拒否される | NSC 設計の場合は、CDE IP アドレスへのaccessを許可するアプリケーションの条件付きAccess ポリシーを含めます。 仮想プライベートネットワーク (VPN) アプライアンスやキャプティブポータルなどを含む、CDE への接続を確立するための緊急アクセスやリモートアクセスでは、意図しないロックアウトを防ぐためのポリシーを設ける必要がある場合があります。 条件付きアクセス ポリシーでの場所条件の設定Microsoft Entra ID での緊急アクセスアカウント管理 |
| **1.3.3** NSC は、ワイヤレス ネットワークが CDE であるかどうかに関係なく、すべてのワイヤレス ネットワークと CDE の間に次のようにインストールされる。ワイヤレス ネットワークから CDE へのワイヤレス トラフィックはすべて既定で拒否される。 CDE には、認可されたビジネス目的を持つワイヤレス トラフィックのみが許可される。 | NSC 設計の場合は、CDE IP アドレスへのaccessを許可するアプリケーションの条件付きAccess ポリシーを含めます。 仮想プライベート ネットワーク (VPN) アプライアンスやキャプティブ ポータルなどを使用してCDEへの接続を確立する緊急アクセスまたはリモートアクセスには、意図しないロックアウトを防ぐためのポリシーが必要となる場合があります。 [条件付きアクセス ポリシーでの場所条件の活用](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)[Microsoft Entra ID の緊急アクセスアカウントを管理します](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access) |

### 1.4 信頼されたネットワークと信頼されていないネットワーク間のネットワーク接続が制御されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **1.4.1** 信頼されたネットワークと信頼されていないネットワークの間で NSC が実装されている。 | Microsoft Entra IDには適用されません。 |
| **1.4.2** 信頼されていないネットワークから信頼されたネットワークへの受信トラフィックは、以下に制限される。パブリックにアクセス可能なサービス、プロトコル、ポートを提供する権限を持つシステム コンポーネントとの通信。 信頼されたネットワーク内のシステム コンポーネントによって開始された通信に対するステートフル応答。 その他のトラフィックはすべて拒否される。 | Microsoft Entra IDには適用されません。 |
| **1.4.3** 偽造されたソース IP アドレスを検知して、信頼されたネットワークに入るのをブロックするために、スプーフィング対策が実装されている。 | Microsoft Entra IDには適用されません。 |
| **1.4.4** カード所有者データを格納するシステム コンポーネントには、信頼されていないネットワークから直接アクセスすることができない。 | Microsoft Entra IDを使用する CDE 内のアプリケーションでは、ネットワーク層の制御に加えて、条件付きAccess ポリシーを使用できます。 場所に基づいてアプリケーションにaccessを制限します。 [条件付きAccess ポリシーでのネットワークの場所の使用](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network) |
| **1.4.5** 内部 IP アドレスとルーティング情報の開示は、認可された当事者のみに限定される。 | Microsoft Entra IDには適用されません。 |

### 1.5 信頼されていないネットワークと CDE の両方に接続できるコンピューティング デバイスからの CDE に対するリスクが軽減されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **1.5.1** 信頼されていないネットワーク (インターネットを含む) と CDE の両方に接続するコンピューティング デバイス (会社所有および従業員所有のデバイスを含む) で、次のようにセキュリティ制御が実装されている。エンティティのネットワークに脅威が導入されるのを防ぐために、特定の構成設定が定義されている。 セキュリティ制御がアクティブに実行されている。 具体的に文書化され、ケースバイケースかつ期間限定で管理者に認可された場合を除き、コンピューティング デバイスのユーザーはセキュリティ制御を変更できない。 | デバイスコンプライアンスを必要とする条件付きAccessポリシーを展開します。 [コンプライアンス ポリシーを使用して、Intune で管理するデバイスのルールを設定する](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started) デバイスのコンプライアンスの状態をマルウェア対策ソリューションと統合する。 [Intune における条件付きアクセスを使用して、Microsoft Defender for Endpoint のコンプライアンスを適用](https://learn.microsoft.com/ja-jp/mem/intune/protect/advanced-threat-protection)[Mobile Threat Defense と Intune の統合](https://learn.microsoft.com/ja-jp/mem/intune/protect/mobile-threat-defense) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-requirement-10"} -->
## Microsoft Entra IDと PCI-DSS 要件 10 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-10
- Service: entra / standards
- Article date: 2023-04-18
- Summary: システム コンポーネントと CHD に対するすべてのaccessのログ記録と監視に関する PCI-DSS 定義されたアプローチ要件について説明します

**要求 10: システム コンポーネントとカード所有者データへのすべてのAccessのログと監視** **既定のアプローチ要件**

### 10.1 システム コンポーネントとカード所有者データに対するすべてのaccessをログに記録および監視するためのプロセスとメカニズムが定義され、文書化されます。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **10.1.1** 要件 10 で特定されるすべてのセキュリティ ポリシーと運用手順は次のとおりです。文書化されている最新の状態に維持されている使用中すべての関係者に通知されている | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |
| **10.1.2** 要件 10 のアクティビティを実行するための役割と責任が文書化され、割り当てられ、理解されている。 | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |

### 10.2 異常および疑わしいアクティビティの検出とイベントのフォレンジック分析をサポートするために、監査ログが実装されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **10.2.1** すべてのシステム コンポーネントとカード所有者データに対して監査ログが有効かつアクティブである。 | Microsoft Entra 監査ログをアーカイブして、セキュリティ ポリシーと Microsoft Entra テナント構成の変更を取得します。  Microsoft Entra アクティビティ ログをセキュリティ情報およびイベント管理 (SIEM) システムにアーカイブして、使用状況を把握します。 [Microsoft Entra のアクティビティログ Azure モニターで](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations) |
| **10.2.1.1** 監査ログは、カード所有者データへのすべての個々のユーザーによるアクセスを記録します。 | Microsoft Entra IDには適用されません。 |
| **10.2.1.2** 監査ログは、アプリケーションまたはシステム アカウントの対話型の使用を含め、管理accessを持つ個人が実行したすべてのアクションをキャプチャします。 | Microsoft Entra IDには適用されません。 |
| **10.2.1.3** 監査ログへのすべてのアクセスがログに記録されます。 | Microsoft Entra IDでは、ログをワイプまたは変更することはできません。 特権ユーザーは、Microsoft Entra IDからログに対してクエリを実行できます。 [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task) のタスク別の最低特権の役割 監査ログが Azure Log Analytics ワークスペース、Storage アカウント、サード パーティの SIEM システムなどのシステムにエクスポートされた場合は、アクセスを監視します。 |
| **10.2.1.4** 監査ログでは、無効な論理access試行がすべてキャプチャされます。 | Microsoft Entra IDは、ユーザーが無効な資格情報でサインインしようとしたときにアクティビティ ログを生成します。 条件付きAccess ポリシーが原因でaccessが拒否されると、アクティビティ ログが生成されます。 |
| **10.2.1.5** 監査ログは、識別情報および認証情報へのすべての変更をキャプチャし、これには特に、 新しいアカウントの作成特権の昇格管理アクセスを持つアカウントに対するすべての変更、追加、または削除が含まれますが、これらに限定されません。 | Microsoft Entra IDは、この要件のイベントの監査ログを生成します。 |
| **10.2.1.6** 監査ログに以下がキャプチャされる:  新しい監査ログのすべての初期化  既存の監査ログのすべての開始、停止、または一時停止。 | Microsoft Entra IDには適用されません。 |
| **10.2.1.7** システム レベル オブジェクトのすべての作成と削除が監査ログにキャプチャされる。 | Microsoft Entra IDは、この要件のイベントの監査ログを生成します。 |
| **10.2.2** 監査可能なイベントごとに次の詳細が監査ログに記録される:  ユーザー ID。  イベントの種類。  日付と時刻。  成功と失敗の表示。  イベントの発生元。  影響を受けるデータ、システム コンポーネント、リソース、またはサービスの ID または名前 (例: 名前とプロトコル)。 | Microsoft Entra ID の監査ログを参照してください。 |

### 10.3 監査ログは、破棄および非認可の変更から保護されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **10.3.1** 監査ログファイルへの読み取りアクセスは、業務に関連する必要性のある場合にのみ限定されています。 | 特権ユーザーは、Microsoft Entra IDからログに対してクエリを実行できます。 [Microsoft Entra ID のタスクごとに最小特権のロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task) |
| **10.3.2** 個人による変更を防ぐように監査ログ ファイルが保護されています。 | Microsoft Entra IDでは、ログをワイプまたは変更することはできません。  監査ログが Azure Log Analytics ワークスペース、storage アカウント、サード パーティの SIEM システムなどのシステムにエクスポートされる場合は、accessを監視します。 |
| **10.3.3** 外部向けテクノロジのログを含む監査ログ ファイルが、セキュリティで保護された中央の内部ログ サーバー、または変更が困難なその他のメディアに迅速にバックアップされる。 | Microsoft Entra IDでは、ログをワイプまたは変更することはできません。  監査ログが Azure Log Analytics ワークスペース、storage アカウント、サード パーティの SIEM システムなどのシステムにエクスポートされる場合は、accessを監視します。 |
| **10.3.4** アラートを生成せずに既存のログ データを変更できないよう、監査ログに対してファイル整合性監視または変更検出メカニズムが使用されている。 | Microsoft Entra IDでは、ログをワイプまたは変更することはできません。  監査ログが Azure Log Analytics ワークスペース、storage アカウント、サード パーティの SIEM システムなどのシステムにエクスポートされる場合は、accessを監視します。 |

### 10.4 異常または疑わしいアクティビティを識別するために、監査ログがレビューされる。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **10.4.1** 以下の監査ログが少なくとも 1 日 1 回レビューされる:  すべてのセキュリティ イベント。  カード所有者のデータ (CHD) または機密性の高い認証データ (SAD) を保存、処理、または送信するすべてのシステム コンポーネントのログ。 すべての重要なシステム コンポーネントのログ。  すべてのサーバーとセキュリティ機能を実行するシステム コンポーネント (ネットワーク セキュリティ コントロール、侵入検知システム/侵入防止システム (IDS/IPS)、認証サーバーなど) のログ。 | このプロセスに Microsoft Entra ログを含めます。 |
| **10.4.1.1** 監査ログのレビューを実行するために、自動化されたメカニズムが使用されています。 | このプロセスに Microsoft Entra ログを含めます。 Microsoft Entra ログが Azure Monitor と統合されている場合の自動アクションとアラートを構成します。 [Deploy Azure Monitor: アラートと自動アクション](https://learn.microsoft.com/ja-jp/azure/azure-monitor/best-practices-alerts) |
| **10.4.2** (要件 10.4.1 で指定されていない) 他のすべてのシステム コンポーネントのログが定期的にレビューされる。 | Microsoft Entra IDには適用されません。 |
| **10.4.2.1** (要件 10.4.1 で定義されていない) 他のすべてのシステム コンポーネントに対する定期的なログ レビューの頻度は、要件 12.3.1 で指定されているすべての要素に従って実行される、エンティティの対象指定リスク分析で定義されている。 | Microsoft Entra IDには適用されません。 |
| **10.4.3** レビュー プロセスで特定された例外と異常に対処している。 | Microsoft Entra IDには適用されません。 |

### 10.5 監査ログの履歴が保持され、分析に使用できる。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **10.5.1** 監査ログの履歴を少なくとも 12 か月間保持し、少なくとも最近の 3 か月分はすぐに分析できるようにする。 | Azure Monitor と統合し、ログをエクスポートして長期的なアーカイブを行います。 [Azure Monitor ログを使用して Microsoft Entra ログを統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) Microsoft Entra ログデータ保持ポリシーについて説明します。 [Microsoft Entra データ保持](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention) |

### 10.6 時刻同期メカニズムにより、すべてのシステムで一貫した時刻設定がサポートされている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **10.6.1** システム クロックと時刻は、時刻同期テクノロジを使用して同期される。 | Azure サービスの時刻同期メカニズムについて説明します。 [Azureにおける金融サービスのための時刻同期](https://azure.microsoft.com/blog/time-synchronization-for-financial-services-in-azure/) |
| **10.6.2** 次のように、正確で一貫した時刻にシステムが構成されている:  1 つ以上の指定されたタイム サーバーが使用中である。  指定された中央タイム サーバーのみが外部ソースから時刻を受信する。  外部ソースから受信する時刻は、国際原子時または協定世界時 (UTC) に基づいている。  指定されたタイム サーバーは、特定の業界で認められた外部ソースからのみ時刻の更新を受け入れる。  指定されたタイム サーバーが複数ある場合、タイム サーバーは互いにピアリングして正確な時刻を維持する。  内部システムは、指定された中央タイム サーバーからのみ時刻情報を受信する。 | Azure サービスの時刻同期メカニズムについて説明します。 [Azure における金融サービスのタイムシンクロナイゼーション](https://azure.microsoft.com/blog/time-synchronization-for-financial-services-in-azure/) |
| **10.6.3** 時刻同期の設定とデータは、次のように保護されています:  時刻データへのアクセスは、業務上の必要がある担当者のみに制限されます。  重要なシステムの時刻設定の変更はすべてログに記録され、監視され、レビューされる。 | Microsoft Entra IDは、Azureの時刻同期メカニズムに依存します。  Azure手順では、サーバーとネットワーク デバイスを NTP Stratum 1 タイム サーバーと同期し、グローバル 位置決めシステム (GPS) 衛星に同期します。 同期は 5 分ごとに行われます。 Azureにより、サービス ホストの同期時間が保証されます。 [Azureにおける金融サービスのための時刻同期](https://azure.microsoft.com/blog/time-synchronization-for-financial-services-in-azure/) Microsoft Entra ID のハイブリッド コンポーネント、例えば Microsoft Entra Connect サーバーは、オンプレミスのインフラストラクチャと相互に作用します。 オンプレミス サーバーの時刻同期はお客様の責任です。 |

### 10.7 重要なセキュリティ制御システムのエラーは迅速に検出され、報告されて、対応される。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **10.7.2***サービスプロバイダーのみの追加要件*: 重要なセキュリティ制御システムの障害は検出され、警告され、迅速に対応されます。例として、次の重要なセキュリティ制御システムの障害があります:  ネットワーク セキュリティ制御  IDS/IPS  ファイル整合性監視 (FIM)  マルウェア対策ソリューション  物理的アクセス制御  論理的アクセス制御  監査ログメカニズム  セグメント化制御 (使用されている場合) | Microsoft Entra IDは、Azureの時刻同期メカニズムに依存します。  Azure は、その運用環境でのリアルタイム イベント分析をサポートします。 内部Azureインフラストラクチャ システムは、侵害の可能性に関するほぼリアルタイムのイベント アラートを生成します。 |
| **10.7.2** 重大なセキュリティ制御システムの障害が検出され、警告されます。 および、次の重要なセキュリティ制御システムの障害を含むがこれらに限定されない迅速な対処:  ネットワーク セキュリティ制御  IDS/IP  変更検出メカニズム  マルウェア対策ソリューション  物理accessコントロール  論理accesscontrols  監査ログ メカニズム  セグメント化制御 (使用されている場合)  監査ログ レビュー メカニズム  自動セキュリティ テスト ツール (使用する場合) | 「[Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)」を参照してください。 |
| **10.7.3** 以下を含むがこれらに限定されない、重要なセキュリティ制御システムのエラーは迅速に対応される:  セキュリティ機能の復元。  セキュリティ障害の期間 (開始から終了までの日付時刻) の識別および文書化。  エラーの原因の特定と文書化、および必要な修復の文書化。  障害中に発生したすべてのセキュリティ問題の識別および対処。  セキュリティ障害の結果、さらなる処置が必要かどうかの判断。  障害原因の再発防止策の実装。  セキュリティ制御の監視の再開。 | 「[Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)」を参照してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-requirement-11"} -->
## Microsoft Entra IDと PCI-DSS 要件 11 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-11
- Service: entra / standards
- Article date: 2023-04-18
- Summary: セキュリティとネットワーク セキュリティの定期的なテストに関する PCI-DSS で定義されたアプローチの要件について説明します

**要件 11: システムおよびネットワークのセキュリティを定期的にテストする** **定義されたアプローチの要件**

### 11.1 システムとネットワークのセキュリティを定期的にテストするためのプロセスとメカニズムが定義され、理解されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **11.1.1** 要件 11 で特定されるすべてのセキュリティ ポリシーと運用手順は次のとおり: 文書化されている最新の状態に維持されている使用中すべての関係者に通知されている | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |
| **11.1.2** 要件 11 のアクティビティを実行するための役割と責任が文書化され、割り当てられ、理解されている。 | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |

### 11.2 ワイヤレスaccessポイントを特定して監視し、未承認のワイヤレスaccessポイントに対処します。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **11.2.1** 承認済みおよび未承認のワイヤレス access ポイントは、次のように管理されます: ワイヤレス (Wi-Fi) access ポイントの有無がテストされます。  すべての承認済みおよび未承認のワイヤレス access ポイントが検出され、識別されます。 テスト、検出、識別は、少なくとも 3 か月に 1 回行われる。 自動監視を使用すると、生成されたアラートを介して担当者に通知される。 | 組織がネットワーク access ポイントを認証用のMicrosoft Entra IDと統合している場合は、「[Requirement 1: ネットワーク セキュリティコントロールのインストールと管理](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-1)」を参照してください> |
| **11.2.2** 文書化された業務上の正当な理由を含め、承認されたワイヤレス access ポイントのインベントリが維持されます。 | Microsoft Entra IDには適用されません。 |

### 11.3 外部および内部の脆弱性が定期的に特定され、優先度が付けられ、対応が行われる。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **11.3.1** 内部脆弱性スキャンは次のように実行される。少なくとも 3 か月に 1 回。 高リスクと重大な脆弱性 (要件 6.3.1 で定義されているエンティティの脆弱性リスクの格付けに従う) が解決される。 再スキャンが実行され、リスクの高い脆弱性と重大な脆弱性 (前述のとおり) がすべて解決されたことを確認する。 スキャン ツールは、最新の脆弱性情報を使用して最新の状態に保たれる。 スキャンは資格のある担当者によって実行され、テスターの組織の独立性が存在する。 | Microsoft Entra ハイブリッド機能をサポートするサーバーを含めます。 たとえば、Microsoft Entra Connect、Application proxy コネクタなど、内部の脆弱性スキャンの一部として使用できます。 フェデレーション認証を使用している組織: フェデレーション システム インフラストラクチャの脆弱性を確認して対処します。 [Microsoft Entra IDとのフェデレーションとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) Microsoft Entra ID Protectionによって報告されたリスク検出を確認して軽減します。 シグナルを SIEM ソリューションと統合して、修復ワークフローや自動化とさらに統合します。 [リスクの種類と検出](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks) Microsoft Entra 評価ツールを定期的に実行し、結果に対処します。 [`AzureADAssessment`](https://github.com/AzureAD/AzureADAssessment)[インフラストラクチャのセキュリティ操作](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-infrastructure)[Azure Monitor ログを使用した Microsoft Entra ログの統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) |
| **11.3.1.1** その他すべての該当する脆弱性 (要件 6.3.1 で定義されているエンティティの脆弱性リスクの格付けで、高リスクまたは重大として格付けされていない脆弱性) は、次のように管理される。エンティティの対象指定リスク分析で定義されているリスクに基づいて対処される。これは、要件 12.3.1 で指定されたすべての要素に従って実行される。 再スキャンは必要に応じて行われる。 | Microsoft Entra ハイブリッド機能をサポートするサーバーを含めます。 たとえば、Microsoft Entra Connect、Application proxy コネクタなど、内部の脆弱性スキャンの一部として使用できます。 フェデレーション認証を使用している組織: フェデレーション システム インフラストラクチャの脆弱性を確認して対処します。 [Microsoft Entra IDとのフェデレーションとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) Microsoft Entra ID Protectionによって報告されたリスク検出を確認して軽減します。 シグナルを SIEM ソリューションと統合して、修復ワークフローや自動化とさらに統合します。 [リスクの種類と検出](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks) Microsoft Entra 評価ツールを定期的に実行し、結果に対処します。 [`AzureAD/AzureADAssessment`](https://github.com/AzureAD/AzureADAssessment)[インフラストラクチャのセキュリティ操作](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-infrastructure)[Azure Monitor ログを使用した Microsoft Entra ログの統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) |
| **11.3.1.2** 内部脆弱性スキャンは、認証されたスキャンを介して次のように実行される。認証されたスキャンの資格情報を受け入れることができないシステムが文書化されている。 スキャン用の資格情報を受け入れるシステムには、十分な特権が使用される。 認証されたスキャンに使用されるアカウントを対話型ログインに使用できる場合は、要件 8.2.2 に従って管理される。 | Microsoft Entra ハイブリッド機能をサポートするサーバーを含めます。 たとえば、Microsoft Entra Connect、Application proxy コネクタなど、内部の脆弱性スキャンの一部として使用できます。 フェデレーション認証を使用している組織: フェデレーション システム インフラストラクチャの脆弱性を確認して対処します。 [Microsoft Entra IDとのフェデレーションとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) Microsoft Entra ID Protectionによって報告されたリスク検出を確認して軽減します。 シグナルを SIEM ソリューションと統合して、修復ワークフローや自動化とさらに統合します。 [リスクの種類と検出](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks) Microsoft Entra 評価ツールを定期的に実行し、結果に対処します。 [`AzureADAssessment`](https://github.com/AzureAD/AzureADAssessment)[インフラストラクチャのセキュリティ操作](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-infrastructure)[Azure Monitor ログを使用した Microsoft Entra ログの統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) |
| **11.3.1.3** 内部脆弱性スキャンは、次のような重大な変更の後に実行される。(要件 6.3.1 で定義されているエンティティの脆弱性リスクの格付けによる) 高リスクと重大な脆弱性が解決された。 再スキャンは必要に応じて行われる。 スキャンは資格のある担当者によって実行され、テスターの組織の独立性が存在する (認定セキュリティ機関 (QSA) または認定スキャン ベンダー (ASV) である必要はない)。 | Microsoft Entra ハイブリッド機能をサポートするサーバーを含めます。 たとえば、Microsoft Entra Connect、Application proxy コネクタなど、内部の脆弱性スキャンの一部として使用できます。 フェデレーション認証を使用している組織: フェデレーション システム インフラストラクチャの脆弱性を確認して対処します。 [Microsoft Entra IDとのフェデレーションとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) Microsoft Entra ID Protectionによって報告されたリスク検出を確認して軽減します。 シグナルを SIEM ソリューションと統合して、修復ワークフローや自動化とさらに統合します。 [リスクの種類と検出](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks) Microsoft Entra 評価ツールを定期的に実行し、結果に対処します。 [`AzureADAssessment`](https://github.com/AzureAD/AzureADAssessment)[インフラストラクチャのセキュリティ操作](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-infrastructure)[Azure Monitor ログを使用した Microsoft Entra ログの統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) |
| **11.3.2** 外部脆弱性スキャンは次のように実行される。少なくとも 3 か月に 1 回。 PCI SSC ASV による。  脆弱性が解決され、スキャン合格のためのASVプログラムガイドの要件を満たす。 再スキャンは、合格スキャンの ASV プログラム ガイドの要件に従って脆弱性が解決されたことを確認するために、必要に応じて実行される。 | Microsoft Entra IDには適用されません。 |
| **11.3.2.1** 外部脆弱性スキャンは、次のような重大な変更の後に実行される。CVSS によって 4.0 以上のスコアが付いている脆弱性が解決される。 再スキャンは必要に応じて行われる。 スキャンは資格のある担当者によって実行され、テスターの組織の独立性が存在する (QSA または ASV である必要はない)。 | Microsoft Entra IDには適用されません。 |

### 11.4 外部および内部の侵入テストが定期的に実行され、悪用可能な脆弱性とセキュリティ上の弱点が修正される。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **11.4.1** 侵入テストの手法は、エンティティによって定義、文書化、実装され、次のものが含まれる。業界で受け入れられている侵入テストのアプローチ。 カード所有者データ環境 (CDE) の境界と重要なシステム全体をカバーすること。 ネットワークの内部と外部両方からのテスト。 任意のセグメント化およびスコープの縮小制御を検証するテスト。 少なくとも要件 6.2.4 に一覧表示されている脆弱性を識別するアプリケーション レイヤー侵入テスト。 ネットワーク機能とオペレーティング システムをサポートするすべてのコンポーネントを含むネットワーク レイヤー侵入テスト。 過去 12 か月間に発生した脅威と脆弱性のレビューと考慮事項。 侵入テスト中に見つかった悪用可能な脆弱性とセキュリティの弱点によってもたらされるリスクを評価して対処するための文書化されたアプローチ。 少なくとも 12 か月間の侵入テストの結果と修復アクティビティの結果の保有期間。 | [侵入テストの実施ルール、Microsoft Cloud](https://www.microsoft.com/msrc/pentest-rules-of-engagement) |
| **11.4.2** 内部侵入テストが次のように実行される。エンティティの定義された手法に従う。 少なくとも 12 か月に 1 回。 重要なインフラストラクチャまたはアプリケーションのアップグレードまたは変更後。 資格のある内部リソースまたは資格のある外部サードパーティによる。 テスターの組織の独立性が存在する (QSA または ASV である必要はない)。 | [侵入テストの実施ルール、Microsoft Cloud](https://www.microsoft.com/msrc/pentest-rules-of-engagement) |
| **11.4.3** 外部侵入テストが次のように実行される。エンティティの定義された手法に従う。 少なくとも 12 か月に 1 回。 重要なインフラストラクチャまたはアプリケーションのアップグレードまたは変更後。 資格のある内部リソースまたは資格のある外部サードパーティによる。 テスターの組織の独立性が存在する (QSA または ASV である必要はない)。 | [侵入テストの実施ルール、Microsoft Cloud](https://www.microsoft.com/msrc/pentest-rules-of-engagement) |
| **11.4.4** 侵入テスト中に見つかった悪用可能な脆弱性とセキュリティの弱点は、次のように修正される。要件 6.3.1 で定義されているセキュリティ問題によってもたらされるリスクに関するエンティティの評価に従う。 侵入テストを繰り返して修正を検証する。 | [侵入テストの実施ルール、Microsoft Cloud](https://www.microsoft.com/msrc/pentest-rules-of-engagement) |
| **11.4.5** セグメント化を使用して CDE を他のネットワークから分離する場合、セグメント化コントロールに対して侵入テストを次のように実行する。少なくとも 12 か月ごとに 1 回、セグメント化コントロール/メソッドの変更後。 使用中のすべてのセグメント化コントロール/メソッドをカバーする。 エンティティの定義された侵入テスト手法に従う。 セグメント化コントロール/メソッドが運用可能かつ効果的であることを確認し、CDE をスコープ外のすべてのシステムから分離する。 分離の使用の有効性を確認して、異なるセキュリティ レベルを持つシステムを分離する (要件 2.2.3 を参照)。 資格のある内部リソースまたは資格のある外部サードパーティが実行する。 テスターの組織の独立性が存在する (QSA または ASV である必要はない)。 | Microsoft Entra IDには適用されません。 |
| **11.4.6** "サービス プロバイダーのみの追加要件": セグメント化を使用して CDE を他のネットワークから分離する場合、セグメント化コントロールに対して侵入テストを次のように実行する。少なくとも 6 か月ごとに 1 回、セグメント化コントロール/メソッドの変更後。使用中のすべてのセグメント化コントロール/メソッドをカバーする。 エンティティの定義された侵入テスト手法に従う。 セグメント化コントロール/メソッドが運用可能かつ効果的であることを確認し、CDE をスコープ外のすべてのシステムから分離する。 分離の使用の有効性を確認して、異なるセキュリティ レベルを持つシステムを分離する (要件 2.2.3 を参照)。 資格のある内部リソースまたは資格のある外部サードパーティが実行する。 テスターの組織の独立性が存在する (QSA または ASV である必要はない)。 | Microsoft Entra IDには適用されません。 |
| **11.4.7** "マルチテナント サービス プロバイダーのみの追加要件": マルチテナント サービス プロバイダーは、要件 11.4.3 および 11.4.4 に従って外部侵入テストのために顧客をサポートする。 | [侵入テストの実施ルール、Microsoft Cloud](https://www.microsoft.com/msrc/pentest-rules-of-engagement) |

### 11.5 ネットワーク侵入と予期しないファイルの変更は検出され、対応が行われる。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **11.5.1** 侵入検出/侵入防止手法を使用して、次のようにネットワークへの侵入を検出または防止する。CDE の境界ですべてのトラフィックが監視される。 CDE の重要なポイントですべてのトラフィックが監視される。 セキュリティ侵害の疑いがある場合は、担当者にアラートが送信される。 すべての侵入検出および防止エンジン、ベースライン、署名が最新の状態に維持される。 | Microsoft Entra IDには適用されません。 |
| **11.5.1.1** "サービス プロバイダーのみの追加要件": 侵入検出/侵入防止手法によって、マルウェアの隠れ通信チャネルを検出、警告、防止、対処する。 | Microsoft Entra IDには適用されません。 |
| **11.5.2** 変更検出メカニズム (ファイル整合性監視ツールなど) が次のようにデプロイされる。重要なファイルの認可されていない修正 (変更、追加、削除を含む) を担当者に警告する。 重要なファイルの比較を毎週少なくとも 1 回実行する。 | Microsoft Entra IDには適用されません。 |

### 11.6 支払ページでの認可されていない変更は検出され、対応が行われる。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **11.6.1** 変更と改ざんの検出メカニズムは次のように展開されます。 HTTP ヘッダーと、consumer ブラウザーで受け取った支払いページの内容に対する不正な変更 (侵害、変更、追加、削除のインジケーターを含む) を担当者に通知します。 このメカニズムは、受信した HTTP ヘッダーと支払ページを評価するように構成される。 メカニズム関数は次のように実行される。少なくとも 7 日に 1 回またはエンティティの対象指定リスク分析で定義されている頻度で定期的に、すべての要素に従って実行される | Microsoft Entra IDには適用されません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-requirement-2"} -->
## Microsoft Entra IDと PCI-DSS 要件 2 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-2
- Service: entra / standards
- Article date: 2023-04-18
- Summary: すべてのシステム コンポーネントにセキュリティで保護された構成を適用するための、PCI-DSS で定義されたアプローチの要件について説明します

**要件 2: すべてのシステム コンポーネントにセキュリティで保護された構成を適用する** **定義されたアプローチの要件**

### 2.1 セキュリティで保護された構成をすべてのシステム コンポーネントに適用するためのプロセスとメカニズムが定義され、理解されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **2.1.1** 要件 2 で特定されるすべてのセキュリティ ポリシーと運用手順は次のとおり: 文書化されている最新の状態に維持されている使用中すべての関係者に通知されている | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |
| **2.1.2** 要件 2 のアクティビティを実行するための役割と責任が文書化され、割り当てられ、理解されている。 | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |

### 2.2 システム コンポーネントは安全に構成され、管理されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **2.2.1** 次の条件を満たすように構成標準が開発、実装、保守されている:  すべてのシステム コンポーネントをカバーする。  すべての既知のセキュリティ脆弱性に対処する。  業界で受け入れられているシステム強化の標準またはベンダー強化の推奨事項に準拠している。  要件 6.3.1 で定義されているように、新しい脆弱性の問題が特定されると更新される。  新しいシステムは、システム コンポーネントが運用環境に接続される前または直後に、構築されて検証された後に適用される。 | 「[Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)」を参照してください。 |
| **2.2.2** ベンダーの既定のアカウントが次のように管理される:  ベンダーの既定のアカウントが使用される場合、要件 8.3.6 に従って既定のパスワードが変更される。  ベンダーの既定のアカウントが使用されない場合、アカウントは削除または無効化される。 | Microsoft Entra IDには適用されません。 |
| **2.2.3** 異なるセキュリティ レベルを要求する主要機能が、次のように管理される:  システム コンポーネントには主要機能が 1 つだけ存在する  または  セキュリティ レベルが異なる主要機能が同じシステム コンポーネントに存在している場合、機能は互いに分離されている  または  セキュリティ レベルが異なる主要機能が同じシステム コンポーネントに存在している場合、すべての機能は、最も高いセキュリティが必要な機能に要求されるレベルのセキュリティで保護されている。 | 最小特権ロールの決定について説明します。 [Microsoft Entra ID のタスクごとに最小特権のロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task) |
| **2.2.4** 必要なサービス、プロトコル、デーモン、機能のみが有効であり、不要な機能はすべて削除または無効化されている。 | Microsoft Entra の設定を確認し、使用していない機能を無効にします。 [ID インフラストラクチャをセキュリティで保護するための手順](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity)[Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction) |
| **2.2.5** セキュリティで保護されていないサービス、プロトコル、またはデーモンが存在する場合:  業務上の正当な理由が文書化されている。  セキュリティで保護されていないサービス、プロトコル、またはデーモンを使用するリスクを軽減する追加のセキュリティ機能が文書化および実装されている。 | Microsoft Entra の設定を確認し、使用していない機能を無効にします。 [ID インフラストラクチャをセキュリティで保護するための手順](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity)[Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction) |
| **2.2.6** 誤用や悪用を防ぐようにシステム セキュリティ パラメーターが構成されている。 | Microsoft Entra の設定を確認し、使用していない機能を無効にします。 [ID インフラストラクチャをセキュリティで保護するための手順](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity)[Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction) |
| **2.2.7** すべての非コンソール管理アクセスは、強力な暗号化を使用して暗号化されます。 | 管理ポータル、Microsoft Graph、PowerShell などのMicrosoft Entra IDインターフェイスは、TLS を使用して転送中に暗号化されます。 [Microsoft Entra TLS 1.1 および 1.0 の非推奨の環境での TLS 1.2 のサポートを有効にします](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/enable-support-tls-environment?tabs=azure-monitor) |

### 2.3 ワイヤレス環境は安全に構成され、管理されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **2.3.1** CDE に接続されているワイヤレス環境またはアカウント データを送信する場合は、 すべてのワイヤレス ベンダーの既定値は、インストール時に変更されるか、セキュリティで保護されていることを確認します。これには、 ワイヤレス access ポイントの既定のワイヤレス暗号化キー  パスワード  SNMP の既定値  その他のセキュリティ関連のワイヤレス ベンダーの既定値が含まれます。 | 組織がネットワーク access ポイントを認証のMicrosoft Entra IDと統合している場合は、「[Requirement 1: ネットワーク セキュリティコントロールのインストールと管理](https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-1)を参照してください。 |
| **2.3.2** CDE に接続する、またはアカウント データを送信するワイヤレス環境で、次のようにワイヤレス暗号化キーが変更される:  会社または役職の業務に必要なキーを知っている担当者が退職または異動した場合は常に。  キーの侵害が疑われるか、侵害済みと判明した場合は。 | Microsoft Entra IDには適用されません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-requirement-5"} -->
## Microsoft Entra IDと PCI-DSS 要件 5 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-5
- Service: entra / standards
- Article date: 2023-04-18
- Summary: 悪意のあるソフトウェアからすべてのシステムとネットワークを保護するための、PCI-DSS で定義されたアプローチの要件について説明します

**要件 5: 悪意のあるソフトウェアからすべてのシステムとネットワークを保護する** **定義されたアプローチの要件**

### 5.1 悪意のあるソフトウェアからすべてのシステムとネットワークを保護するためのプロセスとメカニズムが定義され、理解されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **5.1.1** 要件 5 で特定されるすべてのセキュリティ ポリシーと運用手順は次のとおり: 文書化されている最新の状態に維持されている使用中すべての関係者に通知されている | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |
| **5.1.2** 要件 5 のアクティビティを実行するための役割と責任が文書化され、割り当てられ、理解されている。 | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |

### 5.2 悪意のあるソフトウェア (マルウェア) が防止されているか、検出および対処されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **5.2.1** すべてのシステム コンポーネントにマルウェア対策ソリューションがデプロイされている。ただし、要件 5.2.3 に従って定期的な評価で特定され、マルウェアの危険にさらされていないと結論付けられたシステム コンポーネントは除く。 | デバイスコンプライアンスを必要とする条件付きAccessポリシーを展開します。 [コンプライアンス ポリシーを使用して、Intune で管理するデバイスのルールを設定する](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started) デバイスのコンプライアンスの状態をマルウェア対策ソリューションと統合する。 [Intune における条件付きアクセスを使用して、Microsoft Defender for Endpoint のコンプライアンスを適用](https://learn.microsoft.com/ja-jp/mem/intune/protect/advanced-threat-protection)[Mobile Threat Defense と Intune の統合](https://learn.microsoft.com/ja-jp/mem/intune/protect/mobile-threat-defense) |
| **5.2.2** デプロイされたマルウェア対策ソリューション:  すべての既知の種類のマルウェアを検出する。 すべての既知の種類のマルウェアを削除するか、ブロックするか、封じ込める。 | Microsoft Entra IDには適用されません。 |
| **5.2.3** マルウェアの危険にさらされていないシステム コンポーネントが定期的に評価され、次のものが作成される:  マルウェアの危険にさらされていないすべてのシステム コンポーネントの文書化された一覧。  これらのシステム コンポーネントに対する、進化するマルウェアの脅威の特定と評価。  そのようなシステム コンポーネントには今後もマルウェア対策保護が必要でないかどうかの確認。 | Microsoft Entra IDには適用されません。 |
| **5.2.3.1** マルウェアの危険にさらされていないと特定されたシステム コンポーネントの定期評価の頻度が、要件 12.3.1 で指定されたすべての要素に従って実行される、エンティティの対象指定リスク分析で定義されている。 | Microsoft Entra IDには適用されません。 |

### 5.3 マルウェア対策のメカニズムとプロセスがアクティブであり、維持され、監視されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **5.3.1** マルウェア対策ソリューションが自動更新によって最新の状態に保たれる。 | Microsoft Entra IDには適用されません。 |
| **5.3.2** マルウェア対策ソリューション:  定期的なスキャンとアクティブまたはリアルタイム スキャンを実行する。  または  システムまたはプロセスの継続的なビヘイビアー分析を実行する。 | Microsoft Entra IDには適用されません。 |
| **5.3.2.1** 要件 5.3.2 を満たすために定期的なマルウェア スキャンが実行される場合に、スキャンの頻度が、要件 12.3.1 で指定されたすべての要素に従って実行される、エンティティの対象指定リスク分析で定義されている。 | Microsoft Entra IDには適用されません。 |
| **5.3.3** リムーバブル電子メディアについて、マルウェア対策ソリューションが次の機能を備えている:  メディアが挿入、接続、または論理的にマウントされたときに自動スキャンを実行する  または  メディアが挿入、接続、または論理的にマウントされたときに、システムまたはプロセスの継続的なビヘイビアー分析を実行する。 | Microsoft Entra IDには適用されません。 |
| **5.3.4** 要件 10.5.1 に従って、マルウェア対策ソリューションの監査ログが有効化および保持される。 | Microsoft Entra IDには適用されません。 |
| **5.3.5** 具体的に文書化され、ケースバイケースかつ期間限定で管理者に認可された場合を除き、マルウェア対策メカニズムをユーザーが無効化または変更できない。 | Microsoft Entra IDには適用されません。 |

### 5.4 フィッシング詐欺対策メカニズムでは、フィッシング攻撃からユーザーを保護する。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **5.4.1** フィッシング攻撃を検出してユーザーを保護するためのプロセスと自動化されたメカニズムが用意されている。 | フィッシングに強い資格情報を使用するようにMicrosoft Entra IDを構成します。 [フィッシング耐性 MFA の実装に関する考慮事項](https://learn.microsoft.com/ja-jp/entra/standards/memo-22-09-multi-factor-authentication) 条件付きAccessのコントロールを使用して、フィッシングに対する耐性のある資格情報による認証を要求します。 [Conditional Access認証強度](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths) ガイダンスは、ID およびアクセス管理の構成に関連します。 フィッシング攻撃を軽減するには、Microsoft 365などのワークロード機能をデプロイします。  Microsoft 365 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-requirement-6"} -->
## Microsoft Entra IDと PCI-DSS 要件 6 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-6
- Service: entra / standards
- Article date: 2023-04-18
- Summary: セキュリティで保護されたシステムとソフトウェアの開発と維持に関する PCI-DSS で定義されたアプローチの要件について説明します

**要件 6: セキュリティで保護されたシステムとソフトウェアを開発し、維持する** **定義されたアプローチの要件**

### 6.1 セキュリティで保護されたシステムとソフトウェアを開発および維持するためのプロセスとメカニズムが定義され、理解されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **6.1.1** 要件 6 で特定されるすべてのセキュリティ ポリシーと運用手順は次のとおり: 文書化されている最新の状態に維持されている使用中すべての関係者に通知されている | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |
| **6.1.2** 要件 6 のアクティビティを実行するための役割と責任が文書化され、割り当てられ、理解されている。 | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |

### 6.2 パッケージおよびカスタム ソフトウェアが安全に開発されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **6.2.1** パッケージおよびカスタム ソフトウェアが、次のように安全に開発されている。業界標準や安全な開発のためのベスト プラクティスに基づく。 PCI-DSS に準拠する (たとえば、セキュリティで保護された認証およびログ記録)。 ソフトウェア開発ライフサイクルの各段階における情報セキュリティの問題に関する考慮事項を組み込む。 | OAuth2 や OpenID Connect (OIDC) などの最新の認証プロトコルを使用するアプリケーションを調達して開発し、Microsoft Entra IDと統合します。 Microsoft ID プラットフォームを使用してソフトウェアを構築します。 [Microsoft ID プラットフォームのベスト プラクティスと推奨事項](https://learn.microsoft.com/ja-jp/entra/identity-platform/identity-platform-integration-checklist) |
| **6.2.2** パッケージおよびカスタム ソフトウェアに取り組むソフトウェア開発担当者は、少なくとも 12 か月に 1 回、次のようにトレーニングされる。職務および開発言語に関連するソフトウェア セキュリティについて。 セキュリティで保護されたソフトウェア設計とセキュリティで保護されたコーディング手法を含む。 セキュリティ テスト ツールを使用する場合は、ソフトウェアの脆弱性を検出するためのツールの使用方法も含まれる。 | 次の試験を使用して、Microsoft ID プラットフォームでの能力証明を提供します。[Exam MS-600: Building Applications and Solutions with Microsoft 365 Core Services](https://learn.microsoft.com/ja-jp/credentials/certifications/exams/ms-600/) 次のトレーニングを使用して試験の準備を行います: [MS-600: Microsoft ID を実装します](https://learn.microsoft.com/ja-jp/training/paths/m365-identity-associate/) |
| **6.2.3** パッケージおよびカスタム ソフトウェアは、運用環境または顧客にリリースされる前にレビューされ、次のように潜在的なコーディングの脆弱性を特定して修正する。コード レビューによって、セキュリティで保護されたコーディング ガイドラインに従ってコードが開発されていることを確認する。 コード レビューでは、既存のソフトウェアと新しいソフトウェアの両方の脆弱性が検出される。 リリース前に適切な修正を実装する。 | Microsoft Entra IDには適用されません。 |
| **6.2.3.1** 運用環境にリリースされる前に、パッケージおよびカスタム ソフトウェアに対して手動コード レビューが実行される場合、コードの変更は次のように確認される。コード レビュー手法とセキュリティで保護されたコーディング プラクティスに精通している、元のコード作成者以外の個人によってレビューされる。 リリースする前に管理者が確認し承認する。 | Microsoft Entra IDには適用されません。 |
| **6.2.4** ソフトウェア エンジニアリング手法またはその他の方法は、ソフトウェア開発担当者によって定義され、パッケージおよびカスタム ソフトウェアの一般的なソフトウェア攻撃および関連脆弱性を防止または軽減するために使用される。これには次のものが含まれるが、これらに限定されない。SQL、LDAP、XPath、またはその他のコマンド、パラメーター、オブジェクト、障害、またはインジェクション タイプの欠陥を含むインジェクション攻撃。 バッファー、ポインター、入力データ、または共有データを操作しようとする試みを含む、データとデータ構造に対する攻撃。 脆弱、安全でない、または不適切な暗号化実装、アルゴリズム、暗号スイート、または操作モードを悪用しようとする試みを含む、暗号化の使用に対する攻撃。 API、通信プロトコルとチャネル、クライアント側の機能、またはその他のシステム/アプリケーション機能やリソースを操作してアプリケーションの機能を悪用またはバイパスしようとする試みを含む、ビジネス ロジックに対する攻撃。 これには、クロスサイト スクリプティング (XSS) とクロスサイト リクエスト フォージェリ (CSRF) が含まれる。  識別、認証、または承認メカニズムをバイパスまたは悪用しようとする試み、またはそのようなメカニズムの実装における弱点を悪用しようとする試みを含む、access controlメカニズムに対する攻撃。 要件 6.3.1 の定義に従った脆弱性の特定プロセスで特定される "高リスクの" 脆弱性を使用する攻撃。 | Microsoft Entra IDには適用されません。 |

### 6.3 セキュリティの脆弱性が特定され、対応が行われる。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **6.3.1** セキュリティの脆弱性は次のように特定され、管理される。新しいセキュリティの脆弱性は、国際的および国/地域のコンピューター緊急対応チーム (CERT) からのアラートを含む、セキュリティの脆弱性情報に関する業界で認識されたソースを使用して特定される。 脆弱性には、業界のベスト プラクティスと潜在的な影響の考慮に基づいてリスク格付けが割り当てられる。 リスク格付けでは、少なくとも、環境に高リスクまたは重大と考えられるすべての脆弱性が特定される。 パッケージおよびカスタム ソフトウェア、サードパーティ製ソフトウェア (オペレーティング システムやデータベースなど) の脆弱性について説明する。 | 脆弱性について説明します。 [MSRC | セキュリティの更新、セキュリティ更新プログラム ガイド](https://msrc.microsoft.com/update-guide) |
| **6.3.2** パッケージおよびカスタム ソフトウェアのインベントリ、パッケージおよびカスタム ソフトウェアに組み込まれたサードパーティ製ソフトウェア コンポーネントは、脆弱性とパッチ管理を容易にするために維持される。 | インベントリの認証にMicrosoft Entra IDを使用して、アプリケーションのレポートを生成します。 [`applicationSignInDetailedSummary` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationsignindetailedsummary?view=graph-rest-beta&viewFallbackFrom=graph-rest-1.0&preserve-view=true)[エンタープライズ アプリケーションに一覧表示されるアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-list) |
| **6.3.3** すべてのシステム コンポーネントは、適用可能なセキュリティ パッチ/更新プログラムを次のようにインストールすることで、既知の脆弱性から保護される。重大または高セキュリティのパッチ/更新プログラム (要件 6.3.1 のリスク格付けプロセスに従って識別) は、リリースから 1 か月以内にインストールされる。 その他の適用可能なセキュリティ パッチ/更新プログラムはすべて、エンティティによって決定された適切な期間内 (リリースから 3 か月以内など) にインストールされる。 | Microsoft Entra IDには適用されません。 |

### 6.4 一般向けの Web アプリケーションが攻撃から保護されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **6.4.1** 一般向け Web アプリケーションの場合、次のように、新しい脅威と脆弱性が継続的に対処され、これらのアプリケーションは既知の攻撃から保護される。手動または自動化されたアプリケーション脆弱性セキュリティ評価ツールまたはメソッドを使用した一般向け Web アプリケーションのレビューを次のように行う。 – 少なくとも 12 か月に 1 回、および大幅な変更後。  – アプリケーションのセキュリティに特化したエンティティによる。  – 少なくとも、要件 6.2.4 のすべての一般的なソフトウェア攻撃を含める。  – すべての脆弱性が要件 6.3.1 に従って格付けされる。  – すべての脆弱性が修正される。  – アプリケーションは修正後に再評価される。または次のように、Web ベースの攻撃を継続的に検出して防止する自動化された技術ソリューションをインストールする。 – Web ベースの攻撃を検出して防止するために、一般向け Web アプリケーションの前にインストールされる。  – アクティブに実行され、該当する最新の状態である。  – 監査ログの生成。  – Web ベースの攻撃をブロックするか、直ちに調査されるアラートを生成するように構成されている。 | Microsoft Entra IDには適用されません。 |
| **6.4.2** 一般向け Web アプリケーションの場合、Web ベースの攻撃を継続的に検出して防止する自動化された技術ソリューションがデプロイされ、少なくとも次のことが含まれる。一般向け Web アプリケーションの前にインストールされ、Web ベースの攻撃を検出して防止するように構成されている。  アクティブに実行され、最新である。 監査ログの生成。 Web ベースの攻撃をブロックするか、直ちに調査されるアラートを生成するように構成されている。 | Microsoft Entra IDには適用されません。 |
| **6.4.3** consumer のブラウザーで読み込まれて実行されるすべての支払いページ スクリプトは、次のように管理されます。 各スクリプトが承認されていることを確認するメソッドが実装されています。 メソッドは、各スクリプトの整合性を確保するために実装される。 すべてのスクリプトのインベントリは、それぞれが必要な理由に関する正当な理由の記述と共に維持される。 | Microsoft Entra IDには適用されません。 |

### 6.5 すべてのシステム コンポーネントに対する変更が安全に管理されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **6.5.1** 運用環境のすべてのシステム コンポーネントに対する変更は、以下のものを含む確立された手順に従って行われる。変更の理由と説明。 セキュリティへの影響に関するドキュメント。  認可された者による文書化された変更の承認。 変更がシステムのセキュリティに悪影響を与えないことを確認するためのテスト。 パッケージおよびカスタム ソフトウェアの変更については、運用環境にデプロイする前に、要件 6.2.4 への準拠についてすべての更新プログラムがテストされる。 エラーに対処し、セキュリティで保護された状態に戻す手順。 | 変更制御プロセスに Microsoft Entra 構成の変更を含めます。 |
| **6.5.2** 重大な変更が完了したら、該当するすべての PCI-DSS 要件が、すべての新しいまたは変更されたシステムおよびネットワークに実装されていることを確認し、適切にドキュメントを更新する。 | Microsoft Entra IDには適用されません。 |
| **6.5.3** 実稼働前環境は運用環境から分離され、分離はaccess制御によって適用されます。 | 組織の要件に基づいて、運用前環境と運用環境を分離するためのアプローチ。 [シングル テナントでのリソースの分離](https://learn.microsoft.com/ja-jp/entra/architecture/secure-single-tenant)[複数のテナントでのリソースの分離](https://learn.microsoft.com/ja-jp/entra/architecture/secure-multiple-tenants) |
| **6.5.4** ロールと機能は、運用環境と運用前環境の間で分離され、レビューおよび承認された変更のみがデプロイされるように説明責任を果たす。 | 特権的な役割と専用のプレプロダクション テナントについて学びます。 [Microsoft Entra ロールのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/best-practices) |
| **6.5.5** ライブ PAN は、環境が CDE に含まれ、該当するすべての PCI-DSS 要件に従って保護されている場合を除き、運用前環境では使用されない。 | Microsoft Entra IDには適用されません。 |
| **6.5.6** テスト データとテスト アカウントは、システムが運用環境に移行する前にシステム コンポーネントから削除される。 | Microsoft Entra IDには適用されません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-requirement-7"} -->
## Microsoft Entra IDと PCI-DSS 要件 7 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-7
- Service: entra / standards
- Article date: 2024-08-23
- Summary: PCI-DSS が定義するアプローチの要件について、ビジネスニーズに応じてシステムコンポーネントおよび CHD へのアクセスを制限する方法を学びます。

**要件 7: ビジネス ニーズに基づいてシステムコンポーネントとカード所有者データへのアクセスを制限する** **定義された方法の要件**

### 7.1 ビジネスニーズに応じてシステムコンポーネントやカード所有者データにaccessを制限するためのプロセスとメカニズムが定義され、理解されます。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **7.1.1** 要件 7 で特定されるすべてのセキュリティ ポリシーと運用手順は次のとおり: 文書化されている最新の状態に維持されている使用中すべての関係者に通知されている | Microsoft Entra IDを用いて、カード所有者データ環境 (CDE) アプリケーションへのアクセ スを認証および承認のために統合します。  リモートアクセス技術のための条件付きアクセスポリシーを文書化する。 Microsoft Graph API と PowerShell を使用して自動化します。 [Conditional Access: Programmatic access](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-apis) セキュリティ ポリシーの変更と Microsoft Entra テナント構成を記録するために Microsoft Entra 監査ログをアーカイブします。 使用状況を記録するには、Microsoft Entra サインイン ログをセキュリティ情報イベント管理 (SIEM) システムにアーカイブします。 [Microsoft Entra のアクティビティログ Azure モニターで](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations) |
| **7.1.2** 要件 7 のアクティビティを実行するための役割と責任が文書化され、割り当てられ、理解されている。 | Microsoft Entra IDを使用して、CDEアプリケーションへのアクセスを認証と承認に統合します。  - アプリケーションへのユーザー ロールの割り当てまたはグループ メンバーシップ  - Microsoft Graphを使用してアプリケーションの割り当てを一覧表示 - 割り当ての変更を追跡するには、Microsoft Entra 監査ログを使用します。 [ユーザーに付与された appRoleAssignments を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/user-list-approleassignments?view=graph-rest-1.0&tabs=http&preserve-view=true)[Get-MgServicePrincipalAppRoleAssignedTo](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipalapproleassignedto?view=graph-powershell-1.0&preserve-view=true)**特権アクセス** Microsoft Entra 監査ログを使用してディレクトリ ロールの割り当てを追跡します。 この PCI 要件に関連する管理者ロール:  - グローバル  - アプリケーション  - 認証 - 認証ポリシー  - ハイブリッド ID  最小特権accessを実装するには、Microsoft Entra IDを使用してカスタム ディレクトリ ロールを作成します。  AZUREで CDE の一部を構築する場合は、所有者、共同作成者、ユーザー Access管理者などの特権ロールの割り当て、CDE リソースがデプロイされているサブスクリプション カスタム ロールを文書化します。  Microsoft では、Privileged Identity Management (PIM) を使用して、ロールに対してジャストインタイム (JIT) アクセスを有効にすることをお勧めします。 PIM を使用すると、グループ メンバーシップが CDE アプリケーションまたはリソースに対する特権accessを表すシナリオで、Microsoft Entra セキュリティ グループへの JIT accessが有効になります。 Microsoft Entra 組み込みロールMicrosoft Entra ID のIDおよびアクセス管理操作のリファレンス ガイドMicrosoft Entra ID でカスタム ロールを作成するMicrosoft Entra ID のハイブリッドおよびクラウド デプロイメント用の特権アクセスをセキュリティで保護するMicrosoft Entra 特権ID管理とは?すべての分離アーキテクチャのベスト プラクティスグループのためのPIM |

### 7.2 システムコンポーネントとデータへのAccessが適切に定義され、割り当てられます。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **7.2.1** アクセス制御モデルが定義されており、エンティティのビジネスとアクセスのニーズに応じて、 適切なアクセス権が付与されます。  ユーザーのジョブ分類と機能に基づく、システムコンポーネントおよびデータリソースへのアクセス。 ジョブ機能を実行するために必要な最小限の特権 (ユーザー、管理者など)。 | Microsoft Entra IDを使用して、アプリケーション内のロールにユーザーを直接割り当てるか、グループ メンバーシップを使用してユーザーを割り当てます。  属性として標準化された分類が実装されている組織では、ユーザーのジョブの分類と機能に基づいてaccess許可を自動化できます。 Microsoft Entra のグループ メンバーシップを使用してグループを管理し、動的割り当てポリシーを使用して Microsoft Entra エンタイトルメント管理のアクセス パッケージを使用します。 エンタイトルメント管理を使用して職務の分離を定義し、最小限の特権を示します。  PIM を使用すると、グループ メンバーシップが CDE アプリケーションまたはリソースに対する特権アクセスを表すカスタム シナリオで、Microsoft Entra セキュリティ グループに JIT アクセスできます。 [動的メンバーシップ グループのルールを管理する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)[エンタイトルメント管理でアクセス パッケージの自動割り当てポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)[エンタイトルメント管理でアクセス パッケージの職務分離を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible)[グループ用のPIM](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups) |
| **7.2.2** Access は、 ジョブの分類と機能に基づいて、特権ユーザーを含むユーザーに割り当てられます。 業務を遂行するために必要な最小限の権限。 | Microsoft Entra IDを使用して、直接またはグループ メンバーシップを使用して、アプリケーションのロールにユーザーを割り当てます。  属性として標準化された分類が実装されている組織では、ユーザーのジョブの分類と機能に基づいてaccess許可を自動化できます。 Microsoft Entra のグループ メンバーシップを使用してグループを管理し、動的割り当てポリシーを使用して Microsoft Entra エンタイトルメント管理のアクセス パッケージを使用します。 エンタイトルメント管理を使用して職務の分離を定義し、最小限の特権を示します。  PIM を使用すると、グループ メンバーシップが CDE アプリケーションまたはリソースに対する特権アクセスを表すカスタム シナリオで、Microsoft Entra セキュリティ グループに JIT アクセスできます。 [動的メンバーシップ グループのルールを管理する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)[エンタイトルメント管理でアクセス パッケージの自動割り当てポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)[エンタイトルメント管理でアクセス パッケージの職務分離を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible)[グループ用のPIM](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups) |
| **7.2.3** 必要な特権は、認可された担当者によって承認される。 | 権限管理では、リソースへのアクセスを付与するための承認ワークフローと、定期的なアクセスレビューがサポートされています。 [エンタイトルメント管理でのアクセス要求の承認または拒否](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve)[エンタイトルメント管理のアクセスパッケージのアクセスの確認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-review-access) PIM は、Microsoft Entra ディレクトリ ロール、Azure ロール、およびクラウド グループをアクティブ化するための承認ワークフローをサポートします。 [PIM で Microsoft Entra ロールに対する要求を承認または拒否する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow)[グループのメンバーと所有者のアクティブ化要求を承認する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-approval-workflow) |
| **7.2.4** サードパーティ/ベンダー アカウントを含むすべてのユーザー アカウントと関連するaccess特権は、 少なくとも 6 か月に 1 回確認されます。  業務内容に応じて、ユーザー アカウントとアクセスが適切なままであることを確認します。  不適切なアクセスに対応します。 管理は、アクセスが引き続き適切であることを認識しています。 | アプリケーションへのアクセスを直接割り当てたり、グループメンバーシップを使用して付与する場合は、Microsoft Entraアクセスレビューを構成してください。 エンタイトルメント管理を使用してアプリケーションにaccessを付与する場合は、access パッケージ レベルでaccessレビューを有効にします。 [エンタイトルメント管理でアクセス パッケージのアクセス レビューを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create) サードパーティおよびベンダー アカウントに Microsoft Entra 外部 ID を使用します。 サードパーティアカウントやベンダーアカウントなど、外部 ID を対象とするaccessレビューを実行できます。 [アクセスレビューでゲストアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-guest-access-with-access-reviews) |
| **7.2.5** すべてのアプリケーション アカウントとシステム アカウントおよび関連するアクセス特権が割り当て及び管理されています。 システムまたはアプリケーションの操作に必要な最小限の特権に基づいて割り当てられ、管理されています。  Access は、その使用を特に必要とするシステム、アプリケーション、またはプロセスに限定されます。 | Microsoft Entra IDを使用して、直接またはグループ メンバーシップを使用して、アプリケーションのロールにユーザーを割り当てます。  属性として標準化された分類が実装されている組織では、ユーザーのジョブの分類と機能に基づいてaccess許可を自動化できます。 Microsoft Entra のグループ メンバーシップを使用してグループを管理し、動的割り当てポリシーを使用して Microsoft Entra エンタイトルメント管理のアクセス パッケージを使用します。 エンタイトルメント管理を使用して職務の分離を定義し、最小限の特権を示します。  PIM を使用すると、グループ メンバーシップが CDE アプリケーションまたはリソースに対する特権アクセスを表すカスタム シナリオで、Microsoft Entra セキュリティ グループに JIT アクセスできます。 [動的メンバーシップ グループのルールを管理する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)[エンタイトルメント管理でアクセス パッケージの自動割り当てポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)[エンタイトルメント管理でアクセス パッケージの職務分離を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible)[グループ用のPIM](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups) |
| **7.2.5.1** アプリケーション アカウントおよびシステム アカウントとそれに関連するアクセス特権によるすべてのアクセスは、定期的に（要件 12.3.1 で指定された要素に従って実施されるエンティティの対象リスク分析で定義された頻度で）見直されます。  アプリケーション/システムへのアクセスは、実行中の機能に適しています。  不適切なアクセスに対応します。  経営陣は、アクセスが適切であり続けることを確認します。 | サービス アカウントのアクセス許可を確認するときのベスト プラクティス。 [Microsoft Entra サービス アカウントの管理](https://learn.microsoft.com/ja-jp/entra/architecture/govern-service-accounts)[オンプレミス サービス アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-govern-on-premises) |
| **7.2.6**保存されているカード所有者データのリポジトリに対するすべてのユーザーアクセスは、次のように制限されます。 アプリケーションまたはその他のプログラムによる方法を使用して、ユーザーの役割と最小特権に基づいてアクセスおよび許可されたアクションが規定されます。  保存されているカードホルダー データ (CHD) のリポジトリを直接accessまたはクエリできるのは、担当する管理者だけです。 | 最新のアプリケーションでは、アクセスをデータリポジトリに制限する方法をプログラム的に利用できます。  OAuth や OpenID Connect (OIDC) などの先進認証プロトコルを使用して、アプリケーションをMicrosoft Entra IDと統合します。 [Microsoft ID プラットフォーム上の OAuth 2.0 プロトコルと OIDC プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) 特権ユーザーと特権のないユーザー accessをモデル化するためのアプリケーション固有のロールを定義します。 ユーザーまたはグループをロールに割り当てます。 [アプリケーションにアプリ ロールを追加してトークンで受け取る](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) アプリケーションによって公開される API の場合は、OAuth スコープを定義して、ユーザーと管理者の同意を有効にします。 [Microsoft ID プラットフォームにおけるスコープとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc) 次の方法を用いて、リポジトリへの特権アクセスと非特権アクセスをモデル化し、リポジトリへの直接アクセスを避けてください。 管理者とオペレーターがaccessを必要とする場合は、基になるプラットフォームごとに付与します。 たとえば、Azureの ARM IAM の割り当て、Access Control リスト (ACL) ウィンドウなど Azureでのアプリケーション サービスとしてのプラットフォーム (PaaS) とサービスとしてのインフラストラクチャ (IaaS) のセキュリティ保護を含むアーキテクチャ ガイダンスを参照してください。 [Azure アーキテクチャ センター](https://learn.microsoft.com/ja-jp/azure/architecture/) |

### 7.3 システム コンポーネントとデータへのAccessは、access control システムを介して管理されます。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entra のガイダンスとレコメンデーション |
| --- | --- |
| **7.3.1** ユーザーの知る必要性に基づいてアクセスを制限し、すべてのシステムコンポーネントを対象とするアクセス制御システムが導入されています。 | CDE内のアプリケーションへのアクセスを統合し、アクセス制御システムの認証と承認としてMicrosoft Entra IDを利用します。 条件付きAccess ポリシー。アプリケーションの割り当てによって、アプリケーションへのaccessが制御されます。 [条件付きアクセスとは何ですか?](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)[アプリケーションにユーザーとグループを割り当てます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) |
| **7.3.2** access control システムは、ジョブの分類と機能に基づいて個人、アプリケーション、システムに割り当てられたアクセス許可を適用するように構成されています。 | CDE内のアプリケーションへのアクセスを統合し、アクセス制御システムの認証と承認としてMicrosoft Entra IDを利用します。 条件付きAccess ポリシー。アプリケーションの割り当てによって、アプリケーションへのaccessが制御されます。 [条件付きアクセスとは何ですか?](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)[アプリケーションにユーザーとグループを割り当てます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) |
| **7.3.3** access control システムは既定で "すべて拒否" に設定されています。 | 条件付きAccessを使用して、グループ メンバーシップ、アプリケーション、ネットワークの場所、資格情報の強度などのaccess要求条件に基づいてaccessをブロックします。[Conditional Access: ブロック access](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-example)誤って構成されたブロック ポリシーが意図しないロックアウトの原因になる可能性があります。 緊急アクセス戦略を設計する。 [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) で緊急アクセス管理者アカウントを管理します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/pci-requirement-8"} -->
## Microsoft Entra IDと PCI-DSS 要件 8 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/pci-requirement-8
- Service: entra / standards
- Article date: 2023-04-18
- Summary: ユーザーを識別してシステム コンポーネントへのアクセスを認証するための、PCI-DSS で定義されたアプローチの要件について説明します

**要件 8: ユーザーを識別し、システム コンポーネントへのアクセスを認証する** **定義されたアプローチの要件**

### 8.1 ユーザーを識別し、システム コンポーネントへのアクセスを認証するためのプロセスとメカニズムが定義され、理解されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entraガイダンスと推奨事項 |
| --- | --- |
| **8.1.1** 要件 8 で特定されるすべてのセキュリティ ポリシーと運用手順は次のとおり: 文書化されている最新の状態に維持されている使用中すべての関係者に通知されている | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |
| **8.1.2** 要件 8 のアクティビティを実行するための役割と責任が文書化され、割り当てられ、理解されている。 | ここで示しているガイダンスとリンクを使用してドキュメントを作成し、お使いの環境の構成に基づいた要件を満たします。 |

### 8.2 ユーザーと管理者のユーザー ID と関連アカウントが、アカウントのライフサイクル全体を通じて厳密に管理されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entraガイダンスと推奨事項 |
| --- | --- |
| **8.2.1** システム コンポーネントまたはカード所有者データへのアクセスが許可される前に、すべてのユーザーに一意の ID が割り当てられる。 | Microsoft Entra IDに依存する CDE アプリケーションの場合、一意のユーザー ID はユーザー プリンシパル名 (UPN) 属性です。 [Microsoft Entra UserPrincipalName の割り当て](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-userprincipalname) |
| **8.2.2** グループ アカウント、共有アカウント、または汎用アカウント、あるいはその他の共有される認証資格情報は、例外的に必要な場合にのみ使用され、次のように管理される:  例外的な状況で必要な場合を除き、アカウントの使用は禁止される。  例外的な状況で必要な時間に限定して使用される。  使用に関する業務上の正当な理由が文書化されている。  使用は管理者によって明示的に承認される  アカウントへのアクセスが許可される前に個々のユーザー ID が確認される。  実行されるすべてのアクションは、個々のユーザーに起因します。 | アプリケーション アクセスにMicrosoft Entra IDを使用する CDN に、共有アカウントを禁止するプロセスがあることを確認します。 承認が必要な例外としてそれらを作成します。  Azure にデプロイされた CDE リソースの場合は、共有サービス アカウントを作成するのではなく、Azure リソースのマネージド ID を使用してワークロード ID を表します。 [Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) マネージド ID が使用できず、アクセスされるリソースが OAuth プロトコルを使用している場合は、サービス プリンシパルを使用してワークロード ID を表します。 OAuth スコープを使用して最小特権アクセスを ID に付与します。 管理者は、共有アカウントを作成するためのアクセスを制限したり、そのための承認ワークフローを定義したりできます。 [ワークロード ID とは](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) |
| **8.2.3***サービス プロバイダー限定の追加要件*: 顧客施設にリモートからアクセスするサービス プロバイダーは、顧客施設ごとに固有の認証要素を使用する。 | Microsoft Entra IDには、ハイブリッド機能を有効にするオンプレミス コネクタがあります。 コネクタは識別可能であり、一意に生成された資格情報を使用します。 [Microsoft Entra Connect Sync: 同期の理解とカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)[クラウド同期の詳細](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-how-it-works)[Microsoft Entra オンプレミス アプリケーションのプロビジョニング アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)[クラウド HR アプリケーションから Microsoft Entra へのユーザー プロビジョニング計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)[Microsoft Entra Connect Health エージェントをインストールします](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install) |
| **8.2.4** ユーザー ID、認証要素、その他の ID オブジェクトの追加、削除、変更が次のように管理される:  適切な承認を得て認可される。  文書化された承認で指定された特権のみを使用して実装される。 | Microsoft Entra IDには、人事システムからの自動ユーザー アカウント プロビジョニングがあります。 この機能を使用して、ライフサイクルを作成します。 [人事主導のプロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning) Microsoft Entra ID には、結合子、ムーバー、および脱退プロセスのカスタマイズされたロジックを有効にするライフサイクル ワークフローがあります。 [ライフサイクル ワークフローとは](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) Microsoft Entra ID には、Microsoft Graphを使用して認証方法を管理するためのプログラムインターフェイスがあります。 Windows Hello for Businessキーや FIDO2 キーなどの一部の認証方法では、登録にユーザーの介入が必要です。 [Graph 認証方法 API を使用開始する](https://learn.microsoft.com/ja-jp/graph/authenticationmethods-get-started) 管理者または自動化ツールが、Graph API を使用して一時的なアクセスパスの資格情報を生成します。 パスワードレスのオンボードには、この資格情報を使用します。 [パスワードレス認証方法を登録するために、Microsoft Entra IDで一時アクセス パスを構成します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) |
| **8.2.5** 解雇されたユーザーのアクセスはただちに取り消される。 | アカウントへのアクセスを取り消すには、Microsoft Entra IDから同期されたハイブリッド アカウントのオンプレミス アカウントを無効にし、Microsoft Entra IDのアカウントを無効にして、トークンを取り消します。 [Microsoft Entra ID でユーザー アクセスを取り消す](https://learn.microsoft.com/ja-jp/entra/identity/users/users-revoke-access) 互換性のあるアプリケーションに対して、継続的アクセス評価 (CAE) を使用して、Microsoft Entra ID と双方向の通信を行います。 アプリは、アカウントの終了やトークンの拒否などのイベントの通知を受けることができます。 [継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) |
| **8.2.6** アクティブでないユーザー アカウントは、アクティブでなくなってから 90 日以内に削除または無効化される。 | ハイブリッド アカウントの場合、管理者は 90 日ごとにActive DirectoryとMicrosoft Entraのアクティビティを確認します。 Microsoft Entra IDの場合は、Microsoft Graphを使用して最後のサインイン日を見つけます。 方法: Microsoft Entra ID |
| **8.2.7** リモート アクセスによるシステム コンポーネントへのアクセスや、そのサポートまたは保守のためにサード パーティが使用するアカウントが次のように管理される:  必要な期間中のみ有効化され、未使用時は無効化される。  予期しないアクティビティがないか使用状況が監視される。 | Microsoft Entra IDには外部 ID 管理機能があります。  エンタイトルメント管理によって統制されたゲスト ライフサイクルを使用します。 外部ユーザーは、アプリ、リソース、アクセス パッケージのコンテキストでオンボードされます。これらは、期間限定で許可し、定期的なアクセス レビューを義務付けることができます。 レビューの結果、アカウントが削除または無効化される可能性があります。 [エンタイトルメント管理での外部ユーザーのGovern アクセス](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users) Microsoft Entra IDは、ユーザーおよびセッション レベルでリスク イベントを生成します。 予期しないアクティビティを保護、検出して対応する方法について確認してください。 [リスクとは](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks) |
| **8.2.8** ユーザー セッションが 15 分を越えてアイドル状態になった場合、ユーザーは再認証を行って端末またはセッションを再アクティブ化するように要求される。 | Intune とMicrosoft Intuneでエンドポイント管理ポリシーを使用します。 次に、条件付きアクセスを使用して、準拠しているデバイスからのアクセスを許可します。 [コンプライアンス ポリシーを使用して、Intune で管理するデバイスのルールを設定する](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started) CDE 環境がグループ ポリシー オブジェクト (GPO) に依存している場合は、アイドル タイムアウトを設定するように GPO を構成します。 Microsoft Entra ID を構成して、ハイブリッド参加済みデバイスからのアクセスを許可します。 [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join) |

### 8.3 ユーザーと管理者の強力な認証が確立され、管理されている。

PCI 要件を満たすMicrosoft Entra認証方法の詳細については、「[Information Supplement: Multi-Factor Authentication](https://learn.microsoft.com/ja-jp/entra/standards/pci-dss-mfa)」を参照してください。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entraガイダンスと推奨事項 |
| --- | --- |
| **8.3.1** ユーザーおよび管理者のシステム コンポーネントへのすべてのユーザー アクセスは、次の認証要素のうち少なくとも 1 つを使用して認証される。 既知の情報 (パスワードやパスフレーズなど)。  所有物 (トークン デバイスやスマート カード)。  本人であることを示すもの (生体認証要素など)。 | [Microsoft Entra IDでは、PCI 要件を満たすためにパスワードレスの方法が必要です](https://learn.microsoft.com/ja-jp/entra/standards/pci-dss-mfa) 包括的なパスワードレス展開を参照してください。  Microsoft Entra ID |
| **8.3.2** すべてのシステム コンポーネントで、転送中と保存中にすべての認証要素を読み取り不可能にするために、強力な暗号化が使用されている。 | Microsoft Entra IDによって使用される暗号化は、[強力な暗号化](https://www.pcisecuritystandards.org/glossary/#glossary-s)のPCI 定義に準拠しています。 [Microsoft Entra データ保護に関する考慮事項](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-protection-considerations) |
| **8.3.3** 認証要素を変更する前に、ユーザー ID が検証される。 | Microsoft Entra IDでは、mysecurityinfo ポータルやセルフサービス パスワード リセット (SSPR) ポータルなどのセルフサービスを使用して認証方法を更新するための認証が必要です。 [サインイン ページからセキュリティ情報を設定します](https://support.microsoft.com/en-us/topic/28180870-c256-4ebf-8bd7-5335571bf9a8)[一般的な条件付きアクセス ポリシー: セキュリティ情報登録の保護](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)[Microsoft Entra のセルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks) 管理者は、特権ロールを持っている場合、認証要素を変更できます: グローバル、パスワード、ユーザー、認証、および特権認証。 [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task) でのタスクごとの最小特権ロール。 microsoft では、Microsoft Entra Privileged Identity Management |
| **8.3.4** 次の基準で無効な認証試行が制限されている:  10 回以内の試行後にユーザー ID をロックアウトする。  ロックアウト期間を 30 分以上または "ユーザーの ID が確認されるまで" に設定する。 | ハードウェアのトラステッド プラットフォーム モジュール (TPM) 2.0 以降をサポートするWindows デバイスのWindows Hello for Businessを展開します。  Windows Hello for Businessの場合、ロックアウトはデバイスに関連します。 ジェスチャ、PIN、または生体認証によって、ローカル TPM へのアクセスがロック解除されます。 管理者は、GPO または Intune ポリシーを使用してロックアウト動作を構成します。 [TPM グループ ポリシー設定](https://learn.microsoft.com/ja-jp/windows/security/hardware-security/tpm/trusted-platform-module-services-group-policy-settings)[デバイスが Intune に登録される時点で Windows Hello for Business をデバイス上で管理](https://learn.microsoft.com/ja-jp/mem/intune/protect/windows-hello)[TPM の基礎](https://learn.microsoft.com/ja-jp/windows/security/hardware-security/tpm/tpm-fundamentals)Windows Hello for Business は、オンプレミスの Active Directory 認証および Microsoft Entra ID 上のクラウド リソースへのアクセスで機能します。  FIDO2 セキュリティ キーの場合、ブルートフォース保護はキーに関連しています。 ジェスチャ、PIN、または生体認証によって、ローカル キー ストレージへのアクセスがロック解除されます。 管理者は、PCI 要件に適合する製造元からの FIDO2 セキュリティ キーの登録を許可するようにMicrosoft Entra IDを構成します。 [パスワードレス セキュリティ キーサインインの有効化](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)**Microsoft Authenticator App** Microsoft Authenticator アプリのパスワードレス サインインを使用したブルート フォース攻撃を軽減するには、番号照合などを有効にします。  Microsoft Entra ID は、認証フローで乱数を生成します。 ユーザーが認証アプリでそれを入力します。 モバイル アプリの認証プロンプトに、場所、要求の IP アドレス、要求アプリケーションが表示されます。 [MFA 通知で番号照合を使用する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match)[Microsoft Authenticator通知で追加のコンテキストを使用する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-additional-context) |
| **8.3.5** 要件 8.3.1 を満たすためにパスワード/パスフレーズを認証要素として使用する場合、それらは次のようにユーザーごとに設定およびリセットされる:  初回使用時とリセット時に一意の値に設定される。  初回使用の直後に変更を強制される。 | Microsoft Entra IDには適用されません。 |
| **8.3.6** 要件 8.3.1 を満たすためにパスワード/パスフレーズを認証要素として使用する場合、次の最小レベルの複雑さを満たしている:  最小長は 12 文字 (または、システムで 12 文字がサポートされていない場合、最小長は 8 文字)。  数字と英文字の両方が含まれている。 | Microsoft Entra IDには適用されません。 |
| **8.3.7** ユーザーは、最近使用した 4 つのパスワード/パスフレーズのいずれかと同じである新しいパスワード/パスフレーズの送信を許可されない。 | Microsoft Entra IDには適用されません。 |
| **8.3.8** 以下を含む認証のポリシーと手順が文書化され、すべてのユーザーに通知される:  強力な認証要素の選択に関するガイダンス。  ユーザーが自分の認証要素を保護する方法に関するガイダンス。  前に使用したことがあるパスワード/パスフレーズを再利用しないことの指示。  パスワード/パスフレーズが侵害された疑いがあるか侵害が確認された場合に、パスワード/パスフレーズを変更する手順と、インシデントを報告する方法。 | この要件に従ってポリシーと手順を文書化し、ユーザーに通知します。 Microsoft から、カスタマイズ可能なテンプレートが[ダウンロード センター](https://www.microsoft.com/en-us/download/details.aspx?id=57600)で提供されています。 |
| **8.3.9** パスワード/パスフレーズがユーザー アクセスの唯一の認証要素として (つまり、単一要素認証の実装で) 使用される場合、次のいずれかの条件を満たしている: パスワード/パスフレーズが少なくとも 90 日に 1 回変更される  または  アカウントのセキュリティ体制が動的に分析され、それに応じてリソースへのリアルタイム アクセスが自動的に決定される。 | Microsoft Entra IDには適用されません。 |
| **8.3.10***サービス プロバイダー限定の追加要件*: パスワード/パスフレーズが、顧客ユーザーがカード所有者データにアクセスするための唯一の認証要素として (つまり、単一要素認証の実装で) 使用される場合、以下を含むガイダンスが顧客ユーザーに提供される:  ユーザーのパスワード/パスフレーズを定期的に変更するよう顧客に求めるガイダンス。  パスワード/パスフレーズを変更するタイミングと状況に関するガイダンス。 | Microsoft Entra IDには適用されません。 |
| **8.3.10.1** サービス プロバイダー限定の追加要件: パスワード/パスフレーズが顧客ユーザーのアクセスの唯一の認証要素として (つまり、単一要素認証の実装で) 使用される場合、次のいずれかの条件を満たしている:  パスワード/パスフレーズが少なくとも 90 日に 1 回変更される  または  アカウントのセキュリティ体制が動的に分析され、それに応じてリソースへのリアルタイム アクセスが自動的に決定される。 | Microsoft Entra IDには適用されません。 |
| **8.3.11** 物理また論理セキュリティ トークン、スマート カード、証明書などの認証要素が使用される場合:  要素は個々のユーザーに割り当てられ、複数のユーザー間で共有されない。  物理/論理コントロールにより、意図したユーザーのみがその要素を使用してアクセスできることが保証される。 | 電話でのサインインには、Windows Hello for Business、FIDO2 セキュリティ キー、Microsoft Authenticator アプリなどのパスワードレス認証方法を使用します。 ユーザーに関連付けられた公開または秘密キーペアに基づくスマート カードを使用して再利用を防ぎます。 |

### 8.4 カード所有者データ環境 (CDE) へのアクセスをセキュリティで保護するために、多要素認証 (MFA) が実装されている

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entraガイダンスと推奨事項 |
| --- | --- |
| **8.4.1** 管理アクセス権を持つ担当者がコンソール以外から CDE にアクセスする場合に、すべてのアクセスに対して MFA が実装されている。 | 条件付きアクセスを使用して、CDE リソースへのアクセスに強力な認証を要求します。 [管理ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)、またはアプリケーションへの管理アクセスを表すセキュリティ グループを対象とするポリシーを定義します。  管理アクセスの場合は、Microsoft Entra Privileged Identity Management (PIM) を使用して、特権ロールの Just-In-Time (JIT) アクティブ化を有効にします。 [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)[条件付きアクセス テンプレート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)[PIM の使用を開始する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started) |
| **8.4.2** CDE へのすべてのアクセスに対して MFA が実装されている。 | 強力な認証をサポートしていないレガシ プロトコルへのアクセスをブロックします。 [条件付きアクセスを使用したMicrosoft Entra IDによるレガシ認証のブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication) |
| **8.4.3** エンティティのネットワークの外部から発信され、CDE にアクセスするか、CDE に影響を与える可能性がある、次のようなリモート ネットワーク アクセスのすべてに対して MFA が実装されている:  エンティティのネットワークの外部から発信される、すべての担当者 (ユーザーと管理者の両方) によるすべてのリモート アクセス。  サード パーティとベンダーによるすべてのリモート アクセス。 | 仮想プライベート ネットワーク (VPN)、リモート デスクトップ、ネットワーク アクセス ポイントなどのアクセス テクノロジを、認証と承認のためのMicrosoft Entra IDと統合します。 条件付きアクセスを使用して、リモート アクセス アプリケーションへのアクセスに強力な認証を要求します。 [条件付きアクセス テンプレート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common) |

### 8.5 多要素認証 (MFA) システムが誤用を防止するように構成されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entraガイダンスと推奨事項 |
| --- | --- |
| **8.5.1** MFA システムが次のように実装されている:  MFA システムはリプレイ攻撃の影響を受けない。  MFA システムは、具体的に文書化され、管理者によって例外ベースかつ期間限定で認可されている場合を除き、管理ユーザーを含むどのユーザーによってもバイパスできない。  少なくとも 2 種類の認証要素が使用されている。  アクセスが許可される前に、すべての認証要素の成功が必要である。 | 推奨されるMicrosoft Entra認証方法では、nonce またはチャレンジを使用します。 Microsoft Entra IDは再生された認証トランザクションを検出するため、これらの方法はリプレイ攻撃に抵抗します。 パスワードレス電話サインイン用の  Windows Hello for Business、FIDO2、Microsoft Authenticator アプリでは、nonce を使用して要求を識別し、再生試行を検出します。 CDE でユーザーが使用できるパスワードレスの資格情報を利用します。  証明書ベースの認証では、チャレンジを使用してリプレイ試行を検出します。 [Microsoft Entra ID と NIST 認証者保証レベル 2](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-2)[Microsoft Entra ID を使用した NIST 認証者保証レベル 3](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-3) |

### 8.6 アプリケーション アカウントとシステム アカウント、および関連する認証要素の使用が厳密に管理されている。

| PCI-DSS で定義されたアプローチの要件 | Microsoft Entraガイダンスと推奨事項 |
| --- | --- |
| **8.6.1** システムまたはアプリケーションで使用されるアカウントを対話型ログインに使用できる場合、アカウントが次のように管理されている:  例外的な状況で必要な場合を除き、対話型の使用は防止される。  対話型の使用は、例外的な状況で必要な時間に限定されている。  対話型の使用について、業務上の正当な理由が文書化されている。  対話型の使用は管理者によって明示的に承認される。  アカウントへのアクセスが許可される前に、個々のユーザー ID が確認される。  実行されるすべてのアクションは、個々のユーザーに起因します。 | 先進認証を使用する CDE アプリケーションと、先進認証を使用するAzureにデプロイされた CDE リソースの場合、Microsoft Entra IDには、マネージド ID とサービス プリンシパルという 2 種類のサービス アカウントがあります。  Microsoft Entra サービス アカウントのガバナンスについて説明します。計画、プロビジョニング、ライフサイクル、監視、アクセス レビューなど[Microsoft Entra サービス アカウントの管理](https://learn.microsoft.com/ja-jp/entra/architecture/govern-service-accounts) Microsoft Entra サービス アカウントをセキュリティで保護します。 [Microsoft Entra ID におけるマネージド ID のセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-managed-identities)[Microsoft Entra ID におけるサービス プリンシパルのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-principal) Azure 外部のリソースにアクセスを必要とする CDEs の場合、シークレットや対話型サインインを管理することなく、ワークロード ID フェデレーションを構成します。 [Workload ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation) 要件を満たす承認と追跡プロセスを有効にするには、IT Service Management (ITSM) と構成管理データベース (CMDB) を使用してワークフローを調整します。これらのツールでは、MS Graph APIを使用して、Microsoft Entra IDと対話し、サービス アカウントを管理します。  on-premises Active Directoryと互換性のあるサービス アカウントを必要とする CDN の場合は、グループ管理サービス アカウント (GMSA)、スタンドアロンマネージド サービス アカウント (sMSA)、コンピューター アカウント、またはユーザー アカウントを使用します。 [オンプレミスのサービス アカウントのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-on-premises) |
| **8.6.2** 対話型ログインに使用できるアプリケーションおよびシステム アカウントのパスワード/パスフレーズが、スクリプト、構成/プロパティ ファイル、または、特注およびカスタムのソース コードにハードコーディングされていない。 | パスワードを必要としないAzureマネージド ID やサービス プリンシパルなどの最新のサービス アカウントを使用します。  Microsoft Entra マネージド ID 資格情報がプロビジョニングされ、クラウドでローテーションされます。これにより、パスワードやパスフレーズなどの共有シークレットを使用できなくなります。 システム割り当てマネージド ID を使用する場合、ライフサイクルは基になる Azure リソース のライフサイクルに関連付けられます。  サービス プリンシパルを使用して証明書を資格情報として使用します。これにより、パスワードやパスフレーズなどの共有シークレットの使用を防ぎます。 証明書が実現できない場合は、Azure Key Vaultを使用してサービス プリンシパル クライアント シークレットを格納します。 [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/general/best-practices#using-service-principals-with-key-vault) アクセスを必要とするAzure外のリソースを含む CDEs の場合は、シークレットや対話型サインインを管理せずにワークロード ID フェデレーションを構成します。 [ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation) ワークロード ID の条件付きアクセスをデプロイし、場所やリスク レベルに基づいて認可を制御します。 [ワークロード ID 用の条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity) 前のガイダンスに加えて、コード分析ツールを使用して、コードと構成ファイル内のハードコーディングされたシークレットを検出します。 [コード内で公開されているシークレットを検出する](https://learn.microsoft.com/ja-jp/azure/defender-for-cloud/detect-exposed-secrets)[セキュリティ規則](https://learn.microsoft.com/ja-jp/dotnet/fundamentals/code-analysis/quality-rules/security-warnings) |
| **8.6.3** アプリケーションおよびシステム アカウントのパスワード/パスフレーズが、次のように誤用や悪用から保護されている:  パスワード/パスフレーズが定期的に (要件 12.3.1 で指定されたすべての要素に従って実行される、エンティティの対象指定リスク分析で定義された頻度で) 変更される。  パスワード/パスフレーズは、エンティティがパスワード/パスフレーズを変更する頻度に適した十分な複雑さで構築されている。 | パスワードを必要としないAzureマネージド ID やサービス プリンシパルなどの最新のサービス アカウントを使用します。  シークレットを含むサービス プリンシパルを必要とする例外の場合は、サービス プリンシパルにランダムなパスワードを設定し、パスワードを定期的にローテーションし、リスク イベントに対応するワークフローと自動化を使用して、シークレット ライフサイクルを抽象化します。  セキュリティ運用チームは、リスクの高いワークロード ID などのMicrosoft Entraによって生成されたレポートを確認および修復できます。 Microsoft Entra ID Protection を使用したワークロード ID のセキュリティ保護 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/standards/standards-overview"} -->
## Microsoft Entra ID 標準の概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/standards/standards-overview
- Service: entra / standards
- Article date: 2022-12-07
- Summary: ID 管理の政府機関および業界標準を満たすように Microsoft Entra ID を構成できます。

相互接続されたインフラストラクチャの今日の世界では、多くの場合、政府や業界のフレームワークと標準への準拠が必須です。 Microsoft は、Azure のコンプライアンス要件を理解し、満たすために、政府、規制機関、および標準機関と連携しています。 [90 個の Azure コンプライアンス認定があり](https://learn.microsoft.com/ja-jp/azure/compliance/)、これにはさまざまな国/地域の多くが含まれています。 Azure には、次のような主要な業界向けに 35 のコンプライアンス オファリングがあります。

- 健康
- 政府
- ファイナンス
- Education
- 製造
- メディア

### Azure コンプライアンスは有利なスタートです

コンプライアンスは、Microsoft、クラウド サービス プロバイダー (CSP)、および組織の共同責任です。 Azure コンプライアンス認定をコンプライアンスの基礎として使用し、ID 標準を満たすように Microsoft Entra ID を構成します。

CSP、政府機関、および CSP と協力するユーザーは、次のような 1 つ以上の政府標準を満たす必要があります。

- [米国連邦リスクおよび承認管理プログラム (FedRAMP)](https://learn.microsoft.com/ja-jp/azure/compliance/offerings/offering-fedramp)
- [国立標準技術研究所 (NIST)](https://learn.microsoft.com/ja-jp/azure/compliance/offerings/offering-nist-800-53)

医療や金融などの業界の CSP と組織には、次のような基準があります。

- [1996年医療保険の携行性と説明責任に関する法律 (HIPPA)](https://learn.microsoft.com/ja-jp/azure/compliance/offerings/offering-hipaa-us)
- [2002年Sarbanes-Oxley 法(SOX)](https://learn.microsoft.com/ja-jp/compliance/regulatory/offering-sox)

サポートされているコンプライアンス フレームワークの詳細については、 [Azure コンプライアンス オファリング](https://learn.microsoft.com/ja-jp/azure/compliance/offerings/)に関するページを参照してください。
<!-- /MSL-PAGE -->
