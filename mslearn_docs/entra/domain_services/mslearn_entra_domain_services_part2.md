# Microsoft Learn — Microsoft Entra / Microsoft Entra Domain Services (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 4

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-create-management-vm"} -->
## チュートリアル - Microsoft Entra Domain Services 用の管理 VM を作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: このチュートリアルでは、Microsoft Entra Domain Services マネージド ドメインの管理に使用する Windows 仮想マシンを作成して構成する方法について説明します。

Microsoft Entra Domain Services は、Windows Server Active Directory と完全に互換性のあるドメイン参加、グループ ポリシー、LDAP、Kerberos/NTLM 認証などのマネージド ドメイン サービスを提供します。 このマネージド ドメインは、オンプレミスの Active Directory Domain Services ドメインと同じリモート サーバー管理ツール (RSAT) を使用して管理します。 Domain Services はマネージド サービスであるため、リモート デスクトップ プロトコル (RDP) を使用してドメイン コントローラーに接続するなど、実行できない管理タスクがいくつかあります。

このチュートリアルでは、Azure で Windows Server VM を構成し、Domain Services マネージド ドメインを管理するために必要なツールをインストールする方法について説明します。

このチュートリアルでは、以下の内容を学習します。

- マネージド ドメインで使用可能な管理タスクについて
- Windows Server VM に Active Directory 管理ツールをインストールする
- Active Directory 管理センターを使用して一般的なタスクを実行する

