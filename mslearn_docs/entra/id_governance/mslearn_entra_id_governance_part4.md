# Microsoft Learn — Microsoft Entra / ID ガバナンス (PIM・アクセスレビュー等) (part 4)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 54

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/migrate-from-sap-idm"} -->
## ID 管理シナリオの SAP IDM から Microsoft Entra への移行

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm
- Service: entra-id-governance
- Article date: 2024-08-25
- Summary: 以前は SAP IDM を使用していた組織向けに、SAP SuccessFactors やその他のソースから ID を Microsoft Entra ID に取り込み、それらの ID をプロビジョニングし、SAP ECC、SAP S/4HANA、その他の SAP および SAP 以外のアプリケーションへのアクセスを付与するための詳細な手順について説明します。

ビジネス アプリケーションに加えて、SAP は、[SAP Cloud Identity Services](https://pages.community.sap.com/topics/cloud-identity-services) や [SAP Identity Management](https://pages.community.sap.com/topics/identity-management) (SAP IDM) など、さまざまな ID およびアクセス管理テクノロジを提供し、お客様が SAP アプリケーション内で ID を維持できるようにしています。 組織がオンプレミスにデプロイする SAP IDM では、SAP R/3 デプロイ用に ID およびアクセス管理を以前から提供しています。 SAP は、[SAP Identity Management のメンテナンスを終了します](https://community.sap.com/t5/technology-blogs-by-sap/preparing-for-sap-identity-management-s-end-of-maintenance-in-2027/ba-p/13596101)。 SAP Identity Management を使用していた組織向けに、[Microsoft](https://techcommunity.microsoft.com/t5/security-compliance-and-identity/microsoft-and-sap-collaborate-to-modernize-identity-for-sap/ba-p/4056600) と [SAP](https://community.sap.com/t5/technology-blogs-by-sap/update-on-the-sap-identity-management-migration-to-microsoft-entra/ba-p/13742820) は共同で、それらの組織が ID 管理シナリオを SAP Identity Management から Microsoft Entra に移行するためのガイダンスを開発しています。

ID の最新化は、組織のセキュリティ体制を改善し、ユーザーとリソースを ID の脅威から保護するための重要かつ不可欠なステップです。 これは、ユーザー エクスペリエンスの向上や運用効率の最適化など、より強力なセキュリティ体制を超えた大きな利点を提供できる戦略的投資です。 多くの組織の管理者は、ID およびアクセス管理のシナリオの中心を完全にクラウドに移行することに関心を示しました。 オンプレミス環境がなくなった組織もあれば、クラウドでホストされる ID およびアクセス管理を、残りのオンプレミスのアプリケーション、ディレクトリ、データベースと統合する組織もあります。 ID サービスのクラウドへの変換の詳細については、「[クラウドへの変革の体制](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-posture)」と「[クラウドへの移行](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-migrate)」を参照してください。

Microsoft Entra が提供するユニバーサル クラウドホスト型 ID プラットフォームでは、ユーザー、パートナー、顧客に対し、任意のプラットフォームやデバイスからクラウドやオンプレミスのアプリケーションにアクセスして共同作業を行うための 1 つの ID が提供されます。 このドキュメントでは、ID およびアクセス管理 (IAM) シナリオを SAP Identity Management から Microsoft Entra クラウドホスト型サービスに移行するための移行オプションとアプローチに関するガイダンスを提供します。このドキュメントは、新しいシナリオが移行可能になると随時更新されます。

### Microsoft Entra とそのSAP 成果物統合の概要

Microsoft Entra は、Microsoft Entra ID (旧称 Azure Active Directory) や Microsoft Entra ID ガバナンス などの製品ファミリです。 [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/whatis) は、従業員やゲストがリソースへのアクセスに使用できる、クラウドベースの ID およびアクセス管理サービスです。 強力な認証と安全なアダプティブ アクセスを提供し、オンプレミスのレガシ アプリと数千のサービスとしてのソフトウェア (SaaS) アプリケーションの両方と統合し、シームレスなエンドユーザー エクスペリエンスを提供します。 [Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) には、ユーザーがアクセスする必要があるアプリでユーザー ID を自動的に確立し、ジョブの状態またはロールの変更に応じてユーザー ID を更新および削除するための追加機能が用意されています。

Microsoft Entra は、Microsoft Entra 管理センター、myapps ポータル、myaccess ポータルなどのユーザー インターフェイスを提供し、ID 管理操作と委任されたエンド ユーザーのセルフサービス用の REST API を提供します。 Microsoft Entra ID には、SuccessFactors、SAP ERP Central Component (SAP ECC) の統合が含まれており、SAP Cloud Identity Services を介して、S/4HANA やその他の多くの SAP アプリケーションへのプロビジョニングとシングル サインオンを提供できます。 こうした統合の詳細については、「[SAP アプリケーションへのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/sap)」を参照してください。 Microsoft Entra には、SAML、SCIM、SOAP、OpenID Connect などの標準プロトコルも実装されており、シングル サインオンやプロビジョニング用に他の多くのアプリケーションと直接統合できます。 Microsoft Entra には、[Microsoft Entra Connect クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)や、Microsoft Entra クラウド サービスを組織のオンプレミスのアプリケーション、ディレクトリ、データベースに接続するための[プロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-provisioning-agent)を含むエージェントもあります。

次の図は、ユーザー プロビジョニングとシングル サインオンのトポロジの例を示したものです。 このトポロジでは、作業者は SuccessFactors で表され、Windows Server Active Directory ドメイン、Microsoft Entra、SAP ECC、SAP クラウド アプリケーションにアカウントがある必要があります。 この例では、Windows Server AD ドメインを持つ組織について説明します。ただし、Windows Server AD は必須ではありません。

[Image: 関連する Microsoft および SAP テクノロジとその統合のエンドツーエンドの範囲を示す図。]

### ID 管理シナリオの Microsoft Entra への移行の計画

SAP IDM の導入以来、ID およびアクセス管理のランドスケープは新しいアプリケーションと新しいビジネスの優先順位で進化しているため、IAM のユース ケースに対処するために推奨されるアプローチは、多くの場合、SAP IDM 用に以前に実装された組織とは異なるものになります。 そのため、組織はシナリオ移行の段階的なアプローチを計画する必要があります。 たとえば、ある組織では、エンドユーザーのセルフサービス パスワード リセット シナリオを 1 つの手順として移行し、それが完了したらプロビジョニング シナリオを移動するように優先順位を付ける場合があります。 別の例として、組織は最初に、既存の ID 管理システムや運用テナントと並行して動作する別のステージング テナントに Microsoft Entra 機能を展開し、ステージング テナントから運用テナントに 1 つずつシナリオの構成を導入し、そのシナリオを既存の ID 管理システムから使用停止にすることを選択できます。 組織がシナリオの移動を選択する順序は、IT の全体的な優先順位と、トレーニング更新プログラムを必要とするエンド ユーザーやアプリケーション所有者など、他の利害関係者への影響によって異なります。 組織は、ID 管理の外部にある他のシナリオをオンプレミスの SAP テクノロジから SuccessFactors、S/4HANA、またはその他のクラウド サービスに移行するなど、他の IT 最新化と共に IAM シナリオの移行を構成することもできます。 また、この機会を利用して、古い統合、アクセス権またはロールのクリーンアップや削除、また必要に応じて統合を行うこともできます。

移行の計画を開始する方法:

- IAM 最新化戦略で、現在の IAM ユース ケースと計画されている IAM ユース ケースを特定します。
- これらのユース ケースの要件を分析し、それらの要件を Microsoft Entra サービスの機能と一致させます。
- 移行をサポートするための新しい Microsoft Entra 機能を実装するための期間と利害関係者を決定します。
- シングル サインオン、ID ライフサイクル、アクセス ライフサイクル制御を Microsoft Entra に移行するためのアプリケーションのカットオーバー プロセスを決定します。

SAP IDM の移行には、既存の SAP アプリケーションとの統合が含まれる可能性が高くなります。アプリケーション オンボードのシーケンスを決定する方法と、アプリケーションを Microsoft Entra と統合する方法については、[SAP ソースとターゲットとの統合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#plan-the-integrations-with-sap-sources-and-targets)に関するガイダンスを確認してください。

### SAP アプリケーションを使用して Microsoft Entra をデプロイするためのサービスと統合パートナー

パートナーは、SAP S/4HANA などの SAP アプリケーションを使用した Microsoft Entra の計画とデプロイを提供し、組織を支援することもできます。 お客様は、Microsoft Solution Partner ファインダーに記載されているパートナーと協働することも、次の表に示すこれらのサービスおよび統合パートナーから選択することもできます。 説明とリンクされているページは、パートナー自身によって提供されています。 説明を使用して、SAP アプリケーションを使った Microsoft Entra のデプロイを行うために連絡するパートナーを特定し、詳細情報を確認できます。

| 名前 | 説明 |
| --- | --- |
| [アクセンチュアとアバナード](https://www.avanade.com/en/blogs/techs-and-specs/security/sap-to-entra-migration) | "アクセンチュアとアバナードは、Microsoft Security と SAP Services のグローバル リーダーです。 Microsoft と SAP の独自のスキルを組み合わせることで、SAP IdM から Microsoft Entra ID への移行を理解し、適切に計画するのに役立ちます。 アバナードとアクセンチュアは、デリバリー資産と専門知識を活用して、移行プロジェクトを加速し、リスクを押さえながら目標を達成するお手伝いをします。 アクセンチュアは、大規模なグローバル企業を支援する独自の立場をもち、移行の過程で中堅企業と大企業の両方をサポートしてきたアバナードの深い経験がこれを補完しています。 私たちの豊富な知識と経験は、Microsoft Entra ID への投資を最大化しながら、SAP IdM を置き換えるお客様の取り組みを加速するのに役立ちます。" |
| [ARMIS](https://www.armisgroup.com/services/it-services/security-identity/identity) | 「20 年以上のデジタル変革の経験を持つ ARMIS は、グローバルなテクノロジ ソリューションを提供し、信頼できる Microsoft パートナーです。 Microsoft は、セキュリティ、データ&AI、ビジネス アプリケーションを専門とし、セキュリティと ID ガバナンスを強化するために Microsoft Entra スイート を使用して、効率性と持続可能な成長を強化する革新的なソリューションを提供します。" |
| [カンパーナ & ショット](https://www.campana-schott.com/de/en/expertise/strategie/cyber-security-services/identity-access-management) | Campana & Schott は、ヨーロッパと米国に 600 人以上の従業員を持つ国際的な管理およびテクノロジ コンサルタントです。私たちは、30 年以上にわたるプロジェクト管理や変革の経験と、Microsoft のテクノロジ、IT 戦略、IT 組織に関する深い専門知識を組み合わせています。 Microsoft Cloud の Microsoft ソリューション パートナーとして、Microsoft 365、SaaS アプリケーション、および従来のオンプレミス アプリケーションに最新のセキュリティを提供しています。" |
| [DXCテクノロジー](https://dxc.com/us/en/offerings/security/digital-identity#microsoft-entra) | "DXCテクノロジーは、グローバル企業によるミッションクリティカルなシステムと運用の実行、IT の最新化、データ アーキテクチャの最適化、パブリック、プライベート、ハイブリッド クラウド全体のセキュリティとスケーラビリティの確保に役立ちます。 30 年以上にわたる Microsoft および SAP とのグローバルな戦略的パートナーシップにより、DXC は業界をまたいで SAP IDM の実装に関する豊富な経験を持っています。" |
| [Edgile (Wipro 企業)](https://edgile.com/information-security/microsoft-entra-id-governance/) | "Wipro 企業である Edgile は、 25 年にわたって蓄積されてきた専門知識を SAP と Microsoft 365 の両方で活用し、大切なお客様のために ID およびアクセス管理 (IDM) をシームレスに移行します。 Microsoft Entra Launch Partner および SAP Global Strategic Services Partner (GSSP) として、IAM の最新化と変更管理の分野で際立っています。 Edgile と Wipro の専門家のチームは、独自のアプローチを採用し、お客様のクラウド ID とセキュリティの変革を成功させます。" |
| [IBSolution](https://www.ibsolution.com/en/cyber-security/identity-and-access-management/sap-identity-management/sap-idm-end-of-maintenance-2027) | "IBSolution は、20 年以上の実績を持つ、世界中で最も経験豊富な SAP ID 管理コンサルティング会社の 1 つです。 戦略的アプローチと事前パッケージ化されたソリューションで、SAP IdM から SAP の最も推奨される IdM の後継者である Microsoft Entra ID ガバナンス へ移行するお客様をサポートしています。" |
| [IBM コンサルティング](https://www.ibm.com/services/identity-access-management) | "IBM Consulting は、Microsoft Security および SAP トランスフォーメーション サービスにおけるグローバル リーダーです。 私たちは、お客様の SAP IdM の Microsoft Entra ID への移行のサポートを行っています。 大規模なグローバル企業のお客様のサポートにおける私たちの知識と経験は、サイバーセキュリティ、AI、SAP サービスに関する深い専門知識と組み合わされることで、重要なビジネス サービスと機能の中断を最小限に抑えながらシームレスな移行作業を行う上での助けとなります。" |
| [KPMG](https://aka.ms/KPMGEntraIDGov) | 「KPMG と Microsoft は、包括的な ID ガバナンスの提案を提供することで、アライアンスをさらに強化します。 Microsoft Entra の高度なツールと KPMG Powered Enterprise の組み合わせにより、ID ガバナンスの複雑さに巧みに対処することで、機能的な変革を推進することが可能となります。 この相乗効果により、デジタル機能を加速させ、運用効率を向上させ、セキュリティを強化し、コンプライアンスを確保することができます。」 |
| [ObjektKultur](https://objektkultur.de/technologie/microsoft-entra/) | "Objektkultur は、長年の Microsoft パートナーとして、革新的な IT ソリューションのコンサルティング、開発、運用を通じてビジネス プロセスをデジタル化します。 Microsoft Technologies 内での ID およびアクセス管理ソリューションの実装とアプリケーションの統合に関する深い経験を持つ私たちは、段階的に廃止される SAP IDM から堅牢な Microsoft Entra へシームレスにお客様を導き、クラウド上での安全で管理され、合理化された ID およびアクセス管理を保証します。" |
| [Patecco](https://www.patecco.com) | "PATECCOは、最新の技術に基づくID およびアクセス管理ソリューションの開発、サポート、実装を専門とするドイツの IT コンサルティング会社です。 IAM に関する高度な専門知識と業界リーダーとのパートナーシップにより、銀行、保険、製薬、エネルギー、化学、ユーティリティなど、さまざまなセクターのお客様に合わせたサービスを提供しています。" |
| [Protiviti](https://www.protiviti.com/us-en/microsoft-consulting-solutions) | "Microsoft AI クラウド ソリューション パートナーおよび SAP Gold パートナーとして、Protiviti の Microsoft ソリューションと SAP ソリューションに関する専門知識は、業界でも類を見ないものです。 強力なパートナーシップを組み合わせることで、一元化された ID 管理とロール ガバナンスのための包括的なソリューションのセットをお客様に提供できます。" |
| [PwC](https://www.pwc.com/us/en/technology/alliances/microsoft/cybersecurity.html) | "PwC の ID およびアクセス管理サービスは、SAP IDM にデプロイされている既存の機能を評価し、Microsoft Entra ID ガバナンスを使用して目標とする将来の状態やビジョンの戦略化や計画を支援することで、すべての機能フェーズで役立ちます。 PwC は、稼働後に管理サービスとして環境をサポートおよび運用することもできます。" |
| [SAP](https://www.sap.com/services-support.html) | "SAP IDM の設計と実装における豊富な経験を持つ私たちの SAP コンサルティング チームは、SAP IDM から Microsoft Entra への移行における包括的な専門知識を提供します。 SAP 開発への強力で直接的なつながりにより、私たちはお客様の要件を実現し、革新的なアイデアを実装することができます。 Microsoft Entra との統合における私たちの経験は、私たちがカスタマイズされたソリューションを提供することを可能にします。 さらに、私たちは SAP Cloud Identity Services の取り扱いと、SAP の SAP 以外のクラウド製品との統合に関するベスト プラクティスを熟知しています。 是非、私たちの専門知識を信頼し、私たちの SAP コンサルティング チームにお客様の Microsoft Entra ID への移行のサポートをお任せください。" |
| [SITS](https://sits.com/en/microsoft/ms-iam-modernization) | "DACH リージョンをリードするサイバーセキュリティ プロバイダーである SITS は、最上位レベルのコンサルティング、エンジニアリング、クラウド テクノロジ、マネージド サービスでお客様をサポートしています。 セキュリティ、モダン ワーク、Azure インフラストラクチャの Microsoft ソリューション パートナーとして、セキュリティ、コンプライアンス、ID、プライバシーに関するコンサルティング、実装、サポート、マネージド サービスをお客様に提供しています。" |
| [Traxion](https://www.traxion.com/ms-iam-modernization) | "SITS グループに所属する Traxion は、BeNeLux リージョンでビジネスを展開しています。 Traxionは、160 人の従業員と 24 年以上にわたって蓄積された専門知識を持つ IT セキュリティ企業で、ID 管理ソリューションを専門としています。 Microsoft パートナーとして、最上位レベルの IAM アドバイザリ、実装、サポート サービスを提供し、強力で長期的なクライアント関係を確保しています。" |
| [TrustSis](https://trustsis.com/en/contact/) | "TrustSis は、ブラジルに拠点を置く Microsoft および SAP Gold パートナーであり、Microsoft Entra と SAP ソリューションの統合に関する比類のない専門知識を提供します。 アクセス管理、セキュリティ、内部制御、リスク軽減、職務の分離 (SoD) に特化した TrustSis は、すべての移行がセキュリティで保護され、効率的であることを保証します。 SAP IDM の豊富な経験により、TrustSis は、セキュリティ対策を強化しながら、オペレーショナル エクセレンスを維持する戦略的移行を保証します。 |

### 各 SAP IDM シナリオの移行ガイダンス

以下の各セクションでは、各 SAP IDM シナリオを Microsoft Entra に移行する方法についてのガイダンスへのリンクを示します。 組織がデプロイした SAP IDM シナリオによっては、すべてのセクションが各組織に関連しているわけではありません。

両製品の機能が進化を続けるにつれて、Microsoft Entra と SAP Access Control (SAP AC) および SAP Cloud Identity Access Governance (SAP IAG) の統合など、より多くの移行と新しいシナリオが可能になるため、更新については、この記事、Microsoft Entra 製品ドキュメント、および対応する SAP 製品ドキュメントを必ず定期的に確認してください。

| SAP IDM のシナリオ | Microsoft Entra のガイダンス |
| --- | --- |
| SAP IDM ID ストア | Microsoft Entra ID テナントへの ID ストアの移行、既存の IAM データの Microsoft Entra ID テナントへの移行 |
| SAP IDM でのユーザー管理 | ユーザー管理のシナリオの移行 |
| SAP SuccessFactors とその他の HR ソース | SAP HR ソースとの統合 |
| アプリケーションでのシングル サインオン用の ID のプロビジョニング | SAP システムへのプロビジョニング、SAP 以外のシステムへのプロビジョニングおよび認証とシングル サインオンの移行 |
| エンド ユーザーのセルフサービス | エンド ユーザー セルフサービスの移行 |
| ロールの割り当てとアクセスの割り当て | アクセスのライフサイクル管理シナリオを移行する |
| レポート | レポート用の Microsoft Entra の使用 |
| ID フェデレーション | 組織の境界を越えた ID 情報の交換の移行 |
| ディレクトリ サーバー | ディレクトリ サービスが必要なアプリケーションを移行する |
| 拡張機能 | 統合インターフェイスを介した Microsoft Entra の拡張 |

#### Microsoft Entra ID テナントへの ID ストアの移行

SAP IDM では、ID ストアは、ユーザー、グループ、監査証跡などの ID 情報のリポジトリです。 Microsoft Entra では、"テナント" は Microsoft Entra ID のインスタンスです。ここには、ユーザー、グループ、デバイスなどの組織オブジェクトを含む、1 つの組織に関する情報があります。 テナントには、ディレクトリに登録されているアプリケーションなどのリソースのアクセス ポリシーとコンプライアンス ポリシーも含まれています。 テナントによって提供される主な機能には、ID 認証とリソース アクセス管理が含まれます。 テナントには特権的な組織データが含まれ、他のテナントから安全に分離されています。 さらに、テナントは、特定のリージョンまたはクラウドで組織のデータが永続化され、処理されるように構成できます。これにより、組織では、データ所在地と処理に関するコンプライアンス要件を満たすためのメカニズムとしてテナントを使用できます。

Microsoft 365、Microsoft Azure、またはその他の Microsoft オンライン サービスを持つ組織には、これらのサービスの基になる Microsoft Entra ID テナントが既に存在します。 さらに、組織には、特定のアプリケーションを持ち、それらのアプリケーションに適用対象の [PCI-DSS](https://learn.microsoft.com/ja-jp/entra/standards) などの[標準を満たすように構成された](https://learn.microsoft.com/ja-jp/entra/standards/pci-dss-guidance)テナントなど、追加のテナントを持つ場合があります。 既存のテナントが適切かどうかを判断する方法の詳細については、「[マルチ テナントでのリソースの分離](https://learn.microsoft.com/ja-jp/entra/architecture/secure-multiple-tenants)」を参照してください。 組織にまだテナントがない場合は、「[Microsoft Entra 展開プラン](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)」を確認します。

シナリオを Microsoft Entra テナントに移行する前に、次のステップ バイ ステップ ガイダンスを確認する必要があります。

- [アプリケーションへのアクセスに関するユーザーの前提条件とその他の制約について組織のポリシーを定義する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#define-the-organizations-policy-with-user-prerequisites-and-other-constraints-for-access-to-an-application)
- [プロビジョニングと認証のトポロジを決定する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#decide-on-the-provisioning-and-authentication-topology)。 SAP IDM の「[ID ライフサイクル管理](https://help.sap.com/docs/SAP_IDENTITY_MANAGEMENT/4773a9ae1296411a9d5c24873a8d418c/687dff86043f49e2982ba7942808e0f6.html)」と同様に、1 つの Microsoft Entra ID テナントは、プロビジョニングとシングル サインオンのために複数のクラウドやオンプレミス アプリケーションに接続できます。
- 使用する機能に対して適切な [Microsoft Entra ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals#features-by-license)がそのテナントに存在するかなど、そのテナントで組織の前提条件が満たされていることを確認します。

#### Microsoft Entra ID テナントへの既存の IAM データの移行

SAP IDM では、ID ストアは、`MX_PERSON`、`MX_ROLE`、`MX_PRIVILEGE` などのエントリの種類で ID データを表します。

- Microsoft Entra ID テナントでは、人は [User](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users) として表されます。 Microsoft Entra ID にまだ含まれていない既存のユーザーがいる場合は、Microsoft Entra ID にユーザーを取り込むことができます。 まず、既存のユーザーに、Microsoft Entra ID スキーマに含まれていない属性がある場合は、追加の属性の[拡張属性を使用して Microsoft Entra ユーザー スキーマを拡張](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#update-the-microsoft-entra-id-user-schema)します。 次に、アップロードを実行して、CSV ファイルなどのソースから [Microsoft Entra ID でユーザーを一括作成](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add)します。 次に、[それらの新しいユーザーに資格情報を発行](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#distribute-credentials-to-newly-created-microsoft-entra-users-or-windows-server-ad-users)して、Microsoft Entra ID に対して認証できるようにします。
- ビジネス ロールは、Microsoft Entra ID ガバナンス では、[エンタイトルメント管理アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)として表せます。 [組織のロール モデルを Microsoft Entra ID ガバナンス に移行してアプリケーションへのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles)ことで、各ビジネス ロールに対してアクセス パッケージを作ることができます。 移行プロセスを自動化するには、[PowerShell を使用してアクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app)できます。
- ターゲット システムの特権または技術ロールは、承認に Microsoft Entra ID データを使用する方法に関するターゲット システム必要条件に応じて、Microsoft Entra でアプリ ロールまたはセキュリティ グループとして表せます。 詳細は、「[アプリケーションを Microsoft Entra ID と統合し、レビューされたアクセスのベースラインを確立する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)」を参照してください。
- 動的グループには、Microsoft Entra 内で別個の表現形式が複数あります。 Microsoft Entra では、[Microsoft Entra ID 動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)、または[アクセス パッケージの自動割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)が設定された Microsoft Entra ID ガバナンス エンタイトルメント管理のどちらかを使用して、コスト センター属性の特定の値を持つすべてのユーザーなど、ユーザーのコレクションを自動的に管理できます。 PowerShell コマンドレットを使用すると、動的メンバーシップ グループの作成または[自動割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#add-a-policy-to-the-access-packages-for-auto-assignment)の一括作成ができます。

#### ユーザー管理のシナリオの移行

管理者は、Microsoft Entra 管理センター、Microsoft Graph API、PowerShell を使用して、ユーザーの作成、ユーザーのサインインのブロック、グループへのユーザーの追加、ユーザーの削除など、日常的な ID 管理アクティビティを簡単に実行できます。

大規模な運用を有効にするために、組織は Microsoft Entra を使用して ID ライフサイクル プロセスを自動化できます。

[Image: 他のソースとターゲットとのプロビジョニングにおける Microsoft Entra の関係図。]

Microsoft Entra ID と Microsoft Entra ID Govenance では、次の機能を使用して ID ライフサイクル プロセスを自動化できます。

- [組織の人事ソースからの受信プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)は、Workday と SuccessFactors から従業員情報を取得して、Active Directory と Microsoft Entra ID の両方でユーザー ID を自動的に保持します。
- [ディレクトリ間のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-inter-directory-provisioning)を使用することで、Active Directory に既に存在するユーザーを Microsoft Entra ID に自動的に作成して保持できます。
- Microsoft Entra ID ガバナンス の[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)は、組織の中での状態の変更や離職に際して、新しい従業員が組織で働き始める前など、特定の重要なイベントで実行されるワークフロー タスクを自動化できます。 たとえば、新しいユーザーのマネージャーに一時アクセス パスを記載した Email を送信したり、そのユーザーの初日にウェルカム メールを送信するようなワークフローを構成することができます。
- [エンタイトルメント管理の自動割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)では、ユーザーの属性変更に基づいて、ユーザーの動的メンバーシップ グループ、アプリケーション ロール、SharePoint サイトのロールを追加および削除できます。 また、[アクセス ライフサイクル管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios)のセクションに示されているように、[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)と Privileged Identity Management を使用すれば、リクエストに応じて、グループやチーム、Microsoft Entra ロール、Azure リソース ロール、SharePoint Online サイトにユーザーを割り当てることができます。
- ユーザーが Microsoft Entra ID で適切に動的メンバーシップ グループとアプリのロールが割り当てられると、[ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/id-governance/what-is-provisioning)により、SCIM、LDAP、SQL を介して数百のクラウドおよびオンプレミス アプリケーションに接続するコネクタを使用して、他のアプリケーションのユーザー アカウントを作成、更新、削除できます。
- ゲスト ライフサイクルでは、ユーザーが組織のリソースへのアクセスを要求できる他の組織を[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)で指定できます。 それらのユーザーのいずれかの要求が承認されると、エンタイトルメント管理によって、そのユーザーは組織のディレクトリに [B2B](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) ゲストとして自動的に追加され、適切なアクセス権が割り当てられます。 またエンタイトルメント管理では、アクセス権の有効期限が切れたり取り消されたりすると、B2B ゲスト ユーザーが組織のディレクトリから自動的に削除されます。
- [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)では、組織のディレクトリに既に存在する既存のゲストの定期的なレビューが自動化され、そのユーザーにアクセスが不要になると、そのユーザーが組織のディレクトリから削除されます。

別の IAM 製品から移行する場合、Microsoft Entra での IAM 概念の実装は、他の IAM 製品でのこれらの概念の実装とは異なる場合があることに注意してください。 たとえば、組織は、新しい従業員のオンボードなどのライフサイクル イベントのビジネス プロセスを表現し、一部の IAM 製品ではワークフロー基盤内のワークフローを通じてそのプロセスを実装していました。 これに対し、Microsoft Entra には多くの自動化手順が組み込まれているため、ほとんどの ID 管理自動化アクティビティにワークフローを定義する必要はありません。 たとえば、SuccessFactors などのレコードの人事システムで新しいワーカーが検出されると、Windows Server AD と Microsoft Entra ID でその新しいワーカー のユーザー アカウントを自動的に作成し、アプリケーションにプロビジョニングし、グループに追加して適切なライセンスを割り当てるよう Microsoft Entra を構成できます。 同様に、アクセス要求の承認処理では、ワークフローを定義する必要はありません。 Microsoft Entra では、ワークフローは次の状況でのみ必要です。

- ワークフローは、ワーカーの参加/移動/脱退プロセスで使用でき、Microsoft Entra ライフサイクル ワークフローに[組み込みのタスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks)で使用できます。 たとえば、管理者は、新しいワーカーの一時アクセス パスを使用してメールを送信するタスクを含むワークフローを定義できます。 管理者はまた、参加、移動、および脱退のワークフロー中に、[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)から Azure Logic Apps にコールアウトを追加することもできます。
- ワークフローは、アクセス要求とアクセス割り当てプロセスのステップの追加にも使用できます。 Microsoft Entra のエンタイトルメント管理では、複数段階の承認、職務の分離の確認、有効期限の手順が既に実装されています。 管理者は、アクセス パッケージの割り当て要求の処理、割り当ての付与、Azure Logic Apps ワークフローへの割り当ての削除中に、[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration) からのコールアウトを定義できます。

#### SAP HR ソースとの統合

SAP SuccessFactors を持つ組織は、SAP IDM を使用して SAP SuccessFactors から [従業員データを取り込む](https://help.sap.com/docs/SAP_IDENTITY_MANAGEMENT/4773a9ae1296411a9d5c24873a8d418c/4c54e007ab414f7da3854952cad00221.html) 可能性があります。 SAP SuccessFactors を使用している組織は、Microsoft Entra ID コネクタを使用して、[SuccessFactors から Microsoft Entra ID に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)、または [SuccessFactors からオンプレミスの Active Directory に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) 従業員の ID を簡単に移行して取り込めます。 このコネクタは次のシナリオをサポートします。

- **新しい従業員の雇用**: 新しい従業員が SuccessFactors に追加されると、ユーザー アカウントは Microsoft Entra ID (および必要に応じて Microsoft 365 および [Microsoft Entra ID がサポートするその他サービスとしてのソフトウェア (SaaS) アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)) で自動的に作成されます。
- **従業員の属性とプロファイルの更新**: SuccessFactors で従業員レコード (名前、役職、マネージャーなど) が更新されると、Microsoft Entra ID (および必要に応じて Microsoft 365 や Microsoft Entra ID でサポートされているその他 SaaS アプリケーション) で、その従業員のユーザー アカウントが自動的に更新されます。
- **従業員の退職**: SuccessFactors で従業員が退職状態になると、Microsoft Entra ID (および必要に応じて Microsoft 365 や Microsoft Entra ID によってサポートされているその他 SaaS アプリケーション) で、その従業員のユーザー アカウントが自動的に無効になります。
- **従業員の再雇用**: SuccessFactors で従業員が再雇用されると、Microsoft Entra ID および必要に応じて Microsoft 365 や Microsoft Entra ID によってサポートされているその他 SaaS アプリケーションに対して、その従業員の以前のアカウントを (設定に応じて) 自動的に再アクティブ化または再プロビジョニングできます。

[Microsoft Entra ID から SAP SuccessFactors プロパティに書き戻す](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)こともできます (メール アドレスなど)。

[Image: 作業者に関するデータの Microsoft Entra ID への取り込みに関連する Microsoft および SAP のテクノロジを示す図。]

Windows Server AD または Microsoft Entra ID で適切な資格情報を使用して新しいユーザーを設定するなど、ソースとしての SAP SuccessFactors を使用した ID ライフサイクルに関する詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

また、一部の組織では SAP IDM を使用して、[SAP Human Capital Management (HCM)](https://help.sap.com/docs/SAP_IDENTITY_MANAGEMENT/d376345fb4e94928a70036ddf91d690b/490d22699aff42519eb6b328c7f44e24.html) から読み取りを行いました。 SAP SuccessFactors と SAP Human Capital Management (HCM) の両方を使用している組織も、Microsoft Entra ID に ID を取り込むことができます。 SAP Integration Suite を使用すると、SAP HCM と SAP SuccessFactors の間で雇用者のリストを同期できます。 そこから、前述のネイティブ プロビジョニング統合を使用して、ID を Microsoft Entra ID に直接取り込んだり、Active Directory Domain Services にプロビジョニングしたりできます。

[Image: SAP HR 統合の図。]

SAP HCM を持ち、SuccessFactors がない組織のその他の統合オプションについては、「 [SAP 人事管理 (HCM) から Microsoft Entra ID へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hcm-microsoft-entra-identity-provisioning)」に記載されています。

SuccessFactors または SAP HCM 以外のレコード ソースの他のシステムがある場合は、Microsoft Entra の[インバウンドのプロビジョニング API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) を使用して、そのレコード システムからワーカーを Windows Server または Microsoft Entra ID のユーザーとして取り込めます。

[Image: API ワークフローのシナリオを示す図。]

#### SAP システムへのプロビジョニング

SAP IDM を使用するほとんどの組織は、SAP ECC、SAP IAS、SAP S/4HANA、またはその他の SAP アプリケーションにユーザーをプロビジョニングするために SAP IDM を使用します。 Microsoft Entra には、SAP ECC、SAP Cloud Identity Services、SAP SuccessFactors へのコネクタがあります。 SAP S/4HANA またはその他のアプリケーションへのプロビジョニングでは、ユーザーはまず Microsoft Entra ID に存在する必要があります。 Microsoft Entra ID にユーザーを作成したら、Microsoft Entra ID から SAP Cloud Identity Services または SAP ECC にそれらのユーザーをプロビジョニングして、ユーザーが SAP アプリケーションにサインインできるようにします。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から送信されたユーザーを、SAP クラウド コネクタ、AS ABAP などを介して [`SAP S/4HANA Cloud`](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-sap-s-4hana-cloud) や [`SAP S/4HANA On-premise`](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-sap-s-4hana-on-premise) を含むダウンストリーム SAP アプリケーションにプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

SAP アプリケーションをターゲットとする ID ライフサイクルの詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー ID プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

- SAP Cloud Identity Services と統合された SAP アプリケーションへのユーザーのプロビジョニングを準備するには、SAP Cloud Identity Services にそれらのアプリケーションに必要なスキーマ マッピングがあることを確認し、[Microsoft Entra ID から SAP Cloud Identity Services にユーザーをプロビジョニングします](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-cloud-identity-services)。 SAP Cloud Identity Services はその後、必要に応じてダウンストリーム SAP アプリケーションにユーザーをプロビジョニングします。 その後、SuccessFactors からの HR 受信を使用して、従業員の参加、移動、脱退時に Microsoft Entra ID のユーザーの一覧を最新の状態に保つことができます。 テナントが Microsoft Entra ID ガバナンス のライセンスを持つ場合は、Microsoft Entra ID で SAP Cloud Identity Services に対する[アプリケーション ロールの割り当ての変更を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#assign-users-the-necessary-application-access-rights-in-microsoft-entra)できます。 プロビジョニング前に職務の分離やその他のコンプライアンス チェックを実行する方法の詳細については、「アクセス ライフサイクル管理シナリオの移行」を参照してください。
- SAP ECC にユーザーをプロビジョニングする方法のガイダンスについては、[SAP ECC に必要なビジネス API (BAPI) を Microsoft Entra による ID 管理に使用する準備ができている](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#confirm-that-necessary-bapis-for-sap-ecc-are-ready-for-use-by-microsoft-entra)ことを確認してから、Microsoft Entra ID から [SAP ECC にユーザーをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-ecc)します。
- SAP SuccessFactor ワーカー レコードの更新方法に関するガイダンスについては、「[Microsoft Entra ID から SAP SuccessFactors に書き戻す](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)」を参照してください。
- データ ソースとして Windows Server Active Directory とともに SAP NetWeaver AS for Java を使用している場合は、Microsoft Entra の SuccessFactors 受信を使用して、[Windows Server AD 内のユーザーを自動的に作成および更新できます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)。
- SAP NetWeaver AS for Java を使用しており、別の LDAP ディレクトリをデータ ソースとしている場合は、[ユーザーを LDAP ディレクトリにプロビジョニングするように、Microsoft Entra ID を構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)できます。

SAP アプリケーションのユーザーのプロビジョニングを設定すると、それらのアプリケーションとのシングル サインオンを有効にする必要があります。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーおよび認証機関として機能することができます。 Microsoft Entra ID は、[SAML または OAuth を使用して SAP NetWeaver](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-netweaver-tutorial) と統合できます。 SAP SaaS や最新のアプリにシングル サインオンを構成する方法の詳細は、「[SSO の有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/sap#enable-sso)」を参照してください。 また、アクセス ライフサイクル管理シナリオを SAP IDM から Microsoft Entra に移行することもできます。

#### SAP 以外のシステムへのプロビジョニング

組織では、SAP IDM を使用して、Windows Server AD やその他のデータベース、ディレクトリ、アプリケーションなど、[SAP 以外のシステム](https://help.sap.com/docs/SAP_IDENTITY_MANAGEMENT/d376345fb4e94928a70036ddf91d690b/2f8af286449c4453a8514ba598938581.html)にユーザーをプロビジョニングすることもできます。 これらのシナリオを Microsoft Entra ID に移行して、Microsoft Entra ID でそれらのユーザーのコピーを各リポジトリにプロビジョニングすることができます。

- Windows Server AD を使用している組織では、組織は SAP IDM を SAP R/3 に導入するために[ユーザーとグループのソース](https://help.sap.com/docs/SAP_IDENTITY_MANAGEMENT/4773a9ae1296411a9d5c24873a8d418c/a42a2432a1a143ceb1d5f3809bf1e82f.html)として Windows Server AD を使用している場合があります。 [Microsoft Entra Connect Sync または Microsoft Entra Cloud Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios#supported-sync-scenarios) を使用して、Windows Server AD のユーザーとグループを Microsoft Entra ID に取り込むことができます。 さらに、Microsoft Entra SuccessFactors 受信を使用すれば、[Windows Server AD でユーザーを自動的に作成および更新したり](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)、AD ベースのアプリケーションで使用される[AD のグループのメンバーシップを管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-writeback)したりできます。 Exchange Online メールボックスは、[グループベースのライセンス](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)を使用して、ライセンスの割り当てからユーザーに対して自動的に作成できます。
- 他のディレクトリに依存するアプリケーションを使用する組織の場合は、OpenLDAP、Microsoft Active Directory Lightweight Directory Services、389 Directory Server、Apache Directory Server、IBM Tivoli DS、Isode Directory、NetIQ eDirectory、Novell eDirectory、Open DJ、Open DS、Oracle (以前の Sun ONE) Directory Server Enterprise Edition、RadiantOne Virtual Directory Server (VDS) など、[LDAP ディレクトリにユーザーをプロビジョニングするように Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure) を構成できます。 Microsoft Entra ID のユーザーの属性をそれらのディレクトリ内のユーザーの属性にマップし、必要に応じて初期パスワードを設定できます。
- SQL データベースに依存するアプリケーションを持つ組織の場合は、データベースの ODBC ドライバーを介して [SQL データベース にユーザーをプロビジョニングするように Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure)を構成できます。 サポートされるデータベースには、Microsoft SQL Server、Azure SQL、IBM DB2 9.x、IBM DB2 10.x、IBM DB2 11.5、Oracle 10g および 11g、Oracle 12c および 18c、MySQL 5.x、MySQL 8.x、Postgres が含まれます。 Microsoft Entra ID のユーザーの属性を、それらのデータベースのテーブル列またはストアド プロシージャ パラメーターにマップできます。 SAP HANA については、SAP Cloud Identity Services の「[SAP HANA Dデータベース コネクタ (ベータ)](https://help.sap.com/docs/identity-provisioning/identity-provisioning/sap-hana-database-beta)」を参照してください。
- SAP IDM によってプロビジョニングされ、Microsoft Entra ID にはまだ存在せず、SAP SuccessFactors やその他の HR ソースのワーカーと関連付けることができない既存のユーザーが AD 以外のディレクトリまたはデータベースに存在する場合、それらのユーザーを Microsoft Entra ID に取り込む方法に関するガイダンスについては、「[アプリケーションの既存のユーザーを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)」を参照してください。
- Microsoft Entra には、何百もの SaaS アプリケーションとの組み込みのプロビジョニング統合があります。プロビジョニングをサポートするアプリケーションの完全な一覧については、「[Microsoft Entra ID ガバナンス統合](https://learn.microsoft.com/ja-jp/entra/id-governance/apps)」を参照してください。 また、Microsoft パートナーは、追加の特殊なアプリケーションとの[パートナー主導の統合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/partner-driven-integrations)も提供します。
- 他の社内開発アプリケーションの場合、Microsoft Entra は、[SCIM](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)を介してクラウド アプリケーションにプロビジョニングできます。また、[SCIM](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)、[SOAP または REST](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector)、[PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-powershell-connector)、または [ECMA API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector)を実装するパートナー提供コネクタを介して、オンプレミス アプリケーションにプロビジョニングできます。 以前に SAP IDM からのプロビジョニングに SPML を使用していた場合は、新しい SCIM プロトコルをサポートするようにアプリケーションを更新することをお勧めします。
- プロビジョニング インターフェイスのないアプリケーションの場合は、Microsoft Entra ID ガバナンス 機能を使用して、[ServiceNow チケットの作成を自動化](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-ticketed-provisioning)して、ユーザーが割り当てられたり、アクセス パッケージにアクセスできなくなったりしたときに、アプリケーション所有者にチケットを割り当てることを検討してください。

#### 認証とシングル サインオンの移行

Microsoft Entra ID はセキュリティ トークン サービスとして機能し、ユーザーは多要素認証とパスワードレス認証を使用して [Microsoft Entra ID に対して認証を行い](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)、すべてのアプリケーションにシングル サインオンできます。 Microsoft Entra ID のシングル サインオンでは、[SAML](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)、[OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)、[Kerberos](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso) などの標準プロトコルが使用されます。 SAP BTP 上の SAP クラウド アプリケーションまたはアプリケーションへのシングル サインオンの詳細については、 [Microsoft Entra と SAP Cloud Identity Services のシングル サインオンの統合を](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)参照してください。

ユーザーに対して Windows Server AD などの既存の ID プロバイダーを持つ組織は、Microsoft Entra ID が既存の ID プロバイダーに依存するようにハイブリッド ID 認証を構成できます。 統合パターンの詳細については、「[Microsoft Entra ハイブリッド ID ソリューションの適切な認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)」をご覧ください。

オンプレミスの SAP システムがある場合は、Microsoft Entra Private Access のグローバル セキュア アクセス クライアントを使用して、組織のユーザーがそれらのシステムに接続する方法を最新化できます。 グローバル セキュア アクセス クライアントがインストールされている場合、リモートの作業者がこれらのリソースにアクセスするために VPN を使用する必要はありません。 クライアントは、それらを必要なリソースへバックグラウンドでシームレスに接続します。 詳細については、「[Microsoft Entra プライベート アクセス](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-private-access)」に関する記事を参照してください。

#### エンド ユーザー セルフサービスの移行

組織は、SAP IDM の[ログオン ヘルプ](https://help.sap.com/docs/SAP_IDENTITY_MANAGEMENT/d376345fb4e94928a70036ddf91d690b/d0faf50c76ef43f8808165ad855bc8c6.html)を使用して、エンド ユーザーが Windows Server AD パスワードをリセットできるようにしている可能性があります。

Microsoft Entra セルフサービス パスワード リセット (SSPR) を使用すると、管理者やヘルプ デスクが関与することなく、ユーザーが自分でパスワードを変更したり、リセットしたりできます。 Microsoft Entra SSPR を構成したら、[ユーザーのサインイン時に登録を求める](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks#require-users-to-register-when-they-sign-in)ことができます。 そうすると、ユーザーはアカウントがロックされた場合やパスワードを忘れた場合でも、画面の指示に従って自分自身のブロックを解除して、作業に戻ることができます。 ユーザーがクラウドで SSPR を使用してパスワードを変更またはリセットすると、更新されたパスワードをオンプレミスの AD DS 環境に書き戻すこともできます。 SSPR の動作の詳細については、「[Microsoft Entra のセルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)」を参照してください。 Microsoft Entra ID と Windows Server AD に加えて、他のオンプレミス システムにパスワード変更を送信する必要がある場合は、Microsoft Identity Manager (MIM) を使用したパスワード変更通知サービス (PCNS) のようなツールを使用することで実行できます。 このシナリオについては、「[MIM パスワード変更通知サービスを展開する](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/deploying-mim-password-change-notification-service-on-domain-controller)」をご覧ください。

Microsoft Entra では、[グループ管理](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)、セルフサービス アクセス要求、承認、レビューのためのエンド ユーザーのセルフサービスもサポートされています。 Microsoft Entra ID ガバナンス を使用したセルフサービス アクセス管理の詳細については、「アクセス ライフサイクル管理」の次のセクションを参照してください。

#### アクセス ライフサイクル管理シナリオの移行

組織は、SAP IDM と SAP アクセス ガバナンスを統合して、アクセスの承認、リスク評価、職務チェックの分離、およびその他の操作を行う場合があります。

Microsoft Entra には、組織が ID およびアクセス管理のシナリオをクラウドに導入できるようにするための複数のアクセス ライフサイクル管理テクノロジが含まれています。 テクノロジの選択は、組織のアプリケーション要件と Microsoft Entra ライセンスによって異なります。

- **Microsoft Entra ID セキュリティ グループ管理によるアクセス管理。** 従来の Windows Server AD ベースのアプリケーションでは、認可のためにセキュリティ グループのメンバーシップを確認する必要がありました。 Microsoft Entra では、SAML 要求、プロビジョニング、または [Windows Server AD へのグループの書き込み](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-write-back-portal)により、アプリケーションで動的メンバーシップ グループを使用できます。 OpenID Connect to SAP Cloud Identity Services で要求として送信されたグループ メンバーシップを使用して、SAP [BTP アプリケーションへのアクセスを管理](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276) できます。 Microsoft Entra から SAP Cloud Identity Services にグループをプロビジョニングすることもできます。その後、SAP Cloud Identity Services は、それらのグループをロールとして他の SAP アプリケーションにプロビジョニングできます。

    Microsoft Entra では、管理者は[動的メンバーシップ グループの管理](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-bulk-import-members)、[動的メンバーシップ グループのアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)の作成、および[セルフサービス グループ管理](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)の有効化ができます。 セルフサービスを使用すると、グループ所有者は、メンバーシップ要求の承認または拒否、および動的メンバーシップ グループの制御の委任ができます。 また、[グループに対して](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-discover-groups) Privileged Identity Management (PIM) を使用すると、グループ内の Just-In-Time メンバーシップまたはグループの Just-In-Time 所有権を管理できます。
- **[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)アクセス パッケージを使用したアクセス管理。**. エンタイトルメント管理は、アクセス要求と承認のワークフロー、アクセス割り当て、レビュー、および期限切れ処理を自動化することで、ID とアクセスのライフサイクルを組織が大規模に管理できるようにする、ID ガバナンス機能です。 エンタイトルメント管理は、グループやアプリケーション ロールの割り当てを使用するアプリケーション、または Azure Logic Apps 用のコネクタを持つアプリケーションへのきめ細かいアクセス割り当てに使用できます。

    エンタイトルメント管理を使用すると、組織は、アクセス パッケージと呼ばれる標準化されたアクセス権のコレクションを使用して、複数のリソースにわたってユーザーにアクセスを割り当てる方法に関するプラクティスを実装できます。 各アクセス パッケージは、グループへのメンバーシップ、アプリケーション ロールへの割り当て、または SharePoint Online サイトのメンバーシップを付与します。 アクセス パッケージを使用してビジネス ロールを表すことができます。ビジネス ロールには、1 つ以上のアプリケーションにわたって技術的なロールまたは特権が組み込まれています。 エンタイトルメント管理を構成すれば、ユーザーが自分の部署やコスト センターなどのユーザー プロパティに基づいてアクセス パッケージの割り当てを自動的に受け取るようにすることができます。 また、ユーザーの参加と脱退時に割り当てを追加または削除するようにライフサイクル ワークフローを構成することもできます。 管理者は、アクセス パッケージへの割り当てをユーザーに要求することもできます。また、ユーザーは自分でアクセス パッケージを持つよう要求することもできます。 ユーザーが要求できるアクセス パッケージは、ユーザーのセキュリティ動的メンバーシップ グループによって決まります。 ユーザーは、すぐに必要なアクセスを要求したり、以後にアクセスを要求したり、時間または日の制限時間を指定したりできます。また、質問への回答を含めたり、追加の属性の値を指定したりもできます。 リクエストは、自動承認されるように設定することも、マネージャー、ロールの所有者、またはその他の承認者による複数段階の承認を経るように設定することもできます。承認者が対応できない場合や応答しない場合は、エスカレーション先の承認者に回付されます。 承認されると、要求者にはアクセス権が割り当てられたことが通知されます。 アクセス パッケージへの割り当ては時間制限付きである場合があり、管理者、リソース所有者、またはその他の承認者が、ユーザーの継続的なアクセスの必要性を定期的に再認定または構成証明し直すように、定期的なアクセス レビューを構成できます。 ロール モデルで表される承認ポリシーをエンタイトルメント管理に移行する方法の詳細については、「[組織のロール モデルを移行する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles)」を参照してください。 移行プロセスを自動化するには、[PowerShell を使用してアクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app)できます。

    [Image: エンタイトルメント管理の概要の図]
- **SAP IAG と統合されたエンタイトルメント管理によるアクセス管理**。 SAP Cloud Identity Services を通じてプロビジョニングされたグループ メンバーシップを使用してユーザーをロールに割り当てるだけでなく、Microsoft Entra エンタイトルメント管理を SAP Cloud Identity Access Governance (IAG) と統合することもできます。 この統合により、SAP IAG ビジネス ロールをエンタイトルメント管理カタログのリソースとして追加できるため、Microsoft Entra アクセス パッケージを介して SAP アプリケーションへのアクセス権をユーザーに付与できます。 その後、Microsoft Entra の承認に基づいて、SAP IAG およびダウンストリーム SAP アプリケーションでのロールの割り当てを自動化できます。 詳細については、 [Microsoft Entra SAP IAG 統合 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-sap-integration) を参照してください。
- **エンタイトルメント管理と外部 GRC 製品によるアクセス管理。**[Pathlock](https://pathlock.com/applications/microsoft-entra-id-governance/) および他のパートナー製品への Microsoft Entra 統合により、お客様は、Microsoft Entra ID ガバナンスのアクセス パッケージを使用して、これらの製品に適用される追加のリスクときめ細かな職務分離チェックを利用できます。

#### レポート用の Microsoft Entra の使用

Microsoft Entra には、監査ログ、サインイン ログ、プロビジョニングログのデータに基づいて Azure Monitor に表示されるレポートやワークブックが搭載されています。 Microsoft Entra で使用できるレポート オプションは次のとおりです。

- 管理センターの Microsoft Entra 組み込みのレポートには、サインイン データをアプリケーション中心に表示する [使用状況レポートと分析情報レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report?tabs=microsoft-entra-admin-center) が含まれます。 これらのレポートを使用して、[通常とは異なるアカウントの作成と削除や、通常とは異なるアカウント使用状況を監視できます](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-user-accounts)。
- Microsoft Entra 管理センターからデータをエクスポートして、独自のレポートの生成に使用できます。 たとえば、[ユーザーとその属性の一覧のダウンロード](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-download)や、Microsoft Entra 管理センターからの[ログのダウンロード](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs)ができます ([プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-provisioning-logs)など)。
- Microsoft Graph にクエリを実行して、レポートで使用するデータを取得できます。 たとえば、[Microsoft Entra ID で非アクティブなユーザー アカウントの一覧を取得](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts)できます。
- Microsoft Graph API の上にある PowerShell コマンドレットを使用して、レポートに適したコンテンツをエクスポートおよび再構築できます。 たとえば、Microsoft Entra エンタイトルメント管理を使用している場合、[PowerShell 内のアクセス パッケージへの割り当ての一覧](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#view-assignments-with-powershell)を取得できます。
- ユーザーやグループなどのオブジェクトのコレクションを Microsoft Entra から Azure Data Explorer にエクスポートできます。 詳細については、「[Microsoft Entra ID のデータを使用して Azure Data Explorer (ADX) でレポートをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-entitlement-report-with-adx-and-entra-id)」を参照してください。
- アラートを取得し、Azure Monitor に送信された監査、サインイン、プロビジョニング ログに関するブック、カスタム クエリ、レポートを使用できます。 たとえば、Microsoft Entra 管理センターまたは PowerShell を使用して [Azure Monitor でログをアーカイブし、エンタイトルメント管理に関するレポートを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting)できます。 監査ログには、Microsoft Entra でオブジェクトを作成したユーザーおよび変更したユーザーの詳細が含まれます。 Azure Monitor には、[長期的な期間のデータ保持](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/data-retention-archive)のオプションも用意されています。

#### 組織の境界を越えた ID 情報の交換の移行

組織によっては、SAP IDM [ID フェデレーション](https://help.sap.com/docs/SAP_IDENTITY_MANAGEMENT/d376345fb4e94928a70036ddf91d690b/5674c5a6cf30402390df5abbfded5195.html)を使用して、会社の境界を越えてユーザーに関する ID 情報を交換している場合があります。

Microsoft Entra には、[マルチテナントの組織](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview)向けの機能があり、これを使用すると、複数の Microsoft Entra ID テナントがあるテナントが 1 つのテナントからユーザーをまとめて、別のテナントでアプリケーションにアクセスしたり、共同作業を行ったりできるようになります。 次の図は、マルチテナント組織のテナント間同期機能を使用して、あるテナントのユーザーが組織内の別のテナントのアプリケーションに自動的にアクセスできるようにする方法を示しています。

[Image: 複数のテナントのユーザーの同期を示す図。]

Microsoft Entra 外部 ID には、従業員がビジネス パートナー組織やゲストと安全に連携し、会社のアプリケーションを共有することができる、[B2B コラボレーション機能](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)が含まれています。 ゲスト ユーザーは、各自の職場、学校、またはソーシャルの ID を使用して、アプリとサービスにサインインします。 独自の Microsoft Entra ID テナントを持ち、そこでユーザーを認証しているビジネス パートナー組織の場合は、[テナント間アクセス設定を構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)できます。 また、Microsoft Entra テナントを持たず、代わりに独自の ID プロバイダーを持つビジネス パートナー組織の場合は、[ゲスト ユーザー向けとして SAML/WS-Fed ID プロバイダーとのフェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)を構成できます。 Microsoft Entra エンタイトルメント管理を使用すると、ゲストをテナントに取り込む前に承認を使用してアクセス パッケージを構成し、アクセス レビュー中に継続的なアクセスが拒否されたときにゲストを自動的に削除することで、[外部ユーザーの ID およびアクセス管理ライフサイクル](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users)を管理できます。

[Image: 外部ユーザーのライフサイクルを示す図]

#### ディレクトリ サービスが必要なアプリケーションを移行する

一部の組織では、アプリケーションでの ID の読み取りと書き込み呼び出しのために、ディレクトリ サービスとして SAP IDM を使用していることがあります。 Microsoft Entra ID は、最新のアプリケーションに対しディレクトリ サービスを提供します。アプリケーションは、ユーザー、グループ、その他の ID 情報を照会および更新するための呼び出しを、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/overview) を介して行なえます。

ユーザーまたはグループを読み取るために、LDAP インターフェイスが引き続き必要なアプリケーションに対しては、いくつかのオプションが Microsoft Entra で用意されています。

- [Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview) は、クラウド内のアプリケーションと VM に対し ID サービスを提供します。また、ドメイン参加やセキュアな LDAP の操作などのために、従来型 AD DS 環境との互換性も備えています。 Domain Services では、ID 情報が Microsoft Entra ID からレプリケートされるため、クラウド専用の Microsoft Entra テナントや、オンプレミスの AD DS 環境と同期される Microsoft Entra テナントとも連携します。
- Microsoft Entra を使用して、[SuccessFactors からのワーカーをオンプレミスの Active Directory に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)追加している場合、アプリケーションは、その Windows Server Active Directory からユーザーを読み取ることができます。 アプリケーションで動的メンバーシップ グループも必要な場合には、Microsoft Entra 内の対応するグループから Windows Server AD グループを生成できます。 詳細情報については、「[Microsoft Entra クラウド同期を使ったグループ書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/group-writeback-cloud-sync)」を参照してください。
- これまで、別の LDAP ディレクトリを使用してきた組織であれば、[その LDAP ディレクトリにユーザーをプロビジョニングするように Microsoft Entra ID を構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)できます。

#### 統合インターフェイスを介した Microsoft Entra の拡張

Microsoft Entra には、次のような、サービス全体の統合と拡張のための複数のインターフェイスが含まれています。

- アプリケーションは、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/overview) を介して Microsoft Entra を呼び出して、ID 情報、構成とポリシーのクエリと更新、ログ、状態、レポートの取得を行えます。
- 管理者は、[SCIM](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)、[SOAP または REST](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector)、および [ECMA API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector)を使用して、アプリケーションへのプロビジョニングを構成できます。
- 管理者は、[受信プロビジョニング API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app) を使用して、他のレコード ソース システムからワーカー レコードを取り込み、Windows Server AD と Microsoft Entra ID にユーザー更新プログラムを提供できます。
- Microsoft Entra ID ガバナンス を持つテナントの管理者は、[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration)と[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)からカスタム Azure Logic Apps の呼び出しを構成することもできます。 これにより、ユーザーのオンボード、オフボード、アクセス要求と割り当てのプロセスをカスタマイズできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-active-directory-to-entra-cloud-sync"} -->
## オンプレミスからのプロビジョニングと Entra Connect クラウド同期を使用してクラウド ユーザーとグループを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-active-directory-to-entra-cloud-sync
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事は、クラウド同期を使用してユーザーとグループをプロビジョニングする方法に関するチュートリアルです。

**シナリオ:** Microsoft Entra クラウド同期を使用して Active Directory からプロビジョニングされたクラウド ユーザーとグループを管理します。

### 前提条件

#### Microsoft Entra 管理センターで

- Microsoft では、組織に、[グローバル管理者の](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられている 2 つのクラウド専用緊急アクセス アカウントを持っていることをお勧めします。 これらのアカウントは高い特権を持ち、特定の個人には割り当てられません。 これらのアカウントは、通常のアカウントを使用できない、または他のすべての管理者が誤ってロックアウトされたといったような、緊急または "ブレーク グラス" のシナリオに限定されます。これらのアカウントは、[緊急アクセス アカウントに関する推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。
- Microsoft Entra テナントに 1 つ以上の[カスタム ドメイン名](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)を追加します。 ユーザーは、このドメイン名のいずれかを使用してサインインできます。

#### オンプレミスの環境の場合

1. 4 GB 以上の RAM と .NET 4.7.1 以降のランタイムを搭載した、Windows Server 2016 以降が稼働するドメイン参加済みホスト サーバーを探す
2. サーバーと Microsoft Entra ID の間にファイアウォールがある場合は、次の項目を構成します:

    - エージェントが次のポートを介して Microsoft Entra ID に "送信" 要求を行うことができるようにします。

        | ポート番号 | 用途 |
        | --- | --- |
        | **80** | TLS/SSL 証明書を検証する際に証明書失効リスト (CRL) をダウンロードします |
        | **443** | サービスを使用したすべての送信方向の通信を処理する |
        | **8080** (省略可能) | ポート 443 が使用できない場合、エージェントは、ポート 8080 経由で 10 分おきにその状態をレポートします。 この状態はポータルに表示されます。 |

        ご利用のファイアウォールが送信元ユーザーに応じて規則を適用している場合は、ネットワーク サービスとして実行されている Windows サービスを送信元とするトラフィックに対してこれらのポートを開放します。
    - ファイアウォールまたはプロキシで安全なサフィックスの指定が許可されている場合は、**\*.msappproxy.net** および **\*.servicebus.windows.net** への接続を追加します。 そうでない場合は、毎週更新される [Azure データセンターの IP 範囲](https://www.microsoft.com/download/details.aspx?id=41653)へのアクセスを許可します。
    - エージェントは、初期登録のために **login.windows.net** と **login.microsoftonline.com** にアクセスする必要があります。 これらの URL にもファイアウォールを開きます。
    - 証明書の検証のために、URL **mscrl.microsoft.com:80**、**crl.microsoft.com:80**、**ocsp.msocsp.com:80**、**www.microsoft.com:80** のブロックを解除します。 他の Microsoft 製品でこれらの URL を証明書の検証に使用しているため、URL のブロックを既に解除している可能性があります。

### Microsoft Entra プロビジョニング エージェントをインストールする

[AD と Azure の基本的な環境](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-basic-ad-azure)に関するチュートリアルを使用している場合、これは DC1 になります。 エージェントをインストールするには、これらの手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. 左側のウィンドウで、[ **Entra Connect**] を選択し、[ **クラウド同期**] を選択します。

    [Image: [作業の開始] 画面を示すスクリーンショット。]
3. 左側のウィンドウで、[エージェント] を選択 **します**。
4. [ **オンプレミス エージェントのダウンロード**] を選択し、[ **同意する]** を選択してダウンロードします。

    [Image: エージェントのダウンロードを示すスクリーンショット。]
5. Microsoft Entra Connect プロビジョニング エージェント パッケージをダウンロードしたら、ダウンロード フォルダーから *AADConnectProvisioningAgentSetup.exe* インストール ファイルを実行します。
6. 開いた画面で、[ **ライセンス条項に同意** する] チェック ボックスをオンにし、[ **インストール**] を選択します。

    [Image: Microsoft Entra Provisioning Agent Package のライセンス条項を示すスクリーンショット。]
7. インストールが完了すると、構成ウィザードが開きます。 **[次へ]** を選択して構成を開始します。

    [Image: ようこそ画面を示すスクリーンショット。]
8. 少なくとも [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) ロールを持つアカウントでサインインします。 Internet Explorer のセキュリティ強化が有効になっている場合は、サインインがブロックされます。 その場合は、インストールを閉じ、 [Internet Explorer のセキュリティ強化を無効に](https://learn.microsoft.com/ja-jp/troubleshoot/developer/browsers/security-privacy/enhanced-security-configuration-faq)して、Microsoft Entra Provisioning Agent パッケージのインストールを再起動します。

    [Image: [Microsoft Entra ID の接続] 画面を示すスクリーンショット。]
9. **[サービス アカウントの構成]** 画面で、グループ管理サービス アカウント (gMSA) を選択します。 このアカウントはエージェント サービスの実行に使用されます。 マネージド サービス アカウントが別のエージェントによってドメインに既に構成されていて、2 つ目のエージェントをインストールしている場合は、[ **gMSA の作成**] を選択します。 システムは既存のアカウントを検出し、gMSA アカウントを使用するために新しいエージェントに必要なアクセス許可を追加します。 メッセージが表示されたら、次の 2 つのオプションのいずれかを選択します。

    - **gMSA の作成**: エージェントが **provAgentgMSA$** マネージド サービス アカウントを作成できるようにします。 グループ管理サービス アカウント ( `CONTOSO\provAgentgMSA$` など) は、ホスト サーバーが参加したのと同じ Active Directory ドメインに作成されます。 このオプションを使用するには、Active Directory ドメイン管理者の資格情報を入力します (推奨)。
    - **カスタム gMSA の使用**: このタスク用に手動で作成したマネージド サービス アカウントの名前を指定します。

    [Image: グループの管理対象サービス アカウントを構成する方法を示すスクリーンショット。]
10. 続けるには、 **[次へ]** を選択します。
11. **[Active Directory の接続]** 画面で、ドメイン名が **[構成済みドメイン]** の下に表示される場合は、次の手順に進みます。 それ以外の場合は、Active Directory ドメイン名を入力し、[ **ディレクトリの追加]** を選択します。

    [Image: 構成済みのドメインを示すスクリーンショット。]
12. Active Directory ドメイン管理者アカウントでサインインします。 ドメイン管理者アカウントには、有効期限が切れたパスワードがあってはなりません。 エージェントのインストール中にパスワードの有効期限が切れている場合や変更された場合は、新しい資格情報を使用してエージェントを再構成します。 この操作によってオンプレミス ディレクトリが追加されます。 [ **OK] を**選択し、[ **次へ** ] を選択して続行します。
13. **[次へ]** を選択して続行します。
14. **[構成の完了]** 画面で、 **[確認]** を選択します。 この操作によって、エージェントが登録されて再起動されます。

    [Image: 完了画面を示すスクリーンショット。]
15. 操作が完了すると、エージェントの構成が正常に検証されたことを示す通知が表示されます。 **[終了]** を選択します。 まだ最初の画面が表示される場合は、[ **閉じる**] を選択します。

### エエージェントのインストールを確認する

エージェントの検証は、Azure portal と、エージェントを実行するローカル サーバーで行われます。

#### Azure portal でエージェントを確認する

Microsoft Entra ID によってエージェントが登録されていることを確認するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra Connect**] を選択し、[**クラウド同期**] を選択します。

    [Image: [作業の開始] 画面を示すスクリーンショット。]
3. [ **クラウド同期** ] ページで、[ **エージェント** ] をクリックして、インストールしたエージェントを表示します。 エージェントが表示され、状態が **アクティブ**であることを確認します。

#### ローカル サーバー上のエージェントを確認する

エージェントが実行されていることを確認するには、次の手順に従います。

1. 管理者アカウントでサーバーにサインインします。
2. **[サービス**] に移動します。 *Start/Run/Services.msc* を使用してアクセスすることもできます。
3. [ **サービス**] で、 **Microsoft Azure AD Connect エージェント アップデーター** と **Microsoft Azure AD Connect プロビジョニング エージェント** が存在し、状態が **[実行中**] であることを確認します。

    [Image: Windows サービスを示すスクリーンショット。]

#### プロビジョニング エージェントのバージョンを確認する

実行中のエージェントのバージョンを確認するには、次の手順に従います。

1. *C:\Program Files\Microsoft Azure AD Connect プロビジョニング エージェントに*移動します。
2. *AADConnectProvisioningAgent.exe* を右クリックし、[プロパティ] を選択**します**。
3. [ **詳細** ] タブを選択します。製品バージョンの横にバージョン番号が表示されます。

### Microsoft Entra クラウド同期を構成する

プロビジョニングを構成して開始するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. **新しい構成**&gt;**AD から Microsoft Entra ID 同期**を選択します。
2. 同期するドメインを選択し、[ **作成**] を選択します。

Microsoft Entra Cloud Sync を構成する方法の詳細については、「 [Microsoft Entra ID への Active Directory のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)」を参照してください。

### ユーザーが作成され、同期が実行されていることを確認する

ここで、同期範囲内にあるオンプレミス ディレクトリに存在していたユーザーが同期され、現在は Microsoft Entra テナントに存在することを確認します。 この同期操作が完了するまで数時間かかることがあります。 ユーザーが同期されていることを確認するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. テナントに新しいユーザーが表示されることを確認する

### いずれかのユーザーでサインインをテストする

1. https://myapps.microsoft.com に移動します
2. テナントで作成されたユーザー アカウントを使用してサインインします。 user@domain.onmicrosoft.com の形式を使用してサインインする必要があります。 ユーザーがオンプレミスでのサインインに使用するのと同じパスワードを使用します。

[Image: サインインしているユーザーのマイ アプリ ポータルを示すスクリーンショット。]

これで Microsoft Entra クラウド同期を使用してハイブリッド ID 環境が正常に構成されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-active-directory-to-entra-connect-sync"} -->
## オンプレミスと Entra Connect Sync からのプロビジョニングを使用してクラウド ユーザーとグループを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-active-directory-to-entra-connect-sync
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事では、接続同期を使用してユーザーとグループをプロビジョニングする方法に関するチュートリアルです。

**シナリオ：** Microsoft Entra Connect 同期を使用して、Active Directory からプロビジョニングされたクラウド ユーザーとグループを管理します。

### [前提条件]

- [Hyper-V](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/hyper-v-technology-overview) がインストールされているコンピューター。 [windows 10 または Windows Server 2016](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/about/supported-guest-os) コンピューターに Hyper-V をインストールすることをお勧めします。
- Azure サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。
- [外部ネットワーク アダプター](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/quick-start/connect-to-network)。仮想マシンがインターネットに接続できるようにします。
- Windows Server 2016 のコピー。

注

このチュートリアルでは、PowerShell スクリプトを使用して、チュートリアル環境をすばやく作成します。 各スクリプトでは、そのスクリプトの先頭で宣言された変数が使用されます。 変数は環境に合わせて変更してください。

このチュートリアルのスクリプトでは、Microsoft Entra Connect をインストールする前に、一般的な Windows Server Active Directory (Windows Server AD) 環境を作成します。 スクリプトは、関連するチュートリアルでも使用されます。

このチュートリアルで使用する PowerShell スクリプトは、 [GitHub](https://github.com/billmath/tutorial-phs) で入手できます。

### 仮想マシンを作成する

ハイブリッド ID 環境を作成するための最初のタスクは、オンプレミスのWindows Server AD サーバーとして使用する仮想マシンを作成することです。

注

ホスト コンピューターにおいて PowerShell でスクリプトを実行したことがない場合は、スクリプトを実行する前に、管理者として Windows PowerShell ISE 開き、`Set-ExecutionPolicy remotesigned` を実行します。 [ **実行ポリシーの変更** ] ダイアログで、[ **はい**] を選択します。

仮想マシンを作成するには:

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $VMName = 'DC1'
    $Switch = 'External'
    $InstallMedia = 'D:\ISO\en_windows_server_2016_updated_feb_2018_x64_dvd_11636692.iso'
    $Path = 'D:\VM'
    $VHDPath = 'D:\VM\DC1\DC1.vhdx'
    $VHDSize = '64424509440'
    
    #Create a new virtual machine
    New-VM -Name $VMName -MemoryStartupBytes 16GB -BootDevice VHD -Path $Path -NewVHDPath $VHDPath -NewVHDSizeBytes $VHDSize  -Generation 2 -Switch $Switch  
    
    #Set the memory to be non-dynamic
    Set-VMMemory $VMName -DynamicMemoryEnabled $false
    
    #Add a DVD drive to the virtual machine
    Add-VMDvdDrive -VMName $VMName -ControllerNumber 0 -ControllerLocation 1 -Path $InstallMedia
    
    #Mount installation media
    $DVDDrive = Get-VMDvdDrive -VMName $VMName
    
    #Configure the virtual machine to boot from the DVD
    Set-VMFirmware -VMName $VMName -FirstBootDevice $DVDDrive 
    ```

### オペレーティング システムをインストールする

仮想マシンの作成を完了するには、オペレーティング システムをインストールします。

1. Hyper-V マネージャーで、仮想マシンをダブルクリックします。
2. **[スタート] を選択します**。
3. プロンプトで、任意のキーを押して CD または DVD から起動します。
4. Windows Server のスタート ウィンドウで、言語を選択し、[ **次へ**] を選択します。
5. [ **今すぐインストール]** を選択します。
6. ライセンス キーを入力し、[ **次へ**] を選択します。
7. [ **ライセンス条項に同意する** ] チェック ボックスをオンにし、[ **次へ**] を選択します。
8. [ **カスタム: Windows のみをインストールする (詳細)]**を選択します。
9. [ **次へ**] を選択します。
10. インストールが完了したら、仮想マシンを再起動します。 サインインし、[Windows Update] をオンにします。 更新プログラムをインストールして、VM が完全に最新であることを確認します。

### Windows Server AD の前提条件をインストールする

Windows Server ADをインストールする前に、前提条件をインストールするスクリプトを実行します。

1. Windows PowerShell ISE を管理者として開きます。
2. `Set-ExecutionPolicy remotesigned` を実行します。 **[実行ポリシーの変更**] ダイアログで、[**はい] を [すべて] に**選択します。
3. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $ipaddress = "10.0.1.117" 
    $ipprefix = "24" 
    $ipgw = "10.0.1.1" 
    $ipdns = "10.0.1.117"
    $ipdns2 = "4.2.2.2" 
    $ipif = (Get-NetAdapter).ifIndex 
    $featureLogPath = "c:\poshlog\featurelog.txt" 
    $newname = "DC1"
    $addsTools = "RSAT-AD-Tools" 
    
    #Set a static IP address
    New-NetIPAddress -IPAddress $ipaddress -PrefixLength $ipprefix -InterfaceIndex $ipif -DefaultGateway $ipgw 
    
    # Set the DNS servers
    Set-DnsClientServerAddress -InterfaceIndex $ipif -ServerAddresses ($ipdns, $ipdns2)
    
    #Rename the computer 
    Rename-Computer -NewName $newname -force 
    
    #Install features 
    New-Item $featureLogPath -ItemType file -Force 
    Add-WindowsFeature $addsTools 
    Get-WindowsFeature | Where installed >>$featureLogPath 
    
    #Restart the computer 
    Restart-Computer
    ```

### Windows Server AD 環境を作成する

次に、環境を作成するために Active Directory Domain Services をインストールして構成します。

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $DatabasePath = "c:\windows\NTDS"
    $DomainMode = "WinThreshold"
    $DomainName = "contoso.com"
    $DomainNetBIOSName = "CONTOSO"
    $ForestMode = "WinThreshold"
    $LogPath = "c:\windows\NTDS"
    $SysVolPath = "c:\windows\SYSVOL"
    $featureLogPath = "c:\poshlog\featurelog.txt" 
    $Password = "Pass1w0rd"
    $SecureString = ConvertTo-SecureString $Password -AsPlainText -Force
    
    #Install Active Directory Domain Services, DNS, and Group Policy Management Console 
    start-job -Name addFeature -ScriptBlock { 
    Add-WindowsFeature -Name "ad-domain-services" -IncludeAllSubFeature -IncludeManagementTools 
    Add-WindowsFeature -Name "dns" -IncludeAllSubFeature -IncludeManagementTools 
    Add-WindowsFeature -Name "gpmc" -IncludeAllSubFeature -IncludeManagementTools } 
    Wait-Job -Name addFeature 
    Get-WindowsFeature | Where installed >>$featureLogPath
    
    #Create a new Windows Server AD forest
    Install-ADDSForest -CreateDnsDelegation:$false -DatabasePath $DatabasePath -DomainMode $DomainMode -DomainName $DomainName -SafeModeAdministratorPassword $SecureString -DomainNetbiosName $DomainNetBIOSName -ForestMode $ForestMode -InstallDns:$true -LogPath $LogPath -NoRebootOnCompletion:$false -SysvolPath $SysVolPath -Force:$true
    ```

### Windows Server AD ユーザーを作成する

次に、テスト用のユーザー アカウントを作成します。 オンプレミスの Active Directory 環境でこのアカウントを作成します。 その後、アカウントは Microsoft Entra ID に同期されます。

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $Givenname = "Allie"
    $Surname = "McCray"
    $Displayname = "Allie McCray"
    $Name = "amccray"
    $Password = "Pass1w0rd"
    $Identity = "CN=ammccray,CN=Users,DC=contoso,DC=com"
    $SecureString = ConvertTo-SecureString $Password -AsPlainText -Force
    
    #Create the user
    New-ADUser -Name $Name -GivenName $Givenname -Surname $Surname -DisplayName $Displayname -AccountPassword $SecureString
    
    #Set the password to never expire
    Set-ADUser -Identity $Identity -PasswordNeverExpires $true -ChangePasswordAtLogon $false -Enabled $true
    ```

### Microsoft Entra テナントを作成する

お持ちでない場合は、「 [Microsoft Entra ID で新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant) する」の記事の手順に従って、新しいテナントを作成します。

### Microsoft Entra ID でハイブリッド ID 管理者を作成する

次のタスクは、ハイブリッド ID 管理者アカウントを作成することです。 このアカウントは、Microsoft Entra Connect のインストール中に Microsoft Entra Connector アカウントを作成するために使用されます。 Microsoft Entra Connector アカウントは、Microsoft Entra ID に情報を書き込むために使用されます。

ハイブリッド ID 管理者アカウントを作成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Users** を参照する
3. [**新しいユーザー**] を選択&gt;**新しいユーザーを作成します**。
4. [ **新しいユーザーの作成** ] ウィンドウで、新しいユーザーの **表示名** と **ユーザー プリンシパル名**を入力します。 テナントのハイブリッド ID 管理者アカウントを作成しているところです。 一時パスワードを表示およびコピーできます。
    1. [ **割り当て]** で [ **ロールの追加]** を選択し、[ **ハイブリッド ID 管理者] を選択します**。
5. 次に、[ **確認と作成**&gt;**作成**] を選択します。
6. 新しい Web ブラウザー ウィンドウで、新しいハイブリッド ID 管理者アカウントと一時パスワードを使用して `myapps.microsoft.com` にサインインします。

### Microsoft Entra Connect をダウンロードしてインストールする

ここで、Microsoft Entra Connect をダウンロードしてインストールします。 インストール後、高速インストールを使用します。

1. [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) をダウンロードします。
2. *AzureADConnect.msi* に移動し、ダブルクリックしてインストール ファイルを開きます。
3. [ **ようこそ**] で、チェックボックスをオンにしてライセンス条項に同意し、[続行] を選択 **します**。
4. **[高速設定]** で、[**高速設定を使用**] を選択します。
5. [ **Microsoft Entra ID への接続] で、Microsoft Entra ID** のハイブリッド ID 管理者アカウントのユーザー名とパスワードを入力します。 [ **次へ**] を選択します。
6. [ **AD DS への接続**] で、エンタープライズ管理者アカウントのユーザー名とパスワードを入力します。 [ **次へ**] を選択します。
7. [ **構成の準備完了]** で、[インストール] を選択 **します**。
8. インストールが完了したら、[終了] を選択 **します**。
9. 次に、Synchronization Service Manager または同期規則エディターを使用する前に、サインアウトしてから、もう一度サインインします。

### ポータルでユーザーを確認する

次に、オンプレミスの Active Directory テナント内のユーザーが同期され、Microsoft Entra テナントに存在することを確認します。 このセクションが完了するまでに数時間かかることがあります。

ユーザーが同期されていることを確認するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** を参照する
3. テナントに新しいユーザーが表示されていることを確認します。

    [Image: ユーザーが Microsoft Entra ID で同期されたことを確認するスクリーンショット。]

### ユーザー アカウントでサインインして同期をテスト

Windows Server AD テナントのユーザーが Microsoft Entra テナントと同期されていることをテストするには、次のいずれかのユーザーとしてサインインします。

1. https://myapps.microsoft.com にアクセスします。
2. 新しいテナントで作成されたユーザー アカウントを使用してサインインします。

    ユーザー名には、`user@domain.onmicrosoft.com` という形式を使用します。 ユーザーがオンプレミスの Active Directory へのサインインに使用するのと同じパスワードを使用します。

ハイブリッド ID 環境を正常に設定できました。これは、Azure で提供されるものをテストしたり習熟したりするために使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-entra-to-active-directory-groups"} -->
## Microsoft Entra Cloud Sync を使用してオンプレミスの Active Directory ベースのアプリ (Kerberos) を管理する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-entra-to-active-directory-groups
- Service: entra-id-governance
- Article date: 2025-06-09
- Summary: この記事は、ユーザーとグループを Microsoft Entra ID から Active Directory にプロビジョニングし、Microsoft Entra ID で管理する方法に関するチュートリアルです。

**シナリオ:** クラウドでプロビジョニングおよび管理される Active Directory グループを使用してオンプレミス アプリケーションを管理します。 Microsoft Entra クラウド同期を使用すると、Microsoft Entra ID ガバナンスの機能を利用してアクセス関連の要求を制御および修復しながら、AD でのアプリケーションの割り当てを完全に管理できます。

重要

Microsoft Entra Connect Sync でのグループ ライトバック v2 のプレビューは非推奨となり、サポートされなくなりました。

Microsoft Entra Cloud Sync を使用して、クラウド セキュリティ グループをオンプレミスの Active Directory Domain Services (AD DS) にプロビジョニングできます。

Microsoft Entra Connect Sync でグループ ライトバック v2 を使用する場合は、同期クライアントを Microsoft Entra Cloud Sync に移動する必要があります。Microsoft Entra Cloud Sync に移行する資格があるかどうかを確認するには、ユーザー同期ウィザードを使用します。

ウィザードで推奨されているように Microsoft Cloud Sync を使用できない場合は、Microsoft Entra Connect Sync と並行して Microsoft Entra Cloud Sync を実行できます。その場合は、Microsoft Entra Cloud Sync を実行して、クラウド セキュリティ グループをオンプレミスの AD DS にプロビジョニングするだけです。

MICROSOFT 365 グループを AD DS にプロビジョニングする場合は、グループ ライトバック v1 を引き続き使用できます。

### 前提条件

このシナリオを実装するには、次の前提条件が必要です。

- 少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持つ Microsoft Entra アカウント。
- Windows Server 2016 オペレーティング システム以降のオンプレミス Active Directory Domain Services (AD DS) 環境。

    - AD DS スキーマ属性 *msDS-ExternalDirectoryObjectId に*必要です。
- ビルド バージョンが [1.1.1367.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1113700) 以降のプロビジョニング エージェント。

    注

    サービス アカウントへのアクセス許可は、クリーン インストール中にのみ割り当てられます。 以前のバージョンからアップグレードする場合は、PowerShell を使用して手動でアクセス許可を割り当てます。

    ```powershell
    $credential = Get-Credential
    
    Set-AAD DSCloudSyncPermissions -PermissionType UserGroupCreateDelete -TargetDomain "FQDN of domain" -TargetDomainCredential $credential
    ```

    すべての子孫グループとユーザーのすべてのプロパティの読み取り、書き込み、作成、および削除を許可していることを確認します。

    これらのアクセス許可は、 [既定では、Microsoft Entra プロビジョニング エージェント gMSA PowerShell コマンドレット](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets#grant-permissions-to-a-specific-domain)によって AdminSDHolder オブジェクトに適用されません。
- プロビジョニング エージェントは、ライトウェイト ディレクトリ アクセス プロトコル (LDAP) 用のポート TCP/389 およびグローバル カタログ用 TCP/3268 上の 1 つ以上のドメイン コントローラーと通信できる必要があります。

    - 無効なメンバーシップ参照をフィルターで除外するための、グローバル カタログ検索に必要です。
- ビルド バージョンが [2.2.8.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#2280) 以降の Microsoft Entra Connect。

    - Microsoft Entra Connect を使用して同期されるオンプレミスのユーザー メンバーシップをサポートするために必要です。
    - `AD DS:user:objectGUID`を`Microsoft Entra ID:user:onPremisesObjectIdentifier`に同期するために必要です。

詳細については、「 [クラウド同期でサポートされるグループとスケールの制限」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites?tabs=public-cloud#supported-groups-and-scale-limits)。

### サポートされるグループ

このシナリオでは、以下のグループのみがサポートされます。

- クラウドで作成されたセキュリティ グループまたはソース オブ オーソリティ (SOA) で変換されたセキュリティ グループのみがサポートされます。
- 割り当てられたメンバーシップ グループまたは動的メンバーシップ グループ。
- オンプレミスの同期されたユーザーまたはクラウドで作成されたセキュリティ グループが含まれます。
- クラウドで作成されたセキュリティ グループのメンバーであるオンプレミス同期ユーザーは、同じドメインまたは同じフォレストの他のドメインから取得できます
- クラウドで作成されたセキュリティ グループはユニバーサル グループ スコープを使用して AD DS に書き戻されるため、フォレストは[ユニバーサル グループ](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/manage/understand-security-groups#group-scope)をサポートする必要があります
- メンバー数は 50,000 人以下
- 参照グループにおいて、直接の子グループはそれぞれ1つのメンバーとしてカウントされます。

### グループを AD DS にプロビジョニングする際の考慮事項

グループ SOA を変換した後でグループを AD DS にプロビジョニングし直す場合は、元の組織単位 (OU) にプロビジョニングし直します。 この方法により、Microsoft Entra Cloud Sync は、変換されたグループを AD DS に既に存在するグループと同じものとして認識します。

Cloud Sync は、両方のグループが同じセキュリティ識別子 (SID) を共有しているため、変換されたグループを認識します。 グループを別の OU にプロビジョニングすると、同じ SID が維持され、Microsoft Entra Cloud Sync によって既存のグループが更新されますが、アクセス制御リストに問題が発生する可能性があります。 アクセス許可は常にコンテナー間で正常に転送されるとは限りません。明示的なアクセス許可のみがグループでプロビジョニングされます。 OU に適用された元の OU またはグループ ポリシー オブジェクトのアクセス許可から継承されたアクセス許可は、グループでプロビジョニングされません。

SOA を変換する前に、次の推奨手順を検討してください。

1. 可能であれば、SOA を特定の組織単位に変換する予定のグループを移動します。 グループを移動できない場合は、グループの SOA を変換する前に、各グループの OU パスを元の OU パスに設定します。 元の OU パスを設定する方法の詳細については、「[Microsoft Entra IDからActive Directoryにユーザーとグループをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)」を参照してください。
2. SOA を変更します。
3. グループを AD DS にプロビジョニングする場合は、「[Microsoft Entra ID から Active Directory にユーザーとグループをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)」の説明に従って属性マッピングを設定します。
4. 他のグループのプロビジョニングを有効にする前に、最初にオンデマンド プロビジョニングを実行します。

AD DS にプロビジョニングされるグループのターゲットの場所を構成する方法の詳細については、「 [ターゲット コンテナーの構成」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#configure-the-target-container)を参照してください。

### Group SOA を使用してオンプレミスの AD DS ベースのアプリを管理する

このシナリオでは、AD DS ドメイン内のグループがアプリケーションによって使用されている場合、グループの SOA を Microsoft Entra に変換できます。 その後、エンタイトルメント管理やアクセス レビューなど、Microsoft Entra で行われたグループにメンバーシップの変更をプロビジョニングし、Microsoft Entra Cloud Sync を使用して AD DS に戻すことができます。このモデルでは、アプリを変更したり、新しいグループを作成したりする必要はありません。

[Image: Group Source of Authority への切り替えの概念図のスクリーンショット。]

アプリケーションで Group SOA オプションを使用するには、次の手順を使用します。

#### アプリケーションを作成して SOA を変換する

1. Microsoft Entra 管理センターを使用して、AD DS ベースのアプリケーションを表す Microsoft Entra ID でアプリケーションを作成し、ユーザー割り当てを要求するようにアプリケーションを構成します。
2. 変換する予定の AD DS グループが既に Microsoft Entra に同期されていること、および AD DS グループのメンバーシップがユーザーのみであり、必要に応じて Microsoft Entra にも同期される他のグループであることを確認します。 グループまたはグループのメンバーが Microsoft Entra で表されていない場合、グループの SOA を変換することはできません。
3. SOA を既存の同期されたクラウド グループに変換します。
4. SOA を変換した後、 [グループ プロビジョニングを AD DS に](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory) 使用して、このグループに対する後続の変更を AD DS にプロビジョニングします。 グループ プロビジョニングが有効になると、Microsoft Entra Cloud Sync は、変換されたグループが既に AD DS にあるグループと同じグループであることを認識します。これは、両方のグループに同じセキュリティ識別子 (SID) があるためです。 変換されたクラウド グループを AD DS にプロビジョニングすると、新しい AD DS グループを作成する代わりに、既存の AD DS グループが更新されます。

#### SOA 変換されたグループのメンバーシップを管理するように Microsoft Entra 機能を構成する

1. [アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)を作成します。 前の手順のアプリケーションとセキュリティ グループを、アクセス パッケージのリソースとして追加します。 アクセス パッケージで直接割り当てポリシーを構成します。
2. [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)で、AD DS ベースのアプリへのアクセスを必要とする同期されたユーザーをアクセス パッケージに割り当てます。
3. Microsoft Entra Cloud Sync が次の同期を完了するまで待ちます。 Active Directory ユーザーとコンピューターを使用して、正しいユーザーがグループのメンバーとして存在することを確認します。
4. AD DS ドメイン監視で、プロビジョニング エージェントを実行する [gMSA アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#group-managed-service-accounts) のみを許可し、新しい AD DS グループの [メンバーシップを変更する承認](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-security-group-management) を許可します。

詳細については、「 [クラウド優先体制の採用: グループの権限ソースをクラウドに変換する (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview)を参照してください。

### 新しくプロビジョニングされたクラウド セキュリティ グループを使用してオンプレミスの AD DS を管理する

このシナリオでは、クラウド同期グループ プロビジョニングによって作成された新しいグループの SID、名前、または識別名を確認するようにアプリケーションを更新します。 このシナリオは次の場合に適用されます。

- 初めて AD DS ドメインに接続される新しいアプリケーションのデプロイ。
- アプリケーションにアクセスするユーザーの新しいコーホート。
- アプリケーションの最新化のために、既存の AD DS グループへの依存を削減する。

`Domain Admins` グループのメンバーシップを現在チェックしているアプリケーションは、新しく作成された AD DS グループも確認するために更新する必要があります。

次のセクションの手順に従って、新しいグループを使用するようにアプリケーションを構成します。

#### アプリケーションとグループを作成する

1. Microsoft Entra 管理センターを使用して、AD DS ベースのアプリケーションを表す Microsoft Entra ID でアプリケーションを作成し、ユーザーの割り当てを要求するようにアプリケーションを構成します。
2. Microsoft Entra ID で新しいセキュリティ グループを作成します。
3. [AD DS へのグループ プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)使用して、このグループを AD DS にプロビジョニングします。
4. Active Directory ユーザーとコンピューターを起動し、生成された新しい AD DS グループが AD DS ドメインに作成されるまで待ちます。 存在する場合は、新しい AD DS グループの識別名、ドメイン、アカウント名、および SID を記録します。

#### 新しいグループを使用するようにアプリケーションを構成する

1. アプリケーションが LDAP 経由で AD DS を使用する場合は、新しい AD DS グループの識別名を使用してアプリケーションを構成します。 アプリケーションで Kerberos 経由で AD DS を使用する場合は、SID または新しい AD DS グループのドメインとアカウント名を使用してアプリケーションを構成します。
2. [アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)を作成します。 前の手順のアプリケーションとセキュリティ グループを、アクセス パッケージのリソースとして追加します。 アクセス パッケージで直接割り当てポリシーを構成します。
3. [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)で、AD DS ベースのアプリへのアクセスを必要とする同期されたユーザーをアクセス パッケージに割り当てます。
4. 新しい AD DS グループが新しいメンバーで更新されるまで待ちます。 Active Directory ユーザーとコンピューターを使用して、正しいユーザーがグループのメンバーとして存在することを確認します。
5. AD DS ドメインの監視で、プロビジョニング エージェント[の承認](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#group-managed-service-accounts)を実行する [gMSA アカウント](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-security-group-management)のみを許可して、新しい AD DS グループのメンバーシップを変更します。

この新しいアクセス パッケージを使用して、AD DS アプリケーションへのアクセスを管理できるようになりました。

### 既存のグループ オプションを構成する

このシナリオ オプションでは、既存のグループの入れ子になったグループ メンバーとして新しい AD DS セキュリティ グループを追加します。 このシナリオは、特定のグループ アカウント名、SID、または識別名にハードコーディングされた依存関係があるアプリケーションのデプロイに適用できます。

そのグループをアプリケーションの既存の AD DS グループに入れ子にすると、次のことが可能になります。

- ガバナンス機能によって割り当てられ、アプリにアクセスして適切な Kerberos チケットを持つ Microsoft Entra ユーザー。 このチケットには、既存のグループの SID が含まれています。 ネスティングは、AD DS グループのネスティング規則によって許可されています。

アプリが LDAP を使用し、入れ子になったグループ メンバーシップに従っている場合、Microsoft Entra ユーザーが自分のメンバーシップの 1 つとして既存のグループを持っていることがわかります。

#### 既存のグループの適格性を判断する

1. Active Directory ユーザーとコンピューターを起動し、アプリケーションで使用されている既存の AD DS グループの識別名、種類、スコープを記録します。
2. 既存のグループが `Domain Admins`、 `Domain Guests`、 `Domain Users`、 `Enterprise Admins`、 `Enterprise Key Admins`、 `Group Policy Creation Owners`、 `Key Admins`、 `Protected Users`、または `Schema Admins`されている場合は、上記のように新しいグループを使用するようにアプリケーションを変更する必要があります。これらのグループはクラウド同期では使用できないためです。
3. グループがグローバル スコープを持つ場合は、ユニバーサル スコープを持つようにグループを変更します。 グローバル グループは、ユニバーサル グループをメンバーとして持つことはできません。

#### アプリケーションとグループを作成する

1. Microsoft Entra 管理センターで、AD DS ベースのアプリケーションを表す Microsoft Entra ID でアプリケーションを作成し、ユーザーの割り当てを要求するようにアプリケーションを構成します。
2. Microsoft Entra ID で新しいセキュリティ グループを作成します。
3. [AD DS へのグループ プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)使用して、このグループを AD DS にプロビジョニングします。
4. Active Directory ユーザーとコンピューターを起動し、生成された新しい AD DS グループが AD DS ドメインに作成されるまで待ちます。 存在する場合は、新しい AD DS グループの識別名、ドメイン、アカウント名、および SID を記録します。

#### 新しいグループを使用するようにアプリケーションを構成する

1. Active Directory ユーザーとコンピューターを使用して、新しい AD DS グループを既存の AD DS グループのメンバーとして追加します。
2. [アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)を作成します。 上記の「アプリケーション **とグループの作成** 」セクションの説明に従って、手順 1 のアプリケーションと手順 3 のセキュリティ グループをアクセス パッケージのリソースとして追加します。 アクセス パッケージで直接割り当てポリシーを構成します。
3. [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)で、AD DS ベースのアプリへのアクセスを必要とする同期されたユーザーを、引き続きアクセスが必要な既存の AD DS グループのユーザー メンバーを含むアクセス パッケージに割り当てます。
4. 新しい AD DS グループが新しいメンバーで更新されるまで待ちます。 Active Directory ユーザーとコンピューターを使用して、正しいユーザーがグループのメンバーとして存在することを確認します。
5. Active Directory ユーザーとコンピューターを使用して、新しい AD DS グループとは別に、既存のメンバーを既存の AD DS グループから削除します。
6. AD DS ドメインの監視で、プロビジョニング エージェント[の承認](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#group-managed-service-accounts)を実行する [gMSA アカウント](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-security-group-management)のみを許可して、新しい AD DS グループのメンバーシップを変更します。

その後、新しいアクセス パッケージを使用して、AD DS アプリケーションへのアクセスを管理できます。

### アプリ アクセスのトラブルシューティング

ドメインに参加しているデバイスにサインインする新しい AD DS グループのユーザーが、新しい AD DS グループ メンバーシップを含まないドメイン コントローラーからのチケットを持っている可能性があります。 Cloud Sync がユーザーを新しい AD DS グループにプロビジョニングする前に、チケットが発行される場合があります。 ユーザーは、アプリケーションへのアクセスにチケットを使用できません。 チケットの有効期限が切れるのを待ち、新しいチケットが発行されるまで待つ必要があります。 または、チケットを消去してサインアウトしてから、ドメインにサインインし直す必要があります。 詳細については、 [klist](https://learn.microsoft.com/ja-jp/windows-server/administration/windows-commands/klist) を参照してください。

### 既存の Microsoft Entra Connect グループ書き戻し「v2」をご利用のお客様

Microsoft Entra Connect グループ ライトバック v2 を使用する場合は、Cloud Sync グループ プロビジョニングを利用する前に、AD DS への Cloud Sync プロビジョニングに移行する必要があります。 詳細については、「 [Microsoft Entra Connect Sync グループ ライトバック V2 を Microsoft Entra Cloud Sync に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-group-writeback)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-ldap"} -->
## オンプレミス LDAP ベースのアプリへのプロビジョニングを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-ldap
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: このドキュメントでは、ユーザーをオンプレミスの LDAP ディレクトリにプロビジョニングするために Microsoft Entra ID を構成する方法について説明します。

このドキュメントでは、ユーザーを Microsoft Entra ID から LDAP ディレクトリに自動的にプロビジョニングおよびプロビジョニング解除するために実行する必要がある手順について説明します。 このドキュメントでは、LDAP ディレクトリの例として AD LDS にユーザーをプロビジョニングする方法を説明しますが、プロビジョニングは、サポート対象 (以降のセクションで説明) のあらゆる LDAP ディレクトリ サーバーに対して行うことができます。 このソリューションを使用した Active Directory Domain Services へのユーザーのプロビジョニングはサポートされていません。

このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)および[オンプレミス アプリケーション プロビジョニング アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)に関する記事を参照してください。 次のビデオでは、オンプレミス プロビジョニングの概要を紹介しています。

### LDAP ディレクトリにユーザーをプロビジョニングするための前提条件

#### オンプレミスの前提条件

- ディレクトリ サーバーを使用してユーザーにクエリを実行するアプリケーション。
- Active Directory Domain Services 以外のターゲット ディレクトリ。ユーザーを作成、更新、削除できます。 たとえば、Active Directory Lightweight Services (AD LDS) などです。 このディレクトリ インスタンスは、ユーザーを Microsoft Entra ID にプロビジョニングするのにも使用されるディレクトリにはしないでください。両方のシナリオが存在すると、Microsoft Entra Connect でループが作成される可能性があるためです。
- プロビジョニング エージェントをホストするための RAM が 3 GB 以上のコンピューター。 このコンピューターには、Windows Server 2016 以降のバージョンの Windows Server、ターゲット ディレクトリへの接続、および login.microsoftonline.com、[その他の Microsoft Online Services](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges)、[Azure](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls?tabs=public-cloud) ドメインへの送信接続が必要です。 1 つの例は、Azure IaaS でホストされているか、またはプロキシの背後にある Windows Server 2016 仮想マシンです。
- .NET Framework 4.7.2 をインストールする必要があります。
- 省略可能: 必須ではありませんが、[Windows Server 用の Microsoft Edge](https://www.microsoft.com/en-us/edge?r=1) をダウンロードし、Internet Explorer の代わりに使用することをおすすめします。

##### サポートされている LDAP ディレクトリ サーバー

コネクタは、さまざまな手法に基づいて LDAP サーバーを検出し、識別します。 コネクタでは、ルート DSE、ベンダー名、バージョンを使用し、スキーマを調べて LDAP サーバーに存在している既知の一意のオブジェクトと属性を見つけます。

- OpenLDAP
- Microsoft Active Directory ライトウェイト ディレクトリ サービス (AD LDS)
- 389 Directory Server
- Apache Directory Server
- IBM Tivoli DS
- Isode Directory
- NetIQ eDirectory
- Novell eDirectory
- Open DJ
- Open DS
- Oracle (以前は Sun ONE) Directory Server Enterprise Edition
- RadiantOne Virtual Directory Server (VDS)

詳細については、「[汎用 LDAP コネクタ リファレンス](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericldap)」を参照してください。

#### クラウドの要件

- Microsoft Entra ID P1 あるいは Premium P2 (または EMS E3 または E5) を使用する、Microsoft Entra テナント。

    この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。
- プロビジョニング エージェントを構成するためのハイブリッド ID 管理者ロールと、Azure portal でプロビジョニングを構成するためのアプリケーション管理者またはクラウド アプリケーション管理者ロール。
- LDAP ディレクトリにプロビジョニングされる Microsoft Entra ユーザーには、ディレクトリ サーバー スキーマで必要となる各ユーザーに固有の属性が既に設定されている必要があります。 たとえば、POSIX ワークロードをサポートするために、ディレクトリ サーバーで各ユーザーがユーザー ID 番号として 10000 から 30000 までの一意の番号を持つ必要がある場合は、ユーザーの既存の属性からその番号を生成するか、Microsoft Entra スキーマを拡張し、LDAP ベースのアプリケーションのスコープ内のユーザーにその属性を設定する必要があります。 追加のディレクトリ拡張機能を作成する方法については、「[Graph 拡張機能](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#directory-azure-ad-extensions)」を参照してください。

#### 推奨事項と制限事項

次の箇条書きは、より多くの推奨事項と制限事項です。

- クラウド同期とオンプレミス アプリのプロビジョニングに同じエージェントを使用することはできません。 クラウド同期には別のエージェントを使用し、もう 1 つはオンプレミス アプリのプロビジョニングに使用をお勧めします。
- 現時点AD LDS、ユーザーをパスワードでプロビジョニングすることはできません。 そのため、AD LDS のパスワード ポリシーを無効にするか、ユーザーを無効状態でプロビジョニングする必要があります。
- 他のディレクトリ サーバーの場合、初期のランダムなパスワードを設定できますが、Microsoft Entra ユーザーのパスワードをディレクトリ サーバーにプロビジョニングすることはできません。
- Microsoft Entra ID から Active Directory Domains Services へのユーザーのプロビジョニングはサポートされていません。
- LDAP から Microsoft Entra ID へのユーザーのプロビジョニングはサポートされていません。
- ディレクトリ サーバーへのグループとユーザー メンバーシップのプロビジョニングはサポートされていません。

### 実行プロファイルの選択

ディレクトリ サーバーと対話するようにコネクタの構成を作成する場合は、まず、コネクタがディレクトリのスキーマを読み取るように構成し、そのスキーマを Microsoft Entra ID のスキーマにマップしてから、実行プロファイルを介してコネクタが継続的に使用するアプローチを構成します。 構成する各実行プロファイルは、ディレクトリ サーバーからデータをインポートまたはエクスポートするためにコネクタが LDAP 要求を生成する方法を指定します。 コネクタを既存のディレクトリ サーバーにデプロイする前に、組織内のディレクトリ サーバー オペレーターと、そのディレクトリ サーバーで行なわれる操作のパターンについて話し合う必要があります。

- 構成が完了すると、プロビジョニング サービスが開始する際、**完全インポート**実行プロファイルで構成された相互作用が自動的に実行されます。 この実行プロファイルでは、コネクタは、LDAP 検索操作を使用して、ディレクトリからのユーザーのすべてのレコードを読み取ります。 この実行プロファイルは、後で Microsoft Entra ID がユーザーに変更を加える必要がある場合に、そのユーザーに対して新しいオブジェクトを作成するのではなく、ディレクトリ内のそのユーザーの既存のオブジェクトを更新することが必要になります。
- 新しいユーザーをアプリケーションに割り当てる場合や既存のユーザーを更新する場合など、Microsoft Entra ID で変更が行われるたびに、プロビジョニング サービスによって、**エクスポート**実行プロファイルで LDAP 相互作用が実行されます。 **エクスポート**実行プロファイルでは、ディレクトリの内容を Microsoft Entra ID と同期させるために、Microsoft Entra ID によって LDAP 要求が発行され、ディレクトリ内のオブジェクトに対する追加、変更、削除、または名前の変更が行われます。
- ディレクトリでサポートされている場合は、必要に応じて**差分インポート**実行プロファイルを構成することもできます。 この実行プロファイルでは、前回の完全インポート、または差分インポート以降に Microsoft Entra ID 以外のディレクトリで行われた変更が Microsoft Entra ID によって読み取られます。 差分インポートをサポートするようにディレクトリが構成されていない可能性があるため、この実行プロファイルはオプションです。 たとえば、組織が OpenLDAP を使用している場合、OpenLDAP はアクセス ログ オーバーレイ機能を有効にしてデプロイされている必要があります。

### Microsoft Entra LDAP コネクタがディレクトリ サーバーとどのように対話するかを決定する

コネクタを既存のディレクトリ サーバーにデプロイする前に、ディレクトリ サーバーとの統合方法について、組織内のディレクトリ サーバー オペレーターと話し合う必要があります。 収集する情報には、ネットワーク情報 (ディレクトリ サーバーへの接続方法、コネクタがディレクトリ サーバーに対して自身を認証する方法、ユーザーをモデル化するためにディレクトリ サーバーで選択されているスキーマ、名前付けコンテキストのベース識別名とディレクトリ階層ルール、ディレクトリ サーバー内のユーザーを Microsoft Entra ID のユーザーに関連付ける方法、およびユーザーが Microsoft Entra ID でスコープから外れる場合の動作) が含まれます。 このコネクタをデプロイするには、ディレクトリ サーバーの構成の変更と Microsoft Entra ID の構成の変更が必要になる場合があります。 運用環境での Microsoft Entra ID とサードパーティのディレクトリ サーバーの統合を伴うデプロイの場合、この統合のための支援、ガイダンス、サポートのために、お客様がディレクトリ サーバー ベンダーまたはデプロイ パートナーと協働することをお勧めします。 この記事では、AD LDS と OpenLDAP の 2 つのディレクトリについて、次のサンプル値を使用します。

| 構成設定 | 値が設定されている場所 | 値の例 |
| --- | --- | --- |
| ディレクトリ サーバーのホスト名 | 構成ウィザードの **[接続]** ページ | `APP3` |
| ディレクトリ サーバーのポート番号 | 構成ウィザードの **[接続]** ページ | 636。LDAP over SSL/TLS (LDAPS) の場合は、ポート 636 を使用します。 `Start TLS` の場合は、ポート 389 を使用します。 |
| ディレクトリ サーバーに自身を識別するためのコネクタのアカウント | 構成ウィザードの **[接続]** ページ | AD LDS の場合は `CN=svcAccountLDAP,CN=ServiceAccounts,CN=App,DC=contoso,DC=lab`、OpenLDAP の場合は `cn=admin,dc=contoso,dc=lab` |
| コネクタがディレクトリ サーバーに対して自身を認証するためのパスワード | 構成ウィザードの **[接続]** ページ |  |
| ディレクトリ サーバー内のユーザーの構造型オブジェクト クラス | 構成ウィザードの **[オブジェクトの種類]** ページ | AD LDS の場合は `User`、OpenLDAP の場合は `inetOrgPerson` |
| ディレクトリ サーバー内のユーザーの補助オブジェクト クラス | Azure portal の **[プロビジョニング]** ページの属性マッピング | POSIX スキーマを指定した OpenLDAP の場合は、`posixAccount` および `shadowAccount` |
| 新しいユーザーに設定する属性 | 構成ウィザードの **[属性の選択]** ページと Azure portal の **[プロビジョニング]** ページの属性マッピング | AD LDS の場合は `msDS-UserAccountDisabled`、`userPrincipalName`、`displayName`、OpenLDAP の場合は `cn`、`gidNumber`、`homeDirectory`、`mail`、`objectClass`、`sn`、`uid`、`uidNumber`、`userPassword` |
| ディレクトリ サーバーに必要な名前付け階層 | Azure portal の **[プロビジョニング]** ページの属性マッピング | 新しく作成したユーザーの DN を、AD LDS の場合は `CN=CloudUsers,CN=App,DC=Contoso,DC=lab`、OpenLDAP の場合は `DC=Contoso,DC=lab` の直下に設定します |
| Microsoft Entra ID とディレクトリ サーバー間でユーザーを関連付けするための属性 | Azure portal の **[プロビジョニング]** ページの属性マッピング | AD LDS の場合、この例は最初は空のディレクトリまたは OpenLDAP (`mail`) 用であるため、構成されていません |
| ユーザーが Microsoft Entra ID でスコープから外れる場合のプロビジョニング解除の動作 | 構成ウィザードの **[プロビジョニング解除]** ページ | ディレクトリ サーバーからユーザーを削除します |

ディレクトリ サーバーのネットワーク アドレスは、ホスト名と TCP ポート番号 (通常はポート 389 または 636) です。 ディレクトリ サーバーが同じ Windows サーバー上のコネクタと併置されている場合またはネットワーク レベルのセキュリティを使用している場合を除き、コネクタからディレクトリ サーバーへのネットワーク接続は SSL または TLS を使用して保護する必要があります。 コネクタでは、ポート 389 でのディレクトリ サーバーへの接続と、セッション内での TLS の有効化に Start TLS を使用することがサポートされています。 このコネクタでは、LDAPS - LDAP over TLS でのポート 636 上のディレクトリ サーバーへの接続もサポートされています。

ディレクトリ サーバーに認証を行うためのコネクタの識別済みのアカウントが、ディレクトリ サーバーで既に構成されている必要があります。 このアカウントは通常、識別名で識別され、関連付けられたパスワードまたはクライアント証明書を持ちます。 接続されたディレクトリ内のオブジェクトに対してインポートおよびエクスポート操作を実行するには、そのディレクトリのアクセス制御モデル内で十分なアクセス許可がコネクタのアカウントになければなりません。 コネクタは、エクスポートするためには**書き込み**アクセス許可を、インポートするためには**読み取り**アクセス許可を備えている必要があります。 アクセス許可は、ターゲット ディレクトリ自体の管理エクスペリエンス内で構成されます。

ディレクトリ スキーマでは、ディレクトリ内の実際のエンティティを表すオブジェクト クラスと属性を指定します。 コネクタでは、`inetOrgPerson` などの構造型オブジェクト クラスで表されるユーザーと、省略可能な追加の補助オブジェクト クラスがサポートされます。 コネクタがディレクトリ サーバーにユーザーをプロビジョニングできるようにするには、Azure portal の構成時に、Microsoft Entra スキーマからすべての必須属性へのマッピングを定義します。 これには、構造型オブジェクト クラスの必須属性、その構造型オブジェクト クラスのスーパークラス、補助オブジェクト クラスの必須属性が含まれます。 さらに、これらのクラスの省略可能な属性の一部へのマッピングも構成する場合もあります。 たとえば、OpenLDAP ディレクトリ サーバーでは、新しいユーザーが次の例のような属性を持つオブジェクトが必要になる場合があります。

```
dn: cn=bsimon,dc=Contoso,dc=lab
objectClass: inetOrgPerson
objectClass: posixAccount
objectClass: shadowAccount
cn: bsimon
gidNumber: 10000
homeDirectory: /home/bsimon
sn: simon
uid: bsimon
uidNumber: 10011
mail: bsimon@contoso.com
userPassword: initial-password
```

ディレクトリ サーバーによって実装されるディレクトリ階層ルールでは、各ユーザーのオブジェクトがどのように相互に関連し、ディレクトリ内の既存のオブジェクトと関連するかを記述します。 ほとんどのデプロイでは、組織はディレクトリ サーバーにフラット階層を設定することを選択しました。そこでは、各ユーザーの各オブジェクトが共通のベース オブジェクトのすぐ下に配置されています。 たとえば、ディレクトリ サーバー内の名前付けコンテキストのベース識別名が`dc=contoso,dc=com` である場合、新しいユーザーは `cn=alice,dc=contoso,dc=com` のような識別名を持つことになります。 ただし、一部の組織には、より複雑なディレクトリ階層がある場合があります。その場合は、コネクタの識別名マッピングを指定するときにそのルールを実装する必要があります。 たとえば、ディレクトリ サーバーでは、ユーザーが部門別の組織単位に属することが想定される場合があるため、新しいユーザーには `cn=alice,ou=London,dc=contoso,dc=com` のような識別名が付けられます。 コネクタでは組織単位の中間オブジェクトが作成されないため、ディレクトリ サーバー ルール階層で想定される中間オブジェクトは、ディレクトリ サーバーに既に存在している必要があります。

次に、Microsoft Entra ユーザーに対応するディレクトリ サーバー内のユーザーが既に存在するかどうかをコネクタが判断する方法に関するルールを定義する必要があります。 すべての LDAP ディレクトリには、ディレクトリ サーバー内のオブジェクトごとに一意の識別名がありますが、多くの場合、Microsoft Entra ID のユーザーには識別名が存在しません。 その代わり、組織のディレクトリ サーバー スキーマに、`mail` や `employeeId` などの異なる属性があり、それが Microsoft Entra ID のユーザーにも存在する場合があります。 その場合、コネクタが新しいユーザーをディレクトリ サーバーにプロビジョニングする際に、コネクタで、その属性の特定の値を持つユーザーがそのディレクトリに既に存在するかどうかを検索し、存在しない場合にのみディレクトリ サーバーに新しいユーザーを作成できます。

既存のユーザーを更新または削除するだけでなく、LDAP ディレクトリに新しいユーザーを作成するシナリオの場合は、そのディレクトリ サーバーを使用するアプリケーションが認証を処理する方法も決定する必要があります。 推奨される方法は、アプリケーションが SAML、OAuth、OpenID Connect などのフェデレーションまたは SSO プロトコルを使用して Microsoft Entra ID に対する認証を行い、属性についてはディレクトリ サーバーにのみ依存することです。 従来、LDAP ディレクトリは、パスワードを確認してユーザーを認証するためにアプリケーションで使用されていましたが、このユース ケースは、多要素認証やユーザーが既に認証されている場合には不可能です。 一部のアプリケーションでは、ユーザーの SSH 公開キーや証明書をディレクトリから照会することができますが、これはユーザーが既にそれらの形式の資格情報を持っている場合に適しています。 ただし、ディレクトリ サーバーに依存するアプリケーションが最新の認証プロトコルやより強力な資格情報をサポートしていない場合、Microsoft Entra ID はユーザーの Microsoft Entra パスワードのプロビジョニングをサポートしないため、ディレクトリに新しいユーザーを作成する際にアプリケーション固有のパスワードを設定する必要があります。

最後に、プロビジョニング解除の動作に同意する必要があります。 コネクタが構成されており、Microsoft Entra ID 内のユーザーとディレクトリ内のユーザーの間にリンクが Microsoft Entra ID で確立されている場合は、ディレクトリに既に存在するユーザーであっても新しいユーザーであっても、Microsoft Entra ID で属性の変更を Microsoft Entra ユーザーからディレクトリにプロビジョニングできます。 Microsoft Entra ID でアプリケーションに割り当てられているユーザーが削除された場合、Microsoft Entra ID は削除操作をディレクトリ サーバーに送信します。 また、ユーザーがアプリケーションを使用できるスコープから外れる場合に、Microsoft Entra ID にディレクトリ サーバー内のオブジェクトを更新させることもできます。 この動作は、ディレクトリ サーバーを使用するアプリケーションによって異なります。OpenLDAP などの多くのディレクトリに、ユーザーのアカウントが無効であることを示す既定の方法がない場合があるためです。

### LDAP ディレクトリを準備する

ディレクトリ サーバーがまだなく、この機能を試したい場合は、[Microsoft Entra ID からプロビジョニングするための Active Directory Lightweight Directory Services の準備](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-prepare-directory)に関するページに、テスト AD LDS 環境を作成する方法が示されています。 既に別のディレクトリ サーバーがデプロイされている場合は、その記事をスキップして、ECMA Connector Host のインストールと構成を続行できます。

### Microsoft Entra Connect プロビジョニング エージェントをインストールして構成する

プロビジョニング エージェントを既にダウンロードし、別のオンプレミス アプリケーション用に構成している場合は、次のセクションに進んでください。

1. Azure portal にサインインします。
2. **[エンタープライズ アプリケーション]** に移動し、**[新規アプリケーション]** を選択します。
3. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付けて、**[作成]** を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング]** ページに移動します。
5. **[Get started](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/作業を開始する)** を選択します。
6. **[プロビジョニング]** ページで、モードを **[自動]** に変更します。

[Image: [自動] を選択しているスクリーンショット。]

1. **[オンプレミス接続]** で、**[ダウンロードしてインストール]** を選択し、**[条項に同意してダウンロード]** を選択します。

[Image: エージェントのダウンロード場所のスクリーンショット。]

1. ポータルをそのままにして、プロビジョニング エージェント インストーラーを開き、サービス使用条件に同意して、**[インストール]** を選択します。
2. Microsoft Entra プロビジョニング エージェント構成ウィザードを待ってから、**[次へ]** を選択します。
3. **[拡張機能を選択]** ステップで、**[オンプレミス アプリケーションのプロビジョニング]** を選択し、**[次へ]** を選択します。
4. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID (および場合によっては組織の ID プロバイダー) に対する認証を行うためのポップアップ ウィンドウを表示します。 Windows Server 上のブラウザーとして Internet Explorer を使用している場合、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト リストに Microsoft Web サイトを追加することが必要な場合があります。
5. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーは、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持っている必要があります。
6. **[Confirm]** を選択して設定を確認します。 インストールが成功したら、**[終了]** を選択し、プロビジョニング エージェント パッケージ インストーラーも閉じます。

### オンプレミスの ECMA アプリを構成する

1. ポータルに戻り、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
2. このブラウザー ウィンドウを開いたままにして、構成ウィザードを使用して構成の次の手順を完了します。

### Microsoft Entra ECMA コネクタ ホストの証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから Microsoft ECMA2Host 構成ウィザードを右クリックし、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. このウィザードを初めて実行する場合は、ECMA Connector Host 構成の開始後に証明書の作成を求められます。 既定のポート **8585** のままにし、**[証明書の生成]** を選択して証明書を生成します。 自動生成された証明書は、信頼ルートの一部として自己署名されます。 SAN はホスト名と一致します。 [Image: 設定の構成を示すスクリーンショット。]
3. **[保存]** を選択します。

注意

新しい証明書を生成することを選択した場合は、証明書の有効期限を記録した後、構成ウィザードに戻り、有効期限が切れる前に証明書を生成し直すようにしてください。

### 汎用 LDAP コネクタを構成する

選択するオプションによっては、ウィザードの一部の画面を使用できない場合があり、情報も多少異なる可能性があります。 この構成例では、AD LDS の場合は **User** オブジェクト クラスを、OpenLDAP の場合は **inetOrgPerson** オブジェクト クラスを使用してユーザーをプロビジョニングします。 次の情報を使用して構成を進めます。

1. コネクタに対する Microsoft Entra ID の認証のために使用するシークレット トークンを生成します。 これは、アプリケーションごとに最小 12 文字で、一意である必要があります。 シークレット ジェネレーターがまだない場合は、次のような PowerShell コマンドを使用して、ランダムな文字列の例を生成できます。

    ```powershell
    -join (((48..90) + (96..122)) * 16 | Get-Random -Count 16 | % {[char]$_})
    ```
2. まだ実行していない場合は、スタート メニューから Microsoft ECMA2Host 構成ウィザードを起動します。
3. **[新しいコネクタ]** を選択します。 [Image: 新しいコネクタの選択を示すスクリーンショット。]
4. **[プロパティ]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。 [Image: プロパティの入力を示すスクリーンショット。]

    | プロパティ | 値 |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。これは、環境内のすべてのコネクタで一意である必要があります。 たとえば、`LDAP` のようにします。 |
    | AutoSync タイマー (分) | 120 |
    | シークレット トークン | ここにシークレット トークンを入力します。 12 文字以上である必要があります。 |
    | 拡張 DLL | 汎用 LDAP コネクタの場合、**Microsoft.IAM.Connector.GenericLdap.dll** を選択します。 |
5. **[接続]** ページで、ECMA Connector Host がディレクトリ サーバーと通信する方法を構成し、いくつかの構成オプションを設定します。 画像の下にある表に指定される値をボックスに入力し、[**次へ**] を選択します。 **[次へ]** を選択すると、コネクタによって、ディレクトリ サーバーへの構成のクエリが実行されます。 [Image: [接続性] ページを示すスクリーンショット。]

    | プロパティ | 内容 |
    | --- | --- |
    | Host | LDAP サーバーが配置されているホスト名。 このサンプルでは、ホスト名の例として `APP3` を使用します。 |
    | Port | TCP ポート番号。 ディレクトリ サーバーが LDAP over SSL 用に構成されている場合は、ポート 636 を使用します。 `Start TLS` の場合、またはネットワーク レベルのセキュリティを使用している場合は、ポート 389 を使用します。 |
    | [接続タイムアウト] | 180 |
    | バインド | このプロパティでは、コネクタがディレクトリ サーバーに対して認証する方法を指定します。 `Basic` の設定、または `SSL` もしくは `TLS` の設定でクライアント証明書が構成されていない場合、コネクタは LDAP シンプル バインドを送信して、識別名とパスワードで認証します。 `SSL` または `TLS` の設定でクライアント証明書を指定すると、コネクタは LDAP SASL `EXTERNAL` バインドを送信して、クライアント証明書による認証を行います。 |
    | [ユーザー名] | ECMA コネクタがディレクトリ サーバーに対して自身を認証する方法。 この AD LDS のサンプルでは、ユーザー名の例は `CN=svcAccount,CN=ServiceAccounts,CN=App,DC=contoso,DC=lab` で、OpenLDAP の場合は `cn=admin,dc=contoso,dc=lab` です。 |
    | Password | ECMA コネクタがディレクトリ サーバーに対して自身を認証するユーザーのパスワード。 |
    | 領域/ドメイン | この設定は、ユーザーの領域/ドメインを指定するために [バインド] オプションとして `Kerberos` を選択した場合にのみ必要です。 |
    | Certificate | このセクションの設定は、[バインド] オプションとして `SSL` または `TLS` を選択した場合にのみ使用されます。 |
    | 属性の別名 | RFC4522 構文を使用してスキーマに属性を定義する場合は、 [属性の別名] テキスト ボックスを使用します。 スキーマの検出中にこれらの属性を検出することはできないため、コネクタがそれらの属性を識別できるようにサポートが必要です。 たとえば、ディレクトリ サーバーが `userCertificate;binary` を発行せず、その属性をプロビジョニングする場合、userCertificate 属性をバイナリ属性として正しく識別するには、属性エイリアス ボックスに `userCertificate;binary` を入力する必要があります。 スキーマにない特別な属性を必要としない場合は、空白のままにすることができます。 |
    | 操作属性を含める | `Include operational attributes in schema` チェック ボックスを選択して、ディレクトリ サーバーで作成された属性も含めます。 これには、オブジェクトの作成日時および最終更新日時などの属性が含まれます。 |
    | 拡張可能な属性を含める | ディレクトリ サーバーで拡張可能オブジェクト (RFC4512/4.3) が使用されている場合は、`Include extensible attributes in schema` チェック ボックスをオンにします。 このオプションを有効にすると、すべてのオブジェクトですべての属性を使用できます。 このオプションを選択すると、スキーマの規模が非常に大きくなります。接続先のディレクトリがこの機能を使用していない限り、このオプションは非選択のままにしておくことをお勧めします。 |
    | 手動アンカーの選択を許可する | オフのままにします。 |

    Note

    接続しようとして問題が発生し、**[グローバル]** ページに進めない場合は、AD LDS または他のディレクトリ サーバーのサービス アカウントが有効であることを確認します。
6. **[グローバル]** ページで、必要に応じて、差分変更ログの識別名およびその他の LDAP 機能を構成します。 このページには、LDAP サーバーから提供された情報があらかじめ設定されています。 表示されている値を確認し、**[次へ]** を選択します。

    | プロパティ | 説明 |
    | --- | --- |
    | サポートされている SASL 機構 | 上部のセクションには、SASL メカニズムの一覧など、サーバー自体によって提供される情報が表示されます。 |
    | サーバー証明書の詳細 | `SSL` または `TLS` が指定されている場合は、ディレクトリ サーバーから返された証明書がウィザードに表示されます。 発行者、件名、拇印が正しいディレクトリ サーバーのものであることを確認します。 |
    | 見つかった必須機能 | コネクタでは、必須のコントロールがルート DSE に存在していることも確認されます。 該当するコントロールが一覧表示されていない場合は、警告が表示されます。 LDAP ディレクトリの中には、一部の機能しかルート DSE に一覧表示しないものがあります。このため、警告が存在する場合でも、コネクタは問題なく動作する場合があります。 |
    | サポートされるコントロール | **[Supported Controls (サポートされるコントロール)]** チェック ボックスでは、特定の操作の動作を制御します。 |
    | 差分インポート | 変更ログ DN は差分変更ログで使用される名前付けコンテキストです (たとえば、 **cn=changelog**)。 差分インポートを実行するには、この値を指定する必要があります。 |
    | パスワード属性 | ディレクトリ サーバーが異なるパスワード属性やパスワード ハッシュをサポートしている場合は、パスワードの変更先を指定することができます。 |
    | パーティション名 | 追加のパーティションの一覧に加えて、自動的に検出されない名前空間を追加することもできます。 たとえばこの設定は、複数のサーバーが 1 つの論理クラスターを構成していて、すべて同時にインポートする必要がある場合などに使用することができます。 Active Directory では 1 つのフォレスト内に複数のドメインを保持することができますが、ドメインはすべて 1 つのスキーマを共有します。それと同じことを、このボックスに追加の名前空間を入力することでシミュレートできます。 それぞれの名前空間は、さまざまなサーバーからインポートすることができ、**[Configure Partitions and Hierarchies (パーティションと階層の構成)]** ページで追加の構成を行うことができます。 |
7. [パーティション] **ページで** 、既定値のままにして [次へ] を **選択します**。
8. **[実行プロファイル]** ページで、**[エクスポート]** チェック ボックスと **[フル インポート]** チェック ボックスの両方がオンになっていることを確認します。 **[次へ]** を選択します。 [Image: [実行プロファイル] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | エクスポート | LDAP ディレクトリ サーバーにデータをエクスポートする実行プロファイル。 この実行プロファイルは必須です。 |
    | フル インポート | 前に指定した LDAP ソースからすべてのデータをインポートする実行プロファイル。 この実行プロファイルは必須です。 |
    | 差分インポート | 最後のフルまたは差分のインポート以降の LDAP からの変更のみをインポートする実行プロファイル。 ディレクトリ サーバーが必要な要件を満たしていることを確認した場合にのみ、この実行プロファイルを有効にします。 詳細については、「[汎用 LDAP コネクタ リファレンス](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericldap)」を参照してください。 |
9. **[エクスポート]** ページで、既定値を変更せずに **[次へ]** をクリックします。
10. **[フル インポート]** ページで、既定値を変更せずに **[次へ]** をクリックします。
11. **[差分インポート]** ページが表示されている場合は、既定値を変更せずに **[次へ]** をクリックします。
12. **[オブジェクトの種類]** ページで、ボックスに入力し、 **[次へ]** を選択します。

    | プロパティ | 説明 |
    | --- | --- |
    | ターゲット オブジェクト | この値は、LDAP ディレクトリ サーバー内のユーザーの構造型オブジェクト クラスです。 たとえば、OpenLDAP の場合は `inetOrgPerson`、AD LDS の場合は `User` です。 このフィールドには補助オブジェクト クラスを指定しないでください。 ディレクトリ サーバーに補助オブジェクト クラスが必要な場合は、Azure portal の属性マッピングを使用して構成されます。 |
    | アンカー | この属性の値は、ターゲット ディレクトリ内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスでは、初期サイクル後、この属性を使用して ECMA コネクタ ホストに対してクエリを実行します。 AD LDS の場合は `ObjectGUID` を使用します。他のディレクトリ サーバーの場合は、次の表を参照してください。 識別名は `-dn-` として選択される場合があることに注意してください。 OpenLDAP スキーマの `uid` 属性など、複数値の属性をアンカーとして使用することはできません。 |
    | クエリ属性 | この属性は、AD LDS がディレクトリ サーバーの場合は `objectGUID`、OpenLDAP の場合は `_distinguishedName` など、Anchor と同じである必要があります。 |
    | DN | ターゲット オブジェクトの distinguishedName。 `-dn-` を保持します。 |
    | 自動生成 | unchecked |

    使用する LDAP サーバーとアンカーの一覧を次の表に示します。

    | ディレクトリ | アンカー |
    | --- | --- |
    | Microsoft AD LDS および AD GC | objectGUID。 `ObjectGUID` をアンカーとして使用するには、エージェント バージョン 1.1.846.0 またはそれ以降を使用している必要があります。 |
    | 389 Directory Server | dn |
    | Apache Directory | dn |
    | IBM Tivoli DS | dn |
    | Isode Directory | dn |
    | Novell/NetIQ eDirectory | GUID |
    | Open DJ/DS | dn |
    | Open LDAP | dn |
    | Oracle ODSEE | dn |
    | RadiantOne VDS | dn |
    | Sun One Directory Server | dn |
13. ECMA ホストでは、ターゲット ディレクトリでサポートされる属性を検出します。 Microsoft Entra ID に公開する属性を選択できます。 プロビジョニングのために、これらの属性を Azure portal で構成できます。 **[属性の選択]** ページで、必須属性として必要な属性、または Microsoft Entra ID からプロビジョニングする属性をすべてドロップダウン リストに 1 つずつ追加します。 [Image: [属性の選択] ページを示すスクリーンショット。]**[属性]** ドロップダウン リストには、ターゲット ディレクトリで検出され、構成ウィザードの以前の **[属性の選択]** ページでは選択 "*されなかった*" 属性が示されます。

    `Treat as single value` チェック ボックスが `objectClass` 属性でオフになっていること、`userPassword` が設定されている場合は、`userPassword` の属性で選択不可かチェックされていることを確認します。

    inetOrgPerson スキーマで OpenLDAP を使用している場合は、次の属性の可視性を構成してください。

    | 属性 | 1 つの値として扱う |
    | --- | --- |
    | cn | Y |
    | mail | Y |
    | objectClass |  |
    | sn | Y |
    | userPassword | Y |

    POSIX スキーマで OpenLDAP を使用している場合は、次の属性の可視性を構成してください。

    | 属性 | 1 つの値として扱う |
    | --- | --- |
    | \_distinguishedName |  |
    | -dn- |  |
    | export\_password |  |
    | cn | Y |
    | gidNumber |  |
    | homeDirectory |  |
    | mail | Y |
    | objectClass |  |
    | sn | Y |
    | uid | Y |
    | uidNumber |  |
    | userPassword | Y |

    関連するすべての属性が追加されたら、**[次へ]** を選択します。
14. **[プロビジョニング解除]** ページでは、ユーザーがアプリケーションの範囲外になったときに Microsoft Entra ID にディレクトリからユーザーを削除させるかどうかを指定することができます。 その場合は、**[フローの無効化]** で **[削除]** を選択し、**[フローの削除]** で **[削除]** を選択します。 `Set attribute value` が選択されている場合は、前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。

注意

**[Set attribute value](Set 属性値)**を使用すると、ブール値のみが許可されることに注意してください。

1. **[完了]** を選択します。

### ECMA2Host サービスが実行されていて、ディレクトリ サーバーから読み込めることを確認する

次の手順に従って、コネクタ ホストが起動し、既存のユーザーがディレクトリ サーバーからコネクタ ホストに読み込まれたことを確認します。

1. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、**[開始]** を選択します。
2. **[実行]** を必要に応じて選択し、ボックスに「**services.msc**」と入力します。
3. **サービス** リストで、**Microsoft ECMA2Host** が確実に存在し、実行中であるようにします。 実行されていない場合は、**[開始]** を選択します。 [Image: サービスが稼働中であることを示すスクリーンショット。]
4. 最近サービスを開始し、ディレクトリ サーバーに多数のユーザー オブジェクトがある場合は、コネクタがディレクトリ サーバーとの接続を確立するまで数分待ってください。
5. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、PowerShell を起動します。
6. ECMA ホストがインストールされたフォルダー (`C:\Program Files\Microsoft ECMA2Host` など) に移動します。
7. サブディレクトリ `Troubleshooting` に変更します。
8. 次の例に示すように、そのディレクトリでスクリプト `TestECMA2HostConnection.ps1` を実行し、引数としてコネクタ名と `ObjectTypePath` 値 `cache` を指定します。 コネクタ ホストが TCP ポート 8585 でリッスンしていない場合は、`-Port` 引数の指定も必要になる場合があります。 メッセージが表示されたら、そのコネクタ用に構成されたシークレット トークンを入力します。

    ```
    PS C:\Program Files\Microsoft ECMA2Host\Troubleshooting> $cout = .\TestECMA2HostConnection.ps1 -ConnectorName LDAP -ObjectTypePath cache; $cout.length -gt 9
    Supply values for the following parameters:
    SecretToken: ************
    ```
9. スクリプトにエラーまたは警告メッセージが表示される場合は、サービスが実行されていることを確認し、コネクタ名とシークレット トークンが構成ウィザードで構成した値と一致していることを確認します。
10. スクリプトに出力 `False` が表示される場合、コネクタは既存のユーザーのソース ディレクトリ サーバー内のエントリを認識していません。 これが新しいディレクトリ サーバーのインストールである場合、この動作は予期されたものであり、次のセクションに進むことができます。
11. ただし、ディレクトリ サーバーに既に 1 人以上のユーザーが含まれているにもかかわらず、スクリプトは `False` を表示している場合、この状態はコネクタがディレクトリ サーバーから読み取れなかったことを示します。 プロビジョニングを試みた場合、Microsoft Entra ID でそのソース ディレクトリ内のユーザーと Microsoft Entra ID のユーザーが正しくマッチングされない可能性があります。 コネクタ ホストが既存のディレクトリ サーバーからのオブジェクトの読み取りを完了するまで数分待ってから、スクリプトを再実行します。 出力が引き続き `False` である場合は、コネクタの構成とディレクトリ サーバーのアクセス許可によってコネクタが既存のユーザーを読み取れるようにしていることを確認します。

### Microsoft Entra ID からコネクタ ホストへの接続をテストする

1. ポータルでアプリケーションのプロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    Note

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. Azure portal にサインインします。
    2. **[エンタープライズ アプリケーション]** の **[On-premises ECMA app](オンプレミス ECMA アプリ)** アプリケーションに移動します。
    3. **[プロビジョニング]** をクリックします。
    4. **[概要]** が表示された場合は、モードを **[自動]** に変更し、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択して 10 分間待ちます。 それ以外の場合は、**[プロビジョニングの編集]** に移動します。
2. **[管理者資格情報]** セクションで、次の URL を入力します。 `connectorName` の部分を ECMA ホスト上のコネクタの名前 (`LDAP` など) に置き換えます。 ECMA ホストの証明機関の証明書を提供した場合は、`localhost` を ECMA ホストがインストールされているサーバーのホスト名に置き換えます。

    | プロパティ | 値 |
    | --- | --- |
    | テナントの URL | https://localhost:8585/ecma2host\_connectorName/scim |
3. コネクタの作成時に定義した**シークレット トークン**の値を入力します。

    Note

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動して、Windows 検索バーで**サービス**を検索し、**Microsoft Entra Connect プロビジョニング エージェント** サービスを見つけたら、サービスを右クリックして再起動します。
4. **[接続のテスト]** をクリックし、1 分待ちます。
5. 接続テストが成功し、指定された資格情報が認可されてプロビジョニングが有効になったことが示されたら、**[保存]** を選択します[Image: エージェントのテストを示すスクリーンショット。]。

### Microsoft Entra スキーマを拡張する (省略可能)

ディレクトリ サーバーで、ユーザーの既定の Microsoft Entra スキーマに含まれていない追加の属性が必要な場合は、プロビジョニング時に、定数から、他の Microsoft Entra 属性から変換された式から、または Microsoft Entra スキーマを拡張することで、これらの属性の値を指定するように構成できます。

ユーザーに OpenLDAP POSIX スキーマの場合の `uidNumber` のような属性があることがディレクトリ サーバーによって要求され、その属性がユーザーの Microsoft Entra スキーマにまだ含まれておらず、ユーザーごとに一意である必要がある場合は、[式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)を介してユーザーの他の属性からその属性を生成するか、[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)を使用してその属性を拡張機能として追加する必要があります。

ユーザーが Active Directory Domain Services で生成され、そのディレクトリに属性がある場合は、Microsoft Entra Connect または Microsoft Entra Connect クラウド同期を使用して、その属性を Active Directory Domain Services から Microsoft Entra ID に同期して、他のシステムへのプロビジョニングに使用できるように構成できます。

ユーザーが Microsoft Entra ID で生成された場合、ユーザーに格納する必要がある新しい属性ごとに、[ディレクトリ拡張機能を定義する](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#define-the-directory-extension)必要があります。 次に、プロビジョニングする予定の [Microsoft Entra ユーザーを更新して](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#update-or-delete-directory-extensions)、各ユーザーにこれらの属性の値を付与します。

### 属性マッピングを構成する

このセクションでは、Microsoft Entra ユーザーの属性と、ECMA ホスト構成ウィザードで以前に選択した属性との間のマッピングを構成します。 後でコネクタがディレクトリ サーバーにオブジェクトを作成すると、Microsoft Entra ユーザーの属性がコネクタを介してディレクトリ サーバーに送信され、その新しいオブジェクトの一部になります。

1. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション]** で、**[オンプレミス ECMA アプリ]** アプリケーションを選んでから、**[プロビジョニング]** ページを選びます。
2. **[プロビジョニングの編集]** を選択します。
3. **[マッピング]** を展開し、**[Microsoft Entra ユーザーのプロビジョニング]** を選びます。 このアプリケーションの属性マッピングを初めて構成した場合、プレースホルダーのマッピングは 1 つだけ存在します。
4. ディレクトリ サーバーのスキーマが Microsoft Entra ID で使用可能であることを確認するには、**[詳細オプションの表示]** チェック ボックスをオンにして、**[ScimOnPremises の属性リストの編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ナビゲーション行で **[属性マッピング]** を選択し、**[ScimOnPremises の属性リストの編集]** をもう一度選択してページを再読み込みします。 属性が一覧表示されたら、このページをキャンセルしてマッピングの一覧に戻ります。
5. ディレクトリ内のすべてのユーザーが一意の識別名を持っている必要があります。 属性マッピングを使用して、コネクタで識別名を構築する方法を指定できます。 **[新しいマッピングの追加]** を選択します。 次の例にある値を使用してマッピングを作成し、式内の識別名を、組織単位またはターゲット ディレクトリ内の他のコンテナーの識別名と一致するように変更します。

    - マッピングの種類: 式
    - 式 (AD LDS へのプロビジョニングの場合): `Join("", "CN=", Word([userPrincipalName], 1, "@"), ",CN=CloudUsers,CN=App,DC=Contoso,DC=lab")`
    - 式 (OpenLDAP へのプロビジョニングの場合): `Join("", "CN=", Word([userPrincipalName], 1, "@"), ",DC=Contoso,DC=lab")`
    - ターゲット属性: `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:-dn-`
    - このマッピングを適用する: オブジェクトの作成時のみ
6. ディレクトリ サーバーで `objectClass` 属性に複数の構造型オブジェクト クラス値または補助オブジェクト クラス値を指定する必要がある場合は、その属性にマッピングを追加します。 AD LDS へのプロビジョニングのこの例では `objectClass` のマッピングは必要ありませんが、他のディレクトリ サーバーや他のスキーマでは必要な場合があります。 `objectClass` のマッピングを追加するには、**[新しいマッピングの追加]** を選択します。 次の例にある値を使用してマッピングを作成し、式のオブジェクト クラス名をターゲット ディレクトリ スキーマの名前と一致するように変更します。

    - マッピングの種類: 式
    - 式 (inetOrgPerson スキーマをプロビジョニングする場合): `Split("inetOrgPerson",",")`
    - 式 (POSIX スキーマをプロビジョニングする場合): `Split("inetOrgPerson,posixAccount,shadowAccount",",")`
    - ターゲット属性: `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:objectClass`
    - このマッピングを適用する: オブジェクトの作成時のみ
7. AD LDS にプロビジョニングしていて、**userPrincipalName** から **PLACEHOLDER** へのマッピングがある場合は、そのマッピングをクリックして編集します。 次の値を使用してマッピングを更新します。

    - マッピングの種類: 直接
    - 基になる属性: `userPrincipalName`
    - ターゲット属性: `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:userPrincipalName`
    - 照合の優先順位: 1
    - このマッピングを適用する: オブジェクトの作成時のみ
8. AD LDS にプロビジョニングする場合は、**isSoftDeleted** のマッピングを追加します。 **[新しいマッピングの追加]** を選択します。 次の値を使用してマッピングを作成します。

    - マッピングの種類: 直接
    - 基になる属性: `isSoftDeleted`
    - ターゲット属性: `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:msDS-UserAccountDisabled`
9. 次の表のディレクトリ サーバーの各マッピングについて、**[新しいマッピングの追加]** を選択し、基になる属性とターゲット属性を指定します。 既存のユーザーを含む既存のディレクトリにプロビジョニングしている場合は、共通する属性のマッピングを編集して、**[この属性を使用してオブジェクトを照合する]** をその属性に設定する必要があります。 属性マッピングの詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#understanding-attribute-mapping-properties)を参照してください。

    AD LDS の場合:

    | マッピングの種類 | ソース属性 | ターゲット属性 |
    | --- | --- | --- |
    | 直接 | `displayName` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:displayName` |

    OpenLDAP の場合:

    | マッピングの種類 | ソース属性 | ターゲット属性 |
    | --- | --- | --- |
    | 直接 | `displayName` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:cn` |
    | 直接 | `surname` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:sn` |
    | 直接 | `userPrincipalName` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:mail` |

    POSIX スキーマを使用する OpenLDAP の場合は、`gidNumber`、`homeDirectory`、`uid` および `uidNumber` の属性も指定する必要があります。 各ユーザーには、一意の `uid` と一意の `uidNumber` が必要です。 通常、`homeDirectory` はユーザーの userID から派生した式で設定されます。 たとえば、ユーザーの `uid` がユーザー プリンシパル名から派生した式で生成される場合、そのユーザーのホーム ディレクトリの値は、ユーザー プリンシパル名から派生した同様の式で生成される可能性があります。 また、ユース ケースによっては、すべてのユーザーを同じグループに含める場合があるため、定数から `gidNumber` を割り当てます。

    | マッピングの種類 | ソース属性 | ターゲット属性 |
    | --- | --- | --- |
    | Expression | `ToLower(Word([userPrincipalName], 1, "@"), )` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:uid` |
    | 直接 | (ディレクトリに固有の属性) | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:uidNumber` |
    | Expression | `Join("/", "/home", ToLower(Word([userPrincipalName], 1, "@"), ))` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:homeDirectory` |
    | 定数 | `10000` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:gidNumber` |
10. AD LDS 以外のディレクトリにプロビジョニングする場合は、ユーザーの初期ランダム パスワードを設定する `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:userPassword` にマッピングを追加します。 AD LDS の場合、**userPassword** 向けのマッピングはありません。
11. **[保存]** を選択します。

### アプリケーションにプロビジョニングされたユーザーが Microsoft Entra ID に必須の属性を持っていることを確認する

LDAP ディレクトリに既存のユーザー アカウントを持つユーザーがいる場合は、Microsoft Entra ユーザー表現に照合に必要な属性があることを確認する必要があります。

LDAP ディレクトリに新しいユーザーを作成する予定がある場合は、それらのユーザーの Microsoft Entra 表現に、ターゲット ディレクトリのユーザー スキーマに必要なソース属性があることを確認する必要があります。

[Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)を使用して、必要な属性のユーザー チェックを自動化できます。

たとえば、プロビジョニングでユーザーに `DisplayName`、`surname`、`extension_656b1c479a814b1789844e76b2f459c3_MyNewProperty` の 3 つの属性が必要だとします。 `Get-MgUser` コマンドレットを使用して各ユーザーを取得し、必要な属性が存在するかどうかは確認できます。 Graph v1.0 `Get-MgUser` コマンドレットでは、返すプロパティの 1 つとして要求で属性が指定されていない限り、既定ではユーザーのディレクトリ拡張属性は返されません。

```powershell
$userPrincipalNames = (
 "alice@contoso.com",
 "bob@contoso.com",
 "carol@contoso.com" )

$requiredBaseAttributes = ("DisplayName","surname")
$requiredExtensionAttributes = ("extension_656b1c479a814b1789844e76b2f459c3_MyNewProperty")

$select = "id"
foreach ($a in $requiredExtensionAttributes) { $select += ","; $select += $a;}
foreach ($a in $requiredBaseAttributes) { $select += ","; $select += $a;}

foreach ($un in $userPrincipalNames) {
   $nu = Get-MgUser -UserId $un -Property $select -ErrorAction Stop
   foreach ($a in $requiredBaseAttributes) { if ($nu.$a -eq $null) { write-output "$un missing $a"} }
   foreach ($a in $requiredExtensionAttributes) { if ($nu.AdditionalProperties.ContainsKey($a) -eq $false) { write-output "$un missing $a" } }
}
```

### LDAP ディレクトリから既存のユーザーを収集する

Active Directory などの多くの LDAP ディレクトリには、ユーザーの一覧を出力するコマンドが含まれています。

1. そのディレクトリ内のユーザーのうちのどれだけが、アプリケーションのユーザーとしてのスコープ内に存在するかを識別します。 この選択は、アプリケーションの構成によって異なります。 一部のアプリケーションでは、LDAP ディレクトリに存在するすべてのユーザーが有効なユーザーとなります。 他のアプリケーションでは、ユーザーが特定の属性を持っているか、またはそのディレクトリ内のグループのメンバーであることが必要な場合もあります。
2. LDAP ディレクトリからそのユーザーのサブセットを取得するコマンドを実行します。 その出力には、Microsoft Entra ID との照合に使用されるユーザーの属性が必ず含まれるようにしてください。 これらの属性の例には、従業員 ID、アカウント名、電子メール アドレスなどがあります。

    たとえば、Windows で AD LDS `csvde` プログラムを使用して次のコマンドを実行すると、ディレクトリ内のすべてのユーザーの `userPrincipalName` 属性を含む CSV ファイルが現在のディレクトリ内に生成されます。

    ```powershell
    $out_filename = ".\users.csv"
    csvde -f $out_filename -l userPrincipalName,cn -r "(objectclass=person)"
    ```
3. 必要に応じて、ユーザーの一覧を含む CSV ファイルを [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)がインストールされているシステムに転送します。
4. これで、アプリケーションから取得されたすべてのユーザーのリストが用意できたので、アプリケーションのデータ ストアのユーザーを Microsoft Entra ID 内のユーザーと照合します。 先に進む前に、[ソース システムとターゲット システム内のユーザーの照合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#matching-users-in-the-source-and-target--systems)に関する情報を確認します。

#### Microsoft Entra ID でユーザーの ID を取得する

このセクションでは、[Microsoft Graph PowerShell](https://www.powershellgallery.com/packages/Microsoft.Graph) コマンドレットを使用して Microsoft Entra ID を操作する方法を示します。

このシナリオのために組織でこれらのコマンドレットを初めて使用する場合は、テナントで Microsoft Graph PowerShell を使用できるように、グローバル管理者ロールである必要があります。 以降の操作では、次のような低い特権のロールを使用できます。

- ユーザー管理者、新しいユーザーの作成が予測される場合。
- アプリケーション管理者または [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)、アプリケーション ロールの割り当ての管理だけを行う場合。

1. PowerShell を開きます。
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
    $msg = Connect-MgGraph -ContextScope Process -Scopes "User.ReadWrite.All,Application.ReadWrite.All,AppRoleAssignment.ReadWrite.All,EntitlementManagement.ReadWrite.All"
    ```
4. このコマンドを初めて使用する場合は、Microsoft Graph コマンド ライン ツールにこれらのアクセス許可を付与することを許可する必要があります。
5. アプリケーションのデータ ストアから取得したユーザーの一覧を、PowerShell セッションに読み込みます。 ユーザーの一覧の形式が CSV ファイルであった場合は、PowerShell コマンドレット `Import-Csv` を使用し、引数として前のセクションのファイルの名前を指定できます。

    たとえば、SAP Cloud Identity Services から取得したファイル名が *Users-exported-from-sap.csv* で、現在のディレクトリにある場合は、次のコマンドを入力します。

    ```powershell
    $filename = ".\Users-exported-from-sap.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```

    別の例として、データベースまたはディレクトリを使用している場合、ファイル名が *users.csv* で、現在のディレクトリにある場合は、次のコマンドを入力します:

    ```powershell
    $filename = ".\users.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```
6. Microsoft Entra ID 内のユーザーの属性に一致する *users.csv* ファイルの列を選択します。

    SAP Cloud Identity Services を使用している場合、既定のマッピングは、Microsoft Entra ID 属性が `userPrincipalName` の SAP SCIM 属性 `userName` です:

    ```powershell
    $db_match_column_name = "userName"
    $azuread_match_attr_name = "userPrincipalName"
    ```

    別の例として、データベースやディレクトリを使用している場合、`EMail` という列の値が Microsoft Entra の属性 `userPrincipalName` と同じ値であるデータベース内のユーザーがいる場合があります:

    ```powershell
    $db_match_column_name = "EMail"
    $azuread_match_attr_name = "userPrincipalName"
    ```
7. Microsoft Entra ID でこれらのユーザーの ID を取得します。

    次の PowerShell スクリプトでは、前に指定された `$dbusers`、`$db_match_column_name`、`$azuread_match_attr_name` の各値を使用します。 これは、Microsoft Entra ID にクエリを実行して、ソース ファイル内の各レコードに一致する値の属性を持つユーザーを見つけます。 ソース SAP Cloud Identity Services、データベース、またはディレクトリから取得したファイルに多数のユーザーが存在する場合、このスクリプトが完了するまでに数分かかる場合があります。 この値を持つ属性が Microsoft Entra ID に存在せず、`contains` などのフィルター式を使用する必要がある場合は、このスクリプトと後の手順 11 のスクリプトを、別のフィルター式を使用するようにカスタマイズする必要があります。

    ```powershell
    $dbu_not_queried_list = @()
    $dbu_not_matched_list = @()
    $dbu_match_ambiguous_list = @()
    $dbu_query_failed_list = @()
    $azuread_match_id_list = @()
    $azuread_not_enabled_list = @()
    $dbu_values = @()
    $dbu_duplicate_list = @()
    
    foreach ($dbu in $dbusers) { 
       if ($null -ne $dbu.$db_match_column_name -and $dbu.$db_match_column_name.Length -gt 0) { 
          $val = $dbu.$db_match_column_name
          $escval = $val -replace "'","''"
          if ($dbu_values -contains $escval) { $dbu_duplicate_list += $dbu; continue } else { $dbu_values += $escval }
          $filter = $azuread_match_attr_name + " eq '" + $escval + "'"
          try {
             $ul = @(Get-MgUser -Filter $filter -All -Property Id,accountEnabled -ErrorAction Stop)
             if ($ul.length -eq 0) { $dbu_not_matched_list += $dbu; } elseif ($ul.length -gt 1) {$dbu_match_ambiguous_list += $dbu } else {
                $id = $ul[0].id; 
                $azuread_match_id_list += $id;
                if ($ul[0].accountEnabled -eq $false) {$azuread_not_enabled_list += $id }
             } 
          } catch { $dbu_query_failed_list += $dbu } 
        } else { $dbu_not_queried_list += $dbu }
    }
    
    ```
8. 前のクエリの結果を表示します。 エラーまたは一致が見つからないために、SAP Cloud Identity Services、データベース、またはディレクトリのいずれかのユーザーが Microsoft Entra ID に配置できなかったかどうかを確認します。

    次の PowerShell スクリプトでは、見つからなかったレコードの数を表示します。

    ```powershell
    $dbu_not_queried_count = $dbu_not_queried_list.Count
    if ($dbu_not_queried_count -ne 0) {
      Write-Error "Unable to query for $dbu_not_queried_count records as rows lacked values for $db_match_column_name."
    }
    $dbu_duplicate_count = $dbu_duplicate_list.Count
    if ($dbu_duplicate_count -ne 0) {
      Write-Error "Unable to locate Microsoft Entra ID users for $dbu_duplicate_count rows as multiple rows have the same value"
    }
    $dbu_not_matched_count = $dbu_not_matched_list.Count
    if ($dbu_not_matched_count -ne 0) {
      Write-Error "Unable to locate $dbu_not_matched_count records in Microsoft Entra ID by querying for $db_match_column_name values in $azuread_match_attr_name."
    }
    $dbu_match_ambiguous_count = $dbu_match_ambiguous_list.Count
    if ($dbu_match_ambiguous_count -ne 0) {
      Write-Error "Unable to locate $dbu_match_ambiguous_count records in Microsoft Entra ID as attribute match ambiguous."
    }
    $dbu_query_failed_count = $dbu_query_failed_list.Count
    if ($dbu_query_failed_count -ne 0) {
      Write-Error "Unable to locate $dbu_query_failed_count records in Microsoft Entra ID as queries returned errors."
    }
    $azuread_not_enabled_count = $azuread_not_enabled_list.Count
    if ($azuread_not_enabled_count -ne 0) {
     Write-Error "$azuread_not_enabled_count users in Microsoft Entra ID are blocked from sign-in."
    }
    if ($dbu_not_queried_count -ne 0 -or $dbu_duplicate_count -ne 0 -or $dbu_not_matched_count -ne 0 -or $dbu_match_ambiguous_count -ne 0 -or $dbu_query_failed_count -ne 0 -or $azuread_not_enabled_count) {
     Write-Output "You will need to resolve those issues before access of all existing users can be reviewed."
    }
    $azuread_match_count = $azuread_match_id_list.Count
    Write-Output "Users corresponding to $azuread_match_count records were located in Microsoft Entra ID." 
    ```
9. このスクリプトは、完了時に、データ ソースのいずれかのレコードが Microsoft Entra ID に見つからなかった場合はエラーを示します。 アプリケーションのデータ ストアのユーザーのレコードのうち Microsoft Entra ID 内のユーザーとして見つからなかったものがある場合は、どのレコードが一致しなかったのかとその理由を調査する必要があります。

    たとえば、ユーザーのメールアドレスと userPrincipalName が Microsoft Entra ID で変更されたのに、アプリケーションのデータ ソースでそれに対応する `mail` プロパティが更新されていない可能性があります。 または、ユーザーは既に組織を離れているが、まだアプリケーションのデータ ソースに存在する可能性があります。 あるいは、アプリケーションのデータ ソースに、Microsoft Entra ID 内のどの特定のユーザーにも対応していないベンダーまたはスーパー管理者アカウントが存在する可能性もあります。
10. Microsoft Entra ID で見つけられなかったか、またはアクティブかつサインインできる状態でなかったユーザーが存在したが、そのアクセスをレビューしたり、その属性を SAP Cloud Identity Services、データベース、またはディレクトリで更新したい場合は、アプリケーションまたは照合ルールを更新するか、そのユーザー用の Microsoft Entra ユーザーを更新する必要があります。 どの変更を行うかの詳細については、「[Microsoft Entra ID のユーザーと一致しなかったアプリケーションのマッピングとユーザー アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-application-unmatched-users)」を参照してください。

    Microsoft Entra ID でユーザーを作成するオプションを選択した場合、次のいずれかを使用してユーザーを一括で作成できます。

    - CSV ファイル (「[Microsoft Entra 管理センターでのユーザーの一括作成](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add)」で説明されています)
    - [New-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/new-mguser?view=graph-powershell-1.0#examples&preserve-view=true) コマンドレット

    これらの新しいユーザーに、Microsoft Entra ID が後でアプリケーション内の既存のユーザーと一致するために必要な属性と、Microsoft Entra ID で必要な属性 (`userPrincipalName`、`mailNickname`、`displayName` を含む) が設定されていることを確認します。 `userPrincipalName` は、ディレクトリ内のすべてのユーザー間で一意である必要があります。

    たとえば、`EMail` という名前の列の値が Microsoft Entra のユーザー プリンシパル名として使用したい値であり、列 `Alias` の値に Microsoft Entra ID のメール ニックネームが含まれ、列 `Full name` の値にユーザーの表示名が含まれているようなユーザーが、データベースに存在する場合があります。

    ```powershell
    $db_display_name_column_name = "Full name"
    $db_user_principal_name_column_name = "Email"
    $db_mail_nickname_column_name = "Alias"
    ```

    そのような場合、このスクリプトを使用して、SAP Cloud Identity Services、データベース、またはディレクトリにある Microsoft Entra ID のユーザと一致しなかったユーザに対して Microsoft Entra ユーザを作成できます。 組織で必要な Microsoft Entra 属性をさらに追加するため、または `$azuread_match_attr_name` が `mailNickname` でも `userPrincipalName` でもない場合にその Microsoft Entra 属性を指定するために、このスクリプトの変更が必要になる場合があることに注意してください。

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
11. 不足しているすべてのユーザーを Microsoft Entra ID に追加したら、手順 7 のスクリプトをもう一度実行します。 次に、手順 8 のスクリプトを実行します。 エラーが報告されていないことを確認します。

    ```powershell
    $dbu_not_queried_list = @()
    $dbu_not_matched_list = @()
    $dbu_match_ambiguous_list = @()
    $dbu_query_failed_list = @()
    $azuread_match_id_list = @()
    $azuread_not_enabled_list = @()
    $dbu_values = @()
    $dbu_duplicate_list = @()
    
    foreach ($dbu in $dbusers) { 
       if ($null -ne $dbu.$db_match_column_name -and $dbu.$db_match_column_name.Length -gt 0) { 
          $val = $dbu.$db_match_column_name
          $escval = $val -replace "'","''"
          if ($dbu_values -contains $escval) { $dbu_duplicate_list += $dbu; continue } else { $dbu_values += $escval }
          $filter = $azuread_match_attr_name + " eq '" + $escval + "'"
          try {
             $ul = @(Get-MgUser -Filter $filter -All -Property Id,accountEnabled -ErrorAction Stop)
             if ($ul.length -eq 0) { $dbu_not_matched_list += $dbu; } elseif ($ul.length -gt 1) {$dbu_match_ambiguous_list += $dbu } else {
                $id = $ul[0].id; 
                $azuread_match_id_list += $id;
                if ($ul[0].accountEnabled -eq $false) {$azuread_not_enabled_list += $id }
             } 
          } catch { $dbu_query_failed_list += $dbu } 
        } else { $dbu_not_queried_list += $dbu }
    }
    
    $dbu_not_queried_count = $dbu_not_queried_list.Count
    if ($dbu_not_queried_count -ne 0) {
      Write-Error "Unable to query for $dbu_not_queried_count records as rows lacked values for $db_match_column_name."
    }
    $dbu_duplicate_count = $dbu_duplicate_list.Count
    if ($dbu_duplicate_count -ne 0) {
      Write-Error "Unable to locate Microsoft Entra ID users for $dbu_duplicate_count rows as multiple rows have the same value"
    }
    $dbu_not_matched_count = $dbu_not_matched_list.Count
    if ($dbu_not_matched_count -ne 0) {
      Write-Error "Unable to locate $dbu_not_matched_count records in Microsoft Entra ID by querying for $db_match_column_name values in $azuread_match_attr_name."
    }
    $dbu_match_ambiguous_count = $dbu_match_ambiguous_list.Count
    if ($dbu_match_ambiguous_count -ne 0) {
      Write-Error "Unable to locate $dbu_match_ambiguous_count records in Microsoft Entra ID as attribute match ambiguous."
    }
    $dbu_query_failed_count = $dbu_query_failed_list.Count
    if ($dbu_query_failed_count -ne 0) {
      Write-Error "Unable to locate $dbu_query_failed_count records in Microsoft Entra ID as queries returned errors."
    }
    $azuread_not_enabled_count = $azuread_not_enabled_list.Count
    if ($azuread_not_enabled_count -ne 0) {
     Write-Warning "$azuread_not_enabled_count users in Microsoft Entra ID are blocked from sign-in."
    }
    if ($dbu_not_queried_count -ne 0 -or $dbu_duplicate_count -ne 0 -or $dbu_not_matched_count -ne 0 -or $dbu_match_ambiguous_count -ne 0 -or $dbu_query_failed_count -ne 0 -or $azuread_not_enabled_count -ne 0) {
     Write-Output "You will need to resolve those issues before access of all existing users can be reviewed."
    }
    $azuread_match_count = $azuread_match_id_list.Count
    Write-Output "Users corresponding to $azuread_match_count records were located in Microsoft Entra ID." 
    ```

### アプリケーションにユーザーを割り当てる

これで Microsoft Entra ECMA コネクタ ホストが Microsoft Entra ID と通信するようになり、属性マッピングが構成されたので、プロビジョニングのスコープに含めるユーザーの構成に進むことができます。

重要

このセクションでは、ハイブリッド ID の管理者ロールを使用してサインインしている場合、一度サインアウトし、少なくともこのセクションのアプリ管理者ロールを持つアカウントでサインインする必要があります。 ハイブリッド ID の管理者の役割には、ユーザーをアプリケーションに割り当てるためのアクセス許可がありません。

LDAP ディレクトリに既存のユーザーが存在する場合は、Microsoft Entra ID でそれらの既存のユーザーに対するアプリケーション ロールの割り当てを作成する必要があります。 `New-MgServicePrincipalAppRoleAssignedTo` を使用してアプリケーション ロールの割り当てを一括作成する方法の詳細については、「[Microsoft Entra ID のアプリケーションの既存のユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)」に関する記事をご覧ください。

それ以外の場合、LDAP ディレクトリが空の場合は、Microsoft Entra ID から必要な属性を持ち、アプリケーションのディレクトリ サーバーにプロビジョニングされるテスト ユーザーを選択します。

1. 選択したユーザーに、ディレクトリ サーバー スキーマの必須属性にマップされるすべてのプロパティがあることを確認します。
2. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
3. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
4. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。
5. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/ユーザーまたはグループの追加)** を選択します。 [Image: ユーザーの追加を示すスクリーンショット。]
6. **[ユーザー]** で **[選択なし]** を選択します。 [Image: [選択なし] を示すスクリーンショット。]
7. 右側からユーザーを選択し、**[選択]** ボタン選択します。[Image: [ユーザーの選択] を示すスクリーンショット。]
8. **[割り当て]** を選択します。 [Image: ユーザーの割り当てを示すスクリーンショット。]

### プロビジョニングをテストする

属性がマップされ、初期ユーザーが割り当てられたので、ユーザーの 1 人でオンデマンド プロビジョニングをテストできます。

1. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、**[開始]** を選択します。
2. **「run」** と入力し、ボックスに **「services.msc」** と入力します。
3. **[サービス]** の一覧で、**Microsoft Entra Connect プロビジョニング エージェント** サービスと **Microsoft ECMA2Host** サービスの両方が実行されていることを確認します。 そうでない場合は、 **[開始]** を選択します。
4. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
5. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
6. 左側で **プロビジョニング** を選択します。
7. **[Provision on demand] (オンデマンドでプロビジョニングする)** を選択します。
8. テスト ユーザーのものを検索し、 **[プロビジョニング]** を選択します。 [Image: オンデマンド プロビジョニングのテストを示すスクリーンショット。]
9. 数秒後に、**ターゲット システムでユーザーが正常に作成されました**というメッセージが表示され、ユーザー属性の一覧が表示されます。 そうではなくエラーが表示される場合は、プロビジョニング エラーのトラブルシューティングに関する記事を参照してください。

### ユーザーのプロビジョニングを開始する

オンデマンド プロビジョニングのテストが成功したら、残りのユーザーを追加します。

1. Azure portal で、アプリケーションを選択します。
2. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。
3. すべてのユーザーがアプリケーション ロールに割り当てられていることを確認します。
4. プロビジョニング構成ページに戻ります。
5. スコープが割り当て済みのユーザーとグループだけに設定されるようにし、プロビジョニングの状態を **[オン]** に変更して **[保存]** を選択します。
6. プロビジョニングが開始されるまで数分待機します。 最大 40 分かかる場合があります。 次のセクションで説明するように、プロビジョニング ジョブが完了した後に、

### プロビジョニング エラーのトラブルシューティング

エラーが表示された場合は、**[プロビジョニング ログの表示]** を選択します。 [状態] が **[失敗]** になっている行をログで確認し、その行をクリックします。

エラー メッセージが**ユーザーを作成できませんでした**である場合は、ディレクトリ スキーマの要件に照らして表示される属性を確認します。

詳細については、**[トラブルシューティングと推奨事項]** タブに移動します。

トラブルシューティングのエラー メッセージに objectClass 値が `invalid per syntax` であると記載されている場合は、`objectClass` 属性にマップされるプロビジョニング属性に、ディレクトリ サーバーに認識されるオブジェクト クラス名のみが含まれていることを確認します。

その他のエラーについては、「[オンプレミス アプリケーションのプロビジョニングのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ecma-troubleshoot)」を参照してください。

このアプリケーションへのプロビジョニングを一時停止する場合は、プロビジョニング構成ページでプロビジョニングの状態を **[オフ]** に変更し、**[保存]** を選択します。 これにより、プロビジョニング サービスは今後実行されなくなります。

### ユーザーが正常にプロビジョニングされたことを確認する

待機した後、ディレクトリ サーバーを確認して、ユーザーがプロビジョニング中であることを確認します。 ディレクトリ サーバーに対して実行するクエリは、ディレクトリ サーバーが提供するコマンドによって異なります。

AD LDS を確認する方法を次の手順で示します。

1. サーバー マネージャーを開き、左で AD LDS を選択します。
2. インスタンスを右クリックしAD LDSポップアップldp.exeを選択します。 [Image: Ldp ツールの場所のスクリーンショット。]
3. ldp.exe の上部にある [**接続**] を選択し、 **Connect**] をクリックします。
4. Enter the following information and click **OK**.
    - サーバー: APP3
    - ポートは 80 にします。
    - [SSL] ボックスにチェックを入れます[Image: ユーザーを確認するための Ldp 接続を示すスクリーンショット。]
5. 上部の [ **接続** ] で [ **バインド**] を選択します。
6. 既定値をそのまま使用し、**[Ok]** をクリックします。
7. 上部にある [**表示**と**ツリー** ] を選択します。
8. [BaseDN] に「 **CN = App, dc = contoso, dc = lab** 」と入力し、[ **OK]**をクリックします。
9. 左側の [DN] を展開し、[ **cn = CloudUsers, cn = App, dc = contoso, dc = lab**] をクリックします。 Microsoft Entra ID からプロビジョニングされたユーザーが表示されます。 [Image: ユーザーの Ldp バインドを示すスクリーンショット。]

次の手順は、OpenLDAP を確認する方法を示しています。

1. OpenLDAP があるシステムで、コマンド シェルを含むターミナル ウィンドウを開きます。
2. コマンド `ldapsearch -D "cn=admin,dc=contoso,dc=lab" -W -s sub -b dc=contoso,dc=lab -LLL (objectclass=inetOrgPerson)` を入力します
3. 結果の LDIF に、Microsoft Entra ID からプロビジョニングされたユーザーが含まれていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-microsoft-identity-manager-to-active-directory"} -->
## Microsoft Identity Manager を使用して Active Directory にプロビジョニングされたオンプレミス ユーザーを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-microsoft-identity-manager-to-active-directory
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事は、MIM を使用して Active Directory にユーザーとグループをプロビジョニングする方法に関するチュートリアルです。

**シナリオ:** Microsoft Identity Manager を使用して Active Directory にプロビジョニングされたオンプレミス ユーザーを管理します。

[Image: MIM プロビジョニングを表す概念図。]

Microsoft Entra ID シナリオの範囲に含まれないユーザーとグループの統合シナリオがある場合は、[Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016) の使用を検討してください。 Microsoft Identity Manager 2016 の[サポート終了日](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016#support-update-for-microsoft-entra-id-p1-or-p2-customers)は 2029 年 1 月 9 日であることに注意してください。

Microsoft Identity Manager を使って Active Directory にユーザーをプロビジョニングする方法について詳しくは、「[AD DS にユーザーをプロビジョニングする方法](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/mim-how-provision-users-adds)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-microsoft-identity-manager-to-entra"} -->
## Microsoft Identity Manager を使用してオンプレミスから Microsoft Entra ID にプロビジョニングされたクラウド ユーザーを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-microsoft-identity-manager-to-entra
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事は、MIM を使用してオンプレミスからクラウドにユーザーとグループをプロビジョニングする方法に関するチュートリアルです。

**シナリオ:** Microsoft Identity Manager を使用してオンプレミスから Microsoft Entra ID にプロビジョニングされたクラウド ユーザーを管理します。

[Image: MIM プロビジョニングを表す概念図。]

Microsoft Entra Connect クラウド同期または Microsoft Entra Connect 同期の対象ではないユーザーとグループに対する統合のシナリオがある場合は、[Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016) と [Microsoft Graph 用の Microsoft Identity Manager コネクタ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016-connector-graph)の使用を検討する必要があります。 このコネクタは、[Microsoft Graph API v1.0](https://learn.microsoft.com/ja-jp/graph/overview) およびベータ版を介して Microsoft Entra ID と通信します。 Microsoft Identity Manager 2016 の[サポート](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016#support-update-for-microsoft-entra-id-p1-or-p2-customers)の終了は、2029 年 1 月 9 日です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-sap"} -->
## NetWeaver AS ABAP 7.0 以降を使用したオンプレミスの SAP ERP Central Component (SAP ECC、旧称 SAP R/3) へのクラウド ユーザーのプロビジョニングを管理します。

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-sap
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: このドキュメントでは、NetWeaver AS ABAP 7.0 以降を使用して、SAP ERP Central Component (SAP ECC、旧称 SAP R/3) にユーザーをプロビジョニングする方法について説明します。

次のドキュメントには、NetWeaver 7.0 以降を使用して、Microsoft Entra ID から SAP ERP Central Component (SAP ECC、旧称 SAP R/3) にユーザーをプロビジョニングする方法を示す構成とチュートリアル情報があります。 SAP R/3 などの他のバージョンを使用している場合でも、[ダウンロード センター](https://www.microsoft.com/download/details.aspx?id=51495)で提供されているガイドを参照として使用して、独自のテンプレートをビルドし、プロビジョニングを構成できます。

次のビデオでは、プロビジョニング エージェントを使用したオンプレミス プロビジョニングの概要について説明します。

### サポートされる機能

- SAP ECC でユーザーを作成します。
- アクセスが不要になったユーザーを SAP ECC で削除します。
- Microsoft Entra ID と SAP ECC の間でユーザー属性の同期を維持します。

### 対象範囲外

- ローカル アクティビティ グループ、ロール、プロファイルなど、他の種類のオブジェクトのプロビジョニングはサポートされていません。 グループ メンバーシップのプロビジョニングは、プロビジョニング エージェントではなく [SAP Cloud Identity Services を使用してユーザーとグループをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial) することで可能です。
- パスワード操作はサポートされません。 パスワード管理が必要な場合は、Microsoft Identity Manager を使用して AD から SAP ECC にパスワードを同期します。

### NetWeaver AS ABAP 7.51 を使用して SAP ECC にプロビジョニングするための前提条件

#### オンプレミスの前提条件

プロビジョニング エージェントを実行するコンピューターには、次のものが必要です。

- 少なくとも 3 GB の RAM。
- Windows Server 2016 以降のバージョンの Windows Server。
- SAP ECC NetWeaver AS ABAP 7.51 を使用したシステムへの接続
- login.microsoftonline.com、[その他の Microsoft Online Services](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges)、[Azure](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls) ドメインへの送信接続。 1 つの例は、Azure IaaS でホストされているか、またはプロキシの背後にある Windows Server 2016 仮想マシンです。
- .NET Framework 4.7.2

#### クラウドの要件

- Microsoft Entra ID P1 あるいは Premium P2 (または EMS E3 または E5) を使用する、Microsoft Entra テナント。

    この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。
- プロビジョニング エージェントを構成するためのハイブリッド ID 管理者ロールと、Microsoft Entra 管理センターでプロビジョニングを構成するためのアプリケーション管理者またはクラウド アプリケーション管理者ロール。
- SAP ECC にプロビジョニングされる Microsoft Entra ユーザーには、SAP ECC で必要な属性が既に設定されている必要があります。

### 1.Microsoft Entra Connect プロビジョニング エージェントをインストールして構成する

プロビジョニング エージェントを既にダウンロードし、別のオンプレミス アプリケーション用に構成している場合は、次のセクションに進んでください。

1. Microsoft Entra 管理センターにサインインします。
2. **[エンタープライズ アプリケーション]** に移動し、**[新規アプリケーション]** を選択します。
3. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付けて、**[作成]** を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング]** ページに移動します。
5. **[Get started](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/作業を開始する)** を選択します。
6. **[プロビジョニング]** ページで、モードを **[自動]** に変更します。

[Image: [自動] を選択しているスクリーンショット。]

1. **[オンプレミス接続]** で、**[ダウンロードしてインストール]** を選択し、**[条項に同意してダウンロード]** を選択します。
2. ポータルから移動してプロビジョニング エージェント インストーラーを実行し、サービス使用条件に同意して、**[インストール]** を選びます。
3. Microsoft Entra プロビジョニング エージェント構成ウィザードを待ってから、**[次へ]** を選択します。
4. **[拡張機能を選択]** ステップで、**[オンプレミス アプリケーションのプロビジョニング]** を選択し、**[次へ]** を選択します。
5. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID (および場合によっては組織の ID プロバイダー) に対する認証を行うためのポップアップ ウィンドウを表示します。 Windows Server でブラウザーとして Internet Explorer を使用している場合は、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト リストに Microsoft Web サイトを追加する必要がある場合があります。
6. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーは、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持っている必要があります。
7. **[Confirm]** を選択して設定を確認します。 インストールが成功したら、**[終了]** を選択し、プロビジョニング エージェント パッケージ インストーラーも閉じます。

### 2.必要な SAP API を公開する

ユーザーの作成、更新、削除に必要な API を SAP ECC NetWeaver 7.51 で公開します。 [SAP NetWeaver AS ABAP 7.51 のデプロイ](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/deploy-sap-netweaver)ドキュメントでは、必要な API を公開する方法について説明します。

### 3.Web サービス コネクタのテンプレートを作成する

MIM の既存の Web サービス コネクタからの移行ではない場合、ECMA ホスト用の Web サービス コネクタのテンプレートを作成する必要があります。 MIM からの Web サービス コネクタのテンプレートが既にある場合は、次のセクションに進みます。

テンプレートを構築するためのリファレンスとして、[ECMA2Host 用 SAP ECC 7.51 Web サービス コネクタ テンプレートの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/sap-template)ドキュメントを使用できます。 [Connectors for Microsoft Identity Manager 2016](https://www.microsoft.com/download/details.aspx?id=51495) では、参照用にテンプレート `sapecc.wsconfig` も提供されています。 運用環境にデプロイする前に、特定の環境のニーズに合わせてテンプレートをカスタマイズする必要があります。 **ServiceName**、**EndpointName**、**OperationName** が正しいことを確認します。

### 4. オンプレミスの ECMA アプリを構成する

1. ポータルの **[オンプレミスの接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
2. このブラウザー ウィンドウを開いたままにして、構成ウィザードを使用して構成の次の手順を完了します。

### 5.Microsoft Entra ECMA コネクタ ホストの証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を右クリックし、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. このウィザードを初めて実行する場合は、ECMA Connector Host 構成の開始後に証明書の作成を求められます。 既定のポート **8585** のままにし、**[証明書の生成]** を選択して証明書を生成します。 自動生成された証明書は、信頼ルートの一部として自己署名されます。 証明書の SAN はホスト名と一致します。

    [Image: 設定の構成を示すスクリーンショット。]
3. **[保存]** を選択します。

### 6.汎用 Web サービス コネクタを構成する

このセクションでは、SAP ECC のコネクタ構成を作成します。

SAP ECC への接続の構成は、ウィザードを使用して行います。 選択するオプションによっては、ウィザードの一部の画面を使用できない場合があり、情報も多少異なる可能性があります。 次の情報を使用して構成を進めます。

#### 6.1 プロビジョニング エージェントを SAP ECC に接続する

Microsoft Entra プロビジョニング エージェントを SAP ECC に接続するには、次の手順に従います。

1. Web サービス コネクタのテンプレート ファイル `sapecc.wsconfig` を `C:\Program Files\Microsoft ECMA2Host\Service\ECMA` フォルダーにコピーします。
2. コネクタに対する Microsoft Entra ID の認証のために使用するシークレット トークンを生成します。 これは、アプリケーションごとに最小 12 文字で、一意である必要があります。
3. まだ実行していない場合は、Windows スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を起動します。
4. **[新しいコネクタ]** を選択します。

    [Image: 新しいコネクタの選択を示すスクリーンショット。]
5. **[プロパティ]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。

    [Image: プロパティの入力を示すスクリーンショット。]

    | プロパティ | 値 |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。これは、環境内のすべてのコネクタで一意である必要があります。 たとえば、SAP インスタンスが 1 つしかない場合は、`SAPECC7` です。 |
    | AutoSync タイマー (分) | 120 |
    | シークレット トークン | このコネクタ用に生成したシークレット トークンを入力します。 キーは 12 文字以上である必要があります。 |
    | 拡張 DLL | Web サービス コネクタで、**[Microsoft.IdentityManagement.MA.WebServices.dll]** を選択します。 |
6. **[接続性]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。

    [Image: [接続性] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | Web サービス プロジェクト | SAP ECC テンプレート名 (`sapecc`) です。 |
    | Host | SAP ECC SOAP エンドポイントのホスト名 (例: `vhcalnplci.dummy.nodomain` |
    | ポート | SAP ECC SOAP エンドポイント ポート (例: `8000` |
7. **[機能]** ページでは、下にある表に指定される値をボックスに入力し、**[次へ]** を選択します。

    | プロパティ | 値 |
    | --- | --- |
    | 識別名の様式 | ジェネリック |
    | エクスポートの種類 | ObjectReplace |
    | データの正規化 | None |
    | オブジェクト確認 | 標準 |
    | インポートの有効化 | オン |
    | デルタ インポートの有効化 | オフ |
    | エクスポートの有効化 | オン |
    | 完全エクスポートの有効化 | オフ |
    | 最初のパスのエクスポート パスワードの有効化 | オン |
    | 最初のエクスポート パスに参照値なし | オフ |
    | オブジェクトの名前変更の有効化 | オフ |
    | 置換として削除/追加 | オフ |

Note

Web サービス コネクタ テンプレート `sapecc.wsconfig` が Web サービス構成ツールで編集用に開かれている場合は、エラーが発生します。

1. **[グローバル]** ページでは、画像の下にある表に指定される値をボックスに入力し、**[次へ]** を選択します。

    | プロパティ | 値 |
    | --- | --- |
    | ClientCredentialType | Basic |
    | ユーザー名 | SAP ECC テンプレートで使用される BAPI に対する呼び出しを行う権限を持つアカウントのユーザー名。 |
    | Password | 指定したユーザー名のパスワード。 |
    | 接続をテスト | テンプレートにテスト接続ワークフローが実装されていない場合はオフ |
2. **[パーティション]** ページで **[次へ]** を選択します。
3. **[プロファイルの実行]** ページで **[エクスポート]** チェックボックスを選択したままにします。 **[フル インポート]** チェックボックスを選択して、 **[次へ]** を選択します。 **エクスポート**実行プロファイルは、ECMA コネクタ ホストが Microsoft Entra ID から SAP ECC に変更を送信し、レコードの挿入、更新、削除を行う必要がある場合に使用されます。 **完全インポート**実行プロファイルは、ECMA コネクタ ホスト サービスの開始時に、SAP ECC の現在のコンテンツを読み取るために使用されます。

    | プロパティ | 値 |
    | --- | --- |
    | エクスポート | データを SAP ECC インスタンスにエクスポートする実行プロファイル。 この実行プロファイルは必須です。 |
    | フル インポート | 前に指定した SAP ECC インスタンスからすべてのデータをインポートする実行プロファイル。 |
    | 差分インポート | 最後のフルまたは差分インポート以降の SAP ECC インスタンスからの変更のみをインポートする実行プロファイル。 |
4. **[オブジェクトの種類]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    - **アンカー**: この属性の値は、ターゲット システム内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスでは、初期サイクル後、この属性を使用して ECMA コネクタ ホストに対してクエリを実行します。 この値は、Web サービス コネクタ テンプレートで定義されます。
    - **DN**: ほとんどの場合、Autogenerated オプションを選択する必要があります。 オフになっている場合は、`CN = anchorValue, Object = objectType` という形式で DN が格納されている Microsoft Entra ID の属性に、DN 属性がマップされるようにします。 アンカーと DN の詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

        | プロパティ | 値 |
        | --- | --- |
        | ターゲット オブジェクト | `User` |
        | アンカー | `userName` |
        | DN | `userName` |
        | 自動生成 | オン |
5. ECMA コネクタ ホストにより、SAP ECC がサポートする属性が検出されます。 次に、検出された属性のうち、Microsoft Entra ID に公開する属性を選択できます。 プロビジョニングのために、これらの属性を Microsoft Entra 管理センターで構成できます。 **[属性の選択]** ページで、ドロップダウン リストのすべての属性を 1 つずつ追加します。 **[属性]** ドロップダウン リストには、SAP ECC で検出され、以前の *[属性の選択]* ページでは選択**されなかった**属性が示されます。 関連するすべての属性が追加されたら、**[次へ]** を選択します。

    [Image: [属性の選択] ページを示すスクリーンショット。]
6. **[プロビジョニング解除]** ページの **[フローを無効化]** で **[削除]** を選択します。 前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。 **[完了]** を選択します。

Note

**[Set attribute value](Set 属性値)**を使用すると、ブール値のみが許可されることに注意してください。

**[プロビジョニング解除]** ページの [フローを無効化] で [なし] を選択します。 **expirationTime** プロパティを使用して、ユーザー アカウントの状態を制御します。 [フローの削除] で、SAP ユーザーを削除しない場合は [なし] を選択し、削除する場合は [削除] を選択します。 **[完了]** を選択します。

### 7. ECMA2Host サービスが実行されていることを確認する

1. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、**[開始]** を選択します。
2. **「run」** と入力し、ボックスに **「services.msc」** と入力します。
3. **サービス** リストで、**Microsoft ECMA2Host** が確実に存在し、実行中であるようにします。 そうでない場合は、 **[開始]** を選択します。

    [Image: サービスが稼働中であることを示すスクリーンショット。]
4. 最近サービスを開始し、SAP ECC に多数のユーザー オブジェクトがある場合は、コネクタが SAP ECC との接続を確立するまで数分待ってください。

### 8.Microsoft Entra 管理センターでアプリケーション接続を構成する

1. アプリケーション プロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    Note

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. Microsoft Entra 管理センターにサインインします。
    2. **[エンタープライズ アプリケーション]** の **[On-premises ECMA app](オンプレミス ECMA アプリ)** アプリケーションに移動します。
    3. **[プロビジョニング]** を選択します。
    4. **[作業の開始]** を選択して、モードを **[自動]** に変更し、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。 それ以外の場合は、**[プロビジョニングの編集]** に移動します。
2. **[管理者資格情報]** セクションで、次の URL を入力します。 `{connectorName}` の部分を ECMA コネクタ ホスト上のコネクタの名前 (**SAPECC7** など) に置き換えます。 コネクタ名は大文字と小文字が区別され、ウィザードで構成したのと同じ大文字と小文字の区別にする必要があります。 `localhost` をマシンのホスト名に置き換えることもできます。

    | プロパティ | 値 |
    | --- | --- |
    | テナントの URL | `https://localhost:8585/ecma2host_SAPECC7/scim` |
3. コネクタの作成時に定義した**シークレット トークン**の値を入力します。

    Note

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動して、Windows 検索バーで**サービス**を検索し、**Microsoft Entra Connect プロビジョニング エージェント サービス**を見つけたら、サービスを右クリックして再起動します。
4. **[接続のテスト]** をクリックし、1 分待ちます。
5. 接続テストが成功し、指定された資格情報が認証されてプロビジョニングが有効になったことが示されたら、**[保存]** を選択します。

    [Image: エージェントのテストを示すスクリーンショット。]

### 9. 属性マッピングを構成する

次に、Microsoft Entra ID のユーザーの表現と、SAP ECC のユーザーの表現との間で、属性をマップします。

Microsoft Entra 管理センターを使用して、Microsoft Entra ユーザーの属性と、ECMA ホスト構成ウィザードで前に選んだ属性との間のマッピングを構成します。

1. Microsoft Entra スキーマに、SAP ECC によって必要とされる属性が含まれていることを確認します。 ユーザーに属性があることが要求され、その属性がユーザーの Microsoft Entra スキーマにまだ含まれていない場合は、[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)を使用して、その属性を拡張機能として追加する必要があります。
2. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション]** で、**[オンプレミスの ECMA アプリ]** アプリケーション、**[プロビジョニング]** ページの順に選択します。
3. **[プロビジョニングの編集]** を選択し、10 秒待ちます。
4. **[マッピング]** を展開し、**[Microsoft Entra ユーザーのプロビジョニング]** を選びます。 このアプリケーションの属性マッピングを初めて構成した場合、プレースホルダーのマッピングは 1 つだけ存在します。

    [Image: ユーザーのプロビジョニングを示すスクリーンショット。]
5. SAP ECC のスキーマが Microsoft Entra ID で使用可能であることを確認するには、**[詳細オプションの表示]** チェック ボックスをオンにし、**[ScimOnPremises の属性リストの編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ページを再読み込みします。 属性が一覧表示されたら、このページをキャンセルしてマッピングの一覧に戻ります。
6. ここで、**userPrincipalName** PLACEHOLDER のマッピングをクリックします。 このマッピングは、オンプレミス プロビジョニングを初めて構成するときに既定で追加されます。

[Image: プレースホルダーのスクリーンショット。]

次のものと一致するように値を変更します。

| マッピングの種類 | ソース属性 | ターゲット属性 |
| --- | --- | --- |
| 直接 | userPrincipalName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:userName |

1. ここで **[新しいマッピングの追加]** を選択し、マッピングごとに次の手順を繰り返します。
2. 次の表のマッピングごとにソース属性とターゲット属性を指定します。

    | Microsoft Entra 属性 | ScimOnPremises 属性 | 照合の優先順位 | このマッピングを適用する |
    | --- | --- | --- | --- |
    | `ToUpper(Word([userPrincipalName], 1, "@"), )` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:userName | 1 | オブジェクトの作成中のみ |
    | `Redact("Pass@w0rd1")` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:export\_password |  | オブジェクトの作成中のみ |
    | `city` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:city |  | 常時 |
    | `companyName` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:company |  | 常時 |
    | `department` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:department |  | 常時 |
    | `mail` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:email |  | 常時 |
    | `Switch([IsSoftDeleted], , "False", "9999-12-31", "True", "1990-01-01")` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:expirationTime |  | 常時 |
    | `givenName` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:firstName |  | 常時 |
    | `surname` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:lastName |  | 常時 |
    | `telephoneNumber` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:telephoneNumber |  | 常時 |
    | `jobTitle` | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:jobTitle |  | 常時 |
3. マッピングをすべて追加したら、**[保存]** を選択します。

### 10。アプリケーションにユーザーを割り当てる

これで Microsoft Entra ECMA コネクタ ホストが Microsoft Entra ID と通信するようになり、属性マッピングが構成されたので、プロビジョニングのスコープに含めるユーザーの構成に進むことができます。

重要

ハイブリッド ID 管理者ロールを使用してサインインした場合は、少なくともこのセクションのアプリケーション管理者ロールを持つアカウントでサインアウトしてサインインする必要があります。 ハイブリッド ID の管理者の役割には、ユーザーをアプリケーションに割り当てるためのアクセス許可がありません。

SAP ECC に既存のユーザーが存在する場合は、それらの既存のユーザー向けのアプリケーション ロールの割り当てを作成する必要があります。 アプリケーション ロールの割り当てを一括で作成する方法について詳しくは、[Microsoft Entra ID でのアプリケーションの既存ユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)に関する記事をご覧ください。

それ以外の場合、アプリケーションの現在のユーザーが存在しなければ、アプリケーションがプロビジョニングされるテスト ユーザーを Microsoft Entra から選択します。

1. 選択したユーザーに、SAP ECC スキーマの必須属性にマップされるすべてのプロパティがあることを確認します。
2. Microsoft Entra 管理センターで **[エンタープライズ アプリケーション]** を選びます。
3. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
4. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。
5. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/ユーザーまたはグループの追加)** を選択します。

    [Image: ユーザーの追加を示すスクリーンショット。]
6. **[ユーザー]** で **[選択なし]** を選択します。

    [Image: [選択なし] を示すスクリーンショット。]
7. 右側からユーザーを選択し、 **[選択]** ボタンを選択します。

    [Image: [ユーザーの選択] を示すスクリーンショット。]
8. **[割り当て]** を選択します。

    [Image: ユーザーの割り当てを示すスクリーンショット。]

### 11. プロビジョニングをテストする

属性がマップされ、ユーザーが割り当てられたので、ユーザーの 1 人でオンデマンド プロビジョニングをテストできます。

1. Microsoft Entra 管理センターで **[エンタープライズ アプリケーション]** を選びます。
2. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
3. 左側で **プロビジョニング** を選択します。
4. **[Provision on demand] (オンデマンドでプロビジョニングする)** を選択します。
5. テスト ユーザーのものを検索し、 **[プロビジョニング]** を選択します。

    [Image: プロビジョニングのテストを示すスクリーンショット。]
6. 数秒後に、**ターゲット システムでユーザーが正常に作成されました**というメッセージが表示され、ユーザー属性の一覧が表示されます。

### 12. ユーザーのプロビジョニングを開始する

1. オンデマンド プロビジョニングが成功したら、プロビジョニングの構成ページに戻ります。 スコープが割り当てられたユーザーとグループのみに確実に設定されているようにし、プロビジョニングを [**オン]** にして、 **[保存]** を選択します。

    [Image: プロビジョニングの開始を示すスクリーンショット。]
2. プロビジョニング サービスが開始されるまで最大 40 分待機します。 プロビジョニング ジョブが完了した後、テストが終了している場合は、次のセクションで説明されているように、プロビジョニングの状態を **[オフ]** に変更し、**[保存]** を選択できます。 これにより、プロビジョニング サービスは今後実行されなくなります。

### プロビジョニング エラーのトラブルシューティング

エラーが表示された場合は、**[プロビジョニング ログの表示]** を選択します。 [状態] が **[失敗**] になっている行をログで探し、その行を選択します。

詳細については、**[トラブルシューティングと推奨事項]** タブに移動します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-sql"} -->
## ECMA コネクタ ホストを使用した、クラウド ユーザーのオンプレミスの SQL ベースのアプリケーションへのプロビジョニングを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-sql
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: このドキュメントでは、ECMA コネクタ ホストを使用して SQL ベースのアプリケーションにプロビジョニングすることで、オンプレミスの使用を管理する方法について説明します

次のドキュメントでは、汎用 SQL コネクタと Extensible Connectivity (ECMA) ホストを SQL Server と共に使用する方法を示す構成およびチュートリアル情報を示します。

このドキュメントでは、ユーザーを Microsoft Entra ID から SQL データベースに自動的にプロビジョニングおよびプロビジョニング解除するために実行する必要がある手順について説明します。

このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)および[オンプレミス アプリケーション プロビジョニング アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)に関する記事を確認してください。

次のビデオでは、オンプレミス プロビジョニングの概要を紹介しています。

### SQL Database にプロビジョニングするための前提条件

#### オンプレミスの前提条件

アプリケーションは SQL データベースに依存します。このデータベースでは、ユーザーのレコードを作成、更新、および削除できます。 プロビジョニング エージェントを実行するコンピューターには、次のものが必要です。

- Windows Server 2016 以降のバージョン。
- ターゲット データベース システムへの接続、および login.microsoftonline.com、[その他の Microsoft Online Services](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges)、[Azure](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls) ドメインへの送信接続。 1 つの例は、Azure IaaS でホストされているか、またはプロキシの背後にある Windows Server 2016 仮想マシンです。
- 少なくとも 3 GB の RAM。
- .NET Framework 4.7.2。
- SQL データベース用の ODBC ドライバー。

アプリケーションのデータベースへの接続の構成は、ウィザードを使用して行います。 選択するオプションによっては、ウィザードの一部の画面を使用できない場合があり、情報も多少異なる可能性があります。 次の情報を使用して構成を進めます。

##### サポートされるデータベース

- Microsoft SQL Server と Azure SQL
- IBM DB2 9.x
- IBM DB2 10.x
- IBM DB2 11.5
- Oracle 10g および 11g
- Oracle 12c および 18c
- MySQL 5.x
- MySQL 8.x
- Postgres

#### クラウドの要件

- Microsoft Entra ID P1 あるいは Premium P2 (または EMS E3 または E5) を使用する、Microsoft Entra テナント。

    この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。
- プロビジョニング エージェントを構成するためのハイブリッド ID 管理者ロールと、Azure portal でプロビジョニングを構成するためのアプリケーション管理者またはクラウド アプリケーション管理者ロール。
- データベースにプロビジョニングされる Microsoft Entra ユーザーには、データベース スキーマで必要とされ、データベース自体によって生成されない属性が既に設定されている必要があります。

### サンプル データベースの準備

この記事では、アプリケーションのリレーショナル データベースと対話するように Microsoft Entra SQL コネクタを構成します。 通常、アプリケーションは、ユーザーごとに 1 行ずつある SQL データベース内のテーブルで、アクセスを管理します。 既にデータベースとアプリケーションがある場合は、次のセクションに進みます。

適切なテーブルを持つデータベースがまだない場合は、デモンストレーション用に、Microsoft Entra による使用を許可したテーブルを作成する必要があります。 SQL Server を使用している場合は、「付録 A」に記載されている SQL スクリプトを実行します。このスクリプトは、1つのテーブル `Employees` を含む、CONTOSO という名前のサンプル データベースを作成します。 これは、ユーザーをプロビジョニングするデータベース テーブルです。

| [テーブル列] | source |
| --- | --- |
| ContosoLogin | Microsoft Entra ユーザー プリンシパル名 |
| FirstName | Microsoft Entra 名 |
| 姓 | Microsoft Entra 姓 |
| Email | Exchange Online 電子メールアドレス |
| InternalGUID | データベース自体によって生成されます |
| AzureID | Microsoft Entra オブジェクト ID |
| textID | Microsoft Entra ID メール ニックネーム |

### Microsoft Entra AD SQL コネクタがデータベースとどのように対話するかを決定する

SQL インスタンスに、データベースのテーブル内のデータを更新する権限を持つユーザー アカウントが存在する必要があります。 SQL データベースが他のユーザーによって管理されている場合は、そのユーザーに連絡して、データベースに対する認証に Microsoft Entra ID で使用するアカウント名とパスワードを入手してください。 SQL インスタンスが別のコンピューターにインストールされている場合は、SQL データベースでエージェント コンピューター上の ODBC ドライバーからの受信接続を許可する必要もあります。

アプリケーションに既存のデータベースが既にある場合、Microsoft Entra ID がそのデータベースと対話する方法を決定する必要があります。これは、テーブルとビューを直接操作する方法、データベース内に既に存在するストアド プロシージャを使用する方法、クエリと更新用に指定する SQL ステートメントを使用する方法です。 この設定は、より複雑なアプリケーションが、そのデータベースに他の補助テーブルを持ち、何千人ものユーザーを持つテーブルのページングを必要としたり、暗号化、ハッシュ化、妥当性チェックなどの追加のデータ処理を実行するストアド プロシージャを Microsoft Entra ID が呼び出す必要があったりする可能性があるからです。

アプリケーションのデータベースと対話するようにコネクタの構成を作成する場合は、まず、コネクタ ホストがデータベースのスキーマを読み取る方法についてのアプローチを構成してから、次に実行プロファイルを介してコネクタが継続的に使用するアプローチを構成します。 各実行プロファイルでは、コネクタが SQL ステートメントを生成する方法を指定します。 実行プロファイルの選択と実行プロファイル内のメソッドは、データベース エンジンがサポートする内容とアプリケーションで必要とされるものによって異なります。

- 構成が完了すると、プロビジョニング サービスが開始する際、**完全インポート**実行プロファイルで構成された相互作用が自動的に実行されます。 この実行プロファイルでは、コネクタは、通常 **SELECT** ステートメントを使用して、アプリケーションのデータベースからのユーザーのすべてのレコードを読み取ります。 この実行プロファイルは、後で＠ユーザーの Microsoft Entra ID に変更を加える必要がある場合に、そのユーザーに対して新しいレコードを作成するのではなく、データベース内のそのユーザーの既存のレコードを更新することを認識するために必要になります。
- 新しいユーザーをアプリケーションに割り当てる場合や既存のユーザーを更新する場合など、Microsoft Entra ID で変更が行われるたびに、プロビジョニング サービスは、SQL データベース相互作用によって構成された**エクスポート**実行プロファイルを実行します。 **エクスポート**実行プロファイルでは、データベースの内容を Microsoft Entra ID と同期させるために、Microsoft Entra ID が SQL ステートメントを発行してデータベースのレコードを挿入、更新、および削除します。
- データベースでサポートされている場合は、必要に応じて **差分インポート**実行プロファイルを構成することもできます。 この実行プロファイルでは、前回の完全インポート、または差分インポート以降に Microsoft Entra ID 以外のデータベースで行われた変更が Microsoft Entra ID によって読み取られます。 この実行プロファイルは、変更を読み取ることができるようにデータベースを構造化する必要があるため、省略可能です。

コネクタの各実行プロファイルの構成では、Microsoft Entra コネクタがテーブルまたはビューに対して独自の SQL ステートメントを生成するか、ストアド プロシージャを呼び出すか、または指定したカスタム SQL クエリを使用するかどうかを指定します。 通常は、コネクタ内のすべての実行プロファイルに同じ方法を使用します。

- 実行プロファイルのテーブルまたはビューメソッドを選択すると、Microsoft Entra コネクタによって、データベース内のテーブルまたはビューと対話するために必要な SQL ステートメント、*SELECT*、*INSERT*、*UPDATE*、および *DELETE* が生成されます。 この方法は、データベースに 1 つのテーブル、または既存の行数が少ない更新可能なビューがある場合に、最も簡単なアプローチです。
- ストアド プロシージャのメソッドを選択した場合、データベースには、ユーザーのページの読み取り、ユーザーの追加、ユーザーの更新、ユーザーの削除という 4 つのストアド プロシージャが必要になります。Microsoft Entra コネクタは、呼び出すストアド プロシージャの名前とパラメーターを使用して構成することになります。 この方法では、SQL データベースにより多くの構成が必要となります。通常は、アプリケーションで、ユーザーに対する変更ごとまたは大規模な結果セットをページングするため追加の処理が必要な場合にのみ必要になります。
- SQL クエリ メソッドを選択した場合は、実行プロファイルの実行時にコネクタが発行する特定の SQL ステートメントを入力します。 コネクタは、SQL ステートメントにコネクタが入力するパラメーター (インポート時の結果セットのページング、エクスポート中に作成される新しいユーザーの属性を設定するなど) を使用して構成します。

この記事では、`Employees`と**フル インポート**の実行プロファイルで、サンプル データベースのテーブル と対話するためにテーブル メソッドを使用する方法について説明します。 ストアド プロシージャまたは SQL クエリ メソッドの構成の詳細情報と具体的な要件については、「[汎用 SQL 構成ガイド](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericsql)」を参照してください。

### アプリケーションのデータベース スキーマで一意の識別子を選択する

ほとんどのアプリケーションは、アプリケーションのユーザーごとに一意の識別子を持ちます。 既存のデータベース テーブルにプロビジョニングする場合は、各ユーザーの値を持つそのテーブルのカラムを特定する必要があり、その値は一意であり、変更されることはありません。 この列は**アンカー**となり、Microsoft Entra ID が既存の行を識別して更新や削除ができるようにするために使用します。 アンカーの詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

アプリケーションのデータベースが既に存在していて、その中にユーザーが存在し、Microsoft Entra ID でそれらのユーザーを最新に保ちたい場合は、アプリケーションのデータベースと Microsoft Entra のスキーマの間で同じ各ユーザーの識別子を用意する必要があります。 たとえば、Microsoft Entra ID でアプリケーションにユーザーを割り当て、そのユーザーが既にそのデータベースに存在する場合、Microsoft Entra ID でそのユーザーに変更を加えると、新しい行が追加されるのではなく、そのユーザーの既存の行が更新されます。 Microsoft Entra ID は、そのユーザーのアプリケーションの内部識別子を保存しない可能性が高いので、データベースの**クエリ**に使用する別の列を選択することになります。 この列の値には、アプリケーションのスコープ内にある各ユーザーの Microsoft Entra ID に存在するユーザー プリンシパル名、メールアドレス、従業員 ID、またはその他の識別子を指定することができます。 アプリケーションが使用するユーザー識別子が、ユーザーの Microsoft Entra 表現に格納されている属性でない場合は、Microsoft Entra ユーザー スキーマを拡張して拡張属性を指定してデータベースからその属性を設定する必要はありません。 [PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/using-extension-attributes-sample) を使用して Microsoft Entra スキーマを拡張し、拡張値を設定できます。

### Microsoft Entra ID 内の属性をデータベース スキーマにマップする

Microsoft Entra ID 内のユーザーとデータベース内のレコードの間にリンクが Microsoft Entra ID で確立されている場合は、データベースに既に存在するユーザーであっても新しいユーザーであっても、Microsoft Entra ID で属性の変更を Microsoft Entra ユーザーからデータベースにプロビジョニングできます。 一意の識別子に加え、データベースを調べて他に必要なプロパティがあるかどうかを特定します。 存在する場合は、データベースにプロビジョニングされるユーザーに、必要なプロパティにマップできる属性があることを確認します。

[プロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#deprovisioning)の動作を構成することもできます。 Microsoft Entra ID でアプリケーションに割り当てられているユーザーが削除された場合、Microsoft Entra ID は削除操作をディレクトリ サーバーに送信します。 また、ユーザーがアプリケーションを使用できるスコープから外れる場合に、Microsoft Entra ID にデータベースを更新させることもできます。 ユーザーがアプリから割り当て解除されたか、Microsoft Entra ID で論理的に削除されたか、またはサインインがブロックされた場合は、Microsoft Entra ID が属性の変更を送信するように構成できます。 既存のデータベース テーブルにプロビジョニングする場合は、そのテーブルの列を **isSoftDeleted** にマップできます。 ユーザーがスコープから外れると、Microsoft Entra ID によってそのユーザーの値が **True** に設定されます。

### 1. ODBC ドライバーをインストールする

プロビジョニング エージェントをインストールする Windows Server には、ターゲット データベース用の ODBC ドライバーが必要です。 SQL Server または Azure SQL データベースに接続する予定がある場合は、[ODBC driver for SQL Server (x64)](https://learn.microsoft.com/ja-jp/sql/connect/odbc/download-odbc-driver-for-sql-server) をダウンロードし、Windows サーバーにインストールする必要があります。 他の SQL データベースについては、ODBC ドライバーのインストール方法について独立系ソフトウェア ベンダーによるガイダンスを参照してください。

### 2. DSN 接続ファイルを作成する

Generic SQL コネクタでは、SQL エンドポイントに接続するためにデータソース名 (DSN) のファイルが必要です。 最初に、ODBC 接続情報を含むファイルを作成する必要があります。

1. サーバー上で ODBC 管理ユーティリティを起動します。 64 ビット版を使用します。

    [Image: ODBC 管理を示すスクリーンショット。]
2. **[ファイル DSN]** タブを選択し、 **[追加]** を選択します。

    [Image: [ファイル DSN] タブを示すスクリーンショット。]
3. SQL Server または Azure SQL を使用している場合は、[**SQL Server Native Client 11.0**] を選択し、[**次へ**] を選択します。 別のデータベースを使用している場合は、その ODBC ドライバーを選択します。

    [Image: ネイティブ クライアントの選択を示すスクリーンショット。]
4. ファイルに **GenericSQL** などの名前を付け、 **[次へ]** を選択します。 [Image: コネクタの名前を示すスクリーンショット。]
5. **[完了]** を選択します。

    [Image: 終了を示すスクリーンショット。]
6. ここで接続を構成します。 次の手順は、使用している ODBC ドライバーによって異なります。 この図では、ドライバーを使って SQL Server に接続していることが想定されています。 SQL Server が別のサーバー コンピューターにある場合は、サーバーの名前を入力します。 次に、 **[次へ]** を選択します。

    [Image: サーバー名の入力を示すスクリーンショット。]
7. この手順を実行しているユーザーがデータベースに接続するためのアクセス許可を持っている場合は、Windows 認証を選択したままにします。 SQL Server 管理者が SQL ローカル アカウントを必要とする場合は、代わりにそれらの資格情報を指定します。 **[次へ]** を選択します。

    [Image: Windows 認証を示すスクリーンショット。]
8. データベースの名前を入力します。このサンプルでは、**CONTOSO** になります。

    [Image: データベース名の入力を示すスクリーンショット。]
9. この画面はすべて既定値のままにし、 **[完了]** を選択します。

    [Image: 完了の選択を示すスクリーンショット。]
10. 問題なく動作することを確認するには、 **[データ ソースのテスト]** を選択します。

    [Image: テスト データ ソースを示すスクリーンショット。]
11. テストの成功を確認します。

    [Image: 成功を示すスクリーンショット。]
12. **[OK]** を 2 回選びます。 ODBC データ ソース アドミニストレーターを閉じます。 既定では、DSN 接続ファイルは **Documents** フォルダーに保存されます。

    注意

    SQL コネクタでは、DSN 接続ファイルが UTF-8 でエンコードされることを想定しています。 ファイルが UTF-8 でエンコードされていない場合は、メモ帳を使用して UTF-8 エンコードでファイルを保存します。

### 3.Microsoft Entra Connect のプロビジョニング エージェントをインストールして構成する

プロビジョニング エージェントを既にダウンロードし、別のオンプレミス アプリケーション用に構成している場合は、次のセクションに進んでください。

1. Azure portal にサインインします。
2. **[エンタープライズ アプリケーション]** に移動し、**[新規アプリケーション]** を選択します。
3. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付けて、**[作成]** を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング]** ページに移動します。
5. **[Get started](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/作業を開始する)** を選択します。
6. **[プロビジョニング]** ページで、モードを **[自動]** に変更します。

[Image: [自動] を選択しているスクリーンショット。]

1. **[オンプレミス接続]** で、**[ダウンロードしてインストール]** を選択し、**[条項に同意してダウンロード]** を選択します。

[Image: エージェントのダウンロード場所のスクリーンショット。]

1. ポータルから移動してプロビジョニング エージェント インストーラーを実行し、サービス使用条件に同意して、**[インストール]** を選びます。
2. Microsoft Entra プロビジョニング エージェント構成ウィザードを待ってから、**[次へ]** を選択します。
3. **[拡張機能を選択]** ステップで、**[オンプレミス アプリケーションのプロビジョニング]** を選択し、**[次へ]** を選択します。
4. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID (および場合によっては組織の ID プロバイダー) に対する認証を行うためのポップアップ ウィンドウを表示します。 Windows Server でブラウザーとして Internet Explorer を使用している場合は、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト リストに Microsoft Web サイトを追加する必要がある場合があります。
5. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーは、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持っている必要があります。
6. **[Confirm]** を選択して設定を確認します。 インストールが成功したら、**[終了]** を選択し、プロビジョニング エージェント パッケージ インストーラーも閉じます。

### 4. オンプレミスの ECMA アプリを構成する

1. ポータルに戻り、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
2. このブラウザー ウィンドウを開いたままにして、構成ウィザードを使用して構成の次の手順を完了します。

### 5.Microsoft Entra ECMA コネクタ ホストの証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を右クリックし、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. このウィザードを初めて実行する場合は、ECMA Connector Host 構成の開始後に証明書の作成を求められます。 既定のポート **8585** のままにし、**[証明書の生成]** を選択して証明書を生成します。 自動生成された証明書は、信頼ルートの一部として自己署名されます。 証明書の SAN はホスト名と一致します。

    [Image: 設定の構成を示すスクリーンショット。]
3. **[保存]** を選択します。

注意

新しい証明書を生成することを選択した場合は、証明書の有効期限を記録した後、構成ウィザードに戻り、有効期限が切れる前に証明書を生成し直すようにしてください。

### 6. 汎用 SQL コネクタを作成する

このセクションでは、データベースのコネクタ構成を作成します。

#### 6.1 SQL 接続を構成する

汎用 SQL コネクタを作成するには、以下の手順に従ってください。

1. コネクタに対する Microsoft Entra ID の認証のために使用するシークレット トークンを生成します。 これは、アプリケーションごとに最小 12 文字で、一意である必要があります。
2. まだ実行していない場合は、Windows スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を起動します。
3. **[新しいコネクタ]** を選択します。
4. [**プロパティ]** ページで、次の表で指定した値をボックスに入力し、[次へ] を選択します。

    | プロパティ | 値 |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。これは、環境内のすべてのコネクタで一意である必要があります。 たとえば、SQL データベースが 1 つしかない場合は、`SQL` とします。 |
    | AutoSync タイマー (分) | 120 |
    | シークレット トークン | このコネクタ用に生成したシークレット トークンを入力します。 キーは 12 文字以上である必要があります。 |
    | 拡張 DLL | Generic SQL コネクタの場合、**Microsoft.IAM.Connector.GenericSql.dll** を選択します。 |
5. [**接続**] ページで、次の表で指定した値をボックスに入力し、[次 ] を選択します。

    | プロパティ | 説明 |
    | --- | --- |
    | DSN ファイル | SQL インスタンスに接続するために使用される、前の手順で作成したデータ ソース名ファイル。 |
    | [ユーザー名] | SQL インスタンスのテーブルを更新する権限を持つアカウントのユーザー名。 ターゲット データベースが SQL Server であり、Windows 認証を使用している場合は、ユーザー名は、スタンドアロン サーバーの場合は hostname\sqladminaccount、ドメイン メンバー サーバーの場合は domain\sqladminaccount の形式にする必要があります。 他のデータベースの場合、ユーザー名はデータベース内のローカル アカウントになります。 |
    | Password | 指定したユーザー名のパスワード。 |
    | DN is Anchor (DN はアンカー) | お使いの環境でこれらの設定が必要であるとわかっている場合を除いて、 **[DN is Anchor](DN はアンカー)** および **[エクスポートの種類: オブジェクトの置換]** チェックボックスを選択しないでください。 |

#### 6.2 データベースからスキーマを取得する

資格情報を提供すると、ECMA コネクタ ホストはデータベースのスキーマを取得する準備が整います。 SQL 接続の構成を続けます。

1. **[スキーマ 1]** ページで、オブジェクトの種類の一覧を指定します。 このサンプルでは、オブジェクトの種類は `User` の 1 つです。 画像の下にある表に指定される値をボックスに入力し、[**次へ**] を選択します。

    [Image: [スキーマ 1] ページを示すスクリーンショット。]

    | プロパティ | 値 |
    | --- | --- |
    | オブジェクトの種類の検出方法 | 固定値 |
    | 固定値のリスト/テーブル/ビュー/SP | ユーザー |
2. **[次へ]** を選択すると、`User` オブジェクトの種類を構成するための次のページが自動的に表示されます。 [**スキーマ 2**] ページで、データベースでのユーザーの表示方法を指定します。 このサンプルでは、`Employees` という名前の 1 つの SQL テーブルです。 画像の下にある表に指定される値をボックスに入力し、[**次へ**] を選択します。

    [Image: [スキーマ 2] ページを示すスクリーンショット。]

    | プロパティ | 値 |
    | --- | --- |
    | ユーザー: 属性の検出 | テーブル |
    | ユーザー: テーブル/ビュー/SP | データベース内のテーブルの名前 (`Employees` など) |

    注意

    エラーが発生した場合は、データベース構成を調べて、**[接続]** ページで指定したユーザーがデータベースのスキーマへの読み取りアクセス権を持っていることを確認します。
3. **[次へ]** を選ぶと次のページが自動的に表示され、前に指定したテーブル (この例では `Employees` テーブル) で、ユーザーの `Anchor` および `DN` として使う列を選択できます。 これらの列には、データベース内で一意の識別子が含まれます。 同じ列でも異なる列でもかまいませんが、このデータベースに既に存在する行で、これらの列の値が一意であることを確認してください。 **[スキーマ 3]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。

    [Image: [スキーマ 3] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | [:User] に [アンカー] を選択します | アンカーとして使うデータベース テーブルの列 (`User:ContosoLogin` など) |
    | ユーザーの DN 属性を選択してください | DN 属性として使うデータベースの列 (`AzureID` など) |
4. **[次へ]** を選択すると、`Employee` テーブルの各列のデータ型と、コネクタがそれらをインポート、またはエクスポートする必要があるかどうかを確認するための次のページが自動的に表示されます。 **[スキーマ 4]** ページでは、既定値のままにし、 **[次へ]** を選択します。

    [Image: [スキーマ 4] ページを示すスクリーンショット。]
5. **[グローバル]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    [Image: [グローバル] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | Delta Strategy (デルタ戦略) | IBM DB2 の場合は、`None` を選びます |
    | ウォーター マーク クエリ | IBM DB2 の場合は、「`SELECT CURRENT TIMESTAMP FROM SYSIBM.SYSDUMMY1;`」と入力します |
    | データ ソースの日付と時刻の形式 | SQL Server の場合は `yyyy-MM-dd HH:mm:ss`、IBM DB2 の場合は `YYYY-MM-DD` |
6. **[パーティション]** ページで **[次へ]** を選択します。

    [Image: [パーティション] ページを示すスクリーンショット。]

#### 6.3 実行プロファイルを構成する

次に、**エクスポート**と**完全インポート**の実行プロファイルを構成します。 **エクスポート**実行プロファイルは、ECMA コネクタ ホストが Azure AD からデータベースに変更を送信し、レコードの挿入、更新、削除を行う必要がある場合に使用されます。 **完全インポート**実行プロファイルは、ECMA コネクタ ホスト サービスの開始時に、データベースの現在のコンテンツを読み取るために使用されます。 このサンプルでは、ECMA コネクタ ホストが必要な SQL ステートメントを生成するように、両方の実行プロファイルでテーブル メソッドを使用します。

SQL 接続の構成を続けます。

1. **[プロファイルの実行]** ページで **[エクスポート]** チェックボックスを選択したままにします。 **[フル インポート]** チェックボックスを選択して、 **[次へ]** を選択します。

    [Image: [実行プロファイル] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | エクスポート | データを SQL にエクスポートする実行プロファイル。 この実行プロファイルは必須です。 |
    | フル インポート | 前に指定した SQL ソースからすべてのデータをインポートする実行プロファイル。 |
    | 差分インポート | 最後のフルまたは差分インポート以降の SQL からの変更のみをインポートする実行プロファイル。 |
2. **[次へ]** を選択すると、**[エクスポート]** 実行プロファイルのメソッドを構成するための次のページが自動的に表示されます。 **[エクスポート]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    [Image: [エクスポート] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | 操作方法 | テーブル |
    | テーブル/ビュー/SP | [スキーマ 2] タブで構成されているのと同じテーブル (`Employees` など) |
3. **[フル インポート]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    [Image: [フル インポート] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | 操作方法 | テーブル |
    | テーブル/ビュー/SP | [スキーマ 2] タブで構成されているのと同じテーブル (`Employees` など) |

#### 6.4 Microsoft Entra ID で属性がどのように表示されるかを構成する

SQL 接続設定の最後の手順で、Microsoft Entra ID で属性がどのように表示されるかを構成します。

1. **[オブジェクトの種類]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    - **アンカー**: この属性の値は、ターゲット データベース内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスでは、初期サイクル後、この属性を使用して ECMA コネクタ ホストに対してクエリを実行します。 このアンカー値は、先に **[スキーマ 3]** ページで構成したアンカー列と同じにする必要があります。
    - **クエリ属性**: この属性はアンカーと同じである必要があります。
    - **DN**: ほとんどの場合、**Autogenerated** オプションを選択する必要があります。 オフになっている場合は、`CN = anchorValue, Object = objectType` という形式で DN が格納されている Microsoft Entra ID の属性に、DN 属性がマップされるようにします。 アンカーと DN の詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

    | プロパティ | 説明 |
    | --- | --- |
    | ターゲット オブジェクト | ユーザー |
    | アンカー | [スキーマ 3] タブで構成されている列 (`ContosoLogin` など) |
    | クエリ属性 | アンカーと同じ列 (`ContosoLogin` など) |
    | DN | [スキーマ 3] タブで構成されているのと同じ列 (`ContosoLogin` など) |
    | 自動生成 | オン |
2. ECMA コネクタ ホストにより、ターゲット データベースがサポートする属性が検出されます。 Microsoft Entra ID に公開する属性を選択できます。 プロビジョニングのために、これらの属性を Azure portal で構成できます。 **[属性の選択]** ページで、ドロップダウン リストのすべての属性を 1 つずつ追加します。

**[属性]** ドロップダウン リストには、ターゲット データベースで検出され、先ほどの *[属性の選択]* ページでは選択 "されなかった" 属性が示されます。 関連するすべての属性が追加されたら、**[次へ]** を選択します。

[Image: 属性ドロップダウン リストのスクリーンショット。]

1. **[プロビジョニング解除]** ページの **[フローを無効化]** で **[削除]** を選択します。 前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。 **[完了]** を選択します。

注意

**[Set attribute value](Set 属性値)**を使用すると、ブール値のみが許可されることに注意してください。

[Image: プロビジョニング解除のページを示すスクリーンショット。]

### 7. ECMA2Host サービスが実行されていることを確認する

1. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、**[開始]** を選択します。
2. **「run」** と入力し、ボックスに **「services.msc」** と入力します。
3. **サービス** リストで、**Microsoft ECMA2Host** が確実に存在し、実行中であるようにします。 そうでない場合は、 **[開始]** を選択します。

    [Image: サービスが稼働中であることを示すスクリーンショット。]

新しいデータベース、または空でユーザーがいないデータベースに接続している場合は、次のセクションに進んでください。 それ以外の場合は、次の手順に従って、コネクタがデータベースから既存のユーザーを識別したことを確認してください。

1. 最近サービスを開始し、データベースに多数のユーザー オブジェクトがある場合は、コネクタがデータベースとの接続を確立するまで数分待ってください。

### 8. Azure portal でアプリケーション接続を構成する

1. アプリケーション プロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    注意

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. Azure portal にサインインします。
    2. **[エンタープライズ アプリケーション]** の **[On-premises ECMA app](オンプレミス ECMA アプリ)** アプリケーションに移動します。
    3. **[プロビジョニング]** を選択します。
    4. **[作業の開始]** が表示された場合は、モードを **[自動]** に変更し、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。 それ以外の場合は、**[プロビジョニングの編集]** に移動します。
2. **[管理者資格情報]** セクションで、次の URL を入力します。 `{connectorName}` の部分を ECMA コネクタ ホスト上のコネクタの名前 (**SQL** など) に置き換えます。 コネクタ名は大文字と小文字が区別され、ウィザードで構成したのと同じ大文字と小文字の区別にする必要があります。 `localhost` をマシンのホスト名に置き換えることもできます。

    | プロパティ | 値 |
    | --- | --- |
    | テナントの URL | `https://localhost:8585/ecma2host_{connectorName}/scim` |
3. コネクタの作成時に定義した**シークレット トークン**の値を入力します。

    注意

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動して、Windows 検索バーで**サービス**を検索し、**Microsoft Entra Connect プロビジョニング エージェント サービス**を見つけたら、サービスを右クリックして再起動します。
4. **[接続のテスト]** をクリックし、1 分待ちます。

    [Image: エージェントの割り当てを示すスクリーンショット。]
5. 接続テストが成功し、指定された資格情報が認証されてプロビジョニングが有効になったことが示されたら、**[保存]** を選択します。

    [Image: エージェントのテストを示すスクリーンショット。]

### 9. 属性マッピングを構成する

次に、Microsoft Entra ID のユーザーの表現と、オンプレミス アプリケーションの SQL データベースのユーザーの表現との間で属性をマップする必要があります。

Azure portal を使って、Microsoft Entra ユーザーの属性と、ECMA ホスト構成ウィザードで前に選んだ属性との間の、マッピングを構成します。

1. Microsoft Entra スキーマに、データベースによって必要とされる属性が含まれていることを確認します。 ユーザーに `uidNumber` などの属性があることがデータベースによって要求され、その属性がユーザーの Microsoft Entra スキーマにまだ含まれていない場合は、[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)を使用して、その属性を拡張機能として追加する必要があります。
2. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション]** で、**[オンプレミス ECMA アプリ]** アプリケーションを選んでから、**[プロビジョニング]** ページを選びます。
3. **[プロビジョニングの編集]** を選択し、10 秒待ちます。
4. **[マッピング]** を展開し、**[Microsoft Entra ID ユーザーをプロビジョニングする]** マッピングを選びます。 このアプリケーションの属性マッピングを初めて構成する場合、これがプレースホルダーとして存在する唯一のマッピングになります。

    [Image: ユーザーのプロビジョニングを示すスクリーンショット。]
5. データベースのスキーマが Microsoft Entra ID で使用可能であることを確認するには、**[詳細オプションの表示]** チェック ボックスをオンにし、**[ScimOnPremises の属性リストの編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ページを再読み込みします。 属性が一覧表示されたら、このページを閉じてマッピング一覧に戻ります。
6. ここで、**userPrincipalName** PLACEHOLDER のマッピングをクリックします。 このマッピングは、オンプレミス プロビジョニングを初めて構成するときに既定で追加されます。

[Image: プレースホルダーのスクリーンショット。] 属性の値を次のように変更します。

| マッピングの種類 | ソース属性 | ターゲット属性 |
| --- | --- | --- |
| 直接 | userPrincipalName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:ContosoLogin |

[Image: 値の変更のスクリーンショット。]

1. ここで **[新しいマッピングの追加]** を選択し、マッピングごとに次の手順を繰り返します。

    [Image: 新しいマッピングの追加を示すスクリーンショット。]
2. 次の表のマッピングごとにソース属性とターゲット属性を指定します。

    [Image: マッピングの保存を示すスクリーンショット。]

    | マッピングの種類 | ソース属性 | ターゲット属性 |
    | --- | --- | --- |
    | 直接 | userPrincipalName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:ContosoLogin |
    | 直接 | objectId | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:AzureID |
    | 直接 | メール | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:Email |
    | 直接 | givenName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:FirstName |
    | 直接 | surname | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:LastName |
    | 直接 | mailNickname | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:textID |
3. マッピングをすべて追加したら、**[保存]** を選択します。

### 10. アプリケーションにユーザーを割り当てる

これで Microsoft Entra ECMA コネクタ ホストが Microsoft Entra ID と通信するようになり、属性マッピングが構成されたので、プロビジョニングのスコープに含めるユーザーの構成に進むことができます。

重要

ハイブリッド ID 管理者ロールを使用してサインインした場合は、少なくともこのセクションのアプリケーション管理者ロールを持つアカウントでサインアウトしてサインインする必要があります。 ハイブリッド ID の管理者の役割には、ユーザーをアプリケーションに割り当てるためのアクセス許可がありません。

SQL データベースに既存のユーザーが存在する場合は、それらの既存のユーザー向けのアプリケーション ロールの割り当てを作成する必要があります。 アプリケーション ロールの割り当てを一括で作成する方法について詳しくは、[Microsoft Entra ID でのアプリケーションの既存ユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)に関する記事をご覧ください。

それ以外の場合、アプリケーションの現在のユーザーが存在しなければ、アプリケーションがプロビジョニングされるテスト ユーザーを Microsoft Entra から選択します。

1. データベース スキーマの必須属性にマップされるすべてのプロパティをユーザーが持っていることを確認します。
2. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
3. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
4. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。
5. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/ユーザーまたはグループの追加)** を選択します。

    [Image: ユーザーの追加を示すスクリーンショット。]
6. **[ユーザー]** で **[選択なし]** を選択します。

    [Image: [選択なし] を示すスクリーンショット。]
7. 右側からユーザーを選択し、 **[選択]** ボタンを選択します。

    [Image: [ユーザーの選択] を示すスクリーンショット。]
8. **[割り当て]** を選択します。

    [Image: ユーザーの割り当てを示すスクリーンショット。]

### 11. プロビジョニングをテストする

属性がマップされ、ユーザーが割り当てられたので、ユーザーの 1 人でオンデマンド プロビジョニングをテストできます。

1. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
2. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
3. 左側で **プロビジョニング** を選択します。
4. **[Provision on demand] (オンデマンドでプロビジョニングする)** を選択します。
5. テスト ユーザーのものを検索し、 **[プロビジョニング]** を選択します。

    [Image: プロビジョニングのテストを示すスクリーンショット。]
6. 数秒後に、**ターゲット システムでユーザーが正常に作成されました**というメッセージが表示され、ユーザー属性の一覧が表示されます。

### 12. ユーザーのプロビジョニングを開始する

1. オンデマンド プロビジョニングが成功したら、プロビジョニングの構成ページに戻ります。 スコープが割り当てられたユーザーとグループのみに確実に設定されているようにし、プロビジョニングを [**オン]** にして、 **[保存]** を選択します。

    [Image: プロビジョニングの開始を示すスクリーンショット。]
2. プロビジョニングが開始されるまで数分待機します。 最大 40 分かかる場合があります。 プロビジョニング ジョブが完了した後、テストが終了している場合は、次のセクションで説明されているように、プロビジョニングの状態を **[オフ]** に変更し、**[保存]** を選択できます。 これにより、プロビジョニング サービスは今後実行されなくなります。

### プロビジョニング エラーのトラブルシューティング

エラーが表示された場合は、**[プロビジョニング ログの表示]** を選択します。 [状態] が **[失敗]** になっている行をログで確認し、その行を選択します。

エラー メッセージが**ユーザーを作成できませんでした**である場合は、データベース スキーマの要件に照らして表示される属性を確認します。

詳細については、**[トラブルシューティングと推奨事項]** タブに移動します。ODBC ドライバーがメッセージを返した場合は、ここに表示される場合があります。 たとえば、メッセージ `ERROR [23000] [Microsoft][ODBC SQL Server Driver][SQL Server]Cannot insert the value NULL into column 'FirstName', table 'CONTOSO.dbo.Employees'; column does not allow nulls.` は ODBC ドライバーのエラーです。 この場合、`column does not allow nulls` は、データベース内の `FirstName` 列は必須ですが、プロビジョニングされるユーザーに `givenName` 属性がないため、ユーザーをプロビジョニングできなかったことを示している可能性があります。

### ユーザーが正常にプロビジョニングされたことを確認する

待機した後、SQL データベースを確認して、ユーザーがプロビジョニングされているのを確認します。

[Image: ユーザーがプロビジョニングされていることを確認するスクリーンショット。]

### Azure BLOB ストレージ アカウントにデータを取得/アップロードする方法については、

SQL Server を使用している場合は、次の SQL スクリプトを使用して、サンプル データベースを作成できます。

```SQL
---Creating the Database---------
Create Database CONTOSO
Go
-------Using the Database-----------
Use [CONTOSO]
Go
-------------------------------------

/****** Object:  Table [dbo].[Employees]    Script Date: 1/6/2020 7:18:19 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Employees]([ContosoLogin] [nvarchar](128) NULL,[FirstName] [nvarchar](50) NOT NULL,[LastName] [nvarchar](50) NOT NULL,[Email] [nvarchar](128) NULL,[InternalGUID] [uniqueidentifier] NULL,[AzureID] [uniqueidentifier] NULL,[textID] [nvarchar](128) NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Employees] ADD  CONSTRAINT [DF_Employees_InternalGUID]  DEFAULT (newid()) FOR [InternalGUID]
GO

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-workday-to-active-directory"} -->
## Workday からプロビジョニングされた、Workday での管理対象であるオンプレミスの Active Directory ユーザーを管理します。

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-workday-to-active-directory
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事は、Workday を使用してユーザーとグループを AD にプロビジョニングする方法に関するチュートリアルです。

**シナリオ：** シナリオ: Workday からプロビジョニングされ、Workday で管理されているオンプレミスの Active Directory ユーザーを管理します。

### 概要

[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)は、ユーザー アカウントをプロビジョニングするために [Workday Human Resources API](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html) と統合されます。 Microsoft Entra のユーザー プロビジョニング サービスでサポートされている Workday ユーザー プロビジョニング ワークフローは、次の人事管理および ID ライフサイクル管理シナリオを自動化します。

- **新しい従業員の雇用** - 新しい従業員が Workday に追加されると、Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)にユーザー アカウントが自動的に作成され、IT が管理する連絡先情報が Workday に書き戻されます。
- **従業員属性とプロファイルの更新** - Workday で従業員レコード (名前、タイトル、マネージャーなど) が更新されると、ユーザー アカウントは Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされるその他の SaaS アプリケーションで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)自動的に更新されます。
- **従業員の退職** - Workday で従業員が退職すると、Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされているその他の SaaS アプリケーションで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)、ユーザー アカウントが自動的に無効になります。
- **従業員の再雇用** - Workday で従業員が再雇用されると、古いアカウントを Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされるその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に自動的に再アクティブ化または再プロビジョニングできます (ユーザー設定に応じて)。

#### 新着情報

このセクションでは、最新の Workday 統合の機能強化について説明します。 包括的な更新プログラム、計画された変更、アーカイブの一覧については、「[Microsoft Entra ID の新機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)」を参照してください。

- **2020 年 10 月 - Workday のオンデマンド プロビジョニングが有効になりました。**[オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用すると、Workday で特定のユーザー プロファイルのエンド ツー エンド プロビジョニングをテストして、属性マッピングと式ロジックを確認できるようになりました。
- **2020 年 5 月 - Workday に電話番号を書き戻す機能:** メールとユーザー名に加えて、Microsoft Entra ID から Workday に職場の電話番号と携帯電話番号を書き戻すようになりました。 詳細については、 [ライトバック アプリのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial)を参照してください。
- **2020 年 4 月 - Workday Web Services (WWS) API の最新バージョンのサポート:** Workday では、3 月と 9 月に年 2 回、ビジネス目標の達成と従業員の需要の変化に役立つ機能豊富な更新プログラムを提供しています。 Workday が提供する新機能を活用していただくため、使用する WWS API バージョンを接続 URL で直接指定できます。 Workday API バージョンを指定する方法の詳細については、 Workday 接続の構成に関するセクションを参照してください。

#### このユーザー プロビジョニング ソリューションは誰にとって最適ですか？

この Workday ユーザー プロビジョニング ソリューションは、次のお客様に最適です。

- Workday ユーザー プロビジョニング用に事前に構築されたクラウドベースのソリューションを求めている組織
- Workday から Active Directory、または Microsoft Entra ID への直接ユーザー プロビジョニングを必要とする組織
- Workday HCM モジュールから取得したデータを使用してユーザーをプロビジョニングする必要がある組織 ( [Get_Workers](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html)を参照)
- Workday HCM モジュールで検出された変更のみに基づき、ユーザーを追加、移動、削除する必要がある組織は、1つ以上の Active Directory フォレスト、ドメイン、OUに同期を行います。([Get_Workers](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html)を参照)
- 電子メールに Microsoft 365 を使用している組織

### ソリューションのアーキテクチャ

このセクションでは、一般的なハイブリッド環境に向けた、エンド ツー エンドのユーザー プロビジョニング ソリューションのアーキテクチャについて説明します。 2 つの関連するフローがあります。

- **権限のある HR データ フロー – Workday からオンプレミス Active Directory へ:** このフローでは、新規採用、転送、退職などの worker イベントが最初にクラウド Workday HR テナントで発生し、次にイベント データが Microsoft Entra ID とプロビジョニング エージェントを介してオンプレミスの Active Directory に流れます。 イベントによっては、AD での作成/更新/有効化/無効化の操作に至る可能性があります。
- **ライトバック フロー – オンプレミスの Active Directory から Workday へ:** Active Directory でアカウントの作成が完了すると、Microsoft Entra Connect を介して Microsoft Entra ID と同期され、メール、ユーザー名、電話番号などの情報を Workday に書き戻すことができます。

[Image: 概要の概念図。]

#### エンド ツー エンドのユーザー データ フロー

1. 人事チームは、Workday HCM で社員のトランザクション (参加者/異動者/休暇者または新規雇用/移動/退職) を実行します
2. Microsoft Entra プロビジョニング サービスは、Workday HR からの、スケジュールされた ID の同期を実行し、オンプレミスの Active Directory との同期のために処理する必要がある変更を識別します。
3. Microsoft Entra プロビジョニング サービスは、AD アカウントの作成/更新/有効化/無効化の操作を含む要求ペイロードを使用して、オンプレミスの Microsoft Entra Connect プロビジョニング エージェントを呼び出します。
4. Microsoft Entra Connect プロビジョニング エージェントは、サービス アカウントを使用して AD アカウントのデータを追加/更新します。
5. Microsoft Entra Connect (AD の同期エンジン) は、デルタ同期を実行して AD 内の更新をプルします。
6. Active Directory の更新は、Microsoft Entra ID と同期されます。
7. [Workday Writeback](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial) アプリが構成されている場合、電子メール、ユーザー名、電話番号などの属性が Workday に書き戻されます。

### 展開計画の策定

Workday から Active Directory へのユーザー プロビジョニングを構成するには、次のようなさまざまな側面をカバーする膨大な計画が必要です。

- Microsoft Entra Connect プロビジョニング エージェントの設定
- デプロイする Workday から AD へのユーザー プロビジョニング アプリの数
- 一致する正しい識別子、属性マッピング、変換、スコープ フィルターの選択

包括的なガイドラインと推奨されるベスト プラクティスについては、 [クラウド人事デプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision) を参照してください。

### Workday の統合システム ユーザーの構成

すべての Workday プロビジョニング コネクタに共通する要件は、Workday Human Resources API に接続するために、Workday 統合システム ユーザーの資格情報を必要とすることです。 このセクションでは、Workday で統合システムのユーザーを作成する方法について説明します。次のセクションがあります。

- 統合システム・ユーザーの作成
- 統合セキュリティ グループの作成
- ドメイン セキュリティ ポリシーのアクセス許可の構成
- ビジネス プロセス セキュリティ ポリシーのアクセス許可の構成
- セキュリティ ポリシーの変更のアクティブ化

注

この手順を省略し、代わりに Workday 管理者アカウントをシステム統合アカウントとして使用することもできます。 これはデモでは問題なく動作するかもしれませんが、本番環境での展開にはお勧めできません。

#### 統合システム ユーザーの作成

**統合システム ユーザーを作成するには:**

1. 管理者アカウントを使用して Workday テナントにサインインします。 **Workday アプリケーション**で、検索ボックスに「ユーザーの作成」と入力し、[**統合システム ユーザーの作成**] をクリックします。

[Image: ユーザーの作成のスクリーンショット。]
2. 新しい **統合システム・ユーザー** のユーザー名とパスワードを指定して、「統合システム・ユーザーの作成」タスクを完了します。

    - [ **次のサインイン時に新しいパスワードを要求する** ] オプションはオフのままにします。このユーザーはプログラムでログオンしているためです。
    - ユーザーのセッションが途中でタイムアウトするのを防ぐ、セッション **タイムアウト分** の既定値は 0 のままにします。
    - 統合システムのパスワードを持つユーザーが Workday にログインできないようにするセキュリティレイヤーが追加されているため、[ **UI セッションを許可しない** ] オプションを選択します。

[Image: 統合システム ユーザーの作成のスクリーンショット。]

#### 統合セキュリティ グループの作成

この手順では、Workday 内に、制約のない、または制約付きの統合システム セキュリティ グループを作成し、このグループに、前の手順で作成した統合システム ユーザーを割り当てます。

**セキュリティ グループを作成するには:**

1. 検索ボックスに「セキュリティ グループの作成」と入力し、[ **セキュリティ グループの作成**] をクリックします。

[Image: 検索ボックスに入力された]
2. **[セキュリティ グループの作成**] タスクを完了します。

    - Workday のセキュリティ グループには次の 2 種類があります。

        - **制約なし：** セキュリティ グループのすべてのメンバーは、セキュリティ グループによって保護されたデータインスタンスすべてにアクセスできます。
        - **制約：** すべてのセキュリティ グループ メンバーは、セキュリティ グループがアクセスできるデータ インスタンス (行) のサブセットにコンテキスト アクセスできます。
    - 統合に適したセキュリティ グループの種類を選択するには、Workday 統合パートナーに確認してください。
    - グループの種類がわかったら、[テナントされたセキュリティ グループの種類] ドロップダウンから [**統合システム セキュリティ グループ (制約なし)]** または **[統合システム セキュリティ グループ (制約あり)]** を選択します。

[Image: CreateSecurity グループのスクリーンショット。]
3. セキュリティ グループの作成が成功した後、セキュリティ グループにメンバーを割り当てることができるページが表示されます。 前の手順で作成した新しい統合システム ユーザーをこのセキュリティ グループに追加します。 "制約付き" セキュリティ グループを使用する場合は、適切な組織の範囲を選択する必要があります。

[Image: [セキュリティ グループの編集] のスクリーンショット。]

#### ドメイン セキュリティ ポリシーのアクセス許可の構成

この手順では、セキュリティ グループに、社員データについての "ドメイン セキュリティ" ポリシーのアクセス許可を付与します。

**ドメイン セキュリティ ポリシーのアクセス許可を構成するには:**

1. 検索ボックスに **「セキュリティ グループのメンバーシップとアクセス」** と入力し、レポート リンクをクリックします。

[Image: Search セキュリティ グループ メンバーシップのスクリーンショット。]
2. 前の手順で作成したセキュリティ グループを検索して選択します。

[Image: [セキュリティ グループの選択] のスクリーンショット。]
3. グループ名の横にある省略記号 (`...`) をクリックし、メニューの [**セキュリティ グループ] &gt; [セキュリティ グループのドメインアクセス許可の維持**] を選択します

[Image: [ドメインアクセス権の維持]のスクリーンショット。]
4. [**統合のアクセス許可]** で、**Put アクセスを許可するドメイン セキュリティ ポリシー**の一覧に次のドメインを追加します

    - *外部アカウントの設定*
    - *ワーカー データ: パブリック ワーカー レポート*
    - *個人データ: 職場の連絡先情報* (Microsoft Entra ID から Workday に連絡先データを書き戻す場合に必要)
    - *Workday アカウント* (Microsoft Entra ID から Workday にユーザー名/UPN を書き戻す場合に必要)
5. [**統合のアクセス許可]** で、アクセスを許可する**ドメイン セキュリティ ポリシー**の一覧に次のドメインを追加します

    - *ワーカー データ: 労働者*
    - *ワーカーデータ: すべての役職*
    - *ワーカー データ: 現在のスタッフ情報*
    - *労働者データ: 労働者プロフィールの役職*
    - *ワーカーデータ: 有資格ワーカー* (省略可能 - プロビジョニングのためにワーカーの資格データを取得する場合に追加)
    - *Worker データ: スキルとエクスペリエンス* (省略可能 - プロビジョニング用の worker スキル データを取得するためにこれを追加)
6. 以上の手順が完了すると、次のようなアクセス許可画面が表示されます。

[Image: すべてのドメインセキュリティのアクセス許可のスクリーンショット。]
7. 次の画面で [ **OK] を** クリックし **、[完了]** をクリックして構成を完了します。

#### ビジネス プロセス セキュリティ ポリシーのアクセス許可の構成

この手順では、セキュリティ グループに、社員データについての "ビジネス プロセス セキュリティ" ポリシーのアクセス許可を付与します。

注

この手順は、Workday Writeback アプリのコネクタを設定するためにのみ必要です。

**ビジネス プロセス セキュリティ ポリシーのアクセス許可を構成するには:**

1. 検索ボックスに **「業務プロセス ポリシー** 」と入力し、[ **ビジネス プロセス セキュリティ ポリシーの編集** ] タスクのリンクをクリックします。

[Image: 検索ボックスに [ビジネス プロセス ポリシー] と [ビジネス プロセス セキュリティ ポリシーの編集 - タスク] が選択されていることを示すスクリーンショット。]
2. [ **業務プロセスの種類** ] ボックスで、[ *連絡先* ] を検索し、[ **Work Contact Change** Business Process] を選択し、[OK] をクリック **します**。

[Image: [ビジネス プロセス のセキュリティ ポリシーの編集] ページと、[ビジネス プロセスの種類] メニューで選択された [Work Contact Change](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/勤務先の連絡先の変更) を示すスクリーンショット。]
3. [ **ビジネス プロセス セキュリティ ポリシーの編集** ] ページで、[ **仕事用連絡先情報の変更 (Web サービス)]** セクションまでスクロールします。
4. 新しい統合システム セキュリティ グループを選択し、Web サービス要求を開始できるセキュリティ グループの一覧に追加します。

[Image: ビジネス プロセス セキュリティ ポリシーのスクリーンショット。]
5. **[完了]**をクリックします。

#### セキュリティ ポリシーの変更のアクティブ化

**セキュリティ ポリシーの変更をアクティブにするには:**

1. 検索ボックスに「アクティブ化」と入力し、[保留中の **セキュリティ ポリシーの変更をアクティブ化**する] リンクをクリックします。

[Image: アクティベートのスクリーンショット。]
2. 監査目的でコメントを入力して、[保留中のセキュリティ ポリシー変更のアクティブ化] タスクを開始し、[OK] をクリック **します**。
3. 次の画面で[ **確認**]チェックボックスをオンにしてタスクを完了し、[ **OK]**をクリックします。

[Image: [保留中のセキュリティのアクティブ化] のスクリーンショット。]

### プロビジョニング エージェントのインストールの前提条件

次のセクションに進む前に [、プロビジョニング エージェントのインストールの前提条件](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/how-to-prerequisites) を確認します。

### Workday から Active Directory へのユーザー プロビジョニングの構成

このセクションでは、Workday から、統合の範囲内にある各 Active Directory ドメインへのユーザー アカウントのプロビジョニングの手順について説明します。

- プロビジョニング コネクタ アプリを追加し、プロビジョニング エージェントをダウンロードする
- オンプレミスのプロビジョニング エージェントをインストールして構成する
- Workday と Active Directory への接続を構成する
- 属性マッピングの構成
- ユーザー プロビジョニングを有効にして起動する

#### パート 1: プロビジョニング コネクタ アプリを追加し、プロビジョニング エージェントをダウンロードする

**Workday から Active Directory へのプロビジョニングを構成するには:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **Workday から Active Directory へのユーザー プロビジョニングを**検索し、ギャラリーからそのアプリを追加します。
4. アプリが追加され、アプリの詳細画面が表示されたら、[ **プロビジョニング**] を選択します。
5. **[プロビジョニング** **モード**] を **[自動**] に変更します。
6. 表示された情報バナーをクリックして、プロビジョニング エージェントをダウンロードします。

[Image: エージェントのダウンロードのスクリーンショット。]

#### パート 2: オンプレミス プロビジョニング エージェントのインストールと構成

オンプレミスの Active Directory にプロビジョニングするには、目的の Active Directory ドメインへのネットワーク アクセスを備えたドメイン参加済みのサーバーに、プロビジョニング エージェントがインストールされている必要があります。

ダウンロードしたエージェント インストーラーをサーバー ホストに転送し、「[**エージェントのインストール**」セクションに](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/how-to-install)記載されている手順に従ってエージェントの構成を完了します。

#### パート 3: プロビジョニング アプリで Workday と Active Directory への接続を構成する

この手順では、Workday および Active Directory との接続を確立します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **パート 1** で作成した &gt;&gt; Workday to Active Directory ユーザー プロビジョニング アプリを参照します。
3. 次のように、[ **管理者資格情報]** セクションに入力します。

    - **Workday ユーザー名** – テナント ドメイン名が追加された Workday 統合システム アカウントのユーザー名を入力します。 次のようになります: **username@tenant\_name**
    - **Workday パスワード –** Workday 統合システム アカウントのパスワードを入力します
    - **Workday Web Services API URL –** テナントの Workday Web サービス エンドポイントの URL を入力します。 URL によって、コネクタで使用される Workday Web Services API のバージョンが決まります。

        | URL 形式 | 使用される WWS API のバージョン | XPATH の変更が必要 |
        | --- | --- | --- |
        | https://####.workday.com/ccx/service/tenantName | v21.1 | いいえ |
        | https://####.workday.com/ccx/service/tenantName/Human\_Resources | v21.1 | いいえ |
        | https://####.workday.com/ccx/service/tenantName/Human\_Resources/v##.# | v##.# | はい |

        注

        URL にバージョン情報が指定されていない場合、アプリでは Workday Web Services (WWS) v21.1 が使用され、アプリに付属している既定の XPATH API 式の変更は必要ありません。 特定の WWS API バージョンを使用するには、URL の中にバージョン番号を指定します  例: `https://wd3-impl-services1.workday.com/ccx/service/contoso4/Human_Resources/v34.0` WWS API v30.0 以降を使用している場合は、プロビジョニング ジョブを有効にする前に、[&gt;] セクションと [&gt;] セクションを参照する Workday の属性リストの編集で **XPATH API 式**を更新してください。
    - **Active Directory フォレスト -** エージェントに登録されている Active Directory ドメインの "名前" です。 ドロップダウンを使用して、プロビジョニングのターゲット ドメインを選択します。 通常、この値は次のような文字列です: *contoso.com*
    - **Active Directory コンテナー -** エージェントが既定でユーザー アカウントを作成するコンテナー DN を入力します。 例: *OU=Standard Users,OU=Users,DC=contoso,DC=test*

        注

        この設定は、 *parentDistinguishedName* 属性が属性マッピングで構成されていない場合にのみ、ユーザー アカウントの作成で有効になります。 この設定は、ユーザーの検索や更新の操作には使用されません。 ドメインのサブツリー全体が、検索操作の範囲内になります。
    - **通知メール –** メール アドレスを入力し、[失敗した場合にメールを送信する] チェック ボックスをオンにします。

        注

        プロビジョニング ジョブが [検疫](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) 状態になった場合、Microsoft Entra プロビジョニング サービスは電子メール通知を送信します。
    - [ **テスト接続** ] ボタンをクリックします。 接続テストが成功した場合は、上部にある **[保存** ] ボタンをクリックします。 失敗する場合は、エージェントのセットアップで構成された Workday 資格情報と AD 資格情報が有効であることを再確認します。
    - 資格情報が正常に保存されると、[**マッピング**] セクションに既定のマッピングの **[Synchronize Workday Workers to On Premises Active Directory]\(Workday Workers をオンプレミス Active Directory に同期**する\) が表示されます

#### パート 4:属性マッピングの構成

このセクションでは、ユーザー データが Workday から Active Directory に移動する方法を構成します。

1. [ **マッピング**] の [プロビジョニング] タブで、[ **Workday Worker をオンプレミス Active Directory に同期**する] をクリックします。
2. [ **ソース オブジェクト スコープ]** フィールドでは、属性ベースのフィルターのセットを定義することで、AD へのプロビジョニングのスコープに含める Workday のユーザー セットを選択できます。 既定のスコープは、"Workday のすべてのユーザー" です。 フィルターの例:

    - 例: 1000000 から 2000000 (2000000 を除く) までの Worker ID を持つユーザーにスコープを設定

        - 属性:WorkerID
        - 演算子:REGEX Match
        - 値:(1[0-9][0-9][0-9][0-9][0-9][0-9])
    - 例:臨時社員ではなく、従業員のみ

        - 属性:社員ID
        - 演算子: NULL ではない

    ヒント

    初めてプロビジョニング アプリを構成するときは、属性マッピングと式をテストして検証し、目的の結果が得られていることを確認する必要があります。 Microsoft では、[ソース オブジェクト スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)と**オンデマンド プロビジョニング**で[スコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、Workday のいくつかのテスト ユーザーとマッピングをテストすることをお勧めします。 マッピングが機能していることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

注意事項

プロビジョニング エンジンの既定の動作では、スコープ外に出るユーザーが無効化または削除されます。 これはご使用の Workday と AD の統合には望ましくない場合があります。 この既定の動作をオーバーライドするには、[記事「スコープ外のユーザー アカウントの削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)」を参照してください
3. [ **ターゲット オブジェクト アクション]** フィールドでは、Active Directory で実行されるアクションをグローバルにフィルター処理できます。 **作成** と **更新** が最も一般的です。
4. [ **属性マッピング** ] セクションでは、個々の Workday 属性を Active Directory 属性にマップする方法を定義できます。
5. 既存の属性マッピングをクリックして更新するか、画面の下部にある [ **新しいマッピングの追加** ] をクリックして新しいマッピングを追加します。 個々の属性マッピングは、次のプロパティをサポートしています。

    - **マッピングの種類**

        - **Direct** – Workday 属性の値を AD 属性に書き込みますが、変更はありません。
        - **定数** - 静的な定数文字列値を AD 属性に書き込む
        - **式** – 1 つ以上の Workday 属性に基づいて、AD 属性にカスタム値を書き込みます。 [詳細については、式に関するこの記事を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)。
    - **ソース属性** - Workday のユーザー属性。 探している属性が存在しない場合は、 Workday ユーザー属性の一覧のカスタマイズを参照してください。
    - **既定値** – 省略可能。 ソース属性に空の値がある場合、マッピングではこの値が代わりに書き込まれます。 最も一般的な構成では、これを空白のままにします。
    - **ターゲット属性** – Active Directory のユーザー属性。
    - **この属性を使用してオブジェクトを照合** します。このマッピングを使用して、Workday と Active Directory の間でユーザーを一意に識別する必要があるかどうか。 この値は、通常、Workday の Worker ID フィールドで設定され、通常は Active Directory の従業員 ID 属性のいずれかにマップされます。
    - **一致する優先順位** – 複数の一致する属性を設定できます。 複数の場合は、このフィールドで定義された順序で評価されます。 1 件でも一致が見つかると、一致する属性の評価はそれ以上行われません。
    - **このマッピングを適用する**

        - **常に** – ユーザー作成アクションと更新アクションの両方にこのマッピングを適用する
        - **作成時のみ** - ユーザー作成アクションにのみこのマッピングを適用する
6. マッピングを保存するには、[Attribute-Mapping] セクションの上部にある [ **保存** ] をクリックします。

[Image: [保存] アクションが選択されている [属性マッピング] ページを示すスクリーンショット。]

##### Workday と Active Directory との間の属性マッピングの例と、一般的に使用される式を次に示します

- *parentDistinguishedName* 属性にマップされる式は、1 つ以上の Workday ソース属性に基づいてユーザーを別の OU にプロビジョニングするために使用されます。 この例では、所在する市区町村に基づいて、異なる OU にユーザーを配置しています。
- Active Directory の *userPrincipalName* 属性は、重複除去関数 [SelectUniqueValue](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#selectuniquevalue) を使用して生成されます。この関数は、ターゲット AD ドメインに生成された値が存在するかどうかをチェックし、一意の場合にのみ設定します。
- [ここに式の記述に関するドキュメントがあります](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)。 このセクションでは、特殊文字を削除する方法の例についても紹介しています。

| WORKDAY 属性 | ACTIVE DIRECTORY 属性 | ID 一致の有無 | 作成/更新 |
| --- | --- | --- | --- |
| **WorkerID** | 従業員ID | **はい** | 作成時のみ書き込まれる |
| **PreferredNameData** | cn |  | 作成時にのみ書き込まれる |
| **SelectUniqueValue( Join("@", Join("." , [FirstName], [LastName]), "contoso.com"), Join("@", Join(".", Mid([FirstName], 1, 1), [LastName]), "contoso.com"), Join("@", Join(".", Mid([FirstName], 1, 2), [LastName]), "contoso.com"))** | ユーザープリンシパル名 |  | 作成時のみ書き込まれる |
| `Replace(Mid(Replace([UserID], , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , )` | sAMAccountName (ユーザーログオン名) |  | 作成時のみ書き込まれる |
| `**Switch([Active], , "0", "真", "1", "偽")**` | アカウントが無効化されました |  | 作成 + 更新 |
| **FirstName** | givenName |  | 作成 + 更新 |
| **LastName** | エスエヌ |  | 作成 + 更新 |
| **PreferredNameData** | 表示名 |  | 作成 + 更新 |
| **会社** | 会社 |  | 作成 + 更新 |
| **監督組織** | 部署 |  | 作成 + 更新 |
| **ManagerReference** | マネージャー |  | 作成 + 更新 |
| **BusinessTitle** | タイトル |  | 作成＋更新 |
| **AddressLineData** | 住所 |  | 作成 + 更新 |
| **自治体** | l |  | 作成 + 更新 |
| **CountryReferenceTwoLetter** | 会社 |  | 作成 + 更新 |
| **CountryReferenceTwoLetter** | c |  | 作成＋更新 |
| **CountryRegionReference** | 聖 |  | 作成 + 更新 |
| **WorkSpaceReference** | 物理配送オフィス名 |  | 作成 + 更新 |
| **PostalCode** | 郵便番号 |  | 作成 + 更新 |
| **主な仕事用電話** | 電話番号 |  | 作成 + 更新 |
| **ファックス** | ファクシミリ電話番号 |  | 作成 + 更新 |
| **モバイル** | モバイル |  | 作成 + 更新 |
| **LocalReference** | 優先言語 |  | 作成 + 更新 |
| **Switch([市区町村], "OU=Default Users,DC=contoso,DC=com", "Dallas", "OU=Dallas,OU=Users,DC=contoso,DC=com", "Austin", "OU=Austin,OU=Users,DC=contoso,DC=com", "Seattle", "OU=Seattle,OU=Users,DC=contoso,DC=com", "London", "OU=London,OU=Users,DC=contoso,DC=com")** | 親ディスティングイッシュド・ネーム |  | 作成 + 更新 |

属性マッピングの構成が完了したら、オンデマンド プロビジョニングを使用して 1 人のユーザーのプロビジョニング [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) テストし、 ユーザー プロビジョニング サービスを有効にして起動できます。

### ユーザー プロビジョニングの有効化と起動

Workday プロビジョニング アプリの構成が完了し、オンデマンド プロビジョニングを使用して 1 人のユーザーのプロビジョニング [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)確認したら、プロビジョニング サービスを有効にすることができます。

ヒント

既定では、プロビジョニング サービスを有効にすると、スコープ内のすべてのユーザーに対してプロビジョニング操作が開始されます。 マッピングのエラーまたは Workday データの問題がある場合、プロビジョニング ジョブが失敗し、検疫状態になる可能性があります。 これを回避するには、ベスト プラクティスとして、すべてのユーザーの完全同期を起動する前に、**オンデマンド プロビジョニング**を使用して、ソース [オブジェクト スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) フィルターを構成し、少数のテスト ユーザーで属性マッピングをテストすることをお勧めします。 マッピングが機能し、目的の結果が得られていることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

1. [ **プロビジョニング** ] ブレードに移動し、[ **プロビジョニングの開始**] をクリックします。
2. この操作により初期同期が開始されます。これに要する時間は Workday テナントのユーザー数に応じて変わります。 進行状況バーをチェックして、同期サイクルの進行状況を追跡できます。
3. いつでも、Microsoft Entra 管理センターの [ **プロビジョニング** ] タブで、プロビジョニング サービスが実行したアクションを確認します。 プロビジョニング ログには、Workday から読み込まれたユーザーや、その後 Active Directory に追加または更新されたユーザーなど、プロビジョニング サービスによって実行された個々の同期イベントがすべて表示されます。 プロビジョニング ログを確認してプロビジョニング エラーを修正する方法の手順については、トラブルシューティングに関するセクションを参照してください。
4. 初期同期が完了すると、次に示すように、[ **プロビジョニング** ] タブに監査概要レポートが書き込まれます。

[Image: プロビジョニングの進行状況バーのスクリーンショット]

### よくあるご質問 (FAQ)

- **ソリューション機能に関する質問**

    - Workday から新入社員を処理する場合、ソリューションで Active Directory の新しいユーザー アカウントのパスワードはどのように設定されますか?
    - プロビジョニング操作が完了した後の電子メール通知の送信は、ソリューションでサポートされていますか?
    - ソリューションは、Microsoft Entra クラウドまたはプロビジョニング エージェント レイヤーで Workday ユーザー プロファイルをキャッシュしますか?
    - このソリューションでは、ユーザーへのオンプレミス AD グループの割り当てをサポートしていますか?
    - ソリューションで Workday worker プロファイルのクエリと更新に使用される Workday API はどれですか?
    - 2 つの Microsoft Entra テナントで Workday HCM テナントを構成できますか?
    - Workday と Microsoft Entra の統合に関連する機能強化を提案したり、新機能を要求したりするにはどうすればよいですか?
- **プロビジョニング エージェントに関する質問**

    - プロビジョニング エージェントの GA バージョンは何ですか?
    - プロビジョニング エージェントのバージョンを確認する方法
    - Microsoft はプロビジョニング エージェントの更新プログラムを自動的にプッシュしますか?
    - Microsoft Entra Connect を実行している同じサーバーにプロビジョニング エージェントをインストールできますか?
    - 送信 HTTP 通信にプロキシ サーバーを使用するようにプロビジョニング エージェントを構成するにはどうすればよいですか?
    - プロビジョニング エージェントが Microsoft Entra テナントと通信でき、ファイアウォールがエージェントに必要なポートをブロックしないようにするにはどうすればよいですか?
    - プロビジョニング エージェントに関連付けられているドメインを登録解除するにはどうすればよいですか?
    - プロビジョニング エージェントをアンインストールする方法
- **Workday から AD への属性マッピングと構成に関する質問**

    - Workday Provisioning 属性マッピングとスキーマの作業コピーをバックアップまたはエクスポートするにはどうすればよいですか?
    - Workday と Active Directory にカスタム属性があります。 カスタム属性と連携するようにソリューションを構成するにはどうすればよいですか。
    - Workday から Active Directory にユーザーの写真をプロビジョニングできますか?
    - 一般使用に対するユーザーの同意に基づいて Workday の携帯電話番号を同期するにはどうすればよいですか?
    - ユーザーの部署/国/市区町村の属性に基づいて AD の表示名を書式設定し、地域の差異を処理するにはどうすればよいですか?
    - SelectUniqueValue を使用して samAccountName 属性の一意の値を生成するにはどうすればよいですか?
    - 分音記号を含む文字を削除し、通常の英語のアルファベットに変換するにはどうすればよいですか?

#### ソリューションの機能に関する質問

##### Workday の新規採用者を処理するときに、ソリューションは Active Directory の新規ユーザー アカウントのパスワードをどのように設定しますか。

オンプレミス プロビジョニング エージェントで、新しい AD アカウントの作成要求を受け取ると、AD サーバーによって定義されたパスワードの複雑さの要件を満たすように設計された複雑なランダム パスワードが自動的に生成され、それがユーザー オブジェクトに設定されます。 このパスワードはどこにも記録されません。

##### ソリューションは、プロビジョニング操作の完了後にメール通知を送信することをサポートしていますか。

いいえ。プロビジョニング操作の完了後のメール通知送信は、現在のリリースではサポートされていません。

##### ソリューションでは、Microsoft Entra クラウドまたはプロビジョニング エージェント レイヤーに Workday ユーザープロファイルがキャッシュされますか。

いいえ。このソリューションではユーザー プロファイルのキャッシュが保持されません。 Microsoft Entra プロビジョニング サービスは単なるデータ プロセッサとして機能し、Workday からデータを読み取り、ターゲット Active Directory または Microsoft Entra に書き込みます。 ユーザーのプライバシーとデータ保持に関する詳細については、「 個人データの管理 」セクションを参照してください。

##### ソリューションは、オンプレミスの AD グループをユーザーに割り当てることをサポートしていますか。

この機能は現在サポートされていません。 推奨される回避策は、Microsoft Graph API エンドポイントにクエリを実行して [ログ データをプロビジョニング](https://learn.microsoft.com/ja-jp/graph/api/resources/azure-ad-auditlog-overview) し、グループの割り当てなどのシナリオをトリガーするために使用する PowerShell スクリプトをデプロイすることです。 この PowerShell スクリプトは、タスク スケジューラにアタッチして、プロビジョニング エージェントを実行している同じボックスにデプロイできます。

##### Workday の従業員プロファイルのクエリと更新にこのソリューションが使用する Workday API はどれですか。

現在、このソリューションは次の Workday API を使用しています。

- [**管理者資格情報]** セクションで使用される **Workday Web サービス API URL** 形式によって、Get\_Workersに使用される API のバージョンが決まります

    - URL の形式が https://####.workday.com/ccx/service/tenantName の場合は、API v21.1 が使用されます。
    - URL の形式が https://####.workday.com/ccx/service/tenantName/Human\_Resources の場合は、API v21.1 が使用されます。
    - URL 形式が https://####.workday.com/ccx/service/tenantName/Human\_Resources/v##.# の場合は、指定された API バージョンが使用されます。 (例: v34.0 が指定されると、これが使用されます)。
- Workday メール書き戻し機能は Change\_Work\_Contact\_Information (v30.0) を使用します
- Workday ユーザー名書き戻し機能は Update\_Workday\_Account (v31.2) を使用します

##### Workday HCM テナントは、2 つの Microsoft Entra テナントと一緒に構成できますか。

はい。この構成はサポートされています。 このシナリオを構成する手順の概要を次に示します。

- プロビジョニング エージェント 1 をデプロイし、Microsoft Entra テナント 1 に登録します。
- プロビジョニング エージェント 2 をデプロイし、Microsoft Entra テナント 2 に登録します。
- 各プロビジョニング エージェントが管理する "子ドメイン" に基づいて、各エージェントとドメインを構成します。 1 つのエージェントで複数のドメインを処理できます。
- Microsoft Entra 管理センターで、各テナントの Workday to AD User Provisioning アプリを設定し、それぞれのドメインと共に構成します。

##### Workday と Microsoft Entra の統合に関連した改善を提案したり、新しい機能を依頼したりするにはどうすればよいですか。

今後のリリースや機能強化の方向性を決める上でお客様のフィードバックはとても貴重です。 Microsoft [Entra ID のフィードバック フォーラム](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)で、すべてのフィードバックを歓迎し、アイデアや改善の提案を送信することをお勧めします。 Workday 統合に関連する特定のフィードバックについては、 *カテゴリ SaaS アプリケーション* を選択し、Workday キーワードを使用して検索し、 *Workday* に関連する既存のフィードバックを見つけます。

[Image: UserVoice Workday のスクリーンショット。]

新しいアイデアを提案するときは、他のユーザーが既に同様の機能を提案しているかどうかを確認してください。 その場合は、機能や機能強化のリクエストに賛成を投票することができます。 また、特定のユース ケースについてコメントを残してアイデアを支持していることを示し、その機能がお客様にとってどのように価値があるかを示すこともできます。

#### プロビジョニング エージェントに関する質問

##### プロビジョニング エージェントのGAバージョンは何ですか。

プロビジョニング エージェントの最新の GA バージョンについては、 [Microsoft Entra Connect プロビジョニング エージェントのバージョン リリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history) を参照してください。

##### プロビジョニング エージェントのバージョンを確認する方法を教えてください。

1. プロビジョニング エージェントがインストールされている Windows サーバーにサインインします。
2. **[コントロール パネル**]&gt;**[プログラムのインストールを取り消す]または[プログラムの変更**]メニューに移動します。
3. **Microsoft Entra Connect プロビジョニング エージェント**のエントリに対応するバージョンを探します。

##### Microsoft からプロビジョニング エージェントの更新プログラムは自動的にプッシュされますか。

はい。Windows サービス Microsoft **Entra Connect Agent Updater** が稼働している場合、Microsoft はプロビジョニング エージェントを自動的に更新します。

##### Microsoft Entra Connect を実行しているのと同じサーバーにプロビジョニング エージェントをインストールできますか。

はい、Microsoft Entra Connect を実行しているのと同じサーバーにプロビジョニング エージェントをインストールすることができます。

##### 構成時に、プロビジョニング エージェントでは Microsoft Entra 管理者の資格情報が求められます。 エージェントは資格情報をサーバー上にローカル保存しますか。

構成時に、プロビジョニング エージェントでは、Microsoft Entra テナントに接続するときにのみ、Microsoft Entra 管理者の資格情報が求められます。 資格情報は、サーバーのローカルには保存されません。 ただし、ローカルの Windows パスワード コンテナー内の *オンプレミス Active Directory ドメイン* への接続に使用される資格情報は保持されます。

##### 送信 HTTP 通信にプロキシ サーバーを使用するようにプロビジョニング エージェントを構成するにはどうすればよいですか。

プロビジョニング エージェントは送信プロキシの使用をサポートしています。 エージェント ** 構成ファイルC:\Program Files\Microsoft Azure AD Connect Provisioning Agent\AADConnectProvisioningAgent.exe.config**を編集して構成できます。終了 `</configuration>` タグの直前のファイルの末尾に、次の行を追加します。 変数 [proxy-server] と [proxy-port] をお客様のプロキシ サーバー名とポート値に置き換えてください。

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

##### プロビジョニング エージェントが Microsoft Entra テナントと通信できること、ファイアウォールがエージェントに必要なポートをブロックしていないことを確認するにはどうすればよいですか。

[必要なすべてのポート](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)が開いているかどうかを確認することもできます。

##### 1 つのプロビジョニング エージェントを複数の AD ドメインをプロビジョニングするように構成できますか。

はい。1 つのプロビジョニング エージェントは、そのエージェントから各ドメイン コントローラーへの通信経路がある限り、複数の AD ドメインを処理するように構成できます。 高可用性を確保し、フェールオーバーをサポートするために、同じ AD ドメインのセットにサービスを提供する 3 つのプロビジョニング エージェントのグループを設定することをお勧めします。

##### プロビジョニング エージェントに関連付けられているドメインの登録を解除するにはどうすればよいですか。

\*、Microsoft Entra テナントの*ID を*取得します。

- プロビジョニング エージェントを実行している Windows サーバーにサインインします。
- Windows 管理者として PowerShell を開きます。
- 登録スクリプトを含むディレクトリに移動し、[tenant ID] パラメーターをお客様のテナント ID の値に置き換えて、次のコマンドを実行します。

    ```powershell
    cd "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\RegistrationPowershell\Modules\PSModulesFolder"
    Import-Module "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\RegistrationPowershell\Modules\PSModulesFolder\MicrosoftEntraPrivateNetworkConnectorPSModule.psd1"
    Get-PublishedResources -TenantId "[tenant ID]"
    ```
- 表示されるエージェントの一覧から、`id` が AD ドメイン名と等しいリソースの  フィールドの値をコピーします。
- このコマンドに ID 値を貼り付けて、PowerShell でコマンドを実行します。

    ```powershell
    Remove-PublishedResource -ResourceId "[resource ID]" -TenantId "[tenant ID]"
    ```
- Agent configuration (エージェントの構成) ウィザードを再実行します。
- 以前にこのドメインに割り当てられていた他のエージェントがある場合は、すべて再構成する必要があります。

##### プロビジョニング エージェントをアンインストールするにはどうすればよいですか。

- プロビジョニング エージェントがインストールされている Windows サーバーにサインインします。
- **[コントロール パネル**] に移動&gt;**プログラムメニューのインストールまたは変更を**行う
- 次のプログラムをアンインストールします。
    - Microsoft Entra Connect プロビジョニング エージェント
    - Microsoft Entra Connect エージェント アップデーター
    - Microsoft Entra Connect プロビジョニング エージェント パッケージ

#### Workday から AD への属性マッピングと構成に関する質問

##### Workday プロビジョニング属性マッピングとスキーマの作業用コピーをバックアップまたはエクスポートするにはどうすればよいですか。

Microsoft Graph API を使用して、Workday のユーザー プロビジョニング構成をエクスポートできます。 詳細については、「 Workday ユーザー プロビジョニング属性マッピング構成のエクスポートとインポート 」セクションの手順を参照してください。

##### Workday と Active Directory にカスタム属性があります。 カスタム属性と連携するようにソリューションを構成するにはどうすればよいですか。

このソリューションは、カスタムの Workday 属性と Active Directory 属性をサポートしています。 カスタム属性をマッピング スキーマに追加するには、[ **属性マッピング** ] ブレードを開き、下にスクロールして [ **詳細オプションの表示**] セクションを展開します。

[Image: 属性リストの編集のスクリーンショット。]

カスタム Workday 属性を追加するには、 *Workday の [属性リストの編集]* オプションを選択し、カスタム AD 属性を追加するには、[ *オンプレミス Active Directory] の [属性リストの編集]* オプションを選択します。

関連項目:

- Workday ユーザー属性の一覧のカスタマイズ

##### Workday の変更に基づいて AD 内の属性のみを更新し、新しい AD アカウントを作成しないようにソリューションを構成するにはどうすればよいですか。

この構成は、次に示すように、[**属性マッピング**] ブレードで**ターゲット オブジェクト アクション**を設定することで実現できます。

[Image: Update アクションのスクリーンショット。]

Workday から AD 方向の更新操作のみを実行するには、[更新] チェックボックスをオンにします。

##### ユーザーの写真を Workday から Active Directory にプロビジョニングできますか。

現在、このソリューションでは、Active Directory での *thumbnailPhoto* や *jpegPhoto* などのバイナリ属性の設定はサポートされていません。

##### パブリック使用に関するユーザーの同意に基づいて Workday の携帯電話番号を同期するにはどうすればよいですか。

- Workday Provisioning アプリの [プロビジョニング] ブレードに移動します。
- [属性マッピング] をクリックします
- [ **マッピング**] で、[ **Workday Worker をオンプレミス Active Directory に同期** する] (または **[Workday Worker を Microsoft Entra ID に同期**する] を選択します)。
- [属性マッピング] ページで、下にスクロールして [詳細オプションの表示] チェックボックスをオンにします。 **Workday の [属性リストの編集] を**クリックします
- 開いたブレードで「Mobile」属性を見つけて行をクリックし、**API 式**を編集できるようにします。[Image: Mobile GDPR のスクリーンショット。]
- **API 式**を次の新しい式に置き換えます。この式は、Workday で "Public Usage Flag" が "True" に設定されている場合にのみ、職場の携帯電話番号を取得します。

    ```
     wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Contact_Data/wd:Phone_Data[translate(string(wd:Phone_Device_Type_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='MOBILE' and translate(string(wd:Usage_Data/wd:Type_Data/wd:Type_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='WORK' and string(wd:Usage_Data/@wd:Public)='1']/@wd:Formatted_Phone
    ```
- 属性リストを保存します。
- 属性マッピングを保存します。
- 現在の状態を消去して、完全な同期を再開します。

##### ユーザーの部署/国/市区町村の属性に基づいて AD の表示名の書式を設定し、地域の差異を処理するにはどうすればよいですか。

ユーザーの部署と国/地域に関する情報も提供されるように、AD で *displayName* 属性を構成することは一般的な要件です。 たとえば、John Smith が米国のマーケティング部門で働いている場合、 *displayName* を *Smith、John (Marketing-US)* として表示できます。

CN *または* *displayName* を構築するためのこのような要件を処理して、会社、部署、市区町村、国/地域などの属性を含める方法を次に示します。

- 各 Workday 属性は、基になる XPATH API 式を使用して取得されます。これは、 **属性マッピング -&gt; 詳細セクション -&gt; Workday の属性リストの編集**で構成できます。 Workday *PreferredFirstName*、 *PreferredLastName*、 *Company* 属性、 *SupervisoryOrganization* 属性の既定の XPATH API 式を次に示します。

    | Workday 属性 | API XPATH 式 |
    | --- | --- |
    | 優先使用名 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:First\_Name/text() |
    | 希望の苗字 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:Last\_Name/text() |
    | 会社 | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data[wd:Organization\_Data/wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID']='Company']/wd:Organization\_Reference/@wd:Descriptor |
    | 監督組織 | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data/wd:Organization\_Data[wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID']='Supervisory']/wd:Organization\_Name/text() |

    上記の API 式がお客様の Workday テナント構成で有効であることをお客様の Workday チームに確認してください。 必要に応じて、 Workday ユーザー属性の一覧のカスタマイズセクションの説明に従って編集できます。
- 同様に、Workday に存在する国/地域情報は、次の XPATH を使用して取得されます: *wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference*

    Workday 属性リスト セクションで使用できる国/地域関連の属性が 5 つあります。

    | Workday 属性 | API XPATH 式 |
    | --- | --- |
    | CountryReference | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Alpha-3\_Code']/text() |
    | カントリーリファレンスフレンドリー | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/@wd:Descriptor |
    | 国参照数値 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Numeric-3\_Code']/text() |
    | 国リファレンス2文字 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Alpha-2\_Code']/text() |
    | 国地域リファレンス | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Region\_Reference/@wd:Descriptor |

    上の API 式がお客様の Workday テナント構成で有効であることをお客様の Workday チームに確認してください。 必要に応じて、 Workday ユーザー属性の一覧のカスタマイズセクションの説明に従って編集できます。
- 正しい属性マッピング式を構築するには、どの Workday 属性が "正式に" ユーザーの姓、名、国/地域、部署を表すかを特定します。 属性がそれぞれ *PreferredFirstName*、 *PreferredLastName*、 *CountryReferenceTwoLetter* 、 *SupervisoryOrganization* であるとします。 これを使用して、次のように AD *displayName* 属性の式を作成し *、Smith、John (Marketing-US)* などの表示名を取得できます。

    ```
     Append(Join(", ",[PreferredLastName],[PreferredFirstName]), Join(""," (",[SupervisoryOrganization],"-",[CountryReferenceTwoLetter],")"))
    ```

    適切な式を作成したら、[属性マッピング] テーブルを編集し、 *displayName* 属性マッピングを次のように変更します。 [Image: DisplayName マッピングのスクリーンショット。]
- 上記の例を拡張して、Workday から取得した都市名を短縮形の値に変換し、それを使用して *Smith、John (CHI)、* *Doe、Jane (NYC)* などの表示名を作成すると、この結果は、Workday *市区町村*属性を決定変数として使用して実現できます。

    ```
    Switch
    (
      [Municipality],
      Join(", ", [PreferredLastName], [PreferredFirstName]),  
           "Chicago", Append(Join(", ",[PreferredLastName], [PreferredFirstName]), "(CHI)"),
           "New York", Append(Join(", ",[PreferredLastName], [PreferredFirstName]), "(NYC)"),
           "Phoenix", Append(Join(", ",[PreferredLastName], [PreferredFirstName]), "(PHX)")
    )
    ```

    関連項目:

    - [Switch 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch)
    - [結合関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#join)
    - [Append 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#append)

##### SelectUniqueValue を使用して samAccountName 属性の一意の値を生成する方法を教えてください。

たとえば、Workday の *FirstName* 属性と *LastName* 属性の組み合わせを使用して*、samAccountName* 属性の一意の値を生成するとします。 以下の式を基にして始めることができます。

```
SelectUniqueValue(
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("",  Mid([FirstName],1,1), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , ),
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("",  Mid([FirstName],1,2), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , ),
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("",  Mid([FirstName],1,3), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , )
)
```

上の式は次のように機能します。ユーザーが John Smith の場合は、まず JSmith の生成が試行され、JSmith が既に存在する場合は JoSmith が生成され、それが存在する場合は JohSmith が生成されます。 また、この式により、生成される値が *samAccountName* に関連付けられている長さの制限と特殊文字の制限を満たしていることも保証されます。

関連項目:

- [Mid 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#mid)
- [Replace 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#replace)
- [SelectUniqueValue 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#selectuniquevalue)

##### 分音記号を使用する文字を削除し、通常の英語のアルファベットに変換するにはどうすればよいですか。

[NormalizeDiacritics](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#normalizediacritics) 関数を使用して、ユーザーの電子メール アドレスまたは CN 値を構築しながら、ユーザーの名と姓の特殊文字を削除します。

### トラブルシューティングのヒント

このセクションでは、Microsoft Entra プロビジョニング ログと Windows Server イベント ビューアー ログを使用して、Workday 統合に関するプロビジョニングの問題を解決する方法について、具体的なガイダンスを提供します。 これは、「[チュートリアル: 自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」でキャプチャした一般的なトラブルシューティング手順と概念に基づいています

このセクションでは、トラブルシューティングの次の側面について説明しています。

- イベント ビューアー ログを出力するようにプロビジョニング エージェントを構成する
- エージェントのトラブルシューティングのための Windows イベント ビューアーの設定
- サービスのトラブルシューティングのための Microsoft Entra 管理センターのプロビジョニング ログの設定
- AD ユーザー アカウントの作成操作のログについて
- Manager の更新操作のログについて
- 一般的に発生するエラーの解決

#### イベント ビューアー ログを出力するようにプロビジョニング エージェントを構成する

1. プロビジョニング エージェントがデプロイされている Windows Server マシンにサインインします。
2. **サービス Microsoft Entra Connect プロビジョニング エージェントを停止します**。
3. 元の構成ファイルのコピーを作成 * します:C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\AADConnectProvisioningAgent.exe.config*。
4. 既存の `<system.diagnostics>` セクションを以下の内容に置き換えます。

    - リスナー構成 **etw** は EventViewer ログにメッセージを出力します
    - リスナー構成 **textWriterListener** は、トレース メッセージをファイル *ProvAgentTrace.log*に送信します。 高度なトラブルシューティングを行う場合のみ、textWriterListener に関連した行をコメント解除してください。

    ```xml
      <system.diagnostics>
          <sources>
          <source name="AAD Connect Provisioning Agent">
              <listeners>
              <add name="console"/>
              <add name="etw"/>
              <!-- <add name="textWriterListener"/> -->
              </listeners>
          </source>
          </sources>
          <sharedListeners>
          <add name="console" type="System.Diagnostics.ConsoleTraceListener" initializeData="false"/>
          <add name="etw" type="System.Diagnostics.EventLogTraceListener" initializeData="Azure AD Connect Provisioning Agent">
              <filter type="System.Diagnostics.EventTypeFilter" initializeData="All"/>
          </add>
          <!-- <add name="textWriterListener" type="System.Diagnostics.TextWriterTraceListener" initializeData="C:/ProgramData/Microsoft/Azure AD Connect Provisioning Agent/Trace/ProvAgentTrace.log"/> -->
          </sharedListeners>
      </system.diagnostics>
    
    ```
5. **サービス Microsoft Entra Connect プロビジョニング エージェントを開始します**。

#### エージェントのトラブルシューティングのための Windows イベント ビューアーの設定

1. プロビジョニング エージェントがデプロイされている Windows Server マシンにサインインします。
2. **Windows Server イベント ビューアー** デスクトップ アプリを開きます。
3. **[Windows ログ &gt; アプリケーション] を選択します**。
4. 次に示すようにフィルター "-5" を指定して、ソース**の Microsoft Entra Connect プロビジョニング エージェント**でログに記録されたすべてのイベントを表示し、イベント ID が "5" のイベントを除外するには、[**現在のログのフィルター]...** オプションを使用します。

    注

    イベント ID 5 でキャプチャされるのは Microsoft Entra クラウド サービスに対するエージェントのブートストラップ メッセージであるため、ログ ファイルを分析する間はフィルターで除外します。

    [Image: Windows イベント ビューアーのスクリーンショット。]
5. [ **OK] を** クリックし、結果ビューを **日付と時刻** の列で並べ替えます。

#### サービスのトラブルシューティングのための Microsoft Entra 管理センター プロビジョニング ログの設定

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)を起動し、Workday **プロビジョニング** アプリケーションの [プロビジョニング] セクションに移動します。
2. [プロビジョニング ログ] ページの **[列** ] ボタンを使用して、ビューに次の列 (日付、アクティビティ、状態、状態の理由) のみを表示します。 この構成にすることで、トラブルシューティングに関連するデータのみに確実に集中できます。

    [Image: プロビジョニング ログ列のスクリーンショット。]
3. **[ターゲット**] および **[日付範囲]** クエリ パラメーターを使用して、ビューをフィルター処理します。

    - **Target** クエリ パラメーターを Workday worker オブジェクトの "Worker ID" または "Employee ID" に設定します。
    - **日付範囲**を、プロビジョニングに関するエラーや問題を調査する適切な期間に設定します。

    [Image: プロビジョニング ログ フィルターのスクリーンショット。]

#### AD ユーザー アカウントの作成操作のログの概要

Workday の新入社員が検出されると (たとえば、Employee ID *21023* とします)、Microsoft Entra プロビジョニング サービスは、ワーカーの新しい AD ユーザー アカウントの作成を試み、プロセスで次に説明するように 4 つのプロビジョニング ログ レコードを作成します。

[Image: プロビジョニング ログの作成操作のスクリーンショット。]

プロビジョニング ログ レコードのいずれかをクリックすると、[ **アクティビティの詳細]** ページが開きます。 各ログ レコードの種類に対して [ **アクティビティの詳細]** ページが表示される内容を次に示します。

- **Workday インポート** レコード: このログ レコードには、Workday からフェッチされたワーカー情報が表示されます。 ログ レコードの *[追加の詳細]* セクションの情報を使用して、Workday からのデータのフェッチに関する問題のトラブルシューティングを行います。 レコードの例を、各フィールドの解釈方法についてのポインターと共に次に示します。

    ```JSON
    ErrorCode : None  // Use the error code captured here to troubleshoot Workday issues
    EventName : EntryImportAdd // For full sync, value is "EntryImportAdd" and for delta sync, value is "EntryImport"
    JoiningProperty : 21023 // Value of the Workday attribute that serves as the Matching ID (usually the Worker ID or Employee ID field)
    SourceAnchor : a071861412de4c2486eb10e5ae0834c3 // set to the WorkdayID (WID) associated with the record
    ```
- **AD インポート** レコード: このログ レコードには、AD からフェッチされたアカウントの情報が表示されます。 最初のユーザー作成時には AD アカウントがないため、アクティビティの [状態の理由] は、その照合 ID 属性値を持つアカウントが Active Directory に見つからなかったことを示します。 ログ レコードの *[追加の詳細]* セクションの情報を使用して、Workday からのデータのフェッチに関する問題のトラブルシューティングを行います。 レコードの例を、各フィールドの解釈方法についてのポインターと共に次に示します。

    ```JSON
    ErrorCode : None // Use the error code captured here to troubleshoot Workday issues
    EventName : EntryImportObjectNotFound // Implies that object wasn't found in AD
    JoiningProperty : 21023 // Value of the Workday attribute that serves as the Matching ID
    ```

    この AD インポート操作に対応する Provisioning Agent ログ レコードを検索するには、Windows イベント ビューアーのログを開き、[ **検索...]** メニュー オプションを使用して、照合 ID/結合プロパティの属性値 (この場合 *は 21023*) を含むログ エントリを検索します。

    [Image: [検索] のスクリーンショット。]

    *イベント ID = 9* のエントリを探します。このエントリには、エージェントが AD アカウントを取得するために使用する LDAP 検索フィルターが用意されています。 これが一意のユーザー エントリを取得するための適切な検索フィルターであるかどうかを確認できます。

    *イベント ID = 2* の直後のレコードは、検索操作の結果と、結果が返されたかどうかをキャプチャします。
- **同期規則アクション** レコード: このログ レコードには、属性マッピング ルールと構成されたスコープ フィルターの結果と、受信 Workday イベントを処理するために実行されるプロビジョニング アクションが表示されます。 ログ レコードの *[追加の詳細]* セクションの情報を使用して、同期アクションに関する問題のトラブルシューティングを行います。 レコードの例を、各フィールドの解釈方法についてのポインターと共に次に示します。

    ```JSON
    ErrorCode : None // Use the error code captured here to troubleshoot sync issues
    EventName : EntrySynchronizationAdd // Implies that the object is added
    JoiningProperty : 21023 // Value of the Workday attribute that serves as the Matching ID
    SourceAnchor : a071861412de4c2486eb10e5ae0834c3 // set to the WorkdayID (WID) associated with the profile in Workday
    ```

    属性マッピング式に問題がある場合、または受信 Workday データに問題がある場合 (たとえば、必須の属性値が空または null)、この段階で、失敗の詳細を示す ErrorCode を使用して失敗を確認します。
- **AD エクスポート** レコード: このログ レコードには、AD アカウントの作成操作の結果と、プロセスで設定された属性値が表示されます。 アカウント作成操作に関する問題をトラブルシューティングするには、ログ レコードの *[追加の詳細]* セクションの情報を使用します。 レコードの例を、各フィールドの解釈方法についてのポインターと共に次に示します。 [追加の詳細] セクションで、"EventName" は "EntryExportAdd" に設定され、"JoiningProperty" は [Matching ID](照合 ID) 属性の値に設定され、"SourceAnchor" はそのレコードに関連付けられた WorkdayID (WID) に設定され、"TargetAnchor" は新しく作成されたユーザーの AD の "ObjectGuid" 属性の値に設定されます。

    ```JSON
    ErrorCode : None // Use the error code captured here to troubleshoot AD account creation issues
    EventName : EntryExportAdd // Implies that object is created
    JoiningProperty : 21023 // Value of the Workday attribute that serves as the Matching ID
    SourceAnchor : a071861412de4c2486eb10e5ae0834c3 // set to the WorkdayID (WID) associated with the profile in Workday
    TargetAnchor : aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb // set to the value of the AD "objectGuid" attribute of the new user
    ```

    この AD エクスポート操作に対応するプロビジョニング エージェントのログ レコードを検索するには、Windows イベント ビューアーのログを開き、[ **検索...]** メニュー オプションを使用して、照合 ID/結合プロパティの属性値 (この場合 *は 21023*) を含むログ エントリを検索します。

    *イベント ID = 2* のエクスポート操作のタイムスタンプに対応する HTTP POST レコードを探します。 このレコードには、プロビジョニング サービスからプロビジョニング エージェントに送信された属性値が含まれます。

    [Image: [プロビジョニング エージェント] ログの]

    上記のイベントの直後に、AD アカウント作成操作の応答をキャプチャする別のイベントがあるはずです。 このイベントは、AD で作成された新しい objectGuid を返し、それがプロビジョニング サービスの TargetAnchor 属性として設定されます。

    [Image: AD で作成された objectGuid が強調表示された [プロビジョニング エージェント] ログを示すスクリーンショット。]

#### マネージャーの更新操作のログの概要

manager 属性は AD の参照属性です。 プロビジョニング サービスでは、ユーザー作成操作の一環として manager 属性は設定されません。 代わりに、ユーザーの AD アカウントが作成された後、 *更新* 操作の一部としてマネージャー属性が設定されます。 上記の例を展開すると、Workday で従業員 ID "21451" を持つ新入社員がアクティブになり、新入社員のマネージャー (*21023*) に既に AD アカウントがあるとします。 このシナリオでは、ユーザー 21451 のプロビジョニング ログを検索すると、5 つのエントリが表示されます。

[Image: Manager Update のスクリーンショット。]

最初の 4 つのレコードは、ユーザー作成操作の一部として調べたものと似ています。 5 つ目のレコードは、manager 属性の更新に関連付けられたエクスポートです。 ログ レコードには、マネージャーの *objectGuid* 属性を使用して実行される AD アカウント マネージャーの更新操作の結果が表示されます。

```JSON
// Modified Properties
Name : manager
New Value : "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb" // objectGuid of the user 21023

// Additional Details
ErrorCode : None // Use the error code captured here to troubleshoot AD account creation issues
EventName : EntryExportUpdate // Implies that object is created
JoiningProperty : 21451 // Value of the Workday attribute that serves as the Matching ID
SourceAnchor : 9603bf594b9901693f307815bf21870a // WorkdayID of the user
TargetAnchor : 43b668e7-1d73-401c-a00a-fed14d31a1a8 // objectGuid of the user 21451

```

#### よく発生するエラーの解決

このセクションでは、Workday ユーザーのプロビジョニングでよく見られるエラーとその解決方法について説明します。 エラーは次のように分類されます。

- プロビジョニング エージェントのエラー
- 接続エラー
- AD ユーザー アカウントの作成エラー
- AD ユーザー アカウントの更新エラー

##### プロビジョニング エージェントのエラー

| # | エラーのシナリオ | 考えられる原因 | 推奨される解決方法 |
| --- | --- | --- | --- |
| 1. | サービス *'Microsoft Entra Connect Provisioning Agent' (AADConnectProvisioningAgent) の起動に失敗しました。プロビジョニング エージェントのインストール中にエラー メッセージが表示されました。システムを起動するための十分な特権があることを確認します。* | 通常、このエラーはプロビジョニング エージェントをドメイン コントローラーにインストールしようとしたときに、グループ ポリシーがサービスの開始を妨げた場合に発生します。 前のバージョンのエージェントを実行していて、それを新しいインストールの開始前にアンインストールしなかった場合にもこれが表示されます。 | DC 以外のサーバーにプロビジョニング エージェントをインストールします。 新しいエージェントをインストールする前に、必ず以前のバージョンのエージェントをアンインストールします。 |
| 2. | Windows サービス 'Microsoft Entra Connect プロビジョニング エージェント' は *開始* 状態であり、 *実行中* の状態には切り替わりません。 | エージェント ウィザードは、インストールの一環として、サーバーにローカル アカウント (**NT Service\AADConnectProvisioningAgent**) を作成します。これは、サービスの開始に使用されるログオン アカウントです。 Windows サーバー上のセキュリティ ポリシーにより、ローカル アカウントでサービスを実行できない場合は、このエラーが発生します。 | *サービス コンソール*を開きます。 Windows サービスの 'Microsoft Entra Connect Provisioning Agent' を右クリックし、ログオン タブでサービスを実行するドメイン管理者のアカウントを指定します。 サービスを再起動します。 |
| 3. | *Active Directory の接続*手順で AD ドメインを使用してプロビジョニング エージェントを構成する場合、ウィザードで AD スキーマを読み込もうとする時間が長く、最終的にタイムアウトになります。 | 通常、このエラーは、ファイアウォールの問題のためにウィザードから AD ドメイン コントローラー サーバーに接続できない場合に表示されます。 | [Active Directory の接続] ウィザード画面で、AD ドメインの資格情報を入力するときに、[Select domain controller priority] (ドメイン コントローラーの優先順位の選択) というオプションがあります。 このオプションは、エージェント サーバーと同じサイト内にあるドメイン コントローラーを選択し、通信をブロックするファイアウォール規則がないようにするために使用します。 |

##### 接続エラー

プロビジョニング サービスが Workday または Active Directory に接続できない場合は、プロビジョニングが検疫状態になる可能性があります。 次の表を使用して、接続の問題を解決します。

| # | エラーのシナリオ | 考えられる原因 | 推奨される解決方法 |
| --- | --- | --- | --- |
| 1. | [ **接続のテスト**] をクリックすると、エラー メッセージが表示されます。 *Active Directory への接続中にエラーが発生しました。オンプレミスのプロビジョニング エージェントが実行されており、正しい Active Directory ドメインで構成されていることを確認してください。* | 通常、このエラーは、プロビジョニング エージェントが実行されていないか、Microsoft Entra ID とプロビジョニング エージェントとの間の通信をブロックしているファイアウォールがある場合に発生します。 ドメインがエージェント ウィザードで構成されていない場合にもこのエラーが表示されることがあります。 | Windows サーバーで *Services* コンソールを開き、エージェントが実行されていることを確認します。 プロビジョニング エージェント ウィザードを開き、正しいドメインがエージェントに登録されていることを確認します。 |
| 2. | プロビジョニング ジョブが金曜から土曜日にかけての週末に保留状態になり、同期にエラーがあるというメール通知を受け取ります。 | このエラーの一般的な原因の 1 つは、スケジュールされている Workday のダウンタイムです。 Workday の実装テナントを使用する場合、Workday には、週末にかけて (通常は金曜日の夜から土曜日の朝まで) スケジュールされた実装テナントのダウンタイムがあり、その期間中は Workday に接続できないため、Workday プロビジョニング アプリが検疫状態になる可能性があります。 Workday 実装テナントがオンラインに戻ると、通常の状態に戻ります。 ごくまれに、テナントの更新により統合システム ユーザーのパスワードが変更された場合、またはアカウントがロックまたは期限切れの状態にある場合にも、このエラーが表示されることがあります。 | Workday 管理者または統合パートナーに連絡して、ダウンタイム期間中に Workday がアラート メッセージを無視するようにダウンタイムをスケジュールし、Workday インスタンスがオンラインに戻ったら可用性を確認します。 |

##### AD ユーザー アカウントの作成エラー

| # | エラーのシナリオ | 考えられる原因 | 推奨される解決方法 |
| --- | --- | --- | --- |
| 1. | プロビジョニング ログでのエクスポート操作の失敗と、*エラー: OperationsError-SvcErr: 操作エラーが発生しました。ディレクトリ サービスに対する上位参照が構成されていません。このため、ディレクトリ サービスは、このフォレスト外のオブジェクトへの参照を発行できません。* | このエラーは、通常、 *Active Directory コンテナー* OU が正しく設定されていない場合、または *parentDistinguishedName* に使用される式マッピングに問題がある場合に表示されます。 | *Active Directory コンテナー* OU パラメーターに入力ミスがないか確認します。 属性マッピングで *parentDistinguishedName を* 使用している場合は、常に AD ドメイン内の既知のコンテナーに評価されることを確認します。 プロビジョニング ログで *Export* イベントを調べて、生成された値を確認します。 |
| 2. | プロビジョニングログにおけるエクスポート操作の失敗のエラーコード: *SystemForCrossDomainIdentityManagementBadResponse* およびメッセージ *エラー: ConstraintViolation-AtrErr: リクエストの値が無効です。属性の値が許容範囲にありませんでした。\nエラーの詳細: CONSTRAINT\_ATT\_TYPE - 会社*。 | このエラーは *会社* の属性に固有のものですが、 *CN* などの他の属性でもこのエラーが表示される場合があります。 このエラーは、AD で適用されたスキーマ制約が原因で発生します。 既定では、AD の *company* や *CN* などの属性の上限は 64 文字です。 Workday に由来する値が 64 文字を超える場合は、このエラー メッセージが表示されます。 | プロビジョニング ログの *Export* イベントを調べて、エラー メッセージで報告された属性の値を確認します。 [Mid](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#mid) 関数を使用して Workday からの値を切り捨てるか、長さの制約が類似していない AD 属性へのマッピングを変更することを検討してください。 |

##### AD ユーザー アカウントの更新エラー

AD ユーザー アカウントの更新プロセス中に、プロビジョニング サービスでは Workday と AD の両方から情報が読み取られ、属性マッピング規則を実行して、変更を反映する必要があるかどうかを判断します。 それに応じて更新イベントがトリガーされます。 これらの手順のいずれかで障害が発生した場合は、プロビジョニング ログに記録されます。 次の表を使用して、一般的な更新エラーを解決します。

| # | エラーのシナリオ | 考えられる原因 | 推奨される解決方法 |
| --- | --- | --- | --- |
| 1. | *EventName = EntrySynchronizationError および ErrorCode = EndpointUnavailable* というメッセージを含むプロビジョニング ログの同期規則アクションの失敗。 | このエラーは、オンプレミスのプロビジョニング エージェントで処理エラーが発生したために、プロビジョニング サービスで Active Directory からユーザー プロファイル データを取得できない場合に発生します。 | プロビジョニング エージェントのイベント ビューアー ログで、読み取り操作に関する問題を示すエラー イベントを確認します (イベント ID 2 でフィルター)。 |
| 2. | AD の特定のユーザーについて、AD の manager 属性が更新されません。 | このエラーで最も可能性が高い原因は、スコープ規則を使用していて、ユーザーのマネージャーがスコープに含まれていないことです。 マネージャーの照合 ID 属性 (EmployeeID など) がターゲット AD ドメインに見つからないか、正しい値に設定されていない場合も、この問題が発生する可能性があります。 | スコープ フィルターを確認し、スコープ内にマネージャー ユーザーを追加します。 AD 内のマネージャーのプロファイルを調べて、照合 ID 属性の値があることを確認してください。 |

### 構成の管理

このセクションでは、Workday 主導のユーザー プロビジョニング構成をさらに拡張、カスタマイズ、および管理する方法について説明します。 次のトピックについて説明します。

- Workday ユーザー属性の一覧のカスタマイズ
- 構成のエクスポートとインポート

#### Workday のユーザー属性リストをカスタマイズする

Active Directory と Microsoft Entra ID の Workday プロビジョニング アプリにはどちらも、Workday のユーザー属性の選択元となる既定のリストが含まれています。 ただしこれらのリストに、すべてが含まれているわけではありません。 Workday は、想定されるさまざまなユーザー属性を数多くサポートしていますが、それらのユーザー属性には、標準の属性と特定の Workday テナントに固有の属性とがあります。

Microsoft Entra プロビジョニング サービスでは、リストまたは Workday 属性をカスタマイズして、人事 API の [Get_Workers](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html) 操作で公開されるすべての属性を含めることができます。

この変更を行うには、 [Workday Studio](https://community.workday.com/studio-download) を使用して、使用する属性を表す XPath 式を抽出し、高度な属性エディターを使用してプロビジョニング構成に追加する必要があります。

**Workday ユーザー属性の XPath 式を取得するには:**

1. [Workday Studio](https://community.workday.com/studio-download) をダウンロードしてインストールします。 インストーラーにアクセスするには、Workday コミュニティ アカウントが必要です。
2. Workday **Web Services ディレクトリ**から、使用する予定の WWS API バージョンに固有の Workday [Human_Resources](https://community.workday.com/sites/default/files/file-hosting/productionapi/index.html) WSDL ファイルをダウンロードします
3. Workday Studio を起動します。
4. コマンド バーから、 **Workday &gt; Test Web Service in Tester オプションを** 選択します。
5. [ **外部]** を選択し、手順 2 でダウンロードしたHuman\_Resources WSDL ファイルを選択します。

    [Image: Workday Studio で開いている]
6. **[場所**] フィールドを`https://IMPL-CC.workday.com/ccx/service/TENANT/Human_Resources`に設定しますが、"IMPL-CC" を実際のインスタンスの種類に置き換え、"TENANT" を実際のテナント名に置き換えます。
7. **操作**を**Get\_Workers**に設定する
8. [要求/応答] ウィンドウの下にある小さな **構成** リンクをクリックして、Workday の資格情報を設定します。 **[認証**] をオンにし、Workday 統合システム アカウントのユーザー名とパスワードを入力します。 必ずユーザー名をname@tenantとして書式設定し、 **WS-Security UsernameToken** オプションを選択したままにします。 [Image: [ユーザー名] と [パスワード] が入力され、[WS-Security ユーザー名トークン] が選択されている [セキュリティ] タブを示すスクリーンショット。]
9. [ **OK] を選択します**。
10. [ **要求** ] ウィンドウで、下の XML を貼り付けます。 **Employee\_ID**を Workday テナントの実際のユーザーの従業員 ID に設定します。 **wd:version** を、使用する予定の WWS のバージョンに設定します。 抽出対象となる属性が設定されているユーザーを選択します。

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <env:Envelope xmlns:env="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="https://www.w3.org/2001/XMLSchema">
      <env:Body>
        <wd:Get_Workers_Request xmlns:wd="urn:com.workday/bsvc" wd:version="v21.1">
          <wd:Request_References wd:Skip_Non_Existing_Instances="true">
            <wd:Worker_Reference>
              <wd:ID wd:type="Employee_ID">21008</wd:ID>
            </wd:Worker_Reference>
          </wd:Request_References>
          <wd:Response_Group>
            <wd:Include_Reference>true</wd:Include_Reference>
            <wd:Include_Personal_Information>true</wd:Include_Personal_Information>
            <wd:Include_Employment_Information>true</wd:Include_Employment_Information>
            <wd:Include_Management_Chain_Data>true</wd:Include_Management_Chain_Data>
            <wd:Include_Organizations>true</wd:Include_Organizations>
            <wd:Include_Reference>true</wd:Include_Reference>
            <wd:Include_Transaction_Log_Data>true</wd:Include_Transaction_Log_Data>
            <wd:Include_Photo>true</wd:Include_Photo>
            <wd:Include_User_Account>true</wd:Include_User_Account>
          <wd:Include_Roles>true</wd:Include_Roles>
          </wd:Response_Group>
        </wd:Get_Workers_Request>
      </env:Body>
    </env:Envelope>
    ```
11. [ **要求の送信** ] (緑色の矢印) をクリックしてコマンドを実行します。 成功した場合は、応答ウィンドウに **応答** が表示されます。 エラーではなく、入力したユーザー ID のデータが応答に含まれていることを確認します。
12. 成功した場合は、[ **応答** ] ウィンドウから XML をコピーし、XML ファイルとして保存します。
13. Workday Studio のコマンド バーで、[ **ファイル] &gt; [ファイルを開く]** を選択し、保存した XML ファイルを開きます。 この操作で、Workday Studio の XML エディターにファイルが開きます。

14. ファイル ツリーで、 **/env: Envelope &gt; env: Body &gt; wd:Get\_Workers\_Response &gt; wd:Response\_Data &gt; wd: Worker** 内を移動して、ユーザーのデータを検索します。
15. **wd: Worker** で、追加する属性を見つけて選択します。
16. 選択した属性の XPath 式を **[ドキュメント パス** ] フィールドからコピーします。
17. コピーした式から **/env:Envelope/env:Body/wd:Get\_Workers\_Response/wd:Response\_Data/** プレフィックスを削除します。
18. コピーした式の最後の項目がノード (例: "/wd: Birth\_Date") の場合は、式の末尾に **/text()** を追加します。 最後の項目が属性 (例: "/@wd: type") の場合、これは不要です。
19. 最終的には、`wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Birth_Date/text()` のようになっている必要があります。 この値は、コピーして Microsoft Entra 管理センターに入力する値です。

**カスタム Workday ユーザー属性をプロビジョニング構成に追加するには:**

1. このチュートリアルで前述したように、 [Microsoft Entra 管理センター](https://entra.microsoft.com)を起動し、Workday プロビジョニング アプリケーションの [プロビジョニング] セクションに移動します。
2. **[プロビジョニングの状態]** を **[オフ**] に設定し、[**保存]** を選択します。 このステップにより、準備が整ったときにだけ変更が適用されるようにできます。
3. [ **マッピング**] で、[ **Workday Worker をオンプレミス Active Directory に同期** する] (または **[Workday Worker を Microsoft Entra ID に同期**する] を選択します)。
4. 次の画面の一番下までスクロールし、[ **詳細オプションの表示**] を選択します。
5. **Workday の [属性リストの編集] を選択します**。
6. 属性リストの一番下にある入力フィールドまでスクロールします。
7. [ **名前]** に、属性の表示名を入力します。
8. [ **種類]** で、属性に適切に対応する型を選択します (**文字列** が最も一般的です)。
9. **[API 式]** に、Workday Studio からコピーした XPath 式を入力します。 例: `wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Birth_Date/text()`
10. [ **属性の追加] を選択します**。

    [Image: Workday Studio のスクリーンショット。]
11. 上の **[保存]** を選択し、ダイアログボックスで **[はい** ] を選択します。 [属性マッピング] 画面がまだ開いている場合は閉じてください。
12. メインの [ **プロビジョニング** ] タブに戻り、[ **Workday Worker をオンプレミスの Active Directory に同期** する ( または **Worker を Microsoft Entra ID に同期**する)] をもう一度選択します。
13. [ **新しいマッピングの追加] を**選択します。
14. これで、新しい属性が **ソース属性** の一覧に表示されます。
15. 必要に応じて新しい属性のマッピングを追加します。
16. 完了したら、**[プロビジョニングの状態]**を**[オン]**に戻して保存してください。

#### 構成のエクスポートとインポート

[プロビジョニング構成のエクスポートとインポートに関する記事を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/export-import-provisioning-configuration)参照してください

### 個人データの管理

Active Directory 用の Workday プロビジョニング ソリューションでは、オンプレミスの Windows サーバー上にプロビジョニング エージェントをインストールする必要があります。このエージェントでは、Workday から AD への属性マッピングに応じて個人データを含む可能性があるログが Windows イベント ログに作成されます。 ユーザーのプライバシー義務を遵守するために、イベント ログを消去する Windows のスケジュールされたタスクを設定することで、48 時間を超えてイベント ログにデータが保持されないようにすることができます。

Microsoft Entra プロビジョニング サービスは、GDPR 分類の **データ プロセッサ** カテゴリに分類されます。 このサービスは、データ プロセッサ パイプラインとして、重要なパートナーやエンド コンシューマーにデータ処理サービスを提供するものです。 Microsoft Entra プロビジョニング サービスは、ユーザー データを生成せず、収集する個人データをどれにするか、およびその用途について、独立した制御はありません。 Microsoft Entra ID プロビジョニング サービスでのデータの取得、集計、分析、およびレポートは、既存のエンタープライズ データに基づいて行われます。

注

個人データの表示または削除の詳細については、GDPR サイトに [対する Windows データ主体の要求](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/gdpr-dsr-windows) に関する Microsoft のガイダンスを確認してください。 GDPR に関する一般的な情報については、 [Microsoft セキュリティ センターの GDPR セクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview) と [Service Trust ポータルの GDPR セクションを参照](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)してください。

データ保持に関しては、Microsoft Entra プロビジョニング サービスでは 30 日を超えてレポートの生成、分析の実行、または分析情報の提供を行いません。 そのため Microsoft Entra プロビジョニング サービスでは、いかなるデータも 30 日間を超えて格納、処理、保持されることはありません。 この設計は、GDPR の規制、Microsoft のプライバシー コンプライアンス規則、および Microsoft Entra ID のデータ リテンション ポリシーに準拠したものです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/provision-workday-to-entra"} -->
## Workday からプロビジョニングされた、Workday での管理対象クラウド ユーザーを管理します。

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-workday-to-entra
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事は、Workday からユーザーとグループをプロビジョニングし、Workday で管理する方法に関するチュートリアルです。

**シナリオ:** シナリオ: Workday からプロビジョニングされた、Workday での管理対象クラウド ユーザーを管理します。

### 概要

ユーザー アカウントをプロビジョニングするために、[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を [Workday Human Resources API](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html) と統合します。 Microsoft Entra のユーザー プロビジョニング サービスでサポートされている Workday ユーザー プロビジョニング ワークフローは、次の人事管理および ID ライフサイクル管理シナリオを自動化します。

- **新しい従業員の雇用** - Workday に新しい従業員が追加されると、Microsoft Entra ID と、必要に応じて Microsoft 365 や [Microsoft Entra ID によってサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)でユーザー アカウントが自動的に作成され、メール アドレスが Workday に書き戻されます。
- **従業員の属性とプロファイルの更新** - Workday で従業員レコード (名前、職名、マネージャーなど) が更新されると、Microsoft Entra ID と、必要に応じて Microsoft 365 や [Microsoft Entra ID によってサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)でユーザー アカウントが自動的に更新されます。
- **従業員の退職** - Workday で従業員が退職状態になると、Microsoft Entra ID と、必要に応じて Microsoft 365 や [Microsoft Entra ID によってサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)でユーザー アカウントが自動的に無効になります。
- **従業員の再雇用** - Workday で従業員が再雇用されると、Microsoft Entra ID と、必要に応じて Microsoft 365 や [Microsoft Entra ID によってサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に以前のアカウントが (設定に応じて) 自動的に再アクティブ化または再プロビジョニングされます。

#### このユーザー プロビジョニング ソリューションが最適な人は誰ですか？

この Workday から Microsoft Entra へのユーザー プロビジョニング ソリューションは、次の場合に最適です。

- Workday ユーザー プロビジョニング用に事前に構築されたクラウドベースのソリューションを求めている組織
- Workday から Microsoft Entra ID への直接ユーザー プロビジョニングを必要とする組織
- Workday から取得したデータを使用してユーザーをプロビジョニングする必要がある組織
- 電子メールに Microsoft 365 を使用している組織

### ソリューションのアーキテクチャ

このセクションでは、クラウド専用のユーザーに向けた、エンド ツー エンドのユーザー プロビジョニング ソリューションのアーキテクチャについて説明します。 2 つの関連するフローがあります。

- **権限がある人事データのフロー - Workday から Microsoft Entra ID へ:** このフローでは、最初に社員イベント (新規雇用、異動、退職など) が Workday で発生し、イベント データはその後、Microsoft Entra ID に移動します。 イベントによっては、Microsoft Entra ID での作成、更新、有効化、無効化の操作に至る可能性があります。
- **書き戻しフロー – オンプレミスの Active Directory から Workday へ:** Active Directory でアカウントの作成が完了すると、Microsoft Entra Connect を介して Microsoft Entra ID との同期が行われ、メール、ユーザー名、電話番号などの情報を Workday に書き戻すことができます。

    [Image: ワークデーのプロビジョニングの概念図]

#### エンド ツー エンドのユーザー データ フロー

1. HR チームは、Workday Employee Central で雇用者トランザクション (現職者/異動者/退職者または新規雇用/異動/退職) を実行します。
2. Microsoft Entra プロビジョニング サービスは、Workday EC からの、スケジュールされた ID の同期を実行し、オンプレミスの Active Directory との同期のために処理する必要がある変更を識別します。
3. Microsoft Entra ID プロビジョニング サービスは、変更を特定し、Microsoft Entra ID のユーザーに対して作成、更新、有効化、無効化の操作を呼び出します。
4. [Workday Writeback](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial) アプリが構成されている場合は、メール、ユーザー名、電話番号などの属性が Microsoft Entra ID から取得されます。
5. Microsoft Entra プロビジョニング サービスによって、メール、ユーザー名、および電話番号が Workday に設定されます。

### デプロイメントの計画立案

Workday から Microsoft Entra ID へのクラウド人事駆動型のユーザー プロビジョニングを構成するには、次のようなさまざまな側面をカバーするかなりの計画が必要です。

- 一致する ID の決定
- 属性マッピング
- 属性の変換
- スコープ フィルター

これらのトピックに関する包括的なガイドラインについては、[クラウド人事デプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)に関するページを参照してください。

### Workday の統合システム ユーザーの構成

Workday 統合システム ユーザー アカウントとワーカー データを取得するためのアクセス許可の作成については、[統合システム ユーザーの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#configure-integration-system-user-in-workday)に関するセクションを参照してください。

### Workday から Microsoft Entra ID へのユーザー プロビジョニングを構成する

以下のセクションでは、クラウドのみのデプロイで Workday から Microsoft Entra ID へのユーザー プロビジョニングを構成する手順について説明します。

- Microsoft Entra プロビジョニング コネクタ アプリケーションの追加と Workday への接続の作成
- Workday と Microsoft Entra の属性マッピングを構成する
- ユーザー プロビジョニングの有効化と起動

#### パート 1: Microsoft Entra プロビジョニング コネクタ アプリケーションの追加と Workday への接続の作成

**クラウド専用ユーザーの Workday から Microsoft Entra へのプロビジョニングを構成するには、次のようにします。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[Workday to Microsoft Entra user Provisioning] (Workday から Microsoft Entra へのユーザー プロビジョニング)** を探し、ギャラリーからそのアプリを追加します。
4. アプリが追加され、アプリの詳細画面が表示されたら、 **[プロビジョニング]** を選択します。
5. **[プロビジョニング** **モード]** を **[自動]** に変更します。
6. 以下のように **[管理者の資格情報]** セクションを完了します。

    - **Workday ユーザー名** – Workday 統合システム アカウントのユーザー名にテナント ドメイン名を追加して入力します。 このようになります。username@contoso4
    - **Workday パスワード** - Workday 統合システム アカウントのパスワードを入力します
    - **Workday Web Services API URL** - テナントの Workday Web サービス エンドポイントへの URL を入力します。 URL によって、コネクタで使用される Workday Web Services API のバージョンが決まります。

        | URL 形式 | 使用される WWS API のバージョン | XPATH の変更が必要 |
        | --- | --- | --- |
        | https://####.workday.com/ccx/service/tenantName | v21.1 | いいえ |
        | https://####.workday.com/ccx/service/tenantName/Human\_Resources | v21.1 | いいえ |
        | https://####.workday.com/ccx/service/tenantName/Human\_Resources/v##.# | v##.# | はい |

        メモ

        URL にバージョン情報が指定されていない場合、アプリでは Workday Web Services (WWS) v21.1 が使用され、アプリに付属している既定の XPATH API 式の変更は必要ありません。 特定の WWS API バージョンを使用するには、URL の中にバージョン番号を指定します  例: `https://wd3-impl-services1.workday.com/ccx/service/contoso4/Human_Resources/v34.0` WWS API v30.0 以降を使用する場合は、プロビジョニング ジョブを有効にする前に、「**構成の管理**」セクションおよび &gt;を参照して、[\[属性マッピング\] -&gt; \[詳細オプション\] - \[Workday の属性リストの編集\]](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#managing-your-configuration) の下にある [\[XPATH API 式\]](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference#xpath-values-for-workday-web-services-wws-api-v30) を更新してください。
    - **メール通知** - メール アドレスを入力し、[send email if failure occurs](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/失敗した場合にメールを送信する) チェックボックスをオンにします。
    - **[接続のテスト]** ボタンをクリックします。
    - 接続テストが成功した場合、上部の **[保存]** ボタンをクリックします。 失敗した場合は、Workday URL と資格情報が Workday で有効であることを再度確認します。

#### パート 2: Workday と Microsoft Entra の属性マッピングを構成する

このセクションでは、クラウド専用のユーザーについて、Workday から Microsoft Entra ID へのユーザー データの移動方法を構成します。

1. **[マッピング]** の [プロビジョニング] タブで、**[Microsoft Entra ID に対するワーカーの同期]** をクリックします。
2. **[ソース オブジェクト スコープ]** フィールドでは、属性ベースのフィルター セットを定義して、Microsoft Entra ID へのプロビジョニングの対象にする Workday のユーザー セットを選択できます。 既定のスコープは、"Workday のすべてのユーザー" です。 フィルターの例:

    - 例: 1000000 から 2000000 までの Worker ID を持つユーザーにスコープを設定

        - 属性:従業員ID
        - 演算子:REGEXマッチ
        - 値: (1[0-9][0-9][0-9][0-9][0-9][0-9])
    - 例:正規従業員ではなく、臨時社員のみ

        - 属性: ContingentID
        - 演算子: IS NOT NULL
3. **[対象オブジェクトのアクション]** フィールドでは、Microsoft Entra ID で実行されるアクションをグローバルにフィルター処理できます。 **作成**と**更新**が最も一般的です。
4. **[属性マッピング]** セクションでは、個別の Workday 属性を Active Directory の属性にマッピングする方法を定義できます。
5. 既存の属性マッピングをクリックして更新するか、または画面の下部にある **[新しいマッピングの追加]** をクリックして、新しいマッピングを追加します。 個々の属性マッピングは、次のプロパティをサポートしています。

    - **マッピングの種類**

        - **ダイレクト** - 変更なしで Workday 属性の値を AD 属性に書き込みます
        - **定数** - 静的な定数文字列の値を AD 属性に書き込みます
        - **式** – 1 つ以上の Workday 属性に基づいて、AD 属性にカスタム値を書き込むことができます。 [詳細については、式に関するこの記事を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)。
    - **ソース属性** - Workday のユーザー属性。 探している属性が存在しない場合は、「[Workday のユーザー属性リストをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#customizing-the-list-of-workday-user-attributes)」を参照してください。
    - **既定値** – 省略可能。 ソース属性に空の値がある場合、マッピングではこの値が代わりに書き込まれます。 最も一般的な構成では、これを空白のままにします。
    - **ターゲット属性** – Microsoft Entra ID のユーザー属性。
    - **この属性を使用してオブジェクトを照合する** - この属性を使用して、Workday と Microsoft Entra ID 間でユーザーを一意に識別するかどうかを示します。 この値は、通常、Workday の Worker ID フィールドで設定され、一般的に Microsoft Entra ID の従業員 ID 属性 (新規) または拡張属性にマッピングされます。
    - **照合の優先順位** - 一致させる属性を複数設定できます。 複数の場合は、このフィールドで定義された順序で評価されます。 1 件でも一致が見つかると、一致する属性の評価はそれ以上行われません。
    - **このマッピングを適用する**

        - **常に** - このマッピングをユーザーの作成と更新の両方のアクションに適用します
        - **作成中のみ** - このマッピングをユーザーの作成アクションのみに適用します
6. マッピングを保存するには、[属性マッピング] セクションの上部にある **[保存]** をクリックします。

### ユーザー プロビジョニングの有効化と起動

Workday プロビジョニング アプリの構成が完了したら、プロビジョニング サービスを有効にすることができます。

ヒント

既定では、プロビジョニング サービスを有効にすると、スコープ内のすべてのユーザーに対してプロビジョニング操作が開始されます。 マッピングのエラーまたは Workday データの問題がある場合、プロビジョニング ジョブが失敗し、検疫状態になる可能性があります。 これを避けるために、ベスト プラクティスとして、すべてのユーザーの完全同期を開始する前に、**[ソース オブジェクト スコープ]** フィルターを構成し、少数のテスト ユーザーで属性マッピングをテストすることをお勧めします。 マッピングが機能し、目的の結果が得られていることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

1. **[プロビジョニング]** タブで、 **[プロビジョニングの状態]** を **[ON]** に設定します。
2. **[保存]** をクリックします。
3. この操作により初期同期が開始されます。これに要する時間は Workday テナントのユーザー数に応じて変わります。 進行状況バーをチェックして、同期サイクルの進行状況を追跡できます。
4. 好きなときに、Microsoft Entra 管理センターの **[プロビジョニング]** タブをチェックして、プロビジョニング サービスで実行されたアクションを確認します。 プロビジョニング ログには、Workday から読み込まれたユーザーや、その後 Microsoft Entra ID に追加または更新されたユーザーなど、プロビジョニング サービスによって実行された個々の同期イベントがすべて表示されます。
5. 最初の同期が完了すると、次に示すように、 **[プロビジョニング]** タブに監査概要レポートが書き込まれます。

[Image: プロビジョニングの進捗状況バーのスクリーンショット]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/scenarios/sap-template"} -->
## ECMA2Host 用 SAP ECC 7 テンプレートの作成

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/sap-template
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事では、SAP ECC ユーザーを管理するための Web サービス ECMA コネクタ用のテンプレートを作成する方法について説明します。

このガイドでは、SAP ECC ユーザーを管理するための Web サービス Extensibility Connectivity Management Agent (ECMA) コネクタ用のテンプレートを作成するプロセスについて説明します。

### 制限事項と前提

このテンプレートでは、ユーザーを管理する方法を示します。 ECMA2Host は現在、複数値参照をサポートしていないため、Local Activity Groups、Roles、Profiles などの他のオブジェクトの種類については、このガイドでは説明しません。 パスワード操作も、このガイドの範囲外です。

このガイドでは、公開されている BAPI 関数の呼び出しに使用される SAP 内のサービス アカウントの作成については説明しません。 開発者事前に作成されたデモ アカウントが、この記事で説明する BAPI へのアクセス許可を付与するプロファイル RFC\_ALL と共に使用されることを前提としています。

Web サービス構成ツールでは、既定で SAP で公開されている WSP ポリシーとエンドポイントごとの複数のバインドの機能はサポートされていません。 SOAP 1.1 のみの WSDL と、ポリシーなしのオールインワン ドキュメント スタイル バインドが必要です。

このテンプレートで使用される SAP ECC BAPI 関数:

- BAPI\_USER\_GETLIST - このシステムに接続されているすべてのユーザーの一覧を取得します。
- BAPI\_USER\_GETDETAIL - 特定のユーザーの詳細を取得します。
- BAPI\_USER\_CREATE1 - ユーザーを作成します。
- BAPI\_USER\_DELETE - ユーザーを削除します。
- BAPI\_USER\_CHANGE - ユーザーを更新します。

このガイドのすべての SAP ユーザー プロパティは、単一値のプロパティとして扱われます。

使用されるプログラミング言語は Visual Basic です。

### Web サービス エンドポイントの定義とスキーマの作成

インポート ワークフローやエクスポート ワークフローを設計する前に、テンプレートを作成し、SOAP インターフェイス経由で公開される SAP BAPI 関数を使用してエンドポイントを定義する必要があります。 次に、ECMA2 オブジェクトのスキーマを作成し、そのプロパティをこのテンプレートで使用できます。

1. "C:\Program Files\Microsoft ECMA2Host\Web サービス構成ツール" フォルダーから、Web サービス構成ツールを起動 **wsconfigTool.exe**
2. File-New メニューの [Create new SOAP Project]\(新しい SOAP プロジェクトの作成\) を選択します

[Image: SOAP プロジェクトの作成のスクリーンショット。]

1. [SOAP プロジェクト] を選択し、[新しい Web サービスの追加] を選択します。

[Image: 新しい Web サービスの追加のスクリーンショット。]

1. Web サービスに「SAPECC」という名前を付け、発行された WSDL をダウンロードするための URL を指定し、名前空間として「SAPECC」と入力します。 Web サービス名は、テンプレート内でこの Web サービスを他の Web サービスと区別するのに役立ちます。 名前空間は、クラスの生成に使用される Microsoft .NET 名前空間の名前を定義します。 SAP 管理者の指示がない限り、基本認証モードを選択します。 [次へ] を選択します。

[Image: Web サービスの名前付けのスクリーンショット。]

1. SAP ECC エンドポイントに接続するための資格情報を指定します。 [次へ] を選択します。
2. エンドポイントと操作ページで、BAPI が表示されていることを確認し、[完了] を選択します。

Note

複数のエンドポイントが表示される場合は、SOAP 1.2 と SOAP 1.1 の両方のバインドが有効になります。 これにより、コネクタが失敗します。 SOAMANAGER でバインド定義を変更し、1 つだけ保持します。 次に、Web サービスを追加し直します。

[Image: BAPI のスクリーンショット。]

1. プロジェクトを C:\Program Files\Microsoft ECMA2Host\Service\ECMA フォルダーに保存します。
2. [オブジェクトタイプ]タブを選択し、ユーザオブジェクトタイプを追加することを選択します。 [OK] を選択します。
3. [オブジェクトの種類] タブを展開し、[ユーザーの種類の定義] を選択します。

[Image: オブジェクトの種類のスクリーンショット。]

1. スキーマに次の属性を追加し、アンカーとして userName を選択します。

[Image: 属性の追加のスクリーンショット。]

1. プロジェクトを保存します。

| 名前 | タイプ | アンカー |
| --- | --- | --- |
| city | 文字列 |  |
| 会社 | 文字列 |  |
| 部署 | 文字列 |  |
| メール | 文字列 |  |
| expirationTime | 文字列 |  |
| firstName | 文字列 |  |
| lastName | 文字列 |  |
| middleName | 文字列 |  |
| telephoneNumber | 文字列 |  |
| jobTitle | 文字列 |  |
| userName | 文字列 | checked |

### フル インポート ワークフローの作成

インポート ワークフローは、ECMA2Host では省略可能ですが、既存の SAP ユーザーを ECMA2Host メモリ内キャッシュにインポートし、プロビジョニング中に重複するユーザーが作成されないようにすることができます。

インポート ワークフローを作成しない場合、コネクタはエクスポート専用モードで動作し、ECMA2Host は常にユーザー の作成操作 発行します(既存のユーザーに対しても)。 これにより、エクスポート ワークフローで重複が処理されない限り、標準 SAP BAPI を使用すると、エラーや重複が発生する可能性があります。

SAP ECC には、前回の読み取り以降に行われた変更を読み取るための組み込みメカニズムは用意されていません。

そのため、フル インポート ワークフローのみを実装しています。 パフォーマンス上の理由から差分インポートを実装する必要がある場合は、SAP 管理者に BAPI の一覧を確認し、SOAP Web サービスとして公開してください。 次に、説明されている次のアプローチと、前回成功した実行のタイムスタンプを含む customData プロパティを使用して、差分インポート ワークフローを実装します。

SAP ECC には、プロパティを持つユーザーの一覧を取得するための BAPI 関数がいくつか用意されています。

- BAPI\_USER\_GETLIST - このシステムに接続されているすべてのユーザーの一覧を取得します。
- BAPI\_USER\_GETDETAIL - 特定のユーザーの詳細を取得します。

このテンプレートで SAP ECC から既存のユーザーを取得するには、これら 2 つの BAPI のみが使用されます。

1. [オブジェクトの種類] -&gt; [ユーザー] -&gt; [インポート] -&gt; [フル インポート] ワークフローに移動し、右側の Sequence アクティビティを [ワークフロー デザイナー] ウィンドウにドラッグ アンド ドロップします。
2. 左下にある [変数] ボタンを見つけて選択し、このシーケンス内で定義されている変数の一覧を展開します。
3. 次の変数を追加します。 SAP WSDL から生成された変数型を選択するには、[Browse for Types] を選択し、生成された  を展開してから、SAPECC 名前空間を展開します。

| 名前 | 変数の種類 | Scope | 既定値 |
| --- | --- | --- | --- |
| selRangeTable | SAPECC.TABLE\_OF\_BAPIUSSRGE | シークエンス | 新しい TABLE\_OF\_BAPIUSSRGE が {.item = new BAPIUSSRGE(){new BAPIUSSRGE}} を使用して作成されます。 |
| getListRetTable | SAPECC.TABLE\_OF\_BAPIRET2 | シークエンス | 新しいTABLE\_OF\_BAPIRET2 |
| PageSize | Int32 | シークエンス | 200 |
| returnedSize | Int32 | シークエンス |  |
| usersTable | SAPECC.TABLE\_OF\_BAPIUSNAME | シークエンス | new TABLE\_OF\_BAPIUSNAME() |

[Image: フル インポート操作ワークフローのスクリーンショット。]

1. ツールボックスから、シーケンス アクティビティ内の 4 つの Assign アクティビティをドラッグ アンド ドロップし、次の値を設定します。

```
selRangeTable.item(0).PARAMETER = "USERNAME" 
selRangeTable.item(0).SIGN = "I" selRangeTable.item(0).OPTION = "GT" selRangeTable.item(0).LOW = ""   
```

これらのパラメーターは、BAPI\_USER\_GETLIST 関数の呼び出しと改ページ位置の自動修正の実装に使用されます。

[Image: フル インポート ワークフローのスクリーンショット。]

1. 改ページ位置の自動修正を実装するには、最後の Assign 操作の後に、ツールボックスから、Sequence アクティビティ内の DoWhile アクティビティをドラッグ アンド ドロップします。
2. 右側のウィンドウで [プロパティ] タブに切り替え、DoWhile にこの条件を入力します

- サイクル: `returnedSize = pageSize`

[Image: [returnedsize] 画面のスクリーンショット。]

1. 変数を選択し、既定値が 0 の DoWhile サイクル内に int32 型の currentPageNumber プロパティを追加します。

[Image: dowhile 画面のスクリーンショット。]

1. 省略可能な手順: Delta インポート ワークフローを実装する予定の場合は、DoWhile サイクルの後に、ツールボックスから Assign アクティビティを Sequence アクティビティにドラッグ アンド ドロップします。 次の値を設定します:

- `customData(schemaType.Name + "_lastImportTime") = DateTimeOffset.UtcNow.Ticks.ToString()` これにより、最後の完全インポートが実行された日付と時刻が保存され、このタイムスタンプは後で Delta Import ワークフローで使用できます。

[Image: customdata 画面のスクリーンショット。]

1. ツールボックスから、DoWhile アクティビティ内の Sequence アクティビティをドラッグ アンド ドロップします。 その Sequence アクティビティに WebServiceCall アクティビティをドラッグ アンド ドロップし、SAPECC サービス名、ZSAPCONNECTORWS エンドポイント、BAPI\_USER\_GETLIST 操作を選択します。

[Image: dowhile シーケンスのスクリーンショット。]

1. [引数] ボタンを選択して、次のように Web サービス呼び出しのパラメーターを定義します。

| 名前 | 方向 | タイプ | 値 |
| --- | --- | --- | --- |
| MAX\_ROWS | In | Int32 | PageSize |
| MAX\_ROWSSpecified | In | ブール値 | True |
| RETURN | /アウトの選択 | TABLE\_OF\_BAPIRET2 | getListRetTable |
| SELECTION\_EXP | /アウトの選択 | TABLE\_OF\_BAPIUSSEXP |  |
| SELECTION\_RANGE | /アウトの選択 | TABLE\_OF\_BAPIUSSRGE | selRangeTable |
| USERLIST | /アウトの選択 | TABLE\_OF\_BAPIUSNAME | usersTable |
| WITH\_USERNAME | In | String |  |
| ROWS | Out | Int32 | returnedSize |

1. [OK] を選択します。 警告記号が表示されなくなります。 usersTable 変数に格納されているユーザーの一覧。 SAP は 1 回の応答でユーザーの完全なリストを返さないため、ページの切り替え中に改ページ位置の自動修正を実装し、この関数を複数回呼び出す必要があります。 その後、インポートされたすべてのユーザーについて、別の呼び出しを行って、そのユーザーの詳細を取得する必要があります。 つまり、ユーザー数が 1,000 人でページ サイズが 200 のランドスケープの場合、Web サービス コネクタは 5 回の呼び出しを行ってユーザーのリストを取得し、1,000 回の個別呼び出しを行ってユーザーの詳細を取得します。 パフォーマンスを向上させるには、SAP チームに、すべての用途とそのプロパティを一覧表示するカスタム BAPI プログラムを開発するよう依頼します。 これにより、1,000 個の個別の呼び出しを行う必要が回避され、その BAPI 関数が SOAP WS エンドポイント経由で公開されます。
2. ツールボックスから、WebServiceCall アクティビティの後に DoWhile アクティビティ内の IF アクティビティをドラッグ アンド ドロップします。 空ではない応答とエラーがないことを確認するには、次の条件を指定します: `IsNothing(getListRetTable.item) OrElse getListRetTable.item.Count(Function(errItem) errItem.TYPE.Equals("E") = True) = 0`
3. ツールボックスから、Throw アクティビティを IF アクティビティの Else ブランチにドラッグ アンド ドロップして、インポートに失敗した場合にエラーをスローします。 [プロパティ] タブに切り替え、Throw アクティビティの Exception プロパティに次の式を入力します: `New Exception(getListRetTable.item.First(Function(retItem) retItem.TYPE.Equals("E")).MESSAGE)`

[Image: Exception プロパティのスクリーンショット。]

1. インポートされたユーザーのリストを処理するには、ForEachWithBodyFactory アクティビティをツールボックスから IF アクティビティの Then ブランチにドラッグ アンド ドロップします。 [プロパティ] タブに切り替えて、TypeArgument として SAPECC.BAPIUSNAME を選択します。 [...] ボタンを選択し、values プロパティに次の式を入力します: `if(usersTable.item,Enumerable.Empty(of BAPIUSNAME)())`

[Image: IF アクティビティのスクリーンショット。]

1. ツールボックスから、ForEach アクティビティ内の Sequence アクティビティをドラッグ アンド ドロップします。 このシーケンス アクティビティ ウィンドウをアクティブにして、[変数] ボタンを選択し、次の変数を定義します。

| 名前 | 変数の種類 | Scope | 既定値 |
| --- | --- | --- | --- |
| 会社 | SAPECC.BAPIUSCOMP | シークエンス | new BAPIUSCOMP() |
| address | SAPECC.BAPIADDR3 | シークエンス | new BAPIADDR3() |
| defaults | SAPECC.BAPIDEFAUL | シークエンス | new BAPIDEFAUL() |
| logondata | SAPECC.BAPILOGOND | シークエンス | new BAPILOGOND() |
| getDetailRetTable | SAPECC.TABLE\_OF\_BAPIRET2 | シークエンス | new TABLE\_OF\_BAPIRET2() |

IF アクティビティは次のように表示されます。

[Image: Foreach を含む IF アクティビティのスクリーンショット。]

1. CreateCSEntryChangeScope アクティビティを Sequence アクティビティにドラッグ アンド ドロップします。 DN プロパティに、schemaType.Name & の項目を入力します。USERNAME。 [CreateAnchorAttribute AnchorValue] フィールドに、item.username と入力します。

[Image: CreateCSEntryChangeScope のスクリーンショット。]

1. 各ユーザーの詳細を取得するには、ツールボックスから、CreateAnchorAttribute アクティビティの直前の Sequence アクティビティに WebServiceCall アクティビティをドラッグ アンド ドロップします。 SAPECC サービス名、ZSAPCONNECTORWS エンドポイント、BAPI\_USER\_GET\_DETAIL 操作を選択します。 [引数] ボタンを選択して、次のように Web サービス呼び出しのパラメーターを定義します。

| 名前 | 方向 | タイプ | 値 |
| --- | --- | --- | --- |
| RETURN | /アウトの選択 | TABLE\_OF\_BAPIRET2 | getDetailRetTable |
| USERNAME | In | String | item.username |
| 住所 | Out | BAPIADDR3 | address |
| COMPANY | Out | BAPIUSCOMP | 会社 |
| DEFAULTS | Out | BAPIUSDEFAUL | defaults |
| LOGONDATA | Out | BAPILOGOND | logonData |
| WITH\_USERNAME | In | String |  |
| ROWS | Out | Int32 | returnedSize |

1. [OK] を選択します。 警告記号が表示されなくなります。 ユーザーの詳細は、上記に一覧表示された変数に格納されます。 IF アクティビティは次のように表示されます。

[Image: パラメーターのスクリーンショット。]

1. BAPI\_USER\_GET\_DETAIL 操作の結果を確認するには、ツールボックスから、IF アクティビティをドラッグ アンド ドロップし、WebServiceCall アクティビティと CreateAnchorAttribute アクティビティの間にある Sequence アクティビティに配置します。 次の条件を入力します: `IsNothing(getDetailRetTable.item) OrElse getDetailRetTable.item.Count(Function(errItem) errItem.TYPE.Equals("E") = True) = 0`

見つからないユーザーの詳細は致命的なイベントとして扱われる必要はないため、このエラーを表示し、他のユーザーの処理を続行できます。 Sequence アクティビティを IF アクティビティの Else ブランチにドラッグ アンド ドロップします。 新しい Sequence アクティビティに Log アクティビティを追加します。 [プロパティ] タブに切り替え、レベル プロパティを [高] に、[タグ] を [追跡] に変更します。 LogText プロパティに次を入力します: `string.Join("\n", getDetailRetTable.item.Select (Function(item) item.MESSAGE ))`

1. Sequence アクティビティを IF アクティビティの Then ブランチにドラッグ アンド ドロップします。 既存の CreateAnchorAttribute アクティビティを、IF アクティビティの Then ブランチ内の Sequence アクティビティにドラッグ アンド ドロップします。 ForEach アクティビティは次のように表示されます。

[Image: ForEach のスクリーンショット。]

1. city、company、department、email などのユーザーの各プロパティについては、CreateAnchorAttribute アクティビティの後に IF アクティビティを追加し、`Not string.IsNullOrEmpty(address.city)` などの条件を入力し、その IF アクティビティの Then ブランチに CreateAttributeChange アクティビティを追加して、空ではない値を確認します。

[Image: CreateAttributeChange のスクリーンショット。]

たとえば、次のマッピング テーブルを使用して、すべてのユーザー プロパティに CreateAttributeChange アクティビティを追加します。

| ECMA User プロパティ | SAP プロパティ |
| --- | --- |
| city | address.city |
| 部署 | address.department |
| 会社 | company.company |
| メール | address.e\_mail |
| firstName | address.firstName |
| lastName | address.lastName |
| middleName | address.middleName |
| jobTitle | address.function |
| expirationTime | logonData.GLTGB |
| telephoneNumber | address.TEL1\_NUMBR |

1. 最後に、SetImportStatusCode アクティビティを最後の CreateAttributeChange アクティビティの後に追加します。 Then ブランチで ErrorCode を Success に設定します。 もう 1 つの SetImportStatus コード アクティビティを Else ブランチに追加し、ErrorCode を ImportErrorCustomContinueRun に設定します。

[Image: SetImportStatusCode のスクリーンショット。]

1. DoWhile サイクルが次のようになるように、ForEach アクティビティ内の Sequence アクティビティを折りたたみます。

[Image: DoWhile サイクルのスクリーンショット。]

1. ユーザーの次のページを取得するには、`selRangeTable.item(0).LOW` プロパティを更新します。 IF アクティビティを DoWhile 内の Sequence アクティビティにドラッグ アンド ドロップします。 既存の IF アクティビティの後に配置します。 条件として、returnedSize&gt;0 と入力します。 IF アクティビティの Then ブランチに Assign アクティビティを追加し、`selRangeTable.item(0).LOW` を `usersTable.item(returnedSize-1).username` に設定します。

[Image: DoWhile final のスクリーンショット。]

Full Import ワークフローの定義を完了しました。

### Export Add ワークフローの作成

SAP ECC でユーザーを作成するには、BAPI\_USER\_CREATE1 プログラムを呼び出し、アカウント名と初期パスワードを含むすべてのパラメーターを指定します。 SAP 側でアカウント名を生成する必要がある場合は、SAP 管理者に相談し、新しく作成されたユーザー アカウントの userName プロパティを返すカスタム BAPI 関数を使用します。

このガイドでは、ライセンス、ローカルまたはグローバルのアクティビティ グループ、システム、またはプロファイルの割り当てについては説明していません。 SAP 管理者に相談し、必要に応じてこのワークフローを変更してください。

Exprt ワークフローに改ページ位置の自動修正を実装する必要はありません。 ワークフロー コンテキスト内で使用できる objectToExport オブジェクトは 1 つのみです。

1. [オブジェクトの種類] -&gt; [ユーザー] -&gt; [エクスポート] -&gt; [追加] ワークフローに移動し、右側のツールボックスから、Sequence アクティビティをワークフロー デザイナー ウィンドウにドラッグ アンド ドロップします。
2. 左下にある [変数] ボタンを見つけて選択し、このシーケンス内で定義されている変数の一覧を展開します。
3. 次の変数を追加します。 SAP WSDL から生成された変数型を選択するには、[Browse for Types] を選択し、生成された  を展開してから、SAPECC 名前空間を展開します。 これにより、BAPI\_USER\_CREATE1 プログラムで使用されるデータ構造が初期化されます。

| 名前 | 変数の種類 | Scope | 既定値 |
| --- | --- | --- | --- |
| address | SAPECC.BAPIADDR3 | シークエンス | new BAPIADDR3() |
| userName | String | シークエンス |  |
| パスワード | SAPECC.BAPIPWD | シークエンス | new BAPIPWD() |
| 会社 | SAPECC.BAPIUSCOMP | シークエンス | new BAPIUSCOMP() |
| defaults | SAPECC.BAPIDEFAUL | シークエンス | new BAPIDEFAUL() |
| logOnData | SAPECC.BAPILOGOND | シークエンス | new BAPILOGOND() |
| bapiret2Table | SAPECC.TABLE\_OF\_BAPIRET2 | シークエンス | new TABLE\_OF\_BAPIRET2() |

[Image: Export Add ワークフローのスクリーンショット。]

1. userName プロパティを不変 ID であるアンカーとして定義したので、エクスポート オブジェクトのアンカーのコレクションから userName 値を抽出する必要があります。 ForEachWithBodyFactory アクティビティを、ツールボックスから Sequence アクティビティにドラッグ アンド ドロップします。 **item** の変数名を **anchor** に置き換え、[プロパティ] に切り替えて、`Microsoft.MetadirectoryServices.AnchorAttribute` の TypeArgument を選択します。 [値] フィールドに「`objectToExport.AnchorAttributes`」と入力します。

[Image: Export add シーケンスのスクリーンショット。]

1. userName アンカーの文字列の値を抽出するには、ForEach アクティビティに Switch アクティビティをドラッグ アンド ドロップします。 ポップアップ ウィンドウで、スイッチ `Microsoft.IdentityManagement.MA.WebServices.Activities.Extensions.AnchorAttributeNameWrapper` 種類を選択します。 次の式の値を入力します: New AnchorAttributeNameWrapper(anchor.Name)
2. [スイッチ] アクティビティの [新しいケース領域の追加] を選択します。 Case の値として userName を入力します。 Assign アクティビティを userName ケースの本文にドラッグ アンド ドロップし、userName 変数に anchor.Value.ToString() を割り当てます。

[Image: new case のスクリーンショット。]

1. これで、エクスポートされたオブジェクト アンカー プロパティから userName 値を抽出したので、他の SAP ユーザーの詳細を含む company、defaults、address、logon data など、他の構造を入力する必要があります。 これを行うには、属性の変更のコレクションを循環させます。
2. ForEach アクティビティを折りたたみ、既存の ForEach アクティビティの後の Sequence アクティビティに、別の ForEachWithBothFactory アクティビティをドラッグ アンド ドロップします。 **item** 変数名を **attributeChange** に置き換え、[プロパティ] に切り替えて、`Microsoft.MetadirectoryServices.AttributeChange` の TypeArgument を選択します。 [値] フィールドに「`objectToExport.AttributeChanges`」と入力します。

[Image: 新しい Sequence のスクリーンショット。]

1. ForEach アクティビティの Body に Switch アクティビティをドラッグ アンド ドロップします。
2. ポップアップ メニューで[`Microsoft.IdentityManagement.MA.WebServices.Activities.Extensions.AttributeNameWrapper`]を選択し、[OK]を選択します。
3. 次の式を入力します: New AttributeNameWrapper(attributeChange.Name) Switch アクティビティの右上隅に、スキーマで定義され、プロパティに割り当てられていない未処理の属性に関する警告アイコンが表示されます。
4. [切り替え] アクティビティの [新しいケース領域の追加] を選択し、ケース値として **を入力し、市区町村**を指定します。
5. このケースの Body に、Assign アクティビティをドラッグ アンド ドロップします。 address.city に `attributeChange.ValueChanges(0).Value.ToString()` を割り当てます。

[Image: New export とワークフローのスクリーンショット。]

1. その他の不足しているケースと割り当てを追加します。 このマッピング テーブルをガイドとして使用してください。

| ケース | 譲渡 |
| --- | --- |
| city | address.city = attributeChange.ValueChanges(0)Value.ToString() |
| 会社 | company.company = attributeChange.ValueChanges(0)Value.ToString() |
| 部署 | address.department = attributeChange.ValueChanges(0)Value.ToString() |
| メール | address.e\_mail = attributeChange.ValueChanges(0)Value.ToString() |
| expirationTime | logOnData.GLTGB = attributeChange.ValueChanges(0)Value.ToString() |
| firstname | address.firstname = attributeChange.ValueChanges(0)Value.ToString() |
| lastName | address.lastname = attributeChange.ValueChanges(0)Value.ToString() |
| middleName | address.middlename = attributeChange.ValueChanges(0)Value.ToString() |
| telephoneNumber | 住所。TEL1\_Numbr = attributeChange.ValueChanges(0)Value.ToString() |
| jobTitle | address.function = attributeChange.ValueChanges(0)Value.ToString() |
| export\_password | パスワード。BAPIPWD1 = attributeChange.ValueChanges(0)Value.ToString() |

ここでは、export\_passwordは、常にスキーマで定義され、作成されるユーザーの初期パスワードを渡すために使用できる特殊な仮想属性です。

[Image: ケースのスクリーンショット。]

1. ForEach アクティビティを折りたたみ、2 番目の ForEach アクティビティの後のシーケンス アクティビティに IF アクティビティをドラッグ アンド ドロップして、「ユーザー要求の作成」を送信する前に、ユーザーのプロパティを検証します。 空でない値は、ユーザー名、姓、初期パスワードの 3 つ以上必要です。 次の条件を入力します: `(String.IsNullOrEmpty(address.lastname) = False ) AND (String.IsNullOrEmpty(userName) = False) AND (String.IsNullOrEmpty(password.BAPIPWD1) = False)`
2. IF アクティビティの Else ブランチで、不足している内容に応じて異なるエラーをスローするため、もう 1 つの IF アクティビティを追加します。 条件値 String.IsNullOrEmpty(userName) を入力します。 `CreateCSEntryChangeResult` アクティビティを 2 番目の IF アクティビティの両方のブランチにドラッグ アンド ドロップし、`ExportErrorMissingAnchorComponent` と`ExportErrorMissingProvisioningAttribute` の ErrorCode を設定します。

[Image: 2 番目の IF アクティビティのスクリーンショット。]

1. 最初の IF アクティビティの空の Then ブランチに Sequence アクティビティをドラッグ アンド ドロップします。 Sequence アクティビティ内で WebSeviceCall アクティビティをドラッグ アンド ドロップします。 SAPECC サービス名、ZSAPCONNECTORWS エンドポイント、およびBAPI\_USER\_CREATE1 操作を選択します。 [引数] ボタンを選択して、次のように Web サービス呼び出しのパラメーターを定義します。

| 名前 | 方向 | タイプ | 値 |
| --- | --- | --- | --- |
| 住所 | In | BAPIADDR3 | address |
| COMPANY | In | BAPIUSCOMP | 会社 |
| DEFAULTS | In | BAPIDEFAUL | defaults |
| LOGONDATA | In | BAPILOGOND | logOnData |
| PASSWORD | In | BAPIPWD | パスワード |
| RETURN | In-Out | TABLE\_OF\_BAPIRET2 | bapiret2Table |
| SELF\_REGISTER | In | String | "X" |
| USERNAME | In | String | userName |

1. [OK] を選択します。 警告記号が表示されなくなります。

[Image: パラメーターの後のワークフローのスクリーンショット。]

1. ユーザー要求の作成結果を処理するには、WebServiceCall アクティビティの後の Sequence アクティビティ内で IF アクティビティをドラッグ アンド ドロップします。 次の条件を入力します: `IsNothing (bapiret2Table.item) OrElse bapiret2Table.item.Count(Function(errItem) errItem.TYPE.Equals("E") = True) <> 0`
2. エラーが発生しない場合は、エクスポート操作が正常に完了したと想定し、成功状態の CSEntryChangeResult を作成して、このオブジェクトのエクスポートが成功したことを示します。 CreateCSEntryChangeResult アクティビティを IF アクティビティの Else ブランチにドラッグ アンド ドロップし、成功エラー コード を選択します。
3. 省略可能: Web サービス呼び出しが、ユーザーの生成されたアカウント名を返す場合は、エクスポートされたオブジェクトのアンカー値を更新する必要があります。 これを行うには、`CreateAttrubuteChange` アクティビティ内の `CreateCSEntryChangeResult` アクティビティをドラッグ アンド ドロップし、[ユーザー名の追加] を選択します。 次に、`CreateValueChange` アクティビティ内の `CreateAttributeChange` アクティビティをドラッグ アンド ドロップし、Web サービス呼び出しアクティビティによって設定された変数名を入力します。 このガイドでは、エクスポート時に更新されない userName 変数を使用します。

[Image: 更新されたシーケンス フローのスクリーンショット。]

1. エクスポートの追加ワークフローの最後の手順は、エクスポート エラーを処理してログに記録することです。 シーケンス アクティビティを IF アクティビティの空の Then ブランチにドラッグ アンド ドロップします。
2. ログ アクティビティをシーケンス アクティビティにドラッグ アンド ドロップします。 [プロパティ] タブに切り替え、LogText の値として `bapiret2Table.item.First(Function(retItem) retItem.TYPE.Equals("E"))`.MESSAGE を入力します。 高いログレベルとトレース タグを維持します。 これにより、詳細トレースが有効になっているときに、エラー メッセージが ConnectorsLog または ECMA2Host イベント ログに記録されます。
3. Log アクティビティの後の Sequence アクティビティ内に、Switch アクティビティをドラッグ アンド ドロップします。 ポップアップ ウィンドウで、スイッチ値の文字列型を選択します。 式 `bapiret2Table.item.First(Function(retItem) retItem.TYPE.Equals("E")).NUMBER` を入力します。
4. 既定のケースを選択し、CreateCSEntryChangeResult アクティビティをこのケースの本文にドラッグ アンド ドロップします。 ExportErrorInvalidProvisioningAttributeValue エラー コードを選択します。

[Image: ワークフローの新しい更新のスクリーンショット。]

1. [新しいケースの追加] 領域を選択し、ケース値として 224 を入力します。 `CreateCSEntryChangeResult` アクティビティをこのケースの本文にドラッグ アンド ドロップします。 `ExportErrorCustomContinueRun` エラーコードを選択します。

[Image: ワークフローの最終更新のスクリーンショット。]

エクスポート追加ワークフローの定義が完了しました。

### エクスポート削除ワークフローの作成

SAP ECC でユーザーを削除するには、BAPI\_USER\_DELETEプログラムを呼び出し、接続システムで削除するアカウント名を指定します。 このシナリオが必須かどうかを判断するには、SAP 管理者に問い合わせてください。 ほとんどの場合、SAP ECC アカウントは削除されませんが、履歴レコードを保持するために期限切れに設定されます。

このガイドでは、SAP の一般ユーザー管理システム、接続されたシステムからのユーザーのプロビジョニング解除、ライセンスの失効などに関連するシナリオについては説明しません。

Exprt ワークフローに改ページ位置の自動修正を実装する必要はありません。 ワークフロー コンテキスト内で使用できる objectToExport オブジェクトは 1 つのみです。

1. [オブジェクトの種類] -&gt; [ユーザー] -&gt; [エクスポート] -&gt; [ワークフローの削除] に移動し、右側の Sequence アクティビティをワークフロー デザイナー ウィンドウにドラッグ アンド ドロップします。
2. 左下にある [変数] ボタンを見つけて選択し、このシーケンス内で定義されている変数の一覧を展開します。
3. 次の変数を追加します。 SAP WSDL から生成された変数型を選択するには、[Browse for Types] を選択し、生成された  を展開してから、SAPECC 名前空間を展開します。 これにより、BAPI\_USER\_DELETE プログラムで使用されるデータ構造を初期化されます。

| 名前 | 変数の種類 | Scope | 既定値 |
| --- | --- | --- | --- |
| userName | String | シークエンス |  |
| bapiret2Table | SAPECC.TABLE\_OF\_BAPIRET2 | シークエンス | new TABLE\_OF\_BAPIRET2() |

1. userName プロパティを不変 ID であるアンカーとして定義したので、エクスポート オブジェクトのアンカーのコレクションから userName 値を抽出する必要があります。 ForEachWithBodyFactory アクティビティを、ツールボックスから Sequence アクティビティにドラッグ アンド ドロップします。 **item** の変数名を **anchor** に置き換え、[プロパティ] に切り替えて、`Microsoft.MetadirectoryServices.AnchorAttribute` の TypeArgument を選択します。 [値] フィールドに「`objectToExport.AnchorAttributes`」と入力します。

[Image: エクスポート削除操作ワークフローのスクリーンショット。]

1. userName アンカーの文字列の値を抽出するには、ForEach アクティビティに Switch アクティビティをドラッグ アンド ドロップします。 ポップアップ ウィンドウで、スイッチ `Microsoft.IdentityManagement.MA.WebServices.Activities.Extensions.AnchorAttributeNameWrapper` 種類を選択します。 式の値を入力します: New `AnchorAttributeNameWrapper(anchor.Name)`。 [スイッチ] アクティビティの [新しいケース領域の追加] を選択します。 Case の値として userName を入力します。 Assign アクティビティを userName ケース本文にドラッグ アンド ドロップし、 `anchor.Value.ToString()` を userName 変数に割り当てます。
2. ForEach アクティビティの後の Sequence アクティビティ内で WebSeviceCall アクティビティをドラッグ アンド ドロップします。 SAPECC サービス名、ZSAPCONNECTORWS エンドポイント、およびBAPI\_USER\_DELETE操作を選択します。 [引数] ボタンを選択して、次のように Web サービス呼び出しのパラメーターを定義します。

| 名前 | 方向 | タイプ | 値 |
| --- | --- | --- | --- |
| RETURN | /アウトの選択 | TABLE\_OF\_BAPIRET2 | bapiret2Table |
| USERNAME | In | String | userName |

1. [OK] を選択します。 警告記号が表示されなくなります。

[Image: 更新された削除操作ワークフローのスクリーンショット。]

1. ユーザー削除のリクエストの結果を処理するには、WebServiceCall アクティビティの後の Sequence アクティビティ内に IF アクティビティをドラッグ アンド ドロップします。 次の条件を入力します: `If(bapiRet2Table.item, Enumerable.Empty(Of BAPIRET2)()).Count(Function(errItem) errItem.TYPE.Equals("E") = True) <> 0`
2. エラーが発生しない場合は、削除操作が正常に完了したと見なし、成功ステータスの`CSEntryChangeResult` を作成して、このオブジェクトのエクスポートが成功したことを示します。 `CreateCSEntryChangeResult` アクティビティを IF アクティビティの Else ブランチにドラッグ アンド ドロップし、成功エラー コードを選択します。

[Image: エクスポート削除ワークフローのスクリーンショット。]

1. エクスポート削除ワークフローの最後の手順は、エクスポート エラーを処理してログに記録することです。 シーケンス アクティビティを IF アクティビティの空の Then ブランチにドラッグ アンド ドロップします。
2. ログ アクティビティをシーケンス アクティビティにドラッグ アンド ドロップします。 [Properties] タブに切り替えて、LogText 値として `bapiRetTable.item.First(Function(retItem) retItem.TYPE.Equals("E")= True).MESSAGE`を入力します。 高いログレベルとトレース タグを維持します。 これにより、詳細トレースが有効な場合に、エラー メッセージが ConnectorsLog または ECMA2Host イベント ログに記録されます。
3. Log アクティビティの後の Sequence アクティビティ内に、Switch アクティビティをドラッグ アンド ドロップします。 ポップアップ ウィンドウで、スイッチ値の文字列型を選択します。 式 `bapiret2Table.item.First(Function(retItem) retItem.TYPE.Equals("E")).NUMBER` を入力します。
4. 既定のケースを選択し、CreateCSEntryChangeResult アクティビティをこのケースの本文にドラッグ アンド ドロップします。 ExportErrorSyntaxViolation エラー コードを選択します。

[Image: エクスポート削除操作ワークフローの更新のスクリーンショット。]

1. [新しいケースの追加] 領域を選択し、ケース値として 124 を入力します。 `CreateCSEntryChangeResult` アクティビティをこのケースの本文にドラッグ アンド ドロップします。 `ExportErrorCustomContinueRun` エラーコードを選択します。

[Image: 最終的なエクスポート削除操作ワークフローのスクリーンショット。]

エクスポート削除ワークフローの定義が完了しました。

### エクスポート置換ワークフローの作成

SAP ECCでユーザーを更新するには、BAPI\_USER\_CHANGEプログラムを呼び出し、アカウント名を含む、変更のない情報を含むすべてのユーザーの詳細を含む全パラメーターを指定します。 「Replace]すべてのユーザー プロパティが提供される場合の ECMA2 エクスポート モードは、「Replace]と呼ばれます。 これに対し、AttributeUpdate のエクスポート モードでは、変更される属性のみが提供されるため、一部のユーザー プロパティが空の値で上書きされる可能性があります。 したがって、Webservice コネクタでは常にオブジェクト置換エクスポート モードが使用され、コネクタがエクスポート タイプ: 置換に構成されていることを想定しています。

エクスポート置換ワークフローは、エクスポートの追加ワークフローとほぼ同じです。 唯一の違いは、BAPI\_USER\_CHANGE プログラムに addressX や companyX などの追加パラメーターを指定する必要がある点です。 addressX の末尾にある X は、アドレスの構造に変更が含まれていることを示します。

1. [オブジェクトの種類] -&gt; [ユーザー] -&gt; [エクスポート] -&gt; [ワークフローの置換] に移動し、右側のツールボックスから Sequence アクティビティをワークフロー デザイナー ウィンドウにドラッグ アンド ドロップします。
2. 左下にある [変数] ボタンを見つけて選択し、このシーケンス内で定義されている変数の一覧を展開します。
3. 次の変数を追加します。 SAP WSDL から生成された変数型を選択するには、[Browse for Types] を選択し、生成された  を展開してから、SAPECC 名前空間を展開します。 これにより、BAPI\_USER\_CHANGE プログラムで使用されるデータ構造が初期化されます。

| 名前 | 変数の種類 | Scope | 既定値 |
| --- | --- | --- | --- |
| userName | String | シークエンス |  |
| bapiret2Table | SAPECC.TABLE\_OF\_BAPIRET2 | シークエンス | new TABLE\_OF\_BAPIRET2() |
| addressX | SAPECC.BAPIADDR3X | シークエンス | new BAPIADDR3X() |
| address | SAPECC.BAPIADDR3 | シークエンス | new BAPIADDR3() |
| companyX | SAPECC. BAPIUSCOMX | シークエンス | new BAPIUSCOMX() |
| 会社 | SAPECC.BAPIUSCOMP | シークエンス | new BAPIUSCOMP() |
| defaultsX | SAPECC.BAPIDEFAX | シークエンス | new BAPIDEFAX() |
| defaults | SAPECC.BAPIDEFAUL | シークエンス | new BAPIDEFAUL() |
| logOnDataX | SAPECC.BAPILOGONX | シークエンス | new BAPILOGONX() |
| logOnData | SAPECC.BAPILOGOND | シークエンス | new BAPILOGOND() |

エクスポート置換ワークフローは次のようになります。

[Image: 置換操作ワークフローの開始のスクリーンショット。]

1. userName プロパティを不変 ID であるアンカーとして定義したので、エクスポート オブジェクトのアンカーのコレクションから userName 値を抽出する必要があります。 ForEachWithBodyFactory アクティビティを、ツールボックスから Sequence アクティビティにドラッグ アンド ドロップします。 **item** の変数名を **anchor** に置き換え、[プロパティ] に切り替えて、`Microsoft.MetadirectoryServices.AnchorAttribute` の TypeArgument を選択します。 [値] フィールドに「`objectToExport.AnchorAttributes`」と入力します。

[Image: 操作ワークフローを置き換える更新のスクリーンショット。]

1. userName アンカーの文字列の値を抽出するには、ForEach アクティビティに Switch アクティビティをドラッグ アンド ドロップします。 ポップアップ ウィンドウで、スイッチ `Microsoft.IdentityManagement.MA.WebServices.Activities.Extensions.AnchorAttributeNameWrapper` 種類を選択します。 式の値を入力します: New `AnchorAttributeNameWrapper(anchor.Name)`。 [スイッチ] アクティビティの [新しいケース領域の追加] を選択します。 Case の値として userName を入力します。 Assign アクティビティを userName ケース本文にドラッグ アンド ドロップし、 `anchor.Value.ToString()` を userName 変数に割り当てます。 エクスポート置換ワークフローは次のようになります。

[Image: 操作ワークフローを置き換える別の更新のスクリーンショット。]

1. これで、エクスポートされたオブジェクト アンカー プロパティから userName 値を抽出したので、他の SAP ユーザーの詳細を含む company、defaults、address、logon data など、他の構造を入力する必要があります。 これを行うには、スキーマで定義されているすべての属性のコレクションを循環します。
2. ForEach アクティビティを折りたたみ、既存の ForEach アクティビティの後の Sequence アクティビティに、別の ForEachWithBothFactory アクティビティをドラッグ アンド ドロップします。 **item**変数名を **schemaAttr** に置き換え、プロパティに切り替えて、`Microsoft.MetadirectoryServices.SchemaAttribute`の TypeArgument を選択します。 [値] フィールドに「`schemaType.Attributes`」と入力します。

[Image: 置換操作シーケンス アクティビティのスクリーンショット。]

1. シーケンス アクティビティを ForEach アクティビティの本文にドラッグ アンド ドロップします。 左下にある [変数] ボタンを見つけて選択し、このシーケンス内で定義されている変数の一覧を展開します。 次の変数を追加します: 文字列型の xValue。 Assign アクティビティを Sequence アクティビティにドラッグ アンド ドロップします。 xValue の式を割り当てる: `If(objectToExport.AttributeChanges.Contains(schemaAttr.Name), objectToExport.AttributeChanges(schemaAttr.Name).ValueChanges(0).Value.ToString(), String.Empty)` この属性のエクスポート用にステージングされた変更を抽出するか、空の文字列で初期化します。 エクスポート置換ワークフローは次のようになります。

[Image: 置換シーケンスへの更新のスクリーンショット。]

1. Assign アクティビティの後に Switch アクティビティをドラッグ アンド ドロップします。 ポップアップ メニューで[`Microsoft.IdentityManagement.MA.WebServices.Activities.Extensions.AttributeNameWrapper`]を選択し、[OK]を選択します。 次の式を入力します: New `AttributeNameWrapper(schemaAttr.Name)`。 Switch アクティビティの右上隅に、スキーマで定義され、プロパティに割り当てられていない未処理の属性に関する警告アイコンが表示されます。 [切り替え] アクティビティの [新しいケース領域の追加] を選択し、ケース値として **を入力し、市区町村**を指定します。 Sequence アクティビティをこのケースの本文にドラッグ アンド ドロップします。 Assign アクティビティをケースの本文にドラッグ アンド ドロップします。 addressX.city に "X" 値を割り当てます。 Assign アクティビティをこのケースの本文にドラッグ アンド ドロップします。 address.city に xValue を割り当てます。 エクスポート置換ワークフローは次のようになります。

[Image: Switch アクティビティのドラッグ アンド ドロップのスクリーンショット。]

10. その他の不足しているケースと割り当てを追加します。 このマッピング テーブルをガイドとして使用してください。

| ケース | 譲渡 |
| --- | --- |
| city | addressX.city = "X" address.city = xValue |
| 会社 | companyX.company = "X" company.company = xValue |
| 部署 | address.departmentX = "X" address.department = xValue |
| メール | addressX.e\_mail = "X" address.e\_mail = xValue |
| expirationTime | logOnDataX.GLTGB = "X" logOnData.GLTGB = xValue |
| firstname | addressX.firstname = "X" address.firstname = xValue |
| lastName | addressX.lastname = "X" address.lastname = xValue |
| middleName | addressX.middlename = "X" address.middlename = xValue |
| telephoneNumber | addressX.TEL1\_Numbr = "X" アドレス。TEL1\_Numbr = xValue |
| jobTitle | addressX.function = "X" address.function = xValue |

エクスポート置換ワークフローは次のようになります。

[Image: 2 番目のドラッグ アンド ドロップ スイッチ アクティビティのスクリーンショット。]

1. BAPI\_USER\_CHANGEプログラムを呼び出す前に、空でないユーザー名を確認する必要があります。 ForEach アクティビティの両方を折りたたみ、2 番目の ForEach アクティビティの後に IF アクティビティをドラッグ アンド ドロップします。 次の条件を入力します: `String.IsNullOrEmpty(userName ) = False`
2. ユーザー名が空の場合は、操作が失敗したことを示します。 `CreateCSEntryChangeResult` アクティビティを IF アクティビティの Else ブランチにドラッグ アンド ドロップし、`ExportErrorCustomContinueRun` エラー コードを選択します。 エクスポート置換ワークフローは次のようになります。 [Image: CreateCSEntryChangeResult アクティビティのスクリーンショット。]
3. シーケンス アクティビティを、最初の IF アクティビティの空の Then ブランチにドラッグ アンド ドロップします。 Sequence アクティビティ内で WebSeviceCall アクティビティをドラッグ アンド ドロップします。 SAPECC サービス名、ZSAPCONNECTORWS エンドポイント、およびBAPI\_USER\_CHANGE 操作を選択します。 [引数] ボタンを選択して、次のように Web サービス呼び出しのパラメーターを定義します。

| 名前 | 方向 | タイプ | 値 |
| --- | --- | --- | --- |
| 住所 | In | BAPIADDR3 | address |
| ADDRESSX | In | BAPIADDR3X | addressX |
| COMPANY | In | BAPIUSCOMP | 会社 |
| COMPANYX | In | BAPIUSCOMX | 会社 |
| DEFAULTS | In | BAPIDEFAUL | defaults |
| DEFAULTSX | In | BAPIDEFAX | defaultsX |
| LOGONDATA | In | BAPILOGOND | logOnData |
| LOGONDATAX | In | BAPILOGONX | logOnDataX |
| RETURN | /アウトの選択 | TABLE\_OF\_BAPIRET2 | bapiret2Table |
| USERNAME | In | String | userName |

1. [OK] を選択します。 警告記号が表示されなくなります。 エクスポート置換ワークフローは次のようになります。

[Image: BAPI_USER_CHANGE 操作のスクリーンショット。]

1. ユーザー要求の変更結果を処理するには、WebServiceCall アクティビティの後の Sequence アクティビティに IF アクティビティをドラッグ アンド ドロップします。 次の条件を入力します: `Not IsNothing(bapiret2Table.item) AndAlso bapiret2Table.item.Count(Function(errItem) errItem.TYPE.Equals("E") = True) <> 0`
2. エラーが発生しない場合は、エクスポート操作が正常に完了したと見なし、Success ステータスの `CSEntryChangeResult` を作成して、このオブジェクトのエクスポートが成功したことを示します。 `CreateCSEntryChangeResult` アクティビティを IF アクティビティの Else ブランチにドラッグ アンド ドロップし、成功エラー コードを選択します。
3. Sequence アクティビティを IF アクティビティの Then ブランチにドラッグ アンド ドロップします。 `string.Join("\n",bapiret2Table.item.Where(Function(retItem) retItem.TYPE.Equals("E")).Select(Function(r) r.MESSAGE))` および Error タグの LogText 値を使用して Log アクティビティを追加します。 `CreateCSEntryChangeResult` のエラー コードを使用して、Log アクティビティの後に `ExportErrorCustomContinueRun` アクティビティを追加します。 エクスポート置換ワークフローは次のようになります。

[Image: 最終的な Export Replace ワークフローのスクリーンショット。]

Export Replace ワークフローの定義を完了しました。

次の手順では、このテンプレートを使用して、[ECMA2Host Webservice コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sap-connector-configure)を構成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/self-access-review"} -->
## アクセス レビューでリソースへのアクセスを確認する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/self-access-review
- Service: entra-id-governance / access-reviews
- Article date: 2026-03-12
- Summary: アクセス レビューでリソースへの独自のアクセス権を確認する方法について説明します。

Microsoft エンタイトルメント管理により、企業がグループ、アプリケーション、および SharePoint サイトへのアクセスを管理する方法が簡略化されます。 この記事では、割り当てられたアクセス パッケージを自己レビューする方法について説明します。

### マイ アクセスを使用して自分のアクセスを確認する

グループ、アプリケーション、またはアクセス パッケージへの独自のアクセスは、2 つの方法で確認できます。

#### メールを使用する

Von Bedeutung

メールの受信に遅延が発生する可能性があり、場合によっては最大 24 時間かかる場合があります。 安全な受信者リストに azure-noreply@microsoft.com を追加して、すべてのメールを確実に受信するようにします。

1. Microsoft からのアクセス レビューを実行するように求めるメールを探します。 電子メール メッセージの例を次に示します。

    [Image: グループへのアクセスを確認するように求める Microsoft からの電子メールの例を示すスクリーンショット。]
2. [ **アクセスの確認** ] を選択して、アクセス レビューを開きます。
3. 次に「**アクセス レビューを実行する**」セクションに移ります。

#### マイ アクセスを使用する

ブラウザーを使用して **マイ アクセス**を開くことで、保留中のアクセス レビューを表示することもできます。

1. [マイ アクセス](https://myaccess.microsoft.com/)にサインインします。
2. 左側のメニューで、[ **アクセス レビュー** ] を選択すると、割り当てられている保留中のアクセス レビューの一覧が表示されます。

    [Image: メニューのアクセス レビューを示すスクリーンショット。]

### アクセス レビューを実行する

1. [ **グループとアプリ]** には、次の情報が表示されます。

    - **名前**: アクセス レビューの名前。
    - **期限**: レビューの期限。 この日付を過ぎると、拒否されたユーザーがレビュー対象のグループまたはアプリから削除される可能性があります。
    - **リソース**: レビュー中のリソースの名前。
    - **進行状況**: このアクセス レビューに参加しているユーザーの合計数からレビューされたユーザーの数。
2. 開始するには、アクセス レビューの名前を選びます。

    [Image: アプリとグループの保留中のアクセス レビューの一覧を示すスクリーンショット。]
3. アクセス権を確認し、引き続きアクセス権が必要かどうかを判断します。

    他のユーザーのアクセスをレビューするように要求した場合は、ページの外観が異なります。 詳細については、[グループまたはアプリケーションに対するアクセスのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)に関するページを参照してください。

    [Image: グループへのアクセスがまだ必要かどうかを確認するオープン アクセス レビューを示すスクリーンショット。]
4. [ **はい** ] を選択してアクセス権を保持するか、[ **いいえ** ] を選択してアクセス権を削除します。
5. **[はい]** を選択する場合は、**[理由]** ボックスに正当性を指定する必要がある場合があります。

    [Image: [はい] を選択してグループへのアクセスを維持する方法を示すスクリーンショット。]
6. **送信**を選択します。

    選択内容が送信され、[ **マイ アクセス** ] ページに戻ります。

    返信を変更する場合は、[ **アクセス レビュー** ] ページをもう一度開き、返信を更新します。 応答内容は、アクセス レビューが終了するまでいつでも変更できます。

    注

    アクセスが必要なくなったことを示した場合でも、システムはすぐにあなたのアクセスを削除しません。 レビューが終了したとき、または管理者がレビューを停止すると、削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/services-and-integration-partners"} -->
## サービスと統合パートナー - Microsoft Entra - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/services-and-integration-partners
- Service: entra-id-governance
- Article date: 2023-06-12
- Summary: ID 管理 (IAM) と ID ガバナンス シナリオのデプロイと統合を支援するパートナーについて説明します。

### Microsoft Entra ID ガバナンスのサービスと統合パートナー

パートナーは、Identity Governance を含む Microsoft Entra シナリオの計画とデプロイに関して組織を支援できます。 お客様は、Microsoft Solution Partner ファインダーに記載されているパートナーと協働することも、次の表に示すサービス パートナーから選択することもできます。 説明とリンクされているページは、パートナー自身によって提供されています。 説明を使用して、連絡するパートナーを特定し、詳細情報を確認できます。

| 名前 | 説明 |
| --- | --- |
| [Edgile (Wipro 企業)](https://aka.ms/EdgileEntraIDGov) | 「Wipro 企業の Edgile は、Microsoft Entra ID ガバナンスの Microsoft ローンチ パートナーになることを大変嬉しく思います。 IGA とセキュリティに関する豊富な経験により、プロジェクトが成功することを保証します。 当社のプロジェクト アクセラレータは、お客様のリスクを軽減し、より迅速に結果を提供します。」 |
| [EY](https://aka.ms/EYEntraIDGov) | 「EY 組織は、プロフェッショナル サービスの信頼できるグローバル リーダーとして、大規模なテクノロジを活用し、迅速にイノベーションを推進することで、センターの皆さんと一緒により良い社会を作り出します。 EY-Microsoft アライアンスは、共同で Microsoft Entra を使った革新的な ID 管理ソリューションに取り組み、企業が ID を保護および管理する方法を変革し、信頼と安全が最優先される未来を創造します。」 |
| [InSpark](https://aka.ms/InSparkEntraIDGov) | 「InSpark は、オランダの Microsoft パートナーであり、完全な Microsoft クラウド ポートフォリオを活用して、お客様が無名からヒーローになるお手伝いをしています。 Microsoft Entra ID ガバナンス スタックは、当社が重視している点の 1 つです。お客様のデジタル ID とそのアクセスを安全に保護することは、今日の世界において非常に重要だと考えてるからです。」 |
| [Invoke](https://www.invokellc.com/identity-access-management) | 「Invoke の ID ソリューション体験は、評価から始まり、セキュリティとコンプライアンス リスク軽減策の提示、さらに生産性の向上によって信頼を構築します。 コスト重視の市場において、Microsoft 中心のソリューションに移行することでコスト削減を報告し、経済的評価を行います。 Microsoft Entra チームと提携することで、お客様がより多くのことを達成できるように共同で支援します。」 |
| [KPMG](https://aka.ms/KPMGEntraIDGov) | 「KPMG と Microsoft は、包括的な ID ガバナンスの提案を提供することで、アライアンスをさらに強化します。 Microsoft Entra の高度なツールと KPMG Powered Enterprise の組み合わせにより、ID ガバナンスの複雑さに巧みに対処することで、機能的な変革を推進することが可能となります。 この相乗効果により、デジタル機能を加速させ、運用効率を向上させ、セキュリティを強化し、コンプライアンスを確保することができます。」 |
| [オックスフォード コンピューター グループ](https://aka.ms/OCGEntraIDGov) | "Oxford Computer Group (OCG) には、複雑なレガシ プラットフォームからMicrosoft Entra ID ガバナンスに組織を移行した豊富な経験があります。 OCG のフィールド テスト IP はMicrosoft Entra機能を拡張して導入障壁を排除し、統合された ID データ、自動化されたワークフロー、AI 主導のアクセスの最適化、ガバナンスの可視性を実現します。 これは、組織が断片化された手動ソリューションを置き換え、自信を持ってMicrosoft Entraに移行するのに役立ちます。 |
| [PwC](https://aka.ms/PwCEntraIDGov) | 「組織は ID およびアクセス管理を使用して信頼を築き、それを持続可能に行うには、適切なテクノロジと多専門的なチームが必要になることがよくあります。 当社のチームは、ユーザー、プロセス、テクノロジの 3 つの重要な側面に焦点を当てることで、お客様と当社の専門家のネットワークと連携し、戦略から実行までの Microsoft Entra ID ガバナンスの実装を支援します。」 |

### SAP アプリケーションを使用して Microsoft Entra をデプロイするためのサービスと統合パートナー

パートナーは、SAP S/4HANA などの SAP アプリケーションを使用した Microsoft Entra の計画とデプロイを提供し、組織を支援することもできます。 詳細については、「[ID 管理シナリオを SAP IDM から Microsoft Entra に移行する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/migrate-from-sap-idm#services-and-integration-partners-for-deploying-microsoft-entra-with-sap-applications)」ガイドで、SAP アプリケーションを使用して Microsoft Entra をデプロイするためのサービスと統合パートナーの一覧を参照してください。

### パートナー主導のアプリケーションの統合

Microsoft Entra ID ガバナンスでは、何百もの [Microsoft Entra ID ガバナンスとアプリケーションの統合](https://learn.microsoft.com/ja-jp/entra/id-governance/apps)を通じて、適切なプロセスと可視性により、セキュリティと従業員の生産性に対する組織のニーズのバランスを取ることができます。 これらのアプリケーション統合は、クロスドメイン ID 管理システム (SCIM) などのプロトコルを介して、ID ライフサイクル管理を自動化するために使用されます。 SCIM をサポートするアプリケーションを使用すると、Microsoft Entra がユーザーとグループのプロビジョニング、更新、プロビジョニング解除のためにアプリケーションに変更を送信できるため、組織全体のガバナンス制御の実装が簡素化されます。

アプリケーションが SCIM をサポートしていない場合、パートナーは Microsoft Entra の出力方向のプロビジョニングとそれらのアプリケーションの間にゲートウェイを設けています。 パートナー オファリングを介して統合されたアプリケーションの一覧については、「[パートナー主導の統合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/partner-driven-integrations)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/simulate-workflow-execution"} -->
## What-if ツールを使用してワークフローの実行をシミュレートする - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/simulate-workflow-execution
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローで What-if ツールを使用して、実際のユーザーに影響を与えずにワークフローの実行をシミュレートし、結果をプレビューする方法について説明します。

ライフサイクル ワークフローの What-if ツールを使用すると、ワークフローの実行をユーザーに対して実行する前に評価できます。 What-if ツールを使用すると、次のことができます。

- ワークフローの実行スコープに現在存在するユーザーを表示します。
- 現在の構成に基づいて、失敗する可能性があるワークフロー タスクをプレビューします。
- 最大 10 人のユーザーのワークフロー実行をシミュレートし、実際のユーザーに影響を与えずに結果を確認します。

What-if ツールの主な目的は、誤った構成を特定して解決し、処理を開始する前に誤ったワークフローの実行を防ぐことです。

注

属性の変更やグループ メンバーシップの変更など、変更ベースのトリガーの種類を持つワークフローは、現在サポートされていません。

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### What-If ツールにアクセスする

ワークフローの What-If ツールにアクセスするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**Workflows** に移動します。
3. 評価するワークフローを選択します。

    注

    属性の変更やグループ メンバーシップの変更をトリガーの種類として使用するワークフローは、What-if ツールでは現在サポートされていません。
4. [ワークフローの概要] ページで、コマンド バーから [ **What if** ] を選択します。 [Image: ワークフローの概要ページの What if ツールのスクリーンショット。]

#### スコープ内のユーザーを確認する

What-if ツール内 **で、[スコープ内のユーザー** ] タブを選択して、選択したワークフローの現在の実行スコープに含まれるユーザーの一覧を表示します。 この一覧には、ツールの実行時にワークフローの実行条件を満たすユーザーが反映されます。

#### 潜在的なタスクの失敗を確認する

What-if ツール内で、[What-if]\(What-if\)ページに一覧表示されているタスクを確認して、現在の構成に基づいて失敗する可能性のあるワークフロー タスクを確認することもできます。 [Image: what if ツール内の潜在的なタスクエラーのスクリーンショット。]

### ワークフロー実行シミュレーションを実行する

特定のユーザーのワークフロー実行をシミュレートし、結果をプレビューするには:

1. [What-if] ページで、**[スコープ内のユーザー]** タブを選択します。
2. 一覧から最大 10 人のユーザーを選択します。

    注

    [ **ワークフロー実行のシミュレート** ] ボタンは、10 人を超えるユーザーが選択されている場合は無効になります。
3. コマンド バーから [ **ワークフロー実行のシミュレート** ] を選択します。
4. シミュレーションが自動的に開始されます。 結果が使用可能になると、[ **実行シミュレーションの結果** ] タブに表示されます。 [Image: 実行シミュレーションの結果のスクリーンショット。]

    注

    ワークフロー タスクによっては、結果が表示されるまでに数秒かかる場合があります。 短時間経過しても結果が表示されない場合は、[ **最新の情報に更新** ] を選択して更新された結果を確認します。 [ **実行シミュレーション結果** ] タブは、シミュレーションを初めて実行した後にのみ表示されます。 別のシミュレーションを実行すると、結果がクリアされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance"} -->
## Microsoft Entra テナント ガバナンスに関するドキュメント - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance
- Service: entra-id-governance
- Article date: 2026-03-02
- Summary: 組織全体の Microsoft Entra テナントを検出、管理、管理する方法について説明します。

Microsoft Entra テナント ガバナンスは、一元化されたポリシーとテナント間の委任された管理を使用して、組織が環境内のテナントを検出、管理、管理するのに役立ちます。

### テナント ガバナンスについて

#### 概要

- [テナント ガバナンスとは](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/overview)

### 概念

#### 概念

- [関連テナント](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/related-tenants)
- [シグナルとメトリック](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/signals-metrics)
- [ガバナンス関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships)
- [ガバナンス ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)
- [テナント間で委任された管理権限](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/cross-tenant-delegated-administration)
- [構成管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)

### 操作方法ガイド

#### 攻略ガイド

- [テナントの検出を有効にする](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-enable-tenant-discovery)
- [ガバナンス関係を設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-governance-relationship)
- [委任された管理と監視アクティビティを使用する](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-delegated-administration)
- [構成管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)
- [テナントの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-tenant)

### リファレンス

#### リファレンス

- [よく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/faq)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/automatic-governance-relationships"} -->
## ガバナンス関係の自動形成 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/automatic-governance-relationships
- Service: entra-id-governance
- Article date: 2026-03-26
- Summary: セキュリティで保護されたテナントの作成Microsoft Entra使用してアドオン テナントを作成するときに、テナント ガバナンスによってガバナンス関係が自動的に確立される方法について説明します。

組織内のアクセス許可を持つユーザーが、セキュリティで保護されたアドオン テナント作成機能を使用して新しいテナントを作成すると、Microsoft Entraは自動的に新しく作成されたテナントに対するガバナンス関係を自動的に確立できます。

既定 [のガバナンス ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)を定義した場合、既定のポリシー テンプレートを使用して、ホーム (ガバナンス) テナントと新しく作成されたアドオン (管理) テナントの間に新しいガバナンス関係が形成されます。

既定のガバナンス ポリシー テンプレートでロールとアクセス許可が定義されていない場合、新しいアドオン テナントの作成時にガバナンス関係は確立されません。 テンプレートに対する変更は、既に確立されているガバナンス関係には影響しません。

### Microsoft Entra ID 無料の課金資産

セキュリティで保護されたアドオン テナント作成機能を使用して新しいMicrosoft Entra テナントを作成すると、課金アカウントから既存のサブスクリプションとリソース グループを選択するように求められます。 新しいテナントを作成すると、Microsoftはそのサブスクリプションとリソース グループの下に **Entra ID Free** という名前の新しい課金資産を生成し、新しく作成されたテナントにリンクします。

サブスクリプションでは、同じ課金アカウントで作成された新しいテナントを追跡し、すべての新しいテナントのインベントリを維持できます。 サブスクリプションは、テナントの所有権を証明するのにも役立ち、失った場合に管理アクセス権を回復するのに役立ちます。 詳細については、「[Microsoft Entra ID Free](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/microsoft-entra-id-free) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/configuration-management"} -->
## テナント ガバナンスでの構成管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management
- Service: entra-id-governance
- Article date: 2026-06-05
- Summary: ベースラインやドリフト監視など、Microsoft Entra テナント ガバナンスの構成管理機能について説明します

Microsoft Entra テナント ガバナンスの構成管理を使用すると、構成基準の定義、テナントの誤差の監視、現在の設定のスナップショットの生成を行うことができます。 この記事では、リソース、ベースライン、モニター、監視結果、構成ドリフト、スナップショット ジョブという主要な概念について説明します。

注

構成管理は、一般提供されている Microsoft Graph [テナント構成管理 API に](https://learn.microsoft.com/ja-jp/graph/unified-tenant-configuration-management-concept-overview)基づいて構築されています。 これらの API は、スナップショット、ベースライン、モニター、ドリフト検出など、この記事で説明するコードとしての構成機能を提供します。

### リソース

**リソース**は、コードとしての構成で管理できるマクロ構成コンポーネントを表します。 テナント構成管理ソリューションでは、構成基準に含めることができる数百種類のリソースがサポートされています。 各リソースは、ソリューションで管理できる複数のプロパティを定義します。

たとえば、Microsoft Entra ID の条件付きアクセス ポリシーは、 `microsoft.entra.conditionalaccesspolicy` リソースによって表されます。 そのリソースは、構成基準で定義できる `ExcludedUsers`、 `IncludedGroups`、 `State` などのプロパティを公開します。 リソースの完全な一覧については、「 [テナント構成管理の概要」を](https://learn.microsoft.com/ja-jp/graph/unified-tenant-configuration-management-concept-overview)参照してください。

### ［基準］

**ベースライン**は、特定のテナントを監視する構成の JSON 表現です。 リソースの一覧と、関連するプロパティに対して定義された値で構成されます。 ベースラインには、特定のリソースの種類の複数のインスタンスを含めることができます。

たとえば、構成基準では、Exchange Online トランスポート ルールの複数のインスタンスを定義の一部として定義できます。 構成基準の詳細については、「 [構成基準」を](https://learn.microsoft.com/ja-jp/graph/api/resources/configurationbaseline?view=graph-rest-1.0&preserve-view=true)参照してください。

### モニター

**モニター** は、テナント構成管理ソリューションの中核です。 構成の誤差についてテナントを継続的に監視するプロセスを実行します。 各モニターは、検証するリソースを指定する JSON 構成基準を参照します。 モニター定義には、名前、説明、スケジュールの頻度、およびドリフトが検出されたときに実行するアクションを指定する構成モードも含まれます。

注

統合テナント構成管理サービス プリンシパルには、関連付けられているベースラインで定義されているリソースの種類に対する読み取りアクセス許可が必要です。 この要件は、モニタージョブとスナップショット ジョブの両方に適用されます。 必要なアクセス許可を付与する方法については、「 [認証のセットアップ](https://learn.microsoft.com/ja-jp/graph/utcm-authentication-setup) 」を参照してください。

モニターの詳細については、 [configurationMonitor](https://learn.microsoft.com/ja-jp/graph/api/resources/configurationmonitor?view=graph-rest-1.0&preserve-view=true) を参照してください。

### 監視結果

モニターは、指定されたスケジュールに基づいて実行されるたびに、実行を要約した **監視結果** を生成します。 監視結果には、実行時間、リソースの評価に成功したかどうかを示す状態、検出されたドリフトの数が含まれます。

ドリフトが検出された場合、監視レポートはそれを検出したことを報告しますが、ドリフトの詳細は含まれません。 各ドリフトに関する詳細情報を取得するには、関連付けられている構成ドリフト オブジェクトに対してクエリを実行します。

結果の監視の詳細については、「 [configurationMonitoringResult」を](https://learn.microsoft.com/ja-jp/graph/api/configurationmonitoringresult-get?view=graph-rest-1.0&preserve-view=true)参照してください。

### 構成のずれ

構成基準で定義されているものとテナントの実際の設定の間に差分が存在する場合、テナント ガバナンスは **構成の誤差**を報告します。 構成ドリフトはモニターに関連付けられ、検出されたデルタに関する詳細情報が含まれます。 各ドリフト オブジェクトは、影響を受けるリソースを識別し、現在の値がベースライン定義と異なる各プロパティを一覧表示します。

検出されたドリフトを修復すると、次のモニター実行によって、構成ドリフト オブジェクトが `fixed`として自動的にマークされます。 構成の誤差の詳細については、「 [configurationDrift](https://learn.microsoft.com/ja-jp/graph/api/resources/configurationdrift?view=graph-rest-1.0&preserve-view=true)」を参照してください。

### スナップショット ジョブ

スナップショットを生成する要求を開始すると、非同期ジョブは指定されたリソースの現在の状態を収集します。 この **スナップショット ジョブ** は、要求されたスナップショットの実際の JSON コンテンツを生成します。 ジョブが完了すると、 `succeeded` の状態と、JSON スナップショットをダウンロードできる `resourceLocation` URL が返されます。

Important

スナップショット ジョブとそれに関連付けられているスナップショットの保有期間は 7 日間で、その後は削除されます。 保持期間の有効期限が切れる前に、生成されたスナップショットをダウンロードして格納します。

生成されたスナップショット スキーマは、構成基準のスキーマと一致します。 スナップショット as-is を使用してモニターを作成します。

スナップショット ジョブの詳細については、「 [configurationSnapshotJob」](https://learn.microsoft.com/ja-jp/graph/api/resources/configurationsnapshotjob?view=graph-rest-1.0&preserve-view=true)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/cross-tenant-delegated-administration"} -->
## テナント間で委任された管理権限 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/cross-tenant-delegated-administration
- Service: entra-id-governance
- Article date: 2026-07-27
- Summary: テナント間の委任された管理と、Microsoft Entraでテナントを管理するための GDAP ベースのアクセス許可モデルについて説明します。

テナント間の委任された管理は、テナント ガバナンスの機能であり、あるテナントの管理者は、ホーム テナントの資格情報を使用して別のテナントを管理できます。 管理者は、すべてのテナントでローカル アカウントや企業間 (B2B) ゲスト アカウントを必要としません。 この機能では、詳細な委任された管理者特権 (GDAP) テクノロジを使用して、テナントの境界を越えて委任された最小特権の管理とアクセスを提供します。

この記事では、テナント ガバナンスやその他のMicrosoft サービスが使用する GDAP ベースのアクセス許可モデルについて説明します。 これは、顧客、パートナー、Microsoftワークロードの中心的な参照として機能し、独自の製品とサービスを通じて委任された管理機能を公開します。

### アクセス許可モデルのしくみ

テナント間の委任された管理には、次の 2 つのアクセス許可レイヤーがあります。

- **リレーションシップ レベルのアクセス許可は** 、テナント間の委任された管理関係を確立します。
- **ワークロード固有のアクセス許可**は、個々のMicrosoft サービスが独自のロールベースのアクセス制御 (RBAC) システムを使用するときに必要になる可能性がある追加のアクセス許可です。

#### 関係ごとのアクセス許可

委任された管理者が管理テナントにアクセスできるようになる前に、管理テナントと管理テナントの間に [ガバナンス関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships) (または GDAP リレーションシップ) が確立されます。 この関係により、信頼境界が確立され、委任された管理ポリシー (管理テナントの管理者が使用できるロールとアクセス許可) が定義されます。

テナント ガバナンスでは、委任された管理ロールは [ガバナンス ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)で定義されます。 テンプレートは、管理が有効になっているMicrosoft Entra組み込みロールを識別し、それらのロールを管理テナントのセキュリティ グループにマップします。 リレーションシップが確立されると、管理されたテナントにリモート テナント グループ (またはグループ プロキシ) が作成されます。 各グループ プロキシは、管理テナント内のセキュリティ グループにマップされるため、管理テナントでアクセスが表されている間は、管理テナントのメンバーシップを管理します。 リレーションシップで定義されているように、これらのグループ プロキシに割り当てられるロールは GDAP ロールの割り当てです。

パートナー センターでのリレーションシップ レベルのアクセス許可の詳細については、「 [詳細な委任された管理者特権 (GDAP)」](https://learn.microsoft.com/ja-jp/partner-center/customers/gdap-introduction)を参照してください。

#### ワークロード固有のアクセス許可

Microsoftワークロードには、Microsoft Entraロールに加えて RBAC システムが含まれる場合があります。 委任された管理関係によって信頼と ID の基盤が確立されますが、管理者がそのワークロードのリソースを管理する前に、ワークロードで管理されるテナントに追加のロールの割り当てが必要になる場合があります。

リレーションシップの確立後に行うワークロード固有のロールの割り当ては、管理テナント内のセキュリティ グループにマップするリモート テナント グループ (またはグループ プロキシ) を対象とする場合があります。 このマッピングにより、管理テナントは独自のセキュリティ グループのメンバーシップを管理でき、管理されるテナントは対応するプロキシにワークロードのアクセス許可を割り当てます。 パートナーまたは管理テナントが合意した管理作業を実行するためにこれらのアクセス許可が必要な場合にのみ、リモート テナント グループに追加のワークロードアクセス許可を割り当てます。 パートナーは、リレーションシップが確立された後に、ワークロード固有のアクセス許可が顧客テナントに割り当てられる可能性があることに注意する必要があります。

### アクセス許可の種類別の予期される動作

次の表は、各アクセス許可の種類の構成と制御方法をまとめたものです。

| アクセス許可の種類 | 設定場所 | 承認または割り当てを行う人 | 制御する内容 |
| --- | --- | --- | --- |
| Microsoft Entra の組み込みロール (委任された管理関係に基づく) | ガバナンスまたは GDAP リレーションシップのセットアップ中 | 管理対象 (顧客) テナントが要求を確認し、承諾する | Microsoft Entra ロールに委任されるベースラインの管理者アクセス権 |
| Azure RBAC ロール | 管理対象の (顧客) テナントで、関係が確立された後 | 管理対象の (顧客) テナントの管理者 | Azure RBAC によって管理されるAzure リソースへのアクセス |
| Defender RBAC ロールまたは統合 RBAC ロール | 管理対象 (顧客) テナントで、関係が確立された後 | 管理対象（顧客）テナント内の管理者 | Defender または関連するワークロードへのアクセス許可 |
| セキュリティ グループのメンバーシップ | 管理元 (パートナー) テナント内 | 管理元 (パートナー) テナント | グループが表す委任アクセスを受け取るパートナー ユーザーはどれか |

Microsoft Defenderでの委任された管理の詳細については、「[マルチテナント組織のガバナンス関係を使用して委任されたアクセスを構成](https://learn.microsoft.com/ja-jp/unified-secops/governance-relationships)する」を参照してください。

### お客様に推奨されるプラクティス

#### 同意する前に、要求されたリレーションシップを確認する

テナント ガバナンスまたはパートナー センターを通じて委任された管理関係を受け入れる前に、要求を慎重に確認してください。 どのロールが含まれているかを確認し、要求を送信した組織またはテナントを認識していることを確認します。

認識できない組織またはテナントから要求が送信された場合は、それを受け入れないでください。 ガバナンス (GDAP) 要求を受け入れると、承認されたロールを通じて、管理テナントの管理者にテナントへのアクセス権を付与します。 このリレーションシップは、委任された管理ベースラインを定義し、管理テナントからテナントにアクセスできる管理者を決定します。 要求を受け入れるのは、関係を想定し、要求する組織を信頼する場合のみです。

#### 必要な場合にのみワークロード固有のアクセス許可を割り当てる

管理テナント管理者がリモート テナント グループにアクセス許可を割り当てるように求められた場合は、同意されたサービスまたは管理シナリオに割り当てが必要であることを確認します。 パートナーまたは管理テナントがそのアクセスを必要とする理由を理解していない限り、追加のAzureまたはDefenderアクセス許可を割り当てないでください。

#### 委任された管理者アクティビティを監視する

管理されたテナントのサインイン ログと監査ログを使用して、管理者のアクティビティを監視します。 管理されたテナントは、サインイン ログと監査ログで管理テナント管理者のアクティビティを追跡できます。 詳細については、「 [管理されたテナントでのテナント管理者アクティビティの管理を監視する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-monitor-governing-activity)参照してください。

#### 可能な場合は予防的なコントロールを使用する

Azureロールの割り当てについては、[Azure Policy](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview)を使用して、組織がテナント管理者にこれらのアクセス許可を付与したくない場合は、リモート テナント グループへの割り当てを制限することを検討してください。

### パートナーとテナントの管理に関する推奨プラクティス

#### 必要なアクセス許可を伝える

構成に必要なワークロード固有のロールを顧客に伝えます。 明確なコミュニケーションは、顧客が意図したよりも広範なアクセスを許可する場合を回避するのに役立ちます。

#### セキュリティ グループを使用してアクセスを管理する

委任された管理ロールに名前付きセキュリティ グループを割り当て、管理 (パートナー) テナントから技術者メンバーシップを管理します。 自分のテナントのセキュリティ グループを、顧客テナントの承認済みのロールに割り当てます。 その後、その顧客を支援する技術者のみが含まれるように、そのセキュリティ グループのメンバーシップを管理します。

#### 予期しないワークロードの割り当てを監視する

AzureやDefenderロールの割り当てなど、リモート テナント グループへのワークロード固有のロールの割り当てを検出して確認するプロセスを確立します。 Log Analyticsを使用してAzureログとMicrosoft 365ログを集計することを検討[してください](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-analytics-overview)。 監査ログ内で Azure および Defender のロール割り当てを見つけるには、[Microsoft Defender XDR の監査](https://learn.microsoft.com/ja-jp/defender-xdr/microsoft-xdr-auditing)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/deployment-guide"} -->
## Microsoft Entra Tenant Governance をエンドツーエンドで展開する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/deployment-guide
- Service: entra-id-governance
- Article date: 2026-07-29
- Summary: セットアップからテナントの検出、ガバナンス、構成の監視まで Microsoft Entra テナント ガバナンスを展開する方法について説明します

この記事では、組織全体に Microsoft Entra テナント ガバナンスを展開する方法について説明します。 デプロイは、前提条件の確認、関連するテナントの検出、ガバナンス関係の確立、委任された管理の実装、構成監視の設定の 5 つのフェーズに従います。

各フェーズは、前のフェーズに基づいています。 フェーズは順番に完了しますが、環境に適用されない場合は省略可能なフェーズをスキップします。

### 前提条件

開始する前に、環境が次の要件を満たしていることを確認します。

- テナント ガバナンスの適切なライセンスを持つ Microsoft Entra テナント。 詳細については、 [Microsoft Entra のライセンスに関するページを](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-tenant-governance)参照してください。
- テナント ガバナンス管理者またはグローバル管理者ロールを持つアカウント。
- 構成管理の場合: グローバル管理者または特権ロール管理者ロールを持つアカウント。
- セキュアなテナントを作成するには、有料の [Enterprise Agreement (EA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-ea-roles) または [従量課金制](https://azure.microsoft.com/pricing/offers/ms-azr-0003p?cid=msft_learn) サブスクリプションが必要です。 [マイクロソフト オンライン サブスクリプション契約 (MOSA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts#microsoft-online-services-program) と [Microsoft 顧客契約 (MCA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts#microsoft-customer-agreement) の両方の課金アカウントがサポートされています。 また、テナント共同作成者ロールまたはサブスクリプション所有者/作成者ロールを使用して、選択したサブスクリプションに必要なAzure Resource Manager (ARM) アクセス許可も必要です。 課金アカウントの種類を特定するには、[Azure ポータルで課金アカウントを表示するを](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts)参照してください。

### フェーズ 1: 関連するテナント検出を有効にする

テナント検出は、テナントとのリレーションシップが監視可能な他の Microsoft Entra テナントを識別します。 これらの関係は、B2B コラボレーション、マルチテナント アプリケーションの使用状況、共有課金アカウントなどのシグナルに基づいています。 関連するテナントが表す内容の詳細については、「 [関連テナント」](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/related-tenants)を参照してください。

#### 関連するテナントを有効にする

1. テナント ガバナンス管理者として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **テナント ガバナンス**&gt;**関連テナントを参照します**。
3. テナント検出機能の説明を確認します。
4. **[関連するテナントの検出] を選択します**。

Important

テナント検出の有効化は永続的です。 有効にすると、設定を元に戻すことはできません。

検出シグナルは、機能を有効にした後に集計を開始します。 データが反映されるまでに数日かかる場合があります。 テナント検出で使用されるシグナルとメトリックの詳細については、「テナント検出 [のシグナルとメトリック](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/signals-metrics)」を参照してください。

#### 検出されたテナントを確認して分類する

シグナルが集計されたら、関連するテナント のインベントリを確認し、各テナントを分類します。

1. **テナント ガバナンス**&gt;**関連するテナント**を参照して、完全な一覧を表示します。
2. 各テナント (B2B コラボレーション、マルチテナント アプリケーション、共有課金アカウント) の検出シグナルを確認します。
3. 初期メトリックと最近のメトリックを比較して、アクティビティが進行中か、増加しているか、休止中かを判断します。
4. 各テナントを次のいずれかのカテゴリに分類します。

    - **既知で許容可能**: 期待されるアクティビティを持つ明確に識別可能なテナント (パートナー、ベンダー、顧客)。 既知の関係性として記録します。 即時のガバナンス アクションは必要ありません。
    - **ガバナンスが必要**: 中程度または増加中のアクティビティと明確でない所有権を持つ内部的なテナント。 ガバナンス関係を調査し、確立することを検討します。
    - **潜在的に危険**: 予期しないアクティビティを持ち、明確な業務上の正当な理由がない不明なテナント。 検疫を検討してください。 詳細については、「[未承認テナントの検疫](https://learn.microsoft.com/ja-jp/entra/fundamentals/quarantine-unsanctioned-tenants)」を参照してください。

探索データの解釈に関する詳細なガイダンスについては、「 [テナント検出データの解釈](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-interpret-discovery-data)」を参照してください。

### フェーズ 2: ガバナンス ポリシー テンプレートを作成する

ガバナンス関係を確立する前に、1 つ以上のガバナンス ポリシー テンプレートを作成します。 ポリシー テンプレートは、ガバナンス関係の作成時にプロビジョニングされるアクセス許可とアプリケーションを定義します。

#### ポリシー テンプレートを作成する

1. テナント ガバナンス管理者として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **テナント ガバナンス**&gt;**Templates** に移動します。
3. [ **テンプレートの作成] を選択します**。
4. テンプレートを構成します。

    - **テナント間の委任された管理**: 割り当てる Microsoft Entra 組み込みロールを選択し、それらのロールを受け取る管理テナントのセキュリティ グループを指定します。
    - **マルチテナント アプリケーション** (省略可能): 管理されたテナントでプロビジョニングするカスタム アプリケーションを選択します。
5. テンプレートを [保存] します。

ポリシー テンプレートの更新は、既存のガバナンス関係には自動的には適用されません。 変更を適用するには、新しいガバナンス要求を送信し、管理されたテナントにそれを受け入れるようにします。 詳細については、「 [ガバナンス ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)」を参照してください。

#### 既定のポリシー テンプレートを構成する (省略可能)

セキュリティで保護されたアドオン テナント作成機能を使用する場合は、既定のポリシー テンプレートを構成します。 既定のテンプレートは、そのフローを通じて新しい管理テナントが作成されるときに自動的に適用されます。

1. ID `default`を使用してポリシー テンプレートを作成または更新します。
2. 新しいテナントの委任された管理ロールとアプリケーションを構成します。

既定のテンプレートが存在しない場合、セキュリティで保護されたアドオン フローを通じて作成された新しいテナントは、ガバナンス関係を自動的に取得しません。 詳細については、「 [管理されたテナントの作成」を](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-tenant)参照してください。

### フェーズ 3: ガバナンス関係を設定する

ガバナンス関係は、管理テナントを 1 つ以上の統治されたテナントに接続します。 このプロセスは、テナントが課金アカウントを共有するかどうかによって異なります。 ガバナンス関係の背景については、「ガバナンスの [関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships)」を参照してください。

#### 2 段階のハンドシェイク (請求アカウントを共有する関連テナント間)

テナント間に課金シグナルが存在する場合は、次の 2 段階のプロセスを使用します。

**手順 1: ガバナンス リクエストを送信する (管理テナント)**

1. テナント ガバナンス管理者として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **テナント ガバナンス**&gt;**管理されているテナント**を参照します。
3. [ **ガバナンス要求の送信]** を選択します。
4. 適用するターゲット テナントとポリシー テンプレートを選択します。
5. 要求を送信します。 要求は 14 日間有効です。

**手順 2: 要求を受け入れる (管理されたテナント)**

1. 管理されているテナントにテナント ガバナンス管理者としてサインインします。
2. **テナントのガバナンス**&gt;**テナントの管理**&gt;**受信要求** を参照します。
3. ポリシー テンプレートで定義されているロールとアプリケーションなど、要求の詳細を確認します。
4. **[Accept](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/承認)** を選択します。

#### 3 段階ハンドシェイク (共有請求がないテナント)

テナント間に課金シグナルが存在しない場合は、次の 3 つの手順を使用します。

**手順 1: 招待を送信する (管理されたテナント)**

1. 管理されているテナントにテナント ガバナンス管理者としてサインインします。
2. **テナント ガバナンス**&gt;**テナントの管理**&gt;**招待の送信**に関するページを参照します。
3. 将来の管理テナントに招待を送信します。 招待は 30 日間有効です。

**手順 2: ガバナンス要求を送信する (管理テナント)**

1. テナント ガバナンス&gt; で**ガバナンス**への招待を有効にします (まだ有効になっていない場合)。
2. **テナント ガバナンス**&gt;**管理されたテナント**&gt;**受け取った招待**を参照してください。
3. 招待を確認し、選択したポリシー テンプレートを使用してガバナンス要求を送信します。 要求は 14 日間有効です。

**手順 3: 要求を受け入れる (管理されたテナント)**

1. **テナントのガバナンス**&gt;**テナントの管理**&gt;**受信要求** を参照します。
2. 要求を確認して受け入れます。

#### リレーションシップを確認する

管理されたテナントが要求を受け入れた後、これらのリソースがプロビジョニングされていることを確認します。

- 両方のテナントにおけるガバナンス関係オブジェクト
- テナント間アクセス設定の更新
- GDAP ロールの割り当て (委任された管理が構成されている場合)
- マルチテナント アプリケーションのサービス プリンシパル (アプリケーション管理が構成されている場合)

完全なセットアップ手順については、「 [ガバナンス関係の設定」を](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-governance-relationship)参照してください。

### フェーズ 4: テナント間の委任された管理を実装する

構成された委任された管理とのガバナンス関係を確立した後、管理テナントの管理者は、ローカルアカウントまたは B2B アカウントなしで管理テナントを管理できます。 背景については、 [テナント間の委任された管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/cross-tenant-delegated-administration)に関するページを参照してください。

#### セキュリティ グループにユーザーを追加する

ガバナンス ポリシー テンプレートで指定されたセキュリティ グループへのテナント間アクセスが必要な管理者を追加します。

#### 管理されたテナントにサインインする

管理者は、管理されたテナントのドメインまたは ID を使用して、サポートされている管理ポータルにアクセスして、管理テナントにサインインします。

1. 管理ポータルの URL を開き、管理テナント識別子を追加します。次に例を示します。 `https://entra.microsoft.com/{governed-tenant-domain-or-id}`
2. ガバナンス側テナントの資格情報でサインインします。
3. 割り当てられたロールに基づいて管理タスクを実行します。

サポートされている管理ポータルには、Azure portal、Microsoft Entra 管理センター、Microsoft Intune、Exchange、SharePoint、Teams、および Microsoft 365 管理センターが含まれます。

#### 委任された管理ロールを更新する

ガバナンス関係を通じて割り当てられたロールを変更するには:

1. ガバナンス テナントのガバナンス ポリシー テンプレートを更新します。
2. 更新されたテンプレートを使用して、管理されるテナントに新しいガバナンス要求を送信します。
3. 管理されるテナントは、更新されたロールを適用する要求を確認して受け入れます。

詳細については、「 [テナント間の委任された管理の使用](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-delegated-administration) 」および「 [テナント管理者の管理アクティビティの監視」を](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-monitor-governing-activity)参照してください。

### フェーズ 5: 構成管理を設定する

構成管理を使用すると、目的の構成状態を定義し、テナントの誤差を監視できます。 構成の変更を検出する各テナントで監視を設定します。 背景については、「 [構成管理」を](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)参照してください。

#### アクセス許可の設定

構成管理サービスには、テナント構成を読み取るために明示的なアクセス許可が必要です。

1. グローバル管理者または特権ロール [管理者として Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **テナント ガバナンス**&gt;**構成管理のアクセス許可**を参照します。
3. [ **アプリケーションのアクセス許可** ] タブで、監視するリソースの種類に必要な Microsoft Graph アプリケーションのアクセス許可を追加します (たとえば、条件付きアクセス ポリシーの `Policy.Read.All` )。
4. [ **Entra ロール** ] タブで、必要なロール (Teams リソースの Teams 閲覧者など) を割り当てます。
5. Exchange、セキュリティ、コンプライアンスの各リソースの場合は、それぞれのサービス管理センターでアクセス許可を割り当てます。

詳細な手順については、「 [テナント監視のアクセス許可を設定する」を参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-permissions-tenant-monitoring)。

#### 構成基準を作成する

構成基準は、目的のテナント構成状態の JSON 表現です。

1. 監視するリソースとプロパティを決定します。
2. JSON ベースラインを手動で作成するか、スナップショット API を使用して現在の構成を抽出して開始ベースラインを生成します。
3. ベースラインを編集して、適用する必要がある状態を定義します。

各ベースラインには、最大 200 個のリソース インスタンスを含めることができます。 1 日の全体的な制限は、すべてのモニターで 800 個のリソース インスタンスです。

詳細については、「 [構成管理」を](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)参照してください。

#### モニターを作成する

1. **テナント ガバナンス**&gt;**構成管理**&gt;**Monitors** に移動します。
2. [ **モニターの作成] を選択します**。
3. [ **アクセス許可** ] ステップで、ベースライン内のリソースに必要なアクセス許可を確認して付与します。
4. **[構成基準**] ステップで、JSON ベースラインを貼り付けるか、アップロードします。 続行する前にベースラインを検証します。
5. **[確認**] ステップで、モニター名、説明、およびリソース数を確認します。
6. [ **モニターの作成] を選択します**。

モニターは 6 時間ごとに自動的に実行されます。 結果とドリフト データは、最初の実行後に使用できます。最大 6 時間かかる場合があります。

詳細については、「 [構成モニターの作成」を](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-monitor)参照してください。

#### 結果を確認し、誤差に対処する

1. **テナント ガバナンス**&gt;**構成管理**&gt;**Monitors** に移動します。
2. [ **結果の監視** ] タブを選択して、実行の概要を表示します。
3. [ **構成ドリフト** ] タブを選択して、検出されたドリフトを表示します。
4. 各ドリフトについて、**Drifted プロパティ** の値を選択して、実際の値と予想される値の比較表示を確認します。

誤差に対処するには、ベースラインに一致するようにリソースを更新するか、許容可能な現在の状態を反映するようにベースラインを更新します。 次のモニター実行では、ドリフトが解決済みとして自動的にマークされます。

詳細については、「 [モニターの結果と構成の誤差」を参照](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-see-monitor-results)してください。

### 管理されたテナントを作成する (省略可能)

セキュリティで保護されたアドオン テナント作成機能を使用して、すぐに管理される新しいテナントを作成します。

1. 選択したサブスクリプションのテナント共同作成者ロールまたはサブスクリプション所有者/作成者ロールを使用して、[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Governed Workforce**] オプションを使用して新しいテナントを作成します。
3. Microsoft Entra ID Free 課金資産の Azure サブスクリプションとリソース グループを選択します。

システムはテナントを作成し、既定のポリシー テンプレートを使用してガバナンス関係を確立し、課金資産をプロビジョニングして、関連するテナント インベントリにテナントを追加します。

注

この機能を使用する前に、既定のガバナンス ポリシー テンプレートを構成する必要があります。 既定のテンプレートが存在しない場合、テナントは作成されますが、ガバナンス関係は確立されません。

詳細については、「 [管理された従業員テナントを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-tenant)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/faq"} -->
## Microsoft Entra テナント ガバナンスに関してよく寄せられる質問 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/faq
- Service: entra-id-governance
- Article date: 2026-03-17
- Summary: 検出、ガバナンス関係、監視など、Microsoft Entra テナント ガバナンスに関する一般的な質問に対する回答を見つける

この記事では、Microsoft Entra テナント ガバナンスに関してよく寄せられる質問に回答します。

### 全般

#### Microsoft Graph API を使用してテナント ガバナンスを管理できますか?

はい。テナント ガバナンスを管理するために、一連の Microsoft Graph API を使用できます。 詳細については、 [Microsoft Graph API のドキュメントを参照してください](https://aka.ms/TenantGovernance/MSGraphAPI)。

#### テナント ガバナンスを使用するにはライセンスが必要ですか?

テナント ガバナンスは、無料、基本、およびプレミアムの機能を提供します。 機能ごとのライセンス要件の包括的な一覧については、 [Microsoft Entra のライセンスを](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-tenant-governance)参照してください。

#### Microsoft Entra テナント ガバナンスとマルチテナント組織機能の比較

Microsoft Entra テナント ガバナンスとマルチテナント組織機能は補完的です。 テナント ガバナンスにより、テナントの検出、テナント間の管理アクセス、テナント内の構成の監視が可能になります。 マルチテナント組織機能を使用すると、Microsoft 365 サービス内の Microsoft Entra テナント間でのエンドユーザーコラボレーションが可能になります。 組織内では、ガバナンス関係に関連するテナントの一覧は、マルチテナント組織内のテナントの一覧から独立しています。

### 関連テナント

#### 関連するすべてのテナントを所有していますか?

No. 関連するテナントは、検出されたテナントの所有権を意味するものではありません。 関連するテナントは、探索シグナルに基づいて、テナントとの履歴またはアクティブな関係を持つテナントを表します。 この機能は、ID、アプリケーション、および課金システム全体に既に存在する証拠に基づいてテナント接続を表示することで、状況認識を提供します。 この認識により、組織は情報に基づいたガバナンスの決定を行うことができます。

#### テナント ガバナンスで、認識できない関連テナントが表示された場合はどうすればよいですか?

まず、テナントがコマースの課金アカウントを共有しているかどうかを確認します。 多くの場合、共有課金アカウントは、管理が必要なテナントを示します。 そのテナント内のユーザーは、課金アカウントにアクセスまたは変更するためのアクセス許可を持っているか、課金アカウントで支払われたサブスクリプションを持っている可能性があります。

また、B2B コラボレーション シグナルと、テナントと関連テナントの間のマルチテナント アプリケーションの使用状況を確認することもできます。 B2B の登録パターンとサインイン パターンによって、ユーザーが認識する対話が明らかにされる場合があります。 また、メール スキャン機能を使用して、それらの他のテナントに関する管理メールを検出することもできます。 必要に応じて、Exchange Online や Teams などの組織のコミュニケーション ツールを使用するユーザーに連絡して、リレーションシップが存在する理由を理解します。

#### 別のテナントの管理者がガバナンス要求を承認しない場合、組織を保護するにはどうすればよいですか?

別のテナントの管理者が拒否した場合、またはガバナンス要求に応答しない場合は、検疫ワークフローを実行して、関連するテナントのアクションから組織を保護します。 承認 [されていないテナントの検疫](https://learn.microsoft.com/ja-jp/entra/fundamentals/quarantine-unsanctioned-tenants) に関するページの手順に従って、他のテナントのユーザーまたはアプリケーションがリソースにアクセスできないようにします。 このアプローチにより、テナント内のデータのセキュリティとプライバシーが保護されます。

テストとして、送信 B2B ユーザー アクセスまたはコマース サービス のプロビジョニングを切断することを検討してください。 このアクションにより、他のテナントに要求の再考を求めるメッセージが表示される場合があります。

#### テナント ガバナンスは B2B 機能とどのように関連していますか?

一部のお客様は、マルチテナント管理に B2B アカウントを使用しています。 この方法では、すべてのテナントで B2B ユーザーごとにロールを割り当てる必要があります。 きめ細かい委任された管理者特権 (GDAP) は、B2B コラボレーション機能に代わることはありません。 ただし、マルチテナント管理のニーズでは、多くの場合、ガバナンス関係の GDAP アカウントを使用すると、最小限の特権のアクセス許可の割り当てと保守が容易になります。

### ガバナンス関係

#### クラウド サービス プロバイダー (CSP)、マネージド サービス プロバイダー (MSP)、またはマネージド セキュリティ サービス プロバイダー (MSSP) です。 これらの機能を使用して顧客テナントを管理できますか?

Yes. クラウド ソリューション プロバイダー (CSP)、マネージド サービス プロバイダー (MSP)、およびマネージド セキュリティ サービス プロバイダー (MSSP) は、テナント ガバナンス関係を使用して顧客テナントを管理できます。

ただし、テナント ガバナンスリレーションシップは、パートナー センターを通じて同じ 2 つのテナント間で [構成された GDAP リレーションシップ](https://learn.microsoft.com/ja-jp/partner-center/customers/gdap-introduction) と共存することはできません。 顧客テナントとのパートナー センター GDAP リレーションシップが既にある場合は、同じテナントとのテナント ガバナンス関係を作成する前に、まずそのパートナー センターのリレーションシップを削除する必要があります。

#### 管理されたテナントは独立して関係を終了できますか?

Yes. 管理されるテナントは、常にガバナンス関係を終了できます。 管理テナントの管理者は、管理されたテナントがリレーションシップを終了し、電子メール通知を受信したことを確認します。

#### テナント ガバナンスは多層リレーションシップをサポートしていますか?

No. 多層リレーションシップは現在サポートされていません。 たとえば、テナント A がテナント B を管理し、テナント B がテナント C を管理するガバナンス関係を設定することはできません。テナントを管理テナントと管理テナントの両方にすることはできません。 リレーションシップの設定がこの規則を破ることをシステムが検出した場合、API 呼び出しは失敗します。

#### ガバナンス関係は PIM をサポートしていますか?

Yes. [グループに PIM を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups)使用すると、管理者は、リレーションシップで構成された GDAP アクセスを使用して他のテナントを管理する前に、管理テナントのグループ メンバーシップをアクティブ化できます。 管理テナントで構成され、管理テナント管理者に適用される PIM ポリシーはサポートされていません。

### 構成管理

#### 監視できる Microsoft のサービスとリソース

Microsoft テナント構成 API でサポートされている 200 を超えるリソースの種類のいずれかを監視します。 これらのリソースは、Microsoft Entra、Intune、Exchange Online、Teams、Security & Compliance (Defender および Purview) にまたがります。 サポートされているリソースの種類の完全な一覧については、テナント構成管理のドキュメント( [Entra](https://learn.microsoft.com/ja-jp/graph/utcm-entra-resources)、 [Exchange](https://learn.microsoft.com/ja-jp/graph/utcm-exchange-resources)、 [Intune](https://learn.microsoft.com/ja-jp/graph/utcm-intune-resources)、 [セキュリティとコンプライアンス](https://learn.microsoft.com/ja-jp/graph/utcm-securityandcompliance-resources)、 [Teams](https://learn.microsoft.com/ja-jp/graph/utcm-teams-resources)) を参照してください。

#### 構成基準の外観と作成方法

構成基準は、目的のテナント構成状態の宣言型 JSON 表現です。 手動で作成するか、スナップショット API を使用してテナントの現在の構成を抽出し、目的の状態に編集して開始ベースラインを生成します。

#### モニターはどのくらいの頻度で実行され、監視の頻度を制御できますか?

モニターを作成すると、それは定期的に自動的に実行されます。 既定の実行間隔は 6 時間ごとです。

#### 構成基準のサイズに制限はありますか?

監視ベースラインには、最大 200 個のリソース インスタンスを含めることができます。 1 日の全体の制限は、テナント内のすべてのモニターで 800 個のリソース インスタンスです。

#### 許可されているよりも多くのリソースを監視またはスナップショットを作成しようとするとどうなりますか?

新しいモニターまたはスナップショット ジョブは、作成時に失敗します。 たとえば、既存のモニターが既に 800 個のリソース インスタンスの完全な日次クォータを使用している場合、別のモニターを作成しようとすると、制限を超えるため失敗します。

#### 監視サービスが構成の誤差を検出した場合、どのように修正すればよいですか?

選択したツールを使用して、管理センター、PowerShell、Graph API などのリソース構成を更新します。 次にモニターを実行すると、リソースが構成基準と一致することが検出され、ドリフトがアクティブではなくなったと自動的にマークされます。

#### 同じベースラインを使用して複数のテナント間の構成を監視できますか?

同じ構成基準ファイルを再利用して、異なるテナントにモニターを作成します。 複数のテナントにモニターを作成したり、複数のテナントの構成のずれを検出したりするには、各テナントに個別にサインインし、そのテナントでタスクを実行します。

#### 複数のモニターで同じリソースに対して異なる必須状態が定義されている場合はどうなりますか?

どちらのモニターも正常に実行されますが、それぞれが独自のベースラインに対するドリフトを個別に評価します。 たとえば、モニター 1 でプロパティ A が `true` され、モニター 2 でプロパティ A が `false`されると想定されている場合、モニターは異なる結果を報告します。 実際の状態が `true`場合、モニター 2 のみがドリフトを報告します。 ベースラインの競合を回避するために、リソースごとに 1 つの必要な状態を定義します。

### セキュリティで保護されたテナントの作成

#### テナントを作成するときにサブスクリプションを選択する必要がある理由

セキュリティで保護されたアドオン テナント作成機能を使用するには、Microsoft 顧客契約 (MCA) サブスクリプションに対する所有者または共同作成者のアクセス許可が必要です。 選択したサブスクリプションは、システムが新しいテナントの Microsoft Entra ID Free 課金資産を格納する場所です。

#### Microsoft Entra ID 無料課金資産とは

Microsoft Entra ID Free は、課金アカウント内の Microsoft Entra テナントを表す無料の課金資産です。 この資産は、テナントの商用所有権と法的所有権を示します。 Microsoft サポート チームは、これを使用してアクセスを復元したり、機密性の高い要求を安全に処理したりできます。 各課金資産は 1 つの Microsoft Entra テナントに直接関連付けられており、グローバル管理者によってテナントが削除されない限り、有効期限が切れたり削除されたりすることはありません。 詳細については、「 [Microsoft Entra ID Free」を](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/microsoft-entra-id-free)参照してください。

#### アドオン テナントはどのように管理されますか?

組織内のユーザーが、Microsoft Entra 管理センターのセキュリティで保護されたアドオン テナント作成フロー (または API を介して) を使用して新しいテナントを作成すると、システムによってガバナンス関係が自動的に作成されます。 この関係は、既定のガバナンス ポリシー テンプレートを使用して、ホーム (管理) テナントとアドオン (管理) テナントを接続します。 既定のテンプレートが定義されていない場合、リレーションシップは確立されません。

#### 管理されたテナントからアドオン テナントを作成できますか?

Yes. 多層ガバナンスリレーションシップはサポートされていませんが、新しいテナントの作成には例外が存在します。 テナントが別のテナントによって管理されている場合でも、アドオン テナントを作成できます。 これらのアドオン テナントは、あなたのテナントによって管理されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/governance-policy-templates"} -->
## ガバナンス ポリシー テンプレート - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates
- Service: entra-id-governance
- Article date: 2026-07-01
- Summary: ガバナンス ポリシー テンプレートと、それらを使用して Microsoft Entra のテナント間で一貫したガバナンスを適用する方法について説明します

ガバナンス ポリシー テンプレートは、テナント ガバナンス サービスの基本コンポーネントであり、組織が Microsoft Entra テナントを大規模にセキュリティで保護するのに役立ちます。 テナント間でガバナンス関係を確立する前に、リレーションシップの動作を定義するガバナンス ポリシー テンプレートを作成します。 これらのテンプレートは、異なるガバナンス 関係間で再利用可能であり、テナント間アクセスの一貫性のあるスケーラブルな管理を可能にします。

### ガバナンス ポリシー テンプレートのしくみ

ガバナンス ポリシー テンプレートは、ガバナンス関係のブループリントとして機能します。 テンプレートを作成するときは、次の 2 つの主要なアクセス領域を定義します。

- テナント間の委任された管理ロール - 管理テナントのユーザーが管理テナントに持つ Microsoft Entra 組み込みロールを指定します。
- マルチテナント アプリケーション - テナント間で作成および管理するカスタム アプリケーションを選択します。

テンプレートを作成したら、それを使用して、異なるガバナンス テナントとの複数のガバナンス関係を確立し、組織全体で一貫したアクセス ポリシーを確保できます。

ガバナンス関係を作成すると、テナント ガバナンスによって、そのリレーションシップを使用してポリシー スナップショットがキャプチャされ、格納されます。 このスナップショットは、リレーションシップを確立または最後に更新した時点で適用されたロールとアクセス許可を表します。

ガバナンス ポリシー テンプレートを更新しても、そのテンプレートを使用して作成したリレーションシップは自動的に更新されません。 この設計により、管理されるテナントは常に、リレーションシップの必要なアクセス許可の変更を確認できます。 アクティブなリレーションシップにアクセス許可の更新を適用するには、要求と承認のプロセスを繰り返す必要があります。

### テナント間の委任管理構成

Microsoft Entra 組み込みロールを選択し、それらを管理テナントのグループに割り当てることで、そのグループ内のどのロール (およびアクセス レベル) ユーザーが管理テナントに含まれるかを定義します。 これらのロールを使用すると、ユーザーは次のことができます。

- 管理対象のテナントに、管理者テナントの資格情報を使用してサインインします。
- そのテナントにローカルまたは企業間 (B2B) アカウントを必要とせずに、管理されたテナントを管理します。

各グループには複数のロールの割り当てを割り当てることができ、各ポリシー テンプレートには複数のグループを定義できます。 ガバナンス関係を作成すると、テナント ガバナンスによって、管理されるテナントに [詳細な委任された管理者特権 (GDAP)](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/cross-tenant-delegated-administration) ロールの割り当てが作成されます。

### マルチテナント アプリケーションの構成

ポリシー テンプレートでカスタムのマルチテナント アプリケーションを選択することで、一元化されたアプリケーション管理を有効にすることができます。 ガバナンス関係を作成すると、テナント ガバナンスによって、管理されるテナントに同じアクセス許可を持つサービス プリンシパルが作成されます。

この機能を使用すると、中央管理テナントから大規模なカスタムマルチテナント アプリケーションを管理できます。 最小限の特権を持つアプリ アクセスを監視および維持するために、すべてのテナントに個別にアクセスする必要はありません。

たとえば、Contoso Resource Manager という名前のカスタム 基幹業務アプリを構築し、テナント全体のリソース構成の監視、レポート、自動化を担当しているとします。 ガバナンス関係を使用して、適切なプロビジョニングされたアクセス許可が同意された状態で、管理テナント全体に Contoso Resource Manager のサービス プリンシパル インスタンスを設定します。 アクセス許可を追加または削除する必要がある場合は、テナントごとに変更を加えたりアクセス許可に同意したりするのではなく、ガバナンス関係を通じて行います。

### 既定のポリシー テンプレート

既定のポリシー テンプレートは、セキュリティで保護されたテナント作成シナリオに使用される特別なテンプレートです。 新しいアドオン テナントを作成すると、テナント ガバナンスは、既定のポリシー テンプレートを使用して、親テナントとアドオン テナントの間にガバナンス関係を自動的に確立します。 このセットアップにより、新しいテナントが最初から一元化されたテナント管理の対象になります。

既定のポリシー テンプレートには、次の特性があります。

- **ユニークID**: GUID の代わりに、既定のポリシー テンプレートの ID は「default」です。
- **構成が必要**: 既定のポリシー テンプレートを使用するには、事前に構成する必要があります。

### 制限事項

ガバナンス ポリシー テンプレートには、次の制限事項が適用されます。

| 制限 | Value |
| --- | --- |
| テンプレートあたりのマルチテナント アプリケーションの最大数 | 10 |
| マルチテナント アプリケーションあたりのアクセス許可の最大数 | 100 |
| テンプレートあたりのロールの割り当ての最大数 | 10 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/governance-relationships"} -->
## テナント ガバナンスにおけるガバナンス関係 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships
- Service: entra-id-governance
- Article date: 2026-03-10
- Summary: ガバナンス関係と、Microsoft Entra テナント ガバナンスでテナントの一元管理を可能にする方法について説明します

ガバナンス関係により、2 つの Microsoft Entra テナント間に方向接続が確立されます。 1 つのテナント ( *管理* テナント) が別のテナント ( *管理テナント* ) を管理します。 これらの関係により、組織は一元的な場所から複数のテナントを大規模に安全に管理できます。

ガバナンス関係により、次の 4 つの主要なシナリオが可能になります。

| シナリオ | 説明 |
| --- | --- |
| テナント間で委任された管理権限 | ガバナンス関係を使用して、複数の Microsoft Entra テナント間 **で最小特権の管理アクセス** を一元化します。 管理者は、管理テナントのアカウントを使用してサインインします。 この方法により、すべての管理テナントでローカルまたは B2B 管理者アカウントを作成および管理する必要がなくなります。 |
| マルチテナント アプリケーション管理 | 管理テナントからカスタムのマルチテナント アプリケーションを管理します。 管理者は、各テナントに個別にサインインすることなく、管理されているテナント間で最小特権のアプリケーション アクセスを監視および維持できます。 この方法により、運用上のオーバーヘッドと構成の誤差が軽減されます。 |
| テナント構成の管理 | ガバナンス関係でテナント間の委任された管理を構成した場合は、この管理アクセスを使用して、テナントが組織のセキュリティとコンプライアンスの目標を継続的に満たしていることを確認します。 |
| セキュリティで保護されたテナントの作成 | 既存のテナントから新しいアドオン テナントを作成すると、テナント ガバナンスは、既定のガバナンス ポリシー テンプレートを使用して、親テナントと新しいテナントの間にガバナンス関係を自動的に確立します。 この手順により、新しく作成されたテナントが一元化された管理とガバナンスの制御下にすぐに移動され、管理されていないテナントや正しく構成されていないテナントのリスクが軽減されます。 |

### リレーションシップ ハンドシェイク

2 つの Microsoft Entra テナントは、3 ステップハンドシェイクを通じて新しいガバナンス関係を作成できます。 新しいガバナンス関係を作成したり、既存のガバナンス関係を更新したりするには、両方のテナントの管理者が、管理テナントに対して持つロールとアクセス許可を構成し、同意する必要があります。

1. 将来の統治されるテナントは、将来の統治するテナントにガバナンスの招待を送信します。
2. ガバナンスへの招待を受け取ると、将来のガバナンス テナントは、将来のガバナンス テナントにガバナンス要求を送信します (選択したガバナンス ポリシー テンプレートを使用)。
3. 将来管理されるテナントが要求を確認して受け入れると、テナントはガバナンス関係を確立します。

これらの条件を満たすテナントは、招待手順をスキップできます。

- 将来の管理テナントは、共有課金アカウントを使用したテナント検出を通じて、将来管理されるテナントを関連テナントとして識別します。
- アクティブなガバナンス関係のテナントは、招待手順をスキップしてリレーションシップを更新したり、新しいリレーションシップを作成したりすることができます。

### リレーションシップのライフサイクル

ガバナンス関係は、作成から終了まで、複数の状態を経て移動します。 次のセクションでは、要求と確立されたリレーションシップの両方の状態について説明します。

#### リクエストの状態

ガバナンス要求は、次のステージで進行します。

| 状態 | 説明 |
| --- | --- |
| 保留中 | 管理テナントは要求を送信し、管理されたテナントからの応答を待機します。 |
| 承認済み | 管理されたテナントが要求を受け入れ、ガバナンス関係を作成しました。 |
| 拒否 | 管理されたテナントが要求を拒否しました。 |

#### リレーションシップの状態

ガバナンス関係は、次の状態を経て進行します。

| 状態 | 説明 |
| --- | --- |
| アクティブ | 関係が確立され、運用されています。 |
| 終了が要求されました | 管理テナントは、リレーションシップの終了を要求しました。 |
| 終了 | 両方のテナントがリレーションシップを終了し、テナント ガバナンスによって関連するすべてのリソースが削除されました。 |

### ガバナンス モデル

テナントのペア間にガバナンス関係を設定する場合は、サポートされているこれらのモデルに注意してください。

| サポートされていますか？ | モデルの種類 | 説明 |
| --- | --- | --- |
| ✅ | 1 対多 | テナントは、複数のテナントを管理できます。 |
| ✅ | 多対一 | 複数の管理者が 1 つのテナントを管理することができます。 |
| ❌ | 多層 | テナントは管理する役割のテナントと管理される役割のテナントを同時に兼ねることはできません。 たとえば、Contoso が Fabrikam を管理している場合、Fabrikam は別のテナントの管理を要求できません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-create-monitor"} -->
## 構成モニターを作成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-monitor
- Service: entra-id-governance
- Article date: 2026-07-28
- Summary: Microsoft Entra テナント ガバナンスで構成モニターを作成し、構成基準に対してテナントを評価し、誤差を報告する方法について説明します

構成モニターは、構成基準に対してテナントを評価し、構成の誤差を報告します。 テナントが既知の適切な構成に準拠しているかどうかを追跡する場合は、モニターを使用します。

### Prerequisites

- テナントには、 **テナント ガバナンス Basic** または **テナント ガバナンス Premium** のライセンスがあります。 現在のライセンス要件については、「[テナント ガバナンス のライセンスMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/licensing)」を参照してください。
- テナント ガバナンス Basic には、監視できるリソースの数のクォータが含まれています。 モニター内のリソースによってテナントがそのクォータを超えた場合、モニターの作成は失敗します。 組織は、テナント ガバナンス Premium ライセンスごとに監視対象リソースの追加クォータを取得します。
- サインインしているユーザーは、Microsoft Entra特権ロールにあり、構成モニターを作成するアクセス許可を持っています。 ユーザーには、モニターの構成基準に含まれるリソースの種類に対する読み取りアクセス許可も必要です。
- テナント構成管理サービスには、監視ベースラインに含まれるワークロードとリソースの種類に対するアクセス許可があります。 サービスのアクセス許可を割り当てるまたは削除するには、「 [構成管理サービスのアクセス許可を構成する」](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-permissions-tenant-monitoring)を参照してください。

### モニターの作成を開始する

構成モニターの作成を開始するには、次の手順に従います。

1. 必要なロールとアクセス許可を持つユーザーとして[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **テナント ガバナンス**&gt;**Monitors** に移動します。
3. **新規**をクリックします。

### モニター ウィザードを完了する

ウィザードの手順を完了して、モニターを構成して作成します。

1. **[設定]** で、少なくとも 8 文字の一意のモニター名とオプションの説明を入力します。
2. **構成基準**で、ベースライン JSON ファイルをアップロードするか、**スナップショットからインポート**を選択します。 [ **スナップショットからインポート]** を選択した場合は、スナップショット名で検索し、スナップショットを選択して、[インポート] を選択 **します**。 ページ内エディターを使用して、構成基準を手動で作成または編集できます。
3. **[アクセス許可]** で、サービスに次のアクセス許可があるかどうかを確認します。

    - Microsoft Entraまたは Intune リソースを監視するために必要なMicrosoft Graph リソースを読み取る。
    - Exchangeに対して認証し、Exchange、Defender、または Purview リソースを監視します。
    - Teams のリソースを監視するには、 **Teams 閲覧者** ロールを使用します。

    Note

    この手順では、選択したリソースでモニターを作成する権限がユーザーに付与されているかどうかを評価しません。 また、構成管理サービスに割り当てられたアクセス許可が、Exchange Online、Defender、または Purview 内でローカルに割り当てられているので、それらのサービス内のリソースを含むモニターを作成するのに十分かどうかも表示または検証されません。
4. **[確認**] で概要を確認し、モニターを作成します。

モニターを作成すると、定期的なスケジュールで自動的に実行されます。 モニターの結果は、モニターが初めて実行された後、作成後 0 から 6 時間の間に使用できます。

モニターの結果と構成の誤差を確認する方法については、 [モニターの結果の表示とモニターの管理に関するページを](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-see-monitor-results)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-create-snapshots"} -->
## 構成スナップショットを作成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-snapshots
- Service: entra-id-governance
- Article date: 2026-07-28
- Summary: Microsoft Entra テナント ガバナンスで構成スナップショットを作成して、ベースラインまたは監査証拠のテナント構成をキャプチャする方法について説明します

構成スナップショットは、選択したテナント構成リソースの現在の状態をキャプチャします。 既知の適切な構成でテナントの構成ドリフトを監視するための構成基準を確立する場合、または監査証拠の構成データを収集する場合は、スナップショットを作成します。

### Prerequisites

- テナントには、 **テナント ガバナンス Basic** または **テナント ガバナンス Premium** のライセンスがあります。 現在のライセンス要件については、「[テナント ガバナンス のライセンスMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/licensing)」を参照してください。
- テナント ガバナンス Basic には、スナップショットを作成できるリソースの数のクォータが含まれています。 テナントは、テナント ガバナンス Premium ライセンスごとにスナップショットされたリソースの追加クォータを取得します。 テナントが月単位のクォータを超えている場合、新しいスナップショットを作成することはできません。
- サインインしているユーザーはMicrosoft Entra特権ロールにあり、スナップショットに含まれるすべてのリソースの種類に対する読み取りアクセス許可を持っています。
- テナント構成管理サービスには、スナップショットに含まれるすべてのワークロードとリソースの種類に対するサービス承認があります。 サービスのアクセス許可を割り当てるまたは削除するには、「 [構成管理サービスのアクセス許可を構成する」](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-permissions-tenant-monitoring)を参照してください。

### スナップショットの作成

構成スナップショットを作成するには、次の手順に従います。

1. 必要なロールとアクセス許可を持つユーザーとして[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **テナント ガバナンス**&gt;**スナップショット**を参照します。
3. [ **新しいスナップショット]** を選択します。
4. サービスとリソースの選択手順で、スナップショットに含めるリソースの種類を選択します。
5. **[設定]** で、少なくとも 8 文字の一意の表示名とオプションの説明を入力します。
6. **[アクセス許可]** で、サービスに次のアクセス許可があるかどうかを確認します。

    - 必要なMicrosoft Graph リソースを読み取り、Microsoft Entraまたは Intune リソースのスナップショットを作成します。
    - Exchange、Defender、または Purview リソースのスナップショットを作成するために、Exchangeに対して認証します。
    - Teams のリソースのスナップショットを作成するには、 **Teams 閲覧者** ロールを使用します。

    Note

    この手順では、選択したリソースでスナップショットを作成する権限がユーザーに付与されているかどうかを評価しません。 また、構成管理サービスに割り当てられたアクセス許可が、Exchange Online、Defender、または Purview 内でローカルに割り当てられているので、それらのサービス内のリソースを含むスナップショットを作成するのに十分かどうかも表示または検証されません。
7. [ **確認と作成**] で概要を確認し、スナップショットを作成します。

### スナップショットの状態を確認する

スナップショットの作成は非同期です。 スナップショットが [ **未開始** ] から **[進行中] に進行します**。 スナップショットが成功、失敗、または部分的に成功するまで進行状況を確認するには、コマンド バーの **[更新** ] を選択します。 スナップショットの完了にかかる時間は、スナップショットを作成するリソースの数とほぼ比例します。

### スナップショットの詳細を表示する

完了したスナップショットの詳細を表示するには、次の手順に従います。

1. スナップショットの一覧から完了したスナップショットを開きます。
2. [ **概要** ] タブで、名前、説明、作成時刻、完了時間、有効期限、状態、および含まれるリソース数を確認します。 エラーがある場合は、このページに表示されます。 エラーを展開して詳細を表示できます。
3. [ **構成基準** ] タブで、生成された構成基準 JSON を表示またはダウンロードします。

### スナップショットからモニターを作成する

完了したスナップショットから構成モニターを作成するには、次の手順に従います。

1. 完了したスナップショットを開きます。
2. **モニターとして作成** を選択します。
3. スナップショット ベースラインがインポートされた状態で、モニター作成ウィザードが開きます。 スナップショットのみのフィールド ( `@odata` メタデータやオブジェクト ID など) はベースラインから削除されます。 [「構成](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-monitor)モニターの作成」の説明に従って、モニターの名前の設定やアクセス許可の確認など、モニター作成エクスペリエンスの残りの手順に進みます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-create-tenant"} -->
## ガバナンスが適用されたワークフォース テナントを作成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-tenant
- Service: entra-id-governance
- Article date: 2026-08-26
- Summary: 管理されたMicrosoft Entraワークフォース テナントを安全に作成し、ホーム テナントからガバナンスを確立する方法について説明します。

この記事は、既存のMicrosoft Entra テナントから管理されるアドオン テナントを作成する必要がある IT 管理者向けです。 セキュリティで保護されたアドオン テナント作成フローを使用する前に、前提条件を確認してください。

Microsoft Entra 管理センターの **[Governed Workforce]** オプションを使用してテナントを作成すると、セキュリティで保護されたアドオン テナントの作成フローが自動的に行われます。

- 新しいワークフォース テナントを作成します
- ホーム テナントに既定のガバナンス [ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)がある場合は、ホーム テナントと新しいテナントの間にガバナンス[関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships)を確立します
- 選択したAzure サブスクリプションとリソース グループにMicrosoft Entra ID[無料の課金資産](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/microsoft-entra-id-free)をプロビジョニングします

この記事では、コンシューマー向けアプリの外部テナント構成の作成については説明しません。 顧客 ID とアクセス管理のシナリオについては、[顧客のMicrosoft Entra 外部 IDを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)。

### 前提条件

管理された従業員テナントを作成する前に、次の要件を確認します。

- ホーム テナントには、少なくとも 1 つの有料のライセンスベースのMicrosoft製品 (P1 または P2、Microsoft 365、Windows Enterprise E3 Microsoft Entra IDなど) があります。 無料ライセンスと試用版ライセンスは対象外です。
- 有料の [エンタープライズ契約 (EA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-ea-roles) または [従量課金制](https://azure.microsoft.com/pricing/offers/ms-azr-0003p?cid=msft_learn) サブスクリプションがあります。 [マイクロソフト オンライン サブスクリプション契約 (MOSA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts#microsoft-online-services-program) と [Microsoft 顧客契約 (MCA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts#microsoft-customer-agreement) の両方の課金アカウントがサポートされています。 課金アカウントの種類を特定するには、[Azure ポータルで課金アカウントを表示するを](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts)参照してください。
- アカウントに [テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator) ロールがあります。 このロールは、[ [**管理者以外のユーザーによるテナントの作成を制限する**](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#restrict-member-users-default-permissions) ] 設定に関係なく必要です。
- **テナント共同作成者**ロールまたはサブスクリプション**所有者/作成者**ロールを使用して、選択したサブスクリプションに必要なAzure Resource Manager (ARM) アクセス許可を持っている。
- (省略可能)ホーム テナントには、**既定**の[ガバナンス ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)が構成されています。 テナント作成サービスでは、既定のテンプレート (ID: `default`) のみが使用されます。 既定のテンプレートが定義されていない場合、セキュリティで保護されたアドオン テナント作成フローでは、他のテンプレートが存在する場合でも、ガバナンス関係は確立されません。

### テナントを作成する

管理されたワークフォース テナントを作成する手順については、「**クイック スタート: Microsoft Entra ID で新しいテナントを作成**する」の「[ガバナンスされた従業員](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)」タブを参照してください。

### テナント作成後に何が起こるか

システムがテナントを作成した後:

1. ホーム テナントに [既定のガバナンス ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)がある場合は、ホーム テナントと新しいテナントの間にガバナンス関係が形成されます。 このテンプレートでは、テナント間アクセス設定、詳細な委任された管理者特権 (GDAP) の割り当て、サービス プリンシパルなど、リソースがプロビジョニングされます。
2. [Microsoft Entra ID無料課金資産](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/microsoft-entra-id-free)は、選択したリソース グループの下の Azure サブスクリプションに表示されます。
3. 新しいテナントが [関連するテナント インベントリに](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/related-tenants) 表示されます。

ガバナンス関係とポリシー テンプレートの詳細については、ガバナンス [関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships) と [ガバナンス ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-delegated-administration"} -->
## テナント間の委任された管理を使用する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-delegated-administration
- Service: entra-id-governance
- Article date: 2026-07-27
- Summary: テナント間の委任された管理を使用して、管理テナントの資格情報を使用して、管理されたテナントにサインインして管理する方法について説明します

この記事では、管理下のテナントに委任された管理者としてサインインし、委任された管理ロールを管理する方法について説明します。

テナント間の委任された管理を使用すると、管理テナントの管理者は、自分の管理テナントの資格情報を使用して、管理対象テナントにサインインし、管理を行うことができます。 この機能では、管理される各テナントにローカル アカウントまたは B2B アカウントは必要ありません。 詳細な委任された管理者特権 (GDAP) テクノロジを使用して、一元化された最小特権のテナント間アクセスを提供します。

### 前提条件

- ガバナンス ポリシー テンプレートで委任された管理が設定された、統治を行うテナントと統治されるテナントとの間のアクティブなガバナンス関係。 詳細については、 [GDAP でサポートされるワークロードに関する説明を](https://learn.microsoft.com/ja-jp/partner-center/customers/gdap-supported-workloads)参照してください。
- 管理者は、ガバナンス関係が指定する管理テナント内のセキュリティ グループに属している必要があります。

### 管理されたテナントに委任された管理者としてサインインする

ガバナンス関係がアクティブになり、GDAP ロールの割り当てが行われた後、構成されたセキュリティ グループのメンバーは、管理されたテナントにサインインできます。 アカウントが、ガバナンス ポリシー テンプレートでロールが割り当てられているガバナンス テナントのセキュリティ グループのメンバーであることを確認します。

管理されるテナントには、次の 2 つの方法でサインインできます。

- Microsoft Entra 管理センターの **[管理されたテナント**] ページから。
- サポートされている管理ポータルの URL を直接開きます。

#### Microsoft Entra 管理センターからサインインする

**[管理されたテナント**] ページを使用して、管理されたテナントにサインインし、開く管理ポータルを選択します。

1. 管理テナントで、**Microsoft Entra 管理センター**の [\[管理されたテナント](https://entra.microsoft.com)] ページに移動します。
2. サインインする管理対象のテナントを選択してください。
3. コマンド バーで、[ **テナントにサインイン**] を選択します。 次を示すサイド ウィンドウが開きます。

    - 管理されるテナントにサインインできるかどうか。
    - 管理対象テナントで割り当てられるロール。
4. グループ メンバーシップが Privileged Identity Management (PIM) で構成されていて、メンバーシップが対象である場合、サイド ウィンドウに **[アクティブ化**] ボタンが表示されます。 サインインする前に、有効なグループ メンバーシップをアクティブ化するには、[ **アクティブ化] を** 選択します。
5. サイド ウィンドウで、開く管理ポータルを選択します。 新しいタブが開き、認証を求められます。
6. 管理テナントの資格情報を使用してサインインし、管理されたテナントにアクセスします。

注

同じペアのテナント間に複数のアクティブなリレーションシップがある場合、サイド ウィンドウには、テナントにサインインするときに取得するすべてのロールが表示されない場合があります。 サイド ウィンドウには、選択したリレーションシップのスコープ内にあるロールのみが表示されます。

#### 管理ポータルの URL を使用してサインインする

1. サポートされている管理ポータルの URL を開き、管理されるテナントのドメインまたはテナント ID を追加します。 サポートされているポータルとワークロードの一覧については、 [GDAP でサポートされているワークロード](https://learn.microsoft.com/ja-jp/partner-center/customers/gdap-supported-workloads)に関する説明を参照してください。 例えば次が挙げられます。

    `https://entra.microsoft.com/{governed-tenant-domain-or-id}`
2. 管理テナントの資格情報を使用してサインインします。
3. サインインが成功したら、セキュリティ グループに割り当てられたロールに基づいて、管理テナントで管理タスクを実行します。

    Important

    通常のユーザーとは異なるユーザー情報が表示されます。

    - 表示名が `user_{your user object ID in the governing tenant without dashes}`として表示されます。
    - 管理対象テナントのサインイン記録と監査ログには、表示名が `{Governing tenant name} Technician` と表示されます。

### 委任された管理ロールを更新する

委任された管理者が使用できるロールを追加または変更するには、ガバナンス ポリシー テンプレートを更新し、新しいガバナンス要求を送信します。

1. ガバナンス テナントで、ガバナンス ポリシー テンプレートを更新して、Microsoft Entra 組み込みロールとセキュリティ グループの割り当てを追加または変更します。

    注

    テンプレートを更新すると、そのバージョン番号が 1 ずつインクリメントされます。
2. 更新されたポリシー テンプレートを使用して、管理されるテナントに新しいガバナンス要求を送信します。
3. 管理されるテナントの管理者は、更新されたロールを適用する要求を確認して受け入れます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-enable-tenant-discovery"} -->
## テナントの検出を有効にする - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-enable-tenant-discovery
- Service: entra-id-governance
- Article date: 2026-03-10
- Summary: Microsoft Entra テナント ガバナンスでテナント検出を有効にして、組織全体の関連テナントを識別する方法について説明します

関連テナントは、管理者が自分のテナントと監視可能な関係を持つ他の Microsoft Entra テナントを検出するのに役立ちます。 Microsoft Entra は、B2B コラボレーション、マルチテナント アプリケーションの同意、共有課金アカウントなどのアクティビティ シグナルからこれらの関係を推測します。 関連するテナントは、所有権や管理制御を意味しません。 これは、Microsoft サービス間で観察された関連付けを示します。

関連するテナント検出を有効にした後、テナントがライセンス要件を満たしている場合は、テナントに対して有効なままになります。

### 前提条件

関連するテナントを有効にする前に、次の事項を確認してください。

- 関連するテナントを有効にするために必要なアクセス許可があります。 テナント ガバナンス管理者またはグローバル管理者の Microsoft Entra ロールを保持する必要があります。
- 適切なライセンスを持っている場合、あなたのテナントは関係のある他のテナントの対象となります。
- この設定はトグルではなく、有効にした後も有効なままであることを理解しています。

### Microsoft Entra 管理センターを使用して関連テナントを有効にする

API ではなく管理センターを使用して検出を有効にするには、このオプションを使用します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも**テナント ガバナンス管理者**としてサインインします。
2. **テナント ガバナンス**&gt;**関連するテナントを参照します**。
3. 関連するテナントを有効にする方法の説明を確認します。
4. **[関連するテナントの検出] を選択します**。

設定を有効にすると、Microsoft Entra は検出シグナルの集計を開始し、管理センターで関連するテナントを表示します。 発見データは既存のアクティビティから合成され、反映に時間がかかる場合があります。

### Microsoft Graph API を使用して関連テナントを有効にする

スクリプトまたは自動化による検出を有効にするには、このオプションを使用します。

**エンドポイント**

```http
POST /directory/tenantGovernance/settings/enableRelatedTenants
```

このアクションにより、呼び出し元のテナントの関連テナント検出が有効になります。

**重要な注意点**

- この設定は、既定で新しいテナントの `false` に設定されます。
- 設定を有効にした後、`isRelatedTenantsEnabled`は`true`に変わり、元に戻すことはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-interpret-discovery-data"} -->
## テナント検出データを解釈する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-interpret-discovery-data
- Service: entra-id-governance
- Article date: 2026-07-14
- Summary: Microsoft Entra Tenant Governance でテナント検出データ、シグナル、メトリックを解釈して、関連するテナントを評価する方法について説明します

この記事は、管理者とセキュリティ チームが、Microsoft Entra テナント ガバナンスを通じて検出された関連テナントを分析するのに役立ちます。 関連するテナントシグナルとメトリックを解釈して、次のタイミングを決定する方法について説明します。

- **ガバナンスを求める**: ガバナンス関係を確立することで、テナントを管理下に持ち込みます。
- **テナントを検疫する**: 承認されていないテナントまたは危険なテナントとの対話を制限します。
- **確認と無視**: テナントを既知の受け入れ可能なパートナーとして文書化します。

関連するテナントは、テナントの検出と評価の開始点です。

### 背景: 関連するテナント機能が表すもの

関連するテナントは、所有権や強制ではなく、状況認識を提供します。 Microsoft Entra が ID、アプリケーション、または課金システム間の相互作用の証拠を観察したため、関連するテナントが表示されます。 これらの対話には、次のものが含まれます。

- 外部パートナーまたは顧客テナント
- 内部で作成されたテナントまたはシャドウ IT テナント
- 開発、テスト、または実験のテナント

"関連" であることは、単独で信頼、リスク、または管理管理を意味するものではありません。 代わりに、次のような答えが返されます。

*"他のどの Microsoft Entra テナントが私のテナントと対話しているのか、そしてどのようなアクティビティがそれらの接続を確立しているのか?"*

### 手順 1: 関連するテナント インベントリを確認する

テナントの検出を有効にした後、管理センターまたは API を使用して、検出されたすべての関連テナントの一覧を表示します。 関連する各テナントには、相互作用の性質と量を説明する集計メトリックが含まれています。

最初に探すべき重要な点:

- **関連するテナントの数**
- **検出の原因となったシグナル**
- **テナントがアクティブであるか、履歴のものであるか**
- **テナントが Microsoft によって管理されているかどうか**（Microsoft 所有のインフラストラクチャ テナント）

この手順では、判断ではなくスコープを確立します。

### 手順 2: リレーションシップの背後にあるシグナルを理解する

関連するテナントは、複数の監視可能なシグナルから推論されます。 検出シグナルのカテゴリは次のとおりです。

- **B2B コラボレーション アクティビティ**
    - B2B 登録
    - B2B ログイン
    - B2B 管理者ログイン
- **マルチテナント アプリケーション**
- **請求または商取引関係**

複数のシグナルを介して関連するテナントは、単一のシグナルしか持たないテナントよりも、あなたのテナントとより密接に結びついている可能性があります。 ただし、共有課金アカウントなど、組織のつながりに関しては、特定のシグナルの方が重要な場合があります。 組織にとって最も重要なシグナルを特定したら、関連するテナントの一覧をフィルター処理して、まずそれらのシグナルに焦点を当てます。

### 手順 3: メトリックの傾向を分析する (初期と最近)

各シグナル カテゴリには、初期メトリックと最近のメトリックが含まれます。 この区別は、ガバナンスの決定に不可欠です。 メトリックには次のものが含まれます。

- 受信ユーザーと送信ユーザーの数
- 関連するアプリケーションの数
- 毎月のアクティビティ レベル
- 最終更新日時のタイムスタンプ

使用量の増加から減少に応じてメトリックを並べ替えることができます。 次の比較を使用して回答します。

- 関係は **進行中か休止中**か。
- 活動は **成長しているか、安定しているか、減少していますか**?
- 相互作用は **片側か相互**か。

最近、アクティビティが増加しているテナントは、通常、過去に初期シグナルしかないテナントよりも注意深い調査が必要です。 たとえば、アクティブな B2B ゲスト ユーザー、アクティブな管理サインイン、および共有課金アカウントを持つテナントは、より強力な運用関係を表します。 これを、検出時にキャプチャされた B2B ゲスト ユーザープレゼンスの履歴によってのみ表示されるテナントと比較します。

### 手順 4: シグナルの詳細を確認して、基盤となるエンティティを表示する

検出メトリックは桁ごとに集計されます。 たとえば、100 の戻り値は、100 から 999 までの実際の値を表します。 大まかな件数から具体的な根拠を確認するには、シグナルを掘り下げて、そのシグナルの要因となっているユーザーやアプリケーションを確認します。

管理センターで、関連するテナントのメトリックを選択して詳細パネルを開きます。 パネルには、テナントからライブで取得された基になるエンティティが一覧表示されます。

| 信号 | ドリルダウンに表示される内容 |
| --- | --- |
| B2B 登録 | 関連テナントのゲスト ユーザー |
| B2B ログイン | テナント間サインイン アクティビティを持つユーザーまたはアプリケーション |
| 管理者アプリのサインイン | 管理アプリケーションにサインインしたユーザーまたはアプリケーション |
| マルチテナント アプリケーション | 関連テナントが所有するアプリケーション (サービス プリンシパル) |

ドリルダウン結果を解釈するときは、次の考慮事項に注意してください。

- **ライブ データと集計データ。** 詳細はライブで取得され、カードの集計メトリックとは異なる場合があります。 集計されたメトリックは、アクティビティが新しい桁に渡ったときにのみ更新されます。
- **サインインシグナルはプロキシです。** B2B と管理者アプリのサインインの詳細はサインイン ログから取得され、テナントのログ保有期間によって制限されるため、詳細は表示されるメトリックとは異なる場合があります。
- **プライバシー。** 集計メトリックとは異なり、ドリルインすると、ユーザーレベルおよびアプリケーションレベルの識別子が表示されます。 この情報は、承認された管理者のみが使用できます。

テナントを分類する前に、ドリルインを使用して判断を確認します。 たとえば、管理者アプリのサインインを詳しく調べて、どの管理アプリケーションが誰によって使用されたかを正確に確認します。

同じ基になるデータをプログラムで取得するには、「[Microsoft Graphを使用して関連するテナントシグナルを調査する](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-investigate-related-tenants-api)」を参照してください。

### 手順 5: 関連するテナントを分類する

シグナルとメトリックに基づいて、関連する各テナントを 3 つの実用的なカテゴリのいずれかに分類します。

#### 既知で許容可能: 確認と無視

**特性**:

- 明確に識別可能なパートナー、ベンダー、または顧客 (名前付け、ドメイン、ユーザー)
- **Microsoftマネージド** (Microsoft所有インフラストラクチャ テナント) としてフラグが設定されている
- 予想されるB2Bまたはアプリケーションのアクティビティ
- アクティビティ レベルがビジネス コンテキストに合わせて調整される

**推奨される操作:**

- テナントを既知のリレーションシップとして文書化します。
- 即時のガバナンスまたは検疫アクションは必要ありません。

この分類は、ノイズを減らし、他の場所で注目を集めるのに役立ちます。

#### ガバナンス レビューが必要

**特性**:

- 内部志向のテナント (名前付け、ドメイン、ユーザー)
- 中程度または増加中のアクティビティ
- 不明確な所有権または目的

**推奨される操作:**

- 所有権 (部署、チーム、または開発者) を調査します。
- IT がテナントを管理する必要があるかどうかを評価します。
- [ガバナンス関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-governance-relationship)を確立することを検討してください。

このアプローチは、承認されていないが正当なテナントをブロックするのではなく、ガバナンス下に置くという目標に沿っています。

#### 危険または承認されていない可能性: 検疫候補

**特性**:

- 不明または信頼されていないテナント
- 予期しないサインインまたはアプリケーション アクティビティ
- 明確な業務上の正当な理由がない
- テナントへのアクセス パスを示唆するシグナル

**推奨される操作:**

- リスク評価の入力として、関連するテナント データを使用します。
- リスクを確認する場合は、テナントの検疫を検討してください。 詳細については、「[未承認テナントの検疫](https://learn.microsoft.com/ja-jp/entra/fundamentals/quarantine-unsanctioned-tenants)」を参照してください。

隔離された分析ではなく、関連するテナントシグナルに基づいて検疫の決定を行います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-investigate-related-tenants-api"} -->
## Microsoft Graphを使用して関連するテナントシグナルを調査する (プレビュー) - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-investigate-related-tenants-api
- Service: entra-id-governance
- Article date: 2026-07-14
- Summary: Microsoft Graphを使用して、テナント ガバナンス関連のテナント検出シグナルの背後にある基になるユーザーとアプリケーションを取得する方法について説明します。

Important

Microsoft Entra テナント ガバナンスは現在プレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

関連テナントに関する検出シグナルは、集計されたオーダー・オブ・マグニチュードの指標として要約されます。 シグナルを詳細に調査するために、それに寄与する基になるユーザーとアプリケーションを取得できます。 管理センターでは、 [ドリルダウン エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-interpret-discovery-data#step-4-drill-into-a-signal-to-see-the-underlying-entities)としてこれを公開しています。 同じデータは、Microsoft Graphを介してプログラムで使用できます。

この記事では、Microsoft Graphを使用して関連するテナントシグナルを調査するためのワークフローについて説明します。 完全な要求と応答のスキーマについては、関連コンテンツにリンクされている Microsoft Graph API リファレンスを参照してください。

### 調査ヒントを使用するタイミング

調査ヒントを使用すると、集約された桁違いのメトリックから、その背後にある特定のユーザーまたはアプリケーションに移動できます。 次の場合に使用します。

- セキュリティまたはガバナンス ワークフローの一部として、関連するテナント調査を自動化します。
- シグナルの背後にある基になるユーザーまたはアプリケーションをエクスポートして、レポートまたはチケット作成を行います。
- 関連するテナント アクティビティを環境内の他のシグナルと関連付ける。

[ドリルダウン エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-interpret-discovery-data#step-4-drill-into-a-signal-to-see-the-underlying-entities)と同様に、調査ヒントは集計メトリックとは異なる可能性があるライブ データを返し、サインイン ベースのシグナルはテナントのログ保有期間によって制限されます。 これらの結果の解釈の詳細については、「 [テナント検出データの解釈](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-interpret-discovery-data)」を参照してください。

### 手順 1: 関連するテナントを一覧表示する

自分のテナントに関連付けられたテナントを取得します。 関連する各テナントは、既定で展開された検出メトリック ( `b2BRegistrationMetrics`、 `b2BSignInActivityMetrics`、 `appB2BSignInActivityMetrics`、 `multiTenantApplicationMetrics`、 `billingMetrics`など) を返します。

```http
GET https://graph.microsoft.com/beta/directory/tenantGovernance/relatedTenants
```

関連するテナント ( `id` プロパティのテナント ID) と調査するシグナルを特定します。

### 手順 2: シグナルの調査ヒントを要求する

調査ヒントは既定では返されません。 それらを取得するには、関連するテナントを読み取り、調査中のメトリック リレーションシップで入れ子になった `$expand` を使用します。 たとえば、B2B サインイン シグナルの調査手順を取得するには、次のようにします。

```http
GET https://graph.microsoft.com/beta/directory/tenantGovernance/relatedTenants/{relatedTenantId}?$expand=b2BSignInActivityMetrics($expand=investigationHints)
```

`investigationHints`リレーションシップは、順序付けられた調査手順のコレクションを返します。 以下の応答は、読みやすくするために説明と短縮されています。

```json
{
  "b2BSignInActivityMetrics": {
    "investigationHints": [
      {
        "stepNumber": "1",
        "text": "Guidance that explains what this step reveals about the metric.",
        "actionUrl": {
          "displayName": "b2BSignInActivityMetrics.recent.inboundMonthlyTotalUsers§single§§$output",
          "url": "https://graph.microsoft.com/beta/..."
        }
      }
    ]
  }
}
```

### 手順 3: 調査手順を実行する

各ステップは [actionStep です](https://learn.microsoft.com/ja-jp/graph/api/resources/tenantgovernanceservices-actionstep?view=graph-rest-beta&preserve-view=true)。

- **stepNumber**: ステップを実行する順序。 後の手順は前の手順の出力に依存する可能性があるため、ステップを昇順で実行します。
- **text**: ステップが明らかにするものを説明する人間が判読できるガイダンス。
- **actionUrl**: 呼び出す後続のMicrosoft Graphまたは Azure Resource Manager (ARM) API を識別する [actionUrl](https://learn.microsoft.com/ja-jp/graph/api/resources/tenantgovernanceservices-actionurl?view=graph-rest-beta&preserve-view=true)。
    - **url**: 呼び出す URL テンプレート。 これには、関連するテナント、呼び出し元のコンテキスト、または前の手順の出力から解決する `{@id}`、 `{startDate}`、 `{endDate}`、または `{sourceDomain}` などのプレースホルダーを含めることができます。 前の手順で返されたデータのみを変換するステップの場合は、値を空にすることができます。
    - **displayName**: ステップの実行方法と、その出力を後のステップに連結する方法を説明する、 `metricPath§operation§input§output` 形式のマシンが読み取り可能なディレクティブ。

プレースホルダーを解決し、各ステップの `url` を昇順 `stepNumber` 順に呼び出し、結果を組み合わせて、メトリックの背後にある基になるユーザーまたはアプリケーションを明らかにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-monitor-governing-activity"} -->
## 管理されるテナントでの管理テナントの管理者アクティビティを監視する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-monitor-governing-activity
- Service: entra-id-governance
- Article date: 2026-03-10
- Summary: サインインログと監査ログを使用して、管理テナントの管理テナント管理者アクティビティを監視および監査する方法について説明します

管理テナントと管理テナントの間にガバナンス関係を確立した後、管理テナントの管理者は、詳細な委任された管理者特権 (GDAP) を使用して、管理テナントの資格情報を使用して、管理テナントにサインインできます。 管理されたテナント管理者は、サインイン ログと監査ログを使用してこれらのアクティビティを監視します。 この監視は、セキュリティの可視性を維持し、管理テナント管理者が承認されたスコープ内で動作することを保証するのに役立ちます。

この記事では、管理されるテナントでテナント管理者が実行するアクティビティを特定、確認、監視する方法について説明します。

### 前提条件

- 管理するテナントと管理されるテナントの間のアクティブなガバナンス関係。
- 管理対象のテナントに割り当てられた役割の一つ。

    - [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)
    - [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)
    - [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)
    - [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)
    - [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)
- [Microsoft Entra 管理センター](https://entra.microsoft.com)へのアクセス。

### ログで管理テナント管理者を特定する

GDAP を通じて管理されるテナントにサインインする管理者は、ログに表示される際に通常のユーザーとは異なる方法で示されます。 それらを識別する方法を理解することは、アクティビティを監視するための最初のステップです。

支配テナントの管理者が被管理テナントにサインインする場合:

- サインイン ログと監査ログの**ユーザー表示名**は**、"{Governing tenant name} Technician"** と表示されます。 たとえば、管理テナントの名前が "Contoso IT" の場合、表示名は "Contoso IT 技術者" と表示されます。
- **ユーザー名** は、 **管理テナントの user\_{ユーザー オブジェクト ID (ダッシュなし**) の形式で表示されます。 たとえば、「 `user_a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4` 」のように入力します。

### テナント管理者のアクティビティを管理するためのサインイン ログを確認する

サインイン ログを使用して、管理テナント管理者が管理テナントにサインインするタイミングと方法を監視します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **アイデンティティ**&gt;**モニタリングとヘルス**&gt;**サインインログ**を表示します。
3. テナント管理者のサインインを管理するためにフィルター処理するには、[フィルターの **追加]** を選択します。
4. **[ユーザー**] フィルターを選択し、検索用語として**「技術者**」と入力して、"{Governing tenant name} Technician" 表示名の形式に一致するエントリを検索します。
5. [ **適用]** を選択して、フィルター処理された結果を表示します。
6. サインイン エントリを選択して、次のような詳細を表示します。

    - サインインの**日付と時刻**。
    - 管理者がアクセスした**アプリケーション**。
    - サインインが成功したか失敗したかを示す**状態**。
    - Microsoft Entra が評価または適用した**条件付きアクセス** ポリシー。

### テナント管理者アクションを管理するための監査ログを確認する

監査ログを使用して、管理されるテナントでテナント管理者が行う特定のアクションと変更を追跡します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Identity**&gt;の**監視と健康**&gt;**監査ログ**に移動します。
3. テナント管理者を管理して実行されるアクションをフィルター処理するには、[ **フィルターの追加]** を選択します。
4. **[開始者] (アクター)** フィルターを選択します。 このフィルターでは、 `startsWith` 一致が使用されます。 管理テナントの名前 ( **Contoso IT** など) を入力して、アクターが管理テナント名で始まり、"{Governing tenant name} Technician" の表示名形式と一致するエントリを検索します。
5. [ **適用]** を選択して、フィルター処理された結果を表示します。
6. 次のような監査エントリを確認します。

    - 管理者が実行したアクションを説明する**アクティビティ** ("ユーザーの更新"、"ロールへのメンバーの追加" など)。
    - アクションが発生した**日付と時刻**。
    - 管理者が変更した**ターゲット リソース**。
    - アクションが成功したか失敗したかを示す**結果**。
7. 個々のエントリを選択すると、変更されたプロパティや、その古い値と新しい値など、完全な詳細が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-see-monitor-results"} -->
## モニターの結果を表示し、モニターを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-see-monitor-results
- Service: entra-id-governance
- Article date: 2026-07-28
- Summary: Microsoft Entra テナント ガバナンスでモニターの結果と構成の誤差を表示し、構成モニターを管理する方法について説明します

モニター エクスペリエンスを使用して、モニター定義の確認、実行結果の監視、構成の誤差、ベースラインの詳細、アクセス許可の準備、設定、監査ログを確認します。 この記事では、モニターの作成後にモニター ページを使用する方法について説明します。

### 前提条件

- テナントに少なくとも 1 つの構成モニターが存在します。
- テナント ガバナンス モニター データを表示できるロールを使用して、[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインできます。
- テナント構成管理サービスには、モニターを実行するために必要なアクセス許可があります。 サービスのアクセス許可がないためにモニターの実行が失敗した場合は、後 [で実行結果に依存する前にサービスのアクセス許可を更新](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-permissions-tenant-monitoring) してください。

### モニターを表示

テナントの構成モニターを表示するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **テナント ガバナンス**&gt;**Monitors** に移動します。
3. [ **モニター** ] タブで、モニターの定義の確認、モニターの作成、一覧の更新、またはモニターの選択を行って詳細を開きます。

### モニターの結果を表示する

モニターの実行結果を確認するには、次の手順に従います。

1. [ **結果の監視** ] タブを選択します。
2. モニター名、モニター ID、開始時刻、完了時間、実行状態、検出されたドリフトの数を確認します。

モニターの結果は、1 つのモニター実行を要約します。 このページを使用して、失敗または部分的に成功した実行を特定し、構成の誤差を検出した実行を見つけます。

### 構成の誤差を表示する

モニターが検出された構成ドリフトを確認するには、次の手順に従います。

1. [ **構成の誤差** ] タブを選択します。
2. モニター、リソース名、リソースの種類、ドリフトプロパティ、初回検出時刻を確認します。

誤差レコードは、ベースラインとは異なるリソースとプロパティを識別します。 修復に使用するワークロード管理エクスペリエンスを決定する必要がある場合は、このページを使用します。

### モニターを管理する

個々のモニターを確認して管理するには、次の手順に従います。

1. モニターの一覧からモニターを選択します。
2. [ **概要**] で、[ **詳細**]、[ **監視]**、[ **監査** ] カードを確認します。 **詳細** には、表示名、説明、作成日、およびリソースが監視されているサービスが表示されます。 **[監視]** カードには、最後のモニターの実行時間、リソースの種類、数、構成の誤差が表示されます。 **[監査**] カードには、過去 30 日間のモニター作成イベントや更新イベントなどの監査イベントと、最後の監査イベントの日付が表示されます。
3. **[モニターの結果**] で、選択したモニターの実行履歴を確認します。
4. **[構成の誤差]** で、選択したモニターの誤差レコードを確認します。
5. **[ベースライン**] で、監視ベースライン JSON を表示、編集、またはダウンロードします。
6. [ **アクセス許可]** で、サービス承認の準備状況を確認します。
7. **[設定]** で、モニターの表示名と説明を表示および管理します。
8. **監査ログ**で、モニターの作成イベントと更新イベントを確認します。

### 構成ドリフトを修正する

テナント ガバナンスはドリフトを報告しますが、修復は、ドリフトしたリソースを所有する管理エクスペリエンスで発生します。 たとえば、Microsoft Entra 管理センターまたは Microsoft Graph PowerShell を使用して条件付きアクセス ポリシーを更新したり、Exchange 管理センターまたは Exchange Online PowerShell を使用して Exchange のトランスポート ルールを更新したりします。

ドリフトを修復した後、次のモニター実行でテナントが再評価され、実際のリソースの状態がベースラインと一致することを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-set-up-governance-relationship"} -->
## ガバナンス関係を設定する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-governance-relationship
- Service: entra-id-governance
- Article date: 2026-03-10
- Summary: Microsoft Entra のハンドシェイク プロセスを使用して、管理テナントと管理テナントの間にガバナンス関係を設定する方法について説明します

ガバナンス関係により、テナント間の一元管理とマルチテナント アプリケーション管理が可能になります。 ガバナンス関係は、2 つのテナント間の方向関係です。1 つのテナントは *管理テナント*として機能し、もう 1 つは *管理テナント*として機能します。

3 段階のハンドシェイク プロセスを使用して、2 つの Microsoft Entra テナント間にガバナンス関係を確立します。テナントが特定の条件を満たしている場合は 2 段階認証ハンドシェイクを確立します。 この記事では、両方のオプションについて説明します。

### 前提条件

- **テナント ガバナンス管理者**ロールが必要です。
- [Microsoft Entra ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-tenant-governance)でガバナンス要求を送信するためのライセンス要件を確認します。
- ハンドシェイク プロセスを開始する前に、ガバナンス テナントでガバナンス ポリシー テンプレートを作成する必要があります。
- 三段階ハンドシェイクを使用している場合は、ガバナンス テナントでガバナンスに対する招待を有効にする必要があります。

### ガバナンス ポリシー テンプレートを作成する

ガバナンス関係を設定する前に、ガバナンス テナントにガバナンス ポリシー テンプレートを作成する必要があります。 ポリシー テンプレートは、管理テナントに対するリレーションシップの種類とアクセス レベルを定義します。 異なるリレーションシップ間でテンプレートを再利用します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも管理**テナントのテナント ガバナンス管理者**としてサインインします。
2. **テナント ガバナンス**&gt;**Templates** に移動します。
3. 新しいポリシー テンプレートを作成し、必要に応じてこれらのオプションを構成します。

    - **代理管理**: 1 つ以上の Microsoft Entra 組み込みロールを選択し、それらを管理テナントのロール割り当て可能なセキュリティ グループに割り当てます。 このグループのメンバーは、管理テナントの資格情報を使用して、管理テナントのアカウントを必要とせずに、管理テナントにサインインできます。 各グループには複数のロールの割り当てを割り当てることができ、各ポリシー テンプレートには複数のグループを定義できます。
    - **マルチテナント アプリケーション管理**: カスタムのマルチテナント アプリケーションを選択します。 リレーションシップを確立すると、管理されているテナントは同じアクセス許可を持つサービス プリンシパルを作成します。

### 3 段階認証ハンドシェイクを使用してガバナンス関係を設定する

2 つのテナント間に既存の課金シグナルまたはアクティブな関係がない場合は、3 段階認証ハンドシェイクを使用します。

#### ガバナンステナントでガバナンス招待を有効化する

ハンドシェイクを開始する前に、他のテナントからの招待を受け取れるようにするため、ガバナンステナントで招待機能を有効にしてください。 既定では、この設定はオフです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも管理**テナントのテナント ガバナンス管理者**としてサインインします。
2. **テナント ガバナンス**&gt;**Settings** に移動します。
3. 招待設定を有効にして、ガバナンスの招待を許可します。 招待を受け取った後、この設定を無効にします。

#### 手順 1: 管理テナントからガバナンスへの招待を送信する

1. 今後管理されるテナントで、少なくとも[テナント ガバナンス管理者](https://entra.microsoft.com)として **Microsoft Entra 管理センター**にサインインします。
2. **テナント ガバナンス**&gt;**テナントの管理**&gt;**招待の送信**に関するページを参照します。
3. 今後の管理者テナントにガバナンス招待を送信します。 今後の管理テナントは、招待に関する電子メール通知を受け取ります。

注

ガバナンスへの招待は 30 日間有効です。

#### 手順 2: ガバナンス テナントからガバナンス要求を送信する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも管理**テナントのテナント ガバナンス管理者**としてサインインします。
2. **テナント ガバナンス**&gt;**管理されたテナント**&gt;**受け取った招待**を参照してください。
3. 受信したガバナンスの招待を確認します。
4. 適切なガバナンス ポリシー テンプレートを選択して、ガバナンス要求をガバナンス テナントに送信します。 将来管理されるテナントは、要求が保留中であることを示す電子メール通知を受け取ります。

注

ガバナンス要求は 14 日間有効です。

#### 手順 3: ガバナンス対象のテナントでガバナンスリクエストを受諾する

1. 管理されるテナントの少なくとも[テナント ガバナンス管理者](https://entra.microsoft.com)として **Microsoft Entra 管理センター**にサインインします。
2. **テナントのガバナンス**&gt;**テナントの管理**&gt;**受信要求** を参照します。
3. 要求 ID を選択して、ガバナンス要求を確認します。
4. ガバナンス関係を作成するためのガバナンス要求を受け入れます。 管理テナントは、要求を受け入れてリレーションシップを作成したことを示す電子メール通知を受け取ります。

### 2 段階ハンドシェイクを使用してガバナンス関係を確立する

次のいずれかの条件が満たされた場合は、2 段階認証ハンドシェイクを使用します。

- 課金シグナルは、ターゲット テナントを関連テナントとして識別します。
- 2 つのテナント間に既存のアクティブなガバナンス関係があり、それらの間に別の関係を確立しようとしている。

#### 手順 1: ガバナンス テナントからガバナンスリクエストを送信する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも管理**テナントのテナント ガバナンス管理者**としてサインインします。
2. **テナント ガバナンス**&gt;**ガバナンス対象テナント**&gt;**ガバナンス要求の送信**に移動します。
3. 適切なガバナンス ポリシー テンプレートを選択して、ガバナンス要求をガバナンス テナントに送信します。

注

ガバナンス要求は 14 日間有効です。

#### 手順 2: 管理されるテナントでガバナンス要求を受け入れる

1. 管理されるテナントの少なくとも[テナント ガバナンス管理者](https://entra.microsoft.com)として **Microsoft Entra 管理センター**にサインインします。
2. **テナントのガバナンス**&gt;**テナントの管理**&gt;**受信要求** を参照します。
3. ガバナンス要求を確認します。
4. ガバナンス関係を作成するためのガバナンス要求を受け入れます。

### ガバナンス関係を確認する

ガバナンス関係を正常に作成すると、テナント ガバナンスによって次のリソースがプロビジョニングされます。

- 統治テナントと被統治テナントの両方におけるガバナンス関係オブジェクト。
- 管理されるテナントで次の手順を実行します。

    - 委任された管理を構成した場合、テナント ガバナンスは、テナント間アクセスのパートナー固有の構成を更新し、テナント間ロールの割り当てを作成します。
    - マルチテナント アプリケーション管理を構成した場合、テナント ガバナンスによって、対応するサービス プリンシパルとそのアクセス許可が作成されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-set-up-permissions-tenant-monitoring"} -->
## 構成管理サービスのアクセス許可を構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-set-up-permissions-tenant-monitoring
- Service: entra-id-governance
- Article date: 2026-07-28
- Summary: テナント構成管理サービスがスナップショットの作成とモニターの実行に使用するアプリケーションのアクセス許可とロールを割り当てる方法または削除する方法について説明します

**[構成管理のアクセス許可]** ページを使用して、テナント構成管理サービスのアクセス許可を割り当てるか削除します。 このサービスでは、これらのアクセス許可を使用してスナップショットを作成し、モニターを実行します。

対応するワークロード リソースを含むスナップショットまたはモニターを作成する前に、サービスのアクセス許可を構成します。 サービスのアクセス許可がない場合、スナップショットが不完全になったり、モニターの実行が失敗したりする可能性があります。

モニターまたはスナップショットを作成すると、[ **アクセス許可]** ステップに、選択したリソースの種類に対する最小特権のアクセス許可がサービスに付与されているかどうかが表示されます。 この手順は読み取り専用です。 必要な最小特権のアクセス許可が見つからないことがウィザードに表示される場合は、[ **構成管理のアクセス許可]** ページを使用して追加します。

### 前提条件

- [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)や特権ロール管理者など、テナント内のサービス プリンシパルのアプリ専用アクセス許可を割り当てたり削除したりできるMicrosoft Entra [ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)。 このタスクを実行できるロールを確認するには、[Microsoft Entra組み込みロールのリファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)。

### 構成管理のアクセス許可を開く

アクセス許可ページを開くには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **テナント ガバナンス**&gt;**構成管理のアクセス許可**を参照します。

### アクセス許可の割り当て

スナップショットまたは監視するリソースを含むワークロードに基づいてアクセス許可を割り当てます。

- **Microsoft Entra IDまたは Intune リソース**: [**アプリケーションのアクセス許可**] タブで、関連するMicrosoft Graph リソースのアプリ専用アクセス許可を追加します。

    - Microsoft Entra リソースのスナップショット作成または監視に必要なアクセス許可については、「[テナント構成管理でサポートされているMicrosoft Entra リソース](https://learn.microsoft.com/ja-jp/graph/utcm-entra-resources)」を参照してください。
    - Intune リソースのスナップショット作成または監視に必要なアクセス許可については、「[テナント構成管理でサポートされているMicrosoft Intuneリソース](https://learn.microsoft.com/ja-jp/graph/utcm-intune-resources)」を参照してください。
- **Teams リソース**: **Entra ロール** タブで、**Teams Reader** Microsoft Entra ロールを割り当てます。
- **Exchange Online リソース**: **アプリケーションのアクセス許可** タブで、**Exchange.ManageAsApp** を割り当てます。 次に、Exchange Online PowerShell を使用して、Tenant Configuration Management サービス プリンシパルに Exchange ロールを割り当てます。 手順については、「Exchange Online PowerShell および Security & Compliance PowerShell でのアプリ専用認証」を参照してください。

    Exchangeリソースのスナップショット作成または監視に必要なアクセス許可については、「[テナント構成管理でサポートされているMicrosoft Exchangeリソース](https://learn.microsoft.com/ja-jp/graph/utcm-exchange-resources)」を参照してください。
- **Defender または Purview のリソース**: **アプリケーションのアクセス許可** タブで、**Exchange.ManageAsApp** を付与します。 その後、Security & Compliance PowerShell を使用して、Tenant Configuration Management サービス プリンシパルにセキュリティおよびコンプライアンス (Defender および Purview) のロールを割り当てます。 手順については、 [セキュリティへの接続とコンプライアンス PowerShell に関する説明を](https://learn.microsoft.com/ja-jp/powershell/exchange/connect-to-scc-powershell)参照してください。

    Defenderまたは Purview リソースのスナップショット作成または監視に必要なアクセス許可については、「[テナント構成管理でサポートされているMicrosoft Securityとコンプライアンスのリソース](https://learn.microsoft.com/ja-jp/graph/utcm-securityandcompliance-resources)」を参照してください。

注

**[構成管理のアクセス許可]** ページには、Exchange Online、Defender、または Purview 内のサービスに割り当てられているワークロードのアクセス許可は表示されません。 それらのアクセス許可の割り当てと削除には、Exchange Online PowerShell または Security & Compliance PowerShell を使用します。

### 権限の削除

アクセス許可またはMicrosoft Entraロールを削除するには、その名前の横にあるチェック ボックスをオンにし、コマンド バーで **[削除**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-terminate-governance-relationship"} -->
## ガバナンス関係を終了する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-terminate-governance-relationship
- Service: entra-id-governance
- Article date: 2026-03-10
- Summary: Microsoft Entra テナント ガバナンスでテナント間のガバナンス関係を終了し、削除されるリソースを理解する方法について説明します

この記事では、管理テナントと管理テナントの間のガバナンス関係を終了する方法について説明します。 ガバナンス関係を終了すると、テナント ガバナンスは、詳細な委任された管理者特権 (GDAP) ロールの割り当て、サービス プリンシパル、およびそれらのアクセス許可を含む、関係関連のすべてのリソースを管理テナントから削除します。

ガバナンス関係は、管理テナントまたは被管理テナントのどちらが終了を開始するかに応じて、2 つの方法で終了します。

| 開始者 | プロセス |
| --- | --- |
| テナントの管理 | 管理されているテナントに終了要求を送信します。 規制対象のテナントは、終了手続きを完了することを確認する必要があります。 |
| 管理されたテナント | リレーションシップを直接終了します。 管理テナントは、アクションを実行する必要はありません。 |

### 前提条件

- 2 つのテナント間にアクティブなガバナンス関係が必要です。
- **テナント ガバナンス管理者**ロールが必要です。

### リレーションシップの終了: テナントの開始の管理

管理する側のテナントは、ガバナンス関係の終了を求めることができます。 このプロセスでは、テナント ガバナンスがリレーションシップとそのリソースを削除する前に、管理されたテナントからの確認が必要です。

#### 統括テナントとして終了手続きを開始する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも管理**テナントのテナント ガバナンス管理者**としてサインインします。
2. **テナント ガバナンス**&gt;**管理されているテナント**を参照します。
3. 終了するアクティブなガバナンス関係を選択します。
4. [ **ガバナンスの終了]** を選択します。

    リレーションシップの状態が、 **要求された終了**に変わります。 テナント ガバナンスは、終了要求に関する電子メール通知を管理テナントに送信します。
5. 管理されたテナントが終了を確認するまで待ちます。

    管理されたテナントが確認されると、テナント ガバナンスは、管理されたテナントからすべてのリレーションシップ関連リソースを削除し、リレーションシップの状態が **[終了済み**] に変わります。

#### 管理対象テナントとして終了を確認する

管理テナントが終了を開始すると、管理テナントはプロセスを完了するための要求を確認する必要があります。

1. 管理されるテナントの少なくとも[テナント ガバナンス管理者](https://entra.microsoft.com)として **Microsoft Entra 管理センター**にサインインします。
2. **テナント ガバナンス**&gt;**テナントのガバナンス**を参照します。
3. **[リレーションシップの状態**] フィルターを [**要求された終了]** に変更します。
4. 終了を要求したテナントを選択します。
5. [ **終了の確認]** を選択します。

    テナント ガバナンスは、管理されたテナントからすべてのリレーションシップ関連リソースを削除し、リレーションシップの状態が **[終了]** に変わります。 テナント ガバナンスは、終了が完了したことを示す電子メール通知を管理テナントに送信します。

### リレーションシップを直接終了する: 管理されたテナント

管理テナントとして、ガバナンス テナントからの承認を必要とせずに、ガバナンス関係を直接終了します。

1. 管理されるテナントの少なくとも[テナント ガバナンス管理者](https://entra.microsoft.com)として **Microsoft Entra 管理センター**にサインインします。
2. **テナント ガバナンス**&gt;**テナントのガバナンス**を参照します。
3. ガバナンス関係を終了するテナントを選択します。
4. [ **ガバナンスの終了]** を選択します。
5. リレーションシップの詳細を確認し、終了を確認します。

    テナント ガバナンスは、管理されたテナントからすべてのリレーションシップ関連リソースを削除し、リレーションシップの状態が **[終了]** に変わります。 テナント ガバナンスは、関係が終了したことを示す電子メール通知を管理テナントに送信します。

### ガバナンス関係を終了するとどうなりますか

ガバナンス関係を終了すると、テナント ガバナンスは、管理されるテナントからこれらのリソースを更新または削除します。

- **テナント間アクセス ポリシー**: テナント ガバナンスは、管理されるテナントのパートナー固有のクロステナント アクセス構成から、パートナーとしての管理テナントを削除します。
- **GDAP ロールの割り当て**: テナント ガバナンスは、管理テナントのユーザーが、管理されるテナントにサインインして管理することを許可したテナント間ロールの割り当てを削除します。
- **サービス プリンシパル**: 管理者がマルチテナント アプリケーション管理を構成した場合、テナント ガバナンスは、対応するサービス プリンシパルとそのアクセス許可を管理されるテナントから削除します。

終了後、ガバナンス テナントのユーザーは、ガバナンス関係を通じて、管理テナントの資格情報を使用してガバナンス テナントにサインインできなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-update-delete-monitor"} -->
## モニターを更新または削除する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-update-delete-monitor
- Service: entra-id-governance
- Article date: 2026-03-05
- Summary: ベースラインまたは要件が変更されたときに Microsoft Entra テナント ガバナンスで構成モニターを更新または削除する方法について説明します

この記事では、 [Microsoft Entra 管理センター](https://entra.microsoft.com)で構成モニターを更新または削除する方法について説明します。 構成基準が変更されたときにモニターを更新できます。 監視対象のリソースが組織のセキュリティまたはコンプライアンスの要件にとって重要ではなくなった場合は、モニターを削除します。

### 前提条件

- [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) としてサインインします。
- テナントには、Microsoft Entra テナント ガバナンスのライセンスが必要です。
- テナントに少なくとも 1 つの構成モニターが存在する必要があります。

### モニターを更新する

既存の構成モニターを更新するには:

1. **テナント ガバナンス**&gt;**構成管理**&gt;**Monitors** に移動します。
2. 更新するモニターを見つけて、その名前の横にある編集 (鉛筆) アイコンを選択します。

更新ウィザードでは、モニターの作成と同じ手順を使用します。 **アクセス許可** → **構成基準** → **確認**します。

既存の構成モニターを更新すると、更新された設定によって既存のモニター定義が置き換えられます。

Important

既存のモニターを更新すると、テナント ガバナンスは、以前に生成されたすべてのモニターの結果と構成の誤差を自動的に削除します。 更新されたモニターは、実行されるたびに結果を記録し、再びドリフトします。

### モニターを削除する

モニターの削除を元に戻すことはできません。 モニターを削除すると、テナント ガバナンスによって、関連するすべてのモニター結果と構成の誤差も直ちに削除されます。

構成モニターを削除するには:

1. **テナント ガバナンス**&gt;**構成管理**&gt;**Monitors** に移動します。
2. 削除するモニターを見つけます。
3. モニターの名前の横にあるチェック ボックスをオンにし、コマンド バーで [ **削除** ] を選択します。 または、モニター名にカーソルを合わせ、表示される削除アイコンを選択します。
4. 確認ダイアログで、[削除] を選択 **します**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/how-to-update-governance-relationship"} -->
## ガバナンス関係を更新する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-update-governance-relationship
- Service: entra-id-governance
- Article date: 2026-03-10
- Summary: Microsoft Entra テナント ガバナンスのガバナンスと管理されるテナントの間の既存のガバナンス関係を更新する方法について説明します

この記事では、管理テナントと管理テナントの間の既存のガバナンス関係を更新する方法について説明します。 委任された管理ロールまたはマルチテナント アプリケーション構成を追加または変更するには、ガバナンス関係の更新が必要になる場合があります。

### 前提条件

- 管理テナントと被管理テナントの間には、アクティブなガバナンス関係が必要です。
- 既存のリレーションシップの作成に使用したガバナンス ポリシー テンプレートにアクセスできる必要があります。 ポリシー テンプレートを削除した場合は、新しいリレーションシップを作成する必要があります。
- **テナント ガバナンス管理者**ロールが必要です。
- [Microsoft Entra ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-tenant-governance)でガバナンス要求を送信するためのライセンス要件を確認します。

### ガバナンス ポリシー テンプレートを更新する

ガバナンス関係を更新するには、まず、既存のリレーションシップを確立するために使用したガバナンス ポリシー テンプレートを変更する必要があります。 テンプレートを更新すると、そのバージョン番号は自動的に 1 ずつインクリメントされます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも管理**テナントのテナント ガバナンス管理者**としてサインインします。
2. **テナント ガバナンス**&gt;**テンプレート**を参照し、リレーションシップの設定に使用したポリシー テンプレートを選択します。
3. 必要に応じてテンプレートを変更します。 次の構成の 1 つ以上を更新します。

    - **委任された管理ロール**: 管理テナントのセキュリティ グループに割り当てられた Microsoft Entra 組み込みロールを追加または変更します。 これらのロールは、これらのグループ内のユーザーが管理されるテナントにサインインするときに持つアクセス レベルを決定します。
    - **マルチテナント アプリケーション管理**: カスタムマルチテナント アプリケーションを追加または更新します。 リレーションシップを更新すると、テナント ガバナンスは、管理されるテナント内の対応するアクセス許可を使用してサービス プリンシパルを作成または更新します。
4. 更新されたガバナンス ポリシー テンプレートを保存します。 テンプレートのバージョン番号が 1 ずつインクリメントされます。

注

ガバナンス ポリシー テンプレートを更新しても、ガバナンス関係は自動的に更新されません。 テナント管理者は、ポリシー テンプレートの変更を有効にするために、以下のセクションで説明されているガバナンス要求と承認プロセスを完了する必要があります。

### 更新されたテンプレートを使用して新しいガバナンス要求を送信する

ガバナンス ポリシー テンプレートを更新した後、更新されたテンプレートを使用して、ガバナンス テナントから管理テナントに新しいガバナンス要求を送信します。

1. 管理テナントで、新しいガバナンス要求を作成します。
2. 更新する既存のリレーションシップを持つ管理テナントを選択します。
3. 更新されたガバナンス ポリシー テンプレートを選択します。
4. ガバナンス要求を送信します。 管理されたテナントは、新しいガバナンス要求に関する電子メール通知を受け取ります。

### ガバナンス要求を受け入れる

管理テナントの管理者は、更新を完了するためにガバナンス要求を受け入れる必要があります。

1. 管理されるテナントの少なくとも[テナント ガバナンス管理者](https://entra.microsoft.com)として **Microsoft Entra 管理センター**にサインインします。
2. **テナント ガバナンス**&gt;**受信した要求**を参照します。
3. ポリシー テンプレートの変更を含め、更新されたガバナンス要求を確認します。
4. ガバナンス要求を受け入れます。 システムは、既存のガバナンス関係を新しいポリシー テンプレート構成に更新します。 管理テナントは、受け入れられた要求と更新されたガバナンス関係を確認する電子メールを受信します。

管理されたテナントがガバナンス要求を受け入れると、次の変更が有効になります。

- テナント ガバナンスは、ポリシー テンプレートの最新バージョンを反映するように、既存のガバナンス関係のポリシー スナップショットを更新します。
- 委任された管理ロールを更新した場合、テナント ガバナンスは、それに応じて、管理されるテナントの GDAP ロールの割り当てを更新します。
- マルチテナント アプリケーション管理を更新した場合、テナント ガバナンスは、対応するサービス プリンシパルと、管理されるテナント内のアクセス許可を更新します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/licensing"} -->
## Microsoft Entra テナント ガバナンスのライセンス - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/licensing
- Service: entra-id-governance
- Article date: 2026-09-14
- Summary: P1、P2、ID ガバナンスなど、各ライセンス 層で使用できる Microsoft Entra テナント ガバナンス機能について説明します

Microsoft Entra テナント ガバナンス のライセンスは、テナント ガバナンス機能を使用する管理者に適用されます。 この記事は、IT の意思決定者、IT 管理者、パートナーを対象としています。 これを使用して、組織全体または顧客環境全体のテナントを検出、作成、管理、または管理するためのライセンスを評価します。

テナント内のすべてのユーザーにライセンスが必要なわけではありません。 ガバナンス関係の場合、委任された管理アクティビティを実行する管理者には、管理テナントのライセンスが必要です。

### ライセンス別の機能

次の表は、各ライセンスで使用できるテナント ガバナンス機能を示しています。

注

Microsoft Entra P1 は、Microsoft 365 E3 と Microsoft 365 Business Premium にも含まれています。 Microsoft Entra P2 は、Microsoft 365 E5 にも含まれています。 Microsoft Entra ID ガバナンスは、Microsoft Entra スイート と Microsoft 365 E7 にも含まれています。

#### 構成管理

| 特徴 | Free | Microsoft Entra P1 | Microsoft Entra P2 | Microsoft Entra ID ガバナンス |
| --- | --- | --- | --- | --- |
| シングルテナント構成監視とドリフトレポート |  | ✅ テナントあたり 1 日あたり最大 30 台のモニターと 800 個の構成リソース | ✅ テナントあたり 1 日あたり最大 30 台のモニターと 800 個の構成リソース | ✅ 基本容量に加えて、ライセンスごとに 1 日あたり 10 個の追加の構成リソース |
| シングルテナント用設定スナップショット |  | ✅ テナントあたり 1 か月あたり最大 20,000 個のリソースと 12 個のアクティブなスナップショット ジョブ | ✅ テナントあたり 1 か月あたり最大 20,000 個のリソースと 12 個のアクティブなスナップショット ジョブ | ✅ 基本容量に加えて、ライセンスごとに 1 か月あたり 35 個の追加の構成リソース |

#### 関連テナント

| 特徴 | Free | Microsoft Entra P1 | Microsoft Entra P2 | Microsoft Entra ID ガバナンス |
| --- | --- | --- | --- | --- |
| B2B コラボレーション、マルチテナント アプリ、共有課金アカウントを通じて関連テナントを検出する |  |  |  | ✅ |

#### ガバナンス関係

| 特徴 | Free | Microsoft Entra P1 | Microsoft Entra P2 | Microsoft Entra ID ガバナンス |
| --- | --- | --- | --- | --- |
| テナント間の詳細な委任された管理者特権 (GDAP) とのガバナンス関係 |  | ✅ | ✅ | ✅ |
| カスタム マルチテナント アプリインジェクションとのガバナンス関係 |  |  |  | ✅ |

#### セキュリティで保護されたテナントの作成

| 特徴 | Free | Microsoft Entra P1 | Microsoft Entra P2 | Microsoft Entra ID ガバナンス |
| --- | --- | --- | --- | --- |
| ガバナンス関係を通じて新しいテナントを作成するための方法 | ✅ | ✅ | ✅ | ✅ |

### 機能別のライセンス シナリオ

次のシナリオでは、一般的なテナント ガバナンスの使用に関するライセンスニーズを計算する方法を示します。

#### 構成管理

テナント ガバナンス Basic では、構成の監視とスナップショットの容量が提供されます。 テナント ガバナンス Premium ライセンスでは、より多くの構成リソースをカバーする必要がある組織の容量が追加されます。

##### E3 ライセンスを使用して毎日の構成ドリフトを監視する

**状況：**Contoso には、Microsoft Entra P1 を含むMicrosoft 365 E3があります。 ID チームは、ライセンスを追加購入せずにテナント構成管理を使用したいと考えています。 その監視スコープは、テナントあたり 1 日あたり 800 構成リソースの基本制限内に収まります。

**ライセンスの計算:** Contoso は、既存の E3 ライセンスに含まれる Basic 容量を使用して、毎日最大 800 個の構成リソースを監視できます。 このスコープに追加のライセンスは必要ありません。

**Contoso が容量を使用する方法:** チームは、認証方法、条件付きアクセス ポリシー、ロール設定、およびその他の影響の大きいテナント構成に優先順位を付けます。 監視対象の設定が変更されると、チームはドリフト レポートを確認して、変更の内容と変更が予想されたかどうかを把握します。 承認されていない変更後に承認された構成を復元するか、意図的な変更の後にベースラインを更新します。

**結果：** Contoso は、追加のライセンス コストなしで、最も重要なテナント リソースの日次ドリフトの可視性とより強力な制御を取得します。

##### 基本日次制限を超えて監視を拡張する

**状況：** Fabrikam の環境は、Basic 容量内から、毎日の監視を必要とする 1,000 個の構成リソースに拡張されます。 Basic の 1 日あたりの上限は 800 個のリソースに対応し、監視スコープ外のリソースは 200 個となります。

**ライセンスの決定:** Fabrikam では、200 個のリソースを監視解除したり、Premium ライセンスを追加したりできます。 セキュリティとコンプライアンスの利害関係者は、テナントのセキュリティ体制、監査の準備、運用の一貫性に影響を与えるため、1,000 個のリソースすべてが毎日監視する必要があると判断します。

**ライセンスの計算:** 各 Premium ライセンスでは、1 日あたり 10 個のリソースの容量が追加されます。 200 リソースの不足には、20 個の Premium ライセンスが必要です。

**結果:** 追加ライセンスにより、Fabrikam は毎日のドリフト検出とレポートに 1,000 個のリソースをすべて含めることができます。 重要な構成は除外されません。チームはテナント全体で一貫したドリフトの可視性を維持します。

##### 大規模な月次スナップショットに Premium 容量を使用する

**状況：** Northwind には既に Premium ライセンスがあり、毎日の監視よりも幅広い可視性が必要です。 その ID チームは、レポート、監査証拠、運用レビューのために、基本の月次制限である 20,000 を超える構成リソースをキャプチャしたいと考えています。

**ライセンスの計算:** Northwind には 100 個の Premium ライセンスがあります。 ライセンスごとに 1 か月あたり 35 個のリソースが追加され、これらのライセンスによって 3,500 リソースの容量が追加されます。 Northwind では、毎月最大 23,500 個の構成リソースをキャプチャできます。

**Northwind が容量を使用する方法:** チームには、セキュリティ ポリシー、ID 設定、ロール構成、およびその他のテナント リソースがスナップショット スコープに含まれています。 定期的なスナップショット ジョブをスケジュールして、構成データの履歴を保持し、時間の経過に伴う構成の変化を比較し、予期しない変更や危険な変更を特定します。

**結果:** Northwind は、大規模な月次構成抽出をサポートし、監査、ガバナンス、運用分析に履歴データを使用します。

#### ガバナンス関係

ガバナンス関係ライセンスは、管理テナントでのみ必要です。 管理されるテナントにはライセンスは必要ありません。 たとえば、サービス プロバイダーが顧客とのガバナンス関係を確立する場合、リレーションシップを構成するサービス プロバイダー テナントの管理者のみがライセンスを必要とします。

クロステナントの委任管理には、Microsoft Entra P1、Microsoft Entra P2、または Microsoft Entra ID ガバナンス が必要です。 カスタム マルチテナント アプリケーション プロビジョニングには、Microsoft Entra ID ガバナンスが必要です。

ガバナンス関係を構成する管理者ごとに 1 つのライセンスが必要です。 リレーションシップの数は、ライセンス数には影響しません。

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| 1 人のサービス プロバイダー管理者が、5 つの顧客テナントとのガバナンス関係を構成します。 | リレーションシップを構成する管理者用の 1 つのライセンス。 リレーションシップと顧客テナントの数では、ライセンス要件は追加されません。 | 1 |
| 3 人のサービス プロバイダー管理者がそれぞれ、顧客とのテナント間の委任された管理関係を構成します。 | 構成管理者ごとに 1 つのライセンス。 | 3 |
| 1 人の管理者が、組織の管理テナントからマルチテナント アプリケーション プロビジョニングを構成します。 | 構成管理者用の 1 つのMicrosoft Entra ID ガバナンス ライセンス。 管理されるテナントにはライセンスは必要ありません。 | 1 |

#### 関連テナント

1 人の管理者が、そのテナントで関連テナントの検出を有効化します。 検出が有効になった後、関連テナントを使用するすべての管理者は、結果を表示するか、更新をトリガーするか、シグナルに基づいて操作するかに関係なく、ライセンスが必要になります。 検出を有効にする管理者は、この機能を使用するため、ライセンスも必要です。

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| Woodgrove Bank には 12 人の管理者がいます。 1 人の管理者が関連テナントの検出を有効にし、結果を使用する唯一の管理者です。 | 関連テナントを有効にして使用する管理者向けの 1 つのライセンス。 | 1 |
| Contoso には 30 人の管理者がいます。 5 名の管理者が関連テナントを使用するのは、そのうちの 1 名が検出を有効化してからです。 3つは更新をトリガーしてシグナルに基づいて動作し、2つは結果を表示するだけです。 | 関連テナントを使用する 5 人の管理者ごとに 1 つのライセンス。 | 5 |
| Fabrikam には 50 人の管理者がいます。 10 人の管理者が関連テナントを使用します。これには、検出を有効にする管理者、シグナルを確認する 6 人、結果のみを表示する 3 人が含まれます。 | 関連テナントを使用する 10 人の管理者ごとに 1 つのライセンス。 この機能を使用しない管理者は、その機能のライセンスを必要としません。 | 10 |

#### セキュリティで保護されたテナントの作成

有料のMicrosoftのお客様は、Microsoft Entra無料でセキュリティで保護されたアドオン テナントの作成を利用できます。 Premium Microsoft Entra ライセンスは必要ありませんが、顧客の既存のテナントは有料のMicrosoft クラウド サブスクリプションに関連付ける必要があります。 無料のテナントまたは試用版サブスクリプションのみを使用しているお客様は、Microsoft Entra 管理センターから追加のテナントを作成することはできません。

一般的なテナント作成要件の詳細については、「[Microsoft Entra ID での新しいテナントの作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/overview"} -->
## Microsoft Entra テナント ガバナンスとは - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/overview
- Service: entra-id-governance
- Article date: 2026-03-05
- Summary: Microsoft Entra テナント ガバナンスと、組織が環境全体でテナントを検出、管理、管理するのにどのように役立つかについて説明します

ほとんどの大規模な組織は、合併と買収、セキュリティまたはプライバシーに依存するワークロードのパーティション分割の要件、テスト環境、その他の理由により、複数のテナントで Microsoft サービスを運用しています。 また、ほとんどの組織には、中央 IT が管理せず、多くの場合は知らない、ユーザーが作成した "シャドウ IT" テナントもあります。 多くの環境では、これらの各テナントが適切に構成されていることを確認するのは簡単ではありません。特に、一部のテナントが存在することさえわからない場合です。 これにより、組織のセキュリティとコンプライアンスの目標に対するリスクが生じます。

Microsoft Entra テナント ガバナンスを使用すると、すべてのテナントを可視化し、セキュリティとコンプライアンスの要件を満たすように構成することができます。 これには、現在管理しているテナント、管理していないが組織のリスクを生み出す "シャドウ IT" テナント、ユーザーが作成する新しいテナントが含まれます。

### 関連テナント

関連するテナント機能は、管理する必要があるテナント、または管理するテナントに対するセキュリティまたはプライバシーの露出を持つテナントを特定するのに役立ちます。

主な機能:

- 1 つ以上の検出シグナルに基づいて、テナントに関連するテナントを自動的に検出します。
- B2B 検出シグナルは、テナントと他のテナント間の受信および送信 B2B アクセス、B2B 登録、および B2B 管理アクセスを識別して測定します。
- マルチテナント アプリケーション検出シグナルは、テナントにアクセス許可を持つ登録済みのマルチテナント アプリケーションを持つテナント、または登録済みのマルチテナント アプリケーションがアクセスできるテナントを識別します。
- \*\* 課金識別シグナルは、課金アカウントを共有するテナントを特定します。あなたのテナントまたは関連するテナントがその課金アカウントの関連付けられた課金テナントである場合です。
- テナントと関連テナント間のリレーションシップの量に関する初期メトリックと最近のメトリックを表示します。
- 検出シグナルの結果に基づいて関連するテナントの一覧をフィルター処理して並べ替え、組織が優先する特定の種類のリスクを持つテナントに焦点を当てます。

[関連するテナント](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/related-tenants)の詳細を確認します。

### ガバナンス関係

ガバナンス関係機能は、管理する必要があるテナント間のテナント間の管理アクセスを設定および管理するのに役立ちます。

主な機能:

- ガバナンス関係を明確に定義し、既存のテナント間でクロステナントの管理アクセスを設定するための招待、要求、および承認ワークフロー。
- 課金検出シグナルとの統合により、課金アカウントを共有するテナント間のリレーションシップを設定するためのワークフローが合理化されます。
- 管理テナント内のユーザーが他の管理されるテナントで管理タスク (構成管理タスクを含む) を実行できるようにする最小権限のテナント間管理アクセス。
- 管理されたテナントでカスタム アプリケーションのアクセス許可をプロビジョニングおよび維持するための合理化されたアプリ挿入エクスペリエンス。
- 同じ管理アクセス許可が必要な異なるテナントで同じアクセス許可を簡単に要求するためのガバナンス ポリシー テンプレート。

[ガバナンス関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships)の詳細を確認します。

### 構成管理

構成管理機能は、管理するテナント内のテナント リソースの構成を監視するのに役立ちます。 Entra、Intune、Exchange Online、Teams、Purview、Defender の 6 つの Microsoft サービスで 200 種類以上のリソースがサポートされています。

主な機能:

- 標準の JSON 形式を使用して、テナント リソースの望ましい状態を表す構成基準を作成します。
- テナント リソースの現在の状態を文書化する構成スナップショットを作成します。 "既知の正常な" テナントのスナップショットを使用して、構成基準の作成をすぐに開始できます。 スナップショットは、特定の監査要件を満たすのにも役立ちます。
- テナント リソースの実際の状態と、JSON 構成ベースラインで定義されている目的の状態を自動的に比較するモニターを作成します。
- モニターを実行するたびに、要約統計を表示するモニター結果を表示します。 現在、各モニターは 6 時間間隔で動作しています。
- 1 つのモニターまたはテナントで実行されているすべてのモニターの構成ドリフトの一覧を表示します。 各構成ドリフトは、リソースのどのプロパティが構成基準で定義されているものと異なるかを示します。

[構成管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)の詳細を確認します。

### セキュリティで保護されたテナントの作成

セキュリティで保護されたテナント作成機能を使用すると、新しいアドオン テナントを作成できるユーザーを制御し、それらのテナントとのガバナンス関係を自動的に設定し、必要に応じて組織がそれらのテナントへの管理アクセスを確実に回復できるようにします。

主な機能:

- テナントとユーザーによって作成された新しいテナントの間にガバナンス関係を自動的に作成するガバナンス ポリシー テンプレートを定義します。
- テナント内のコマース課金アカウントへのアクセスを割り当てたり制限したりして、新しいアドオン テナントを作成できるユーザーを制御します。
- コマース課金アカウントで Microsoft Entra 資産を使用して、そのテナントの最後の管理者が組織を離れた場合や、脅威アクターがアドオン テナントを侵害したときなど、アドオン テナントへの管理アクセスの回復を効率化します。
- コマース API または Microsoft Entra 管理センターを使用して、新しいアドオン テナントを作成します。

[管理されたテナントの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-tenant)の詳細を確認します。

### 作業の開始

テナント ガバナンス機能の使用を開始するには、テナント ガバナンス タスクを実行する権限を持つロールを持つ管理者として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。 **[テナント ガバナンス**] ページに移動します。

### ライセンスとサポート

テナント ガバナンスは、テナント ガバナンス Basic とテナント ガバナンス Premium の 2 つのサービス レベルで利用できます。 各サービス レベルで使用できるテナント ガバナンス機能を確認するには、 [Microsoft Entra のライセンスに関するページを](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/related-tenants"} -->
## Tenant Governance 内の関連テナント - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/related-tenants
- Service: entra-id-governance
- Article date: 2026-07-14
- Summary: Microsoft Entra Tenant Governance が、組織全体の ID、アプリケーション、課金シグナルを通じて関連するテナントを検出する方法について説明します

**関連するテナント**機能は、組織が監視可能なアクティビティを通じてテナントと対話する Microsoft Entra テナントを可視化するのに役立つテナント ガバナンス機能です。 これらの対話には、外部のテナント (パートナー、ベンダー、顧客) や、従業員が作成したテストテナントや開発テナントなど、一元的な監視なしで内部的に作成されたテナントが含まれる場合があります。

最新の組織は、1 つの Microsoft Entra テナント内で動作することはめったにありません。 合併、買収、売却、地理的な拡張、開発者の実験、分散型 IT モデルがテナントのスプロールにつながっています。 その結果、Microsoft Entra テナントのエコシステムが拡大し、多くの場合、目に見えないか、十分に理解されていない方法でやり取りされます。

関連するテナント機能は、組織が所有するテナントの権限のあるインベントリではありません。 代わりに、ID、アプリケーション、および課金システム全体に既に存在する証拠に基づいてテナント接続を表示することで、状況認識を提供します。 この認識により、組織はテナント エコシステムを理解し、ガバナンス アクションが適切な場所を決定できます。

その中核となるのは、関連するテナント機能が次の質問に答えます。

*"どの Microsoft Entra テナントと私のテナントが相互作用し、どのような行動がその接続を作成していますか?"*

この可視性は、所有権や強制の決定を前提とせずに、効果的なテナント ガバナンスの基礎を形成します。

### 関連テナントとは？

関連するテナントは、監視可能な検出シグナルに基づいて別のテナントへの検証可能な接続を示す Microsoft Entra テナントです。 これらのシグナルは、実際の構成とアクティビティ データから派生し、時間の経過と同時に更新されます。

テナント ガバナンスは、関連するテナントを検出します。 手動で宣言することはありません。 検出では、意図ではなくコンテキストが確立されます。 関連するテナントは次のようになります。

- 外部パートナー、ベンダー、または顧客テナント
- マルチテナント アプリケーションを介してアクセスされるサービスとしてのソフトウェア (SaaS) プロバイダー テナント
- 承認されていないテナントまたはシャドウ IT テナント (テスト、開発、実験のために従業員によって作成されたものなど)

**テナント検出の表示形式**

- どのテナントが関連していますか
- リレーションシップを確立する発見シグナルまたは信号
- シグナルに関連するIDの相互作用、アプリケーションの使用状況、請求関連などのアクティビティおよび発見のメトリック

これらのメトリックは、テナントの接続方法を表します。 定性的スコアを割り当てたり、関連するすべてのテナントを管理する必要があることを意味したりすることはありません。 代わりに、ガバナンス アクションが保証されているかどうかを判断するために必要な証拠を提供します。

### Microsoftマネージド テナント

検出された関連テナントの中には、組織やパートナーに属するテナントではなく、Microsoft所有のインフラストラクチャ テナントがあります。 テナント ガバナンスは、これらのテナントを特定し、**Microsoft managed** としてマークします。

Microsoft によって管理されるテナントが表示されるのは、Microsoft クラウドの定常的な運用によって、観測可能なテナント間アクティビティが発生するためです。 これらは想定されており、一般的にガバナンス アクションは必要ありません。 **Microsoftマネージド** インジケーターを使用すると、このクラスの関連テナントをすばやく認識して確保できるため、注意が必要なテナントに集中できます。

Microsoft管理インジケーターは、テナント ガバナンス ライセンスを持ち、関連するテナントを読み取るアクセス許可を持つ管理者が使用できます。

### 関連するテナントが重要な理由

関連するテナントは、承認されていないテナントの表示から、情報に基づくガバナンスの決定を可能にするまで、いくつかの重要な領域で価値を提供します。

#### 外部テナントとシャドウ IT テナントの両方の可視性

多くの場合、組織では、正式なプロビジョニング プロセスの外部に存在するテナントの可視性が制限されています。 従業員は概念実証作業、テスト、または実験用のテナントを作成する場合があります。一方、ビジネス ユニットは、コラボレーションや SaaS の使用のために外部テナントと直接やり取りする場合があります。

検出しないと、次のような場合でも、これらのテナントは非表示のままになります。

- 組織と ID を交換する
- 従業員が使用するアプリケーションをホストする
- 共有の請求や商取引関係に関連付ける

関連するテナント機能を使用すると、承認されていないシャドウ IT テナントを含め、これらの相互作用が表示されます。これにより、組織は、予期される外部リレーションシップと、ガバナンスの注意を必要とする可能性がある内部で作成されたテナントを区別できます。

#### テナント間 ID、アプリケーション、課金アクティビティ

最新の ID モデルとアプリケーション モデルは、本質的に複数のテナントを跨ります。 外部コラボレーション、マルチテナント アプリケーション、および共有課金コンストラクトでは、異なる所有権モデルを持つテナントにまたがる相互作用が導入されます。

関連するテナント機能では、次の情報が表示されます。

- テナント間 ID アクティビティ
- テナント境界を越えたアプリケーションの使用
- テナント間の財務連携または請求関連付け

この可視性により、組織はどこでどのように対話が行われるかを理解でき、情報に基づくセキュリティとガバナンスの意思決定の基礎を形成できます。

#### ガバナンスは認識から始まる

テナント ガバナンスは、何を管理するかを決定する前に、何が存在するかを把握することに依存します。 関連するテナント機能は、この認識を可能にする探索レイヤーを提供します。

外部テナントと内部で作成されたシャドウ IT テナントの両方を表示することで、関連するテナント機能を使用すると、管理者は次のことができます。

- 想定されるテナントと外部管理されているテナントを特定する
- 組織のポリシーの外にある可能性があるテナントを検出する
- ガバナンス アクションを開始するかどうかを決定する

このアプローチにより、ガバナンスはリアクティブではなく、意図的かつ証拠ベースであることが保証されます。

### 関連するテナントがテナント ガバナンスにどのように適合するか

テナント ガバナンスは、組織が複数のテナントをシステムとして管理できるように設計されており、異なるテナントには異なるレベルの監視が必要であることを認識しています。

関連するテナント機能は、テナント ガバナンスの検出レイヤーを表します。

1. **検出**: 自社のテナントとやり取りしているテナントを識別する
2. **理解**: 検出シグナルとアクティビティ メトリックを確認する
3. **ガバナンス**: ガバナンス アクションが適切かどうかを判断する

このプロセスによって検出された承認されていないテナントまたはシャドウ IT テナントの場合、組織は、ガバナンス関係の確立や一元的な監視の適用など、テナント ガバナンス機能を使用してガバナンス アクションを開始できます。 外部テナントの場合、検出では、それ以上のアクションを実行せずに、単に認識が提供される場合があります。

### 関連するテナントを識別するために使用される検出シグナル

検出シグナルは、関連するテナントを識別します。 各シグナルは、2 つのテナントが意味のある方法で対話することを監視可能なインジケーターです。 各シグナルには、相互作用の性質を説明する集計されたアクティビティ メトリックが伴います。

メトリックは、正確な値ではなく桁違いに表示され、アクティビティの変化に応じて更新されるため、個々のユーザーのプライバシーを保護しながら継続的な可視性が提供されます。

#### B2B コラボレーション

企業間 (B2B) コラボレーションシグナルは、ゲスト ユーザーの登録やサインイン アクティビティなどのテナント間 ID の相互作用に基づいてテナントの関係を識別します。

これらの信号は一般的に表面化します。

- パートナー、ベンダー、または顧客のテナント
- コラボレーションまたはテストに使用される従業員が作成したテナント

関連付けられたメトリックは、受信 ID と送信 ID アクティビティを表します。これにより、管理者はテナント間 ID の使用状況が存在する場所と、さらにガバナンスの考慮事項が必要かどうかを把握できます。

#### マルチテナント アプリケーション

マルチテナント アプリケーションは、あるテナントに登録されているアプリケーションが別のテナントのユーザーに同意またはアクセスされる、サーフェスの関係を示します。

これらのシグナルによって、次の情報が表示される場合があります。

- SaaS プロバイダー テナント
- 共有アプリケーションをホストする内部で作成されたテナント

メトリックは、テナント間のアプリケーション レベルの依存関係を強調表示する、受信および送信マルチテナント アプリケーションの使用状況を表します。

#### 共有課金アカウント

共有課金アカウントシグナルは、1 つ以上の共有課金アカウントを介して接続されているテナントを識別します。

これらのシグナルは、次の識別に特に役立ちます。

- 組織に関連するテナント
- 一元化された課金に関連付けられている内部で作成されたテナント

関連するメトリックは、課金関係とアクティブな機能を表し、管理者がガバナンス アクションを保証する可能性のある財務リンケージを理解するのに役立ちます。

### 検出からガバナンスまで

**テナントの検出** は意図的に開始点であり、自動強制メカニズムではありません。

関連するテナントが検出されると、組織は次のことができます。

1. 環境と相互作用するテナントをレビューする
2. 承認されていないテナントまたはシャドウ IT テナントを特定する
3. 探索メトリックを使用して調査に優先順位を付ける
4. 監視を必要とするテナントのガバナンス アクションを開始する

一部の関連テナントにはガバナンスが必要な場合があります。他のユーザーは監視を必要とする場合があります。多くは、まったくアクションを必要としない場合があります。 関連するテナント機能を使用すると、最初に可視性を提供し、次に意図的なガバナンスの決定を可能にすることで、この差別化が可能になります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tenant-governance/signals-metrics"} -->
## テナント検出のシグナルとメトリック - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/signals-metrics
- Service: entra-id-governance
- Article date: 2026-03-20
- Summary: 関連するテナントを識別して評価するために Microsoft Entra テナント ガバナンスで使用されるシグナルとメトリックについて説明します

**テナントの検出** は、テナント ガバナンスの柱内の主要な機能であり、組織が **関連するテナント** (テナントと検出可能な関係を持つテナント) を識別するのに役立ちます。

検出では、既存の Microsoft Entra と Azure プラットフォームの相互作用から派生した **、明確に定義された少数の検出シグナル** が使用されます。 これらのシグナルは、2 つのテナントが関連する *理由* を説明します。 メトリックは、方向、機密性、相対スケールなどの**追加のコンテキストを提供**し、管理者がそれらの関係を解釈し、ガバナンス アクションに優先順位を付けるのに役立ちます。

この記事では、以下に重点を置いています。

- 検出シグナルとその表現
- 各シグナルの構成方法
- メトリックの概念が各シグナルにどのように適用されるか
- シグナルとメトリックを一緒に解釈する方法

### 検出シグナルの概要

| タイプ | 検出シグナル | それが表すもの | 自然 |
| --- | --- | --- | --- |
| B2B コラボレーション | **B2B 登録** | テナント間のゲストユーザー対応 | 状態ベース |
| B2B コラボレーション | **B2B サインイン** | テナント間サインイン アクティビティ | アクティビティに基づく |
| B2B コラボレーション | **管理者アプリのサインイン** | 事前定義された管理者アプリケーション間でのクロステナントサインイン活動 | アクティビティに基づく |
| マルチテナント アプリケーション | **マルチテナント アプリケーション** | テナント間のアプリケーションの信頼と同意 | 状態ベース |
| 共有課金アカウント | **共有課金アカウント** | テナント間の財務と運用上のリンケージ | 状態ベース |

検出シグナルは **説明的であり、規範的ではありません**。 シグナルは、リレーションシップが存在し、その理由が所有権や必要なアクションを意味しないことを説明します。

### 検出シグナル

これらのセクションでは、各探索信号とそのサブ信号について詳しく説明します。

#### B2B コラボレーション 信号

**企業間 (B2B) コラボレーションシグナル**は、関連するテナントとの**テナント間 ID の対話**に参加するテナントを識別します。 Microsoft Entra 外部 ID に基づいて構築され、ユーザーのコラボレーションとテナント間の管理アクティビティの両方をキャプチャします。

概念レベルでは、このシグナルは次の答えを示します。

*あるテナントの ID が別のテナントに対して認証を行っているか、もしくは別のテナントと共同作業をしているか?*

このシグナルは、テナント間の対話の幅と深さの両方を反映するために、複数の ID 入力を意図的に結合します。

B2B コラボレーション信号は、次の 3 つの関連するサブ信号を結合します。

- B2B 登録
- B2B ログイン
- 管理者アプリのサインイン

##### B2B 登録

B2B 登録には、別のテナントに登録されているテナントの **ゲスト ユーザーまたは外部メンバー** の存在が反映されます。 これは、多くの場合、テナント間コラボレーションの最初の監視可能なインジケーターです。

**重要な理由**

- 信頼境界が越えられたことを確立します。
- リソースへの潜在的なアクセスを示します
- アクティブな使用状況を意味しません

##### B2B ログイン

B2B ユーザー サインインでは、テナント間のゲスト ユーザーまたは外部メンバーによる **認証アクティビティ** がキャプチャされます。 登録とは異なり、サインインはアクティブなコラボレーションを示します。

**重要な理由**

- アクティブなリレーションシップと休止中のリレーションシップを区別する
- *最近*のアクティビティの主要なシグナルとして機能します
- テナント関係の運用上の関連性を評価するのに役立ちます

##### 管理者アプリのサインイン

管理者アプリのサインインは、**ユーザーがテナント間で定義済みの Microsoft Entra 管理アプリケーションに対して認証**を行うときに発生する**、B2B サインインの特殊なサブセット**です。通常、これらのサインインは、コラボレーションだけでなく、テナント間の管理アクティビティを示します。

**"管理者アプリ" とは** 管理アプリは、ファースト パーティの Microsoft Entra 管理サーフェイスの定義済みのセットです。 **テナント探索**サービスは、管理者アプリの正確なセットを定義し、維持します。 このセットは、お客様が構成することはできません。

| アプリケーション |
| --- |
| Azure portal |
| Microsoft Entra 管理センター |
| Intune 管理センター |
| Exchange 管理センター |
| Windows Admin Center |
| SharePoint テナント管理センター |
| Microsoft Teams 管理ポータル サービス |
| Microsoft 365 管理 ポータル |
| Microsoft Office 365 ポータル |

**重要な理由**

- テナント間の信頼性と権限の向上を示します
- 管理ワークフローがテナントの境界をまたがっていることを提案する
- 多くの場合、ガバナンスの関連性が高いと関連付けられる

##### B2B コンポーネントの連携方法

| 観察 | 解釈 |
| --- | --- |
| 登録のみ | 信頼が確立され、アクティビティが不明 |
| ユーザーサインインがあります | アクティブなコラボレーション |
| 管理者アプリのサインインが記録されている | 管理上の結合と増大した影響 |

これらの入力を組み合わせることで、ユーザー レベルのデータを公開することなく、階層化されたコンテキストが提供されます。

#### マルチテナントアプリケーションの信号

**マルチテナント アプリケーション シグナルは、マルチテナント アプリケーション**の登録とテナント間の同意とインスタンス化を通じて、関連するテナントとの**アプリケーション レベルの信頼関係**を確立しているテナントを識別します。

概念レベルでは、このシグナルは次の答えを示します。

*アプリケーションは 1 つのテナントに登録され、別のテナントで信頼され、インスタンス化されていますか?*

このシグナルは、人間以外の信頼関係をキャプチャします。多くの場合、長く保持され、ユーザーの共同作業よりも監査が困難です。

**重要な理由**

- アプリケーションには広範なアクセス許可がある場合があります
- 信頼関係は永続的
- アクティブなユーザー コラボレーションがなくてもリスクが存在する可能性がある

#### 共有課金アカウントシグナル

**課金シグナル**は、Azure MCA エンタープライズ課金アカウントの**プライマリテナントと関連する課金テナント**の基本概念を通じて接続されているテナントを識別します。

概念レベルでは、このシグナルは次の答えを示します。

*これらのテナントは、財務上または運用上リンクされていますか?*

このシグナルは、ID やアプリケーションの信頼ではなく、組織の所属を反映しています。

現時点では、Enterprise Agreement (EA) と従来のコマース コンストラクトはサポートされていません。 課金シグナルを使用して関連するテナントを検出するには、Microsoft 顧客契約 (MCA) エンタープライズ課金アカウントが必要です。

**重要な理由**

- 内部所有権または整合の強力なインジケーター
- 多くの場合、一元的に資金を提供される環境と関連付けます
- 優先順位付けの信頼性の高い入力

### メトリックの概念

メトリックは、探索シグナルの追加のコンテキストを提供します。 管理者が次のことを理解するのに役立ちます。

- リレーションシップが現在のものか過去のものか
- 信頼とアクセスの方向性
- リレーションシップの相対的な強さ

すべてのメトリックの概念がすべてのシグナルに適用されるわけではありません。

#### 初期メトリックと最近のメトリック

アクティビティ ベースのシグナルの場合、 **テナント検出** では **初期** メトリックと **最近** のメトリックが区別されます。

| メトリクス | 説明 |
| --- | --- |
| 初期 | 対象となるアクティビティの最初に観察されたインスタンス |
| 最近 | ローリング ウィンドウ内で発生中または新たに観察されたアクティビティ |

この区別の答えは次のとおりです。

*このリレーションシップが検出されたのはいつですか。このリレーションシップは現在もアクティブに使用されていますか?*

**シグナルの適用可能性**

| 信号 | 初期 | 最近 |
| --- | --- | --- |
| B2B コラボレーション | ✅ | ✅ |
| マルチテナント アプリケーション | ✅ | ✅ |
| 共有課金アカウント | ✅ | ✅ |

#### 受信メトリックと送信メトリック

受信メトリックと送信メトリックは **、対話の方向**を表します。

| 方向 | 定義 |
| --- | --- |
| 受信 | 関連するテナントからのアクティビティまたは構成。 |
| 送信 | 呼び出し元のテナントから発信されたアクティビティまたは構成。 |

この回答は次のとおりです。

*信頼やアクセスの方向性は何ですか?*

**シグナルの適用可能性**

| 信号 | 受信/送信 |
| --- | --- |
| B2B コラボレーション | ✅ |
| マルチテナント アプリケーション | ✅ |
| 共有課金アカウント | ❌ |

#### Aggregations

**テナント検出** では、未加工のカウントではなく、 **集計されたメトリック**が表示されます。 値は、正確な数値ではなく、**桁**で返されます。

集計の結果:

*この関係は高いレベルでどの程度重要ですか?*

**集計の動作**

| 実際の値の範囲 | 返される値 |
| --- | --- |
| 1-9 | 1 |
| 10-99 | 10 |
| 100-999 | 100 |
| 1,000-9,999 | 1,000 |
| 10,000-99,999 | 1万 |

最近のメトリックは、アクティビティが新しい桁に入ったときにのみ更新されます。

**例 (B2B サインイン)**

- 初期検出: 50 ユーザー →戻り値 10
- 後で 101 ユーザーに増加→、値 100 が返され、タイムスタンプが更新されます

**シグナルの適用可能性**

| 信号 | Aggregations |
| --- | --- |
| B2B コラボレーション | ✅ |
| マルチテナント アプリケーション | ✅ |
| 共有課金アカウント | ✅ |

課金は、アクティビティベースではなくプレゼンスベースです。

#### シグナル メトリック マッピングの概要

| メトリックの概念 | B2B | マルチテナント アプリ | 請求書 |
| --- | --- | --- | --- |
| 初期と最近のバージョン | ✅ | ✅ | ✅ |
| インバウンドとアウトバウンド | ✅ | ✅ | ❌ |
| 集計されたカウント | ✅ | ✅ | ✅ |
| アクティビティに基づく | ✅ | ❌ | ❌ |
| 設定ベース | ✅ | ✅ | ✅ |

シグナルは、テナントが関連する ***理由*** を説明し、メトリックでは ***、方法*、 *量*、および *最近の状態を説明します*。** どちらも意図的に規範的ではない。 ガバナンス アクションを実施することなく、調査と優先順位付けを通知します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/trigger-custom-task"} -->
## カスタム タスク拡張機能に基づいて Logic Apps をトリガーする - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/trigger-custom-task
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2024-12-10
- Summary: カスタム タスク拡張機能に基づいて Logic Apps をトリガーする

ライフサイクル ワークフローを使用すると、Azure Logic Apps の拡張機能を使用してカスタム タスクをトリガーできます。 これを使用して、組み込みのタスクを超えてライフサイクル ワークフローの機能を拡張できます。 カスタム タスク拡張機能に基づいてロジック アプリをトリガーする手順は次のとおりです。

- カスタム タスク拡張機能を作成します。
- カスタム タスク拡張機能で実行する動作を選択します。
- カスタム タスク拡張機能を新規または既存の Azure Logic App にリンクします。
- カスタム タスクをワークフローに追加します。

ライフサイクル ワークフローの拡張性の詳細については、「 [ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)の拡張性」を参照してください。

### Microsoft Entra 管理センターを使用してカスタム タスク拡張機能を作成する

ワークフローでカスタム タスク拡張機能を使用するには、最初にカスタム タスク拡張機能を作成して、Azure Logic App にリンクする必要があります。 カスタム タスク拡張機能の作成と同時にロジック アプリを作成できます。 これを行うには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**ワークフロー**にアクセスします。
3. [ライフサイクル ワークフロー] 画面で、[カスタム タスク拡張機能 を選択します。
4. [カスタム タスク拡張機能] ページで、[カスタム タスク拡張機能の作成] 選択します。 [Image: カスタム タスク拡張機能の選択を作成するためのスクリーンショット。]
5. [基本] ページで、カスタム タスク拡張機能の一意の表示名と説明を入力し、[次へ] 選択します。 [Image: カスタム タスク拡張機能を作成するための [基本] セクションのスクリーンショット。]
6. [タスクの動作 ] ページで、Azure Logic App の実行後にカスタム タスク拡張機能がどのように動作するかを指定します。 **[起動**] を選択して続行した場合は、[**次へ: 詳細**] をすぐに選択できます。 [Image: カスタム タスク拡張機能のタスクの選択動作のスクリーンショット。]
7. [起動 待機] を選択すると、タスクが失敗と見なされるまでロジック アプリからの応答を待機する期間、および応答承認 設定するオプションが表示されます。 これらのオプションを選択すると、[次へ: 詳細] 選択できるようになります。 [Image: カスタム タスク拡張機能の起動と待機オプションのスクリーンショット。]

    手記

    カスタム タスク拡張機能の動作の詳細については、「ライフサイクル ワークフローの拡張性 」を参照してください。
8. **[ロジック アプリの詳細**] ページで、[**新しいロジック アプリの作成**] を選択し、配置するサブスクリプションとリソース グループを指定します。 また、新しい Azure ロジック アプリに名前を付けます。 [Image: カスタム タスク拡張機能用の新しいロジック アプリの作成を示す画面。]

    重要

    ロジック アプリは、カスタム タスク拡張機能と互換性を持つよう構成する必要があります。 詳細については、「[ライフサイクル ワークフローを使用するためのロジック アプリの構成](https://learn.microsoft.com/ja-jp/entra/id-governance/configure-logic-app-lifecycle-workflows)」を参照してください。
9. 正常にデプロイされた場合は、 **すぐにロジック アプリの詳細** ページに確認が表示され、[ **次へ**] を選択できます。
10. [ **確認** ] ページでは、カスタム タスク拡張機能と作成した Azure ロジック アプリの詳細を確認できます。 詳細がカスタム タスク拡張機能に必要な内容と一致する場合は、[ **作成** ] を選択します。

### カスタム タスク拡張機能をワークフローに追加する

カスタム タスク拡張機能を作成したら、ワークフローに追加できるようになりました。 カテゴリに一致するワークフロー テンプレートにのみ追加できる一部のタスクとは異なり、カスタム タスク拡張機能は、カスタム ワークフローの作成元として選択した任意のテンプレートに追加できます。

カスタム タスク拡張機能をワークフローに追加するには、次の手順を実行します。

1. 左側のメニューで、[ **ライフサイクル ワークフロー**] を選択します。
2. 左側のメニューで、[ワークフロー] 選択します。
3. カスタム タスク拡張機能を追加するワークフローを選択します。
4. ワークフロー画面で、[タスク] を選択 **します**。
5. [タスク] 画面で、[タスク 追加] を選択します。
6. [ **タスクの選択** ] サイド メニューで、[ **カスタム タスク拡張機能の実行**] を選択し、[ **追加**] を選択します。
7. カスタム タスク拡張機能ページでは、タスクに名前と説明を付けることができます。 また、使用する構成済みのカスタム タスク拡張機能の一覧から選択することもできます。 [Image: カスタム タスク拡張機能をワークフローに追加するスクリーンショット。]
8. 完了したら、[ **保存]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tutorial-mover-custom-workflow-portal"} -->
## Microsoft Entra 管理センターを使って従業員が仕事を変えたときの異動に関するタスクを自動化する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-mover-custom-workflow-portal
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: Microsoft Entra 管理センターでのライフサイクル ワークフローを使用して仕事を変えるユーザーを移動するためのチュートリアル。

このチュートリアルでは、Microsoft Entra 管理センターでライフサイクル ワークフローを使用して異動に関するタスクを自動化する方法のステップバイステップ ガイドを提供します。 このチュートリアルのユース ケースでは、既存のユーザーを新しい部署に追加します。

この異動者のシナリオでは、スケジュールされたワークフローを実行して、次のタスクを行います。

1. マネージャーにユーザーの移動を通知するメールを送信する
2. ユーザーのすべてのアクセス パッケージの割り当てを削除します (削除は既定で 15 日後にスケジュールされます)
3. グループにユーザーを追加する

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンス または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### 開始する前に

このチュートリアルを完了するには、チュートリアルを開始する前に、このセクションに記載されている前提条件を満たす必要があります。これらの前提条件は実際のチュートリアルには含まれていません。 2 つのアカウントが必要です。1 つは、ユーザーが正社員になるアカウント (ジョブ プロファイルの変更) と、そのマネージャーとして機能するもう 1 つのアカウントです。 ユーザー アカウントには、次の属性を設定しておく必要があります。

- マネージャー属性を設定した、ワークフローの実行対象とする既存のユーザー、およびマネージャー アカウントにメールを受信するためのメールボックスが必要です。
- テナント内の "Sales" という名前のセキュリティ グループ。

関連する属性の詳細な内訳は次のとおりです。

| 属性 | 説明 | 設定対象 |
| --- | --- | --- |
| メール | 新しい従業員の一時的なアクセス パスをマネージャーに通知するために使用されます | 両方 |
| マネージャー | この属性は、ライフサイクル ワークフローによって使用されます | 従業員 |
| 部署 | ワークフローのスコープを指定するために使います | 従業員 |

異動者のシナリオは次の手順に分けることができます。

- **前提：** 2 つのユーザー アカウントを作成します。1 つは従業員を表し、1 つはマネージャーを表します
- **前提：** ユーザーを追加するグループを作成する
- **前提：** 管理センターでこのシナリオに必要な属性を編集する
- **前提：** Microsoft Graph Explorer を使用してこのシナリオの属性を編集する
- ワークフローを作成する
- ワークフローをトリガーする
- ワークフローが正常に実行されたことを確認する

### 異動者テンプレートを使用してワークフローを作成する

属性の変更によってトリガーされる異動を行うユーザーの異動者ワークフローを作成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **[ID ガバナンス]**&gt;**[ライフサイクル ワークフロー]**&gt;**[ワークフローを作成]** の順に参照します。
3. テンプレートから、 **従業員のジョブ プロファイルの変更**を選択します。 [Image: 従業員のジョブ プロファイル変更テンプレートを選択するスクリーンショット。]
4. 次に、ワークフローに関する基本情報を構成します。 この情報には、名前、説明、 [および管理スコープ](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-delegate-workflow)が含まれます。 また、ワークフローの **トリガーの種類** (この場合は **属性変更** トリガー) を選択することもできます。 **[トリガー属性**] では、変更する属性を定義してワークフローをトリガーできます。この場合は**部門**です。 トリガーが設定されたら、[スコープの **構成]** を選択します。 [Image: テンプレートで属性変更メンバーシップ トリガーを設定するスクリーンショット。]
5. 次の画面では、スコープを構成します。 スコープによって、このワークフローの実行対象のユーザーが決まります。 この場合は、営業部門に追加されるすべてのユーザーが対象です。 スコープの構成画面で、[ **ルール** ] で次の設定を追加し、[ **タスクの確認**] を選択します。 サポートされているユーザー プロパティの完全な一覧については、「 [サポートされているユーザー プロパティとクエリ パラメーター](https://learn.microsoft.com/ja-jp/graph/api/resources/identitygovernance-rulebasedsubjectset?view=graph-rest-beta&preserve-view=true#supported-user-properties-and-query-parameters)」を参照してください。 [Image: 属性変更スコープの設定のスクリーンショット。]
6. [ **タスクの確認** ] 画面では、タスクを追加、編集、または削除できます。 既定のタスクから、 **選択したグループからユーザーを削除**し、 **選択した Teams からユーザーを削除**し、リストから **ユーザー アクセス パッケージの割り当てを要求** し、[タスクの追加] 画面から **グループにユーザーを** 追加します。 **ユーザーのすべてのアクセス パッケージの割り当てを削除** タスクは、既定で含まれており、スケジュールされた削除は 15 日に設定されているため、そのまま保持します。 [グループ **にユーザーを追加] タスクを編集して** 、Sales グループが選択されるようにします。 完了したら、[ **確認と作成**] を選択します。 [Image: ジョブ変更テンプレート タスクのスクリーンショット。]
7. 確認画面で、情報が正しいことを確認し、ワークフローのスケジュールを有効にすることを選択します。 確認したら、[ **作成**] を選択します。 [Image: ジョブ変更テンプレートの確認のスクリーンショット。]

### ワークフローを実行する

ワークフローが作成されたので、ワークフローを実行する対象のユーザーに移動し、それらを Sales 部署に追加します。 30 分以内に、ユーザーはワークフローの実行条件のスコープに表示されます。 ライフサイクル ワークフローは、関連付けられた実行条件でユーザーを 3 時間ごとにチェックし、それらのユーザーに対して構成されたタスクを実行します。

### ワークフローの状態とタスクを確認する

ユーザーの属性を設定した後、ワークフローの状態、そのスコープを満たすユーザー、およびそのタスクを確認できます。 注意として、現在使用できるユーザー、実行、タスクという 3 つの異なるデータ ピボットがあります。 詳細については、ワークフローの状態の確認に関 [する](https://learn.microsoft.com/ja-jp/entra/id-governance/check-status-workflow)ハウツー ガイドを参照してください。 このチュートリアルでは、ユーザーに焦点を当てたレポートを使用して状態を確認します。

1. 作成したワークフローで、[ **実行条件** ] を選択し、[ **実行ユーザー スコープ** ] セクションに移動します。
2. [ **実行ユーザー スコープ]** ページには、現在実行ユーザー スコープを満たしているユーザーが表示され、次回の実行時にワークフローが実行されます。 [Image: 実行ユーザー スコープのスクリーンショット。]
3. ユーザーのワークフローが実行されたら、[ **ワークフロー履歴** ] タブを選択して、ユーザーの概要と関連するワークフロー タスクと状態を表示します。
4. [ **ワークフロー履歴** ] タブに、ワークフロー履歴ページが表示されます。 [Image: ワークフロー履歴ページのスクリーンショット。]
5. このページでは、ユーザーに基づいて、処理された合計数、正常に処理された数、処理に失敗した数、成功したタスク数、失敗したタスク数の概要を確認できます。
6. 処理されたユーザー数の一覧から、ユーザーを選択し、ユーザーに対して実行されたタスクやそれらのタスクが正常に実行されたかどうかを確認することもできます。 タスクが失敗した場合は、その理由を確認できます。 [Image: ユーザーのタスクの状態のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tutorial-offboard-custom-workflow-portal"} -->
## ライフサイクル ワークフローを使用して従業員の退職タスクを実行する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-offboard-custom-workflow-portal
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: Microsoft Entra 管理センターのライフサイクル ワークフローを使用して、在籍最終日にユーザーを組織からリアルタイムで削除する方法について説明します。

このチュートリアルでは、Microsoft Entra 管理センターでライフサイクル ワークフローを使用して従業員の退職をリアルタイムで実行する方法のステップバイステップ ガイドを提供します。

この "退職者" シナリオでは、ワークフローをオンデマンドで実行し、次のタスクを実行します。

- ユーザーをすべてのグループから削除します。
- ユーザーをすべての Microsoft Teams メンバーシップから削除します。
- ユーザー アカウントを削除します。

詳しくは、「[ワークフローをオンデマンドで実行する](https://learn.microsoft.com/ja-jp/entra/id-governance/on-demand-workflow)」を参照してください。

### 前提条件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### 開始する前に

このチュートリアルを完了するには、チュートリアルを開始する前に、このセクションの中に記載されている前提条件を満たす必要があります (この実際のチュートリアルにはそれらが含まれていないため)。 このチュートリアルを行うための前提条件の 1 つとして、グループと Teams のメンバーシップを持ち、チュートリアルの間に削除できるアカウントが必要です。 これらの前提条件の手順を実行する方法の包括的な手順については、[ライフサイクル ワークフローのユーザー アカウントの準備](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-prepare-user-accounts)に関する記事を参照してください。

この退職者シナリオには、次の手順が含まれています。

1. 前提条件: 組織を辞める従業員を表すユーザー アカウントを作成する。
2. 前提条件: グループと Teams メンバーシップでユーザー アカウントを準備する。
3. ライフサイクル管理ワークフローを作成する。
4. オンデマンドでワークフローを実行する。
5. ワークフローが正常に実行されたことを確認する。

### 退職者テンプレートを使用してワークフローを作成する

次の手順に従って、Microsoft Entra 管理センター内でライフサイクル ワークフローを使用してリアルタイムで従業員の退職を実行する、退職者オンデマンド ワークフローを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)以上としてサインインします。
2. **ID ガバナンス** を選択します。
3. **[ライフサイクル ワークフロー]** を選択します。
4. **[概要]** タブで **[新しいワークフロー]** を選択します。

    [Image: 新しいワークフローを作成するための [概要] タブとボタンのスクリーンショット。]
5. テンプレートのコレクションから、**[リアルタイムの従業員の退職]** の **[選択]** を選びます。

    [Image: 従業員のリアルタイムの退職のワークフロー テンプレートを選択しているスクリーンショット。]
6. ワークフローに関する基本情報 (名前、説明、 [管理スコープ](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-delegate-workflow)など) を構成し、[ **次へ: タスクの確認**] を選択します。

    [Image: ワークフローの基本情報のタブのスクリーンショット。]
7. 必要に応じてタスクを検査しますが、追加の構成は必要ありません。 終わったら、**[次へ: ユーザーの選択]** を選択します。

    [Image: テンプレートの確認タスクのタブのスクリーンショット。]
8. **[ユーザーの選択]** ページ上の **[選択の種類]** で、**[今すぐ実行するユーザーを選択]** を選択します。 これにより、作成直後にワークフローを実行するユーザーを選択できます。 選択に関係なく、後で必要に応じていつでもワークフローをオンデマンドで実行できます。

    [Image: 今すぐ実行するユーザーを選択するためのオプションのスクリーンショット。]
9. **[ユーザーの追加]** を選択して、このワークフローのユーザーを指定します。

    [Image: ユーザーを追加するためのボタンのスクリーンショット。]
10. 選択可能なユーザーの一覧を含むパネルが、ウィンドウの右側に表示されます。 選択が完了したら、**[選択]** を選択します。

    [Image: 選択可能なユーザーの一覧のスクリーンショット。]
11. ユーザーの選択に問題がなければ、**[次へ: 確認と作成]** を選択します。

    [Image: 追加されたユーザーのスクリーンショット。]
12. 情報が正しいことを確認し、 **[作成]** を選択します。

    [Image: ワークフローの選択を確認するためのタブと、ワークフローを作成するためのボタンのスクリーンショット。]

### ワークフローを実行する

このワークフローは作成されると、3 時間ごとに自動的に実行されます。 ライフサイクル ワークフローは、関連する実行条件のユーザーを 3 時間ごとにチェックし、それらのユーザーに対して構成されたタスクを実行します。

ワークフローをすぐに実行するには、オンデマンド機能を使用できます。

注

現在、**[無効]** に設定されているワークフローをオンデマンドで実行することはできません。 オンデマンド機能を使うには、ワークフローを **[有効]** に設定する必要があります。

Microsoft Entra 管理センターを使って、ユーザーに対してワークフローをオンデマンドで実行するには:

1. ワークフロー画面で、実行する特定のワークフローを選びます。
2. **[オンデマンドで実行]** を選びます。
3. **[ユーザーの選択]** タブで、**[ユーザーの追加]** を選びます。
4. ユーザーを追加する。
5. **[ワークフローの実行]** を選びます。

### タスクとワークフローの状態を調べる

いつでも、ワークフローとタスクの状態を監視できます。 現在、3 つのデータ ピボット、ユーザー、実行、タスクを使用できます。 詳しくは、ハウツー ガイド「[ワークフローの状態を確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/check-status-workflow)」をご覧ください。 このチュートリアルでは、ユーザーに重点を置いたレポートを使用して状態を確認します。

1. ワークフローの **[概要]** ページで、**[Workflow history] (ワークフロー履歴)** を選択します。

    [Image: ワークフローの [概要] ページのスクリーンショット。]

    **[ワークフロー履歴]** ページが表示されます。

    [Image: リアルタイム ワークフロー履歴のスクリーンショット。]
2. ユーザーの **[合計タスク数]** を選んで、作成されたタスクの合計数とその状態を表示します。

    [Image: リアルタイム ワークフローの合計タスク数のスクリーンショット。]
3. 細分性のレイヤーを追加するには、ユーザーの **[失敗したタスク]** を選んで、そのユーザーに割り当てられた失敗したタスクの合計数を表示します。

    [Image: リアルタイム ワークフローの失敗したタスクのスクリーンショット。]
4. ユーザーに割り当てられた未処理または取り消されたタスクの合計数を表示するには、そのユーザーの **[未処理のタスク]** を選択します。

    [Image: リアルタイム ワークフローの未処理タスクのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tutorial-onboard-custom-workflow-portal"} -->
## 初出勤日前に従業員のオンボーディング タスクを Microsoft Entra 管理センターを使って自動化する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-onboard-custom-workflow-portal
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: Microsoft Entra 管理センターでのライフサイクル ワークフローを使用した組織へのユーザーのオンボードに関するチュートリアル。

このチュートリアルでは、Microsoft Entra 管理センターでライフサイクル ワークフローを使用して雇用前タスクを自動化する方法のステップバイステップ ガイドを提供します。

この前雇用シナリオでは、新しい従業員の一時的なアクセス パスが生成され、ユーザーの新しいマネージャーに電子メールで送信されます。

[Image: ライフサイクル ワークフロー シナリオのスクリーンショット。]

### 前提条件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### 開始する前に

このチュートリアルを完了するには、チュートリアルを開始する前に、このセクションに一覧表示されている前提条件を満たす必要があります。これらは実際のチュートリアルには含まれていないためです。 このチュートリアルには 2 つのアカウントが必要です。1 つは新規従業員用のアカウント、もう 1 つは新規従業員のマネージャーとして機能するアカウントです。 新入社員アカウントには、次の属性を設定しておく必要があります。

- employeeHireDate は today に設定する必要があります
- 部署は営業に設定する必要があります
- manager 属性を設定する必要があり、manager アカウントにはメールを受信するためのメールボックスが必要です。

これらの前提条件の手順を完了する方法に関するより包括的な手順については、 [ライフサイクル ワークフローのユーザー アカウントの準備に関するチュートリアルを](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-prepare-user-accounts)参照してください。 このチュートリアルを実行するには、 [TAP ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#enable-the-temporary-access-pass-policy) も有効にする必要があります。

関連する属性の詳細な内訳は次のとおりです。

| 属性 | 説明 | 有効化 |
| --- | --- | --- |
| メール | 新しい従業員の一時的なアクセス パスをマネージャーに通知するために使用されます | 両方 |
| マネージャー | この属性は、ライフサイクル ワークフローによって使用されます | 社員 |
| 従業員採用日 | ワークフローをトリガーするために使います | 社員 |
| 部署 | ワークフローのスコープを指定するために使います | 社員 |

雇用前のシナリオは、次のセクションに分けることができます。

- **前提：** 2 つのユーザー アカウントを作成します。1 つは従業員を表し、1 つはマネージャーを表します
- **前提：** 管理センターでのこのシナリオに必要な属性の編集
- **前提：** Microsoft Graph Explorer を使用してこのシナリオの属性を編集する
- **前提：** 一時アクセス パス (TAP) の有効化と使用
- ライフサイクル管理ワークフローを作成する
- ワークフローをトリガーする
- ワークフローが正常に実行されたことを確認する

### Prehire テンプレートを使用してワークフローを作成する

次の手順を使用して、TAP を生成し、Microsoft Entra 管理センターを使用してユーザーのマネージャーに電子メールで送信する事前雇用ワークフローを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス** を選択します。
3. **ライフサイクル ワークフロー**を選択します。
4. [ **概要** ] ページで、[ **新しいワークフロー**] を選択します。 [Image: 新しいワークフローを選択するスクリーンショット。]
5. テンプレートから、**事前採用者のオンボード** の下で **選択** を選びます。 [Image: ワークフロー テンプレートの選択のスクリーンショット。]
6. 次に、ワークフローがいつトリガーするかを含むワークフローに関する基本情報を構成します ( **イベントからの日数**と呼ばれます)。 この場合、従業員の入社日の 2 日前にこのワークフローがトリガーされます。 従業員の事前雇用のオンボード画面で、次の設定を追加し、[ **次へ: スコープの構成**] を選択します。

    [Image: 構成スコープの選択のスクリーンショット。]
7. 次に、スコープを構成します。 スコープによって、このワークフローの実行対象のユーザーが決まります。 この場合は、Sales 部署のすべてのユーザーになります。 [スコープの構成] 画面の [ **ルール**] で、次の設定を追加し、[ **次へ: タスクの確認**] を選択します。 サポートされているユーザー プロパティの完全な一覧については、「 [サポートされているユーザー プロパティとクエリ パラメーター](https://learn.microsoft.com/ja-jp/graph/api/resources/identitygovernance-rulebasedsubjectset?view=graph-rest-beta&preserve-view=true#supported-user-properties-and-query-parameters)」を参照してください。

    [Image: レビュー タスクの選択のスクリーンショット。]
8. 次のページでは、必要に応じてタスクを調べることができますが、追加の構成は必要ありません。 完了したら、[ **次へ: 確認と作成** ] を選択します。 [Image: オンボード ワークフローの確認のスクリーンショット。]
9. レビュー画面で、情報が正しいことを確認し、[ **作成**] を選択します。 [Image: オンボード ワークフローの作成のスクリーンショット。]

### ワークフローを実行する

これでワークフローが作成されました。ワークフローは 3 時間ごとに自動的に実行されます。 つまり、ライフサイクル ワークフローは、関連する実行条件のユーザーについて 3 時間ごとにチェックし、それらのユーザーに対して構成されたタスクを実行します。 ただし、このチュートリアルでは、すぐに実行します。 ワークフローをすぐに実行するには、オンデマンド機能を使用できます。

注意

現在、ワークフローが無効に設定されている場合は、オンデマンドでそれを実行できないことに注意してください。 オンデマンド機能を使うには、ワークフローを有効に設定する必要があります。

Microsoft Entra 管理センターを使用してユーザーのワークフローをオンデマンドで実行するには、次の手順を実行します。

1. ワークフロー画面で、実行する特定のワークフローを選びます。
2. [ **オンデマンドで実行**] を選択します。
3. [ **ユーザーの選択** ] タブで、[ **ユーザーの追加]** を選択します。
4. ユーザーを追加します。
5. [ **ワークフローの実行]** を選択します。

### タスクとワークフローの状態を調べる

ワークフローとタスクの状態は、いつでも監視できます。 注意として、現在使用できるユーザー、実行、タスクという 3 つの異なるデータ ピボットがあります。 詳細については、ワークフローの状態の確認に関 [する](https://learn.microsoft.com/ja-jp/entra/id-governance/check-status-workflow)ハウツー ガイドを参照してください。 このチュートリアルでは、ユーザーに焦点を当てたレポートを使用して状態を確認します。

1. 開始するには、[ **ワークフロー履歴** ] タブを選択して、ユーザーの概要と関連するワークフロー タスクと状態を表示します。[Image: ワークフロー履歴の状態のスクリーンショット。]
2. [ **ワークフロー履歴** ] タブが選択されると、次のようにワークフロー履歴ページに移動します。 [Image: ワークフロー履歴の概要のスクリーンショット]
3. 次に、ユーザー Jane Smith の **[合計タスク]** を選択して、作成されたタスクの合計数とその状態を表示できます。 この例では、ユーザー Jane Smith には全部で 3 つのタスクが割り当てられています。[Image: ワークフローの合計タスクの概要のスクリーンショット。]
4. 細分性のレイヤーを追加するには、ユーザー Jeff Smith の **[失敗したタスク** ] を選択して、ユーザー Jeff Smith に割り当てられた失敗したタスクの合計数を表示できます。 [Image: ワークフローに失敗したタスクのスクリーンショット。]
5. 同様に、ユーザー Jeff Smith の **[未処理のタスク** ] を選択すると、ユーザー Jeff Smith に割り当てられている未処理または取り消されたタスクの合計数を表示できます。 [Image: ワークフローの未処理タスクの概要のスクリーンショット。]

### ワークフローのスケジュールを有効にする

ワークフローをオンデマンドで実行し、すべてが問題なく動作していることを確認したら、ワークフローのスケジュールを有効にすることができます。 ワークフロー スケジュールを有効にするには、[プロパティ] ページの [ **スケジュールを有効にする** ] チェック ボックスをオンにします。

[Image: ワークフロー スケジュールを有効にするスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tutorial-prepare-user-accounts"} -->
## チュートリアル: ライフサイクル ワークフローのユーザー アカウントの準備 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-prepare-user-accounts
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2025-08-27
- Summary: ライフサイクル ワークフローのユーザー アカウントの準備に関するチュートリアル。

オンボーディングとオフボーディングのチュートリアルでは、ワークフローを実行するアカウントが必要です。 このセクションでは、それらのアカウントを準備する方法について説明します。以下の要件を満たすテスト アカウントを既に持っている場合は、オンボーディングとオフボーディングのチュートリアルに直接進むことができます。 オンボードのチュートリアルには 2 つのアカウントが必要です。1 つは新入社員用のアカウント、もう 1 つは新入社員のマネージャーとして機能するアカウントです。 新入社員アカウントには、次の属性を設定しておく必要があります。

- employeeHireDate は today に設定する必要があります
- department は sales に設定する必要があります
- manager 属性を設定する必要があり、manager アカウントにはメールを受信するためのメールボックスが必要です

オフボード チュートリアルには、グループと Teams のメンバーシップを持つ 1 つのアカウントのみが必要ですが、このアカウントはチュートリアルの間に削除されます。

### 前提条件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

- Microsoft Entra テナント
- Microsoft Entra テナントに対する適切なアクセス許可を持つ管理者アカウント。 このアカウントは、ユーザーとワークフローを作成するために使います。

### 開始する前に

ほとんどの場合、ユーザーは、オンプレミス ソリューション (Microsoft Entra Connect、Cloud sync など) から、または HR ソリューションを使って Microsoft Entra ID にプロビジョニングされます。 これらのユーザーには、作成時に属性と値が設定されます。 ユーザーをプロビジョニングするインフラストラクチャの設定については、このチュートリアルでは説明しません。 詳細については、「[チュートリアル: Active Directory の基本的な環境](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-basic-ad-azure)」と「[チュートリアル: 単一のフォレストを単一の Microsoft Entra テナントに統合する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest)」を参照してください。

### Microsoft Entra ID でユーザーを作成する

チュートリアルのライフサイクル ワークフローの実行に必要な 2 人のユーザーを簡単に作成するために、Graph エクスプローラーを使います。 1 人のユーザーは新入社員を、もう 1 人は新入社員のマネージャーを表します。

POST を編集して、&lt;your tenant name here&gt; の部分をお使いのテナント名に置き換える必要があります。 例: $UPN\_manager = "bsimon@&lt;your tenant name here&gt;" を $UPN\_manager = "bsimon@contoso.onmicrosoft.com" にします。

注意

従業員の雇用日 (イベントからの日数) がワークフロー作成日より前の場合、ワークフローはトリガーされません。 設計上、employeeHiredate を未来に設定する必要があります。 このチュートリアルで使われる日付はスナップショットです。 そのため、この状況に対応するために、日付を適宜変更する必要があります。

まず、従業員の Melva Prince を作成します。

1. 次に [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)に移動します。
2. テナントのユーザー管理者アカウントを使用して Graph エクスプローラーにサインインします。
3. 上部にある **[GET]** を **[POST]** に変更し、ボックスに「`https://graph.microsoft.com/v1.0/users/`」を追加します。
4. **[要求本文]** に次のコードを入力します。
5. 次のコードの `<your tenant here>` を実際の Microsoft Entra テナントの値に置き換えます。
6. **[クエリの実行]** 選びます
7. 結果で返された ID をコピーします。 これは、後でマネージャーを割り当てるために使います。

```json
{
  "accountEnabled": true,
  "displayName": "Melva Prince",
  "mailNickname": "mprince",
  "department": "sales",
  "mail": "mprince@<your tenant name here>",
  "employeeHireDate": "2022-04-15T22:10:00Z",
  "userPrincipalName": "mprince@<your tenant name here>",
  "passwordProfile" : {
    "forceChangePasswordNextSignIn": true,
    "password": "<Generated Password>"
  }
}
```

[Image: Graph エクスプローラーで POST を使って Melva を作成するスクリーンショット。]

次に Britta Simon を作成します。 このアカウントは、マネージャーとして使用されます。

1. まだ [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を表示している状態です。
2. 上部がまだ **[POST]** に設定され、「`https://graph.microsoft.com/v1.0/users/`」がボックスに入力されていることを確認します。
3. **[要求本文]** に次のコードを入力します。
4. 次のコードの `<your tenant here>` を実際の Microsoft Entra テナントの値に置き換えます。
5. **[クエリの実行]** 選びます
6. 結果で返された ID をコピーします。 この ID は、後でマネージャーを割り当てるために使います。

    ```json
    {
      "accountEnabled": true,
      "displayName": "Britta Simon",
      "mailNickname": "bsimon",
      "department": "sales",
      "mail": "bsimon@<your tenant name here>",
      "employeeHireDate": "2021-01-15T22:10:00Z",
      "userPrincipalName": "bsimon@<your tenant name here>",
      "passwordProfile" : {
        "forceChangePasswordNextSignIn": true,
        "password": "<Generated Password>"
      }
    }
    ```

注意

このコードの &lt;your tenant name here&gt; セクションを、実際の Microsoft Entra テナントに合わせて変更する必要があります。

ライフサイクル ワークフローの実行に必要な 2 人のユーザーを簡単に作成するもう 1 つの方法として、次の PowerShell スクリプトを使うこともできます。 1 人のユーザーは新入社員を、もう 1 人は新入社員のマネージャーを表します。

重要

このチュートリアルに必要な 2 人のユーザーを簡単に作成できる次の PowerShell スクリプトが用意されています。 これらのユーザーは、Microsoft Entra 管理センターで作成することもできます。

この手順を作成するために、Azure にアクセスできるマシンに次の PowerShell スクリプトを保存します。

次に、スクリプトを編集して &lt;your tenant name here&gt; の部分をお使いのテナント名に置き換える必要があります。 例: $UPN\_manager = "bsimon@&lt;your tenant name here&gt;" を $UPN\_manager = "bsimon@contoso.onmicrosoft.com" にします。

このアクションは、$UPN\_employee と $UPN\_manager の両方に対して実行する必要があります。

スクリプトを編集したら、保存して次の手順を実行します。

1. Microsoft Entra 管理センター にアクセスできるマシンから、管理者特権を使って Windows PowerShell コマンド プロンプトを開きます。
2. 保存した PowerShell スクリプトの場所に移動し、実行します。
3. PowerShell モジュールのインストール時にプロンプトが表示されたら、**[すべてはい]** を選びます。
4. プロンプトが表示されたら、テナントのグローバル管理者で Microsoft Entra 管理センターにサインインします。

```powershell
#
# DISCLAIMER:
# Copyright (c) Microsoft Corporation. All rights reserved. This 
# script is made available to you without any express, implied or 
# statutory warranty, not even the implied warranty of 
# merchantability or fitness for a particular purpose, or the 
# warranty of title or non-infringement. The entire risk of the 
# use or the results from the use of this script remains with you.
#
#
#
#
#Declare variables

$Displayname_employee = "Melva Prince"
$UPN_employee = "mprince<your tenant name here>"
$Name_employee = "mprince"
$Password_employee = "Pass1w0rd"
$EmployeeHireDate_employee = "04/10/2022"
$Department_employee = "Sales"
$Displayname_manager = "Britta Simon"
$Name_manager = "bsimon"
$Password_manager = "Pass1w0rd"
$Department = "Sales"
$UPN_manager = "bsimon@<your tenant name here>"

Install-Module -Name Microsoft.Graph
Connect-MgGraph -Confirm

$PasswordProfile = @{
  Password = "$Password_manager"
  }

New-MgUser -DisplayName $Displayname_manager -PasswordProfile $PasswordProfile -UserPrincipalName $UPN_manager -AccountEnabled $true -MailNickName $Name_manager -Department $Department

$PasswordProfile = @{
  Password = "$Password_employee"
  }

New-MgUser -DisplayName $Displayname_employee -PasswordProfile $PasswordProfile -UserPrincipalName $UPN_employee -AccountEnabled $true -MailNickName $Name_employee -Department $Department
```

Microsoft Entra ID に 1 人以上のユーザーが作成されたら、ライフサイクル ワークフローのチュートリアルに従ってワークフローを作成できます。

### 雇用前シナリオのその他の手順

[Microsoft Entra 管理センターでライフサイクル ワークフローを使用して組織にユーザーをオンボーディングする](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-onboard-custom-workflow-portal)チュートリアルと [Microsoft Graph でライフサイクル ワークフローを使用して組織にユーザーをオンボーディングする](https://learn.microsoft.com/ja-jp/graph/tutorial-lifecycle-workflows-onboard-custom-workflow)チュートリアルのいずれかでテストするときに注意する必要がある他の手順があります。

#### Microsoft Entra 管理センターを使用してユーザー属性を編集する

雇用前オンボードのチュートリアルに必要な属性の一部は Microsoft Entra 管理センターに表示され、そこで設定することができます。

次のような属性です。

| 属性 | 説明 | 設定対象 |
| --- | --- | --- |
| メール | 新入社員の一時的なアクセス パスをマネージャーに通知するために使います。 | マネージャー |
| マネージャー | この属性はライフサイクル ワークフローに使用します | 社員 |

このチュートリアルでは、**mail** 属性をマネージャー アカウントにのみ設定し、**manager** 属性を従業員アカウントに設定する必要があります。 次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. &gt; **Entra ID**&gt;**Users**」を参照します。
3. **"Melva Prince"** を選びます。
4. 上部にある **[編集]** を選びます。
5. マネージャーの下にある **[変更]** を選び、**"Britta Simon"** を選びます。
6. 上部にある **[保存]** を選択します。
7. ユーザーに戻り、**"Britta Simon"** を選びます。
8. 上部にある **[編集]** を選びます。
9. **[メール]** に有効なメール アドレスを入力します。
10. **[保存]** を選択します。

#### employeeHireDate を編集します

employeeHireDate 属性は、Microsoft Entra ID の新しい属性です。 UI には表示されないので、Graph を使って更新する必要があります。 この属性を編集するには、[Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を使用できます。

注意

従業員の雇用日 (イベントからの日数) がワークフロー作成日より前の場合、ワークフローはトリガーされません。 設計上、`employeeHireDate` を現在より後の日付に設定する必要があります。 このチュートリアルで使われる日付はスナップショットです。 そのため、この状況に対応するために、日付を適宜変更する必要があります。

このためには、ユーザー Melva Prince のオブジェクト ID を取得する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. &gt; **Entra ID**&gt;**Users**」を参照します。
3. **"Melva Prince"** を選びます。
4. **[オブジェクト ID]** の横にあるコピー記号を選びます。
5. 次に [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)に移動します。
6. テナントのグローバル管理者アカウントで Graph エクスプローラーにサインインします。
7. 上部にある **[GET]** を **[PATCH]** に変更し、ボックスに「`https://graph.microsoft.com/v1.0/users/<id>`」を追加します。 「`<id>`」を先ほどコピーした値に置き換えます。
8. **[要求本文]** に以下をコピーして、**[クエリの実行]** を選びます。

    ```Example
    {
    "employeeHireDate": "2022-04-15T22:10:00Z"
    }
    ```

    [Image: PATCH employeeHireDate のスクリーンショット。]
9. **PATCH** を **GET** に戻し、**v1.0** を **beta** にして変更を検証します。 **[クエリの実行]** を選択します。 設定された Melva の属性を確認できます。[Image: GET employeeHireDate のスクリーンショット。]

#### 従業員アカウントの manager 属性を編集する

manager 属性はメール通知タスクに使います マネージャーに新しい従業員の一時的なパスワードをメールで送信します。 次の手順に従って、Microsoft Entra ユーザーに manager 属性の値が設定されていることを確認します。

1. まだ [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を表示している状態です。
2. 上部がまだ **[PUT]** に設定され、「`https://graph.microsoft.com/v1.0/users/<id>/manager/$ref`」がボックスに入力されていることを確認します。 「`<id>`」を Melva Prince の ID に変更します。
3. **[要求本文]** に次のコードをコピーします。
4. 次のコードの「`<managerid>`」を Britta Simons の ID の値に置き換えます。
5. **[クエリの実行]** 選びます

    ```Example
    {
      "@odata.id": "https://graph.microsoft.com/v1.0/users/<managerid>"
    }
    ```

    [Image: Graph エクスプローラーでマネージャーを追加するスクリーンショット。]
6. 次に、**[PUT]** を **[GET]** に変更することで、マネージャーが正しく設定されたことを確認できます。
7. 「`https://graph.microsoft.com/v1.0/users/<id>/manager/`」がボックスに入力されていることを確認します。 「`<id>`」はまだ Melva Prince のものです。
8. **[クエリの実行]** を選択します。 その応答では Britta Simon が返されるはずです。

    [Image: Graph エクスプローラーでマネージャーを取得するスクリーンショット。]

Graph API でユーザーのマネージャー情報を更新する方法については、[マネージャーの割り当て](https://learn.microsoft.com/ja-jp/graph/api/user-post-manager?view=graph-rest-1.0&tabs=http&preserve-view=true)のドキュメントを参照してください。 この属性は、Azure 管理センターで設定することもできます。 詳細については、[プロファイル情報の追加または変更](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info?context=azure/active-directory/users-groups-roles/context/ugr-context)に関する記事を参照してください。

#### 一時アクセス パス (TAP) の有効化

一時アクセス パス は、管理者が発行する期限付のパスであり、強力な認証要件を満たすものです。

このシナリオでは、Microsoft Entra ID のこの機能を使って、新入社員のための一時的なアクセス パスを生成します。 これはその従業員のマネージャーにメールで送信されます。

この機能を使うには、Microsoft Entra テナントでこの機能を有効にする必要があります。 この機能を有効にするには、以下の手順を実行します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**一時アクセスパスを参照します。**
3. **[はい]** を選んでポリシーを有効にし、Britta Simons を追加し、ポリシーが適用されているユーザーと、**[全般]** の設定を選びます。

### 退職者シナリオに関する考慮事項

Microsoft Entra 管理センター チュートリアルまたは Microsoft Graph で Lifecycle ワークフローを使用して組織からオフボーディングするユーザーのチュートリアルをテストするときに、注意する必要がある追加の手順があります。

#### グループと Teams のチーム メンバーシップをユーザーに設定する

退職者シナリオのチュートリアルを始める前に、グループと Teams メンバーシップを持つユーザーが必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/tutorial-scheduled-leaver-portal"} -->
## 従業員の在籍最終日の後のオフボーディング タスクを Microsoft Entra 管理センターで自動化する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-scheduled-leaver-portal
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2024-11-25
- Summary: Microsoft Entra 管理センターでのライフサイクル ワークフローを使用した組織からのユーザーのオフボーディング後に関するチュートリアル。

このチュートリアルでは、従業員の在籍最終日の後のオフボーディング タスクを Microsoft Entra 管理センターを使ってライフサイクル ワークフローで構成する方法の手順を説明します。

このオフボーディング後のシナリオでは、スケジュールされたワークフローを実行して、次のタスクを行います。

1. ユーザーのすべてのライセンスを削除する
2. すべての Teams からユーザーを削除する
3. ユーザー アカウントの削除

### 前提条件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### 開始する前に

このチュートリアルを完了するには、チュートリアルを開始する前に、このセクションに一覧表示されている前提条件を満たす必要があります。これらは実際のチュートリアルには含まれていないためです。 このチュートリアルを完了するための前提条件の一部として、ライセンスと Teams のメンバーシップが設定された、チュートリアルの間に削除できるアカウントが必要です。 これらの前提条件の手順を完了する方法に関するより包括的な説明については、「[ライフサイクル ワークフローのユーザー アカウントの準備に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-prepare-user-accounts)」を参照してください。

スケジュールされた退職者のシナリオは、次のセクションに分けることができます。

- **前提条件:** 組織を辞める従業員を表すユーザー アカウントを作成する
- **前提条件:** ライセンスと Teams メンバーシップでユーザー アカウントを準備する
- ライフサイクル管理ワークフローを作成する
- 在籍最終日の後にスケジュールされたワークフローを実行する
- ワークフローが正常に実行されたことを確認する

### スケジュールされた退職者テンプレートを使用してワークフローを作成する

Microsoft Entra 管理センターを使ってライフサイクル ワークフローで、在職最終日の後の従業員のオフボーディング タスクを自動的に実行するスケジュールされた退職者ワークフローを作成するには、次の手順を使用します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)以上としてサインインします。
2. **ID ガバナンス** を選択します。
3. **[ライフサイクル ワークフロー]** を選択します。
4. **[概要]** ページで、**[New workflow] (新しいワークフロー)** を選択します。 [Image: 新しいワークフローの選択のスクリーンショット。]
5. テンプレートから、**[従業員の事後オフボーディング]** の **[選択]** を選びます。 [Image: 退職者ワークフローの選択のスクリーンショット。]
6. 次に、ワークフローに関する基本情報を構成します。 この情報には、**[イベントからの日数]** という名前で、ワークフローがトリガーされるタイミングが含まれます。 そのため、この場合、ワークフローは従業員の退職日から 7 日後にトリガーされます。 [従業員の事後オフボーディング] 画面で、次の設定を追加して、**[Next: Configure Scope] (次へ: スコープの構成)** を選びます。 [Image: ワークフローの退職者テンプレート基本情報のスクリーンショット。]
7. 次に、スコープを構成します。 スコープによって、このワークフローの実行対象のユーザーが決まります。 この場合は、マーケティング部門のすべてのユーザーになります。 スコープの構成画面の **[ルール]** を次のように追加して、**[次へ: タスクの確認]** を選択します。 サポートされているユーザー プロパティの完全な一覧については、「[サポートされているユーザー プロパティとクエリのパラメーター](https://learn.microsoft.com/ja-jp/graph/api/resources/identitygovernance-rulebasedsubjectset?view=graph-rest-beta&preserve-view=true#supported-user-properties-and-query-parameters)」を参照してください。[Image: leaver ワークフローのスコープの詳細を確認するスクリーンショット。]
8. 次のページでは、必要に応じてタスクを調べることができますが、追加の構成は必要ありません。 終わったら、**[次へ: ユーザーの選択]** を選択します。 [Image: 退職者ワークフローのタスクのスクリーンショット。]
9. 確認画面で、情報が正しいことを確認して、**[作成]** を選択します。 [Image: 作成中の退職者ワークフローのスクリーンショット。]

注意

ワークフローをオンデマンドで実行するには、**[スケジュールを有効にする]** チェック ボックスをオフにして **[作成]** を選びます。 この設定は、後ほどタスクとワークフローの状態を調べた後で有効にすることができます。

### ワークフローを実行する

これでワークフローが作成されました。ワークフローは 3 時間ごとに自動的に実行されます。 つまり、ライフサイクル ワークフローでは、関連付けられた実行条件でユーザーが 3 時間ごとにチェックされ、それらのユーザーに対して構成されたタスクが実行されます。 ただし、このチュートリアルではすぐに実行します。 ワークフローをすぐに実行する場合は、オンデマンド機能を使用できます。

注意

現在、ワークフローが無効に設定されている場合は、オンデマンドでそれを実行できないことに注意してください。 オンデマンド機能を使うには、ワークフローを有効に設定する必要があります。

Microsoft Entra 管理センターを使って、ユーザーに対してワークフローをオンデマンドで実行するには、次の手順を行います。

1. ワークフロー画面で、実行する特定のワークフローを選びます。
2. **[オンデマンドで実行]** を選びます。
3. **[ユーザーの選択]** タブで、**[ユーザーの追加]** を選びます。
4. ユーザーを追加します。
5. **[ワークフローの実行]** を選びます。

### タスクとワークフローの状態を調べる

ワークフローとタスクの状態は、いつでも監視できます。 なお、現在利用できる 3 つの異なるデータ ピボット、ユーザー実行、タスクがあります。 詳しくは、ハウツー ガイド「[ワークフローの状態を確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/check-status-workflow)」をご覧ください。 このセクションでは、ユーザーに重点を置いたレポートを使用して状態を確認します。

1. まず、**[Workflow history] (ワークフロー履歴)** タブを選択して、ユーザーの概要および関連するワークフロー タスクと状態を表示します。 [Image: ワークフローの履歴のサマリーのスクリーンショット。]
2. **[ワークフロー履歴]** タブが選択されると、次のようなワークフロー履歴ページに移動します。[Image: ワークフローの履歴の概要のスクリーンショット。]
3. 次に、ユーザー Jane Smith の **[合計タスク数]** を選択すると、作成されたタスクの合計数とその状態が表示されます。 この例では、ユーザー Jane Smith には全部で 3 つのタスクが割り当てられています。[Image: ワークフローの合計タスク数のスクリーンショット。]
4. さらに細分化するために、ユーザー Wade Warren の **[失敗したタスク]** を選択すると、ユーザー Wade Warren に割り当てられた失敗したタスクの合計数が表示されます。 [Image: ワークフローの失敗したタスクのスクリーンショット。]
5. 同様に、ユーザー Wade Warren の **[未処理のタスク]** を選択すると、ユーザー Wade Warren に割り当てられている未処理のタスクまたは取り消されたタスクの合計数が表示されます。 [Image: ワークフローの未処理タスクのスクリーンショット。]

### ワークフローのスケジュールを有効にする

ワークフローをオンデマンドで実行し、すべてが問題なく動作していることを確認したら、ワークフローのスケジュールを有効にすることができます。 ワークフローのスケジュールを有効にするには、[プロパティ] ページの **[スケジュールの有効化]** チェック ボックスをオンにします。

[Image: スケジュールが有効にされたワークフローのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/understanding-lifecycle-workflows"} -->
## ライフサイクル ワークフローについて - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-08-21
- Summary: ライフサイクル ワークフローとそのさまざまな部分の概要について説明します。

以下のドキュメントでは、ライフサイクル ワークフローを使って作成されるワークフローの概要について説明します。 ワークフローは、ライフサイクル管理の joiner-mover-leaver (JML) サイクルに基づいてタスクを自動化し、ユーザーのタスクを組織のライフサイクルに含まれるカテゴリに分割します。 これらのカテゴリはテンプレートに拡張され、組織内のユーザーのニーズに合わせてすばやくカスタマイズできます。 詳しくは、「[ライフサイクル ワークフローとは](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)」をご覧ください。

[Image: ライフサイクル ワークフローの図。]

注

ライフサイクル ワークフローは、ルーチン プロセスを自動化することで、Microsoft Entra ID ガバナンスの[人事主導のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)を強化します。 HR プロビジョニングではユーザー アカウントの作成と属性の更新が管理されますが、ライフサイクル ワークフローではタスクの追加の自動化が提供されます。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンス または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### アクセス許可とロール

ライフサイクル ワークフローを使用するために必要なサポートされている委任およびアプリケーションのアクセス許可の完全な一覧については、「[ライフサイクル ワークフローのアクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-reference#lifecycle-workflows-permissions)」を参照してください。

委任されたシナリオの場合、管理者は少なくともライフサイクル ワークフロー管理者 [Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を持っている必要があります。

### 制限

ライフサイクル ワークフロー サービスの制限の詳細な一覧については、[ライフサイクル ワークフロー サービスの制限](https://learn.microsoft.com/ja-jp/entra/id-governance/governance-service-limits#lifecycle-workflows)に関する記事を参照してください。

### ワークフローの各部分

ワークフローは、次の 3 つの主要な部分に分けることができます。

| ワークフローの部分 | 説明 |
| --- | --- |
| 一般情報 | ワークフローのこの部分には、表示名などの基本的な情報と、ワークフローの機能の説明が含まれます。 |
| タスク | タスクは、ワークフローの実行時に行われるアクションです。 |
| 実行条件 | スケジュールされたワークフローを実行するタイミング (トリガー)、およびユーザー (スコープ) を定義します。 これら 2 つのパラメータについて詳しくは、「[トリガーの詳細](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows#trigger-details)」と「[スコープ](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows#scope)」をご覧ください。 |

### テンプレート

Microsoft Entra 管理センターでワークフローを作成するには、テンプレートを使用する必要があります。 ライフサイクル ワークフロー テンプレートは、事前定義済みのタスクに使われ、ワークフローの作成の自動化に役立つフレームワークです。

[Image: ワークフロー テンプレートの概要の図。]

テンプレートは、そのカテゴリに応じて、使用できるタスクを定義し、ワークフローの作成をガイドします。 テンプレートには、基本説明、実行条件、タスク情報の入力が用意されています。

注

どのテンプレートを選ぶかにより、使用できるオプションが異なる場合があります。 このドキュメントの画像では、 [**オンボーディングの採用前従業員テンプレートを**](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#onboard-pre-hire-employee) 使用して、ワークフローの各部分を示します。

詳しくは、「[ライフサイクル ワークフロー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates)」をご覧ください。

### ワークフローの概要

各ワークフローには、この概要セクションがあり、ワークフローに関するクイック アクションを実行したり、詳細を確認したりできます。 この概要セクションは、次の 3 つのパートに分かれています。

- 基本情報
- マイ フィード
- クイック アクション

このセクションでは、各セクションの説明と、この情報から得られるアクションについて説明します。

#### 基本情報

ワークフローを選ぶと、概要の **[基本情報]** セクションに基本情報の一覧が表示されます。 これらの基本情報には、ワークフロー カテゴリ、その ID、変更された日時、再実行がスケジュールされている日時などの情報が表示されます。 この情報は、管理目的のために現在の使用状況に関する詳細情報をすばやく提供する上で重要です。 基本情報はライブ データでもあります。つまり、概要ページで実行したクイック アクションは、このセクション内ですぐに表示されます。

**[基本情報]** では、次の情報を見ることができます。

| 名前 | 説明 |
| --- | --- |
| 名前 | ワークフローの名前。 |
| 説明 | ワークフローの簡単な説明。 |
| カテゴリ | ワークフローのカテゴリを示す文字列。 |
| 作成日 | ワークフローが作成された日付。 |
| ワークフロー ID | ワークフローのユニークな識別子です。 |
| スケジュール | ワークフローの実行が現在スケジュールされているかどうかを定義します。 |
| 前回の実行日 | ワークフローが最後に実行された日付と時刻。 |
| 更新日時 | ワークフローが最後に変更された日付と時刻。 |

#### マイ フィード

ワークフローの概要の **[マイ フィード]** セクションでは、ワークフローがいつ、どのように実行されたかを簡単に確認できます。 このセクションでは、対象領域にすばやく移動して詳細を確認することもできます。 次の情報が提供されます。

- 次のターゲット実行: 次に予定されているワークフローの実行日。
- 処理されたユーザーの合計数: ワークフローで処理されたユーザーの合計数。
- 処理が失敗したユーザー数: ワークフローで処理されたユーザーのうち、失敗したユーザーの合計数。
- 失敗したタスク: 失敗したタスクの合計数。
- タスクの数: ワークフロー内のタスクの合計数。
- 現在のバージョン: 作成されたワークフローの新しいバージョンの数。

#### クイック アクション

**クイック アクション** セクションでは、ワークフローですばやくアクションを実行できます。 これらのクイック アクションは、ワークフローに何かをさせるか、履歴や編集のために使うことができます。 実行できるアクションは次のとおりです。

- オンデマンドで実行: オンデマンドでワークフローをすばやく実行できます。 このプロセスの詳細については、「[ワークフローをオンデマンドで実行する](https://learn.microsoft.com/ja-jp/entra/id-governance/on-demand-workflow)」を参照してください
- タスクの編集: ワークフロー内のタスクを追加、削除、編集、並べ替えできます。 このプロセスの詳細については、「[Microsoft Entra 管理センターを使ってワークフローのタスクを編集する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-tasks#edit-the-tasks-of-a-workflow-using-the-microsoft-entra-admin-center)」を参照してください
- ワークフローの履歴を表示: ワークフローの履歴を表示します。 3 つの履歴パースペクティブの詳細については、「[ライフサイクル ワークフローの履歴](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-history)」を参照してください

ワークフローの概要からアクションを実行することで、通常、ワークフローの管理セクションから実行するタスクを、すばやく完了できます。

[Image: ワークフロー管理セクションの更新のレビュー。]

### ワークフローの基本

テンプレートを選んだ後、基本画面で次のようにします。

- ワークフローの説明部分で使われる情報を指定します。
- 実行条件が発生するタイミングを定義するトリガーを選択します。

[Image: ワークフローの基本。]

### 実行条件

ワークフローの基本画面では、ワークフローの実行条件に関する最初の詳細 (トリガー) を設定することもできます。 ワークフローの実行条件では、ワークフローを、いつ、誰に対して実行するかを定義します。 それは、トリガーとスコープと呼ばれる 2 つの異なる部分で構成されます。

### トリガーの詳細

ワークフローのトリガーは、ワークフローのスコープ内のユーザーに対してスケジュールされたワークフローが、いつ実行されるかを定義します。 ワークフローのトリガーは、実行するワークフローの種類によって異なります。

次に示すスケジュール設定されたトリガーがサポートされています。

- 属性の変更
- グループ メンバーシップの変更
- 時間ベース
- サインインの無操作状態

選択するワークフローの種類によって、使用するトリガーが決まります。

Important

相対時間ベースの比較では、時間ベースの属性トリガーが拡張され、パブリック プレビュー段階にあります。 パブリック プレビュー中、Microsoft Entra 管理センターでは、**時間ベースの属性**と**時間ベースの属性 V2 (プレビュー)** が個別の選択肢として一時的に表示されます。 どちらの選択も同じ時間ベースのトリガー機能を表し、V2 の選択では標準の時間ベースの動作を再現することもできます。 一般提供では、相対的な比較を含む時間ベースのトリガーの選択肢が 1 つだけ表示されます。 サポートされている比較と構成の詳細については、「 [時間ベースの属性トリガー](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-execution-conditions#time-based-attribute-trigger)」を参照してください。 プレビューの詳細については、「 [オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)」を参照してください。

### Scope

スコープは、スケジュールされたワークフローが誰に対して実行されるかを定義します。 このパラメーターを構成することで、ワークフローが実行されるユーザーをさらに絞り込むことができます。 ライフサイクル ワークフローでは、スコープを構成するための[豊富なユーザー プロパティ セット](https://learn.microsoft.com/ja-jp/graph/api/resources/identitygovernance-rulebasedsubjectset#supported-user-properties-and-query-parameters)がサポートされます。

スコープは、使用するトリガーによって異なります。

- **属性の変更**の場合、トリガーはルール ベースであり、定義した属性がユーザーに対して変更されるとトリガーされます。
- **グループ メンバーシップの変更**の場合、トリガーはグループ ベースであり、特定のグループのユーザーが追加または削除された場合にトリガーされます。
- **時間ベースの属性**の場合、トリガーはルールベースであり、ユーザーの日付属性が構成されたポイントインタイムまたは相対比較を満たす場合にトリガーされます。
- **サインイン非アクティブ**の場合、トリガーはルールベースであり、ユーザーが特定の期間サインインしていない場合にトリガーされます。

ワークフローの実行条件設定の詳細なガイドについては、「[ライフサイクル ワークフローを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/create-lifecycle-workflow)」を参照してください。

### スケジュール設定

新規作成されたワークフローは既定で有効になりますが、スケジュールは手動で有効にする必要があるオプションです。 ワークフローがスケジュールされているかどうかは、**[スケジュール設定]** 列で確認できます。

スケジュールが有効になると、ワークフローはワークフロー設定内で設定された間隔 (既定値は 3 時間) に基づいて評価され、実行条件に基づいて実行する必要があるかどうかを判断します。

[Image: ワークフロー テンプレートのスケジュール。]

注

標準の時間ベースの属性の選択は、ワークフロー構成 (ユーザーの採用日の 7 日前など) とユーザー アカウントの構成に基づいて、特定の時点でユーザーを処理します。 信頼性の高いワークフロー実行を実現するには、スケジュールされたワークフロー実行の前に、関連するすべての詳細 (トリガー属性やスコープ属性など) を使用してユーザー アカウントを構成する必要があります。 指定された時刻が到着すると、スケジュールされた次のワークフロー実行は、実行条件を満たしている場合にユーザーを処理します。 ワークフローまたはユーザー アカウントが意図した処理時間 (たとえば、HR システムの遅延など) の後に構成されている場合、ライフサイクル ワークフローは、元の処理時間から 3 日以内に必要なセットアップが完了した場合でも、ユーザーの処理を試みます。 パブリック プレビュー中に **時間ベースの属性 V2 (プレビュー)** を選択した場合、このキャッチアップ動作は適用されません。

ワークフローのスケジュールのカスタマイズに関する詳細なガイドについては、「[ワークフローのスケジュールをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/id-governance/customize-workflow-schedule)」をご覧ください。

#### オンデマンド スケジュール

ワークフローは、テストのため、または必要に応じて、オンデマンドで実行できます。

ワークフローをすぐに実行するには、**[オンデマンドで実行]** 機能を使います。 ワークフローは、オンデマンドで実行する前に有効にする必要があります。

注

任意のユーザーに対してオンデマンドで実行されるワークフローでは、ユーザーがワークフローの実行条件を満たしているかどうかは考慮されません。 ユーザーが実行条件を満たしているかどうかに関係なく、タスクが適用されます。

詳しくは、「[ワークフローをオンデマンドで実行する](https://learn.microsoft.com/ja-jp/entra/id-governance/on-demand-workflow)」を参照してください

### 履歴

ワークフローを選ぶと、ユーザー、実行、タスクのレンズを通じて履歴情報を見ることができます。 これらの観点から情報を見ることで、ワークフローがどのように処理されたかに関して、具体的な情報をすばやく絞り込むことができます。

詳細については、「[ライフサイクル ワークフローの履歴](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-history)」を参照してください

### バージョン管理

ワークフローのバージョンは、元のワークフローと同じ情報を使って構築された別のワークフローですが、タスクまたはスコープが更新されているため、ログ内で異なって報告されます。 ワークフローのバージョンでは、既存のワークフローのアクションやスコープを変更できます。

[Image: ワークフローのバージョン管理の選択を管理します。]

詳しくは、「[ライフサイクル ワークフローのバージョン管理](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-versioning)」に関する記事を参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/using-multi-stage-reviews"} -->
## 複数ステージのレビューを使って構成証明と認定のニーズを満たす - Microsoft Entra - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/using-multi-stage-reviews
- Service: entra-id-governance / access-reviews
- Article date: 2024-07-15
- Summary: 複数ステージのレビューを使って、Microsoft Entra でより効率的なレビューを設計する方法について説明します。

Microsoft Entra アクセス レビューは、最大 3 つのレビュー ステージをサポートし、複数の種類のレビュー担当者が、まだ会社のリソースにアクセスする必要があるユーザーの決定に取り組みます。 これらのレビューは、グループやチームのメンバーシップ、アプリケーションへのアクセス、特権ロールへの割り当て、またはアクセス パッケージの割り当てのために行われる可能性があります。 レビュー管理者が決定を自動的に適用するようにレビューを構成した場合、レビュー期間の終了時に、拒否されたユーザーのアクセスは取り消されます。

### 複数ステージのレビューのユース ケース

複数ステージのアクセス レビューにより、複数のレビュー担当者が特定の順序でユーザーのアクセスを証明することが求められる再認定や監査要件を満たす複雑なワークフローを、個人や組織で実現することができます。 また、各レビュー担当者が責任を負う決定事項の数を減らすことで、リソース所有者や監査担当者がより効率的なレビューを設計するのにも役立ちます。 このアプローチでは、同じリソースに対して、本来なら別々に行われるレビューを、1 つのアクセス レビューにまとめることができます。

[Image: 複数ステージのレビューを構成するための管理者エクスペリエンスのスクリーンショット。]

考慮する必要があるいくつかのシナリオを次に示します。

- **複数のレビュー担当者のセットで合意を得る:** 2 人のレビュー担当者の対象ユーザーがリソースへのアクセスを独立してレビューできるようにします。 レビュー担当者の両方のステージで、互いの決定を見ずに "承認済み" で同意する必要があるようにレビューを構成できます。
- **未レビューの決定に介入する、別のレビュー担当者を割り当てる:** まず、リソース所有者にリソースへのアクセスを証明させます。 次に、決定が記録されていないユーザーは、ユーザーのマネージャーや監査チームなどの第 2 ステージのレビュー担当者に送られ、レビュー担当者が未決定の要求をレビューします。
- **後のステージのレビュー担当者の負担を軽減する:** 前のステージで却下されたユーザーは後のステージではレビューされないように構成でき、後のステージのレビュー担当者に絞り込んだ一覧を表示するようにできます。 このシナリオを使用して、ステージごとにレビュー対象のユーザーをフィルターで絞り込みます。

### 複数のレビュー担当者のセットで合意を得る

ユーザーへの適切なアクセスについて、定足数に達することは困難な場合があります。 多くのユーザーがアクセスできるリソースや、レビューが必要なさまざまなユーザー グループでは、1 人のレビュー担当者がすべてのレビュー対象者に対して正しい選択をすることは特に困難です。 最大 3 つの異なるレビュー担当者グループに決定を記録する機会を与え、前のレビュー担当者の対象ユーザーが言ったことを示すことによって合意を得ることは、どのユーザーがリソースにアクセスする必要があるかに関する合意を促進するのに役立ちます。

たとえば、3 つのステージからなるレビューで、リソースへのアクセスを管理するグループへのメンバーシップを決定する場合です。 レビューの設定で、管理者は前のステージのレビュー担当者の決定を表示しないことを選びます。 この構成により、たとえばユーザーのマネージャー、グループ所有者、セキュリティ担当者など、あらゆるレビュー対象者が独立してアクセスをレビューできるようになります。 3 つのステージでは、順にレビュー担当者の対象ユーザーの重みの重要性が高まるようになっていて、最後のレビュー担当者の決定によって、以前のステージのレビュー担当者の決定が上書きされる可能性があります。

このシナリオの構成は、次のようになります。

| 属性 | 構成 |
| --- | --- |
| 複数ステージのレビュー | 有効化済み |
| 第 1 ステージのレビュー担当者 | [ユーザーの管理者] |
| 第 2 ステージのレビュー担当者 | グループ所有者 |
| 第 3 ステージのレビュー担当者 | ユーザーまたはグループの選択 - "Contoso 監査グループ" |
| 後のレビュー担当者に前のステージの決定を表示する | 有効化済み |
| 次のステージに進むレビュー対象者 | すべて選択する |
| レビュー担当者が応答しない場合 | アクセス権の削除 |

### 未レビューの決定に介入する、別のレビュー担当者を割り当てる

決定事項を記録する必要があり、適切なユーザーに対してアクセスを保持する必要があるシナリオでは、複数ステージのレビューを行うと、レビュー対象者のサブセットを次のステージに進めることができ、二重チェックまたは意思決定のために 2 番目のレビュー担当者の対象ユーザーが必要になる可能性があります。 このパターンを使用して、レビュー対象者を別のステージに進め、別のレビュー担当者グループに意思決定させることで、未レビューのユーザーまたは**不明**とマークされたユーザーの数を減らすことができます。

例として、アプリケーションへのアクセスを決定する 2 つのステージを含むレビューがあります。 レビュー設定で、レビュー管理者は **[前のステージの決定を後のステージのレビュー担当者に表示する]** を選びます。 **次のステージに進むレビュー対象者**に対して、確認が必要な決定を追加します。すべてのレビュー対象者に決定が行われるように、**'不明' とマークされたレビュー対象者**と**未レビューのレビュー対象者**を選び、後のステージのレビュー担当者に、未定または不明のレビュー対象者だけを表示して正しいアクセスを保持するように設定します。

| 属性 | 構成 |
| --- | --- |
| 複数ステージのレビュー | 有効化済み |
| 第 1 ステージのレビュー担当者 | ユーザーまたはグループを選択 - アプリケーション所有者 |
| 第 2 ステージのレビュー担当者 | [ユーザーの管理者] |
| 後のレビュー担当者に前のステージの決定を表示する | 無効 |
| 次のステージに進むレビュー対象者 | **未レビューのレビュー対象者**と **'不明' とマークされたレビュー対象者**を選びます |
| レビュー担当者が応答しない場合 | アクセスを承認する |

### 後のステージのレビュー担当者の負担を軽減する

多くのレビュー対象者を含む、またはレビューと証明の対象となるユーザーを含むレビューでは、リソース所有者やそのマネージャーが後のステージで確認する前に、すべてのエンド ユーザーに自己証明を求めることができます。 このモデルでは、ステージごとにレビュー対象者をフィルター処理し、自己承認したレビュー対象者のみを進行させることができます。

ユーザーのマネージャーやリソース所有者など、後のステージのレビュー担当者は、前回承認されたレビュー担当者など、少なくなったレビュー対象者一覧だけが表示されます。 ステージごとのレビュー対象者の数は、ステージごとに減少します。 3 つのステージすべてで承認されたユーザーだけが、アクセスを維持します。

たとえば、管理者が定期的に確認したい IT 例外を許可しているグループのレビューがあります。 その例外は一般的であるので、まずユーザーに回答を求め、それでも例外が必要だと回答したユーザーだけが、マネージャーが決定する 2 番目のステージに進みます。 ユーザーとマネージャーが承認した場合のみ、その例外の IT 所有者は、まだ例外が必要なユーザーと例外を希望するユーザーの一覧を見ることができ、レビュー対象者の一覧を減らして表示します。

| 属性 | 構成 |
| --- | --- |
| 複数ステージのレビュー | 有効化済み |
| 第 1 ステージのレビュー担当者 | [ユーザーによる自分のアクセスのレビュー] |
| 第 2 ステージのレビュー担当者 | [ユーザーの管理者] |
| 第 3 ステージのレビュー担当者 | グループ所有者 |
| 後のレビュー担当者に前のステージの決定を表示する | 無効 |
| 次のステージに進むレビュー対象者 | **承認されたレビュー対象者**を選ぶ |
| レビュー担当者が応答しない場合 | アクセス権の削除 |

[Image: 複数ステージのレビューのスクリーンショット。]

### ゲスト ユーザーのレビュー

ゲスト ユーザーのレビューは、コラボレーションに Microsoft Entra B2B を使用する組織に役立ちます。 これらのゲスト ユーザーのアクセスを定期的に確認して、ゲスト ユーザーに適切なアクセス権があるかどうか、およびコラボレーションがまだ必要かどうかを確認し、不要になったアクセス権の取り消しやゲスト ユーザー アカウントのクリーンアップを行うことができます。

このシナリオは、"後のステージのレビュー担当者の負担を軽減する" シナリオと同様に、複数ステージのレビューを使用して構成できます。 まず、ゲスト ユーザーに自己レビューを依頼し、業務上の正当な理由を提供する要件を含め、継続的な関心とコラボレーションの必要性を証明します。 自己承認だけを行ったゲストは後のステージに進み、そこでスポンサーまたはその他従業員が継続的なアクセスやコラボレーションを承認または拒否します。

ゲスト ユーザーのレビューの場合は、**[非アクティブ ユーザー (テナント レベル) のみ]** の設定を利用することも検討してください。 これにより、指定した日数の間リソース テナントにサインインしていない非アクティブな外部ユーザーにレビューのスコープが設定されます。

ゲスト ユーザーのシナリオでは、アクセス レビューにおいて **[拒否されたゲスト ユーザーに適用するアクション]** という追加の構成オプションがサポートされています。その結果、次のいずれかが行われる場合があります。

- ユーザーのメンバーシップをリソースから削除する
- ユーザーのサインインを 30 日間ブロックした後、テナントからユーザーを削除する

レビューのニーズに応じて、レビュー要求に対応しないゲスト ユーザーや、それ以上のコラボレーションを拒否するゲスト ユーザーを、テナントから自動的に削除できます。 これにより、アカウントの削除後、B2B ゲスト ユーザーのアカウントが 30 日間ブロックされます。

| 属性 | 構成 |
| --- | --- |
| 非アクティブなユーザー (テナント レベル) のみ | 180 日 |
| 複数ステージのレビュー | 有効化済み |
| 第 1 ステージのレビュー担当者 | [ユーザーによる自分のアクセスのレビュー] |
| 第 2 ステージのレビュー担当者 | グループ所有者 |
| 後のレビュー担当者に前のステージの決定を表示する | 無効 |
| 次のステージに進むレビュー対象者 | **承認されたレビュー対象者**を選ぶ |
| レビュー担当者が応答しない場合 | アクセス権の削除 |
| 拒否されたゲスト ユーザーに適用するアクション | ユーザーのサインインを 30 日間ブロックした後、テナントからユーザーを削除する |

注意

**[拒否されたゲスト ユーザーに適用するアクション]** 設定は、アクセス レビュー スコープが **[ゲスト ユーザーのみ]** として構成されている場合にのみ、レビュー作成プロセスで表示されます。

### レビュー ステージの期間

レビュー管理者は、各レビュー ステージの期間を定義し、したがって、そのステージのレビュー担当者が決定を記録しなければならない時間を定義します。 各ステージは、レビュー担当者の都合や見込みに応じて、独自の期間を持つように構成できます。

[Image: 複数ステージのレビュー使用のスクリーンショット。]

各レビュー ステージは、期間中、レビュー担当者が決定を追加できるようにオープンのままになります。 レビュー管理者は、レビュー担当者の概要ページで **[現在のステージの停止]** を選ぶことで、実行中のステージを停止し、レビュー全体を次のレビュー ステージに自動的に進行させることができます。

### 結果の適用

Microsoft Entra アクセス レビューは、不要になったユーザーをリソースから削除することで、リソースへのアクセスに関する決定を適用できます。 決定は、レビュー期間の終了時またはレビュー管理者がレビューを手動で終了したときに常に適用されます。 結果の自動適用は、レビュー管理者が **[リソースに結果を自動適用する] ** 設定で定義するか、レビューの概要ページの **[結果の適用]** ボタンで手動で行います。

決定事項はステージごとにレビュー担当者が収集します。 **次のステージに進むレビュー対象者**の設定は、どのレビュー対象者が後のステージのレビュー担当者に表示され、決定を記録するように要求されるかを定義しています。 全体のレビューが終了した時点で、リソースに決定が適用されます。

すべての決定について、あるレビュー対象者に対して最後に記録された決定が、レビューの終了時に適用されます。 レビューの最初のステージで Jane に対して行われた決定は、ステージ 2 とステージ 3 において、後のステージのレビュー担当者によって上書きされることがあります。

レビュー対象者の一部のみが後のステージに進むように**次のステージに進むレビュー対象者**設定が設定されている場合、最初のステージで行われた決定がレビューの最後に適用されることがあります。 レビュー管理者が 3 ステージのレビューを構成し、**拒否**と**未レビュー**のレビュー対象者だけを次のステージに進行させたい場合、Jane が最初のステージで承認されると、後のステージに進行せず、**承認**の決定が記録され、レビューの最後の時点で適用されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/what-are-lifecycle-workflows"} -->
## ライフサイクル ワークフローとは - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: Microsoft Entra IDのライフサイクル ワークフロー機能の概要について説明します。

ライフサイクル ワークフロー (LCW) は、組織が組織とのユーザーのライフサイクルの 3 つのフェーズにわたってMicrosoft Entraユーザーを管理できるようにする ID ガバナンス機能です。

- **加入者**: 個人がアクセスを必要とする範囲に入るとき。 たとえば、会社や組織に参加する新入社員です。
- **異動者**: 個人が組織内の境界間を移動するとき。 この移動には、より多くのアクセスまたは認可が必要になる場合があります。 たとえば、マーケティングに所属していたユーザーが、営業組織のメンバーになった場合などです。
- **離脱者**: 個人がアクセスが必要な範囲から外れるとき。 この移動には、アクセスの削除が必要になる場合があります。 たとえば、退職する従業員や解雇された従業員などです。

ワークフローには、ライフサイクルの遷移に合わせてユーザーに対して自動的に実行される特定のプロセスが含まれています。 ワークフローは、[タスク](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks)と[実行条件](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows#understanding-lifecycle-workflows)で構成されています。

タスクは、ワークフローがトリガーされたときに自動的に実行される特定のアクションです。 実行条件には、誰が影響を受けるかのスコープと、いつワークフローを実行するかのトリガーを定義します。 たとえば、新しい従業員の `employeeHireDate` 属性値の 7 日前にマネージャーにメールを送信することをワークフローとして記述できます。 構成は次のとおりです。

- タスク: メールを送信する。
- 誰が（対象）: 新入社員。
- いつ (トリガー): `employeeHireDate` 属性値の 7 日前。

自動ワークフローでは、ユーザー属性に基づいて[トリガー](https://learn.microsoft.com/ja-jp/entra/id-governance/understanding-lifecycle-workflows#trigger-details)をスケジュールします。 自動ワークフローのスコープを設定するには、ユーザーが所属する部署など、さまざまなユーザーおよび拡張属性を使うことができます。

ライフサイクル ワークフローによって、既存のロジック アプリを使ってより複雑なシナリオ用に[ワークフローを拡張するために、ロジック アプリ タスク機能と統合する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)こともできます。

[Image: ライフサイクル ワークフローの図。]

### ライフサイクル ワークフローを使う理由

従業員の ID ライフサイクル管理プロセスをモダン化するには、次のことが確実に行われるようにする必要があります。

- ユーザーが組織に参加するとき、初日からすぐに業務を開始できる状態になっている。 必要な情報、グループのメンバーシップ、アプリケーションに対する適切なアクセス権が付与されている。
- さまざまな理由 (退職、離脱、休職、引退) で会社から離れたユーザーに対して、迅速にアクセスを取り消す。
- アクセスを付与したり取り消したりするプロセスが、管理者にとって過度の負担や時間がかからない。
- 管理者とその他の許可されているユーザーは問題のトラブルシューティングを簡単に行うことができ、ログがトラブルシューティング、監査、コンプライアンスに十分に役立つ。

ライフサイクル ワークフローを使用する主な理由は次のとおりです。

- タスクを簡素化および自動化する他のワークフローにより、人事主導のプロビジョニング プロセスを拡張します。
- ワークフロー プロセスを一元化して、ワークフローの作成と管理を 1 か所で簡単に行うことができるようにします。
- ワークフローの履歴と監査ログを使って、ワークフローのシナリオのトラブルシューティングを簡単に行います。
- 大規模なユーザー ライフサイクルを管理します。 組織が成長しても、ユーザーのライフサイクルの管理に必要な他のリソースは減ります。
- 手動タスクを減らすか排除します。
- ロジック アプリを適用し、既存のロジック アプリを使ってワークフローをさらに複雑なシナリオ用に拡張します。
- [Microsoft Security Copilotを使用して、自然言語を使用してライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-lifecycle-workflows)を作成および管理します。

これらの機能により、他の依存関係やアプリケーションを削除しても同じ結果を実現し、包括的なエクスペリエンスを確保できます。 その後、新入社員向けのオリエンテーションの際や、元従業員をシステムから削除する際の効率を高めることができます。

### ライフサイクル ワークフローを使うタイミング

ライフサイクル ワークフローは、次のような条件に対応するために使用できます。

- **ユーザーのオリエンテーションと人事プロビジョニングの自動化と拡張**: 一時パスワードの生成やマネージャーへのメール送信などのタスクを自動化することで人事プロビジョニング シナリオを拡張する必要があるときに、ライフサイクル ワークフローを使います。 現在、オリエンテーションに手動プロセスを使っている場合は、自動化プロセスの一部としてライフサイクル ワークフローを使います。
- **グループ メンバーシップの自動化**: 組織内のグループが明確に定義されている場合は、これらのグループのユーザー メンバーシップを自動化することができます。 その利点と、動的グループとの相違点には次のようなものがあります。
    - ライフサイクル ワークフローでは静的グループを管理し、動的グループ ルールは必要ありません。
    - グループごとに 1 つのルールを使用する必要はありません。 ライフサイクル ワークフロー ルールは、グループではなく、ワークフローを実行する対象となるユーザーのスコープを決定します。
    - ライフサイクル ワークフローは、動的グループでサポートされている属性を超えてユーザーのライフサイクルを管理するのに役立ちます。たとえば、`employeeHireDate` 属性値までの特定の日数などです。
    - ライフサイクル ワークフローを使うと、メンバーシップだけでなく、グループに対してアクションを実行できます。
- **ワークフローの履歴と監査**: ライフサイクル ワークフローは、ユーザーのライフサイクル プロセスの監査証跡を作成する必要がある場合に使います。 Microsoft Entra 管理センターを使用すると、オリエンテーションと出発のシナリオの履歴と監査を表示できます。
- **ユーザー アカウント管理の自動化**: ID ライフサイクル プロセスの重要な部分は、退職するユーザーのリソースに対するアクセスを確実に取り消すことです。 ライフサイクル ワークフローを使うと、ユーザー アカウントの無効化と削除を自動化できます。
- **アクセス パッケージの割り当ての自動化**: ライフサイクル ワークフローを使用して、ユーザーのアクセス パッケージの割り当てと削除を自動化できます。
- **ロジック アプリとの統合**: ロジック アプリを適用し、ワークフローをさらに複雑なシナリオ用に拡張できます。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

ライフサイクル ワークフローを使うと、次のことができます。

- 合計 100 ワークフローまで作成、管理、削除できます。
- オンデマンドおよびスケジュールされたワークフロー実行をトリガーします。
- 既存のタスクを管理および構成して、ニーズに固有のワークフローを作成します。
- ワークフローで使用するカスタム タスク拡張機能を最大 100 個作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/what-is-provisioning"} -->
## Microsoft Entra ID を使ったプロビジョニングとは - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/what-is-provisioning
- Service: entra-id-governance
- Article date: 2024-12-30
- Summary: ID プロビジョニングと ILM のシナリオについて概要を説明します。

プロビジョニングとプロビジョニング解除は、複数のシステム間でデジタル ID の一貫性を確保するプロセスです。 これらのプロセスは通常、[ID ライフサイクル管理](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-the-employee-lifecycle)の一部として使用されます。

**プロビジョニング**は、特定の条件に基づいて、対象のシステムに ID を作成するプロセスです。 **プロビジョニング解除**は、条件を満たさなくなった ID を対象のシステムから削除するプロセスです。 **同期**は、ソース オブジェクトとターゲット オブジェクトがほぼ一致するように、プロビジョニングされたオブジェクトを最新の状態に維持するプロセスです。

たとえば、新しい従業員が組織に加わると、その従業員は人事システムに入力されます。 その時点で、人事**から** Microsoft Entra ID **への**プロビジョニングを行うと、Microsoft Entra ID の対応するユーザー アカウントを作成できます。 その新しい従業員のアカウントは、Microsoft Entra ID に対してクエリを実行するアプリケーションで確認できます。 Microsoft Entra ID を使用しないアプリケーションがある場合は、 Microsoft Entra ID から をプロビジョニングして、それらのアプリケーションのデータベースを すると、ユーザーがアクセスする必要があるすべてのアプリケーションにユーザーがアクセスできるようになります。 このプロセスによって、ユーザーは出社したその日から仕事に着手し、必要なアプリケーションやシステムにアクセスすることができます。 同様に、部署や雇用状態などのプロパティが人事システムで変更されると、人事システムから Microsoft Entra ID への更新の同期によって一貫性が確保されます。 さらに、この同期は他のアプリケーションとターゲット データベースにも拡張されます。

プロビジョニングの自動化に関して、Microsoft Entra ID には現在 3 つの領域が用意されています。 以下のとおりです:

- ディレクトリを除く外部の信頼できる記録システムから Microsoft Entra ID へのプロビジョニング (**人事主導のプロビジョニング**を使用)
- Microsoft Entra ID からアプリケーションへのプロビジョニング (**アプリのプロビジョニング**を使用)
- Microsoft Entra ID と Active Directory Domain Services の間のプロビジョニング (**ディレクトリ間のプロビジョニング**を使用)

[Image: ID ライフサイクル管理の図。]

### 人事主導のプロビジョニング

[Image: 人事のプロビジョニングの図。]

人事から Microsoft Entra ID へのプロビジョニングには、人事システム内の情報に基づいてオブジェクトを作成することが含まれます。これは通常、各従業員を表すユーザー ID ですが、部署などの構造を表す別のオブジェクトである場合もあります。

最も一般的なシナリオは、新しい従業員が会社に加わったときの、それらの従業員の人事システムへの入力です。 その場合は、Microsoft Entra ID で新しいユーザーとして自動的にプロビジョニングされるため、新規採用のたびに管理者が関与する必要はありません。 一般に、人事からのプロビジョニングでは、次のシナリオに対応できます。

- **新しい従業員の採用** - 新しい従業員が人事システムに追加された場合は、ユーザー アカウントが Active Directory、Microsoft Entra ID のほか、必要に応じて Microsoft Entra ID でサポートされているプリケーションのディレクトリ内に自動的に作成され、電子メール アドレスが人事システムに書き戻されます。
- **従業員属性とプロファイルの更新** - その人事システムで従業員レコード (名前、タイトル、マネージャーなど) が更新されると、ユーザー アカウントは Active Directory、Microsoft Entra ID、および必要に応じて Microsoft Entra ID でサポートされるその他のアプリケーションで自動的に更新されます。
- **従業員の退職** - 人事システムで従業員が退職状態になると自動的に、ユーザー アカウントがサインインできないようブロックされるか、Active Directory と Microsoft Entra ID、およびその他のアプリケーションから削除されます。
- **従業員の再雇用** - 従業員がクラウド人事システムで再雇用された場合は、その以前のアカウントを自動的に再アクティブ化または (設定に応じて) 再プロビジョニングできます。

Microsoft Entra ID を使用した人事主導のプロビジョニングには、3 つのデプロイ方法があります。

1. Workday または SuccessFactors の単一サブスクリプションを所有していて、Active Directory を使用しない組織向け
2. Workday または SuccessFactors の単一サブスクリプションを所有していて、Active Directory と Microsoft Entra ID の両方を使用する組織向け
3. 複数の人事システムを持つ組織、または SAP HCM、Oracle E-Business Suite、PeopleSoft などのオンプレミスの人事システムの場合

詳細については、「[人事主導のプロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)」を参照してください。

### アプリのプロビジョニング

[Image: アプリのプロビジョニングのフローを示す図。]

Microsoft Entra ID における**[アプリのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)**という用語は、Microsoft Entra ID や Active Directory とは異なる独自のデータ ストアを有するアプリケーションに関して、ユーザーがアクセスする必要のあるアプリケーションに、ユーザー ID のコピーを自動的に作成することを意味します。 アプリのプロビジョニングには、ユーザー ID の作成に加えて、ユーザーの状態または役割が変化したときに、ユーザー ID をメンテナンスしたりそれらのアプリから削除したりすることも含まれます。 一般的なシナリオとしては、[Dropbox](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dropboxforbusiness-provisioning-tutorial)、[Salesforce](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-provisioning-tutorial)、[ServiceNow](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-provisioning-tutorial) といったアプリケーションへの Microsoft Entra ユーザーのプロビジョニングが挙げられます。これらのアプリケーションにはいずれも、Microsoft Entra ID とは異なる独自のユーザー リポジトリがあります。

また、Microsoft Entra ID では、ファイアウォールを開くことなく、オンプレミスまたは仮想マシンでホストされているアプリケーションへのユーザーのプロビジョニングがサポートされています。 アプリケーションで [SCIM](https://aka.ms/scimoverview) がサポートされている場合、またはレガシ アプリケーションに接続するために SCIM ゲートウェイを構築済みの場合は、Microsoft Entra プロビジョニング エージェントを使用してアプリケーションに[直接接続](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)し、プロビジョニングとプロビジョニング解除を自動化できます。 SCIM をサポートしておらず、[LDAP](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure) ユーザー ストアまたは [SQL](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure) データベースに依存しているレガシ アプリケーションや、[SOAP または REST API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector) を持つレガシアプリケーションがある場合、Microsoft Entra ID ではそれらもサポートできます。

詳細については、「[アプリのプロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### ディレクトリ間のプロビジョニング

[Image: ディレクトリ間のプロビジョニングを示す図]

多くの組織は Active Directory と Microsoft Entra ID の両方を利用していて、なおかつ、Active Directory に接続されたアプリケーション (オンプレミスのファイル サーバーなど) を所有している場合があります。

これまで、多くの組織では人事主導のプロビジョニングをオンプレミスでデプロイしているため、すべての従業員のユーザー ID が Active Directory に既に存在する可能性があります。 ディレクトリ間のプロビジョニングの最も一般的なシナリオは、Active Directory に既に存在するユーザーを Microsoft Entra ID にプロビジョニングすることです。 このプロビジョニングは、通常、Microsoft Entra Connect 同期または Microsoft Entra Connect クラウド プロビジョニングによって実現されます。

また、組織が Microsoft Entra ID からオンプレミスのシステムへのプロビジョニングを希望する場合もあります。 たとえば、組織は Microsoft Entra ディレクトリにゲストを含めることができますが、それらのゲストは、アプリ プロキシ経由でオンプレミスの Windows 統合認証 (WIA) ベースの Web アプリケーションにアクセスする必要があります。 このシナリオでは、Microsoft Entra ID 内のそれらのユーザーのために、オンプレミスの AD アカウントをプロビジョニングする必要があります。

詳細については、「[ディレクトリ間のプロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-inter-directory-provisioning)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/workflow-custom-triggers"} -->
## ライフサイクル ワークフローでカスタム属性トリガーを使用する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/workflow-custom-triggers
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: この記事では、ライフサイクル ワークフローのワークフロー内でカスタム属性トリガーを属性変更トリガーとして使用する方法について説明します。

ライフサイクル ワークフローを使用すると、ワークフローの実行条件を満たすユーザーに対してワークフローを自動的に実行できます。 ワークフローのトリガーに使用できる既定の属性は多数ありますが、既定では提供されていない特定の属性に基づいてワークフローをトリガーする必要がある場合があります。 カスタム属性トリガーを使用すると、ユーザーが次の条件に基づいて組織内で移動するタイミングに基づいて、ユーザーに対して実行するワークフローをトリガーできます。

- [カスタム セキュリティ属性 (CSA)](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-workflow-custom-security-attribute)
- ディレクトリ拡張属性
- オンプレミス拡張機能の属性 (1 から 15)
- EmployeeOrgData 属性

### [前提条件]

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### Microsoft Entra 管理センターを使用して新しいワークフローでカスタム属性トリガーを使用する

新しいワークフローでカスタム属性トリガーを使用するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)および[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフ サイクル ワークフロー**&gt;**ワークフローの作成**に移動します。
3. [ワークフロー] ページで、スコープの一部としてカスタム セキュリティ属性を使用するワークフロー テンプレートを選択します。
4. 表示名、説明、管理スコープなどの基本情報を入力します。
5. [ **トリガーの種類** ] で[ **属性の変更]** を選択します。
6. [属性] で、実行するワークフローをトリガーする属性トリガーを選択します。
7. ワークフローの構成を完了し、保存します。

    注

    属性の変更は、スケジュールされたワークフローでのみ検出されます。

### Microsoft Entra 管理センターを使用して既存のワークフロー トリガーにカスタム属性を追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)および[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**のワークフローを参照してください**。
3. トリガーにカスタム属性を追加するワークフローを選択します。
4. [ワークフローの概要] ページで、[実行条件 選択します。
5. [ **トリガーの詳細**] で、ワークフローのトリガーに使用するカスタム属性でトリガーを更新します。
6. **保存** を選択します。

### カスタム属性トリガーに関する考慮事項

現在、属性の変更を取得し、ワークフローの実行をスケジュールするには、ワークフローとワークフロー スケジュールを有効にする必要があります。 ライフサイクル ワークフローが属性の変更のチェックを開始すると、これらのカスタム属性に対して変更の取得にかかる時間が遅れる可能性があります。 変更は数分以内に取得する必要がありますが、ユーザー属性の変更後にさらに遅延を追加するアップストリーム プロセスがあります。次に例を示します。

- カスタム属性の変更が基になるサービスによって更新されるまでに最大 4 時間かかる場合があります。ただし、カスタム属性の変更が反映されると、ライフサイクル ワークフローは数秒以内に変更を取得する必要があります。
- ライフサイクル ワークフローによって変更が取得されると、ワークフロースコープを満たすユーザーのスケジュールに従って、次のターゲット実行でワークフローの実行が行われます。

### 属性とカスタム属性の処理のタイミング

次の図は、ワークフロー トリガーで通常の属性とカスタム属性を使用する場合の処理時間の潜在的な違いを示しています。

[Image: ワークフロー実行での通常の属性処理タイミングとカスタム属性処理タイミングの比較のスクリーンショット。]

例 A では、部門属性が変更されたときにワークフローが実行されるようにスケジュールされ、午後 12 時、次のターゲット実行は午後 1 時と午後 2 時に実行されます。

- 午後 12 時 10 分に、ユーザー部門が変更されます
- 午後 12 時 15 分に、ユーザーがワークフローのスコープ内にあると検出されます
- 午後 1 時の実行では、ユーザーはワークフローによって処理されます

例 B では、TestCustomSecurityAttribute1 属性が変更されたときにワークフローが実行されるようにスケジュールされ、午後 12 時、次のターゲット実行は午後 1 時と午後 2 時に実行されます。

- 午後 12 時 10 分に、ユーザーの TestCustomSecurityAttribute1 属性が変更されます
- 午後 3 時 55 分に変更がライフサイクル ワークフローに渡されます
- 午後 4 時に、ユーザーはワークフローのスコープ内にあることが検出されます (午後 4 時の実行には遅すぎます)。
- 午後 5 時の実行では、ユーザーはワークフローによって処理されます

ライフサイクル ワークフロー内でのカスタム属性トリガーの使用に関してよく寄せられる質問については、「 [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/workflows-faqs)に関する FAQ」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/workflow-sensitivity-labels"} -->
## ライフサイクル ワークフローの感度ラベル - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/workflow-sensitivity-labels
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: この記事では、ワークフローの秘密度ラベルと、タスクの作成プロセス中にそれらを表示する方法について説明します。

環境内のデータの保守と分類は、セキュリティで保護された環境を維持する上で重要な部分です。 Microsoft Purview Information Protection の秘密度ラベルを使用すると、組織のデータを分類および保護しながら、ユーザーの生産性と共同作業を行う能力が損なわれないようにすることができます。 ライフサイクル ワークフローの秘密度ラベルを使用すると、管理者はワークフローの作成時および編集中にグループとチームの秘密度ラベルをすばやく表示できます。

次のタスクでは、秘密度ラベルの表示がサポートされています。

- [ユーザーをグループに追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#add-user-to-groups)
- [ユーザーを Teams に追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#add-user-to-teams)
- [選択したグループからユーザーを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-user-from-selected-groups)
- [選択したチームからユーザーを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-user-from-selected-teams)

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### [前提条件]

ライフサイクル ワークフローに必要な Microsoft Entra ライセンスと共に、以下も必要です。

- [作成されたセンシティビティラベル](https://learn.microsoft.com/ja-jp/purview/create-sensitivity-labels?tabs=classic-label-scheme#create-and-configure-sensitivity-labels)
- [ライフサイクル ワークフローで使用するグループまたはチームに適用される秘密度ラベル](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels-teams-groups-sites#using-sensitivity-labels-for-microsoft-teams-microsoft-365-groups-and-sharepoint-sites)

### ワークフローの作成時に割り当てられた秘密度ラベルを表示する

ワークフローの作成時にライフサイクル ワークフローを使用してグループとチームの秘密度ラベルを表示するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフ サイクル ワークフロー**&gt;**ワークフローの作成**に移動します。
3. **[ワークフローの選択]** ページで、使用するワークフロー テンプレートを選択します。
4. ワークフローの基本情報、トリガーの種類、スコープの詳細を追加します。
5. [タスク] ページで、秘密度ラベルの表示に使用するタスクを追加します。 タスクの可用性は、ワークフローを作成するために選択したテンプレートに基づいています。 ワークフロー テンプレートの詳細については、「 [ライフサイクル ワークフローのテンプレートとカテゴリ](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates)」を参照してください。
6. グループ関連のタスクをワークフローに追加した後、タスクを選択し、[グループの **選択**] を選択します。 [Image: ワークフローでグループを選択するスクリーンショット。]
7. リスト ウィンドウでは、選択できるグループまたはチームの一覧と、その秘密度ラベルを表示できます。 [Image: 秘密度ラベルと共にワークフローにグループを追加するスクリーンショット。]
8. グループまたはチームをタスクに追加した後、[ **次へ** ] を選択してレビュー画面に移動し、[ **作成]** を選択してワークフローを作成します。

### 既存のワークフロー タスクに割り当てられた秘密度ラベルを表示する

ワークフローの既存のタスク内で使用されるグループとチームの秘密度ラベルは、次の手順を実行して表示できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**のワークフローを参照してください**。
3. 表示するタスクを含むワークフローを選択します。
4. ワークフローの概要画面で、[タスク] を選択 **します**。
5. 感度に関連する特定のタスクを表示するには、タスク画面でそれを選択します。
6. タスクの概要画面で、グループまたはチームの選択オプションを選択します。
7. リスト画面では、現在タスクに割り当てられているグループ、タスクに追加できるグループまたはチームの一覧、および秘密度ラベルを確認できます。 [Image: 既存のワークフローのタスク内のチームと秘密度ラベルのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/workflows-faqs"} -->
## ライフサイクル ワークフローの FAQ - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/workflows-faqs
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローについてよく寄せられる質問。

この記事では、 [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)に関してよく寄せられる質問に対する回答を紹介します。 このページは変更頻度が高く、継続的に回答が追加されているため、頻繁に参照してください。

### よく寄せられる質問

#### ゲスト用のカスタム ワークフローを作成できますか?

はい。テナント内のメンバーまたはゲスト用にカスタム ワークフローを構成できます。 ワークフローは、すべての種類の外部ゲスト、外部メンバー、内部ゲスト、内部メンバーに対して実行できます。

#### "ライフサイクル ワークフロー" ではなく "ライフサイクル管理" が表示されるのはなぜですか?

ごく一部お客様で、監査ログとエンタープライズ アプリケーションに、ライフサイクル ワークフローが以前の名前のライフサイクル管理で引き続き表示できます。

#### WorkDay などのアプリをプロビジョニングするときに、employeeHireDate をマップする必要がありますか?

はい。employeeHireDate などの主要なユーザー プロパティは、WorkDay などの人事アプリからのユーザー プロビジョニングのためにサポートされています。 これらのプロパティをライフサイクル ワークフローで使うには、値が設定されていることを確認するために、プロビジョニング プロセスでそれらをマップする必要があります。 次のスクリーンショットはマッピングの一例です。

[Image: ライフサイクル ワークフローでマッピングを完了する方法の例を示すスクリーンショット。]

ライフサイクル ワークフローでの employee 属性の同期の詳細については、「[ライフサイクル ワークフローの属性を同期する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes)」を参照してください

#### タスクの詳細とパラメーター、更新対象の属性を確認するにはどうすればよいですか?

タスクによっては、既存の属性が更新されます。ただし、現在はその具体的な内容を公表していません。 このようなタスクは、他の Microsoft Entra 機能に関連する属性を更新するものなので、それらのドキュメントで情報を確認できます。一時的なアクセス パスについては、[こちら](https://learn.microsoft.com/ja-jp/graph/api/resources/temporaryaccesspassauthenticationmethod)に掲載されている適切な属性に説明を加えています。

#### 新しいタスクを作成することはできますか? また、その方法を教えてください。 たとえば、他の Graph API や Webhook をトリガーする場合はどうですか？

現在、タスク テンプレートでサポートされているタスク セット以外に、新しいタスクを作成する機能はサポートしていません。 これを実現する別の方法として、ロジック アプリを設定し、その URL を使ってライフサイクル ワークフローにロジック アプリ タスクを作成することもできます。 詳細については、「[カスタム タスク拡張機能に基づいて Logic Apps をトリガーする](https://learn.microsoft.com/ja-jp/entra/id-governance/trigger-custom-task)」を参照してください。

#### プロパティ一覧にカスタム セキュリティ属性が表示されない理由は何ですか?

非アクティブ化されたカスタム セキュリティ属性はリストに表示されないため、テナントにアクティブなカスタム セキュリティ属性があることを確認します。 また、カスタム セキュリティ属性を表示するには、[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)または[属性割り当て閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader)ロールが必要です。

#### 「このルールには無効なプロパティが含まれています」とはどういう意味ですか。 既存のワークフローのルール式に赤い x アイコンがあるとき、何を意味していますか?

赤いアイコンは、このカスタム セキュリティ属性がアクティブではなくなったため、ルールが無効であることを示します。 ルールが無効であるため、ワークフローは処理されません。 非アクティブ化されたカスタム セキュリティ属性式をワークフローのルールから削除する必要があります。

#### ユーザーがワークフロー トリガーの一部であるカスタム セキュリティ属性を割り当てられましたが、なぜこのユーザーに対してワークフローが実行されなかったのですか?

ライフサイクル ワークフローでは、大文字と小文字の区別など、指定した値に一致するカスタム セキュリティ属性がユーザーに割り当てられていることを確認します。 カスタム セキュリティ属性値が正確に一致していることを確認します。

#### ライフサイクル ワークフローではどのように複数の値を持つカスタム セキュリティ属性を扱うのですか?

複数の値を持つカスタム セキュリティ属性がユーザーに割り当てられ、いずれかの値がルール式で指定された値と一致する場合、ユーザーはスコープに一致します。

#### 属性変更トリガーにカスタム属性が一覧表示されないのはなぜですか?

ドロップダウン リストに予期される属性を表示するには、次のことを確認する必要があります。

- 属性を構成してアクティブにする必要があります
- カスタム セキュリティ属性の場合は、適切なアクセス許可があります

#### カスタム属性トリガーを使用するワークフローは、変更の処理に常に 4 時間かかりますか?

いいえ。変更処理時間は、テナントの変更アクティビティ、タイミング、ボリュームによって異なる場合があり、上限は 4 時間と予想されます。 時間の短縮を期待し、可能な限りタイミングの最適化に取り組んでいます。

#### カスタム属性をトリガーとして使用する複数のワークフローがある場合はどうなりますか?

複数のワークフローがトリガーと同じカスタム属性を使用している場合、その変更が処理されると、それらのワークフローが同時に処理されます。 複数のワークフローが異なるカスタム属性をトリガーとして使用しているが、カスタム属性の更新が同時に発生した場合、それらのワークフローは同時に処理されます。 属性の更新が異なると、ワークフローの処理が異なる場合があります。
<!-- /MSL-PAGE -->
