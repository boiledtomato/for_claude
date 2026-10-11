# Microsoft Learn — Microsoft Entra / ハイブリッド ID (Connect / Cloud Sync) (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 65

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid"} -->
## ハイブリッド ID のドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid
- Service: entra-id
- Article date: 2026-08-10
- Summary: オンプレミスのディレクトリを Microsoft Entra ID と統合します。 これにより、Microsoft Entra ID と統合された Microsoft 365、Azure、SaaS アプリケーションのユーザーに共通の ID を提供できます。

Microsoft の ID ソリューションは、オンプレミスとクラウドベースの機能にまたがり、場所に関係なく、すべてのリソースに対する認証と承認のための単一のユーザー ID を作成します。 このハイブリッド ID と呼びます。

### ハイブリッド ID について

#### 概要

- [ハイブリッド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity)
- [プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning)
- [ディレクトリ間プロビジョニングとは何ですか？](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-inter-directory-provisioning)

### はじめに

#### 作業の開始

- [一般的なシナリオ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)
- [適切な同期クライアントを選択する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)
- [開始する手順](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/get-started)

### デプロイを管理する

#### 作業の開始

- [同期ツールをインストールする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/install)
- [同期を設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/configure)
- [アカウント設定を構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/accounts)
- [同期ツールを移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync)

### ユーザーとグループを管理する

#### 作業の開始

- [必要に応じてユーザーをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/on-demand-provision)
- [インポートのスケジュール設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler)
- [誤って削除されないようにする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/accidental-deletes)

### プロビジョニング - Active Directory から Microsoft Entra ID

#### 作業の開始

- [単一の AD フォレストを 1 つの Microsoft Entra テナントと統合する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest)
- [既存のフォレストと新しいフォレストを 1 つの Microsoft Entra テナントと統合する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-existing-forest)
- [構成 - AD から Microsoft Entra ID への](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)
- [属性マッピング - AD から Microsoft Entra ID への](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-attribute-mapping)
- [ディレクトリ拡張機能とカスタム属性 - AD から Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping)
- [サポートされているトポロジ - AD から Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/plan-cloud-sync-topologies)

### プロビジョニング - Microsoft Entra ID から Active Directory

#### 作業の開始

- [Microsoft Entra Cloud Sync を使用してグループを Active Directory にプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/group-writeback-cloud-sync)
- [Microsoft Entra ID ガバナンスを使用してオンプレミスの Active Directory ベースのアプリ (Kerberos) を管理する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups)
- [サポートされているトポロジ - Microsoft Entra ID から AD](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/plan-cloud-sync-topologies)
- [構成 - Microsoft Entra ID から AD への変換 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)
- [シナリオ - ユーザーとグループを AD にプロビジョニングする (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-users-groups-provisioning-walkthrough)
- [AD へのプロビジョニング時のディレクトリ拡張機能の使用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-directory-extension-group-provisioning)
- [Active Directory へのプロビジョニングに関する FAQ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-provision-to-active-directory-faq)

### 移行

#### 作業の開始

- [Connect 同期から Microsoft Entra Cloud Sync に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync)
- [Microsoft Entra Connect Sync グループ書き戻し V2 を Microsoft Entra Cloud Sync に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-group-writeback)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/accidental-deletes"} -->
## Active Directory を使用して誤削除防止を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/accidental-deletes
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、Active Directory を使用した同期ツールの誤削除防止を構成する方法について説明します。

クラウド同期または Microsoft Entra Connect をインストールする場合、この機能は既定で有効になっており、500 を超える削除を含むエクスポートを許可しないように構成されています。 この機能は、多くのユーザーや他のオブジェクトに影響を与えるオンプレミス ディレクトリに対する誤った構成変更や変更からユーザーを保護するように設計されています。

既定の動作を変更し、組織のニーズに合わせて調整できます。

### クラウド同期を使用して誤削除防止を構成する

この新機能を使用するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. [ **既定のプロパティの表示]** を選択します。
3. [**基本**] の横にある鉛筆をクリックします
4. 右側に次の情報を入力します。
    - **通知メール - 通知** に使用される電子メール
    - **誤って削除されないようにする** - このチェック ボックスをオンにして機能を有効にします
    - **誤削除のしきい値** - 同期を停止して通知を送信するオブジェクトの数を入力します

詳細については、「[クラウド同期による誤削除防止」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-accidental-deletes)を参照してください。

### Microsoft Entra Connect を使用して誤削除防止を構成する

PowerShell では、Microsoft Entra Connect と共にインストールされた `Enable-ADSyncExportDeletionThreshold`の一部である  を使用して、既定値の 500 個のオブジェクトを変更できます。 この値は、組織のサイズに合わせて構成する必要があります。 同期スケジューラは 30 分おきに実行されるので、この値は 30 分以内に確認される削除数になります。

詳細については、「 [Microsoft Entra Connect による誤削除防止](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/accounts"} -->
## Active Directory と統合するためのアカウント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/accounts
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、各同期ツールに必要なアカウントについて説明します。

次の記事では、2 つの同期ツールのそれぞれに必要なアカウントについて説明します。 環境を構成および設定するときに、これらのセクションを参考にしてください。

### クラウド同期をインストールして実行するためのアカウント

| 要件 | 説明とその他の要件 |
| --- | --- |
| ドメインまたはエンタープライズ管理者 | サーバーにエージェントをインストールし、gMSA サービス アカウントを作成するために必要です。 |
| ハイブリッド ID の管理者 | クラウド同期を構成するために必要です。このアカウントをゲスト アカウントにすることはできません。 |
| gMSA サービス アカウント | エージェントを実行するために必要です。 |

クラウド同期アカウントの詳細と、カスタム gMSA アカウントの設定方法については、「[クラウド同期の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites)」を参照してください。

### Microsoft Entra Connect をインストールして実行するためのアカウント

Microsoft Entra Connect では、オンプレミスの Windows Server Active Directory (Windows Server AD) から Microsoft Entra ID に "情報を同期" させるために 3 つのアカウントが使用されます。

| 要件 | 説明と追加の要件 |
| --- | --- |
| AD DS コネクタ アカウント | Active Directory Domain Services (AD DS) を使用した Windows Server AD に対する情報の読み取りと書き込みに使用されます。 |
| ADSync サービス アカウント | 同期サービスの実行と SQL Server データベースへのアクセスに使用されます。 |
| Microsoft Entra Connector アカウント | Microsoft Entra ID への情報の書き込みに使用されます。 |
| ローカル管理者アカウント | Microsoft Entra Connect をインストールしており、コンピューターのローカル管理者権限を持つ管理者。 |
| AD DS エンタープライズ管理者アカウント | 必要に応じて、必要な AD DS コネクタ アカウントを作成するために使用されます。 |
| ハイブリッド ID の管理者 | Microsoft Entra Connector アカウントを作成し、Microsoft Entra ID を構成するために使用されます。 [Microsoft Entra 管理センター](https://entra.microsoft.com)で、ハイブリッド ID 管理者のアカウントを表示できます。 「[Microsoft Entra ロールの割り当てを一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments)」を参照してください。 |
| SQL SA アカウント (任意) | 通常版の SQL Server を使用している場合、ADSync データベースの作成に使用されます。 SQL Server のインスタンスは、Microsoft Entra Connect のインストールに対してローカルでもリモートでもかまいません。 このアカウントは、エンタープライズ管理者アカウントと同じアカウントにすることもできます。 |

Microsoft Entra Connet アカウントの詳細と構成方法については、「[アカウントとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/cloud-sync-migration-faq"} -->
## Microsoft Entra Connect Sync から Cloud Sync への移行に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/cloud-sync-migration-faq
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-05-04
- Summary: Microsoft Entra Connect Sync から Microsoft Entra Cloud Sync への移行についてよく寄せられる質問 (適格性、タイムライン、機能パリティ、移行後の動作など)。

この記事では、Microsoft Entra Connect Sync から Microsoft Entra Cloud Sync への移行に関してよく寄せられる質問に回答します。機能の完全な比較については、[decision ガイド](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide)を参照してください。 詳細な移行手順については、「[Microsoft Entra Connect から Microsoft Entra Cloud Sync への移行](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync)を参照してください。

### 移行の資格とタイムライン

#### Cloud Sync に移行するタイミングを確認するにはどうすればよいですか?

Microsoftは、移行を開始する資格が得られたときに組織に通知します。 適格性は、現在の構成やサポートされているシナリオを含む要因によって決定され、移行は段階的にロールアウトされます。 時間が経ったら、公式のコミュニケーション チャネルを通じて、続行方法に関する明確な指示を受け取ります。

#### 組織が推奨期間内に移行できない場合はどうすればよいでしょうか。

推奨期間内に移行できない場合は、例外を要求する必要があります。 承認された場合は、Microsoftサポートまたはアカウント チームと連携しながら、既存のセットアップを引き続き使用して、適切な移行タイムラインを計画できます。

#### 移行にはどのくらいの時間がかかりますか?

移行時間は、オブジェクトの数と環境の複雑さによって異なります。 期間に影響を与える要因には、ユーザーとグループの数、カスタム同期規則、移行する組織単位 (OU) の数などがあります。

### 移行のプロセス

#### Connect Sync から Cloud Sync に移行するにはどうすればよいですか?

移行を準備するには、次のリソースを確認します。

- [クラウド同期とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)
- [クラウド同期の詳細 - しくみ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-how-it-works)
- [ステップ バイ ステップの移行ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync)

**移行シナリオ:**

- [同期されたActive Directory フォレストのクラウド同期をMicrosoft Entraに移行します](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp)
- [Microsoft Entra Connect Sync グループ書き戻し v2 を Microsoft Entra Cloud Sync に移行します](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-group-writeback)
- [Microsoft Entra Cloud Sync と Microsoft Entra Connect Sync の機能の比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)

#### Connect Sync と Cloud Sync をサイド バイ サイドで実行できますか?

同じオブジェクトに対して Connect Sync と Cloud Sync を並行して実行することはサポートされていません。 移行中は、OU ベースのスコープを使用して、各組織単位が一度に 1 つの同期ツールでのみ管理されるようにします。 この段階的なアプローチでは、追加の OU を移行する前に、ユーザーのサブセットでクラウド同期を検証できます。 この方法の実装の詳細については、 [移行チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp)を参照してください。

#### 完全にロールアウトする前に移行をテストできますか?

Yes. 完全なロールアウトにコミットする前に、Active Directoryテストフォレストを使用して移行を試行できます。 チュートリアル [Microsoft Entra Cloud Sync への移行（同期された Active Directory フォレスト用）](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp)は、サンドボックス移行を段階的に案内します。 オンデマンド プロビジョニングを使用して、構成の変更 [を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision) 1 人のユーザーに適用して検証することもできます。

### 構成とカスタマイズ

#### 移行中に既存の構成は保持されますか?

移行中、Connect Sync はステージング モードになり、完全に切り替える前にクラウド同期を検証できます。 必要に応じて、以前の構成にロールバックできます。 移行する前に、[インポートおよびエクスポート設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-import-export-config)機能を使用して、Microsoft Entra Connect 構成をバックアップします。

#### カスタマイズは自動的に引き継がされますか?

移行ツールは、サポートされている構成を Cloud Sync に転送します。移行後、 [オンデマンド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision) を実行して、最初の運用同期に進む前に、構成が期待どおりに動作していることを検証します。サポートされている機能とカスタマイズの詳細については、 [機能の比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)を参照してください。

### 機能のサポートと互換性

#### クラウド同期で現在サポートされている Connect Sync 機能はどれですか?

機能の完全な比較については、[Connect SyncとCloud Syncの決定ガイド](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)を参照してください。 決定ガイドには、クラウド同期での 40 以上の機能とその可用性をカバーする詳細な比較表が含まれています。

#### Cloud Sync でまだ利用できない機能に依存している場合はどうなりますか?

組織が依存している機能が Cloud Sync でサポートされるまで、移行する必要はありません。接続同期は、暫定的に引き続き使用できます。 これらの機能が使用可能になったら、移行を続行できます。 機能の可用性に関する更新プログラムについては、 [意思決定ガイド](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide) を定期的に確認してください。

#### Pass-Through 認証 (PTA) などのハイブリッド認証機能はどうなりますか?

Cloud Sync に移行すると、クラウド リソースへのアクセスにオンプレミスの資格情報を使用できるようにするハイブリッド認証機能が引き続き使用できるようになります。 [Pass-Through 認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)や[シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)などの機能は、同期ツールとは別に構成され、Connect Sync 構成ウィザードを使用して移行後も機能し続けます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/concept-attributes"} -->
## Microsoft Entra スキーマとカスタム式について理解します。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-attributes
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra スキーマ、プロビジョニング エージェントでフローする属性、およびカスタム式について説明します。

Microsoft Entra ID におけるオブジェクトは、他のディレクトリと同様に、ユーザー、グループ、連絡先などを表す、プログラムによる高次データ構造体です。 Microsoft Entra ID に新しいユーザーや連絡先を作成すると、それはオブジェクトの新しいインスタンスとして作成されます。 これらのインスタンスは、プロパティに基づいて区別できます。

Microsoft Entra ID におけるプロパティは、オブジェクトのインスタンスに関する情報を保持する要素です。

Microsoft Entra スキーマでは、エントリでプロパティを使用するルール、それらのプロパティが持つことができる値の種類、およびユーザーがこれらの値を操作する方法を定義します。

Microsoft Entra ID には 2 種類のプロパティがあります。

- **組み込みプロパティ**: Microsoft Entra スキーマで事前に定義されているプロパティです。 これらのプロパティにはさまざまな用途があり、アクセスできる場合とできない場合があります。
- **ディレクトリ拡張**: 独自に Microsoft Entra ID をカスタマイズするために提供されているプロパティです。 たとえば、特定の属性を持つオンプレミスの Active Directory を拡張し、その属性をフローさせる場合は、提供されているカスタム プロパティの 1 つを使用できます。

各クラウド同期の構成には[同期スキーマ](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-synchronizationschema)が含まれます。 この同期スキーマは、どのオブジェクトを同期するか、どのように同期するかを定義します。

### 属性と式

たとえば、ユーザーのようなオブジェクトが Microsoft Entra ID にプロビジョニングされると、ユーザー オブジェクトの新しいインスタンスが作成されます。 この作成物には、そのオブジェクトのプロパティが含まれています。これは属性とも呼ばれます。 初期状態では、新しく作成されたオブジェクトの属性は、同期規則によって決定される値に設定されます。 これらの属性は、クラウド プロビジョニング エージェントを使用して最新の状態に保たれます。

[Image: オブジェクトのプロビジョニング]

たとえば、ユーザーがマーケティング部門に属しているとします。 最初に、プロビジョニング時に Microsoft Entra の部門属性が作成され、値が Marketing に設定されます。 6 か月後に販売に異動した場合、オンプレミスの Active Directory の部門属性が Sales に変更されます。 この変更は Microsoft Entra ID に同期され、そのユーザー オブジェクトに反映されます。

属性の同期は、直接行うことができます。この場合、Microsoft Entra ID の値は、オンプレミスの属性の値に直接設定されます。 または、プログラム式によって同期が処理される場合があります。 値を設定するために何らかのロジックを要するか、または決定を行う必要がある場合は、プログラム式が必要になります。

たとえば、メールの属性が "john.smith@contoso.com" であり、"@contoso.com" の部分を取り除いて値 "john. smith" だけをフローさせる必要がある場合は、次のコードを使用します。

`Replace([mail], "@contoso.com", , ,"", ,)`

**サンプル入力/出力:**

- **入力** (mail): "john.smith@contoso.com"
- **出力**: "john. smith"

カスタム式および構文の記述方法の詳細については、「[Microsoft Entra ID での属性マッピングのための式の作成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)」を参照してください。

次の表では、一般的な属性と、それらがどのように Microsoft Entra ID に同期されるかを示しています。

| オンプレミスの Active Directory | マッピングの種類 | Microsoft Entra ID |
| --- | --- | --- |
| cn | 直接 | commonName |
| countryCode | 直接 | countryCode |
| displayName | 直接 | displayName |
| givenName | 式 | givenName |
| objectGUID（オブジェクトGUID） | 直接 | sourceAnchorBinary |
| userPrincipalName | 直接 | userPrincipalName |
| プロキシアドレス | 直接 | プロキシアドレス |

### 同期スキーマを表示する

警告

クラウド同期構成ではサービス プリンシパルが作成されます。 サービス プリンシパルは Microsoft Entra 管理センターで表示されます。 Microsoft Entra 管理センターのサービス プリンシパルの画面では、属性マッピングを変更しないでください。 これはサポートされていません。

クラウド同期構成の同期スキーマを表示して確認するには、以下の手順に従います。

1. [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)に移動します。
2. 自分のグローバル管理者アカウントでサインインします。
3. 左側で、 **[アクセス許可の変更]** を選択し、**Directory.ReadWrite.All** が*同意*になっていることを確認します。
4. クエリ `https://graph.microsoft.com/beta/serviceprincipals/?$filter=startswith(DisplayName, ‘{sync config name}’)` を実行します。 このクエリでは、サービス プリンシパルのフィルター処理された一覧が返されます。 この一覧は Microsoft Entra ID の [アプリの登録] ノードからも確認できます。
5. `"appDisplayName": "Active Directory to Azure Active Directory Provisioning"` を見つけて、`"id"` の値を書き留めます。

    ```
    "value": [
            {
                "id": "00d41b14-7958-45ad-9d75-d52fa29e02a1",
                "deletedDateTime": null,
                "accountEnabled": true,
                "appDisplayName": "Active Directory to Azure Active Directory Provisioning",
                "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",
                "applicationTemplateId": null,
                "appOwnerOrganizationId": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
                "appRoleAssignmentRequired": false,
                "displayName": "Active Directory to Azure Active Directory Provisioning",
                "errorUrl": null,
                "homepage": "https://account.activedirectory.windowsazure.com:444/applications/default.aspx?metadata=AD2AADProvisioning|ISV9.1|primary|z",
                "loginUrl": null,
                "logoutUrl": null,
                "notificationEmailAddresses": [],
                "preferredSingleSignOnMode": null,
                "preferredTokenSigningKeyEndDateTime": null,
                "preferredTokenSigningKeyThumbprint": null,
                "publisherName": "Active Directory Application Registry",
                "replyUrls": [],
                "samlMetadataUrl": null,
                "samlSingleSignOnSettings": null,
                "servicePrincipalNames": [
                    "http://adapplicationregistry.onmicrosoft.com/adprovisioningtoaad/primary",
                    "1a4721b3-e57f-4451-ae87-ef078703ec94"
                ],
                "signInAudience": "AzureADMultipleOrgs",
                "tags": [
                    "WindowsAzureActiveDirectoryIntegratedApp"
                ],
                "addIns": [],
                "api": {
                    "resourceSpecificApplicationPermissions": []
                },
                "appRoles": [
                    {
                        "allowedMemberTypes": [
                            "User"
                        ],
                        "description": "msiam_access",
                        "displayName": "msiam_access",
                        "id": "a0326856-1f51-4311-8ae7-a034d168eedf",
                        "isEnabled": true,
                        "origin": "Application",
                        "value": null
                    }
                ],
                "info": {
                    "termsOfServiceUrl": null,
                    "supportUrl": null,
                    "privacyStatementUrl": null,
                    "marketingUrl": null,
                    "logoUrl": null
                },
                "keyCredentials": [],
                "publishedPermissionScopes": [
                    {
                        "adminConsentDescription": "Allow the application to access Active Directory to Azure Active Directory Provisioning on behalf of the signed-in user.",
                        "adminConsentDisplayName": "Access Active Directory to Azure Active Directory Provisioning",
                        "id": "d40ed463-646c-4efe-bb3e-3fa7d0006688",
                        "isEnabled": true,
                        "type": "User",
                        "userConsentDescription": "Allow the application to access Active Directory to Azure Active Directory Provisioning on your behalf.",
                        "userConsentDisplayName": "Access Active Directory to Azure Active Directory Provisioning",
                        "value": "user_impersonation"
                    }
                ],
                "passwordCredentials": []
            },
    ```
6. `{Service Principal id}` を独自の値に置き換え、クエリ `https://graph.microsoft.com/beta/serviceprincipals/{Service Principal id}/synchronization/jobs/` を実行します。
7. `"id": "AD2AADProvisioning.fd1c9b9e8077402c8bc03a7186c8f976"` を見つけて、`"id"` の値を書き留めます。

    ```
    {
                "id": "AD2AADProvisioning.fd1c9b9e8077402c8bc03a7186c8f976",
                "templateId": "AD2AADProvisioning",
                "schedule": {
                    "expiration": null,
                    "interval": "PT2M",
                    "state": "Active"
                },
                "status": {
                    "countSuccessiveCompleteFailures": 0,
                    "escrowsPruned": false,
                    "code": "Active",
                    "lastSuccessfulExecutionWithExports": null,
                    "quarantine": null,
                    "steadyStateFirstAchievedTime": "2019-11-08T15:48:05.7360238Z",
                    "steadyStateLastAchievedTime": "2019-11-20T16:17:24.7957721Z",
                    "troubleshootingUrl": "",
                    "lastExecution": {
                        "activityIdentifier": "2dea06a7-2960-420d-931e-f6c807ebda24",
                        "countEntitled": 0,
                        "countEntitledForProvisioning": 0,
                        "countEscrowed": 15,
                        "countEscrowedRaw": 15,
                        "countExported": 0,
                        "countExports": 0,
                        "countImported": 0,
                        "countImportedDeltas": 0,
                        "countImportedReferenceDeltas": 0,
                        "state": "Succeeded",
                        "error": null,
                        "timeBegan": "2019-11-20T16:15:21.116098Z",
                        "timeEnded": "2019-11-20T16:17:24.7488681Z"
                    },
                    "lastSuccessfulExecution": {
                        "activityIdentifier": null,
                        "countEntitled": 0,
                        "countEntitledForProvisioning": 0,
                        "countEscrowed": 0,
                        "countEscrowedRaw": 0,
                        "countExported": 5,
                        "countExports": 0,
                        "countImported": 0,
                        "countImportedDeltas": 0,
                        "countImportedReferenceDeltas": 0,
                        "state": "Succeeded",
                        "error": null,
                        "timeBegan": "0001-01-01T00:00:00Z",
                        "timeEnded": "2019-11-20T14:09:46.8855027Z"
                    },
                    "progress": [],
                    "synchronizedEntryCountByType": [
                        {
                            "key": "group to Group",
                            "value": 33
                        },
                        {
                            "key": "user to User",
                            "value": 3
                        }
                    ]
                },
                "synchronizationJobSettings": [
                    {
                        "name": "Domain",
                        "value": "{\"DomainFQDN\":\"contoso.com\",\"DomainNetBios\":\"CONTOSO\",\"ForestFQDN\":\"contoso.com\",\"ForestNetBios\":\"CONTOSO\"}"
                    },
                    {
                        "name": "DomainFQDN",
                        "value": "contoso.com"
                    },
                    {
                        "name": "DomainNetBios",
                        "value": "CONTOSO"
                    },
                    {
                        "name": "ForestFQDN",
                        "value": "contoso.com"
                    },
                    {
                        "name": "ForestNetBios",
                        "value": "CONTOSO"
                    },
                    {
                        "name": "QuarantineTooManyDeletesThreshold",
                        "value": "500"
                    }
                ]
            }
    ```
8. ここで、クエリ `https://graph.microsoft.com/beta/serviceprincipals/{Service Principal Id}/synchronization/jobs/{AD2AAD Provisioning id}/schema` を実行します。

    `{Service Principal Id}` と `{AD2ADD Provisioning Id}` を独自の値に置き換えます。
9. このクエリで[同期スキーマ](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-synchronizationschema)が返されます。

    [Image: 返されたスキーマ]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/concept-deployment-options-provision-to-active-directory"} -->
## Microsoft Entra プロビジョニング オプション (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-deployment-options-provision-to-active-directory
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-10
- Summary: Microsoft Entra IDからActive Directoryへのプロビジョニングのために、グループ専用、ユーザー専用、および組み合わせMicrosoft Entra Cloud Sync のオプションを比較します。

Microsoft Entra Cloud Sync を使用して Microsoft Entra ID からオンプレミスの Active Directory Domain Services (AD DS) にプロビジョニングする場合は、**何を**プロビジョニングするかを *スコープ フィルター*で選択します。 この記事では、3 つのデプロイ オプションを比較し、適切なデプロイ オプションと適切なスケールを選択するのに役立ちます。

概念的な概要については、「[Microsoft Entra IDからActive Directoryへのプロビジョニングの概要](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory)」を参照してください。 これらのオプションのいずれかを構成するには、「[Active Directoryへのプロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)」を参照してください。

### 3 つのデプロイ オプション

3 つのオプションはすべて、クラウド同期で同じ構成の種類 (**AD 同期にMicrosoft Entra ID) を**使用します。異なるのは、スコープに取り込むオブジェクトの種類です。

| オプション | 規定 | Availability | タイミングを選択 |
| --- | --- | --- | --- |
| **グループのみ** | セキュリティ グループと、オンプレミス所有ユーザーへのメンバーシップ | 一般公開 | オンプレミスの所有ユーザーに対してのみ、オンプレミス アプリケーションへのアクセスを管理する必要があります。 ソースオブオーソリティ (SOA) をクラウドに変換するクラウドネイティブ ユーザーとユーザーは、オンプレミス アプリケーションにアクセスする必要はありません。 |
| **ユーザーのみ** | ユーザー | プレビュー | AD でアクセス ガバナンスを維持しながら、Microsoft Entra IDで ID ライフサイクルを管理したいと考えています。 |
| **ユーザーとグループ** | ユーザーとセキュリティ グループ、およびオンプレミス所有ユーザーとクラウド所有ユーザーの両方へのメンバーシップ | プレビュー | Microsoft Entra IDから ID ライフサイクルとオンプレミス アプリケーションへのアクセスを管理する必要があります。 |

Note

ユーザー **のプロビジョニング** (ユーザーのみおよびユーザーとグループ) は現在 **プレビュー段階です**。 プロビジョニング **グループ** は一般提供されています。

#### オプションを選択する方法

構成のスコープ フィルターでオプションを選択します。

- **ユーザー**をプロビジョニングするには、ユーザーをスコープに取り込みます。
- **グループ**をプロビジョニングするには、セキュリティ グループをスコープに取り込みます。
- **両方**をプロビジョニングするには、同じ構成で両方のオブジェクトの種類を有効にします。

詳細なスコープ手順については、「Active Directory[へのプロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#configure-scoping-filters)」を参照してください。

### ドメインごとおよびテナントごとの構成

AD ドメインごとに 1 つの構成しか存在できないため、同じドメインに対して個別のユーザー構成とグループ構成を実行しないでください。 代わりに、ドメインの構成がスコープに取り込むオブジェクトの種類を選択します。

- ユーザーとグループ **の両方** で、ドメインの両方のオブジェクトの種類を 1 か所で管理できるようにします。
- ユーザーまたはグループのみをプロビジョニングする必要がある場合は、 **1 つの** オブジェクトの種類を有効にします。 後で他の種類をプロビジョニングする場合は、2 つ目の種類を作成するのではなく、既存の構成を含むように変更します。

異なるドメインの構成は独立しているため、各ドメインのスコープ設定、スケジュール設定、トラブルシューティングを個別に行うことができます。 [ライセンス要件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites-provision-entra-to-active-directory)に従って、テナントごとに最大 20 個の構成を実行できます。 この制限は、ユーザー、グループ、またはその両方をプロビジョニングするかどうかに関係なく、すべての構成を対象とします。

### パフォーマンスとスケールの制限

プロビジョニングのパフォーマンスは、テナントのサイズとスコープ内のオブジェクトの数によって影響を受けます。 ユーザー、グループ、およびグループ メンバーシップのリンクはすべて、同じ合計にカウントされます。 ユーザーにはメンバーシップ モデルがないため、各ユーザーは 1 つのオブジェクトとしてカウントされ、グループは 1 つのオブジェクトと各メンバーシップ リンクに対して 1 つのオブジェクトとしてカウントされます。

#### 推奨される構成

スコープの選択によって、サービスがサイクルごとに実行する作業の量が決まります。 プロビジョニング構成では、プロビジョニングが遅くなる可能性が高い場合に警告が表示されますが、構成の保存はブロックされません。 次の表では、構成する内容とその理由について説明します。

| 設定 | レコメンデーション | なぜでしょうか |
| --- | --- | --- |
| **すべてのユーザーとグループ** | 属性値フィルターを少なくとも 1 つ追加します。 | フィルターを使用しない場合、テナント内のすべてのユーザーとグループは、プロビジョニングする予定のないオブジェクトを含め、すべてのサイクルで評価されます。 サイクルに時間がかかり、テナントの成長に合わせてパフォーマンスがさらに低下します。 |
| **選択したユーザーとグループ** | 属性値フィルターは追加しないでください。 | どのオブジェクトがプロビジョニングされているかは、既に選択によって決まります。 フィルターは、プロビジョニングされるオブジェクトを変更することなく、すべてのサイクルに処理時間を追加します。 |
| **オンプレミス ユーザーにメンバーシップをプロビジョニングする** | 遷移として扱います。 グループの機関ソースをクラウドに変換し、代わりにグループをActive Directoryにプロビジョニングします。 | この設定では、権限ソースが引き続きオンプレミスであるメンバーのメンバーシップ リンクを書き戻します。これは、各サイクルでメンバーごとにサービスによって解決されます。 グループをクラウド管理に変換すると、それらのリンクが完全に不要になります。 |

2 つのスコープ モードには、反対のフィルター要件があります。 **すべてのユーザーとグループ** には、それ以外の無制限のスコープを絞り込むためのフィルターが必要ですが、 **選択したユーザーとグループ** は既に絞り込まれているため、フィルターは純粋なオーバーヘッドです。 既存の構成を **[すべて** **] から [選択済み]** に変更した場合は、追加した属性値フィルターを削除します。

#### オンプレミスのメンバーシップよりもクラウドマネージド グループを優先する

オンプレミス ユーザーにメンバーシップをプロビジョニングすると、クラウド グループは Active Directory が引き続き管理しているメンバーと同期した状態に保たれます。 機能は機能しますが、機能の最適な用途ではありません。すべてのサイクルで、別のシステムが制御するオブジェクトへのリンクを維持するために労力が費やされます。

推奨される終了状態は、[グループの機関ソースをMicrosoft Entra IDに変換](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-group-source-of-authority-configure)し、そのクラウドマネージド グループをActive Directoryにプロビジョニングすることです。 その後、グループとそのメンバーは 1 か所で管理され、同期されたユーザーへのメンバーシップ リンクは残されていません。

#### サポートされていないもの

50,000 を超えるメンバーのグループはサポートされていません。

#### グループとメンバーシップのスケール制限

| スコープ モード | 対象範囲内のグループ | メンバーシップ リンク (直接メンバー) | メモ |
| --- | --- | --- | --- |
| **選択したセキュリティ グループ** | 最大 10,000 グループ。 Microsoft Entra 管理センターの [クラウド同期] ウィンドウでは、最大 999 個のグループを選択して表示できます。 999 を超えるグループを追加するには、「 API を使用したグループの選択の展開」を参照してください。 | 対象範囲内のすべてのグループを合わせて、メンバー総数は最大 250,000 人。 | テナントが 200,000 人を超えるユーザー、40,000 を超えるグループ、または 100 万を超えるグループ メンバーシップのいずれかの値を超える場合は、このモードを使用します。 |
| 少なくとも 1 つの属性スコープ フィルターを持つ**すべてのセキュリティ グループ** | 最大 20K グループ。 | スコープ内のすべての対象グループの合計メンバー数は最大 500,000 人です。 | このモードは、テナントがこれらのすべての条件を満たしている場合にのみ使用します。ユーザー数が 200,000 人未満、グループ数が 40,000 人未満、グループ メンバーシップが 1M 未満です。 |

#### ユーザーのスケール制限

| Configuration | スケールの制限 | メモ |
| --- | --- | --- |
| **ユーザーのみ** | 最大 200,000 ユーザー | この制限は、グループ プロビジョニングでサポートされているテナント スケール条件 (200,000 人未満のユーザー、40,000 グループ未満、1M 未満のグループ メンバーシップ) に合わせて調整されます。 この構成では、グループまたはメンバーシップ リンクは処理されません。スコープ内のすべてのオブジェクトはユーザーです。 |
| **ユーザーとグループ** | 前の表の制限に対して合計を測定する | ユーザー、グループ、メンバーシップ のリンクは、同じ合計にカウントされます。 |

#### 制限を超えた場合の操作

推奨される制限を超えると、初期同期と差分同期が遅くなり、同期エラーが発生する可能性があります。 その場合は、次のようにしてください。

- **選択したセキュリティ グループ モードのグループまたはメンバーが多すぎます**: スコープ内グループの数を減らします。 AD ドメインごとに構成は 1 つだけであるため、1 つのドメインのスコープを複数の構成に分割することはできません。
- **[すべてのセキュリティ グループ] モードでグループまたはメンバーが多すぎます**: **選択したセキュリティ グループ** モードに切り替えます。
- **スコープ内のユーザーが多すぎます**: スコープ内のユーザーが少ないほど、ユーザー スコープ フィルターを絞り込みます。
- **グループが 50,000 人を超えています**。複数のグループにメンバーシップを分割するか、段階的なグループを採用して各グループを上限の下に保持します。

#### API を使用したグループの選択の拡張

999 を超えるグループを選択する必要がある場合は、[Grant an appRoleAssignment for a service principal](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-post-approleassignedto) API を使用してください。

```https
POST https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalID}/appRoleAssignedTo
Content-Type: application/json

{
  "principalId": "",
  "resourceId": "",
  "appRoleId": ""
}
```

Where:

- **principalId**: グループ オブジェクト ID。
- **resourceId**: ジョブのサービス プリンシパル ID。
- **appRoleId**: リソース サービス プリンシパルによって公開されるアプリ ロールの識別子。

クラウド別アプリ ロール ID:

| 雲 | appRoleId |
| --- | --- |
| Public | `1a0abf4d-b9fa-4512-a3a2-51ee82c6fd9f` |
| AzureChinaCloud | `15af7fd5-1a3f-4034-8ea1-1afa3d5ecf63` |
| AzureUSGovernment | `d8fa317e-0713-4930-91d8-1dbeb150978f` |
| AzureUSNatCloud | `50a55e47-aae2-425c-8dcb-ed711147a39f` |
| AzureUSSecCloud | `52e862b9-0b95-43fe-9340-54f51248314f` |

### その他の考慮事項

- AD DS に書き込まれるグループ メンバーシップには、AD DS アカウントを持つメンバーのみが含まれます。 これらのメンバーは、オンプレミスの同期されたユーザー、クラウドで管理されているユーザー、ユーザー プロビジョニングのスコープ内にあるため、Cloud Sync が AD DS にプロビジョニングするクラウドマネージド ユーザー、または他のクラウドで作成されたセキュリティ グループにすることができます。 AD DS アカウントを持たないクラウドマネージド ユーザーはスキップされます。
- オンプレミスの同期されたユーザーには、ターゲット AD DS 環境の対応する`objectGUID`と一致する`onPremisesObjectIdentifier`属性が設定されている必要があります。
- Microsoft Entra ID から AD DS にプロビジョニングできるのは、グローバル Microsoft Entra ID テナントのみです。 B2C などのテナントはサポートされていません。
- プロビジョニング ジョブは、定期的なスケジュールで実行されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/concept-how-it-works"} -->
## Microsoft Entra クラウド同期 徹底解説 - しくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-how-it-works
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: このトピックでは、クラウド同期のしくみについて詳細に説明します。

### コンポーネントの概要

[Image: しくみ]

クラウド同期は Microsoft Entra サービスの上に構築されており、次の 2 つの主要コンポーネントがあります:

- **プロビジョニング エージェント**: Microsoft Entra Connect クラウド プロビジョニング エージェントは Workday 受信と同じエージェントであり、アプリ プロキシおよびパススルー認証と同じサーバー側テクノロジで構築されています。 必要となるのは送信接続のみであり、エージェントは自動更新されます。
- **プロビジョニング サービス**: 送信プロビジョニング、およびスケジューラに基づいたモデルを使用する Workday 受信プロビジョニングと同じプロビジョニング サービス。 クラウド同期のプロビジョニングは 2 分ごとに変更されます。

### 初期セットアップ

初期セットアップ中に、いくつかの操作を行うことでクラウド同期が実行されます。

- **エージェントのインストール中**:プロビジョニングするための AD ドメインのエージェントを構成します。 この構成でドメインがハイブリッド ID サービスに登録され、要求をリッスンする Service Bus への送信接続が確立されます。
- **プロビジョニングを有効にするとき**: AD ドメインを選択し、2 分ごとに実行されるプロビジョニングを有効にします。 必要に応じて、パスワード ハッシュ同期を選択解除して通知用メールを定義することもできます。 Microsoft Graph API を使用して属性変換を管理することもできます。

### エージェントのインストール

クラウド プロビジョニング エージェントのインストール時には、以下のことが行われます。

- インストーラーによってエージェント バイナリとエージェントサービスがインストールされ、仮想サービス アカウント (NETWORK SERVICE\AADProvisioningAgent) で実行されます。 仮想サービス アカウントは、パスワードのない特殊な種類のアカウントで、Windows によって管理されます。
- 次にインストーラーによって、ウィザードが開始されます。
- ウィザードで Microsoft Entra の資格情報の入力が求められ、認証が行われて、トークンが取得されます。
- 次にウィザードで、現在のコンピューターのドメイン管理者の資格情報が求められます。
- このドメインのエージェント一般管理サービス アカウント (GMSA) が作成されるか、既に存在する場合は再利用されます。
- エージェント サービスは、GMSA で動作するよう再構成されます。
- ウィザードでは、エージェントがサービスを提供する各ドメインに対して、ドメイン構成とエンタープライズ管理者 (EA) またはドメイン管理者 (DA) アカウントが要求されます。
- GMSA アカウントがアクセス許可で更新され、これにより、セットアップ中に入力した各ドメインにアクセスできるようになります。
- 次に、ウィザードでエージェント登録がトリガーされます。
- エージェントは、証明書を作成し、Microsoft Entra トークンを使って、自身と証明書をハイブリッド ID サービス (HIS) 登録サービスに登録します。
- ウィザードで AgentResourceGrouping 呼び出しがトリガーされます。 この HIS 管理サービスへの呼び出しでは、HIS 構成内の 1 つ以上の AD ドメインにエージェントが割り当てられます。
- ウィザードがエージェントサービスを再起動します。
- エージェントは再起動時 (およびその後 10 分ごと) にブートストラップ サービスを呼び出して、構成に更新がないか確認します。 ブートストラップ サービスは、エージェントの ID を検証します。 最後のブートストラップ時刻も更新されます。 これは、エージェントがブートストラップしなければ、Service Bus エンドポイントが更新されず、要求を受信できなくなる可能性があるため、重要です。

### System for Cross-domain Identity Management (SCIM) とは

[SCIM 仕様](https://tools.ietf.org/html/draft-scim-core-schema-01)は、Microsoft Entra ID などの ID ドメイン間でのユーザーまたはグループ ID 情報の交換を自動化するために使われる標準です。 SCIM は、プロビジョニングに対する事実上の標準になりつつあり、SAML や OpenID Connect などのフェデレーション標準と共に使用すると、エンドツーエンドの標準ベースのアクセス管理用ソリューションが管理者に提供されます。

Microsoft Entra Connect クラウド プロビジョニング エージェントは、Microsoft Entra ID を持つ SCIM を使って、ユーザーとグループのプロビジョニングとプロビジョニング解除を行います。

### 同期フロー

[Image: プロビジョニング]

エージェントをインストールしてプロビジョニングを有効にすると、以下のフローが発生します。

1. 構成が完了すると、Microsoft Entra プロビジョニング サービスから Microsoft Entra ハイブリッド サービスが呼び出され、サービス バスに要求が追加されます。 エージェントは、要求をリッスンする Service Bus への送信接続を常に維持し、クロスドメイン ID 管理システム (SCIM) 要求を即座に取得します。
2. エージェントは、オブジェクトの種類に基づいて要求を個別のクエリに分割します。
3. AD からエージェントに結果が返され、エージェントはこのデータをフィルター処理してから、Microsoft Entra ID に送信します。
4. エージェントは SCIM 応答を Microsoft Entra ID に返します。 これらの応答は、エージェント内で行われたフィルター処理に基づいています。 エージェントは、スコーピングを使用して結果をフィルター処理します。
5. プロビジョニング サービスが変更を Microsoft Entra ID に書き込みます。
6. 完全同期でなく、差分同期が行われる場合は、クッキーまたはウォーターマークが使用されます。 新しいクエリでは、変更がそのクッキーまたはウォーターマーク以降に反映されます。

### サポートされているシナリオ:

クラウド同期では、次のシナリオがサポートされています。

- **新しいフォレストがある既存のハイブリッドのお客様**: Microsoft Entra Connect 同期はプライマリ フォレストに使われます。 クラウド同期機能は、AD フォレスト（切断されているものも含む）からのプロビジョニングに使用されます。 詳細については、チュートリアル ([こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-existing-forest)) を参照してください。

[Image: 既存のハイブリッド]

- **新規のハイブリッドのお客様**: Microsoft Entra Connect 同期は使われません。 クラウド同期が AD フォレストからのプロビジョニングに使用されます。 詳細については、チュートリアル ([こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest)) を参照してください。

[Image: 新規のお客様]

- **既存のハイブリッドのお客様**: Microsoft Entra Connect 同期はプライマリ フォレストに使われます。 クラウド同期のパイロットがプライマリ フォレストの小数のユーザーを対象に実施されています ([こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-existing-forest))。

[Image: 既存のパイロット]

詳細については、[サポートされるトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/plan-cloud-sync-topologies)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide"} -->
## Microsoft Entra Connect からクラウド同期への移行: 意思決定ガイド - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-02-17
- Summary: Microsoft Entra Connect と Cloud Sync の技術的な機能を比較して、情報に基づいた移行の決定を行います。 機能の違い、制限事項、移行のタイミングについて説明します。

Microsoft Entra Cloud Sync は、ハイブリッド ID 同期のための Microsoft の戦略的方向性を表し、Active Directory と Microsoft Entra ID の間でユーザー、グループ、連絡先を同期するための最新のクラウド管理アプローチを提供します。 組織がハイブリッド ID インフラストラクチャを評価する際、情報に基づいた意思決定を行うには、技術的な利点と移行の準備状況を理解することが不可欠です。

この意思決定ガイドは、IT アーキテクトと意思決定者が、技術的な機能を比較し、アーキテクチャ上の利点を特定し、移行準備評価を提供することで、Microsoft Entra Connect からクラウド同期への移行を評価するのに役立ちます。 基本的な概要情報については、「 [Microsoft Entra Cloud Sync とは」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)を参照してください。詳細な移行手順については、「 [Microsoft Entra Connect から Microsoft Entra Cloud Sync への移行](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync)」を参照してください。

新しい ID と同期機能は、主にクラウド同期プラットフォームで開発されており、ほとんどの組織で推奨されるパスです。

### 移行の技術的な利点

このセクションでは、Microsoft Entra Connect から Cloud Sync に移行する主な技術的な利点について説明し、Cloud Sync のアーキテクチャと機能によってハイブリッド ID 環境がどのように強化されるかについての分析情報を提供します。

#### クラウド管理の構成アーキテクチャ

Microsoft Entra Connect は、管理、更新、およびトラブルシューティングのためにサーバーに直接アクセスする必要があるオンプレミスのサーバー構成に依存しています。 Cloud Sync は、構成管理をクラウドに移行し、すべての同期設定をサービスの一部として Microsoft Entra ID に格納します。 このアーキテクチャにより、Microsoft Entra 管理センターを通じて一元的な制御を提供しながら、オンプレミスのサーバー管理が不要になります。

管理者は、VPN アクセスやオンプレミス接続を必要とせずに、同期構成の変更、同期の状態の監視、および任意の場所からの問題のトラブルシューティングを行うことができます。 構成の変更はエージェントに自動的に分散されるため、管理オーバーヘッドと潜在的な構成誤差が軽減されます。

#### 複数のアクティブなエージェントによる信頼性の向上

Microsoft Entra Connect は単一障害点を作成します。Connect サーバーが使用できなくなった場合、サーバーが復元されるまで同期が停止します。 Cloud Sync では、異なるサーバーにデプロイされた複数のアクティブなプロビジョニング エージェントがサポートされ、自動フェールオーバー機能が提供されます。

1 つのエージェントが使用できなくなった場合、他のエージェントは中断することなく同期要求の処理を続行します。 この分散モデルは、メンテナンスの計画的なダウンタイムを排除し、ハードウェア障害やネットワーク接続の問題に対する組み込みの回復性を提供します。 組織は、パフォーマンスと信頼性を最適化するために、さまざまな場所またはネットワーク セグメントにエージェントを戦略的にデプロイできます。

#### 最新のスケーラビリティとデプロイ モデル

軽量プロビジョニング エージェント モデルでは、Connect 同期の包括的インストールと比較して最小限のサーバー リソースが必要です。 エージェントは、リソースへの影響を最小限に抑えながら、既存のドメイン コントローラーまたはスタンドアロン サーバーにデプロイできます。 この展開の柔軟性により、組織は地理的分散または組織の要件に基づいて同期インフラストラクチャをスケーリングできます。

エージェントは、手動による介入を必要とせずに Microsoft から更新プログラムとセキュリティ パッチを自動的に受け取り、同期インフラストラクチャを最新かつ安全な状態に保ちます。 クラウド管理の更新プロセスにより、メンテナンス期間と管理作業が削減されます。

#### 高度なディスコネクトフォレスト機能

Cloud Sync は、切断された複数の Active Directory フォレストからの同期をネイティブにサポートします。 これらのシナリオは、合併、買収、または複雑な組織構造の間に一般的に必要です。 接続が分断されているフォレストに複雑な構成または複数のインスタンスを必要とする Connect 同期とは異なり、Cloud Sync はマルチテナントアーキテクチャによってこれらのシナリオを対応します。

切断された各フォレストは、クラウド サービスを通じて統合管理を維持しながら、専用のエージェントを持つことができます。 この機能により、複雑な組織シナリオが簡略化され、従来は複数フォレストの同期に必要なインフラストラクチャの複雑さが軽減されます。

#### 新機能の戦略的プラットフォーム

新しい同期とプロビジョニング機能に関する Microsoft の開発フォーカスは、Cloud Sync を中心としています。Active Directory へのグループ プロビジョニング、高度な機関管理ソース、クラウドネイティブ ID シナリオなどの機能は、Cloud Sync でのみ使用できます。Connect 同期を使用している組織では、新機能へのアクセスが失われる場合や、同様の機能を実現するために追加のツールが必要になる場合があります。

### Microsoft Entra Connect とクラウド同期の比較

次の表は、Microsoft Entra Connect と Cloud Sync の技術的な機能の詳細な比較を示しています。

| 特徴/機能 | Connect Sync | クラウド同期 | 技術ノート |
| --- | --- | --- | --- |
| **ユーザー、グループ、連絡先の同期** | ✓ | ✓ | 基本的なディレクトリ オブジェクト同期の完全な一致 |
| **単一接続森** | ✓ | ✓ | どちらも標準の単一フォレスト トポロジをサポートしています |
| **複数の接続されたフォレスト** | ✓ | ✓ | 両方とも、複数の接続されたフォレスト シナリオをサポートします |
| **分離されたフォレストのサポート** | ✗ | ✓ | クラウド同期を使用すると、フォレストを統合せずに M&A シナリオを実現できます |
| **デバイスの同期** | ✓ | ✗ | Connect では、Hybrid Azure AD Join がサポートされます。クラウド同期では現在サポートされていません |
| **複数のアクティブな同期インスタンス** | ✗ | ✓ | クラウド同期エージェントは、自動フェールオーバーと負荷分散を提供します |
| **ドメインあたりのスケール制限** | 無制限 | 150K オブジェクト | クラウド同期では現在、ドメインあたり最大 150,000 個のオブジェクトがサポートされています |
| **大規模なグループのサポート** | 250K メンバー | 50,000 人のメンバー | Connect では、より大きなグループがサポートされます。Cloud Sync のメンバー数は 50,000 人に制限 |
| **パスワードハッシュの同期化** | ✓ | ✓ | パスワード同期機能の完全一致 |
| **パスワード ライトバック** | ✓ | ✓ | 両方のプラットフォームでサポートされている SSPR ライトバック |
| **Pass-Through 認証構成** | ✓ | ✗ | クラウド同期での同期とは別に管理される PTA 構成。移行後も PTA とシームレス SSO が引き続き機能する |
| **ADFS 統合のセットアップ** | ✓ | ✗ | フェデレーション構成には、クラウド同期で個別のツールが必要です |
| **Exchange ハイブリッド属性** | ✓ | ✓ | Exchange ハイブリッド シナリオの完全なサポート |
| **ディレクトリ拡張機能 (1 から 15)** | ✓ | ✓ | 標準の拡張機能属性の同期がサポートされています |
| **カスタム AD 属性** | ✓ | ✓ | サポートされている顧客定義の属性とディレクトリ拡張機能 |
| **基本的な属性のカスタマイズ** | ✓ | ✓ | 式ビルダーで使用できる属性フローのカスタマイズ |
| **高度な同期規則** | ✓ | ✗ | Connect で使用できる複雑な同期規則エンジン。Cloud Sync で式ビルダーを使用する |
| **OU ベースのフィルター処理** | ✓ | ✓ | どちらも組織単位のスコープをサポートします |
| **属性ベースのフィルター処理** | ✓ | 制限あり | Connect では、完全な属性フィルター処理が提供されます。Cloud Sync には基本的な機能があります |
| **デバイスへの書き戻し** | ✓ | ✗ | Cloud Kerberos Trust を優先して、デバイスの書き戻しが廃止されました |
| **グループ ライトバック V1** | ✓ | ✓ | レガシー グループの書き戻しは両方の環境でサポートされています |
| **AD へのグループ プロビジョニング** | ✗ | ✓ | Cloud-to-AD グループ プロビジョニングは、Cloud Sync でのみ使用できます |
| **AD へのユーザー プロビジョニング** | ✗ | ✗ | クラウドto-AD ユーザー プロビジョニングは現在サポートされていません |
| **クロスドメイン参照** | ✓ | ✓ | ドメイン間でのユーザー参照のサポート |
| **フォレスト間参照** | ✓ | ✗ | Connect では、フォレストとフォレストのオブジェクトのリレーションシップがサポートされます |
| **複数のドメインからの属性のマージ** | ✓ | ✗ | Connect では、さまざまな AD ソースの属性をマージできます |
| **調整機能** | ✓ | ✗ | クラウド同期で現在サポートされていない帯域外同期の修正 |
| **オンデマンド プロビジョニング** | ✗ | ✓ | クラウド同期では、即時同期テスト機能が提供されます |
| **クラウド構成管理** | ✗ | ✓ | クラウド同期は、Microsoft Entra 管理センターを通じて完全に管理されます |
| **シームレス シングル サインオン** | ✓ | ✓ | どちらのプラットフォームでもシームレス SSO がサポートされます |
| **米国政府クラウド** | ✓ | ✓ | どちらもソブリン クラウドデプロイをサポートしています |

### 移行の準備状況の評価

上の表の現在の要件と機能の比較に基づいて、次のシナリオを使用して移行の準備状況を評価します。

#### すぐに移行できる状態

次のすべての条件を満たしている場合、組織はすぐに移行できます。

- **オブジェクト スケール**: Active Directory ドメインあたり 150,000 未満のオブジェクト
- **グループ サイズ**: メンバー数が 50,000 未満のグループ
- **デバイス管理**: ハイブリッド Azure AD 参加を使用していない、または Cloud Kerberos Trust へ移行する意思がある
- **認証**: パスワード ハッシュ同期を使用するか、ADFS/PTA 構成を個別に管理します。
- **フィルター処理**: 複雑な属性ベースの規則ではなく、OU ベースのフィルター処理を使用する
- **フォレストの構成**: 単一フォレストまたは接続されたフォレスト (切断されたフォレストの要件なし)

このプロファイルに一致する組織は、Cloud Sync のアーキテクチャの改善、複数のエージェントサポート、新しいクラウドネイティブ機能へのアクセスからすぐにメリットを得ることができます。

#### 近い期間の移行を計画する

これらの要件がサポートされる可能性がある場合は、機能の可用性に基づいて移行を計画することを検討してください。

- **デバイス同期**: 現在、ハイブリッド Azure AD 参加デバイスの同期に依存しています
- **高度なフィルター処理**: 現在のクラウド同期機能を超える複雑な属性ベースのフィルター処理を使用する
- **AD へのユーザー プロビジョニング**: クラウドto-AD ユーザー プロビジョニング機能が必要

Microsoft の機能のお知らせを監視して、機能の可用性を確認し、重要な依存関係に基づいて移行のタイミングを計画します。

#### 将来の移行を評価する

これらの特性を持つ組織は、ビジネス 計画サイクルに基づいて移行のタイミングを評価する必要があります。

- **大規模な展開**: ドメインあたり150,000を超えるオブジェクト、または50,000を超えるメンバーを持つグループ
- **複雑な同期規則**: 大幅な再構成作業を必要とする広範なカスタム同期規則
- **フォレスト間依存関係**: Cloud Sync でサポートされていない複雑なフォレスト間オブジェクトの関係性
- **調整要件**: 帯域外同期修正機能への重要な依存関係
- **リソース計画**: 移行プロジェクトの実行に限定されたリソース

大規模な環境の場合は、ドメインまたは組織単位で移行をセグメント化することで、ビジネス継続性を維持しながら、実行可能なパスを提供するかどうかを評価します。

### 意思決定フレームワーク

このフレームワークを使用して、移行を決定します。

- **現在の依存関係を評価**する: 上記の包括的な機能テーブルを使用して、Connect 固有の機能の使用状況を確認します
- **移行の準備状況の評価**: 環境に一致する準備シナリオを決定する
- **移行タイミングの計画**: 機能のお知らせの監視、ビジネス サイクルの検討、リソースの可用性の評価
- **前提条件の検証**: 環境が[クラウド同期の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites)を満たしていることを確認する
- **移行プロセスのテスト**: [パイロット移行チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp) を使用して移行プロセスを検証する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/custom-attribute-mapping"} -->
## Microsoft Entra クラウド同期のディレクトリ拡張機能とカスタム属性マッピング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、クラウド同期でのカスタム属性マッピングについて説明します。

Microsoft Entra ID からユーザー アカウントを基幹業務 (LOB)、[SaaS アプリ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)、またはオンプレミス アプリケーションにプロビジョニングするときは、ユーザー プロファイルを作成するために必要なすべてのデータ (属性) が Microsoft Entra ID に含まれている必要があります。 ディレクトリ拡張機能を使用して、独自の属性を使用して Microsoft Entra ID のスキーマを拡張できます。 この機能を使用すると、オンプレミスで引き続き管理する属性を使用して LOB アプリを構築し、Active Directory から Microsoft Entra ID または SaaS アプリにユーザーをプロビジョニングし、動的メンバーシップ グループや Active Directory へのグループ プロビジョニングなどの Microsoft Entra ID と Microsoft Entra ID ガバナンス機能で拡張機能属性を使用できます。

ディレクトリ拡張機能の詳細については、[要求でのディレクトリ拡張属性の使用](https://learn.microsoft.com/ja-jp/entra/identity-platform/schema-extensions)に関するページ、「[Microsoft Entra Connect Sync: ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)」および「[Microsoft Entra アプリケーション プロビジョニングのための拡張属性の同期](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)」を参照してください。

使用可能な属性を確認するには、[Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を使用します。

注

新しい Active Directory 拡張機能属性を検出するには、プロビジョニング エージェントを再起動する必要があります。 ディレクトリ拡張機能が作成されたら、エージェントを再起動する必要があります。 Microsoft Entra 拡張機能属性の場合、エージェントを再起動する必要はありません。

### Microsoft Entra クラウド同期のディレクトリ拡張機能の同期

[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/graph/api/resources/extensionproperty?view=graph-rest-1.0&preserve-view=true)を使用すると、Microsoft Entra ID の同期スキーマ ディレクトリ定義を、独自の属性を使用して拡張できます。

重要

Microsoft Entra Cloud Sync のディレクトリ拡張機能は、識別子 URI `API://<tenantId>/CloudSyncCustomExtensionsApp` を持つアプリケーションと、Microsoft Entra Connect によって作成された [テナント スキーマ拡張機能アプリ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions#configuration-changes-in-azure-ad-made-by-the-wizard) でのみサポートされます。

#### ディレクトリ拡張機能のアプリケーションとサービス プリンシパルを作成する

識別子 URI  が存在しない場合は `API://<tenantId>/CloudSyncCustomExtensionsApp` を作成し、存在しない場合はアプリケーションのサービス プリンシパルを作成する必要があります。

## [Graph PowerShell](#tab/ps)
1. 識別子 URI `API://<tenantId>/CloudSyncCustomExtensionsApp` を持つアプリケーションが存在するかどうかを確認します。

    ```powershell
    $tenantId = (Get-MgOrganization).Id
    
    Get-MgApplication -Filter "identifierUris/any(uri:uri eq 'API://$tenantId/CloudSyncCustomExtensionsApp')"
    ```

    詳細については、「 [Get-MgApplication」を](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgapplication)参照してください。
2. アプリケーションが存在しない場合は、前の手順の `$tenantId` 変数を使用して、識別子 URI `API://<tenantId>/CloudSyncCustomExtensionsApp`を持つアプリケーションを作成します。

    ```powershell
    New-MgApplication -DisplayName "CloudSyncCustomExtensionsApp" -IdentifierUris "API://$tenantId/CloudSyncCustomExtensionsApp"
    ```

    詳細については、「 [New-MgApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgapplication)」を参照してください。
3. 前の手順の `$tenantId` 変数を使用して、識別子 URI が `API://<tenantId>/CloudSyncCustomExtensionsApp`されたアプリケーションのサービス プリンシパルが存在するかどうかを確認します。

    ```powershell
    $appId = (Get-MgApplication -Filter "identifierUris/any(uri:uri eq 'API://$tenantId/CloudSyncCustomExtensionsApp')").AppId
    
    Get-MgServicePrincipal -Filter "AppId eq '$appId'"
    ```

    詳細については、「 [Get-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal)」を参照してください。
4. サービス プリンシパルが存在しない場合は、前の手順の `$appId variable` を使用して、識別子 URI `API://<tenantId>/CloudSyncCustomExtensionsApp`を持つアプリケーションの新しいサービス プリンシパルを作成します。

    ```powershell
    New-MgServicePrincipal -AppId $appId
    ```

    詳細については、「 [New-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgserviceprincipal)」を参照してください。
5. 前の手順の `$tenantId` 変数を使用して、Microsoft Entra ID でディレクトリ拡張機能を作成します。 たとえば、Group オブジェクトの文字列型の "GroupDN" という名前の新しい拡張機能を次に示します。

    ```powershell
    $appObjId = (Get-MgApplication -Filter "identifierUris/any(uri:uri eq 'API://$tenantId/CloudSyncCustomExtensionsApp')").Id
    
    New-MgApplicationExtensionProperty -ApplicationId $appObjId -Name GroupDN -DataType String -TargetObjects Group
    ```

## [Graph エクスプローラー](#tab/ge)
1. 識別子 URI `API://<tenantId>/CloudSyncCustomExtensionsApp` を持つアプリケーションが存在するかどうかを確認します。

    ```https
    GET /applications?$filter=identifierUris/any(uri:uri eq 'api://<tenantId>/CloudSyncCustomExtensionsApp')
    ```

    詳細については、「[アプリケーションを取得する](https://learn.microsoft.com/ja-jp/graph/api/application-get?view=graph-rest-1.0&tabs=http&preserve-view=true)」を参照してください
2. アプリケーションが存在しない場合は、識別子 URI `API://<tenantId>/CloudSyncCustomExtensionsApp`を使用してアプリケーションを作成します。

    ```https
    POST https://graph.microsoft.com/v1.0/applications
    Content-type: application/json
    
    {
    "displayName": "CloudSyncCustomExtensionsApp",
    "identifierUris": ["api://<tenant id>/CloudSyncCustomExtensionsApp"]
    }
    ```

    詳細については、「アプリケーションの [作成](https://learn.microsoft.com/ja-jp/graph/api/application-post-applications?view=graph-rest-1.0&tabs=http&preserve-view=true)」を参照してください。
3. 識別子 URI が `API://<tenantId>/CloudSyncCustomExtensionsApp`されたアプリケーションのサービス プリンシパルが存在するかどうかを確認します。

    ```
    GET /servicePrincipals?$filter=(appId eq '{appId}')
    ```

    詳細については、「[servicePrincipal を取得する](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-get?view=graph-rest-1.0&tabs=http&preserve-view=true)」を参照してください
4. サービス プリンシパルが存在しない場合は、識別子 URI `API://<tenantId>/CloudSyncCustomExtensionsApp`を持つアプリケーションの新しいサービス プリンシパルを作成します。

    ```https
    POST https://graph.microsoft.com/v1.0/servicePrincipals
    Content-type: application/json
    
    {
    "appId": 
    "<application appId>"
    }
    ```

    詳細については、「 [servicePrincipal の作成](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-post-serviceprincipals?view=graph-rest-1.0&tabs=http&preserve-view=true)」を参照してください。
5. Microsoft Entra ID でディレクトリ拡張機能を作成します。 たとえば、Group オブジェクトの文字列型の "GroupDN" という名前の新しい拡張機能を次に示します。

    ```https
    POST https://graph.microsoft.com/v1.0/applications/<ApplicationId>/extensionProperties
    Content-type: application/json
    
    {
      "name": "GroupDN",
      "dataType": "String",
      "isMultiValued": false,
      "targetObjects": [
          "Group"
      ]
    }    
    ```

---

次の表に示すように、Microsoft Entra ID には、いくつかの異なる方法でディレクトリ拡張機能を作成できます。

| メソッド | 説明 | URL |
| --- | --- | --- |
| MS Graph | Microsoft Graph を使用して拡張機能を作成する | [extensionProperty を作成する](https://learn.microsoft.com/ja-jp/graph/api/application-post-extensionproperty?view=graph-rest-1.0&tabs=http&preserve-view=true) |
| PowerShell | PowerShell を使用して拡張機能を作成する | [New-MgApplicationExtensionProperty](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgapplicationextensionproperty) (新しいMgアプリケーション拡張プロパティ) |
| クラウド同期と Microsoft Entra Connect の使用 | Microsoft Entra Connect を使用して拡張機能を作成する | [Microsoft Entra Connect を使用して拡張属性を作成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping#create-an-extension-attribute-using-azure-ad-connect) |
| 同期する属性のカスタマイズ | 同期する属性のカスタマイズに関する情報 | [Microsoft Entra ID と同期する属性をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions#select-which-attributes-to-synchronize-with-microsoft-entra-id) |

### 属性マッピングを使用してディレクトリ拡張機能をマップする

カスタム属性を含むように Active Directory を拡張した場合は、これらの属性を追加してユーザーにマップできます。

属性を検出してマップするには、**[属性マッピング** の追加] を選択し、属性 **ソース属性**のドロップダウンで使用できるようになります。 目的のマッピングの種類を入力し、**[適用]** をクリックします。 [Image: カスタム属性のマッピング]

Microsoft Entra ID で追加および更新される新しい属性の詳細については、[`user` リソース タイプ](https://learn.microsoft.com/ja-jp/graph/api/resources/user?view=graph-rest-1.0#properties&preserve-view=true)に関する記事を参照し、[変更通知](https://learn.microsoft.com/ja-jp/graph/change-notifications-overview)のサブスクライブを検討してください。

拡張属性の詳細については、「[Microsoft Entra アプリケーション プロビジョニングのための拡張属性の同期](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/custom-attribute-mapping-entra-to-active-directory"} -->
## AD へのプロビジョニング用の Microsoft Entra ディレクトリ拡張機能 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping-entra-to-active-directory
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-11
- Summary: ユーザーとグループのディレクトリ拡張機能が、Microsoft Entra IDからActive Directoryへのプロビジョニングをサポートする方法について説明します。

ディレクトリ拡張機能を使用してユーザーとグループのスキーマを拡張し、それらの属性をスコープと属性マッピングに使用できます。 Active DirectoryからMicrosoft Entra IDへのプロビジョニング時にディレクトリ拡張機能を探している場合は、「[クラウド同期ディレクトリ拡張機能とカスタム属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping)」を参照してください。

Von Bedeutung

Microsoft Entra Cloud Sync のディレクトリ拡張機能は、識別子 URI `api://<tenantId>/CloudSyncCustomExtensionsApp`と、Microsoft Entra Connect によって作成された[テナント スキーマ拡張機能アプリ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions#configuration-changes-in-azure-ad-made-by-the-wizard)を持つアプリケーションでのみサポートされます。

スキーマを拡張し、クラウド同期プロビジョニングでディレクトリ拡張機能属性を使用してActive Directoryする手順の例については、「Active Directory[へのプロビジョニング時にディレクトリ拡張機能を使用する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-directory-extension-group-provisioning)」を参照してください。 この記事では、ユーザーとグループの両方について説明します。

### ディレクトリ拡張機能を作成する方法

Microsoft Entra ID では、いくつかの異なる方法でディレクトリ拡張機能を作成できます。 次の表に、リンクと追加情報を示します。

| メソッド | 説明 | URL |
| --- | --- | --- |
| Microsoft Graph | Microsoft Graph を使用して拡張機能を作成する | [extensionProperty を作成する](https://learn.microsoft.com/ja-jp/graph/api/application-post-extensionproperty?view=graph-rest-1.0&tabs=http&preserve-view=true) |
| PowerShell | PowerShell を使用して拡張機能を作成する | [New-MgApplicationExtensionProperty](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgapplicationextensionproperty) (新しいMgアプリケーション拡張プロパティ) |
| Microsoft Entra Connect | Microsoft Entra Connect を使用して拡張機能を作成する | [Microsoft Entra Connect を使用して拡張属性を作成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping#create-an-extension-attribute-using-azure-ad-connect) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/device-sync"} -->
## Microsoft Entra Cloud Sync を使用してデバイス同期を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/device-sync
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-09-29
- Summary: Active Directory コンピューター オブジェクトをMicrosoft Entra IDに同期するように Microsoft Entra Cloud Sync を構成する方法について説明します。

デバイス同期を使用すると、`AD2AADDeviceSync` Cloud Sync 構成で  ジョブを使用して、コンピューター オブジェクトを Active Directory (AD) から Microsoft Entra ID に同期できます。 同期後、デバイスは Microsoft Entra にハイブリッド参加できるようになります。

デバイス同期は既定で無効になっています。 開始する前に、環境が前提条件を満たしていることを確認します。 次に、サービス接続ポイント (SCP) を構成し、デバイス同期を有効にし、オンデマンドでデバイスをプロビジョニングし、Microsoft Graphを使用してデバイスの同期を管理し、削除されたデバイスを回復します。

### 前提条件

環境が次の要件を満たしていることを確認します。

- オンプレミス環境に [Microsoft Entra プロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)をインストールします。 エージェントはバージョン 1.1.1107 以降である必要があります。 Microsoft Entra 管理センターから利用可能な最新のエージェント バージョンをダウンロードします。 エージェントのバージョンについては、[Microsoft Entraプロビジョニング エージェントのリリース履歴を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history)参照してください。
- **AD から Microsoft Entra ID への**クラウド同期構成を作成します。 構成手順については、「[Active DirectoryからMicrosoft Entra IDへのプロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)」を参照してください。
- Microsoft Entraテナント ID と、デバイスが認証に使用する検証済みドメイン。 フェデレーション環境の場合は、フェデレーション ドメインを使用します。 それ以外の場合は、プライマリ `*.onmicrosoft.com` ドメインを使用します。
- SCP を構成する必要がある場合は、ドメインに参加しているコンピューターを含む各 AD フォレストの **Enterprise Admins** グループのメンバーであるアカウントに一時的にアクセスします。 組織の Just-In-Time 特権アクセス プロセス (使用可能な場合) を使用します。
- Microsoft Entra 管理センターでデバイス同期を構成するには、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持つアカウントを使用します。

### サービス接続ポイントを構成する

ドメインに参加しているコンピューターを含む各 AD フォレストで SCP を構成します。 デバイスは SCP を使用して、登録するMicrosoft Entra テナントを検出します。

SCP は、フォレスト内で既に構成されている可能性があります。 スクリプトを実行する前に、 [既存の SCP 値を確認](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-manual#configure-a-service-connection-point)し、現在の `azureADName` と `azureADId` の値を記録して、必要に応じて復元できるようにします。 値が既に目的のテナントを識別している場合は、「 デバイス同期を有効にする」に進みます。

Warning

SCP が既に存在する場合、スクリプトは現在の `keywords` 値をクリアし、指定された `azureADName` と `azureADId` 値に置き換えます。 ドメインとテナント ID を実行する前に確認します。

SCP を構成するには、次の手順に従います。

1. 現在のフォレストの **Enterprise Admins** に所属するアカウントを使用して、ドメイン参加済みの管理ワークステーションまたはドメイン コントローラーにサインインします。 組織のポリシーでローカル スクリプトが許可されている Windows PowerShell セッションを使用します。
2. 次のスクリプトを `ConfigureSCP.ps1`として保存します。

    ```powershell
    #
    # ConfigureSCP.ps1
    # Configures the service connection point (SCP) for Microsoft Entra hybrid join in the current forest.
    #
    # REQUIREMENT: Must be run by an Enterprise Admin of the current forest.
    #
    # EXAMPLES:
    #   .\ConfigureSCP.ps1 -Domain contoso.com -TenantId <guid>
    #   .\ConfigureSCP.ps1 -Domain contoso.onmicrosoft.com -TenantId <guid>
    #
    [CmdletBinding()]
    param(
        # Verified domain used for device authentication.
        # If you use federation, enter a federated domain name.
        # Otherwise, enter your primary *.onmicrosoft.com domain name.
        [Parameter(Mandatory = $true)]
        [ValidateNotNullOrEmpty()]
        [string]$Domain,
    
        # Microsoft Entra tenant identifier (GUID).
        [Parameter(Mandatory = $true)]
        [guid]$TenantId
    )
    
    $ErrorActionPreference = "Stop"
    
    Write-Output "Configuring the SCP for Microsoft Entra hybrid join in your Active Directory forest."
    
    try {
        ## Set variables
        $azureADName = "azureADName:" + $Domain.Trim()
        $azureADId   = "azureADId:" + $TenantId.ToString()
        $keywords    = "keywords"
        $ldap        = "LDAP://"
    
        $rootDSE    = New-Object System.DirectoryServices.DirectoryEntry($ldap + "RootDSE")
        $configCN   = $rootDSE.Properties["configurationNamingContext"][0].ToString()
        $servicesCN = "CN=Services," + $configCN
        $drcCN      = "CN=Device Registration Configuration," + $servicesCN
        $scpCN      = "CN=62a0ff2e-97b9-4513-943f-0d221bd30080," + $drcCN
    
        ## Get/Create: CN=Device Registration Configuration,CN=Services
        if ([System.DirectoryServices.DirectoryEntry]::Exists($ldap + $drcCN)) {
            $deDRC = New-Object System.DirectoryServices.DirectoryEntry($ldap + $drcCN)
        }
        else {
            $de    = New-Object System.DirectoryServices.DirectoryEntry($ldap + $servicesCN)
            $deDRC = $de.Children.Add("CN=Device Registration Configuration", "container")
            $deDRC.CommitChanges()
        }
    
        ## Edit/Create: CN=62a0ff2e-97b9-4513-943f-0d221bd30080,CN=Device Registration Configuration,CN=Services
        if ([System.DirectoryServices.DirectoryEntry]::Exists($ldap + $scpCN)) {
            $deSCP = New-Object System.DirectoryServices.DirectoryEntry($ldap + $scpCN)
            $deSCP.Properties[$keywords].Clear()
        }
        else {
            $deSCP = $deDRC.Children.Add("CN=62a0ff2e-97b9-4513-943f-0d221bd30080", "serviceConnectionPoint")
        }
    
        $deSCP.Properties[$keywords].Add($azureADName) | Out-Null
        $deSCP.Properties[$keywords].Add($azureADId)   | Out-Null
        $deSCP.CommitChanges()
    
        Write-Output "Configuration complete!"
    }
    catch {
        Write-Output "Configuration could not be completed."
        Write-Output $_
        exit 1
    }
    ```
3. 目的のテナントの値を使用してスクリプトを実行します。

    - `Domain`の場合は、フェデレーション環境にフェデレーション ドメインを使用します。 それ以外の場合は、プライマリ `*.onmicrosoft.com` ドメインを使用します。
    - `TenantId`の場合は、Microsoft Entra テナント GUID を使用します。

    ```powershell
    .\ConfigureSCP.ps1 -Domain <verified-domain> -TenantId <tenant-id>
    ```
4. PowerShell に `Configuration complete!`が表示されることを確認します。 `Configuration could not be completed.`が表示される場合は、付随するエラーを確認し、解決してから続行してください。
5. ドメインに参加しているコンピューターを含む追加の AD フォレストごとに、既存の SCP 値を確認し、必要に応じてこの手順を繰り返します。
6. 必要なすべてのフォレストを構成したら、 **エンタープライズ管理者** アカウントからサインアウトし、組織の特権アクセス プロセスに従って一時的なアクセスを削除または非アクティブ化します。

### デバイス同期を有効にする

既存の**AD から Microsoft Entra ID への** Cloud Sync 構成でデバイス同期を有効にします。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    [Image: Microsoft Entra Connect Cloud Sync ホーム ページを示すスクリーンショット。]

1. 既存の**AD から Microsoft Entra ID への**構成を選択します。
2. **[プロパティ]** を選択します。 [ **基本**] で、[ **デバイスの同期** ] が **[無効]** になっていることを確認します。

    [Image: デバイス同期が無効になっている Cloud Sync 構成プロパティのスクリーンショット。]
3. [ **基本**] の横にある編集アイコンを選択します。
4. [ **デバイス同期を有効にする]** を選択し、[ **適用**] を選択します。

    [Image: デバイス同期が有効になっている Cloud Sync の基本設定のスクリーンショット。]

### 同期されたデバイス属性を確認する

Cloud Sync デバイス同期では、次のデバイス属性マッピングがサポートされています。

| Microsoft Entra 属性 | AD 属性 | マッピングの種類 |
| --- | --- | --- |
| `AccountEnabled` | `userAccountControl` | Expression |
| `DeviceId` | `objectGUID` | 直接 |
| `DeviceOSType` | `operatingSystem` | Expression |
| `DeviceTrustType` | なし。 値は常に `ServerAd`。 | Expression |
| `DisplayName` | `displayName`、`dNSHostName` | Expression |
| `OnPremiseSecurityIdentifier` | `objectSid` | 直接 |
| `RegisteredOwnerReference` | `mS-DS-CreatorSID` | ある時 |
| `SourceAnchor` | `objectGUID` | 直接 |
| `UserCertificate` | `userCertificate` | 直接 |

`RegisteredOwnerReference` マッピングは、Cloud Sync が AD でコンピューター オブジェクトを初めて検出したときにのみ適用されます。

### オンデマンドでデバイスをプロビジョニングする

特定の AD コンピューター オブジェクトに対してデバイス同期を実行するために、デバイスをオンデマンドでプロビジョニングします。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    [Image: Microsoft Entra Connect Cloud Sync ホーム ページを示すスクリーンショット。]

1. 既存の**AD から Microsoft Entra ID への**構成を選択します。
2. **オンデマンドプロビジョン** を選択します。
3. [ **デバイス** ] タブを選択します。
4. AD コンピューターの識別名を入力します。
5. **プロビジョン** を選択します。

### Microsoft Graphを使用してデバイス同期を管理する

Microsoft Graphを使用して、デバイス同期ジョブを作成して開始したり、オンデマンドでデバイスをプロビジョニングしたりできます。

#### デバイス同期ジョブを作成して開始する

1. [Create synchronizationJob を](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronization-post-jobs?tabs=http)使用して、デバイス同期ジョブを作成します。
2. リクエスト内:

    - **AD のサービス プリンシパル ID を、Microsoft Entra ID** のサービス プリンシパルに使用します。
    - `templateId` を `AD2AADDeviceSync` に設定します。
3. [Start synchronizationJob を](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-start?tabs=http)使用して、デバイス同期ジョブを開始します。

#### Microsoft Graphを使用してオンデマンドでデバイスをプロビジョニングする

1. デバイス同期ジョブで [Get synchronizationSchema](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationschema-get?tabs=http) を使用します。
2. 応答で、 `synchronizationRules`からデバイス同期規則 ID を取得します。
3. [synchronizationJob: provisionOnDemand を](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-provisionondemand?tabs=http)使用します。 要求本文で、次の値を設定します。

    - 「Active Directory ユーザーとコンピューター」から、コンピューターの識別名に `objectId` を追加します。
    - `objectTypeName` から `computer`。
    - `ruleId` をデバイス同期規則 ID に追加します。

### 削除されたデバイスを回復する

デバイスは、次の場合にMicrosoft Entra IDから削除できます。

- 対応するコンピューターが AD で削除され、Cloud Sync によって Microsoft Entra ID 内のデバイスが削除されます。
- `dsregcmd /leave`の実行後、デバイスはクラウドから参加解除されるため、Azure Device Registration Service はMicrosoft Entra ID内のデバイスを削除します。
- 管理者は、Microsoft Entra 管理センターの **Entra ID**&gt;**Devices** からデバイスを手動で削除します。

Cloud Sync 以外のサービスまたは管理者がデバイスを削除した場合、対応する AD コンピューター オブジェクトが変更されない限り、Cloud Sync は次の同期サイクル中にデバイスを再作成しません。

次のいずれかのオプションを使用して、デバイスを回復し、ハイブリッド結合状態を復元します。

- Microsoft Entra 管理センターで、**Entra ID**&gt;**Devices**&gt;**削除済みのデバイス**に移動し、デバイスを復元します。
- AD で対応するコンピューター オブジェクトを変更し、次のデバイス同期サイクルを待ちます。
- デバイスをオンデマンドでプロビジョニングします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/exchange-hybrid"} -->
## クラウド同期を使用した Exchange ハイブリッド 書き戻し - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/exchange-hybrid
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-25
- Summary: この記事では、Exchange ハイブリッド ライトバック シナリオを有効にする方法について説明します。

Exchange ハイブリッド展開を使用すると、組織は、既存のオンプレミスの Microsoft Exchange 組織に対して持っている機能豊富なエクスペリエンスと管理制御をクラウドに拡張できます。 ハイブリッド展開は、オンプレミスの Exchange 組織と Exchange Online の間の単一の Exchange 組織のシームレスな外観を提供します。

[Image: Exchange ハイブリッド シナリオの概念図。]

このシナリオは、クラウド同期でサポートされるようになりました。クラウド同期では、Exchange のオンプレミス スキーマ属性が検出され、Exchange のオンライン属性がオンプレミスの AD 環境に "書き戻されます" というメッセージが表示されます。

Exchange ハイブリッド展開の詳細については、「 [Exchange ハイブリッド](https://learn.microsoft.com/ja-jp/exchange/exchange-hybrid)」を参照してください。

### 前提 条件

クラウド同期を使用して Exchange Hybrid を展開する前に、次の前提条件を満たす必要があります。

- [プロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-provisioning-agent)は、バージョン 1.1.1107.0 以降である必要があります。
- Exchange スキーマを含めるために、オンプレミスの Active Directory を拡張する必要があります。
    - Exchange のスキーマを拡張するには、「Exchange [Server 用に Active Directory とドメインを準備する](https://learn.microsoft.com/ja-jp/exchange/plan-and-deploy/prepare-ad-and-domains?view=exchserver-2019&preserve-view=true)」を参照してください

    手記

    プロビジョニング エージェントをインストールした後にスキーマが拡張されている場合は、スキーマの変更を取得するために、スキーマを再起動する必要があります。

### 有効にする方法

Exchange ハイブリッド ライトバックは既定で無効になっています。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. 既存の構成を選択します。
2. 上部にある **[プロパティ**] を選択します。 Exchange ハイブリッドの書き戻しが無効になっているはずです。
3. **[基本**] の横にある鉛筆を選択します。 [Image: 基本的なプロパティのスクリーンショット。]
4. 右側で、[**Exchange ハイブリッドの書き戻し**] チェック ボックスをオンにし、[ **適用**] を選択します。 [Image: Exchange の書き戻しの有効化のスクリーンショット。]

手記

**Exchange ハイブリッド ライトバック**のチェック ボックスが無効になっている場合は、スキーマが検出されていないことを意味します。 前提条件が満たされていること、およびプロビジョニング エージェントを再び開始したことを確認します。

### 同期された属性

クラウド同期では、Exchangeハイブリッド シナリオを有効にするために、Exchange Online属性がユーザーに書き戻されます。

#### Entra2ADExchangeOnlineAttributeWriteback (LES Writeback)

Exchange Online を正とする属性書き戻し (LES 書き戻し) のシナリオについては、[ハイブリッド環境におけるリモート メールボックスの Exchange 属性のクラウドベース管理](https://learn.microsoft.com/ja-jp/exchange/hybrid-deployment/enable-exchange-attributes-cloud-management)を参照してください。

このシナリオでは、Exchange Onlineが特定のExchange関連ユーザー属性の信頼できるソースであり、クラウド同期によって、クラウド管理属性がオンプレミスの Active Directoryに書き戻されます。

これは、Exchange **ハイブリッド ライトバック** (AAD2ADExchangeHybridWriteback) とは異なります。これは、プレビュー シナリオで使用されるハイブリッド ライトバック テンプレートに従います。

次の表に、サポートされている属性と LES ライトバックのマッピングを示します。

| Microsoft Entra 属性 | AD 属性 | オブジェクト クラス | マッピングの種類 |
| --- | --- | --- | --- |
| クラウドアンカー | msDS-ExternalDirectoryObjectId | User、InetOrgPerson | 直接 |
| cloudLegacyExchangeDN | プロキシアドレス | User、Contact、InetOrgPerson | 表現 |
| cloudMSExchArchiveStatus | msExchArchiveStatus | User、InetOrgPerson | 直接 |
| cloudMSExchBlockedSendersHash | msExchBlockedSendersHash | User、InetOrgPerson | 表現 |
| cloudMSExchSafeRecipientsHash | msExchSafeRecipientsHash | User、InetOrgPerson | 表現 |
| cloudMSExchSafeSendersHash | msExchSafeSendersHash | User、InetOrgPerson | 表現 |
| cloudMSExchUCVoiceMailSettings | msExchUCVoiceMailSettings | User、InetOrgPerson | 表現 |
| cloudMSExchUserHoldPolicies | msExchUserHoldPolicies | User、InetOrgPerson | 表現 |

### オンデマンドプロビジョニング

Exchange ハイブリッド ライトバックを使用してオンデマンドでプロビジョニングするには、2 つの手順が必要です。 まず、ユーザーをプロビジョニングまたは作成する必要があります。 Exchange Online では、ユーザーに必要な属性が設定されます。 その後、クラウド同期では、これらの属性をユーザーに "書き戻す" ことができます。 手順は次のとおりです。

- 初期ユーザーをプロビジョニングして同期します。これにより、ユーザーはクラウドに移行され、Exchange オンライン属性を設定できるようになります。
- Exchange 属性を Active Directory に書き戻します。これにより、Exchange オンライン属性がオンプレミスのユーザーに書き込まれます。

Exchange ハイブリッドを使用したオンデマンド プロビジョニングでは、次の手順を使用します。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. 左側で**オンデマンドプロビジョン**を選択します。
3. ユーザーの識別名を入力し、[ **プロビジョニング** ] ボタンを選択します。
4. 成功画面に 4 つの緑色のチェック マークが表示されます。
5. [ **次へ**] を選択します。 [ **Active Directory への書き戻し交換属性** ] タブで、同期が開始されます。
6. 成功の詳細が表示されます。 [Image: 書き戻される Exchange 属性のスクリーンショット。]

    手記

    この最後の手順は、完了するまでに最大 2 分かかる場合があります。

### MS Graph を使用した Exchange ハイブリッド書き戻し

MS Graph API を使用して、Exchange ハイブリッド ライトバックを有効にすることができます。 詳細については、「[MS Graph を使用した Exchange ハイブリッド書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-inbound-synch-ms-graph#exchange-hybrid-writeback-public-preview)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/gmsa-cloud-sync"} -->
## Microsoft Entra クラウド同期でグループ管理サービス アカウントを使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/gmsa-cloud-sync
- Service: entra-id / hybrid-connect
- Article date: 2025-09-29
- Summary: このドキュメントではクラウドの同期で gMSA アカウントを使用する方法を詳しく説明します

グループ管理サービス アカウントは、パスワードの自動管理、簡略化されたサービス プリンシパル名 (SPN) の管理、および管理を他の管理者に委任する機能を提供し、またこの機能を複数のサーバーに拡張する、マネージド ドメイン アカウントです。 Microsoft Entra クラウド同期では、エージェントを実行するための gMSA がサポートされ、使用されています。 インストーラーに新しいアカウントの作成を許可するか、カスタム アカウントを指定するかを選択できます。 このアカウントを作成するため、またはカスタム アカウントを使用している場合はアクセス許可を設定するために、セットアップ中に管理資格情報の入力を求められます。 インストーラーによってアカウントが作成された場合、アカウントは `domain\provAgentgMSA$` として表示されます。 gMSA の詳細については、[グループ管理サービス アカウント](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/group-managed-service-accounts-overview)に関するページを参照してください。

### gMSA の前提条件

- gMSA ドメインのフォレスト内の Active Directory スキーマを Windows Server 2012 以降に更新する必要があります。
- ドメイン コントローラー上の [PowerShell RSAT モジュール](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-server-administration-tools)。
- ドメイン内の少なくとも 1 つのドメイン コントローラーで Windows Server 2012 以降が実行されている必要があります。
- エージェントのインストール用に Windows Server 2022、Windows Server 2019、または Windows Server 2016 を実行するドメイン参加済みサーバー。

### gMSA アカウントに設定されたアクセス許可 (すべてのアクセス許可)

インストーラーが gMSA アカウントを作成する場合は、そのアカウントに**すべて**のアクセス許可が設定されます。 次の表で、これらのアクセス許可について詳しく説明しています

#### MS-DS-Consistency-Guid

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| Allow | &lt;gMSA アカウント&gt; | mS-Ds-ConsistencyGuid 書き込みプロパティ | ユーザーの子孫オブジェクト |
| Allow | &lt;gMSA アカウント&gt; | mS-Ds-ConsistencyGuid 書き込みプロパティ | グループの子孫オブジェクト |

関連付けられているフォレストが Windows Server 2016 環境でホストされている場合、NGC キーと STK キーに対する次のアクセス許可が含まれます。

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| Allow | &lt;gMSA アカウント&gt; | msDS-KeyCredentialLink 書き込みプロパティ | ユーザーの子孫オブジェクト |
| Allow | &lt;gMSA アカウント&gt; | msDS-KeyCredentialLink 書き込みプロパティ | デバイスの子孫オブジェクト |

#### パスワード ハッシュの同期

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| Allow | &lt;gMSA アカウント&gt; | ディレクトリの変更のレプリケート | このオブジェクトのみ (ドメインのルート) |
| Allow | &lt;gMSA アカウント&gt; | ディレクトリの変更すべてのレプリケート | このオブジェクトのみ (ドメインのルート) |

#### パスワード ライトバック

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| Allow | &lt;gMSA アカウント&gt; | パスワードのリセット | ユーザーの子孫オブジェクト |
| Allow | &lt;gMSA アカウント&gt; | プロパティ lockoutTime の書き込み | ユーザーの子孫オブジェクト |
| Allow | &lt;gMSA アカウント&gt; | プロパティ pwdLastSet の書き込み | ユーザーの子孫オブジェクト |
| Allow | &lt;gMSA アカウント&gt; | 無期限パスワード | このオブジェクトのみ (ドメインのルート) |

#### グループの書き戻し

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| Allow | &lt;gMSA アカウント&gt; | 汎用の読み取り/書き込み | オブジェクトの種類のグループとサブオブジェクトのすべての属性 |
| Allow | &lt;gMSA アカウント&gt; | 子オブジェクトの作成/削除 | オブジェクトの種類のグループとサブオブジェクトのすべての属性 |
| Allow | &lt;gMSA アカウント&gt; | 削除/ツリー オブジェクトの削除 | オブジェクトの種類のグループとサブオブジェクトのすべての属性 |

#### Exchange ハイブリッドのデプロイ

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| Allow | &lt;gMSA アカウント&gt; | すべてのプロパティの読み取り/書き込み | ユーザーの子孫オブジェクト |
| Allow | &lt;gMSA アカウント&gt; | すべてのプロパティの読み取り/書き込み | InetOrgPerson の子孫オブジェクト |
| Allow | &lt;gMSA アカウント&gt; | すべてのプロパティの読み取り/書き込み | グループの子孫オブジェクト |
| Allow | &lt;gMSA アカウント&gt; | すべてのプロパティの読み取り/書き込み | 連絡先の子孫オブジェクト |

#### Exchange メールのパブリック フォルダー

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| Allow | &lt;gMSA アカウント&gt; | すべてのプロパティの読み取り | パブリック フォルダーの子孫オブジェクト |

#### ユーザーグループ作成削除 (CloudHR)

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| Allow | &lt;gMSA アカウント&gt; | 汎用書き込み | オブジェクトの種類のグループとサブオブジェクトのすべての属性 |
| Allow | &lt;gMSA アカウント&gt; | 子オブジェクトの作成/削除 | オブジェクトの種類のグループとサブオブジェクトのすべての属性 |
| Allow | &lt;gMSA アカウント&gt; | 汎用書き込み | オブジェクト型のユーザーとサブオブジェクトのすべての属性 |
| Allow | &lt;gMSA アカウント&gt; | 子オブジェクトの作成/削除 | オブジェクト型のユーザーとサブオブジェクトのすべての属性 |

### カスタムの gMSA アカウントの使用

カスタム gMSA アカウントを作成する場合、インストーラーはカスタム アカウントに**すべての**アクセス許可を設定します。

gMSA アカウントを使用するように既存のエージェントをアップグレードする手順については、「[グループ管理サービス アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install#group-managed-service-accounts)」を参照してください。

グループの管理されたサービス アカウント用の Active Directory を準備する方法について詳しくは、「[グループの管理されたサービス アカウントの概要](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/group-managed-service-accounts-overview)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/govern-on-premises-groups"} -->
## クラウドからのグループを使用してオンプレミスの Active Directory Domain Services (Kerberos) アプリケーション アクセスを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-09-04
- Summary: この記事では、クラウド同期を使用して、オンプレミスのアプリケーション アクセスをグループを使用して管理する方法の概要について説明します。

重要

Microsoft Entra Connect Sync でのグループ ライトバック v2 のプレビューは非推奨となり、サポートされなくなりました。

Microsoft Entra Cloud Sync を使用して、クラウド セキュリティ グループをオンプレミスの Active Directory Domain Services (AD DS) にプロビジョニングできます。

Microsoft Entra Connect Sync でグループ ライトバック v2 を使用する場合は、同期クライアントを Microsoft Entra Cloud Sync に移動する必要があります。Microsoft Entra Cloud Sync に移行する資格があるかどうかを確認するには、ユーザー同期ウィザードを使用します。

ウィザードで推奨されているように Microsoft Cloud Sync を使用できない場合は、Microsoft Entra Connect Sync と並行して Microsoft Entra Cloud Sync を実行できます。その場合は、Microsoft Entra Cloud Sync を実行して、クラウド セキュリティ グループをオンプレミスの AD DS にプロビジョニングするだけです。

MICROSOFT 365 グループを AD DS にプロビジョニングする場合は、グループ ライトバック v1 を引き続き使用できます。

この記事では、Active Directory Domain Services (AD DS) と統合されたオンプレミス アプリケーションに Microsoft Entra ID ガバナンスを使用するシナリオについて説明します。

**対象となるシナリオ:** クラウドからプロビジョニングされ、クラウドで管理される Active Directory グループを使用して、オンプレミス アプリケーションを管理します。 Microsoft Entra Cloud Sync を使用すると、Microsoft Entra ID ガバナンス機能を利用してアクセス関連の要求を制御および修復しながら、AD DS でのアプリケーションの割り当てを完全に管理できます。

AD DS と統合されていないアプリケーションを管理する方法の詳細については、「 [環境内のアプリケーションのアクセス権を管理する」を参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare)。

### サポートされるシナリオ

ユーザーが Windows 認証を使用する Active Directory アプリケーションに接続できるかどうかを制御する場合は、アプリケーション プロキシと Microsoft Entra セキュリティ グループを使用できます。 アプリケーションで Kerberos またはライトウェイト ディレクトリ アクセス プロトコル (LDAP) を使用してユーザーの AD DS グループ メンバーシップを確認する場合は、Cloud Sync グループ プロビジョニングを使用して、ユーザーがアプリケーションにアクセスする前にそれらのグループ メンバーシップを持っていることを確認できます。

以降のセクションでは、Cloud Sync グループのプロビジョニングでサポートされる 3 つのオプションについて説明します。 これらのシナリオ オプションは、アプリケーションに割り当てられているユーザーがアプリケーションに対して認証するときにグループ メンバーシップを持っていることを確実にするためのものです。

- Microsoft Entra Connect Sync または Microsoft Entra Cloud Sync を使用して、Microsoft Entra ID に同期される AD DS のグループの機関ソース (SOA) を変換します。
- 新しいグループを作成し、アプリケーションが既に存在する場合は更新して、新しいグループを確認します
- 新しいグループを作成し、既存のグループを更新します。アプリケーションがチェックされ、新しいグループがメンバーとして含まれるようにしました

開始する前に、ご自分がアプリケーションがインストールされているドメインのドメイン管理者であることを確認します。 ドメイン コントローラーにサインインできること、または AD DS [管理用のリモート サーバー管理ツール](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/system-management-components/remote-server-administration-tools) が Windows PC にインストールされていることを確認します。

Microsoft Entra ID のアプリケーション プロキシ サービスを使うと、ユーザーは Microsoft Entra アカウントでサインインして、オンプレミスのアプリケーションにアクセスできます。 アプリ プロキシを構成する方法の詳細については、「 [Microsoft Entra ID でアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)する」を参照してください。

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

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-provisioning-to-active-directory-works"} -->
## Microsoft Entra プロビジョニング動作 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-provisioning-to-active-directory-works
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-19
- Summary: クラウド同期Microsoft Entra、Active Directoryでユーザー、グループ、メンバーシップの照合、作成、更新、無効化、削除を行う方法について説明します。

この記事では、Microsoft Entra IDからオンプレミス Active Directory Domain Services (AD DS) に**ユーザー、グループ、メンバーシップ**をプロビジョニングするときに、Microsoft Entra Cloud Sync プロビジョニング エンジンがオブジェクトを処理する方法について説明します。 この動作を理解すると、デプロイの計画、作成、更新、または削除の動作を予測し、予期しない結果のトラブルシューティングを行うのに役立ちます。

この機能とサポートされるシナリオの概要については、[Microsoft Entra IDからActive Directoryへのプロビジョニングの概要に関するページを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory)参照してください。

### プロビジョニング パイプライン

Microsoft Entra IDは権限のソースです。 同期サイクルごとに、プロビジョニング サービスは次の処理を実行します:

1. **スコープを読み込みます。**[スコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#configure-scoping-filters)に基づいて、スコープ内のユーザーとグループが決定されます。
2. **一致します。** スコープ内のオブジェクトごとに、更新する既存の AD オブジェクトが検索されます。 詳細については、「 照合のしくみ」を参照してください。
3. **属性マッピングを適用します。** 構成された属性マッピングを使用して、Microsoft Entra ID属性を AD 属性に変換[します](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#configure-attribute-mapping)。
4. **書き込み。** Cloud Sync プロビジョニング エージェントを使用して AD オブジェクトを作成、更新、または削除します。これにより、要求がライトウェイト ディレクトリ アクセス プロトコル (LDAP) 操作に変換されます。

プロビジョニング サービスは、変更を段階的に処理します。 最後のサイクル以降に変更されたオブジェクトのみが評価され、デルタ サイクルが高速に保たれます。

### 照合のしくみ

エンジンは名前や識別名ではなく永続的なアンカーを使用するため、リンクを壊さずにオブジェクトの名前を変更したり移動したりできます。

- プロビジョニング サービスは、プロビジョニングするすべてのオブジェクトに **`msDS-ExternalDirectoryObjectId`** を付与します（ユーザーには `Group_<objectId>`、グループには `User_<objectId>`）。 この属性は、Microsoft Entra ID オブジェクトとそれに対応する AD オブジェクトの間の結合アンカーです。
- エンジンは、各サイクルで、ソース オブジェクトと一致する `msDS-ExternalDirectoryObjectId`を持つ AD オブジェクトをターゲット ドメインで検索します。
    - 既存の AD オブジェクト→**一致が検出**され、その場で**更新**されます。
    - ターゲット コンテナーに**新しい** AD オブジェクトが**作成**→**一致するものが見つかりません**。

この照合してから作成する動作により、プロビジョニングは冪等になります。 サイクルを再実行しても、同じMicrosoft Entra ID ID に対して重複するオブジェクトは作成されません。

Note

`msDS-ExternalDirectoryObjectId`および関連するアンカー属性はサービスによって管理され、Microsoft Entra 管理センターでは編集できません。

### ユーザーのプロビジョニング方法

- **クラウドネイティブ ユーザー** は、ターゲット コンテナーに新しい AD ユーザー オブジェクトとして作成されます。
- **ソース オブ オーソリティ (SOA) がクラウドに変換されたユーザーは** 、既存の AD オブジェクトと照合され、その場で更新されます。 **元の組織単位 (OU) は**既定のターゲット コンテナー式によって保持されるため、クラウドの更新はユーザーの元の場所に戻ります。 詳細については、「 [ターゲット コンテナーの構成」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#configure-the-target-container)参照してください。
- **企業間 (B2B) ゲスト ユーザー** は、必要に応じて AD にプロビジョニングできます。
- **ID の継続性** は、SOA に変換されたユーザーのセキュリティ識別子 (SID) などのキー属性を保持することによって維持されます。

ユーザーのオンプレミスの場所は、その属性が設定されるときに `onPremisesDistinguishedName` 属性から派生するため、構成するターゲット コンテナーは、まだ存在しないユーザーにのみ適用されます。 既にプロビジョニングされているユーザーを移動するには、「 [プロビジョニングされたユーザーを別の組織単位に移動する」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#move-a-provisioned-user-to-a-different-organizational-unit)参照してください。

#### ユーザー SOA シナリオ

次の表では、両方のプロビジョニングの方向について、ユーザーの権限のソースに基づいてユーザーをプロビジョニングする方法について説明します。

| 利用シーン | ユーザー SOA | 同期方向 | 同期処理のしくみ |
| --- | --- | --- | --- |
| AD アカウントを持たないクラウドネイティブ ユーザー | 雲 | Microsoft Entra ID から AD | 構成したターゲット コンテナーに新しい AD ユーザーを作成します。 |
| SOA に変換されたユーザー | 雲 | Microsoft Entra ID から AD | 既存の AD ユーザーと一致し、その場で更新します。 元の OU と SID は保持されます。 |
| B2B ゲスト ユーザー | 雲 | Microsoft Entra ID から AD | ゲストがスコープ内にあるときに AD ユーザーを作成します。 |
| 同期されたユーザー | オンプレミス | Microsoft Entra ID から AD | ユーザーをプロビジョニング**しません**。 Active Directoryは引き続き権限を持っています。 |
| 同期されたユーザー | オンプレミス | AD から Microsoft Entra ID へ | ユーザーを Microsoft Entra ID にプロビジョニングします。 |
| SOA に変換されたユーザー | 雲 | AD から Microsoft Entra ID へ | ユーザーをプロビジョニング**しません**。 クラウドが権限を持っているため、AD で直接行われた変更はスキップされます。 |

#### オンプレミスの属性がMicrosoft Entra IDに書き戻される

Cloud Sync は、ユーザーをActive Directoryにプロビジョニングすると、結果として得られるオンプレミスの値をMicrosoft Entra IDのユーザーのオブジェクトに書き戻します。

| 特性 | 書き戻された値 |
| --- | --- |
| `onPremisesDistinguishedName` | OU パスを含む AD ユーザー オブジェクトの識別名 (DN)。 |
| `onPremisesSamAccountName` | AD ユーザー オブジェクトに割り当てられた SAM アカウント名。 |
| `onPremisesSecurityIdentifier` | AD ユーザー オブジェクトのセキュリティ識別子 (SID)。 |
| `onPremisesUserPrincipalName` | AD ユーザー オブジェクトのユーザー プリンシパル名 (UPN)。 |
| `onPremisesDomainName` | ユーザーがプロビジョニングされている AD ドメイン。 |

このライトバックは、クラウドネイティブ ユーザーが最初のプロビジョニング サイクルの後に `onPremisesDistinguishedName` 値を持つ理由です。

#### 属性の更新

Microsoft Entra ID で行われた属性の変更は、次回のサイクルで AD に反映されます。 **クラウドが信頼できるソース**であるため、AD で管理されている属性に直接加えられた変更は、次の同期サイクル中に上書きされる可能性があります。

### グループとメンバーシップのプロビジョニング方法

- **セキュリティ グループ**のみがプロビジョニングされます。 メールが有効なグループと配布グループはサポートされていません。
- グループは **ユニバーサル** セキュリティ グループとしてプロビジョニングされ、同じ `msDS-ExternalDirectoryObjectId` アンカーによって照合されます。
- **メンバーシップ** はメンバーごとに解決されます。 メンバー参照は、そのメンバーが AD アカウントを持っている場合にのみ書き込まれます。 クラウドで管理されるメンバーは、構成によってユーザーもプロビジョニングされ、そのメンバーがスコープ内にある場合に 1 つを取得します。 親グループ自体がプロビジョニングされるかどうかは、その権限のソースによって異なります。
- **オンプレミス ユーザーへのメンバーシップのプロビジョニング** は既定ではオフになっており、スコープ ウィザードで明示的に有効にする必要があります。 [オンプレミス ユーザーのグループ メンバーシップを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#group-membership-to-on-premises-users)参照してください。
- 対象となるメンバーを持たないスコープ内グループは、 **空のメンバーシップ**でプロビジョニングされます。

#### グループ メンバーシップ SOA のシナリオ

オブジェクトは、プロビジョニングする前にスコープ内に存在する必要があり、ユーザーとグループに同様に適用されます。 メンバー参照は、メンバーが参照する AD アカウントを持っている場合にのみ書き込まれます。 オンプレミスの同期されたメンバーには、既にメンバーが存在します。 クラウド管理メンバーは、構成でユーザーもプロビジョニングされ、そのメンバーがスコープ内にある場合にのみ 1 つを取得します。 そのため、両方をプロビジョニングする構成で、メンバーがプロビジョニングするユーザーであるグループを選択します。 グループのみの構成では、クラウドで管理されるメンバーには AD アカウントがないため、メンバーシップ参照を書き込むことができません。

次の表では、ソースがMicrosoft Entra IDされたときにグループ メンバーシップをプロビジョニングする方法について説明します。

| Configuration | 親グループ SOA | メンバー ユーザー SOA | 同期処理のしくみ |
| --- | --- | --- | --- |
| **グループのみ** | 雲 | オンプレミス | すべてのメンバー参照を使用してグループをプロビジョニングします。 メンバーには、ディレクトリ同期の AD アカウントが既に存在します。 |
| **グループのみ** | 雲 | 雲 | メンバー参照なしでグループをプロビジョニングします。 |
| **グループのみ** | 雲 | Mixed | グループをプロビジョニングし、オンプレミスメンバーのみのメンバー参照を含めます。 |
| **ユーザーとグループ** | 雲 | オンプレミス | すべてのメンバー参照を使用してグループをプロビジョニングします。 |
| **ユーザーとグループ** | 雲 | 雲 | グループをプロビジョニングし、スコープ内のメンバーごとに AD アカウントを作成し、すべてのメンバー参照を書き込みます。 |
| **ユーザーとグループ** | 雲 | Mixed | すべてのメンバー参照を使用してグループをプロビジョニングします。 オンプレミスのメンバーには既に AD アカウントがあり、スコープ内のクラウド管理メンバーはユーザー プロビジョニングから 1 つを取得します。 |

Important

オンプレミス メンバーの参照を書き込む行は、オンプレミス ユーザーへのメンバーシップ プロビジョニングが有効になっている場合にのみ適用されます。 この設定は既定ではオフになっています。 オンプレミス ユーザーへの **グループ メンバーシップ** の説明に従って、スコープ ウィザードの [グループ メンバーシップ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#group-membership-to-on-premises-users)の構成手順で有効にします。

次の表では、ソースがActive Directoryされたときにグループ メンバーシップをプロビジョニングする方法について説明します。

| 親グループ SOA | メンバー ユーザー SOA | 同期処理のしくみ |
| --- | --- | --- |
| オンプレミス | オンプレミス | すべてのメンバー参照を使用してグループをプロビジョニングします。 |
| オンプレミス | 雲 | SOA がクラウドに移行されたメンバーを含む、すべてのメンバー参照付きでグループをプロビジョニングします。 |
| オンプレミス | Mixed | SOA がクラウドに移行されたメンバーを含む、すべてのメンバー参照付きでグループをプロビジョニングします。 |
| オンプレミス | None | メンバーのないグループを作成します。 |
| 雲 | Any | グループを**プロビジョニングしません**。 |

#### 入れ子グループの所属関係の挙動

`AAD2ADGroupProvisioning` ジョブは、次の表に示すように、入れ子になったグループ メンバーシップ参照を処理します。

| 利用シーン | 親グループの種類 | メンバー グループの種類 | 同期処理のしくみ |
| --- | --- | --- | --- |
| Microsoft Entra親セキュリティ グループには、Microsoft Entraのセキュリティ グループ メンバーのみが含まれます。 | Microsoft Entra セキュリティ グループ | Microsoft Entra セキュリティ グループ | 親グループにすべてのメンバー グループ参照を設定します。 |
| Microsoft Entra親セキュリティ グループに同期グループ メンバーが含まれます。 | Microsoft Entra セキュリティ グループ | 同期された AD DS セキュリティ グループ | 親グループをプロビジョニングしますが、AD DS グループ参照はプロビジョニングされません。 |
| Microsoft Entra親セキュリティ グループには、SOA がクラウドに変換された同期グループ メンバーが含まれます。 | Microsoft Entra セキュリティ グループ | SOA に変換された AD DS セキュリティ グループ | 親グループにすべてのメンバー グループ参照を設定します。 |
| クラウド所有のグループをメンバーとして含む同期済みの親グループの SOA を変換します。 | SOA に変換された AD DS セキュリティ グループ | Microsoft Entra セキュリティ グループ | 親グループにすべてのメンバー グループ参照を設定します。 |
| 他の同期グループをメンバーとして含む同期された親グループの SOA を変換します。 | SOA に変換された AD DS セキュリティ グループ | 同期された AD DS セキュリティ グループ | 親グループをプロビジョニングしますが、AD DS グループ参照はプロビジョニングされません。 |
| メンバーが他の SOA に変換されたグループである同期親グループの SOA を変換します。 | SOA に変換された AD DS セキュリティ グループ | SOA に変換された AD DS セキュリティ グループ | 親グループにすべてのメンバー グループ参照を設定します。 |

### 削除の仕組み

削除の動作は、オブジェクトの種類とライフサイクル イベントによって異なります。 AD ユーザー アカウントは無効にできますが、AD グループの状態は無効になっていません。

| Microsoft Entra ID のトリガー | Active Directoryでの効果 |
| --- | --- |
| ユーザーが論理削除される | 一致した AD ユーザー アカウントが無効になっています。 |
| ユーザーがハード削除される | 一致した AD ユーザー アカウントが削除されます。 |
| ユーザーがスコープ外になる | 一致した AD ユーザー アカウントが無効になっています。 |
| グループがハード削除される | 一致した AD グループが削除されます。 |
| Microsoft Entra IDのグループからメンバーが削除される | 対応するメンバー参照が AD グループから削除されます。 |

Warning

運用環境で適用する前に、テスト構成でスコープの変更を検証します。 オブジェクトがスコープを離れた場合は、プロビジョニング ログを確認して、結果のアクションを確認します。 AD グループは無効にできないため、ユーザーのプロビジョニング解除動作をグループに適用しないでください。

### パスワード書き戻し

Microsoft Entra IDのパスワード変更を一致する AD アカウントと同期するパスワード ライトバックは、クラウドで管理されているユーザーには使用できません。 これらのユーザーは、プロビジョニングによって作成される AD アカウントでパスワードレス認証を使用して、Kerberos ベースのアプリケーションにアクセスできます。 詳細については、「 [クラウドマネージド ユーザーがアプリケーションにサインインする方法」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-users-groups-provisioning-walkthrough#how-cloud-managed-users-sign-in-to-the-application)参照してください。

### 同期の頻度

プロビジョニング サービスは、定期的なスケジュールで実行されます。 変更は、サイクルごとに増分的に取得されます。

### 一般的なタスク

次のタスクは、「Active Directory[へのプロビジョニングを構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)」のステップ バイ ステップで説明されています。

| Task | Description |
| --- | --- |
| [プロビジョニングされたユーザーを別の組織単位に移動する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#move-a-provisioned-user-to-a-different-organizational-unit) | 既にプロビジョニングされているユーザーの OU を変更します。 |
| [グループの元の組織単位を保持する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#preserve-a-groups-original-organizational-unit) | SOA 変換済みのグループは、現在配置されている OU にそのまま保持します。 |
| [グループの元の共通名を保持する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#preserve-a-groups-original-common-name) | SOA 変換されたグループの既存の名前を保持します。 |
| [SOA 変換されたユーザーまたはグループをロールバックする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#roll-back-a-soa-converted-user-or-group) | オブジェクトをオンプレミス コントロールに返します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-accidental-deletes"} -->
## Microsoft Entra クラウド同期での誤削除 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-accidental-deletes
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: このトピックでは、誤削除機能を使用して削除を防ぐ方法について説明します。

次のドキュメントでは、Microsoft Entra Cloud Sync の誤削除機能について説明します。誤って削除する機能は、多くのユーザーやグループに影響を与えるオンプレミス ディレクトリに対する誤った構成変更や変更からユーザーを保護するように設計されています。 この機能では次のことができます。

- 自動的に誤って削除されないように保護する機能を構成します。
- 構成を有効にするオブジェクトの数 (しきい値) を設定します。
- このシナリオで対象の同期ジョブが検疫されると、通知メール アドレスを設定して電子メール通知を受け取ることができるようにします。

注

Microsoft Entra ID への誤削除防止の 4 つのグループ プロビジョニングを指定した場合は、グループが削除されるのを妨げるだけであることに注意してください。 これにより、メンバーが削除されるのを防ぐことはありません。 メンバーが削除されないようにするには、同期されたユーザーに対して誤った削除防止を構成する必要があります。

この機能を使用するには、削除された場合に同期を停止する必要があるオブジェクトの数のしきい値を設定します。 そのため、この数に達すると、同期が停止し、指定された電子メールに通知が送信されます。 この通知を使用すると、何が起こっているかを調査できます。

詳細と例については、次のビデオを参照してください。

### 誤削除防止を構成する

この新機能を使用するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. **[プロパティ] を選択します**。
3. [**基本**] の横にある鉛筆をクリックします
4. 右側に次の情報を入力します。
    - **通知メール - 通知** に使用される電子メール
    - **誤って削除されないようにする** - このチェック ボックスをオンにして機能を有効にします
    - **誤削除のしきい値** - 同期を停止して通知を送信するオブジェクトの数を入力します

### 誤って削除されたインスタンスからの復旧

誤って削除が発生した場合は、プロビジョニング エージェントの構成の状態に関するこのメッセージが表示されます。 **削除のしきい値を超えたと表示されます**。

[Image: 誤って削除された状態]

[ **削除のしきい値を超えました**] をクリックすると、同期の状態情報が表示されます。 このアクションにより、詳細が提供されます。

[Image: 同期の状態]

省略記号を右クリックすると、次のオプションが表示されます。

- プロビジョニング ログの表示
- エージェントの表示
- 削除を許可する

[Image: 右クリック]

**プロビジョニング ログの表示を**使用すると、**StagedDelete** エントリを確認し、削除されたユーザーに対して提供された情報を確認できます。

[Image: プロビジョニング ログ]

#### 削除を許可する

**[削除を許可]** アクションは、誤って削除しきい値をトリガーしたオブジェクトを削除します。 これらの削除を受け入れるには、次の手順を使用します。

1. 省略記号を右クリックし、 **[Allow deletes](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/削除を許可する)** を選択します。
2. 削除を許可するには、確認で [ **はい** ] をクリックします。

[Image: 確認時ははい]

1. 削除が受け入れられ、次のサイクルで状態が正常に戻るという確認が表示されます。

[Image: 削除を受け入れる]

#### 削除の拒否

削除を許可しない場合は、次の操作を行う必要があります。

- 削除のソースを調査します。
- 問題を修正します (たとえば、OU が誤ってスコープ外に移動され、スコープに再び追加した場合)。
- エージェント構成で **再起動同期** を実行します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-active-directory-object-enforcement"} -->
## Microsoft Entra AD オブジェクトの適用 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-active-directory-object-enforcement
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-11
- Summary: 同期されたActive Directory オブジェクトを Microsoft Entra Cloud Sync プロビジョニング サービスによってのみ変更できるように、AD 強制プレビューを構成します。

Microsoft Entra Cloud Sync では、クラウド ユーザーとグループを オンプレミスの Active Directory (AD) にプロビジョニングできます。 AD オブジェクトの適用を使用すると、特定のプロビジョニングされたユーザーとグループを保護して、Microsoft Entra プロビジョニング サービスを通じてのみ変更を実行できます。 この記事では、強制エンジンを有効にし、その共有ポリシーを構成し、適用のためにユーザーとグループをマークする方法について説明します。

開始する前に、ドメイン コントローラー、プロビジョニング エージェント、AD スキーマ、および管理アクセスが前提条件を満たしていることを確認します。

### Prerequisites

構成手順を開始する前に、次の前提条件が既に満たされていることを確認してください。

| 前提条件 | Details |
| --- | --- |
| Microsoft Entra ライセンス | ユーザーとグループを AD にプロビジョニングするために必要なライセンスを持つMicrosoft Entra テナント。 [「ライセンスの要件」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory#license-requirements)参照してください。 |
| AD ロール | ドメイン管理者。ポリシーをインストールする PowerShell スクリプトを実行し、ポリシー オブジェクトを管理します。 この特権は、ポリシーのインストールまたは変更中にのみ使用してください。 組織の Just-In-Time 特権アクセス プロセスに従って、完了したら特権アクセスを削除または非アクティブ化します。 |
| 書き込み可能なすべての DC でサポートされているドメイン コントローラー OS | Windows Server 2022 または Windows Server 2025。 すべての書き込み可能なドメイン コントローラーで適用を有効にする必要があるため、サポートされている OS をすべて実行できることを確認します。 書き込み可能なドメイン コントローラーをサポートされている OS に持ち込めることができない場合、そのコントローラーは参加できず、ドメインに対して適用を構成することはできません。 |
| エージェント ホストをプロビジョニングする | [クラウド同期エージェントの要件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#cloud-provisioning-agent-requirements)を満たすドメイン参加済みサーバー。 Windows Server 2025 または Windows Server 2022 をお勧めします。 エージェントはドメイン コントローラーで実行する必要はありません。 |
| ドメイン機能レベルの要件なし | 強制では、ドメインまたはフォレストの機能レベルを上げる必要はありません。 機能レベルの設定ではなく、すべての書き込み可能なドメイン コントローラーを有効にすることが運用上の要件です。 |
| Schema | Windows Server 2016 スキーマ以降に存在する既存の`msDS-ObjectSoa`属性を使用します。 スキーマ拡張機能は必要ありません。 |
| ドメイン コントローラー インベントリ | ドメイン内のすべての書き込み可能なドメイン コントローラーのインベントリ。 それらを列挙するには、 `Get-ADDomainController -Filter * | Where-Object { -not $_.IsReadOnly }` 実行するか、 [ドメイン コントローラーの一覧を表示するを参照してください](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/get-addomaincontroller)。 |
| AD へのプロビジョニングが構成されています | 適用するユーザーまたはグループをプロビジョニングする AD 構成へのMicrosoft Entra ID。 「[チュートリアル: Microsoft Entra IDからオンプレミス アプリへのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-users-groups-provisioning-walkthrough)」を参照してください。 |

プロビジョニング エージェントの前提条件の完全な一覧については、「[Microsoft Entra IDからActive Directoryへのプロビジョニングの前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites-provision-entra-to-active-directory)」を参照してください。

Important

AD ユーザーとグループの適用は **プレビュー段階です**。 プレビューは、ドメイン コントローラー上の **Active Directory 強制エンジン**と、ユーザーとグループを適用対象としてマークする **Microsoft Entra Cloud Sync** 構成の 2 つの部分で構成されます。 プレビューのAzure機能に適用される法的条件については、[Microsoft Azure プレビューの追加使用条件](https://azure.microsoft.com/support/legal/preview-supplemental-terms/)を参照してください。

### AD オブジェクトの強制適用のしくみ

強制適用は、**Lightweight Directory Access Protocol (LDAP) による書き込みが行われる時点で、その書き込みを処理するドメインコントローラー上のActive Directory**で評価されます。 変更が適用対象としてマークされているユーザーまたはグループを対象とする場合、ドメイン コントローラーは、呼び出し元 ID がポリシーによって承認されているかどうかを確認します。 そうでない場合は、Microsoft Entra 内のオブジェクトの状態と AD 内の状態との間で*ドリフト*が発生する前に、変更がブロックされる (強制モード) か、ログに記録されます (監査モード)。

次の 2 つの要素が連携して動作します。

- 適用されたオブジェクトの変更が許可されているセキュリティ識別子 (SID) と、現在のモード (強制または監査) を一覧表示するドメイン全体の **機関ソース (SOA) ポリシー** 。 ポリシーは、`SOA-Policies`の下に作成された`CN=System,DC=<your domain>` コンテナーに格納されます。
- Cloud Sync を使用して設定するオブジェクトごとのマーカー ( [`msDS-ObjectSoa`](https://learn.microsoft.com/ja-jp/openspecs/windows_protocols/ms-ada2/426118f6-06ea-4ea0-adbe-03556bb58c9c) 属性)。このポリシーは、この属性が設定されているオブジェクトにのみ適用されます。

| Mode | Behavior |
| --- | --- |
| **強制適用** | ポリシーで許可されている SID のみが、適用されたユーザーとグループを変更できます。 このポリシーは、LDAP の modify 操作、modify DN 操作、およびごみ箱からの復元をブロックします。 ポリシーでは、追加に `msDS-ObjectSoa` 属性が含まれている場合でも、LDAP 追加操作が許可されます。 削除操作は、パブリック プレビュー中に許可されます。 |
| **監査** | 変更は、既存の AD ロールベースのアクセス制御 (RBAC) ごとに許可されます。 承認されていない ID が適用されたオブジェクトを変更すると、ポリシーによって **ディレクトリ サービス** ログにイベントが書き込まれます。 [適用] に切り替える前に、監査モードを使用して帯域外の変更を検出します。 イベントを表示するには、セキュリティ診断ログを有効にします ( イベント ログの適用イベントの表示を参照してください)。 |

AD オブジェクトの適用は、既存の AD RBAC モデルに **追加** されます。 追加のアクセス権を付与することなく、現在のアクセス制御の上に追加の制限を設定します。

Warning

**適用は、マークしたユーザーとグループにのみ適用され、マークされたオブジェクトの保護は、すべての書き込み可能なドメイン コントローラーが有効になっている場合にのみ完了します。** 適用はドメイン全体のスイッチではなく、 `msDS-ObjectSoa` 属性が設定されているオブジェクトに対してのみ有効になります。 マークされたオブジェクトごとに、ポリシーは、サポートされているオペレーティング システムを実行し、更新プログラムをインストールし、機能を有効にしているドメイン コントローラーでのみ受け入れられます。 書き込み可能なドメイン コントローラーが 1 つでも有効になっていない場合は、そのドメイン コントローラーに対する未承認の変更が成功し、そのオブジェクトのコントロールがバイパスされます。 適用に依存する前に、書き込み可能 **なすべての** ドメイン コントローラーを更新して有効にすることを計画します。

### ロールアウトを計画する

大まかな構成は次のとおりです。

1. 更新プログラムをインストールし、書き込み可能なすべてのドメイン コントローラーで機能を有効にします。
2. ポリシーを適用モードまたは監査モードでインストールします。
3. 保護するユーザーとグループをマークします。

### 書き込み可能なすべてのドメイン コントローラーを更新して有効にする

ドメイン全体で適用エンジンをオンラインにします。 **書き込み可能なすべてのドメイン コントローラー**で更新と有効化を繰り返します。

書き込み可能なドメイン コントローラーごとに、最新の累積的なWindows Server更新プログラムをインストールし、一致するグループ ポリシー (KIR) パッケージを展開して機能を有効にします。 `C:\Windows\System32\ntdsai.dll`の最小バージョンは、Windows Server 2022の場合は **10.0.20348.5257**、Windows Server **2025 の場合は 10.0.26100.32995** です。 インストールされているバージョンを確認するには、ドメイン コントローラーで `(Get-Item C:\Windows\System32\ntdsai.dll).VersionInfo.FileVersion` を実行します。

1. すべての書き込み可能なドメイン コントローラーに、最新の累積的なWindows Server更新プログラムをインストールします。
2. 更新のプロンプトが表示されたら、ドメイン コントローラーを再起動します。
3. 既知の問題ロールバック (KIR) 有効化モデルを使用する一致するグループ ポリシー パッケージを展開して、すべての書き込み可能なドメイン コントローラーでこの機能を有効にします。 すべての書き込み可能なドメイン コントローラーで機能を有効にする手順については、「 [グループ ポリシーを使用して既知の問題のロールバックを展開する](https://learn.microsoft.com/ja-jp/troubleshoot/windows-client/group-policy/use-group-policy-to-deploy-known-issue-rollback)」を参照してください。 一致するパッケージをダウンロードします。

    - Windows Server 2022: [Windows Server 2022のグループ ポリシー パッケージ](https://aka.ms/ADEnforcementGPMSI2022)
    - Windows Server 2025: [Windows Server 2025 のグループ ポリシー パッケージ](https://aka.ms/ADEnforcementGPMSI2025)
4. 有効にした後、各ドメイン コントローラーを再起動します。

プライマリ ドメイン コントローラー エミュレーター (PDCe) が更新されて有効になった後、`SOA-Policies`の下に`CN=System,DC=<your domain>` コンテナーが自動的に作成され、すべてのドメイン コントローラーにレプリケートされます。 コンテナーが存在することを確認します (実際のドメイン名に置き換えます)。 表示およびレプリケートには数分かかる場合があります。

### ポリシーを適用モードまたは監査モードでインストールする

`Set-CloudSyncSOAPolicy.ps1` スクリプトは、`Cloud` コンテナー内に`SOA-Policies` ポリシー オブジェクトを作成し、プロビジョニング エージェントのグループ管理サービス アカウント (GMSA) SID をポリシーの許可リストに追加します。 `SOA-Policies` コンテナーがまだ存在しない場合は、スクリプトによって作成されます。

このスクリプトは、ローカルにインストールされたプロビジョニング エージェント サービスからエージェントの GMSA を読み取ります。 **Cloud Sync プロビジョニング エージェントがインストールされているマシンで実行します。**

1. Microsoft Entra Cloud Sync プロビジョニング エージェントをインストールします。 インストール手順については、「[Microsoft Entra Cloud Sync プロビジョニング エージェントのインストール」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)参照してください。
2. プロビジョニング エージェントがインストールされているマシンにサインインします。
3. GitHubの AzureAD/EntraIDGovernance リポジトリから [`Set-CloudSyncSOAPolicy.ps1`](https://github.com/AzureAD/EntraIDGovernance/blob/main/Set-CloudSyncSOAPolicy.ps1) PowerShell スクリプトをダウンロードします。
4. PowerShell を管理者として開きます。
5. スクリプトを含むフォルダーにディレクトリを変更します。
6. スクリプトを実行します。 モードとして `Enforced` を指定します ("what-if" ロールアウトには `Audit` を使用します)。

    ```powershell
    .\Set-CloudSyncSOAPolicy.ps1 -EnforcementMode Enforced -Credential (Get-Credential -Message "Enter Domain Admin credentials (format: DOMAIN\Username)")
    ```
7. `Cloud` ポリシーが、**強制** (または**監査**) キーワードで構成されていることを確認します。 新しいオブジェクトがドメイン間でレプリケートされる時間を許可します。

    [Image: CN=SOA-Policies コンテナーの下にある CN=Cloud ポリシー オブジェクトを示す ADSI Edit のスクリーンショット。]

### ユーザーとグループを強制適用対象としてマークする

属性マッピングは、グループとユーザーによって異なります。 保護するオブジェクトの種類ごとに適用可能なマッピングを構成します。

#### 強制適用するグループをマークする

Cloud Sync の属性マッピングを使用して `msDS-ObjectSoa` 属性を `Cloud` に設定し、グループを適用対象としてマークします。

1. AD へのグループ プロビジョニング構成で、属性マッピングを編集します。
2. `msDS-ObjectSoa`値を持つターゲット属性として`Cloud`を追加します。 次のいずれかを選択してください:
    - **定数マッピング** (ほとんどのお客様に推奨): プロビジョニング ジョブのスコープ内のすべてのグループのプロパティを設定します。
    - **式マッピング**: 条件付きロジックに基づいて、プロパティが設定されるグループを制限します。
3. 保護するグループをプロビジョニング スコープに割り当てます。
4. グループをオンデマンドでプロビジョニングするか、同期サイクルを開始します。

AD へのグループ プロビジョニングの構成の詳細については、「プロビジョニングを[Active DirectoryするようにMicrosoft Entra IDを構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)」を参照してください。

#### ユーザーを措置対象としてマークします

Cloud Sync の属性マッピングを使用して `msDS-ObjectSoa` 属性を `Cloud` に設定し、ユーザーを強制適用対象としてマークします。 MICROSOFT ENTRA IDから AD への構成では、既定のユーザー属性マッピングに定数`Cloud`にマップされた`msDS-ObjectSoa`が既に含まれているため、その構成によってプロビジョニングされたユーザーは自動的にマークされます。

マッピングを確認または構成するには:

1. Microsoft Entra IDから AD への構成で、**ユーザー**属性マッピングを編集します。
2. `msDS-ObjectSoa`値が`Cloud`のターゲット属性として存在することを確認します。 マークするユーザーを制限する必要がある場合は、**定数**マッピングの代わりに**式**マッピングを使用します。
3. 保護するユーザーをプロビジョニング スコープに割り当てます。
4. ユーザーをオンデマンドでプロビジョニングするか、同期サイクルを開始します。 Active Directory[へのプロビジョニングの構成を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)参照してください。

ユーザー属性マッピングの詳細については、「[Active Directoryへのプロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#user-attribute-mappings)」を参照してください。

### 強制属性を確認する

ドメイン コントローラーで ADSI Edit を使用して、マークした各オンプレミス ユーザーまたはグループにポリシーが適用されることを確認します。

1. **ADSI Edit を**開きます。
2. **View**&gt;**Advanced Features** を選択します。
3. ユーザーまたはグループに移動し、[ **プロパティ]** を開きます。
4. `msDS-ObjectSoa` プロパティが `Cloud` に設定されていることを確認します。

次のスクリーンショットは、グループに設定された属性を示しています。

[Image: ADSI Edit の Active Directory グループの [属性エディター] タブのスクリーンショット。msDS-ObjectSoa 属性セットが表示されています。]

### 変更がブロックされたときに管理者に表示される内容

**強制**モードでは、適用されたユーザーまたはグループを変更する未承認の試行が LDAP 書き込みレイヤーでブロックされ、オブジェクトがクラウド SOA ポリシーによって管理されていることを示す特定のエラーが返されます。 この変更はコミットされないため、調整すべきドリフトは発生しません。 このエラーは一般的な "アクセス拒否" とは異なるので、管理者は変更が意図的にブロックされたこと、およびオブジェクトをMicrosoft Entraで管理する必要があることを通知できます。 正確な文言はツール (Active Directory ユーザーとコンピューター、PowerShell、または LDAP クライアント) によって異なりますが、意味は同じです。

**監査**モードでは、同じ変更が許可され、変更がブロックされたことを示すイベントが**ディレクトリ サービス** ログに書き込まれます。

### 強制モードと監査モードを切り替える

モードを変更するには、`Set-CloudSyncSOAPolicy.ps1`の新しい値で`-EnforcementMode`をもう一度実行します。

```powershell
.\Set-CloudSyncSOAPolicy.ps1 -EnforcementMode Audit -Credential (Get-Credential -Message "Enter Domain Admin credentials (format: DOMAIN\Username)")
```

### 緊急アクセス用アカウント

クラウド プロビジョニングが利用できないときに使用する緊急管理者アカウントなど、オンプレミスで強制ユーザーとグループを変更するための追加の ID を承認できます。 これを行うには、アカウントの SID をポリシーに追加します。

1. **ADSI Edit を**開きます。
2. **CN=SOA-Policies**&gt;**CN=Cloud** に移動します。
3. **属性エディター**を開きます。
4. `msDS-Settings`属性を編集し、break-glass アカウントの SID を追加します。

    [Image: SID 値を持つ複数値の文字列エディターで編集されている CN=SOA-Policies の msDS-Settings 属性を示す ADSI Edit のスクリーンショット。]

次の制限と動作に注意してください。

- **許可リストはできるだけ小さくしてください。** 最も強力なガバナンス体制の場合は、プロビジョニング エージェント SID のみを許可し、特定の運用上のニーズがない限り、重大なアカウントの追加を回避します。 このポリシーでは、最大 **64 個の SID がサポートされています**。
- **SID は、ポリシーの読み込み時に検証されます。** 無効な SID または古い SID が 1 つ存在すると **、ポリシー全体の読み込みに失敗し**、ID は承認されません。 **ディレクトリ サービス** ログでポリシー読み込みエラーを確認し、SID を修正します。
- **アカウントの変更は運用上のリスクです。** 承認されたアカウントが削除または再作成され、その SID が変更された場合は、それに応じて `msDS-Settings` 更新します。 これを運用 Runbook で文書化します。

### イベント ログで強制イベントを表示する

承認されていない変更の監査イベントを表示するには:

1. [Security Diagnostics]\(セキュリティ診断\) の値をレジストリで `1` に設定します。 詳細については、 [AD および LDS 診断イベントのログ記録](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/active-directory/configure-ad-and-lds-event-logging)を参照してください。
2. イベント ビューアーを開き、**ディレクトリ サービス**のイベント ログを表示します。

セキュリティ診断を既定値の `0` に設定すると、ポリシー読み込みイベントのみがログに記録されます。個々のブロック イベントと監査イベントは記録されません。

### 適用ポリシーのトラブルシューティング

AD オブジェクトの適用が想定どおりに動作しない場合 (たとえば、ブロックする必要があるオンプレミスの変更は引き続き処理されます)、 `Check-CloudSyncSOAPolicy.ps1` スクリプトを使用して、ドメイン コントローラーで強制が有効になっていることを確認します。

1. GitHubの AzureAD/EntraIDGovernance リポジトリから[`Check-CloudSyncSOAPolicy.ps1`](https://github.com/AzureAD/EntraIDGovernance/blob/main/Check-CloudSyncSOAPolicy.ps1) スクリプトをダウンロードします。
2. 検証するドメイン コントローラーにサインインします。
3. PowerShell を管理者として開きます。
4. スクリプトを含むフォルダーにディレクトリを変更します。
5. スクリプトを実行します。 そのドメイン コントローラーで AD オブジェクト強制ポリシーが有効になっているかどうかを報告します。

ブロックする必要がある変更が引き続き成功する場合は、次の点を確認します。

- 変更は、**更新されておらず**有効になっていないドメイン コントローラーに書き込まれました。 **すべての書き込み可能なドメイン コントローラー**に更新プログラムとグループ ポリシー パッケージが含まれており、後で再起動されたことを確認します。 クライアントが使用するドメイン コントローラーを見つけるには、 `nltest /dsgetdc:<your domain>`を実行します。
- ポリシーは、強制ではなく **監査** モード **です**。
- `SOA-Policies` コンテナーは、`CN=System,DC=<your domain>`の下に存在します。
- `msDS-ObjectSoa`属性は、ターゲット ユーザーまたはグループに設定されます。 そうでない場合は、該当するユーザーまたはグループ属性のマッピングを確認し、プロビジョニング サイクルを実行します。

承認された変更が予期せずブロックされた場合は、アクションアカウントの SID がポリシーの `msDS-Settings` 属性に存在し、変更が書き込みを処理するドメイン コントローラーにレプリケートされていることを確認します。

### ポリシーをテストする

次のテスト ケースの例を使用して、構成を検証します。

- 許可されていないアカウントを使用して、オンプレミスで適用されるグループのメンバーシップを更新します。 変更はブロックする必要があります。
- 許可されていないアカウントを使用して、オンプレミスで適用されるユーザーの属性を更新します。 変更はブロックする必要があります。
- ポリシーを **監査** に切り替え、テストを繰り返します。 変更が許可され、 **ディレクトリ サービス** のイベント ログにイベントが表示されます。
- ポリシーに SID を「緊急用アカウント」として追加し、そのアカウントを使用して更新を行います。 変更は成功するはずです。
- 書き込み可能な各ドメイン コントローラーに対して同じ未承認の変更を試み、ドメイン全体で適用が一貫することを確認します。

### このプレビューでの既知の動作と制限事項

- Microsoft Entraでユーザーまたはグループの権限のソースを変換しても、AD 内のオブジェクトは自動的にロックダウンされません。 この記事の手順を実行して、AD へのプロビジョニングによってオブジェクトを適用済みとしてマークします。
- 強制によって削除が妨げることはありません。
- 強制は、マークされた各オブジェクトの属性と、グループのメンバーシップを保護します。 強制グループを非強制グループにネストすることに制限はありません。
- AD へのユーザーとグループのプロビジョニングに関する既存の制限は、このプレビュー期間中も引き続き適用されます。
- 適用は、有効になっているドメイン コントローラーでのみ有効になります。 すべての書き込み可能なドメイン コントローラーで機能を有効にします。それ以外の場合は、有効になっていないドメイン コントローラーに書き込まれた変更が処理されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-attribute-mapping"} -->
## Microsoft Entra クラウド同期の属性マッピング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-attribute-mapping
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra Connect のクラウド同期機能を使って属性をマップする方法について説明します。

クラウド同期属性マッピング機能を使って、オンプレミスのユーザーまたはグループ オブジェクトと Microsoft Entra ID 内のオブジェクトの間で属性をマップすることができます。

[Image: 新しい UX 画面属性マッピングのスクリーンショット。]

次のドキュメントでは、Active Directory から Microsoft Entra ID にプロビジョニングするための Microsoft Entra クラウド同期の属性スコープについて説明します。 Microsoft Entra ID から AD への属性マッピングに関する情報をお探しの場合は、[Microsoft Entra ID から Active Directory へのプロビジョニングを構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)を参照してください。

既定の属性マッピングをビジネスのニーズに合わせてカスタマイズ (変更、削除、または作成) できます。 同期される属性の一覧については、「 [Microsoft Entra ID に同期される属性」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized)参照してください。

注記

この記事では、Microsoft Entra 管理センターを使って属性をマップする方法について説明します。 Microsoft Graph の使用方法については、「 [変換](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-transformation)」を参照してください。

### 属性マッピングの種類について

属性マッピングを使うと、Microsoft Entra ID に属性を設定する方法を制御できます。 Microsoft Entra ID では、次の 4 種類のマッピングがサポートされています。

| マッピングの種類 | 説明 |
| --- | --- |
| **直接** | ターゲットの属性に、Active Directory 内でリンクされているオブジェクトの属性値を設定します。 |
| **定数** | ターゲットの属性に、指定した特定の文字列を設定します。 |
| **式** | ターゲットの属性を、スクリプトのような式の結果に基づいて設定します。 詳細については、「[Microsoft Entra ID での属性マッピングの式ビルダーと式の記述」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-expression-builder)参照してください。 |
| **何一つ** | ターゲットの属性を変更しません。 ただし、ターゲットの属性が空の場合は、指定した既定値が設定されます。 |

これらの基本型と共に、カスタム属性マッピングでは、省略可能な *既定値* の割り当ての概念がサポートされます。 既定値の割り当てでは、Microsoft Entra ID またはターゲット オブジェクトに値がない場合にも、ターゲットの属性には必ず値が設定されます。 最も一般的な構成では、これを空白のままにします。

### スキーマの更新とマッピング

クラウド同期では、スキーマと同期される既定の属性の一覧が更新 [される](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized)場合があります。 これらの既定の属性マッピングは、新しいインストールで使用できますが、既存のインストールには自動的に追加されません。 これらのマッピングを追加するには、以下の手順に従います。

1. **[属性マッピングの追加**] をクリックします
2. [対象の属性] ドロップダウンを選択します
3. 使用できる新しい属性がここに表示されます。

追加された新しいマッピングの一覧を示します。

| 追加された属性 | マッピングの種類 | 追加が行われたエージェント バージョン |
| --- | --- | --- |
| 優先データ場所 | 直接 | 1.1.359.0 |
| 従業員番号 | 直接 | 1.1.359.0 |
| ユーザータイプ | 直接 | 1.1.359.0 |

UserType をマップする方法の詳細については、「 [クラウド同期を使用して UserType をマップ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-map-usertype)する」を参照してください。

### 属性マッピングのプロパティについて

属性マッピングでは、このタイプ プロパティに加えて、特定の属性もサポートしています。 これらの属性は、選択したマッピングの種類によって異なります。 以降のセクションでは、個々の種類ごとにサポートされる属性マッピングについて説明します。 次の種類の属性マッピングを使用できます。

- 直接
- 定数
- 表現

#### 直接マッピングの属性

直接マッピングでサポートされる属性を次に示します。

- **ソース属性**: ソース システムのユーザー属性 (例: Active Directory)。
- **ターゲット属性**: ターゲット システムのユーザー属性 (例: Microsoft Entra ID)。
- **null の場合の既定値 (省略可能):** ソース属性が null の場合にターゲット システムに渡される値。 この値は、ユーザーを作成するときにのみプロビジョニングされます。 既存のユーザーを更新するときにプロビジョニングされることはありません。
- **このマッピングを適用**します。
    - **常に**: ユーザー作成アクションと更新アクションの両方にこのマッピングを適用します。
    - **作成時のみ**: このマッピングは、ユーザー作成アクションにのみ適用します。

[Image: 属性マッピングの編集のスクリーンショット。]

#### 定数マッピングの属性

定数マッピングでサポートされる属性を次に示します。

- **定数値**: ターゲット属性に適用する値。
- **ターゲット属性**: ターゲット システムのユーザー属性 (例: Microsoft Entra ID)。
- **このマッピングを適用**します。
    - **常に**: ユーザー作成アクションと更新アクションの両方にこのマッピングを適用します。
    - **作成時のみ**: このマッピングは、ユーザー作成アクションにのみ適用します。

#### 式マッピングの属性

式マッピングでサポートされる属性を次に示します。

- **式**: この式は、ターゲット属性に適用される式です。 詳細については、「[Microsoft Entra ID での属性マッピングの式ビルダーと式の記述」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-expression-builder)参照してください。
- **null の場合の既定値 (省略可能):** ソース属性が null の場合にターゲット システムに渡される値。 この値は、ユーザーを作成するときにのみプロビジョニングされます。 既存のユーザーを更新するときにプロビジョニングされることはありません。
- **ターゲット属性**: ターゲット システムのユーザー属性 (例: Microsoft Entra ID)。
- **このマッピングを適用**します。

    - **常に**: ユーザー作成アクションと更新アクションの両方にこのマッピングを適用します。
    - **作成時のみ**: このマッピングは、ユーザー作成アクションにのみ適用します。

### 属性マッピングを追加する - AD から Microsoft Entra ID に

[AD と Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure) の構成を使用して属性マッピングを構成するには、次の手順を使用します。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. 左側の [ **属性マッピング**] を選択します。
3. 上部で、正しいオブジェクトの種類が選択されていることを確認します。 つまり、ユーザー、グループ、または連絡先です。
4. [ **属性マッピングの追加]** をクリックします。

[Image: 属性マッピングの追加のスクリーンショット。]

1. マッピングの種類を選択します。 次のいずれかになります。

    - **直接**: ターゲット属性には、Active Directory のリンクされたオブジェクトの属性の値が設定されます。
    - **定数**: ターゲット属性には、指定した特定の文字列が設定されます。
    - **式**: ターゲット属性は、スクリプトに似た式の結果に基づいて設定されます。
    - **なし**: ターゲット属性は変更されません。
2. 前の手順で選択した内容に応じて、さまざまなオプションが入力に使用できます。
3. このマッピングを適用するタイミングを選択し、[ **適用**] を選択します。 [Image: 属性マッピングの保存のスクリーンショット。]
4. **[属性マッピング**] 画面に戻ると、新しい属性マッピングが表示されます。
5. [ **スキーマの保存] を選択します**。 スキーマを保存すると、同期が行われることが通知されます。 [ **OK] をクリックします**。 [Image: スキーマの保存のスクリーンショット。]
6. 保存が成功すると、右側に通知が表示されます。

[Image: スキーマの保存が成功したスクリーンショット。]

### 属性マッピングをテストする

属性マッピングをテストするには、 [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision)使用できます。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. 左側で、[ **Provision on demand**] を選択します。
3. ユーザーの識別名を入力し、[ **プロビジョニング** ] ボタンを選択します。

[Image: ユーザー識別名のスクリーンショット。]

1. 4 つの緑色のチェックマークがある成功画面が表示されます。 エラーがある場合は、左側に表示されます。

[Image: オンデマンドの成功のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-automatic-upgrade"} -->
## Microsoft Entra Connect クラウド プロビジョニング エージェント: 自動アップグレード - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-automatic-upgrade
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra Connect クラウド プロビジョニング エージェントの組み込みの自動アップグレード機能について説明します。

自動アップグレード機能を使用すると、Microsoft Entra Connect クラウド プロビジョニング エージェントのインストールが常に最新の状態に保たれやすくなります。

エージェントはここにインストールされています: "program files\Azure AD Connect Provisioning Agent\AADConnectProvisioningAgent.exe"

バージョンを確認するには、実行可能ファイルを右クリックし、プロパティを選択してから詳細を選択します。

[Image: エージェント ファイルのバージョン]

エージェントアップデーターがここにインストールされています: "プログラムファイル\Azure AD Connect Provisioning Agent Updater\AzureADConnectAgentUpdater.exe"

バージョンを確認するには、実行可能ファイルを右クリックし、プロパティを選択してから詳細を選択します。

[Image: エージェント アップデーターのバージョン]

### エージェントのアンインストール

エージェントを削除するには、「プログラムの **アンインストールまたは変更** 」に移動し、以下をアンインストールします。

- **Microsoft Entra Connect エージェント アップデーター**
- **Microsoft Entra Provisioning Agent**
- **Microsoft Entra Provisioning Agent パッケージ**

[Image: エージェントの削除]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-cloud-sync-workbook"} -->
## Microsoft Entra Connect クラウド同期分析ワークブック - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-cloud-sync-workbook
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、クラウド同期用の Azure Monitor ワークブックについて説明します。

クラウド同期ワークブックは、データ分析のための柔軟なキャンバスを提供します。 ブックを使うと、Microsoft Entra 管理センター内で高度なビジュアルのレポートを作成できます。 詳細については、Azure Monitor ワークブックの概要をご覧ください。

このブックは、クラウド同期を使って AD から Microsoft Entra ID にユーザーを同期するハイブリッド ID 管理者のためのものです。 管理者は、これを利用して同期の状態や詳細に関する分析情報を得ることができます。

ブックにアクセスするには、クラウド同期ページの左側にある **[Insights]** を選びます。

注意

[Insights] ノードは、すべての構成レベルと個々の構成レベルの両方で使用できます。 個々の構成に関する情報を表示するには、構成のジョブ ID を選びます。

このブックは次のことを行います。

- AD から Microsoft Entra ID に同期されたユーザーとグループの同期の概要が表示されます
- クラウド同期プロビジョニング ログによって取り込まれた情報の詳細ビューが表示されます。
- 特定のニーズに合わせてデータをカスタマイズできます

| フィールド | 説明 |
| --- | --- |
| 日付 | データを表示する範囲。 |
| ステータス | "成功" や "スキップ済み" などのプロビジョニングの状態が表示されます。 |
| アクション | 作成や削除など、実行されたプロビジョニング アクションが表示されます。 |
| ジョブ ID | 特定のジョブ ID をターゲットにすることができます。 複数の構成がある場合、これを利用して個々の構成データを確認できます。 |
| SyncType | オブジェクトやパスワードなど、同期の種類でフィルター処理します。 |

### プロビジョニング ログの有効化

Azure Monitoring と Log Analytics については既に理解している必要があります。 そうでない場合は、その詳細をすぐに確認し、それから戻ってアプリケーション プロビジョニング ログについて学習してください。 Azure Monitoring の詳細については、「[Azure Monitor の概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)」を参照してください。 Azure Monitor のログと Log Analytics の詳細については、[Azure Monitor のログ クエリの概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-query-overview)と[クラウド同期のトラブルシューティングのためのプロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-troubleshoot)に関する記事を参照してください。

### 同期の概要

同期の概要セクションには、組織の同期アクティビティの概要が表示されます。 たとえば、次のようなアクティビティです。

- 1 日あたりの同期アクション (アクション別)
- 1 日あたりの同期アクション (状態別)
- 一意の同期数 (状態別)
- 最近の同期エラー

[Image: クラウド同期の概要のスクリーンショット。]

### 同期の詳細

同期の詳細タブでは、同期データをドリルインして詳細情報を取得できます。 この情報には、以下が含まれます。

- 状態別のオブジェクト同期
- 同期ログの詳細

[Image: クラウド同期の詳細のスクリーンショット。]

さらに同期ログをドリルインして追加情報を得ることができます。

[Image: ログの詳細のスクリーンショット。]

### ジョブ ID

ジョブ ID は、実行時に構成ごとに作成され、データが設定されます。 ジョブ ID に基づいて個々の構成を確認できます。

### カスタム クエリ

カスタム クエリを作成し、Azure ダッシュボードにデータを表示することができます。 方法については、[Log Analytics データのダッシュボードの作成と共有](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/get-started-queries)に関するページを参照してください。 また、[Azure Monitor のログ クエリの概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-query-overview)に関するページも参照してください。

### カスタム アラート

Azure Monitor を使用すると、プロビジョニングに関連する主要なイベントに関する通知を受け取ることができるように、カスタム アラートを構成することができます。 たとえば、障害の急増を検知した際にアラートを受信することが考えられます。 または、無効や削除が急増することもあります。 アラートが必要なもう 1 つの例として、プロビジョニングがないことがあります。これは、何かがうまく行っていないことを示します。

アラートについて詳しくは、[Azure Monitor のログ アラート](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/alerts-create-new-alert-rule)に関するページをご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-configure"} -->
## Microsoft Entra Cloud Sync の新しいエージェント構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-03-30
- Summary: この記事では、クラウド同期をインストールする方法について説明します。

次のドキュメントでは、Active Directory から Microsoft Entra ID へのプロビジョニング用に Microsoft Entra Cloud Sync を構成する方法について説明します。 Microsoft Entra ID から AD へのプロビジョニングの詳細については、「構成 - Microsoft Entra [Cloud Sync を使用して Active Directory を Microsoft Entra ID にプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)する」を参照してください。

次のドキュメントでは、Microsoft Entra Cloud Sync の新しいガイド付きユーザー エクスペリエンスを示します。

詳細とクラウド同期を構成する方法の例については、以下のビデオを参照してください。

### プロビジョニングの構成

プロビジョニングを構成するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **新しい構成]** を選択します。
2. **AD から Microsoft Entra ID の同期を**選択します。[Image: 構成の追加のスクリーンショット。]
3. 構成画面で、ドメインとパスワード ハッシュ同期を有効にするかどうかを選択します。[ **作成**] をクリックします。

[Image: 新しい構成のスクリーンショット。]

1. [ **作業の開始** ] 画面が開きます。 ここから、クラウド同期の構成を続行できます。

[Image: 作業の開始画面のスクリーンショット。]

1. 構成は、次の 5 つのセクションに分かれています。

| セクション | 説明 |
| --- | --- |
| 1. スコープ フィルターを追加する | このセクションを使用して、Microsoft Entra ID に表示されるオブジェクトを定義します |
| 2. マップ属性 | このセクションを使用して、オンプレミスのユーザー/グループと Microsoft Entra オブジェクトの間で属性をマップします |
| 3. テスト | 構成をデプロイする前にテストする |
| 4. 既定のプロパティを表示する | 既定の設定を有効にする前に表示し、必要に応じて変更を加える |
| 5. 構成を有効にする | 準備ができたら、構成を有効にし、ユーザー/グループの同期が開始されます |

### 特定のユーザーとグループにスコープを指定する

既定では、プロビジョニング エージェントは Active Directory からユーザーとグループのサブセットを同期します。 オンプレミスの Active Directory グループまたは組織単位を使用して、特定のユーザーとグループを同期するようにエージェントのスコープを設定できます。

[Image: スコープ フィルター アイコンのスクリーンショット。]

構成内でグループと組織単位を構成できます。

注

グループ スコープで入れ子になったグループを使用することはできません。 第 1 レベルを超えて入れ子になっているオブジェクトは、セキュリティ グループを使用してスコープを設定するときには含まれません。 大規模なグループの同期には制限があるため、パイロット シナリオではグループ スコープのフィルター処理のみを使用します。

1. **[作業の開始]** 構成画面で、 [**スコープ フィルターの追加**] アイコンの横にある [**スコープ フィルターの追加**] をクリックするか、左側の [**管理**] の下にある [**スコープ フィルター**] をクリックします。

[Image: スコープ フィルターのスクリーンショット。]

1. スコープ フィルターを選択します。 フィルターには、次のいずれかを指定できます。
    - **すべてのユーザー**: 同期されているすべてのユーザーに適用する構成のスコープを設定します。
    - **選択したセキュリティ グループ**: 特定のセキュリティ グループに適用する構成のスコープを設定します。
    - **選択した組織単位**: 特定の OU に適用する構成のスコープを設定します。
2. セキュリティ グループと組織単位の場合は、適切な識別名を指定し、[ **追加**] をクリックします。
3. スコープ フィルターを構成したら、[ **保存**] をクリックします。
4. 保存すると、クラウド同期を構成するためにまだ何を行う必要があるかを示すメッセージが表示されます。リンクをクリックして続行できます。 [Image: スコープ フィルターのナッジのスクリーンショット。]
5. スコープを変更したら、 プロビジョニングを再開 して、変更の即時同期を開始する必要があります。

### 属性マッピング

Microsoft Entra Cloud Sync を使用すると、オンプレミスのユーザー/グループ オブジェクトと Microsoft Entra ID のオブジェクトの間で属性を簡単にマップできます。

[Image: マップ属性アイコンのスクリーンショット。]

既定の属性マッピングをビジネスのニーズに合わせてカスタマイズできます。 そのため、既存の属性マッピングを変更、削除したり、新規の属性マッピングを作成したりすることができます。

[Image: 既定の属性マッピングのスクリーンショット。]

保存すると、クラウド同期を構成するためにまだ何を行う必要があるかを示すメッセージが表示されます。リンクをクリックして続行できます。 [Image: 属性フィルターのナッジのスクリーンショット。]

詳細については、 [属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-attribute-mapping)を参照してください。

### ディレクトリ拡張機能とカスタム属性マッピング。

Microsoft Entra Cloud Sync を使用すると、拡張機能を使用してディレクトリを拡張し、カスタム属性マッピングを提供できます。 詳細については、「 [ディレクトリ拡張機能とカスタム属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping)」を参照してください。

### オンデマンド プロビジョニング

Microsoft Entra Cloud Sync では、これらの変更を 1 人のユーザーまたはグループに適用することで、構成の変更をテストできます。

[Image: テスト アイコンのスクリーンショット。]

これを使用して、構成に加えられた変更が正しく適用され、Microsoft Entra ID に正しく同期されていることを検証して確認できます。

[Image: オンデマンド プロビジョニングのスクリーンショット。]

テスト後、クラウド同期を構成するために必要な操作を示すメッセージが表示されます。リンクをクリックして続行できます。 [Image: テスト用のナッジのスクリーンショット。]

詳細については、「 [オンデマンド プロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision)参照してください。

### 誤削除と電子メール通知

既定のプロパティ セクションでは、誤った削除と電子メール通知に関する情報が提供されます。

[Image: 既定のプロパティ アイコンのスクリーンショット。]

誤って削除する機能は、多くのユーザーやグループに影響を与えるオンプレミス ディレクトリに対する誤った構成変更や変更からユーザーを保護するように設計されています。

この機能では次のことができます。

- 誤って自動的に削除されないようにする機能を構成します。
- 構成を有効にするオブジェクトの数 (しきい値) を設定する
- このシナリオで対象の同期ジョブが検疫されると、通知メール アドレスを設定して電子メール通知を受け取ることができるようにする

詳細については、[誤削除](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-accidental-deletes)に関する情報をご覧ください。

[**基本**] の横にある**鉛筆**をクリックして、構成の既定値を変更します。

[Image: 基本のスクリーンショット。]

### 構成を有効にする

構成を完了してテストしたら、有効にすることができます。

[Image: レビューと有効化アイコンのスクリーンショット。]

[ **構成の有効化]** をクリックして有効にします。

[Image: 構成を有効にするスクリーンショット。]

### 検疫

クラウド同期は、構成の正常性を監視し、異常なオブジェクトを検疫状態に配置します。 ターゲット システムに対して行われた呼び出しのほとんどまたは全部が、無効な管理者資格情報などのエラーのために一貫して失敗する場合、同期ジョブは検疫中としてマークされます。 詳細については、 [検疫](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-troubleshoot#provisioning-quarantined-problems)に関するトラブルシューティングのセクションを参照してください。

### プロビジョニングを再開する

次回のスケジュールされた実行を待機しない場合は、[ **同期の再開** ] ボタンを使用してプロビジョニングの実行をトリガーします。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。

[Image: 同期の再起動のスクリーンショット。]

1. 上部にある [同期の **再起動**] を選択します。

### 構成を削除する

構成を削除するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。

[Image: 削除のスクリーンショット。]

1. 構成画面の上部にある [ **構成の削除**] を選択します。

重要

構成を削除する前に確認はありません。 **[削除**] を選択する前に、これが実行するアクションであることを確認します。

### アンインストール後のポータルからのエージェントの削除

Microsoft Entra Cloud Sync エージェントを [アンインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-automatic-upgrade#uninstall-the-agent) または停止しても、エージェントはすぐに Microsoft Entra 管理センターから削除されることはありません。 次のタイムラインでは、エージェントの削除プロセスについて説明します。

| 時間枠 | ポータルの動作 |
| --- | --- |
| 約 1 時間後 | エージェントはポータルで **非アクティブ** と表示されます。 |
| 約 10 日後 | エージェントは論理的に削除され、ポータルに表示されなくなります。 |
| エージェント証明書の有効期限が切れたとき | エージェントは完全に削除され、Microsoft サービスとやり取りできなくなります。 |

自動クリーンアップを待機しない場合は、エージェントに関連付けられている構成を削除することで、ポータルからエージェント構成を手動で削除できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory"} -->
## Microsoft Entra プロビジョニングのセットアップ (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-19
- Summary: スコープ フィルター、ターゲット コンテナー、属性マッピングを構成して、ユーザーとグループをMicrosoft Entra IDからActive Directoryにプロビジョニングします。

この記事では、Microsoft Entra IDからオンプレミス Active Directory Domain Services (AD DS) に**ユーザーとグループ**をプロビジョニングするように Microsoft Entra Cloud Sync を構成する手順について説明します。 構成の作成、プロビジョニングするオブジェクトの選択、スコープ フィルターと属性値のフィルター処理の設定、ターゲット コンテナーの選択、属性マッピングのカスタマイズを行います。

スコープの選択によって属性値のフィルター処理を使用できるかどうかが決まりますので、ここではスコープと属性マッピングをまとめて説明します。 構成が完了したら、 [テストして有効にします](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-test-and-enable-provisioning-entra-to-active-directory)。これはすべてのデプロイ オプションで同じです。 最後のセクションでは、結果の確認やプロビジョニングされたユーザーの移動など、実行のプロビジョニング後に実行するタスクについて説明します。

AD から Microsoft Entra ID へのプロビジョニングをお探しの場合は、「Active Directory [から Microsoft Entra ID へのプロビジョニングを構成する](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-configure)」を参照してください。

### [前提条件]

続行する前に、「[Microsoft Entra IDからActive Directoryへのプロビジョニングの前提条件」](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-prerequisites-provision-entra-to-active-directory)の手順を完了します。

### 配置オプションを選択する

すべてのデプロイ オプションでは、**AD 同期にMicrosoft Entra ID**、同じ構成の種類が使用されます。異なるのは、スコープに取り込むオブジェクトの種類です。

| オプション | 規定 | Availability |
| --- | --- | --- |
| グループのみ | セキュリティ グループとメンバーシップ | 一般公開 |
| ユーザーのみ | ユーザー | Preview |
| ユーザーとグループ | 両方を1つの構成で | Preview |

オプションの選択とスケールに関する考慮事項のガイダンスについては、「 [デプロイ オプション」](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/concept-deployment-options-provision-to-active-directory)を参照してください。

### 構成を作成する

AD プロビジョニング構成へのMicrosoft Entra IDを作成するには:

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/en-us/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。
3. [ **新しい構成]** を選択します。
4. **[Microsoft Entra ID to AD sync]** を選択します。
5. 構成画面で、ドメインを選択します。 **を選択して**を作成します。
6. [ **作業の開始** ] 画面が開きます。 ここから、クラウド同期の構成を続行できます。

構成は、次の 5 つのセクションに分かれています。

| セクション | 説明 |
| --- | --- |
| 1. スコープ フィルターを追加する | プロビジョニングのスコープ内にあるオブジェクトを定義します。 |
| 2. マップ属性 | Microsoft Entra IDユーザー/グループと AD オブジェクトの間で属性をマップします。 |
| 3. テスト | 構成をデプロイする前にテストします。 |
| 4. 既定のプロパティを表示する | 既定の設定を確認し、必要に応じて変更します。 |
| 5. 構成を有効にする | 構成を有効にし、オブジェクトの同期を開始します。 |

この記事では、セクション 1 と 2 について説明します。 セクション 3 から 5 については、[Active Directoryへのプロビジョニングのテストと有効化に関するセクションを](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-test-and-enable-provisioning-entra-to-active-directory)参照してください。

### スコープ フィルターを構成する

スコープ フィルターによって、プロビジョニングされるオブジェクトが決まります。 **[スコープ フィルター**] ページには、現在の構成の読み取り専用ビューが表示されます。 [ **編集] を** 選択してウィザードを開きます。

1. [**作業の開始**] 画面で、[**スコープ フィルターの追加**] を選択するか、左側の [**管理**] の下にある **[スコープ フィルター**] を選択します。

    [Image: 現在のスコープ設定、割り当て、グループ メンバーシップ、およびターゲット コンテナーを示す [スコープ フィルター] ページのスクリーンショット。]

#### 割り当てによるスコープ

**編集**モードでは、**割り当てによるスコープ**を使用して、すべてのオブジェクトと選択したオブジェクトのどちらを同期するかを選択します。 属性値のフィルター処理は、どちらかの選択肢で使用できますが、その中の 1 つだけに適しています。

- **すべてのユーザーとグループ** — 次の手順は **[属性によるスコープ]です**。 プロビジョニングで必要なオブジェクトのみが評価されるように、有効になっているすべてのオブジェクトの種類 (ユーザー、グループ、またはその両方) に 属性値フィルター を追加します。

    [Image: [割り当てによるスコープ] ステップのスクリーンショット。[すべてのユーザーとグループ] が選択されています。]
- **選択したユーザーとグループ** - 次の手順は[ **ユーザーとグループの選択**]で、特定のオブジェクトを選択します。 属性値のフィルター処理は、このモードで使用できますが、選択によってスコープが既に決定されるため、推奨されません。 詳細については、「 [推奨される構成](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/concept-deployment-options-provision-to-active-directory#recommended-configuration)」を参照してください。

    [Image: [選択したユーザーとグループ] が選択されている [割り当てによるスコープ] ステップのスクリーンショット。]

注記

入れ子になったセキュリティ グループをメンバーとして持つセキュリティ グループを選択した場合、入れ子になったグループのみが書き戻され、そのメンバーは書き戻されません。 入れ子になったメンバーをプロビジョニングするには、すべてのメンバー グループもスコープに追加します。

#### 属性値のフィルター処理

属性ベースのスコープ フィルター処理では、属性値を評価することで、プロビジョニングされるオブジェクトを絞り込みます。 [ **属性別のスコープ]** ステップ、[ **ユーザー** ] タブ、[ **グループ** ] タブ、またはその両方で構成します。

**すべてのユーザーとグループで**使用します。フィルターがスコープを絞り込む唯一の機能です。1 つを使用しないと、テナント内のすべてのユーザーとグループがすべてのサイクルで評価されます。

[Image: [すべてのユーザーとグループ] が選択されている [属性別のスコープ] ステップのスクリーンショット。属性値フィルターが構成されていないことが警告されています。]

**選択したユーザーとグループ**では使用しないでください。選択したユーザーとグループではスコープが既に絞り込まれているため、フィルターを使用すると、プロビジョニングされるオブジェクトを変更せずに処理時間が追加されます。

[Image: [選択したユーザーとグループ] が選択されている [属性によるスコープ] ステップのスクリーンショット。属性値のフィルター処理が不要であることを警告します。]

プロビジョニング構成では、どちらの場合も警告が表示されますが、ブロックされません。 詳細については、「 [推奨される構成](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/concept-deployment-options-provision-to-active-directory#recommended-configuration)」を参照してください。

##### 既定のセキュリティ条項

既定のセキュリティ条件は、作成した条件に加えて、`AND` ロジックを使用してグループに適用されます。

`securityEnabled IS TRUE AND dirSyncEnabled IS FALSE AND mailEnabled IS FALSE`

既定のセキュリティ句は、構成する句の前に評価されます。

##### フィルター 処理ロジック

1 つの句で、1 つの属性値に対して 1 つの条件を定義します。 1 つのスコープ フィルター内の句は、 `AND` で評価されます。すべて `TRUE`する必要があります。

[Image: AND ロジックを使用する 2 つの句を含む属性スコープ フィルターのスクリーンショット。]

複数のスコープ フィルターが `OR` で評価されます。フィルターが `TRUE`されている場合は、オブジェクトがプロビジョニングされます。

[Image: OR ロジックを使用する 2 つの属性スコープ フィルターのスクリーンショット。]

##### 属性ベースのフィルターを作成する

属性値を評価するフィルターを作成するには:

1. **属性スコープ フィルターの追加**を選択します。
2. [ **名前]** に、フィルターの名前を入力します。
3. [ **属性]** で、評価する属性を選択します。
4. [ **演算子**] で、演算子 ( `EQUALS`、 `NOT EQUALS`、 `INCLUDES`、 `REGEX MATCH`など) を選択します。
5. [ **値]** に値を入力します。
6. **保存**を選びます。

##### サポートされている演算子

次の演算子がサポートされています。

| Operator | 説明 |
| --- | --- |
| `EQUALS` | 評価された属性が入力文字列と完全に一致する場合は、 `TRUE` を返します。 比較では、大文字と小文字を区別します。 |
| `GREATER_THAN` | 評価された属性が指定した値より大きい場合は、 `TRUE` を返します。 指定した値と評価される属性は整数である必要があります (例: 0、1、2)。 |
| `GREATER_THAN_OR_EQUALS` | 評価された属性が指定した値以上の場合は、 `TRUE` を返します。 指定された値と評価される属性は整数である必要があります。 |
| `IS FALSE` | 評価された属性に`false`のブール値が含まれている場合は、`TRUE`を返します。 |
| `IS NOT NULL` | 評価された属性が空でない場合は、 `TRUE` を返します。 |
| `IS NULL` | 評価された属性が空の場合、 `TRUE` を返します。 |
| `IS TRUE` | 評価された属性に`true`のブール値が含まれている場合は、`TRUE`を返します。 |
| `NOT EQUALS` | 評価された属性が入力文字列と一致しない場合は、 `TRUE` を返します。 比較では、大文字と小文字を区別します。 |
| `NOT REGEX MATCH` | 評価された属性が正規表現パターンと一致しない場合は、 `TRUE` を返します。 属性が null または空の場合は、 `FALSE` が返されます。 |
| `REGEX MATCH` | 評価された属性が正規表現パターンと一致する場合は、 `TRUE` を返します。 たとえば、 `([1-9][0-9])` は 10 ~ 99 の任意の数値と一致します。 比較では、大文字と小文字を区別します。 |

##### 正規表現を使用してフィルター処理する

より高度なフィルター処理を行うには、 `REGEX MATCH` を使用して、属性文字列で部分文字列を検索します。 たとえば、次の説明があるグループを考えてみましょう。

- `Contoso-Sales-US`
- `Contoso-Marketing-US`
- `Contoso-Operations-US`
- `Contoso-LT-US`

Active Directory に Sales、Marketing、Operations の各グループのみをプロビジョニングするには、次の正規表現を使用します。

```text
REGEX MATCH description (?:^|\W)Sales|Marketing|Operations(?:$|\W)
```

式は、指定された単語のグループの説明を検索し、一致するグループのみをプロビジョニングします。

式の記述の詳細については、[Microsoft Entra IDでの属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/en-us/entra/identity/app-provisioning/functions-for-customizing-application-data)を参照してください。

#### オンプレミス ユーザーのグループ メンバーシップ

グループの同期が有効になっている場合は、必要に応じて、グループ メンバーシップの構成手順でクラウド グループからオンプレミス ユーザーに **メンバーシップ** をプロビジョニングできます。 この設定は既定ではオフになっています。

[Image: オンプレミス ユーザーにメンバーシップをプロビジョニングするオプションを含む [グループ メンバーシップの構成] ステップのスクリーンショット。]

#### ディレクトリ拡張機能を使用してスコープを設定する

より高度なスコープとフィルター処理を行うには、ディレクトリ拡張機能を使用できます。 概要については、[Active DirectoryへのプロビジョニングMicrosoft Entra IDのディレクトリ拡張機能に関するページを](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/custom-attribute-mapping-entra-to-active-directory)参照してください。 詳細なチュートリアルについては、「[Active Directoryへのプロビジョニング時のディレクトリ拡張機能の使用](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/tutorial-directory-extension-group-provisioning)」を参照してください。

### ターゲット コンテナーを構成する

**ターゲット コンテナー**を使用して、ユーザー オブジェクトとグループ オブジェクトがActive Directoryに作成される組織単位 (OU) を制御します。 `parentDistinguishedName`属性では、**定数**、**直接**、または**式**のマッピングを使用できます。

[Image: ユーザーとグループのターゲット コンテナーを示す [ターゲット コンテナーの構成] ステップのスクリーンショット。]

- **ユーザー** — 既定のユーザー ターゲット コンテナーは、ソース オブ オーソリティ (SOA) がクラウドに変換されたユーザーの元の OU を自動的に保持します (既定の `parentDistinguishedName` 式では `onPremisesDistinguishedName`が使用されます)。 クラウドネイティブ ユーザーは、 `CN=Users,DC=<selected AD domain>`で作成され、これをオーバーライドできます。

    [Image: ユーザー ターゲット コンテナーのマップに使用される式のスクリーンショット。]
- **グループ** — 既定のグループ ターゲット コンテナーは `CN=Users,DC=<selected AD domain>`。 属性によって異なる OU にグループを配置するには、 `Switch()` 関数で式を使用します。 次の例では、表示名でグループをルーティングします。

    ```text
    Switch([displayName],"OU=Default,OU=container,DC=contoso,DC=com","Marketing","OU=Marketing,OU=container,DC=contoso,DC=com","Sales","OU=Sales,OU=container,DC=contoso,DC=com")
    ```

    [Image: 複数のグループ ターゲット コンテナーを構成する式のスクリーンショット。]

    表示名は、変更される可能性があり、一貫性のあるパターンに従うとは限らないため、弱いルーティング キーです。 安定した値でグループをルーティングしたり、既に占有している組織単位にグループを保持したりするには、代わりにディレクトリ拡張機能を使用します。 詳細については、「 OU パスを保持する」を参照してください。

#### OU パスを保持する

元の OU の保持方法は、オブジェクトの種類によって異なります。

- **Users** — 保全機能は標準搭載です。 既定の `parentDistinguishedName` マッピングでは、SOA 変換されたユーザーが元の OU に再作成されるため、追加の構成は必要ありません。
- **グループ** — 元の OU は自動的に検出されません。 SOA を変換する前に、 `GroupDN` ディレクトリ拡張機能を使用してグループの識別名 (DN) をキャプチャし、OU 内でその拡張機能と共通名 (CN) マッピング式を参照します。

拡張機能を設定するには、「 [グループの組織単位と名前を保持](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-preserve-group-organizational-unit-entra-to-active-directory)する」を参照してください。 グループをクラウド管理に変換する前に、その設定を完了してから、次の式を使用します。

#### グループの元の組織単位を保持する

SOA 変換されたグループを元の OU にマップするには、 `extension_<AppIdWithoutHyphens>_GroupDN` をテナント内の拡張属性名に置き換えてサンプル式を調整し、拡張値が空のときに使用するターゲット OU に `<Default ParentDistinguishedName>` します。

```text
IIF(
    IsPresent([extension_<AppIdWithoutHyphens>_GroupDN]),
    Replace(
        Mid(
            Mid(
                Replace([extension_<AppIdWithoutHyphens>_GroupDN], "\,", , , "\2C", , ),
                Instr(Replace([extension_<AppIdWithoutHyphens>_GroupDN], "\,", , , "\2C", , ), ",", , ),
                9999
            ),
            2,
            9999
        ),
        "\2C", , , ",", ,
    ),
    "<Default ParentDistinguishedName>"
)
```

`GroupDN`拡張機能が空の場合に使用するターゲット OU に**既定値 (null の場合)** を設定します。

ターゲット コンテナー マッピングに式を適用します。

1. [ **グループ ターゲット コンテナー**] で、[ **属性マッピングの編集]** を選択します。
2. **[マッピングの種類]** を **[式]** に変更します。
3. 式ボックスに式を貼り付けます。
4. [ **適用]** を選択し、[ **保存]** を選択します。

#### グループの元の共通名を保持する

共通名 (CN) を保持するには、同じ `GroupDN` 拡張子を使用します。 式は、格納されている識別名から CN を抽出し、フォールバックとしてグループ表示名とオブジェクト ID を使用します。

```text
IIF(
    IsPresent([extension_<AppIdWithoutHyphens>_GroupDN]),
    Replace(
        Replace(
            Replace(
                Word(Replace([extension_<AppIdWithoutHyphens>_GroupDN], "\,", , , "\2C", , ), 1, ","),
                "CN=", , , "", ,
            ),
            "cn=", , , "", ,
        ),
        "\2C", , , ",", ,
    ),
    Append(Append(Left(Trim([displayName]), 51), "_"), Mid([objectId], 25, 12))
)
```

式を `cn` 属性マッピングに適用し、[ **スキーマの保存]** を選択します。

スコープ フィルターとターゲット コンテナーの構成が完了したら、[ **保存]** を選択します。 次に構成する内容を示すメッセージ。リンクを選択して続行します。

### 属性マッピングを構成する

Microsoft Entra Cloud Sync は、Microsoft Entra IDユーザー/グループと AD オブジェクトの間で属性をマップします。 AD スキーマは検出できないため、AD 構成への各Microsoft Entra IDでは、固定の既定のマッピング セットが使用されます。このマッピングは、ビジネス ニーズに合わせてカスタマイズできます。

#### 同じグループ名を保持する

既定では、`sAMAccountName`はMicrosoft Entra IDからActive Directoryに同期されないため、AD で新しく作成されたグループはランダムに生成された`sAMAccountName`を受け取ります。 AD で一貫したグループ名を保持するには、 `sAMAccountName`へのカスタム マッピングを作成します。 たとえば、次の式を使用します。

```text
Join("_", [displayName], "Contoso_Group")
```

式は、 `displayName` 値と `Contoso_Group`を結合します。 たとえば、表示名が`Marketing`グループは、`Marketing_Contoso_Group``sAMAccountName`値を受け取ります。

重要

すべてのカスタム `sAMAccountName`値がActive Directory内で一意であることを確認します。

#### 必要に応じて他の属性マッピングを変更する

次の表に、既定のユーザーとグループの属性マッピングを示します。 これらのマッピングは、要件に基づいて変更できます。既存のマッピングを変更したり、マッピングを削除したり、新しいマッピングを追加したりできます。 一部のマッピングはサービスによって管理され、編集できません。

マッピングを追加するには:

1. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。
2. [**構成]** で、AD 構成へのMicrosoft Entra IDを選択します。
3. 左側の [ **属性マッピング**] を選択します。
4. 上部で、マッピングするオブジェクトの種類 ( **ユーザー**、 **グループ**、または連絡先) を選択 **します**。
5. [ **属性マッピングの追加]** を選択し、マッピングの種類を選択します。

    - **Direct** — ターゲット属性はソース属性の値を受け取ります。
    - **定数** — ターゲット属性は、指定した固定文字列を受け取ります。
    - **Expression** — ターゲット属性は、式の結果を受け取ります。
    - **なし** — ターゲット属性は変更されません。
6. 選択したマッピングの種類の残りのオプションを入力し、マッピングを適用するタイミングを選択して、[ **適用**] を選択します。
7. [ **スキーマの保存] を選択します**。 スキーマを保存すると、同期がトリガーされます。

式の記述の詳細については、[Microsoft Entra IDでの属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/en-us/entra/identity/app-provisioning/functions-for-customizing-application-data)を参照してください。

##### ユーザー属性マッピング

| ターゲット属性 (Active Directory) | ソース属性 (Microsoft Entra ID) | マッピングの種類 |
| --- | --- | --- |
| `accountDisabled` | `Not([accountEnabled])` | Expression |
| `cn` | `Append(Append(Left(Trim([displayName]), 51), "_"), Mid([objectId], 25, 12))` | Expression |
| `co` | `IgnoreFlowIfNullOrEmpty(Trim([country]))` | Expression |
| `company` | `IgnoreFlowIfNullOrEmpty(Trim([companyName]))` | Expression |
| `department` | `IgnoreFlowIfNullOrEmpty(Trim([department]))` | Expression |
| `displayName` | `displayName` | 直接 |
| `employeeID` | `IgnoreFlowIfNullOrEmpty([employeeId])` | Expression |
| `givenName` | `IgnoreFlowIfNullOrEmpty(Trim([givenName]))` | Expression |
| `l` | `IgnoreFlowIfNullOrEmpty(Trim([city]))` | Expression |
| `manager` | `manager` | 直接 |
| `mobile` | `IgnoreFlowIfNullOrEmpty(Trim([mobile]))` | Expression |
| `msDS-ObjectSoa` | `Cloud` | 定数 |
| `parentDistinguishedName` | 元のOUを保持した表現 | Expression |
| `postalCode` | `IgnoreFlowIfNullOrEmpty(Trim([postalCode]))` | Expression |
| `preferredLanguage` | `IgnoreFlowIfNullOrEmpty(Trim([preferredLanguage]))` | Expression |
| `sAMAccountName` | `Left(Item(Split([userPrincipalName], "@"), 1), 15)` | Expression |
| `sn` | `IgnoreFlowIfNullOrEmpty(Trim([surname]))` | Expression |
| `st` | `IgnoreFlowIfNullOrEmpty(Trim([state]))` | Expression |
| `streetAddress` | `IgnoreFlowIfNullOrEmpty(Trim([streetAddress]))` | Expression |
| `userPrincipalName` | `IIF(IsPresent([onPremisesUserPrincipalName]), [onPremisesUserPrincipalName], Append(Item(Split([userPrincipalName], "@"), 1), Append("@", %DomainFQDN%)))` | Expression |

一部のユーザー マッピング ( `adminDescription` や `msDS-ExternalDirectoryObjectId`など) はサービスによって管理され、UI に表示されず、編集できません。

##### グループ属性マッピング

| ターゲット属性 (Active Directory) | ソース属性 (Microsoft Entra ID) | マッピングの種類 |
| --- | --- | --- |
| `cn` | `Append(Append(Left(Trim([displayName]),51),"_"),Mid([objectId],25,12))` | Expression |
| `description` | `Left(Trim([description]),448)` | Expression |
| `displayName` | `displayName` | 直接 |
| `isSecurityGroup` | `True` | 定数 |
| `member` | `members` | 直接 |
| `parentDistinguishedName` | `CN=Users,DC=<selected AD domain>` | 定数 |
| `UniversalScope` | `True` | 定数 |

サービス管理グループ マッピング (`adminDescription`、 `msDS-ExternalDirectoryObjectId`、 `ObjectGUID`) は UI に表示されず、編集することはできません。

#### ディレクトリ拡張機能とカスタム属性マッピング

Microsoft Entra Cloud Sync を使用すると、ディレクトリ拡張機能を追加し、カスタム属性にマップできます。 詳細については、[Active DirectoryにMicrosoft Entra IDをプロビジョニングするためのディレクトリ拡張機能を](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/custom-attribute-mapping-entra-to-active-directory)参照してください。

属性マッピングが完了したら、[ **スキーマの保存]** を選択します。 次に構成する内容を示すメッセージ。リンクを選択して続行します。

### プロビジョニングされたオブジェクトの確認と管理

次のタスクは、プロビジョニングの実行後に適用されます。 作業する前に、 [構成をテストして有効にします](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-test-and-enable-provisioning-entra-to-active-directory)。

#### プロビジョニングの確認

SOA を変換し、オブジェクトがスコープ内にある場合は、プロビジョニングを実行して結果を確認します。 これらの手順は、ユーザーのみ、グループのみ、またはユーザーとグループの 3 つの展開オプションすべてに適用されます。

**[グループ]:**

- SOA に変換されたグループがプロビジョニング構成のグループ スコープで使用可能であることを確認します。

    [Image: プロビジョニング構成のグループ スコープで選択された SOA に変換されたグループのスクリーンショット。]
- ジョブが実行されると、SOA 変換されたグループが正常にプロビジョニングされます。 **プロビジョニング ログで**、グループを検索し、プロビジョニングされたことを確認します。

    [Image: プロビジョニング ログでのグループの更新が成功したスクリーンショット。]
- プロビジョニング ログの詳細を開き、グループが既存のターゲット グループと **一致** したことを確認します。

    [Image: Active Directoryで一致および更新されたグループを示すプロビジョニング ログの詳細のスクリーンショット。]
- [ **変更されたプロパティ** ] タブで、ターゲット グループの `adminDescription` 属性と `cn` 属性が更新されていることを確認します。

    [Image: 更新された cn 属性と adminDescription 属性を示すプロビジョニング ログの変更されたプロパティのスクリーンショット。]

##### AD DS で確認する

**Active Directory ユーザーとコンピューター**で、重複するグループが作成されるのではなく、想定される OU で元のグループが更新されていることを確認します。

[Image: Active Directory組織単位のプロビジョニング済みセキュリティ グループのスクリーンショット。]

グループのプロパティを開き、想定されるグループ名、スコープ、および属性が存在することを確認します。

[Image: Active Directoryで更新された SOA に変換されたグループの全般プロパティのスクリーンショット。]

[Image: SOA で変換されたグループの属性エディターの更新されたスクリーンショット。識別名、cn、adminDescription の値が表示されています。]

#### クラウドは、SOA 変換後に AD で行われた変更をスキップします

オブジェクトの SOA をクラウドに変換すると、クラウドが権限のソースになります。 そのオブジェクトの属性を AD DS で直接編集する場合 (グループ名の変更など)、Cloud Sync はプロビジョニング中にオブジェクトを **スキップ** します。 **プロビジョニング ログ**では、オブジェクトは**スキップ済**みと表示され、SOA がクラウドに変換されるため、オブジェクトが同期されていないことが詳細に説明されています。

[Image: SOA で変換されたグループの名前がActive Directoryで直接変更されているスクリーンショット。]

[Image: [スキップ済み] 状態の変更された SOA 変換済みグループを示すプロビジョニング ログのスクリーンショット。]

[Image: グループの SOA がクラウドに変換されたためにエクスポートがスキップされたことを説明するプロビジョニング ログの詳細のスクリーンショット。]

#### プロビジョニングされたユーザーを別の組織単位に移動する

ユーザーがプロビジョニングされると、 **ターゲット コンテナー** を変更しても移動は行われず、AD DS でオブジェクトを手動で移動することは、次の同期サイクルで元に戻されます。 既定の `parentDistinguishedName` 式では、ユーザーが既にオンプレミスの場所を持っているかどうかを確認するため、この動作が想定されます。

```text
IIF(IsNullOrEmpty([onPremisesDistinguishedName]), "OU=Cloud_Users,DC=contoso,DC=com", Replace([onPremisesDistinguishedName], , "^.*?,(?=(?:CN|OU|DC)=)", , "", , ))
```

`onPremisesDistinguishedName`が空の場合、指定した定数ターゲット コンテナーにユーザーが作成されます。 値が設定されている場合、式は定数の代わりにその属性から OU を導出し、定数は無視します。 この優先順位により、SOA がクラウドに変換されるユーザーの元の OU が保持されます。 また、既にプロビジョニングされているユーザーがクラウド オブジェクトに記録された OU に戻り続けるのもそのためです。

クラウドネイティブ ユーザーは、最初のプロビジョニング サイクルまで `onPremisesDistinguishedName` を持たず、クラウド オブジェクトに値を書き戻します。 書き戻される属性の完全な一覧については、[Microsoft Entra IDに書き戻されるオンプレミスの属性を](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-provisioning-to-active-directory-works#on-premises-attributes-written-back-to-microsoft-entra-id)参照してください。

[Image: オンプレミスの識別名を示すMicrosoft Entra IDのユーザーのオンプレミス プロパティのスクリーンショット。]

ユーザーを移動するには、変更のスコープに一致するオプションを使用します。

- **マッピング ルールに一致するすべてのユーザー** — ターゲット コンテナー マッピングを変更して、新しい OU をポイントするようにし、[ **保存]** を選択します。 プロビジョニングは再起動し、次のサイクルでマッピングルールに一致するすべてのユーザーを移動します。
- **1 人のユーザー** — Microsoft Entra IDでそのユーザーの`onPremisesDistinguishedName`属性を更新します。
- **別のドメインに移行するユーザー** - 現在の構成のスコープからユーザーを削除し、他のドメインを対象とする構成のスコープにユーザーを追加します。

`onPremisesDistinguishedName`属性は、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/en-us/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールが割り当てられている場合を除き、読み取り専用です。 Microsoft Entra 管理センターでは編集用のオンプレミス属性が公開されないため、Microsoft Graphを使用して 1 人のユーザーを更新します。

```http
PATCH https://graph.microsoft.com/v1.0/users/{user-id}
Content-Type: application/json

{
    "onPremisesDistinguishedName": "CN=Cloud User,OU=Cloud_Users,DC=contoso,DC=com"
}
```

`204 No Content`応答によって更新が確認されます。 ユーザーは、次の同期サイクルで新しい OU に移動します。

[Image: オンプレミスの識別名を更新し、204 No Content を返すMicrosoft Graph PATCH 要求のスクリーンショット。]

移動を確認するには、 **プロビジョニング ログ** でユーザーのエントリを開き、[ **変更されたプロパティ**] を選択します。 `parentDistinguishedName`行には、古い値と新しい値が表示されます。

[Image: 古い parentDistinguishedName 値と新しい parentDistinguishedName 値を表示したプロビジョニング ログの詳細のスクリーンショット。]

#### SOA 変換されたユーザーまたはグループをロールバックする

SOA に変換されたユーザーまたはグループをロールバックして、AD DS が再び所有するようにすると、プロビジョニングはそのオブジェクトの変更の同期を停止し、構成スコープから削除します。 オンプレミス オブジェクトは削除されず、次の同期サイクルでオンプレミスの制御が再開されます。

ロールバックを確認するには、 **監査ログ** を調べて、オブジェクトがオンプレミスで管理されているためにオブジェクトの同期が行えなくなったことを確認し、そのオブジェクトがまだ存在することを AD DS で確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-expression-builder"} -->
## Microsoft Entra Cloud Sync で式ビルダーを使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-expression-builder
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、クラウド同期で式ビルダーを使用する方法について説明します。

式ビルダーは、クラウド同期の下にある Azure の新しい関数です。複雑な式を作成するのに役立ちます。 クラウド同期環境に適用する前に、この式を使用してこれらの式をテストできます。

### 式ビルダーを使用する

数式ビルダーにアクセスするには:

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. **[構成]** で、自分の構成を選択します。
2. **[属性の管理]** で、**[クリックしてマッピングを編集します]** を選びます。
3. **[属性マッピングの編集]** ウィンドウで、**[属性マッピングの追加]** を選択します。
4. **[マッピングの種類]** で **[式]** を選びます。
5. \*\*[式ビルダーを試す]\*\* を選択します。

    [Image: 式ビルダーの使用を示すスクリーンショット。]

### 式を作成する

このセクションでは、ドロップダウン リストを使用して、サポートされている関数から選択します。 次に、選択した関数に応じて、さらに多くのボックスを入力します。 **式**を適用を選択すると、**式入力** ボックスに構文が表示されます。

たとえば、ドロップダウン リストから [ の置換] 選択すると、さらに多くのボックスが表示されます。 関数の構文は、水色のボックスに表示されます。 表示されるボックスは、選択した関数の構文に対応します。 Replace は、指定されたパラメーターによって動作が異なります。

この例では、**oldValue** と **replacementValue** が指定されると、ソース内のすべての **oldValue** が **replacementValue**に置き換えられます。

詳細については、「[置換](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-expressions#replace)」を参照してください。

最初に行う必要があるのは、replace 関数のソースである属性を選択することです。 この例では、**メール** 属性が選択されています。

次に、**の oldValue** のボックスを見つけ、「**@fabrikam.com**」を入力します。 最後に、**replacementValue** のボックスに値 **@contoso.com** を入力します。

式は基本的に、@fabrikam.com の値を持つユーザー オブジェクトのメール属性を @contoso.com 値に置き換えます。 **[式の追加]** を選択すると、**[式の入力]** ボックスに構文が表示されます。

手記

**Replace** を選択した場合に発生する構文に基づいて、**oldValue** および **replacementValue** に対応するボックスに値を配置してください。

サポートされている式の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-expressions)での属性マッピングの式の記述」を参照してください。

#### 式ビルダーの入力ボックスに関する情報

選択した関数に応じて、式ビルダーによって提供されるボックスは複数の値を受け入れます。 たとえば、JOIN 関数は、特定の属性に関連付けられている文字列または値を受け入れます。 たとえば、**[givenName]** の属性値に含まれる値を使用し、**@contoso.com** の文字列値と結合して電子メール アドレスを作成できます。

[Image: 入力ボックスの値を示すスクリーンショット。]

許容される値と式を記述する方法の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-expressions)での属性マッピングの式の記述」を参照してください。

### 式をテストする

このセクションでは、式をテストできます。 ドロップダウン リストから、**メール** 属性を選択します。 値に **@fabrikam.com**を入力し、**テスト式**を選択します。

値 **@contoso.com** が **[式の出力の表示]** ボックスに表示されます。

[Image: 式のテストを示すスクリーンショット。]

### 式をデプロイする

式に問題がなければ、**[式の適用]** を選択します。

[Image: 式の追加を示すスクリーンショット。]

このアクションにより、エージェント構成に式が追加されます。

[Image: エージェントの構成を示すスクリーンショット。]

### 式に NULL 値を設定する

属性の値を NULL に設定するには、`""`の値を持つ式を使用します。 この式は、NULL 値をターゲット属性にフローします。

[Image: NULL 値を示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets"} -->
## Microsoft Entra プロビジョニング エージェント gMSA PowerShell コマンドレット - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: Microsoft Entra プロビジョニング エージェント gMSA PowerShell コマンドレットの使用方法について説明します。

このドキュメントの目的は、Microsoft Entra Connect クラウド プロビジョニング エージェント gMSA PowerShell コマンドレットの使用方法について説明することです。 これらのコマンドレットを使用すると、サービス アカウント (gMSA) に対して適用されるアクセス許可をより細かく設定できます。 既定では、クラウド プロビジョニング エージェントのインストール中に、Microsoft Entra クラウド同期によって Microsoft Entra Connect に似たすべてのアクセス許可が既定の gMSA またはカスタム gMSA に対して適用されます。

このドキュメントでは、次のコマンドレットについて説明します。

`Set-AADCloudSyncPermissions`

`Set-AADCloudSyncRestrictedPermissions`

### コマンドレットの使用方法:

これらのコマンドレットを使用するには、次の前提条件があります。

1. プロビジョニング エージェントをインストールします。
2. プロビジョニング エージェント PowerShell モジュールを PowerShell セッションにインポートします。

    ```powershell
    Import-Module "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\Microsoft.CloudSync.Powershell.dll"
    ```
3. これらのコマンドレットには、渡すことができる `Credential` というパラメーターが必要です。コマンド ラインで指定されていない場合は、ユーザーにプロンプトを表示します。 使用されるコマンドレット構文に応じて、これらの資格情報は、エンタープライズ管理者アカウントであるか、または少なくともアクセス許可を設定しているターゲット ドメインのドメイン管理者である必要があります。
4. 資格情報の変数を作成するには、次を使用します。

    `$credential = Get-Credential`
5. クラウド プロビジョニング エージェント用の Active Directory アクセス許可を設定するには、次のコマンドレットを使用できます。 これにより、ドメインのルートにアクセス許可が付与され、サービス アカウントがオンプレミスの Active Directory オブジェクトを管理できるようになります。 アクセス許可の設定例については、下の「Set-AADCloudSyncPermissions を使用する」を参照してください。

    `Set-AADCloudSyncPermissions -EACredential $credential`
6. クラウド プロビジョニング エージェント アカウントに対して既定で設定されている Active Directory アクセス許可を制限するには、次のコマンドレットを使用できます。 これにより、アクセス許可の継承を無効にし、管理者の SELF とフル コントロールを除くすべての既存のアクセス許可を削除することで、サービス アカウントのセキュリティが向上します。 アクセス許可の制限例については、下の「Set-AADCloudSyncRestrictedPermissions を使用する」を参照してください。

    `Set-AADCloudSyncRestrictedPermission -Credential $credential`

### Set-AADCloudSyncPermissions を使用する

`Set-AADCloudSyncPermissions` でサポートされている次のアクセス許可の種類は、AD Connect Classic Sync (ADSync) で使用されるアクセス許可と同じです。 次のアクセス許可の種類がサポートされています。

| アクセス許可の種類 | 説明 |
| --- | --- |
| BasicRead | Microsoft Entra Connect の [BasicRead](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-configure-ad-ds-connector-account#configure-basic-read-only-permissions) アクセス許可を参照 |
| PasswordHashSync | Microsoft Entra Connect の [PasswordHashSync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-configure-ad-ds-connector-account#permissions-for-password-hash-synchronization) アクセス許可を参照 |
| パスワード書き戻し (PasswordWriteBack) | Microsoft Entra Connect の [PasswordWriteBack](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-configure-ad-ds-connector-account#permissions-for-password-writeback) アクセス許可を参照 |
| ハイブリッドエクスチェンジ権限 | Microsoft Entra Connect の [HybridExchangePermissions](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-configure-ad-ds-connector-account#permissions-for-exchange-hybrid-deployment) アクセス許可を参照 |
| ExchangeMailPublicFolderPermissions | Microsoft Entra Connect の [ExchangeMailPublicFolderPermissions](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-configure-ad-ds-connector-account#permissions-for-exchange-mail-public-folders) アクセス許可を参照 |
| ユーザーグループ作成削除 | Microsoft Entra Cloud Sync の AD へのグループ プロビジョニングのアクセス許可。 [このオブジェクトとすべての子孫オブジェクト] に [ユーザー オブジェクトの作成/削除] を適用し、[このオブジェクトとすべての子孫オブジェクト] に [グループ オブジェクトの作成/削除] を適用します |
| すべて | 上のすべてのアクセス許可が適用されます。 |

AADCloudSyncPermissions を次のいずれかの方法で使用できます。

- 構成されているすべてのドメインにアクセス許可を付与する
- 特定のドメインにアクセス許可を付与する

### 構成されているすべてのドメインにアクセス許可を付与する

構成されているすべてのドメインに特定のアクセス許可を付与するには、エンタープライズ管理者アカウントを使用する必要があります。

```powershell
$credential = Get-Credential
Set-AADCloudSyncPermissions -PermissionType "Any mentioned above" -EACredential $credential 
```

### 特定のドメインにアクセス許可を付与する

特定のドメインに特定のアクセス許可を付与するには、ターゲット ドメインのエンタープライズ管理者またはドメイン管理者である TargetDomainCredential を使用する必要があります。 TargetDomain は、ウィザードを使用して既に構成されている必要があります。

```powershell
$credential = Get-Credential
Set-AADCloudSyncPermissions -PermissionType "Any mentioned above" -TargetDomain "FQDN of domain" -TargetDomainCredential $credential
```

### Set-AADCloudSyncRestrictedPermissions を使用する

セキュリティを強化するために、 `Set-AADCloudSyncRestrictedPermissions` はクラウド プロビジョニング エージェント アカウント自体に設定されたアクセス許可を調整します。 クラウド プロビジョニング エージェント アカウントに対するアクセス許可の厳格化には、次の変更が含まれます。

- 継承の無効化
- SELF に固有の ACE を除くすべての既定のアクセス許可を削除します。
- システム管理者、ドメイン管理者、エンタープライズ管理者にフル コントロール アクセス許可を設定します。
- 認証済みユーザーとエンタープライズ ドメイン コントローラーに読み取りアクセス許可を設定します。

    -Credential パラメーターは、クラウド プロビジョニング エージェント アカウントに対する Active Directory のアクセス許可を制限するために必要な特権を持つ、管理者アカウントを指定するために必要です。 これは通常、ドメイン管理者またはエンタープライズ管理者です。

たとえば次のようになります。

```powershell
$credential = Get-Credential 
Set-AADCloudSyncRestrictedPermissions -Credential $credential  
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-inbound-synch-ms-graph"} -->
## MS Graph API を使用してクラウドの同期をプログラムで構成する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-inbound-synch-ms-graph
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: このトピックでは、Graph API のみを使用して受信同期を有効にする方法について説明します

次のドキュメントでは、MSGraph API のみを使用して 0 から同期プロファイルをレプリケートする方法について説明します。 同期プロファイルをレプリケートする方法の構造は、以下の手順で構成されます。 これらは次のとおりです。

- MS Graph API を使用してクラウドの同期をプログラムで構成する方法
    - 基本的なセットアップ
        - テナント フラグを有効にする
    - サービス プリンシパルの作成
    - 同期ジョブを作成する
    - ターゲット ドメインを更新する
    - 構成ブレードでパスワード ハッシュの同期を有効にする
    - Exchange ハイブリッドの書き戻し
    - 不注意による削除
        - しきい値の有効化と設定
        - 削除を許可する
    - 同期ジョブを開始する
    - 状態をレビューする
    - 次のステップ

これらの [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/) のコマンドを使って、運用テナントの同期を有効にします。これは、そのテナントの管理 Web サービスを呼び出すための前提条件となっています。

### 基本的なセットアップ

#### テナント フラグを有効にする

```powershell
Connect-MgGraph -Scopes "Organization.ReadWrite.All" ('-Environment <AzureEnvironment>')
$organizationId = (Get-MgOrganization).Id
$params = @{onPremisesSyncEnabled = $true
}
Update-MgBetaOrganization -OrganizationId $organizationId -BodyParameter $params
```

このコマンドレットを実行すると、テナントの同期が有効になります。 [Get-MgOrganization](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgorganization) を使って組織の ID を取得しています。

### サービス プリンシパルの作成

次に、[AD2AAD アプリケーションまたはサービス プリンシパル](https://learn.microsoft.com/ja-jp/graph/api/applicationtemplate-instantiate)を作成する必要があります

このアプリケーション ID 1a4721b3-e57f-4451-ae87-ef078703ec94 を使用する必要があります。 displayName は、ポータルで使用されている場合は AD ドメインの URL (たとえば contoso.com) ですが、他の名前を付けても構いません。

```
POST https://graph.microsoft.com/beta/applicationTemplates/1a4721b3-e57f-4451-ae87-ef078703ec94/instantiate
Content-type: application/json
{
    displayName: [your app name here]
}
```

### 同期ジョブを作成する

上記のコマンドの出力では、作成されたサービス プリンシパルの objectId が返されます。 この例では、objectId は aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb です。 このサービス プリンシパルに同期ジョブを追加するには、Microsoft Graph を使用します。

同期ジョブの作成に関するドキュメントについては、[こちら](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronization-post-jobs?tabs=http&preserve-view=true&view=graph-rest-beta)を参照してください。

この ID をメモしなかった場合は、次の MS Graph 呼び出しを実行してサービス プリンシパルを確認できます。 この呼び出しを行うには、Directory.Read.All アクセス許可が必要です。

`GET https://graph.microsoft.com/beta/servicePrincipals`

次に、出力からアプリ名を探します。

次の 2 つのコマンドを実行して、2 つのジョブを作成します。1 つはユーザーまたはグループのプロビジョニング用、もう 1 つはパスワード ハッシュ同期用です。 これは 2 回繰り返されている同じ要求ですが、テンプレート ID が異なります。

次の 2 つの要求を呼び出します。

```
POST https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/jobs
Content-type: application/json
{
"templateId":"AD2AADProvisioning"
} 
```

```
POST https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/jobs
Content-type: application/json
{
"templateId":"AD2AADPasswordHash"
}
```

両方を作成する場合は、2 回呼び出す必要があります。

戻り値の例 (プロビジョニングの場合):

```
HTTP 201/Created
{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#servicePrincipals('aaaaaaaa-0000-1111-2222-bbbbbbbbbbbbc')/synchronization/jobs/$entity",
    "id": "AD2AADProvisioning.fc96887f36da47508c935c28a0c0b6da",
    "templateId": "ADDCInPassthrough",
    "schedule": {
        "expiration": null,
        "interval": "PT40M",
        "state": "Disabled"
    },
    "status": {
        "countSuccessiveCompleteFailures": 0,
        "escrowsPruned": false,
        "code": "Paused",
        "lastExecution": null,
        "lastSuccessfulExecution": null,
        "lastSuccessfulExecutionWithExports": null,
        "quarantine": null,
        "steadyStateFirstAchievedTime": "0001-01-01T00:00:00Z",
        "steadyStateLastAchievedTime": "0001-01-01T00:00:00Z",
        "troubleshootingUrl": null,
        "progress": [],
        "synchronizedEntryCountByType": []
    }
}
```

### ターゲット ドメインを更新する

このテナントでは、サービス プリンシパルのオブジェクト識別子とアプリケーション識別子は次のようになります。

```
ObjectId: bbbbbbbb-1111-2222-3333-cccccccccccc
AppId: 00001111-aaaa-2222-bbbb-3333cccc4444
DisplayName: testApp
```

この構成の対象となるドメインを更新する必要があります。そのため、このドメインのシークレットを更新してください。

使用するドメイン名が、オンプレミスのドメイン コントローラーに設定した URL と同じであることを確認します。

```
PUT – https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/secrets
```

実行しようとしている内容に基づいて、次の値配列に次のキーと値のペアを追加します。

- PHS と同期テナント フラグの両方を有効にする { key:"AppKey", value: "{"appKeyScenario":"AD2AADPasswordHash"}" }
- 同期テナント フラグのみを有効にする (PHS を有効にしない) { key: "AppKey", value: "{"appKeyScenario":"AD2AADProvisioning"}" }

```
Request body –
{
   "value": [
              {
                "key": "Domain",
                "value": "{\"domain\":\"ad2aadTest.com\"}"
              }
            ]
}
```

予期される応答は次の通りです: HTTP 204/コンテンツなし

ここで強調表示されている "Domain" 値は、オンプレミスの Active Directory ドメインの名前であり、エントリはそこから Microsoft Entra ID にプロビジョニングされます。

### 構成ブレードでパスワード ハッシュの同期を有効にする

このセクションでは、特定の構成のパスワード ハッシュの同期を有効にする方法について説明します。 この状況は、テナントレベルの機能フラグを有効にする AppKey シークレットとは異なります。 この手順は、1 つのドメインまたは構成のみに適用されます。この手順がエンド ツー エンドで動作するようにするには、アプリケーション キーを PHS に設定する必要があります。

1. スキーマを取得する (非常に大容量):

    ```
    GET –https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/jobs/ [AD2AADProvisioningJobId]/schema
    ```
2. この CredentialData 属性のマッピングを取得する:

    ```
    {
    "defaultValue": null,
    "exportMissingReferences": false,
    "flowBehavior": "FlowWhenChanged",
    "flowType": "Always",
    "matchingPriority": 0,
    "targetAttributeName": "CredentialData",
    "source": {
    "expression": "[PasswordHash]",
    "name": "PasswordHash",
    "type": "Attribute",
    "parameters": []
    }
    ```
3. スキーマで次の名前を持つ次のオブジェクトマッピングを検索する

    - Active Directory Domain Services Users のプロビジョニング
    - Active Directory Domain Services inetOrgPersons のプロビジョニング

    オブジェクト マッピングは、schema.synchronizationRules[0].objectMappings 内にあります (現時点では、1 つの同期規則のみがあると想定)
4. 手順 (2) から CredentialData マッピングを取得し、手順 (3) でオブジェクト マッピングに挿入します

    オブジェクト マッピングは次のようになります。

    ```
    {
    "enabled": true,
    "flowTypes": "Add,Update,Delete",
    "name": "Provision Active Directory users",
    "sourceObjectName": "user",
    "targetObjectName": "User",
    "attributeMappings": [
    ...
    } 
    ```

    **AD2AADProvisioning ジョブと AD2AADPasswordHash ジョブの作成**手順からのマッピングをコピーして attributeMappings 配列に貼り付けます。

    この配列内の要素の順序は重要ではありません (バックエンドによって並べ替えられます)。 名前が配列に既に存在する場合は、この属性マッピングの追加に注意してください。 たとえば、attributeMappings に targetAttributeName CredentialData を含む項目が既にある場合、競合エラーが発生したり、既存のマッピングと新しいマッピングが組み合わされたりすることがあります。 通常、これは望ましい結果ではありません。 バックエンドでの重複除去は行われません。

    このアクションは、Users と inetOrgpersons の両方に対して実行してください。
5. 作成したスキーマを保存します。

    ```
    PUT –
    https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/jobs/ [AD2AADProvisioningJobId]/schema
    ```

要求本文にスキーマを追加します。

### Exchange ハイブリッドの書き戻し (パブリック プレビュー)

このセクションでは、[Exchange ハイブリッドの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/exchange-hybrid)について、プログラムでの有効化/無効化の方法と使用方法を説明します。

Exchange ハイブリッドの書き戻しをプログラムで有効化するには、2 つの手順が必要になります。

1. スキーマの検証
2. Exchange ハイブリッドの書き戻しジョブの作成

#### スキーマの検証

Exchange ハイブリッド ライトバックを有効にして使用する前に、クラウド同期は、オンプレミスの Active Directory が Exchange スキーマを含むように拡張されたかどうかを判断する必要があります。

[directoryDefinition:discover](https://learn.microsoft.com/ja-jp/graph/api/synchronization-directorydefinition-discover?tabs=http&preserve-view=true&view=graph-rest-beta) を使用してスキーマ検出を開始できます。

```
POST https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/jobs/[AD2AADProvisioningJobId]/schema/directories/[ADDirectoryID]/discover
```

予期される応答は次の通りです: HTTP 200/OK

応答は、次の出力のようになります。

```
HTTP/1.1 200 OK
Content-type: application/json
{
  "objects": [
    {
      "name": "user",
      "attributes": [
        {
          "name": "mailNickName",
          "type": "String"
        },
        ...
      ]
    },
    ...
  ]
}
```

ここで、**mailNickName** 属性が存在するかどうかを確認します。 存在していれば、スキーマは検証済みとなります。つまり、Exchange 属性が含まれています。 存在していない場合は、Exchange ハイブリッドの書き戻しの[前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/exchange-hybrid#prerequisites)を再確認してください。

#### Exchange ハイブリッドの書き戻しジョブの作成

スキーマを検証したら、ジョブを作成できます。

```
POST https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/jobs
Content-type: application/json
{
"templateId":"AAD2ADExchangeHybridWriteback"
}
```

### 不注意による削除

このセクションでは、プログラムによる有効化または無効化、およびプログラムによる[誤削除](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-accidental-deletes)の使用方法について説明します。

#### しきい値の有効化と設定

ジョブの設定ごとに使用できるものには次の 2 つがあります。

- DeleteThresholdEnabled - 'true' に設定されている場合は、ジョブの誤削除防止が有効になります。 既定では 'true' に設定されます。
- DeleteThresholdValue - 誤削除防止が有効になっている場合は、ジョブの各実行で許可される削除の最大数を定義します。 既定では、値は 500 に設定されています。 したがって、値が 500 に設定されている場合、各実行で許可される削除の最大数は 499 です。

削除のしきい値の設定は `SyncNotificationSettings` の一部であり、グラフを使用して変更できます。

この構成の対象となる SyncNotificationSettings を更新する必要があるため、シークレットを更新します。

```
PUT – https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/secrets
```

実行しようとしている内容に基づいて、次の値配列に次のキーと値のペアを追加します。

```
Request body -
{
  "value":[
    {
      "key":"SyncNotificationSettings",
      "value": "{\"Enabled\":true,\"Recipients\":\"foobar@xyz.com\",\"DeleteThresholdEnabled\":true,\"DeleteThresholdValue\":50}"
     }
  ]
}
```

例にある "Enabled" の設定は、ジョブが検疫されたときに通知メールを有効または無効にするためのものです。

現時点では、シークレットに対する PATCH 要求はサポートされていません。 そのため、他の値を保持するには、例のように、PUT 要求の本文にすべての値を追加する必要があります。

すべてのシークレットの既存の値は、以下を使用して取得できます。

```
GET https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/secrets 
```

#### 削除を許可する

ジョブが検疫された後に削除が通過できるようにするには、スコープとして "ForceDeletes" のみを使用して再起動を発行する必要があります。

```
Request:
POST https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/jobs/{jobId}/restart
```

```
Request Body:
{
  "criteria": {"resetScope": "ForceDeletes"}
}
```

### 同期ジョブを開始する

ジョブは、次のコマンドを使用して再度取得できます。

`GET https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/jobs/`

ジョブを取得するためのドキュメントについては、[こちら](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronization-list-jobs?tabs=http&preserve-view=true&view=graph-rest-beta)を参照してください。

ジョブを開始するには、最初の手順で作成したサービス プリンシパルの objectId と、ジョブを作成した要求から返されたジョブ識別子を使用して、以下の要求を発行します。

ジョブを開始する方法のドキュメントについては、[こちら](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-start?tabs=http&view=graph-rest-beta&preserve-view=true)を参照してください。

```
POST  https://graph.microsoft.com/beta/servicePrincipals/8895955e-2e6c-4d79-8943-4d72ca36878f/synchronization/jobs/AD2AADProvisioning.fc96887f36da47508c935c28a0c0b6da/start
```

予期される応答は次の通りです: HTTP 204/No content。

ジョブを制御するその他のコマンドについては、[こちら](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-synchronizationjob?view=graph-rest-beta&preserve-view=true)に記載されています。

ジョブを再開するには、次を使用します。

```
POST  https://graph.microsoft.com/beta/servicePrincipals/8895955e-2e6c-4d79-8943-4d72ca36878f/synchronization/jobs/AD2AADProvisioning.fc96887f36da47508c935c28a0c0b6da/restart
{
  "criteria": {
    "resetScope": "Full"
  }
}
```

### 状態をレビューする

次を使用してジョブの状態を取得します。

```
GET https://graph.microsoft.com/beta/servicePrincipals/[SERVICE_PRINCIPAL_ID]/synchronization/jobs/ 
```

関連する詳細については、返されたオブジェクトの 'status' セクションを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-install"} -->
## Microsoft Entra プロビジョニング エージェントをインストールする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-09-18
- Summary: Microsoft Entra プロビジョニング エージェントをインストールする方法と、Microsoft Entra 管理センターでそれを構成する方法について説明します。

この記事では、Microsoft Entra プロビジョニング エージェントのインストール プロセスと、Microsoft Entra 管理センターでその初期構成を行う方法について説明します。

重要

次のインストール手順は、[前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites)がすべて満たされていることを前提としています。

Note

この記事では、ウィザードを使用したプロビジョニング エージェントのインストールについて説明します。 CLI を使用した Microsoft Entra プロビジョニング エージェントのインストールの詳細については、「[CLI と PowerShell を使用した Azure AD プロビジョニング エージェントのインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install-pshell)」に関する記事を参照してください。

詳細と例については、次のビデオをご覧ください。

### グループ管理サービス アカウント

グループ管理サービス アカウント (gMSA) は、パスワードの自動管理、簡略化されたサービス プリンシパル名 (SPN) の管理、および管理を他の管理者に委任する機能を提供する、マネージド ドメイン アカウントです。 また gMSA では、この機能が複数のサーバーにも拡張されます。 Microsoft Entra クラウド同期では、エージェントを実行するための gMSA の使用がサポートされ、推奨されています。 詳細については、「[グループの管理されたサービス アカウントの概要](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#group-managed-service-accounts)」を参照してください。

#### gMSA を使用するよう既存のエージェントを更新する

インストール中に作成されたグループ管理サービス アカウントを使用するように既存のエージェントを更新するには、*AADConnectProvisioningAgent.msi* を実行して、エージェント サービスを最新バージョンに更新します。 ここで、インストール ウィザードを再度実行し、メッセージが表示されたらアカウントを作成するための資格情報を指定します。

### エージェントをインストールする

Note

既定では、Microsoft Entra プロビジョニング エージェントは既定の Azure 環境にインストールされます。

1. Azure portal で、**Microsoft Entra ID** を選択します。
2. 左側のウィンドウで、[ **Microsoft Entra Connect**] を選択し、[ **クラウド同期**] を選択します。

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
12. Active Directory ドメイン管理者アカウントでサインインします。 ドメイン管理者アカウントのパスワードは有効期限が切れていないものである必要があります。 エージェントのインストール中にパスワードの有効期限が切れている場合や変更された場合は、新しい資格情報を使用してエージェントを再構成します。 この操作によってオンプレミス ディレクトリが追加されます。 [ **OK] を**選択し、[ **次へ** ] を選択して続行します。
13. **[次へ]** を選択して続行します。
14. **[構成の完了]** 画面で、 **[確認]** を選択します。 この操作によって、エージェントが登録されて再起動されます。

    [Image: 完了画面を示すスクリーンショット。]
15. 操作が完了すると、エージェントの構成が正常に検証されたことを示す通知が表示されます。 **[終了]** を選択します。 まだ最初の画面が表示される場合は、[ **閉じる**] を選択します。

### エージェントのインストールを確認する

エージェントの検証は、Azure portal と、エージェントを実行するローカル サーバーで行われます。

#### Azure portal でエージェントを確認する

Microsoft Entra ID によってエージェントが登録されていることを確認するには、次の手順に従います。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. **[Microsoft Entra ID]** を選びます。
3. **[Microsoft Entra Connect**] を選択し、[**クラウド同期**] を選択します。

    [Image: [作業の開始] 画面を示すスクリーンショット。]
4. [ **クラウド同期** ] ページに、インストールしたエージェントが表示されます。 エージェントが表示され、状態が **正常**であることを確認します。

#### ローカル サーバー上のエージェントを確認する

エージェントが実行されていることを確認するには、次の手順に従います。

1. 管理者アカウントでサーバーにサインインします。
2. **[サービス**] に移動します。 *Start/Run/Services.msc* を使用してアクセスすることもできます。
3. [ **サービス**] で、 **Microsoft Entra Connect エージェント アップデーター** と **Microsoft Entra Connect プロビジョニング エージェント** が存在し、状態が **[実行中**] であることを確認します。

    [Image: Windows サービスを示すスクリーンショット。]

#### プロビジョニング エージェントのバージョンを確認する

実行中のエージェントのバージョンを確認するには、次の手順に従います。

1. *C:\Program Files\Microsoft Azure AD Connect プロビジョニング エージェントに*移動します。
2. *AADConnectProvisioningAgent.exe* を右クリックし、[プロパティ] を選択**します**。
3. [ **詳細** ] タブを選択します。製品バージョンの横にバージョン番号が表示されます。

重要

エージェントをインストールした後、エージェントによってユーザーの同期が開始される前に、エージェントを構成して有効にする必要があります。 新しいエージェントを構成するには、「[Microsoft Entra クラウド同期の新しい構成を作成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)」を参照してください。

### クラウド同期でパスワード ライトバックを有効にする

ポータルで直接、または PowerShell を使用して、SSPR でパスワード ライトバックを有効にすることができます。

#### ポータルでパスワード ライトバックを有効にする

*パスワード ライトバック*を使用して、セルフサービス パスワード リセット (SSPR) サービスでポータルを使用してクラウド同期エージェントを検出できるようにするには、次の手順を実行します。

1. [ハイブリッド ID の管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[パスワードのリセット]**&gt;**[オンプレミスの統合]** に移動します。
3. **[同期されたユーザーのパスワード ライト バックを有効にする]** のオプションをオンにします。
4. (省略可能) Microsoft Entra Connect プロビジョニング エージェントが検出された場合は、**[Microsoft Entra クラウド同期を使用したパスワード ライトバック]** オプションもオンにできます。
5. **[パスワードをリセットせずにアカウントのロックを解除することをユーザーに許可する]** オプションを *[はい]* にします。
6. 準備ができたら、 **[保存]** を選択します。

#### PowerShell の使用

"パスワード ライトバック" を使って、セルフサービス パスワード リセット (SSPR) サービスでクラウド同期エージェントを検出するには、 コマンドレットとテナントのグローバル管理者資格情報を使う必要があります。`Set-AADCloudSyncPasswordWritebackConfiguration`

```
 Import-Module "C:\\Program Files\\Microsoft Azure AD Connect Provisioning Agent\\Microsoft.CloudSync.Powershell.dll" 
 Set-AADCloudSyncPasswordWritebackConfiguration -Enable $true -Credential $(Get-Credential)
```

Microsoft Entra クラウド同期でパスワード ライトバックを使用する方法の詳細については、「[チュートリアル: クラウド同期セルフサービス パスワード リセットのオンプレミス環境へのライトバックを有効にする (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-cloud-sync-sspr-writeback)」を参照してください。

### クラウド同期でのパスワード ハッシュ同期と FIPS

Federal Information Processing Standard (FIPS) に従ってサーバーがロックされた場合、MD5 (message-digest algorithm 5) は無効になります。

パスワード ハッシュ同期で MD5 を有効にするには、次を実行します。

1. %programfiles%\Microsoft Azure AD Connect Provisioning Agent に移動します。
2. *AADConnectProvisioningAgent.exe.config* を開きます。
3. ファイルの先頭にある configuration/runtime ノードに移動します。
4. `<enforceFIPSPolicy enabled="false"/>` ノードを追加します。
5. 変更内容を保存します。

参考までに、コードは次のスニペットのようになっているはずです。

```xml
<configuration>
   <runtime>
      <enforceFIPSPolicy enabled="false"/>
   </runtime>
</configuration>
```

セキュリティと FIPS の詳細については、[Microsoft Entra のパスワード ハッシュ同期、暗号化、および FIPS コンプライアンス](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/aad-password-sync-encryption-and-fips-compliance/ba-p/243709)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-install-pshell"} -->
## コマンド ライン インターフェイス (CLI) と PowerShell を使用して Microsoft Entra Connect クラウド プロビジョニング エージェントをインストールする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install-pshell
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-09-18
- Summary: PowerShell コマンドレットを使用して Microsoft Entra Connect クラウド プロビジョニング エージェントをインストールする方法について説明します。

この記事では、PowerShell コマンドレットを使用して Microsoft Entra プロビジョニング エージェントをインストールする方法について説明します。

手記

この記事では、コマンド ライン インターフェイス (CLI) を使用したプロビジョニング エージェントのインストールについて説明します。 ウィザードを使用して Microsoft Entra プロビジョニング エージェントをインストールする方法については、「 [Microsoft Entra プロビジョニング エージェントのインストール」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)参照してください。

### 前提

PowerShell コマンドレットを使用して Microsoft Entra プロビジョニング エージェントをインストールする前に、Windows サーバーで TLS 1.2 が有効になっている必要があります。 TLS 1.2 を有効にするには、「 [Microsoft Entra Cloud Sync の前提条件」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#tls-requirements)の手順に従います。

重要

次のインストール手順では、すべての [前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites) が満たされていることを前提としています。

### PowerShell コマンドレットを使用して Microsoft Entra プロビジョニング エージェントをインストールする

手記

既定では、Microsoft Entra プロビジョニング エージェントは既定の Azure 環境にインストールされます。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. **管理**を選択します。
2. [ **プロビジョニング エージェントのダウンロード] を選択する**
3. 右側の [ **同意する] を選択してダウンロード**します。
4. これらの手順のために、エージェントは C:\temp フォルダーにダウンロードされました。
5. ProvisioningAgent をサイレント モードでインストールします。

    ```
    $installerProcess = Start-Process 'c:\temp\ProvisioningAgentSetup.exe' /quiet -NoNewWindow -PassThru 
    $installerProcess.WaitForExit()
    
    ```
6. プロビジョニング エージェント PS モジュールをインポートします。

    ```
    Import-Module "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\Microsoft.CloudSync.PowerShell.dll" 
    ```
7. ハイブリッド ID ロールを持つアカウントを使用して Microsoft Entra ID に接続します。 このセクションをカスタマイズして、セキュリティで保護されたストアからパスワードをフェッチできます。

    ```
    $hybridAdminPassword = ConvertTo-SecureString -String "Hybrid Identity Administrator password" -AsPlainText -Force 
    
    $hybridAdminCreds = New-Object System.Management.Automation.PSCredential -ArgumentList ("HybridIDAdmin@contoso.onmicrosoft.com", $hybridAdminPassword) 
    
    Connect-AADCloudSyncAzureAD -Credential $hybridAdminCreds 
    ```
8. gMSA アカウントを追加し、ドメイン管理者の資格情報を指定して、既定の gMSA アカウントを作成します。

    ```
    $domainAdminPassword = ConvertTo-SecureString -String "Domain admin password" -AsPlainText -Force 
    
    $domainAdminCreds = New-Object System.Management.Automation.PSCredential -ArgumentList ("DomainName\DomainAdminAccountName", $domainAdminPassword) 
    
    Add-AADCloudSyncGMSA -Credential $domainAdminCreds 
    ```
9. または、前のコマンドレットを使用して、事前に作成された gMSA アカウントを指定します。

    ```
    Add-AADCloudSyncGMSA -CustomGMSAName preCreatedGMSAName$ 
    ```
10. ドメインを追加します。

    ```
    $contosoDomainAdminPassword = ConvertTo-SecureString -String "Domain admin password" -AsPlainText -Force 
    
    $contosoDomainAdminCreds = New-Object System.Management.Automation.PSCredential -ArgumentList ("DomainName\DomainAdminAccountName", $contosoDomainAdminPassword) 
    
    Add-AADCloudSyncADDomain -DomainName contoso.com -Credential $contosoDomainAdminCreds 
    ```
11. または、上記のコマンドレットを使用して優先ドメイン コントローラーを構成します。

    ```
    $preferredDCs = @("PreferredDC1", "PreferredDC2", "PreferredDC3") 
    
    Add-AADCloudSyncADDomain -DomainName contoso.com -Credential $contosoDomainAdminCreds -PreferredDomainControllers $preferredDCs 
    ```
12. ドメインを追加するには、前の手順を繰り返します。 それぞれのドメインのアカウント名とドメイン名を指定します。
13. サービスを再起動します。

    ```
    Restart-Service -Name AADConnectProvisioningAgent  
    ```
14. クラウド同期構成を作成するには、Microsoft Entra 管理センターに移動します。

### プロビジョニング エージェント gMSA PowerShell コマンドレット

エージェントをインストールしたら、gMSA により細かいアクセス許可を適用できます。 アクセス許可を構成する方法の詳細と手順については、 [Microsoft Entra Connect クラウド プロビジョニング エージェント gMSA PowerShell コマンドレットを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-manage-registry-options"} -->
## Microsoft Entra Connect クラウド プロビジョニング エージェント: レジストリ オプションを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-manage-registry-options
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra Connect クラウド プロビジョニング エージェントでレジストリ オプションを管理する方法について説明します。

このセクションでは、Microsoft Entra プロビジョニング エージェントのランタイム処理動作を制御するために設定できるレジストリ オプションについて説明します。

### LDAP 接続タイムアウトの構成

構成された Active Directory ドメイン コントローラーに対して LDAP 操作を実行する場合、既定では、プロビジョニング エージェントは既定の接続タイムアウト値 30 秒を使用します。 ドメイン コントローラーの応答に時間がかかる場合は、エージェント ログ ファイルに次のエラー メッセージが表示されることがあります。

`System.DirectoryServices.Protocols.LdapException: The operation was aborted because the client side timeout limit was exceeded.`

検索属性にインデックスが作成されていない場合、LDAP 検索操作に時間がかかる場合があります。 最初の手順として、前述のエラーが発生した場合は、最初に検索/参照属性がインデックス付け かどうかを確認します。 検索属性のインデックスが作成され、エラーが解決しない場合は、次の手順を使用して LDAP 接続のタイムアウトを増やすことができます。

1. Microsoft Entra プロビジョニング エージェントを実行している Windows サーバーで管理者としてサインインします。
2. *実行* メニュー項目を使用して、レジストリ エディター (regedit.exe) を開きます。
3. キー フォルダーの **HKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft\Azure AD Connect Agents\Azure AD Connect Provisioning Agent** を見つける
4. 右クリックし、「新規 -&gt; 文字列値」を選択します。
5. 名前を指定します: `LdapConnectionTimeoutInMilliseconds`
6. **[値の名前]** をダブルクリックし、値データを `60000` ミリ秒として入力します。
[Image: LDAP 接続タイムアウト]
7. *Services* コンソールから Microsoft Entra Connect Provisioning Service を再起動します。
8. 複数のプロビジョニング エージェントをデプロイした場合は、一貫性を保つため、このレジストリの変更をすべてのエージェントに適用します。

### 参照の追跡を構成する

既定では、Microsoft Entra プロビジョニング エージェントは [のリファラルを追跡しません](https://learn.microsoft.com/ja-jp/windows/win32/ad/referrals)。 紹介の追跡を有効にして、次のような特定の HR 受信プロビジョニング シナリオをサポートしたい場合があります。

- 複数のドメインにわたる UPN の一意性の確認
- クロスドメイン マネージャー参照の解決

紹介の追跡を有効にするには、次の手順に従います。

1. Microsoft Entra プロビジョニング エージェントを実行している Windows サーバーで管理者としてサインインします。
2. *実行* メニュー項目を使用して、レジストリ エディター (regedit.exe) を開きます。
3. キー フォルダーの **HKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft\Azure AD Connect Agents\Azure AD Connect Provisioning Agent** を見つける
4. 右クリックし、「新規 -&gt; 文字列値」を選択します。
5. 名前を指定します: `ReferralChasingOptions`
6. **[値の名前]** をダブルクリックし、値データを `96` として入力します。 この値は `ReferralChasingOptions.All` の定数値に対応し、エージェントがサブツリーと基本レベルのリファーラルの両方を追従するように指定します。
[Image: 紹介追跡]
7. *Services* コンソールから Microsoft Entra Connect Provisioning Service を再起動します。
8. 複数のプロビジョニング エージェントをデプロイした場合は、一貫性を保つため、このレジストリの変更をすべてのエージェントに適用します。

手記

[詳細ログ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-troubleshoot#log-files)を有効にすることで、レジストリ オプションが設定されていることを確認できます。 エージェントの起動時に出力されたログには、レジストリから選択された構成値が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-map-usertype"} -->
## Microsoft Entra Cloud Sync で map UserType を使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-map-usertype
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、UserType 属性をクラウド同期にマップする方法について説明します。

クラウド同期では、User オブジェクトの **UserType** 属性の同期がサポートされています。

既定では、 **UserType** 属性は同期に対して有効になっていません。これは、オンプレミスの Active Directory に対応する **UserType** 属性がないためです。 同期のために、このマッピングを手動で追加する必要があります。 この手順を実行する前に、Microsoft Entra ID によって適用される次の動作を書き留めておく必要があります。

- Microsoft Entra のみ、 **UserType** 属性に対して Member と Guest の 2 つの値を受け入れます。
- **UserType** 属性がクラウド同期でマップされていない場合、ディレクトリ同期を使用して作成された Microsoft Entra ユーザーの **UserType** 属性は Member に設定されます。

**UserType** 属性のマッピングを追加する前に、まず、オンプレミスの Active Directory から属性を派生する方法を決定する必要があります。 最も一般的な方法は次のとおりです。

- ソース属性として使用する未使用のオンプレミス Active Directory 属性 (extensionAttribute1 など) を指定します。 指定されたオンプレミスの Active Directory 属性は、型文字列であり、単一値で、値 Member または Guest を含む必要があります。
- この方法を選択した場合は、 **UserType** 属性の同期を有効にする前に、指定された属性に、Microsoft Entra ID に同期されているオンプレミス Active Directory 内のすべての既存のユーザー オブジェクトに対して正しい値が設定されていることを確認する必要があります。

### UserType マッピングを追加する

**UserType** マッピングを追加するには:

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. [ **属性の管理**] で、[ **クリック] を選択してマッピングを編集します**。

    [Image: 属性マッピングの編集を示すスクリーンショット。]
3. [ **属性マッピングの追加] を**選択します。

    [Image: 新しい属性マッピングの追加を示すスクリーンショット。]
4. マッピングの種類を選択します。 マッピングは、次の 3 つの方法のいずれかで実行できます。

- たとえば、Active Directory 属性からの直接マッピング
- 「IIF(InStr([userPrincipalName], "@partners") &gt; 0,"Guest","Member")」のような式
- たとえば、すべてのユーザー オブジェクトを Guest に設定する定数

    [Image: UserType 属性の追加を示すスクリーンショット。]

1. [ **ターゲット属性** ] ドロップダウン ボックスで、[ **UserType**] を選択します。
2. ページの下部にある [ **適用** ] を選択して、Microsoft Entra ID **UserType** 属性のマッピングを作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-on-demand-provision"} -->
## Microsoft Entra Cloud Sync でのオンデマンド プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra Connect のクラウド同期機能を使用して、構成の変更をテストする方法について説明します。

Microsoft Entra Connect のクラウド同期機能を使用して、これらの変更を 1 人のユーザーに適用することで、構成の変更をテストできます。 このオンデマンド プロビジョニングは、構成に加えられた変更が正しく適用され、Microsoft Entra ID に正しく同期されていることを検証し、検証するのに役立ちます。

この記事では、Active DirectoryからMicrosoft Entra IDにプロビジョニングする構成のオンデマンド プロビジョニングについて説明します。 Microsoft Entra IDからActive Directoryへのプロビジョニングに関する情報については、「[オンデマンド プロビジョニング - Microsoft Entra IDからActive Directory」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision-entra-to-active-directory)を参照してください。

重要

オンデマンド プロビジョニングを使用する場合、選択したユーザーにはスコープ フィルターは適用されません。 指定した組織単位外のユーザーに対してオンデマンド プロビジョニングを使用できます。

詳細と例については、次のビデオを参照してください。

### ユーザーを検証する

オンデマンド プロビジョニングを使用するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. 左側の **オンデマンドプロビジョン** を選択します。
3. ユーザーの識別名を入力し、[ **プロビジョニング** ] ボタンを選択します。

[Image: ユーザー識別名のスクリーンショット。]

1. プロビジョニングが完了すると、4 つの緑色のチェック マークが付いた成功画面が表示されます。 エラーがある場合は、左側に表示されます。

[Image: オンデマンドの成功のスクリーンショット。]

### プロビジョニングの詳細を取得する

これで、ユーザー情報を確認し、構成で行った変更が適用されているかどうかを確認できます。 この記事の残りの部分では、正常に同期されたユーザーの詳細に表示される個々のセクションについて説明します。

#### ユーザーのインポート

[ **ユーザーのインポート]** セクションには、Active Directory からインポートされたユーザーに関する情報が表示されます。 これは、Microsoft Entra ID にプロビジョニングする前のユーザーの外観です。 この情報を表示するには、[ **詳細の表示** ] リンクを選択します。

この情報を使用すると、インポートされたさまざまな属性 (およびその値) を確認できます。 カスタム属性マッピングを作成した場合は、ここで値を確認できます。

[Image: ユーザーのインポートのスクリーンショット。]

#### ユーザーがスコープ内にあるかどうかを判断する

[ **ユーザーがスコープ内にあるかどうかを確認** する] セクションでは、Microsoft Entra ID にインポートされたユーザーがスコープ内にあるかどうかに関する情報を提供します。 この情報を表示するには、[ **詳細の表示** ] リンクを選択します。

この情報を使用すると、ユーザーがスコープ内にあるかどうかを確認できます。

[Image: スコープの決定のスクリーンショット。]

#### ソース システムとターゲット システムの間でユーザーを照合する

「 **ソースとターゲットのシステム間のユーザーの照合** 」セクションでは、ユーザーが Microsoft Entra ID に既に存在するかどうか、および新しいユーザーをプロビジョニングする代わりに結合を行う必要があるかどうかに関する情報が提供されます。 この情報を表示するには、[ **詳細の表示** ] リンクを選択します。

この情報を使用すると、一致が見つかったかどうか、または新しいユーザーが作成されるかどうかを確認できます。

[Image: 一致するユーザーのスクリーンショット。]

一致する詳細には、次の 3 つの操作のいずれかを含むメッセージが表示されます。

- **作成**: ユーザーは Microsoft Entra ID で作成されます。
- **更新**: ユーザーは、構成で行われた変更に基づいて更新されます。
- **削除**: ユーザーは Microsoft Entra ID から削除されます。

実行した操作の種類によって、メッセージは異なります。

#### 実行するアクション

**[アクションの実行**] セクションには、構成の適用後に Microsoft Entra ID にプロビジョニングまたはエクスポートされたユーザーに関する情報が表示されます。 これは、Microsoft Entra ID にプロビジョニングした後のユーザーの外観です。 この情報を表示するには、[ **詳細の表示** ] リンクを選択します。

この情報を使用すると、構成の適用後に属性の値を確認できます。 インポートされた内容に似ていますか、それとも異なっていますか? 構成は正常に適用されましたか?

このプロセスを使用すると、クラウドを経由して Microsoft Entra テナントに移動するときに、属性変換をトレースできます。

[Image: アクションの実行のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-on-demand-provision-entra-to-active-directory"} -->
## オンデマンド プロビジョニング - Microsoft Entra ID による Active Directory への提供 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision-entra-to-active-directory
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-24
- Summary: この記事では、Microsoft Entra ID から Active Directory にプロビジョニングするときにオンデマンド プロビジョニングを使用する方法について説明します。

Microsoft Entra Cloud Sync を使用すると、スコープ内のすべてのオブジェクトの構成を有効にする前に、構成の変更を 1 人のユーザーまたはグループに適用してテストできます。

このテストを使用して、構成に加えた変更が正しく適用され、オブジェクトがActive Directoryに正しく同期されていることを検証して確認します。

この記事では、Microsoft Entra IDからActive Directoryにプロビジョニングする構成のオンデマンド プロビジョニングについて説明します。 Active DirectoryからMicrosoft Entra IDへのプロビジョニングに関する情報については、「[オンデマンド プロビジョニング - Active DirectoryからMicrosoft Entra ID」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision)を参照してください。

オンデマンド グループ のプロビジョニングには、次のことが当てはまります。

- グループのオンデマンド プロビジョニングでは、一度に最大 5 つのメンバーの更新がサポートされます。
- オンデマンド プロビジョニングでは、Microsoft Entra ID から削除されたグループの削除はサポートされていません。 これらのグループは、グループを検索するときに表示されません。
- オンデマンド プロビジョニングでは、アプリケーションに直接割り当てられない入れ子になったグループはサポートされません。
- オンデマンド プロビジョニング要求 API は、一度に最大 5 つのメンバーを持つ 1 つのグループのみを受け入れます。

### ユーザーまたはグループを確認する

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. 左側で**Provision on demand**を選択します。
3. テストするオブジェクトの種類に応じて、[ **ユーザー** ] タブまたは [ **グループ** ] タブを選択します。

    [Image: [ユーザー] タブと [グループ] タブがある [オンデマンドプロビジョニング] ページのスクリーンショット。]

次に、選択したオブジェクトの種類の手順に従います。

## [ユーザー](#tab/users)
1. [ **ユーザーの選択**] で、名前でユーザーを検索し、ユーザーを選択します。

    [Image: [オンデマンドプロビジョニング] ページの [ユーザー] タブで選択されているユーザーのスクリーンショット。]
2. **プロビジョン** を選択します。

## [グループ](#tab/groups)
1. **[選択したグループ]** で、名前でグループを検索し、グループを選択します。
2. [ **選択したユーザー**] で、[ **メンバーのみを表示** ] を選択してグループの現在のメンバーから選択するか、[ **すべてのユーザーを表示]** を選択してディレクトリ全体を検索します。 次に、テストするメンバーを選択します。

    手記

    メンバーは自動的にプロビジョニングされません。 テストするメンバーを一度に最大 5 つ選択します。

    [Image: [グループ] タブで選択されているグループのスクリーンショット。テストするメンバーを選択するためのオプションが表示されています。]
3. **プロビジョン** を選択します。

---

### 結果を確認する

結果には、オブジェクトのインポート、スコープ フィルターに対する評価、ターゲット システムとの照合、Active Directoryでのアクションの実行の 4 つの手順が一覧表示されます。 評価された内容を確認するには、[任意のステップの **詳細を表示]** を選択します。

ステップは完了すると**成功**を報告し、何も行わない場合は**スキップ**されます (Active Directory内のオブジェクトが既に一致する場合など)。 同じテストをもう一度実行するには、[再試行] を選択 **します**。 別のオブジェクトをテストするには、[ **別のオブジェクトのプロビジョニング**] を選択します。

## [ユーザー](#tab/users)
[Image: ユーザーのオンデマンド プロビジョニングの結果のスクリーンショット。4 つの手順とその状態が示されています。]

## [グループ](#tab/groups)
[Image: グループのオンデマンド プロビジョニング結果のスクリーンショット。4 つの手順とその状態が示されています。]

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-prerequisites"} -->
## Microsoft Entra ID での Microsoft Entra Cloud Sync の前提条件 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-07-27
- Summary: この記事では、クラウド同期に必要な前提条件とハードウェア要件について説明します。

この記事では、ID ソリューションとして Microsoft Entra Cloud Sync を使用する方法に関するガイダンスを提供します。

### クラウド プロビジョニング エージェントの要件

Microsoft Entraクラウド同期を使用するには、次のものが必要です。

- エージェント サービスを実行する Microsoft Entra Connect クラウド同期 gMSA (グループ管理サービス アカウント) を作成するためのドメイン管理者またはエンタープライズ管理者の資格情報。
- ゲスト ユーザーではないMicrosoft Entra テナントのハイブリッド ID 管理者アカウント。
- Microsoft Entraクラウド同期エージェントは、ドメインに参加しているサーバーにインストールする必要があります。 Windows Server 2025 または Windows Server 2022 を使用することをお勧めします。 延長サポート対象の古いWindows Serverバージョンに Microsoft Entra Cloud Sync をデプロイすることもできますが、この構成のサポートには[有料サポート プログラム](https://learn.microsoft.com/ja-jp/lifecycle/policies/fixed#extended-support)が必要になる場合があります。
- このサーバーは、[Active Directory管理層モデル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model)に基づく階層 0 サーバーである必要があります。 ドメイン コントローラーへのエージェントのインストールがサポートされています。 詳細については、「Microsoft Entra プロビジョニング エージェント サーバーの強化」をご覧ください
- Active Directory スキーマには、Windows Server 2016 以降で使用できる msDS-ExternalDirectoryObjectId 属性が必要です。
- Windows Credential Manager サービス (VaultSvc) は、プロビジョニング エージェントのインストールを妨げるので無効にできません。
- 高可用性とは、Microsoft Entraクラウド同期が長時間障害なく継続的に動作する機能を指します。 複数のアクティブなエージェントをインストールして実行することで、1 つのエージェントで障害が発生した場合でも、Microsoft Entra Cloud Sync を引き続き機能させることができます。 Microsoft では、高可用性のために 3 つのアクティブなエージェントをインストールすることをお勧めします。
- オンプレミスのファイアウォール構成。

### Microsoft Entra プロビジョニング エージェント サーバーを強化する

IT 環境のこの重要なコンポーネントのセキュリティ攻撃対象領域を減らすには、Microsoft Entra プロビジョニング エージェント サーバーを強化することをお勧めします。 これらの推奨事項に従うと、組織に対するいくつかのセキュリティ リスクを軽減できます。

- Microsoft Entra プロビジョニング エージェント サーバーをコントロール プレーン (以前の階層 0) 資産として強化することをお勧めします。これは、[Secure Privileged Access](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview) および [Active Directory 管理レベル モデル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model)で提供されるガイダンスに従って行います。
- Microsoft Entra プロビジョニング エージェント サーバーへの管理アクセスを、ドメイン管理者またはその他の厳密に制御されたセキュリティ グループのみに制限します。
- 特権アクセスを持つすべての担当者に対して、専用アカウントを作成します。 管理者は、Web を閲覧したり、メールを確認したり、高い特権を持つアカウントで日常の生産性タスクを実行したりしないでください。
- [特権アクセス](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview)のセキュリティ保護に関する記事に記載されているガイダンスに従ってください。
- Microsoft Entra プロビジョニング エージェント サーバーでの NTLM 認証の使用を拒否します。 これを行うには、[Microsoft Entra プロビジョニング エージェント サーバー上での NTLM の制限](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/security-policy-settings/network-security-restrict-ntlm-outgoing-ntlm-traffic-to-remote-servers)と[ドメイン上での NTLM の制限](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/security-policy-settings/network-security-restrict-ntlm-ntlm-authentication-in-this-domain)
- すべてのマシンに一意のローカル管理者パスワードがあることを確認します。 詳細については、「[ローカル管理者パスワード ソリューション (Windows LAPS)](https://learn.microsoft.com/ja-jp/windows-server/identity/laps/laps-overview) では、各ワークステーションで一意のランダム パスワードを構成し、ACL によって保護されたActive Directoryにサーバーに格納できます。 これらのローカル管理者アカウント のパスワードの読み取りまたはリセットを要求できるのは、資格のある承認されたユーザーだけです。 Windows LAPS と特権アクセス ワークステーション (PAW) を使用して環境を運用するための追加のガイダンスは、クリーン ソースの原則に基づく [Operational 標準](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model#operational-standards-based-on-clean-source-principle)で確認できます。
- 組織の情報システムへの特権アクセス権を持つすべての担当者に 専用の 特権アクセス ワークステーションを実装します。
- これらの[追加のガイドライン](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/reducing-the-active-directory-attack-surface)に従って、Active Directory環境の攻撃対象領域を減らします。
- [Monitor のフェデレーション構成への変更](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-monitor-federation-changes)に従って、IdP とMicrosoft Entra IDの間で確立された信頼への変更を監視するアラートを設定します。
- Microsoft Entra IDまたは AD で特権アクセス権を持つすべてのユーザーに対して多要素認証 (MFA) を有効にします。 Microsoft Entra プロビジョニング エージェントの使用に関するセキュリティの問題の 1 つは、攻撃者がMicrosoft Entra プロビジョニング エージェント サーバーを制御できる場合、Microsoft Entra IDでユーザーを操作できることです。 攻撃者がこれらの機能を使用してMicrosoft Entraアカウントを引き継ぐのを防ぐために、MFA は保護を提供します。 たとえば、攻撃者が Microsoft Entra プロビジョニング エージェントを使用してユーザーのパスワードをリセットしたとしても、2 番目の要素をバイパスすることはできません。

### グループ管理サービス アカウント

グループ管理サービス アカウントは、パスワードの自動管理とサービス プリンシパル名 (SPN) の管理を簡素化するマネージド ドメイン アカウントです。 また、管理を他の管理者に委任し、この機能を複数のサーバーに拡張することもできます。 Microsoft Entra Cloud Sync では、エージェントの実行に gMSA がサポートされ、使用されます。 このアカウントを作成するために、セットアップ中に管理者資格情報の入力を求められます。 アカウントは `domain\provAgentgMSA$`として表示されます。 gMSA の詳細については、「[グループ管理サービス アカウントの](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/group-managed-service-accounts-overview)」を参照してください。

#### gMSA の前提条件

- gMSA ドメインのフォレスト内のActive Directory スキーマをWindows Server 2012以降に更新する必要があります。
- ドメイン コントローラー上の [PowerShell RSAT モジュール](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-server-administration-tools)。
- ドメイン内の少なくとも 1 つのドメイン コントローラーが、Windows Server 2012以降で実行されている必要があります。
- エージェントのインストール用に、Windows Server 2025、Windows Server 2022、Windows Server 2019、または Windows Server 2016 のいずれかを実行するドメイン参加済みサーバー。

#### カスタム gMSA アカウント

カスタム gMSA アカウントを作成する場合は、アカウントに次のアクセス許可があることを確認する必要があります。

| タイプ | 名前 | アクセス権 | 適用対象 |
| --- | --- | --- | --- |
| 許す | gMSA アカウント | すべてのプロパティを読み取る | デバイスの子孫オブジェクト |
| 許す | gMSA アカウント | すべてのプロパティを読み取る | InetOrgPerson の子孫オブジェクト |
| 許す | gMSA アカウント | すべてのプロパティを読み取る | コンピューターの子孫オブジェクト |
| 許す | gMSA アカウント | すべてのプロパティを読み取る | foreignSecurityPrincipal の子孫オブジェクト |
| 許す | gMSA アカウント | フル コントロール | グループの子孫オブジェクト |
| 許す | gMSA アカウント | すべてのプロパティを読み取る | ユーザーの子孫オブジェクト |
| 許す | gMSA アカウント | すべてのプロパティを読み取る | 連絡先の子孫オブジェクト |
| 許す | gMSA アカウント | ユーザー オブジェクトの作成/削除 | このオブジェクトとすべての子孫オブジェクト |

既存のエージェントをアップグレードして gMSA アカウントを使用する手順については、[グループの管理対象サービス アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install#group-managed-service-accounts)を参照してください。

グループ管理サービス アカウントのActive Directoryを準備する方法の詳細については、「[group Managed Service Accounts Overview](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/group-managed-service-accounts-overview) および [Group managed service accounts with cloud sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/gmsa-cloud-sync) を参照してください。

### Microsoft Entra管理センターで

1. Microsoft Entra テナントにクラウド専用のハイブリッド ID 管理者アカウントを作成します。 これにより、オンプレミスのサービスが失敗したり使用できなくなったりした場合に、テナントの構成を管理できます。 クラウド専用のハイブリッド ID 管理者アカウント追加する方法について説明します。 テナントからロックアウトされないようにするには、この手順を完了することが重要です。
2. Microsoft Entra テナントに 1 つ以上の [custom ドメイン名](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)を追加します。 ユーザーは、次のいずれかのドメイン名でサインインできます。

### Active Directoryのあなたのディレクトリにある

[IdFix ツール](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/set-up-directory-synchronization) を実行して、同期用のディレクトリ属性を準備します。

### オンプレミスの環境の場合

1. Windows Server 2025、Windows Server 2022、Windows Server 2019、または Windows Server 2016 を実行し、4 GB 以上の RAM と .NET 4.7.1 以降のランタイムを備えた、ドメイン参加済みのホスト サーバーを特定します。
2. ローカル サーバー上の PowerShell 実行ポリシーを Undefined または RemoteSigned に設定する必要があります。
3. サーバーとMicrosoft Entra IDの間にファイアウォールがある場合は、ファイアウォールとプロキシの要件を参照してください。

手記

Windows Server Core へのクラウド プロビジョニング エージェントのインストールはサポートされていません。

### Active Directory Domain ServicesへのMicrosoft Entra IDのプロビジョニング - 前提条件

Active Directory Domain Services (AD DS) へのプロビジョニング グループを実装するには、次の前提条件が必要です。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID の一般的に利用可能な機能を比較する](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) を参照してください。

### 一般的な要件

- 少なくとも[ハイブリッドアイデンティティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持つMicrosoft Entraアカウント。
- Windows Server 2016 以降で使用できる、*msDS-ExternalDirectoryObjectId* 属性を持つオンプレミス AD DS スキーマ。
- ビルド バージョン [1.1.2334.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1123340) 以降のプロビジョニング エージェント。

手記

サービス アカウントへのアクセス許可は、クリーン インストール時にのみ割り当てられます。 以前のバージョンからアップグレードする場合は、PowerShell を使用してアクセス許可を手動で割り当てる必要があります。

```powershell
$credential = Get-Credential  

Set-AADCloudSyncPermissions -PermissionType UserGroupCreateDelete -TargetDomain "FQDN of domain" -EACredential $credential
```

権限が手動で設定されている場合は、すべての子孫グループおよびユーザー オブジェクトのすべてのプロパティの読み取り、書き込み、作成、および削除を割り当てる必要があります。

これらのアクセス許可は、既定では AdminSDHolder オブジェクトには適用されません。 詳細については、「[Microsoft Entra プロビジョニング エージェント gMSA PowerShell コマンドレット](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets#grant-permissions-to-a-specific-domain)を参照してください。

- プロビジョニング エージェントは、Windows Server 2022、Windows Server 2019、またはWindows Server 2016を実行するサーバーにインストールする必要があります。
- プロビジョニング エージェントは、ポート TCP/389 (LDAP) および TCP/3268 (グローバル カタログ) 上の 1 つ以上のドメイン コントローラーと通信できる必要があります。
    - 無効なメンバーシップ参照を除外するためには、グローバルカタログの検索が必要です
- ビルド バージョンが [2.2.8.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#2280)の Microsoft Entra Connect Sync
    - Microsoft Entra Connect Sync を使用して同期されるオンプレミスのユーザー メンバーシップをサポートするために必要です
    - `AD DS:user:objectGUID`と`AAD DS:user:onPremisesObjectIdentifier`を同期するために必要です。

### プロビジョニンググループのActive Directoryへのスケール制限

Active Directory機能へのグループ プロビジョニング機能のパフォーマンスは、テナントのサイズと、Active Directoryへのプロビジョニングのスコープ内にあるグループとメンバーシップの数によって影響を受けます。 このセクションでは、GPAD がスケール要件をサポートしているかどうかを判断する方法と、初期および差分同期サイクルを迅速に実現するために適切なグループ スコープ モードを選択する方法について説明します。

#### サポートされていないもの

- 50,000 を超えるメンバーのグループはサポートされていません。
- 属性スコープのフィルター処理を適用しない "すべてのセキュリティ グループ" スコープの使用はサポートされていません。

#### スケールの制限

| スコープ モード | スコープ内グループの数 | メンバーシップ リンクの数 (直接メンバーのみ) | 注記 |
| --- | --- | --- | --- |
| "選択されたセキュリティ グループ" モード | 最大 10,000 グループ。 Microsoft Entra ポータルの CloudSync ペインでは、最大 999 個のグループを選択できるだけでなく、最大 999 個のグループを表示することもできます。 スコープに 1,000 を超えるグループを追加する必要がある場合は、「 API を使用したグループの選択の展開」を参照してください。 | **スコープ内**のすべてのグループで最大 250,000 のメンバーの合計。 | テナントがこれらの制限のいずれかを超えている場合は、このスコープ モードを使用します 1. テナントのユーザー数が 200,000 人を超える2. テナントに 40,000 を超えるグループがある 3. テナントには 100 万を超えるグループ メンバーシップがあります。 |
| 少なくとも 1 つの属性スコープ フィルターを持つ "すべてのセキュリティ グループ" モード。 | 最大 2万 グループ。 | **スコープ内**のすべてのグループ全体で最大 500,000 個のメンバー。 | テナントが以下のすべての制限を満たしている場合は、このスコープ モードを使用します。1. テナントのユーザー数が 200,000 人未満2. テナントのグループ数が 40,000 未満3. テナントのグループ メンバーシップが 1M 未満です。 |

#### 制限を超えた場合の操作

推奨される制限を超えると、初期同期と差分同期が遅くなり、同期エラーが発生する可能性があります。 その場合は、次の手順に従います。

#### [選択したセキュリティ グループ] スコープ モードのグループまたはグループ メンバーが多すぎます。

スコープ内グループの数を減らす (より高い値のグループをターゲットにする) **、またはプロビジョニングを複数の個別のジョブに分割し、** スコープを分離します。

#### "すべてのセキュリティ グループ" スコープ モードのグループまたはグループ メンバーが多すぎます。

**推奨どおり、選択したセキュリティ グループ**のスコープ モードを使用します。

#### 一部のグループのメンバーが 50,000 人を超えています。

複数のグループに**メンバーシップを分割**するか、段階的なグループ (リージョンや部署別など) を採用して、各グループを上限の下に維持します。

#### API を使用したグループの選択の拡張

999 を超えるグループを選択する必要がある場合は、サービス プリンシパル API 呼び出しに [appRoleAssignment の付与を使用する](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-post-approleassignedto) 必要があります。

API 呼び出しの例を次に示します。

```https
POST https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalID}/appRoleAssignedTo
Content-Type: application/json

{
  "principalId": "",
  "resourceId": "",
  "appRoleId": ""
}

```

どこで:

- **principalId**: グループ オブジェクト ID。
- **resourceId**: ジョブのサービス プリンシパル ID。
- **appRoleId**: リソース サービス プリンシパルによって公開されるアプリ ロールの識別子。

クラウドのアプリ ロール ID の一覧を次の表に示します。

| 雲 | appRoleId |
| --- | --- |
| Public | 1a0abf4d-b9fa-4512-a3a2-51ee82c6fd9f |
| AzureUSGovernment | d8fa317e-0713-4930-91d8-1dbeb150978f |
| AzureUSNatCloud | 50a55e47-aae2-425c-8dcb-ed711147a39f |
| AzureUSSecCloud | 52e862b9-0b95-43fe-9340-54f51248314f |

#### 詳細情報

AD DS にグループをプロビジョニングする際に考慮すべきその他のポイントを次に示します。

- AD DS に書き込まれるグループ メンバーシップには、AD DS アカウントを持つメンバーのみが含まれます。 これらのメンバーは、オンプレミスの同期されたユーザー、クラウドで管理されているユーザー、ユーザー プロビジョニングのスコープ内にあるため、Cloud Sync が AD DS にプロビジョニングするクラウドマネージド ユーザー、または他のクラウドで作成されたセキュリティ グループにすることができます。 AD DS アカウントを持たないクラウドマネージド ユーザーはスキップされます。
- オンプレミスの同期されたユーザーは、自分のアカウント *に onPremisesObjectIdentifier* 属性が設定されている必要があります。
- *onPremisesObjectIdentifier* は、ターゲット AD DS 環境の対応する *objectGUID* と一致する必要があります。
- オンプレミス ユーザー *objectGUID* 属性は、いずれかの同期クライアントを使用して、クラウド ユーザー *の onPremisesObjectIdentifier* 属性に同期できます。
- Microsoft Entra IDから AD DS にプロビジョニングできるのは、グローバル Microsoft Entra ID テナントだけです。 B2C などのテナントはサポートされていません。
- プロビジョニング ジョブは、20 分ごとに実行するようにスケジュールされています。

### その他の要件

- 最低[Microsoft .NET Framework 4.7.1](https://dotnet.microsoft.com/download/dotnet-framework/net471) 以降

#### TLS の要件

手記

トランスポート層セキュリティ (TLS) は、セキュリティで保護された通信を提供するプロトコルです。 TLS 設定を変更すると、フォレスト全体に影響します。 詳細については、「[Windows で既定のセキュリティで保護されたプロトコルとして TLS 1.1 と TLS 1.2 を有効にする更新](https://support.microsoft.com/help/3140245/update-to-enable-tls-1-1-and-tls-1-2-as-default-secure-protocols-in-wi)」を参照してください。

Microsoft Entra Connect クラウド プロビジョニング エージェントをホストするWindows サーバーは、インストールする前に TLS 1.2 を有効にする必要があります。

TLS 1.2 を有効にするには、次の手順に従います。

1. コンテンツを *.reg* ファイルにコピーして次のレジストリ キーを設定し、ファイルを実行します (**選択して [**のマージ] を選択します)。

    ```dos
    Windows Registry Editor Version 5.00
    
    [HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2]
    [HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client]
    "DisabledByDefault"=dword:00000000
    "Enabled"=dword:00000001
    [HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server]
    "DisabledByDefault"=dword:00000000
    "Enabled"=dword:00000001
    [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\.NETFramework\v4.0.30319]
    "SchUseStrongCrypto"=dword:00000001
    ```
2. サーバーを再起動します。

### ファイアウォールとプロキシの要件

サーバーとMicrosoft Entra IDの間にファイアウォールがある場合は、次の項目を構成します。

- エージェントが次のポートを通じてMicrosoft Entra IDに対して*送信*要求を行えることを確認してください。

    | ポート番号 | 説明 |
    | --- | --- |
    | **80** | TLS/SSL 証明書の検証中に証明書失効リスト (CRL) をダウンロードします。 |
    | **443** | サービスとのすべての送信通信を処理します。 |
    | **8080** (省略可能) | エージェントは、ポート 443 が使用できない場合、ポート 8080 経由で 10 分ごとに状態を報告します。 この状態は、Microsoft Entra管理センターに表示されます。 |
- ファイアウォールが送信元ユーザーに従って規則を適用する場合は、ネットワーク サービスとして実行されるWindows サービスからのトラフィックに対してこれらのポートを開きます。
- プロキシが少なくとも HTTP 1.1 プロトコルをサポートし、チャンク エンコードが有効になっていることを確認します。
- ファイアウォールまたはプロキシで安全なサフィックスを指定できる場合は、接続を追加します。

## [パブリック クラウド](#tab/public-cloud)
| URL | 説明 |
| --- | --- |
| `*.msappproxy.net``*.servicebus.windows.net` | エージェントはこれらの URL を使用して、Microsoft Entra クラウド サービスと通信します。 |
| `*.microsoftonline.com``*.microsoft.com``*.msappproxy.com``*.windowsazure.com` | エージェントはこれらの URL を使用して、Microsoft Entra クラウド サービスと通信します。 |
| `mscrl.microsoft.com:80``crl.microsoft.com:80``ocsp.msocsp.com:80``www.microsoft.com:80` | エージェントは、これらの URL を使用して証明書を検証します。 |
| `login.windows.net``login.live.com` | エージェントは、登録プロセス中にこれらの URL を使用します。 |
| `aadcdn.msauth.net``aadcdn.msftauth.net``www.msftconnecttest.com` | エージェントは、登録プロセス中にこれらの URL を使用します。 |

## [米国政府クラウド](#tab/us-government-cloud)
| URL | 説明 |
| --- | --- |
| `*.msappproxy.us``*.servicebus.usgovcloudapi.net` | エージェントはこれらの URL を使用して、Microsoft Entra クラウド サービスと通信します。 |
| `mscrl.microsoft.us:80``crl.microsoft.us:80``ocsp.msocsp.us:80``www.microsoft.us:80` | エージェントは、これらの URL を使用して証明書を検証します。 |
| `login.windows.us``secure.aadcdn.microsoftonline-p.com``*.microsoftonline.us``*.microsoftonline-p.us``*.msauth.net``*.msauthimages.net``*.msecnd.net``*.msftauth.net``*.msftauthimages.net``*.phonefactor.net``enterpriseregistration.windows.net``management.azure.com``policykeyservice.dc.ad.msft.net``ctldl.windowsupdate.us:80``aadcdn.msftauthimages.us``*.microsoft.us``msauthimages.us``msftauthimages.us` | エージェントは、登録プロセス中にこれらの URL を使用します。 |

- 接続を追加できない場合は、毎週更新される [Azure データセンターの IP 範囲](https://www.microsoft.com/download/details.aspx?id=41653) へのアクセスを許可します。

---

### NTLM 要件

Microsoft Entra プロビジョニング エージェントを実行しているWindows Serverで NTLM を有効にしないでください。有効になっている場合は、必ず無効にする必要があります。

### 既知の制限事項

既知の制限事項を次に示します。

#### 差分同期

- 差分同期のグループ スコープ フィルター処理では、50,000 を超えるメンバーがサポートされていません。
- グループ スコープ フィルターの一部として使用されているグループを削除しても、グループのメンバーであるユーザーは削除されません。
- スコープ内の OU またはグループの名前を変更しても、差分同期ではユーザーは削除されません。

#### プロビジョニング ログ

- プロビジョニング ログでは、作成操作と更新操作が明確に区別されません。 更新時に作成操作、および作成時に更新操作が表示される場合があります。

#### グループ名の変更または OU の名前変更

- 特定の構成のスコープ内にある AD のグループまたは OU の名前を変更した場合、クラウド同期ジョブは AD での名前の変更を認識できません。 ジョブは検疫されず、正常な状態のままになります。

#### スコープ フィルター

OU スコープ フィルターを使用する場合

- スコープ構成の文字数には 4 MB の制限があります。 標準のテスト環境では、これは、特定の構成に対して、必要なメタデータを含め、約 50 個の個別の組織単位 (OU) またはセキュリティ グループに変換されます。
- 入れ子になった OU がサポートされています (つまり、130 の入れ子になった OU を持つ OU を同期**できます**が、同じ構成で 60 の個別の OU を同期することは**できません**)。

手記

現時点では、スコープ構成サイズを確認して、メタデータを含む 4 MB の文字制限に近いか、到達したか、超えているかを判断することはできません。

#### パスワード ハッシュ同期

- InetOrgPerson でのパスワード ハッシュ同期の使用はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-prerequisites-provision-entra-to-active-directory"} -->
## AD 向け Microsoft Entra の前提条件 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites-provision-entra-to-active-directory
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-10
- Summary: Microsoft Entra Cloud Sync を使用してユーザーとグループをMicrosoft Entra IDからオンプレミスの Active Directoryにプロビジョニングするための前提条件とライセンス要件。

この記事では、Microsoft Entra Cloud Sync を使用して、Microsoft Entra IDからオンプレミス Active Directory Domain Services (AD DS) に**ユーザーとグループ**をプロビジョニングするための前提条件とライセンス要件を示します。プロビジョニングを構成する前に、これらの項目[を完了してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)。

### ライセンス要件

Active Directoryへのプロビジョニングでは、構成ベースのライセンス モデルが使用されます。

| Configuration | ライセンスが必要 |
| --- | --- |
| **既存の構成** (一般提供前に作成) | ライセンスの変更は必要ありません。 これらの構成は、既存の Microsoft Entra ID P1 ライセンスで引き続き実行されます。 |
| 各テナントにつき最初の**2**つの新しい構成 | Microsoft Entra ID P1。 |
| テナントあたり **2 つ以上**の新しい構成 (3 ~ 20) | Microsoft Entra ID ガバナンス。 |

Note

テナントごとに最大 **20** 個の構成 (ドメイン) を構成できます。 要件に適したライセンスを見つけるには、[Microsoft Entra ID の一般的に利用可能な機能を比較する](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) を参照してください。

### 一般的な要件

- Microsoft Entra アカウントで、少なくとも [Hybrid Identity Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) ロールを持っている必要があります。
- Windows Server 2016 以降で使用できる、*msDS-ExternalDirectoryObjectId* 属性を持つオンプレミス AD DS スキーマ。
- ビルド バージョン [1.1.2334.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1123340) 以降のプロビジョニング エージェント。

Note

サービス アカウントへのアクセス許可は、クリーン インストール時にのみ割り当てられます。 以前のバージョンからアップグレードする場合は、PowerShell を使用してアクセス許可を手動で割り当てる必要があります。

```powershell
$credential = Get-Credential
Set-AADCloudSyncPermissions -PermissionType UserGroupCreateDelete -TargetDomain "FQDN of domain" -EACredential $credential
```

権限が手動で設定されている場合は、すべての子孫グループおよびユーザー オブジェクトのすべてのプロパティの読み取り、書き込み、作成、および削除を割り当てます。 これらのアクセス許可は、既定では AdminSDHolder オブジェクトには適用されません。 詳細については、 [Microsoft Entra プロビジョニング エージェント gMSA PowerShell コマンドレットを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets#grant-permissions-to-a-specific-domain)参照してください。

プロビジョニング エージェントと同期クライアントには、独自の要件もあります。

- ドメインに参加しているサーバーにプロビジョニング エージェントをインストールします。 Windows Server 2025 または Windows Server 2022 をお勧めします。 延長サポートされている古いWindows Serverバージョンを使用できますが、この構成のサポートには[有料サポート プログラム](https://learn.microsoft.com/ja-jp/lifecycle/policies/fixed#extended-support)が必要な場合があります。
- プロビジョニング エージェントは、ポート TCP/389 (LDAP) および TCP/3268 (グローバル カタログ) 上の 1 つ以上のドメイン コントローラーと通信できる必要があります。
    - 無効なメンバーシップ参照を除外するには、グローバル カタログ参照が必要です。
- ビルド バージョン [2.2.8.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#2280)以降の Microsoft Entra Connect Sync。
    - Microsoft Entra Connect Sync を使用して同期されるオンプレミスのユーザー メンバーシップをサポートするために必要です。
    - `AD DS:user:objectGUID`を`AAD DS:user:onPremisesObjectIdentifier`に同期するために必要です。

### ユーザー プロビジョニングの前提条件 (プレビュー)

ユーザーを AD DS にプロビジョニングする場合は、ユーザーが使用するアプリケーションで Kerberos がサポートされていることを確認します。 ユーザーのパスワードを収集し、LDAP バインドを実行するアプリケーションは、クラウドで管理されているユーザーにはサポートされていません。AD DS パスワードがないためです。

クラウドで管理されているユーザーにオンプレミスの Kerberos リソースへのパスワードなしのアクセス権を付与するには、組織で使用するパスワードなしの方法を構成します。 Windows Hello for Businessの場合は、[クラウド Kerberos 信頼](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/hybrid-cloud-kerberos-trust)を構成します。 FIDO2 セキュリティ キーの場合は、 [パスワードなしのセキュリティ キーのサインインに従ってオンプレミス のリソースにサインインします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises)。 Microsoft Entra ID は部分的な TGT を発行し、クライアントはこれをオンプレミスのドメイン コントローラーと交換して、完全な AD TGT を取得します。

### 詳細情報

AD DS にプロビジョニングするときは、次の点を考慮してください。

- AD DS に書き込まれるグループ メンバーシップには、AD DS アカウントを持つメンバーのみが含まれます。 これらのメンバーは、オンプレミスの同期されたユーザー、クラウドで管理されているユーザー、ユーザー プロビジョニングのスコープ内にあるため、Cloud Sync が AD DS にプロビジョニングするクラウドマネージド ユーザー、または他のクラウドで作成されたセキュリティ グループにすることができます。 AD DS アカウントを持たないクラウドマネージド ユーザーはスキップされます。
- オンプレミスの同期されたユーザーは、自分のアカウント *に onPremisesObjectIdentifier* 属性が設定されている必要があります。
- *onPremisesObjectIdentifier* は、ターゲット AD DS 環境の対応する *objectGUID* と一致する必要があります。
- オンプレミス ユーザー *objectGUID* 属性は、いずれかの同期クライアントを使用して、クラウド ユーザー *の onPremisesObjectIdentifier* 属性に同期できます。
- Microsoft Entra ID から AD DS にプロビジョニングできるのは、グローバル Microsoft Entra ID テナントのみです。 B2C などのテナントはサポートされていません。
- プロビジョニング ジョブは、定期的なスケジュールで実行されます。

Note

Microsoft Entra Connect Sync でのグループ ライトバック v2 のプレビューは非推奨となり、サポートされなくなりました。 グループ ライトバック v2 を使用する場合は、同期クライアントを Microsoft Entra Cloud Sync に移動します。MICROSOFT 365 グループを AD DS にプロビジョニングする場合は、グループ ライトバック v1 を引き続き使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-preserve-group-organizational-unit-entra-to-active-directory"} -->
## グループの組織単位を保持する (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-preserve-group-organizational-unit-entra-to-active-directory
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-20
- Summary: グループのソース オブ オーソリティを Microsoft Entra ID に変換した後もグループが元の組織単位 (OU) と共通名 (CN) を保持するように、GroupDN ディレクトリ拡張機能を設定します。

グループの機関ソース (SOA) をMicrosoft Entra IDに変換しても、グループの元の組織単位 (OU) は自動的に検出されません。 SOA を変換する前に、 `GroupDN` ディレクトリ拡張機能を使用してグループの識別名 (DN) をキャプチャし、OU 内でその拡張機能と共通名 (CN) マッピング式を参照します。

この記事では、1 回限りのセットアップについて説明します。 グループをクラウド管理に変換する **前** に完了します。 拡張機能を使用するマッピング式については、「 [グループの元の組織単位を保持](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#preserve-a-groups-original-organizational-unit)する」を参照してください。

### Prerequisites

続行する前に、「[Microsoft Entra IDからActive Directoryへのプロビジョニングの前提条件」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites-provision-entra-to-active-directory)の手順を完了します。

### GroupDN 拡張機能を設定する

グループをクラウド管理に変換する前に、次のタスクを順番に完了します。

#### グループスコープをユニバーサルに変更する

グループスコープをユニバーサルに変更するには:

1. **管理センター Active Directory**開きます。
2. グループを長押し (または右クリック) し、[ **プロパティ**] を選択します。
3. [ **グループ** ] セクションで、グループ スコープとして **[ユニバーサル** ] を選択します。
4. **保存**を選びます。

#### 拡張機能を作成する

Cloud Sync では、 `CloudSyncCustomExtensionsApp` アプリケーションで作成された拡張機能のみがサポートされます。 このアプリケーションがまだ存在しない場合は、テナントごとに 1 回作成します。

## [Graph PowerShell](#tab/ps)
1. 管理者特権の PowerShell ウィンドウを開き、次のコマンドを実行してモジュールをインストールし、接続します。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser -Force
    Connect-MgGraph -Scopes "Application.ReadWrite.All","Directory.ReadWrite.All","Directory.AccessAsUser.All"
    ```
2. アプリケーションが存在するかどうかを確認します。 そうでない場合は、作成し、サービス プリンシパルが存在することを確認します。

    ```powershell
    $tenantId = (Get-MgOrganization).Id
    $app = Get-MgApplication -Filter "identifierUris/any(uri:uri eq 'API://$tenantId/CloudSyncCustomExtensionsApp')"
    if (-not $app) {
      $app = New-MgApplication -DisplayName "CloudSyncCustomExtensionsApp" -IdentifierUris "API://$tenantId/CloudSyncCustomExtensionsApp"
    }
    $app
    
    $sp = Get-MgServicePrincipal -Filter "AppId eq '$($app.AppId)'"
    if (-not $sp) {
      $sp = New-MgServicePrincipal -AppId $app.AppId
    }
    $sp
    ```
3. `GroupDN`という名前のディレクトリ拡張プロパティ (グループ オブジェクトの文字列属性) を追加します。

    ```powershell
    New-MgApplicationExtensionProperty `
      -ApplicationId $app.Id `
      -Name "GroupDN" `
      -DataType "String" `
      -TargetObjects Group
    ```

## [グラフ エクスプローラー](#tab/ge)
1. 識別子 URI `API://<tenantId>/CloudSyncCustomExtensionsApp` を持つアプリケーションが存在するかどうかを確認します。

    ```http
    GET /applications?$filter=identifierUris/any(uri:uri eq 'api://<tenantId>/CloudSyncCustomExtensionsApp')
    ```
2. アプリケーションが存在しない場合は、作成します。

    ```http
    POST https://graph.microsoft.com/v1.0/applications
    Content-type: application/json
    
    {
      "displayName": "CloudSyncCustomExtensionsApp",
      "identifierUris": ["api://<tenant id>/CloudSyncCustomExtensionsApp"]
    }
    ```
3. `GroupDN`ディレクトリ拡張機能 (グループ オブジェクトの文字列型) を作成します。

    ```http
    POST https://graph.microsoft.com/v1.0/applications/<ApplicationId>/extensionProperties
    Content-type: application/json
    
    {
      "name": "GroupDN",
      "dataType": "String",
      "isMultiValued": false,
      "targetObjects": [ "Group" ]
    }
    ```

---

詳細については、[Active DirectoryにMicrosoft Entra IDをプロビジョニングするためのディレクトリ拡張機能を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping-entra-to-active-directory)参照してください。

#### distinguishedName を GroupDN 拡張機能にマップする

Cloud Sync に、グループの識別名 (DN) をActive Directoryから拡張機能に設定するように指示します。 これにより、グループの完全な DN (CN + OU パス) がMicrosoft Entra IDにキャプチャされます。

1. **Entra ID**&gt;**Entra Connect**&gt;**Cloud Sync** を開きます。
2. **AD から Microsoft Entra ID への**構成を選択します。
3. **[属性マッピング**] に移動し、[**オブジェクトの種類]** を **[グループ**] に設定します。
4. 新しい属性マッピングを追加します。
    - **マッピングの種類**: Direct
    - **ソース属性**: `distinguishedName`
    - **ターゲット属性**: `extension_<appIdWithoutHyphens>_GroupDN`
5. スキーマを保存して同期をトリガーします。

#### マッピングを確認し、SOA を変換する

同期の実行後、Microsoft Graph PowerShell を使用して、拡張プロパティに DN が設定されていることを確認します。

```powershell
$groupDisplayName = 'My Security Group'
$clientId = $app.AppId
$propName = "extension_{0}_GroupDN" -f ($clientId -replace "-","")
$grp = Get-MgGroup -Filter "displayName eq '$groupDisplayName'" -ConsistencyLevel eventual
Get-MgGroup -GroupId $grp.Id -Property "id,displayName,$propName" |
  Select-Object id, displayName, @{n=$propName; e={$_."$propName"}}
```

DN が拡張機能に格納されたら、[グループの権限のソースをMicrosoft Entra IDに変換](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-group-source-of-authority-configure)します。 グループはクラウド管理になりますが、元の DN は、次の OU および CN マッピング式で使用するために拡張機能に保持されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-sso"} -->
## クラウド同期でシングル サインオンを使用する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-sso
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-09-15
- Summary: この記事では、クラウド同期でシングル サインオンをインストールして使用する方法について説明します。

次のドキュメントでは、クラウド同期でシングル サインオンを使用する方法について説明します。

### シングル サインオンを有効にする手順

クラウド プロビジョニングは、シングル サインオン (SSO) で動作します。 現時点では、エージェントのインストール時に SSO を有効にするオプションはありません。ただし、次の手順を使用して SSO を有効にして使用できます。

#### 手順 1: Microsoft Entra Connect ファイルをダウンロードして抽出する

1. まず、[Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) の最新バージョンをダウンロードします
2. 管理者特権を使用してコマンド プロンプトを開き、ダウンロードした msi に移動します。
3. 次のコマンドを実行します。`msiexec /a C:\filepath\AzureADConnect.msi /qb TARGETDIR=C:\filepath\extractfolder`
4. ファイル パスと `extractfolder` を、ファイル パスと抽出フォルダーの名前と一致するように変更します。 コンテンツは抽出フォルダーに格納されます。

#### 手順 2: ADSync とシームレス SSO PowerShell モジュールをインポートする

注

Azure AD および MSOnline PowerShell モジュールは、2024 年 3 月 30 日の時点で非推奨となります。 詳細については、[非推奨に関する更新情報](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/important-azure-ad-graph-retirement-and-powershell-module/ba-p/3848270)を参照してください。 この日以降、これらのモジュールのサポートは、Microsoft Graph PowerShell SDK への移行支援とセキュリティ修正に限定されます。 非推奨のモジュールは、2025 年 3 月 30 日まで引き続き機能します。

Microsoft Entra ID (旧称 Azure AD) を使用するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) に移行することをお勧めします。 移行に関する一般的な質問については、「[移行に関する FAQ](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/migration-faq)」を参照してください。 *注:* MSOnline のバージョン 1.0.x では、2024 年 6 月 30 日以降に中断が発生する可能性があります。

1. [Azure AD PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/overview) をダウンロードしてインストールします。
2. Microsoft Entra Connect Sync が、これらのコマンドを実行するサーバーにインストールされていることを確認します。 ADSync モジュールは、インストールされている製品から読み込む必要があります。
3. 次のコマンドを使用して ADSync PowerShell モジュールをインポートします: `Import-Module "$env:ProgramFiles\Microsoft Azure AD Sync\Bin\ADSync\ADSync.psd1"`。
4. 手順 1 の抽出フォルダーにある `Microsoft Azure Active Directory Connect` フォルダーを参照します。
5. `Import-Module .\AzureADSSO.psd1` コマンドを使用して、Seamless SSO PowerShell モジュールをインポートします。

#### 手順 3: シームレス SSO が有効になっている Active Directory フォレストの一覧を取得する

1. PowerShell を管理者として実行します。 PowerShell で、`New-AzureADSSOAuthenticationContext` を呼び出します。 メッセージが表示されたら、 [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)の資格情報を入力します。
2. `Get-AzureADSSOStatus`を呼び出します。 このコマンドでは、この機能が有効になっている Active Directory フォレストの一覧 ("ドメイン" リストを参照) が表示されます。

#### 手順 4:各 Active Directory フォレストのシームレス SSO を有効にする

1. `Enable-AzureADSSOForest`を呼び出します。 求められたら、目的の Active Directory フォレストのドメイン管理者の資格情報を入力します。

    注

    ドメイン管理者の資格情報のユーザー名は、SAM アカウント名の形式 (`contoso\johndoe` または `contoso.com\johndoe`) で入力する必要があります。 Microsoft はユーザー名のドメイン部分を使用して、DNS を使用してドメイン管理者のドメイン コントローラーを検索します。

    注

    使用するドメイン管理者アカウントは、保護されているユーザー グループのメンバーであってはなりません。 そうである場合、操作は失敗します。
2. 機能を設定する Active Directory フォレストごとに、前の手順を繰り返します。

#### 手順 5: テナントで機能を有効にする

テナントで機能を有効にするには、`Enable-AzureADSSO -Enable $true`を呼び出します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-test-and-enable-provisioning-entra-to-active-directory"} -->
## Microsoft Entra プロビジョニングをテストする (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-test-and-enable-provisioning-entra-to-active-directory
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-26
- Summary: Microsoft Entra ID から Active Directory へのオンデマンド プロビジョニングをテストし、既定のプロパティを確認して、構成を有効にするか管理します。

スコープ フィルターと属性マッピングを構成したら、構成をテストし、既定のプロパティを確認して有効にします。 これらの手順は、すべての展開オプション (ユーザーのみ、グループのみ、またはユーザーとグループ) で同じです。

### Prerequisites

続行する前に、「[プロビジョニングをActive DirectoryするようにMicrosoft Entra IDを構成する」の手順を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)完了します。

### オンデマンド プロビジョニングを使用してテストする

すべてのスコープ内オブジェクトの構成を有効にする前に、それを 1 人のユーザーまたはグループに適用し、結果を確認します。 オンデマンド プロビジョニングでは、インポート、スコープの評価、一致、ターゲット オブジェクトに対して実行されたアクションなど、各手順が個別にレポートされるため、オブジェクトが失敗する場所を正確に確認できます。

グループには 1 つの追加の考慮事項があります。メンバーは自動的にプロビジョニングされないため、グループと一緒にテストする最大 5 つのメンバーを選択します。

テストが完了すると、ポータルに次の構成手順を示すメッセージが表示されます。 そのメッセージのリンクを選択して続行します。

ユーザーとグループの両方の完全な手順については、「[オンデマンド プロビジョニング - Active DirectoryへのMicrosoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-on-demand-provision-entra-to-active-directory)」を参照してください。

### 既定のプロパティ: 誤った削除と電子メール通知

既定のプロパティ セクションでは、誤って削除したり、電子メール通知を送信したりするための設定を提供します。

[Image: 既定のプロパティ オプションのスクリーンショット。]

誤って削除する機能により、多くのユーザーやグループに影響を与える構成の変更やオンプレミスのディレクトリの変更から保護できます。 これにより、次のことができます。

- 誤って自動的に削除されないようにします。
- 保護を有効にするオブジェクトの数 (しきい値) を設定します。
- このシナリオでは、同期ジョブが検疫に配置されたときの通知メール アドレスを設定します。

詳細については、[誤削除](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-accidental-deletes)を参照してください。

[**基本**] の横にある**鉛筆**を選択して、構成の既定値を変更します。

[Image: 構成の基本プロパティのスクリーンショット。]

### 構成の有効化

構成を最終処理してテストしたら、有効にします。

左側で、[**概要**]&gt;**[確認して有効にする**] を選択し、[構成の**有効化]** を選択します。

[Image: プロビジョニング構成の確認と有効化のスクリーンショット。]

サービスは、すべてのスコープ内オブジェクトに対して初期サイクルを実行し、その後、定期的なスケジュールでデルタ サイクルを実行します。

### 検疫

Cloud Sync は、構成の正常性を監視し、異常なオブジェクトを検疫状態に配置します。 無効な管理者資格情報などのエラーが原因でターゲット システムに対して行われたほとんどの呼び出しまたはすべての呼び出しが一貫して失敗する場合、同期ジョブは検疫中としてマークされます。 詳細については、[「検疫済み問題をプロビジョニングする」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-troubleshoot#provisioning-quarantined-problems)を参照してください。

### プロビジョニングを再開する

次回のスケジュールされた実行を待機しない場合は、 **同期の再起動**を使用してプロビジョニングの実行をトリガーします。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。
3. [ **構成]** で、構成を選択します。
4. 上部にある [同期の **再起動**] を選択します。

### 構成を削除する

構成を削除するには:

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。
3. [ **構成]** で、構成を選択します。
4. 構成画面の上部にある [ **構成の削除**] を選択します。

Important

構成が削除される前に確認はありません。 **[削除**] を選択する前に、これが実行するアクションであることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-transformation"} -->
## Microsoft Entra クラウド同期の変換 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-transformation
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、変換を使用して既定の属性マッピングを変更する方法について説明します。

変換では、クラウド同期を使用して、属性と Microsoft Entra ID の同期方法の既定の動作を変更できます。

このタスクを実行するには、スキーマを編集し、Web 要求を使用して再送信する必要があります。

クラウド同期属性の詳細については、「 [Microsoft Entra スキーマについて](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-attributes)」を参照してください。

### スキーマを取得する

スキーマを取得するには、「 [同期スキーマを表示する」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-attributes#view-the-synchronization-schema)の手順に従います。

### カスタム属性マッピング

カスタム属性マッピングを追加するには、次の手順に従います。

1. [Visual Studio Code](https://code.visualstudio.com/) などのテキスト エディターまたはコード エディターにスキーマをコピーします。
2. スキーマで更新するオブジェクトを見つけます。

    [Image: スキーマ内のオブジェクトのスクリーンショット。]
3. ユーザー オブジェクトの下に `ExtensionAttribute3` のコードを見つけます。

    ```
                            {
                                "defaultValue": null,
                                "exportMissingReferences": false,
                                "flowBehavior": "FlowWhenChanged",
                                "flowType": "Always",
                                "matchingPriority": 0,
                                "targetAttributeName": "ExtensionAttribute3",
                                "source": {
                                    "expression": "Trim([extensionAttribute3])",
                                    "name": "Trim",
                                    "type": "Function",
                                    "parameters": [
                                        {
                                            "key": "source",
                                            "value": {
                                                "expression": "[extensionAttribute3]",
                                                "name": "extensionAttribute3",
                                                "type": "Attribute",
                                                "parameters": []
                                            }
                                        }
                                    ]
                                }
                            },
    ```
4. 会社の属性が `ExtensionAttribute3`にマップされるようにコードを編集します。

    ```
                                     {
                                         "defaultValue": null,
                                         "exportMissingReferences": false,
                                         "flowBehavior": "FlowWhenChanged",
                                         "flowType": "Always",
                                         "matchingPriority": 0,
                                         "targetAttributeName": "ExtensionAttribute3",
                                         "source": {
                                             "expression": "Trim([company])",
                                             "name": "Trim",
                                             "type": "Function",
                                             "parameters": [
                                                 {
                                                     "key": "source",
                                                     "value": {
                                                         "expression": "[company]",
                                                         "name": "company",
                                                         "type": "Attribute",
                                                         "parameters": []
                                                     }
                                                 }
                                             ]
                                         }
                                     },
    ```
5. スキーマを Graph エクスプローラーにコピーし、要求の **種類** を **PUT** に変更して、[ **クエリの実行**] を選択します。

    [Image: クエリを実行する]
6. 次に、ポータルでクラウド同期構成に移動し、[プロビジョニングの **再開**] を選択します。
7. しばらくしてから、Graph エクスプローラーで次のクエリを実行して、属性が設定されていることを確認します: `https://graph.microsoft.com/beta/users/{Azure AD user UPN}`。
8. これで値が表示されます。

    [Image: 値が表示されます]

### 関数を使用したカスタム属性マッピング

より高度なマッピングを行うには、組織のニーズに合わせてデータを操作し、属性の値を作成できる関数を使用できます。

このタスクを実行するには、前の手順に従って、最終的な値の構築に使用される関数を編集します。

式の構文と例については、「 [Microsoft Entra ID での属性マッピングの式の記述」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-expressions)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/how-to-troubleshoot"} -->
## Microsoft Entra Cloud Sync のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-troubleshoot
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-21
- Summary: この記事では、クラウド プロビジョニング エージェントで発生する可能性がある問題のトラブルシューティング方法について説明します。

クラウド同期にはさまざまな依存関係と相互作用があり、さまざまな問題が発生する可能性があります。 この記事は、これらの問題のトラブルシューティングに役立ちます。 ここでは、注目すべき一般的な領域、追加情報を収集する方法、および問題を追跡するために使用できるさまざまな手法について説明します。

### エージェントの問題

エージェントの問題のトラブルシューティングを行うときは、エージェントが正しくインストールされていること、およびエージェントが Microsoft Entra ID と通信していることを確認します。 特に、エージェントで最初に確認する内容の一部を次に示します。

- インストールされていますか?
- エージェントはローカルで実行されているか。
- エージェントはポータルにありますか?
- エージェントは正常とマークされていますか?

これらの項目は、ポータルと、エージェントを実行しているローカル サーバーで確認できます。

#### Microsoft Entra 管理センターのエージェントの検証

Azure がエージェントを検出し、エージェントが正常であることを確認するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. **クラウド同期**を選択します。
2. インストールしたエージェントが表示されます。 該当するエージェントが存在することを確認します。 すべて問題がなければ、エージェントの *アクティブ (* 緑) 状態が表示されます。

#### 必要な開いているポートを確認する

Microsoft Entra プロビジョニング エージェントが Azure データセンターと正常に通信できることを確認します。 パスにファイアウォールがある場合は、送信トラフィックに対する次のポートが開いていることを確認します。

| ポート番号 | 使用方法 |
| --- | --- |
| 80 | TLS/SSL 証明書の検証中の証明書失効リスト (CRL) のダウンロード。 |
| 443 | アプリケーション プロキシ サービスとの送信通信をすべて処理します。 |

ファイアウォールが送信元ユーザーに従ってトラフィックを適用する場合は、ネットワーク サービスとして実行される Windows サービスからのトラフィックに対してもポート 80 と 443 を開きます。

#### URL へのアクセスを許可する

次の URL へのアクセスを許可します。

| URL | ポート | 使用方法 |
| --- | --- | --- |
| `*.msappproxy.net``*.servicebus.windows.net` | 443/HTTPS | コネクタとアプリケーション プロキシ クラウド サービス間の通信。 |
| `crl3.digicert.com``crl4.digicert.com``ocsp.digicert.com``crl.microsoft.com``oneocsp.microsoft.com``ocsp.msocsp.com` | 80/HTTP | コネクタは、これらの URL を使用して証明書を検証します。 |
| `login.windows.net``secure.aadcdn.microsoftonline-p.com``*.microsoftonline.com``*.microsoftonline-p.com``*.msauth.net``*.msauthimages.net``*.msecnd.net``*.msftauth.net``*.msftauthimages.net``*.phonefactor.net``enterpriseregistration.windows.net``management.azure.com``policykeyservice.dc.ad.msft.net``ctldl.windowsupdate.com``www.microsoft.com/pkiops` | 443/HTTPS | コネクタでは、登録プロセス中にこれらの URL が使用されます。 |
| `ctldl.windowsupdate.com` | 80/HTTP | コネクタでは、登録プロセス中にこの URL が使用されます。 |

ファイアウォールまたはプロキシでドメイン サフィックスに基づいてアクセス規則を構成できる場合は、`*.msappproxy.net`、`*.servicebus.windows.net`、およびその他の URL への接続を許可できます。 そうでない場合は、 [Azure IP 範囲とサービス タグ (パブリック クラウド](https://www.microsoft.com/download/details.aspx?id=56519)) へのアクセスを許可する必要があります。 IP 範囲は毎週更新されます。

重要

Microsoft Entra プライベート ネットワーク コネクタと Microsoft Entra アプリケーション プロキシ クラウド サービス間の送信 TLS 通信では、あらゆる形式のインライン検査と終了を回避します。

#### Microsoft Entra アプリケーション プロキシ エンドポイントの DNS 名前解決

Microsoft Entra アプリケーション プロキシ エンドポイントのパブリック DNS レコードは、A レコードを指すチェーンされた CNAME レコードです。 これにより、障害許容性と柔軟性が確保されます。 Microsoft Entra プライベート ネットワーク コネクタは、常にドメイン サフィックスが `*.msappproxy.net` または `*.servicebus.windows.net`ホスト名にアクセスすることが保証されます。

ただし、名前解決中に、CNAME レコードにホスト名とサフィックスが異なる DNS レコードが含まれる場合があります。 このため、デバイスがチェーン内のすべてのレコードを解決できることを確認し、解決された IP アドレスへの接続を許可する必要があります。 チェーン内の DNS レコードは随時変更される可能性があるため、DNS レコードの一覧を提供することはできません。

#### ローカル サーバー上

エージェントが実行されていることを確認するには、次の手順に従います。

1. エージェントがインストールされているサーバーで、[ **サービス**] を開きます。 これを行うには、 **Start**&gt;**Run**&gt;**Services.msc** に移動します。
2. **[サービス**] で、**Microsoft Entra Connect エージェント アップデーター**と **Microsoft Entra Provisioning Agent** が存在することを確認します。 また、状態が *[実行中*] であることを確認します。

    [Image: ローカル サービスとその状態のスクリーンショット。]

#### エージェントのインストールに関する一般的な問題

以下のセクションでは、エージェントのインストールに関する一般的な問題と、それらの問題の一般的な解決策について説明します。

##### エージェントの起動に失敗しました

次のようなエラー メッセージが表示されることがあります。

*サービス 'Microsoft Entra Provisioning Agent' を開始できませんでした。 システム・サービスを開始するための十分な特権があることを確認します。*

この問題は通常、グループ ポリシーによって発生します。 このポリシーにより、インストーラー (`NT SERVICE\AADConnectProvisioningAgent`) によって作成されたローカル NT Service サインイン アカウントにアクセス許可が適用されなくなります。 これらのアクセス許可は、サービスを開始するために必要です。

この問題を解決するには、次の手順に従います。

1. 管理者アカウントを使用してサーバーにサインインします。
2. **Start**&gt;&gt; に**移動してサービス**を開きます。
3. [ **サービス**] で、[ **Microsoft Entra Provisioning Agent] を**ダブルクリックします。
4. [ログオン] タブ **で** 、[ **このアカウント** ] をドメイン管理者に変更します。次に、サービスを再起動します。

    [Image: [ログオン] タブから使用できるオプションを示すスクリーンショット。]

##### エージェントがタイムアウトするか、証明書が無効です

エージェントを登録しようとすると、次のエラー メッセージが表示されることがあります。

[Image: タイムアウト エラー メッセージを示すスクリーンショット。]

この問題は通常、エージェントがハイブリッド ID サービスに接続できないことが原因で発生します。 この問題を解決するには、送信プロキシを構成します。

プロビジョニング エージェントは、送信プロキシの使用をサポートしています。 これを構成するには、次のエージェント .config ファイルを編集します。 *C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\AADConnectProvisioningAgent.exe.config*。

次の行をファイルの末尾に追加し、終了 `</configuration>` タグの直前に追加します。 `[proxy-server]` 変数と `[proxy-port]` 変数をプロキシ サーバー名とポート値に置き換えます。

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

##### エージェントの登録がセキュリティ エラーで失敗する

クラウド プロビジョニング エージェントをインストールすると、エラー メッセージが表示されることがあります。 この問題は、通常、ローカルの PowerShell 実行ポリシーが原因で、エージェントが PowerShell 登録スクリプトを実行できないことが原因で発生します。

この問題を解決するには、サーバー上の PowerShell 実行ポリシーを変更します。 コンピューターポリシーとユーザーポリシーを `Undefined` または `RemoteSigned`として設定する必要があります。 `Unrestricted`として設定されている場合は、このエラーが表示されます。 詳細については、「 [PowerShell 実行ポリシー](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_execution_policies)」を参照してください。

#### ログ ファイル

既定では、エージェントは最小限のエラー メッセージとスタック トレース情報を出力します。 これらのトレース ログは、C:\ProgramData\Microsoft\Azure AD Connect Provisioning Agent\Trace フォルダーにあります。

エージェント関連の問題のトラブルシューティングの詳細を収集するには、次の手順に従います。

1. [AADCloudSyncTools PowerShell モジュールをインストールします](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-powershell#install-the-aadcloudsynctools-powershell-module)。
2. `Export-AADCloudSyncToolsLogs`PowerShell コマンドレットを使用して情報をキャプチャします。 次のオプションを使用して、データ収集を微調整できます。
    - 詳細ログをキャプチャせずに現在のログのみをエクスポートするように `SkipVerboseTrace` します (既定値は false)。
    - 別のキャプチャ期間 (既定値は 3 分) を指定 `TracingDurationMins`。
    - 別の出力パスを指定する `OutputPath` します (既定値はユーザーのドキュメント フォルダー)。

### オブジェクト同期の問題

ポータルでは、プロビジョニング ログを使用して、オブジェクト同期の問題の追跡とトラブルシューティングを行うことができます。 ログを表示するには、[ログ] を選択 **します**。

[Image: [ログ] ボタンを示すスクリーンショット。]

プロビジョニング ログは、オンプレミスの Active Directory 環境と Azure の間で同期されているオブジェクトの状態に関する豊富な情報を提供します。

[Image: プロビジョニング ログに関する情報を示すスクリーンショット。]

ビューをフィルター処理して、日付などの特定の問題に焦点を当てることができます。 Active Directory `ObjectGuid`を使用して、Active Directory オブジェクトに関連するアクティビティのログを検索することもできます。 個々のイベントをダブルクリックすると、追加情報が表示されます。

[Image: プロビジョニング ログのドロップダウン リスト情報を示すスクリーンショット。]

この情報では、詳細な手順と、同期の問題が発生している場所について説明します。 この方法では、問題の正確な場所を特定できます。

##### スキップされたオブジェクト

Active Directory からユーザーとグループを同期している場合は、Microsoft Entra ID で 1 つ以上のグループを見つけることができない可能性があります。 これは、同期がまだ完了していないか、Active Directory でのオブジェクトの作成にまだ追いついていないか、Microsoft Entra ID で作成されているオブジェクトをブロックしている同期エラー、またはオブジェクトを除外する同期規則のスコープルールが適用されていることが原因である可能性があります。

同期を再開し、プロビジョニング サイクルが完了したら、プロビジョニング ログで、そのオブジェクトの Active Directory `ObjectGuid`を使用するオブジェクトに関連するアクティビティを検索します。 ソース ID のみを含み、状態が `Skipped` の ID を持つイベントがログに存在する場合は、エージェントがスコープ外であるために Active Directory オブジェクトをフィルター処理したことを示すことができます。

既定では、スコープ規則により、次のオブジェクトが Microsoft Entra ID に同期されなくなります。

- `IsCriticalSystemObject` が TRUE に設定されているユーザー、グループ、連絡先 (Active Directory の組み込みユーザーとグループの多くを含む)
- レプリケーション対象オブジェクト

[同期スキーマ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-attributes)には、追加の制限が存在する場合があります。

##### Microsoft Entra オブジェクトの削除のしきい値

Microsoft Entra Connect と Microsoft Entra Cloud Sync を使用した実装トポロジがあり、両方とも同じ Microsoft Entra テナントにエクスポートしている場合、または Microsoft Entra Connect を使用して Microsoft Entra Cloud Sync に完全に移行した場合は、定義されたスコープから複数のオブジェクトを削除または移動するときに、次のエクスポート エラー メッセージが表示されることがあります。

[Image: エクスポート エラーを示すスクリーンショット。]

このエラーは、Microsoft Entra Connect クラウド同期の誤削除防止機能  とは関係ありません。これは、Microsoft Entra Connect から Microsoft Entra ディレクトリに設定  誤削除防止機能によってトリガーされます。 機能を切り替えることができる Microsoft Entra Connect サーバーがインストールされていない場合は、Microsoft Entra Connect クラウド同期エージェントと共にインストールされた ["AADCloudSyncTools"](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/reference-powershell) PowerShell モジュールを使用して、テナントの設定を無効にし、ブロックされた削除が想定されており、許可する必要があることを確認した後にエクスポートできるようにします。 次のコマンドを使用します。

```PowerShell
Disable-AADCloudSyncToolsDirSyncAccidentalDeletionPrevention -tenantId "aaaabbbb-0000-cccc-1111-dddd2222eeee"
```

次のプロビジョニング サイクル中に、削除対象としてマークされたオブジェクトを Microsoft Entra ディレクトリから正常に削除する必要があります。

### プロビジョニングの検疫に関する問題

クラウド同期は、構成の正常性を監視し、異常なオブジェクトを検疫状態に配置します。 ターゲット システムに対して行われたほとんどの呼び出し (たとえば、無効な管理者資格情報) が原因で一貫して失敗した場合、同期ジョブは検疫中としてマークされます。

[Image: 検疫の状態を示すスクリーンショット。]

状態を選択すると、検疫に関する追加情報を確認できます。 エラー コードとメッセージを取得することもできます。

[Image: 検疫に関する追加情報を示すスクリーンショット。]

状態を右クリックすると、次の追加オプションが表示されます。

- プロビジョニング ログを表示します。
- エージェントを表示します。
- 検疫をクリアします。

[Image: 右クリック メニュー オプションを示すスクリーンショット。]

#### 検疫を解決する

検疫を解決するには、2 つの方法があります。 検疫をクリアすることも、プロビジョニング ジョブを再起動することもできます。

##### 検疫をクリアする

透かしをクリアし、確認後にプロビジョニング ジョブで差分同期を実行するには、状態を右クリックし、**[検疫のクリア]** を選択します。

検疫がクリアされていることを示す通知が表示されます。

[Image: 検疫がクリアされていることを示すスクリーンショット。]

その後、エージェントの状態が正常であることを確認できるはずです。

[Image: エージェントの状態が正常であることを示すスクリーンショット。]

##### プロビジョニング ジョブを再起動する

ポータルを使用してプロビジョニング ジョブを再起動します。 エージェントの構成ページで、[ **同期の再開**] を選択します。

[Image: エージェント構成ページのオプションを示すスクリーンショット。]

または、Microsoft Graph を使用して [プロビジョニング ジョブを再起動](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-restart?tabs=http&view=graph-rest-beta&preserve-view=true)することもできます。 再起動する内容を完全に制御できます。 次の内容をクリアすることを選択できます。

- エスクロー。検疫状態を発生させるエスクロー カウンターを再起動します。
- 検疫。アプリケーションを検疫から削除します。
- 透かし。

次の要求を使用します。

`POST /servicePrincipals/{id}/synchronization/jobs/{jobId}/restart`

### ディレクトリ サービスの承認エラー

**AzureDirectoryServiceAuthorizationFailed** 検疫エラー コードが発生した場合、最も可能性の高い原因は、テナントにディレクトリ同期サービス アプリケーションのサービス プリンシパルが存在していないことです。 これは、次の例のように Microsoft Graph API に対してクエリを実行することで確認できます。

```http
GET /servicePrincipals?$filter=displayName eq 'Microsoft Entra AD Synchronization Service'
```

一致するサービス プリンシパルが見つからない場合は、次の Microsoft Graph API 要求で同期 (既に有効になっている場合でも) を有効にすることで、新しいサービス プリンシパルの作成をトリガーできます。

```http
PATCH /organization/<your tenant ID>

Request body:

{ "onPremisesSyncEnabled": true }
```

最初のクエリを再度実行すると、テナント内でサービス プリンシパルが復元されているのが確認できます。 その後、承認エラーが完全に消え、同期が正常に再開されるまでに最大 24 時間かかる場合があります。 エラーがその時点を超えて解決しない場合は、サポートにお問い合わせください。

### パスワード書き戻し

クラウド同期でパスワード ライトバックを有効にして使用するには、次の点に注意してください。

- [gMSA アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets#using-set-aadcloudsyncpermissions)を更新する必要がある場合、これらのアクセス許可がディレクトリ内のすべてのオブジェクトにレプリケートされるまでに 1 時間以上かかる場合があります。 これらのアクセス許可を割り当てない場合、ライトバックは正しく構成されているように見えますが、ユーザーがクラウドからオンプレミスのパスワードを更新するときにエラーが発生する可能性があります。 **Unexpire Password** を表示するには**、このオブジェクトとすべての子孫オブジェクト**にアクセス許可を適用する必要があります。
- 一部のユーザー アカウントのパスワードがオンプレミス ディレクトリに書き戻されない場合は、オンプレミスの Active Directory Domain Services (AD DS) 環境のアカウントに対して継承が無効になっていないことを確認してください。 機能が正しく機能するためには、子孫オブジェクトにパスワードの書き込みアクセス許可を適用する必要があります。
- オンプレミスの AD DS 環境のパスワード ポリシーにより、パスワード リセットが正しく処理されない場合があります。 この機能をテストしていて、ユーザーのパスワードを 1 日に複数回リセットする場合は、パスワードの最小有効期間のグループ ポリシーを 0 に設定する必要があります。 この設定は、**gpmc.msc** 内の&gt;&gt;&gt;&gt;**アカウント ポリシー**の場所にあります。
    - グループ ポリシーを更新する場合は、更新されたポリシーがレプリケートされるまで待機するか、`gpupdate /force` コマンドを使用します。
    - パスワードをすぐに変更するには、パスワードの最小有効期間を 0 に設定する必要があります。 ただし、ユーザーがオンプレミス のポリシーに従い、パスワードの最小有効期間が 0 より大きい値に設定されている場合、オンプレミス ポリシーの評価後にパスワード ライトバックは機能しません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync"} -->
## Microsoft Entra Connect を Microsoft Entra クラウド同期に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect を Microsoft Entra クラウド同期に移行する手順について説明します。

Microsoft Entra クラウド同期は、ユーザー、グループ、および連絡先を Microsoft Entra ID に同期するというハイブリッド ID の目標を達成するための将来的な機能です。 この機能では、Microsoft Entra Connect アプリケーションではなく Microsoft Entra クラウド プロビジョニング エージェントが使用されます。 現在 Microsoft Entra Connect を使用しており、クラウド同期に移行したい場合は、次のドキュメントでガイダンスが提供されています。

### Microsoft Entra Connect をクラウド同期に移行するための手順

Important

パイロット フェーズまたは共存フェーズの間は、OU、ドメイン、グループ、ユーザー、コンタクト、またはその他の参照されるオブジェクトを Microsoft Entra Connect Sync のスコープから削除しないでください。 オブジェクトが完全に移行され、最終的なカットオーバーの準備が整うまで、既存のスコープを構成したままにしておきます。 最終的なカットオーバーの前にスコープからオブジェクトを削除するのは安全ではありません。Microsoft Entra コネクタ スペース内の参照を削除し、参照の削除 (グループ メンバーシップの削除など) をMicrosoft Entra IDにエクスポートできます。

サポートされている共存モデルは、Microsoft Entra Connect Sync スコープにオブジェクトを保持し、`cloudNoFlow`と`JoinNoFlow`ルールを使用して、Microsoft Entra Connect Sync がオブジェクトの追加、オブジェクト削除、および非参照属性の更新をエクスポートできないようにすることです。 `member`や`manager`などの参照属性の更新は、引き続き参照解決のためにフローできます。

OU や別の定義されたバッチなど、段階的に移行することもできます。 各バッチは、Microsoft Entra Connect Sync スコープに残り、そのバッチが完全に移行され、カットオーバーの準備が整うまで、フローなしのルールが適用されている必要があります。

| ステップ | 説明 |
| --- | --- |
| 最適な同期ツールを選択する | クラウド同期に移行する前に、クラウド同期が現在最適な同期ツールであることを確認する必要があります。 このタスクは、 [サポートされている同期シナリオの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)を確認することで実行できます。 |
| 移行の前提条件を確認する | 次のガイダンスは、簡易設定を使用して Microsoft Entra Connect をインストールし、デバイスを同期していないユーザーのみを対象としています。 また、クラウド同期の[前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites)も確認する必要があります。 |
| Microsoft Entra Connect 構成をバックアップする | 何らかの変更を行う前に、Microsoft Entra Connect 構成をバックアップする必要があります。 これにより、ロールバックできます。 詳細については、「[Microsoft Entra Connect 構成設定をインポートおよびエクスポートする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-import-export-config)」を参照してください。 |
| 移行チュートリアルを確認する | 移行プロセスを理解するには、チュートリアル「[既存の同期済み AD フォレストを Microsoft Entra クラウド同期に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp)」を参照してください。 このチュートリアルでは、サンドボックス環境での移行プロセスについて説明します。 |
| 移行用の OU を作成または特定する | 新しい OU を作成するか、移行をテストするユーザーを含む既存の OU を特定します。 移行中は、この OU を Microsoft Entra Connect Sync スコープに保持します。 |
| ユーザーを新しい OU に移動する (省略可能) | 新しい OU を使用する場合は、このパイロット計画の対象となるユーザーをその OU に移動してください。 続行する前に、Microsoft Entra Connect Sync で変更を取得して、新しい OU で同期できるようにします。 移行中に、Microsoft Entra Connect Sync スコープから OU またはユーザーを削除しないでください。 |
| OU で PowerShell を実行する | 次の PowerShell コマンドレットを実行して、パイロット OU 内のユーザーの数を取得できます。 `Get-ADUser -Filter * -SearchBase "<DN path of OU>"` 例: `Get-ADUser -Filter * -SearchBase "OU=Finance,OU=UserAccounts,DC=FABRIKAM,DC=COM"` |
| スケジューラの停止 | 新しい同期規則を作成する前に、Microsoft Entra Connect スケジューラを停止する必要があります。 詳細については、[スケジューラを停止する方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler#stop-the-scheduler)に関する記事を参照してください。 |
| カスタム同期規則を作成する | Microsoft Entra接続同期規則エディターで、前に作成または識別した OU 内のユーザーに対して`cloudNoFlow`属性を`True`に設定する受信同期規則を作成します。 また、リンクの種類が `JoinNoFlow` の送信同期規則と、 `cloudNoFlow` 属性が `True` に設定されているスコープ フィルターも必要です。 これらのルールを組み合わせることで、Microsoft Entra Connect Sync がスコープ付きユーザーのオブジェクトの追加、オブジェクトの削除、および非参照属性の更新をエクスポートできなくなります。 `member`や`manager`などの参照属性の更新は、引き続き参照解決のためにフローできます。 パイロットまたは共存フェーズでは、パイロット OU、グループ、ドメイン、または関連する参照先オブジェクトを Microsoft Entra Connect Sync スコープから削除しないでください。 詳細については、既存の[同期された AD フォレストの Microsoft Entra Cloud Sync への移行に関する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp#create-a-custom-user-inbound-rule)チュートリアルで、これらの規則を作成する方法を参照してください。 |
| プロビジョニング エージェントをインストールする | まだインストールしていない場合は、プロビジョニング エージェントをインストールします。 詳細については、[エージェントのインストール方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)に関する記事を参照してください。 |
| クラウドの同期を構成する | エージェントがインストールされたら、クラウド同期を構成する必要があります。構成では、以前に作成または識別された OU のスコープを作成する必要があります。 詳細については、[クラウド同期の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)に関する記事を参照してください。 |
| パイロット ユーザーが同期してプロビジョニングされていることを確認する | ユーザーがポータルで同期されていることを確認します。 次の PowerShell スクリプトを使用して、識別名にオンプレミスのパイロット OU を持つユーザーの数を取得できます。 この数は、前の手順のユーザー数と一致している必要があります。 この OU に新しいユーザーを作成する場合は、プロビジョニングされていることを確認します。 |
| スケジューラの開始 | ユーザーのプロビジョニングと同期が行われていることを確認したので、Microsoft Entra Connect スケジューラを開始できます。 詳細については、[スケジューラを開始する方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler#start-the-scheduler)に関する記事を参照してください。 |
| 残りのユーザーをスケジュールする | 残りのユーザー、グループ、連絡先、および関連する参照オブジェクトの段階的な移行計画を作成します。 OU 単位などでバッチごとに移行できますが、各バッチが完全に移行され、カットオーバーの準備が整うまでは、no-flow ルールを適用した Microsoft Entra Connect Sync のスコープ内に各バッチを維持してください。 |
| すべてのユーザーがプロビジョニングされていることを確認する | ユーザーを移行するときは、ユーザーが正しくプロビジョニングおよび同期されていることを確認してください。 |
| Microsoft Entra Connect を停止する | 移行スコープ内のすべてのオブジェクトが Microsoft Entra Cloud Sync によってプロビジョニングされ、その参照 (グループ メンバーシップやマネージャーのリレーションシップなど) がそのまま残っていることを確認したら、最終的なカットオーバーの一環として、そのスコープMicrosoft Entra Connect Sync を停止できます。 Microsoftでは、移行が成功したことを確認できるように、サーバーを一定期間無効な状態のままにすることをお勧めします。 |
| すべてが正常であることを確認する | 一定期間が経過したら、すべてが正常であることを確認します。 |
| Microsoft Entra Connect サーバーの使用を停止する | すべてに問題がないことを確認したら、Microsoft Entra Connect サーバーをオフラインにすることができます。 詳細については、「[Microsoft Entra Connect のアンインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-uninstall)」を参照してください。 |

### ユーザー確認スクリプト

```PowerShell
# Filename:  VerifyAzureUsers.ps1
# Description: Counts the number of users in Azure that have a specific on-premises distinguished name.
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

Connect-MgGraph -Scopes "User.Read.All"

#Declare variables

$Users = Get-EntraUser -All:$true -Filter "DirSyncEnabled eq true"
$OU = "OU=Sales,DC=contoso,DC=com"
$counter = 0

#Search users

foreach ($user in $Users) {
  $test = $User.ExtensionProperty
  $DN = $test["onPremisesDistinguishedName"]
  if ($DN -match $OU){$counter++}
}

Write-Host "Total Users found:" + $counter

```

### 詳細情報

- [プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning)
- [Microsoft Entra クラウド同期とは?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)
- [Microsoft Entra クラウド同期の新しい構成を作成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)。
- [既存の同期済み AD フォレストを Microsoft Entra クラウド同期に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp) ``
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/migrate-connect-sync-cloud-sync-tool"} -->
## 移行ツールを使用してクラウド同期Microsoft Entraに移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-connect-sync-cloud-sync-tool
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-09-16
- Summary: Microsoft Entra Connect Sync から Microsoft Entra Cloud Sync に移行し、アクティブ化後に同期を検証する方法について説明します。

ガイド付き移行ツールを使用して、サポートされている同期設定を Microsoft Entra Connect Sync から Microsoft Entra Cloud Sync に移動します。このツールは、環境を評価し、必要なクラウド同期構成を作成し、事前アクティブ化プロビジョニング オンデマンド チェックを実行します。 次に、Connect Sync をステージング モードにし、Cloud Sync をアクティブ化して、事後アクティブ化の検証を行います。 開始する前に、前提条件とサポートされている移行シナリオを確認してください。

### 前提条件

- Microsoft Entraに接続し、クラウド同期構成を作成および管理するための[**ハイブリッド ID 管理者**](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持つアカウント。
- 選択したすべてのActive Directory フォレストのドメイン管理者資格情報。 ウィザードはこれらの資格情報を検証し、それらを使用してプロビジョニング エージェントのグループ管理サービス アカウント (gMSA) を構成します。
- サポートされているバージョンの Microsoft Entra Connect Sync。移行オプションは、現在の移行ウェーブの対象となる組織と Connect Sync のインストールにのみ表示されます。
- 現在の移行ツールの制限を満たす環境:
    - 1 つのActive Directoryフォレストで、スコープ内オブジェクトが 2,000 個以下です。
    - 各ドメインに含まれるコンテナーが 30 個以下の追加組織単位 (OU) 包含スコープ。
    - カスタム同期規則、グループ フィルター処理、カスタム ユーザー プリンシパル名 (UPN)、ディレクトリ拡張機能、デバイス同期、サポートされていない書き戻しシナリオなど、移行をブロックする機能はありません。
- Connect Sync と Cloud Sync の他の違いについて [、サポートされているシナリオと機能の比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync) を確認します。
- アクティブな Microsoft Entra Connect 同期サーバーへのアクセス。 ステージング モードでサーバーから移行ツールを実行することはできません。
- 移行後 2 週間の検証期間。 この期間中Microsoft Entra Connect Sync をインストールしたままにしておきます。

移行ツールでは、次の同期シナリオの転送がサポートされています。

- ユーザー同期。
- グループ同期。
- パスワード ハッシュ同期 (PHS)。
- パスワードの書き戻し。
- Exchange ハイブリッド書き戻し。
- サポートされているドメインと組織単位のスコープ。

### 移行ツールを起動する

アクティブな Microsoft Entra Connect Sync サーバーからガイド付き移行を開始します。 ウィザードには、環境を変更する前の移行ステージの概要が表示されます。

1. アクティブ サーバーで Microsoft Entra Connect Sync ウィザードを開きます。
2. **設定**を選択します。
3. [ **クラウド同期への移行] を選択します**。

    [Image: Microsoft Entra接続同期ウィザードの [クラウド同期への移行] タスクのスクリーンショット。]
4. 移行の概要を確認します。

    [Image: 移行の概要とその 5 つの移行ステージのスクリーンショット。]

### Microsoft EntraとActive Directoryに接続する

ツールをMicrosoft Entraとオンプレミスの Active Directoryに接続して、現在の同期のセットアップを評価できるようにします。

1. メッセージが表示されたら、**ハイブリッド ID 管理者**ロールを持つアカウントでMicrosoft Entraにサインインします。
2. 選択したフォレストごとに、メッセージが表示されたらドメイン管理者の資格情報を入力します。

このツールは、現在の Microsoft Entra Connect Sync 構成へのアクセスを確認し、移行前に注意が必要になる可能性がある条件を確認します。

### 環境の準備状況を確認する

準備レビューには、ドメイン、スコープ、有効な機能、認証関連の設定、書き戻し設定、オブジェクトの制限、既存のクラウド同期エージェントまたは構成、サポートされていない同期規則を含めることができます。 ウィザードには、接続同期の誤削除保護設定と削除しきい値も表示されます。 移行を続行するには、これらの設定を読み取り可能で有効にする必要があります。

1. 各結果とブロック条件を確認します。
2. 誤削除保護の設定と削除のしきい値を確認します。
3. 検疫通知を受信する電子メール アドレスを入力します。
4. 準備レポートをダウンロードします。
5. [移行の同意] チェック ボックスをオンにし、[ **切り替えの開始]** を選択します。

    [Image: レポートのダウンロードと移行の同意オプションを使用して、環境の準備レビューが成功したスクリーンショット。]

Important

レビューによって移行がブロックされる場合は、結果をバイパスしないでください。 識別された条件を修復するか、必要な機能を待ちます。

### 構成を転送する

移行ツールは、サポートされている設定を自動的に転送します。 Cloud Sync プロビジョニング エージェントをインストールして登録し、選択したスコープ内Active Directoryドメインごとにクラウド同期構成を作成し、環境内で検出されたサポートされている機能に必要な同期ジョブを作成します。

1. 移行ツールが構成を転送するまで待ちます。
2. 転送が完了するまで転送の進行状況を監視します。
3. 転送が完了したことをウィザードで確認します。
4. Microsoft Entra 管理センターで、作成されたクラウド同期の構成と同期ジョブを確認します。

    [Image: 構成転送の進行状況と完了した移行タスクのスクリーンショット。]

Note

新しく作成されたクラウド同期構成は、転送中も無効のままです。 Connect Sync は、アクティブ化されるまでアクティブなエクスポーターのままです。

[Image: 無効な状態で転送されたクラウド同期構成のスクリーンショット。]

### [オンデマンドプロビジョニング] を使用して構成を確認する

クラウド同期をアクティブ化する前に、プロビジョニング オンデマンドを使用して、移行された各ドメイン構成の代表的なオブジェクトをテストします。正常な結果は、エージェントが選択したActive Directory オブジェクトを読み取ることができ、Cloud Sync 構成で処理できることを確認します。

1. 移行ウィザードで、[**Entra ポータルを開く**] を選択し、新しく作成したクラウド同期構成をMicrosoft Entra 管理センターで開きます。

    [Image: [オンデマンドプロビジョニング] が確認される前の [クラウド同期の確認] ページのスクリーンショット。]
2. 移行されたドメイン構成ごとに、次のアクションを実行します。

    1. **オンデマンドプロビジョン** を選択します。
    2. 組織が移行テスト用に承認した代表的なテスト ユーザーまたはグループの識別名を入力します。
    3. **プロビジョン** を選択します。
    4. 詳細な結果を確認します。 最終的な成功インジケーターだけに依存しないでください。
3. パスワード ライトバックが有効になっている場合は、テナント レベルのセルフサービス パスワード リセット (SSPR) ライトバックが有効になっていることを確認し、パスワード ライトバックの確認チェック ボックスをオンにします。
4. 移行ウィザードに戻ります。
5. 移行されたドメイン構成ごとに、オンデマンドプロビジョニングが正常に完了したことを確認します。
6. [ **クラウド同期への完全な転送] を選択します**。

### クラウド同期のアクティブ化

アクティブ化により、アクティブな同期パスが変更されます。 移行ツールを使用すると、転送されたクラウド同期の構成とジョブが有効になり、Connect Sync がステージング モードになり、最初のクラウド同期同期が開始されます。 Connect Sync はインポートと同期の処理をローカルで続行しますが、Microsoft Entra IDへの通常のエクスポートは実行しなくなります。

1. [ **クラウド同期への転送の完了**] を選択した後、移行ツールが Cloud Sync をアクティブ化するまで待ちます。
2. アクティブ化が完了し、最初のクラウド同期同期が開始されることを確認します。

    [Image: Cloud Sync がアクティブで、Connect Sync がステージング モードであることを示すアクティブ化の確認のスクリーンショット。]

Caution

初期検証中に Connect Sync をアンインストールしないでください。

### 同期結果を検証する

検証ページを使用して、Connect Sync のステージング結果とアクティブなクラウド同期の結果を比較します。 ウィザードは、1 つの集計 **オブジェクト数** を自動的に比較し、必要なクラウド同期ジョブの正常性を確認し、Connect Sync がステージング モードであることを確認します。 個々のユーザー、グループ、メンバーシップ、または書き戻し操作は自動的には検証されません。

1. **集計された合計オブジェクト**数を比較し、予想される数が調整されることを確認します。

    [Image: 一致するオブジェクト数と成功した同期チェックを示す [移行の検証] ページのスクリーンショット。]
2. クラウド同期ジョブの正常性、最後の同期情報、プロビジョニング エラーを確認します。

    [Image: エージェントと同期の状態の詳細が有効になっている正常なクラウド同期構成のスクリーンショット。]
3. 代表的なユーザー、グループ、メンバーシップ、および有効な書き戻しシナリオを手動で検証します。
4. 永続的なオブジェクト レベルの違いを調査します。
5. 必要なすべてのチェックに合格したら、検証の同意チェック ボックスをオンにし、[ **移行の完了**] を選択します。
6. アンインストールする前に、承認された 2 週間の検証期間に Connect Sync をインストールしたままにします。

次の Connect Sync ステージング サイクルを待機する必要がある場合は、ウィザードを閉じて、Connect Sync 構成ミューテックスを解放します。 ウィザードは遷移状態を保持します。 検証を再開するには、Connect Sync Microsoft Entra再度開き、[**クラウド同期Microsoft Entra移行**] を選択し、概要を確認して、メッセージが表示されたらもう一度Microsoft Entraにサインインします。 検証ページに進み、結果を更新します。

### 移行の時間を増やす

Microsoftは、製品内のエクスペリエンスと電子メールを通じて、対象となる組織に通知します。 この通知は、組織の移行期間または期限を識別し、移行ガイダンスに移動します。

確認された技術的またはビジネス上の阻害要因によって、通知の期限までに移行を完了できなくなる場合は、一時的な Cloud Sync 移行例外のMicrosoft サポート要求を開きます。 例外は個別にレビューされ、保証されず、移行の要件は削除されません。

サポート要求に次の情報を含めます。

- お客様の Microsoft Entra テナント ID と組織名。
- 要求された例外の終了日と、より多くの時間が必要な理由。
- 準備レポート、確認されたブロック状態の説明、および関連するエラー証拠。
- 実施が進む場合に予想される顧客またはビジネスへの影響。
- 試した軽減策と、それらが阻害要因を解決しなかった理由。
- マイルストーンと目標完了日を含む移行計画。

セキュリティで保護されたMicrosoft サポート チャネル経由でのみ証拠を送信します。 レポート、ログ、またはスクリーンショットをアップロードする前に、Microsoft サポートによる調査に不要な資格情報、シークレット、トークン、証明書関連情報、個人データ、内部ホスト名、識別名、および無関係なテナント識別子を削除してください。 サポートが問題を調査できるように、要求されたテナントと相関関係の識別子を保持します。

### 一般的な問題のトラブルシューティング

トラブルシューティングの表を使用して、移行の問題を調査し、Microsoft サポートの証拠を収集します。

| Issue | 確認すべきこと | 収集する裏付け資料 |
| --- | --- | --- |
| 準備レビューによって移行がブロックされる | ブロック機能、スコープ、オブジェクトの制限、誤削除の保護設定、または構成の競合を確認します。 | 準備レポートとブロック結果の編集されたスクリーンショット。 |
| 転送に失敗する | Connect Sync トレース ログで、資格情報の検証、エージェントのインストール、登録、Microsoft Graph操作、ジョブ作成の失敗を確認します。 | トレース ログの抜粋、タイムスタンプ、ウィザードの手順、エラー テキスト、関連付け識別子 (表示されている場合)。 |
| アクティブ化が失敗する | ウィザードの回復手順に従います。 手動による変更は復旧に干渉する可能性があるため、移行所有のクラウド同期リソースを削除、無効化、または再タグ付けしないでください。 | 診断 ID、エクスポートされたウィザード ログ、タイムスタンプ、ウィザード ステップ、およびエラー テキスト。 |
| オンデマンドプロビジョニングが失敗する | エージェントの正常性、ディレクトリ接続、アクセス許可、オブジェクト識別名、および詳細なプロビジョニング結果を確認します。 | オンデマンド プロビジョニングの結果の詳細とプロビジョニング ログ。 |
| 検証件数が一致しません | 次の Connect Sync ステージング サイクルを待機している間、ウィザードを閉じます。 ウィザードを再度開き、検証に戻り、集計カウントの比較を更新して、永続的なオブジェクト レベルの違いを調査します。 | 検証のスクリーンショット、最終同期時刻、ジョブの正常性、および関連するプロビジョニング エラー。 |
| 既存のクラウド同期構成が検出されました | 既存の構成が互換性のある書き戻し構成であるか、競合するActive Directory対Microsoft Entra構成であるかを判断します。 | テナント識別子が伏せられた構成名、方向、スコープ、および状態。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/migrate-group-writeback"} -->
## Microsoft Entra Connect Sync グループ書き戻し v2 を Microsoft Entra Cloud Sync に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-group-writeback
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-09-29
- Summary: この記事では、Microsoft Entra Connect Sync を使用してグループ ライトバック用に最初に設定されたグループを Microsoft Entra Cloud Sync に移行する方法について説明します。

重要

Microsoft Entra Connect Sync でのグループ ライトバック v2 のプレビューは非推奨となり、サポートされなくなりました。

Microsoft Entra Cloud Sync を使用して、クラウド セキュリティ グループをオンプレミスの Active Directory Domain Services (AD DS) にプロビジョニングできます。

Microsoft Entra Connect Sync でグループ ライトバック v2 を使用する場合は、同期クライアントを Microsoft Entra Cloud Sync に移動する必要があります。Microsoft Entra Cloud Sync に移行する資格があるかどうかを確認するには、ユーザー同期ウィザードを使用します。

ウィザードで推奨されているように Microsoft Cloud Sync を使用できない場合は、Microsoft Entra Connect Sync と並行して Microsoft Entra Cloud Sync を実行できます。その場合は、Microsoft Entra Cloud Sync を実行して、クラウド セキュリティ グループをオンプレミスの AD DS にプロビジョニングするだけです。

MICROSOFT 365 グループを AD DS にプロビジョニングする場合は、グループ ライトバック v1 を引き続き使用できます。

この記事では、Microsoft Entra Connect Sync (旧称 Azure Active Directory Connect) を使用してグループ ライトバックを Microsoft Entra Cloud Sync に移行する方法について説明します。このシナリオは、現在 Microsoft Entra Connect グループ ライトバック v2 を使用しているお客様 *のみを* 対象としています。 この記事で説明するプロセスは、ユニバーサル スコープで書き戻されるクラウドで作成されたセキュリティ グループにのみ関連します。

このシナリオは、次の場合にのみサポートされます。

- クラウドで作成された [セキュリティ グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups#group-types)。
- [ユニバーサル](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/manage/understand-security-groups#group-scope)のスコープで Active Directory に書き戻されたグループ。

重要

移行中は、Microsoft Entra Cloud Sync が構成され、Active Directoryにプロビジョニングされるように検証されるまで、書き戻されたグループ、グループ ライトバック ターゲット OU、または関連する参照先オブジェクトを Microsoft Entra Connect Sync スコープから削除しないでください。

サポートされている共存モデルは、Microsoft Entra Connect Sync で既存の書き戻しグループを認識し、この記事の`cloudNoFlow`と`JoinNoFlow`の規則を使用して、Microsoft Entra Cloud Sync がグループ のプロビジョニングを引き継ぐ間、結合関係を維持することです。

`JoinNoFlow` は完全ステージング (読み取り専用) モードではありません。 これにより、オブジェクトの追加、オブジェクトの削除、および参照以外の属性の更新が防止されます。 ただし、グループ メンバーシップの参照など、参照属性の更新は、参照解決のために引き続き処理される場合があります。 カットオーバー前に Microsoft Entra Connect Sync スコープからグループまたは参照オブジェクトを削除すると、Microsoft Entra Connect Sync が参照を削除したり、オンプレミスのグループ オブジェクトのプロビジョニングを解除したりする可能性があります。

Active Directory に書き戻されたメールが有効なグループと配布リストは、引き続き Microsoft Entra Connect グループ ライトバックで動作しますが、グループ ライトバック v1 の動作に戻ります。 このシナリオでは、グループ の書き戻し v2 を無効にした後、すべての Microsoft 365 グループは、Microsoft Entra 管理センターの **[書き戻しが有効]** 設定とは別に Active Directory に書き戻されます。 詳細については、「 [Microsoft Entra Cloud Sync を使用した Active Directory へのプロビジョニングに関する FAQ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-provision-to-active-directory-faq)」を参照してください。

### 前提条件

- 少なくとも [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) ロールを持つ Microsoft Entra アカウント。
- 少なくともドメイン管理者のアクセス許可を持つオンプレミスの Active Directory アカウント。

    `adminDescription`属性にアクセスし、`msDS-ExternalDirectoryObjectId`属性にコピーするために必要です。
- Windows Server 2022、Windows Server 2019、または Windows Server 2016 を実行するオンプレミスの Active Directory Domain Services 環境。

    Active Directory スキーマ属性の `msDS-ExternalDirectoryObjectId`に必要です。
- ビルド バージョンが [1.1.1367.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history) 以降のプロビジョニング エージェント。
- プロビジョニング エージェントは、ポート TCP/389 (LDAP) と TCP/3268 (グローバル カタログ) 上のドメイン コントローラーと通信できる必要があります。

    無効なメンバーシップ参照をフィルターで除外するには、グローバルカタログ検索に必要です。

### 書き戻されたグループの名前付け規則

既定では、Microsoft Entra Connect Sync では、名前付けグループが書き戻されるときに次の形式が使用されます。

- **既定の形式:**`CN=Group_<guid>,OU=<container>,DC=<domain component>`
- **例：**`CN=Group_3a5c3221-c465-48c0-95b8-e9305786a271,OU=WritebackContainer,DC=Contoso,DC=com`

Microsoft Entra ID から Active Directory に書き戻されるグループを見つけやすくするため、Microsoft Entra Connect 同期ではクラウド表示名を使ってグループの名前を書き戻すオプションが追加されました。 このオプションを使用するには、グループ書き戻し v2 の初期セットアップの際に、**クラウド表示名を持つ書き戻しグループ識別名**を選択します。 この機能が有効になっている場合、Microsoft Entra Connect では、既定の形式ではなく、次の新しい形式が使用されます。

- **新しい形式:**`CN=<display name>_<last 12 digits of object ID>,OU=<container>,DC=<domain component>`
- **例：**`CN=Sales_e9305786a271,OU=WritebackContainer,DC=contoso,DC=com`

既定では、Microsoft Entra Connect Sync でライトバック グループ識別名とクラウド表示名機能が有効になっていない場合でも、Microsoft Entra Cloud Sync は新しい形式を使用します。既定の Microsoft Entra Connect Sync の名前を使用し、Microsoft Entra Cloud Sync によって管理されるようにグループを移行すると、グループの名前が新しい形式に変更されます。 Microsoft Entra Cloud Sync で Microsoft Entra Connect の既定の形式を使用できるようにするには、次のセクションを使用します。

#### 既定の形式を使用する

Microsoft Entra Cloud Sync で Microsoft Entra Connect Sync と同じ既定の形式を使用する場合は、 `CN` 属性の属性フロー式を変更する必要があります。 2 つのマッピングを使用できます。

| 表現 | 構文 | 説明 |
| --- | --- | --- |
| クラウド同期の既定の表現に`DisplayName`を使用する | `Append(Append(Left(Trim([displayName]), 51), "_"), Mid([objectId], 25, 12))` | Microsoft Entra Cloud Sync で使用される既定の式 (つまり、新しい形式)。 |
| `DisplayName` を使用せずに新しい式をクラウドに同期する | `Append("Group_", [objectId])` | Microsoft Entra Connect 同期の既定の形式を利用する新しい式。 |

詳細については、「 [必要に応じて他の属性マッピングを変更する」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#change-other-attribute-mappings-as-needed)参照してください。

### 手順 1: adminDescription を msDS-ExternalDirectoryObjectID にコピーする

グループ メンバーシップ参照を検証するには、Microsoft Entra Cloud Sync で Active Directory グローバル カタログに対して `msDS-ExternalDirectoryObjectID` 属性のクエリを実行する必要があります。 このインデックス付き属性は、Active Directory フォレスト内のすべてのグローバル カタログにレプリケートされます。

1. オンプレミス環境で、 **ADSI Edit** を開きます。
2. グループの `adminDescription` 属性にある値をコピーします。

    [Image: adminDescription 属性を示すスクリーンショット。]
3. `msDS-ExternalDirectoryObjectID`属性に値を貼り付けます。

    [Image: msDS-ExternalDirectoryObjectID 属性を示すスクリーンショット。]

この手順を自動化するには、次の PowerShell スクリプトを使用できます。 このスクリプトは、 `OU=Groups,DC=Contoso,DC=com` コンテナー内のすべてのグループを取得し、 `adminDescription` 属性値を `msDS-ExternalDirectoryObjectID` 属性値にコピーします。 このスクリプトを使用する前に、変数 `$gwbOU` を、グループ ライトバックのターゲット組織単位 (OU) の `DistinguishedName` 値で更新します。

```powershell

# Provide the DistinguishedName of your Group Writeback target OU
$gwbOU = 'OU=Groups,DC=Contoso,DC=com'

# Get all groups written back to Active Directory
$properties = @('displayName', 'Samaccountname', 'adminDescription', 'msDS-ExternalDirectoryObjectID')
$groups = Get-ADGroup -Filter * -SearchBase $gwbOU -Properties $properties | 
    Where-Object {$_.adminDescription -ne $null} |
        Select-Object $properties

# Set msDS-ExternalDirectoryObjectID for all groups written back to Active Directory 
foreach ($group in $groups) {
    Set-ADGroup -Identity $group.Samaccountname -Add @{('msDS-ExternalDirectoryObjectID') = $group.adminDescription}
} 

```

次の PowerShell スクリプトを使用して、前のスクリプトの結果を確認できます。 すべてのグループの `adminDescription` 値が `msDS-ExternalDirectoryObjectID` 値と等しいことを確認することもできます。

```powershell

# Provide the DistinguishedName of your Group Writeback target OU
$gwbOU = 'OU=Groups,DC=Contoso,DC=com'

# Get all groups written back to Active Directory
$properties = @('displayName', 'Samaccountname', 'adminDescription', 'msDS-ExternalDirectoryObjectID')
$groups = Get-ADGroup -Filter * -SearchBase $gwbOU -Properties $properties | 
    Where-Object {$_.adminDescription -ne $null} |
        Select-Object $properties

$groups | select displayName, adminDescription, 'msDS-ExternalDirectoryObjectID', @{Name='Equal';Expression={$_.adminDescription -eq $_.'msDS-ExternalDirectoryObjectID'}}

```

### 手順 2: Microsoft Entra Connect 同期サーバーをステージング モードにし、同期スケジューラを無効にする

1. Microsoft Entra Connect 同期ウィザードを起動します。
2. **設定**を選択します。
3. [ **ステージング モードの構成]** を選択し、[ **次へ**] を選択します。
4. Microsoft Entra 資格情報を入力します。
5. [ **ステージング モードを有効にする]** チェック ボックスをオンにし、[ **次へ**] を選択します。

    [Image: ステージング モードの有効化を示すスクリーンショット。]
6. **設定**を選択します。
7. **[終了]** を選択します。

    [Image: ステージング モードの成功を示すスクリーンショット。]
8. Microsoft Entra Connect サーバーで、管理者として PowerShell プロンプトを開きます。
9. 同期スケジューラを無効にします。

    ```PowerShell
    Set-ADSyncScheduler -SyncCycleEnabled $false  
    ```

### 手順 3: カスタム グループ受信規則を作成する

Microsoft Entra Connect 同期規則エディターで、現在 Microsoft Entra ID でマスターされ、 `NULL` メール属性を持つクラウドで作成されたグループを対象とする受信同期規則を作成します。 この受信同期規則は、 `cloudNoFlow` 属性を `True`に設定する結合規則です。

このルールの目的は、グループ ライトバックが無効になった後も接続同期Microsoft Entraが結合オブジェクトとして認識し続けることができるように、これらのグループにフラグを設定することです。 これにより、Microsoft Entra Connect Sync のグループ ライトバックから Microsoft Entra Cloud Sync でのグループ プロビジョニングへの移行中に、既存のオンプレミス グループ オブジェクトがスコープ外として扱われるのを防ぐことができます。

この同期規則は、指定されたスクリプトでユーザー インターフェイスまたは PowerShell を使用して作成できます。

#### ユーザー インターフェイスでカスタム グループ受信規則を作成する

1. **[スタート**] メニューで、同期規則エディターを起動します。
2. **方向**でドロップダウンリストから**受信**を選択し、**新しいルールの追加**を選択します。
3. [ **説明** ] ページで、次の値を入力し、[ **次へ**] を選択します。

    - **名前**: ルールにわかりやすい名前を付けます。
    - **説明**: わかりやすい説明を追加します。
    - **接続システム**: カスタム同期規則を作成する Microsoft Entra コネクタを選択します。
    - **接続されたシステム オブジェクトの種類**: グループを選択 **します**。
    - **メタバース オブジェクトの種類**: **グループ**を選択します。
    - **リンクの種類**: **[結合**] を選択します。
    - **優先順位**: システム内で一意の値を指定します。 既定の規則よりも優先されるように、100 より小さい値を使用することをお勧めします。
    - **タグ：** フィールドは空のままにします。

    [Image: 受信同期規則を示すスクリーンショット。]
4. [ **スコープ フィルター** ] ページで、次の値を追加し、[ **次へ**] を選択します。

    | 属性 | オペレーター | 値 |
    | --- | --- | --- |
    | `cloudMastered` | `EQUAL` | `true` |
    | `mail` | `ISNULL` |  |

    [Image: スコープ フィルターを示すスクリーンショット。]
5. [ **結合ルール** ] ページで、[ **次へ**] を選択します。
6. **変換の追加** ページで、**FlowType**として**定数**を選択します。 **[ターゲット属性] で**、**cloudNoFlow** を選択します。 **「ソース」** で **「True」** を選択します。

    [Image: 変換の追加を示すスクリーンショット。]
7. **[追加]** を選択します。

#### PowerShell でカスタム グループ受信規則を作成する

1. Microsoft Entra Connect サーバーで、管理者として PowerShell プロンプトを開きます。
2. モジュールをインポートします。

    ```PowerShell
    Import-Module ADSync
    ```
3. 同期規則の優先順位に一意の値を指定します (0 から 99)。

    ```PowerShell
    # Provide the sync rule precedence (0-99)
    [int] $inboundSyncRulePrecedence = 88
    ```
4. 次のスクリプトを実行します。

    ```PowerShell
     New-ADSyncRule  `
     -Name 'In from AAD - Group SOAinAAD coexistence with Cloud Sync' `
     -Identifier 'e4eae1c9-b9bc-4328-ade9-df871cdd3027' `
     -Description 'https://learn.microsoft.com/entra/identity/hybrid/cloud-sync/migrate-group-writeback' `
     -Direction 'Inbound' `
     -Precedence $inboundSyncRulePrecedence `
     -PrecedenceAfter '00000000-0000-0000-0000-000000000000' `
     -PrecedenceBefore '00000000-0000-0000-0000-000000000000' `
     -SourceObjectType 'group' `
     -TargetObjectType 'group' `
     -Connector 'b891884f-051e-4a83-95af-2544101c9083' `
     -LinkType 'Join' `
     -SoftDeleteExpiryInterval 0 `
     -ImmutableTag '' `
     -OutVariable syncRule
    
     Add-ADSyncAttributeFlowMapping  `
     -SynchronizationRule $syncRule[0] `
     -Source @('true') `
     -Destination 'cloudNoFlow' `
     -FlowType 'Constant' `
     -ValueMergeType 'Update' `
     -OutVariable syncRule
    
     New-Object  `
     -TypeName 'Microsoft.IdentityManagement.PowerShell.ObjectModel.ScopeCondition' `
     -ArgumentList 'cloudMastered','true','EQUAL' `
     -OutVariable condition0
    
     New-Object  `
     -TypeName 'Microsoft.IdentityManagement.PowerShell.ObjectModel.ScopeCondition' `
     -ArgumentList 'mail','','ISNULL' `
     -OutVariable condition1
    
     Add-ADSyncScopeConditionGroup  `
     -SynchronizationRule $syncRule[0] `
     -ScopeConditions @($condition0[0],$condition1[0]) `
     -OutVariable syncRule
    
     Add-ADSyncRule  `
     -SynchronizationRule $syncRule[0]
    
     Get-ADSyncRule  `
     -Identifier 'e4eae1c9-b9bc-4328-ade9-df871cdd3027'
    ```

### 手順 4: カスタム グループの送信規則を作成する

また、リンクの種類が `JoinNoFlow` の送信同期規則と、 `cloudNoFlow` が `True`に設定されているグループを選択するスコープ フィルターも必要です。 この送信規則は、Microsoft Entra Connect Sync でグループ ライトバックが無効になった後も結合関係を維持し、それらのグループに対してオブジェクトの追加、オブジェクトの削除、および非参照属性の更新がエクスポートされないようにします。

この規則がないと、以前に書き戻されたグループはスコープ内ではなくなったと解釈され、次の同期サイクル中にオンプレミスの Active Directoryから削除される可能性があります。 このルールは、グループ の書き戻し v2 を安全に廃止し、Microsoft Entra Cloud Sync がグループ プロビジョニングの責任を引き継ぐようにするために必要です。

この同期規則は、指定されたスクリプトでユーザー インターフェイスまたは PowerShell を使用して作成できます。

#### ユーザー インターフェイスでカスタム グループ送信規則を作成する

1. [ **方向**] で、ドロップダウン リストから [ **送信** ] を選択し、[ **ルールの追加]** を選択します。
2. [ **説明** ] ページで、次の値を入力し、[ **次へ**] を選択します。

    - **名前**: ルールにわかりやすい名前を付けます。
    - **説明**: わかりやすい説明を追加します。
    - **接続システム**: カスタム同期規則を作成する Active Directory コネクタを選択します。
    - **接続されたシステム オブジェクトの種類**: グループを選択 **します**。
    - **メタバース オブジェクトの種類**: **グループ**を選択します。
    - **リンクの種類**: **JoinNoFlow** を選択します。
    - **優先順位**: システム内で一意の値を指定します。 既定の規則よりも優先されるように、100 より小さい値を使用することをお勧めします。
    - **タグ**: フィールドは空のままにします。

    [Image: 送信同期規則を示すスクリーンショット。]
3. [ **スコープ フィルター** ] ページで、[ **属性**] で **cloudNoFlow** を選択します。 **[演算子**] で [**EQUAL**] を選択します。 **[値]** で [True] を選択**します**。 **[次へ]** を選択します。

    [Image: 送信スコープ フィルターを示すスクリーンショット。]
4. [ **結合ルール** ] ページで、[ **次へ**] を選択します。
5. **[変換]** ページで **[追加]** を選択します。

#### PowerShell でカスタム グループ受信規則を作成する

1. Microsoft Entra Connect サーバーで、管理者として PowerShell プロンプトを開きます。
2. モジュールをインポートします。

    ```PowerShell
    Import-Module ADSync
    ```
3. 同期規則の優先順位に一意の値を指定します (0 から 99)。

    ```PowerShell
    # Provide the sync rule precedence (0-99)
    [int] $outboundSyncRulePrecedence = 89
    ```
4. グループ ライトバック用の Active Directory コネクタを取得します。

    ```PowerShell
    # Provide the name of your Active Directory Connector
    $connectorAD = Get-ADSyncConnector -Name "Contoso.com"
    ```
5. 次のスクリプトを実行します。

    ```PowerShell
     New-ADSyncRule  `
     -Name 'Out to AD - Group SOAinAAD coexistence with Cloud Sync' `
     -Identifier '419fda18-75bb-4e23-b947-8b06e7246551' `
     -Description 'https://learn.microsoft.com/entra/identity/hybrid/cloud-sync/migrate-group-writeback' `
     -Direction 'Outbound' `
     -Precedence $outboundSyncRulePrecedence `
     -PrecedenceAfter '00000000-0000-0000-0000-000000000000' `
     -PrecedenceBefore '00000000-0000-0000-0000-000000000000' `
     -SourceObjectType 'group' `
     -TargetObjectType 'group' `
     -Connector $connectorAD.Identifier `
     -LinkType 'JoinNoFlow' `
     -SoftDeleteExpiryInterval 0 `
     -ImmutableTag '' `
     -OutVariable syncRule
    
     New-Object  `
     -TypeName 'Microsoft.IdentityManagement.PowerShell.ObjectModel.ScopeCondition' `
     -ArgumentList 'cloudNoFlow','true','EQUAL' `
     -OutVariable condition0
    
     Add-ADSyncScopeConditionGroup  `
     -SynchronizationRule $syncRule[0] `
     -ScopeConditions @($condition0[0]) `
     -OutVariable syncRule
    
     Add-ADSyncRule  `
     -SynchronizationRule $syncRule[0]
    
     Get-ADSyncRule  `
     -Identifier '419fda18-75bb-4e23-b947-8b06e7246551'
    ```

### 手順 5: PowerShell を使用して構成を完了する

1. Microsoft Entra Connect サーバーで、管理者として PowerShell プロンプトを開きます。
2. `ADSync` モジュールをインポートします。

    ```PowerShell
    Import-Module ADSync
    ```
3. 完全同期サイクルを実行します。

    ```PowerShell
    Start-ADSyncSyncCycle -PolicyType Initial
    ```
4. テナントのグループ ライトバック機能を無効にします。

    警告

    この操作は元に戻すことができません。 グループ ライトバック v2 を無効にすると、Microsoft Entra 管理センターの **[書き戻しが有効]** 設定とは別に、すべての Microsoft 365 グループが Active Directory に書き戻されます。

    ```PowerShell
    Set-ADSyncAADCompanyFeature -GroupWritebackV2 $false 
    ```
5. 完全同期サイクルをもう一度実行します。

    ```PowerShell
    Start-ADSyncSyncCycle -PolicyType Initial
    ```
6. 同期スケジューラを再び可能にします。

    ```PowerShell
    Set-ADSyncScheduler -SyncCycleEnabled $true  
    ```

    [Image: PowerShell の実行を示すスクリーンショット。]

### 手順 6: Microsoft Entra Connect 同期サーバーをステージング モードから削除する

1. Microsoft Entra Connect 同期ウィザードを起動します。
2. **設定**を選択します。
3. [ **ステージング モードの構成]** を選択し、[ **次へ**] を選択します。
4. Microsoft Entra 資格情報を入力します。
5. [ **ステージング モードを有効にする** ] チェック ボックスをオフにし、[ **次へ**] を選択します。
6. **設定**を選択します。
7. **[終了]** を選択します。

### 手順 7: Microsoft Entra Cloud Sync を構成する

Microsoft Entra Connect Sync で、既存の書き戻しグループに対する no-flow ルールの構成が完了したので、Active Directory へのセキュリティ グループのプロビジョニングを引き継ぐように Microsoft Entra Cloud Sync をセットアップして構成します。 詳細については、「 [Microsoft Entra Cloud Sync を使用した Active Directory へのグループのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)」を参照してください。

Microsoft Entra Connect Sync Group Writeback を廃止する前に、Microsoft Entra Cloud Sync がグループをプロビジョニングし、それらのメンバーシップを Active Directory で維持していることを確認してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory"} -->
## MICROSOFT ENTRA ID オブジェクトを AD にプロビジョニングする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-10
- Summary: Cloud Sync Microsoft Entraがユーザー、グループ、メンバーシップをMicrosoft Entra IDからActive Directoryにプロビジョニングし、サポートされているシナリオを確認する方法について説明します。

Microsoft Entra Cloud Sync では、Microsoft Entra IDからオンプレミス Active Directory Domain Services (AD DS) に**ユーザー、グループ、およびグループ メンバーシップ**をプロビジョニングできます。 これにより、Microsoft Entra ID ガバナンスを使用してクラウドからの ID ライフサイクルを管理しながら、[Active Directory](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) (AD) に依存しているアプリケーションへのアクセスを中断なく維持できます。 [Microsoft Entraクラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)に基づいて構築され、[ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview)と[グループ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview)の機関ソース (SOA) 機能を補完します。

多くの組織はクラウドファーストの ID モデルを採用していますが、アプリケーション アクセスには AD に依存しています。 Microsoft Entra IDから AD へのプロビジョニングは、管理者が次の操作を行えるようにすることで、このギャップを埋めます。

- **信頼できる情報源**として、Microsoft Entra ID で ID を直接管理します。
- **クラウドネイティブ ユーザー、SOA に変換されたユーザー、ゲスト 企業間 (B2B) ユーザーを** AD にプロビジョニングします。
- **セキュリティ グループ**とそのメンバーシップを AD にプロビジョニングします。
- **セキュリティ識別子 (SID)** などのキー属性を保持することで、ID の継続性を維持します。
- ID 属性とグループ メンバーシップは、Microsoft Entra IDと AD の間で揃えておきます。
- 中断することなく、オンプレミスアプリケーションへの継続的なアクセスを確保します。

注

Active Directoryへの**ユーザー**のプロビジョニングは現在**プレビュー段階です**。 Active Directoryへの**グループ**のプロビジョニングは一般提供されています。 プレビュー期間については、「[Microsoft Azure プレビューの追加使用条件](https://azure.microsoft.com/support/legal/preview-supplemental-terms/)」を参照してください。

### どのように機能するのか

Microsoft Entra IDは権限のソースです。 Microsoft Entra プロビジョニング サービスは、変更を検出し、Cloud Sync プロビジョニング エージェントを介してターゲット AD ドメインに送信します。

[Image: プロビジョニング サービスとクラウド同期エージェントから Active Directory へのMicrosoft Entra IDからのプロビジョニング フローを示す図。]

1. 管理者は、Microsoft Entra 管理センターでプロビジョニング構成を構成します。
2. Microsoft Entra プロビジョニング サービスは、オブジェクトの変更 (作成、更新、削除) を検出します。
3. 変更は、オンプレミスのクラウド同期プロビジョニング エージェントに送信されます。
4. エージェントは、ターゲット Active Directory ドメインにユーザー オブジェクトとグループ オブジェクトをプロビジョニングします。

注

**パスワードの書き戻し** (Microsoft Entra ID から AD へのパスワード変更の同期) は利用できません。

同期エンジンがオブジェクトと一致し、プロビジョニングし、削除する方法の詳細については、「[Active Directoryへのプロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-provisioning-to-active-directory-works)」を参照してください。 プロビジョニング グループのみ、ユーザーのみ、またはその両方を選択するには、「 [展開オプション」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-deployment-options-provision-to-active-directory)を参照してください。

### プロビジョニングできる内容

| 能力 | Description |
| --- | --- |
| 結合されたユーザーとグループの構成 | 1 つの構成で、ユーザーとグループの両方が同じ AD ドメインにプロビジョニングされます。 |
| スタンドアロン ユーザー専用の構成 | 専用構成では、ユーザーのみがプロビジョニングされます。 |
| スタンドアロン グループのみの構成 | 専用構成では、グループのみがプロビジョニングされます。 |
| クラウドネイティブ ユーザー プロビジョニング | Microsoft Entra IDで作成されたユーザーは AD にプロビジョニングされます。 |
| SOA で変換されたユーザー プロビジョニング | 権限のソースがクラウドに変換されたユーザーは、引き続き継続的に AD にプロビジョニングできます。 |
| ゲスト (B2B) ユーザー プロビジョニング | 外部ユーザーを AD にプロビジョニングできます。 |
| セキュリティ グループのプロビジョニング | Microsoft Entra IDのセキュリティ グループは AD にプロビジョニングされます。 |
| グループ メンバーシップ (クラウド + 同期されたユーザー) | グループは、クラウドのみのユーザー メンバーと同期されたユーザー メンバーの両方をサポートします。 |
| 属性の更新 (Entra ID → AD) | Microsoft Entra ID の属性の変更は AD に反映されます。 |
| ディレクトリ拡張属性 | ユーザー アカウントとグループ アカウントに関連付けられているディレクトリ拡張機能は、AD にフローします。 「 [ディレクトリ拡張機能を使用する」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-directory-extension-group-provisioning)を参照してください。 |
| プロビジョニングログと監査 | ログは検証とトラブルシューティングに使用できます。 |

### 主要な動作

- **クラウドは真実の源です。** AD で直接行われた変更は、次の同期サイクル中にMicrosoft Entra IDによって上書きされる可能性があります。 オンプレミスの変更からプロビジョニングされたオブジェクトを保護するには、「 [AD ユーザーとグループの適用を構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-active-directory-object-enforcement)」を参照してください。
- **SOA で変換されたオブジェクトは、クラウドで管理されたままになります。** これらのユーザーとグループは、変換後も AD にプロビジョニングし直すことができます。
- **照合して作成します。** 既存のオブジェクトが照合され、更新されます。新しいオブジェクトは、一致するものが見つからない場合にのみ作成されます。
- **ターゲット OU の動作。** オーバーライドされない限り、既定の組織単位 (OU) が使用されます。 SOA がクラウドに変換されたユーザーは、元の OU に自動的に返されます。 グループではなく、変換されたグループを元の OU に保持するには、ディレクトリ拡張を使用します。 [OU パスの保持を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#preserve-the-ou-path)参照してください。
- **グループ。** セキュリティ グループのみがサポートされています。 オンプレミスのユーザー メンバーシップを明示的に有効にする必要があります。

### Active Directory へのプロビジョニングを使用するタイミング

AD へのプロビジョニングは、いくつかのソース オブ オーソリティ (SOA) シナリオをサポートするメカニズムです。 ここでこれらのシナリオを繰り返すのではなく、次の表を使用して、目標に一致するシナリオに進みます。

| 目標 | Scenario | 詳細情報 |
| --- | --- | --- |
| グループ管理をクラウドに移動するが、AD アクセスを維持する | Microsoft Entra ID ガバナンスを使用してアクセスを管理する。AD DS の最小化 | [グループ SOA をクラウドに変換する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview) |
| ユーザー管理をクラウドに移動するが、AD アクセスを維持する | AD ユーザーを最小限に抑え、ユーザーのライフサイクルを管理する | [ユーザー SOA をクラウドに転送する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview) |
| ユーザー SOA を転送した後も Kerberos アプリへのアクセスを維持する | ユーザーを AD にプロビジョニングする。パスワードレスを使用する (Windows Hello for Business/ Cloud Kerberos Trust) | [ユーザー SOA をクラウドに転送する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview) |
| クラウドのみが変更できるようにプロビジョニング済みグループをロックダウンする | AD グループの強制 | [AD ユーザーとグループの強制を設定する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-active-directory-object-enforcement#mark-groups-for-enforcement) |
| クラウドのみが変更できるように、プロビジョニングされたユーザーをロックダウンする | AD ユーザーの強制適用 | [AD ユーザーとグループの強制を設定する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-active-directory-object-enforcement#mark-users-for-enforcement) |

### サポートされていないもの

サポートされていないシナリオは次のとおりです:

- **同じユーザーを複数の AD ドメインにプロビジョニングする**。 認証の一貫性を確保するために、ユーザーごとに 1 つのターゲット ドメインが適用されます。
- **同じドメイン名を共有する複数のフォレストに** ID をプロビジョニングする。
- **フォレスト間**のユーザー関係のプロビジョニング（たとえば、フォレストをまたいだ上司関係やメンバーシップ）。
- AD への **カスタム セキュリティ属性 (CSA)** のプロビジョニング。
- EXCHANGE**属性を** AD にプロビジョニングする。 ユーザー SOA はクラウド内にあるため、AD ではExchange関連情報は必要ありません。 オンプレミスのExchange Serverを使用せずにExchange受信者を管理する方法については、「[SOA をクラウドに転送した後の最後のExchange Serverの使用停止](https://learn.microsoft.com/ja-jp/exchange/hybrid-deployment/decommission-last-exchange-server)」および[「管理ツールを使用してExchangeハイブリッド環境で受信者を管理](https://learn.microsoft.com/ja-jp/exchange/manage-hybrid-exchange-recipients-with-management-tools)する」を参照してください。
- **メールが有効なグループと配布グループ。** セキュリティ グループのみがサポートされています。
- ユーザーのパスワードを収集するアプリケーションで必要となる**パスワードの書き戻し**は、サポートされていません。 これには、ユーザーのパスワードを使用して LDAP バインドを実行してユーザーを認証するアプリケーションが含まれます。 クラウドで管理されるユーザーには表示する AD DS パスワードがないため、代わりに Kerberos をサポートするアプリケーションではパスワードレス認証を使用します。 詳細については、「 [クラウドマネージド ユーザーがアプリケーションにサインインする方法」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-users-groups-provisioning-walkthrough#how-cloud-managed-users-sign-in-to-the-application)参照してください。
- 複雑な **マルチドメイン ハイブリッド ID アーキテクチャ**。 AD へのプロビジョニングは、単一ドメイン ID の継続性を目的として設計されています。

### ライセンス要件

Active Directoryへのプロビジョニングは、構成ベースのライセンス モデルに従います。

| Configuration | ライセンスが必要 |
| --- | --- |
| **既存の構成** (一般提供前に作成) | ライセンスの変更は必要ありません。 これらの構成は、既存の Microsoft Entra ID P1 ライセンスで引き続き実行されます。 |
| 各テナントにつき最初の**2**つの新しい構成 | Microsoft Entra ID P1。 |
| テナントあたり **2 つ以上**の新しい構成 (3 ~ 20) | Microsoft Entra ID ガバナンス。 |

注

テナントごとに最大 **20** 個の構成 (ドメイン) を構成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/plan-cloud-sync-topologies"} -->
## Microsoft Entra クラウド同期でサポートされているトポロジとシナリオ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/plan-cloud-sync-topologies
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: Microsoft Entra Cloud Sync を使用する、オンプレミスと Microsoft Entra ID 間のさまざまなトポロジについて説明します。

この記事では、Microsoft Entra Cloud Sync を使用する、オンプレミスと Microsoft Entra 間のさまざまなトポロジについて説明します。この記事には、サポートされている構成とシナリオのみが含まれています。

重要

公式に文書化されている構成やアクションを除き、Microsoft は Microsoft Entra Cloud Sync の変更や操作をサポートしません。 このような構成やアクションを行うと、Microsoft Entra Cloud Sync が整合性のない、またはサポートされていない状態になる可能性があります。結果的に、Microsoft ではこのようなデプロイについてテクニカル サポートを提供できなくなります。

### すべてのシナリオとトポロジに関する注意事項

ソリューションを選択するときは、次の情報に留意する必要があります。

- ユーザーとグループは、すべてのフォレストで一意に識別される必要があります。
- クラウドの同期では、フォレスト間の照合は行われません。
- オブジェクトのソース アンカーは自動的に選択されます。 ms-DS-ConsistencyGuid を使用します (存在する場合)。それ以外の場合は、ObjectGUID が使用されます。
- ソース アンカーに使用される属性を変更することはできません。

### Active Directory から Microsoft Entra ID へのサポートされているトポロジ

Active Directory から Microsoft Entra ID へのプロビジョニングでサポートされているトポロジは次のとおりです。

#### 単一フォレスト、単一 Microsoft Entra テナント

[Image: 単一のフォレストと単一のテナントのトポロジを示す図。]

最も単純なトポロジは、1 つのオンプレミス フォレスト (1 つまたは複数のドメイン) と、1 つの Microsoft Entra テナントです。 このシナリオの例については、[チュートリアル: 単一のフォレストと単一の Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest)に関する記事を参照してください。

#### 複数フォレスト、単一 Microsoft Entra テナント

[Image: 1 つのMicrosoft Entra テナントを持つマルチフォレスト トポロジを示す図。]

複数の AD フォレストは一般的なトポロジで、1 つまたは複数のドメインと単一の Microsoft Entra テナントで構成されます。

#### 既存フォレストに Microsoft Entra Connect、新規フォレストにクラウド プロビジョニングを使用

[Image: 既存のフォレストと新しいフォレストのトポロジを示す図。]

このシナリオ トポロジは、マルチフォレスト シナリオに似ています。 ただし、これには既存の Microsoft Entra Connect 環境が含まれており、Microsoft Entra Cloud Sync を使用して新しいフォレストを使用します。このシナリオの例については、「[チュートリアル: 単一の Microsoft Entra テナントを持つ既存のフォレスト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-existing-forest)」を参照してください。

#### 既存のハイブリッド AD フォレストで Microsoft Entra Cloud Sync をパイロット導入

[Image: 単一のMicrosoft Entra テナントを持つ単一フォレスト トポロジを示す図。]

パイロット シナリオでは、Microsoft Entra Connect と Microsoft Entra Cloud Sync の両方が同じフォレストに存在し、それに応じてユーザーとグループのスコープを設定します。 注:オブジェクトは、いずれかのツールでのみスコープ内にある必要があります。

このシナリオの例については、[チュートリアル: 既存の同期済み AD フォレストでの Microsoft Entra Cloud Sync のパイロット](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp)に関する記事を参照してください。

#### 切断されたソースからのオブジェクトのマージ

##### (パブリック プレビュー)

[Image: 2 つの切断された Active Directory フォレストからマージされる 1 人のユーザーの属性を示す図。]

このシナリオでは、ユーザーの属性は 2 つの切断された Active Directory フォレストによって提供されます。

例を次に示します:

- 1 つのフォレスト (1) にほとんどの属性が含まれている。
- 2 番目のフォレスト (2) にはいくつかの属性が含まれている。

2 番目のフォレストには Microsoft Entra Connect サーバーへのネットワーク接続がないため、Microsoft Entra Connect を介してオブジェクトをマージすることはできません。 2 番目のフォレストの Cloud Sync を使用すると、2 番目のフォレストから属性値を取得できます。 Microsoft Entra Connect は、Microsoft Entra ID 内のオブジェクトを同期し、値をそれにマージできます。

この構成は高度であり、このトポロジにはいくつかの注意事項があります。

1. Cloud Sync 構成でソース アンカーとして `ms-DS-ConsistencyGuid` を使用する必要があります。
2. 2 番目のフォレスト内のユーザー オブジェクトの `ms-DS-ConsistencyGuid` は、Microsoft Entra ID の対応するオブジェクトのものと一致する必要があります。
3. 2 番目のフォレスト内の `UserPrincipalName` 属性と `Alias` 属性を設定する必要があり、1 番目のフォレストから同期された属性と一致する必要があります。
4. Cloud Sync 構成の属性マッピングから、値がない、または 2 番目のフォレストで異なる値を持つ可能性があるすべての属性を削除する必要があります。1 番目のフォレストと 2 番目のフォレストの間で属性マッピングは重複することはできません。
5. 最初のフォレストに一致するオブジェクトがない場合、2 番目のフォレストから同期されたオブジェクトに対して、クラウド同期では引き続き Microsoft Entra ID でオブジェクトが作成されます。 オブジェクトには、2 つ目のフォレストのクラウド同期のマッピング構成で定義されている属性のみが含まれます。
6. 2 番目のフォレストからオブジェクトを削除すると、Microsoft Entra ID では一時的に論理的に削除されます。 次の Microsoft Entra Connect 同期サイクルの後に自動的に復元されます。
7. 最初のフォレストからオブジェクトを削除すると、Microsoft Entra ID から論理的に削除されます。 そのオブジェクトは、2 番目のフォレスト内のオブジェクトに変更が加えられる場合を除き、復元されません。 30 日後、オブジェクトは Microsoft Entra ID からハード削除されます。 2 番目のフォレスト内のオブジェクトに変更が加えられた場合は、Microsoft Entra ID の新しいオブジェクトとして作成されます。

### Microsoft Entra ID から Active Directory へのサポートされているトポロジ

Microsoft Entra ID から Active Directory へのプロビジョニングでサポートされているトポロジは次のとおりです。

#### 単一フォレストへのグループ プロビジョニング

[Image: 単一フォレストへの書き戻しの概念図]

最もシンプルなグループ プロビジョニング トポロジは、単一のオンプレミス フォレスト (1 つまたは複数のドメイン) と単一の Microsoft Entra テナントで構成されます。 このシナリオの例については、「[Microsoft Entra IDからActive Directoryにユーザーとグループをプロビジョニングする」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)。

#### 複数フォレストへのグループ プロビジョニング

[Image: 複数フォレストへの書き戻しの概念図]

より高度なグループ プロビジョニング トポロジでは、複数のオンプレミス AD フォレストが単一の Microsoft Entra ID テナントを共有します。

この構成は高度であり、次の点に注意が必要です。

- AD にプロビジョニングされたグループ メンバーシップには、AD アカウントを持つメンバーのみが含まれます。 これらのメンバーは、オンプレミス同期ユーザー、ユーザー プロビジョニングのスコープ内にあるため、Cloud Sync が AD にプロビジョニングするクラウドマネージド ユーザー、または他のクラウドで作成されたセキュリティ グループにすることができます。
- オンプレミスの同期されたユーザーは、自分のアカウントに onPremisesObjectIdentifier 属性が設定されている必要があります。
- onPremisesObjectIdentifier は、対象となる Active Directory 環境内の対応する objectGUID と一致している必要があります。
- オンプレミスのユーザーの objectGUID 属性とクラウド ユーザーの onPremisesObjectIdentifier 属性は、Microsoft Entra Cloud Sync (バージョン [1.1.1370.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1113700)) または Microsoft Entra Connect Sync (バージョン [2.2.8.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#2280)) のいずれかを使用して同期できます。
- テナント内で、両方のフォレストのユーザーを含む共通グループを共有することもできます。
- ただし、他のフォレストに存在しないユーザーは、オンプレミスでプロビジョニングされるときにグループのメンバーとしてプロビジョニングされません。 そのため、contoso.com と fabrikam.com のユーザーを含むグループが Microsoft Entra ID にある場合、contoso.com フォレストに存在するユーザーのみが、contoso.com にプロビジョニングされるときにグループのメンバーになります。 fabrikam でも同様です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/reference-cloud-sync-faq"} -->
## Microsoft Entra クラウド同期に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-cloud-sync-faq
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-03-30
- Summary: このドキュメントでは、クラウド同期に関してよく寄せられる質問について説明します。

Microsoft Entra クラウド同期に関してよくあるご質問をお読みください。

### クラウド同期はどのくらいの頻度で実行されるのですか?

パスワード ハッシュ同期は 2 分から 5 分ごとにスケジュールされます。 ユーザーとグループのクラウド プロビジョニング同期は、約 10 ~ 20 分ごとにスケジュールされます。 ただし、Microsoft Entra ID でのオブジェクトのプロビジョニングにかかる実際の時間は、各同期サイクルで保留中の変更の数によって異なります。 変更の量が多いほどプロビジョニング時間が長くなる一方で、セットが小さいほど迅速に完了する場合があります。 そのため、オブジェクト同期の待機時間は、同期サイクル間隔と処理される変更の量の両方によって影響を受けます。

### パスワード ハッシュ同期が初回実行時にエラーになります。 なぜですか?

この動作は想定内です。 このエラーは、ユーザー オブジェクトが Microsoft Entra ID に存在しないことが原因で発生します。 ユーザーがプロビジョニングされたら、何度か実行されるのを待ってから、パスワード ハッシュ同期のエラーが発生しないことを確認してください。

### Microsoft Entra Connect 同期とクラウド同期の違いは何ですか?

Microsoft Entra Connect 同期では、プロビジョニングはオンプレミスの同期サーバーで実行されます。 構成は、オンプレミスの同期サーバーに格納されます。 Microsoft Entra クラウド同期では、プロビジョニングの構成はクラウドに保存され、Microsoft Entra プロビジョニング サービスの一部としてクラウドで実行されます。 詳細な比較については、詳しい記事「[Microsoft Entra クラウド同期とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)」をご覧ください。

### クラウド同期を使用して複数の Active Directory フォレストから同期することはできますか?

はい。 クラウド プロビジョニングを使用すると、複数の Active Directory フォレストから同期することができます。 複数フォレスト環境では、すべての参照 (マネージャーなど) がドメイン内に存在する必要があります。

### エージェントはどのように更新されますか?

エージェントは、Microsoft によって自動的にアップグレードされます。 その結果、エージェントの新しいバージョンのテストや検証に伴う IT 部門の負担が軽減されます。

### 自動アップグレードを無効にすることはできますか?

自動アップグレードを無効にするためのサポートされた方法はありません。

### クラウド同期のソース アンカーは変更できますか?

クラウド同期では、既定で、ソース アンカーとして ObjectGUID へのフォールバックが設定された ms-ds-consistency-GUID が使用されます。 ソース アンカーを変更するためのサポートされた方法はありません。

### クラウド同期を使用すると、新しいサービス プリンシパルが AD ドメイン名と一緒に表示されます。これは予想どおりですか?

はい。クラウド同期では、プロビジョニング構成用のサービス プリンシパルが作成されますが、そのサービス プリンシパル名としてドメイン名が使用されます。 サービス プリンシパルの構成は変更しないでください。

### 同期済みユーザーが次回ログオン時にパスワードの変更を要求される場合はどうなりますか?

パスワード ハッシュ同期がクラウド同期で有効になっていて、同期されるユーザーがオンプレミス AD に次にログオンするときにパスワードを変更する必要がある場合、クラウド同期は "変更予定" のパスワード ハッシュを Microsoft Entra ID にプロビジョニングしません。 ユーザーがパスワードを変更すると、ユーザー パスワード ハッシュが AD から Microsoft Entra ID にプロビジョニングされます。

### クラウド同期では、オブジェクトの ms-ds-consistencyGUID の書き戻しはサポートされていますか?

いいえ。クラウド同期では、いずれのオブジェクト (ユーザー オブジェクトを含む) についても ms-ds-consistencyGUID の書き戻しはサポートされません。

### クラウド同期を利用してユーザーをプロビジョニングしています。構成を削除しました。 Microsoft Entra ID に同期された古いオブジェクトがまだ表示されるのはなぜですか?

構成を削除しても、Microsoft Entra ID 内の同期されたオブジェクトがクラウド同期によって自動的に削除されることはありません。 古いオブジェクトがないことを確認するには、構成のスコープを空のグループまたは組織単位に変更します。 プロビジョニングが実行されてオブジェクトがクリーンアップされたら、構成を無効にして削除してください。

### クラウド同期エージェントをアンインストールしました。 ポータルから削除されるまでどのくらいの時間がかかりますか?

クラウド同期エージェントをアンインストールまたは停止しても、エージェントはすぐに Microsoft Entra 管理センターから削除されません。 約 1 時間後、エージェントは **非アクティブ**と表示されます。 約 10 日後、エージェントは論理的に削除され、ポータルに表示されなくなります。 エージェントは、証明書の有効期限が切れると完全に削除されるため、Microsoft サービスとのそれ以上の対話ができなくなります。 詳細については、「 [アンインストール後のポータルからのエージェントの削除」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure#agent-removal-from-the-portal-after-uninstall)。

### クラウド同期は Exchange ハイブリッドをサポートしますか?

はい。 クラウド同期で Exchange ハイブリッド シナリオがサポートされるようになりました。 詳細については、「[クラウド同期を使用した Exchange ハイブリッド](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/exchange-hybrid)」を参照してください

### クラウド プロビジョニング エージェントを Windows Server Core にインストールすることはできますか?

いいえ。Server Core へのエージェントのインストールはサポートされません。

### Microsoft Entra Connect 同期と同じサーバーにクラウド プロビジョニング エージェントをインストールできますか?

はい。エージェントを同じサーバーにインストールすることがサポートされています。

### クラウド プロビジョニング エージェントでステージング サーバーを使用できますか?

いいえ。ステージング サーバーはサポートされていません。

### ゲスト ユーザー アカウントを同期させることはできますか?

いいえ。ゲスト ユーザー アカウントの同期はサポートされていません。

### クラウド同期にスコープ指定されている OU から、Microsoft Entra Connect にスコープ指定されている OU にユーザーを移動すると、どうなりますか?

ユーザーが削除され、再作成されます。 クラウド同期に対してスコープが指定されている OU からのユーザーの移動は、削除操作として表示されます。 Microsoft Entra Connect によって管理されている OU にユーザーが移動される場合は、Microsoft Entra ID に再プロビジョニングされて、新しいユーザーが作成されます。

### クラウド同期フィルターのスコープ内にある OU の名前を変更または移動した場合、Microsoft Entra ID で作成されたユーザーはどうなりますか?

何もありません。 OU の名前を変更したり、OU を移動したりしても、ユーザーは削除されません。

### Microsoft Entra クラウド同期では、大規模なグループはサポートされていますか?

はい。 現在、OU スコープ フィルターを使用して同期された最大 50,000 人のグループ メンバーがサポートされています。

### Microsoft Entra クラウド同期では、グループ スコープで入れ子になったグループはサポートされていますか?

いいえ。 第 1 レベルを超えて入れ子になっているオブジェクトは、セキュリティ グループを使用してスコープを設定するときには含まれません。 パイロット シナリオ用のグループ スコープ フィルターのみをお使いください。大規模なグループの同期には制限があります。

### 複数のエージェントがインストールされている場合、クラウド プロビジョニング エージェントで負荷分散されますか?

いいえ。 常にアクティブなのは 1 つのエージェントのみです。

### クラウド プロビジョニング エージェントでは、優先ドメイン コントローラーの一覧を設定する必要がありますか?

いいえ。 優先ドメイン コントローラーの一覧は、[エージェントのインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)時に指定されますが、省略可能です。 優先 DC が構成されていない場合、エージェントではドメイン内で使用可能な任意の DC が使用されます。

### マルチフォレスト環境を使用しており、2 つのフォレスト間のネットワークでは NAT (ネットワーク アドレス変換) を使用しています。 これら 2 つのフォレスト間で Microsoft Entra Cloud Sync を使用することはサポートされていますか?

いいえ。NAT をサポートしていない Active Directory に依存しているため、NAT 経由での Microsoft Entra Cloud Sync の使用はサポートされていません。 [NAT 経由の Active Directory のサポート境界](https://learn.microsoft.com/troubleshoot/windows-server/active-directory/support-for-active-directory-over-nat)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/reference-error-codes"} -->
## Microsoft Entra クラウド同期のエラー コードと説明 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-error-codes
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-21
- Summary: クラウド同期エラー コードのリファレンス記事

エラー コードとその説明の一覧を次に示します。

### エラー コード

| エラー コード | 詳細 | シナリオ | 解像度 |
| --- | --- | --- | --- |
| [TimeOut] | エラー メッセージ:オンプレミスのエージェントにアクセスして構成を同期するときに、要求タイムアウト エラーが検出されました。 クラウド同期エージェントに関連するその他の問題については、トラブルシューティング ガイダンスを参照してください。 | HIS への要求がタイムアウトになりました。現在のタイムアウト値は 10 分です。 | [トラブルシューティング ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-troubleshoot)を参照してください。 |
| HybridSynchronizationActiveDirectoryInternalServerError | エラー メッセージ:この時点で、この要求を処理できませんでした。 この問題が解決しない場合は、サポートに連絡し、次のジョブ ID を提供してください。AD2AADProvisioning.30b500eaf9c643b2b78804e80c1421fe.5c291d3c-d29f-4570-9d6b-f0c2fa3d5926. 追加情報:HTTP 要求の処理中に例外が発生しました。 | 検索要求に対して SCIM 要求で受信したパラメーターを処理できませんでした。 | 詳細については、この例外の 'Response' プロパティによって返される HTTP 応答を参照してください。 |
| HybridIdentityServiceNoAgentsAssigned | エラー メッセージ:同期しようとしているドメインでアクティブなエージェントが見つかりません。エージェントが削除されているかをご確認ください。 その場合は、エージェントを再インストールします。 | 実行中のエージェントはありません。 エージェントが削除された可能性があります。 新しいエージェントを登録します。 | この場合、ポータルでドメインに割り当てられているエージェントは表示されません。 |
| HybridIdentityServiceNoActiveAgents | エラー メッセージ: 同期しようとしているドメインでアクティブなエージェントが見つかりません。エージェントがインストールされているサーバーに移動して、エージェントが実行されているかどうかを確認し、[サービス] で **Microsoft Entra Connect クラウド同期エージェント**が実行されているかどうかを確認してください。 | エージェントが ServiceBus エンドポイントをリッスンしていません。 [エージェントは、Service Bus への接続を許可しないファイアウォールの内側にあります](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-connectors-with-proxy-servers#use-the-outbound-proxy-server) |  |
| HybridIdentityServiceInvalidResource | エラー メッセージ:この時点で、この要求を処理できませんでした。 この問題が解決しない場合は、サポートに連絡し、次のジョブ ID を提供してください。AD2AADProvisioning.3a2a0d8418f34f54a03da5b70b1f7b0c.d583d090-9cd3-4d0a-aee6-8d666658c3e9. 追加情報:クラウド同期のセットアップに問題があるようです。 オンプレミスの AD ドメインにクラウド同期エージェントを再登録し、ポータルから構成を再起動してください。 | アクセス先のエージェントが HIS によって認識されるように、リソース名を設定する必要があります。 | オンプレミスの AD ドメインにクラウド同期エージェントを再登録し、ポータルから構成を再起動してください。 |
| HybridIdentityServiceAgentSignalingError | エラー メッセージ:この時点で、この要求を処理できませんでした。 この問題が解決しない場合は、サポートに連絡し、次のジョブ ID を提供してください。AD2AADProvisioning.92d2e8750f37407fa2301c9e52ad7e9b.efb835ef-62e8-42e3-b495-18d5272eb3f9. 追加情報:この時点で、この要求を処理できませんでした。 この問題が解決しない場合は、(構成の状態ウィンドウから) ジョブ ID を使用してサポートにお問い合わせください。 | Service Bus からエージェントにメッセージを送信できません。 Service Bus が停止しているか、エージェントが応答していない可能性があります。 | この問題が解決しない場合は、(構成の状態ウィンドウから) ジョブ ID を使用してサポートにお問い合わせください。 |
| AzureDirectoryServiceServerBusy | エラー メッセージ:エラーが発生しました。 Error Code:81。 エラーの説明: Microsoft Entra ID は現在ビジー状態です。 この操作は自動的に再試行されます。 24 時間を超えてこの問題が解決しない場合は、テクニカル サポートにお問い合わせください。 追跡 ID:8a4ab3b5-3664-4278-ab64-9cff37fd3f4f サーバー名: | Microsoft Entra ID は現在ビジー状態です。 | 24 時間を超えてこの問題が解決しない場合は、テクニカル サポートにお問い合わせください。 |
| AzureActiveDirectoryAuthenticationFailed | エラー メッセージ:この時点で、この要求を処理できませんでした。 この問題が解決しない場合は、サポートに連絡し、次のジョブ ID を提供してください。AD2AADProvisioning.60b943e88f234db2b887f8cb91dee87c.707be0d2-c6a9-405d-a3b9-de87761dc3ac. 追加情報:この時点で、この要求を処理できませんでした。 この問題が解決しない場合は、(構成の状態ウィンドウから) ジョブ ID を使用してサポートにお問い合わせください。 その他のエラーの詳細:UnexpectedError. | 不明なエラー。 | この問題が解決しない場合は、(構成の状態ウィンドウから) ジョブ ID を使用してサポートにお問い合わせください。 |
| HybridSynchronizationActiveDirectoryUnexpectedDuplicateEntriesFound | エラー メッセージ: Active Directory クエリは、予期された場合に複数のオブジェクトを返しました。 ディレクトリ内の重複するオブジェクトをクリーンアップして、この問題を解決します。 | LDAP 検索クエリで複数のオブジェクトが返されるため、プロビジョニングでは、Entra オブジェクトを Active Directory 内の一意のオブジェクトに関連付けることができません。 | Active Directory へのグループ プロビジョニング ジョブが検疫中の場合:1. *属性 msDS-ExternalDirectoryObjectId* の "Group\_" で始まる同じ値を持つ Active Directory 内のグループを検索します。 次の表の後に PowerShell スクリプトを使用して、グループを見つけることができます。2. 同じ値を持つグループのセットごとに、重複するグループを削除します。 |

*属性 msDS-ExternalDirectoryObjectId* の "Group\_" で始まる同じ値を持つ Active Directory 内のグループを検索するには、次の PowerShell スクリプトを実行します。

```powershell
$attributeName = "msDS-ExternalDirectoryObjectId"
$prefix = "Group_"
$allGroups = Get-ADGroup -LDAPFilter "($attributeName=$prefix*)" -Properties $attributeName
$duplicateGroups = $allGroups |
    Group-Object -Property $attributeName |
    Where-Object { $_.Count -gt 1 }
if ($duplicateGroups) {
    foreach ($group in $duplicateGroups) {
        Write-Host "Value: $($group.Name) (Count: $($group.Count))"
        $group.Group | Select-Object Name, DistinguishedName, msDS-ExternalDirectoryObjectId | Format-Table -AutoSize
        Write-Host "----"
    }
} else {
    Write-Host "No duplicate groups found"
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/reference-expressions"} -->
## Microsoft Entra Cloud Sync の式と関数リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-expressions
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: 参考

クラウド同期を構成する場合、指定できる属性マッピングの種類の 1 つが式マッピングです。

式マッピングを使用すると、スクリプトのような式を使用して属性をカスタマイズできます。 これにより、オンプレミスのデータを新しい値または別の値に変換できます。 たとえば、この 1 つの属性はクラウド アプリケーションのいずれかで使用されるため、2 つの属性を 1 つの属性に結合できます。

次のドキュメントでは、データの変換に使用されるスクリプトのような式について説明します。 これはプロセスの一部にすぎません。 次に、この式を使用して、テナントへの Web 要求に配置する必要があります。 その詳細については、「変換」を参照してください [。](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-transformation)

### 構文の概要

属性マッピングの式の構文は、Visual Basic for Applications (VBA) 関数を思い起こさせます。

- 式全体は、名前の後にかっこで囲まれた引数で構成される関数の観点から定義する必要があります。 *FunctionName(`<<argument 1>>`,`<<argument N>>`)*
- 関数は相互に入れ子にすることができます。 例えば：*FunctionOne(FunctionTwo(`<<argument1>>`))*
- 関数には、次の 3 種類の引数を渡すことができます。

    1. 属性。角かっこで囲む必要があります。 例: [attributeName]
    2. 文字列定数。二重引用符で囲む必要があります。 例: "United States"
    3. その他の関数。 例: FunctionOne(`<<argument1>>`, FunctionTwo(`<<argument2>>`))
- 文字列定数の場合、文字列に円記号 ( \ ) または引用符 ( " ) が必要な場合は、円記号 ( \ ) 記号でエスケープする必要があります。 例: "会社名: \"Contoso\""

### 関数の一覧

| 関数の一覧 | 説明 |
| --- | --- |
| 追加 | ソース文字列値を受け取り、サフィックスを末尾に追加します。 |
| BitAnd | BitAnd 関数は、値に指定のビットを設定します。 |
| CBool | CBool 関数は、式の評価結果に基づいてブール値を返します。 |
| ConvertFromBase64 | ConvertFromBase64 関数は、指定した base64 でエンコードされた値を正規の文字列に変換します。 |
| ConvertToBase64 | ConvertToBase64 関数は、文字列を Unicode の base64 文字列に変換します。 |
| ConvertToUTF8Hex | ConvertToUTF8Hex 関数は、文字列を UTF8 の 16 進数でエンコードされた値に変換します。 |
| 数える | Count 関数は、複数値の属性内の要素数を返します。 |
| Cstr | CStr 関数は、文字列データ型に変換します。 |
| DateFromNum | DateFromNum 関数は、AD の日付形式の値を DateTime 型に変換します。 |
| DNComponent | DNComponent 関数は、指定した DN コンポーネントの値を左から返します。 |
| エラー | Error 関数は、カスタム エラーを返すために使用します。 |
| FormatDateTime | 1 つの形式から日付文字列を受け取り、別の形式に変換します。 |
| GUID | 関数 Guid は、新しいランダム GUID を生成します。 |
| IIF | IIF 関数は、指定した条件に基づいて、使用できる一連の値のうち、いずれかを返します。 |
| InStr | InStr 関数は、文字列内の部分文字列の最初の出現箇所を検索します。 |
| IsNull | 式の評価結果が Null の場合、IsNull 関数は true を返します。 |
| IsNullOrEmpty | 式が null または空の文字列の場合、IsNullOrEmpty 関数は true を返します。 |
| IsPresent | 式の評価結果が Null でもなく、空でもない文字列の場合、IsPresent 関数は true を返します。 |
| IsString | 式を文字列型として評価できる場合、IsString 関数の評価結果は True になります。 |
| 項目 | Item 関数は複数値の文字列/属性から 1 つの項目を返します。 |
| 参加 | Join() は Append() に似ていますが、複数の **ソース** 文字列値を 1 つの文字列に結合でき、各値は **区切り文字列** で区切られます。 |
| 左 | Left 関数は文字列の左端から数えて指定した文字数分の文字を返します。 |
| 半ば | ソース値の部分文字列を返します。 部分文字列は、ソース文字列の一部の文字のみを含む文字列です。 |
| NormalizeDiacritics | 1 つの文字列引数が必要です。 文字列を返しますが、任意の分音文字を等価の非分音文字に置き換えます。 |
| じゃない | ソースのブール値を反転 **します**。 **ソース**値が "*True" の*場合は、"*False"* を返します。 それ以外の場合は、"*True"* を返します。 |
| RemoveDuplicates | RemoveDuplicates 関数は複数値の文字列を受け取り、各値が一意になるように処理します。 |
| 置換 | 文字列内の値を置き換えます。 |
| SelectUniqueValue | 少なくとも 2 つの引数が必要です。これは、式を使用して定義された一意の値生成規則です。 この関数は、各ルールを評価し、生成された値でターゲット アプリ/ディレクトリ内の一意性をチェックします。 |
| 単一のアプリ役割の指定 | 特定のアプリケーションのユーザーに割り当てられているすべての appRoleAssignment の一覧から 1 つの appRoleAssignment を返します。 |
| 割る | 指定した区切り文字を使用して、文字列を複数値配列に分割します。 |
| StringFromSID | StringFromSid 関数は、セキュリティ識別子が含まれるバイト配列を文字列に変換します。 |
| StripSpaces | ソース文字列からすべてのスペース (" ") 文字を削除します。 |
| スイッチ | **ソース**値がキーと一致する場合は、その**キー**の**値**を返**します**。 |
| ToLower | 指定されたカルチャ 規則を使用して *、ソース* 文字列値を受け取り、小文字に変換します。 |
| ToUpper | 指定されたカルチャ ルールを使用して *、ソース* 文字列値を受け取り、大文字に変換します。 |
| 刈る | Trim 関数は、文字列の先頭と末尾の空白文字を削除します。 |
| 言葉 | Word 関数は、使用する区切り記号と返す単語の番号を表すパラメーターに基づいて、文字列内に含まれる単語を返します。 |

#### 追加

**機能：** Append(source, suffix)

**説明:** ソース文字列値を受け取り、サフィックスを末尾に追加します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常、ソース オブジェクトの属性の名前。 |
| **接尾辞** | 必須 | 糸 | ソース値の末尾に追加する文字列。 |

#### BitAnd

**説明:** BitAnd 関数は、値に指定のビットを設定します。

**構文 :**`num BitAnd(num value1, num value2)`

- value1, value2: ともに AND になる数値

**備考:** この関数は両方のパラメーターをバイナリ表現に変換し、ビットを次に設定します。

- 0 - *value1* と *value2* 内の対応するビットの 1 つまたは両方が 0 の場合
- 1 - 対応するビットの両方が 1 の場合。

つまり、両方のパラメーターの対応するビットが 1 の場合を除くすべてのケースで 0 を返します。

**例:**

`BitAnd(&HF, &HF7)` 16 進数の "F" と "F7" がこの値に評価されるため、7 を返します。

#### CBool

**説明:** CBool 関数は、式の評価結果に基づいてブール値を返します。

**構文 :**`bool CBool(exp Expression)`

**備考:** 式の評価結果が 0 以外の値の場合は CBool によって True が返され、それ以外の場合は False が返されます。

**例:**`CBool([attrib1] = [attrib2])`

両方の属性が同じ値を持つ場合は、True を返します。

#### ConvertFromBase64

**説明:** ConvertFromBase64 関数は、指定した base64 でエンコードされた値を正規の文字列に変換します。

**構文 :**`str ConvertFromBase64(str source)` - エンコードには Unicode を想定しています`str ConvertFromBase64(str source, enum Encoding)`

- source:Base64 でエンコードされた文字列
- Encoding:Unicode、ASCII、UTF8

**例**`ConvertFromBase64("SABlAGwAbABvACAAdwBvAHIAbABkACEA")``ConvertFromBase64("SGVsbG8gd29ybGQh", UTF8)`

どちらの例でも "*Hello world!* " を返します。

#### ConvertToBase64

**説明:** ConvertToBase64 関数は、文字列を Unicode の base64 文字列に変換します。 整数の配列の値を、base 64 桁でエンコードされているそれと同等の文字列表現に変換します。

**構文 :**`str ConvertToBase64(str source)`

**例:**`ConvertToBase64("Hello world!")` "SABlAGwAbABvACAAdwBvAHIAbABkACEA" を返します。

#### ConvertToUTF8Hex

**説明:** ConvertToUTF8Hex 関数は、文字列を UTF8 の 16 進数でエンコードされた値に変換します。

**構文 :**`str ConvertToUTF8Hex(str source)`

**備考:** この関数の出力形式は、DN 属性の形式として Microsoft Entra ID で使用されます。

**例:**`ConvertToUTF8Hex("Hello world!")` 48656C6C6F20776F726C6421 を返します。

#### 数える

**説明:** Count 関数は、複数値の属性内の要素数を返します。

**構文 :**`num Count(mvstr attribute)`

#### CStr

**説明:** CStr 関数は、文字列データ型に変換します。

**構文 :**`str CStr(num value)``str CStr(ref value)``str CStr(bool value)`

- value: 数値、参照属性、ブール値を指定できます。

**例:**`CStr([dn])` "cn=Joe,dc=contoso,dc=com" を返します。

#### DateFromNum

**説明:** DateFromNum 関数は、AD の日付形式の値を DateTime 型に変換します。

**構文 :**`dt DateFromNum(num value)`

**例:**`DateFromNum([lastLogonTimestamp])``DateFromNum(129699324000000000)` 2012-01-01 23:00:00 を表す DateTime を返します。

#### DNComponent

**説明:** DNComponent 関数は、指定した DN コンポーネントの値を左から返します。

**構文 :**`str DNComponent(ref dn, num ComponentNumber)`

- dn: 解釈する参照属性
- ComponentNumber:返される DN のコンポーネント

**例:**`DNComponent(CRef([dn]),1)` dn が "cn=Joe,ou=…," の場合は、Joe が返されます。

#### エラー

**説明:** Error 関数は、カスタム エラーを返すために使用します。

**構文 :**`void Error(str ErrorMessage)`

**例:**`IIF(IsPresent([accountName]),[accountName],Error("AccountName is required"))` 属性 accountName が存在しない場合は、オブジェクトでエラーをスローします。

#### 日付と時間のフォーマット

**機能：** FormatDateTime(source, inputFormat, outputFormat)

**説明:** 1 つの形式から日付文字列を受け取り、別の形式に変換します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常、ソース オブジェクトの属性の名前。 |
| **inputFormat** | 必須 | 糸 | ソース値の形式が必要です。 サポートされている形式については、 [/dotnet/standard/base-types/custom-date-and-time-format-strings](https://learn.microsoft.com/ja-jp/dotnet/standard/base-types/custom-date-and-time-format-strings) を参照してください。 |
| **outputFormat** | 必須 | 糸 | 出力日の形式。 |

#### Guid

**説明:** GUID 関数は、新しいランダムな GUID を生成します。

**構文 :**`str Guid()`

#### IIF

**説明:** IIF 関数は、指定した条件に基づいて、使用できる一連の値のうち、いずれかを返します。

**構文 :**`var IIF(exp condition, var valueIfTrue, var valueIfFalse)`

- condition: 評価結果が true または false になる任意の値または式。
- valueIfTrue:条件の評価結果が true の場合に返される値。
- valueIfFalse:条件の評価結果が false の場合に返される値。

**例:**`IIF([employeeType]="Intern","t-" & [alias],[alias])` ユーザーがインターンの場合はユーザーのエイリアスの先頭に "t-" を付けて返し、そうでない場合はユーザーのエイリアスをそのまま返します。

#### InStr（文字列内検索関数）

**説明:** InStr 関数は文字列内の最初の部分文字列を検索します。

**構文 :**

`num InStr(str stringcheck, str stringmatch)``num InStr(str stringcheck, str stringmatch, num start)``num InStr(str stringcheck, str stringmatch, num start, enum compare)`

- stringcheck: 検索対象の文字列
- stringmatch: 検出対象の文字列
- start: 部分文字列の検索開始位置
- compare: vbTextCompare または vbBinaryCompare

**備考:** 部分文字列が見つかった位置を返します。見つからなかった場合は 0 を返します。

**例:**`InStr("The quick brown fox","quick")` 評価結果は 5 になります。

`InStr("repEated","e",3,vbBinaryCompare)` 評価結果は 7 になります。

#### IsNull

**説明:** 式の評価結果が Null の場合、IsNull 関数は true を返します。

**構文 :**`bool IsNull(var Expression)`

**備考:** 属性の場合、Null は属性の不在によって表されます。

**例:**`IsNull([displayName])` CS または MV に属性がない場合は True を返します。

#### IsNullOrEmpty

**説明:** 式が null または空の文字列の場合、IsNullOrEmpty 関数は true を返します。

**構文 :**`bool IsNullOrEmpty(var Expression)`

**備考:** 属性の場合は、属性がないか、存在しても空の文字列の場合、評価結果は True になります。 この関数の逆の関数は IsPresent です。

**例:**`IsNullOrEmpty([displayName])` CS または MV に属性がないか、空の文字列の場合は True を返します。

#### IsPresent

**説明:** 式の評価結果が Null でもなく、空でもない文字列の場合、IsPresent 関数は true を返します。

**構文 :**`bool IsPresent(var expression)`

**備考:** この関数の逆関数は IsNullOrEmpty です。

**例:**`Switch(IsPresent([directManager]),[directManager], IsPresent([skiplevelManager]),[skiplevelManager], IsPresent([director]),[director])`

#### アイテム

**説明:** Item 関数は複数値の文字列/属性から 1 つの項目を返します。

**構文 :**`var Item(mvstr attribute, num index)`

- attribute: 複数値の属性
- index: 複数値の文字列内の項目へのインデックス。

**備考:** Contains 関数は複数値の属性内の項目に対するインデックスを返すため、Item 関数を Contains 関数と共に使用すると便利です。

インデックスが範囲外にある場合は、エラーをスローします。

**例:**`Mid(Item([proxyAddresses],Contains([proxyAddresses], "SMTP:")),6)` プライマリ メール アドレスを返します。

#### IsString

**説明:** 式を文字列型として評価できる場合、IsString 関数の評価結果は True になります。

**構文 :**`bool IsString(var expression)`

**備考:** CStr() が式の解析に成功するかどうかを判断するために使用します。

#### 参加する

**機能：** Join(separator, source1, source2, ...)

**説明:** Join() は Append() に似ていますが、複数の **ソース** 文字列値を 1 つの文字列に結合でき、各値は **区切り文字列** で区切られます。

ソース値の 1 つが複数値属性の場合、その属性内のすべての値が区切り記号の値で区切って結合されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **分離器** | 必須 | 糸 | ソース値を 1 つの文字列に連結するときに、ソース値を区切るために使用される文字列。 区切り記号が必要ない場合は、"" にすることができます。 |
| **source1 ... sourceN** | 必須、可変回数 | 糸 | 結合する文字列値。 |

#### 左

**説明:** Left 関数は文字列の左端から数えて指定した文字数分の文字を返します。

**構文 :**`str Left(str string, num NumChars)`

- string: 返される文字を含む文字列
- NumChars: 文字列の左端から数えて返される文字数を指定する数値

**備考:** string 内の最初の numChars 文字分の文字を含む文字列。

- numChars = 0 の場合、空の文字列を返します。
- numChars &lt; 0 の場合、入力文字列を返します。
- string が null の場合、空の文字列を返します。

string に含まれる文字数が numChars で指定した数より少ない場合は、string と同一の文字列 (パラメーター 1 のすべての文字が含まれる) が返されます。

**例:**`Left("John Doe", 3)``Joh` を返します。

#### 中間

**機能：** Mid(source, start, length)

**説明:** ソース値の部分文字列を返します。 部分文字列は、ソース文字列の一部の文字のみを含む文字列です。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常、属性の名前。 |
| **を開始** | 必須 | 整数 | 部分文字列を開始する **ソース** 文字列内のインデックス。 文字列の最初の文字のインデックスは 1 になり、2 番目の文字はインデックス 2 になります。 |
| **長さ** | 必須 | 整数 | 部分文字列の長さ。 長さが **ソース** 文字列の外側で終わる場合、関数は **開始** インデックスから **ソース** 文字列の末尾まで部分文字列を返します。 |

#### NormalizeDiacritics

**機能：** NormalizeDiacritics(source)

**説明:** 1 つの文字列引数が必要です。 文字列を返しますが、任意の分音文字を等価の非分音文字に置き換えます。 通常、ユーザー プリンシパル名、SAM アカウント名、電子メール アドレスなど、さまざまなユーザー識別子で使用できる有効な値に、分音記号 (アクセント 記号) を含む名と姓を変換するために使用されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常は、名または姓の属性です。 |

#### じゃない

**機能：** Not(source)

**説明:** ソースのブール値を反転 **します**。 **ソース**値が "*True" の*場合は、"*False"* を返します。 それ以外の場合は、"*True"* を返します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | ブール値文字列 | 想定される **ソース** 値は "True" または "False" です。 |

#### RemoveDuplicates

**説明:** RemoveDuplicates 関数は複数値の文字列を受け取り、各値が一意になるように処理します。

**構文 :**`mvstr RemoveDuplicates(mvstr attribute)`

**例:**`RemoveDuplicates([proxyAddresses])` 重複する値がすべて削除された、校正済みの proxyAddress 属性を返します。

#### 取り替える

**機能：** Replace(source, oldValue, regexPattern, regexGroupName, replacementValue, replacementAttributeName, template)

**説明:** 文字列内の値を置き換えます。 これは、指定されたパラメーターによって動作が異なります。

- **oldValue** と **replacementValue** が指定されている場合:

    - **ソース**内のすべての **oldValue を replacementValue** に**置き換**えます。
- **oldValue** と**テンプレート**が指定されている場合:

    - **テンプレート**内のすべての **oldValue** を**ソース**値に置き換えます。
- **regexPattern** と **replacementValue** が指定されている場合:

    - この関数は**、ソース**文字列に **regexPattern** を適用し、正規表現グループ名を使用して **replacementValue** の文字列を構築できます。
- **regexPattern**、**regexGroupName**、**replacementValue** が指定されている場合:

    - この関数は、**regexPattern** を**ソース**文字列に適用し、**regexGroupName** に一致するすべての値を **replacementValue** に置き換えます。
- **regexPattern**、**regexGroupName**、**replacementAttributeName** が指定されている場合:

    - **source** に値がない場合は、**source** が返されます。
    - **source** に値がある場合、関数は **regexPattern** を**ソース**文字列に適用し、**regexGroupName に**一致するすべての値を **replacementAttributeName** に関連付けられた値に置き換えます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常、 **ソース** オブジェクトの属性の名前。 |
| **oldValue** | オプション | 糸 | **ソース**または**テンプレート**で置き換える値。 |
| **regexPattern** | オプション | 糸 | **ソース**で置き換えられる値の正規表現パターン。 または、 **replacementPropertyName** を使用する場合は、 **replacementPropertyName** から値を抽出するパターン。 |
| **regexGroupName** | オプション | 糸 | **regexPattern** 内のグループの名前。 **replacementPropertyName** が使用されている場合にのみ、**replacementPropertyName** から **replacementValue** としてこのグループの値を抽出します。 |
| **replacementValue** | オプション | 糸 | 古いものを置き換える新しい値。 |
| **replacementAttributeName** | オプション | 糸 | 置換値に使用する属性の名前 |
| **テンプレート** | オプション | 糸 | テンプレート値が指定されると、 **テンプレート** 内で **oldValue** を検索し、 **ソース** 値に置き換えます。 |

#### SelectUniqueValue

**機能：** SelectUniqueValue(uniqueValueRule1, uniqueValueRule2, uniqueValueRule3, ...)

**説明:** 少なくとも 2 つの引数が必要です。これは、式を使用して定義された一意の値生成規則です。 この関数は、各ルールを評価し、生成された値でターゲット アプリ/ディレクトリ内の一意性をチェックします。 最初に見つかった一意の値が返されます。 すべての値がターゲットに既に存在する場合、エントリはエスクローされ、理由は監査ログに記録されます。 指定できる引数の数に上限はありません。

注

- これは最上位レベルの関数であり、入れ子にすることはできません。
- この関数は、一致する優先順位を持つ属性には適用できません。
- この関数は、エントリの作成にのみ使用されます。 属性と共に使用する場合は、オブジェクトの作成時に [ **マッピングの適用** ] プロパティを **[のみ]** に設定します。
- この関数は現在、"Workday と SuccessFactors to Active Directory User Provisioning" でのみサポートされています。 他のプロビジョニング アプリケーションでは使用できません。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **uniqueValueRule1 ... uniqueValueRuleN** | 少なくとも 2 つ必要です。上限はありません | 糸 | 評価する一意の値生成ルールの一覧。 |

#### SingleAppRoleAssignment

**機能：** SingleAppRoleAssignment([appRoleAssignments])

**説明:** 特定のアプリケーションのユーザーに割り当てられているすべての appRoleAssignment の一覧から 1 つの appRoleAssignment を返します。 この関数は、appRoleAssignments オブジェクトを 1 つのロール名文字列に変換するために必要です。 ベスト プラクティスは、一度に 1 人の appRoleAssignment のみが 1 人のユーザーに割り当てられるようにすることです。また、複数のロールが割り当てられている場合、返されるロール文字列は予測できない可能性があることに注意してください。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **[appRoleAssignments]** | 必須 | 糸 | **[appRoleAssignments]** オブジェクト。 |

#### 割る

**機能：** Split(source, delimiter)

**説明:** 指定した区切り文字を使用して、文字列を複数値配列に分割します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | **更新するソース** 値。 |
| **デリミタ** | 必須 | 糸 | 文字列の分割に使用する文字を指定します (例: ",") |

#### StringFromSid

**説明:** StringFromSid 関数は、セキュリティ識別子が含まれるバイト配列を文字列に変換します。

**構文 :**`str StringFromSid(bin ObjectSID)`

#### StripSpaces

**機能：** StripSpaces(source)

**説明:** ソース文字列からすべてのスペース (" ") 文字を削除します。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | **更新するソース** 値。 |

#### スイッチ

**機能：** Switch(source, defaultValue, key1, value1, key2, value2, ...)

**説明:** **ソース**値がキーと一致する場合は、その**キー**の**値**を返**します**。 **ソース**値がキーと一致しない場合は、**defaultValue** を返します。 **キー** と **値** のパラメーターは常にペアである必要があります。 この関数は常に偶数のパラメーターを受け取ります。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | チェックする**ソース**値。 |
| **defaultValue** | オプション | 糸 | ソースがキーと一致しない場合に使用される既定値。 空の文字列 ("") を指定できます。 |
| キー **の** | 必須 | 糸 | **ソース**値を比較する**キー**。 |
| **の値** | 必須 | 糸 | キーに一致する **ソース** の置換値。 |

#### ToLower関数

**機能：** ToLower(source, culture)

**説明:** 指定されたカルチャ 規則を使用して *、ソース* 文字列値を受け取り、小文字に変換します。 *カルチャ*情報が指定されていない場合は、インバリアント カルチャが使用されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常、ソース オブジェクトの属性の名前 |
| **文化** | オプション | 糸 | RFC 4646 に基づくカルチャ名の形式は *languagecode2-country/regioncode2* です。 *languagecode2 は 2* 文字の言語コードで、 *country/regioncode2 は 2* 文字のサブカルチャ コードです。 たとえば、日本語 (日本) の場合は ja-JP、英語 (米国) の場合は en-US となります。 2 文字の言語コードを使用できない場合は、ISO 639-2 から派生した 3 文字のコードが使用されます。 |

#### ToUpper

**機能：** ToUpper(source, culture)

**説明:** 指定されたカルチャ ルールを使用して *、ソース* 文字列値を受け取り、大文字に変換します。 *カルチャ*情報が指定されていない場合は、インバリアント カルチャが使用されます。

**パラメーター:**

| 名前 | 必須/繰り返し | タイプ | 注記 |
| --- | --- | --- | --- |
| **ソース** | 必須 | 糸 | 通常、ソース オブジェクトの属性の名前。 |
| **文化** | オプション | 糸 | RFC 4646 に基づくカルチャ名の形式は *languagecode2-country/regioncode2* です。 *languagecode2 は 2* 文字の言語コードで、 *country/regioncode2 は 2* 文字のサブカルチャ コードです。 たとえば、日本語 (日本) の場合は ja-JP、英語 (米国) の場合は en-US となります。 2 文字の言語コードを使用できない場合は、ISO 639-2 から派生した 3 文字のコードが使用されます。 |

#### トリミング

**説明:** Trim 関数は、文字列の先頭と末尾の空白文字を削除します。

**構文 :**`str Trim(str value)`

**例:**`Trim(" Test ")` "test" を返します。

`Trim([proxyAddresses])` proxyAddress 属性の値ごとに先頭と末尾の空白文字を削除します。

#### ワード

**説明:** Word 関数は、使用する区切り記号と返す単語の番号を表すパラメーターに基づいて、文字列内に含まれる単語を返します。

**構文 :**`str Word(str string, num WordNumber, str delimiters)`

- string: 返される単語を含む文字列。
- WordNumber: 返すべき単語の番号を指定する数値。
- delimiters: 単語を識別するために使用される区切り記号を表す文字列

**備考:** delimiters 内のいずれかの文字で区切られた string 内の各文字列が、単語として識別されます。

- 数値 &lt; 1 の場合、空の文字列を返します。
- string が null の場合、空の文字列を返します。

string に含まれる単語の数が指定より少ないか、区切り記号文字で識別されるどの単語も string に含まれていない場合は、空の文字列が返されます。

**例:**`Word("The quick brown fox",3," ")` "brown" を返します。

`Word("This,string!has&many separators",3,",!&#")` "has" を返します。

### 例示

#### 既知のドメイン名を削除する

ユーザー名を取得するには、ユーザーの電子メールから既知のドメイン名を削除する必要があります。  たとえば、ドメインが "contoso.com" の場合は、次の式を使用できます。

**式:**`Replace([mail], "@contoso.com", , ,"", ,)`

**サンプルの入力/出力:**

- **INPUT** (mail): "john.doe@contoso.com"
- **出力**: "john.doe"

#### ユーザー名に定数サフィックスを追加する

Salesforce サンドボックスを使用している場合は、同期する前に、すべてのユーザー名に追加のサフィックスを追加することが必要になる場合があります。

**式:**`Append([userPrincipalName], ".test")`

**サンプルの入力/出力:**

- **INPUT**: (userPrincipalName): "John.Doe@contoso.com"
- **出力**: "John.Doe@contoso.com.test"

#### 姓と名の一部を連結してユーザー エイリアスを生成する

ユーザーの名の最初の 3 文字とユーザーの姓の最初の 5 文字を使用して、ユーザー エイリアスを生成する必要があります。

**式:**`Append(Mid([givenName], 1, 3), Mid([surname], 1, 5))`

**サンプルの入力/出力:**

- **INPUT** (givenName): "John"
- **INPUT** (姓): "Doe"
- **出力**: "JohDoe"

#### 文字列から分音記号を削除する

アクセント 記号を含む文字を、アクセント 記号を含まない同等の文字に置き換える必要があります。

**式:** NormalizeDiacritics([givenName])

**サンプルの入力/出力:**

- **INPUT** (givenName): "Zoë"
- **出力**: "Zoe"

#### 文字列を複数値配列に分割する

文字列のコンマ区切りのリストを取得し、Salesforce の PermissionSets 属性のような複数値属性に接続できる配列に分割する必要があります。 この例では、Microsoft Entra ID の extensionAttribute5 にアクセス許可セットの一覧が設定されています。

**式:** Split([extensionAttribute5], ",")

**サンプルの入力/出力:**

- **INPUT** (extensionAttribute5): "PermissionSetOne, PermissionSetTwo"
- **OUTPUT**: ["PermissionSetOne", "PermissionSetTwo"]

#### 特定の形式の文字列として日付を出力する

特定の形式で SaaS アプリケーションに日付を送信する場合。  たとえば、ServiceNow の日付を書式設定します。

**式:**

`FormatDateTime([extensionAttribute1], "yyyyMMddHHmmss.fZ", "yyyy-MM-dd")`

**サンプルの入力/出力:**

- **INPUT** (extensionAttribute1): "20150123105347.1Z"
- **出力**: "2015-01-23"

#### 定義済みの一連のオプションに基づいて値を置き換える

Microsoft Entra ID に格納されている状態コードに基づいて、ユーザーのタイム ゾーンを定義する必要があります。  状態コードが定義済みのオプションのいずれとも一致しない場合は、既定値の "Australia/Sydney" を使用します。

**式:**`Switch([state], "Australia/Sydney", "NSW", "Australia/Sydney","QLD", "Australia/Brisbane", "SA", "Australia/Adelaide")`

**サンプルの入力/出力:**

- **INPUT** (状態): "QLD"
- **出力**: "Australia/Brisbane"

#### 正規表現を使用して文字を置換する

正規表現の値に一致する文字を見つけて削除する必要があります。

**式:**

Replace([mailNickname], , "[a-zA-Z\_]\*", "", , )

**サンプルの入力/出力:**

- **INPUT** (mailNickname: "john\_doe72"
- **出力**: "72"

#### 生成された userPrincipalName (UPN) 値を小文字に変換する

次の例では、PreferredFirstName ソース フィールドと PreferredLastName ソース フィールドを連結することで UPN 値が生成され、ToLower 関数は生成された文字列を操作してすべての文字を小文字に変換します。

`ToLower(Join("@", NormalizeDiacritics(StripSpaces(Join(".",  [PreferredFirstName], [PreferredLastName]))), "contoso.com"))`

**サンプルの入力/出力:**

- **INPUT** (PreferredFirstName): "John"
- **INPUT** (PreferredLastName): "Smith"
- **出力**: "john.smith@contoso.com"

#### userPrincipalName (UPN) 属性の一意の値を生成する

ユーザーの名、ミドル ネーム、姓に基づいて、UPN 属性の値を生成し、その値を UPN 属性に割り当てる前に、ターゲット AD ディレクトリでその一意性を確認する必要があります。

**式:**

```ad
    SelectUniqueValue( 
        Join("@", NormalizeDiacritics(StripSpaces(Join(".",  [PreferredFirstName], [PreferredLastName]))), "contoso.com"), 
        Join("@", NormalizeDiacritics(StripSpaces(Join(".",  Mid([PreferredFirstName], 1, 1), [PreferredLastName]))), "contoso.com"),
        Join("@", NormalizeDiacritics(StripSpaces(Join(".",  Mid([PreferredFirstName], 1, 2), [PreferredLastName]))), "contoso.com")
    )
```

**サンプルの入力/出力:**

- **INPUT** (PreferredFirstName): "John"
- **INPUT** (PreferredLastName): "Smith"
- **OUTPUT**: John.Smith@contoso.comの UPN 値がまだディレクトリに存在しない場合は "John.Smith@contoso.com"
- **OUTPUT**: J.Smith@contoso.comの UPN 値がディレクトリに既に存在する場合は "John.Smith@contoso.com"
- **OUTPUT**: "Jo.Smith@contoso.com" (上記の 2 つの UPN 値がディレクトリに既に存在する場合)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/reference-powershell"} -->
## Microsoft Entra Cloud Sync 用の AADCloudSyncTools PowerShell モジュール - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-powershell
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-21
- Summary: この記事では、Microsoft Entra Connect クラウド プロビジョニング エージェントをインストールする方法について説明します。

AADCloudSyncTools モジュールには、Microsoft Entra Cloud Sync のデプロイを管理するのに役立つ便利なツールのセットが用意されています。

### [前提条件]

`Install-AADCloudSyncToolsPrerequisites`を使用して、AADCloudSyncTools モジュールのすべての前提条件を自動的にインストールできます。 これは、この記事の次のセクションで行います。

必要な内容の詳細を次に示します。

- AADCloudSyncTools モジュールは Microsoft Authentication Library (MSAL) 認証を使用するため、MSAL.PS モジュールをインストールする必要があります。 インストールを確認するには、PowerShell ウィンドウで `Get-module MSAL.PS -ListAvailable`を実行します。 モジュールが正しくインストールされている場合は、応答が返されます。 必要に応じて、 `Install-AADCloudSyncToolsPrerequisites` を使用して最新バージョンの MSAL.PS をインストールできます。
- AADCloudSyncTools モジュールの機能に [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) モジュールは必要ありませんが、役に立ちます。 そのため、 `Install-AADCloudSyncToolsPrerequisites`を使用すると自動的にインストールされます。
- PowerShell ギャラリーからモジュールをインストールするには、トランスポート層セキュリティ (TLS) 1.2 の適用が必要です。 コマンドレット `Install-AADCloudSyncToolsPrerequisites` は、すべての前提条件をインストールする前に TLS 1.2 の適用を設定します。 モジュールを手動でインストールできるようにするには、コマンドレットを使用する前に、PowerShell セッションで以下を設定します。

    ```
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12 
    ```
- Microsoft Entra Connect クラウド プロビジョニング エージェントが実行されていないか、構成ウィザードが正常に完了していない場合、AADCloudSyncTools モジュールが正しく動作しない可能性があります。

### AADCloudSyncTools PowerShell モジュールをインストールする

1. 管理者特権で Windows PowerShell を開きます。
2. `Import-module -Name "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\Utility\AADCloudSyncTools"` を実行します。
3. モジュールがインポートされたことを確認するには、 `Get-module AADCloudSyncTools`実行します。

    今、モジュールに関する情報が表示されるはずです。
4. AADCloudSyncTools モジュールの前提条件をインストールするには、 `Install-AADCloudSyncToolsPrerequisites`実行します。
5. 最初の実行時に、PowerShellGet モジュールが存在しない場合はインストールされます。 新しい PowerShellGet モジュールを読み込むには、PowerShell ウィンドウを閉じて、管理特権で新しい PowerShell セッションを開きます。
6. `Import-module -Name "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\Utility\AADCloudSyncTools"`を実行して、モジュールをもう一度インポートします。
7. `Install-AADCloudSyncToolsPrerequisites`をもう一度実行して、MSAL モジュールと Microsoft Graph PowerShell モジュールをインストールします。

    すべての前提条件をインストールする必要があります。

    [Image: 前提条件が正常にインストールされたことを示す PowerShell ウィンドウの通知のスクリーンショット。]
8. 新しい PowerShell セッションで AADCloudSyncTools モジュールを使用するたびに、次のコマンドを実行します。

    ```
    Import-module "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\Utility\AADCloudSyncTools"
    ```

### AADCloudSyncTools コマンドレット

注

AADCloudSyncTools モジュールを使用する前に、Microsoft Entra Connect クラウド プロビジョニング エージェントが実行されており、構成ウィザードが正常に完了していることを確認してください。 ウィザードの問題をトラブルシューティングするには、 *C:\ProgramData\Microsoft\Azure AD Connect Provisioning Agent\Trace* フォルダーにトレース ログがあります。詳細については、「 [クラウド同期のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-troubleshoot) 」を参照してください。

#### Connect-AADCloudSyncTools

このコマンドレットでは、MSAL.PS モジュールを使用して、Microsoft Entra 管理者が Microsoft Graph にアクセスするためのトークンを要求します。

#### Export-AADCloudSyncToolsLogs

このコマンドレットは、次のように、すべてのトラブルシューティング データを圧縮ファイルにエクスポートしてパッケージ化します。

1. 詳細トレースを設定し、プロビジョニング エージェントからのデータの収集を開始します ( `Start-AADCloudSyncToolsVerboseLogs`と同じです)。
2. 3 分後にデータ収集を停止し、詳細トレースを無効にします ( `Stop-AADCloudSyncToolsVerboseLogs`と同じです)。
3. 過去 24 時間のイベント ビューアー ログを収集します。
4. すべてのエージェント ログ、詳細ログ、およびイベント ビューアー のログを、ユーザーの *ドキュメント* フォルダー内の .zip ファイルに圧縮します。

次のオプションを使用して、データ収集を微調整できます。

- 詳細ログをキャプチャせずに現在のログのみをエクスポートするように `SkipVerboseTrace` します (既定値は false)。
- 別のキャプチャ期間 (既定値は 3 分) を指定 `TracingDurationMins`。
- 別の出力パスを指定する `OutputPath` します (既定値はユーザーのドキュメント フォルダー)。

#### Get-AADCloudSyncToolsInfo

このコマンドレットは、Microsoft Entra テナントの詳細と内部変数の状態を示します。

#### Get-AADCloudSyncToolsJob

このコマンドレットは、Microsoft Graph を使用して Microsoft Entra サービス プリンシパルを取得し、同期ジョブの情報を返します。 特定の同期ジョブ ID をパラメーターとして使用して呼び出すこともできます。

#### Get-AADCloudSyncToolsJobSchedule

このコマンドレットは、Microsoft Graph を使用して Microsoft Entra サービス プリンシパルを取得し、同期ジョブのスケジュールを返します。 特定の同期ジョブ ID をパラメーターとして使用して呼び出すこともできます。

#### Get-AADCloudSyncToolsJobSchema

このコマンドレットは、Microsoft Graph を使用して Microsoft Entra サービス プリンシパルを取得し、同期ジョブのスキーマを返します。

#### Get-AADCloudSyncToolsJobScope

このコマンドレットは、Microsoft Graph を使用して、指定された同期ジョブ ID の同期ジョブのスキーマを取得し、すべてのフィルター グループのスコープを出力します。

#### Get-AADCloudSyncToolsJobSettings

このコマンドレットは、Microsoft Graph を使用して Microsoft Entra サービス プリンシパルを取得し、同期ジョブの設定を返します。 特定の同期ジョブ ID をパラメーターとして使用して呼び出すこともできます。

#### Get-AADCloudSyncToolsJobStatus

このコマンドレットは、Microsoft Graph を使用して Microsoft Entra サービス プリンシパルを取得し、同期ジョブの状態を返します。 特定の同期ジョブ ID をパラメーターとして使用して呼び出すこともできます。

#### Get-AADCloudSyncToolsServicePrincipal

このコマンドレットでは、Microsoft Graph を使用して、Microsoft Entra ID または Azure Service Fabric のサービス プリンシパルを取得します。 パラメーターがないと、Microsoft Entra サービス プリンシパルのみが返されます。

#### Install-AADCloudSyncToolsPrerequisites

このコマンドレットは、PowerShellGet v2.2.4.1 以降、Microsoft Graph PowerShell モジュール、および MSAL.PS モジュールの有無を確認します。 これらの項目が見つからない場合は、インストールされます。

#### Invoke-AADCloudSyncToolsGraphQuery

このコマンドレットは、パラメーターとして指定された URI、メソッド、および本文に対する Web 要求を呼び出します。

#### Restart-AADCloudSyncToolsJob

このコマンドレットは、完全同期を再起動します。

#### Resume-AADCloudSyncToolsJob

このコマンドレットは、前の基準値からの同期を続行します。

#### Start-AADCloudSyncToolsVerboseLogs

このコマンドレットは、詳細トレースを有効にするように *AADConnectProvisioningAgent.exe.config* を変更し、AADConnectProvisioningAgent サービスを再起動します。 `-SkipServiceRestart`を使用してサービスの再起動を防ぐことができますが、構成の変更は有効になりません。 これらのトレース ログは *、C:\ProgramData\Microsoft\Azure AD Connect プロビジョニング エージェント\Trace* フォルダーにあります。

#### Stop-AADCloudSyncToolsVerboseLogs

このコマンドレットは、詳細なトレースを無効化し、*AADConnectProvisioningAgent.exe.config* を変更して、AADConnectProvisioningAgent サービスを再起動します。 `-SkipServiceRestart`を使用してサービスの再起動を防ぐことができますが、構成の変更は有効になりません。

#### Suspend-AADCloudSyncToolsJob

このコマンドレットは同期を一時停止します。

#### Disable-AADCloudSyncToolsDirSyncAccidentalDeletionPrevention

accidentalDeletionPrevention テナント機能を無効にします

```powershell
Disable-AADCloudSyncToolsDirSyncAccidentalDeletionPrevention -tenantId <TenantId>
```

このコマンドレットには、Microsoft Entra テナントの `TenantId` が必要です。 Microsoft Entra Connect (クラウド同期ではなく ADSync) を使用してテナントに設定されている誤削除防止機能が有効になっているかどうかを確認し、無効にします。

##### 例：

```powershell
Disable-AADCloudSyncToolsDirSyncAccidentalDeletionPrevention -tenantId "aaaabbbb-0000-cccc-1111-dddd2222eeee"
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/reference-provision-to-active-directory-faq"} -->
## Microsoft Entra Cloud Sync による Active Directory へのプロビジョニング FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-provision-to-active-directory-faq
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-19
- Summary: このドキュメントでは、クラウド同期に関してよく寄せられる質問について説明します。

Microsoft Entra Cloud Sync で Active Directory にプロビジョニングする方法に関する FAQ をお読みください。

### Active Directoryへのユーザーのプロビジョニング

#### ターゲット コンテナーを変更した後も、プロビジョニングされたユーザーが元の組織単位に戻り続けるのはなぜですか?

この動作は想定内です。 既定の `parentDistinguishedName` 式では、その属性が設定されるときにユーザーの `onPremisesDistinguishedName` 属性が使用され、空の場合にのみ定数ターゲット コンテナーにフォールバックします。 クラウドは権限のソースであるため、クラウド オブジェクトに記録されている場所が優先されるため、ターゲット コンテナーを変更しても既にプロビジョニングされているユーザーは移動せず、Active Directoryでオブジェクトを手動で移動することは、次の同期サイクルで元に戻されます。

ユーザーを移動するには、ターゲット コンテナー マッピング (マッピング ルールに一致するすべてのユーザーを移動) を更新するか、Microsoft Entra IDでそのユーザーの`onPremisesDistinguishedName`属性を更新します。 手順については、「 [プロビジョニングされたユーザーを別の組織単位に移動する」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#move-a-provisioned-user-to-a-different-organizational-unit)参照してください。

### Active Directory へのグループのプロビジョニング

#### Microsoft Entra Cloud Sync での AD へのグループ プロビジョニングは、他の Microsoft Entra Connect Sync 機能と横並びで機能しますか?

はい。Ad へのセキュリティ グループ のプロビジョニングにのみ Microsoft Entra Cloud Sync を使用でき、同時に Connect Sync を使用して AD を Microsoft Entra ID に同期できます。 Microsoft Entra Connect Sync 経由で AD から同期されたユーザーを含むクラウド セキュリティ グループは、Microsoft Entra Cloud Sync と AD へのグループ プロビジョニングを使用して Active Directory にプロビジョニングできます。

たとえば、Active Directory Domain Services ユーザーであり、Microsoft Entra Connect Sync と Microsoft Entra ID に同期されている 2 人のユーザー (ユーザー A とユーザー B) が存在する場合は、SecurityGroup A という名前の Microsoft Entra ID にクラウド セキュリティ グループを作成できます。このグループは、[Microsoft Entra Cloud Sync - Group Provisioning to Active Directory](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/group-writeback-cloud-sync)を使用して AD DS にプロビジョニングし直すことができます。

#### Microsoft Entra Connect Sync のグループ書き戻し機能を使用して AD にプロビジョニングする Microsoft 365 グループがあります。これは引き続き機能しますか?

はい。Connect Sync 構成からグループ書き戻し V2 をアンインストールするか、無効にすると、既定でグループ書き戻し V1 になります。 この既定では、Microsoft Entra ID ですべての Microsoft 365 グループを書き戻すことができます。

#### グループ書き戻し V1 も無効にする場合はどうすればよいですか?

グループの書き戻し V1 を無効にすると、次の完全同期で、Microsoft Entra Connect 同期によって AD に書き込まれるすべてのグループが削除されます。 Microsoft Entra Cloud Sync を使用してプロビジョニングされたクラウド セキュリティ グループがこの操作の影響を受けることはありません。

#### Microsoft Entra Cloud Sync を使用して AD にプロビジョニングするために範囲内でグループを設定する目的で、MS Graph および Microsoft Entra 管理センターから "グループ書き戻し" フィールドを引き続き使用することはできますか?

いいえ。Cloud Sync を使用して AD にプロビジョニングされるグループの範囲を決定する目的ではこのフィールドは現在使用されていません。範囲を設定するには、ポータルで Microsoft Entra Cloud Sync 構成エクスペリエンスを使用する必要があります。 詳細については、「[Active Directoryへのプロビジョニング時にディレクトリ拡張機能を使用する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-directory-extension-group-provisioning)」を参照してください。

#### Microsoft Entra Connect 同期のグループの書き戻し V2 から Microsoft Entra クラウド同期への移行で説明されている手順に従った場合、Microsoft Entra Connect での Active Directory から Microsoft Entra ID への同期に影響しますか?

いいえ。グループの書き戻し V2 から Microsoft Entra クラウド同期に移動する移行手順に従うと、AD と Microsoft Entra ID の間の同期には影響しません。

### AD ユーザーおよびグループの適用 (プレビュー)

#### AD 強制でサポートされているオブジェクトの種類は何ですか?

このプレビューでは、ユーザーとグループがサポートされています。

#### プロビジョニング エージェントをインストールすると、すべてのActive Directory オブジェクトに対して強制が有効になりますか?

No. ポリシーのインストールに加えて、各ユーザーまたはグループを適用対象としてマークする必要があります。 保護するオブジェクトに対して`msDS-ObjectSoa`属性を設定するように、Active Directoryへのプロビジョニングで該当するユーザーまたはグループ属性のマッピングを構成します。 属性セットのないオブジェクトは適用されません。

#### 保護されたユーザーまたはグループに緊急変更を行うための緊急用アカウントを定義できますか?

Yes. 承認されたユーザーまたはグループのセキュリティ識別子 (SID) をポリシーに追加して、プロビジョニング サービスが利用できないときにアカウントが適用されたオブジェクトを変更できるようにします。 詳細については、「 [AD ユーザーとグループの適用を構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-active-directory-object-enforcement#break-glass-accounts)」を参照してください。

#### グループ A が適用対象としてマークされている場合、適用対象としてマークされていないグループ B のメンバーとして追加できますか?

Yes. グループ A は、グループ B のメンバーとして追加できます。ただし、グループ A のメンバーに対する変更は、プロビジョニング サービスによってのみ行うことができます。

#### AD オブジェクトの適用が有効になっていないドメイン コントローラーで変更が行われた場合はどうなりますか?

変更が処理されます。 ドメイン全体で完全に適用するには、すべてのドメイン コントローラーで、累積的なWindows Server更新プログラムと一致するグループ ポリシー MSI をインストールするか、機能が既に有効になっているWindows Server Insider Preview ビルドを実行して、機能を有効にする必要があります。

#### この機能によって、ロールベースのアクセス制御 (RBAC) モデルActive Directoryはどのように変更されますか?

AD オブジェクトの適用は、既存の RBAC モデルに追加されます。 ユーザーに追加のアクセス権を与えることなく、既存のロールの割り当ての上に別の制限を課します。

#### ポリシーを有効にするために必要なロールは何ですか?

ドメイン管理者は、ポリシーをインストールする PowerShell スクリプトを実行するために必要です。 Microsoft Entra Cloud Sync プロビジョニング エージェントがインストールされているコンピューターでスクリプトを実行します。

#### イベント ログに強制イベントを表示するにはどうすればよいですか?

監査モードのイベントを表示するには、PDCe のセキュリティ診断レジストリの値を **1** に設定します。 監査された変更は、そのドメイン コントローラーのディレクトリ サービス イベント ログに表示されます。 詳細については、 [AD および LDS 診断イベントのログ記録](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/active-directory/configure-ad-and-lds-event-logging)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/reference-version-history"} -->
## Microsoft Entra プロビジョニング エージェント: バージョンのリリース履歴 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history
- Service: entra-id / app-provisioning
- Article date: 2025-09-23
- Summary: この記事では、Microsoft Entra プロビジョニング エージェントのすべてのリリースの一覧を示し、新機能と修正された問題について説明します

この記事では、プロビジョニング エージェント リリースのバージョンと機能Microsoft Entra一覧表示します。 Microsoft Entra チームは、新しい機能を使用してプロビジョニング エージェントを定期的に更新します。

注

すべての新しいプロビジョニング エージェント リリースは、Microsoft Entra 管理センターを通じてダウンロード可能になり、自動アップグレードのために特定のリリースのみがプッシュされます。

注

プロビジョニング エージェントMicrosoft Entra、[Modern ライフサイクル ポリシー](https://learn.microsoft.com/ja-jp/lifecycle/policies/modern)に従います。 モダン ライフサイクル ポリシーに基づく製品とサービスの変更は、より頻繁に行われる可能性があり、顧客に対して製品またはサービスに対する今後の変更に関する通知が必要です。

モダン ポリシーによって管理される製品は、[継続的なサポートとサービス モデル](https://learn.microsoft.com/ja-jp/lifecycle/overview/product-end-of-support-overview)に従います。 サポートを維持するには、最新の更新プログラムを使用する必要があります。

モダン ライフサイクル ポリシーに準拠する製品とサービスの場合、Microsoftのポリシーは、製品またはサービスの通常の使用が大幅に低下しないように、お客様がアクションを実行する必要がある場合に、少なくとも 30 日間の通知を提供することです。

### ダウンロード リンク

[Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)から **Cloud Sync** を選択し、**Agents** に移動して、**プロビジョニング エージェント**をダウンロードします。

URL `https://aka.ms/cloudsyncrss` をコピーして、お使いの [Image: RSS フィード リーダー アイコン] フィード リーダーに貼り付け、更新内容を確認するためにこのページに再度アクセスするタイミングに関する通知を受け取るようにしてください。

### 1.1.2505.0

**リリース日:** 2026 年 9 月 21 日

#### 新機能と機能強化

- Azure China Cloud のダウンロード機能のサポートが追加されました。
- 顧客が gMSA を提供し、カスタム gMSA アクセス許可を選択するときに、ドメイン管理者の資格情報の要件を排除することで、プロビジョニング エージェントの構成を簡略化しました。 この拡張機能は、ハイブリッド クラウド同期シナリオをサポートし、gMSA PowerShell コマンドレット のエクスペリエンスに合わせて調整されます。
- Repair-AADCloudSyncToolsAccount コマンドレットを削除し、モジュールを更新されたプロビジョニング エージェント構成エクスペリエンスに合わせて調整することで、AADCloudSyncTools PowerShell モジュールを合理化しました。

#### 修正

- ドメイン サフィックスを使用するAzure Government テナントのクラウド環境検出が改善されました。
- 初期同期中にセキュリティ グループに影響する書式設定の不整合を解決しました。
- AD2AADProvisioning ジョブで、未解決のクロスドメイン ディレクトリ参照によってオブジェクト レベルの検疫エラーが発生する可能性がある問題を修正しました。
- 入れ子になった組織単位とコンテナーを処理するときのドメイン コントローラー アフィニティページングの問題を修正しました。

#### セキュリティ強化

- ハイブリッド ID 同期デプロイの信頼性と保護をさらに強化するために、セキュリティ強化の強化が追加されました。

### 1.1.2334.0

2026 年 4 月 14 日: ダウンロード用にリリース

2026 年 5 月 5 日: 自動アップグレード用にリリース

#### 新機能または変更された機能

- ECMA コネクタを使用するオンプレミス アプリケーションで[アカウント検出](https://aka.ms/accountDiscoveryDocumentation)がサポートされるようになりました。
- `Add-AADCloudSyncADDomain` PowerShell コマンドレットは、カスタム グループのマネージド サービス アカウント (gMSA) に対する追加のActive Directoryアクセス許可を設定しなくなりました。 管理者は、gMSA [PowerShell コマンドレット](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets)に記載されている、カスタム gMSA に適切なアクセス許可があることを確認する責任を負うようになりました。

#### 修正された問題

- プロビジョニング エージェント サービスが正常にシャットダウンできず、再起動とアップグレードに影響する問題を修正しました。

### 1.1.2108.0

2025年12月4日:ダウンロードのみリリース

#### 修正された問題

バージョン 1.1.2102.0 で AzureUSGovernment クラウドのプロビジョニング エージェントMicrosoft Entra使用してパスワードを書き戻すときにエラーが発生する既知の問題を修正しました。

### 1.1.2102.0

2025 年 9 月 22 日: ダウンロードのみリリース

#### 修正された問題

- サービスのタイムアウトを防ぐことにより、エージェントのインストールとアップグレードの信頼性が向上しました
- エージェントのインストール中にWindows資格情報マネージャーを有効にする方法の合理化
- ハイブリッド管理者ロールの前提条件のチェックを追加し、資格のある認証済みユーザーのロールをアクティブ化しました
- プロビジョニング シナリオを選択する方法の簡略化
- 既定で現在のクラウド環境用に構成されたインストール
- サービスのタイムアウトを防ぐことで、大規模なグループの列挙を高速化する
- 管理者がドメイン コントローラーの優先度リストを表示する方法が改善されました
- ドメイン間 ID 管理 (SCIM) プロパティのシステムとして UserAccountControl を追加しました
- 新しく作成されたユーザーの構成可能なパスワードの長さを追加しました。 新しく作成されたユーザーの既定のパスワードの長さは 256 です。 管理者は、新しく作成されたユーザーに対して 127 ~ 256 の整数値を設定できます。この値は、次のデータ型REG\_DWORDレジストリ エントリを作成します。

    `HKLM\Software\Microsoft\Azure AD Connect Agents\Azure AD Connect Provisioning Agent\UserPasswordLength`

#### 既知の問題

- AzueUSGovernment のお客様で、Microsoft Entra プロビジョニング エージェントでパスワードの書き戻しを有効にしている場合、操作が失敗する可能性があります。 この問題に対処するためにエージェントをバージョン1.1.2108.0にアップグレードしてください。

### 1.1.1586.0

2024 年 5 月 13 日: ダウンロードのみリリース

#### 修正された問題

- その他のサポート性の向上。
- Active Directory プロバイダーの初期化に関する問題の処理が改善されました。
- 属性削除のバグを修正しました。
- グループ書き戻しのエラー処理を改善しました。

### 1.1.1373.0

2024 年 1 月 19 日: ダウンロードと自動アップグレードのリリース

#### 修正された問題

ドメイン名の大文字と小文字を区別する比較に関する問題を修正し、エラー処理を強化しました。

### 1.1.1370.0

リリース日: 2023 年 10 月 16 日

#### 新機能または変更された機能

- パブリック プレビュー機能を追加しました: Group Active Directory
- Microsoft Entraのエージェントのブランド変更

### 1.1.1365.0

2023 年 9 月 8 日: ダウンロードのみリリース

#### 新機能または変更された機能

- プロビジョニング エージェントへのアプリケーションの Kerberos キー同期のサポートを追加しました
- Web サービス、Windows PowerShell、カスタム ECMA2 コネクタのサポートを追加しました
- ECMA2Host の**クエリ属性**は使用されなくなり、ECMA2 構成ウィザードから削除されました
- DirSyncConfiguration の誤削除防止を無効にするための関数で AADCloudSyncTools モジュールを更新しました

#### 修正された問題

- OR 名属性値が存在しないオブジェクトを指している場合に検疫に入るジョブの問題を修正しました
- エージェントが更新されると AADConnectProvisioningAgent.exe.config のカスタマイズがリセットされる問題を修正しました
- NTLM トラフィックが 'Deny-All' に設定されている場合のプロビジョニング エージェントの誤った資格情報を修正しました
- トレース ログへの誤ったファイル パスを修正しました
- スキーマ オブジェクトの重複が原因でプロビジョニング エージェント インストーラーがクラッシュする問題を修正しました
- 文書化された前提条件に従って更新Windows Serverバージョンチェック
- 'ハイコントラスト 黒' モードでのインストール ページ リンクの表示を修正しました
- 大文字と小文字を区別する比較を修正し、UPN/ロール ID チェックのログ記録を追加しました
- ECMA2 構成ウィザードのアクセシビリティの修正

### 1.1.1107.0

2022 年 12 月 16 日: ダウンロードのみリリース

#### 新機能または変更された機能

- [オンプレミス アプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/azure/active-directory/app-provisioning/on-premises-application-provisioning-architecture) (SCIM、SQL、LDAP) のサポートを追加しました

### 1.1.977.0

2022 年 9 月 23 日: ダウンロードのみリリース

#### 新機能または変更された機能

- [クラウド同期のセルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-cloud-sync-sspr-writeback) 一般提供のサポートが追加されました。
- 切断されたフォレストでのパスワード ライトバックのサポートを追加しました。

#### 修正された問題

- クラウド同期で SSPR をサポートするためのさまざまなバグ修正を修正しました

### 1.1.972.0

2022 年 8 月 8 日: ダウンロードのみリリース

#### 新機能または変更された機能

- パスワードの書き戻しを有効または無効にする新しいコマンドレットを追加しました。 このコマンドレットとその使用方法の詳細については、「 クラウド同期。
- 'Get-AADCloudSyncDomains' コマンドレットから詳細情報が返されるようになりました
- 無人エージェント インストール スクリプトの CloudSync PowerShell モジュールの新しいコマンドレットを更新しました。
- コマンド ラインを使用したプロビジョニング エージェントのインストールのサポートを追加しました。
- EX 環境と RX 環境のサポートを追加しました。

#### 修正された問題

- エージェントのアップグレード時に app.config ファイルを削除します。 Newtonsoft.Json のアップグレード後、AADConnectProvisioningAgent.exe.config はインストール後に更新されないため、同期に失敗します。
- OU の名前が変更された後の DC アフィニティに関する問題を修正しました。
- PowerShell モジュールのいくつかの問題を修正しました。
- HTTP クライアントを破棄しないことによるメモリ リークを修正しました。
- GMSA に "サービスとしてのログオン" 権限を付与するコードのバグを修正しました。
- GMSA for CloudHR のアクセス許可を調整しました。
- バンドルがアンインストールされると、クラウド同期エージェントがアンインストールされるようになりました。
- すべてのジョブが削除されない場合にサービス プリンシパルを削除できないバグを修正しました。
- "ユーザーは次回ログオン時にパスワードを変更する必要があります" というユーザーのパスワードの更新に関する問題を修正しました。
- エージェントの GMSA フォルダーのアクセス許可に関する問題を修正しました。
- グループ メンバーシップの更新が常に正しいとは限らない問題を修正しました。

### 1.1.818.0

2022 年 4 月 18 日: ダウンロードのみリリース

新機能と機能強化

- gMSA へのサービスに対するログオン権限の付与が失敗するバグを修正しました。
- エージェントが更新され、セルフサービス パスワード リセット機能にも使用されるようにするため、エージェントで構成されている優先ドメイン コントローラーの一覧が優先されます。

### 1.1.587.0

2021 年 11 月 2 日: ダウンロードのみリリース

新機能と機能強化

- パスワード ライトバックを構成するコマンドレットを追加しました

### 1.1.584.0

2021 年 8 月 20 日: ダウンロードのみリリース

#### 修正された問題

- ドメインの名前が変更されると、パスワード ハッシュ同期が失敗し、イベント ログに "指定したキャストが無効です" というエラーが表示されるバグを修正しました。 このエラーは、以前のビルドからの回帰です。

### 1.1.582.0

2021 年 8 月 8 日: ダウンロードのみリリース

注

これは、Azure AD Connect のセキュリティ更新プログラムリリースです。 このリリースで[こちらの CVE](https://msrc.microsoft.com/update-guide/vulnerability/CVE-2021-36949) に記載されている脆弱性に対処します。 この脆弱性の詳細については、CVE を参照してください。

### 1.1.359.0

#### 新機能と機能強化

- アクセス許可を設定/リセットするための GMSA コマンドレット

#### 修正された問題

- GMSA フォルダーのアクセス許可を修正しました (最初は、ブートストラップの問題が発生しました)
- 1 つの値参照属性 (マネージャーなど) に対する複数の変更の処理を修正しました
- 初期列挙のエラーと、エラーのトレースの強化を修正しました
- スコーピング グループに対するグループ メンバーシップの更新を最適化しました。 この更新プログラムにより、お客様はグループ スコープ フィルターを使用して最大 50 K メンバーのグループを同期できるようになりました
- プロビジョニング オンデマンドで使用されるスコープを使用して DN によって 1 つのオブジェクトを取得し、スコープ ロジックに従うサポートを追加しました

### 1.1.354.0

2021 年 1 月 20 日: ダウンロードのみリリース

#### 新機能と機能強化

- 事前にカスタムで作成された GMSA アカウントに対するサポートなど、GMSA エクスペリエンスの向上
- [PowerShell コマンドレットを使用した GMSA セットアップ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets) のサポートを追加しました
- [CLI](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install-pshell) エージェントのインストール (サイレント インストール) のサポートを追加しました
- エージェント ソースの検疫に関する問題の診断を追加しました
- OU スコープ フィルターのメモリ使用量の削減、スコープ内ユーザーに対してのみ PHS を実行する、OU スコープを使用する場合の OU 内の入れ子になったオブジェクトの処理など。

#### 修正された問題

- スコープ グループがスコープ外になったときに検疫を防止
- スコープ フィルターが構成されている場合、PHS ジョブがスコープ内ユーザーに対してのみ動作するようになりました
- アップグレード中にエージェントが応答を停止する場合がある
- OU スコープ使用時における入れ子になった OU 内のオブジェクトの初期同期
- Repair-AADCloudSyncToolsAccount の堅牢性を向上
- OU スコープ フィルターでの大量のメモリ使用量を削減
- ロール メンバーにセキュリティ グループが含まれていると、管理者ロールの検査が失敗する
- エージェント証明書の更新を妨げる GMSA フォルダーのアクセス許可の問題を修正しました

### 1.1.281.0

#### リリースの状態

2020 年 11 月 23 日: ダウンロードのみリリース

#### 新機能と機能強化

- [gMSA](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#group-managed-service-accounts) のサポートを追加しました
- 増分または差分同期サイクル中に、最大サイズが 1,500 メンバー未満のグループのサポートが追加されました。 この変更は、グループ スコープ フィルターの使用時に適用されます
- メンバー サイズが最大 15 K の大規模なグループのサポートを追加しました
- 初期同期の機能強化を追加しました
- 高度な詳細ログ記録を追加しました
- AADCloudSyncTools PowerShell モジュール  を追加しました
- 英語以外のサーバーにエージェントをインストールできるようにするために修正された制限
- スコープ内のオブジェクトに対してのみ PHS フィルター処理のサポートが追加されました (当初は、すべてのオブジェクトのパスワード ハッシュを同期していました)
- エージェントのメモリ リークの問題を修正しました
- プロビジョニング ログの改善を追加
- [LDAP 接続タイムアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-manage-registry-options#configure-ldap-connection-timeout) の構成のサポートを追加しました
- [紹介追跡](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-manage-registry-options#configure-referral-chasing) の構成のサポートを追加しました

### 1.1.96.0

#### リリースの状態

2019 年 12 月 4 日: ダウンロードのみリリース

#### 新機能と機能強化

- [Azure AD Connect クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync) のサポートが追加され、ユーザー、連絡先、グループデータを オンプレミスの Active Directory から Azure AD に同期する

### 1.1.67.0

#### リリースの状態

2019 年 9 月 9 日: 自動更新向けにリリース済み

#### 新機能と機能強化

- プロビジョニング エージェントの問題をデバッグするためのトレースとログ記録をさらに構成する機能を追加しました
- マッピングで構成されているAzure AD 属性のみをフェッチして同期のパフォーマンスを向上させる機能を追加しました

#### 修正された問題

- Azure AD 接続エラーに関する問題が発生した場合にエージェントが応答しない状態になるバグを修正しました
- バイナリ データが Azure Active Directory から読み取られたときに問題が発生するバグを修正しました
- エージェントがクラウド ハイブリッド ID サービスとの信頼を更新するのを失敗させていたバグを修正しました

### 1.1.30.0

#### リリースの状態

2019 年 1 月 23 日:ダウンロード対象としてリリース済み

#### 新機能と機能強化

- パフォーマンス、安定性、信頼性を向上させるためにプロビジョニング エージェントおよびコネクタのアーキテクチャが刷新されました
- UI 駆動型インストール ウィザードを使用してプロビジョニング エージェントの構成が簡略化されました
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/tutorial-basic-ad-azure"} -->
## チュートリアル - オンプレミスの Active Directory と Microsoft Entra の基本的な環境。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-basic-ad-azure
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: AD と Microsoft Entra の基本的な環境を作成する方法について説明します。

このチュートリアルでは、Active Directory の基本的な環境を作成する手順について説明します。

[Image: Microsoft Entra の基本的な環境を示す図。]

チュートリアルで作成した環境を使用して、ハイブリッド ID シナリオのさまざまな側面をテストできます。 これは、一部のチュートリアルの前提条件です。 既存の Active Directory 環境がある場合は、代わりに使用できます。 この情報は、何も開始していない個人向けに提供されます。

### 前提条件

このチュートリアルを完了するために必要な前提条件を次に示します。

- [Hyper-V](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/hyper-v-technology-overview) がインストールされたコンピューター。 Windows [10 または Windows](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/about/supported-guest-os)[Server 2022](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/supported-windows-guest-operating-systems-for-hyper-v-on-windows) コンピューターでこれを行うことをお勧めします。
- 仮想マシンがインターネットで通信できるようにするための[外部ネットワーク アダプター](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/quick-start/connect-to-network)。
- [Azure サブスクリプション](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)
- Windows Server 2022 のコピー
- [Microsoft .NET Framework 4.7.1](https://dotnet.microsoft.com/download/dotnet-framework/net471)

注

最短時間でチュートリアル環境を作成できるよう、このチュートリアルでは PowerShell スクリプトを使用します。 各スクリプトでは、その先頭で宣言された変数が使用されます。 変数はお客様の環境に合わせて変更することができ、そうする必要があります。

使用されるスクリプトでは、Microsoft Entra Connect クラウド プロビジョニング エージェントのインストール前に一般的な Active Directory 環境が作成されます。 これらはすべてのチュートリアルに関連しています。

このチュートリアルで使用される PowerShell スクリプトのコピーは、[こちら](https://github.com/billmath/tutorial-phs)の GitHub で入手できます。

### 仮想マシンの作成

まず、仮想マシンを作成する必要があります。 この仮想マシンは、オンプレミスの Active Directory サーバーとして使用されます。 この手順は、ハイブリッド ID 環境を稼働させるために不可欠です。 次の操作を行います。

1. PowerShell ISE を管理者として起動します。
2. 次のスクリプトを実行します。

```powershell
#Declare variables
$VMName = 'DC1'
$Switch = 'External'
$InstallMedia = 'D:\ISO\en_windows_server_2016_updated_feb_2018_x64_dvd_11636692.iso'
$Path = 'D:\VM'
$VHDPath = 'D:\VM\DC1\DC1.vhdx'
$VHDSize = '64424509440'

#Create New Virtual Machine
New-VM -Name $VMName -MemoryStartupBytes 16GB -BootDevice VHD -Path $Path -NewVHDPath $VHDPath -NewVHDSizeBytes $VHDSize -Generation 2 -Switch $Switch 

#Set the memory to be non-dynamic
Set-VMMemory $VMName -DynamicMemoryEnabled $false

#Add DVD Drive to Virtual Machine
Add-VMDvdDrive -VMName $VMName -ControllerNumber 0 -ControllerLocation 1 -Path $InstallMedia

#Mount Installation Media
$DVDDrive = Get-VMDvdDrive -VMName $VMName

#Configure Virtual Machine to Boot from DVD
Set-VMFirmware -VMName $VMName -FirstBootDevice $DVDDrive 
```

### オペレーティング システムの展開を完了する

仮想マシンの作成を完了するには、オペレーティング システムのインストールを完了する必要があります。

1. Hyper-V マネージャーで、仮想マシンをダブル選択します
2. [スタート] ボタンを選択します。
3. "CD または DVD から起動するには、任意のキーを押してください" というメッセージが表示されます。 どうぞ、そうしてください。
4. Windows Server の起動画面で言語を選択し、[ **次へ**] を選択します。
5. **[今すぐインストール]** を選択します。
6. ライセンス キーを入力し、**[次へ]** を選択します。
7. \*\*[ライセンス条項に同意します] をオンにして、[ **次へ**] を選択します。
8. **[カスタム: Windows のみをインストールする (詳細設定)]** を選択します
9. **[次へ]** を選択します
10. インストールが完了したら、仮想マシンを再起動してサインインし、Windows 更新プログラムを実行して、VM が最も up-to日付であることを確認します。 最新の更新プログラムをインストールします。

### Active Directory の前提条件をインストールする

仮想マシンを稼働させたところで、Active Directory のインストール前にいくつかの作業を行う必要があります。 言い換えると、仮想マシンの名前を変更し、静的 IP アドレスと DNS 情報を設定して、リモート サーバー管理ツールをインストールする必要があります。 次の操作を行います。

1. PowerShell ISE を管理者として起動します。
2. 次のスクリプトを実行します。

```powershell
#Declare variables
$ipaddress = "10.0.1.117" 
$ipprefix = "24" 
$ipgw = "10.0.1.1" 
$ipdns = "10.0.1.117"
$ipdns2 = "8.8.8.8" 
$ipif = (Get-NetAdapter).ifIndex 
$featureLogPath = "c:\poshlog\featurelog.txt" 
$newname = "DC1"
$addsTools = "RSAT-AD-Tools" 

#Set static IP address
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

作成した VM の作成と名前の変更が完了し、静的 IP アドレスが設定されたので、Active Directory Domain Services をインストールして構成できます。 次の操作を行います。

1. PowerShell ISE を管理者として起動します。
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

#Install AD DS, DNS and GPMC 
start-job -Name addFeature -ScriptBlock { 
Add-WindowsFeature -Name "ad-domain-services" -IncludeAllSubFeature -IncludeManagementTools 
Add-WindowsFeature -Name "dns" -IncludeAllSubFeature -IncludeManagementTools 
Add-WindowsFeature -Name "gpmc" -IncludeAllSubFeature -IncludeManagementTools } 
Wait-Job -Name addFeature 
Get-WindowsFeature | Where installed >>$featureLogPath

#Create New AD Forest
Install-ADDSForest -CreateDnsDelegation:$false -DatabasePath $DatabasePath -DomainMode $DomainMode -DomainName $DomainName -SafeModeAdministratorPassword $SecureString -DomainNetbiosName $DomainNetBIOSName -ForestMode $ForestMode -InstallDns:$true -LogPath $LogPath -NoRebootOnCompletion:$false -SysvolPath $SysVolPath -Force:$true
```

### Windows Server AD ユーザーを作成する

Active Directory 環境が整ったら、テスト アカウントを作成する必要があります。 このアカウントは、オンプレミスの AD 環境で作成され、Microsoft Entra ID に同期されます。 次の操作を行います。

1. PowerShell ISE を管理者として起動します。
2. 次のスクリプトを実行します。

```powershell
# Filename:  4_CreateUser.ps1
# Description: Creates a user in Active Directory. This is part of
#       the Azure AD Connect password hash sync tutorial.
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

次に、ユーザーをクラウドに同期できるよう、Microsoft Entra テナントを作成する必要があります。 新しい Microsoft Entra テナントを作成するには、次の操作を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインし、Microsoft Entra サブスクリプションのあるアカウントでサインインします。
2. **[概要]** を選択します。
3. **テナントの管理** を選択します。
4. **[作成]** を選択します。
5. **組織の名前**と**初期ドメイン名**を入力します。 **[作成]** を選択します。 これにより、ディレクトリが作成されます。
6. これが完了したら、 **ここ** のリンクを選択してディレクトリを管理します。

### Microsoft Entra ID でハイブリッド ID 管理者を作成する

Microsoft Entra テナントを作成したら、ハイブリッド ID 管理者アカウントを作成します。 ハイブリッド ID 管理者アカウントを作成するには、次のようにします。

1. **[管理]** で、**[ユーザー]** を選択します。[Image: [概要] メニューを示すスクリーンショット。[ユーザー] が選択されています。]
2. **[すべてのユーザー]** を選択し、 **+ [新しいユーザー]** を選択します。
3. このユーザーの名前およびユーザー名を入力します。 これは、テナントのハイブリッド ID 管理者です。 **ディレクトリ ロール**を**ハイブリッド ID 管理者に変更します。**一時パスワードを表示することもできます。 完了したら、**[作成]** を選択します。
4. これが完了したら、新しい Web ブラウザーを開き、新しいハイブリッド ID 管理者アカウントと一時パスワードを使用して myapps.microsoft.com にサインインします。
5. ハイブリッド アイデンティティ管理者のパスワードを覚えやすいものに変更してください。

### 省略可能: 別のサーバーとフォレスト

次に示すのは、別のサーバーとフォレストを作成する手順を説明する省略可能なセクションです。 これは、[Microsoft Entra Connect クラウド同期のパイロットの実施](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp)など、より高度なチュートリアルで使用できます。

別のサーバーのみが必要な場合は、[- **仮想マシンの作成] ステップの** 後で停止し、前に作成した既存のドメインにサーバーを参加させることができます。

#### 仮想マシンの作成

1. PowerShell ISE を管理者として起動します。
2. 次のスクリプトを実行します。

```powershell
# Filename:  1_CreateVM_CP.ps1
# Description: Creates a VM to be used in the tutorial.
#
# DISCLAIMER:
# Copyright (c) Microsoft Corporation. All rights reserved. #This script is made available to you without any express, implied or statutory warranty, not even the implied warranty of merchantability or fitness for a particular purpose, or the warranty of title or non-infringement. The entire risk of the use or the results from the use of this script remains with you.
#
#
#
#
#Declare variables
$VMName = 'CP1'
$Switch = 'External'
$InstallMedia = 'D:\ISO\en_windows_server_2016_updated_feb_2018_x64_dvd_11636692.iso'
$Path = 'D:\VM'
$VHDPath = 'D:\VM\CP1\CP1.vhdx'
$VHDSize = '64424509440'

#Create New Virtual Machine
New-VM -Name $VMName -MemoryStartupBytes 16GB -BootDevice VHD -Path $Path -NewVHDPath $VHDPath -NewVHDSizeBytes $VHDSize -Generation 2 -Switch $Switch 

#Set the memory to be non-dynamic
Set-VMMemory $VMName -DynamicMemoryEnabled $false

#Add DVD Drive to Virtual Machine
Add-VMDvdDrive -VMName $VMName -ControllerNumber 0 -ControllerLocation 1 -Path $InstallMedia

#Mount Installation Media
$DVDDrive = Get-VMDvdDrive -VMName $VMName

#Configure Virtual Machine to Boot from DVD
Set-VMFirmware -VMName $VMName -FirstBootDevice $DVDDrive
```

#### オペレーティング システムの展開を完了する

仮想マシンの作成を完了するには、オペレーティング システムのインストールを完了する必要があります。

1. Hyper-V マネージャーで、仮想マシンをダブル選択します
2. [スタート] ボタンを選択します。
3. "CD または DVD から起動するには、任意のキーを押してください" というメッセージが表示されます。 どうぞ、そうしてください。
4. Windows Server の起動画面で言語を選択し、[ **次へ**] を選択します。
5. **[今すぐインストール]** を選択します。
6. ライセンス キーを入力し、**[次へ]** を選択します。
7. \*\*[ライセンス条項に同意します] をオンにして、[ **次へ**] を選択します。
8. **[カスタム: Windows のみをインストールする (詳細設定)]** を選択します
9. **[次へ]** を選択します
10. インストールが完了したら、仮想マシンを再起動してサインインし、Windows 更新プログラムを実行して、VM が最も up-to日付であることを確認します。 最新の更新プログラムをインストールします。

#### Active Directory の前提条件をインストールする

仮想マシンが稼働したら、Active Directory をインストールする前にいくつかの操作を行う必要があります。 言い換えると、仮想マシンの名前を変更し、静的 IP アドレスと DNS 情報を設定して、リモート サーバー管理ツールをインストールする必要があります。 次の操作を行います。

1. PowerShell ISE を管理者として起動します。
2. 次のスクリプトを実行します。

```powershell
# Filename:  2_ADPrep_CP.ps1
# Description: Prepares your environment for Active Directory. This is part of
#       the Azure AD Connect password hash sync tutorial.
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
$ipaddress = "10.0.1.118" 
$ipprefix = "24" 
$ipgw = "10.0.1.1" 
$ipdns = "10.0.1.118"
$ipdns2 = "8.8.8.8" 
$ipif = (Get-NetAdapter).ifIndex 
$featureLogPath = "c:\poshlog\featurelog.txt" 
$newname = "CP1"
$addsTools = "RSAT-AD-Tools" 

#Set static IP address
New-NetIPAddress -IPAddress $ipaddress -PrefixLength $ipprefix -InterfaceIndex $ipif -DefaultGateway $ipgw 

#Set the DNS servers
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

#### Windows Server AD 環境を作成する

VM を作成して名前を変更し、静的 IP アドレスが設定できたので、Active Directory Domain Services をインストールして構成する準備ができました。 次の操作を行います。

1. PowerShell ISE を管理者として起動します。
2. 次のスクリプトを実行します。

```powershell
# Filename:  3_InstallAD_CP.ps1
# Description: Creates an on-premises AD environment. This is part of
#       the Azure AD Connect password hash sync tutorial.
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
$DatabasePath = "c:\windows\NTDS"
$DomainMode = "WinThreshold"
$DomainName = "fabrikam.com"
$DomaninNetBIOSName = "FABRIKAM"
$ForestMode = "WinThreshold"
$LogPath = "c:\windows\NTDS"
$SysVolPath = "c:\windows\SYSVOL"
$featureLogPath = "c:\poshlog\featurelog.txt" 
$Password = "Pass1w0rd"
$SecureString = ConvertTo-SecureString $Password -AsPlainText -Force

#Install AD DS, DNS and GPMC 
start-job -Name addFeature -ScriptBlock { 
Add-WindowsFeature -Name "ad-domain-services" -IncludeAllSubFeature -IncludeManagementTools 
Add-WindowsFeature -Name "dns" -IncludeAllSubFeature -IncludeManagementTools 
Add-WindowsFeature -Name "gpmc" -IncludeAllSubFeature -IncludeManagementTools } 
Wait-Job -Name addFeature 
Get-WindowsFeature | Where installed >>$featureLogPath

#Create New AD Forest
Install-ADDSForest -CreateDnsDelegation:$false -DatabasePath $DatabasePath -DomainMode $DomainMode -DomainName $DomainName -SafeModeAdministratorPassword $SecureString -DomainNetbiosName $DomainNetBIOSName -ForestMode $ForestMode -InstallDns:$true -LogPath $LogPath -NoRebootOnCompletion:$false -SysvolPath $SysVolPath -Force:$true
```

#### Windows Server AD ユーザーを作成する

Active Directory 環境を作成したところで、テスト アカウントが必要になります。 このアカウントは、オンプレミスの AD 環境で作成され、Microsoft Entra ID に同期されます。 次の操作を行います。

1. PowerShell ISE を管理者として起動します。
2. 次のスクリプトを実行します。

```powershell
# Filename:  4_CreateUser_CP.ps1
# Description: Creates a user in Active Directory. This is part of
#       the Azure AD Connect password hash sync tutorial.
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
$Givenname = "Anna"
$Surname = "Ringdal"
$Displayname = "Anna Ringdal"
$Name = "aringdal"
$Password = "Pass1w0rd"
$Identity = "CN=aringdal,CN=Users,DC=fabrikam,DC=com"
$SecureString = ConvertTo-SecureString $Password -AsPlainText -Force

#Create the user
New-ADUser -Name $Name -GivenName $Givenname -Surname $Surname -DisplayName $Displayname -AccountPassword $SecureString

#Set the password to never expire
Set-ADUser -Identity $Identity -PasswordNeverExpires $true -ChangePasswordAtLogon $false -Enabled $true
```

### まとめ

これで、既存のチュートリアルでクラウド同期が提供する他の機能をテストするのに使用できる環境ができました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/tutorial-directory-extension-group-provisioning"} -->
## Active Directoryへのプロビジョニング時にディレクトリ拡張機能を使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-directory-extension-group-provisioning
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-21
- Summary: Microsoft Entra IDからActive Directoryにユーザーとグループをプロビジョニングするときにディレクトリ拡張属性を使用する方法について説明します。

ディレクトリ拡張機能は、Microsoft Entra IDが所有するMicrosoft Entra スキーマに属性を追加します。 Microsoft Entra IDからActive Directoryにプロビジョニングする場合、その属性を 2 つの用途に配置できます。プロビジョニング*する*オブジェクトを決定するか、*値*をActive Directory属性に伝達します。 どちらの方法も、ユーザーとグループの両方で機能します。

この記事では、それぞれの 1 つの例について説明します。 各手順の [ **グループ** ] タブまたは [ **ユーザー** ] タブを選択して、目的の例に従います。 選択内容は、記事の残りの部分にも引き継がれます。

| タブ | Example | Use |
| --- | --- | --- |
| **グループ** | `WritebackEnabled` | 拡張機能の値が true のグループのみをプロビジョニングします。 |
| **ユーザー** | `EmployeeCode` | 拡張機能の値をActive Directoryユーザー属性に書き込みます。 |

ディレクトリ拡張機能の背景については、[Active DirectoryにMicrosoft Entra IDをプロビジョニングするためのディレクトリ拡張機能に関する説明を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping-entra-to-active-directory)参照してください。 一般的なマッピング インターフェイスと式の構文については、「[Active Directoryへのプロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#configure-attribute-mapping)」を参照してください。

注

Microsoft Entra IDには、Microsoft Graphで設定できるグループに組み込みの`isWritebackEnabled` プロパティが用意されています。 属性値のフィルター処理を使用して、そのプロパティ [を直接フィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#attribute-value-filtering)できるため、書き戻されるグループを制御するためにカスタム拡張属性は必要ありません。 この記事の手順は、組み込みプロパティに含まれない値のスコープを設定する必要がある場合に使用します。

グループは属性マッピングもサポートします。 権限のソースがMicrosoft Entra IDに変換されたグループの場合、`GroupDN`拡張機能は元の組織単位と共通名を保持します。 その拡張機能を作成するには、「 [グループの組織単位と名前を保持](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-preserve-group-organizational-unit-entra-to-active-directory)する」を参照してください。 これを読み取る式については、「 [グループの元の組織単位を保持](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#preserve-a-groups-original-organizational-unit)する」を参照してください。

### 始める前の準備

どちらの例も、同じ開始点が必要です。

- ユーザーをMicrosoft Entra IDに同期する作業環境。
- ターゲット Active Directory ドメインに接続されている正常なプロビジョニング エージェント。
- Microsoft Entra ID から Active Directory への構成、またはその構成を作成するためのアクセス許可。

各例には、独自のオブジェクトが必要です。

## [グループ](#tab/groups)
この例では、次の環境を使用します。

- 同期された 4 人のユーザー: Britta Simon、Lola Jacobson、Anna Ringdahl、John Smith。
- Active Directoryの 3 つの組織単位: 営業、マーケティング、グループ。
- Britta Simon と Anna Ringdahl のユーザー アカウントは、Sales OU に存在します。
- Lola Jacobson と John Smith のユーザー アカウントは、Marketing OU に存在します。
- グループ OU は、Microsoft Entra IDのグループがプロビジョニングされる場所です。

[Image: クラウド同期を使用したグループ ライトバックの図。]

## [ユーザー](#tab/users)
この例では、次の環境を使用します。

- Microsoft Entra IDのクラウドで管理されたテスト ユーザー。
- ターゲット組織単位 ( `OU=test,DC=Contoso,DC=com`など)。
- 書き込み可能なターゲットActive Directoryユーザー属性 (`extensionAttribute1`など)。

---

この記事で作成した環境は、テスト用またはクラウド同期に慣れるために使用できます。

ヒント

Microsoft Graph PowerShell SDK コマンドレットの実行エクスペリエンスを向上するには、`ms-vscode.powershell`で拡張機能 Visual Studio Code を使用します。

### Microsoft Graph PowerShell SDK をインストールして接続する

1. まだインストールされていない場合は、 [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) のドキュメントに従って、Microsoft Graph PowerShell SDK のメイン モジュール ( `Microsoft.Graph`) をインストールします。
2. 管理者特権で PowerShell を開きます。
3. 実行ポリシーを設定するには、以下を実行します (プロンプトが表示されたら、[A] (はい) を押します)。

    ```powershell
    Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
    ```
4. テナントに接続します (サインイン時に、必ず代理としての同意を承諾してください):

    ```powershell
    Connect-MgGraph -Scopes "Directory.ReadWrite.All", "Application.ReadWrite.All", "User.ReadWrite.All", "Group.ReadWrite.All"
    ```

    重要

    対話形式で認証します。 アカウント パスワードをスクリプトに含めないでください。

### CloudSyncCustomExtensionsApp アプリケーションとサービス プリンシパルを作成する

どちらの例も同じアプリケーションに拡張機能を格納するため、これは 1 回だけ行う必要があります。

重要

Microsoft Entra Cloud Sync のディレクトリ拡張機能は、識別子 URI `api://<tenantId>/CloudSyncCustomExtensionsApp` を持つアプリケーションと、Microsoft Entra Connect によって作成された [テナント スキーマ拡張機能アプリ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions#configuration-changes-in-azure-ad-made-by-the-wizard) でのみサポートされます。

1. テナント ID を取得します。

    ```powershell
    $tenantId = (Get-MgOrganization).Id
    $tenantId
    ```

    注

    これにより、現在のテナント ID が出力されます。 このテナント ID を確認するには、 [Microsoft Entra 管理センター](https://entra.microsoft.com/)&gt;**Entra ID**&gt;**Overview** に移動します。
2. 前の手順の `$tenantId` 変数を使用して、CloudSyncCustomExtensionsApp が存在するかどうかを確認します。

    ```powershell
    $cloudSyncCustomExtApp = Get-MgApplication -Filter "identifierUris/any(uri:uri eq 'api://$tenantId/CloudSyncCustomExtensionsApp')"
    $cloudSyncCustomExtApp
    ```
3. CloudSyncCustomExtensionsApp が存在する場合は、次の手順に進みます。 それ以外の場合は、新しい CloudSyncCustomExtensionsApp アプリを作成します。

    ```powershell
    $cloudSyncCustomExtApp = New-MgApplication -DisplayName "CloudSyncCustomExtensionsApp" -IdentifierUris "api://$tenantId/CloudSyncCustomExtensionsApp"
    $cloudSyncCustomExtApp
    ```
4. CloudSyncCustomExtensionsApp アプリケーションにサービス プリンシパルが関連付けられているかどうかを確認します。 新しいアプリを作成したばかりの場合は、次の手順に進みます。

    ```powershell
    Get-MgServicePrincipal -Filter "AppId eq '$($cloudSyncCustomExtApp.AppId)'"
    ```
5. 新しいアプリを作成したばかりの場合、またはサービス プリンシパルが返されない場合は、CloudSyncCustomExtensionsApp のサービス プリンシパルを作成します。

    ```powershell
    New-MgServicePrincipal -AppId $cloudSyncCustomExtApp.AppId
    ```

注

Microsoft Entra IDでディレクトリ拡張機能を作成する場合、プロビジョニング エージェントを再起動する必要はありません。 新しく追加されたActive Directoryスキーマ属性を検出する必要がある場合にのみ、エージェントを再起動します。

### プロビジョニング対象のオブジェクトを準備する

## [グループ](#tab/groups)
Microsoft Entra IDで 2 つのグループを作成します。 一方のグループは Sales で、もう 1 つはマーケティングです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[グループ]**&gt;**[すべてのグループ]** に移動します。
3. 上部にある [ **新しいグループ**] を選択します。
4. **グループの種類**が**セキュリティ**に設定されていることを確認します。
5. **[グループ名] に**「**Sales**」と入力します。
6. **[メンバーシップの種類**] では、割り当て済みのままにします。
7. **を選択して**を作成します。
8. **グループ名**として **Marketing** を使用して、このプロセスを繰り返します。

ここで、作成したグループにユーザーを追加します。

1. **[Entra ID]**&gt;**[グループ]**&gt;**[すべてのグループ]** に移動します。
2. 上部の検索ボックスに「 **Sales**」と入力します。
3. 新しい **Sales** グループを選択します。
4. 左側の [メンバー] を選択 **します**。
5. 上部にある [メンバーの **追加]** を選択します。
6. **Britta Simon** と **Anna Ringdahl** の横にチェックを入れて、[選択] を**選択**します。
7. 左端で[ **すべてのグループ** ]を選択し、 **マーケティング** グループを使用してこのプロセスを繰り返し、 **ローラ・ジェイコブソン** と **ジョン・スミス**を追加します。

注

Marketing グループにユーザーを追加するときは、概要ページでグループ ID をメモしておきます。 この ID は、後で新しく作成されたプロパティをグループに追加するために使用されます。

## [ユーザー](#tab/users)
Active Directory にプロビジョニングするクラウド管理ユーザーを特定し、そのユーザーを取得できることを確認します。

```powershell
$testUser = Get-MgUser -Filter "userPrincipalName eq '<test-user-UPN>'"
$testUser
```

必要に応じて、ユーザーを直接選択するのではなく、グループを介してスコープにユーザーを取り込むことができます。

1. **AD-Provisioning-Test** など、割り当てられたMicrosoft Entraセキュリティ グループを作成します。
2. クラウド管理のテスト ユーザーをグループに追加します。
3. クラウド同期でユーザー スコープを構成するときに、そのグループを選択します。

重要

拡張機能の値は、各ユーザーに保持されます。 グループ メンバーシップは、ユーザーをプロビジョニング スコープにのみ取り込みます。値は含まれません。

---

### ディレクトリ拡張機能を作成する

## [グループ](#tab/groups)
ヒント

この例では、Microsoft Entra Cloud Sync スコープ フィルターで使用する`WritebackEnabled`というカスタム拡張属性を作成し、`WritebackEnabled`が true に設定されているグループのみがオンプレミスの Active Directoryに書き戻されるようにします。 これは、この記事で前述した組み込みの `isWritebackEnabled` プロパティと同様に機能します。

CloudSyncCustomExtensionsApp で拡張機能属性を作成し、Group オブジェクトに割り当てます。

```powershell
New-MgApplicationExtensionProperty -ApplicationId $cloudSyncCustomExtApp.Id -Name 'WritebackEnabled' -DataType 'Boolean' -TargetObjects 'Group'
```

このコマンドレットは、 `extension_<AppIdWithoutHyphens>_WritebackEnabled`のような拡張属性を作成します。

## [ユーザー](#tab/users)
CloudSyncCustomExtensionsApp で、拡張機能属性を作成し、User オブジェクトに割り当てます。

```powershell
New-MgApplicationExtensionProperty -ApplicationId $cloudSyncCustomExtApp.Id -Name 'EmployeeCode' -DataType 'String' -TargetObjects 'User'
```

このコマンドレットは、 `extension_<AppIdWithoutHyphens>_EmployeeCode`のような拡張属性を作成します。 属性マッピングを追加するときに必要になるため、生成された名前を取得します。

```powershell
$userExtension = Get-MgApplicationExtensionProperty -ApplicationId $cloudSyncCustomExtApp.Id |
    Where-Object Name -Like '*_EmployeeCode' | Select-Object -First 1

$userExtensionName = $userExtension.Name
$userExtensionName
```

---

### 拡張機能の値を設定する

## [グループ](#tab/groups)
マーケティング グループの新しく作成されたプロパティに値を設定します。

1. 拡張プロパティを取得します。

    ```powershell
    $gwbEnabledExtAttrib = Get-MgApplicationExtensionProperty -ApplicationId $cloudSyncCustomExtApp.Id |
        Where-Object {$_.Name -Like '*WritebackEnabled'} | Select-Object -First 1
    $gwbEnabledExtName = $gwbEnabledExtAttrib.Name
    ```
2. `Marketing` グループを取得します。

    ```powershell
    $marketingGrp = Get-MgGroup -ConsistencyLevel eventual -Filter "DisplayName eq 'Marketing'"
    ```
3. マーケティング グループの値 `True` を設定します。

    ```powershell
    Update-MgGroup -GroupId $marketingGrp.Id -AdditionalProperties @{$gwbEnabledExtName = $true}
    ```
4. 確認するには、プロパティ値を読み取る:

    ```powershell
    $marketingGrp = Get-MgGroup -ConsistencyLevel eventual -Filter "DisplayName eq 'Marketing'" -Property Id,$gwbEnabledExtName
    $marketingGrp.AdditionalProperties.$gwbEnabledExtName
    ```

## [ユーザー](#tab/users)
1. テスト ユーザーに値を設定します。

    ```powershell
    Update-MgUser -UserId $testUser.Id -AdditionalProperties @{ $userExtensionName = "EMP-1001" }
    ```
2. 確認するには、プロパティ値を読み取る:

    ```powershell
    $testUser = Get-MgUser -UserId $testUser.Id -Property "id,displayName,$userExtensionName"
    $testUser.AdditionalProperties[$userExtensionName]
    ```

---

#### Microsoft Graph Explorer を使用して拡張機能の値を設定する

この値は、PowerShell ではなく [Microsoft Graph Explorer](https://developer.microsoft.com/graph/graph-explorer) を使用して設定できます。 [アクセス許可の変更] を選択して、必要な **アクセス許可**に同意したことを確認します。

## [グループ](#tab/groups)
1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)に移動し、`Group.ReadWrite.All`に同意します。
2. テナント管理者アカウントを使用してサインインします。 ハイブリッド ID 管理者アカウントは、このシナリオの作成に使用され、十分な場合があります。
3. 上部で、[ **GET** ] を **[PATCH**] に変更します。
4. [アドレス] ボックスに「 `https://graph.microsoft.com/v1.0/groups/<Group Id>`」と入力します。
5. 要求本文で、次のように入力します。

    ```json
    {
      "extension_<AppIdWithoutHyphens>_WritebackEnabled": true
    }
    ```
6. **[クエリの実行]** を選択します。

    [Image: グラフ クエリの実行のスクリーンショット。]
7. 正常に完了すると、 `[]`が表示されます。
8. 上部で **PATCH を** **GET** に変更し、Marketing グループのプロパティを見ます。 **[クエリの実行]** を選択します。 新しく作成された属性が表示されるはずです。

    [Image: グループ プロパティのスクリーンショット。]

## [ユーザー](#tab/users)
1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)に移動し、`User.ReadWrite.All`に同意します。
2. テナント管理者アカウントを使用してサインインします。
3. 上部で、[ **GET** ] を **[PATCH**] に変更します。
4. [アドレス] ボックスに「 `https://graph.microsoft.com/v1.0/users/<User Id>`」と入力します。
5. 要求本文で、次のように入力します。

    ```json
    {
      "extension_<AppIdWithoutHyphens>_EmployeeCode": "EMP-1001"
    }
    ```
6. **[クエリの実行]** を選択します。
7. 上部で **PATCH を** **GET** に変更し、ユーザーのプロパティを表示します。 **[クエリの実行]** を選択します。 新しく作成された属性が表示されるはずです。

---

### クラウド同期構成で拡張機能を使用する

## [グループ](#tab/groups)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。
3. [ **新しい構成]** を選択します。
4. **[Microsoft Entra ID to AD sync]** を選択します。

    [Image: 構成の選択のスクリーンショット。]
5. 構成画面で、ドメインを選択します。 **を選択して**を作成します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **作業の開始** ] 画面が開きます。 ここから、クラウド同期の構成を続行できます。
7. 左側で、 **スコープ フィルターを**選択し、 **グループ スコープ**&gt;**すべてのグループ**を選択します。
8. [ **属性マッピングの編集]** を選択し、 **ターゲット コンテナー** を `OU=Groups,DC=Contoso,DC=com`に変更します。 **保存**を選びます。
9. **属性スコープ フィルターの追加**を選択します。
10. スコープ フィルターの名前を入力します: `Filter groups with Writeback Enabled`。
11. [ **ターゲット属性**] で、 `extension_<AppIdWithoutHyphens>_WritebackEnabled`のような新しく作成された属性を選択します。

    重要

    ドロップダウン リストに表示される一部のターゲット属性は、`extensionAttribute[1-15]`など、すべてのプロパティをMicrosoft Entra IDで管理できないので、スコープ フィルターとして使用できない場合があります。 そのため、この目的のためにカスタム拡張機能プロパティを作成することをお勧めします。

    [Image: 使用可能な属性のスクリーンショット。]
12. [ **演算子**] で [ **IS TRUE**] を選択します。
13. [ **保存]** を選択し、[ **保存]** を選択します。
14. 構成は無効のままにして、後で戻ります。

## [ユーザー](#tab/users)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。
3. [**新しい構成**] を選択し、**AD 同期Microsoft Entra IDを**選択するか、既存の構成を開きます。
4. Active Directory ドメインと正常なプロビジョニング エージェントを選択します。
5. 左側で、 **スコープ フィルターを**選択し、 **ユーザー スコープを構成します**。 テスト ユーザーを直接選択するか、ユーザーを含むグループを選択します。
6. ユーザーの**属性マッピングの編集**を選択し、**ターゲット コンテナー**を完全なActive Directory識別名 (`OU=test,DC=Contoso,DC=com`など) に設定します。
7. 属性マッピングを追加します。

    | Setting | 価値 |
    | --- | --- |
    | マッピングの種類 | 直接 |
    | ソース属性 | `extension_<AppIdWithoutHyphens>_EmployeeCode` |
    | ターゲット属性 | `extensionAttribute1`、または別の書き込み可能なActive Directoryユーザー属性 |
8. **保存**を選びます。
9. スコープ、ターゲット コンテナー、マッピングを確認するまで、構成は無効のままにします。

---

### テストして検証する

## [グループ](#tab/groups)
注

オンデマンド プロビジョニングを使用する場合、メンバーは自動的にプロビジョニングされません。 テストするメンバーを選択する必要があり、5 つのメンバー制限があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. 左側で、[ **オンデマンドプロビジョニング**] を選択します。
3. **[選択したグループ**] ボックスに「**マーケティング**」と入力します。
4. **[Selected users]\(選択したユーザー**\) セクションで、**Lola Jacobson** と **John Smith** を選択します。
5. **プロビジョン** を選択します。 これで正常にプロビジョニングされるはずです。

    [Image: プロビジョニングが正常に完了したスクリーンショット。]
6. 次に **、Sales** グループを試して、 **Britta Simon** と **Anna Ringdahl を**追加します。 Sales グループには拡張機能の値が設定されていないため、これはプロビジョニングしないでください。

    [Image: プロビジョニングがブロックされているスクリーンショット。]
7. Active Directory に、新しく作成された Marketing グループが表示されるはずです。

    [Image: Active Directory ユーザーとコンピューターの新しいグループのスクリーンショット。]
8. **Entra ID**&gt;**Entra Connect**&gt;**Cloud sync**&gt;**Overview** を参照して、構成を確認して有効にし、同期を開始できるようになりました。

## [ユーザー](#tab/users)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. 左側で、[ **オンデマンドプロビジョニング**] を選択します。
3. テスト ユーザーを選択します。 グループ スコープを使用した場合は、グループを選択し、テスト ユーザーを明示的に選択します。
4. **プロビジョン** を選択します。
5. インポート、スコープの評価、照合、およびエクスポートの手順を確認します。

Active Directoryに設定された値を確認します。

```powershell
Get-ADUser -Filter "UserPrincipalName -eq '<test-user-UPN>'" -SearchBase "OU=test,DC=Contoso,DC=com" -Properties extensionAttribute1 |
    Select-Object DistinguishedName,extensionAttribute1
```

予期される値は `extensionAttribute1 = EMP-1001` です。

プロビジョニング ログの結果を確認するには、**Entra ID**&gt;**監視と正常性**&gt;プロビジョニング **ログに**移動し、テスト ユーザーとクラウド同期構成でフィルター処理します。 次の点を確認します。

- スコープ評価を通過しました。
- ユーザーがActive Directoryで作成または照合されました。
- [ **変更されたプロパティ**] で、ディレクトリ拡張機能が `extensionAttribute1`にマップされました。
- エクスポート操作は成功しました。

---

### Active Directoryから変換されたユーザーのディレクトリ拡張機能

ユーザーの権限ソースを Microsoft Entra ID に変更する場合、以前は Active Directory が所有していた属性が、ディレクトリ属性またはディレクトリ スキーマ拡張機能として、Microsoft Entra ID にあらかじめ存在している必要があります。 前提条件の完全な一覧については、「ユーザーの [権限ソースを変換するための環境の準備](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/prepare-user-source-of-authority-environment)」を参照してください。

つまり、通常、変換されたユーザーは必要な拡張値を既に持っているので、それらの属性の新しい拡張機能は作成しません。 代わりに、値を既に保持している拡張機能からマップします。

Microsoft Entra Connect によって作成された拡張機能は、CloudSyncCustomExtensionsApp と共にマッピング ソースとしてサポートされている[テナント スキーマ拡張機能アプリ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions#configuration-changes-in-azure-ad-made-by-the-wizard)上でライブです。 [ **ユーザー** ] タブと同じ方法でマッピングを追加し、ソース属性として既存の拡張機能を選択します。 ソースオブオーソリティの変換では、拡張機能を再登録または再作成する必要はありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/tutorial-existing-forest"} -->
## チュートリアル - Microsoft Entra クラウド同期を使用して既存のフォレストと新しいフォレストを単一の Microsoft Entra テナントに統合する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-existing-forest
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: 既存のハイブリッド ID 環境にクラウド同期を追加する方法について説明します。

このチュートリアルでは、既存のハイブリッド ID 環境にクラウド同期を追加する手順について説明します。

[Image: Microsoft Entra Cloud Sync フローを示すダイアグラム。]

このチュートリアルで作成した環境は、テスト目的や、ハイブリッド ID のしくみについて理解を深める目的で使用できます。

このシナリオでは、Microsoft Entra Connect 同期を使って Microsoft Entra テナントと同期された既存のフォレストがあります。 さらに、同じ Microsoft Entra テナントに同期したい新しいフォレストがあります。 この新しいフォレストのためのクラウド同期を設定します。

### 前提条件

#### Microsoft Entra 管理センターで

1. Microsoft Entra テナントで、クラウド専用のハイブリッド ID 管理者アカウントを作成します。 その方法を採用すると、オンプレミス サービスがエラーまたは利用できなくなったとき、テナントの構成を管理できます。 [クラウド専用のハイブリッド ID 管理者アカウントを追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)方法について確認してください。 テナントからロックアウトされないようにするには、この手順を必ず完了する必要があります。
2. Microsoft Entra テナントに 1 つ以上の[カスタム ドメイン名](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)を追加します。 ユーザーは、このドメイン名のいずれかを使用してサインインできます。

#### オンプレミスの環境の場合

1. 4 GB 以上の RAM と .NET 4.7.1 以降のランタイムを搭載した、Windows Server 2012 R2 以降が実行されているドメイン参加済みホスト サーバーを特定します
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

プロビジョニングを構成するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. **[新しい構成]** を選択する
2. 構成画面で **通知用メール アドレス**を入力し、セレクターを **[有効]** に移動して、**[保存]** を選択します。
3. 構成の状態が **[正常]** になります。

### ユーザーが作成され、同期が実行されていることを確認する

ここで、オンプレミスのディレクトリに存在していたユーザーが同期され、現在は Microsoft Entra テナントに存在することを確認します。 このプロセスが完了するまでに数時間かかることがあります。 ユーザーが同期されていることを確認するには、次のようにします。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. テナントに新しいユーザーが表示されていることを確認してください。

### いずれかのユーザーでサインインをテストする

1. https://myapps.microsoft.com に移動します
2. 新しいテナントで作成されたユーザー アカウントを使用してサインインします。 user@domain.onmicrosoft.com の形式を使用してサインインする必要があります。 ユーザーがオンプレミスでのサインインに使用するのと同じパスワードを使用します。

    [Image: サインインしているユーザーを含むマイ アプリ ポータルを示すスクリーンショット。]

これでハイブリッド ID 環境を正常に設定できました。この環境は、Azure で提供されるサービスをテストしたり理解したりするために使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp"} -->
## チュートリアル - 同期された Active Directory フォレストの Microsoft Entra Cloud Sync に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-09-29
- Summary: 同期された Active Directory フォレストの Microsoft Entra Cloud Sync に移行する方法について説明します。

このチュートリアルでは、Microsoft Entra Connect Sync を使用して同期されたテスト Active Directory フォレストの Microsoft Entra Cloud Sync に移行する方法について説明します。

この記事では、基本的な移行に関する情報を提供します。 運用環境 [の移行を試みる前に、Microsoft Entra Cloud Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync) への移行に関するドキュメントを確認してください。

このチュートリアルでは、以下の内容を学習します。

- スケジューラを停止します。
- カスタム ユーザーの受信規則と送信規則を作成します。
- プロビジョニング エージェントをインストールします。
- エージェントのインストールを確認します。
- Microsoft Entra Cloud Sync を構成します。
- スケジューラを再起動します。

[Image: Microsoft Entra Cloud Sync フローを示す図。]

### 考慮事項

このチュートリアルを試す前に、次の点を考慮してください。

- Microsoft Entra Cloud Sync の基本を理解していることを確認します。
- Microsoft Entra Connect Sync バージョン 1.4.32.0 以降を実行していること、および記載されているように同期規則を構成していることを確認します。
- パイロットまたは共存のフェーズでは、パイロット組織単位 (OU)、グループ、ドメイン、または関連する参照先オブジェクトを Microsoft Entra Connect Sync スコープから削除しないでください。 オブジェクトをスコープ内に保持したまま、このチュートリアルで説明するカスタム `cloudNoFlow`受信ルールおよび `JoinNoFlow`送信ルールを使用して、Microsoft Entra Connect Sync がオブジェクトの追加、削除、および非参照属性の更新をエクスポートしないようにします。

    Microsoft Entra Connect Sync スコープからオブジェクトを削除すると、Active Directory コネクタ スペース、メタバース、Microsoft Entra コネクタ スペースから削除されます。 この削除により、コネクタ スペース内の参照が削除され、グループ メンバーシップの削除などの参照削除がMicrosoft Entra IDにエクスポートされます。
- Microsoft Entra Cloud Sync がオブジェクトと一致するように、パイロット スコープ内のオブジェクトに `ms-ds-consistencyGUID` が設定されていることを確認します。

    Microsoft Entra Connect Sync では、グループ オブジェクトの `ms-ds-consistencyGUID` は既定では設定されません。
- このチュートリアルの手順に正確に従ってください。 この構成は、高度なシナリオ用です。

#### Microsoft Entra Connect Sync からのスコープの削除を計画する

共存またはパイロット フェーズ中に、Microsoft Entra Connect Sync スコープから OU、グループ、またはドメインを削除しないでください。 この操作は、そのスコープの移行が完了し、Cloud Sync がオブジェクトとその参照に対して権限があることを確認した後にのみ行います。

Microsoft Entra Connect Sync からスコープを削除する前に、次のことを確認してください。

- そのスコープ内のすべてのオブジェクトは、適切なクラウド同期構成に含まれます。
- グループ メンバーやマネージャーなどの関連する参照対象オブジェクトも移行されるか、参照の維持のために Microsoft Entra Connect Sync に依存しなくなります。
- Microsoft Entra Connect Sync がコネクタ スペースから削除し、参照の削除としてエクスポートする可能性があるクロススコープ参照やクロスドメイン参照は残っていません。

Microsoft Entra Connect Sync からドメインを削除するのは、そのドメインの移行が完了した後で、ドメイン間の参照が残っていないと確信している場合のみです。

### 前提条件

このチュートリアルを完了するために必要な前提条件を次に示します。

- Microsoft Entra Connect 同期のバージョン 1.4.32.0 以降を備えたテスト環境
- 同期の対象範囲に含まれる OU またはグループで、パイロット中に使用可能です。 少数のオブジェクトから始めることをお勧めします。
- プロビジョニング エージェントをホストするために Windows Server 2022、Windows Server 2019、または Windows Server 2016 を実行するサーバー。
- Microsoft Entra Connect Sync のソース アンカーは*、objectGuid* または *ms-ds-consistencyGUID* である必要があります

### Microsoft Entra Connect を更新する

少なくとも、 [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) 1.4.32.0 が必要です。 Microsoft Entra Connect Sync を更新するには、「 [Microsoft Entra Connect: 最新バージョンにアップグレードする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)」の手順に従います。

### Microsoft Entra Connect 構成をバックアップする

変更を行う前に、Microsoft Entra Connect の構成をバックアップします。 これにより、以前の構成にロールバックできます。 詳細については、「 [Microsoft Entra Connect 構成設定のインポートとエクスポート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-import-export-config)」を参照してください。

### スケジューラの停止

Microsoft Entra Connect 同期は、オンプレミス ディレクトリで発生する変更を、スケジューラを使用して同期します。 カスタム ルールを変更して追加するには、作業中や変更中に同期が実行されないようにスケジューラを無効にする必要があります。 スケジューラを停止するには、次の手順に従います。

1. Microsoft Entra Connect Sync を実行しているサーバーで、管理者特権で PowerShell を開きます。
2. `Stop-ADSyncSyncCycle` を実行します。 Enter キーを押します。
3. `Set-ADSyncScheduler -SyncCycleEnabled $false` を実行します。

ノート

Microsoft Entra Connect Sync 用に独自のカスタム スケジューラを実行している場合は、カスタム同期スケジューラを無効にします。

### カスタム ユーザー受信規則を作成する

Microsoft Entra接続同期規則エディターで、パイロット用に識別した OU またはグループ内のユーザーに対して`cloudNoFlow`に`True`属性を設定する受信同期規則を作成します。 この属性は、このチュートリアルの後半で使用する `JoinNoFlow` 送信規則と共に使用され、Microsoft Entra Connect Sync がこれらのユーザーについてオブジェクトの追加、オブジェクトの削除、および非参照属性の更新をエクスポートしないようにします。 `member`や`manager`などの参照属性の更新は、引き続き参照解決のためにフローできます。

Important

`cloudNoFlow`属性は、オブジェクトを完全な読み取り専用モードまたはステージング モードに配置しません。 `JoinNoFlow`送信規則と共に使用すると、Microsoft Entra Connect Sync では、オブジェクトの追加、オブジェクトの削除、および参照以外の属性の更新がエクスポートされなくなります。 ただし、 `member` や `manager`などの参照属性の更新は、引き続きフローできます。 この動作では、移行中の参照解決がサポートされます。

グループ メンバーシップの削除などの意図しない参照削除を回避するには、`cloudNoFlow`と`JoinNoFlow`がアクティブな間は、Microsoft Entra Connect Sync スコープで参照の両側を保持します。 たとえば、グループが Microsoft Entra Connect Sync に残っていて、そのメンバーが Microsoft Entra Cloud Sync に移行される場合は、そのスコープの移行が完了するまで、グループ オブジェクトとメンバー オブジェクトの両方Microsoft Entra Connect Sync スコープに保持します。

1. デスクトップのアプリケーション メニューから同期規則エディターを開きます。

    [Image: [同期規則エディター] メニューを示すスクリーンショット。]
2. **[方向]** で、ドロップダウン リストから **[受信]** を選択します。 次に、[ **新しいルールの追加]** を選択します。

    [Image: [同期規則の表示と管理] ウィンドウ内で [受信] と [新しいルールの追加] ボタンが選択されていることを示すスクリーンショット。]
3. [ **説明** ] ページで、次の値を入力し、[ **次へ**] を選択します。

    - **名前**: ルールにわかりやすい名前を付けます。
    - **説明**: わかりやすい説明を追加します。
    - **接続システム**: カスタム同期規則を作成する Microsoft Entra コネクタを選択します。
    - **接続されたシステム オブジェクトの種類**: ユーザーを選択 **します**。
    - **メタバース オブジェクトの種類**: ユーザーを選択 **します**。
    - **リンクの種類**: **[結合**] を選択します。
    - **優先順位**: システム内で一意の値を指定します。
    - **タグ**: このフィールドは空のままにします。

    [Image: 値が入力された [受信同期規則の作成 - 説明] ページを示すスクリーンショット。]
4. [ **スコープ フィルター** ] ページで、パイロットのベースにする OU またはセキュリティ グループを入力します。 OU でフィルター処理するには、識別名の OU 部分を追加します。 この規則は、その OU に含まれるすべてのユーザーに適用されます。 そのため、識別名 (DN) が `OU=CPUsers,DC=contoso,DC=com`で終わる場合は、このフィルターを追加します。 **次へ**を選択します。

    | ルール | 属性 | オペレーター | 価値 |
    | --- | --- | --- | --- |
    | スコープ OU | `DN` | `ENDSWITH` | OU の識別名。 |
    | スコープ グループ |  | `ISMEMBEROF` | セキュリティ グループの識別名。 |

    [Image: 同期規則のスコープ フィルターを示すスクリーンショット。]
5. [ **結合** ルール] ページで、[ **次へ**] を選択します。
6. [変換] ページ **で** 、cloudNoFlow 属性の定数変換のソース値 True を追加します。 **追加**を選択します。

    [Image: 同期規則の変換を示すスクリーンショット。]

すべてのオブジェクトの種類 (ユーザー、グループ、連絡先) に対して同じ手順に従います。 構成されている Active Directory コネクタまたは Active Directory フォレストに従って、手順を繰り返します。

### カスタム ユーザー送信規則を作成する

リンクの種類が `JoinNoFlow` の送信同期規則と、 `cloudNoFlow` 属性が `True` に設定されているスコープ フィルターが必要です。 このルールは、Microsoft Entra Connect Sync に対して、これらのユーザーのオブジェクトの追加、オブジェクトの削除、または非参照属性の更新をエクスポートしないように指示します。 `member`や`manager`などの参照属性の更新は、引き続き参照解決のためにフローできます。

1. [ **方向**] で、ドロップダウン リストから [ **送信** ] を選択します。 次に、[ **ルールの追加]** を選択します。

    [Image: 送信同期規則を示すスクリーンショット。]
2. [ **説明** ] ページで、次の値を入力し、[ **次へ**] を選択します。

    - **名前**: ルールにわかりやすい名前を付けます。
    - **説明**: わかりやすい説明を追加します。
    - **接続システム**: カスタム同期規則を作成する Microsoft Entra コネクタを選択します。
    - **接続されたシステム オブジェクトの種類**: ユーザーを選択 **します**。
    - **メタバース オブジェクトの種類**: ユーザーを選択 **します**。
    - **リンクの種類**: **JoinNoFlow** を選択します。
    - **優先順位**: システム内で一意の値を指定します。
    - **タグ**: このフィールドは空のままにします。

    [Image: 同期規則の説明を示すスクリーンショット。]
3. [ **スコープ フィルター** ] ページで、[ **属性**] で **cloudNoFlow** を選択します。 **[値]** で [True] を選択**します**。 **次へ**を選択します。

    [Image: カスタム ルールを示すスクリーンショット。]
4. [ **結合ルール** ] ページで、[ **次へ**] を選択します。
5. [変換] ページ **で** 、[追加] を選択 **します**。

すべてのオブジェクトの種類 (ユーザー、グループ、連絡先) に対して同じ手順に従います。

### Microsoft Entra プロビジョニング エージェントをインストールする

[Basic Active Directory と Azure 環境](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-basic-ad-azure)のチュートリアルを使用している場合は、CP1 を使用します。 エージェントをインストールするには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. 左側のウィンドウで、[ **Entra Connect**] を選択し、[ **クラウド同期**] を選択します。

    [Image: [作業の開始] 画面を示すスクリーンショット。]
3. 左側のウィンドウで、[エージェント] を選択 **します**。
4. [ **オンプレミス エージェントのダウンロード**] を選択し、[ **同意する]** を選択してダウンロードします。

    [Image: エージェントのダウンロードを示すスクリーンショット。]
5. Microsoft Entra Connect プロビジョニング エージェント パッケージをダウンロードしたら、ダウンロード フォルダーから *AADConnectProvisioningAgentSetup.exe* インストール ファイルを実行します。
6. 開いた画面で、[ **ライセンス条項に同意** する] チェック ボックスをオンにし、[ **インストール**] を選択します。

    [Image: Microsoft Entra Provisioning Agent Package のライセンス条項を示すスクリーンショット。]
7. インストールが完了すると、構成ウィザードが開きます。 **[次へ**] を選択して構成を開始します。

    [Image: ようこそ画面を示すスクリーンショット。]
8. 少なくとも [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) ロールを持つアカウントでサインインします。 Internet Explorer のセキュリティ強化が有効になっている場合は、サインインがブロックされます。 その場合は、インストールを閉じ、 [Internet Explorer のセキュリティ強化を無効に](https://learn.microsoft.com/ja-jp/troubleshoot/developer/browsers/security-privacy/enhanced-security-configuration-faq)して、Microsoft Entra Provisioning Agent パッケージのインストールを再起動します。

    [Image: [Microsoft Entra ID の接続] 画面を示すスクリーンショット。]
9. [ **サービス アカウントの構成]** 画面で、グループの管理対象サービス アカウント (gMSA) を選択します。 このアカウントはエージェント サービスの実行に使用されます。 マネージド サービス アカウントが別のエージェントによってドメインに既に構成されていて、2 つ目のエージェントをインストールしている場合は、[ **gMSA の作成**] を選択します。 システムは既存のアカウントを検出し、gMSA アカウントを使用するために新しいエージェントに必要なアクセス許可を追加します。 メッセージが表示されたら、次の 2 つのオプションのいずれかを選択します。

    - **gMSA の作成**: エージェントが **provAgentgMSA$** マネージド サービス アカウントを作成できるようにします。 グループ管理サービス アカウント ( `CONTOSO\provAgentgMSA$` など) は、ホスト サーバーが参加したのと同じ Active Directory ドメインに作成されます。 このオプションを使用するには、Active Directory ドメイン管理者の資格情報を入力します (推奨)。
    - **カスタム gMSA の使用**: このタスク用に手動で作成したマネージド サービス アカウントの名前を指定します。

    [Image: グループの管理対象サービス アカウントを構成する方法を示すスクリーンショット。]
10. 続行するには、[ **次へ**] を選択します。
11. [ **Active Directory の接続** ] 画面で、[ **構成済みの**ドメイン] にドメイン名が表示される場合は、次の手順に進みます。 それ以外の場合は、Active Directory ドメイン名を入力し、[ **ディレクトリの追加]** を選択します。

    [Image: 構成済みのドメインを示すスクリーンショット。]
12. Active Directory ドメイン管理者アカウントでサインインします。 ドメイン管理者アカウントには、有効期限が切れたパスワードがあってはなりません。 エージェントのインストール中にパスワードの有効期限が切れている場合や変更された場合は、新しい資格情報を使用してエージェントを再構成します。 この操作によってオンプレミス ディレクトリが追加されます。 [ **OK] を**選択し、[ **次へ** ] を選択して続行します。
13. **[次へ**] を選択して続行します。
14. [ **構成の完了** ] 画面で、[確認] を選択 **します**。 この操作によって、エージェントが登録されて再起動されます。

    [Image: 完了画面を示すスクリーンショット。]
15. 操作が完了すると、エージェントの構成が正常に検証されたことを示す通知が表示されます。 [ **終了]** を選択します。 まだ最初の画面が表示される場合は、[ **閉じる**] を選択します。

### エージェントのインストールを確認する

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

プロビジョニングを構成するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **新しい構成]** を選択します。

    [Image: 構成の追加を示すスクリーンショット。]
2. 構成画面で、ドメインとパスワード ハッシュ同期を有効にするかどうかを選択します。次に、[ **作成**] を選択します。

    [Image: 新しい構成を示すスクリーンショット。]
3. [ **作業の開始** ] 画面で、[ **スコープ フィルターの追加** ] アイコンの横にある [ **スコープ フィルターの追加** ] を選択します。 または、左側のウィンドウの [ **管理**] で、[ **スコープ フィルター**] を選択します。

    [Image: スコープ フィルターを示すスクリーンショット。]
4. スコープ フィルターを選択します。 このチュートリアルでは、[ **選択した組織単位]** を選択します。 このフィルターは、特定の OU に適用する構成のスコープを設定します。
5. ボックスに、「 **OU=CPUsers,DC=contoso,DC=com」**と入力します。

    [Image: スコープ フィルターを示すスクリーンショット。]
6. [ **追加**&gt;**保存]** を選択します。

### スケジューラの開始

Microsoft Entra Connect Sync は、スケジューラを使用して、オンプレミスディレクトリで発生する変更を同期します。 ルールを変更したら、スケジューラを再起動できます。

1. Microsoft Entra Connect Sync を実行しているサーバーで、管理者特権で PowerShell を開きます。
2. `Set-ADSyncScheduler -SyncCycleEnabled $true` を実行します。
3. `Start-ADSyncSyncCycle` を実行します。 次に、「入力」を選択します。

ノート

Microsoft Entra Connect Sync 用に独自のカスタム スケジューラを実行している場合は、カスタム同期スケジューラを再度有効にします。

スケジューラが有効になった後、Microsoft Entra Connect Sync は、メタバース内の`cloudNoFlow=true`と送信規則で`JoinNoFlow`を使用するオブジェクトのオブジェクトの追加、オブジェクト削除、および非参照属性の更新のエクスポートを停止します。 参照属性の処理方法は異なります。 `member`や`manager`などの参照属性の更新は、引き続き参照解決のためにエクスポートできます。

オブジェクトが Microsoft Entra Connect Sync のスコープから削除された場合、そのコネクタ スペースとメタバース オブジェクトは削除されます。 そのオブジェクトとの間のすべての参照は、Microsoft Entra コネクタ スペースにドロップし、参照削除としてエクスポートできます。 この動作により、グループ メンバーシップが削除されたり、Microsoft Entra IDでその他の参照が変更されたりする可能性があります。

### トラブルシューティング

パイロットが期待どおりに動作しない場合は、Microsoft Entra Connect Sync のセットアップに戻ります。

1. ポータルでプロビジョニング構成を無効にします。
2. 同期規則エディター ツールを使用して、クラウド プロビジョニング用に作成したすべてのカスタム同期規則を無効にします。 無効にすると、すべてのコネクタで完全同期が実行されます。

#### グループ メンバーシップまたはマネージャー参照を削除しました

この問題は、関連するグループ、ユーザー、連絡先、またはその他の参照先オブジェクトがまだ共存している間に、Microsoft Entra Connect Sync スコープからオブジェクトが削除された場合に発生する可能性があります。 オブジェクトがスコープから削除されると、Microsoft Entra Connect Sync は対応するコネクタ スペースとメタバース オブジェクトを削除します。 この削除により、Microsoft Entra コネクタ スペース内の参照が削除され、参照削除が Microsoft Entra ID にエクスポートされる可能性があります。

回復するには、影響を受けるオブジェクトを Microsoft Entra Connect Sync のスコープに再追加し、完全同期を実行してコネクタ スペースとメタバースの参照を再構築します。 スコープを再度削除する前に、Microsoft Entra Cloud Sync が影響を受けるすべてのオブジェクトをプロビジョニングしていること、およびグループ メンバーシップやマネージャーの関係などの参照が、Cloud Sync プロビジョニング サイクルの後もそのまま残っていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/tutorial-single-forest"} -->
## チュートリアル - 単一のフォレストを単一の Microsoft Entra テナントに統合する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: このトピックでは、クラウド同期の前提条件とハードウェア要件について説明します。

このチュートリアルでは、Microsoft Entra Cloud Sync を使用してハイブリッド ID 環境を作成する手順を説明します。

[Image: Microsoft Entra Cloud Sync フローを示す図。]

このチュートリアルで作成した環境は、テストに使用したり、クラウド同期について理解を深めるために使用したりできます。

### 前提条件

#### Microsoft Entra 管理センターの場合

- Microsoft では、グローバル [管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられている 2 つのクラウド専用緊急アクセス アカウントを組織に付与することをお勧めします。 これらのアカウントは高い特権を持ち、特定の個人には割り当てられません。 アカウントは、通常のアカウントが使用できないか、すべての管理者が誤ってロックアウトされてしまうような「緊急対応」シナリオに限定されます。これらのアカウントは、[緊急アクセスアカウントの推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。
- Microsoft Entra テナントに 1 つ以上の [カスタム ドメイン名](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain) を追加します。 ユーザーは、このドメイン名のいずれかを使用してサインインできます。

#### オンプレミスの環境の場合

1. 4 GB 以上の RAM と .NET 4.7.1 以降のランタイムを搭載した、Windows Server 2016 以降が稼働するドメイン参加済みホスト サーバーを探す
2. サーバーと Microsoft Entra ID の間にファイアウォールがある場合は、次の項目を構成します:

    - エージェントが次のポートを介して Microsoft Entra ID に*アウトバウンド*要求を発行できることを確認します。

        | ポート番号 | 用途 |
        | --- | --- |
        | **80** | TLS/SSL 証明書を検証する際に証明書失効リスト (CRL) をダウンロードします |
        | **443** | サービスを使用したすべての送信方向の通信を処理する |
        | **8080** (省略可能) | ポート 443 が使用できない場合、エージェントは、ポート 8080 経由で 10 分おきにその状態をレポートします。 この状態はポータルに表示されます。 |

        ご利用のファイアウォールが送信元ユーザーに応じて規則を適用している場合は、ネットワーク サービスとして実行されている Windows サービスを送信元とするトラフィックに対してこれらのポートを開放します。
    - ファイアウォールまたはプロキシで安全なサフィックスを指定できる場合は、**\*.msappproxy.net と \*.servicebus.windows.net** に接続を追加します。 そうでない場合は、毎週更新される [Azure データセンターの IP 範囲](https://www.microsoft.com/download/details.aspx?id=41653)へのアクセスを許可します。
    - エージェントは、初期登録のために **login.windows.net** と **login.microsoftonline.com** にアクセスする必要があります。 これらの URL にもファイアウォールを開きます。
    - 証明書の検証では、**mscrl.microsoft.com:80、crl.microsoft.com:80**、**ocsp.msocsp.com:80** **、www.microsoft.com** ** :80** の URL のブロックを解除します。 他の Microsoft 製品でもこれらの URL を証明書の検証に使用しているので、URL のブロックを既に解除している可能性もあります。

### Microsoft Entra プロビジョニング エージェントをインストールする

[Basic AD と Azure 環境](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-basic-ad-azure)のチュートリアルを使用している場合は、DC1 になります。 エージェントをインストールするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. 左側のウィンドウで、[ **Entra Connect**] を選択し、[ **クラウド同期**] を選択します。

    [Image: [作業の開始] 画面を示すスクリーンショット。]
3. 左側のウィンドウで、[エージェント] を選択 **します**。
4. [ **オンプレミス エージェントのダウンロード**] を選択し、[ **同意する]** を選択してダウンロードします。

    [Image: エージェントのダウンロードを示すスクリーンショット。]
5. Microsoft Entra Connect プロビジョニング エージェント パッケージをダウンロードしたら、ダウンロード フォルダーから *AADConnectProvisioningAgentSetup.exe* インストール ファイルを実行します。
6. 開いた画面で、[ **ライセンス条項に同意** する] チェック ボックスをオンにし、[ **インストール**] を選択します。

    [Image: Microsoft Entra Provisioning Agent Package のライセンス条項を示すスクリーンショット。]
7. インストールが完了すると、構成ウィザードが開きます。 **[次へ**] を選択して構成を開始します。

    [Image: ようこそ画面を示すスクリーンショット。]
8. 少なくとも [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) ロールを持つアカウントでサインインします。 Internet Explorer のセキュリティ強化が有効になっている場合は、サインインが拒否されます。 その場合は、インストールを閉じ、 [Internet Explorer のセキュリティ強化を無効に](https://learn.microsoft.com/ja-jp/troubleshoot/developer/browsers/security-privacy/enhanced-security-configuration-faq)して、Microsoft Entra Provisioning Agent パッケージのインストールを再起動します。

    [Image: [Microsoft Entra ID の接続] 画面を示すスクリーンショット。]
9. [ **サービス アカウントの構成]** 画面で、グループの管理対象サービス アカウント (gMSA) を選択します。 このアカウントはエージェント サービスの実行に使用されます。 マネージド サービス アカウントが別のエージェントによってドメインに既に構成されていて、2 つ目のエージェントをインストールしている場合は、[ **gMSA の作成**] を選択します。 システムは既存のアカウントを検出し、gMSA アカウントを使用するために新しいエージェントに必要なアクセス許可を追加します。 メッセージが表示されたら、次の 2 つのオプションのいずれかを選択します。

    - **gMSA の作成**: エージェントが **provAgentgMSA$** マネージド サービス アカウントを作成できるようにします。 グループ管理サービス アカウント ( `CONTOSO\provAgentgMSA$` など) は、ホスト サーバーが参加したのと同じ Active Directory ドメインに作成されます。 このオプションを使用するには、Active Directory ドメイン管理者の資格情報を入力します (推奨)。
    - **カスタム gMSA の使用**: このタスク用に手動で作成したマネージド サービス アカウントの名前を指定します。

    [Image: グループの管理対象サービス アカウントを構成する方法を示すスクリーンショット。]
10. 続行するには、[ **次へ**] を選択します。
11. [ **Active Directory の接続** ] 画面で、[ **構成済みの**ドメイン] にドメイン名が表示される場合は、次の手順に進みます。 それ以外の場合は、Active Directory ドメイン名を入力し、[ **ディレクトリの追加]** を選択します。

    [Image: 構成済みのドメインを示すスクリーンショット。]
12. Active Directory ドメイン管理者アカウントでサインインします。 ドメイン管理者アカウントのパスワードは有効期限が切れていないものである必要があります。 エージェントのインストール中にパスワードの有効期限が切れている場合や変更された場合は、新しい資格情報を使用してエージェントを再構成します。 この操作によってオンプレミス ディレクトリが追加されます。 [ **OK] を**選択し、[ **次へ** ] を選択して続行します。
13. **[次へ**] を選択して続行します。
14. [ **構成の完了** ] 画面で、[確認] を選択 **します**。 この操作によって、エージェントが登録されて再起動されます。

    [Image: 完了画面を示すスクリーンショット。]
15. 操作が完了すると、エージェントの構成が正常に検証されたことを示す通知が表示されます。 [ **終了]** を選択します。 まだ最初の画面が表示される場合は、[ **閉じる**] を選択します。

### エエージェントのインストールを確認する

エージェントの検証は、Azure portal と、エージェントを実行するローカル サーバーで行われます。

#### Azure portal でエージェントを確認する

Microsoft Entra ID によってエージェントが登録されていることを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
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

### Microsoft Entra Cloud Sync を構成する

プロビジョニングを構成して開始するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. **新しい構成**&gt;**AD から Microsoft Entra ID 同期**を選択します。
2. 同期するドメインを選択し、[ **作成**] を選択します。

Microsoft Entra Cloud Sync を構成する方法の詳細については、「 [Microsoft Entra ID への Active Directory のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)」を参照してください。

### ユーザーが作成され、同期が実行されていることを確認する

ここで、同期範囲内にあるオンプレミス ディレクトリに存在していたユーザーが同期され、現在は Microsoft Entra テナントに存在することを確認します。 この同期操作が完了するまで数時間かかることがあります。 ユーザーが同期されていることを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. テナントに新しいユーザーが表示されることを確認する

### いずれかのユーザーでサインインをテストする

1. https://myapps.microsoft.com に移動します
2. テナントで作成されたユーザー アカウントを使用してサインインします。 user@domain.onmicrosoft.com の形式を使用してサインインする必要があります。 ユーザーがオンプレミスでのサインインに使用するのと同じパスワードを使用します。

[Image: サインインしているユーザーを含むマイ アプリ ポータルを示すスクリーンショット。]

これで Microsoft Entra Cloud Sync を使用するハイブリッド ID 環境が正常に構成されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/tutorial-users-groups-provisioning-walkthrough"} -->
## チュートリアル: オンプレミス アプリへのアクセスを管理する (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-users-groups-provisioning-walkthrough
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-20
- Summary: クラウド で管理されるユーザーとセキュリティ グループをプロビジョニングして、Microsoft Entra Cloud Sync を使用してActive Directoryし、クラウド グループメンバーが Kerberos アプリにサインインできるようにします。

Contoso は、オンプレミスで経費アプリケーションを実行します。 アプリケーションはWindows統合認証を使用するため、Kerberos でユーザーを認証し、Active Directory Domain Services (AD DS) セキュリティ グループのメンバーシップを確認して承認します。

ID チームは、Microsoft Entra IDのユーザーを管理し、クラウドからそのアプリケーションへのアクセスを管理したいと考えています。クラウド グループにユーザーを追加すると、経費アプリを使用できます。 Microsoft Entra IDの **Expense 承認者**グループは既に適切なユーザーを保持していますが、そのメンバーシップは混在しています。 一部のメンバーは AD DS から同期し、一部はクラウドで作成され、AD DS アカウントを持っていませんでした。

その組み合わせが問題です。 メンバー参照は、メンバーが指す AD DS アカウントを持っている場合にのみ、AD DS グループに書き込むことができます。 同期されたメンバーは1人です。 クラウドで管理されるメンバーは見つからないため、アプリケーションで見られないため、経費を承認できません。

このチュートリアルでは、クラウドマネージド ユーザーをグループとそのメンバーシップと共に AD DS にプロビジョニングします。 アプリケーションは常に持っている方法を承認し続けますが、Microsoft Entra IDはアクセス権を取得するユーザーを決定する場所になります。

このチュートリアルでは、次の操作を行います。

- 関係するオブジェクトと、クラウドで管理されているメンバーが現在アクセス権を持たない理由を確認します。
- プロビジョニング構成を作成し、グループをスコープに取り込みます。
- 何かを有効にする前に、オンデマンド プロビジョニングで結果を検証します。
- AD DS で、アカウントとグループ メンバーシップが作成されたことを確認します。
- クラウド管理ユーザーが AD DS パスワードなしでアプリケーションにサインインする方法について説明します。
- プロビジョニングされたオブジェクトをロックして、Microsoft Entra IDからのみ変更できるようにします。
- ユーザーまたはグループをオンプレミスの管理に戻します。

Important

Active Directoryへの**ユーザー**のプロビジョニングは現在プレビュー段階です。

### Prerequisites

[前提条件とライセンス要件を完了します](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-prerequisites-provision-entra-to-active-directory)。

### このシナリオのオブジェクト

このチュートリアルでは、次のオブジェクトを使用します。 自分のテナント内の対応するものに置き換えます。

| Object | タイプ | 開始状態 |
| --- | --- | --- |
| Britta Simon | AD DS から同期されたユーザー | AD DS アカウントを持っています。 既に経費アプリに到達しています。 |
| ローラ・ジェイコブソン | AD DS から同期されたユーザー | AD DS アカウントを持っています。 既に経費アプリに到達しています。 |
| Ada Whitfield | ユーザー、クラウド管理対象、`department` は `Finance` | AD DS アカウントがありません。 経費アプリに到達できません。 |
| マルコ トレヴィサン | ユーザー、クラウド管理対象、`department` は `Finance` | AD DS アカウントがありません。 経費アプリに到達できません。 |
| 経費承認者 | Microsoft Entra IDのセキュリティ グループ | 4 人のユーザーすべてを含みます。 AD DS には存在しません。 |

AD DS には、次の組織単位 (OU) が存在します。

| 表示名称 | 識別名 |
| --- | --- |
| Finance | OU=Finance,DC=contoso,DC=com |
| Groups | OU=グループ,DC=contoso,DC=com |

### 現在不足しているものを理解する

何かを変える前に、埋めようとしているギャップを確認してください。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/en-us/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**グループ**&gt;**すべてのグループ**に移動し、[**経費承認者**] を選択します。
3. [ **メンバー]** を選択し、AD DS から同期するメンバーとクラウドで管理されているメンバーをメモします。 **オンプレミスの同期が有効な列によって**区別されます。
4. オンプレミス環境にサインインし、**Active Directory ユーザーとコンピューター**を開きます。 Ada Whitfield と MarcoTreysan にアカウントがなく、 **経費承認者** グループが存在しないことを確認します。

この時点で、クラウド グループは、経費を承認する必要があるユーザーの記録システムですが、AD DS はそれに対処できません。

### プロビジョニング構成を作成する

ドメインは 1 つの構成をサポートしているため、それぞれに個別の構成を作成するのではなく、両方のオブジェクトの種類を 1 つの構成に取り込みます。

1. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。
2. **新しい構成**&gt;を選択します。
3. ドメインを選択し、[ **作成**] を選択します。

[ **作業の開始** ] 画面が開きます。

### グループとそのメンバーをスコープに取り込む

スコープによってプロビジョニングされるオブジェクトが決まります。定義する方法は 2 つあります。 また、属性値のフィルター処理を使用できるかどうかも決定されます。

| Scope | 次の場合に選択します。 |
| --- | --- |
| **選択したユーザーとグループ** | 最高のパフォーマンスと最速の同期サイクルが必要です。 サービスは選択したオブジェクトのみを評価するため、各サイクルの処理が少なくなります。 このモードでは属性値フィルターを追加しないでください。選択によってスコープが既に決定されます。 |
| **すべてのユーザーとグループ** | スコープを大規模に設定する必要があります。または、一連のオブジェクトが時間の経過と同時に変化します。 属性値のフィルター処理では、ユーザーが参加、移動、退出する際にスコープを最新の状態に保ちます。手動で選択したリストは保持しません。 常に、このモードで少なくとも 1 つの属性フィルターを追加します。 |

テナント サイズのしきい値と、各モードでサポートされるオブジェクト数については、「 [パフォーマンスとスケールの制限」](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/concept-deployment-options-provision-to-active-directory#performance-and-scale-limits)を参照してください。

[**スコープ フィルターの追加]** を選択するか、左側の [**管理**] で **[スコープ フィルター**] を選択し、[**編集]** を選択します。 次に、選択したタブに従います。

## [選択したユーザーとグループ](#tab/selected)
グループを選択すると、グループがスコープに入り、そのメンバーがグループと共にプロビジョニングされます。

1. [ **割り当てによるスコープ** ] タブで、スコープを **[選択したユーザーとグループ]** に設定します。
2. **[経費承認者**] グループを選択します。

選択したオブジェクトのみが各サイクルで評価されるため、同期する最も高速なオプションです。 後で他のユーザーを追加するには、Microsoft Entra IDのグループに追加します。

## [すべてのユーザーとグループ](#tab/all)
属性値によるフィルター処理では、属性値に基づいてスコープが決まるため、スコープはメンバーの変更に自動的に追随します。

1. [ **割り当てによるスコープ** ] タブで、スコープを **[すべてのユーザーとグループ**] に設定し、[ **次へ** ] を選択して [ **属性別のスコープ** ] タブを開きます。
2. **[ユーザー**] オブジェクトの種類で、[**属性スコープフィルターの追加]** を選択し、次の構成を行います。

    - **名前**: `Finance users`
    - **属性**: `department`
    - **オペレーター**: `EQUALS`
    - **値**: `Finance`

    Ada Whitfield と MarcoTreysan は、クラウドで管理されているため、フィルターと一致し、プロビジョニングされます。 Britta Simon と Lola Jacobson は、ソース オブ オーソリティ (SOA) がオンプレミスであり、Active Directoryが引き続き権限を持っているため、それらが一致してもプロビジョニングされません。 グループが参照する AD DS アカウントが既に存在します。
3. [ **グループ** ] オブジェクトの種類で、グループを選択するフィルターを追加します ( **例: displayName**`EQUALS``Expense approvers`。
4. **保存**を選びます。

1 つのフィルター内の句は `AND`と結合され、複数のフィルターは `OR`と結合されます。 演算子の一覧と正規表現のフィルター処理については、「 [属性値のフィルター処理](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#attribute-value-filtering)」を参照してください。

---

どのスコープを選択した場合でも、ウィザードを完了してください。

1. [ **グループ メンバーシップの構成]** ステップで、オンプレミス ユーザーへのメンバーシップのプロビジョニングを有効にします。

    Important

    この設定は既定ではオフになっています。 それがなければ、Britta Simon と Lola Jacobson のメンバー参照は AD DS グループに書き込まれません。 詳細については、「 [オンプレミス ユーザーへのグループ メンバーシップ](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#group-membership-to-on-premises-users)」を参照してください。
2. ターゲット コンテナーを設定します。

    - **ユーザー** — クラウド管理ユーザーには `onPremisesDistinguishedName`がないため、既定のコンテナー `CN=Users,DC=contoso,DC=com`に作成されます。 それを `OU=Finance,DC=contoso,DC=com` で上書きして、Finance の他の項目と一緒に配置します。
    - **グループ** - `OU=Groups,DC=contoso,DC=com`の定数`parentDistinguishedName`を設定します。

    Note

    これらの既定値は、目的に応じてオブジェクトの種類によって異なります。 ソースオブオーソリティがクラウドに変換されたユーザーは、既定のマッピングが `onPremisesDistinguishedName`を読み取るため、自動的に元の OU に戻ります。 グループには同等のものがないため、変換されたグループを既に占有している OU に保持するには、「 [グループの組織単位と名前を保持](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-preserve-group-organizational-unit-entra-to-active-directory)する」を参照してください。
3. **保存**を選びます。

### オンデマンド プロビジョニングを使用して検証する

有効にする前にテストします。 オンデマンド プロビジョニングでは、構成が少数のオブジェクト セットに適用され、ジョブをオンにすることなく、エンジンが実行した各ステップが表示されます。

1. 設定を開き、**オンデマンド プロビジョニング**を選択します。
2. **Ada Whitfield** を選択し、[**プロビジョニング**] を選択します。
3. ステップの結果を確認します。 インポート、スコープの決定、照合、およびエクスポートの手順はすべて成功し、エクスポート 手順には作成アクションが表示されます。
4. **経費承認者**グループに対して繰り返します。

オブジェクトがスキップされた場合、スコープ決定手順で理由が説明されます。 完全な手順については、「 [プロビジョニングのテストと有効化](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-test-and-enable-provisioning-entra-to-active-directory)」を参照してください。

### 構成を有効にする

オンデマンドの結果が正しく表示されたら、 **概要**&gt;**Review を選択し**&gt;**構成を有効**にします。 その後、プロビジョニングは定期的なスケジュールで実行されます。

### AD DS で結果を確認する

シナリオの両方の半分が着陸したことを確認します。

1. **Active Directory ユーザーとコンピューター**で、`OU=Finance,DC=contoso,DC=com`を開きます。 エイダ・ホイットフィールドとマルコ・トレヴィサンに AD DS アカウントが追加されました。
2. `OU=Groups,DC=contoso,DC=com` を開きます。 **経費承認者**グループが存在します。
3. グループの **[メンバー] タブを** 開きます。次の 4 つのユーザーがすべて表示されます。

    - Britta Simon と Lola Jacobson は、既に AD DS アカウントを持っているためです。
    - Ada Whitfield と Marco Trevisan は、プロビジョニングによってアカウントが作成されたことで、メンバー参照フィールドが書き込み可能になったためです。

**結果:** クラウド グループが AD DS での承認を推進するようになりました。 Microsoft Entra IDの **Expense 承認者**に他のユーザーを追加すると、そのユーザーがプロビジョニングされ、次のサイクルでアクセスが許可されます。 削除すると逆になります。

### クラウドマネージド ユーザーがアプリケーションにサインインする方法

Kerberos が機能するように、プロビジョニングされた AD DS アカウントが存在します。 ユーザーが個別に管理する 2 つ目の ID ではなく、AD DS パスワードは必要ありません。

Ada Whitfield は、Windows Hello for Businessや FIDO2 セキュリティ キーなどのパスワードなしの方法でMicrosoft Entra IDにサインインします。 [Cloud Kerberos Trust](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/hybrid-cloud-kerberos-trust) が構成されている場合、Microsoft Entra IDはオンプレミス ドメインの Kerberos チケットを発行し、経費アプリケーションはそれを受け入れ、プロビジョニングが維持する AD DS グループのメンバーシップを通じて承認します。

認証はクラウドで行われるため、オンプレミス アクセスでは多要素認証と条件付きアクセス ポリシーも選択されます。

Important

これは、Kerberos を使用するアプリケーションで機能します。 パスワードを直接必要とするアプリケーション (LDAP バインドやパスワードを使用した Kerberos など) は、クラウドで管理されるユーザーではサポートされていません。AD DS パスワードがないためです。

### 変更がMicrosoft Entra IDからのみ反映されるようにオブジェクトをロックする

プロビジョニングにより、Microsoft Entra IDアクセスを決定できますが、オンプレミスの管理者は引き続き AD DS でプロビジョニングされたアカウントまたはグループを直接編集できます。 AD オブジェクトの適用によってそのギャップが埋められます。そのため、マークされたオブジェクトはプロビジョニング サービスからの変更のみを受け入れます。

1. ドメイン コントローラーを準備し、適用ポリシーをインストールします。 これはドメインごとに 1 回限りセットアップされるため、書き込み可能なすべてのドメイン コントローラーを使用する前に有効にする必要があります。 [「AD ユーザーとグループの強制の構成」](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-active-directory-object-enforcement)を参照してください。
2. プロビジョニングされたユーザーと **経費承認者** グループに適用のマークを付けます。
3. **Active Directory ユーザーとコンピューター**でグループの名前を変更してみてください。 変更がブロックされ、試行がイベント ログに記録されます。

Tip

最初に **監査** モードでポリシーをインストールします。 監査モードでは、何もブロックせずにブロックされた内容が記録されるため、 **強制**に切り替える前に、これらのオブジェクトに書き込むスクリプトとスケジュールされたタスクを見つけることができます。

強制が有効な場合、Microsoft Entra ID は、そのオブジェクトについて、慣例上だけでなく実際にも唯一の正本となる情報源です。

### クラウドからユーザー ライフサイクルを管理する

このチュートリアルは、Microsoft Entra IDに既に存在するユーザーから始まります。 完全なデプロイでは、ライフサイクルが前に開始され、AD DS へのプロビジョニングが、人事システムで始まるチェーンの最後のステップになります。

1. **Joiner。** 人事システムによってユーザーがMicrosoft Entra IDにプロビジョニングされるため、HR レコードは誰が存在するかの信頼の源になります。 [人事主導のプロビジョニングを](https://learn.microsoft.com/en-us/entra/identity/app-provisioning/what-is-hr-driven-provisioning)参照してください。
2. **アクセスの割り当て。**[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) により、ユーザーにアクセス パッケージが付与されるか、動的グループ ルールによって追加され、 **経費承認者**に配置されます。
3. **AD DS へのプロビジョニング。** ユーザーは AD DS で作成され、メンバー参照が書き込まれるため、経費アプリケーションによって承認されます。
4. **Mover。** HR の属性変更は、次回のサイクルで Microsoft Entra ID に反映され、その後 AD DS に反映されます。 変更によってグループから削除された場合、そのアプリケーション アクセスも削除されます。
5. **Leaver。** HR は終了をマークし、 [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) はオフボード タスクを実行し、AD DS アカウントは無効になります。 完全な削除と無効化の動作については、「 [削除のしくみ](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-provisioning-to-active-directory-works#how-deletes-work)」を参照してください。

2 つのガバナンス コントロールは、この実行後に追加する価値があります。

- [アクセス レビューは](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)定期的にメンバーシップの妥当性を再確認し、削除は自動的にアプリケーションに反映されます。
- アクセス パッケージでグループを発行すると、管理者に電子メールを送信する代わりに、経費承認者のアクセス権を要求できます。

その結果、このライフサイクルの一部で管理者がActive Directoryに触れる必要はありません。 このパターンの詳細については、「[Microsoft Entra ID ガバナンス を使用したオンプレミスの Active Directory ベースのアプリ (Kerberos) の管理](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/govern-on-premises-groups)」を参照してください。

### ユーザーまたはグループをオンプレミスの管理に戻す

オブジェクトの権限ソースをクラウドに変更し、再び AD DS が管理するように戻したい場合は、その変更をロールバックします。 プロビジョニングによってオブジェクトの同期が停止され、スコープから削除されます。 オンプレミス オブジェクト **は削除されず**、次の同期サイクルでオンプレミスの制御が再開されます。

1. AD DS がそのオブジェクトを再び管理できるように、オブジェクトの Source of Authority を元に戻します。
2. **監査ログ**で、同期がオンプレミスで管理されているため、オブジェクトに対して実行されなくなったことを確認します。
3. **Active Directory ユーザーとコンピューター**で、オブジェクトがまだ存在し、削除されなかったことを確認します。

オブジェクトは構成スコープから自動的に削除されます。 手動でスコープを変更する必要はありません。

機関のソースを変更せずにオブジェクトのプロビジョニングを停止するには、代わりに構成のスコープからオブジェクトを削除します。

### プロビジョニングによって生成される内容

- オブジェクトは、スコープと属性のマッピングに基づいて、ターゲット OU で作成または照合および更新されます。
- 定期的なスケジュールでの AD DS へのMicrosoft Entra ID フローの変更。 ソースオブオーソリティ変換されたオブジェクトの場合、AD DS でマネージド属性を直接変更すると、Cloud Sync によってオブジェクトがスキップされます。 詳細については、「クラウドは [SOA 変換後に AD で行われた変更をスキップ](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory#cloud-skips-changes-made-in-ad-after-soa-conversion)する」を参照してください。
- 完全一致、更新、および削除の動作については、「[Active Directoryへのプロビジョニングのしくみ](https://learn.microsoft.com/en-us/entra/identity/hybrid/cloud-sync/how-provisioning-to-active-directory-works)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/what-is-cloud-sync"} -->
## Microsoft Entra Cloud 同期とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-02-17
- Summary: Microsoft Entra Cloud 同期について説明します。

Microsoft Entra Cloud Sync は、Active Directory と Microsoft Entra ID の間のユーザー、グループ、および連絡先の最新のクラウド管理同期を提供するハイブリッド ID 同期サービスです。 これは、ハイブリッド ID に対する Microsoft の戦略的な方向性を表し、デプロイと管理を簡素化しながら、切断されたフォレストの同期などの高度なシナリオを可能にする、軽量のエージェントベースのアプローチを提供します。

Cloud Sync は、単一障害点を排除し、オンプレミスの管理オーバーヘッドを削減し、組織の成長と変化をサポートする複雑なマルチフォレスト シナリオを可能にすることで、ハイブリッド ID インフラストラクチャで組織が直面する一般的な課題を解決します。

注

クラウド同期は、Microsoft Commercial、US Government、 [および 21Vianet (China)](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/office-365-platform-service-description/microsoft-365-operated-by-21vianet) クラウドのテナントに使用できます。 オンプレミス ID の SSPR は、21 Vianet (中国) クラウドのクラウド同期でまだ使用できません。

### コア アーキテクチャとコンポーネント

クラウド同期は、次の 2 つの主要なコンポーネントを備えた最新のクラウドファースト アーキテクチャに基づいて構築されています。

**Microsoft Entra プロビジョニング エージェント**: Active Directory と Microsoft Entra ID の間のセキュリティで保護されたブリッジとして機能する軽量のオンプレミス エージェント。 エージェントは、Microsoft Entra アプリケーション プロキシおよび Pass-Through 認証と同じ実証済みのテクノロジを使用します。送信接続のみを必要とし、クラウドから自動更新を提供します。

**Microsoft Entra プロビジョニング サービス**: 同期の構成、スケジュール、および処理を管理するクラウドベースのオーケストレーション サービス。 このサービスは、他の Microsoft Entra プロビジョニング機能と同じインフラストラクチャを使用し、2 分ごとに同期が行われているスケジューラ ベースのモデルで動作します。

[Image: 基本的なクラウド同期の図。]

### クラウド同期のしくみ

クラウド同期では、System for Cross-domain Identity Management (SCIM) 標準を利用して、信頼性と標準ベースの ID 管理を確保します。 同期プロセスは、次のフローに従います。

- **エージェント通信**: プロビジョニング エージェントは、Azure Service Bus を介して Microsoft Entra サービスへの永続的な送信接続を維持し、同期要求をリッスンします。
- **要求処理**: 同期が行われると、エージェントはクラウド サービスから SCIM 要求を受け取り、Active Directory に要求された情報を照会します。
- **データのフィルター処理と変換**: エージェントは、Microsoft Entra ID に応答を送信する前に、構成されたスコープ ルールと属性マッピングに基づいてデータをフィルター処理および変換します。
- **クラウド処理**: プロビジョニング サービスは、応答を処理し、Microsoft Entra ID への変更をコミットし、同期状態を維持し、透かし追跡によって増分更新を処理します。

### 主な機能

| 能力 | Description |
| --- | --- |
| クラウド管理の構成 | すべての同期構成は、Microsoft Entra 管理センターを通じて Microsoft Entra ID に格納および管理されます。 管理者は、設定の変更、状態の監視、VPN アクセスのない任意の場所からの問題のトラブルシューティングを行うことができます。 |
| マルチエージェントサポートによる高可用性 | クラウド同期では、異なるサーバーにデプロイされた複数のアクティブなプロビジョニング エージェントがサポートされ、構成を変更せずに自動フェールオーバーが提供されます。 個々のエージェントが使用できなくなった場合、同期はシームレスに続行されます。 |
| 切断されたフォレストの同期 | クラウド同期は、複雑な構成や複数の同期インスタンスを必要とせずに、複数の切断された Active Directory フォレストをネイティブに処理します。 この機能は、合併と買収、過去のマルチフォレスト環境、フォレストを接続できないシナリオをサポートします。 |
| インストールと管理の簡素化 | 軽量エージェント モデルでは、最小限のサーバー リソースが必要であり、ドメイン コントローラーまたは専用サーバーにデプロイできます。 エージェントは、Microsoft からセキュリティ更新プログラムとパッチを自動的に受け取ります。 |
| 高度なプロビジョニング シナリオ | クラウド同期を使用すると、オンプレミス アプリケーションを管理するための Active Directory へのグループ プロビジョニングなど、クラウドとto-AD のプロビジョニング シナリオが可能になります。 この双方向機能は、Microsoft Entra ID が権限のある ID ソースとして機能する最新の ID アーキテクチャをサポートします。 |

### Microsoft Entra Cloud 同期と Microsoft Entra Connect 同期の違い

Microsoft Entra Cloud Sync では、プロビジョニング オーケストレーションは、オンプレミスインフラストラクチャではなく、Microsoft Online Services で完全に行われます。 組織は、Microsoft Entra ID と Active Directory の間のセキュリティで保護されたブリッジとして機能するオンプレミスまたはサービスとしてのインフラストラクチャ (IaaS) 環境に軽量エージェントをデプロイします。 すべてのプロビジョニング構成、監視、および管理はクラウド サービスを通じて処理されるため、オンプレミスの同期サーバー管理の複雑さが解消されます。

このアーキテクチャの違いにより、複数の切断されたフォレストからフォレストを統合せずに 1 つの Microsoft Entra テナントに同期するなど、従来のアプローチでは複雑または不可能なシナリオが可能になります。

詳細な機能の比較表については、「 [Cloud Sync と Microsoft Entra Connect の機能の比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)」を参照してください。

### Microsoft Entra Cloud 同期ビデオ

次の短いビデオでは、Microsoft Entra クラウド同期の概要をわかりやすく説明しています:

### クラウド同期を検討するタイミング

Cloud Sync は、ハイブリッド ID インフラストラクチャを最新化し、従来の同期アプローチでは効果的にサポートできないシナリオを実現するように設計されています。 主なシナリオは次のとおりです。

- 切断されたフォレストの迅速な統合を必要とする合併と買収を受けている組織
- 単一障害点の排除と同期の信頼性の向上を目指す企業
- 管理の簡素化とオンプレミス インフラストラクチャの削減を必要とする環境
- フォレストの統合が実現できない複数フォレスト組織
- クラウドファーストのID戦略を実施し、クラウドからADへのプロビジョニングニーズを持つ組織

Microsoft Entra Connect からの移行を評価したり、新しいハイブリッド ID ソリューションを実装したりする組織では、Cloud Sync にはアーキテクチャ上の大きな利点があります。 機能の比較、移行計画、準備状況の評価の詳細については、「 [Microsoft Entra Connect からクラウド同期への移行: 意思決定ガイド](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide)」を参照してください。

### Microsoft Entra エコシステムとの統合

Cloud Sync は、より広範な Microsoft Entra ID プラットフォームとシームレスに統合され、次のような高度なシナリオをサポートします。

- **Microsoft Entra ID ガバナンス**: ハイブリッド ID のライフサイクル管理とアクセス ガバナンスの有効化
- **ソース オブ オーソリティ (SOA) 管理**: Microsoft Entra ID が信頼できる ID ソースになるクラウドファーストの ID 戦略のサポート
- **人事主導のプロビジョニング**: HR アプリケーション統合と連携してエンドツーエンドの ID プロビジョニング ワークフローを作成する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/cloud-sync/what-is-provisioning-agent"} -->
## プロビジョニング エージェントとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-provisioning-agent
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、クラウド同期とオンプレミス アプリ プロビジョニングで使用されるプロビジョニング エージェントについて説明します。

プロビジョニング エージェントは、Microsoft Entra ID で使用するいくつかの機能を提供するために使用される同期ツールであり、クラウドから管理されます。

プロビジョニング エージェントは、Microsoft Entra ID とオンプレミス環境とを接続する役割を担います。

次のような機能があります。

- クラウド同期
- オンプレミス アプリのプロビジョニング

### 動作方法

プロビジョニング エージェントは、SCIM ([クロスドメイン ID 管理システム (SCIM) 2.0](https://techcommunity.microsoft.com/t5/identity-standards-blog/provisioning-with-scim-getting-started/ba-p/880010)) を使用します。 SCIM 仕様では、ユーザーのアプリへの移動、アプリからの移動、アプリ内での移動に対し、共通のユーザー スキーマが提供されています。 SCIM は、プロビジョニングに対する事実上の標準になりつつあり、SAML や OpenID Connect などのフェデレーション標準と組み合わせて使用すると、エンドツーエンドの標準ベースのアクセス管理用ソリューションが管理者に提供されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/common-scenarios"} -->
## Microsoft Entra ID を使用した一般的なハイブリッド シナリオ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra クラウド同期と Microsoft Entra Connect の使用に関する一般的なシナリオについて説明します。

次のドキュメントでは、サポートされている一般的なハイブリッド同期シナリオについて説明します。

### サポートされている同期シナリオ

次の表に、サポートされている一般的な同期シナリオの概要を示します。

| シナリオ | クラウド同期でのサポート | Connect 同期でのサポート | MIM と Graph Connector でのサポート | ECMA ホスト コネクタでのサポート |
| --- | --- | --- | --- | --- |
| ハイブリッドを新規で使用する顧客による ID 管理 | ● | ● | ● | 対象外 |
| 合併と買収 (切断されたフォレスト) | ● | 対象外 | ● | 対象外 |
| 高可用性 - 待機時間 (高可用性が必要) | ● | 対象外 | ● | 対象外 |
| Connect 同期からクラウド同期への移行 | ● | ● | 対象外 | 対象外 |
| Microsoft Entra ハイブリッド参加 | 対象外 | ● | 対象外 | 対象外 |
| Exchange ハイブリッド | ● | ● | 対象外 | 対象外 |
| 1 つのフォレスト内のユーザー アカウント / リソース フォレスト内のメールボックス | 対象外 | ● | 対象外 | 対象外 |
| オブジェクトが 250,000 個以上ある大規模ドメインの同期 | 対象外 | ● | ● | 対象外 |
| 属性値に基づくディレクトリ オブジェクトのフィルタリング | 対象外 | ● | ● | 対象外 |
| Windows Hello for Business | 対象外 | ● | 対象外 | 対象外 |
| クラウドからオンプレミス AD への同期 | 対象外 | 対象外 | ● | 対象外 |
| クラウドからオンプレミス LDAP への同期 | 対象外 | 対象外 | ● | ● |
| クラウドからオンプレミス SQL への同期 | 対象外 | 対象外 | ● | ● |

### サポートされているプロビジョニング シナリオ

次の表に、サポートされている一般的なプロビジョニング シナリオの概要を示します。

| シナリオ | クラウド同期でのサポート | Connect 同期でのサポート | MIM と Graph Connector でのサポート | ECMA ホスト コネクタでのサポート |
| --- | --- | --- | --- | --- |
| Active Directory へのグループ プロビジョニング | ● | 対象外 | ● | 対象外 |

詳細については、「[クラウド同期でサポートされているトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/plan-cloud-sync-topologies)」と「[Connect 同期でサポートされているトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies)」をご覧ください。

### 追加情報

- 次の場合は、Connect 同期とクラウド同期を使って、同じドメインからユーザーとグループを同期できます。
    - 個々の同期のスコープ フィルターが相互に排他的である場合
    - 包括的である場合は、同じ属性値が競合していないこと (優先順位はサポートされていません)
- クラウド同期の新機能 (\*ロードマップに記載) の使用中に、Connect 同期でユーザーとグループを同期できます
- 書き戻し機能が 1 つの Microsoft Entra テナントでのみ有効になっている場合は、1 つの AD から複数の Azure AD にオブジェクトを同期できます。

### クラウド同期と Connect 同期の併用

クラウド同期と Microsoft Entra Connect を同じフォレストで実行できます。 たとえば 80% をクラウド同期で処理し、比較的複雑な 20% のシナリオの一部に Microsoft Entra Connect を使用することが考えられます。 チュートリアル「[同期された既存の Active Directory フォレストの Microsoft Entra クラウド同期に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp)」に、それぞれの実行方法の例が示されています。

### 一般的な認証方法とシナリオ

ハイブリッド ID シナリオでは、3 つの認証方法のいずれかを使用します。 次に 3 つの方法を示します。

- **[パスワード ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)**
- **[パススルー認証 (PTA)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)**
- **[フェデレーション (AD FS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed)**

これらの認証方法でも、[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)機能が提供されます。 シングル サインオンでは、ユーザーが企業ネットワークに接続される会社のデバイスを使用するときに、自動的にサインインを行います。

詳細については、「[Microsoft Entra ハイブリッド ID ソリューションの適切な認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)」をご覧ください。

| 必要事項 | PHS と SSO | PTA と SSO | フェデレーション |
| --- | --- | --- | --- |
| 新しいユーザー、連絡先、およびオンプレミスの Active Directory で作成したグループ アカウントを自動的にクラウドに同期 | ● | ● | ● |
| テナントを Microsoft 365 ハイブリッド シナリオ用に設定する。 | ● | ● | ● |
| ユーザーがオンプレミスのパスワードを使用してサインインしたりクラウド サービスにアクセスしたりすることを有効化する。 | ● | ● | ● |
| 企業の資格情報を使用したシングル サインオンを実装する。 | ● | ● | ● |
| クラウドにパスワード ハッシュが保存されないようにする。 |  | ● | ● |
| クラウドベースの多要素認証ソリューションを有効にする。 | ● | ● | ● |
| オンプレミスの多要素認証ソリューションを有効にする。 |  |  | ● |
| ユーザーのスマートカード認証をサポートする。 |  |  | ● |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/concept-group-source-of-authority-guidance"} -->
## Microsoft Entra ID で Group Source of Authority (SOA) を使用するためのガイダンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-group-source-of-authority-guidance
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-10
- Summary: グループの機関ソース (SOA) を使用して、Active Directory グループを管理して Microsoft Entra ID に移行する方法について説明します。 ハイブリッド環境とクラウド環境でのグループ管理、プロビジョニング、復元、およびロールバックの変更に関するベスト プラクティスについて説明します。

ハイブリッド環境間でのグループの管理は、オンプレミスの Active Directory Domain Services (AD DS) からクラウドに移行する組織にとって不可欠です。 Microsoft Entra ID の Group Source of Authority (SOA) を使用すると、AD DS からクラウドにグループ管理を移行できるため、柔軟性、最新のガバナンス、合理化された管理を実現できます。 このガイダンスでは、グループ SOA を使用して、ハイブリッド環境とクラウド環境でグループを管理、プロビジョニング、復元、ロールバックする方法について説明します。 ID インフラストラクチャを最新化する際に、グループのクリーンアップ、グループ管理の変換、セキュリティで保護された効率的なアクセス制御の確保に関するベスト プラクティスについて説明します。

### AD DS グループのクリーンアップ

多くの組織が直面する課題の 1 つは、Active Directory ドメイン内のグループ (特にセキュリティ グループ) の急増です。 組織は、プロジェクトの完了後に不要になったセキュリティ グループを作成する場合があります。 これらのグループは、メンテナンスされずにドメイン内に残る可能性があります。

アプリやファイルなどのリソースにアクセスするためにグループが必要かどうかを確認する方法はありません。 そのため、不要になったグループを識別してクリーンアップする別の方法が必要です。 1 つの方法は、絶叫テスト手法を使用して、使用されなくなったグループを識別することです。 詳細については、「 [Active Directory から未使用のグループを削除する方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-active-directory-group-cleanup)」を参照してください。

### ベスト プラクティス

グループ管理をオンプレミスから Microsoft Entra ID に移行するには、次のベスト プラクティスに従います。

#### グループ SOA の変換とプロビジョニングのためのグループの準備

変換された SOA セキュリティ グループ (メールが有効ではない) を AD DS にプロビジョニングし直す場合は、元の組織単位 (OU) パスを保持するために次の手順を実行する必要があります。

1. AD DS グループのグループ スコープをユニバーサルに変更します。
2. グループ用のテナント スコープのディレクトリ拡張プロパティを作成します。
3. 識別名 (DN) などのオンプレミスの値を拡張機能プロパティに直接マップします。
4. Microsoft Graph を使用してプロパティ値を確認します。
5. 準備ができたら、引用文献 (SOA) を変換します。
6. カスタム式を使用して、Cloud Sync がグループを同じ CN 値と OU 値で AD DS に書き戻す (プロビジョニングする) ようにします。

詳細については、「 [Microsoft Entra Cloud Sync を使用して Active Directory Domain Services にグループをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)する」を参照してください。

#### 移行グループの管理

Microsoft Entra ID ガバナンスは、Microsoft Entra ID セキュリティ グループと Microsoft 365 グループのガバナンスをサポートします。 配布リスト (DLs) と Mail-Enabled セキュリティ グループ (MESG) はクラウドに存在できますが、これらは Exchange の概念であり、Microsoft Entra 管理センターや Microsoft Graph API では管理できません。 そのため、メールを有効にしたままにするグループが必要ない場合は、それを AD DS の標準セキュリティ グループに変換し、グループを同期してから SOA を変換します。

コラボレーションとアクセス管理のシナリオでは、DLs と MESG を Microsoft 365 グループに置き換える必要があります。 ガバナンス、コラボレーション、セルフサービスの組み込み機能を提供します。 ほとんどの場合、DLs と MESG は Microsoft 365 グループとして再作成する必要があります。 ただし、入れ子になっていない単純なクラウド管理の DLs を Microsoft 365 グループに直接アップグレードできます。 詳細については、「 [配布リストを Microsoft 365 グループにアップグレードする](https://learn.microsoft.com/ja-jp/exchange/recipients-in-exchange-online/manage-distribution-groups/upgrade-distribution-lists)」を参照してください。

#### セルフサービス グループ管理への移行

Microsoft Entra ID は、Microsoft 365 およびメールが有効でないセキュリティ グループのマイ グループを通じてセルフサービス グループ管理を提供します。 Microsoft Entra ID ガバナンス を使用すると、マイ アクセスによるアクセス管理が可能になり、アクセス パッケージを使用してグループを管理できます。 アクセス パッケージを使用すると、ユーザーは構造化されたガバナンス フレームワークの一部としてグループへのアクセスを要求できます。 ただし、これらのソリューションでは、オンプレミスソリューションとクラウド ソリューションの違いにより、Microsoft Identity Manager のセルフサービス グループ管理機能が正確にレプリケートされるわけではありません。

セルフサービス グループ管理をオンプレミスの AD DS グループから移行するには、アプリケーションを最新化し、クラウドベースのセキュリティ グループと Microsoft 365 グループを使用します。 詳細については、 [グループの機関ソース (SOA) のセルフサービス グループ管理ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-source-of-authority-self-service-group-management)を参照してください。

#### Microsoft 365 グループに関連付けられているオンプレミス アプリを管理する

AD DS ベースのアプリを管理および管理するには、Microsoft Entra Connect 同期でグループ ライトバックを使用して、Microsoft 365 グループを AD DS にプロビジョニングします。ただし、AD DS にプロビジョニングするグループを選択することはできません。

#### クラウド所有のセキュリティ グループに対するオンプレミスの変更が上書きされる

AD DS にクラウド セキュリティ グループをプロビジョニングし、アクセス許可を持つユーザーが AD DS グループに直接変更を行った場合、次にクラウド グループを AD DS にプロビジョニングするときに変更が上書きされます (通常は、クラウド グループへの次回の変更時)。 ローカル AD DS の変更は、Microsoft Entra ID には反映されません。

#### 入れ子になったグループでの AD DS へのグループ プロビジョニングのしくみ

*CloudGroupB* という名前のセキュリティ グループを AD DS にプロビジョニングする例を見てみましょう。 これには、 *OnPremGroupA* という名前の親オンプレミス AD DS グループがあります。 *SoA for CloudGroupB* を変換します。

次に、変換された *CloudGroupB* の Microsoft Entra ID でグループ メンバーシップの管理を開始します。 オンプレミス グループ *OnPremGroupA* 内にネストされたグループとしてプロビジョニングします。 *OnPremGroupA* が同期のスコープ内に残っている場合、AD DS から Microsoft Entra ID への同期構成が *OnPremGroupA* に対して実行されると、*CloudGroupB* のメンバーシップ参照は同期されません。設計上、同期クライアントはクラウド グループ メンバーシップ参照を認識しません。

詳細については、「[Active Directoryへのプロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-provisioning-to-active-directory-works#nested-group-membership-behavior)」を参照してください。

#### 入れ子になったグループに SOA を適用する方法

SOA は、再帰なしで指定された直接の個々のグループ オブジェクトにのみ適用されます。 グループ内の入れ子になったグループに SOA を適用した場合、それらは引き続きオンプレミスで管理されます。 この手法は仕様であるため、変換する各グループに SOA を明示的に適用します。 入れ子になったグループを変換する場合は、最下位階層のグループから始めて、ツリーの上に移動できます。

#### クラウド内のオンプレミス AD から動的グループ構成を再作成する

オンプレミスの AD グループは本質的に静的です。 動的メンバーシップは、Microsoft Identity Manager (MIM) や Forefront Identity Manager (FIM) などの外部ツールを使用して実装されます。 動的メンバーシップ ルールは、グループを動的としてマークするネイティブ AD 属性がないため、SOA の変換時に自動的に転送されません。 移行後にクラウドで動的メンバーシップ ルールを再作成する必要があります。 動的メンバーシップ グループを設定する方法の詳細については、「 [Microsoft Entra ID で動的メンバーシップ グループを作成または更新](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)する」を参照してください。

#### Microsoft Entra Connect Sync でのカスタムライトウェイト ディレクトリ アクセス プロトコル (LDAP) コネクタの制限事項

Group SOA では、Microsoft Entra Connect Sync でカスタム LDAP コネクタを使用して ID とグループを Microsoft Entra ID に同期することはサポートされていません。 クラウド オブジェクトとして AD から Microsoft Entra ID に同期するグループの SOA の転送のみがサポートされます。 SOA 操作のロールバックは、オブジェクトの元の SOA が AD の場合にのみ機能します。

### クラウド セキュリティ グループを管理する方法

セキュリティ グループは、アクセス制御、ポリシー管理、およびその他の重要な機能の基本です。 ほとんどのコラボレーション シナリオでは、強化されたコラボレーション機能、セルフサービス オプション、API 機能により、Microsoft 365 グループをお勧めします。 配布グループ (DLs) とメールが有効なセキュリティ グループ (MESG) は、特に Exchange 管理者にとって有効なオプションです。

クラウドに移行する場合は、Microsoft Entra、Exchange Online、および Microsoft 365 で、オンプレミス のグループを最新のグループの種類にマップします。 次の表では、SOA 変換後にグループをマップして管理する方法について説明します。

| オンプレミス グループの種類 | クラウド グループの種類 | SOA 変換後の管理方法 | 説明 |
| --- | --- | --- | --- |
| セキュリティ グループ | Microsoft Entra セキュリティ グループ (メールが有効ではない) | Microsoft Entra 管理センター Microsoft Graph API | アクセス制御に不可欠で、Microsoft Entra セキュリティ グループとして直接変換し、Microsoft Graph と Microsoft Entra 管理センターを含むさまざまな管理センターによる管理を提供します。 |
| メールが有効なセキュリティ グループ (Exchange オンプレミス) | メールが有効なセキュリティ グループ (Microsoft Entra ID での読み取り専用で、Exchange で管理) | Exchange Online または PowerShell | 直接移行することも、セキュリティ対応の Microsoft 365 グループ ([グループの作成](https://learn.microsoft.com/ja-jp/graph/api/group-post-groups)) として再作成することもできます。 電子メール機能が不要になった場合は、Microsoft Entra セキュリティ グループとして再作成される可能性があります。 メールが有効なセキュリティ グループは、Exchange または PowerShell を使用してのみ編集できます。 セキュリティ グループと Microsoft 365 グループは、Microsoft Graph および Microsoft Entra 管理センターを含むさまざまな管理センターで管理されます。 |
| 配布リスト (Exchange オンプレミス) | 配布リスト (Microsoft Entra の場合は読み取り専用で、Exchange で管理されます) | Exchange Online または PowerShell 経由 | 電子メールのみの通信用です。 Exchange Online 配布リストとして移行し、Exchange Online または Exchange PowerShell を使用して管理できます。 その後、Microsoft 365 グループとして再作成することも、 [Microsoft 365 グループに直接アップグレード](https://learn.microsoft.com/ja-jp/exchange/recipients-in-exchange-online/manage-distribution-groups/upgrade-distribution-lists)することもできます。 Outlook、Teams、マイ グループ、または Microsoft Graph を使用して、共有ファイル、予定表、Teams の統合、セルフサービス管理を有効にします。 |
| 対象外 (過去に v1 で対応済み) | Microsoft 365 グループ (クラウドのみ) | Microsoft Entra 管理センターMicrosoft Graph API |  |

注

セキュリティ対応の Microsoft 365 グループは、Teams、SharePoint、Outlook などのアプリのコラボレーションと、Microsoft Entra でのアクセス制御の両方に使用できます。 ただし、セキュリティが有効な Microsoft 365 グループは、Exchange 共有メールボックスへのアクセス許可の割り当てにはサポートされていません。 共有メールボックスをセキュリティで保護する必要があるシナリオでは、引き続きメールが有効なセキュリティ グループを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/concept-group-source-of-authority-how-it-works"} -->
## Microsoft Entra ID で Active Directory Domain Services (AD DS) グループを「権限のあるソース (SOA)」を使って管理する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-group-source-of-authority-how-it-works
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-08-01
- Summary: グループの管理を、グループの機関ソース (SOA) を使用して Active Directory Domain Services (AD DS) から Microsoft Entra ID に変換する方法について説明します。

グループのソースオブオーソリティ (SOA) を Active Directory Domain Services (AD DS) から Microsoft Entra ID に変換できます。 SOA を変換すると、グループはクラウド所有になり、クラウド内の対応するクラウド グループの種類にマップできます。 サポートされているグループの種類の一覧については、「 [クラウド セキュリティ グループを管理する方法」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-group-source-of-authority-guidance#how-to-manage-cloud-security-groups)参照してください。

### SOA 変更後に AD DS から Microsoft Entra ID への同期をブロックする

グループ SOA を変換してクラウド グループになると、最新バージョンの Microsoft Entra Connect Sync と Microsoft Entra Cloud Sync で SOA 設定が適用されます。 彼らはグループの同期を続けません。 AD DS グループが不要になったら、スコープ フィルターでスコープ外として削除するのではなく、削除できます。

### AD DS へのセキュリティ グループ プロビジョニングとのシームレスな統合

メールが有効になっていないクラウド セキュリティ グループを AD DS にプロビジョニングし、Microsoft Entra ID と同期するには、グループを AD DS にプロビジョニングするときに、スコープ構成にグループを追加します。 **[選択したグループ]** または [**すべてのグループ**] を属性値スコープで使用します。 動的セキュリティ グループを AD DS にプロビジョニングします。 クラウド セキュリティ グループは、ユニバーサル グループとしてプロビジョニングされます。

Microsoft Entra Cloud Sync は、AD DS にセキュリティ グループをプロビジョニングするときに、以前に SOA が適用され、Microsoft Entra ID から AD DS にプロビジョニングされた既存のドメイン グループを認識します。 セキュリティ識別子 (SID) の値は、それらを相互に関連付ける。 そのため、クラウド セキュリティ グループを AD DS にプロビジョニングすると、元の AD グループにプロビジョニングされます (存在する場合)。 AD DS で一致するものが見つからない場合は、新しいオンプレミス セキュリティ グループが作成されます。

### AD DS でグループを削除および復元する

AD DS でオンプレミス グループを削除するとします。 後で、Microsoft Entra Cloud Sync を使用して、同じ SID を持つグループを AD DS ドメインにプロビジョニングすることにします。この場合は、 **Active Directory のごみ箱** が有効になっていることを確認する必要があります。 AD DS へのグループ プロビジョニングのスコープにグループを追加する前に、 **Active Directory のごみ箱** からグループを復元する必要があります。

### SOA の変更をロールバックする

グループに対する SOA の変更を元に戻すことができます。 このシナリオでは、グループ SOA が元に戻り、AD DS によって管理されます。 次の同期サイクル中に、AD DS がグループを制御します。 Microsoft Entra ID では、グループは読み取り専用になります。

このメソッドは、グループがクラウドで管理されている間に行われたすべての変更を保持します。 オブジェクトが引き継がれた後、クラウドで行われたすべての変更がオーバーライドされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/concept-source-of-authority-overview"} -->
## クラウド優先の体制を採用し、グループの機関ソース (SOA) をクラウドに変換する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview
- Service: entra-id
- Article date: 2025-08-19
- Summary: 前提条件、サポートされているシナリオ、IT アーキテクトと管理者向けのステップ バイ ステップ ガイダンスなど、機関ソース (SOA) について説明します。

最新化の要件では、多くの組織が ID およびアクセス管理 (IAM) ソリューションをオンプレミスからクラウドに移行しています。 クラウド イニシアチブへの移行に向けて、Microsoft は、顧客のビジネス目標に合わせて [5 つの変革の状態をモデル化しました](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-posture#five-states-of-transformation) 。

オンプレミスのインフラストラクチャのサイズと複雑さを最小限に抑えるには、クラウド優先のアプローチを採用します。 クラウドでのプレゼンスが拡大すると、オンプレミスの Active Directory Domain Services (AD DS) のプレゼンスが縮小する可能性があります。 このプロセスは AD DS 最小化と呼ばれ、必要なオブジェクトのみがオンプレミス ドメインに残ります。

AD DS 最小化アプローチの 1 つは、グループの機関ソース (SOA) を Microsoft Entra ID に変換することです。 このアプローチを使用すると、クラウドでこれらのグループを直接管理できます。 オンプレミスで不要になった AD DS グループを削除できます。 グループをオンプレミスに保持する必要がある場合は、Microsoft Entra ID から AD DS へのセキュリティ グループ プロビジョニングを構成できます。 その後、Microsoft Entra ID でグループに変更を加え、それらの変更をオンプレミス グループに反映させることができます。

この記事では、グループ SOA を使用して、IT 管理者がグループ管理を AD DS からクラウドに移行する方法について説明します。 Microsoft Entra ID ガバナンスを使用してアクセス ガバナンスなどの高度なシナリオを有効にすることもできます。 IT アーキテクト向けのグループ SOA の使用に関するガイドについては、[クラウドファーストの ID ガイダンスMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/guidance-it-architects-source-of-authority)参照してください。

### ビデオ: Microsoft Entra グループの権限のソース

SOA の概要と、それがクラウドへの移行にどのように役立つかについては、ビデオをご覧ください。

### グループ SOA を変換して、AD DS グループのクラウドへの移行を効率化する

グループ SOA 機能を使用すると、組織はオンプレミスのアプリケーション アクセス ガバナンスをクラウドに移行できます。 この機能は、MICROSOFT Entra Connect Sync または Microsoft Entra Cloud Sync を使用して Microsoft Entra ID に同期する AD DS のグループの権限のソースを変換します。段階的な移行アプローチを使用すると、管理者はエンド ユーザーの中断を最小限に抑えながら、複雑な移行タスクを実行できます。

オブジェクト レベルの SOA を使用してディレクトリ全体を一度にクラウドに移動するのではなく、制御された方法で AD DS の依存関係を徐々に減らすことができます。 Microsoft Entra ID ガバナンス を使用して、セキュリティ グループに関連付けられているクラウド アプリケーションとオンプレミス アプリケーションの両方のアクセス ガバナンスを管理できます。

AD DS から同期するグループにグループ SOA を適用すると、グループがクラウド オブジェクトに変換されます。 変換後は、クラウド内のクラウド グループ メンバーシップを直接編集、削除、および変更できます。 Microsoft Entra Connect Sync は変換を尊重し、AD DS からのオブジェクトの同期を停止します。 グループ SOA を使用すると、複数のグループを移行したり、特定のグループを選択したりできます。 SOA を変換した後は、クラウド グループで使用できるすべての操作を実行できます。 必要に応じて、これらの変更を元に戻すことができます。

### グループ SOA のシナリオ

#### Microsoft Entra ID ガバナンスを使用してアクセスを管理する

**シナリオ：** ポートフォリオには、最新化できない、または AD DS に接続できないアプリケーションがあります。 これらのアプリケーションでは、Kerberos または LDAP を使用して、AD DS でメールが有効でないセキュリティ グループに対してクエリを実行し、アクセス許可を決定します。 目標は、Microsoft Entra ID と Microsoft Entra ID ガバナンスを使用して、これらのアプリケーションへのアクセスを規制することです。 この目標は、Microsoft Entra が管理するグループ メンバーシップ情報にアプリケーションからアクセスできるようにする必要があります。

[Image: オンプレミス のアプリ ガバナンスの概念図。]

**解決：** 目標は、次の 2 つの方法のいずれかで達成できます。

- オンプレミス グループのグループ SOA を変換します。 グループを AD DS にプロビジョニングし直します。 このモデルでは、アプリを変更したり、新しいグループを作成したりする必要はありません。 詳細については、「 [Microsoft Entra ID ガバナンスを使用したオンプレミスの Active Directory Domain Services ベースのアプリ (Kerberos) の管理」を](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/provision-entra-to-active-directory-groups)参照してください。
- AD DS でグループをレプリケートするには、新しいクラウド セキュリティ グループとして Microsoft Entra ID で最初から作成します。 それらをユニバーサル グループとして AD DS にプロビジョニングします。 このモデルでは、新しいグループ セキュリティ識別子を使用するようにアプリを変更できます。 "Account &gt; Global &gt; Domain Local" アクセス許可モデルを使用する場合は、新しくプロビジョニングされたグループを既存のグループの下に組み込みます。 詳細については、「[Microsoft Entra IDからActive Directoryにユーザーとグループをプロビジョニングする」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)。

#### AD DS の最小化

**シナリオ：** アプリケーションの一部または全部を最新化し、アクセスに AD DS グループを使用する必要がなくなりました。 たとえば、これらのアプリケーションでは、AD FS などのフェデレーション システムの代わりに、セキュリティ アサーション マークアップ言語 (SAML) または Microsoft Entra ID からの OpenID Connect を使用するグループ要求が使用されるようになりました。 ただし、これらのアプリは引き続き既存の同期されたセキュリティ グループに依存してアクセスを管理します。 グループ SOA を使用すると、クラウドでセキュリティ グループ メンバーシップを編集可能にし、AD DS セキュリティ グループを完全に削除し、必要に応じて Microsoft Entra ID ガバナンス機能を使用してクラウド セキュリティ グループを管理できます。

**解決：** グループ SOA を使用して、クラウドマネージド グループを作成し、AD DS から削除できます。 クラウドで新しいグループを直接作成し続けることができます。 詳細については、「 [クラウドでグループを管理するためのベスト プラクティス」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups#best-practices-for-managing-groups-in-the-cloud)参照してください。

[Image: クラウドで管理されているグループと、クラウドでグループを管理するためのベスト プラクティスのスクリーンショット。]

#### オンプレミスの Exchange の依存関係を削除する

**シナリオ：** すべてのユーザー Exchange メールボックスをクラウドに移行しました。 SAML や OpenID Connect などの最新の認証方法を使用するように、メール ルーティング機能に依存するアプリケーションを更新しました。 AD DS で配布リスト (DLL) と Mail-Enabled セキュリティ グループ (MESG) を管理する必要がなくなりました。 目標は、既存の DLs と MESG をクラウドに移行することです。 次に、これらのグループを Microsoft 365 グループに更新するか、Exchange Online で管理します。

**解決：** グループ SOA でこの目標を達成して、クラウドマネージド グループにし、AD DS から削除することができます。 これらのグループは、EXO または Exchange PowerShell モジュールを使用して直接編集できます。 これらのメール オブジェクトは、Microsoft Entra ID または MS Graph API を使用して直接管理することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/configure"} -->
## Active Directory との統合を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/configure
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、Active Directory を使用して同期ツールを構成する方法について説明します。

同期を構成する方法は、使用している同期ツールとビジネス目標によって異なります。 次の表を使用して、ターゲット目標を満たす機能を決定します。

### クラウド同期

Microsoft Entra プロビジョニング エージェントをインストールしたら、クラウド同期を構成する必要があります。この構成は、ポータルを使用して行われます。 次の表に、ビジネス目標を達成するために使用できる機能の一覧を示します。

| タスク | 説明 |
| --- | --- |
| [クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure) の構成と新規インストール | 組織の同期を構成して調整します。 |
| [ユーザーとグループのスコープ設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure#scope-provisioning-to-specific-users-and-groups) | クラウド同期のスコープを特定のユーザーとグループに設定する方法 |
| [ユーザー属性とグループ属性のマッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure#attribute-mapping) | ユーザーとグループの属性をマップします。 |
| [ディレクトリ拡張機能とカスタム属性の操作](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure#directory-extensions-and-custom-attribute-mapping) | ディレクトリ拡張機能とカスタム属性を使用する |
| [シングル サインオンの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-sso) | シングル サインオンを使用するようにクラウド同期を設定する |

### Microsoft Entra Connect

Microsoft Entra Connect で使用される構成タスクの一部は、ツールのインストール時に設定されます。 カスタム インストール セクションを確認して、セットアップ時に必要な情報があることを確認する必要があります。 また、インストール後のタスクを確認して、特定の構成をさらに検証してカスタマイズする必要があります。

| タスク | 説明 |
| --- | --- |
| [同期機能の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-roadmap#configure-sync-features) | Microsoft Entra Connect の構成可能な同期機能を確認します。 |
| [Microsoft Entra Connect Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-roadmap#customize-azure-ad-connect-sync) をカスタマイズする | 既定の構成をカスタマイズする方法。 |
| [フェデレーションの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-roadmap#configure-federation-features) | Microsoft Entra Connect とフェデレーションする方法。 |
| [インストール後のタスク](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-post-installation) | Microsoft Entra Connect を管理するためのその他のタスク |
| [ユーザー属性とグループ属性のマッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure#attribute-mapping) | ユーザーとグループの属性をマップします。 |
| [デバイスの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-device-writeback) | デバイスの書き戻しを構成します。 |
| [シングル サインオンの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start) | シングル サインオンを使用するように Microsoft Entra Connect を設定します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/admin-audit-logging"} -->
## Microsoft Entra Connect Sync の管理者イベントを監査する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/admin-audit-logging
- Service: entra-id / hybrid-connect
- Article date: 2025-09-25
- Summary: この記事では、Microsoft Entra Connect Sync のセキュリティ強化と、管理者アクティビティのログ記録を有効にする方法について説明します。

Microsoft Entra Connect Sync のバージョン [(2.4.129.0)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#241290) 以降では、新しい管理者監査ログ機能が使用でき、既定で有効になっています。 この機能を使用すると、グローバル管理者、ハイブリッド管理者、ローカル サーバー管理者による Microsoft Entra Connect Sync 構成に対する変更を監視できます。

Microsoft Entra Connect 同期ウィザード、PowerShell、または同期規則エディターを通じて実行される管理者アクション (同期規則、認証設定、フェデレーション設定の更新など) は、 **Entra Connect 管理者アクション**と呼ばれる専用のイベント ソースの下で Windows イベント ビューアー アプリケーションのイベント ログにキャプチャされ、ログに記録されます。 これにより、ID インフラストラクチャの変更に対する可視性が向上し、トラブルシューティング、運用アカウンタビリティ、規制コンプライアンスがサポートされます。

この記事では、この機能によってログに記録されるイベントの一覧について説明し、必要に応じて無効にする方法について説明します。

### ログに記録されたイベントの一覧

次の表は、新しい監査機能でログに記録されるイベントの一覧です。 イベントを表示するには、イベント ビューアーを使用して、アプリケーション ログを表示します。

[Image: イベント ビューアーを示すスクリーンショット。]

注

Windows の既定のサイズ 20 MB は比較的小さく、迅速に上書きできるため、アプリケーション イベント ログのサイズを大きくすることをお勧めします。 最小サイズは 250 MB から 500 MB にすることをお勧めします。

| イベント ID | イベント名 | Description |
| --- | --- | --- |
| 2603 | ディレクトリの追加/更新/削除。 | 影響を受けるディレクトリの名前を指定します。 |
| 2604 | 高速設定モードを有効にします。 | このイベントは、管理者が **Express Setup** を選択した後にログに記録されます。 |
| 2605 | 同期のドメインと組織単位 (OU) を有効または無効にします。 | Microsoft Entra Connect Sync に接続されているすべてのドメインの一覧を表示します。 |
| 2606 | パスワード ハッシュ同期 (PHS) を有効または無効にします。 | PHS が有効または無効であることを示します。 |
| 2607 | インストール後に同期の開始を有効または無効にします。 | イベントは、インストールが完了した後に同期が有効または無効になったときにログに記録されます。 |
| 2608 | Active Directory Domain Services (AD DS) アカウントを作成します。 | 追加された新しいディレクトリに接続するために必要な作成済みアカウントを表示します。 |
| 2609 | 既存の AD DS アカウントを使用します。 | ディレクトリへの接続に使用するアカウントの名前を表示します。 |
| 2610 | 同期規則の作成/更新/削除。 | 変更された同期規則の名前と、変更された内容に関する情報が表示されます。 |
| 2611 | ドメイン ベースのフィルター処理を有効または無効にします。 | ドメイン フィルターが選択され、選択したドメインが一覧表示されます。 |
| 2612 | OU ベースのフィルター処理を有効または無効にします。 | OU ベースのフィルター処理が選択され、選択した OU が一覧表示されることを示します。 |
| 2613 | ユーザー のサインイン方法が変更されました。 | 古いサインイン方法と新しいサインイン方法が表示されます。 |
| 2614 | 新しい Active Directory フェデレーション サービス (AD FS) ファームを構成します。 | フェデレーション サービス名を表示します。 |
| 2615 | シングル サインオンを有効または無効にします。 | シングル サインオンの変更を表示します。 |
| 2616 | Web アプリケーション プロキシ サーバーをインストールします。 | 選択した AD FS サーバーとドメイン管理者のユーザー名が表示されます。 |
| 2617 | アクセス許可を設定します。 | 変更された特定の ADSync アクセス許可を表示します。 |
| 2618 | AD DS コネクタの資格情報を変更します。 | 変更された AD DS コネクタの資格情報を表示します。 |
| 2619 | Microsoft Entra ID Connector アカウントのパスワードを再初期化します。 | ADSync アカウントのパスワードがリセットされたことを示します。 |
| 2620 | AD FS サーバーをインストールします。 | 選択したサーバーを表示します。 |
| 2621 | AD FS サービス アカウントを設定します。 | グループが管理されているか、ドメイン ユーザーであるかを指定します。 管理者ユーザー名が含まれます。 |
| 2622 | `ConfigureEntraApplicationAuthentication` | Microsoft Entra ID へのアプリケーション認証の構成が試行されたことを示します。 操作の状態と、アプリケーション (クライアント) ID などの関連する詳細が提供されます。 |
| 2623 | `RotateEntraApplicationCertificate` | Microsoft Entra ID への認証に使用されるアプリケーション証明書のローテーションが、操作の状態と共に試行されたことを指定します。 |
| 2624 | `DeleteEntraConnectorAccount` | Microsoft Entra ID 同期アカウントの削除が試行されたことを指定します。 操作のステータスとアカウントの名前を提供します。 |
| 2625 | `DeleteEntraApplication` | Microsoft Entra ID との同期に使用される Microsoft Entra Connect Sync アプリケーションの削除が試行されたことを指定します。 アプリケーション (クライアント) ID と操作の状態を提供します。 |
| 2626 | `DeleteApplicationCertificate` | Microsoft Entra Connect Sync アプリケーション証明書の削除が試行されたことを指定します。 アプリケーション (クライアント) ID と証明書 ID が、操作の状態と共に提供されます。 |

### 管理者イベントの監査を無効にする

#### レジストリ エディターを使用する

管理者イベントの監査を無効にするには、次の手順に従います。

1. レジストリ エディターを開きます。 **Win + R を**選択して**実行**ダイアログを開きます。
2. **regedit** と入力し、Enter キーを**押**してレジストリ エディターを開きます。 セキュリティ関連のプロンプトを確認して続行してください。
3. **HKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft\Azure AD Connect**に移動します。
4. **Azure AD Connect** キーを右クリックして**、AuditEventLogging** 値を変更または作成します。 **AuditEventLogging** 値がまだ存在しない場合は、[**新規**] を選択し、**DWORD (32 ビット)** の値を選択します。
5. 新しい **DWORD** に **AuditEventLogging という名前を付けます**。
6. **AuditEventLogging** エントリをダブルクリックし、「**0**」と入力して監査イベントログを無効にします。 **「1**」と入力して再び適用します。

[Image: 新しい AuditEventLogging レジストリ キーを示すスクリーンショット。]

#### PowerShell の使用

PowerShell を使用して、管理者イベントの監査ログを無効にすることもできます。 次のスクリプトを実行します。

```powershell
#Declare variables
$registryPath = 'HKLM:\SOFTWARE\Microsoft\Azure AD Connect'
$valueName = 'AuditEventLogging'
$newValue = '0'

#Create the AuditEventLogging key if it doesn't exist
if (!(Test-Path $registryPath)) {New-Item -Path $registryPath -Force}

#Set the value of the new AuditEventLogging key
Set-ItemProperty -Path $registryPath -Name $valueName -Value $newValue
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/authenticate-application-id"} -->
## アプリケーション ID を使用して Microsoft Entra ID に対する認証を行う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/authenticate-application-id
- Service: entra-id / hybrid
- Article date: 2025-07-24
- Summary: この記事では、Microsoft Entra Connect アプリケーションが最新のセキュリティで保護された資格情報を使用して Microsoft Entra ID で認証できるようにする方法について説明します。

Microsoft Entra Connect では、 [Microsoft Entra Connector アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions#accounts-used-for-microsoft-entra-connect) を使用して、Active Directory から Microsoft Entra Connect への ID の認証と同期を行います。 このアカウントでは、ユーザー名とパスワードを使用して要求を認証します。

サービスのセキュリティを強化するために、Oauth 2.0 クライアント資格情報フローと証明書資格情報を使用するアプリケーション ID をロールアウトします。 この新しい方法では、Microsoft Entra または管理者は、Microsoft Entra ID で Microsoft 以外の単一テナント アプリケーションを作成し、次の関連する証明書管理オプションのいずれかを資格情報に使用します。

Microsoft Entra Connect には、アプリケーションと証明書の管理に次の 3 つのオプションがあります。

- Microsoft Entra Connect によって管理される (既定)
- 自分の証明書を持参 (BYOC)
- Bring Your Own Application (BYOA)

注

[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロールは、Azure AD Graph と Microsoft Graph のアプリケーションアクセス許可を除き、アプリケーションのアクセス許可に同意する権限を付与します。 つまり、アプリケーション管理者は、他のアプリ (特に AWS ファーストパーティ アプリや SSPR ファーストパーティ アプリ) に対するアプリケーションのアクセス許可に同意できます。

このロールでは、アプリケーション資格情報を管理する機能も付与されます。 このロールに割り当てられたユーザーは、アプリケーション (特に Connect Sync) に資格情報を追加し、それらの資格情報を使用してアプリケーションの ID を偽装できます。 これは、ユーザーがロールの割り当てを使用して実行できる操作に対する特権の昇格である可能性があります。

### Microsoft Entra Connect によって管理される (既定)

Microsoft Entra Connect は、証明書の作成、ローテーション、削除を含むアプリケーションと証明書を管理します。 証明書は、 `CURRENT_USER` ストアに格納されます。 証明書の秘密キーを最適に保護するには、コンピューターでトラステッド プラットフォーム モジュール (TPM) ソリューションを使用して、ハードウェア ベースのセキュリティ境界を確立することをお勧めします。

TPM が使用可能な場合、主要なサービス操作は専用のハードウェア環境内で実行されます。 これに対し、TPM を使用できない場合、Microsoft Entra Connect は既定の Microsoft ソフトウェア キー ストレージ プロバイダーに証明書を格納し、保護を強化するために秘密キーを nonexportable としてマークします。 TPM によって提供されるハードウェア分離がないと、ソフトウェアのみが秘密キーを保護し、同じレベルの保護を実現しません。

TPM テクノロジの詳細については、「 [トラステッド プラットフォーム モジュール テクノロジの概要](https://learn.microsoft.com/ja-jp/windows/security/hardware-security/tpm/trusted-platform-module-overview)」を参照してください。

[Image: アプリケーション ID を使用した認証を示す図。]

キーを管理し、有効期限が切れたときに証明書を自動的にローテーションするため、Microsoft Entra Connect 証明書管理 (既定) オプションをお勧めします。 既定では、Microsoft Entra Connect は有効期間が 90 日間の証明書を生成します。

Microsoft Entra Connect Sync では、スケジューラを使用して証明書がローテーションの期限かどうかを確認し、証明書を自動的にローテーションします。 スケジューラが中断されている場合、Microsoft Entra Connect Sync が証明書を管理している場合でも、証明書の自動ローテーションは行われません。

### Bring Your Own Certificate

Bring Your Own Certificate (BYOC) セットアップでは、管理者がアプリケーションで使用する証明書資格情報を管理します。 管理者は、証明書の作成、ローテーション、未使用または期限切れの証明書の削除を行う必要があります。 証明書は、 `LOCAL_MACHINE` ストアに格納する必要があります。

管理者は、証明書の秘密キーをセキュリティで保護し、署名のために Microsoft Entra Connect Sync のみが秘密キーにアクセスできることを確認する責任があります。

既定ではなく、TPM またはハードウェア セキュリティ モジュール (HSM) を使用してハードウェア ベースのセキュリティ境界を提供することをお勧めします。 TPM の状態を確認するには、 [Get-TPM](https://learn.microsoft.com/ja-jp/powershell/module/trustedplatformmodule/get-tpm) PowerShell コマンドレットを使用します。

Hyper-V 仮想マシン (VM) を使用する場合は、 **セキュリティ**&gt;**使用可能な信頼されたプラットフォーム モジュール**を選択して TPM を有効にすることができます。 この手順は、第 2 世代 VM でのみ実行できます。 第 1 世代 VM を第 2 世代 VM に変換することはできません。 詳細については、「 [Hyper-V の第 2 世代 VM セキュリティ設定](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/learn-more/generation-2-virtual-machine-security-settings-for-hyper-v) 」および「 [既存の Azure Gen2 VM での信頼された起動を有効にする」を参照してください](https://learn.microsoft.com/ja-jp/azure/virtual-machines/trusted-launch-existing-vm)。

### 独自のアプリケーションを持参する

Bring Your Own Application (BYOA) セットアップでは、管理者は Microsoft Entra Connect Sync が Microsoft Entra ID、アプリケーションのアクセス許可、アプリケーションで使用する証明書資格情報に対する認証に使用するアプリケーションを管理します。

管理者は [Microsoft Entra アプリを登録し、サービス プリンシパルを作成します](https://learn.microsoft.com/ja-jp/graph/tutorial-applications-basics)。 アプリケーションには、Microsoft Graph PowerShell コマンドを構成するために必要なアクセス許可が必要です。

注

アプリケーション ID を使用して Microsoft Entra ID に対する認証を行うには、Microsoft Entra Connect Sync バージョン 2.5.76.0 以降が必要です。

注

BYOA を使用するには、独自の証明書が必要です。

### [前提条件]

アプリケーション ID を使用して認証を実装するには、次の前提条件が必要です。

Von Bedeutung

新しい Microsoft Entra Connect Sync バージョンは、Microsoft Entra 管理センター経由でのみ使用できます。

[新機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new-archive#general-availability---download-microsoft-entra-connect-sync-on-the-microsoft-entra-admin-center)のコミュニケーションの後、Microsoft Entra Connect Sync の新しいバージョンは、Microsoft Entra 管理センター内の [Microsoft Entra Connect ウィンドウ](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)でのみ使用でき、[Microsoft ダウンロード センター](https://www.microsoft.com/en-us/download/details.aspx?id=47594)にリリースされなくなります。

- 手動オンボードには、[Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) バージョン [2.5.3.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#2530) 以降が必要です。
- 自動オンボード用の Microsoft Entra Connect バージョン [2.5.76.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#25760) 以降
- 少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持つ Microsoft Entra アカウント。
- 省略可能: TPM 2.0 が存在し、すぐに使用できます (セキュリティを強化することを強くお勧めします)。

BYOA および BYOC 証明書管理オプションには、次の追加要件が必要です。

- 証明書は、Cryptography API: Next Generation プロバイダーを使用して HSM または TPM に作成されます。 秘密キーはエクスポート不可としてマークされます。 TPM が使用されていない場合は、警告イベント 1014 が生成されます。 次の証明書構成がサポートされています。
    - `KeyUsage`: DigitalSignature
    - `KeyLength`: 2048
    - `KeyAlgorithm`：RSA
    - `KeyHashAlgorithm`: SHA256
- 作成された証明書は、 `LOCAL_MACHINE` ストアに格納されます。
- 秘密キーを使用して署名を実行するアクセス許可を Microsoft Entra Connect Sync アカウントに付与します。

BYOA アプリケーション管理オプションには、次の追加要件が必要です。

- 顧客は、前の BYOC 前提条件の指示に従って証明書を作成します。
- 顧客は Microsoft Entra ID でアプリケーションを登録し、サービス プリンシパルを作成します。 必要なアクセス許可がアプリケーションに付与されます。
- 顧客が証明書をアプリケーションに登録します。

### 現在の認証構成を表示する

現在の認証構成を表示するには、ウィザードを実行して **[タスク]** に移動し、[ **現在の構成の表示またはエクスポート**] を選択します。

サーバーがアプリケーション ベースの認証を使用するように構成されている場合は、次のスクリーンショットに示すように、アプリケーション (クライアント) ID を確認できます。

[Image: クライアント ID を示すスクリーンショット。]

下にスクロールして証明書の詳細を表示します。 次の表に、証明書に関する情報を示します。

| プロパティ | 説明 |
| --- | --- |
| **によって管理される証明書** | Microsoft Entra Connect Sync と BYOC のどちらを使用して証明書を管理するか |
| **自動回転が有効** | 自動回転と手動回転のどちらを有効にするか |
| **証明書の拇印** | 認定資格証の一意の識別子 |
| **証明書 SHA256 ハッシュ** | SHA-256 ハッシュ アルゴリズムを使用して生成された証明書のフィンガープリント |
| **以前は無効です** | 証明書が有効な最初の日付 |
| **次の後に無効です** | 証明書が有効な最後の日付 |
| **件名** | 証明書に関連付けられているエンティティを識別します |
| **発行者** | 証明書の発行者は誰か |
| **シリアル番号** | 同じ発行者によって証明書間で証明書を一意に識別する |
| **プロバイダー名** | キー ストレージ プロバイダーを指定します。ソフトウェア証明書の`Microsoft Software Key Storage Provider`または TPM ベースの証明書の`Microsoft Platform Crypto Provider` |

[Image: 証明書を示すスクリーンショット。]

サーバーがユーザー名とパスワードを使用している場合は、次のスクリーンショットに示すようにアカウント名を確認できます。

[Image: アカウント名を示すスクリーンショット。]

### インストールとアップグレード (Microsoft Entra Connect によって管理)

Microsoft Entra Connect Sync マネージド アプリケーションと資格情報は、初期インストールまたは手動の対話型アップグレード中に自動的に設定されます。 Microsoft Entra Connect がアプリケーション ID を使用していることを確認するには、 現在の認証構成を表示できます。

### アプリケーションベースの認証に移行する

#### 自動

Microsoft Entra Connect Sync の新規インストールは、セットアップ時にアプリケーション ベースの認証用に構成されます。 Microsoft Entra Connect では、レガシ ディレクトリ同期アカウントを使用する既存のサーバーは自動的に切り替わりません。 既存のサーバーを切り替えるには、「 手動 」セクションの手順に従います。

#### マニュアル

アプリケーション認証が自動的に構成されていない場合は、アプリケーション ベースの認証に手動で切り替えることができます。

既定のオプション (Microsoft Entra Connect によって管理) を使用してアプリケーション ベースの認証を構成する場合は、ウィザードを使用できます。 ただし、BYOC または BYOA を使用してアプリケーション ベースの認証を構成する場合は、PowerShell を使用する必要があります。

## [既定のアプリケーション](#tab/default-application)
1. Microsoft Entra Connect ウィザードを起動する
2. **[その他のタスク**&gt;**アプリケーション ベースの認証を Microsoft Entra ID に構成する**] に移動し、プロンプトに従います。

    [Image: [追加タスク] ウィンドウでのアプリケーション ベースの認証の構成を示すスクリーンショット。]

## [BYOC と既定のアプリケーション](#tab/byoc-default-application)
注

Microsoft Entra Connect サーバー上にあり、Microsoft Entra Connect Sync (ADSync) PowerShell モジュールがインストールされていることを確認します。

現在の 認証構成ウィザード ビュー を使用して、Microsoft Entra Connect がアプリケーション ID を使用していることを確認するか、PowerShell コマンドを使用して現在の認証方法を確認します。

```powershell
Get-ADSyncEntraConnectorCredential
```

このコマンドは、現在使用中の `ConnectorIdentityType` 値を返します。 値は、 `ServiceAccount` または `Application`できます。 認証で `ServiceAccount`を使用している場合は、次の手順に進み、 `ServiceAccount` から `Application`に切り替えます。 認証で `Application`を使用している場合は、「 **アプリケーション証明書を独自の証明書に変更する** 」の手順に進んでください。

##### ServiceAccount から Application に切り替える

1. Microsoft Entra Connect ウィザードを起動します。
2. **[その他のタスク**&gt;**アプリケーション ベースの認証を Microsoft Entra ID に構成する**] に移動し、プロンプトに従います。

    [Image: [追加タスク] ウィンドウでのアプリケーション ベースの認証の構成を示すスクリーンショット。]

##### アプリケーション証明書を独自の証明書に変更する

1. 次のいずれかのオプションを使用して、証明書 (.cer) ファイルをエクスポートして Microsoft Entra アプリ登録にアップロードします。

    **オプション 1**: mmc コンソールを使用して Windows 証明書ストアから証明書をエクスポートする:

    1. 次のコマンドを実行して、ローカル コンピューターの証明書管理コンソールを開きます。 このコマンドを実行する方法には、[スタート] メニュー、Windows 実行プロンプト、PowerShell プロンプト、またはコマンド プロンプトがあります。

        ```powershell
        certlm.msc
        ```
    2. コンソール ツリーで、エクスポートする証明書に移動します。
    3. 証明書を右クリックし、[ **すべてのタスク]** を選択し、[ **エクスポート**] を選択します。
    4. 画面で、[ **証明書のエクスポート ウィザードへようこそ**] を選択し、[ **次へ**] を選択します。
    5. 秘密キーのエクスポートを求められたら、[いいえ] を選択し **、秘密キーをエクスポートしません**。次へを選択 **します**。
    6. ファイル形式として、 **DER でエンコードされたバイナリ X.509 (.CER)** を選択し、[ **次へ**] を選択します。
    7. ファイル パスを入力または参照し、[ **次へ**] を選択します。
    8. 概要を確認し、[ **完了]** を選択します。

    **オプション 2**: PowerShell を使用して証明書をエクスポートする:

    ```powershell
    $cerFile  = "C:\Temp\MyBYOC.cer"
    $cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object {$_.Subject -eq 'CN=YOUR_CERTIFICATE_SUBJECT'}  
    Export-Certificate -Cert $cert -FilePath $cerFile
    ```
2. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Entra ID** **アプリ登録**に移動し、Connect Sync のインストール、構成、またはアップグレード中に作成されたアプリケーションを選択します。Connect Sync でどのアプリケーションが使用されているかを確認するには、`Get-ADSyncEntraConnectorCredential` コマンドを実行して、アプリケーション (クライアント) ID を取得します。 ユーザー名の形式は `{AppID}@tenantName.onmicrosoft.com`。 [ **証明書とシークレット** ] で[ **証明書のアップロード** ] を選択し、エクスポートした.cerファイルをアップロードして **、[追加]** を選択します。

    [Image: アプリ登録への証明書のアップロードを示すスクリーンショット。]
3. 次の PowerShell コマンドを使用して証明書ハッシュを取得する

    ```powershell
    $cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object {$_.Subject -eq 'CN=YOUR_CERTIFICATE_SUBJECT'}  
    
    # Get raw data from X509Certificate cert
    $certRawDataString = $cert.GetRawCertData()
    
    # Compute SHA256Hash of certificate 
    $sha256 = [System.Security.Cryptography.SHA256]::Create()
    $hashBytes = $sha256.ComputeHash($certRawDataString)
    ```

    **PowerShell バージョン 7** を使用している場合は、次のコマンドを使用します。

    ```powershell
    $certHash = [System.Convert]::ToHexString($hashBytes)
    ```

    **古い PowerShell バージョン**または **PowerShell ISE** を使用している場合は、次のコマンドを使用します。

    ```powershell
    $certHash = ($hashBytes|ForEach-Object ToString X2) -join ''
    ```
4. Connect Sync (ADSync) サービス アカウントに証明書の秘密キーを取得するアクセス許可を付与します。

    ```powershell
    $rsaCert = [System.Security.Cryptography.X509Certificates.RSACertificateExtensions]::GetRSAPrivateKey($cert)
    ```

    証明書が **証明機関 (CA) によって発行**された場合は、次の `$path` 変数を使用します。

    ```powershell
    $path = "$env:ALLUSERSPROFILE\Microsoft\Crypto\RSA\MachineKeys\$($rsaCert.key.UniqueName)"
    ```

    **自己署名**証明書を使用している場合は、次の`$path`変数を使用します。

    ```powershell
    $path = "$env:ALLUSERSPROFILE\Microsoft\Crypto\Keys\$($rsaCert.key.UniqueName)"
    ```
5. 次のコマンドを実行して、アクセス許可を付与します。

    ```powershell
    $permissions = Get-Acl -Path $path
    $serviceAccount = (Get-ItemProperty -Path HKLM:\SYSTEM\CurrentControlSet\Services\ADSync -Name ObjectName).ObjectName
    $rule = New-Object Security.Accesscontrol.FileSystemAccessRule "$serviceAccount", "read", allow
    $permissions.AddAccessRule($rule)
    Set-Acl -Path $path -AclObject $permissions
    
    # Verify permissions
    $permissions = Get-Acl -Path $path
    $permissions.Access
    ```
6. 同期スケジューラを無効にする

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $false
    ```
7. ADSync モジュールのインポート

    **PowerShell バージョン 7** を使用している場合は、次のコマンドを使用して ADSync モジュールをインポートします。

    ```powershell
    Import-Module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync" -UseWindowsPowerShell
    ```

    **古い PowerShell バージョン**または **PowerShell ISE** を使用している場合は、次のコマンドを使用して ADSync モジュールをインポートします。

    ```powershell
    Import-Module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync"
    ```
8. 証明書ローテーション コマンドを使用してアプリケーション証明書を更新する

    ```powershell
    Invoke-ADSyncApplicationCredentialRotation -CertificateSHA256Hash $certHash 
    ```
9. 現在の 認証構成 ウィザード ビューを使用して、Microsoft Entra Connect で新しい証明書が使用されていることを確認します。
10. 同期スケジューラを有効にする

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $true
    ```
11. Microsoft Entra ID (推奨) からディレクトリ同期アカウント (DSA) を削除します。

## [BYOC を使用した BYOA](#tab/byoa-with-byoc)
##### PowerShell を使用してアプリケーションを作成する

1. テナントに接続する

    ```powershell
    Connect-MgGraph -scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"
    ```
2. BYOA アプリケーションの作成と初期化

    ```powershell
    $BYOApp = New-MgApplication -DisplayName "My BYOA For Connect Sync serverName"
    ```
3. BYOA サービス プリンシパル名を作成して初期化する

    ```powershell
    $BYOA_ServicePrincipal = New-MgServicePrincipal -AppId $BYOApp.AppId
    ```
4. `ConnectSyncAppId`変数と`ConnectSyncSPId`変数を初期化します。

    ```powershell
    $ConnectSyncAppId = $BYOApp.AppId
    $ConnectSyncSPId = $BYOA_ServicePrincipal.Id
    ```
5. `SynchronizationServiceAppId`のアプリケーション (クライアント) ID を表す変数初期化します。 値は`6bf85cfa-ac8a-4be5-b5de-425a0d0dc016`**すべてのクラウド**に対してです。

    ```powershell
    $SynchronizationServiceAppId = "6bf85cfa-ac8a-4be5-b5de-425a0d0dc016"
    ```
6. 変数 `SynchronizationServiceSPId` 初期化します。

    ```powershell
    $SynchronizationServiceSPId = (Get-MgServicePrincipal -Filter "appId eq '$SynchronizationServiceAppId'").Id
    ```
7. 変数 `SynchronizationServiceAppRoleId` 初期化します。

    ```powershell
    $SynchronizationServiceAppRoleId =  (Get-MgServicePrincipal -Filter "appId eq '$SynchronizationServiceAppId'").AppRoles | Where-Object {$_.Value -eq "ADSynchronization.ReadWrite.All"} | Select-Object -ExpandProperty Id
    ```

    注

    **パスワード ライトバック**機能を使用している場合は、次の手順に進みます。それ以外の場合は、手順 11 に進むことができます。
8. 変数 `PasswordResetServiceAppId` 初期化します。

    アーリントンを除く **すべてのクラウド** では、次を使用します。

    ```powershell
    $PasswordResetServiceAppId = "93625bc8-bfe2-437a-97e0-3d0060024faa"
    ```

    **Arlington** クラウドの場合:

    ```powershell
    $PasswordResetServiceAppId = "2e5ecfc8-ea79-48bd-8140-c19324acb278"
    ```
9. 変数 `PasswordResetServiceSPId` 初期化します。

    ```powershell
    $PasswordResetServiceSPId = (Get-MgServicePrincipal -Filter "appId eq '$PasswordResetServiceAppId'").Id
    ```
10. パスワードリセットのAppRoles変数を初期化します。

    ```powershell
    $PasswordResetServiceServiceOffboardClientAppRoleId = (Get-MgServicePrincipal -Filter "appId eq '$PasswordResetServiceAppId'").AppRoles | Where-Object {$_.Value -eq "PasswordWriteback.OffboardClient.All"} | Select-Object -ExpandProperty Id
    $PasswordResetServiceServiceRegisterClientAppRoleId = (Get-MgServicePrincipal -Filter "appId eq '$PasswordResetServiceAppId'").AppRoles | Where-Object {$_.Value -eq "PasswordWriteback.RegisterClientVersion.All"} | Select-Object -ExpandProperty Id
    $PasswordResetServiceServiceRefreshClientAppRoleId = (Get-MgServicePrincipal -Filter "appId eq '$PasswordResetServiceAppId'").AppRoles | Where-Object {$_.Value -eq "PasswordWriteback.RefreshClient.All"} | Select-Object -ExpandProperty Id
    ```
11. 変数 `RequiredResourceAccess` 初期化して、Microsoft Entra AD 同期サービスと Microsoft パスワード リセット サービスに必要なアクセス許可を構成します。

    **パスワード ライトバックを使用しない**場合は、次を使用します。

    ```powershell
    $RequiredResourceAccess = @(
        @{
            ResourceAppId = $SynchronizationServiceAppId
            ResourceAccess = @(
                @{
                    Id = $SynchronizationServiceAppRoleId
                    Type = "Role"
                }
            )
        }
    )
    ```

    **パスワード ライトバックを使用**する場合は、以下を使用してください。

    ```powershell
    $RequiredResourceAccess = @(
        @{
            ResourceAppId = $SynchronizationServiceAppId
            ResourceAccess = @(
                @{
                    Id = $SynchronizationServiceAppRoleId
                    Type = "Role"
                }
            )
        },
        @{
            ResourceAppId = $PasswordResetServiceAppId
            ResourceAccess = @(
                @{
                    Id = $PasswordResetServiceServiceOffboardClientAppRoleId
                    Type = "Role"
                },
                @{
                    Id = $PasswordResetServiceServiceRegisterClientAppRoleId
                    Type = "Role"
                },
                @{
                    Id = $PasswordResetServiceServiceRefreshClientAppRoleId
                    Type = "Role"
                }
            )
        }
    )
    ```
12. 必要なアクセス許可でアプリケーションを更新します。

    ```powershell
    Update-MgApplication -ApplicationId $BYOApp.Id -RequiredResourceAccess $RequiredResourceAccess
    ```
13. Synchronization Service のアプリ ロールの割り当てを作成します。

    ```powershell
    $SyncAppRoleAssignment = New-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $BYOA_ServicePrincipal.Id -PrincipalId $BYOA_ServicePrincipal.Id -ResourceId $SynchronizationServiceSPId -AppRoleId $SynchronizationServiceAppRoleId
    ```

    注

    **パスワード ライトバック**機能を使用している場合は、次の手順に進みます。それ以外の場合は、手順 15 に進むことができます。
14. パスワード ライトバック機能のアプリ ロールの割り当てを作成します。

    ```powershell
     $OffboardAppRoleAssignment = New-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $BYOA_ServicePrincipal.Id -PrincipalId $BYOA_ServicePrincipal.Id -ResourceId $PasswordResetServiceSPId -AppRoleId $PasswordResetServiceServiceOffboardClientAppRoleId
    
     $RegisterAppRoleAssignment = New-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $BYOA_ServicePrincipal.Id -PrincipalId $BYOA_ServicePrincipal.Id -ResourceId $PasswordResetServiceSPId -AppRoleId $PasswordResetServiceServiceRegisterClientAppRoleId
    
     $RefreshAppRoleAssignment = New-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $BYOA_ServicePrincipal.Id -PrincipalId $BYOA_ServicePrincipal.Id -ResourceId $PasswordResetServiceSPId -AppRoleId $PasswordResetServiceServiceRefreshClientAppRoleId
    ```
15. 次のいずれかのオプションを使用して、証明書 (.cer) ファイルをエクスポートして Microsoft Entra アプリ登録にアップロードします。

    **オプション 1**: mmc コンソールを使用して Windows 証明書ストアから証明書をエクスポートする:

    1. 次のコマンドを実行して、ローカル コンピューターの証明書管理コンソールを開きます。 このコマンドを実行する方法には、[スタート] メニュー、Windows 実行プロンプト、PowerShell プロンプト、またはコマンド プロンプトがあります。

        ```powershell
        certlm.msc
        ```
    2. コンソール ツリーで、エクスポートする証明書に移動します。
    3. 証明書を右クリックし、[ **すべてのタスク]** を選択し、[ **エクスポート**] を選択します。
    4. [ **証明書のエクスポート ウィザードへようこそ** ] 画面で、[ **次へ**] を選択します。
    5. 秘密キーのエクスポートを求められたら、[いいえ] を選択し **、秘密キーをエクスポートしません**。次へを選択 **します**。
    6. ファイル形式として、 **DER でエンコードされたバイナリ X.509 (.CER)** を選択し、[ **次へ**] を選択します。
    7. ファイル パスを入力または参照し、[ **次へ**] を選択します。
    8. 概要を確認し、[ **完了]** を選択します。

    **オプション 2**: PowerShell を使用して証明書をエクスポートする:

    ```powershell
    $cerFile  = "C:\Temp\MyBYOC.cer"
    $cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object {$_.Subject -eq 'CN=YOUR_CERTIFICATE_SUBJECT'}  
    Export-Certificate -Cert $cert -FilePath $cerFile
    ```
16. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Entra ID**&gt;**App Registration** に移動し、手順 2 で作成したアプリケーションを選択します。 [ **証明書とシークレット**] で、[ **証明書のアップロード** ] を選択し、エクスポートした.cerファイルをアップロードして **、[追加]** を選択します。

    [Image: アプリ登録への証明書のアップロードを示すスクリーンショット。]
17. 次の PowerShell コマンドを使用して証明書ハッシュを取得します。

    ```powershell
    $cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object {$_.Subject -eq 'CN=YOUR_CERTIFICATE_SUBJECT'}  
    
    # Get raw data from X509Certificate cert
    $certRawDataString = $cert.GetRawCertData()
    
    # Compute SHA256Hash of certificate 
    $sha256 = [System.Security.Cryptography.SHA256]::Create()
    $hashBytes = $sha256.ComputeHash($certRawDataString)
    ```

    **PowerShell バージョン 7** を使用している場合は、次のコマンドを使用します。

    ```powershell
    $certHash = [System.Convert]::ToHexString($hashBytes)
    ```

    **古い PowerShell バージョン**または **PowerShell ISE** を使用している場合は、次のコマンドを使用します。

    ```powershell
    $certHash = ($hashBytes|ForEach-Object ToString X2) -join ''
    ```
18. Connect Sync (ADSync) サービス アカウントに証明書の秘密キーを取得するアクセス許可を付与します。

    ```powershell
    $rsaCert = [System.Security.Cryptography.X509Certificates.RSACertificateExtensions]::GetRSAPrivateKey($cert)
    ```

    証明書が **証明機関 (CA) によって発行**された場合は、次の `$path` 変数を使用します。

    ```powershell
    $path = "$env:ALLUSERSPROFILE\Microsoft\Crypto\RSA\MachineKeys\$($rsaCert.key.UniqueName)"
    ```

    **自己署名**証明書を使用している場合は、次の`$path`変数を使用します。

    ```powershell
    $path = "$env:ALLUSERSPROFILE\Microsoft\Crypto\Keys\$($rsaCert.key.UniqueName)"
    ```
19. 次のコマンドを実行して、アクセス許可を付与します。

    ```powershell
    $permissions = Get-Acl -Path $path
    $serviceAccount = (Get-ItemProperty -Path HKLM:\SYSTEM\CurrentControlSet\Services\ADSync -Name ObjectName).ObjectName
    $rule = New-Object Security.Accesscontrol.FileSystemAccessRule "$serviceAccount", "read", allow
    $permissions.AddAccessRule($rule)
    Set-Acl -Path $path -AclObject $permissions
    
    # Verify permissions
    $permissions = Get-Acl -Path $path
    $permissions.Access
    ```
20. 同期スケジューラを無効にする

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $false
    ```
21. ADSync モジュールのインポート:

    **PowerShell バージョン 7** を使用している場合は、次のコマンドを使用して ADSync モジュールをインポートします。

    ```powershell
    Import-Module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync" -UseWindowsPowerShell
    ```
22. 新しいアプリケーションを認証に使用するように切り替える

    ```powershell
    Add-ADSyncApplicationRegistration -CertificateSHA256Hash $certHash –ApplicationAppId $ConnectSyncAppId 
    ```
23. 現在の 認証構成 ウィザード ビューを使用して、Microsoft Entra Connect がアプリケーション ID を使用していることを確認するか、PowerShell コマンドを使用して現在の認証方法を確認します。

    ```powershell
    Get-ADSyncEntraConnectorCredential
    ```
24. 同期スケジューラを有効にする

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $true
    ```
25. Microsoft Entra ID (推奨) からディレクトリ同期アカウント (DSA) を削除します。

---

### 従来のサービス アカウントを削除する

アプリケーション ベースの認証に移行し、Microsoft Entra Connect Sync が期待どおりに動作している場合は、PowerShell を使用して従来の DSA ユーザー名とパスワード サービス アカウントを削除することを強くお勧めします。 削除できないカスタムアカウントを使用する場合は、そのアカウントの特権を解除し、DSAロールを削除してください。

レガシ サービス アカウントを削除するには、次の手順に従います。

1. サービス アカウントのユーザー名とパスワードを追加します。

    ```powershell
    $HACredential = Get-Credential
    ```
2. Microsoft Entra 管理者の `UserPrincipalName` 値とパスワードを入力するように求められます。 ユーザー名とパスワードを入力します。
3. 次に、サービス アカウントを削除します。

    ```powershell
    Remove-ADSyncAADServiceAccount -AADCredential $HACredential -Name <$serviceAccountName>
    ```

    `ServiceAccountName`値は、Microsoft Entra ID で使用されるサービス アカウントの`UserPrincipalName`値の最初の部分です。 このユーザーは、Microsoft Entra 管理センターのユーザーの一覧で確認できます。 UPN が`Sync_Server_id@tenant.onmicrosoft.com`されている場合は、`Sync_Server_id`値として `ServiceAccountName` を使用します。

### PowerShell を使用してレガシ サービス アカウントにロールバックする

従来のサービス アカウントに戻す場合は、PowerShell を使用してサービス アカウントの使用に戻し、問題を迅速に軽減できます。 次の手順を使用して、サービス アカウントにロールバックします。

ロールバックの一環として、DSA アカウントを再作成します。 この新しいアカウントが有効になるまでに最大 15 分かかる場合があるため、同期サイクルを再度有効にすると、"アクセス拒否" エラーが発生する可能性があります。

1. スケジューラを無効にして、この変更が完了するまで同期サイクルが実行されないようにします。

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $false
    ```
2. サービス アカウントを追加します。 Microsoft Entra 管理者の `UserPrincipalName` 値とパスワードを入力するように求められます。 RDP 資格情報を入力します。

    ```powershell
    Add-ADSyncAADServiceAccount
    ```
3. 現在の認証メカニズムを取得し、 `ConnectorIdentityType` 値が `ServiceAccount`に戻っていることを確認します。

    ```powershell
    Get-ADSyncEntraConnectorCredential
    ```
4. スケジューラを再び使用して同期サービスを開始します。

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $true
    ```

### 証明書のローテーション

証明書が有効期間の 70% 以上を消費した場合、Microsoft Entra Connect によって警告が表示されます。 90 日間の証明書の場合、警告は 63 日頃から始まります。 証明書の有効期限が既に切れている場合は、エラーが生成されます。 これらの警告 (イベント ID 1011) とエラー (イベント ID 1012) は、アプリケーション イベント ログにあります。

スケジューラが中断されていない場合、このメッセージはスケジューラの頻度で出力されます。 `Get-ADSyncScheduler`を実行して、スケジューラが中断されているかどうかを確認します。

#### 自動

Microsoft Entra Connect が証明書を管理する場合、スケジューラが中断されない限り、 *ユーザーは何も行う必要はありません* 。Microsoft Entra Connect Sync は新しい証明書資格情報をアプリケーションに追加し、古い証明書資格情報の削除を試みます。

古い証明書の資格情報を削除できない場合は、 **イベント ビューアー**のアプリケーション ログにエラー イベントが表示されます。

このエラーが表示された場合は、PowerShell で次のコマンドレットを実行して、Microsoft Entra ID から古い証明書の資格情報をクリーンアップします。 コマンドレットは、削除する必要がある証明書の `CertificateId` 値を受け取ります。この値は、ログまたは Microsoft Entra 管理センターから取得できます。

```powershell
Remove-EntraApplicationKey -CertificateId <certificateId>
```

#### マニュアル

構成が証明書の自動ローテーションの対象ではない場合は、現在の証明書がまだローテーションの期限切れでも、現在の証明書の有効期限が切れている場合でも、いつでも証明書をローテーションできます。

## [既定のアプリケーション](#tab/default-cert-renewal)
1. **Microsoft Entra Connect ウィザード**を起動する
2. **その他のタスク**&gt;**アプリケーション証明書のローテーション**に移動して、画面の指示に従います。

[Image: [その他のタスク] ウィンドウの [アプリケーション証明書のローテーション] オプションを示すスクリーンショット。]

## [BYOC と既定のアプリケーション](#tab/byoc-cert-renewal)
Microsoft Entra Connect Sync から警告が表示されたら、新しいキーと証明書を生成し、Microsoft Entra Connect Sync で使用される *証明書をローテーションすることを強くお勧めします* 。

1. 次のいずれかのオプションを使用して、証明書 (.cer) ファイルをエクスポートして Microsoft Entra アプリ登録にアップロードします。

    **オプション 1**: mmc コンソールを使用して Windows 証明書ストアから証明書をエクスポートする:

    1. 次のコマンドを実行して、ローカル コンピューターの証明書管理コンソールを開きます。 このコマンドを実行する方法には、[スタート] メニュー、Windows 実行プロンプト、PowerShell プロンプト、またはコマンド プロンプトがあります。

        ```powershell
        certlm.msc
        ```
    2. コンソール ツリーで、エクスポートする証明書に移動します。
    3. 証明書を右クリックし、[ **すべてのタスク]** を選択し、[ **エクスポート**] を選択します。
    4. [証明書の **エクスポート ウィザードへようこそ**] 画面で、[ **次へ**] を選択します。
    5. 秘密キーのエクスポートを求められたら、[いいえ] を選択し **、秘密キーをエクスポートしません**。次へを選択 **します**。
    6. ファイル形式として、 **DER でエンコードされたバイナリ X.509 (.CER)** を選択し、[ **次へ**] を選択します。
    7. ファイル パスを入力または参照し、[ **次へ**] を選択します。
    8. 概要を確認し、[ **完了]** を選択します。

        **オプション 2**: PowerShell を使用して証明書をエクスポートする:

        ```powershell
        $cerFile  = "C:\Temp\MyBYOC.cer"
        $cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object {$_.Subject -eq 'CN=YOUR_CERTIFICATE_SUBJECT'}  
        Export-Certificate -Cert $cert -FilePath $cerFile
        ```
2. [Microsoft Entra 管理センター](https://entra.microsoft.com)で**、[アプリの登録**] に移動し、Connect Sync のインストール、構成、またはアップグレード中に作成されたアプリケーションを選択します。Connect Sync で使用されるアプリケーションを確認するには、`Get-ADSyncEntraConnectorCredential` コマンドを実行して、アプリケーション (クライアント) ID を取得します。 ユーザー名の形式は `{AppID}@tenantName.onmicrosoft.com`。 [ **証明書とシークレット**] で、[ **証明書のアップロード** ] を選択し、エクスポートした.cerファイルをアップロードして **、[追加]** を選択します。

    [Image: アプリ登録への証明書のアップロードを示すスクリーンショット。]
3. 次の PowerShell コマンドを使用して証明書ハッシュを取得する

    ```powershell
    $cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object {$_.Subject -eq 'CN=YOUR_CERTIFICATE_SUBJECT'}  
    
    # Get raw data from X509Certificate cert
    $certRawDataString = $cert.GetRawCertData()
    
    # Compute SHA256Hash of certificate 
    $sha256 = [System.Security.Cryptography.SHA256]::Create()
    $hashBytes = $sha256.ComputeHash($certRawDataString)
    ```

    **PowerShell バージョン 7** を使用している場合は、次のコマンドを使用します。

    ```powershell
    $certHash = [System.Convert]::ToHexString($hashBytes)
    ```

    **古い PowerShell バージョン**または **PowerShell ISE** を使用している場合は、次のコマンドを使用します。

    ```powershell
    $certHash = ($hashBytes|ForEach-Object ToString X2) -join ''
    ```
4. Connect Sync (ADSync) サービス アカウントに証明書の秘密キーを取得するアクセス許可を付与します。

    ```powershell
    $rsaCert = [System.Security.Cryptography.X509Certificates.RSACertificateExtensions]::GetRSAPrivateKey($cert)
    ```

    証明書が **証明機関 (CA) によって発行**された場合は、次の `$path` 変数を使用します。

    ```powershell
    $path = "$env:ALLUSERSPROFILE\Microsoft\Crypto\RSA\MachineKeys\$($rsaCert.key.UniqueName)"
    ```

    **自己署名**証明書を使用している場合は、次の`$path`変数を使用します。

    ```powershell
    $path = "$env:ALLUSERSPROFILE\Microsoft\Crypto\Keys\$($rsaCert.key.UniqueName)"
    ```
5. 次のコマンドを実行して、アクセス許可を付与します。

    ```powershell
    $permissions = Get-Acl -Path $path
    $serviceAccount = (Get-ItemProperty -Path HKLM:\SYSTEM\CurrentControlSet\Services\ADSync -Name ObjectName).ObjectName
    $rule = New-Object Security.Accesscontrol.FileSystemAccessRule "$serviceAccount", "read", allow
    $permissions.AddAccessRule($rule)
    Set-Acl -Path $path -AclObject $permissions
    
    # Verify permissions
    $permissions = Get-Acl -Path $path
    $permissions.Access
    ```
6. 同期スケジューラを無効にする

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $false
    ```
7. ADSync モジュールのインポート:

    **PowerShell バージョン 7** を使用している場合は、次のコマンドを使用して ADSync モジュールをインポートします。

    ```powershell
    Import-Module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync" -UseWindowsPowerShell
    ```

    **古い PowerShell バージョン**または **PowerShell ISE** を使用している場合は、次のコマンドを使用して ADSync モジュールをインポートします。

    ```powershell
    Import-Module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync"
    ```
8. 証明書ローテーション コマンドを使用してアプリケーション証明書を更新する

    ```powershell
    Invoke-ADSyncApplicationCredentialRotation -CertificateSHA256Hash $certHash 
    ```
9. 現在の 認証構成 ウィザード ビューを使用して、Microsoft Entra Connect で新しい証明書が使用されていることを確認します。
10. 同期スケジューラを有効にする

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $true
    ```
11. LOCAL\_MACHINE ストアから古い証明書を削除します。

## [BYOC を使用した BYOA](#tab/byoa-with-byoc-cert-renewal)
Microsoft Entra Connect Sync から警告が表示されたら、新しいキーと証明書を生成し、Microsoft Entra Connect Sync で使用される *証明書をローテーションすることを強くお勧めします* 。

1. 次のいずれかのオプションを使用して、証明書 (.cer) ファイルをエクスポートして Microsoft Entra アプリ登録にアップロードします。

    **オプション 1**: mmc コンソールを使用して Windows 証明書ストアから証明書をエクスポートする:

    1. 次のコマンドを実行して、ローカル コンピューターの証明書管理コンソールを開きます。 このコマンドを実行する方法には、[スタート] メニュー、Windows 実行プロンプト、PowerShell プロンプト、またはコマンド プロンプトがあります。

        ```powershell
        certlm.msc
        ```
    2. コンソール ツリーで、エクスポートする証明書に移動します。
    3. 証明書を右クリックし、[ **すべてのタスク]** を選択し、[ **エクスポート**] を選択します。
    4. [証明書の **エクスポート ウィザードへようこそ**] 画面で、[ **次へ**] を選択します。
    5. 秘密キーのエクスポートを求められたら、[いいえ] を選択し **、秘密キーをエクスポートしません**。次へを選択 **します**。
    6. ファイル形式として、 **DER でエンコードされたバイナリ X.509 (.CER)** を選択し、[ **次へ**] を選択します。
    7. ファイル パスを入力または参照し、[ **次へ**] を選択します。
    8. 概要を確認し、[ **完了]** を選択します。

    **オプション 2**: PowerShell を使用して証明書をエクスポートする:

    ```powershell
    $cerFile  = "C:\Temp\MyBYOC.cer"
    $cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object {$_.Subject -eq 'CN=YOUR_CERTIFICATE_SUBJECT'}  
    Export-Certificate -Cert $cert -FilePath $cerFile
    ```
2. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、[**アプリの登録**] に移動し、Connect Sync のインストール、構成、またはアップグレード中に作成されたアプリケーションを選択します。Connect Sync で使用されているアプリケーションを確認するには、`Get-ADSyncEntraConnectorCredential` コマンドを実行して、アプリケーション (クライアント) ID を取得します。 ユーザー名の形式は `{AppID}@tenantName.onmicrosoft.com`。 [ **証明書とシークレット**] で、[ **証明書のアップロード** ] を選択し、エクスポートした.cerファイルをアップロードして **、[追加]** を選択します。

    [Image: アプリ登録への証明書のアップロードを示すスクリーンショット。]
3. 次の PowerShell コマンドを使用して証明書ハッシュを取得する

    ```powershell
    $cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object {$_.Subject -eq 'CN=YOUR_CERTIFICATE_SUBJECT'}  
    
    # Get raw data from X509Certificate cert
    $certRawDataString = $cert.GetRawCertData()
    
    # Compute SHA256Hash of certificate 
    $sha256 = [System.Security.Cryptography.SHA256]::Create()
    $hashBytes = $sha256.ComputeHash($certRawDataString)
    ```

    **PowerShell バージョン 7** を使用している場合は、次のコマンドを使用します。

    ```powershell
    $certHash = [System.Convert]::ToHexString($hashBytes)
    ```

    **古い PowerShell バージョン**または **PowerShell ISE** を使用している場合は、次のコマンドを使用します。

    ```powershell
    $certHash = ($hashBytes|ForEach-Object ToString X2) -join ''
    ```
4. Connect Sync (ADSync) サービス アカウントに証明書の秘密キーを取得するアクセス許可を付与します。

    ```powershell
    $rsaCert = [System.Security.Cryptography.X509Certificates.RSACertificateExtensions]::GetRSAPrivateKey($cert)
    ```

    証明書が **証明機関 (CA) によって発行**された場合は、次の `$path` 変数を使用します。

    ```powershell
    $path = "$env:ALLUSERSPROFILE\Microsoft\Crypto\RSA\MachineKeys\$($rsaCert.key.UniqueName)"
    ```

    **自己署名**証明書を使用している場合は、次の`$path`変数を使用します。

    ```powershell
    $path = "$env:ALLUSERSPROFILE\Microsoft\Crypto\Keys\$($rsaCert.key.UniqueName)"
    ```
5. 次のコマンドを実行して、アクセス許可を付与します。

    ```powershell
    $permissions = Get-Acl -Path $path
    $serviceAccount = (Get-ItemProperty -Path HKLM:\SYSTEM\CurrentControlSet\Services\ADSync -Name ObjectName).ObjectName
    $rule = New-Object Security.Accesscontrol.FileSystemAccessRule "$serviceAccount", "read", allow
    $permissions.AddAccessRule($rule)
    Set-Acl -Path $path -AclObject $permissions
    
    # Verify permissions
    $permissions = Get-Acl -Path $path
    $permissions.Access
    ```
6. 同期スケジューラを無効にする

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $false
    ```
7. ADSync モジュールのインポート:

    **PowerShell バージョン 7** を使用している場合は、次のコマンドを使用して ADSync モジュールをインポートします。

    ```powershell
    Import-Module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync" -UseWindowsPowerShell
    ```

    **古い PowerShell バージョン**または **PowerShell ISE** を使用している場合は、次のコマンドを使用して ADSync モジュールをインポートします。

    ```powershell
    Import-Module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync"
    ```
8. 証明書ローテーション コマンドを使用してアプリケーション証明書を更新する

    ```powershell
    Invoke-ADSyncApplicationCredentialRotation -CertificateSHA256Hash $certHash 
    ```
9. 現在の 認証構成 ウィザード ビューを使用して、Microsoft Entra Connect で新しい証明書が使用されていることを確認します。
10. 同期スケジューラを有効にする

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $true
    ```
11. LOCAL\_MACHINE ストアから古い証明書を削除します。

---

### 証明書失効プロセス

Microsoft Entra Managed または BYOC のいずれかの自己署名証明書の場合、管理者は Microsoft Entra ID から `keyCredential` 値を削除して手動失効を実行する必要があります。 証明書のオンデマンド ローテーションもオプションです。

Microsoft Entra ID に登録されている証明機関によって発行された BYOC 証明書の場合、管理者は [証明書失効プロセス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list#enforce-crl-validation-for-cas)に従うことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/choose-ad-authn"} -->
## Microsoft Entra ハイブリッド ID ソリューションの認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このガイドは、CEO、CIO、CISO、チーフ ID アーキテクト、エンタープライズ アーキテクト、セキュリティと IT に関する意思決定者など、中規模から大規模な組織で Microsoft Entra ハイブリッド ID ソリューションの認証方法の選択を担当するユーザーの役に立ちます。

注

**この記事の範囲**: この記事では、Microsoft Entra ハイブリッド ID ソリューションの認証方法について説明します。 これらの認証方法は、使用される同期テクノロジ (Microsoft Entra Connect Sync またはクラウド同期) とは別に適用されます。 同期テクノロジを選択しても、ユーザー サインインの認証動作は決定または変更されません。

正しい認証方法の選択は、クラウドにアプリを移行しようとしている組織にとって最大の関心事です。 次の理由により、この決定を軽く考えてはなりません。

1. クラウドに移行する必要がある組織が最初に決定することです。
2. 認証方法は、クラウドにおける組織のプレゼンスの重要なコンポーネントです。 これによって、クラウドのすべてのデータとリソースへのアクセスが制御されます。
3. これは、Microsoft Entra ID での他の高度なセキュリティやユーザー エクスペリエンス機能すべての基盤となります。

ID は IT セキュリティの新しいコントロール プレーンであるため、認証は新しいクラウド環境への組織のアクセス ガードです。 組織には、セキュリティを強化し、クラウド アプリを侵入者から保護する ID コントロール プレーンが必要です。

注

認証方法を変更するには、計画、テスト、予測されるダウンタイムが必要です。 [段階的なロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)は、フェデレーションからクラウド認証へのユーザーの移行をテストするための優れた方法です。

#### 対象範囲外

オンプレミスに既存のディレクトリ フットプリントを持たない組織は、この記事の対象範囲外です。 通常、このような企業はクラウド内にのみ ID を作成し、ハイブリッド ID ソリューションを必要としません。 クラウド専用の ID はクラウドにのみ存在し、対応するオンプレミスの ID とは関連付けられません。

### 認証方法

新しいコントロール プレーンとして Microsoft Entra ハイブリッド ID ソリューションを採用する場合、認証がクラウド アクセスの基盤です。 正しい認証方法の選択は、Microsoft Entra ハイブリッド ID ソリューションのセットアップにおける最初の重要な決定です。 選択する認証方法は、クラウドでのユーザーのプロビジョニングも行う Microsoft Entra Connect を使って構成されます。

認証方法を選ぶには、時間、既存のインフラストラクチャ、複雑さ、および選んだ方法の実装にかかるコストを考慮する必要があります。 これらの要因は組織ごとに異なり、時間の経過とともに変化する場合があります。

Microsoft Entra ID は、ハイブリッド ID ソリューションに対して次の認証方法をサポートします。

#### クラウド認証

この認証方法を選ぶと、Microsoft Entra ID がユーザーのサインイン プロセスを処理します。 シングル サインオン (SSO) と組み合わせると、ユーザーは資格情報を再入力しなくてもクラウド アプリにサインインできます。 クラウド認証では、2 つのオプションから選ぶことができます。

**Microsoft Entra パスワード ハッシュ同期**。 Microsoft Entra ID でオンプレミスのディレクトリ オブジェクトの認証を有効にする最も簡単な方法です。 ユーザーはオンプレミスで使用しているものと同じユーザー名とパスワードを使用でき、他のインフラストラクチャをデプロイする必要はありません。 なお、Microsoft Entra ID 保護や [Microsoft Entra ドメイン サービス](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)など、一部の Microsoft Entra ID のプレミアム機能を利用するには、どの認証方法を選択しても パスワード ハッシュ同期が必要になります。

注

パスワードがクリア テキストで保存されたり、Microsoft Entra ID の復元可能なアルゴリズムで暗号化されたりすることはありません。 パスワード ハッシュ同期の実際のプロセスについて詳しくは、「[Microsoft Entra Connect 同期を使用したパスワード ハッシュ同期の実装](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)」をご覧ください。

**Microsoft Entra パススルー認証**。 1 つ以上のオンプレミス サーバーで実行されているソフトウェア エージェントを使用して、Microsoft Entra 認証サービスに簡単なパスワード検証を提供します。 オンプレミスの Active Directory を使用してサーバーで直接ユーザーが検証され、クラウドでパスワードの検証が行われることはありません。

オンプレミスのユーザー アカウントの状態、パスワード ポリシー、およびサインイン時間をすぐに適用するセキュリティ要件のある企業は、この認証方法を使用します。 パススルー認証の実際のプロセスについて詳しくは、「[Microsoft Entra パススルー認証によるユーザー サインイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)」をご覧ください。

#### フェデレーション認証

この認証方法を選ぶと、Microsoft Entra ID は別の信頼された認証システム (オンプレミスの Active Directory フェデレーション サービス (AD FS) など) に、ユーザーのパスワードを検証する認証プロセスを引き継ぎます。

この認証システムには、サードパーティの多要素認証など、その他の高度な認証要件がある場合があります。

次のセクションは、デシジョン ツリーを使用することで組織に適した認証方法を決定するのに役立ちます。 Microsoft Entra ハイブリッド ID ソリューションにクラウド認証とフェデレーション認証のどちらを展開すればよいかを判断できます。

### デシジョン ツリー

[Image: Microsoft Entra 認証に関するデシジョン ツリー]

意思決定に関する質問の詳細:

1. Microsoft Entra ID は、パスワードを検証するためのオンプレミス コンポーネントに依存せずにユーザーのサインインを処理できます。
2. Microsoft Entra ID は、Microsoft の AD FS などの信頼された認証プロバイダーにユーザーのサインイン情報を渡すことができます。
3. ユーザー レベルの Active Directory のセキュリティ ポリシー (アカウントの期限切れ、無効なアカウント、パスワードの期限切れ、アカウントのロックアウト、ユーザーのサインインごとのサインイン時間など) を適用する必要がある場合、Microsoft Entra ID では一部のオンプレミス コンポーネントが必要です。
4. Microsoft Entra ID によってネイティブでサポートされてないサインイン機能:
    - サード パーティの認証ソリューションを使用したサインイン。
    - 複数サイトのオンプレミス認証ソリューション。
5. Microsoft Entra ID Protection では、「*資格情報が漏洩したユーザー*」レポートを提供するために、選択されたサインイン方法には関係なくパスワード ハッシュの同期が必要です。 組織は主要なサインイン方法が失敗した場合、パスワード ハッシュ同期が失敗イベントの前に構成されていれば、パスワード ハッシュ同期にフェールオーバーできます。

注

Microsoft Entra ID Protection には、[Microsoft Entra ID P2](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) ライセンスが必要です。

### 詳細な考慮事項

#### クラウド認証: パスワード ハッシュの同期

- **作業量**。 パスワード ハッシュ同期は、展開、メンテナンス、インフラストラクチャに関して最小の作業量を必要とします。 ユーザーに必要なことが、Microsoft 365、SaaS アプリ、その他の Microsoft Entra ID ベースのリソースへのサインインのみである組織に対しては、通常、このレベルの作業量が適用されます。 パスワード ハッシュ同期をオンにすると、Microsoft Entra Connect 同期プロセスの一部となって 2 分ごとに実行されます。
- **ユーザー エクスペリエンス**。 ユーザーのサインイン エクスペリエンスを向上させるには、[Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)または [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)を使用します。 Windows デバイスを Microsoft Entra ID に参加させることができない場合は、パスワード ハッシュ同期を使用してシームレス SSO を展開することをお勧めします。 シームレス SSO によって、ユーザーのサインイン時に不要なプロンプトが表示されないようになります。
- **高度なシナリオ**。 組織が選択した場合、Microsoft Entra ID P2 を使用して、Microsoft Entra ID Protection レポートで ID からの分析情報を使用できます。 その 1 つの例が漏洩した資格情報レポートです。 Windows Hello for Business には、[パスワード ハッシュ同期を使用するときの特定の要件](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification)があります。 [Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance) では、ユーザーが会社の資格情報を使用してマネージド ドメインでプロビジョニングできるよう、パスワードのハッシュ同期が必要です。

    パスワード ハッシュ同期を使用する多要素認証が必要な組織は、Microsoft Entra の多要素認証または[条件付きアクセス カスタム コントロール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/controls#custom-controls-preview)を使用する必要があります。 これらの組織は、フェデレーションに依存する、サード パーティ製またはオンプレミスの多要素認証方法を使用できません。

注

Microsoft Entra 条件付きアクセスには、[Microsoft Entra ID P1](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) ライセンスが必要です。

- **ビジネス継続性**。 クラウド認証と共にパスワード ハッシュ同期を使用することは、すべての Microsoft データセンターに対応するようにスケーリングするクラウド サービスとして、高い可用性を実現します。 パスワード ハッシュ同期が長期間ダウンしないようにするには、2 つ目の Microsoft Entra Connect サーバーを、スタンバイ構成のステージング モードで展開します。
- **考慮事項**。 現在、パスワード ハッシュ同期では、オンプレミスのアカウントの状態の変化はすぐには適用されません。 このような状況では、ユーザーは、Microsoft Entra ID にユーザー アカウントの状態が同期されるまで、クラウド アプリにアクセスできます。 組織がこのような制限を回避する方法の 1 つは、管理者がオンプレミスのユーザー アカウントの状態を一括更新した後に、新しい同期サイクルを実行することです。 たとえば、アカウントを無効にします。

    **認証のタイミング**について: "クラウドで認証が行われます" とは、Microsoft Entra ID が資格情報を検証する場所を指します (提供されたパスワード ハッシュと格納されているハッシュを比較する)、サインインのパスワード変更の可用性は同期のタイミングによって異なります。 ユーザーがオンプレミスでパスワードを変更した場合、ユーザーがサインインする前に、新しいパスワード ハッシュを Microsoft Entra ID に同期する必要があります。 通常、この同期プロセスは 2 分ごとに実行されますが、構成によって異なる場合があります。

注

現在、パスワードの期限切れとアカウントのロックアウトの状態は、Microsoft Entra ID と Microsoft Entra Connect では同期されません。 ユーザーのパスワードを変更し、*[ユーザーは次回ログオン時にパスワードの変更が必要]* フラグを設定した場合、ユーザーが自分のパスワードを変更するまで Microsoft Entra ID と Microsoft Entra Connect ではパスワード ハッシュは同期されません。

展開手順については、[パスワード ハッシュ同期の実装](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)に関する記事をご覧ください。

#### クラウド認証: パススルー認証

- **作業量**。 パススルー認証の場合、既存のサーバーに、1 つまたは複数 (推奨は 3 つ) の軽量のエージェントをインストールする必要があります。 これらのエージェントは、オンプレミスの AD ドメイン コントローラーなど、オンプレミスの Active Directory Domain Services にアクセスできる必要があります。 これらは、インターネットへの発信アクセスと、ドメイン コントローラーへのアクセスが必要です。 このため、境界ネットワーク内にエージェントを展開することはできません。

    パススルー認証には、ドメイン コントローラーへの制約なしのネットワーク アクセスが必要です。 すべてのネットワーク トラフィックは暗号化され、認証要求に制限されます。 このプロセスの詳細については、パススルー認証での[セキュリティの詳細](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-security-deep-dive)に関する記事をご覧ください。
- **ユーザー エクスペリエンス**。 ユーザーのサインイン エクスペリエンスを向上させるには、[Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)または [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)を使用します。 Windows デバイスを Microsoft Entra ID に参加させることができない場合は、パスワード ハッシュ同期を使用してシームレス SSO を展開することをお勧めします。 シームレス SSO によって、ユーザーのサインイン時に不要なプロンプトが表示されないようになります。
- **高度なシナリオ**。 パススルー認証では、サインインの時点でオンプレミスのアカウント ポリシーが適用されます。 たとえば、オンプレミスのユーザーのアカウントの状態が無効、ロックアウト、[パスワードが期限切れ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-faq#what-happens-if-my-user-s-password-expired-and-they-try-to-sign-in-by-using-pass-through-authentication-)であるか、ログオンの試行時にユーザーに許可されているサインイン時間が超過した場合、アクセスは拒否されます。

    パススルー認証を使用する多要素認証が必要な組織は、Microsoft Entra 多要素認証または[条件付きアクセス カスタム コントロール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/controls#custom-controls-preview)を使用する必要があります。 これらの組織は、フェデレーションに依存する、サード パーティ製またはオンプレミスの多要素認証方法を使用できません。 また高度な機能の利用には、パススルー認証を選択するかどうかにかかわらず、パスワード ハッシュ同期が必要になります。 高度な機能とは、例えばMicrosoft Entra ID 保護による漏洩した認証情報の検出などです。
- **ビジネス継続性**。 2 つの追加パススルー認証エージェントを展開することをお勧めします。 Microsoft Entra Connect サーバー上の最初のエージェントに加えて、これらを追加します。 この追加の展開によって、認証要求の高可用性が保証されます。 3 つのエージェントを展開すると、メンテナンスのために 1 つのエージェントを停止しても、まだ 1 つのエージェントの障害に対応できます。

    パススルー認証だけでなくパスワード ハッシュ同期も展開することの利点は、もう 1 つあります。 それは、プライマリ認証方法を利用できなくなった場合に、バックアップの認証方法として機能することです。
- **考慮事項**。 エージェントがオンプレミスで発生した重大な障害によってユーザーの資格情報を検証できない場合は、パススルー認証のバックアップの認証方法としてパスワード ハッシュ同期を使用できます。 パスワードハッシュ同期へのフェールオーバーは自動的には行われないため、Microsoft Entra Connect を使用してサインイン方法を手動で切り替える必要があります。

    代替 ID のサポートなど、パススルー認証におけるその他の考慮事項については、[よく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-faq)をご覧ください。

展開手順については、[パススルー認証の実装](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)に関する記事をご覧ください。

#### フェデレーション認証

- **作業量**。 フェデレーション認証システムでは、信頼できる外部システムを利用してユーザーを認証します。 フェデレーション システムへの既存の投資を Microsoft Entra ハイブリッド ID ソリューションで再利用したい会社もあります。 フェデレーション システムの管理とメンテナンスは、Microsoft Entra ID のコントロールの範囲外です。 フェデレーション システムが安全に展開されていて、認証の負荷を処理できるかどうかを確認するのは、フェデレーション システムを使用している組織の責任です。
- **ユーザー エクスペリエンス**。 フェデレーション認証のユーザー エクスペリエンスは、機能、トポロジ、およびフェデレーション ファーム構成の実装に依存します。 組織によっては、セキュリティ要件に合わせてフェデレーション ファームへのアクセスを調整したり構成したりするために、この柔軟性が必要になります。 たとえば、内部的に接続されているユーザーとデバイスを構成して、資格情報の入力を要求することなく、自動的にユーザーをサインインさせることができます。 この構成が機能するのは、ユーザーが既にデバイスにサインインしているためです。 必要な場合は、高度なセキュリティ機能を利用してユーザーのサインイン プロセスをさらに困難にすることもできます。
- **高度なシナリオ**。 フェデレーション認証ソリューションが必要になるのは、Microsoft Entra ID によってネイティブにサポートされていない認証要件がある場合です。 [適切なサインイン オプションを選択する](https://learn.microsoft.com/ja-jp/archive/blogs/samueld/choosing-the-right-sign-in-option-to-connect-to-azure-ad-office-365)のに役立つ詳しい情報をご覧ください。 次の一般的な要件で考えてみましょう。

    - フェデレーション ID プロバイダーを必要とするサード パーティの多要素プロバイダー。
    - サード パーティの認証ソリューションを使用する認証。 「[Microsoft Entra のフェデレーション互換性リスト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-compatibility)」を参照してください。
    - ユーザー プリンシパル名 (UPN) (例: user@domain.com) ではなく、sAMAccountName (例: DOMAIN\username) を必要とするサインイン。
- **ビジネス継続性**。 フェデレーション システムでは、通常、負荷分散されたサーバーのアレイ (ファームとも呼ばれます) が必要になります。 このファームは、認証要求の高可用性を保証するために、内部ネットワークおよび境界ネットワークのトポロジに構成されます。

    プライマリ認証方法を使用できないときのために、フェデレーション認証と共に、パスワード ハッシュ同期をバックアップ認証方法として展開します。 たとえば、オンプレミスのサーバーが利用できない場合です。 大規模なエンタープライズ組織では、認証要求の待機時間を短くするため、geo-DNS で構成された複数のインターネット イングレス ポイントをフェデレーション ソリューションでサポートすることが必要になる場合があります。
- **考慮事項**。 フェデレーション システムでは通常、オンプレミスのインフラストラクチャへのより大きな投資が必要です。 オンプレミスのフェデレーションに既に投資している組織のほとんどは、このオプションを選択します。 または、ビジネス上の理由から、単一の ID プロバイダーを使用することがどうしても必要な場合です。 運用とトラブルシューティングは、クラウド認証ソリューションよりフェデレーション認証の方が複雑です。

Microsoft Entra ID では検証できないルーティング不可能なドメインの場合、ユーザー ID によるサインインを実装するには追加の構成が必要になります。 この要件は、代替ログイン ID のサポートと呼ばれます。 制限事項と要件については、「[Configuring Alternate Login ID](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id)」(代替ログイン ID の構成) をご覧ください。 フェデレーションを使用したサード パーティの多要素認証プロバイダーを使用する場合は、デバイスが Microsoft Entra ID に参加できるよう、プロバイダーが WS-Trust をサポートしていることを確実にしてください。

展開手順については、「[Deploying Federation Servers](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/deploying-federation-servers)」(フェデレーション サーバーの展開) をご覧ください。

注

Microsoft Entra ハイブリッド ID ソリューションを展開するときは、Microsoft Entra Connect のサポートされているトポロジの 1 つを実装する必要があります。 サポートされている構成とサポートされていない構成について詳しくは、「[Microsoft Entra Connect のトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies)」をご覧ください。

### アーキテクチャ図

以下の図では、Microsoft Entra ハイブリッド ID ソリューションで使用できる各認証方法に必要な、アーキテクチャ コンポーネントの概要を示します。 これにより、ソリューション間の相違点を比較することができます。

- パスワード ハッシュ同期ソリューションのシンプルさ:

    [Image: パスワード ハッシュ同期を使用する Microsoft Entra ハイブリッド ID]
- 冗長性のために 2 つのエージェントを使用する、パススルー認証のエージェント要件:

    [Image: パススルー認証を使用する Microsoft Entra ハイブリッド ID]
- 組織の境界ネットワークと内部ネットワークでのフェデレーションに必要なコンポーネントの概要:

    [Image: フェデレーション認証を使用する Microsoft Entra ハイブリッド ID]

### 方法の比較

| 考慮事項 | パスワード ハッシュの同期 | パススルー認証 | AD FS とのフェデレーション |
| --- | --- | --- | --- |
| 認証が行われる場所 | クラウド - パスワード ハッシュが同期され、Microsoft Entra ID が資格情報を検証する | クラウド内で、オンプレミスの認証エージェントとのセキュリティで保護されたパスワード検証の交換後 | オンプレミス |
| プロビジョニング システム (Microsoft Entra Connect) 以外のオンプレミスのサーバーの要件とは何ですか? | なし | 追加の認証エージェントごとに 1 つのサーバー | 2 つ以上の AD FS サーバー境界/DMZ ネットワークに 2 つ以上の WAP サーバー |
| プロビジョニング システム以外のオンプレミスのインターネットおよびネットワークの要件 | なし | 認証エージェントを実行しているサーバーからの[発信インターネット アクセス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start) | 境界ネットワークの WAP サーバーへのインターネットからのアクセス境界の WAP サーバーから AD FS サーバーへの着信ネットワーク アクセスネットワークの負荷分散 |
| TLS/SSL 証明書の要件 | いいえ | いいえ | はい |
| 稼働状況の監視ソリューションはあるか? | 必要なし | [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-pass-through-authentication)によって提供されるエージェントの状態 | [Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs) |
| ユーザーは会社のネットワーク内のドメイン参加済みデバイスからクラウド リソースにシングル サインオンするか? | [Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)、[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)、[Apple デバイス用の Microsoft Enterprise SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)、または[シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) の場合は、はい | [Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)、[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)、[Apple デバイス用の Microsoft Enterprise SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)、または[シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) の場合は、はい | はい |
| サポートされているサインインの種類 | UserPrincipalName + パスワード[シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) を使用した Windows 統合認証[代替ログイン ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom)[Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)[証明書とスマート カード認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-smartcard) | UserPrincipalName + パスワード[シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) を使用した Windows 統合認証[代替ログイン ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-faq)[Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)[証明書とスマート カード認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-smartcard) | UserPrincipalName + パスワードsAMAccountName + パスワードWindows 統合認証[証明書とスマート カード認証](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-user-certificate-authentication)[代替ログイン ID](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id) |
| Windows Hello for Business のサポート | [キー信頼モデル](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification)[ハイブリッド クラウドの信頼](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-cloud-trust) | [キー信頼モデル](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification)[ハイブリッド クラウドの信頼](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-cloud-kerberos-trust)*いずれも Windows Server 2016 ドメインの機能レベルが必要* | [キー信頼モデル](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification)[ハイブリッド クラウドの信頼](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-cloud-kerberos-trust)[証明書信頼モデル](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-key-trust-adfs) |
| 多要素認証のオプション | [Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/azure/multi-factor-authentication/)[条件付きアクセスを使用するカスタム コントロール*](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/controls) | [Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/)[条件付きアクセスを使用するカスタム コントロール*](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/controls) | [Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/)[サード パーティの MFA](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-additional-authentication-methods-for-ad-fs)[条件付きアクセスを使用するカスタム コントロール*](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/controls) |
| サポートされているユーザー アカウントの状態は何ですか？ | 無効なアカウント(最大 30 分の遅延) | 無効なアカウントアカウントのロックアウトアカウント期限切れパスワード期限切れサインイン時間 | 無効なアカウントアカウントのロックアウトアカウント期限切れパスワード期限切れサインイン時間 |
| 条件付きアクセスのオプション | [Microsoft Entra ID P1 または P2 を使用した Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) | [Microsoft Entra ID P1 または P2 を使用した Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) | [Microsoft Entra ID P1 または P2 を使用した Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)[AD FS のアクセス制御ポリシー](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/create-a-rule-to-permit-or-deny-users-based-on-an-incoming-claim) |
| 従来のプロトコルのブロックはサポートされるか? | [あり](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) | [あり](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) | [あり](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/access-control-policies-w2k12) |
| サインイン ページのロゴ、イメージ、説明のカスタマイズ可能性 | [はい (Microsoft Entra ID P1 または P2 を使用)](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding) | [はい (Microsoft Entra ID P1 または P2 を使用)](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding) | [あり](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-management) |
| サポートされる高度なシナリオ | [スマート パスワード ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)[漏洩した資格情報レポート (Microsoft Entra ID P2 を使用)](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) | [スマート パスワード ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout) | 複数サイトの低待機時間の認証システム[AD FS エクストラネットのロックアウト](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-extranet-soft-lockout-protection)[サード パーティの ID システムとの統合](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-compatibility) |

注

Microsoft Entra 条件付きアクセスでのカスタム コントロールは、現時点ではデバイスの登録をサポートしていません。

### 推奨事項

ID システムによって、クラウドに移行して利用できるようにしたアプリに対するユーザーのアクセスが保証されます。 どの認証方法を選択する場合でも、次の理由により、パスワード ハッシュ同期を使用するか有効にします。

1. **高可用性とディザスター リカバリー**。 パススルー認証とフェデレーションは、オンプレミスのインフラストラクチャに依存します。 パススルー認証の場合、オンプレミスのフットプリントには、パススルー認証エージェントに必要なサーバー ハードウェアとネットワークが含まれます。 フェデレーションの場合、オンプレミスのフットプリントはさらに大きくなります。 認証要求と内部フェデレーション サーバーをプロキシするために、境界ネットワーク内にサーバーが必要になるためです。

    単一障害点を回避するには、冗長サーバーを展開します。 これにより、いずれかのコンポーネントで障害が発生しても、認証要求サービスが常に提供されるようにします。 また、パススルー認証とフェデレーションは、認証要求に応答するために、両方ともドメイン コントローラーにも依存しますが、ここでも障害が発生する可能性があります。 これらのコンポーネントの多くは、正常性を保つためにメンテナンスが必要です。 メンテナンスの計画や実装が適切でない場合は、システムが停止する可能性が高くなります。
2. **オンプレミスの停止への対応**。 サイバー攻撃や災害によるオンプレミスの停止の影響は、ブランドの評判が損なわれることから、組織が麻痺して攻撃に対処できなくなることまで、大きな範囲に及ぶ可能性があります。 近年でも、オンプレミスのサーバーがダウンする原因となった特定対象へのランサムウェアなど、多くの組織がマルウェア攻撃の被害を受けました。 Microsoft は、この種の攻撃への対処を支援するなかで、組織には 2 つのカテゴリがあることに気付きました。

    - 以前にフェデレーション認証またはパススルー認証でパスワード ハッシュ同期をオンにしていた組織は、パスワード ハッシュ同期を使用するようにプライマリ認証方法を変更しました。 彼らは数時間でオンラインに戻りました。 Microsoft 365 を介して電子メールにアクセスすることで、問題解決と他のクラウド ベースのワークロードへのアクセスのために作業することができました。
    - 事前にパスワード ハッシュ同期を有効にしていなかった組織は、問題解決のために、信頼されていない外部のコンシューマー向けメール システムを通信に利用するしかありませんでした。 そのような企業は、ユーザーがクラウドベースのアプリに再度サインインできるよう、オンプレミスの ID インフラストラクチャを復元するのに数週間かかりました。
3. **ID 保護** クラウド内のユーザーを保護する最適な方法の 1 つは、Microsoft Entra ID P2 を使用した Microsoft Entra ID 保護 です。 Microsoft は常にインターネットを精査して、闇サイトで販売され利用されているユーザーやパスワードのリストを入手しています。 Microsoft Entra ID はこの情報を使って、組織のユーザー名やパスワードのいずれかが侵害されているかどうかを確認します。 したがって、使用している認証方法がフェデレーション認証かパススルー認証かに関係なく、パスワード ハッシュ同期を有効にすることが重要になります。 漏洩した資格情報は、レポートとして示されます。 ユーザーが漏洩したパスワードでのサインインを試みた場合、この情報を使用してユーザーをブロックするか、パスワードの変更を強制します。

### まとめ

この記事では、組織がクラウド アプリへのアクセスをサポートするために構成して展開できるさまざまな認証オプションの概要を説明しました。 ビジネス、セキュリティ、テクノロジに関するさまざまな要件を満たすため、組織は、パスワード ハッシュ同期、パススルー認証、およびフェデレーション認証のいずれかを選択できます。

各認証方法を検討してください。 ソリューションを展開するための作業量や、サインイン プロセスでのユーザーのエクスペリエンスは、ビジネス要件に合っているでしょうか。 各認証方法において、組織が高度なシナリオとビジネス継続性機能を必要とするかどうかを評価してください。 最後に、各認証方法の考慮事項を評価してください。 選択した方法を実装する妨げになる項目はありませんか。
<!-- /MSL-PAGE -->