Azure サブスクリプションをお持ちでない場合は、開始 [する前にアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、 [Microsoft Entra Domain Services マネージド ドメインを作成して構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)する最初のチュートリアルを参照してください。
- マネージド ドメインに参加している Windows Server VM。
    - 必要に応じて、 [Windows Server VM を作成してマネージド ドメインに参加させる前のチュートリアルを](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)参照してください。
- *Microsoft Entra テナントの Microsoft Entra DC 管理者*グループのメンバーであるユーザー アカウント。
- Domain Services 仮想ネットワークにデプロイされた Azure Bastion ホスト。
    - 必要に応じて [Azure Bastion ホストを作成](https://learn.microsoft.com/ja-jp/azure/bastion/tutorial-create-host-portal)してください。

### Microsoft Entra 管理センターにサインインする

このチュートリアルでは、Microsoft Entra 管理センターを使用して管理 VM を作成して構成します。 まず、Microsoft Entra 管理センター にサインインします。

### Domain Services で使用可能な管理タスク

Domain Services は、ユーザー、アプリケーション、およびサービスが使用するマネージド ドメインを提供します。 このようなアプローチにより、実行できる管理タスクや、マネージド ドメイン内で与えられる特権が一部変更されています。 これらのタスクとアクセス許可は、通常のオンプレミス Active Directory Domain Services 環境と異なる場合があります。 また、リモート デスクトップを使用してマネージド ドメイン上のドメイン コントローラーに接続することもできません。

#### マネージド ドメインで実行できる管理タスク

*AAD DC Administrators* グループのメンバーには、次のようなタスクを実行できるマネージド ドメインの特権が付与されます。

- マネージド ドメインの *AADDC コンピューター* と *AADDC Users* コンテナーの組み込みグループ ポリシー オブジェクト (GPO) を構成します。
- マネージド ドメイン上で DNS を管理する。
- マネージド ドメインでカスタム組織単位 (OU) を作成して管理します。
- マネージド ドメインに参加しているコンピューターへの管理アクセスを取得する。

#### マネージド ドメインに対して持っていない管理特権

マネージド ドメインはロックダウンされているため、ドメインで特定の管理タスクを実行する権限がありません。 次の例には、実行できないタスクがあります。

- マネージド ドメインのスキーマを拡張する。
- リモート デスクトップを使用してマネージド ドメインのドメイン コントローラーに接続する。
- マネージド ドメインにドメイン コントローラーを追加する。
- 管理対象ドメインに対する*ドメイン管理者*または*エンタープライズ管理者*特権を持っていません。

### Windows Server VM にサインインする

前のチュートリアルでは、Windows Server VM が作成され、マネージド ドメインに参加しました。 その VM を使用して管理ツールをインストールします。 必要に応じて、 [チュートリアルの手順に従って Windows Server VM を作成し、マネージド ドメインに参加させます](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)。

注

このチュートリアルでは、マネージド ドメインに参加している Azure の Windows Server VM を使用します。 マネージド ドメインに参加している Windows クライアント (Windows 10 など) を使用することもできます。

Windows クライアントに管理ツールをインストールする方法の詳細については、「[リモート サーバー管理ツール (RSAT) のインストール」を](https://social.technet.microsoft.com/wiki/contents/articles/2202.remote-server-administration-tools-rsat-for-windows-client-and-windows-server-dsforum2wiki.aspx)参照してください。

開始するには、次のように Windows Server VM に接続します。

1. Microsoft Entra 管理センターで、左側にある **[リソース グループ** ] を選択します。 *myResourceGroup* など、VM が作成されたリソース グループを選択し、*myVM* などの VM を選択します。
2. VM の **[概要]** ペインで **[接続]** を選択し、 **[Bastion]** を選択します。

    [Image: Microsoft Entra 管理センターで Bastion を使用して Windows 仮想マシンに接続する]
3. VM の資格情報を入力し、[ **接続**] を選択します。

    [Image: Microsoft Entra 管理センターの Bastion ホスト経由で接続する]

必要に応じて、ポップアップの表示を Web ブラウザーに許可して、Bastion 接続を表示します。 VM への接続には数秒かかります。

### Active Directory 管理ツールをインストールする

Active Directory 管理センター (ADAC) や AD PowerShell など、オンプレミスの AD DS 環境と同じ管理ツールをマネージド ドメインで使用します。 これらのツールは、Windows Server およびクライアント コンピューターのリモート サーバー管理ツール (RSAT) 機能の一部としてインストールできます。 その後、 *AAD DC Administrators* グループのメンバーは、マネージド ドメインに参加しているコンピューターからこれらの AD 管理ツールを使用して、マネージド ドメインをリモートで管理できます。

ドメインに参加している VM に Active Directory 管理ツールをインストールするには、次の手順を実行します。

1. VM にサインインするときに **サーバー マネージャー** が既定で開かない場合は、[ **スタート** ] メニューを選択し、[ **サーバー マネージャー**] を選択します。
2. *[サーバー マネージャー*] ウィンドウの **[ダッシュボード**] ウィンドウで、[**役割と機能の追加]** を選択します。
3. 役割**と機能の追加ウィザード**の [*開始する前*に] ページで、[**次へ**] を選択します。
4. *[インストールの種類*] で、[**役割ベースまたは機能ベースのインストール**] オプションをオンのままにして、[**次へ**] を選択します。
5. [ **サーバーの選択]** ページで、サーバー プールから現在の VM ( *myvm.aaddscontoso.com* など) を選択し、[ **次へ**] を選択します。
6. [ **サーバーの役割]** ページで、[ **次へ**] をクリックします。
7. [ **機能** ] ページで、[ **リモート サーバー管理ツール** ] ノードを展開し、[ **役割管理ツール** ] ノードを展開します。

    役割管理ツールの一覧から **AD DS と AD LDS ツール** 機能を選択し、[ **次へ**] を選択します。

    [Image: [機能] ページから]
8. **[確認]** ページで **[インストール]** を選択します。 管理ツールのインストールには 1 ~ 2 分かかる場合があります。
9. 機能のインストールが完了したら、[ **閉じる** ] を選択して **役割と機能の追加** ウィザードを終了します。

### Active Directory 管理ツールを使用する

管理ツールがインストールされたら、それらを使用してマネージド ドメインを管理する方法を見てみましょう。 *AAD DC Administrators* グループのメンバーであるユーザー アカウントを使用して VM にサインインしていることを確認します。

1. **[スタート**] メニューの **[Windows 管理ツール**] を選択します。 前の手順でインストールした AD 管理ツールが一覧表示されます。

    [Image: サーバーにインストールされている管理ツールの一覧]
2. **Active Directory 管理センターを選択します**。
3. マネージド ドメインを探索するには、左側のウィンドウでドメイン名 ( *aaddscontoso* など) を選択します。 *AADDC コンピューター*と *AADDC ユーザー*という名前の 2 つのコンテナーが一覧の一番上にあります。

    [Image: マネージド ドメインの使用可能なコンテナー部分を一覧表示する]
4. マネージド ドメインに属しているユーザーとグループを表示するには、 **AADDC Users** コンテナーを選択します。 このコンテナーには、Microsoft Entra テナントのユーザー アカウントとグループが一覧表示されます。

    次の出力例では、 *Contoso Admin* という名前のユーザー アカウントと *AAD DC 管理者* のグループがこのコンテナーに表示されます。

    [Image: Active Directory 管理センターで Domain Services ドメイン ユーザーの一覧を表示する]
5. マネージド ドメインに参加しているコンピューターを表示するには、 **AADDC Computers** コンテナーを選択します。 *myVM* などの現在の仮想マシンのエントリが一覧表示されます。 マネージド ドメインに参加しているすべてのデバイスのコンピューター アカウントは、この *AADDC Computers* コンテナーに格納されます。

ユーザー アカウントのパスワードのリセットやグループ メンバーシップの管理など、Active Directory 管理センターの一般的なアクションを使用できます。 これらのアクションは、マネージド ドメインで直接作成されたユーザーとグループに対してのみ機能します。 ID 情報は、Microsoft Entra ID *から* Domain Services にのみ同期されます。 Domain Services から Microsoft Entra ID への書き戻しはありません。 Microsoft Entra ID から同期されたユーザーのパスワードまたはマネージド グループ メンバーシップを変更して、それらの変更を再同期することはできません。

管理ツールの一部としてインストールされている *Windows PowerShell 用 Active Directory モジュール*を使用して、マネージド ドメインの一般的なアクションを管理することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-create-replica-set"} -->
## チュートリアル - Microsoft Entra Domain Services でレプリカ セットを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-replica-set
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Microsoft Entra Domain Services でサービスの回復性を確保するために Microsoft Entra 管理センターでレプリカ セットを作成して使用する方法について説明します

Microsoft Entra Domain Services マネージド ドメインの回復性を向上させるか、アプリケーションに近い追加の地理的な場所にデプロイするには、 *レプリカ セット*を使用します。 *aaddscontoso.com* など、すべての Domain Services マネージド ドメイン名前空間には、1 つの初期レプリカ セットが含まれています。 他の Azure リージョンで追加のレプリカ セットを作成する機能により、マネージド ドメインの地理的な回復性が提供されます。

Domain Services をサポートする任意の Azure リージョン内のピアリングされた仮想ネットワークにレプリカ セットを追加できます。

このチュートリアルでは、以下の内容を学習します。

- 仮想ネットワークの要件を理解する
- レプリカ セットを作成する
- レプリカ セットを削除する

Azure サブスクリプションをお持ちでない場合は、開始 [する前にアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。

    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。

    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Azure Resource Manager デプロイ モデルを使用して作成され、Microsoft Entra テナントで構成された Microsoft Entra Domain Services マネージド ドメイン。

    - 必要に応じて、[Microsoft Entra Domain Services のマネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance-advanced)。

    Von Bedeutung

    レプリカ セットをサポートするには、マネージド ドメインに最低限の *Enterprise* SKU を使用する必要があります。 必要に応じて、 [マネージド ドメインの SKU を変更します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/change-sku)。

### Microsoft Entra 管理センターにサインインする

このチュートリアルでは、Microsoft Entra 管理センターを使用してレプリカ セットを作成および管理します。 まず、Microsoft Entra 管理センター にサインインします。

### ネットワークに関する考慮事項

レプリカ セットをホストする仮想ネットワークは、相互に通信できる必要があります。 Domain Services に依存するアプリケーションとサービスでも、レプリカ セットをホストする仮想ネットワークへのネットワーク接続が必要です。 完全にメッシュ化されたネットワークを作成するには、すべての仮想ネットワーク間で Azure 仮想ネットワーク ピアリングを構成する必要があります。 これらのピアリングにより、レプリカ セット間の効果的なサイト内レプリケーションが有効になります。

Domain Services でレプリカ セットを使用する前に、次の Azure 仮想ネットワーク要件を確認してください。

- IP アドレス空間が重複しないようにして、仮想ネットワークのピアリングとルーティングを可能にします。
- シナリオをサポートするのに十分な IP アドレスを持つサブネットを作成します。
- Domain Services に独自のサブネットがあることを確認します。 この仮想ネットワーク サブネットをアプリケーションの VM やサービスと共有しないでください。
- ピアリングされた仮想ネットワークは推移的ではありません。

ヒント

Microsoft Entra 管理センターでレプリカ セットを作成すると、仮想ネットワーク間のネットワーク ピアリングが自動的に作成されます。

必要に応じて、Microsoft Entra 管理センターでレプリカ セットを追加するときに、仮想ネットワークとサブネットを作成できます。 または、レプリカ セットの宛先リージョンにある既存の仮想ネットワーク リソースを選択し、ピアリングがまだ存在しない場合は自動的に作成されるようにすることができます。

### レプリカ セットを作成する

*aaddscontoso.com* などのマネージド ドメインを作成すると、初期レプリカ セットが作成されます。 追加のレプリカ セットは、同じ名前空間と構成を共有します。 構成、ユーザー ID と資格情報、グループ、グループ ポリシー オブジェクト、コンピューター オブジェクト、およびその他の変更を含む Domain Services への変更は、AD DS レプリケーションを使用してマネージド ドメイン内のすべてのレプリカ セットに適用されます。

このチュートリアルでは、最初の Domain Services レプリカ セットとは異なる追加のレプリカ セットを Azure リージョンに作成します。

追加のレプリカ セットを作成するには、次の手順を実行します。

1. Microsoft Entra 管理センターで、Microsoft Entra Domain Services 検索して選択します。
2. 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
3. 左側で、[ **レプリカ セット**] を選択します。 次のスクリーンショット例に示すように、各マネージド ドメインには、選択したリージョンに 1 つの初期レプリカ セットが含まれています。

    [Image: Microsoft Entra 管理センターでレプリカ セットを表示して追加するスクリーンショットの例]

    追加のレプリカ セットを作成するには、[ **+ 追加**] を選択します。
4. [ *レプリカ セットの追加* ] ウィンドウで、宛先リージョン ( *米国東部*など) を選択します。

    *vnet-eastus* などの宛先リージョンの仮想ネットワークを選択し、*aadds-subnet* などのサブネットを選択します。 必要に応じて、[ **新規作成** ] を選択して宛先リージョンに仮想ネットワークを追加し、[ **管理** ] を選択して Domain Services のサブネットを作成します。

    まだ存在しない場合は、既存のマネージド ドメインの仮想ネットワークと宛先仮想ネットワークの間に Azure 仮想ネットワーク ピアリングが自動的に作成されます。

    次のスクリーンショットの例は、 *米国東部*で新しいレプリカ セットを作成するプロセスを示しています。

    [Image: Microsoft Entra 管理センターでレプリカ セットを作成するスクリーンショットの例]
5. 準備ができたら、**[保存]** を選択します。

レプリカ セットを作成するプロセスは、ターゲット リージョンにリソースが作成されるため、しばらく時間がかかります。 マネージド ドメイン自体は、AD DS レプリケーションを使用してレプリケートされます。

次のスクリーンショットの例に示すように、レプリカ セットはデプロイが続行すると *プロビジョニング* として報告されます。 完了すると、レプリカ セットが *[実行中*] と表示されます。

[Image: Microsoft Entra 管理センターでのレプリカ セットのデプロイ状態のスクリーンショットの例]

### レプリカ セットを削除する

現在、マネージド ドメインは 5 つのレプリカ (初期レプリカ セットと 4 つの追加レプリカ セット) に制限されています。 レプリカ セットが不要になった場合、または別のリージョンにレプリカ セットを作成する場合は、不要なレプリカ セットを削除できます。

Von Bedeutung

マネージド ドメイン内の最後のレプリカ セットまたは初期レプリカ セットは削除できません。

レプリカ セットを削除するには、次の手順を実行します。

1. Microsoft Entra 管理センターで、Microsoft Entra Domain Services 検索して選択します。
2. 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
3. 左側で、[ **レプリカ セット**] を選択します。 レプリカ セットの一覧から、削除するレプリカ セットの横にある **...** コンテキスト メニューを選択します。
4. コンテキスト メニューから [ **削除** ] を選択し、レプリカ セットを削除することを確認します。
5. Domain Services 管理 VM で、DNS コンソールにアクセスし、削除されたレプリカ セットからドメイン コントローラーの DNS レコードを手動で削除します。

注

レプリカ セットの削除には時間がかかる場合があります。

レプリカ セットで使用されている仮想ネットワークまたはピアリングが不要になった場合は、それらのリソースを削除することもできます。 削除する前に、他のリージョンの他のアプリケーション リソースにネットワーク接続が必要がないことを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-perform-disaster-recovery-drill"} -->
## チュートリアル - Microsoft Entra Domain Services でディザスター リカバリーの訓練を実行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-perform-disaster-recovery-drill
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Microsoft Entra Domain Services でレプリカ セットを使用して、ディザスター リカバリーの訓練を実行する方法について学習します

この記事では、レプリカ セットを使用して Microsoft Entra Domain Services のディザスター リカバリー (DR) 訓練を実行する方法について説明します。 この演習では、ネットワークの仮想ネットワーク プロパティを変更してネットワークへのクライアント アクセスをブロックすることで、レプリカ セットの 1 つがオフラインになることをシミュレートします。 レプリカ セットがオフラインにならないという点で、本当の DR の訓練ではありません。

DR の訓練には次の内容が含まれます。

1. クライアント コンピューターが、特定のレプリカ セットに接続されています。 ドメインに対して認証を行い、LDAP クエリを実行できます。
2. レプリカ セットへのクライアントの接続が終了します。 これは、ネットワーク アクセスを制限することで発生します。
3. 次に、クライアントは別のレプリカ セットとの新しい接続を確立します。 これが行われると、クライアントはドメインに対して認証を行い、LDAP クエリを実行できるようになります。
4. ドメイン メンバーが再起動され、ドメイン ユーザーは再起動後にサインインできます。
5. ネットワーク制限が解除され、クライアントが元のレプリカ セットに接続できるようになります。

### 前提条件

DR の訓練を実行するには、次の要件を満たす必要があります。

- 少なくとも 1 つの追加のレプリカ セットが配置された、デプロイ済みのアクティブな Domain Services インスタンス。 ドメインは正常な状態である必要があります。
- Domain Services ホステッド ドメインに参加しているクライアント マシン。 クライアントは独自の仮想ネットワークに存在する必要があります。また、両方のレプリカ セットの仮想ネットワークで仮想ネットワーク ピアリングが有効になっており、仮想ネットワークには、DNS にリストされているレプリカ セット内のすべてのドメイン コントローラーの IP アドレスが存在する必要があります。

### 環境の検証

1. ドメイン アカウントを使用してクライアント コンピューターにサインインします。
2. Active Directory Domain Services の RSAT ツールをインストールします。
3. 管理者特権の PowerShell ウィンドウを開始します。
4. 基本的なドメイン検証チェックを実行します。
    - `nslookup [domain]` を実行して、DNS 解決が正常に機能していることを確認します
    - `nltest /dsgetdc:` を実行して、成功が返され、現在どのドメイン コントローラーが使用されているかを確認します
    - `nltest /dclist:` を実行して、ディレクトリ内のドメイン コントローラーの完全な一覧を返します。
5. ディレクトリ内の各ドメイン コントローラーで基本的なドメイン コントローラー検証を実行します ("nltest /dclist:" の出力から完全な一覧を取得できます)。
    - `nltest /sc_reset:[domain name]\[domain controller name]` を実行して、ドメイン コントローラーとのセキュリティで保護された接続を確立します。
    - `Get-AdDomain` を実行して、基本的なディレクトリ設定を取得します。

### ディザスター リカバリーの訓練を実行する

Domain Services インスタンス内の各レプリカ セットに対して、これらの操作を実行する必要があります。 この操作により、各レプリカ セットの停止がシミュレートされます。 ドメイン コントローラーに到達できない場合、クライアントは自動的に到達可能なドメイン コントローラーにフェールオーバーします。 このエクスペリエンスは、エンド ユーザーやワークロードにとってシームレスである必要があります。 そのため、アプリケーションやサービスが特定のドメイン コントローラーを指定しないことが重要です。

1. オフラインになるようにシミュレートするレプリカ セット内のドメイン コントローラーを特定します。
2. クライアント コンピューターで、`nltest /sc_reset:[domain]\[domain controller name]` を使用していずれかのドメイン コントローラーに接続します。
3. Azure portal で、クライアントの仮想ネットワーク ピアリングにアクセスし、クライアントとレプリカ セットの間のすべてのトラフィックがブロックされるようにプロパティを更新します。
    1. 更新する必要があるピアリングされたネットワークを選択します。
    2. 仮想ネットワークに出入りするすべてのネットワーク トラフィックをブロックするよう選択します。 [Image: Azure portal でトラフィックをブロックする方法のスクリーンショット]
4. クライアント コンピューターで、手順 2 と同じ nltest コマンドを使用して、両方のドメイン コントローラーとセキュリティで保護された接続の再確立を試行します。 ネットワーク接続がブロックされているため、これらの操作は失敗します。
5. `Get-AdDomain` と `Get-AdForest` を実行して、基本的なディレクトリ プロパティを取得します。 これらの呼び出しは、他のレプリカ セット内のいずれかのドメイン コントローラーに自動的に移動するため、成功します。
6. クライアントを再起動し、同じドメイン アカウントでサインインします。 これは、認証が期待どおりに動作していて、ログインがブロックされていないことを示しています。
7. Azure portal で、クライアントの仮想ネットワーク ピアリングにアクセスし、すべてのトラフィックがブロック解除されるようにプロパティを更新します。 これにより、手順 3 で行った変更が元に戻されます。
8. クライアント コンピューターで、手順 2 と同じ nltest コマンドを使用して、ドメイン コントローラーとセキュリティで保護された接続の再確立を試行します。 ネットワーク接続のブロックが解除されると、これらの操作は成功するはずです。

これらの操作は、クライアントがいずれかのレプリカ セットに到達できない場合でも、ドメインが引き続き使用可能であることを示しています。 Domain Services インスタンス内の各レプリカ セットに対して、この一連の手順を実行します。

### まとめ

これらの手順を実行すると、Domain Services 内のいずれかのレプリカ セットに到達できない場合に、ドメイン メンバーは引き続きディレクトリにアクセスできることがわかります。 クライアント コンピューターの代わりにレプリカ セットのすべてのネットワーク アクセスをブロックすることによって同じ動作をシミュレートできますが、これは推奨されません。 クライアントの観点からは動作は変更されませんが、ネットワーク アクセスが復元されるまで、Domain Services インスタンスの正常性に影響します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/use-azure-monitor-workbooks"} -->
## Microsoft Entra Domain Services で Azure Monitor ワークブックを使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/use-azure-monitor-workbooks
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Azure Monitor ワークブックを使ってセキュリティ監査を確認し、Microsoft Entra Domain Services に管理されたドメインの問題を理解する方法を学びます。

Microsoft Entra Domain Services マネージド ドメインの状態を理解するために、セキュリティ監査イベントを有効にすることができます。 これらのセキュリティ監査イベントは、テキスト、分析クエリ、パラメーターを組み合わせて豊富な対話型レポートを作成する Azure Monitor ブックを使用して確認できます。 Domain Services には、監査イベントを掘り下げて環境を管理できる、セキュリティの概要とアカウント アクティビティ用のブック テンプレートが含まれています。

この記事では、Azure Monitor ワークブックを使用して Domain Services のセキュリティ監査イベントを確認する方法について説明します。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、[Microsoft Entra Domain Services マネージド ドメインを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)チュートリアルを完了します。
- Log Analytics ワークスペースにデータをストリーミングするマネージド ドメインに対して有効になっているセキュリティ監査イベント。
    - 必要に応じて、 [Domain Services のセキュリティ監査を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events)。

### Azure Monitor ワークブックの概要

Domain Services でセキュリティ監査イベントが有効になっていると、マネージド ドメインの問題を分析して特定するのが難しい場合があります。 Azure Monitor を使用すると、これらのセキュリティ監査イベントを集計し、データにクエリを実行できます。 Azure Monitor ブックを使用すると、このデータを視覚化して、問題をより迅速かつ簡単に特定できます。

ブック テンプレートは、複数のユーザーとチームが柔軟に再利用できるように設計された、キュレーションされたレポートです。 ブック テンプレートを開くと、Azure Monitor 環境のデータが読み込まれます。 組織内の他のユーザーに影響を与えずにテンプレートを使用でき、テンプレートに基づいて独自のブックを保存できます。

Domain Services には、次の 2 つのブック テンプレートが含まれています。

- セキュリティの概要レポート
- アカウント アクティビティ レポート

ワークブックを編集および管理する方法について詳しくは、[Azure Monitor ワークブックの概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-overview)を参照してください。

### セキュリティ概要レポートブックを使用する

使用状況の理解を深め、潜在的なセキュリティ上の脅威を特定するために、セキュリティの概要レポートでは、サインイン データが要約され、確認したいアカウントが特定されます。 特定の日付範囲のイベントを表示し、不適切なパスワードの試行やアカウントが無効にされた場所など、特定のサインイン イベントにドリルダウンできます。

セキュリティ概要レポートのブック テンプレートにアクセスするには、次の手順を実行します。

1. Azure portal で **Microsoft Entra Domain Services** を検索して選択します。
2. マネージド ドメイン (*aaddscontoso.com* など) を選択します
3. 左側のメニューで、**[監視] &gt; [ブック]** を選択します。

    [Image: セキュリティ概要レポートとアカウント アクティビティ レポートを選択する場所が強調表示されているスクリーンショット。]
4. **セキュリティ概要レポート**を選択します。
5. ブックの上部にあるドロップダウン メニューから、Azure サブスクリプションと Azure Monitor ワークスペースを選択します。

    次のスクリーンショット例に示すように、[**過去 7 日間**] などの*時間範囲*を選択します。

    [Image: [ワークブック] メニューオプションを選択する]

    **タイル ビュー**と**グラフ ビュー**のオプションを変更して、必要に応じてデータを分析および視覚化することもできます。
6. 特定のイベントの種類にドリルダウンするには、次の例に示すように、**アカウント**ロックアウトなどの*サインイン結果*カードのいずれかを選択します。

    [Image: Azure Monitor Workbooks で視覚化されたセキュリティ概要レポートデータの例]
7. グラフの下にあるセキュリティ概要レポートの下部で、選択したアクティビティの種類を分類します。 次のレポート例に示すように、右側に関係するユーザー名でフィルター処理できます。

    [Image: Azure Monitor ワークブックのアカウント ロックアウトの詳細。]

### アカウント アクティビティ レポート ブックを使用する

特定のユーザー アカウントの問題のトラブルシューティングに役立つよう、アカウント アクティビティ レポートで詳細な監査イベント ログ情報が分割されます。 サインイン中に不適切なユーザー名またはパスワードが指定されたタイミングと、サインイン試行のソースを確認できます。

アカウント アクティビティ レポートのブック テンプレートにアクセスするには、次の手順を実行します。

1. Azure portal で **Microsoft Entra Domain Services** を検索して選択します。
2. マネージド ドメイン (*aaddscontoso.com* など) を選択します
3. 左側のメニューで、**[監視] &gt; [ブック]** を選択します。
4. **アカウント アクティビティ レポート**を選択します。
5. ブックの上部にあるドロップダウン メニューから、Azure サブスクリプションと Azure Monitor ワークスペースを選択します。

    [**過去 30 日間**] などの*時間範囲*を選択し、**タイル ビュー**でデータを表示する方法を選択します。

    次のレポート例に示すように、アカウント **ユーザー名** ( *felix* など) でフィルター処理できます。

    [Image: Azure Monitor ワークブックのアカウント アクティビティ レポート。]

    グラフの下の領域には、個々のサインイン イベントと、アクティビティの結果やソース ワークステーションなどの情報が表示されます。 この情報は、アカウントロックアウトを引き起こす可能性があるサインイン イベントの繰り返しのソースを特定したり、潜在的な攻撃を示したりするのに役立ちます。

セキュリティ概要レポートと同様に、レポートの上部にあるさまざまなタイルにドリルダウンして、必要に応じてデータを視覚化および分析できます。

### ブックを保存および編集する

Domain Services によって提供される 2 つのテンプレート ブックは、独自のデータ分析から始めるのに適した場所です。 データ クエリと調査をより詳細に行う必要がある場合は、独自のブックを保存してクエリを編集できます。

1. ブック テンプレートのコピーを保存するには、[**編集 &gt; 名前をつけて保存 &gt; 共有レポート**] を選択し、名前を指定して保存します。
2. テンプレートの独自のコピーから、[ **編集]** を選択して編集モードに入ります。 レポートの任意の部分の横にある青い **[編集]** ボタンを選択して変更できます。

Azure Monitor ブックのすべてのグラフとテーブルは、Kusto クエリを使用して生成されます。 独自のクエリの作成の詳細については、 [Azure Monitor ログ クエリ](https://learn.microsoft.com/ja-jp/azure/data-explorer/kusto/query/) と [Kusto クエリのチュートリアル](https://learn.microsoft.com/ja-jp/azure/data-explorer/kusto/query/tutorials/learn-common-operators?pivots=azuredataexplorer)を参照してください。
<!-- /MSL-PAGE -->
