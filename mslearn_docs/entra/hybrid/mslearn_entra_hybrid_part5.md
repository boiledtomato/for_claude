# Microsoft Learn — Microsoft Entra / ハイブリッド ID (Connect / Cloud Sync) (part 5)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 27

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/whatis-azure-ad-connect-v2"} -->
## Microsoft Entra Connect V2 の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect の次のバージョンについて説明します。

Azure AD Connect V1は数年前にリリースされました。 それ以降、使用されているいくつかのコンポーネントが非推奨となり、新しいバージョンに更新されています。 このすべてのコンポーネントを個別に更新するには、時間と計画が必要です。

この問題に対処するために、新しいコンポーネントの多くが新しい単一のリリースにバンドルされたため、更新は 1 回で済みます。 このリリースは、Microsoft Entra Connect V2 です。 このリリースは、ハイブリッド ID の目標を実現するために使用される同じソフトウェアの新しいバージョンであり、最新の基本コンポーネントを使用して構築されています。

注

Azure AD Connect V1 は 2022 年 8 月 31 日に廃止され、サポートされなくなりました。 Azure AD Connect V1 のインストールは、**予期せず動作しなくなる**可能性があります。 Azure AD Connect V1 をまだ使用している場合は、アップグレードするか、Microsoft Entra Cloud Syncに移行することを検討する必要があります。

### Microsoft Entra クラウド同期への移行を検討する

Microsoft Entra Cloud Sync は、クラウドから動作する新しい同期クライアントであり、お客様はオンラインで同期のユーザー設定を行い、管理できます。 Cloud Sync を使用して同期エクスペリエンスを向上させる新機能が導入されているため、Cloud Sync を使用することをお勧めします。クラウド同期が適切なオプションである場合は、クラウド同期を選択することで、今後の移行を回避できます。 [サポートされている同期シナリオの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)を使用して、Cloud Sync が適切な同期クライアントであるかどうかを確認します。

クラウド同期がビジネスに価値を提供する方法を理解するには、次のビデオをご覧ください。

詳細については、「[クラウド同期とは](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/what-is-cloud-sync)」を参照してください。

### 主な変更は?

#### SQL Server 2019 LocalDB

