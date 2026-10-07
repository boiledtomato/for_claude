# Microsoft Learn — Microsoft Entra / 基礎・アーキテクチャ・標準・その他 (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 55

---

<!-- MSL-PAGE {"url":"entra/architecture/id-protection-guide-investigate"} -->
## リスク テレメトリを調査するための Microsoft Entra ID Protection - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-investigate
- Service: entra-id-protection
- Article date: 2025-10-31
- Summary: Security Operations Center (SOC) 管理者が Microsoft Entra ID Protection を使用して ID リスク関連のテレメトリをセキュリティ調査に取り込む方法について説明します。

この一連の記事の概念実証 (PoC) ガイダンスは、Microsoft Entra ID Protection を学習、展開、テストして、ID ベースのリスクを検出、調査、修復するのに役立ちます。

ガイダンスの概要は、 [Microsoft Entra ID Protection の概念実証ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-introduction)から始まります。

詳細なガイダンスは、次のシナリオで引き続き行われます。

- [リアルタイム リスク検出を使用して保護されたリソースへのアクセスを許可する](https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-detect)
- [効果的な修復のためのリスク分析を習得する](https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-analyze)
- [ユーザーがエンタープライズで管理されるリソースの ID リスクを自己修復できるようにする](https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-remediate)

この記事は、Security Operations Center (SOC) 管理者が ID リスク関連のテレメトリをセキュリティ調査に取り込むのに役立ちます。

Microsoft Entra ID Protection を使用して ID リスク関連テレメトリ用に次の機能を構成します。

- ID ベースのインシデントを調査する
- Microsoft Security Copilot を使用して調査する
- 修復と対応
- 監査とコンプライアンスの確認
- マルチテナント環境でアクセスを構成する

### ID ベースのインシデントを調査する

Microsoft Entra 管理センターまたは Microsoft Graph API を使用して、ID の脅威を検出して調査します。

1. [あり得ない移動](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports#risky-sign-ins)、[匿名 IP、マルウェアにリンクされた IP](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#atypical-travel-detections) などの[危険なサインイン](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#malicious-ip-address-detections)。
2. [資格情報の漏洩](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports#risky-users)や[疑わしい動作](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#leaked-credentials-detections)を持つアカウントなど、危険な[ユーザー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#password-spray-detections)。
3. [トークンの再生](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risk-reports#risk-detections)や未知のサインイン プロパティなどの[リスク検出](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk#anomalous-token-and-token-issuer-anomaly-detections)。

### Microsoft Entra で Microsoft Security Copilot を使用して調査する

Microsoft Entra の Microsoft Security は、人工知能 (AI) と人間の専門知識を組み合わせて、管理者とセキュリティ チームが脅威や攻撃に迅速に対応できるように支援します。 埋め込みエクスペリエンスを通じて、ID リスクの調査と解決、ID の評価、完全な複雑なタスクへの迅速なアクセスを行うことができます。

[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-incident) で自然言語プロンプトを使用する:

- ユーザーが危険である理由を要約します。
- サインイン ログ、監査証跡、およびグループ メンバーシップを取得します。
- 修復に関する推奨事項とドキュメントへのリンクを取得します。

Microsoft Entra の [Microsoft Security Copilot シナリオ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-security-scenarios) の詳細について説明します。

### 修復と対応

脅威を確認した後:

1. アクセスをブロックまたはチャレンジするためにトリガーされた [リスクベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies) を追跡します。
2. [リスクを修復し、ユーザーのブロックを解除するには、](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock#administrator-manual-remediation)侵害を手動で無視または確認します。
3. [セキュリティで保護されたパスワード リセットまたは MFA の再登録を開始](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-user-experience)します。
4. 応答プレイブックを使用して、次の手順をガイドします。

### 監査とコンプライアンスの確認

Microsoft Entra のすべての SOC アクションをログに記録して監査し、次の手順を実行します。

1. Microsoft Entra 管理センターでログを確認します。
2. 関連付けとストレージの場合は、 [Azure Monitor Log Analytics](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-activity-logs-log-analytics)、 [Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview?tabs=defender-portal)、または専用のセキュリティ情報およびイベント管理 (SIEM) システムにログをエクスポートします。
3. ポリシーの変更、ユーザーのブロック解除などの特定のアクションに対するアラートを生成します。

### マルチテナント環境でアクセスを構成する

[マルチテナント環境](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/defender-xdr-microsoft-entra-mto)の場合:

1. [テナント間アクセス ポリシーを構成します](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)。
2. スコープを制限するには、 [ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview) (セキュリティ閲覧者、セキュリティ オペレーターなど) を使用します。
3. [PowerShell または Microsoft Graph API を](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-graph-api)使用してデプロイを自動化します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/id-protection-guide-remediate"} -->
## ユーザー リスクを自己回復するための Microsoft Entra ID Protection - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-remediate
- Service: entra-id-protection
- Article date: 2025-10-31
- Summary: IT 管理者が Microsoft Entra ID Protection を使用して、エンタープライズ管理リソースにアクセスするユーザーの ID リスクを特定して修復する方法について説明します。

この一連の記事の概念実証 (PoC) ガイダンスは、Microsoft Entra ID Protection を学習、展開、テストして、ID ベースのリスクを検出、調査、修復するのに役立ちます。

ガイダンスの概要は、 [Microsoft Entra ID Protection の概念実証ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-introduction)から始まります。

詳細なガイダンスは、次のシナリオで引き続き行われます。

- [リアルタイム リスク検出を使用して保護されたリソースへのアクセスを許可する](https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-detect)
- [効果的な修復のためにリスク分析を習得する](https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-analyze)
- [ID リスク関連のテレメトリをセキュリティ調査に取り込む](https://learn.microsoft.com/ja-jp/entra/architecture/id-protection-guide-investigate)

この記事は、管理者が Microsoft 365 を含むエンタープライズ管理リソースにアクセスするユーザーの ID リスクを特定して修復するのに役立ちます。 リアルタイムとオフラインのリスク検出を使用して、サインインとユーザーの動作を評価します。 多要素認証 (MFA)、パスワードのリセット、リスク レベルに基づくアクセスのブロックなどの自動応答を適用します。 大規模な環境にまたがってスケーリングされるリスクベースの条件付きアクセス ポリシーでは、これらの保護が適用されます。

Microsoft Entra ID Protection を使用してエンタープライズマネージド リソースの ID リスクをユーザーが自己修復できるように、次の機能を構成します。

- リスクのあるサインインの自己回復を設定する
- パスワード リセット保護とセルフサービスを構成する
- 条件付きアクセスを適用する
- アカウントの状態の可視性を設定する
- [インシデントの相関関係と調査のために Microsoft Defender と統合する](https://learn.microsoft.com/ja-jp/defender-xdr/incidents-overview)

### リスクのあるサインインの自己対応を設定する

Microsoft Entra ID Protection に登録する Microsoft 365 ユーザーに、 [危険なサインイン検出](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)時に多要素認証 (MFA) を完了するように求めます。

### パスワード リセット保護とセルフサービスを構成する

パスワードの検疫と自律性が重要な大規模な Microsoft 365 環境では、特にパスワード保護機能を構成します。

1. 脆弱なパスワードまたは禁止されたパスワードをブロックするパスワード [保護ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad) を構成します。
2. ユーザーが複数の認証方法でアクセスを安全に回復できるようにするには、 [セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#user-risk-policy-in-conditional-access) を構成します。
3. [フィッシングに強いパスワードレス認証に移行します。](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)

### 条件付きアクセスを適用する

次の条件に基づいて動的な適用を行うユーザーの条件付きアクセス ポリシーを構成します。

1. 未知の場所やデバイスからの[サインイン リスク](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)。
2. 資格情報の漏洩や疑わしい動作などの[ユーザー リスク](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#user-risk-detections)。

リスク軽減の後まで、ユーザーが自分の ID を確認したり、機密性の高いアプリへのアクセスを制限したりする必要があります。

### アカウント健全性の可視化を設定する

次のシナリオのユーザー アラートと通知を構成します。

1. アカウントでの疑わしいアクティビティ。
2. 再認証やデバイス コンプライアンスなどのアクセスを維持するために必要なアクション。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/locate-integration-partners"} -->
## Microsoft Entra Suite のサービスと統合パートナー - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/locate-integration-partners
- Service: entra
- Article date: 2025-06-18
- Summary: Microsoft Entra の展開に関するガイダンスについては、パートナーを探して関与させます。

パートナーは、次のような [Microsoft Entra Suite での Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra) シナリオの計画と展開を組織で支援できます。

- [Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)
- [Microsoft グローバル セキュリティで保護されたアクセス](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)
- [Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)
- [Microsoft Entra Verified ID](https://learn.microsoft.com/ja-jp/entra/verified-id/decentralized-identifier-overview)

お客様は、 [Microsoft ソリューション パートナー ファインダーでパートナー](https://partner.microsoft.com/partnership/find-a-partner)と連携することも、次の表のサービス パートナーから選択することもできます。 連絡するパートナーを特定し、サービスの詳細を確認します。 説明とリンクはパートナーが提供します。

| パートナー | 説明 |
| --- | --- |
| Accenture | Accenture の Microsoft Entra Suite オファリングは、優れたグローバルデリバリー機能、高度な技術的専門知識、実績のある能力によって区別されています。 Microsoft の 19 回のグローバル システム インテグレーター パートナー オブ ザ イヤー賞を受賞した Accenture は、最先端のソリューションとシームレスな統合を提供し、あらゆるエンタープライズ要件の第一線のパートナーとして位置付けます。 |
| [Armis](https://www.armisgroup.com/services/it-services/security-identity/) | Armis は、20 年以上にわたるデジタル変革の専門知識を適用し、信頼できる Microsoft パートナーとしてグローバルな技術ソリューションを提供しています。 セキュリティ、データ、AI、ビジネス アプリケーションを専門とし、効率性と持続可能な成長を促進する革新的なソリューションを提供します。 Armis は、Microsoft Entra Suite の包括的な機能を使用して、ゼロ トラストの原則を採用する企業をサポートします。 この取り組みにより、セキュリティ体制が強化され、回復力のある将来対応のインフラストラクチャが可能になります。 |
| [Ascent Solutions](https://www.meetascent.com/services/cybersecurity/identity-management) | Ascent Solutions は、高度な ID の専門知識と、調整されたデプロイ、ガバナンス、保護サービスを組み合わせることで、セキュリティで保護された Microsoft Entra の導入を加速します。 Microsoft Entra を最適化して、セキュリティで保護されたシングル サインオン (SSO)、Microsoft Entra 多要素認証 (MFA)、Microsoft Entra 条件付きアクセスを有効にしながら、ID ガバナンスをコンプライアンス目標に合わせて調整します。 Ascent を使用すると、組織はリスクを軽減し、アクセスを簡素化し、Microsoft の投資を完全に有効にすることができます。 |
| [Atea](https://www.atea.se/eshop/campaigns/microsoft-entra-suite) | Atea は、ノルディックおよびバルティック地域で最大の Microsoft パートナーです。 Microsoft は、Microsoft Entra に関するプロフェッショナル なサービスを顧客に提供することに特化しています。 Microsoft のライセンス スペシャリストと認定されたエキスパートにより、Microsoft Entra Suite のコンポーネントからお客様が最大限の価値を得られます。 |
| [アバナード](https://www.avanade.com/en/services/microsoft-tech/microsoft-security/advanced-identity) | Avanade の Microsoft Entra Suite に対する資産主導のアプローチは、反復可能なライフサイクル ワークフローと Microsoft Entra Verified ID の高度な資産を備えた堅牢な Microsoft Entra ID ガバナンス コンテンツ ライブラリを利用します。 また、Security Service Edge (SSE) の高速手法も備え、レガシ ソリューションからの導入と移行を合理化します。 この独特の戦略により、セキュリティが強化され、運用効率が向上し、変換が高速化されます。 |
| [baseVISION AG](https://www.basevision.ch/our-solutions/identity-management-solutions/) | baseVISION は、セキュリティで保護された最新のエンドポイント管理に重点を置いた主要な Microsoft セキュリティ パートナーです。 Microsoft Entra ID ガバナンス サービスを使用すると、企業は世界中の ID およびアクセス管理 (IAM) プロセスをより効率的かつ最新の方法で、エンタープライズ規模で変革できます。 Microsoft は、Microsoft との重点的な会社戦略とパートナーシップにより、優れた知識と実用的な経験を組み合わせています。 |
| [BDO](https://www.bdo.com/services/bdo-digital/cybersecurity) | BDO は、グローバルなサイバーセキュリティ アドバイザーおよびインテグレーターとして、クライアントがリスクを管理し、Microsoft Entra Suite を使用して組織を変革するのに役立ちます。 業界の経験を活かしながら、Microsoft が提供する適切な ID 戦略とガバナンスを実装する変革的なセキュリティとクラウド ソリューションを使用して、お客様が自信を持って成長できるように支援します。 |
| [カンパーナ & ショット](https://www.campana-schott.com/de/en/expertise/strategie/cyber-security-services/identity-access-management) | Campana > Schott は、ヨーロッパと米国で 600 人以上の従業員を持つ国際的な管理およびテクノロジ コンサルタントです。Microsoft は長年にわたる変革の経験と、Microsoft のテクノロジ、IT 戦略、IT 組織に関する深い知識を持っています。 Microsoft Entra Suite を使用した ID とアクセス管理 (IAM) の変革をサポートする理想的なパートナーです。 |
| [Condatis](https://condatis.com/technology/microsoft-entra-suite/) | Condatis は、複雑なグローバル クライアントに Microsoft Entra Suite を使用して実用的な ID ソリューションを提供します。 Condatis は Microsoft と緊密に連携して、強力な IAM の専門知識を実現し、サイバー リスクの削減とコストの最適化に重点を置いています。 Microsoft Entra Suite は、従業員を支援し、デジタル エコシステムを保護し、測定可能なビジネス成果を提供するためのシームレスで安全なアクセスを保証します。 |
| [サイクロトロン](https://www.cyclotron.com/post/streamline-identity-access-management-with-microsofts-entra-suite) | Cyclotron は、SailPoint から Microsoft Entra Suite、Okta から Microsoft Entra Suite への専門的な移行、およびその他のツールセット移行に関する専門知識を提供します。 Microsoft の独自の移行ツールは、展開のタイムラインを大幅に短縮するのに役立ちますが、Change Leadership の専門家はユーザーの変更の負担を軽減し、成功戦略全体を通じて利害関係者とシームレスに関わります。 |
| [DXC テクノロジ](https://dxc.com/content/dam/dxc/projects/dxc-com/us/pdfs/practices/DXC%20Service%20for%20MS%20Entra%20FS.pdf) | 信頼できるグローバル パートナーである DXC Technology は、Microsoft Entra Suite でさまざまなコントロールを有効にすることを楽しみにしています。 当社は、組織が戦略、計画、準備、変革を通じて効率性を向上させ、ゼロトラストモデルを採用するのを支援します。 ID、ネットワーク、デバイス、アプリケーション、ワークロード、データなど、主要なセキュリティ ドメインの運用管理を可能にします。 |
| [エルンスト & ヤング、LLP](https://www.ey.com/en_us/alliances/simplify-your-cybersecurity-with-the-ey-microsoft-alliance) | EYは、大規模な技術を使用し、迅速にイノベーションを推進し、センターの人々とより良い仕事の世界を作り出すプロフェッショナルサービスの信頼できるグローバルリーダーです。 EY-Microsoft Alliance は、Microsoft Entra と革新的な ID 管理ソリューションを共同で行い、企業が ID を保護および管理する方法を変革し、信頼と安全性が最も重要な未来を作り出します。 |
| [glueckkanja AG](https://www.glueckkanja.com/en/workplace/microsoft-entra-suite/) | glueckkanja は、100% クラウド ソリューションと ID 中心のセキュリティに関する専門知識で有名な主要な Microsoft パートナーです。 同社は、ゼロトラスト戦略の一貫した実用的なアプリケーションを使用して、完全にクラウドで管理された職場を実装する確立されたパイオニアです。 サービスは、Microsoft Global Secure Access を含む最新の Microsoft 製品の概念実証など、多岐にわたる形で提供しています。 マネージド Red Tenant などのマネージド サービスに対する ID セキュリティ (MyWorkID など) のソリューションがあります。 glueckkanja は、Microsoft 製品チームと TechCommunity との緊密な接続を通じて、Microsoft テクノロジの最先端でブループリント アプローチを推進します。 いくつかの Microsoft MVP は、セキュリティ組織の分野の専門家として ID とアクセスの作業に特化しています。 |
| [IBM コンサルティング](https://www.ibm.com/services/security) | IBM と Microsoft の戦略的パートナーシップは、高度な技術的専門知識の基盤を持ち、継続的なイノベーションに対する強力で共有されたコミットメントを持っています。 IBM の強力な AskIAM for Microsoft Entra は、最先端の Agentic AI を使用して ID ガバナンスに革命を起こします。 このテクノロジは、複雑なワークフローをシームレスに自動化し、エンタープライズ レベルのセキュリティを大幅に強化し、堅牢な規制コンプライアンスを保証し、運用の生産性を大幅に向上させます。 |
| [増分](https://appsource.microsoft.com/en-us/marketplace/consulting-services/incrementptyltd1637494276783.inc_offer-microsoft_entra_suite_engagement-01) | Increment の Microsoft Entra Suite エンゲージメントは、環境内の Microsoft Entra の幅を示します。 Microsoft Entra ID ガバナンスを使用して従業員のライフサイクルを自動化し、Microsoft Entra Internet Access でセキュリティで保護されたインターネット アクセスを提供する方法を示します。 Microsoft Entra Private Access で真のゼロ トラストのロックを解除し、Microsoft Entra の検証済み ID を使用して次世代の ID 検証エクスペリエンスを作成します。 |
| [InSpark](https://www.inspark.nl/en/solution/security/entra) | InSpark は、Microsoft Entra を使用して ID とネットワーク アクセスを変換します。 Microsoft Entra ID、Microsoft Entra ID Governance、Microsoft Entra ID Protection、Security Service Edge (SSE)、Microsoft Entra Verified ID、および Microsoft Entra External ID を専門としています。 迅速なデプロイのための独自のプロジェクト アクセラレータを提供します。 コンサルティングからマネージド サービスまで、シームレスで安全なイノベーションを実現します。 InSpark と提携し、違いを体験してください。 |
| [呼び出し](https://www.invokellc.com/identity-access-management) | ゼロ トラスト原則に従って、Microsoft 統合セキュリティ運用プラットフォームでのみ Excel を呼び出します。 Identity のパートナー オブ ザ イヤー ファイナリストとして、すべての Microsoft Security パートナー資格を取得した Invoke は、アイデンティティ ソリューションを最良のスイートワークロードと統合します。 Microsoft Entra ID の移行、Microsoft Entra ID Protection、Microsoft Global Secure Access、および Microsoft Entra ID ガバナンスに関する専門知識により、包括的な ID 変換が保護されます。 |
| [日本ビジネスシステム株式会社](https://blog.jbs.co.jp/archive/category/Microsoft%20Entra%20ID) | JBS は、Microsoft Entra と Microsoft 365 の広範な統合経験と専門知識を持ち、初期計画と検証から実装、デプロイ、運用サポートまで、あらゆるフェーズでワンストップ サービスを提供します。 Microsoft との強力なパートナーシップにより、包括的なソリューションを提供する能力がさらに強化されます。 |
| [クロウディネット](https://www.kloudynet.com/services/identity-security/) | クロウディネットは、Microsoft Entra Suite でゼロ トラストアラインコンバージド ID ソリューションを提供します。 当社のKloudIdentityプラットフォームは、アプリのオンボードを高速化し、ユーザーの調整を可能にし、ロードマップ上のエンタープライズリソースプランニング(ERP)統合を使用して、Linux、SQL、およびAS400をサポートします。 私たちは Microsoft Intelligent Security Association (MISA) のメンバーとして、従来の ID ガバナンスと管理 (IGA) の実装よりも、より深い ID の専門知識と迅速な価値の提供を実現します。 |
| [コーチョグループ](https://kocho.co.uk/solutions/identity-governance/) | Kocho は、Microsoft Entra ID ガバナンス、Microsoft Entra Verified ID、Microsoft Entra Private Access を含む Microsoft Entra Suite を使用して、カスタマイズされた評価とサービスを提供します。 MICROSOFT は、多くの業界にわたる英国の組織が、自動化されたユーザー ライフサイクル プロセスと特権アカウント管理を通じて、ID 管理の最新化、アクセスのセキュリティ保護、運用の複雑さの軽減を支援しました。 私たちのIDアーキテクトが率いる無料のビジョンコールで、あなたの旅を始めましょう。 |
| [KPMG](https://kpmg.com/us/en/capabilities-services/advisory-services/cyber-security-services.html) | Microsoft Entra Suite を使用した KPMG のオファリングは、受賞歴のある独自のパワード エンタープライズ手法とアクセラレータに基づいて構築されています。 このアプローチにより、お客様は変革の取り組みを強化し、ゼロ トラスト アーキテクチャを中心に、シームレスで標準ベースのスケーラブルなデプロイを実現できます。 |
| [MajorKey Technologies](https://www.majorkeytech.com/microsoft-entra-id-partner) | Microsoft Elite パートナーである MajorKey は、Microsoft Entra AI 主導のクラウドネイティブ プラットフォームを使用して、ID とアクセス管理の変革を加速します。 統合されたIDおよびアクセス管理 (IAM) 戦略を迅速に実装することで、顧客が停滞している部分的に導入されたシステムを乗り越えるのを支援します。 Microsoft は、高度なセキュリティ機能をシームレスに統合し、コストのかかるレガシの非効率性を排除しながら、将来の ID セキュリティ プログラムをゼロ トラストの原則と共に実現します。 |
| [MSP Corp](https://mspcorp.ca/technologies/microsoft-entra-consulting-services/) | MSP Corp は、セキュリティを強化し、ID 管理を合理化し、統合を確保する Microsoft Entra Suite ソリューションを提供しています。 Microsoft のエキスパート チームは、Microsoft Entra Private Access をデプロイし、仮想プライベート ネットワーク (VPN)、Face Check を Microsoft Entra Verified ID に置き換え、ユーザーがデジタル資格情報に対する顔認識を通じて ID を検証し、安全なアクセスを確保できるようにします。 |
| [Objektkultur Software GmbH](https://objektkultur.de/technologie/microsoft-entra/) | 長年の Microsoft パートナーである Objektkultur は、革新的な IT ソリューションのコンサルティング、開発、運用を通じてビジネス プロセスをデジタル化します。 Microsoft テクノロジでの ID とアクセス管理 (IAM) とアプリケーション統合に関する深い知識を持つ Microsoft は、組織がデジタル ID を安全に管理し、ハイブリッド環境とマルチクラウド環境間でアクセスできるように支援します。 Microsoft Entra Suite を使用することで、企業はゼロ トラスト戦略を強化し、最小限の特権アクセスを確保し、スケーラブルな ID ガバナンスを実装できます。 |
| [オックスフォード・コンピューター・グループ、メジャーキー・テクノロジーズ社](https://oxfordcomputergroup.com/microsoft-entra-suite/) | OCG には、Microsoft Entra Suite と重要な基幹業務アプリの統合に関する深い経験があり、資格情報の合理化、プロセスの自動化、アクセスと ID の管理に役立ちます。 OCG Identity Exchange は Microsoft Azure の基礎上に構築され、Microsoft Entra Suite を拡張することで、実際の ID の課題を解決するために、独自のロジック、ブック、AI で強化されています。 |
| [パラマウント](https://paramountassure.com/microsoft-entra/) | パラマウント コンピューター システムは、20 年以上にわたる ID およびアクセス管理 (IAM) の専門知識に支えられ、中東地域のサイバーセキュリティ リーダーです。 パラマウントは、Microsoft Entra Suite を搭載した、パラマウント ID 360 などの高度な ID ソリューションを提供するために、Microsoft と戦略的に提携しています。 IAM の主題の専門家は、成功した ID ガバナンス、アクセス管理、アクセスの再認定、検証済み ID、グローバルなセキュリティで保護されたアクセスを実装しました。 この業務は、企業、中小企業 (SMC)、中小規模企業 (SMB) のお客様向けでした。 Microsoft のチームは、カスタム開発と複雑なレガシ環境の統合に優れており、シームレスな ID モダン化の取り組みを保証します。 |
| [Protiviti](https://www.protiviti.com/us-en/microsoft-consulting-solutions) | Protiviti は、Microsoft Entra Suite の強力な機能を通じてデジタル ID 管理を最新化するための革新的なソリューションを提供します。 Microsoft のカスタマイズされたソリューションは、オンプレミスの ID、アプリケーション、認証、デバイスを Microsoft Entra に移行する ID モダン化の取り組みを支援します。 Microsoft は、リスクベースの Microsoft Entra 条件付きアクセスや Microsoft Global Secure Access などの最先端のテクノロジを通じて、堅牢なアクセス セキュリティとガバナンスを適用します。 |
| [PwC](https://www.pwc.com/us/en/technology/alliances/microsoft/cybersecurity.html) | PwC の差別化されたアプローチは、ID セキュリティを強化し、ゼロ トラスト フレームワークをサポートするために、戦略的な計画、展開、およびマネージド サービスにカスタム アクセラレータを使用して配信を調整します。 Microsoft Entra Suite を実装するためのエンドツーエンドの戦略的および技術的なサービスを提供します。 このオファリングには、Microsoft Entra ID Protection、Microsoft Entra ID Governance、Microsoft Entra Verified ID、ワークロード、Security Service Edge (SSE) が含まれます。 |
| [Quorum](https://quorumsystems.com.au/our-capabilities/modern-work/microsoft-entra/) | Quorum は、オーストラリアとニュージーランドの各リージョンで働く、高度に専門化された Microsoft パートナーです。 クォーラムは、Microsoft Entra Suite 全体で Microsoft の深い専門知識を持っています。 このスコープには、Microsoft Entra ID Protection、Microsoft Entra ID Governance、Microsoft Global Secure Access、および Microsoft Entra Verified ID が含まれます。 私たちは、組織が複雑でシンプルな優れた人々と技術を通じて繁栄することを可能にします。 |
| [レイヴンズウッド テクノロジー グループ](https://www.ravenswoodtechnology.com/services/identity-and-access-solutions/microsoft-entra-suite-overview/) | レイヴンズウッドは、何十年ものアイデンティティの専門知識をあなたのイニシアチブにもたらします。 Microsoft のチームは、オンプレミスとクラウドの両方で複雑なエンタープライズ システムを統合することに優れています。 レイヴンズウッドは、最も知名高いブランド名を含む世界中の組織が、困難な ID、セキュリティ、コンプライアンスの課題に取り組むのに役立ちます。 |
| [SBテクノロジー株式会社](https://www.softbanktech.co.jp/service/list/microsoft365/e5-security) | SBテクノロジーのサービスを介してEntra Suiteを統合すると、高度なサイバー脅威から保護する堅牢なセキュリティフレームワークが提供されます。 主な機能には、多要素認証 (MFA)、ID 保護、リスクベースの条件付きアクセスが含まれており、組織のニーズに合わせた包括的なセキュリティ管理が保証されます。 |
| [SHI International](https://www.shi.com/industries/smb/microsoft-solutions) | SHI は、ハイブリッドなマルチクラウド現実向けに専用に構築された、エンドツーエンドの Microsoft Entra Suite デプロイを提供します。 ゼロ トラスト インターネットと Microsoft Entra Private Access を設計しています。 仮想プライベート ネットワーク (VPN) を排除し、ID ガバナンスを統合し、ライフサイクル管理を自動化し、セキュリティを強化します。 組織の価値と機敏性を加速させる、現場で実証済みの計画、発見、設計を通じて実行されるアダプティブ リスク ポリシーを実現します。 |
| [SITS](https://sits.com/en/microsoft/ms-iam-modernization/) | SITS は、ドイツ、オーストリア、スイス (DACH) およびベルギー、オランダ、ルクセンブルク (ベネルクス) 地域の主要なサイバーセキュリティ プロバイダーです。 Microsoft は、最上位レベルのコンサルティング、エンジニアリング、クラウド テクノロジ、マネージド サービスでお客様をサポートします。 Microsoft は、セキュリティ、最新の作業、Microsoft Azure インフラストラクチャの Microsoft ソリューション パートナーです。 Microsoft Entra Suite を使用して、セキュリティ、コンプライアンス、ID、プライバシーに関するコンサルティング、実装、サポート、マネージド サービスを提供しています。 |
| [スラローム](https://www.slalom.com/us/en/who-we-are/partners/microsoft) | Slalom の Microsoft セキュリティに対する人第一のアプローチは、クライアントのチームの権限付与と教育を重視しています。 セキュリティに対応した文化を構築し、セキュリティを維持する上で誰もが自分の役割を理解できるようにトレーニングとリソースを提供することに重点を置きます。 この共同作業は、組織が進化する脅威とテクノロジに効果的に適応するのに役立ちます。 |
| [Tata Consultancy Services](https://www.tcs.com/what-we-do/services/cybersecurity) | TCS は、Microsoft Entra スイートを使用して、企業が ID とアクセス管理 (IAM) の課題と ID 境界リスクを解決するのを支援します。 機敏性、自動化、分析を使用して、最小限の特権アクセス制御を適用し、独自のビジネス ニーズを満たすようにソリューションをカスタマイズします。 当社は、価値の市場化を加速し、ロールアウト時間を短縮し、IAM 制御の対象範囲を迅速に拡張します。 この取り組みにより、シングル サインオン (SSO) とパスワードレス認証によるユーザー エクスペリエンスが向上します。 サードパーティの IAM ソリューションは、特権アクセス管理 (PAM) など、完全な IAM 制御カバレッジを提供します。 Microsoft は、お客様の現在の Microsoft Azure 投資を使用して、総保有コストを削減し、収益を最大化します。 |
| [TOSYS Corp.](https://live-style.jp/managed-security/) | TOSYS は、Security Service Edge (SSE) 実装用のコスト効率の高いパッケージ サービスを提供し、中小企業の多くのデプロイの実績があります。 クイック アクセス アプリケーションに加えて、Microsoft Global Secure Access およびインターネット アクセス ソリューションの展開に関する広範な専門知識も備えています。 |
| [water IT Security GmbH](https://www.water-security.de/blog/techblog/?tag=entraid) | water は、包括的なサイバーセキュリティを提供する、受賞歴のあるマネージド セキュリティ サービス プロバイダー (MSSP) です。 マネージド サービス ポートフォリオ以外にも、Microsoft のコンサルティング部門は、Microsoft Entra Suite のエキスパートガイダンスを提供し、カスタマイズされた ID とアクセス戦略を提供します。 Microsoft [Entra Private Access の成功事例](https://www.water-security.de/cases/schmitz-cargobull-spain/)に例示されているように、お客様は Microsoft のエンジニアリング チームと緊密に連携し、包括的な経験を得られます。 |
| [Wipro - Edgile](https://www.wipro.com/cybersecurity/microsoft-security/) | Wipro は Microsoft Entra で強力な経験を持ち、技術的な深さと ID とアクセスのニーズの実用的な理解を組み合わせています。 Microsoft は、各組織の環境に合わせて調整された、セキュリティで保護されたスケーラブルなソリューションに重点を置きます。 このアプローチは、ガバナンス、リスクの削減、運用効率を重視し、クライアントがクラウドおよびハイブリッド インフラストラクチャ全体で複雑な ID の課題を解決するのに役立ちます。 |
| [ワーテル](https://www.wortell.nl/nl/versterk-de-toegang-tot-al-jouw-online-diensten-met-de-uitgebreide-beveiliging-van-microsoft-entra-suite) | ワーテルは、Microsoft Most Valuable Professional (MVP) を含むセキュリティ チームの知識で世界的に認められた Microsoft セキュリティ パートナーです。 Microsoft Entra Suite を使用して、比類のない保護を提供します。 サービスは、Microsoft Entra ID、Microsoft Entra ID Governance、Microsoft Entra ID Protection、Global Secure Access、および Microsoft Entra Verified ID にまたがる。 ハイブリッドおよびマルチクラウド環境全体でシームレスな ID とアクセス管理を使用して、ゼロ トラストのセキュリティ プラクティスを確保します。 |
| ZerosIT | 強力な機能、最先端の ID ガバナンス、AI 主導の脅威検出、堅牢な多要素認証 (MFA)、きめ細かなアクセス制御を展開する比類のない専門知識。 ゼロ トラスト フレームワークを使用してハイブリッド環境を積極的にセキュリティで保護し、正確に実装します。 セキュリティ、効率性、コンプライアンスをシームレスに向上させます。 Microsoft Entra ID のスケーラブルで将来の可能性を最大限に高める信頼スペシャリスト。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/migration-best-practices"} -->
## アプリケーションと認証を Microsoft Entra ID に移行するためのベスト プラクティス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/migration-best-practices
- Service: entra / architecture
- Article date: 2023-02-14
- Summary: Active Directory フェデレーション サービス (AD FS) を Microsoft Entra ID のクラウド認証に移行するためのベスト プラクティスについて説明します。

[Azure Active Directory が Microsoft Entra ID に変わった](https://www.microsoft.com/security/business/identity-access/microsoft-entra-id)と聞き、今が Active Directory フェデレーション サービス (AD FS) を [Microsoft Entra ID のクラウド認証 (AuthN)](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/migrate-from-federation-to-cloud-authentication) に移行する良い機会であると考えている方もいらっしゃるでしょう。 オプションを確認する際は、[アプリを Microsoft Entra ID に移行するための広範なリソース](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migration-resources)とベスト プラクティスを参照してください。

AD FS を Microsoft Entra ID に移行できるようになるまでは、[Microsoft Defender for Identity](https://learn.microsoft.com/ja-jp/defender-for-identity/what-is) を使ってオンプレミスのリソースを保護してください。 脆弱性を事前に見つけて修正することができます。 評価、分析とデータ インテリジェンス、ユーザー調査の優先順位スコア付け、侵害された ID への自動対応を使います。 クラウドへの移行は、認証にパスワードレスの方法など、組織が先進認証のメリットを得られることを意味します。

次のような理由で、AD FS を移行しないことを選ぶ場合があります。

- 環境内のユーザー名 (従業員 ID など) がフラットである。
- カスタム多要素認証プロバイダーのために他にも必要なオプションがある。
- VMware など、サードパーティのモバイル デバイス管理 (MDM) システムのデバイス認証ソリューションを使っている。
- AD FS に複数のクラウドから二重にフィードされている。
- ネットワークをエア ギャップで隔離する必要がある。

### アプリケーションの移行

段階的なアプリケーション移行のロールアウトを計画し、Microsoft Entra ID の認証を受けるテスト対象のユーザーを選びます。 「[Microsoft Entra ID へのアプリケーションの移行を計画する](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migrate-adfs-apps-phases-overview)」と、[Microsoft Entra ID にアプリを移行するリソース](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migration-resources)のページのガイダンスを参考にしてください。

詳細については、次のビデオ「Microsoft Entra ID を使用した簡単なアプリケーションの移行」を参照してください。

#### アプリケーション移行ツール

[AD FS アプリを Microsoft Entra ID に移行する AD FS アプリケーション移行](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-ad-fs-application-howto) (現在プレビュー段階です) は、IT 管理者が AD FS 証明書利用者アプリケーションを AD FS から Microsoft Entra ID に移行するためのガイドです。 AD FS アプリケーション移行ウィザードは、新しい Microsoft Entra アプリケーションの検出、評価、構成を行うための統合エクスペリエンスを提供します。 基本的な SAML URL、要求のマッピング、ユーザー割り当てをワンクリックで構成して、アプリケーションと Microsoft Entra ID を統合できます。 オンプレミス AD FS アプリケーションの移行をエンドツーエンドでサポートしており、次の機能を備えています。

- アプリケーションの使用状況と影響を特定するには、AD FS 証明書利用者アプリケーションのサインイン アクティビティを評価します
- 移行の阻害要因と、アプリケーションを Microsoft Entra ID に移行するために必要なアクションを特定するには、AD FS から Microsoft Entra への移行の可能性について分析します
- AD FS アプリケーション用に新しい Microsoft Entra アプリケーションを自動的に構成するには、ワンクリック アプリケーション移行プロセスで新しい Microsoft Entra アプリケーションを構成します

#### フェーズ 1:アプリを検出してスコープを設定する

[アプリの検出](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migrate-adfs-discover-scope-apps)時には、開発中と計画段階のアプリを含めます。 移行の完了後に Microsoft Entra ID を使って認証するようにスコープを指定します。

[アクティビティ レポートを使って AD FS アプリを Microsoft Entra ID に移行します](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migrate-adfs-application-activity)。 このレポートは、Microsoft Entra ID に移行できるアプリケーションを特定するのに役立ちます。 AD FS アプリケーションの Microsoft Entra との互換性を評価し、問題を確認し、個々のアプリケーションの移行準備に関するガイダンスを提供します。

[AD FS から Microsoft Entra アプリへの移行ツール](https://github.com/AzureAD/Deployment-Plans/tree/master/ADFS%20to%20AzureAD%20App%20Migration)を使って、AD FS サーバーから証明書利用者アプリケーションを収集し、構成を分析します。 この分析から、どのアプリが Microsoft Entra ID への移行の対象となるかがレポートに表示されます。 対象外のアプリについては、移行できない理由の説明を確認できます。

[Microsoft Entra Connect AD FS 正常性エージェント](https://www.microsoft.com/download/details.aspx?id=47594)を使用して、オンプレミス環境用に [Microsoft Entra Connect](https://go.microsoft.com/fwlink/?LinkID=518973) をインストールしてください。

詳細については、「[Microsoft Entra Connect とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect)」を参照してください。

#### フェーズ 2:アプリを分類し、パイロットを計画する

[アプリの移行を分類すること](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migrate-adfs-classify-apps-plan-pilot)は、重要な演習です。 アプリを移行する順序を決めたら、段階的なアプリの移行と切り替えを計画します。 アプリの分類には次の条件を含めます。

- 最新の認証プロトコル。[Microsoft Entra アプリケーション ギャラリー](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/overview-application-gallery)にある、Microsoft Entra ID を使用しないサードパーティのサービスとしてのソフトウェア (SaaS) アプリなど。
- 最新化するレガシ認証プロトコル。たとえば、サードパーティの SaaS アプリ (ギャラリーにはなく、ギャラリーに追加できるもの)。
- AD FS を使っており、Microsoft Entra ID に移行できるフェデレーション アプリ。
- レガシ認証プロトコルは最新化されません。 最新化により、[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/azure/active-directory/app-proxy/application-proxy-configure-single-sign-on-with-kcd)を使用できる Web アプリケーション プロキシ (WinSCP (WAP)) の背後にあるプロトコルと、[安全なハイブリッド アクセス](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/secure-hybrid-access)を使用できるアプリケーション配信ネットワーク/アプリケーション配信コントローラーが除外される場合があります。
- 新しい基幹業務 (LOB) アプリ。
- 非推奨のアプリには、他のシステムと重複する機能があり、ビジネスの所有者または用途がない場合があります。

移行する最初のアプリを選ぶ場合は、シンプルにします。 条件には、複数の ID プロバイダー (IdP) 接続をサポートする SaaS アプリをギャラリーに含めることができます。 テストインスタンス アクセスを備えたアプリ、単純な要求規則があるアプリ、対象ユーザーのアクセスを制御するアプリがあります。

#### フェーズ 3: 移行とテストを計画する

[移行およびテスト ツール](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migrate-adfs-plan-migration-test)には、アプリを検出、分類、移行するための [Microsoft Entra アプリ移行ツールキット](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migration-resources)が含まれています。 エンドツーエンドのプロセスを説明する [SaaS アプリのチュートリアル](https://learn.microsoft.com/ja-jp/azure/active-directory/saas-apps/tutorial-list)と [Microsoft Entra シングル サインオン (SSO) の展開プラン](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/plan-sso-deployment)の一覧を参照してください。

[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/)について確認し、完全な [Microsoft Entra アプリケーション プロキシの展開プラン](https://aka.ms/AppProxyDPDownload)を使います。 Microsoft Entra ID に接続してオンプレミスとクラウドのレガシ認証アプリケーションを保護するには、[セキュリティで保護されたハイブリッド アクセス (SHA)](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/secure-hybrid-access) を検討してください。 [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/whatis-azure-ad-connect-v2) を使って、AD FS ユーザーとグループを Microsoft Entra ID と同期します。

#### フェーズ 4: 管理と分析情報について計画する

アプリを移行したら、[管理と分析情報に関するガイダンス](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migrate-adfs-plan-management-insights)に従って、ユーザーがセキュリティで保護された方法でアプリにアクセスして管理できるようにします。 使用状況とアプリの正常性に関する分析情報を取得して共有します。

[Microsoft Entra 管理センター](https://entra.microsoft.com/)から、次の方法を使ってすべてのアプリを監査します。

- **[エンタープライズ アプリケーション] の [監査]** を使ってアプリを監査したり、[Microsoft Entra reporting API](https://learn.microsoft.com/ja-jp/azure/active-directory/reports-monitoring/howto-configure-prerequisites-for-reporting-api) から同じ情報にアクセスして、お気に入りのツールに統合したりします。
- **エンタープライズ アプリケーション、アクセス許可**を使用して Open Authorization (OAuth)/OpenID Connect を使用するアプリに関するアプリのアクセス許可を確認します。
- **[エンタープライズ アプリケーション] の [サインイン]** を使い、[Microsoft Entra reporting API](https://learn.microsoft.com/ja-jp/azure/active-directory/reports-monitoring/howto-configure-prerequisites-for-reporting-api) からサインインの分析情報を入手します。
- [Microsoft Entra ブック](https://learn.microsoft.com/ja-jp/azure/active-directory/reports-monitoring/howto-use-workbooks)からアプリの使用状況を視覚化します。

#### 検証環境を準備する

PowerShell ギャラリーの [MSIdentityTools](https://www.powershellgallery.com/packages/MSIdentityTools/) を使って、Microsoft Identity 製品とサービスの管理、トラブルシューティング、報告を行います。 [Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-health-adfs) を使って AD FS インフラストラクチャを監視します。 AD FS でテスト アプリを作成し、定期的にユーザー アクティビティを生成し、Microsoft Entra 管理センターでアクティビティを監視します。

[オブジェクトを Microsoft Entra ID に同期する](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-single-object-sync)ときは、クラウドで使用できる要求規則に使われているグループ、ユーザー、ユーザー属性の AD FS [証明書利用者の信頼](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/create-a-relying-party-trust)要求規則を確認します。 コンテナーと属性の同期を確認します。

#### アプリ移行シナリオの違い

環境に次のシナリオが含まれる場合、アプリの移行計画には特に注意が必要です。 さらに詳しいガイダンスについては、リンクをクリックしてください。

- **証明書。** ([AD FS グローバル署名証明書](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/certificate-requirements-for-federation-servers))。 Microsoft Entra ID では、1 つのアプリケーションに 1 つの証明書を使うか、すべてのアプリケーションに対して 1 つの証明書を使うことができます。 有効期限をずらしてリマインダーを構成します。 証明書の管理を最小特権のロールに委任します。 [Microsoft Entra ID で作成される証明書に関する](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/tutorial-manage-certificates-for-federated-single-sign-on)一般的な質問と情報を確認して、SaaS アプリケーションへのフェデレーション SSO を確立します。
- **要求規則と基本的な属性マッピング。** SaaS アプリケーションの場合、基本的な属性と要求間のマッピングのみが必要な場合があります。 [SAML トークン要求をカスタマイズする](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/saml-claims-customization)方法を参照してください。
- **UPN からユーザー名を抽出します。** Microsoft Entra ID は、正規表現ベースの変換をサポートしています ([SAML トークン要求のカスタマイズ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/saml-claims-customization)にも関連します)。
- **グループ要求。** メンバーであり、アプリケーションに割り当てられているユーザーによってフィルター処理されたグループ要求を出力します。 [Microsoft Entra ID を使ってアプリケーションのグループ要求を構成する](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-fed-group-claims)ことができます。
- **Web アプリケーション プロキシ。**[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/azure/active-directory/app-proxy/application-proxy-configure-single-sign-on-with-kcd)を使って発行し、VPN を使わずにオンプレミスの Web アプリにセキュリティで保護された方法で接続できます。 オンプレミスの Web アプリへのリモート アクセスと、レガシ認証プロトコルのネイティブ サポートを提供します。

#### アプリケーション移行プロセス計画

アプリケーション移行プロセスに次の手順を含めることを検討してください。

- **AD FS アプリの構成を複製します。** アプリのテスト インスタンスを設定するか、テスト用の Microsoft Entra テナントでモック アプリを使います。 AD FS 設定を Microsoft Entra 構成にマップします。 ロールバックを計画します。 テスト インスタンスを Microsoft Entra ID に切り替えて検証します。
- **要求と識別子を構成します。** 確認とトラブルシューティングを行うには、運用アプリを再現します。 アプリのテスト インスタンスが Microsoft Entra ID テスト アプリケーションを指すようにします。 アクセスを検証してトラブルシューティングし、必要に応じて構成を更新します。
- **運用環境インスタンスの移行を準備します。** テスト結果に基づいて、運用環境アプリケーションを Microsoft Entra ID に追加します。 複数の ID プロバイダー (IdP) を許可するアプリケーションの場合は、運用インスタンスに追加される IdP として Microsoft Entra ID を構成します。
- **Microsoft Entra ID を使うように運用環境インスタンスを切り替えます。** 複数の同時 IdP を許可するアプリケーションの場合は、既定の IdP を Microsoft Entra ID に変更します。 それ以外の場合は、運用環境インスタンスに対する IdP として Microsoft Entra ID を構成します。 Microsoft Entra 運用アプリケーションをプライマリ IdP として使うように運用インスタンスを更新します。
- **最初のアプリを移行し、移行テストを実行して、問題を修正します。** 移行状態については、[AD FS アプリケーション アクティビティ レポート](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/migrate-adfs-application-activity)を参照してください。潜在的な移行の問題に対処する方法に関するガイダンスが記載されています。
- **大規模に移行します。** アプリとユーザーを段階的に移行します。 Microsoft Entra ID を使って移行されたアプリとユーザーを管理しながら、認証を十分にテストします。
- **フェデレーションを削除します。** AD FS ファームが認証に使われなくなったことを確認します。 関連する構成のフェールバック、バックアップ、エクスポートを使います。

### 認証を移行する

認証の移行の準備をする際に、組織にどの[認証方法](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/choose-ad-authn)が必要かを決定します。

- フェールオーバーまたはプライマリ エンド ユーザー認証には、[Microsoft Entra ID を使ったパスワード ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/whatis-phs) を使用できます。 Microsoft Entra ID 保護の一部として[リスク検出](https://learn.microsoft.com/ja-jp/azure/active-directory/identity-protection/concept-identity-protection-risks)を構成します。 [Microsoft Graph API を使用したリスクの特定と修復](https://learn.microsoft.com/ja-jp/graph/tutorial-riskdetection-api)のチュートリアルを参照し、[Microsoft Entra Connect 同期を使用してパスワード ハッシュ同期を実装する](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-password-hash-synchronization)方法を学習してください。
- [Microsoft Entra 証明書ベースの認証 (CBA)](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-certificate-based-authentication) は、フェデレーション IdP を必要とせずに、Microsoft Entra ID に対して直接認証されます。 主な利点には、フィッシング耐性 CBA によるセキュリティの向上に役立つ機能や、フィッシング耐性多要素認証の Executive Order (EO) 14028 要件を満たす機能が含まれます。 オンプレミスのフェデレーション インフラストラクチャに関連するコストとリスクを削減し、きめ細かい制御を使用して Microsoft Entra ID の管理エクスペリエンスを簡素化します。
- [Microsoft Entra Connect: パススルー認証](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-pta) (PTA) は、ソフトウェア エージェントを使って、検証のためにオンプレミスに格納されているパスワードに接続します。 ユーザーは、オンプレミス リソースと同じユーザー名とパスワードを使ってクラウド アプリにサインインし、[Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーとシームレスに連携します。 スマート ロックアウトはブルート フォース攻撃を防ぎます。 現在の Microsoft Windows Server Active Directory インフラストラクチャを使用して、オンプレミスに認証エージェントをインストールします。 パスワード ハッシュを Microsoft Entra ID と同期できないことが規制要件で指定されている場合は、PTA を使います。それ以外の場合は PHS を使います。
- AD FS を使用しない現在の SSO エクスペリエンス。 [シームレス シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-sso-faq) により、企業ネットワーク (Kerberos) 内のドメインに参加しているデバイスからの SSO エクスペリエンスを実現できます。 PHS、PTA、CBA と連携し、他のオンプレミス インフラストラクチャは必要ありません。 ユーザーが、オンプレミスのディレクトリ環境と同じ資格情報を使い、[代替ログイン ID としてメール アドレスを使って Microsoft Entra ID にサインイン](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-authentication-use-email-signin)できるようにすることができます。 ハイブリッド認証の場合、ユーザーには 1 セットの資格情報が必要です。
- 推奨事項: PTA 経由の PHS、[Microsoft Entra ID でのパスワードレス認証](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-authentication-passwordless-deployment) ([Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification)、[FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-authentication-passwordless-security-key)、または [Microsoft Authenticator](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-authentication-passwordless-phone) を使用)。 [Windows Hello for Business ハイブリッド証明書信頼](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-cert-trust)を使用する予定の場合は、すべてのユーザーを再登録するためにまずクラウド信頼に移行します。

#### パスワードと認証のポリシー

オンプレミスの AD FS と Microsoft Entra ID の専用ポリシーを使って、オンプレミスと Microsoft Entra ID のパスワード有効期限ポリシーを調整します。 「[Microsoft Entra ID のパスワード ポリシーの組み合わせと、脆弱なパスワードのチェック](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-password-ban-bad-combined-policy#microsoft-entra-password-policies)」を実装することを検討してください。 マネージド ドメインに切り替えると、両方のパスワード有効期限ポリシーが適用されます。

[セルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-sspr-howitworks) (SSPR) を使って忘れたパスワードをユーザーがリセットできるようにすることで、ヘルプ デスクのコストを削減します。 [デバイスベースの認証方法](https://learn.microsoft.com/ja-jp/azure/active-directory/devices/faq#how-can-users-change-their-temporary-or-expired-password-on-microsoft-entra-joined-devices)を制限します。 パスワード ポリシーを最新化して、定期的な有効期限を設定せず、リスクがある場合に失効できるようにします。 脆弱なパスワードをブロックし、パスワードを禁止パスワード一覧と照合します。 オンプレミスの AD FS とクラウドの両方で[パスワード保護](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-password-ban-bad-combined-policy)を有効にします。

多要素認証と SSPR を個別に制御する Microsoft Entra ID の[レガシ ポリシー設定](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-authentication-methods-manage#legacy-mfa-and-sspr-policies)を、[認証方法ポリシー](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-authentication-methods-manage)を使用する統合管理に移行します。 元に戻せるプロセスで[ポリシー設定を移行します](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/how-to-authentication-methods-manage)。 ユーザーとグループの認証方法を正確に構成すると同時に、テナント全体の多要素認証ポリシーと SSPR ポリシーを引き続き使用することができます。

Windows サインインやその他のパスワードを使わない方法には詳細な構成が必要なので、[Microsoft Authenticator](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-authentication-passwordless-phone) と [FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-authentication-passwordless-security-key)を使ったサインインを有効にします。 グループを使ってデプロイを管理し、ユーザーの範囲を設定します。

[ホーム領域検出を使ってサインイン自動高速化を構成する](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/configure-authentication-for-federated-users-portal?pivots=powershell-hrd)ことができます。 自動高速化サインインを使ってユーザー名入力画面をスキップし、ユーザーをフェデレーション サインイン エンドポイントに自動的に転送する方法を確認してください。 [ホーム領域検出ポリシーを使ってサインイン自動高速化を防ぎ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/prevent-domain-hints-with-home-realm-discovery?pivots=powershell-hrd)、ユーザーの認証方法と場所を複数の方法で制御します。

#### アカウントの有効期限ポリシー

ユーザー アカウント管理の **accountExpires** 属性は Microsoft Entra ID と同期されません。 結果として、パスワード ハッシュ同期用に構成された環境の[期限切れの Active Directory アカウント](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-password-hash-synchronization#account-expiration)は、Microsoft Entra ID でアクティブです。 [Set-ADUser コマンドレット](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-aduser?view=windowsserver2022-ps&preserve-view=true)など、スケジュールされた PowerShell スクリプトを使用して、ユーザー Microsoft Windows Server Active Directory アカウントを有効期限が切れた後に無効にします。 逆に、Microsoft Windows Server Active Directory アカウントから有効期限を削除する場合は、アカウントを再度有効にします。

Microsoft Entra ID を使うと、[スマート ロックアウトを使って攻撃を防止](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-password-smart-lockout)できます。 ブルートフォース攻撃を軽減するために、オンプレミスでアカウントをロックする前に、クラウドでアカウントをロックします。 クラウドの間隔を短くし、オンプレミスのしきい値が Microsoft Entra のしきい値よりも少なくとも 2 倍から 3 倍大きくなるようにします。 Microsoft Entra のロックアウト期間をオンプレミス期間よりも長く設定します。 移行が完了したら、[AD FS エクストラネット スマート ロックアウト (ESL) 保護を構成](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-extranet-smart-lockout-protection)します。

#### 条件付きアクセスのデプロイを計画する

条件付きアクセス ポリシーの柔軟性には慎重な計画が必要です。 計画手順については、「[Microsoft Entra 条件付きアクセス デプロイの計画](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/plan-conditional-access)」を参照してください。 注意すべき重要な点は次のとおりです。

- わかりやすい名前付け規則を使って、[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-policies)の下書きを作成します
- 次のフィールドがある設計決定ポイント スプレッドシートを使います。
    - [割り当て要因](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-users-groups): ユーザー、クラウド アプリ
    - [条件](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-conditions): 場所、デバイスの種類、クライアント アプリの種類、サインインのリスク
    - [コントロール](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-grant): ブロック、多要素認証が必要、ハイブリッド Microsoft Windows Server Active Directory 参加済み、準拠デバイスが必要、承認されたクライアント アプリの種類
- 条件付きアクセス ポリシー用に少人数のテスト ユーザーのセットを選びます
- [レポート専用モード](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-report-only)で条件付きアクセス ポリシーをテストします
- [what if ポリシー ツール](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/what-if-tool)を使用して、条件付きアクセス ポリシーを検証します
- [条件付きアクセス テンプレート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)を使用します (特に組織が条件付きアクセスを初めて使用する場合)

#### 条件付きアクセス ポリシー

第 1 要素認証を完了したら、[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-policies)を適用します。 条件付きアクセスは、サービス拒否 (DoS) 攻撃などのシナリオに対する最前線の防御ではありません。 ただし、条件付きアクセスでは、サインインのリスクや要求の場所など、これらのイベントからのシグナルを使ってアクセスを決定できます。 条件付きアクセス ポリシーのフローは、セッションとその条件を調べて、結果を中央のポリシー エンジンにフィードします。

条件付きアクセスを使うと、[Intune アプリ保護ポリシーを備えた承認済み (先進認証対応) クライアント アプリのみにアクセスを制限できます](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-grant#require-app-protection-policy)。 アプリ保護ポリシーをサポートしていない場合がある古いクライアント アプリの場合、[承認されたクライアント アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-grant#require-approved-client-app)のみにアクセスを制限できます。

属性に基づいてアプリを自動的に保護します。 お客様のセキュリティ属性を変更するには、クラウド アプリケーションの[動的フィルター処理](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-filter-for-applications)を使って、アプリケーション ポリシー スコープを追加および削除します。 カスタム セキュリティ属性を使って、条件付きアクセス アプリケーション ピッカーに表示されない Microsoft ファーストパーティ アプリケーションを対象にします。

条件付きアクセスの[認証強度](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-authentication-strengths)コントロールを使って、リソースにアクセスする認証方法の組み合わせを指定します。 たとえば、機密リソースへのアクセスにフィッシング耐性のある認証方法を使用できるようにします。

[条件付きアクセスのカスタム コントロール](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/controls)を評価します。 ユーザーは、Microsoft Entra ID 以外の認証要件を満たす互換性のあるサービスにリダイレクトされます。 Microsoft Entra ID は応答を検証し、ユーザーが認証または検証された場合は、条件付きアクセス フロー内にとどまります。 「[カスタム コントロールへの今後の変更点](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/upcoming-changes-to-custom-controls/ba-p/1144696)」で言及されているように、パートナーが提供する認証機能は、Microsoft Entra 管理者エクスペリエンスおよびエンド ユーザー エクスペリエンスとシームレスに連携します。

#### カスタム多要素認証ソリューション

カスタム多要素認証プロバイダーを使用する場合は、[Multi-Factor Authentication Server から Microsoft Entra 多要素認証への移行](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/how-to-migrate-mfa-server-to-azure-mfa)を検討してください。 必要に応じて[多要素認証を移行するための Multi-Factor Authentication Server 移行ユーティリティ](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/how-to-mfa-server-migration-utility)を使用します。 アカウント ロックアウトのしきい値、不正行為アラート、通知を設定して[エンド ユーザー エクスペリエンスをカスタマイズします](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-mfa-mfasettings)。

[多要素認証と Microsoft Entra ユーザー認証へ移行する](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/how-to-migrate-mfa-server-to-mfa-user-authentication)方法を学習します。 すべてのアプリケーション、多要素認証サービス、ユーザー認証を Microsoft Entra ID に移行します。

[Microsoft Entra 多要素認証を有効](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/tutorial-enable-azure-mfa)にして、ユーザー グループの条件付きアクセス ポリシーの作成、多要素認証を要求するポリシー条件の構成、ユーザー構成と多要素認証の使用のテストを行う方法を学習できます。

多要素認証のためのネットワーク ポリシー サーバー (NPS) 拡張機能は、サーバーを使用してクラウドベースの多要素認証機能を認証インフラストラクチャに追加します。 [NPS による Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-mfa-nps-extension)を使う場合は、Security Assertion Markup Language (SAML) や OAuth2 などの最新のプロトコルに移行できるものを決定します。 リモート アクセス用の Microsoft Entra アプリケーション プロキシを評価し、[セキュリティで保護されたハイブリッド アクセス (SHA) を使って Microsoft Entra ID でレガシ アプリを保護します](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/secure-hybrid-access)。

#### サインインを監視する

要求とエラーの詳細については、[Connect Health を使って AD FS サインインを統合](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-health-ad-fs-sign-in)し、サーバーのバージョンに応じて AD FS の複数のイベント ID を関連付けます。 [Microsoft Entra サインイン レポート](https://learn.microsoft.com/ja-jp/azure/active-directory/reports-monitoring/concept-all-sign-ins)には、ユーザー、アプリケーション、管理対象リソースがいつ Microsoft Entra ID にサインインしてリソースにアクセスしたかに関する情報が含まれています。 これらの情報は Microsoft Entra サインイン レポート スキーマに関連付けられ、Microsoft Entra サインイン レポート ユーザー エクスペリエンスに表示されます。 レポートでは、Log Analytics ストリームによって AD FS データが提供されます。 シナリオ分析には Azure Monitor ブック テンプレートを使って変更します。 たとえば、AD FS アカウントのロックアウト、不正なパスワードの試行、予期しないサインイン試行の増加などがあります。

Microsoft Entra により、内部アプリとリソースを含め、Azure テナントへのすべてのサインインがログされます。 サインイン エラーとパターンを確認して、ユーザーがアプリケーションやサービスにどのようにアクセスするかを把握します。 [Microsoft Entra ID のサインイン ログ](https://learn.microsoft.com/ja-jp/azure/active-directory/reports-monitoring/concept-sign-ins)は、分析に役立つ[アクティビティ ログ](https://learn.microsoft.com/ja-jp/azure/active-directory/reports-monitoring/howto-access-activity-logs)です。 ライセンスに応じて、[データ保持](https://learn.microsoft.com/ja-jp/azure/active-directory/reports-monitoring/reference-reports-data-retention)が最長 30 日間のログを構成し、[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/active-directory/reports-monitoring/howto-integrate-activity-logs-with-azure-monitor-logs)、Sentinel、Splunk、その他のセキュリティ情報イベント管理 (SIEM) システムにエクスポートします。

#### 企業ネットワーク内要求

[ゼロ トラスト モデル](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)は、要求の送信元やアクセス先のリソースに関係なく、"決して信頼せず、常に確認する" ことを要求します。すべての場所を企業ネットワークの外部であるかのようにセキュリティで保護します。 ID 保護の擬陽性を減らすために、ネットワーク内を[信頼できる場所](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network#trusted-locations)として宣言します。 すべてのユーザーに多要素認証を適用し、[デバイスベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/create-conditional-access-intune)を確立します。

サインイン ログに対してクエリを実行して、企業ネットワーク内から接続していても、[Microsoft Entra ハイブリッド参加済みまたは準拠](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)ではないユーザーを検出します。 準拠デバイスを使っているユーザーと、拡張機能なしの Firefox や Chrome などのサポートされていないブラウザーの使用を検討してください。 代わりに、コンピューター ドメインとコンプライアンスの状態を使います。

#### 段階的な認証移行テストと一括移行

テストには、[段階的ロールアウトによる Microsoft Entra Connect クラウド認証](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-staged-rollout)を使って、グループによるテスト ユーザー認証を制御します。 [Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/tutorial-enable-azure-mfa)、[条件付きアクセス](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/overview)、[Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/azure/active-directory/identity-protection/overview-identity-protection) などのクラウド認証機能を使用してユーザー グループを選択的にテストします。 ただし、段階的な移行には段階的ロールアウトを使わないでください。

クラウド認証への移行を完了するには、段階的ロールアウトを使って新しいサインイン方法をテストします。 ドメインを[フェデレーション認証からマネージド認証に](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/migrate-from-federation-to-cloud-authentication#convert-domains-from-federated-to-managed)変換します。 テスト ドメイン、またはユーザー数が最も少ないドメインから始めます。

ロールバックが必要になった場合に備えて、営業時間外にドメインの一括移行を計画します。 [ロールバックを計画する](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/migrate-from-federation-to-cloud-authentication#plan-for-rollback)には、現在のフェデレーション設定を使います。 [フェデレーションの設計および展開ガイド](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/windows-server-2012-r2-ad-fs-deployment-guide)のページを参照してください。

ロールバック プロセスにマネージド ドメインからフェデレーション ドメインへの変換を含めます。 [New-MgDomainFederationConfiguration](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdomainfederationconfiguration?view=graph-powershell-1.0&preserve-view=true) コマンドレットを使います。 必要に応じて、追加の要求規則を構成します。 プロセスが確実に完了するように、一括移行後 24 時間から 48 時間は、ロールバック段階的ロールアウトのままにします。 段階的ロールアウトからユーザーとグループを削除し、オフにします。

一括移行後、30 日ごとに、[シームレス SSO](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-sso-faq) のキーをロールオーバーします。

```azurecli
Update-AzureADSSOForest -OnPremCredentials $creds -PreserveCustomPermissionsOnDesktopSsoAccount
```

### AD FS の使用を停止する

[AD FS の Connect Health 利用状況分析](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-health-adfs#usage-analytics-for-ad-fs)から AD FS アクティビティを監視します。 まず、[AD FS の監査を有効にします](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-health-agent-install#enable-auditing-for-ad-fs)。 AD FS ファームを使用停止にする前に、AD FS アクティビティがないことを確認します。 [AD FS の使用停止ガイド](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/decommission/adfs-decommission-guide)と [AD FS 使用停止リファレンス](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/ad-fs-decommission)の記事を参照してください。 主な使用停止手順の概要を次に示します。

1. AD FS サーバーの使用を停止する前に、[最終バックアップ](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/ad-fs-rapid-restore-tool#create-a-backup)を作成します。
2. 内部および外部のロード バランサーから AD FS エントリを削除します。
3. AD FS サーバー ファーム名に対応するドメイン ネーム サーバー (DNS) エントリを削除します。
4. プライマリ AD FS サーバー上で [Get-ADFSProperties](https://learn.microsoft.com/ja-jp/powershell/module/adfs/get-adfsproperties?view=windowsserver2022-ps&preserve-view=true) を実行し、**CertificateSharingContainer** を探します。
    注

    ドメイン名 (DN) は、インストールの終了近くに、数回再起動した後、使用できなくなったら削除します。
5. SQL Server データベース インスタンスをストアとして使っている場合は、AD FS 構成データベースを削除します。
6. WAP サーバーをアンインストールします。
7. AD FS サーバーをアンインストールします。
8. 各サーバー ストレージから AD FS Secure Sockets Layer (SSL) 証明書を削除します。
9. フル ディスク フォーマットを使って AD FS サーバーを再イメージ化します。
10. AD FS アカウントを削除します。
11. Active Directory サービス インターフェイス (ADSI) 編集を使用して、**CertificateSharingContainer** DN の内容を削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/monitor-sign-in-health-for-resilience"} -->
## Microsoft Entra ID で回復性のためにアプリケーションのサインインの正常性を監視する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/monitor-sign-in-health-for-resilience
- Service: entra / architecture
- Article date: 2023-06-16
- Summary: クエリと通知を作成して、アプリケーションのサインインの正常性を監視します。

インフラストラクチャの回復性を向上させるには、重要なアプリケーションを対象にアプリケーション サインイン 正常性の監視を設定します。 影響を及ぼすインシデントが発生したときに、アラートを受け取ることができます。 この記事では、ユーザーのサインインへの障害を監視するために、アプリ サインイン正常性ブックを設定する手順について説明します。

アプリ サインインヘルス ワークブックに基づいてアラートを構成できます。 このブックを使用すると、管理者はテナント内のアプリケーションに対する認証要求を監視できます。 次の主要な機能が用意されています。

- ほぼリアルタイムのデータを使用して、すべてまたは個別のアプリを監視するようにワークブックを構成します。
- 調査と対応ができるように、認証パターンの変更に対するアラートを構成します。
- 一定期間にわたる傾向を比較します。 ワークブックのデフォルト設定は週単位です。

注意

[レポートに Azure Monitor ワークブックを使用する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)」で、利用可能なすべてのワークブックとそれらを使用するための前提条件をご確認ください。

影響を及ぼすイベントが発生すると、2 つのことが発生する可能性があります。

- ユーザーがサインインできない場合、アプリケーションのサインイン回数が急激に減少する可能性があります。
- サインイン失敗の数が増加するおそれがあります。

### 前提条件

- Microsoft Entra テナント。
- 少なくとも[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロールが割り当てられているユーザー。
- Azure Monitor ログにログを送信するための、Azure サブスクリプション内の Azure Log Analytics ワークスペース。 [Log Analytics ワークスペースの作成方法](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)を確認してください。
- Azure Monitor ログに統合された Microsoft Entra ログ。 [Microsoft Entra サインイン ログを Azure Monitor Stream と統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)方法を参照してください。

### アプリ サインインの健全性ワークブックを構成する

**Azure portal** でワークブックにアクセスするには、**[Microsoft Entra ID]** を選択し、**[ワークブック]** を選択します。

ブックは、**[使用状況]**、**[条件付きアクセス]**、および **[トラブルシューティング]** の下に表示されます。 アプリのサインインヘルスワークブックは、**[ヘルス]** セクションに表示されます。 ブックを使用した後、**[最近変更されたブック]** セクションに表示される場合があります。

アプリのサインインヘルス ワークブックを使用して、サインインの状況を視覚化できます。次のスクリーンショットに示すように、ワークブックには2つのグラフが表示されます。

[Image: サインインの正常性グラフを示すスクリーンショット。]

先のスクリーンショットには、次の 2 つのグラフがあります。

- **時間あたり使用量 (成功したユーザーの数)。** 現在の成功したユーザーの数を典型的な使用量の期間と比較すると、調査が必要な可能性のある使用量の低下を特定するのに役立ちます。 成功使用率の低下は、失敗率では検出できないパフォーマンスと使用率の問題を検出するのに役立ちます。 たとえば、ユーザーがサインインしようとしてもアプリケーションに到達できない場合、失敗にはならず、使用量が低下するだけです。 この記事の次のセクションで、このデータのためのサンプル クエリを確認してください。
- **時間あたり失敗率。** 失敗率の急上昇は、認証メカニズムに問題があることを示している場合があります。 失敗率の測定は、ユーザーが認証を試みることができる場合にのみ行われます。 ユーザーが試行を行うためのアクセスを取得できない場合、失敗にはなりません。

### クエリとアラートを構成する

Azure Monitor でアラート ルールを作成し、保存済みのクエリまたはカスタム ログ検索を一定の間隔で自動的に実行できます。 使用量または失敗率が指定したしきい値を超えたときに特定のグループに通知するアラートを構成できます。

次の手順に従って、グラフに反映されたクエリに基づいて電子メール アラートを作成します。 サンプル スクリプトは、次の場合にメール通知を送信します。

- 先の時間あたり使用量のグラフの例のように、成功の使用量が 2 日前の同じ時間から 90% 低下している場合。
- 先の時間あたり失敗率のグラフの例のように、失敗率が 2 日前の同じ時間から 90% 増加している場合。

基になるクエリを構成し、アラートを設定するには、構成の基礎としてサンプル クエリを使用する次の手順を完了してください。 クエリ構造の説明はこのセクションの最後にあります。 「[ログ アラートの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/alerts-create-new-alert-rule)」で、Azure Monitor を使用してログ アラートを作成、表示、管理する方法を確認してください。

1. 次のスクリーンショットに示すように、ワークブックで **[編集]** を選択します。 グラフの右上隅にある**クエリ アイコン**を選択します。

    [Image: ブックの編集を示すスクリーンショット。]
2. 次のスクリーンショットに示すように、クエリ ログを表示します。

    [Image: クエリ ログを示すスクリーンショット。]
3. 新しい Kusto クエリ用に次のいずれかのサンプル スクリプトをコピーします。

    - 失敗率の増加に関する Kusto クエリ
    - 使用量の低下に関する Kusto クエリ
4. ウィンドウにクエリを貼り付けます。 **[実行]** を選択します。 次のスクリーンショットに示すように、**完了**メッセージとクエリ結果を探します。

    [Image: クエリの実行結果を示すスクリーンショット。]
5. クエリを強調表示する。 **[+ 新しいアラート ルール]** を選択します。

    [Image: 新しいアラート ルールの画面を示すスクリーンショット。]
6. アラートの条件を構成します。 次のスクリーンショットの例に示すように、**[条件]** セクションの **[測定]** の下で、**[測定]** として **[テーブル行]** を選択します。 **[集計の種類]** に **[カウント]** を選択します。 **集計の粒度**として**2日**を選択します。

    [Image: アラートの構成画面を示すスクリーンショット。]

    - **テーブル行。** 返される行の数を使用して、Windows イベント ログ、Syslog、アプリケーション例外などのイベントを処理できます。
    - **集計の種類。** "カウント" が適用されたデータ ポイント。
    - **集計の粒度。** この値は、**評価の頻度**で使用される期間を定義します。
7. **アラート ロジック**で、スクリーンショットの例に示すようにパラメーターを構成します。

    [Image: アラート ロジックを示すスクリーンショット。]

    - **しきい値**: 0。 この値を使用すると、任意の結果に対してアラートが行われます。
    - **評価の頻度**: 1 時間。 この値を使用して、直前の時間について評価期間を 1 時間に 1 回に設定します。
8. **[アクション]** セクションで、スクリーンショットの例に示すように設定を構成します。

    [Image: [アラート ルールの作成] 画面を示すスクリーンショット。]

    - **[アクション グループの選択]** を選択し、アラート通知の対象となるグループを追加します。
    - **[アクションのカスタマイズ]** で、**[メール アラート]** を選択します。
    - **件名行**を追加します。
9. **[詳細]** セクションで、スクリーンショットの例に示すように設定を構成します。

    [Image: [詳細] セクションを示すスクリーンショット。]

    - **サブスクリプション**の名前と説明を追加します。
    - アラート追加先としたい**リソース グループ**を選択します。
    - 既定の**重大度**を選択します。
    - すぐに有効にしたい場合は、**[作成時に有効化]** を選択します。 それ以外の場合は、**[ミュート アクション]** を選択します。
10. **[確認と作成]** セクションで、スクリーンショットの例に示すように設定を構成します。

    [Image: [確認と作成] セクションを示すスクリーンショット。]
11. **[保存]** を選択します。 クエリの名前を入力します。 **[名前を付けて保存]** では、**[クエリ]** を選択します。 **[カテゴリ]** では、**[アラート]** を選択します。 もう一度、**[保存]** を選択します。

    [Image: クエリの保存ボタンを示すスクリーンショット。]

#### クエリとアラートを調整する

効果が最大限になるようにクエリとアラートを変更するには:

- 常にアラートをテストします。
- 重要な通知を受け取れるようにアラートの感度と頻度を変更します。 管理者は、受け取る数が多すぎるとアラートに対して鈍感になり、重要なことを見逃すおそれがあります。
- 管理者のメール クライアントで、アラートが送信されてくるメールを許可された送信者の一覧に追加します。 この方法により、メール クライアントのスパム フィルターが原因で通知が見逃されるのを防ぐことができます。
- 設計上、Azure Monitor のアラート クエリには、過去 48 時間の結果のみを含めることができます。

### サンプルのスクリプト

#### 失敗率の増加に関する Kusto クエリ

次のクエリでは、失敗率の増加を検出します。 必要に応じて、下部の比率を調整できます。 これは、過去 1 時間のトラフィックの比率の変化を、昨日の同時刻のトラフィックと比較して示しています。 0.5 という結果は、トラフィックにおいて 50% の違いがあることを示します。

```kusto
let today = SigninLogs
| where TimeGenerated > ago(1h) // Query failure rate in the last hour
| project TimeGenerated, UserPrincipalName, AppDisplayName, status = case(Status.errorCode == "0", "success", "failure")
// Optionally filter by a specific application
//| where AppDisplayName == **APP NAME**
| summarize success = countif(status == "success"), failure = countif(status == "failure") by bin(TimeGenerated, 1h) // hourly failure rate
| project TimeGenerated, failureRate = (failure * 1.0) / ((failure + success) * 1.0)
| sort by TimeGenerated desc
| serialize rowNumber = row_number();
let yesterday = SigninLogs
| where TimeGenerated between((ago(1h) – totimespan(1d))..(now() – totimespan(1d))) // Query failure rate at the same time yesterday
| project TimeGenerated, UserPrincipalName, AppDisplayName, status = case(Status.errorCode == "0", "success", "failure")
// Optionally filter by a specific application
//| where AppDisplayName == **APP NAME**
| summarize success = countif(status == "success"), failure = countif(status == "failure") by bin(TimeGenerated, 1h) // hourly failure rate at same time yesterday
| project TimeGenerated, failureRateYesterday = (failure * 1.0) / ((failure + success) * 1.0)
| sort by TimeGenerated desc
| serialize rowNumber = row_number();
today
| join (yesterday) on rowNumber // join data from same time today and yesterday
| project TimeGenerated, failureRate, failureRateYesterday
// Set threshold to be the percent difference in failure rate in the last hour as compared to the same time yesterday
// Day variable is the number of days since the previous Sunday. Optionally ignore results on Sat, Sun, and Mon because large variability in traffic is expected.
| extend day = dayofweek(now())
| where day != time(6.00:00:00) // exclude Sat
| where day != time(0.00:00:00) // exclude Sun
| where day != time(1.00:00:00) // exclude Mon
| where abs(failureRate – failureRateYesterday) > 0.5
```

#### 使用量の低下に関する Kusto クエリ

次のクエリでは、過去 1 時間のトラフィックを昨日の同時刻のトラフィックと比較します。 前日の同時刻のトラフィックに大きな変動があると予想されるため、土曜日、日曜日、月曜日は除外します。

必要に応じて、下部の比率を調整できます。 これは、過去 1 時間のトラフィックの比率の変化を、昨日の同時刻のトラフィックと比較して示しています。 0.5 という結果は、トラフィックにおいて 50% の違いがあることを示します。 これらの値を、ビジネスの運用モデルに合わせて調整します。

```kusto
Let today = SigninLogs // Query traffic in the last hour
| where TimeGenerated > ago(1h)
| project TimeGenerated, AppDisplayName, UserPrincipalName
// Optionally filter by AppDisplayName to scope query to a single application
//| where AppDisplayName contains "Office 365 Exchange Online"
| summarize users = dcount(UserPrincipalName) by bin(TimeGenerated, 1hr) // Count distinct users in the last hour
| sort by TimeGenerated desc
| serialize rn = row_number();
let yesterday = SigninLogs // Query traffic at the same hour yesterday
| where TimeGenerated between((ago(1h) – totimespan(1d))..(now() – totimespan(1d))) // Count distinct users in the same hour yesterday
| project TimeGenerated, AppDisplayName, UserPrincipalName
// Optionally filter by AppDisplayName to scope query to a single application
//| where AppDisplayName contains "Office 365 Exchange Online"
| summarize usersYesterday = dcount(UserPrincipalName) by bin(TimeGenerated, 1hr)
| sort by TimeGenerated desc
| serialize rn = row_number();
today
| join // Join data from today and yesterday together
(
yesterday
)
on rn
// Calculate the difference in number of users in the last hour compared to the same time yesterday
| project TimeGenerated, users, usersYesterday, difference = abs(users – usersYesterday), max = max_of(users, usersYesterday)
| extend ratio = (difference * 1.0) / max // Ratio is the percent difference in traffic in the last hour as compared to the same time yesterday
// Day variable is the number of days since the previous Sunday. Optionally ignore results on Sat, Sun, and Mon because large variability in traffic is expected.
| extend day = dayofweek(now())
| where day != time(6.00:00:00) // exclude Sat
| where day != time(0.00:00:00) // exclude Sun
| where day != time(1.00:00:00) // exclude Mon
| where ratio > 0.7 // Threshold percent difference in sign-in traffic as compared to same hour yesterday
```

### アラートを管理するプロセスを作成する

クエリとアラートを設定したら、アラートを管理するビジネス プロセスを作成します。

- 誰がブックを監視しており、いつ監視されていますか?
- アラートが発生した場合、誰が調査を行うのか?
- 通信のニーズはどのようなものか? 誰がコミュニケーションを開始し、誰が受け取るのか?
- 障害が発生した場合、どのようなビジネス プロセスが適用されるのか?
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multi-tenant-common-considerations"} -->
## Microsoft Entra ID でのマルチテナント ユーザー管理の一般的な考慮事項 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-considerations
- Service: entra / architecture
- Article date: 2026-02-04
- Summary: ゲスト アカウントによる複数の Microsoft Entra テナントへのユーザー アクセスを設計するときの一般的な考慮事項について説明します

この記事は、Microsoft Entra マルチテナント環境でユーザー ライフサイクル管理を構成、提供するためのガイダンスを提供するシリーズ記事の第 3 回目です。 シリーズ記事の次回以降は、下記のとおり、さらに詳しい情報について説明します。

- このシリーズ記事の第 1 回目の「[マルチテナント ユーザー管理の概要](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-introduction)」では、Microsoft Entra マルチテナント環境でユーザー ライフサイクル管理を構成、提供するためのガイダンスを提供しています。
- 「[マルチテナント ユーザー管理のシナリオ](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-scenarios)」では、マルチテナント ユーザー管理機能を使用できる 3 つのシナリオ (エンド ユーザーによる実施、スクリプト化、自動化) について説明します。
- [マルチテナント ユーザー管理の一般的なソリューション](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-solutions) (シングル テナントでは要件を満たせない場合): テナント間での自動ユーザー ライフサイクル管理とリソース割り当て、テナント間でのオンプレミス アプリ共有、といった課題のガイダンスを説明します。

このガイダンスは、ユーザー ライフサイクル管理で整合性のとれた状態を実現するのに役立ちます。 ライフサイクル管理には、[Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) (B2B) や[テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を含む使用可能な Azure ツールを用いた、テナント間のユーザー プロビジョニング、ユーザー管理、ユーザー プロビジョニング解除が含まれます。

同期要件は、組織固有のニーズに応じて異なります。 組織の要件を満たすソリューションを設計する際には、この記事にある次の考慮事項が最適なオプションの特定に役立ちます。

- テナント間同期
- ディレクトリ オブジェクト
- Microsoft Entra 条件付きアクセス
- 追加のアクセス制御
- オフィス365

### テナント間同期

[テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を使用すると、マルチテナント組織が直面するコラボレーションとアクセスの課題に対処できます。 次の表は、一般的な同期のユース ケースを示しています。 考慮事項が複数のコラボレーション パターンに関連する場合は、テナント間同期とカスタム開発の両方を使用してユース ケースを満たすことができます。

| 使用事例 | テナント間同期 | カスタム開発 |
| --- | --- | --- |
| ユーザー ライフサイクル管理 | [Image: チェック マーク アイコン] | [Image: チェック マーク アイコン] |
| ファイル共有とアプリ アクセス | [Image: チェック マーク アイコン] | [Image: チェック マーク アイコン] |
| ソブリンクラウドとの同期対応 | [Image: チェック マーク アイコン] | [Image: チェック マーク アイコン] |
| リソース テナントから同期を制御する |  | [Image: チェック マーク アイコン] |
| グループ オブジェクトの同期 |  | [Image: チェック マーク アイコン] |
| 同期マネージャーのリンク | [Image: チェック マーク アイコン] | [Image: チェック マーク アイコン] |
| 属性レベルの権限の根拠 |  | [Image: チェック マーク アイコン] |
| Microsoft Entra による Microsoft Windows Server Active Directory への書き戻し |  | [Image: チェック マーク アイコン] |

### ディレクトリ オブジェクトに関する考慮事項

#### 外部ユーザーの招待に使用する UPN と SMTP アドレス

Microsoft Entra B2B では、ユーザーの **UserPrincipalName** (UPN) と、招待を送信するためのプライマリ簡易メール転送プロトコル SMTP (メール) アドレスが一致することを想定しています。 ユーザーの UPN がプライマリ SMTP アドレスと同じ場合、B2B は想定どおりに動作します。 ただし、UPN が外部ユーザーのプライマリ SMTP アドレスと異なる場合、ユーザーが招待を受け入れたときに解決に失敗する場合があります。 ユーザーの実際の UPN がわからない場合、この問題は困難になる場合があります。 B2B の招待を送信するときには、UPN を確認して使用する必要があります。

この記事の「Microsoft Exchange Online」セクションで、外部ユーザーの既定のプライマリ SMTP を変更する方法について説明します。 この手法は、外部向けのすべてのメールと通知を、UPN ではなく実際のプライマリ SMTP アドレスに送信する場合に便利です。 メール フローで UPN がルーティング不可の場合は、この手法が必要になることがあります。

#### 外部ユーザーの UserType の変換

コンソールを使用して外部ユーザー アカウントの招待を手動で作成する場合、ユーザーの種類がゲストに設定されたユーザー オブジェクトが作成されます。 他の手法で招待を作成すると、ユーザーの種類を外部ゲスト アカウント以外に設定できます。 たとえば、API を使用すると、アカウントを外部メンバー アカウントまたは外部ゲスト アカウントのどちらにするかを構成できます。

- [ゲスト機能の制限の一部をなくすことができます](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties#guest-user-permissions)。
- [ゲストアカウントをメンバータイプに変換できます。](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties#can-azure-ad-b2b-users-be-added-as-members-instead-of-guests)

外部ゲスト ユーザーから外部メンバー ユーザー アカウントに変換する場合、Exchange Online による B2B アカウントの処理に問題が発生する可能性があります。 外部メンバー ユーザーとして招待したアカウントのメールを有効にすることはできません。 外部メンバー アカウントのメールを有効にするには、次の最適な方法を使用します。

- 組織間ユーザーを外部ゲスト ユーザー アカウントとして招待します。
- GAL でアカウントを示します。
- UserType を Member に設定します。

この方法を使用すると、アカウントは Exchange Online および Office 365 全体で MailUser オブジェクトとして表示されます。 また、タイミングの問題もあることに注意してください。 Microsoft Entra ユーザーの ShowInAddressList プロパティが Exchange Online PowerShell の HiddenFromAddressListsEnabled プロパティと整合している (互いに逆の値が設定されている) ことをチェックして、ユーザーが GAL に表示されることを確認します。 この記事の「Microsoft Exchange Online」セクションで、表示設定の変更について詳しく説明します。

メンバー ユーザーをゲスト ユーザーに変換できます。これは、内部ユーザーのアクセス許可をゲスト レベルのアクセス許可に制限する場合に役立ちます。 内部ゲスト ユーザーとは、組織の従業員ではないものの、そのユーザーと資格情報を管理するユーザーです。 この方法により、内部ゲスト ユーザーへのライセンス付与を回避できる場合があります。

#### 外部ユーザーまたは外部メンバーの代わりにメール連絡先オブジェクトを使用する場合の問題

従来の GAL 同期を使用して、別のテナントのユーザーを表すことができます。 Microsoft Entra B2B Collaborationを使用するのではなく GAL 同期を行った場合、メール連絡先オブジェクトが作成されます。

- メール連絡先オブジェクトと、メールが有効な外部メンバーまたはゲスト ユーザーは、同じメール アドレスで同じテナントに同時に存在することはできません。
- 招待された外部ユーザーと同じメール アドレスを持つメール連絡先オブジェクトが存在する場合、外部ユーザーは作成されますが、メールは有効になりません。
- 同じメール アドレスを持つ、メールが有効な外部ユーザーが存在する場合、メール連絡先オブジェクトを作成しようとすると、作成時に例外がスローされます。

重要

メール連絡先を使用するには、アクティブ ディレクトリ サービス (AD DS) または Exchange Online PowerShell が必要です。 Microsoft Graph では、連絡先を管理するための API 呼び出しは提供されません。

次の表に、メール連絡先オブジェクトと外部ユーザーの状態の結果を示します。

| 既存の状態 | プロビジョニング シナリオ | 有効な結果 |
| --- | --- | --- |
| 無し | B2B メンバーを招待する | メールが有効でないメンバー ユーザー。 重要な注意をご覧ください。 |
| 無し | B2B ゲストを招待する | メールが有効な外部ユーザー。 |
| メール連絡先オブジェクトが存在する | B2B メンバーを招待する | エラー。 プロキシ アドレスの競合。 |
| メール連絡先オブジェクトが存在する | B2B ゲストを招待する | メール連絡先とメール未対応の外部ユーザー。 重要な注意をご覧ください。 |
| メールが有効な外部ゲスト ユーザー | メール連絡先オブジェクトを作成する | エラー |
| メールが有効な外部メンバー ユーザーが存在する | メール連絡先を作成する | エラー |

Microsoft では、(従来の GAL 同期ではなく) Microsoft Entra B2B コラボレーションを使用して、以下を作成することを推奨しています。

- GAL に表示できる外部ユーザー。
- 既定で GAL に表示されるが、メールは有効になっていない外部メンバー ユーザー。

メール連絡先オブジェクトを使用して GAL にユーザーを表示することもできます。 この方法では、メール連絡先はセキュリティ プリンシパルではないため、他のアクセス許可を付与せずに GAL を統合します。

この推奨アプローチに従って、次を行います。

- ゲスト ユーザーを招待する。
- GAL から非表示を解除する。
- [サインインをブロック](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)してそれらを無効にする。

メール連絡先オブジェクトをユーザー オブジェクトに変換することはできません。 そのため、メール連絡先オブジェクトに関連付けられているプロパティ (グループ メンバーシップやその他のリソース アクセスなど) は転送できません 。 メール連絡先オブジェクトを使用したユーザーの表示には、次の問題があります。

- **Office 365 グループ。** Office 365 グループでは、グループのメンバーになり、グループに関連付けられているコンテンツを操作することが許可されるユーザーの種類を管理するポリシーがサポートされています。 たとえば、グループでゲスト ユーザーの参加が許可されない場合があります。 これらのポリシーでメール連絡先オブジェクトを管理することはできません。
- **Microsoft Entra セルフサービス グループ管理 (SSGM)。** メール連絡先オブジェクトには、SSGM 機能を使用するグループのメンバーになる資格はありません。 ユーザー オブジェクトではなく連絡先として表される受信者を含むグループを管理するには、追加のツールが必要になる場合があります。
- **Microsoft Entra ID ガバナンスとアクセス レビュー。** アクセス レビュー機能を使用して、Office 365 グループのメンバーシップを確認し、証明できます。 アクセス レビューは、ユーザー オブジェクトに基づいています。 メール連絡先オブジェクトで表されるメンバーは、アクセス レビューの対象外です。
- **Microsoft Entra ID ガバナンス、エンタイトルメント管理 (EM)。** EM を使用して、会社の EM ポータルで外部ユーザーに対してセルフサービス アクセス要求を有効にすると、要求時にユーザー オブジェクトが作成されます。 メール連絡先オブジェクトはサポートされていません。

### Microsoft Entra 条件付きアクセスの考慮事項

ユーザーのホーム テナントでのユーザー、デバイス、またはネットワークの状態は、リソース テナントに伝達されません。 そのため、外部ユーザーは、次の制御を使用する条件付きアクセス ポリシーを満たさない可能性があります。

許可されている場合は、[テナント間アクセス設定 (CTAS)](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview) を使用してこの動作をオーバーライドできます。この設定では、ホーム テナントからの多要素認証とデバイスのコンプライアンスが適用されます。

- **多要素認証が必要。** CTAS が構成されていない場合、外部ユーザーは (ホーム テナントで多要素認証が満たされた場合でも) リソース テナントの多要素認証を登録するか、これに応答する必要があります。 このシナリオでは、複数の多要素認証の課題が発生します。 多要素認証証明をリセットする必要がある場合、テナント間の複数の多要素認証証明登録を認識していない可能性があります。 認識がないと、ユーザーはホーム テナントとリソース テナントの一方または両方の管理者に連絡することが必要な場合があります。
- **準拠として登録されたデバイスが必要です。** CTAS が構成されていない場合、デバイス ID はリソース テナントに登録されていないため、外部ユーザーはこの制御を必要とするリソースにアクセスできません。
- **Microsoft Entra ハイブリッド参加済みデバイスが必要です。** CTAS が構成されていない場合、デバイス ID はリソース テナント (またはリソース テナントに接続されているオンプレミスの Active Directory) に登録されていません。 そのため、外部ユーザーは、この制御を必要とするリソースにアクセスできません。
- **承認済みのクライアント アプリまたはアプリ保護ポリシーが必要。** CTAS が構成されていない場合、デバイスの登録も必要であるため、外部ユーザーはリソース テナントの Intune モバイル アプリケーション管理 (MAM) ポリシーを適用できません。 リソース テナントの条件付きアクセス ポリシーでは、この制御を使用しても、ホーム テナントの MAM 保護ではポリシーを満たすことができません。 すべての MAM ベースの条件付きアクセス ポリシーから外部ユーザーを除外してください。

さらに、次の条件付きアクセスの条件も使用できますが、予期しない結果が生じる可能性があるので注意してください。

- **サインイン リスクとユーザー リスク。** ホーム テナントでのユーザーの動作によって、サインイン リスクとユーザー リスクの一部が決まります。 ホーム テナントには、データとリスク スコアが格納されます。 リソース テナント ポリシーによって外部ユーザーがブロックされている場合、リソース テナント管理者はアクセスを有効にできない可能性があります。 「[Microsoft Entra ID 保護 と B2B ユーザー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-b2b)」では、Microsoft Entra ID 保護 が Microsoft Entra ユーザーの資格情報の侵害を検出する仕組みについて説明しています。
- **場所。** リソース テナントのネームド ロケーションの定義によって、ポリシーのスコープが決まります。 ホーム テナントで管理されている信頼できる場所は、ポリシーのスコープで評価されません。 組織がテナント間で信頼できる場所を共有する場合は、リソースと条件付きアクセス ポリシーを定義する各テナントで場所を定義します。

### マルチテナント環境のセキュリティ保護

マルチテナント環境をセキュリティで保護するには、まず、各テナントがセキュリティのベスト プラクティスに準拠していることを確認します。 テナントのセキュリティ保護に関するガイダンスについては、[セキュリティ チェックリスト](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity) と [ベスト プラクティス](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/operational-best-practices) を確認してください。 これらのベスト プラクティスに従っていることを確認し、密接に連携しているテナントと共にそれを確認します。

#### 管理者アカウントを保護し、最小限の権限を確保する

- 管理者の[強力な認証範囲](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-find-coverage-gaps)にギャップがないか見つけて対処する
- [ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/best-practices)と[アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access)の両方の最小限の権限の原則を使用してセキュリティを強化します。 Microsoft Entra ID のタスク別の最小限の権限の[ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task)を確認します。
- [Privileged Identity Management](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity#implement-privilege-access-management) を有効にして、永続的な管理者アクセスを最小限に抑えます。

#### マルチテナント環境を監視する

- [監査ログ UI](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)、[API](https://learn.microsoft.com/ja-jp/graph/api/resources/azure-ad-auditlog-overview)、または [Azure Monitor 統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace) (プロアクティブ アラートの場合) を使用して、テナント間アクセス ポリシーの変更を監視します。 監査イベントでは、カテゴリ "CrossTenantAccessSettings" と "CrossTenantIdentitySyncSettings" が使用されます。これらのカテゴリの監査イベントを監視することで、テナント内のテナント間アクセス ポリシーの変更を特定し、アクションを実行できます。 Azure Monitor でアラートを作成するときに、次のようなクエリを作成して、テナント間アクセス ポリシーの変更を特定できます。

```
AuditLogs
| where Category contains "CrossTenant"
```

- テナント間アクセス設定に追加された新しいパートナーを監視します。

```
AuditLogs
| where OperationName == "Add a partner to cross-tenant access setting"
| where parse_json(tostring(TargetResources[0].modifiedProperties))[0].displayName == "tenantId"
| extend initiating_user=parse_json(tostring(InitiatedBy.user)).userPrincipalName
| extend source_ip=parse_json(tostring(InitiatedBy.user)).ipAddress
| extend target_tenant=parse_json(tostring(TargetResources[0].modifiedProperties))[0].newValue
| project TimeGenerated, OperationName,initiating_user,source_ip, AADTenantId,target_tenant
| project-rename source_tenant= AADTenantId
```

- 同期を許可または禁止するテナント間アクセス ポリシーの変更を監視します。

```
AuditLogs
| where OperationName == "Update a partner cross-tenant identity sync setting"
| extend a = tostring(TargetResources)
| where a contains "true"
| where parse_json(tostring(TargetResources[0].modifiedProperties))[0].newValue contains "true"
```

- [テナント間アクセス アクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-cross-tenant-access-activity) ダッシュボードを使用して、テナント内のアプリケーション アクセスを監視します。 監視により、テナント内のリソースにアクセスしているユーザーと、そのユーザーのアクセス元を確認できます。

#### 既定では拒否します

- アプリケーションにユーザー割り当てを要求します。 アプリケーションで **[ユーザーの割り当てが必要ですか]** プロパティが **[いいえ]** に設定されている場合、外部ユーザーはアプリケーションにアクセスできます。 [Microsoft Entra テナント内の一連のユーザーに Microsoft Entra アプリを制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restrict-your-app-to-a-set-of-users)、Microsoft Entra テナントに登録されているアプリケーションが、認証に成功したテナントのすべてのユーザーが既定でどのように使用できるかについて説明します。
- 外部コラボレーション設定を更新して、"特定の管理者ロールに割り当てられているメンバー ユーザーとユーザーのみが、メンバーアクセス許可を持つゲストを含むゲスト ユーザーを招待できます" ようにします。これにより、テナント内のゲストが他のユーザーを招待できなくなります。
- 信頼度の高いテナントでのみ、MFA を信頼するテナント間同期またはクロステナントアクセスポリシーを有効にしてください。
- 既定のブロック送信ポリシーを作成し、ユーザーが自分の会社の ID を使用して承認されたテナントにゲストとしてサインインすることを許可します。 これにより、テナントの分離と、テナント間の情報のクロス フローが保証されます。
- [テナント制限を使用して、事前に定義されたテナントの一覧への外部ユーザー アクセス](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2)制限します。
- ゲスト アクセス制限が "ゲスト ユーザーはメンバーと同じアクセス権を持つ (ほとんどの場合を含む) " に設定されていないことを確認します。
- [ユーザー フローによるゲスト セルフサービス サインアップを有効にする] が無効になっているかどうかを確認します。 セルフサービス サインアップ フローを使用すると、内部ユーザーからの開始なしで、テナント内にゲスト ID を作成できます。

#### 深層防御

**条件付きアクセス**。

- [アクセス制御ポリシー](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access) を定義して、リソースへのアクセスを制御します。
- 外部ユーザーに留意して、条件付きアクセス ポリシーを設計します。
- すべてのゲスト サインインにサインイン頻度の条件付き Accesspolicy が適用されているかどうかを確認します。サインインの頻度は、最大 24 時間に制限する必要があります。 アンマネージド デバイスからサインインするゲストのトークンは、トークン流出とトークンリプレイ攻撃のリスクが高くなります。 トークンの有効期間を制限すると、このリスクによる露出が減少します。 これにより、トークンが流出した場合でも、脅威アクターの使用期間が制限されます。
- 外部アカウント用に専用の条件付きアクセス ポリシーを作成します。 既存の条件付きアクセス ポリシーで[**すべてのユーザー**の動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/external-id/use-dynamic-groups)条件が組織で使用されている場合、外部ユーザーは**すべてのユーザー**のスコープ内にあるため、このポリシーは外部ユーザーに影響します。

**テナント間アクセス** を管理する

- [エンタイトルメント管理、アクセス レビュー、ライフサイクル ワークフローを使用して、テナント間アクセス](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-governance) 管理します。

**制限付き管理ユニット**

セキュリティ グループを使用してテナント間同期の対象となるユーザーを制御する場合は、セキュリティ グループを変更できるユーザーを制限します。 テナント間同期ジョブに割り当てられているセキュリティ グループの所有者の数を最小限に抑え、そのグループを[制限付き管理ユニット](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-restricted-management)に含めます。 これにより、グループ メンバーを追加または削除し、テナント間でアカウントをプロビジョニングできるユーザーの数が制限されます。

### アクセス制御に関するその他の考慮事項

#### 利用条件

[Microsoft Entra の使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)により、組織でエンド ユーザーに情報を提示するために使うことができる簡単な方法が提供されます。 使用条件を使って、リソースにアクセスする前に使用条件に同意するよう外部ユーザーに要求できます。

#### Microsoft Entra ID P1 または P2 機能でのゲスト ユーザーのライセンスに関する考慮事項

Microsoft Entra 外部 ID の価格は、月間アクティブ ユーザー (MAU) に基づきます。 アクティブ ユーザーの数は、1 か月間に認証アクティビティを使用した一意のユーザーの数です。 「[Microsoft Entra 外部 IDの課金モデル](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)」では、MAU に基づく価格について説明します。

### Office 365 に関する考慮事項

次の情報は、この記事のシナリオのコンテキストで Office 365 に対処しています。 詳細情報は、「[Microsoft 365 テナント間コラボレーション](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/microsoft-365-inter-tenant-collaboration)」で確認できます。 この記事では、ファイルと会話の一元的な場所の使用、予定表の共有、IM の使用、オーディオ/ビデオ通話によるコミュニケーション、リソースとアプリケーションへのアクセスの保護などのオプションについて説明しています。

#### Microsoft Exchange Online

Exchange Online により、外部ユーザー向けの特定の機能が制限されます。 外部ゲスト ユーザーではなく外部メンバー ユーザーを作成すると、この制限を減らすことができます。 外部ユーザーのサポートには、次の制限があります。

- 外部ユーザーに Exchange Online ライセンスを割り当てることができます。 ただし、Exchange Online のトークンを発行することはできません。 したがって、リソースにはアクセスできません。
    - 外部ユーザーは、リソース テナント内の共有された、またはデリゲートされた Exchange Online メールボックスを使用できません。
    - 共有メールボックスに外部ユーザーを割り当てることはできますが、外部ユーザーは共有メールボックスにアクセスできません。
- 外部ユーザーを GAL に含めるには、再表示する必要があります。 既定では、非表示になっています。
    - 非表示の外部ユーザーは、招待時に作成されます。 この作成は、ユーザーが招待を引き換えたかどうかとは無関係です。 そのため、すべての外部ユーザーの非表示が解除された場合、一覧には招待を引き換えていない外部ユーザーのユーザー オブジェクトが含まれます。 シナリオによっては、オブジェクトを一覧に含める必要がある場合と、ない場合があります。
    - 外部ユーザーは、[Exchange Online PowerShell](https://learn.microsoft.com/ja-jp/powershell/exchange/exchange-online-powershell-v2) を使って再表示される可能性があります。 [Set-MailUser](https://learn.microsoft.com/ja-jp/powershell/module/exchange/set-mailuser) PowerShell コマンドレットを実行して、**HiddenFromAddressListsEnabled** プロパティの値を **$false** に設定できます。

次に例を示します。

`Set-MailUser [ExternalUserUPN] -HiddenFromAddressListsEnabled:\$false\`

ここで、**ExternalUserUPN** は計算された **UserPrincipalName** です。

次に例を示します。

`Set-MailUser externaluser1_contoso.com#EXT#@fabrikam.onmicrosoft.com\ -HiddenFromAddressListsEnabled:\$false`

外部ユーザーは、Microsoft 365 管理センターで、非表示ではなくなる可能性があります。

- Exchange 固有のプロパティ (**PrimarySmtpAddress**、**ExternalEmailAddress**、**EmailAddresses**、**MailTip** など) の更新は、[Exchange Online PowerShell](https://learn.microsoft.com/ja-jp/powershell/exchange/exchange-online-powershell-v2) でのみ設定できます。 Exchange Online 管理センターでは、グラフィカル ユーザー インターフェイス (GUI) を使って属性を変更することはできません。

例に示すように、メール固有のプロパティに [Set-MailUser](https://learn.microsoft.com/ja-jp/powershell/module/exchange/set-mailuser) PowerShell コマンドレットを使用できます。 [Set-User](https://learn.microsoft.com/ja-jp/powershell/module/exchange/set-user) PowerShell コマンドレットで変更できるユーザー プロパティもあります。 ほとんどのプロパティは Microsoft Graph API で変更できます。

**Set-MailUser** の最も便利な機能の 1 つは、**EmailAddresses** プロパティを操作できることです。 この複数値属性には、外部ユーザーの複数のプロキシ アドレス (SMTP、X500、セッション開始プロトコル (SIP) など) が含まれる場合があります。 外部ユーザーには既定で、**UserPrincipalName** (UPN) に関連付けられたプライマリ SMTP アドレスがスタンプされます。 プライマリ SMTP の変更または SMTP アドレスの追加を行う場合は、このプロパティを設定できます。 Exchange 管理センターは使用できません。Exchange Online PowerShell を使用する必要があります。 「[Exchange Online 内のメールボックスのメール アドレスを追加または削除する](https://learn.microsoft.com/ja-jp/exchange/recipients-in-exchange-online/manage-user-mailboxes/add-or-remove-email-addresses)」では、**EmailAddresses** などの複数値プロパティを変更するさまざまな方法について説明しています。

#### Microsoft 365 の Microsoft SharePoint

Microsoft 365 の SharePoint Online には、Microsoft Entra テナントでユーザー (内部または外部) の種類がメンバーになっているかゲストになっているかに応じて、独自のサービス固有のアクセス許可があります。 「[Microsoft 365 の外部共有と Microsoft Entra B2B Collaboration](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)」では、SharePoint および OneDrive との統合を有効にして、組織の外部のユーザーとファイル、フォルダー、リスト アイテム、ドキュメント ライブラリ、サイトを共有する方法について説明しています。 Microsoft 365 は、認証と管理に Azure B2B を使用しているときにこれを行います。

Microsoft 365 の SharePoint で外部共有を有効にすると、Microsoft 365 のSharePoint のユーザー ピッカーでゲスト ユーザーを検索する機能は既定では **オフ** になります。 この設定のため、Exchange Online の GAL から非表示にされているゲスト ユーザーを検出することはできません。 ゲスト ユーザーを表示するには 2 つの方法があります (相互に排他的ではありません)。

- ゲスト ユーザーを検索する機能を有効にする方法は次のとおりです。
    - テナントとサイト コレクション レベルで **ShowPeoplePickerSuggestionsForGuestUsers** 設定を変更します。
    - [Set-SPOTenant](https://learn.microsoft.com/ja-jp/powershell/module/sharepoint-online/set-spotenant) および [Set-SPOSite](https://learn.microsoft.com/ja-jp/powershell/module/sharepoint-online/set-sposite) の [Microsoft 365 の SharePoint PowerShell](https://learn.microsoft.com/ja-jp/powershell/sharepoint/sharepoint-online/connect-sharepoint-online) コマンドレットを使用して機能を設定します。
- Exchange Online の GAL に表示されるゲスト ユーザーは、Microsoft 365 の SharePoint のユーザー ピッカーにも表示されます。 アカウントは、**ShowPeoplePickerSuggestionsForGuestUsers** の設定に関係なく表示されます。

#### Microsoft Teams

Microsoft Teams には、ユーザーの種類に基づいてアクセスを制限する機能があります。 ユーザーの種類を変更すると、コンテンツへのアクセスと使用できる機能に影響する可能性があります。 Microsoft Teams では、ホーム テナントの外部で Teams を使用するときに、ユーザーは Teams クライアントのテナント切り替えメカニズムを使用してコンテキストを変更する必要があります。

Microsoft Teams のテナント切り替えメカニズムでは、ホームテナント以外の Teams で作業を行う場合、ユーザーが Teams クライアントのコンテキストを手動で切り替えることが必要になる場合があります。

Teams フェデレーションを使用すると、別のまったく外部のドメインの Teams ユーザーが、内部のユーザーとの検索、通話、チャット、会議の設定をできるようにすることが可能です。 「[Microsoft ID を使用して外部会議を管理し、人や組織とチャットする](https://learn.microsoft.com/ja-jp/microsoftteams/trusted-organizations-external-meetings-chat)」では、組織内のユーザーが、ID プロバイダーとして Microsoft を使用している組織外のユーザーとチャットや会議を行えるようにする方法について説明しています。

#### Teams でのゲスト ユーザーのライセンスに関する考慮事項

Office 365 ワークロードで Azure B2B を使用する場合、ゲスト ユーザー (内部または外部) のエクスペリエンスがメンバー ユーザーの場合と異なる例も重要な考慮事項に含まれます。

- **Microsoft グループ。**「[Office 365 グループへのゲストの追加](https://support.office.com/article/adding-guests-to-office-365-groups-bfc7a840-868f-4fd6-a390-f347bf51aff6)」では、Microsoft 365 グループのゲスト アクセスを使用して、グループの会話、ファイル、予定表の招待状、グループのノートブックへのアクセス権を組織外の人に付与し、自分やチームが外部の人と共同作業できるようにする方法について説明しています。
- **Microsoft Teams。**「[Teams のチーム所有者、メンバー、およびゲスト機能](https://support.office.com/article/team-owner-member-and-guest-capabilities-in-teams-d03fdf5b-1a6e-48e4-8e07-b13e1350ec7b)」では、Microsoft Teams でのゲスト アカウントのエクスペリエンスについて説明しています。 外部メンバー ユーザーを使用することで、Teams の完全に忠実なエクスペリエンスを有効にできます。
    - 商用クラウド内の複数のテナントの場合、ホーム テナントでのライセンスを持つユーザーは、同じ法人内の別のテナントのリソースにアクセスできます。 アクセス権は外部メンバーの設定を使用して、追加のライセンス料金なしで付与することができます。 この設定は、Teams および Groups での SharePoint および OneDrive に適用されます。
    - 他の Microsoft クラウド内の複数のテナントおよび異なるクラウド内の複数のテナントでは、B2B メンバー ライセンス チェックはまだ使用できません。 Teams での B2B メンバーの使用には、B2B メンバーごとに追加のライセンスが必要です。 この要件は、Power BI などの他のワークロードにも影響する場合があります。
    - 同じ法人に属していないテナントに対する B2B メンバーの使用には、追加のライセンス要件が適用されます。
- **Identity Governance の機能。** エンタイトルメント管理とアクセス レビューでは、外部ユーザーに他のライセンスが必要になる場合があります。
- **その他の製品。** Dynamics 顧客関係管理 (CRM) などの製品では、ユーザーが存在するテナントごとにライセンスが必要になる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multi-tenant-common-solutions"} -->
## Microsoft Entra ID でのマルチテナント ユーザー管理の一般的なソリューション - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-solutions
- Service: entra / architecture
- Article date: 2023-04-19
- Summary: ゲスト アカウントを使用して、Microsoft Entra テナント間でユーザー アクセスを構成するために使用する一般的なソリューションについて学習する

この記事は、Microsoft Entra マルチテナント環境でのユーザー ライフサイクル管理の構成と提供に関するガイダンスを提供する一連の記事の 4 番目です。 シリーズ記事の次回以降は、下記のとおり、さらに詳しい情報について説明します。

- [マルチテナント ユーザー管理の概要](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-introduction) は、シリーズの最初です。
- 「[マルチテナント ユーザー管理のシナリオ](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-scenarios)」では、マルチテナント ユーザー管理機能を使用できる 3 つのシナリオ (エンド ユーザーによる実施、スクリプト化、自動化) について説明します。
- [マルチテナント ユーザー管理に関する一般的な考慮事項](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-considerations) は、テナント間同期、ディレクトリ オブジェクト、Microsoft Entra 条件付きアクセス、追加のアクセス制御、Office 365 に関する考慮事項に関するガイダンスを提供します。

このガイダンスは、ユーザー ライフサイクル管理で整合性のとれた状態を実現するのに役立ちます。 ライフサイクル管理には、 [Microsoft Entra B2B コラボレーション (B2B](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) ) と [テナント間の同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を含む使用可能な Azure ツールを使用して、テナント間でのユーザーのプロビジョニング、管理、プロビジョニング解除が含まれます。

Microsoft では、できる限りシングル テナントを選ぶことをお勧めしています。 ご自分のシナリオでシングル テナントが機能しない場合は、Microsoft のお客様がこれらの課題に対して正常に実装した次のソリューションを参照してください。

- テナント間での自動ユーザー ライフサイクル管理とリソース割り当て
- テナント間でのオンプレミス アプリの共有

### テナント間での自動ユーザー ライフサイクル管理とリソース割り当て

以前は密接なビジネス関係を持っていた競合他社をお客様が獲得するとします。 各組織は、会社の ID を維持したいと考えています。

#### 現在の状態

現在、組織は互いにメール連絡先オブジェクトとしてユーザーを同期し、互いにディレクトリに表示しています。 各リソース テナントでは、他のテナント内のすべてのユーザーに対してメール連絡先オブジェクトが有効になっています。 テナント間では、アプリケーションにアクセスできません。

#### 目標

お客様には次の目標があります。

- 各組織の GAL にすべてのユーザーを表示する。
    - ホーム テナントのユーザー アカウント ライフサイクルの変更は、リソース テナント GAL に自動的に反映されます。
    - ホーム テナント (部署、名前、簡易メール転送プロトコル (SMTP) アドレスなど) の属性の変更は、リソース テナント GAL とホーム GAL に自動的に反映されます。
- ユーザーは、リソース テナント内のアプリケーションとリソースにアクセスできます。
- ユーザーは、リソースへのアクセス要求をセルフサービスで実行できます。

#### ソリューション アーキテクチャ

組織は、ポイント対ポイント アーキテクチャを、Microsoft Identity Manager (MIM) などの同期エンジンで使用します。 次の図は、このソリューションのポイント対ポイント アーキテクチャの例を示しています。

[!\[ポイント対ポイント アーキテクチャ ソリューションを示す図。\](media/multi-tenant-common-solutions/diagram-point-to-point-sync-inline.png)
 図のタイトル: ポイント対ポイント アーキテクチャ ソリューション。 左側の \[A 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 右側の \[B 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 A 社と B 社の間で、A 社から B 社、および B 社から A 社に対して同期エンジンの相互作用が行われています。](https://learn.microsoft.com/ja-jp/entra/architecture/media/multi-tenant-common-solutions/diagram-point-to-point-sync-expanded.png#lightbox)

各テナント管理者は、ユーザー オブジェクトを作成するために次の手順を実行します。

1. ユーザー データベースが最新の情報に更新されている必要があります。
2. [MIM をデプロイして構成する](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-deploy)。
    1. 既存の連絡先オブジェクトにアドレスを指定します。
    2. 他のテナントの内部メンバー ユーザーに対して、外部メンバー ユーザー オブジェクトを作成します。
    3. ユーザー オブジェクト属性を同期します。
3. [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)アクセス パッケージをデプロイして構成します。
    1. 共有するリソース。
    2. 有効期限とアクセス レビュー ポリシー。

### テナント間でのオンプレミス アプリの共有

複数のピア組織を持つお客様は、テナントの 1 つからオンプレミスのアプリケーションを共有する必要があります。

#### 現在の状態

ピア組織がメッシュ トポロジで外部ユーザーを同期し、テナント間でクラウド アプリケーションへのリソース割り当てを有効にします。 お客様は、次の機能を提供しています。

- Microsoft Entra ID でのアプリケーションの共有。
- リソーステナントにおけるユーザーライフサイクルの自動管理（ホームテナント上での追加、変更、削除の反映）

次の図は、A 社の内部ユーザーのみが A 社のオンプレミス アプリにアクセスするという、このシナリオを示しています。

[!\[図はメッシュ トポロジを示しています。\](media/multi-tenant-user-management-scenarios/diagram-mesh-topology-inline.png)
 図のタイトル: メッシュ トポロジ。 左上の \[A 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 右上の \[B 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 左下の \[C 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 右下の \[D 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 A 社と B 社と C 社と D 社の間では、左側の会社と右側の会社の間で同期エンジンの相互作用が行われています。](https://learn.microsoft.com/ja-jp/entra/architecture/media/multi-tenant-user-management-scenarios/diagram-mesh-topology-expanded.png#lightbox)

#### 目標

現在の機能と共に、次の機能を提供したいと考えています。

- 外部ユーザーに対して、A 社のオンプレミス リソースへのアクセスを提供する。
- セキュリティ アサーション マークアップ言語 (SAML) 認証を使用するアプリ。
- 統合 Windows 認証と Kerberos 認証を使用するアプリ。

#### ソリューション アーキテクチャ

A 社は、次の図に示すように、Azure アプリケーション プロキシを使用して、独自の内部ユーザーに対してオンプレミス アプリにシングル サインオン (SSO) を提供します。

[Image: アプリケーション アクセスの例を示す図。]

 図のタイトル: Azure アプリケーション プロキシ アーキテクチャ ソリューション。 左上には、"https://sales.constoso.com" というラベルの付いたボックスに、Web サイトを表す地球アイコンが含まれています。 その下には、ユーザーを表すアイコンのグループが表示され、ユーザーから Web サイトへ矢印で接続されています。 右上の [Microsoft Entra ID] というラベルのクラウドのシェイプには、[アプリケーション プロキシ サービス] というラベルのアイコンが含まれています。 Web サイトからクラウド シェイプへ矢印で接続されています。 右下の [DMZ] というラベルのボックスには、[オンプレミス] というサブタイトルが付いています。 クラウド シェイプから [DMZ] ボックスへ矢印で接続され、[コネクタ] というラベルのアイコンを指すように 2 つに分かれています。 左側の [コネクタ] アイコンの下では、矢印が下向きになり、[アプリ 1] と [アプリ 2] というラベルのアイコンを指すように 2 つに分かれています。 右側の [コネクタ] アイコンの下では、矢印が下向きになり、[アプリ 3] というラベルのアイコンを指しています。

テナント A の管理者は、次の手順を実行して、外部ユーザーが同じオンプレミス アプリケーションにアクセスできるようにします。

1. [SAML アプリへのアクセスを構成します](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises#access-to-saml-apps)。
2. [他のアプリケーションに対するアクセスを構成します](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises#access-to-iwa-and-kcd-apps)。
3. [MIM](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises#create-b2b-guest-user-objects-through-mim) または [PowerShell](https://www.microsoft.com/download/details.aspx?id=51495) を使用して、オンプレミス ユーザーを作成します。

次の記事では、B2B コラボレーションに関する追加の情報が提供されています。

- [Microsoft Entra ID の B2B ユーザーに対するオンプレミス リソースへのアクセス権の付与](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises)に関する記事では、B2B ユーザーにオンプレミス アプリへのアクセス権を提供する方法について説明します。
- 「[Microsoft Entra のハイブリッド組織向けの B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-organizations)」では、外部パートナーに組織内のアプリやリソースへのアクセス権を付与する方法について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multi-tenant-user-management-introduction"} -->
## Microsoft Entra ID でのマルチテナント ユーザー管理の構成 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-introduction
- Service: entra / architecture
- Article date: 2023-04-19
- Summary: ゲスト アカウントを使用して Microsoft Entra テナント全体でユーザー アクセスを構成するために使用されるさまざまなパターンについて学習する

この記事は、Microsoft Entra マルチテナント環境でユーザー ライフサイクル管理を構成および提供するためのガイダンスを説明するシリーズ記事の第 1 回目です。 シリーズ記事の次回以降は、下記のとおり、さらに詳しい情報について説明します。

- 「[マルチテナント ユーザー管理のシナリオ](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-scenarios)」では、マルチテナント ユーザー管理機能を使用できる 3 つのシナリオ (エンド ユーザーによる実施、スクリプト化、自動化) について説明します。
- 「[マルチテナント ユーザー管理の一般的な考慮事項](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-considerations)」では、テナント間同期、ディレクトリ オブジェクト、Microsoft Entra 条件付きアクセス、追加のアクセス制御、Office 365 に関する考慮事項のガイダンスを提供します。
- 「[マルチテナント ユーザー管理の一般的なソリューション](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-solutions)」 (シングル テナントでは要件を満たせない場合) では、テナント間の自動ユーザー ライフサイクル管理とリソースの割り当てに加え、テナント間のオンプレミス アプリの共有に関する課題のガイダンスを説明します。

このガイダンスは、ユーザー ライフサイクル管理で整合性のとれた状態を実現するのに役立ちます。 ライフサイクル管理には、[Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) (B2B) や[テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を含む使用可能な Azure ツールを用いた、テナント間のユーザー プロビジョニング、ユーザー管理、ユーザー プロビジョニング解除が含まれます。

複数のユーザーを 1 つの Microsoft Entra テナントにプロビジョニングすると、リソースをまとめて閲覧したり、ポリシーと管理をそれぞれ 1 つにまとめたりできます。 この方法により、一貫性のあるユーザー ライフサイクル管理ができます。

Microsoft では、できる限りシングル テナントを選ぶことをお勧めしています。 マルチテナントを導入すると、テナント間のコラボレーションや管理について、独自の要件が発生する場合があります。 1 つの Microsoft Entra テナントへの統合ができない場合、マルチテナント組織は次のような理由で 2 つ以上の Microsoft Entra テナントに広がることがあります。

- 合併
- 取得
- 売却
- パブリック クラウド、ソブリン クラウド、リージョン クラウド間のコラボレーション
- 1 つの Microsoft Entra テナントへの統合を妨げる、政治的または組織的な構造

### Microsoft Entra B2B コラボレーション

Microsoft Entra B2B コラボレーション (B2B) を使用すると、会社のアプリケーションとサービスを、外部ユーザーと安全に共有できます。 ユーザーがどの組織からでもアクセスできる場合、B2B は IT 環境とデータへのアクセス制御の維持に役立ちます。

B2B コラボレーションを使用すると、外部アクセスを自社組織のユーザーに付与することで、管理対象のマルチテナントにアクセスすることができるようになります。 従来から、B2B 外部ユーザー アクセスは、自社組織が管理しないユーザーにアクセスを認可することができます。 しかし、外部ユーザー アクセスは、自社組織が管理するマルチテナント間のアクセスも管理できます。

Microsoft Entra B2B コラボレーションで混乱が生じる領域は、[B2B ゲスト ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)に関するものです。 内部ユーザー アカウントと外部ユーザー アカウントの違いとメンバー ユーザー タイプとゲスト ユーザー タイプの違いが混乱のもとになっています。 最初は、すべての内部ユーザーは、**UserType** 属性に *Member* (メンバー ユーザー) が設定されたメンバー ユーザーです。 内部ユーザーは、権限のある Microsoft Entra ID にアカウントを持ち、ユーザーが存在するテナントに認証されます。 メンバー ユーザーは、テナントの既定[メンバーレベル アクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) を持つ、ライセンス ユーザーです。 メンバー ユーザーは自社組織の従業員として扱われます。

あるテナントの内部ユーザーを、別のテナントに外部ユーザーとして招待できます。 外部ユーザーは、外部 Microsoft Entra アカウント、ソーシャル ID、またはその他外部 ID プロバイダーを使用してサインインします。 外部ユーザーは、外部ユーザーを招待するテナントの外部で認証されます。 B2B の最初のリリースでは、すべての外部ユーザーの **UserType** は *Guest* (ゲスト ユーザー) でした。 ゲスト ユーザーは、テナント内の [アクセス許可が制限されています](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)。 たとえば、ゲスト ユーザーは、テナント ディレクトリ内のすべてのユーザーもすべてのグループも一覧表示できません。

B2B は、内部ユーザーでも外部ユーザーでも、ユーザーの **UserType** プロパティに対する変更 (メンバーからゲストへの変更や、その逆もしかり) をサポートしているため、混乱のもとになっています。

内部ユーザーをメンバー ユーザーからゲスト ユーザーに変更できます。 たとえば、テナントのゲスト レベルのアクセス許可を持つ、ライセンスのない内部ゲスト ユーザーを設定できます。これは、自社組織の従業員ではない人にユーザー アカウントと資格情報を付与する場合に便利です。

外部ユーザーをゲスト ユーザーからメンバー ユーザーに変更し、その外部ユーザーにメンバーレベルのアクセス許可を付与できます。 この変更は、自社組織でマルチテナントを管理しており、あるユーザーにすべてのテナントのメンバーレベル アクセス許可を付与する必要がある場合に便利です。 このニーズは、ユーザーが特定テナントの内部ユーザーか外部ユーザーかに関係なく発生することがあります。 メンバー ユーザーにはより多くの[ライセンス](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)が必要なことがあります。

B2B に関するほとんどのドキュメントは、外部ユーザーのことをゲスト ユーザーと呼んでいます。 これは、**UserType** プロパティを混同して、すべてのゲスト ユーザーは外部ユーザーであると想定しています。 ドキュメントにゲスト ユーザーの記述がある場合、それは外部ゲスト ユーザーのことを指しています。 この記事では、明確かつ意図的に外部ユーザーと内部ユーザー、メンバー ユーザーとゲスト ユーザーを区別して説明しています。

### テナント間同期

[テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を使用すると、マルチテナント組織は既存の B2B 外部コラボレーション機能を活用して、エンド ユーザーにシームレスなアクセスとコラボレーションのエクスペリエンスを提供できます。 この機能は、Microsoft ソブリン クラウド間のテナント間同期を許可していません (Microsoft 365 米国政府 GCC High、DOD または Office 365 中国など) 。 自動およびカスタムのテナント間同期シナリオのヘルプについては、「[マルチテナント ユーザー管理に関する一般的な考慮事項](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-considerations#cross-tenant-synchronization)」をご覧ください。

Microsoft Entra ID テナント間同期機能について、Arvind Harinder による説明をご覧ください (以下埋め込み)。

次の概念と操作方法に関する記事では、Microsoft Entra B2B コラボレーションとテナント間同期の情報について説明します。

#### 概念に関する記事

- [B2B のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-fundamentals): ユーザーと管理者に、とてもスムーズなエクスペリエンスを提供するレコメンデーションをまとめています。
- [B2B と Office 365 の外部共有](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b): B2B、Office 365、SharePoint/OneDrive で、リソースを共有する場合の類似点と相違点を説明します。
- [Microsoft Entra B2B コラボレーション ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties): Microsoft Entra ID 外部ユーザー オブジェクトのプロパティと状態について説明します。 招待の引き換え前後の作業を詳しく説明しています。
- 「[B2B 用の条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)」では、外部ユーザー向けの条件付きアクセスと多要素認証のしくみについて説明します。
- [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)を使うと、外部の Microsoft Entra 組織があなたと共同作業する方法 (受信アクセス) と、あなたのユーザーが外部の Microsoft Entra 組織と共同作業する方法 (送信アクセス) をきめ細かく制御できます。
- [テナント間同期の概要](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview): 組織のテナント間で、Microsoft Entra B2B コラボレーション ユーザーを自動的に作成、更新、削除する方法について説明します。

#### ハウツー記事

- [PowerShell を使用して Microsoft Entra B2B コラボレーション ユーザーを一括で招待する](https://learn.microsoft.com/ja-jp/entra/external-id/bulk-invite-powershell): PowerShell を使用して外部ユーザーに一括招待を送信する方法について説明します。
- 「[B2B ゲスト ユーザーに多要素認証を適用する](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-tutorial-require-mfa)」では、条件付きアクセスと多要素認証のポリシーを使用して、テナント、アプリ、または個々の外部ユーザーの認証レベルを適用する方法について説明します。
- 「[電子メールの ワンタイム パスコード認証](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)」では、Microsoft Entra ID、Microsoft アカウント (MSA)、Google フェデレーションなどの他の方法で認証できない場合に、電子メールのワンタイム パスコード機能で外部ユーザーを認証する方法について説明します。

### 用語

Microsoft コンテンツの次の用語は、Microsoft Entra ID のマルチテナント コラボレーションに関するものです。

- **リソース テナント:** ユーザーが他のユーザーと共有するリソースが存在する Microsoft Entra テナント。
- **ホーム テナント:** リソース テナントのリソースへのアクセスを必要とするユーザーが存在する Microsoft Entra テナント。
- **内部ユーザー:** 権限のあるアカウントを持ち、そのユーザーが存在するテナントに認証されます。
- **外部ユーザー:** 外部 Microsoft Entra アカウント、ソーシャル ID、またはその他外部 ID プロバイダーを使用してサインインします。 外部ユーザーは、その外部ユーザーを招待したテナントの外部で認証されます。
- **メンバー ユーザー:** 内部または外部メンバー ユーザーは、テナントの既定メンバーレベル アクセス許可を持つライセンス ユーザーです。 メンバー ユーザーは自社組織の従業員として扱われます。
- **ゲスト ユーザー:** 内部ゲスト ユーザーまたは外部ゲスト ユーザーは、テナントの制限されたアクセス許可を持っています。 ゲスト ユーザーは、自社組織の従業員ではありません (パートナーのユーザーなど)。 B2B のほとんどのドキュメントで B2B ゲストと言及している箇所は、正確には外部ゲスト ユーザー アカウントを指しています。
- **ユーザー ライフサイクル管理:** リソースに対するユーザー アクセスのプロビジョニング、管理、プロビジョニング解除を行うプロセスです。
- **統一 GAL:** 各テナントの各ユーザーは、グローバル アドレス リスト (GAL) に存在する各組織のユーザーを見ることができます。

### 要件を満たす方法を決める

組織独自の要件に応じて、テナント間のユーザー管理方法は影響を受けます。 効果的な戦略を策定するには、次の要件を検討します。

- テナントの数
- 組織の種類
- 現在のトポロジ
- ユーザー同期に関する具体的要件

#### 一般的な要件

組織では、すぐに実現したいコラボレーションの要件にまず取り組みます。 "Day One (初日)" の要件と呼ばれることもありますが、事業価値を生み出す業務を妨げることなく、エンド ユーザーをスムーズに統合することに焦点を当てています。 Day One と管理の要件を定義する際は、次の要件とニーズを含めて検討します。

#### コミュニケーションの要件

- **統一グローバル アドレス リスト:** 各ユーザーが、ホーム テナントの GAL で、他のすべてのユーザーを見ることができます。
- **空き時間情報:** ユーザーがお互いの空き時間を把握できるようにします。 これは、[Exchange Online の組織の関係](https://learn.microsoft.com/ja-jp/exchange/sharing/organization-relationships/create-an-organization-relationship)で実現できます。
- **チャットと在席確認:** 他のユーザーが在席しているかどうかを確認して、インスタント メッセージを送れるようにします。 これは、[Microsoft Teams の外部アクセス](https://learn.microsoft.com/ja-jp/microsoftteams/trusted-organizations-external-meetings-chat)で設定できます。
- **会議室などのリソース予約:** ユーザーが組織全体で会議室やその他のリソースを予約できるようにします。 テナント間の会議室予約は現在 Exchange Online では使用できません。
- **単一電子メール ドメイン:** すべてのユーザーが、単一の電子メール ドメインから電子メールを送受信できるようにします (`users@contoso.com` など)。 送信には、電子メール アドレス書き換えソリューションが必要です。

#### アクセス要件

- **ドキュメント アクセス:** ユーザーが SharePoint、OneDrive、Teams でドキュメントを共有できるようにします。
- **管理:** 管理者が、マルチテナントにデプロイされたサブスクリプションとサービスの構成を管理できるようにします。
- **アプリケーション アクセス:** エンド ユーザーが、組織全体のアプリケーションにアクセスできるようにします。
- **シングル サインオン:** ユーザーが、追加のサインイン情報を入力せずに、組織全体のリソースにアクセスできるようにします。

#### アカウント作成の方法

外部ユーザー アカウントのライフサイクルを作成および管理する Microsoft のメカニズムは、3 つの一般的なパターンに従います。 それらのパターンを参考にすると、要件の定義や実装に役立ちます。 シナリオに最適なパターンを選択し、それからパターンの詳細に焦点を当てます。

| メカニズム | 説明 | 最適な条件 |
| --- | --- | --- |
| [エンド ユーザーによる実施](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-scenarios#end-user-initiated-scenario) | 外部ユーザーを、テナント、アプリ、リソースに招待する権限を、リソース テナント管理者がリソース テナント内のユーザーに委任します。 ホーム テナントからユーザーを招待する、またはユーザーが個別にサインアップすることができます。 | Day One (初日) に統一グローバル アドレス リストが不要。 |
| [スクリプト化](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-scenarios#scripted-scenario) | リソース テナント管理者が "プル" プロセスのスクリプトをデプロイすることで、外部ユーザーの検出とプロビジョニングを自動化し、共有のシナリオに対応します。 | テナントが少ない (2 つなど)。 |
| [自動](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-scenarios#automated-scenario) | リソース テナント管理者が、ID プロビジョニング システムを使用して、プロセスのプロビジョニングとプロビジョニング解除を自動化します。 | テナント間の統一グローバル アドレス リストが必要。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multi-tenant-user-management-scenarios"} -->
## Microsoft Entra ID でマルチテナント ユーザー管理を使用する一般的なシナリオ - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-scenarios
- Service: entra / architecture
- Article date: 2024-08-25
- Summary: Microsoft Entra テナント間のユーザー アクセスをゲスト アカウントを使用して構成する一般的なシナリオについて説明します

この記事は、Microsoft Entra マルチテナント環境でユーザー ライフサイクル管理を構成、提供するためのガイダンスを提供するシリーズ記事の第 2 回目です。 シリーズ記事の次回以降は、下記のとおり、さらに詳しい情報について説明します。

- [マルチテナント ユーザー管理の概要](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-introduction) は、Microsoft Entra マルチテナント環境でユーザー ライフサイクル管理を構成および提供するためのガイダンスを提供する一連の記事の最初です。
- [マルチテナント ユーザー管理に関する一般的な考慮事項](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-considerations) は、テナント間同期、ディレクトリ オブジェクト、Microsoft Entra 条件付きアクセス、追加のアクセス制御、Office 365 に関する考慮事項に関するガイダンスを提供します。
- シングル テナントがシナリオで機能しない場合の[マルチテナント ユーザー管理の一般的なソリューション](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-solutions)。この記事では、テナント間での自動ユーザー ライフサイクル管理とリソース割り当て、テナント間でのオンプレミス アプリの共有という課題に関するガイダンスを提供します。

このガイダンスは、ユーザー ライフサイクル管理で整合性のとれた状態を実現するのに役立ちます。 ライフサイクル管理には、 [Microsoft Entra B2B コラボレーション (B2B](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) ) と [テナント間の同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を含む使用可能な Azure ツールを使用して、テナント間でのユーザーのプロビジョニング、管理、プロビジョニング解除が含まれます。

この記事では、マルチテナント ユーザー管理機能を使用できる 3 つのシナリオについて説明します。

- エンドユーザーによる開始
- スクリプト化
- 自動化

### エンドユーザー主導シナリオ

エンドユーザー開始シナリオでは、リソース テナント管理者は、テナント内のユーザーに特定の機能を委任します。 エンド ユーザーには、テナント、アプリ、リソースに外部ユーザーを招待する権限が管理者から与えられます。 ホーム テナントからユーザーを招待することも、個別にサインアップすることもできます。

たとえば、あるグローバル プロフェッショナル サービス企業が、プロジェクトで下請業者と共同作業を行います。 下請業者 (外部ユーザー) は、元請け企業のアプリケーションやドキュメントにアクセスする必要があります。 企業の管理者は、下請業者を招待したり、下請業者のリソース アクセス用にセルフ サービスを構成したりする機能をエンド ユーザーに委任できます。

#### アカウントのプロビジョニング

次に示すのは、テナント リソースにアクセスするためにエンド ユーザーを招待するときに最も広く使用されている方法です。

- [**アプリケーション ベースの招待。**](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) Microsoft アプリケーション (Teams や SharePoint など) は、外部ユーザーの招待を有効にすることができます。 Microsoft Entra B2B および関連するアプリケーションの両方で、B2B の招待設定を構成します。
- [**MyApps。**](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview) ユーザーは、MyApps を使用して外部ユーザーをアプリケーションに招待して割り当てることができます。 ユーザー アカウントには [、アプリケーションのセルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access) 承認者のアクセス許可が必要です。 グループ所有者は、外部ユーザーを自分のグループに招待できます。
- [**エンタイトルメント管理。**](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) 管理者またはリソース所有者が、リソース、許可された外部組織、外部ユーザーの有効期限、アクセス ポリシーを含むアクセス パッケージを作成できるようにします。 アクセス パッケージを発行して、外部ユーザーがリソース アクセスのセルフサービス サインアップを実行できるようにします。
- [**Azure portal。**](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)[ゲスト招待者ロール](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)を持つエンド ユーザーは、Azure portal にサインインし、Microsoft Entra ID の [**ユーザー**] メニューから外部ユーザーを招待できます。
- [**プログラム (PowerShell、Graph API)。**](https://learn.microsoft.com/ja-jp/entra/external-id/customize-invitation-api)[ゲスト招待者ロール](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)を持つエンド ユーザーは、PowerShell または Graph API を使用して外部ユーザーを招待できます。

#### 招待の引き換え

リソースにアクセスするためのアカウントをプロビジョニングすると、招待されたユーザーのメール アドレスに招待メールが送信されます。

招待されたユーザーは招待を受け取ったら、メールに含まれている引き換え URL のリンク先を参照できます。 その際、招待されたユーザーは招待を承認または拒否し、必要に応じて外部ユーザー アカウントを作成できます。

次のいずれかのシナリオに該当する場合、招待されたユーザーは、Just-In-Time (JIT) 引き換えと呼ばれる、リソースへの直接アクセスを試みることもできます。

- 招待されたユーザーが既に Microsoft Entra ID または Microsoft アカウントを持っている。または
- 管理者は [、電子メールワンタイム パスコードを有効にしています](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)。

JIT 引き換え中、次の考慮事項が適用される場合があります。

- 管理者が [同意プロンプトを抑制](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)していない場合、ユーザーはリソースにアクセスする前に同意する必要があります。
- 許可リストまたはブロックリストを使用して、特定の組織の外部ユーザーへの招待 [を許可またはブロックできます](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)。

詳細については、「 [Microsoft Entra B2B コラボレーションの招待の利用](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience)」を参照してください。

#### ワンタイム パスコード認証の有効化

アドホック B2B を許可するシナリオでは、 [電子メールワンタイム パスコード認証](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)を有効にします。 この機能は、次のような他の方法で認証できないときに外部ユーザーを認証します。

- Microsoft Entra ID。
- Microsoft アカウント。
- Gmail アカウント (Google フェデレーションを使用)。
- 直接フェデレーションを介した Security Assertion Markup Language (SAML)/WS-Fed IDP からのアカウント。

ワンタイム パスコード認証では、Microsoft アカウントを作成する必要はありません。 外部ユーザーは、招待を引き換えるか共有リソースにアクセスするときにメール アドレスで一時コードを受け取ります。 次に、そのコードを入力してサインインを続行します。

#### アカウントの管理

エンド ユーザー開始シナリオでは、リソース テナント管理者はリソース テナント内で外部ユーザー アカウントを管理します (ホーム テナントの更新値に基づいて更新しません)。 受信した表示属性には、メール アドレスと表示名だけが含まれます。

外部ユーザー オブジェクトに対してさらに多くの属性を構成することで、さまざまなシナリオ (エンタイトルメント シナリオなど) を可能にできます。 アドレス帳に連絡先の詳細を入力することを含めることができます。 たとえば、次の属性を考えてみましょう。

- **アドレスリストから非表示にする設定有効** [アドレスリストに表示する]
- **ファーストネーム** [GivenName]
- **姓** [姓]
- **タイトル**
- **部**
- **電話番号**

これらの属性を設定して、外部ユーザーをグローバル アドレス一覧 (GAL) やユーザー検索 (SharePoint のユーザー ピッカーなど) に追加できます。 他のシナリオでは、異なる属性 (アクセス パッケージ、動的グループ メンバーシップ、SAML クレームに対するエンタイトルメントとアクセス許可の設定など) が必要になる場合があります。

既定で、GAL では招待された外部ユーザーは非表示になります。 統合 GAL に含めるには、外部ユーザーの属性を非表示にしないように設定します。 [マルチテナント ユーザー管理に関する一般的な考慮事項](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-considerations)に関する Microsoft Exchange Online セクションでは、外部ゲスト ユーザーではなく外部メンバー ユーザーを作成することで制限を軽減する方法について説明します。

#### アカウントのプロビジョニング解除

エンド ユーザー開始シナリオでは、アクセスに関する決定が分散化されます。これにより、外部ユーザーとその関連するアクセス権を削除するタイミングを決定するという課題が生じる可能性があります。 [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) と [アクセス レビューを](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-guest-access-with-access-reviews) 使用すると、既存の外部ユーザーとそのリソース アクセスを確認および削除できます。

エンタイトルメント管理の範囲外でユーザーを招待する場合は、そのアクセス権をレビューして管理するためのプロセスを別途作成する必要があります。 たとえば、Microsoft 365 の SharePoint 経由で外部ユーザーを直接招待する場合は、エンタイトルメント管理プロセスには含まれません。

### スクリプト シナリオ

スクリプト シナリオでは、リソース テナント管理者が、スクリプト化されたプル プロセスをデプロイすることで、検出と外部ユーザーのプロビジョニングを自動化します。

たとえば、ある会社が競合企業を買収したとします。 それぞれの企業には、Microsoft Entra テナントが 1 つ存在します。 ユーザーが招待や引き換えの手順を実行することなく、次の 1 日目のシナリオを機能させる必要があります。 すべてのユーザーが次のことを実行できる必要があります。

- プロビジョニングされているすべてのリソースにシングル サインオンを使用する。
- 統合 GAL でお互いやリソースを見つける。
- お互いの存在を確認し、チャットを開始する。
- 動的メンバーシップ グループに基づいてアプリケーションにアクセスする。

このシナリオでは、それぞれの組織のテナントは、既存の従業員にとってはホーム テナントであり、もう一方の組織の従業員にとってはリソース テナントとなります。

#### アカウントのプロビジョニング

[Delta Query を](https://learn.microsoft.com/ja-jp/graph/delta-query-overview)使用すると、テナント管理者はスクリプト化されたプル プロセスをデプロイして、リソース アクセスをサポートするための検出と ID プロビジョニングを自動化できます。 このプロセスでは、ホーム テナントで新しいユーザーを確認します。 次のマルチテナント トポロジ図に示すように、新しいユーザーは、B2B Graph API を使用して、リソース テナントの外部ユーザーとしてプロビジョニングされます。

[!\[図は、B2B Graph API を使用して新しいユーザーをリソース テナントの外部ユーザーとしてプロビジョニングする方法を示しています。\](media/multi-tenant-user-management-scenarios/diagram-multi-tenant-scripted-scenario-inline.png)
 図のタイトル: マルチテナント トポロジの図。 左側の \[A 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 右側の \[B 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 A 社と B 社の間では、A 社から B 社に対して相互作用が行われており、"A のユーザーを B にプルするスクリプト" というラベルが付いています。もう 1 つの相互作用は、B 社から A 社に対して行われており、"B のユーザーを A にプルするスクリプト" というラベルが付いています。](https://learn.microsoft.com/ja-jp/entra/architecture/media/multi-tenant-user-management-scenarios/diagram-multi-tenant-scripted-scenario-expanded.png#lightbox)

- テナント管理者は、各テナントの読み取りを許可するために資格情報と同意を事前に設定します。
- テナント管理者は、列挙と、対象ユーザーのリソース テナントへのプルを自動化します。
- 同意済みのアクセス許可で Microsoft Graph API を使用し、招待 API によってユーザーを読み取ってプロビジョニングします。
- 初回プロビジョニングでソース属性を読み取り、それらをターゲット ユーザー オブジェクトに適用できます。

#### アカウントの管理

リソース組織は、リソース テナント内のユーザーのメタデータ属性を更新することで、共有シナリオをサポートするためにプロファイル データを拡張できます。 ただし常時同期が必要な場合は、同期ソリューションを選んだ方が得策でしょう。

#### アカウントのプロビジョニング解除

[デルタ クエリ](https://learn.microsoft.com/ja-jp/graph/delta-query-overview) は、外部ユーザーをプロビジョニング解除する必要があるときに通知できます。 [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) と [アクセス レビューを](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-guest-access-with-access-reviews) 使用すると、既存の外部ユーザーとそのリソースへのアクセスを確認および削除できます。

エンタイトルメント管理の範囲外のユーザーを招待する場合は、外部ユーザーのアクセス権をレビューして管理するためのプロセスを別途作成します。 たとえば、Microsoft 365 の SharePoint 経由で直接外部ユーザーを招待する場合は、エンタイトルメント管理プロセスには含まれません。

### 自動化シナリオ

テナント間での同期共有は、この記事で説明するパターンの中で最も複雑です。 このパターンを使用すると、エンドユーザー開始やスクリプトよりもさらに自動化された管理とプロビジョニング解除のオプションを使用できます。

自動化シナリオでは、リソース テナント管理者が、ID プロビジョニング システムを使用して、プロビジョニングとプロビジョニング解除プロセスを自動化します。 Microsoft の Commercial Cloud インスタンス内のシナリオでは、 [テナント間同期](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/seamless-application-access-and-lifecycle-management-for-multi/ba-p/3728752)があります。 Microsoft ソブリン クラウドの複数のインスタンスにまたがるシナリオの場合、テナント間同期ではクラウド間はまだサポートされていないため、他のアプローチが必要です。

たとえば、Microsoft の商用クラウド インスタンス内に、次の要件を持つ複数の子会社がある多国籍/地域の複合企業があるとします。

- それぞれ独自の Microsoft Entra テナントを持っており、連携する必要がある。
- テナント間で新しいユーザーを同期することに加え、属性の更新を自動的に同期し、プロビジョニング解除を自動化する。
- 子会社の従業員が退社した場合、次回の同期中に他のすべてのテナントからそのアカウントを削除する。

拡張されたクラウド間シナリオでは、防衛産業基盤 (DIB) の請負業者には、防衛基盤と商用基盤の子会社があります。 これらには、競合する規制要件があります。

- 米国の防衛ビジネスは、Microsoft 365 US Government GCC High や Azure Government などの米国ソブリン クラウド テナントに存在する。
- 商用ビジネスは、グローバル Azure クラウドで実行されている Microsoft Entra 環境など、商用内の別個の Microsoft Entra テナントに存在する。

クロスクラウド アーキテクチャにデプロイされた単一の会社のように機能するため、すべてのユーザーは両方のテナントに同期されます。 この方法を使用すると、両方のテナント間で統合 GAL を利用できるようになり、両方のテナントに自動的に同期されるユーザーには、アプリケーションとコンテンツに対するエンタイトルメントと制限を含めることができる場合があります。 要件の例としては次のようなものがあります。

- 米国の従業員は、両方のテナントにどこからでもアクセスできます。
- 米国以外の従業員は、両方のテナントの統合 GAL に表示されるが、GCC High テナント内の保護されたコンテンツにはアクセスできません。

このシナリオでは、両方のテナントでユーザーを構成し、それらのユーザーを適切なエンタイトルメントとデータ保護ポリシーに関連付けるために、自動同期と ID 管理が必要となります。

[クラウド間 B2B](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/collaborate-securely-across-organizational-boundaries-and/ba-p/3094109) では、リモート クラウド インスタンスで共同作業を行う組織ごとに [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-cloud-settings) を構成する必要があります。

#### アカウントのプロビジョニング

このセクションでは、自動化シナリオでアカウント プロビジョニングを自動化するための 3 つの手法について説明します。

##### 手法 1: [Microsoft Entra ID で組み込みのテナント間同期機能を](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)使用する

この方法は、同期する必要があるすべてのテナントが同じクラウド インスタンス (商用間など) 内にある場合にのみ機能します。

##### 手法 2: Microsoft Identity Manager を使用してアカウントをプロビジョニングする

[Microsoft Identity Manager (MIM](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016)) などの外部 ID およびアクセス管理 (IAM) ソリューションを同期エンジンとして使用します。

この高度なデプロイでは、同期エンジンとして MIM が使用されます。 MIM は [、Microsoft Graph API](https://developer.microsoft.com/graph) と [Exchange Online PowerShell](https://learn.microsoft.com/ja-jp/powershell/exchange/exchange-online-powershell?view=exchange-ps&preserve-view=true) を呼び出します。 別の実装には、クラウドでホストされる [Active Directory 同期サービス (ADSS)](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/get-started/virtual-dc/active-directory-domain-services-overview) マネージド サービス オファリングを含めることができます。 他の IAM オファリング (SailPoint、Omada、OKTA など) を使用して最初から作成できる Microsoft 以外のオファリングがあります。

次の図に示すように、あるテナントから別のテナントへの ID (ユーザー、連絡先、グループ) のクラウド間同期を実行します。

[!\[図は、ユーザー、連絡先、グループなどの ID をテナント間でクラウドからクラウドに同期する方法を示しています。\](media/multi-tenant-user-management-scenarios/diagram-cloud-to-cloud-sync-inline.png)
 図のタイトル: クラウド間の ID の同期。 左側の \[A 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 右側の \[B 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 A 社と B 社の間で、A 社から B 社、および B 社から A 社に対して同期エンジンの相互作用が行われています。](https://learn.microsoft.com/ja-jp/entra/architecture/media/multi-tenant-user-management-scenarios/diagram-cloud-to-cloud-sync-expanded.png#lightbox)

この記事の範囲外の考慮事項としては、オンプレミス アプリケーションの統合があります。

##### 手法 3: Microsoft Entra Connect を使用してアカウントをプロビジョニングする

この手法は、従来の Windows Server ベースの Active Directory Domain Services (AD DS) ですべての ID を管理している複雑な組織にのみ適用されます。 この方法では、次の図に示すように、同期エンジンとして Microsoft Entra Connect を使用します。

[!\[図は、Microsoft Entra Connect を同期エンジンとして使用するプロビジョニング アカウントへのアプローチを示しています。\](media/multi-tenant-user-management-scenarios/diagram-sync-engine-inline.png)
 図のタイトル: Microsoft Entra Connect を使用してアカウントをプロビジョニングする。 この図は、4 つの主要コンポーネントを示しています。 左側のボックスは、顧客を表しています。 右側のクラウドのシェイプは B2B 変換を表しています。 上部中央にあるクラウドのシェイプを含むボックスは、Microsoft 商用クラウドを表しています。 中央下部にある雲の形状を含むボックスは、Microsoft US Government Sovereign Cloudを表しています。 \[顧客\] ボックス内では、Windows Server Active Directory アイコンが、それぞれ Microsoft Entra Connect のラベルが付いた 2 つのボックスに接続されています。 接続は、両端に矢印がついている赤い破線と更新アイコンで示されています。 Microsoft 商用クラウドのシェイプの中には、Microsoft Azure Commercial を表すもう 1 つのクラウドのシェイプがあります。 内部には、Microsoft Entra ID を表す別のクラウドのシェイプがあります。 Microsoft Azure 商用クラウドのシェイプの右側には、\[パブリック マルチテナント\] というラベルが付いた Office 365 を表すボックスがあります。 両端が矢印になった赤の実線は、Office 365 ボックスを Microsoft Azure Commercial クラウドのシェイプとハイブリッド ワークロードのラベルに接続します。 2 本の破線が、Office 365 のボックスから Microsoft Entra クラウドのシェイプに接続しています。 1 つは、Microsoft Entra ID に接続する端に矢印があります。 もう一方は、両端に矢印があります。 両端が矢印になった破線が、Microsoft Entra クラウドのシェイプを上部の顧客の Microsoft Entra Connect ボックスに接続しています。 両端が矢印になった破線が、Microsoft 商用クラウドのシェイプを B2B 変換クラウドのシェイプに接続しています。 Microsoft US Government ソブリン クラウド ボックス内には、Microsoft Azure Government を表すもう 1 つのクラウドのシェイプがあります。 内部には、Microsoft Entra ID を表す別のクラウドのシェイプがあります。 Microsoft Azure 商用クラウドのシェイプの右側には、\[US Gov GCC-High L4\] というラベルが付いた Office 365 を表すボックスがあります。 両端が矢印になった赤の実線が、Office 365 ボックスを Microsoft Azure Government クラウドのシェイプと \[ハイブリッド ワークロード\] のラベルに接続しています。 2 本の破線が、Office 365 のボックスから Microsoft Entra クラウドのシェイプに接続しています。 1 つは、Microsoft Entra ID に接続する端に矢印があります。 もう一方は、両端に矢印があります。 両端が矢印になった破線が、Microsoft Entra クラウドのシェイプを下部の顧客の Microsoft Entra Connect ボックスに接続しています。 両端が矢印になった破線が、Microsoft 商用クラウドのシェイプを B2B 変換クラウドのシェイプに接続しています。](https://learn.microsoft.com/ja-jp/entra/architecture/media/multi-tenant-user-management-scenarios/diagram-sync-engine-expanded.png#lightbox)

MIM の手法とは異なり、すべての ID ソース (ユーザー、連絡先、グループ) は、従来の Windows Server ベースの Active Directory Domain Services (AD DS) から取得されます。 AD DS ディレクトリは、通常、複数のテナントの ID を管理する複雑な組織のオンプレミス デプロイです。 クラウドのみの ID は、この手法の範囲内には含まれません。 同期の範囲に含めるには、すべての ID が AD DS 内に存在する必要があります。

概念的には、この手法では、ユーザーは内部メンバー ユーザーとしてホーム テナントに同期されます (既定の動作)。 または、ユーザーを外部ユーザーとしてリソース テナントに同期することもできます (カスタマイズされた動作)。

Microsoft では、Microsoft Entra Connect 構成で行われる変更を慎重に考慮しつつ、この二重のユーザー同期手法をサポートしています。 たとえば、ウィザード駆動型のセットアップ構成を変更する場合、サポート インシデント中に構成を再構築する必要がある場合は、変更を文書化する必要があります。

Microsoft Entra Connect では、そのままの状態ですぐに外部ユーザーを同期することはできません。 ユーザーを内部から外部アカウントに変換するために外部プロセス (PowerShell スクリプトなど) を使用して拡張する必要があります。

この手法の利点としては、Microsoft Entra Connect では AD DS に格納されている属性で ID が同期されることがあげられます。 同期には、スコープ内のすべてのテナントへのアドレス帳属性、マネージャー属性、グループ メンバーシップ、およびその他のハイブリッド ID 属性が含まれる場合があります。 ID の無効化は、AD DS に連動して行われます。 この特定のタスクでは、クラウド ID を管理するために、より複雑な IAM ソリューションは必要ありません。

テナントごとに Microsoft Entra Connect の 1 対 1 のリレーションシップがあります。 各テナントには、メンバーまたは外部ユーザー アカウントの同期をサポートするために個別に変更できる Microsoft Entra Connect の独自の構成があります。

#### 適切なトポロジの選択

自動化シナリオでは、ほとんどの場合、次のトポロジのいずれかが使用されます。

- メッシュ型トポロジ: すべてのテナントのすべてのリソースを共有できます。 他のテナントのユーザーを各リソース テナントに外部ユーザーとして作成します。
- 単一リソース テナント トポロジでは、1 つのテナント (リソース テナント) を使用します。そこでは、他のテナントのユーザーは外部ユーザーです。

以下の表は、ソリューションを設計する際のデシジョン ツリーとして参照してください。 表の後の図では、自分の組織に適したトポロジを判断できるように、両方のトポロジを示しています。

##### メッシュ型トポロジとシングル リソース テナント型トポロジの比較

| 考慮事項 | メッシュ型トポロジ | シングルリソーステナント |
| --- | --- | --- |
| ユーザーとリソースを含んだ個別の Microsoft Entra テナントが会社ごとに存在する | はい | はい |
| **リソースの場所とコラボレーション** |  |  |
| 共有アプリとその他のリソースは現在のホームテナントに保たれる | はい | いいえ。 リソース テナント内のアプリとその他のリソースのみを共有できます。 他のテナントに存在するアプリやその他のリソースは共有できません。 |
| 個々の会社の GAL (統合 GAL) ですべて表示可能 | はい | いいえ |
| **リソースのアクセスと管理** |  |  |
| Microsoft Entra ID に接続されているすべてのアプリケーションはすべての会社間で共有できます。 | はい | いいえ。 リソース テナント内のアプリケーションのみが共有されます。 他のテナントに存在しているアプリケーションは共有できません。 |
| グローバル リソース管理 | テナント レベルで続行します。 | リソース テナントに統合されます。 |
| ライセンス: Microsoft 365 の Office 365 SharePoint、統合 GAL、Teams アクセスはすべてゲストをサポートしますが、他の Exchange Online シナリオではサポートされません。 | テナント レベルで継続されます。 | テナント レベルで継続されます。 |
| ライセンス: [Microsoft Entra ID (Premium)](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing) | 最初の 5 万人の月間アクティブ ユーザーは無料です (テナントごと)。 | 最初の 5 万人の月間アクティブ ユーザーは無料です。 |
| ライセンス: サービスとしてのソフトウェア (SaaS) アプリ | 個々のテナン内で維持されます。テナントごとにユーザーごとのライセンスが必要な場合があります。 | すべての共有リソースは、シングル リソース テナントに存在します。 必要に応じて、シングル テナントへのライセンスの統合を検討できます。 |

##### メッシュ型トポロジ

次の図は、メッシュ トポロジを示しています。

[!\[図はメッシュ トポロジを示しています。\](media/multi-tenant-user-management-scenarios/diagram-mesh-topology-inline.png)
 図のタイトル: メッシュ トポロジ。 左上の \[A 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 右上の \[B 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 左下の \[C 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 右下の \[D 社\] というラベルのボックスには、内部ユーザーと外部ユーザーが含まれています。 A 社と B 社と C 社と D 社の間では、左側の会社と右側の会社の間で同期エンジンの相互作用が行われています。](https://learn.microsoft.com/ja-jp/entra/architecture/media/multi-tenant-user-management-scenarios/diagram-mesh-topology-expanded.png#lightbox)

メッシュ トポロジでは、各ホーム テナントのすべてのユーザーは他の各テナントに同期され、それらはリソース テナントになります。

- テナント内のあらゆるリソースを外部ユーザーと共有できます。
- 各組織は、複合企業内のすべてのユーザーを表示できます。 上の図には、4 つの統合 GAL があります。そのそれぞれにホーム ユーザーと、他の 3 つのテナントの外部ユーザーが含まれています。

[マルチテナント ユーザー管理に関する一般的な考慮事項](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-considerations) は、このシナリオでのユーザーのプロビジョニング、管理、プロビジョニング解除に関する情報を提供します。

##### クロスクラウドのメッシュ トポロジ

メッシュ トポロジーは、最低限 2 つのテナントで使用でき、これはクロスソブリン クラウド ソリューションにまたがる DIB（防衛産業基盤）関連の防衛請負業者のシナリオに適用されます。 メッシュ トポロジと同様に、各ホーム テナントの各ユーザーは他のテナントに同期され、それがリソース テナントになります。 手法 3 セクション図では、パブリック商用テナントの内部ユーザーが、外部ユーザー アカウントとして米国ソブリン GCC High テナントに同期します。 同時に、GCC High の内部ユーザーは外部ユーザー アカウントとして商用システムに同期されています。

この図では、データの保存場所も示されています。 データの分類とコンプライアンスは、この記事の範囲外ですが、アプリケーションとコンテンツに対するエンタイトルメントと制限を含めることができます。 コンテンツには、内部ユーザーのユーザー所有データがある場所 (Exchange Online メールボックスや OneDrive に格納されているデータなど) が含まれる場合があります。 コンテンツは、そのホーム テナントに存在し、リソース テナントには存在しない場合があります。 共有データは、両方のテナントに存在する可能性があります。 コンテンツに対するアクセスは、アクセスの制御や条件付きアクセス ポリシーによって制限することができます。

##### シングル リソース テナント型トポロジ

次の図は、単一リソース テナント トポロジを示しています。

[!\[図は、単一のリソース テナント トポロジを示しています。\](media/multi-tenant-user-management-scenarios/diagram-single-resource-tenant-scenario-inline.png)
 図のタイトル: 単一リソース テナント トポロジ。 上部の A 社を表すボックスには、3 つのボックスが含まれています。 左側のボックスは、すべての共有リソースを表します。 中央のボックスは、内部ユーザーを表します。 右側のボックスは、外部ユーザーを表します。 A 社のボックスの下には、同期エンジンを表すボックスがあります。 3 本の矢印で同期エンジンが A 社に接続されています。同期エンジン ボックスの下で、図の下部には、B 社、C 社、D 社を表す 3 つのボックスがあります。そのそれぞれが矢印で同期エンジン ボックスに接続されています。 下部の各会社のボックス内には、Microsoft Graph API Exchange Online PowerShell というラベルと、内部ユーザーを表すアイコンがあります。](media/multi-tenant-user-management-scenarios/diagram-single-resource-tenant-scenario-expanded.png#lightbox)

単一リソース テナント トポロジでは、ユーザーとその属性はリソース テナント (上の図の A 社) に同期されます。

- メンバー組織間で共有されるすべてのリソースは、単一のリソース テナントに存在する必要があります。 複数の子会社が同じ SaaS アプリのサブスクリプションを保有している場合は、それらのサブスクリプションを統合できます。
- リソース テナントの GAL にのみ、全社のユーザーが表示されます。

#### アカウントの管理

このソリューションでは、ソース テナントのユーザーの属性の変更が検出されて、リソース テナントの外部ユーザーに同期されます。 これらの属性を使用して、承認の決定 (動的メンバーシップ グループを使用している場合など) を行うことができます。

#### アカウントのプロビジョニング解除

ソース環境でオブジェクトが削除されると、オートメーションによってそれが検出されて、関連する外部ユーザー オブジェクトがターゲット環境から削除されます。

[マルチテナント ユーザー管理に関する一般的な考慮事項](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-common-considerations) は、このシナリオでのユーザーのプロビジョニング、管理、プロビジョニング解除に関する追加情報を提供します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multilateral-federation-baseline"} -->
## 大学の多国間フェデレーションのベースライン設計 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multilateral-federation-baseline
- Service: entra / architecture
- Article date: 2023-04-01
- Summary: 大学向けの多国間フェデレーション ソリューションのベースライン設計について説明します。

Microsoft は、多くの場合、アプリケーションがクラウドベースまたはオンプレミスでホストされているハイブリッド環境で動作する研究大学と話します。 どちらの場合も、アプリケーションではさまざまな認証プロトコルが使用される可能性があります。 これらのプロトコルは、サポート終了が間近であったり、必要なレベルのセキュリティを提供していなかったりする場合があります。

[Image: 信頼、同期、資格情報の検証パスを含むクラウドとオンプレミスの領域を含む一般的な大学アーキテクチャの図。]

さまざまな認証プロトコル、さまざまな ID 管理 (IdM) メカニズムに対するニーズは、その多くがアプリケーションによって生じています。

研究大学の環境では、多くの場合、研究アプリによって IdM についての要件が生じます。 大学は、Shibboleth などのフェデレーション プロバイダーをプライマリ ID プロバイダー (IdP) として使用することがあります。 その場合、Microsoft Entra ID は Shibboleth とフェデレーションするように構成されていることがよくあります。 Microsoft 365 Apps も使用している場合は、Microsoft Entra ID を使用して統合を構成できます。

研究大学で使用されるアプリケーションは、IT フットプリント全体のさまざまな部分で動作します。

- 調査アプリケーションや多国間フェデレーション アプリケーションは、InCommon と eduGAIN を通じて使用できます。
- ライブラリ アプリケーションは、電子ジャーナルやその他の電子コンテンツ プロバイダーへのアクセスを提供します。
- 一部のアプリケーションでは、Central Authentication Service などのレガシ認証プロトコルを使用してシングル サインオンを使用できるようにしています。
- 学生と教職員のアプリケーションで、複数の認証メカニズムが使用されている場合があります。 たとえば、Shibboleth や他のフェデレーション プロバイダーと統合されているものもあれば、Microsoft Entra ID と統合されているものもあります。
- Microsoft 365 アプリケーションは、Microsoft Entra ID と統合されています。
- Windows Server Active Directory が使われて Microsoft Entra ID と同期されている場合もあります。
- ライトウェイト ディレクトリ アクセス プロトコル (LDAP) は、外部 LDAP ディレクトリまたは ID レジストリを持つ多くの大学で使用されています。 これらのレジストリは、多くの場合、機密属性、ロール階層情報、さらには申請者などの特定の種類のユーザーを格納するために使用されます。
- オンプレミス Active Directory または外部 LDAP ディレクトリは、多くの場合、Web 以外のアプリケーションやさまざまな Microsoft オペレーティング システム以外のサインインについてシングル資格情報サインインを有効にするために使用されます。

### ベースライン アーキテクチャの課題

多くの場合、ベースライン アーキテクチャは時間の経過と共に進化し、それにより設計に複雑さと剛性がもたらされ、更新機能が導入されます。 ベースライン アーキテクチャを使用する場合の課題には、次のようなものがあります。

- **新しい要件に対応するのは難しい:** 複雑な環境では、最新の規制や要件に迅速に適応して対応することが困難になります。 たとえば、アプリが多数の場所にあり、これらのアプリがさまざまな IDM でさまざまな方法で接続されている場合は、多要素認証サービスを配置する場所と多要素認証を適用する方法を決定する必要があります。

    高等教育では、サービス所有権の断片化の問題も生じます。 エンタープライズ リソース プランニング 、学習管理システム、部門、部署ソリューションなどの主要なサービスを担当する担当者は、運用するシステムを変更したり修正したりする作業に抵抗する可能性があります。
- **アプリによっては、Microsoft 365 の機能を活用できない** (たとえば、Intune、条件付きアクセス、パスワードレスなど): 多くの大学がクラウドに移行し、Microsoft Entra ID への既存の投資を活用したいと考えています。 しかしながら、別のフェデレーション プロバイダーがプライマリ IdP として使用されていると、大学としてはその他のアプリについて、Microsoft 365 機能で使用できなくなるものが生じてくることがあります。
- **ソリューションの複雑さ:** 管理するコンポーネントは多数あります。 クラウド内にあるコンポーネントもあれば、オンプレミスまたはサービスとしてのインフラストラクチャ (IaaS) インスタンスにあるコンポーネントもあります。 アプリはさまざまな場所で動作します。 ユーザーの観点からは、エクスペリエンスに連続性が欠けているように感じられる可能性があります。 たとえば、ユーザーには Shibboleth サインイン ページが表示されることもあれば、Microsoft Entra サインイン ページが表示されることもあります。

これらの課題を解決すると同時に、次の要件にも対処できる 3 つのソリューションを提示します。

- InCommon や eduGAIN などの多国間フェデレーションに参加する機能
- すべての種類のアプリ (レガシ プロトコルを必要とするアプリも含む) をサポートする機能
- 外部ディレクトリと属性ストアをサポートする機能

これら 3 つのソリューションは、上から推奨度が高い順に提示されています。 それぞれが要件を満たしますが、複雑なアーキテクチャにおいて想定されるトレードオフの決定を行う必要があります。 要件と開始点に基づいて、お使いの環境に最適なものを選択してください。 この決定に役立つデシジョン ツリーも用意されています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multilateral-federation-decision-tree"} -->
## 大学の多国間フェデレーションのデシジョン ツリー - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multilateral-federation-decision-tree
- Service: entra / architecture
- Article date: 2023-04-01
- Summary: このデシジョン ツリーを使用して、大学向けの多国間フェデレーション ソリューションを設計します。

このデシジョン ツリーを使用して、環境に最適な多国間フェデレーション ソリューションを決定します。

[Image: 3 つのソリューションのいずれかを選択するのに役立つ主要な条件を含むデシジョン マトリックスを示す図。]

### 移行リソース

このコンテンツで説明されているソリューションへの移行に役立つリソースを次に示します。

| 移行リソース | 説明 | 該当する移行先.. |
| --- | --- | --- |
| [アプリケーションを Microsoft Entra ID に移行するためのリソース](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources) | アプリケーションのアクセスと認証を Microsoft Entra ID に移行するために役立つリソース | ソリューション 1、ソリューション 2、ソリューション 3 |
| [Microsoft Entra カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview) | Microsoft Entra カスタム クレーム プロバイダーの概要 | 解決策 1 |
| [カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage) | カスタム セキュリティ属性へのアクセスを管理する手順 | 解決策 1 |
| [Microsoft Entra シングル サインオン (SSO) と Cirrus Bridge の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cirrus-identity-bridge-for-azure-ad-tutorial) | Cirrus Bridge を Microsoft Entra ID と統合するためのチュートリアル | 解決策 1 |
| [Cirrus Bridge の概要](https://blog.cirrusidentity.com/documentation/azure-bridge-setup-rev-6.0) | Microsoft Entra ID を使用して Cirrus Bridge を構成するための Cirrus Identity のドキュメント | 解決策 1 |
| [セキュリティ アサーション マークアップ言語 (SAML) プロキシとしての Shibboleth の構成](https://shibboleth.atlassian.net/wiki/spaces/KB/pages/1467056889/Using+SAML+Proxying+in+the+Shibboleth+IdP+to+connect+with+Azure+AD) | SAML プロキシ機能を使用して Shibboleth ID プロバイダー (IdP) を Microsoft Entra ID に接続する方法を説明する Shibboleth の記事 | 解決策 2 |
| [Microsoft Entra 多要素認証デプロイに関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted) | Microsoft Entra 多要素認証を構成するためのガイダンス | ソリューション 1、ソリューション 2 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multilateral-federation-introduction"} -->
## 大学の多国間フェデレーション ソリューションの設計 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multilateral-federation-introduction
- Service: entra / architecture
- Article date: 2023-04-01
- Summary: 大学向けの多国間フェデレーション ソリューションの設計方法を説明します。

研究大学は相互のコラボレーションを必要としています。 コラボレーションを実現するには、グローバルな大学間の認証とアクセスを可能にするための、多国間フェデレーションが必須です。

### 多国間フェデレーション ソリューションに関する課題

大学は多くの課題に直面しています。 たとえば、大学では、1 つの ID 管理システムと一連のプロトコルを使用できます。 他の大学では、要件に応じて異なる一連のテクノロジを使用する場合があります。 一般に、大学間では次のようなことが起こり得ます。

- さまざまな ID 管理システムを使用する。
- さまざまなプロトコルを使用する。
- カスタマイズされたソリューションを使用する。
- 長い歴史のあるレガシ機能をサポートする必要がある。
- さまざまな IT 世代で構築されたソリューションをサポートする必要がある。

多くの大学では、Microsoft 365 スイートの生産性ツールとコラボレーション ツールも採用されています。 これらのツールは、ID 管理のためにMicrosoft Entra ID に依存しています。これにより、大学は次の構成を行うことができます。

- 複数のアプリケーションにまたがるシングル サインオン。
- パスワードレス認証、多要素認証、リスク ベースの条件付きアクセス ポリシーなどの最新のセキュリティ制御。
- 強化されたレポートと監視。

Microsoft Entra ID では多国間フェデレーションがネイティブにサポートされていないため、このコンテンツでは、一般的な研究大学アーキテクチャを使用している大学間の認証とアクセスをフェデレーションするための 3 つのソリューションについて説明します。 これらのシナリオでは、Microsoft 以外の製品は例示のみを目的として言及しており、製品のより広範なクラスの代表例として挙げています。 たとえば、このコンテンツでは、フェデレーション プロバイダーの例として Shibboleth を使用しています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multilateral-federation-solution-one"} -->
## 解決策 1: Cirrus Bridge を使用した Microsoft Entra ID - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multilateral-federation-solution-one
- Service: entra / architecture
- Article date: 2023-04-01
- Summary: この記事では、大学の多国間フェデレーション ソリューションとして、Cirrus Bridge で Microsoft Entra ID を使用する場合の設計上の考慮事項について説明します。

ソリューション 1 では、すべてのアプリケーションのプライマリ ID プロバイダー (IdP) として Microsoft Entra ID を使用します。 マネージド サービスは、多国間フェデレーションを提供します。 この例では、Cirrus Bridge は、中央認証サービス (CAS) と多国間フェデレーション アプリを統合するためのマネージド サービスです。

[Image: Cirrus を使用して CAS ブリッジとセキュリティ アサーション マークアップ言語 (SAML) ブリッジを提供する Microsoft Entra とさまざまなアプリケーション環境の統合を示す図。]

オンプレミスの Active Directory インスタンスも使用している場合は、ハイブリッド ID を使用して [Active Directory を構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) できます。 Cirrus Bridge で Microsoft Entra ID を使用するソリューションを実装すると、次の機能が提供されます。

- **セキュリティ アサーション マークアップ言語 (SAML) ブリッジ:** InCommon と eduGAIN への多国間フェデレーションと参加を構成します。 SAML ブリッジを使用して、各多国間フェデレーション アプリの Microsoft Entra 条件付きアクセス ポリシー、アプリの割り当て、ガバナンス、およびその他の機能を構成することもできます。
- **CAS ブリッジ:** Microsoft Entra ID で認証するオンプレミスの CAS アプリをサポートするためのプロトコル変換を提供します。 CAS ブリッジを使用して、すべての CAS アプリ全体の Microsoft Entra 条件付きアクセス ポリシー、アプリの割り当て、ガバナンスを構成できます。

Cirrus Bridge を使用して Microsoft Entra ID を実装すると、Microsoft Entra ID のより多くの機能を利用できます。

- **カスタム クレーム プロバイダーのサポート:**[Microsoft Entra カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)を使用すると、外部属性ストア (外部 LDAP ディレクトリなど) を使用して、個々のアプリのトークンに要求を追加できます。 カスタム要求プロバイダーは、外部 REST API を呼び出して外部システムから要求をフェッチするカスタム拡張機能を使用します。
- **カスタム セキュリティ属性:** ディレクトリ内のオブジェクトにカスタム属性を追加し、それらを読み取ることができるユーザーを制御できます。 [カスタム セキュリティ属性を](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview) 使用すると、より多くの属性を Microsoft Entra ID に直接格納できます。

### 利点

Cirrus Bridge で Microsoft Entra ID を実装する利点の一部を次に示します。

- **すべてのアプリのシームレスなクラウド認証**

    - すべてのアプリは、Microsoft Entra ID を介して認証されます。
    - マネージド サービス内のすべてのオンプレミス ID コンポーネントを削除すると、運用コストと管理コストが削減され、セキュリティ リスクが軽減され、他の作業のためにリソースが解放される可能性があります。
- **合理化された構成、デプロイ、およびサポート モデル**

    - [Cirrus Bridge](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cirrus-identity-bridge-for-azure-ad-tutorial) は、Microsoft Entra アプリ ギャラリーに登録されています。
    - ブリッジ ソリューションを構成および設定するための確立されたプロセスを利用できます。
    - Cirrus Identity は継続的なサポートを提供します。
- **多国間フェデレーション アプリの条件付きアクセスのサポート**

    - 条件付きアクセス制御の実装は、 [NIH](https://auth.nih.gov/CertAuthV3/forms/help/compliancecheckhelp.html) および [REFEDS](https://refeds.org/category/research-and-scholarship) の要件に準拠するのに役立ちます。
    - このソリューションは、多国間フェデレーション アプリと CAS アプリの両方に対して詳細な Microsoft Entra 条件付きアクセスを構成できる唯一のアーキテクチャです。
- **すべてのアプリに対する他の Microsoft Entra 関連ソリューションの使用**

    - デバイス管理には、Intune と Microsoft Entra の接続を利用できます。
    - Microsoft Entra 参加を使用すると、Windows Autopilot、Microsoft Entra 多要素認証、パスワードレス機能を使用できます。 Microsoft Entra join では、ゼロ トラスト体制の実現がサポートされています。

        注

        Microsoft Entra 多要素認証に切り替えると、使用している他のソリューションよりも大幅なコストを節約できる場合があります。

### 考慮事項とトレードオフ

このソリューションを使用する場合のトレードオフの一部を次に示します。

- **認証エクスペリエンスをカスタマイズする機能が制限されています。** このシナリオでは、マネージド ソリューションが提供されます。 フェデレーション プロバイダー製品を使用してカスタム ソリューションを構築する柔軟性や粒度が得られない場合があります。
- **限定されたサードパーティ MFA 統合:** サード パーティの多要素認証ソリューションで使用できる統合の数が制限される場合があります。
- **1 回限りの統合作業が必要です。** 統合を効率化するには、すべての学生と教職員のアプリを Microsoft Entra ID に 1 回限り移行する必要があります。 また、Cirrus Bridge を設定する必要があります。
- **Cirrus Bridge に必要なサブスクリプション:** Cirrus Bridge のサブスクリプション料金は、ブリッジの予想される年間認証使用量に基づいています。

### 移行リソース

次のリソースは、このソリューション アーキテクチャへの移行に役立ちます。

| 移行リソース | 説明 |
| --- | --- |
| [アプリケーションを Microsoft Entra ID に移行するためのリソース](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources) | アプリケーションのアクセスと認証を Microsoft Entra ID に移行するために役立つリソース |
| [Microsoft Entra カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview) | Microsoft Entra カスタム クレーム プロバイダーの概要 |
| [カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage) | カスタム セキュリティ属性へのアクセスを管理する手順 |
| [Microsoft Entra と Cirrus Bridge のシングル サインオン統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cirrus-identity-bridge-for-azure-ad-tutorial) | Cirrus Bridge を Microsoft Entra ID と統合するためのチュートリアル |
| [Cirrus Bridge の概要](https://blog.cirrusidentity.com/documentation/azure-bridge-setup-rev-6.0) | Microsoft Entra ID を使用して Cirrus Bridge を構成するための Cirrus Identity のドキュメント |
| [Microsoft Entra 多要素認証デプロイに関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted) | Microsoft Entra 多要素認証を構成するためのガイダンス |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multilateral-federation-solution-three"} -->
## ソリューション 3: Microsoft Entra ID、ADFS、Shibboleth - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multilateral-federation-solution-three
- Service: entra / architecture
- Article date: 2023-04-01
- Summary: この記事では、Microsoft Entra ID、AD FS、Shibboleth を大学向けの多国間フェデレーション ソリューションとして使う場合の設計上の考慮事項について説明します。

ソリューション 3 では、フェデレーション プロバイダーがプライマリ ID プロバイダー (IdP) です。 この例では、Shibboleth は、多国間フェデレーション アプリ、オンプレミス Central Authentication Service (CAS) アプリ、ライトウェイト ディレクトリ アクセス プロトコル (LDAP) ディレクトリを統合するためのフェデレーション プロバイダーです。

[Image: Shibboleth、Active Directory フェデレーション サービス (AD FS)、Microsoft Entra ID を統合した設計を示す図。]

このシナリオでは、Shibboleth がプライマリ IdP です。 多国間フェデレーション (InCommon など) への参加は、この統合をネイティブにサポートする Shibboleth を通じて行われます。 オンプレミスの CAS アプリと LDAP ディレクトリも Shibboleth によって統合されています。

学生向けアプリ、教職員向けアプリ、および Microsoft 365 アプリは、Microsoft Entra ID と統合されています。 Active Directory のオンプレミスのインスタンスが Microsoft Entra ID と同期されている。 Active Directory フェデレーション サービス (AD FS) では、サード パーティの多要素認証との統合が提供されます。 AD FS によってプロトコル変換が実行され、デバイス管理、Windows Autopilot、パスワードレス機能のために Microsoft Entra Join などの特定の Microsoft Entra 機能が有効化されます。

### 利点

このソリューションを使用する利点の一部を次に示します。

- **カスタマイズされた認証:** Shibboleth を使用して、多国間フェデレーション アプリのエクスペリエンスをカスタマイズできます。
- **実行の容易さ:** このソリューションは、Shibboleth をプライマリ IdP として既に使用している機関に対して、短期間で簡単に実装できます。 学生と教職員のアプリを Microsoft Entra ID に移行し、AD FS インスタンスを追加する必要があります。
- **最小限の中断:** このソリューションでは、サード パーティの多要素認証が可能です。 更新の準備ができるまで、Duo などの既存の多要素認証ソリューションを配置したままにしておくことができます。

### 考慮事項とトレードオフ

このソリューションを使用する場合のトレードオフの一部を次に示します。

- **複雑さとセキュリティリスクの向上:** オンプレミスのフットプリントは、マネージド サービスと比較して、環境の複雑さと追加のセキュリティ リスクを意味する可能性があります。 オンプレミス コンポーネントの管理に関連するオーバーヘッドと料金が増加する場合もあります。
- **最適でない認証エクスペリエンス:** 多国間フェデレーションと CAS アプリの場合、クラウドベースの認証メカニズムはなく、複数のリダイレクトが存在する可能性があります。
- **Microsoft Entra 多要素認証はサポートされません。** このソリューションでは、多国間フェデレーションまたは CAS アプリに対する Microsoft Entra 多要素認証のサポートは有効になりません。 コスト削減の可能性を見逃す恐れがあります。
- **詳細な条件付きアクセスのサポートなし:** 詳細な条件付きアクセスのサポートがないため、細かい決定を行う機能が制限されます。
- **継続的なスタッフの大幅な割り当て:** IT スタッフは、認証ソリューションのインフラストラクチャとソフトウェアを維持する必要があります。 人員削減はリスクを伴う可能性があります。

### 移行リソース

このソリューション アーキテクチャへの移行に役立つリソースを次に示します。

| 移行リソース | 説明 |
| --- | --- |
| [アプリケーションを Microsoft Entra ID に移行するためのリソース](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources) | アプリケーションのアクセスと認証を Microsoft Entra ID に移行するために役立つリソース |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/multilateral-federation-solution-two"} -->
## ソリューション 2: Microsoft Entra ID と SAML プロキシとしての Shibboleth - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/multilateral-federation-solution-two
- Service: entra / architecture
- Article date: 2023-04-01
- Summary: この記事では、Microsoft Entra ID と SAML プロキシとしての Shibboleth を大学向けの多国間フェデレーション ソリューションとして使う場合の設計上の考慮事項について説明します。

ソリューション 2 では、Microsoft Entra ID はプライマリ ID プロバイダー (IdP) として動作します。 フェデレーション プロバイダーは、中央認証サービス (CAS) アプリと多国間フェデレーション アプリへの Security Assertion Markup Language (SAML) プロキシとして機能します。 この例では、参照リンクを提供するために [Shibboleth が SAML プロキシとして動作](https://shibboleth.atlassian.net/wiki/spaces/KB/pages/1467056889/Using+SAML+Proxying+in+the+Shibboleth+IdP+to+connect+with+Azure+AD)します。

[Image: SAML プロキシ プロバイダーとして使用される Shibboleth を示す図。]

Microsoft Entra ID はプライマリ IdP であるため、すべての学生と教職員のアプリは Microsoft Entra ID と統合されます。 すべての Microsoft 365 アプリも Microsoft Entra ID と統合されています。 Microsoft Entra Domain Services が使われている場合は、Microsoft Entra ID とも同期されます。

Shibboleth の SAML プロキシ機能は、Microsoft Entra ID と統合されています。 Microsoft Entra ID では、Shibboleth はギャラリー以外のエンタープライズ アプリケーションと見なされます。 大学では、CAS アプリについてはシングル サインオン (SSO) が使用可能で、InCommon 環境に参加できます。 さらに、Shibboleth はライトウェイト ディレクトリ アクセス プロトコル (LDAP) ディレクトリ サービスの統合を提供します。

### 利点

このソリューションを使用すると、次の利点があります。

- **すべてのアプリのクラウド認証:** すべてのアプリは、Microsoft Entra ID を介して認証されます。
- **実行の容易さ:** このソリューションは、既に Shibboleth を使用している大学の短期的な実行を容易にします。

### 考慮事項とトレードオフ

このソリューションを使用する場合のトレードオフの一部を次に示します。

- **複雑さとセキュリティリスクの向上:** オンプレミスのフットプリントは、マネージド サービスと比較して、環境の複雑さと追加のセキュリティ リスクを意味する可能性があります。 オンプレミス コンポーネントの管理に関連するオーバーヘッドと料金が増加する場合もあります。
- **最適でない認証エクスペリエンス:** 多国間フェデレーションおよび CAS アプリの場合、Shibboleth 経由のリダイレクトにより、ユーザーの認証エクスペリエンスがシームレスにならない可能性があります。 ユーザーの認証エクスペリエンスをカスタマイズするためのオプションは限定されています。
- **限定されたサード パーティの多要素認証統合:** サード パーティの多要素認証ソリューションで使用できる統合の数が制限される場合があります。
- **詳細な条件付きアクセスのサポートなし:** 詳細な条件付きアクセスのサポートがない場合は、最も低い共通分母 (低い摩擦のために最適化されますが、セキュリティ制御は制限されます) または最も高い共通分母 (ユーザーの摩擦を犠牲にしてセキュリティ制御用に最適化) を選択する必要があります。 きめ細かい決定を行う機能は限定されます。

### 移行リソース

このソリューション アーキテクチャへの移行に役立つリソースを次に示します。

| 移行リソース | 説明 |
| --- | --- |
| [アプリケーションを Microsoft Entra ID に移行するためのリソース](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources) | アプリケーションのアクセスと認証を Microsoft Entra ID に移行するために役立つリソース |
| [SAML プロキシとしての Shibboleth の構成](https://shibboleth.atlassian.net/wiki/spaces/KB/pages/1467056889/Using+SAML+Proxying+in+the+Shibboleth+IdP+to+connect+with+Azure+AD) | SAML プロキシ機能を使って Shibboleth IdP を Microsoft Entra ID に接続する方法を説明する Shibboleth の記事 |
| [Microsoft Entra 多要素認証デプロイに関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted) | Microsoft Entra 多要素認証を構成するためのガイダンス |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/ops-guide-auth"} -->
## Microsoft Entra 認証管理操作リファレンス ガイド - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-auth
- Service: entra / architecture
- Article date: 2024-08-25
- Summary: この運用リファレンス ガイドでは、認証管理をセキュリティで保護するために実行する必要がある検査とアクションについて説明します

[Microsoft Entra 運用リファレンス ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-intro)のこのセクションでは、企業のセキュリティ体制に基づいた資格情報のセキュリティ保護と管理、認証 (AuthN) エクスペリエンスの定義、割り当ての委任、使用状況の測定、およびアクセス ポリシーの定義に必要な検査と操作について説明します。

注

これらの推奨事項は公開日の時点で最新ですが、時間の経過と共に変化する可能性があります。 Microsoft の製品とサービスは時間の経過と共に進化するため、継続的に ID プラクティスを評価する必要があります。

### 主要な運用プロセス

#### 主要タスクに所有者を割り当てる

Microsoft Entra ID を管理するには、ロールアウト プロジェクトに含まれていない場合のある主要な運用タスクとプロセスを継続的に実行する必要があります。 環境を最適化するために、これらのタスクを設定することも重要です。 主なタスクと推奨される所有者は次のとおりです。

| タスク | 担当者 |
| --- | --- |
| Microsoft Entra ID のシングル サインオン (SSO) の構成のライフサイクルを管理する | ID およびアクセス管理 (IAM) の運用チーム |
| Microsoft Entra アプリケーションの条件付きアクセス ポリシーを設計する | InfoSec アーキテクチャ チーム |
| セキュリティ情報イベント管理 (SIEM) システムにサインイン アクティビティをアーカイブする | InfoSec 運用チーム |
| SIEM システムでリスク イベントをアーカイブする | InfoSec 運用チーム |
| セキュリティ レポートについてトリアージおよび調査する | InfoSec 運用チーム |
| リスク イベントについてトリアージおよび調査する | InfoSec 運用チーム |
| Microsoft Entra ID Protection からのリスクと脆弱性のレポートでフラグが設定されたユーザーをトリアージして調査する | InfoSec 運用チーム |

注

Microsoft Entra ID Protection には、Microsoft Entra ID P2 ライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID Free と Microsoft Entra ID P1 または P2 エディションの一般提供機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)について記載されたページを参照してください。

リストを確認することで、所有者が空になっているタスクに所有者を割り当てたり、上記の推奨事項に一致しない所有者を持つタスクの所有権を調整したりする必要があるものを見つけることができます。

##### 所有者に関する推奨資料

- [Microsoft Entra ID での管理者ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)

### 資格情報の管理

#### パスワード ポリシー

パスワードを安全に管理することは、ID およびアクセス管理の最も重要な部分の 1 つであり、多くの場合、攻撃の最大の標的となる部分でもあります。 Microsoft Entra ID は、攻撃が成功するのを防ぐために役立ついくつかの機能をサポートしています。

次の表を使用して、対処が必要な問題を軽減するための推奨される解決策を見つけてください。

| 問題 | Recommendation |
| --- | --- |
| 脆弱なパスワードから保護するためのメカニズムがない | Microsoft Entra ID の[セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks) と[パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)を有効にします |
| 漏洩したパスワードを検出するためのメカニズムがない | [パスワード ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization) を有効にして分析情報を取得します |
| AD FS を使用し、管理された認証に移行できない | [AD FS エクストラネットのスマート ロックアウト](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-extranet-smart-lockout-protection)や [Microsoft Entra スマート ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)を有効にします |
| パスワード ポリシーで、長さ、複数の文字セット、有効期限などの複雑なものに基づくルールを使用している | [Microsoft の推奨プラクティス](https://www.microsoft.com/research/publication/password-guidance/?from=http%3A%2F%2Fresearch.microsoft.com%2Fpubs%2F265143%2Fmicrosoft_password_guidance.pdf)を優先して再度検討し、アプローチをパスワード管理に切り替えて、[Microsoft Entra のパスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad)をデプロイしてください。 |
| ユーザーが多要素認証を使用するように登録されていない | [すべてのユーザーのセキュリティ情報を登録](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy)して、ユーザーの ID をパスワードと共に検証するためのメカニズムとして使用できるようにします |
| パスワードの失効がユーザーのリスクに基づいていない | Microsoft Entra [Identity Protection ユーザー リスク ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)をデプロイして、SSPR を使用して漏洩した資格情報のパスワード変更を強制します |
| 識別された IP アドレスから行われる悪意のあるユーザーからの悪意のある認証から保護するための、スマート ロックアウト メカニズムがない | パスワード ハッシュ同期または[パススルー認証 (PTA)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start) のいずれかを使用してクラウドで管理される認証をデプロイします |

##### パスワード ポリシーに関する推奨資料

- [Microsoft Entra ID と AD FS のベスト プラクティス: パスワードのスプレー攻撃に対する防御 - Enterprise Mobility + Security](https://cloudblogs.microsoft.com/enterprisemobility/2018/03/05/azure-ad-and-adfs-best-practices-defending-against-password-spray-attacks/)

#### セルフサービス パスワード リセット とパスワード保護を有効にする

パスワードを変更またはリセットする必要があるユーザーは、ヘルプ デスクへの問い合わせの量とコストの最大の発生源の 1 つです。 コストに加え、ユーザーのリスクを軽減するためのツールとしてパスワードを変更することは、組織のセキュリティ体制を改善するための基本的な手順です。

少なくとも、Microsoft Entra ID の[セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks) とオンプレミスの[パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-deploy)をデプロイして、次のことを行うことをお勧めします。

- ヘルプ デスクへの問い合わせをうまくそらす。
- 一時パスワードの使用を置き替える。
- オンプレミスのソリューションに依存している既存のセルフサービスのパスワード管理ソリューションを置き換える。
- 組織内の[脆弱なパスワードを排除する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad)。

注

Microsoft Entra ID P2 ライセンスを持つ組織の場合は、SSPR をデプロイし、[Microsoft Entra ID 保護のリスクに基づく条件付きアクセス ポリシーの一部として SSPR を使用する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)ことをお勧めします。

#### 強力な資格情報の管理

パスワード自体は、悪意のあるユーザーによる環境へのアクセスを阻止できるほど安全ではありません。 少なくとも、特権アカウントを持つすべてのユーザーが、多要素認証 を有効にする必要があります。 理想的には、[統合された登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)を有効にし、すべてのユーザーに対して、[統合された登録エクスペリエンス](https://support.microsoft.com/account-billing/set-up-your-security-info-from-a-sign-in-prompt-28180870-c256-4ebf-8bd7-5335571bf9a8)を使用して多要素認証と SSPR に登録するように要求する必要があります。 最終的には、予期しない状況によるロックアウトのリスクを軽減するために、[回復性を提供する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-resilient-controls)戦略を採用することをお勧めします。

[Image: 統合されたユーザー エクスペリエンスのフロー]

#### オンプレミスの停止に対する認証の回復性

Microsoft Entra のパスワード ハッシュ同期 (PHS) と Microsoft Entra の多要素認証を使用すると、よりシンプルな管理と漏洩した資格情報の検出が可能になるだけでなく、[NotPetya](https://www.microsoft.com/security/blog/2018/02/05/overview-of-petya-a-rapid-cyberattack/) などのサイバー攻撃によってオンプレミスの停止が発生しても、ユーザーはサービスとしてのソフトウェア (SaaS) アプリケーションや Microsoft 365 にアクセスできます。 また、フェデレーションと組み合わせると、PHS を有効にすることもできます。 PHS を有効にすると、フェデレーション サービスが使用できない場合に認証のフォールバックが可能になります。

オンプレミスの組織に障害回復戦略がない場合や、Microsoft Entra ID と統合されていない場合は、Microsoft Entra PHS をデプロイし、PHS を含むディザスター リカバリー計画を定義する必要があります。 Microsoft Entra PHS を有効にすると、オンプレミスの Active Directory が使用できない場合に、ユーザーが Microsoft Entra ID に対して認証を行うことができるようになります。

[Image: パスワード ハッシュの同期フロー]

認証オプションに対する理解を深めるには、「[Microsoft Entra ハイブリッド ID ソリューションの適切な認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)」を参照してください。

#### プログラムによる資格情報の使用

PowerShell を使用する Microsoft Entra ID スクリプトや Microsoft Graph API を使用するアプリケーションには、セキュリティで保護された認証が必要です。 このようなスクリプトやツールを実行する資格情報の管理が不十分な場合は、資格情報の盗難のリスクが高まります。 ハードコーディングされたパスワードまたはパスワード プロンプトに依存するスクリプトまたはアプリケーションを使用している場合は、まず、構成ファイルまたはソース コード内のパスワードを確認してから、それらの依存関係を置き換えて、可能な限り Azure マネージド ID、統合 Windows 認証、または[証明書](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-prerequisites-for-reporting-api)を使用する必要があります。 以前のソリューションに可能性がないアプリケーションの場合は、[Azure Key Vault](https://azure.microsoft.com/services/key-vault/) の使用を検討してください。

パスワード資格情報を持つサービス プリンシパルがあることを確認し、それらのパスワード資格情報がスクリプトやアプリケーションによってどのように保護されているかわからない場合は、アプリケーションの所有者に問い合わせて使用パターンの理解を深めてください。

また、パスワード資格情報を持つサービス プリンシパルがある場合は、アプリケーション所有者に連絡して使用パターンを把握することもお勧めします。

### 認証エクスペリエンス

#### オンプレミスの認証

統合 Windows 認証 (IWA) を使用したフェデレーション認証や、パスワード ハッシュ同期またはパススルー認証を使用したシームレス シングル サインオン (SSO) のマネージド認証は、オンプレミスのドメイン コントローラーへの通信経路のある企業ネットワーク内の場合は最適なユーザー エクスペリエンスです。 これにより、資格情報プロンプトによる疲労が最小限に抑えられ、ユーザーがフィッシング攻撃の犠牲になるリスクが軽減されます。 PHS または PTA によるクラウドで管理された認証を既に使用していても、オンプレミスで認証するときにユーザーがパスワードを入力する必要がある場合は、すぐに[シームレス SSO をデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)必要があります。 一方、クラウドで管理された認証に最終的に移行する予定がある場合は、移行プロジェクトの一環としてシームレス SSO を実装する必要があります。

#### デバイスの信頼のアクセス ポリシー

組織内のユーザーと同様、デバイスは保護対象となる主要なアイデンティティです。 デバイスの ID を使用して、いつでもどこからでもリソースを保護できます。 デバイスを認証し、その信頼の種類を考慮することで、次のようなセキュリティ体制と使用可能性が向上します。

- デバイスが信頼されている場合に、多要素認証などを使用してユーザー体験を阻害する要素を回避する
- 信頼されていないデバイスからのアクセスをブロックする
- Windows 10 デバイスの場合は、[オンプレミスのリソースへのシングル サインオンをシームレスに](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources)提供します。

この目標は、次のいずれかの方法を使用してデバイス ID を取り込んで、Microsoft Entra ID で管理することで実行できます。

- 組織は、[Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune) を使用してデバイスを管理し、コンプライアンス ポリシーを適用して、デバイスの正常性を証明し、デバイスが準拠しているかどうかに基づいて条件付きアクセス ポリシーを設定することができます。 Microsoft Intune では、iOS デバイス、Mac デスクトップ (JAMF 統合経由)、Windows デスクトップ (Windows 10 のモバイル デバイス管理のネイティブな使用、および Microsoft Configuration Manager との共同管理)、および Android モバイル デバイスを管理できます。
- [Microsoft Entra ハイブリッド参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)では、Active Directory ドメイン参加済みコンピューター デバイスがある環境で、グループ ポリシーまたは Microsoft Configuration Manager を使用した管理を提供します。 組織は、シームレス SSO を使用した PHS または PTA のいずれかを使用して、マネージド環境をデプロイできます。 Microsoft Entra ID に自分のデバイスを取り込むと、クラウドとオンプレミスのリソースでの SSO を使用したユーザーの生産性を最大化でき、同時に[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を使用したクラウドとオンプレミスのリソースへのアクセスをセキュリティで保護することができます。

クラウドに登録されていないドメイン参加済み Windows デバイス、またはクラウドに登録されていても条件付きアクセス ポリシーがないドメイン参加済み Windows デバイスがある場合は、登録されていないデバイスを登録する必要があり、いずれにしても条件付きアクセス ポリシーで[制御として Microsoft Entra ハイブリッド参加を使用](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)する必要があります。

[Image: ハイブリッド デバイスを必要とする条件付きアクセス ポリシーでのアクセス許可のスクリーンショット]

MDM または Microsoft Intune を使用してデバイスを管理していても、条件付きアクセス ポリシーでデバイス制御を使用していない場合は、それらのポリシーで制御として [\[デバイスは準拠としてマーク済みである必要がある\]](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant#require-device-to-be-marked-as-compliant) を使用することをお勧めします。

[Image: デバイス準拠を必要とする条件付きアクセス ポリシーでの許可のスクリーンショット]

##### デバイスの信頼のアクセス ポリシーに関する推奨資料

- [Microsoft Entra ハイブリッド参加の実装を計画する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)
- [ID とデバイスのアクセスの構成](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/microsoft-365-policies-configurations)

#### Windows Hello for Business

Windows 10 では、[Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification) によって PC 上のパスワードが強力な 2 要素認証に置き換えられます。 Windows Hello for Business を使用すると、ユーザーにとってより効率的な多要素認エクスペリエンスを実現し、パスワードへの依存を減らすことができます。 Windows 10 デバイスのロールアウトを開始していない場合、または部分的にデプロイしただけの場合は、Windows 10 にアップグレードして、すべてのデバイスで [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-manage-in-organization) を有効することをお勧めします。

パスワードレス認証の詳細については、[Microsoft Entra ID でのパスワードレスの環境](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)に関する記事を参照してください。

### アプリケーションの認証と割り当て

#### アプリのシングル サインオン

標準化されたシングル サインオンのメカニズムを社内全体に提供することは、最良のユーザー エクスペリエンス、リスクの削減、報告する能力、およびガバナンスにとって不可欠です。 Microsoft Entra ID で SSO をサポートしていても、現時点ではローカル アカウントを使用するように構成されているアプリケーションを使用している場合は、そのようなアプリケーションを Microsoft Entra ID で SSO を使用するように再構成する必要があります。 同様に、Microsoft Entra ID で SSO をサポートしていても、別の ID プロバイダーを使用している場合は、そのようなアプリケーションも Microsoft Entra ID で SSO を使用するように再構成する必要があります。 フェデレーション プロトコルはサポートしていなくても、フォーム ベースの認証をサポートしているアプリケーションの場合は、Microsoft Entra アプリケーション プロキシを使用して[パスワード保管](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-password-vaulting)を使用するようにアプリケーションを構成することをお勧めします。

注

組織内の管理されていないアプリケーションを検出するメカニズムがない場合は、[Microsoft Defender for Cloud Apps](https://www.microsoft.com/enterprise-mobility-security/cloud-app-security) などのクラウド アプリケーション セキュリティ ブローカー (CASB) を使用して検出プロセスを実装することをお勧めします。

最後に、Microsoft Entra アプリ ギャラリーがあり、Microsoft Entra ID で SSO をサポートするアプリケーションを使用している場合は、[アプリ ギャラリーでアプリケーションを一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)ことをお勧めします。

##### シングル サインオンに関する推奨資料

- [Microsoft Entra ID でのアプリケーション アクセスとシングル サインオンとは](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)

#### AD FS アプリケーションから Microsoft Entra ID に移行する

[AD FS から Microsoft Entra ID にアプリを移行](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)すると、セキュリティの機能の追加、より一貫性のある管理容易性、および共同作業エクスペリエンスの向上が可能になります。 Microsoft Entra ID で SSO をサポートしている AD FS に構成されているアプリケーションがある場合は、それらのアプリケーションを Microsoft Entra ID で SSO を使用するように再構成する必要があります。 Microsoft Entra ID でサポートされていない一般的でない構成の AD FS で構成されたアプリケーションがある場合は、アプリの所有者に問い合わせて、特別な構成がアプリケーションの絶対的な要件であるかどうかを把握する必要があります。 必要でない場合は、Microsoft Entra ID で SSO を使用するようにアプリケーションを再構成する必要があります。

[Image: プライマリの ID プロバイダーとしての Microsoft Entra ID]

注

[Microsoft Entra Connect Health for AD FS](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs) を使用すると、Microsoft Entra ID に移行できる可能性がある各アプリケーションの構成の詳細情報を収集できます。

#### アプリケーションへのユーザーの割り当て

[アプリケーションへのユーザーの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)はグループを使用すると、優れた柔軟性と大規模な管理が可能になるため、最適にマッピングされます。 グループを使用する利点には、属性ベースの[動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)と[アプリ所有者への委任](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)などがあります。 そのため、既にグループを使用して管理している場合は、管理を大規模に向上させるために次の操作を行うことをお勧めします。

- グループ管理とガバナンスをアプリケーション所有者に委任します。
- アプリケーションへのセルフサービス アクセスを許可します。
- ユーザー属性が一貫してアプリケーションへのアクセスを決定できる場合は、動的メンバーシップ グループを定義します。
- [Microsoft Entra アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)を使用して、アプリケーションへのアクセスに使用されるグループに構成証明を実装します。

一方、個々のユーザーに割り当てられているアプリケーションが見つかった場合は、それらのアプリケーションに[ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/)を実装してください。

##### アプリケーションへのユーザーの割り当てに関する推奨資料

- [Microsoft Entra ID のアプリケーションにユーザーとグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)
- [Microsoft Entra ID でアプリ登録のアクセス許可を委任する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-app-roles)
- [Microsoft Entra ID の動的グループ メンバーシップ ルール](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)

### アクセス ポリシー

#### ネームド ロケーション

Microsoft Entra ID で[ネームド ロケーション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)を使用すると、組織内の信頼できる IP アドレス範囲にラベルを付けることができます。 Microsoft Entra ID では、ネームド ロケーションを使用して以下を実現します。

- リスク イベントの誤判定を防ぎます。 信頼できるネットワークの場所からサインインすることで、ユーザーのサインイン リスクが低下します。
- [場所ベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)を構成する。

[Image: ネームド ロケーション]

優先度に基づいた次の表を使用して、組織のニーズに最も合った推奨される解決策を見つけてください。

| **優先度** | **シナリオ** | **推奨** |
| --- | --- | --- |
| 1 | PHS または PTA を使用していて、ネームド ロケーションが定義されていない場合 | ネームド ロケーションを定義して、リスク イベントの検出を改善します |
| 2 | フェデレーションされていて、"insideCorporateNetwork" 要求を使用しておらず、ネームド ロケーションが定義されていない場合 | ネームド ロケーションを定義して、リスク イベントの検出を改善します |
| 3 | 条件付きアクセス ポリシーでネームド ロケーションを使用しておらず、条件付きアクセス ポリシーにリスクまたはデバイス制御がない場合 | 条件付きアクセス ポリシーを、ネームド ロケーションを含めるように構成します |
| 4 | フェデレーションされていて、"insideCorporateNetwork" 要求を使用していて、ネームド ロケーションが定義されていない場合 | ネームド ロケーションを定義して、リスク イベントの検出を改善します |
| 5 | ネームド ロケーションではなく多要素認証で信頼された IP アドレスを使用していて、それらを信頼済みとしてマークする場合 | ネームド ロケーションを定義して、リスク イベントの検出を改善するためにそれらに信頼済みとしてマークします |

#### リスクベースのアクセス ポリシー

Microsoft Entra ID は、すべてのサインインとすべてのユーザーのリスクを算出することができます。 アクセス ポリシーの基準としてリスクを使用すると、ユーザー エクスペリエンスを向上させることができます。たとえば、認証プロンプトが減ってセキュリティが向上したり、ユーザーに必要なときにのみプロンプトが表示されたり、応答と修復を自動化したりできます。

[Image: サインイン リスク ポリシー]

アクセス ポリシーでのリスクの使用をサポートする Microsoft Entra ID P2 ライセンスを既に所有していても、使用されていない場合は、セキュリティ体制にリスクを追加することを強くお勧めします。

##### リスクベースのアクセス ポリシーの推奨資料

- [サインイン リスク ポリシーを構成する手順](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)
- [ユーザー リスク ポリシーを構成する手順](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)

#### クライアント アプリケーションのアクセス ポリシー

Microsoft Intune アプリケーション管理 (MAM) を使用すると、ストレージの暗号化、PIN、リモート ストレージのクリーンアップなどのデータ保護制御を、Outlook Mobile などの互換性のあるクライアント モバイル アプリケーションにプッシュすることができます。 さらに、条件付きアクセス ポリシーを作成して、承認済みまたは互換性のあるアプリから Exchange Online などのクラウド サービスへの[アクセスを制限](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)できます。

従業員が Office モバイル アプリなどの MAM 対応アプリケーションをインストールして、Exchange Online や Microsoft 365 の SharePoint などの会社のリソースにアクセスし、BYOD (Bring Your Own Device) もサポートしている場合は、アプリケーションの MAM ポリシーをデプロイして、MDM に登録されていない個人所有のデバイスのアプリケーション構成を管理してから、MAM 対応クライアントからのアクセスのみを許可するように条件付きアクセス ポリシーを更新することをお勧めします。

[Image: 条件付きアクセスの付与の制御]

従業員が企業リソースに対して MAM 対応アプリケーションをインストールし、Intune のマネージド デバイスでアクセスを制限する必要がある場合は、個人用デバイスのアプリケーション構成を管理し、MAM 対応クライアントからのアクセスのみを許可するように条件付きアクセス ポリシーを更新するために、アプリケーション MAM ポリシーのデプロイを検討する必要があります。

#### 条件付きアクセスの実装

条件付きアクセスは、組織のセキュリティ体制を向上させるための重要なツールです。 そのため、次のベスト プラクティスに従うことが重要です。

- すべての SaaS アプリケーションに少なくとも 1 つのポリシーが適用されていることを確認します
- ロックアウトのリスクを回避するために、 **[すべてのアプリ]** フィルターを**ブロック**制御と組み合わせないようにします
- **[すべてのユーザー]** をフィルターとして使用せず、誤って **[ゲスト]** を追加しないようにします
- **すべての "レガシ" ポリシーを Azure Portal に移行します**
- ユーザー、デバイス、およびアプリケーションのすべての条件をキャッチします
- [ユーザーごとの MFA](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access) を使用するのではなく、条件付きアクセス ポリシーを使用して **多要素認証を実装**します
- 複数のアプリケーションに適用できる重要なポリシーの小さいセットを用意します
- 空の例外グループを定義し、それらをポリシーに追加して例外戦略を設定します
- 多要素認証の制御のない[緊急用](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning#break-glass-what-to-do-in-an-emergency)アカウントを計画します
- 複数の Microsoft 365 クライアント アプリケーション (例: Teams、OneDrive、Outlook など) にわたって、一貫したエクスペリエンスを確保します。 そのためには、Exchange Online や Microsoft 365 の Sharepoint などのサービスに対して同じ一連の制御を実装します
- ポリシーへの割り当ては、個人ではなくグループを使用して実装する必要があります
- ポリシーで使用されている例外グループを定期的にレビューして、ユーザーがセキュリティ体制外にある時間を制限します。 Microsoft Entra ID P2 を所有している場合は、アクセス レビューを使用してプロセスを自動化できます

##### 条件付きアクセスに関する推奨資料

- [Microsoft Entra ID での条件付きアクセスのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [ID とデバイスのアクセスの構成](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/microsoft-365-policies-configurations)
- [Microsoft Entra の条件付きアクセス設定のリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions)
- [一般的な条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)

### 外部からのアクセス

#### レガシ認証

多要素認証などの強力な資格情報は、レガシ認証プロトコルを使用してアプリを保護することができないため、悪意のあるユーザーによって優先される攻撃ベクトルになります。 アクセスのセキュリティ体制を向上させるには、レガシ認証をロックダウンすることが重要です。

レガシ認証は、アプリで使用される以下のような認証プロトコルを指す用語です。

- 先進認証を使用していない古い Office クライアント (Office 2010 クライアントなど)
- インターネット メッセージ アクセス プロトコル (IMAP)、簡易メール転送プロトコル (SMTP)、ポイント オブ プレゼンス (POP) などのメール プロトコルを使用するクライアント

攻撃者はこれらのプロトコルを使用することを非常に好みます。実際に、ほぼ [100% のパスワードのスプレー攻撃](https://techcommunity.microsoft.com/t5/Azure-Active-Directory-Identity/Your-Pa-word-doesn-t-matter/ba-p/731984)がレガシ認証プロトコルを使用しています。 ハッカーは多要素認証やデバイス認証などの追加のセキュリティの問題に必要な対話型サインインをサポートしていないため、レガシ認証プロトコルを使用します。

環境でレガシ認証が広く使用されている場合は、レガシ クライアントをできるだけ早く[先進認証](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/modern-auth-for-office-2013-and-2016)をサポートするクライアントに移行するように計画する必要があります。 同じトークンで、一部のユーザーが既に先進認証を使用していても、他のユーザーがまだレガシ認証を使用している場合は、次の手順を実行してレガシ認証クライアントをロックダウンする必要があります。

1. [サインイン アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins) を使用して、レガシ認証をまだ使用しているユーザーを特定し、修復を計画します。

    1. 影響を受けるユーザーに対して先進認証対応のクライアントにアップグレードします。
    2. 次の手順ごとにロックダウンするように、カットオーバー期間を計画します。
    3. レガシ認証にハードの依存関係があるレガシ アプリケーションを特定します。 下記の手順 3 を参照してください。
2. レガシ認証を使用していないユーザーが、より多くの露出を回避するために、ソース (たとえば、Exchange メールボックス) でレガシ プロトコルを無効にします。
3. 残りのアカウント (理想的にはサービス アカウントなどの人間以外の ID) では、認証後に[従来のプロトコルを制限するために条件付きアクセス](https://techcommunity.microsoft.com/t5/Azure-Active-Directory-Identity/Azure-AD-Conditional-Access-support-for-blocking-legacy-auth-is/ba-p/245417)を使用します。

##### レガシ認証に関する推奨資料

- [Exchange Server のメールボックスへの POP3 またはインターネット メッセージ アクセス プロトコル v4 (IMAP4) アクセスを有効化または無効化する](https://learn.microsoft.com/ja-jp/exchange/clients/pop3-and-imap4/configure-mailbox-access)

#### 同意の付与

不正な同意の付与攻撃では、攻撃者は、連絡先情報、メール、ドキュメントなどのデータへのアクセスを要求する Microsoft Entra 登録済みのアプリケーションを作成します。 ユーザーは、悪意のある Web サイトにアクセスしたときに、フィッシング攻撃を介して悪意のあるアプリケーションに同意を与える可能性があります。

以下は、Microsoft クラウド サービスを精査する場合に必要となるアクセス許可を持つアプリのリストです。

- アプリまたは委任された \*.ReadWrite アクセス許可を持つアプリ
- ユーザーの代わりにメールの読み取り、送信、または管理を行うことができる、委任されたアクセス許可を持つアプリ
- 次のアクセス許可の使用を許可されているアプリケーション：

| リソース | アクセス許可 |
| --- | --- |
| エクスチェンジ・オンライン | EAS.AccessAsUser.All |
|  | EWS.AccessAsUser.All |
|  | メールを読む |
| Microsoft Graph API | メールを読む |
|  | Mail.Read.Shared |
|  | Mail.ReadWrite |

- アプリには、サインインしたユーザーの、完全なユーザー偽装が許可されています。 例えば次が挙げられます。

| リソース | アクセス許可 |
| --- | --- |
| Microsoft Graph API | Directory.AccessAsUser.All |
| Azure の REST API | ユーザーなりすまし |

このシナリオを回避するには、[Office 365 での不正な同意の付与の検出と修復](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/detect-and-remediate-illicit-consent-grants) に関するページを参照して、不正な許可があるアプリケーションや必要以上に許可がされているアプリケーションを特定して修正する必要があります。 次に、[セルフサービスを完全に削除し](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)、[ガバナンスプロシージャを確立](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow) します。 最後に、アプリのアクセス許可の定期的なレビューをスケジュールし、不要な場合には削除します。

##### 同意の付与に関する推奨資料

- [Microsoft Graph のアクセス許可の概要](https://learn.microsoft.com/ja-jp/graph/permissions-overview)
- [Microsoft Graph API のアクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-reference)

#### ユーザーとグループの設定

次に示すのは、明示的なビジネス ニーズがない場合にロックダウンできるユーザーとグループの設定です。

##### ユーザー設定

- **外部ユーザー** - 外部コラボレーションは、Teams、Power BI、Microsoft 365 の SharePoint、Azure Information Protection などのサービスを使用して、企業内で有機的に発生する場合があります。 ユーザーが開始した外部コラボレーションを制御するための明示的な制約がある場合は、[Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)やヘルプ デスクの利用などの制御された運用を使用して、外部ユーザーを有効にすることをお勧めします。 サービスに対して有機的な外部コラボレーションを許可しない場合は、[外部ユーザーの招待からメンバーを完全にブロックする](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)ことができます。 また、外部ユーザーの招待で[特定のドメインを許可またはブロック](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)することもできます。
- **アプリの登録** - アプリの登録が有効になっている場合、エンド ユーザーはアプリケーション自体をオンボードし、データへのアクセスを許可することができます。 アプリの登録の典型的な例としては、Outlook プラグインを有効にするユーザー、またはメールやカレンダーを読んだりメールを送信したりするための音声アシスタント (Alexa や Siri など) があります。 顧客がアプリの登録を無効にすると決めた場合は、アプリケーションを管理者アカウントに登録する必要があり、多くの場合はプロセスを運用化するプロセスを設計する必要があるため、InfoSec チームと IAM チームは例外 (ビジネス要件に基づいて必要なアプリの登録) の管理に関与する必要があります。
- **管理ポータル** - 組織は Azure Portal の Microsoft Entra ブレードをロックダウンして、管理者以外が Azure portal 内の Microsoft Entra 管理にアクセスして混乱を招くことができないようにします。 Microsoft Entra 管理ポータルのユーザー設定にアクセスして、アクセスを制限します。

[Image: 管理ポータルの制限されたアクセス]

注

管理者以外は、コマンドラインやその他のプログラム インターフェイスを使用して、引き続き Microsoft Entra 管理インターフェイスにアクセスすることができます。

##### グループ設定

**[セルフサービス グループ管理] / [ユーザーはセキュリティ グループを作成できます] / [Microsoft 365 グループ]。** クラウドにグループの最新のセルフサービス イニシアチブがない場合は、顧客はこの機能を使用する準備が整うまで、無効にすることができます。

##### グループに関する推奨資料

- [Microsoft Entra B2B Collaboration とは](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)
- [アプリケーションを Microsoft Entra ID と統合する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- [Microsoft Entra ID でのアプリ、アクセス許可、同意。](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- [グループを使って、Microsoft Entra ID のリソースへのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups)
- [Microsoft Entra ID でセルフ サービスのアプリケーション アクセス管理を設定する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)

#### 予期しない場所からのトラフィック

攻撃者は世界中のさまざまな場所からやって来ます。 このリスクは、場所が条件である条件付きアクセス ポリシーを使用して管理します。 条件付きアクセス ポリシーの[場所の条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)によって、サインインするビジネス上の理由がない場所からのアクセスをブロックすることができます。

[Image: 新しいネームド ロケーションを作成する]

使用可能な場合は、セキュリティ情報およびイベント管理 (SIEM) ソリューションを使用して、リージョン間のアクセスのパターンを分析および検索します。 SIEM 製品を使用していない場合、または Microsoft Entra ID からの認証情報の取り込みではない場合は、[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview) を使用してリージョン間のアクセスのパターンを特定することをお勧めします。

### アクセスの使用状況

#### アーカイブされ、インシデント対応計画と統合された Microsoft Entra ログ

Microsoft Entra ID のサインイン アクティビティや監査とリスク イベントへのアクセス権を持つことは、トラブルシューティング、利用状況分析、およびフォレンジクスの調査を行うために不可欠です。 Microsoft Entra ID は、保有期間が制限されている REST API を介して、これらのソースへのアクセスを提供します。 セキュリティ情報とイベント管理 (SIEM) システム、またはそれと同等のアーカイブ テクノロジは、監査とサポートの長期ストレージの鍵となります。 Microsoft Entra ログの長期ストレージを有効にするには、既存の SIEM ソリューションに追加するか、[Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations) を使用する必要があります。 インシデント対応計画および調査の一環として使用できるログをアーカイブしてください。

##### ログに関する推奨資料

- [Microsoft Entra 監査 API のリファレンス](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)
- [Microsoft Entra サインイン アクティビティ レポート API のリファレンス](https://learn.microsoft.com/ja-jp/graph/api/resources/signin)
- [Microsoft Entra レポート API と証明書を使ってデータを取得する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-prerequisites-for-reporting-api)
- [Microsoft Entra ID 保護のための Microsoft Graph](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-graph-api)
- [Office 365 管理アクティビティ API のリファレンス](https://learn.microsoft.com/ja-jp/office/office-365-management-api/office-365-management-activity-api-reference)
- [Microsoft Entra ID Power BI コンテンツ パックの使用方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)

### まとめ

セキュリティで保護された ID インフラストラクチャには、12 の側面があります。 このリストは、企業のセキュリティ体制に基づいた資格情報のセキュリティ保護と管理、認証エクスペリエンスの定義、割り当ての委任、使用状況の測定、およびアクセス ポリシーの定義に役立ちます。

- 主要タスクに所有者を割り当てる。
- 脆弱なパスワードや漏洩したパスワードを検出し、パスワードの管理と保護を強化し、リソースへのユーザーのアクセスをさらにセキュリティで保護するソリューションを実装します。
- いつでもどこからでもリソースを保護するために、デバイスの ID を管理します。
- パスワードレス認証をデプロイします。
- 組織全体で標準化されたシングル サインオン メカニズムを提供します。
- AD FS から Microsoft Entra ID にアプリを移行して、セキュリティの強化とより一貫性のある管理を可能にします。
- グループを使用して優れた柔軟性と大規模な管理を可能にすることで、アプリケーションにユーザーを割り当てます。
- リスクベースのアクセス ポリシーを構成します。
- レガシ認証プロトコルをロックダウンします。
- 不正な同意の付与を検出して修復します。
- ユーザーとグループの設定をロックダウンします。
- トラブルシューティング、利用状況分析、フォレンジクス調査のために Microsoft Entra ログの長期ストレージを可能にします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/ops-guide-govern"} -->
## Microsoft Entra ID ガバナンス運用リファレンス ガイド - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-govern
- Service: entra / architecture
- Article date: 2024-08-25
- Summary: この運用リファレンス ガイドでは、ガバナンス管理をセキュリティで保護するために実行する必要がある検査とアクションについて説明します。

[Microsoft Entra 運用リファレンス ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-intro)のこのセクションでは、非特権および特権 ID が付与されたアクセスを評価および証明し、環境への変更を監査および管理するために行う必要があるチェックとアクションについて説明します。

注

これらの推奨事項は公開日の時点で最新ですが、時間の経過と共に変化する可能性があります。 Microsoft の製品とサービスは時間の経過と共に進化するため、継続的にガバナンス プラクティスを評価する必要があります。

### 主要な運用プロセス

#### 主要タスクに所有者を割り当てる

Microsoft Entra ID を管理するには、ロールアウト プロジェクトに含まれていない可能性のある重要な運用タスクとプロセスを継続的に実行する必要があります。 環境を最適化するために、これらのタスクを設定することも重要です。 主なタスクと推奨される所有者は次のとおりです。

| タスク | 所有者 |
| --- | --- |
| SIEM システムで Microsoft Entra 監査ログをアーカイブする | InfoSec 運用チーム |
| 準拠していないマネージド アプリケーションを検出する | IAM 運用チーム |
| アプリケーションへのアクセスを定期的に確認する | InfoSec アーキテクチャ チーム |
| 外部 ID へのアクセスを定期的に確認する | InfoSec アーキテクチャ チーム |
| 特権ロールを持つユーザーを定期的に確認する | InfoSec アーキテクチャ チーム |
| 特権ロールをアクティブ化するためのセキュリティ ゲートを定義する | InfoSec アーキテクチャ チーム |
| 同意の付与を定期的に確認する | InfoSec アーキテクチャ チーム |
| 組織の従業員に基づいてアプリケーションとリソースのカタログとアクセス パッケージを設計する | アプリ所有者 |
| セキュリティ ポリシーを定義して、パッケージにアクセスするユーザーを割り当てます | InfoSec チーム + アプリ所有者 |
| ポリシーに承認ワークフローが含まれる場合、ワークフローの承認を定期的に確認する | アプリ所有者 |
| アクセス レビューを使用して、条件付きアクセス ポリシーなどのセキュリティ ポリシーの例外を確認する | InfoSec 運用チーム |

リストの確認中に、所有者のいないタスクに所有者を割り当てるか、上記のレコメンデーションに一致しない所有者を持つタスクの所有権を調整する必要があることに気付く可能性があります。

##### オーナー推奨の読書

- [Microsoft Entra ID での管理者ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)

#### 構成変更のテスト

ターゲットとなるユーザーのサブセットをロールアウトするといった単純な手法から、並列テスト テナントへの変更の展開まで、テスト時に特別な配慮が必要な変更があります。 テスト戦略を制定していない場合は、表のガイドラインに基づいてテスト アプローチを定義する必要があります。

| シナリオ | 推奨事項 |
| --- | --- |
| 認証の種類をフェデレーションから PHS/PTA に、またはその逆に変更する | [段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout) を使用して、認証の種類を変更した場合の効果をテストします。 |
| 新しい条件付きアクセス ポリシーのロールアウト | 新しい条件付きアクセス ポリシーを作成し、テスト ユーザーに割り当てます。 |
| アプリケーションのテスト環境をオンボードする | アプリケーションを運用環境に追加し、MyApps パネルで非表示にして、品質保証 (QA) フェーズでテスト ユーザーに割り当てます。 |
| 同期規則の変更 | 現在実稼働環境にあるものと同じ構成 (ステージング モードともいう) を使用して、テスト Microsoft Entra Connect で変更を実行し、エクスポート結果を分析します。 満足できたら、準備が整った時点で運用に移行します。 |
| ブランド化の変更 | 別のテスト テナントでテストを実施してください。 |
| 新しい機能の展開 | ターゲットとなるユーザー セットへの展開をこの機能がサポートしている場合は、パイロット ユーザーを特定して構築します。たとえば、セルフサービス パスワード リセットと多要素認証は、特定のユーザーまたはグループをターゲットにすることができます。 |
| Active Directory などのオンプレミス ID プロバイダー (IdP) から Microsoft Entra ID へのアプリケーションの移行 | アプリケーションが複数の IdP 構成 (Salesforce など) をサポートしている場合、変更期間中に両方を構成し、Microsoft Entra ID をテストします (アプリケーションでページを導入する場合)。 アプリケーションが複数の IdP をサポートしていない場合、変更管理期間とプログラムのダウンタイム中にテストをスケジュールします。 |
| 動的メンバーシップ グループのルールを更新する | 新しい規則を使用して、並列ダイナミック グループを作成します。 計算された結果と比較します。たとえば、同じ条件で PowerShell を実行します。テストに合格した場合、古いグループが使用されていた場所をスワップします (可能な場合)。 |
| 製品ライセンスの移行 | Microsoft 365 管理センターの[グループへのライセンスの割り当てまたは割り当て解除を](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)参照してください。 |
| 承認、発行、MFA などの AD FS 規則の変更 | グループ要求を使用して、ユーザーのサブセットをターゲットにします。 |
| AD FS 認証エクスペリエンスまたは同様のファーム全体の変更 | 同じホスト名で並列ファームを作成し、構成の変更を実装し、HOSTS ファイル、NLB ルーティング規則、または同様のルーティングを使用してクライアントからテストします。 ターゲット プラットフォームが HOSTS ファイル (モバイル デバイスなど) をサポートしていない場合、変更を管理します。 |

### アクセスレビュー

#### アプリケーションに対するアクセス レビュー

時間の経過と共に、ユーザーがさまざまなチームや地位を移動するにつれ、リソースへのアクセス権が蓄積する場合があります。 リソース所有者は、アプリケーションへのアクセス権を定期的に確認することが重要です。 この確認プロセスでは、ユーザーのライフサイクル全体で不要になった特権を削除したりします。 Microsoft Entra [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview) を組織で使用することにより、グループ メンバーシップ、エンタープライズ アプリケーションへのアクセス、ロールの割り当てを効率的に管理できます。 リソース所有者は定期的にユーザーのアクセス権を確認し、適切なユーザーのみがアクセスを継続できるようにします。 理想的には、このタスクに Microsoft Entra アクセス レビューを使用することをお勧めします。

[Image: アクセス レビューの開始ページ]

注

アクセス レビューを操作する各ユーザーには、有料の Microsoft Entra ID P2 ライセンスが必要です。

#### 外部 ID に対するアクセス レビュー

必要な時間内に、必要なリソースのみに制限された外部 ID へのアクセスを維持することが重要です。 Microsoft Entra [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)を使用して、すべての外部 ID およびアプリケーション アクセスに対する定期的な自動アクセス レビュー プロセスを確立します。 プロセスが既にオンプレミスに存在する場合は、Microsoft Entra アクセス レビューの使用を検討します。 アプリケーションがインベントリから削除されるか、使用されなくなったら、アプリケーションへのアクセス権を持っていたすべての外部 ID を削除します。

注

アクセス レビューを操作する各ユーザーには、有料の Microsoft Entra ID P2 ライセンスが必要です。

### 特権アカウントの管理

#### 特権アカウントの使用

ハッカーは、多くの場合、管理者アカウントや特権アクセスのその他の要素を標的にして、機密データやシステムにすばやくアクセスします。 特権ロールを持つユーザーは時間の経過と共に蓄積する傾向があるため、定期的に管理者アクセスを確認および管理し、Microsoft Entra ID および Azure リソースへの Just-In-Time 特権アクセスを提供することが重要です。

特権アカウントを管理するプロセスが組織に存在しない場合、または通常のユーザー アカウントを使用してサービスとリソースを管理する管理者が現在いる場合は、すぐに個別のアカウントの使用を開始すべきです。 たとえば、1 つは日常のアクティビティ用にし、もう 1 つは特権アクセス用で MFA を使用して構成します。 さらに、組織に Microsoft Entra ID P2 サブスクリプションがある場合は、すぐに [Microsoft Entra Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure#license-requirements) をデプロイする必要があります。 同じトークン内で、その特権アカウントも確認し、必要に応じて[下位の特権ロールを割り当てます](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning)。

実装が推奨される特権アカウント管理のもう 1 つの側面は、そのようなアカウントに対して、手動で、または [PIM による自動的な方法](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)で[アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-perform-roles-and-resource-roles-review)を定義することです。

##### 特権アカウントの管理に関する推奨資料

- [Microsoft Entra Privileged Identity Management の役割](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-roles)

#### 緊急アクセス アカウント

Microsoft は、組織が[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)ロールが永続的に割り当てられる 2 つのクラウド専用緊急アクセス アカウントを作成することを推奨しています。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 これらのアカウントは、通常のアカウントが使用できない、またはすべての管理者が誤ってロックアウトされたときに限定される緊急時あるいは「ブレイクグラス」という非常事態に使用されます。これらのアカウントの作成は、[緊急アクセスアカウントに関する推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従う必要があります。

#### Azure EA Portalへの特権アクセス

[Azure マイクロソフト エンタープライズ契約 (Azure EA) ポータル](https://azure.microsoft.com/blog/create-enterprise-subscription-experience-in-azure-portal-public-preview/)を使用すると、エンタープライズ内の強力なロールである主要なマイクロソフト エンタープライズ契約に対して Azure サブスクリプションを作成できます。 Microsoft Entra ID の設定よりも前に、このポータルの作成を開始することが一般的です。 この場合、Microsoft Entra ID を使用してポータルをロックダウンし、ポータルから個人アカウントを削除し、適切な委任が行われるようにして、ロックアウトのリスクを軽減する必要があります。

わかりやすく説明すると、EA ポータルの承認レベルが現在 「混合モード」 に設定されている場合、EA ポータルのすべての特権アクセスからすべての [Microsoft アカウント](https://support.skype.com/en/faq/FA12059/what-is-a-microsoft-account) を削除し、Microsoft Entra アカウントのみを使用するように EA ポータルを構成する必要があります。 EA ポータルの委任された役割が構成されていない場合は、部門とアカウントの委任された役割を見つけて実装する必要もあります。

##### 特権アクセスに関する推奨資料

- [Microsoft Entra ID の管理者ロールの権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)

### 権限管理

[エンタイトルメント管理 (EM)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) を使用すると、アプリ所有者はリソースをバンドルし、組織内の特定のペルソナ (内部および外部の両方) に割り当てることができます。 EM を使用すると、ビジネス オーナーは、アクセスを許可し、アクセス期間を設定し、承認ワークフローを許可するガバナンス ポリシーを維持しながら、セルフサービスのサインアップと委任ができます。

注

Microsoft Entra のエンタイトルメント管理には、Microsoft Entra ID P2 ライセンスが必要です。

### まとめ

セキュリティで保護された ID ガバナンスには 8 つの側面があります。 このリストは、非特権および特権 ID に付与されたアクセス権を評価および証明し、環境への変更を監査および管理するために実行する必要があるアクションを特定するのに役立ちます。

- 主要タスクに所有者を割り当てます。
- テスト戦略を実装する。
- Microsoft Entra アクセス レビューを使用して、グループ メンバーシップ、エンタープライズ アプリケーションへのアクセス、ロールの割り当てを効率的に管理する。
- すべての種類の外部 ID およびアプリケーション アクセスに対して、定期的な自動アクセス レビュー プロセスを確立します。
- アクセス レビュー プロセスを確立することで、定期的に管理者アクセスを確認および管理し、Microsoft Entra ID および Azure リソースへの Just-In-Time 特権アクセスを提供する。
- 緊急アカウントをプロビジョニングして、予想外の停止が起こった場合に Microsoft Entra ID を管理できるように備えます。
- Azure EA ポータルへのアクセスをロックする。
- エンタイトルメント管理を実装し、リソースのコレクションに対して管理されたアクセスを提供します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/ops-guide-iam"} -->
## Microsoft Entra ID とアクセス管理の運用リファレンス ガイド - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-iam
- Service: entra / architecture
- Article date: 2024-08-25
- Summary: この運用リファレンス ガイドでは、ID およびアクセス管理をセキュリティで保護するために実行する必要がある検査とアクションについて説明します

[Microsoft Entra 運用リファレンス ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-intro)のこのセクションでは、ID とその割り当てのライフサイクルのセキュリティ保護と管理のために考慮する必要がある検査とアクションについて説明します。

注

これらの推奨事項は公開日の時点で最新ですが、時間の経過と共に変化する可能性があります。 Microsoft の製品とサービスは時間の経過と共に進化するため、継続的に ID プラクティスを評価する必要があります。

### 主要な運用プロセス

#### 主要タスクに所有者を割り当てます。

Microsoft Entra ID を管理するには、ロールアウト プロジェクトに含まれていない可能性のある重要な運用タスクとプロセスを継続的に実行する必要があります。 環境を維持するためには、これらのタスクを設定することも重要です。 主なタスクと推奨される所有者は次のとおりです。

| タスク | 所有者 |
| --- | --- |
| Azure サブスクリプションを作成するプロセスを定義する | 組織によって異なる |
| Enterprise Mobility + Security のライセンスを取得する対象者を決定する | IAM 運用チーム |
| Microsoft 365 のライセンスを取得するユーザーを決定する | 生産性チーム |
| 他のライセンス (Dynamics、Visual Studio Codespaces など) を取得するユーザーを決定する | アプリケーション所有者 |
| ライセンスを割り当てる | IAM 運用チーム |
| ライセンスの割り当てエラーをトラブルシューティングして修復する | IAM 運用チーム |
| Microsoft Entra ID でアプリケーションに ID をプロビジョニングする | IAM 運用チーム |

リストの確認中に、所有者のいないタスクに所有者を割り当てるか、上記のレコメンデーションに一致しない所有者を持つタスクの所有権を調整する必要があることに気付く可能性があります。

##### 所有者の割り当てに関する推奨資料

- [Microsoft Entra ID での管理者ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)

### オンプレミスの ID の同期

#### 同期の問題を特定して解決する

Microsoft では、クラウドに対する同期の問題が発生する可能性があるオンプレミス環境での問題を十分に把握し、理解しておくことをお勧めします。 [IdFix](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/set-up-directory-synchronization) や [Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect#why-use-azure-ad-connect-health) などの自動化されたツールによって大量の誤検知が発生する可能性があるため、エラーが発生したオブジェクトをクリーンアップすることで、100 日以上解決されなかった同期エラーを特定することをお勧めします。 長期にわたって未解決の同期エラーによって、サポート インシデントが発生する可能性があります。 「[同期中のエラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sync-errors)」では、さまざまな種類の同期エラーの概要、これらのエラーを引き起こすシナリオ、エラーを修正する方法について説明します。

#### Microsoft Entra Connect 同期の構成

すべてのハイブリッド エクスペリエンス、デバイスベースのセキュリティ体制、Microsoft Entra ID との統合を有効にするには、従業員が自分のデスクトップにログインするために使うユーザー アカウントを同期する必要があります。

ユーザーがログインするフォレストを同期しない場合は、適切なフォレストから行われるように同期を変更する必要があります。

##### 同期スコープとオブジェクトのフィルター処理

同期する必要がないオブジェクトの既知のバケットを削除すると、次の操作上の利点があります。

- 同期エラーの原因の削減
- 同期サイクルの高速化
- オンプレミスから持ち越される不要物（たとえば、クラウドでは関係のないオンプレミスのサービスアカウントのためのグローバルアドレス一覧の汚染）を削減

注

クラウドにエクスポートされていない多数のオブジェクトをインポートすることがわかっている場合は、OU または特定の属性でフィルター処理する必要があります。

除外するオブジェクトの例を次に示します。

- クラウド アプリケーションに使用されていないサービス アカウント
- リソースへのアクセスを許可するために使用されるものなど、クラウドのシナリオで使用されることを意図していないグループ
- Microsoft Entra B2B コラボレーションで表現されることを意図した外部 ID であるユーザーまたは連絡先
- 従業員がサーバーなどからクラウド アプリケーションにアクセスすることを意図していないコンピューター アカウント

注

1 人のユーザーの ID に、レガシ ドメインの移行、合併、買収などによってプロビジョニングされた複数のアカウントがある場合は、ユーザーが使用するアカウント (ユーザーがコンピューターにログインするために使用するものなど) を毎日同期するだけで済みます。

理想的には、同期するオブジェクトの数を減らすことと規則の複雑さとのバランスを取ることを目指します。 一般に、OU/コンテナーの[フィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering)と、cloudFiltered 属性への単純な属性マッピングとの組み合わせによって、効果的なフィルターの組み合わせが有効になります。

重要

運用環境でグループのフィルター処理を使用する場合は、別のフィルター処理方法に切り替える必要があります。

##### 同期フェールオーバーまたはディザスターリカバリー

Microsoft Entra Connect は、プロビジョニング プロセスにおいて重要な役割を果たします。 何らかの理由で同期サーバーがオフラインになると、オンプレミスへの変更をクラウドで更新できず、ユーザーに対するアクセス問題が生じる可能性があります。 そのため、同期サーバーがオフラインになった後、管理者がすばやく同期を再開できるように、フェールオーバー方式を定義しておくことが重要です。 そのような戦略は次のカテゴリに分類されます。

- **ステージング モードで Microsoft Entra Connect サーバーをデプロイする** - 管理者は、単純な構成の切り替えによってステージング サーバーを運用に "昇格" させることができます。
- **仮想化を使う** - Microsoft Entra Connect が仮想マシン (VM) にデプロイされている場合、管理者は仮想化スタックを適用して VM の実行、移行、迅速な再デプロイをすることができ、それによって同期を再開できます。

組織が同期に関するディザスター リカバリーとフェールオーバー戦略を持っていない場合は、躊躇なく Microsoft Entra Connect をステージング モードでデプロイしてください。 同様に、運用とステージングの構成が一致しない場合は、ソフトウェアのバージョンや構成などの運用構成と一致するように、Microsoft Entra Connect ステージング モードのベースラインを再設定する必要があります。

[Image: Microsoft Entra Connect のステージング モードの構成を示すスクリーンショット。]

##### 常に最新の状態を保つ

Microsoft は Microsoft Entra Connect を定期的に更新しています。 パフォーマンスの向上、バグの修正、および新しい各バージョンで提供される新機能を利用するために、最新の状態に保ってください。

お使いの Microsoft Entra Connect バージョンが 6 か月よりも前の場合は、最新のバージョンにアップグレードすることをお勧めします。

##### ソース アンカー

**ms-DS-consistencyguid** を[ソース アンカー](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts)として使用すると、フォレストおよびドメイン間でのオブジェクトの移行が容易になります。これは、AD ドメインの統合/クリーンアップ、合併、買収、および売却に共通です。

現在 **ObjectGuid** をソース アンカーとして使用している場合は、**ms-DS-ConsistencyGuid** の使用に切り替えることをお勧めします。

##### カスタム規則

Microsoft Entra Connect カスタム規則により、オンプレミスのオブジェクトとクラウド オブジェクトの間の属性のフローを制御できます。 ただし、カスタム規則を乱用したり誤用したりすると、次のようなリスクが生じる可能性があります。

- トラブルシューティングの複雑さ
- 複数のオブジェクトに対して複雑な操作を実行するときのパフォーマンスの低下
- 運用サーバーとステージング サーバーの間で構成が分岐する確率の増加
- (組み込みのルールに使われる) 100 を超える優先順位内でカスタム ルールが作成されている場合、Microsoft Entra Connect のアップグレード時に追加のオーバーヘッドが生じる

過度に複雑な規則を使用している場合は、複雑である理由を調査し、簡略化する機会を見つける必要があります。 同様に、優先順位の値が 100 を超えるカスタム規則を作成した場合は、リスクが生じたり既定の設定と競合したりしないように、規則を修正する必要があります。

カスタム規則の誤用の例を次に示します。

- **ディレクトリ内のダーティ データの補正** - この場合は、AD チームの所有者と協力して、修復タスクとしてディレクトリ内のデータをクリーンアップし、不適切なデータが再度取り込まれないないようにプロセスを調整することをお勧めします。
- **個々のユーザーの 1 回限りの修復** - 通常は特定のユーザーの問題のため、特殊なケースの外れ値を検出する規則を見つけることが一般的です。
- **複雑過ぎる「クラウド フィルタリング」**: オブジェクトの数を減らすのは良いことですが、多過ぎる同期規則を使用して、複雑過ぎる同期スコープを作成してしまうリスクがあります。 OU のフィルター処理以外のオブジェクトを含めたり除外したりする複雑なロジックがある場合は、同期の外部でこのロジックを処理することをお勧めします。単純な同期規則でフローできる単純な「クラウド フィルターされた」属性でオブジェクトにラベルを付けることでこれができます。

##### Microsoft Entra Connect 構成ドキュメント

[Microsoft Entra Connect Configuration Documenter](https://github.com/Microsoft/AADConnectConfigDocumenter) は、Microsoft Entra Connect インストールのドキュメントを生成するために使用できるツールです。 このツールを使用すると、同期構成について理解を深め、物事を正しく行う自信を深めるのに役立ちます。 また、このツールは、どのような変更が発生したか、Microsoft Entra Connect の新しいビルドまたは構成をいつ適用したか、どのカスタム同期規則が追加または更新されたかも示します。

このツールの最新の機能は次のとおりです。

- Microsoft Entra Connect 同期の完全な構成に関するドキュメント。
- 2 つの Microsoft Entra Connect 同期サーバーの構成の変更、または特定の構成ベースラインの変更に関するドキュメント。
- サーバー間で同期規則の相違点またはカスタマイズを移行するための PowerShell デプロイ スクリプトの生成。

### アプリとリソースへの割り当て

#### Microsoft クラウド サービスのグループベースのライセンス

Microsoft Entra ID は、Microsoft クラウド サービスの[グループベースのライセンス](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)を使ってライセンスの管理を効率化します。 このようにして IAM は、グループのインフラストラクチャと、組織内の適切なチームに対するそれらのグループの委任管理を提供します。 Microsoft Entra ID でグループのメンバーシップを設定するには、次のような複数の方法があります。

- **オンプレミスからの同期** - グループはオンプレミスのディレクトリから取得できます。これは、Microsoft 365 でライセンスを割り当てるために拡張できるグループ管理プロセスを確立した組織に適しています。
- **動的メンバーシップ グループ** - 属性ベースのグループは、ユーザー属性に基づいた式に基づいてクラウドで作成できます。たとえば、部署は「営業」となります。 Microsoft Entra ID は、定義された式との一貫性を維持したまま、グループのメンバーを保持します。 ライセンスの割り当てに動的メンバーシップ グループを使用すると、属性ベースのライセンスの割り当てが有効になります。これは、ディレクトリ内のデータ品質が高い組織に適しています。
- **委任された所有権** - グループはクラウドで作成でき、所有者として指定できます。 これにより、コラボレーション チームや BI チームなどのビジネス オーナーが、アクセス権を持つ必要があるユーザーを定義できるようになります。

現在、手動プロセスを使用してライセンスとコンポーネントをユーザーに割り当てている場合は、グループベースのライセンスを実装することをお勧めします。 現在のプロセスがライセンス エラーを監視していないか、あるいはどのライセンスが割り当てられているか使用可能かを決定する場合は、プロセスの改善を定義する必要があります。 プロセスがライセンス エラーに対処し、ライセンスの割り当てを監視していることを確認します。

ライセンス管理のもう 1 つの側面は、組織内の職務に基づいて有効にする必要があるサービス プラン (ライセンスのコンポーネント) の定義です。 不要なサービス プランにアクセス権を付与すると、ユーザーがトレーニングを受けていない、または使用すべきでないツールが Microsoft 365 ポータルに表示される可能性があります。 これにより、ヘルプ デスクの量や不要なプロビジョニングが追加され、コンプライアンスとガバナンスを危険にさらす可能性があります。たとえば、コンテンツを共有することが許可されていない個人に OneDrive をプロビジョニングする場合などです。

ユーザーに対するサービス プランを定義するには、次のガイドラインに従ってください。

- 管理者は、ロールに基づいてユーザーに提供されるサービス プランの「バンドル」を定義する必要があります。たとえば、事務系従業員と現場作業員との違いに基づいて定義します。
- クラスターごとにグループを作成し、サービス プランにライセンスを割り当てます。
- 必要に応じて、ユーザーのパッケージを保持する属性を定義できます。

重要

Microsoft Entra ID のグループベースのライセンスでは、ユーザーのライセンス付与に関してエラー状態の概念が導入されています。 ライセンスに関するエラーが発生した場合は、直ちにライセンスの割り当ての問題を[特定して解決](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true#manage-group-based-licensing-errors)してください。

[Image: 自動的に生成されたコンピューター画面の説明のスクリーンショット]

##### ライフサイクル管理

オンプレミスのインフラストラクチャに依存する [Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/) やサードパーティ システムなどのツールを現在使用している場合は、既存のツールから割り当てをオフロードすることをお勧めします。 代わりに、グループ ベースのライセンスを実装し、[動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)に基づいてグループ ライフサイクル管理を定義する必要があります。

既存のプロセスで、新しい従業員や組織を離れる従業員が考慮されていない場合は、動的メンバーシップ グループに基づいてグループベースのライセンスをデプロイし、メンバーシップ グループのライフサイクルを定義する必要があります。 最後に、ライフサイクル管理が行われていないオンプレミスのグループに対してグループベースのライセンスをデプロイする場合は、クラウド グループを使用して、委任された所有権や属性ベースの動的メンバーシップ グループなどの機能を有効にすることを検討してください。

#### "すべてのユーザー" グループを使用したアプリの割り当て

リソースの所有者は、**すべてのユーザー**グループに、実際には**企業の従業員**と**ゲスト**の両方が含まれている可能性があるときに、**企業の従業員**のみが含まれていると考えることがあります。 そのため、**すべてのユーザー** グループを使用してアプリケーションの割り当てを行い、SharePoint コンテンツやアプリケーションなどのリソースへのアクセスを許可する場合は、特別な注意を払う必要があります。

重要

**すべてのユーザー** グループが有効になっていて、条件付きアクセス ポリシー、アプリ、またはリソースの割り当てに使用されている場合に、ゲスト ユーザーを含めたくない場合は、必ず[グループをセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/external-id/use-dynamic-groups)してください。 さらに、**企業の従業員**のみが含まれるグループを作成して割り当てることによって、ライセンスの割り当てを修正する必要があります。 一方で、**すべてのユーザー** グループが有効になっているが、リソースへのアクセスを許可するために使用されていない場合は、組織の運用上のガイダンスで、そのグループ (**企業の従業員**と**ゲスト**を含む) を意図的に使用していることを確認してください。

#### 自動化されたアプリへのユーザー プロビジョニング

アプリケーションへの[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)は、複数のシステムにわたった ID のプロビジョニング、プロビジョニング解除、およびライフサイクルを作成するための最良の方法です。

現在アドホック方式でアプリをプロビジョニングしている場合や、CSV ファイル、JIT、またはライフサイクル管理に対応していないオンプレミス ソリューションを使用している場合は、Microsoft Entra ID を使用して[アプリケーションのプロビジョニングを実行](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning#how-do-i-set-up-automatic-provisioning-to-an-application)することをお勧めします。 このソリューションは、サポートされているアプリケーションを提供し、Microsoft Entra ID でまだサポートされていないアプリケーションの一貫したパターンを定義します。

[Image: Microsoft Entra プロビジョニング サービス]

#### Microsoft Entra Connect 差分同期サイクルのベースライン

組織における変更の量を把握し、予測可能な同期時間を確保するのに時間がかかりすぎないようにすることが重要です。

[既定の差分同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler)の頻度は 30 分です。 差分同期が常に 30 分以上かかる場合、またはステージングと運用の差分同期のパフォーマンスに大きな違いがある場合は、[Microsoft Entra Connect のパフォーマンスに影響を及ぼす要因](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-performance-factors)を調査して確認する必要があります。

##### Microsoft Entra Connect のトラブルシューティングについて推奨される資料

- [IdFix ツールを使用した Microsoft 365 との同期のためのディレクトリ属性の準備](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/set-up-directory-synchronization)
- [Microsoft Entra Connect: 同期中のエラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sync-errors)

### まとめ

セキュリティで保護された ID インフラストラクチャには、5 つの側面があります。 このリストは、組織内の ID とそのエンタイトルメントのライフサイクルをセキュリティで保護して管理するために必要なアクションをすばやく見つけて実行するのに役立ちます。

- 主要タスクに所有者を割り当てる。
- 同期の問題を見つけて解決します。
- ディザスター リカバリーのためのフェールオーバー戦略を定義します。
- ライセンスの管理とアプリの割り当てを効率化します。
- アプリへのユーザー プロビジョニングを自動化します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/ops-guide-intro"} -->
## Microsoft Entra 操作参照ガイド - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-intro
- Service: entra / architecture
- Article date: 2022-08-17
- Summary: この運用リファレンス ガイドでは、ID とアクセスの管理、認証、ガバナンス、操作をセキュリティで保護して維持するために実行する必要があるチェックとアクションについて説明します。

この運用リファレンス ガイドでは、次の領域をセキュリティ保護して維持するために実行する必要があるチェックとアクションについて説明します。

- **[ID とアクセスの管理](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-iam)** - ID とそのエンタイトルメントのライフサイクルを管理する機能。
- **[認証管理](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-auth)** - 資格情報の管理、認証エクスペリエンスの定義、割り当ての委任、使用状況の測定、および企業のセキュリティ体制に基づいたアクセス ポリシーの定義を行う機能。
- **[ガバナンス](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-govern)** - アクセス許可されている非特権 および特権 ID の評価と証明、監査、環境に対する変更の管理を行う機能。
- **[操作](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-ops)** - Microsoft Entra ID の操作を最適化します。

ここに示すいくつかの推奨事項は、一部のお客様の環境には適用されない場合があります。たとえば、組織がパスワード ハッシュ同期を使用する場合、AD FS のベスト プラクティスは適用されないことがあります。

注

これらの推奨事項は公開日の時点で最新ですが、時間の経過と共に変化する可能性があります。 Microsoft の製品とサービスは時間の経過と共に進化するため、継続的に ID プラクティスを評価する必要があります。 推奨事項は、組織が別の Microsoft Entra ID P1 または P2 ライセンスをサブスクライブするときに変更される可能性があります。

### 利害関係者

このリファレンス ガイドの各セクションでは、主要なタスクを正しく計画して実装するために利害関係者を割り当てることを推奨しています。 次の表に、このガイドのすべての利害関係者の一覧を示します。

| 利害関係者 | 説明 |
| --- | --- |
| IAM 運用チーム | このチームは、ID およびアクセス管理システムの日常業務の管理を担当します。 |
| 生産性チーム | このチームは、メール、ファイル共有とコラボレーション、インスタント メッセージング、会議などの生産性アプリケーションを所有し、管理します。 |
| アプリケーション所有者 | このチームは、ビジネスに関する特定のアプリケーションを、通常は組織の技術的な観点から所有します。 |
| InfoSec アーキテクチャ チーム | このチームは、組織の情報セキュリティ プラクティスを計画および設計します。 |
| InfoSec 運用チーム | このチームは、InfoSec アーキテクチャ チームの実装された情報セキュリティ プラクティスを実行および監視します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/ops-guide-ops"} -->
## Microsoft Entra の一般的な運用ガイド リファレンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-ops
- Service: entra / architecture
- Article date: 2022-08-17
- Summary: この操作リファレンス ガイドでは、一般的な操作をセキュリティで保護するために実行する必要があるチェックとアクションについて説明します

[Microsoft Entra 操作リファレンス ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-intro)のこのセクションでは、Microsoft Entra ID の一般的な操作を最適化するために実行する必要があるチェックとアクションについて説明します。

注

これらの推奨事項は公開日の時点で最新ですが、時間の経過と共に変化する可能性があります。 組織は、Microsoft の製品とサービスが時間の経過とともに進化するにつれて、運用プラクティスを継続的に評価する必要があります。

### 主要な運用プロセス

#### 主要タスクに所有者を割り当てる

Microsoft Entra ID を管理するには、ロールアウト プロジェクトに含まれていない可能性のある主要な運用タスクとプロセスを継続的に実行する必要があります。 環境を最適化するために、これらのタスクを設定することも重要です。 主なタスクと推奨される所有者は次のとおりです。

| 課題 | オーナー |
| --- | --- |
| ID セキュリティ スコアの向上を推進する | InfoSec 運用チーム |
| Microsoft Entra Connect サーバーを維持する | IAM 運用チーム |
| IdFix レポートを定期的に実行してトリアージする | IAM 運用チーム |
| Microsoft Entra Connect の同期と AD FS の正常性アラートを優先対応 | IAM 運用チーム |
| Microsoft Entra Connect Health を使用していない場合、お客様には、カスタム インフラストラクチャを監視するための同等のプロセスとツールがあります | IAM 運用チーム |
| AD FS を使用していない場合、顧客には、カスタム インフラストラクチャを監視するための同等のプロセスとツールがあります | IAM 運用チーム |
| ハイブリッド ログの監視: Microsoft Entra プライベート ネットワーク コネクタ | IAM 運用チーム |
| ハイブリッド ログの監視: パススルー認証エージェント | IAM 運用チーム |
| ハイブリッド ログの監視: パスワード ライトバック サービス | IAM 運用チーム |
| ハイブリッド ログの監視: オンプレミスパスワード保護ゲートウェイ | IAM 運用チーム |
| ハイブリッド ログの監視: Microsoft Entra 多要素認証 NPS 拡張機能 (該当する場合) | IAM 運用チーム |

リストを確認しているときに、所有者が空のタスクに所有者を割り当てたり、上記のレコメンデーションに一致しない所有者を持つタスクの所有権を調整したりする必要があることに気付く場合があります。

##### 所有者が推奨する読書

- [Microsoft Entra ID での管理者ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)

### ハイブリッド管理

#### オンプレミス コンポーネントの最新バージョン

オンプレミスコンポーネントの最新バージョンを使用することで、顧客には最新のセキュリティ更新プログラム、パフォーマンス向上、および環境をさらに簡素化するための機能がすべて提供されます。 ほとんどのコンポーネントには自動アップグレード設定があり、アップグレード プロセスが自動化されます。

これらのコンポーネントは次のとおりです。

- Microsoft Entra Connect
- Microsoft Entra プライベート ネットワーク コネクタ
- Microsoft Entra パススルー認証エージェント
- Microsoft エントラ コネクト ヘルス エージェント

1 つが確立されていない限り、これらのコンポーネントをアップグレードし、可能な限り自動アップグレード機能に依存するプロセスを定義する必要があります。 6 か月以上遅れているコンポーネントが見つかる場合は、できるだけ早くアップグレードする必要があります。

##### ハイブリッド管理に関する推奨読書

- [Microsoft Entra Connect: 自動アップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)
- [Microsoft Entra プライベート ネットワーク コネクタについて |自動更新](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#connector-updates)

#### Microsoft Entra Connect Health 警告の基準

組織は [、Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect#what-is-azure-ad-connect-health) と AD FS の監視とレポートのために Microsoft Entra Connect Health を展開する必要があります。 Microsoft Entra Connect と AD FS は重要なコンポーネントであり、ライフサイクル管理と認証が中断され、障害が発生する可能性があります。 Microsoft Entra Connect Health は、オンプレミスの ID インフラストラクチャの監視と分析情報の取得に役立ち、環境の信頼性を確保します。

[Image: Microsoft Entra Connect Heath アーキテクチャ]

環境の正常性を監視するときは、重大度の高いアラートに直ちに対処し、その後に重大度の低いアラートに対処する必要があります。

##### Microsoft Entra Connect Health の推奨読み物

- [Microsoft Entra Connect Health エージェントのインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install)

#### オンプレミスエージェントのログ

一部の ID およびアクセス管理サービスでは、ハイブリッド シナリオを有効にするためにオンプレミスのエージェントが必要です。 たとえば、パスワード リセット、パススルー認証 (PTA)、Microsoft Entra アプリケーション プロキシ、Microsoft Entra 多要素認証 NPS 拡張機能などがあります。 これは、System Center Operations Manager や SIEM などのソリューションを使用してコンポーネント エージェント ログをアーカイブおよび分析することで、運用チームがこれらのコンポーネントの正常性をベースライン化し、監視することが重要です。 Infosec Operations チームまたはヘルプ デスクが、エラーのパターンのトラブルシューティング方法を理解することも同様に重要です。

##### オンプレミスエージェントの推奨される読み取りログ

- [アプリケーション プロキシのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)
- [セルフサービス パスワード リセットのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-sspr)
- [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)
- [Microsoft Entra Connect: パススルー認証のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-pass-through-authentication#collecting-pass-through-authentication-agent-logs)
- [Microsoft Entra 多要素認証 NPS 拡張機能のエラー コードのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-errors)

#### オンプレミス エージェントの管理

ベスト プラクティスを採用すると、オンプレミス エージェントの最適な運用に役立ちます。 次のベスト プラクティスを検討してください。

- プロキシ アプリケーションにアクセスするときに単一障害点を回避することで、シームレスな負荷分散と高可用性を実現するには、コネクタ グループごとに複数の Microsoft Entra プライベート ネットワーク コネクタを使用することをお勧めします。 現在、運用環境のアプリケーションを処理するコネクタ グループにコネクタが 1 つだけある場合は、冗長性のために少なくとも 2 つのコネクタをデプロイする必要があります。
- デバッグ目的でプライベート ネットワーク コネクタ グループを作成して使用すると、シナリオのトラブルシューティングや、新しいオンプレミス アプリケーションのオンボード時に役立ちます。 また、コネクタ マシンに Message Analyzer や Fiddler などのネットワーク ツールをインストールすることもお勧めします。
- 認証フロー中に単一障害点を回避することで、シームレスな負荷分散と高可用性を実現するには、複数のパススルー認証エージェントをお勧めします。 冗長性を確保するために、少なくとも 2 つのパススルー認証エージェントをデプロイしてください。

##### オンプレミス エージェント管理に関する推奨読書

- [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)
- [Microsoft Entra パススルー認証 - クイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-4-ensure-high-availability)

### 大規模な管理

#### ID セキュリティ スコア

[ID セキュリティ スコア](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score)は、組織のセキュリティ体制の定量化可能な尺度を提供します。 報告された調査結果を常に確認して対処し、可能な限り最高のスコアを得るために努力することが重要です。 このスコアは、次のために役立ちます。

- ID セキュリティ体制を客観的に測定する
- ID セキュリティの強化を計画する
- 改善の成功を確認する

[Image: セキュリティ スコア]

組織で現在、ID セキュリティ スコアの変更を監視するプログラムがない場合は、計画を実装し、改善アクションを監視して推進する所有者を割り当てることをお勧めします。 組織は、スコアの影響が 30 を超える改善アクションをできるだけ早く修復する必要があります。

#### 通知

Microsoft は、サービスのさまざまな変更、必要な構成の更新、および管理者の介入を必要とするエラーを通知するために、管理者に電子メール通信を送信します。 お客様が通知メール アドレスを設定して、すべての通知を確認して対処できる適切なチーム メンバーに通知が送信されるようにすることが重要です。 複数の受信者を [メッセージ センター](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/message-center) に追加し、通知 (Microsoft Entra Connect Health 通知を含む) を配布リストまたは共有メールボックスに送信するよう要求することをお勧めします。 電子メール アドレスを持つグローバル管理者アカウントが 1 つしかない場合は、少なくとも 2 つの電子メール対応アカウントを構成してください。

Microsoft Entra ID で使用される "From" アドレスには、メッセージ センターの通知を送信する o365mc@email2.microsoft.com と、次に関連する通知を送信する azure-noreply@microsoft.comの 2 つがあります。

- [Microsoft Entra アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)
- [Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-operations#enable-email-notifications)
- [Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-notifications)
- [Microsoft Entra Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-email-notifications)
- [エンタープライズアプリの証明書期限切れ通知](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on#add-email-notification-addresses-for-certificate-expiration)
- エンタープライズ アプリ プロビジョニング サービスの通知

送信される通知の種類と確認する場所については、次の表を参照してください。

| 通知ソース | 送信される内容 | 確認する場所 |
| --- | --- | --- |
| 技術担当者 | 同期エラー | Azure portal - [プロパティ] ブレード |
| メッセージ センター | Identity Services と Microsoft 365 バックエンド サービスのインシデントと劣化に関する通知 | Office ポータル |
| Identity Protection の週次ダイジェスト | アイデンティティ保護ダイジェスト | Microsoft Entra ID Protection ブレード |
| Microsoft Entra Connect ヘルス | アラート通知 | Azure portal - Microsoft Entra Connect Health ブレード |
| エンタープライズ アプリケーションの通知 | 証明書の有効期限が切れ、プロビジョニング エラーが発生する場合の通知 | Azure portal - [エンタープライズ アプリケーション] ブレード (各アプリには独自の電子メール アドレス設定があります) |

##### 通知に関する推奨された読み物

- [組織の住所、技術連絡先などを変更する](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/change-address-contact-and-more)

### 運用範囲

#### AD FS ロックダウン

Microsoft Entra ID に対して直接認証するようにアプリケーションを構成する組織は、 [Microsoft Entra スマート ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)の恩恵を受けます。 Windows Server 2012 R2 で AD FS を使用する場合は、AD FS [エクストラネット ロックアウト保護](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-extranet-soft-lockout-protection)を実装します。 Windows Server 2016 以降で AD FS を使用する場合は、 [エクストラネット スマート ロックアウト](https://support.microsoft.com/help/4096478/extranet-smart-lockout-feature-in-windows-server-2016)を実装します。 少なくとも、オンプレミスの Active Directory に対するブルート フォース攻撃のリスクを含めるために、エクストラネット ロックアウトを有効にすることをお勧めします。 ただし、Windows 2016 以降に AD FS がある場合は、 [パスワード スプレー](https://www.microsoft.com/microsoft-365/blog/2018/03/05/azure-ad-and-adfs-best-practices-defending-against-password-spray-attacks/) 攻撃の軽減に役立つエクストラネット スマート ロックアウトも有効にする必要があります。

AD FS が Microsoft Entra フェデレーションにのみ使用されている場合は、攻撃対象領域を最小限に抑えるために無効にできるエンドポイントがいくつかあります。 たとえば、AD FS が Microsoft Entra ID にのみ使用されている場合は、 **usernamemixed** と **windowstransport** に対して有効になっているエンドポイント以外の WS-Trust エンドポイントを無効にする必要があります。

#### オンプレミス ID コンポーネントを持つマシンへのアクセス

組織は、オンプレミス のハイブリッド コンポーネントを持つマシンへのアクセスを、オンプレミス ドメインと同じ方法でロックダウンする必要があります。 たとえば、バックアップオペレーターや Hyper-V 管理者は、Microsoft Entra Connect サーバーにサインインしてルールを変更することはできません。

Active Directory 管理層モデルは、環境 (階層 0) のフル コントロールと、攻撃者が頻繁に侵害するリスクの高いワークステーション資産との間のバッファー ゾーンのセットを使用して ID システムを保護するように設計されました。

[Image: 階層モデルの 3 つのレイヤーを示す図]

[階層モデル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model)は 3 つのレベルで構成され、標準ユーザー アカウントではなく、管理アカウントのみが含まれます。

- **階層 0** - 環境内のエンタープライズ ID を直接制御します。 階層 0 には、Active Directory フォレスト、ドメイン、またはドメイン コントローラー、およびその中のすべての資産を直接または間接的に管理するアカウント、グループ、およびその他の資産が含まれます。 階層 0 のすべての資産のセキュリティ感度は、すべてが互いに効果的に制御されるため、同等です。
- **階層 1** - エンタープライズ サーバーとアプリケーションの制御。 階層 1 の資産には、サーバー オペレーティング システム、クラウド サービス、エンタープライズ アプリケーションが含まれます。 階層 1 の管理者アカウントでは、これらの資産でホストされる大量のビジネス価値を管理できます。 一般的な役割の例として、すべてのエンタープライズ サービスに影響を与える機能を備えたオペレーティング システムを維持するサーバー管理者があります。
- **階層 2** - ユーザー ワークステーションとデバイスの制御。 階層 2 の管理者アカウントは、ユーザー ワークステーションとデバイスでホストされるビジネス価値のかなりの量を管理できます。 たとえば、ヘルプ デスクとコンピューター サポート管理者は、ほぼすべてのユーザー データの整合性に影響を与える可能性があるためです。

ドメイン コントローラーの場合と同じ方法で、Microsoft Entra Connect、AD FS、SQL サービスなどのオンプレミス ID コンポーネントへのアクセスをロックダウンします。

### 概要

セキュリティで保護された ID インフラストラクチャには、7 つの側面があります。 この一覧は、Microsoft Entra ID の操作を最適化するために実行する必要があるアクションを見つけるのに役立ちます。

- 主要タスクに所有者を割り当てる。
- オンプレミスのハイブリッド コンポーネントのアップグレード プロセスを自動化します。
- Microsoft Entra Connect と AD FS の監視とレポートのために Microsoft Entra Connect Health をデプロイします。
- System Center Operations Manager または SIEM ソリューションを使用してコンポーネント エージェント ログをアーカイブおよび分析することで、オンプレミスハイブリッド コンポーネントの正常性を監視します。
- ID セキュリティ スコアを使用してセキュリティ体制を測定することで、セキュリティの強化を実装します。
- AD FS をロックダウンします。
- オンプレミス ID コンポーネントを持つマシンへのアクセスをロックダウンします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/parallel-identity-options"} -->
## 並列および結合された ID インフラストラクチャ オプション - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/parallel-identity-options
- Service: entra / architecture
- Article date: 2022-08-17
- Summary: この記事では、組織が複数のテナントとマルチクラウド シナリオを実行するために使用できるさまざまなオプションについて説明します

Microsoft は、さまざまなオンプレミスコンポーネントと ID インフラストラクチャのクラウド コンポーネントの間に統合するためのさまざまなテクノロジとソリューションを提供しています。 多くの場合、お客様はどのテクノロジが最も適切か不明であり、"最新のリリースは以前のテクノロジ リリースのすべてのシナリオを対象としています" と誤って考える可能性があります。

この記事では、会社が以下に概説する複雑なシナリオを実行し、ID 情報を組み合わせようとしている場合のシナリオについて説明します。 理想的には、1 つの人事ソース、1 つの Active Directory フォレスト、1 つの Microsoft Entra テナントを持つ組織は、それぞれが同じユーザーと統合され、Microsoft Online Services に最適な ID エクスペリエンスを持つことになります。 ただし、実際には、企業のお客様は、それが可能な状況にあるとは限りません。 たとえば、顧客が合併を行ったり、一部のユーザーやアプリケーションを分離する必要がある場合などです。 複数の HR、複数の AD、または複数の Microsoft Entra テナントを持つお客様は、それぞれのインスタンスを少なくするか、並列に保つかを決定する必要があります。

お客様からのフィードバックに基づいて、一般的なシナリオと要件の一部を次に示します。

### マルチクラウド ID とマルチ組織 ID に関するシナリオ

- 合併と買収 (M&A) – 通常、A 社が B 社を購入する状況を指します。
- ブランド変更 – 会社名またはブランドの変更、通常は電子メール ドメイン名の変更。
- Microsoft Entra ID または Office 365 テナントの統合 - 複数の Office 365 テナントを持つ企業は、コンプライアンスまたは歴史的な要件のために組み合わせる必要がある場合があります。
- Active Directory ドメインまたはフォレストの統合 - Active Directory ドメインまたはフォレストの統合を実行することを評価している企業。
- 売却 – 会社の部門または事業グループが販売または独立する場合。
- ユーザー情報のプライバシー – 企業には、特定のデータ (属性) が一般に公開されないようにするための要件があり、右側の委任されたグループまたはユーザーのみがそれを読み取り、変更、更新できます。

### これらのシナリオに起因する要件

- 中央ディレクトリまたは **ユニバーサル ディレクトリ**を作成して、会議のスケジュールの電子メールや状態の可用性など、すべてのユーザーとグループのデータを 1 つの場所に移動します。
- シングル サインオンを実装することで、すべてのアプリケーションでユーザー名とパスワードを入力する必要性を減らしながら、 **1 つのユーザー名と資格情報** を維持します。
- ユーザーのオンボーディングを効率化して、数週間または数か月はかからないようにします。
- 将来の取得とアクセス管理の要求に備えて組織を準備します。
- 会社間のコラボレーションと生産性を実現し、改善します。
- セキュリティ ポリシーを一元的かつ一貫してデプロイすることで、セキュリティ侵害やデータ流出の可能性を減らします。

### この記事では取り上げられないシナリオ

- 一部M&A。 たとえば、ある組織が別の組織の一部を購入するとします。
- 組織の事業売却または分割
- 組織の名前を変更する。
- ジョイント ベンチャーまたは一時的なパートナー

この記事では、Microsoft が現在サポートしている M&A シナリオを含む、さまざまなマルチクラウドまたはマルチ組織 ID 環境の概要と、組織が統合に取り組む方法に応じて適切なテクノロジを選択する方法について説明します。

### 架空の M&A シナリオの統合オプション

次のセクションでは、架空の M&A シナリオの 4 つの主要なシナリオについて説明します。

Contoso はエンタープライズ顧客であり、IT 部門に単一の (オンプレミスの) 人事システム、単一の Active Directory フォレスト、アプリ用のシングル テナント Microsoft Entra ID があり、想定どおりに実行されているとします。 ユーザーは人事システムから Active Directory に取り込まれ、Microsoft Entra ID に投影され、そこから SaaS アプリに投影されます。 このシナリオを次の図に示し、矢印に ID 情報のフローを示します。 同じモデルは、Microsoft Identity Manager (MIM) を使用しているお客様だけでなく、Workday や SuccessFactors プロビジョニング Active Directory などのクラウド人事システムをお持ちのお客様にも適用できます。

[Image: 各コンポーネントの単一インスタンス]

次に、Contoso は、以前は独自の IT を個別に実行していた Litware とのマージを開始しました。 Contoso の IT 部門は合併を処理する予定であり、Contoso のアプリが変更されずに維持されることを期待していますが、Litware のユーザーにもアクセス権を付与し、それらのアプリ内で共同作業できるようにしたいと考えています。 Microsoft アプリ、サード パーティの SaaS、カスタム アプリの場合、エンド ステートは Contoso ユーザーと Litware ユーザーが概念的に同じデータにアクセスできる状態である必要があります。

最初の IT の決定は、インフラストラクチャをどの程度組み合わせたいかです。 Litware の ID インフラストラクチャに依存しないことを選択できます。 Litware のインフラストラクチャを使用し、Litware の環境に対する影響を最小限に抑えつつ、徐々に一体化を図ることを検討できます。 場合によっては、顧客は Litware の既存の ID インフラストラクチャを独立させ、収束しないようにしながら、それを使用して Litware の従業員に Contoso アプリへのアクセス権を付与したい場合があります。

顧客が Litware の ID インフラストラクチャの一部または全部を保持することを選択した場合、Litware ユーザーに Contoso リソースへのアクセス権を付与するために使用される Litware の Active Directory Domain Services または Microsoft Entra ID の量に関するトレードオフがあります。 このセクションでは、Contoso が Litware のユーザーに使用する内容に基づいて、実行可能なオプションについて説明します。

- シナリオ A - Litware の ID インフラストラクチャ *を* 使用しないでください。
- シナリオ B - Litware の Active Directory フォレストを使用するが、Litware の Microsoft Entra ID は使用しない (存在する場合)
- シナリオ C - Litware の Microsoft Entra ID を使用します。
- シナリオ D - Litware の Microsoft 以外の ID インフラストラクチャを使用する (Litware が Active Directory/Microsoft Entra ID を使用していない場合)

次の表は、各オプションと、顧客がそれらの結果、制約、および各利点を達成する方法のテクノロジをまとめたものです。

| 考慮事項 | A1: 単一のHR、単一のIAM & テナント | A2: 分離されたHR、単一のIAM、テナント | B3: Active Directory フォレスト トラスト、単一の Microsoft Entra Connect | B4: Microsoft Entra の Active Directory をシングル テナントに接続する | B5: Microsoft Entra Connect クラウドが Active Directory を同期する | C6: 複数のテナントをアプリに並列プロビジョニングする | C7: テナントから読み取り、B2B がユーザーを招待する | C8: 単一の IAM ユーザーおよび B2B ユーザーを必要に応じて使用する | D9: Azure AD 以外の IDP を持つ DF |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 移行作業 | 高 | 作業量: 中 | 作業量の削減 | 作業量: 少 | 作業量: 少 | 無し | 無し | 無し | 無し |
| デプロイ作業 | 作業量が少ない | 作業量: 中 | 作業量: 中 | 作業量: 中 | 低 | 低 | 高 | 高 | 非常に高 |
| 移行中のエンドユーザーへの影響 | 高 | 高 | ミディアム | ミディアム | ミディアム | 無し | 無し | 無し | 無し |
| 運転労力 | 低コスト | 低コスト | 低コスト | 低コスト | 低コスト | 高 | 高 | 高 | 非常に高 |
| プライバシーとデータの機能 (地理的な場所/データの境界) | なし (地理的な場所のシナリオの主要な障害) | 困難な場合でも、分離が制限される | オンプレミスでの分離は制限されていますが、クラウド上では分離されません | オンプレミスでの分離は制限されていますが、クラウド上では分離されません | オンプレミスでの分離は制限されていますが、クラウド上では分離されません | オンプレミスとクラウドの両方で適切な分離 | オンプレミスとクラウドの両方の分離が制限されている | オンプレミスとクラウドの両方の分離が制限されている | オンプレミスとクラウドの両方の分離 |
| 分離 (個別の委任と異なる管理モデルのセットアップ) 注: ソース システム (HR) で定義されているとおり | 無理です | 可能 | 可能 | 可能 | 可能 | 高い可能性 | 高い可能性 | 高い可能性 | 可能 |
| コラボレーション機能 | 非常に良い | 非常に良い | 非常に良い | 非常に良い | 非常に良い | 貧しい | 平均 | 平均 | 貧しい |
| サポートされている IT 管理者モデル (一元化と分離) | 一元化 | 一元化 | 一元化 | 一元化 | 一元化 | Decentralized | Decentralized | Decentralized | 能動的に分散化する |
| 制限事項 | 分離なし | 制限付き分離 | 制限付き分離 | 制限付き分離 | 制限された分離。 書き戻し機能なし | Microsoft Online Services アプリでは機能しません。 アプリの機能に大きく依存 | アプリが B2B に対応している必要がある | アプリが B2B に対応している必要がある | アプリを B2B 対応にする必要があります。 すべてがどのように連携するかの不確実性 |

表の詳細

- 従業員の努力は、組織でソリューションを実装するために必要な専門知識と追加作業を予測しようとします。
- 運用作業は、ソリューションの実行を維持するために必要なコストと労力を予測しようとします。
- プライバシーとデータの機能は、ソリューションで地理的な場所とデータの境界をサポートできるかどうかを示します。
- 分離は、このソリューションが管理者モデルを分離または委任する機能を提供するかどうかを示します。
- コラボレーション機能は、ソリューションがサポートするコラボレーションのレベルを示し、より統合されたソリューションはチームワークの忠実度を高めます。
- IT 管理者モデルは、管理モデルが一元化する必要があるかどうか、または分散化できるかどうかを示します。
- 制限事項: リストに記載する価値のある課題の問題。

#### デシジョン ツリー

次のデシジョン ツリーを使用して、組織に最適なシナリオを決定します。

[Image: 決定木。]

このドキュメントの残りの部分では、それらをサポートするさまざまなオプションを含む 4 つのシナリオ A-D について説明します。

### シナリオ A - Contoso が Litware の既存の ID インフラストラクチャに依存したくない場合

このオプションでは、Litware に ID システム (小規模企業など) がない場合や、顧客が Litware のインフラストラクチャをオフにしたい場合があります。 または、Litware の従業員が Litware のアプリに対して認証を行い、Litware の従業員に Contoso の一部として新しい ID を付与するために、そのままにしておきたい場合です。 たとえば、Alice Smith が Litware の従業員だった場合、 Alice@litware.com と ASmith123@contoso.comという 2 つの ID を持っている可能性があります。 これらの ID は、互いに完全に異なります。

#### オプション 1 - 1 つの人事システムに結合する

通常、顧客は Litware の従業員を Contoso 人事システムに取り込みます。 このオプションにより、これらの従業員はアカウントを受け取り、Contoso のディレクトリとアプリへの適切なアクセスを受け取ります。 その後、Litware ユーザーは新しい Contoso ID を持ち、適切な Contoso アプリへのアクセスを要求するために使用できます。

#### オプション 2 - Litware HR システムを維持する

人事システムの統合は、少なくとも短期的には不可能な場合があります。 代わりに、顧客はプロビジョニング システム (MIM など) を接続して、 *両方* の人事システムから読み取ります。 この図では、最上位の HR は既存の Contoso 環境であり、2 番目の HR は Litware のインフラストラクチャ全体への追加です。

[Image: Litware HR システムを保持する]

その同じシナリオは、Microsoft Entra Workday または SuccessFactors のインバウンドを使用しても実現可能です。Contoso は、Litware の Workday HR ソースからユーザーを既存の Contoso 従業員と共に取り込むことができます。

#### すべての ID インフラストラクチャを統合した結果

- IT インフラストラクチャの削減、管理する ID システムの 1 つだけ、HR システムを除くネットワーク接続要件なし。
- 一貫性のあるエンド ユーザーと管理エクスペリエンス

#### すべての ID インフラストラクチャの統合の制約

- Contoso の従業員が必要とし、Litware から来たデータは、Contoso 環境に移行する必要があります。
- Contoso に必要となる Litware の Active Directory または Microsoft Entra 統合アプリは、Contoso 環境に再構成する必要があります。 この再構成では、アクセスに使用するグループ、またはアプリ自体に対する可能性がある構成を変更する必要がある場合があります。

### シナリオ B - Contoso が Litware の Active Directory フォレストを保持したいが、Litware の Microsoft Entra ID を使用しない場合

Litware には、自分が依存している既存の Active Directory ベースのアプリが多数存在する場合があるため、Contoso は、Litware の従業員が既存の AD に自分の ID を保持し続ける必要がある場合があります。 Litware の従業員は、既存のリソースの認証と Contoso リソースの認証に既存の ID を使用します。 このシナリオでは、Litware には Microsoft Online Services にクラウド ID がありません。Litware は Microsoft Entra のお客様ではなく、Litware のクラウド資産を Contoso と共有することも、Contoso が Contoso のテナントの一部として Litware のクラウド資産を移行することもありませんでした。

#### 取得したフォレストとの信頼関係オプション 3

[Active Directory フォレストの信頼](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/forest-design-models)を使用して、Contoso と Litware は Active Directory ドメインを接続できます。 この信頼により、Litware ユーザーは Contoso の Active Directory 統合アプリを認証できます。 また [、Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) は Litware の Active Directory フォレストから読み取り、Litware ユーザーが Contoso の Microsoft Entra 統合アプリで認証できるようにすることもできます。 この展開トポロジには、2 つのドメイン間にネットワーク ルートを設定し、Litware ユーザーと Contoso Active Directory 統合アプリの間の TCP/IP ネットワーク接続が必要です。 また、Contoso ユーザーが Litware AD 統合アプリ (存在する場合) にアクセスできるように、双方向の信頼を簡単に設定できます。

[Image: 単一テナントでのフォレスト トラスト]

#### フォレスト トラストを設定したアウトカム

- Litware のすべての従業員は、Contoso の Active Directory または Microsoft Entra 統合アプリを認証でき、Contoso は現在の AD ベースのツールを使用して承認を管理できます。

#### フォレスト トラストを設定する際の制約

- ドメインが 1 つのフォレストに参加しているユーザーと、もう一方のフォレストに参加しているリソース間の TCP/IP 接続が必要です。
- Contoso フォレスト内の Active Directory ベースのアプリが複数フォレストに対応している必要がある

#### オプション 4 - フォレスト信頼なしで取得したフォレストに Microsoft Entra Connect を構成する

お客様は、別のフォレストから読み取る Microsoft Entra Connect を構成することもできます。 この構成により、Litware ユーザーは Contoso の Microsoft Entra 統合アプリに対して認証を行うことができますが、Contoso の Active Directory 統合アプリへのアクセスは Litware ユーザーに提供されません。これらの Contoso アプリは Litware ユーザーを認識しません。 この展開トポロジでは、Microsoft Entra Connect と Litware のドメイン コントローラー間の TCP/IP ネットワーク接続が必要です。 たとえば、Microsoft Entra Connect が Contoso IaaS VM 上にある場合、Litware のネットワークにもトンネルを確立する必要があります。

[Image: Microsoft Entra Connect 二つのフォレスト]

#### Microsoft Entra Connect を使用して 1 つのテナントをプロビジョニングした結果

- Litware のすべての従業員は、Contoso の Microsoft Entra 統合アプリを認証できます。

#### Microsoft Entra Connect を使用して 1 つのテナントをプロビジョニングする場合の制約

- Contoso の Microsoft Entra Connect と Litware の Active Directory ドメイン間の TCP/IP 接続が必要です。
- Litware ユーザーが Contoso の Active Directory ベースのアプリケーションにアクセスすることを許可しない

#### オプション 5 - 取得したフォレストに Microsoft Entra Connect クラウド同期をデプロイする

[Microsoft Entra Connect クラウド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync) では、ネットワーク接続の要件は削除されますが、クラウド同期を使用する特定のユーザーに対して、1 つの Active Directory から Microsoft Entra ID へのリンクのみを設定できます。Litware ユーザーは、Contoso の Microsoft Entra 統合アプリを認証できますが、Contoso の Active Directory 統合アプリは認証できません。 このトポロジでは、Litware と Contoso のオンプレミス環境の間に TCP/IP 接続は必要ありません。

[Image: 取得したフォレストに Microsoft Entra Connect クラウド同期をデプロイする]

#### 取得したフォレストに Microsoft Entra Connect クラウド同期をデプロイした結果

- Litware のすべての従業員は、Contoso の Microsoft Entra 統合アプリを認証できます。

#### 取得したフォレストで Microsoft Entra Connect クラウド同期を使用する場合の制約

- Litware ユーザーが Contoso の AD ベースのアプリケーションにアクセスすることを許可しない

### シナリオ C - Contoso が Litware の Microsoft Entra ID を保持する場合

Litware は、Microsoft Online Services または Azure のお客様であるか、1 つ以上の Microsoft Entra ID ベースのアプリに依存している可能性があります。 そのため、Contoso は引き続き Litware の従業員に、それらのリソースへのアクセス用に独自の ID を保持してもらう必要があります。 Litware の従業員は、既存のリソースの認証と Contoso リソースの認証に既存の ID を使用します。

このシナリオは、次のような場合に適しています。

- Litware には、別のテナントへの移行にコストや時間がかかる複数の Office 365 テナントを含む、Azure または Microsoft Online Services への広範な投資があります。
- Litware は将来スピンアウトする可能性があります。あるいは、独立して実行されるパートナーシップです。
- Litware にはオンプレミスのインフラストラクチャがありません

#### オプション 6 - 各 Microsoft Entra ID でアプリの並列プロビジョニングと SSO を維持する

1 つのオプションは、Microsoft Entra ID ごとに個別に SSO を提供し、ユーザーをディレクトリからターゲット アプリに [プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) することです。 たとえば、Contoso IT が Salesforce などのアプリを使用している場合、同じ Salesforce サブスクリプションにユーザーを作成するための管理者権限が Litware に付与されます。

[Image: アプリの並列プロビジョニング]

#### 並列プロビジョニングの結果

- ユーザーは、Contoso のインフラストラクチャに変更を加えることなく、既存の ID を使用してアプリを認証できます。

#### 並列プロビジョニングの制約

- フェデレーションを使用する場合は、アプリケーションが同じサブスクリプションに対して複数のフェデレーション プロバイダーをサポートする必要があります。
- Office や Azure などの Microsoft アプリでは使用できません
- Contoso は、Litware ユーザーのアプリケーションアクセスの状況が Microsoft Entra ID に表示されていません。

#### オプション 7 - 取得したテナントのユーザーの B2B アカウントを構成する

Litware が独自のテナントを実行している場合、Contoso はそのテナントからユーザーを読み取ることができ、B2B API を使用して、それらの各ユーザーを Contoso テナントに招待できます。 (この一括招待プロセスは、たとえば [MIM グラフ コネクタ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016-connector-graph)を使用して実行できます)。Contoso に Litware ユーザーが使用できるようにする AD ベースのアプリがある場合、MIM は、Microsoft Entra ユーザーの UPN にマップする Active Directory でユーザーを作成することもできます。これにより、アプリ プロキシは Contoso の Active Directory で Litware ユーザーの表現に代わって KCD を実行できます。

その後、Litware の従業員が Contoso アプリにアクセスする場合は、リソース テナントへのアクセス権の割り当てを使用して、自分のディレクトリに対して認証することでアクセスできます。

[Image: 他のテナントからのユーザーの B2B アカウントを構成する]

#### 他のテナントのユーザーに対して B2B アカウントを設定した結果

- Litware ユーザーは Contoso アプリを認証することができ、アクセスは Contoso がテナント内で管理します。

#### 他のテナントのユーザーに対する B2B アカウントの設定の制約

- Contoso リソースへのアクセスを必要とする Litware ユーザーごとに重複するアカウントが必要です。
- アプリが SSO に対応する B2B である必要があります。

#### オプション 8 - B2B を構成しますが、両方のディレクトリに共通の HR フィードを使用する

場合によっては、買収後に組織が 1 つの HR プラットフォームに収束しても、既存の ID 管理システムを実行する場合があります。 このシナリオでは、MIM は、ユーザーが所属している組織のどの部分に応じて、複数の Active Directory システムにユーザーをプロビジョニングできます。 ユーザーが既存のディレクトリを認証し、統合 GAL を使用できるように、引き続き B2B を使用できます。

[Image: 共通の人事システム フィードを使用して B2B ユーザーを構成する]

#### 共通の人事システム フィードから B2B ゲスト ユーザーを設定した結果

- Litware ユーザーは Contoso アプリに対して認証を行い、Contoso はテナント内でそのアクセスを制御できます。
- Litware と Contoso には統合 GAL があります。
- Litware の Active Directory または Microsoft Entra ID への変更なし

#### 共通の人事システム フィードから B2B ゲスト ユーザーを設定する場合の制約

- ユーザーを Litware の Active Directory に送信し、Litware のドメインと Contoso のドメイン間の接続も行うには、Contoso のプロビジョニングに対する変更が必要です。
- アプリが SSO に対応する B2B である必要があります。

### シナリオ D - Litware が Active Directory 以外のインフラストラクチャを使用している場合

最後に、Litware がオンプレミスまたはクラウドの別のディレクトリ サービスを使用している場合でも、Contoso IT は Litware の従業員が認証し、既存の ID を使用して Contoso のリソースにアクセスできるように構成できます。

#### オプション 9 - B2B 直接フェデレーションを使用する (パブリック プレビュー)

このシナリオでは、Litware には次のことが想定されています。

- OpenLDAP などの一部の既存のディレクトリ、または Contoso と定期的に共有できる電子メール アドレスを持つユーザーの SQL データベースまたはフラット ファイル。
- PingFederate や OKTA など、SAML をサポートする ID プロバイダー。
- Litware.com や、そのドメイン内の電子メール アドレスを持つユーザーなど、パブリックにルーティングされた DNS ドメイン

このアプローチでは、Contoso は、そのドメインのテナントから Litware の ID プロバイダーへの [直接フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation) 関係を構成した後、ディレクトリから Litware ユーザーへの更新を定期的に読み取り、Litware ユーザーを Contoso の Microsoft Entra ID に招待します。 この更新は、MIM Graph コネクタを使用して実行できます。 Contoso に Litware ユーザーが使用できるようにする Active Directory ベースのアプリがある場合、MIM は、Microsoft Entra ユーザーの UPN にマップする Active Directory でユーザーを作成して、アプリ プロキシが Contoso の Active Directory の Litware ユーザーの代わりに KCD を実行できるようにすることもできます。

[Image: B2B 直接フェデレーションを使用する]

#### B2B 直接フェデレーションを使用した結果

- Litware ユーザーは、既存の ID プロバイダーを使用して Contoso の Microsoft Entra ID に対して認証を行い、Contoso のクラウドおよびオンプレミスの Web アプリにアクセスします。

#### B2B 直接フェデレーションの使用に関する制約

- Contoso アプリで B2B ユーザー SSO をサポートできるようにする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/protect-m365-from-on-premises-attacks"} -->
## オンプレミスの攻撃から Microsoft 365 を保護する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks
- Service: entra / architecture
- Article date: 2025-01-03
- Summary: オンプレミスの侵害から Microsoft 365 のクラウド環境を保護するためにシステムを構成する方法について説明します。

多くのお客様は、プライベートな企業ネットワークを Microsoft 365 に接続して、ユーザー、デバイス、アプリケーションがベネフィットを得られるようにしています。 脅威アクターは、よく文書化された方法でこれらのプライベート ネットワークを侵害する可能性があります。 Microsoft 365 は、クラウドへの環境の最新化に投資した組織にとって、一種の神経系として機能します。 オンプレミスのインフラストラクチャの侵害から Microsoft 365 を保護することが重要です。

この記事では、オンプレミスの侵害から Microsoft 365 クラウド環境を保護するためにシステムを構成する方法について説明します。

- Microsoft Entra テナントの構成設定。
- Microsoft Entra テナントをオンプレミス システムに安全に接続する方法。
- オンプレミスの侵害からクラウド システムを保護する方法でシステムを運用するために必要なトレードオフ。

Microsoft では、この記事のガイダンスを実装することを強くお勧めします。

### オンプレミス環境での脅威のソース

Microsoft 365 クラウド環境は、広範な監視とセキュリティのインフラストラクチャからベネフィットを受けています。 Microsoft 365 は、機械学習と人の知性を使用して、世界中のトラフィックに注意を向けています。 これにより、攻撃を迅速に検出し、ほぼリアルタイムで再構成できます。

ハイブリッド デプロイでは、オンプレミスのインフラストラクチャを Microsoft 365 に接続できます。 こうしたデプロイでは、多くの組織で、重要な認証およびディレクトリ オブジェクトの状態管理の決定のため、オンプレミスのコンポーネントに信頼が委任されます。 脅威アクターがオンプレミス環境を侵害した場合、これらの信頼関係は、Microsoft 365 環境を侵害する機会にもなります。

2 つの主要な脅威ベクトルは、"フェデレーションの信頼関係" と "アカウントの同期" です。どちらのベクトルによっても、攻撃者にクラウドへの管理アクセスが許可されます。

- **フェデレーションの信頼関係** (Security Assertion Markup Language (SAML) 認証など) は、オンプレミスの ID インフラストラクチャを介して Microsoft 365 に対する認証を行うために使用されます。 SAML トークン署名証明書が侵害された場合、フェデレーションにより、その証明書を持つすべてのユーザーは、クラウド内の任意のユーザーを偽装できるようになります。 このベクトルを軽減するには、可能であれば、Microsoft 365 への認証のフェデレーション信頼関係を無効にすることをお勧めします。 また、オンプレミスのフェデレーション インフラストラクチャを使用する他のアプリケーションを移行して、認証に Microsoft Entra を使用することもお勧めします。
- **アカウント同期**を使用して、特権ユーザー (資格情報を含む) または Microsoft 365 の管理特権を持つグループを変更します。 このベクトルを軽減するには、同期されたオブジェクトが Microsoft 365 のユーザーを超える特権を保持しないようにすることをお勧めします。 権限は、直接制御することも、信頼されたロールまたはグループに含めることによって制御することもできます。 これらのオブジェクトに、信頼されたクラウドのロールまたはグループにおいて、確実に直接または入れ子の割り当てがないようにします。

### オンプレミスの侵害から Microsoft 365 を保護する

オンプレミスの脅威に対処するには、次の図に示す 4 つの原則に従うことをお勧めします。

[Image: 次の一覧で説明するように、Microsoft 365 を保護するための参照アーキテクチャを示す図。]

1. **Microsoft 365 管理者アカウントを完全に分離します。** それらは次のようになっている必要があります。

    - クラウドネイティブ アカウント。
    - [フィッシングに強い資格情報を](https://aka.ms/PasswordlessGuide)使用して認証されます。
    - Microsoft Entra 条件付きアクセスでセキュリティ保護されています。
    - クラウドで管理された [特権アクセス ワークステーション](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-devices)を使用してのみアクセスされます。

    これらの管理者アカウントは制限付きの使用アカウントです。 Microsoft 365 では、オンプレミスのアカウントが管理者特権を持つことはできません。

    詳細については、「[管理者ロールについて](https://learn.microsoft.com/ja-jp/microsoft-365/admin/add-users/about-admin-roles)」および「[Microsoft Entra ID における Microsoft 365 のロールについて](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/m365-workload-docs)」を参照してください。
2. **Microsoft 365 からデバイスを管理します。** Microsoft Entra 参加とクラウドベースのモバイル デバイス管理 (MDM) を使用して、オンプレミスのデバイス管理インフラストラクチャへの依存関係を排除します。 これらの依存関係は、デバイスとセキュリティ制御を侵害するおそれがあります。
3. **オンプレミスのアカウントが、Microsoft 365 に対する昇格された特権を持たないようにしてください。** 一部のアカウントは、NTLM、ライトウェイト ディレクトリ アクセス プロトコル (LDAP)、または Kerberos 認証を必要とするオンプレミス アプリケーションにアクセスします。 これらのアカウントは、組織のオンプレミス ID インフラストラクチャ内に存在する必要があります。 これらのアカウントとサービス アカウントを、特権クラウド ロールまたはグループに含めないようにします。 これらのアカウントに対する変更がクラウド環境の整合性に影響しないようにします。 特権のあるオンプレミス ソフトウェアが、Microsoft 365 の特権のあるアカウントまたはロールに影響を与えることができないようにする必要があります。
4. **Microsoft Entra クラウド認証を使用して、オンプレミスの資格情報への依存関係を排除します。** Windows Hello for Business、 [MacOS のプラットフォーム資格情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)、パスキー (FIDO2)、Microsoft Authenticator パスキー、証明書ベースの認証など、フィッシングに強い認証方法を常に使用します。

### 特定のセキュリティに関する推奨事項

以降のセクションでは、この記事の原則を実装する方法に関するガイダンスを提供します。

#### 特権 ID を分離する

Microsoft Entra ID では、管理者などの特権ロールを持つユーザーが、環境の他の部分の構築と管理に対する信頼の根幹になります。 セキュリティ侵害の影響を最小限に抑えるため、次のことを実装します。

- Microsoft Entra ID と Microsoft 365 の特権ロールには、クラウド専用アカウントを使用します。
- Microsoft 365 と Microsoft Entra ID を管理するための特権アクセス用に、特権アクセス デバイスをデプロイします。 「[デバイスのロールとプロファイル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-devices#device-roles-and-profiles)」を参照してください。
- [Microsoft Entra Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) (PIM) をデプロイして、特権ロールを持つすべての人間のアカウントに Just-In-Time アクセスできるようにします。 ロールをアクティブ化するには、フィッシングに対する耐性のある認証が必要です。
- 必要なタスクを実行するために必要な最小限の特権を許可する管理者ロールを提供します。 「[Microsoft Entra ID のタスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task)」を参照してください。
- 同時に委任と複数のロールが含まれる、より充実したロール割り当てエクスペリエンスを有効にするには、Microsoft Entra セキュリティ グループまたは Microsoft 365 グループを使用することを検討します。 まとめて、これらの *クラウド グループ*と呼びます。
- ロールベースのアクセス制御を有効にします。 「[ユーザーに Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。 [Microsoft Entra ID の管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)を使用して、ロールのスコープを組織の一部に制限します。
- 資格情報を格納するために、オンプレミスのパスワード コンテナーではなく緊急アクセス アカウントをデプロイします。 「[Microsoft Entra ID で緊急アクセス用アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)」を参照してください。

詳細については、「[Microsoft Entra ID での管理者の](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview)[特権アクセス](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning)のセキュリティ保護とセキュリティで保護されたアクセス方法」を参照してください。

#### クラウド認証を使用する

資格情報は、主要な攻撃ベクトルです。 資格情報のセキュリティ保護を高めるには、次の方法を実装します。

- **パスワードなしの認証をデプロイします**。 パスワードのない資格情報をデプロイすることで、パスワードの使用を可能な限り減らします。 これらの資格情報は、クラウドでネイティブに管理および検証できます。 詳細については、「 [Microsoft Entra ID でのフィッシングに強いパスワードレス認証の展開の概要」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)参照してください。 以下の認証方法から選択します。

    - [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/)
    - [macOS のプラットフォーム資格情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)
    - [Microsoft Authenticator アプリ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone)
    - パスキー [FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-windows)
    - [Microsoft Entra 証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)
- **多要素認証をデプロイします**。 詳細については、「[Microsoft Entra 多要素認証のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)」を参照してください。 Microsoft Entra MFA を使用して複数の強力な資格情報をプロビジョニングします。 そのようにすると、クラウド リソースへのアクセスには、オンプレミスのパスワードに加えて、Microsoft Entra ID で管理される資格情報が必要になります。 詳細については、「[資格情報管理を使用して回復性を強化する](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-credentials)」と 「[Microsoft Entra ID を使用して回復性があるアクセス制御管理戦略を作成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-resilient-controls)」を参照してください。
- **デバイスから SSO を最新化します。** Windows 11、 [macOS](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos)、Linux、モバイル デバイスの最新のシングル サインオン (SSO) 機能を利用します。
- **考慮 事項。** ハイブリッド アカウントのパスワードの管理には、パスワード保護エージェントやパスワード ライトバック エージェントなどのハイブリッド コンポーネントが必要です。 攻撃者がオンプレミスのインフラストラクチャを侵害した場合、これらのエージェントが存在するマシンを制御できます。 この脆弱性により、クラウド インフラストラクチャが侵害されることはありません。 特権ロールにクラウド アカウントを使用しても、これらのハイブリッド コンポーネントはオンプレミスの侵害から保護されません。

    Microsoft Entra の既定のパスワード有効期限ポリシーでは、同期されたオンプレミス アカウントのアカウント パスワードが *[期限なし]* に設定されます。 この設定は、オンプレミスの Active Directory パスワード設定で軽減できます。 Active Directory のインスタンスが侵害され、同期が無効になっている場合は、 [CloudPasswordPolicyForPasswordSyncedUsersEnabled](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#password-expiration-policy) オプションを設定して、パスワードの変更を強制するか、パスワードから [フィッシングに強いパスワード認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)に移行します。

#### クラウドからのユーザー アクセスをプロビジョニングする

*プロビジョニング*とは、アプリケーションまたは ID プロバイダーにユーザー アカウントとグループを作成することです。

[Image: プロビジョニング アーキテクチャの図は、Microsoft Entra ID と Cloud HR、Microsoft Entra B2B、Azure アプリプロビジョニング、グループベースのライセンスの相互作用を示しています。]

次のプロビジョニング方法をお勧めします。

- **クラウド HR アプリから Microsoft Entra ID にプロビジョニングする。** このプロビジョニングにより、オンプレミスの侵害を分離することができます。 この分離によって、クラウド HR アプリから Microsoft Entra ID への Joiner-Mover-Leaver サイクルが中断されることはありません。
- **クラウド アプリケーション** 可能であれば、オンプレミス [のプロビジョニング ソリューションではなく、Microsoft Entra ID でアプリ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) プロビジョニングをデプロイします。 この方法では、サービスとしてのソフトウェア (SaaS) アプリの一部を、オンプレミスの侵害の悪意のある攻撃者プロファイルから保護します。
- **外部アイデンティティ。**[Microsoft Entra 外部 ID B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を使用して、パートナー、顧客、サプライヤーとの外部コラボレーションのためのオンプレミス アカウントへの依存関係を減らします。 他の ID プロバイダーとの直接フェデレーションを慎重に評価します。 次の方法で B2B ゲスト アカウントを制限することをお勧めします。

    - ディレクトリ内の参照グループおよびその他のプロパティへのゲスト アクセスを制限します。 外部コラボレーション設定を使用して、ゲストがメンバーではないグループを読み取る機能を制限します。
    - Azure portal へのアクセスをブロックします。 まれに必要となる例外を作成できます。 すべてのゲストと外部ユーザーを含む [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps) ポリシーを作成します。 その後、アクセスをブロックするポリシーを実装します。
- **切断されたフォレスト。** Microsoft Entra クラウド プロビジョニングを使用して、切断されたフォレストに接続します。 この方法により、オンプレミスの侵害の影響を広げるおそれがあるフォレスト間の接続や信頼関係を確立する必要がなくなります。 詳細については、「 [Microsoft Entra Connect Cloud Sync とは」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)を参照してください。
- **考慮 事項。** ハイブリッド アカウントのプロビジョニングに使用する場合、クラウド HR システムからの Microsoft Entra ID は、オンプレミスの同期に依存して、Active Directory から Microsoft Entra ID へのデータ フローを完了します。 同期が中断された場合、新しい従業員レコードを Microsoft Entra ID で利用できなくなります。

#### コラボレーションとアクセスにクラウド グループを使用する

クラウド グループを使用すると、オンプレミス インフラストラクチャからのコラボレーションとアクセスを切り離すことができます。

- **コラボレーション**: 最新のコラボレーションのために Microsoft 365 グループと Microsoft Teams を使用します。 オンプレミスの配布リストの使用を停止し、 [配布リストを Outlook の Microsoft 365 グループにアップグレード](https://learn.microsoft.com/ja-jp/microsoft-365/admin/create-groups/office-365-groups)します。
- **アクセス**。 Microsoft Entra セキュリティ グループまたは Microsoft 365 グループを使用して、Microsoft Entra ID でアプリケーションへのアクセスを承認します。 オンプレミス アプリケーションへのアクセスを制御するには、 [Microsoft Entra Cloud Sync を使用して Active Directory にグループをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)することを検討してください。
- **ライセンス**。 グループベースのライセンスを使用して、クラウド専用グループを使用して Microsoft サービスにプロビジョニングします。 この方法により、グループ メンバーシップの制御がオンプレミスのインフラストラクチャから切り離されます。

オンプレミスの侵害でメンバーシップの引き継ぎを回避するために、アクセスに使用されるグループの所有者を特権 ID と見なします。 引き継ぎには、Microsoft 365 動的グループ メンバーシップに影響を与える可能性がある、直接のオンプレミス グループ メンバーシップ操作またはオンプレミスの属性操作が含まれます。

#### クラウドからデバイスを管理する

Microsoft Entra 機能を使用してデバイスを安全に管理します。

モバイル デバイス管理ポリシーを使用して、Microsoft Entra 参加済み Windows 11 ワークステーションを展開します。 [Windows Autopilot を](https://learn.microsoft.com/ja-jp/autopilot/overview)有効にして、完全に自動化されたプロビジョニング エクスペリエンスを実現します。 「[Microsoft Entra 参加の実装を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-plan)」を参照してください。

- 最新の更新プログラムが展開されている Windows 11 ワークステーションを使用します。

    - Windows 10 以前を実行するマシンを非推奨にします。
    - サーバー オペレーティング システムを持つコンピューターをワークステーションとしてデプロイしないでください。
- Windows、macOS、iOS、Android、Linux など、すべてのデバイス管理ワークロードの権限として [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune) を使用します。

    - [iOS Enterprise SSO 拡張機能をデプロイします](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)。
    - [macOS Enterprise SSO Extension](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin) または [Platform SSO Secure Enclave Key](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso) をデプロイします。
- 特権アクセス デバイスをデプロイする。 詳細については、「[デバイスのロールとプロファイル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-devices#device-roles-and-profiles)」を参照してください。

#### ワークロード、アプリケーション、リソース

このセクションでは、ワークロード、アプリケーション、およびリソースに対するオンプレミス攻撃から保護するための推奨事項を示します。

- **オンプレミスのシングル サインオン (SSO) システム。** オンプレミスのフェデレーションと Web アクセス管理インフラストラクチャを廃止します。 Microsoft Entra ID を使用するようにアプリケーションを構成します。 フェデレーションに AD FS を使用している場合は、「 [AD FS から Microsoft Entra ID にアプリケーション認証を移行する段階について理解](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)する」を参照してください。
- **先進認証プロトコルをサポートする SaaS および基幹業務 (LOB) アプリケーション。**[Microsoft Entra ID でシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を使用します。 認証に Microsoft Entra ID を使用するようにアプリを構成して、オンプレミスの侵害のリスクを軽減します。
- **レガシーアプリケーション。**[Microsoft Entra Private Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-private-access) を使用して、先進認証をサポートしていないレガシ アプリケーションへの認証、承認、リモート アクセスを有効にすることができます。 最初の手順として、Microsoft Entra Private Access クイック アクセスを使用して内部ネットワークへの最新のアクセスを有効にします。 この手順では、条件付きアクセスのセキュリティで保護された機能を使用して、VPN の 1 回限りの構成をすばやく簡単に置き換える方法を提供します。 次に、TCP ベースまたは UDP ベースのアプリケーションへのアプリごとのアクセスを構成します。
- **条件付きアクセス。** SaaS、LOB、レガシ アプリケーションの条件付きアクセス ポリシーを定義して、フィッシングに強い MFA やデバイス コンプライアンスなどのセキュリティ制御を適用します。 詳細については、「 [Microsoft Entra 条件付きアクセスの展開を計画する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access#recommendations)」を参照してください。
- **アクセスのライフサイクル。** Microsoft Entra ID ガバナンス を使用してアプリケーションとリソースへのアクセス ライフサイクルを制御し、最小限の特権アクセスを実装します。 情報やリソースへのアクセス権をユーザーに付与するのは、タスクを実行するために本当に必要がある場合に限定します。 SaaS、LOB、レガシ アプリケーションを Microsoft Entra ID ガバナンスと統合します。 Microsoft Entra ID Entitlement Management は、アクセス要求ワークフロー、アクセスの割り当て、レビュー、有効期限を自動化します。
- **アプリケーション サーバーとワークロード サーバー。** サーバーを必要とするアプリケーションまたはリソースを Azure サービスとしてのインフラストラクチャ (IaaS) に移行できます。 [Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview) を使用して、Active Directory のオンプレミス インスタンスに対する信頼と依存関係を切り離します。 この分離を実現するには、Microsoft Entra Domain Services に使われる仮想ネットワークが、企業ネットワークに接続されていないことを確認してください。 資格情報の階層化を利用する。 通常、アプリケーション サーバーは階層 1 の資産と見なされます。 詳細については、「[エンタープライズ アクセス モデル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model)」を参照してください。

#### 条件付きアクセス ポリシー

Microsoft Entra 条件付きアクセスを使用して、信号を解釈し、それを使用して認証の決定を行います。 詳細については、[条件付きアクセスのデプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)に関するページを参照してください。

- 可能な場合は常に、条件付きアクセスを使用して、レガシ認証プロトコルをブロックします。 また、アプリケーション固有の構成を使用して、アプリケーション レベルでレガシ認証プロトコルを無効にします。 「 [レガシ認証](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication) と [レガシ認証プロトコル](https://learn.microsoft.com/ja-jp/entra/architecture/auth-sync-overview#legacy-authentication-protocols)をブロックする」を参照してください。 [Exchange Online](https://learn.microsoft.com/ja-jp/exchange/clients-and-mobile-in-exchange-online/disable-basic-authentication-in-exchange-online#how-basic-authentication-works-in-exchange-online) と [SharePoint Online](https://learn.microsoft.com/ja-jp/powershell/module/sharepoint-online/set-spotenant) の詳細を確認します。
- 推奨される ID とデバイスのアクセス構成を実装します。 「[一般的なゼロトラストにおける ID とデバイスのアクセス ポリシー](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/identity-access-policies)」を参照してください。
- 条件付きアクセスを含まないバージョンのMicrosoft Entra ID を使用している場合は、[Microsoft Entra ID のセキュリティの既定値](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)を使用します。 Microsoft Entra 機能ライセンスの詳細については、「[Microsoft Entra 価格ガイド](https://www.microsoft.com/security/business/microsoft-entra-pricing)」を参照してください。

#### モニター

Microsoft 365 をオンプレミスの侵害から保護するように環境を構成したら、環境を事前に監視します。 詳細については、「 [Microsoft Entra 監視とは」](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)を参照してください。

組織に固有のシナリオに加えて、次の主要なシナリオを監視します。

- **疑わしいアクティビティ。** すべての Microsoft Entra リスク イベントで疑わしいアクティビティを監視します。 「[方法: リスクの調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)」を参照してください。 Microsoft Entra ID 保護 は、 [Microsoft Defender for Identity](https://learn.microsoft.com/ja-jp/defender-for-identity/what-is) とネイティブに統合されます。 場所ベースのシグナルで多くのノイズが検出されないよう、ネットワークのネームド ロケーションを定義します。 「[条件付きアクセスポリシーでの場所の条件の使用](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)」を参照してください。
- **ユーザーとエンティティの行動分析 (UEBA) アラート。** UEBA を使用して、異常検出に関する分析情報を取得します。 Microsoft Defender for Cloud Apps は、クラウドでの UEBA を提供しています。 「[危険性の高いユーザーを調査する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/tutorial-ueba)」を参照してください。 Microsoft Defender for Identity からオンプレミスの UEBA を統合できます。 Microsoft Defender for Cloud アプリは Microsoft Entra ID Identity Protection からシグナルを読み取ります。 [高度な脅威を検出するためのエンティティ動作分析の有効化に関するページを](https://learn.microsoft.com/ja-jp/azure/sentinel/enable-entity-behavior-analytics)参照してください。
- **緊急アクセス アカウントのアクティビティ。** 緊急アクセス アカウントを使用するすべてのアクセスを監視します。 「[Microsoft Entra ID で緊急アクセス用アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)」を参照してください。 調査用のアラートを作成します。 この監視には、次のアクションを含める必要があります。

    - サインイン
    - 資格情報の管理
    - グループメンバーシップに関する最新情報はありますか?
    - アプリケーションの割り当て
- **特権ロールのアクティビティ。** Microsoft Entra Privileged Identity Management (PIM) によって生成される [セキュリティ アラート](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) を構成して確認します。 ユーザーが直接割り当てられたときに常にアラートを生成することで、PIM の外部での特権ロールの直接割り当てを監視します。
- **Microsoft Entra テナント全体の構成。** テナント全体の構成が変更されたら常に、システムでアラートが生成されるようにする必要があります。 次の変更を含めます (ただし、これらに限定されません)。

    - 更新されたカスタム ドメイン
    - 許可リストとブロックリストに対する Microsoft Entra B2B の変更
    - Microsoft Entra B2B は、SAML ID プロバイダーなどの許可された ID プロバイダーに対する変更を直接フェデレーションやソーシャル サインインを通じて行います。
    - 条件付きアクセスまたはリスク ポリシーの変更
- **アプリケーション オブジェクトとサービス プリンシパル オブジェクト**

    - 条件付きアクセス ポリシーが必要になる可能性がある新しいアプリケーションまたはサービス プリンシパル
    - サービス プリンシパルに追加された資格情報
    - アプリケーションの同意アクティビティ
- **カスタム ロール**

    - カスタム ロールの定義の更新
    - 新規作成されたカスタム ロール

このトピックに関する包括的なガイダンスについては、 [Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)を参照してください。

#### ログの管理

一貫したツール セットを容易にするために、ログのストレージと保持の戦略、設計、および実装を定義します。 たとえば、Microsoft Sentinel などのセキュリティ情報およびイベント管理 (SIEM) システム、一般的なクエリ、調査とフォレンジクスのプレイブックについて考えてみましょう。

- **Microsoft Entra ログ**。 診断、ログの保持、SIEM インジェストなどの設定のベスト プラクティスに常に従って、生成されたログとシグナルを取り込みます。
- Microsoft Entra ID は、 [複数の ID ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-diagnostic-settings-logs-options)に対して Azure Monitor 統合を提供します。 詳細については、 [Azure Monitor の Microsoft Entra アクティビティ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations) を参照し、 [Copilot で危険なユーザーを調査](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization)します。
- **ハイブリッド インフラストラクチャ オペレーティング システムのセキュリティ ログ**。 ハイブリッド ID インフラストラクチャのすべてのオペレーティングシステムログを、脅威の範囲に関連する影響を考慮して、階層 0 システムとしてアーカイブし、細心の注意を払って監視します。 次の要素が含まれます。

    - Microsoft Entra Private Access と Microsoft Entra アプリケーション プロキシ用のプライベート ネットワーク コネクタ。
    - パスワード書き戻しエージェント。
    - パスワード保護ゲートウェイ マシン。
    - Microsoft Entra 多要素認証 RADIUS 拡張機能を持つネットワーク ポリシー サーバー (NPS)。
    - [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect)。
    - Microsoft Entra Connect Health をデプロイし、ID の同期を監視する必要があります。

このトピックに関する包括的なガイダンスについては、[インシデント対応プレイブック](https://learn.microsoft.com/ja-jp/security/operations/incident-response-playbooks)を確認し、[Copilot で危険なユーザーを調査](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization)します
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/recover-from-deletions"} -->
## Microsoft Entra ID での削除からの回復 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions
- Service: entra / architecture
- Article date: 2026-05-06
- Summary: 論理的な削除とハード削除の違いと、Microsoft Entra ID でオブジェクトを回復または再作成する方法について説明します。

Microsoft Entra ID での削除の管理は、組織の ID インフラストラクチャの整合性と可用性を維持するうえで重要な側面です。 この記事では、論理的な削除とハード削除の違いを理解し、削除の監視を行い、Microsoft Entra テナント内のオブジェクトを回復または再作成するための包括的なガイドを提供します。 誤削除に対処する場合でも、復旧戦略を準備する場合でも、このガイドでは、中断を最小限に抑え、継続性を確保するための知識とツールを提供します。 基本的な分析情報については、 [回復性のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/recoverability-overview)から始めます。

### 削除を監視する

[Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)には、テナント内で実行されたすべての削除操作に関する情報が含まれています。 これらのログを [、Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview) などのセキュリティ情報とイベント管理ツールにエクスポートします。

Microsoft Graph を使用して変更を監査し、時間の経過に伴う違いを監視するカスタム ソリューションを構築します。 Microsoft Graph を使用して削除済みアイテムを検索する方法について詳しくは、[Microsoft Graph v1.0 での削除済みアイテムの一覧表示](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-list?tabs=http)に関するページを参照してください。

#### 監査ログ

テナント内のオブジェクトが論理的または物理的な削除によってアクティブな状態から削除されると、監査ログには常に "&lt;オブジェクト&gt; の削除" イベントが記録されます。

[Image: 削除を含む監査ログを示すスクリーンショット。]

アプリケーション、ユーザー、Microsoft 365 グループ、クラウド セキュリティ グループの削除イベントはソフトデリートです。 その他すべての種類のオブジェクトでは、物理的な削除になります。 "&lt;オブジェクト&gt; の削除" イベントと削除されたオブジェクトの種類を比較して、物理的な削除イベントの発生を追跡します。 論理的な削除がサポートされていないイベントをメモします。 また、"&lt;オブジェクト&gt; の物理的な削除" イベントをメモします。

| オブジェクトの種類 | ログ内のアクティビティ | 結果 |
| --- | --- | --- |
| アプリケーション | アプリケーションを削除する | ソフト削除 |
| アプリケーション | アプリケーションの物理的な削除 | 物理的な削除 |
| ユーザー | ユーザーの削除 | ソフト削除 |
| ユーザー | ユーザーの物理的な削除 | 物理的な削除 |
| Microsoft 365 グループ | グループの削除 | ソフト削除 |
| Microsoft 365 グループ | グループの物理的な削除 | 物理的な削除 |
| クラウド セキュリティ グループ | グループの削除 | ソフト削除 |
| クラウド セキュリティ グループ | グループの物理的な削除 | 物理的な削除 |
| 他のすべてのオブジェクト | "objectType" を削除する | 物理的な削除 |

注意

監査ログは、削除されたグループのグループの種類を区別しません。 Microsoft 365 グループとクラウド セキュリティ グループは論理的に削除されます。 [グループの削除] エントリが表示される場合は、Microsoft 365 グループまたはクラウド セキュリティ グループの論理的な削除、または別の種類のグループのハード削除である可能性があります。

*既知の良好な状態を記したドキュメントには、組織内にあるグループごとにその種類が記載されていることが大切です*。 既知の良好な状態の文書化については、「[回復可能性のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/recoverability-overview)」を参照してください。

#### サポート チケットを監視する

特定のオブジェクトへのアクセスに関するサポート チケットの急激な増加は、削除が発生したことを示している可能性があります。 一部のオブジェクトには依存関係があるため、アプリケーション、アプリケーション自体、またはアプリケーションを対象とする条件付きアクセス ポリシーにアクセスするために使用されるグループを削除すると、大きな影響を及ぼす可能性があります。 このような傾向が見られた場合は、アクセスに必要なオブジェクトが削除されていないことを確認してください。

### 論理的な削除

ユーザー、Microsoft 365 グループ、クラウド セキュリティ グループ、アプリケーションの登録などのオブジェクトが論理的に削除されると、他のサービスで使用できない中断状態になります。 この状態では、アイテムはプロパティを保持し、30 日間復元できます。 30日後、半削除された状態のオブジェクトは完全に削除されるか、またはハード削除されます。

注意

物理的に削除された状態からオブジェクトを復元することはできません。 再作成して再構成します。

#### 論理的な削除が実行された時

オブジェクトの削除が環境内で発生する理由を理解することは、その削除に対する準備に役立ちます。 このセクションでは、オブジェクト クラスによる論理的な削除の一般的なシナリオについて説明します。 組織に固有のシナリオが確認される場合があるため、検出プロセスが準備の鍵となります。

#### ユーザー

Microsoft Entra 管理センター、Microsoft Graph、または PowerShell を使用してユーザー オブジェクトを削除すると、ユーザーはソフト削除状態になります。

ユーザーを削除する一般的なシナリオは次のとおりです。

- 管理者は、要求に応じて、または定期的なユーザー メンテナンスの一環として、Microsoft Entra 管理センターのユーザーを削除します。
- Microsoft Graph または PowerShell のオートメーション スクリプトによって削除がトリガーされる。 たとえば、指定した時間サインインしていないユーザーを削除するスクリプトがあるとします。
- ユーザーは、Microsoft Entra Connect との同期のスコープ外に移動します。
- ユーザーは廃止され、人事システムから削除され、自動化されたワークフローを介してプロビジョニング解除されます。

#### グループ

グループを削除する最も頻繁なシナリオは次のとおりです。

- 管理者が、サポート リクエストなどに応じてグループを意図的に削除する。
- Microsoft Graph または PowerShell のオートメーション スクリプトによって削除がトリガーされる。 たとえば、指定された期間、グループ所有者がアクセスまたは証明していないグループを削除するスクリプトがあると仮定します。
- 管理者以外が所有するグループを意図せず削除する。

#### アプリケーション オブジェクトとサービス プリンシパル

アプリケーションを削除する最も頻繁なシナリオは次のとおりです。

- 管理者が、サポート リクエストなどに応じてアプリケーションを意図的に削除する。
- Microsoft Graph または PowerShell のオートメーション スクリプトによって削除がトリガーされる。 たとえば、使用または管理されなくなった不要なアプリケーションを削除するプロセスが必要な場合です。 通常は、意図しない削除を回避するために、スクリプトを作成するのではなく、アプリケーションのオフボード プロセスを作成します。

アプリケーションを削除すると、アプリケーションの登録は既定で論理的に削除された状態になります。 アプリケーション登録とサービス プリンシパルの関係を理解するには、[Microsoft Entra ID - Microsoft ID プラットフォームのアプリとサービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)に関するページを参照してください。

#### 管理単位

削除の最も一般的なシナリオは、管理単位が誤って削除されても依然として必要とされる場合です。

### ソフト削除からの復旧

すべてのオブジェクト クラスが Microsoft Entra 管理センターで論理的な削除機能を管理するわけではありません。 一部の項目は、deletedItems Microsoft Graph API を使用して一覧表示、表示、ハード削除、または復元されるだけです。

#### 論理的な削除で維持されるプロパティ

| オブジェクトの種類 | 維持される重要なプロパティ |
| --- | --- |
| ユーザー (外部ユーザーを含む) | ObjectID、グループ メンバーシップ、ロール、ライセンス、アプリケーションの割り当てを含むすべてのプロパティが維持される |
| Microsoft 365 グループ | ObjectID、グループ メンバーシップ、ライセンス、アプリケーションの割り当てを含むすべてのプロパティが維持される |
| クラウド セキュリティ グループ | ObjectID、グループ メンバーシップ、アプリケーションの割り当てを含め、保持されているすべてのプロパティ |
| アプリケーションの登録 | すべてのプロパティが維持される。 この表の後の詳細を参照してください。 |
| サービス プリンシパル | すべてのプロパティが維持される |
| 管理単位 | すべてのプロパティが維持される |
| 条件付きアクセス ポリシー | すべてのプロパティが維持される |
| 指定された場所 | すべてのプロパティが維持される |

#### ユーザー

論理的に削除されたユーザーは、Azure portal の **[ユーザー] - [削除済みのユーザー]** ページで確認できます。

ユーザーを復元する方法の詳細については、次のドキュメントを参照してください。

- Azure portal から復元する場合は、[最近削除したユーザーの復元または完全な削除](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore)に関するページを参照してください。
- Microsoft Graph を使用した復元については、[Microsoft Graph v1.0 での削除済みアイテムの復元](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-restore?tabs=http)に関するページを参照してください。

#### グループ

Azure ポータルの **[グループ] |[削除されたグループ]** ページで、ソフト削除された Microsoft 365 グループとクラウド セキュリティ グループを表示できます。

[Image: Azure portal でのグループの復元を示すスクリーンショット。]

重要

セキュリティ グループの論理的な削除は、次のシナリオではサポートされていません。

- OneDrive for Business (OBD) ストレージを使用する EDU テナント
- 従来の Web パーツ (すべてのテナント) でのオーディエンス ターゲティング

論理的に削除された Microsoft 365 グループとクラウド セキュリティ グループを復元する方法の詳細については、次のドキュメントを参照してください。

- Azure portal から復元する場合は、[削除された Microsoft 365 グループの復元](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-restore-deleted)に関するページを参照してください。
- Microsoft Graph を使用した復元については、[Microsoft Graph v1.0 での削除済みアイテムの復元](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-restore?tabs=http)に関するページを参照してください。

#### アプリケーションとサービスプリンシパル

アプリケーションには、アプリケーション登録とサービス プリンシパルの 2 つのオブジェクトが含まれます。 登録とサービス プリンシパルの違いについて詳しくは、[Microsoft Entra ID のアプリとサービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)に関するページを参照してください。

Microsoft Entra 管理センターからアプリケーションを復元するには、**Entra ID**&gt;**アプリ登録**&gt;**削除されたアプリケーション**を参照します。 復元するアプリケーションの登録を選び、**[アプリの登録を復元]** を選択します。

サービス プリンシパルは、Microsoft Graph API の deletedItems を用いて、リスト表示、詳細表示、完全削除、または復元が可能です。 Microsoft Graph を使用したアプリケーションの復元については、[削除済みアイテムを復元する - Microsoft Graph v1.0](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-restore?tabs=http) に関するページを参照してください。

#### 管理単位

管理単位は、deletedItems Microsoft Graph API を使用して一覧表示、表示、または復元できます。 Microsoft Graph を使用して管理単位を復元するには、「 [削除済みアイテムの復元 - Microsoft Graph v1.0」](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-restore?tabs=http)を参照してください。 管理単位が削除されると、論理的に削除された状態のままになり、30 日間復元できますが、その間はハード削除できません。 論理的に削除された管理単位は、30 日後に自動的にハード削除されます。

#### 条件付きアクセス ポリシー

復元後に条件付きアクセス ポリシーの構成を確認して検証し、期待どおりに動作することを確認する必要があります。

条件付きアクセス ポリシーを復元するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**条件付きアクセス**&gt;**削除されたポリシー**にアクセスします。
3. 復元するポリシーの右端にある省略記号 (...) を選択します。
4. **[復元]** を選択します。
5. [ **条件付きアクセス ポリシーの復元]** ダイアログ ボックスで、 [ポリシーをレポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only) で復元するか、削除された状態のままにするかを選択できます( **オン**)。 選択を行い、[ **復元**] を選択します。

Warnung

ポリシーを以前の状態に復元すると、意図しない結果になる可能性があります。 Microsoft では、管理者が最初にレポート専用モードでポリシーを復元してから、時間をかけて確認して有効にすることをお勧めします。

#### 指定された場所

名前付き場所は、信頼済みとしてマークされている場合は削除できません。また、論理的な削除から復旧した場合、その場所は信頼済みとしてマークされません。 復元された名前付き場所は、復元後に再び信頼済みとしてマークする前に確認する必要があります。

名前付きの場所を復元するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[条件付きアクセス]**&gt;**[ネームド ロケーション]**&gt;**[削除されたネームド ロケーション]** に移動します。
3. 復元する場所の右端にある省略記号 (...) を選択します。
4. **[復元]** を選択します。
5. [ **選択した名前付きの場所に復元しますか?** ] ダイアログ ボックスで、[復元] を選択 **します**。

### 物理的な削除

ハード削除では、Microsoft Entra テナントからオブジェクトが完全に削除されます。 論理的な削除がサポートされていないオブジェクトは、この方法で削除されます。 論理的に削除されたオブジェクトも、30 日後にハード削除されます。 論理的な削除をサポートするのは、次のオブジェクトの種類のみです。

- ユーザー
- Microsoft 365 グループ
- クラウド セキュリティ グループ
- アプリケーションの登録
- サービス プリンシパル
- 管理単位
- 条件付きアクセス ポリシー
- 指定された場所

重要

他のすべてのアイテムの種類はハード削除され、復元できません。 これらは再作成する必要があります。 **管理者と Microsoft は、ハード削除されたアイテムを復元できません**。 中断を最小限に抑えるためにプロセスとドキュメントを作成して準備します。

準備と現在の状態の文書化の方法については、「[回復可能性のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/recoverability-overview)」を参照してください。

#### 通常、物理的な削除が行われる場合

ハード削除は、次の状況で発生する可能性があります。

ソフト削除からハード削除への移行:

- 論理的に削除されたオブジェクトは、30 日以内に復元されません。
- 管理者がソフト削除状態のオブジェクトを意図的に削除する。

注意

アプリの 'signInAudience' 値が ("AzureADMultipleOrgs"、"AzureADandPersonalMicrosoftAccount"、または "PersonalMicrosoftAccount") のいずれかに設定されている場合、30日経過してもアプリケーション オブジェクトは自動的にハード削除されることはありません。 この場合、アプリケーション オブジェクトは手動でハード削除する必要があります。

直接、物理的に削除された場合:

- 削除されたオブジェクトの種類は、ソフトデリートをサポートしていない。
- 管理者が (通常は要求に応じて) ポータルを使用して、アイテムを完全に削除することを選択する。
- オートメーション スクリプトは、Microsoft Graph または PowerShell を使用してオブジェクトの削除をトリガーします。 オートメーション スクリプトは、古いオブジェクトをクリーンアップするために一般的に使用されます。 堅牢なオフボーディング プロセスは、重要なオブジェクトを大量に削除する可能性がある間違いを回避するのに役立ちます。

### 物理的な削除からの回復

ハード削除されたアイテムを再作成して再構成する必要があります。 不要なハード削除を避けるのが最善です。

#### ソフト削除されたオブジェクトをレビューする

論理的な削除状態のアイテムを定期的に確認し、必要に応じて復元するプロセスがあることを確認します。 準備の方法:

- [削除されたアイテムを定期的に一覧表示します](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-list?tabs=http)。
- 復元する対象の特定の条件を定義します。
- 必要に応じて、特定のロールまたはユーザーを割り当てて項目を評価および復元します。
- 継続性管理計画を作成してテストします。 詳細については、「 [エンタープライズ ビジネス継続性管理計画に関する考慮事項](https://learn.microsoft.com/ja-jp/compliance/assurance/assurance-developing-your-ebcm-plan)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/recover-from-misconfigurations"} -->
## Microsoft Entra ID の構成ミスから復旧する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-misconfigurations
- Service: entra / architecture
- Article date: 2022-08-26
- Summary: 構成の誤りから回復する方法について説明します。

Microsoft Entra ID の構成設定は、対象を絞った管理アクション、またはテナント全体の管理アクションを通じて、Microsoft Entra テナント内のすべてのリソースに影響を与える可能性があります。

### 構成とは？

構成とは、Microsoft Entra のサービスまたは機能の動作または機能を変える、Microsoft Entra ID におけるすべての変更です。 たとえば、条件付きアクセス ポリシーを構成する場合、どのユーザーがどのような状況で対象アプリケーションにアクセスできるかを変更します。

組織にとって重要な構成項目を理解する必要があります。 次の構成は、セキュリティ体制に大きな影響を与えます。

#### テナント全体の構成

- **外部 ID**: テナントの管理者は、テナントでプロビジョニングできる外部 ID を識別して制御します。 次のことを決定します。

    - テナントで外部 ID を許可するかどうか。
    - どのドメインから外部 ID を追加できるか。
    - ユーザーが他のテナントからユーザーを招待できるかどうか。
- **名前付きの場所**: 管理者は、次の操作に使用できる名前付きの場所を作成できます。

    - 特定の場所からのサインインをブロックする。
    - 多要素認証などの条件付きアクセス ポリシーをトリガーする。
- **許可する認証方法**: 管理者は、テナントに許可する認証方法を設定します。
- **セルフサービス オプション**: 管理者は、セルフサービス パスワード リセットなどのセルフサービス オプションを設定し、テナント レベルで Office 365 グループを作成します。

テナント全体の設定の一部は、グローバルポリシーによって上書きされない限り、スコープ設定が可能です。 次に例を示します。

- テナントで外部 ID が許可されるように構成されている場合でも、リソース管理者は、それらの ID がリソースにアクセスできないように設定できます。
- テナントで個人デバイスの登録が許可されるように構成されている場合でも、リソース管理者は、それらのデバイスが特定のリソースにアクセスできないように設定できます。
- ネームド ロケーションが構成されている場合、リソース管理者は、それらの場所からのアクセスを許可または除外するポリシーを構成できます。

#### 条件付きアクセスの構成

条件付きアクセス ポリシーは、意思決定を行い、組織のポリシーを適用するための指示をまとめたアクセス制御構成です。

[Image: 条件付きアクセス ポリシーに含まれるユーザー、場所、デバイス、アプリケーション、リスク シグナルを示すスクリーンショット。]

条件付きアクセス ポリシーの詳細については、[Microsoft Entra ID における条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)に関するページを参照してください。

注意

構成によりオブジェクトまたはポリシーの動作や機能が変更されますが、オブジェクトに対するすべての変更が構成であるわけではありません。 アイテムに関連付けられているデータまたは属性は、そのユーザー オブジェクトの機能に影響を与えることなく変更できます (ユーザーのアドレスの変更など)。

### 構成の誤りとは

構成の誤りとは、組織のポリシーや計画から逸脱し、意図しない結果や望ましくない結果を引き起こすリソースまたはポリシーの構成です。

テナント全体の設定または条件付きアクセス ポリシーの構成の誤りによって以下の事態が引き起こされると、セキュリティと組織のパブリック イメージに深刻な影響を与える可能性があります。

- 管理者、テナント ユーザー、外部ユーザーによるテナント内のリソースの操作方法が変更される。

    - リソースへのアクセスが不必要に制限される。
    - 機密性の高いリソースへのアクセス制御が緩和される。
- ユーザーが他のテナントと対話する、および外部ユーザーがテナントと対話する機能が変更される。
- 顧客による自身のアカウントへのアクセスが許可されないなどが原因で、サービス拒否が発生する。
- データ、システム、およびアプリケーション間の依存関係が壊れ、ビジネス プロセス エラーが発生する。

#### 誤設定はいつ発生するのか？

構成の誤りは、次の場合に発生する可能性が最も高くなります。

- アドホック変更中に誤りが発生した。
- トラブルシューティングの演習の結果、誤りが発生した。
- 悪意のあるアクターが悪意を持ってアクションを実行した。

### 構成の誤りを防ぐ

Microsoft Entra テナントの目的の構成を変更する場合、次のような堅牢な変更管理プロセスに従うことが重要です。

- 変更を文書化し、以前の状態や意図した変更後の状態を含める。
- Privileged Identity Management (PIM) を使用して、変更の意思がある管理者が、その権限を意図的にエスカレートする必要があることを確認する。 PIM の詳細については、「[Privileged Identity Management とは](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)」を参照してください。
- [特権の PIM エスカレーションの承認](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-approval-workflow)を必要とするなど、変更の際には強力な承認ワークフローを使用する。

### 構成の変更の監視

構成の誤りを防ぐ一方で、管理者が作業を効率的に実行できなくなるほど、変更の要件を厳しくすることはできません。

[Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)で次の操作を監視して、構成の変更を注意深く監視します。

- 追加
- 作成
- 更新
- 設定
- 削除

次の表には、監査ログで検索できる有益なエントリが含まれています。

#### 条件付きアクセスと認証方法の構成の変更

条件付きアクセス ポリシーは、Azure portal の **[条件付きアクセス]** ページで作成します。 ポリシーに対する変更は、ポリシーの **[条件付きアクセス ポリシーの詳細]** ページで行います。

| サービス フィルター | 活動 | 生じる可能性のある影響 |
| --- | --- | --- |
| 条件付きアクセス | 条件付きアクセス ポリシーの追加、更新、削除 | アクセスが不適切に付与またはブロックされることがあります。 |
| 条件付きアクセス | ネームド ロケーションの追加、更新、削除 | 条件付きアクセス ポリシーによって使用されるネットワークの場所が意図したとおりに構成されず、条件付きアクセス ポリシーの条件にギャップが生じます。 |
| 認証方法 | 認証方法ポリシーの更新 | ユーザーは弱い認証方法を使用できます。または、使用する必要がある方法からブロックされます。 |

#### ユーザーとパスワードのリセットの構成の変更

ユーザー設定の変更は、Azure portal の **[ユーザー設定]** ページで行います。 パスワードのリセットの変更は、**[パスワード リセット]** ページで行います。 これらのページで行われた変更は、次の表に示すように監査ログに記録されます。

| サービス フィルター | アクティビティ | 生じる可能性のある影響 |
| --- | --- | --- |
| コア ディレクトリ | 会社の設定の更新 | ユーザーは、アプリケーションを登録できる場合と、意図に反してできない場合があります。 |
| コア ディレクトリ | 会社情報の設定 | ユーザーが、意図に反して、Microsoft Entra 管理ポータルにアクセスできたり、できなかったりする場合があります。 サインイン ページには、評判を損なう可能性のある会社のブランドは示されません。 |
| コア ディレクトリ | **アクティビティ**: サービス プリンシパルの更新**ターゲット**: 0365 LinkedIn 接続 | ユーザーが、意図に反して、Azure AD アカウントを LinkedIn に接続できたり、できなかったりする場合があります。 |
| セルフサービスのグループ管理 | MyApps 機能値の更新 | ユーザーは、ユーザー機能を使用できる場合と、意図に反してできない場合があります。 |
| セルフサービスのグループ管理 | ConvergedUXV2 機能値の更新 | ユーザーは、ユーザー機能を使用できる場合と、意図に反してできない場合があります。 |
| セルフサービスのグループ管理 | MyStaff 機能値の更新 | ユーザーは、ユーザー機能を使用できる場合と、意図に反してできない場合があります。 |
| コア ディレクトリ | **アクティビティ**: サービス プリンシパルの更新**ターゲット**: Microsoft パスワードのリセット サービス | ユーザーは意図に反してパスワードをリセットできる場合とできない場合があります。 ユーザーは、セルフサービス パスワード リセットに登録する必要がある場合と、意図に反して必要がない場合があります。 ユーザーは、未承認の方法 (セキュリティの質問を使用するなど) でパスワードをリセットできます。 |

#### 外部 ID 構成の変更

これらの設定は、Azure portal の **[外部 ID]** または **[外部コラボレーション]** の設定ページで変更できます。

| サービス フィルター | アクティビティ | 生じる可能性のある影響 |
| --- | --- | --- |
| コア ディレクトリ | クロステナント アクセス設定に対するパートナーの追加、更新、削除 | ユーザーにはブロックされるべきテナントへのアウトバウンドアクセスがあります。ブロックする必要がある外部テナントのユーザーには、受信アクセス権があります。 |
| B2C | ID プロバイダーの作成または削除 | 共同作業を可能にするユーザーの ID プロバイダーが見つからないため、それらのユーザーのアクセスがブロックされます。 |
| コア ディレクトリ | テナントでのディレクトリ機能の設定 | 外部ユーザーのディレクトリ オブジェクトの可視性は、意図したより高くまたは低くなります。外部ユーザーは、意図に反して、他の外部ユーザーをテナントに招待するかしないかもしれません。 |
| コア ディレクトリ | ドメインのフェデレーションの設定 | 外部ユーザーの招待は、他のテナントのユーザーに送信される場合と、意図に反して送信されない場合があります。 |
| 承認ポリシー | 承認ポリシーの更新 | 外部ユーザーの招待は、他のテナントのユーザーに送信される場合と、意図に反して送信されない場合があります。 |
| コア ディレクトリ | 更新ポリシー | 外部ユーザーの招待は、他のテナントのユーザーに送信される場合と、意図に反して送信されない場合があります。 |

#### カスタム役割とモビリティ定義の構成の変更

| サービス フィルター | アクティビティ/ポータル | 生じる可能性のある影響 |
| --- | --- | --- |
| コア ディレクトリ | ロール定義の追加 | カスタム役割のスコープが意図したよりも狭くなっている場合や広くなっている場合があります。 |
| PIM | ロール設定の更新 | カスタム役割のスコープが想定より狭すぎたり広すぎたりする。 |
| コア ディレクトリ | ロール定義の更新 | カスタム役割のスコープが意図されたよりも狭すぎたり広すぎたりしている。 |
| コア ディレクトリ | ロールの定義の削除 | カスタム役割が見つからなくなります。 |
| コア ディレクトリ | 委任されたアクセス許可の付与の追加 | モバイル デバイス管理またはモバイル アプリケーション管理の構成が見つからない、または正しく構成されないことが原因で、デバイスまたはアプリケーションの管理が失敗します。 |

#### 監査ログの詳細ビュー

監査ログで一部の監査エントリを選択すると、古いおよび新しい構成値の詳細が表示されます。 たとえば、条件付きアクセス ポリシーの構成を変更した場合、次のスクリーンショットの情報を確認できます。

[Image: 条件付きアクセス ポリシーの変更に関する監査ログの詳細を示すスクリーンショット。]

### ワークブックを使用して変更を追跡する

Azure Monitor ワークブックは、構成変更を監視するのに役立ちます。

[機密性の高い操作のレポート ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-sensitive-operations-report)は、侵害を示す可能性のある、アプリケーションやサービス プリンシパルの次のような疑わしいアクティビティを識別するのに役立ちます。

- 変更されたアプリケーション、サービス プリンシパルの資格情報、または認証方法。
- サービス プリンシパルに付与された新しいアクセス許可。
- サービス プリンシパルのディレクトリ ロールとグループ メンバーシップの更新。
- 変更されたフェデレーション設定。

[テナント間アクセス アクティビティ ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-cross-tenant-access-activity)は、ユーザーがアクセスしている外部テナント内のアプリケーションと、テナント外部ユーザーがアクセスしているアプリケーションを監視するのに役立ちます。 このワークブックを使用して、複数のテナント間での受信または送信アプリケーション アクセスの異常な変更を検索します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/recoverability-overview"} -->
## Microsoft Entra ID での回復可能性のベスト プラクティス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/recoverability-overview
- Service: entra / architecture
- Article date: 2025-11-03
- Summary: リカバリ性を高めるためのベスト プラクティスについて説明します。

テナントには、意図しない削除や構成の誤りが生じます。 こうした意図しないイベントの影響を最小限に抑えるためには、その発生に備える必要があります。

リカバリ性とは、意図しない変更が生じたサービスを元の正常な状態に戻すことのできる準備のプロセスと機能のことです。 意図しない変更には、Microsoft Entra テナント内のアプリケーション、グループ、ユーザー、ポリシー、その他のオブジェクトの論理削除、物理削除、または構成の誤りなどがあります。

リカバリ性には、組織の回復性を高める効果があります。 回復性は関連しているものの、異なるものです。 回復性とは、システム コンポーネントの中断に耐え、ビジネス、ユーザー、顧客、および運用への影響を最小限にして復旧する能力です。 システムの回復性を高める方法の詳細については、「[Microsoft Entra ID を使って ID およびアクセス管理の回復性を強化する](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-overview)」を参照してください。

この記事では、削除と構成の誤りに備えて、企業のビジネスに対する意図しない結果を最小限に抑えるためのベスト プラクティスについて説明します。

### 削除と構成の誤り

削除と構成の誤りは、テナントにさまざまな影響を及ぼします。

#### 削除

削除の影響は、オブジェクトの種類によって異なります。

ユーザー、Microsoft 365 グループ、クラウド セキュリティ グループ、アプリケーションを含むオブジェクトの種類を論理的に削除できます。 論理的に削除されたアイテムは、Microsoft Entra IDのごみ箱に移動します。 ごみ箱では、アイテムは使用できませんが、すべてのプロパティが保持されます。 Microsoft Graph API呼び出しまたはMicrosoft Entra 管理センターから復元できます。 ソフト削除状態のアイテムを 30 日以内に復元しない場合、Microsoft Entra ID はそれらを完全にハード削除します。 [Microsoft Entra ID での削除からの回復](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions#properties-maintained-with-soft-delete) では、論理削除をサポートするオブジェクトの一覧表を提供します。

[Image: ユーザー、Microsoft 365 グループ、クラウド セキュリティ グループ、アプリケーションが論理的に削除され、30 日後にハード削除されることを示す図。]

重要

その他の種類のオブジェクトはすべて、削除が選択されるとすぐ物理的に削除されます。 物理的に削除されたオブジェクトを復旧させることはできません。 作成し直して再構成する必要があります。

削除とその復元方法の詳細については、「[削除からの回復](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions)」を参照してください。

#### 構成の不備

構成の誤りとは、組織のポリシーや計画から逸脱し、意図しない結果や望ましくない結果を引き起こすリソースまたはポリシーの構成のことです。 テナント全体の設定や条件付きアクセス ポリシーの構成の誤りによって、セキュリティと、組織のパブリック イメージに重大な影響を与える可能性があります。 構成の誤りにより、次の可能性があります。

- 管理者、テナント ユーザー、外部ユーザーによるテナント内のリソースの操作方法が変更される。
- ユーザーが他のテナントと対話する、および外部ユーザーがテナントと対話する機能が変更される。
- サービス拒否を引き起こす。
- データ、システム、アプリケーション間の依存関係が解除される。

構成の誤りとそこから回復させる方法について詳しくは、[構成の誤りからの回復](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-misconfigurations)に関する記事を参照してください。

削除とは異なり、構成ミスによって、オブジェクトはごみ箱に移動されるのではなく、所定の位置に変更されます。 サポート対象の影響を受けるオブジェクトの種類については、[Microsoft Entra Backup and Recovery](https://learn.microsoft.com/ja-jp/entra/backup/overview) の差分レポートを使用して、変更された属性とリンクの編集内容を特定します。 その後、復旧ジョブを実行して、オブジェクトを以前の状態にロールバックできます。 Microsoft Entra Backup and Recovery でサポートされていない構成については、文書化された既知の正常な状態に基づいて設定を再適用してください。

### 共有責任

回復性は、クラウド サービス プロバイダーとしての Microsoft とお客様の組織とが共同で担う責任です。

[Image: 計画と回復に関して Microsoft とお客様との間で分担される責任を示す図。]

削除や構成の誤りには、Microsoft から提供されるツールとサービスを使用して備えることができます。

### ビジネス継続性と障害計画

物理的に削除されたアイテムや構成に誤りのあるアイテムを復元するのは、多くのリソースを必要とするプロセスです。 事前に計画することで、必要なリソースを最小限に抑えることができます。 特別に復元を担当する管理者のチームを設置することを検討してください。

#### 復元プロセスをテストする

オブジェクトの種類ごとの復元プロセスと、それに伴う情報伝達をリハーサルしてください。 リハーサルは、必ずテスト オブジェクトで行ってください。テスト テナント内で行うのが理想です。

計画をテストすると、次のことを判断するのに役立ちます。

- オブジェクトの状態を記述したドキュメントの有効性と完全性。
- 解決までの標準的な所要時間。
- 適切な情報伝達とその対象ユーザー。
- 期待される成功と潜在的な課題。

#### 情報伝達プロセスを作成する

問題と復元のタイムラインを他の人々が把握できるよう、事前に定義された情報伝達のプロセスを作成します。 復元の情報伝達計画には、次の点を含めるようにします。

- 伝えられる情報伝達の種類。定義済みテンプレートの作成を検討してください。
- 情報伝達の受け手となる関係者。 適宜、次のグループを含めます。

    - 影響を受ける経営者。
    - 復旧作業を行う運用管理者。
    - ビジネス承認者と技術承認者。
    - 影響を受けるユーザー。
- 情報伝達のトリガーとなるイベントの定義。その例を次に示します。

    - 最初の削除。
    - 影響のアセスメント。
    - 解決までの時間。
    - 復元。

### 既知の良好な状態を文書化する

テナントとそのオブジェクトの状態を定期的に文書化し、外部のバージョン管理されたリポジトリに保持します。 ハード削除や構成の誤りが発生した場合、ドキュメントは復旧のロードマップとして機能します。

デプロイされたリソースに基づいて、必要な API を選択し、テクノロジをエクスポートします。 リソース固有のMicrosoft Graph API を直接呼び出すことができますが、他のMicrosoftやMicrosoft以外のオプションを使用すると、構成のエクスポートとダウンロードのプロセスを抽象化して効率化できます。

- **構成スナップショット**—Microsoft Graph の統合 [テナント構成管理 (TCM) API](https://learn.microsoft.com/ja-jp/graph/unified-tenant-configuration-management-concept-overview) に含まれる [スナップショット API](https://learn.microsoft.com/ja-jp/graph/api/resources/configurationsnapshotjob) は、テナント内の複数のワークロード (Microsoft Entra、Microsoft Intune、Exchange Online など) にまたがる現在の構成の抽出を簡素化します。 テナントでは、外部保管用にスナップショットをダウンロードできるよう、スナップショットを 7 日間保存します。 TCM スキーマは、Microsoft Entraリソースとプロパティのサブセットをサポートします。 サブセットの一覧を確認して、MICROSOFT GRAPH API を直接呼び出すのではなく、TCM が十分なカバレッジを提供しているかどうかを判断します。
- \* **Microsoft Graph API** - Microsoft Graph API を使用して、TCM がまだサポートしていないすべての重要なディレクトリ オブジェクトの構成を定期的にエクスポートします。 構成設定をエクスポートするには、オープン ソース ツール[である Exporter Microsoft Entra](https://github.com/microsoft/entraexporter)使用します。
- **サード パーティのソリューション** - 宣言形式でMicrosoft Entra構成をエクスポート、正規化、および格納するには、Microsoft以外の構成管理およびコードとしてのインフラストラクチャ ツールを評価します。 これらのソリューションは、基になる API を抽象化し、大規模な構成キャプチャを簡略化し、回復ワークフローの一部として反復可能な比較と設定の再適用をサポートする場合があります。

十分な保有期間を持つバージョン管理されたリポジトリ (Azure DevOpsやGitHubなど) に構成基準を格納します。 さまざまなキャプチャ メカニズムを使用して取得した構成抽出を論理的に分離し、個別に管理します。 たとえば、TCM スナップショットと直接の Microsoft Graph API エクスポートは、全体的な既知の良好な状態に貢献できますが、同じ形式 (JSON など) がある場合でも、それらを組み合わせないでください。 その理由は、TCM スナップショット スコープによって、サポートされているリソースとプロパティに制限されているため、サポートされているスコープ外の構成を再作成または再適用するために使用することはできません。

#### よく使用される Microsoft Graph API

Microsoft Graph API を使うと、Microsoft Entra のさまざまな構成から現在の状態をエクスポートできます。 この API は、以前の状態について参照する資料や、エクスポートしたコピーからその状態を適用する機能が、事業を継続するうえで不可欠となるほとんどのシナリオに対応します。

Microsoft Graph API は、組織のニーズに応じて細かくカスタマイズできます。 バックアップや参考資料のためのソリューションを実装するためには、データの照会、保存、表示のためのコードを開発者が設計しなければなりません。 その機能の一部として、オンライン コード リポジトリが多くの実装で使用されています。

#### 回復に役立つ API

| リソースの種類 | リファレンスのリンク |
| --- | --- |
| ユーザー、グループなどのディレクトリ オブジェクト | [directoryObject API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryobject)[user API](https://learn.microsoft.com/ja-jp/graph/api/resources/user)[グループ API](https://learn.microsoft.com/ja-jp/graph/api/resources/group)[application API](https://learn.microsoft.com/ja-jp/graph/api/resources/application)[servicePrincipal API](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) |
| ディレクトリ ロール | [directoryRole API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryrole)[roleManagement API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement) |
| 条件付きアクセス ポリシー | [条件付きアクセス ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/conditionalaccesspolicy) |
| デバイス | [デバイス API](https://learn.microsoft.com/ja-jp/graph/api/resources/device) |
| ドメイン | [ドメイン API](https://learn.microsoft.com/ja-jp/graph/api/domain-list?tabs=http) |
| 管理単位 | [管理単位 API](https://learn.microsoft.com/ja-jp/graph/api/resources/administrativeunit) |
| 削除済みアイテム\* | [deletedItems API](https://learn.microsoft.com/ja-jp/graph/api/resources/directory) |

\*これらの構成エクスポートは、少数の管理者にのみアクセス権を与えて安全に保存してください。

必要となるドキュメントのほとんどは、[Microsoft Entra Exporter](https://github.com/microsoft/entraexporter) から入手できます。

- 望ましい構成が導入済みであることを確認します。
- エクスポーターを使用して現在の構成を収集します。
- エクスポートを確認し、自分のテナントのエクスポートされていない設定を把握して手動で文書化します。
- アクセスが制限された安全な場所に出力を保存します。

注

レガシ多要素認証ポータル内の、アプリケーション プロキシとフェデレーション設定に関する設定は、Microsoft Entra Exporter や Microsoft Graph API ではエクスポートされない場合があります。

[条件付きアクセスの Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/conditionalaccesspolicy) を使用すると、ポリシーをコードのように管理できます。

#### オブジェクト間の依存関係をマッピングする

一部のオブジェクトを削除すると、依存関係が原因で他に影響が波及することがあります。 たとえば、アプリケーションの割り当てに使用されるクラウド セキュリティ グループを削除すると、そのグループのメンバーであるユーザーは、グループが割り当てられたアプリケーションにアクセスできなくなります。

##### 共通の依存関係

| オブジェクトの種類 | 潜在的な依存関係 |
| --- | --- |
| アプリケーション オブジェクト | サービス プリンシパル (エンタープライズ アプリケーション)。 アプリケーションに割り当てられているグループ。 アプリケーションに影響を与える条件付きアクセス ポリシー。 |
| サービス プリンシパル | アプリケーション オブジェクト。 |
| 条件付きアクセス ポリシー | ポリシーに割り当てられたユーザー。ポリシーに割り当てられたグループ。ポリシーの対象となるサービス プリンシパル (エンタープライズ アプリケーション)。 |
| Microsoft 365 グループおよびクラウド セキュリティ グループ以外のグループ | グループに割り当てられたユーザー。グループが割り当てられている条件付きアクセス ポリシー。グループにアクセス権が割り当てられているアプリケーション。 |

### 監視とデータ保持

[Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)には、テナント内で実行されたすべての削除操作と構成操作に関する情報が含まれています。 これらのログは、[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview) などのセキュリティ情報イベント管理ツールにエクスポートすることをお勧めします。 また、Microsoft Graph を使って変更を監査すれば、一定期間にわたって差分を監視するカスタム ソリューションを構築することもできます。 Microsoft Graph を使って削除済みアイテムを検索する方法について詳しくは、[Microsoft Graph v1.0 での削除済みアイテムの一覧表示](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-list?tabs=http)に関する記事を参照してください。

[Microsoft Entra Backup and Recovery](https://learn.microsoft.com/ja-jp/entra/backup/overview) でサポートされるオブジェクトの変更を特定するには、差分レポートを作成します。 差分レポートは、前回のバックアップ以降に回復可能な追加、属性の編集、リンクの編集、論理的な削除を表示することで、監査ログと構成スナップショットを補完します。 差分レポートには、ハード削除されたオブジェクトは表示されません。

#### 監査ログ

テナントからアクティブ状態のオブジェクトが削除されると、監査ログには必ず "&lt;オブジェクト&gt; の削除" (アクティブから論理削除またはアクティブから物理削除のどちらかの) イベントが記録されます。

[Image: 監査ログの詳細を示すスクリーンショット。]

[ソフト削除をサポートするオブジェクトの種類](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions#properties-maintained-with-soft-delete)（アプリケーション、サービス プリンシパル、ユーザー、Microsoft 365 グループ、クラウド セキュリティ グループなど）の Delete イベントは、ソフト削除を示します。 他の種類のオブジェクトの場合、Delete イベントはハード削除です。

| オブジェクトの種類 | ログ内のアクティビティ | 結果 |
| --- | --- | --- |
| アプリケーション | アプリケーションとサービス プリンシパルの削除 | 論理的な削除 |
| アプリケーション | アプリケーションの物理的な削除 | 物理的な削除 |
| サービス プリンシパル | サービス プリンシパルを削除する | 論理的な削除 |
| サービス プリンシパル | サービス プリンシパルの物理的な削除 | 物理的な削除 |
| ユーザー | ユーザーの削除 | 論理的な削除 |
| ユーザー | ユーザーの物理的な削除 | 物理的な削除 |
| Microsoft 365 グループ | グループの削除 | 論理的な削除 |
| Microsoft 365 グループ | グループの物理的な削除 | 物理的な削除 |
| セキュリティ グループ | グループの削除 | 論理的な削除 |
| セキュリティ グループ | グループの物理的な削除 | 物理的な削除 |
| 他のすべてのオブジェクト | "objectType" を削除する | 物理的な削除 |

注

監査ログでは、削除されたグループの種類は区別されません。 Microsoft 365 グループとクラウド セキュリティ グループは論理的に削除されます。 [グループの削除] エントリが表示される場合は、Microsoft 365 グループまたはクラウド セキュリティ グループの論理的な削除、または別の種類のグループのハード削除である可能性があります。 既知の良好な状態のドキュメントには、組織内にあるグループごとにグループの種類を含める必要があります。

構成の変更の監視については、[構成の誤りからの回復](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-misconfigurations)に関する記事を参照してください。

#### ワークブックを使用して構成の変更を追跡する

Azure Monitor ワークブックは、構成変更を監視するのに役立ちます。

[機密性の高い操作のレポート ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-sensitive-operations-report)は、侵害を示す可能性のある、アプリケーションやサービス プリンシパルの次のような疑わしいアクティビティを識別するのに役立ちます。

- 変更されたアプリケーション、サービス プリンシパルの資格情報、または認証方法。
- サービス プリンシパルに付与された新しい権限。
- サービス プリンシパルのディレクトリ ロールとグループ メンバーシップの更新。
- 変更されたフェデレーション設定。

「[テナント間アクセス アクティビティ ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-cross-tenant-access-activity)」は、外部テナント内のどのアプリケーションにユーザーがアクセスしているか、また外部ユーザーが自分のテナント内のどのアプリケーションにアクセスしているかを監視するのに役立ちます。 このワークブックを使用して、テナント間で受信または送信アプリケーションアクセスにおける異常な変更を検出します。

[Microsoft Entraバックアップと回復](https://learn.microsoft.com/ja-jp/entra/backup/overview)は、サポートされているオブジェクトの種類とその保持期間内の構成変更に対して、最も低い労力で忠実度の高い復元パスを提供する組み込み機能です。 Microsoft Entraバックアップと回復では、一連のテナント オブジェクトの[種類](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations)と回復可能なプロパティがサポートされます。 サポートされているオブジェクトのバックアップは、Microsoft定義された固定間隔で自動的に作成されます。 使用可能なバックアップの表示、差分レポートの作成、復旧オブジェクトの作成、復旧履歴の確認を行うことができます。

### 運用上のセキュリティ

オブジェクトの再作成や再構成が必要になることより、不要な変更を防止することの方がはるかに簡単です。 アクシデントを最小限に抑えるために、変更管理プロセスには次のタスクを含めるようにしてください。

- 最小特権モデルを使用する。 チームの各メンバーが、通常のタスクを完了するために必要な最小限の特権を持っていることを確認します。 通常とは異なるタスクのために、特権をエスカレートするプロセスが必要です。
- 構成と削除は、オブジェクトの管理制御で有効にする。 作成、更新、削除 (CRUD) の操作を必要としないタスクには、[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)のような、より特権の少ないロールを使用します。 CRUD 操作が必要な場合は、可能な限り、オブジェクト固有のロールを使用します。 たとえば、ユーザー管理者はユーザーのみを削除でき、アプリケーション管理者はアプリケーションのみを削除できます。 可能な限り、こうしたより制限されたロールを使用してください。
- [Privileged Identity Management (PIM) を使用する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)。 PIM を使用すると、権限の Just-In-Time エスカレーションによって物理的な削除などのタスクを許可できます。 PIM を構成することで、特権エスカレーションの通知を受け取ったり承認を行ったりすることができます。
- [Microsoft Entra ID で保護されたアクション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)を使用して、使用されているロールやユーザーにアクセス許可が付与された方法に関係なく、条件付きアクセス ポリシー保護の追加レイヤーを適用します。 保護されたアクションは、ハード削除、認証コンテキストの変更、条件付きアクセスの変更などの機密性の高い操作に適用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/recoverability-tenant"} -->
## テナントの回復可能性を計画する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/recoverability-tenant
- Service: entra / architecture
- Article date: 2026-06-25
- Summary: 共有責任モデルでテナント スコープの復旧を準備して実行する方法について説明します。

テナント オブジェクト Microsoft Entraに対する誤った削除、構成の誤り、または悪意のある変更により、ユーザーのサインインが中断され、ビジネスクリティカルなアプリケーションへのアクセスがブロックされ、ダウンストリームの操作に迅速に影響する可能性があります。 この記事では、 [共有責任モデル](https://learn.microsoft.com/ja-jp/entra/architecture/recoverability-overview)の下でテナント スコープの復旧を準備して実行する方法について説明します。 次の図は、障害モード全体のカバレッジを最大化する階層化されたアプローチを示しています。

[Image: 障害モード全体のカバレッジを最大化するための回復性のための階層化アーキテクチャを示す図。]

組織の全体的な ID 回復性戦略には、常に回復可能性を含めます。

- [Microsoft Entra IDを使用して ID とアクセス管理に回復性を組み込む](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-overview)には、サービス レベルの回復性と高可用性に関するガイダンスが提供されます。
- [インシデント対応の概要](https://learn.microsoft.com/ja-jp/security/operations/incident-response-overview) では、サイバーセキュリティインシデント対応ガイダンスについて詳しく説明します。

中断の可能性を減らし、迅速に回復するには、ワークフローに次の推奨事項を含めます。

- **共同責任モデルを念頭に置いて設計**します。 Microsoftは、サービス レベルの冗長性、分離、自動化された軽減策を提供します。 侵害によってテナント オブジェクトまたは構成が変更されたときに、既知の良好なテナント状態を復元する準備を整えます。
- **外部のバージョン管理された既知の正常な状態を維持します**。 テナント構成管理 (TCM) API とMicrosoft Graphエクスポートを使用して、構成を定期的にキャプチャします。 必要に応じて、手動またはプログラムで設定を再適用できます。
- **Microsoft がサポートしている場合は、組み込みの回復オプションを優先してください**。 Microsoft Entraバックアップと復旧では、サポートされているオブジェクトとそのリテンション期間内の構成変更に対して、最も低い作業量の復元パスが提供されます。
- **復旧シナリオで必要な場合は、組み込みの機能を超えてカバレッジを拡張します**。 Microsoft以外のバックアップと回復のソリューションを評価します。 サポートされていないオブジェクトの種類、詳細な属性のロールバック、保持期間の延長、およびハード削除されたオブジェクトを再作成する必要があるシナリオのオプションについて説明します。
- **復旧を運用化します**。 既定のウィンドウを超えて監査ログとサインイン ログを保持します。 目標復旧時間 (RTO) と目標復旧ポイント (RPO) を定義します。 通常の復旧訓練を実行して、復旧が負荷の下で実行可能になるようにします。
- **回復イベントを回避 (および簡略化) するために、ブラスト半径を減らします**。 最小限の特権管理、Microsoft Entra Privileged Identity Management (PIM)、Just-In-Time (JIT)、保護されたアクション、緊急アクセス アカウント、および重要[なワークロードのテナント分離](https://learn.microsoft.com/ja-jp/entra/architecture/secure-single-tenant) (保証されている場合) を実装します。

### 回復性と回復性を比較する

この記事では、回復性と回復性を区別します。 サービス レベルの回復性と回復可能性は、組織の ID 回復性のディメンションです。 ID レジリエンスとは、Microsoft Entra ID などの中核となる認証システムを保護し、安全を確保し、迅速に復旧する能力です。 これは、より広範な運用回復性の重要なコンポーネントです。 運用上の回復性とは、重要なビジネス サービスの実行を継続できるように、中断を防ぎ、適応し、中断から回復する機能です。 回復性と回復性は、個々の製品機能ではありません。 むしろ、これらは社会技術システム (人、プロセス、テクノロジ) のエンドツーエンドのプロパティです。

#### 中断を通じて運用を継続するための回復性

回復性とは、サービス コンポーネント、依存関係、または接続の障害時に、ID とアクセスの機能を継続できる設計および運用上の手段を指します。機能が低下しているがセーフ モードの場合もあります。 主な目標は、中断状態が存在する間にユーザーとアプリケーションへの影響を最小限に抑えるためです。

一般的な回復性のシナリオは次のとおりです。

- ID サービス パスの可用性エラー (一時的なMicrosoft Entra サービスの中断など)
- ネットワークまたは DNS エラー
- フェデレーションまたは多要素認証 (MFA) の依存先の障害
- トークン取得の中断

Microsoft Entraは、プラットフォーム レベルで高可用性を実現します。 共有責任モデルに従って、回復性の結果をサポートするようにテナントの構成と統合を設計します。 認証方法の選択、依存関係の最小化、一時的な障害に対するアプリケーションの許容度を含めます。 エラーまたは悪意のあるテナントの変更を元に戻すための回復性に依存しないでください。

#### 中断後にオブジェクトを目的の状態に復元できる回復性

回復可能性とは、意図しない変更や悪意のある変更の後Microsoft Entraテナント オブジェクトと構成を既知の正常な状態に復元するために必要な機能と Runbook を指します。 主な目標は、テナントの変更を検出し、サポートされている復旧パスを使用し、明確な運用 Runbook アクションを定義することで、復旧時間と元に戻せない影響を最小限に抑することです。

一般的な回復可能性のシナリオは次のとおりです。

- テナント構成の整合性エラー (ディレクトリ オブジェクトの誤削除など)
- 構成の誤り (条件付きアクセスやアプリケーションのアクセス許可の変更など)
- 侵害に基づく変更 (不正アクセスを確立または維持する大量削除や変更を含む)

共有責任モデルに従って、問題が発生する前にテナント オブジェクトと構成を含むテナント レベルの回復可能性を計画します。 Microsoftが提供する回復機能、API、ログを運用化します。 すべてのサービスの停止や依存関係の障害を排除するために回復可能性に依存しないでください。

実際には、インシデントはレジリエンスと復旧性の両方に関わる場合があります。 侵害を起因とする事象では、復旧対応（テナントの状態の復元と再保護）が必要になる場合があります。 緊急アクセス アカウントや独立した管理パスなどの回復性パターンにより、制御にかかる時間を大幅に短縮し、爆発半径を制限し、その後の復旧を簡略化できます。

#### Microsoft Entraの回復性

Microsoft Entraには、99.99% 可用性 SLA が、階層化された冗長性、継続的な正常性の監視、自動化された軽減策を備えた分散型のクラウドネイティブ ID サービスとして提供されています。 バックアップ認証などの機能は、プライマリ サービスの中断中にサインイン継続性を維持するのに役立ちます。 次のリソースでは、単一の認証パスへの依存関係を減らすために採用できる回復性モデルと設計パターンについて説明します。

- [Microsoft Entra IDを使用した ID およびアクセス管理の回復性](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-overview) (概要)
- [Microsoft Entra アーキテクチャの概要](https://learn.microsoft.com/ja-jp/entra/architecture/architecture)
- Microsoft Entra ID (インフラストラクチャ パターンと依存関係の最小化) を[使用して IAM インフラストラクチャに回復性を構築](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-infrastructure)する
- [アプリケーションの認証と承認の回復性の向上 (アプリケーション開発の](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview) 回復性)
- [Microsoft Entra IDバックアップ認証システム](https://learn.microsoft.com/ja-jp/entra/architecture/backup-authentication-system) (サポートされているアプリとサービスがプライマリ サービスの中断中にサインイン継続性を維持する方法)

Microsoft Entra回復性の結果は、テナントの構成と統合の選択によって異なります。 例えば次が挙げられます。

- 資格情報と MFA に関する堅牢な戦略を使用してください。
- 脆弱な依存関係 (フェデレーションまたはオンプレミス接続での単一障害点の回避など) を減らします。
- 一時的な認証とトークン取得の失敗を許容するアプリケーションを構築します。

この記事では、復旧性に関する相補的な領域、すなわち整合性が損なわれた後にテナント単位の復旧を準備し、実行するための取り組みに焦点を当てています。

### テナントを対象とした復旧に備える

サービス レベルのセーフガードをテナント レベルの回復可能性から分離することが重要です。 Microsoftでは、冗長性と分離の手段を使用して、インフラストラクチャとコンポーネントの障害によってサービスの動作を維持します。 これに対し、回復可能性は、論理的なエラーや、ディレクトリ オブジェクトの誤った削除、構成の誤り、悪意のある変更などのテナント レベルの変更シナリオに対処します。 このようなシナリオでは、Microsoftは、既知の良好な状態を文書化し、ディレクトリの変更を監視し、ソフト オブジェクトの削除と構成ミスのシナリオから回復できるように、機能と API を提供します。

Microsoftと組織[は、回復性に対する責任を共有します](https://learn.microsoft.com/ja-jp/entra/architecture/recoverability-overview)。 Microsoft提供されているツールとサービスを使用して、テナント内の削除や構成の誤りに備え、対応します。

意図しない変更や悪意のある変更に続くビジネス継続性を確保するには、復旧準備完了の体制を事前に確立します。 継続的なビジネス継続性ディザスター リカバリー (BCDR) 計画には、次の手順を含めます。

1. テナントの既知の良好な状態を定義して文書化します。 復旧できるのは、復元できる明確なベースラインがある場合のみです。 永続的なオブジェクトの削除後の迅速な再構築を容易にするには、環境の正常な構成の外部バージョン管理されたレコードを維持します。 階層化されたキャプチャ アプローチを採用する。 復旧計画に必要なスコープと忠実性を提供するメカニズムを選択します。

- **テナント構成管理 (TCM) スナップショットは、**Microsoft Graphサポートされているリソースとプロパティの定義済みのサブセットに対して、ワークロード間のカバレッジを提供します。
- **Microsoft Graph API の直接エクスポート**は、TCM のスコープ外にあるオブジェクトを補完的にカバーします。
- **非Microsoft構成管理ツールは**、抽象化、正規化、宣言型の再適用ワークフローをサポートします。

異なるメカニズムからのエクスポートを、バージョン管理された外部リポジトリに格納します。 組織の目標復旧ポイント (RPO) を満たすように保持期間を設定します。 エクスポートとキャプチャ メカニズムを論理的に分離します。 TCM スナップショットはサポートされているリソースのみを対象とし、そのスコープ外の構成を再適用できないことに注意してください。 [回復性のベスト プラクティスでは](https://learn.microsoft.com/ja-jp/entra/architecture/recoverability-overview) 、API の例とツール オプションが提供されます。

Note

GitHubやAzure DevOpsなどの外部リポジトリを使用して復旧する場合は、循環依存関係を計画します。 これらのプラットフォームが認証に同じMicrosoft Entra ID テナントに依存している場合、致命的な構成の誤り (グローバル条件付きアクセス ロックアウトなど) によって、修復に必要なスクリプトと構成ファイルへのアクセスが禁止される可能性があります。

1. ログを保持します。 インシデント対応のプロアクティブな監視とアラートを構成します。

- **監査ログの保持。** Microsoft Entra IDは、限られた期間 (通常は 30 日間) の監査ログを格納します。 効果的なフォレンジック分析と復旧のために、より長いリテンション期間を構成します。
- **診断設定とアラート**。 監査ログとサインイン ログをログ分析ワークスペース、Azure Storage アカウント、またはセキュリティ情報およびイベント管理 (SIEM) (Microsoft Sentinel など) にストリーミングするようにMicrosoft Entra IDを構成します。 イベントが発生した後、誰が何を変更したかの履歴が記録されていることを確認します。 予期される操作の範囲外にあるハード削除イベントのアラートを作成します。
- **構成の監視**。 [TCM のテナント監視 API を](https://learn.microsoft.com/ja-jp/graph/api/resources/unified-tenant-configuration-management-api-overview)使用すると、管理者は 1 つ以上のモニターを作成し、必要な構成状態からの逸脱を定期的に検出できます。 TCM モニターは、変更できない固定の 6 時間間隔で実行されます。
- **インシデント対応プレイブック**。 重要なオブジェクト (優先度の高いグループや条件付きアクセス ポリシーなど) に対するハード削除と構成の変更によって、迅速な調査と修復のために、即時の優先度の高いインシデント対応プレイブックがトリガーされるようにします。

1. 復旧計画を策定し、定期的にテストします。 技術ツールは、組織が復旧プロセスを定義してテストする場合にのみ機能します。 危機が発生する前の回復の人間の要素を定義します。

- **RTO/RPO を定義**します。 ID サービスの目標復旧時間 (RTO) と目標復旧ポイント (RPO) を確立します。 IT の取り組みを組織の要件に合わせるために、ビジネス関係者にこれらのメトリックに同意するよう依頼します。
- **通信テンプレート**。 エンドユーザーと事業主向けのドラフト作成前の通知。 ID の停止中に明確な通信を行うと、ヘルプ デスクのボリュームが減少し、技術チームは修復に集中できます。
- **定期的な復旧訓練**。 定期的に [ゲームの日の演習を](https://learn.microsoft.com/ja-jp/azure/well-architected/reliability/disaster-recovery) 実施して、復旧計画を検証します。 非運用環境のテナントを使用して、意図しない悪意のあるディレクトリの変更からの復旧をシミュレートしてテストし、運用ディレクトリのデータやMicrosoft Entraに依存するリソースに影響を与えないようにします。

1. エンドツーエンドの復旧プロセスを迅速化するには、サポートされているオブジェクトに対して組み込みの差分レポートを準備します。 API を使用してMicrosoft Entraバックアップと回復の相違レポートを事前に作成します。 [データの読み込み時間は、テナントのサイズと最近の変更によって異なります](https://learn.microsoft.com/ja-jp/entra/backup/backup-difference-report-recovery-model)。特に、初めてバックアップ用のレポートを生成する場合です。

Important

変更されたオブジェクトのうち、現在もテナントに存在するものだけが、Microsoft Entra Backup and Recovery の差分レポートに表示されます。 Microsoft Entraは、監査ログにハード削除イベントを記録します。

1. 大規模なテナントの変更を計画する場合は、Microsoft Entraバックアップ スケジュールを検討してください。 Microsoft Entraバックアップと復旧では、Microsoft定義された固定間隔でバックアップが自動的に作成されます。 スケジュールを決定するには、Microsoft Entra 管理センターまたは Microsoft Graph APIで[バックアップタイムスタンプを表示](https://learn.microsoft.com/ja-jp/entra/backup/view-available-backups)します。 復旧ポイント目標 (RPO) を満たすために、変更スケジュールで許可されている場合は、バックアップの作成直後に大規模なテナントの変更が行われるよう計画します。
2. テナントの爆発半径を減らします。

### テナントを対象とした復旧を実行

回復が必要な場合は、Microsoft Entra の機能と API を使用して、オブジェクト回復アクションを識別して実行します。 このセクションでは、テナントの変更を識別し、復旧オプションを選択するためのオブジェクト ライフサイクルの状態を決定する方法について説明します。

#### テナントの変更を特定する

復旧アクションを必要とする可能性があるテナントの変更を特定します。 次の方法を使用すると、問題が発生したときに変更を簡単に識別できるため、回復性を向上させることができます。

- **Entra Backup と Recovery の相違レポート**。 レポートを作成して、前回のバックアップ以降の重要なディレクトリ オブジェクトに対するディレクトリの変更 (追加、属性の編集、リンクの編集、論理的な削除など) を特定します。 組み込みのMicrosoft Entra回復ジョブを使用して、復元できるオブジェクトまたはプロパティを決定します。
- **文書化された既知の良好な状態を使用したポイントインタイム比較**。 変更された内容を正確に特定するには、TCM API (またはMicrosoft Graph エクスポート) からの構成スナップショットを現在のテナントの状態と比較します。 TCM では、サポートされているオブジェクト属性の異なるサブセットがサポートされているにもかかわらず、Microsoft Entra Backup and Recovery と比較して、より広範なオブジェクトの種類 (リソース) がサポートされています。
- **Microsoft Entra 監査ログ**。 組み込みの回復ツールで回復できない場合でも、Microsoft Entra テナント内[のすべてのトレース可能な](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)アクティビティ (ハード削除アクティビティを含む) を収集します。 [削除](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions) と [構成変更](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-misconfigurations) の監査ログ イベントは、復旧シナリオに関連します。
- **その他のバックアップと回復のソリューション**。 特に長いドウェルまたは遅延検出のシナリオで、何がいつ変更されたかを特定するのに役立つ、Microsoft以外のバックアップおよび回復ソリューションを評価します。 次のセクションでは、Microsoft以外のソリューションのユース ケースについて説明します。

Note

Microsoft以外のソリューションは、情報提供のみを目的としています。 Microsoftは、Microsoft以外のソリューションの信頼性、セキュリティ、適合性を保証したり保証したりすることはありません。 全体的な復旧戦略の一環として、個別に評価および検証します。

#### オブジェクトのライフサイクルの状態を確認する

Microsoft Entraの復旧モデルは、オブジェクトのライフサイクル状態 (論理的な削除、ハード削除、またはインプレース変更) によって異なります。 この違いは、回復の忠実性と使用できるオプションに直接影響します。 変更の性質 (削除、構成の誤り、追加) とそのライフサイクルの状態によって、復旧パスが決まります。

Note

Microsoft Entraバックアップと回復では、[定義されたテナント オブジェクトの種類のセットと、それらのオブジェクトで選択されたプロパティの](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations)復旧がサポートされます。 Microsoft Entra Backup and Recovery の対象外である [ユーザー認証方法の回復](https://learn.microsoft.com/ja-jp/entra/backup/recover-user-secrets) および [アプリケーション シークレットの回復](https://learn.microsoft.com/ja-jp/entra/backup/recover-applications) については、ガイダンスに従ってください。

#### ソフト削除からの復旧

いくつかのコア オブジェクトの種類の場合、Microsoft Entraは、オブジェクトが完全なプロパティ、メンバーシップ、および構成を保持する 30 日間の論理的な削除セーフティ ネットを提供します。

- **サポートされているオブジェクト**。 [Microsoft Entra IDの削除からの復旧](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions#properties-maintained-with-soft-delete)では、論理的な削除をサポートするオブジェクトについて説明します。
- **復元の忠実性**。 ごみ箱からオブジェクトを復元すると、Microsoftはグループ メンバーシップ、ロールの割り当て、サービス プリンシパル リンクを保持するため、手動による再リンクが減ります。
- **操作パス**。 ソフト削除されたオブジェクトは、Microsoft Entra Backup and Recovery、Microsoft Entra 管理センターの[削除されたオブジェクト ページ](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions)、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/directory)、または[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/restore-mgdirectorydeleteditem)を使用して復元できます。

#### 構成ミスからのリカバリ

削除とは異なり、構成の誤り (不適切な範囲の条件付きアクセス ポリシーやフェデレーション設定の変更など) は、オブジェクトをごみ箱に移動しません。 代わりに、何が変更されたかを特定し、現在の状態を既知の適切な構成と比較し、影響を受ける設定を意図的にロールバックまたは再構築します。

- **構成変更の回復**。 前回のバックアップ以降の構成の変更 (属性の編集、リンクの編集) から重要なディレクトリ オブジェクトに回復できます。 Microsoft Entra のバックアップ ジョブと復旧ジョブを使用して、サポートされているオブジェクトを以前の時点に復元します。 このオプションを使用すると、意図しない変更が発生したときに手動で再構成する必要が減ります。
- **スクリプト化された修復と再デプロイ**。 構成をバージョン管理された成果物 (GitHub や Azure DevOps の JSON ファイルなど) として格納する場合は、スクリプト化されたワークフローまたはコードとしてのインフラストラクチャワークフローを使用して、最新の既知の適切なバージョンを再デプロイして復旧します。 条件付きアクセス ポリシー、認証方法の構成、テナント間アクセス設定などの複雑なオブジェクト構成を効率的に回復するには、このオプションを使用します。
- **対象を絞った手動ロールバックまたは再構築**。 小規模な復旧の場合は、Microsoft Entra 管理センター、Microsoft Graph、または自動化スクリプトを使用して、個々の設定を手動で元に戻すか、以前の構成を再適用できます。

#### ハード削除後に再作成する

ハード削除では、最も破壊的な回復シナリオが作成されます。 ハード削除は、管理者が削除されたオブジェクトを手動で消去したとき、またはオブジェクトが論理的な削除をサポートしていない場合に発生します。 Microsoft Entraは、30 日間の論理的な削除の保持期間を超えた後、オブジェクトを完全に削除します。

ハード削除により、Microsoft Entraから完全に削除されます。 ハード削除後は、オブジェクトを復元したり、削除を取り消したりすることはできません。 回復するには、削除する前にキャプチャした既知の適切な構成からオブジェクトを再作成します。 ハード削除の再作成オプションには、次のものがあります。

- **文書化されたベースラインを使用して手動で再作成します**。 削除前にキャプチャした構成スナップショットまたはエクスポートされた定義を使用して、削除されたオブジェクトを再作成できます。 新しく作成されたオブジェクトは、アクセスの再割り当てなど、さらに修復が必要になる可能性がある新しいオブジェクト ID を受け取ります。
- **構成スナップショットとエクスポートを使用してプログラムで再作成します**。 TCM スナップショットまたはMicrosoft Graphエクスポートを使用して構成データをキャプチャした後、スクリプトまたは自動化されたワークフローを使用してディレクトリ オブジェクトを再作成できます。
- **依存する構成を再リンクします**。 オブジェクト識別子は再作成後に変更されるため、元のオブジェクト ID を参照していた依存関係を再確立します。 たとえば、ハード削除されたグループを再作成するには、グループ メンバーの再割り当てが必要です。 その後、以前に元のオブジェクトを対象とした条件付きアクセス ポリシー、アプリケーションの割り当て、ロールの割り当て、またはアクセス パッケージにグループを再リンクします。

#### パートナー主導のバックアップと回復ソリューション

より多くのディレクトリ オブジェクトのサポートと機能が必要な場合は、Microsoft Entra IDなどのMicrosoftクラウド ワークロード用のMicrosoft以外のバックアップおよび回復ソリューションを検討してください。

Microsoft以外のソリューションでは、組み込みのMicrosoft Entra機能を使用して、変更の識別、復旧ワークフローの調整、サポートされているオブジェクトとプロパティの復元を行うことができます。 機能としては、バックアップおよび回復用 API、ソフト削除、Microsoft Entra の監査ログ、Microsoft Graph API が含まれます。 これらのソリューションは、組み込みの回復機能を補完します。 Microsoft以外のソリューションでは、プラットフォームでサポートされる復旧シナリオ、オブジェクトの種類、およびプロパティ レベルのサポートの範囲内で、Microsoft Entra回復機能を提供できます。 機能の例:

- ディレクトリ オブジェクトと属性は、組み込みのMicrosoft Entra回復機能でサポートされるディレクトリ オブジェクトと属性のサブセットを超えてサポートされます。
- 詳細な復元を使用すると、オブジェクトの残りの部分に影響を与えることなく、特定の属性を復元できます。 たとえば、ユーザーの部署または特定の条件付きアクセス (CA) ポリシー条件のみを復元できます。
- ハード削除されたオブジェクトを再作成し、場合によっては、ディレクトリ オブジェクト間のリンクとリレーションシップを再作成します。
- オンプレミス Active Directory Domain Services (AD DS) オブジェクトと Microsoft Entra ディレクトリ オブジェクトの両方をバックアップおよび復旧します。
- バックアップを外部に保存し、Microsoft Entra に組み込まれている[ソフト削除](https://learn.microsoft.com/ja-jp/entra/backup/soft-deletion)および[バックアップと復旧](https://learn.microsoft.com/ja-jp/entra/backup/overview)の現在のデータ保持期間を超えて保持します。

### 主要な障害シナリオと回復オプション

次の大まかな手順を使用して、主要な障害シナリオから復旧します。 この記事の「テナント スコープの復旧の準備 」セクションを参照してください。 テナント単位の復旧シナリオの場合:

1. 適切な [インシデント対応プロセス](https://learn.microsoft.com/ja-jp/security/operations/incident-response-overview)に従います。
2. アカウントを復元します。
3. アプリケーションとその他のディレクトリ オブジェクトを回復します。
4. CA ポリシーを再度有効にします。
5. BCDR プランの一部として、既知の適切な状態を使用してセキュリティ設定を再構築します。

#### 顧客Microsoft Entra IDテナントの破損またはデータ損失から復旧する

偶発的または悪意のあるアクティビティによるディレクトリ データの破損または削除により、ビジネスが中断されます。 テナントは、顧客が引き続きアクセスできます (たとえば、Microsoft Entraブレーク グラス アカウントを使用する場合など)。

Microsoft Entra の復旧機能、構成ベースライン、運用ランブックを使用することで、このシナリオから復旧できます。

インシデント対応中に、復旧を必要とするインシデントの範囲と影響を特定します。 バックアップと回復の相違レポートMicrosoft Entra、Microsoft Graph API を使用した構成エクスポート、監査ログのMicrosoft Entra、一部のMicrosoft以外のソリューションは、回復オプションの選択に役立ちます。

回復が必要なオブジェクトと設定を特定したら、変更の性質と影響を受けるオブジェクトの状態に基づいて回復アクションを選択します。

- Microsoft Entraバックアップと復旧では、[バックアップ保有期間内](https://learn.microsoft.com/ja-jp/entra/backup/overview)に含まれるサポートされているオブジェクトと属性に対して、最も効率的な復旧パスが提供されます。 オブジェクトがまだテナントに存在する場合 (ハード削除されていない場合) に、復旧シナリオ用の 差分レポート を事前に作成します。
- TCM API を使用して、文書化された既知の良好な構成状態を確立し、構成スナップショットをキャプチャします。 サポートされていないリソース向けの Microsoft Graph からのエクスポートでそれらを補完します。 回復シナリオ中に既知の良好な状態を使用して、選択したテナント設定、ポリシー、または属性を組み込みの復元機能の外部で手動またはプログラムで再適用します。 既知の適切なバージョンに構成を選択的に復元するには、スナップショットを自動化ワークフロー (スクリプト化されたMicrosoft Graph更新プログラムやパイプライン駆動型デプロイなど) に統合します。
- Microsoft Graph API (直接または非Microsoft ソリューションを使用) を使用して、組み込みの機能を超えて、オブジェクトの種類と属性に回復カバレッジを拡張します。 方法と実装に応じて、ハード削除されたオブジェクトの再作成や、組み込みのバックアップ保有期間がカバーされなくなったポイントへのオブジェクトの回復などのシナリオに対処します。

オブジェクトを再作成するときは、新しいオブジェクト識別子が必要です。 依存関係 (グループ メンバーシップ、アクセスの割り当て、条件付きアクセスのターゲット設定など) を再確立することを計画します。 これらの手順は、Microsoft以外のバックアップおよび回復ソリューションを使用して自動化できます。

#### 顧客Microsoft Entra IDテナント ロックアウトから復旧する

ランサムウェア、条件付きアクセスによるロックアウト、管理者によるすべてのユーザーの完全削除、アクセスできない Microsoft Entra ブレークグラス アカウントによって、組織が Microsoft Entra テナントにアクセスできなくなる可能性があります。

テナントがロックアウトされた場合は、Microsoft サポートにサポート ケースを作成してください。 ポータルにアクセスできない場合は、 [カスタマー サービスの電話番号](https://support.microsoft.com/topic/customer-service-phone-numbers-c0389ade-5640-e588-8b0e-28de8afeb3f2) を呼び出すことができます。 Microsoftは、テナントの所有権を検証するための高保証 ID 検証プロセスに従います。 Microsoftは、指定されたグローバル管理者ユーザーがテナント アクセスを回復するのに役立ちます。 Microsoftは新しいテナントを発行しません。 Microsoftが所有権を確認した後、Microsoftは既存のテナントへのアクセスを回復します。

#### Microsoft Entra ID サービスの停止からの復旧 (テナント固有ではない)

Microsoft Entra IDを使用できなくなるサービスレイヤーの停止は、複数の顧客に影響を与える可能性があります。 この記事のMicrosoft Entra回復性に関するセクションを参照してください。

Microsoftは、重大な障害が発生した場合でも、大規模な停止を防ぐために、サービス層の冗長性を備えたMicrosoft Entraを運用します。 サービス レベルの中断が発生した場合、Microsoftの設計と運用のプラクティスでは、顧客への影響を最小限に抑えるために、可能な限り迅速にサービスを復元することに重点を置きます。

一部のMicrosoftのお客様は、特定のシナリオで重要なワークロードの回復性を向上させるために ID インフラストラクチャ ソリューションを追加します。 これらのソリューションでは、運用上の大きなオーバーヘッドと複雑さが生じます。 これには、高いバーの依存関係 (アプリやフェデレーション インフラストラクチャと共に物理的に配置されているユーザーなど) が含まれる場合があります。

### 運用上のセキュリティ ガードレールを使用してテナントを保護する

爆発半径を減らすと、テナントの侵害や構成ミスが環境に影響を与える可能性のある量が制限されます。 Microsoftは、テナント境界の整合性を適用します。 さらに特権を制限し、テナント内の爆発半径を減らすには、多層防御制御を適用します。 単一障害点がシステム全体に及ぶ ID 基盤の危機につながらないように、ゼロ トラスト の*侵害を前提とする*考え方を採用します。

#### テナント内での影響範囲を縮小する

回復イベントの防止は、1 つを準備するのと同じくらい重要です。 テナントの侵害リスクを最小限に抑え、Microsoft Entra テナントの爆発半径を減らす管理コントロールを実装するための階層化されたアプローチを採用します。 少なくとも、次のコントロールを含めます。

- **Privileged Identity Management (PIM)**。 すべての高い特権ロールに対して JIT 昇格を適用します。 永続的な削除が可能なロール (グローバル管理者やユーザー管理者など) に対して MFA と管理者の承認を要求するように PIM ポリシーを構成します。
- **管理単位 (AU)**。 AU を使用して、部門または地理的境界に基づいてアクセス許可を委任します。 この方法では、ある部署の管理者が別の部署のオブジェクトを誤って削除しないようにすることで、爆発半径が制限されます。
- **制限付き管理管理単位 (RMAU)**。 階層 0 の ID (緊急アクセス アカウントなど) を識別します。 削除を防ぐには、RMAU に追加し、追加の監視レイヤーを適用します。
- **条件付きアクセス (CA) で保護されたアクション**。 永続的な削除や CA ポリシーの作成、更新、削除など、ユーザー ロールに依存しない影響の大きいアクセス許可を保護するには、保護されたアクションを使用します。
- **緊急アクセスアカウント**。 管理アクセスまたはテナント ロックアウトの誤った不足を軽減するには、各Microsoft Entra テナントに 2 つ以上の緊急アクセス アカウントを作成します。 これらのアカウントの偶発的または悪意のある削除、構成の誤り、または使用のリスクを軽減するには、RMAU を使用して保護します。

#### 複数のテナントで重要なワークロードを分離する

価値の高い、またはミッション クリティカルなアプリケーションとリソースをホストする専用テナントを維持し、より広範なエンタープライズ環境から分離することができます。 リスク評価を使用して、このアーキテクチャの選択が適切かどうかを判断します。 1 つのテナント内で使用可能なすべてのセキュリティ制御とベスト プラクティスを適用した後でも、メイン ワークフォース テナントのセキュリティ インシデントまたは運用エラーによって、特定の重要なワークロードが中断される可能性があります。

より広範な環境でインシデントが発生したときに最も機密性の高いシステムを保護するには、重要なワークロードを専用テナントに分離します。 専用テナントでは、より厳密なセキュリティ制御とガバナンスが適用されるため、組織は、最適なセキュリティ体制であっても、重要な資産をプライマリ テナントの爆発半径に公開する残りのリスクを受け入れる必要はありません。

### まとめ

Microsoft Entraは、サービス レイヤーでの冗長性、分離、自動化された軽減策を備えたクラウド ネイティブのサービス レベルの回復性を通じて高可用性を提供します。 組織とMicrosoftがテナント レベルの回復性に対する責任を共有していることを認識しながら、これらの機能を ID の可用性の基礎として扱います。 誤った削除、構成の誤り、悪意のある変更を行うには、回復の準備を積極的に行う必要があります。

- 既知の適切な構成状態を文書化します。
- 監査データを保持します。
- ビジネス回復の目標に合った明確な運用 Runbook を確立します。

回復性と回復性の結果を向上させるには、階層化された復旧アプローチを採用します。

- 外部のバージョン管理された既知の正常な状態を維持します。 階層化されたキャプチャ アプローチを採用する。 必要なスコープと忠実性に基づいて、使用可能なメカニズムから選択します。 このベースラインは、特にハード削除されたオブジェクトを識別し、レクリエーションを容易にするために、比較、再適用、およびレクリエーションに不可欠です。
- Microsoft Entraバックアップと回復は、組み込みのバックアップ保有期間内で[サポートされているオブジェクトと回復可能なプロパティ](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations)のプライマリ復旧メカニズムとして使用します。 これは、高い忠実性と低労力の復元パスを提供します。 TCM API を使用して構成スナップショットをキャプチャし、Microsoft Graph API 呼び出しを指示して、さらに回復オプションを指定します。 これらのオプションを組み合わせると、Microsoft Graph経由でアクセスできるディレクトリ オブジェクトにカバレッジが拡張されます。
- 組み込みカバレッジ外のシナリオ (ハード削除後の長期保有やオブジェクトの再作成など) の場合は、Microsoft以外のバックアップおよび回復ソリューションを使用します。 組み込みの機能が終了する場所で回復性を拡張するパートナー ソリューションを使用して、Microsoftプラットフォームの機能を補完します。 検証、依存関係の再リンク、復旧 Runbook の実行は引き続き担当します。

回復可能性は、スタンドアロンの技術的な機能ではなく、より広範な ID 回復性戦略の一部として扱います。 最小限の特権管理、特権アクセス管理、高リスクの変更監視、重大なワークロードの分離などの予防ガードレールにより、爆発半径と回復の複雑さが大幅に軽減されます。 ID 関連のインシデントに耐え、中断を最小限に抑えて信頼されたアクセスを復元するには、Microsoft Entraのサービス レベルの回復性と、規範的なテナント ガバナンス、テスト済みの復旧手順、アーキテクチャの分離を組み合わせます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-app-development-overview"} -->
## 開発する認証と認可のアプリケーションの回復性を向上させる - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview
- Service: entra / architecture
- Article date: 2023-03-02
- Summary: Microsoft Entra ID と Microsoft ID プラットフォームを使用したアプリケーションの開発に関する回復性ガイダンス

[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)は、ユーザーと顧客が自分の Microsoft ID またはソーシャル アカウントを使用してサインインできるアプリケーションを構築するのに役立ちます。 Microsoft ID プラットフォームでは、トークンベースの認証と承認フローを使用してアプリケーションと通信します。 クライアント アプリケーションは、ID プロバイダー (IdP)、Microsoft Entra、Azure AD B2C からトークンを取得して、ユーザーを認証し、保護された API を呼び出すアプリケーションを承認します。 サービスはトークンを検証します。 詳細については、「 [セキュリティ トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens)」を参照してください。

トークンは一定の期間有効であり、その後アプリは新しいものを取得する必要があります。 まれに、ネットワークやインフラストラクチャの問題、または認証サービスの停止が原因で、トークンを取得するための呼び出しが失敗します。 [バックアップ認証システム](https://learn.microsoft.com/ja-jp/entra/architecture/backup-authentication-system)では、障害が発生した場合に認証の回復性が向上します。 このシステムは、主要な Microsoft Entra サービスが利用不可であるか、機能が低下した場合に、サポートされているアプリケーションとサービスの認証を透明的かつ自動的に処理します。

次の記事では、サインインしているユーザーとデーモン アプリケーションに関する、クライアントとサービスのアプリケーションのためのガイダンスを示します。 また、トークンの使用と、リソースの呼び出しに関するベスト プラクティスも含まれます。

- [開発するクライアント アプリケーションで認証と認可の回復性を向上させる](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-client-app)
- [開発するデーモン アプリケーションで認証と認可の回復性を向上させる](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-daemon-app)
- [ID およびアクセス管理インフラストラクチャで回復性を強化する](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-infrastructure)
- [Azure AD B2C を使用して、顧客 ID およびアクセス管理で回復性を強化する](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-b2c)
- [バックアップ認証システムのアプリケーション サポートの構築](https://learn.microsoft.com/ja-jp/entra/architecture/backup-authentication-system-apps)
- [Microsoft Entra ID の OpenID Connect メタデータの更新に対して回復性のあるサービスを構築する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-build-services-resilient-to-metadata-refresh)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-b2b-authentication"} -->
## Microsoft Entra ID を使って外部ユーザー認証の回復性を強化する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-b2b-authentication
- Service: entra / architecture
- Article date: 2022-11-16
- Summary: IT 管理者とアーキテクトが外部ユーザーに対する回復力のある認証を構築するためのガイド

[Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) (Microsoft Entra B2B) は、他の組織や個人との共同作業を可能にする[外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure) の機能です。 これにより、資格情報を管理することなく、ゲスト ユーザーを Microsoft Entra テナントに安全にオンボードすることができます。 外部ユーザーは、外部 ID プロバイダー (IdP) から ID と資格情報を取得するため、新しい資格情報を覚える必要がありません。

### 外部ユーザーを認証する方法

お使いのディレクトリに対する外部ユーザー認証の方法を選択できます。 Microsoft IdP または他の IdP を使用できます。

どの外部 IdP を使用しても、その IdP の可用性に依存することになります。 IdP に接続するいくつかの方法を使用して、回復性を向上させるためにできることがあります。

注

Microsoft Entra B2B には、任意の [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/) テナントから、または個人用 [Microsoft アカウント](https://account.microsoft.com/account)を使って、任意のユーザーを認証する組み込みの機能があります。 これらの組み込みオプションを使用して構成を行う必要はありません。

#### 他の IdP を使用した回復性に関する考慮事項

ゲスト ユーザーの認証に外部 IdP を使用する場合、中断を防ぐために管理する必要がある構成があります。

| 認証方法 | 回復性に関する考慮事項 |
| --- | --- |
| [Facebook](https://learn.microsoft.com/ja-jp/entra/external-id/facebook-federation) や [Google](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation) などのソーシャル IDP とのフェデレーション。 | その IdP でアカウントを管理し、クライアント ID とクライアント シークレットを構成する必要があります。 |
| [SAML/WS-Fed ID プロバイダー (IdP) フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation) | 自分が依存している IdP 所有者のエンドポイントにアクセスするために、その所有者と連携する必要があります。 証明書とエンドポイントを含むメタデータを管理する必要があります。 |
| [ワンタイム パスコードの電子メール送信](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode) | Microsoft の電子メール システム、ユーザーの電子メール システム、およびユーザーの電子メール クライアントに依存します。 |

### セルフサービス サインアップ

招待またはリンクを送信する代わりに、[セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)を有効にすることができます。 この方法により、外部ユーザーがアプリケーションへのアクセスをリクエストできるようになります。 [API コネクタ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-api-connector)を作成し、それをユーザー フローに関連付ける必要があります。 ユーザー エクスペリエンスを定義するユーザー フローを 1 つ以上のアプリケーションに関連付けます。

[API コネクタ](https://learn.microsoft.com/ja-jp/entra/external-id/api-connectors-overview)を使用して、セルフサービス サインアップ ユーザー フローを外部システムの API と統合することができます。 この API 統合は、[カスタム承認ワークフロー](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-approvals)、[本人確認の実行](https://learn.microsoft.com/ja-jp/entra/external-id/code-samples-self-service-sign-up)、およびユーザー属性の上書きなどのその他のタスクに使用できます。 API を使用するには、次の依存関係を管理する必要があります。

- **API コネクタの認証**:コネクタの設定には、エンドポイント URL、ユーザー名、パスワードが必要です。 これらの資格情報が管理されるプロセスを設定し、API 所有者と協力して、有効期限のスケジュールについて確実に把握するようにします。
- **API コネクタの応答**:API が使用できない場合に正常に失敗するように、サインアップ フロー内に API コネクタを設計します。 これらの[API 応答の例](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-api-connector)と[トラブルシューティングのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-api-connector)を調べて、API 開発者に提供します。 API 開発チームと協力して、継続、検証エラー、ブロックの各応答を含む、考えられるすべての応答シナリオをテストします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-b2c"} -->
## Azure AD B2C を使用して、顧客の ID とアクセスの管理で回復性を強化する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-b2c
- Service: entra / architecture
- Article date: 2025-05-20
- Summary: Azure AD B2C を使用して顧客の ID とアクセスの管理 (CIAM) で回復性を強化する方法について説明します。

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

[Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview) は、顧客向けの重要なアプリケーションを起動できるように設計された顧客の ID とアクセスの管理 (CIAM) プラットフォームです。 お客様のニーズに合わせてサービスをスケーリングし、潜在的停止状況に直面したときに回復性を向上させる、[回復性](https://azure.microsoft.com/blog/advancing-azure-active-directory-availability/)のための組み込み機能を備えています。 さらに、ミッション クリティカルなアプリケーションを作り始めるときは、アプリケーションのさまざまな設計と構成の要素について検討することが重要です。 Azure AD B2C 内でアプリケーションがどのように構成されているか検討し、停止や障害のシナリオに対応する回復性のある動作を確認できるようにします。 この記事では、回復性の向上に役立ついくつかのベスト プラクティスについて説明します。

回復性があるサービスとは、中断が発生しても機能し続けるということです。 回復性を向上させるには、次のことを行います。

- すべてのコンポーネントを理解する
- 単一障害点を排除する
- 障害のあるコンポーネントを分離して影響を限定する
- 高速フェールオーバー メカニズムと復旧パスを使用して冗長性を提供する

アプリケーションを開発する際は、ソリューションの ID コンポーネントを使用して、[アプリケーションで認証と認可の回復性を向上させる](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview)方法を検討することをお勧めします。 この記事では、Azure AD B2C アプリケーションの回復性の強化について説明します。 推奨事項を CIAM の機能別にグループ化しています。

以降のセクションでは、次の領域での回復性の強化について説明します。

- [エンドユーザー エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/architecture/resilient-end-user-experience): 認証フローのフォールバック計画を有効にし、Azure AD B2C 認証サービスの中断による潜在的な影響を軽減します。
- [外部プロセスとのインターフェイス](https://learn.microsoft.com/ja-jp/entra/architecture/resilient-external-processes): エラーから復旧することによって、アプリケーションとインターフェイスでの回復性を強化します。
- [開発者のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-b2c-developer-best-practices): 一般的なカスタム ポリシー問題が原因の脆弱性を回避し、要求検証ツール、サードパーティ製アプリケーション、REST API の操作などの領域でのエラー処理を改善します。
- [監視と分析](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-with-monitoring-alerting): 重要な指標を監視してサービスの正常性を評価し、アラートを使用して障害とパフォーマンスの中断を検出します。
- [認証インフラストラクチャの回復性を構築する](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-infrastructure): リソースの認証や承認が中断されるリスクを理解し、封じ込め、軽減します。
- [アプリケーションでの認証と承認の回復性を向上させる](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview): Microsoft ID プラットフォームを使用して、ユーザーと顧客が Microsoft ID またはソーシャル アカウントでサインインするアプリを構築します。

[回復性と拡張性に優れたフローの構築](https://www.youtube.com/embed/8f_Ozpw9yTs)に関するビデオをご覧ください。 Azure AD B2C を使用して回復性と拡張性に優れたサービスを設計して構成する方法について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-b2c-developer-best-practices"} -->
## Azure AD B2C を使用した開発者ベスト プラクティスによるレジリエンス向上 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-b2c-developer-best-practices
- Service: entra / architecture
- Article date: 2025-05-20
- Summary: Azure AD B2C を使用した顧客 ID およびアクセス管理での開発者のベスト プラクティスによる回復性

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

この記事では、私たちが大規模な顧客との作業経験から得たメリットを紹介します。 サービスの設計と実装において、次の推奨事項を検討してください。

### Microsoft Authentication Library

[Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) (MSAL) と [ASP.NET 用 Microsoft ID Web 認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用すると、アプリケーションのトークンの取得、管理、キャッシュ、更新が簡素化されます。 これらのライブラリは、アプリケーションの回復性を向上させる機能など、Microsoft ID をサポートするように最適化されています。

開発者は、MSAL の最新リリースを導入し、最新の状態を維持できます。 アプリケーションで[認証と認可の回復性を向上させる方法](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview)に関する記事を参照してください。 可能な限り、独自の認証スタックを実装しないでください。 代わりに、確立されたライブラリを使用してください。

### ディレクトリの読み取りと書き込みを最適化する

Azure AD B2C ディレクトリ サービスは、1 秒あたりの読み取り率が高く、1 日に数十億件の認証をサポートします。 書き込みを最適化して、依存関係を最小限に抑え、回復性を向上させます。

#### ディレクトリの読み取りと書き込みを最適化する方法

- **サインイン時にディレクトリへの書き込み関数を使用しない**: カスタム ポリシーの中で、前提条件 (if 句) を指定せずに、サインイン時に書き込みを実行しないようにします。 サインイン時に書き込みを必要とするユース ケースの 1 つとして、[ユーザー パスワードの Just-In-Time 移行](https://github.com/azure-ad-b2c/user-migration/tree/master/seamless-account-migration)があります。 サインインのたびに書き込みをする必要はありません。 ユーザー体験での [Preconditions](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/userjourneys) は次のとおりです。

    ```xml
    <Precondition Type="ClaimEquals" ExecuteActionsIf="true"> 
    <Value>requiresMigration</Value>
    ...
    <Precondition/>
    ```
- **調整について**: ディレクトリには、アプリケーションおよびテナントのレベルで調整規則が実装されています。 読み取り/GET、書き込み/POST、更新/PUT、削除/DELETE の操作には、より多くのレート制限があります。 各操作には異なる制限があります。

    - サインイン中の書き込みは、新しいユーザーの場合は POST、現在のユーザーの場合は PUT に該当します。
    - サインインのたびにユーザーを作成または更新するカスタム ポリシーは、アプリケーション レベルの PUT または POST のレート制限に達する場合があります。 Microsoft Entra ID または Microsoft Graph を使用してディレクトリ オブジェクトを更新する場合にも同じ制限が適用されます。 同様に、読み取りを調べて、毎回のサインインでの読み取り回数を最小限に抑えます。
    - ピーク時の負荷を推定して、ディレクトリの書き込みレートを予測し、調整を回避します。 ピーク時のトラフィックの見積もりには、サインアップ、サインイン、多要素認証 (MFA) などのアクションの見積もりを含める必要があります。 Azure AD B2C システムとアプリケーションで、ピーク時のトラフィックをテストします。 Azure AD B2C は、ダウンストリーム アプリケーションまたはサービスが処理しない場合に、調整なしで負荷を処理できます。
    - 移行タイムラインを理解して、計画します。 Microsoft Graph を使用して Azure AD B2C にユーザーを移行する計画を立てる場合は、アプリケーションとテナントの制限を考慮して、ユーザーの移行を完了するまでの時間を計算します。 2 つのアプリケーションを使用してユーザー作成ジョブまたはスクリプトを分割する場合は、アプリケーションごとの制限を使用できます。 必ずテナントごとのしきい値を下回るようにします。
    - 他のアプリケーションに対する移行ジョブの影響を理解します。 他の依存アプリケーションによって提供されるライブ トラフィックを考慮して、テナント レベルでの調整やライブ アプリケーションのリソース不足が発生しないようにします。 詳しくは、「[Microsoft Graph 調整ガイド](https://learn.microsoft.com/ja-jp/graph/throttling)」をご覧ください。
    - [ロード テストのサンプル](https://github.com/azure-ad-b2c/load-tests)を使用して、サインアップとサインインをシミュレートします。
    - 詳しくは、「[Azure AD B2C サービスの上限と制約](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/service-limits?pivots=b2c-custom-policy)」を参照してください。

### トークンの有効期間

Azure AD B2C 認証サービスが新しいサインアップとサインインを完了できない場合は、サインインしているユーザーに軽減策を提供します。 [構成](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/configure-tokens)により、サインインしているユーザーは、アプリケーションからサインアウトする、または非アクティブ状態により[セッション](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/session-behavior) タイム アウトするまで、中断なしでアプリケーションを使用できます。

ビジネス要件とエンド ユーザー エクスペリエンスによって、Web およびシングルページ アプリケーション (SPA) のトークン更新頻度が決まります。

#### トークンの有効期間を延長する

- **Web アプリケーション**: サインイン時に認証トークンが検証される Web アプリケーションの場合、そのアプリケーションはセッション Cookie に依存してセッションの有効性を延長し続けます。 ユーザー アクティビティに基づいて更新するローリング セッション時間を実装することで、ユーザーがサインインを維持できるようにします。 長期的なトークン発行の停止が発生した場合、これらのセッション時間は、アプリケーション上での 1 回限りの構成として延長できます。 セッションの有効期間は、許可されている最大値に維持します。
- **シングルページ アプリケーション**（SPA）: SPAがAPIを呼び出す際にアクセス トークンに依存することがあります。 SPA の場合は、認証コード フローと [Proof Key for Code Exchange (PKCE)](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas) フローをオプションとして使用し、ユーザーがアプリケーションを引き続き使用できるようにすることをお勧めします。 SPA で暗黙的フローを使用している場合は、PKCE を使用した認証コード フローに移行することを検討してください。 アプリケーションを MSAL.js 1.x から MSAL.js 2.x に移行して、Web アプリケーションの回復性を実現します。 暗黙的フローでは、更新トークンは生成されません。 ブラウザーに Azure AD B2C とのアクティブなセッションがある場合、SPA は非表示の `iframe`を使用して、承認エンドポイントに対して新しいトークン要求を実行できます。 SPA の場合、ユーザーがアプリケーションを引き続き使用できるようにするためのオプションがいくつかあります。
    - アクセス トークンの有効期間を延長します。
    - API ゲートウェイを認証プロキシとして使用するようにアプリケーションを構築します。 この構成では、SPA は認証なしで読み込み、API 呼び出しは API ゲートウェイに対して行われます。 API ゲートウェイは、ポリシーに基づく[認証コード許可](https://oauth.net/2/grant-types/authorization-code/)を使用して、サインイン プロセスを通じてユーザーを送信し、ユーザーを認証します。 API ゲートウェイとクライアント間の認証セッションは、認証 Cookie を使用して維持されます。 API ゲートウェイは、API ゲートウェイによって取得されたトークン、または別の直接認証方法 (証明書、クライアント資格情報、API キーなど) を使用して、API にサービスを提供します。
    - 推奨されるオプションに切り替えます。 Proof Key for Code Exchange (PKCE) とクロスオリジン リソース共有 (CORS) のサポートを使用して、SPA を暗黙的な許可から[認証コード許可のフロー](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/implicit-flow-single-page-application)に移行します。
    - モバイル アプリケーションの場合は、更新およびアクセス トークンの有効期間を延長します。
- **バックエンドまたはマイクロサービス アプリケーション**: バックエンド (デーモン) アプリケーションは非対話型であり、ユーザー コンテキスト内にないため、トークン盗難の可能性は減少します。 セキュリティと有効期間との間のバランスを取り、トークンの有効期間を長く設定することをお勧めします。

### シングル サインオン

[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) (SSO) を使用すると、ユーザーはアカウントを使用して 1 回サインインし、プラットフォームまたはドメイン名に関係なく、Web、モバイル、シングル ページ アプリケーション (SPA) のアプリケーションにアクセスできます。 ユーザーがアプリケーションにサインインすると、Azure AD B2C によって [Cookie ベースのセッション](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/session-behavior)が保持されます。

それ以降の認証要求では、Azure AD B2C によって Cookie ベースのセッションが読み取られて検証され、ユーザーにサインインを求めずにアクセス トークンが発行されます。 SSO がポリシーまたはアプリケーションで、制限されたスコープを使用して構成されている場合、後で他のポリシーやアプリケーションにアクセスするには、新しい認証が必要です。

#### SSO の構成

テナント内の複数のアプリケーションおよびユーザー フローで同じユーザー セッションを共有できるように、テナント全体 (既定) を対象とするように [SSO を構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start)します。 テナント全体の構成では、新しい認証に対して最も高い回復性が提供されます。

### 安全なデプロイ プラクティス

最も一般的なサービス中断は、コードと構成の変更です。 継続的インテグレーションと継続的デリバリー (CICD) のプロセスとツールを導入することで、大規模なデプロイが可能になり、テストおよびデプロイ中の人的エラーが減少します。 エラーの削減、効率、および整合性のために CI/CD を導入します。 [Azure Pipelines](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/apps/cd/azure/cicd-data-overview) は、CI/CD の 1 つの例です。

### ボットからの保護

分散型サービス拒否 (DDoS) 攻撃、SQL インジェクション、クロスサイト スクリプティング、リモート コード実行、「[OWASP Top-10](https://owasp.org/www-project-top-ten/)」に記載されているその他のものなど、既知の脆弱性からアプリケーションを保護します。 Web アプリケーション ファイアウォール (WAF) をデプロイして、一般的な悪用と脆弱性から防御します。

- Azure [WAF](https://learn.microsoft.com/ja-jp/azure/web-application-firewall/overview) の使用により、攻撃に対する一元的な保護を提供できます。
- Azure AD B2C を使用している場合は、WAF を Microsoft Entra [Identity Protection および条件付きアクセスと共に使用して、多層保護を提供](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/conditional-access-identity-protection-overview)します。
- [CAPTCHA システムとの統合](https://github.com/azure-ad-b2c/samples/tree/master/policies/captcha-integration)により、ボットによるサインアップ試行に対する抵抗力を構築します。

### シークレット

Azure AD B2C では、アプリケーション、API、ポリシー、および暗号化にシークレットが使用されます。 シークレットにより、認証、外部の相互作用、およびストレージがセキュリティで保護されます。 National Institute of Standards and Technology (NIST) は、正当なエンティティによって使用されるキー認可の期間を、*cryptoperiod* と呼んでいます。 必要な長さの [cryptoperiod](https://csrc.nist.gov/publications/detail/sp/800-57-part-1/rev-5/final) を選択します。 有効期限を設定し、有効期限が切れる前にシークレットをローテーションします。

#### シークレットのローテーションを実装する

- サポートされているリソースに対して[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用して、Microsoft Entra 認証がサポートされているサービスに対して認証を行います。 マネージド ID を使用すると、資格情報のローテーションを含め、リソースの管理を自動的に行うことができます。
- Azure AD B2C 内で[構成されているキーと証明書](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/policy-keys-overview)のインベントリを取得します。 この一覧には、カスタム ポリシーの中で使用されるキー、[API](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/secure-rest-api)、署名 ID トークン、Security Assertion Markup Language (SAML) の証明書を含めることができます。
- CICD を使用して、予想されるピーク シーズンから 2 か月以内に期限が切れるシークレットをローテーションします。 証明書に関連付けられている秘密キーの推奨される最大暗号化期間は 1 年です。
- パスワードや証明書などの API アクセス資格情報を監視してローテーションします。

### REST API のテスト

回復性のために、REST API のテストには、HTTP コード、応答ペイロード、ヘッダー、パフォーマンスの確認を含める必要があります。 正常系のテストのみを実行するのではなく、問題のあるシナリオを API が適切に処理することを確認してください。

#### テスト計画

テスト計画には、[包括的な API テスト](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/best-practices#testing)を含めることをお勧めします。 キャンペーンまたは休日によるトラフィック急増の場合は、新しい見積もりでロード テストを改訂します。 運用内ではなく、開発環境内で API ロード テストとコンテンツ配信ネットワーク (CDN) を実施します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-client-app"} -->
## 開発するクライアント アプリケーションで認証と認可の回復性を向上させる - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-client-app
- Service: entra / architecture
- Article date: 2023-03-02
- Summary: Microsoft ID プラットフォームを使用してクライアント アプリケーションで認証と認可の回復性を向上させる方法を学習します

Microsoft ID プラットフォームと Microsoft Entra ID を使用してユーザーのサインインを行い、それらのユーザーに代わってアクションを実行するクライアント アプリケーションで回復性を強化する方法を学習します。

### Microsoft Authentication Library (MSAL) を使用する

Microsoft Authentication Library (MSAL) は、Microsoft ID プラットフォームの一部です。 MSAL ではトークンを取得、管理、キャッシュ、および更新します。回復性のベスト プラクティスを使用します。 MSAL は、開発者がセキュリティで保護されたソリューションを作成するのに役立ちます。

詳細情報:

- [Microsoft Authentication Library の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview)
- [Microsoft ID プラットフォームとは](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)
- [Microsoft ID プラットフォームのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/)

MSAL では、トークンがキャッシュされ、サイレント トークン取得パターンが使用されます。 MSAL では、ユニバーサル Windows プラットフォーム (UWP)、iOS、Android などのセキュリティで保護されたストレージをネイティブに提供するオペレーティング システムでトークン キャッシュをシリアル化します。 以下を使用している場合はシリアル化の動作をカスタマイズします。

- Microsoft.Identity.Web
- MSAL.NET
- MSAL for Java
- MSAL for Python

詳細情報:

- [トークン キャッシュのシリアル化](https://github.com/AzureAD/microsoft-identity-web/wiki/token-cache-serialization)
- [MSAL.NET でのトークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization)
- [MSAL for Java でのカスタム トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-java-token-cache-serialization)
- [MSAL for Python でのカスタム トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-python-token-cache-serialization)。

    [Image: MSAL を使用して Microsoft ID を呼び出すデバイスとアプリケーションの画像]

MSAL を使用する場合、トークンのキャッシュ、更新、およびサイレント取得がサポートされます。 単純なパターンを使用して、認証用のトークンを取得します。 多くの言語がサポートされています。 [Microsoft ID プラットフォーム コード サンプル](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code)でコード サンプルを検索します。

## [C#](#tab/csharp)
```csharp
try
{
    result = await app.AcquireTokenSilent(scopes, account).ExecuteAsync();
}
catch(MsalUiRequiredException ex)
{
    result = await app.AcquireToken(scopes).WithClaims(ex.Claims).ExecuteAsync()
}
```

## [JavaScript](#tab/javascript)
```javascript
return myMSALObj.acquireTokenSilent(request).catch(error => {
    console.warn("silent token acquisition fails. acquiring token using redirect");
    if (error instanceof msal.InteractionRequiredAuthError) {
        // fallback to interaction when silent call fails
        return myMSALObj.acquireTokenPopup(request).then(tokenResponse => {
            console.log(tokenResponse);

            return tokenResponse;
        }).catch(error => {
            console.error(error);
        });
    } else {
        console.warn(error);
    }
});
```

---

MSAL ではトークンを更新できます。 Microsoft ID プラットフォームは、有効期間が長いトークンを発行するときに、トークンを更新するための情報をクライアントに送信できます (refresh\_in)。 古いトークンが有効な間はアプリが実行されますが、別のトークンの取得には時間がかかります。

#### MSAL リリース

認証はアプリ セキュリティの一部であるため、開発者は最新の MSAL リリースを使用するプロセスを構築することをお勧めします。 このプラクティスは、開発中のライブラリに使用し、アプリの回復性を向上させます。

最新バージョンとリリース ノートを検索します。

- [`microsoft-authentication-library-for-js`](https://github.com/AzureAD/microsoft-authentication-library-for-js/releases)
- [`microsoft-authentication-library-for-dotnet`](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases)
- [`microsoft-authentication-library-for-python`](https://github.com/AzureAD/microsoft-authentication-library-for-python/releases)
- [`microsoft-authentication-library-for-java`](https://github.com/AzureAD/microsoft-authentication-library-for-java/releases)
- [`microsoft-authentication-library-for-objc`](https://github.com/AzureAD/microsoft-authentication-library-for-objc/releases)
- [`microsoft-authentication-library-for-android`](https://github.com/AzureAD/microsoft-authentication-library-for-android/releases)
- [`microsoft-identity-web`](https://github.com/AzureAD/microsoft-identity-web/releases)

### トークン処理のための回復性があるパターン

MSAL を使用しない場合は、回復性があるパターンをトークン処理に使用します。 MSAL ライブラリではベスト プラクティスが実装されています。

一般的に、先進認証を使用するアプリケーションでは、エンドポイントを呼び出して、ユーザーを認証するトークンを取得したり、アプリケーションが保護された API を呼び出すことを承認したりします。 MSAL は認証を処理し、回復性を向上させるためのパターンを実装します。 MSAL を使用しない場合は、ベスト プラクティスについてこのセクションのガイダンスを使用してください。 それ以外の場合は、MSAL はベスト プラクティスを自動的に実装します。

#### トークンをキャッシュする

アプリが Microsoft ID プラットフォームからトークンを正確にキャッシュするようにします。 アプリがトークンを受け取った後、トークンを含む HTTP 応答には、キャッシュする期間と、それを再利用するタイミングを示す `expires_in` プロパティがあります。 アプリケーションが API アクセス トークンをデコードしようとしないことを確認します。

[Image: アプリケーションを実行しているデバイス上のトークン キャッシュを介して Microsoft ID プラットフォームを呼び出すアプリの図。]

キャッシュされたトークンでは、アプリと Microsoft ID プラットフォーム間の不要なトラフィックを防ぎます。 このシナリオでは、トークン取得呼び出しを減らすことで、アプリがトークン取得エラーの影響を受けにくくなります。 キャッシュされたトークンでは、アプリがトークンの取得をブロックする頻度が低くなるため、アプリケーションのパフォーマンスが向上します。 ユーザーは、トークンの有効期間中、アプリケーションにサインインしたままです。

#### トークンをシリアル化および永続化する

アプリがトークン キャッシュを安全にシリアル化して、アプリ インスタンス間でトークンを保持するようにします。 有効期間中にトークンを再利用する。 更新トークンとアクセス トークンが何時間も発行されます。 この間に、ユーザーがアプリケーションを複数回起動する場合があります。 アプリが起動したら、有効なアクセスまたは更新トークンを検索することを確認します。 これにより、アプリの回復性とパフォーマンスが向上します。

詳細情報:

- [アクセス トークンの更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token)
- [Microsoft ID プラットフォーム アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)

    [Image: アプリケーションを実行しているデバイス上のトークン キャッシュとトークン ストアを介して Microsoft ID プラットフォームを呼び出すアプリの図。]

永続的トークン ストレージに、ユーザー所有者またはプロセス ID に関するアクセス制御と暗号化があることを確認します。 さまざまなオペレーティング システムには、資格情報ストレージ機能があります。

#### トークンをサイレントに取得する

ユーザーを認証したり、API を呼び出すための認可を取得したりするためには、Microsoft ID で複数の手順が必要です。 たとえば、初めてサインインするユーザーは資格情報を入力し、多要素認証を実行します。 各ステップは、サービスを提供するリソースに影響します。 依存関係が最も少ない最適なユーザー エクスペリエンスは、サイレント トークンの取得です。

[Image: ユーザー認証または承認の完了に役立つ Microsoft ID プラットフォーム サービスの図。]

サイレント トークンの取得は、アプリのトークン キャッシュにある有効なトークンから開始されます。 有効なトークンがない場合、アプリは使用可能な更新トークンとトークン エンドポイントを使用してトークンの取得を試行します。 どちらのオプションも使用できない場合、アプリは `prompt=none` パラメーターを使用してトークンを取得します。 このアクションでは承認エンドポイントが使用されますが、ユーザーの UI は表示されません。 可能であれば、Microsoft ID プラットフォームはユーザーの操作なしにアプリにトークンを提供します。 トークンを生成するメソッドがない場合、ユーザーは手動で再認証します。

Note

一般的に、アプリで 'ログイン' や '同意' などのプロンプトが使用されないようにします。 これらのプロンプトは、操作が必要ない場合に、ユーザーの操作を強制します。

### 応答コードの処理

応答コードについては、次のセクションを参照してください。

#### HTTP 429 応答コード

回復性に影響するエラー応答があります。 アプリケーションが HTTP 429 応答コード (要求が多すぎます) を受信した場合、Microsoft ID プラットフォームによって要求が調整されています。 アプリの要求が多すぎる場合は、アプリがトークンを受け取らないように調整されます。 **Retry-After** 応答フィールド時間が完了する前に、アプリがトークンの取得を試みることを許可しないでください。 多くの場合、429 応答は、アプリケーションがトークンを正しくキャッシュおよび再利用していないことを示します。 アプリケーションでどのようにトークンがキャッシュされて再利用されているかを確認します。

#### HTTP 5x 応答コード

アプリケーションが HTTP 5x 応答コードを受信した場合、アプリは高速再試行ループに入ってはなりません。 429 応答にも同じ処理を行います。 "Retry-After" ヘッダーが表示されない場合は、応答から 5 秒以上経過した後に最初の再試行で指数バックオフ再試行を実装します。

要求がタイムアウトした場合、すぐに再試行することは推奨されません。 応答から 5 秒以上経過した後に最初の再試行で指数バックオフ再試行を実装します。

### 承認に関する情報を取得する

多くのアプリケーションと API では、認証するためにユーザー情報が必要です。 使用可能なメソッドには、長所と短所があります。

#### トークン

ID トークンとアクセス トークンには、情報を提供する標準の要求があります。 必要な情報がトークン内にある場合、最も効率的な手法はトークン要求です。これにより、別のネットワーク呼び出しを防ぐことができます。 ネットワーク呼び出しの数が少ないほど、回復性が向上します。

詳細情報:

- [Microsoft ID プラットフォームの ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)
- [Microsoft ID プラットフォーム アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)

Note

一部のアプリケーションでは、認証されたユーザーに関する要求を取得するために、UserInfo エンドポイントを呼び出します。 ID トークンの情報は、UserInfo エンドポイントからの情報のスーパーセットです。 UserInfo エンドポイントを呼び出す代わりに、アプリで ID トークンを使用できるようにします。

標準トークン要求を、グループなどの省略可能な要求で拡張できます。 **[アプリケーション グループ]** オプションには、アプリケーションに割り当てられたグループが含まれます。 **[すべて]** または **[セキュリティ グループ]** オプションには、同じテナント内のアプリのグループが含まれます。これにより、グループをトークンに追加できます。 トークンが肥大化し、グループを取得するためにより多くの呼び出しを必要とすることで、トークンでグループを要求することによって得られる効率性が打ち消される可能性があるので、効果を評価してください。

詳細情報:

- [アプリに省略可能な要求を提供する](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)
- [グループの省略可能な要求を構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims#configure-groups-optional-claims)

ポータルまたは API を使用して顧客が管理するアプリ ロールを使用して含めることをお勧めします。 ユーザーとグループにロールを割り当ててアクセスを制御します。 トークンが発行されると、割り当てられたロールはトークン ロール要求に含まれます。 トークンから派生した情報により、より多くの API 呼び出しを防ぐことができます。

「[アプリケーションにアプリ ロールを追加してトークンで受け取る](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)」を参照してください

テナント情報に基づいて要求を追加します。 たとえば、拡張機能には企業固有のユーザー ID があります。

ディレクトリからトークンに情報を追加すると効率的であり、依存関係を減らすことで回復性を向上させることができます。 トークンを取得取得できないことによる回復性の問題には対処しません。 アプリケーションの主要なシナリオに対する省略可能な要求を追加します。 アプリが管理機能の情報を必要とする場合、アプリケーションは必要に応じてその情報を取得できます。

#### Microsoft Graph

Microsoft Graph には、生産性パターン、ID、セキュリティに関する Microsoft 365 データにアクセスするための統合 API エンドポイントがあります。 Microsoft Graph を使用するアプリケーションでは、承認に Microsoft 365 情報を使用できます。

アプリは、Microsoft 365 にアクセスするために 1 つのトークンを必要とします。これは、複数のトークンを必要とする Microsoft Exchange や Microsoft SharePoint などの Microsoft 365 コンポーネントの以前の API よりも回復性が高くなります。

Microsoft Graph API を使用する場合は、Microsoft Graph にアクセスする回復性のあるアプリケーションの構築を簡略化する Microsoft Graph SDK を使用します。

「[Microsoft Graph SDK の概要](https://learn.microsoft.com/ja-jp/graph/sdks/sdks-overview)」を参照してください

承認のために、一部の Microsoft Graph 呼び出しの代わりにトークン要求を使用することを検討してください。 トークン内でグループ、アプリ ロール、および省略可能な要求を要求する。 承認のための Microsoft Graph では、Microsoft ID プラットフォームと Microsoft Graph に依存するより多くのネットワーク呼び出しが必要です。 ただし、アプリケーションがデータ レイヤーとして Microsoft Graph に依存している場合、承認のための Microsoft Graph のリスクは高くはありません。

### モバイル デバイスでブローカー認証を使用する

モバイル デバイスでは、Microsoft Authenticator のような認証ブローカーが回復性を向上させます。 認証ブローカーは、ユーザーとデバイスに関する要求を含むプライマリ更新トークン (PRT) を使用します。 認証トークンに PRT を使用して、デバイスから他のアプリケーションにアクセスします。 PRT からアプリケーション アクセスを要求すると、Microsoft Entra ID ではそのデバイスと MFA 要求を信頼します。 これにより、デバイスを認証するための手順を減らすことで、回復性が向上します。 ユーザーは、同じデバイス上の複数の MFA プロンプトに悩まされることはありません。

「[プライマリ リフレッシュ トークンとは](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)」を参照してください。

[Image: アプリケーションを実行しているデバイス上のトークン キャッシュ、トークン ストアおよび認証ブローカーを介して Microsoft ID プラットフォームを呼び出すアプリの図。]

MSAL では、ブローカー認証がサポートされています。 詳細情報:

- [iOS での認証ブローカーを使用した SSO](https://learn.microsoft.com/ja-jp/entra/msal/objc/single-sign-on-macos-ios#sso-through-authentication-broker-on-ios)
- [Android で MSAL を使用してクロス アプリ SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on)

### 継続的アクセス評価

継続的アクセス評価 (CAE) は、有効期間が長いトークンを使用してアプリケーションのセキュリティと回復性を高めることができます。 CAE では、短いトークンの有効期間ではなく、重大なイベントとポリシーの評価に基づいてアクセス トークンを取り消すことができます。 一部のリソース API については、リスクとポリシーがリアルタイムで評価されるため、CAE によりトークンの有効期間を最大 28 時間まで延長することができます。 MSAL は、有効期間の長いトークンを更新します。

詳細情報:

- [継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)
- [継続的アクセス評価によるアプリケーションのセキュリティ保護](https://learn.microsoft.com/ja-jp/security/zero-trust/develop/secure-with-cae)
- [重大なイベントの評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation#critical-event-evaluation)
- [条件付きアクセス ポリシーの評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation#conditional-access-policy-evaluation)
- [継続的アクセス評価が有効になった API をアプリケーションで使用する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-resilience-continuous-access-evaluation)

リソース API を開発する場合は、`openid.net` で「[共有シグナル - セキュリティで保護された Webhooks Framework](https://openid.net/wg/sse/)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-daemon-app"} -->
## 開発するデーモン アプリケーションでの認証と承認の回復性を高める - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-daemon-app
- Service: entra / architecture
- Article date: 2023-03-03
- Summary: Microsoft ID プラットフォームを使用してデーモン アプリケーションの認証と承認の回復性を向上させる方法について説明します

Microsoft ID プラットフォームと Microsoft Entra ID を使用して、デーモン アプリケーションの回復性を高める方法について説明します。 バックグラウンド プロセス、サービス、サーバー間アプリ、およびユーザーなしのアプリケーションに関する情報を検索します。

[Microsoft ID プラットフォームとは](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)

次の図は、Microsoft ID プラットフォームを呼び出すデーモン アプリケーションを示しています。

[Image: Microsoft ID プラットフォームを呼び出すデーモン アプリケーション。]

### Azure リソースのマネージド ID

Microsoft Azure でデーモン アプリを構築する場合は、シークレットと資格情報を処理する Azure リソースのマネージド ID を使用します。 この機能により、証明書の有効期限、ローテーション、または信頼を処理することで回復性が向上します。

[Azure リソースのマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) とは

マネージド ID は、有効期間の長いアクセス トークンと Microsoft ID プラットフォームからの情報を使用して、トークンの有効期限が切れる前に新しいトークンを取得します。 アプリは、新しいトークンの取得中に実行されます。

マネージド ID はリージョン エンドポイントを使用します。これは、サービスの依存関係を統合することでリージョン外の障害を防ぐのに役立ちます。 リージョン エンドポイントでは、地理的な領域でトラフィックを維持することができます。 たとえば、Azure リソースが WestUS2 内にある場合は、すべてのトラフィックが WestUS2 内にとどまります。

### Microsoft Authentication Library

デーモン アプリを開発し、マネージド ID を使用しない場合は、認証と承認に Microsoft Authentication Library (MSAL) を使用します。 MSAL を使用すると、クライアント資格情報を提供するプロセスが容易になります。 たとえば、アプリケーションは、証明書ベースの資格情報を使用して JSON Web トークン アサーションを作成して署名する必要はありません。

[Microsoft 認証ライブラリ (MSAL) の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview)を参照してください。

#### .NET 開発者向けの Microsoft.Identity.Web

ASP.NET Core でデーモン アプリを開発する場合は、Microsoft.Identity.Web ライブラリを使用して承認を容易にします。 これには、複数のリージョンで実行される分散アプリの分散トークン キャッシュ戦略が含まれています。

詳細情報：

- [Microsoft Identity Web 認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/microsoft-identity-web/)
- [分散トークン キャッシュ](https://github.com/AzureAD/microsoft-identity-web/wiki/token-cache-serialization#distributed-token-cache)

### トークンのキャッシュと格納

認証と承認に MSAL を使用しない場合は、トークンのキャッシュと格納に関するベスト プラクティスがあります。 MSAL は、これらのベスト プラクティスを実装し、従います。

アプリケーションは ID プロバイダー (IdP) からトークンを取得して、保護された API を呼び出すアプリケーションを承認します。 アプリがトークンを受け取ると、トークンを含む応答には、トークンをキャッシュして再利用する期間をアプリケーションに伝える `expires\_in` プロパティが含まれます。 アプリケーションで `expires\_in` プロパティを使用してトークンの有効期間を決定することを確認します。 アプリケーションが API アクセス トークンをデコードしようとしないことを確認します。 キャッシュされたトークンを使用すると、アプリと Microsoft ID プラットフォームの間の不要なトラフィックを防ぐことができます。 ユーザーは、トークンの有効期間中にアプリケーションにサインインします。

[Microsoft Entra ID バックアップ認証システム](https://learn.microsoft.com/ja-jp/entra/architecture/backup-authentication-system)は、サポートされているプロトコルとフローを使用するアプリケーションに回復性を提供します。 バックアップ認証を利用するためのアプリケーション要件の詳細については、バックアップ認証 [システムのアプリケーション要件を](https://learn.microsoft.com/ja-jp/entra/architecture/backup-authentication-system-apps)参照してください。

### HTTP 429 および 5xx エラー コード

HTTP 429 および 5xx エラー コードについては、次のセクションを参照してください。

#### HTTP 429

回復性に影響する HTTP エラーがあります。 アプリケーションが HTTP 429 エラー コード (要求が多すぎます) を受け取った場合、Microsoft ID プラットフォームによって要求が調整され、アプリがトークンを受信できなくなります。 **Retry-After** 応答フィールドの時間が経過するまで、アプリがトークンを取得しようとしないようにしてください。 多くの場合、429 エラーは、アプリケーションがトークンを正しくキャッシュおよび再利用していないことを示しています。

#### HTTP 5xx

アプリケーションが HTTP 5x エラー コードを受け取った場合、アプリは高速再試行ループに入らてはなりません。 [ **Retry-After** ]\(再試行後\) フィールドの有効期限が切れるまでアプリケーションが待機していることを確認します。 応答に Retry-After ヘッダーがない場合は、最初の再試行 (応答の少なくとも 5 秒後) で指数バックオフ再試行を使用します。

要求がタイムアウトしたら、アプリケーションがすぐに再試行しないことを確認します。 前に示した指数バックオフ再試行を使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-for-federated-applications-with-colocated-users"} -->
## フェデレーション アプリケーションが配置されているサイトでの共同配置ユーザー向けの認証の堅牢性を向上させる - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-for-federated-applications-with-colocated-users
- Service: entra / architecture
- Article date: 2025-02-19
- Summary: 依存パーティセキュリティトークンサービスのIDプロバイダーを変更するためのオーケストレーションを伴うアプリケーション展開のレジリエンスガイダンス

ID およびアクセス管理アーキテクチャの [回復性のために構築](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-overview) する多くの組織が対応する必要があるシナリオの 1 つは、一時的なサイトの切断中のアプリケーション アクセスの継続性です。 組織には、アプリケーションを展開する 1 つ以上の物理サイトがある場合があります。 一部のユーザーは、これらのサイトに併置され、ローカル アプリケーションにアクセスできる必要があります。 たとえば、工場や店舗の従業員は、そのサイトで運用を管理する社内開発のビジネス アプリケーションにサインインできる必要がある場合があります。

これまで、組織は Active Directory Domain Services を使用し、 [各サイトにドメイン コントローラー](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/planning-domain-controller-placement)を展開して、ユーザーがローカル ドメイン コントローラーに対して認証できるようにすることができました。 Microsoft Entra を使用してユーザーを認証し、SAML などのフェデレーション プロトコルを使用してアプリケーションを Microsoft Entra ID に接続すると、多要素認証やリスクベースの条件付きアクセスなどの利点があります。 アプリケーションが Microsoft Entra とフェデレーションされている場合、ユーザーは Microsoft Entra に対して自分自身を認証し、Microsoft Entra は要求を伝達し、認証状態をアプリケーションに示すトークンを提供します。 ただし、サイトとインターネットの間のネットワーク接続に中断が発生した場合、ユーザーとアプリケーションは Microsoft Entra クラウド エンドポイントにアクセスできません。 その接続の中断により、中断が修復されるまで、そのルートを介して Microsoft Entra からアプリケーションにトークンを発行することはできません。 アプリケーションに対して既に認証されており、アプリケーションに接続できるユーザーは、一定期間、アプリケーションを引き続き使用できる場合があります。 ただし、アプリケーションでユーザーに再認証を要求し、中断がまだ修復されていない場合、ユーザーは Microsoft Entra から新しいアクセス トークンを取得できなくなります。 さらに、中断が修復されるまで、以前にアプリケーションに接続していないユーザーは、アプリケーションを使用できない場合があります。

この問題を解決し、一時的なサイト切断中でもフェデレーション アプリケーションにトークンを発行できるようにする方法の 1 つは、次のとおりです。

- 各サイトで Active Directory ドメイン コントローラーを維持する
- サイトのユーザーが Active Directory または Microsoft Entra に対して認証できること、および両方の ID プロバイダーから同じ要求を使用できることを確認する
- 通常の操作中に Microsoft Entra を ID プロバイダーとして信頼するようにアプリケーションを構成しますが、切断イベント中は、Active Directory を ID プロバイダーとして信頼します

[Image: Windows Server Active Directory と Microsoft Entra ID の両方を ID プロバイダーとして持つアプリケーション間の信頼関係を示す図。]

ただし、一部の事前パッケージ化されたフェデレーション アプリケーションでは、アプリケーションが一度に 1 つの ID プロバイダーで動作するように設計されている場合があり、複数の信頼された ID プロバイダーを持つアプリケーションを構成することは不可能な場合があります。 1 つの ID プロバイダー用に設計された社内で開発されたアプリケーションを持つ組織であっても、さまざまな構成モデルで多くのアプリケーションを変更するプロセスを実装するには、かなりのエンジニアリング作業が必要になる場合があります。

このチュートリアルで説明する別の方法は、AD FS などの証明書利用者セキュリティ トークン サービス (STS) をトポロジに追加することです。 このトポロジでは、

- 各サイトで Active Directory ドメイン コントローラーを維持する
- サイトのユーザーが Active Directory または Microsoft Entra に対して認証できることを確認する
- ローカル証明書利用者セキュリティ トークン サービス (STS) を信頼するようにアプリケーションを構成する
- 通常の操作中に Microsoft Entra を ID プロバイダーとして信頼するように証明書利用者 STS を構成しますが、切断イベントの間は、Active Directory を ID プロバイダーとして信頼します

[Image: アプリケーション、証明書利用者 STS、ID プロバイダーの間の信頼関係を示す図。Windows Server Active Directory と Microsoft Entra ID の両方が、ID プロバイダーとして証明書利用者 STS で構成されます。]

このチュートリアルでは、証明書利用者 STS を使用して、フェデレーション アプリケーション用に Microsoft Entra を構成する方法について説明します。 通常の操作では、ユーザーは Microsoft Entra に対して認証を行い、Microsoft Entra はアプリケーションのトークンを発行します。 また、サイトの切断状況では、ユーザーは Active Directory に対して認証を行い、Microsoft Entra が提供するのと同様の要求を持つそのアプリケーションのトークンを取得できます。 多要素認証やリスクベースの条件付きアクセスを含む Microsoft Entra 機能は、切断時にアプリケーションでは使用できませんが、ユーザーは引き続きこれらのアプリケーションにアクセスするための認証を受けられます。

重要

このガイドは、ドメイン コントローラーを複数のサイトに展開し、AD FS などのフェデレーション テクノロジを使用して [Active Directory ユーザーにクレーム対応アプリケーションへのアクセスを提供](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/provide-your-active-directory-users-access-to-your-claims-aware-applications-and-services)することに既に慣れている組織を対象としています。 ネットワークの停止に対する高可用性と回復性のための Active Directory、AD FS、および関連テクノロジのアーキテクチャとデプロイは、大きな投資です。 この記事で説明する手順を開始する前に、 [AD FS 展開トポロジを決定します](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/determine-your-ad-fs-deployment-topology)。 高可用性のために AD ドメインをデプロイしていない場合、または運用環境 [で AD FS の展開をサポートできることを確認した](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/ad-fs-deployment-topology-considerations#verifying-that-your-production-environment-can-support-an-ad-fs-deployment)場合は、回復性のための別の方法を選択します。

運用テナントでアプリケーションまたはフェールオーバーを構成する前に、非運用環境を使用してこの記事の手順をテストすることをお勧めします。

### [前提条件]

このチュートリアルでは、Microsoft Entra テナントと Active Directory ドメインの両方に依存します。 開始する前に、Microsoft Entra に次のいずれかのロールがあることを確認します: `Cloud Application Administrator`、 `Application Administrator`。 また、AD を管理するためのアクセス許可も必要です。

各サイトで、インターネットやその他のサイトへの接続に依存せずに、アプリケーションをサポートするための十分なインフラストラクチャ コンポーネントがあることを確認する必要があります。 このチュートリアルでは、次の構成について説明します。

- 1 つ以上のアプリケーション。各アプリケーションは、証明書利用者が開始したサインオン パターンを使用して SAML などのフェデレーション プロトコルを実装します。 詳細については、「 [シングル サインオン SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)」を参照してください。 このチュートリアルでは、複数のアプリケーションがある場合、同じユーザー セットを認証し、すべてのアプリケーションに対して承認する構成について説明します。 アプリケーションごとに異なるユーザー セットを構成することは、このチュートリアルの範囲外です。
- Active Directory フェデレーション サービスなどの証明書利用者 STS として動作するように構成できる ID コンポーネント。 このチュートリアルでは、Windows Server 2025 を想定しています。 セキュリティのベスト プラクティスとして、Active Directory フェデレーション サービスのフェデレーション サーバーをファイアウォールの内側に配置し、それらを企業ネットワークに接続して、インターネットからの露出を防ぎます。 フェデレーション サーバーにはセキュリティ トークンを付与するための完全な承認があるため、この方法が重要です。 そのため、ドメイン コントローラーと同じ保護が必要です。 フェデレーション サーバーが侵害された場合、悪意のあるユーザーは Active Directory フェデレーション サービスによって保護されているすべての Web アプリケーションにフル アクセス トークンを発行できます。 詳細については、 [フェデレーション サーバーを配置する場所を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/where-to-place-a-federation-server)参照してください。
- Active Directory ドメイン コントローラー。 運用環境では、高可用性のために構成された複数のドメイン コントローラーが必要です。

アプリケーションには、これらの ID 要件を超える他の要件 (ログの保持、DNS/DHCP、その他のネットワークおよびインフラストラクチャ サービスの要件など) が含まれる場合があります。このチュートリアルの範囲外です。

#### Active Directory と Microsoft Entra ID 全体で一貫性のあるユーザー ID と属性を確保する

アプリケーションは、構成された ID プロバイダーに関係なく、証明書利用者 STS からフェデレーション トークン内のユーザーに対して同じ要求を受け取ることを期待します。 証明書利用者 STS が Microsoft Entra ID から想定どおりに同じ要求を提供するには、Microsoft Entra ID に到達できない場合でも、証明書利用者 STS が要求を生成するために使用できるサイトに対してローカルなユーザー ID とその属性のソースが存在する必要があります。 AD FS は、ID ソースとして Windows Server Active Directory をサポートし、それらの Active Directory ユーザー属性をクレーム ソースとしてサポートします。

一貫性を維持する 1 つの方法は、AD でユーザーを作成および更新し、それらのユーザーを AD から Microsoft Entra に同期する ID 管理プロセスを定義することです。 AD 環境と関連する Microsoft Entra エージェントが、アプリケーションに必要なスキーマを使用して AD との間でユーザーを転送する準備ができていることを確認します。

[Image: Microsoft Entra と Active Directory の間のユーザー オブジェクトのデータ フローを示す図。]

Active Directory から Microsoft Entra に同期するようにユーザーを既に構成している場合は、次のセクションに進みます。

1. **Active Directory のごみ箱を有効にします**。 Microsoft Entra ライフサイクル ワークフローが AD からユーザーを削除できるようにするには、ごみ箱機能が必要です。 詳細については、「 [Active Directory 管理センターを使用した Active Directory ごみ箱の有効化と管理」を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/get-started/adac/Advanced-AD-DS-Management-Using-Active-Directory-Administrative-Center--Level-200-#enabling-and-managing-the-active-directory-recycle-bin-using-active-directory-administrative-center)参照してください。
2. **必要に応じて、Active Directory スキーマを拡張します。** Microsoft Entra に必要なユーザー属性と、AD ユーザー スキーマにまだ含まれていないアプリケーションごとに、組み込みのユーザー拡張機能属性を選択する必要があります。 または、 [WINDOWS Server AD スキーマを拡張して、](https://learn.microsoft.com/ja-jp/windows/win32/ad/how-to-extend-the-schema) AD がその属性を保持する場所を持つ必要があります。 この要件には、作業者の結合日や休暇日など、自動化に使用される属性も含まれます。

    たとえば、一部の組織では、 `extensionAttribute1` 属性と `extensionAttribute2` を使用してこれらのプロパティを保持する場合があります。 組み込みの拡張属性を使用する場合は、それらの属性が他の LDAP ベースのアプリケーションや、Microsoft Entra ID と統合された他のアプリケーションによってまだ使用されていないことを確認してください。 一部の組織では、 `contosoWorkerId`など、要件に固有の名前を持つ新しい AD 属性を作成しています。 ただし、新しい属性を使用してスキーマを拡張すると、Windows Server ドメイン管理に大きな影響があります。 詳細については、 [スキーマの拡張を](https://learn.microsoft.com/ja-jp/windows/win32/ad/extending-the-schema)参照してください。
3. **権限のあるソースから属性を持つユーザーを取り込みます**。 まだ行っていない場合は、 [Workday](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial)、 [SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)、またはその [他の人事ソース](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app) から AD のユーザーとして worker をプロビジョニングするように Microsoft Entra を構成します。 ワーカー レコードのプロパティを AD スキーマのユーザー属性にマップできます。
4. **Microsoft Entra と Active Directory の間の同期を設定します**。 これらのユーザーを AD から [Microsoft Entra に同期するように Microsoft Entra Connect 同期または Microsoft Entra クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest) を構成します。 また、AD からのユーザーの削除などの [ライフサイクル ワークフロー ユーザー アカウント タスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-on-premises#user-account-tasks)を実行するために、プロビジョニング エージェントをデプロイします。
5. **Microsoft Entra ID スキーマを拡張し、Active Directory スキーマから Microsoft Entra ID ユーザー スキーマへのマッピングを構成します。** Microsoft Entra Connect Sync を使用している場合は、「 [Microsoft Entra Connect Sync: Directory 拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions) 」の手順を実行して、属性を使用して Microsoft Entra ID ユーザー スキーマを拡張します。 AD の属性と Microsoft Entra ID の属性の Microsoft Entra Connect Sync マッピングを構成します。

    Microsoft Entra Cloud Sync を使用している場合は、 [Microsoft Entra Cloud Sync ディレクトリ拡張機能とカスタム属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping) の手順を実行して、Microsoft Entra ID ユーザー スキーマを他の必要な属性で拡張します。 AD の属性と Microsoft Entra の属性の Microsoft Entra Cloud Sync マッピングを構成します。 [ライフサイクル ワークフローに必要な属性を](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes)同期していることを確認します。
6. **サインインをブロックする leaver ワークフローを作成します。** Microsoft Entra ライフサイクル ワークフローで、ユーザーがオンプレミスにサインインするのをブロックする `Disable user account` タスクを使用して、離職者ワークフローを構成します。 このワークフローは、オンデマンドで実行できます。 スケジュールされた休暇日の後にワーカーがサインインできないようにレコードソースからの受信プロビジョニングを構成しなかった場合は、そのタスクを含む離職者ワークフローを、それらのワーカーのスケジュールされた休暇日に実行するように構成します。 詳細については、「 [オンプレミスから同期されたユーザーを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-on-premises)」を参照してください。
7. **ユーザー アカウントを削除する離職者ワークフローを作成します。** Microsoft Entra ライフサイクル ワークフローで、active Directory からユーザーを削除する `Delete user` タスクを含む離職者ワークフローを構成します。 このワークフローを、作業者のスケジュールされた休暇日から 30 日や 90 日間など、一定期間実行するようにスケジュールします。

複数の属性を持つアプリケーションの HR 受信フローの設定の詳細については、「 [ユーザー プロビジョニングのための Microsoft Entra の展開の計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

#### Active Directory のユーザーに認証オプションを提供する

HR ソースから AD への受信プロビジョニングを構成する場合、Microsoft Entra は、以前に AD ユーザー アカウントを持っていない既存のワーカーと、後で参加した新しいワーカーの両方に対して、AD にユーザーを作成します。 これらのユーザーは、切断されている間に AD に対して認証を行うことができ、また、通常の操作中に Microsoft Entra ID に対して認証できる必要があります。 AD で使用できるアプリケーション アクセスが必要なワーカーに対して資格情報を発行することを計画する必要があります。

AD に接続されているアプリケーションが回復性のために構成されているアプリケーションのみである場合、一貫性のある認証を行う 1 つの方法は、AD でユーザーを作成し、AD から Microsoft Entra に同期されるユーザーの [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) を有効にすることです。 ユーザーが AD でパスワードを設定し、AD で自分のパスワードを知っていることを確認します。 通常の操作中に多要素認証の要件がある場合は、切断されたネットワーク イベント中に、パスワードを使用して Active Directory ドメインに対して認証を行うことができます。

もう 1 つのオプションは、 [Microsoft Entra 証明書ベースの認証 (CBA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) を使用することです。これにより、Microsoft Entra はエンタープライズ公開キー 基盤 (PKI) によって発行された X.509 証明書を使用してユーザーを認証できます。 AD では証明書認証もサポートされており、一部の組織ではユーザーにスマートカード、仮想スマートカード、またはソフトウェア証明書を発行しています。 ユーザーが Windows Hello for Business をサポートするデバイスからアプリケーションを操作する場合は、ユーザーがより強力な認証 [のために Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/) に登録することも検討してください。

### 信頼できるパーティ STS をデプロイする

まだ行っていない場合は、サイトの 1 つ以上の Windows Server に [AD FS](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/ad-fs-deployment) または同様の ID テクノロジを展開します。 この AD FS は証明書利用者 STS として動作するように構成されるため、Microsoft Entra にクレームを提供するために ID プロバイダーとして構成された別の AD FS と同じインストールを行うことはできません。

注

Microsoft Entra の ID プロバイダーとして既に AD FS を使用している場合、この証明書利用者 STS は、個別のドメインに参加しているサーバーに個別にインストールする必要があります。 AD FS のデプロイをロール別に分離すると、Microsoft Entra と AD FS の間の循環依存関係を回避するのに役立ちます。

[Image: 別の ID プロバイダーと証明書利用者セキュリティ トークン サービスが同じ Active Directory に接続されているトポロジを示す図。]

注

このガイドでは、高可用性のために AD を展開する方法や、 [フェデレーション サーバー ファーム](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/when-to-create-a-federation-server-farm) を展開する方法、または Windows Server 環境での高可用性に関するその他の考慮事項については説明しません。

証明書ベースの認証を使用している場合は、証明書を使用してユーザーを認証するように AD FS を構成する必要もあります。 詳細については、「 [ユーザー証明書認証の AD FS サポートを構成する」](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-user-certificate-authentication)を参照してください。

### 証明書利用者 STS をセキュリティで保護する

証明書利用者 STS は、ユーザーが認証されたことを示すトークンを提供するために、アプリケーションによって信頼されます。 そのためには、STS、それをホストするサーバー、およびアップストリーム ドメイン コントローラーまたはその他のリソースが、環境内の他の ID インフラストラクチャと同じセキュリティ標準にロックダウンされていることを確認する必要があります。

詳細については、「 [Active Directory フェデレーション サービスをセキュリティで保護するためのベスト プラクティス」を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/best-practices-securing-ad-fs)参照してください。

### フェデレーション アプリケーションを証明書利用者 STS に接続する

Microsoft Entra に直接接続するのではなく、サイトのどのアプリケーションが証明書利用者 STS に接続されるスコープ内にあるかを特定する必要があります。 これは、接続の損失中に操作を維持するために必要な最小セットである必要があります。 考慮事項は次のとおりです。

- トークンの発行に [Microsoft Authentication Library (MSAL) SDK](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) のみに依存するアプリケーションは、証明書利用者 STS に接続できません。
- 以前に 1 つのフェデレーション メタデータ エンドポイントまたは発行者証明書でハードコーディングされたアプリケーションでは、証明書利用者 STS と統合する前にその依存関係を削除するために追加の変更が必要になる場合があります。
- 複数の ID プロバイダーをサポートできるアプリケーションは、Microsoft Entra への既存の接続を保持したい場合があります。
- 証明書利用者 STS に関連付けられているアプリケーションは、Microsoft Entra では 1 つのアプリケーションとして表されるため、これらのアプリケーションには、同じユーザー、CA ポリシー、指定されたクレーム、およびそれらに適用できるその他の設定が必要になります。 2 つのアプリケーションが Microsoft Entra に依存してトークン発行を異なるユーザー集団に制限する場合、または異なる CA 要件がある場合、両方を同じ証明書利用者 STS に接続することはできません。

### 証明書利用者 STS の ID プロバイダーとして Microsoft Entra を構成する

[証明書利用者セキュリティ トークン サービスを使用したエンタープライズ アプリケーションのシングル サインオンの有効化に関するチュートリアルの手順を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso-rpsts)実行します。 このチュートリアルでは、Microsoft Entra でアプリケーションの表現を作成し、そのアプリケーションのシングル サインオンを Microsoft Entra から証明書利用者 STS に構成し、証明書利用者 STS からアプリケーションへのシングル サインオンを構成します。 このチュートリアルを実行すると、通常の操作中に、要求を含むトークンが証明書利用者 STS を介してアプリケーションに Microsoft Entra からフローできることを検証します。

注

Microsoft Entra では、証明書利用者 STS に接続されているアプリケーションの数に関係なく、証明書利用者 STS ごとに 1 つのアプリケーション表現が使用されます。 1 つの証明書利用者 STS に複数のアプリケーションが接続されている場合、Microsoft Entra で共通のアプリケーションを共有するため、Microsoft Entra で同じユーザー、CA ポリシー、指定されたクレーム、およびその他の設定が必要です。

### 証明書利用者 STS で AD を LDAP ソースとして構成する

次に、Active Directory がアプリケーションで必要な要求のクレーム ソースであることを証明書利用者 STS で構成します。 AD FS を使用して次の手順を示しますが、代わりに別の証明書利用者 STS を使用できます。

1. AD FS が展開されているサーバーで管理者としてサインインします。
2. `AD FS Management`を起動します。
3. [ `AD FS` ] メニューの [ `Claims Provider Trusts`] を選択します。
4. `Microsoft Entra`と`Active Directory`の 2 つのクレーム プロバイダーがあり、両方が有効になっていることを確認します。 既定では、 `Active Directory` クレーム プロバイダーが存在します。
5. 「`Active Directory`」を選択し、「`Edit Claim Rules`」を選択します。
6. 環境内のアプリケーションによって要求として必要な LDAP 属性の規則があることを確認します。
7. [ `AD FS` ] メニューの [ `Relying Party Trusts`] を選択します。
8. ターゲット アプリケーションを選択し、 `Edit Claim Issuance Policy`を選択します。
9. アプリケーションに送信される要求の規則が、必要なすべての要求を提供することを確認します。 詳細については、「 [受信要求のパススルーまたはフィルター処理」を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/create-a-rule-to-pass-through-or-filter-an-incoming-claim)参照してください。

### AD テスト ユーザーが Microsoft Entra 経由でアプリケーションにサインインする準備をする

構成をテストするときは、Active Directory で作成された指定されたテスト ユーザーを Microsoft Entra のアプリケーションのロールに割り当てる必要があります。 この AD ユーザーの選択により、ユーザーが Microsoft Entra と証明書利用者 STS を介してアプリケーションにサインオンできることが検証されます。

1. Active Directory でテスト ユーザーを識別します。 Active Directory と Microsoft Entra の両方に対してそのユーザーとして認証できるように、そのユーザーのパスワードがわかっていることを確認します。
2. AD でユーザーを最近作成した場合は、AD から Microsoft Entra ID への同期が完了するまで待ちます。

    Microsoft Entra Cloud Sync を使用している場合は、Microsoft Entra Cloud Sync を表すサービス プリンシパルの`steadyStateLastAchievedTime`を取得することで、同期状態の  プロパティを監視できます。サービス プリンシパル ID がない場合は、「[同期スキーマの表示」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-attributes#view-the-synchronization-schema)参照してください。
3. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
4. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
5. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
6. 左側のメニューの [ **管理** ] セクションで、[ **プロパティ**] を選択します。
7. **ユーザーがサインインできるように [有効]** の値が [**はい**] に設定されていることを確認します。
8. [ **必要な割り当て]** の値が [ **はい**] に設定されていることを確認します。
9. 変更した場合、**[保存]** を選択します。
10. 左側のメニューの [ **管理** ] セクションで、[ **ユーザーとグループ**] を選択します。
11. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/architecture/ユーザーまたはグループの追加)** を選択します。
12. **なしを選択します**
13. 検索ボックスにテスト ユーザーの名前を入力し、ユーザーを選択して [選択] を **選択します**。
14. [ **割り当て]** を選択して、アプリケーションの既定のユーザー ロールに **ユーザー** を割り当てます。
15. 左側のメニューの [ **セキュリティ** ] セクションで、[ **条件付きアクセス**] を選択します。
16. **[What If]** を選択します。
17. [ **ユーザーまたはサービス プリンシパルが選択されていません**] を選択し、[ **ユーザーが選択されていません**] を選択し、以前にアプリケーションに割り当てられたユーザーを選択します。
18. **任意のクラウド アプリ**を選択し、エンタープライズ アプリケーションを選択します。
19. **[What If]** を選択します。 適用されるすべてのポリシーで、ユーザーがアプリケーションにサインインすることを許可していることを検証します。

### 各アプリケーションのクレーム プロバイダーを Microsoft Entra に設定する

Microsoft Entra と証明書利用者 STS でアプリケーションを構成した後、ユーザーはアプリケーションにサインインできます。 ユーザーはまず Microsoft Entra に対する認証を行い、Microsoft Entra によって提供されるトークンは、AD FS などの証明書利用者 STS によって、アプリケーションに必要な形式と要求に変換されます。 さらに、ユーザーは AD に対して認証することでサインインでき、同様の要求を持つ AD FS によってトークンが提供されます。

このセクションでは、通常の操作中に各アプリケーションの ID プロバイダーとして Microsoft Entra を提供するように証明書利用者 STS を構成します。 AD FS を使用して次の手順を示しますが、代わりに別の証明書利用者 STS を使用できます。

1. AD FS がインストールされている Windows Server で PowerShell を起動します。
2. コマンド `Get-AdfsRelyingPartyTrust | ft Name,ClaimsProviderName`を使用して、アプリケーションの一覧を取得します。
3. コマンド `Get-AdfsClaimsProviderTrust | ft Name`を使用して、クレーム プロバイダーの一覧を取得します。 Active Directory 用と Microsoft Entra 用の 2 つの名前が必要です。
4. [Set-AdfsRelyingPartyTrust](https://learn.microsoft.com/ja-jp/powershell/module/adfs/set-adfsrelyingpartytrust) コマンドを使用して、Microsoft Entra が各アプリケーションの唯一のクレーム プロバイダーであることを構成します。 たとえば、アプリケーションの名前が `appname` の場合は、「 `Set-AdfsRelyingPartyTrust -TargetName "appname" -ClaimsProviderName "Microsoft Entra"`」と入力します。 これをアプリケーションごとに繰り返します。 詳細については、「 [証明書利用者ごとに ID プロバイダーの一覧を構成する」を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/home-realm-discovery-customization#configure-an-identity-provider-list-per-relying-party)参照してください。

### 通常の操作中にシングル サインオンを検証する

このセクションでは、通常の操作中にユーザーが Microsoft Entra に対してシームレスに認証し、アプリケーションにサインインできることを検証します。

[Image: アプリケーション、証明書利用者 STS、および ID プロバイダーとしての Microsoft Entra ID の間の Web ブラウザー リダイレクトを示す図。]

1. Web ブラウザーのプライベート ブラウズ セッションで、アプリケーションに接続し、ログイン プロセスを開始します。 アプリケーションは Web ブラウザーを証明書利用者 STS AD FS にリダイレクトし、AD FS は適切な要求を提供できる ID プロバイダーを決定します。
2. 前のセクションの構成に基づいて、AD FS は Microsoft Entra ID プロバイダーを選択します。 AD FS は、Microsoft Entra ID グローバル サービスを使用している場合に `https://login.microsoftonline.com` 、Web ブラウザーを Microsoft Entra ログイン エンドポイントにリダイレクトします。
3. 前のセクションで構成したテスト ユーザーの ID を使用して Microsoft Entra にサインインし、 AD テスト ユーザーがアプリケーションにサインインできるように準備します。 その後、Microsoft Entra はエンティティ識別子に基づいてエンタープライズ アプリケーションを検索し、Web ブラウザーを応答 URL エンドポイントで AD FS にリダイレクトし、Web ブラウザーは SAML トークンを転送します。
4. AD FS は、Microsoft Entra によって発行された SAML トークンを検証し、SAML トークンから要求を抽出して変換し、Web ブラウザーをアプリケーションにリダイレクトします。 このプロセスを介して、アプリケーションが Microsoft Entra から必要な要求を受け取っていることを確認します。

### アプリケーションによって信頼されているクレーム プロバイダーを Active Directory に変更した後にシングル サインオンを検証する

このセクションでは、アプリケーションの ID プロバイダーを Active Directory に切り替えた場合でも、ユーザーが認証してアプリケーションにサインインできることを検証します。

[Image: Microsoft Entra ID がアプリケーションの ID プロバイダーとして構成されていない場合の Web ブラウザーのリダイレクトを示す図。]

1. AD FS がインストールされている Windows Server で PowerShell を起動します。
2. PowerShell セッションで、コマンド `Set-AdfsRelyingPartyTrust`を使用して、Active Directory がアプリケーションの唯一のクレーム プロバイダーになり、Microsoft Entra を置き換えることを構成します。 たとえば、アプリケーションの名前が `appname` の場合は、「 `Set-AdfsRelyingPartyTrust -TargetName "appname" -ClaimsProviderName "Active Directory"`」と入力します。
3. ブラウザーでサインイン状態をクリアするには、以前に開いた Web ブラウザーのプライベート ブラウズ セッションを閉じます。
4. 新しい Web ブラウザーのプライベート ブラウズ セッションで、そのアプリケーションに接続し、ログイン プロセスを開始します。 アプリケーションは Web ブラウザーを AD FS にリダイレクトし、AD FS は適切な要求を提供できる ID プロバイダーを決定します。
5. 手順 2 の構成に基づいて、AD FS によって Active Directory が選択されます。
6. テスト ユーザーの Active Directory ID を使用して AD FS にサインインします。 AD FS は Active Directory でユーザーを認証します。
7. AD FS は、ユーザーの LDAP 属性をクレームに変換し、Web ブラウザーをアプリケーションにリダイレクトします。 このプロセスを使用して、アプリケーションが Active Directory から必要な要求を受信したことを確認します。
8. PowerShell セッションで、コマンド `Set-AdfsRelyingPartyTrust` を使用して、Microsoft Entra がアプリケーションのクレーム プロバイダーであることを再度構成し、 `-ClaimsProviderName` パラメーターの値を指定します。 `Set-AdfsRelyingPartyTrust -TargetName "appname" -ClaimsProviderName @()`などのコマンドを使用して、すべてのクレーム プロバイダーを許可することもできます。

### アプリケーションにサインインできるユーザーを構成する

次に、各アプリケーションにサインインできるユーザーを構成する必要があります。 AD FS と Microsoft Entra には、ポリシーでトークン発行をスコープするためのメカニズムが異なります。そのため、両方で変更が必要になる場合があります。

1. 以前は、Microsoft Entra のテスト ユーザーをアプリケーションのロールに割り当てていましたが、ここでそのテスト ユーザーのロールの割り当てを削除することもできます。
2. 複数のアプリケーションが同じ証明書利用者 STS に接続されている場合、Microsoft Entra では、それらのすべてのアプリケーションを表す 1 つのアプリケーション オブジェクトしか持つことができない場合があります。 ロールの割り当てでは、動的グループやエンタイトルメント管理などの機能を使用して、アプリケーション ロールにユーザーを割り当てることができます。 ユーザーがアプリケーション ロールのロール メンバーシップを持っている場合、ユーザーは Microsoft Entra からトークンを受け取ることができるようになります。
3. さらに、AD FS では、アプリケーションに割り当てられているアクセス制御ポリシーも適用されます。 アプリケーションに対して選択するすべてのポリシーは、Microsoft Entra のトークンと AD に対して認証されるユーザーの両方について、AD FS によって評価できる必要があります。 Microsoft Entra からのトークンは Active Directory を通過しないため、 **特定のグループ** アクセス制御ポリシーを許可するポリシーを使用することはできません。これにより、Microsoft Entra トークンが拒否されます。 AD からトークンを発行するために AD FS のアクセスを制御する場合は、代わりに別のポリシーを使用する必要があります。 アクセス制御ポリシーの詳細については、「 [AD FS のアクセス制御ポリシー](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/access-control-policies-in-ad-fs)」を参照してください。

### AD と Microsoft Entra への自動フェールオーバーを実行するようにタスクを構成する

次に、サイトからの接続用にモニターを構成する必要があります。 このモニターは、切断が検出されたときに、そのアプリケーションの `Microsoft Entra` コマンドを呼び出すことによって、AD FS 内の各アプリケーションの ID プロバイダーを `Active Directory` から `Set-AdfsRelyingPartyTrust` に自動的に切り替えます。 必要に応じて、接続が復元されたことが検出されたときに AD FS 構成を `Microsoft Entra` にリセットするようにモニターを構成することもできます。

単純な環境では、PowerShell スクリプトと組み込みのタスク スケジューラを使用してモニターを実装できます。 スケールアウトデプロイの場合、モニターのデプロイは、組織で使用されている IT 自動化システムによって異なり、この記事の範囲外です。

#### AD FS が AD にフェールオーバーするようにスケジュールされたタスクの例を構成する

1. サイトからネットワーク接続エラーを検出し、id プロバイダーを変更する `Set-AdfsRelyingPartyTrust` を呼び出すスクリプトを作成します。 スクリプトの例は、 [https://github.com/microsoft/Entra-reporting/blob/main/PowerShell/sample-changeover-multiple-apps.ps1](https://raw.githubusercontent.com/microsoft/Entra-reporting/refs/heads/main/PowerShell/sample-changeover-multiple-apps.ps1)にあります。 スクリプトをダウンロードする場合は、PowerShell でスクリプトを実行する前に、エクスプローラーを使用してスクリプトのブロックを解除する必要があることに注意してください。
2. AD FS を使用して Windows Server にスクリプトをコピーします。
3. AD FS の構成と、そのサイトの AD FS で構成されているアプリケーションの一覧と一致するようにスクリプトを編集します。
4. AD FS がインストールされている Windows Server で PowerShell を起動します。
5. スクリプトがアプリケーション イベント ログに書き込むことができるようにソースを登録します。 たとえば、スクリプトの名前が `AD FS changeover script` の場合は、コマンド `New-EventLog -LogName Application -Source "AD FS changeover script"`を入力します。
6. **イベント ビューアー**を起動し、新しく作成したログに移動します。
7. スクリプトを PowerShell で実行してテストし、対話型セッションから正しく動作することを確認します。
8. **タスク スケジューラを**起動し、タスク スケジューラ ライブラリを表示します。
9. タスク スケジューラにタスク用のフォルダーがまだない場合は、フォルダーを作成します。
10. タスクのフォルダーを選択し、[タスクの **作成**] を選択します。
11. タスクの [ **全般** ] タブの設定を入力します。
12. [トリガー] タブ **に** 移動します。組織のリスクとネットワークのガイダンスに従って、[ **新規** ] を選択し、タスクの繰り返しスケジュールを指定します。
13. [ **アクション]** タブに移動します。[ **新規** ] を選択し、 **プログラムを開始**するアクションを選択します。 プログラムとして `powershell.exe` を指定し、PowerShell スクリプトを呼び出すために必要な引数を指定します。 たとえば、`-NonInteractive -WindowStyle Hidden -File c:\scripts\ad_fs_changeover_script.ps1` のようにします。 [ **OK] を** 選択してアクション ウィンドウを閉じ、[ **OK] を** 選択してタスク ウィンドウを閉じます。 詳細については、 [powershell.exeについて](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_powershell_exe)を参照してください。
14. [ **実行**] を選択し、1 分間待ってから、[ **最新の情報に更新**] を選択します。 スクリプトが正常に開始および完了したことを確認し、 **イベント ビューアー** でエラーが記録されたかどうかを確認します。

#### セッション無効化の構成 (省略可能)

アプリケーションには、認証されたユーザーから派生したユーザー セッションがある場合もあります。 たとえば、ブラウザー ベースのアプリケーションでは、ユーザーを認証し、ユーザーが認証されたことを示すブラウザー Cookie を格納できます。 できるだけ短い期間、切断された ID プロバイダーにアプリケーションを依存させる必要がある場合があります。 たとえば、ユーザーが以前にアプリケーションを使用していなかった場合、ユーザーが初めてアプリケーションに接続しようとしたのが、サイトが切断されたときの場合、認証は Windows Server AD によって実行されました。 その認証の後、アプリケーションは、ユーザーが再認証を必要とせずにアプリケーションと対話し続けることができるように、セッション Cookie を保存している可能性があります。 ただし、サイトが再接続されたら、条件付きアクセスなどの Microsoft Entra ポリシーを適用できるように、ユーザーを Microsoft Entra によって再認証することができます。 これには、特定の時間枠内に生成されたセッション状態を許可しないようにアプリケーションに通知するなど、Windows Server AD から派生したユーザーのセッション状態を無効にする必要があります。 これをアプリケーションに提供する方法は、アプリケーションによって異なります。

### 完全な構成

初期サインオン構成をテストした後、新しい証明書が Microsoft Entra に追加されると、証明書利用者 STS が最新の状態に保たれるようにする必要があります。 証明書利用者 STS によっては、ID プロバイダーのフェデレーション メタデータを監視するための組み込みプロセスがある場合があります。

高可用性のために AD FS を構成する方法の詳細については、 [フェデレーション サーバー ファーム](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/when-to-create-a-federation-server-farm)を展開する方法を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-in-credentials"} -->
## Microsoft Entra ID で資格情報管理を使用して回復性を強化する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-credentials
- Service: entra / architecture
- Article date: 2026-04-03
- Summary: 回復性がある資格情報戦略の構築に関する、アーキテクトおよび IT 管理者向けのガイド。

トークン要求で Microsoft Entra ID に資格情報が提示された場合、検証に使用できなければならない複数の依存関係があるはずです。 1 つ目の認証要素は、Microsoft Entra 認証 (および場合によってはオンプレミス インフラストラクチャなどの外部 (Entra ID 以外の) 依存関係) に依存しています。 ハイブリッド認証アーキテクチャの詳細については、「[ハイブリッド インフラストラクチャでの回復性の強化](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-hybrid)」に関する記事を参照してください。

最も回復性のある安全な資格情報戦略は、パスワードレス認証を使用することです。 [Windows Hello for Business](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) および [Passkey (FIDO 2.0)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) セキュリティ キーの依存関係は、他の MFA メソッドよりも少なくなります。 macOS ユーザーの場合、顧客は [macOS の Platform 資格情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)を有効にすることができます。 これらの方法を実装すると、ユーザーは強力なパスワードレスと **フィッシング対策**の Multi-Factor Authentication (MFA) を実行できます。

[Image: 優先する認証方法と依存関係の画像]

ヒント

これらの認証方法の展開に関するビデオ シリーズの詳細については、「[Microsoft Entra ID でのフィッシング耐性のある認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/phishing-resistant-authentication-videos)」を参照してください。

2 つ目の要素を実装すると、2 つ目の要素の依存関係が 1 つ目のものの依存関係に追加されます。 たとえば、1 つ目の要素で[パススルー認証 (PTA)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) が使用され、2 つ目の要素が[携帯ショートメール](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin)の場合、依存関係は次のようになります。

- Microsoft Entra 認証サービス
- Microsoft Entra 多要素認証サービス
- オンプレミス インフラストラクチャ
- 電話会社
- ユーザーのデバイス (図には示されていません)

[Image: 残りの認証方法と依存関係の画像。]

資格情報戦略では、各種の認証の依存関係、および単一障害点を回避するプロビジョニング方法を考慮する必要があります。

認証方法にはさまざまな依存関係があるため、ユーザーができるだけ多くの第 2 要素オプションを登録できるようにすることをお勧めします。 可能であれば、依存関係が異なる第 2 要素を含めるようにしてください。 たとえば、第 2 要素としての音声通話と携帯ショートメールでは同じ依存関係が共有されるため、それらを唯一のオプションとして使用した場合、リスクは軽減されません。

第 2 要素として、時間ベースのワンタイム パスコード (TOTP) または OAuth ハードウェア トークンを使用する Microsoft Authenticator アプリまたはその他の認証アプリは、最も依存関係が少ないため、より回復性が高くなります。

### 外部 (Entra 以外) の依存関係に関する追加の詳細

| 認証方法 | 外部 (Entra 以外) の依存関係 | その他の情報 |
| --- | --- | --- |
| 証明書ベースの認証 (CBA) | ほとんどの場合 (構成に応じて) CBA には失効チェックが必要です。 これにより、CRL 配布ポイント (CDP) への外部依存関係が追加されます | [証明書の失効プロセスについて](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list#enforce-crl-validation-for-cas) |
| パススルー認証 (PTA) | PTA では、オンプレミスのエージェントを使用してパスワード認証を処理します。 | [Microsoft Entra パススルー認証のしくみとは?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-how-it-works#how-does-microsoft-entra-pass-through-authentication-work) |
| フェデレーション | フェデレーション サーバーはオンラインであり、認証の試行を処理するために使用できる必要があります | [Azure Traffic Manager を使用した Azure への可用性に優れた地域間 AD FS デプロイ](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/active-directory-adfs-in-azure-with-azure-traffic-manager) |
| 外部多要素認証 (外部 MFA) | 外部 MFA は、顧客が外部 MFA プロバイダーを使用するためのパスを提供します。 | [Microsoft Entra ID で外部 MFA を管理する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage) |

### 複数の資格情報が回復性に役立つ理由

複数の種類の資格情報をプロビジョニングすることで、ユーザーの設定と環境の制約に対応したオプションが提供されます。 その結果、ユーザーが多要素認証の入力を求められる対話型認証は、要求時に特定の依存関係が使用できないことに対する回復性が向上します。 [多要素認証の再認証プロンプトを最適化](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime)できます。

前述の個々のユーザーの回復性に加えて、企業では、構成の誤り、自然災害、または (特に多要素認証に使用されている場合の) オンプレミス フェデレーション サービスに対する企業全体でのリソース停止をもたらす運用上のエラーなど、大規模中断に関する不測の事態に備えて対策を講じておく必要があります。

### 回復性がある資格情報を実装する方法

- [パスワードレスの資格情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-deployment)を配置します。 依存関係を減らしながらセキュリティを強化するには、Windows Hello for Business、Passkeys (Authenticator パスキー サインインと FIDO2 セキュリティ キーの両方)、証明書ベースの認証 (CBA) などのフィッシングに強い方法を使用します。
- [Microsoft Authenticator アプリ](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)を第 2 要素としてデプロイします。
- [フェデレーションからクラウド認証に移行](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication)し、フェデレーション ID プロバイダーへの依存を削除します。
- Windows Server Active Directory から同期されるハイブリッド アカウントで[パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)をオンにします。 このオプションは、Active Directory フェデレーション サービス (AD FS) などのフェデレーション サービスと共に有効にでき、フェデレーション サービスで障害が発生した場合にフォールバックを提供します。
- [多要素認証方法の使用状況を分析して](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity)、ユーザーのエクスペリエンスを向上させます。
- [回復性のあるアクセス制御戦略を実装する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-resilient-controls)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-in-hybrid"} -->
## Microsoft Entra IDで回復性の高いハイブリッド認証を構築する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-hybrid
- Service: entra / architecture
- Article date: 2022-11-16
- Summary: 回復性があるハイブリッド インフラストラクチャの構築に関する、アーキテクトおよび IT 管理者向けのガイド。

ハイブリッド インフラストラクチャには、クラウドとオンプレミスの両方のコンポーネントが含まれています。 ハイブリッド認証を使用すると、ユーザーはオンプレミスから発信された ID を使用してクラウドベースのリソースにアクセスしたり、クラウドベースの ID を使用してオンプレミスのリソースにアクセスしたりできます。

- クラウド コンポーネントには、Microsoft Entra ID、Azureリソースとサービス、組織のクラウドベースのアプリ、SaaS アプリケーションが含まれます。
- オンプレミス コンポーネントには、オンプレミス アプリケーション、SQL データベースなどのリソース、Windows Server Active Directoryなどの ID プロバイダーが含まれます。

重要

ハイブリッド インフラストラクチャでの回復性を計画する際は、依存関係と単一障害点の数を最小限にすることが重要です。 オンプレミスとクラウドの接続の中断は、ハードウェア障害、停電、自然災害、マルウェア攻撃など、さまざまな理由で発生する可能性があります。

Microsoftでは、Microsoft Entraに接続されているアプリケーションに対してハイブリッド認証のための複数のメカニズムが提供されます。 組織がActive Directoryパスワードとパススルー認証またはフェデレーションに依存してユーザーを認証している場合は、可能であればパスワード ハッシュ同期を実装することをお勧めします。

- [Password ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) では、Microsoft Entra Connect を使用して、WINDOWS SERVER AD から Microsoft Entra ID に ID とパスワードのハッシュを同期します。 これにより、ユーザーはMicrosoft Entraにサインインして、Active Directoryで設定したのと同じパスワードでクラウドベースのリソースにアクセスできます。 PHS には、認証時ではなく、同期中にのみ AD への依存関係があります。
- [パススルー認証 (PTA)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) は、サインインのためにユーザーをMicrosoft Entra IDにリダイレクトします。 その後、ユーザー名とパスワードは、企業ネットワークにデプロイされているエージェントを介してオンプレミスのActive Directoryに対して検証されます。 PTA には、オンプレミスのサーバーに存在する Microsoft Entra PTA エージェントのフットプリントがあります。 これらのサーバーは認証中に到達可能であり、ドメイン コントローラーに到達できる必要があります。
- [Federation](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) ユーザーは、ACTIVE DIRECTORY FEDERATION SERVICES (AD FS) などのフェデレーション サービスを ID プロバイダーとしてデプロイします。 Microsoft Entra IDは、ID プロバイダーのフェデレーション サービスに対する認証をユーザーにリダイレクトし、フェデレーション サービスによって生成された SAML アサーションを検証します。 ユーザーは ID プロバイダーに接続できる必要があり、ID プロバイダーがActive Directoryに依存している場合もあります。
- [Microsoft Entra証明書ベースの認証 (CBA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) を使用するとMicrosoft Entraは、Active Directory、Microsoft Entra ID、またはその両方に格納されているエンタープライズ公開キー 基盤 (PKI) によって発行された X.509 証明書を使用してユーザーを認証できます。 Microsoft Entra CBA を使用すると、Active Directory Federation Services (AD FS) の必要性を排除することで、オンプレミス コンポーネントへの依存関係を簡素化および削減できます。

組織では、これらの方法の 1 つ以上が使用されている可能性があります。 詳細については、「[Microsoft Entra ハイブリッド ID ソリューションに適した認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)を参照してください。 この記事には、方法を決定するのに役立つデシジョン ツリーが含まれています。

### パスワード ハッシュの同期

Microsoft Entra IDの最も単純で回復性の高いハイブリッド認証オプションは、[Password ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)です。 これには、認証要求を処理する際にオンプレミス ID インフラストラクチャへの依存関係はありません。 パスワード ハッシュを持つ ID がMicrosoft Entra IDに同期されると、ユーザーはオンプレミスの ID コンポーネントに依存せず、Microsoft Entraおよびクラウド リソースに対して認証を行うことができます。

[Image: PHS のアーキテクチャ図]

この認証オプションを選択した場合、オンプレミスの ID コンポーネントが使用できなくなったときに、Microsoft Entraやその他のクラウド リソースへのアクセスが中断されることはありません。

#### PHS の実装方法

PHS を実装するには、次のリソースを参照してください。

- [Microsoft Entra Connect を使用したパスワード ハッシュ同期の実装](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)
- [パスワード ハッシュ同期を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)

PHS を使用できないという要件がある場合は、パススルー認証を使用してください。

### パススルー認証

パススルー認証は、サーバー上にオンプレミスで存在する認証エージェントに依存します。 永続的な接続 (Service Bus) は、Microsoft Entra IDとオンプレミスの PTA エージェントの間に存在します。 ファイアウォール、認証エージェントをホストするサーバー、オンプレミスのWindows Server Active Directory (またはその他の ID プロバイダー) はすべて潜在的な障害ポイントです。

[Image: PTA のアーキテクチャ図]

#### PTA の実装方法

パススルー認証を実装するには、次のリソースを参照してください。

- [パススルー認証のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-how-it-works)
- [パススルー認証のセキュリティの詳細](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-security-deep-dive)
- [Microsoft Entra パススルー認証をインストールする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start)
- PTA を使用する場合は、[高可用性トポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start)を定義します。

### フェデレーション

フェデレーションには、エンドポイントの交換、トークン署名証明書、およびその他のメタデータを含む、Microsoft Entra IDとフェデレーション サービス間の信頼関係の作成が含まれます。 要求がMicrosoft Entra IDに届くと、設定が読み取られ、ユーザーは設定されたエンドポイントへリダイレクトされます。 その時点で、ユーザーはフェデレーション サービスと対話し、Microsoft Entra IDによって検証される SAML アサーションを発行します。

次の図は、企業の AD FS のトポロジを示しています。これは、複数のオンプレミス データ センターにまたがる冗長フェデレーションおよび Web アプリケーション プロキシ・サーバーが含まれたデプロイです。 この構成は、DNS、geo アフィニティ機能を備えたネットワーク負荷分散、ファイアウォールなどのエンタープライズ ネットワーク インフラストラクチャ コンポーネントに依存します。 オンプレミスのコンポーネントと接続はすべて、障害の影響を受けやすくなります。 詳細については、[AD FS のキャパシティ プランニングに関するドキュメント](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/planning-for-ad-fs-server-capacity)を参照してください。

注意

フェデレーションには、オンプレミスの依存関係が多数あります。 こちらの図は AD FS を示していますが、他のオンプレミスの ID プロバイダーも、高可用性、スケーラビリティ、およびフェールオーバーを実現するために、同様の設計上の考慮事項の対象となります。

[Image: フェデレーションのアーキテクチャ図]

#### フェデレーションの実装方法

フェデレーション認証戦略を実装している場合、またはその回復性を向上させたい場合は、次のリソースを参照してください。

- [フェデレーション認証とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed)
- [フェデレーションのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-whatis)
- [Microsoft Entra フェデレーション互換性リスト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-compatibility)
- [AD FS のキャパシティ プランニングに関するドキュメント](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/design/planning-for-ad-fs-server-capacity)に従います
- AZURE IaaS
- フェデレーションと共に [PHS を有効](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-phs-backup)にします

### 関連するアーキテクチャ リソース

ハイブリッド ID に関連するアーキテクチャとデプロイに関するその他のガイダンスについては、次を参照してください。

- [Microsoft Entra 展開計画](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans) — 認証、アプリ、デバイス、ハイブリッド シナリオの展開ガイダンス
- [Microsoft Entra アーキテクチャの概要](https://learn.microsoft.com/ja-jp/entra/architecture/architecture) — サービス設計、スケーラビリティ、継続的可用性、データセンター アーキテクチャ
- [Azure](https://learn.microsoft.com/ja-jp/azure/architecture/identity/identity-start-here) の識別子とアクセス管理のアーキテクチャ - ハイブリッド ID の参照アーキテクチャ、ベースライン実装、設計ガイダンス
- [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/azure/architecture/reference-architectures/identity/azure-ad) を使用したオンプレミス AD の統合 - ダウンロード可能なVisio図を含む完全な参照アーキテクチャ
- [適切な認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn) - ハイブリッド ID ソリューションの認証デシジョン ツリー
- [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency) のデータ・レジデンシー — データの保存場所、ソブリン クラウド、環境の制約
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-in-infrastructure"} -->
## Microsoft Entra ID の IAM インフラストラクチャで回復性を強化する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-infrastructure
- Service: entra / architecture
- Article date: 2022-11-16
- Summary: 設計担当者と IT 管理者のための、IAM インフラストラクチャ障害に対する回復性の構築ガイド。

Microsoft Entra ID は、組織のリソースに対する認証や認可などの重要なサービスを提供する、グローバル クラウドの ID およびアクセス管理システムです。 この記事では、Microsoft Entra ID に依存するリソースの認証または認可サービスが中断されるリスクを理解し、封じ込め、軽減するためのガイダンスを提供します。

このドキュメントセットは、〜するために設計されています。

- ID アーキテクト
- ID サービスの所有者
- ID 運用チーム

[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview)および [Azure AD B2C システム](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-b2c)を対象としたドキュメントも参照してください。

### 回復性とは

ID インフラストラクチャのコンテキストにおいて、回復性とは、ビジネス、ユーザー、および運用への影響を最小限またはゼロに抑えながら、認証や認可などのサービスの中断、または他のコンポーネントの障害に耐える能力のことです。 中断の影響は深刻なものになる可能性があり、回復性には入念な計画が必要です。

### 中断について考慮する理由

認証システムへのすべての呼び出しは、呼び出しのいずれかのコンポーネントで障害が発生すると中断される可能性があります。 基本コンポーネントのエラーが原因で認証が中断されると、ユーザーはアプリケーションにアクセスできなくなります。 したがって、認証呼び出しの回数とそれらの呼び出しに含まれる依存関係の数を減らすことが、回復性にとって重要になります。 アプリケーション開発者は、トークンの要求頻度に対して何らかの制御をアサートできます。 たとえば、開発者と協力して、可能な限りアプリケーションに対して Azure リソースのマネージド ID を使用していることを確認します。

Microsoft Entra ID のようなトークンベースの認証システムでは、ユーザーのアプリケーション (クライアント) は、アプリケーションやその他のリソースにアクセスする前に、ID システムからセキュリティ トークンを取得する必要があります。 有効期間中、クライアントは、アプリケーションにアクセスするために、同じトークンを複数回提示できます。

アプリケーションに提示されたトークンの有効期限が切れると、そのトークンはアプリケーションによって拒否され、クライアントは Microsoft Entra ID から新しいトークンを取得する必要があります。 新しいトークンを取得するには、資格情報のプロンプトなどのユーザー操作や、認証システムのその他の要件を満たすことが必要になる場合があります。 有効期間が長いトークンを使用して認証呼び出しの頻度を減らすことで、不要なやりとりが減ります。 ただし、トークンの有効期間と、ポリシー評価数を減らすことで生じるリスクとのバランスを取る必要があります。 トークンの有効期間の管理について詳しくは、[再認証プロンプトの最適化](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime)に関するこちらの記事を参照してください。

### 回復性を向上させる方法

次の図は、回復性を向上させるための 6 つの具体的な方法を示しています。 各方法の詳細については、この記事の「次のステップ」部分でリンクされている記事をご覧ください。

[Image: 管理の回復性の概要を示す図]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-on-premises-access"} -->
## アプリケーション プロキシを使用したアプリケーション アクセスで回復性を強化する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-on-premises-access
- Service: entra / architecture
- Article date: 2022-11-16
- Summary: オンプレミスのアプリケーションへの回復性のあるアクセスのためにアプリケーション プロキシを使用することに関するアーキテクトと IT 管理者向けのガイド

アプリケーション プロキシとは、ユーザーがリモート クライアントからオンプレミス Web アプリケーションにアクセスできるようにする Microsoft Entra ID の機能です。 アプリケーション プロキシには、クラウド内のアプリケーション プロキシ サービスと、オンプレミス サーバーで実行されるプライベート ネットワーク コネクタが含まれます。

ユーザーは、アプリケーション プロキシ経由で公開された URL を使用して、オンプレミスのリソースにアクセスします。 ユーザーは Microsoft Entra ID のサインイン ページにリダイレクトされます。 Microsoft Entra ID のアプリケーション プロキシ サービスは、そのトークンをオンプレミスの Active Directory に渡す企業ネットワーク内のプライベート ネットワーク コネクタにトークンを送信します。 認証されたユーザーは、オンプレミスのリソースにアクセスできます。 次の図では、[コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)は[コネクタ グループ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connector-groups)内に示されています。

Von Bedeutung

アプリケーション プロキシ経由でアプリケーションを発行する場合は、 [プライベート ネットワーク コネクタの容量計画と適切な冗長性を実装する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#specifications-and-sizing-requirements)必要があります。

[Image: アプリケーション y のアーキテクチャ図] )

### アプリケーション プロキシの実装方法

Microsoft Entra アプリケーション プロキシを使用したリモート アクセスを実装するには、次のリソースを参照してください。

- [アプリケーション プロキシのデプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-deployment-plan)
- [高可用性と負荷分散のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-high-availability-load-balancing)
- [プロキシ サーバーを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-connectors-with-proxy-servers)
- [回復性のあるアクセス制御戦略を設計する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-resilient-controls)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-overview"} -->
## Microsoft Entra ID を使用したアイデンティティおよびアクセス管理のレジリエンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-overview
- Service: entra / architecture
- Article date: 2022-08-26
- Summary: ID およびアクセス管理の回復性を強化する方法について説明します。 回復性は、システム コンポーネントの中断に耐えて最小限の労力で復旧するのに役立ちます。

ID 管理とアクセス管理 (IAM) は、プロセス、ポリシー、テクノロジのフレームワークです。 IAM によって、ID とそれらがアクセスするものの管理が容易になります。 これには、システム内のユーザーおよびその他のアカウントの認証と認可をサポートする多くのコンポーネントが含まれます。

IAM の回復性とは、システム コンポーネントの中断に耐え、ビジネス、ユーザー、顧客、および運用への影響を最小限にして復旧する能力です。 包括的なエラー処理を確保しながら、依存関係、複雑さ、および単一点障害を減らすことで、回復性が向上します。

中断は、IAM システムのどのコンポーネントからでも発生する可能性があります。 回復性がある IAM システムを構築するには、中断が発生するものと想定し、それらに備えて計画を立てる必要があります。

IAM ソリューションの回復性を計画するときは、次の要素を考慮してください。

- IAM システムに依存しているアプリケーション
- 通信会社、インターネット サービス プロバイダー、公開キーのプロバイダーなど、認証呼び出しで使用される公共インフラストラクチャ
- クラウドとオンプレミスの ID プロバイダー
- IAM に依存する他のサービスと、それらを接続する API
- システム内のその他のオンプレミス コンポーネント

原因が何であれ、不測の事態を認識し、それに備えて計画を立てることが重要です。 ただし、他の ID システムを追加し、その結果として依存関係と複雑さが増すことで、回復性は向上するのではなく、むしろ低下する可能性があります。

システムの回復性を高めるには、次の記事を確認してください。

- [IAM インフラストラクチャで回復性を強化する](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-infrastructure)
- [アプリケーションで IAM の回復性を強化する](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview)
- [顧客 ID およびアクセス管理 (CIAM) システムで回復性を強化する](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-b2c)

### 関連するリソース

- [Microsoft Entra 展開計画](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans) — ハイブリッド シナリオ、認証、ガバナンスなどのデプロイ ガイダンス
- [ハイブリッド アーキテクチャで回復性を構築](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-hybrid) する - PHS、PTA、フェデレーション トポロジのアーキテクチャ図
- [Azure](https://learn.microsoft.com/ja-jp/azure/architecture/identity/identity-start-here) の Id とアクセス管理のアーキテクチャ - 参照アーキテクチャと設計ガイダンス
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-with-continuous-access-evaluation"} -->
## Microsoft Entra ID で継続的アクセス評価を使用して回復性を強化する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-with-continuous-access-evaluation
- Service: entra / architecture
- Article date: 2022-11-16
- Summary: アーキテクトと IT 管理者を対象とした CAE の使用に関するガイド

[継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) (CAE) を使用すると、Microsoft Entra アプリケーションで重要なイベントをサブスクライブし、それらのイベントを評価して適用することができます。 CAE には次のイベントの評価が含まれます。

- ユーザーアカウントが削除または無効化された
- ユーザーのパスワードが変更された
- ユーザーに対して多要素認証が有効になった
- 管理者がトークンを明示的に取り消した
- ユーザーのリスクが検出された

これにより、アプリケーションは Microsoft Entra ID から通知されたイベントに基づいて、期限が切れていないトークンを拒否できます (次の図を参照)。

[Image: CAE の概念図]

### CAE がもたらすメリット

CAE メカニズムにより、Microsoft Entra ID では有効期間の長いトークンを発行できるようになるほか、必要な場合にのみ、アプリケーションによってアクセスを取り消して再認証を強制できるようになります。 このパターンにより、最終的に、トークンを取得する呼び出しの数が減るため、エンド ツー エンドのフローの回復性が向上します。

CAE を使用するには、サービスとクライアントの両方が CAE に対応している必要があります。 Exchange Online、Teams、SharePoint Online などの Microsoft 365 サービスでは、CAE がサポートされています。 クライアント側では、これらの Office 365 サービス (Outlook Web App など) を使用するブラウザー ベースのエクスペリエンスと、特定バージョンの Office 365 ネイティブ クライアントが CAE に対応しています。 CAE 対応になる Microsoft クラウド サービスは、今後増える予定です。

Microsoft は業界と連携して、サードパーティのアプリケーションが CAE 機能を使用できるようにするための[標準](https://openid.net/wg/sse/)を作成しています。 CAE 対応のアプリケーションを開発することも可能です。 CAE に対応したアプリケーション開発の詳細については、[アプリケーションで回復性を強化する方法](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview)に関するページを参照してください。

### CAE を実装する方法

- [CAE 対応 API を使用するようにコードを更新します](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-resilience-continuous-access-evaluation)。
- Microsoft Entra のセキュリティ構成で [CAE を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)。
- 組織で使用されている Microsoft Office ネイティブ アプリケーションが[互換性のあるバージョン](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)であることを確認します。
- [再認証プロンプトを最適化します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-with-device-states"} -->
## Microsoft Entra ID でデバイスの状態を使用して回復性を強化する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-with-device-states
- Service: entra / architecture
- Article date: 2022-11-16
- Summary: アーキテクトと IT 管理者を対象とした、デバイスの状態を使用して回復性を強化するためのガイド

管理者は、Microsoft Entra ID で[デバイスの状態](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)を有効にすることで、デバイスの状態に基づいてアプリケーションへのアクセスを制御する[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を作成できます。 デバイスの状態を有効にすると、リソース アクセスの強力な認証要件が満たされ、多要素認証要求が削減され、回復性が向上します。

次のフロー チャートは、デバイスの状態を有効にするように Microsoft Entra ID でデバイスをオンボードする方法を示しています。 組織ではこのうちの複数を使用できます。

[Image: デバイスの状態を選択するためのフロー チャート]

[デバイスの状態](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)を使用する場合は、ほとんどのケースでユーザーは[プライマリ更新トークン](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) (PRT) を通じてリソースにシングル サインオンすることになります。 PRT には、ユーザーとデバイスに関する要求が含まれます。 これらの要求を使用して、デバイスからアプリケーションにアクセスするための認証トークンを取得できます。 PRT は 14 日間有効であり、ユーザーがデバイスをアクティブに使用している限り継続的に更新されることで、回復性があるユーザーエクスペリエンスを提供します。 PRT で多要素認証の要求を取得する方法については、「[PRT が MFA 要求を受けるのはいつですか?](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)」を参照してください。

### デバイス状態はどのように役立ちますか？

PRT がアプリケーションへのアクセスを要求すると、デバイス、セッション、MFA に関するその要求は Microsoft Entra ID によって信頼されます。 管理者がデバイスベースのコントロールまたは多要素認証コントロールを必要とするポリシーを作成すると、MFA を試行することなくデバイスの状態によってそのポリシー要件を満たすことができます。 これ以降、同じデバイスでユーザーに多要素認証プロンプトが表示されることはありません。 これにより、Microsoft Entra 多要素認証サービスや、ローカル通信プロバイダーなどの依存関係の中断に対する回復性が向上します。

### デバイスの状態を実装する方法

- 会社が所有する Windows デバイスで [Microsoft Entra ハイブリッド参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)および [Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-plan)を有効にし、可能であれば参加するように要求します。 可能でない場合は登録を要求します。 組織に古いバージョンの Windows がある場合は、Windows 10 を使用するようにそれらのデバイスをアップグレードします。
- [Microsoft Edge](https://learn.microsoft.com/ja-jp/deployedge/microsoft-edge-security-identity) または Google Chrome のいずれかで PRT を使用した Web アプリケーションへのシームレスな SSO を可能にする [Microsoft Single Sign On extension](https://chrome.google.com/webstore/detail/windows-10-accounts/ppnbnpeolgkicgegkbkbjmhlideopiji) を使用するようにユーザーのブラウザー アクセスを標準化します。
- 個人または会社が所有する iOS デバイスと Android デバイスでは、[Microsoft Authenticator アプリ](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)をデプロイします。 Microsoft Authenticator アプリは、MFA 機能とパスワードレス サインイン機能に加えて、エンドユーザーに対する認証プロンプトが少ない[ブローカー認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on)を使用して、ネイティブ アプリケーション全体でシングル サインオンを有効にします。
- 個人または会社が所有する iOS デバイスと Android デバイスでは、[モバイル アプリケーション管理](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-management)を使用して、少ない認証要求で会社のリソースに安全にアクセスできるようにします。
- macOS デバイスの場合、[Apple デバイス用の Microsoft Enterprise SSO プラグイン (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin) を使用してデバイスを登録し、ブラウザーとネイティブ Microsoft Entra アプリケーション全体で SSO を実現します。 次に、環境に応じて、Microsoft Intune または Jamf Pro に固有の手順に従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilience-with-monitoring-alerting"} -->
## Azure AD B2C を使用した監視と分析による回復性 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilience-with-monitoring-alerting
- Service: entra / architecture
- Article date: 2025-05-20
- Summary: Azure AD B2C を使用した監視と分析による回復性

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

監視することにより、アプリケーションとサービスの可用性とパフォーマンスは最大限に高められます。 インフラストラクチャとアプリケーションのテレメトリを収集、分析し、対応する包括的なソリューションが提供されます。 サービスまたはアプリケーションに問題が見つかると、アラートが通知されます。 サービスのエンド ユーザーが問題に気付く前に、それらを特定して対処できます。 [Microsoft Entra ID Log Analytics](https://azure.microsoft.com/services/monitor/?OCID=AID2100131_SEM_6d16332c03501fc9c1f46c94726d2264:G:s&amp;ef_id=6d16332c03501fc9c1f46c94726d2264:G:s&amp;msclkid=6d16332c03501fc9c1f46c94726d2264#features) では、監査ログとサインイン ログの分析と検索を行い、カスタム ビューを作成することができます。

### 監視を行いアラートを通じて通知を受ける

システムとインフラストラクチャの監視は、サービス全体の正常性を確保するのに役立ちます。 これは、新規ユーザーのアクセス、エンド ユーザーの認証率、コンバージョンなどのビジネス メトリックを定義することから始まります。 このような監視対象の指標を構成します。 プロモーションまたは休日のトラフィックに起因する急増が見込まれる場合は、そのイベントおよび対応するビジネス メトリックのベンチマークの推定値を修正してください。 イベントの後に、以前のベンチマークに戻します。

同様に、障害またはパフォーマンスの中断を検出するには、適切なベースラインを設定してから、アラートを定義します。 発生する問題に迅速に対応してください。

#### 監視とアラートの実装

- **監視**: [Azure Monitor](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/azure-monitor) を使用し、主要なサービス レベル目標 (SLO) に対して、正常性を継続的に監視します。 クリティカルな変更が発生した際に通知を受け取ります。 SLO を維持するために正常性を監視する必要があるビジネスのクリティカルなコンポーネントとして、Azure AD B2C ポリシーまたはアプリケーションを特定します。 SLO に対応した主要指標を特定します。 たとえば、次のメトリックを追跡します。いずれかが急激に低下するとビジネスでの損失につながるためです。

    - **要求の総数**: Azure AD B2C ポリシーに送信された要求の総数 "n"。
    - **成功率 (%)** : 成功した要求の数 / 要求の総数。

    Azure AD B2C ポリシー ベースのログ、[監査ログ](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/view-audit-logs)、およびサインイン ログが格納される [Application Insights](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/analytics-with-application-insights) で、[主要指標](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/analytics-with-application-insights)にアクセスします。

    - **視覚化**: Log Analytics を使用してダッシュボードを作成し、主要なインジケーターを視覚的に監視します。
    - **現在の期間**: 現在の期間 (今週、など) での要求の合計数と成功率 (%) における変化を示す、一時的なグラフを作成します。
    - **前の期間**: 以前のある期間での要求の合計数と成功率 (%) における変化を示す、一時的なグラフを作成します。
- **アラート**: Log Analytics を使用して、主要なインジケーターが急激に変化した際にトリガーされる[アラート](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/alerts-create-new-alert-rule)を定義します。 これらの変化は SLO に悪影響を及ぼす場合があります。 アラートには、電子メール、SMS、Webhook などのさまざまな形式の通知方法が使用されます。 アラート トリガーのしきい値として条件を定義します。 次に例を示します。

    - 要求の合計数での急激な低下のアラート: 要求の合計数が急激に低下した際にアラートをトリガーします。 たとえば、要求数が前の期間と比較して 25% 低下した際にアラートを発生させます。
    - 成功率 (% ) での大幅な低下のアラート: 選択したポリシーの成功率が低下した際にアラートをトリガーします。
    - アラートを受信したら、[Log Analytics](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-view-designer-conversion-overview)、[Application Insights](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/troubleshoot-with-application-insights)、および Azure AD B2C 用 [VS Code 拡張機能](https://marketplace.visualstudio.com/items?itemName=AzureADB2CTools.aadb2c)を使用して問題のトラブルシューティングを行います。 その問題を解決し、更新したアプリケーションまたはポリシーをデプロイした後は、通常の範囲に戻るまで主要なインジケーターの監視が継続されます。
- **サービス アラート**: [Azure AD B2C のサービス レベル アラート](https://learn.microsoft.com/ja-jp/azure/service-health/service-health-overview)を使用して、サービスの問題、計画メンテナンス、正常性アドバイザリ、セキュリティ アドバイザリの通知を受け取ります。
- **レポート**: [Log Analytics を使用して](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)、ユーザーの分析情報、技術的な課題、成長の機会に関するレポートを作成します。

    - **Azure ダッシュボード**: Log Analytics クエリを使用したグラフの追加をサポートする [Azure ダッシュボード機能を使用してカスタム ダッシュボード](https://learn.microsoft.com/ja-jp/azure/azure-monitor/app/overview-dashboard#create-custom-kpi-dashboards-using-application-insights)を作成します。 たとえば、成功および失敗したサインインのパターン、失敗した理由、要求を行うために使用されたデバイスに関するテレメトリを確認します。
    - **Azure AD B2C 体験を破棄する**: [ブック](https://github.com/azure-ad-b2c/siem#list-of-abandon-journeys)を使用して、ユーザーがサインインまたはサインアップを開始したものの完了しなかった、破棄された Azure AD B2C 体験を追跡します。 ポリシー ID に関する詳細と、体験を破棄する前にユーザーが実行した手順を確認します。
    - **Azure AD B2C 監視ブック**: Azure AD B2C ダッシュボード、多要素認証 (MFA) 操作、条件付きアクセス レポート、correlationId によるログの検索を含む[監視ブック](https://github.com/azure-ad-b2c/siem)を使用します。 この方法により、Azure AD B2C 環境の正常性に関するより良い分析情報が得られます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilient-end-user-experience"} -->
## Azure AD B2C を使用した回復性のあるエンドユーザー エクスペリエンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilient-end-user-experience
- Service: entra / architecture
- Article date: 2025-05-20
- Summary: Azure AD B2C を使用してエンドユーザー エクスペリエンスで回復性を強化する方法を学びます

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

サインアップとサインインのエンドユーザー エクスペリエンスは、次の要素で構成されています。

- ユーザーがやり取りするインターフェイス: CSS、HTML、JavaScript など
- ユーザーが作成するユーザー フローとカスタム ポリシー: サインアップ、サインイン、プロファイルの編集など
- アプリケーション用の ID プロバイダー (IDP): ローカル アカウントのユーザー名またはパスワード、Microsoft Outlook、Facebook、Google など

### ユーザー フローとカスタム ポリシー

最も一般的な ID タスクを設定しやすくするために、Azure AD B2C には組み込みの構成可能な[ユーザー フロー](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/user-flow-overview)が用意されています。 また、最大限の柔軟性を提供する独自の[カスタム ポリシー](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/custom-policy-overview)を作成することもできます。 ただし、複雑なシナリオに対処するために、カスタム ポリシーを使用することをお勧めします。

#### ユーザー フローまたはカスタム ポリシーを選択する

ビジネス要件を満たす組み込みのユーザー フローを選択します。 Microsoft が組み込みのフローをテストするため、ユーザーは、ポリシー レベルの機能、パフォーマンス、またはスケールを検証するためのテストを最小限に抑えることができます。 ただし、機能、パフォーマンス、スケールについては、アプリケーションをテストします。

[カスタム ポリシー](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/user-flow-overview)を使用すると機能、パフォーマンス、またはスケールのポリシー レベルのテストを確実に行うことができます。 アプリケーション レベルのテストを実施します。

詳細については、[ユーザー フローとカスタム ポリシーを比較](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/user-flow-overview#comparing-user-flows-and-custom-policies)できます。

### 複数の IdP を選択する

Facebook などの[外部 IdP](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/add-identity-provider) を使用する場合は、外部 IdP が使用できない場合nのフォールバック プランを作成します。

#### 複数の IdP を設定する

外部 IdP 登録プロセスには、ユーザーの携帯電話番号やメール アドレスなどの検証済みの ID 要求を含めます。 検証済みの要求を、基になっている Azure AD B2C ディレクトリ インスタンスにコミットします。 外部 IdP が使用できない場合は、検証済みの ID 要求に戻し、認証方法として電話番号にフォール バックします。 もう 1 つのオプションは、ユーザーにサインイン用のワンタイム パスコード (OTP) を送信することです。

[代替の認証パスを構築](https://github.com/azure-ad-b2c/samples/tree/master/policies/idps-filter)できます:

1. ローカル アカウントと外部 IDP によるサインアップを許可するように、サインアップ ポリシーを構成します。
2. ユーザーがサインインした後に[他の ID を自分のアカウントにリンク](https://github.com/Azure-Samples/active-directory-b2c-advanced-policies/tree/master/account-linking)することを許可するように、プロファイル ポリシーを構成します。
3. 障害発生時にはユーザーに通知し、[代替の IDP に切り替える](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/customize-ui-with-html#configure-dynamic-custom-page-content-uri)ことができるようにします。

### 多要素認証の可用性

[多要素認証に電話サービスを使用する場合](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/phone-authentication-user-flows)、代替のサービス プロバイダーを検討してください。 ローカルの電話サービス プロバイダーは、サービスの中断が発生する場合があります。

#### 代替の多要素認証を選択する

Azure AD B2C サービスには、電話ベースの MFA プロバイダーがあり、時間ベースのワンタイム パスコード (OTP) が配信されます。 これは、ユーザーが事前登録した電話番号への音声通話と SMS メッセージです。

ユーザー フローでの回復性の強化には、2 つの方法があります。

- **ユーザー フローの構成を変更する**: 電話ベースの OTP 配信の中断中に、OTP 配信方法をメールに変更します。 ユーザー フローを再デプロイします。

    [Image: ユーザーのサインインとサインアップのスクリーンショット。]
- **アプリケーションの変更**: サインアップやサインインなどの ID タスクの場合は、2 セットのユーザー フローを定義します。 最初のセットでは電話ベースの OTP、2 番目のセットではメールの OTP を使用するように構成します。 電話ベースの OTP 配信の中断中に、ユーザー フローは変更しないままで、ユーザー フローを最初のセットから 2 番目に切り替えます。

カスタム ポリシーを使用する場合、回復性を構築するには 4 つの方法があります。 リストは複雑さの順に並んでいます。 更新されたポリシーを再デプロイします。

- **電話 OTP またはメール OTP のユーザーによる選択を有効にする**: 両方のオプションを公開して、ユーザーが自己選択できるようにします。 ポリシーやアプリケーションは変更しないでください。
- **電話 OTP とメール OTP を動的に切り替える**: サインアップ時に電話とメールの両方の情報を収集します。 電話の中断中に条件付きでメール OTP に切り替えるカスタム ポリシーを定義します。 ポリシーやアプリケーションを変更しないでください。
- **認証アプリを使用する**: 認証アプリを使用するようにカスタム ポリシーを更新します。 MFA が電話またはメール OTP の場合は、カスタム ポリシーを再デプロイし、認証アプリを使用します。

    注

    ユーザーはサインアップ時に Authenticator 統合を構成します。
- **セキュリティに関する質問**: 前の方法をすべて適用できない場合は、セキュリティの質問を使用します。 これらの質問は、オンボード中のユーザーまたはプロファイルの編集に関する質問です。 回答は別のデータベースに格納されます。 この方法は、MFA の要件である "ユーザーが持っているもの" (電話など) は満たしていませんが、"ユーザーが知っているもの" です。

### コンテンツ配信ネットワーク

コンテンツ配信ネットワーク (CDN) は、カスタム ユーザー フロー UI を格納するための BLOB ストアよりもパフォーマンスが良く、コストが低くなります。 Web ページのコンテンツは、地理的に分散した高可用性サーバーのネットワークから提供されます。

エンド ツー エンドのシナリオとロード テストを通じて、CDN の可用性とコンテンツ配信のパフォーマンスを定期的にテストします。 プロモーションや休日のトラフィックによる急増の場合は、ロード テストの見積もりを修正します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/resilient-external-processes"} -->
## Azure AD B2C を使用した外部プロセスとの回復性のあるインターフェイス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/resilient-external-processes
- Service: entra / architecture
- Article date: 2025-05-20
- Summary: 外部プロセスとの、回復性があるインターフェイスを構築する方法について説明します。

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

この記事では、アプリケーションの API エラーに対する回復性を高めるために、RESTful API を計画および実装する方法に関するガイダンスを示します。

### 正しい API 配置を確認する

Identity Experience Framework (IEF) ポリシーを使用し、[RESTful API 技術プロファイル](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/restful-technical-profile)を使用して外部システムを呼び出します。 IEF ランタイム環境は外部システムを制御しません。これは潜在的な障害点となります。

#### API を使用して外部システムを管理する

インターフェイスを呼び出して特定のデータにアクセスするときに、そのデータが認証の決定要因となっていることを確認します。 その情報がアプリケーションの機能にとって不可欠であるかどうかを評価します。 たとえば、Eコマースと管理のような二次的な機能の対比です。 認証にその情報が必要ない場合は、呼び出しをアプリケーション ロジックに移動することを検討します。

認証用のデータが比較的静的で小さく、外部化すべきでない場合は、ディレクトリに配置します。

可能であれば、事前認証されたパスから API 呼び出しを削除します。 できない場合は、API のサービス拒否 (DoS) 攻撃と分散サービス拒否 (DDoS) 攻撃に対する保護を有効にします。 攻撃者はサインイン ページを読み込み、API に大量の DoS 攻撃を実行してアプリケーションを無効にしようとする可能性があります。 たとえば、サインインやサインアップのフローで、人間とコンピュータを区別する完全自動化されたテスト (CAPTCHA) を使用します。

ID プロバイダーとのフェデレーション後、サインアップ中、またはユーザーの作成前に、[サインアップ ユーザー フローの API コネクタ](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/api-connectors-overview)を使用して Web API と統合します。 ユーザー フローはテストされるため、ユーザー フロー レベルの機能、パフォーマンス、またはスケールのテストを実行する必要はありません。 アプリケーションの機能、パフォーマンス、スケールをテストします。

Azure AD B2C RESTful API [技術プロファイル](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/restful-technical-profile)では、キャッシュ動作は提供されません。 代わりに、RESTful API プロファイルは、ポリシーに組み込まれている再試行ロジックとタイムアウトを実装します。

データを書き込む必要がある API の場合は、タスクを使用してこれらのアクションをバックグラウンド ワーカーに実行させます。 [Azure キュー](https://learn.microsoft.com/ja-jp/azure/storage/queues/storage-queues-introduction)などのサービスを使用します。 この方法により、API は効率よくレスポンスを返し、ポリシー実行のパフォーマンスを向上させます。

### API エラー

API は Azure AD B2C システムの外部にあるため、技術プロファイルでエラー処理を有効にします。 ユーザーに通知され、アプリケーションが障害に適切に対処できることを確認します。

#### API エラーの処理

API はさまざまな理由で失敗するため、アプリケーションの回復性を高めてください。 API が要求の処理を完了できない場合は、[HTTP 4XX エラー メッセージを返します](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/restful-technical-profile#returning-validation-error-message)。 Azure AD B2C ポリシーでは、API が利用できない状況に対処し、場合によってはエクスペリエンスを低下させるようにしてください。

[一時的なエラーをスムーズに処理します](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/restful-technical-profile#error-handling)。 RESTful API プロファイルを使用して、さまざまな[サーキット ブレーカー](https://learn.microsoft.com/ja-jp/azure/architecture/patterns/circuit-breaker)のエラー メッセージを構成します。

継続的インテグレーションと継続的デリバリー (CICD) を監視して使用します。 [技術プロファイル エンジン](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/restful-technical-profile)で使用されるパスワードや証明書などの API アクセス資格情報をローテーションします。

### API 管理のベスト プラクティス

REST API をデプロイし、RESTful 技術プロファイルを構成するときは、次のベスト プラクティスに従って一般的なエラーを回避してください。

#### API Management

API Management (APIM) は、API の公開、管理、分析を行います。 APIM は、バックエンド サービスとマイクロサービスに安全にアクセスするための認証を処理します。 API ゲートウェイを使用して、API のデプロイ、キャッシュ、負荷分散をスケールアウトします。

API ごとに何度も呼び出すのではなく、適切なトークンを取得して、[Azure APIM API をセキュアにする](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/secure-api-management?tabs=app-reg-ga)ことをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/road-to-the-cloud-ad-minimization"} -->
## クラウドへの道 - 従来のオンプレミス Active Directory サービスへの依存関係を減らすためのケース スタディ - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-ad-minimization
- Service: entra / architecture
- Article date: 2026-01-04
- Summary: 従来のオンプレミス Active Directory サービスへの依存関係を減らすためのケース スタディについて説明します。

Active Directory の最小化とは、ID とデバイスの管理をクラウドベースのプラットフォームに移行することで、従来のオンプレミス Active Directory サービスへの依存関係を戦略的に削減することを指します。 この記事では、企業がセキュリティと運用効率を維持しながら Active Directory 最小化戦略を正常に実装した方法を示す、実際のケース スタディを示します。

### メリット

Active Directory の最小化には、次の利点があります。

- **インフラストラクチャ コストの削減:** オンプレミスの Active Directory インフラストラクチャのメンテナンス オーバーヘッドを削減する
- **セキュリティ強化:** 先進認証と条件付きアクセス ポリシー
- **ユーザー エクスペリエンスの向上:** 任意のデバイスからどこからでもリソースにシームレスにアクセス
- **管理の簡素化:** クラウド ベースのデバイスと ID の一元管理

### ケース スタディ

#### 1. 中外製薬: クラウドへのデバイス管理移行の完了

**組織：** 中外製薬株式会社**業界：** 製薬**ソリューション：** クラウド優先アプローチを使用した Windows Autopilot

**主なハイライト:**

- 従来のドメイン参加済みデバイスからクラウドマネージド エンドポイントへの移行
- 合理化されたデバイス プロビジョニング用に Windows Autopilot を実装しました
- エンタープライズ セキュリティ標準を維持しながら IT オーバーヘッドを削減
- 最新のデバイス管理でリモート作業機能を有効にしました

**リンク:**[Microsoft カスタマー ストーリー - 中外製薬](https://www.microsoft.com/en/customers/story/20037-chugai-pharmaceutical-windows-autopilot)

#### 2. NTTコミュニケーションズ株式会社 - ハイブリッドから100% クラウドへの移行

**組織：** NTTコミュニケーションズ株式会社**業界：** 通信**ソリューション：** 統合エンドポイント管理用の Microsoft Intune

**主なハイライト:**

- Active Directory の依存関係を減らすためにクラウドベースのデバイス管理を採用しました
- 包括的なエンドポイント保護のために Microsoft Intune を実装しました
- 組織全体のデバイス ライフサイクル管理の合理化
- IT 運用を簡素化しながらセキュリティ体制を強化

**リンク:**[Microsoft カスタマー ストーリー - NTT Communications](https://www.microsoft.com/en/customers/story/24348-ntt-communications-corporation-microsoft-intune)

#### 3. オンプレミス Active Directory の使用停止: We Are Era の移行の成功から学んだ教訓

**組織：** We Are Era

**主なハイライト:**

- Active Directory フットプリントを削減するための実用的なアプローチを示します
- 最新の認証パターンとゼロ トラストの原則について説明します
- クラウド ID とオンプレミス リソースの統合を示します
- Active Directory 最小化戦略に関する技術的なガイダンスを提供します

**リンク:**[YouTube テクニカル セッション](https://www.youtube.com/watch?v=GB-DFmxJcJQ&amp;t=1467s)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/road-to-the-cloud-establish"} -->
## クラウドへの道 - ID とアクセス管理を Active Directory から Microsoft Entra ID に移行するためのフットプリントを確立する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-establish
- Service: entra / architecture
- Article date: 2023-07-27
- Summary: Active Directory から Microsoft Entra ID への IAM の移行を計画する一環として、Microsoft Entra フットプリントを確立します。

ID とアクセス管理 (IAM) を Active Directory から Microsoft Entra ID に移行する前に、Microsoft Entra ID を設定する必要があります。

### 必須のタスク

Microsoft Office 365、Exchange Online、または Teams を使用している場合は、既に Microsoft Entra ID を使用しています。 次の手順では、Microsoft Entra の機能をさらに確立します。

- [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) または Microsoft Entra Connect クラウド同期を使用して、Active Directory と [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync) ID の間でハイブリッド ID 同期を確立します。
- [認証方法を選択します](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)。 パスワード ハッシュ同期を強くお勧めします。
- ID インフラストラクチャをセキュリティで [保護するための 5 つの手順に従って、ハイブリッド ID インフラストラクチャをセキュリティで保護](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity)します。

### 省略可能なタスク

次の関数は、Active Directory から Microsoft Entra ID への移行に固有または必須ではありませんが、環境に組み込むことをお勧めします。 これらの項目は、 [ゼロ トラスト](https://learn.microsoft.com/ja-jp/security/zero-trust/) ガイダンスでも推奨されます。

#### パスワードレス認証をデプロイする

[パスワードレス資格情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)のセキュリティ上の利点に加えて、パスワードレス認証は、管理と登録のエクスペリエンスが既にクラウドにネイティブであるため、環境を簡素化します。 Microsoft Entra ID は、さまざまなユース ケースに合わせたパスワードなしの資格情報を提供します。 この記事の情報を使用して展開を計画します。 [Microsoft Entra ID でパスワードレス認証の展開を計画します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-deployment)。

パスワードなしの資格情報をユーザーにロールアウトした後は、パスワード資格情報の使用を減らすことを検討してください。 [レポートと分析情報ダッシュボードを](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity)使用して、パスワードなしの資格情報の使用を引き続き推進し、Microsoft Entra ID でのパスワードの使用を減らすことができます。

Important

アプリケーションの検出中に、パスワードに関する依存関係または前提条件を持つアプリケーションが見つかる場合があります。 これらのアプリケーションのユーザーは、それらのアプリケーションが更新または移行されるまで、自分のパスワードにアクセスできる必要があります。

#### 既存の Windows クライアント用に Microsoft Entra ハイブリッド参加を構成する

既存の Active Directory に参加している Windows クライアントに対して Microsoft Entra ハイブリッド参加を構成して、 [共同管理](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/overview)、条件付きアクセス、Windows Hello for Business などのクラウドベースのセキュリティ機能を利用できます。 新しいデバイスは、Microsoft Entra ハイブリッド参加ではなく、Microsoft Entra に結合される必要があります。

詳細については、「 [Microsoft Entra ハイブリッド参加の実装を計画](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/road-to-the-cloud-implement"} -->
## クラウドへの道 - ID とアクセス管理を Active Directory Domain Services (AD DS) から Microsoft Entra ID に移行する場合に、クラウド優先のアプローチを実装する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-implement
- Service: entra / architecture
- Article date: 2025-07-30
- Summary: Active Directory Domain Services (AD DS) から Microsoft Entra ID への IAM の移行を計画する一環として、クラウド優先のアプローチを実装します。

これは主に、Active Directory Domain Services (AD DS) に新しい依存関係を追加し、IT ソリューションの新しい需要に対してクラウド優先のアプローチを実装して、可能な限り停止または制限するプロセスとポリシー主導のフェーズです。

この時点で、AD DS に新しい依存関係を追加する内部プロセスを特定することが重要です。 たとえば、ほとんどの組織には、新しいシナリオ/機能/ソリューションの実装前に従う必要がある変更管理プロセスがあります。 これらの変更承認プロセスが、以下を行うように更新されることを強くお勧めします。

- 提案された変更によって AD DS に新しい依存関係が追加されるかどうかを評価する手順を含めます。
- 可能な場合は、Microsoft Entra の代替手段を評価します。

### 属性

Microsoft Entra ID のユーザー属性を充実させて、より多くのユーザー属性を含めることができます。 リッチ ユーザー属性を必要とする一般的なシナリオの例を次に示します。

- アプリのプロビジョニング: アプリ プロビジョニングのデータ ソースは Microsoft Entra ID であり、必要なユーザー属性がそこに存在する必要があります。
- アプリケーションの認可: Microsoft Entra ID で発行されたトークンには、ユーザー属性から生成された要求を含めることができるため、アプリケーションはトークン内の要求に基づいて認可の決定を行うことができます。 また、 [カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)を介して外部データ ソースから取得される属性を含めることもできます。
- グループ メンバーシップの設定とメンテナンス: 動的メンバーシップ グループを使用すると、部署情報などのユーザー属性に基づいたグループの動的な設定が可能になります。

次の 2 つのリンクは、スキーマの変更に関するガイダンスを提供します。

- [Microsoft Entra スキーマとカスタム式について](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-attributes)
- [Microsoft Entra Connect によって同期される属性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized)

これらのリンクは、このトピックに関する詳細情報を提供しますが、スキーマの変更に固有のものではありません:

- [要求での Microsoft Entra スキーマ拡張属性の使用 - Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/schema-extensions)
- [Microsoft Entra ID のカスタム セキュリティ属性とは (プレビュー)](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)
- [アプリケーション プロビジョニングで Microsoft Entra 属性マッピングをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)
- [Microsoft Entra アプリにオプションの要求を提供する - Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)

- [スコープ フィルターを使用した属性ベースのアプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) または [Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) とは (アプリケーション アクセス用)

### グループ

グループに対するクラウドファーストのアプローチでは、クラウドに新しいグループを作成する必要があります。 オンプレミスで必要な場合は、 [Microsoft Entra Cloud Sync を使用して Active Directory Domain Services (AD DS) にグループをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)します。既存のオンプレミス グループのグループソース (SOA) を変換して、Microsoft Entra から管理します。

グループの詳細については、次のリンクを参照してください。

- [Microsoft Entra ID で動的グループを作成または編集し、状態を取得する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)
- [ユーザーが開始するグループ管理にセルフサービス グループを使用する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)
- [スコープ フィルターを使用した属性ベースのアプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)または [Microsoft Entra エンタイトルメント管理とは](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) (アプリケーション アクセスの場合)
- [グループの比較](https://learn.microsoft.com/ja-jp/microsoft-365/admin/create-groups/compare-groups)
- [Microsoft Entra ID でゲスト アクセス許可を制限する](https://learn.microsoft.com/ja-jp/entra/identity/users/users-restrict-guest-permissions)

### ユーザー

Active Directory へのアプリケーションの依存関係がないユーザーが組織内に存在する場合は、それらのユーザーを Microsoft Entra ID に直接プロビジョニングすることで、クラウド優先のアプローチを取ることができます。 Active Directory へのアクセスを必要としないが、Active Directory アカウントがプロビジョニングされているユーザーがいる場合は、その権限のソースを変更して、Active Directory アカウントをクリーンアップできます。

### デバイス

クライアント ワークステーションは、従来、Active Directory に参加し、グループ ポリシー オブジェクト (GPO) または Microsoft Configuration Manager などのデバイス管理ソリューション経由で管理されます。 チームは、新しく展開されたワークステーションが今後ドメインに参加しないようにするための新しいポリシーとプロセスを確立します。 重要なポイントは次のとおりです。

- 新しい Windows クライアント ワークステーションに対して [Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)を強制して、"ドメインへの参加をこれ以上しない" ようにします。
- [Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune) などの統合エンドポイント管理 (UEM) ソリューションを使用して、クラウドからワークステーションを管理します。

[Windows オートパイロット](https://learn.microsoft.com/ja-jp/autopilot/windows-autopilot)によって、合理化されたオンボードとデバイス プロビジョニングを確立することを支援することができ。これにより、これらのディレクティブを適用できます。

[Windows ローカル管理者パスワード ソリューション](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-manage-local-admin-passwords) (LAPS) を使用すると、クラウド優先のソリューションでローカル管理者アカウントのパスワードを管理できます。

詳細については、「[クラウドネイティブ エンドポイントの詳細](https://learn.microsoft.com/ja-jp/mem/solutions/cloud-native-endpoints/cloud-native-endpoints-overview)」を参照してください。

### アプリケーション

従来、アプリケーション サーバーでは、Windows 統合認証 (Kerberos または NTLM)、LDAP を介したディレクトリ クエリ、GPO または Microsoft Configuration Manager を通じたサーバー管理を利用できるように、オンプレミスの Active Directory ドメインに参加していることがよくあります。

新しいサービス、アプリ、インフラストラクチャを検討するとき、組織には Microsoft Entra の代替手段を評価するプロセスがあります。 アプリケーションに対するクラウド優先アプローチのディレクティブは、次のようになります。 (最新の代替手段が存在しない場合、新しいオンプレミス アプリケーションまたはレガシ アプリケーションは、まれな例外である必要があります)。

- 調達ポリシーとアプリケーション開発ポリシーを変更して、最新のプロトコル (OIDC/OAuth2 および SAML) を要求し、Microsoft Entra ID を使って認証するための推奨事項を提供します。 新しいアプリは、[Microsoft Entra アプリ プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)もサポートし、LDAP クエリに依存しない必要があります。 例外には、明示的な確認と承認が必要です。

    重要

    従来のプロトコルを必要とするアプリケーションの予想される需要に応じて、より新しい代替手段が機能しない場合に [Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview) をデプロイすることを選択できます。
- クラウド ネイティブの代替手段の使用に優先順位を付けるポリシーを作成するための推奨事項を提供します。 ポリシーでは、ドメインへの新しいアプリケーション サーバーのデプロイを制限する必要があります。 ドメイン メンバー サーバーを置き換えるクラウドネイティブの一般的なシナリオは次のとおりです。

    - ファイル サーバー :

        - SharePoint または OneDrive は、Microsoft 365 ソリューション全体のコラボレーション サポートと、組み込みのガバナンス、リスク、セキュリティ、コンプライアンスを提供します。
        - [Azure Files](https://learn.microsoft.com/ja-jp/azure/storage/files/storage-files-introduction) はクラウドで、業界標準の SMB プロトコルか NFS プロトコルを介してアクセスできる、フル マネージドのファイル共有を提供します。 お客様は、ドメイン コントローラーへの通信経路なしで [Azure Files へのネイティブの Microsoft Entra 認証](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/create-profile-container-azure-ad)をインターネット経由で使用できます。
        - Microsoft Entra ID は、Microsoft [アプリケーション ギャラリー](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/integrated-apps-and-azure-ads)内のサード パーティ製アプリケーションで動作します
    - プリント サーバー:

        - [ユニバーサル印刷](https://learn.microsoft.com/ja-jp/universal-print/)互換プリンターの調達が組織で義務付けられている場合は、[パートナー統合](https://learn.microsoft.com/ja-jp/universal-print/fundamentals/universal-print-partner-integrations)に関するページを参照してください。
        - 互換性のないプリンタ用の[ユニバーサル印刷コネクタ](https://learn.microsoft.com/ja-jp/universal-print/fundamentals/universal-print-connector-overview)付きブリッジ。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/road-to-the-cloud-introduction"} -->
## クラウドへの移行 - ID およびアクセス管理の AD から Microsoft Entra ID への移行の概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-introduction
- Service: entra / architecture
- Article date: 2023-07-27
- Summary: Active Directory から Microsoft Entra ID への IAM の移行を計画する方法について説明します。

組織は、オンプレミスの Active Directoryへの依存を減らし、Microsoft Entra IDでクラウドネイティブ機能を採用することで、ID、アクセス、およびデバイス管理をますます最新化しています。 目標が Active Directory の完全な廃止であっても、より小規模で安全性の高いオンプレミス環境への移行であっても、このガイダンスはその変革の計画と実行に役立ちます。

この記事では、次の移行に関するガイダンスを示します。

- *移行元* オンプレミスまたは IaaS (サービスとしてのインフラストラクチャ) で、ID 管理 (IDM)、ID およびアクセス管理 (IAM)、デバイス管理を提供する、Active Directory やその他の非クラウド ベースのサービス。
- IDM、IAM、デバイス管理用の Microsoft Entra ID とその他の Microsoft クラウド ネイティブ ソリューション*へ*。

注

このコンテンツで、*Active Directory* は、Windows Server Active Directory Domain Services のことを言います。

変革は、生産性の向上、コストと複雑さの削減、セキュリティ体制の強化などのビジネス目標に対応し、これを達成するものでなければなりません。 クラウドに移行する場合のコストと価値をより深く理解するには、[Microsoft Entra ID に関する Forrester TEI](https://www.microsoft.com/security/business/forrester-tei-study)、および[クラウドの経済性](https://azure.microsoft.com/overview/cloud-economics/)を参照してください。

### AD 最小化の取り組み

Active DirectoryからMicrosoft Entra IDへの移行は、5 つの段階を経て進行します。 各ステージでは、環境が移行中の場所を記述し、そのステージでの作業の主な焦点を説明する単一の戦略的フェーズと組み合わせています。 すべてのステージで、3 つの進行状況ストリーム (ユーザーとグループ、アプリケーション、デバイス) が進み、互いに独立して移動できます。

1. **クラウド接続 (識別):** 現在の ID、アプリケーション、デバイスのランドスケープを検出してベースラインを確立します。 Active Directory Domain Servicesは、クラウド ID がアタッチされている間は権限を持ち続けますが、まだプライマリではありません。 フォーカスは、可視性と、どのワークロードを移動する準備ができているかを決定することです。
2. **ハイブリッド (モダン化):** オンプレミス環境を維持しながら、ID の同期を超えて移動し、クラウド ID の使用を開始して、セキュリティ、回復性、ユーザー エクスペリエンスを強化します。 依存するアプリとサービスを特定し、条件付きアクセスやセルフサービス パスワード リセットなどの機能を有効にします。
3. **Cloud-First (導入):**Microsoft Entra IDを新しい投資の既定の機関として扱い、ID コントロール プレーンをクラウドに移行します。 新しいユーザー、グループ、アプリケーション、デバイスは、既定でクラウドネイティブとしてプロビジョニングされます。
4. **オンプレミス AD を最小化 (削減):**Active Directoryを既定の依存関係から例外に減らします。 従来のワークロードをクラウドの代替手段に置き換え、ID ライフサイクル ワークフローをMicrosoft Entra IDに移行することで、オンプレミスのフットプリントを積極的に縮小します。
5. **クラウドのみ (最適化):** 残りのオンプレミス ID 依存関係を削除し、完全なクラウドネイティブ サービスとして ID を運用します。 すべてのユーザー、グループ、デバイスはMicrosoft Entra IDで管理され、Active Directoryを使用停止にすることができます。 Active DirectoryからMicrosoft Entra IDへの移行は、5 つの段階を経て進行します。 各ステージでは、環境が移行中の場所を記述し、そのステージでの作業の主な焦点を説明する単一の戦略的フェーズと組み合わせています。 すべてのステージで、3 つの進行状況ストリーム (ユーザーとグループ、アプリケーション、デバイス) が進み、互いに独立して移動できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/road-to-the-cloud-migrate"} -->
## クラウドへの道 - ID とアクセス管理を Active Directory Domain Services (AD DS) から Microsoft Entra 移行ワークストリームに移動する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-migrate
- Service: entra / architecture
- Article date: 2025-07-30
- Summary: Active Directory Domain Services (AD DS) から Microsoft Entra ID への IAM の移行ワークストリームを計画する方法について説明します。

Active Directory Domain Services (AD DS) フットプリントの増加の停止に向けて組織を調整したら、既存のオンプレミス ワークロードを Microsoft Entra ID に移行することに集中できます。 この記事ではさまざまな移行ワークストリームについて説明します。 この記事のワークストリームは、ユーザーの優先順位とリソースに基づいて実行できます。

一般的な移行ワークストリームには、次のステージがあります。

- **検出**: 環境内に現在あるものを確認する。
- **パイロット**: 新しいクラウド機能をワークストリームに応じてユーザー、アプリケーション、またはデバイスの小さなサブセットにデプロイする。
- **スケールアウト**: パイロットを拡張して、クラウドへの機能の移行を完了する。
- **カットオーバー (該当する場合)**: 古いオンプレミス ワークロードの使用を停止する。

### ユーザーおよびグループ

#### パスワードの有効化 (セルフサービス)

Microsoft では、[パスワードレス環境](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)をお勧めしています。 それまでは、オンプレミスのシステムから Microsoft Entra ID にパスワードのセルフサービス ワークフローを移行して、環境を簡素化できます。 Microsoft Entra ID [セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks) を使用すると、管理者やヘルプ デスクが関与することなく、ユーザーが自分でパスワードを変更したり、リセットしたりできます。

セルフサービス機能を有効にするには、組織に適した[認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)を選択します。 認証方法が更新されたら、Microsoft Entra 認証環境に対してユーザーのセルフサービス パスワード機能を有効にできます。 デプロイのガイダンスについては、「[Microsoft Entra のセルフサービス パスワード リセットのデプロイに関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-deployment)」を参照してください。

追加の考慮事項には、次のものがあります。

- [監査](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-operations)モードを使用してドメイン コントローラーのサブセットに **Microsoft Entra パスワード保護**をデプロイして、最新ポリシーの影響に関する情報を収集します。
- [SSPR と Microsoft Entra MFA のための結合された登録を徐々に有効化します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)。 たとえば、すべてのユーザーを対象に、リージョン、子会社、または部門別にロールアウトします。
- 脆弱なパスワードを一掃するために、すべてのユーザーのパスワード変更サイクルを確認します。 サイクルが完了したら、ポリシーの有効期限を実装します。
- モードが **[強制]** に設定されているドメイン コントローラーのパスワード保護構成を切り替えます。 詳細については、「[オンプレミスの Microsoft Entra パスワード保護を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-operations)」を参照してください。

注

- スムーズなデプロイのために、ユーザーへのコミュニケーションと伝達を行うことをお勧めします。 [SSPR ロールアウトのサンプル資料](https://www.microsoft.com/download/details.aspx?id=56768)を参照してください。
- Microsoft Entra ID Protection を使用している場合は、リスクが高いとマークされているユーザーに対して、[条件付きアクセス ポリシーでコントロールとしてのパスワード リセット](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)を有効にします。

#### グループの管理を移動する

グループと配布リストを変換するには、次のようにします。

- AD DS で作成された既存のセキュリティ グループが、クラウド リソースへのアクセス権の付与にのみ使用されるようになった場合は、グループソースオブオーソリティ (SOA) 転送を使用して、それらのグループをクラウド専用にします。 クラウドからグループ メンバーシップを管理します。 次に、AD DS からグループを削除します。
- AD DS でまだフットプリントが必要な既存のセキュリティ グループが AD DS で作成されている場合は、AD グループのスコープをユニバーサルに更新します (まだない場合)。 次に、グループ SOA を使用して、これらのグループをクラウドで編集可能に変換し、クラウドからのメンバーシップを管理します。 最後に、AD DS へのグループ プロビジョニングを使用して、オンプレミスメンバーシップが同期されたままになるように、クラウドの変更を AD にプロビジョニングします。
- セキュリティ グループにユーザーを割り当てるビジネス ロジックの場合は、ロジックを Entra ID に移行して動的メンバーシップ グループを使用できるかどうかを評価します。
- Microsoft Identity Manager で提供される自己管理グループ機能の場合は、その機能をセルフサービス グループ管理に置き換えます。
- Outlook で[配布リストを Microsoft 365 グループに変換](https://learn.microsoft.com/ja-jp/microsoft-365/admin/create-groups/office-365-groups)できます。 これは、組織の配布リストに Microsoft 365 グループのすべての機能を与える優れたアプローチ方法です。
- [Outlook で配布リストを Microsoft 365 グループに](https://support.microsoft.com/office/7fb3d880-593b-4909-aafa-950dd50ce188)アップグレードします。

#### ユーザーとグループのプロビジョニングをアプリケーションに移動する

アプリケーションのプロビジョニング フローを Microsoft Identity Manager などのオンプレミスの ID 管理 (IDM) システムから削除して環境を簡素化できます。 アプリケーションの検出に基づき、次の特性に基づいてアプリケーションを分類します。

- [Microsoft Entra アプリケーション ギャラリー](https://www.microsoft.com/security/business/identity-access-management/integrated-apps-azure-ad)とのプロビジョニング統合が行われている、環境内のアプリケーション。
- ギャラリー内にはないが、SCIM 2.0 プロトコルをサポートするアプリケーション。 これらのアプリケーションは、Microsoft Entra クラウド プロビジョニング サービスとネイティブに互換性があります。
- ECMA コネクタを使用できるオンプレミス アプリケーション。 これらのアプリケーションは、[Microsoft Entra オンプレミス アプリケーションのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)と統合できます。

詳細については、「[Microsoft Entra ID の自動ユーザー プロビジョニングのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-auto-user-provisioning)」を参照してください。

#### クラウド人事プロビジョニングに移動する

人事プロビジョニング ワークフローをオンプレミスの IDM システム (Microsoft Identity Manager など) から Microsoft Entra ID に移動することによって、オンプレミスのフットプリントを削減できます。 Microsoft Entra クラウド人事プロビジョニングでは、次の 2 つのアカウントの種類を使用できます:

- Microsoft Entra ID を使用するアプリケーションのみを使用している新しい従業員の場合は、*クラウド専用アカウント*をプロビジョニングすることを選択できます。 このプロビジョニングは、AD DS のフットプリントを抑えるのに役立ちます。
- AD DS に依存するアプリケーションにアクセスする必要がある新しい従業員の場合は、 *ハイブリッド アカウントを*プロビジョニングできます。

Microsoft Entra クラウド人事プロビジョニングでは、既存の従業員の AD DS アカウントを管理することもできます。 詳細については、「[Microsoft Entra ユーザー プロビジョニングのためのクラウド人事アプリケーションの計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)」と「[デプロイ プロジェクトを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-auto-user-provisioning)」を参照してください。

#### ライフサイクル ワークフローを動かす

既存の joiner/mover/leaver ワークフローおよびプロセスを Microsoft Entra クラウド環境への適用性と妥当性評価に関して評価します。 その後、[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-lifecycle-workflow)を使用して、これらのワークフローを簡略化し、[新しいワークフローを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)できます。

#### 外部 ID 管理を移動する

組織が AD DS またはベンダー、請負業者、コンサルタントなどの外部 ID の他のオンプレミス ディレクトリにアカウントをプロビジョニングする場合は、クラウドでネイティブにこれらのサード パーティのユーザー オブジェクトを管理することで、環境を簡素化できます。 次のような可能性があります。

- 新しい外部ユーザーの場合は、ユーザーの AD DS フットプリントを停止する [Microsoft Entra 外部 ID を](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview)使用します。
- 外部 ID 用にプロビジョニングする既存の AD DS アカウントの場合、ローカル資格情報 (パスワードなど) を管理するオーバーヘッドを解消するには、企業間 (B2B) コラボレーション用に構成します。 「[内部ユーザーを B2B コラボレーションに招待する](https://learn.microsoft.com/ja-jp/entra/external-id/invite-internal-users)」の手順に従います。
- [Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用して、アプリケーションとリソースへのアクセス権を付与します。 ほとんどの企業にはこのための専用システムとワークフローがあり、それらをオンプレミスのツールから移動できるようになりました。
- [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-external-users)を使用して、不要になったアクセス権や外部 ID を削除します。

### デバイス

#### Windows 以外のワークステーションを移動する

Windows 以外のワークステーションを Microsoft Entra ID と統合して、ユーザー エクスペリエンスを強化し、条件付きアクセスなどのクラウドベースのセキュリティ機能を活用できます。

- macOS の場合:

    - macOS を Microsoft Entra ID に登録し、[モバイル デバイス管理ソリューションを使用して登録/管理](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/macos-enroll)します。
    - [Apple デバイス用の Microsoft Enterprise SSO (シングル サインオン) プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)をデプロイします。
    - [macOS 13 のプラットフォーム SSO](https://techcommunity.microsoft.com/t5/microsoft-intune-blog/microsoft-simplifies-endpoint-manager-enrollment-for-apple/ba-p/3570319) のデプロイを計画します。
- Linux の場合は、[Microsoft Entra 資格情報を使用して Linux 仮想マシン (VM) にサインイン](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-linux)できます。

#### ワークステーション用の他の Windows バージョンを置き換える

ワークステーションに次のオペレーティング システムがある場合は、クラウドネイティブ管理 (Microsoft Entra 参加と統合エンドポイント管理) を活用するために、最新バージョンにアップグレードすることを検討してください:

- Windows 7 または 8.x
- Windows Server

#### VDI ソリューション

このプロジェクトには、2 つの主要なイニシアチブがあります。

- **新しいデプロイ**: オンプレミスの AD DS を必要としない、Windows 365 や Azure Virtual Desktop などのクラウドで管理された仮想デスクトップ インフラストラクチャ (VDI) ソリューションをデプロイします。
- **既存のデプロイ**: 既存の VDI 展開が AD DS に依存している場合は、ビジネス目標と目標を使用して、ソリューションを維持するか、Microsoft Entra ID に移行するかを決定します。

詳細については、以下を参照してください:

- [Azure Virtual Desktop に Microsoft Entra に参加した VM をデプロイする](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/azure-ad-joined-session-hosts)
- [Windows 365 計画ガイド](https://learn.microsoft.com/ja-jp/windows-365/enterprise/planning-guide)

### アプリケーション

セキュリティで保護された環境を維持するために、Microsoft Entra ID では先進認証プロトコルがサポートされています。 アプリケーション認証を AD DS から Microsoft Entra ID に移行するには、次の操作を行う必要があります。

- 変更なしで Microsoft Entra ID に移行できるアプリケーションを判別する。
- アップグレードによって移行できるアップグレード パスがあるアプリケーションを判別する。
- 移行するために置換や重要なコード変更が必要なアプリケーションを判別する。

アプリケーション検出イニシアチブの結果は、アプリケーション ポートフォリオの移行のための優先順位付き一覧を作成することです。 この一覧には、次のアプリケーションが含まれています。

- ソフトウェアへのアップグレードまたは更新が必要であり、アップグレード パスが利用可能である。
- ソフトウェアへのアップグレードまたは更新が必要であるが、アップグレード パスが利用可能ではない。

一覧を使用して、既存のアップグレード パスがないアプリケーションをさらに評価できます。 ビジネス価値によってソフトウェアの更新が正当化されるかどうか、またはインベントリから削除するべきかどうかを判断します。 ソフトウェアをインベントリから削除するべきである場合は、置換が必要かどうかを判断します。

結果に基づいて、AD DS から Microsoft Entra ID への変換の側面を再設計できます。 サポートされていない認証プロトコルを使用するアプリケーションに対して、オンプレミスの AD DS を Azure サービスとしてのインフラストラクチャ (IaaS) (リフト アンド シフト) に拡張するために使用できる方法があります。 この方法を使用するには、例外を必要とするポリシーを設定することをお勧めします。

#### アプリケーションの検出

アプリ ポートフォリオをセグメント化したら、ビジネス価値とビジネスの優先順位に基づいて移行の優先順位を設定できます。 ツールを使用して、アプリ インベントリを作成または更新できます。

アプリを分類するには、次の 3 つの主な方法があります。

- **先進認証アプリ**: これらのアプリケーションでは、先進認証プロトコル (OIDC、OAuth2、SAML、WS-Federation など) が使用されるか、Active Directory フェデレーション サービス (AD FS) などのフェデレーション サービスが使用されます。
- **Web アクセス管理 (WAM) ツール**: これらのアプリケーションでは、SSO にヘッダー、Cookie、および同様の手法が使用されます。 これらのアプリには、通常、Symantec SiteMinder などの WAM ID プロバイダーが必要です。
- **レガシ アプリ**: これらのアプリケーションでは、Kerberos、LDAP、Radius、リモート デスクトップ、NTLM (推奨されない) などのレガシ プロトコルが使用されます。

Microsoft Entra ID を各種類のアプリケーションで使用することで、さまざまなレベルの機能を提供でき、これによってさまざまな移行戦略、複雑さ、トレードオフが生じます。 一部の組織には、検出ベースラインとして使用できるアプリケーション インベントリがあります。 (このインベントリは完了または更新されていないことが一般的です。)

先進認証アプリを検出するには、次のようにします。

- AD FS を使用している場合は、[AD FS アプリケーション アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-application-activity)を使用します。
- 別の ID プロバイダーを使用している場合は、ログと構成を使用します。

次のツールは、LDAP が使用されているアプリケーションを検出するのに役立ちます。

- [Event1644Reader](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/identity/event1644reader-analyze-ldap-query-performance): フィールド エンジニアリング ログを使用してドメイン コントローラーに対して行われた LDAP クエリに関するデータを収集するためのサンプル ツール。
- [Microsoft 365 Defender for Identity](https://learn.microsoft.com/ja-jp/defender-for-identity/monitored-activities): サインイン操作監視機能を使用するセキュリティ ソリューション。 (Secure LDAP ではなく LDAP を使用してバインドをキャプチャすることに注意してください。)
- [PSLDAPQueryLogging](https://github.com/RamblingCookieMonster/PSLDAPQueryLogging): LDAP クエリに関するレポートを作成するための GitHub ツール。

#### AD FS またはその他のフェデレーション サービスを移行する

Microsoft Entra ID への移行を計画する場合は、先進認証プロトコル (SAML や OpenID Connect など) を使用するアプリを最初に移行することを検討してください。 Azure アプリ ギャラリーの組み込みコネクタを使用するか、Microsoft Entra ID での登録を使用して、Microsoft Entra ID で認証を行うようにこれらのアプリを再構成できます。

Microsoft Entra ID にフェデレーションされていた SaaS アプリケーションを移動したら、オンプレミスのフェデレーション システムの使用を停止するための手順がいくつかあります:

- [アプリケーション認証を Microsoft Entra ID に移動する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)
- [Azure 多要素認証サーバー (MFA サーバー) から Microsoft Entra 多要素認証に移行する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-azure-mfa)
- [フェデレーションからクラウド認証に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication)
- Microsoft Entra アプリケーション プロキシを使用している場合にリモート アクセスを内部アプリケーションに移動する

重要

他の機能を使用している場合は、AD FS の使用を停止する前に、それらのサービスが再配置されていることを確認してください。

#### WAM 認証アプリを移動する

このプロジェクトでは、SSO 機能を WAM システムから Microsoft Entra ID に移行することに重点を置きます。 詳細については、[Symantec SiteMinder から Microsoft Entra ID へのアプリケーションの移行](https://azure.microsoft.com/resources/migrating-applications-from-symantec-siteminder-to-azure-active-directory/)に関する記事を参照してください。

#### アプリケーション サーバー管理戦略を定義する

インフラストラクチャ管理の観点から、オンプレミス環境では、管理業務を分割するために、多くの場合、グループ ポリシー オブジェクト (GPO) と Microsoft Configuration Manager 機能が組み合わせて使用されます。 たとえば、業務は、セキュリティ ポリシーの管理、更新管理、構成管理、監視に分割できます。

AD DS はオンプレミスの IT 環境用であり、Microsoft Entra ID はクラウドベースの IT 環境用です。 機能の 1 対 1 のパリティはここには存在しないため、アプリケーション サーバーをいくつかの方法で管理できます。

たとえば、Azure Arc は、ID とアクセス管理 (IAM) に Microsoft Entra ID を使用する場合に、AD DS に存在する多くの機能を 1 つのビューにまとめるのに役立ちます。 Microsoft Entra Domain Services を使用して、特に特定のビジネス上または技術的な理由で GPO を使用する場合に、Microsoft Entra ID でサーバーをドメインに参加させることができます。

次の表を使用して、オンプレミス環境を置き換えるために使用できる Azure ベースのツールを判別してください。

| 管理領域 | オンプレミス (AD DS) 機能 | 同等の Microsoft Entra 機能 |
| --- | --- | --- |
| セキュリティ ポリシーの管理 | GPO、Microsoft Configuration Manager | [Microsoft 365 Defender for Cloud](https://azure.microsoft.com/services/security-center/) |
| 更新管理 | Microsoft Configuration Manager、Windows Server Update Services | [Azure Update Manager](https://learn.microsoft.com/ja-jp/azure/update-manager/overview) |
| 構成管理 | GPO、Microsoft Configuration Manager | [Azure マシン構成](https://learn.microsoft.com/ja-jp/azure/governance/machine-configuration/overview) |
| 監視 | System Center Operations Manager | [Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/fundamentals/overview) |

アプリケーション サーバー管理に使用できる詳細情報を次に示します。

- [Azure Arc](https://azure.microsoft.com/services/azure-arc/) を使用すると、Azure 以外の VM に対して Azure 機能が有効になります。 たとえば、オンプレミスまたはアマゾン ウェブ サービスで使用されている Windows Server の Azure 機能を取得するために使用できたり、[SSH で Linux マシンを認証する](https://learn.microsoft.com/ja-jp/azure/azure-arc/servers/ssh-arc-overview?tabs=azure-cli)ために使用できたりします。
- [Azure VM 環境を管理してセキュリティで保護します](https://azure.microsoft.com/services/virtual-machines/secure-well-managed-iaas/)。
- 移行または部分的移行の実行を待つ必要がある場合は、[Microsoft Entra Domain Services](https://azure.microsoft.com/services/active-directory-ds/) で GPO を使用できます。

Microsoft Configuration Manager を使用したアプリケーション サーバーの管理を必要とする場合、Microsoft Entra Domain Services を使用してこの要件を満たすことはできません。 Microsoft Configuration Manager を Microsoft Entra Domain Services 環境で実行することはサポートされていません。 代わりに、オンプレミスの AD DS インスタンスを、Azure VM で実行されているドメイン コントローラーに拡張する必要があります。 または、新しい AD DS インスタンスを Azure IaaS 仮想ネットワークにデプロイする必要があります。

#### レガシ アプリケーションの移行戦略を定義する

レガシ アプリケーションには、次のような AD DS への依存関係があります。

- ユーザー認証と承認: Kerberos、NTLM、LDAP バインド、ACL。
- ディレクトリ データへのアクセス: LDAP クエリ、スキーマ拡張機能、ディレクトリ オブジェクトの読み取り/書き込み。
- サーバー管理: サーバー管理戦略によって決定される。

これらの依存関係を減らすかまたは排除するには、3 つの主な方法があります。

##### 方法 1

最も推奨される方法では、レガシ アプリケーションから、先進認証が使用されている SaaS の代替手段に移行するプロジェクトに着手します。 SaaS の代替手段をMicrosoft Entra ID に対して直接認証します:

1. Microsoft Entra Domain Services を Azure 仮想ネットワークにデプロイし、アプリケーションに必要な追加の属性を組み込むように[スキーマを拡張](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-custom-attributes)します。
2. Microsoft Entra Domain Services にドメイン参加済みの Azure 仮想ネットワーク上の VM にレガシ アプリをリフト アンド シフトします。
3. Microsoft Entra アプリケーション プロキシまたは[安全なハイブリッド アクセス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access) パートナーを使用して、レガシ アプリをクラウドに発行します。
4. レガシ アプリが使用されなくなって廃止されたら、最終的に Azure 仮想ネットワークで実行されている Microsoft Entra Domain Services の使用を停止します。

注

- 依存関係が [Microsoft Entra Domain Services の一般的なデプロイ シナリオ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/scenarios)と一致している場合は、Microsoft Entra Domain Services を使用します。
- Microsoft Entra Domain Services が適切かどうかを検証するには、Azure Monitor VM 分析情報 [https://learn.microsoft.com/azure/azure-monitor/vm/vminsights-overview] などのツールを使用できます。
- SQL Server のインスタンス化を[別のドメインに移行](https://social.technet.microsoft.com/wiki/contents/articles/24960.migrating-sql-server-to-new-domain.aspx)できることを確認します。 SQL サービスが仮想マシンで実行されている場合は、[このガイダンスを使用](https://learn.microsoft.com/ja-jp/azure/azure-sql/migration-guides/virtual-machines/sql-server-to-sql-on-azure-vm-individual-databases-guide)してください。

##### 方法 2

最初のアプローチが不可能で、アプリケーションが AD DS に強い依存関係を持っている場合は、オンプレミスの AD DS を Azure IaaS に拡張できます。

最新のサーバーレス ホスティングをサポートするようにリプラットフォームできます。たとえば、サービスとしてのプラットフォーム (PaaS) を使用します。 または、先進認証をサポートするようにコードを更新できます。 アプリを Microsoft Entra ID と直接統合できるようにすることもできます。 [Microsoft ID プラットフォームの Microsoft 認証ライブラリについて説明します](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview)。

1. 仮想プライベート ネットワーク (VPN) または Azure ExpressRoute 経由で Azure 仮想ネットワークをオンプレミス ネットワークに接続します。
2. オンプレミスの AD DS インスタンスの新しいドメイン コントローラーを仮想マシンとして Azure 仮想ネットワークにデプロイします。
3. ドメインに参加している Azure 仮想ネットワーク上の VM にレガシ アプリをリフト アンド シフトします。
4. Microsoft Entra アプリケーション プロキシまたは[安全なハイブリッド アクセス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access) パートナーを使用して、レガシ アプリをクラウドに発行します。
5. 最終的に、オンプレミスの AD DS インフラストラクチャの使用を停止し、Azure 仮想ネットワークで AD DS を完全に実行します。
6. レガシ アプリは削除によって廃止されるため、最終的には Azure 仮想ネットワークで実行されている AD DS インスタンスの使用を停止します。

##### 方法 3

最初の移行が不可能で、アプリケーションが AD DS に強い依存関係を持っている場合は、新しい AD DS インスタンスを Azure IaaS にデプロイできます。 予見可能な将来にわたってアプリケーションをレガシ アプリケーションのままにするか、または機会があったらアプリケーションの使用を停止します。

この方法では、アプリを既存の AD DS インスタンスから切り離して、領域を減らすことができます。 これは最後の手段としてのみ考えることをお勧めします。

1. 新しい AD DS インスタンスを仮想マシンとして Azure 仮想ネットワークにデプロイします。
2. 新しい AD DS インスタンスにドメイン参加している Azure 仮想ネットワーク上の VM にレガシ アプリをリフト アンド シフトします。
3. Microsoft Entra アプリケーション プロキシまたは[安全なハイブリッド アクセス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access) パートナーを使用して、レガシ アプリをクラウドに発行します。
4. レガシ アプリは削除によって廃止されるため、最終的には Azure 仮想ネットワークで実行されている AD DS インスタンスの使用を停止します。

##### 戦略の比較

| 戦略 | Microsoft Entra Domain Services | AD DS を IaaS に拡張する | IaaS の独立した AD DS インスタンス |
| --- | --- | --- | --- |
| オンプレミス AD DS からの切り離し | はい | いいえ | はい |
| スキーマの拡張機能の許可 | いいえ | はい | はい |
| 完全な管理制御 | いいえ | はい | はい |
| 必要なアプリの再構成の可能性 (ACL や承認など) | はい | いいえ | はい |

#### VPN 認証を移行する

このプロジェクトでは、VPN 認証を Microsoft Entra ID に移行することに重点を置きます。 VPN ゲートウェイ接続ではさまざまな構成が利用できることを理解しておくことが重要です。 また、どの構成が自分のニーズに最適かを判断する必要があります。 ソリューションの設計の詳細については、「[VPN Gateway の設計](https://learn.microsoft.com/ja-jp/azure/vpn-gateway/design)」を参照してください。

VPN 認証に Microsoft Entra ID を使用することに関する重要なポイントは、次のとおりです:

- VPN プロバイダーで先進認証がサポートされているかどうかを確認します。 次に例を示します。

    - [チュートリアル: Microsoft Entra SSO と Cisco Secure Firewall - Secure Client の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-secure-firewall-secure-client)
    - [チュートリアル: Microsoft Entra SSO と Palo Alto Networks GlobalProtect の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/palo-alto-networks-globalprotect-tutorial)
- Windows 10 デバイスの場合は、[組み込みの VPN クライアントに Microsoft Entra サポート](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-access/how-to-aovpn-conditional-access)を統合することを検討してください。
- このシナリオを評価した後、VPN 認証を行うためのオンプレミスへの依存関係を削除するソリューションを実装できます。

#### リモート アクセスを内部アプリケーションに移動する

環境を簡素化するために、[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)または[安全なハイブリッド アクセス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access) パートナーを使用してリモート アクセスを提供できます。 これにより、オンプレミスのリバース プロキシへの依存関係ソリューションを削除できます。

上記のテクノロジを使用してアプリケーションへのリモート アクセスを有効にすることは、中間の手順であることを理解しておくことが重要です。 アプリケーションを AD DS から完全に切り離すには、さらに多くの作業を行う必要があります。

Microsoft Entra Domain Services を使用すると、Microsoft Entra アプリケーション プロキシを使用してリモート アクセスを有効にしながら、アプリケーション サーバーをクラウド IaaS に移行し、AD DS から切り離すことができます。 このシナリオの詳細については、「[Microsoft Entra Domain Services 用に Microsoft Entra アプリケーション プロキシをデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/deploy-azure-app-proxy)」をチェックしてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/road-to-the-cloud-posture"} -->
## クラウドへの移行 - ID およびアクセス管理を Active Directory から Microsoft Entra ID に移行する場合にクラウドへの変革の体制を決定する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-posture
- Service: entra / architecture
- Article date: 2023-07-27
- Summary: IAM を Active Directory から Microsoft Entra ID に移行する計画を立てるときに、クラウドへの変革の体制を決定します。

Active Directory、Microsoft Entra ID、およびその他の Microsoft ツールは、ID およびアクセス管理 (IAM) の中核です。 たとえば、Active Directory Domain Services (AD DS) と Microsoft Configuration Manager では、Active Directory でのデバイス管理を提供します。 Microsoft Entra ID では、Intune は同じ機能を提供します。

組織では、ほとんどの最新化、移行、またはゼロトラストのイニシアチブの一環として、IAM のアクティビティを、オンプレミスまたはサービスとしてのインフラストラクチャ (IaaS) ソリューションの使用からクラウド向けに構築されたソリューションの使用に移行します。 Microsoft の製品とサービスを使用する IT 環境の場合は、Active Directory と Microsoft Entra ID が関与します。

Active Directory から Microsoft Entra ID に移行する企業の多くは、次の図のような環境から着手します。 この図では 3 つの中心的な要素が重なっています。

- **アプリケーション**: アプリケーション、リソース、それらの基盤であるドメイン参加済みサーバーが含まれます。
- **デバイス**: ドメイン参加済みクライアント デバイスが中心になっています。
- **ユーザーとグループ**: ガバナンスとポリシー作成のためのリソース アクセスとグループ メンバーシップの人間とワークロードの ID と属性を表します。

[Image: Architectural diagram that depicts the common technologies contained in the pillars of applications, devices, and users.]

Microsoft は、お客様のビジネス目標に広く対応する変革の 5 つの状態をモデル化しました。 お客様の目標が成熟するにつれて、それぞれのリソースとカルチャに適したペースで、ある状態から次の状態に移行するのが一般的です。

この 5 つの状態には、環境が現在どの場所にあるかを特定するための終了基準があります。 アプリケーションの移行のような一部のプロジェクトでは、5 つの状態をすべて経過します。 他のプロジェクトでは、経過する状態は 1 つです。

次に、このコンテンツでは、人員、プロセス、テクノロジを意図的に変更するのに役立つように編成された詳細なガイダンスを提供します。 このガイダンスは、次の場合に役立ちます。

- Microsoft Entra フットプリントを確立します。
- クラウド優先のアプローチを実装する。
- Active Directory 環境からの移行を開始する。

ガイダンスは、前出の中心的な要素に従って、ユーザー管理、デバイス管理、アプリケーション管理別に編成されています。

Active Directory ではなく Microsoft Entra ID で形成された組織には、確立している組織が対応する必要があるレガシ オンプレミス環境がありません。 このように IT 環境をクラウドで全面的に再構築するお客様は、新しい IT 環境が確立された時点で 100% のクラウド化を実現できます。

オンプレミスの IT 機能が確立しているお客様の場合は、変革のプロセスが複雑になるため、慎重な計画が必要です。 Active Directory と Microsoft Entra ID は異なる IT 環境を対象とする別個の製品であるため、同等の機能はありません。 たとえば、Microsoft Entra ID には Active Directory のドメインとフォレストの信頼という概念がありません。

### 変革の 5 つの状態

エンタープライズ規模の組織では一般に、IAM の変革に、または Active Directory から Microsoft Entra ID への変革にも、複数の状態で複数年かかります。 お客様は、自身の環境を分析して現在の状態を判断し、次の状態の目標を設定します。 Active Directory を完全に不要にすることを目標にする場合も、一部の機能を Microsoft Entra ID に移行しないでそのままにする場合も考えられます。

状態により、変革の完了に向けてイニシアチブがプロジェクトに論理的にグループ化されます。 状態が遷移する間、暫定的なソリューションが導入されます。 暫定的なソリューションにより、Active Directory と Microsoft Entra ID の両方での IAM 運用を IT 環境でサポートできます。 また暫定的なソリューションでは、2 つの環境を相互運用できるようにする必要があります。

次の図は 5 つの状態を示しています。

[Image: Diagram that shows five network architectures: cloud attached, hybrid, cloud first, Active Directory minimized, and 100% cloud.]

Note

この図の状態は、クラウド変換の論理的な進行を表しています。 ある状態から次の状態に移行できるかどうかは、実装した機能と、その機能内のクラウドへの移行機能によって異なります。

#### 状態 1: クラウドに接続

クラウドに接続状態では、組織がユーザーの生産性とコラボレーションのツールを有効にするために Microsoft Entra ID テナントを作成しています。 テナントは完全に機能しています。

IT 環境で Microsoft の製品とサービスを使用している企業のほとんどが、既にこの状態にあるか、この状態を過ぎています。 この状態では、オンプレミス環境とクラウド環境を維持してインタラクティブにする必要があるため、運用コストが高くなる可能性があります。 ユーザーと組織をサポートする担当者に、両方の環境に関する専門知識が必要です。

この状態の特徴は次のとおりです。

- デバイスは Active Directory に参加していて、グループ ポリシーまたはオンプレミスのデバイス管理ツールを使用して管理されます。
- ユーザーは、Active Directory 内で管理され、オンプレミスの ID 管理 (IDM) システム経由でプロビジョニングされ、Microsoft Entra Connect 経由で Microsoft Entra ID に同期されます。
- アプリは、Web アクセス管理 (WAM) ツール、Microsoft 365、または SiteMinder や Oracle Access Manager などの他のツール経由で、Active Directory、および Active Directory フェデレーション サービス (AD FS) などのフェデレーション サーバーに対して認証されます。

#### 状態 2: ハイブリッド

ハイブリッド状態では、組織がクラウドの機能経由でオンプレミス環境の強化を開始します。 このソリューションでは、複雑さを軽減し、セキュリティ体制を強化し、オンプレミス環境のフットプリントを削減する計画を立てることができます。

遷移とこの状態での運用中に、組織は IAM ソリューションに Microsoft Entra ID を使用するスキルと専門知識を養成します。 ユーザー アカウントとデバイスの接続が比較的簡単で、かつ日常的な IT 運用の一部であるため、ほとんどの組織がこのアプローチを使用してきました。

この状態の特徴は次のとおりです。

- Windows クライアントは Microsoft Entra ハイブリッド参加済みです。
- サービスとしてのソフトウェア (SaaS) に基づく Microsoft 以外のプラットフォームが、Microsoft Entra ID との統合を開始します。 たとえば、Salesforce と ServiceNow です。
- レガシ アプリは、アプリケーション プロキシまたはセキュリティで保護されたハイブリッド アクセスを提供するパートナー ソリューション経由で Microsoft Entra ID に対して認証されます。
- ユーザー向けのセルフサービス パスワード リセット (SSPR) とパスワード保護が有効になっています。
- 一部のレガシ アプリは、Microsoft Entra Domain Services とアプリケーション プロキシ経由でクラウドで認証されます。

#### 状態 3: クラウド優先

クラウド優先状態では、組織全体にわたってチームが成功実績を積み、より困難なワークロードを Microsoft Entra ID に移行する計画を開始します。 一般に、組織は変革のこの状態に最も多くの時間を費やします。 時間の経過にともなって、複雑さ、ワークロードの数、および Active Directory の使用が増加するにつれて、組織は、クラウドに移行するための労力とそのイニシアチブの数を増やす必要があります。

この状態の特徴は次のとおりです。

- 新しい Windows クライアントが Microsoft Entra ID に参加し、Intune で管理されます。
- オンプレミス アプリに対するユーザーとグループのプロビジョニングには、ECMA コネクタが使用されます。
- 前に AD FS などの AD DS 統合フェデレーション ID プロバイダーを使用していたすべてのアプリが、認証に Microsoft Entra ID を使用するように更新されます。 Microsoft Entra ID に対して、この ID プロバイダーを経由するパスワードベースの認証を使用した場合は、パスワード ハッシュ同期に移行されます。
- ファイル サービスと印刷サービスを Microsoft Entra ID に移行する計画が策定されます。
- Microsoft Entra ID は、企業間 (B2B) コラボレーション機能を提供します。
- 新しいグループは Microsoft Entra ID で作成および管理されます。

#### 状態 4: Active Directory 最小化

ほとんどの IAM 機能は Microsoft Entra ID で提供されますが、エッジ ケースと例外には、引き続きオンプレミスの Active Directory が使用されます。 Active Directory を最小限に抑える状態は、特に大きなオンプレミスの技術的負債を抱える大規模組織では、実現がより困難です。

組織の変革が成熟するにつれて Microsoft Entra ID が引き続き進化し、新しい機能やツールを利用できるようになります。 組織は、機能を廃止したり、置き換えるための新しい機能を構築したりする必要があります。

この状態の特徴は次のとおりです。

- HR プロビジョニング機能経由でプロビジョニングされた新しいユーザーが Microsoft Entra ID で直接作成されます。
- Active Directory に依存していて、将来の Microsoft Entra ID 環境の構想に含まれているアプリを移行する計画が実行されます。 移行しないサービス (ファイル、印刷、FAX の各サービス) を置き換える計画が策定されます。
- オンプレミスのワークロードがクラウドの代替手段 (Windows Virtual Desktop、Azure Files、Universal Print など) に置き換えられました。 SQL Server が、Azure SQL Managed Instance に置き換えられます。

#### 状態 5: 100% クラウド

100% クラウド状態では、Microsoft Entra ID やその他の Azure ツールによってすべての IAM 機能が提供されます。 この状態は多くの組織にとって長期的な目標です。

この状態の特徴は次のとおりです。

- オンプレミスの IAM フットプリントは必要ありません。
- すべてのデバイスは、Microsoft Entra ID とクラウド ソリューション (Intune など) で管理されます。
- ユーザー ID のライフサイクルは Microsoft Entra ID 経由で管理されます。
- ユーザーとグループはすべてクラウド ネイティブです。
- Active Directory に依存するネットワーク サービスは再配置されます。

### 変革の比喩

状態間の変革は、場所を移動することに似ています。

1. **新しい場所を確立する**: 移動先となる場所を購入し、現在の場所と新しい場所との間の接続を確立します。 これらのアクティビティにより、生産性と運用能力を維持できます。 詳細については、「[Microsoft Entra フットプリントを確立する](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-establish)」をご覧ください。 この結果、状態 2 に遷移します。
2. **前の場所で新しいアイテムを制限する**: 前の場所への投資を停止し、新しいアイテムを新しい場所でステージングするポリシーを設定します。 詳細については、「[クラウド優先のアプローチを実装する](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-implement)」を参照してください。 これらのアクティビティにより、大規模な移行の基盤が設定され、状態 3 に到達します。
3. **既存のアイテムを新しい場所に移動する**: 前の場所から新しい場所にアイテムを移動します。 アイテムのビジネス価値を評価して、そのまま移動するか、アップグレードするか、置き換えるか、廃止するかを決定します。 詳細については、「[クラウドに移行する](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-migrate)」を参照してください。

    これらのアクティビティにより、状態 3 を完了して状態 4 と 5 に到達できます。 事業目標に基づいて、ターゲットとする終了状態を決定します。

クラウドへの変革は、ID チームだけの責任ではありません。 組織は、チーム間で調整を行って、テクノロジと合わせて、人やプロセスの変更を含むポリシーを定義する必要があります。 調整されたアプローチを使用することで、一貫した進歩を確保でき、オンプレミス ソリューションに回帰するリスクを軽減できます。 関係するチームは次のとおりです。

- デバイス/エンドポイント
- ネットワーク
- セキュリティ/リスク
- アプリケーションの所有者
- 人事管理
- コラボレーション
- 調達
- Operations

#### おおまかな工程

組織は、IAM の Microsoft Entra ID への移行の開始時に、具体的なニーズに基づいて作業の優先順位を決定する必要があります。 運用スタッフとサポート スタッフが新しい環境で業務を実行できるように、トレーニングする必要があります。 次のチャートに、Active Directory から Microsoft Entra ID への移行のおおまかな工程を示します:

[Image: Chart that shows three major milestones in migrating from Active Directory to Microsoft Entra ID: establish Microsoft Entra capabilities, implement a cloud-first approach, and move workloads to the cloud.]

- **Microsoft Entra フットプリントを確立する**: 新しい Microsoft Entra テナントを初期化して、最終状態のデプロイのビジョンをサポートします。 工程の早期段階で、[ゼロ トラスト](https://www.microsoft.com/security/blog/2020/04/30/zero-trust-deployment-guide-azure-active-directory/) アプローチと、[オンプレミスの侵害からテナントを保護するのに役立つ](https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks)セキュリティ モデルを採用します。
- **クラウド優先のアプローチを実装する**: 新しいデバイス、アプリ、サービスのすべてをクラウド優先にするポリシーを確立します。 従来のプロトコル (NTLM、Kerberos、LDAP など) を使用する新しいアプリケーションとサービスは、例外のみにする必要があります。
- **クラウドに移行する**:ユーザー、アプリ、デバイスの管理と統合を、オンプレミスからクラウド優先の代替手段に移行します。 Microsoft Entra ID と統合された[クラウド優先のプロビジョニング機能](https://learn.microsoft.com/ja-jp/entra/id-governance/what-is-provisioning)を利用して、ユーザーのプロビジョニングを最適化します。

この変革によって、ユーザーがタスクを実行する方法やサポート チームがユーザーをサポートする方法が変わります。 組織は、ユーザーの生産性への影響を最小限に抑える方法でイニシアチブまたはプロジェクトを設計および実装する必要があります。

変革の一環として、組織はセルフサービスの IAM 機能を導入します。 一部の部署では、クラウドベースの企業で普及しているセルフサービスのユーザー環境に簡単に順応します。

古くなったアプリケーションをクラウドベースの IT 環境で適切に運用するために、更新や置換が必要になる場合があります。 アプリケーションの更新や置換にはコストと時間がかかります。 計画などのステージでは、組織のアプリケーションの経過年数や機能も考慮する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-best-practices"} -->
## Microsoft Entra ID でセキュリティ保護するためのベスト プラクティス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-best-practices
- Service: entra / architecture
- Article date: 2025-05-21
- Summary: Microsoft Entra ID 内の分離された環境をセキュリティで保護するために従うことが推奨されるベスト プラクティス。

すべての分離構成に関する設計上の考慮事項を次に示します。 このコンテンツの全体を通して、多くのリンクがあります。 ここでは内容を複製するのではなく、コンテンツにリンクするようにしているため、常に最新の情報にアクセスできます。

Microsoft Entra テナント (分離されているかどうかにかかわらず) を構成する方法に関する一般的なガイダンスについては、[Microsoft Entra 機能のデプロイ ガイド](https://learn.microsoft.com/ja-jp/entra/fundamentals/configure-security)を参照してください。

注

すべての分離されたテナントで、明確でかつ差別化されたブランド化を使用して、間違ったテナントでの作業の人的エラーの回避に役立てることをお勧めします。

### 分離セキュリティの原則

分離された環境を設計する場合は、次の原則を考慮することが重要です。

- **先進認証のみを使用する** - 分離された環境にデプロイされたアプリケーションでは、フェデレーション、Microsoft Entra B2B コラボレーション、委任、同意フレームワークなどの機能を使用するために、要求ベースの先進認証 (SAML、\* 認証、OAuth2、OpenID Connect など) を使用する必要があります。 これにより、NT LAN Manager (NTLM) などのレガシ認証方法に依存するレガシ アプリケーションが分離された環境に引き継がれなくなります。
- **強力な認証を適用する** - 分離された環境サービスやインフラストラクチャにアクセスするときは、常に、強力な認証を使用する必要があります。 可能な場合は常に、[パスワードレス認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) ([Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-overview) や [FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)など) を使用する必要があります。
- **セキュリティで保護されたワークステーションをデプロイする** - [セキュリティで保護されたワークステーション](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-devices)には、プラットフォームとそのプラットフォームが表す ID が適切に証明され、悪用から保護されることを保証するためのメカニズムが用意されています。 考慮すべきその他の 2 つのアプローチは次のとおりです。

    - Microsoft Graph API で Windows 365 クラウド PC (クラウド PC) を使用します。
    - [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)を使用し、条件としてデバイスをフィルター処理します。
- **従来の信頼メカニズムを排除する** - 分離されたディレクトリやサービスでは、Active Directory の信頼などの従来のメカニズムを使用して他の環境との信頼関係を確立すべきではありません。 環境間のすべての信頼を、フェデレーションや要求ベースの ID などの最新の構造を使用して確立する必要があります。
- **サービスを分離する** - 基になる ID とサービス インフラストラクチャを露出から保護することによって、攻撃対象領域を最小限に抑えます。 サービスに対する先進認証を通してのみアクセスを有効にし、インフラストラクチャへのリモート アクセスをセキュリティで保護します (先進認証でも保護します)。
- **ディレクトリ レベルのロールの割り当て** - ディレクトリ レベルのロールの割り当て (AU スコープではなく、ディレクトリ スコープに基づくユーザー管理者) や、コントロール プレーン アクションを使用したサービス固有のディレクトリ ロール (セキュリティ グループ メンバーシップを管理するためのアクセス許可を持つ知識管理者) を回避するか、またはその数を減らします。

[Microsoft Entra 一般的な運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-ops)のガイダンスに加えて、分離された環境に関する次の考慮事項もお勧めします。

### 人間の ID のプロビジョニング

#### 特権アカウント

環境を運用する管理担当者と IT チームのために、分離された環境でアカウントをプロビジョニングします。 これにより、[セキュリティで保護されたワークステーション](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-deployment)に対するデバイス ベースのアクセス制御などの、より強力なセキュリティ ポリシーを追加できます。 前のセクションで説明したように、非運用環境では Microsoft Entra B2B コラボレーションを利用して、その運用環境内の特権アクセス用に設計された同じ体制およびセキュリティ制御を使用して特権アカウントを非運用テナントにオンボードする場合があります。

クラウドのみのアカウントは、Microsoft Entra テナントで人間の ID をプロビジョニングするための最も簡単な方法であり、グリーン フィールド環境に最適です。 ただし、分離された環境に対応する既存のオンプレミス インフラストラクチャ (実稼働前または管理 Active Directory フォレストなど) が存在する場合は、そこから ID を同期することも検討できます。 これは、特に、前述のオンプレミス インフラストラクチャが、ソリューション データ プレーンを管理するためにサーバー アクセスが必要な IaaS ソリューションにも使用される場合に当てはまります。 このシナリオの詳細については、「[オンプレミスの攻撃から Microsoft 365 を保護する](https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks)」を参照してください。 分離されたオンプレミス環境からの同期は、スマート カードのみの認証などの、特定の規制コンプライアンス要件がある場合にも必要になります。

注

Microsoft Entra B2B アカウントの ID 証明を行うための技術的な制御はありません。 Microsoft Entra B2B でプロビジョニングされた外部 ID は 1 つの要因でブートストラップされます。 この軽減策は、B2B 招待が発行される前に、組織で必要な ID を証明するためのプロセスを用意することと、ライフサイクルを管理するための外部 ID の定期的なアクセス レビューです。 MFA 登録を制御するために、条件付きアクセス ポリシーを有効にすることを検討してください。

#### リスクの高いロールのアウトソーシング

内部の脅威を軽減するために、Azure B2B コラボレーションを使うか、CSP パートナーまたは Lighthouse 経由でアクセスを委任して、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)と[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)のロールへのアクセスをマネージド サービス プロバイダーにアウトソーシングできます。 このアクセスは、Azure Privileged Identity Management (PIM) の承認フローを使用して、社内スタッフが制御できます。 このアプローチにより、内部の脅威を大幅に軽減できます。 これは、不安を抱いている顧客のコンプライアンス要求を満たすために使用できるアプローチです。

### 人間以外の ID のプロビジョニング

#### 緊急アクセス アカウント

Microsoft は、組織が[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)ロールが永続的に割り当てられる 2 つのクラウド専用緊急アクセス アカウントを作成することを推奨しています。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 これらのアカウントは、通常のアカウントを使用できない、またはすべての管理者が誤ってロックアウトされた場合の緊急時や最終手段の緊急対応としてのシナリオにおいてのみ使用されます。これらのアカウントは、[緊急アクセス アカウントに関する推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。

#### Azure マネージド ID

サービス ID を必要とする Azure リソースには [Azure マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用します。 Azure ソリューションを設計するときに、[マネージド ID をサポートするサービスの一覧](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)を確認してください。

マネージド ID がサポートされていないか、または使用できない場合は、[サービス プリンシパル オブジェクトのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)を検討してください。

#### ハイブリッド サービス アカウント

ハイブリッド ソリューションの中には、オンプレミス リソースとクラウド リソースの両方へのアクセスが必要なものがあります。 ユース ケースの例としては、オンプレミスのドメインに参加しているサーバーへのアクセスに AD DS のサービス アカウントを使用し、Microsoft Online Services へのアクセスが必要な Microsoft Entra ID のアカウントも含まれるアプリケーションがあります。

オンプレミスのサービス アカウントには通常、対話形式でサインインする機能はありません。つまり、クラウド シナリオでは、多要素認証などの強力な資格情報要件を満足できません。 このシナリオでは、オンプレミスから同期されたサービス アカウントは使用せず、代わりにマネージド ID \ サービス プリンシパルを使用します。 サービス プリンシパル (SP) では、資格情報として証明書を使用するか、または[条件付きアクセスで SP を保護します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity)。

これを実行できない技術的な制約があり、オンプレミスとクラウドの両方に同じアカウントを使用する必要がある場合は、ハイブリッド アカウントを特定のネットワークの場所から来るようにロックダウンするために、条件付きアクセスなどの補正制御を実装します。

### リソースの割り当て

エンタープライズ ソリューションは複数の Azure リソースで構成される可能性があるため、そのアクセスは割り当ての論理ユニット、つまりリソース グループとして管理および制御する必要があります。 そのシナリオでは、Azure AD セキュリティ グループを作成し、すべてのソリューション リソースにまたがる適切なアクセス許可とロールの割り当てに関連付けることができます。それにより、これらのグループにユーザーを追加または削除すると、ソリューション全体へのアクセスが許可または拒否されます。

アクセスを提供する前に、ライセンス シートの割り当てを持つユーザーに依存する Microsoft サービスのグループベースのライセンスとセキュリティ グループを使用することをお勧めします (例えば、Dynamics 365、Power BI など)。

Microsoft Entra クラウド ネイティブ グループは、[Microsoft Entra アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)および [Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)と組み合わせた場合、クラウドからネイティブに管理できます。

Microsoft Entra ID では、シングル サインオンと ID プロビジョニングのために、サードパーティの SaaS サービス (例えば、Salesforce、Service Now など) とオンプレミス アプリケーションへの直接ユーザー割り当てもサポートされています。 リソースへの直接割り当ては、[Microsoft Entra アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)および [Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-ops)と組み合わせた場合、クラウドからネイティブに管理できます。 直接割り当ては、エンドユーザー向けの割り当てに適している場合があります。

シナリオによっては、オンプレミスの Active Directory セキュリティ グループを使用してオンプレミス リソースへのアクセス権を付与することが必要な場合があります。 その場合は、プロセスの SLA を設計するときに、Microsoft Entra への同期サイクルを検討してください。

### 認証管理

このセクションでは、組織のセキュリティ体制に基づいて、資格情報の管理やアクセス ポリシーのために実行すべきチェックと取るべきアクションについて説明します。

#### 資格情報の管理

##### 信頼性のある資格

分離された環境内の人間の ID (B2B コラボレーションを使用してプロビジョニングされたローカル アカウントや外部 ID) はすべて、多要素認証や FIDO キーなどの強力な認証資格情報でプロビジョニングする必要があります。 スマート カード認証などの強力な認証を使用している基になるオンプレミス インフラストラクチャを含む環境では、引き続きクラウドでスマート カード認証を使用できます。

##### パスワードレスの資格情報

[パスワードレスのソリューション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)は、最も便利で、かつ安全な認証方法を保証するための最適なソリューションです。 特権ロールを持つ人間の ID には、[FIDO セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)や [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/) などのパスワードレスの資格情報が推奨されます。

##### パスワード保護

環境がオンプレミスの Active Directory フォレストから同期されている場合は、組織内の脆弱なパスワードを排除するために、[Microsoft Entra のパスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)をデプロイする必要があります。 ハイブリッドまたはクラウド専用の環境では、ユーザーのパスワードを推測するか、またはブルート フォース方法を使用して侵入しようとしている悪意のあるアクターをロックアウトするために、[Microsoft Entra スマート ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)も使用する必要があります。

##### セルフサービスによるパスワード管理

パスワードを変更またはリセットする必要があるユーザーは、ヘルプ デスクへの問い合わせの量とコストの最大の発生源の 1 つです。 コストに加え、ユーザーのリスクを軽減するためのツールとしてパスワードを変更することは、組織のセキュリティ体制を改善するための基本的な手順です。 少なくとも、ヘルプ デスクへの問い合わせをそらすために、パスワードがある人間のアカウントやテスト アカウントには[セルフサービスのパスワード管理](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-deployment)をデプロイします。

##### 外部 ID のパスワード

Microsoft Entra B2B コラボレーションを使用すると、[招待と引き換えプロセス](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)により、パートナー、開発者、下請業者などの外部ユーザーは、独自の資格情報を使用して会社のリソースにアクセスできます。 これにより、分離されたテナントにより多くのパスワードを導入する必要性が軽減されます。

注

一部のアプリケーション、インフラストラクチャ、またはワークフローには、ローカル資格情報が必要になることがあります。 これはケース バイ ケースで評価してください。

##### サービス プリンシパルの資格情報

サービス プリンシパルが必要なシナリオでは、サービス プリンシパルの証明書資格情報または[ワークロード ID 用の条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity)を使用します。 必要に応じて、組織のポリシーの例外としてクライアント シークレットを使用します。

どちらの場合も、ランタイム環境 (Azure 関数など) でキー コンテナーから資格情報を取得できるように、Azure Key Vault を Azure マネージド ID と共に使用できます。

証明書資格情報を含むサービス プリンシパルの認証については、[自己署名証明書を使用してサービス プリンシパルを作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-authenticate-service-principal-powershell)この例を確認してください。

#### アクセス ポリシー

次のセクションでは、Azure ソリューションの推奨事項について説明します。 個々の環境の条件付きアクセス ポリシーに関する一般的なガイダンスについては、[条件付きアクセスのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)、[Microsoft Entra 運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-auth)、[ゼロ トラストの条件付きアクセス](https://learn.microsoft.com/ja-jp/azure/architecture/guide/security/conditional-access-zero-trust)を確認してください。

- Azure Resource Manager にアクセスするときの ID セキュリティ体制を適用するために、[Windows Azure Service Management API](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity)クラウド アプリに対して[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)を定義します。 これには、セキュリティで保護されたワークステーション経由のアクセスのみを有効にする MFA に対する制御とデバイス ベースの制御を含める必要があります (これについては、「Identity Governance」の下の「特権ロール」セクションで詳細に説明します)。 さらに、[デバイスをフィルター処理するための条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)を使用します。
- 分離された環境にオンボードされたすべてのアプリケーションには、オンボード プロセスの一部として明示的な条件付きアクセス ポリシーが適用されている必要があります。
- オンプレミスにある信頼プロセスのセキュリティで保護されたルートが反映された[セキュリティ情報登録](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)に対して条件付きアクセス ポリシーを定義します (たとえば、従業員が検証のために自分でアクセスする必要がある、IP アドレスで識別できる物理的な場所にあるワークステーションの場合)。
- 条件付きアクセスを使用してワークロード ID を制限することを検討してください。 場所またはその他の関連する状況に基づいてアクセスを制限するか、またはより適切に制御するためのポリシーを作成します。

#### 認証の課題

- Microsoft Entra B2B でプロビジョニングされた外部 ID では、リソース テナントに多要素認証資格情報を再プロビジョニングすることが必要になる場合があります。 これは、リソース テナントにテナント間アクセス ポリシーが設定されていない場合に必要になることがあります。 つまり、システムへの登録は単一の要因で初期化されます。 このアプローチでは、リスク軽減策は、B2B 招待が発行される前に、組織でユーザーと資格情報のリスク プロファイルを証明するためのプロセスを用意することです。 さらに、前に説明したように、登録プロセスに対して条件付きアクセスを定義します。
- B2B コラボレーションと [B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)を使用して、他の Microsoft Entra 組織やその他の Microsoft Azure クラウドと共同作業する方法を管理するには、[外部 ID テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-direct-connect)を使用します。
- 特定のデバイスの構成と制御については、条件付きアクセス ポリシーのデバイス フィルターを使用して、[特定のデバイスを対象または対象外とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)ことができます。 これにより、指定されたセキュリティで保護された管理ワークステーション (SAW) から Azure 管理ツールへのアクセスを制限できます。 実行できる他のアプローチには、[Azure Virtual Desktop](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/terminology)、[Azure Bastion](https://learn.microsoft.com/ja-jp/azure/bastion/bastion-overview)、または[クラウド PC](https://learn.microsoft.com/ja-jp/graph/cloudpc-concept-overview) の使用が含まれます。
- Azure EA Portal や MCA 課金アカウントなどの課金管理アプリケーションは、条件付きアクセスのターゲット設定のためのクラウド アプリケーションとしては表されません。 補正制御として、別の管理アカウントを定義し、[すべてのアプリ] の条件を使用して、これらのアカウントに対して条件付きアクセス ポリシーを対象とします。

### ID ガバナンス

#### 特権ロール

分離のためにすべてのテナント構成にまたがって考慮すべき、いくつかの ID ガバナンスの原則を次に示します。

- **継続的なアクセスはなし** - 人間の ID には、分離された環境で特権操作を実行するための継続的なアクセス権を付与するべきではありません。 Azure ロールベースのアクセス制御 (RBAC) は、[Microsoft Entra Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) (PIM) と統合されます。 PIM は、多要素認証、承認ワークフロー、期間の制限などのセキュリティ ゲートによって決定される Just-In-Time アクティブ化を提供します。
- **管理者の数** - 組織は、ビジネス継続性のリスクを軽減するために、特権ロールを持つユーザーの最小数と最大数を定義する必要があります。 特権ロールの数が少なすぎると、タイム ゾーンを十分にカバーできない可能性があります。 最小特権の原則に従って、管理者の数をできるだけ少なくすることによってセキュリティ リスクを軽減します。
- **特権アクセスを制限する** - 高い特権または機密性が高い特権のための、クラウド専用のロール割り当て可能なグループを作成します。 これにより、割り当てられたユーザーとグループ オブジェクトの保護が提供されます。
- **最小特権アクセス** - ID には、組織内のそのロールに従った特権操作を実行するために必要なアクセス許可のみを付与する必要があります。

    - Azure RBAC [カスタム ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/custom-roles)を使用すると、組織のニーズに基づいて最小特権ロールを設計できます。 カスタム ロールの定義は、専門のセキュリティ チームが作成またはレビューするようにして、意図しない過剰な特権のリスクを軽減することをお勧めします。 カスタム ロールの作成は、[Azure Policy](https://github.com/Azure/azure-policy/blob/master/built-in-policies/policyDefinitions/General/Subscription_AuditCustomRBACRoles_Audit.json) を使用して監査できます。
    - 組織内でのより広範囲の使用が想定されていないロールの誤った使用を軽減するために、Azure Policy を使用して、アクセス権を割り当てるためにどのロール定義を使用できるかを明示的に定義します。 詳細については、この [GitHub サンプル](https://github.com/Azure/azure-policy/tree/master/samples/Authorization/allowed-role-definitions)を参照してください。
- **セキュリティで保護されたワークステーションからの特権アクセス** - すべての特権アクセスを、セキュリティで保護されたロックダウンされたデバイスから行う必要があります。 これらの機密性の高いタスクやアカウントを日常的に使用されるワークステーションやデバイスから分離すると、特権アカウントがフィッシング攻撃、アプリケーションと OS の脆弱性、さまざまな偽装攻撃のほか、キーボード操作のログ、[Pass-the-Hash](https://aka.ms/AzureADSecuredAzure/27a)、Pass-the-Ticket などの資格情報窃盗攻撃から保護されます。

[特権アクセス ストーリーの一部としてのセキュリティで保護されたデバイスの使用](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-devices)のために使用できるいくつかのアプローチには、[特定のデバイスを対象または対象外とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)ための条件付きアクセス ポリシーの使用、[Azure Virtual Desktop](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/terminology)、[Azure Bastion](https://learn.microsoft.com/ja-jp/azure/bastion/bastion-overview)、または[クラウド PC](https://learn.microsoft.com/ja-jp/graph/cloudpc-concept-overview) の使用、Azure マネージド ワークステーションまたは特権アクセス ワークステーションの作成が含まれます。

- **特権ロール プロセスのガードレール** - 組織は、規制要件に準拠しながら、必要な場合は常に特権操作を確実に実行できるようにするためのプロセスと技術的なガードレールを定義する必要があります。 ガードレールの基準の例には、次のものが含まれます。

    - 特権ロールを持つユーザーの資格 (フルタイム従業員/ベンダー、クリアランス レベル、市民権など)
    - ロールの明示的な非互換性 (職務の分離とも呼ばれます)。 例として、Microsoft Entra ディレクトリ ロールを持つチームが Azure Resource Manager 特権ロールの管理を行うべきではない、などがあります。
    - 直接のユーザーまたはグループの割り当てがどのロールに推奨されるか。
- Microsoft Entra PIM の外部での IAM 割り当ての監視は、Azure ポリシーでは自動化されません。 この軽減策として、エンジニアリング チームにサブスクリプション所有者またはユーザー アクセス管理者ロールを付与しないようにします。 代わりに、共同作成者などの最小特権ロールに割り当てられたグループを作成し、それらのグループの管理をエンジニアリング チームに委任します。

#### リソース アクセス

- **アテステーション** - メンバーシップを最新かつ正当な状態に維持するためには、特権的な役割を持つアイデンティティを定期的にレビューする必要があります。 [Microsoft Entra アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)は、Azure RBAC ロール、グループ メンバーシップ、Microsoft Entra B2B 外部 ID と統合されます。
- **ライフサイクル** - 特権操作には、基幹業務アプリケーション、SaaS アプリケーション、Azure リソース グループとサブスクリプションなどの複数のリソースへのアクセスが必要になることがあります。 [Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用すると、ユーザーにユニットとして割り当てられたセット リソースを表すアクセス パッケージを定義し、有効期間や承認ワークフローなどを確立できます。

### テナントとサブスクリプションのライフサイクル管理

#### テナントのライフサイクル

- 新しい企業 Microsoft Entra テナントを要求するためのプロセスを実装することをお勧めします。 このプロセスでは、次のことを考慮する必要があります。

    - それを作成するための業務上の正当な理由。 新しい Microsoft Entra テナントを作成すると、複雑さが大幅に増すため、新しいテナントが必要かどうかを確認することが重要です。
    - それが作成される必要がある Azure クラウド (商用クラウドや政府のクラウドなど)。
    - これが運用または非運用テナントのどちらであるか
    - ディレクトリのデータ所在地の要件
    - それを誰が管理するか
    - 一般的なセキュリティ要件のトレーニングと理解。
- 承認されると、Microsoft Entra テナントが作成され、必要なベースライン制御が構成されて、課金プレーンや監視などにオンボードされます。
- 管理されたプロセスの外部でのテナント作成を検出するために、課金プレーン内の Microsoft Entra テナントの定期的なレビューを実装する必要があります。 詳細については、このドキュメントの「インベントリと可視性」セクションを参照してください。
- Azure AD B2C テナントの作成は、Azure Policy を使用して制御できます。 このポリシーは、その B2C テナントに Azure サブスクリプションが関連付けられているときに実行されます (課金の前提条件)。 顧客は、Azure AD B2C テナントの作成を特定の管理グループに制限できます。

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

- テナントの作成を組織に従属させるための技術的な制御はありません。 ただし、アクティビティは監査ログに記録されます。 課金システムへの登録プロセスは、ゲートでの補償制御です。 代わりに、これを監視とアラートで補完する必要があります。

#### サブスクリプションのライフサイクル

管理されたサブスクリプション ライフサイクル プロセスを設計する場合の、いくつかの考慮事項を次に示します。

- Azure リソースが必要なアプリケーションとソリューションの分類を定義します。 サブスクリプションを要求するすべてのチームは、サブスクリプションを要求するときにその "製品識別子" を指定する必要があります。 この情報分類によって、次のものが決定されます。

    - サブスクリプションをプロビジョニングする Microsoft Entra テナント
    - サブスクリプションの作成に使用する Azure EA アカウント
    - 名称に関する規則
    - 管理グループの割り当て
    - その他の側面として、タグ付け、クロス課金、製品ビューの使用状況などがあります。
- ポータルまたはその他の手段を使用したアドホック サブスクリプションの作成を許可しないでください。 代わりに、[Azure Resource Manager を使用してプログラムでサブスクリプション](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/programmatically-create-subscription)を管理し、使用量レポートと課金レポートを[プログラムで](https://learn.microsoft.com/ja-jp/rest/api/consumption/)プルすることを検討してください。 これは、サブスクリプションのプロビジョニングを認可されたユーザーに制限し、ポリシーと分類の目標を適用するのに役立ちます。 [AZOps の原則](https://github.com/azure/azops/wiki/introduction)に従うことに関するガイダンスを使用すると、実用的なソリューションを作成するのに役立ちます。
- サブスクリプションがプロビジョニングされたら、アプリケーション チームに必要な標準の Azure Resource Manager ロール (共同作成者、閲覧者、承認されたカスタム ロールなど) を持つ Microsoft Entra クラウド グループを作成します。 これにより、管理された特権アクセスを使用して Azure RBAC ロールの割り当てを大規模に管理できます。

    1. Microsoft Entra PIM をアクティブ化ポリシー、アクセス レビュー、承認者などの対応する制御と共に使用して、Azure RBAC ロールの対象となるようにグループを構成します。
    2. 次に、ソリューション所有者に[グループの管理を委任します](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)。
    3. ガードレールとして、Microsoft Entra PIM の外部でロールが誤って直接割り当てられることや、サブスクリプションが別のテナントに完全に変更される可能性を回避するために、製品所有者をユーザー アクセス管理者または所有者ロールに割り当てないでください。
    4. Azure Lighthouse を使用して非運用テナントでテナント間のサブスクリプション管理を有効にすることを選択した顧客の場合は、サブスクリプションを管理するために認証するときに、運用特権アカウントの同じアクセス ポリシー (たとえば、[セキュリティで保護され ワークステーション](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-deployment)からのみの特権アクセス) が確実に適用されるようにしてください。
- 組織に事前に承認された参照アーキテクチャがある場合は、サブスクリプションのプロビジョニングを [Azure Blueprints](https://learn.microsoft.com/ja-jp/azure/governance/blueprints/overview) や [Terraform](https://www.terraform.io) などのリソース デプロイ ツールと統合できます。
- Azure サブスクリプションへのテナント アフィニティを考慮すると、サブスクリプションのプロビジョニングでは、複数のテナントにまたがる同じ人間のアクターの複数の ID (従業員、パートナー、ベンダーなど) を認識し、それに応じてアクセス権を割り当てる必要があります。

#### EA ロールと MCA ロール

- Azure Enterprise (Azure EA) Agreement ポータルは、Azure RBAC や条件付きアクセスとは統合されません。 この軽減策として、ポリシーや追加の監視の対象にできる専用の管理アカウントを使用します。
- Azure EA Enterprise ポータルでは、監査ログは提供されません。 これを軽減するには、前に説明した考慮事項を使用してサブスクリプションをプロビジョニングし、専用の EA アカウントを使用して認証ログを監査するための自動化された管理されたプロセスを検討してください。
- [Microsoft 顧客契約](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/understand/mca-overview) (MCA) ロールは、PIM とネイティブには統合されません。 これを軽減するには、専用の MCA アカウントを使用し、これらのアカウントの使用状況を監視します。

#### Azure AD B2C テナント

- Azure AD B2C テナントでは、組み込みロールは PIM をサポートしていません。 セキュリティを強化するために、Microsoft Entra B2B コラボレーションを使用して、Azure テナントから顧客 ID アクセス管理 (CIAM) を管理するエンジニアリング チームをオンボードし、それらを Azure AD B2C 特権ロールに割り当て、これらの専用管理アカウントに条件付きアクセス ポリシーを適用することをお勧めします。
- Azure AD B2C テナントの特権ロールは、Microsoft Entra アクセス レビューとは統合されません。 この軽減策として、組織の Microsoft Entra テナント内に専用アカウントを作成し、これらのアカウントをグループに追加して、このグループに対して定期的なアクセス レビューを実行します。
- 上記の Microsoft Entra の緊急アクセス ガイドラインに従って、前に説明した外部管理者に加え、同等の[緊急アクセス用アカウント](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)を作成することを検討してください。
- B2C テナントの基になる Microsoft Entra サブスクリプションの論理所有権を、Azure サブスクリプションの残りの部分が B2C ソリューションに使用されるのと同じように、CIAM エンジニアリング チームに合わせることをお勧めします。

### 操作

複数の分離された環境に固有の Microsoft Entra に関する運用上の追加の考慮事項を次に示します。 個々の環境を運用するための詳細なガイダンスについては、[Azure クラウド導入フレームワーク](https://learn.microsoft.com/ja-jp/azure/cloud-adoption-framework/manage/)、[Microsoft クラウド セキュリティ ベンチマーク](https://learn.microsoft.com/ja-jp/security/benchmark/azure/)、[Microsoft Entra 運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-ops)を確認してください。

#### 異なる環境間の役割と責任

**企業全体にわたる SecOps アーキテクチャ** - 組織内のすべての環境の運用およびセキュリティ チームのメンバーが次のものを共同で定義する必要があります。

- 環境をいつ作成、統合、または非推奨化する必要があるかを定義するための原則。
- 各環境で管理グループ階層を定義するための原則。
- 請求プラットフォーム EA ポータル/MCA のセキュリティ体制、運用体制、委任アプローチ。
- テナントの作成プロセス。
- エンタープライズ アプリケーションの分類。
- Azure サブスクリプションのプロビジョニング プロセス。
- 分離と管理の自律性の境界と、チームや環境にまたがるリスク評価。
- すべての環境で使用される一般的なベースライン構成およびセキュリティ制御 (技術的および補完的) と運用ベースライン。
- 複数の環境にまたがる一般的な標準の運用手順およびツール (監視やプロビジョニングなど)。
- 複数の環境にまたがる役割の委任が合意された。
- 環境にまたがる職務の分離。
- 特権ワークステーションに対する一般的なサプライ チェーン管理。
- 名前付け規則。
- クロス環境の相関関係メカニズム。

**テナントの作成** - 企業全体にわたる SecOps アーキテクチャによって定義されている標準化された手順に従って、特定のチームがテナントの作成を所有している必要があります。 これには次のものが含まれます

- 基になるライセンス プロビジョニング (Microsoft 365 など)。
- 企業の課金プランへのオンボード (Azure EA や MCA など)。
- Azure 管理グループ階層の作成。
- ID、データ保護、Azure などを含むさまざまな境界に対する管理ポリシーの構成。
- 診断設定、SIEM オンボード、CASB オンボード、PIM オンボードなどを含む、合意されたサイバーセキュリティ アーキテクチャごとのセキュリティ スタックのデプロイ。
- 合意された委任に基づいた Microsoft Entra ロールの構成。
- 初期の特権ワークステーションの構成と配布。
- 緊急アクセス用アカウントのプロビジョニング。
- ID プロビジョニング スタックの構成。

**クロス環境ツールのアーキテクチャ** - ID プロビジョニングやソース管理パイプラインなどの一部のツールは、複数の環境にまたがって動作することが必要な場合があります。 これらのツールはインフラストラクチャにとって重要であると見なす必要があり、そのように設計、デザイン、実装、管理する必要があります。 そのため、クロス環境ツールを定義する必要がある場合は常に、すべての環境の設計者を関与させる必要があります。

#### インベントリと可視性

**Azure サブスクリプションの検出** - 検出されたテナントごとに、Microsoft Entra グローバル管理者は、環境内のすべてのサブスクリプションを可視化するために[アクセス権限を昇格させる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)ことができます。 この昇格により、それらのユーザーには、ルート管理グループでの[ユーザー アクセス管理者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles/general#user-access-administrator)の組み込みロールが割り当てられます。

注

このアクションは高い特権を持つため、データが適切に分離されていない場合は、非常に機密性の高い情報を保持するサブスクリプションへのアクセス権が管理者に付与される可能性があります。

**リソースを検出するための読み取りアクセスの有効化** - 管理グループにより、RBAC 割り当てが、複数のサブスクリプションにまたがって大規模に有効になります。 顧客は、ルート管理グループでロールの割り当てを構成することによって、一元化された IT チームに閲覧者ロールを付与できます。これは、環境内のすべてのサブスクリプションに伝達されます。

**リソースの検出** - 環境内のリソースの読み取りアクセスを取得したら、[Azure Resource Graph](https://learn.microsoft.com/ja-jp/azure/governance/resource-graph/overview) を使用して環境内のリソースにクエリを実行できます。

#### ログ記録と監視

**セキュリティ ログの一元管理** - 環境にわたって一貫性のあるベスト プラクティス (診断設定、ログ保持期間、SIEM インジェストなど) に従って、[一元的な方法](https://learn.microsoft.com/ja-jp/security/benchmark/azure/security-control-logging-monitoring)で各環境からログを取り込みます。 [Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview) を使用すると、エンドポイント デバイス、ネットワーク、オペレーティング システムのセキュリティ ログなどのさまざまなソースからログを取り込むことができます。

自動または手動のプロセスやツールを使用して、セキュリティ運用の一部としてログを監視する方法に関する詳細情報は、[Microsoft Entra セキュリティ運用ガイド](https://github.com/azure/azops/wiki/introduction)で入手できます。

環境によっては、特定の環境から持ち出すことができるデータ (存在する場合) を制限する規制要件がある場合があります。 環境にまたがる一元的な監視が不可能な場合、チームには、クロス環境の横移動の試みなどの監査やフォレンジックスの目的のために、環境にまたがる ID のアクティビティを関連付ける運用手順が用意されている必要があります。 同じユーザーに属する一意識別子や人間の ID のオブジェクトを (場合によっては、ID プロビジョニング システムの一部として) 検出可能にすることをお勧めします。

ログ戦略には、組織で使うテナントごとに次の Microsoft Entra ログを含める必要があります。

- サインイン アクティビティ
- 監査ログ
- リスク イベント

Microsoft Entra ID により、サインイン アクティビティ ログと監査ログのための [Azure Monitor の統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations)が提供されます。 リスク イベントは、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/tutorial-riskdetection-api) を使用して取り込むことができます。

次の図は、監視戦略の一部として組み込まれむ必要があるさまざまなデータ ソースを示しています。

Azure AD B2C テナントは、[Azure Monitor と統合](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/azure-monitor)できます。 Microsoft Entra ID に対して前に説明したのと同じ条件を使用した Azure AD B2C の監視をお勧めします。

Azure Lighthouse を使用してテナント間の管理を有効にしているサブスクリプションでは、Azure Monitor によってログが収集されている場合、テナント間の監視を有効にすることができます。 対応する Log Analytics ワークスペースはリソース テナント内に存在することができ、Azure Monitor ブックを使用して管理テナントで一元的に分析できます。 詳細については、「[委任されたリソースを大規模に監視する - Azure Lighthouse](https://learn.microsoft.com/ja-jp/azure/lighthouse/how-to/monitor-at-scale)」を確認してください。

#### ハイブリッド インフラストラクチャの OS セキュリティ ログ

ハイブリッド ID インフラストラクチャの OS ログはすべて、外部からのアクセスの影響を考慮し、階層 0 システムとしてアーカイブして注意深く監視する必要があります。 これには次のものが含まれます

- AD FS サーバーと Web アプリケーション プロキシ
- Microsoft Entra Connect
- アプリケーション プロキシ エージェント
- パスワード ライトバック エージェント
- パスワード保護ゲートウェイ マシン
- Microsoft Entra 多要素認証 RADIUS 拡張機能を備えた NPS

すべての環境の ID 同期とフェデレーション (該当する場合) を監視するには、[Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) をデプロイする必要があります。

**ログ ストレージ保持期間** - 一貫性のあるツールセット (たとえば、Azure Sentinel などの SIEM システム)、一般的なクエリ、調査、フォレンジックス プレイブックを促進するには、すべての環境に凝集性のあるログ ストレージ保持期間の戦略、設計、実装が必要です。 Azure Policy を使用すると、診断設定を設定できます。

**監視とログのレビュー** - ID 監視に関する運用タスクは一貫性があり、各環境に所有者が存在する必要があります。 前に説明したように、規制コンプライアンスや分離の要件によって許容される範囲内で、これらの責任を環境にまたがって統合するように努力してください。

次のシナリオを明示的に監視および調査する必要があります。

- **疑わしいアクティビティ** - すべての [Microsoft Entra リスク イベント](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)を、疑わしいアクティビティがないかどうか監視する必要があります。 場所ベースのシグナルで多くのノイズが検出されないように、すべてのテナントでネットワークの[ネームド ロケーション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)を定義する必要があります。 [Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) は、Azure Security Center とネイティブに統合されています。 すべてのリスク検出調査に、ID がプロビジョニングされているすべての環境を含めることをお勧めします (たとえば、人間の ID で企業テナント内のアクティブなリスク検出が発生した場合、顧客向けのテナントを運用しているチームは、その環境内の対応するアカウントのアクティビティも調査する必要があります)。
- **ユーザー/エンティティ行動分析 (UEBA) のアラート** - 異常検出に基づいて詳細な分析情報を取得するには、UEBA を使用する必要があります。 [Microsoft 365 Defender for Cloud Apps](https://www.microsoft.com/security/business/siem-and-xdr/microsoft-defender-cloud-apps) は、[クラウドでの UEBA](https://learn.microsoft.com/ja-jp/defender-cloud-apps/tutorial-ueba) を提供します。 顧客は、[Microsoft 365 Defender for Identity からオンプレミスの UEBA](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/microsoft-365-security-center-mdi) を統合できます。 MCAS は、Microsoft Entra ID 保護から信号を読み取ります。
- **緊急アクセス用アカウントのアクティビティ** - [緊急アクセス用アカウント](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-privileged-accounts)を使用したすべてのアクセスを監視し、調査のために[アラート](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)を作成する必要があります。 この監視には次のものを含める必要があります。

    - サインイン
    - 資格情報の管理
    - グループ メンバーシップに関する何らかの更新
    - アプリケーションの割り当て
- **課金管理アカウント** - Azure EA または MCA の課金管理ロールを持つアカウントの機密性とその高い特権を考慮して、次のことを監視し、アラートを生成することをお勧めします。

    - 請求ロールを持つアカウントによるサインイン試行。
    - EA ポータル以外のアプリケーションに対して認証しようとするすべての試み。
    - MCA 課金タスクに専用アカウントを使用している場合は、Azure Resource Management 以外のアプリケーションに対して認証しようとするすべての試み。
    - MCA 課金タスクの専用アカウントを使用した Azure リソースへの割り当て。
- **特権ロールのアクティビティ** - [Microsoft Entra PIM によって生成されるセキュリティ アラート](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts)を構成およびレビューします。 RBAC の直接割り当てのロックダウンを技術的な制御で完全には適用できない場合 (たとえば、ジョブを実行できるように製品チームに所有者ロールを付与する必要がある場合) は、Azure RBAC を使用してサブスクリプションにアクセスするためにユーザーが直接割り当てられるたびにアラートを生成することによって、PIM の外部での特権ロールの直接割り当てを監視します。
- **クラシック ロールの割り当て** - 組織は、クラシック ロールの代わりに、最新の Azure RBAC ロール インフラストラクチャを使用する必要があります。 その結果、次のイベントを監視する必要があります。

    - サブスクリプション レベルでのクラシック ロールへの割り当て
- **テナント全体の構成** - テナント全体の構成サービスでは、常にシステムでアラートを生成する必要があります。

    - カスタム ドメインの更新
    - ブランドの更新
    - Microsoft Entra B2B の許可/ブロック リスト
    - Microsoft Entra B2B で許可された ID プロバイダー (直接フェデレーションまたはソーシャル ログインによる SAML IDP)
    - 条件付きアクセス ポリシーの変更
- **アプリケーション オブジェクトとサービス プリンシパル オブジェクト**

    - 条件付きアクセス ポリシーを必要とする可能性がある新しいアプリケーション/サービス プリンシパル
    - アプリケーションの同意アクティビティ
- **管理グループのアクティビティ** - 管理グループの次の ID の側面を監視する必要があります。

    - MG での RBAC ロールの割り当て
    - MG で適用された Azure ポリシー
    - MG から別の MG へ移動されたサブスクリプション
    - ルート MG に対するセキュリティ ポリシーへのすべての変更
- **カスタム ロール**

    - カスタム ロールの定義の更新
    - 作成された新しいカスタム ロール
- **職務規則のカスタム分離** - 組織が職務の分離規則を確立した場合、Microsoft Entra Entitlement Management の互換性のないアクセス パッケージを使用して職務の分離を強制し、アラートを作成するか、管理者による違反を検出するための定期的なレビューを構成します。

**監視に関するその他の考慮事項** - ログ管理に使用されるリソースが含まれている Azure サブスクリプションは、重要なインフラストラクチャ (階層 0) と見なし、対応する環境のセキュリティ運用チームにロックダウンする必要があります。 Azure Policy などのツールを使用して、これらのサブスクリプションに追加の制御を適用することを検討してください。

#### 運用ツール

**クロス環境**におけるツールの設計上の考慮事項:

- 可能な場合は常に、複数のテナントにまたがって使用される運用ツールは、各テナントで複数のインスタンスが再デプロイされないようにし、運用上の非効率性を回避するために、Microsoft Entra マルチテナント アプリケーションとして実行されるように設計する必要があります。 この実装には、ユーザーとデータの間の分離が確実に保持されるようにするための認可ロジックを含める必要があります。
- クロス環境の自動化 (ID プロビジョニングなど) や、フェールセーフに対するしきい値の制限をすべて監視するためのアラートと検出を追加します。 たとえば、ユーザー アカウントのプロビジョニング解除が特定のレベルに達した場合は、それが広範囲に影響を与えるバグまたは運用エラーを示している可能性があるため、アラートを生成することもできます。
- クロス環境タスクを調整するすべての自動化を、高い特権を持つシステムとして運用する必要があります。 このシステムは最も高いセキュリティ環境に配置し、他の環境のデータが必要な場合は外部ソースからプルする必要があります。 システムの整合性を維持するには、データ検証としきい値を適用する必要があります。 一般的なクロス環境タスクとして、解雇された従業員に関して、すべての環境から ID を削除するための ID ライフサイクル管理があります。

**IT サービス管理ツール** - ServiceNow などの IT Service Management (ITSM) システムを使用している組織は、アクティブ化の目的の一部としてチケット番号を要求するように [Microsoft Entra PIM ロールのアクティブ化の設定](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)を構成する必要があります。

同様に、[IT Service Management Connector](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/itsmc-overview) を使用して Azure Monitor を ITSM システムと統合できます。

**運用プラクティス** - 人間の ID での環境への直接アクセスを必要とする運用アクティビティを最小限に抑えます。 代わりに、それを一般的な操作 (PaaS ソリューションへの容量の追加や、診断の実行など) を実行する Azure Pipelines としてモデル化し、Azure Resource Manager インターフェイスへの直接アクセスを "緊急時対応" のシナリオとしてモデル化します。

#### 運用の課題

- サービス プリンシパルの監視のアクティビティが一部のシナリオに制限されます。
- Microsoft Entra PIM アラートには API がありません。 この軽減策として、これらの PIM アラートの定期的なレビューを実行します。
- Azure EA Portal では、監視機能は提供されません。 この軽減策として、専用の管理アカウントを保有し、そのアカウントのアクティビティを監視します。
- MCA では、課金タスクの監査ログは提供されません。 この軽減策として、専用の管理アカウントを保有し、そのアカウントのアクティビティを監視します。
- 環境を運用するために必要な Azure の一部のサービスはマルチテナントやマルチクラウドにできないため、再デプロイし、環境にまたがって再構成する必要があります。
- コードとしてのインフラストラクチャを完全に実現するための Microsoft Online Services にまたがる完全な API カバレッジはありません。 この軽減策として、API をできる限り使用し、残りの部分にはポータルを使用します。 この[オープンソースのイニシアティブ](https://microsoft365dsc.com/)は、環境で機能する可能性のあるアプローチを決定するのに役立ちます。
- サブスクリプション アクセスを管理テナント内の ID に委任しているリソース テナントを検出するためのプログラムによる機能はありません。 たとえば、電子メール アドレスによって contoso.com テナント内のセキュリティ グループが fabrikam.com テナント内のサブスクリプションを管理できるようになった場合、contoso.com 内の管理者には、この委任が行われたことを検出するための API がありません。
- 特定のアカウントアクティビティ (例えば、緊急用アカウントや課金管理アカウント) の監視は標準機能として提供されていません。 この軽減策として、顧客が独自のアラート ルールを作成します。
- テナント全体の構成の監視が標準で提供されません。 この軽減策として、顧客が独自のアラート ルールを作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-external-access-resources"} -->
## Microsoft Entra B2B コラボレーションのデプロイを計画する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-external-access-resources
- Service: entra / architecture
- Article date: 2023-04-28
- Summary: 内部リソースへの外部アクセスをセキュリティで保護し、管理するためのアーキテクトと IT 管理者向けのガイド

外部パートナーとの安全なコラボレーションにより、予想される期間にわたって、内部リソースへの適切なアクセス権を保証します。 セキュリティ リスクを軽減し、コンプライアンス目標を達成し、正確なアクセス権を保証するためのガバナンス プラクティスについて説明します。

### ガバナンスの利点

管理コラボレーションにより、アクセスの所有権の明確さが向上し、機密性の高いリソースの露出が軽減され、アクセス ポリシーへの準拠を証明できるようになります。

- リソースにアクセスする外部組織とそのユーザーを管理する
- アクセス権が適切であり、レビューされ、期限が設定されていることを確認する
- ビジネス所有者が委任を使用してコラボレーションを管理できるようにする

### コラボレーションの方法

これまで、組織は次の 2 つの方法のいずれかを使用してコラボレーションを行ってきました。

- 外部ユーザー用にローカルで管理される資格情報を作成する
- パートナー ID プロバイダー (IdP) とのフェデレーションを確立する

どちらの方法にも欠点があります。 詳細については、次の表を参照してください。

| 懸念のある領域 | ローカルの資格情報 | フェデレーション |
| --- | --- | --- |
| セキュリティ | - 外部ユーザーが終了した後もアクセスが続行する - UserType は既定で Member であり、既定で過剰なアクセス権が付与される | - ユーザー レベルの可視性なし  - 不明なパートナーのセキュリティ体制 |
| 経費 | - パスワードと多要素認証 (MFA) の管理 - オンボード プロセス - ID クリーンアップ - 別のディレクトリを実行するオーバーヘッド | 小規模なパートナーは、インフラストラクチャを提供する余裕がなく、専門知識が不足していて、コンシューマー メールを使用している可能性がある |
| 複雑さ | パートナー ユーザーが管理する資格情報が多くなる | 新しいパートナーごとに複雑さが増し、パートナーにとっての負担が増す |

Microsoft Entra B2B は、Microsoft Entra ID、および Microsoft 365 サービスの他のツールと統合されます。 Microsoft Entra B2B では、コラボレーションが簡素化され、費用が削減され、セキュリティが向上しています。

### Microsoft Entra B2B の利点

- ホーム ID が無効になっているかまたは削除されている場合、外部ユーザーはリソースにアクセスできない
- ユーザー ホーム IdP が認証と資格情報の管理を処理する
- リソース テナントがゲスト ユーザーのアクセスと承認を制御する
- メール アドレスを持っているがインフラストラクチャがないユーザーとコラボレーションを行う
- IT 部門が帯域外で接続してアクセスまたはフェデレーションを設定することはない
- ゲスト ユーザーのアクセスは、内部ユーザーと同じセキュリティ プロセスによって保護される
- 追加の資格情報を必要としない快適なエンド ユーザー エクスペリエンス
- ユーザーは、IT 部門の関与なしにパートナーとコラボレーションを行う
- Microsoft Entra ディレクトリのゲストの既定のアクセス許可が制限されたり、厳しく規制されたりすることがない
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-fundamentals"} -->
## Microsoft Entra ID を使用したセキュリティ保護の基礎 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-fundamentals
- Service: entra / architecture
- Article date: 2024-08-25
- Summary: Microsoft Entra ID でのテナントのセキュリティ保護の基礎。

Microsoft Entra ID は、Azure リソースと信頼アプリケーションの ID とアクセスの境界を提供します。 ほとんどの環境分離要件は、シングル Microsoft Entra テナントでの委任された管理で満たすことができます。 この構成により、システムの管理オーバーヘッドが軽減されます。 ただし、リソースと ID の完全な分離などの特定のケースでは、マルチ テナントが必要です。

ニーズに基づいて環境分離アーキテクチャを決定する必要があります。 次の領域について検討する必要があります。

- **リソースの分離**。 リソースがユーザー オブジェクトなどのディレクトリ オブジェクトを変更する可能性があり、その変更が他のリソースに干渉する場合は、マルチテナント アーキテクチャでリソースを分離する必要があるかもしれません。
- **構成の分離**。 テナント全体の構成は、すべてのリソースに影響します。 テナント全体の構成のうち一部のものの影響については、条件付きアクセス ポリシーやその他の方法でスコープを設定できます。 条件付きアクセス ポリシーでスコープを設定できない別のテナント構成が必要な場合は、マルチテナント アーキテクチャが必要かもしれません。
- **管理上の分離**。 シングル テナント内の管理グループ、サブスクリプション、リソース グループ、リソース、一部のポリシーの管理を委任できます。 グローバル管理者は、常にテナント内のすべてに対するアクセス権を持っています。 環境が別の環境と管理者を共有していることがないようにする必要がある場合は、マルチテナント アーキテクチャが必要です。

セキュリティを維持するには、ID プロビジョニング、認証管理、ID ガバナンス、ライフサイクル管理、および操作に関するベスト プラクティスに従い、それをすべてのテナントで一貫して実行する必要があります。

### 用語

この用語の一覧は、一般的に Microsoft Entra ID に関連付けられており、このコンテンツに関連しています。

**Microsoft Entra テナント**. 組織が Microsoft クラウド サービスのサブスクリプションにサインアップしたときに自動的に作成される Microsoft Entra ID の信頼できる専用インスタンス。 サブスクリプションの例としては、Microsoft Azure、Microsoft Intune、Microsoft 365 があります。 Microsoft Entra テナントは、通常、単一の組織またはセキュリティ境界を表します。 Microsoft Entra テナントには、テナント リソースに対して ID およびアクセス管理 (IAM) を実行するために使用されるユーザー、グループ、アプリケーションが含まれています。

**環境**。 このコンテンツの文脈においては、環境とは、1 つ以上の Microsoft Entra テナントに関連付けられている Azure サブスクリプション、Azure リソース、およびアプリケーションのコレクションです。 Microsoft Entra テナントは、これらのリソースへのアクセスを制御する ID コントロール プレーンを提供します。

**運用環境**。 このコンテンツの文脈においては、運用環境とは、エンド ユーザーが直接やり取りを行うインフラストラクチャとサービスを備えたライブ環境です。 たとえば、企業や顧客向けの環境などです。

**非本番環境**。 このコンテンツの文脈においては、非運用環境とは、次の目的で使用される環境を指します。

- 開発
- テスト
- ラボの目的

非運用環境は、一般的にサンドボックス環境と呼ばれます。

**アイデンティティ**。 ID とは、リソースへのアクセスの認証と承認を受けられるディレクトリ オブジェクトです。 ID オブジェクトには、人間に与えられる ID と人間以外に与えられる ID があります。 人間以外のエンティティには、次のものがあります。

- アプリケーション オブジェクト
- ワークロード アイデンティティ (旧称ではサービス プリンシパルと表現されていました)
- マネージドアイデンティティ
- デバイス

**人間に与えられる ID** は、通常、組織内のユーザーを表すユーザー オブジェクトです。 これらの ID は、Microsoft Entra ID で直接作成され管理されるか、特定の組織のオンプレミスの Active Directory から Microsoft Entra ID に同期されます。 これらの種類の ID は **ローカル ID** と呼ばれます。 [Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を使用して、パートナー組織またはソーシャル ID プロバイダーから招待されたユーザー オブジェクトもある可能性があります。 このコンテンツでは、これらの種類の ID を**外部 ID** と呼びます。

**人間以外に与えられる ID **には、人間に関連付けられていないすべての ID が含まれます。 この種類の ID は、実行に ID を必要とするアプリケーションなどのオブジェクトです。 このコンテンツでは、この種類の ID を**ワークロード ID** と呼びます。 この種類の ID は、[アプリケーション オブジェクトやサービス プリンシパル](https://learn.microsoft.com/ja-jp/partner-center/marketplace/manage-aad-apps)など、さまざまな用語を使用して表現されます。

- **アプリケーション オブジェクト**。 Microsoft Entra アプリケーションは、それ自身のアプリケーション オブジェクトで定義されます。 オブジェクトはアプリケーションが登録された Microsoft Entra テナントに存在します。 テナントは、アプリケーションの "ホーム" テナントと呼ばれます。

    - **シングルテナント** アプリケーションは、"ホーム" テナントからの ID のみを承認するように作成されます。
    - **マルチテナント** アプリケーションでは、任意の Microsoft Entra テナントからの ID を認証できます。
- **サービス プリンシパル オブジェクト**。 [例外](https://learn.microsoft.com/ja-jp/partner-center/manage-aad-apps)はありますが、アプリケーション オブジェクトはアプリケーションの*定義*と考えることができます。 サービス プリンシパル オブジェクトは、アプリケーションのインスタンスと考えることができます。 一般的に、サービス プリンシパルはアプリケーション オブジェクトを参照し、1 つのアプリケーション オブジェクトはディレクトリを超えて複数のプリンシパルによって参照されます。

**サービス プリンシパル オブジェクト**も、人間の介入とは関係なくタスクを実行できるディレクトリ ID です。 サービス プリンシパルは、その Microsoft Entra テナント内のユーザーまたはアプリケーションのアクセス ポリシーとアクセス許可を定義します。 このメカニズムによって、サインイン時のユーザーまたはアプリケーションの認証、リソースへのアクセス時の承認などのコア機能を利用できるようになります。

Microsoft Entra ID を使用すると、アプリケーション オブジェクトとサービス プリンシパル オブジェクトは、パスワード (アプリケーション シークレットとも呼ばれる) または証明書を使用して認証できます。 サービス プリンシパルにパスワードを使用することはお勧めしません。可能な限り[証明書を使用することをお勧めします](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-service-principal-portal)。

- **Azure リソースの管理 ID**。 マネージド ID は、Microsoft Entra ID における特別なサービス プリンシパルです。 この種類のサービス プリンシパルを使用すると、コードに資格情報を格納したりシークレット管理を処理したりする必要なく、Microsoft Entra 認証をサポートするサービスに対する認証を行うことができます。 詳細については、「[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください。
- **デバイス ID**: デバイス ID は、認証フローにおいて、そのデバイスが正規品であり、技術的要件を満たしていると証明するプロセスを通過したことを確証します。 デバイスがこのプロセスを正常に完了すると、関連付けられている ID を使用して、組織のリソースへのアクセスをさらに制御できます。 Microsoft Entra ID を使用すると、デバイスは証明書を使用して認証できます。

レガシー シナリオの中には、*非人間*シナリオで人間の認証情報を使用する必要があるものもあります。 たとえば、スクリプトやバッチ ジョブなどのオンプレミス アプリケーションで使用されているサービス アカウントが Microsoft Entra ID へのアクセスを必要とする場合です。 このパターンはお勧めしません。[証明書](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive)の使用をお勧めします。 ただし、認証に人間の ID とパスワードを使用する場合は、[Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)を使用して Microsoft Entra アカウントを保護してください。

**ハイブリッド ID**。 ハイブリッド ID は、オンプレミス環境とクラウド環境にまたがる ID です。 ハイブリッドには、同じ ID を使用してオンプレミス リソースとクラウド リソースにアクセスできるという利点があります。 このシナリオの認証元は通常、オンプレミス ディレクトリであり、プロビジョニング、プロビジョニング解除、リソースの割り当てといった ID ライフサイクルもオンプレミス主導で行われます。 詳細については、「[ハイブリッド ID のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/)」を参照してください。

**ディレクトリ オブジェクト**。 Microsoft Entra テナントには、次の共通オブジェクトが含まれています。

- **ユーザー オブジェクト**は、現在はサービス プリンシパルをサポートしていないサービスの人間用 ID と人間以外用 ID を表します。 ユーザー オブジェクトには、個人の詳細、グループ メンバーシップ、デバイス、ユーザーに割り当てられたロールなど、ユーザーに関する必要な情報をもつ属性が含まれます。
- **デバイス オブジェクト**は、Microsoft Entra テナントに関連付けられているデバイスを表します。 デバイス オブジェクトには、デバイスに関する必要な情報をもつ属性が含まれています。 これらのオブジェクトには、オペレーティング システム、関連付けられているユーザー、コンプライアンスの状態、および Microsoft Entra テナントとの関連付けの性質が含まれます。 この関連付けは、デバイスの相互作用と信頼レベルの性質に応じて、複数の形式を取ることができます。

    - **ハイブリッド ドメイン参加済み**。 組織が所有し、オンプレミスの Active Directory と Microsoft Entra ID の両方に[参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)しているデバイス。 通常、デバイスは組織によって購入および管理され、System Center Configuration Manager によって管理されます。
    - **Microsoft Entra ドメイン参加済み**。 組織が所有し、組織の Microsoft Entra テナントに参加しているデバイス。 通常、Microsoft Entra ID に参加し、[Microsoft Intune](https://www.microsoft.com/microsoft-365/enterprise-mobility-security/microsoft-intune) などのサービスによって管理されている、組織によって購入および管理されるデバイスです。
    - **Microsoft Entra 登録済み**。 会社のリソースへのアクセスに使用される個人のデバイスなど、組織が所有していないデバイス。 組織は、デバイスを[モバイル デバイス管理 (MDM)](https://www.microsoft.com/itshowcase/mobile-device-management-at-microsoft) 経由で登録するか、リソースにアクセスするための登録なしで[モバイル アプリケーション管理 (MAM)](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-supported-intune-apps) を経由するよう強制するか、いずれかを求める場合があります。 Microsoft Intune などのサービスは、この機能を提供します。
- **グループ オブジェクト**には、リソース アクセスの割り当て、コントロールの適用、または構成を目的としたオブジェクトが含まれます。 グループ オブジェクトには、名前、説明、グループ メンバー、グループ所有者、グループの種類など、グループに関する必要な情報をもつ属性が含まれます。 Microsoft Entra ID のグループは、組織の要件に基づいて複数のフォームを取得します。 これらの要件は、Microsoft Entra ID を使用して満たされるか、オンプレミスの Active Directory Domain Services (AD DS) から同期できます。

    - **割り当てられたグループ**。 割り当てられたグループでは、ユーザーは手動でグループに追加されるか、あるいはグループから削除され、オンプレミスの AD DS から同期されるか、あるいは自動化されてスクリプト化されたワークフローの一部として更新されます。 割り当てられたグループは、オンプレミスの AD DS から同期することも、Microsoft Entra ID をホームとすることもできます。
    - **動的メンバーシップ グループ**。 属性ベースの動的メンバーシップ グループでは、ユーザーは定義された属性に基づいて自動的にグループに割り当てられます。 このシナリオにより、ユーザー オブジェクト内に保持されているデータに基づいて、グループ メンバーシップを動的に更新できます。 動的メンバーシップ グループは、Microsoft Entra ID のみに所属することができます。

**Microsoft アカウント (MSA)**. Microsoft アカウント (MSA) を使用して Azure サブスクリプションとテナントを作成できます。 Microsoft アカウントは個人アカウント (組織アカウントとは対照的に) であり、開発者が、トライアル シナリオで使用するのが一般的です。 使用する場合、個人アカウントは常に Microsoft Entra テナントのゲストになります。

### Microsoft Entra 機能領域

これらの機能領域は、分離された環境に関連する Microsoft Entra ID によって提供されます。 Microsoft Entra ID の機能の詳細については、「[Microsoft Entra ID とは](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra)」を参照してください。

#### 認証

**[認証]** : Microsoft Entra ID は、Open ID Connect、OAuth、SAML などのオープン標準に準拠した認証プロトコルのサポートを提供しています。 また、Microsoft Entra ID には、Active Directory フェデレーション サービス (AD FS) (AD FS) などの既存のオンプレミス ID プロバイダーをフェデレーションして、Microsoft Entra 統合アプリケーションへのアクセスを認証するための機能も用意されています。

Microsoft Entra ID には、組織がリソースへのアクセスをセキュリティで保護するために使用できる、業界をリードする強力な認証オプションが用意されています。 Microsoft Entra 多要素認証、デバイス認証、パスワードレス機能により、組織は従業員の要件に合った強力な認証オプションをデプロイできます。

**シングル サインオン (SSO)**。 シングル サインオンを使用すると、ユーザーは 1 つのアカウントで 1 回サインインすることで、ドメイン参加済みデバイス、会社のリソース、サービスとしてのソフトウェア (SaaS) アプリケーション、すべての Microsoft Entra 統合アプリケーションなど、ディレクトリを信頼するすべてのリソースにアクセスできます。 詳細については、「[Microsoft Entra ID でのアプリケーションへのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を参照してください。

#### 認可

**リソース アクセスの割り当て**。 Microsoft Entra ID では、リソースへのアクセスを、セキュリティで保護された状態で提供します。 Microsoft Entra ID のリソースへのアクセス権の割り当てには、次の 2 つの方法があります。

- **ユーザーの割り当て**: ユーザーにリソースへのアクセス権が直接割り当てられ、適切なロールまたはアクセス許可がユーザーに割り当てられます。
- **グループの割り当て**: 1 人以上のユーザーを含むグループがリソースに割り当てられ、適切なロールまたはアクセス許可がグループに割り当てられます

**アプリケーションのアクセス ポリシー**。 Microsoft Entra ID には、組織のアプリケーションへのアクセスをさらに制御し、セキュリティで保護する機能が用意されています。

**条件付きアクセス**。 Microsoft Entra ID 条件付きアクセス ポリシーは、Microsoft Entra ID リソースにアクセスするときにユーザーとデバイスのコンテキストを承認フローに取り込むためのツールです。 組織は、ユーザー、リスク、デバイス、ネットワーク コンテキストに基づいて認証を許可、拒否、または強化するために、条件付きアクセス ポリシーの使用を検討する必要があります。 詳細については、「[Microsoft Entra の条件付きアクセスのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/)」を参照してください。

**Microsoft Entra ID 保護**。 この機能によって、組織は、ID ベースのリスクの検出と修復の自動化、リスク調査、さらなる分析のためのサードパーティ製ユーティリティへのリスク検出データのエクスポートを実行できます。 詳細については、「[Microsoft Entra ID 保護の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)」を参照してください。

#### 管理

**ID 管理**。 Microsoft Entra ID には、ユーザー ID、グループ ID、およびデバイス ID のライフサイクルを管理するためのツールが用意されています。 [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) を使用すると、組織は現在のオンプレミスの ID 管理ソリューションをクラウドに拡張できます。 Microsoft Entra Connect は、Microsoft Entra ID でのこれらの ID の プロビジョニング、プロビジョニング解除、更新を管理します。

Microsoft Entra ID には、組織が ID を管理したり、Microsoft Entra ID 管理を既存のワークフローや自動化に統合したりできるようにするためのポータルと Microsoft Graph API も用意されています。 Microsoft Graph の詳細については、「[Microsoft Graph API を使用する](https://learn.microsoft.com/ja-jp/graph/use-the-api)」を参照してください。

**デバイスの管理**。 Microsoft Entra ID は、クラウドおよびオンプレミスのデバイス管理インフラストラクチャのライフサイクルと統合を管理するために使用されます。 また、クラウドまたはオンプレミスのデバイスから組織のデータへのアクセスを制御するポリシーを定義するためにも使用されます。 Microsoft Entra ID では、ディレクトリ内のデバイスのライフサイクル サービスと、認証を有効にする資格情報のプロビジョニングが提供されます。 さらに、信頼レベルというシステム内のデバイスの重要な属性も管理します。 この詳細は、リソース アクセス ポリシーを設計するときに重要です。 詳細については、「[Microsoft Entra デバイス管理のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/devices/)」を参照してください。

**構成管理**。 Microsoft Entra ID には、サービスが確実に組織の要件に合わせて構成されるように構成し管理する必要があるサービス要素があります。 ドメイン管理、SSO 構成、アプリケーション管理などは、これらの要素のごく一部です。 Microsoft Entra ID にはポータルと Microsoft Graph API が用意されており、組織がこれらの要素を管理したり、既存のプロセスに統合したりできます。 Microsoft Graph の詳細については、「[Microsoft Graph API を使用する](https://learn.microsoft.com/ja-jp/graph/use-the-api)」を参照してください。

#### ガバナンス

**アイデンティティ ライフサイクル**。 Microsoft Entra ID には、ディレクトリ内で (外部 ID を含む) ID を作成、取得、削除、更新する機能が用意されています。 Microsoft Entra ID には、組織のニーズに合わせて維持されるよう [ID ライフサイクルを自動化するためのサービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)も用意されています。 たとえば、アクセス レビューを使用して、指定した期間にサインインしていない外部ユーザーを削除します。

**レポートと分析**。 ID ガバナンスの重要な側面は、ユーザー アクションの可視性です。 Microsoft Entra ID は、環境のセキュリティと使用パターンに関する分析情報を提供します。 これらの分析情報には、次に関する詳細情報が含まれます。

- ユーザーがアクセスする対象
- どこでアクセスするか
- 使用するデバイス
- アクセスに使用されるアプリケーション

Microsoft Entra ID では、Microsoft Entra ID 内で実行されているアクションに関する情報や、セキュリティ リスクに関するレポートも提供されます。 詳細については、「[Microsoft Entra レポートと監視](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/)」を参照してください。

**監査**。 監査により、Microsoft Entra ID 内の特定の機能によって行われるすべての変更について、ログによる追跡可能性を提供します。 監査ログで確認できるアクティビティの例としては、ユーザー、アプリ、グループ、ロール、ポリシーの追加や削除など、Microsoft Entra ID 内のあらゆるリソースに対して行われる変更があります。 Microsoft Entra ID のレポートを使用すると、サインイン アクティビティ、危険なサインイン、およびリスクのフラグが設定されたユーザーを監査できます。 詳細については、「[Azure portal の監査アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)」を参照してください。

**アクセス認定**。 アクセス認定とは、ユーザーがある時点であるリソースにアクセスする権利があることを証明するプロセスです。 Microsoft Entra アクセス レビューでは、グループやアプリケーションのメンバーシップを継続的に確認し、アクセスが必要かどうか、または削除する必要があるかどうかを判断するための分析情報を提供します。 この認定により、組織はグループ メンバーシップ、エンタープライズ アプリケーションへのアクセス、ロールの割り当てを効果的に管理して、適切なユーザーのみが引き続きアクセスするようにできます。 詳細については、「[Microsoft Entra アクセス レビューとは](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)」を参照してください。

**特権アクセス**。 [Microsoft Entra Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) (PIM) によって、時間ベースおよび承認ベースのロールのアクティブ化が提供され、Azure リソースに対する過剰、不要、または誤用であるアクセス許可のリスクが軽減されます。 特権の露出時間を短縮し、レポートとアラートを通じて使用状況の可視性を高めることにより、特権アカウントを保護するために使用されます。

#### セルフサービス管理

**資格情報の登録**。 Microsoft Entra ID には、組織のヘルプデスクのワークロードを軽減するために、ユーザー ID ライフサイクルのすべての側面を管理する機能とセルフサービス機能が用意されています。

**グループ管理**。 Microsoft Entra ID には、ユーザーがリソース アクセス用のグループのメンバーシップを要求できる機能が用意されています。 Microsoft Entra ID を使用して、リソースまたはコラボレーションのセキュリティ保護に使用できるグループを作成します。 したがって、組織は、設定した適切な制御を活用してこれらの機能を制御できます。

#### コンシューマーの ID とアクセスの管理 (IAM)

**Azure AD B2C**。 [Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview) は、顧客 ID とアクセス管理のための Microsoft のレガシ ソリューションです。 2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、FAQ で [Azure AD B2C を引き続き購入できますか](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) ? を参照してください。

**Microsoft Entra External ID** は、組織外のユーザーと共同作業するための強力なソリューションを組み合わせた次世代製品です。 外部 ID 機能を使用すると、外部 ID がアプリとリソースに安全にアクセスできるようにすることができます。 外部パートナー、消費者、ビジネス顧客のいずれと連携している場合でも、ユーザーは会社または政府が発行したアカウントから Google や Facebook などのソーシャル ID プロバイダーまで、独自の ID を持ち込むことができます。 詳細については、「 [Microsoft Entra External ID の概要」を](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-generative-ai"} -->
## Microsoft Entra を使用して生成 AI をセキュリティで保護する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-generative-ai
- Service: entra / architecture
- Article date: 2025-06-20
- Summary: Microsoft Entra を使用して、Generative AI (Gen AI) がもたらす特定のセキュリティ上の課題を軽減し、組織のセキュリティを確保する方法について説明します。

デジタル環境が急速に進化するにつれて、さまざまな業界で、イノベーションを促進し、生産性を向上するために [生成人工知能](https://learn.microsoft.com/ja-jp/ai/playbook/technology-guidance/generative-ai/) (Gen AI) を採用する企業が増えています。 最近の[調査](https://techcommunity.microsoft.com/t5/security-compliance-and-identity/security-for-ai-how-to-secure-and-govern-ai-usage/ba-p/4082269)によると、企業の 93% が AI 戦略を実装中または開発中であることが示されています。 リスクリーダーのほぼ同割合が、関連するリスクに対処するための準備が不十分、またはある程度しかできていないと感じていると報告しています。 Gen AI を業務に統合する際には、重大なセキュリティとガバナンスのリスクを軽減する必要があります。

Microsoft Entra は、AI アプリケーションを安全に管理し、アクセスを適切に制御し、機密データを保護するための包括的な機能スイートを提供します。

- [Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/graph/api/resources/identitygovernance-overview)
- [Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [Microsoft Entra Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) (PIM)
- [Microsoft Purview インサイダー リスク](https://learn.microsoft.com/ja-jp/purview/insider-risk-management-adaptive-protection)

この記事では、Gen AI がもたらすセキュリティ上の特定の課題と、Microsoft Entra によって提供される機能を使用して、それらの課題に対処する方法について詳しく説明します。

### すべての過剰な特権 ID を検出する

[最小特権の原則](https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access)に準拠するために、ユーザーが持っているアクセス許可が適切であることを確認します。 テレメトリによると、90% 以上の ID は付与された権限の 5% 未満しか使用していません。 これらのアクセス許可の 50% 以上が高リスクです。 セキュリティ侵害されたアカウントは、致命的な損害を引き起こす可能性があります。

マルチクラウド環境の管理は、ID およびアクセス管理 (IAM) チームとセキュリティ チームが部門横断的に連携する必要があることが多いため、困難です。 マルチクラウド環境では、ID、アクセス許可、リソースに対する包括的なビューを制限できます。 この限定的なビューにより、過剰な特権を持つロールや過剰に許可されたアカウントを持つ ID に対する攻撃対象領域が拡大します。 組織がマルチクラウドを導入するにつれて、高いアクセス許可を持つ未使用のアカウントが侵害されるリスクが高まります。

#### 人間以外のアカウントを識別する

人間以外のアカウントには反復可能なパターンがあり、時間の経過とともに変化する可能性は低くなります。 これらのアカウントを特定する場合は、 [ワークロードまたはマネージド ID の使用を検討してください](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview)。

ロールを[ゼロ トラスト](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)の最小限の特権というアクセス セキュリティ原則に絞り込みます。 [スーパー ID](https://techcommunity.microsoft.com/blog/identity/securing-access-to-any-resource-anywhere/4120308) ([サーバーレス関数やアプリ](https://azure.microsoft.com/resources/cloud-computing-dictionary/what-is-serverless-computing)など) には細心の注意を払います。 Gen AI アプリケーションのユースケースを考慮します。

#### Microsoft Entra ロールに Just-In-Time アクセスを適用する

Microsoft Entra Privileged Identity Management (PIM) は、Microsoft Entra ID、Azure、およびその他の Microsoft Online Services (Microsoft 365 や Microsoft Intune など) のリソースへのアクセスを管理、制御、監視するのに役立ちます。 PIM が有効になっていない特権ユーザーには、常時アクセス権が付与されます (特権が必要ない場合でも、割り当てられたロールでは常にアクセス権が与えられます)。 PIM [の検出および分析情報](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-security-wizard) ページには、永続的なグローバル管理者の割り当て、高度な特権ロールを持つアカウント、および特権ロールの割り当てを持つサービス プリンシパルが表示されます。 これらの権限が表示されたら、自分の環境で正常な権限であるかどうかをメモします。 これらのロールを削減するか、PIM 対応の割り当てを使用して Just-In-Time (JIT) アクセスに絞ることを検討します。

検出と分析情報のページ内で直接、特権ロールを PIM 対応にして、常時アクセスを減らすことができます。 特権アクセスが不要な場合は、割り当てを完全に削除できます。 グローバル管理者に対して、自分のアクセスを定期的にレビューするように求めるアクセス レビューを作成できます。

### アクセス制御を有効にする

アクセス許可の拡大により、不正アクセスや企業の機密データが操作されるリスクが生じます。 すべての企業リソースに適用するのと同じガバナンス ルールを使用して AI アプリケーションを保護します。 この目標を達成するには、ID ガバナンスと Microsoft Entra 条件付きアクセスを使用して、すべてのユーザーと会社のリソース (Gen AI アプリを含む) に対するきめ細かなアクセス ポリシーを定義して展開します。

適切なユーザーのみが適切なリソースに対して適切なアクセス レベルを持っていることを確認します。 エンタイトルメント管理、ライフサイクル ワークフロー、アクセス要求、レビュー、有効期限などの制御を通じて、アクセス ライフサイクルを大規模に自動化します。

ライフサイクル ワークフローを使用して [Joiner、Mover、Leaver (JML)](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows) のユーザー プロセスを管理し、ID ガバナンスでアクセスを制御します。

#### ユーザー アクセスを管理するためにポリシーと制御を構成する

ID ガバナンスの[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用して、アプリケーション、グループ、Teams、SharePoint サイトへのアクセスを許可するユーザーを、複数ステージの承認ポリシーを使用して制御します。

 部門やコスト センターなどのユーザー プロパティに基づいてユーザーにリソースへのアクセス権を自動的に付与するには、エンタイトルメント管理で[自動割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)ポリシーを構成します。 これらのプロパティが変更されたら、ユーザー アクセスは削除します。

アクセス権を適切なレベルにするため、[有効期限](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy)と定期的な[アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create)、またはそのいずれかをエンタイトルメント管理ポリシーに適用することをお勧めします。 これらの要件により、ユーザーが、時間制限付きの割り当てや定期的なアプリケーション アクセス レビューで無期限にアクセス権を保持することがなくなります。

#### Microsoft Entra 条件付きアクセスを使用して組織のポリシーを適用する

条件付きアクセスでは、決定のためにシグナルをまとめ、組織のポリシーを適用します。 条件付きアクセスは Microsoft の[ゼロ トラスト ポリシー エンジン](https://learn.microsoft.com/ja-jp/security/zero-trust/deploy/identity)です。これは、さまざまな情報源からのシグナルを考慮してポリシーを決定します。

最小限の特権という原則を適用し、適切なアクセス制御を適用して、組織のセキュリティを[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)を使用して確保します。 条件付きアクセス ポリシーは、特定の基準を満たす ID が、MFA やデバイスのコンプライアンス状態などの特定の要件を満たしている場合にのみリソースにアクセスできる if-then ステートメントと考えてください。

- 認証方法の組み合わせを指定してリソースにアクセスする、[認証強度](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths)という条件付きアクセス制御を使用します。 ユーザーは、[フィッシングに強い多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength) (MFA) を完了して Gen AI アプリにアクセスする必要があります。
- [Microsoft Purview 適応型保護](https://learn.microsoft.com/ja-jp/purview/insider-risk-management-adaptive-protection)をデプロイして、AI 使用時のリスクを軽減および管理します。 [インサイダー リスク](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#insider-risk)条件を使用して、[インサイダー リスクが高いユーザーによる Gen AI アプリへのアクセスをブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-insider-block)します。
- [Microsoft Intune デバイス コンプライアンス ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)をデプロイし、デバイス コンプライアンスシグナルを条件付きアクセス ポリシーの決定に組み込みます。 デバイス コンプライアンス条件を使用して、[ユーザーが Gen AI アプリにアクセスするためにコンプライアンス デバイスを所有していることを必須とします。](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)。

条件付きアクセス ポリシーを展開したら、[条件付きアクセスのギャップ アナライザー ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-conditional-access-gap-analyzer)を使用して、これらのポリシーを適用していないサインインとアプリケーションを特定します。 ベースラインのアクセス制御を確実に行うため、[すべてのリソースを対象とする少なくとも 1 つの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)をデプロイすることをお勧めします。

#### 従業員の ID ライフサイクルを大規模に管理するための自動化を実現する

タスクを実行するために本当に必要な場合にのみ、ユーザーに情報とリソースへのアクセスを許可します。 このアプローチにより、機密データへの不正アクセスが防止され、潜在的なセキュリティ侵害の影響が最小限に抑えられます。 自動化された[ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/id-governance/what-is-provisioning)を使用すると、必要のないアクセス権の付与を減らすことができます。

ID ガバナンスの[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)を使用すると、Microsoft Entra ユーザーのライフサイクル プロセスを大規模に自動化できます。 ワークフロー タスクを自動化すると、主要なイベントが発生したときのユーザー アクセスの規模を適切に設定できます。 イベントの例には、新しい従業員が組織で勤務を開始する前、従業員のステータスが変更になったとき、従業員が組織を退職したときなどがあります。

#### 高い特権を持つ管理者ロールのアクセスを管理する

ビジネス上または運用上の理由により、ID に高度な特権アクセスが必要となる場合があります ([緊急アクセス用](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)アカウントなど)。

[特権アカウント](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-accounts)には、最も高い保護レベルが求められます。 特権アカウントが侵害されると、組織の運営に重大な影響を及ぼす可能性があります。

[Microsoft Azure](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/rbac-and-directory-admin-roles) と [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles) では、承認されたユーザーのみを管理者[ロール](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user)に割り当てます。 Microsoft では、少なくとも以下のロールに対してフィッシング対策 MFA を必須にすることを推奨しています。

- グローバル管理者
- アプリケーション管理者
- 認証管理者
- 課金管理者
- クラウド アプリケーション管理者
- 条件付きアクセス管理者
- Exchange 管理者
- ヘルプデスク管理者
- パスワード管理者
- 特権認証管理者
- 特権ロール管理者
- セキュリティ管理者
- SharePoint 管理者
- ユーザー管理者

組織では、要件に基づいてロールを含めるか除外するかを選択できます。 ロール メンバーシップを確認するには、[ディレクトリ ロールのアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)を構成して使用します。

Privileged Identity Management (PIM) と Just-In-Time (JIT) アクセスを使用して、ロールベースのアクセス制御を適用します。 PIM は、有効期限付きのアクセス権を割り当てることで、リソースへの JIT アクセスを提供します。 永続的なアクセス権を排除して、アクセス権の過剰や悪用のリスクを減らします。 PIM では、承認と正当化の要件、ロールのアクティブ化での MFA の適用、監査履歴を使用できます。

#### 外部ゲスト ID のライフサイクルとアクセスを管理する

ほとんどの組織では、エンドユーザーが[ビジネス パートナー (B2B)](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) やベンダーを共同作業に招待し、アプリケーションへのアクセスを提供します。 通常、コラボレーション パートナーはオンボーディング プロセス中にアプリケーション アクセス権を受け取ります。 コラボレーションに明確な終了日がない場合、ユーザーがいつアクセスする必要がなくなるかが明確ではありません。

[エンタイトルメント管理機能](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用すると、リソースへのアクセスでの[外部 ID のライフサイクルを自動化](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#manage-the-lifecycle-of-external-users)できます。 エンタイトルメント管理を使用して、アクセス権を管理するプロセスと手順を確立します。 リソースは、アクセス パッケージを使用して公開します。 このアプローチは、リソースへの外部ユーザー アクセスを追跡し、問題の複雑さを軽減するのに役立ちます。

従業員に外部ユーザーとの共同作業を許可すると、従業員は組織外から任意の数のユーザーを招待できるようになります。 [外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview) がアプリケーションを使用する場合は、[Microsoft Entra アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)が、アクセスの確認をサポートします。 リソース所有者、外部 ID 自体、または信頼できる別の委任された人物に、継続的なアクセスが必要かどうかを証明してもらえます。

Microsoft Entra アクセス レビューを使用すると、外部 ID がテナントにサインインするのをブロックし、30 日後にテナントから外部 ID を削除することができます。

#### Microsoft Purview を使用してデータのセキュリティとコンプライアンスの保護を実施する

[Microsoft Purview](https://learn.microsoft.com/ja-jp/purview/ai-microsoft-purview) を使用すると、AI の使用に関連するリスクを軽減および管理できます。 対応する保護とガバナンスの制御を実装します。 Microsoft Purview AI Hub は現在プレビュー段階です。 これは、使いやすいグラフィカル ツールとレポートを提供し、組織内での AI の使用に関する分析情報をすばやく得ることができます。 また、ワンクリック ポリシーにより、データを保護し、規制要件に準拠できます。

条件付きアクセスでは、Microsoft Purview 適応型保護を有効にして、インサイダー リスクを示す動作にフラグを設定できます。 適応型保護は、[他の条件付きアクセス制御](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/recommendation-insider-risk-condition)を適用して、ユーザーに MFA の入力を求めたり、ユース ケースに基づいて他のアクションを提供する際に適用します。

### アクセスを監視する

監視は、潜在的な脅威や脆弱性を早期に検出するために重要です。 セキュリティ侵害を防ぎ、データの整合性を維持するため、[異常なアクティビティや構成の変更](https://learn.microsoft.com/ja-jp/entra/id-governance/governance-custom-alerts#custom-alert-notifications)を監視します。

環境へのアクセスを継続的に確認して監視し、疑わしいアクティビティを検出します。 アクセス許可クリープを回避し、何かが意図せずに変更されたらアラートを受け取るようにします。

- 構成の変更や疑わしいアクティビティについて環境を事前に監視するには、[Microsoft Entra ID 監査ログを Azure Monitor に統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)します。
- [セキュリティ アラート](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts)を構成して、ユーザーが特権ロールをアクティブ化するタイミングを監視します。
- [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks) を使用して、通常とは異なるユーザーの使用パターンを監視します。 通常とは異なる使用では、悪意のある人物が Gen AI ツールを不正に使用している可能性を示している可能性があります。

シナリオによっては、AI アプリケーションの使用が特定の時期のみの場合があります。 たとえば、金融アプリは税務・監査シーズン以外では使用率が低くなる可能性がありますが、小売アプリはホリデー シーズン中に使用率が急増する可能性があります。 ライフサイクル ワークフローを使用して、長期間使用されていないアカウント (特に外部パートナー アカウント) を無効にします。 JIT または一時的なアカウントの非アクティブ化がより適切な場合は、季節性を考慮してください。

理想的なのは、すべてのユーザーがアクセス ポリシーに従って、組織のリソースへのアクセスをセキュリティで保護することです。 個々のユーザーまたはゲストを除外する条件付きアクセス ポリシーを使用する必要がある場合は、ポリシー例外の見落としを回避できます。 Microsoft Entra アクセス レビューを使用して、定期的な例外レビューの証明を監査人に提供します。

- [定期的に実行されるようにレポートを構成します](https://learn.microsoft.com/ja-jp/entra/permissions-management/how-to-audit-trail-results)。 特定のユース ケース、特に Gen AI アプリにアクセスする必要がある ID に対して、[カスタム レポートを構成](https://learn.microsoft.com/ja-jp/entra/permissions-management/report-create-custom-report)します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-introduction"} -->
## 委任された管理と分離された環境の概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-introduction
- Service: entra / architecture
- Article date: 2024-10-08
- Summary: Microsoft Entra ID での委任された管理と分離された環境の概要。

Microsoft Entra のシングルテナント アーキテクチャは、委任された管理の機能を備えており、多くの場合、環境を分離するのに十分です。 組織では、単一のテナントでは不可能なレベルの分離が必要になる場合があります。

この記事では、次の点を理解することが重要です。

- 単一テナントの操作と機能
- Microsoft Entra ID の管理単位 (AU)
- Azure リソースと Microsoft Entra テナント間の関係
- 分離を推進する要件

### セキュリティ境界としての Microsoft Entra テナント

Microsoft Entra テナントは、組織のアプリケーションとリソースに ID とアクセス管理 (IAM) 機能を提供します。

ID とは、リソースへのアクセスを認証、承認されたディレクトリ オブジェクトです。 人間と人間以外に与えられる ID の ID オブジェクトがあります。 これらを区別するために、人間に与えられる ID を ID、人間以外に与えられる ID をワークロード ID と呼びます。 人間以外のエンティティには、アプリケーション オブジェクト、サービス プリンシパル、マネージド ID、デバイスがあります。 一般的に、ワークロード ID は、ソフトウェア エンティティがシステムで認証を行うために使用されます。

- **ID** - 人間を表すオブジェクト
- **ワークロード ID**- ワークロード ID はアプリケーション、サービス プリンシパル、マネージド ID です。
    - ワークロード ID は、他のサービスやリソースを認証し、アクセスします。

詳細については、[ワークロード ID の概要](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview)を参照してください。

Microsoft Entra テナントは、管理者が制御する ID セキュリティ境界です。 このセキュリティ境界内では、サブスクリプション、管理グループ、リソース グループの管理を委任して、Azure リソースの管理制御をセグメント化できます。 これらのグループは、ポリシーと設定のテナント全体の構成に依存します。

Microsoft Entra ID は、アプリケーションや Azure リソースへのアクセスをオブジェクトに許可します。 Microsoft Entra ID を信頼する Azure リソースやアプリケーションは、Microsoft Entra ID で管理できます。 ベスト プラクティスに従い、テスト環境で環境を設定します。

#### Microsoft Entra ID を使用するアプリにアクセスする

アプリケーションへのアクセス権を ID に付与します。

- Exchange Online、Microsoft Teams、SharePoint Online などの Microsoft 生産性サービス
- Azure Sentinel、Microsoft Intune、Microsoft Defender Advanced Threat Protection (ATP) などの Microsoft IT サービス
- Azure DevOps や Microsoft Graph API などの Microsoft 開発者ツール
- Salesforce や ServiceNow などの SaaS ソリューション
- Microsoft Entra アプリケーション プロキシなどのハイブリッド アクセス機能と統合されたオンプレミス アプリケーション
- カスタム開発アプリケーション

Microsoft Entra ID を使用するアプリケーションでは、信頼された Microsoft Entra テナントでディレクトリ オブジェクトを構成し、管理する必要があります。 ディレクトリ オブジェクトの例としては、アプリケーションの登録、サービス プリンシパル、グループ、[スキーマ属性拡張機能](https://learn.microsoft.com/ja-jp/graph/extensibility-overview)などがあります。

#### Azure リソースへのアクセス

Microsoft Entra テナントのユーザー、グループ、サービス プリンシパル オブジェクト (ワークロード ID) にロールを付与します。 詳細については、[Azure ロールベースのアクセス制御 (RBAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview) と [Azure 属性ベースのアクセス制御 (ABAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview) を参照してください。

Azure RBAC を使用すると、セキュリティ プリンシパル、ロール定義、スコープによって決定されるロールに基づいてアクセスを提供できます。 Azure ABAC では、アクションの属性に基づいてロールの割り当て条件が追加されます。 より詳細なアクセス制御を行う場合は、ロールの割り当て条件を追加します。 RBAC ロールが割り当てられた Azure リソース、リソース グループ、サブスクリプション、管理グループにアクセスします。

[マネージド ID をサポート](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)する Azure リソースを使用すると、リソースで Microsoft Entra テナント境界内の他のリソースに対する認証、アクセス権の取得、ロールの割り当てを行うことができます。

サインインに Microsoft Entra ID を使用するアプリケーションでは、コンピューティングやストレージなどの Azure リソースを使用できます。 たとえば、Azure で実行され、Microsoft Entra ID を信頼して認証を行うカスタム アプリケーションでは、ディレクトリ オブジェクトと Azure リソースが使用されます。 Microsoft Entra テナント内のすべての Azure リソースは、テナント全体の [Azure クォータと制限](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-subscription-service-limits)に影響します。

#### ディレクトリ オブジェクトへのアクセス

ID、リソース、およびそれらのリレーションシップは、Microsoft Entra テナントのディレクトリ オブジェクトとして表されます。 例としては、ユーザー、グループ、サービス プリンシパル、アプリの登録などがあります。 Microsoft Entra テナント境界内に一連のディレクトリ オブジェクトがあると、次の機能を利用できます。

- **可視性**: ID では、アクセス許可に基づいて、リソース、ユーザー、グループ、アクセス使用状況レポート、監査ログを検出または列挙できます。 たとえば、あるディレクトリ メンバーは、Microsoft Entra ID の[既定のユーザー アクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)で、ディレクトリ内のユーザーを検出できます。
- **アプリケーションへの効果**: アプリケーションは、ビジネス ロジックの一部として Microsoft Graph を介してディレクトリ オブジェクトを操作できます。 一般的な例としては、ユーザー属性の読み取りまたは設定、ユーザーの予定表の更新、ユーザーに代わってメールを送信するなどがあります。 アプリケーションがテナントに影響を与えることを許可するには、同意が必要です。 管理者はすべてのユーザーについて同意できます。 詳細については、「[Microsoft ID プラットフォームでのアクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-admin-consent)」を参照してください。
- **調整とサービスの制限**: リソースのランタイム動作によって、過剰使用やサービスの低下を防ぐために[調整](https://learn.microsoft.com/ja-jp/graph/throttling)をトリガーする可能性があります。 調整は、アプリケーション、テナント、またはサービス レベル全体で行うことができます。 一般的に、調整は、テナント内またはテナント間でアプリケーションに大量の要求がある場合に発生します。 同様に、アプリケーションのランタイム動作に影響を与える可能性のある [Microsoft Entra サービスの制限と制約](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)があります。

    注

    アプリケーションのアクセス許可は慎重に行う必要があります。 たとえば、Exchange Online では、[アプリケーションのアクセス許可をメールボックスとアクセス許可にスコープ設定](https://learn.microsoft.com/ja-jp/graph/auth-limit-mailbox-access)します。

### ロール管理の管理単位

管理単位では、ロールのアクセス許可が、組織の任意の部分に限定されます。 管理単位を使用して、[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)のロールを地域のサポート スペシャリストに委任できます。そうすれば、そのスペシャリストが自分のサポートするリージョンのユーザーを管理できます。 管理単位は、他の Microsoft Entra リソースのコンテナーとして使用できる Microsoft Entra リソースです。 管理単位に含めることができるのは、以下のとおりです。

- ユーザー
- グループ
- デバイス

次の図では、AU が組織構造に基づいて Microsoft Entra テナントをセグメント化しています。 この方法は、部署またはグループが専用の IT サポート スタッフを割り当てる場合に便利です。 AU を使用して、管理単位に限定された特権アクセス許可を提供します。

[Image: Microsoft Entra 管理単位の図。]

詳細については、「[Microsoft Entra ID の管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)」を参照してください。

#### リソースの分離を行う一般的な理由

特殊なアクセス要件を持つリソースなど、セキュリティ上の理由からリソースのグループを他のリソースから分離することがあります。 このアクションは AU の適切なユース ケースです。 ユーザーやセキュリティ プリンシパルのリソース アクセス、ロールを決定します。 リソースを分離する理由:

- 開発者チームは、反復を安全に行えることが必要です。 ただし、Microsoft Entra ID への書き込みがあるアプリの開発とテストは、書き込み操作によって Microsoft Entra テナントに影響を与える場合があります。
    - SharePoint サイト、OneDrive、Microsoft Teams など、Office 365 コンテンツを変更する可能性がある新しいアプリケーション
    - MS Graph または類似の API を大規模に使用した、ユーザーのデータを変更する可能性のあるカスタム アプリケーション。 たとえば、Directory.ReadWrite.All が付与されているアプリケーションです。
    - 大量のオブジェクト セットを更新する DevOps スクリプト
    - Microsoft Entra 統合アプリの開発者は、テスト用にユーザー オブジェクトを作成できることが必要です。 ユーザー オブジェクトは、運用環境のリソースにアクセスできません。
- 他のリソースに影響を与える可能性がある非運用の Azure リソースとアプリケーション。 たとえば、新しい SaaS アプリでは、運用インスタンスとユーザー オブジェクトからの分離が必要です。
- 検出、列挙、または管理者によく引き継ぎから保護されるシークレット リソース

### テナントの構成

Microsoft Entra ID の構成設定は、対象を絞った管理アクション、またはテナント全体の管理アクションを通じて、Microsoft Entra テナント内のリソースに影響を与える可能性があります。

- **外部 ID**: 管理者は、テナントでプロビジョニングする外部 ID を識別して制御します。
    - テナントで外部 ID を許可するかどうか
    - どのドメインから外部 ID を追加するか
    - ユーザーが他のテナントからユーザーを招待できるかどうか
- **名前付きの場所**: 管理者は、次の目的で名前付きの場所を作成します。
    - 場所からのサインインをブロックする
    - 多要素認証などの条件付きアクセス ポリシーをトリガーする
    - セキュリティ要件をバイパスする
- **セルフサービス オプション**: 管理者は、セルフサービス パスワード リセットを設定し、テナント レベルで Microsoft 365 グループを作成します。

グローバル ポリシーでオーバーライドしない場合は、一部のテナント全体の構成をスコープ設定できます。

- テナント構成では、外部 ID が許可されます。 リソース管理者は、これらの ID をアクセスから除外できます。
- テナント構成では、個人デバイスの登録が許可されます。 リソース管理者は、アクセスからデバイスを除外できます。
- 名前付きの場所が構成されます。 リソース管理者は、アクセスを許可または除外するポリシーを構成できます。

#### 構成の分離を行う一般的な理由

管理者によって制御される構成は、リソースに影響します。 テナント全体にわたる構成の中には、リソースに非適用にしたり部分的に適用したりするようポリシーをスコープ設定できるものもありますが、できない構成もあります。 リソースに固有の構成がある場合は、そのリソースを別のテナントに分離します。 以下に例を示します。

- テナント全体のセキュリティまたはコラボレーション体制と競合する要件があるリソース
    - 許可される認証の種類、デバイス管理ポリシー、セルフサービス、外部 ID の ID 証明など
- 環境全体に認定をスコープ設定するコンプライアンス要件
    - このアクションには、すべてのリソースと Microsoft Entra テナントが含まれます (特に、要件が他の組織リソースと競合しているか、他の組織リソースを除外している場合)
- 運用ポリシーまたは機密性の高いリソース ポリシーと競合する外部ユーザー アクセス要件
- Microsoft Entra テナントでホストされている、複数の国やリージョンにまたがる組織と企業。
    - たとえば、国やリージョン、または企業の子会社で使用されている設定やライセンスなど

### テナント管理

特権ロールをもつ Microsoft Entra テナントの ID には、前のセクションで説明した構成タスクを実行するための可視性とアクセス許可があります。 管理には、ユーザー、グループ、デバイスなどの ID オブジェクトの所有権が含まれます。 また、認証、承認などのテナント全体の構成のスコープ実装も含まれます。

#### ディレクトリ オブジェクトの管理

管理者は、ID オブジェクトがリソースにどのようにアクセスするか、またどのような状況でアクセスするかを管理します。 また、権限に基づいてディレクトリ オブジェクトを無効化、削除、変更します。 ID オブジェクトには、次のものがあります。

- **組織 ID**(次のようなもの) は、ユーザー オブジェクトによって表されます。
    - 管理者
    - 組織のユーザー
    - 組織の開発者
    - サービス アカウント
    - テスト ユーザー
- **外部 ID**は、組織外のユーザーを表します。
    - アカウント付きでプロビジョニングされている組織環境のパートナー、サプライヤー、またはベンダー
    - Azure B2B コラボレーションを介してプロビジョニングされているパートナー、サプライヤー、またはベンダー
- **グループ**は、オブジェクトによって表されます。
    - セキュリティ グループ
    - [Microsoft 365 グループ](https://learn.microsoft.com/ja-jp/microsoft-365/community/all-about-groups)
    - 動的グループ
    - 管理単位
- **デバイス**は、オブジェクトによって表されます。
    - Microsoft Entra ハイブリッド参加済みデバイス。 オンプレミスから同期されたオンプレミス コンピューター。
    - Microsoft Entra 参加済みデバイス
    - 従業員が職場のアプリケーションにアクセスするために使用する Microsoft Entra 登録済みモバイル デバイス
    - Microsoft Entra に登録済みの下位レベルのデバイス (レガシ)。 たとえば、Windows 2012 R2 などです。
- **ワークロード ID**
    - マネージド ID
    - サービス プリンシパル
    - アプリケーション

ハイブリッド環境では、ID は通常 [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) を使用してオンプレミスの環境から同期されます。

#### ID サービスの管理

特定のアクセス許可をもつ管理者は、リソース グループ、セキュリティ グループ、またはアプリケーションに対して、テナント全体のポリシーをどのように実装するかを管理します。 リソース管理を検討する際は、リソースをグループ化するか分離するかを決める次の理由を考慮してください。

- **グローバル管理者**は、テナントにリンクされている Azure サブスクリプションを制御します。
- **認証管理者ロールが割り当てられた ID** は、管理者以外のユーザーに、多要素認証または Fast IDentity Online (FIDO) 認証のための再登録を要求します。
- **条件付きアクセス管理者**は、組織所有のデバイスからユーザーがアプリにサインインするための条件付きアクセス ポリシーを作成します。 これらの管理者は、構成をスコープ指定します。 たとえば、テナントで外部 ID が許可されている場合は、リソースへのアクセスを除外できます。
- **クラウド アプリケーション管理者**は、アプリケーションのアクセス許可への同意をユーザーを代行して行います。

#### 管理の分離を行う一般的な理由

環境とそのリソースはだれが管理すべきでしょうか。 ある環境の管理者は、別の環境にアクセスできない場合があります。

- 重要なリソースに影響を与えるセキュリティ上のエラーと運用エラーのリスクを軽減するために、テナント全体の管理責任を分離する場合
- 環境を管理できる人を制約する規制が、市民権、居住地、クリアランス レベルなどの条件に基づいている場合

### セキュリティと運用面の考慮事項

Microsoft Entra テナントとそのリソース間の相互依存関係を考えると、侵害やエラーのセキュリティ リスクと運用上のリスクを理解することは重要です。 同期されたアカウントをもつフェデレーション環境で運用している場合、オンプレミスの侵害によって Microsoft Entra ID の侵害が発生するおそれがあります。

- **ID の侵害**: アクセス権を提供する管理者に十分な特権がある場合、テナントの境界では、ID に任意のロールが割り当てられます。 非特権 ID が侵害された場合の影響は大部分は封じ込めることができますが、管理者が侵害された場合は広範囲に及ぶ問題が発生する可能性があります。 たとえば、高度な特権を持つアカウントが侵害された場合、Azure リソースが侵害された状態になる可能性があります。 ID 侵害や不適切なアクターのリスクを軽減するには、[階層化された管理](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model)を実装し、[Microsoft Entra 管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task)について最小特権の原則に従うようにします。 テスト アカウントとテスト サービス プリンシパルがテスト アプリケーション外のリソースにアクセスしないようにする条件付きアクセス ポリシーを作成してください。 特権アクセス戦略の詳細については、「[特権アクセス: 戦略](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-strategy)」を参照してください。
- **フェデレーション環境の侵害**
- **信頼リソースの侵害**: Microsoft Entra テナントのコンポーネントが侵害された場合、テナントとリソースのレベルでのアクセス許可に基づいて、信頼リソースに影響が及ぶおそれがあります。 リソースの特権によって、侵害されたコンポーネントの影響が決まります。 書き込み操作を実行するために統合されたリソースは、テナント全体に影響する可能性があります。 [ゼロ トラストに関するガイダンス](https://learn.microsoft.com/ja-jp/azure/architecture/guide/security/conditional-access-zero-trust)に従うことで、侵害の影響を制限できます。
- **アプリケーション開発**: アプリケーション開発ライフサイクルの初期段階では、Microsoft Entra ID への書き込み特権がある場合にリスクがあります。 バグにより、意図せず Microsoft Entra オブジェクトに書き込みが行われて変更される可能性があります。 詳細については、[Microsoft ID プラットフォームのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity-platform/identity-platform-integration-checklist)を参照してください。
- **操作エラー**: 不適切なアクター、またはテナント管理者やリソース所有者による運用エラーが、セキュリティ インシデントの原因となる場合があります。 これらのリスクは、どのようなアーキテクチャでも発生します。 職務の分離、階層化された管理、最小限の特権の原則と次のベスト プラクティスを使用してください。 別のテナントは使用しないでください。

#### ゼロ トラスト原則

セキュアな設計に導くため、Microsoft Entra ID の設計戦略にゼロ トラスト原則を組み込んでください。 [ゼロ トラストを使用してプロアクティブなセキュリティを採用する](https://www.microsoft.com/security/business/zero-trust)ことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-multiple-tenants"} -->
## Microsoft Entra ID でセキュリティ保護するための複数のテナントでのリソースの分離 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-multiple-tenants
- Service: entra / architecture
- Article date: 2022-07-05
- Summary: Microsoft Entra ID の複数のテナントを使用したリソース分離の概要。

1 つのテナント境界内で管理を委任してもニーズを満たさない、特定のシナリオがあります。 このセクションでは、マルチテナント アーキテクチャの作成が必要になる場合がある要件について説明します。 組織の中には、2 つ以上の Microsoft Entra テナントを使用しているマルチテナントの組織もあります。 この場合、テナント間で独自のコラボレーションを行ったり、独自の管理要件を設定したりすることが必要な場合があります。 マルチテナント アーキテクチャでは、管理のオーバーヘッドと複雑さが増すため、注意して使用する必要があります。 シングル テナントでニーズを満たすことができる場合は、そのアーキテクチャを使用することをお勧めします。 詳しくは、「[マルチテナント ユーザー管理](https://learn.microsoft.com/ja-jp/entra/architecture/multi-tenant-user-management-introduction)」をご覧ください。

個別のテナントによって新しい境界が作成されるため、前のセクションで説明したように、Microsoft Entra ディレクトリ ロール、ディレクトリ オブジェクト、条件付きアクセス ポリシー、Azure リソース グループ、Azure 管理グループ、およびその他のコントロールの分離された管理が行われます。

個別のテナントは、組織の IT 部門が、Intune、Microsoft Entra Connect、ハイブリッド認証構成などの Microsoft サービスでテナント全体の変更を検証しながら、組織のユーザーとリソースを保護するのに役立ちます。 これには、テナント全体に影響を与える可能性があり、運用テナント内のユーザーのサブセットにスコープを設定できないテスト サービス構成が含まれます。

MS Graph または類似の API を使用して、運用ユーザー オブジェクトのデータを変更する可能性があるカスタム アプリケーション (たとえば、Directory.ReadWrite.All または同様の広いスコープが付与されているアプリケーション) の開発中に、運用環境以外の環境を個別のテナントにデプロイすることが必要になる場合があります。

注

複数のテナントへの Microsoft Entra Connect 同期。これは運用環境以外の環境を個別のテナントにデプロイするときに便利なことがあります。 詳細については、「[Microsoft Entra Connect: サポートされるトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies)」を参照してください。

### 結果

前述のようなシングル テナント アーキテクチャで実現した結果に加えて、組織は次に示すようにリソースとテナントの相互作用を完全に分離できます。

#### リソースの分離

- **可視性** - 分離したテナント内のリソースは、他のテナントのユーザーおよび管理者が検出または列挙することができません。 同様に、使用状況レポートと監査ログは新しいテナント境界内に含められます。 この可視性の分離により、組織では機密プロジェクトに必要なリソースを管理できます。
- **オブジェクト占有領域** - Microsoft Graph やその他の管理インターフェイスを介して Microsoft Entra ID や他の Microsoft Online サービスに書き込むアプリケーションは、個別のオブジェクト領域で動作できます。 これにより、開発チームはソフトウェア開発ライフサイクル中に他のテナントに影響を与えることなくテストを実行できます。
- **クォータ** - テナント全体の [Azure クォータと制限](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-subscription-service-limits)の使用量は、他のテナントの使用量とは別です。

#### 構成の分離

新しいテナントには、そのテナント レベルで異なる構成を必要とする要件を持つ、リソースと信頼する側のアプリケーションに対応できる、個別のテナント全体の設定のセットが用意されています。 さらに、新しいテナントでは、Office 365 などの新しい Microsoft Online サービスのセットが提供されます。

#### 管理上の分離

新しいテナント境界には、別個の Microsoft Entra ディレクトリ ロールのセットが含まれています。これにより、さまざまな管理者のセットを構成できます。

### 一般的な使用法

次の図は、複数のテナントでのリソース分離の一般的な使用方法を示しています。これは、シングル テナントの委任された管理で実現できるよりももっと多くの分離を必要とする、実稼働前環境または "サンドボックス" 環境です。

[Image: 一般的な使用シナリオを示す図。]

Contoso は、ContosoSandbox.com と呼ばれる実稼働前テナントを使用して企業テナント アーキテクチャを強化した組織です。 サンドボックス テナントは、Microsoft Graph を使用して Microsoft Entra ID と Microsoft 365 に書き込むエンタープライズ ソリューションの継続的な開発をサポートするために使用されます。 これらのソリューションは、企業テナントにデプロイされます。

サンドボックス テナントがオンライン化されることで、開発中のアプリケーションが、テナント リソースを消費してクォータに影響を与えたり、スロットリングを行うことで、運用システムに直接または間接的に影響を与えないようにします。

開発者は、開発ライフサイクル中にサンドボックス テナントにアクセスする必要があります。このとき、運用環境で制限されている追加のアクセス許可を必要とするセルフサービス アクセスで行うことが理想的です。 これらの追加のアクセス許可の例としては、ユーザー アカウントの作成、削除、更新のほか、アプリケーションの登録、Azure リソースのプロビジョニングとプロビジョニング解除、ポリシーまたは環境の全体的な構成の変更などがあります。

この例では、Contoso は [Microsoft Entra B2B Collaboration](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) を使用して企業テナントからのユーザーをプロビジョニングし、複数の資格情報を管理せずにサンドボックス テナント内のアプリケーションのリソースを管理およびアクセスできるユーザーを有効にします。 この機能は、主に組織間コラボレーション シナリオを対象としています。 ただし、Contoso のような複数のテナントを持つ企業では、この機能を使用して、資格情報のライフサイクル管理が増えたりユーザー エクスペリエンスが複雑になったりすることを回避できます。

[External Identities クロステナント アクセス](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)設定を使用して、B2B コラボレーションを通じて他の Microsoft Entra 組織とコラボレーションを行う方法を管理します。 これらの設定によって、お客様のリソースに対して外部 Microsoft Entra 組織の受信アクセス ユーザーが持つレベルと、お客様のユーザーが外部組織に対して持つ送信アクセスのレベルの両方が決まります。 また、他の Microsoft Entra 組織からの多要素認証 (MFA) およびデバイス クレーム ([準拠しているクレームと Microsoft Entra ハイブリッド参加済みクレーム](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)) を信頼できるようになります。 詳細と計画に関する考慮事項については、[Microsoft Entra 外部 ID でのクロステナント アクセス](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)に関するページを参照してください。

もう 1 つの方法として、Microsoft Entra Connect の機能を利用して、同じオンプレミスの Microsoft Entra 資格情報を複数のテナントに同期し、同じパスワードを維持しながらユーザー UPN ドメインで区別するという方法もあります。

### マルチテナントのリソースの分離

新しいテナントでは、別個の管理者セットがあります。 組織は [Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を通じて企業 ID を使用することを選択できます。 同様に、組織では Azure リソースのテナント間の管理のために [Azure Lighthouse](https://learn.microsoft.com/ja-jp/azure/lighthouse/overview) を実装できるため、非運用の Azure サブスクリプションを、運用の Azure サブスクリプションの ID で管理できます。 Azure Lighthouse は、Microsoft Intune などの Azure 外部のサービスの管理には使用できません。 マネージド サービス プロバイダー (MSP) 向けの [Microsoft 365 Lighthouse](https://learn.microsoft.com/ja-jp/microsoft-365/lighthouse/m365-lighthouse-overview?view=o365-worldwide&preserve-view=true) は、Microsoft 365 Business Premium、Microsoft 365 E3、またはWindows 365 Business を使っている中小規模企業 (SMB) の顧客向けに、デバイス、データ、ユーザーを大規模にセキュリティで保護して管理するための管理ポータルです。

これによりユーザーは、分離のベネフィットを享受しながら企業の資格情報を引き続き使用できます。

サンドボックス テナント内の Microsoft Entra B2B コラボレーションは、Azure B2B の[許可/拒否リスト](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)を使用して、企業環境からの ID のみをオンボードできるように構成する必要があります。 B2B に許可する必要があるテナントの場合は、クロステナント多要素認証やデバイスの信頼用に、External Identities クロステナント アクセス設定を使用することを検討してください。

重要

外部 ID アクセスが有効になっているマルチテナント アーキテクチャでは、リソースの分離のみが提供されますが、ID の分離は有効化されません。 Microsoft Entra B2B コラボレーションと Azure Lighthouse を使用したリソース分離では、ID に関連するリスクは軽減されません。

サンドボックス環境で ID が企業環境と共有される場合、サンドボックス テナントには次のシナリオが適用されます。

- 企業テナント内のユーザー、デバイス、またはハイブリッド インフラストラクチャを侵害する悪意のあるアクターが、サンドボックス テナントに招待された場合、サンドボックス テナントのアプリとリソースにアクセスできる可能性があります。
- 企業テナントの操作エラー (ユーザー アカウントの削除や資格情報の失効など) によって、サンドボックス テナントへの招待されたユーザーのアクセスに影響が出る可能性があります。

非常に防御的なアプローチを必要とするビジネス クリティカルなリソースに対し、リスク分析を行い、複数のテナントによる ID 分離を検討する必要があります。 Azure Privileged Identity Management は、ビジネス クリティカルなテナントとリソースへのアクセスに対して追加のセキュリティを強制することで、リスクをいくらか軽減するのに役立ちます。

#### ディレクトリ オブジェクト

リソースの分離に使用するテナントには、プライマリ テナントと同じ種類のオブジェクト、Azure リソース、および信頼する側のアプリケーションが含まれている場合があります。 次のオブジェクトの種類をプロビジョニングする必要がある場合があります。

**ユーザーとグループ**: 次のようなソリューション エンジニアリング チームが必要とする ID。

- サンドボックス環境管理者。
- アプリケーションの技術所有者。
- 基幹業務アプリケーション開発者。
- テストエンドユーザーアカウント。

これらの ID は、次の目的でプロビジョニングされる場合があります。

- [Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を通じて企業アカウントを持つ従業員。
- 管理、緊急管理アクセス、またはその他の技術的な理由でローカル アカウントを必要とする従業員。

非運用のオンプレミス Active Directory を持っているかそれを必要とするお客様は、基になるリソースとアプリケーションによって必要な場合、自分のオンプレミス ID をサンドボックス テナントに同期することもできます。

**デバイス**: 非運用テナントでは、ソリューション エンジニアリング サイクルで必要な程度にデバイス数が減ります。

- 管理ワークステーション
- 開発、テスト、およびドキュメントに必要な非運用コンピューターとモバイル デバイス

#### アプリケーション

Microsoft Entra 統合アプリケーションにおけるアプリケーション オブジェクトとサービス プリンシパルについて:

- 運用環境にデプロイされているアプリケーションのテスト インスタンス (たとえば、Microsoft Entra ID や Microsoft オンライン サービス に書き込むアプリケーション)。
- 運用以外のテナントを管理および維持するためのインフラストラクチャ サービス。企業テナントで使用できるソリューションのサブセットである可能性があります。

Microsoft Online Services:

- 通常、運用環境で Microsoft Online Services を所有するチームは、それらのサービスの非運用インスタンスを所有しているチームである必要があります。
- 運用環境以外のテスト環境の管理者は、これらのサービスが特にテストされている場合を除き、Microsoft Online Services をプロビジョニングしないでください。 これにより、テスト環境での運用 SharePoint サイトの設定など、Microsoft サービスの不適切な使用を回避できます。
- 同様に、エンド ユーザーによって開始できる Microsoft Online サービス (アドホック サブスクリプションとも呼ばれます) のプロビジョニングはロックダウンする必要があります。 詳細については、「[Microsoft Entra ID のセルフサービス サインアップについて](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-self-service-signup)」を参照してください。
- 一般に、グループベースのライセンスを使用して、テナントに対して重要でないライセンス機能をすべて無効にする必要があります。 これは、ライセンス機能を有効にすることの影響を知らないことがある開発者による構成ミスを回避するために、運用テナント内のライセンスを管理するのと同じチームが行う必要があります。

#### Azure リソース

信頼する側のアプリケーションによって必要なすべての Azure リソースもデプロイできます。 たとえば、データベース、仮想マシン、コンテナー、Azure Functions などです。 サンドボックス環境では、使用できるセキュリティ機能が少ない製品やサービスについて、低価格の SKU を使用するというコスト削減を検討する必要があります。

テストの終了後に変更が運用環境にレプリケートされる場合は、アクセス制御用の RBAC モデルを運用環境以外の環境で引き続き使用する必要があります。 これを行わないと、非運用環境のセキュリティ上の欠陥が運用テナントに反映されます。

### 複数のテナントを使用したリソースと ID の分離

#### 分離の結果

リソースの分離だけでは要件を満たせない、限定的な状況があります。 マルチテナント アーキテクチャでのリソースと ID の両方の分離は、すべてのクロステナント コラボレーション機能を無効にし、個別の ID 境界を実質的に構築することによって行うことができます。 この方法は、運用エラーと、企業テナント内のユーザー ID、デバイス、またはハイブリッド インフラストラクチャの侵害に対する防御です。

#### アイソレーションの一般的な使用法

個別の ID 境界は、通常、顧客向けサービスなどのビジネス クリティカルなアプリケーションやリソースに使用されます。 このシナリオでは、Fabrikam は、従業員の ID 侵害が SaaS の顧客に影響を与えるリスクを回避するために、顧客向けの SaaS 製品用に個別のテナントを作成することを決定しました。 上の図は、Fabrikam と FabrikamSaaS の境界を示しています。 FabrikamSaaS テナントには、Fabrikam のビジネス モデルの一部として顧客に提供されるアプリケーションに使用される環境が含まれています。

#### ディレクトリ オブジェクトの分離

FabrikamSaas のディレクトリ オブジェクトは、次のようになります。

ユーザーとグループ: ソリューション IT チーム、カスタマー サポート スタッフ、またはその他の必要な担当者が必要とする ID が SaaS テナント内に作成されます。 分離を維持するために、ローカル アカウントのみが使用され、Microsoft Entra B2B コラボレーションは有効になっていません。

Azure AD B2C ディレクトリ オブジェクト: テナント環境が顧客によってアクセスされる場合、Azure AD B2C テナントとそれに関連付けられている ID オブジェクトが含まれている可能性があります。 これらのディレクトリを保持するサブスクリプションは、分離されたコンシューマー向け環境に適しています。

デバイス: このテナントでは含まれるデバイスの数が少なくなり、以下のような、顧客向けソリューションを実行するために必要なもののみとなります。

- セキュリティで保護された管理ワークステーション。
- サポート担当者のワークステーション (前述のように "待機中" のエンジニアが含まれる場合があります)。

#### アプリケーションの分離

**Microsoft Entra 統合アプリケーション**: 次の用のアプリケーション オブジェクトとサービス プリンシパル:

- 運用アプリケーション (マルチテナント アプリケーション定義など)。
- 顧客向けの環境を管理および維持するためのインフラストラクチャ サービス。

**Azure リソース**: 顧客向けの運用インスタンスの IaaS、PaaS、SaaS リソースをホストします。
<!-- /MSL-PAGE -->