以前のバージョンの Microsoft Entra Connect は、SQL Server 2012 LocalDB に付属して出荷されます。 V2.0 は SQL Server 2019 LocalDB 付きで出荷されます。これにより、安定性とパフォーマンスが向上し、セキュリティ関連のいくつかのバグ修正が行われます。 SQL Server 2012 は、2022 年 7 月に延長サポートを終了しました。 詳しくは、[Microsoft SQL 2019](https://www.microsoft.com/sql-server/sql-server-2019)をご覧ください。

#### MSAL 認証ライブラリ

以前のバージョンの Microsoft Entra Connect は、ADAL 認証ライブラリに付属して出荷されます。 このライブラリは、2022 年 12 月の時点で非推奨です。 V2 リリースには、新しい MSAL ライブラリが付属しています。 詳細については、[「MSAL ライブラリの概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview)」を参照してください。

#### Visual C++ Redist 14

SQL Server 2019 には Visual C++ Redist 14 ランタイムが必要であるため、このバージョンを使用するように C++ ランタイム ライブラリを更新します。 この再頒布可能パッケージは Microsoft Entra Connect V2 パッケージと共にインストールされるため、C++ ランタイムの更新プログラムに対して何らかの操作を行う必要はありません。

#### TLS 1.2

重要

バージョン 2.3.20.0 はセキュリティ更新プログラムです。 この更新プログラムでは、Microsoft Entra Connect に TLS 1.2 が必要です。 このバージョンに更新する前に、TLS 1.2 が有効になっていることを確かめてください。

すべてのバージョンの [Windows Server が TLS 1.2 をサポートしています](https://learn.microsoft.com/ja-jp/windows-server/security/tls/tls-ssl-schannel-ssp-overview)。 サーバーで TLS 1.2 が有効になっていない場合は、Microsoft Entra Connect V2.0 を展開する前に、これを有効にする必要があります。

TLS 1.2 が有効かどうかを確認する PowerShell スクリプトについては、「[TLS を確認する PowerShell スクリプト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement#powershell-script-to-check-tls-12)」を参照してください

TLS 1.2 について詳しくは、[Microsoft セキュリティ アドバイザリ 2960358](https://learn.microsoft.com/ja-jp/security-updates/SecurityAdvisories/2015/2960358) に関するページを参照してください。 TLS 1.2 の有効化の詳細については、「[TLS 1.2 を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement)」を参照してください

TLS1.0 および TLS 1.1 は、安全でないとみなされているプロトコルです。 これらは Microsoft では非推奨です。 このリリースの Microsoft Entra Connect では、TLS 1.2 のみがサポートされます。 Microsoft Entra Connect V2 でサポートされているすべてのバージョンの Windows Server では、既に TLS 1.2 が既定になっています。 サーバーで TLS 1.2 がサポートされていない場合は、Microsoft Entra Connect V2 をデプロイする前に、この機能を有効にする必要があります。 詳細については、「[Microsoft Entra Connect への TLS 1.2 の適用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement)」を参照してください。

#### SHA2 で署名されたすべてのバイナリ

一部のコンポーネントには SHA1 署名済みバイナリがあることがわかりました。 ダウンロード可能なバイナリに対して SHA1 はサポートされなくなりました。すべてのバイナリを SHA2 署名にアップグレードしました。 デジタル署名は、更新プログラムが Microsoft から直接取得され、配信中に改ざんされないようにするために使われます。 SHA-1 アルゴリズムには欠点があり、業界標準に合わせるために、より安全な SHA-2 アルゴリズムを使用するように Windows 更新プログラムの署名が変更されました。

ユーザー側で必要な操作はありません。

#### Windows Server 2012 と Windows Server 2012 R2 はサポートされなくなりました

SQL Server 2019 には、サーバー オペレーティング システムとして Windows Server 2016 以降が必要です。 Microsoft Entra Connect v2 には SQL Server 2019 コンポーネントが含まれているため、以前のバージョンの Windows サーバーをサポートすることはできなくなりました。

このバージョンを以前のバージョンの Windows サーバーにインストールすることはできません。 Microsoft Entra Connect サーバーを Windows サーバー2019にアップグレードすることをお勧めします。これは、Windows サーバーのオペレーティング システムの最新バージョンです。

この[記事で](https://learn.microsoft.com/ja-jp/windows-server/get-started/install-upgrade-migrate)は、古い Windows サーバー バージョンから Windows サーバー 2019 へのアップグレードについて説明します。

#### PowerShell 5.0

このリリースの Microsoft Entra Connect には、PowerShell 5.0 を必要とするいくつかのコマンドレットが含まれているため、この要件は Microsoft Entra Connect の新しい前提条件です。

PowerShell の前提条件の詳細については、[こちら](https://learn.microsoft.com/ja-jp/powershell/scripting/windows-powershell/install/windows-powershell-system-requirements#windows-powershell-50)を参照してください。

注

PowerShell 5 は既に Windows Server 2016 に組み込まれているため、最近のバージョンの Windows Server を使用していれば、アクションを実行する必要はありません。

### 他に知るべきことはありますか?

**このアップグレードが重要なのはなぜですか?** 現在の Microsoft Entra Connect サーバー インストールのコンポーネントの一部はサポートされなくなりました。 サポートされていない製品を使用している場合、サポート チームが組織に必要なサポート エクスペリエンスを提供することは困難です。 そのため、できるだけ早くこの新しいバージョンにアップグレードすることをお勧めします。

このアップグレードが特に重要なのは、Microsoft Entra Connect の前提条件が更新され、更新された前提条件に対応するためにサーバーを計画して更新する時間が新たに必要となる可能性があるためです

**理解しておくべき新機能はありますか?** いいえ。リリース V2.0 には新しい機能がありません。 このリリースには、Microsoft Entra Connect 上の一部の基本コンポーネントの更新プログラムのみが含まれています。

**以前のバージョンから V2 にアップグレードすることはできますか?** はい – 以前のバージョンの Microsoft Entra Connect から Microsoft Entra Connect V2 へのアップグレードがサポートされています。 [この記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)のガイダンスに従って、最適なアップグレード方法を決定してください。 アップグレードする前に、Microsoft Entra Cloud Sync への移行を検討してください。

**現在のサーバーの構成をエクスポートして Microsoft Entra Connect V2 にインポートすることはできますか?** はい、できます。これは、特に新しいオペレーティング システムのバージョンにアップグレードする場合なら特に、Microsoft Entra Connect V2 に移行するための優れた方法です。 インポート/エクスポートの構成機能の詳細と、この[記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-import-export-config)での使用方法については、こちらを参照してください。

**Microsoft Entra Connect の自動アップグレードを有効にしました - この新しいバージョンを自動的に取得しますか?** はい - 自動アップグレード機能を有効にした場合、Microsoft Entra Connect サーバーは最新リリースにアップグレードされます。 ただし、Windows Server 2016 以降を使用していて TLS 1.2 を有効にしている場合にのみ、サーバーをアップグレードできます。

**まだアップグレードする準備ができていませんが、どれくらいの時間がありますか?** できるだけ早く Microsoft Entra Connect V2 にアップグレードする必要があります。 **すべての Azure AD Connect V1 バージョンは、2022 年 8 月 31 日に廃止されます。** 現時点では、Microsoft Entra Connect の古いバージョンを引き続きサポートします。 ただし、Microsoft Entra Connect のコンポーネントの一部がサポートから除外された場合、適切なサポート エクスペリエンスを提供することは困難な場合があります。 このアップグレードは、ADAL および TLS 1.0/1.1 では特に重要であり、非推奨とされた後、これらのサービスが予期せず動作しなくなる可能性があります。

**外部の SQL データベースを使用していて、SQL 2012 LocalDb を使用していない場合でも、アップグレードが必要ですか?** はい。TLS 1.0/1.1 と ADAL が非推奨となるため、SQL Server 2012 を使用しない場合でも、サポートされている状態を維持するためにアップグレードする必要があります。 Microsoft Entra Connect V2 を使用して、SQL Server 2012 を外部の SQL データベースとして引き続き使用できることに注意してください。 Microsoft Entra Connect V2 の SQL 2019 ドライバーは SQL Server 2012 と互換性があります。

**Microsoft Entra Connect インスタンスを V2 にアップグレードした後、SQL 2012 コンポーネントは自動的にアンインストールされますか?** いいえ。SQL 2019 へのアップグレードでは、サーバーから SQL 2012 コンポーネントが削除されることはありません。 これらのコンポーネントが不要になった場合は、の SQL Server アンインストール手順 従う必要があります。

**アップグレードしなかった場合に行われる処理は?** インベントリから削除されるコンポーネントのいずれかが実際に非推奨とされるまでは、影響はありません。 Microsoft Entra Connect は作業を続けます。

TLS 1.0/1.1 のサポートは 2022 年に非推奨となり、サービスが予期せず停止する可能性があるため、その日までにこれらのプロトコルを使っていないことを確認する必要があります。 ただし、サーバーを TLS 1.2 用に手動で構成できます。この場合、Microsoft Entra Connect を V2 に更新する必要はありません。

Microsoft Entra Connect Health は、2023 年 3 月以降に動作を停止する可能性があります。 それまでにすべての Health エージェントを新しいバージョンに自動アップグレードしますが、V バージョンとの互換性の問題により、Azure AD Connect V1 を実行している場合は自動アップグレードできません。

2022 年 12 月以降に、ADAL のサポートが終了する予定です。 ADAL がサポート対象外になると、認証が予期せず動作しなくなる可能性があり、これにより Microsoft Entra Connect サーバーが正常に動作しなくなる可能性があります。 2022 年 12 月より前に Microsoft Entra Connect V2 にアップグレードすることを強く推奨します。 現在の Microsoft Entra Connect バージョンでサポートされている認証ライブラリにアップグレードすることはできません。

**V2 にアップグレードした後、ADSync PowerShell コマンドレットが機能しませんか?** これは既知の問題です。 V2 をインストールまたはアップグレードした後に、PowerShell セッションを再起動し、モジュールを再インポートします。 次の指示に従って、モジュールをインポートします。

1. 管理者特権で Windows PowerShell を開きます。
2. 次のコードを入力するか、コピーして貼り付けます。

    ```powershell
    Import-module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync"
    ```

### Microsoft Entra Connect V2 を使用するためのライセンス要件

この機能の使用は無料で、Azure サブスクリプションに含まれています。

### Microsoft Entra Connect Health を使用するためのライセンス要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/whatis-fed"} -->
## Microsoft Entra ID とのフェデレーションとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra ID とのフェデレーションについて説明します。

フェデレーションは、信頼を確立したドメインのコレクションです。 信頼のレベルは異なる場合がありますが、通常は認証が含まれており、ほとんどの場合、承認が含まれます。 一般的なフェデレーションには、一連のリソースへの共有アクセスに対する信頼が確立された複数の組織が含まれる場合があります。

オンプレミス環境を Microsoft Entra ID とフェデレーションし、このフェデレーションを認証と承認に使用できます。 このサインイン方法により、すべてのユーザー認証がオンプレミスで行われます。 この方法により、管理者はより厳密なレベルのアクセス制御を実装できます。 AD FS と PingFederate とのフェデレーションを利用できます。

[Image: フェデレーション ID]

ヒント

Active Directory フェデレーション サービス (AD FS) とのフェデレーションを使用する場合は、AD FS インフラストラクチャが失敗した場合に備えて、必要に応じてパスワード ハッシュ同期をバックアップとして設定できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/whatis-phs"} -->
## Microsoft Entra ID とのパスワード ハッシュ同期とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: パスワード ハッシュ同期について説明します。

パスワード ハッシュ同期は、ハイブリッド ID を実現するために使用されるサインイン方法の 1 つです。 Microsoft Entra Connect は、ユーザーのパスワードのハッシュをオンプレミスの Active Directory インスタンスからクラウドベースの Microsoft Entra インスタンスに同期します。

パスワード ハッシュ同期は、Microsoft Entra Connect Sync によって実装されるディレクトリ同期機能の拡張機能です。この機能を使用して、Microsoft 365 などの Microsoft Entra サービスにサインインできます。 オンプレミスの Active Directory インスタンスへのサインインに使用するのと同じパスワードを使用して、サービスにサインインします。

[Image: Microsoft Entra Connect とは]

パスワード ハッシュ同期は、パスワードの数を減らすことで役立ちます。ユーザーは 1 つだけに維持する必要があります。 パスワード ハッシュ同期では、次のことができます。

- ユーザーの生産性を向上させます。
- ヘルプデスクのコストを削減します。

パスワード ハッシュの同期では、ハイブリッド アカウントの[漏洩資格情報検出](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#leaked-credentials)も有効になります。 Microsoft は、ダーク Web 研究者や法執行機関と協力して、公開されているユーザー名とパスワードのペアを見つけます。 これらのペアのいずれかがユーザーのペアと一致する場合、関連するアカウントは高リスクに移行されます。

注

PHS を有効にした後に検出された新しい漏洩した資格情報のみが、テナントに対して処理されます。 以前に見つかった資格情報ペアに対する検証は実行されません。

サインイン方法として [Active Directory フェデレーション サービス (AD FS) とのフェデレーション](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-whatis)を使用する場合、必要に応じて、バックアップとしてパスワード ハッシュ同期を設定することができます。

環境内でパスワード ハッシュ同期を使用するには、次の操作を行う必要があります。

- Microsoft Entra Cloud Sync エージェントをインストールします。
- オンプレミスの Active Directory インスタンスと Microsoft Entra インスタンスの間でディレクトリ同期を構成します。
- パスワード ハッシュ同期を有効にします。

詳細については、「[What is hybrid identity?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity)」 (ハイブリッド ID とは) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/decommission-connect-sync-v1"} -->
## Azure AD Connect V1 の使用停止 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/decommission-connect-sync-v1
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、Azure AD Connect V1 の使用停止と、V2 への移行方法について説明します。

Azure AD Connect V1 の提供終了に関する 1 年間の事前通知は、2021 年 8 月に発表されました。 2022 年 8 月 31 日の時点で、すべての V1 バージョンがサポート対象外となり、任意の時点で予期せず動作を停止する可能性がありました。

2023 年 10 月 1 日、Microsoft Entra クラウド サービスは Azure AD Connect V1 サーバーからの接続の受け入れを停止し、ID は同期しなくなりました。

Azure AD Connect V1 をまだ使用している場合は、すぐにアクションを実行する必要があります。

### クラウド同期に移行する

Microsoft Entra Connect Sync に移行する前に、代わりにクラウド同期が適しているかどうかを確認する必要があります。 クラウド同期では軽量のプロビジョニング エージェントが使用され、ポータルから完全に構成できます。 状況に最適な同期ツールを選択するには、[サポートされている同期シナリオの比較を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)使用します。

環境とニーズに基づいて、クラウド同期に移行する資格があります。クラウド同期と接続同期の比較については、「[クラウド同期と接続同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)の比較」を参照してください。詳細については、「クラウド同期とは [」を参照してください。](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync) と [プロビジョニング エージェントとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-provisioning-agent)

### Microsoft Entra Connect V2 への移行

クラウド同期に移行する資格がまだない場合は、次の表を使用して V2 への移行の詳細を確認してください。

| タイトル | 説明 |
| --- | --- |
| [廃止に関する情報](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/deprecated-azure-ad-connect) | Azure AD Connect V1 の非推奨に関する情報 |
| [Microsoft Entra Connect V2 とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2) | Microsoft Entra Connect の最新バージョンに関する情報 |
| [以前のバージョンからのアップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version) | Microsoft Entra Connect の 1 つのバージョンから別のバージョンへの移行に関する情報 |

### よく寄せられる質問
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/exchange-hybrid-writeback"} -->
## 同期を使用した Exchange ハイブリッドの書き戻し - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/exchange-hybrid-writeback
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、同期クライアントを使用した Exchange ハイブリッド ライトバック機能について説明します。

ハイブリッド展開によって、充実した機能の利用、および既存の社内 Microsoft Exchange 組織に対する管理制御をクラウドにまで展開することができます。 ハイブリッド展開は、オンプレミスの Exchange 組織と Exchange Online との間が単一の Exchange 組織に見えるシームレスな外観を提供します。

このシナリオを実現し、オンプレミスユーザーが Exchange Online を最大限に活用できるようにするには、クラウドの属性をオンプレミスのユーザーに書き戻す必要があります。 クラウド同期と接続同期の両方で、属性を書き戻すことができます。

[Image: Exchange ハイブリッド シナリオの概念図。]

### クラウド同期

クラウド同期を使用してこのシナリオを有効にするには、最新のプロビジョニング エージェントを使用していることを確認し、ドキュメントに従います。 詳細については、「[クラウド同期を使用した Exchange ハイブリッドの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/exchange-hybrid)」を参照してください

### Connect 同期

インストーラーを使用して、接続同期シナリオを有効にすることができます。 カスタム インストールを選択すると、Exchange ハイブリッドの書き戻しを選択できます。 詳細については、「[接続同期のカスタム インストール」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/get-started"} -->
## Microsoft Entra ID との統合を開始する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/get-started
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、Active Directory と統合するために必要な手順について説明します。

ハイブリッド ID を初めて使用する場合は、このドキュメントを開始する必要があります。 まだ行っていない場合は、[ハイブリッド ID とは何ですか？](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) のドキュメントをよく読んで理解してください。先に進む前に必ず参照しましょう。

このドキュメントでは、オンプレミスの Active Directory と Microsoft Entra ID を統合するために必要な手順について説明します。 Active Directory との統合は、Microsoft Entra ID を使用してユーザーとグループの同期を設定するプロセスです。 これらの手順は、使用するツールによって若干異なります。

最初に [\[適切な同期ツールの選択\]](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios) を使用して、適切な同期ツールを指定します。 推奨されたツールについては、次のセクションを使用してください。

### クラウド同期

Active Directory と統合するためにクラウド同期をデプロイする場合は、これらのタスクを使用します。

| タスク | 説明 |
| --- | --- |
| [どの同期ツールが正しいかを判断](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios) | ウィザードを使用して、クラウド同期と Microsoft Entra Connect のどちらを適切なツールにするかを判断します。 |
| [クラウド同期の前提条件を確認する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites) | 作業を開始する前に、必要な前提条件を確認してください。 |
| [プロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install) をダウンロードしてインストールする | Microsoft Entra プロビジョニング エージェントをダウンロードしてインストールします。 |
| [クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure) を構成する | 組織の同期を構成して調整します。 |
| [ユーザーが](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest#verify-users-are-created-and-synchronization-is-occurring) を同期していることを確認する | 動作していることを確認します。 |

### Microsoft Entra Connect

Microsoft Entra Connect を展開して Active Directory と統合する場合は、これらのタスクを使用します。

| タスク | 説明 |
| --- | --- |
| [どの同期ツールが正しいかを判断](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios) | ウィザードを使用して、クラウド同期と Microsoft Entra Connect のどちらを適切なツールにするかを判断します。 |
| [Microsoft Entra Connect の前提条件を確認](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites) | 作業を開始する前に、必要な前提条件を確認してください。 |
| [インストールの種類を確認して選択](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-select-installation) | 高速インストールとカスタム インストールのどちらを使用するかを決定します。 |
| [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) をダウンロードする | Microsoft Entra Connect をダウンロードします。 |
| [Microsoft Entra Connect の簡易設定をインストールして構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express) | 高速設定を使用している場合は、高速設定を使用して Microsoft Entra Connect をインストールして構成します。 |
| [Microsoft Entra Connect カスタム設定をインストールして構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom) | カスタム設定を使用している場合は、簡易設定を使用して Microsoft Entra Connect をインストールして構成してください。 |
| [インストール後のタスクの実行](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-post-installation) | インストール後のタスクを実行します。 |
| [ユーザーが](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest#verify-users-are-created-and-synchronization-is-occurring) を同期していることを確認する | 動作していることを確認します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/group-writeback-cloud-sync"} -->
## Microsoft Entra Cloud Sync を使用したグループ書き戻し - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/group-writeback-cloud-sync
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-04-09
- Summary: この記事では、グループをプロビジョニングしてオンプレミス AD に書き戻すクラウド同期の新機能について説明します。

プロビジョニング エージェント [1.1.1370.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1113700) のリリースにより、クラウド同期でグループ ライトバックを実行できるようになりました。 この機能は、クラウド同期がグループをオンプレミスの Active Directory環境に直接プロビジョニングできることを意味します。 また、ID ガバナンス機能を使用して、 [エンタイトルメント管理アクセス パッケージにグループ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-writeback)を含めるなど、AD ベースのアプリケーションへのアクセスを管理することもできます。

[Image: クラウド同期を使用したグループ ライトバックの図。]

重要

Microsoft Entra Connect Sync でのグループ ライトバック v2 のプレビューは非推奨となり、サポートされなくなりました。

Microsoft Entra Cloud Sync を使用して、クラウド セキュリティ グループをオンプレミス Active Directory Domain Services (AD DS) にプロビジョニングできます。

Microsoft Entra Connect Sync でグループ ライトバック v2 を使用する場合は、同期クライアントを Microsoft Entra Cloud Sync に移動する必要があります。Microsoft Entra Cloud Sync に移行する資格があるかどうかを確認するには、ユーザー同期ウィザードを使用します。

ウィザードで推奨されているように Microsoft Cloud Sync を使用できない場合は、Microsoft Entra Cloud Sync を Microsoft Entra Connect Sync と並行して実行できます。その場合は、Microsoft Entra Cloud Sync を実行して、クラウド セキュリティ グループをオンプレミスの AD DS にプロビジョニングするだけです。

MICROSOFT 365 グループを AD DS にプロビジョニングする場合は、グループ ライトバック v1 を引き続き使用できます。

### Active Directory Domain ServicesへのMicrosoft Entra IDのプロビジョニング - 前提条件

Active Directory Domain Services (AD DS) へのプロビジョニング グループを実装するには、次の前提条件が必要です。

### ライセンスの要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID の一般的に利用可能な機能を比較する](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) を参照してください。

### 一般的な要件

- 少なくとも[ハイブリッドアイデンティティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持つMicrosoft Entraアカウント。
- Windows Server 2016 以降で使用できる、*msDS-ExternalDirectoryObjectId* 属性を持つオンプレミス AD DS スキーマ。
- ビルド バージョン [1.1.2334.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1123340) 以降のプロビジョニング エージェント。

注

サービス アカウントへのアクセス許可は、クリーン インストール時にのみ割り当てられます。 以前のバージョンからアップグレードする場合は、PowerShell を使用してアクセス許可を手動で割り当てる必要があります。

```powershell
$credential = Get-Credential  

Set-AADCloudSyncPermissions -PermissionType UserGroupCreateDelete -TargetDomain "FQDN of domain" -EACredential $credential
```

権限が手動で設定されている場合は、すべての子孫グループおよびユーザー オブジェクトのすべてのプロパティの読み取り、書き込み、作成、および削除を割り当てる必要があります。

これらのアクセス許可は、既定では AdminSDHolder オブジェクトには適用されません。 詳細については、「[Microsoft Entra プロビジョニング エージェント gMSA PowerShell コマンドレット](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-gmsa-cmdlets#grant-permissions-to-a-specific-domain)を参照してください。

- プロビジョニング エージェントは、Windows Server 2022、Windows Server 2019、またはWindows Server 2016を実行するサーバーにインストールする必要があります。
- プロビジョニング エージェントで、ポート TCP/389 (LDAP) および TCP/3268 (グローバル カタログ) 上の 1 つ以上のドメイン コントローラーと通信できる必要があります。
    - 無効なメンバーシップ参照をフィルターで除外するためには、グローバルカタログのルックアップが必要です。
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
| 少なくとも 1 つの属性スコープ フィルターを持つ "すべてのセキュリティ グループ" モード。 | 最大 20K グループ。 | **スコープ内**のすべてのグループ全体で最大 500,000 個のメンバー。 | テナントが以下のすべての制限を満たしている場合は、このスコープ モードを使用します。1. テナントのユーザー数が 200,000 人未満2. テナントのグループ数が 40,000 未満3. テナントのグループ メンバーシップが 1M 未満です。 |

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

### Microsoft Entra Cloud Sync を使用したグループ ライトバックでサポートされるシナリオ

次のセクションでは、Microsoft Entra Cloud Sync を使用したグループ ライトバックでサポートされるシナリオについて説明します。

- Microsoft Entra Connect Sync グループ書き戻し V2 を Microsoft Entra Cloud Sync へ移行します
- Microsoft Entra ID ガバナンス を使用して、オンプレミスの Active Directory ベースのアプリ (Kerberos) を管理する

#### Microsoft Entra Connect Sync グループ書き戻し V2 を Microsoft Entra Cloud Sync に移行する

**Scenario:** Microsoft Entra Connect Sync (旧称 Azure AD Connect) を使用してグループ の書き戻しを Microsoft Entra Cloud Sync に移行します。このシナリオは、Microsoft Entra Connect グループ ライトバック v2 を現在使用しているお客様向けの**only**です。 このドキュメントで概説するプロセスは、ユニバーサル スコープで書き戻される、クラウドで作成されたセキュリティ グループにのみ関連するものです。 Microsoft Entra Connect グループの書き戻し V1 または V2 を使用して書き戻されたメールが有効なグループと DL はサポートされていません。

詳細については、「[Microsoft Entra Connect 同期グループ書き戻し V2 を Microsoft Entra Cloud Sync に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-group-writeback)を参照してください。

#### Microsoft Entra ID ガバナンスを使用してオンプレミスの Active Directory ベースのアプリ (Kerberos) を管理する

**Scenario:** クラウドからプロビジョニングされ、クラウドで管理されるActive Directory グループを使用してオンプレミス アプリケーションを管理します。 Microsoft Entra Cloud Sync を使用すると、MICROSOFT ENTRA ID GOVERNANCE機能を利用してアクセス関連の要求を制御および修復しながら、AD でのアプリケーションの割り当てを完全に管理できます。

詳細については、「Microsoft Entra ID ガバナンスを使用してオンプレミスの Active Directory ベースのアプリ (Kerberos) を管理する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/guidance-it-architects-source-of-authority"} -->
## Microsoft Entra のクラウドファースト ID ガイダンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/guidance-it-architects-source-of-authority
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-11
- Summary: IT アーキテクトが、アプリケーション アクセスを維持しながら、ユーザーとグループの権限ソースからMicrosoft Entra IDへの段階的な移行を計画する方法について説明します。

現代の企業では、ID 管理を最新化し、運用を合理化することで、セキュリティを強化する圧力が高まっています。 このドキュメントでは、IT アーキテクトが、ソース オブ オーソリティ (SOA) 変換を使用して、ユーザーとグループの管理をオンプレミスの Active Directory (AD) から Microsoft Entra ID に移行するための戦略的かつ技術的なフレームワークを提供します。 従来の AD 環境は複雑で、維持コストが高く、最新の状態に保たれなければ、最新の脅威に対してますます脆弱になる可能性があります。 Microsoft の目標は、ID 管理のために Microsoft Entra ID を確立できるようにすることで、ハイブリッド顧客をセキュリティで保護するオプションを提供することです。 SOA を転送すると、段階的でリスクの低い移行パスが可能になり、"ビッグ バン" カットオーバーの中断を回避できます。

このガイドでは、クラウドファーストの ID 管理の包括的な概要を IT アーキテクトに提供します。 クラウドベースの ID への移行に関するビジネスとセキュリティの利点について説明し、ハイブリッド環境からクラウドファーストおよび AD 最小化状態への移行のための段階的なロードマップの概要と、ユーザーとグループの SOA 転送の準備状況を評価する方法について説明します。 また、このガイドでは、完全に移行する準備ができていない組織向けのアプリ中心のアプローチについて詳しく説明し、Kerberos および LDAP ベースのアプリケーションの統合戦略について説明し、ハイブリッド共存の主な制限事項と考慮事項を強調し、移行プロセス全体の計画、実行、ガバナンスをサポートするための実用的なチェックリストを提供します。

### ビジネスとセキュリティの利点

Active Directory は長い間、組織にとって "**王国の鍵**" と考えられ、侵害された場合の攻撃者にとって魅力的なターゲットとなっています。 アプリケーション認証を Microsoft Entra ID を使用するように移行することで、AD への依存を減らすことで、条件付きアクセスと MFA によって保護されたユーザーのセキュリティが向上します。 ID と認証を Microsoft Entra ID に移行すると、条件付きアクセス ポリシー、パスワードレス認証、ユーザーとアプリケーションの高度な ID ガバナンスなどの最新の機能 (当初オンプレミスで管理されていたものも含む) のロックが解除されます。 基本的に、Microsoft Entra ID の管理を一元化することで、組織の全体的なセキュリティ体制が強化されます。

#### IT 効率とユーザー エクスペリエンスの向上

クラウド優先のアプローチは、次の方法で組織の IT 効率とユーザー エクスペリエンスを向上させるのに役立ちます。

- IT 管理者は、Microsoft Entra ID の管理センターと Microsoft Graph API を使用して、ユーザー ID、グループ、フェデレーションまたはプロビジョニングされたアプリケーション アクセス ポリシーを管理できます。
- エンタイトルメント管理、アクセス レビュー、ライフサイクル ワークフローなどの Microsoft Entra ID ガバナンス機能は、以前 AD で管理されていたグループに依存するアプリのガバナンスを合理化します。 これにより、自動化が導入され、ID ライフサイクル全体のコンプライアンスに役立ちます。
- ユーザーは、リスクベースの条件付きアクセス ポリシーなどの最新のアクセス制御を使用して、クラウドとオンプレミスの両方のアプリケーションで **シングル サインオン** を利用できます。 移行後、従業員は、フィッシングに強いパスワードレスメソッドなどのMicrosoft Entra ID資格情報を使用して、レガシ イントラネット アプリケーションにシームレスにアクセスできます。 これにより、複数の AD パスワードを管理する必要が減り、資格情報のスプロールが軽減されます。

### クラウド ID へのロードマップ: ハイブリッドから Cloud-First/AD への最小化状態

組織は通常、クラウドとオンプレミスの両方の AD を環境内で使用する*ハイブリッド* アプローチから始まり、"**クラウドへの道**" の異なるステージを進めます。 その後、 [クラウドファースト](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-posture#state-3-cloud-first) ステージが続き、リソースはますますクラウドに移行されます。 組織が達成する次の段階は、 [AD 最小化状態](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-posture#state-4-active-directory-minimized) です。 ユーザー、グループ、および連絡先の SOA 転送は、Microsoft Entra ID への ID の増分移行を可能にすることで、この取り組みを容易にします。 多数のアプリケーションの書き換えや再プラットフォーム化が必要になる AD をすべて一度に使用停止するのではなく、SOA では *段階的なアプローチ*が可能になります。 このアプローチにより、組織は適切な ID をすぐにクラウドに移行し、時間の経過と共に徐々に AD フットプリントを減らすことができます。

注

SOA 転送を開始する前に、必ずアプリケーション インベントリから始めてください。 これにより、レガシ AD アプリに関連付けられているユーザーのアクセスを維持できます。

### ロックを解除できるシナリオ

#### AD で不要になったユーザーとグループの AD フットプリントを最小限に抑える

クラウド サービスと最新のアプリケーションに移行すると、特定の AD アカウントとグループが古くなる可能性があります。 現在でも、これらのユーザーとグループは、MIM などの従来の ID 管理ソリューションを通じて AD で作成されています。 これは、クラウドでこれらのオブジェクトを手動で作成すると、手作業が集中的に行われるためです。 SOAを誰に転送するかを決める際に、これらのユーザーとグループについては、ADを考慮から外すことができます。 これにより、AD と Microsoft Entra の両方からそれらを削除できます。または、Microsoft Entra でのみ必要な場合は、 [AD から削除](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-active-directory-group-cleanup)する前に SOA を転送できます。 この移行をターゲットにすることで、組織は移行を自動化し、運用の中断を最小限に抑えながらその進行状況を監視できます。 詳細については、「 [Microsoft Entra ID ガバナンスを使用した AD ユーザーの最小化とユーザー ライフサイクルの管理」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview#minimizing-ad-users-and-governing-user-lifecycle-with-microsoft-entra-id-governance)参照してください。

#### ライフサイクル管理をクラウドに移行する

Microsoft Entra ID ガバナンスを使用して、クラウドから転送された SOA ユーザーとグループのライフサイクルとアクセス ガバナンスを有効にすることができます。 ユーザーの場合は、ユーザーを Microsoft Entra ID に直接プロビジョニングし、Microsoft Entra の ID ガバナンス機能を使用してこれらのユーザーを管理できることを意味します。 グループの場合は、グループを最新化し、クラウドからそれらに関連付けられているアプリのアクセス ガバナンスを有効にすることができます。 ここでは、Mail-Enabled セキュリティ グループ (MESG) や配布リスト (DLs) などの Exchange コンストラクトであるグループをクラウドで管理する前に最新化が必要な場合に、いくつかの注意事項が適用されます。 詳細については、 [グループ SOA ガイダンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview)。

### SOA は適切なソリューションですか?

次の図は、ユーザーとグループの権限ソース (SOA) を転送する準備ができている場合の概要を示しています。

[Image: ユーザーとグループの権限ソースをMicrosoft Entra IDに転送するための準備状況の決定を示す図。]

#### 考慮事項

**ユーザー：** SOA は、AD DS にリンクされているアプリケーションの依存関係がないユーザーに適しています。 効果的な移行計画には、特定のアプリケーションに関連付けられているユーザーを特定することが重要です。

**グループ：** グループの場合は、まずセキュリティ グループをクラウドに移行することをお勧めします。 クラウドに入ったら、必要に応じて Microsoft Entra ID から AD にプロビジョニングし直します。 配布リスト (DLs) と Mail-Enabled セキュリティ グループ (MESG) の場合、すべての Exchange ワークロードがクラウドに存在し、オンプレミスの Exchange サーバーが不要になったら移行することをお勧めします。

### オンプレミス認証を最新化するためのアプリケーション中心のアプローチ

このセクションでは、アプリケーション中心のアプローチと呼ばれる AD 負荷の高い環境の主なクラウド移行戦略について説明します。 このアプローチにより、オンプレミスのアプリケーションは ID にMicrosoft Entra IDを使用できます。 また、AD の Kerberos、LDAP、またはパスワードに依存し続けるアプリケーションの前提条件とオプションについても説明します。 Microsoft Entra IDから AD にプロビジョニングされたユーザーのパスワード ライトバックは使用できないため、パスワードベースのオンプレミス アプリケーションには、別のサポートされている認証パスが必要です。

アプリケーション中心のアプローチでは、アプリケーションの観点からクラウド移行に取り組みます。 このアプローチでは、次のフレームワークを適用して、アプリ認証を最新化しようとします。

- **インベントリ アプリケーション:** 認証に AD を使用するすべてのオンプレミス アプリ (Kerberos/NTLM または LDAP) を一覧表示します。
- **統合の目標:** 認証に Microsoft Entra ID を使用するように各アプリを再構成し、オンプレミス AD への依存を減らします。
- **ブリッジング ソリューション:** Microsoft Entra アプリケーション プロキシ、Microsoft Entra Domain Services、またはその他のクラウド サービスを使用して、レガシ アプリを大幅な書き換えを行わずに Microsoft Entra ID に接続します。

#### アプリケーション中心の移行に推奨されるシーケンス

Active Directory フェデレーション サービス (AD FS) またはサード パーティの IdP 経由で SAML/OIDC などの最新のプロトコルを既に使用しているアプリもあります。 これらのアプリは Microsoft Entra に直接移行する方が簡単ですが、ハードコーディングされた NTLM 専用アプリなどの他のいくつかのレガシ ケースでは、特別な処理が必要になる場合があります。

### アプリケーション インベントリと認証の分析

移行を計画する前に **、すべてのオンプレミス アプリケーションを検出して分類** することが重要です。 目標は、アプリごとに決定することです。 *現在、ユーザーを認証する方法*と、 *その認証を Microsoft Entra ID と統合または最新化するための最適なパスは何ですか?*

[Image: アプリケーション インベントリの優先順位、テレメトリベースの検出、アプリケーション所有者とのコラボレーションの図。]

完全なアプリケーション分析は、AD 負荷の高い環境でのクラウドベースの ID 管理への移行を成功させる基盤となります。 このプロセスでは、認証のために Active Directory に依存するすべてのアプリケーションを体系的に評価し、各ユーザーを認証する方法を決定し、各アプリケーションの固有の要件に合った最新化または統合計画をマッピングします。 次の手順は、アプリ中心の移行に推奨されるシーケンスです。

#### ステップ 1. Active Directory 統合アプリケーションのカタログ化

認証または承認に AD を使用するすべてのアプリケーションを特定して、移行を開始します。 クラウド ID 管理への移行を計画するための依存関係を理解し、各アプリケーションに接続する AD グループとユーザー アカウントに関する説明を示します。 これにより、最初に移行するユーザーとグループ、特にビジネス クリティカルなアプリにリンクされているユーザーとグループに優先順位を付けるのに役立ちます。

##### Active Directory 統合アプリケーションの検出

[Microsoft Entra Global Secure Access Application Discovery](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-application-discovery) や AD Domain Controller ログなどのツールを使用して、従業員がアクセスしているアプリと、それらのアプリが AD とどのようにやり取りしているかを判断します。 ユーザー数で並べ替え、個々のユーザー アクティビティでフィルター処理するダッシュボードを使用して、使用率の高いアプリケーションに優先順位を付けます。

##### アプリケーションを AD セキュリティ グループにマップする

見つかったすべてのアプリについて、アクセスを制御する AD セキュリティ グループを特定します。 所有者と共同作業するか、構成を確認して、これらのグループを一覧表示します。 関連するグループを検索するために必要な場合は、スクリプトまたは AD クエリを使用します。特に、グループ名または説明がアプリを参照している場合です。 セキュリティ グループの識別の詳細については、「 [1 つのドメインで未使用の Active Directory Domain Services グループをクリーンアップ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-active-directory-group-cleanup)する」を参照してください。

##### 各アプリケーションのユーザーを識別する

各アプリケーションの AD グループのグループ メンバーシップを抽出し、実際の使用状況データを分析して、ユーザーを特定します。 メンバーシップ リストと使用状況ログを組み合わせて、各アプリのユーザーの明確な一覧を作成します。 この情報を使用して、クラウド移行計画をガイドし、プロセス全体でアクセスが維持されるようにします。

#### 手順 2. 認証方法を決定する

インベントリ内の各アプリケーションについて、使用する認証メカニズムを特定します。 この手順は、最も適切な移行または統合戦略を選択するために不可欠です。 考慮すべき主なカテゴリは次のとおりです。

- **統合 Windows 認証 (Kerberos/NTLM)** – Windows 認証、ファイル サーバー、SharePoint、および同様のプラットフォームを使用する IIS/.NET アプリケーションで一般的です。 通常、ユーザーは通常、別のプロンプトなしでドメイン資格情報を使用してサインインできます。
- **LDAP 認証/クエリ** – アプリケーションは [、AD を指す LDAP サーバー設定を](https://learn.microsoft.com/ja-jp/entra/architecture/auth-ldap) 持つ場合があり、ユーザーに AD 資格情報の入力を求めるバインドまたは参照、カスタム開発またはサードパーティ製品を実行できます。
- **フェデレーション/先進認証** – 一部のアプリケーションは [既に AD FS](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/ad-fs-overview) 経由でフェデレーションされているか、SAML や OAuth などの最新のプロトコルをサポートしています。 これらは通常、最小限の労力で Microsoft Entra ID を使用するように再構成できます。
- **その他の従来の方法** – アプリが AD またはローカル アカウントに対して RADIUS を使用するケースが含まれます。 主な焦点ではありませんが、これらを完全に文書化する必要があります。 詳細については、「 [Microsoft Entra ID を使用した RADIUS 認証」を](https://learn.microsoft.com/ja-jp/entra/architecture/auth-radius)参照してください。

#### 手順 3. モダン化の実現可能性を評価する

先進認証プロトコル (SAML/OIDC) をネイティブに採用する各アプリケーションの能力を評価します。 ベンダーの更新プログラムが利用可能な場合、またはアプリが社内で開発され、再コーディングできる場合は、通常、ID プロバイダーとして Microsoft Entra ID に移行するのが最適な長期的なソリューションです。 この方法では、AD の依存関係が削除され、クラウド ID 管理の利点が最大限に引き出されます。 ただし、簡単に更新できない古いアプリケーションの場合は、間接統合が必要な場合でも、Microsoft Entra ID と統合する "*ブリッジ*" ソリューションを計画してください。

一部の古いアプリケーションでは、特定の OU でユーザーを見つけたり、AD に属性を書き込んだりするなど、AD に関してハードコーディングされた前提がある場合があります。 これらのアプリは、この種の ID 移行の **対象範囲外** です。 これらのアプリケーションは、Microsoft Entra ID または Microsoft Entra Domain Services では、書き込みアクセス許可を保持している場合を除き、簡単にはサポートされません。これは可能ですが、その後はデータが異なっています。 LDAP 書き込みを行うアプリ、または動的補助クラスなどの不明瞭な AD 機能に依存しているアプリがあるかどうかを確認してください。 これらは、廃止されるまで AD に残る必要がある場合があります。 説明に従ってクラウド認証に移動できるため、AD 経由で *読み取り/認証* を行うアプリに焦点を当てる必要があります。

#### 手順 4. アプリケーションの分類と統合アプローチの計画

認証方法と最新化の実現可能性を決定したら、アプリケーションを個別のカテゴリにグループ化して、統合計画を導きます。

- **管理する必要があるアプリの数を最小限に抑えます。** ターゲットの日付で終了する予定のアプリの場合、これらはスコープ外と見なされ、これらのアプリに関連付けられているユーザー/グループの管理をクラウドに移行できます。 冗長なアプリの場合は、アプリを統合し、最新化できるかどうかを判断します。
- **最新の認証を既に使用しているアプリまたは対応しているアプリ:** これらは、MICROSOFT Entra ID を指すように AD FS アプリケーションを更新したり、ファイルを Azure ファイル共有に移動したりするなど、ID プロバイダーとして Microsoft Entra ID に直接移行できます。 中心的な焦点ではありませんが、これらに対処することで、従来の依存関係全体が減少します。 これらのアプリに関連付けられているユーザー/グループの管理は、クラウドに転送されるスコープ内にあります。
- **Kerberos/NTLM アプリ (簡単には最新化されません):** アプリケーション プロキシまたは同様のソリューションを使用して、Microsoft Entra をフロントエンドとして使用します。 アプリケーションはオンプレミスのままですが、ユーザー認証は Microsoft Entra ID トークンに切り替え、AD 内の Kerberos チケットに変換されます。
- **LDAP バインド アプリ:** アプリケーションをオンプレミスの AD に接続したままにする必要がある場合は、クラウドで管理されたユーザーとグループを AD にプロビジョニングし直します。 または、アプリケーションがクラウドマネージド ドメインにバインドできるように、Microsoft Entra Domain Servicesを使用します。
- **その他の特殊なケース:** AD 参加済みマシンに制限された古いクライアント サーバー アプリなど、変更またはプロキシできないアプリケーションの場合は、Azure Virtual Desktop、Windows 365 Cloud PC などの VDI ソリューションでホストすることを検討してください。 これにより、これらのアプリのマネージド環境が維持され、他の場所でのクラウド移行が可能になります。 これは、複雑さとコストが増えたため、最後の手段である必要があります。
- **切断されたアプリ:** インターネット接続なしで作業する、または切断された環境で動作するというビジネス要件があるアプリの場合は、オンプレミスに留まる必要があります。 これらのアプリを使用しているユーザーは、SOA を転送する資格がありません。

#### ステップ 5. マッピングと計画

分析フェーズを終了することで、どのアプリケーションが "*Kerberos カテゴリ*"、"*LDAP カテゴリ*"、またはその他のバケットに分類されるかを明確にマッピングし、それぞれが必要とする特定の統合メカニズムを作成する必要があります。 この計画手順は、各アプリケーションに固有の特性があり、移行戦略を選択する前に慎重に検討する必要があるため、重要です。 Microsoft Entra ID は、ほとんどの AD ベースの認証パターンに対応したソリューションを提供します。また、変更できないレガシ アプリケーションでも、セキュリティで保護されたハイブリッド アクセスと最新のガバナンスの恩恵を受けることができます。

#### ステップ 6. グループをクラウドに移行する

アプリ中心のアプローチでは、まずセキュリティ グループとアプリのアクセス制御をクラウドに移行することをお勧めします。 これにより、一元的なアクセス管理が可能になり、ユーザーを移動する前にグループ メンバーシップはそのまま維持されます。 Microsoft では、メンバーシップの整合性を維持し、テストを許可するために、ユーザーの前にグループの SOA を転送することをお勧めします。 また、グループを使用してアプリケーションをパイロットする、ユーザーをエンド ツー エンドでテストするなど、各アプリのシーケンスを調整することもできます。 クラウドに移行する場合は、「 [Microsoft Entra ID でグループの権限ソース (SOA) を使用するためのガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-group-source-of-authority-guidance) 」で説明されているように、クラウド グループにマップする方法に関する具体的なガイダンスの概要を説明しました。または、次のビデオをご覧ください。

ヒント

最初にセキュリティ グループをクラウドに移行します。 これにより、ユーザーを移動する前にアプリのアクセス制御をテストできます

アプリケーションが引き続き AD ユーザーまたはグループ メンバーシップに依存している場合、Microsoft Entra Cloud Sync はクラウドで管理されるユーザーとグループを AD にプロビジョニングし直すことができます。 この共存モデルの概要については、「[Microsoft Entra IDユーザーとグループをActive Directoryにプロビジョニングする」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory)を参照してください。 クラウドマネージド グループを使用して Kerberos または LDAP アプリケーションへのアクセスを管理するには、クラウドマネージド グループを使用した [オンプレミス アプリケーション アクセスの管理に](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups)関する説明を参照してください。

#### 手順 7. LDAP ベースのアプリケーションの処理 (Directory-Bound Apps)

**LDAP にバインドされたアプリケーション** またはサービスは、LDAP 経由で Active Directory Domain Services (AD DS) に直接クエリを実行します。多くの場合、ユーザー名とパスワードを使用した単純なバインドを使用した認証、またはディレクトリ読み取り用です。 一般的な例としては、古いエンタープライズ アプリケーション、ネットワーク アプライアンス、または LDAP バインドに依存して資格情報を検証するカスタム開発アプリなどがあります。 通常、これらのアプリケーションには LDAP サーバーが必要であり、最新の認証プロトコルに簡単に移行することはできません。 詳細については、「 [Microsoft Entra ID を使用した LDAP 認証」を](https://learn.microsoft.com/ja-jp/entra/architecture/auth-ldap)参照してください。

##### アプリケーションをオンプレミス AD に接続したままにする

ユーザーとグループのライフサイクル管理がMicrosoft Entra IDに移行している間、アプリケーションはオンプレミス AD に接続されたままにすることができます。 Microsoft Entra Cloud Sync を使用して、アプリケーションが AD に戻す必要があるクラウドで管理されたユーザーとグループをプロビジョニングします。 パスワード ベースのアプリケーションのパスワード ライトバックは使用できないため、別のサポートされている認証パスがない限り、アプリケーション アクセスに AD パスワードを必要とするユーザーを転送しないでください。

##### 別のオプション: Microsoft Entra Domain Services

クラウドで LDAP バインド アプリをサポートするためのもう 1 つのオプションは、Microsoft Entra Domain Servicesです。 Azure でホストされる Microsoft Entra Domain Services は、LDAP、Kerberos、NTLM エンドポイントを提供し、Microsoft Entra ID テナントからユーザー アカウントと資格情報を同期します。 これにより、レガシ アプリケーションでは、最新のプロトコルに切り替えることなく、認証にクラウドホスト型 AD を使用できます。 マネージド ドメインでは、主に LDAP クライアントの読み取りと認証がサポートされています。 詳細については、「[Microsoft Entra Domain Services とは」](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview)を参照してください。

#### 手順 8. Kerberos-Based アプリケーションの処理 (Windows 統合認証)

**Kerberos ベースのアプリ** には、通常、Windows 認証を使用する内部 Web アプリケーション、Kerberos チケットに依存するファイル サーバー (SMB)、Active Directory (AD) サインインが必要なその他のサービスが含まれます。 Microsoft では、これらのアプリケーションを Microsoft Entra ID と統合するための中間ソリューションを提供しています。

**Kerberos 制約付き委任 (KCD) を使用した Microsoft Entra アプリケーション プロキシまたはプライベート アクセス:**

*推奨される解決策:*

- **Kerberos 制約付き委任 (KCD) を使用した Microsoft Entra アプリケーション プロキシまたはプライベート アクセス:** このクラウド サービスを使用すると、Microsoft Entra ID を使用してオンプレミスの Web アプリケーションを公開できます。 ユーザーは、OAuth/OpenID Connect の使用など、Microsoft Entra ID に対して認証を行い、オンプレミスで動作するアプリケーション プロキシ コネクタは、KCD を使用してユーザーの代わりにバックエンド アプリケーションへの Kerberos チケットを取得します。 Microsoft Entra ID は認証ゲートウェイとして機能し、アプリケーションの認証を Kerberos に変換します。 このソリューションは、Web ベースのアプリケーション (HTTP/HTTPS) をサポートしており、 *ユーザーが AD にアカウントを持っている場合*は、クラウドマネージド ユーザーにシングル サインオン (SSO) を提供できます。 詳細については、「[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)と [Microsoft Entra Private Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-private-access)」を参照してください。
- **Cloud Kerberos Trust を使用したパスワードレス:**この方法Microsoft Entra ID、ユーザーが Windows Hello for Business や FIDO2 などのパスワードレス認証を使用してMicrosoft Entra ID資格情報でサインインするときに、オンプレミス AD リソースの Kerberos チケットを発行できます。 Microsoft Entra ID のクラウド Kerberos サービスを信頼するように AD ドメインを構成し、ユーザーの AD オブジェクトに必要なキーがあることを確認する必要があります。 このプロセスは完全にパスワードレスであるため、クラウド ユーザーがオンプレミスのリソースにアクセスするのに最適です。 詳細については、「 [Cloud Kerberos Trust](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/hybrid-cloud-kerberos-trust)」を参照してください。

#### Kerberos ワークロードを移行する前の主な考慮事項

これらのアプリに関連付けられているユーザーとグループのワークロードを移行する前の Kerberos アプリケーションの主な考慮事項を次に示します。

- **ユーザー ライフサイクル管理:** ユーザーをクラウド管理に移行した後も、Kerberos 機能のために、UserPrincipalName が一致する AD アカウントが残っている必要があります。
- **認証と属性:** Kerberos アプリケーションで AD アカウントまたは属性が必要な場合は、クラウドマネージド ユーザーを AD にプロビジョニングし直します。 パスワード ライトバックは使用できないため、AD パスワードを必要とするアプリケーションには、それらのユーザーに対してサポートされている既存の認証パスが必要です。
- **Microsoft Entra ID 参加済みデバイス:** 真のシングル サインオンの場合、Kerberos リソースにアクセスするデバイスは、Microsoft Entra ID 参加済みまたはハイブリッド参加済みである必要があります。 ユーザーが Microsoft Entra ID 資格情報を使用してデバイスにログインすると、デバイスは、信頼またはコネクタを介して Kerberos チケットに変換できる Microsoft Entra ID からトークンを取得できます。 デバイスがドメインに参加していて、ユーザーがクラウドで管理されている場合、シームレス SSO が困難になり、手動で資格情報を入力する必要がある可能性があります。 Microsoft では、ユーザーとデバイスの信頼を調整できるように、クラウド変換の一環として、クラウド信頼を使用してデバイスを Microsoft Entra ID 参加に移行することをお勧めします。

    注

    デバイスの移行は、SOA を転送するための現在のスコープ外です。
- **オンプレミス アプリの条件付きアクセス:**アプリケーションに対してアプリ プロキシまたはMicrosoft Entra Private Accessをデプロイすると、認証がMicrosoft Entra IDを通過するため、MFA などの条件付きアクセス ポリシーをアプリケーション アクセスに適用できます。 これにより、レガシ アプリでも変更なしでゼロ トラスト条件の恩恵を受けるので、セキュリティが強化されます。 Kerberos 信頼シナリオの場合、条件付きアクセスは、ユーザーがデバイス上の Microsoft Entra ID に対して最初に認証するときに適用されます。

#### 手順 9. 検証と最適化

統合された各アプリケーションを徹底的にテストします。 既存の AD ソースユーザーが新しいメソッドを使用してアプリケーションにアクセスできることを確認します。 MICROSOFT Entra ID からのグループ メンバーシップが AD へのグループ プロビジョニングによって受け入れられていることを確認します。 パフォーマンスとサインイン ログを監視します。 運用環境で使用する設定を最適化します。

権限のソースが転送されたすべてのユーザーが引き続きアプリケーションにアクセスできることを確認します。 AD に存在していたグループ メンバーシップも Microsoft Entra ID に存在することを確認します。 [グループ ログ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-source-of-authority-auditing-monitoring)と[ユーザー ログ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-audit-monitor)を使用して、機関のソースが正常に転送されたことを確認します。

AD の依存関係を保持するアプリケーションの場合は、 [オンプレミス アプリへのアクセスを管理するためのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-users-groups-provisioning-walkthrough)に従って、ユーザーとグループのプロビジョニングを検証します。 プロビジョニングされたオブジェクトに対するオンプレミスの変更を防ぐ必要がある場合は、 [AD ユーザーとグループの適用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-active-directory-object-enforcement)を構成します。

### Conclusion

次の表は、クラウド優先モデルでオンプレミス アプリを処理するためのオプションの概要です。

| アプリの種類 | クラウド統合方法とツール | 要件と考慮事項 |
| --- | --- | --- |
| **Kerberos ベースのアプリ**(Windows 統合認証、イントラネット Web アプリ、ファイル共有) | **Kerberos (KCD) を使用したMicrosoft Entra ID アプリケーション プロキシ:** Microsoft Entra IDを介してオンプレミスの Web アプリを発行し、オンプレミスの Kerberos 用コネクタを使用します。**Microsoft Entra ID Cloud Kerberos Trust:** Microsoft Entra ID 参加済みデバイス (Web 以外、ファイル共有など) の場合。 | **要件**:- オンプレミスMicrosoft Entra Private Accessインストール済み- 構成された SPN と委任の権限- Entra ID P1/P2 または Suite ライセンス- ユーザーの AD アカウント (同期済みまたはプロビジョニング済み)**Considerations:**- Entra ID 資格情報を使用してシームレスな SSO を提供するか、Kerberos ベースのアプリにパスワード不要でアクセスし、フィッシング対策の方法を使用してオンプレミスのリソースをセキュリティで保護してアクセスする |
| **LDAP ベースのアプリ**(認証/クエリのために LDAP 経由で AD DS にバインドするアプリ) | **Microsoft Entraクラウド同期:** クラウドで管理されているユーザーとグループをオンプレミス AD にプロビジョニングします。**Microsoft Entra Domain Services:** アプリケーションの LDAP 接続をクラウドマネージド ドメインに再ポイントします。 | **要件**:- AD へのプロビジョニングを構成するか、Microsoft Entra Domain Servicesをデプロイする- アプリケーションに必要なユーザー、グループ、属性をディレクトリで使用できるようにする**Considerations:**- オンプレミス AD へのパスワード ライトバックは利用できません- Microsoft Entra Domain Services、必要なパスワード ハッシュを生成するためにユーザーがパスワードをリセットする必要がある場合があります |

### IT アーキテクト向けの SOA 転送チェックリスト

#### 戦略的な利点を理解する

- オンプレミスの AD 依存関係を最小限に抑えることで、セキュリティ リスクを軽減します。
- 最新の ID 機能 (条件付きアクセス、パスワードレス、ゼロ トラスト) を有効にします。
- Microsoft Entra ID の ID 管理とガバナンスを合理化します。

#### 準備状況の評価

- すべてのユーザー、グループ、アプリケーションのインベントリを作成します。
- AD に依存するアプリに関連付けられなくなったユーザー/グループを特定します。
- 各アプリケーション (Kerberos、LDAP、SAML/OIDC) の認証の依存関係をマップします。

#### グループの移行を計画する

- [セキュリティ グループをクラウドに移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview)。必要に応じて、Microsoft Entra ID から AD にプロビジョニングし直してください。
- Exchange ワークロードが完全にクラウドベースになって初めて、DLs と MESG をシフトします。

#### アプリケーション認証の最新化

- Kerberos/NTLM アプリの場合:[Cloud Kerberos Trust](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/hybrid-cloud-kerberos-trust)
- LDAP アプリの場合:[LDAP の概要](https://learn.microsoft.com/ja-jp/entra/architecture/auth-ldap)
- AD に接続したままにする必要があるアプリの場合: [Microsoft Entra IDユーザーとグループをActive Directoryにプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory)する
- 最新/フェデレーション アプリの場合: Microsoft Entra ID (SAML/OIDC) に対して直接認証するように再構成します。

#### パスワードレス認証を有効にする

- [Hello For Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business) を展開する
- シームレスなチケットベースの認証のために[クラウド Kerberos 信頼](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/hybrid-cloud-kerberos-trust)と統合します。

#### Entra ID ガバナンスを実装する

- [Microsoft Entra ID のライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)
- [エンタイトルメント管理の概要](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)
- [アクセス レビューの概要](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)
- [リソース ロールの特権アイデンティティ管理 (Privileged Identity Management)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)

#### アドレス キーの制限事項

- Microsoft Entra ID から AD にプロビジョニングされたユーザーのパスワード ライトバックは利用できません。 パスワード ベースのアプリケーションでサポートされている既存の認証パスを保持します。
- ハードコードされた AD 依存関係を持つレガシ アプリでは、カスタム プロキシが必要な場合や、オンプレミスのままである場合があります。

#### 監視と反復処理

- 移行の進行状況を追跡する: 変換されたユーザー/グループ、最新化されたアプリ。
- セキュリティ体制とコンプライアンスを継続的に確認します。

ヒント

従来の AD アプリに関連付けられているユーザーのアクセスを中断しないように、常にアプリ中心の分析から始めます。 段階的な移行を使用して、"*ビッグ バン*" カットオーバーを回避します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/how-to-active-directory-group-cleanup"} -->
## 1 つのドメインで未使用の Active Directory Domain Services (AD DS) グループをクリーンアップする方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-active-directory-group-cleanup
- Service: entra-id / hybrid
- Article date: 2025-08-04
- Summary: 構造化された絶叫テスト手法を使用して、Active Directory Domain Services (AD DS) の未使用のセキュリティグループと配布グループを識別、トリアージ、および削除するためのステップ バイ ステップ ガイド。 ドメインで不要になったグループをクリーンアップすることで、セキュリティを強化し、管理上の負担を軽減します。

多くの組織が直面する課題の 1 つは、Active Directory Domain Services (AD DS) ドメイン (特にセキュリティ グループ) のグループが急増することです。 組織はプロジェクトのセキュリティ グループを作成する場合がありますが、時間の経過と同時に不要になります。 これらのグループは、ドメインに残る可能性があります。

グループのクリーンアップにより、管理の負担とリスクが軽減され、未使用のグループが Microsoft Entra に同期されるのを防ぐことができます。 この記事では、グループを分析し、 *絶叫テスト* 手法を使用して、AD DS ドメインから未使用のグループを識別および削除する方法について説明します。 絶叫テストでは、管理者は不要になった可能性のあるリソースを特定し、そのリソースを一時的に使用できないようにし、影響を受けたことを管理者に通知するかどうかを待機します。 影響を受けたユーザーのレポートがない場合、管理者はリソースのクリーンアップに進みます。

まず、各グループを **Active Directory ユーザーやコンピューター**などの AD DS ベースの管理ツールで管理するか、Microsoft Entra 管理センターまたは Exchange Online を使用してクラウドで管理する必要があるか、または必要ない可能性があるかを判断します。 場合によっては、絶叫テストは、グループがまだ必要であり、Microsoft Entra ID または AD DS ドメインで管理するかどうかを示します。 グループが必要ない場合は、複数の絶叫テストを実行して、アクティブかどうかを判断できます。 アクティブでなくなった場合は、AD DS ドメインから削除できます。

### クリーンアップのスコープ内のグループの種類

- 1 つのフォレスト、単一ドメインを持つ AD DS トポロジのセキュリティおよび配布リスト (DL) グループ。 マルチフォレスト環境またはマルチドメイン環境、ワークグループ、ドメインに参加しているコンピューター上のローカル グループ、またはその他の環境は、この記事の範囲外です。
- Microsoft Entra との同期の対象となるユーザーまたは他のグループであるメンバーを含むグループ。 コンピューター、連絡先、またはその他のオブジェクトをメンバーとして含むグループは、この記事の範囲外です。
- **Active Directory ユーザーとコンピューター**、**Microsoft Identity Manager (MIM)**、またはその他の ID 管理ツールを使用して AD DS で作成されたグループ。 この記事では、組み込みのグループや、他の製品によって作成されるグループについては説明しません。

### [前提条件]

この方法で未使用のグループの削除を開始する前に、次の前提条件を満たす必要があります。

- Domain Admins グループのメンバーシップ、またはグループを更新、移動、削除するには、同様のアクセス許可が必要です。
- グループを含めたり除外したりするには、Microsoft Entra Connect Sync または Cloud Sync のスコープを変更できる必要があります。
- AD DS オブジェクトの変更のログ記録を有効にするには、すべての書き込み可能なドメイン コントローラーのグループ ポリシー オブジェクト (GPO) を作成し、収集されたログの中央リポジトリを設定する必要があります。 詳細については、以下を参照してください。

    - [Windows イベント ログの監査ポリシーを構成する](https://learn.microsoft.com/ja-jp/defender-for-identity/deploy/configure-windows-event-collection)
    - [イベント 5136: ディレクトリ サービス オブジェクトが変更されました](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-10/security/threat-protection/auditing/event-5136)
    - [Azure Monitor を使用して仮想マシンから Windows イベントを収集する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/vm/data-collection-windows-events)
- [Active Directory のごみ箱を有効にする](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/get-started/adac/active-directory-recycle-bin)必要があります。
- ドメインに 2 つの組織単位 (OU) を作成する必要があります。1 つは一時的な Kerberos アプリの絶叫テスト グループ用、もう 1 つは一時的なライトウェイト ディレクトリ アクセス プロトコル (LDAP) アプリのスクリーム テスト グループ用です。

### ドメインのグループ分析を実行する

グループ分析の目的は、ドメイン内のどのグループが次のグループであるかを確認することです。

- AD DS 統合アプリケーションに必要であり、AD DS 統合ツールを使用して管理されます。
- AD DS 統合アプリケーションに必要であり、Microsoft Entra または Exchange Online を使用して管理され、メンバーシップが Microsoft Entra に書き戻されます。
- AD DS ドメインでは不要になった可能性がありますが、Microsoft Entra に接続され、Microsoft Entra でのみ維持できるサービスで必要になります。
- AD DS または Microsoft Entra と統合されているアプリケーションでは必要ありません。

最初の手順では、トリアージが必要なドメイン内のグループを特定して分類します。 多数のグループを持つ大規模な組織の場合は、グループを評価する順序を選択する必要があります。 順序は、次のような要因に基づく場合があります。

- グループの種類 (セキュリティ グループ、メールが有効なセキュリティ グループ、配布リスト)
- グループ組織単位 (OU)
- グループのサイズとメンバーシップ
- グループの入れ子 (グループは AD DS ドメイン内の別のグループのメンバーです)

分析のために、未登録のグループの妥当なサイズのバッチを選択します。 各グループの種類に基づいて、クリーンアップの可能性を分析する手順については、次のセクションを参照してください。

- 配布リストまたはメールが有効なセキュリティ グループの分析
- セキュリティ グループの分析

そのバッチでこれらの分析の 1 つを実行した後、未調整グループの次のバッチに進みます。 すべてのグループが分析されると、叫び声テストが完了します。

#### 配布リストまたはメールが有効なセキュリティ グループの分析

1. Exchange または Exchange Online でグループの所有者が設定されているかどうかを確認します。 グループがまだ必要かどうかを判断するには、グループの所有者に問い合わせてください。
2. グループにメンバーがあるかどうかを確認します。 メンバーがいない場合は、クラウドの絶叫テストに進みます。
3. Exchange または Exchange Online のログを検索して、メールがグループに送信されたかどうかを確認します。 [Get-MessageTrackingLog](https://learn.microsoft.com/ja-jp/powershell/module/exchangepowershell/get-messagetrackinglog) を使用して、ログを検索および分析できます。 メールが送信された場合、グループはまだ使用中である可能性があります。 グループの目的を決定するには、それらのメールの送信者に問い合わせてください。
4. グループ メンバーに、Microsoft Entra と同期されていないユーザー、連絡先、またはグループが含まれているかどうかを確認します。 グループに同期されていないメンバーが含まれている場合、そのグループの種類は Microsoft 365 グループまたは Exchange Online DL に更新されません。 Kerberos のスクリーム テストと後続テストを実行します。 完了したら、次の手順を実行します。

    - グループがまだ必要な場合は、Exchange オンプレミスを使用してドメインに保持するか、Exchange Online DL または Microsoft 365 グループに置き換えます。
    - グループが必要ない場合は、ドメインから削除します。
5. グループが DL の場合、グループのメンバーが Microsoft Entra にある場合でも、グループの引用元 (SOA) を Microsoft Entra に変換することはできません。 クラウド使用のスクリームテストとその後のテストを実施する必要があります。 完了すると、オプションは次のようになります。

    - それでもグループが必要な場合は、Exchange Online DL または Microsoft 365 グループに置き換えます。
    - グループが必要ない場合は、AD DS ドメインから削除し、置き換えないでください。

    続行するオプションを決定するには、Exchange 管理者に問い合わせてください。
6. グループがメールが有効なセキュリティ グループ (MESG) の場合、グループ SOA は変換できません。 クラウドの使用状況と後続テストの絶叫テストを実行します。 完了すると、オプションは次のようになります。

    - Microsoft 365 グループに置き換えます。
    - 個別の DL グループとセキュリティ グループを作成し、それぞれを個別に処理します。
    - グループの種類を、MESG ではなくセキュリティ グループまたは DL に更新します。
    - AD DS ドメインからグループを削除し、置き換えないでください。

    Exchange 管理者に問い合わせて、Exchange の目的でグループが引き続き必要かどうかを確認してください。 引き続き Exchange のグループが必要な場合は、Microsoft 365 グループに置き換えるか、個別の DL とセキュリティ グループを作成して、それぞれを個別に処理します。 それ以外の場合は、グループがセキュリティ グループのみである必要がある、または不要になったと想定し、セキュリティ グループの分析に進みます。

#### セキュリティ グループの分析

1. グループ メンバーを確認します。 グループにメンバーがない場合は、 クラウドの使用に関する絶叫テストに進みます。 その他のメンバー オブジェクトの種類については、次の表の推奨事項を確認してください。

    | **メンバー オブジェクトの種類** | **推奨 事項** |
    | --- | --- |
    | 1 台以上のコンピューター | グループは、グループ ポリシーまたは System Center の管理に使用される可能性があります。 このグループの計画を決定するには、Windows Server と System Center の管理者に問い合わせてください。 これを、Azure または Intune の新しいアプローチに置き換えることができます。 |
    | 1 つ以上の連絡先 | グループが MESG の場合、これらの連絡先を使用して Microsoft Entra に対する認証を行うことはできません。 グループをメール対応にしないように変換する場合は、グループから削除します。 |
    | Microsoft Entra に同期されていない 1 人以上のユーザーまたはグループ (同期スコープから除外) | グループは、権限のソースを変換しないでください。 |
    | Microsoft Entra に同期されたユーザーとグループ | クラウドの使用状況に関する絶叫テストの実行を計画します。 |
2. グループの変更日を見つけます。 グループが最近変更された場合は、ログを調べて、グループを変更したユーザーを特定します。 グループを変更したユーザーのセキュリティ識別子 (SID) は、 [イベント 5136](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-10/security/threat-protection/auditing/event-5136) のサブジェクト フィールドに含まれます。 グループの目的と、グループをクラウド セキュリティ グループに更新できるかどうかを確認するには、それらに連絡してください。
3. それ以外の場合、グループが最近変更されていない場合は、 [Get-Acl](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.security/get-acl) を使用して、グループまたはその OU のアクセス制御リスト (ACL) がグループの所有権を委任しているかどうかを確認します。 ACL が所有権を委任する場合は、所有者に連絡してグループの目的を決定します。
4. それ以外の場合は、別のグループがこのグループをメンバーとして持ち、そのグループに識別された所有者があるかどうかを確認します。 その場合は、そのグループの所有者に問い合わせてください。
5. すべてのグループ メンバーが Microsoft Entra に同期されているユーザーとグループであり、グループに最近の変更がなく、明確な所有者もいない場合は、別のグループの依存ではありません。次のセクションに進み、 クラウドの使用に関する絶叫テストを実行します。
6. それ以外の場合、グループに Microsoft Entra アカウントではないユーザーまたはグループ メンバーがあり、組み込みのグループではない場合は、 Kerberos アプリの絶叫テストに進みます。

分析対象のグループに対してこれらの絶叫テストが開始されたら、バッチ内の次のグループに進みます。

### クラウド使用のスクリーム テスト

このテストでは、Azure サブスクリプションの特権ロールを含め、クラウド リソースに使用されるユーザーがグループ内に存在するかどうかを判断します。

1. まず、グループへの参照がある場合は、Microsoft Entra をチェックインします。

    - アプリ ロールから
    - 条件付きアクセス ポリシーから
    - 別のクラウド グループ内
    - Privileged Identity Management で
    - ライフサイクル ワークフローでは、アクセス レビューまたはエンタイトルメント管理

    その場合は、そのリソースまたはサービスの管理者に連絡して、グループの使用方法と、グループをクラウド セキュリティ グループに置き換えることができるかどうかを確認します。
2. Azure サブスクリプション、リソース グループ、またはリソース内の Azure ロールからのグループへの参照があるかどうかを確認します。 その場合は、その Azure サブスクリプションの所有者に問い合わせてください。
3. Exchange、SharePoint、Intune、Azure、およびその他の管理者と話して、サービスの管理にグループが必要かどうかを判断します。
4. Microsoft Online Services の他のアプリケーションの管理者と話して、グループを使用しているかどうかを判断します。
5. Microsoft Online Services でグループが明確に使用されていないと判断した後、明らかではない別のサービスが存在する可能性があります。 別のサービスがあるかどうかを検出するには、以下の絶叫テストの手順に進みます。
6. クラウド同期または接続同期の構成を変更して、グループを同期から除外します。
7. 同期が完了するまで待ち、グループが Microsoft Entra に表示されなくなったかどうかを確認します。
8. グループが使用できないとユーザーが不平を言っているかどうかを確認するには、数日待ちます。 たとえば、IT ヘルプデスクでサポート チケットを開くユーザーがいるかどうかを確認します。
9. 苦情がある場合は、除外からグループを削除します。 グループに依存するチームを特定し、将来クラウドで管理されるグループを使用するようにチームを移行する計画を決定します。
10. グループが Microsoft Entra で利用できなくなったことに関する苦情がない場合は、 Kerberos アプリの悲鳴テストである次のセクションに進みます。

### Kerberos アプリのスクリーム テスト

このテストでは、ユーザーがセキュリティ グループのメンバーであることに依存するアプリがあるかどうかを判断します。AD DS は、このグループのグループ ID を含む Kerberos チケットをアプリに提供するか、そのグループを含む別のグループをアプリに提供します。

Kerberos の絶叫テストを実行するには、次の手順に従います。

1. Kerberos の絶叫テストでグループの OU にグループを移動します。
2. グループの種類を DL に更新して、グループが Kerberos トークンに含まれるようにします。 または、メンバーを削除します。 たとえば、メンバーを一時的に別の新しい DL グループに移動します。
3. グループが使用できないとユーザーが不平を言っているかどうかを確認するには、数日待ちます。 たとえば、IT ヘルプデスクでサポート チケットを開くユーザーがいるかどうかを確認します。
4. 苦情がある場合は、グループの種類をセキュリティ グループに戻すか、メンバーを追加します。 次に、グループに依存するチームを特定します。
5. 苦情がない場合は、次のセクション、LDAP アプリの絶叫テストに進みます。

### LDAP アプリのスクリーム テスト

このテストでは、ユーザーがセキュリティ グループのメンバーであることに依存する LDAP アプリがあるかどうかを判断します。

1. LDAP スクリーム テストのグループの OU にグループを移動します。
2. グループからメンバーを削除します。 たとえば、メンバーを一時的に別の新しいグループに移動します。
3. グループが使用できないとユーザーが不平を言っているかどうかを確認するには、数日待ちます。 たとえば、IT ヘルプデスクでサポート チケットを開くユーザーがいるかどうかを確認します。
4. 苦情がある場合は、グループのメンバーシップを復元し、元の OU に戻します。 グループに依存するチームを特定します。
5. 苦情がない場合は、グループを削除します。 削除されたグループは **、Active Directory のごみ箱**に移動します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/how-to-group-source-of-authority-configure"} -->
## Microsoft Entra ID でグループの権限のソース (SOA) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-group-source-of-authority-configure
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-10-13
- Summary: グループソースオブオーソリティ (SOA) を使用して、グループ管理を Active Directory Domain Services から Microsoft Entra ID に変換する方法について説明します。

この記事では、Group Source of Authority (SOA) を構成するための前提条件と手順、変更を元に戻す方法、および制限について説明します。 グループ SOA の詳細については、「 [クラウド優先の体制を採用する: グループの権限ソースをクラウドに変換する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview)」を参照してください。

### [前提条件]

| 要件 | 説明 |
| --- | --- |
| **役割** | [ハイブリッド管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-administrator) は、Microsoft Graph API を呼び出して、グループの SOA の読み取りと更新を行う必要があります。[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) または [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) は、Microsoft Graph Explorer または Microsoft Graph API の呼び出しに使用されるアプリに、必要なアクセス許可にユーザーの同意を付与する必要があります。 |
| **アクセス許可** | onPremisesSyncBehavior Microsoft Graph API を呼び出すアプリの場合、Group-OnPremisesSyncBehavior.ReadWrite.All アクセス許可スコープを付与する必要があります。 詳細については、Graph Explorer またはテナント内の既存のアプリに このアクセス許可を付与する方法 を参照してください。 |
| **必要なライセンス** | Microsoft Entra Free ライセンス。 |
| **クライアントの同期** | SOA を適用する前に、クライアントが SOA 設定を受け入れることができるように、サポートされているバージョンの同期クライアントを使用していることを確認してください。 Connect Sync を使用する場合は、最小バージョン [2.5.76.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#25760) にアップグレードします。 Cloud Sync を使用する場合は、最小バージョン [1.1.1370.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1113700) にアップグレードします。 |
| **AD DS へのプロビジョニング (省略可能)** | SOA に変換されたグループを Microsoft Entra ID から Active Directory Domain Services (AD DS) にプロビジョニングするには、クラウド同期を使用する必要があります。また、元の OU パスを使用して AD DS にプロビジョニングするためのグループを準備する手順も完了する必要があります。 詳細については、「 [グループ SOA の変換とプロビジョニングのためのグループの準備」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-group-source-of-authority-guidance#prepare-groups-for-group-soa-conversion-and-provisioning)参照してください。 |

#### Connect Sync クライアントのダウンロード

1. Connect Sync ビルド バージョン [2.5.76.0 以降を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#25760) ダウンロードします。
2. コントロール パネルの **[プログラム** ] に移動し、Microsoft Entra Connect Sync のバージョンを確認します。

#### クラウド同期クライアントのダウンロード

1. Cloud Sync ビルド バージョン [1.1.1370.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1113700) 以降をダウンロードします。
2. [エージェントの現在のバージョンを特定する](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/cloud-sync/how-to-automatic-upgrade)方法について説明します。
3. [手順に従って、AD DS から Microsoft Entra ID へのプロビジョニングを構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)します。

### アプリにアクセス許可を付与する

Microsoft Entra 管理センターまたは Graph Explorer でアクセス許可を付与できます。 この高い特権を持つ操作には、アプリケーション管理者またはクラウド アプリケーション管理者ロールが必要です。 PowerShell を使用して同意を付与することもできます。 詳細については、「 [1 人のユーザーに代わって同意を付与する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-consent-single-user?pivots=ms-graph)参照してください。

#### カスタム アプリ

対応するアプリに `Group-OnPremisesSyncBehavior.ReadWrite.All` アクセス許可を付与するには、次の手順に従います。 アプリの登録に新しいアクセス許可を追加し、同意を付与する方法の詳細については、「 [Microsoft Entra ID でアプリの要求されたアクセス許可を更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-update-permissions)する」を参照してください。

#### Microsoft Entra 管理センターを使用してアプリにアクセス許可を付与する

1. [アプリケーション管理者](https://entra.microsoft.com)または[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者として Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[エンタープライズ アプリケーション**&gt;***アプリケーション名***] を参照します。
3. [**アクセス許可**]&gt;***テナント名*に対する管理者の同意を選択します**。
4. [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)または[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)管理者としてもう一度サインインします。
5. 同意が必要なアクセス許可の一覧を確認し、[ **同意する**] を選択します。
6. 付与したアクセス許可の一覧を確認できます。

    [Image: アクセス許可を付与する方法のスクリーンショット。]

#### Graph エクスプローラーを使用してアプリにアクセス許可を付与する

1. [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を開き、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. プロファイル アイコンを選択し、[ **アクセス許可に同意する**] を選択します。
3. Group-OnPremisesSyncBehavior を検索し、アクセス許可の **[同意** ] を選択します。

[Image: Group-OnPremisesSyncBehavior.ReadWrite アクセス許可に同意する方法のスクリーンショット。]

### グループ SOA の変換とプロビジョニングのためのグループの準備

グループを AD DS にプロビジョニングし直す場合は、次の手順を実行して OU パスを保持し、適切なマッピングを使用 **してグループの AD へのプロビジョニング** 構成に設定します。

1. AD DS グループのグループ スコープをユニバーサルに変更します。
2. グループ用のテナント スコープのディレクトリ拡張プロパティを作成します。
3. 識別名 (DN) などのオンプレミスの値を拡張機能プロパティに直接マップします。
4. Microsoft Graph を使用してプロパティ値を確認します。
5. 準備ができたら、引用文献 (SOA) を変換します。
6. カスタム式を使用して、Cloud Sync がグループを同じ CN 値と OU 値で AD DS に書き戻す (プロビジョニングする) ようにします。

詳細については、「 [Microsoft Entra Cloud Sync を使用して Active Directory Domain Services にグループをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)する」を参照してください。

### テスト グループの SOA を変換する

テスト グループの SOA を変換するには、次の手順に従います。

1. テスト用のセキュリティ グループまたはメールが有効な配布グループを AD に作成し、グループ メンバーを追加します。 Connect Sync を使用して、Microsoft Entra ID に同期されるグループを使用することもできます。
2. 次のコマンドを実行して、Connect Sync を開始します。

    ```powershell
    Start-ADSyncSyncCycle
    ```
3. グループが Microsoft Entra 管理センターに同期されたグループとして表示されることを確認します。
4. Microsoft Graph API を使用して、グループ オブジェクトの SOA を変換します (*isCloudManaged*=true)。 [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を開き、グループ管理者などの適切なユーザー ロールでサインインします。
5. 既存の SOA の状態を確認してみましょう。 SOA はまだ更新していないので、 *isCloudManaged* 属性値は false にする必要があります。 次の例の *{ID} を* グループのオブジェクト ID に置き換えます。 この API の詳細については、「 [Get onPremisesSyncBehavior](https://learn.microsoft.com/ja-jp/graph/api/onpremisessyncbehavior-get)」を参照してください。

    ```https
    GET https://graph.microsoft.com/v1.0/groups/{ID}/onPremisesSyncBehavior?$select=isCloudManaged
    ```

    [Image: Microsoft Graph エクスプローラーを使用してグループの SOA 値を取得する方法のスクリーンショット。]
6. 同期されたグループが読み取り専用であることを確認します。 グループはオンプレミスで管理されているため、クラウド内のグループに対する書き込み試行は失敗します。 エラー メッセージはメールが有効なグループでは異なりますが、更新はまだ許可されていません。

    注

    この API が 403 で失敗した場合は、[ **アクセス許可の変更** ] タブを使用して、必要な Group.ReadWrite.All アクセス許可に同意を付与します。

    ```https
    PATCH https://graph.microsoft.com/v1.0/groups/{ID}/
       {
         "DisplayName": "Group1 Name Updated"
       }   
    ```

    [Image: グループが読み取り専用であることを確認するために更新を試みた際のスクリーンショット。]
7. Microsoft Entra 管理センターでグループを検索します。 すべてのグループ フィールドがグレー表示され、ソースが Windows Server AD DS であることを確認します。

    [Image: 基本的なグループ プロパティのスクリーンショット。]

    [Image: 高度なグループ プロパティのスクリーンショット。]
8. これで、グループの SOA をクラウドで管理するように更新できます。 クラウドに変換するグループ オブジェクトに対して、Microsoft Graph Explorer で次の操作を実行します。 この API の詳細については、「 [Update onPremisesSyncBehavior](https://learn.microsoft.com/ja-jp/graph/api/onpremisessyncbehavior-update)」を参照してください。

    ```https
    PATCH https://graph.microsoft.com/v1.0/groups/{ID}/onPremisesSyncBehavior
       {
         "isCloudManaged": true
       }   
    ```

    [Image: グループのプロパティを更新する PATCH 操作のスクリーンショット。]
9. 変更を検証するには、GET を呼び出して *isCloudManaged* が true であることを確認します。

    ```https
    GET https://graph.microsoft.com/v1.0/groups/{ID}/onPremisesSyncBehavior?$select=isCloudManaged
    ```

    [Image: グループのプロパティを確認するための GET 呼び出しのスクリーンショット。]
10. 監査ログの変更を確認します。 Azure portal で監査ログにアクセスするには、 **Microsoft Entra ID の管理**&gt;**Monitoring**&gt;**Audit ログ**を開くか、 *監査ログ*を検索します。 アクティビティとして **AD からクラウドへの権限付与元の変更** を選択します。

    [Image: 監査ログのグループ プロパティへの変更のスクリーンショット。]
11. クラウドでグループを更新できることを確認します。

    ```https
    PATCH https://graph.microsoft.com/v1.0/groups/{ID}/
       {
         "DisplayName": "Group1 Name Updated"
       }   
    ```

    [Image: グループのプロパティを変更するための再試行のスクリーンショット。]
12. Microsoft Entra 管理センターを開き、グループ **の Source** プロパティが **Cloud** であることを確認します。

    [Image: グループ ソース プロパティを確認する方法のスクリーンショット。]

### 同期クライアントの接続

1. 次のコマンドを実行して、Connect Sync を開始します。

    ```powershell
    Start-ADSyncSyncCycle
    ```
2. 変換された SOA を持つグループ オブジェクトを確認するには、 **Synchronization Service Manager** でコネクタに移動 **します**。

    [Image: コネクタのスクリーンショット。]
3. **Active Directory Domain Services コネクタ**を右クリックします。 相対ドメイン名 (RDN) 設定 "CN=&lt;GroupName&gt;" でグループを検索します。

    [Image: RDN を検索する方法のスクリーンショット。]
4. 検索したエントリをダブルクリックし、[ **系列**&gt;**Metaverse オブジェクトのプロパティ]** を選択します。

    [Image: 系列を表示する方法のスクリーンショット。]
5. **コネクタ**を選択し、"CN={&lt;英数字&gt;}" の **Entra ID オブジェクト**をダブルクリックします。
6. Entra ID オブジェクトで **blockOnPremisesSync** プロパティが true に設定されていることがわかります。 このプロパティ値は、対応する AD DS オブジェクトで行われた変更が Entra ID オブジェクトにフローしないことを意味します。

    [Image: データ フローをブロックする方法のスクリーンショット。]
7. オンプレミスのグループ オブジェクトを更新しましょう。 グループ名を *SecGroup1 から SecGroup1.1* に変更します。

    [Image: オブジェクト名を変更する方法のスクリーンショット。]
8. 次のコマンドを実行して、Connect Sync を開始します。

    ```powershell
    Start-ADSyncSyncCycle
    ```
9. イベント ビューアーを開き、アプリケーション ログでイベント ID 6956 をフィルター処理します。 このイベント ID は、オブジェクトの SOA がクラウド内にあるため、オブジェクトがクラウドに同期されていないことを顧客に通知するために予約されています。

    [Image: イベント ID 6956 のスクリーンショット。]

### グループ SOA の一括更新

次の PowerShell スクリプトを使用して、アプリベースの認証を使用してグループ SOA の更新を自動化できます。

```powershell
<#
.SYNOPSIS
    Updates groups to set isCloudManaged parameter to true for on-premises synchronized groups.

.DESCRIPTION
    This script reads a file containing group Ids, checks each group's OnPremisesSyncEnabled property,
    and if true, calls the onPremisesSyncBehavior API to set isCloudManaged to true.

.PARAMETER FilePath
    Mandatory. Path to the file containing group Ids (one per line).

.PARAMETER WhatIf
    Boolean parameter. When true (default), shows what would be done without making actual changes.

.EXAMPLE
    .\Update-GroupSoA.ps1 -FilePath "C:\temp\groups.txt"
    .\Update-GroupSoA.ps1 -FilePath "C:\temp\groups.txt" -WhatIf $false

.NOTES
    Requires Microsoft.Graph PowerShell module to be installed and appropriate permissions.
    Required Graph permissions: Group.ReadWrite.All, Group-OnPremisesSyncBehavior.ReadWrite.All

    Input file should contain one group Id (GUID) per line.
#>

[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)]
    [string]$FilePath,

    [Parameter(Mandatory = $false)]
    [bool]$WhatIf = $true
)

# Import Groups module
try {
    $moduleName = "Microsoft.Graph.Groups"
    if (-not (Get-Module -Name $moduleName)) {
        Import-Module -Name $moduleName
    }
    Write-Host "Successfully imported Microsoft Graph modules."
}
catch {
    Write-Error "Failed to import Microsoft Graph modules. Please ensure Microsoft.Graph PowerShell SDK is installed."
    Write-Host "Install with: Install-Module Microsoft.Graph -Scope CurrentUser"
    exit 1
}

# Connect to MS Graph
$context = Get-MgContext
if (-not $context) {
    Connect-MgGraph -Scopes 'Group.ReadWrite.All', 'Group-OnPremisesSyncBehavior.ReadWrite.All'
}

# Validate input file
if (-not (Test-Path $FilePath)) {
    Write-Error "Input file not found: $FilePath"
    exit 1
}

Write-Host "Starting group update process using $FilePath (WhatIf: $WhatIf)..."
Write-Host "-----------------------------------"

# Read group Ids from file
$groupGuids = Get-Content $FilePath

if ($groupGuids.Count -eq 0) {
    Write-Error "No group Ids found in the input file."
    exit 1
}

Write-Host "Found $($groupGuids.Count) group Ids to process."

# Initialize counters for summary
$totalGroups = $groupGuids.Count
$processedCount = 0
$updatedCount = 0
$skippedCount = 0
$errorCount = 0

# Process each group
foreach ($groupId in $groupGuids) {
    $processedCount++
    Write-Host "`nProcessing $groupId ($processedCount/$totalGroups)"

    try {
        $group = Get-MgGroup -GroupId $groupId -Property "Id,DisplayName,OnPremisesSyncEnabled"

        Write-Host "Group Name: $($group.DisplayName)"
        Write-Host "OnPremisesSyncEnabled: $($group.OnPremisesSyncEnabled)"

        if ($group.OnPremisesSyncEnabled -eq $true) {
            $actionDescription = "Set isCloudManaged to true for group '$($group.DisplayName)'"

            if ($WhatIf) {
                Write-Host "Skipping since WhatIf is enabled: $actionDescription"
                $skippedCount++
            }
            else {
                try {
                    # Call the onPremisesSyncBehavior API to set isCloudManaged to true
                    $body = @{
                        isCloudManaged = $true
                    }

                    $uri = "https://graph.microsoft.com/v1.0/groups/$groupId/onPremisesSyncBehavior"
                    Invoke-MgGraphRequest -Uri $uri -Method PATCH -Body ($body | ConvertTo-Json) -ContentType "application/json"

                    Write-Host "SUCCESS: Updated group to cloud-managed"
                    $updatedCount++
                }
                catch {
                    Write-Host "ERROR: Failed to update group: $_"
                    $errorCount++
                }
            }
        }
        else {
            Write-Host "SKIPPED: Group is not on-premises synchronized"
            $skippedCount++
        }
    }
    catch {
        Write-Host "ERROR: Failed to retrieve group information: $_"
        $errorCount++
    }
}

Write-Host "`n-----------------------------------"
Write-Host "SUMMARY"
Write-Host "-----------------------------------"
Write-Host "Total groups processed: $totalGroups"
Write-Host "Successfully updated: $updatedCount"
Write-Host "Skipped (not sync-enabled or WhatIf): $skippedCount"
Write-Host "Errors encountered: $errorCount"

Write-Host "`nScript completed."
```

#### SOA を変換した後の属性の状態

次の表では、オブジェクトの SOA を変換した後の *isCloudManaged* 属性と *onPremisesSyncEnabled* 属性の状態について説明します。

| 管理者ステップ | isCloudManaged 値 | onPremisesSyncEnabled 値 | 説明 |
| --- | --- | --- | --- |
| 管理者が AD DS から Microsoft Entra ID にオブジェクトを同期する | `false` | `true` | オブジェクトが最初に Microsoft Entra ID に同期されている場合、 *onPremisesSyncEnabled* 属性は `true` に設定され *、isCloudManaged* は `false` に設定されます。 |
| 管理者は、オブジェクトの権限ソース (SOA) をクラウドに変換します。 | `true` | `null` | 管理者がオブジェクトの SOA をクラウドに変換すると、 *isCloudManaged 属性が*`true` に設定され、 *onPremisesSyncEnabled* 属性値が `null` に設定されます。 |
| 管理者が SOA 操作をロールバックする | `false` | `null` | 管理者が SOA を AD に変換すると、 *isCloudManaged* は `false` に設定され、同期クライアントがオブジェクトを引き継ぐまで *onPremisesSyncEnabled* は `null` に設定されます。 |
| 管理者が Microsoft Entra ID でクラウド ネイティブ オブジェクトを作成する | `false` | `null` | 管理者が Microsoft Entra ID で新しいクラウドネイティブ オブジェクトを作成した場合、 *isCloudManaged* は `false` に設定され、 *onPremisesSyncEnabled* は `null` に設定されます。 |

### SOA 更新プログラムをロールバックする

Von Bedeutung

ロールバックするグループにクラウド参照がないことを確認します。 SOA で変換されたグループからクラウド ユーザーを削除し、グループを AD DS にロールバックする前に、これらのグループをアクセス パッケージから削除します。 同期クライアントは、次の同期サイクルでオブジェクトを引き継ぎます。 グループからクラウド ユーザーを識別および削除するサンプル PowerShell スクリプトについては、「グループの [クラウド メンバー (ユーザー) を識別するためのスクリプト」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-group-source-of-authority-configure#script-to-identify-cloud-members-users-of-a-group)参照してください。

この操作を実行して、SOA 更新プログラムをロールバックし、SOA をオンプレミスに戻すことができます。

```https
PATCH https://graph.microsoft.com/v1.0/groups/{ID}/onPremisesSyncBehavior
   {
     "isCloudManaged": false
   }   
```

[Image: SOA を元に戻す API 呼び出しのスクリーンショット。]

注

*isCloudManaged* を `false` に変更すると、同期のスコープ内にある AD DS オブジェクトは、次回の実行時に Connect Sync によって引き継がれることになります。 次に Connect Sync が実行されるまで、オブジェクトはクラウドで編集できます。 SOA のロールバックは、API 呼び出しと、Connect Sync の次回のスケジュールされた実行または強制実行の *両方* が完了した後にのみ完了します。

#### 監査ログの変更を検証する

**Source of Authority の AD DS からクラウドへの変更を元に戻す** としてアクティビティを選択します。

[Image: 監査ログの「変更の取り消し」のスクリーンショット。]

### Connect Sync クライアントで検証する

1. 次のコマンドを実行して、Connect Sync を開始します。

    ```powershell
    Start-ADSyncSyncCycle
    ```
2. **同期サーバー マネージャー**でオブジェクトを開きます (詳細については、「同期クライアントの接続」セクションを参照してください)。 Microsoft Entra ID コネクタ オブジェクトが **エクスポートの確認を待機中** で *blockOnPremisesSync* = false の状態を確認できます。つまり、オブジェクト SOA がオンプレミスによって再度引き継がれることになります。

    [Image: エクスポートを待機しているオブジェクトのスクリーンショット。]

### 制限事項

- **AD DS グループの調整サポートなし**: AD DS 管理者 (または十分なアクセス許可を持つアプリケーション) は、AD DS グループを直接変更できます。 グループ SOA がグループに対して変換された場合、または AD DS へのクラウド セキュリティ グループのプロビジョニングが有効になっている場合、それらのローカル AD の変更は Microsoft Entra ID には反映されません。 クラウド セキュリティ グループに対する変更が行われると、AD DS へのグループ プロビジョニングの実行時にローカル AD DS の変更が上書きされます。
- **二重書き込み禁止**: Microsoft Entra ID から変換されたグループ (クラウド グループ A など) のメンバーシップの管理を開始し、このグループを、Microsoft Entra ID との同期のスコープ内にある別の AD DS グループ (OnPremGroupB) の下の入れ子になったグループとして AD にプロビジョニングした後、OnPremGroupB の同期が行われると、グループ A のメンバーシップ参照は同期されません。 同期クライアントがクラウド グループ メンバーシップ参照を認識していないため、メンバーシップ参照は同期されません。 この動作は設計によるものです。
- **入れ子になったグループの SOA 変換なし**: AD DS に入れ子になったグループがあり、親グループまたは上位グループの SOA を Microsoft Entra ID に変換する場合は、親グループ SOA のみが変換されます。 親グループに含まれる入れ子グループは、引き続き AD DS グループのままです。 入れ子になったグループの SOA を 1 つずつ変換する必要があります。 階層内で最も低いグループから始めて、ツリーを上に移動することをお勧めします。
- **拡張機能属性 (1 から 15) のサポートなし**: 拡張属性 1 から 15 はクラウド セキュリティ グループではサポートされず、SOA の変換後はサポートされません。

### グループのクラウド メンバー (ユーザー) を識別するスクリプト

次のスクリプトを使用して、グループからクラウド ユーザーを識別および削除できます。

```powershell
<#
.SYNOPSIS
    Finds cloud users in an Entra ID group and optionally removes them.

.DESCRIPTION
    This script pages through all users in a specified Entra ID group, identifies cloud users
    (users where onPremisesSyncEnabled is not set to true), and prints their details.
    Optionally removes these users from the group if removeUsers is set to true.

.PARAMETER GroupId
    The GUID of the Entra ID group to process. This parameter is required.

.PARAMETER RemoveUsers
    Boolean flag to indicate whether to remove cloud users from the group. 
    Default is false (optional parameter).

.EXAMPLE
    .\Find-CloudUsersInGroup.ps1 -GroupId "12345678-1234-1234-1234-123456789012"
    .\Find-CloudUsersInGroup.ps1 -GroupId "12345678-1234-1234-1234-123456789012" -RemoveUsers $true

.NOTES
    Requires Microsoft.Graph PowerShell module to be installed and appropriate permissions.
    Required Graph permissions: Group.Read.All, GroupMember.Read.All, User.Read.All and GroupMember.ReadWrite.All (if removing users)
#>

[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)]
    [System.Guid]$GroupId,

    [Parameter(Mandatory = $false)]
    [bool]$RemoveUsers = $false
)

# Import required modules
try {
    $moduleName = "Microsoft.Graph.Groups"
    if (-not (Get-Module -Name $moduleName)) {
        Import-Module -Name $moduleName
    }
    $moduleName = "Microsoft.Graph.Users"
    if (-not (Get-Module -Name $moduleName)) {
        Import-Module -Name $moduleName
    }
    Write-Host "Microsoft Graph modules imported successfully"
}
catch {
    Write-Error "Failed to import Microsoft Graph modules. Please install Microsoft.Graph PowerShell module."
    Write-Error "Run: Install-Module Microsoft.Graph -Scope CurrentUser"
    exit 1
}

# Connect to MS Graph and verify the group exists
$context = Get-MgContext
if (-not $context) {
    Connect-MgGraph -Scopes 'Group.Read.All','GroupMember.Read.All','GroupMember.ReadWrite.All','User.Read.All'
}

$group = Get-MgGroup -GroupId $GroupId -Property "Id,DisplayName,MailEnabled"
Write-Host "Processing group: $($group.DisplayName) (ID: $GroupId)"

if ($group.MailEnabled -eq $true) {
    Write-Warning "The specified group is mail-enabled. Users can only be identified using this script. To remove users, use Exchange."
}

# Initialize counters
$totalUsers = 0
$cloudUsers = 0
$removedUsers = 0

Write-Host "`nStarting to process group users..."

try {
    # Get all group members that are users
    $usersInGroup = Get-MgGroupMemberAsUser -GroupId $GroupId -All -Property "Id"

    if ($usersInGroup.Count -ge 1) {
        $totalUsers = $usersInGroup.Count
        Write-Host "Found $totalUsers total users in the group"
    }
    else {
        Write-Host "No users found in the group"
        exit 0
    }
}
catch {
    Write-Error "Failed to retrieve group users: $($_.Exception.Message)"
    exit 1
}

Write-Host "`nProcessing each user to identify cloud users..."
Write-Host "-----------------"

# Process each user
foreach ($user in $usersInGroup) {
    try {
        # Get detailed user information
        $user = Get-MgUser -UserId $user.Id -Property "Id,DisplayName,UserPrincipalName,OnPremisesSyncEnabled"

        # Check if user is a cloud user
        $isCloudUser = -not $user.OnPremisesSyncEnabled

        if ($isCloudUser) {
            $cloudUsers++

            # Print cloud user details
            Write-Host "Cloud User Found:"
            Write-Host "  Object ID: $($user.Id)"
            Write-Host "  Display Name: $($user.DisplayName)"
            Write-Host "  User Principal Name: $($user.UserPrincipalName)"
            Write-Host "  OnPremisesSyncEnabled: $($user.OnPremisesSyncEnabled)"

            # Remove user from group if requested
            if ($RemoveUsers) {
                Remove-MgGroupMemberByRef -GroupId $GroupId -DirectoryObjectId $user.Id -ErrorAction Stop
                Write-Host "REMOVED from group"
                $removedUsers++
            }
        }
    }
    catch {
        Write-Warning "Failed to process User ID $($user.Id): $($_.Exception.Message)"
    }
}

# Summary
Write-Host "-----------------"
Write-Host "SUMMARY:"
Write-Host "-----------------"
Write-Host "Total group users processed: $totalUsers"
Write-Host "Cloud users identified: $cloudUsers"
if ($RemoveUsers) {
    Write-Host "Cloud users successfully removed: $removedUsers"
}

if ($cloudUsers -eq 0) {
    Write-Host "No cloud users found in this group."
}

Write-Host "`nScript completed."
```

このスクリプトは例として提供されており、グループ メンバーシップからクラウド ユーザーを特定して削除する方法に関する公式ガイダンスと見なすべきではありません。

### 管理単位内の SOA 操作のグループのスコープを設定する

管理単位内の機関ソース操作のグループのスコープを設定するには、次の手順を実行します。

1. グループのスコープとして使用するユニットを作成します。 ユニットを作成する手順については、「 [管理単位の作成」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-manage#create-an-administrative-unit)参照してください。
2. グループをハイブリッド ID 管理者としてスコープ内に追加します。 [Image: ハイブリッド管理者ロールを管理単位スコープに割り当てるスクリーンショット。]
3. グループをユニットに追加します。 詳細については、「 [ユーザー、グループ、またはデバイスを管理単位に追加する」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-add)参照してください。
4. ユニットのスコープ内でグループの SOA を転送します。 グループの SOA の転送に関するガイドについては、「 [テスト グループの SOA の変換](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-group-source-of-authority-configure#convert-soa-for-a-test-group)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/how-to-source-of-authority-auditing-monitoring"} -->
## Microsoft Entra ID でグループの機関ソース (SOA) を監査および監視する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-source-of-authority-auditing-monitoring
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-08-01
- Summary: Microsoft Entra ID でグループの機関ソース (SOA) を監査および監視する方法について説明します。

管理者は、Azure portal または onPremisesSyncBehavior Microsoft Graph API の **監査ログ** を使用して、環境内の SOA の変更を監視および報告できます。 また、SOA の変更をサードパーティの監視システムと統合することもできます。 詳細については、 [onPremisesSyncBehavior を](https://learn.microsoft.com/ja-jp/graph/api/resources/onpremisessyncbehavior)参照してください。

### 監査ログを使用して SOA の変更を表示する方法

監査ログには、Azure portal でアクセスできます。 過去 30 日間の SOA 変更の記録が保持されます。

1. 少なくとも[レポート閲覧者](https://portal.azure.com)として [Azure portal](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) にサインインします。
2. [ **Microsoft Entra ID の管理**&gt;**監視**&gt;**監査ログ** を選択するか、検索バーで **監査ログ** を検索します。
3. **AD DS からクラウドへの機関の変更ソース**としてアクティビティを選択します。

    [Image: AD DS からクラウド アクティビティの選択に対する機関の変更元を示す Azure portal のスクリーンショット。]

### Microsoft Graph API を使用して SOA のレポートを作成する方法

Microsoft Graph を使用して、次のようなデータをレポートできます。

- SOA 変換されるオブジェクトの数を報告する
- 変換されたグループのデータをフィルター処理する
- SOA が変換およびロールバックされたオブジェクトを識別する

#### 変換されたオブジェクトのフィルター処理とカウント

[onPremisesSyncBehavior API](https://learn.microsoft.com/ja-jp/graph/api/resources/onpremisessyncbehavior) は、グループの *isCloudManaged* プロパティを表示するのに役立ちます。 *isCloudManaged* プロパティを `true` に設定して、グループ SOA を変換できます。

onPremisesSyncBehavior API を呼び出して、SOA をクラウドマネージドに変換したグループの数を照会することもできます。

```https
GET groups/{ID}/onPremisesSyncBehavior?$select=id,isCloudManaged
```

$searchと$countを使用して、変換された SOA を持つすべてのグループ オブジェクトを表示できます。 $filterまたは$countを使用する前に、Microsoft Graph エクスプローラーの **要求ヘッダー** で consistencyLevel = eventual を設定する必要があります。

```https
GET groups?$filter=onPremisesSyncBehavior/isCloudManaged eq true&$select=id,displayName,isCloudManaged&$count=true
```

### Azure Monitor を使用して Log Analytics を使用してブックとレポートを作成する方法

監査ログを Azure Monitoring と統合し、次のイベントを検索して SOA 操作を取得できます。

- オブジェクトの SOA がクラウドで管理されているため、オブジェクトがクラウドに同期されていない場合、イベント ID 6956 がログに記録されます。
- SOA 転送がオンプレミスにロールバックされると、AD DS へのグループ プロビジョニングは、AD DS グループを削除せずに変更の同期を停止します。 AD DS グループも構成スコープから削除されます。 AD DS グループはそのまま残り、AD DS は次の同期サイクルで制御を再開します。 このオブジェクトはオンプレミスで管理されているため、同期が行われないことを **監査ログ** で確認できます。

    [Image: 監査ログの詳細のスクリーンショット。]

カスタム クエリを作成する方法の詳細については、「 [プロビジョニングと Azure Monitor ログの統合方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/how-to-source-of-authority-self-service-group-management"} -->
## グループ SOA 変換後のセルフサービス グループ管理の設定 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-source-of-authority-self-service-group-management
- Service: entra-id / hybrid
- Article date: 2025-08-01
- Summary: SOA 変換後のセキュリティ グループ、メールが有効なセキュリティ グループ、配布グループに対して、Microsoft Entra でセルフサービス グループ管理を構成します。

ソース オブ オーソリティ (SOA) を使用してグループを Microsoft Entra に変換する場合、セルフサービス グループ管理を有効にすると、ユーザーは管理オーバーヘッドを減らしながら、独自のグループ メンバーシップを管理できるようになります。 この記事では、SOA 変換後にさまざまなグループの種類に対してセルフサービス グループ管理を構成する方法について説明します。これには、各グループの種類の機能、制限事項、ベスト プラクティスが含まれます。

- セキュリティ グループ
- メールが有効なセキュリティ グループ
- 配布グループ

### セキュリティ グループ

#### セルフサービス管理エクスペリエンス

セキュリティ グループの SOA を Microsoft Entra に変換すると、セルフサービス エクスペリエンスを通じて Microsoft Entra セキュリティ グループとして完全に管理できるようになります。

- [マイ グループ](https://myaccount.microsoft.com/groups) - グループ所有者は、メンバーシップの管理、グループの詳細の表示、その他のセルフサービス タスクの実行を行うことができます。 テナント管理者は、Microsoft Entra ID テナント設定の [マイ グループ] でセルフサービス管理用のセキュリティ グループを明示的に有効にする必要があります。 詳細については、「[Microsoft Entra ID でのセルフサービス グループ管理の設定](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)」をご覧ください。
- Microsoft Entra 管理センター - 管理者はグループの設定と所有権を管理できます。
- Microsoft Graph - 管理者は、Microsoft Graph API を使用してタスクを自動化し、グループを一括で管理できます。

#### サポートされているシナリオ

- ロールとアクセス許可の割り当て
- 条件付きアクセス ポリシーの適用
- アプリとリソースへのアクセスの制御
- グループ メンバーシップ、所有権、およびプロパティの管理
- アクセス パッケージの割り当てポリシーのスコープ
- ライフサイクル ワークフローの起動

#### ガバナンス統合

Microsoft Entra のセキュリティ グループは、ガバナンス機能と統合できます。

- アクセス パッケージ (エンタイトルメント管理を使用): リソースへのアクセスをバンドルして委任し、承認ワークフローを自動化し、アクセス ライフサイクルを管理します。
- ライフサイクル ワークフロー: プロビジョニング、プロビジョニング解除、アクセス レビューなど、ユーザーとグループのライフサイクル イベントを自動化します。

#### セルフサービスと正しいグループの所有権と参加ポリシーを有効にする方法

1. グループ所有者の設定: SOA を変換する前に、オンプレミスのグループ管理ツールでグループの所有権が確立されていることを確認します。
2. SOA の変換: 権限のグループ ソースを Microsoft Entra に移行します。
3. セルフサービス グループ管理ポリシーを設定する (該当する場合): 組織 ** でSelf-Service グループ管理**を使用している場合は、グループの転送後に **マイ グループ** にポリシーを手動で適用します。

### メールが有効なセキュリティ グループ

#### セルフサービス管理エクスペリエンス

メールが有効なセキュリティ グループの SOA を変換すると、それらはクラウドで管理されるメールが有効なセキュリティ グループになります。

クラウドで管理されるメールが有効なセキュリティ グループには、いくつかの制限があります。

- これらのグループは、Microsoft Entra では読み取り専用です。
- マイ グループまたはセルフサービス インターフェイスを使用して管理することはできません。
- 管理は、Exchange Online 管理センターまたは Exchange PowerShell を使用して管理者のみが使用できます。

セルフサービス管理が必要な場合の回避策として、メールが有効なセキュリティ グループをオンプレミス環境の標準セキュリティ グループに変換し、SOA を変換することを検討してください。 この回避策により、グループのメールが有効な機能が削除されます。

#### ガバナンス統合

メールが有効なセキュリティ グループは、現在、アクセス パッケージやライフサイクル ワークフローなどの Microsoft Entra ガバナンス機能と互換性がありません。

### 配布グループ

#### セルフサービス管理エクスペリエンス

オンプレミスの配布リストの SOA を変換した後、クラウド管理の配布グループに移行できます。 可能であれば、完全なセルフサービスおよびガバナンス統合のために配布グループを Microsoft 365 グループに変換します。 詳細については、「 [配布リストを Microsoft 365 グループにアップグレードする](https://learn.microsoft.com/ja-jp/exchange/recipients-in-exchange-online/manage-distribution-groups/upgrade-distribution-lists)」を参照してください。

#### 制限事項

- クラウド配布グループは、 **マイ グループ**を使用して管理することはできません。
- セルフサービス管理は、Exchange のエンド ユーザーのセルフサービス ポータルでのみ可能です。
- 入れ子になった配布グループは、Microsoft 365 グループへの直接変換ではサポートされていません。

#### ガバナンス統合

配布グループは、アクセス パッケージやライフサイクル ワークフローなどの Microsoft Entra Governance 機能では使用できません。 ただし、配布グループを Microsoft 365 グループに変換すると、ガバナンス シナリオのブロックが解除されます。

### 概要テーブル

| グループの種類 | マイ グループでのセルフサービス | 管理エクスペリエンス | ガバナンスのサポート | 推奨されるアクション |
| --- | --- | --- | --- | --- |
| セキュリティ グループ | イエス | Microsoft Entra 管理センター、Graph、マイ グループ | イエス | 所有者を設定し、SOAを変換し、セルフサービスグループ管理を使用する |
| メールが有効なセキュリティ グループ | いいえ (読み取り専用) | Exchange Online、PowerShellエンド ユーザー向けのセルフサービス エクスペリエンスなし | いいえ | 必要に応じてセキュリティ グループに変換する |
| 配布グループ | いいえ | Exchange 管理センター、Exchange Self-Service ポータル | いいえ | 可能であれば Microsoft 365 グループに変換する |

### 管理者向けの重要なポイント

- **セルフサービスのセキュリティ グループを有効にする**: ユーザーのロールアウトの前に、Microsoft Entra Admin Center でセルフサービスのセキュリティ グループを有効にします。
- **グループの所有権を確立**する: 適切なセルフサービス エクスペリエンスを確保するために SOA を変換する前に、所有権をオンプレミスで設定する必要があります。
- **ガバナンス シナリオのサポート**: ガバナンス機能をサポートするのは、Microsoft Entra セキュリティ グループと Microsoft 365 グループだけです。 配布グループとメール対応のセキュリティ グループは、その機能を持っていません。
- **制限事項の計画**: 現在のグループの種類を確認し、最適なエンド ユーザーとガバナンス エクスペリエンスを実現 [するために、それらを Microsoft 365 グループにアップグレード](https://learn.microsoft.com/ja-jp/exchange/recipients-in-exchange-online/manage-distribution-groups/upgrade-distribution-lists) することを検討してください。

グループ管理の詳細については、「グループの [種類、メンバーシップの種類、およびアクセス管理について」](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/how-to-user-source-of-authority-configure"} -->
## Microsoft Entra ID でユーザーの信頼のソース (SOA) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-user-source-of-authority-configure
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-04-15
- Summary: ユーザー Source of Authority (SOA) を使用して、管理を Active Directory Domain Services (AD DS) から Microsoft Entra ID に移行する方法について説明します。

この記事では、権限ソース (SOA) のユーザーと連絡先の両方を構成するための前提条件と手順について説明します。 この記事では、変更を元に戻す方法と、現在の機能制限についても説明します。 ユーザー SOA の完全な概要については、「 [クラウド優先の体制を採用する: ユーザーの権限ソースをクラウドに転送する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview)」を参照してください。

### [前提条件]

| Requirement | Description |
| --- | --- |
| **役割** | [ハイブリッド管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-administrator) は、Microsoft Graph API を呼び出して、ユーザーの SOA の読み取りと更新を行う必要があります。[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) または [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) は、Microsoft Graph Explorer または Microsoft Graph API の呼び出しに使用されるアプリに、必要なアクセス許可にユーザーの同意を付与する必要があります。 |
| **アクセス許可** | `onPremisesSyncBehavior` Microsoft Graph API を呼び出すアプリの場合は、`User-OnPremisesSyncBehavior.ReadWrite.All`アクセス許可スコープを付与する必要があります。 詳細については、Microsoft Entra 管理センターを使用して [このアクセス許可に同意する方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-user-source-of-authority-configure#consent-permission-to-apps) を参照してください。 |
| **必要なライセンス** | Microsoft Entra Free ライセンス。 |
| **同期クライアントの接続** | 最小バージョンは [2.5.76.0 です](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#25760)。 Contact SOA、バージョン [2.5.79.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#25790) を使用するには |
| **クラウド同期クライアント** | 最小バージョンは [1.1.1370.0 です](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1113700) |

### 設定

Connect Sync クライアントと Microsoft Entra Provisioning エージェントを設定する必要があります。

#### 同期クライアントを接続する

1. Connect Sync ビルドの最新バージョンをダウンロードします。
2. Connect Sync ビルドが正常にインストールされていることを確認します。 コントロール パネルの **[プログラム** ] に移動し、Microsoft Entra Connect Sync のバージョンが [2.5.76.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#25760) であることを確認します。

#### クラウド同期クライアント

ビルド バージョン [1.1.1370.0 以降の](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#1113700) Microsoft Entra Provisioning エージェントをダウンロードします。

1. 指示に従って [Cloud Sync クライアントをダウンロードします](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/reference-version-history#download-link)。
2. [エージェントの現在のバージョンを特定する](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/cloud-sync/how-to-automatic-upgrade)方法について説明します。
3. [手順に従って、AD DS から Microsoft Entra ID へのプロビジョニングを構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure)します。

### アプリへのアクセス許可に対する同意

Microsoft Entra 管理センターでアクセス許可を同意できます。 この高い特権を持つ操作には、アプリケーション管理者またはクラウド アプリケーション管理者ロールが必要です。 PowerShell を使用して同意を付与することもできます。 詳細については、「 [1 人のユーザーに代わって同意を付与する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-consent-single-user?pivots=ms-graph)参照してください。

#### カスタム アプリ

対応するアプリに `User-OnPremisesSyncBehavior.ReadWrite.All` アクセス許可を付与するには、次の手順に従います。 アプリの登録に新しいアクセス許可を追加し、同意を付与する方法の詳細については、「 [Microsoft Entra ID でアプリの要求されたアクセス許可を更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-update-permissions)する」を参照してください。

注

連絡先の SOA を転送するには、必要なアクセス許可が `Contacts-OnPremisesSyncBehavior.ReadWrite.All`。

#### Microsoft Entra 管理センターを使用してアプリへのアクセス許可を同意する

1. [アプリケーション管理者](https://entra.microsoft.com)または[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者として Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Enterprise Apps**&gt;***App name*** に移動します。
3. [**アクセス許可**]&gt;***テナント名*に対する管理者の同意を選択します**。
4. [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)または[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)管理者としてもう一度サインインします。
5. 同意が必要なアクセス許可の一覧を確認し、[ **同意する**] を選択します。
6. 付与したアクセス許可の一覧を確認できます。

[Image: アクセス許可が付与されていることを検証する方法のスクリーンショット。]

#### Graph エクスプローラーにアクセス許可を付与する

1. [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を開き、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. プロファイル アイコンを選択し、[ **アクセス許可に同意する**] を選択します。
3. User-OnPremisesSyncBehavior を検索し、アクセス許可の **[同意** ] を選択します。 [Image: グラフのアクセス許可のスクリーンショット。]

### テスト ユーザーの SOA の転送

注

`https://graph.microsoft.com/v1.0/contacts` API エンドポイントを使用して、連絡先 SOA を転送することもできます。

テスト ユーザーの SOA を転送するには、次の手順に従います。

1. AD 内にユーザーを作成します。 Connect Sync を使用して、Microsoft Entra ID に同期されている既存のユーザーを使用することもできます。
2. 次のコマンドを実行して、Connect Sync を開始します。

    ```powershell
    Start-ADSyncSyncCycle
    ```
3. ユーザーが Microsoft Entra 管理センターに同期されたユーザーとして表示されることを確認します。
4. Microsoft Graph API を使用して、ユーザー オブジェクトの SOA を転送します (*isCloudManaged*=true)。 [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を開き、ユーザー管理者などの適切なユーザー ロールでサインインします。
5. 既存の SOA の状態を確認してみましょう。 SOA はまだ更新していないので、 *isCloudManaged* 属性値は false にする必要があります。 次の例の *{ID} を* 、ユーザーのオブジェクト ID に置き換えます。 この API の詳細については、「 [Get onPremisesSyncBehavior](https://learn.microsoft.com/ja-jp/graph/api/onpremisessyncbehavior-get)」を参照してください。 /graph/api/onpremisessyncbehavior-update

    ```https
    GET https://graph.microsoft.com/v1.0/users/{ID}/onPremisesSyncBehavior?$select=isCloudManaged
    ```

    [Image: ユーザーのプロパティを確認するための GET 呼び出しのスクリーンショット。]
6. 同期されたユーザーが読み取り専用であることを確認します。 ユーザーはオンプレミスで管理されているため、クラウド内のユーザーに対する書き込み試行は失敗します。 メールが有効なユーザーの場合、エラー メッセージは異なりますが、更新はまだ許可されていません。

    注

    この API が 403 で失敗した場合は、[ **アクセス許可の変更** ] タブを使用して、必要な User.ReadWrite.All アクセス許可に同意を付与します。

    ```https
    PATCH https://graph.microsoft.com/v1.0/users/{ID}/
       {
         "DisplayName": "User Name Updated"
       }   
    ```

    [Image: 読み取り専用であることを確認するためにユーザーを更新しようとする試みのスクリーンショット。]
7. Microsoft Entra 管理センターでユーザーを検索します。 すべてのユーザー フィールドがグレー表示されていること、およびソースが Windows Server AD DS であることを確認します。
8. これで、クラウドで管理されるようにユーザーの SOA を更新できます。 クラウドに転送するユーザー オブジェクトに対して、Microsoft Graph Explorer で次の操作を実行します。 この API の詳細については、「 [Update onPremisesSyncBehavior](https://learn.microsoft.com/ja-jp/graph/api/onpremisessyncbehavior-update)」を参照してください。

    ```https
    PATCH https://graph.microsoft.com/v1.0/users/{ID}/onPremisesSyncBehavior
       {
         "isCloudManaged": true
       }   
    ```

    [Image: ユーザーのプロパティを更新する PATCH 操作のスクリーンショット。]
9. 変更を検証するには、GET を呼び出して *isCloudManaged* が true であることを確認します。

    ```https
    GET https://graph.microsoft.com/v1.0/users/{ID}/onPremisesSyncBehavior?$select=isCloudManaged
    ```

    [Image: Microsoft Graph エクスプローラーを使用してユーザーの SOA 値を取得する方法のスクリーンショット。]
10. 監査ログの変更を確認します。 Azure portal で監査ログにアクセスするには、 **Microsoft Entra ID の管理**&gt;**Monitoring**&gt;**Audit ログ**を開くか、 *監査ログ*を検索します。 アクティビティとして **権限元を AD からクラウドに変更** を選択します。
11. ユーザーがクラウドで更新できることを確認します。

    ```https
    PATCH https://graph.microsoft.com/v1.0/users/{ID}/
       {
         "DisplayName": "Update User Name"
       }   
    ```

    [Image: ユーザーのプロパティを変更するための再試行のスクリーンショット。]
12. Microsoft Entra 管理センターを開き、ユーザー **のオンプレミス同期が有効な** プロパティが **[はい**] であることを確認します。

### 同期クライアントの接続

1. 次のコマンドを実行して、Connect Sync を開始します。

    ```powershell
    Start-ADSyncSyncCycle
    ```
2. 転送された SOA を持つユーザー オブジェクトを確認するには、 **Synchronization Service Manager** でコネクタに移動 **します**。

    [Image: コネクタのスクリーンショット。]
3. **Active Directory Domain Services コネクタ**を右クリックします。 相対ドメイン名 (RDN) 設定 "CN=&lt;UserName&gt;" でユーザーを検索します。

    [Image: RDN を検索する方法のスクリーンショット。]
4. 検索したエントリをダブルクリックし、[ **系列**&gt;**Metaverse オブジェクトのプロパティ]** を選択します。

    [Image: 系列を表示する方法のスクリーンショット。]
5. **コネクタを**選択し、"CN={Alphanumeric Characters&lt;}" の &gt;をダブルクリックします。
6. Microsoft Entra ID オブジェクトで **blockOnPremisesSync** プロパティが true に設定されていることがわかります。 このプロパティ値は、対応する AD DS オブジェクトで行われた変更が Microsoft Entra ID オブジェクトにフローしないことを意味します。

    [Image: データ フローをブロックする方法のスクリーンショット。]
7. オンプレミスのユーザー オブジェクトを更新しましょう。 ユーザー名を *TestUserF1* から *TestUserF1.1* に変更します。

    [Image: オブジェクト名を変更する方法のスクリーンショット。]
8. 次のコマンドを実行して、Connect Sync を開始します。

    ```powershell
    Start-ADSyncSyncCycle
    ```
9. イベント ビューアーを開き、アプリケーション ログでイベント ID 6956 をフィルター処理します。 このイベント ID は、オブジェクトの SOA がクラウド内にあるため、オブジェクトがクラウドに同期されていないことを顧客に通知するために予約されています。

    [Image: イベント ID 6956 のスクリーンショット。]

#### SOA を転送した後の属性の状態

次の表では、オブジェクトの SOA を転送した後の *isCloudManaged* 属性と *onPremisesSyncEnabled* 属性の状態について説明します。

| 管理ステップ | isCloudManagedの値 | onPremisesSyncEnabled の値 | 説明 |
| --- | --- | --- | --- |
| 管理者が AD DS から Microsoft Entra ID にオブジェクトを同期する | `false` | `true` | オブジェクトが最初に Microsoft Entra ID に同期されている場合、 *onPremisesSyncEnabled* 属性は `true` に設定され *、isCloudManaged* は `false` に設定されます。 |
| 管理者がオブジェクトの機関ソース (SOA) をクラウドに転送する | `true` | `null` | 管理者がオブジェクトの SOA をクラウドに転送すると、 *isCloudManaged 属性が*`true` に設定され、 *onPremisesSyncEnabled* 属性値が `null` に設定されます。 |
| 管理者が SOA 操作をロールバックする | `false` | `null` | 管理者が SOA を AD に転送すると、 *isCloudManaged* は `false` に設定され、同期クライアントがオブジェクトを引き継ぐまで *onPremisesSyncEnabled* は `null` に設定されます。 |
| 管理者が Microsoft Entra ID でクラウド ネイティブ オブジェクトを作成する | `false` | `null` | 管理者が Microsoft Entra ID で新しいクラウドネイティブ オブジェクトを作成した場合、 *isCloudManaged* は `false` に設定され、 *onPremisesSyncEnabled* は `null` に設定されます。 |
| 管理者が Microsoft Entra ID でクラウド ネイティブ オブジェクトを作成する | `false` | `null` | 管理者が Microsoft Entra ID で新しいクラウドネイティブ オブジェクトを作成した場合、 *isCloudManaged* は `false` に設定され、 *onPremisesSyncEnabled* は `null` に設定されます。 |

### SOA 更新プログラムをロールバックする

Important

ロールバックするユーザーにクラウド参照がないことを確認します。 ユーザーを AD DS にロールバックする前に、SOA で転送されたグループからクラウド ユーザーを削除し、アクセス パッケージからこれらのグループを削除します。 同期クライアントは、次の同期サイクルでオブジェクトを引き継ぎます。

SOA 更新プログラムをロールバックし、SOA をオンプレミスに戻すには、次の手順に従います。

1. `blockCloudObjectTakeoverThroughHardMatchEnabled`機能フラグを無効にします。 このフラグは、既定で、権限のソースをクラウドからオンプレミスに切り替えるのをブロックします。 無効にするには、次のリクエストをMicrosoft Graphに送信します。

    ```http
    PATCH https://graph.microsoft.com/beta/directory/onPremisesSynchronization/{id}
    {
        "features": {
            "blockCloudObjectTakeoverThroughHardMatchEnabled": false
        }
    }
    ```

    `{id}`を、テナントのオンプレミスディレクトリ同期構成 ID に置き換えます。
2. 次の操作を実行して SOA をロールバックし、オンプレミスの管理に戻します。

    ```https
    PATCH https://graph.microsoft.com/v1.0/users/{ID}/onPremisesSyncBehavior
       {
         "isCloudManaged": false
       }   
    ```

    [Image: SOA を元に戻す API 呼び出しのスクリーンショット。]

注

*isCloudManaged* を `false` に変更すると、同期のスコープ内にある AD DS オブジェクトは、次回の実行時に Connect Sync によって引き継がれることになります。 次に Connect Sync が実行されるまで、オブジェクトはクラウドで編集できます。 SOA のロールバックは、API 呼び出しと、Connect Sync の次回のスケジュールされた実行または強制実行の *両方* が完了した後にのみ完了します。

#### 監査ログの変更を検証する

**AD DS からクラウドへの承認ソースの変更を元に戻す**アクティビティを選択します。

[Image: 監査ログの変更取り消しのスクリーンショット。]

### Connect Sync クライアントで検証する

1. 次のコマンドを実行して、Connect Sync を開始します。

    ```powershell
    Start-ADSyncSyncCycle
    ```
2. **同期サーバー マネージャー**でオブジェクトを開きます (詳細については、「同期クライアントの接続」セクションを参照してください)。 Microsoft Entra ID コネクタ オブジェクトが **エクスポートの確認を待機中** で *blockOnPremisesSync* = false の状態を確認できます。つまり、オブジェクト SOA がオンプレミスによって再度引き継がれることになります。

    [Image: エクスポートを待機しているオブジェクトのスクリーンショット。]

Important

ロールバックが完了し、Connect Sync がオブジェクトを引き継いだ後、 `blockCloudObjectTakeoverThroughHardMatchEnabled` 機能フラグを再度有効にして、意図しないクラウド オブジェクトの引き継ぎに対する保護を維持します。

```http
PATCH https://graph.microsoft.com/beta/directory/onPremisesSynchronization/{id}
{
    "features": {
        "blockCloudObjectTakeoverThroughHardMatchEnabled": true
    }
}
```

### SOA で転送されたユーザーのオンプレミス属性をクリアする

オンプレミス リソースへのアクセスに使用されるクラウド ユーザー オブジェクトに存在するオンプレミス [のプロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) の一覧を次に示します。

- onPremisesDistinguishedName
- onPremisesDomainName
- オンプレミスSAMアカウント名
- オンプレミス セキュリティ識別子
- オンプレミスユーザープリンシパル名 (onPremisesUserPrincipalName)

管理者が SOA の転送後にオンプレミスのリソースにアクセスする場合は、これらの属性を削除せず、 [Microsoft Graph を使用して手動で](https://learn.microsoft.com/ja-jp/graph/api/resources/user)これらの属性を維持する必要があります。

### 管理ユニット内で SOA 操作に関連するユーザーのスコープを設定する

管理単位内の権限ソース操作のスコープをユーザーに設定するには、次の手順を実行します。

1. ユーザーのスコープとして使用するユニットを作成します。 ユニットを作成する手順については、「 [管理単位の作成」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-manage#create-an-administrative-unit)参照してください。
2. ユーザーをハイブリッド ID 管理者としてスコープ内に追加します。 [Image: ハイブリッド管理者ロールを管理単位スコープに割り当てるスクリーンショット。]
3. ユニットにユーザーを追加します。 詳細については、「 [ユーザー、グループ、またはデバイスを管理単位に追加する」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-add)参照してください。
4. ユニットのスコープ内でユーザーの SOA を転送します。 ユーザーの SOA の転送に関するガイドについては、「 [テスト ユーザーの SOA の転送](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-user-source-of-authority-configure#transfer-soa-for-a-test-user)」を参照してください。

### Contact SOA を構成する

また、この記事で提供されている例を使用して、ユーザーの連絡先の SOA を転送することもできます。 連絡先は Outlook のアイテムで、通信相手のユーザーや組織に関する情報を整理して保存できます。 次の例では、連絡先の SOA を転送する方法を示します。

注

連絡先の SOA を転送するには、必要なアクセス許可が `Contacts-OnPremisesSyncBehavior.ReadWrite.All`。

連絡先 SOA がクラウドで管理されていることを確認するには、次の API 呼び出しを実行します。

```https
GET https://graph.microsoft.com/v1.0/contacts/{ID}/onPremisesSyncBehavior?$select=isCloudManaged
```

クラウド管理の連絡先を更新するには、次の API 呼び出しを行います。

```https
   PATCH https://graph.microsoft.com/v1.0/contacts/{ID}/
      {
        "DisplayName": "Contact Name Updated"
      }   
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/install"} -->
## 同期ツールのインストール - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/install
- Service: entra-id / hybrid
- Article date: 2025-09-18
- Summary: この記事では、クラウド同期または Microsoft Entra Connect をインストールするために必要な手順について説明します。

次のドキュメントでは、クラウド同期または Microsoft Entra Connect をインストールする手順について説明します。

### クラウド同期用の Microsoft Entra プロビジョニング エージェントをインストールする

クラウド同期では、Microsoft Entra プロビジョニング エージェントを使用します。 次の手順に従ってインストールします。

1. 少なくともハイブリッド ID 管理者として、Microsoft Entra 管理センターにサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. **クラウド同期の**選択
2. 左側の **[エージェント]** を選びます。
3. **[オンプレミス エージェントのダウンロード]** を選び、**[条件に同意して & ダウンロード]** を選びます。
4. **Microsoft Entra プロビジョニング エージェント パッケージ**のダウンロードが完了したら、ダウンロード フォルダから *AADConnectProvisioningAgentSetup.exe* インストール ファイルを実行します。
5. スプラッシュ スクリーンで **[ライセンスと条件に同意する]** を選んでから、**[インストール]** を選択します。
6. インストール操作が完了すると、構成ウィザードが起動します。 **[次へ]** を選択して構成を開始します。
7. **[拡張機能の選択]** 画面で、**[人事主導のプロビジョニング (Workday や SuccessFactors) / Microsoft Entra Connect クラウド同期]** を選択し、**[次へ]** をクリックします。
8. Microsoft Entra ハイブリッド ID 管理者アカウントでサインインします。
9. **[サービス アカウントの構成]** 画面で、グループ管理サービス アカウント (gMSA) を選択します。 このアカウントはエージェント サービスの実行に使用されます。 続けるには、 **[次へ]** を選択します。
10. **[Active Directory の接続]** 画面で、ドメイン名が **[構成済みドメイン]** の下に表示される場合は、次の手順に進みます。 それ以外の場合は、Active Directory ドメイン名を入力し、**[ディレクトリの追加]** を選択します。
11. Active Directory ドメイン管理者アカウントでサインインします。 **[OK]** を選んでから、**[次へ]** を選択して続行します。
12. **[次へ]** を選択して続行します。
13. **[構成の完了]** 画面で、 **[確認]** を選択します。
14. この操作が完了すると、**[エージェントの構成が正常に検証されました]** と通知されるはずです。**[終了]** を選択できます。
15. まだ最初のスプラッシュ スクリーンが表示される場合は、**[閉じる]** を選択します。

詳細については、クラウド同期リファレンス セクションにある[プロビジョニング エージェントのインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)に関するページを参照してください。

### 簡易設定を使用して Microsoft Entra Connect をインストールする

簡易設定は、Microsoft Entra をインストールするための既定のオプションであり、最も一般的にデプロイされるシナリオに使用されます。

1. Microsoft Entra Connect をインストールするサーバーでローカル管理者としてサインインします。 サインインするサーバーが同期サーバーです。
2. *AzureADConnect.msi* に移動し、ダブルクリックしてインストール ファイルを開きます。
3. **[開始]** で、ライセンス条項に同意するチェック ボックスをオンにし、**[続行]** を選択します。
4. **[簡易設定]** で、**[簡易設定を使う]** を選択します。
5. **[Microsoft Entra ID に接続]** で、ハイブリッド ID 管理者アカウントのユーザー名とパスワードを入力し、**[次へ]** を選択します。
6. **[AD DS に接続]** で、エンタープライズ管理者アカウントのユーザー名とパスワードを入力します。 ドメイン部分は、`FABRIKAM\administrator` や `fabrikam.com\administrator` のように、NetBIOS 形式または FQDN 形式で入力できます。 **[次へ]** を選択します
7. [\[Microsoft Entra サインインの構成\]](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-user-signin#azure-ad-sign-in-configuration) ページは、[前提条件](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)の[ドメインの検証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)の手順が済んでいない場合にのみ表示されます。
8. **[構成の準備完了]** で、**[インストール]** を選択します。
9. インストールが完了したら、**[終了]** を選択します。
10. Synchronization Service Manager または同期規則エディターを使用する前に、サインアウトして、もう一度サインインします。

詳細については、Microsoft Entra Connect 同期リファレンス セクションの「[簡易設定を使用した Microsoft Entra Connect のインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express)」を参照してください。

### カスタム設定による Microsoft Entra Connect

その他のインストール オプションが必要な場合は、Microsoft Entra Connect の*カスタム設定*を使用します。

詳細については、Microsoft Entra Connect 同期リファレンス セクションの「[カスタム設定を使用した Microsoft Entra Connect のインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/on-demand-provision"} -->
## クラウド同期を使用したオンデマンド プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/on-demand-provision
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra Cloud Sync でオンデマンド プロビジョニングを使用する方法について説明します。

クラウド同期を使用して、これらの変更を 1 人のユーザーに適用することで、構成の変更をテストできます。 このオンデマンド プロビジョニングは、構成に加えられた変更が正しく適用され、Microsoft Entra ID に正しく同期されていることを検証し、検証するのに役立ちます。 この機能はクラウド同期でのみ使用でき、Microsoft Entra Connect では使用できません。

### オンデマンド プロビジョニングを使用する手順

オンデマンド プロビジョニングを使用するには、次の手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. [ **構成]** で、構成を選択します。
2. 左側の、**[オンデマンドでプロビジョニング]** を選択します。
3. ユーザーの識別名を入力し、[ **プロビジョニング** ] ボタンを選択します。
4. プロセスが完了すると、4 つの緑色のチェック マークが付いた成功画面が表示されます。 エラーは画面の左側に表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/prepare-user-source-of-authority-environment"} -->
## ユーザー SOA 用に環境を準備する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/prepare-user-source-of-authority-environment
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-08-14
- Summary: ユーザーに対してソース オブ オーソリティ (SOA) を使用するように環境を準備する手順について説明します。

ユーザーの権限のソースを使用してオンプレミスのフットプリントを最小限に抑える場合は、ユーザーの構成方法に基づいて Active Directory 環境を準備する必要があります。 環境を準備する方法は、多くの要因に基づいています。 その他の考慮事項については、「 [ユーザー SOA の転送の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview#prerequisites-for-transferring-user-soa) 」および「 [ユーザーの権限ソース (SOA) を使用するためのガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-guidance)」を参照してください。

ユーザー SOA の準備のフローは次のとおりです。

[Image: 権限のソースを準備する手順のスクリーンショット。]

この記事では、ユーザー SOA を切り替える前に、Microsoft Exchange、HR 統合、および Microsoft Identity Manager 環境を準備するために完了する必要がある手順について説明します。

### AD オブジェクトの SOA を変更する準備ができているか確認する

ユーザーの SOA を転送する前に、Active Directory ドメインからオブジェクトを取得し、次の情報を確認して転送する準備ができていることを確認します。

- オブジェクトが既に Microsoft Entra に同期されていることを確認します。 管理オブジェクトまたは同期から除外されたオブジェクトでは、SOA を変更できません。
- これらのユーザーのすべての属性が、変更予定の属性を含めて、Microsoft Entra に同期され、ディレクトリ属性またはディレクトリ *スキーマ拡張機能*として Microsoft Graph で表示されていることを確認します。
- Active Directory のユーザー オブジェクトに、ユーザーのマネージャーの属性以外の参照値属性と、 `memberof`などの Active Directory のバックリンクが設定されていないことを確認します。 その他の参照値属性は、SOA 転送ではサポートされていません。
- マネージャー属性とメンバー属性の値が設定されている場合は、同じ Active Directory ドメイン内のユーザーへの参照である必要があり、それらが Microsoft Entra に同期されていることを確認します。 他の種類のオブジェクトや、このドメインから Microsoft Entra に同期されていないオブジェクトを参照することはできません。
- Active Directory Domain Services (AD DS) 自体以外の別の Microsoft オンプレミス テクノロジによって更新されるオブジェクトに属性がないことを確認します。 たとえば、 `userCertificate` 属性が [Active Directory 証明書サービス](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-cs/active-directory-certificate-services-overview)によって管理されているユーザーの SOA を変更しないでください。

### Active Directory の更新

一部の Active Directory ユーザーの SOA のみを変更する予定で、すべてのユーザーが LDAP を使用しない Kerberos アプリケーションを使用して現在 1 つの OU に存在する場合は、これらのオブジェクトに対して新しい AD DS OU を作成することをお勧めします。 それらを別の OU に配置すると、SOA の変更後に Active Directory で誤って更新を行うのを回避できます。 SOA が変更されていないユーザーは、Active Directory ユーザーとコンピューター、PowerShell 用 Active Directory モジュール、またはその他の Active Directory 管理ツールを使用して引き続き管理できます。 OU を作成したら、その OU にオブジェクトを移動します。 詳細については、「 [Move-ADObject](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/move-adobject?view=windowsserver2025-ps&preserve-view=true)」を参照してください。

### Microsoft Exchange のセットアップを準備する

Microsoft 365 Exchange Online で Exchange ハイブリッドをセットアップしている場合は、ユーザー アカウントの SOA を切り替える前に、次のガイダンスに従って Exchange Server と Exchange Online を準備します。 Exchange ハイブリッド構成を実行している場合は、すべてのユーザーの SOA をクラウドに切り替える前に、すべてのメールボックスが Exchange Online に移行されていることを確認します。 すべてのユーザーのメールボックス移行後、これらのユーザーは Microsoft 365 で管理でき、ユーザーの SOA をクラウドに安全に切り替えることができます。 SOA を切り替えた場合は、次の手順を実行して Exchange ハイブリッドを無効にします。

1. MX レコードと自動検出 DNS レコードを Exchange Server ではなく Exchange Online にポイントします。
2. Exchange サーバー上のサービス接続ポイント (SCP) の値を削除します。 この手順では、SCP が返されないようにし、Outlook クライアントは自動検出に DNS メソッドを使用します。
3. (省略可能)環境をセキュリティで保護するには、Exchange Server と Exchange Online の間のメール フローに使用されるハイブリッド構成ウィザードによって作成された受信コネクタと送信コネクタを削除します。
4. (省略可能)環境をセキュリティで保護するには、Exchange Server と Exchange Online by HCW の間に設定された組織関係、Fed Trust、oauth 信頼を削除します。
5. オブジェクトのオンプレミス Exchange への書き込みを停止し、オブジェクトをクラウドに同期して、EXO にオンプレミスからの最新の変更があることを確認します。

Exchange ハイブリッドを無効にする方法の詳細については、「管理ツールを [使用して Exchange ハイブリッド環境で受信者を管理する](https://learn.microsoft.com/ja-jp/Exchange/manage-hybrid-exchange-recipients-with-management-tools)」を参照してください。

### プロビジョニング時のユーザーの構成を人事システムから移行する

SOA を設定する次の手順は、人事システムのプロビジョニング戦略を決定することです。 SOA より前の世界では、人事システムのユーザーは最初にオンプレミスの Active Directory にプロビジョニングされ、次に Microsoft Entra Connect Sync または Cloud Sync を使用して Microsoft Entra ID に同期されます。Microsoft Entra プロビジョニング サービスを使用している場合は、ほとんどの場合、Workday から AD へのユーザー プロビジョニング、SAP SuccessFactors から AD へのユーザー プロビジョニング、または AD への API 駆動型プロビジョニングのいずれかのアプリを構成している可能性があります。 次の図は、この構成のデータ フローを示しています。

[Image: 権限のソースを転送する前の人事管理システムのスクリーンショット。]

この構成を更新するには、まず、Microsoft Entra ID に直接プロビジョニングでき、Active Directory では不要になった従業員を人事システムから特定します。 人事部門で利用可能なグループ化コンストラクトを使用して、このような従業員を段階的に特定できます。たとえば、部門別、コスト センター別、または場所別にユーザーの SOA を転送します。 次に、次のセクションの説明に従って、Microsoft Entra プロビジョニング構成を更新します。

#### HR プロビジョニング構成を更新する

SOA 変換の従業員を特定したら、次の手順に従います。

1. *HR のオンプレミス Active Directory への*プロビジョニング ジョブを停止してください。
2. SOA 変換の対象となるユーザーの場合は、ユーザーの SOA をオンプレミスの Active Directory から Entra ID に切り替えます。
3. 新しい "*HR to Microsoft Entra ID*" プロビジョニング アプリケーションを作成して、人事システムから Entra ID にユーザーをプロビジョニングします。 スコープ フィルターを使用して、SOA に変換されたユーザーのみを処理するようにこのアプリを制限します。 例: 最初のフェーズで、"Finance" 部門のユーザーに対してのみ SOA を変換する場合は、スコープ フィルターを部門 EQUALS "Finance" として設定します。 今後のフェーズでスコープ フィルターを展開して、より多くのユーザーを含めます。
4. 新しい "*HR to Microsoft Entra ID*" プロビジョニング ジョブを開始します。
5. "HR からオンプレミス Active Directory" プロビジョニング アプリの構成を更新して、SOA 変換されたユーザーが AD に同期されないようにします。 プロビジョニング ジョブでスコープ外削除のスキップ フラグが設定されていることを確認します。 上記の例を続けると、フィルター部門 NOT EQUALS "Finance" を設定することで、"Finance" 部門のユーザーを AD との同期から除外できます。
6. "*オンプレミス Active Directory への HR*" プロビジョニング ジョブを再起動します。
7. 同様のスコープ フィルターを使用して、SOA 変換されたユーザーが Entra ID に同期されないように、Entra Connect Sync/Cloud Sync アプリの構成を更新します。

次の図は、新しい SOA 対応のプロビジョニング構成を示しています。

[Image: 変換後の人事システムの機関ソースのスクリーンショット。]

### MIM セットアップを準備する

Microsoft Identity Manager (MIM) を使用しているお客様の場合は、MIM の同期規則を更新して、どのオブジェクトが Active Directory にプロビジョニングされ続け、どのオブジェクトが Microsoft Entra ID にプロビジョニングされているかを判断できます。

[Image: SOA の MIM セットアップのスクリーンショット。]

ユーザー SOA の MIM セットアップを準備するには、次の手順を実行します。

1. Active Directory と Microsoft Entra の両方で同じユーザーの一意識別子となる属性を選択します。
2. [MIM 同期に Microsoft Graph 用 Microsoft Identity Manager コネクタ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016-connector-graph)を追加し、このコネクタから取り込まれた既存の Active Directory ユーザーの参加を構成します。
3. ユーザーの優先順位の設定を確認します。
4. Microsoft Graph コネクタを使用して、Microsoft Entra から完全なインポートを実行します。
5. SOA 変換を計画しているすべてのユーザーが、メタバースと Microsoft Graph コネクタの間で結合されていることを確認します。
6. これらのユーザー (MIM ポータルやサービスなど) の管理システムを更新して、SOA が Microsoft Entra ID に変更されるユーザー オブジェクトにラベルを追加します。
7. MIM から Active Directory にラベル付けされたユーザー オブジェクトを同期するのではなく、Microsoft Graph コネクタに同期するように同期規則を更新します。
8. SOA 転送が完了するまで、同期規則は非アクティブのままにします。
9. SOA 転送後、ユーザーを再同期します。 SOA が変更されたユーザーに対して AD MA に保留中のエクスポートがないことを確認します(Microsoft Graph コネクタのみ)。
10. 実行プロファイルに追加すると、Microsoft Graph コネクタを使用して MIM から Microsoft Entra ID に変更をエクスポートする実行がスケジュールされます。
11. Active Directory の他のユーザーへの変更をエクスポートしなくなった場合は、実行プロファイル スケジュールから AD 実行プロファイルへのエクスポートを削除します。

これらの手順が完了したら、MIM 同期ハイブリッド環境は、次の図に従う必要があります。 [Image: ユーザー SOA を使用する準備ができている環境の図のスクリーンショット。]

### SOA を使用するための一連の手順

環境がユーザー SOA の転送用に準備されると、SOA を転送するためのシーケンスは次のようになります。

1. 権限のソース (SOA) を Microsoft Entra ID に切り替えるユーザーやグループを特定します。 Microsoft Entra Connect Sync または Microsoft Entra Cloud Sync を使用して、これらのユーザーとグループが現在同期されていることを確認します。

    注

    ユーザーの SOA を切り替える前に、グループの SOA を切り替えます。
2. これらのユーザーを App-&gt; AD プロビジョニング構成 (たとえば、Workday から AD、MIM から AD など) から削除して、AD に同期しないようにします。
3. 同期サイクルが完了するまで待ち、オブジェクト データが AD と Microsoft Entra ID の間で同じであることを確認します。
4. AD でこれらのユーザーやグループに直接変更を加えるのをやめてください。
5. ユーザーやグループの SOA を切り替えます。
6. 次の手順に従って、ユーザーやグループをクラウドから管理できることを確認します。

    1. Microsoft Entra 管理センターに移動し、SOA を切り替えたユーザー/グループを見つけて、それらがクラウド オブジェクトであり、編集できるかどうかを確認します (または)
    2. このスクリプトを実行して、"DirSync" 属性と "isCloudManaged" 属性がクラウドに設定されているかどうかを確認します。
    3. 監査ログに一覧表示されているイベントを調べて、SOA の状態が変更されたかどうかを確認します。
7. Connect/Cloud Sync のスコープ内のユーザーやグループを引き続き保持します。これは、これらのオブジェクトに、AD で管理されているグループ、デバイス、連絡先への参照がある場合に必要です。
8. これらのユーザー変更が対応する人事システムから Microsoft Entra ID に直接プロビジョニングされるように、同期を停止したユーザーのプロビジョニングの方向を変更します。

    1. プロビジョニング API を使用して、同等のクラウド アプリ システムから Microsoft Entra ID に同期されなくなったユーザーをプロビジョニングするための新しいプロビジョニング構成を作成します。
    2. クラウド システム (HR または他のアプリ) から同じユーザーを Microsoft Entra に直接プロビジョニングします。
    3. この時点で、SOA 転送が完了し、ID がクラウド システムから Microsoft Entra ID に流れ始め、Microsoft Entra ID が機関のソースになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/prerequisites"} -->
## Active Directory と統合するための前提条件 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/prerequisites
- Service: entra-id / hybrid
- Article date: 2025-09-29
- Summary: この記事では、Active Directory との統合に必要な前提条件について説明します。

本ドキュメントでは、Active Directory と統合するための前提条件について説明します。

### クラウドの同期

#### ハードウェアとソフトウェア

| 要件 | 説明とその他の要件 |
| --- | --- |
| Windows Server 2022、Windows Server 2019、または Windows Server 2016 | • 4 GB 以上の RAM• .NET 4.7.1 ランタイム以上• ドメイン参加済み• PowerShell 実行ポリシーが **Undefined** または **RemoteSigned** に設定• TLS 1.2 が有効 |
| Active Directory | • フォレストの機能レベルが 2003 以上のオンプレミス AD |
| Microsoft Entra テナント | • オンプレミスからの同期に使用される Azure 内のテナント |

クラウド同期の前提条件の詳細については、[クラウド同期の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites)に関するページを参照してください。

#### アカウント

| 要件 | 説明とその他の要件 |
| --- | --- |
| ドメインまたはエンタープライズ管理者 | サーバーにエージェントをインストールし、gMSA サービス アカウントを作成するために必要です。 |
| ハイブリッド ID の管理者 | クラウド同期を構成するために必要です。このアカウントをゲスト アカウントにすることはできません。 |
| gMSA サービス アカウント | エージェントを実行するために必要です。 |

クラウド同期アカウントの詳細と、カスタム gMSA アカウントの設定方法については、[クラウド同期の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites)に関するページを参照してください。

### Microsoft Entra Connect

#### ハードウェアとソフトウェア

| 要件 | 説明とその他の要件 |
| --- | --- |
| Windows Server 2022、Windows Server 2019、および Windows Server 2016 | • 4 GB 以上の RAM• .NET 4.6.2 ランタイム以上• ドメイン参加済み• PowerShell 実行ポリシーが **RemoteSigned**• TLS 1.2 が有効• フェデレーションを使用している場合は、AD FS サーバーが Windows Server 2012 R2 以降である必要があり、TLS/SSL 証明書を構成する必要があります。 |
| Active Directory | • フォレスト機能レベルが 2003 以上のオンプレミス AD• 書き込み可能なドメイン コントローラー |
| Microsoft Entra テナント | • オンプレミスからの同期に使用される Azure 内のテナント |
| SQL Server | Microsoft Entra Connect には、ID データを格納するための SQL Server データベースが必要です。 既定では、SQL Server 2019 Express LocalDB (SQL Server Express の簡易バージョン) がインストールされます。 SQL サーバーの使用に関する詳細については、[Microsoft Entra Connect SQL サーバーの要件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#sql-server-used-by-azure-ad-connect)に関するページを参照してください |

クラウド同期の前提条件の詳細については、[Microsoft Entra Connect の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)に関するページを参照してください。

#### アカウント

| 要件 | 説明とその他の要件 |
| --- | --- |
| エンタープライズ管理者 | Microsoft Entra Connect をインストールする必要があります。 |
| ハイブリッド ID の管理者 | クラウド同期を構成するために必要です。このアカウントをゲスト アカウントにすることはできません。 このアカウントは学校または組織のアカウントである必要があり、Microsoft アカウントを使用することはできません。 |
| カスタム設定 | カスタム設定のインストール パスを使用する場合、さらにオプションがあります。 次の情報を指定できます。• [AD DS Connector アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions)• [ADSync サービス アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions) • [Microsoft Entra Connector アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions)。 詳細については、[カスタム インストールの設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions#custom-settings)に関するページを参照してください。 |

Microsoft Entra Connect アカウントの詳細については、「[Microsoft Entra Connect:アカウントとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/sso"} -->
## シングル サインオンの概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/sso
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、同期ツールでシングル サインオンを構成する方法について説明します。

シングル サインオンの設定は、使用している同期ツールとビジネス目標によって異なります。 次の表を使用して、ターゲットの目標に合った機能を判断します。

### クラウドの同期

Microsoft Entra Connect プロビジョニング エージェントをインストールしたら、クラウド同期のシングル サインオンを構成する必要があります。次の表に、シングル サインオンを使用するために必要な手順の一覧を示します。

| タスク | 説明 |
| --- | --- |
| Microsoft Entra Connect ファイルをダウンロードして抽出する | PowerShell モジュールを使用するために、Microsoft Entra Connect ファイルをダウンロードして抽出します。 |
| シームレス シングル サインオン PowerShell モジュールをインポートする | PowerShell モジュールを PowerShell セッションにインポートします。 |
| シームレス シングル サインオンが有効になっている Active Directory フォレストの一覧を取得します。 | シングル サインオンが有効になっている場所を決定します。 |
| 各 Active Directory フォレストのシームレス シングル サインオンを有効にする | フォレストでシングル サインオンを有効にします。 |
| テナントで機能を有効にする | テナントでシングル サインオンを有効にします。 |

詳細については、「[クラウド同期でシングル サインオンを使用する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-sso)」を参照してください。

### Microsoft Entra Connect

Microsoft Entra シームレス シングル サインオン (シームレス シングル サインオン) では、ユーザーが企業ネットワークに接続されている会社のデスクトップを使用しているときに、そのユーザーを自動でサインインさせます。 次の表は、シングル サインオンを使用するために必要な手順の一覧をまとめたものです。

| タスク | 説明 |
| --- | --- |
| 前提条件を確認する | 前提条件を確認し、シングル サインオンを有効にできることを確認します。 |
| 機能を有効にする | Microsoft Entra Connect ウィザードを使用して、シングル サインオンを有効にします。 |
| 機能をロールアウトする | シングル サインオンを段階的に実装します。 |
| シングル サインオンのテスト | シングル サインオンが機能していることを確認します。 |

詳細については、[Microsoft Entra Connect を使用したシングル サインオンの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start)に関するページと、「[クラウド同期を使用したシングル サインオンの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-sso)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/sync-tools"} -->
## 同期に使用されるツール - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/sync-tools
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、クラウド環境とオンプレミス環境の同期に使用できるさまざまなツールについて説明します。

以下の記事では、同期を行うために現在使用できる Microsoft ツールについて簡単に説明しています。

### ツールの一覧

- **クラウド同期とプロビジョニング エージェント** - Microsoft Entra クラウド同期は、ユーザー、グループ、連絡先を Microsoft Entra ID に同期するというハイブリッド ID の目標を実現するように設計された Microsoft の最新のオファリングです。 軽量のプロビジョニング エージェントを使用しており、ポータルから完全に構成できます。 詳細については、[クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)に関するページと[プロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-provisioning-agent)に関するページを参照してください。
- **Connect 同期** - Microsoft Entra Connect は、ハイブリッド ID の目標を達成するために設計されたオンプレミスの Microsoft アプリケーションです。 詳細については、「[Microsoft Sentinel Connect とは?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2)」を参照してください。
- **Graph コネクタを使用した Microsoft Identity Manager** - Active Directory や Microsoft Entra ID などのディレクトリのためのハイブリッド ID 環境を実現する高度なディレクトリ間プロビジョニングを提供する、Microsoft のオンプレミス ID およびアクセス管理ソリューションです。 詳細については、[Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016) に関するページを参照してください。 MIM は徐々に非推奨になるため、高度なシナリオでのみ使用するようにしてください。 詳細については、「[非推奨の機能と将来の計画](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016-deprecated-features)」を参照してください。
- **ECMA ホスト コネクタ** - ECMA ホストはプロビジョニング エージェントと連携して、クラウドから SQL や LDAP などのオンプレミス アプリケーションにユーザーをプロビジョニングおよび同期します。 詳細については、「[Microsoft Entra オンプレミス アプリケーション ID プロビジョニングのアーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)」と「[プロビジョニング エージェントとは?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-provisioning-agent)」に関するページを参照してください。

### 適切なツールを選択する

これらのツールを使用することで得られる結果は、それぞれ似通っています。 そのため、適切なツールを選択することが不可欠です。 ほとんどのシナリオでは、クラウド同期が推奨されるツールになります。 その後、同期を接続し、高度で複雑なシナリオには MIM を使用します。 オンプレミスアプリケーションに適しているツールは、ECMA ホストになります。 詳細については、[サポートされている同期シナリオの表を参照](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios#supported-sync-scenarios)してください。 適切なツールを特定するには、[適切な同期ツールの選択](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)サイトにあるウィザードを使用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/troubleshoot-connect-sync-application-authentication"} -->
## Microsoft Entra Connect Sync アプリケーション ベースの認証のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/troubleshoot-connect-sync-application-authentication
- Service: entra-id / hybrid
- Article date: 2025-01-15
- Summary: Microsoft Entra Connect Sync のアプリケーション ベース認証 (ABA) に関する既知の問題 (不足している接続パラメーターや、同じコネクタ アカウントを使用する複数のサーバーなど) をトラブルシューティングする方法について説明します。

Microsoft Entra Connect Sync のアプリケーション ベース認証 (ABA) では、ユーザー名とパスワードの代わりにアプリケーション ID (証明書を含むサービス プリンシパル) を使用して、Microsoft Entra ID に対する認証を行います。 この方法では、同期サーバーに管理者資格情報を格納する必要がなくなり、セキュリティが向上します。 この記事は、Microsoft Entra Connect Sync の ABA に関する既知の問題のトラブルシューティングに役立ち、それらを解決する手順について説明します。

Important

最近のバージョンの Microsoft Entra Connect では、いくつかのアプリケーション ベース認証 (ABA) の問題が解決されています。 これらの修正プログラムの恩恵を受け、既知の問題を防ぐために、最新バージョンに更新することをお勧めします。

### 既知の問題

**自動アップグレード後に接続パラメーターが見つからない** – ABA 対応の Microsoft Entra Connect バージョンへの自動アップグレードの後、Microsoft Entra (Azure AD) コネクタの接続フィールド (アプリケーションや証明書など) が Synchronization Service Manager UI に空白で表示されます。 コネクタのプロパティで **[OK] を** 選択すると、構成ウィザードで **ApplicationManagedBy** にエラーが設定されず、次の証明書の自動ロールオーバーが防止されます。

**同じコネクタ アカウントを使用する複数のサーバー** - ABA は、サーバーごとに 1 セットのサービス プリンシパルまたは証明書を操作するように設計されています。 2 つの Microsoft Entra Connect Sync サーバーが同じカスタム Microsoft Entra (Azure AD) コネクタ アカウント (Microsoft Entra ID のオンプレミス サービス アカウント) を共有している場合、ABA の自動セットアップによって 1 つの共有アプリケーション登録が提供され、サーバーは同じ Microsoft Entra ID アプリまたはサービス プリンシパルを使用します。

あるサーバーがそのアプリの証明書を更新すると、もう一方のサーバーの認証が中断され、2 番目のサーバーで同期エラーが発生します。 この問題は、Microsoft Entra Connect Sync サービスのインストール後にサーバーが複製された場合、既定の Microsoft Entra (Azure AD) コネクタ アカウント (つまり、 `Sync_SERVERNAME_############@contoso.onmicrosoft.com`) でも発生する可能性があります。

### アップグレード後に Microsoft Entra Connector の接続パラメーターが見つからない

#### 症状

Microsoft Entra Connect サーバーがバージョン 2.5.x に自動更新され、アプリケーション ベースの認証に自動的に切り替わると、Synchronization Service Manager の Microsoft Entra (Azure AD) コネクタには、新しい接続パラメーターの値、 `ApplicationManagedBy`、 `CertificateManagedBy`、および `CertificateId` のフィールドは空白で表示されません。 コネクタのプロパティを開いて [ **OK] を** 選択すると (何も変更しなくても)、コネクタの構成が保存され、それらの ABA パラメーター定義がクリアされます。

その結果、Microsoft Entra Connect 構成ウィザードを実行すると、 **ApplicationManagedBy が設定されていません**というエラーで失敗します。 同期は、有効期限が切れるまで現在の証明書を使用して動作し続けますが、構成が不完全であるため、証明書の自動ロールオーバーが失敗し、次回の更新時に Microsoft Entra ID への同期が中断されます。

#### 原因

Synchronization Service Manager UI は、新しい ABA フィールドを処理するように更新されません。このレガシ UI でコネクタを開いて保存すると、アプリケーション ベースの認証設定が削除されます。 この問題は、自動アップグレード後に ABA に自動登録されたインスタンスでのみ発生します。 ウィザードまたは新規インストールで ABA を手動で有効にした場合、この問題は発生しません。

#### 解決策

PowerShell 修復関数を使用して、Microsoft Entra Connector 構成で不足しているパラメーターを復元します。 影響を受けるサーバーで次の手順を実行します。

1. **管理者として実行**を使用して新しい PowerShell セッションを開始します。
2. ADSyncTools モジュールを使用するには、次のように PowerShell ギャラリーからインストールまたは更新する必要があります。

    注

    ADSyncTools のこの関数に必要な最小バージョンは 2.3.0 です。

    ```powershell
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    Install-Module ADSyncTools # If ADSyncTools isn't installed, or;
    Update-Module ADSyncTools # If ADSyncTools is already installed
    ```
3. ADSyncTools モジュールのインポート:

    ```powershell
    Import-Module ADSyncTools
    ```
4. 修復関数を実行します。

    ```powershell
    Repair-ADSyncToolsEntraAppParameters
    ```

このプロセスにより、不足しているパラメーターが復元されます。 また、構成ウィザードをエラーなしで実行することもできます。 同期サービスではアプリケーション ベースの認証が使用され、今後の証明書のロールオーバーは成功します。

Important

ABA が有効になっている場合は、Synchronization Service Manager UI を使用して Microsoft Entra (Azure AD) コネクタのプロパティを表示または編集しないでください。 [ **OK]** を変更せずに選択しても、必要な設定が削除される可能性があります。 コネクタの構成変更には、常に Microsoft Entra Connect ウィザード (または PowerShell コマンド) を使用します。

### 同じカスタム Microsoft Entra Connector アカウントを共有する複数のサーバー

#### 症状

これらのサーバーが同じ Microsoft Entra ID コネクタ アカウント (つまり、レガシ認証に同じ Microsoft Entra サービス アカウント資格情報) を使用するように構成されている場合は、ABA を有効にした後に次の認証エラーが発生する可能性があります。2 つの Microsoft Entra Connect Sync サーバー 、1 つのアクティブ なサーバー、1 つのステージング サーバーをバックアップ用に展開することを検討してください。

```
Log Name:      Application
Source:        Directory Synchronization
Date:          12/01/2025 0:00:00 AM
Event ID:      906
Task Category: None
Level:         Error
Keywords:      Classic
User:          N/A
Computer:      EntraConnect.Contoso.com
Description:
[MSAL] AcquireTokenWithCertificate: Exception caught while acquiring token via certificate. [invalid_client] - A configuration issue is preventing authentication - check the error message from the server for details. You can modify the configuration in the application registration portal. See https://aka.ms/msal-net-invalid-client for details.  Original exception: AADSTS700027: The certificate with identifier used to sign the client assertion is not registered on application. [Reason - The key was not found., Please visit the Azure Portal, Graph Explorer or directly use MS Graph to see configured keys for app Id '<guid>'. Review the documentation at https://docs.microsoft.com/en-us/graph/deployments to determine the corresponding service endpoint and https://docs.microsoft.com/en-us/graph/api/application-get?view=graph-rest-1.0&tabs=http to build a query request URL, such as 'https://graph.microsoft.com/beta/applications/<guid>']. Trace ID: <guid> Correlation ID: <guid> Timestamp: <datetime>
```

通常、2 番目のサーバー (または最後に ABA に切り替えたサーバー) は正常に動作し、最初に ABA が構成されていたサーバーは、このような証明書エラーで Microsoft Entra ID への接続に失敗し始めます。 (たとえば、ABA セットアップを手動で再実行して) 最初のサーバーを修正しようとすると、2 番目のサーバーで認証が中断されます。

#### 原因

設計上、1 つの Microsoft Entra ID アプリケーションで一度に認証できるサーバーは 1 つだけです。 ABA の自動登録では、コネクタのサービス アカウントを使用してアプリケーションの登録を識別します。 2 つの異なるサーバーが同じアカウントを共有している場合、システムは誤って両方に 1 つのアプリケーション ID を使用します。 どちらのサーバーも、最終的に同じアプリケーションとサービス プリンシパルで構成されます。 これはサポートされていません。各 Microsoft Entra Connect インスタンスには一意のアプリケーションが必要です。

Microsoft Entra Connect がインストールされているサーバーが別の運用サーバーに複製されている場合も同じ問題が発生します。これは、同じコンピューター識別子を共有するこれらのサーバーとしてこの製品を展開する方法としてはサポートされていません。 つまり、サーバーの ID は 1 つのアプリ登録に関連付けられているため、競合します。 Microsoft Entra Connect ウィザードでは、既定でサーバーごとに一意のアカウントが使用されます。これは、この問題を回避するために、Microsoft Entra コネクタのサービス アカウントではなく、サーバーの名前を使用してアプリケーションの登録を識別するためです。

注

この問題を回避するには、各 Microsoft Entra Connect インスタンスが一意のコネクタ アカウントと一意のマシン識別子を使用していることを確認します。 同じ Microsoft Entra (Azure AD) コネクタ アカウントを使用する複数の同期サーバー (ステージング モードなど) がある場合は、各サーバーで文書化されている解決手順に従って、各インスタンスが独自のアプリケーション登録を取得できるようにします。

Warnung

Microsoft Entra (Azure AD) コネクタ アカウントとして **グローバル管理者** アカウントを使用しないでください。 既定で構成されている Microsoft Entra サービス アカウントには、同期中に必要なものに対するアクセス許可が制限されているのに対し、管理者アカウントにはクラウドでの無制限の特権があります。 グローバル管理者アカウントで構成されたオンプレミスの Microsoft Entra Connect サーバーが侵害された場合、Microsoft Entra テナント全体が危険にさらされます。

#### 解決策

各 Microsoft Entra Connect サーバーに独自のアプリケーション ID を付与します。 これを行うには、各サーバーをレガシ認証に戻してから ABA 構成を実行して、各サーバーが独自のアプリ登録を作成するように、各サーバーを個別に再構成する必要があります。 このシナリオでは、ABA で構成された 2 つのサーバーがあります。ServerA は正常に実行されており、ServerB は壊れた状態です。 各サーバーで次の手順を実行します。作業サーバーから始めて、破損した状態のサーバーに移動します。

1. **ServerA で、同期スケジューラを一時的に一時停止**します。Microsoft Entra Connect サーバーで管理者として PowerShell を開き、次のコマンドを実行します。

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $false
    ```
2. **ServerA をレガシ認証に戻す**: PowerShell ウィンドウで、次のコマンドを実行して、サービス アカウントを Microsoft Entra (Azure AD) コネクタに割り当てます。 資格情報の入力を求められます。 Microsoft Entra ID でグローバル管理者またはハイブリッド ID 管理者のユーザー名とパスワードを指定します。 次のコマンドレットは、(アプリケーション ID を置き換える) `Sync_<Servername>_<SyncMachineIdentifier>` という同期にアカウントを使用するように Microsoft Entra (Azure AD) コネクタを構成します。

    ```powershell
    $cred = Get-Credential
    $connAccountName = "Sync_$($env:COMPUTERNAME)_$((Get-ItemProperty 'HKLM:\SOFTWARE\Microsoft\Azure AD Connect').SyncMachineIdentifier.Substring(0, 12))"
    Add-ADSyncAADServiceAccount -AADCredential $cred -Name $connAccountName
    ```
3. **レガシ認証が使用中であることを確認する (省略可能):**コマンド `Get-ADSyncEntraConnectorCredential`を実行して、コネクタがサービス アカウントを使用して戻ってきたかどうかを確認できます。 出力で、 `ConnectorIdentityType` が ServiceAccount (アプリケーションではない) かどうかを確認します。 つまり、コネクタは提供されたアカウント資格情報を使用しています。
4. **ServerA マシン識別子の確認**: ServerB に切り替える前に、ServerA から現在のマシン識別子を書き留めます。

    ```powershell
    Get-ItemProperty -Path "HKLM:\SOFTWARE\Microsoft\Azure AD Connect" | Select-Object SyncMachineIdentifier
    ```
5. **ServerB に切り替えて ABA 証明書をローテーション**する: ServerB の Microsoft Entra ID 認証 (破損した状態) を修正するには、ServerB で Microsoft Entra Connect ウィザードを実行し、[ **アプリケーション証明書のローテーション**] を選択してから、ABA を回復するためにすべての手順を完了します。
6. **同期スケジューラ (ServerB) を一時的に一時停止**する: Microsoft Entra Connect サーバーで管理者として PowerShell を開き、次を実行します。

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $false
    ```
7. ServerB マシン識別子の確認: ServerB から現在のマシン識別子を取得し、ServerA の値と比較します。

    ```powershell
    Get-ItemProperty -Path "HKLM:\SOFTWARE\Microsoft\Azure AD Connect" | Select-Object SyncMachineIdentifier
    ```
8. **新しいマシン識別子を生成**する: 両方のサーバーに同じマシン識別子がある場合は、次のコマンドを実行して新しい ID を生成します。

    ```powershell
    # 1. Stop ADSync service
    Stop-Service ADSync
    
    # 2. Generate and set new identifier
    $newId = [Guid]::NewGuid().ToString("N")
    Set-ItemProperty -Path "HKLM:\SOFTWARE\Microsoft\Azure AD Connect" -Name SyncMachineIdentifier -Value $newId
    
    # 3. Confirm new identifier
    Get-ItemProperty -Path "HKLM:\SOFTWARE\Microsoft\Azure AD Connect" | Select-Object SyncMachineIdentifier
    
    # 4. Restart ADSync service
    Start-Service ADSync
    ```
9. **ServerB をレガシ認証に戻す**: PowerShell ウィンドウで、次のコマンドを実行して、サービス アカウントを Microsoft Entra (Azure AD) コネクタに割り当てます。 資格情報の入力を求められます。 Microsoft Entra ID でグローバル管理者またはハイブリッド ID 管理者のユーザー名とパスワードを指定します。 次のコマンドレットは、(アプリケーション ID を置き換える) `Sync_<Servername>_<SyncMachineIdentifier>` という同期にアカウントを使用するように Microsoft Entra (Azure AD) コネクタを構成します。

    ```powershell
    $cred = Get-Credential
    $connAccountName = "Sync_$($env:COMPUTERNAME)_$((Get-ItemProperty 'HKLM:\SOFTWARE\Microsoft\Azure AD Connect').SyncMachineIdentifier.Substring(0, 12))"
    Add-ADSyncAADServiceAccount -AADCredential $cred -Name $connAccountName
    ```
10. **Microsoft Entra ID からアプリを削除**する: [Microsoft Entra 管理センター](https://entra.microsoft.com) に移動し **、[アプリの登録**&gt;**すべてのアプリケーション**] に移動し、証明書の競合が発生した 1 つ以上の以前のアプリ (つまり、 `ConnectSyncProvisioning_*`) を削除します。
11. **ServerB での ABA の再構成: ServerB** で Microsoft Entra Connect ウィザードを起動し、[アプリケーション ベースの認証の構成] を選択して、このサーバーで ABA を有効にするセットアップ手順を実行します。 これにより、ServerB 専用の新しい Microsoft Entra アプリケーションが登録されます。
12. **同期スケジューラを再開**する: 次のコマンドを実行して同期を再度有効にし、通常の同期が再開されたことを確認します。

    ```powershell
    Set-ADSyncScheduler -SyncCycleEnabled $true
    ```
13. **ServerA に切り替える: ServerA** で手順 11 と手順 12 を繰り返します。

これらの手順を完了すると、各 Microsoft Entra Connect サーバーは、Microsoft Entra ID の独自のアプリケーション ID にリンクされます。 それらは互いに競合しなくなります。 どちらのサーバーも、認証エラーなしで同時に同期を実行できる必要があります。 Microsoft Entra 管理センターの **[アプリの登録** ] で、名前が `ConnectSyncProvisioning_<Servername>_<SyncMachineIdentifier>`された 2 つの異なるアプリ エントリ (サーバーごとに 1 つ) があることを確認できます。

このガイドで説明されている問題に対処し、アップグレード後に不足している接続パラメーターを復元し、複数のサーバーのアプリケーション ID を分離することで、アプリケーション ベースの認証を使用して、正常でより安全な Microsoft Entra Connect 環境を維持できます。

構成の変更には、Microsoft Entra Connect ウィザードまたはドキュメントに記載されている PowerShell コマンドを常に使用してください。 ABA の設定と管理の詳細については、「 [アプリケーション ID を使用した Microsoft Entra ID に対する認証」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/authenticate-application-id)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/user-source-of-authority-audit-monitor"} -->
## Microsoft Entra ID でユーザーの権限ソース (SOA) を監査および監視する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-audit-monitor
- Service: entra-id / hybrid
- Article date: 2025-09-16
- Summary: Microsoft Entra ID でユーザーの権限ソース (SOA) を監査および監視する方法について説明します。

管理者は、Azure portal または onPremisesSyncBehavior Microsoft Graph API の **監査ログ** を使用して、環境内の SOA の変更を監視および報告できます。 また、SOA の変更をサードパーティの監視システムと統合することもできます。 詳細については、 [onPremisesSyncBehavior を](https://learn.microsoft.com/ja-jp/graph/api/resources/onpremisessyncbehavior)参照してください。

### 監査ログを使用して SOA の変更を表示する方法

監査ログには、Azure portal でアクセスできます。 過去 30 日間の SOA 変更の記録が保持されます。

1. 少なくとも[レポート閲覧者](https://portal.azure.com)として [Azure portal](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) にサインインします。
2. [ **Microsoft Entra ID の管理**&gt;**監視**&gt;**監査ログ** を選択するか、検索バーで **監査ログ** を検索します。
3. **AD DS からクラウドへの機関の変更ソース**としてアクティビティを選択します。 [Image: ユーザーソースの機関監査ログのスクリーンショット。]
4. また、ユーザーの権限ソースがクラウドから元に戻されたときのアクティビティを確認することもできます。 [Image: 権限のソースが元に戻されたときの監査ログのスクリーンショット。]
5. 次の API 呼び出しを行うことで、SOA を元に戻したすべてのユーザーの完全な一覧を取得することもできます。

    ```https
    GET https://graph.microsoft.com/v1.0/users?$count=true&$filter=OnPremisesSyncEnabled ne true and OnPremisesImmutableId ne null
    ```

### Azure Monitor を使用して Log Analytics でワークブックとレポートを作成する方法

監査ログを Azure Monitoring と統合し、次のイベントを検索して SOA 操作を取得できます。

- オブジェクトの SOA がクラウドで管理されているため、オブジェクトがクラウドに同期されていない場合、イベント ID 6956 がログに記録されます。
- SOA 転送がオンプレミスにロールバックされると、AD DS へのユーザー プロビジョニングは、AD DS ユーザーを削除せずに変更の同期を停止します。 AD DS ユーザーも構成スコープから削除されます。 AD DS ユーザーはそのまま残り、AD DS は次の同期サイクルで制御を再開します。 このオブジェクトはオンプレミスで管理されているため、同期が行われないことを **監査ログ** で確認できます。

カスタム クエリを作成する方法の詳細については、「 [プロビジョニングと Azure Monitor ログの統合方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/user-source-of-authority-guidance"} -->
## Microsoft Entra ID でユーザーの権限の情報源 (SOA) を使用するためのガイダンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-guidance
- Service: entra-id / hybrid-cloud-sync
- Article date: 2025-09-30
- Summary: Microsoft Entra ID のユーザーソース (SOA) を使用してユーザー管理を効率化します。 AD フットプリントを最小限に抑え、クラウドへのスムーズな移行を保証します。

ユーザー ID を効果的に管理することは、クラウドに移行する組織にとって重要です。 この記事では、ユーザーの権限ソース (SOA) を使用して、ユーザーをオンプレミスからクラウドに移行するのに役立つガイダンスを提供します。 SOA 転送後に Active Directory (AD) のフットプリントを最小限に抑え、ユーザー管理を移行するためのベスト プラクティスを採用し、ユーザー管理をクラウドにスムーズに移行する方法についてもこの記事で説明します。

### Active Directory ユーザー管理

ユーザーの SOA を転送するための主要なシナリオの 1 つは、AD フットプリントを最小限に抑える機能です。 SOA 転送を完了すると、Active Directory 固有のリソースへのアクセスを必要としないユーザーは、AD 内で無効にすることも、完全に削除することもできます。

注

SOA 転送後もユーザーがオンプレミス リソースへのアクセスを必要とする場合は、Microsoft Graph を使用してこれらの属性を手動で管理する必要があります。 これらの属性の詳細については、「[SOA 転送されたユーザーのオンプレミス属性をクリア](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/how-to-user-source-of-authority-configure#clear-on-premises-attributes-for-soa-transferred-users)する」を参照してください。

### ベスト プラクティス

次のベスト プラクティスに従って、ユーザー管理をオンプレミスから Microsoft Entra ID に移行します

#### ユーザーを OU に移動する

Active Directory ユーザーとコンピューターなどの Active Directory 管理ツールや PowerShell 用 Active Directory モジュールを使用して、変更された Source of Authority (SOA) で AD オブジェクトを変更すると、Microsoft Entra 表現に不整合が発生する可能性があります。 SOA の変更を実行する前に、組織はそれらのオブジェクトを指定された AD OU に移動する必要があります。この OU は、それらのオブジェクトを AD ツールを使用して管理されなくなったことを示します。 転送する SOA のユーザーがオンプレミスのマネージド グループで参照されている場合、ユーザーは同期スコープに残る必要があります。 オンプレミスのユーザーを削除すると、オンプレミスと Microsoft Entra の両方のグループからも削除されます。

#### ユーザー管理の移行

ユーザーの SOA を移行する前に、ユーザーの同期サイクルが完了していることを確認します。 完了したら、HR から AD、または MIM から AD への構成のスコープからユーザーを削除し、HR-&gt;Microsoft Entra ID 構成に追加します。 組織で Active Directory 管理エージェント (AD MA) と共に Microsoft Identity Manager (MIM) を使用して AD ユーザーとグループを管理する場合は、同期ロジックを更新して、SOA を変更する前に、AD MA 経由でそれらのオブジェクトへの変更のエクスポートを停止する必要があります。 AD MA を使用する代わりに、MIM が Microsoft [Graph 用 MIM コネクタ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016-connector-graph) を使用して Microsoft Entra のオブジェクトを更新し、MIM によって行われた変更が最初に Microsoft Entra に送信され、必要に応じて Active Directory に送信されるようにすることができます。 詳細については、「 [MIM セットアップを準備する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/prepare-user-source-of-authority-environment#prepare-your-mim-setup)。 AD でユーザーに直接変更を加えるのをやめます。 SOA が完了したら、クラウド内のユーザーの管理を開始します。

#### LDAP アプリケーションを使用しているユーザー

オンプレミスの LDAP アプリケーションを使用するユーザーをクラウドに移行する場合は、ユーザーの SOA を転送する前に [、Microsoft Entra Domain サービス](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview) を使用して LDAP アプリケーションをクラウドに移行します。

#### サード パーティのフェデレーション認証

組織がサード パーティのフェデレーション認証 ID プロバイダーを使用していて、ユーザーの SOA を転送する予定の場合は、Active Directory アカウントを手動で管理し、サード パーティの同期ツールを使用してパスワードを維持する必要があります。 ユーザーが [Active Directory フェデレーション サービス](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/ad-fs-overview)を使用してフェデレーション認証を使用している場合、SOA の転送はサポートされていません。

#### デバイス

ユーザーの SOA 機能を完全に使用するには、お客様がデバイスをクラウドに移行し、Microsoft Entra Joined Device セットアップを使用することをお勧めします。 グループの場合、デバイスに関する前提条件はありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/user-source-of-authority-overview"} -->
## ユーザー SOA を Microsoft Entra ID に転送する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview
- Service: entra-id / hybrid-cloud-sync
- Article date: 2026-08-11
- Summary: ユーザーの機関ソース (SOA) をMicrosoft Entra IDに転送し、AD DS フットプリントを最小限に抑え、クラウドからの完全なユーザー ライフサイクルを管理する方法について説明します。

組織では、ID およびアクセス管理 (IAM) ソリューションを最新化するためのクラウドファーストのアプローチがますます採用されています。 クラウド イニシアチブへの移行に向けて、Microsoft は、顧客のビジネス目標に合わせて [5 つの変革の状態をモデル化しました](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-posture#five-states-of-transformation) 。 オンプレミスの Active Directory Domain Services (AD DS) からクラウドへのユーザーの機関ソース (SOA) の移行は、この体験の重要な手順です。 このプロセスは AD DS 最小化と呼ばれ、クラウドでユーザーを直接管理することで、オンプレミス インフラストラクチャの複雑さを軽減します。

この記事では、ユーザー SOA の概念、その利点、およびユーザーがサポートするシナリオについて説明します。 また、Microsoft Entra ID を使用してユーザー管理をクラウドに移行することを計画している IT 管理者にとって重要な考慮事項と前提条件についても説明します。 組織では、ユーザー SOA を使用して、Microsoft Entra ID ガバナンスを使用してクラウド内のユーザー ライフサイクルを管理できます。 オンプレミスリソースへのアクセスが必要なユーザーの場合、[Microsoft Entra IDからActive Directoryへのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory)では、Microsoft Entra IDが機関のソースのままである間、必要な AD アカウントを維持できます。 IT アーキテクト向けのユーザー SOA の使用に関するガイダンスについては、[クラウドファーストの ID ガイダンスMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/guidance-it-architects-source-of-authority)参照してください。

### ビデオ: Microsoft Entra ユーザー の信任ソース

SOA の概要と、それがクラウドへの移行にどのように役立つかについては、このビデオをご覧ください。

### ユーザー SOA シナリオ

次のセクションでは、ユーザー SOA がサポートするシナリオの詳細について説明します。

#### Microsoft Entra ID ガバナンスを使用した AD ユーザーの最小化とユーザー ライフサイクルの管理

**シナリオ**: 一部またはすべてのアプリケーションを最新化し、アクセスに AD DS ユーザーを使用する必要がなくなりました。 たとえば、これらのアプリケーションでは、AD FS などのフェデレーション システムの代わりに、Microsoft Entra ID の [Security Assertion Markup Language (SAML)](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization) または [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) でユーザー要求が使用されるようになりました。 ただし、これらのアプリは引き続き既存の同期ユーザーに依存してアクセスを管理します。 ユーザー SOA を実装することで、クラウド内のユーザーを編集し、AD DS ユーザーを完全に削除し、Microsoft Entra ID ガバナンス機能を使用してユーザーを管理できます。

[Image: ユーザー SOA を転送し、Microsoft Entra ID ガバナンスを介してユーザーを管理した後の AD DS ユーザーの削除を示す図。]

#### SOA で転送されたユーザーのパスワードレス認証

**シナリオ**: ユーザーの SOA を転送し、オンプレミスリソースとクラウド リソースの両方にアクセスするように設定しました。 オンプレミスからユーザーを削除する代わりに、Cloud Kerberos Trust パスワードレス認証を使用して、ハイブリッド プレゼンスとオンプレミス リソースへのアクセスを維持します。

[Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/configure)や [FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)などのパスワードレス認証方法を使用すると、これらのユーザーはオンプレミスとクラウドのリソースにアクセスできます。 たとえば、ユーザーはMicrosoft Entra Private Access[を介](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-private-access)して[Azure Files](https://learn.microsoft.com/ja-jp/azure/storage/files/storage-files-introduction)にアクセスできます。 これらの方法では、オンプレミス リソースの多要素認証と条件付きアクセス ポリシーも有効になり、制御とセキュリティが強化されます。 **このシナリオを機能させるには、ユーザー アカウントが Active Directory に残っている必要があります**。

[Image: SOA が Microsoft Entra ID で管理されているユーザーのパスワードレス認証を示す図。]

#### Kerberos アプリのアクセスとライフサイクル ガバナンスのActive Directoryにユーザーをプロビジョニングする

**シナリオ**: ユーザーの SOA をMicrosoft Entra IDに転送した場合でも、クラウドからライフサイクルを管理しながら、Kerberos 認証に依存するオンプレミス アプリケーションにアクセスする必要があります。

[Microsoft Entra IDからActive Directoryへのプロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory)構成します。 Microsoft Entra Cloud Sync は、クラウド管理ユーザーを AD にプロビジョニングし直します。そのため、Kerberos ベースのシングル サインオン用に AD アカウントが存在します。 そのアカウントでは、Windows Hello for Businessや Cloud Kerberos Trust などのパスワードレス認証がサポートされます。 Microsoft Entra IDは引き続き権限のソースであり、属性の変更は自動的に AD に送ります。 Cloud Sync が AD アカウントを維持している間は、Microsoft Entra ID ガバナンスを通じてユーザー のライフサイクルを管理できます。

[Image: Kerberos アプリケーションへのアクセスとライフサイクル ガバナンスのために、SOA で変換されたユーザーをActive Directoryにプロビジョニングする方法を示す図。]

### ユーザー SOA を転送するための前提条件

組織内のユーザーの SOA の転送を開始する前に、環境が次の前提条件を満たしている必要があります。

- クラウド人事システムが構成され、Microsoft Entra ID と正常に統合されました。 人事システムからプロビジョニングされたユーザーに対する変更は、直接 Microsoft Entra ID に行く必要があります。 詳細については、 [HR システムからのプロビジョニングでのユーザーの構成のシフトを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/prepare-user-source-of-authority-environment#shift-the-configuration-of-users-in-provisioning-from-the-hr-system)参照してください。
- オンプレミスの Exchange ワークロードはありません。 現在オンプレミスの Exchange サーバーを使用している場合は、ユーザーとメールボックスをクラウドに移行してから、オンプレミスの Exchange を削除します。 詳細については、「 [Microsoft Exchange のセットアップを準備する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/prepare-user-source-of-authority-environment#prepare-your-microsoft-exchange-setup)。
- すべての認証方法は、クラウド ユーザーに対して機能します。 Kerberos ベースのアプリケーションの場合は、パスワードレス認証 (Cloud Kerberos Trust でのWindows Hello for Businessなど) を使用します。 [Active Directory へのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory)によって作成された AD アカウントにより、Kerberos のシングルサインオンが有効になります。
- パスワードレス認証には [、Cloud Kerberos Trust の種類](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/hybrid-cloud-kerberos-trust) を使用する必要があります。
- SOA 転送を目的としたユーザーは、LDAP バインドやパスワードを使用した Kerberos など、パスワードベースの認証を必要とするアプリケーションに関連付けることはできません。
- ユーザーが [Active Directory フェデレーション サービス (AD FS) (AD FS)](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/ad-fs-overview) を介してフェデレーション認証を使用する場合、SOA の転送はサポートされません。
- 組織でサード パーティのフェデレーション ID プロバイダーを使用している場合は、Active Directory アカウントを手動で管理し、サード パーティの同期ツールを使用してパスワードを維持する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/verify-sync-tool-version"} -->
## クラウド同期または接続同期のバージョンを確認する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/verify-sync-tool-version
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: この記事では、プロビジョニング エージェントまたは接続同期のバージョンを確認する手順について説明します。

この記事では、インストールされているプロビジョニング エージェントと接続同期のバージョンを確認する手順について説明します。

### プロビジョニング エージェントを確認する

使用しているプロビジョニング エージェントのバージョンを確認するには、次の手順に従います。

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

### 接続同期を確認する

使用している接続同期のバージョンを確認するには、次の手順に従います。

#### ローカル サーバーの場合

エージェントが実行されていることを確認するには、次の手順に従います。

1. 管理者アカウントでサーバーにサインインします。
2. **[サービス]** を開きます。これには、そこに直接移動するか、スタート ボタンをクリックし、[ファイル名を指定して実行] で「Services.msc」と入力します。
3. **[サービス]** で、**[Microsoft Entra ID Sync]** が存在し、その状態が **［実行中］** であることを確認します。

#### 接続同期のバージョンを確認する

実行中のエージェントのバージョンを確認するには、以下の手順に従います。

1. 'C:\Program Files\Microsoft Azure AD Connect' に移動します。
2. **[AzureADConnect.exe]** を右クリックし、**[プロパティ]** を選択します。
3. **[詳細]** タブをクリックし、[製品バージョン] の横にあるバージョン番号 ID をクリックします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/what-is-inter-directory-provisioning"} -->
## Microsoft Entra ID を使用したディレクトリ間プロビジョニングとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-inter-directory-provisioning
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: ID のディレクトリ間のプロビジョニングについて概説します。

ディレクトリは共有情報のインフラストラクチャであり、ネットワーク リソースや項目を検索、管理、整理する目的で使用されます。 ディレクトリ サービスを使用するアプリケーションの例として、Microsoft Active Directory と Microsoft Entra ID があります。 だれが何にアクセスできるかや、特定のリソースをだれが使用できるかといったディレクトリ システムの決定は、ID があることで可能となります。

ディレクトリ間のプロビジョニングは、2 つの異なるディレクトリ サービス システム間で ID をプロビジョニングすることです。 ディレクトリ間のプロビジョニングの最も一般的なシナリオは、Active Directory に既に存在するユーザーを Microsoft Entra ID にプロビジョニングすることです。 このプロビジョニングは、Microsoft Entra Connect Sync や Microsoft Entra Connect クラウド プロビジョニングなどのエージェントによって実現できます。

ディレクトリ間のプロビジョニングを使用すると、[ハイブリッド ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) 環境を作成することができます。

### Microsoft Entra ID でサポートされるディレクトリ間のプロビジョニングの種類

ディレクトリ間のプロビジョニングを行う手段として、Microsoft Entra ID では現在 3 つの方法がサポートされています。 それらの方法を次に示します。

- [Microsoft Entra クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync) - ハイブリッド ID の目標を達成するように設計された新しい Microsoft エージェントです。 Active Directory と Microsoft Entra ID との間でディレクトリ間のプロビジョニングを実現する軽量の環境となっており、これはポータルで構成できます。
- [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) - ハイブリッド ID に対応し、それを達成するように設計された Microsoft のツールです。たとえば、Active Directory から Microsoft Entra ID へのディレクトリ間のプロビジョニングに対応します。
- [Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016) - ID とアクセス管理を意図した Microsoft のオンプレミス ソリューションです。組織内のユーザー、資格情報、ポリシー、アクセスを効率的に管理できます。 また、MIM では Active Directory や Microsoft Entra ID などのディレクトリのためのハイブリッド ID 環境を実現する高度なディレクトリ間のプロビジョニングを利用できます。

#### 主な利点

ここで説明するディレクトリ間のプロビジョニング機能は、次に挙げるような大きなメリットをビジネスにもたらします。

- [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) - ユーザーのオンプレミス AD パスワードのハッシュを Microsoft Entra ID と同期させるサインイン方法。
- [パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) - ユーザーがオンプレミスとクラウド内で同じパスワードを使用できるようにするサインイン方法ですが、フェデレーション環境の追加のインフラストラクチャは必要ありません。
- [フェデレーション統合](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-whatis) - オンプレミスの AD FS インフラストラクチャを使ってハイブリッド環境を構成するために使用できます。 証明書の更新や追加の AD FS サーバー デプロイなどの AD FS 管理機能も提供されます。
- [同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) - ユーザー、グループ、およびその他のオブジェクトを作成する役割を果たします。 また、オンプレミスのユーザーやグループの ID 情報をクラウド側と一致させる役割もあります。 この同期にはパスワード ハッシュも含まれます。
- [稼働状況の監視](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) - 堅牢な監視機能を提供し、このアクティビティを表示するための [Microsoft Entra 管理センター](https://entra.microsoft.com)内の一元的な場所を提供することができます。

#### 一般的なシナリオ

ハイブリッド同期シナリオの一覧については、「[一般的なシナリオ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/what-is-provisioning"} -->
## Microsoft Entra ID を使った ID プロビジョニングとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: ID プロビジョニングについて概説します。

今日、ビジネスや企業ではますます、オンプレミスのアプリケーションとクラウド アプリケーションが混在して使用されるようになっています。 ユーザーは、オンプレミスおよびクラウドの両方のアプリケーションへのアクセス権を必要とします。 そうしたさまざまな (オンプレミスとクラウドの) アプリケーションの垣根を越えた単一の ID が必要です。

プロビジョニングは、特定の条件に基づいてオブジェクトを作成し、それを最新の状態に維持すると共に、その条件を満たさなくなったら削除するプロセスです。 たとえば、新しいユーザーが組織に加わると、そのユーザーは人事システムに入力されます。 その時点で、クラウドや Active Directory、さらに、ユーザーがアクセスする必要のあるさまざまなアプリケーションには、プロビジョニングを通じて対応するユーザー アカウントを作成することができます。 そうすることで、ユーザーは出社したその日から仕事に着手し、必要なアプリケーションやシステムにアクセスすることができるのです。

[Image: Microsoft Entra ID によるクラウド プロビジョニングの図。]

Microsoft Entra ID に関して言えば、プロビジョニングは大きく次のシナリオに分けることができます。

- **人事主導のプロビジョニング**
- **アプリのプロビジョニング**
- **ディレクトリのプロビジョニング**

### 人事主導のプロビジョニング

[Image: クラウド HR、オンプレミス HR、Microsoft Entra ID による人事主導プロビジョニングの図。]

人事からクラウドへのプロビジョニングには、人事システム内の情報に基づいてオブジェクト (ユーザー、ロール、グループなど) を作成することが含まれます。

最も一般的なシナリオは、新しい従業員が会社に加わったときの、それらの従業員の人事システムへの入力です。 その後、ユーザーはクラウドにプロビジョニングされます。 この場合は、Microsoft Entra ID です。 人事からのプロビジョニングで生じうるシナリオは次のとおりです。

- **新しい従業員の雇用** - クラウド HR に新しい従業員が追加されると、Active Directory、Microsoft Entra ID、必要に応じて Microsoft 365 や Microsoft Entra ID によってサポートされているその他の SaaS アプリケーションでユーザー アカウントが自動的に作成され、メール アドレスが クラウド HR に書き戻されます。
- **従業員属性とプロファイルの更新** - クラウド人事で従業員レコード (名前、タイトル、マネージャーなど) が更新されると、ユーザー アカウントは Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および Microsoft Entra ID でサポートされるその他の SaaS アプリケーションで自動的に更新されます。
- **従業員の退職** - クラウド HR で従業員が退職状態になると、Active Directory、Microsoft Entra ID、必要に応じて Microsoft 365 や Microsoft Entra ID によってサポートされているその他の SaaS アプリケーションでユーザー アカウントが自動的に無効になります。
- **従業員の再雇用** - クラウド HR で従業員が再雇用されると、Active Directory、Microsoft Entra ID、必要に応じて Microsoft 365 や Microsoft Entra ID によってサポートされているその他の SaaS アプリケーションに以前のアカウントが (設定に応じて) 自動的に再アクティブ化または再プロビジョニングされます。

### アプリ プロビジョニング

[Image: オンプレミス アプリ、Microsoft 以外のクラウド アプリ、Microsoft Entra ID によるアプリ プロビジョニングの図。]

Microsoft Entra ID において、**[アプリのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)**という用語は、ユーザーがアクセスする必要があるクラウド アプリケーション内にユーザーの ID とロールを自動的に作成することを意味します。 自動プロビジョニングには、ユーザー ID の作成に加えて、状態または役割が変化したときのユーザー ID のメンテナンスおよび削除が含まれます。 一般的なシナリオには、[Dropbox](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dropboxforbusiness-provisioning-tutorial)、[Salesforce](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-provisioning-tutorial)、[ServiceNow](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-provisioning-tutorial) などのアプリケーションへの Microsoft Entra ユーザーのプロビジョニングが含まれます。

### ディレクトリのプロビジョニング

[Image: クラウド プロビジョニング]

オンプレミス プロビジョニングには、オンプレミス ソース (Active Directory など) から Microsoft Entra ID へのプロビジョニングが含まれます。

最も一般的なシナリオは、Active Directory (AD) 内のユーザーが Microsoft Entra ID にプロビジョニングされるケースです。

これは Microsoft Entra Connect 同期と Microsoft Entra Connect クラウド プロビジョニング、Microsoft Identity Manager によって実現されています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/whatis-hybrid-identity"} -->
## Microsoft Entra ID のハイブリッド ID とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity
- Service: entra-id / hybrid
- Article date: 2025-04-09
- Summary: ハイブリッド ID には、オンプレミスとクラウドの両方で、認証と承認に共通のユーザー ID があります。

現在、企業や企業は、オンプレミスアプリケーションとクラウド アプリケーションの組み合わせをますますデプロイしています。 ユーザーは、オンプレミスとクラウドの両方でこれらのアプリケーションにアクセスする必要があります。 オンプレミスとクラウドの両方でユーザーを管理することは、困難なシナリオになります。

Microsoft の ID ソリューションは、オンプレミスとクラウドベースの機能にまたがっています。 これらのソリューションでは、場所に関係なく、すべてのリソースに対する認証と承認のための共通ユーザー ID が作成されます。 この **ハイブリッド ID**と呼びます。

[Image: 新しいハイブリッド シナリオの図。]

ハイブリッド ID は、プロビジョニングと同期によって実現されます。 プロビジョニングとは、特定の条件に基づいてオブジェクトを作成し、オブジェクトを最新の状態に保ち、条件が満たされなくなったときにオブジェクトを削除するプロセスです。 同期は、オンプレミスのユーザーとグループの ID 情報がクラウドと一致していることを確認する役割を担います。

詳細については、「[プロビジョニングとは」を参照してください。](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning) と [ディレクトリ間プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-inter-directory-provisioning).

### Microsoft Entra Connect を使用するためのライセンス要件

この機能の使用は無料で、Azure サブスクリプションに含まれています。
<!-- /MSL-PAGE -->
